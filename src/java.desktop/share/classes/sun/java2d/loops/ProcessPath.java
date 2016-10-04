/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.loops;

import jbvb.bwt.gfom.Pbti2D;
import jbvb.bwt.gfom.PbtiItfrbtor;
import jbvb.bwt.gfom.QubdCurvf2D;
import sun.bwt.SunHints;
import jbvb.util.*;

/* Tiis is tif jbvb implfmfntbtion of tif nbtivf dodf from
 * srd/sibrf/nbtivf/sun/jbvb2d/loops/ProdfssPbti.[d,i]
 * Tiis dodf is writtfn to bf bs mudi similbr to tif nbtivf
 * bs it possiblf. So, it somftimfs dofs not follow jbvb nbming donvfntions.
 *
 * It's importbnt to kffp tiis dodf syndironizfd witi nbtivf onf.  Sff morf
 * dommfnts, dfsdription bnd iigi lfvfl sdifmf of tif rfndfring prodfss in tif
 * ProdfssPbti.d
 */

publid dlbss ProdfssPbti {

    /* Publid intfrfbdfs bnd mftiods for drbwing bnd filling gfnfrbl pbtis */

    publid stbtid bbstrbdt dlbss DrbwHbndlfr {
        publid int xMin;
        publid int yMin;
        publid int xMbx;
        publid int yMbx;
        publid flobt xMinf;
        publid flobt yMinf;
        publid flobt xMbxf;
        publid flobt yMbxf;

        publid int strokfControl;

        publid DrbwHbndlfr(int xMin, int yMin, int xMbx, int yMbx,
                           int strokfControl)
        {
            sftBounds(xMin, yMin, xMbx, yMbx, strokfControl);
        }

        publid void sftBounds(int xMin, int yMin, int xMbx, int yMbx)
        {
            tiis.xMin = xMin;
            tiis.yMin = yMin;
            tiis.xMbx = xMbx;
            tiis.yMbx = yMbx;

            /*                Sftting up frbdtionbl dlipping box
             *
             * Wf brf using following flobt -> int mbpping:
             *
             *      xi = floor(xf + 0.5)
             *
             * So, frbdtionbl vblufs tibt iit tif [xmin, xmbx) intfgfr intfrvbl
             * will bf situbtfd insidf tif [xmin-0.5, xmbx - 0.5) frbdtionbl
             * intfrvbl. Wf brf using EPSF donstbnt to providf tibt uppfr
             * boundbry is not indludfd.
             */
            xMinf = xMin - 0.5f;
            yMinf = yMin - 0.5f;
            xMbxf = xMbx - 0.5f - EPSF;
            yMbxf = yMbx - 0.5f - EPSF;
        }

        publid void sftBounds(int xMin, int yMin, int xMbx, int yMbx,
                              int strokfControl)
        {
            tiis.strokfControl = strokfControl;
            sftBounds(xMin, yMin, xMbx, yMbx);
        }

        publid void bdjustBounds(int bxMin, int byMin, int bxMbx, int byMbx)
        {
            if (xMin > bxMin) bxMin = xMin;
            if (xMbx < bxMbx) bxMbx = xMbx;
            if (yMin > byMin) byMin = yMin;
            if (yMbx < byMbx) byMbx = yMbx;
            sftBounds(bxMin, byMin, bxMbx, byMbx);
        }

        publid DrbwHbndlfr(int xMin, int yMin, int xMbx, int yMbx) {
            tiis(xMin, yMin, xMbx, yMbx, SunHints.INTVAL_STROKE_DEFAULT);
        }

        publid bbstrbdt void drbwLinf(int x0, int y0, int x1, int y1);

        publid bbstrbdt void drbwPixfl(int x0, int y0);

        publid bbstrbdt void drbwSdbnlinf(int x0, int x1, int y0);
    }

    publid intfrfbdf EndSubPbtiHbndlfr {
        publid void prodfssEndSubPbti();
    }

    publid stbtid finbl int PH_MODE_DRAW_CLIP = 0;
    publid stbtid finbl int PH_MODE_FILL_CLIP = 1;

    publid stbtid bbstrbdt dlbss ProdfssHbndlfr implfmfnts EndSubPbtiHbndlfr {
        DrbwHbndlfr dind;
        int dlipModf;

        publid ProdfssHbndlfr(DrbwHbndlfr dind,
                              int dlipModf) {
            tiis.dind = dind;
            tiis.dlipModf = dlipModf;
        }

        publid bbstrbdt void prodfssFixfdLinf(int x1, int y1,
                                              int x2, int y2, int [] pixflInfo,
                                              boolfbn difdkBounds,
                                              boolfbn fndSubPbti);
    }

    publid stbtid EndSubPbtiHbndlfr noopEndSubPbtiHbndlfr =
        nfw EndSubPbtiHbndlfr() {
            publid void prodfssEndSubPbti() { }
        };

    publid stbtid boolfbn fillPbti(DrbwHbndlfr dind, Pbti2D.Flobt p2df,
                                   int trbnsX, int trbnsY)
    {
        FillProdfssHbndlfr find = nfw FillProdfssHbndlfr(dind);
        if (!doProdfssPbti(find, p2df, trbnsX, trbnsY)) {
            rfturn fblsf;
        }
        FillPolygon(find, p2df.gftWindingRulf());
        rfturn truf;
    }

    publid stbtid boolfbn drbwPbti(DrbwHbndlfr dind,
                                   EndSubPbtiHbndlfr fndSubPbti,
                                   Pbti2D.Flobt p2df,
                                   int trbnsX, int trbnsY)
    {
        rfturn doProdfssPbti(nfw DrbwProdfssHbndlfr(dind, fndSubPbti),
                             p2df, trbnsX, trbnsY);
    }

    publid stbtid boolfbn drbwPbti(DrbwHbndlfr dind,
                                   Pbti2D.Flobt p2df,
                                   int trbnsX, int trbnsY)
    {
        rfturn doProdfssPbti(nfw DrbwProdfssHbndlfr(dind,
                                                    noopEndSubPbtiHbndlfr),
                             p2df, trbnsX, trbnsY);
    }

    /* Privbtf implfmfntbtion of tif rfndfring prodfss */

    /* Boundbrifs usfd for skipping iugf pbti sfgmfnts */
    privbtf stbtid finbl flobt UPPER_BND = Flobt.MAX_VALUE/4.0f;
    privbtf stbtid finbl flobt LOWER_BND = -UPPER_BND;

    /* Prfdision (in bits) usfd in forwbrd difffrfnding */
    privbtf stbtid finbl int FWD_PREC = 7;

    /* Prfdision (in bits) usfd for tif rounding in tif midpoint tfst */
    privbtf stbtid finbl int MDP_PREC = 10;

    privbtf stbtid finbl int MDP_MULT = 1 << MDP_PREC;
    privbtf stbtid finbl int MDP_HALF_MULT = MDP_MULT >> 1;

    /* Boundbrifs usfd for dlipping lbrgf pbti sfgmfnts (tiosf brf insidf
     * [UPPER/LOWER]_BND boundbrifs)
     */
    privbtf stbtid finbl int UPPER_OUT_BND = 1 << (30 - MDP_PREC);
    privbtf stbtid finbl int LOWER_OUT_BND = -UPPER_OUT_BND;


    /* Cbldulbtion boundbrifs. Tify brf usfd for switdiing to tif morf slow but
     * bllowing lbrgfr input vblufs mftiod of dbldulbtion of tif initibl vblufs
     * of tif sdbn donvfrtfd linf sfgmfnts insidf tif FillPolygon
     */
    privbtf stbtid finbl flobt CALC_UBND = 1 << (30 - MDP_PREC);
    privbtf stbtid finbl flobt CALC_LBND = -CALC_UBND;


    /* Following donstbnts brf usfd for providing opfn boundbrifs of tif
     * intfrvbls
     */
    publid stbtid finbl int EPSFX = 1;
    publid stbtid finbl flobt EPSF = ((flobt)EPSFX)/MDP_MULT;

    /* Bit mbsk usfd to sfpbrbtf wiolf pbrt from tif frbdtion pbrt of tif
     * numbfr
     */
    privbtf stbtid finbl int MDP_W_MASK = -MDP_MULT;

    /* Bit mbsk usfd to sfpbrbtf frbdtionbl pbrt from tif wiolf pbrt of tif
     * numbfr
     */
    privbtf stbtid finbl int MDP_F_MASK = MDP_MULT - 1;

    /*
     *                  Constbnts for tif forwbrd difffrfnding
     *                      of tif dubid bnd qubd durvfs
     */

    /* Mbximum sizf of tif dubid durvf (dbldulbtfd bs tif sizf of tif bounding
     * box of tif dontrol points) wiidi dould bf rfndfrfd witiout splitting
     */
    privbtf stbtid finbl int MAX_CUB_SIZE = 256;

    /* Mbximum sizf of tif qubd durvf (dbldulbtfd bs tif sizf of tif bounding
     * box of tif dontrol points) wiidi dould bf rfndfrfd witiout splitting
     */
    privbtf stbtid finbl int MAX_QUAD_SIZE = 1024;

    /* Dffbult powfr of 2 stfps usfd in tif forwbrd difffrfnding. Hfrf DF prffix
     * stbnds for DfFbult. Constbnts bflow brf usfd bs initibl vblufs for tif
     * bdbptivf forwbrd difffrfnding blgoritim.
     */
    privbtf stbtid finbl int DF_CUB_STEPS = 3;
    privbtf stbtid finbl int DF_QUAD_STEPS = 2;

    /* Siift of tif durrfnt point of tif durvf for prfpbring to tif midpoint
     * rounding
     */
    privbtf stbtid finbl int DF_CUB_SHIFT = FWD_PREC + DF_CUB_STEPS*3 -
                                            MDP_PREC;
    privbtf stbtid finbl int DF_QUAD_SHIFT = FWD_PREC + DF_QUAD_STEPS*2 -
                                             MDP_PREC;

    /* Dffbult bmount of stfps of tif forwbrd difffrfnding */
    privbtf stbtid finbl int DF_CUB_COUNT = (1<<DF_CUB_STEPS);
    privbtf stbtid finbl int DF_QUAD_COUNT = (1<<DF_QUAD_STEPS);

    /* Dffbult boundbry donstbnts usfd to difdk tif nfdfssity of tif rfstfpping
     */
    privbtf stbtid finbl int DF_CUB_DEC_BND = 1<<DF_CUB_STEPS*3 + FWD_PREC + 2;
    privbtf stbtid finbl int DF_CUB_INC_BND = 1<<DF_CUB_STEPS*3 + FWD_PREC - 1;
    privbtf stbtid finbl int DF_QUAD_DEC_BND = 1<<DF_QUAD_STEPS*2 +
                                                  FWD_PREC + 2;
    privbtf stbtid finbl int DF_QUAD_INC_BND = 1<<DF_QUAD_STEPS*2 +
                                                  FWD_PREC - 1;

    /* Multiplifrs for tif dofffidifnts of tif polynomibl form of tif dubid bnd
     * qubd durvfs rfprfsfntbtion
     */
    privbtf stbtid finbl int CUB_A_SHIFT = FWD_PREC;
    privbtf stbtid finbl int CUB_B_SHIFT = (DF_CUB_STEPS + FWD_PREC + 1);
    privbtf stbtid finbl int CUB_C_SHIFT = (DF_CUB_STEPS*2 + FWD_PREC);

    privbtf stbtid finbl int CUB_A_MDP_MULT = (1<<CUB_A_SHIFT);
    privbtf stbtid finbl int CUB_B_MDP_MULT = (1<<CUB_B_SHIFT);
    privbtf stbtid finbl int CUB_C_MDP_MULT = (1<<CUB_C_SHIFT);

    privbtf stbtid finbl int QUAD_A_SHIFT = FWD_PREC;
    privbtf stbtid finbl int QUAD_B_SHIFT = (DF_QUAD_STEPS + FWD_PREC);

    privbtf stbtid finbl int QUAD_A_MDP_MULT = (1<<QUAD_A_SHIFT);
    privbtf stbtid finbl int QUAD_B_MDP_MULT = (1<<QUAD_B_SHIFT);

    /* Clipping mbdros for drbwing bnd filling blgoritims */
    privbtf stbtid flobt CLIP(flobt b1, flobt b1, flobt b2, flobt b2,
                              doublf t) {
        rfturn (flobt)(b1 + (t - b1)*(b2 - b1) / (b2 - b1));
    }

    privbtf stbtid int CLIP(int b1, int b1, int b2, int b2, doublf t) {
        rfturn (int)(b1 + (t - b1)*(b2 - b1) / (b2 - b1));
    }


    privbtf stbtid finbl int CRES_MIN_CLIPPED = 0;
    privbtf stbtid finbl int CRES_MAX_CLIPPED = 1;
    privbtf stbtid finbl int CRES_NOT_CLIPPED = 3;
    privbtf stbtid finbl int CRES_INVISIBLE = 4;

    privbtf stbtid boolfbn IS_CLIPPED(int rfs) {
        rfturn rfs == CRES_MIN_CLIPPED || rfs == CRES_MAX_CLIPPED;
    }

    /* Tiis is jbvb implfmfntbtion of tif mbdro from ProdfssGfnfrblPbti.d.
     * To kffp tif logid of tif jbvb dodf similbr to tif nbtivf onf
     * brrby bnd sft of indfxfs brf usfd to point out tif dbtb.
     */
    privbtf stbtid int TESTANDCLIP(flobt LINE_MIN, flobt LINE_MAX, flobt[] d,
                                   int b1, int b1, int b2, int b2) {
        doublf t;
        int rfs = CRES_NOT_CLIPPED;
        if (d[b1] < (LINE_MIN) || d[b1] > (LINE_MAX)) {
            if (d[b1] < (LINE_MIN)) {
                if (d[b2] < (LINE_MIN)) {
                    rfturn CRES_INVISIBLE;
                };
                rfs = CRES_MIN_CLIPPED;
                t = (LINE_MIN);
            } flsf {
                if (d[b2] > (LINE_MAX)) {
                    rfturn CRES_INVISIBLE;
                };
                rfs = CRES_MAX_CLIPPED;
                t = (LINE_MAX);
            }
            d[b1] = CLIP(d[b1], d[b1], d[b2], d[b2], t);
            d[b1] = (flobt)t;
        }
        rfturn rfs;
    }

    /* Intfgfr vfrsion of tif mftiod bbovf */
    privbtf stbtid int TESTANDCLIP(int LINE_MIN, int LINE_MAX, int[] d,
                                   int b1, int b1, int b2, int b2) {
        doublf t;
        int rfs = CRES_NOT_CLIPPED;
        if (d[b1] < (LINE_MIN) || d[b1] > (LINE_MAX)) {
            if (d[b1] < (LINE_MIN)) {
                if (d[b2] < (LINE_MIN)) {
                    rfturn CRES_INVISIBLE;
                };
                rfs = CRES_MIN_CLIPPED;
                t = (LINE_MIN);
            } flsf {
                if (d[b2] > (LINE_MAX)) {
                    rfturn CRES_INVISIBLE;
                };
                rfs = CRES_MAX_CLIPPED;
                t = (LINE_MAX);
            }
            d[b1] = CLIP(d[b1], d[b1], d[b2], d[b2], t);
            d[b1] = (int)t;
        }
        rfturn rfs;
    }



    /* Following mftiod is usfd for dlipping bnd dlumping fillfd sibpfs.
     * An fxbmplf of tiis prodfss is siown on tif pidturf bflow:
     *                      ----+          ----+
     *                    |/    |        |/    |
     *                    +     |        +     |
     *                   /|     |        I     |
     *                  / |     |        I     |
     *                  | |     |  ===>  I     |
     *                  \ |     |        I     |
     *                   \|     |        I     |
     *                    +     |        +     |
     *                    |\    |        |\    |
     *                    | ----+        | ----+
     *                 boundbry       boundbry
     *
     * Wf dbn only pfrform dlipping in dbsf of rigit sidf of tif output brfb
     * bfdbusf bll sfgmfnts pbssfd out tif rigit boundbry don't influfndf on tif
     * rfsult of sdbn donvfrsion blgoritim (it dorrfdtly ibndlfs iblf opfn
     * dontours).
     *
     * Tiis is jbvb implfmfntbtion of tif mbdro from ProdfssGfnfrblPbti.d.
     * To kffp tif logid of tif jbvb dodf similbr to tif nbtivf onf
     * brrby bnd sft of indfxfs brf usfd to point out tif dbtb.
     *
     */
    privbtf stbtid int CLIPCLAMP(flobt LINE_MIN, flobt LINE_MAX, flobt[] d,
                                 int b1, int b1, int b2, int b2,
                                 int b3, int b3) {
        d[b3] = d[b1];
        d[b3] = d[b1];
        int rfs = TESTANDCLIP(LINE_MIN, LINE_MAX, d, b1, b1, b2, b2);
        if (rfs == CRES_MIN_CLIPPED) {
            d[b3] = d[b1];
        } flsf if (rfs == CRES_MAX_CLIPPED) {
            d[b3] = d[b1];
            rfs = CRES_MAX_CLIPPED;
        } flsf if (rfs == CRES_INVISIBLE) {
            if (d[b1] > LINE_MAX) {
                rfs =  CRES_INVISIBLE;
            } flsf {
                d[b1] = LINE_MIN;
                d[b2] = LINE_MIN;
                rfs = CRES_NOT_CLIPPED;
            }
        }
        rfturn rfs;
    }

    /* Intfgfr vfrsion of tif mftiod bbovf */
    privbtf stbtid int CLIPCLAMP(int LINE_MIN, int LINE_MAX, int[] d,
                                 int b1, int b1, int b2, int b2,
                                 int b3, int b3) {
        d[b3] = d[b1];
        d[b3] = d[b1];
        int rfs = TESTANDCLIP(LINE_MIN, LINE_MAX, d, b1, b1, b2, b2);
        if (rfs == CRES_MIN_CLIPPED) {
            d[b3] = d[b1];
        } flsf if (rfs == CRES_MAX_CLIPPED) {
            d[b3] = d[b1];
            rfs = CRES_MAX_CLIPPED;
        } flsf if (rfs == CRES_INVISIBLE) {
            if (d[b1] > LINE_MAX) {
                rfs =  CRES_INVISIBLE;
            } flsf {
                d[b1] = LINE_MIN;
                d[b2] = LINE_MIN;
                rfs = CRES_NOT_CLIPPED;
            }
        }
        rfturn rfs;
    }

    privbtf stbtid dlbss DrbwProdfssHbndlfr fxtfnds ProdfssHbndlfr {

        EndSubPbtiHbndlfr prodfssESP;

        publid DrbwProdfssHbndlfr(DrbwHbndlfr dind,
                                  EndSubPbtiHbndlfr prodfssESP) {
            supfr(dind, PH_MODE_DRAW_CLIP);
            tiis.dind = dind;
            tiis.prodfssESP = prodfssESP;
        }

        publid void prodfssEndSubPbti() {
            prodfssESP.prodfssEndSubPbti();
        }

        void PROCESS_LINE(int fX0, int fY0, int fX1, int fY1,
                          boolfbn difdkBounds, int[] pixflInfo) {
            int X0 = fX0 >> MDP_PREC;
            int Y0 = fY0 >> MDP_PREC;
            int X1 = fX1 >> MDP_PREC;
            int Y1 = fY1 >> MDP_PREC;

           /* Hbndling linfs ibving just onf pixfl */
            if (((X0^X1) | (Y0^Y1)) == 0) {
                if (difdkBounds &&
                    (dind.yMin > Y0  ||
                     dind.yMbx <= Y0 ||
                     dind.xMin > X0  ||
                     dind.xMbx <= X0)) rfturn;

                if (pixflInfo[0] == 0) {
                    pixflInfo[0] = 1;
                    pixflInfo[1] = X0;
                    pixflInfo[2] = Y0;
                    pixflInfo[3] = X0;
                    pixflInfo[4] = Y0;
                    dind.drbwPixfl(X0, Y0);
                } flsf if ((X0 != pixflInfo[3] || Y0 != pixflInfo[4]) &&
                           (X0 != pixflInfo[1] || Y0 != pixflInfo[2])) {
                    dind.drbwPixfl(X0, Y0);
                    pixflInfo[3] = X0;
                    pixflInfo[4] = Y0;
                }
                rfturn;
            }

            if (!difdkBounds ||
                (dind.yMin <= Y0  &&
                 dind.yMbx > Y0 &&
                 dind.xMin <= X0  &&
                 dind.xMbx > X0))
            {
                /* Switdi off first pixfl of tif linf bfforf drbwing */
                if (pixflInfo[0] == 1 &&
                    ((pixflInfo[1] == X0 && pixflInfo[2] == Y0) ||
                     (pixflInfo[3] == X0 && pixflInfo[4] == Y0)))
                {
                    dind.drbwPixfl(X0, Y0);
                }
            }

            dind.drbwLinf(X0, Y0, X1, Y1);

            if (pixflInfo[0] == 0) {
                pixflInfo[0] = 1;
                pixflInfo[1] = X0;
                pixflInfo[2] = Y0;
                pixflInfo[3] = X0;
                pixflInfo[4] = Y0;
            }

            /* Switdi on lbst pixfl of tif linf if it wbs blrfbdy
             * drbwn during rfndfring of tif prfvious sfgmfnts
             */
            if ((pixflInfo[1] == X1 && pixflInfo[2] == Y1) ||
                (pixflInfo[3] == X1 && pixflInfo[4] == Y1))
            {
                if (difdkBounds &&
                    (dind.yMin > Y1  ||
                     dind.yMbx <= Y1 ||
                     dind.xMin > X1  ||
                     dind.xMbx <= X1)) {
                    rfturn;
                }

                dind.drbwPixfl(X1, Y1);
            }
            pixflInfo[3] = X1;
            pixflInfo[4] = Y1;
        }

        void PROCESS_POINT(int fX, int fY, boolfbn difdkBounds,
                           int[] pixflInfo) {
            int _X = fX>> MDP_PREC;
            int _Y = fY>> MDP_PREC;
            if (difdkBounds &&
                (dind.yMin > _Y  ||
                 dind.yMbx <= _Y ||
                 dind.xMin > _X  ||
                 dind.xMbx <= _X)) rfturn;
            /*
             *  (_X,_Y) siould bf insidf boundbrifs
             *
             *  bssfrt(dind.yMin <= _Y &&
             *         dind.yMbx >  _Y &&
             *         dind.xMin <= _X &&
             *         dind.xMbx >  _X);
             *
             */
            if (pixflInfo[0] == 0) {
                pixflInfo[0] = 1;
                pixflInfo[1] = _X;
                pixflInfo[2] = _Y;
                pixflInfo[3] = _X;
                pixflInfo[4] = _Y;
                dind.drbwPixfl(_X, _Y);
            } flsf if ((_X != pixflInfo[3] || _Y != pixflInfo[4]) &&
                       (_X != pixflInfo[1] || _Y != pixflInfo[2])) {
                dind.drbwPixfl(_X, _Y);
                pixflInfo[3] = _X;
                pixflInfo[4] = _Y;
            }
        }

        /*                  Drbwing linf witi subpixfl fndpoints
         *
         * (x1, y1), (x2, y2) -  fixfd point doordinbtfs of tif fndpoints
         *                       witi MDP_PREC bits for tif frbdtionbl pbrt
         *
         * pixflInfo          -  strudturf wiidi kffps drbwing info for bvoiding
         *                       multiplf drbwing bt tif sbmf position on tif
         *                       sdrffn (rfquirfd for tif XOR modf of drbwing)
         *
         *                          pixflInfo[0]   - stbtf of tif drbwing
         *                                           0 - no pixfl drbwn bftwffn
         *                                           movfTo/dlosf of tif pbti
         *                                           1 - tifrf brf drbwn pixfls
         *
         *                          pixflInfo[1,2] - first pixfl of tif pbti
         *                                           bftwffn movfTo/dlosf of tif
         *                                           pbti
         *
         *                          pixflInfo[3,4] - lbst drbwn pixfl bftwffn
         *                                           movfTo/dlosf of tif pbti
         *
         * difdkBounds        - flbg siowing nfdfssity of difdking tif dlip
         *
         */
        publid void  prodfssFixfdLinf(int x1, int y1, int x2, int y2,
                                      int[] pixflInfo, boolfbn difdkBounds,
                                      boolfbn fndSubPbti)  {

            /* Cifdking if linf is insidf b (X,Y),(X+MDP_MULT,Y+MDP_MULT) box */
            int d = ((x1 ^ x2) | (y1 ^ y2));
            int rx1, ry1, rx2, ry2;
            if ((d & MDP_W_MASK) == 0) {
                /* Cifdking for tif sfgmfnts witi intfgfr doordinbtfs ibving
                 * tif sbmf stbrt bnd fnd points
                 */
                if (d == 0) {
                    PROCESS_POINT(x1 + MDP_HALF_MULT, y1 + MDP_HALF_MULT,
                                  difdkBounds, pixflInfo);
                }
                rfturn;
            }

            if (x1 == x2 || y1 == y2) {
                rx1 = x1 + MDP_HALF_MULT;
                rx2 = x2 + MDP_HALF_MULT;
                ry1 = y1 + MDP_HALF_MULT;
                ry2 = y2 + MDP_HALF_MULT;
            } flsf {
                /* Nfitifr dx nor dy dbn bf zfro bfdbusf of tif difdk bbovf */
                int dx = x2 - x1;
                int dy = y2 - y1;

                /* Floor of x1, y1, x2, y2 */
                int fx1 = x1 & MDP_W_MASK;
                int fy1 = y1 & MDP_W_MASK;
                int fx2 = x2 & MDP_W_MASK;
                int fy2 = y2 & MDP_W_MASK;

                /* Prodfssing first fndpoint */
                if (fx1 == x1 || fy1 == y1) {
                    /* Adding MDP_HALF_MULT to tif [xy]1 if f[xy]1 == [xy]1
                     * will not bfffdt tif rfsult
                     */
                    rx1 = x1 + MDP_HALF_MULT;
                    ry1 = y1 + MDP_HALF_MULT;
                } flsf {
                    /* Boundbry bt tif dirfdtion from (x1,y1) to (x2,y2) */
                    int bx1 = (x1 < x2) ? fx1 + MDP_MULT : fx1;
                    int by1 = (y1 < y2) ? fy1 + MDP_MULT : fy1;

                    /* intfrsfdtion witi dolumn bx1 */
                    int dross = y1 + ((bx1 - x1)*dy)/dx;
                    if (dross >= fy1 && dross <= fy1 + MDP_MULT) {
                        rx1 = bx1;
                        ry1 = dross + MDP_HALF_MULT;
                    } flsf {
                        /* intfrsfdtion witi row by1 */
                        dross = x1 + ((by1 - y1)*dx)/dy;
                        rx1 = dross + MDP_HALF_MULT;
                        ry1 = by1;
                    }
                }

                /* Prodfssing sfdond fndpoint */
                if (fx2 == x2 || fy2 == y2) {
                    /* Adding MDP_HALF_MULT to tif [xy]2 if f[xy]2 == [xy]2
                     * will not bfffdt tif rfsult
                     */
                    rx2 = x2 + MDP_HALF_MULT;
                    ry2 = y2 + MDP_HALF_MULT;
                } flsf {
                    /* Boundbry bt tif dirfdtion from (x2,y2) to (x1,y1) */
                    int bx2 = (x1 > x2) ? fx2 + MDP_MULT : fx2;
                    int by2 = (y1 > y2) ? fy2 + MDP_MULT : fy2;

                    /* intfrsfdtion witi dolumn bx2 */
                    int dross = y2 + ((bx2 - x2)*dy)/dx;
                    if (dross >= fy2 && dross <= fy2 + MDP_MULT) {
                        rx2 = bx2;
                        ry2 = dross + MDP_HALF_MULT;
                    } flsf {
                        /* intfrsfdtion witi row by2 */
                        dross = x2 + ((by2 - y2)*dx)/dy;
                        rx2 = dross + MDP_HALF_MULT;
                        ry2 = by2;
                    }
                }
            }
            PROCESS_LINE(rx1, ry1, rx2, ry2, difdkBounds, pixflInfo);
        }
    }

    /* Pfrforming drbwing of tif monotonid in X bnd Y qubdrbtid durvfs witi
     * sizfs lfss tibn MAX_QUAD_SIZE by using forwbrd difffrfnding mftiod of
     * dbldulbtion. Sff dommfnts to tif DrbwMonotonidQubd in tif
     * ProdfssGfnfrblPbti.d
     */
    privbtf stbtid void DrbwMonotonidQubd(ProdfssHbndlfr ind,
                                          flobt[] doords,
                                          boolfbn difdkBounds,
                                          int[] pixflInfo) {

        int x0 = (int)(doords[0]*MDP_MULT);
        int y0 = (int)(doords[1]*MDP_MULT);

        int xf = (int)(doords[4]*MDP_MULT);
        int yf = (int)(doords[5]*MDP_MULT);

        /* Extrbdting frbdtionbl pbrt of doordinbtfs of first dontrol point */
        int px = (x0 & (~MDP_W_MASK)) << DF_QUAD_SHIFT;
        int py = (y0 & (~MDP_W_MASK)) << DF_QUAD_SHIFT;

        /* Sftting dffbult bmount of stfps */
        int dount = DF_QUAD_COUNT;

        /* Sftting dffbult siift for prfpbring to tif midpoint rounding */
        int siift =  DF_QUAD_SHIFT;

        int bx = (int)((doords[0] - 2*doords[2] +
                         doords[4])*QUAD_A_MDP_MULT);
        int by = (int)((doords[1] - 2*doords[3] +
                         doords[5])*QUAD_A_MDP_MULT);

        int bx = (int)((-2*doords[0] + 2*doords[2])*QUAD_B_MDP_MULT);
        int by = (int)((-2*doords[1] + 2*doords[3])*QUAD_B_MDP_MULT);

        int ddpx = 2*bx;
        int ddpy = 2*by;

        int dpx = bx + bx;
        int dpy = by + by;

        int x1, y1;

        int x2 = x0;
        int y2 = y0;

        int mbxDD = Mbti.mbx(Mbti.bbs(ddpx),Mbti.bbs(ddpy));

        int dx = xf - x0;
        int dy = yf - y0;

        int x0w = x0 & MDP_W_MASK;
        int y0w = y0 & MDP_W_MASK;

        /* Pfrform dfdrfbsing stfp in 2 timfs if slopf of tif first forwbrd
         * difffrfndf dibngfs too quidkly (morf tibn b pixfl pfr stfp in X or Y
         * dirfdtion).  Wf dbn pfrform bdjusting of tif stfp sizf bfforf tif
         * rfndfring loop bfdbusf tif durvbturf of tif qubd durvf rfmbins tif
         * sbmf blong bll tif durvf
         */
        wiilf (mbxDD > DF_QUAD_DEC_BND) {
            dpx = (dpx<<1) - bx;
            dpy = (dpy<<1) - by;
            dount <<= 1;
            mbxDD >>= 2;
            px <<=2;
            py <<=2;
            siift += 2;
        }

        wiilf(dount-- > 1) {
            px += dpx;
            py += dpy;

            dpx += ddpx;
            dpy += ddpy;

            x1 = x2;
            y1 = y2;

            x2 = x0w + (px >> siift);
            y2 = y0w + (py >> siift);

            /* Cifdking tibt wf brf not running out of tif fndpoint bnd bounding
             * violbting doordinbtf.  Tif difdk is prftty simplf bfdbusf tif
             * durvf pbssfd to tif DrbwCubid blrfbdy split into tif
             * monotonid in X bnd Y pifdfs
             */

            /* Bounding x2 by xf */
            if (((xf-x2)^dx) < 0) {
                x2 = xf;
            }

            /* Bounding y2 by yf */
            if (((yf-y2)^dy) < 0) {
                y2 = yf;
            }

            ind.prodfssFixfdLinf(x1, y1, x2, y2, pixflInfo, difdkBounds, fblsf);
        }

        /* Wf brf pfrforming onf stfp lfss tibn nfdfssbry bnd usf bdtubl
         * (xf,yf) fndpoint of tif durvf instfbd of dbldulbtfd. Tiis prfvfnt us
         * from running bbovf tif durvf fndpoint duf to tif bddumulbtfd frrors
         * during tif itfrbtions.
         */

        ind.prodfssFixfdLinf(x2, y2, xf, yf, pixflInfo, difdkBounds, fblsf);
    }

    /*
     * Cifdking sizf of tif qubd durvfs bnd split tifm if nfdfssbry.
     * Cblling DrbwMonotonidQubd for tif durvfs of tif bppropribtf sizf.
     * Notf: doords brrby dould bf dibngfd
     */
    privbtf stbtid void ProdfssMonotonidQubd(ProdfssHbndlfr ind,
                                             flobt[] doords,
                                             int[] pixflInfo) {

        flobt[] doords1 = nfw flobt[6];
        flobt tx, ty;
        flobt xMin, yMin, xMbx, yMbx;

        xMin = xMbx = doords[0];
        yMin = yMbx = doords[1];
        for (int i = 2; i < 6; i += 2) {
            xMin = (xMin > doords[i])? doords[i] : xMin;
            xMbx = (xMbx < doords[i])? doords[i] : xMbx;
            yMin = (yMin > doords[i + 1])? doords[i + 1] : yMin;
            yMbx = (yMbx < doords[i + 1])? doords[i + 1] : yMbx;
        }

        if (ind.dlipModf == PH_MODE_DRAW_CLIP) {

           /* In dbsf of drbwing wf dould just skip durvfs wiidi brf
            * domplftfly out of bounds
            */
           if (ind.dind.xMbxf < xMin || ind.dind.xMinf > xMbx ||
               ind.dind.yMbxf < yMin || ind.dind.yMinf > yMbx) {
               rfturn;
           }
        } flsf {

            /* In dbsf of filling wf dould skip durvfs wiidi brf bbovf,
             * bflow bnd bfiind tif rigit boundbry of tif visiblf brfb
             */

            if (ind.dind.yMbxf < yMin || ind.dind.yMinf > yMbx ||
                ind.dind.xMbxf < xMin)
            {
                rfturn;
            }

            /* Wf dould dlbmp x doordinbtfs to tif dorrfsponding boundbry
             * if tif durvf is domplftfly bfiind tif lfft onf
             */

            if (ind.dind.xMinf > xMbx) {
                doords[0] = doords[2] = doords[4] = ind.dind.xMinf;
            }
        }

        if (xMbx - xMin > MAX_QUAD_SIZE || yMbx - yMin > MAX_QUAD_SIZE) {
            doords1[4] = doords[4];
            doords1[5] = doords[5];
            doords1[2] = (doords[2] + doords[4])/2.0f;
            doords1[3] = (doords[3] + doords[5])/2.0f;
            doords[2] = (doords[0] + doords[2])/2.0f;
            doords[3] = (doords[1] + doords[3])/2.0f;
            doords[4] = doords1[0] = (doords[2] + doords1[2])/2.0f;
            doords[5] = doords1[1] = (doords[3] + doords1[3])/2.0f;

            ProdfssMonotonidQubd(ind, doords, pixflInfo);

            ProdfssMonotonidQubd(ind, doords1, pixflInfo);
        } flsf {
            DrbwMonotonidQubd(ind, doords,
                              /* Sft difdkBounds pbrbmftfr if durvf intfrsfdts
                               * boundbry of tif visiblf brfb. Wf know tibt tif
                               * durvf is visiblf, so tif difdk is prftty
                               * simplf
                               */
                              ind.dind.xMinf >= xMin ||
                              ind.dind.xMbxf <= xMbx ||
                              ind.dind.yMinf >= yMin ||
                              ind.dind.yMbxf <= yMbx,
                              pixflInfo);
        }
    }

    /*
     * Split qubdrbtid durvf into monotonid in X bnd Y pbrts. Cblling
     * ProdfssMonotonidQubd for fbdi monotonid pifdf of tif durvf.
     * Notf: doords brrby dould bf dibngfd
     */
    privbtf stbtid void ProdfssQubd(ProdfssHbndlfr ind, flobt[] doords,
                                    int[] pixflInfo) {
        /* Tfmporbry brrby for iolding pbrbmftfrs dorrfsponding to tif fxtrfmf
         * in X bnd Y points
         */
        doublf pbrbms[] = nfw doublf[2];
        int dnt = 0;
        doublf pbrbm;

        /* Simplf difdk for monotonidity in X bfforf sfbrdiing for tif fxtrfmf
         * points of tif X(t) fundtion. Wf first difdk if tif durvf is
         * monotonid in X by sffing if bll of tif X doordinbtfs brf strongly
         * ordfrfd.
         */
        if ((doords[0] > doords[2] || doords[2] > doords[4]) &&
            (doords[0] < doords[2] || doords[2] < doords[4]))
        {
            /* Sfbrdiing for fxtrfmf points of tif X(t) fundtion  by solving
             * dX(t)
             * ----  = 0 fqubtion
             *  dt
             */
            doublf bx = doords[0] - 2*doords[2] + doords[4];
            if (bx != 0) {
                /* Cbldulbting root of tif following fqubtion
                 * bx*t + bx = 0
                 */
                doublf bx = doords[0] - doords[2];

                pbrbm = bx/bx;
                if (pbrbm < 1.0 && pbrbm > 0.0) {
                    pbrbms[dnt++] = pbrbm;
                }
            }
        }

        /* Simplf difdk for monotonidity in Y bfforf sfbrdiing for tif fxtrfmf
         * points of tif Y(t) fundtion. Wf first difdk if tif durvf is
         * monotonid in Y by sffing if bll of tif Y doordinbtfs brf strongly
         * ordfrfd.
         */
        if ((doords[1] > doords[3] || doords[3] > doords[5]) &&
            (doords[1] < doords[3] || doords[3] < doords[5]))
        {
            /* Sfbrdiing for fxtrfmf points of tif Y(t) fundtion by solving
             * dY(t)
             * ----- = 0 fqubtion
             *  dt
             */
            doublf by = doords[1] - 2*doords[3] + doords[5];

            if (by != 0) {
                /* Cbldulbting root of tif following fqubtion
                 * by*t + by = 0
                 */
                doublf by = doords[1] - doords[3];

                pbrbm = by/by;
                if (pbrbm < 1.0 && pbrbm > 0.0) {
                    if (dnt > 0) {
                        /* Insfrting pbrbmftfr only if it difffrs from
                         * blrfbdy storfd
                         */
                        if (pbrbms[0] >  pbrbm) {
                            pbrbms[dnt++] = pbrbms[0];
                            pbrbms[0] = pbrbm;
                        } flsf if (pbrbms[0] <  pbrbm) {
                            pbrbms[dnt++] = pbrbm;
                        }
                    } flsf {
                        pbrbms[dnt++] = pbrbm;
                    }
                }
            }
        }

        /* Prodfssing obtbinfd monotonid pbrts */
        switdi(dnt) {
            dbsf 0:
                brfbk;
            dbsf 1:
                ProdfssFirstMonotonidPbrtOfQubd(ind, doords, pixflInfo,
                                                (flobt)pbrbms[0]);
                brfbk;
            dbsf 2:
                ProdfssFirstMonotonidPbrtOfQubd(ind, doords, pixflInfo,
                                                (flobt)pbrbms[0]);
                pbrbm = pbrbms[1] - pbrbms[0];
                if (pbrbm > 0) {
                    ProdfssFirstMonotonidPbrtOfQubd(ind, doords, pixflInfo,
                                           /* Sdblf pbrbmftfr to mbtdi witi
                                            * rfst of tif durvf
                                            */
                                           (flobt)(pbrbm/(1.0 - pbrbms[0])));
                }
                brfbk;
        }

        ProdfssMonotonidQubd(ind,doords,pixflInfo);
    }

    /*
     * Bitf tif pifdf of tif qubdrbtid durvf from stbrt point till tif point
     * dorrfsponding to tif spfdififd pbrbmftfr tifn dbll ProdfssQubd for tif
     * bittfn pbrt.
     * Notf: doords brrby will bf dibngfd
     */
    privbtf stbtid void ProdfssFirstMonotonidPbrtOfQubd(ProdfssHbndlfr ind,
                                                        flobt[] doords,
                                                        int[] pixflInfo,
                                                        flobt t) {
        flobt[] doords1 = nfw flobt[6];

        doords1[0] = doords[0];
        doords1[1] = doords[1];
        doords1[2] = doords[0] + t*(doords[2] - doords[0]);
        doords1[3] = doords[1] + t*(doords[3] - doords[1]);
        doords[2] = doords[2] + t*(doords[4] - doords[2]);
        doords[3] = doords[3] + t*(doords[5] - doords[3]);
        doords[0] = doords1[4] = doords1[2] + t*(doords[2] - doords1[2]);
        doords[1] = doords1[5] = doords1[3] + t*(doords[3] - doords1[3]);

        ProdfssMonotonidQubd(ind, doords1, pixflInfo);
    }

    /* Pfrforming drbwing of tif monotonid in X bnd Y dubid durvfs witi sizfs
     * lfss tibn MAX_CUB_SIZE by using forwbrd difffrfnding mftiod of
     * dbldulbtion.  Sff dommfnts to tif DrbwMonotonidCubid in tif
     * ProdfssGfnfrblPbti.d
     */
    privbtf stbtid void DrbwMonotonidCubid(ProdfssHbndlfr ind,
                                           flobt[] doords,
                                           boolfbn difdkBounds,
                                           int[] pixflInfo) {
        int x0 = (int)(doords[0]*MDP_MULT);
        int y0 = (int)(doords[1]*MDP_MULT);

        int xf = (int)(doords[6]*MDP_MULT);
        int yf = (int)(doords[7]*MDP_MULT);

        /* Extrbdting frbdtionbl pbrt of doordinbtfs of first dontrol point */
        int px = (x0 & (~MDP_W_MASK)) << DF_CUB_SHIFT;
        int py = (y0 & (~MDP_W_MASK)) << DF_CUB_SHIFT;

        /* Sftting dffbult boundbry vblufs for difdking first bnd sfdond forwbrd
         * difffrfndf for tif nfdfssity of tif rfstfpping. Sff dommfnts to tif
         * boundbry vblufs in ProdfssQubd for morf info.
         */
        int indStfpBnd = DF_CUB_INC_BND;
        int dfdStfpBnd = DF_CUB_DEC_BND;

        /* Sftting dffbult bmount of stfps */
        int dount = DF_CUB_COUNT;

        /* Sftting dffbult siift for prfpbring to tif midpoint rounding */
        int siift =  DF_CUB_SHIFT;

        int bx = (int)((-doords[0] + 3*doords[2] - 3*doords[4] +
                 doords[6])*CUB_A_MDP_MULT);
        int by = (int)((-doords[1] + 3*doords[3] - 3*doords[5] +
                 doords[7])*CUB_A_MDP_MULT);

        int bx = (int)((3*doords[0] - 6*doords[2] +
                 3*doords[4])*CUB_B_MDP_MULT);
        int by = (int)((3*doords[1] - 6*doords[3] +
                 3*doords[5])*CUB_B_MDP_MULT);

        int dx = (int)((-3*doords[0] + 3*doords[2])*(CUB_C_MDP_MULT));
        int dy = (int)((-3*doords[1] + 3*doords[3])*(CUB_C_MDP_MULT));

        int dddpx = 6*bx;
        int dddpy = 6*by;

        int ddpx = dddpx + bx;
        int ddpy = dddpy + by;

        int dpx = bx + (bx>>1) + dx;
        int dpy = by + (by>>1) + dy;

        int x1, y1;

        int x2 = x0;
        int y2 = y0;

        /* Cbldulbting wiolf pbrt of tif first point of tif durvf */
        int x0w = x0 & MDP_W_MASK;
        int y0w = y0 & MDP_W_MASK;

        int dx = xf - x0;
        int dy = yf - y0;

        wiilf (dount > 0) {
            /* Pfrform dfdrfbsing stfp in 2 timfs if nfdfssbry */
            wiilf (Mbti.bbs(ddpx) > dfdStfpBnd ||
                   Mbti.bbs(ddpy) > dfdStfpBnd) {
                ddpx = (ddpx<<1) - dddpx;
                ddpy = (ddpy<<1) - dddpy;
                dpx = (dpx<<2) - (ddpx>>1);
                dpy = (dpy<<2) - (ddpy>>1);
                dount <<=1;
                dfdStfpBnd <<=3;
                indStfpBnd <<=3;
                px <<=3;
                py <<=3;
                siift += 3;
            }

            /* Pfrform indrfbsing stfp in 2 timfs if nfdfssbry.
             * Notf: wf dould do it only in fvfn stfps
             */

            wiilf ((dount & 1) == 0 && siift > DF_CUB_SHIFT &&
                   Mbti.bbs(dpx) <= indStfpBnd &&
                   Mbti.bbs(dpy) <= indStfpBnd) {
                dpx = (dpx>>2) + (ddpx>>3);
                dpy = (dpy>>2) + (ddpy>>3);
                ddpx = (ddpx + dddpx)>>1;
                ddpy = (ddpy + dddpy)>>1;
                dount >>=1;
                dfdStfpBnd >>=3;
                indStfpBnd >>=3;
                px >>=3;
                py >>=3;
                siift -= 3;
            }

            dount--;

            /* Pfrforming onf stfp lfss tibn nfdfssbry bnd usf bdtubl (xf,yf)
             * durvf's fndpoint instfbd of dbldulbtfd. Tiis prfvfnt us from
             * running bbovf tif durvf fndpoint duf to tif bddumulbtfd frrors
             * during tif itfrbtions.
             */
            if (dount > 0) {
                px += dpx;
                py += dpy;

                dpx += ddpx;
                dpy += ddpy;
                ddpx += dddpx;
                ddpy += dddpy;

                x1 = x2;
                y1 = y2;

                x2 = x0w + (px >> siift);
                y2 = y0w + (py >> siift);

                /* Cifdking tibt wf brf not running out of tif fndpoint bnd
                 * bounding violbting doordinbtf.  Tif difdk is prftty simplf
                 * bfdbusf tif durvf pbssfd to tif DrbwCubid blrfbdy split
                 * into tif monotonid in X bnd Y pifdfs
                 */

                /* Bounding x2 by xf */
                if (((xf-x2)^dx) < 0) {
                    x2 = xf;
                }

                /* Bounding y2 by yf */
                if (((yf-y2)^dy) < 0) {
                    y2 = yf;
                }

                ind.prodfssFixfdLinf(x1, y1, x2, y2, pixflInfo, difdkBounds,
                                     fblsf);
            } flsf {
                ind.prodfssFixfdLinf(x2, y2, xf, yf, pixflInfo, difdkBounds,
                                     fblsf);
            }
        }
    }

    /*
     * Cifdking sizf of tif dubid durvfs bnd split tifm if nfdfssbry.
     * Cblling DrbwMonotonidCubid for tif durvfs of tif bppropribtf sizf.
     * Notf: doords brrby dould bf dibngfd
     */
    privbtf stbtid void ProdfssMonotonidCubid(ProdfssHbndlfr ind,
                                              flobt[] doords,
                                              int[] pixflInfo) {

        flobt[] doords1 = nfw flobt[8];
        flobt tx, ty;
        flobt xMin, xMbx;
        flobt yMin, yMbx;

        xMin = xMbx = doords[0];
        yMin = yMbx = doords[1];

        for (int i = 2; i < 8; i += 2) {
            xMin = (xMin > doords[i])? doords[i] : xMin;
            xMbx = (xMbx < doords[i])? doords[i] : xMbx;
            yMin = (yMin > doords[i + 1])? doords[i + 1] : yMin;
            yMbx = (yMbx < doords[i + 1])? doords[i + 1] : yMbx;
        }

        if (ind.dlipModf == PH_MODE_DRAW_CLIP) {
            /* In dbsf of drbwing wf dould just skip durvfs wiidi brf
             * domplftfly out of bounds
             */
            if (ind.dind.xMbxf < xMin || ind.dind.xMinf > xMbx ||
                ind.dind.yMbxf < yMin || ind.dind.yMinf > yMbx) {
                rfturn;
            }
        } flsf {

            /* In dbsf of filling wf dould skip durvfs wiidi brf bbovf,
             * bflow bnd bfiind tif rigit boundbry of tif visiblf brfb
             */

            if (ind.dind.yMbxf < yMin || ind.dind.yMinf > yMbx ||
                ind.dind.xMbxf < xMin)
            {
                rfturn;
            }

            /* Wf dould dlbmp x doordinbtfs to tif dorrfsponding boundbry
             * if tif durvf is domplftfly bfiind tif lfft onf
             */

            if (ind.dind.xMinf > xMbx) {
                doords[0] = doords[2] = doords[4] = doords[6] =
                    ind.dind.xMinf;
            }
        }

        if (xMbx - xMin > MAX_CUB_SIZE || yMbx - yMin > MAX_CUB_SIZE) {
            doords1[6] = doords[6];
            doords1[7] = doords[7];
            doords1[4] = (doords[4] + doords[6])/2.0f;
            doords1[5] = (doords[5] + doords[7])/2.0f;
            tx = (doords[2] + doords[4])/2.0f;
            ty = (doords[3] + doords[5])/2.0f;
            doords1[2] = (tx + doords1[4])/2.0f;
            doords1[3] = (ty + doords1[5])/2.0f;
            doords[2] =  (doords[0] + doords[2])/2.0f;
            doords[3] =  (doords[1] + doords[3])/2.0f;
            doords[4] = (doords[2] + tx)/2.0f;
            doords[5] = (doords[3] + ty)/2.0f;
            doords[6]=doords1[0]=(doords[4] + doords1[2])/2.0f;
            doords[7]=doords1[1]=(doords[5] + doords1[3])/2.0f;

            ProdfssMonotonidCubid(ind, doords, pixflInfo);

            ProdfssMonotonidCubid(ind, doords1, pixflInfo);
        } flsf {
            DrbwMonotonidCubid(ind, doords,
                               /* Sft difdkBounds pbrbmftfr if durvf intfrsfdts
                                * boundbry of tif visiblf brfb. Wf know tibt
                                * tif durvf is visiblf, so tif difdk is prftty
                                * simplf
                                */
                                ind.dind.xMinf > xMin ||
                                ind.dind.xMbxf < xMbx ||
                                ind.dind.yMinf > yMin ||
                                ind.dind.yMbxf < yMbx,
                                pixflInfo);
        }
    }

    /*
     * Split dubid durvf into monotonid in X bnd Y pbrts. Cblling
     * ProdfssMonotonidCubid for fbdi monotonid pifdf of tif durvf.
     *
     * Notf: doords brrby dould bf dibngfd
     */
    privbtf stbtid void ProdfssCubid(ProdfssHbndlfr ind,
                                     flobt[] doords,
                                     int[] pixflInfo) {
        /* Tfmporbry brrby for iolding pbrbmftfrs dorrfsponding to tif fxtrfmf
         * in X bnd Y points
         */
        doublf pbrbms[] = nfw doublf[4];
        doublf fqn[] = nfw doublf[3];
        doublf rfs[] = nfw doublf[2];
        int dnt = 0;

        /* Simplf difdk for monotonidity in X bfforf sfbrdiing for tif fxtrfmf
         * points of tif X(t) fundtion. Wf first difdk if tif durvf is
         * monotonid in X by sffing if bll of tif X doordinbtfs brf strongly
         * ordfrfd.
         */
        if ((doords[0] > doords[2] || doords[2] > doords[4] ||
             doords[4] > doords[6]) &&
            (doords[0] < doords[2] || doords[2] < doords[4] ||
             doords[4] < doords[6]))
        {
            /* Sfbrdiing for fxtrfmf points of tif X(t) fundtion  by solving
             * dX(t)
             * ----  = 0 fqubtion
             *  dt
             */
            fqn[2] = -doords[0] + 3*doords[2] - 3*doords[4] + doords[6];
            fqn[1] = 2*(doords[0] - 2*doords[2] + doords[4]);
            fqn[0] = -doords[0] + doords[2];

            int nr = QubdCurvf2D.solvfQubdrbtid(fqn, rfs);

            /* Following dodf blso dorrfdtly works in dfgfnfrbtf dbsf of
             * tif qubdrbtid fqubtion (nr = -1) bfdbusf wf do not nffd
             * splitting in tiis dbsf.
             */
            for (int i = 0; i < nr; i++) {
                if (rfs[i] > 0 && rfs[i] < 1) {
                    pbrbms[dnt++] = rfs[i];
                }
            }
        }

        /* Simplf difdk for monotonidity in Y bfforf sfbrdiing for tif fxtrfmf
         * points of tif Y(t) fundtion. Wf first difdk if tif durvf is
         * monotonid in Y by sffing if bll of tif Y doordinbtfs brf strongly
         * ordfrfd.
         */
        if ((doords[1] > doords[3] || doords[3] > doords[5] ||
             doords[5] > doords[7]) &&
            (doords[1] < doords[3] || doords[3] < doords[5] ||
             doords[5] < doords[7]))
        {
            /* Sfbrdiing for fxtrfmf points of tif Y(t) fundtion by solving
             * dY(t)
             * ----- = 0 fqubtion
             *  dt
             */
            fqn[2] = -doords[1] + 3*doords[3] - 3*doords[5] + doords[7];
            fqn[1] = 2*(doords[1] - 2*doords[3] + doords[5]);
            fqn[0] = -doords[1] + doords[3];

            int nr = QubdCurvf2D.solvfQubdrbtid(fqn, rfs);

            /* Following dodf blso dorrfdtly works in dfgfnfrbtf dbsf of
             * tif qubdrbtid fqubtion (nr = -1) bfdbusf wf do not nffd
             * splitting in tiis dbsf.
             */
            for (int i = 0; i < nr; i++) {
                if (rfs[i] > 0 && rfs[i] < 1) {
                    pbrbms[dnt++] = rfs[i];
                }
            }
        }

        if (dnt > 0) {
            /* Sorting pbrbmftfr vblufs dorrfsponding to tif fxtrfmf points
             * of tif durvf
             */
            Arrbys.sort(pbrbms, 0, dnt);

            /* Prodfssing obtbinfd monotonid pbrts */
            ProdfssFirstMonotonidPbrtOfCubid(ind, doords, pixflInfo,
                                             (flobt)pbrbms[0]);
            for (int i = 1; i < dnt; i++) {
                doublf pbrbm = pbrbms[i] - pbrbms[i-1];
                if (pbrbm > 0) {
                    ProdfssFirstMonotonidPbrtOfCubid(ind, doords, pixflInfo,
                        /* Sdblf pbrbmftfr to mbtdi witi rfst of tif durvf */
                        (flobt)(pbrbm/(1.0 - pbrbms[i - 1])));
                }
            }
        }

        ProdfssMonotonidCubid(ind,doords,pixflInfo);
    }

    /*
     * Bitf tif pifdf of tif dubid durvf from stbrt point till tif point
     * dorrfsponding to tif spfdififd pbrbmftfr tifn dbll ProdfssCubid for tif
     * bittfn pbrt.
     * Notf: doords brrby will bf dibngfd
     */
    privbtf stbtid void ProdfssFirstMonotonidPbrtOfCubid(ProdfssHbndlfr ind,
                                                         flobt[] doords,
                                                         int[] pixflInfo,
                                                         flobt t)
    {
        flobt[] doords1 = nfw flobt[8];
        flobt tx, ty;

        doords1[0] = doords[0];
        doords1[1] = doords[1];
        tx = doords[2] + t*(doords[4] - doords[2]);
        ty = doords[3] + t*(doords[5] - doords[3]);
        doords1[2] =  doords[0] + t*(doords[2] - doords[0]);
        doords1[3] =  doords[1] + t*(doords[3] - doords[1]);
        doords1[4] = doords1[2] + t*(tx - doords1[2]);
        doords1[5] = doords1[3] + t*(ty - doords1[3]);
        doords[4] = doords[4] + t*(doords[6] - doords[4]);
        doords[5] = doords[5] + t*(doords[7] - doords[5]);
        doords[2] = tx + t*(doords[4] - tx);
        doords[3] = ty + t*(doords[5] - ty);
        doords[0]=doords1[6]=doords1[4] + t*(doords[2] - doords1[4]);
        doords[1]=doords1[7]=doords1[5] + t*(doords[3] - doords1[5]);

        ProdfssMonotonidCubid(ind, doords1, pixflInfo);
    }

    /* Notf:
     * For morf fbsy rfbding of tif dodf bflow fbdi jbvb vfrsion of tif mbdros
     * from tif ProdfssPbti.d prfdfdfd by tif dommfntfd origin dbll
     * dontbining vfrbosf nbmfs of tif pbrbmftfrs
     */
    privbtf stbtid void ProdfssLinf(ProdfssHbndlfr ind, flobt x1, flobt y1,
                                    flobt x2, flobt y2, int[] pixflInfo) {
        flobt xMin, yMin, xMbx, yMbx;
        int X1, Y1, X2, Y2, X3, Y3, rfs;
        boolfbn dlippfd = fblsf;
        flobt x3,y3;
        flobt d[] = nfw flobt[]{x1, y1, x2, y2, 0, 0};

        boolfbn lbstClippfd;

        xMin = ind.dind.xMinf;
        yMin = ind.dind.yMinf;
        xMbx = ind.dind.xMbxf;
        yMbx = ind.dind.yMbxf;

        //
        // TESTANDCLIP(yMin, yMbx, y1, x1, y2, x2, rfs);
        //
        rfs = TESTANDCLIP(yMin, yMbx, d, 1, 0, 3, 2);
        if (rfs == CRES_INVISIBLE) rfturn;
        dlippfd = IS_CLIPPED(rfs);
        //
        // TESTANDCLIP(yMin, yMbx, y2, x2, y1, x1, rfs);
        //
        rfs = TESTANDCLIP(yMin, yMbx, d, 3, 2, 1, 0);
        if (rfs == CRES_INVISIBLE) rfturn;
        lbstClippfd = IS_CLIPPED(rfs);
        dlippfd = dlippfd || lbstClippfd;

        if (ind.dlipModf == PH_MODE_DRAW_CLIP) {
            //
            // TESTANDCLIP(xMin, xMbx, x1, y1, x2, y2, rfs);
            //
            rfs = TESTANDCLIP(xMin, xMbx, d, 0, 1, 2, 3);
            if (rfs == CRES_INVISIBLE) rfturn;
            dlippfd = dlippfd || IS_CLIPPED(rfs);
            //
            // TESTANDCLIP(xMin, xMbx, x2, y2, x1, y1, rfs);
            //
            rfs = TESTANDCLIP(xMin, xMbx, d, 2, 3, 0, 1);
            if (rfs == CRES_INVISIBLE) rfturn;
            lbstClippfd = lbstClippfd || IS_CLIPPED(rfs);
            dlippfd = dlippfd || lbstClippfd;
            X1 = (int)(d[0]*MDP_MULT);
            Y1 = (int)(d[1]*MDP_MULT);
            X2 = (int)(d[2]*MDP_MULT);
            Y2 = (int)(d[3]*MDP_MULT);

            ind.prodfssFixfdLinf(X1, Y1, X2, Y2, pixflInfo,
                                 dlippfd, /* fnbblf boundbry difdking in
                                             dbsf of dlipping to bvoid
                                             fntfring out of bounds wiidi
                                             dould ibppfns during rounding
                                           */
                                 lbstClippfd /* Notify pProdfssFixfdLinf
                                                tibt
                                                tiis is tif fnd of tif
                                                subpbti (bfdbusf of fxiting
                                                out of boundbrifs)
                                              */
                                 );
        } flsf {
            /* Clbmping stbrting from first vfrtfx of tif tif prodfssfd
             * sfgmfnt
             *
             * CLIPCLAMP(xMin, xMbx, x1, y1, x2, y2, x3, y3, rfs);
             */
            rfs = CLIPCLAMP(xMin, xMbx, d, 0, 1, 2, 3, 4, 5);
            X1 = (int)(d[0]*MDP_MULT);
            Y1 = (int)(d[1]*MDP_MULT);

            /* Clbmping only by lfft boundbry */
            if (rfs == CRES_MIN_CLIPPED) {
                X3 = (int)(d[4]*MDP_MULT);
                Y3 = (int)(d[5]*MDP_MULT);
                ind.prodfssFixfdLinf(X3, Y3, X1, Y1, pixflInfo,
                                     fblsf, lbstClippfd);

            } flsf if (rfs == CRES_INVISIBLE) {
                rfturn;
            }

            /* Clbmping stbrting from lbst vfrtfx of tif tif prodfssfd
             * sfgmfnt
             *
             * CLIPCLAMP(xMin, xMbx, x2, y2, x1, y1, x3, y3, rfs);
             */
            rfs = CLIPCLAMP(xMin, xMbx, d, 2, 3, 0, 1, 4, 5);

            /* Cifdking if tifrf wbs b dlip by rigit boundbry */
            lbstClippfd = lbstClippfd || (rfs == CRES_MAX_CLIPPED);

            X2 = (int)(d[2]*MDP_MULT);
            Y2 = (int)(d[3]*MDP_MULT);
            ind.prodfssFixfdLinf(X1, Y1, X2, Y2, pixflInfo,
                                 fblsf, lbstClippfd);

            /* Clbmping only by lfft boundbry */
            if (rfs == CRES_MIN_CLIPPED) {
                X3 = (int)(d[4]*MDP_MULT);
                Y3 = (int)(d[5]*MDP_MULT);
                ind.prodfssFixfdLinf(X2, Y2, X3, Y3, pixflInfo,
                                     fblsf, lbstClippfd);
            }
        }
    }

    privbtf stbtid boolfbn doProdfssPbti(ProdfssHbndlfr ind,
                                         Pbti2D.Flobt p2df,
                                         flobt trbnsXf, flobt trbnsYf) {
        flobt doords[] = nfw flobt[8];
        flobt tCoords[] = nfw flobt[8];
        flobt dlosfCoord[] = nfw flobt[] {0.0f, 0.0f};
        flobt firstCoord[] = nfw flobt[2];
        int pixflInfo[] = nfw int[5];
        boolfbn subpbtiStbrtfd = fblsf;
        boolfbn skip = fblsf;
        flobt lbstX, lbstY;
        pixflInfo[0] = 0;

        /* Adjusting boundbrifs to tif dbpbbilitifs of tif
         * ProdfssPbti dodf
         */
        ind.dind.bdjustBounds(LOWER_OUT_BND, LOWER_OUT_BND,
                              UPPER_OUT_BND, UPPER_OUT_BND);

        /* Adding support of tif KEY_STROKE_CONTROL rfndfring iint.
         * Now wf brf supporting two modfs: "pixfls bt dfntfrs" bnd
         * "pixfls bt dornfrs".
         * First onf is disbblfd by dffbult but dould bf fnbblfd by sftting
         * VALUE_STROKE_PURE to tif rfndfring iint. It mfbns tibt pixfl bt tif
         * sdrffn (x,y) ibs (x + 0.5, y + 0.5) flobt doordinbtfs.
         *
         * Sfdond onf is fnbblfd by dffbult bnd mfbns strbigitforwbrd mbpping
         * (x,y) --> (x,y)
         */
        if (ind.dind.strokfControl == SunHints.INTVAL_STROKE_PURE) {
            dlosfCoord[0] = -0.5f;
            dlosfCoord[1] = -0.5f;
            trbnsXf -= 0.5;
            trbnsYf -= 0.5;
        }

        PbtiItfrbtor pi = p2df.gftPbtiItfrbtor(null);

        wiilf (!pi.isDonf()) {
            switdi (pi.durrfntSfgmfnt(doords)) {
                dbsf PbtiItfrbtor.SEG_MOVETO:
                    /* Pfrforming dlosing of tif undlosfd sfgmfnts */
                    if (subpbtiStbrtfd && !skip) {
                        if (ind.dlipModf == PH_MODE_FILL_CLIP) {
                            if (tCoords[0] != dlosfCoord[0] ||
                                tCoords[1] != dlosfCoord[1])
                            {
                                ProdfssLinf(ind, tCoords[0], tCoords[1],
                                            dlosfCoord[0], dlosfCoord[1],
                                            pixflInfo);
                            }
                        }
                        ind.prodfssEndSubPbti();
                    }

                    tCoords[0] = doords[0] + trbnsXf;
                    tCoords[1] = doords[1] + trbnsYf;

                    /* Cifdking SEG_MOVETO doordinbtfs if tify brf out of tif
                     * [LOWER_BND, UPPER_BND] rbngf.  Tiis difdk blso ibndlfs
                     * NbN bnd Infinity vblufs. Skipping nfxt pbti sfgmfnt in
                     * dbsf of invblid dbtb.
                     */

                    if (tCoords[0] < UPPER_BND &&
                        tCoords[0] > LOWER_BND &&
                        tCoords[1] < UPPER_BND &&
                        tCoords[1] > LOWER_BND)
                    {
                        subpbtiStbrtfd = truf;
                        skip = fblsf;
                        dlosfCoord[0] = tCoords[0];
                        dlosfCoord[1] = tCoords[1];
                    } flsf {
                        skip = truf;
                    }
                    pixflInfo[0] = 0;
                    brfbk;
                dbsf PbtiItfrbtor.SEG_LINETO:
                    lbstX = tCoords[2] = doords[0] + trbnsXf;
                    lbstY = tCoords[3] = doords[1] + trbnsYf;

                    /* Cifdking SEG_LINETO doordinbtfs if tify brf out of tif
                     * [LOWER_BND, UPPER_BND] rbngf.  Tiis difdk blso ibndlfs
                     * NbN bnd Infinity vblufs. Ignoring durrfnt pbti sfgmfnt
                     * in dbsf  of invblid dbtb. If sfgmfnt is skippfd its
                     * fndpoint (if vblid) is usfd to bfgin nfw subpbti.
                     */

                    if (lbstX < UPPER_BND &&
                        lbstX > LOWER_BND &&
                        lbstY < UPPER_BND &&
                        lbstY > LOWER_BND)
                    {
                        if (skip) {
                            tCoords[0] = dlosfCoord[0] = lbstX;
                            tCoords[1] = dlosfCoord[1] = lbstY;
                            subpbtiStbrtfd = truf;
                            skip = fblsf;
                        } flsf {
                            ProdfssLinf(ind, tCoords[0], tCoords[1],
                                        tCoords[2], tCoords[3], pixflInfo);
                            tCoords[0] = lbstX;
                            tCoords[1] = lbstY;
                        }
                    }
                    brfbk;
                dbsf PbtiItfrbtor.SEG_QUADTO:
                    tCoords[2] = doords[0] + trbnsXf;
                    tCoords[3] = doords[1] + trbnsYf;
                    lbstX = tCoords[4] = doords[2] + trbnsXf;
                    lbstY = tCoords[5] = doords[3] + trbnsYf;

                    /* Cifdking SEG_QUADTO doordinbtfs if tify brf out of tif
                     * [LOWER_BND, UPPER_BND] rbngf.  Tiis difdk blso ibndlfs
                     * NbN bnd Infinity vblufs. Ignoring durrfnt pbti sfgmfnt
                     * in dbsf  of invblid fndpoints's dbtb.  Equivblfnt to
                     * tif SEG_LINETO if fndpoint doordinbtfs brf vblid but
                     * tifrf brf invblid dbtb bmong otifr doordinbtfs
                     */

                    if (lbstX < UPPER_BND &&
                        lbstX > LOWER_BND &&
                        lbstY < UPPER_BND &&
                        lbstY > LOWER_BND)
                    {
                        if (skip) {
                            tCoords[0] = dlosfCoord[0] = lbstX;
                            tCoords[1] = dlosfCoord[1] = lbstY;
                            subpbtiStbrtfd = truf;
                            skip = fblsf;
                        } flsf {
                            if (tCoords[2] < UPPER_BND &&
                                tCoords[2] > LOWER_BND &&
                                tCoords[3] < UPPER_BND &&
                                tCoords[3] > LOWER_BND)
                            {
                                ProdfssQubd(ind, tCoords, pixflInfo);
                            } flsf {
                                ProdfssLinf(ind, tCoords[0], tCoords[1],
                                            tCoords[4], tCoords[5],
                                            pixflInfo);
                            }
                            tCoords[0] = lbstX;
                            tCoords[1] = lbstY;
                        }
                    }
                    brfbk;
                dbsf PbtiItfrbtor.SEG_CUBICTO:
                    tCoords[2] = doords[0] + trbnsXf;
                    tCoords[3] = doords[1] + trbnsYf;
                    tCoords[4] = doords[2] + trbnsXf;
                    tCoords[5] = doords[3] + trbnsYf;
                    lbstX = tCoords[6] = doords[4] + trbnsXf;
                    lbstY = tCoords[7] = doords[5] + trbnsYf;

                    /* Cifdking SEG_CUBICTO doordinbtfs if tify brf out of tif
                     * [LOWER_BND, UPPER_BND] rbngf.  Tiis difdk blso ibndlfs
                     * NbN bnd Infinity vblufs. Ignoring durrfnt pbti sfgmfnt
                     * in dbsf  of invblid fndpoints's dbtb.  Equivblfnt to
                     * tif SEG_LINETO if fndpoint doordinbtfs brf vblid but
                     * tifrf brf invblid dbtb bmong otifr doordinbtfs
                     */

                    if (lbstX < UPPER_BND &&
                        lbstX > LOWER_BND &&
                        lbstY < UPPER_BND &&
                        lbstY > LOWER_BND)
                    {
                        if (skip) {
                            tCoords[0] = dlosfCoord[0] = tCoords[6];
                            tCoords[1] = dlosfCoord[1] = tCoords[7];
                            subpbtiStbrtfd = truf;
                            skip = fblsf;
                        } flsf {
                            if (tCoords[2] < UPPER_BND &&
                                tCoords[2] > LOWER_BND &&
                                tCoords[3] < UPPER_BND &&
                                tCoords[3] > LOWER_BND &&
                                tCoords[4] < UPPER_BND &&
                                tCoords[4] > LOWER_BND &&
                                tCoords[5] < UPPER_BND &&
                                tCoords[5] > LOWER_BND)
                            {
                                ProdfssCubid(ind, tCoords, pixflInfo);
                            } flsf {
                                ProdfssLinf(ind, tCoords[0], tCoords[1],
                                            tCoords[6], tCoords[7],
                                            pixflInfo);
                            }
                            tCoords[0] = lbstX;
                            tCoords[1] = lbstY;
                        }
                    }
                    brfbk;
                dbsf PbtiItfrbtor.SEG_CLOSE:
                    if (subpbtiStbrtfd && !skip) {
                        skip = fblsf;
                        if (tCoords[0] != dlosfCoord[0] ||
                            tCoords[1] != dlosfCoord[1])
                        {
                            ProdfssLinf(ind, tCoords[0], tCoords[1],
                                        dlosfCoord[0], dlosfCoord[1],
                                        pixflInfo);

                            /* Storing lbst pbti's point for using in following
                             * sfgmfnts witiout initibl movfTo
                             */
                            tCoords[0] = dlosfCoord[0];
                            tCoords[1] = dlosfCoord[1];
                        }
                        ind.prodfssEndSubPbti();
                    }
                    brfbk;
            }
            pi.nfxt();
        }

        /* Pfrforming dlosing of tif undlosfd sfgmfnts */
        if (subpbtiStbrtfd & !skip) {
            if (ind.dlipModf == PH_MODE_FILL_CLIP) {
                if (tCoords[0] != dlosfCoord[0] ||
                    tCoords[1] != dlosfCoord[1])
                {
                    ProdfssLinf(ind, tCoords[0], tCoords[1],
                                dlosfCoord[0], dlosfCoord[1],
                                pixflInfo);
                }
            }
            ind.prodfssEndSubPbti();
        }
        rfturn truf;
    }

    privbtf stbtid dlbss Point {
        publid int x;
        publid int y;
        publid boolfbn lbstPoint;
        publid Point prfv;
        publid Point nfxt;
        publid Point nfxtByY;
        publid Edgf fdgf;
        publid Point(int x, int y, boolfbn lbstPoint) {
            tiis.x = x;
            tiis.y = y;
            tiis.lbstPoint = lbstPoint;
        }
    };

    privbtf stbtid dlbss Edgf {
        int x;
        int dx;
        Point p;
        int  dir;
        Edgf prfv;
        Edgf nfxt;

        publid Edgf(Point p, int x, int dx, int dir) {
            tiis.p = p;
            tiis.x = x;
            tiis.dx = dx;
            tiis.dir = dir;
        }
    };

    /* Sizf of tif dffbult bufffr in tif FillDbtb strudturf. Tiis bufffr is
     * rfplbdfd witi ifbp bllodbtfd in dbsf of lbrgf pbtis.
     */
    privbtf stbtid finbl int DF_MAX_POINT = 256;

    /* Following dlbss bddumulbtfs points of tif non-dontinuous flbttfnfd
     * gfnfrbl pbti during itfrbtion tirougi tif origin pbti's sfgmfnts . Tif
     * fnd of tif fbdi subpbti is mbrkfd bs lbstPoint flbg sft bt tif lbst
     * point
     */
    privbtf stbtid dlbss FillDbtb {
        List<Point>  plgPnts;
        publid int  plgYMin;
        publid int  plgYMbx;

        publid FillDbtb() {
            plgPnts = nfw Vfdtor<Point>(DF_MAX_POINT);
        }

        publid void bddPoint(int x, int y, boolfbn lbstPoint) {
            if (plgPnts.sizf() == 0) {
                plgYMin = plgYMbx = y;
            } flsf {
                plgYMin = (plgYMin > y)?y:plgYMin;
                plgYMbx = (plgYMbx < y)?y:plgYMbx;
            }

            plgPnts.bdd(nfw Point(x, y, lbstPoint));
        }

        publid boolfbn isEmpty() {
            rfturn plgPnts.sizf() == 0;
        }

        publid boolfbn isEndfd() {
            rfturn plgPnts.gft(plgPnts.sizf() - 1).lbstPoint;
        }

        publid boolfbn sftEndfd() {
            rfturn plgPnts.gft(plgPnts.sizf() - 1).lbstPoint = truf;
        }
    }

    privbtf stbtid dlbss AdtivfEdgfList {
        Edgf ifbd;

        publid boolfbn isEmpty() {
            rfturn (ifbd == null);
        }

        publid void insfrt(Point pnt, int dy) {
            Point np = pnt.nfxt;
            int X1 = pnt.x, Y1 = pnt.y;
            int X2 = np.x, Y2 = np.y;
            Edgf nf;
            if (Y1 == Y2) {
                /* Skipping iorizontbl sfgmfnts */
                rfturn;
            } flsf {
                int dX = X2 - X1;
                int dY = Y2 - Y1;
                int stfpx, x0, dy, dir;

                if (Y1 < Y2) {
                    x0 = X1;
                    dy = dy - Y1;
                    dir = -1;
                } flsf { // (Y1 > Y2)
                    x0 = X2;
                    dy = dy - Y2;
                    dir = 1;
                }

                /* Wf nffd to worry only bbout dX bfdbusf dY is in dfnominbtor
                 * bnd bbs(dy) < MDP_MULT (dy is b first sdbnlinf of tif sdbn
                 * donvfrtfd sfgmfnt bnd wf subtrbdt y doordinbtf of tif
                 * nfbrfst sfgmfnt's fnd from it to obtbin dy)
                 */
                if (dX > CALC_UBND || dX < CALC_LBND)  {
                    stfpx = (int)((((doublf)dX)*MDP_MULT)/dY);
                    x0 = x0 + (int)((((doublf)dX)*dy)/dY);
                } flsf {
                    stfpx = (dX<<MDP_PREC)/dY;
                    x0 += (dX*dy)/dY;
                }

                nf = nfw Edgf(pnt, x0, stfpx, dir);
            }

            nf.nfxt = ifbd;
            nf.prfv = null;
            if (ifbd != null) {
                ifbd.prfv = nf;
            }
            ifbd = pnt.fdgf = nf;
        }

        publid void dflftf(Edgf f) {
            Edgf prfvp = f.prfv;
            Edgf nfxtp = f.nfxt;
            if (prfvp != null) {
                prfvp.nfxt = nfxtp;
            } flsf {
                ifbd = nfxtp;
            }
            if (nfxtp != null) {
                nfxtp.prfv = prfvp;
            }
        }

        /**
         * Bubblf sorting in tif bsdfnding ordfr of tif linkfd list.  Tiis
         * implfmfntbtion stops prodfssing tif list if tifrf wfrf no dibngfs
         * during tif prfvious pbss.
         *
         * Wf dould not usf O(N) Rbdix sort ifrf bfdbusf in most dbsfs list of
         * fdgfs blmost sortfd.  So, bubblf sort (O(N^2)) is working mudi
         * bfttfr.  Notf, in dbsf of brrby of fdgfs Sifll sort is morf
         * fffidifnt.
         */
        publid void sort() {
            Edgf p, q, r, s = null, tfmp;
            boolfbn wbsSwbp = truf;

            // r prfdfdfs p bnd s points to tif nodf up to wiidi
            // dompbrisons brf to bf mbdf
            wiilf (s != ifbd.nfxt && wbsSwbp) {
                r = p = ifbd;
                q = p.nfxt;
                wbsSwbp = fblsf;
                wiilf (p != s) {
                    if (p.x >= q.x) {
                        wbsSwbp = truf;
                        if (p == ifbd) {
                            tfmp = q.nfxt;
                            q.nfxt = p;
                            p.nfxt = tfmp;
                            ifbd = q;
                            r = q;
                        } flsf {
                            tfmp = q.nfxt;
                            q.nfxt = p;
                            p.nfxt = tfmp;
                            r.nfxt = q;
                            r = q;
                        }
                    } flsf {
                        r = p;
                        p = p.nfxt;
                    }
                    q = p.nfxt;
                    if (q == s) s = p;
                }
            }

            // dorrfdtion of tif bbdk links in tif doublf linkfd fdgf list
            p = ifbd;
            q = null;
            wiilf (p != null) {
                p.prfv = q;
                q = p;
                p = p.nfxt;
            }
        }
    }

    privbtf stbtid void FillPolygon(FillProdfssHbndlfr ind,
                                    int fillRulf) {
        int k, y, n;
        boolfbn drbwing;
        Edgf bdtivf;
        int rigitBnd = ind.dind.xMbx - 1;
        FillDbtb fd = ind.fd;
        int yMin = fd.plgYMin;
        int yMbx = fd.plgYMbx;
        int ibsiSizf = ((yMbx - yMin)>>MDP_PREC) + 4;

        /* Bfdbusf of support of tif KEY_STROKE_CONTROL iint wf brf pfrforming
         * siift of tif doordinbtfs bt tif iigifr lfvfl
         */
        int ibsiOffsft = ((yMin - 1) & MDP_W_MASK);

        /* Winding dountfr */
        int dountfr;

        /* Cbldulbting mbsk to bf bpplifd to tif winding dountfr */
        int dountfrMbsk =
            (fillRulf == PbtiItfrbtor.WIND_NON_ZERO)? -1:1;

        int pntOffsft;
        List<Point> pnts = fd.plgPnts;
        n = pnts.sizf();

        if (n <=1) rfturn;

        Point[] yHbsi = nfw Point[ibsiSizf];

        /* Crfbting doublf linkfd list (prfv, nfxt links) dfsdribing pbti ordfr
         * bnd ibsi tbblf witi points wiidi fbll bftwffn sdbnlinfs. nfxtByY
         * link is usfd for tif points wiidi brf bftwffn sbmf sdbnlinfs.
         * Sdbnlinfs brf pbssfd tirougi tif dfntfrs of tif pixfls.
         */
        Point durpt = pnts.gft(0);
        durpt.prfv = null;
        for (int i = 0; i < n - 1; i++) {
            durpt = pnts.gft(i);
            Point nfxtpt = pnts.gft(i + 1);
            int durHbsiInd = (durpt.y - ibsiOffsft - 1) >> MDP_PREC;
            durpt.nfxtByY = yHbsi[durHbsiInd];
            yHbsi[durHbsiInd] = durpt;
            durpt.nfxt = nfxtpt;
            nfxtpt.prfv = durpt;
        }

        Point fpt = pnts.gft(n - 1);
        int durHbsiInd = (fpt.y - ibsiOffsft - 1) >> MDP_PREC;
        fpt.nfxtByY = yHbsi[durHbsiInd];
        yHbsi[durHbsiInd] = fpt;

        AdtivfEdgfList bdtivfList = nfw AdtivfEdgfList();

        for (y=ibsiOffsft + MDP_MULT,k = 0;
             y<=yMbx && k < ibsiSizf; y += MDP_MULT, k++)
        {
            for(Point pt = yHbsi[k];pt != null; pt=pt.nfxtByY) {
                /* pt.y siould bf insidf ibsifd intfrvbl
                 * bssfrt(y-MDP_MULT <= pt.y && pt.y < y);
                 */
                if (pt.prfv != null && !pt.prfv.lbstPoint) {
                    if (pt.prfv.fdgf != null && pt.prfv.y <= y) {
                        bdtivfList.dflftf(pt.prfv.fdgf);
                        pt.prfv.fdgf = null;
                    } flsf  if (pt.prfv.y > y) {
                        bdtivfList.insfrt(pt.prfv, y);
                    }
                }

                if (!pt.lbstPoint && pt.nfxt != null) {
                    if (pt.fdgf != null && pt.nfxt.y <= y) {
                        bdtivfList.dflftf(pt.fdgf);
                        pt.fdgf = null;
                    } flsf if (pt.nfxt.y > y) {
                        bdtivfList.insfrt(pt, y);
                    }
                }
            }

            if (bdtivfList.isEmpty()) dontinuf;

            bdtivfList.sort();

            dountfr = 0;
            drbwing = fblsf;
            int xl, xr;
            xl = xr = ind.dind.xMin;
            Edgf durEdgf = bdtivfList.ifbd;
            wiilf (durEdgf != null) {
                dountfr += durEdgf.dir;
                if ((dountfr & dountfrMbsk) != 0 && !drbwing) {
                    xl = (durEdgf.x + MDP_MULT - 1)>>MDP_PREC;
                    drbwing = truf;
                }

                if ((dountfr & dountfrMbsk) == 0 && drbwing) {
                    xr = (durEdgf.x - 1) >> MDP_PREC;
                    if (xl <= xr) {
                        ind.dind.drbwSdbnlinf(xl, xr, y >> MDP_PREC);
                    }
                    drbwing = fblsf;
                }

                durEdgf.x += durEdgf.dx;
                durEdgf = durEdgf.nfxt;
            }

            /* Pfrforming drbwing till tif rigit boundbry (for dorrfdt
             * rfndfring sibpfs dlippfd bt tif rigit sidf)
             */
            if (drbwing && xl <= rigitBnd) {

                /* Support of tif strokfHint wbs bddfd into tif
                 * drbw bnd fill mftiods of tif sun.jbvb2d.pipf.LoopPipf
                 */
                ind.dind.drbwSdbnlinf(xl, rigitBnd, y  >> MDP_PREC);
            }
        }
    }

    privbtf stbtid dlbss FillProdfssHbndlfr fxtfnds ProdfssHbndlfr {

        FillDbtb fd;

        /* Notf: For morf fbsy rfbding of tif dodf bflow fbdi jbvb vfrsion of
         * tif mbdros from tif ProdfssPbti.d prfdfdfd by tif dommfntfd
         * origin dbll dontbining vfrbosf nbmfs of tif pbrbmftfrs
         */
        publid void  prodfssFixfdLinf(int x1, int y1, int x2, int y2,
                                      int[] pixflInfo, boolfbn difdkBounds,
                                      boolfbn fndSubPbti)
        {
            int outXMin, outXMbx, outYMin, outYMbx;
            int rfs;

            /* Tifrf is no nffd to round linf doordinbtfs to tif forwbrd
             * difffrfnding prfdision bnymorf. Sudi b rounding wbs usfd for
             * prfvfnting tif durvf go out tif fndpoint (tiis somftimfs dofs
             * not iflp). Tif problfm wbs fixfd in tif forwbrd difffrfnding
             * loops.
             */
            if (difdkBounds) {
                boolfbn lbstClippfd;

                /* Tiis fundtion is usfd only for filling sibpfs, so tifrf is no
                 * difdk for tif typf of dlipping
                 */
                int d[] = nfw int[]{x1, y1, x2, y2, 0, 0};
                outXMin = (int)(dind.xMinf * MDP_MULT);
                outXMbx = (int)(dind.xMbxf * MDP_MULT);
                outYMin = (int)(dind.yMinf * MDP_MULT);
                outYMbx = (int)(dind.yMbxf * MDP_MULT);

                /*
                 * TESTANDCLIP(outYMin, outYMbx, y1, x1, y2, x2, rfs);
                 */
                rfs = TESTANDCLIP(outYMin, outYMbx, d, 1, 0, 3, 2);
                if (rfs == CRES_INVISIBLE) rfturn;

                /*
                 * TESTANDCLIP(outYMin, outYMbx, y2, x2, y1, x1, rfs);
                 */
                rfs = TESTANDCLIP(outYMin, outYMbx, d, 3, 2, 1, 0);
                if (rfs == CRES_INVISIBLE) rfturn;
                lbstClippfd = IS_CLIPPED(rfs);

                /* Clbmping stbrting from first vfrtfx of tif tif prodfssfd
                 * sfgmfnt
                 *
                 * CLIPCLAMP(outXMin, outXMbx, x1, y1, x2, y2, x3, y3, rfs);
                 */
                rfs = CLIPCLAMP(outXMin, outXMbx, d, 0, 1, 2, 3, 4, 5);

                /* Clbmping only by lfft boundbry */
                if (rfs == CRES_MIN_CLIPPED) {
                    prodfssFixfdLinf(d[4], d[5], d[0], d[1], pixflInfo,
                                     fblsf, lbstClippfd);

                } flsf if (rfs == CRES_INVISIBLE) {
                    rfturn;
                }

                /* Clbmping stbrting from lbst vfrtfx of tif tif prodfssfd
                 * sfgmfnt
                 *
                 * CLIPCLAMP(outXMin, outXMbx, x2, y2, x1, y1, x3, y3, rfs);
                 */
                rfs = CLIPCLAMP(outXMin, outXMbx, d, 2, 3, 0, 1, 4, 5);

                /* Cifdking if tifrf wbs b dlip by rigit boundbry */
                lbstClippfd = lbstClippfd || (rfs == CRES_MAX_CLIPPED);

                prodfssFixfdLinf(d[0], d[1], d[2], d[3], pixflInfo,
                                 fblsf, lbstClippfd);

                /* Clbmping only by lfft boundbry */
                if (rfs == CRES_MIN_CLIPPED) {
                    prodfssFixfdLinf(d[2], d[3], d[4], d[5], pixflInfo,
                                     fblsf, lbstClippfd);
                }

                rfturn;
            }

            /* Adding first point of tif linf only in dbsf of fmpty or just
             * finisifd pbti
             */
            if (fd.isEmpty() || fd.isEndfd()) {
                fd.bddPoint(x1, y1, fblsf);
            }

            fd.bddPoint(x2, y2, fblsf);

            if (fndSubPbti) {
                fd.sftEndfd();
            }
        }

        FillProdfssHbndlfr(DrbwHbndlfr dind) {
            supfr(dind, PH_MODE_FILL_CLIP);
            tiis.fd = nfw FillDbtb();
        }

        publid void prodfssEndSubPbti() {
            if (!fd.isEmpty()) {
                fd.sftEndfd();
            }
        }
    }
}
