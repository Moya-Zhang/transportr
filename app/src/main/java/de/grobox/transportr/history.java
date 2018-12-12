package de.grobox.transportr;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import android.support.annotation.Nullable;
import android.content.Intent;
import java.io.*;
import java.util.Comparator;
public class history extends TransportrActivity {

    private ListView listView;
    private List<String> mList = new LinkedList<>();
    private List<String> bList=new LinkedList<>();
    private Button btn,sortBtn;
    private EditText editText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        setUpCustomToolbar(false);
        init();
        btn=(Button)findViewById(R.id.button3);
        sortBtn=(Button)findViewById(R.id.sort);
        editText=(EditText)findViewById(R.id.editText5);
        this.listView = (ListView) findViewById(R.id.history_list_view);
        final history_adapter adapter = new history_adapter(history.this, mList);
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
                        Toast.makeText(history.this, "整理成功", Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                        update1();
                    default:
                        break;
                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId()){
                    case R.id.button3:
                        String input=editText.getText().toString();
                        if(!input.isEmpty()){
                            Toast.makeText(history.this,"添加成功",Toast.LENGTH_SHORT).show();
                            mList.add(input);
                            update1();
                        }
                        adapter.notifyDataSetChanged();
                    default:
                        break;
                }
            }
        });
        //ListView item 中的删除按钮的点击事件
        adapter.setOnItemDeleteClickListener(new history_adapter.onItemDeleteListener() {
            @Override
            public void onDeleteClick(int i) {
                mList.remove(i);
                update1();
                Toast.makeText(history.this,"删除成功",Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        });
        //收藏按钮的点击事件
        adapter.setmOnItemBookmarkListener(new history_adapter.onItemBookmarkListener() {
            @Override
            public void onBookmarkClick(int i) {
                String bookmark=mList.get(i);
                bList.add(bookmark);
                update2();
                Toast.makeText(history.this,"添加收藏成功",Toast.LENGTH_SHORT).show();
            }
        });
        //分享按钮的点击事件
        adapter.setOnItemShareClickListener(new history_adapter.onItemShareListener() {
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
    private void update1(){
        FileOutputStream out=null;
        BufferedWriter writer=null;
        try{
            out=openFileOutput("historyData", Context.MODE_PRIVATE);
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
    private void update2(){
        FileOutputStream out=null;
        BufferedWriter writer=null;
        try{
            out=openFileOutput("collectionData", Context.MODE_PRIVATE);
            writer=new BufferedWriter(new OutputStreamWriter(out));
            for(int i=0;i<bList.size();i++)
                writer.write(bList.get(i)+'\n');
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
    /**
     * 初始化数据
     */
    /*public int add(String s){
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
    }
    public int bookmark(int i){
        if(i>=0&&i<mList.size()){
            bList.add(mList.get(i));
        }
        return bList.size();
    }*/
    private void init(){
        FileInputStream in1=null,in2=null;
        BufferedReader reader1=null,reader2=null;
        try{
            in1=openFileInput("historyData");
            in2=openFileInput("collectionData");
            reader1=new BufferedReader(new InputStreamReader(in1));
            reader2=new BufferedReader(new InputStreamReader(in2));
            String line="";
            while((line=reader1.readLine())!=null)
                mList.add(line);
            while((line=reader2.readLine())!=null)
                bList.add(line);
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(reader1!=null)
                    reader1.close();
                if(reader2!=null)
                    reader2.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}