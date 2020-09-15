#Gist Updater

Application which runs a cron job every hour to pull a gist off Github.

##### Stack
[X]   Java
[X]   Maven
###### Demonstrations:
>   1.  How to HTTPRequest in Java
>   2.  How to run a cron job in Java 


#### QUICK START

Step 1. Set the details in `config.yaml` under `resources\config` directory.
        You need to enter gist link and destination of file.

```
---
gitLink: #enter the gist link <CANNOT BE NULL>
destinationPath:  #enter the path of the file into which the gist is to written <CANNOT BE NULL>\
```

I will demonstrate an example :
```
---
gitLink: https://gist.github.com/aditya109/d48d70dd67c2065521c7952ed5c5209f
destinationPath: D:\Projects\project_ideas.txt
```

Step 2 : Type the following command:
```
mvn clean install package

java -jar target/document-pull-using-cron-quartz-1.0-SNAPSHOT.jar
```