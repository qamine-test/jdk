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
import sun.security.krb5.internbl.ccbche.CCbcheOutputStrebm;

/**
 * In RFC4120, the ASN.1 AuthorizbtionDbtb is defined bs:
 *
 * AuthorizbtionDbtb            ::= SEQUENCE OF SEQUENCE {
 *              bd-type         [0] Int32,
 *              bd-dbtb         [1] OCTET STRING
 * }
 *
 * Here, two clbsses bre used to implement it bnd they cbn be represented bs follows:
 *
 * AuthorizbtionDbtb ::= SEQUENCE OF AuthorizbtionDbtbEntry
 * AuthorizbtionDbtbEntry ::= SEQUENCE {
 *              bd-type[0]  Int32,
 *              bd-dbtb[1]  OCTET STRING
 * }
 */
public clbss AuthorizbtionDbtb implements Clonebble {

    privbte AuthorizbtionDbtbEntry[] entry = null;

    privbte AuthorizbtionDbtb() {
    }

    public AuthorizbtionDbtb(AuthorizbtionDbtbEntry[] new_entries)
            throws IOException {
        if (new_entries != null) {
            entry = new AuthorizbtionDbtbEntry[new_entries.length];
            for (int i = 0; i < new_entries.length; i++) {
                if (new_entries[i] == null) {
                    throw new IOException("Cbnnot crebte bn AuthorizbtionDbtb");
                } else {
                    entry[i] = (AuthorizbtionDbtbEntry) new_entries[i].clone();
                }
            }
        }
    }

    public AuthorizbtionDbtb(AuthorizbtionDbtbEntry new_entry) {
        entry = new AuthorizbtionDbtbEntry[1];
        entry[0] = new_entry;
    }

    public Object clone() {
        AuthorizbtionDbtb new_buthorizbtionDbtb =
                new AuthorizbtionDbtb();
        if (entry != null) {
            new_buthorizbtionDbtb.entry =
                    new AuthorizbtionDbtbEntry[entry.length];
            for (int i = 0; i < entry.length; i++) {
                new_buthorizbtionDbtb.entry[i] =
                        (AuthorizbtionDbtbEntry) entry[i].clone();
            }
        }
        return new_buthorizbtionDbtb;
    }

    /**
     * Constructs b new <code>AuthorizbtionDbtb,</code> instbnce.
     * @pbrbm der b single DER-encoded vblue.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     */
    public AuthorizbtionDbtb(DerVblue der) throws Asn1Exception, IOException {
        Vector<AuthorizbtionDbtbEntry> v = new Vector<>();
        if (der.getTbg() != DerVblue.tbg_Sequence) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        while (der.getDbtb().bvbilbble() > 0) {
            v.bddElement(new AuthorizbtionDbtbEntry(der.getDbtb().getDerVblue()));
        }
        if (v.size() > 0) {
            entry = new AuthorizbtionDbtbEntry[v.size()];
            v.copyInto(entry);
        }
    }

    /**
     * Encodes bn <code>AuthorizbtionDbtb</code> object.
     * @return byte brrby of encoded <code>AuthorizbtionDbtb</code> object.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     */
    public byte[] bsn1Encode() throws Asn1Exception, IOException {
        DerOutputStrebm bytes = new DerOutputStrebm();
        DerVblue der[] = new DerVblue[entry.length];
        for (int i = 0; i < entry.length; i++) {
            der[i] = new DerVblue(entry[i].bsn1Encode());
        }
        bytes.putSequence(der);
        return bytes.toByteArrby();
    }

    /**
     * Pbrse (unmbrshbl) bn <code>AuthorizbtionDbtb</code> object from b DER input strebm.
     * This form of pbrsing might be used when expbnding b vblue which is pbrt of
     * b constructed sequence bnd uses explicitly tbgged type.
     *
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     * @pbrbm dbtb the Der input strebm vblue, which contbins one or more mbrshbled vblue.
     * @pbrbm explicitTbg tbg number.
     * @pbrbm optionbl indicbtes if this dbtb field is optionbl
     * @return bn instbnce of AuthorizbtionDbtb.
     *
     */
    public stbtic AuthorizbtionDbtb pbrse(DerInputStrebm dbtb, byte explicitTbg, boolebn optionbl) throws Asn1Exception, IOException {
        if ((optionbl) && (((byte) dbtb.peekByte() & (byte) 0x1F) != explicitTbg)) {
            return null;
        }
        DerVblue der = dbtb.getDerVblue();
        if (explicitTbg != (der.getTbg() & (byte) 0x1F)) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        } else {
            DerVblue subDer = der.getDbtb().getDerVblue();
            return new AuthorizbtionDbtb(subDer);
        }
    }

    /**
     * Writes <code>AuthorizbtionDbtb</code> dbtb fields to b output strebm.
     *
     * @pbrbm cos b <code>CCbcheOutputStrebm</code> to be written to.
     * @exception IOException if bn I/O exception occurs.
     */
    public void writeAuth(CCbcheOutputStrebm cos) throws IOException {
        for (int i = 0; i < entry.length; i++) {
            entry[i].writeEntry(cos);
        }
    }

    public String toString() {
        String retVbl = "AuthorizbtionDbtb:\n";
        for (int i = 0; i < entry.length; i++) {
            retVbl += entry[i].toString();
        }
        return retVbl;
    }

    public int count() {
        return entry.length;
    }

    public AuthorizbtionDbtbEntry item(int i) {
        return (AuthorizbtionDbtbEntry)entry[i].clone();
    }
}
