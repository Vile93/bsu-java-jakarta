FROM tomcat:10.0-jdk17

COPY ./target/bsu-1.0-SNAPSHOT.war /usr/local/tomcat/webapps

VOLUME /usr/local/tomcat/logs

CMD ["catalina.sh", "run"]