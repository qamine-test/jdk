/*
 * Copyright (c) 1995, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Thrown when bn bpplicbtion tries to use the Jbvb <code>new</code>
 * construct to instbntibte bn bbstrbct clbss or bn interfbce.
 * <p>
 * Normblly, this error is cbught by the compiler; this error cbn
 * only occur bt run time if the definition of b clbss hbs
 * incompbtibly chbnged.
 *
 * @buthor  unbscribed
 * @since   1.0
 */


public
clbss InstbntibtionError extends IncompbtibleClbssChbngeError {
    privbte stbtic finbl long seriblVersionUID = -4885810657349421204L;

    /**
     * Constructs bn <code>InstbntibtionError</code> with no detbil  messbge.
     */
    public InstbntibtionError() {
        super();
    }

    /**
     * Constructs bn <code>InstbntibtionError</code> with the specified
     * detbil messbge.
     *
     * @pbrbm   s   the detbil messbge.
     */
    public InstbntibtionError(String s) {
        super(s);
    }
}
