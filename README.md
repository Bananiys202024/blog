# blog

<!---#<h2>Overwiew</h2>-->
<!---#<h6><h6>-->

    
<h2>Screenshots</h2>
<kbd><img src="screenshots/1.png"></kbd>
<kbd><img src="screenshots/3.png"></kbd>
<kbd><img src="screenshots/2.png"></kbd>


<h2>Frameworks and tools</h2>

<h6>
    Java&IDE: JDK8 MyEclipse2018<br>
    Backend: Spring-boot, Spring, Spring-data, Spring Security, apache POI, Hibernate<br>
    Frontend: Bootstrap, thymeleaf<br>
    Database: cassandra<br>
    Web Server: Tomcat7<br>
    Build Tool: Maven<br>
    Linux： Arch Linux<br>
    Other: Log4j, junit, lombok<br>
</h6>

<h2>Start project with maven</h2>

Start cassandra database on arch linux
    
        sudo cassandra -R

Generate archive

        mvn install

Start server

        java -jar target/blog-0.0.1-SNAPSHOT.jar
        
Go to web site        
        
        http://localhost:8082/
        
port of url can be different. You need check server.port in file src/main/resources/application.properties


<h2>Tuning project...</h2>

<h6>
    <ol>
        <li>
            force update of snapshots updates
            
            mvn clean install -U
           
</li>

<li>Add libraries to build path. In Eclipse: right click by folder "library" and choose "Add to Build Path".
        </li>
        
<li> “The selection cannot be run on any server” you need go to project facets and mark version for Dynamic Web Module, on date 21.10.2020 version must be 3.0. may be you will been unmark Cloud Foundry Standalone in Project Facets and use tomcat7</li>        
     <li>If you can't start project from of weakly computer you can delete package "com.web.blog.Initializing" </li> 
        <li>If you can't start project then execute command "Drop keyspace blog;" in cassandra </li> 
        <li>If you can't start project then change schemaAction.RECREATE to another recreate RECREATE_.. in class CassandraConfiguration in package "com.web.blog.configuration" </li> 
          <li>If you want start project without mistakes then change schemaAction to "CREATE_IF_NOT_EXISTS" in class configurationCassandra and delete package Initizator </li> 
   </ol>
</h6>


<h2>Username, password and role for sign In</h2>

<h6>
    <ol>
        <li>Admin: 123 :admin</li>
        <li>User: 123 :user</li>
        <li>Banan: 1234 :user</li>
        <li>nonBanan: 12345 :user</li>
   </ol>
</h6>


<h2>How use cassandra</h2>

Start cassandra db on arch linux

    systemctl start cassandra

Open cassandra in terminal
            
    cqlsh
    
Show all exist databases

    DESCRIBE keyspaces;
    
Use database "blog"
    
    use blog
  
Show all tables
    
    DESCRIBE tables;
    
Select all from table "jokes"
    
    SELECT * FROm jokes;

Drop table
    
    DROP TABLE jokes;
    
Drop database

    drop keyspace blog;
    
To clear table "goals"

    TRUNCATE goals;


<h2> Address already in use on arch linux  </h2>

Looking for port

    sudo netstat -tulpn | grep :9042

Looking for port

    sudo lsof -i :9042

Kill port

    sudo kill -9 11778
    
Where is 11778 is PID of process    

<h2> Get Ip of laptop in arch linux</h2>

1 way

    ip addr show wlp2s0

2 way

    ip addr show

3 way

    ip route list 

