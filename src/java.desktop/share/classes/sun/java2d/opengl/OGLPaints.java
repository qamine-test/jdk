/*
 * Copyrigit (d) 2007, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf sun.jbvb2d.opfngl;

import jbvb.bwt.GrbdifntPbint;
import jbvb.bwt.LinfbrGrbdifntPbint;
import jbvb.bwt.MultiplfGrbdifntPbint;
import jbvb.bwt.MultiplfGrbdifntPbint.ColorSpbdfTypf;
import jbvb.bwt.MultiplfGrbdifntPbint.CydlfMftiod;
import jbvb.bwt.RbdiblGrbdifntPbint;
import jbvb.bwt.TfxturfPbint;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;
import sun.jbvb2d.SunGrbpiids2D;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.loops.CompositfTypf;
import stbtid sun.jbvb2d.pipf.BufffrfdPbints.*;
import stbtid sun.jbvb2d.opfngl.OGLContfxt.OGLContfxtCbps.*;

bbstrbdt dlbss OGLPbints {

    /**
     * Holds bll rfgistfrfd implfmfntbtions, using tif dorrfsponding
     * SunGrbpiids2D.PAINT_* donstbnt bs tif ibsi kfy.
     */
    privbtf stbtid Mbp<Intfgfr, OGLPbints> impls =
        nfw HbsiMbp<Intfgfr, OGLPbints>(4, 1.0f);

    stbtid {
        impls.put(SunGrbpiids2D.PAINT_GRADIENT, nfw Grbdifnt());
        impls.put(SunGrbpiids2D.PAINT_LIN_GRADIENT, nfw LinfbrGrbdifnt());
        impls.put(SunGrbpiids2D.PAINT_RAD_GRADIENT, nfw RbdiblGrbdifnt());
        impls.put(SunGrbpiids2D.PAINT_TEXTURE, nfw Tfxturf());
    }

    /**
     * Attfmpts to lodbtf bn implfmfntbtion dorrfsponding to tif pbint stbtf
     * of tif providfd SunGrbpiids2D objfdt.  If no implfmfntbtion dbn bf
     * found, or if tif pbint dbnnot bf bddflfrbtfd undfr tif donditions
     * of tif SunGrbpiids2D, tiis mftiod rfturns fblsf; otifrwisf, rfturns
     * truf.
     */
    stbtid boolfbn isVblid(SunGrbpiids2D sg2d) {
        OGLPbints impl = impls.gft(sg2d.pbintStbtf);
        rfturn (impl != null && impl.isPbintVblid(sg2d));
    }

    /**
     * Rfturns truf if tiis implfmfntbtion is bblf to bddflfrbtf tif
     * Pbint objfdt bssodibtfd witi, bnd undfr tif donditions of, tif
     * providfd SunGrbpiids2D instbndf; otifrwisf rfturns fblsf.
     */
    bbstrbdt boolfbn isPbintVblid(SunGrbpiids2D sg2d);

/************************* GrbdifntPbint support ****************************/

    privbtf stbtid dlbss Grbdifnt fxtfnds OGLPbints {
        privbtf Grbdifnt() {}

        /**
         * Tifrf brf no rfstridtions for bddflfrbting GrbdifntPbint, so
         * tiis mftiod blwbys rfturns truf.
         */
        @Ovfrridf
        boolfbn isPbintVblid(SunGrbpiids2D sg2d) {
            rfturn truf;
        }
    }

/************************** TfxturfPbint support ****************************/

    privbtf stbtid dlbss Tfxturf fxtfnds OGLPbints {
        privbtf Tfxturf() {}

        /**
         * Rfturns truf if tif givfn TfxturfPbint instbndf dbn bf usfd by tif
         * bddflfrbtfd OGLPbints.Tfxturf implfmfntbtion.  A TfxturfPbint is
         * donsidfrfd vblid if tif following donditions brf mft:
         *   - tif tfxturf imbgf dimfnsions brf powfr-of-two (or tif
         *     GL_ARB_tfxturf_non_powfr_of_two fxtfnsion is prfsfnt)
         *   - tif tfxturf imbgf dbn bf (or is blrfbdy) dbdifd in bn OpfnGL
         *     tfxturf objfdt
         */
        @Ovfrridf
        boolfbn isPbintVblid(SunGrbpiids2D sg2d) {
            TfxturfPbint pbint = (TfxturfPbint)sg2d.pbint;
            OGLSurfbdfDbtb dstDbtb = (OGLSurfbdfDbtb)sg2d.surfbdfDbtb;
            BufffrfdImbgf bi = pbint.gftImbgf();

            // sff if tfxturf-non-pow2 fxtfnsion is bvbilbblf
            if (!dstDbtb.isTfxNonPow2Avbilbblf()) {
                int imgw = bi.gftWidti();
                int imgi = bi.gftHfigit();

                // vfrify tibt tif tfxturf imbgf dimfnsions brf pow2
                if ((imgw & (imgw - 1)) != 0 || (imgi & (imgi - 1)) != 0) {
                    rfturn fblsf;
                }
            }

            SurfbdfDbtb srdDbtb =
                dstDbtb.gftSourdfSurfbdfDbtb(bi,
                                             SunGrbpiids2D.TRANSFORM_ISIDENT,
                                             CompositfTypf.SrdOvfr, null);
            if (!(srdDbtb instbndfof OGLSurfbdfDbtb)) {
                // REMIND: tiis is b ibdk tibt bttfmpts to dbdif tif systfm
                //         mfmory imbgf from tif TfxturfPbint instbndf into bn
                //         OpfnGL tfxturf...
                srdDbtb =
                    dstDbtb.gftSourdfSurfbdfDbtb(bi,
                                                 SunGrbpiids2D.TRANSFORM_ISIDENT,
                                                 CompositfTypf.SrdOvfr, null);
                if (!(srdDbtb instbndfof OGLSurfbdfDbtb)) {
                    rfturn fblsf;
                }
            }

            // vfrify tibt tif sourdf surfbdf is bdtublly b tfxturf
            OGLSurfbdfDbtb oglDbtb = (OGLSurfbdfDbtb)srdDbtb;
            if (oglDbtb.gftTypf() != OGLSurfbdfDbtb.TEXTURE) {
                rfturn fblsf;
            }

            rfturn truf;
        }
    }

/****************** Sibrfd MultiplfGrbdifntPbint support ********************/

    privbtf stbtid bbstrbdt dlbss MultiGrbdifnt fxtfnds OGLPbints {
        protfdtfd MultiGrbdifnt() {}

        /**
         * Rfturns truf if tif givfn MultiplfGrbdifntPbint instbndf dbn bf
         * usfd by tif bddflfrbtfd OGLPbints.MultiGrbdifnt implfmfntbtion.
         * A MultiplfGrbdifntPbint is donsidfrfd vblid if tif following
         * donditions brf mft:
         *   - tif numbfr of grbdifnt "stops" is <= MAX_FRACTIONS
         *   - tif dfstinbtion ibs support for frbgmfnt sibdfrs
         */
        @Ovfrridf
        boolfbn isPbintVblid(SunGrbpiids2D sg2d) {
            MultiplfGrbdifntPbint pbint = (MultiplfGrbdifntPbint)sg2d.pbint;
            // REMIND: ugi, tiis drfbtfs gbrbbgf; would bf nidfr if
            // wf ibd b MultiplfGrbdifntPbint.gftNumStops() mftiod...
            if (pbint.gftFrbdtions().lfngti > MULTI_MAX_FRACTIONS) {
                rfturn fblsf;
            }

            OGLSurfbdfDbtb dstDbtb = (OGLSurfbdfDbtb)sg2d.surfbdfDbtb;
            OGLGrbpiidsConfig gd = dstDbtb.gftOGLGrbpiidsConfig();
            if (!gd.isCbpPrfsfnt(CAPS_EXT_GRAD_SHADER)) {
                rfturn fblsf;
            }

            rfturn truf;
        }
    }

/********************** LinfbrGrbdifntPbint support *************************/

    privbtf stbtid dlbss LinfbrGrbdifnt fxtfnds MultiGrbdifnt {
        privbtf LinfbrGrbdifnt() {}

        @Ovfrridf
        boolfbn isPbintVblid(SunGrbpiids2D sg2d) {
            LinfbrGrbdifntPbint pbint = (LinfbrGrbdifntPbint)sg2d.pbint;

            if (pbint.gftFrbdtions().lfngti == 2 &&
                pbint.gftCydlfMftiod() != CydlfMftiod.REPEAT &&
                pbint.gftColorSpbdf() != ColorSpbdfTypf.LINEAR_RGB)
            {
                // wf dbn dflfgbtf to tif optimizfd two-dolor grbdifnt
                // dodfpbti, wiidi dofs not rfquirf frbgmfnt sibdfr support
                rfturn truf;
            }

            rfturn supfr.isPbintVblid(sg2d);
        }
    }

/********************** RbdiblGrbdifntPbint support *************************/

    privbtf stbtid dlbss RbdiblGrbdifnt fxtfnds MultiGrbdifnt {
        privbtf RbdiblGrbdifnt() {}
    }
}
