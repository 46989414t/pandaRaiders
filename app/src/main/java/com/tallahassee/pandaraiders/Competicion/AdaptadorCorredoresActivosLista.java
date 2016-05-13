package com.tallahassee.pandaraiders.Competicion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tallahassee.pandaraiders.R;
import com.tallahassee.pandaraiders.objetos.Localizacion;

import java.util.ArrayList;


/**
 * Created by enric on 27/3/16.
 */
public class AdaptadorCorredoresActivosLista extends BaseAdapter {
    private static ArrayList<Localizacion> listContact;

    private LayoutInflater mInflater;

    public AdaptadorCorredoresActivosLista(Context photosFragment, ArrayList<Localizacion> results){
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
            convertView = mInflater.inflate(R.layout.item_corredores_activos, null);
            holder = new ViewHolder();
            holder.txtname = (TextView) convertView.findViewById(R.id.idEmailCorredorActivo);
            // holder.txtphone = (TextView) convertView.findViewById(R.id.lv_contact_item_phone);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtname.setText(listContact.get(position).getEmail());
        //holder.txtphone.setText(listContact.get(position).GetPhone());

        return convertView;
    }

    static class ViewHolder{
        TextView txtname;
    }
}