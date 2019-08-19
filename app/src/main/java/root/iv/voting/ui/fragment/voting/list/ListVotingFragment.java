package root.iv.voting.ui.fragment.voting.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import root.iv.voting.App;
import root.iv.voting.R;
import root.iv.voting.db.voting.Voting;

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

        app = (App) this.getContext().getApplicationContext();
        List<Voting> votings = app.getDB().votingDAO().getAll();
        adapter = new VotingAdapter(votings, this::clickItem, getLayoutInflater());
        listVoting.setAdapter(adapter);
        listVoting.setLayoutManager(new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false));

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
        Toast.makeText(this.getContext(), adapter.getItem(index).getName(), Toast.LENGTH_SHORT).show();
    }
}
