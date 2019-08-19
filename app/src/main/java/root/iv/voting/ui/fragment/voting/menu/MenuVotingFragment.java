package root.iv.voting.ui.fragment.voting.menu;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import root.iv.voting.R;

public class MenuVotingFragment extends Fragment {
    private Listener listener;
    @BindView(R.id.itemList)
    protected LinearLayout itemList;

    private final MenuItemData[] ITEM_DATA = new MenuItemData[] {
            new MenuItemData(R.string.menu_item_add_voting, R.drawable.ic_plus, v -> listener.clickAddVoting()),
            new MenuItemData(R.string.menu_item_view_all_voting, R.drawable.ic_eye, v -> listener.clickViewAllVotings())
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_voting, container, false);
        ButterKnife.bind(this, view);


        // Создание пунктов меню
        for (int i = 0; i < ITEM_DATA.length; i++) {
            View item = inflater.inflate(R.layout.component_menu_item, null);
            itemList.addView(item);
            bindMenuItem(item, i);
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Listener) {
            listener = (Listener) context;
        } else {
            throw new IllegalStateException("Для создания фрагмента 'Меню Голосований' передан не подходящий контекст");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public static MenuVotingFragment getInstance() {
        MenuVotingFragment fragment = new MenuVotingFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    private void bindMenuItem(View item, int index) {
        CircleImageView image = item.findViewById(R.id.imageMenuVoting);
        image.setImageDrawable(getResources().getDrawable(ITEM_DATA[index].getImageID(), this.getContext().getTheme()));
        TextView text = item.findViewById(R.id.textMenuVoting);
        text.setText(getString(ITEM_DATA[index].getStringID()));
        itemList.getChildAt(index).setOnClickListener(ITEM_DATA[index].getListener());
    }

    public interface Listener {
        void clickViewAllVotings();
        void clickAddVoting();
    }
}
