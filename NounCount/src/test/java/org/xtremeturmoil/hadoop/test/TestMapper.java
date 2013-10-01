package org.xtremeturmoil.hadoop.test;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.xtremeturmoi.hadoop.mapper.NounCountMapper;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;

public class TestMapper {

	
	private MapDriver<LongWritable, Text, Text, IntWritable> driver;

	@Before
	public void setUp() {
		driver = MapDriver.newMapDriver(new NounCountMapper());
	}
	
	@Test
	public void testMapper() throws IOException {
		
		// This is what the mapper will take as input.
		driver.withInput(new LongWritable(), new Text("I like shooting guns"));
		driver.withInput(new LongWritable(), new Text("There was a man named bob"));
		
		// This is what the mapper should output.
		driver.withOutput(new Text("guns"), new IntWritable(1));
		driver.withOutput(new Text("man"), new IntWritable(1));
		driver.withOutput(new Text("bob"), new IntWritable(1));
		
		// Run the test.
		driver.runTest();
	}
}
