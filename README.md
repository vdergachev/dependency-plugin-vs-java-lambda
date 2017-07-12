# dependency-plugin-vs-java-lambda
Here is simple spring boot project that reproduced bug in dependency plugin
It contains only one rest endpoint, so you can check it and it works fine
So, run mvn clean install and you will get next message 

[INFO] --- maven-dependency-plugin:3.0.1:analyze-only (analyze-dependencies) @ submodule ---<br />
[WARNING] Unused declared dependencies found:<br />
[WARNING]    org.eclipse.jetty:jetty-server:jar:9.2.14.v20151106:compile<br />

In this line https://github.com/vdergachev/dependency-plugin-vs-java-lambda/blob/master/submodule/src/main/java/com/abc/def/Application.java#L36 we have a implicit reference to Server object, but dependency plugin doenst resolve it and as a result totaly ignored jetty-server.jar 

Below in the code you can find commented block, so you can uncomment it, and commen previous, add all required imports and launch mvn clean install again. Now there is no warnings or errors, because in line 52 https://github.com/vdergachev/dependency-plugin-vs-java-lambda/blob/master/submodule/src/main/java/com/abc/def/Application.java#L52 we have direct reference to Server object and plugin agree with jetty-server.jar in dependencies.
