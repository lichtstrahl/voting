package root.iv.voting.ui.fragment.voting;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import root.iv.voting.App;
import root.iv.voting.R;
import root.iv.voting.db.voting.Voting;

public class CreateVotingFragment extends Fragment {

    @BindView(R.id.inputVotingName)
    protected EditText inputName;
    private App app;
    private Listener listener;

    public static Fragment getInstance() {
        return new CreateVotingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_voting, container, false);
        ButterKnife.bind(this, view);

        app = (App) this.getContext().getApplicationContext();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Listener) {
            listener = (Listener) context;
        } else {
            throw new IllegalArgumentException("Некорректный контекст для создания фрагмента. Нужно реализовать Listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @OnClick(R.id.buttonSaveVoting)
    public void clickSave() {
        Voting voting = Voting.create(inputName.getText().toString());
        app.getDB().votingDAO().insert(voting);
    }

    public interface Listener {
    }


}
