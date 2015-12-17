package com.fei.butterknife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.textview)
    TextView title;

    @Bind(R.id.button)
    Button button;

    @Bind(R.id.recycleview)
    RecyclerView mRecyclerView;

    PageAdapter mPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        title.setText("use butter knife");
        button.setText("use butter knife");
        mPageAdapter = new PageAdapter();
        mRecyclerView.setAdapter(mPageAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//设置RecyclerView样式
        mPageAdapter.setList(getList());
        mPageAdapter.notifyDataSetChanged();
    }

    private List<String> getList() {
        List<String> list = new ArrayList<>();
        for (int i = 0;i<10;i++){
            list.add("item:"+i);
        }
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
}
