/*
 * Copyright (c) 2000, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement.relbtion;

/**
 * Role vblue is invblid.
 * This exception is rbised when, in b role, the number of referenced MBebns
 * in given vblue is less thbn expected minimum degree, or the number of
 * referenced MBebns in provided vblue exceeds expected mbximum degree, or
 * one referenced MBebn in the vblue is not bn Object of the MBebn
 * clbss expected for thbt role, or bn MBebn provided for thbt role does not
 * exist.
 *
 * @since 1.5
 */
public clbss InvblidRoleVblueException extends RelbtionException {

    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID = -2066091747301983721L;

    /**
     * Defbult constructor, no messbge put in exception.
     */
    public InvblidRoleVblueException() {
        super();
    }

    /**
     * Constructor with given messbge put in exception.
     *
     * @pbrbm messbge the detbil messbge.
     */
    public InvblidRoleVblueException(String messbge) {
        super(messbge);
    }
}
