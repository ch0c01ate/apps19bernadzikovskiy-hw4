package ua.edu.ucu.utils.immutable;

public final class ImmutableArrayList implements ImmutableList {
    private Object[] array;
    private static int NOTFOUNDSTATUS = -1;

    public ImmutableArrayList() {
        this.array = new Object[0];
    }

    public ImmutableArrayList(Object[] a) {
        this.array = a.clone();
    }

    @Override
    public ImmutableArrayList add(Object e) {
        return this.add(this.size(), e);
    }

    @Override
    public ImmutableArrayList add(int index, Object e) {
        return this.addAll(index, new Object[]{e});
    }

    @Override
    public ImmutableArrayList addAll(Object[] c) {
        return this.addAll(this.size(), c);
    }

    @Override
    public ImmutableArrayList addAll(int index, Object[] c) {
        Object[] resultArray = new Object[this.size() + c.length];
        System.arraycopy(array, 0, resultArray, 0, index);
        System.arraycopy(c, 0, resultArray, index, c.length);
        System.arraycopy(array, index, resultArray, index + c.length,
                this.size() - index);
        return new ImmutableArrayList(resultArray);
    }

    @Override
    public Object get(int index) {
        checkIfIndexOutOfBounds(index);
        return array[index];
    }

    @Override
    public ImmutableArrayList remove(int index) {
        checkIfIndexOutOfBounds(index);

        Object[] resultArray = new Object[this.size() - 1];
        System.arraycopy(array, 0, resultArray, 0, index);
        System.arraycopy(array, index + 1, resultArray, index, this.size() - index - 1);
        return new ImmutableArrayList(resultArray);
    }

    @Override
    public ImmutableArrayList set(int index, Object e) {
        checkIfIndexOutOfBounds(index);

        Object[] resultArray = array.clone();
        resultArray[index] = e;
        return new ImmutableArrayList(resultArray);
    }

    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(e)) {
                return i;
            }
        }
        return NOTFOUNDSTATUS;
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public ImmutableArrayList clear() {
        return new ImmutableArrayList();
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public Object[] toArray() {
        return array.clone();
    }

    private void checkIfIndexOutOfBounds(int index) {
        if (index >= this.size() || this.size() == 0 || index < 0) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
    }

    public ImmutableArrayList addLast(Object e) {
        return this.add(e);
    }

    public Object getFirst() {
        return this.get(0);
    }

    public ImmutableArrayList removeFirst() {
        return this.remove(0);
    }
}
