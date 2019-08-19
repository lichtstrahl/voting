package root.iv.voting.ui.fragment.voting.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;
import root.iv.voting.R;
import root.iv.voting.db.voting.Voting;
import root.iv.voting.utils.RecyclerAdapter;
import root.iv.voting.utils.adapter.ItemTouchHelperAdapter;

@AllArgsConstructor
public class VotingAdapter
        extends RecyclerView.Adapter<VotingAdapter.ViewHolder>
        implements ItemTouchHelperAdapter<Voting>

{
    private List<Voting> votings;
    private View.OnClickListener listener;
    private LayoutInflater inflater;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_voting, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(votings.get(position));
    }

    @Override
    public int getItemCount() {
        return votings.size();
    }

    @Override
    public Voting getItem(int i) {
        return votings.get(i);
    }

    @Override
    public void clear() {
        int count = votings.size();
        votings.clear();
        notifyItemRangeRemoved(0, count);
    }

    @Override
    public boolean onItemMove(int from, int to) {
        if (from < to) {
            for (int i = from; i < to; i++)
                Collections.swap(votings, i, i+1);
        } else {
            for (int i = from; i > to; i--)
                Collections.swap(votings, i, i-1);
        }
        notifyItemMoved(from, to);
        return true;
    }


    @Override
    public void onItemDismiss(int position) {
        votings.remove(position);
        notifyItemRemoved(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;

        public ViewHolder(@NonNull View item) {
            super(item);

            name = item.findViewById(R.id.votingName);
            item.setOnClickListener(listener);
        }

        public void bindView(Voting voting) {
            name.setText(voting.getName());
        }
    }
}
