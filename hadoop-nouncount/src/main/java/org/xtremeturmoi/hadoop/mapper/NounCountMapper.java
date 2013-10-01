package org.xtremeturmoi.hadoop.mapper;

import java.io.IOException;
import java.util.List;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.xtremeturmoil.hadoop.helper.Entity;
import org.xtremeturmoil.hadoop.helper.NounExtractor;

public class NounCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	private final NounExtractor extractor = new NounExtractor(NounExtractor.ENGLISH_MODEL);
	private final IntWritable ONE = new IntWritable(1);
	private Text word = new Text();

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		List<Entity> nouns = extractor.process(value.toString());
		for(Entity entity : nouns) {
			word.set(entity.getWord());
			context.write(word, ONE);
		}
	}

}
