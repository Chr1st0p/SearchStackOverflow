package filter;

import org.dom4j.*;
import searcher.QIDSearcher;
import utils.Paths;

import java.io.*;

public class AnswerFilter {

    public static void FilterAnswerByQIdIndex() throws IOException {
        System.out.println("Start Read File:");

        FileWriter writer = null;
        try {
            File ansFile = new File(Paths.FILTEREDFILEPATH + "pythonanswer.xml");

            writer = new FileWriter(ansFile, true);

        } catch (IOException e) {
            e.printStackTrace();
        }

        assert writer != null : "Print Writer is NULL";
        PrintWriter pWriter = new PrintWriter(writer);

        QIDSearcher searcher = new QIDSearcher();

        File file = new File(Paths.POSTSPATH + "Posts.xml");
        if (file.isFile() && file.exists()) {
            InputStreamReader read = new InputStreamReader(new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(read);
            String line = null;
            int lineCount = 0;
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    if (line.length() > 100) {
                        Document document = DocumentHelper.parseText(line);
                        Element root = document.getRootElement();
                        int IdType;
                        int parentId;
                        try {
                            Attribute ID = root.attribute("PostTypeId");
                            IdType = Integer.parseInt(ID.getText());
                            if (IdType == 2) {
                                Attribute pId = root.attribute("ParentId");
                                parentId = Integer.parseInt(pId.getText());
                                if (searcher.search(parentId) == 1) {
                                    pWriter.println(line);
                                }
                            }
                        } catch (Exception e) {
//                        System.out.println("No Tags");
                        }
                    }
                } catch (Exception e) {
//                System.out.println("Error occur at Line:" + lineCount);
                }
                lineCount++;
            }
            bufferedReader.close();
            read.close();
        }

        searcher.close();
        try {
            pWriter.flush();
            writer.flush();
            pWriter.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Filter python answers end");
    }
}
