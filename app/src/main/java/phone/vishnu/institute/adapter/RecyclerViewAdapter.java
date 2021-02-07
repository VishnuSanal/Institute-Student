package phone.vishnu.institute.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import phone.vishnu.institute.R;
import phone.vishnu.institute.model.Message;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final ArrayList<Message> arrayList;
    private final Context context;

    public RecyclerViewAdapter(Context context, ArrayList<Message> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_item_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Message message = arrayList.get(i);
        viewHolder.titleTV.setText((message.getTitle()));
        viewHolder.messageTV.setText((message.getMessage()));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleTV, messageTV;

        ViewHolder(View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.firstTvCard);
            messageTV = itemView.findViewById(R.id.secondTvCard);
        }
    }
}
