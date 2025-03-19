package az;

import java.util.List;

public interface Process<T> {

    void add(List<T> list);
    void getID();
    void uptade(T item);
    void delete();
    void findbyname();
}
