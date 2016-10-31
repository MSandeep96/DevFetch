package com.harambe.devfetch.mainActivity_MVP;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.harambe.devfetch.R;
import com.harambe.devfetch.mainActivity_MVP.Fragment.RantsViewFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView{

    @OnClick(R.id.fab)
    void onFabClicked(){

    }

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    RantsViewFragment mFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        mFrag=new RantsViewFragment();
        showRantsView();
    }

    private void showRantsView() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.cm_fl_frags_holder,mFrag)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
