package utils;

/**
 * Created by str2n on 2017/2/25.
 */
public class Paths {
	//Path for Posts.xml
//    public static final String POSTSPATH = "The directory to store Posts.xml in your computer";
    public static final String POSTSPATH = System.getProperty("user.dir") + "\\postsXML\\";
    
    //Path to store the filtered XML file
    public static final String FILTEREDFILEPATH = System.getProperty("user.dir") + "\\filteredXML\\";
    //Path for question id index -> to  filter answer related to python
    public static final String QIDINDEXPATH = System.getProperty("user.dir") + "\\index\\qidindex\\";
    //
    public static final String POSTINDEXPATH = System.getProperty("user.dir") + "\\index\\postindex\\";

    public static final String REINDEX1PATH = System.getProperty("user.dir") + "\\exampleappindex\\01\\";
    public static final String REINDEX2PATH = System.getProperty("user.dir") + "\\exampleappindex\\02\\";

}
