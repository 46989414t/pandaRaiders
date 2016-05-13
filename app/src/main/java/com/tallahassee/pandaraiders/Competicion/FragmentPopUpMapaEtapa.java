package com.tallahassee.pandaraiders.Competicion;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tallahassee.pandaraiders.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPopUpMapaEtapa extends Fragment {


    public FragmentPopUpMapaEtapa() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.pop_up_mapa_etapa, container, false);
        System.out.println("ENTRO EN EL FRAGMENT DE POP UP MAPA");

        return rootView;
    }


}
