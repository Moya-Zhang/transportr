package de.grobox.transportr;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import android.support.annotation.Nullable;
import android.content.Intent;
public class collection extends TransportrActivity {

    private ListView listView;
    private List<String> mList = new LinkedList<>();
    private Button sortBtn;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collection);
        setUpCustomToolbar(false);
        initList();
        sortBtn=(Button)findViewById(R.id.sort);
        this.listView = (ListView) findViewById(R.id.collection_list_view);
        final collection_adapter adapter = new collection_adapter(collection.this, mList);
        listView.setAdapter(adapter);
        sortBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                switch(view.getId()) {
                    case R.id.sort:
                        Collections.sort(mList, new Comparator<String>() {
                            @Override
                            public int compare(String o1, String o2) {
                                if (o1 == null || o2 == null) {
                                    return -1;
                                }
                                if (o1.length() > o2.length()) {
                                    return 1;
                                }
                                if (o1.length() < o2.length()) {
                                    return -1;
                                }
                                if (o1.compareTo(o2) > 0) {
                                    return 1;
                                }
                                if (o1.compareTo(o2) < 0) {
                                    return -1;
                                }
                                if (o1.compareTo(o2) == 0) {
                                    return 0;
                                }
                                return 0;
                            }
                        });
                        Toast.makeText(collection.this, "整理成功", Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                        update();
                    default:
                        break;
                }
            }
        });
        //ListView item 中的删除按钮的点击事件
        adapter.setOnItemDeleteClickListener(new collection_adapter.onItemDeleteListener() {
            @Override
            public void onDeleteClick(int i) {
                mList.remove(i);
                Toast.makeText(collection.this,"删除成功",Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                update();
            }
        });
        //分享按钮的点击事件
        adapter.setOnItemShareClickListener(new collection_adapter.onItemShareListener() {
            @Override
            public void onShareClick(int i) {
                String tobeShare=mList.get(i);
                Intent textIntent = new Intent(Intent.ACTION_SEND);
                textIntent.setType("text/plain");
                textIntent.putExtra(Intent.EXTRA_TEXT, tobeShare);
                startActivity(Intent.createChooser(textIntent, "分享"));
            }
        });
    }
   /*
   public int add(String s){
        if(!s.isEmpty()) {
            mList.add(s);
        }
        return mList.size();
    }
    public int del(int i){
        if(i>=0&&i<mList.size()){
            mList.remove(i);
        }
        return mList.size();
    }*/
    private void update(){
        FileOutputStream out=null;
        BufferedWriter writer=null;
        try{
            out=openFileOutput("collectionData", Context.MODE_PRIVATE);
            writer=new BufferedWriter(new OutputStreamWriter(out));
            for(int i=0;i<mList.size();i++)
                writer.write(mList.get(i)+'\n');
        }catch(IOException e){
            e.printStackTrace();
        } finally {
            try{
                if(writer!=null)
                    writer.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    private void initList(){
        FileInputStream in=null;
        BufferedReader reader=null;
        try{
            in=openFileInput("collectionData");
            reader=new BufferedReader(new InputStreamReader(in));
            String line="";
            while((line=reader.readLine())!=null)
                mList.add(line);
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(reader!=null)
                    reader.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}