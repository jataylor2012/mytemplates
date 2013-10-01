package org.xtremeturmoil.hadoop.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;
import org.xtremeturmoi.hadoop.reducer.NounCountReducer;

public class TestReducer {

	private ReduceDriver<Text, IntWritable, Text, IntWritable> driver;

	@Before
	public void setUp() {
		driver = ReduceDriver.newReduceDriver(new NounCountReducer());
	}

	@Test
	public void testReducer() throws IOException {
		List<IntWritable> values = new ArrayList<IntWritable>();
		values.add(new IntWritable(1));
		values.add(new IntWritable(2));
		driver.withInput(new Text("guns"), values);
		driver.withInput(new Text("man"), values);
		driver.withInput(new Text("bob"), values);

		driver.withOutput(new Text("guns"), new IntWritable(3));
		driver.withOutput(new Text("man"), new IntWritable(3));
		driver.withOutput(new Text("bob"), new IntWritable(3));

		driver.runTest();
	}
}
