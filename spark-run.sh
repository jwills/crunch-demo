# system jars:

CLASSPATH=$CLASSPATH:$HADOOP_HOME/*:$HADOOP_HOME/lib/*
CLASSPATH=$CLASSPATH:$HADOOP_HOME/../hadoop-mapreduce/*:$HADOOP_HOME/../hadoop-mapreduce/lib/*
CLASSPATH=$CLASSPATH:$HADOOP_HOME/../hadoop-yarn/*:$HADOOP_HOME/../hadoop-yarn/lib/*
CLASSPATH=$CLASSPATH:$HADOOP_HOME/../hadoop-hdfs/*:$HADOOP_HOME/../hadoop-hdfs/lib/*

# app jar:
CLASSPATH=$CLASSPATH:target/crunch-demo-1.0-SNAPSHOT-jar-with-dependencies.jar

$JAVA_HOME/bin/java -cp $CLASSPATH com.example.WordCount $@

