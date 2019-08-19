package root.iv.voting.ui.fragment.voting.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import root.iv.voting.App;
import root.iv.voting.R;
import root.iv.voting.db.voting.Voting;
import root.iv.voting.utils.adapter.VotingTouchCallback;

public class ListVotingFragment extends Fragment {

    @BindView(R.id.listVoting)
    protected RecyclerView listVoting;
    private VotingAdapter adapter;
    private App app;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_voting_all, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle(R.string.title_all_voting);
        app = (App) this.getContext().getApplicationContext();
        List<Voting> votings = app.getDB().votingDAO().getAll();
        initRecyclerView(votings);
        return view;
    }

    public static ListVotingFragment getInstance() {
        ListVotingFragment fragment = new ListVotingFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    public void clickItem(View view) {
        int index = listVoting.indexOfChild(view);
        Voting voting = adapter.getItem(index);
        VotingDetailsDialog dialog = VotingDetailsDialog.getInstance(voting);
        dialog.show(getChildFragmentManager(), "detailDialog");
    }

    private void initRecyclerView(List<Voting> votings) {
        adapter = new VotingAdapter(votings, this::clickItem, getLayoutInflater());
        ItemTouchHelper touchHelper = new ItemTouchHelper(new VotingTouchCallback(this::onDismiss, this::onMoveItem));
        touchHelper.attachToRecyclerView(listVoting);
        listVoting.setAdapter(adapter);
        listVoting.setLayoutManager(new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false));
    }

    private void onMoveItem(int from, int to) {
        adapter.notifyItemMoved(from, to);
    }

    private void onDismiss(int pos) {
        Voting voting = adapter.getItem(pos);
        app.getDB().votingTargetDAO().deleteForVoting(voting.getId());
        app.getDB().votingDAO().delete(voting);
        adapter.onItemDismiss(pos);

    }
}
