import org.w3c.dom.ls.LSOutput;

import javax.print.DocFlavor;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class LeetCode0535EncodeAndDecodeTinyURL {
    public static void main(String[] args) {
        CodecSequence codec = new CodecSequence();
        String encoded = codec.encode("https://leetcode.cn/problems/1");
        System.out.println(encoded);

        encoded = codec.encode("https://leetcode.cn/problems/2");
        System.out.println(encoded);

        for (int i = 0;  i <= 62;  i++) {
            System.out.println(i + "\t" + CodecSequence.toBase62(i));

        }
        System.out.println(CodecSequence.toBase62(237849728));
    }

        /*
        要让【长】【短】网址一一对应

            1. 用【随机数】作为短网址标识
            2. 用【hash码】作为短网址标识
            3. 用【递增数】作为短网址标识
               思考：1）多线程可以使用吗？
                    2）分布式下可以使用吗？
                    3）4e9iAk是怎么生成的？这是一个高进制的数字 a-z 0-9 A-Z 62进制的数字


        0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f
        十进制 => 十六进制

        31 % 16 = 15  -> f  第一位
        31 / 16 = 1

        1 & 16 = 1 -> 1  第二位
        1 / 16 = 0

        31  -> 1f

        十进制 => 62进制 道理同上

        长网址： https://leetcode.cn/problems/encode-and-decode-tinyurl/description/
        对应的短网址： http://tinyurl.com/4e9iAk
     */

    /**
     * 用随机数作为短网址标识
     */
    static class CodecRandom {
        private Map<String, String> longToShort = new HashMap<>();
        private Map<String, String> shortToLong = new HashMap<>();
        private static final String SHORT_PREFIX = "http://tinyurl.com";

        public String encode(String longUrl){
            String shortUrl = longToShort.get(longUrl);
            if(shortUrl != null){
                return shortUrl;
            }
            //生成短网址
            while(true){
                int id = ThreadLocalRandom.current().nextInt(); //获取随机整数
                shortUrl = SHORT_PREFIX + id;
                if(!shortToLong.containsKey(shortUrl)){  //避免出现同样的短网址
                    longToShort.put(longUrl, shortUrl);
                    shortToLong.put(shortUrl, longUrl);
                    break;
                }
            }
            return shortUrl;

        }
        public String decode(String shortUrl){
            return shortToLong.get(shortUrl);
        }
   }

    /**
     * 用Hash码作为短网址标识
     */
   static class CodecHashCode{
        private Map<String, String> longToShort = new HashMap<>();
        private Map<String, String> shortToLong = new HashMap<>();
        private static final String SHORT_PREFIX = "http://tinyurl.com";

        public String encode(String longUrl){
            String shortUrl = longToShort.get(longUrl);
            if(shortUrl != null){
                return shortUrl;
            }
            //生成短网址
            int id = longUrl.hashCode(); //获取长网址的Hash码作为短网址的标识
            while(true){
                shortUrl = SHORT_PREFIX + id;
                if(!shortToLong.containsKey(shortUrl)){  //避免出现同样的短网址
                    longToShort.put(longUrl, shortUrl);
                    shortToLong.put(shortUrl, longUrl);
                    break;
                }
                id++;
            }
            return shortUrl;

        }
        public String decode(String shortUrl){
            return shortToLong.get(shortUrl);
        }

   }

    /**
     * 用递增数来作为短网址的标识
     */
    static class CodecSequence{
       //对照表， 用于十进制和62进制的转换
        private static final char[] toBase62 = {
               '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
               'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
               'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
               'U', 'V', 'W', 'X', 'Y', 'Z',
               'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
               'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
               'u', 'v', 'w', 'x', 'y', 'z'
        };

        // 将10进制数字转换为62进制数字
        public static String toBase62(int number){
            if(number == 0){
                return String.valueOf(toBase62[0]);
            }
            StringBuilder sb = new StringBuilder();
            while(number > 0){
                int r = number % 62;  //拿到了对照表的“索引”
                sb.append(toBase62[r]);
                number = number / 62;
            }
            return sb.reverse().toString();
        }
        private Map<String, String> longToShort = new HashMap<>();
        private Map<String, String> shortToLong = new HashMap<>();
        private static final String SHORT_PREFIX = "http://tinyurl.com";
        private static int id = 1; //用于标识短网址的id，每次进行递增

        public String encode(String longUrl){
            String shortUrl = longToShort.get(longUrl);
            if(shortUrl != null){
                return shortUrl;
            }
            //生成短网址
            shortUrl = SHORT_PREFIX + toBase62(id);
            longToShort.put(longUrl, shortUrl);
            shortToLong.put(shortUrl, longUrl);
            id++;
            return shortUrl;

        }
        public String decode(String shortUrl){
            return shortToLong.get(shortUrl);
        }

    }

}
