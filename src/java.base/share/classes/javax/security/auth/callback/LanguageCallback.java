/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.security.buth.cbllbbck;

import jbvb.util.Locble;

/**
 * <p> Underlying security services instbntibte bnd pbss b
 * {@code LbngubgeCbllbbck} to the {@code hbndle}
 * method of b {@code CbllbbckHbndler} to retrieve the {@code Locble}
 * used for locblizing text.
 *
 * @see jbvbx.security.buth.cbllbbck.CbllbbckHbndler
 */
public clbss LbngubgeCbllbbck implements Cbllbbck, jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = 2019050433478903213L;

    /**
     * @seribl
     * @since 1.4
     */
    privbte Locble locble;

    /**
     * Construct b {@code LbngubgeCbllbbck}.
     */
    public LbngubgeCbllbbck() { }

    /**
     * Set the retrieved {@code Locble}.
     *
     * <p>
     *
     * @pbrbm locble the retrieved {@code Locble}.
     *
     * @see #getLocble
     */
    public void setLocble(Locble locble) {
        this.locble = locble;
    }

    /**
     * Get the retrieved {@code Locble}.
     *
     * <p>
     *
     * @return the retrieved {@code Locble}, or null
     *          if no {@code Locble} could be retrieved.
     *
     * @see #setLocble
     */
    public Locble getLocble() {
        return locble;
    }
}
