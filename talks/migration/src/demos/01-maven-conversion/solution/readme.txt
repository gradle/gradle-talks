mvn clean install
mvn jetty:run

gradle setupBuild
gradle build
//add jetty plugin to war project
gradle jettyRun