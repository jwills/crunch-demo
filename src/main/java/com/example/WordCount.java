package com.example;

import org.apache.crunch.PCollection;
import org.apache.crunch.PTable;
import org.apache.crunch.Pipeline;
import org.apache.crunch.PipelineResult;
import org.apache.crunch.impl.spark.SparkPipeline;
import org.apache.crunch.types.writable.Writables;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import org.apache.spark.api.java.*;
import org.apache.spark.api.java.function.*;
import org.apache.spark.SparkConf;

/**
 * A word count example for Apache Crunch, based on Crunch's example projects.
 */
public class WordCount extends Configured implements Tool {

    public static void main(String[] args) throws Exception {
        ToolRunner.run(new Configuration(), new WordCount(), args);
    }

    public int run(String[] args) throws Exception {

        if (args.length != 2) {
            System.err.println("Usage: hadoop jar crunch-demo-1.0-SNAPSHOT-job.jar"
                                      + " [generic options] input output");
            System.err.println();
            GenericOptionsParser.printGenericCommandUsage(System.err);
            return 1;
        }

        String inputPath = args[0];
        String outputPath = args[1];

        // Create an object to coordinate pipeline creation and execution.
        String master = getConf().get("spark.master", "local");
        System.out.println("Connecting to spark at = " + master);
        Pipeline pipeline = new SparkPipeline(master, "sparktest", WordCount.class);

        // Reference a given text file as a collection of Strings.
        PCollection<String> lines = pipeline.readTextFile(inputPath);

        // Define a function that splits each line in a PCollection of Strings into
        // a PCollection made up of the individual words in the file.
        // The second argument sets the serialization format.
        PCollection<String> words = lines.parallelDo(new Tokenizer(), Writables.strings());

        // Take the collection of words and remove known stop words.
        PCollection<String> noStopWords = words.filter(new StopWordFilter());

        // The count method applies a series of Crunch primitives and returns
        // a map of the unique words in the input PCollection to their counts.
        PTable<String, Long> counts = noStopWords.count();

        // Instruct the pipeline to write the resulting counts to a text file.
        pipeline.writeTextFile(counts, outputPath);

        // Execute the pipeline as a MapReduce.
        PipelineResult res = pipeline.done();
        System.out.println("Stopword count = " + 
            res.getStageResults().get(0).getCounterValue("stopwords", "count"));
        return res.succeeded() ? 0 : 1;
    }
}
