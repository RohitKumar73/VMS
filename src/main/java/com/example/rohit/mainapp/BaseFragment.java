package com.example.rohit.mainapp;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.annotation.RequiresApi;
import android.util.Log;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import java.util.Objects;


public class BaseFragment extends Fragment{
    private PackageManager packageManager;
    private ArrayList<AppDetail> apps;
    private GridView gridview;

    //  private static final String TAG = "BaseFragment";

    private OnFragmentInteractionListener mListener;


    public BaseFragment() {


        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        packageManager = getActivity().getPackageManager();
        apps = new ArrayList();
        final Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);
        final List<ResolveInfo> availableActivities = packageManager.queryIntentActivities(i, 0);
        for (ResolveInfo ri : availableActivities) {
            AppDetail app = new AppDetail();


            //      app.name = ri.activityInfo.loadLabel(packageManager);
            app.label = ri.loadLabel(packageManager);

            app.name = ri.activityInfo.packageName;

               // Toast.makeText(getLoaderManager(),Toast.LENGTH_LONG).show();
            app.icon = ri.activityInfo.loadIcon(packageManager);
            apps.add(app);
        }

        gridview = (GridView) getActivity().findViewById(R.id.apps_grid);
        final ArrayAdapter<AppDetail> appDetailArrayAdapter = new ArrayAdapter<AppDetail>(getActivity(), android.R.layout.activity_list_item, apps) {
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = convertView;
if(view==null) {
    view = getActivity().getLayoutInflater().inflate(R.layout.list_item, parent, false);
}
                ImageView  imageview = (ImageView) view.findViewById(R.id.app_icon);
                        imageview.setImageDrawable(apps.get(position).icon);

                TextView textview = (TextView) view.findViewById(R.id.app_text);

                    textview.setText(apps.get(position).label);
                return view;
            }


        };
        gridview.setAdapter(appDetailArrayAdapter);


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = packageManager.getLaunchIntentForPackage(apps.get(position).name.toString());
                       getActivity().startActivity(intent);

           }

        });
        ViewGroup.LayoutParams params = gridview.getLayoutParams();
        params.height = 500;
        params.width = 400;
        gridview.setLayoutParams(params);


    }
    public static BaseFragment newInstance() {
        BaseFragment fragment = new BaseFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



//        onActivityCreated(savedInstanceState);

    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       // getActivity().setContentView(R.layout.fragment_base);

        return inflater.inflate(R.layout.fragment_base, container, false);


    }

    // TODO: Rename method, update argument and hook method into UI event

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }




 /*   public void closefragment() {
        getActivity().getFragmentManager().beginTransaction().remove(this).commit();

    }
*/
  /*  public void setfragmentsize()
    {
        if(frame!=null) {

        }*/
      /*       frame = (FrameLayout) getActivity().findViewById(R.id.text_views);
           if(frame!=null){
            getActivity().getWindowManager().getDefaultDisplay().getSize(null);
            int height = 100;
            int width = 100;

            if (height<=frame.getHeight() ) {
                    if (width<=frame.getWidth()) {
                        Bundle savedInstanceState = null;
                        onActivityCreated(savedInstanceState);

                    }   }
            }*/


    public interface OnFragmentInteractionListener {

        // TODO: Update argument type and name
        void onFragmentInteraction(int position);
    }

}


