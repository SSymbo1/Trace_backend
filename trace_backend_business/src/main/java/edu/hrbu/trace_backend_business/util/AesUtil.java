package edu.hrbu.trace_backend_business.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import edu.hrbu.trace_backend_business.entity.enums.Secret;

import java.nio.charset.StandardCharsets;

public class AesUtil {
    private static final SymmetricCrypto aes = new SymmetricCrypto(
            SymmetricAlgorithm.AES,
            Secret.AES.getValue().getBytes(StandardCharsets.UTF_8)
    );

    public static String encryptHex(String origin) {
        return aes.encryptHex(origin);
    }

    public static String decryptStr(String encrypted) {
        return aes.decryptStr(encrypted, CharsetUtil.CHARSET_UTF_8);
    }
}
