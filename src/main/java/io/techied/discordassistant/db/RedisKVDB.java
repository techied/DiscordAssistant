package io.techied.discordassistant.db;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.net.URI;

public class RedisKVDB implements KVDB {

    JedisPool jedis;
    Jedis resource;

    public RedisKVDB(URI uri) {
        jedis = new JedisPool(uri);
        resource = jedis.getResource();
    }

    @Override
    public String get(String key) {
        return resource.get(key);
    }

    @Override
    public Boolean exists(String key) {
        return resource.exists(key);
    }

    @Override
    public String getInfo() {
        return resource.info();
    }

    @Override
    public void set(String key, String value) {
        resource.set(key, value);
    }

    @Override
    public void del(String key) {
        resource.del(key);
    }


}
