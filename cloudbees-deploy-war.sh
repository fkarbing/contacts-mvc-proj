#!/usr/bin/bash

# deploy
bees app:deploy -a fkarbing/contacts-mvc -m "contacts-mvc" ./contacts-spring-mvc/target/spring-1.0.0-BUILD-SNAPSHOT.war

# info
bees app:info -a fkarbing/contacts-mvc

# tail
bees app:tail -a fkarbing/contacts-mvc server
# LOGNAME           server, access or error

# list
bees app:list

# delete
# bees app:delete -a fkarbing/contacts-mvc -f


# update
bees app:update -a fkarbing/contacts-mvc containerType=play2

# scale

# resource list
bees app:resource:list -a fkarbing/contacts-mvc

# args:

-t,--type <container_type>  Deployment container type (tomcat | jboss)  Default: tomcat (you can change this later via app:update)
-a,--appid <appId>          application ID (of the form accountName/applicationName)
-e,--environment <env>      environment configurations to deploy
-m,--message <message>      message describing the deployment
-P <arg>                    Config application parameter name=value
-s,--secret <API secret>    Your API secret
-k,--key <API key>          Your API key
