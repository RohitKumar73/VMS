package com.example.rohit.mainapp;




import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class BaseFragment extends android.support.v4.app.Fragment{
    private PackageManager packageManager;
    private ArrayList<AppDetail> apps;
    private GridView gridview;
    private OnFragmentInteractionListener mListener;
    public BaseFragment() {
    }

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        packageManager = getActivity().getPackageManager();
        apps = new ArrayList<>();
         Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> availableActivities = packageManager.queryIntentActivities(i, 0);
        for (ResolveInfo ri : availableActivities) {
            AppDetail app = new AppDetail();
            app.label = ri.loadLabel(packageManager);
            app.name = ri.activityInfo.packageName;
            app.icon = ri.activityInfo.loadIcon(packageManager);
            apps.add(app);
        }
        gridview = (GridView) getActivity().findViewById(R.id.apps_grid);
         ArrayAdapter<AppDetail> appDetailArrayAdapter = new ArrayAdapter<AppDetail>(getActivity(), android.R.layout.activity_list_item, apps) {
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

                Intent i =packageManager.getLaunchIntentForPackage(apps.get(position).name.toString());
                getActivity().startActivity(i);
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

    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

               return inflater.inflate(R.layout.fragment_base, container, false);


    }

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

    public interface OnFragmentInteractionListener {

        // TODO: Update argument type and name
        void onFragmentInteraction(int position);
    }

}


