public class DisjointSetLinkedList implements IDisjointSetStructure {
    private Node[] nodes;

    public DisjointSetLinkedList(int size) {
        nodes = new Node[size];
        for (int i = 0; i < size; i++) {
            nodes[i] = new Node(i);
        }
    }

    private Node findRepresentative(Node node) {
        Node representative = node.representative;
        if (representative != node) {
            node.representative = findRepresentative(representative);
        }
        return node.representative;
    }

    @Override
    public int findSet(int item) throws ItemOutOfRangeException {
        if (item < 0 || item >= nodes.length) {
            throw new ItemOutOfRangeException();
        }
        Node node = nodes[item];
        return findRepresentative(node).value;
    }

    @Override
    public void union(int item1, int item2) throws ItemOutOfRangeException {
        if (item1 < 0 || item1 >= nodes.length || item2 < 0 || item2 >= nodes.length) {
            throw new ItemOutOfRangeException();
        }
        Node node1 = nodes[item1];
        Node node2 = nodes[item2];

        Node representative1 = findRepresentative(node1);
        Node representative2 = findRepresentative(node2);

        if (representative1 == representative2) {
            return; // Both items are already in the same set
        }

        if (representative1.length < representative2.length) {
            Node temp = representative1;
            representative1 = representative2;
            representative2 = temp;
        }

        representative2.representative = representative1;
        representative1.last.next = nodes[representative2.value];
        representative1.last = representative2.last;
        representative1.length += representative2.length;
    }
}
