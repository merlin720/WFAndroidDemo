package whiskeyfei.com.study;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import whiskeyfei.com.study.adapter.ItemAdapterGetView;
import whiskeyfei.com.study.model.ItemModel;


public class MainActivity extends Activity {
    private ListView mListView;
    private List<ItemModel> mList = new ArrayList<ItemModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        for (int i=0;i<20;i++){
            mList.add(new ItemModel(R.drawable.ic_launcher,"title"+i,"content"+i));
        }
    }

    private void initView() {
        mListView = (ListView)findViewById(R.id.list_view);
        mListView.setAdapter(new ItemAdapterGetView(this,mList));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "item" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
