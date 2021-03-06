package pypoh.project.com.palapa.Main2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import pypoh.project.com.palapa.Auth.LoginPage;
import pypoh.project.com.palapa.KatalogPage.Katalog;
import pypoh.project.com.palapa.KoinPage.Koin;
import pypoh.project.com.palapa.Post.KirimanBaru;
import pypoh.project.com.palapa.ProfilePage.Profile;
import pypoh.project.com.palapa.R;
import pypoh.project.com.palapa.Util.SectionsPageAdapter;
import pypoh.project.com.palapa.Util.SectionsPageAdapter11;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private SectionsPageAdapter11 mSectionsPageAdapter;

    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("Beranda");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mSectionsPageAdapter = new SectionsPageAdapter11(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_black_18dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_search_black_18dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_notifications_black_18dp);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_email_black_24dp);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab  = tabLayout.getTabAt(i);
            if (tab != null) tab.setCustomView(R.layout.icon_tab);
        }


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newPost = new Intent(Main2Activity.this, KirimanBaru.class);
                startActivity(newPost);
            }
        });
    }



    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter11 adapter = new SectionsPageAdapter11(getSupportFragmentManager());
        adapter.addFragment(new Tab1Timeline());
        adapter.addFragment(new Tab2Search());
        adapter.addFragment(new Tab3Notification());
        adapter.addFragment(new Tab4Chat());
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.profile) {
            Intent toProfile = new Intent(Main2Activity.this, Profile.class);
            startActivity(toProfile);
        } else if (id == R.id.katalog) {
            Intent toKatalog = new Intent(Main2Activity.this, Katalog.class);
            startActivity(toKatalog);
        } else if (id == R.id.koinsaya) {
            Intent toKoinSaya = new Intent(Main2Activity.this, Koin.class);
            startActivity(toKoinSaya);
        } else if (id == R.id.logout_navbar) {
            FirebaseAuth.getInstance().signOut();
            Intent toLogin = new Intent(Main2Activity.this, LoginPage.class);
            toLogin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(toLogin);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
