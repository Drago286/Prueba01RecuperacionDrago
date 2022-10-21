package com.example.prueba01recuperacion;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.prueba01recuperacion.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private ScrollView scr_1;
    private Spinner sp_1;
    private String[] nombresCompañeros = {"Drago", "Diego", "Alex"};
    private String[] edadCompas = {"21", "20", "22"};


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        sp_1 = (Spinner) binding.sp1;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, nombresCompañeros);
        sp_1.setAdapter(adapter);
        scr_1 = (ScrollView) binding.scr1;

        return binding.getRoot();
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MediaPlayer mediaPlayer = MediaPlayer.create(this.getActivity(), R.raw.sound_long);


        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        binding.btnCerezas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "cerezas", Toast.LENGTH_SHORT).show();
                //Completar lógica de ScrollView, ojo no necesariamente se aplica toda la lógica en este boton.
            }
        });
        binding.btnPlatano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "platano :P", Toast.LENGTH_SHORT).show();

            }
        });

        binding.btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // medis
//                Completar el codigo necesario para reproducir el sonido "sound_long.mp3"
                mediaPlayer.start();
            }
        });
        sp_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                binding.btnShow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(),nombresCompañeros[position]+" tiene "+edadCompas[position]+" años",Toast.LENGTH_SHORT).show();

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        binding.btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Código para dar funcionamiento al spinner.
                //Puede que el onClick no sea el mejor método para resolver.
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * Función para capturar la imagen seleccionada
     *
     * @param view
     */
    public void selected(View view) {
//        Se recomendia generar una lógica de prgoramación para validar que fruta se selecciono y mostrar en un Toast el Nombre de la fruta seleccionada.
    }

}

