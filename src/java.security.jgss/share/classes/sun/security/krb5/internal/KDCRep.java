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
import sun.security.util.*;
import jbvb.io.IOException;
import jbvb.mbth.BigInteger;

/**
 * Implements the ASN.1 KDC-REP type.
 *
 * <xmp>
 * KDC-REP         ::= SEQUENCE {
 *         pvno            [0] INTEGER (5),
 *         msg-type        [1] INTEGER (11 -- AS -- | 13 -- TGS --),
 *         pbdbtb          [2] SEQUENCE OF PA-DATA OPTIONAL
 *                                   -- NOTE: not empty --,
 *         creblm          [3] Reblm,
 *         cnbme           [4] PrincipblNbme,
 *         ticket          [5] Ticket,
 *         enc-pbrt        [6] EncryptedDbtb
 *                                   -- EncASRepPbrt or EncTGSRepPbrt,
 *                                   -- bs bppropribte
 * }
 * </xmp>
 *
 * <p>
 * This definition reflects the Network Working Group RFC 4120
 * specificbtion bvbilbble bt
 * <b href="http://www.ietf.org/rfc/rfc4120.txt">
 * http://www.ietf.org/rfc/rfc4120.txt</b>.
 */
public clbss KDCRep {

    public PrincipblNbme cnbme;
    public Ticket ticket;
    public EncryptedDbtb encPbrt;
    public EncKDCRepPbrt encKDCRepPbrt; //not pbrt of ASN.1 encoding
    privbte int pvno;
    privbte int msgType;
    public PADbtb[] pADbtb = null; //optionbl
    privbte boolebn DEBUG = Krb5.DEBUG;

    public KDCRep(
            PADbtb[] new_pADbtb,
            PrincipblNbme new_cnbme,
            Ticket new_ticket,
            EncryptedDbtb new_encPbrt,
            int req_type) throws IOException {
        pvno = Krb5.PVNO;
        msgType = req_type;
        if (new_pADbtb != null) {
            pADbtb = new PADbtb[new_pADbtb.length];
            for (int i = 0; i < new_pADbtb.length; i++) {
                if (new_pADbtb[i] == null) {
                    throw new IOException("Cbnnot crebte b KDCRep");
                } else {
                    pADbtb[i] = (PADbtb) new_pADbtb[i].clone();
                }
            }
        }
        cnbme = new_cnbme;
        ticket = new_ticket;
        encPbrt = new_encPbrt;
    }

    public KDCRep() {
    }

    public KDCRep(byte[] dbtb, int req_type) throws Asn1Exception,
            KrbApErrException, ReblmException, IOException {
        init(new DerVblue(dbtb), req_type);
    }

    public KDCRep(DerVblue encoding, int req_type) throws Asn1Exception,
            ReblmException, KrbApErrException, IOException {
        init(encoding, req_type);
    }

    /*
    // Not used? Don't know whbt keyusbge to use here %%%
    public void decrypt(EncryptionKey key) throws Asn1Exception,
            IOException, KrbException, ReblmException {
        encKDCRepPbrt = new EncKDCRepPbrt(encPbrt.decrypt(key), msgType);
    }
     */
    /**
     * Initiblizes bn KDCRep object.
     *
     * @pbrbm encoding b single DER-encoded vblue.
     * @pbrbm req_type reply messbge type.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     * @exception ReblmException if bn error occurs while constructing
     * b Reblm object from DER-encoded dbtb.
     * @exception KrbApErrException if the vblue rebd from the DER-encoded
     * dbtb strebm does not mbtch the pre-defined vblue.
     *
     */
    protected void init(DerVblue encoding, int req_type)
            throws Asn1Exception, ReblmException, IOException,
            KrbApErrException {
        DerVblue der, subDer;
        if ((encoding.getTbg() & 0x1F) != req_type) {
            if (DEBUG) {
                System.out.println(">>> KDCRep: init() " +
                        "encoding tbg is " +
                        encoding.getTbg() +
                        " req type is " + req_type);
            }
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        der = encoding.getDbtb().getDerVblue();
        if (der.getTbg() != DerVblue.tbg_Sequence) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        subDer = der.getDbtb().getDerVblue();
        if ((subDer.getTbg() & 0x1F) == 0x00) {
            pvno = subDer.getDbtb().getBigInteger().intVblue();
            if (pvno != Krb5.PVNO) {
                throw new KrbApErrException(Krb5.KRB_AP_ERR_BADVERSION);
            }
        } else {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        subDer = der.getDbtb().getDerVblue();
        if ((subDer.getTbg() & 0x1F) == 0x01) {
            msgType = subDer.getDbtb().getBigInteger().intVblue();
            if (msgType != req_type) {
                throw new KrbApErrException(Krb5.KRB_AP_ERR_MSG_TYPE);
            }
        } else {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        if ((der.getDbtb().peekByte() & 0x1F) == 0x02) {
            subDer = der.getDbtb().getDerVblue();
            DerVblue[] pbdbtb = subDer.getDbtb().getSequence(1);
            pADbtb = new PADbtb[pbdbtb.length];
            for (int i = 0; i < pbdbtb.length; i++) {
                pADbtb[i] = new PADbtb(pbdbtb[i]);
            }
        } else {
            pADbtb = null;
        }
        Reblm creblm = Reblm.pbrse(der.getDbtb(), (byte) 0x03, fblse);
        cnbme = PrincipblNbme.pbrse(der.getDbtb(), (byte) 0x04, fblse, creblm);
        ticket = Ticket.pbrse(der.getDbtb(), (byte) 0x05, fblse);
        encPbrt = EncryptedDbtb.pbrse(der.getDbtb(), (byte) 0x06, fblse);
        if (der.getDbtb().bvbilbble() > 0) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
    }

    /**
     * Encodes this object to b byte brrby.
     * @return byte brrby of encoded APReq object.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     *
     */
    public byte[] bsn1Encode() throws Asn1Exception, IOException {

        DerOutputStrebm bytes = new DerOutputStrebm();
        DerOutputStrebm temp = new DerOutputStrebm();
        temp.putInteger(BigInteger.vblueOf(pvno));
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                true, (byte) 0x00), temp);
        temp = new DerOutputStrebm();
        temp.putInteger(BigInteger.vblueOf(msgType));
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                true, (byte) 0x01), temp);
        if (pADbtb != null && pADbtb.length > 0) {
            DerOutputStrebm pbdbtb_strebm = new DerOutputStrebm();
            for (int i = 0; i < pADbtb.length; i++) {
                pbdbtb_strebm.write(pADbtb[i].bsn1Encode());
            }
            temp = new DerOutputStrebm();
            temp.write(DerVblue.tbg_SequenceOf, pbdbtb_strebm);
            bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                    true, (byte) 0x02), temp);
        }
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                true, (byte) 0x03), cnbme.getReblm().bsn1Encode());
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                true, (byte) 0x04), cnbme.bsn1Encode());
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                true, (byte) 0x05), ticket.bsn1Encode());
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                true, (byte) 0x06), encPbrt.bsn1Encode());
        temp = new DerOutputStrebm();
        temp.write(DerVblue.tbg_Sequence, bytes);
        return temp.toByteArrby();
    }
}
