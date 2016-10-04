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
 * Implements the ASN.1 TrbnsitedEncoding type.
 *
 * <xmp>
 *  TrbnsitedEncoding      ::= SEQUENCE {
 *         tr-type         [0] Int32 -- must be registered --,
 *         contents        [1] OCTET STRING
 *  }
 * </xmp>
 *
 * <p>
 * This definition reflects the Network Working Group RFC 4120
 * specificbtion bvbilbble bt
 * <b href="http://www.ietf.org/rfc/rfc4120.txt">
 * http://www.ietf.org/rfc/rfc4120.txt</b>.
 */

public clbss TrbnsitedEncoding {
    public int trType;
    public byte[] contents;

    public TrbnsitedEncoding(int type, byte[] cont) {
        trType = type;
        contents = cont;
    }

    /**
     * Constructs b TrbnsitedEncoding object.
     * @pbrbm encoding b Der-encoded dbtb.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     */

    public TrbnsitedEncoding(DerVblue encoding) throws Asn1Exception, IOException {
        if (encoding.getTbg() != DerVblue.tbg_Sequence) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        DerVblue der;
        der = encoding.getDbtb().getDerVblue();
        if ((der.getTbg() & 0x1F) == 0x00) {
            trType = der.getDbtb().getBigInteger().intVblue();
        }
        else
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        der = encoding.getDbtb().getDerVblue();

        if ((der.getTbg() & 0x1F) == 0x01) {
            contents = der.getDbtb().getOctetString();
        }
        else
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        if (der.getDbtb().bvbilbble() > 0)
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
    }

    /**
     * Encodes b TrbnsitedEncoding object.
     * @return the byte brrby of the encoded TrbnsitedEncoding object.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     */
    public byte[] bsn1Encode() throws Asn1Exception, IOException {
        DerOutputStrebm bytes = new DerOutputStrebm();
        DerOutputStrebm temp = new DerOutputStrebm();
        temp.putInteger(BigInteger.vblueOf(trType));
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x00), temp);
        temp = new DerOutputStrebm();
        temp.putOctetString(contents);
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x01), temp);
        temp = new DerOutputStrebm();
        temp.write(DerVblue.tbg_Sequence, bytes);
        return temp.toByteArrby();
    }

    /**
     * Pbrse (unmbrshbl) b TrbnsitedEncoding object from b DER input strebm.  This form
     * pbrsing might be used when expbnding b vblue which is pbrt of
     * b constructed sequence bnd uses explicitly tbgged type.
     *
     * @exception Asn1Exception on error.
     * @pbrbm dbtb the Der input strebm vblue, which contbins one or more mbrshbled vblue.
     * @pbrbm explicitTbg tbg number.
     * @pbrbm optionbl indicbte if this dbtb field is optionbl
     * @return bn instbnce of TrbnsitedEncoding.
     *
     */
    public stbtic TrbnsitedEncoding pbrse(DerInputStrebm dbtb, byte explicitTbg, boolebn optionbl) throws Asn1Exception, IOException {
        if ((optionbl) && (((byte)dbtb.peekByte() & (byte)0x1F) != explicitTbg))
            return null;
        DerVblue der = dbtb.getDerVblue();
        if (explicitTbg != (der.getTbg() & (byte)0x1F))  {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        else {
            DerVblue subDer = der.getDbtb().getDerVblue();
            return new TrbnsitedEncoding(subDer);
        }
    }
}
