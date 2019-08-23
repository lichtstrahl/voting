package root.iv.voting.ui.fragment.target.create;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import root.iv.voting.App;
import root.iv.voting.R;
import root.iv.voting.db.target.Target;
import root.iv.voting.db.voting.Voting;
import root.iv.voting.db.voting_target.VotingTarget;

public class CreateTargetFragment extends Fragment {
    private static final String ARG_ID = "arg:id";

    @BindView(R.id.inputTargetName)
    protected EditText inputTargetName;
    @BindView(R.id.inputTargetDesc)
    protected EditText inputTargetDesc;
    @BindView(R.id.viewVotingName)
    protected TextView viewVotingName;
    private App app;
    private Voting currentVoting;
    private Listener listener;

    public static CreateTargetFragment getInstance(long vID) {
        CreateTargetFragment fragment = new CreateTargetFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(ARG_ID, vID);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_target, container, false);
        ButterKnife.bind(this, view);
        this.getActivity().setTitle(R.string.title_create_target);

        currentVoting = app.getDB().votingDAO().findByID(getArguments().getLong(ARG_ID));
        viewVotingName.setText(currentVoting.getName());

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        app = (App)getContext().getApplicationContext();

        if (context instanceof Listener) {
            listener = (Listener) context;
        } else {
            throw new IllegalArgumentException("Контекст должен реализовывать интерфейс");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @OnClick(R.id.buttonSaveTarget)
    public void clickSave() {
        Target target = Target.create(inputTargetName.getText().toString(), inputTargetDesc.getText().toString());
        long id = app.getDB().targetDAO().insert(target);
        Target created = app.getDB().targetDAO().findByID(id);
        app.getDB().votingTargetDAO().insert(VotingTarget.create(currentVoting, created));
        listener.clickSave();
    }

    public interface Listener {
        void clickSave();
    }
}
