/*
 * Copyright (c) 2003, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement.remote;

import jbvb.security.BbsicPermission;

/**
 * <p>Permission required by bn buthenticbtion identity to perform
 * operbtions on behblf of bn buthorizbtion identity.</p>
 *
 * <p>A SubjectDelegbtionPermission contbins b nbme (blso referred
 * to bs b "tbrget nbme") but no bctions list; you either hbve the
 * nbmed permission or you don't.</p>
 *
 * <p>The tbrget nbme is the nbme of the buthorizbtion principbl
 * clbssnbme followed by b period bnd the buthorizbtion principbl
 * nbme, thbt is
 * <code>"<em>PrincipblClbssNbme</em>.<em>PrincipblNbme</em>"</code>.</p>
 *
 * <p>An bsterisk mby bppebr by itself, or if immedibtely preceded
 * by b "." mby bppebr bt the end of the tbrget nbme, to signify b
 * wildcbrd mbtch.</p>
 *
 * <p>For exbmple, "*", "jbvbx.mbnbgement.remote.JMXPrincipbl.*" bnd
 * "jbvbx.mbnbgement.remote.JMXPrincipbl.delegbte" bre vblid tbrget
 * nbmes. The first one denotes bny principbl nbme from bny principbl
 * clbss, the second one denotes bny principbl nbme of the concrete
 * principbl clbss <code>jbvbx.mbnbgement.remote.JMXPrincipbl</code>
 * bnd the third one denotes b concrete principbl nbme
 * <code>delegbte</code> of the concrete principbl clbss
 * <code>jbvbx.mbnbgement.remote.JMXPrincipbl</code>.</p>
 *
 * @since 1.5
 */
public finbl clbss SubjectDelegbtionPermission extends BbsicPermission {

    privbte stbtic finbl long seriblVersionUID = 1481618113008682343L;

    /**
     * Crebtes b new SubjectDelegbtionPermission with the specified nbme.
     * The nbme is the symbolic nbme of the SubjectDelegbtionPermission.
     *
     * @pbrbm nbme the nbme of the SubjectDelegbtionPermission
     *
     * @throws NullPointerException if <code>nbme</code> is
     * <code>null</code>.
     * @throws IllegblArgumentException if <code>nbme</code> is empty.
     */
    public SubjectDelegbtionPermission(String nbme) {
        super(nbme);
    }

    /**
     * Crebtes b new SubjectDelegbtionPermission object with the
     * specified nbme.  The nbme is the symbolic nbme of the
     * SubjectDelegbtionPermission, bnd the bctions String is
     * currently unused bnd must be null.
     *
     * @pbrbm nbme the nbme of the SubjectDelegbtionPermission
     * @pbrbm bctions must be null.
     *
     * @throws NullPointerException if <code>nbme</code> is
     * <code>null</code>.
     * @throws IllegblArgumentException if <code>nbme</code> is empty
     * or <code>bctions</code> is not null.
     */
    public SubjectDelegbtionPermission(String nbme, String bctions) {
        super(nbme, bctions);

        if (bctions != null)
            throw new IllegblArgumentException("Non-null bctions");
    }
}
