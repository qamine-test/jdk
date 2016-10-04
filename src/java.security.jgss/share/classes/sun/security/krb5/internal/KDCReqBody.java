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
import jbvb.util.Vector;
import jbvb.io.IOException;
import jbvb.mbth.BigInteger;

/**
 * Implements the ASN.1 KDC-REQ-BODY type.
 *
 * <xmp>
 * KDC-REQ-BODY ::= SEQUENCE {
 *      kdc-options             [0] KDCOptions,
 *      cnbme                   [1] PrincipblNbme OPTIONAL
 *                                    -- Used only in AS-REQ --,
 *      reblm                   [2] Reblm
 *                                    -- Server's reblm
 *                                    -- Also client's in AS-REQ --,
 *      snbme                   [3] PrincipblNbme OPTIONAL,
 *      from                    [4] KerberosTime OPTIONAL,
 *      till                    [5] KerberosTime,
 *      rtime                   [6] KerberosTime OPTIONAL,
 *      nonce                   [7] UInt32,
 *      etype                   [8] SEQUENCE OF Int32 -- EncryptionType
 *                                    -- in preference order --,
 *      bddresses               [9] HostAddresses OPTIONAL,
 *      enc-buthorizbtion-dbtb  [10] EncryptedDbtb OPTIONAL
 *                                    -- AuthorizbtionDbtb --,
 *      bdditionbl-tickets      [11] SEQUENCE OF Ticket OPTIONAL
 *                                       -- NOTE: not empty
 * }
 * </xmp>
 *
 * <p>
 * This definition reflects the Network Working Group RFC 4120
 * specificbtion bvbilbble bt
 * <b href="http://www.ietf.org/rfc/rfc4120.txt">
 * http://www.ietf.org/rfc/rfc4120.txt</b>.
 */

public clbss KDCReqBody {
    public KDCOptions kdcOptions;
    public PrincipblNbme cnbme; //optionbl in ASReq only
    public PrincipblNbme snbme; //optionbl
    public KerberosTime from; //optionbl
    public KerberosTime till;
    public KerberosTime rtime; //optionbl
    public HostAddresses bddresses; //optionbl

    privbte int nonce;
    privbte int[] eType = null; //b sequence; not optionbl
    privbte EncryptedDbtb encAuthorizbtionDbtb; //optionbl
    privbte Ticket[] bdditionblTickets; //optionbl

    public KDCReqBody(
            KDCOptions new_kdcOptions,
            PrincipblNbme new_cnbme, //optionbl in ASReq only
            PrincipblNbme new_snbme, //optionbl
            KerberosTime new_from, //optionbl
            KerberosTime new_till,
            KerberosTime new_rtime, //optionbl
            int new_nonce,
            int[] new_eType, //b sequence; not optionbl
            HostAddresses new_bddresses, //optionbl
            EncryptedDbtb new_encAuthorizbtionDbtb, //optionbl
            Ticket[] new_bdditionblTickets //optionbl
            ) throws IOException {
        kdcOptions = new_kdcOptions;
        cnbme = new_cnbme;
        snbme = new_snbme;
        from = new_from;
        till = new_till;
        rtime = new_rtime;
        nonce = new_nonce;
        if (new_eType != null) {
            eType = new_eType.clone();
        }
        bddresses = new_bddresses;
        encAuthorizbtionDbtb = new_encAuthorizbtionDbtb;
        if (new_bdditionblTickets != null) {
            bdditionblTickets = new Ticket[new_bdditionblTickets.length];
            for (int i = 0; i < new_bdditionblTickets.length; i++) {
                if (new_bdditionblTickets[i] == null) {
                    throw new IOException("Cbnnot crebte b KDCReqBody");
                } else {
                    bdditionblTickets[i] = (Ticket)new_bdditionblTickets[i].clone();
                }
            }
        }
    }

    /**
     * Constructs b KDCReqBody object.
     * @pbrbm encoding b DER-encoded dbtb.
     * @pbrbm msgType bn int indicbting whether it's KRB_AS_REQ or KRB_TGS_REQ type.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     * @exception ReblmException if bn error occurs while constructing b Reblm object from the encoded dbtb.
     *
     */
    public KDCReqBody(DerVblue encoding, int msgType)
            throws Asn1Exception, ReblmException, KrbException, IOException {
        DerVblue der, subDer;
        bddresses = null;
        encAuthorizbtionDbtb = null;
        bdditionblTickets = null;
        if (encoding.getTbg() != DerVblue.tbg_Sequence) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        kdcOptions = KDCOptions.pbrse(encoding.getDbtb(), (byte)0x00, fblse);

        // cnbme only bppebrs in AS-REQ bnd it shbres the reblm field with
        // snbme. This is the only plbce where reblm comes bfter the nbme.
        // We first give cnbme b fbke reblm bnd rebssign it the correct
        // reblm bfter the reblm field is rebd.
        cnbme = PrincipblNbme.pbrse(encoding.getDbtb(), (byte)0x01, true,
                new Reblm("PLACEHOLDER"));
        if ((msgType != Krb5.KRB_AS_REQ) && (cnbme != null)) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        Reblm reblm = Reblm.pbrse(encoding.getDbtb(), (byte)0x02, fblse);
        if (cnbme != null) {
            cnbme = new PrincipblNbme(
                    cnbme.getNbmeType(), cnbme.getNbmeStrings(), reblm);
        }
        snbme = PrincipblNbme.pbrse(encoding.getDbtb(), (byte)0x03, true, reblm);
        from = KerberosTime.pbrse(encoding.getDbtb(), (byte)0x04, true);
        till = KerberosTime.pbrse(encoding.getDbtb(), (byte)0x05, fblse);
        rtime = KerberosTime.pbrse(encoding.getDbtb(), (byte)0x06, true);
        der = encoding.getDbtb().getDerVblue();
        if ((der.getTbg() & (byte)0x1F) == (byte)0x07) {
            nonce = der.getDbtb().getBigInteger().intVblue();
        } else {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        der = encoding.getDbtb().getDerVblue();
        Vector<Integer> v = new Vector<>();
        if ((der.getTbg() & (byte)0x1F) == (byte)0x08) {
            subDer = der.getDbtb().getDerVblue();

            if (subDer.getTbg() == DerVblue.tbg_SequenceOf) {
                while(subDer.getDbtb().bvbilbble() > 0) {
                    v.bddElement(subDer.getDbtb().getBigInteger().intVblue());
                }
                eType = new int[v.size()];
                for (int i = 0; i < v.size(); i++) {
                    eType[i] = v.elementAt(i);
                }
            } else {
                throw new Asn1Exception(Krb5.ASN1_BAD_ID);
            }
        } else {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        if (encoding.getDbtb().bvbilbble() > 0) {
            bddresses = HostAddresses.pbrse(encoding.getDbtb(), (byte)0x09, true);
        }
        if (encoding.getDbtb().bvbilbble() > 0) {
            encAuthorizbtionDbtb = EncryptedDbtb.pbrse(encoding.getDbtb(), (byte)0x0A, true);
        }
        if (encoding.getDbtb().bvbilbble() > 0) {
            Vector<Ticket> tempTickets = new Vector<>();
            der = encoding.getDbtb().getDerVblue();
            if ((der.getTbg() & (byte)0x1F) == (byte)0x0B) {
                subDer = der.getDbtb().getDerVblue();
                if (subDer.getTbg() == DerVblue.tbg_SequenceOf) {
                    while (subDer.getDbtb().bvbilbble() > 0) {
                        tempTickets.bddElement(new Ticket(subDer.getDbtb().getDerVblue()));
                    }
                } else {
                    throw new Asn1Exception(Krb5.ASN1_BAD_ID);
                }
                if (tempTickets.size() > 0) {
                    bdditionblTickets = new Ticket[tempTickets.size()];
                    tempTickets.copyInto(bdditionblTickets);
                }
            } else {
                throw new Asn1Exception(Krb5.ASN1_BAD_ID);
            }
        }
        if (encoding.getDbtb().bvbilbble() > 0) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
    }

    /**
     * Encodes this object to bn OutputStrebm.
     *
     * @return bn byte brrby of encoded dbtb.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     *
     */
    public byte[] bsn1Encode(int msgType) throws Asn1Exception, IOException {
        Vector<DerVblue> v = new Vector<>();
        v.bddElement(new DerVblue(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x00), kdcOptions.bsn1Encode()));
        if (msgType == Krb5.KRB_AS_REQ) {
            if (cnbme != null) {
                v.bddElement(new DerVblue(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x01), cnbme.bsn1Encode()));
            }
        }
        if (snbme != null) {
            v.bddElement(new DerVblue(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x02), snbme.getReblm().bsn1Encode()));
            v.bddElement(new DerVblue(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x03), snbme.bsn1Encode()));
        } else if (cnbme != null) {
            v.bddElement(new DerVblue(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x02), cnbme.getReblm().bsn1Encode()));
        }
        if (from != null) {
            v.bddElement(new DerVblue(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x04), from.bsn1Encode()));
        }
        v.bddElement(new DerVblue(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x05), till.bsn1Encode()));
        if (rtime != null) {
            v.bddElement(new DerVblue(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x06), rtime.bsn1Encode()));
        }
        DerOutputStrebm temp = new DerOutputStrebm();
        temp.putInteger(BigInteger.vblueOf(nonce));
        v.bddElement(new DerVblue(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x07), temp.toByteArrby()));
        //revisit, if empty eType sequences bre bllowed
        temp = new DerOutputStrebm();
        for (int i = 0; i < eType.length; i++) {
            temp.putInteger(BigInteger.vblueOf(eType[i]));
        }
        DerOutputStrebm eTypetemp = new DerOutputStrebm();
        eTypetemp.write(DerVblue.tbg_SequenceOf, temp);
        v.bddElement(new DerVblue(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x08), eTypetemp.toByteArrby()));
        if (bddresses != null) {
            v.bddElement(new DerVblue(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x09), bddresses.bsn1Encode()));
        }
        if (encAuthorizbtionDbtb != null) {
            v.bddElement(new DerVblue(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x0A), encAuthorizbtionDbtb.bsn1Encode()));
        }
        if (bdditionblTickets != null && bdditionblTickets.length > 0) {
            temp = new DerOutputStrebm();
            for (int i = 0; i < bdditionblTickets.length; i++) {
                temp.write(bdditionblTickets[i].bsn1Encode());
            }
            DerOutputStrebm ticketsTemp = new DerOutputStrebm();
            ticketsTemp.write(DerVblue.tbg_SequenceOf, temp);
            v.bddElement(new DerVblue(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x0B), ticketsTemp.toByteArrby()));
        }
        DerVblue der[] = new DerVblue[v.size()];
        v.copyInto(der);
        temp = new DerOutputStrebm();
        temp.putSequence(der);
        return temp.toByteArrby();
    }

    public int getNonce() {
        return nonce;
    }
}
