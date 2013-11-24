Library
=======

The EER Diagram of this project is as shown in word file EERDiagram.doc.

1. Based on it I have generated library.sql that will create a database library and will insert dummy data into the database.
open mysql in your terminal and write the below command.

mysql > source /path/to/library.sql;


2 . Incase if you want to know how I generated library.sql just populate database from csv file by importing createDB.sql in mysql.

mysql > source /path/to/createDB.sql

Any one of the below will populate the database.
After populating you can generate you .csv file by writing the command in terminal or command prompt

deepshah$ mysqldump -u [username] -p [password] [database name] > library.sql