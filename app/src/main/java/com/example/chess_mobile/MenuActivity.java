package com.example.chess_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.chess_mobile.Fragments.OptionsFragment;
import com.example.chess_mobile.Fragments.PlayFragment;
import com.example.chess_mobile.Fragments.RecordFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_actinity);

        BottomNavigationView bottomNav = findViewById(R.id.main_menu);

        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.nav_Options:
                    selectedFragment = new OptionsFragment();
                    break;
                case R.id.nav_Play:
                    selectedFragment = new PlayFragment();
                    break;
                case R.id.nav_Record:
                    selectedFragment = new RecordFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, selectedFragment).commit();

            return true;
        });

    }
}