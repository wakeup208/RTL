package com.wakeup.myapplication;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.wakeup.myapplication.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    FloatingActionButton fb, fb2;
    SecondFragment fragment1;
    FirstFragment fragment;

    LinearLayout ln1, ln2;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        fb = findViewById(R.id.fab);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideFB();
            }
        });

        fb2 = findViewById(R.id.fab2);

        fb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFB();
            }
        });

        ln1 = findViewById(R.id.fg1);
        ln2 = findViewById(R.id.fg2);

        fragment = new FirstFragment();
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fg1, fragment, fragment.getTag()).commit();

        fragment1 = new SecondFragment();
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fg2, fragment1, fragment1.getTag()).commit();

    }

    public void hideFB() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(fragment.isAdded()) {
            ft.hide(fragment).commit();

           ln1.setVisibility(View.GONE);
        }
    }

    public void showFB() {
        ln1.setVisibility(View.VISIBLE);

        fragment = new FirstFragment();
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fg1, fragment, fragment.getTag()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}