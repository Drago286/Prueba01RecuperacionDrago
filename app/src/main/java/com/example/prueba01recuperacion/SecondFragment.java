package com.example.prueba01recuperacion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.prueba01recuperacion.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    private EditText txt_name, txt_info, txt_search;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);

        txt_name = (EditText) binding.txtName;
        txt_info = (EditText) binding.txtInfo;
        txt_search = (EditText) binding.txtSearch;


        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences preferences = getActivity().getSharedPreferences("datos", Context.MODE_PRIVATE);

        String name = preferences.getString("nombre", "sin info");

        String info = preferences.getString("correo", "sin info");

        txt_name.setText(name);

        txt_info.setText(info);

        binding.btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_thirdFragment);
            }
        });

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarInfo();
            }
        });

        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarInfo();

            }
        });
        binding.btnNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navegar();
            }
        });



    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void navegar(){

        Intent i= new Intent(this.getContext(), WebActivity.class);

        i.putExtra("url",txt_search.getText().toString());

        startActivity(i);
    }

    public void guardarInfo(){


        String nombre = txt_name.getText().toString();
        String correo = txt_info.getText().toString();
        SharedPreferences preferences = getActivity().getSharedPreferences("datos",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(nombre,correo);
        editor.commit();

        Toast.makeText(getContext(),"Informacion guardada correctamente :D",Toast.LENGTH_SHORT).show();



    }

    public void buscarInfo(){

        String nombreBuscado = txt_name.getText().toString();

        SharedPreferences preferences = getActivity().getSharedPreferences("datos",Context.MODE_PRIVATE);

        String correoEncontrado = preferences.getString(nombreBuscado.toString(),"");

        if (correoEncontrado.length() == 0) {

            Toast.makeText(getActivity(), "no tenemos registros :P ", Toast.LENGTH_SHORT).show();

        }else{

            txt_info.setText(correoEncontrado);
        }

    }

}