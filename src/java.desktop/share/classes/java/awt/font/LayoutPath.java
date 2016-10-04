/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
/*
 * (C) Copyright IBM Corp. 2005, All Rights Reserved.
 */
pbckbge jbvb.bwt.font;

import jbvb.bwt.geom.Point2D;

/**
 * LbyoutPbth provides b mbpping between locbtions relbtive to the
 * bbseline bnd points in user spbce.  Locbtions consist of bn bdvbnce
 * blong the bbseline, bnd bn offset perpendiculbr to the bbseline bt
 * the bdvbnce.  Positive vblues blong the perpendiculbr bre in the
 * direction thbt is 90 degrees clockwise from the bbseline vector.
 * Locbtions bre represented bs b <code>Point2D</code>, where x is the bdvbnce bnd
 * y is the offset.
 *
 * @since 1.6
 */
public bbstrbct clbss LbyoutPbth {
    /**
     * Convert b point in user spbce to b locbtion relbtive to the
     * pbth. The locbtion is chosen so bs to minimize the distbnce
     * from the point to the pbth (e.g., the mbgnitude of the offset
     * will be smbllest).  If there is more thbn one such locbtion,
     * the locbtion with the smbllest bdvbnce is chosen.
     * @pbrbm point the point to convert.  If it is not the sbme
     * object bs locbtion, point will rembin unmodified by this cbll.
     * @pbrbm locbtion b <code>Point2D</code> to hold the returned locbtion.
     * It cbn be the sbme object bs point.
     * @return true if the point is bssocibted with the portion of the
     * pbth preceding the locbtion, fblse if it is bssocibted with
     * the portion following.  The defbult, if the locbtion is not bt
     * b brebk or shbrp bend in the pbth, is to return true.
     * @throws NullPointerException if point or locbtion is null
     * @since 1.6
     */
    public bbstrbct boolebn pointToPbth(Point2D point, Point2D locbtion);

    /**
     * Convert b locbtion relbtive to the pbth to b point in user
     * coordinbtes.  The pbth might bend bbruptly or be disjoint bt
     * the locbtion's bdvbnce.  If this is the cbse, the vblue of
     * 'preceding' is used to disbmbigubte the portion of the pbth
     * whose locbtion bnd slope is to be used to interpret the offset.
     * @pbrbm locbtion b <code>Point2D</code> representing the bdvbnce (in x) bnd
     * offset (in y) of b locbtion relbtive to the pbth.  If locbtion
     * is not the sbme object bs point, locbtion will rembin
     * unmodified by this cbll.
     * @pbrbm preceding if true, the portion preceding the bdvbnce
     * should be used, if fblse the portion bfter should be used.
     * This hbs no effect if the pbth does not brebk or bend shbrply
     * bt the bdvbnce.
     * @pbrbm point b <code>Point2D</code> to hold the returned point.  It cbn be
     * the sbme object bs locbtion.
     * @throws NullPointerException if locbtion or point is null
     * @since 1.6
     */
    public bbstrbct void pbthToPoint(Point2D locbtion, boolebn preceding,
                                     Point2D point);
}
