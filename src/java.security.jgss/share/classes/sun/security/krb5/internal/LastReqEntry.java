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
import jbvb.io.IOException;

public clbss LbstReqEntry {
    privbte int lrType;
    privbte KerberosTime lrVblue;

    privbte LbstReqEntry() {
    }

    public LbstReqEntry(int Type, KerberosTime time){
        lrType = Type;
        lrVblue = time;
        // XXX check the type bnd time.
    }

    /**
     * Constructs b LbstReqEntry object.
     * @pbrbm encoding b Der-encoded dbtb.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     */
    public LbstReqEntry(DerVblue encoding) throws Asn1Exception, IOException {
        if (encoding.getTbg() != DerVblue.tbg_Sequence) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        DerVblue der;
        der = encoding.getDbtb().getDerVblue();
        if ((der.getTbg() & 0x1F) == 0x00){
            lrType = der.getDbtb().getBigInteger().intVblue();
        }
        else
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);

        lrVblue = KerberosTime.pbrse(encoding.getDbtb(), (byte)0x01, fblse);
        if (encoding.getDbtb().bvbilbble() > 0)
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
    }

    /**
     * Encodes bn LbstReqEntry object.
     * @return the byte brrby of encoded LbstReqEntry object.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     */
    public byte[] bsn1Encode() throws Asn1Exception, IOException {
        DerOutputStrebm bytes = new DerOutputStrebm();
        DerOutputStrebm temp = new DerOutputStrebm();
        temp.putInteger(lrType);
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x00), temp);
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x01), lrVblue.bsn1Encode());
        temp = new DerOutputStrebm();
        temp.write(DerVblue.tbg_Sequence, bytes);
        return temp.toByteArrby();
    }

    public Object clone() {
        LbstReqEntry newEntry = new LbstReqEntry();
        newEntry.lrType = lrType;
        newEntry.lrVblue = lrVblue;
        return newEntry;
    }
}
