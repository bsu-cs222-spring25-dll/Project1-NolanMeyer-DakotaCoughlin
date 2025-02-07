# Wikepedia Revision Parser
For this assigment we were tasked with creating an application that takes a Wikipedia title from the command line, and returns the twenty one most recent revisions in reverse chronological order. It will also tell you if any redirects were made. If there is no Wikipedia article found it will give an error to the user, if no redirects were made it will simple just print out the revisions, if there is a network error it will also tell that to the user. 

<img width="566" alt="Screenshot 2025-02-07 at 8 55 05 AM" src="https://github.com/user-attachments/assets/6a8437d5-295e-4431-a941-849fc17fd7a7" />
<img width="532" alt="Screenshot 2025-02-07 at 8 59 57 AM" src="https://github.com/user-attachments/assets/9f93346c-7874-4cab-b631-97410e483ef0" />
<img width="681" alt="Screenshot 2025-02-07 at 8 55 30 AM" src="https://github.com/user-attachments/assets/d41f3a71-6cdc-496f-9ee1-d3f2d07ee14f" />



## Revision class
This class although simple, is a fundamental building block for the entire project. This contains two fields, a field for a username, and a field for a timestamp. This way we can further encapsulation and organization of our data.

## RevisionInputStream class
This is another class that is very helpful. When using input streams once you use them they will close. Since, we will need to do parse the data more than once we will store the original input stream as a byte array. That way when we need to use it again we can easily look at the data and parse it again! 

### openStream method
This method allows us to easily open a ByteArrayInputStream. This allows to have a lot of flexibility to open an input stream multiple times so we can parse the data more than once. 

## RevisionParser class
The RevisionParser class is the class responsible for doing all the parsing related to the revisions. It has multiple methods to make this happen. In order to instantiate this object you must pass it an instance of the RevisionInputStream class which is another class that we made for this project. 

### Parse Method
This method doesn't take any parameters. It will call another method called the extractRevisions method that will return a JSONArray of what we need. It will then call the convertRevisionsToList method that will return a list of objects of the Revision class, and return that value. 

### extractRevisions method
This method takes an input stream type. It will then read then extra the revisions from the input stream and return them as JSONArray.

### convertRevisionsToList
This method will take a JSONArray. It will then loop through every item in that JSONArray and convert to a LinkedHashMap. After that it will extract the username and the timestamp and add it to a Revision object. It will then add that object to a list that will be returned. It does this process for every item in the JSONArray.

### extractRedirect method
This method takes in an input stream. It will then parse out the part that will tell us if the request was redirected or not. If there was no redirect found in the parse it will return an empty string. If there was a redirect found it will return the string such as "Redirected to {redirect found}"

### RevisionFormatter Class
This class is responsible for formatting all of our output. Each revision that needed to be printed needed to follow this exact format: "Line:  timestamp  username".

### formatOutput method
This method will take a Revision object. It will then extract the revision timeStamp and the userName and return a string. The string will be the timestamp followed by two spaces, then the user name per the guidelines. 

### printRevionList method
This method will take the list of revisions. It will loop through each revision and call the formatOutput method and combine that with the line number to get the desired output. 

## WikiConnection class
This class is responsible for actually pulling all of our data from Wikipedia. 

### createRequestUrl method
The method takes in a string. It will then use the URLEncoder class to encode the string. After the string is encoded it will add that title to the URL string and return it. 

### getInputStream
This method will take in a URL string as a paraemter. It will then use the URL class to create a connection. The last thing this method will do is return the input stream of the request. 

## Menu class
This is the class that will control the running of the menu, and when all the other classes are called. It has four fields. An instance of the WikiConnection class, RevisionParser class, RevisionFormatterClass, and lastly the Scanner class. 

### runMenu
Asks the user for a title. It will then grab the title and send it to the inputSearch method.

### inputSearch method
This will call the WikiConnection search method with will return us our input stream. It will then create an instance of the RevisionParser class and give it an instance of the RevisionInputStream class. The parameter of the RevisionInputStream constructor will be the input stream generate from the WikiConnection search method call. After that it will call the parse method and return the list of revisions. It will then call call the parser extraRedict method trying to extract a redirect if there is one. The last method call will be the RevisionFormatter printRevisions method where it will print out all the revisions found. 

## Menu class
This class is a very simple class that creates an instance of the menu class and calls it's runMenu method.

## Authors
Nolan Meyer
Dakota Coughlin

