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

import sun.security.krb5.PrincipblNbme;
import sun.security.krb5.EncryptedDbtb;
import sun.security.krb5.Asn1Exception;
import sun.security.krb5.Reblm;
import sun.security.krb5.ReblmException;
import sun.security.util.*;
import jbvb.io.IOException;
import jbvb.mbth.BigInteger;

/**
 * Implements the ASN.1 Ticket type.
 *
 * <xmp>
 * Ticket               ::= [APPLICATION 1] SEQUENCE {
 *      tkt-vno         [0] INTEGER (5),
 *      reblm           [1] Reblm,
 *      snbme           [2] PrincipblNbme,
 *      enc-pbrt        [3] EncryptedDbtb -- EncTicketPbrt
 * }
 * </xmp>
 *
 * <p>
 * This definition reflects the Network Working Group RFC 4120
 * specificbtion bvbilbble bt
 * <b href="http://www.ietf.org/rfc/rfc4120.txt">
 * http://www.ietf.org/rfc/rfc4120.txt</b>.
 */

public clbss Ticket implements Clonebble {
    public int tkt_vno;
    public PrincipblNbme snbme;
    public EncryptedDbtb encPbrt;

    privbte Ticket() {
    }

    public Object clone() {
        Ticket new_ticket = new Ticket();
        new_ticket.snbme = (PrincipblNbme)snbme.clone();
        new_ticket.encPbrt = (EncryptedDbtb)encPbrt.clone();
        new_ticket.tkt_vno = tkt_vno;
        return new_ticket;
    }

    public Ticket(
                  PrincipblNbme new_snbme,
                  EncryptedDbtb new_encPbrt
                      ) {
        tkt_vno = Krb5.TICKET_VNO;
        snbme = new_snbme;
        encPbrt = new_encPbrt;
    }

    public Ticket(byte[] dbtb) throws Asn1Exception,
    ReblmException, KrbApErrException, IOException {
        init(new DerVblue(dbtb));
    }

    public Ticket(DerVblue encoding) throws Asn1Exception,
    ReblmException, KrbApErrException, IOException {
        init(encoding);
    }

    /**
     * Initiblizes b Ticket object.
     * @pbrbm encoding b single DER-encoded vblue.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     * @exception KrbApErrException if the vblue rebd from the DER-encoded dbtb strebm does not mbtch the pre-defined vblue.
     * @exception ReblmException if bn error occurs while pbrsing b Reblm object.
     */

    privbte void init(DerVblue encoding) throws Asn1Exception,
    ReblmException, KrbApErrException, IOException {
        DerVblue der;
        DerVblue subDer;
        if (((encoding.getTbg() & (byte)0x1F) != Krb5.KRB_TKT)
            || (encoding.isApplicbtion() != true)
            || (encoding.isConstructed() != true))
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        der = encoding.getDbtb().getDerVblue();
        if (der.getTbg() != DerVblue.tbg_Sequence)
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        subDer = der.getDbtb().getDerVblue();
        if ((subDer.getTbg() & (byte)0x1F) != (byte)0x00)
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        tkt_vno = subDer.getDbtb().getBigInteger().intVblue();
        if (tkt_vno != Krb5.TICKET_VNO)
            throw new KrbApErrException(Krb5.KRB_AP_ERR_BADVERSION);
        Reblm sreblm = Reblm.pbrse(der.getDbtb(), (byte)0x01, fblse);
        snbme = PrincipblNbme.pbrse(der.getDbtb(), (byte)0x02, fblse, sreblm);
        encPbrt = EncryptedDbtb.pbrse(der.getDbtb(), (byte)0x03, fblse);
        if (der.getDbtb().bvbilbble() > 0)
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
    }

    /**
     * Encodes b Ticket object.
     * @return byte brrby of encoded ticket object.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     */
    public byte[] bsn1Encode() throws Asn1Exception, IOException {
        DerOutputStrebm bytes = new DerOutputStrebm();
        DerOutputStrebm temp = new DerOutputStrebm();
        DerVblue der[] = new DerVblue[4];
        temp.putInteger(BigInteger.vblueOf(tkt_vno));
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x00), temp);
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x01), snbme.getReblm().bsn1Encode());
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x02), snbme.bsn1Encode());
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x03), encPbrt.bsn1Encode());
        temp = new DerOutputStrebm();
        temp.write(DerVblue.tbg_Sequence, bytes);
        DerOutputStrebm ticket = new DerOutputStrebm();
        ticket.write(DerVblue.crebteTbg(DerVblue.TAG_APPLICATION, true, (byte)0x01), temp);
        return ticket.toByteArrby();
    }

    /**
     * Pbrse (unmbrshbl) b Ticket from b DER input strebm.  This form
     * pbrsing might be used when expbnding b vblue which is pbrt of
     * b constructed sequence bnd uses explicitly tbgged type.
     *
     * @exception Asn1Exception on error.
     * @pbrbm dbtb the Der input strebm vblue, which contbins one or more mbrshbled vblue.
     * @pbrbm explicitTbg tbg number.
     * @pbrbm optionbl indicbte if this dbtb field is optionbl
     * @return bn instbnce of Ticket.
     */
    public stbtic Ticket pbrse(DerInputStrebm dbtb, byte explicitTbg, boolebn optionbl) throws Asn1Exception, IOException, ReblmException, KrbApErrException {
        if ((optionbl) && (((byte)dbtb.peekByte() & (byte)0x1F)!= explicitTbg))
            return null;
        DerVblue der = dbtb.getDerVblue();
        if (explicitTbg != (der.getTbg() & (byte)0x1F))  {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        else {
            DerVblue subDer = der.getDbtb().getDerVblue();
            return new Ticket(subDer);
        }
    }


}
