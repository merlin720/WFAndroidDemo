package whiskeyfei.android.com.android_data_interface;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import whiskeyfei.android.com.android_data_interface.entity.HttpHelper;
import whiskeyfei.android.com.android_data_interface.entity.HttpHelper.OnHttpDataListener;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private final String URL_ACTION = "http://www.w3cschool.cc/tags/html-httpmethods.html";

    private EditText mHttpEditText;
    private Button mHttpBtn;
    private TextView mHttpContent;
    private Button mHttpClientBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mHttpBtn = (Button) findViewById(R.id.http_btn);
        mHttpClientBtn = (Button)findViewById(R.id.httpclient_btn);
        mHttpEditText = (EditText) findViewById(R.id.http_edit_url);
        mHttpContent = (TextView) findViewById(R.id.http_content);
        mHttpClientBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpHelper.get().callString(URL_ACTION, mOnHttpDataListener);
            }
        });

        mHttpBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpHelper.get().call(URL_ACTION, mOnHttpDataListener);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private OnHttpDataListener mOnHttpDataListener = new OnHttpDataListener() {

        @Override
        public void onSuccess(String json) {
            mHttpContent.setText(json);
        }

        @Override
        public void onFild(String code) {
            mHttpContent.setText("response is null.");
        }

        @Override
        public void onPreExecute() {
            mHttpContent.setText("wating…");
        }
    };
}
