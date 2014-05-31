import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
  
import redis.clients.jedis.Jedis;  
  
  
public class Demo {  
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
          
        //����redis����  
        Jedis jedis = new Jedis("192.168.0.225",6400);  
          
        //������֤-�����û������redis����ɲ���֤����ʹ���������  
        jedis.auth("abcdefg");  
          
        //�򵥵�key-value �洢  
        jedis.set("redis", "myredis");  
        System.out.println(jedis.get("redis"));  
          
        //��ԭ��ֵ�û��������,����֮ǰû�и�key�������key  
        //֮ǰ�Ѿ��趨��redis��Ӧ"myredis",�˾�ִ�б��ʹredis��Ӧ"myredisyourredis"  
        jedis.append("redis", "yourredis");     
        jedis.append("content", "rabbit");  
          
        //mset �����ö��key-valueֵ   ������key1,value1,key2,value2,...,keyn,valuen��  
        //mget �ǻ�ȡ���key����Ӧ��valueֵ  ������key1,key2,key3,...,keyn��  ���ص��Ǹ�list  
        jedis.mset("name1","yangw","name2","demon","name3","elena");  
        System.out.println(jedis.mget("name1","name2","name3"));  
          
        //map  
        Map<String,String> user = new HashMap<String,String>();  
        user.put("name", "cd");  
        user.put("password", "123456");  
        //map����redis  
        jedis.hmset("user", user);  
        //mapkey����  
        System.out.println(String.format("len:%d", jedis.hlen("user")));  
        //map�е����м�ֵ  
        System.out.println(String.format("keys: %s", jedis.hkeys("user") ));  
        //map�е�����value  
        System.out.println(String.format("values: %s", jedis.hvals("user") ));  
        //ȡ��map�е�name�ֶ�ֵ  
        List<String> rsmap = jedis.hmget("user", "name","password");  
        System.out.println(rsmap);  
        //ɾ��map�е�ĳһ����ֵ password  
        jedis.hdel("user", "password");  
        System.out.println(jedis.hmget("user", "name", "password"));  
          
        //list  
        jedis.del("listDemo");  
        System.out.println(jedis.lrange("listDemo", 0, -1));  
        jedis.lpush("listDemo", "A");  
        jedis.lpush("listDemo", "B");  
        jedis.lpush("listDemo", "C");  
        System.out.println(jedis.lrange("listDemo", 0, -1));  
        System.out.println(jedis.lrange("listDemo", 0, 1));  
          
        //set  
        jedis.sadd("sname", "wobby");  
        jedis.sadd("sname", "kings");  
        jedis.sadd("sname", "demon");  
        System.out.println(String.format("set num: %d", jedis.scard("sname")));  
        System.out.println(String.format("all members: %s", jedis.smembers("sname")));  
        System.out.println(String.format("is member: %B", jedis.sismember("sname", "wobby")));  
        System.out.println(String.format("rand member: %s", jedis.srandmember("sname")));  
        //ɾ��һ������  
        jedis.srem("sname", "demon");  
        System.out.println(String.format("all members: %s", jedis.smembers("sname")));        
    }  
  
}  