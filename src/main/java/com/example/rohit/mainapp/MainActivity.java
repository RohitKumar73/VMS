package com.example.rohit.mainapp;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity implements BaseFragment.OnFragmentInteractionListener{

    BaseFragment baseFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baseFragment = new BaseFragment();
        android.support.v4.app.FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.add(R.id.app_fragment,baseFragment).setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
                .hide(baseFragment).commit();
    }

    public void onLaunch(View v) {

        if (baseFragment.isVisible()) {
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.hide(baseFragment).commit();
        } else if (!baseFragment.isVisible()) {
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.show(baseFragment).commit();
        }

    }

    @Override
    public void onFragmentInteraction(int position) {

    }
}
