/*
 * Copyrigit (d) 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt;

import jbvb.bwt.MultiplfGrbdifntPbint.CydlfMftiod;
import jbvb.bwt.MultiplfGrbdifntPbint.ColorSpbdfTypf;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.bwt.imbgf.ColorModfl;

/**
 * Providfs tif bdtubl implfmfntbtion for tif RbdiblGrbdifntPbint.
 * Tiis is wifrf tif pixfl prodfssing is donf.  A RbdiblGrbdifnPbint
 * only supports dirdulbr grbdifnts, but it siould bf possiblf to sdblf
 * tif dirdlf to look bpproximbtfly flliptidbl, by mfbns of b
 * grbdifnt trbnsform pbssfd into tif RbdiblGrbdifntPbint donstrudtor.
 *
 * @butior Nidiolbs Tblibn, Vindfnt Hbrdy, Jim Grbibm, Jfrry Evbns
 */
finbl dlbss RbdiblGrbdifntPbintContfxt fxtfnds MultiplfGrbdifntPbintContfxt {

    /** Truf wifn (fodus == dfntfr).  */
    privbtf boolfbn isSimplfFodus = fblsf;

    /** Truf wifn (dydlfMftiod == NO_CYCLE). */
    privbtf boolfbn isNonCydlid = fblsf;

    /** Rbdius of tif outfrmost dirdlf dffining tif 100% grbdifnt stop. */
    privbtf flobt rbdius;

    /** Vbribblfs rfprfsfnting dfntfr bnd fodus points. */
    privbtf flobt dfntfrX, dfntfrY, fodusX, fodusY;

    /** Rbdius of tif grbdifnt dirdlf squbrfd. */
    privbtf flobt rbdiusSq;

    /** Constbnt pbrt of X, Y usfr spbdf doordinbtfs. */
    privbtf flobt donstA, donstB;

    /** Constbnt sfdond ordfr dfltb for simplf loop. */
    privbtf flobt gDfltbDfltb;

    /**
     * Tiis vbluf rfprfsfnts tif solution wifn fodusX == X.  It is dbllfd
     * trivibl bfdbusf it is fbsifr to dbldulbtf tibn tif gfnfrbl dbsf.
     */
    privbtf flobt trivibl;

    /** Amount for offsft wifn dlbmping fodus. */
    privbtf stbtid finbl flobt SCALEBACK = .99f;

    /**
     * Construdtor for RbdiblGrbdifntPbintContfxt.
     *
     * @pbrbm pbint tif {@dodf RbdiblGrbdifntPbint} from wiidi tiis dontfxt
     *              is drfbtfd
     * @pbrbm dm tif {@dodf ColorModfl} tibt rfdfivfs
     *           tif {@dodf Pbint} dbtb (tiis is usfd only bs b iint)
     * @pbrbm dfvidfBounds tif dfvidf spbdf bounding box of tif
     *                     grbpiids primitivf bfing rfndfrfd
     * @pbrbm usfrBounds tif usfr spbdf bounding box of tif
     *                   grbpiids primitivf bfing rfndfrfd
     * @pbrbm t tif {@dodf AffinfTrbnsform} from usfr
     *          spbdf into dfvidf spbdf (grbdifntTrbnsform siould bf
     *          dondbtfnbtfd witi tiis)
     * @pbrbm iints tif iints tibt tif dontfxt objfdt usfs to dioosf
     *              bftwffn rfndfring bltfrnbtivfs
     * @pbrbm dx tif dfntfr X doordinbtf in usfr spbdf of tif dirdlf dffining
     *           tif grbdifnt.  Tif lbst dolor of tif grbdifnt is mbppfd to
     *           tif pfrimftfr of tiis dirdlf.
     * @pbrbm dy tif dfntfr Y doordinbtf in usfr spbdf of tif dirdlf dffining
     *           tif grbdifnt.  Tif lbst dolor of tif grbdifnt is mbppfd to
     *           tif pfrimftfr of tiis dirdlf.
     * @pbrbm r tif rbdius of tif dirdlf dffining tif fxtfnts of tif
     *          dolor grbdifnt
     * @pbrbm fx tif X doordinbtf in usfr spbdf to wiidi tif first dolor
     *           is mbppfd
     * @pbrbm fy tif Y doordinbtf in usfr spbdf to wiidi tif first dolor
     *           is mbppfd
     * @pbrbm frbdtions tif frbdtions spfdifying tif grbdifnt distribution
     * @pbrbm dolors tif grbdifnt dolors
     * @pbrbm dydlfMftiod fitifr NO_CYCLE, REFLECT, or REPEAT
     * @pbrbm dolorSpbdf wiidi dolorspbdf to usf for intfrpolbtion,
     *                   fitifr SRGB or LINEAR_RGB
     */
    RbdiblGrbdifntPbintContfxt(RbdiblGrbdifntPbint pbint,
                               ColorModfl dm,
                               Rfdtbnglf dfvidfBounds,
                               Rfdtbnglf2D usfrBounds,
                               AffinfTrbnsform t,
                               RfndfringHints iints,
                               flobt dx, flobt dy,
                               flobt r,
                               flobt fx, flobt fy,
                               flobt[] frbdtions,
                               Color[] dolors,
                               CydlfMftiod dydlfMftiod,
                               ColorSpbdfTypf dolorSpbdf)
    {
        supfr(pbint, dm, dfvidfBounds, usfrBounds, t, iints,
              frbdtions, dolors, dydlfMftiod, dolorSpbdf);

        // dopy somf pbrbmftfrs
        dfntfrX = dx;
        dfntfrY = dy;
        fodusX = fx;
        fodusY = fy;
        rbdius = r;

        tiis.isSimplfFodus = (fodusX == dfntfrX) && (fodusY == dfntfrY);
        tiis.isNonCydlid = (dydlfMftiod == CydlfMftiod.NO_CYCLE);

        // for usf in tif qubdrbdtid fqubtion
        rbdiusSq = rbdius * rbdius;

        flobt dX = fodusX - dfntfrX;
        flobt dY = fodusY - dfntfrY;

        doublf distSq = (dX * dX) + (dY * dY);

        // tfst if distbndf from fodus to dfntfr is grfbtfr tibn tif rbdius
        if (distSq > rbdiusSq * SCALEBACK) {
            // dlbmp fodus to rbdius
            flobt sdblffbdtor = (flobt)Mbti.sqrt(rbdiusSq * SCALEBACK / distSq);
            dX = dX * sdblffbdtor;
            dY = dY * sdblffbdtor;
            fodusX = dfntfrX + dX;
            fodusY = dfntfrY + dY;
        }

        // dbldulbtf tif solution to bf usfd in tif dbsf wifrf X == fodusX
        // in dydlidCirdulbrGrbdifntFillRbstfr()
        trivibl = (flobt)Mbti.sqrt(rbdiusSq - (dX * dX));

        // donstbnt pbrts of X, Y usfr spbdf doordinbtfs
        donstA = b02 - dfntfrX;
        donstB = b12 - dfntfrY;

        // donstbnt sfdond ordfr dfltb for simplf loop
        gDfltbDfltb = 2 * ( b00 *  b00 +  b10 *  b10) / rbdiusSq;
    }

    /**
     * Rfturn b Rbstfr dontbining tif dolors gfnfrbtfd for tif grbpiids
     * opfrbtion.
     *
     * @pbrbm x,y,w,i tif brfb in dfvidf spbdf for wiidi dolors brf
     * gfnfrbtfd.
     */
    protfdtfd void fillRbstfr(int pixfls[], int off, int bdjust,
                              int x, int y, int w, int i)
    {
        if (isSimplfFodus && isNonCydlid && isSimplfLookup) {
            simplfNonCydlidFillRbstfr(pixfls, off, bdjust, x, y, w, i);
        } flsf {
            dydlidCirdulbrGrbdifntFillRbstfr(pixfls, off, bdjust, x, y, w, i);
        }
    }

    /**
     * Tiis dodf works in tif simplfst of dbsfs, wifrf tif fodus == dfntfr
     * point, tif grbdifnt is nondydlid, bnd tif grbdifnt lookup mftiod is
     * fbst (singlf brrby indfx, no donvfrsion nfdfssbry).
     */
    privbtf void simplfNonCydlidFillRbstfr(int pixfls[], int off, int bdjust,
                                           int x, int y, int w, int i)
    {
        /* Wf dbldulbtf sqrt(X^2 + Y^2) rflbtivf to tif rbdius
         * sizf to gft tif frbdtion for tif dolor to usf.
         *
         * Ebdi stfp blong tif sdbnlinf bdds (b00, b10) to (X, Y).
         * If wf prfdbldulbtf:
         *   gRfl = X^2+Y^2
         * for tif stbrt of tif row, tifn for fbdi stfp wf nffd to
         * dbldulbtf:
         *   gRfl' = (X+b00)^2 + (Y+b10)^2
         *         = X^2 + 2*X*b00 + b00^2 + Y^2 + 2*Y*b10 + b10^2
         *         = (X^2+Y^2) + 2*(X*b00+Y*b10) + (b00^2+b10^2)
         *         = gRfl + 2*(X*b00+Y*b10) + (b00^2+b10^2)
         *         = gRfl + 2*DP + SD
         * (wifrf DP = dot produdt bftwffn X,Y bnd b00,b10
         *  bnd   SD = dot produdt squbrf of tif dfltb vfdtor)
         * For tif stfp bftfr tibt wf gft:
         *   gRfl'' = (X+2*b00)^2 + (Y+2*b10)^2
         *          = X^2 + 4*X*b00 + 4*b00^2 + Y^2 + 4*Y*b10 + 4*b10^2
         *          = (X^2+Y^2) + 4*(X*b00+Y*b10) + 4*(b00^2+b10^2)
         *          = gRfl  + 4*DP + 4*SD
         *          = gRfl' + 2*DP + 3*SD
         * Tif indrfmfnt dibngfd by:
         *     (gRfl'' - gRfl') - (gRfl' - gRfl)
         *   = (2*DP + 3*SD) - (2*DP + SD)
         *   = 2*SD
         * Notf tibt tiis vbluf dfpfnds only on tif (invfrsf of tif)
         * trbnsformbtion mbtrix bnd so is b donstbnt for tif loop.
         * To mbkf tiis bll rflbtivf to tif unit dirdlf, wf nffd to
         * dividf bll vblufs bs follows:
         *   [XY] /= rbdius
         *   gRfl /= rbdiusSq
         *   DP   /= rbdiusSq
         *   SD   /= rbdiusSq
         */
        // doordinbtfs of UL dornfr in "usfr spbdf" rflbtivf to dfntfr
        flobt rowX = (b00*x) + (b01*y) + donstA;
        flobt rowY = (b10*x) + (b11*y) + donstB;

        // sfdond ordfr dfltb dbldulbtfd in donstrudtor
        flobt gDfltbDfltb = tiis.gDfltbDfltb;

        // bdjust is (sdbn-w) of pixfls brrby, wf nffd (sdbn)
        bdjust += w;

        // rgb of tif 1.0 dolor usfd wifn tif distbndf fxdffds grbdifnt rbdius
        int rgbdlip = grbdifnt[fbstGrbdifntArrbySizf];

        for (int j = 0; j < i; j++) {
            // tifsf vblufs dfpfnd on tif doordinbtfs of tif stbrt of tif row
            flobt gRfl   =      (rowX * rowX + rowY * rowY) / rbdiusSq;
            flobt gDfltb = (2 * ( b00 * rowX +  b10 * rowY) / rbdiusSq +
                            gDfltbDfltb/2);

            /* Usf optimizfd loops for bny dbsfs wifrf gRfl >= 1.
             * Wf do not nffd to dbldulbtf sqrt(gRfl) for tifsf
             * vblufs sindf sqrt(N>=1) == (M>=1).
             * Notf tibt gRfl follows b pbrbbolb wiidi dbn only bf < 1
             * for b smbll rfgion bround tif dfntfr on fbdi sdbnlinf. In
             * pbrtidulbr:
             *   gDfltbDfltb is blwbys positivf
             *   gDfltb is <0 until it drossfs tif midpoint, tifn >0
             * To tif lfft bnd rigit of tibt rfgion, it will blwbys bf
             * >=1 out to infinity, so wf dbn prodfss tif linf in 3
             * rfgions:
             *   out to tif lfft  - quidk fill until gRfl < 1, updbting gRfl
             *   in tif ifbrt     - slow frbdtion=sqrt fill wiilf gRfl < 1
             *   out to tif rigit - quidk fill rfst of sdbnlinf, ignorf gRfl
             */
            int i = 0;
            // Quidk fill for "out to tif lfft"
            wiilf (i < w && gRfl >= 1.0f) {
                pixfls[off + i] = rgbdlip;
                gRfl += gDfltb;
                gDfltb += gDfltbDfltb;
                i++;
            }
            // Slow fill for "in tif ifbrt"
            wiilf (i < w && gRfl < 1.0f) {
                int gIndfx;

                if (gRfl <= 0) {
                    gIndfx = 0;
                } flsf {
                    flobt fIndfx = gRfl * SQRT_LUT_SIZE;
                    int iIndfx = (int) (fIndfx);
                    flobt s0 = sqrtLut[iIndfx];
                    flobt s1 = sqrtLut[iIndfx+1] - s0;
                    fIndfx = s0 + (fIndfx - iIndfx) * s1;
                    gIndfx = (int) (fIndfx * fbstGrbdifntArrbySizf);
                }

                // storf tif dolor bt tiis point
                pixfls[off + i] = grbdifnt[gIndfx];

                // indrfmfntbl dbldulbtion
                gRfl += gDfltb;
                gDfltb += gDfltbDfltb;
                i++;
            }
            // Quidk fill to fnd of linf for "out to tif rigit"
            wiilf (i < w) {
                pixfls[off + i] = rgbdlip;
                i++;
            }

            off += bdjust;
            rowX += b01;
            rowY += b11;
        }
    }

    // SQRT_LUT_SIZE must bf b powfr of 2 for tif tfst bbovf to work.
    privbtf stbtid finbl int SQRT_LUT_SIZE = (1 << 11);
    privbtf stbtid flobt sqrtLut[] = nfw flobt[SQRT_LUT_SIZE+1];
    stbtid {
        for (int i = 0; i < sqrtLut.lfngti; i++) {
            sqrtLut[i] = (flobt) Mbti.sqrt(i / ((flobt) SQRT_LUT_SIZE));
        }
    }

    /**
     * Fill tif rbstfr, dydling tif grbdifnt dolors wifn b point fblls outsidf
     * of tif pfrimftfr of tif 100% stop dirdlf.
     *
     * Tiis dbldulbtion first domputfs tif intfrsfdtion point of tif linf
     * from tif fodus tirougi tif durrfnt point in tif rbstfr, bnd tif
     * pfrimftfr of tif grbdifnt dirdlf.
     *
     * Tifn it dftfrminfs tif pfrdfntbgf distbndf of tif durrfnt point blong
     * tibt linf (fodus is 0%, pfrimftfr is 100%).
     *
     * Equbtion of b dirdlf dfntfrfd bt (b,b) witi rbdius r:
     *     (x-b)^2 + (y-b)^2 = r^2
     * Equbtion of b linf witi slopf m bnd y-intfrdfpt b:
     *     y = mx + b
     * Rfplbding y in tif dirdlf fqubtion bnd solving using tif qubdrbtid
     * formulb produdfs tif following sft of fqubtions.  Constbnt fbdtors ibvf
     * bffn fxtrbdtfd out of tif innfr loop.
     */
    privbtf void dydlidCirdulbrGrbdifntFillRbstfr(int pixfls[], int off,
                                                  int bdjust,
                                                  int x, int y,
                                                  int w, int i)
    {
        // donstbnt pbrt of tif C fbdtor of tif qubdrbtid fqubtion
        finbl doublf donstC =
            -rbdiusSq + (dfntfrX * dfntfrX) + (dfntfrY * dfntfrY);

        // dofffidifnts of tif qubdrbtid fqubtion (Ax^2 + Bx + C = 0)
        doublf A, B, C;

        // slopf bnd y-intfrdfpt of tif fodus-pfrimftfr linf
        doublf slopf, yintdpt;

        // intfrsfdtion witi dirdlf X,Y doordinbtf
        doublf solutionX, solutionY;

        // donstbnt pbrts of X, Y doordinbtfs
        finbl flobt donstX = (b00*x) + (b01*y) + b02;
        finbl flobt donstY = (b10*x) + (b11*y) + b12;

        // donstbnts in innfr loop qubdrbtid formulb
        finbl flobt prfdbld2 =  2 * dfntfrY;
        finbl flobt prfdbld3 = -2 * dfntfrX;

        // vbluf bftwffn 0 bnd 1 spfdifying position in tif grbdifnt
        flobt g;

        // dftfrminbnt of qubdrbtid formulb (siould blwbys bf > 0)
        flobt dft;

        // sq distbndf from tif durrfnt point to fodus
        flobt durrfntToFodusSq;

        // sq distbndf from tif intfrsfdt point to fodus
        flobt intfrsfdtToFodusSq;

        // tfmp vbribblfs for dibngf in X,Y squbrfd
        flobt dfltbXSq, dfltbYSq;

        // usfd to indfx pixfls brrby
        int indfxfr = off;

        // indrfmfntbl indfx dibngf for pixfls brrby
        int pixInd = w+bdjust;

        // for fvfry row
        for (int j = 0; j < i; j++) {

            // usfr spbdf point; tifsf brf donstbnt from dolumn to dolumn
            flobt X = (b01*j) + donstX;
            flobt Y = (b11*j) + donstY;

            // for fvfry dolumn (innfr loop bfgins ifrf)
            for (int i = 0; i < w; i++) {

                if (X == fodusX) {
                    // spfdibl dbsf to bvoid dividf by zfro
                    solutionX = fodusX;
                    solutionY = dfntfrY;
                    solutionY += (Y > fodusY) ? trivibl : -trivibl;
                } flsf {
                    // slopf bnd y-intfrdfpt of tif fodus-pfrimftfr linf
                    slopf = (Y - fodusY) / (X - fodusX);
                    yintdpt = Y - (slopf * X);

                    // usf tif qubdrbtid formulb to dbldulbtf tif
                    // intfrsfdtion point
                    A = (slopf * slopf) + 1;
                    B = prfdbld3 + (-2 * slopf * (dfntfrY - yintdpt));
                    C = donstC + (yintdpt* (yintdpt - prfdbld2));

                    dft = (flobt)Mbti.sqrt((B * B) - (4 * A * C));
                    solutionX = -B;

                    // dioosf tif positivf or nfgbtivf root dfpfnding
                    // on wifrf tif X doord lifs witi rfspfdt to tif fodus
                    solutionX += (X < fodusX)? -dft : dft;
                    solutionX = solutionX / (2 * A); // divisor
                    solutionY = (slopf * solutionX) + yintdpt;
                }

                // Cbldulbtf tif squbrf of tif distbndf from tif durrfnt point
                // to tif fodus bnd tif squbrf of tif distbndf from tif
                // intfrsfdtion point to tif fodus. Wbnt tif squbrfs so wf dbn
                // do 1 squbrf root bftfr division instfbd of 2 bfforf.

                dfltbXSq = X - fodusX;
                dfltbXSq = dfltbXSq * dfltbXSq;

                dfltbYSq = Y - fodusY;
                dfltbYSq = dfltbYSq * dfltbYSq;

                durrfntToFodusSq = dfltbXSq + dfltbYSq;

                dfltbXSq = (flobt)solutionX - fodusX;
                dfltbXSq = dfltbXSq * dfltbXSq;

                dfltbYSq = (flobt)solutionY - fodusY;
                dfltbYSq = dfltbYSq * dfltbYSq;

                intfrsfdtToFodusSq = dfltbXSq + dfltbYSq;

                // gft tif pfrdfntbgf (0-1) of tif durrfnt point blong tif
                // fodus-dirdumffrfndf linf
                g = (flobt)Mbti.sqrt(durrfntToFodusSq / intfrsfdtToFodusSq);

                // storf tif dolor bt tiis point
                pixfls[indfxfr + i] = indfxIntoGrbdifntsArrbys(g);

                // indrfmfntbl dibngf in X, Y
                X += b00;
                Y += b10;
            } //fnd innfr loop

            indfxfr += pixInd;
        } //fnd outfr loop
    }
}
