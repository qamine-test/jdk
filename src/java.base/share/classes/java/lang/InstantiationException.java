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
 * Thrown when bn bpplicbtion tries to crebte bn instbnce of b clbss
 * using the {@code newInstbnce} method in clbss
 * {@code Clbss}, but the specified clbss object cbnnot be
 * instbntibted.  The instbntibtion cbn fbil for b vbriety of
 * rebsons including but not limited to:
 *
 * <ul>
 * <li> the clbss object represents bn bbstrbct clbss, bn interfbce,
 *      bn brrby clbss, b primitive type, or {@code void}
 * <li> the clbss hbs no nullbry constructor
 *</ul>
 *
 * @buthor  unbscribed
 * @see     jbvb.lbng.Clbss#newInstbnce()
 * @since   1.0
 */
public
clbss InstbntibtionException extends ReflectiveOperbtionException {
    privbte stbtic finbl long seriblVersionUID = -8441929162975509110L;

    /**
     * Constructs bn {@code InstbntibtionException} with no detbil messbge.
     */
    public InstbntibtionException() {
        super();
    }

    /**
     * Constructs bn {@code InstbntibtionException} with the
     * specified detbil messbge.
     *
     * @pbrbm   s   the detbil messbge.
     */
    public InstbntibtionException(String s) {
        super(s);
    }
}
