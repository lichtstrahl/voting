package root.iv.voting.utils.adapter;

import root.iv.voting.utils.RecyclerAdapter;

// Adapter-который поддерживает перетаскивание и смахивание элемент
public interface ItemTouchHelperAdapter<T> extends RecyclerAdapter<T> {
    boolean onItemMove(int from, int to);
    void onItemDismiss(int position);
}
