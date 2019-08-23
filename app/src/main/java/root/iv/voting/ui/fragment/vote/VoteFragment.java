package root.iv.voting.ui.fragment.vote;

import android.content.Context;
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

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import root.iv.voting.App;
import root.iv.voting.R;
import root.iv.voting.db.target.Target;
import root.iv.voting.db.voting.Voting;
import root.iv.voting.ui.fragment.voting.list.TargetAdapter;
import timber.log.Timber;

public class VoteFragment extends Fragment {
    private static final String ARG_ID = "arg:id";

    @BindView(R.id.listTargets)
    protected RecyclerView listTargets;
    private TargetAdapter targetAdapter;
    private Voting voting;
    private App app;

    public static VoteFragment getInstance(long vID) {
        VoteFragment fragment = new VoteFragment();
        Bundle bundle = new Bundle();

        bundle.putLong(ARG_ID, vID);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vote, container, false);
        ButterKnife.bind(this, view);

        Bundle args = getArguments();
        if (args != null) {
            long votingID = args.getLong(ARG_ID);
            voting = app.getDB().votingDAO().findByID(votingID);
            initTargetList(votingID);
        }

        return view;
    }

    private void initTargetList(long votingID) {
        targetAdapter = new TargetAdapter(app.getDB().targetDAO().findForVoting(votingID), getLayoutInflater(), this::itemClick);
        listTargets.setAdapter(targetAdapter);
        listTargets.setLayoutManager(new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        app = (App) context.getApplicationContext();
    }

    private void itemClick(View v) {
        int pos = listTargets.getChildAdapterPosition(v);
        Target target = targetAdapter.getItem(pos);
        vote(target);
        targetAdapter.notifyItemChanged(pos);
    }

    private void vote(Target target) {
        target.vote();
        app.getDB().targetDAO().update(target);
        Timber.i("Add vote for target with id = %d", target.getId());
    }
}
