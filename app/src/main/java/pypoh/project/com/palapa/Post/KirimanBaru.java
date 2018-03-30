package pypoh.project.com.palapa.Post;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import pypoh.project.com.palapa.R;
import pypoh.project.com.palapa.ReviewWisata.Review;

public class KirimanBaru extends AppCompatActivity {

    private Button mBtnReview;
    private Button mBtnCatatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiriman_baru);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBtnReview = (Button) findViewById(R.id.btn_newReview);
        mBtnCatatan = (Button) findViewById(R.id.btn_newCatatan);

        mBtnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toReview = new Intent (KirimanBaru.this, Review.class);
                startActivity(toReview);
            }
        });

        mBtnCatatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(KirimanBaru.this, "Belom Jadi", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
