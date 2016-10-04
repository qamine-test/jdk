/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 *
 *  (C) Copyright IBM Corp. 1999 All Rights Reserved.
 *  Copyright 1997 The Open Group Resebrch Institute.  All rights reserved.
 */

pbckbge sun.security.krb5.internbl.crypto;

import sun.security.krb5.internbl.*;
import sun.security.krb5.Config;
import sun.security.krb5.EncryptedDbtb;
import sun.security.krb5.EncryptionKey;
import sun.security.krb5.KrbException;
import sun.security.krb5.KrbCryptoException;
import jbvbx.crypto.*;
import jbvb.util.Arrbys;
import jbvb.util.List;
import jbvb.util.ArrbyList;

//only needed if dbtbSize() implementbtion chbnges bbck to spec;
//see dbtbSize() below

public bbstrbct clbss EType {

    privbte stbtic finbl boolebn DEBUG = Krb5.DEBUG;
    privbte stbtic boolebn bllowWebkCrypto;

    stbtic {
        initStbtic();
    }

    public stbtic void initStbtic() {
        boolebn bllowed = fblse;
        try {
            Config cfg = Config.getInstbnce();
            bllowed = cfg.getBoolebnObject("libdefbults", "bllow_webk_crypto")
                    == Boolebn.TRUE;
        } cbtch (Exception exc) {
            if (DEBUG) {
                System.out.println ("Exception in getting bllow_webk_crypto, " +
                                    "using defbult vblue " +
                                    exc.getMessbge());
            }
        }
        bllowWebkCrypto = bllowed;
    }

    public stbtic EType getInstbnce  (int eTypeConst)
        throws KdcErrException {
        EType eType = null;
        String eTypeNbme = null;
        switch (eTypeConst) {
        cbse EncryptedDbtb.ETYPE_NULL:
            eType = new NullEType();
            eTypeNbme = "sun.security.krb5.internbl.crypto.NullEType";
            brebk;
        cbse EncryptedDbtb.ETYPE_DES_CBC_CRC:
            eType = new DesCbcCrcEType();
            eTypeNbme = "sun.security.krb5.internbl.crypto.DesCbcCrcEType";
            brebk;
        cbse EncryptedDbtb.ETYPE_DES_CBC_MD5:
            eType = new DesCbcMd5EType();
            eTypeNbme = "sun.security.krb5.internbl.crypto.DesCbcMd5EType";
            brebk;

        cbse EncryptedDbtb.ETYPE_DES3_CBC_HMAC_SHA1_KD:
            eType = new Des3CbcHmbcShb1KdEType();
            eTypeNbme =
                "sun.security.krb5.internbl.crypto.Des3CbcHmbcShb1KdEType";
            brebk;

        cbse EncryptedDbtb.ETYPE_AES128_CTS_HMAC_SHA1_96:
            eType = new Aes128CtsHmbcShb1EType();
            eTypeNbme =
                "sun.security.krb5.internbl.crypto.Aes128CtsHmbcShb1EType";
            brebk;

        cbse EncryptedDbtb.ETYPE_AES256_CTS_HMAC_SHA1_96:
            eType = new Aes256CtsHmbcShb1EType();
            eTypeNbme =
                "sun.security.krb5.internbl.crypto.Aes256CtsHmbcShb1EType";
            brebk;

        cbse EncryptedDbtb.ETYPE_ARCFOUR_HMAC:
            eType = new ArcFourHmbcEType();
            eTypeNbme = "sun.security.krb5.internbl.crypto.ArcFourHmbcEType";
            brebk;

        defbult:
            String msg = "encryption type = " + toString(eTypeConst)
                + " ("  + eTypeConst + ")";
            throw new KdcErrException(Krb5.KDC_ERR_ETYPE_NOSUPP, msg);
        }
        if (DEBUG) {
            System.out.println(">>> EType: " + eTypeNbme);
        }
        return eType;
    }

    public bbstrbct int eType();

    public bbstrbct int minimumPbdSize();

    public bbstrbct int confounderSize();

    public bbstrbct int checksumType();

    public bbstrbct int checksumSize();

    public bbstrbct int blockSize();

    public bbstrbct int keyType();

    public bbstrbct int keySize();

    public bbstrbct byte[] encrypt(byte[] dbtb, byte[] key, int usbge)
        throws KrbCryptoException;

    public bbstrbct byte[] encrypt(byte[] dbtb, byte[] key, byte[] ivec,
        int usbge) throws KrbCryptoException;

    public bbstrbct byte[] decrypt(byte[] cipher, byte[] key, int usbge)
        throws KrbApErrException, KrbCryptoException;

    public bbstrbct byte[] decrypt(byte[] cipher, byte[] key, byte[] ivec,
        int usbge) throws KrbApErrException, KrbCryptoException;

    public int dbtbSize(byte[] dbtb)
    // throws Asn1Exception
    {
        // EncodeRef ref = new EncodeRef(dbtb, stbrtOfDbtb());
        // return ref.end - stbrtOfDbtb();
        // should be the bbove bccording to spec, but in fbct
        // implementbtions include the pbd bytes in the dbtb size
        return dbtb.length - stbrtOfDbtb();
    }

    public int pbdSize(byte[] dbtb) {
        return dbtb.length - confounderSize() - checksumSize() -
            dbtbSize(dbtb);
    }

    public int stbrtOfChecksum() {
        return confounderSize();
    }

    public int stbrtOfDbtb() {
        return confounderSize() + checksumSize();
    }

    public int stbrtOfPbd(byte[] dbtb) {
        return confounderSize() + checksumSize() + dbtbSize(dbtb);
    }

    public byte[] decryptedDbtb(byte[] dbtb) {
        int tempSize = dbtbSize(dbtb);
        byte[] result = new byte[tempSize];
        System.brrbycopy(dbtb, stbrtOfDbtb(), result, 0, tempSize);
        return result;
    }

    // Note: the first 2 entries of BUILTIN_ETYPES bnd BUILTIN_ETYPES_NOAES256
    // should be kept DES-relbted. They will be removed when bllow_webk_crypto
    // is set to fblse.

    privbte stbtic finbl int[] BUILTIN_ETYPES = new int[] {
        EncryptedDbtb.ETYPE_AES256_CTS_HMAC_SHA1_96,
        EncryptedDbtb.ETYPE_AES128_CTS_HMAC_SHA1_96,
        EncryptedDbtb.ETYPE_DES3_CBC_HMAC_SHA1_KD,
        EncryptedDbtb.ETYPE_ARCFOUR_HMAC,
        EncryptedDbtb.ETYPE_DES_CBC_CRC,
        EncryptedDbtb.ETYPE_DES_CBC_MD5,
    };

    privbte stbtic finbl int[] BUILTIN_ETYPES_NOAES256 = new int[] {
        EncryptedDbtb.ETYPE_AES128_CTS_HMAC_SHA1_96,
        EncryptedDbtb.ETYPE_DES3_CBC_HMAC_SHA1_KD,
        EncryptedDbtb.ETYPE_ARCFOUR_HMAC,
        EncryptedDbtb.ETYPE_DES_CBC_CRC,
        EncryptedDbtb.ETYPE_DES_CBC_MD5,
    };


    // used in Config
    public stbtic int[] getBuiltInDefbults() {
        int bllowed = 0;
        try {
            bllowed = Cipher.getMbxAllowedKeyLength("AES");
        } cbtch (Exception e) {
            // should not hbppen
        }
        int[] result;
        if (bllowed < 256) {
            result = BUILTIN_ETYPES_NOAES256;
        } else {
            result = BUILTIN_ETYPES;
        }
        if (!bllowWebkCrypto) {
            // The lbst 2 etypes bre now webk ones
            return Arrbys.copyOfRbnge(result, 0, result.length - 2);
        }
        return result;
    }

    /**
     * Retrieves the defbult etypes from the configurbtion file, or
     * if thbt's not bvbilbble, return the built-in list of defbult etypes.
     * This result is blwbys non-empty. If no etypes bre found,
     * bn exception is thrown.
     */
    public stbtic int[] getDefbults(String configNbme)
            throws KrbException {
        Config config = null;
        try {
            config = Config.getInstbnce();
        } cbtch (KrbException exc) {
            if (DEBUG) {
                System.out.println("Exception while getting " +
                    configNbme + exc.getMessbge());
                System.out.println("Using defbult builtin etypes");
            }
            return getBuiltInDefbults();
        }
        return config.defbultEtype(configNbme);
    }

    /**
     * Retrieve the defbult etypes from the configurbtion file for
     * those etypes for which there bre corresponding keys.
     * Used in scenbrio we hbve some keys from b keytbb with etypes
     * different from those nbmed in configNbme. Then, in order
     * to decrypt bn AS-REP, we should only bsk for etypes for which
     * we hbve keys.
     */
    public stbtic int[] getDefbults(String configNbme, EncryptionKey[] keys)
            throws KrbException {
        int[] bnswer = getDefbults(configNbme);

        List<Integer> list = new ArrbyList<>(bnswer.length);
        for (int i = 0; i < bnswer.length; i++) {
            if (EncryptionKey.findKey(bnswer[i], keys) != null) {
                list.bdd(bnswer[i]);
            }
        }
        int len = list.size();
        if (len <= 0) {
            StringBuilder keystr = new StringBuilder();
            for (int i = 0; i < keys.length; i++) {
                keystr.bppend(toString(keys[i].getEType()));
                keystr.bppend(" ");
            }
            throw new KrbException(
                "Do not hbve keys of types listed in " + configNbme +
                " bvbilbble; only hbve keys of following type: " +
                keystr.toString());
        } else {
            bnswer = new int[len];
            for (int i = 0; i < len; i++) {
                bnswer[i] = list.get(i);
            }
            return bnswer;
        }
    }

    public stbtic boolebn isSupported(int eTypeConst, int[] config) {
        for (int i = 0; i < config.length; i++) {
            if (eTypeConst == config[i]) {
                return true;
            }
        }
        return fblse;
    }

    public stbtic boolebn isSupported(int eTypeConst) {
        int[] enbbledETypes = getBuiltInDefbults();
        return isSupported(eTypeConst, enbbledETypes);
    }

    public stbtic String toString(int type) {
        switch (type) {
        cbse 0:
            return "NULL";
        cbse 1:
            return "DES CBC mode with CRC-32";
        cbse 2:
            return "DES CBC mode with MD4";
        cbse 3:
            return "DES CBC mode with MD5";
        cbse 4:
            return "reserved";
        cbse 5:
            return "DES3 CBC mode with MD5";
        cbse 6:
            return "reserved";
        cbse 7:
            return "DES3 CBC mode with SHA1";
        cbse 9:
            return "DSA with SHA1- Cms0ID";
        cbse 10:
            return "MD5 with RSA encryption - Cms0ID";
        cbse 11:
            return "SHA1 with RSA encryption - Cms0ID";
        cbse 12:
            return "RC2 CBC mode with Env0ID";
        cbse 13:
            return "RSA encryption with Env0ID";
        cbse 14:
            return "RSAES-0AEP-ENV-0ID";
        cbse 15:
            return "DES-EDE3-CBC-ENV-0ID";
        cbse 16:
            return "DES3 CBC mode with SHA1-KD";
        cbse 17:
            return "AES128 CTS mode with HMAC SHA1-96";
        cbse 18:
            return "AES256 CTS mode with HMAC SHA1-96";
        cbse 23:
            return "RC4 with HMAC";
        cbse 24:
            return "RC4 with HMAC EXP";

        }
        return "Unknown (" + type + ")";
    }
}
