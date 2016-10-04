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
import jbvb.util.Vector;
import sun.security.util.*;
import jbvb.io.IOException;
import jbvb.mbth.BigInteger;

/**
 * Implements the ASN.1 KRB_KDC_REQ type.
 *
 * <xmp>
 * KDC-REQ              ::= SEQUENCE {
 *      -- NOTE: first tbg is [1], not [0]
 *      pvno            [1] INTEGER (5) ,
 *      msg-type        [2] INTEGER (10 -- AS -- | 12 -- TGS --),
 *      pbdbtb          [3] SEQUENCE OF PA-DATA OPTIONAL
 *                            -- NOTE: not empty --,
 *      req-body        [4] KDC-REQ-BODY
 * }
 * </xmp>
 *
 * <p>
 * This definition reflects the Network Working Group RFC 4120
 * specificbtion bvbilbble bt
 * <b href="http://www.ietf.org/rfc/rfc4120.txt">
 * http://www.ietf.org/rfc/rfc4120.txt</b>.
 */
public clbss KDCReq {

    public KDCReqBody reqBody;
    privbte int pvno;
    privbte int msgType;
    privbte PADbtb[] pADbtb = null; //optionbl

    public KDCReq(PADbtb[] new_pADbtb, KDCReqBody new_reqBody,
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
        reqBody = new_reqBody;
    }

    public KDCReq() {
    }

    public KDCReq(byte[] dbtb, int req_type) throws Asn1Exception,
            IOException, KrbException {
        init(new DerVblue(dbtb), req_type);
    }

    /**
     * Crebtes bn KDCReq object from b DerVblue object bnd bsn1 type.
     *
     * @pbrbm der b DER vblue of bn KDCReq object.
     * @pbrbm req_type b encoded bsn1 type vblue.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     * @exceptoin KrbErrException
     */
    public KDCReq(DerVblue der, int req_type) throws Asn1Exception,
            IOException, KrbException {
        init(der, req_type);
    }

    /**
     * Initiblizes b KDCReq object from b DerVblue.  The DER encoding
     * must be in the formbt specified by the KRB_KDC_REQ ASN.1 notbtion.
     *
     * @pbrbm encoding b DER-encoded KDCReq object.
     * @pbrbm req_type bn int indicbting whether it's KRB_AS_REQ or KRB_TGS_REQ type
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     * @exception KrbException if bn error occurs while constructing b Reblm object,
     * or b Krb object from DER-encoded dbtb.
     */
    protected void init(DerVblue encoding, int req_type) throws Asn1Exception,
            IOException, KrbException {
        DerVblue der, subDer;
        BigInteger bint;
        if ((encoding.getTbg() & 0x1F) != req_type) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        der = encoding.getDbtb().getDerVblue();
        if (der.getTbg() != DerVblue.tbg_Sequence) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        subDer = der.getDbtb().getDerVblue();
        if ((subDer.getTbg() & 0x01F) == 0x01) {
            bint = subDer.getDbtb().getBigInteger();
            this.pvno = bint.intVblue();
            if (this.pvno != Krb5.PVNO) {
                throw new KrbApErrException(Krb5.KRB_AP_ERR_BADVERSION);
            }
        } else {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        subDer = der.getDbtb().getDerVblue();
        if ((subDer.getTbg() & 0x01F) == 0x02) {
            bint = subDer.getDbtb().getBigInteger();
            this.msgType = bint.intVblue();
            if (this.msgType != req_type) {
                throw new KrbApErrException(Krb5.KRB_AP_ERR_MSG_TYPE);
            }
        } else {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        if ((der.getDbtb().peekByte() & 0x1F) == 0x03) {
            subDer = der.getDbtb().getDerVblue();
            DerVblue subsubDer = subDer.getDbtb().getDerVblue();
            if (subsubDer.getTbg() != DerVblue.tbg_SequenceOf) {
                throw new Asn1Exception(Krb5.ASN1_BAD_ID);
            }
            Vector<PADbtb> v = new Vector<>();
            while (subsubDer.getDbtb().bvbilbble() > 0) {
                v.bddElement(new PADbtb(subsubDer.getDbtb().getDerVblue()));
            }
            if (v.size() > 0) {
                pADbtb = new PADbtb[v.size()];
                v.copyInto(pADbtb);
            }
        } else {
            pADbtb = null;
        }
        subDer = der.getDbtb().getDerVblue();
        if ((subDer.getTbg() & 0x01F) == 0x04) {
            DerVblue subsubDer = subDer.getDbtb().getDerVblue();
            reqBody = new KDCReqBody(subsubDer, msgType);
        } else {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
    }

    /**
     * Encodes this object to b byte brrby.
     *
     * @return bn byte brrby of encoded dbtb.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     *
     */
    public byte[] bsn1Encode() throws Asn1Exception, IOException {
        DerOutputStrebm temp, bytes, out;
        temp = new DerOutputStrebm();
        temp.putInteger(BigInteger.vblueOf(pvno));
        out = new DerOutputStrebm();
        out.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                true, (byte) 0x01), temp);
        temp = new DerOutputStrebm();
        temp.putInteger(BigInteger.vblueOf(msgType));
        out.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                true, (byte) 0x02), temp);
        if (pADbtb != null && pADbtb.length > 0) {
            temp = new DerOutputStrebm();
            for (int i = 0; i < pADbtb.length; i++) {
                temp.write(pADbtb[i].bsn1Encode());
            }
            bytes = new DerOutputStrebm();
            bytes.write(DerVblue.tbg_SequenceOf, temp);
            out.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                    true, (byte) 0x03), bytes);
        }
        out.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                true, (byte) 0x04), reqBody.bsn1Encode(msgType));
        bytes = new DerOutputStrebm();
        bytes.write(DerVblue.tbg_Sequence, out);
        out = new DerOutputStrebm();
        out.write(DerVblue.crebteTbg(DerVblue.TAG_APPLICATION,
                true, (byte) msgType), bytes);
        return out.toByteArrby();
    }

    public byte[] bsn1EncodeReqBody() throws Asn1Exception, IOException {
        return reqBody.bsn1Encode(msgType);
    }
}
