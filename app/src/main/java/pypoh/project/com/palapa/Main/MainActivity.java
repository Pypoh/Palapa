package pypoh.project.com.palapa.Main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import pypoh.project.com.palapa.Main2.Tab1Timeline;
import pypoh.project.com.palapa.Main2.Tab2Search;
import pypoh.project.com.palapa.Main2.Tab3Notification;
import pypoh.project.com.palapa.Main2.Tab4Chat;
import pypoh.project.com.palapa.R;
import pypoh.project.com.palapa.Util.SectionsPageAdapter11;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private SectionsPageAdapter11 mSectionsPageAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionsPageAdapter = new SectionsPageAdapter11(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter11 adapter = new SectionsPageAdapter11(getSupportFragmentManager());
        adapter.addFragment(new Tab1Timeline());
        adapter.addFragment(new Tab2Search());
        adapter.addFragment(new Tab3Notification());
        adapter.addFragment(new Tab4Chat());
        viewPager.setAdapter(adapter);
    }
}
