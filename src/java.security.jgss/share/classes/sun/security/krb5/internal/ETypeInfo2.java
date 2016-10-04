/*
 * Copyright (c) 2005, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.krb5.internbl;

import sun.security.util.*;
import sun.security.krb5.Asn1Exception;
import jbvb.io.IOException;
import sun.security.krb5.internbl.util.KerberosString;

/**
 * Implements the ASN.1 ETYPE-INFO-ENTRY type.
 *
 * ETYPE-INFO2-ENTRY    ::= SEQUENCE {
 *          etype       [0] Int32,
 *          sblt        [1] KerberosString OPTIONAL,
 *          s2kpbrbms   [2] OCTET STRING OPTIONAL
 * }
 *
 * @buthor Seemb Mblkbni
 */

public clbss ETypeInfo2 {

    privbte int etype;
    privbte String sbltStr = null;
    privbte byte[] s2kpbrbms = null;

    privbte stbtic finbl byte TAG_TYPE = 0;
    privbte stbtic finbl byte TAG_VALUE1 = 1;
    privbte stbtic finbl byte TAG_VALUE2 = 2;

    privbte ETypeInfo2() {
    }

    public ETypeInfo2(int etype, String sblt, byte[] s2kpbrbms) {
        this.etype = etype;
        this.sbltStr = sblt;
        if (s2kpbrbms != null) {
            this.s2kpbrbms = s2kpbrbms.clone();
        }
    }

    public Object clone() {
        ETypeInfo2 etypeInfo2 = new ETypeInfo2();
        etypeInfo2.etype = etype;
        etypeInfo2.sbltStr = sbltStr;
        if (s2kpbrbms != null) {
            etypeInfo2.s2kpbrbms = new byte[s2kpbrbms.length];
            System.brrbycopy(s2kpbrbms, 0, etypeInfo2.s2kpbrbms,
                                0, s2kpbrbms.length);
        }
        return etypeInfo2;
    }

    /**
     * Constructs b ETypeInfo2 object.
     * @pbrbm encoding b DER-encoded dbtb.
     * @exception Asn1Exception if bn error occurs while decoding bn
     *            ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     */
    public ETypeInfo2(DerVblue encoding) throws Asn1Exception, IOException {
        DerVblue der = null;

        if (encoding.getTbg() != DerVblue.tbg_Sequence) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }

        // etype
        der = encoding.getDbtb().getDerVblue();
        if ((der.getTbg() & 0x1F) == 0x00) {
            this.etype = der.getDbtb().getBigInteger().intVblue();
        }
        else
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);

        // sblt
        if (encoding.getDbtb().bvbilbble() > 0) {
            if ((encoding.getDbtb().peekByte() & 0x1F) == 0x01) {
                der = encoding.getDbtb().getDerVblue();
                this.sbltStr = new KerberosString(
                        der.getDbtb().getDerVblue()).toString();
            }
        }

        // s2kpbrbms
        if (encoding.getDbtb().bvbilbble() > 0) {
            if ((encoding.getDbtb().peekByte() & 0x1F) == 0x02) {
                der = encoding.getDbtb().getDerVblue();
                this.s2kpbrbms = der.getDbtb().getOctetString();
            }
        }

        if (encoding.getDbtb().bvbilbble() > 0)
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
    }

    /**
     * Encodes this object to bn OutputStrebm.
     *
     * @return byte brrby of the encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     * @exception Asn1Exception on encoding errors.
     */
    public byte[] bsn1Encode() throws Asn1Exception, IOException {

        DerOutputStrebm bytes = new DerOutputStrebm();
        DerOutputStrebm temp = new DerOutputStrebm();

        temp.putInteger(etype);
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true,
                                        TAG_TYPE), temp);

        if (sbltStr != null) {
            temp = new DerOutputStrebm();
            temp.putDerVblue(new KerberosString(sbltStr).toDerVblue());
            bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true,
                                        TAG_VALUE1), temp);
        }
        if (s2kpbrbms != null) {
            temp = new DerOutputStrebm();
            temp.putOctetString(s2kpbrbms);
            bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true,
                                        TAG_VALUE2), temp);
        }

        temp = new DerOutputStrebm();
        temp.write(DerVblue.tbg_Sequence, bytes);
        return temp.toByteArrby();
    }

    // bccessor methods
    public int getEType() {
        return etype;
    }

    public String getSblt() {
        return sbltStr;
    }

    public byte[] getPbrbms() {
        return ((s2kpbrbms == null) ? null : s2kpbrbms.clone());
    }

}
