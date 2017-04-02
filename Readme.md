# PyFlowSearch - A Search Engine for Retrieving Python-Related Topic on Stack Overflow

#### 1.Third party libary

The third party can be found in the local directory` .\lib`, if you are running the system with eclipse, you may need to set the build path for the project like below.

![image](https://github.com/Chr1st0p/SearchStackOverflow/raw/master/image/01.png)

And add all these library to Build Path.

![image](https://github.com/Chr1st0p/SearchStackOverflow/raw/master/image/02.png)

Also you can download it your own with the links or use [maven](https://maven.apache.org/) with the `.\pom.xml` to add the library. All the libraries and its link(in [Maven repository](http://www.mvnrepository.com/)) are listed below.

* [dom4j](http://central.maven.org/maven2/dom4j/dom4j/1.6.1/dom4j-1.6.1.jar)
* [Jsoup](http://central.maven.org/maven2/org/jsoup/jsoup/1.10.2/jsoup-1.10.2.jar)
* [lucene core](http://central.maven.org/maven2/org/apache/lucene/lucene-core/6.4.1/lucene-core-6.4.1.jar)
* [lucene queryparser](http://central.maven.org/maven2/org/apache/lucene/lucene-queryparser/6.4.1/lucene-queryparser-6.4.1.jar)
* [lucene common analyzers](http://central.maven.org/maven2/org/apache/lucene/lucene-analyzers-common/6.4.1/lucene-analyzers-common-6.4.1.jar)

#### 2.Set file directory

The `.\postsXML` directory is where you put such huge xml file `Posts.xml`. To start the system, you can put the huge xml in the `.\postsXML` directory or edit the class `utils.Paths` in the code, which means change the `POSTSPATH` to where the `Posts.xml` file dataset exists in your computer.

#### 3.Filter python related questions and answers

After setting `Posts.xml` directory, all python related questions and answers can be filtered by running the `main` method of `FilterPythonMain` class. When filtering is finished, `python.xml` and `pythonanswer.xml` will be created in `.\filteredXML` directory. It may take serval hours for the filtering process.

#### 4. Create index

Index is created with the two filtered xml file `python.xml` and `pythonanswer.xml`. Run `main` method of  `PostIndexMain` class, then index will be created in `.\index\postindex` directory. It may takes 10 minutes to run create the whole index.

#### 5.Search

After index is created, we can query on the field that has been indexed. Run `PostSearchMain` class to search. In the search process, the first step is to input the query, phrase query can be specified with double quotation marks. The picture below shows a example of a query.

![queryexample](https://github.com/Chr1st0p/SearchStackOverflow/raw/master/image/03.png)

Then specify the fields you want to query. The figures below shows query all fields and customized fields example.

Input "0" to query on all fields.

![queryonall](https://github.com/Chr1st0p/SearchStackOverflow/raw/master/image/04.png)

Input "1" for customizing fields, and then input the corresponding number to choose fields.

![queryonspecific](https://github.com/Chr1st0p/SearchStackOverflow/raw/master/image/05.png)

After query fields, user can determine top N results to be returned. Here is a example of a certain N.

![topN](https://github.com/Chr1st0p/SearchStackOverflow/raw/master/image/06.png)

Then two example results will be returned like below.

![queryallresult](https://github.com/Chr1st0p/SearchStackOverflow/raw/master/image/07.png)

![phrasequeryspecificfields](https://github.com/Chr1st0p/SearchStackOverflow/raw/master/image/08.png)

