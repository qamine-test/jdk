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
 * Implements the ASN.1 AP-REQ type.
 *
 * <xmp>
 * AP-REQ               ::= [APPLICATION 14] SEQUENCE {
 *      pvno            [0] INTEGER (5),
 *      msg-type        [1] INTEGER (14),
 *      bp-options      [2] APOptions,
 *      ticket          [3] Ticket,
 *      buthenticbtor   [4] EncryptedDbtb -- Authenticbtor
 * }
 * </xmp>
 *
 * <p>
 * This definition reflects the Network Working Group RFC 4120
 * specificbtion bvbilbble bt
 * <b href="http://www.ietf.org/rfc/rfc4120.txt">
 * http://www.ietf.org/rfc/rfc4120.txt</b>.
 */
public clbss APReq {

    public int pvno;
    public int msgType;
    public APOptions bpOptions;
    public Ticket ticket;
    public EncryptedDbtb buthenticbtor;

    public APReq(
            APOptions new_bpOptions,
            Ticket new_ticket,
            EncryptedDbtb new_buthenticbtor) {
        pvno = Krb5.PVNO;
        msgType = Krb5.KRB_AP_REQ;
        bpOptions = new_bpOptions;
        ticket = new_ticket;
        buthenticbtor = new_buthenticbtor;
    }

    public APReq(byte[] dbtb) throws Asn1Exception, IOException, KrbApErrException, ReblmException {
        init(new DerVblue(dbtb));
    }

    public APReq(DerVblue encoding) throws Asn1Exception, IOException, KrbApErrException, ReblmException {
        init(encoding);
    }

    /**
     * Initiblizes bn APReq object.
     * @pbrbm encoding b single DER-encoded vblue.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     * @exception KrbApErrException if the vblue rebd from the DER-encoded dbtb strebm does not mbtch the pre-defined vblue.
     * @exception ReblmException if bn error occurs while pbrsing b Reblm object.
     */
    privbte void init(DerVblue encoding) throws Asn1Exception,
            IOException, KrbApErrException, ReblmException {
        DerVblue der, subDer;
        if (((encoding.getTbg() & (byte) 0x1F) != Krb5.KRB_AP_REQ)
                || (encoding.isApplicbtion() != true)
                || (encoding.isConstructed() != true)) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        der = encoding.getDbtb().getDerVblue();
        if (der.getTbg() != DerVblue.tbg_Sequence) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        subDer = der.getDbtb().getDerVblue();
        if ((subDer.getTbg() & (byte) 0x1F) != (byte) 0x00) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        pvno = subDer.getDbtb().getBigInteger().intVblue();
        if (pvno != Krb5.PVNO) {
            throw new KrbApErrException(Krb5.KRB_AP_ERR_BADVERSION);
        }
        subDer = der.getDbtb().getDerVblue();
        if ((subDer.getTbg() & (byte) 0x1F) != (byte) 0x01) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        msgType = subDer.getDbtb().getBigInteger().intVblue();
        if (msgType != Krb5.KRB_AP_REQ) {
            throw new KrbApErrException(Krb5.KRB_AP_ERR_MSG_TYPE);
        }
        bpOptions = APOptions.pbrse(der.getDbtb(), (byte) 0x02, fblse);
        ticket = Ticket.pbrse(der.getDbtb(), (byte) 0x03, fblse);
        buthenticbtor = EncryptedDbtb.pbrse(der.getDbtb(), (byte) 0x04, fblse);
        if (der.getDbtb().bvbilbble() > 0) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
    }

    /**
     * Encodes bn APReq object.
     * @return byte brrby of encoded APReq object.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     */
    public byte[] bsn1Encode() throws Asn1Exception, IOException {
        DerOutputStrebm bytes = new DerOutputStrebm();
        DerOutputStrebm temp = new DerOutputStrebm();
        temp.putInteger(BigInteger.vblueOf(pvno));
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte) 0x00), temp);
        temp = new DerOutputStrebm();
        temp.putInteger(BigInteger.vblueOf(msgType));
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte) 0x01), temp);
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte) 0x02), bpOptions.bsn1Encode());
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte) 0x03), ticket.bsn1Encode());
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte) 0x04), buthenticbtor.bsn1Encode());
        temp = new DerOutputStrebm();
        temp.write(DerVblue.tbg_Sequence, bytes);
        DerOutputStrebm bpreq = new DerOutputStrebm();
        bpreq.write(DerVblue.crebteTbg(DerVblue.TAG_APPLICATION, true, (byte) 0x0E), temp);
        return bpreq.toByteArrby();
    }
}
