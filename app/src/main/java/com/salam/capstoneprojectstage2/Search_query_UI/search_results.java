package com.salam.capstoneprojectstage2.Search_query_UI;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.salam.capstoneprojectstage2.R;

public class search_results extends AppCompatActivity {


 String date_min, date_max;

    String Keyword_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        getSupportActionBar().setTitle(getString(R.string.RESULTS));
        //INTENTS TO FRAGMENT FOR SEARCH QUERY
        Intent query_parameters = getIntent();
       date_min = query_parameters.getStringExtra(getString(R.string.DATE_MIN_S));
       date_max = query_parameters.getStringExtra(getString(R.string.DATE_MAX_S));
        Keyword_search = query_parameters.getStringExtra(getString(R.string.KEY_WORD_SS));
        addFragment(new query_results_fragment(), false, "one");
    }

    public void addFragment(query_results_fragment fragment, boolean addToBackStack, String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        if (addToBackStack) {
            ft.addToBackStack(tag);
        }
        ft.replace(R.id.container, fragment, tag);
        ft.commitAllowingStateLoss();
        Bundle bundle = new Bundle();
        bundle.putString(getString(R.string.DATE_MIN_S), date_min);
        bundle.putString(getString(R.string.DATE_MAX_S), date_max);
        bundle.putString(getString(R.string.KEY_WORD_SS), Keyword_search);

        fragment.setArguments(bundle);
    }


}
