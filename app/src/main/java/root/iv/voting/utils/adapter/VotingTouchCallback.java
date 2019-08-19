package root.iv.voting.utils.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import lombok.AllArgsConstructor;
import timber.log.Timber;

@AllArgsConstructor
public class VotingTouchCallback extends ItemTouchHelper.Callback {
    private Consumer<Integer> swiped;
    private BiConsumer<Integer, Integer> move;

    // Разрешаем по долгому нажатию активировать перетаскивание
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    // Разрешаем смахивание по касанию
    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }


    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        // Метод, определяющие какие флаги показывают swipe и drag
        // Разрешаем смахивание право-лево и перетаскивание верх-вниз
        int verticalFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int horizontalFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(verticalFlags, horizontalFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        try {
            move.accept(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        } catch (Exception e) {
            Timber.e(e);
        }
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        try {
            swiped.accept(viewHolder.getAdapterPosition());
        } catch (Exception e) {
            Timber.e(e);
        }
    }
}
