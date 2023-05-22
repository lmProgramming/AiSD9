public class DisjointSetForest implements IDisjointSetStructure
{
    private final Node[] nodes;

    public DisjointSetForest(int size)
    {
        nodes = new Node[size];
        for (int i = 0; i < size; i++)
        {
            nodes[i] = new Node(i);
        }
    }

    @Override
    public int findSet(int item) throws ItemOutOfRangeException {
        validateItem(item);

        Node node = nodes[item];

        return findNode(node).item;
    }

    public Node findNode(int item) throws ItemOutOfRangeException
    {
        validateItem(item);

        Node node = nodes[item];

        return findNode(node);
    }

    public Node findNode(Node node)
    {
        if (node.parent != node)
        {
            node.parent = findNode(node.parent);
        }
        return node.parent;
    }

    @Override
    public void union(int item1, int item2) throws ItemOutOfRangeException {
        validateItem(item1);
        validateItem(item2);

        if (item1 == item2)
        {
            return;
        }

        Node root1 = findNode(item1);
        Node root2 = findNode(item2);

        link(root1, root2);
    }

    public void link(Node root1, Node root2)
    {
        if (root1.rank < root2.rank)
        {
            root1.parent = root2;
        }
        else
        {
            root2.parent = root1;

            if (root1.rank == root2.rank)
            {
                root1.rank++;
            }
        }
    }

    private void validateItem(int item) throws ItemOutOfRangeException
    {
        if (item < 0 || item >= nodes.length)
        {
            throw new ItemOutOfRangeException();
        }
    }

    private static class Node {
        int item;
        Node parent;
        int rank;

        public Node(int item) {
            this.item = item;
            this.parent = this;
            this.rank = 0;
        }
    }
}
