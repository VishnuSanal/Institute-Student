package phone.vishnu.institute.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import phone.vishnu.institute.R;
import phone.vishnu.institute.fragment.MessageFragment;
import phone.vishnu.institute.fragment.NoticeBoardFragment;

public class StudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);

        getSupportFragmentManager().beginTransaction().add(R.id.studentContainer,
                MessageFragment.newInstance(getIntent().getStringExtra("userId"))).commit();
        bottomNavigationView.setSelectedItemId(R.id.navigation_messages);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                        Fragment selectedFragment = null;

                        int i = menuItem.getItemId();

                        if (i == R.id.navigation_dashboard) {

                            selectedFragment = NoticeBoardFragment.newInstance();

                        } else if (i == R.id.navigation_messages) {
                            selectedFragment = MessageFragment.newInstance(getIntent().getStringExtra("userId"));
                        }
                        if (selectedFragment != null) {
                            getSupportFragmentManager().beginTransaction().replace(R.id.studentContainer, selectedFragment).commit();
                        }
                        return true;
                    }
                });

    }
}
