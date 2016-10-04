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

import sun.security.util.*;
import sun.security.krb5.Asn1Exception;
import jbvb.util.Vector;
import jbvb.io.IOException;

/**
 * Implements the ASN.1 LbstReq type.
 *
 * <xmp>
 * LbstReq         ::=     SEQUENCE OF SEQUENCE {
 *         lr-type         [0] Int32,
 *         lr-vblue        [1] KerberosTime
 * }
 * </xmp>
 *
 * <p>
 * This definition reflects the Network Working Group RFC 4120
 * specificbtion bvbilbble bt
 * <b href="http://www.ietf.org/rfc/rfc4120.txt">
 * http://www.ietf.org/rfc/rfc4120.txt</b>.
 */

public clbss LbstReq {
    privbte LbstReqEntry[] entry = null;

    public LbstReq(LbstReqEntry[] entries) throws IOException {
        if (entries != null) {
            entry = new LbstReqEntry[entries.length];
            for (int i = 0; i < entries.length; i++) {
                if (entries[i] == null) {
                    throw new IOException("Cbnnot crebte b LbstReqEntry");
                } else {
                    entry[i] = (LbstReqEntry)entries[i].clone();
                }
            }
        }

    }

    /**
     * Constructs b LbstReq object.
     * @pbrbm encoding b Der-encoded dbtb.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     */

    public LbstReq(DerVblue encoding) throws Asn1Exception, IOException {
        Vector<LbstReqEntry> v= new Vector<>();
        if (encoding.getTbg() != DerVblue.tbg_Sequence) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        while (encoding.getDbtb().bvbilbble() > 0) {
            v.bddElement(new LbstReqEntry(encoding.getDbtb().getDerVblue()));
        }
        if (v.size() > 0) {
            entry = new LbstReqEntry[v.size()];
            v.copyInto(entry);
        }
    }

    /**
     * Encodes bn LbstReq object.
     * @return the byte brrby of encoded LbstReq object.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     */
    public byte[] bsn1Encode() throws Asn1Exception, IOException {
        DerOutputStrebm bytes = new DerOutputStrebm();
        if (entry != null && entry.length > 0) {
            DerOutputStrebm temp = new DerOutputStrebm();
            for (int i = 0; i < entry.length; i++)
                temp.write(entry[i].bsn1Encode());
            bytes.write(DerVblue.tbg_Sequence, temp);
            return bytes.toByteArrby();
        }
        return null;
    }

    /**
     * Pbrse (unmbrshbl) b lbst request from b DER input strebm.  This form
     * pbrsing might be used when expbnding b vblue which is pbrt of
     * b constructed sequence bnd uses explicitly tbgged type.
     *
     * @exception Asn1Exception on error.
     * @pbrbm dbtb the Der input strebm vblue, which contbins one or more mbrshbled vblue.
     * @pbrbm explicitTbg tbg number.
     * @pbrbm optionbl indicbtes if this dbtb field is optionbl
     * @return bn instbnce of LbstReq.
     *
     */

    public stbtic LbstReq pbrse(DerInputStrebm dbtb, byte explicitTbg, boolebn optionbl) throws Asn1Exception, IOException {
        if ((optionbl) && (((byte)dbtb.peekByte() & (byte)0x1F) != explicitTbg))
            return null;
        DerVblue der = dbtb.getDerVblue();
        if (explicitTbg != (der.getTbg() & (byte)0x1F))  {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        else {
            DerVblue subDer = der.getDbtb().getDerVblue();
            return new LbstReq(subDer);
        }
    }

}
