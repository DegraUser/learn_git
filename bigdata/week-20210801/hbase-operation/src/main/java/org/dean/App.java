package org.dean;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.io.compress.Compression;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.net.URISyntaxException;

public class App {
    public static void main(String[] args) throws URISyntaxException {
        // 加载HBase的配置
        Configuration configuration = HBaseConfiguration.create();
	configuration.set("hbase.zookeeper.quorum", "jikehadoop01,jikehadoop02,jikehadoop03");

/*
        // 读取配置文件
        configuration.addResource(new Path(ClassLoader.getSystemResource("hbase-site.xml").toURI()));
        configuration.addResource(new Path(ClassLoader.getSystemResource("core-site.xml").toURI()));
*/

        try (// 创建一个HBase连接
             Connection connection = ConnectionFactory.createConnection(configuration);
             // 获得执行操作的管理接口
             Admin admin = connection.getAdmin();) {

            // 新建一个表名为mytable的表
            TableName tableName = TableName.valueOf("songwanquan_student");
            HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);

            // 新建一个列族名为mycf的列族
			HColumnDescriptor infocf = new HColumnDescriptor("info");
			HColumnDescriptor scorecf = new HColumnDescriptor("score");
			
			
            // 将列族添加到表中
			tableDescriptor.addFamily(infocf);
            tableDescriptor.addFamily(scorecf);
            // 执行建表操作
            createOrOverwrite(admin, tableDescriptor);

            // 获取表对象
            Table table = connection.getTable(tableName);

            // 创建一个put请求，用于添加数据或者更新数据
            Put tomput = new Put(Bytes.toBytes("Tom"));
            tomput.addColumn(Bytes.toBytes("info"), Bytes.toBytes("student_id"), Bytes.toBytes("20210000000001"));
			tomput.addColumn(Bytes.toBytes("info"), Bytes.toBytes("class"), Bytes.toBytes("1"));
			tomput.addColumn(Bytes.toBytes("score"), Bytes.toBytes("understanding"), Bytes.toBytes("75"));
			tomput.addColumn(Bytes.toBytes("score"), Bytes.toBytes("programming"), Bytes.toBytes("82"));
            table.put(tomput);
			
			Put jerryput = new Put(Bytes.toBytes("Jerry"));
            jerryput.addColumn(Bytes.toBytes("info"), Bytes.toBytes("student_id"), Bytes.toBytes("20210000000002"));
			jerryput.addColumn(Bytes.toBytes("info"), Bytes.toBytes("class"), Bytes.toBytes("1"));
			jerryput.addColumn(Bytes.toBytes("score"), Bytes.toBytes("understanding"), Bytes.toBytes("85"));
			jerryput.addColumn(Bytes.toBytes("score"), Bytes.toBytes("programming"), Bytes.toBytes("67"));
            table.put(jerryput);
			
			Put jackput = new Put(Bytes.toBytes("Jack"));
            jackput.addColumn(Bytes.toBytes("info"), Bytes.toBytes("student_id"), Bytes.toBytes("20210000000003"));
			jackput.addColumn(Bytes.toBytes("info"), Bytes.toBytes("class"), Bytes.toBytes("1"));
			jackput.addColumn(Bytes.toBytes("score"), Bytes.toBytes("understanding"), Bytes.toBytes("80"));
			jackput.addColumn(Bytes.toBytes("score"), Bytes.toBytes("programming"), Bytes.toBytes("80"));
            table.put(jackput);
			
			Put roseput = new Put(Bytes.toBytes("Rose"));
            roseput.addColumn(Bytes.toBytes("info"), Bytes.toBytes("student_id"), Bytes.toBytes("20210000000004"));
			roseput.addColumn(Bytes.toBytes("info"), Bytes.toBytes("class"), Bytes.toBytes("1"));
			roseput.addColumn(Bytes.toBytes("score"), Bytes.toBytes("understanding"), Bytes.toBytes("60"));
			roseput.addColumn(Bytes.toBytes("score"), Bytes.toBytes("programming"), Bytes.toBytes("61"));
            table.put(roseput);
			
			
			


            // 创建一个查询请求，查询一行数据
            Get get = new Get(Bytes.toBytes("Tom"));
            // 由于HBase的一行可能非常大，所以限定要取出的列族
            get.addFamily(Bytes.toBytes("info"));
			get.addFamily(Bytes.toBytes("score"));
            // 创建一个结果请求
            Result result = table.get(get);
            // 从查询结果中取出name列，然后打印（这里默认取最新版本的值，如果要取其他版本要使用Cell对象）
            byte[] student_id_t = result.getValue(Bytes.toBytes("info"), Bytes.toBytes("student_id"));
	    byte[] class_t = result.getValue(Bytes.toBytes("info"), Bytes.toBytes("class"));
	    byte[] understanding_t = result.getValue(Bytes.toBytes("score"), Bytes.toBytes("understanding"));
	    byte[] programming_t = result.getValue(Bytes.toBytes("score"), Bytes.toBytes("programming"));
            System.out.println("name: " + Bytes.toString(student_id_t) + " class: " + Bytes.toString(class_t)
				+ " understanding: "+ Bytes.toString(understanding_t) + " programming: " + Bytes.toString(programming_t));
/*
            System.out.println(Bytes.toString(class_t));
            System.out.println(Bytes.toString(understanding_t));
            System.out.println(Bytes.toString(programming_t));
*/
            if (table != null) table.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ok");
    }

    public static void createOrOverwrite(Admin admin, HTableDescriptor table) throws IOException {
        // 获取table名
        TableName tableName = table.getTableName();
        // 判断table是否存在，如果存在则先停用并删除
        if (admin.tableExists(tableName)) {
            // 停用表
            admin.disableTable(tableName);
            // 删除表
            admin.deleteTable(tableName);
        }
        // 创建表
        admin.createTable(table);
    }
}


