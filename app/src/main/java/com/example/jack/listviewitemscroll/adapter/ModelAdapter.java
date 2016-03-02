package com.example.jack.listviewitemscroll.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.jack.listviewitemscroll.R;
import com.example.jack.listviewitemscroll.model.model;

import java.util.List;

/**
 * Created by Jack on 2016/3/2.
 */
public class ModelAdapter extends ArrayAdapter<model>{

    private int ResourceId;

    public ModelAdapter(Context context, int resource, List<model> objects) {
        super(context, resource, objects);
        this.ResourceId=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=null;
        ViewHolder mViewHolder=null;
        model m=getItem(position);
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(this.ResourceId,parent,false);
            mViewHolder=new ViewHolder();
            mViewHolder.left=(TextView)view.findViewById(R.id.leftbutton);
            mViewHolder.content=(TextView)view.findViewById(R.id.contentview);
            mViewHolder.right=(TextView)view.findViewById(R.id.rightbutton);
            view.setTag(mViewHolder);
        }else{
            view=convertView;
            mViewHolder=(ViewHolder)view.getTag();
        }

        mViewHolder.left.setText(m.getLeft());
        mViewHolder.content.setText(m.getContent());
        mViewHolder.right.setText(m.getRight());

        return view;
    }

    class ViewHolder{
        public TextView left;
        public TextView content;
        public TextView right;
    }

}
