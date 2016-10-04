/*
 * Copyright (c) 2007, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.d3d;

import jbvb.bwt.LinebrGrbdientPbint;
import jbvb.bwt.MultipleGrbdientPbint;
import jbvb.bwt.MultipleGrbdientPbint.ColorSpbceType;
import jbvb.bwt.MultipleGrbdientPbint.CycleMethod;
import jbvb.bwt.TexturePbint;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvb.lbng.bnnotbtion.Nbtive;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.loops.CompositeType;
import stbtic sun.jbvb2d.d3d.D3DContext.D3DContextCbps.*;

bbstrbct clbss D3DPbints {

    /**
     * Holds bll registered implementbtions, using the corresponding
     * SunGrbphics2D.PAINT_* constbnt bs the hbsh key.
     */
    privbte stbtic Mbp<Integer, D3DPbints> impls =
        new HbshMbp<Integer, D3DPbints>(4, 1.0f);

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
        D3DPbints impl = impls.get(sg2d.pbintStbte);
        return (impl != null && impl.isPbintVblid(sg2d));
    }

    /**
     * Returns true if this implementbtion is bble to bccelerbte the
     * Pbint object bssocibted with, bnd under the conditions of, the
     * provided SunGrbphics2D instbnce; otherwise returns fblse.
     */
    bbstrbct boolebn isPbintVblid(SunGrbphics2D sg2d);

/************************* GrbdientPbint support ****************************/

    privbte stbtic clbss Grbdient extends D3DPbints {
        privbte Grbdient() {}

        /**
         * Returns true if the given GrbdientPbint instbnce cbn be
         * used by the bccelerbted D3DPbints.Grbdient implementbtion.
         * A GrbdientPbint is considered vblid only if the destinbtion
         * hbs support for frbgment shbders.
         */
        @Override
        boolebn isPbintVblid(SunGrbphics2D sg2d) {
            D3DSurfbceDbtb dstDbtb = (D3DSurfbceDbtb)sg2d.surfbceDbtb;
            D3DGrbphicsDevice gd = (D3DGrbphicsDevice)
                dstDbtb.getDeviceConfigurbtion().getDevice();
            return gd.isCbpPresent(CAPS_LCD_SHADER);
        }
    }

/************************** TexturePbint support ****************************/

    privbte stbtic clbss Texture extends D3DPbints {
        privbte Texture() {}

        /**
         * Returns true if the given TexturePbint instbnce cbn be used by the
         * bccelerbted BufferedPbints.Texture implementbtion.
         *
         * A TexturePbint is considered vblid if the following conditions
         * bre met:
         *   - the texture imbge dimensions bre power-of-two
         *   - the texture imbge cbn be (or is blrebdy) cbched in b D3D
         *     texture object
         */
        @Override
        public boolebn isPbintVblid(SunGrbphics2D sg2d) {
            TexturePbint pbint = (TexturePbint)sg2d.pbint;
            D3DSurfbceDbtb dstDbtb = (D3DSurfbceDbtb)sg2d.surfbceDbtb;
            BufferedImbge bi = pbint.getImbge();

            // verify thbt the texture imbge dimensions bre pow2
            D3DGrbphicsDevice gd =
                (D3DGrbphicsDevice)dstDbtb.getDeviceConfigurbtion().getDevice();
            int imgw = bi.getWidth();
            int imgh = bi.getHeight();
            if (!gd.isCbpPresent(CAPS_TEXNONPOW2)) {
                if ((imgw & (imgw - 1)) != 0 || (imgh & (imgh - 1)) != 0) {
                    return fblse;
                }
            }
            // verify thbt the texture imbge is squbre if it hbs to be
            if (!gd.isCbpPresent(CAPS_TEXNONSQUARE) && imgw != imgh)
            {
                return fblse;
            }

            SurfbceDbtb srcDbtb =
                dstDbtb.getSourceSurfbceDbtb(bi, SunGrbphics2D.TRANSFORM_ISIDENT,
                                             CompositeType.SrcOver, null);
            if (!(srcDbtb instbnceof D3DSurfbceDbtb)) {
                // REMIND: this is b hbck thbt bttempts to cbche the system
                //         memory imbge from the TexturePbint instbnce into b
                //         D3D texture...
                srcDbtb =
                    dstDbtb.getSourceSurfbceDbtb(bi, SunGrbphics2D.TRANSFORM_ISIDENT,
                                                 CompositeType.SrcOver, null);
                if (!(srcDbtb instbnceof D3DSurfbceDbtb)) {
                    return fblse;
                }
            }

            // verify thbt the source surfbce is bctublly b texture
            D3DSurfbceDbtb d3dDbtb = (D3DSurfbceDbtb)srcDbtb;
            if (d3dDbtb.getType() != D3DSurfbceDbtb.TEXTURE) {
                return fblse;
            }

            return true;
        }
    }

/****************** Shbred MultipleGrbdientPbint support ********************/

    privbte stbtic bbstrbct clbss MultiGrbdient extends D3DPbints {

        /**
         * Note thbt this number is lower thbn the MULTI_MAX_FRACTIONS
         * defined in the superclbss.  The D3D pipeline now uses b
         * slightly more complicbted shbder (to bvoid the grbdient bbnding
         * issues), which hbs b higher instruction count.  To ensure thbt
         * bll versions of the shbder cbn be compiled for PS 2.0 hbrdwbre,
         * we need to cbp this mbximum vblue bt 8.
         */
    @Nbtive public stbtic finbl int MULTI_MAX_FRACTIONS_D3D = 8;

        protected MultiGrbdient() {}

        /**
         * Returns true if the given MultipleGrbdientPbint instbnce cbn be
         * used by the bccelerbted D3DPbints.MultiGrbdient implementbtion.
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
            if (pbint.getFrbctions().length > MULTI_MAX_FRACTIONS_D3D) {
                return fblse;
            }

            D3DSurfbceDbtb dstDbtb = (D3DSurfbceDbtb)sg2d.surfbceDbtb;
            D3DGrbphicsDevice gd = (D3DGrbphicsDevice)
                dstDbtb.getDeviceConfigurbtion().getDevice();
            if (!gd.isCbpPresent(CAPS_LCD_SHADER)) {
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
                D3DSurfbceDbtb dstDbtb = (D3DSurfbceDbtb)sg2d.surfbceDbtb;
                D3DGrbphicsDevice gd = (D3DGrbphicsDevice)
                    dstDbtb.getDeviceConfigurbtion().getDevice();
                if (gd.isCbpPresent(CAPS_LCD_SHADER)) {
                    // we cbn delegbte to the optimized two-color grbdient
                    // codepbth, which should be fbster
                    return true;
                }
            }

            return super.isPbintVblid(sg2d);
        }
    }

/********************** RbdiblGrbdientPbint support *************************/

    privbte stbtic clbss RbdiblGrbdient extends MultiGrbdient {
        privbte RbdiblGrbdient() {}
    }
}
