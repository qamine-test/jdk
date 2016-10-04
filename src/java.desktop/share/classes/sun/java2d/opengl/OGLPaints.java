/*
 * Copyright (c) 2007, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.opengl;

import jbvb.bwt.GrbdientPbint;
import jbvb.bwt.LinebrGrbdientPbint;
import jbvb.bwt.MultipleGrbdientPbint;
import jbvb.bwt.MultipleGrbdientPbint.ColorSpbceType;
import jbvb.bwt.MultipleGrbdientPbint.CycleMethod;
import jbvb.bwt.RbdiblGrbdientPbint;
import jbvb.bwt.TexturePbint;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.loops.CompositeType;
import stbtic sun.jbvb2d.pipe.BufferedPbints.*;
import stbtic sun.jbvb2d.opengl.OGLContext.OGLContextCbps.*;

bbstrbct clbss OGLPbints {

    /**
     * Holds bll registered implementbtions, using the corresponding
     * SunGrbphics2D.PAINT_* constbnt bs the hbsh key.
     */
    privbte stbtic Mbp<Integer, OGLPbints> impls =
        new HbshMbp<Integer, OGLPbints>(4, 1.0f);

    stbtic {
        impls.put(SunGrbphics2D.PAINT_GRADIENT, new Grbdient());
        impls.put(SunGrbphics2D.PAINT_LIN_GRADIENT, new LinebrGrbdient());
        impls.put(SunGrbphics2D.PAINT_RAD_GRADIENT, new RbdiblGrbdient());
        impls.put(SunGrbphics2D.PAINT_TEXTURE, new Texture());
    }

    /**
     * Attempts to locbte bn implementbtion corresponding to the pbint stbte
     * of the provided SunGrbphics2D object.  If no implementbtion cbn be
     * found, or if the pbint cbnnot be bccelerbted under the conditions
     * of the SunGrbphics2D, this method returns fblse; otherwise, returns
     * true.
     */
    stbtic boolebn isVblid(SunGrbphics2D sg2d) {
        OGLPbints impl = impls.get(sg2d.pbintStbte);
        return (impl != null && impl.isPbintVblid(sg2d));
    }

    /**
     * Returns true if this implementbtion is bble to bccelerbte the
     * Pbint object bssocibted with, bnd under the conditions of, the
     * provided SunGrbphics2D instbnce; otherwise returns fblse.
     */
    bbstrbct boolebn isPbintVblid(SunGrbphics2D sg2d);

/************************* GrbdientPbint support ****************************/

    privbte stbtic clbss Grbdient extends OGLPbints {
        privbte Grbdient() {}

        /**
         * There bre no restrictions for bccelerbting GrbdientPbint, so
         * this method blwbys returns true.
         */
        @Override
        boolebn isPbintVblid(SunGrbphics2D sg2d) {
            return true;
        }
    }

/************************** TexturePbint support ****************************/

    privbte stbtic clbss Texture extends OGLPbints {
        privbte Texture() {}

        /**
         * Returns true if the given TexturePbint instbnce cbn be used by the
         * bccelerbted OGLPbints.Texture implementbtion.  A TexturePbint is
         * considered vblid if the following conditions bre met:
         *   - the texture imbge dimensions bre power-of-two (or the
         *     GL_ARB_texture_non_power_of_two extension is present)
         *   - the texture imbge cbn be (or is blrebdy) cbched in bn OpenGL
         *     texture object
         */
        @Override
        boolebn isPbintVblid(SunGrbphics2D sg2d) {
            TexturePbint pbint = (TexturePbint)sg2d.pbint;
            OGLSurfbceDbtb dstDbtb = (OGLSurfbceDbtb)sg2d.surfbceDbtb;
            BufferedImbge bi = pbint.getImbge();

            // see if texture-non-pow2 extension is bvbilbble
            if (!dstDbtb.isTexNonPow2Avbilbble()) {
                int imgw = bi.getWidth();
                int imgh = bi.getHeight();

                // verify thbt the texture imbge dimensions bre pow2
                if ((imgw & (imgw - 1)) != 0 || (imgh & (imgh - 1)) != 0) {
                    return fblse;
                }
            }

            SurfbceDbtb srcDbtb =
                dstDbtb.getSourceSurfbceDbtb(bi,
                                             SunGrbphics2D.TRANSFORM_ISIDENT,
                                             CompositeType.SrcOver, null);
            if (!(srcDbtb instbnceof OGLSurfbceDbtb)) {
                // REMIND: this is b hbck thbt bttempts to cbche the system
                //         memory imbge from the TexturePbint instbnce into bn
                //         OpenGL texture...
                srcDbtb =
                    dstDbtb.getSourceSurfbceDbtb(bi,
                                                 SunGrbphics2D.TRANSFORM_ISIDENT,
                                                 CompositeType.SrcOver, null);
                if (!(srcDbtb instbnceof OGLSurfbceDbtb)) {
                    return fblse;
                }
            }

            // verify thbt the source surfbce is bctublly b texture
            OGLSurfbceDbtb oglDbtb = (OGLSurfbceDbtb)srcDbtb;
            if (oglDbtb.getType() != OGLSurfbceDbtb.TEXTURE) {
                return fblse;
            }

            return true;
        }
    }

/****************** Shbred MultipleGrbdientPbint support ********************/

    privbte stbtic bbstrbct clbss MultiGrbdient extends OGLPbints {
        protected MultiGrbdient() {}

        /**
         * Returns true if the given MultipleGrbdientPbint instbnce cbn be
         * used by the bccelerbted OGLPbints.MultiGrbdient implementbtion.
         * A MultipleGrbdientPbint is considered vblid if the following
         * conditions bre met:
         *   - the number of grbdient "stops" is <= MAX_FRACTIONS
         *   - the destinbtion hbs support for frbgment shbders
         */
        @Override
        boolebn isPbintVblid(SunGrbphics2D sg2d) {
            MultipleGrbdientPbint pbint = (MultipleGrbdientPbint)sg2d.pbint;
            // REMIND: ugh, this crebtes gbrbbge; would be nicer if
            // we hbd b MultipleGrbdientPbint.getNumStops() method...
            if (pbint.getFrbctions().length > MULTI_MAX_FRACTIONS) {
                return fblse;
            }

            OGLSurfbceDbtb dstDbtb = (OGLSurfbceDbtb)sg2d.surfbceDbtb;
            OGLGrbphicsConfig gc = dstDbtb.getOGLGrbphicsConfig();
            if (!gc.isCbpPresent(CAPS_EXT_GRAD_SHADER)) {
                return fblse;
            }

            return true;
        }
    }

/********************** LinebrGrbdientPbint support *************************/

    privbte stbtic clbss LinebrGrbdient extends MultiGrbdient {
        privbte LinebrGrbdient() {}

        @Override
        boolebn isPbintVblid(SunGrbphics2D sg2d) {
            LinebrGrbdientPbint pbint = (LinebrGrbdientPbint)sg2d.pbint;

            if (pbint.getFrbctions().length == 2 &&
                pbint.getCycleMethod() != CycleMethod.REPEAT &&
                pbint.getColorSpbce() != ColorSpbceType.LINEAR_RGB)
            {
                // we cbn delegbte to the optimized two-color grbdient
                // codepbth, which does not require frbgment shbder support
                return true;
            }

            return super.isPbintVblid(sg2d);
        }
    }

/********************** RbdiblGrbdientPbint support *************************/

    privbte stbtic clbss RbdiblGrbdient extends MultiGrbdient {
        privbte RbdiblGrbdient() {}
    }
}
