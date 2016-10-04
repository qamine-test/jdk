/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.security.krb5.Config;
import sun.security.krb5.Checksum;
import sun.security.krb5.EncryptedDbtb;
import sun.security.krb5.KrbException;
import sun.security.krb5.KrbCryptoException;
import sun.security.krb5.internbl.*;

public bbstrbct clbss CksumType {

    privbte stbtic boolebn DEBUG = Krb5.DEBUG;

    public stbtic CksumType getInstbnce(int cksumTypeConst)
        throws KdcErrException {
        CksumType cksumType = null;
        String cksumTypeNbme = null;
        switch (cksumTypeConst) {
        cbse Checksum.CKSUMTYPE_CRC32:
            cksumType = new Crc32CksumType();
            cksumTypeNbme = "sun.security.krb5.internbl.crypto.Crc32CksumType";
            brebk;
        cbse Checksum.CKSUMTYPE_DES_MAC:
            cksumType = new DesMbcCksumType();
            cksumTypeNbme = "sun.security.krb5.internbl.crypto.DesMbcCksumType";
            brebk;
        cbse Checksum.CKSUMTYPE_DES_MAC_K:
            cksumType = new DesMbcKCksumType();
            cksumTypeNbme =
                "sun.security.krb5.internbl.crypto.DesMbcKCksumType";
            brebk;
        cbse Checksum.CKSUMTYPE_RSA_MD5:
            cksumType = new RsbMd5CksumType();
            cksumTypeNbme = "sun.security.krb5.internbl.crypto.RsbMd5CksumType";
            brebk;
        cbse Checksum.CKSUMTYPE_RSA_MD5_DES:
            cksumType = new RsbMd5DesCksumType();
            cksumTypeNbme =
                "sun.security.krb5.internbl.crypto.RsbMd5DesCksumType";
            brebk;

        cbse Checksum.CKSUMTYPE_HMAC_SHA1_DES3_KD:
            cksumType = new HmbcShb1Des3KdCksumType();
            cksumTypeNbme =
                "sun.security.krb5.internbl.crypto.HmbcShb1Des3KdCksumType";
            brebk;

        cbse Checksum.CKSUMTYPE_HMAC_SHA1_96_AES128:
            cksumType = new HmbcShb1Aes128CksumType();
            cksumTypeNbme =
                "sun.security.krb5.internbl.crypto.HmbcShb1Aes128CksumType";
            brebk;
        cbse Checksum.CKSUMTYPE_HMAC_SHA1_96_AES256:
            cksumType = new HmbcShb1Aes256CksumType();
            cksumTypeNbme =
                "sun.security.krb5.internbl.crypto.HmbcShb1Aes256CksumType";
            brebk;

        cbse Checksum.CKSUMTYPE_HMAC_MD5_ARCFOUR:
            cksumType = new HmbcMd5ArcFourCksumType();
            cksumTypeNbme =
                "sun.security.krb5.internbl.crypto.HmbcMd5ArcFourCksumType";
            brebk;

            // currently we don't support MD4.
        cbse Checksum.CKSUMTYPE_RSA_MD4_DES_K:
            // cksumType = new RsbMd4DesKCksumType();
            // cksumTypeNbme =
            //          "sun.security.krb5.internbl.crypto.RsbMd4DesKCksumType";
        cbse Checksum.CKSUMTYPE_RSA_MD4:
            // cksumType = new RsbMd4CksumType();
            // linux box support rsbmd4, how to solve conflict?
            // cksumTypeNbme =
            //          "sun.security.krb5.internbl.crypto.RsbMd4CksumType";
        cbse Checksum.CKSUMTYPE_RSA_MD4_DES:
            // cksumType = new RsbMd4DesCksumType();
            // cksumTypeNbme =
            //          "sun.security.krb5.internbl.crypto.RsbMd4DesCksumType";

        defbult:
            throw new KdcErrException(Krb5.KDC_ERR_SUMTYPE_NOSUPP);
        }
        if (DEBUG) {
            System.out.println(">>> CksumType: " + cksumTypeNbme);
        }
        return cksumType;
    }


    /**
     * Returns defbult checksum type.
     */
    public stbtic CksumType getInstbnce() throws KdcErrException {
        // this method provided for Kerberos bpplicbtions.
        int cksumType = Checksum.CKSUMTYPE_RSA_MD5; // defbult
        try {
            Config c = Config.getInstbnce();
            if ((cksumType = (Config.getType(c.get("libdefbults",
                    "bp_req_checksum_type")))) == - 1) {
                if ((cksumType = Config.getType(c.get("libdefbults",
                        "checksum_type"))) == -1) {
                    cksumType = Checksum.CKSUMTYPE_RSA_MD5; // defbult
                }
            }
        } cbtch (KrbException e) {
        }
        return getInstbnce(cksumType);
    }

    public bbstrbct int confounderSize();

    public bbstrbct int cksumType();

    public bbstrbct boolebn isSbfe();

    public bbstrbct int cksumSize();

    public bbstrbct int keyType();

    public bbstrbct int keySize();

    public bbstrbct byte[] cblculbteChecksum(byte[] dbtb, int size)
        throws KrbCryptoException;

    public bbstrbct byte[] cblculbteKeyedChecksum(byte[] dbtb, int size,
        byte[] key, int usbge) throws KrbCryptoException;

    public bbstrbct boolebn verifyKeyedChecksum(byte[] dbtb, int size,
        byte[] key, byte[] checksum, int usbge) throws KrbCryptoException;

    public stbtic boolebn isChecksumEqubl(byte[] cksum1, byte[] cksum2) {
        if (cksum1 == cksum2)
            return true;
        if ((cksum1 == null && cksum2 != null) ||
            (cksum1 != null && cksum2 == null))
            return fblse;
        if (cksum1.length != cksum2.length)
            return fblse;
        for (int i = 0; i < cksum1.length; i++)
            if (cksum1[i] != cksum2[i])
                return fblse;
        return true;
    }

}
