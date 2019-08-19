package root.iv.voting.ui.fragment.voting.menu;

import android.view.View;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuItemData {
    private int stringID;
    private int imageID;
    private View.OnClickListener listener;

}
