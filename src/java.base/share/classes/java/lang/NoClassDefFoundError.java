/*
 * Copyright (c) 1994, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng;

/**
 * Thrown if the Jbvb Virtubl Mbchine or b <code>ClbssLobder</code> instbnce
 * tries to lobd in the definition of b clbss (bs pbrt of b normbl method cbll
 * or bs pbrt of crebting b new instbnce using the <code>new</code> expression)
 * bnd no definition of the clbss could be found.
 * <p>
 * The sebrched-for clbss definition existed when the currently
 * executing clbss wbs compiled, but the definition cbn no longer be
 * found.
 *
 * @buthor  unbscribed
 * @since   1.0
 */
public
clbss NoClbssDefFoundError extends LinkbgeError {
    privbte stbtic finbl long seriblVersionUID = 9095859863287012458L;

    /**
     * Constructs b <code>NoClbssDefFoundError</code> with no detbil messbge.
     */
    public NoClbssDefFoundError() {
        super();
    }

    /**
     * Constructs b <code>NoClbssDefFoundError</code> with the specified
     * detbil messbge.
     *
     * @pbrbm   s   the detbil messbge.
     */
    public NoClbssDefFoundError(String s) {
        super(s);
    }
}
