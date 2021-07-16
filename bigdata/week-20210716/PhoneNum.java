import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class PhoneNum {

  public static class TokenizerMapper
       extends Mapper<Object, Text, Text, IntWritable>{

	private final static int cnt_one_line = 10;
	private final static int phone_num_index = 2;
	private final static int up_stream_index = 8;
	private final static int down_stream_index = 9;
    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
      StringTokenizer itr = new StringTokenizer(value.toString());
	  int index = 1;
          int un = 0;
          int dn = 0;
	  String buf = new String();
      while (itr.hasMoreTokens()) {
	    if (index == phone_num_index) {
			buf += itr.nextToken();
			buf += "\t";
	    } else if (index == up_stream_index) {
			String token = itr.nextToken();
			un = Integer.parseInt(token);
			buf += token;
			buf += "\t";
	    } else if (index == down_stream_index) {
			String token = itr.nextToken();
			dn = Integer.parseInt(token);
			buf += token;
			buf += "\t";
	    } else {
		itr.nextToken();
	    }
	    index++;
	    if (index == cnt_one_line+1)  {
			Integer sum = un + dn;
			//buf += String.valueOf(sum);
			index = 1;
			word.set(buf);
			context.write(word, one);
			buf = "";
	    }
      }
    }
  }

  public static class IntSumReducer
       extends Reducer<Text,IntWritable,Text,IntWritable> {
    private IntWritable result = new IntWritable();

    public void reduce(Text key, Iterable<IntWritable> values,
                       Context context
                       ) throws IOException, InterruptedException {
/*
 */
      StringTokenizer itr = new StringTokenizer(key.toString());
          int index = 1;
          int un = 0;
          int dn = 0;
          int sum = 0;
      while (itr.hasMoreTokens()) {
            if (index == 2) {
                        String token = itr.nextToken();
                        un = Integer.parseInt(token);
            } else if (index == 3) {
                        String token = itr.nextToken();
                        dn = Integer.parseInt(token);
            } else {
                itr.nextToken();
            }
            index++;
            if (index == 3+1)  {
                        sum = un + dn;
                        index = 1;
            }
      }
      result.set(sum);

/*
      int sum = 0;
      for (IntWritable val : values) {
        sum += val.get();
      }
      result.set(sum);
*/
      context.write(key, result);
    }
  }

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "Dean MR Demo");
    job.setJarByClass(PhoneNum.class);
    job.setMapperClass(TokenizerMapper.class);
    job.setCombinerClass(IntSumReducer.class);
    job.setReducerClass(IntSumReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}

