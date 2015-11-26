package whiskeyfei.android.com.android_data_interface.entity;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import whiskeyfei.android.com.android_data_interface.utils.ArrayUtils;
import whiskeyfei.android.com.android_data_interface.utils.HttpClientUtils;
import whiskeyfei.android.com.android_data_interface.utils.StringUtils;


public class HttpHelper {
	private static HttpHelper mInstance = new HttpHelper();

	public static HttpHelper get() {
		return mInstance;
	}
	
	public interface OnHttpDataListener{
		void onPreExecute();
		void onSuccess(String json);
		void onFild(String code);
	}
	
	public interface OnHttpResponseListener{
		void onSuccess(HttpResponse httpResponse);
		void onPreExecute();
		void onFild();
	}
	
	public void call(String url,OnHttpDataListener l){
		new HttpStringAsyncTask(l).execute(url);
	}
	
//	public void call(String url,OnHttpResponseListener l){
//		new HttpRequestAsyncTask(l).execute(new HttpRequestModel(url));
//	}

    public void callString(String url,OnHttpDataListener l){
        new HttpAsyncTask(l).execute(url);
    }
	
    public HttpResponse httpGetResponse(String url){
    	return httpGet(new HttpRequestModel(url));
    }
    
    public HttpResponse httpGet(HttpRequestModel request) {
        String url;
        if (request == null || StringUtils.isEmpty(url = request.getUrl())) {
            return null;
        }

        return  httpGet2(new HttpRequestModel(url));
    }
	
	private class HttpStringAsyncTask extends AsyncTask<String, Void, HttpResponse> {

        private OnHttpDataListener listener;

        public HttpStringAsyncTask(OnHttpDataListener listener) {
            this.listener = listener;
        }

        protected HttpResponse doInBackground(String... url) {
            if (ArrayUtils.isEmpty(url)) {
                return null;
            }
            return httpGetResponse(url[0]);
        }

        protected void onPreExecute() {
        	if (listener != null) {
        		listener.onPreExecute();
			}
        }

        protected void onPostExecute(HttpResponse httpResponse) {
            if (listener != null) {
            	if (httpResponse != null && httpResponse.getResponseBody() != null) {
            		String responseBody = httpResponse.getResponseBody();
            		if (!StringUtils.isEmpty(responseBody)) {
            			listener.onSuccess(responseBody);
					}else {
						listener.onFild("responseBody is null");
					}
				}else if(httpResponse == null){
					listener.onFild("httpResponse is null");
				}
            }
        }
    }
    
    private class HttpRequestAsyncTask extends AsyncTask<HttpRequestModel, Void, HttpResponse> {

        private OnHttpResponseListener listener;

        public HttpRequestAsyncTask(OnHttpResponseListener listener) {
            this.listener = listener;
        }

        protected HttpResponse doInBackground(HttpRequestModel... httpRequest) {
            if (ArrayUtils.isEmpty(httpRequest)) {
                return null;
            }
            return httpGet(httpRequest[0]);
        }

        protected void onPreExecute() {
            if (listener != null) {
            	listener.onPreExecute();
            }
        }

        protected void onPostExecute(HttpResponse httpResponse) {
            if (listener != null) {
                listener.onSuccess(httpResponse);
            }
        }
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {

        private OnHttpDataListener listener;

        public HttpAsyncTask(OnHttpDataListener listener) {
            this.listener = listener;
        }

        protected String doInBackground(String... url) {
            if (ArrayUtils.isEmpty(url)) {
                return null;
            }
            return HttpClientUtils.HttpGetString(url[0]);
        }

        protected void onPreExecute() {
            if (listener != null) {
                listener.onPreExecute();
            }
        }

        protected void onPostExecute(String str) {
            if (listener != null) {
                listener.onSuccess(str);
            }
        }
    }

    public static HttpResponse httpGet2(HttpRequestModel request) {
        if (request == null) {
            return null;
        }

        BufferedReader bufferedReader = null;
        HttpURLConnection conn = null;
        HttpResponse response;
        try {
            URL url = new URL(request.getUrl());
            try {
                response = new HttpResponse(request.getUrl());
                conn = (HttpURLConnection) url.openConnection();
                bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String s;
                while ((s = bufferedReader.readLine()) != null) {
                    sb.append(s).append("\n");
                }
                response.setResponseBody(sb.toString());
                return response;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) conn.disconnect();
        }
        return null;
    }
	
}
