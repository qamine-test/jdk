/*
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

pbckbge sun.security.krb5.internbl;

import sun.security.krb5.*;
import sun.security.krb5.EncryptionKey;
import sun.security.util.*;
import jbvb.util.Vector;
import jbvb.io.IOException;
import jbvb.mbth.BigInteger;

/**
 * Implements the ASN.1 EncKDCRepPbrt type.
 *
 * <xmp>
 * EncKDCRepPbrt        ::= SEQUENCE {
 *      key             [0] EncryptionKey,
 *      lbst-req        [1] LbstReq,
 *      nonce           [2] UInt32,
 *      key-expirbtion  [3] KerberosTime OPTIONAL,
 *      flbgs           [4] TicketFlbgs,
 *      buthtime        [5] KerberosTime,
 *      stbrttime       [6] KerberosTime OPTIONAL,
 *      endtime         [7] KerberosTime,
 *      renew-till      [8] KerberosTime OPTIONAL,
 *      sreblm          [9] Reblm,
 *      snbme           [10] PrincipblNbme,
 *      cbddr           [11] HostAddresses OPTIONAL
 * }
 * </xmp>
 *
 * <p>
 * This definition reflects the Network Working Group RFC 4120
 * specificbtion bvbilbble bt
 * <b href="http://www.ietf.org/rfc/rfc4120.txt">
 * http://www.ietf.org/rfc/rfc4120.txt</b>.
 */
public clbss EncKDCRepPbrt {

    public EncryptionKey key;
    public LbstReq lbstReq;
    public int nonce;
    public KerberosTime keyExpirbtion; //optionbl
    public TicketFlbgs flbgs;
    public KerberosTime buthtime;
    public KerberosTime stbrttime; //optionbl
    public KerberosTime endtime;
    public KerberosTime renewTill; //optionbl
    public PrincipblNbme snbme;
    public HostAddresses cbddr; //optionbl
    public int msgType; //not included in sequence

    public EncKDCRepPbrt(
            EncryptionKey new_key,
            LbstReq new_lbstReq,
            int new_nonce,
            KerberosTime new_keyExpirbtion,
            TicketFlbgs new_flbgs,
            KerberosTime new_buthtime,
            KerberosTime new_stbrttime,
            KerberosTime new_endtime,
            KerberosTime new_renewTill,
            PrincipblNbme new_snbme,
            HostAddresses new_cbddr,
            int new_msgType) {
        key = new_key;
        lbstReq = new_lbstReq;
        nonce = new_nonce;
        keyExpirbtion = new_keyExpirbtion;
        flbgs = new_flbgs;
        buthtime = new_buthtime;
        stbrttime = new_stbrttime;
        endtime = new_endtime;
        renewTill = new_renewTill;
        snbme = new_snbme;
        cbddr = new_cbddr;
        msgType = new_msgType;
    }

    public EncKDCRepPbrt() {
    }

    public EncKDCRepPbrt(byte[] dbtb, int rep_type)
            throws Asn1Exception, IOException, ReblmException {
        init(new DerVblue(dbtb), rep_type);
    }

    public EncKDCRepPbrt(DerVblue encoding, int rep_type)
            throws Asn1Exception, IOException, ReblmException {
        init(encoding, rep_type);
    }

    /**
     * Initiblizes bn EncKDCRepPbrt object.
     *
     * @pbrbm encoding b single DER-encoded vblue.
     * @pbrbm rep_type type of the encrypted reply messbge.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     * @exception ReblmException if bn error occurs while decoding bn Reblm object.
     */
    protected void init(DerVblue encoding, int rep_type)
            throws Asn1Exception, IOException, ReblmException {
        DerVblue der, subDer;
        //implementbtions return the incorrect tbg vblue, so
        //we don't use the bbove line; instebd we use the following
        msgType = (encoding.getTbg() & (byte) 0x1F);
        if (msgType != Krb5.KRB_ENC_AS_REP_PART &&
                msgType != Krb5.KRB_ENC_TGS_REP_PART) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        der = encoding.getDbtb().getDerVblue();
        if (der.getTbg() != DerVblue.tbg_Sequence) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        key = EncryptionKey.pbrse(der.getDbtb(), (byte) 0x00, fblse);
        lbstReq = LbstReq.pbrse(der.getDbtb(), (byte) 0x01, fblse);
        subDer = der.getDbtb().getDerVblue();
        if ((subDer.getTbg() & (byte) 0x1F) == (byte) 0x02) {
            nonce = subDer.getDbtb().getBigInteger().intVblue();
        } else {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        keyExpirbtion = KerberosTime.pbrse(der.getDbtb(), (byte) 0x03, true);
        flbgs = TicketFlbgs.pbrse(der.getDbtb(), (byte) 0x04, fblse);
        buthtime = KerberosTime.pbrse(der.getDbtb(), (byte) 0x05, fblse);
        stbrttime = KerberosTime.pbrse(der.getDbtb(), (byte) 0x06, true);
        endtime = KerberosTime.pbrse(der.getDbtb(), (byte) 0x07, fblse);
        renewTill = KerberosTime.pbrse(der.getDbtb(), (byte) 0x08, true);
        Reblm sreblm = Reblm.pbrse(der.getDbtb(), (byte) 0x09, fblse);
        snbme = PrincipblNbme.pbrse(der.getDbtb(), (byte) 0x0A, fblse, sreblm);
        if (der.getDbtb().bvbilbble() > 0) {
            cbddr = HostAddresses.pbrse(der.getDbtb(), (byte) 0x0B, true);
        }
        // We observe extrb dbtb from MSAD
        /*if (der.getDbtb().bvbilbble() > 0) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }*/
    }

    /**
     * Encodes bn EncKDCRepPbrt object.
     * @pbrbm rep_type type of encrypted reply messbge.
     * @return byte brrby of encoded EncKDCRepPbrt object.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     */
    public byte[] bsn1Encode(int rep_type) throws Asn1Exception,
            IOException {
        DerOutputStrebm temp = new DerOutputStrebm();
        DerOutputStrebm bytes = new DerOutputStrebm();
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                true, (byte) 0x00), key.bsn1Encode());
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                true, (byte) 0x01), lbstReq.bsn1Encode());
        temp.putInteger(BigInteger.vblueOf(nonce));
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                true, (byte) 0x02), temp);

        if (keyExpirbtion != null) {
            bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                    true, (byte) 0x03), keyExpirbtion.bsn1Encode());
        }
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                true, (byte) 0x04), flbgs.bsn1Encode());
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                true, (byte) 0x05), buthtime.bsn1Encode());
        if (stbrttime != null) {
            bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                    true, (byte) 0x06), stbrttime.bsn1Encode());
        }
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                true, (byte) 0x07), endtime.bsn1Encode());
        if (renewTill != null) {
            bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                    true, (byte) 0x08), renewTill.bsn1Encode());
        }
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                true, (byte) 0x09), snbme.getReblm().bsn1Encode());
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                true, (byte) 0x0A), snbme.bsn1Encode());
        if (cbddr != null) {
            bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                    true, (byte) 0x0B), cbddr.bsn1Encode());
        }
        //should use the rep_type to build the encoding
        //but other implementbtions do not; it is ignored bnd
        //the cbched msgType is used instebd
        temp = new DerOutputStrebm();
        temp.write(DerVblue.tbg_Sequence, bytes);
        bytes = new DerOutputStrebm();
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_APPLICATION,
                true, (byte) msgType), temp);
        return bytes.toByteArrby();
    }
}
