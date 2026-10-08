class WordDictionary {

     static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd;
    }

    private final TrieNode root;


    public WordDictionary() {
        root = new TrieNode();
    }
    
    public void addWord(String word) {
        
        TrieNode current = root;

        for (char c : word.toCharArray()) {

            int index = c - 'a';

            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }

            current = current.children[index];
        }

        current.isEnd = true;
    }
    
    public boolean search(String word) {
        return searchHelper(word, 0, root);
    }

     private boolean searchHelper(String word,
                                 int index,
                                 TrieNode node) {

        if (node == null) {
            return false;
        }

        if (index == word.length()) {
            return node.isEnd;
        }

        char c = word.charAt(index);

        // Wildcard
        if (c == '.') {

            for (TrieNode child : node.children) {

                if (child != null &&
                    searchHelper(word, index + 1, child)) {

                    return true;
                }
            }

            return false;
        }

        int childIndex = c - 'a';

        return searchHelper(
            word,
            index + 1,
            node.children[childIndex]
        );
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */