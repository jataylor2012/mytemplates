package org.xtremeturmoil.hadoop.test;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import org.xtremeturmoi.hadoop.mapper.SkeletonMapper;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;

public class TestMapper {

	
	private MapDriver<LongWritable, Text, Text, IntWritable> driver;

	@Before
	public void setUp() {
		driver = MapDriver.newMapDriver(new SkeletonMapper());
	}
	
	@Test
	public void testMapper() throws IOException {
		
		// This is what the mapper will take as input.
		driver.withInput(new LongWritable(), new Text("This"));
		driver.withInput(new LongWritable(), new Text("is"));
		driver.withInput(new LongWritable(), new Text("awesome!"));
		
		// This is what the mapper should output.
		driver.withOutput(new Text("this"), new IntWritable(1));
		driver.withOutput(new Text("is"), new IntWritable(1));
		driver.withOutput(new Text("awesome!"), new IntWritable(1));
		
		// Run the test.
		driver.runTest();
	}
}
