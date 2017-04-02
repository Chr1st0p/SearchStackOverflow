package exampleapp.app1;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.IntPoint;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.dom4j.DocumentException;
import parser.XMLParser;
import stackoverflow.Post;
import stackoverflow.PostField;

import java.io.*;
import java.nio.file.Paths;



/**
 * Created by str2n on 2017/3/31.
 */
public class ReIndex1 {
    private IndexWriter writer = null;

    public ReIndex1() throws IOException {
        Directory reIndexDir = FSDirectory.open(Paths.get(utils.Paths.REINDEX1PATH));


        IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

        try {
            writer = new IndexWriter(reIndexDir, config);
        } catch (IOException e) {
            System.out.println("Question IndexWriter initialize Error.");
        }
    }

    private static Document getDocument(Post p) {

        Document doc = new Document();

        doc.add(new IntPoint(PostField.Id.toString(), p.getId()));
        doc.add(new StoredField(PostField.OwnerUserId.toString(), p.getOwnerUserId()));
        return doc;
    }

    public void ReIndexAnswer() throws IOException {
        System.out.println("Start Example01 Answer Indexing:");


        File file = new File(utils.Paths.FILTEREDFILEPATH + "pythonanswer.xml");
        if (file.isFile() && file.exists()) {
            InputStreamReader read = new InputStreamReader(new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(read);
            String line = null;
            int lineCount = 1;
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    Document doc = getDocument(new Post(XMLParser.ParsePost(line)));
                    writer.addDocument(doc);
                } catch (DocumentException e) {
                    System.out.println("Parse data from pythonanswer.xml wrong. Answer Document can't be added at line:" + lineCount);
                } catch (IOException e) {
                    System.out.println("Writer add Answer documents wrong." + utils.Paths.FILTEREDFILEPATH + "pythonanswer.xml" + " at line " + lineCount);
                }
                lineCount++;
            }
            bufferedReader.close();
            read.close();
        }

        System.out.println("Total number of Answer documents indexed: " + writer.maxDoc());
        try {
            writer.close();
        } catch (IOException e) {
            System.out.println("Answer IndexWriter be closed with errors!");
        }
        System.out.println("Index end.");
    }
}
