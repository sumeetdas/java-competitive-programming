package sumeetdas.javacp.datastructures.queues;

@SuppressWarnings("unchecked")
public class JavaCompatibleLinkedQueue<T> extends java.util.AbstractQueue<T> {
    private LinkedQueue<T> queue = null;

    public JavaCompatibleLinkedQueue(LinkedQueue<T> queue) {
        this.queue = queue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(T value) {
        return queue.offer(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(Object value) {
        return queue.remove((T)value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(Object value) {
        return queue.contains((T)value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean offer(T value) {
        return queue.offer(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T peek() {
        return queue.peek();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T poll() {
        return queue.poll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return queue.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public java.util.Iterator<T> iterator() {
        return (new LinkedQueueIterator<T>(queue));
    }

    private static class LinkedQueueIterator<T> implements java.util.Iterator<T> {

        private LinkedQueue<T> queue = null;
        private LinkedQueue.Node<T> lastNode = null;
        private LinkedQueue.Node<T> nextNode = null;

        private LinkedQueueIterator(LinkedQueue<T> queue) {
            this.queue = queue;
            this.nextNode = queue.getTail();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasNext() {
            return (nextNode!=null);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public T next() {
            LinkedQueue.Node<T> current = nextNode;
            lastNode = current;
            if (current!=null) {
                nextNode = current.getPrev();
                return current.getValue();
            }
            return null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void remove() {
            queue.remove(lastNode);
        }
    }
}
