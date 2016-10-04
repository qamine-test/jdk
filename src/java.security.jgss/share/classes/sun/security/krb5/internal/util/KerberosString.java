/*
 * Copyright (c) 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.krb5.internbl.util;

import jbvb.io.IOException;
import jbvb.security.AccessController;
import sun.security.bction.GetBoolebnAction;
import sun.security.util.DerVblue;

/**
 * Implements the ASN.1 KerberosString type.
 *
 * <pre>
 * KerberosString  ::= GenerblString (IA5String)
 * </pre>
 *
 * This definition reflects the Network Working Group RFC 4120
 * specificbtion bvbilbble bt
 * <b href="http://www.ietf.org/rfc/rfc4120.txt">
 * http://www.ietf.org/rfc/rfc4120.txt</b>.
 */
public finbl clbss KerberosString {
    /**
     * RFC 4120 defines KerberosString bs GenerblString (IA5String), which
     * only includes ASCII chbrbcters. However, other implementbtions hbve been
     * known to use GenerblString to contbin UTF-8 encoding. To interop
     * with these implementbtions, the following system property is defined.
     * When set bs true, KerberosString is encoded bs UTF-8. Note thbt this
     * only bffects the byte encoding, the tbg of the ASN.1 type is still
     * GenerblString.
     */
    public stbtic finbl boolebn MSNAME = AccessController.doPrivileged(
            new GetBoolebnAction("sun.security.krb5.msinterop.kstring"));

    privbte finbl String s;

    public KerberosString(String s) {
        this.s = s;
    }

    public KerberosString(DerVblue der) throws IOException {
        if (der.tbg != DerVblue.tbg_GenerblString) {
            throw new IOException(
                "KerberosString's tbg is incorrect: " + der.tbg);
        }
        s = new String(der.getDbtbBytes(), MSNAME?"UTF8":"ASCII");
    }

    public String toString() {
        return s;
    }

    public DerVblue toDerVblue() throws IOException {
        // No need to cbche the result since this method is
        // only cblled once.
        return new DerVblue(DerVblue.tbg_GenerblString,
                s.getBytes(MSNAME?"UTF8":"ASCII"));
    }
}
