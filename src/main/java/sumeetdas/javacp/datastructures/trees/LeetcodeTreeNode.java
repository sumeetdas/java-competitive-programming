package sumeetdas.javacp.datastructures.trees;

public class LeetcodeTreeNode<T> {
    T val;
    LeetcodeTreeNode<T> left;
    LeetcodeTreeNode<T> right;
    public LeetcodeTreeNode() {}
    public LeetcodeTreeNode(T val) { this.val = val; }
    public LeetcodeTreeNode(T val, LeetcodeTreeNode<T> left, LeetcodeTreeNode<T> right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
    public LeetcodeTreeNode<T> getLeft() {
        return left;
    }
    public LeetcodeTreeNode<T> getRight() {
        return right;
    }
    public T getVal() {
        return val;
    }
    public void setLeft(LeetcodeTreeNode<T> left) {
        this.left = left;
    }
    public void setRight(LeetcodeTreeNode<T> right) {
        this.right = right;
    }
    public void setVal(T val) {
        this.val = val;
    }
}
