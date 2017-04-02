package indexer;

import analyzer.CodeAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.miscellaneous.PerFieldAnalyzerWrapper;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.dom4j.DocumentException;
import parser.HTMLParser;
import parser.XMLParser;
import stackoverflow.Post;
import stackoverflow.PostField;


import java.io.*;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


import static org.apache.lucene.document.Field.Store.*;

/**
 * Created by str2n on 2017/3/24.
 */
public class PostIndexer {

    private IndexWriter writer = null;

    public PostIndexer() throws IOException {
        Directory questionIndexDir = FSDirectory.open(Paths.get(utils.Paths.POSTINDEXPATH));

        Map<String, Analyzer> codeSpecialAnalyzer = new HashMap<String, Analyzer>();
        codeSpecialAnalyzer.put("Code", new CodeAnalyzer());
        PerFieldAnalyzerWrapper wrapper = new PerFieldAnalyzerWrapper(new StandardAnalyzer(), codeSpecialAnalyzer);
        IndexWriterConfig config = new IndexWriterConfig(wrapper);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

        try {
            writer = new IndexWriter(questionIndexDir, config);
        } catch (IOException e) {
            System.out.println("Question IndexWriter initialize Error.");
        }
    }

    private static Document getDocument(Post p) {

        Document doc = new Document();
        // Common field
        doc.add(new IntPoint(PostField.Id.toString(), p.getId()));
        doc.add(new StoredField(PostField.IdCopy.toString(), p.getId()));

        doc.add(new StoredField(PostField.Score.toString(), p.getScore()));
        doc.add(new StoredField(PostField.CreationDate.toString(), p.getCreationDate()));

        doc.add(new TextField(PostField.Body.toString(), HTMLParser.ParseText(p.getBody()), YES));
        doc.add(new TextField(PostField.Code.toString(), HTMLParser.ParseCode(p.getBody()), YES));
        // Only for answer
        if (p.getParentId() != 0) {
            doc.add(new IntPoint(PostField.ParentId.toString(), p.getParentId()));
            doc.add(new StoredField(PostField.ParentIdCopy.toString(), p.getParentId()));
        } else {
            // Only for question
            doc.add(new TextField(PostField.Title.toString(), p.getTitle(), YES));
            doc.add(new StoredField(PostField.AnswerCount.toString(), p.getAnswerCount()));
            doc.add(new StoredField(PostField.ViewCount.toString(), p.getViewCount()));
            // Only for specific question
            if (p.getAcceptedAnswerId() != 0) {
                doc.add(new StoredField(PostField.AcceptedAnswerId.toString(), p.getAcceptedAnswerId()));
            }
            if (p.getTags() != null) {
                doc.add(new TextField(PostField.Tags.toString(), HTMLParser.ParseTag(p.getTags()), YES));
            }
        }
        return doc;
    }

    public void IndexPost() throws IOException {
        System.out.println("Start Question Indexing:");


        String line = null;

        int lineCount = 0;
        File file = new File(utils.Paths.FILTEREDFILEPATH + "python.xml");
        if (file.isFile() && file.exists()) {
            InputStreamReader read = new InputStreamReader(new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(read);
            while ((line = bufferedReader.readLine()) != null) {
                lineCount++;
                try {
                    Document doc = getDocument(new Post(XMLParser.ParsePost(line)));
                    writer.addDocument(doc);
                } catch (DocumentException e) {
                    System.out.println("Parse data from xml wrong. Question Document can't be added at line:" + lineCount);
                } catch (IOException e) {
                    System.out.println("Writer add Question documents wrong." + utils.Paths.FILTEREDFILEPATH + "python.xml" + " at line " + lineCount);
                }
            }
            bufferedReader.close();
            read.close();
        }

        System.out.println("Total number of Question documents indexed: " + writer.maxDoc());

        System.out.println("Start Answer Indexing:");

        File answerFile = new File(utils.Paths.FILTEREDFILEPATH + "pythonanswer.xml");
        lineCount = 0;
        if (answerFile.isFile() && answerFile.exists()) {
            InputStreamReader read = new InputStreamReader(new FileInputStream(answerFile));
            BufferedReader bufferedReader = new BufferedReader(read);
            while ((line = bufferedReader.readLine()) != null) {
                lineCount++;
                try {
                    Document doc = getDocument(new Post(XMLParser.ParsePost(line)));
                    writer.addDocument(doc);
                } catch (DocumentException e) {
                    System.out.println("Parse data from xml wrong. Answer Document can't be added at line:" + lineCount);
                } catch (IOException e) {
                    System.out.println("Writer add Answer documents wrong." + utils.Paths.FILTEREDFILEPATH + "pythonanswer.xml" + " at line " + lineCount);
                }
            }
            bufferedReader.close();
            read.close();
        }

        System.out.println("Total number of Post documents indexed: " + writer.maxDoc());
        try {
            writer.close();
        } catch (IOException e) {
            System.out.println("Answer IndexWriter be closed with errors!");
        }
        System.out.println("Index end.");
    }

}
