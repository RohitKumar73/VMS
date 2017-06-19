package com.example.rohit.mainapp;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements BaseFragment.OnFragmentInteractionListener{
    Button b1;
public  int click=0;
     //  ragmentTransaction fragmentTransaction=getFragmentManager.;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.button);
b1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        BaseFragment baseFragment= new BaseFragment();
        android.app.FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.app_fragment,baseFragment);
     //   fragmentTransaction.setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out);
        switch (click){
            case 0:
                click=1;
                fragmentTransaction.show(baseFragment);
            case 1:
                click=0;

                fragmentTransaction.hide(baseFragment);

                break;

        }
fragmentTransaction.commit();

    }
});

     //   b1.setOnClickListener(but);

    }
    @Override
    public void onFragmentInteraction(int position) {

    }
}
