package com.example.jack.listviewitemscroll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.jack.listviewitemscroll.adapter.ModelAdapter;
import com.example.jack.listviewitemscroll.model.model;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView mListView=null;
    private List<model> listmodel=null;
    private ModelAdapter adapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listmodel=new ArrayList<model>();
        initData();
        initView();
    }

    public void initData(){
        model m=null;

        for(int i=0;i<20;i++){
            m=new model("left","content","right");
            listmodel.add(m);
        }
    }

    public void initView(){
        mListView=(ListView)findViewById(R.id.listview);
        adapter=new ModelAdapter(this,R.layout.listview_item,listmodel);
        mListView.setAdapter(adapter);
    }

}
