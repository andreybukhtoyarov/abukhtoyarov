package ru.job4j.generic;

/**
 * This class contains the general methods for classes extended class Base.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public abstract class AbstractStore<E extends Base> implements Store<E> {
    /**
     * Store for E extends Base. Based on SimpleArray.
     */
    private final SimpleArray<E> sArray = new SimpleArray<>(5);

    @Override
    public void add(E model) {
        sArray.add(model);
    }

    @Override
    public boolean replace(String id, E model) {
        boolean replace = false;
        int index = findIndex(id);
        if (index >= 0) {
            this.sArray.set(index, model);
            replace = true;
        }
        return replace;
    }

    @Override
    public boolean delete(String id) {
        boolean delete = false;
        int index = findIndex(id);
        if (index >= 0) {
            this.sArray.delete(index);
            delete = true;
        }
        return delete;
    }

    @Override
    public E findById(String id) {
        E result = null;
        for (E element : this.sArray) {
            if (element.getId().equals(id)) {
                result = element;
                break;
            }
        }
        return result;
    }

    /**
     * Find index of element in SimpleArray by id.
     * @param id id of element.
     * @return index of element.
     */
    private int findIndex(String id) {
        int index = -1;
        for (int position = 0; position < this.sArray.size(); ++position) {
            if (this.sArray.get(position).getId().equals(id)) {
                index = position;
                break;
            }
        }
        return index;
    }

    /**
     * Return size of store.
     * @return size of store.
     */
    public int size() {
        return this.sArray.size();
    }
}
