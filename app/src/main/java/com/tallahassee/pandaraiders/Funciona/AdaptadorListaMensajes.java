package com.tallahassee.pandaraiders.Funciona;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tallahassee.pandaraiders.R;
import com.tallahassee.pandaraiders.objetos.Mensaje;

import java.util.ArrayList;

/**
 * Created by enric on 29/3/16.
 */
public class AdaptadorListaMensajes extends BaseAdapter {
    private static ArrayList<Mensaje> listMensajes;

    private LayoutInflater mInflater;

    public AdaptadorListaMensajes(Context photosFragment, ArrayList<Mensaje> results){
        listMensajes = results;
        mInflater = LayoutInflater.from(photosFragment);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listMensajes.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return listMensajes.get(arg0);
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
            convertView = mInflater.inflate(R.layout.item_mis_mensajes, null);
            holder = new ViewHolder();
            holder.txtname = (TextView) convertView.findViewById(R.id.idRemitente);
            holder.txtmesage = (TextView) convertView.findViewById(R.id.idMensajeRecibido);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtname.setText(listMensajes.get(position).getRemitente());
        holder.txtmesage.setText(listMensajes.get(position).getMensaje());

        return convertView;
    }

    static class ViewHolder{
        TextView txtname;
        TextView txtmesage;
    }
}
