package com.crisspian.fragment_guide_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import com.crisspian.fragment_guide_01.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private boolean isFragmentShow = false;

    public static final String KEY_ONE ="KEY_ONE";

    protected void onSaveInstanceState(@NonNull Bundle outState){
        outState.putBoolean(KEY_ONE, isFragmentShow);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(savedInstanceState !=null){
            isFragmentShow =savedInstanceState.getBoolean(KEY_ONE);
        }

        if(isFragmentShow){
            binding.button.setText("Close");
        }
        
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isFragmentShow) {
                  showFragment();
                } else {
                    closeFragment();
                }
            }
        });

    }

    private void showFragment() {
        // Generamos la instancia del fragmento gracias al factory method
        FirstFragment firstFragment = FirstFragment.newInstance("","");
        //Obtener instancia del FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        //Obtenemos e instanciamos una transacción
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // añadir el fragmento y lo asociamos al contenedor donde se mostrara
        fragmentTransaction.add(R.id.content_fragment, firstFragment)
                //.addToBackStack(null)
                .commit();

        binding.button.setText("Close");
        isFragmentShow = true;

    }

    private void closeFragment(){

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment =fragmentManager.findFragmentById(R.id.content_fragment);
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(fragment).commit();

        }
        binding.button.setText("Open");
        isFragmentShow = false;
    }




    }