package com.whiskeyfei.demo.custom;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class GsonRequest<T> extends Request<T> {
	private final Listener<T> mListener;
	private Gson mGson;
	private Class<T> mClass;
	private final Map<String, String> mHeaders;
	
	public GsonRequest(int method, String url, Class<T> clazz,Map<String, String> headers,
			Listener<T> listener, ErrorListener errorListener) {
		super(method, url, errorListener);
		mGson = new Gson();
		mClass = clazz;
		mHeaders = headers;
		mListener = listener;
	}
	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		return mHeaders != null ? mHeaders : super.getHeaders();
	}

	public GsonRequest(String url, Class<T> clazz, Listener<T> listener,
			ErrorListener errorListener) {
		this(Method.GET, url, clazz, null,listener, errorListener);
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		try {
			String jsonString = new String(response.data,HttpHeaderParser.parseCharset(response.headers));
			return Response.success(mGson.fromJson(jsonString, mClass),
					HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		} catch (JsonSyntaxException e) {
			return Response.error(new ParseError(e));
		}
	}

	@Override
	protected void deliverResponse(T response) {
		mListener.onResponse(response);
	}

}
