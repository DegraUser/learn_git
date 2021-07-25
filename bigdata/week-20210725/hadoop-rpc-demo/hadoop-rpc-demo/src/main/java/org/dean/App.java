package org.dean;

import java.net.InetSocketAddress;
 
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
 
//import rpc.protocol.*;
import org.dean.MyInterface;

 
public class App {
 
	public static void main(String[] args) throws Exception {
 
		MyInterface proxy = RPC.getProxy(MyInterface.class, 1L, 
			new InetSocketAddress("localhost", 8888), 
			new Configuration());
		//String metaData = proxy.getMetaData("/hellohellohelloxxxxxxx.xxx");
		int res = proxy.add(1, 2);
		String name = proxy.findName(1);
		System.out.println(res);
	}
 
}


/**
 * Hello world!
 *
 */
/*
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
*/
