package sumeetdas.javacp.datastructures.queues;

@SuppressWarnings("unchecked")
public class JavaCompatibleArrayQueue<T> extends java.util.AbstractQueue<T> {
    private ArrayQueue<T> queue = null;

        public JavaCompatibleArrayQueue(ArrayQueue<T> queue) {
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
            return (new ArrayQueueIterator<T>(queue));
        }

        private static class ArrayQueueIterator<T> implements java.util.Iterator<T> {

            private ArrayQueue<T> queue = null;
            private int last = -1;
            private int index = 0; //offset from first

            private ArrayQueueIterator(ArrayQueue<T> queue) {
                this.queue = queue;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public boolean hasNext() {
                return (queue.getFirstIndex() + index) < queue.getLastIndex();
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public T next() {
                if (queue.getFirstIndex() + index < queue.getLastIndex()) {
                    last = queue.getFirstIndex() + index;
                    return queue.getArray()[(queue.getFirstIndex() + index++) % queue.getArray().length];
                }
                return null;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void remove() {
                queue.remove(last);
            }
        }
}
