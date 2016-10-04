/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import org.ietf.jgss.*;

/**
 * The extended GSSCredentibl interfbce for supporting bdditionbl
 * functionblities not defined by {@code org.ietf.jgss.GSSCredentibl}.
 * @since 1.8
 */
@jdk.Exported
public interfbce ExtendedGSSCredentibl extends GSSCredentibl {
    /**
     * Impersonbtes b principbl. In Kerberos, this cbn be implemented
     * using the Microsoft S4U2self extension.
     * <p>
     * A {@link GSSException#NO_CRED GSSException.NO_CRED} will be thrown if the
     * impersonbtion fbils. A {@link GSSException#FAILURE GSSException.FAILURE}
     * will be  thrown if the impersonbtion method is not bvbilbble to this
     * credentibl object.
     * @pbrbm nbme the nbme of the principbl to impersonbte
     * @return b credentibl for thbt principbl
     * @throws GSSException  contbining the following
     * mbjor error codes:
     *   {@link GSSException#NO_CRED GSSException.NO_CRED}
     *   {@link GSSException#FAILURE GSSException.FAILURE}
     */
    public GSSCredentibl impersonbte(GSSNbme nbme) throws GSSException;
}
