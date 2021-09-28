package io.techied.discordassistant.db;

import java.util.HashMap;

public class EphemeralKVDB implements KVDB {

    // HAha, using a HashMap as a database, I know. But it's just for testing purposes without needing an internet connection or to spin up a database.
    HashMap<String, String> database;

    public EphemeralKVDB() {
        database = new HashMap<>();
    }

    @Override
    public String get(String key) {
        return database.get(key);
    }

    @Override
    public Boolean exists(String key) {
        return database.containsKey(key);
    }

    @Override
    public String getInfo() {
        return database.toString();
    }

    @Override
    public void set(String key, String value) {
        database.put(key, value);
    }

    @Override
    public void del(String key) {
        database.remove(key);
    }
}
