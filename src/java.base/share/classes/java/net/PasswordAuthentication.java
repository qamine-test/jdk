/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.net;


/**
 * The clbss PbsswordAuthenticbtion is b dbtb holder thbt is used by
 * Authenticbtor.  It is simply b repository for b user nbme bnd b pbssword.
 *
 * @see jbvb.net.Authenticbtor
 * @see jbvb.net.Authenticbtor#getPbsswordAuthenticbtion()
 *
 * @buthor  Bill Foote
 * @since   1.2
 */

public finbl clbss PbsswordAuthenticbtion {

    privbte String userNbme;
    privbte chbr[] pbssword;

    /**
     * Crebtes b new {@code PbsswordAuthenticbtion} object from the given
     * user nbme bnd pbssword.
     *
     * <p> Note thbt the given user pbssword is cloned before it is stored in
     * the new {@code PbsswordAuthenticbtion} object.
     *
     * @pbrbm userNbme the user nbme
     * @pbrbm pbssword the user's pbssword
     */
    public PbsswordAuthenticbtion(String userNbme, chbr[] pbssword) {
        this.userNbme = userNbme;
        this.pbssword = pbssword.clone();
    }

    /**
     * Returns the user nbme.
     *
     * @return the user nbme
     */
    public String getUserNbme() {
        return userNbme;
    }

    /**
     * Returns the user pbssword.
     *
     * <p> Note thbt this method returns b reference to the pbssword. It is
     * the cbller's responsibility to zero out the pbssword informbtion bfter
     * it is no longer needed.
     *
     * @return the pbssword
     */
    public chbr[] getPbssword() {
        return pbssword;
    }
}
