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

public class AdaptadorUserList extends BaseAdapter {

    private Context context;
    private ArrayList<UserProfile> items;
    private Comunidad comunidad;

    public AdaptadorUserList(Context context, ArrayList<UserProfile> items) {
        this.context = context;
        this.items = items;
    }

    /*public AdaptadorUserList(ValueEventListener comunidad, ArrayList<UserProfile> items) {
        this.comunidad = comunidad;
        this.items = items;
    }*/

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;

        if (convertView == null) {
            // Create a new view into the list.
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.item_lista_usuarios, parent, false);
        }

        // Set data into the view.
        //ImageView ivItem = (ImageView) rowView.findViewById(R.id.ivItem);
        TextView nomUsuari = (TextView) rowView.findViewById(R.id.idUserNameAdapter);

        UserProfile item = this.items.get(position);
        nomUsuari.setText(item.getName());
        //ivItem.setImageResource(item.getImage());

        return rowView;
    }

}