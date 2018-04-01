package ru.job4j.generic;

public abstract class AbstractStore<E extends Base> {
    private SimpleArray<E> sArray = new SimpleArray<>(5);

    public void add(E model) {
        sArray.add(model);
    }

    public boolean replace(String id, E model) {
        boolean will = false;
        for (int index = 0; index < this.sArray.size(); ++index) {
            if (this.sArray.get(index).getId().equals(id)) {
                this.sArray.set(index, model);
                will = true;
                break;
            }
        }
        return will;
    }

    public boolean delete(String id) {
        boolean will = false;
        for (int index = 0; index < this.sArray.size(); ++index) {
            if (this.sArray.get(index).getId().equals(id)) {
                this.sArray.delete(index);
                will = true;
                break;
            }
        }
        return will;
    }
    
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
}
