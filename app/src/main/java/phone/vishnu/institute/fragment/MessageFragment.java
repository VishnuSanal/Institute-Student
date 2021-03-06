package phone.vishnu.institute.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import phone.vishnu.institute.R;
import phone.vishnu.institute.adapter.RecyclerViewAdapter;
import phone.vishnu.institute.model.Message;

public class MessageFragment extends Fragment {

    public MessageFragment() {
    }

    public static MessageFragment newInstance(String userId) {
        Bundle args = new Bundle();

        MessageFragment fragment = new MessageFragment();
        args.putSerializable("userId", userId);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_messages, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final ArrayList<Message> arr = new ArrayList<>();

        final RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), arr);

        RecyclerView recyclerView = view.findViewById(R.id.messageRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        DatabaseReference reference = database.getReference("Users").child((getArguments().getString("userId"))).child("Message");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    arr.add(singleSnapshot.getValue(Message.class));
                }
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
