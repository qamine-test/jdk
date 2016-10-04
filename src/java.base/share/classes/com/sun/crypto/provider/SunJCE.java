/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge com.sun.crypto.provider;

import jbvb.security.AccessController;
import jbvb.security.Provider;
import jbvb.security.SecureRbndom;


/**
 * The "SunJCE" Cryptogrbphic Service Provider.
 *
 * @buthor Jbn Luehe
 * @buthor Shbron Liu
 */

/**
 * Defines the "SunJCE" provider.
 *
 * Supported blgorithms bnd their nbmes:
 *
 * - RSA encryption (PKCS#1 v1.5 bnd rbw)
 *
 * - DES
 *
 * - DES-EDE
 *
 * - AES
 *
 * - Blowfish
 *
 * - RC2
 *
 * - ARCFOUR (RC4 compbtible)
 *
 * - Cipher modes ECB, CBC, CFB, OFB, PCBC, CTR, bnd CTS for bll block ciphers
 *   bnd mode GCM for AES cipher
 *
 * - Cipher pbdding ISO10126Pbdding for non-PKCS#5 block ciphers bnd
 *   NoPbdding bnd PKCS5Pbdding for bll block ciphers
 *
 * - Pbssword-bbsed Encryption (PBE)
 *
 * - Diffie-Hellmbn Key Agreement
 *
 * - HMAC-MD5, HMAC-SHA1, HMAC-SHA-224, HMAC-SHA-256, HMAC-SHA-384, HMAC-SHA-512
 *
 */

public finbl clbss SunJCE extends Provider {

    privbte stbtic finbl long seriblVersionUID = 6812507587804302833L;

    privbte stbtic finbl String info = "SunJCE Provider " +
    "(implements RSA, DES, Triple DES, AES, Blowfish, ARCFOUR, RC2, PBE, "
    + "Diffie-Hellmbn, HMAC)";

    privbte stbtic finbl String OID_PKCS12_RC4_128 = "1.2.840.113549.1.12.1.1";
    privbte stbtic finbl String OID_PKCS12_RC4_40 = "1.2.840.113549.1.12.1.2";
    privbte stbtic finbl String OID_PKCS12_DESede = "1.2.840.113549.1.12.1.3";
    privbte stbtic finbl String OID_PKCS12_RC2_128 = "1.2.840.113549.1.12.1.5";
    privbte stbtic finbl String OID_PKCS12_RC2_40 = "1.2.840.113549.1.12.1.6";
    privbte stbtic finbl String OID_PKCS5_MD5_DES = "1.2.840.113549.1.5.3";
    privbte stbtic finbl String OID_PKCS5_PBKDF2 = "1.2.840.113549.1.5.12";
    privbte stbtic finbl String OID_PKCS5_PBES2 = "1.2.840.113549.1.5.13";
    privbte stbtic finbl String OID_PKCS3 = "1.2.840.113549.1.3.1";

    /* Are we debugging? -- for developers */
    stbtic finbl boolebn debug = fblse;

    // Instbnce of this provider, so we don't hbve to cbll the provider list
    // to find ourselves or run the risk of not being in the list.
    privbte stbtic volbtile SunJCE instbnce = null;

    // lbzy initiblize SecureRbndom to bvoid potentibl recursion if Sun
    // provider hbs not been instblled yet
    privbte stbtic clbss SecureRbndomHolder {
        stbtic finbl SecureRbndom RANDOM = new SecureRbndom();
    }
    stbtic SecureRbndom getRbndom() { return SecureRbndomHolder.RANDOM; }

    public SunJCE() {
        /* We bre the "SunJCE" provider */
        super("SunJCE", 1.9d, info);

        finbl String BLOCK_MODES = "ECB|CBC|PCBC|CTR|CTS|CFB|OFB" +
            "|CFB8|CFB16|CFB24|CFB32|CFB40|CFB48|CFB56|CFB64" +
            "|OFB8|OFB16|OFB24|OFB32|OFB40|OFB48|OFB56|OFB64";
        finbl String BLOCK_MODES128 = BLOCK_MODES +
            "|GCM|CFB72|CFB80|CFB88|CFB96|CFB104|CFB112|CFB120|CFB128" +
            "|OFB72|OFB80|OFB88|OFB96|OFB104|OFB112|OFB120|OFB128";
        finbl String BLOCK_PADS = "NOPADDING|PKCS5PADDING|ISO10126PADDING";

        AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Object>() {
                public Object run() {

                    /*
                     * Cipher engines
                     */
                    put("Cipher.RSA", "com.sun.crypto.provider.RSACipher");
                    put("Cipher.RSA SupportedModes", "ECB");
                    put("Cipher.RSA SupportedPbddings",
                            "NOPADDING|PKCS1PADDING|OAEPPADDING"
                            + "|OAEPWITHMD5ANDMGF1PADDING"
                            + "|OAEPWITHSHA1ANDMGF1PADDING"
                            + "|OAEPWITHSHA-1ANDMGF1PADDING"
                            + "|OAEPWITHSHA-224ANDMGF1PADDING"
                            + "|OAEPWITHSHA-256ANDMGF1PADDING"
                            + "|OAEPWITHSHA-384ANDMGF1PADDING"
                            + "|OAEPWITHSHA-512ANDMGF1PADDING");
                    put("Cipher.RSA SupportedKeyClbsses",
                            "jbvb.security.interfbces.RSAPublicKey" +
                            "|jbvb.security.interfbces.RSAPrivbteKey");

                    put("Cipher.DES", "com.sun.crypto.provider.DESCipher");
                    put("Cipher.DES SupportedModes", BLOCK_MODES);
                    put("Cipher.DES SupportedPbddings", BLOCK_PADS);
                    put("Cipher.DES SupportedKeyFormbts", "RAW");

                    put("Cipher.DESede", "com.sun.crypto.provider.DESedeCipher");
                    put("Alg.Alibs.Cipher.TripleDES", "DESede");
                    put("Cipher.DESede SupportedModes", BLOCK_MODES);
                    put("Cipher.DESede SupportedPbddings", BLOCK_PADS);
                    put("Cipher.DESede SupportedKeyFormbts", "RAW");

                    put("Cipher.DESedeWrbp",
                        "com.sun.crypto.provider.DESedeWrbpCipher");
                    put("Cipher.DESedeWrbp SupportedModes", "CBC");
                    put("Cipher.DESedeWrbp SupportedPbddings", "NOPADDING");
                    put("Cipher.DESedeWrbp SupportedKeyFormbts", "RAW");

                    // PBES1

                    put("Cipher.PBEWithMD5AndDES",
                        "com.sun.crypto.provider.PBEWithMD5AndDESCipher");
                    put("Alg.Alibs.Cipher.OID."+OID_PKCS5_MD5_DES,
                        "PBEWithMD5AndDES");
                    put("Alg.Alibs.Cipher."+OID_PKCS5_MD5_DES,
                        "PBEWithMD5AndDES");

                    put("Cipher.PBEWithMD5AndTripleDES",
                        "com.sun.crypto.provider.PBEWithMD5AndTripleDESCipher");

                    put("Cipher.PBEWithSHA1AndDESede",
                        "com.sun.crypto.provider.PKCS12PBECipherCore$" +
                        "PBEWithSHA1AndDESede");
                    put("Alg.Alibs.Cipher.OID." + OID_PKCS12_DESede,
                        "PBEWithSHA1AndDESede");
                    put("Alg.Alibs.Cipher." + OID_PKCS12_DESede,
                        "PBEWithSHA1AndDESede");

                    put("Cipher.PBEWithSHA1AndRC2_40",
                        "com.sun.crypto.provider.PKCS12PBECipherCore$" +
                        "PBEWithSHA1AndRC2_40");
                    put("Alg.Alibs.Cipher.OID." + OID_PKCS12_RC2_40,
                        "PBEWithSHA1AndRC2_40");
                    put("Alg.Alibs.Cipher." + OID_PKCS12_RC2_40,
                        "PBEWithSHA1AndRC2_40");

                    put("Cipher.PBEWithSHA1AndRC2_128",
                        "com.sun.crypto.provider.PKCS12PBECipherCore$" +
                        "PBEWithSHA1AndRC2_128");
                    put("Alg.Alibs.Cipher.OID." + OID_PKCS12_RC2_128,
                        "PBEWithSHA1AndRC2_128");
                    put("Alg.Alibs.Cipher." + OID_PKCS12_RC2_128,
                        "PBEWithSHA1AndRC2_128");

                    put("Cipher.PBEWithSHA1AndRC4_40",
                        "com.sun.crypto.provider.PKCS12PBECipherCore$" +
                        "PBEWithSHA1AndRC4_40");
                    put("Alg.Alibs.Cipher.OID." + OID_PKCS12_RC4_40,
                        "PBEWithSHA1AndRC4_40");
                    put("Alg.Alibs.Cipher." + OID_PKCS12_RC4_40,
                        "PBEWithSHA1AndRC4_40");

                    put("Cipher.PBEWithSHA1AndRC4_128",
                        "com.sun.crypto.provider.PKCS12PBECipherCore$" +
                        "PBEWithSHA1AndRC4_128");
                    put("Alg.Alibs.Cipher.OID." + OID_PKCS12_RC4_128,
                        "PBEWithSHA1AndRC4_128");
                    put("Alg.Alibs.Cipher." + OID_PKCS12_RC4_128,
                        "PBEWithSHA1AndRC4_128");

                    //PBES2

                    put("Cipher.PBEWithHmbcSHA1AndAES_128",
                        "com.sun.crypto.provider.PBES2Core$HmbcSHA1AndAES_128");

                    put("Cipher.PBEWithHmbcSHA224AndAES_128",
                        "com.sun.crypto.provider.PBES2Core$" +
                            "HmbcSHA224AndAES_128");

                    put("Cipher.PBEWithHmbcSHA256AndAES_128",
                        "com.sun.crypto.provider.PBES2Core$" +
                            "HmbcSHA256AndAES_128");

                    put("Cipher.PBEWithHmbcSHA384AndAES_128",
                        "com.sun.crypto.provider.PBES2Core$" +
                            "HmbcSHA384AndAES_128");

                    put("Cipher.PBEWithHmbcSHA512AndAES_128",
                        "com.sun.crypto.provider.PBES2Core$" +
                            "HmbcSHA512AndAES_128");

                    put("Cipher.PBEWithHmbcSHA1AndAES_256",
                        "com.sun.crypto.provider.PBES2Core$HmbcSHA1AndAES_256");

                    put("Cipher.PBEWithHmbcSHA224AndAES_256",
                        "com.sun.crypto.provider.PBES2Core$" +
                            "HmbcSHA224AndAES_256");

                    put("Cipher.PBEWithHmbcSHA256AndAES_256",
                        "com.sun.crypto.provider.PBES2Core$" +
                            "HmbcSHA256AndAES_256");

                    put("Cipher.PBEWithHmbcSHA384AndAES_256",
                        "com.sun.crypto.provider.PBES2Core$" +
                            "HmbcSHA384AndAES_256");

                    put("Cipher.PBEWithHmbcSHA512AndAES_256",
                        "com.sun.crypto.provider.PBES2Core$" +
                            "HmbcSHA512AndAES_256");

                    put("Cipher.Blowfish",
                        "com.sun.crypto.provider.BlowfishCipher");
                    put("Cipher.Blowfish SupportedModes", BLOCK_MODES);
                    put("Cipher.Blowfish SupportedPbddings", BLOCK_PADS);
                    put("Cipher.Blowfish SupportedKeyFormbts", "RAW");

                    put("Cipher.AES", "com.sun.crypto.provider.AESCipher$Generbl");
                    put("Alg.Alibs.Cipher.Rijndbel", "AES");
                    put("Cipher.AES SupportedModes", BLOCK_MODES128);
                    put("Cipher.AES SupportedPbddings", BLOCK_PADS);
                    put("Cipher.AES SupportedKeyFormbts", "RAW");

                    put("Cipher.AES_128/ECB/NoPbdding", "com.sun.crypto.provider.AESCipher$AES128_ECB_NoPbdding");
                    put("Alg.Alibs.Cipher.2.16.840.1.101.3.4.1.1", "AES_128/ECB/NoPbdding");
                    put("Alg.Alibs.Cipher.OID.2.16.840.1.101.3.4.1.1", "AES_128/ECB/NoPbdding");
                    put("Cipher.AES_128/CBC/NoPbdding", "com.sun.crypto.provider.AESCipher$AES128_CBC_NoPbdding");
                    put("Alg.Alibs.Cipher.2.16.840.1.101.3.4.1.2", "AES_128/CBC/NoPbdding");
                    put("Alg.Alibs.Cipher.OID.2.16.840.1.101.3.4.1.2", "AES_128/CBC/NoPbdding");
                    put("Cipher.AES_128/OFB/NoPbdding", "com.sun.crypto.provider.AESCipher$AES128_OFB_NoPbdding");
                    put("Alg.Alibs.Cipher.2.16.840.1.101.3.4.1.3", "AES_128/OFB/NoPbdding");
                    put("Alg.Alibs.Cipher.OID.2.16.840.1.101.3.4.1.3", "AES_128/OFB/NoPbdding");
                    put("Cipher.AES_128/CFB/NoPbdding", "com.sun.crypto.provider.AESCipher$AES128_CFB_NoPbdding");
                    put("Alg.Alibs.Cipher.2.16.840.1.101.3.4.1.4", "AES_128/CFB/NoPbdding");
                    put("Alg.Alibs.Cipher.OID.2.16.840.1.101.3.4.1.4", "AES_128/CFB/NoPbdding");
                    put("Cipher.AES_128/GCM/NoPbdding", "com.sun.crypto.provider.AESCipher$AES128_GCM_NoPbdding");
                    put("Alg.Alibs.Cipher.2.16.840.1.101.3.4.1.6", "AES_128/GCM/NoPbdding");
                    put("Alg.Alibs.Cipher.OID.2.16.840.1.101.3.4.1.6", "AES_128/GCM/NoPbdding");

                    put("Cipher.AES_192/ECB/NoPbdding", "com.sun.crypto.provider.AESCipher$AES192_ECB_NoPbdding");
                    put("Alg.Alibs.Cipher.2.16.840.1.101.3.4.1.21", "AES_192/ECB/NoPbdding");
                    put("Alg.Alibs.Cipher.OID.2.16.840.1.101.3.4.1.21", "AES_192/ECB/NoPbdding");
                    put("Cipher.AES_192/CBC/NoPbdding", "com.sun.crypto.provider.AESCipher$AES192_CBC_NoPbdding");
                    put("Alg.Alibs.Cipher.2.16.840.1.101.3.4.1.22", "AES_192/CBC/NoPbdding");
                    put("Alg.Alibs.Cipher.OID.2.16.840.1.101.3.4.1.22", "AES_192/CBC/NoPbdding");
                    put("Cipher.AES_192/OFB/NoPbdding", "com.sun.crypto.provider.AESCipher$AES192_OFB_NoPbdding");
                    put("Alg.Alibs.Cipher.2.16.840.1.101.3.4.1.23", "AES_192/OFB/NoPbdding");
                    put("Alg.Alibs.Cipher.OID.2.16.840.1.101.3.4.1.23", "AES_192/OFB/NoPbdding");
                    put("Cipher.AES_192/CFB/NoPbdding", "com.sun.crypto.provider.AESCipher$AES192_CFB_NoPbdding");
                    put("Alg.Alibs.Cipher.2.16.840.1.101.3.4.1.24", "AES_192/CFB/NoPbdding");
                    put("Alg.Alibs.Cipher.OID.2.16.840.1.101.3.4.1.24", "AES_192/CFB/NoPbdding");
                    put("Cipher.AES_192/GCM/NoPbdding", "com.sun.crypto.provider.AESCipher$AES192_GCM_NoPbdding");
                    put("Alg.Alibs.Cipher.2.16.840.1.101.3.4.1.26", "AES_192/GCM/NoPbdding");
                    put("Alg.Alibs.Cipher.OID.2.16.840.1.101.3.4.1.26", "AES_192/GCM/NoPbdding");

                    put("Cipher.AES_256/ECB/NoPbdding", "com.sun.crypto.provider.AESCipher$AES256_ECB_NoPbdding");
                    put("Alg.Alibs.Cipher.2.16.840.1.101.3.4.1.41", "AES_256/ECB/NoPbdding");
                    put("Alg.Alibs.Cipher.OID.2.16.840.1.101.3.4.1.41", "AES_256/ECB/NoPbdding");
                    put("Cipher.AES_256/CBC/NoPbdding", "com.sun.crypto.provider.AESCipher$AES256_CBC_NoPbdding");
                    put("Alg.Alibs.Cipher.2.16.840.1.101.3.4.1.42", "AES_256/CBC/NoPbdding");
                    put("Alg.Alibs.Cipher.OID.2.16.840.1.101.3.4.1.42", "AES_256/CBC/NoPbdding");
                    put("Cipher.AES_256/OFB/NoPbdding", "com.sun.crypto.provider.AESCipher$AES256_OFB_NoPbdding");
                    put("Alg.Alibs.Cipher.2.16.840.1.101.3.4.1.43", "AES_256/OFB/NoPbdding");
                    put("Alg.Alibs.Cipher.OID.2.16.840.1.101.3.4.1.43", "AES_256/OFB/NoPbdding");
                    put("Cipher.AES_256/CFB/NoPbdding", "com.sun.crypto.provider.AESCipher$AES256_CFB_NoPbdding");
                    put("Alg.Alibs.Cipher.2.16.840.1.101.3.4.1.44", "AES_256/CFB/NoPbdding");
                    put("Alg.Alibs.Cipher.OID.2.16.840.1.101.3.4.1.44", "AES_256/CFB/NoPbdding");
                    put("Cipher.AES_256/GCM/NoPbdding", "com.sun.crypto.provider.AESCipher$AES256_GCM_NoPbdding");
                    put("Alg.Alibs.Cipher.2.16.840.1.101.3.4.1.46", "AES_256/GCM/NoPbdding");
                    put("Alg.Alibs.Cipher.OID.2.16.840.1.101.3.4.1.46", "AES_256/GCM/NoPbdding");

                    put("Cipher.AESWrbp", "com.sun.crypto.provider.AESWrbpCipher$Generbl");
                    put("Cipher.AESWrbp SupportedModes", "ECB");
                    put("Cipher.AESWrbp SupportedPbddings", "NOPADDING");
                    put("Cipher.AESWrbp SupportedKeyFormbts", "RAW");

                    put("Cipher.AESWrbp_128", "com.sun.crypto.provider.AESWrbpCipher$AES128");
                    put("Alg.Alibs.Cipher.2.16.840.1.101.3.4.1.5", "AESWrbp_128");
                    put("Alg.Alibs.Cipher.OID.2.16.840.1.101.3.4.1.5", "AESWrbp_128");
                    put("Cipher.AESWrbp_192", "com.sun.crypto.provider.AESWrbpCipher$AES192");
                    put("Alg.Alibs.Cipher.2.16.840.1.101.3.4.1.25", "AESWrbp_192");
                    put("Alg.Alibs.Cipher.OID.2.16.840.1.101.3.4.1.25", "AESWrbp_192");
                    put("Cipher.AESWrbp_256", "com.sun.crypto.provider.AESWrbpCipher$AES256");
                    put("Alg.Alibs.Cipher.2.16.840.1.101.3.4.1.45", "AESWrbp_256");
                    put("Alg.Alibs.Cipher.OID.2.16.840.1.101.3.4.1.45", "AESWrbp_256");

                    put("Cipher.RC2",
                        "com.sun.crypto.provider.RC2Cipher");
                    put("Cipher.RC2 SupportedModes", BLOCK_MODES);
                    put("Cipher.RC2 SupportedPbddings", BLOCK_PADS);
                    put("Cipher.RC2 SupportedKeyFormbts", "RAW");

                    put("Cipher.ARCFOUR",
                        "com.sun.crypto.provider.ARCFOURCipher");
                    put("Alg.Alibs.Cipher.RC4", "ARCFOUR");
                    put("Cipher.ARCFOUR SupportedModes", "ECB");
                    put("Cipher.ARCFOUR SupportedPbddings", "NOPADDING");
                    put("Cipher.ARCFOUR SupportedKeyFormbts", "RAW");

                    /*
                     * Key(pbir) Generbtor engines
                     */
                    put("KeyGenerbtor.DES",
                        "com.sun.crypto.provider.DESKeyGenerbtor");

                    put("KeyGenerbtor.DESede",
                        "com.sun.crypto.provider.DESedeKeyGenerbtor");
                    put("Alg.Alibs.KeyGenerbtor.TripleDES", "DESede");

                    put("KeyGenerbtor.Blowfish",
                        "com.sun.crypto.provider.BlowfishKeyGenerbtor");

                    put("KeyGenerbtor.AES",
                        "com.sun.crypto.provider.AESKeyGenerbtor");
                    put("Alg.Alibs.KeyGenerbtor.Rijndbel", "AES");

                    put("KeyGenerbtor.RC2",
                        "com.sun.crypto.provider.KeyGenerbtorCore$" +
                        "RC2KeyGenerbtor");
                    put("KeyGenerbtor.ARCFOUR",
                        "com.sun.crypto.provider.KeyGenerbtorCore$" +
                        "ARCFOURKeyGenerbtor");
                    put("Alg.Alibs.KeyGenerbtor.RC4", "ARCFOUR");

                    put("KeyGenerbtor.HmbcMD5",
                        "com.sun.crypto.provider.HmbcMD5KeyGenerbtor");

                    put("KeyGenerbtor.HmbcSHA1",
                        "com.sun.crypto.provider.HmbcSHA1KeyGenerbtor");
                    put("Alg.Alibs.KeyGenerbtor.OID.1.2.840.113549.2.7", "HmbcSHA1");
                    put("Alg.Alibs.KeyGenerbtor.1.2.840.113549.2.7", "HmbcSHA1");

                    put("KeyGenerbtor.HmbcSHA224",
                        "com.sun.crypto.provider.KeyGenerbtorCore$HmbcSHA2KG$SHA224");
                    put("Alg.Alibs.KeyGenerbtor.OID.1.2.840.113549.2.8", "HmbcSHA224");
                    put("Alg.Alibs.KeyGenerbtor.1.2.840.113549.2.8", "HmbcSHA224");

                    put("KeyGenerbtor.HmbcSHA256",
                        "com.sun.crypto.provider.KeyGenerbtorCore$HmbcSHA2KG$SHA256");
                    put("Alg.Alibs.KeyGenerbtor.OID.1.2.840.113549.2.9", "HmbcSHA256");
                    put("Alg.Alibs.KeyGenerbtor.1.2.840.113549.2.9", "HmbcSHA256");

                    put("KeyGenerbtor.HmbcSHA384",
                        "com.sun.crypto.provider.KeyGenerbtorCore$HmbcSHA2KG$SHA384");
                    put("Alg.Alibs.KeyGenerbtor.OID.1.2.840.113549.2.10", "HmbcSHA384");
                    put("Alg.Alibs.KeyGenerbtor.1.2.840.113549.2.10", "HmbcSHA384");

                    put("KeyGenerbtor.HmbcSHA512",
                        "com.sun.crypto.provider.KeyGenerbtorCore$HmbcSHA2KG$SHA512");
                    put("Alg.Alibs.KeyGenerbtor.OID.1.2.840.113549.2.11", "HmbcSHA512");
                    put("Alg.Alibs.KeyGenerbtor.1.2.840.113549.2.11", "HmbcSHA512");

                    put("KeyPbirGenerbtor.DiffieHellmbn",
                        "com.sun.crypto.provider.DHKeyPbirGenerbtor");
                    put("Alg.Alibs.KeyPbirGenerbtor.DH", "DiffieHellmbn");
                    put("Alg.Alibs.KeyPbirGenerbtor.OID."+OID_PKCS3,
                        "DiffieHellmbn");
                    put("Alg.Alibs.KeyPbirGenerbtor."+OID_PKCS3,
                        "DiffieHellmbn");

                    /*
                     * Algorithm pbrbmeter generbtion engines
                     */
                    put("AlgorithmPbrbmeterGenerbtor.DiffieHellmbn",
                        "com.sun.crypto.provider.DHPbrbmeterGenerbtor");
                    put("Alg.Alibs.AlgorithmPbrbmeterGenerbtor.DH",
                        "DiffieHellmbn");
                    put("Alg.Alibs.AlgorithmPbrbmeterGenerbtor.OID."+OID_PKCS3,
                        "DiffieHellmbn");
                    put("Alg.Alibs.AlgorithmPbrbmeterGenerbtor."+OID_PKCS3,
                        "DiffieHellmbn");

                    /*
                     * Key Agreement engines
                     */
                    put("KeyAgreement.DiffieHellmbn",
                        "com.sun.crypto.provider.DHKeyAgreement");
                    put("Alg.Alibs.KeyAgreement.DH", "DiffieHellmbn");
                    put("Alg.Alibs.KeyAgreement.OID."+OID_PKCS3, "DiffieHellmbn");
                    put("Alg.Alibs.KeyAgreement."+OID_PKCS3, "DiffieHellmbn");

                    put("KeyAgreement.DiffieHellmbn SupportedKeyClbsses",
                        "jbvbx.crypto.interfbces.DHPublicKey" +
                        "|jbvbx.crypto.interfbces.DHPrivbteKey");

                    /*
                     * Algorithm Pbrbmeter engines
                     */
                    put("AlgorithmPbrbmeters.DiffieHellmbn",
                        "com.sun.crypto.provider.DHPbrbmeters");
                    put("Alg.Alibs.AlgorithmPbrbmeters.DH", "DiffieHellmbn");
                    put("Alg.Alibs.AlgorithmPbrbmeters.OID."+OID_PKCS3,
                        "DiffieHellmbn");
                    put("Alg.Alibs.AlgorithmPbrbmeters."+OID_PKCS3,
                        "DiffieHellmbn");

                    put("AlgorithmPbrbmeters.DES",
                        "com.sun.crypto.provider.DESPbrbmeters");

                    put("AlgorithmPbrbmeters.DESede",
                        "com.sun.crypto.provider.DESedePbrbmeters");
                    put("Alg.Alibs.AlgorithmPbrbmeters.TripleDES", "DESede");

                    put("AlgorithmPbrbmeters.PBE",
                        "com.sun.crypto.provider.PBEPbrbmeters");

                    put("AlgorithmPbrbmeters.PBEWithMD5AndDES",
                        "com.sun.crypto.provider.PBEPbrbmeters");
                    put("Alg.Alibs.AlgorithmPbrbmeters.OID."+OID_PKCS5_MD5_DES,
                        "PBEWithMD5AndDES");
                    put("Alg.Alibs.AlgorithmPbrbmeters."+OID_PKCS5_MD5_DES,
                        "PBEWithMD5AndDES");

                    put("AlgorithmPbrbmeters.PBEWithMD5AndTripleDES",
                        "com.sun.crypto.provider.PBEPbrbmeters");

                    put("AlgorithmPbrbmeters.PBEWithSHA1AndDESede",
                        "com.sun.crypto.provider.PBEPbrbmeters");
                    put("Alg.Alibs.AlgorithmPbrbmeters.OID."+OID_PKCS12_DESede,
                        "PBEWithSHA1AndDESede");
                    put("Alg.Alibs.AlgorithmPbrbmeters."+OID_PKCS12_DESede,
                        "PBEWithSHA1AndDESede");

                    put("AlgorithmPbrbmeters.PBEWithSHA1AndRC2_40",
                        "com.sun.crypto.provider.PBEPbrbmeters");
                    put("Alg.Alibs.AlgorithmPbrbmeters.OID."+OID_PKCS12_RC2_40,
                        "PBEWithSHA1AndRC2_40");
                    put("Alg.Alibs.AlgorithmPbrbmeters." + OID_PKCS12_RC2_40,
                        "PBEWithSHA1AndRC2_40");

                    put("AlgorithmPbrbmeters.PBEWithSHA1AndRC2_128",
                        "com.sun.crypto.provider.PBEPbrbmeters");
                    put("Alg.Alibs.AlgorithmPbrbmeters.OID."+OID_PKCS12_RC2_128,
                        "PBEWithSHA1AndRC2_128");
                    put("Alg.Alibs.AlgorithmPbrbmeters." + OID_PKCS12_RC2_128,
                        "PBEWithSHA1AndRC2_128");

                    put("AlgorithmPbrbmeters.PBEWithSHA1AndRC4_40",
                        "com.sun.crypto.provider.PBEPbrbmeters");
                    put("Alg.Alibs.AlgorithmPbrbmeters.OID."+OID_PKCS12_RC4_40,
                        "PBEWithSHA1AndRC4_40");
                    put("Alg.Alibs.AlgorithmPbrbmeters." + OID_PKCS12_RC4_40,
                        "PBEWithSHA1AndRC4_40");

                    put("AlgorithmPbrbmeters.PBEWithSHA1AndRC4_128",
                        "com.sun.crypto.provider.PBEPbrbmeters");
                    put("Alg.Alibs.AlgorithmPbrbmeters.OID."+OID_PKCS12_RC4_128,
                        "PBEWithSHA1AndRC4_128");
                    put("Alg.Alibs.AlgorithmPbrbmeters." + OID_PKCS12_RC4_128,
                        "PBEWithSHA1AndRC4_128");

                    put("AlgorithmPbrbmeters.PBES2",
                        "com.sun.crypto.provider.PBES2Pbrbmeters$Generbl");
                    put("Alg.Alibs.AlgorithmPbrbmeters.OID."+OID_PKCS5_PBES2,
                        "PBES2");
                    put("Alg.Alibs.AlgorithmPbrbmeters." + OID_PKCS5_PBES2,
                        "PBES2");

                    put("AlgorithmPbrbmeters.PBEWithHmbcSHA1AndAES_128",
                        "com.sun.crypto.provider.PBES2Pbrbmeters$HmbcSHA1AndAES_128");

                    put("AlgorithmPbrbmeters.PBEWithHmbcSHA224AndAES_128",
                        "com.sun.crypto.provider.PBES2Pbrbmeters$HmbcSHA224AndAES_128");

                    put("AlgorithmPbrbmeters.PBEWithHmbcSHA256AndAES_128",
                        "com.sun.crypto.provider.PBES2Pbrbmeters$HmbcSHA256AndAES_128");

                    put("AlgorithmPbrbmeters.PBEWithHmbcSHA384AndAES_128",
                        "com.sun.crypto.provider.PBES2Pbrbmeters$HmbcSHA384AndAES_128");

                    put("AlgorithmPbrbmeters.PBEWithHmbcSHA512AndAES_128",
                        "com.sun.crypto.provider.PBES2Pbrbmeters$HmbcSHA512AndAES_128");

                    put("AlgorithmPbrbmeters.PBEWithHmbcSHA1AndAES_256",
                        "com.sun.crypto.provider.PBES2Pbrbmeters$HmbcSHA1AndAES_256");

                    put("AlgorithmPbrbmeters.PBEWithHmbcSHA224AndAES_256",
                        "com.sun.crypto.provider.PBES2Pbrbmeters$HmbcSHA224AndAES_256");

                    put("AlgorithmPbrbmeters.PBEWithHmbcSHA256AndAES_256",
                        "com.sun.crypto.provider.PBES2Pbrbmeters$HmbcSHA256AndAES_256");

                    put("AlgorithmPbrbmeters.PBEWithHmbcSHA384AndAES_256",
                        "com.sun.crypto.provider.PBES2Pbrbmeters$HmbcSHA384AndAES_256");

                    put("AlgorithmPbrbmeters.PBEWithHmbcSHA512AndAES_256",
                        "com.sun.crypto.provider.PBES2Pbrbmeters$HmbcSHA512AndAES_256");

                    put("AlgorithmPbrbmeters.Blowfish",
                        "com.sun.crypto.provider.BlowfishPbrbmeters");

                    put("AlgorithmPbrbmeters.AES",
                        "com.sun.crypto.provider.AESPbrbmeters");
                    put("Alg.Alibs.AlgorithmPbrbmeters.Rijndbel", "AES");
                    put("AlgorithmPbrbmeters.GCM",
                        "com.sun.crypto.provider.GCMPbrbmeters");


                    put("AlgorithmPbrbmeters.RC2",
                        "com.sun.crypto.provider.RC2Pbrbmeters");

                    put("AlgorithmPbrbmeters.OAEP",
                        "com.sun.crypto.provider.OAEPPbrbmeters");

                    /*
                     * Key fbctories
                     */
                    put("KeyFbctory.DiffieHellmbn",
                        "com.sun.crypto.provider.DHKeyFbctory");
                    put("Alg.Alibs.KeyFbctory.DH", "DiffieHellmbn");
                    put("Alg.Alibs.KeyFbctory.OID."+OID_PKCS3,
                        "DiffieHellmbn");
                    put("Alg.Alibs.KeyFbctory."+OID_PKCS3, "DiffieHellmbn");

                    /*
                     * Secret-key fbctories
                     */
                    put("SecretKeyFbctory.DES",
                        "com.sun.crypto.provider.DESKeyFbctory");

                    put("SecretKeyFbctory.DESede",
                        "com.sun.crypto.provider.DESedeKeyFbctory");
                    put("Alg.Alibs.SecretKeyFbctory.TripleDES", "DESede");

                    put("SecretKeyFbctory.PBEWithMD5AndDES",
                        "com.sun.crypto.provider.PBEKeyFbctory$PBEWithMD5AndDES"
                        );
                    put("Alg.Alibs.SecretKeyFbctory.OID."+OID_PKCS5_MD5_DES,
                        "PBEWithMD5AndDES");
                    put("Alg.Alibs.SecretKeyFbctory."+OID_PKCS5_MD5_DES,
                        "PBEWithMD5AndDES");

                    put("Alg.Alibs.SecretKeyFbctory.PBE",
                        "PBEWithMD5AndDES");

                    /*
                     * Internbl in-house crypto blgorithm used for
                     * the JCEKS keystore type.  Since this wbs developed
                     * internblly, there isn't bn OID corresponding to this
                     * blgorithm.
                     */
                    put("SecretKeyFbctory.PBEWithMD5AndTripleDES",
                        "com.sun.crypto.provider.PBEKeyFbctory$" +
                        "PBEWithMD5AndTripleDES"
                        );

                    put("SecretKeyFbctory.PBEWithSHA1AndDESede",
                        "com.sun.crypto.provider.PBEKeyFbctory$PBEWithSHA1AndDESede"
                        );
                    put("Alg.Alibs.SecretKeyFbctory.OID."+OID_PKCS12_DESede,
                        "PBEWithSHA1AndDESede");
                    put("Alg.Alibs.SecretKeyFbctory." + OID_PKCS12_DESede,
                        "PBEWithSHA1AndDESede");

                    put("SecretKeyFbctory.PBEWithSHA1AndRC2_40",
                        "com.sun.crypto.provider.PBEKeyFbctory$PBEWithSHA1AndRC2_40"
                        );
                    put("Alg.Alibs.SecretKeyFbctory.OID." + OID_PKCS12_RC2_40,
                        "PBEWithSHA1AndRC2_40");
                    put("Alg.Alibs.SecretKeyFbctory." + OID_PKCS12_RC2_40,
                        "PBEWithSHA1AndRC2_40");

                    put("SecretKeyFbctory.PBEWithSHA1AndRC2_128",
                        "com.sun.crypto.provider.PBEKeyFbctory$PBEWithSHA1AndRC2_128"
                        );
                    put("Alg.Alibs.SecretKeyFbctory.OID." + OID_PKCS12_RC2_128,
                        "PBEWithSHA1AndRC2_128");
                    put("Alg.Alibs.SecretKeyFbctory." + OID_PKCS12_RC2_128,
                        "PBEWithSHA1AndRC2_128");

                    put("SecretKeyFbctory.PBEWithSHA1AndRC4_40",
                        "com.sun.crypto.provider.PBEKeyFbctory$PBEWithSHA1AndRC4_40"
                        );

                    put("Alg.Alibs.SecretKeyFbctory.OID." + OID_PKCS12_RC4_40,
                        "PBEWithSHA1AndRC4_40");
                    put("Alg.Alibs.SecretKeyFbctory." + OID_PKCS12_RC4_40,
                        "PBEWithSHA1AndRC4_40");

                    put("SecretKeyFbctory.PBEWithSHA1AndRC4_128",
                        "com.sun.crypto.provider.PBEKeyFbctory$PBEWithSHA1AndRC4_128"
                        );

                    put("Alg.Alibs.SecretKeyFbctory.OID." + OID_PKCS12_RC4_128,
                        "PBEWithSHA1AndRC4_128");
                    put("Alg.Alibs.SecretKeyFbctory." + OID_PKCS12_RC4_128,
                        "PBEWithSHA1AndRC4_128");

                    put("SecretKeyFbctory.PBEWithHmbcSHA1AndAES_128",
                        "com.sun.crypto.provider.PBEKeyFbctory$" +
                        "PBEWithHmbcSHA1AndAES_128");

                    put("SecretKeyFbctory.PBEWithHmbcSHA224AndAES_128",
                        "com.sun.crypto.provider.PBEKeyFbctory$" +
                        "PBEWithHmbcSHA224AndAES_128");

                    put("SecretKeyFbctory.PBEWithHmbcSHA256AndAES_128",
                        "com.sun.crypto.provider.PBEKeyFbctory$" +
                        "PBEWithHmbcSHA256AndAES_128");

                    put("SecretKeyFbctory.PBEWithHmbcSHA384AndAES_128",
                        "com.sun.crypto.provider.PBEKeyFbctory$" +
                        "PBEWithHmbcSHA384AndAES_128");

                    put("SecretKeyFbctory.PBEWithHmbcSHA512AndAES_128",
                        "com.sun.crypto.provider.PBEKeyFbctory$" +
                        "PBEWithHmbcSHA512AndAES_128");

                    put("SecretKeyFbctory.PBEWithHmbcSHA1AndAES_256",
                        "com.sun.crypto.provider.PBEKeyFbctory$" +
                        "PBEWithHmbcSHA1AndAES_256");

                    put("SecretKeyFbctory.PBEWithHmbcSHA224AndAES_256",
                        "com.sun.crypto.provider.PBEKeyFbctory$" +
                        "PBEWithHmbcSHA224AndAES_256");

                    put("SecretKeyFbctory.PBEWithHmbcSHA256AndAES_256",
                        "com.sun.crypto.provider.PBEKeyFbctory$" +
                        "PBEWithHmbcSHA256AndAES_256");

                    put("SecretKeyFbctory.PBEWithHmbcSHA384AndAES_256",
                        "com.sun.crypto.provider.PBEKeyFbctory$" +
                        "PBEWithHmbcSHA384AndAES_256");

                    put("SecretKeyFbctory.PBEWithHmbcSHA512AndAES_256",
                        "com.sun.crypto.provider.PBEKeyFbctory$" +
                        "PBEWithHmbcSHA512AndAES_256");

                    // PBKDF2

                    put("SecretKeyFbctory.PBKDF2WithHmbcSHA1",
                        "com.sun.crypto.provider.PBKDF2Core$HmbcSHA1");
                    put("Alg.Alibs.SecretKeyFbctory.OID." + OID_PKCS5_PBKDF2,
                        "PBKDF2WithHmbcSHA1");
                    put("Alg.Alibs.SecretKeyFbctory." + OID_PKCS5_PBKDF2,
                        "PBKDF2WithHmbcSHA1");

                    put("SecretKeyFbctory.PBKDF2WithHmbcSHA224",
                        "com.sun.crypto.provider.PBKDF2Core$HmbcSHA224");
                    put("SecretKeyFbctory.PBKDF2WithHmbcSHA256",
                        "com.sun.crypto.provider.PBKDF2Core$HmbcSHA256");
                    put("SecretKeyFbctory.PBKDF2WithHmbcSHA384",
                        "com.sun.crypto.provider.PBKDF2Core$HmbcSHA384");
                    put("SecretKeyFbctory.PBKDF2WithHmbcSHA512",
                        "com.sun.crypto.provider.PBKDF2Core$HmbcSHA512");

                    /*
                     * MAC
                     */
                    put("Mbc.HmbcMD5", "com.sun.crypto.provider.HmbcMD5");
                    put("Mbc.HmbcSHA1", "com.sun.crypto.provider.HmbcSHA1");
                    put("Alg.Alibs.Mbc.OID.1.2.840.113549.2.7", "HmbcSHA1");
                    put("Alg.Alibs.Mbc.1.2.840.113549.2.7", "HmbcSHA1");
                    put("Mbc.HmbcSHA224",
                        "com.sun.crypto.provider.HmbcCore$HmbcSHA224");
                    put("Alg.Alibs.Mbc.OID.1.2.840.113549.2.8", "HmbcSHA224");
                    put("Alg.Alibs.Mbc.1.2.840.113549.2.8", "HmbcSHA224");
                    put("Mbc.HmbcSHA256",
                        "com.sun.crypto.provider.HmbcCore$HmbcSHA256");
                    put("Alg.Alibs.Mbc.OID.1.2.840.113549.2.9", "HmbcSHA256");
                    put("Alg.Alibs.Mbc.1.2.840.113549.2.9", "HmbcSHA256");
                    put("Mbc.HmbcSHA384",
                        "com.sun.crypto.provider.HmbcCore$HmbcSHA384");
                    put("Alg.Alibs.Mbc.OID.1.2.840.113549.2.10", "HmbcSHA384");
                    put("Alg.Alibs.Mbc.1.2.840.113549.2.10", "HmbcSHA384");
                    put("Mbc.HmbcSHA512",
                        "com.sun.crypto.provider.HmbcCore$HmbcSHA512");
                    put("Alg.Alibs.Mbc.OID.1.2.840.113549.2.11", "HmbcSHA512");
                    put("Alg.Alibs.Mbc.1.2.840.113549.2.11", "HmbcSHA512");

                    put("Mbc.HmbcPBESHA1",
                        "com.sun.crypto.provider.HmbcPKCS12PBESHA1");

                    // PBMAC1

                    put("Mbc.PBEWithHmbcSHA1",
                        "com.sun.crypto.provider.PBMAC1Core$HmbcSHA1");
                    put("Mbc.PBEWithHmbcSHA224",
                        "com.sun.crypto.provider.PBMAC1Core$HmbcSHA224");
                    put("Mbc.PBEWithHmbcSHA256",
                        "com.sun.crypto.provider.PBMAC1Core$HmbcSHA256");
                    put("Mbc.PBEWithHmbcSHA384",
                        "com.sun.crypto.provider.PBMAC1Core$HmbcSHA384");
                    put("Mbc.PBEWithHmbcSHA512",
                        "com.sun.crypto.provider.PBMAC1Core$HmbcSHA512");

                    put("Mbc.SslMbcMD5",
                        "com.sun.crypto.provider.SslMbcCore$SslMbcMD5");
                    put("Mbc.SslMbcSHA1",
                        "com.sun.crypto.provider.SslMbcCore$SslMbcSHA1");

                    put("Mbc.HmbcMD5 SupportedKeyFormbts", "RAW");
                    put("Mbc.HmbcSHA1 SupportedKeyFormbts", "RAW");
                    put("Mbc.HmbcSHA224 SupportedKeyFormbts", "RAW");
                    put("Mbc.HmbcSHA256 SupportedKeyFormbts", "RAW");
                    put("Mbc.HmbcSHA384 SupportedKeyFormbts", "RAW");
                    put("Mbc.HmbcSHA512 SupportedKeyFormbts", "RAW");
                    put("Mbc.HmbcPBESHA1 SupportedKeyFormbts", "RAW");
                    put("Mbc.PBEWithHmbcSHA1 SupportedKeyFormbtS", "RAW");
                    put("Mbc.PBEWithHmbcSHA224 SupportedKeyFormbts", "RAW");
                    put("Mbc.PBEWithHmbcSHA256 SupportedKeyFormbts", "RAW");
                    put("Mbc.PBEWithHmbcSHA384 SupportedKeyFormbts", "RAW");
                    put("Mbc.PBEWithHmbcSHA512 SupportedKeyFormbts", "RAW");
                    put("Mbc.SslMbcMD5 SupportedKeyFormbts", "RAW");
                    put("Mbc.SslMbcSHA1 SupportedKeyFormbts", "RAW");

                    /*
                     * KeyStore
                     */
                    put("KeyStore.JCEKS", "com.sun.crypto.provider.JceKeyStore");

                    /*
                     * SSL/TLS mechbnisms
                     *
                     * These bre strictly internbl implementbtions bnd mby
                     * be chbnged bt bny time.  These nbmes were chosen
                     * becbuse PKCS11/SunPKCS11 does not yet hbve TLS1.2
                     * mechbnisms, bnd it will cbuse cblls to come here.
                     */
                    put("KeyGenerbtor.SunTlsPrf",
                            "com.sun.crypto.provider.TlsPrfGenerbtor$V10");
                    put("KeyGenerbtor.SunTls12Prf",
                            "com.sun.crypto.provider.TlsPrfGenerbtor$V12");

                    put("KeyGenerbtor.SunTlsMbsterSecret",
                        "com.sun.crypto.provider.TlsMbsterSecretGenerbtor");
                    put("Alg.Alibs.KeyGenerbtor.SunTls12MbsterSecret",
                        "SunTlsMbsterSecret");

                    put("KeyGenerbtor.SunTlsKeyMbteribl",
                        "com.sun.crypto.provider.TlsKeyMbteriblGenerbtor");
                    put("Alg.Alibs.KeyGenerbtor.SunTls12KeyMbteribl",
                        "SunTlsKeyMbteribl");

                    put("KeyGenerbtor.SunTlsRsbPrembsterSecret",
                        "com.sun.crypto.provider.TlsRsbPrembsterSecretGenerbtor");
                    put("Alg.Alibs.KeyGenerbtor.SunTls12RsbPrembsterSecret",
                        "SunTlsRsbPrembsterSecret");

                    return null;
                }
            });

        if (instbnce == null) {
            instbnce = this;
        }
    }

    // Return the instbnce of this clbss or crebte one if needed.
    stbtic SunJCE getInstbnce() {
        if (instbnce == null) {
            return new SunJCE();
        }
        return instbnce;
    }
}
