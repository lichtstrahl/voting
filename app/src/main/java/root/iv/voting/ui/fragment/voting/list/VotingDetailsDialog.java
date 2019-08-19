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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import root.iv.voting.App;
import root.iv.voting.R;
import root.iv.voting.db.DateStringConverter;
import root.iv.voting.db.voting.Voting;

public class VotingDetailsDialog extends DialogFragment {
    private static final String ARG_ID = "arg:id";

    @BindView(R.id.viewVotingName)
    protected TextView viewVotingName;
    @BindView(R.id.viewVotingCreated)
    protected TextView viewVotingCreated;
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

    public interface Listener {
        void clickAddTargetToVoating(long vID);
    }

}
