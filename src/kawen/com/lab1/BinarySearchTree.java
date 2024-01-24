package kawen.com.lab1;

import java.util.Scanner;

class TreeNode {
    int key;
    TreeNode left, right;

    public TreeNode(int key) {
        this.key = key;
        this.left = this.right = null;
    }
}
public class BinarySearchTree {
    private TreeNode root;
    //初始化
    public BinarySearchTree() {
        this.root = null;
    }

    public void insert(int key) {
        root = insertRec(root, key);
    }

    //插入节点
    public TreeNode insertRec(TreeNode root, int key) {
        if (root == null) {
            root = new TreeNode(key);
            return root;
        }
        if (key < root.key) {
            root.left = insertRec(root.left, key);
        } else if (key > root.key) {
            root.right = insertRec(root.right, key);
        }

        return root;
    }

    public void delete(int key) {
        root = deleteRec(root, key);
    }
    //删除节点
    public TreeNode deleteRec(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        if (key < root.key) {
            root.left = deleteRec(root.left, key);
        } else if (key > root.key) {
            root.right = deleteRec(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            root.key = minValue(root.right);

            root.right = deleteRec(root.right, root.key);
        }
        return root;
    }

    public int minValue(TreeNode root) {
        int minValue = root.key;
        while (root.left != null) {
            minValue = root.left.key;
            root = root.left;
        }
        return minValue;
    }

   /* public boolean search(int key) {
        return searchRec(root, key);
    }*/
//折半查找
   public int[] toArray(TreeNode root) {
       int[] result = new int[getSize(root)];
       toArrayUtil(root, result, 0);
       return result;
   }
//转化成顺序存储，递归
public int toArrayUtil(TreeNode root, int[] array, int index) {
        if (root != null) {
            index = toArrayUtil(root.left, array, index);
            array[index++] = root.key;
            index = toArrayUtil(root.right, array, index);
        }
        return index;
    }
    public boolean searchRec(int key) {
        int[] sortedArray = toArray(root);
        int low = 0;
        int high = sortedArray.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (sortedArray[mid] == key) {
                return true;
            } else if (sortedArray[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }
    public int getSize(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + getSize(root.left) + getSize(root.right);
        }
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("输入二叉树的元素个数");
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        int n= scanner.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println("输入元素");
            binarySearchTree.insert(scanner.nextInt());
        }
        System.out.println("二叉树建立成功");
        System.out.println("输入要查找的元素");
        System.out.println("查找结果："+binarySearchTree.searchRec(scanner.nextInt()));
        System.out.println("输入要删除的元素");
        binarySearchTree.delete(scanner.nextInt());
        System.out.println("输入要查找的元素");
        System.out.println("查找结果："+binarySearchTree.searchRec(scanner.nextInt()));
    }
}
