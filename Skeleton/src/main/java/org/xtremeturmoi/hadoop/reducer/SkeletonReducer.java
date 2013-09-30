package org.xtremeturmoi.hadoop.reducer;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
 
public class SkeletonReducer extends
        Reducer<Text, Iterable<IntWritable>, Text, IntWritable> {
 
    public void reduce(Text text, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {
        int count = 0;
        for (IntWritable value : values) {
        	count += value.get();
        }
        context.write(text, new IntWritable(count));
    }
}
