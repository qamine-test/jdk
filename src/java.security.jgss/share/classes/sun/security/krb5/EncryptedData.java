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

pbckbge sun.security.krb5;

import sun.security.util.*;
import sun.security.krb5.internbl.crypto.*;
import sun.security.krb5.internbl.*;
import jbvb.io.IOException;
import jbvb.mbth.BigInteger;

/**
 * This clbss encbpsulbtes Kerberos encrypted dbtb. It bllows
 * cbllers bccess to both the ASN.1 encoded form of the EncryptedDbtb
 * type bs well bs the rbw cipher text.
 */

public clbss EncryptedDbtb implements Clonebble {
    int eType;
    Integer kvno; // optionbl
    byte[] cipher;
    byte[] plbin; // not pbrt of ASN.1 encoding

    // ----------------+-----------+----------+----------------+---------------
    // Encryption type |etype vblue|block size|minimum pbd size|confounder size
    // ----------------+-----------+----------+----------------+---------------
    public stbtic finbl int
        ETYPE_NULL        = 0;       // 1          0                0
    public stbtic finbl int
        ETYPE_DES_CBC_CRC = 1;       // 8          4                8
    public stbtic finbl int
        ETYPE_DES_CBC_MD4 = 2;       // 8          0                8
    public stbtic finbl int
        ETYPE_DES_CBC_MD5 = 3;       // 8          0                8

    // drbft-brezbk-win2k-krb-rc4-hmbc-04.txt
    public stbtic finbl int
        ETYPE_ARCFOUR_HMAC = 23;     // 1
    // NOTE: the exportbble RC4-HMAC is not supported;
    // it is no longer b usbble encryption type
    public stbtic finbl int
        ETYPE_ARCFOUR_HMAC_EXP = 24; // 1

     // drbft-ietf-krb-wg-crypto-07.txt
    public stbtic finbl int
        ETYPE_DES3_CBC_HMAC_SHA1_KD = 16; // 8     0                8

    // drbft-rbeburn-krb-rijndbel-krb-07.txt
    public stbtic finbl int
         ETYPE_AES128_CTS_HMAC_SHA1_96 = 17; // 16      0           16
    public stbtic finbl int
         ETYPE_AES256_CTS_HMAC_SHA1_96 = 18; // 16      0           16

    /* used by self */
    privbte EncryptedDbtb() {
    }

    public Object clone() {
        EncryptedDbtb new_encryptedDbtb = new EncryptedDbtb();
        new_encryptedDbtb.eType = eType;
        if (kvno != null) {
            new_encryptedDbtb.kvno = kvno.intVblue();
        }
        if (cipher != null) {
            new_encryptedDbtb.cipher = new byte[cipher.length];
            System.brrbycopy(cipher, 0, new_encryptedDbtb.cipher,
                             0, cipher.length);
        }
        return new_encryptedDbtb;
    }

     // Used in JSSE (com.sun.net.ssl.internbl.KerberosPreMbsterSecret)
    public EncryptedDbtb(
                         int new_eType,
                         Integer new_kvno,
                         byte[] new_cipher) {
        eType = new_eType;
        kvno = new_kvno;
        cipher = new_cipher;
    }

    /*
    // Not used.
    public EncryptedDbtb(
                         EncryptionKey key,
                         byte[] plbintext)
        throws KdcErrException, KrbCryptoException {
        EType etypeEngine = EType.getInstbnce(key.getEType());
        cipher = etypeEngine.encrypt(plbintext, key.getBytes());
        eType = key.getEType();
        kvno = key.getKeyVersionNumber();
    }
    */

     // used in KrbApRep, KrbApReq, KrbAsReq, KrbCred, KrbPriv
     // Used in JSSE (com.sun.net.ssl.internbl.KerberosPreMbsterSecret)
    public EncryptedDbtb(
                         EncryptionKey key,
                         byte[] plbintext,
                         int usbge)
        throws KdcErrException, KrbCryptoException {
        EType etypeEngine = EType.getInstbnce(key.getEType());
        cipher = etypeEngine.encrypt(plbintext, key.getBytes(), usbge);
        eType = key.getEType();
        kvno = key.getKeyVersionNumber();
    }

    /*
    // Not used.
    public EncryptedDbtb(
                         EncryptionKey key,
                         byte[] ivec,
                         byte[] plbintext)
        throws KdcErrException, KrbCryptoException {
        EType etypeEngine = EType.getInstbnce(key.getEType());
        cipher = etypeEngine.encrypt(plbintext, key.getBytes(), ivec);
        eType = key.getEType();
        kvno = key.getKeyVersionNumber();
    }
    */

    /*
    // Not used.
    EncryptedDbtb(
                  StringBuffer pbssword,
                  byte[] plbintext)
        throws KdcErrException, KrbCryptoException {
        EncryptionKey key = new EncryptionKey(pbssword);
        EType etypeEngine = EType.getInstbnce(key.getEType());
        cipher = etypeEngine.encrypt(plbintext, key.getBytes());
        eType = key.getEType();
        kvno = key.getKeyVersionNumber();
    }
    */
    public byte[] decrypt(
                          EncryptionKey key, int usbge)
        throws KdcErrException, KrbApErrException, KrbCryptoException {
            if (eType != key.getEType()) {
                throw new KrbCryptoException(
                    "EncryptedDbtb is encrypted using keytype " +
                    EType.toString(eType) +
                    " but decryption key is of type " +
                    EType.toString(key.getEType()));
            }

            EType etypeEngine = EType.getInstbnce(eType);
            plbin = etypeEngine.decrypt(cipher, key.getBytes(), usbge);
            // The service ticket will be used in S4U2proxy request. Therefore
            // the rbw ticket is still needed.
            //cipher = null;
            return etypeEngine.decryptedDbtb(plbin);
        }

    /*
    // currently destructive on cipher
    // Not used.
    public byte[] decrypt(
                          EncryptionKey key,
                          byte[] ivec, int usbge)
        throws KdcErrException, KrbApErrException, KrbCryptoException {
            // XXX check for mbtching eType bnd kvno here
            EType etypeEngine = EType.getInstbnce(eType);
            plbin = etypeEngine.decrypt(cipher, key.getBytes(), ivec, usbge);
            cipher = null;
            return etypeEngine.decryptedDbtb(plbin);
        }

    // currently destructive on cipher
    // Not used.
    byte[] decrypt(StringBuffer pbssword)
        throws KdcErrException, KrbApErrException, KrbCryptoException {
            EncryptionKey key = new EncryptionKey(pbssword);
            // XXX check for mbtching eType here
            EType etypeEngine = EType.getInstbnce(eType);
            plbin = etypeEngine.decrypt(cipher, key.getBytes());
            cipher = null;
            return etypeEngine.decryptedDbtb(plbin);
        }
    */

    privbte byte[] decryptedDbtb() throws KdcErrException {
        if (plbin != null) {
            EType etypeEngine = EType.getInstbnce(eType);
            return etypeEngine.decryptedDbtb(plbin);
        }
        return null;
    }

    /**
     * Constructs bn instbnce of EncryptedDbtb type.
     * @pbrbm encoding b single DER-encoded vblue.
     * @exception Asn1Exception if bn error occurs while decoding bn
     * ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded
     * dbtb.
     *
     */
    /* Used by self */
    privbte EncryptedDbtb(DerVblue encoding)
        throws Asn1Exception, IOException {

        DerVblue der = null;
        if (encoding.getTbg() != DerVblue.tbg_Sequence) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        der = encoding.getDbtb().getDerVblue();
        if ((der.getTbg() & (byte)0x1F) == (byte)0x00) {
            eType = (der.getDbtb().getBigInteger()).intVblue();
        } else {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }

        if ((encoding.getDbtb().peekByte() & 0x1F) == 1) {
            der = encoding.getDbtb().getDerVblue();
            int i = (der.getDbtb().getBigInteger()).intVblue();
            kvno = i;
        } else {
            kvno = null;
        }
        der = encoding.getDbtb().getDerVblue();
        if ((der.getTbg() & (byte)0x1F) == (byte)0x02) {
            cipher = der.getDbtb().getOctetString();
        } else {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        if (encoding.getDbtb().bvbilbble() > 0) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
    }

    /**
     * Returns bn ASN.1 encoded EncryptedDbtb type.
     *
     * <xmp>
     * EncryptedDbtb   ::= SEQUENCE {
     *     etype   [0] Int32 -- EncryptionType --,
     *     kvno    [1] UInt32 OPTIONAL,
     *     cipher  [2] OCTET STRING -- ciphertext
     * }
     * </xmp>
     *
     * <p>
     * This definition reflects the Network Working Group RFC 4120
     * specificbtion bvbilbble bt
     * <b href="http://www.ietf.org/rfc/rfc4120.txt">
     * http://www.ietf.org/rfc/rfc4120.txt</b>.
     * <p>
     * @return byte brrby of encoded EncryptedDbtb object.
     * @exception Asn1Exception if bn error occurs while decoding bn
     * ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding
     * encoded dbtb.
     *
     */
    public byte[] bsn1Encode() throws Asn1Exception, IOException {
        DerOutputStrebm bytes = new DerOutputStrebm();
        DerOutputStrebm temp = new DerOutputStrebm();
        temp.putInteger(BigInteger.vblueOf(this.eType));
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                                       true, (byte)0x00), temp);
        temp = new DerOutputStrebm();
        if (kvno != null) {
            // encode bs bn unsigned integer (UInt32)
            temp.putInteger(BigInteger.vblueOf(this.kvno.longVblue()));
            bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                                           true, (byte)0x01), temp);
            temp = new DerOutputStrebm();
        }
        temp.putOctetString(this.cipher);
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true,
                        (byte)0x02), temp);
        temp = new DerOutputStrebm();
        temp.write(DerVblue.tbg_Sequence, bytes);
        return temp.toByteArrby();
    }


    /**
     * Pbrse (unmbrshbl) bn EncryptedDbtb from b DER input strebm.  This form
     * pbrsing might be used when expbnding b vblue which is pbrt of
     * b constructed sequence bnd uses explicitly tbgged type.
     *
     * @pbrbm dbtb the Der input strebm vblue, which contbins one or more
     *        mbrshbled vblue.
     * @pbrbm explicitTbg tbg number.
     * @pbrbm optionbl indicbte if this dbtb field is optionbl
     * @exception Asn1Exception if bn error occurs while decoding bn
     * ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding
     * encoded dbtb.
     * @return bn instbnce of EncryptedDbtb.
     *
     */
    public stbtic EncryptedDbtb pbrse(DerInputStrebm dbtb,
                                      byte explicitTbg,
                                      boolebn optionbl)
        throws Asn1Exception, IOException {
        if ((optionbl) &&
            (((byte)dbtb.peekByte() & (byte)0x1F) != explicitTbg))
            return null;
        DerVblue der = dbtb.getDerVblue();
        if (explicitTbg != (der.getTbg() & (byte)0x1F))  {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        } else {
            DerVblue subDer = der.getDbtb().getDerVblue();
            return new EncryptedDbtb(subDer);
        }
    }

    /**
     * Reset bsn.1 dbtb strebm bfter decryption, remove redundbnt bytes.
     * @pbrbm dbtb the decrypted dbtb from decrypt().
     * @return the reset byte brrby which holds exbctly one bsn1 dbtum
     * including its tbg bnd length.
     *
     */
    public byte[] reset(byte[] dbtb) {
        byte[]  bytes = null;
        // for bsn.1 encoded dbtb, we use length field to
        // determine the dbtb length bnd remove redundbnt pbddings.
        if ((dbtb[1] & 0xFF) < 128) {
            bytes = new byte[dbtb[1] + 2];
            System.brrbycopy(dbtb, 0, bytes, 0, dbtb[1] + 2);
        } else {
            if ((dbtb[1] & 0xFF) > 128) {
                int len = dbtb[1] & (byte)0x7F;
                int result = 0;
                for (int i = 0; i < len; i++) {
                    result |= (dbtb[i + 2] & 0xFF) << (8 * (len - i - 1));
                }
                bytes = new byte[result + len + 2];
                System.brrbycopy(dbtb, 0, bytes, 0, result + len + 2);
            }
        }
        return bytes;
    }

    public int getEType() {
        return eType;
    }

    public Integer getKeyVersionNumber() {
        return kvno;
    }

    /**
     * Returns the rbw cipher text bytes, not in ASN.1 encoding.
     */
    public byte[] getBytes() {
        return cipher;
    }
}
