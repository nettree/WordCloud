# WordCloud
A single page web application with Spring Boot, AngularJS, Bootstrap in order to generate word cloud by using given URL

# Required runtime environment
Before running application, please install JDK 1.8+ and Maven 3.x first to ensure the correctness of running

# How to run this application
1. If you already get the wordcloud-0.0.1-SNAPSHOT.jar, just run the command: 
   'java - jar wordcloud-0.0.1-SNAPSHOT.jar', 
   then go to http://localhost:8080 to see the result.
2. If you want to compile the whole application from scratch, you first need to get all the source code, then run the command: 
   'mvn package' 
   under the root folder of project, then you can find the wordcloud-0.0.1-SNAPSHOT.jar in target folder and run it by using command: 
   'java - jar wordcloud-0.0.1-SNAPSHOT.jar', 
   then go to http://localhost:8080 to see the result.
3. If you want to run this application via an IDE, you could use Eclipse to import the existing Maven Project, then right-click the imported project, follow the Run As --> Maven build, input 'spring-boot:run' in Goals field and Run, then go to http://localhost:8080 to see the result.

# How this application works
At http://localhost:8080, you could see a blank page with some labels, a text field, and a button named as 'Generate Word Cloud', if you want to retrieve web page content directly from https://www.parashift.com.au/parablog/, just click this button, then wait for a second, the result will be shown. Or if you want to try another URL, just paste it into the text field, click button again, the result will be updated according to the new content retrieved from this URL.
