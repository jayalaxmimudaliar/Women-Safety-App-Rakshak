package com.example.wsafety;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.wsafety.R;
import com.google.android.material.tabs.TabLayout;



public class Settings extends Fragment implements View.OnClickListener{
    TextView tv;
    Button b2;
    EditText e1, e2;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private String cycledays;
    private String periodlength;
    private String cycledays_edit = "";
    private String perioddays_edit = "";
    ViewPager viewPager;
    TabLayout tabs;
    TabLayout.TabLayoutOnPageChangeListener listener;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_period_setting, container, false);

        super.onCreate(savedInstanceState);
        e1 = (EditText)view.findViewById(R.id.editText);
        e2 = (EditText)view.findViewById(R.id.editText2);
        b2 = (Button)view.findViewById(R.id.button2);
        View v= getActivity().findViewById(R.id.root_acv);
        viewPager =(ViewPager) v.findViewById(R.id.view_pager);

        tabs =(TabLayout) v.findViewById(R.id.tabs);
        listener=new TabLayout.TabLayoutOnPageChangeListener(tabs);
        viewPager.addOnPageChangeListener(listener);
        b2.setOnClickListener(this);

        sharedPreferences = this.getActivity().getSharedPreferences(loginIn.PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        cycledays = sharedPreferences.getString("cycledays", "");
        periodlength = sharedPreferences.getString("periodlength", "");

        e1.setText(cycledays);
        e2.setText(periodlength);

        cycledays_edit = e1.getText().toString();
        perioddays_edit = e2.getText().toString();

        editor.putString("cycledays", cycledays_edit);
        editor.putString("periodlength", perioddays_edit);

        return view;
    }

    @Override
    public void onClick(View v) {
        e1 = (EditText)getActivity().findViewById(R.id.editText);
        e2 = (EditText)getActivity().findViewById(R.id.editText2);
        Log.i("e1",e1.getText().toString());
        cycledays_edit = e1.getText().toString();
        perioddays_edit = e2.getText().toString();

        editor.putString("cycledays", cycledays_edit);
        editor.commit();
        editor.putString("periodlength", perioddays_edit);
        editor.commit();
        editor.apply();
        e1.setText(sharedPreferences.getString("cycledays", ""));
        e2.setText(sharedPreferences.getString("periodlength", ""));
        e1.clearFocus();
        e2.clearFocus();



        // don't use an anonymous inner class here
        tabs.addOnTabSelectedListener(tabListener);
        TabLayout.Tab tab=tabs.getTabAt(0);
        viewPager.setCurrentItem(tab.getPosition());

    }

    TabLayout.OnTabSelectedListener tabListener = new TabLayout.OnTabSelectedListener() {

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            viewPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

    public void updateData(String cycledays_edit, String perioddays_edit) {
        e1 = (EditText)getActivity().findViewById(R.id.editText);
        e2 = (EditText)getActivity().findViewById(R.id.editText2);
        Log.i("e1",e1.getText().toString());
        cycledays_edit = e1.getText().toString();
        perioddays_edit = e2.getText().toString();

        editor.putString("cycledays", cycledays_edit);
        editor.commit();
        editor.putString("periodlength", perioddays_edit);
        editor.commit();
    }


}


