package exampleapp.app1;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.IntPoint;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.FSDirectory;
import org.dom4j.DocumentException;
import parser.XMLParser;
import stackoverflow.Answer;
import stackoverflow.Post;
import stackoverflow.PostField;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;


/**
 * Created by str2n on 2017/3/31.
 */
public class Main {

    private static boolean ASC = true;
    private static boolean DESC = false;

    public static void main(String[] args) throws IOException, DocumentException {

        ReIndex1 indexer = new ReIndex1();
        indexer.ReIndexAnswer();

        System.out.println("Start read question python");

        IndexReader reIndex1Reader;
        IndexSearcher reIndex1Searcher;

        reIndex1Reader = DirectoryReader.open(FSDirectory.open(Paths.get(utils.Paths.REINDEX1PATH)));
        reIndex1Searcher = new IndexSearcher(reIndex1Reader);

        Map<String, Integer> map = new HashMap<>();

        File file = new File(utils.Paths.FILTEREDFILEPATH + "python.xml");
        if (file.isFile() && file.exists()) {
            InputStreamReader read = new InputStreamReader(new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(read);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                Post p = new Post(XMLParser.ParsePost(line));
                if (p.getAcceptedAnswerId() != 0) {
                    Query query = IntPoint.newExactQuery(PostField.Id.toString(), p.getAcceptedAnswerId());
                    ScoreDoc[] docs = reIndex1Searcher.search(query, 100).scoreDocs;
                    if (docs.length != 0) {
                        for (ScoreDoc doc : docs) {
                            Document document = reIndex1Searcher.doc(doc.doc);
                            String ownerID = document.get(PostField.OwnerUserId.toString());
                            if (Integer.parseInt(ownerID) != 0) {
                                Integer count = map.get(ownerID);
                                map.put(ownerID, (count == null) ? 1 : count + 1);
                            }
                        }
                    }
                }
            }
            bufferedReader.close();
            read.close();
        }

        File result = new File(utils.Paths.REINDEX1PATH + "results.txt");
        FileWriter resultWriter = new FileWriter(result, true);
        PrintWriter resultPrintWriter = new PrintWriter(resultWriter);
        Map<String, Integer> sortedMap = sortByComparator(map, DESC);
        int i = 1;
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            System.out.println("Rank " + i + " UserId: " + entry.getKey() + " Accepted answer number: " + entry.getValue());
            resultPrintWriter.println("UserId:" + entry.getKey() + " Accepted Answer Count" + entry.getValue());
            if (i >= 10) {
                break;
            }
            i++;
        }

        resultPrintWriter.flush();
        resultWriter.flush();
        resultPrintWriter.close();
        resultWriter.close();
    }

    private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order) {

        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, (o1, o2) -> {
            if (order) {
                return o1.getValue().compareTo(o2.getValue());
            } else {
                return o2.getValue().compareTo(o1.getValue());

            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

}
