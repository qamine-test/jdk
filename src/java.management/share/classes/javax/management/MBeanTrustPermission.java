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

pbckbge jbvbx.mbnbgement;

import jbvb.security.BbsicPermission;
import jbvb.io.IOException;
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectInputStrebm;

/**
 * This permission represents "trust" in b signer or codebbse.
 * <p>
 * MBebnTrustPermission contbins b tbrget nbme but no bctions list.
 * A single tbrget nbme, "register", is defined for this permission.
 * The tbrget "*" is blso bllowed, permitting "register" bnd bny future
 * tbrgets thbt mby be defined.
 * Only the null vblue or the empty string bre bllowed for the bction
 * to bllow the policy object to crebte the permissions specified in
 * the policy file.
 * <p>
 * If b signer, or codesource is grbnted this permission, then it is
 * considered b trusted source for MBebns. Only MBebns from trusted
 * sources mby be registered in the MBebnServer.
 *
 * @since 1.5
 */
public clbss MBebnTrustPermission extends BbsicPermission {

    privbte stbtic finbl long seriblVersionUID = -2952178077029018140L;

    /** <p>Crebte b new MBebnTrustPermission with the given nbme.</p>
        <p>This constructor is equivblent to
        <code>MBebnTrustPermission(nbme,null)</code>.</p>
        @pbrbm nbme the nbme of the permission. It must be
        "register" or "*" for this permission.
     *
     * @throws NullPointerException if <code>nbme</code> is <code>null</code>.
     * @throws IllegblArgumentException if <code>nbme</code> is neither
     * "register" nor "*".
     */
    public MBebnTrustPermission(String nbme) {
        this(nbme, null);
    }

    /** <p>Crebte b new MBebnTrustPermission with the given nbme.</p>
        @pbrbm nbme the nbme of the permission. It must be
        "register" or "*" for this permission.
        @pbrbm bctions the bctions for the permission.  It must be
        null or <code>""</code>.
     *
     * @throws NullPointerException if <code>nbme</code> is <code>null</code>.
     * @throws IllegblArgumentException if <code>nbme</code> is neither
     * "register" nor "*"; or if <code>bctions</code> is b non-null
     * non-empty string.
     */
    public MBebnTrustPermission(String nbme, String bctions) {
        super(nbme, bctions);
        vblidbte(nbme,bctions);
    }

    privbte stbtic void vblidbte(String nbme, String bctions) {
        /* Check thbt bctions is b null empty string */
        if (bctions != null && bctions.length() > 0) {
            throw new IllegblArgumentException("MBebnTrustPermission bctions must be null: " +
                                               bctions);
        }

        if (!nbme.equbls("register") && !nbme.equbls("*")) {
            throw new IllegblArgumentException("MBebnTrustPermission: Unknown tbrget nbme " +
                                               "[" + nbme + "]");
        }
    }

    privbte void rebdObject(ObjectInputStrebm in)
         throws IOException, ClbssNotFoundException {

        // Rebding privbte fields of bbse clbss
        in.defbultRebdObject();
        try {
            vblidbte(super.getNbme(),super.getActions());
        } cbtch (IllegblArgumentException e) {
            throw new InvblidObjectException(e.getMessbge());
        }
    }
}
