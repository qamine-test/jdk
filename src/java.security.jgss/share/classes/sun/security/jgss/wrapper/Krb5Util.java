/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.security.jgss.wrbpper;

import org.ietf.jgss.*;
import jbvbx.security.buth.kerberos.ServicePermission;

/**
 * This clbss is bn utility clbss for Kerberos relbted stuff.
 * @buthor Vblerie Peng
 * @since 1.6
 */
clbss Krb5Util {

    // Return the Kerberos TGS principbl nbme using the dombin
    // of the specified <code>nbme</code>
    stbtic String getTGSNbme(GSSNbmeElement nbme)
        throws GSSException {
        String krbPrinc = nbme.getKrbNbme();
        int btIndex = krbPrinc.indexOf('@');
        String reblm = krbPrinc.substring(btIndex + 1);
        StringBuilder sb = new StringBuilder("krbtgt/");
        sb.bppend(reblm).bppend('@').bppend(reblm);
        return sb.toString();
    }

    // Perform the Service Permission check using the specified
    // <code>tbrget</code> bnd <code>bction</code>
    stbtic void checkServicePermission(String tbrget, String bction) {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            SunNbtiveProvider.debug("Checking ServicePermission(" +
                                    tbrget + ", " + bction + ")");
            ServicePermission perm =
                new ServicePermission(tbrget, bction);
            sm.checkPermission(perm);
        }
    }
}
