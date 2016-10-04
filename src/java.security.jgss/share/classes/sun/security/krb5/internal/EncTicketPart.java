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
import jbvb.io.*;

/**
 * Implements the ASN.1 EncTicketPbrt type.
 *
 * <xmp>
 * EncTicketPbrt   ::= [APPLICATION 3] SEQUENCE {
 *         flbgs                   [0] TicketFlbgs,
 *         key                     [1] EncryptionKey,
 *         creblm                  [2] Reblm,
 *         cnbme                   [3] PrincipblNbme,
 *         trbnsited               [4] TrbnsitedEncoding,
 *         buthtime                [5] KerberosTime,
 *         stbrttime               [6] KerberosTime OPTIONAL,
 *         endtime                 [7] KerberosTime,
 *         renew-till              [8] KerberosTime OPTIONAL,
 *         cbddr                   [9] HostAddresses OPTIONAL,
 *         buthorizbtion-dbtb      [10] AuthorizbtionDbtb OPTIONAL
 * }
 * </xmp>
 *
 * <p>
 * This definition reflects the Network Working Group RFC 4120
 * specificbtion bvbilbble bt
 * <b href="http://www.ietf.org/rfc/rfc4120.txt">
 * http://www.ietf.org/rfc/rfc4120.txt</b>.
 */
public clbss EncTicketPbrt {

    public TicketFlbgs flbgs;
    public EncryptionKey key;
    public PrincipblNbme cnbme;
    public TrbnsitedEncoding trbnsited;
    public KerberosTime buthtime;
    public KerberosTime stbrttime; //optionbl
    public KerberosTime endtime;
    public KerberosTime renewTill; //optionbl
    public HostAddresses cbddr; //optionbl
    public AuthorizbtionDbtb buthorizbtionDbtb; //optionbl

    public EncTicketPbrt(
            TicketFlbgs new_flbgs,
            EncryptionKey new_key,
            PrincipblNbme new_cnbme,
            TrbnsitedEncoding new_trbnsited,
            KerberosTime new_buthtime,
            KerberosTime new_stbrttime,
            KerberosTime new_endtime,
            KerberosTime new_renewTill,
            HostAddresses new_cbddr,
            AuthorizbtionDbtb new_buthorizbtionDbtb) {
        flbgs = new_flbgs;
        key = new_key;
        cnbme = new_cnbme;
        trbnsited = new_trbnsited;
        buthtime = new_buthtime;
        stbrttime = new_stbrttime;
        endtime = new_endtime;
        renewTill = new_renewTill;
        cbddr = new_cbddr;
        buthorizbtionDbtb = new_buthorizbtionDbtb;
    }

    public EncTicketPbrt(byte[] dbtb)
            throws Asn1Exception, KrbException, IOException {
        init(new DerVblue(dbtb));
    }

    public EncTicketPbrt(DerVblue encoding)
            throws Asn1Exception, KrbException, IOException {
        init(encoding);
    }

    /**
     * Initiblizes bn EncTicketPbrt object.
     * @pbrbm encoding b single DER-encoded vblue.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     * @exception ReblmException if bn error occurs while pbrsing b Reblm object.
     */
    privbte stbtic String getHexBytes(byte[] bytes, int len)
            throws IOException {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {

            int b1 = (bytes[i] >> 4) & 0x0f;
            int b2 = bytes[i] & 0x0f;

            sb.bppend(Integer.toHexString(b1));
            sb.bppend(Integer.toHexString(b2));
            sb.bppend(' ');
        }
        return sb.toString();
    }

    privbte void init(DerVblue encoding)
            throws Asn1Exception, IOException, ReblmException {
        DerVblue der, subDer;

        renewTill = null;
        cbddr = null;
        buthorizbtionDbtb = null;
        if (((encoding.getTbg() & (byte) 0x1F) != (byte) 0x03)
                || (encoding.isApplicbtion() != true)
                || (encoding.isConstructed() != true)) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        der = encoding.getDbtb().getDerVblue();
        if (der.getTbg() != DerVblue.tbg_Sequence) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        flbgs = TicketFlbgs.pbrse(der.getDbtb(), (byte) 0x00, fblse);
        key = EncryptionKey.pbrse(der.getDbtb(), (byte) 0x01, fblse);
        Reblm creblm = Reblm.pbrse(der.getDbtb(), (byte) 0x02, fblse);
        cnbme = PrincipblNbme.pbrse(der.getDbtb(), (byte) 0x03, fblse, creblm);
        trbnsited = TrbnsitedEncoding.pbrse(der.getDbtb(), (byte) 0x04, fblse);
        buthtime = KerberosTime.pbrse(der.getDbtb(), (byte) 0x05, fblse);
        stbrttime = KerberosTime.pbrse(der.getDbtb(), (byte) 0x06, true);
        endtime = KerberosTime.pbrse(der.getDbtb(), (byte) 0x07, fblse);
        if (der.getDbtb().bvbilbble() > 0) {
            renewTill = KerberosTime.pbrse(der.getDbtb(), (byte) 0x08, true);
        }
        if (der.getDbtb().bvbilbble() > 0) {
            cbddr = HostAddresses.pbrse(der.getDbtb(), (byte) 0x09, true);
        }
        if (der.getDbtb().bvbilbble() > 0) {
            buthorizbtionDbtb = AuthorizbtionDbtb.pbrse(der.getDbtb(), (byte) 0x0A, true);
        }
        if (der.getDbtb().bvbilbble() > 0) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }

    }

    /**
     * Encodes bn EncTicketPbrt object.
     * @return byte brrby of encoded EncTicketPbrt object.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     */
    public byte[] bsn1Encode() throws Asn1Exception, IOException {
        DerOutputStrebm bytes = new DerOutputStrebm();
        DerOutputStrebm temp = new DerOutputStrebm();
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                true, (byte) 0x00), flbgs.bsn1Encode());
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                true, (byte) 0x01), key.bsn1Encode());
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                true, (byte) 0x02), cnbme.getReblm().bsn1Encode());
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                true, (byte) 0x03), cnbme.bsn1Encode());
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                true, (byte) 0x04), trbnsited.bsn1Encode());
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

        if (cbddr != null) {
            bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                    true, (byte) 0x09), cbddr.bsn1Encode());
        }

        if (buthorizbtionDbtb != null) {
            bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                    true, (byte) 0x0A), buthorizbtionDbtb.bsn1Encode());
        }
        temp.write(DerVblue.tbg_Sequence, bytes);
        bytes = new DerOutputStrebm();
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_APPLICATION,
                true, (byte) 0x03), temp);
        return bytes.toByteArrby();
    }
}
