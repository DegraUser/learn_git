#!/bin/bash
#java -Dport=$1 -DproxyUri=$2 -cp "/home/test/.m2/repository/io/netty/netty-all/4.1.51.Final/netty-all-4.1.51.Final.jar:target/netty-demo-1.0-SNAPSHOT.jar:/home/test/.m2/repository/org/apache/httpcomponents/httpclient-cache/4.1.2/httpclient-cache-4.1.2.jar:/home/test/.m2/repository/org/apache/httpcomponents/httpclient/4.5.12/httpclient-4.5.12.jar:/home/test/.m2/repository/org/apache/httpcomponents/httpcore/4.4.13/httpcore-4.4.13.jar:/home/test/.m2/repository/commons-logging/commons-logging/1.2/commons-logging-1.2.jar" org.dean.NettyHttpServer
#java -Dport=$1 -DproxyUri=http://127.0.0.1:1234 -cp "/home/test/.m2/repository/io/netty/netty-all/4.1.51.Final/netty-all-4.1.51.Final.jar:target/netty-demo-1.0-SNAPSHOT.jar:/home/test/.m2/repository/org/apache/httpcomponents/httpclient-cache/4.1.2/httpclient-cache-4.1.2.jar:/home/test/.m2/repository/org/apache/httpcomponents/httpclient/4.5.12/httpclient-4.5.12.jar:/home/test/.m2/repository/org/apache/httpcomponents/httpcore/4.4.13/httpcore-4.4.13.jar:/home/test/.m2/repository/commons-logging/commons-logging/1.2/commons-logging-1.2.jar" org.dean.NettyHttpServer
java  -cp "/home/test/.m2/repository/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar:/home/test/.m2/repository/org/springframework/spring-context/5.2.3.RELEASE/spring-context-5.2.3.RELEASE.jar:/home/test/.m2/repository/org/springframework/spring-aop/5.2.3.RELEASE/spring-aop-5.2.3.RELEASE.jar:/home/test/.m2/repository/org/springframework/spring-beans/5.2.3.RELEASE/spring-beans-5.2.3.RELEASE.jar:/home/test/.m2/repository/org/springframework/spring-core/5.2.3.RELEASE/spring-core-5.2.3.RELEASE.jar:/home/test/.m2/repository/org/springframework/spring-jcl/5.2.3.RELEASE/spring-jcl-5.2.3.RELEASE.jar:/home/test/.m2/repository/org/springframework/spring-expression/5.2.3.RELEASE/spring-expression-5.2.3.RELEASE.jar:.:target/spring-frame-demo-1.0-SNAPSHOT.jar" org.dean.App
