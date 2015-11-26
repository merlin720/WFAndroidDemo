package whiskeyfei.android.com.android_data_interface.entity;

public class HttpRequestModel {

    private String mHttpUrl;

    public HttpRequestModel(String url) {
        this.mHttpUrl = url;
    }

    public String getUrl() {
        return mHttpUrl;
    }
}
