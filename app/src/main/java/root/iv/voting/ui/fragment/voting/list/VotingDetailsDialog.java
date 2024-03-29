package root.iv.voting.ui.fragment.voting.list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import root.iv.voting.App;
import root.iv.voting.R;
import root.iv.voting.db.DateStringConverter;
import root.iv.voting.db.target.Target;
import root.iv.voting.db.voting.Voting;

public class VotingDetailsDialog extends DialogFragment {
    private static final String ARG_ID = "arg:id";

    @BindView(R.id.viewVotingName)
    protected TextView viewVotingName;
    @BindView(R.id.viewVotingCreated)
    protected TextView viewVotingCreated;
    @BindView(R.id.listTargets)
    protected RecyclerView listTargets;
    private TargetAdapter adapter;
    private App app;
    private Listener listener;
    private long voatingID;

    public static VotingDetailsDialog getInstance(Voting voting) {
        VotingDetailsDialog dialog = new VotingDetailsDialog();
        Bundle bundle = new Bundle();
        bundle.putLong(ARG_ID, voting.getId());
        dialog.setArguments(bundle);

        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setTitle("Заголовок");
        getDialog().setCanceledOnTouchOutside(false);
        View view = inflater.inflate(R.layout.dialog_voting_detail, container, false);
        ButterKnife.bind(this, view);
        app = (App)getContext().getApplicationContext();

        Bundle args = getArguments();
        if (args != null) {
            voatingID = args.getLong(ARG_ID);
            Voting voting = app.getDB().votingDAO().findByID(voatingID);
            viewVotingName.setText(voting.getName());
            String created = DateStringConverter.toString(voting.getCreated());
            List<Target> targets = app.getDB().targetDAO().findForVoting(voatingID);
            adapter = new TargetAdapter(targets, getLayoutInflater(), this::clickItem);
            listTargets.setAdapter(adapter);
            listTargets.setLayoutManager(new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false));
            viewVotingCreated.setText(String.format("Создано: %s", created));
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Listener) {
            listener = (Listener) context;
        } else {
            throw new IllegalArgumentException("Невозможно создать фрагмент с таким контекстом. Требуется реализация интерфейса");
        }
    }

    public void clickItem(View view) {

    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @OnClick(R.id.buttonAddTargetToVoting)
    public void clickAddTargetToVoting() {
        dismiss();
        listener.clickAddTargetToVoating(voatingID);
    }

    @OnClick(R.id.buttonVote)
    public void clickVote() {
        dismiss();
        listener.clickVote(voatingID);
    }

    public interface Listener {
        void clickAddTargetToVoating(long vID);
        void clickVote(long vID);
    }

}
