
### contacts-spring-mvc

## Related doc:
        doc/tech-spec.txt

## Since the application is made up of 2 parts:

    - The spring-mvc project (this)
    - The LINKED IN javascriptMVC project (../contacts-jmvc)
    
    the application can be run in 3 different modes:
    
        # Local jmvc:
        
            - ${basedir}/contacts-jmvc/contacts/crm.html
            
        # Local app (DEV):
        
            - Services is accessed as normal
            
                http://localhost:7778/contacts-spring-mvc/
                
            - jmvc stuff as:
            
                http://localhost:7778/contacts-spring-mvc/resources/jmvc/contacts/contacts/contacts.html
                
        # Deployed app (PROD):
        
            - jmvc stuff as (due to the copy):
            
                http://localhost:7778/contacts-spring-mvc/resources/contacts.html
                
          
          