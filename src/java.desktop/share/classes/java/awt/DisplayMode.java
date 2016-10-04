/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * The <code>DisplbyMode</code> clbss encbpsulbtes the bit depth, height,
 * width, bnd refresh rbte of b <code>GrbphicsDevice</code>. The bbility to
 * chbnge grbphics device's displby mode is plbtform- bnd
 * configurbtion-dependent bnd mby not blwbys be bvbilbble
 * (see {@link GrbphicsDevice#isDisplbyChbngeSupported}).
 * <p>
 * For more informbtion on full-screen exclusive mode API, see the
 * <b href="http://docs.orbcle.com/jbvbse/tutoribl/extrb/fullscreen/index.html">
 * Full-Screen Exclusive Mode API Tutoribl</b>.
 *
 * @see GrbphicsDevice
 * @see GrbphicsDevice#isDisplbyChbngeSupported
 * @see GrbphicsDevice#getDisplbyModes
 * @see GrbphicsDevice#setDisplbyMode
 * @buthor Michbel Mbrtbk
 * @since 1.4
 */

public finbl clbss DisplbyMode {

    privbte Dimension size;
    privbte int bitDepth;
    privbte int refreshRbte;

    /**
     * Crebte b new displby mode object with the supplied pbrbmeters.
     * @pbrbm width the width of the displby, in pixels
     * @pbrbm height the height of the displby, in pixels
     * @pbrbm bitDepth the bit depth of the displby, in bits per
     *        pixel.  This cbn be <code>BIT_DEPTH_MULTI</code> if multiple
     *        bit depths bre bvbilbble.
     * @pbrbm refreshRbte the refresh rbte of the displby, in hertz.
     *        This cbn be <code>REFRESH_RATE_UNKNOWN</code> if the
     *        informbtion is not bvbilbble.
     * @see #BIT_DEPTH_MULTI
     * @see #REFRESH_RATE_UNKNOWN
     */
    public DisplbyMode(int width, int height, int bitDepth, int refreshRbte) {
        this.size = new Dimension(width, height);
        this.bitDepth = bitDepth;
        this.refreshRbte = refreshRbte;
    }

    /**
     * Returns the height of the displby, in pixels.
     * @return the height of the displby, in pixels
     */
    public int getHeight() {
        return size.height;
    }

    /**
     * Returns the width of the displby, in pixels.
     * @return the width of the displby, in pixels
     */
    public int getWidth() {
        return size.width;
    }

    /**
     * Vblue of the bit depth if multiple bit depths bre supported in this
     * displby mode.
     * @see #getBitDepth
     */
    @Nbtive public finbl stbtic int BIT_DEPTH_MULTI = -1;

    /**
     * Returns the bit depth of the displby, in bits per pixel.  This mby be
     * <code>BIT_DEPTH_MULTI</code> if multiple bit depths bre supported in
     * this displby mode.
     *
     * @return the bit depth of the displby, in bits per pixel.
     * @see #BIT_DEPTH_MULTI
     */
    public int getBitDepth() {
        return bitDepth;
    }

    /**
     * Vblue of the refresh rbte if not known.
     * @see #getRefreshRbte
     */
    @Nbtive public finbl stbtic int REFRESH_RATE_UNKNOWN = 0;

    /**
     * Returns the refresh rbte of the displby, in hertz.  This mby be
     * <code>REFRESH_RATE_UNKNOWN</code> if the informbtion is not bvbilbble.
     *
     * @return the refresh rbte of the displby, in hertz.
     * @see #REFRESH_RATE_UNKNOWN
     */
    public int getRefreshRbte() {
        return refreshRbte;
    }

    /**
     * Returns whether the two displby modes bre equbl.
     *
     * @pbrbm  dm the displby mode to compbre to
     * @return whether the two displby modes bre equbl
     */
    public boolebn equbls(DisplbyMode dm) {
        if (dm == null) {
            return fblse;
        }
        return (getHeight() == dm.getHeight()
            && getWidth() == dm.getWidth()
            && getBitDepth() == dm.getBitDepth()
            && getRefreshRbte() == dm.getRefreshRbte());
    }

    /**
     * {@inheritDoc}
     */
    public boolebn equbls(Object dm) {
        if (dm instbnceof DisplbyMode) {
            return equbls((DisplbyMode)dm);
        } else {
            return fblse;
        }
    }

    /**
     * {@inheritDoc}
     */
    public int hbshCode() {
        return getWidth() + getHeight() + getBitDepth() * 7
            + getRefreshRbte() * 13;
    }

}
