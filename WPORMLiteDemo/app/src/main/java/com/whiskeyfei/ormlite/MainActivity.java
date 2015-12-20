package com.whiskeyfei.ormlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.whiskeyfei.ormlite.adapter.ListViewAdapter;
import com.whiskeyfei.ormlite.bean.Student;
import com.whiskeyfei.ormlite.db.DatabaseHelper;
import com.whiskeyfei.ormlite.utils.StringUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText mEditText;
    private Button mButton;
    private ListView mListVeiw;
    ListViewAdapter mListViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        mEditText = (EditText) findViewById(R.id.input);
        mButton = (Button) findViewById(R.id.save);
        initListView();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = mEditText.getText().toString();
                if (StringUtils.isEmpty(text)) {
                    Toast.makeText(MainActivity.this, " input someting", Toast.LENGTH_SHORT).show();
                } else {
                    testAddStudent(text);
                }
            }
        });
        refershList();
    }

    private void refershList() {
        List<Student> list = getListStudent();
        if (list != null)
            mListViewAdapter.setList(list);
        mListViewAdapter.notifyDataSetChanged();
    }

    private void initListView() {
        mListVeiw = (ListView) findViewById(R.id.listview);
        mListViewAdapter = new ListViewAdapter(this);
        mListVeiw.setAdapter(mListViewAdapter);
    }

    private List<Student> getListStudent(){
        try {
            Dao dao = DatabaseHelper.getHelper(this).getDao(Student.class);
            List<Student> list = dao.queryForAll();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void testAddStudent(String text) {
        try {
            Dao dao = DatabaseHelper.getHelper(this).getDao(Student.class);
            Student student = new Student();
            student.setDao(dao);
            student.setName(text);
            student.create();
            refershList();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
