package root.iv.voting.utils.adapter;

public interface RecyclerAdapter<T> {
    T getItem(int i);
    void clear();
}
