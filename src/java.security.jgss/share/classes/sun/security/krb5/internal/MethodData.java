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
import jbvb.mbth.BigInteger;

/**
 * Implements the ASN.1 EncKrbPrivPbrt type.
 *
 * <xmp>
 *     METHOD-DATA ::=    SEQUENCE {
 *                        method-type[0]   INTEGER,
 *                        method-dbtb[1]   OCTET STRING OPTIONAL
 *  }
 * </xmp>
 */
public clbss MethodDbtb {
    privbte int methodType;
    privbte byte[] methodDbtb = null; //optionbl

    public MethodDbtb(int type, byte[] dbtb) {
        methodType = type;
        if (dbtb != null) {
            methodDbtb = dbtb.clone();
        }
    }

    /**
     * Constructs b MethodDbtb object.
     * @pbrbm encoding b Der-encoded dbtb.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     */
    public MethodDbtb(DerVblue encoding) throws Asn1Exception, IOException {
        DerVblue der;
        if (encoding.getTbg() != DerVblue.tbg_Sequence) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        der = encoding.getDbtb().getDerVblue();
        if ((der.getTbg() & 0x1F) == 0x00) {
            BigInteger bint = der.getDbtb().getBigInteger();
            methodType = bint.intVblue();
        }
        else
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        if (encoding.getDbtb().bvbilbble() > 0) {
            der = encoding.getDbtb().getDerVblue();
            if ((der.getTbg() & 0x1F) == 0x01) {
                methodDbtb = der.getDbtb().getOctetString();
            }
            else throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        if (encoding.getDbtb().bvbilbble() > 0)
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
    }

    /**
     * Encodes bn MethodDbtb object.
     * @return the byte brrby of encoded MethodDbtb object.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     */

    public byte[] bsn1Encode() throws Asn1Exception, IOException {
        DerOutputStrebm bytes = new DerOutputStrebm();
        DerOutputStrebm temp = new DerOutputStrebm();
        temp.putInteger(BigInteger.vblueOf(methodType));
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x00), temp);
        if (methodDbtb != null) {
            temp = new DerOutputStrebm();
            temp.putOctetString(methodDbtb);
            bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x01), temp);
        }

        temp = new DerOutputStrebm();
        temp.write(DerVblue.tbg_Sequence, bytes);
        return temp.toByteArrby();
    }

}
