import java.util.PriorityQueue;

public class HuffmanTree {

    HuffmanNode root;

    public HuffmanTree(char[] chars, int[] freq){

        root = null;

        int n = chars.length;

        PriorityQueue<HuffmanNode> queue = new PriorityQueue<HuffmanNode>(n, new HuffmanComparator());

        for(int i=0;i<n;i++){

            queue.add(new HuffmanNode(chars[i], freq[i]));

        }
        while(queue.size()>1){

            HuffmanNode n1 = queue.poll();
            
            HuffmanNode n2 = queue.poll();

            HuffmanNode temp = new HuffmanNode(n1.data + n2.data, n1, n2);
            
            root = temp;
            //System.out.println(root.toString());

            queue.add(temp);

        }
    }

}
