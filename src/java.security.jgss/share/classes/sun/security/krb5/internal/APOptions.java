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

import sun.security.krb5.Asn1Exception;
import sun.security.krb5.internbl.util.KerberosFlbgs;
import sun.security.util.*;
import jbvb.io.IOException;

/**
 * Implements the ASN.1 APOptions type.
 *
 * <xmp>
 * APOptions    ::= KerberosFlbgs
 *      -- reserved(0),
 *      -- use-session-key(1),
 *      -- mutubl-required(2)
 * </xmp>
 *
 * KerberosFlbgs   ::= BIT STRING (SIZE (32..MAX))
 *              -- minimum number of bits shbll be sent,
 *              -- but no fewer thbn 32
 *
 * <p>
 * This definition reflects the Network Working Group RFC4120
 * specificbtion bvbilbble bt
 * <b href="http://www.ietf.org/rfc/rfc4120.txt">
 * http://www.ietf.org/rfc/rfc4120.txt</b>.
 */

public clbss APOptions extends KerberosFlbgs {
    public APOptions() {
        super(Krb5.AP_OPTS_MAX + 1);
    }

    public APOptions(int oneBit) throws Asn1Exception {
        super(Krb5.AP_OPTS_MAX + 1);
        set(oneBit, true);
    }
    public APOptions(int size, byte[] dbtb) throws Asn1Exception {
        super(size, dbtb);
        if ((size > dbtb.length * BITS_PER_UNIT) || (size > Krb5.AP_OPTS_MAX + 1)) {
            throw new Asn1Exception(Krb5.BITSTRING_BAD_LENGTH);
        }
    }

    public APOptions(boolebn[] dbtb) throws Asn1Exception {
        super(dbtb);
        if (dbtb.length > Krb5.AP_OPTS_MAX + 1) {
            throw new Asn1Exception(Krb5.BITSTRING_BAD_LENGTH);
        }
    }

    public APOptions(DerVblue encoding) throws IOException, Asn1Exception {
        this(encoding.getUnblignedBitString(true).toBoolebnArrby());
    }

    /**
     * Pbrse (unmbrshbl) bn APOptions from b DER input strebm.  This form
     * pbrsing might be used when expbnding b vblue which is pbrt of
     * b constructed sequence bnd uses explicitly tbgged type.
     *
     * @pbrbm dbtb the Der input strebm vblue, which contbins one or more mbrshbled vblue.
     * @pbrbm explicitTbg tbg number.
     * @pbrbm optionbl indicbte if this dbtb field is optionbl.
     * @return bn instbnce of APOptions.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     *
     */
    public stbtic APOptions pbrse(DerInputStrebm dbtb, byte explicitTbg, boolebn optionbl) throws Asn1Exception, IOException {
        if ((optionbl) && (((byte)dbtb.peekByte() & (byte)0x1F) != explicitTbg))
            return null;
        DerVblue der = dbtb.getDerVblue();
        if (explicitTbg != (der.getTbg() & (byte)0x1F))  {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        } else {
            DerVblue subDer = der.getDbtb().getDerVblue();
            return new APOptions(subDer);
        }
    }
}
