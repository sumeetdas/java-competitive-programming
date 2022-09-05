package sumeetdas.javacp.datastructures.queues;

@SuppressWarnings("unchecked")
public class ArrayQueue<T> implements IQueue<T> {
    private static final int MINIMUM_SIZE = 1024;

    private T[] array = (T[]) new Object[MINIMUM_SIZE];
    private int lastIndex = 0;
    private int firstIndex = 0;

    public T[] getArray() {
        return array;
    }
    public int getFirstIndex() {
        return firstIndex;
    }
    public int getLastIndex() {
        return lastIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean offer(T value) {
        if (size() >= array.length)
            grow(size());

        array[lastIndex % array.length] = value;
        lastIndex++;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T poll() {
        int size = lastIndex - firstIndex;
        if (size < 0) return null;

        T t = array[firstIndex % array.length];
        array[firstIndex % array.length] = null;
        firstIndex++;

        size = lastIndex - firstIndex;
        if (size <= 0) {
            // Removed last element
            lastIndex = 0;
            firstIndex = 0;
        }

        int shrinkSize = array.length>>1;
        if (shrinkSize >= MINIMUM_SIZE && size < shrinkSize)
            shrink();

        return t;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T peek() {
        return array[firstIndex % array.length];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(T value) {
        for (int i=0; i < array.length; i++) {
            T obj = array[i];
            // if obj is null, it should return false (not NPE)
            if (value.equals(obj)) return remove(i);
        }
        return false;
    }

    public boolean remove(int index) {
        if (index<0 || index >= array.length) return false;
        if (index==firstIndex) return (poll()!=null);

        int adjIndex = index % array.length;
        int adjLastIndex = (lastIndex-1) % array.length;
        if (adjIndex != adjLastIndex) {
            // Shift the array down one spot
            System.arraycopy(array, index+1, array, index, (array.length - (index+1)));
            if (adjLastIndex < firstIndex) {
                //Wrapped around array
                array[array.length-1] = array[0];
                System.arraycopy(array, 1, array, 0, firstIndex-1);
            }
        }
        array[adjLastIndex] = null;

        int shrinkSize = array.length>>1;
        if (shrinkSize >= MINIMUM_SIZE && size() < shrinkSize)
            shrink();

        lastIndex--;
        return true;
    }

    // Triple the size of the underlying array and rearrange to make sequential
    private void grow(int size) {
        int growSize = (size + (size<<1));
        T[] temp = (T[]) new Object[growSize];
        // Since the array can wrap around, make sure you grab the first chunk 
        int adjLast = lastIndex % array.length;
        if (adjLast > 0 && adjLast <= firstIndex) {
            System.arraycopy(array, 0, temp, array.length-adjLast, adjLast);
        }
        // Copy the remaining
        System.arraycopy(array, firstIndex, temp, 0, array.length - firstIndex);
        array = null;
        array = temp;
        lastIndex = (lastIndex - firstIndex);
        firstIndex = 0;
    }

    // Shrink the array by 50% and rearrange to make sequential
    private void shrink() {
        var shrinkSize = array.length>>1;
        var temp = (T[]) new Object[shrinkSize];
        // Since the array can wrap around, make sure you grab the first chunk 
        int adjLast = lastIndex % array.length;
        int endIndex = (lastIndex>array.length) ? array.length : lastIndex;
        if (adjLast <= firstIndex) {
            System.arraycopy(array, 0, temp, array.length - firstIndex, adjLast);
        }
        // Copy the remaining
        System.arraycopy(array, firstIndex, temp, 0, endIndex-firstIndex);
        array = null;
        array = temp;
        lastIndex = (lastIndex - firstIndex);
        firstIndex = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        firstIndex = 0;
        lastIndex = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(T value) {
        for (int i=0; i < array.length; i++) {
            T obj = array[i];
            // if obj is null, it should return false (not NPE)
            if (value.equals(obj)) return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validate() {
        if (size()==0) return true;

        int localSize = 0;
        int realFirst = firstIndex;
        if (firstIndex>array.length) 
            realFirst = firstIndex%array.length;
        int realLast = lastIndex;
        if (lastIndex>array.length) 
            realLast = lastIndex%array.length;
        for (int i=0; i<array.length; i++) {
            T t = array[i];
            if ((realFirst==realLast) || 
                (realFirst<realLast && (i>=realFirst && i<realLast)) || 
                (realLast<realFirst && (i<realLast || i>=realFirst))
            ) {
                if (t==null)
                    return false;
                localSize++;
            } else {
                if (t!=null)
                    return false;
            }
        }
        return (localSize==size());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return lastIndex - firstIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.util.Queue<T> toQueue() {
        return (new JavaCompatibleArrayQueue<T>(this));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.util.Collection<T> toCollection() {
        return (new JavaCompatibleArrayQueue<T>(this));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = lastIndex - 1; i >= firstIndex; i--) {
            builder.append(array[i%array.length]).append(", ");
        }
        return builder.toString();
    }
}
