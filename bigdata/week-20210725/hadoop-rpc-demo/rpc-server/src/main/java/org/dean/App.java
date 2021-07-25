package org.dean;

import java.net.InetSocketAddress;
 
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
 
//import rpc.protocol.*;
import org.dean.MyInterface;
import org.dean.MyInterfaceImpl;




import java.io.IOException; 
import org.apache.hadoop.conf.Configuration; 
import org.apache.hadoop.io.Text; 
import org.apache.hadoop.ipc.ProtocolSignature; 
import org.apache.hadoop.ipc.RPC; 
import org.apache.hadoop.ipc.RPC.Server; 
 
public class App {
 
	public static void main(String[] args) throws Exception {
        //server = RPC.getServer(this,"localhost",8888,new Configuration()); 
        //相对于以前的版本有略微的改动 
        RPC.Builder builder = new RPC.Builder(new Configuration()); 
        builder.setBindAddress("localhost"); 
        builder.setPort(8888); 
        builder.setProtocol(MyInterface.class); 
        builder.setInstance(new MyInterfaceImpl()); 
        //RPC.setProtocolEngine(new Configuration(), MyRPCProtocal.class, RpcEngine.class); 
        RPC.Server server = builder.build();//获得一个server实例 
        server.start(); 
    } 
}

/*
 
public class App {
 
	public static void main(String[] args) throws Exception {
 
		MyInterface proxy = RPC.getProxy(MyInterface.class, 1L, 
			new InetSocketAddress("localhost", 8888), 
			new Configuration());
		//String metaData = proxy.getMetaData("/hellohellohelloxxxxxxx.xxx");
		int res = proxy.add(1, 2);
		System.out.println(res);
	}
 
}
*/

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
