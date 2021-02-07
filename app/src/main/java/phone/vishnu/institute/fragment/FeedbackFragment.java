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
import phone.vishnu.institute.model.Feedback;


public class FeedbackFragment extends Fragment {
    private TextInputEditText feedbackEditText, nameEditText, emailEditText;
    private String feedBack, name, email;
    private ImageView imageView;

    public FeedbackFragment() {
    }

    public static FeedbackFragment newInstance() {
        return new FeedbackFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        nameEditText = view.findViewById(R.id.feedBackNameTIE);
        emailEditText = view.findViewById(R.id.feedBackEmailTIE);
        feedbackEditText = view.findViewById(R.id.feedBackFeedbackTIE);
        imageView = view.findViewById(R.id.feedbackSubmitIV);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                feedBack = Objects.requireNonNull(feedbackEditText.getText()).toString().trim();
                name = Objects.requireNonNull(nameEditText.getText()).toString().trim();
                email = Objects.requireNonNull(emailEditText.getText()).toString().trim();

                if (feedBack.isEmpty() || name.isEmpty() || email.isEmpty() || !email.contains("@") || !email.contains(".")) {

                    if (name.isEmpty()) {
                        feedbackEditText.setError("Field Empty");
                    } else if (email.isEmpty()) {
                        nameEditText.setError("Field Empty");
                    } else if (feedBack.isEmpty()) {
                        emailEditText.setError("Field Empty");
                    } else if (!email.contains("@") || !email.contains(".")) {
                        emailEditText.setError("Invalid Email");
                    }

                } else {

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Feedback");

                    Feedback feedback = new Feedback(email, feedBack, name);

                    reference.child(String.valueOf(System.currentTimeMillis())).setValue(feedback).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getActivity(), "Feedback Sent", Toast.LENGTH_SHORT).show();
                            requireActivity().onBackPressed();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), "Couldn't Sent Feedback", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });


        super.onViewCreated(view, savedInstanceState);

    }
}
