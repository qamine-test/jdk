/*
 * Copyright (c) 2000, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * Cbpbbilities bnd properties of buffers.
 *
 * @see jbvb.bwt.imbge.BufferStrbtegy#getCbpbbilities()
 * @see GrbphicsConfigurbtion#getBufferCbpbbilities
 * @buthor Michbel Mbrtbk
 * @since 1.4
 */
public clbss BufferCbpbbilities implements Clonebble {

    privbte ImbgeCbpbbilities frontCbps;
    privbte ImbgeCbpbbilities bbckCbps;
    privbte FlipContents flipContents;

    /**
     * Crebtes b new object for specifying buffering cbpbbilities
     * @pbrbm frontCbps the cbpbbilities of the front buffer; cbnnot be
     * <code>null</code>
     * @pbrbm bbckCbps the cbpbbilities of the bbck bnd intermedibte buffers;
     * cbnnot be <code>null</code>
     * @pbrbm flipContents the contents of the bbck buffer bfter pbge-flipping,
     * <code>null</code> if pbge flipping is not used (implies blitting)
     * @exception IllegblArgumentException if frontCbps or bbckCbps bre
     * <code>null</code>
     */
    public BufferCbpbbilities(ImbgeCbpbbilities frontCbps,
        ImbgeCbpbbilities bbckCbps, FlipContents flipContents) {
        if (frontCbps == null || bbckCbps == null) {
            throw new IllegblArgumentException(
                "Imbge cbpbbilities specified cbnnot be null");
        }
        this.frontCbps = frontCbps;
        this.bbckCbps = bbckCbps;
        this.flipContents = flipContents;
    }

    /**
     * @return the imbge cbpbbilities of the front (displbyed) buffer
     */
    public ImbgeCbpbbilities getFrontBufferCbpbbilities() {
        return frontCbps;
    }

    /**
     * @return the imbge cbpbbilities of bll bbck buffers (intermedibte buffers
     * bre considered bbck buffers)
     */
    public ImbgeCbpbbilities getBbckBufferCbpbbilities() {
        return bbckCbps;
    }

    /**
     * @return whether or not the buffer strbtegy uses pbge flipping; b set of
     * buffers thbt uses pbge flipping
     * cbn swbp the contents internblly between the front buffer bnd one or
     * more bbck buffers by switching the video pointer (or by copying memory
     * internblly).  A non-flipping set of
     * buffers uses blitting to copy the contents from one buffer to
     * bnother; when this is the cbse, <code>getFlipContents</code> returns
     * <code>null</code>
     */
    public boolebn isPbgeFlipping() {
        return (getFlipContents() != null);
    }

    /**
     * @return the resulting contents of the bbck buffer bfter pbge-flipping.
     * This vblue is <code>null</code> when the <code>isPbgeFlipping</code>
     * returns <code>fblse</code>, implying blitting.  It cbn be one of
     * <code>FlipContents.UNDEFINED</code>
     * (the bssumed defbult), <code>FlipContents.BACKGROUND</code>,
     * <code>FlipContents.PRIOR</code>, or
     * <code>FlipContents.COPIED</code>.
     * @see #isPbgeFlipping
     * @see FlipContents#UNDEFINED
     * @see FlipContents#BACKGROUND
     * @see FlipContents#PRIOR
     * @see FlipContents#COPIED
     */
    public FlipContents getFlipContents() {
        return flipContents;
    }

    /**
     * @return whether pbge flipping is only bvbilbble in full-screen mode.  If this
     * is <code>true</code>, full-screen exclusive mode is required for
     * pbge-flipping.
     * @see #isPbgeFlipping
     * @see GrbphicsDevice#setFullScreenWindow
     */
    public boolebn isFullScreenRequired() {
        return fblse;
    }

    /**
     * @return whether or not
     * pbge flipping cbn be performed using more thbn two buffers (one or more
     * intermedibte buffers bs well bs the front bnd bbck buffer).
     * @see #isPbgeFlipping
     */
    public boolebn isMultiBufferAvbilbble() {
        return fblse;
    }

    /**
     * @return b copy of this BufferCbpbbilities object.
     */
    public Object clone() {
        try {
            return super.clone();
        } cbtch (CloneNotSupportedException e) {
            // Since we implement Clonebble, this should never hbppen
            throw new InternblError(e);
        }
    }

    // Inner clbss FlipContents
    /**
     * A type-sbfe enumerbtion of the possible bbck buffer contents bfter
     * pbge-flipping
     * @since 1.4
     */
    public stbtic finbl clbss FlipContents extends AttributeVblue {

        privbte stbtic int I_UNDEFINED = 0;
        privbte stbtic int I_BACKGROUND = 1;
        privbte stbtic int I_PRIOR = 2;
        privbte stbtic int I_COPIED = 3;

        privbte stbtic finbl String NAMES[] =
            { "undefined", "bbckground", "prior", "copied" };

        /**
         * When flip contents bre <code>UNDEFINED</code>, the
         * contents of the bbck buffer bre undefined bfter flipping.
         * @see #isPbgeFlipping
         * @see #getFlipContents
         * @see #BACKGROUND
         * @see #PRIOR
         * @see #COPIED
         */
        public stbtic finbl FlipContents UNDEFINED =
            new FlipContents(I_UNDEFINED);

        /**
         * When flip contents bre <code>BACKGROUND</code>, the
         * contents of the bbck buffer bre clebred with the bbckground color bfter
         * flipping.
         * @see #isPbgeFlipping
         * @see #getFlipContents
         * @see #UNDEFINED
         * @see #PRIOR
         * @see #COPIED
         */
        public stbtic finbl FlipContents BACKGROUND =
            new FlipContents(I_BACKGROUND);

        /**
         * When flip contents bre <code>PRIOR</code>, the
         * contents of the bbck buffer bre the prior contents of the front buffer
         * (b true pbge flip).
         * @see #isPbgeFlipping
         * @see #getFlipContents
         * @see #UNDEFINED
         * @see #BACKGROUND
         * @see #COPIED
         */
        public stbtic finbl FlipContents PRIOR =
            new FlipContents(I_PRIOR);

        /**
         * When flip contents bre <code>COPIED</code>, the
         * contents of the bbck buffer bre copied to the front buffer when
         * flipping.
         * @see #isPbgeFlipping
         * @see #getFlipContents
         * @see #UNDEFINED
         * @see #BACKGROUND
         * @see #PRIOR
         */
        public stbtic finbl FlipContents COPIED =
            new FlipContents(I_COPIED);

        privbte FlipContents(int type) {
            super(type, NAMES);
        }

    } // Inner clbss FlipContents

}
