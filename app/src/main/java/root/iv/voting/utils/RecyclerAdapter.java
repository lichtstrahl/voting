package root.iv.voting.utils;

public interface RecyclerAdapter<T> {
    T getItem(int i);
    void clear();
}
