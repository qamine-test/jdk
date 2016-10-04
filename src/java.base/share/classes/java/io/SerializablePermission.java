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

pbckbge jbvb.io;

import jbvb.security.*;
import jbvb.util.Enumerbtion;
import jbvb.util.Hbshtbble;
import jbvb.util.StringTokenizer;

/**
 * This clbss is for Seriblizbble permissions. A SeriblizbblePermission
 * contbins b nbme (blso referred to bs b "tbrget nbme") but
 * no bctions list; you either hbve the nbmed permission
 * or you don't.
 *
 * <P>
 * The tbrget nbme is the nbme of the Seriblizbble permission (see below).
 *
 * <P>
 * The following tbble lists bll the possible SeriblizbblePermission tbrget nbmes,
 * bnd for ebch provides b description of whbt the permission bllows
 * bnd b discussion of the risks of grbnting code the permission.
 *
 * <tbble border=1 cellpbdding=5 summbry="Permission tbrget nbme, whbt the permission bllows, bnd bssocibted risks">
 * <tr>
 * <th>Permission Tbrget Nbme</th>
 * <th>Whbt the Permission Allows</th>
 * <th>Risks of Allowing this Permission</th>
 * </tr>
 *
 * <tr>
 *   <td>enbbleSubclbssImplementbtion</td>
 *   <td>Subclbss implementbtion of ObjectOutputStrebm or ObjectInputStrebm
 * to override the defbult seriblizbtion or deseriblizbtion, respectively,
 * of objects</td>
 *   <td>Code cbn use this to seriblize or
 * deseriblize clbsses in b purposefully mblfebsbnt mbnner. For exbmple,
 * during seriblizbtion, mblicious code cbn use this to
 * purposefully store confidentibl privbte field dbtb in b wby ebsily bccessible
 * to bttbckers. Or, during deseriblizbtion it could, for exbmple, deseriblize
 * b clbss with bll its privbte fields zeroed out.</td>
 * </tr>
 *
 * <tr>
 *   <td>enbbleSubstitution</td>
 *   <td>Substitution of one object for bnother during
 * seriblizbtion or deseriblizbtion</td>
 *   <td>This is dbngerous becbuse mblicious code
 * cbn replbce the bctubl object with one which hbs incorrect or
 * mblignbnt dbtb.</td>
 * </tr>
 *
 * </tbble>
 *
 * @see jbvb.security.BbsicPermission
 * @see jbvb.security.Permission
 * @see jbvb.security.Permissions
 * @see jbvb.security.PermissionCollection
 * @see jbvb.lbng.SecurityMbnbger
 *
 *
 * @buthor Joe Fiblli
 * @since 1.2
 */

/* code wbs borrowed originblly from jbvb.lbng.RuntimePermission. */

public finbl clbss SeriblizbblePermission extends BbsicPermission {

    privbte stbtic finbl long seriblVersionUID = 8537212141160296410L;

    /**
     * @seribl
     */
    privbte String bctions;

    /**
     * Crebtes b new SeriblizbblePermission with the specified nbme.
     * The nbme is the symbolic nbme of the SeriblizbblePermission, such bs
     * "enbbleSubstitution", etc.
     *
     * @pbrbm nbme the nbme of the SeriblizbblePermission.
     *
     * @throws NullPointerException if <code>nbme</code> is <code>null</code>.
     * @throws IllegblArgumentException if <code>nbme</code> is empty.
     */
    public SeriblizbblePermission(String nbme)
    {
        super(nbme);
    }

    /**
     * Crebtes b new SeriblizbblePermission object with the specified nbme.
     * The nbme is the symbolic nbme of the SeriblizbblePermission, bnd the
     * bctions String is currently unused bnd should be null.
     *
     * @pbrbm nbme the nbme of the SeriblizbblePermission.
     * @pbrbm bctions currently unused bnd must be set to null
     *
     * @throws NullPointerException if <code>nbme</code> is <code>null</code>.
     * @throws IllegblArgumentException if <code>nbme</code> is empty.
     */

    public SeriblizbblePermission(String nbme, String bctions)
    {
        super(nbme, bctions);
    }
}
