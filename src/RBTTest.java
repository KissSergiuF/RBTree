public class RBTTest {
    public static void main(String[] args) {
        RBTree rbTree = new RBTree();
        Participant participant1 = new Participant("Ion Popescu", "0722222222", 20);
        Participant participant2 = new Participant("Ana Popescu", "0722222222", 20);
        Participant participant3 = new Participant("Maria Popescu", "0722222222", 20);
        Participant participant4 = new Participant("Mihai Popescu", "0722222222", 20);
        Participant participant5 = new Participant("Xintia Andrei", "0722222222", 20);
        Participant participant6 = new Participant("Zeng Cheanu", "0722222222", 20);
        RBNode node1 = new RBNode(participant1);
        RBNode node2 = new RBNode(participant2);
        RBNode node3 = new RBNode(participant3);
        RBNode node4 = new RBNode(participant4);
        RBNode node5 = new RBNode(participant5);
        RBNode node6 = new RBNode(participant6);

        rbTree.insertNode(node1);
        rbTree.insertNode(node2);
        rbTree.insertNode(node3);
        rbTree.insertNode(node4);
        rbTree.insertNode(node5);
        rbTree.insertNode(node6);
        afisareDate(rbTree.getRoot(), rbTree.getNullNode());
        System.out.println();
        rbTree.del(node2);
        afisareDate(rbTree.getRoot(), rbTree.getNullNode());
        //Ion Popescu=2;
        //Ana Popescu=1;
        //Maria Popescu=3;
        //Mihai Popescu=4;
        //Xintia Andrei=5;
        //Zeng Cheanu=6;
    }
    private static void testSuccesor(RBTree rbTree, String key) {
        RBNode resultNode = rbTree.search(key);

        if (resultNode != null && resultNode != rbTree.getNullNode() && resultNode.getData() != null) {
            RBNode succesor = rbTree.successor(resultNode);
            if (succesor != null && succesor != rbTree.getNullNode() && succesor.getData() != null) {
                System.out.println("Succesorul nodului cu cheia '" + key + "' este: " + succesor.getData().getNumeIntreg());
            } else {
                System.out.println("Nodul cu cheia '" + key + "' nu are succesor.");
            }
        } else {
            System.out.println("Nodul cu cheia '" + key + "' nu a fost găsit.");
        }
    }
    private static void testSearch(RBTree rbTree, String key) {
        RBNode resultNode = rbTree.search(key);

        if (resultNode != null && resultNode != rbTree.getNullNode() && resultNode.getData() != null) {
            System.out.println("Participantul cu numele '" + key + "' a fost găsit: " + resultNode.getData());
        } else {
            System.out.println("Participantul cu numele '" + key + "' nu a fost găsit.");
        }
    }
    public static void afisareDate(RBNode node, RBNode nullNode) {
        if (node != null && node != nullNode && node.getData() != null) {
            String culoareNod = (node.getColor() == 1) ? "Rosu" : "Negru";
            System.out.println("Nodul " + node.getData().getNumeIntreg() + " (" + culoareNod + ")");

            if (node.getLeft() != nullNode && node.getLeft().getData() != null) {
                String culoareStanga = (node.getLeft().getColor() == 1) ? "Rosu" : "Negru";
                System.out.println("Copilul stâng: " + node.getLeft().getData().getNumeIntreg() + " (" + culoareStanga + ")");
            } else {
                System.out.println("Copilul stâng: null");
            }

            if (node.getRight() != nullNode && node.getRight().getData() != null) {
                String culoareDreapta = (node.getRight().getColor() == 1) ? "Rosu" : "Negru";
                System.out.println("Copilul drept: " + node.getRight().getData().getNumeIntreg() + " (" + culoareDreapta + ")");
            } else {
                System.out.println("Copilul drept: null");
            }

            // Afișează informațiile pentru subarborii stângi și drepti
            afisareDate(node.getLeft(), nullNode);
            afisareDate(node.getRight(), nullNode);
        }
    }



}