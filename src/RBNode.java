public class RBNode {
    public Participant data;
    public RBNode parent;
    public RBNode left;
    public RBNode right;
    public int color; // 0 pentru negru, 1 pentru roșu

    public RBNode(Participant data) {
        this.data = data;
        this.color = 1;
        this.left = null;
        this.right = null;
        this.parent = null;
    }
    public Participant getData() {
        return data;
    }

    public void setData(Participant data) {
        this.data = data;
    }

    public RBNode getParent() {
        return parent;
    }

    public void setParent(RBNode parent) {
        this.parent = parent;
    }

    public RBNode getLeft() {
        return left;
    }

    public void setLeft(RBNode left) {
        this.left = left;
    }

    public RBNode getRight() {
        return right;
    }

    public void setRight(RBNode right) {
        this.right = right;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}