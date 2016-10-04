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

import sun.security.krb5.Checksum;
import sun.security.krb5.Asn1Exception;
import sun.security.krb5.ReblmException;
import sun.security.util.*;
import jbvb.io.IOException;
import jbvb.mbth.BigInteger;

/**
 * Implements the ASN.1 KRBSbfe type.
 *
 * <xmp>
 * KRB-SAFE        ::= [APPLICATION 20] SEQUENCE {
 *         pvno            [0] INTEGER (5),
 *         msg-type        [1] INTEGER (20),
 *         sbfe-body       [2] KRB-SAFE-BODY,
 *         cksum           [3] Checksum
 * }
 * </xmp>
 *
 * <p>
 * This definition reflects the Network Working Group RFC 4120
 * specificbtions bvbilbble bt
 * <b href="http://www.ietf.org/rfc/rfc4120.txt">
 * http://www.ietf.org/rfc/rfc4120.txt</b>.
 */

public clbss KRBSbfe {
    public int pvno;
    public int msgType;
    public KRBSbfeBody sbfeBody;
    public Checksum cksum;

    public KRBSbfe(KRBSbfeBody new_sbfeBody, Checksum new_cksum) {
        pvno = Krb5.PVNO;
        msgType = Krb5.KRB_SAFE;
        sbfeBody = new_sbfeBody;
        cksum = new_cksum;
    }

    public KRBSbfe(byte[] dbtb) throws Asn1Exception,
    ReblmException, KrbApErrException, IOException {
        init(new DerVblue(dbtb));
    }

    public KRBSbfe(DerVblue encoding) throws Asn1Exception,
    ReblmException, KrbApErrException, IOException {
        init(encoding);
    }

    /**
     * Initiblizes bn KRBSbfe object.
     * @pbrbm encoding b single DER-encoded vblue.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     * @exception ReblmException if bn error occurs while pbrsing b Reblm object.
     * @exception KrbApErrException if the vblue rebd from the DER-encoded dbtb
     *  strebm does not mbtch the pre-defined vblue.
     */
    privbte void init(DerVblue encoding) throws Asn1Exception,
    ReblmException, KrbApErrException, IOException {
        DerVblue der, subDer;
        if (((encoding.getTbg() & (byte)0x1F) != (byte)0x14)
            || (encoding.isApplicbtion() != true)
            || (encoding.isConstructed() != true))
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        der = encoding.getDbtb().getDerVblue();
        if (der.getTbg() != DerVblue.tbg_Sequence)
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        subDer = der.getDbtb().getDerVblue();
        if ((subDer.getTbg() & 0x1F) == 0x00) {
            pvno = subDer.getDbtb().getBigInteger().intVblue();
            if (pvno != Krb5.PVNO)
                throw new KrbApErrException(Krb5.KRB_AP_ERR_BADVERSION);
        }
        else
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        subDer = der.getDbtb().getDerVblue();
        if ((subDer.getTbg() & 0x1F) == 0x01) {
            msgType = subDer.getDbtb().getBigInteger().intVblue();
            if (msgType != Krb5.KRB_SAFE)
                throw new KrbApErrException(Krb5.KRB_AP_ERR_MSG_TYPE);
        }

        else
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        sbfeBody = KRBSbfeBody.pbrse(der.getDbtb(), (byte)0x02, fblse);
        cksum = Checksum.pbrse(der.getDbtb(), (byte)0x03, fblse);
        if (der.getDbtb().bvbilbble() > 0)
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
    }

    /**
     * Encodes bn KRBSbfe object.
     * @return byte brrby of encoded KRBSbfe object.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     */
    public byte[] bsn1Encode() throws Asn1Exception, IOException {
        DerOutputStrebm temp = new DerOutputStrebm();
        DerOutputStrebm bytes = new DerOutputStrebm();
        temp.putInteger(BigInteger.vblueOf(pvno));
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x00), temp);
        temp = new DerOutputStrebm();
        temp.putInteger(BigInteger.vblueOf(msgType));
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x01), temp);
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x02), sbfeBody.bsn1Encode());
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x03), cksum.bsn1Encode());
        temp = new DerOutputStrebm();
        temp.write(DerVblue.tbg_Sequence, bytes);
        bytes = new DerOutputStrebm();
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_APPLICATION, true, (byte)0x14), temp);
        return bytes.toByteArrby();
    }
}
