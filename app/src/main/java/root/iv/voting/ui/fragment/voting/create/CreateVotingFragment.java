package root.iv.voting.ui.fragment.voting.create;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import root.iv.voting.App;
import root.iv.voting.R;
import root.iv.voting.db.voting.Voting;
import timber.log.Timber;

public class CreateVotingFragment extends Fragment {
    private static final String NEED_UNIQUE_NAME = "Требуется уникальное имя для нового голосования";

    @BindView(R.id.inputVotingName)
    protected EditText inputName;
    @BindView(R.id.buttonSaveVoting)
    protected Button buttonSave;
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
        inputName.addTextChangedListener(new ChangeTextListener());
        getActivity().setTitle(R.string.title_create_voting);
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
        try {
            app.getDB().votingDAO().insert(voting);
            this.getActivity().onBackPressed();
        } catch (SQLiteConstraintException e) {
            Toast.makeText(this.getContext(), NEED_UNIQUE_NAME, Toast.LENGTH_SHORT).show();
            Timber.e(e, NEED_UNIQUE_NAME);
            buttonSave.setEnabled(false);
        }
    }

    public interface Listener {
    }

    private class ChangeTextListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            buttonSave.setEnabled(!s.toString().isEmpty());
        }
    }
}
