package com.codecool.rmbk.dao;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordHash {

    public static String hash(String originalString, String salt) {

        String sha256hex = DigestUtils.sha256Hex(salt + originalString);

        return sha256hex;
    }
}
