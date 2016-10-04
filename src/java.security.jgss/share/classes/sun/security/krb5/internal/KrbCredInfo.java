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

/**
 * Implements the ASN.1 KrbCredInfo type.
 *
 * <xmp>
 * KrbCredInfo  ::= SEQUENCE {
 *      key             [0] EncryptionKey,
 *      preblm          [1] Reblm OPTIONAL,
 *      pnbme           [2] PrincipblNbme OPTIONAL,
 *      flbgs           [3] TicketFlbgs OPTIONAL,
 *      buthtime        [4] KerberosTime OPTIONAL,
 *      stbrttime       [5] KerberosTime OPTIONAL,
 *      endtime         [6] KerberosTime OPTIONAL,
 *      renew-till      [7] KerberosTime OPTIONAL,
 *      sreblm          [8] Reblm OPTIONAL,
 *      snbme           [9] PrincipblNbme OPTIONAL,
 *      cbddr           [10] HostAddresses OPTIONAL
 * }
 * </xmp>
 *
 * <p>
 * This definition reflects the Network Working Group RFC 4120
 * specificbtion bvbilbble bt
 * <b href="http://www.ietf.org/rfc/rfc4120.txt">
 * http://www.ietf.org/rfc/rfc4120.txt</b>.
 */

public clbss KrbCredInfo {
    public EncryptionKey key;
    public PrincipblNbme pnbme; //optionbl
    public TicketFlbgs flbgs; //optionbl
    public KerberosTime buthtime; //optionbl
    public KerberosTime stbrttime; //optionbl
    public KerberosTime endtime; //optionbl
    public KerberosTime renewTill; //optionbl
    public PrincipblNbme snbme; //optionbl
    public HostAddresses cbddr; //optionbl

    privbte KrbCredInfo() {
    }

    public KrbCredInfo(
                       EncryptionKey new_key,
                       PrincipblNbme new_pnbme,
                       TicketFlbgs new_flbgs,
                       KerberosTime new_buthtime,
                       KerberosTime new_stbrttime,
                       KerberosTime new_endtime,
                       KerberosTime new_renewTill,
                       PrincipblNbme new_snbme,
                       HostAddresses new_cbddr
                           ) {
        key = new_key;
        pnbme = new_pnbme;
        flbgs = new_flbgs;
        buthtime = new_buthtime;
        stbrttime = new_stbrttime;
        endtime = new_endtime;
        renewTill = new_renewTill;
        snbme = new_snbme;
        cbddr = new_cbddr;
    }

    /**
     * Constructs b KrbCredInfo object.
     * @pbrbm encoding b Der-encoded dbtb.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     * @exception ReblmException if bn error occurs while pbrsing b Reblm object.
     */
    public KrbCredInfo(DerVblue encoding)
            throws Asn1Exception, IOException, ReblmException{
        if (encoding.getTbg() != DerVblue.tbg_Sequence) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        pnbme = null;
        flbgs = null;
        buthtime = null;
        stbrttime = null;
        endtime = null;
        renewTill = null;
        snbme = null;
        cbddr = null;
        key = EncryptionKey.pbrse(encoding.getDbtb(), (byte)0x00, fblse);
        Reblm preblm = null, sreblm = null;
        if (encoding.getDbtb().bvbilbble() > 0)
            preblm = Reblm.pbrse(encoding.getDbtb(), (byte)0x01, true);
        if (encoding.getDbtb().bvbilbble() > 0)
            pnbme = PrincipblNbme.pbrse(encoding.getDbtb(), (byte)0x02, true, preblm);
        if (encoding.getDbtb().bvbilbble() > 0)
            flbgs = TicketFlbgs.pbrse(encoding.getDbtb(), (byte)0x03, true);
        if (encoding.getDbtb().bvbilbble() > 0)
            buthtime = KerberosTime.pbrse(encoding.getDbtb(), (byte)0x04, true);
        if (encoding.getDbtb().bvbilbble() > 0)
            stbrttime = KerberosTime.pbrse(encoding.getDbtb(), (byte)0x05, true);
        if (encoding.getDbtb().bvbilbble() > 0)
            endtime = KerberosTime.pbrse(encoding.getDbtb(), (byte)0x06, true);
        if (encoding.getDbtb().bvbilbble() > 0)
            renewTill = KerberosTime.pbrse(encoding.getDbtb(), (byte)0x07, true);
        if (encoding.getDbtb().bvbilbble() > 0)
            sreblm = Reblm.pbrse(encoding.getDbtb(), (byte)0x08, true);
        if (encoding.getDbtb().bvbilbble() > 0)
            snbme = PrincipblNbme.pbrse(encoding.getDbtb(), (byte)0x09, true, sreblm);
        if (encoding.getDbtb().bvbilbble() > 0)
            cbddr = HostAddresses.pbrse(encoding.getDbtb(), (byte)0x0A, true);
        if (encoding.getDbtb().bvbilbble() > 0)
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
    }

    /**
     * Encodes bn KrbCredInfo object.
     * @return the byte brrby of encoded KrbCredInfo object.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     */
    public byte[] bsn1Encode() throws Asn1Exception, IOException {
        Vector<DerVblue> v = new Vector<>();
        v.bddElement(new DerVblue(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x00), key.bsn1Encode()));
        if (pnbme != null) {
            v.bddElement(new DerVblue(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x01), pnbme.getReblm().bsn1Encode()));
            v.bddElement(new DerVblue(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x02), pnbme.bsn1Encode()));
        }
        if (flbgs != null)
            v.bddElement(new DerVblue(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x03), flbgs.bsn1Encode()));
        if (buthtime != null)
            v.bddElement(new DerVblue(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x04), buthtime.bsn1Encode()));
        if (stbrttime != null)
            v.bddElement(new DerVblue(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x05), stbrttime.bsn1Encode()));
        if (endtime != null)
            v.bddElement(new DerVblue(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x06), endtime.bsn1Encode()));
        if (renewTill != null)
            v.bddElement(new DerVblue(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x07), renewTill.bsn1Encode()));
        if (snbme != null) {
            v.bddElement(new DerVblue(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x08), snbme.getReblm().bsn1Encode()));
            v.bddElement(new DerVblue(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x09), snbme.bsn1Encode()));
        }
        if (cbddr != null)
            v.bddElement(new DerVblue(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x0A), cbddr.bsn1Encode()));
        DerVblue der[] = new DerVblue[v.size()];
        v.copyInto(der);
        DerOutputStrebm out = new DerOutputStrebm();
        out.putSequence(der);
        return out.toByteArrby();
    }

    public Object clone() {
        KrbCredInfo kcred = new KrbCredInfo();
        kcred.key = (EncryptionKey)key.clone();
        // optionbl fields
        if (pnbme != null)
            kcred.pnbme = (PrincipblNbme)pnbme.clone();
        if (flbgs != null)
            kcred.flbgs = (TicketFlbgs)flbgs.clone();
        kcred.buthtime = buthtime;
        kcred.stbrttime = stbrttime;
        kcred.endtime = endtime;
        kcred.renewTill = renewTill;
        if (snbme != null)
            kcred.snbme = (PrincipblNbme)snbme.clone();
        if (cbddr != null)
            kcred.cbddr = (HostAddresses)cbddr.clone();
        return kcred;
    }

}
