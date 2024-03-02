public class RBTree {
    private RBNode root;
    private RBNode nullNode; // Nodul null folosit în implementarea Red-Black Tree
    // Constructor
    public RBTree() {
        nullNode = new RBNode(null);
        nullNode.color = 0; // Nodul null este întotdeauna negru
        root = nullNode;
    }
    private boolean isNil(RBNode node) {
        return node == nullNode;
    }
    public RBNode getRoot() {
        return root;
    }

    public void setRoot(RBNode root) {
        this.root = root;
    }

    public RBNode getNullNode() {
        return nullNode;
    }

    public void setNullNode(RBNode nullNode) {
        this.nullNode = nullNode;
    }

    // Metoda de căutare în arbore
    public RBNode search(String fullName) {
        return search(root, fullName);
    }

    public RBNode search(RBNode currentNode, String fullName) {
        if (currentNode == nullNode || currentNode.getData() == null) {
            // Nodul nu există sau este nodul null, participantul nu a fost găsit
            return nullNode;
        }

        int compareResult = fullName.compareTo(currentNode.getData().getNumeIntreg());

        if (compareResult == 0) {
            // Participantul a fost găsit, returnăm nodul
            return currentNode;
        } else if (compareResult < 0) {
            // Caută în subarborele stâng
            return search(currentNode.getLeft(), fullName);
        } else {
            // Caută în subarborele drept
            return search(currentNode.getRight(), fullName);
        }
    }

    public void leftRotate(RBNode x) {
        RBNode y = x.getRight();
        x.setRight(y.getLeft());

        if (y.getLeft() != nullNode) {
            y.getLeft().setParent(x);
        }

        y.setParent(x.getParent());

        if (x.getParent() == nullNode) {
            root = y;
        } else if (x == x.getParent().getLeft()) {
            x.getParent().setLeft(y);
        } else {
            x.getParent().setRight(y);
        }

        y.setLeft(x);
        x.setParent(y);
    }

    // Metoda de rotație la dreapta
    public void rightRotate(RBNode y) {
        RBNode x = y.getLeft();
        y.setLeft(x.getRight());

        if (x.getRight() != nullNode) {
            x.getRight().setParent(y);
        }

        x.setParent(y.getParent());

        if (y.getParent() == nullNode) {
            root = x;
        } else if (y == y.getParent().getLeft()) {
            y.getParent().setLeft(x);
        } else {
            y.getParent().setRight(x);
        }

        x.setRight(y);
        y.setParent(x);
    }
    public void insertNode(RBNode z) {
        RBNode y = nullNode;
        RBNode x = root;

        while (x != nullNode) {
            y = x;
            if (z.getData().getNumeIntreg().compareTo(x.getData().getNumeIntreg()) < 0) {
                x = x.getLeft();
            } else {
                x = x.getRight();
            }
        }

        z.setParent(y);

        if (y == nullNode) {
            root = z;
        } else if (z.getData().getNumeIntreg().compareTo(y.getData().getNumeIntreg()) < 0) {
            y.setLeft(z);
        } else {
            y.setRight(z);
        }

        z.setLeft(nullNode);
        z.setRight(nullNode);
        z.setColor(1); // Noul nod este roșu

        fixInsert(z);
    }
    public void fixInsert(RBNode z) {
        while (z.getParent().getColor() == 1) {
            if (z.getParent() == z.getParent().getParent().getLeft()) {
                RBNode y = z.getParent().getParent().getRight();
                if (y.getColor() == 1) {
                    z.getParent().setColor(0);
                    y.setColor(0);
                    z.getParent().getParent().setColor(1);
                    z = z.getParent().getParent();
                } else {
                    if (z == z.getParent().getRight()) {
                        z = z.getParent();
                        leftRotate(z);
                    }
                    z.getParent().setColor(0);
                    z.getParent().getParent().setColor(1);
                    rightRotate(z.getParent().getParent());
                }
            } else {
                // Cazul simetric pentru subarborele drept
                RBNode y = z.getParent().getParent().getLeft();
                if (y.getColor() == 1) {
                    z.getParent().setColor(0);
                    y.setColor(0);
                    z.getParent().getParent().setColor(1);
                    z = z.getParent().getParent();
                } else {
                    if (z == z.getParent().getLeft()) {
                        z = z.getParent();
                        rightRotate(z);
                    }
                    z.getParent().setColor(0);
                    z.getParent().getParent().setColor(1);
                    leftRotate(z.getParent().getParent());
                }
            }
        }

        root.setColor(0); // Rădăcina trebuie să fie mereu neagră
    }
    public RBNode successor(RBNode w) {
        if (w == null || w == nullNode) {
            return nullNode;
        }

        RBNode x = w;
        if (x.getRight() != nullNode && x.getRight().getData() != null) {
            return minimum(x.getRight());
        }

        RBNode y = x.getParent();
        while (y != nullNode && y.getData() != null && x == y.getRight()) {
            x = y;
            y = x.getParent();
        }

        return (y != nullNode && y.getData() != null) ? y : nullNode;
    }

    private RBNode minimum(RBNode w) {
        RBNode x = w;
        while (x.getLeft() != nullNode && x.getLeft().getData() != null) {
            x = x.getLeft();
        }
        return x;
    }
    public RBNode del(RBNode z) {
        RBNode y = (isNil(z.getLeft()) || isNil(z.getRight())) ? z : successor(z);
        RBNode x = (!isNil(y.getLeft())) ? y.getLeft() : y.getRight();
        x.setParent(y.getParent());

        if (isNil(y.getParent())) {
            root = x;
        } else {
            if (y == y.getParent().getLeft()) {
                y.getParent().setLeft(x);
            } else {
                y.getParent().setRight(x);
            }
        }

        if (y != z) {
            z.setData(y.getData()); // Copy y's satellite data into z
        }

        if (y.getColor() == 0) {
            fixDelete(x);
        }

        return y;
    }
    private void fixDelete(RBNode x) {
        RBNode w;
        while (x != root && x.getColor() == 0) {
            if (x == x.getParent().getLeft()) {
                w = x.getParent().getRight();
                if (w.getColor() == 1) {
                    w.setColor(0);
                    x.getParent().setColor(1);
                    leftRotate(x.getParent());
                    w = x.getParent().getRight();
                }
                if (w.getLeft().getColor() == 0 && w.getRight().getColor() == 0) {
                    w.setColor(1);
                    x = x.getParent();
                } else {
                    if (w.getRight().getColor() == 0) {
                        w.getLeft().setColor(0);
                        w.setColor(1);
                        rightRotate(w);
                        w = x.getParent().getRight();
                    }
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(0);
                    w.getRight().setColor(0);
                    leftRotate(x.getParent());
                    x = root;
                }
            } else {
                // Symmetric case for the right subtree
                w = x.getParent().getLeft();
                if (w.getColor() == 1) {
                    w.setColor(0);
                    x.getParent().setColor(1);
                    rightRotate(x.getParent());
                    w = x.getParent().getLeft();
                }
                if (w.getRight().getColor() == 0 && w.getLeft().getColor() == 0) {
                    w.setColor(1);
                    x = x.getParent();
                } else {
                    if (w.getLeft().getColor() == 0) {
                        w.getRight().setColor(0);
                        w.setColor(1);
                        leftRotate(w);
                        w = x.getParent().getLeft();
                    }
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(0);
                    w.getLeft().setColor(0);
                    rightRotate(x.getParent());
                    x = root;
                }
            }
        }
        x.setColor(0);
    }
    public void displayTree() {
        display(root, 0);
    }
    private void display(RBNode w, int indent) {
        if (!isNil(w)) {
            display(w.getRight(), indent + 2);
            for (int i = 0; i < indent; i++) System.out.print(" ");
            System.out.println(w.getData().getNumeIntreg() + " (" + (w.getColor() == 1 ? "R" : "B") + ")");
            display(w.getLeft(), indent + 2);
        }
    }




}