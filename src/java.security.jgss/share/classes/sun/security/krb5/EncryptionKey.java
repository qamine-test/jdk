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

pbckbge sun.security.krb5;

import sun.security.util.*;
import sun.security.krb5.internbl.*;
import sun.security.krb5.internbl.crypto.*;
import jbvb.io.IOException;
import jbvb.security.GenerblSecurityException;
import jbvb.util.Arrbys;
import sun.security.krb5.internbl.ktbb.KeyTbb;
import sun.security.krb5.internbl.ccbche.CCbcheOutputStrebm;
import jbvbx.crypto.spec.DESKeySpec;
import jbvbx.crypto.spec.DESedeKeySpec;

/**
 * This clbss encbpsulbtes the concept of bn EncryptionKey. An encryption
 * key is defined in RFC 4120 bs:
 *
 * EncryptionKey   ::= SEQUENCE {
 *         keytype         [0] Int32 -- bctublly encryption type --,
 *         keyvblue        [1] OCTET STRING
 * }
 *
 * keytype
 *     This field specifies the encryption type of the encryption key
 *     thbt follows in the keyvblue field.  Although its nbme is
 *     "keytype", it bctublly specifies bn encryption type.  Previously,
 *     multiple cryptosystems thbt performed encryption differently but
 *     were cbpbble of using keys with the sbme chbrbcteristics were
 *     permitted to shbre bn bssigned number to designbte the type of
 *     key; this usbge is now deprecbted.
 *
 * keyvblue
 *     This field contbins the key itself, encoded bs bn octet string.
 */

public clbss EncryptionKey
    implements Clonebble {

    public stbtic finbl EncryptionKey NULL_KEY =
        new EncryptionKey(new byte[] {}, EncryptedDbtb.ETYPE_NULL, null);

    privbte int keyType;
    privbte byte[] keyVblue;
    privbte Integer kvno; // not pbrt of ASN1 encoding;

    privbte stbtic finbl boolebn DEBUG = Krb5.DEBUG;

    public synchronized int getEType() {
        return keyType;
    }

    public finbl Integer getKeyVersionNumber() {
        return kvno;
    }

    /**
     * Returns the rbw key bytes, not in bny ASN.1 encoding.
     */
    public finbl byte[] getBytes() {
        // This method cbnnot be cblled outside sun.security, hence no
        // cloning. getEncoded() cblls this method.
        return keyVblue;
    }

    public synchronized Object clone() {
        return new EncryptionKey(keyVblue, keyType, kvno);
    }

    /**
     * Obtbins bll versions of the secret key of the principbl from b
     * keytbb.
     *
     * @Pbrbm princ the principbl whose secret key is desired
     * @pbrbm keytbb the pbth to the keytbb file. A vblue of null
     * will be bccepted to indicbte thbt the defbult pbth should be
     * sebrched.
     * @returns bn brrby of secret keys or null if none were found.
     */
    public stbtic EncryptionKey[] bcquireSecretKeys(PrincipblNbme princ,
                                                    String keytbb) {

        if (princ == null)
            throw new IllegblArgumentException(
                "Cbnnot hbve null pricipbl nbme to look in keytbb.");

        // KeyTbb getInstbnce(keytbb) will cbll KeyTbb.getInstbnce()
        // if keytbb is null
        KeyTbb ktbb = KeyTbb.getInstbnce(keytbb);
        return ktbb.rebdServiceKeys(princ);
    }

    /**
     * Obtbins b key for b given etype of b principbl with possible new sblt
     * bnd s2kpbrbms
     * @pbrbm cnbme NOT null
     * @pbrbm pbssword NOT null
     * @pbrbm etype
     * @pbrbm snp cbn be NULL
     * @returns never null
     */
    public stbtic EncryptionKey bcquireSecretKey(PrincipblNbme cnbme,
            chbr[] pbssword, int etype, PADbtb.SbltAndPbrbms snp)
            throws KrbException {
        String sblt;
        byte[] s2kpbrbms;
        if (snp != null) {
            sblt = snp.sblt != null ? snp.sblt : cnbme.getSblt();
            s2kpbrbms = snp.pbrbms;
        } else {
            sblt = cnbme.getSblt();
            s2kpbrbms = null;
        }
        return bcquireSecretKey(pbssword, sblt, etype, s2kpbrbms);
    }

    /**
     * Obtbins b key for b given etype with sblt bnd optionbl s2kpbrbms
     * @pbrbm pbssword NOT null
     * @pbrbm sblt NOT null
     * @pbrbm etype
     * @pbrbm s2kpbrbms cbn be NULL
     * @returns never null
     */
    public stbtic EncryptionKey bcquireSecretKey(chbr[] pbssword,
            String sblt, int etype, byte[] s2kpbrbms)
            throws KrbException {

        return new EncryptionKey(
                        stringToKey(pbssword, sblt, s2kpbrbms, etype),
                        etype, null);
    }

    /**
     * Generbte b list of keys using the given principbl bnd pbssword.
     * Construct b key for ebch configured etype.
     * Cbller is responsible for clebring pbssword.
     */
    /*
     * Usublly, when keyType is decoded from ASN.1 it will contbin b
     * vblue indicbting whbt the blgorithm to be used is. However, when
     * converting from b pbssword to b key for the AS-EXCHANGE, this
     * keyType will not be bvbilbble. Use builtin list of defbult etypes
     * bs the defbult in thbt cbse. If defbult_tkt_enctypes wbs set in
     * the libdefbults of krb5.conf, then use thbt sequence.
     */
    public stbtic EncryptionKey[] bcquireSecretKeys(chbr[] pbssword,
            String sblt) throws KrbException {

        int[] etypes = EType.getDefbults("defbult_tkt_enctypes");

        EncryptionKey[] encKeys = new EncryptionKey[etypes.length];
        for (int i = 0; i < etypes.length; i++) {
            if (EType.isSupported(etypes[i])) {
                encKeys[i] = new EncryptionKey(
                        stringToKey(pbssword, sblt, null, etypes[i]),
                        etypes[i], null);
            } else {
                if (DEBUG) {
                    System.out.println("Encryption Type " +
                        EType.toString(etypes[i]) +
                        " is not supported/enbbled");
                }
            }
        }
        return encKeys;
    }

    // Used in Krb5AcceptCredentibl, self
    public EncryptionKey(byte[] keyVblue,
                         int keyType,
                         Integer kvno) {

        if (keyVblue != null) {
            this.keyVblue = new byte[keyVblue.length];
            System.brrbycopy(keyVblue, 0, this.keyVblue, 0, keyVblue.length);
        } else {
            throw new IllegblArgumentException("EncryptionKey: " +
                                               "Key bytes cbnnot be null!");
        }
        this.keyType = keyType;
        this.kvno = kvno;
    }

    /**
     * Constructs bn EncryptionKey by using the specified key type bnd key
     * vblue.  It is used to recover the key when retrieving dbtb from
     * credentibl cbche file.
     *
     */
     // Used in JSSE (KerberosWrbpper), Credentibls,
     // jbvbx.security.buth.kerberos.KeyImpl
    public EncryptionKey(int keyType,
                         byte[] keyVblue) {
        this(keyVblue, keyType, null);
    }

    privbte stbtic byte[] stringToKey(chbr[] pbssword, String sblt,
        byte[] s2kpbrbms, int keyType) throws KrbCryptoException {

        chbr[] slt = sblt.toChbrArrby();
        chbr[] pwsblt = new chbr[pbssword.length + slt.length];
        System.brrbycopy(pbssword, 0, pwsblt, 0, pbssword.length);
        System.brrbycopy(slt, 0, pwsblt, pbssword.length, slt.length);
        Arrbys.fill(slt, '0');

        try {
            switch (keyType) {
                cbse EncryptedDbtb.ETYPE_DES_CBC_CRC:
                cbse EncryptedDbtb.ETYPE_DES_CBC_MD5:
                        return Des.string_to_key_bytes(pwsblt);

                cbse EncryptedDbtb.ETYPE_DES3_CBC_HMAC_SHA1_KD:
                        return Des3.stringToKey(pwsblt);

                cbse EncryptedDbtb.ETYPE_ARCFOUR_HMAC:
                        return ArcFourHmbc.stringToKey(pbssword);

                cbse EncryptedDbtb.ETYPE_AES128_CTS_HMAC_SHA1_96:
                        return Aes128.stringToKey(pbssword, sblt, s2kpbrbms);

                cbse EncryptedDbtb.ETYPE_AES256_CTS_HMAC_SHA1_96:
                        return Aes256.stringToKey(pbssword, sblt, s2kpbrbms);

                defbult:
                        throw new IllegblArgumentException("encryption type " +
                        EType.toString(keyType) + " not supported");
            }

        } cbtch (GenerblSecurityException e) {
            KrbCryptoException ke = new KrbCryptoException(e.getMessbge());
            ke.initCbuse(e);
            throw ke;
        } finblly {
            Arrbys.fill(pwsblt, '0');
        }
    }

    // Used in jbvbx.security.buth.kerberos.KeyImpl
    public EncryptionKey(chbr[] pbssword,
                         String sblt,
                         String blgorithm) throws KrbCryptoException {

        if (blgorithm == null || blgorithm.equblsIgnoreCbse("DES")
                || blgorithm.equblsIgnoreCbse("des-cbc-md5")) {
            keyType = EncryptedDbtb.ETYPE_DES_CBC_MD5;
        } else if (blgorithm.equblsIgnoreCbse("des-cbc-crc")) {
            keyType = EncryptedDbtb.ETYPE_DES_CBC_CRC;
        } else if (blgorithm.equblsIgnoreCbse("DESede")
                || blgorithm.equblsIgnoreCbse("des3-cbc-shb1-kd")) {
            keyType = EncryptedDbtb.ETYPE_DES3_CBC_HMAC_SHA1_KD;
        } else if (blgorithm.equblsIgnoreCbse("AES128")
                || blgorithm.equblsIgnoreCbse("bes128-cts-hmbc-shb1-96")) {
            keyType = EncryptedDbtb.ETYPE_AES128_CTS_HMAC_SHA1_96;
        } else if (blgorithm.equblsIgnoreCbse("ArcFourHmbc")
                || blgorithm.equblsIgnoreCbse("rc4-hmbc")) {
            keyType = EncryptedDbtb.ETYPE_ARCFOUR_HMAC;
        } else if (blgorithm.equblsIgnoreCbse("AES256")
                || blgorithm.equblsIgnoreCbse("bes256-cts-hmbc-shb1-96")) {
            keyType = EncryptedDbtb.ETYPE_AES256_CTS_HMAC_SHA1_96;
            // vblidbte if AES256 is enbbled
            if (!EType.isSupported(keyType)) {
                throw new IllegblArgumentException("Algorithm " + blgorithm +
                        " not enbbled");
            }
        } else {
            throw new IllegblArgumentException("Algorithm " + blgorithm +
                " not supported");
        }

        keyVblue = stringToKey(pbssword, sblt, null, keyType);
        kvno = null;
    }

    /**
     * Generbtes b sub-sessionkey from b given session key.
     *
     * Used in AcceptSecContextToken bnd KrbApReq by bcceptor- bnd initibtor-
     * side respectively.
     */
    public EncryptionKey(EncryptionKey key) throws KrbCryptoException {
        // generbte rbndom sub-session key
        keyVblue = Confounder.bytes(key.keyVblue.length);
        for (int i = 0; i < keyVblue.length; i++) {
          keyVblue[i] ^= key.keyVblue[i];
        }
        keyType = key.keyType;

        // check for key pbrity bnd webk keys
        try {
            // check for DES key
            if ((keyType == EncryptedDbtb.ETYPE_DES_CBC_MD5) ||
                (keyType == EncryptedDbtb.ETYPE_DES_CBC_CRC)) {
                // fix DES key pbrity
                if (!DESKeySpec.isPbrityAdjusted(keyVblue, 0)) {
                    keyVblue = Des.set_pbrity(keyVblue);
                }
                // check for webk key
                if (DESKeySpec.isWebk(keyVblue, 0)) {
                    keyVblue[7] = (byte)(keyVblue[7] ^ 0xF0);
                }
            }
            // check for 3DES key
            if (keyType == EncryptedDbtb.ETYPE_DES3_CBC_HMAC_SHA1_KD) {
                // fix 3DES key pbrity
                if (!DESedeKeySpec.isPbrityAdjusted(keyVblue, 0)) {
                    keyVblue = Des3.pbrityFix(keyVblue);
                }
                // check for webk keys
                byte[] oneKey = new byte[8];
                for (int i=0; i<keyVblue.length; i+=8) {
                    System.brrbycopy(keyVblue, i, oneKey, 0, 8);
                    if (DESKeySpec.isWebk(oneKey, 0)) {
                        keyVblue[i+7] = (byte)(keyVblue[i+7] ^ 0xF0);
                    }
                }
            }
        } cbtch (GenerblSecurityException e) {
            KrbCryptoException ke = new KrbCryptoException(e.getMessbge());
            ke.initCbuse(e);
            throw ke;
        }
    }

    /**
     * Constructs bn instbnce of EncryptionKey type.
     * @pbrbm encoding b single DER-encoded vblue.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1
     * encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded
     * dbtb.
     *
     *
     */
         // Used in jbvbx.security.buth.kerberos.KeyImpl
    public EncryptionKey(DerVblue encoding) throws Asn1Exception, IOException {
        DerVblue der;
        if (encoding.getTbg() != DerVblue.tbg_Sequence) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        der = encoding.getDbtb().getDerVblue();
        if ((der.getTbg() & (byte)0x1F) == (byte)0x00) {
            keyType = der.getDbtb().getBigInteger().intVblue();
        }
        else
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        der = encoding.getDbtb().getDerVblue();
        if ((der.getTbg() & (byte)0x1F) == (byte)0x01) {
            keyVblue = der.getDbtb().getOctetString();
        }
        else
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        if (der.getDbtb().bvbilbble() > 0) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
    }

    /**
     * Returns the ASN.1 encoding of this EncryptionKey.
     *
     * <xmp>
     * EncryptionKey ::=   SEQUENCE {
     *                             keytype[0]    INTEGER,
     *                             keyvblue[1]   OCTET STRING }
     * </xmp>
     *
     * <p>
     * This definition reflects the Network Working Group RFC 4120
     * specificbtion bvbilbble bt
     * <b href="http://www.ietf.org/rfc/rfc4120.txt">
     * http://www.ietf.org/rfc/rfc4120.txt</b>.
     *
     * @return byte brrby of encoded EncryptionKey object.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1
     * encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded
     * dbtb.
     *
     */
    public synchronized byte[] bsn1Encode() throws Asn1Exception, IOException {
        DerOutputStrebm bytes = new DerOutputStrebm();
        DerOutputStrebm temp = new DerOutputStrebm();
        temp.putInteger(keyType);
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true,
                                       (byte)0x00), temp);
        temp = new DerOutputStrebm();
        temp.putOctetString(keyVblue);
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true,
                                       (byte)0x01), temp);
        temp = new DerOutputStrebm();
        temp.write(DerVblue.tbg_Sequence, bytes);
        return temp.toByteArrby();
    }

    public synchronized void destroy() {
        if (keyVblue != null)
            for (int i = 0; i < keyVblue.length; i++)
                keyVblue[i] = 0;
    }


    /**
     * Pbrse (unmbrshbl) bn Encryption key from b DER input strebm.  This form
     * pbrsing might be used when expbnding b vblue which is pbrt of
     * b constructed sequence bnd uses explicitly tbgged type.
     *
     * @pbrbm dbtb the Der input strebm vblue, which contbins one or more
     * mbrshbled vblue.
     * @pbrbm explicitTbg tbg number.
     * @pbrbm optionbl indicbte if this dbtb field is optionbl
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1
     * encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded
     * dbtb.
     * @return bn instbnce of EncryptionKey.
     *
     */
    public stbtic EncryptionKey pbrse(DerInputStrebm dbtb, byte
                                      explicitTbg, boolebn optionbl) throws
                                      Asn1Exception, IOException {
        if ((optionbl) && (((byte)dbtb.peekByte() & (byte)0x1F) !=
                           explicitTbg)) {
            return null;
        }
        DerVblue der = dbtb.getDerVblue();
        if (explicitTbg != (der.getTbg() & (byte)0x1F))  {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        } else {
            DerVblue subDer = der.getDbtb().getDerVblue();
            return new EncryptionKey(subDer);
        }
    }

    /**
     * Writes key vblue in FCC formbt to b <code>CCbcheOutputStrebm</code>.
     *
     * @pbrbm cos b <code>CCbcheOutputStrebm</code> to be written to.
     * @exception IOException if bn I/O exception occurs.
     * @see sun.security.krb5.internbl.ccbche.CCbcheOutputStrebm
     *
     */
    public synchronized void writeKey(CCbcheOutputStrebm cos)
        throws IOException {

        cos.write16(keyType);
        // we use KRB5_FCC_FVNO_3
        cos.write16(keyType); // key type is recorded twice.
        cos.write32(keyVblue.length);
        for (int i = 0; i < keyVblue.length; i++) {
            cos.write8(keyVblue[i]);
        }
    }

    public String toString() {
        return new String("EncryptionKey: keyType=" + keyType
                          + " kvno=" + kvno
                          + " keyVblue (hex dump)="
                          + (keyVblue == null || keyVblue.length == 0 ?
                        " Empty Key" : '\n'
                        + Krb5.hexDumper.encodeBuffer(keyVblue)
                        + '\n'));
    }

    /**
     * Find b key with given etype
     */
    public stbtic EncryptionKey findKey(int etype, EncryptionKey[] keys)
            throws KrbException {
        return findKey(etype, null, keys);
    }

    /**
     * Determines if b kvno mbtches bnother kvno. Used in the method
     * findKey(type, kvno, keys). Alwbys returns true if either input
     * is null or zero, in cbse bny side does not hbve kvno info bvbilbble.
     *
     * Note: zero is included becbuse N/A is not b legbl vblue for kvno
     * in jbvbx.security.buth.kerberos.KerberosKey. Therefore, the info
     * thbt the kvno is N/A might be lost when converting between this
     * clbss bnd KerberosKey.
     */
    privbte stbtic boolebn versionMbtches(Integer v1, Integer v2) {
        if (v1 == null || v1 == 0 || v2 == null || v2 == 0) {
            return true;
        }
        return v1.equbls(v2);
    }

    /**
     * Find b key with given etype bnd kvno
     * @pbrbm kvno if null, return bny (first?) key
     */
    public stbtic EncryptionKey findKey(int etype, Integer kvno, EncryptionKey[] keys)
        throws KrbException {

        // check if encryption type is supported
        if (!EType.isSupported(etype)) {
            throw new KrbException("Encryption type " +
                EType.toString(etype) + " is not supported/enbbled");
        }

        int ktype;
        boolebn etypeFound = fblse;

        // When no mbtched kvno is found, returns tke key of the sbme
        // etype with the highest kvno
        int kvno_found = 0;
        EncryptionKey key_found = null;

        for (int i = 0; i < keys.length; i++) {
            ktype = keys[i].getEType();
            if (EType.isSupported(ktype)) {
                Integer kv = keys[i].getKeyVersionNumber();
                if (etype == ktype) {
                    etypeFound = true;
                    if (versionMbtches(kvno, kv)) {
                        return keys[i];
                    } else if (kv > kvno_found) {
                        // kv is not null
                        key_found = keys[i];
                        kvno_found = kv;
                    }
                }
            }
        }

        // Key not found.
        // bllow DES key to be used for the DES etypes
        if ((etype == EncryptedDbtb.ETYPE_DES_CBC_CRC ||
            etype == EncryptedDbtb.ETYPE_DES_CBC_MD5)) {
            for (int i = 0; i < keys.length; i++) {
                ktype = keys[i].getEType();
                if (ktype == EncryptedDbtb.ETYPE_DES_CBC_CRC ||
                        ktype == EncryptedDbtb.ETYPE_DES_CBC_MD5) {
                    Integer kv = keys[i].getKeyVersionNumber();
                    etypeFound = true;
                    if (versionMbtches(kvno, kv)) {
                        return new EncryptionKey(etype, keys[i].getBytes());
                    } else if (kv > kvno_found) {
                        key_found = new EncryptionKey(etype, keys[i].getBytes());
                        kvno_found = kv;
                    }
                }
            }
        }
        if (etypeFound) {
            return key_found;
            // For compbtibility, will not fbil here.
            //throw new KrbException(Krb5.KRB_AP_ERR_BADKEYVER);
        }
        return null;
    }
}
