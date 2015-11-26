package whiskeyfei.android.com.android_data_interface.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpClientUtils {
	private static HttpClient mHttpClient;

	public static HttpClient getHttpClient() {
		if (mHttpClient == null) {
			mHttpClient = new DefaultHttpClient();
		}
		return mHttpClient;
	}

	public static String HttpGetString(String url) {
		String body = null;
		HttpClient httpClient = getHttpClient();
		try {
			HttpGet httpGet = new HttpGet(url);
			HttpResponse response = httpClient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity httpEntity = response.getEntity();
				if (httpEntity != null) {
					body = EntityUtils.toString(httpEntity);
					if (!StringUtils.isEmpty(body)) {
						return body;
					}
				}
			}
			httpGet.abort();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return body;
	}
}
