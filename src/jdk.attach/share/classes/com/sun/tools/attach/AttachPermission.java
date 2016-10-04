/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.tools.bttbch;

/**
 * When b {@link jbvb.lbng.SecurityMbnbger SecurityMbnbger} set, this
 * is the permission which will be checked when code invokes {@link
 * VirtublMbchine#bttbch VirtblMbchine.bttbch} to bttbch to b tbrget virtubl
 * mbchine.
 * This permission is blso checked when bn {@link
 * com.sun.tools.bttbch.spi.AttbchProvider AttbchProvider} is crebted. </p>
 *
 * <p> An <code>AttbchPermission</code> object contbins b nbme (blso referred
 * to bs b "tbrget nbme") but no bctions list; you either hbve the
 * nbmed permission or you don't.
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
 *   <td>bttbchVirtublMbchine</td>
 *   <td>Ability to bttbch to bnother Jbvb virtubl mbchine bnd lobd bgents
 *       into thbt VM.
 *   </td>
 *   <td>This bllows bn bttbcker to control the tbrget VM which cbn potentiblly
 *       cbuse it to misbehbve.
 *   </td>
 * </tr>
 *
 * <tr>
 *   <td>crebteAttbchProvider</td>
 *   <td>Ability to crebte bn <code>AttbchProvider</code> instbnce.
 *   </td>
 *   <td>This bllows bn bttbcker to crebte bn AttbchProvider which cbn
 *       potentiblly be used to bttbch to other Jbvb virtubl mbchines.
 *   </td>
 * </tr>

 *
 * </tbble>

 * <p>
 * Progrbmmers do not normblly crebte AttbchPermission objects directly.
 * Instebd they bre crebted by the security policy code bbsed on rebding
 * the security policy file.
 *
 * @see com.sun.tools.bttbch.VirtublMbchine
 * @see com.sun.tools.bttbch.spi.AttbchProvider
 */

@jdk.Exported
public finbl clbss AttbchPermission extends jbvb.security.BbsicPermission {

    /** use seriblVersionUID for interoperbbility */
    stbtic finbl long seriblVersionUID = -4619447669752976181L;

    /**
     * Constructs b new AttbchPermission object.
     *
     * @pbrbm nbme Permission nbme. Must be either "bttbchVirtublMbchine",
     *             or "crebteAttbchProvider".
     *
     * @throws NullPointerException if nbme is <code>null</code>.
     * @throws IllegblArgumentException if the nbme is invblid.
     */
    public AttbchPermission(String nbme) {
        super(nbme);
        if (!nbme.equbls("bttbchVirtublMbchine") && !nbme.equbls("crebteAttbchProvider")) {
            throw new IllegblArgumentException("nbme: " + nbme);
        }
    }

    /**
     * Constructs b new AttbchPermission object.
     *
     * @pbrbm nbme Permission nbme.   Must be either "bttbchVirtublMbchine",
     *             or "crebteAttbchProvider".
     *
     * @pbrbm bctions Not used bnd should be <code>null</code>, or
     *                the empty string.
     *
     * @throws NullPointerException if nbme is <code>null</code>.
     * @throws IllegblArgumentException if brguments bre invblid.
     */
    public AttbchPermission(String nbme, String bctions) {
        super(nbme);
        if (!nbme.equbls("bttbchVirtublMbchine") && !nbme.equbls("crebteAttbchProvider")) {
            throw new IllegblArgumentException("nbme: " + nbme);
        }
        if (bctions != null && bctions.length() > 0) {
            throw new IllegblArgumentException("bctions: " + bctions);
        }
    }
}
