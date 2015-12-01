package com.whiskeyfei.demo.custom;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class FastJsonRequest<T> extends Request<T> {
	private final Listener<T> mListener;
	private Class<T> mClass;
	private final Map<String, String> mHeaders;
	 
	public FastJsonRequest(String url, Class<T> clazz, Listener<T> listener,
			ErrorListener errorListener) {
		this(Method.GET, url, clazz, null,listener, errorListener);
	}
	
	public FastJsonRequest(int method, String url, Class<T> clazz,Map<String, String> headers,
			Listener<T> listener, ErrorListener errorListener) {
		super(method, url, errorListener);
		mClass = clazz;
		mListener = listener;
		mHeaders = headers;
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		 return mHeaders != null ? mHeaders : super.getHeaders();
	}
	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		try {
			String jsonString = new String(response.data,
					HttpHeaderParser.parseCharset(response.headers));
			return Response.success(JSON.parseObject(jsonString, mClass),
					HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		}
	}

	@Override
	protected void deliverResponse(T response) {
		mListener.onResponse(response);
	}

}
