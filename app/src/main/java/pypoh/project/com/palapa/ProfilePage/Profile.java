package pypoh.project.com.palapa.ProfilePage;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

import pypoh.project.com.palapa.R;
import pypoh.project.com.palapa.Util.SectionsPageAdapter;

public class Profile extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }


    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Kiriman(), "Kiriman");
        adapter.addFragment(new Tab2Mengikuti(), "Mengikuti");
        adapter.addFragment(new Tab3Pengikut(), "Pengikut");
        viewPager.setAdapter(adapter);
    }
}
