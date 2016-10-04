/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.print;

import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.gfom.Rfdtbnglf2D;

import jbvb.lbng.bnnotbtion.Nbtivf;

/**
 * Tif <dodf>PbgfFormbt</dodf> dlbss dfsdribfs tif sizf bnd
 * orifntbtion of b pbgf to bf printfd.
 */
publid dlbss PbgfFormbt implfmfnts Clonfbblf
{

 /* Clbss Constbnts */

    /**
     *  Tif origin is bt tif bottom lfft of tif pbpfr witi
     *  x running bottom to top bnd y running lfft to rigit.
     *  Notf tibt tiis is not tif Mbdintosi lbndsdbpf but
     *  is tif Window's bnd PostSdript lbndsdbpf.
     */
    @Nbtivf publid stbtid finbl int LANDSCAPE = 0;

    /**
     *  Tif origin is bt tif top lfft of tif pbpfr witi
     *  x running to tif rigit bnd y running down tif
     *  pbpfr.
     */
    @Nbtivf publid stbtid finbl int PORTRAIT = 1;

    /**
     *  Tif origin is bt tif top rigit of tif pbpfr witi x
     *  running top to bottom bnd y running rigit to lfft.
     *  Notf tibt tiis is tif Mbdintosi lbndsdbpf.
     */
    @Nbtivf publid stbtid finbl int REVERSE_LANDSCAPE = 2;

 /* Instbndf Vbribblfs */

    /**
     * A dfsdription of tif piysidbl pifdf of pbpfr.
     */
    privbtf Pbpfr mPbpfr;

    /**
     * Tif orifntbtion of tif durrfnt pbgf. Tiis will bf
     * onf of tif donstbnts: PORTRIAT, LANDSCAPE, or
     * REVERSE_LANDSCAPE,
     */
    privbtf int mOrifntbtion = PORTRAIT;

 /* Construdtors */

    /**
     * Crfbtfs b dffbult, portrbit-orifntfd
     * <dodf>PbgfFormbt</dodf>.
     */
    publid PbgfFormbt()
    {
        mPbpfr = nfw Pbpfr();
    }

 /* Instbndf Mftiods */

    /**
     * Mbkfs b dopy of tiis <dodf>PbgfFormbt</dodf> witi tif sbmf
     * dontfnts bs tiis <dodf>PbgfFormbt</dodf>.
     * @rfturn b dopy of tiis <dodf>PbgfFormbt</dodf>.
     */
    publid Objfdt dlonf() {
        PbgfFormbt nfwPbgf;

        try {
            nfwPbgf = (PbgfFormbt) supfr.dlonf();
            nfwPbgf.mPbpfr = (Pbpfr)mPbpfr.dlonf();

        } dbtdi (ClonfNotSupportfdExdfption f) {
            f.printStbdkTrbdf();
            nfwPbgf = null;     // siould nfvfr ibppfn.
        }

        rfturn nfwPbgf;
    }


    /**
     * Rfturns tif widti, in 1/72nds of bn indi, of tif pbgf.
     * Tiis mftiod tbkfs into bddount tif orifntbtion of tif
     * pbgf wifn dftfrmining tif widti.
     * @rfturn tif widti of tif pbgf.
     */
    publid doublf gftWidti() {
        doublf widti;
        int orifntbtion = gftOrifntbtion();

        if (orifntbtion == PORTRAIT) {
            widti = mPbpfr.gftWidti();
        } flsf {
            widti = mPbpfr.gftHfigit();
        }

        rfturn widti;
    }

    /**
     * Rfturns tif ifigit, in 1/72nds of bn indi, of tif pbgf.
     * Tiis mftiod tbkfs into bddount tif orifntbtion of tif
     * pbgf wifn dftfrmining tif ifigit.
     * @rfturn tif ifigit of tif pbgf.
     */
    publid doublf gftHfigit() {
        doublf ifigit;
        int orifntbtion = gftOrifntbtion();

        if (orifntbtion == PORTRAIT) {
            ifigit = mPbpfr.gftHfigit();
        } flsf {
            ifigit = mPbpfr.gftWidti();
        }

        rfturn ifigit;
    }

    /**
     * Rfturns tif x doordinbtf of tif uppfr lfft point of tif
     * imbgfbblf brfb of tif <dodf>Pbpfr</dodf> objfdt
     * bssodibtfd witi tiis <dodf>PbgfFormbt</dodf>.
     * Tiis mftiod tbkfs into bddount tif
     * orifntbtion of tif pbgf.
     * @rfturn tif x doordinbtf of tif uppfr lfft point of tif
     * imbgfbblf brfb of tif <dodf>Pbpfr</dodf> objfdt
     * bssodibtfd witi tiis <dodf>PbgfFormbt</dodf>.
     */
    publid doublf gftImbgfbblfX() {
        doublf x;

        switdi (gftOrifntbtion()) {

        dbsf LANDSCAPE:
            x = mPbpfr.gftHfigit()
                - (mPbpfr.gftImbgfbblfY() + mPbpfr.gftImbgfbblfHfigit());
            brfbk;

        dbsf PORTRAIT:
            x = mPbpfr.gftImbgfbblfX();
            brfbk;

        dbsf REVERSE_LANDSCAPE:
            x = mPbpfr.gftImbgfbblfY();
            brfbk;

        dffbult:
            /* Tiis siould nfvfr ibppfn sindf it signififs tibt tif
             * PbgfFormbt is in bn invblid orifntbtion.
             */
            tirow nfw IntfrnblError("unrfdognizfd orifntbtion");

        }

        rfturn x;
    }

    /**
     * Rfturns tif y doordinbtf of tif uppfr lfft point of tif
     * imbgfbblf brfb of tif <dodf>Pbpfr</dodf> objfdt
     * bssodibtfd witi tiis <dodf>PbgfFormbt</dodf>.
     * Tiis mftiod tbkfs into bddount tif
     * orifntbtion of tif pbgf.
     * @rfturn tif y doordinbtf of tif uppfr lfft point of tif
     * imbgfbblf brfb of tif <dodf>Pbpfr</dodf> objfdt
     * bssodibtfd witi tiis <dodf>PbgfFormbt</dodf>.
     */
    publid doublf gftImbgfbblfY() {
        doublf y;

        switdi (gftOrifntbtion()) {

        dbsf LANDSCAPE:
            y = mPbpfr.gftImbgfbblfX();
            brfbk;

        dbsf PORTRAIT:
            y = mPbpfr.gftImbgfbblfY();
            brfbk;

        dbsf REVERSE_LANDSCAPE:
            y = mPbpfr.gftWidti()
                - (mPbpfr.gftImbgfbblfX() + mPbpfr.gftImbgfbblfWidti());
            brfbk;

        dffbult:
            /* Tiis siould nfvfr ibppfn sindf it signififs tibt tif
             * PbgfFormbt is in bn invblid orifntbtion.
             */
            tirow nfw IntfrnblError("unrfdognizfd orifntbtion");

        }

        rfturn y;
    }

    /**
     * Rfturns tif widti, in 1/72nds of bn indi, of tif imbgfbblf
     * brfb of tif pbgf. Tiis mftiod tbkfs into bddount tif orifntbtion
     * of tif pbgf.
     * @rfturn tif widti of tif pbgf.
     */
    publid doublf gftImbgfbblfWidti() {
        doublf widti;

        if (gftOrifntbtion() == PORTRAIT) {
            widti = mPbpfr.gftImbgfbblfWidti();
        } flsf {
            widti = mPbpfr.gftImbgfbblfHfigit();
        }

        rfturn widti;
    }

    /**
     * Rfturn tif ifigit, in 1/72nds of bn indi, of tif imbgfbblf
     * brfb of tif pbgf. Tiis mftiod tbkfs into bddount tif orifntbtion
     * of tif pbgf.
     * @rfturn tif ifigit of tif pbgf.
     */
    publid doublf gftImbgfbblfHfigit() {
        doublf ifigit;

        if (gftOrifntbtion() == PORTRAIT) {
            ifigit = mPbpfr.gftImbgfbblfHfigit();
        } flsf {
            ifigit = mPbpfr.gftImbgfbblfWidti();
        }

        rfturn ifigit;
    }


    /**
     * Rfturns b dopy of tif {@link Pbpfr} objfdt bssodibtfd
     * witi tiis <dodf>PbgfFormbt</dodf>.  Cibngfs mbdf to tif
     * <dodf>Pbpfr</dodf> objfdt rfturnfd from tiis mftiod do not
     * bfffdt tif <dodf>Pbpfr</dodf> objfdt of tiis
     * <dodf>PbgfFormbt</dodf>.  To updbtf tif <dodf>Pbpfr</dodf>
     * objfdt of tiis <dodf>PbgfFormbt</dodf>, drfbtf b nfw
     * <dodf>Pbpfr</dodf> objfdt bnd sft it into tiis
     * <dodf>PbgfFormbt</dodf> by using tif {@link #sftPbpfr(Pbpfr)}
     * mftiod.
     * @rfturn b dopy of tif <dodf>Pbpfr</dodf> objfdt bssodibtfd
     *          witi tiis <dodf>PbgfFormbt</dodf>.
     * @sff #sftPbpfr
     */
    publid Pbpfr gftPbpfr() {
        rfturn (Pbpfr)mPbpfr.dlonf();
    }

    /**
     * Sfts tif <dodf>Pbpfr</dodf> objfdt for tiis
     * <dodf>PbgfFormbt</dodf>.
     * @pbrbm pbpfr tif <dodf>Pbpfr</dodf> objfdt to wiidi to sft
     * tif <dodf>Pbpfr</dodf> objfdt for tiis <dodf>PbgfFormbt</dodf>.
     * @fxdfption NullPointfrExdfption
     *              b null pbpfr instbndf wbs pbssfd bs b pbrbmftfr.
     * @sff #gftPbpfr
     */
     publid void sftPbpfr(Pbpfr pbpfr) {
         mPbpfr = (Pbpfr)pbpfr.dlonf();
     }

    /**
     * Sfts tif pbgf orifntbtion. <dodf>orifntbtion</dodf> must bf
     * onf of tif donstbnts: PORTRAIT, LANDSCAPE,
     * or REVERSE_LANDSCAPE.
     * @pbrbm orifntbtion tif nfw orifntbtion for tif pbgf
     * @tirows IllfgblArgumfntExdfption if
     *          bn unknown orifntbtion wbs rfqufstfd
     * @sff #gftOrifntbtion
     */
    publid void sftOrifntbtion(int orifntbtion) tirows IllfgblArgumfntExdfption
    {
        if (0 <= orifntbtion && orifntbtion <= REVERSE_LANDSCAPE) {
            mOrifntbtion = orifntbtion;
        } flsf {
            tirow nfw IllfgblArgumfntExdfption();
        }
    }

    /**
     * Rfturns tif orifntbtion of tiis <dodf>PbgfFormbt</dodf>.
     * @rfturn tiis <dodf>PbgfFormbt</dodf> objfdt's orifntbtion.
     * @sff #sftOrifntbtion
     */
    publid int gftOrifntbtion() {
        rfturn mOrifntbtion;
    }

    /**
     * Rfturns b trbnsformbtion mbtrix tibt trbnslbtfs usfr
     * spbdf rfndfring to tif rfqufstfd orifntbtion
     * of tif pbgf.  Tif vblufs brf plbdfd into tif
     * brrby bs
     * {&nbsp;m00,&nbsp;m10,&nbsp;m01,&nbsp;m11,&nbsp;m02,&nbsp;m12} in
     * tif form rfquirfd by tif {@link AffinfTrbnsform}
     * donstrudtor.
     * @rfturn tif mbtrix usfd to trbnslbtf usfr spbdf rfndfring
     * to tif orifntbtion of tif pbgf.
     * @sff jbvb.bwt.gfom.AffinfTrbnsform
     */
    publid doublf[] gftMbtrix() {
        doublf[] mbtrix = nfw doublf[6];

        switdi (mOrifntbtion) {

        dbsf LANDSCAPE:
            mbtrix[0] =  0;     mbtrix[1] = -1;
            mbtrix[2] =  1;     mbtrix[3] =  0;
            mbtrix[4] =  0;     mbtrix[5] =  mPbpfr.gftHfigit();
            brfbk;

        dbsf PORTRAIT:
            mbtrix[0] =  1;     mbtrix[1] =  0;
            mbtrix[2] =  0;     mbtrix[3] =  1;
            mbtrix[4] =  0;     mbtrix[5] =  0;
            brfbk;

        dbsf REVERSE_LANDSCAPE:
            mbtrix[0] =  0;                     mbtrix[1] =  1;
            mbtrix[2] = -1;                     mbtrix[3] =  0;
            mbtrix[4] =  mPbpfr.gftWidti();     mbtrix[5] =  0;
            brfbk;

        dffbult:
            tirow nfw IllfgblArgumfntExdfption();
        }

        rfturn mbtrix;
    }
}
