public class DisjointSetLinkedList implements IDisjointSetStructure {
    private final Node[] nodes;

    public DisjointSetLinkedList(int size)
    {
        nodes = new Node[size];
        for (int i = 0; i < size; i++)
        {
            nodes[i] = new Node(i);
        }
    }

    private Node findRepresentative(Node node)
    {
        Node representative = node.representative;
        if (representative != node)
        {
            node.representative = findRepresentative(representative);
        }
        return node.representative;
    }

    @Override
    public int findSet(int item) throws ItemOutOfRangeException
    {
        validateItem(item);

        Node node = nodes[item];

        return findRepresentative(node).value;
    }

    private void validateItem(int item) throws ItemOutOfRangeException
    {
        if (item < 0 || item >= nodes.length)
        {
            throw new ItemOutOfRangeException();
        }
    }

    @Override
    public void union(int item1, int item2) throws ItemOutOfRangeException
    {
        validateItem(item1);
        validateItem(item2);

        Node node1 = nodes[item1];
        Node node2 = nodes[item2];

        Node representative1 = findRepresentative(node1);
        Node representative2 = findRepresentative(node2);

        if (representative1 == representative2)
        {
            return;
        }

        if (representative1.length < representative2.length)
        {
            Node temp = representative1;

            representative1 = representative2;
            representative2 = temp;

            for (int i = 0; i < ; i++)
            {
                
            }
        }

        representative2.representative = representative1;
        representative1.last.next = nodes[representative2.value];
        representative1.last = representative2.last;
        representative1.length += representative2.length;
    }

    private static class Node
    {
        int value;
        Node representative;
        Node last;
        int length;
        Node next;

        Node(int value)
        {
            this.value = value;
            this.representative = this;
            this.last = this;
            this.length = 1;
            this.next = this;
        }
    }
}
