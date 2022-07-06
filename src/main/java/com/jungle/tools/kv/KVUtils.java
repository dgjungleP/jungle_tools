package com.jungle.tools.kv;


public class KVUtils {
    private KVUtils() {
    }


    public static KVPair<String, String> queryKV(String kvString, String split) {
        KVPair<String, String> result = new KVPair<>();
        String[] kvSplit = kvString.split(split);
        String key;
        String value;
        if (kvSplit.length < 1) {
            return result;
        } else if (kvSplit.length < 2) {
            key = kvSplit[0].trim();
            value = "";
        } else {
            key = kvSplit[0].trim();
            value = kvSplit[1].trim();
        }
        result.setKey(key);
        result.setValue(value);
        return result;
    }
}
