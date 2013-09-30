package org.xtremeturmoi.hadoop;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.xtremeturmoi.hadoop.mapper.SkeletonMapper;
import org.xtremeturmoi.hadoop.reducer.SkeletonReducer;

public class SkeletonMain extends Configured implements Tool {

	public int run(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Path inputPath = new Path(args[0]);
		Path outputDir = new Path(args[1]);

		//Create Config
		Configuration conf = new Configuration(true);

		//Define Job
		Job job = Job.getInstance(conf, "Skeleton Job");
		job.setJarByClass(SkeletonMapper.class);

		//Setup Mappers/Reducers
		job.setMapperClass(SkeletonMapper.class);
		job.setReducerClass(SkeletonReducer.class);
		job.setNumReduceTasks(1);

		//Specify key / value
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		//Input Path
		FileInputFormat.addInputPath(job, inputPath);
		job.setInputFormatClass(TextInputFormat.class);

		//Output Path
		FileOutputFormat.setOutputPath(job, outputDir);
		job.setOutputFormatClass(TextOutputFormat.class);

		//Allow same output but deleting if already exists.
		FileSystem hdfs = FileSystem.get(conf);
		if (hdfs.exists(outputDir))
			hdfs.delete(outputDir, true);

		//Run the MapReduce Job.
		return job.waitForCompletion(true) ? 0 : -1;
	}

	public static void main(String[] args) throws Exception {
		System.exit(ToolRunner.run(new SkeletonMain(), args));
	}

}
