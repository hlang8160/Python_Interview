public class Solution {
    /*
     * @param root: A Tree
     * @return: Level order a list of lists of integer
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            ArrayList<Integer> level = new ArrayList<Integer>();
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(level);
        }
        return result;
    }
}

public class Solution1 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        Stack<List<Integer>> stack = new Stack<>();

        queue.offer(root);
        while(!queue.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            stack.push(level);
        }
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }
}

public class Solution2 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        boolean LeftToRight = true;
        while(!queue.isEmpty()) {
            ArrayList<Integer> level = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                // if (LeftToRight) {
                //     if (node.right != null) {
                //         queue.offer(node.right);
                //     }
                //     if (node.left != null) {
                //         queue.offer(node.left);
                //     }
                // }
                // else {
                    // if (node.left != null) {
                    //     queue.offer(node.left);
                    // }
                    // if (node.right != null) {
                    //     queue.offer(node.right);
                    // }
                // }
            }
            if
            LeftToRight = !LeftToRight;
            result.add(level);
        }
        return result;
    }
}


/*
二叉树的序列化与反序列化
采用递归遍历的方法
*/
public class Solution3 {
    public int index = -1;
    public String serialize(TreeNode root) {
        StringBuffer s = new StringBuffer();
        if (root == null) {
            s.append("#,");
            return s.toString();
        }
        s.append(root.val + ",");
        s.append(serialize(root.left));
        s.append(serialize(root.right));
        return s.toString();
    }

    public TreeNode deserialize(String str) {
        index++;
        int length = str.length();
        if (index >= length) {
            return null;
        }
        String[] nodeSeq = str.split(",");
        TreeNode pNode = null;
        if (!nodeSeq[index].equals("#")) {
            pNode = new TreeNode(Integer.valueOf(nodeSeq[index]));
            pNode.left = deserialize(str);
            pNode.right = deserialize(str);
        }
        return pNode;
    }
}


//二叉树的之字形遍历
public class Solution {
    /*
     * @param root: A Tree
     * @return: A list of lists of integer include the zigzag level order traversal of its nodes' values.
     */
    private boolean LeftToRight = true;
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            ArrayList<Integer> level = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            if (!LeftToRight) {
                Collections.reverse(level);
            }
            LeftToRight = !LeftToRight;
            result.add(level);
        }
        return result;
    }
}

/*
下面这种方法不可行，因为只是将左右字节点的添加方式改变了，整体层次的左右顺序并没有改变，因此要用栈的方式
*/
public class Solution {
    /*
     * @param root: A Tree
     * @return: A list of lists of integer include the zigzag level order traversal of its nodes' values.
     */
   public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        boolean LeftToRight = true;
        while(!queue.isEmpty()) {
            ArrayList<Integer> level = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (LeftToRight) {
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                }
                else {
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
            }
            LeftToRight = !LeftToRight;
            result.add(level);
        }
        return result;
    }
}

/*
用栈的方式
*/

public class Solution {
    /*
     * @param root: A Tree
     * @return: A list of lists of integer include the zigzag level order traversal of its nodes' values.
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        boolean LeftToRight = true;
        Stack<TreeNode> curlevel = new Stack<TreeNode>();
        Stack<TreeNode> nextlevel = new Stack<TreeNode>();
        Stack<TreeNode> tmp;

        curlevel.push(root);
        while (!curlevel.isEmpty()) {//确保所有层
            List<Integer> list = new ArrayList<Integer>();
            while (!curlevel.isEmpty()) { //当前层，都是先将该层的list定义在外面，之后循环不断的加入，得到该层信息
                TreeNode node = curlevel.pop();
                list.add(node.val);
                if (!LeftToRight) {
                    if (node.right != null) {
                        nextlevel.push(node.right);
                    }
                    if (node.left != null) {
                        nextlevel.push(node.left);
                    }
                }
                else {
                    if (node.left != null) {
                        nextlevel.push(node.left);
                    }
                    if (node.right != null) {
                        nextlevel.push(node.right);
                    }
                }
            }
            result.add(list);
            // curlevel = nextlevel; //将下一层放到当前层  3347s
            // nextlevel = new Stack<TreeNode>(); //将原有下一层置空 
            //另外一种方式
            tmp = curlevel;
            curlevel = nextlevel;
            nextlevel = tmp;

            LeftToRight = !LeftToRight;
        }
        return result;
    }
}

/*
验证图是否是树
给出n = 5 并且 edges = [[0, 1], [0, 2], [0, 3], [1, 4]], 返回 true.
图是树要满足两个条件
1.n个点，n-1条边 （保证无环）
2.验证n个点是连通的。
*/

public class Solution {
    /**
     * @param n an integer
     * @param edges a list of undirected edges
     * @return true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        if (n == 0) {
            return false;
        }
        
        if (edges.length != n - 1) {
            return false;
        }
        
        Map<Integer, Set<Integer>> graph = initializeGraph(n, edges);
        
        // bfs
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> hash = new HashSet<>();
        
        queue.offer(0);
        hash.add(0);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (Integer neighbor : graph.get(node)) {
                if (hash.contains(neighbor)) {
                    continue;
                }
                hash.add(neighbor);
                queue.offer(neighbor);
            }
        }
        
        return (hash.size() == n);
    }
    
    //无向图无权 邻接表的构建 用一个MAP来实现 
    private Map<Integer, Set<Integer>> initializeGraph(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<Integer>());
        }
        
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        
        return graph;
    }
}

public class Solution {
    /**
     * @param n an integer
     * @param edges a list of undirected edges
     * @return true if it's a valid tree, or false
     * 自写
     */
    public boolean validTree(int n, int[][] edges) {
        if (n == 0) {
            return false;
        }
        if (edges.length != n - 1) {
            return false;
        }

        Map<Integer, Set<Integer>> graph = initializeGraph(n, edges);

        Queue<Integer> queue = new LinkedList<Integer>();
        Set<Integer> hash = new HashSet<Integer>();
        
        queue.offer(0);
        hash.add(0);
        while(!queue.isEmpty()) {
            int node = queue.poll();
            for (Integer neighbor : graph.get(node)) {
                if (hash.contains(neighbor)) {
                    continue;
                }
                hash.add(neighbor);
                queue.offer(neighbor);
            }
        }

        return (hash.size() == n);
    }

    private Map<Integer, Set<Integer>> initializeGraph(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        //Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<Integer>());
        }
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        return graph;
    }
}