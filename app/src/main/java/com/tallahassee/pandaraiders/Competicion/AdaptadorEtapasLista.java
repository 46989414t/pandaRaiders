package com.tallahassee.pandaraiders.Competicion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tallahassee.pandaraiders.R;
import com.tallahassee.pandaraiders.objetos.Etapa;

import java.util.ArrayList;


/**
 * Created by enric on 27/3/16.
 */
public class AdaptadorEtapasLista extends BaseAdapter {
    private static ArrayList<Etapa> listContact;

    private LayoutInflater mInflater;

    public AdaptadorEtapasLista(Context photosFragment, ArrayList<Etapa> results){
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
            convertView = mInflater.inflate(R.layout.item_etapa, null);
            holder = new ViewHolder();
            holder.txtnombre = (TextView) convertView.findViewById(R.id.idNombreEtapa);
            holder.txtinicio = (TextView) convertView.findViewById(R.id.idInicioEtapa);
            holder.txtFin = (TextView) convertView.findViewById(R.id.idFinEtapa);
            holder.txtDescripcion = (TextView) convertView.findViewById(R.id.idDescripcionEtapa);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtnombre.setText(listContact.get(position).getNombre());
        holder.txtinicio.setText(listContact.get(position).getInicio());
        holder.txtFin.setText(listContact.get(position).getFin());
        holder.txtDescripcion.setText(listContact.get(position).getDescripcion());
        //holder.txtphone.setText(listContact.get(position).GetPhone());

        return convertView;
    }

    static class ViewHolder{
        TextView txtnombre;
        TextView txtinicio;
        TextView txtFin;
        TextView txtDescripcion;
    }
}