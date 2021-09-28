package io.techied.discordassistant.db;

public interface KVDB {
    String get(String key);

    Boolean exists(String key);

    String getInfo();

    void set(String key, String value);

    void del(String key);
}
