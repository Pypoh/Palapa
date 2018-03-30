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
import pypoh.project.com.palapa.Util.SectionsPageAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Timeline(), "Timeline");
        adapter.addFragment(new Tab2Search(), "Search");
        adapter.addFragment(new Tab3Notification(), "Notif");
        adapter.addFragment(new Tab4Chat(), "Chat");
        viewPager.setAdapter(adapter);
    }
}
