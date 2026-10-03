import java.util.*;

class Twitter {

    private static class Tweet {
        int id;
        int time;
        Tweet next;

        Tweet(int id, int time) {
            this.id = id;
            this.time = time;
        }
    }

    private static class TweetEntry {
        Tweet tweet;

        TweetEntry(Tweet tweet) {
            this.tweet = tweet;
        }
    }

    private Map<Integer, Set<Integer>> following;
    private Map<Integer, Tweet> tweets;
    private int timestamp;

    public Twitter() {
        following = new HashMap<>();
        tweets = new HashMap<>();
        timestamp = 0;
    }
    
    public void postTweet(int userId, int tweetId) {
        Tweet tweet = new Tweet(tweetId, timestamp++);

        tweet.next = tweets.get(userId);
        tweets.put(userId, tweet);
    }
    
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> feed = new ArrayList<>();

        PriorityQueue<TweetEntry> maxHeap =
            new PriorityQueue<>(
                (a, b) -> Integer.compare(
                    b.tweet.time, a.tweet.time
                )
            );

        if (tweets.containsKey(userId)) {
            maxHeap.offer(new TweetEntry(tweets.get(userId)));
        }

        for (int followee : following.getOrDefault(
                userId, Collections.emptySet())) {

            if (tweets.containsKey(followee)) {
                maxHeap.offer(
                    new TweetEntry(tweets.get(followee))
                );
            }
        }

        while (!maxHeap.isEmpty() && feed.size() < 10) {
            TweetEntry entry = maxHeap.poll();

            Tweet current = entry.tweet;
            feed.add(current.id);

            if (current.next != null) {
                maxHeap.offer(new TweetEntry(current.next));
            }
        }

        return feed;
    }
    
    public void follow(int followerId, int followeeId) {
        following
            .computeIfAbsent(
                followerId, id -> new HashSet<>()
            )
            .add(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        following
            .getOrDefault(
                followerId, Collections.emptySet()
            )
            .remove(followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */