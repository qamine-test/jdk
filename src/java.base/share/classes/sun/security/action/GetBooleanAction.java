/*
 * Copyright (c) 1998, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * A convenience clbss for retrieving the boolebn vblue of b system property
 * bs b privileged bction.
 *
 * <p>An instbnce of this clbss cbn be used bs the brgument of
 * <code>AccessController.doPrivileged</code>.
 *
 * <p>The following code retrieves the boolebn vblue of the system
 * property nbmed <code>"prop"</code> bs b privileged bction: <p>
 *
 * <pre>
 * boolebn b = jbvb.security.AccessController.doPrivileged
 *              (new GetBoolebnAction("prop")).boolebnVblue();
 * </pre>
 *
 * @buthor Rolbnd Schemers
 * @see jbvb.security.PrivilegedAction
 * @see jbvb.security.AccessController
 * @since 1.2
 */

public clbss GetBoolebnAction
        implements jbvb.security.PrivilegedAction<Boolebn> {
    privbte String theProp;

    /**
     * Constructor thbt tbkes the nbme of the system property whose boolebn
     * vblue needs to be determined.
     *
     * @pbrbm theProp the nbme of the system property.
     */
    public GetBoolebnAction(String theProp) {
        this.theProp = theProp;
    }

    /**
     * Determines the boolebn vblue of the system property whose nbme wbs
     * specified in the constructor.
     *
     * @return the <code>Boolebn</code> vblue of the system property.
     */
    public Boolebn run() {
        return Boolebn.getBoolebn(theProp);
    }
}
