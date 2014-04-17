source /etc/spark/conf/spark-env.sh

export JAVA_HOME=/usr/java/jdk1.7.0_45-cloudera

# system jars:
CLASSPATH=/etc/hadoop/conf
CLASSPATH=$CLASSPATH:$HADOOP_HOME/*:$HADOOP_HOME/lib/*
CLASSPATH=$CLASSPATH:$HADOOP_HOME/../hadoop-mapreduce/*:$HADOOP_HOME/../hadoop-mapreduce/lib/*
CLASSPATH=$CLASSPATH:$HADOOP_HOME/../hadoop-yarn/*:$HADOOP_HOME/../hadoop-yarn/lib/*
CLASSPATH=$CLASSPATH:$HADOOP_HOME/../hadoop-hdfs/*:$HADOOP_HOME/../hadoop-hdfs/lib/*
CLASSPATH=$CLASSPATH:$SPARK_HOME/assembly/lib/*

# app jar:
CLASSPATH=$CLASSPATH:crunch-demo-1.0-SNAPSHOT-jar-with-dependencies.jar

$JAVA_HOME/bin/java -cp $CLASSPATH com.example.WordCount $@

