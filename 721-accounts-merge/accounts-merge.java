class Solution {
    private int[] parent;
    private int[] rank;

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();

        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        Map<String, Integer> emailToAccount = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String email = accounts.get(i).get(j);

                if (emailToAccount.containsKey(email)) {
                    union(i, emailToAccount.get(email));
                } else {
                    emailToAccount.put(email, i);
                }
            }
        }

        Map<Integer, List<String>> groups = new HashMap<>();

        for (String email : emailToAccount.keySet()) {
            int root = find(emailToAccount.get(email));

            groups.computeIfAbsent(root, k -> new ArrayList<>())
                  .add(email);
        }

        List<List<String>> result = new ArrayList<>();

        for (int root : groups.keySet()) {
            List<String> emails = groups.get(root);

            Collections.sort(emails);

            List<String> account = new ArrayList<>();
            account.add(accounts.get(root).get(0));
            account.addAll(emails);

            result.add(account);
        }

        return result;
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }

        return parent[x];
    }

    private void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return;
        }

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }
}