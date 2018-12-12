package de.grobox.transportr;


import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.View;

import android.content.Intent;
import android.widget.Toolbar;


public class test extends TransportrActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        setUpCustomToolbar(false);
    }
    public void click(View v) {
        // TODO Auto-generated method stub
        //获取组件的资源id
        int id = v.getId();
        switch (id) {
            case R.id.button:
                Intent intent=new Intent(this,de.grobox.transportr.history.class);
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent2=new Intent(this,de.grobox.transportr.collection.class);
                startActivity(intent2);
                //Log.i("指定onClick属性方式","bt2点击事件");
                break;
            default:
                break;
        }
    }
    private Toolbar bar;
}
