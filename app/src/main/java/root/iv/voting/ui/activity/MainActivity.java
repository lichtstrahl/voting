package root.iv.voting.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import root.iv.voting.ui.fragment.target.create.CreateTargetFragment;
import root.iv.voting.ui.fragment.vote.VoteFragment;
import root.iv.voting.ui.fragment.voting.create.CreateVotingFragment;
import root.iv.voting.ui.fragment.voting.list.ListVotingFragment;
import root.iv.voting.ui.fragment.voting.list.VotingDetailsDialog;
import root.iv.voting.ui.fragment.voting.menu.MenuVotingFragment;
import root.iv.voting.R;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity
        implements
        MenuVotingFragment.Listener,
        CreateVotingFragment.Listener,
        VotingDetailsDialog.Listener,
        CreateTargetFragment.Listener,
        ListVotingFragment.Listener
{

    @BindView(R.id.viewContent)
    ViewGroup viewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupFragment(ListVotingFragment.getInstance(), false);
    }

    @Override
    public void clickViewAllVotings() {
        setupFragment(ListVotingFragment.getInstance(), true);
    }

    @Override
    public void clickAddVoting() {
        setupFragment(CreateVotingFragment.getInstance(), true);
    }

    @Override
    public void clickAddTargetToVoating(long vID) {
        setupFragment(CreateTargetFragment.getInstance(vID), true);
    }

    @Override
    public void clickCreateVoting() {
        setupFragment(CreateVotingFragment.getInstance(), true);
    }

    @Override
    public void clickSave() {
        onBackPressed();
    }

    @Override
    public void clickVote(long vID) {
        setupFragment(VoteFragment.getInstance(vID), true);
    }

    private void setupFragment(Fragment fragment, boolean backStack) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.viewContent, fragment);

        if (backStack) transaction.addToBackStack(null);

        transaction.commit();
    }
}
