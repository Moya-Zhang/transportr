package de.grobox.transportr;

import java.util.*;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


public class collection_adapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList = new ArrayList<>();
    public collection_adapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.collection_item, null);
            viewHolder.mTextView = (TextView) view.findViewById(R.id.item_tv);
            viewHolder.bt1 = (Button) view.findViewById(R.id.item_btn_del);
            viewHolder.bt3 = (Button) view.findViewById(R.id.item_btn_share);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.mTextView.setText(mList.get(i));
        viewHolder.bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemDeleteListener.onDeleteClick(i);
            }
        });
        viewHolder.bt3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mOnItemShareListener.onShareClick(i);
            }
        });
        return view;
    }

    /**
     * 删除按钮的监听接口
     */
    public interface onItemDeleteListener {
        void onDeleteClick(int i);
    }
    public interface onItemShareListener {
        void onShareClick(int i);
    }
    private onItemDeleteListener mOnItemDeleteListener;
    private onItemShareListener mOnItemShareListener;

    public void setOnItemDeleteClickListener(onItemDeleteListener mOnItemDeleteListener) {
        this.mOnItemDeleteListener = mOnItemDeleteListener;
    }
    public void setOnItemShareClickListener(onItemShareListener mOnItemShareListener) {
        this.mOnItemShareListener = mOnItemShareListener;
    }
    class ViewHolder {
        TextView mTextView;
        Button bt1,bt3;
    }

}