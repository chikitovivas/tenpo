package com.example.tenpo.utils;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class HashUtils {
    public static String hash(String str) {
        return Hashing.sha256()
                .hashString(str, StandardCharsets.UTF_8)
                .toString();
    }
}
