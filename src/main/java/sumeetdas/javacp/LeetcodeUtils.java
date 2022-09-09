package sumeetdas.javacp;

import java.util.ArrayList;
import java.util.List;

import sumeetdas.javacp.datastructures.queues.ArrayQueue;
import sumeetdas.javacp.datastructures.trees.LeetcodeTreeNode;

public class LeetcodeUtils {
    public <T> List<T> getLevelFirstTreeRepresentation (LeetcodeTreeNode<T> lcTreeNode) {

        var list = new ArrayList<T>();
        if (null == lcTreeNode) {
            return null;
        }
        var queue = new ArrayQueue<LeetcodeTreeNode<T>>();
        queue.addLast(lcTreeNode);
        while (!queue.isEmpty()) {
            var size = queue.size();
            while (size > 0) {
                var parentNode = queue.removeFirst();
                if (null == parentNode) {
                    list.add(null);
                }
                else {
                    list.add(parentNode.getVal());
                    if (parentNode.getLeft() != null || parentNode.getRight() != null) {
                        queue.addLast(parentNode.getLeft());
                        queue.addLast(parentNode.getRight());
                    }
                }
                size -= 1;
            }
        }
        return list;
    }

    public <T> LeetcodeTreeNode<T> getRootNodeFromLevelFirstArray (T[] p) {
        if (null == p || p.length == 0) {
            return null;
        }
        var queue = new ArrayQueue<LeetcodeTreeNode<T>>();
        var root = new LeetcodeTreeNode<T>(p[0]);
        queue.addLast(root);
        var idx = 0;
        while (idx < p.length) {
            while (queue.size() != 0) {
                var parentNode = queue.removeFirst();
                idx += 1;
                if (idx < p.length) {
                    var leftNode = new LeetcodeTreeNode<>(p[idx]);
                    parentNode.setLeft(leftNode);
                    queue.addLast(leftNode);
                }
                else {
                    parentNode.setLeft(null);
                }
                idx += 1;
                if (idx < p.length) {
                    var rightNode = new LeetcodeTreeNode<>(p[idx]);
                    parentNode.setRight(rightNode);
                    queue.addLast(rightNode);
                }
                else {
                    parentNode.setRight(null);
                }
            }
        }
        return root;
    }
}
