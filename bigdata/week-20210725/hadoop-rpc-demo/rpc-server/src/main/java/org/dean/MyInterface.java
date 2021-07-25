package org.dean;
import java.net.InetSocketAddress;
 
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
 
//import rpc.protocol.*;
//import org.apache.hadoop.ipc.VersionedProtocol; 
import org.apache.hadoop.ipc.*; 

public interface MyInterface extends VersionedProtocol {
        long versionID = 1L;
        int add(int number1, int number2);
	String findName(int studentId);
}

