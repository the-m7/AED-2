public class HuffmanNode {
    int data;
    char c;

    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(char c, int data) {
        this.data = data;
        this.c = c;
        this.left = null;
        this.right = null;
    }
    
    public HuffmanNode(int data, HuffmanNode left, HuffmanNode right) {
        this.data = data;
        this.c = '¶';
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "HuffmanNode [data=" + data + ", c=" + c + ", left=" + left + ", right=" + right + "]";
    }

    
}
