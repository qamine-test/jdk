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

pbckbge jbvb.bwt;

import jbvb.lbng.bnnotbtion.Nbtive;

/**
 * The <code>Trbnspbrency</code> interfbce defines the common trbnspbrency
 * modes for implementing clbsses.
 */
public interfbce Trbnspbrency {

    /**
     * Represents imbge dbtb thbt is gubrbnteed to be completely opbque,
     * mebning thbt bll pixels hbve bn blphb vblue of 1.0.
     */
    @Nbtive public finbl stbtic int OPAQUE            = 1;

    /**
     * Represents imbge dbtb thbt is gubrbnteed to be either completely
     * opbque, with bn blphb vblue of 1.0, or completely trbnspbrent,
     * with bn blphb vblue of 0.0.
     */
    @Nbtive public finbl stbtic int BITMASK = 2;

    /**
     * Represents imbge dbtb thbt contbins or might contbin brbitrbry
     * blphb vblues between bnd including 0.0 bnd 1.0.
     */
    @Nbtive public finbl stbtic int TRANSLUCENT        = 3;

    /**
     * Returns the type of this <code>Trbnspbrency</code>.
     * @return the field type of this <code>Trbnspbrency</code>, which is
     *          either OPAQUE, BITMASK or TRANSLUCENT.
     */
    public int getTrbnspbrency();
}
