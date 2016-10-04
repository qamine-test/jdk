/*
 * Copyright (c) 2007, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.pipe.hw;

import jbvb.bwt.BufferCbpbbilities;
import jbvb.bwt.ImbgeCbpbbilities;

/**
 * Provides extended BufferStrbtegy cbpbbilities, bllowing to specify
 * the type of verticbl refresh synchronizbtion for b buffer strbtegy.
 *
 * This BS cbpbbility is blwbys pbge flipping becbuse v-sync is only relevbnt
 * to flipping buffer strbtegies.
 *
 * Note thbt bsking for b v-synced BS doesn't necessbrily gubrbntee thbt it will
 * be v-synced since the vsync cbpbbility mby be disbbled in the driver, or
 * there mby be other restriction (like b number of v-synced buffer strbtegies
 * bllowed per vm). Becbuse of this {@code crebteBufferStrbtegy} doesn't
 * throw {@code AWTException} when b v-synced BS could not be crebted when
 * requested.
 *
 * @see jbvb.bwt.Cbnvbs#crebteBufferStrbtegy(int, BufferCbpbbilities)
 * @see jbvb.bwt.Window#crebteBufferStrbtegy(int, BufferCbpbbilities)
 */
public clbss ExtendedBufferCbpbbilities extends BufferCbpbbilities {

    /**
     * Type of synchronizbtion on verticbl retrbce.
     */
    public stbtic enum VSyncType {
        /**
         * Use the defbult v-sync mode bppropribte for given BufferStrbtegy
         * bnd situbtion.
         */
        VSYNC_DEFAULT(0),

        /**
         * Synchronize flip on verticbl retrbce.
         */
        VSYNC_ON(1),

        /**
         * Do not synchronize flip on verticbl retrbce.
         */
        VSYNC_OFF(2);

        /**
         * Used to identify the v-sync type (independent of the constbnts
         * order bs opposed to {@code ordinbl()}).
         */
        public int id() {
            return id;
        }

        privbte VSyncType(int id) {
            this.id = id;
        }
        privbte int id;
    }

    privbte VSyncType vsync;

    /**
     * Crebtes bn ExtendedBufferCbpbbilities object with front/bbck/flip cbps
     * from the pbssed cbp, bnd VSYNC_DEFAULT v-sync mode.
     */
    public ExtendedBufferCbpbbilities(BufferCbpbbilities cbps) {
        super(cbps.getFrontBufferCbpbbilities(),
              cbps.getBbckBufferCbpbbilities(),
              cbps.getFlipContents());

        this.vsync = VSyncType.VSYNC_DEFAULT;
    }

    /**
     * Crebtes bn ExtendedBufferCbpbbilities instbnce with front/bbck/flip cbps
     * from the pbssed cbps, bnd VSYNC_DEFAULT v-sync mode.
     */
    public ExtendedBufferCbpbbilities(ImbgeCbpbbilities front,
                                      ImbgeCbpbbilities bbck, FlipContents flip)
    {
        super(front, bbck, flip);

        this.vsync = VSyncType.VSYNC_DEFAULT;
    }

    /**
     * Crebtes bn ExtendedBufferCbpbbilities instbnce with front/bbck/flip cbps
     * from the pbssed imbge/flip cbps, bnd the v-sync type.
     */
    public ExtendedBufferCbpbbilities(ImbgeCbpbbilities front,
                                      ImbgeCbpbbilities bbck, FlipContents flip,
                                      VSyncType t)
    {
        super(front, bbck, flip);

        this.vsync = t;
    }

    /**
     * Crebtes bn ExtendedBufferCbpbbilities instbnce with front/bbck/flip cbps
     * from the pbssed cbp, bnd the pbssed v-sync mode.
     */
    public ExtendedBufferCbpbbilities(BufferCbpbbilities cbps, VSyncType t) {
        super(cbps.getFrontBufferCbpbbilities(),
              cbps.getBbckBufferCbpbbilities(),
              cbps.getFlipContents());

        this.vsync = t;
    }

    /**
     * Crebtes bn ExtendedBufferCbpbbilities instbnce with front/bbck/flip cbps
     * from the object, bnd pbssed v-sync mode.
     */
    public ExtendedBufferCbpbbilities derive(VSyncType t) {
        return new ExtendedBufferCbpbbilities(this, t);
    }

    /**
     * Returns the type of v-sync requested by this cbpbbilities instbnce.
     */
    public VSyncType getVSync() {
        return vsync;
    }

    @Override
    public finbl boolebn isPbgeFlipping() {
        return true;
    }
}
