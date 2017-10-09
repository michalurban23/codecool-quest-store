package com.codecool.rmbk.dao;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordHash {

    public static String hash(String originalString, String salt) {

        String hashed = DigestUtils.sha256Hex(salt + originalString);

        return hashed;
    }
}
