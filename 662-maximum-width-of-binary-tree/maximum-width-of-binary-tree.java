/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
import java.util.*;

class Solution {

    static class Pair {
        TreeNode node;
        long index;

        Pair(TreeNode node, long index) {
            this.node = node;
            this.index = index;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));

        long maxWidth = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            long firstIndex = queue.peek().index;
            long lastIndex = firstIndex;

            for (int i = 0; i < size; i++) {

                Pair current = queue.poll();

                long normalizedIndex =
                    current.index - firstIndex;

                lastIndex = normalizedIndex;

                if (current.node.left != null) {
                    queue.offer(
                        new Pair(
                            current.node.left,
                            2 * normalizedIndex
                        )
                    );
                }

                if (current.node.right != null) {
                    queue.offer(
                        new Pair(
                            current.node.right,
                            2 * normalizedIndex + 1
                        )
                    );
                }
            }

            maxWidth = Math.max(
                maxWidth,
                lastIndex + 1
            );
        }

        return (int) maxWidth;
    }
}