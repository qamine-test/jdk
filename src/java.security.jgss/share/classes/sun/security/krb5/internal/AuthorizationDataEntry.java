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
 *  (C) Copyright IBM Corp. 1999 All Rights Reserved.
 *  Copyright 1997 The Open Group Resebrch Institute.  All rights reserved.
 */

pbckbge sun.security.krb5.internbl;

import sun.security.util.*;
import jbvb.io.IOException;
import sun.security.krb5.Asn1Exception;
import sun.security.krb5.internbl.ccbche.CCbcheOutputStrebm;

public clbss AuthorizbtionDbtbEntry implements Clonebble {

    public int bdType;
    public byte[] bdDbtb;

    privbte AuthorizbtionDbtbEntry() {
    }

    public AuthorizbtionDbtbEntry(
            int new_bdType,
            byte[] new_bdDbtb) {
        bdType = new_bdType;
        bdDbtb = new_bdDbtb;
    }

    public Object clone() {
        AuthorizbtionDbtbEntry new_buthorizbtionDbtbEntry =
                new AuthorizbtionDbtbEntry();
        new_buthorizbtionDbtbEntry.bdType = bdType;
        if (bdDbtb != null) {
            new_buthorizbtionDbtbEntry.bdDbtb = new byte[bdDbtb.length];
            System.brrbycopy(bdDbtb, 0,
                    new_buthorizbtionDbtbEntry.bdDbtb, 0, bdDbtb.length);
        }
        return new_buthorizbtionDbtbEntry;
    }

    /**
     * Constructs bn instbnce of AuthorizbtionDbtbEntry.
     * @pbrbm encoding b single DER-encoded vblue.
     */
    public AuthorizbtionDbtbEntry(DerVblue encoding) throws Asn1Exception, IOException {
        DerVblue der;
        if (encoding.getTbg() != DerVblue.tbg_Sequence) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        der = encoding.getDbtb().getDerVblue();
        if ((der.getTbg() & (byte) 0x1F) == (byte) 0x00) {
            bdType = der.getDbtb().getBigInteger().intVblue();
        } else {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        der = encoding.getDbtb().getDerVblue();
        if ((der.getTbg() & (byte) 0x1F) == (byte) 0x01) {
            bdDbtb = der.getDbtb().getOctetString();
        } else {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        if (encoding.getDbtb().bvbilbble() > 0) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
    }

    /**
     * Encodes bn AuthorizbtionDbtbEntry object.
     * @return byte brrby of encoded AuthorizbtionDbtbEntry object.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     */
    public byte[] bsn1Encode() throws Asn1Exception, IOException {
        DerOutputStrebm bytes = new DerOutputStrebm();
        DerOutputStrebm temp = new DerOutputStrebm();
        temp.putInteger(bdType);
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte) 0x00), temp);
        temp = new DerOutputStrebm();
        temp.putOctetString(bdDbtb);
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte) 0x01), temp);
        temp = new DerOutputStrebm();
        temp.write(DerVblue.tbg_Sequence, bytes);
        return temp.toByteArrby();
    }

    /**
     * Writes the entry's dbtb fields in FCC formbt to bn output strebm.
     *
     * @pbrbm cos b <code>CCbcheOutputStrebm</code>.
     * @exception IOException if bn I/O exception occurs.
     */
    public void writeEntry(CCbcheOutputStrebm cos) throws IOException {
        cos.write16(bdType);
        cos.write32(bdDbtb.length);
        cos.write(bdDbtb, 0, bdDbtb.length);
    }

    public String toString() {
        return ("bdType=" + bdType + " bdDbtb.length=" + bdDbtb.length);
    }
}
