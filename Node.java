public class Node {
    int value;
    Node representative;
    Node last;
    int length;
    Node next;

    Node(int value) {
        this.value = value;
        this.representative = this;
        this.last = this;
        this.length = 1;
        this.next = this;
    }
}