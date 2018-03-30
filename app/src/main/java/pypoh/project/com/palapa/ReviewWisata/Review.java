package pypoh.project.com.palapa.ReviewWisata;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import pypoh.project.com.palapa.Main2.Main2Activity;
import pypoh.project.com.palapa.ProfilePage.Tab1Kiriman;
import pypoh.project.com.palapa.ProfilePage.Tab2Mengikuti;
import pypoh.project.com.palapa.R;
import pypoh.project.com.palapa.Util.SectionsPageAdapter;
import pypoh.project.com.palapa.Util.SectionsPageAdapter11;

public class Review extends AppCompatActivity {

    private SectionsPageAdapter11 mSectionsPageAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        mSectionsPageAdapter = new SectionsPageAdapter11(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Prakata(), "Prakata");
        adapter.addFragment(new Tab2Review(), "Review");
        viewPager.setAdapter(adapter);
    }
}

