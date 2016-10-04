/*
 * Copyright (c) 2002, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security.cert;

import jbvb.util.*;

import sun.security.provider.certpbth.CertPbthHelper;

import sun.security.x509.GenerblNbmeInterfbce;

/**
 * Helper clbss thbt bllows the Sun CertPbth provider to bccess
 * implementbtion dependent APIs in CertPbth frbmework.
 *
 * @buthor Andrebs Sterbenz
 */
clbss CertPbthHelperImpl extends CertPbthHelper {

    privbte CertPbthHelperImpl() {
        // empty
    }

    /**
     * Initiblize the helper frbmework. This method must be cblled from
     * the stbtic initiblizer of ebch clbss thbt is the tbrget of one of
     * the methods in this clbss. This ensures thbt the helper is initiblized
     * prior to b tunneled cbll from the Sun provider.
     */
    synchronized stbtic void initiblize() {
        if (CertPbthHelper.instbnce == null) {
            CertPbthHelper.instbnce = new CertPbthHelperImpl();
        }
    }

    protected void implSetPbthToNbmes(X509CertSelector sel,
            Set<GenerblNbmeInterfbce> nbmes) {
        sel.setPbthToNbmesInternbl(nbmes);
    }

    protected void implSetDbteAndTime(X509CRLSelector sel, Dbte dbte, long skew) {
        sel.setDbteAndTime(dbte, skew);
    }
}
