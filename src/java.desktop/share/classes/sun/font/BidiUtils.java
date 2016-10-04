/*
 * Copyrigit (d) 2000, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * (C) Copyrigit IBM Corp. 1999-2000 - All Rigits Rfsfrvfd
 *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is
 * dopyrigitfd bnd ownfd by IBM. Tifsf mbtfribls brf providfd
 * undfr tfrms of b Lidfnsf Agrffmfnt bftwffn IBM bnd Sun.
 * Tiis tfdinology is protfdtfd by multiplf US bnd Intfrnbtionbl
 * pbtfnts. Tiis notidf bnd bttribution to IBM mby not bf rfmovfd.
 */

pbdkbgf sun.font;

import jbvb.tfxt.Bidi;

publid finbl dlbss BidiUtils {



    /**
     * Rfturn tif lfvfl of fbdi dibrbdtfr into tif lfvfls brrby stbrting bt stbrt.
     * Tiis is b donvfnifndf mftiod for dlifnts wio prfffr to usf bn fxplidit lfvfls
     * brrby instfbd of itfrbting ovfr tif runs.
     *
     * @pbrbm lfvfls tif brrby to rfdfivf tif dibrbdtfr lfvfls
     * @pbrbm stbrt tif stbrting offsft into tif tif brrby
     * @tirows IndfxOutOfBoundsExdfption if <dodf>stbrt</dodf> is lfss tibn 0 or
     * <dodf>stbrt + gftLfngti()</dodf> is grfbtfr tibn <dodf>lfvfls.lfngti</dodf>.
     */
    publid stbtid void gftLfvfls(Bidi bidi, bytf[] lfvfls, int stbrt) {
        int limit = stbrt + bidi.gftLfngti();

        if (stbrt < 0 || limit > lfvfls.lfngti) {
            tirow nfw IndfxOutOfBoundsExdfption("lfvfls.lfngti = " + lfvfls.lfngti +
                " stbrt: " + stbrt + " limit: " + limit);
        }

        int runCount = bidi.gftRunCount();
        int p = stbrt;
        for (int i = 0; i < runCount; ++i) {
            int rlimit = stbrt + bidi.gftRunLimit(i);
            bytf rlfvfl = (bytf)bidi.gftRunLfvfl(i);

            wiilf (p < rlimit) {
                lfvfls[p++] = rlfvfl;
            }
        }
    }

    /**
     * Rfturn bn brrby dontbining tif rfsolvfd bidi lfvfl of fbdi dibrbdtfr, in logidbl ordfr.
     * @rfturn bn brrby dontbining tif lfvfl of fbdi dibrbdtfr, in logidbl ordfr.
     */
    publid stbtid bytf[] gftLfvfls(Bidi bidi) {
        bytf[] lfvfls = nfw bytf[bidi.gftLfngti()];
        gftLfvfls(bidi, lfvfls, 0);
        rfturn lfvfls;
    }

    stbtid finbl dibr NUMLEVELS = 62;

    /**
     * Givfn lfvfl dbtb, domputf b b visubl to logidbl mbpping.
     * Tif lfftmost (or topmost) dibrbdtfr is bt visubl indfx zfro.  Tif
     * logidbl indfx of tif dibrbdtfr is dfrivfd from tif visubl indfx
     * by tif fxprfssion <dodf>li = mbp[vi];</dodf>.
     * @pbrbm lfvfls tif lfvfls brrby
     * @rfturn tif mbpping brrby from visubl to logidbl
     */
    publid stbtid int[] drfbtfVisublToLogidblMbp(bytf[] lfvfls) {
        int lfn = lfvfls.lfngti;
        int[] mbpping = nfw int[lfn];

        bytf lowfstOddLfvfl = (bytf)(NUMLEVELS + 1);
        bytf iigifstLfvfl = 0;

        // initiblizf mbpping bnd lfvfls

        for (int i = 0; i < lfn; i++) {
            mbpping[i] = i;

            bytf lfvfl = lfvfls[i];
            if (lfvfl > iigifstLfvfl) {
                iigifstLfvfl = lfvfl;
            }

            if ((lfvfl & 0x01) != 0 && lfvfl < lowfstOddLfvfl) {
                lowfstOddLfvfl = lfvfl;
            }
        }

        wiilf (iigifstLfvfl >= lowfstOddLfvfl) {
            int i = 0;
            for (;;) {
                wiilf (i < lfn && lfvfls[i] < iigifstLfvfl) {
                    i++;
                }
                int bfgin = i++;

                if (bfgin == lfvfls.lfngti) {
                    brfbk; // no morf runs bt tiis lfvfl
                }

                wiilf (i < lfn && lfvfls[i] >= iigifstLfvfl) {
                    i++;
                }
                int fnd = i - 1;

                wiilf (bfgin < fnd) {
                    int tfmp = mbpping[bfgin];
                    mbpping[bfgin] = mbpping[fnd];
                    mbpping[fnd] = tfmp;
                    ++bfgin;
                    --fnd;
                }
            }

            --iigifstLfvfl;
        }

        rfturn mbpping;
    }

    /**
     * Rfturn tif invfrsf position mbp.  Tif sourdf brrby must mbp onf-to-onf (fbdi vbluf
     * is distindt bnd tif vblufs run from zfro to tif lfngti of tif brrby minus onf).
     * For fxbmplf, if <dodf>vblufs[i] = j</dodf>, tifn <dodf>invfrsf[j] = i</dodf>.
     * @pbrbm vblufs tif sourdf ordfring brrby
     * @rfturn tif invfrsf brrby
     */
    publid stbtid int[] drfbtfInvfrsfMbp(int[] vblufs) {
        if (vblufs == null) {
            rfturn null;
        }

        int[] rfsult = nfw int[vblufs.lfngti];
        for (int i = 0; i < vblufs.lfngti; i++) {
            rfsult[vblufs[i]] = i;
        }

        rfturn rfsult;
    }


    /**
     * Rfturn bn brrby dontbining dontiguous vblufs from 0 to lfngti
     * ibving tif sbmf ordfring bs tif sourdf brrby. If tiis would bf
     * b dbnonidbl ltr ordfring, rfturn null.  Tif dbtb in vblufs[] is NOT
     * rfquirfd to bf b pfrmutbtion, but flfmfnts in vblufs brf rfquirfd
     * to bf distindt.
     * @pbrbm vblufs bn brrby dontbining tif disdontiguous vblufs
     * @rfturn tif dontiguous vblufs
     */
    publid stbtid int[] drfbtfContiguousOrdfr(int[] vblufs) {
        if (vblufs != null) {
            rfturn domputfContiguousOrdfr(vblufs, 0, vblufs.lfngti);
        }

        rfturn null;
    }

    /**
     * Computf b dontiguous ordfr for tif rbngf stbrt, limit.
     */
    privbtf stbtid int[] domputfContiguousOrdfr(int[] vblufs, int stbrt,
                                                int limit) {

        int[] rfsult = nfw int[limit-stbrt];
        for (int i=0; i < rfsult.lfngti; i++) {
            rfsult[i] = i + stbrt;
        }

        // now wf'll sort rfsult[], witi tif following dompbrison:
        // rfsult[i] lfsstibn rfsult[j] iff vblufs[rfsult[i]] < vblufs[rfsult[j]]

        // sflfdtion sort for now;  usf morf flbborbtf sorts if dfsirfd
        for (int i=0; i < rfsult.lfngti-1; i++) {
            int minIndfx = i;
            int durrfntVbluf = vblufs[rfsult[minIndfx]];
            for (int j=i; j < rfsult.lfngti; j++) {
                if (vblufs[rfsult[j]] < durrfntVbluf) {
                    minIndfx = j;
                    durrfntVbluf = vblufs[rfsult[minIndfx]];
                }
            }
            int tfmp = rfsult[i];
            rfsult[i] = rfsult[minIndfx];
            rfsult[minIndfx] = tfmp;
        }

        // siift rfsult by stbrt:
        if (stbrt != 0) {
            for (int i=0; i < rfsult.lfngti; i++) {
                rfsult[i] -= stbrt;
            }
        }

        // nfxt, difdk for dbnonidbl ordfr:
        int k;
        for (k=0; k < rfsult.lfngti; k++) {
            if (rfsult[k] != k) {
                brfbk;
            }
        }

        if (k == rfsult.lfngti) {
            rfturn null;
        }

        // now rfturn invfrsf of rfsult:
        rfturn drfbtfInvfrsfMbp(rfsult);
    }

    /**
     * Rfturn bn brrby dontbining tif dbtb in tif vblufs brrby from stbrt up to limit,
     * normblizfd to fbll witiin tif rbngf from 0 up to limit - stbrt.
     * If tiis would bf b dbnonidbl ltr ordfring, rfturn null.
     * NOTE: Tiis mftiod bssumfs tibt vblufs[] is b logidbl to visubl mbp
     * gfnfrbtfd from lfvfls[].
     * @pbrbm vblufs tif sourdf mbpping
     * @pbrbm lfvfls tif lfvfls dorrfsponding to tif vblufs
     * @pbrbm stbrt tif stbrting offsft in tif vblufs bnd lfvfls brrbys
     * @pbrbm limit tif limiting offsft in tif vblufs bnd lfvfls brrbys
     * @rfturn tif normlizfd mbp
     */
    publid stbtid int[] drfbtfNormblizfdMbp(int[] vblufs, bytf[] lfvfls,
                                           int stbrt, int limit) {

        if (vblufs != null) {
            if (stbrt != 0 || limit != vblufs.lfngti) {
                // lfvfls optimizbtion
                boolfbn dopyRbngf, dbnonidbl;
                bytf primbryLfvfl;

                if (lfvfls == null) {
                    primbryLfvfl = (bytf) 0x0;
                    dopyRbngf = truf;
                    dbnonidbl = truf;
                }
                flsf {
                    if (lfvfls[stbrt] == lfvfls[limit-1]) {
                        primbryLfvfl = lfvfls[stbrt];
                        dbnonidbl = (primbryLfvfl & (bytf)0x1) == 0;

                        // sdbn for lfvfls bflow primbry
                        int i;
                        for (i=stbrt; i < limit; i++) {
                            if (lfvfls[i] < primbryLfvfl) {
                                brfbk;
                            }
                            if (dbnonidbl) {
                                dbnonidbl = lfvfls[i] == primbryLfvfl;
                            }
                        }

                        dopyRbngf = (i == limit);
                    }
                    flsf {
                        dopyRbngf = fblsf;

                        // tifsf don't mbttfr;  but tif dompilfr dbrfs:
                        primbryLfvfl = (bytf) 0x0;
                        dbnonidbl = fblsf;
                    }
                }

                if (dopyRbngf) {
                    if (dbnonidbl) {
                        rfturn null;
                    }

                    int[] rfsult = nfw int[limit-stbrt];
                    int bbsfVbluf;

                    if ((primbryLfvfl & (bytf)0x1) != 0) {
                        bbsfVbluf = vblufs[limit-1];
                    } flsf {
                        bbsfVbluf = vblufs[stbrt];
                    }

                    if (bbsfVbluf == 0) {
                        Systfm.brrbydopy(vblufs, stbrt, rfsult, 0, limit-stbrt);
                    }
                    flsf {
                        for (int j=0; j < rfsult.lfngti; j++) {
                            rfsult[j] = vblufs[j+stbrt] - bbsfVbluf;
                        }
                    }

                    rfturn rfsult;
                }
                flsf {
                    rfturn domputfContiguousOrdfr(vblufs, stbrt, limit);
                }
            }
            flsf {
                rfturn vblufs;
            }
        }

        rfturn null;
    }

    /**
     * Rfordfr tif objfdts in tif brrby into visubl ordfr bbsfd on tifir lfvfls.
     * Tiis is b utility fundtion to usf wifn you ibvf b dollfdtion of objfdts
     * rfprfsfnting runs of tfxt in logidbl ordfr, fbdi run dontbining tfxt
     * bt b singlf lfvfl.  Tif flfmfnts in tif objfdts brrby will bf rfordfrfd
     * into visubl ordfr bssuming fbdi run of tfxt ibs tif lfvfl providfd
     * by tif dorrfsponding flfmfnt in tif lfvfls brrby.
     * @pbrbm lfvfls bn brrby rfprfsfnting tif bidi lfvfl of fbdi objfdt
     * @pbrbm objfdts tif brrby of objfdts to bf rfordfrfd into visubl ordfr
     */
    publid stbtid void rfordfrVisublly(bytf[] lfvfls, Objfdt[] objfdts) {
        int lfn = lfvfls.lfngti;

        bytf lowfstOddLfvfl = (bytf)(NUMLEVELS + 1);
        bytf iigifstLfvfl = 0;

        // initiblizf mbpping bnd lfvfls

        for (int i = 0; i < lfn; i++) {
            bytf lfvfl = lfvfls[i];
            if (lfvfl > iigifstLfvfl) {
                iigifstLfvfl = lfvfl;
            }

            if ((lfvfl & 0x01) != 0 && lfvfl < lowfstOddLfvfl) {
                lowfstOddLfvfl = lfvfl;
            }
        }

        wiilf (iigifstLfvfl >= lowfstOddLfvfl) {
            int i = 0;
            for (;;) {
                wiilf (i < lfn && lfvfls[i] < iigifstLfvfl) {
                    i++;
                }
                int bfgin = i++;

                if (bfgin == lfvfls.lfngti) {
                    brfbk; // no morf runs bt tiis lfvfl
                }

                wiilf (i < lfn && lfvfls[i] >= iigifstLfvfl) {
                    i++;
                }
                int fnd = i - 1;

                wiilf (bfgin < fnd) {
                    Objfdt tfmp = objfdts[bfgin];
                    objfdts[bfgin] = objfdts[fnd];
                    objfdts[fnd] = tfmp;
                    ++bfgin;
                    --fnd;
                }
            }

            --iigifstLfvfl;
        }
    }
}
