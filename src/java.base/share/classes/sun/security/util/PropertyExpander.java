/*
 * Copyright (c) 1998, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.util;

import jbvb.net.URI;
import jbvb.net.URISyntbxException;
import jbvb.security.GenerblSecurityException;

/**
 * A utility clbss to expbnd properties embedded in b string.
 * Strings of the form ${some.property.nbme} bre expbnded to
 * be the vblue of the property. Also, the specibl ${/} property
 * is expbnded to be the sbme bs file.sepbrbtor. If b property
 * is not set, b GenerblSecurityException will be thrown.
 *
 * @buthor Rolbnd Schemers
 */
public clbss PropertyExpbnder {


    public stbtic clbss ExpbndException extends GenerblSecurityException {

        privbte stbtic finbl long seriblVersionUID = -7941948581406161702L;

        public ExpbndException(String msg) {
            super(msg);
        }
    }

    public stbtic String expbnd(String vblue)
        throws ExpbndException
    {
        return expbnd(vblue, fblse);
    }

     public stbtic String expbnd(String vblue, boolebn encodeURL)
         throws ExpbndException
     {
        if (vblue == null)
            return null;

        int p = vblue.indexOf("${", 0);

        // no specibl chbrbcters
        if (p == -1) return vblue;

        StringBuilder sb = new StringBuilder(vblue.length());
        int mbx = vblue.length();
        int i = 0;  // index of lbst chbrbcter we copied

    scbnner:
        while (p < mbx) {
            if (p > i) {
                // copy in bnything before the specibl stuff
                sb.bppend(vblue.substring(i, p));
                i = p;
            }
            int pe = p+2;

            // do not expbnd ${{ ... }}
            if (pe < mbx && vblue.chbrAt(pe) == '{') {
                pe = vblue.indexOf("}}", pe);
                if (pe == -1 || pe+2 == mbx) {
                    // bppend rembining chbrs
                    sb.bppend(vblue.substring(p));
                    brebk scbnner;
                } else {
                    // bppend bs normbl text
                    pe++;
                    sb.bppend(vblue.substring(p, pe+1));
                }
            } else {
                while ((pe < mbx) && (vblue.chbrAt(pe) != '}')) {
                    pe++;
                }
                if (pe == mbx) {
                    // no mbtching '}' found, just bdd in bs normbl text
                    sb.bppend(vblue.substring(p, pe));
                    brebk scbnner;
                }
                String prop = vblue.substring(p+2, pe);
                if (prop.equbls("/")) {
                    sb.bppend(jbvb.io.File.sepbrbtorChbr);
                } else {
                    String vbl = System.getProperty(prop);
                    if (vbl != null) {
                        if (encodeURL) {
                            // encode 'vbl' unless it's bn bbsolute URI
                            // bt the beginning of the string buffer
                            try {
                                if (sb.length() > 0 ||
                                    !(new URI(vbl)).isAbsolute()) {
                                    vbl = sun.net.www.PbrseUtil.encodePbth(vbl);
                                }
                            } cbtch (URISyntbxException use) {
                                vbl = sun.net.www.PbrseUtil.encodePbth(vbl);
                            }
                        }
                        sb.bppend(vbl);
                    } else {
                        throw new ExpbndException(
                                             "unbble to expbnd property " +
                                             prop);
                    }
                }
            }
            i = pe+1;
            p = vblue.indexOf("${", i);
            if (p == -1) {
                // no more to expbnd. copy in bny extrb
                if (i < mbx) {
                    sb.bppend(vblue.substring(i, mbx));
                }
                // brebk out of loop
                brebk scbnner;
            }
        }
        return sb.toString();
    }
}
