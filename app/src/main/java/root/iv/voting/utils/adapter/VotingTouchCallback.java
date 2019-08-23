package root.iv.voting.utils.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import root.iv.voting.db.voting.Voting;
import timber.log.Timber;

public class VotingTouchCallback extends ItemTouchHelper.Callback {
    private Consumer<Integer> swiped;
    private BiConsumer<Integer, Integer> move;
    private boolean enableSwipe;
    private boolean enableMove;

    private static void initVertical(VotingTouchCallback callback, BiConsumer<Integer, Integer> move) {
        callback.move = move;
        callback.enableMove = true;
    }

    private static void initHorizontal(VotingTouchCallback callback, Consumer<Integer> swipe) {
        callback.swiped = swipe;
        callback.enableSwipe = true;
    }

    public static VotingTouchCallback newVerticalCallback(BiConsumer<Integer, Integer> move) {
        VotingTouchCallback callback = new VotingTouchCallback();
        initVertical(callback, move);
        return callback;
    }

    public static VotingTouchCallback newHorizontalCallback(Consumer<Integer> swipe) {
        VotingTouchCallback callback = new VotingTouchCallback();
        initHorizontal(callback, swipe);
        return callback;
    }

    public static VotingTouchCallback newMixedCallback(Consumer<Integer> swipe, BiConsumer<Integer, Integer> move) {
        VotingTouchCallback callback = new VotingTouchCallback();
        initHorizontal(callback, swipe);
        initVertical(callback, move);
        return callback;
    }

    // Разрешаем по долгому нажатию активировать перетаскивание
    @Override
    public boolean isLongPressDragEnabled() {
        return enableMove;
    }

    // Разрешаем смахивание по касанию
    @Override
    public boolean isItemViewSwipeEnabled() {
        return enableSwipe;
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
