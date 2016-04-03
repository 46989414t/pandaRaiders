package com.tallahassee.pandaraiders.Funciona;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tallahassee.pandaraiders.R;
import com.tallahassee.pandaraiders.objetos.UserProfile;

import java.util.ArrayList;

/**
 * Created by enric on 27/3/16.
 */
public class ListviewContactAdapter extends BaseAdapter {
    private static ArrayList<UserProfile> listContact;

    private LayoutInflater mInflater;

    public ListviewContactAdapter(Context photosFragment, ArrayList<UserProfile> results){
        listContact = results;
        mInflater = LayoutInflater.from(photosFragment);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listContact.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return listContact.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.item_lista_usuarios, null);
            holder = new ViewHolder();
            holder.txtname = (TextView) convertView.findViewById(R.id.idUserNameAdapter);
           // holder.txtphone = (TextView) convertView.findViewById(R.id.lv_contact_item_phone);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtname.setText(listContact.get(position).getName());
        //holder.txtphone.setText(listContact.get(position).GetPhone());

        return convertView;
    }

    static class ViewHolder{
        TextView txtname;
    }
}