package com.harambe.devfetch.mainActivity_MVP;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.harambe.devfetch.NetworkPojos.Rants;
import com.harambe.devfetch.R;
import com.harambe.devfetch.mainActivity_MVP.Fragment.CommunicatorInterface;
import com.harambe.devfetch.mainActivity_MVP.Fragment.RantsViewFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView,CommunicatorInterface {

    private static final String BUNDLE_FRAG = "BUNDLE_FRAG";
    private MainPresenterInterface mPresenter;
    public static final String BUNDLE_KEY="BUNDLE_KEY";

    @OnClick(R.id.fab)
    void onFabClicked() {

    }

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    Fragment mFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if(savedInstanceState!=null){
            mFrag=getSupportFragmentManager().getFragment(savedInstanceState,BUNDLE_FRAG);
        }else {
            mFrag = new RantsViewFragment();
        }
        showRantsView();
        mPresenter = new MainPresenter(this);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(BUNDLE_KEY,mPresenter.getFeedForBundle());
        getSupportFragmentManager().putFragment(outState,BUNDLE_FRAG,mFrag);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mPresenter.setHomeFeedFromBundle(savedInstanceState.<Rants>getParcelableArrayList(BUNDLE_KEY));
    }

    private void showRantsView() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.cm_fl_frags_holder, mFrag)
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

    @Override
    public MainPresenterInterface getPresenter() {
        return mPresenter;
    }

    @Override
    public void notifyChange(int size) {
        if(size==0){
            Toast.makeText(this, "No changes", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,size+" new items",Toast.LENGTH_SHORT).show();
        }
    }
}
