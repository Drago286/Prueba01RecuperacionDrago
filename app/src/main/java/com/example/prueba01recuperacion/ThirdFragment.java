package com.example.prueba01recuperacion;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


import com.example.prueba01recuperacion.databinding.FragmentThirdBinding;


public class ThirdFragment extends Fragment {

    private FragmentThirdBinding binding;

    private EditText txt_nombre, txt_codigo;

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txt_codigo = (EditText) binding.txtCodigo;
        txt_nombre = (EditText) binding.txtNombre;

        binding.btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

        binding.btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
            }
        });

        binding.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        binding.btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ThirdFragment.this)
                        .navigate(R.id.action_thirdFragment_to_SecondFragment);
            }
        });
    }

    public void save() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.getActivity(), "administration", null, 1);
        SQLiteDatabase database = admin.getWritableDatabase();

        String codigo = txt_codigo.getText().toString();
        String nombre = txt_nombre.getText().toString();
        if (!codigo.isEmpty() && !nombre.isEmpty()) {
            ContentValues register = new ContentValues();
            register.put("code", codigo);
            register.put("name", nombre);
            database.insert("products", null, register);
            database.close();
            cleanForm();
            Toast.makeText(this.getActivity(), "Producto ingresado correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this.getActivity(), "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
        }
    }

    public void search() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this.getActivity(), "administration", null, 1);
        SQLiteDatabase database = admin.getWritableDatabase();
        admin.getWritableDatabase();
        String codigo = txt_codigo.getText().toString();
        if (!codigo.isEmpty()) {
            Cursor row = database.rawQuery("select name from products where code=" + codigo, null);
            if (row.moveToFirst()) {
                txt_nombre.setText(row.getString(0));
                database.close();
            } else {
                Toast.makeText(this.getActivity(), "Producto no encontrado", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this.getActivity(), "Ingrese un 'Codigo'", Toast.LENGTH_SHORT).show();
        }
    }

    public void cleanForm() {
        txt_nombre.setText("");
        txt_codigo.setText("");
    }
}
