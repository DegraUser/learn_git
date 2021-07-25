package org.dean;
import org.dean.MyInterface;
import org.apache.hadoop.ipc.ProtocolSignature; 

public class MyInterfaceImpl implements MyInterface {
        @Override
        public int add(int number1, int number2) {
                System.out.println("number1 = " + number1 + " number2 = " + number2);
                return number1 + number2;
        }
        @Override
        public String findName(int studentId) {
                System.out.println("studentId = " + studentId);
                return "GeekClass";
        }
        @Override
        public long getProtocolVersion(String protocol, long clientVersion) {
                return MyInterface.versionID;
        }
        @Override
	public ProtocolSignature getProtocolSignature(String protocol, long clientVersion,  
	    int hashcode) {  
      	return new ProtocolSignature(this.versionID, null);  
    	}  
}
