package phone.vishnu.institute.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import phone.vishnu.institute.R;
import phone.vishnu.institute.fragment.AboutFragment;
import phone.vishnu.institute.fragment.ApplyFragment;
import phone.vishnu.institute.fragment.FeedbackFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        findViewById(R.id.signInButton).setOnClickListener(this);
        findViewById(R.id.aboutTV).setOnClickListener(this);
        findViewById(R.id.feedBackTV).setOnClickListener(this);
        findViewById(R.id.applyTV).setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            Intent i = new Intent(getApplicationContext(), StudentActivity.class);
            i.putExtra("userId", currentUser.getUid());
            i.putExtra("userName", currentUser.getDisplayName());
            startActivity(i);
            Toast.makeText(this, "Signing In...", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.signInButton)
            signInAction();
        else
            openFragment(id);
    }

    private void openFragment(int id) {

        if (id == R.id.aboutTV)
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, AboutFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        else if (id == R.id.feedBackTV)
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, FeedbackFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        else if (id == R.id.applyTV)
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, ApplyFragment.newInstance())
                    .addToBackStack(null)
                    .commit();

    }

    private void signInAction() {
        TextInputEditText studentIDEditText = findViewById(R.id.studentIDTIE);
        TextInputEditText passwordEditText = findViewById(R.id.studentPasswordTIE);

        String studentId = Objects.requireNonNull(studentIDEditText.getText()).toString().trim();
        String password = Objects.requireNonNull(passwordEditText.getText()).toString().trim();

        if (studentId.isEmpty() || password.isEmpty()) {
            if (studentId.isEmpty()) {
                studentIDEditText.setError("Field Empty");
                studentIDEditText.requestFocus();
            }
            if (password.isEmpty()) {
                passwordEditText.setError("Field Empty");
                passwordEditText.requestFocus();
            }
        } else {

            studentId += "@institute.com";

            ProgressDialog progressBar = new ProgressDialog(MainActivity.this);
            progressBar.setMessage("Please Wait");
            progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressBar.setProgress(0);
            progressBar.setMax(100);
            progressBar.show();

            firebaseAuth.signInWithEmailAndPassword(studentId, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user != null) {
                            Intent i = new Intent(getApplicationContext(), StudentActivity.class);
                            i.putExtra("userId", user.getUid());
                            i.putExtra("userName", user.getDisplayName());
                            startActivity(i);
                            finish();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            progressBar.dismiss();
        }
    }
}
