package com.codecool.rmbk.dao;

import org.apache.commons.codec.digest.DigestUtils;
import java.util.Random;

class PasswordHash {

    static String hash(String originalString, String salt) {

        String hashed = DigestUtils.sha256Hex(salt + originalString);

        return hashed;
    }

    static String getSalt() {

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 20;

        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);

        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit +
                    (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }

        return buffer.toString();
    }

}
