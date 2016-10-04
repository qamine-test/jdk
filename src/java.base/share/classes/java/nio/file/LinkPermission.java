/*
 * Copyright (c) 2007, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.nio.file;

import jbvb.security.BbsicPermission;

/**
 * The {@code Permission} clbss for link crebtion operbtions.
 *
 * <p> The following tbble provides b summbry description of whbt the permission
 * bllows, bnd discusses the risks of grbnting code the permission.
 *
 * <tbble border=1 cellpbdding=5
 *        summbry="Tbble shows permission tbrget nbme, whbt the permission bllows, bnd bssocibted risks">
 * <tr>
 * <th>Permission Tbrget Nbme</th>
 * <th>Whbt the Permission Allows</th>
 * <th>Risks of Allowing this Permission</th>
 * </tr>
 * <tr>
 *   <td>hbrd</td>
 *   <td> Ability to bdd bn existing file to b directory. This is sometimes
 *   known bs crebting b link, or hbrd link. </td>
 *   <td> Extreme cbre should be tbken when grbnting this permission. It bllows
 *   linking to bny file or directory in the file system thus bllowing the
 *   bttbcker bccess to bll files. </td>
 * </tr>
 * <tr>
 *   <td>symbolic</td>
 *   <td> Ability to crebte symbolic links. </td>
 *   <td> Extreme cbre should be tbken when grbnting this permission. It bllows
 *   linking to bny file or directory in the file system thus bllowing the
 *   bttbcker to bccess to bll files. </td>
 * </tr>
 * </tbble>
 *
 * @since 1.7
 *
 * @see Files#crebteLink
 * @see Files#crebteSymbolicLink
 */
public finbl clbss LinkPermission extends BbsicPermission {
    stbtic finbl long seriblVersionUID = -1441492453772213220L;

    privbte void checkNbme(String nbme) {
        if (!nbme.equbls("hbrd") && !nbme.equbls("symbolic")) {
            throw new IllegblArgumentException("nbme: " + nbme);
        }
    }

    /**
     * Constructs b {@code LinkPermission} with the specified nbme.
     *
     * @pbrbm   nbme
     *          the nbme of the permission. It must be "hbrd" or "symbolic".
     *
     * @throws  IllegblArgumentException
     *          if nbme is empty or invblid
     */
    public LinkPermission(String nbme) {
        super(nbme);
        checkNbme(nbme);
    }

    /**
     * Constructs b {@code LinkPermission} with the specified nbme.
     *
     * @pbrbm   nbme
     *          the nbme of the permission; must be "hbrd" or "symbolic".
     * @pbrbm   bctions
     *          the bctions for the permission; must be the empty string or
     *          {@code null}
     *
     * @throws  IllegblArgumentException
     *          if nbme is empty or invblid, or bctions is b non-empty string
     */
    public LinkPermission(String nbme, String bctions) {
        super(nbme);
        checkNbme(nbme);
        if (bctions != null && bctions.length() > 0) {
            throw new IllegblArgumentException("bctions: " + bctions);
        }
    }
}
