package com.example.r3l0ad3d.expendablelistview;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by r3l0ad3d on 4/29/17.
 */

public class ExAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> parentList;
    private HashMap<String,List<String>> childList;

    public ExAdapter(Context context, List<String> parentList, HashMap<String, List<String>> childList) {
        this.context = context;
        this.parentList = parentList;
        this.childList = childList;
    }


    protected class ParentViewHolder{
        TextView parentTV;
    }

    protected class ChildViewHolder{
        TextView childTV;
    }

    //return Parentlist size
    @Override
    public int getGroupCount() {
        return parentList.size();
    }

    //return childList size
    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(parentList.get(groupPosition)).size();
    }

    //return parent item object
    @Override
    public Object getGroup(int groupPosition) {
        return parentList.get(groupPosition);
    }

    //return child item as a object
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(parentList.get(groupPosition)).get(childPosition);
    }

    //return paren position
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //return child position
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //return paren view
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        //get paren item name
        String headerText = (String) getGroup(groupPosition);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ParentViewHolder parentViewHolder;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.list_item,null);
            parentViewHolder = new ParentViewHolder();
            parentViewHolder.parentTV = (TextView) convertView.findViewById(R.id.tvListItem);
            convertView.setTag(parentViewHolder);
        }else {
            parentViewHolder = (ParentViewHolder) convertView.getTag();
        }

        parentViewHolder.parentTV.setTypeface(null, Typeface.BOLD_ITALIC);
        parentViewHolder.parentTV.setText(headerText);

        return convertView;
    }

    //return child view
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        //get child item
        String childText = (String) getChild(groupPosition,childPosition);
        LayoutInflater inflater = (LayoutInflater) context
                                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ChildViewHolder childViewHolder;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.list_item_child,null);
            childViewHolder = new ChildViewHolder();
            childViewHolder.childTV = (TextView) convertView.findViewById(R.id.tvListItemChild);
            convertView.setTag(childViewHolder);
        }else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }

        childViewHolder.childTV.setText(childText);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
