PyFlowSearch - A Search Engine for Retrieving Python-Related Topic on Stack Overflow
====================================================================================

1.Third party libary
--------------------

The third party library can be found in the local directory\ ``.\lib``.

Also you can download it your own with the links or use
`maven <https://maven.apache.org/>`__ with the ``.\pom.xml`` to add the
library. All the libraries and its link(in `Maven
repository <http://www.mvnrepository.com/>`__) are listed below.

-  `dom4j <http://central.maven.org/maven2/dom4j/dom4j/1.6.1/dom4j-1.6.1.jar>`__

-  `Jsoup <http://central.maven.org/maven2/org/jsoup/jsoup/1.10.2/jsoup-1.10.2.jar>`__

-  `lucene
   core <http://central.maven.org/maven2/org/apache/lucene/lucene-core/6.4.1/lucene-core-6.4.1.jar>`__

-  `lucene
   queryparser <http://central.maven.org/maven2/org/apache/lucene/lucene-queryparser/6.4.1/lucene-queryparser-6.4.1.jar>`__

-  `lucene common
   analyzers <http://central.maven.org/maven2/org/apache/lucene/lucene-analyzers-common/6.4.1/lucene-analyzers-common-6.4.1.jar>`__

2.Set file directory
--------------------

The ``.\postsXML`` directory is where you put such huge xml file
``Posts.xml``. To start the system, you can put the huge xml in the
``.\postsXML`` directory or edit the class ``utils.Paths`` in the code,
which means change the ``POSTSPATH`` to where the ``Posts.xml`` file
dataset exists in your computer.

3.Filter python related questions and answers
---------------------------------------------

After setting ``Posts.xml`` directory, all python related questions and
answers can be filtered by running ``FilterPythonMain.java`` in the
default package of ``scr/main/java``. When filtering is finished,
``python.xml`` and ``pythonanswer.xml`` will be created in
``.\filteredXML`` directory. It may take serval hours for the filtering
process.

4. Create index
---------------

Index is created with the two filtered xml file ``python.xml`` and
``pythonanswer.xml``. Run ``PostIndexMain.java`` in the default package
of ``scr/main/java``, then index will be created in
``.\index\postindex`` directory. It may takes 10 minutes to run create
the whole index.

5.Search
--------

After index is created, we can query on the field that has been indexed.
Run ``PostSearchMain`` class in the default package of "src/main/java" 
to search. In the search process, the firststep is to input the query, 
phrase query can be specified with double quotation marks. The picture 
below shows a example of a query.

	©°©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©´
	©¦ Input your query("" means phrase query):   ©¦
	©¦ python numpy ndarray                       ©¦
        ©¦                                            ©¦
        ©¸©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¼

Then specify the fields you want to query. The text below shows query
all fields and customized fields example output in console.

Input "0" to query on all fields.The console output will be.

	©°©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©´
	©¦ Input your query("" means phrase query):                                             ©¦
	©¦ python numpy ndarray                                                                 ©¦
	©¦ Select the field that you are going to query(0 means all, 1 means customized fields):©¦
	©¦ 0                                                                                    ©¦
        ©¦                                                                                      ©¦
        ©¸©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¼	

Input "1" for customizing fields, and then input the corresponding
number to choose fields.

	©°©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©´
	©¦ Input your query("" means phrase query):                                                                                 ©¦
	©¦ python numpy ndarray                                                                                                     ©¦
	©¦ Select the field that you are going to query(0 means all, 1 means customized fields):                                    ©¦
	©¦ 1                                                                                                                        ©¦
	©¦ Specify the fields you need to query, 1 means Body(Text), 2 means Title(question), 3 means Code, 4 means Tags(question). ©¦
	©¦ 134                                                                                                                      ©¦
	©¦ Input the result number Top N:                                                                                           ©¦
	©¦ 10                                                                                                                       ©¦
        ©¦                                                                                                                          ©¦
        ©¸©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¼


After query fields, user can determine top N results to be returned.
Here is a example of a certain N.

	©°©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©´
	©¦ Input your query("" means phrase query):                                             ©¦
	©¦ python numpy ndarray                                                                 ©¦
	©¦ Select the field that you are going to query(0 means all, 1 means customized fields):©¦
	©¦ 0                                                                                    ©¦
	©¦ Input the result number Top N:                                                       ©¦
	©¦ 10                                                                                   ©¦
        ©¦                                                                                      ©¦
        ©¸©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¼	

Then example results will be returned like below. Note: If you try
to set a large returned query results number, please unselect the
``Limit console output`` in ``Preference`` -> ``Run/Debug``->``Console``
.
	Example output:

	©°©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©´
	©¦  Question1 ID:9785514                                                                                    ©¦
	©¦  Question1 search Score:88.71509552001953                                                                ©¦
	©¦  Question1 Title:numpy ndarray hashability                                                               ©¦
	©¦  Question1 Body:I have some problems understanding how numpy objects hashability is managed....          ©¦
	©¦  Question1 Code:>>> import numpy as np >>> class Vector(np.ndarray): ...     pass >>> nparray = ...      ©¦
	©¦  Question1 Tags:python numpy                                                                             ©¦
	©¦     Answer1 ID:9786043                                                                                   ©¦
	©¦     Answer1 Score:8                                                                                      ©¦
	©¦     Answer1 Body:I get the same results in Python 2.6.6 and numpy 1.3.0. According to the Python ....    ©¦
	©¦     Answer1 Code:                                                                                        ©¦
	©¦     Answer2 ID:9803494                                                                                   ©¦
	©¦     Answer2 Score:2                                                                                      ©¦
	©¦     Answer2 Body:This is not a clear answer, but here is some track to follow to understand this ...     ©¦
	©¦     Answer2 Code:NPY_NO_EXPORT PyTypeObject PyArray_Type = { #if defined(NPY_PY3K)     PyVarObject_...   ©¦
	©¦  Question2 ID:21088133                                                                                   ©¦
	©¦  Question2 search Score:87.77174377441406                                                                ©¦
	©¦  Question2 Title:How to construct a ndarray from a numpy array? python                                   ©¦
	©¦  Question2 Body:I can't seem to convert it into an ndarray in numpy, i've read http://docs.scipy.org...  ©¦
	©¦  Question2 Code:[[1, 2, 4, 1, 5],  [6, 0, 0, 0, 2],  [0, 0, 0, 1, 0]] import numpy as np x = [[1, 2, ... ©¦
	©¦  Question2 Tags:python arrays numpy multidimensional-array                                               ©¦
	©¦    Answer1 ID:21088294 ....                                                                              ©¦
        ©¦                                                                                                          ©¦
        ©¸©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¼

6.Two IR Applications
---------------------

The IR applications can be found at ``exampleapp.app1`` and
``exampleapp.app2`` package, They can be initialized by running the
``Main.java`` separately. After running, index and results will be
created in ``.\exampleappindex\01`` and ``.\exampleappindex\02``
respectively.
