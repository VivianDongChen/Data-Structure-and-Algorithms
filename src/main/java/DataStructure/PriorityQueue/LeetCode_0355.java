package DataStructure.PriorityQueue;

import java.util.*;

/**
 * 设计推特
 */
public class LeetCode_0355 {

    static class Twitter {

        /**
         * Tweet类
         */
        static class Tweet{
            int id;
            int time;
            Tweet next; //以链表形式存放Tweet

            public Tweet(int id, int time, Tweet next) {
                this.id = id;
                this.time = time;
                this.next = next;
            }

            public int getId() {
                return id;
            }

            public int getTime() {
                return time;
            }
        }

        /**
         * 用户类
         */
        static class User{
            int id;

            public User(int id) {
                this.id = id;
            }

            Set<Integer> followees = new HashSet<>();
            Tweet head = new Tweet(-1, -1, null); //Tweet存储链表头(哨兵）即可
        }


        private Map<Integer, User> userMap = new HashMap<>();    //用户的集合
        private static int time;  //发布文章的篇数

        /**
         * 发布文章
         * @param userId 发布文章的用户
         * @param tweetId 文章的id
         */
        public void postTweet(int userId, int tweetId){
            User user =  userMap.computeIfAbsent(userId, User::new);  //在用户集合中找到用户，如果没有就创建一个新的用户
            user.head.next = new Tweet(tweetId,time++, user.head.next);   //将文章指向链表头的next，链表头指向文章
        }

        /**
         * 新增关注
         * @param userId 被关注用户id
         * @param followeeId 关注者用户id
         */
        public void follow(int userId, int followeeId){
            User user =  userMap.computeIfAbsent(userId, User::new);
            User followee =  userMap.computeIfAbsent(followeeId, User::new);
            user.followees.add(followee.id);
        }

        /**
         * 取消关注
         * @param userId  被关注用户id
         * @param followeeId   关注者用户id
         */
        public void unfollow(int userId, int followeeId){
            User user =  userMap.get(userId);
            if(user != null){
                user.followees.remove(followeeId);
            }
        }

        /**
         * 获取最新10篇文章（包括自己和关注者）
         * 合并K个有序链表 - 使用大顶堆
         * @param userId
         * @return
         */
        public List<Integer> getNewsFeed(int userId){
            User user = userMap.get(userId);  //通过用户ID找到用户
            if(user == null){   //如果用户不存在
                return List.of();
            }
            //定义一个大顶堆，按照文章的时间顺序从大到小排序
            PriorityQueue<Tweet> queue = new PriorityQueue<>(Comparator.comparingInt(Tweet:: getTime).reversed());
            if(user.head.next != null){
                queue.offer(user.head.next); //将用户的第一篇文章加入堆
            }

            for(Integer id: user.followees){   //将所有关注者的第一篇文章加入堆
                User followee = userMap.get(id);
                if(followee.head.next != null){
                    queue.offer(followee.head.next);
                }
            }
            List<Integer> res = new ArrayList<>();
            int count = 0;
            while(!queue.isEmpty() && count < 10){
                Tweet max = queue.poll();  //弹出堆顶元素（最新文章）
                res.add(max.id);
                count++;
                if(max.next != null) {
                    queue.add(max.next);
                }
            }
            return res;
        }
    }


    public static void main(String[] args) {
        Twitter t1 = new Twitter();
        t1.postTweet(2, 5); //0
        t1.postTweet(1, 3); //1
        t1.postTweet(1, 101); //2
        t1.postTweet(2, 13); //3
        t1.postTweet(2, 10); //4
        t1.postTweet(1, 2); //5
        t1.postTweet(2, 94); //6
        t1.postTweet(2, 505); //7
        t1.postTweet(1, 333); //8
        t1.postTweet(1, 22); //9
        System.out.println(t1.getNewsFeed(2)); // -> 505 94 10 13 5
        t1.follow(2, 1);
        System.out.println(t1.getNewsFeed(2)); // -> 22 333 505 94 2 10 13 101 3 5


        Twitter t2 = new Twitter();
        t2.postTweet(1, 11);
        t2.postTweet(2, 21);
        t2.postTweet(3, 31);
        t2.postTweet(4, 41);
        t2.postTweet(1, 12);
        t2.postTweet(2, 22);
        t2.postTweet(3, 32);
        t2.postTweet(3, 33);
        t2.postTweet(4, 42);
        t2.follow(1, 2);
        t2.follow(1, 3);
        t2.follow(1, 4);
        System.out.println(t2.getNewsFeed(1)); // -> 42, 33, 32, 22, 12, 41, 31, 21, 11

        Twitter t3 = new Twitter();
        for (int i = 0; i < 11; i++) {
            t3.postTweet(1, i);
        }
        System.out.println(t3.getNewsFeed(1));

        Twitter t4 = new Twitter();
        t4.postTweet(1, 1);
        System.out.println(t4.getNewsFeed(1));
        t4.follow(2, 1);
        System.out.println(t4.getNewsFeed(2));
        t4.unfollow(2, 1);
        System.out.println(t4.getNewsFeed(2));

    }
}
