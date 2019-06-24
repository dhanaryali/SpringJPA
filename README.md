 Spring provides CrudRepository implementation class automatically at runtime. It contains methods such as save, findById, delete, count etc. If we want to add extra methods, we need to declare it in our interface. All the methods of CrudRepository are annotated with @Transactional in implementation class by default at runtime.
 
 The query builder mechanism built into Spring Data repository infrastructure is useful for building constraining queries over entities of the repository. The mechanism strips the prefixes find…By, read…By, and get…By from the method and starts parsing the rest of it. The introducing clause can contain further expressions such as a Distinct to set a distinct flag on the query to be created. However, the first By acts as delimiter to indicate the start of the actual criteria. At a very basic level you can define conditions on entity properties and concatenate them with And and Or.
 
 We have few examples of methods for Query Creation as mentioned above.
 
 <b>Note:</b> To see the output after running this application, we need hsql server should be run parallelly.
