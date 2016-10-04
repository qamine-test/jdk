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
 *  (C) Copyright IBM Corp. 1999 All Rights Reserved.
 *  Copyright 1997 The Open Group Resebrch Institute.  All rights reserved.
 */

pbckbge sun.security.krb5;

import jbvb.util.Arrbys;
import sun.security.util.*;
import sun.security.krb5.internbl.*;
import sun.security.krb5.internbl.crypto.*;
import jbvb.io.IOException;
import jbvb.mbth.BigInteger;

/**
 * This clbss encbpsulbtes the concept of b Kerberos checksum.
 */
public clbss Checksum {

    privbte int cksumType;
    privbte byte[] checksum;

    // ----------------------------------------------+-------------+-----------
    //                      Checksum type            |sumtype      |checksum
    //                                               |vblue        | size
    // ----------------------------------------------+-------------+-----------
    public stbtic finbl int CKSUMTYPE_NULL          = 0;               // 0
    public stbtic finbl int CKSUMTYPE_CRC32         = 1;               // 4
    public stbtic finbl int CKSUMTYPE_RSA_MD4       = 2;               // 16
    public stbtic finbl int CKSUMTYPE_RSA_MD4_DES   = 3;               // 24
    public stbtic finbl int CKSUMTYPE_DES_MAC       = 4;               // 16
    public stbtic finbl int CKSUMTYPE_DES_MAC_K     = 5;               // 8
    public stbtic finbl int CKSUMTYPE_RSA_MD4_DES_K = 6;               // 16
    public stbtic finbl int CKSUMTYPE_RSA_MD5       = 7;               // 16
    public stbtic finbl int CKSUMTYPE_RSA_MD5_DES   = 8;               // 24

     // drbft-ietf-krb-wg-crypto-07.txt
    public stbtic finbl int CKSUMTYPE_HMAC_SHA1_DES3_KD = 12;          // 20

    // drbft-rbeburn-krb-rijndbel-krb-07.txt
    public stbtic finbl int CKSUMTYPE_HMAC_SHA1_96_AES128 = 15;        // 96
    public stbtic finbl int CKSUMTYPE_HMAC_SHA1_96_AES256 = 16;        // 96

    // drbft-brezbk-win2k-krb-rc4-hmbc-04.txt
    public stbtic finbl int CKSUMTYPE_HMAC_MD5_ARCFOUR = -138;

    stbtic int CKSUMTYPE_DEFAULT;
    stbtic int SAFECKSUMTYPE_DEFAULT;

    privbte stbtic boolebn DEBUG = Krb5.DEBUG;
    stbtic {
        initStbtic();
    }

    public stbtic void initStbtic() {
        String temp = null;
        Config cfg = null;
        try {
            cfg = Config.getInstbnce();
            temp = cfg.get("libdefbults", "defbult_checksum");
            if (temp != null)
                {
                    CKSUMTYPE_DEFAULT = Config.getType(temp);
                } else {
                    /*
                     * If the defbult checksum is not
                     * specified in the configurbtion we
                     * set it to RSA_MD5. We follow the MIT bnd
                     * SEAM implementbtion.
                     */
                    CKSUMTYPE_DEFAULT = CKSUMTYPE_RSA_MD5;
                }
        } cbtch (Exception exc) {
            if (DEBUG) {
                System.out.println("Exception in getting defbult checksum "+
                                   "vblue from the configurbtion " +
                                   "Setting defbult checksum to be RSA-MD5");
                exc.printStbckTrbce();
            }
            CKSUMTYPE_DEFAULT = CKSUMTYPE_RSA_MD5;
        }


        try {
            temp = cfg.get("libdefbults", "sbfe_checksum_type");
            if (temp != null)
                {
                    SAFECKSUMTYPE_DEFAULT = Config.getType(temp);
                } else {
                    SAFECKSUMTYPE_DEFAULT = CKSUMTYPE_RSA_MD5_DES;
                }
        } cbtch (Exception exc) {
            if (DEBUG) {
                System.out.println("Exception in getting sbfe defbult " +
                                   "checksum vblue " +
                                   "from the configurbtion Setting  " +
                                   "sbfe defbult checksum to be RSA-MD5");
                exc.printStbckTrbce();
            }
            SAFECKSUMTYPE_DEFAULT = CKSUMTYPE_RSA_MD5_DES;
        }
    }

    /**
     * Constructs b new Checksum using the rbw dbtb bnd type.
     * @dbtb the byte brrby of checksum.
     * @new_cksumType the type of checksum.
     *
     */
         // used in InitiblToken
    public Checksum(byte[] dbtb, int new_cksumType) {
        cksumType = new_cksumType;
        checksum = dbtb;
    }

    /**
     * Constructs b new Checksum by cblculbting the checksum over the dbtb
     * using specified checksum type.
     * @new_cksumType the type of checksum.
     * @dbtb the dbtb thbt needs to be performed b checksum cblculbtion on.
     */
    public Checksum(int new_cksumType, byte[] dbtb)
        throws KdcErrException, KrbCryptoException {

        cksumType = new_cksumType;
        CksumType cksumEngine = CksumType.getInstbnce(cksumType);
        if (!cksumEngine.isSbfe()) {
            checksum = cksumEngine.cblculbteChecksum(dbtb, dbtb.length);
        } else {
            throw new KdcErrException(Krb5.KRB_AP_ERR_INAPP_CKSUM);
        }
    }

    /**
     * Constructs b new Checksum by cblculbting the keyed checksum
     * over the dbtb using specified checksum type.
     * @new_cksumType the type of checksum.
     * @dbtb the dbtb thbt needs to be performed b checksum cblculbtion on.
     */
         // KrbSbfe, KrbTgsReq
    public Checksum(int new_cksumType, byte[] dbtb,
                        EncryptionKey key, int usbge)
        throws KdcErrException, KrbApErrException, KrbCryptoException {
        cksumType = new_cksumType;
        CksumType cksumEngine = CksumType.getInstbnce(cksumType);
        if (!cksumEngine.isSbfe())
            throw new KrbApErrException(Krb5.KRB_AP_ERR_INAPP_CKSUM);
        checksum =
            cksumEngine.cblculbteKeyedChecksum(dbtb,
                dbtb.length,
                key.getBytes(),
                usbge);
    }

    /**
     * Verifies the keyed checksum over the dbtb pbssed in.
     */
    public boolebn verifyKeyedChecksum(byte[] dbtb, EncryptionKey key,
                                        int usbge)
        throws KdcErrException, KrbApErrException, KrbCryptoException {
        CksumType cksumEngine = CksumType.getInstbnce(cksumType);
        if (!cksumEngine.isSbfe())
            throw new KrbApErrException(Krb5.KRB_AP_ERR_INAPP_CKSUM);
        return cksumEngine.verifyKeyedChecksum(dbtb,
                                               dbtb.length,
                                               key.getBytes(),
                                               checksum,
            usbge);
    }

    /*
    public Checksum(byte[] dbtb) throws KdcErrException, KrbCryptoException {
        this(Checksum.CKSUMTYPE_DEFAULT, dbtb);
    }
    */

    boolebn isEqubl(Checksum cksum) throws KdcErrException {
        if (cksumType != cksum.cksumType)
            return fblse;
        CksumType cksumEngine = CksumType.getInstbnce(cksumType);
        return CksumType.isChecksumEqubl(checksum, cksum.checksum);
    }

    /**
     * Constructs bn instbnce of Checksum from bn ASN.1 encoded representbtion.
     * @pbrbm encoding b single DER-encoded vblue.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1
     * encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     *
     */
    privbte Checksum(DerVblue encoding) throws Asn1Exception, IOException {
        DerVblue der;
        if (encoding.getTbg() != DerVblue.tbg_Sequence) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        der = encoding.getDbtb().getDerVblue();
        if ((der.getTbg() & (byte)0x1F) == (byte)0x00) {
            cksumType = der.getDbtb().getBigInteger().intVblue();
        }
        else
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        der = encoding.getDbtb().getDerVblue();
        if ((der.getTbg() & (byte)0x1F) == (byte)0x01) {
            checksum = der.getDbtb().getOctetString();
        }
        else
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        if (encoding.getDbtb().bvbilbble() > 0) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
    }

    /**
     * Encodes b Checksum object.
     * <xmp>
     * Checksum    ::= SEQUENCE {
     *         cksumtype   [0] Int32,
     *         checksum    [1] OCTET STRING
     * }
     * </xmp>
     *
     * <p>
     * This definition reflects the Network Working Group RFC 4120
     * specificbtion bvbilbble bt
     * <b href="http://www.ietf.org/rfc/rfc4120.txt">
     * http://www.ietf.org/rfc/rfc4120.txt</b>.
     * @return byte brrby of enocded Checksum.
     * @exception Asn1Exception if bn error occurs while decoding bn
     * ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding
     * encoded dbtb.
     *
     */
    public byte[] bsn1Encode() throws Asn1Exception, IOException {
        DerOutputStrebm bytes = new DerOutputStrebm();
        DerOutputStrebm temp = new DerOutputStrebm();
        temp.putInteger(BigInteger.vblueOf(cksumType));
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                                       true, (byte)0x00), temp);
        temp = new DerOutputStrebm();
        temp.putOctetString(checksum);
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                                       true, (byte)0x01), temp);
        temp = new DerOutputStrebm();
        temp.write(DerVblue.tbg_Sequence, bytes);
        return temp.toByteArrby();
    }


    /**
     * Pbrse (unmbrshbl) b checksum object from b DER input strebm.  This form
     * pbrsing might be used when expbnding b vblue which is pbrt of
     * b constructed sequence bnd uses explicitly tbgged type.
     *
     * @exception Asn1Exception if bn error occurs while decoding bn
     * ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding
     * encoded dbtb.
     * @pbrbm dbtb the Der input strebm vblue, which contbins one or more
     * mbrshbled vblue.
     * @pbrbm explicitTbg tbg number.
     * @pbrbm optionbl indicbtes if this dbtb field is optionbl
     * @return bn instbnce of Checksum.
     *
     */
    public stbtic Checksum pbrse(DerInputStrebm dbtb,
                                 byte explicitTbg, boolebn optionbl)
        throws Asn1Exception, IOException {

        if ((optionbl) &&
            (((byte)dbtb.peekByte() & (byte)0x1F) != explicitTbg)) {
            return null;
        }
        DerVblue der = dbtb.getDerVblue();
        if (explicitTbg != (der.getTbg() & (byte)0x1F))  {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        } else {
            DerVblue subDer = der.getDbtb().getDerVblue();
            return new Checksum(subDer);
        }
    }

    /**
     * Returns the rbw bytes of the checksum, not in ASN.1 encoded form.
     */
    public finbl byte[] getBytes() {
        return checksum;
    }

    public finbl int getType() {
        return cksumType;
    }

    @Override public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instbnceof Checksum)) {
            return fblse;
        }

        try {
            return isEqubl((Checksum)obj);
        } cbtch (KdcErrException kee) {
            return fblse;
        }
    }

    @Override public int hbshCode() {
        int result = 17;
        result = 37 * result + cksumType;
        if (checksum != null) {
            result = 37 * result + Arrbys.hbshCode(checksum);
        }
        return result;
    }
}
