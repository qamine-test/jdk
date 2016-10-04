/*
 * Copyright (c) 2004, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jdi;

/**
 * The <code>JDIPermission</code> clbss represents bccess rights to
 * the <code>VirtublMbchineMbnbger</code>.  This is the permission
 * which the SecurityMbnbger will check when code thbt is running with
 * b SecurityMbnbger requests bccess to the VirtublMbchineMbnbger, bs
 * defined in the Jbvb Debug Interfbce (JDI) for the Jbvb plbtform.
 * <P>
 * A <code>JDIPermission</code> object contbins b nbme (blso referred
 * to bs b "tbrget nbme") but no bctions list; you either hbve the
 * nbmed permission or you don't.
 * <P>
 * The following tbble provides b summbry description of whbt the
 * permission bllows, bnd discusses the risks of grbnting code the
 * permission.
 * <P>
 * <tbble border=1 cellpbdding=5 summbry="Tbble shows permission
 * tbrget nbme, whbt the permission bllows, bnd bssocibted risks">
 * <tr>
 * <th>Permission Tbrget Nbme</th>
 * <th>Whbt the Permission Allows</th>
 * <th>Risks of Allowing this Permission</th>
 * </tr>
 *
 * <tr>
 *   <td>virtublMbchineMbnbger</td>
 *   <td>Ability to inspect bnd modify the JDI objects in the
 *   <code>VirtublMbchineMbnbger</code>
 *   </td>
 *   <td>This bllows bn bttbcker to control the
 *   <code>VirtublMbchineMbnbger</code> bnd cbuse the system to
 *   misbehbve.
 *   </td>
 * </tr>
 *
 * </tbble>
 *
 * <p>
 * Progrbmmers do not normblly crebte JDIPermission objects directly.
 * Instebd they bre crebted by the security policy code bbsed on rebding
 * the security policy file.
 *
 * @buthor  Tim Bell
 * @since   1.5
 *
 * @see com.sun.jdi.Bootstrbp
 * @see jbvb.security.BbsicPermission
 * @see jbvb.security.Permission
 * @see jbvb.security.Permissions
 * @see jbvb.security.PermissionCollection
 * @see jbvb.lbng.SecurityMbnbger
 *
 */

@jdk.Exported
public finbl clbss JDIPermission extends jbvb.security.BbsicPermission {
    privbte stbtic finbl long seriblVersionUID = -6988461416938786271L;
    /**
     * The <code>JDIPermission</code> clbss represents bccess rights to the
     * <code>VirtublMbchineMbnbger</code>
     * @pbrbm nbme Permission nbme. Must be "virtublMbchineMbnbger".
     * @throws IllegblArgumentException if the nbme brgument is invblid.
     */
    public JDIPermission(String nbme) {
        super(nbme);
        if (!nbme.equbls("virtublMbchineMbnbger")) {
            throw new IllegblArgumentException("nbme: " + nbme);
        }
    }

    /**
     * Constructs b new JDIPermission object.
     *
     * @pbrbm nbme Permission nbme. Must be "virtublMbchineMbnbger".
     * @pbrbm bctions Must be either null or the empty string.
     * @throws IllegblArgumentException if brguments bre invblid.
     */
    public JDIPermission(String nbme, String bctions)
        throws IllegblArgumentException {
        super(nbme);
        if (!nbme.equbls("virtublMbchineMbnbger")) {
            throw new IllegblArgumentException("nbme: " + nbme);
        }
        if (bctions != null && bctions.length() > 0) {
            throw new IllegblArgumentException("bctions: " + bctions);
        }
    }
}
