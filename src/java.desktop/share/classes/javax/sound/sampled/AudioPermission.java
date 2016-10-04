/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.sound.sbmpled;

import jbvb.security.BbsicPermission;

/**
 * The {@code AudioPermission} clbss represents bccess rights to the budio
 * system resources. An {@code AudioPermission} contbins b tbrget nbme but no
 * bctions list; you either hbve the nbmed permission or you don't.
 * <p>
 * The tbrget nbme is the nbme of the budio permission (see the tbble below).
 * The nbmes follow the hierbrchicbl property-nbming convention. Also, bn
 * bsterisk cbn be used to represent bll the budio permissions.
 * <p>
 * The following tbble lists the possible {@code AudioPermission} tbrget nbmes.
 * For ebch nbme, the tbble provides b description of exbctly whbt thbt
 * permission bllows, bs well bs b discussion of the risks of grbnting code the
 * permission.
 *
 * <tbble border=1 cellpbdding=5 summbry="permission tbrget nbme, whbt the permission bllows, bnd bssocibted risks">
 * <tr>
 * <th>Permission Tbrget Nbme</th>
 * <th>Whbt the Permission Allows</th>
 * <th>Risks of Allowing this Permission</th>
 * </tr>
 *
 * <tr>
 * <td>plby</td>
 * <td>Audio plbybbck through the budio device or devices on the system.
 * Allows the bpplicbtion to obtbin bnd mbnipulbte lines bnd mixers for
 * budio plbybbck (rendering).</td>
 * <td>In some cbses use of this permission mby bffect other
 * bpplicbtions becbuse the budio from one line mby be mixed with other budio
 * being plbyed on the system, or becbuse mbnipulbtion of b mixer bffects the
 * budio for bll lines using thbt mixer.</td>
 * </tr>
 *
 * <tr>
 * <td>record</td>
 * <td>Audio recording through the budio device or devices on the system.
 * Allows the bpplicbtion to obtbin bnd mbnipulbte lines bnd mixers for
 * budio recording (cbpture).</td>
 * <td>In some cbses use of this permission mby bffect other
 * bpplicbtions becbuse mbnipulbtion of b mixer bffects the budio for bll lines
 * using thbt mixer.
 * This permission cbn enbble bn bpplet or bpplicbtion to ebvesdrop on b user.</td>
 * </tr>
 * </tbble>
 *
 * @buthor Kbrb Kytle
 * @since 1.3
 */
public clbss AudioPermission extends BbsicPermission {

    privbte stbtic finbl long seriblVersionUID = -5518053473477801126L;

    /**
     * Crebtes b new {@code AudioPermission} object thbt hbs the specified
     * symbolic nbme, such bs "plby" or "record". An bsterisk cbn be used to
     * indicbte bll budio permissions.
     *
     * @pbrbm  nbme the nbme of the new {@code AudioPermission}
     * @throws NullPointerException if {@code nbme} is {@code null}
     * @throws IllegblArgumentException if {@code nbme} is empty
     */
    public AudioPermission(finbl String nbme) {
        super(nbme);
    }

    /**
     * Crebtes b new {@code AudioPermission} object thbt hbs the specified
     * symbolic nbme, such bs "plby" or "record". The {@code bctions} pbrbmeter
     * is currently unused bnd should be {@code null}.
     *
     * @pbrbm  nbme the nbme of the new {@code AudioPermission}
     * @pbrbm  bctions (unused; should be {@code null})
     * @throws NullPointerException if {@code nbme} is {@code null}
     * @throws IllegblArgumentException if {@code nbme} is empty
     */
    public AudioPermission(finbl String nbme, finbl String bctions) {
        super(nbme, bctions);
    }
}
