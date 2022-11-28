#  **Project Technikon**
## A group project for the Java Bootcamp by CodeHub

#### Description

This project implements an application for a Renovation Contractor Agency, Technicon. 
The types of users for the application are two: an **admin** and an **owner**, who is the customer of the platform.

The customer has the ability to register in the application with its credentials and to also register one or more of his propeties 
in the platform. Also the customer can apply via the platform for a repair in one or more of his/hers properties.
The customer can update  some of his personal info such as his email or password. Also after the request for a repair, 
the customer can accept or decline the offer that has been proposed by the admin/employee responsible for the 
the customer.

The admin of the application has the ability to propose a cost as well as an indicative start and end date of the repair 
process . Furthermore the admin can a receive an extentive report for all the registered repairs.



In order to work more efficienlty our team decided to divide the project in 6 packages.

**Enums Package**
In the enums package we grouped the classes that identified the types of the properties, the repairs and the statuses 
of the repairs.

**Exceptions Package**
In the exceptions package are included the classes that handle the exception messages that occur in our code. 

**Model Package**
In the model package are included the classes that specify the modeling of our project. (*PersistenClass* , *Owner*, 
*Property*, *Repair*)

**Repository Package**
In the repository package are specified the interfaces and the implementations that encapsulate the logic required 
to access our data sources.

**Services Package**
In the services package are implemented the services of the customer, the admin and the data manipulation.

**Util Package**
In the util package we implement the standard code for the JPA specification as well as the use cases of the project.


### How to Run the Project
The essential development tools required to run the project are:
JDK 17
Any IDE that supports Java(Apache NetBeans, Eclipse etc)
Maven

When the enviroment is set up, all you have to do is  run the Technikon.java, a file that include the main method and implements
the basic use cases of our project.More specifically by running the main, the following procedures will take place:
By the method **dataPopulation()** we popoulate our database with tha data given by the CSV files.
By the method **ownerRegistrationWithTwoProperties()** the customer directly register himself/hershelf into the 
application and two of his/her properties. The relevant fields are being filled in the database.
By the method **repairRegistration()** the owner register a repair for one of hiw properties in the application.
By the method **repairAdministration()** the admin can overview the pending repairs that are registered in the platform and by the 
method **reports()** the admin gets a full report of the repairs in all statuses.

