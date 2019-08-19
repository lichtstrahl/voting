package root.iv.voting.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import root.iv.voting.ui.fragment.voting.CreateVotingFragment;
import root.iv.voting.ui.fragment.voting.list.ListVotingFragment;
import root.iv.voting.ui.fragment.voting.menu.MenuVotingFragment;
import root.iv.voting.R;

public class MainActivity extends AppCompatActivity
        implements
        MenuVotingFragment.Listener,
        CreateVotingFragment.Listener
{

    @BindView(R.id.viewContent)
    ViewGroup viewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupFragment(MenuVotingFragment.getInstance(), false);
    }

    @Override
    public void clickViewAllVotings() {
        setupFragment(ListVotingFragment.getInstance(), true);
    }

    @Override
    public void clickAddVoting() {
        setupFragment(CreateVotingFragment.getInstance(), true);
    }


    private void setupFragment(Fragment fragment, boolean backStack) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.viewContent, fragment);

        if (backStack) transaction.addToBackStack(null);

        transaction.commit();
    }
}
