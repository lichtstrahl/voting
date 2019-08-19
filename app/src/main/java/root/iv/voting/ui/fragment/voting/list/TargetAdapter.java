package root.iv.voting.ui.fragment.voting.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

import lombok.AllArgsConstructor;
import root.iv.voting.R;
import root.iv.voting.db.target.Target;
import root.iv.voting.utils.RecyclerAdapter;

@AllArgsConstructor
public class TargetAdapter extends RecyclerView.Adapter<TargetAdapter.ViewHolder> implements RecyclerAdapter<Target> {
    private List<Target> targets;
    private LayoutInflater inflater;
    private View.OnClickListener listener;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_target, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(targets.get(position));
    }

    @Override
    public int getItemCount() {
        return targets.size();
    }

    @Override
    public Target getItem(int i) {
        return targets.get(i);
    }

    @Override
    public void clear() {
        int count = getItemCount();
        targets.clear();
        notifyItemRangeRemoved(0, count);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView viewName;
        private TextView viewDesc;
        private TextView viewVote;

        public ViewHolder(@NonNull View item) {
            super(item);

            viewName = item.findViewById(R.id.viewTargetName);
            viewDesc = item.findViewById(R.id.viewTargetDesc);
            viewVote = item.findViewById(R.id.viewTargetVote);

            item.setOnClickListener(listener);
        }

        public void bindView(Target target) {
            viewName.setText(target.getName());
            viewDesc.setText(target.getDesc());
            viewVote.setText(String.format(Locale.ENGLISH,"%d", target.getVote()));
        }
    }
}
