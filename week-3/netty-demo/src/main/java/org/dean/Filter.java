package org.dean;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
class Filter {
	public static void filt(FullHttpResponse response) {
		        response.headers().set("geek", "java-2-filter");
	}
}
