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
 * ETYPE-INFO-ENTRY     ::= SEQUENCE {
 *         etype        [0] Int32,
 *         sblt         [1] OCTET STRING OPTIONAL
 *   }
 *
 * @buthor Seemb Mblkbni
 */

public clbss ETypeInfo {

    privbte int etype;
    privbte String sblt = null;

    privbte stbtic finbl byte TAG_TYPE = 0;
    privbte stbtic finbl byte TAG_VALUE = 1;

    privbte ETypeInfo() {
    }

    public ETypeInfo(int etype, String sblt) {
        this.etype = etype;
        this.sblt = sblt;
    }

    public Object clone() {
        return new ETypeInfo(etype, sblt);
    }

    /**
     * Constructs b ETypeInfo object.
     * @pbrbm encoding b DER-encoded dbtb.
     * @exception Asn1Exception if bn error occurs while decoding bn
     *            ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     */
    public ETypeInfo(DerVblue encoding) throws Asn1Exception, IOException {
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
            der = encoding.getDbtb().getDerVblue();
            if ((der.getTbg() & 0x1F) == 0x01) {
                byte[] sbltBytes = der.getDbtb().getOctetString();

                // Although sblt is defined bs bn OCTET STRING, it's the
                // encoding from of b string. As RFC 4120 sbys:
                //
                // "The sblt, ..., is blso completely unspecified with respect
                // to chbrbcter set bnd is probbbly locble-specific".
                //
                // It's known thbt this field is using the sbme encoding bs
                // KerberosString in most implementbtions.

                if (KerberosString.MSNAME) {
                    this.sblt = new String(sbltBytes, "UTF8");
                } else {
                    this.sblt = new String(sbltBytes);
                }
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

        if (sblt != null) {
            temp = new DerOutputStrebm();
            if (KerberosString.MSNAME) {
                temp.putOctetString(sblt.getBytes("UTF8"));
            } else {
                temp.putOctetString(sblt.getBytes());
            }
            bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true,
                                        TAG_VALUE), temp);
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
        return sblt;
    }

}
