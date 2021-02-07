package phone.vishnu.institute.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import phone.vishnu.institute.R;
import phone.vishnu.institute.model.CourseApplication;

public class ApplyFragment extends Fragment {

    private String studentName, courseInterested, studentEmail;
    private TextInputEditText nameTIE, courseTIE, emailTIE;
    private ImageView button;

    public ApplyFragment() {
    }

    public static ApplyFragment newInstance() {
        return new ApplyFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_apply, container, false);

        nameTIE = view.findViewById(R.id.applyNameTIE);
        courseTIE = view.findViewById(R.id.applyCourseTIE);
        emailTIE = view.findViewById(R.id.applyEmailTIE);
        button = view.findViewById(R.id.applySubmitButton);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentName = Objects.requireNonNull(nameTIE.getText()).toString().trim();
                courseInterested = Objects.requireNonNull(courseTIE.getText()).toString().trim();
                studentEmail = Objects.requireNonNull(emailTIE.getText()).toString().trim();

                if (studentName.isEmpty() || !studentEmail.contains("@") || !studentEmail.contains(".")) {

                    if (studentName.isEmpty()) {
                        nameTIE.setError("Field Empty");
                    } else if (courseInterested.isEmpty()) {
                        courseTIE.setError("Field Empty");
                    } else if (!studentEmail.contains("@") || !studentEmail.contains(".")) {
                        emailTIE.setError("Invalid Email");
                    }

                } else {

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("CourseApplication");

                    CourseApplication application = new CourseApplication(studentName, courseInterested, studentEmail);

                    reference.child(String.valueOf(System.currentTimeMillis())).setValue(application).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getActivity(), "Application Sent", Toast.LENGTH_SHORT).show();
                            requireActivity().onBackPressed();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), "Couldn't Sent Application", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }
}
