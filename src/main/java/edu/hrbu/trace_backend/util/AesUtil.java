package edu.hrbu.trace_backend.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

@Slf4j
public class AesUtil {
    private static final String AES_KEY = "sxbagtlSXBAGTL318957%@^&";
    private static SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, AES_KEY.getBytes(StandardCharsets.UTF_8));

    public static String encryptHex(String origin) {
        return aes.encryptHex(origin);
    }

    public static String decryptStr(String encrypted) {
        return aes.decryptStr(encrypted, CharsetUtil.CHARSET_UTF_8);
    }
}
