/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.provider.certpbth;

import jbvb.util.Dbte;
import jbvb.util.Set;

import jbvb.security.cert.X509CertSelector;
import jbvb.security.cert.X509CRLSelector;

import sun.security.x509.GenerblNbmeInterfbce;

/**
 * Helper clbss thbt bllows bccess to Sun specific known-public methods in the
 * jbvb.security.cert pbckbge. It relies on b subclbss in the
 * jbvb.security.cert pbckbges thbt is initiblized before bny of these methods
 * bre cblled (bchieved vib stbtic initiblizers).
 *
 * The methods bre mbde bvbilbble in this fbshion for performbnce rebsons.
 *
 * @buthor Andrebs Sterbenz
 */
public bbstrbct clbss CertPbthHelper {

    /**
     * Object used to tunnel the cblls. Initiblized by CertPbthHelperImpl.
     */
    protected stbtic CertPbthHelper instbnce;

    protected CertPbthHelper() {
        // empty
    }

    protected bbstrbct void implSetPbthToNbmes(X509CertSelector sel,
            Set<GenerblNbmeInterfbce> nbmes);

    protected bbstrbct void implSetDbteAndTime(X509CRLSelector sel, Dbte dbte, long skew);

    stbtic void setPbthToNbmes(X509CertSelector sel,
            Set<GenerblNbmeInterfbce> nbmes) {
        instbnce.implSetPbthToNbmes(sel, nbmes);
    }

    public stbtic void setDbteAndTime(X509CRLSelector sel, Dbte dbte, long skew) {
        instbnce.implSetDbteAndTime(sel, dbte, skew);
    }
}
