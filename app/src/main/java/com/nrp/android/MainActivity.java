package com.nrp.android;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import com.nrp.android.base.BaseActivity;
import com.nrp.android.fragment.HomeFragment;
import com.nrp.android.fragment.MainFragment;
import com.nrp.android.utils.SnackBarUtils;

import butterknife.Bind;


public class MainActivity extends BaseActivity {

    public static final String MAIN_FRAGMENT_TAG = "main_fragment_tag";
    public static final String HOME_FRAGMENT_TAG = "home_fragment_tag";

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.navigation_view)
    NavigationView mNavigationView;

    // 菜单开关
    private ActionBarDrawerToggle mDrawerToggle;

    private FragmentManager mFragmentManager;
    private Fragment mCurrentFragment;
    private MenuItem mPreMenuItem;

    private long lastBackKeyDownTick = 0;
    public static final long MAX_DOUBLE_BACK_DURATION = 1500;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getContentId() {
        return R.id.frame_content;
    }

    @Override
    protected void init() {
        super.init();
        mFragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void onApplyData() {
        super.onApplyData();

        mToolbar.setTitle(getResources().getString(R.string.app_name));

        // 这句一定要在下面几句之前调用，不然就会出现点击无反应
        setSupportActionBar(mToolbar);
        setNavigationViewItemClickListener();
        // ActionBarDrawerToggle配合Toolbar，实现Toolbar上菜单按钮开关效果。
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mToolbar.setNavigationIcon(R.drawable.ic_drawer_home);

        replaceFragment(MainFragment.class , MAIN_FRAGMENT_TAG);
    }

    private void setNavigationViewItemClickListener() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (null != mPreMenuItem) {
                    mPreMenuItem.setChecked(false);
                }
                switch (item.getItemId()) {
                    case R.id.navigation_item_home:
                        mToolbar.setTitle(getResources().getString(R.string.navigation_item_home_text));
                        replaceFragment(HomeFragment.class , HOME_FRAGMENT_TAG);
                        break;
                    case R.id.navigation_item_post:
                        mToolbar.setTitle(getResources().getString(R.string.navigation_item_post_text));
                        replaceFragment(HomeFragment.class , HOME_FRAGMENT_TAG);
                        break;
                    case R.id.navigation_item_contact:
                        mToolbar.setTitle(getResources().getString(R.string.navigation_item_contact_text));
                        replaceFragment(HomeFragment.class , HOME_FRAGMENT_TAG);
                        break;
                    case R.id.navigation_item_setting:
                        mToolbar.setTitle(getResources().getString(R.string.navigation_item_setting_text));
                        replaceFragment(HomeFragment.class , HOME_FRAGMENT_TAG);
                        break;
                    default:
                        break;
                }
                item.setChecked(true);
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                mPreMenuItem = item;
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        // 当前抽屉是打开的，则关闭
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
            return;
        }

        long currentTick = System.currentTimeMillis();
        if (currentTick - lastBackKeyDownTick > MAX_DOUBLE_BACK_DURATION) {
            SnackBarUtils.makeShort(mDrawerLayout,
                    getResources().getString(R.string.main_back_finish_tips)).success();
            lastBackKeyDownTick = currentTick;
        } else {
            finish();
            System.exit(0);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_toolbar, menu);
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
            // startActivityWithoutExtras(SettingActivity.class);
        } else if (id == R.id.action_about) {
            // startActivityWithoutExtras(AboutActivity.class);
        }


        return super.onOptionsItemSelected(item);
    }
}
