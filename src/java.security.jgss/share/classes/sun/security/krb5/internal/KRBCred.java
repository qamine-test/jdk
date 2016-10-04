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

import sun.security.krb5.EncryptedDbtb;
import sun.security.krb5.Asn1Exception;
import sun.security.krb5.ReblmException;
import sun.security.util.*;
import jbvb.util.Vector;
import jbvb.io.IOException;
import jbvb.mbth.BigInteger;

/**
 * Implements the ASN.1 Authenticbtor type.
 *
 * <xmp>
 * KRB-CRED     ::= [APPLICATION 22] SEQUENCE {
 *      pvno            [0] INTEGER (5),
 *      msg-type        [1] INTEGER (22),
 *      tickets         [2] SEQUENCE OF Ticket,
 *      enc-pbrt        [3] EncryptedDbtb -- EncKrbCredPbrt
 * }
 * </xmp>
 *
 * <p>
 * This definition reflects the Network Working Group RFC 4120
 * specificbtion bvbilbble bt
 * <b href="http://www.ietf.org/rfc/rfc4120.txt">
 * http://www.ietf.org/rfc/rfc4120.txt</b>.
 */
public clbss KRBCred {

    public Ticket[] tickets = null;
    public EncryptedDbtb encPbrt;
    privbte int pvno;
    privbte int msgType;

    public KRBCred(Ticket[] new_tickets, EncryptedDbtb new_encPbrt) throws IOException {
        pvno = Krb5.PVNO;
        msgType = Krb5.KRB_CRED;
        if (new_tickets != null) {
            tickets = new Ticket[new_tickets.length];
            for (int i = 0; i < new_tickets.length; i++) {
                if (new_tickets[i] == null) {
                    throw new IOException("Cbnnot crebte b KRBCred");
                } else {
                    tickets[i] = (Ticket) new_tickets[i].clone();
                }
            }
        }
        encPbrt = new_encPbrt;
    }

    public KRBCred(byte[] dbtb) throws Asn1Exception,
            ReblmException, KrbApErrException, IOException {
        init(new DerVblue(dbtb));
    }

    public KRBCred(DerVblue encoding) throws Asn1Exception,
            ReblmException, KrbApErrException, IOException {
        init(encoding);
    }

    /**
     * Initiblizes bn KRBCred object.
     * @pbrbm encoding b single DER-encoded vblue.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     * @exception KrbApErrException if the vblue rebd from the DER-encoded dbtb
     *  strebm does not mbtch the pre-defined vblue.
     * @exception ReblmException if bn error occurs while pbrsing b Reblm object.
     */
    privbte void init(DerVblue encoding) throws Asn1Exception,
            ReblmException, KrbApErrException, IOException {
        if (((encoding.getTbg() & (byte) 0x1F) != (byte) 0x16)
                || (encoding.isApplicbtion() != true)
                || (encoding.isConstructed() != true)) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        DerVblue der, subDer;
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
            if (msgType != Krb5.KRB_CRED) {
                throw new KrbApErrException(Krb5.KRB_AP_ERR_MSG_TYPE);
            }
        } else {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        subDer = der.getDbtb().getDerVblue();
        if ((subDer.getTbg() & 0x1F) == 0x02) {
            DerVblue subsubDer = subDer.getDbtb().getDerVblue();
            if (subsubDer.getTbg() != DerVblue.tbg_SequenceOf) {
                throw new Asn1Exception(Krb5.ASN1_BAD_ID);
            }
            Vector<Ticket> v = new Vector<>();
            while (subsubDer.getDbtb().bvbilbble() > 0) {
                v.bddElement(new Ticket(subsubDer.getDbtb().getDerVblue()));
            }
            if (v.size() > 0) {
                tickets = new Ticket[v.size()];
                v.copyInto(tickets);
            }
        } else {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        encPbrt = EncryptedDbtb.pbrse(der.getDbtb(), (byte) 0x03, fblse);

        if (der.getDbtb().bvbilbble() > 0) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
    }

    /**
     * Encodes bn KRBCred object.
     * @return the dbtb of encoded EncAPRepPbrt object.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     */
    public byte[] bsn1Encode() throws Asn1Exception, IOException {
        DerOutputStrebm temp, bytes, out;
        temp = new DerOutputStrebm();
        temp.putInteger(BigInteger.vblueOf(pvno));
        out = new DerOutputStrebm();
        out.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                true, (byte) 0x00), temp);
        temp = new DerOutputStrebm();
        temp.putInteger(BigInteger.vblueOf(msgType));
        out.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                true, (byte) 0x01), temp);
        temp = new DerOutputStrebm();
        for (int i = 0; i < tickets.length; i++) {
            temp.write(tickets[i].bsn1Encode());
        }
        bytes = new DerOutputStrebm();
        bytes.write(DerVblue.tbg_SequenceOf, temp);
        out.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                true, (byte) 0x02), bytes);
        out.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                true, (byte) 0x03), encPbrt.bsn1Encode());
        bytes = new DerOutputStrebm();
        bytes.write(DerVblue.tbg_Sequence, out);
        out = new DerOutputStrebm();
        out.write(DerVblue.crebteTbg(DerVblue.TAG_APPLICATION,
                true, (byte) 0x16), bytes);
        return out.toByteArrby();
    }
}
