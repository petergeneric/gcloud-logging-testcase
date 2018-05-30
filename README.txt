Put credentials json into "credentials.json" and then run:

env GOOGLE_APPLICATION_CREDENTIALS="credentials.json" /opt/maven-3.2.1/bin/mvn clean test

This should work.

Next, to have the credentials be loaded using builder.setCredentials(ServiceAccountCredentials.fromStream use:

env GOOGLE_APPLICATION_CREDENTIALS_PATH="credentials.json" /opt/maven-3.2.1/bin/mvn clean test

This should fail. This fails on my machine and a clean build container I spun up (ubuntu 16.04, java version "1.8.0_152", maven 3.2.1)

