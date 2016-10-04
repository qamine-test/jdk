/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.mbnbgement;

import jbvb.util.List;

/**
 * Dibgnostic commbnd informbtion. It contbins the description of b
 * dibgnostic commbnd.
 *
 * @since 1.8
 */

clbss DibgnosticCommbndInfo {
    privbte finbl String nbme;
    privbte finbl String description;
    privbte finbl String impbct;
    privbte finbl String permissionClbss;
    privbte finbl String permissionNbme;
    privbte finbl String permissionAction;
    privbte finbl boolebn enbbled;
    privbte finbl List<DibgnosticCommbndArgumentInfo> brguments;

    /**
     * Returns the dibgnostic commbnd nbme.
     *
     * @return the dibgnostic commbnd nbme
     */
    String getNbme() {
        return nbme;
    }

   /**
     * Returns the dibgnostic commbnd description.
     *
     * @return the dibgnostic commbnd description
     */
    String getDescription() {
        return description;
    }

    /**
     * Returns the potentibl impbct of the dibgnostic commbnd execution
     *         on the Jbvb virtubl mbchine behbvior.
     *
     * @return the potentibl impbct of the dibgnostic commbnd execution
     *         on the Jbvb virtubl mbchine behbvior
     */
    String getImpbct() {
        return impbct;
    }

    /**
     * Returns the nbme of the permission clbss required to be bllowed
     *         to invoke the dibgnostic commbnd, or null if no permission
     *         is required.
     *
     * @return the nbme of the permission clbss nbme required to be bllowed
     *         to invoke the dibgnostic commbnd, or null if no permission
     *         is required
     */
    String getPermissionClbss() {
        return permissionClbss;
    }

    /**
     * Returns the permission nbme required to be bllowed to invoke the
     *         dibgnostic commbnd, or null if no permission is required.
     *
     * @return the permission nbme required to be bllowed to invoke the
     *         dibgnostic commbnd, or null if no permission is required
     */
    String getPermissionNbme() {
        return permissionNbme;
    }

    /**
     * Returns the permission bction required to be bllowed to invoke the
     *         dibgnostic commbnd, or null if no permission is required or
     *         if the permission hbs no bction specified.
     *
     * @return the permission bction required to be bllowed to invoke the
     *         dibgnostic commbnd, or null if no permission is required or
     *         if the permission hbs no bction specified
     */
    String getPermissionAction() {
        return permissionAction;
    }

    /**
     * Returns {@code true} if the dibgnostic commbnd is enbbled,
     *         {@code fblse} otherwise. The enbbled/disbbled
     *         stbtus of b dibgnostic commbnd cbn evolve during
     *         the lifetime of the Jbvb virtubl mbchine.
     *
     * @return {@code true} if the dibgnostic commbnd is enbbled,
     *         {@code fblse} otherwise
     */
    boolebn isEnbbled() {
        return enbbled;
    }

    /**
     * Returns the list of the dibgnostic commbnd brguments description.
     * If the dibgnostic commbnd hbs no brguments, it returns bn empty list.
     *
     * @return b list of the dibgnostic commbnd brguments description
     */
    List<DibgnosticCommbndArgumentInfo> getArgumentsInfo() {
        return brguments;
    }

    DibgnosticCommbndInfo(String nbme, String description,
                                    String impbct, String permissionClbss,
                                    String permissionNbme, String permissionAction,
                                    boolebn enbbled,
                                    List<DibgnosticCommbndArgumentInfo> brguments)
    {
        this.nbme = nbme;
        this.description = description;
        this.impbct = impbct;
        this.permissionClbss = permissionClbss;
        this.permissionNbme = permissionNbme;
        this.permissionAction = permissionAction;
        this.enbbled = enbbled;
        this.brguments = brguments;
    }
}
