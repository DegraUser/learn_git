import java.io.*;
public class HelloClassLoader extends ClassLoader{
public static void main(String[] args) {
	if (args.length <= 0) {
		System.out.println("useage: xclasspath");
		return;
	}
	for(int i=0; i<args.length; i++) {
		System.out.println(args[i]);
	}
	try {
		HelloClassLoader cl = new HelloClassLoader();
		cl.setFileName(args[0]);
	if (args.length >= 2) {
		cl.setEncodeByteLoader();
	}
	cl.findClass("Hello").newInstance();
//	System.out.println(bytes);
	}/* catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} */catch (InstantiationException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		e.printStackTrace();
	}

}
private String fileName;
private boolean isByteLoader = true;
private void setFileName(String name) {
	fileName = name;
}
private void setEncodeByteLoader() {
	isByteLoader = false;
}
private void byteCodeLoader() {

}
@Override
protected Class<?> findClass(String className) throws ClassNotFoundException {
	try {
		if (isByteLoader) {
			byte[] bytes = getByteCode(fileName);
			System.out.println("ByteClassLoader");
			return defineClass(className, bytes, 0, bytes.length);
		} else {
			byte[] bytes = getByteCode(fileName);
			byte[] encodeByte = new byte[bytes.length];
			for(int i=0; i<bytes.length; i++) {
				encodeByte[i] = (byte)((byte)255 - bytes[i]);
			}
			System.out.println("EncodeClassLoader");
			return defineClass(className, encodeByte, 0, encodeByte.length);
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
	return null;
}
private byte[] getByteCode(String xclassFilePath) throws IOException, FileNotFoundException {
	try {
		File file = new File(xclassFilePath);
		long fileSize = file.length();
		if (fileSize > Integer.MAX_VALUE) {
			System.out.println("file too large: size " + fileSize);
			return null;
		}
		FileInputStream fi = new FileInputStream(file);
		byte[] buffer = new byte[(int) fileSize];
		int offset = 0;
		int readNum = 0;
		while(offset<buffer.length && readNum >= 0) {
			readNum = fi.read(buffer, offset, buffer.length-offset);
			offset += readNum;
			System.out.println("file too large: TEST " + readNum);
		}
		System.out.println("read length: " + offset);
		if (offset != buffer.length) {
			throw new IOException("read uncompleted " + file.getName());
		}
		fi.close();	
		return buffer;
	} catch (FileNotFoundException e) {
		throw e;
	} catch (IOException e) {
		throw e;
	}
}
}
