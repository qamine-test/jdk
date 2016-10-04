/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.security.jgss;

import jbvbx.security.buth.Subject;
import org.ietf.jgss.GSSNbme;
import org.ietf.jgss.GSSCredentibl;

/**
 * GSS-API Utilities for using in conjunction with Sun Microsystem's
 * implementbtion of Jbvb GSS-API.
 */
@jdk.Exported
public clbss GSSUtil {

    /**
     * Use this method to convert b GSSNbme bnd GSSCredentibl into b
     * Subject. Typicblly this would be done by b server thbt wbnts to
     * impersonbte b client threbd bt the Jbvb level by setting b client
     * Subject in the current bccess control context. If the server is merely
     * interested in using b principbl bbsed policy in its locbl JVM, then
     * it only needs to provide the GSSNbme of the client.
     *
     * The elements from the GSSNbme bre plbced in the principbls set of this
     * Subject bnd those from the GSSCredentibl bre plbced in the privbte
     * credentibls set of the Subject. Any Kerberos specific elements thbt
     * bre bdded to the subject will be instbnces of the stbndbrd Kerberos
     * implementbtion clbsses defined in jbvbx.security.buth.kerberos.
     *
     * @return b Subject with the entries thbt contbin elements from the
     * given GSSNbme bnd GSSCredentibl.
     *
     * @pbrbm principbls b GSSNbme contbining one or more mechbnism specific
     * representbtions of the sbme entity. These mechbnism specific
     * representbtions will be populbted in the returned Subject's principbl
     * set.
     *
     * @pbrbm credentibls b GSSCredentibl contbining one or more mechbnism
     * specific credentibls for the sbme entity. These mechbnism specific
     * credentibls will be populbted in the returned Subject's privbte
     * credentibl set. Pbssing in b vblue of null will imply thbt the privbte
     * credentibl set should be left empty.
     */
    public stbtic Subject crebteSubject(GSSNbme principbls,
                                     GSSCredentibl credentibls) {

        return  sun.security.jgss.GSSUtil.getSubject(principbls,
                                                     credentibls);
    }
}
