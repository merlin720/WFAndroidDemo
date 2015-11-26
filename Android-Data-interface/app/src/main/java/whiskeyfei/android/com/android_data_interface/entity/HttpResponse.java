package whiskeyfei.android.com.android_data_interface.entity;

public class HttpResponse {

    private String url;
    private String responseBody;

    public HttpResponse(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

}
