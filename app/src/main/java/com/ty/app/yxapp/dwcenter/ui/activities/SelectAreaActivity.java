package com.ty.app.yxapp.dwcenter.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ty.app.yxapp.dwcenter.R;
import com.ty.app.yxapp.dwcenter.ui.activities.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class SelectAreaActivity extends BaseActivity implements AdapterView.OnItemClickListener{

    private ListView selectLv;
    private ArrayAdapter<String> adapter;
    private List<String> names;

    @Override
    public void onBeforeCreate() {

    }

    @Override
    public View onCreate() {
        String from = getIntent().getStringExtra("from");
        names = new ArrayList<>();
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,names);
        initItems(from);
        View view = getLayoutInflater().inflate(R.layout.activity_select_area,null);
        selectLv = (ListView) view.findViewById(R.id.select_lv);
        selectLv.setAdapter(adapter);
        selectLv.setOnItemClickListener(this);
        return view;
    }

    public void initItems(String from) {
        names.clear();
        switch (from){
            case "area":
                addArea();
                break;
            case "street":
                addStreet();
                break;
            case "village":
                addVillage();
                break;
            default:
                break;
        }
        adapter.notifyDataSetChanged();
    }

    private void addVillage() {
        names.add("村庄1");
        names.add("村庄2");
    }

    private void addStreet() {
        names.add("街道1");
        names.add("街道2");
        names.add("街道3");
        names.add("街道4");
    }

    private void addArea() {
        names.add("区域1");
        names.add("区域2");
        names.add("区域3");
        names.add("区域4");
        names.add("区域5");
        names.add("区域6");
        names.add("区域7");
        names.add("区域8");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.putExtra("return",names.get(position));
        setResult(0,intent);
        finish();
    }
}
