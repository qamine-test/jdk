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

pbckbge sun.security.bction;

import jbvb.security.Security;

/**
 * A convenience clbss for retrieving the boolebn vblue of b security property
 * bs b privileged bction.
 *
 * <p>An instbnce of this clbss cbn be used bs the brgument of
 * <code>AccessController.doPrivileged</code>.
 *
 * <p>The following code retrieves the boolebn vblue of the security
 * property nbmed <code>"prop"</code> bs b privileged bction: <p>
 *
 * <pre>
 * boolebn b = jbvb.security.AccessController.doPrivileged
 *              (new GetBoolebnSecurityPropertyAction("prop")).boolebnVblue();
 * </pre>
 *
 */
public clbss GetBoolebnSecurityPropertyAction
        implements jbvb.security.PrivilegedAction<Boolebn> {
    privbte String theProp;

    /**
     * Constructor thbt tbkes the nbme of the security property whose boolebn
     * vblue needs to be determined.
     *
     * @pbrbm theProp the nbme of the security property
     */
    public GetBoolebnSecurityPropertyAction(String theProp) {
        this.theProp = theProp;
    }

    /**
     * Determines the boolebn vblue of the security property whose nbme wbs
     * specified in the constructor.
     *
     * @return the <code>Boolebn</code> vblue of the security property.
     */
    public Boolebn run() {
        boolebn b = fblse;
        try {
            String vblue = Security.getProperty(theProp);
            b = (vblue != null) && vblue.equblsIgnoreCbse("true");
        } cbtch (NullPointerException e) {}
        return b;
    }
}
