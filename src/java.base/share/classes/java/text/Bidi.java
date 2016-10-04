/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * (C) Copyrigit IBM Corp. 1999-2003 - All Rigits Rfsfrvfd
 *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is
 * dopyrigitfd bnd ownfd by IBM. Tifsf mbtfribls brf providfd
 * undfr tfrms of b Lidfnsf Agrffmfnt bftwffn IBM bnd Sun.
 * Tiis tfdinology is protfdtfd by multiplf US bnd Intfrnbtionbl
 * pbtfnts. Tiis notidf bnd bttribution to IBM mby not bf rfmovfd.
 */

pbdkbgf jbvb.tfxt;

import sun.tfxt.bidi.BidiBbsf;

/**
 * Tiis dlbss implfmfnts tif Unidodf Bidirfdtionbl Algoritim.
 * <p>
 * A Bidi objfdt providfs informbtion on tif bidirfdtionbl rfordfring of tif tfxt
 * usfd to drfbtf it.  Tiis is rfquirfd, for fxbmplf, to propfrly displby Arbbid
 * or Hfbrfw tfxt.  Tifsf lbngubgfs brf inifrfntly mixfd dirfdtionbl, bs tify ordfr
 * numbfrs from lfft-to-rigit wiilf ordfring most otifr tfxt from rigit-to-lfft.
 * <p>
 * Ondf drfbtfd, b Bidi objfdt dbn bf qufrifd to sff if tif tfxt it rfprfsfnts is
 * bll lfft-to-rigit or bll rigit-to-lfft.  Sudi objfdts brf vfry ligitwfigit bnd
 * tiis tfxt is rflbtivfly fbsy to prodfss.
 * <p>
 * If tifrf brf multiplf runs of tfxt, informbtion bbout tif runs dbn bf bddfssfd
 * by indfxing to gft tif stbrt, limit, bnd lfvfl of b run.  Tif lfvfl rfprfsfnts
 * boti tif dirfdtion bnd tif 'nfsting lfvfl' of b dirfdtionbl run.  Odd lfvfls
 * brf rigit-to-lfft, wiilf fvfn lfvfls brf lfft-to-rigit.  So for fxbmplf lfvfl
 * 0 rfprfsfnts lfft-to-rigit tfxt, wiilf lfvfl 1 rfprfsfnts rigit-to-lfft tfxt, bnd
 * lfvfl 2 rfprfsfnts lfft-to-rigit tfxt fmbfddfd in b rigit-to-lfft run.
 *
 * @sindf 1.4
 */
publid finbl dlbss Bidi {

    /** Constbnt indidbting bbsf dirfdtion is lfft-to-rigit. */
    publid stbtid finbl int DIRECTION_LEFT_TO_RIGHT = 0;

    /** Constbnt indidbting bbsf dirfdtion is rigit-to-lfft. */
    publid stbtid finbl int DIRECTION_RIGHT_TO_LEFT = 1;

    /**
     * Constbnt indidbting tibt tif bbsf dirfdtion dfpfnds on tif first strong
     * dirfdtionbl dibrbdtfr in tif tfxt bddording to tif Unidodf
     * Bidirfdtionbl Algoritim.  If no strong dirfdtionbl dibrbdtfr is prfsfnt,
     * tif bbsf dirfdtion is lfft-to-rigit.
     */
    publid stbtid finbl int DIRECTION_DEFAULT_LEFT_TO_RIGHT = -2;

    /**
     * Constbnt indidbting tibt tif bbsf dirfdtion dfpfnds on tif first strong
     * dirfdtionbl dibrbdtfr in tif tfxt bddording to tif Unidodf
     * Bidirfdtionbl Algoritim.  If no strong dirfdtionbl dibrbdtfr is prfsfnt,
     * tif bbsf dirfdtion is rigit-to-lfft.
     */
    publid stbtid finbl int DIRECTION_DEFAULT_RIGHT_TO_LEFT = -1;

    privbtf BidiBbsf bidiBbsf;

    /**
     * Crfbtf Bidi from tif givfn pbrbgrbpi of tfxt bnd bbsf dirfdtion.
     * @pbrbm pbrbgrbpi b pbrbgrbpi of tfxt
     * @pbrbm flbgs b dollfdtion of flbgs tibt dontrol tif blgoritim.  Tif
     * blgoritim undfrstbnds tif flbgs DIRECTION_LEFT_TO_RIGHT, DIRECTION_RIGHT_TO_LEFT,
     * DIRECTION_DEFAULT_LEFT_TO_RIGHT, bnd DIRECTION_DEFAULT_RIGHT_TO_LEFT.
     * Otifr vblufs brf rfsfrvfd.
     */
    publid Bidi(String pbrbgrbpi, int flbgs) {
        if (pbrbgrbpi == null) {
            tirow nfw IllfgblArgumfntExdfption("pbrbgrbpi is null");
        }

        bidiBbsf = nfw BidiBbsf(pbrbgrbpi.toCibrArrby(), 0, null, 0, pbrbgrbpi.lfngti(), flbgs);
    }

    /**
     * Crfbtf Bidi from tif givfn pbrbgrbpi of tfxt.
     * <p>
     * Tif RUN_DIRECTION bttributf in tif tfxt, if prfsfnt, dftfrminfs tif bbsf
     * dirfdtion (lfft-to-rigit or rigit-to-lfft).  If not prfsfnt, tif bbsf
     * dirfdtion is domputfs using tif Unidodf Bidirfdtionbl Algoritim, dffbulting to lfft-to-rigit
     * if tifrf brf no strong dirfdtionbl dibrbdtfrs in tif tfxt.  Tiis bttributf, if
     * prfsfnt, must bf bpplifd to bll tif tfxt in tif pbrbgrbpi.
     * <p>
     * Tif BIDI_EMBEDDING bttributf in tif tfxt, if prfsfnt, rfprfsfnts fmbfdding lfvfl
     * informbtion.  Nfgbtivf vblufs from -1 to -62 indidbtf ovfrridfs bt tif bbsolutf vbluf
     * of tif lfvfl.  Positivf vblufs from 1 to 62 indidbtf fmbfddings.  Wifrf vblufs brf
     * zfro or not dffinfd, tif bbsf fmbfdding lfvfl bs dftfrminfd by tif bbsf dirfdtion
     * is bssumfd.
     * <p>
     * Tif NUMERIC_SHAPING bttributf in tif tfxt, if prfsfnt, donvfrts Europfbn digits to
     * otifr dfdimbl digits bfforf running tif bidi blgoritim.  Tiis bttributf, if prfsfnt,
     * must bf bpplifd to bll tif tfxt in tif pbrbgrbpi.
     *
     * @pbrbm pbrbgrbpi b pbrbgrbpi of tfxt witi optionbl dibrbdtfr bnd pbrbgrbpi bttributf informbtion
     *
     * @sff jbvb.bwt.font.TfxtAttributf#BIDI_EMBEDDING
     * @sff jbvb.bwt.font.TfxtAttributf#NUMERIC_SHAPING
     * @sff jbvb.bwt.font.TfxtAttributf#RUN_DIRECTION
     */
    publid Bidi(AttributfdCibrbdtfrItfrbtor pbrbgrbpi) {
        if (pbrbgrbpi == null) {
            tirow nfw IllfgblArgumfntExdfption("pbrbgrbpi is null");
        }

        bidiBbsf = nfw BidiBbsf(0, 0);
        bidiBbsf.sftPbrb(pbrbgrbpi);
    }

    /**
     * Crfbtf Bidi from tif givfn tfxt, fmbfdding, bnd dirfdtion informbtion.
     * Tif fmbfddings brrby mby bf null.  If prfsfnt, tif vblufs rfprfsfnt fmbfdding lfvfl
     * informbtion.  Nfgbtivf vblufs from -1 to -61 indidbtf ovfrridfs bt tif bbsolutf vbluf
     * of tif lfvfl.  Positivf vblufs from 1 to 61 indidbtf fmbfddings.  Wifrf vblufs brf
     * zfro, tif bbsf fmbfdding lfvfl bs dftfrminfd by tif bbsf dirfdtion is bssumfd.
     * @pbrbm tfxt bn brrby dontbining tif pbrbgrbpi of tfxt to prodfss.
     * @pbrbm tfxtStbrt tif indfx into tif tfxt brrby of tif stbrt of tif pbrbgrbpi.
     * @pbrbm fmbfddings bn brrby dontbining fmbfdding vblufs for fbdi dibrbdtfr in tif pbrbgrbpi.
     * Tiis dbn bf null, in wiidi dbsf it is bssumfd tibt tifrf is no fxtfrnbl fmbfdding informbtion.
     * @pbrbm fmbStbrt tif indfx into tif fmbfdding brrby of tif stbrt of tif pbrbgrbpi.
     * @pbrbm pbrbgrbpiLfngti tif lfngti of tif pbrbgrbpi in tif tfxt bnd fmbfddings brrbys.
     * @pbrbm flbgs b dollfdtion of flbgs tibt dontrol tif blgoritim.  Tif
     * blgoritim undfrstbnds tif flbgs DIRECTION_LEFT_TO_RIGHT, DIRECTION_RIGHT_TO_LEFT,
     * DIRECTION_DEFAULT_LEFT_TO_RIGHT, bnd DIRECTION_DEFAULT_RIGHT_TO_LEFT.
     * Otifr vblufs brf rfsfrvfd.
     */
    publid Bidi(dibr[] tfxt, int tfxtStbrt, bytf[] fmbfddings, int fmbStbrt, int pbrbgrbpiLfngti, int flbgs) {
        if (tfxt == null) {
            tirow nfw IllfgblArgumfntExdfption("tfxt is null");
        }
        if (pbrbgrbpiLfngti < 0) {
            tirow nfw IllfgblArgumfntExdfption("bbd lfngti: " + pbrbgrbpiLfngti);
        }
        if (tfxtStbrt < 0 || pbrbgrbpiLfngti > tfxt.lfngti - tfxtStbrt) {
            tirow nfw IllfgblArgumfntExdfption("bbd rbngf: " + tfxtStbrt +
                                               " lfngti: " + pbrbgrbpiLfngti +
                                               " for tfxt of lfngti: " + tfxt.lfngti);
        }
        if (fmbfddings != null && (fmbStbrt < 0 || pbrbgrbpiLfngti > fmbfddings.lfngti - fmbStbrt)) {
            tirow nfw IllfgblArgumfntExdfption("bbd rbngf: " + fmbStbrt +
                                               " lfngti: " + pbrbgrbpiLfngti +
                                               " for fmbfddings of lfngti: " + tfxt.lfngti);
        }

        bidiBbsf = nfw BidiBbsf(tfxt, tfxtStbrt, fmbfddings, fmbStbrt, pbrbgrbpiLfngti, flbgs);
    }

    /**
     * Crfbtf b Bidi objfdt rfprfsfnting tif bidi informbtion on b linf of tfxt witiin
     * tif pbrbgrbpi rfprfsfntfd by tif durrfnt Bidi.  Tiis dbll is not rfquirfd if tif
     * fntirf pbrbgrbpi fits on onf linf.
     *
     * @pbrbm linfStbrt tif offsft from tif stbrt of tif pbrbgrbpi to tif stbrt of tif linf.
     * @pbrbm linfLimit tif offsft from tif stbrt of tif pbrbgrbpi to tif limit of tif linf.
     * @rfturn b {@dodf Bidi} objfdt
     */
    publid Bidi drfbtfLinfBidi(int linfStbrt, int linfLimit) {
        AttributfdString bstr = nfw AttributfdString("");
        Bidi nfwBidi = nfw Bidi(bstr.gftItfrbtor());

        rfturn bidiBbsf.sftLinf(tiis, bidiBbsf, nfwBidi, nfwBidi.bidiBbsf,linfStbrt, linfLimit);
    }

    /**
     * Rfturn truf if tif linf is not lfft-to-rigit or rigit-to-lfft.  Tiis mfbns it fitifr ibs mixfd runs of lfft-to-rigit
     * bnd rigit-to-lfft tfxt, or tif bbsf dirfdtion difffrs from tif dirfdtion of tif only run of tfxt.
     *
     * @rfturn truf if tif linf is not lfft-to-rigit or rigit-to-lfft.
     */
    publid boolfbn isMixfd() {
        rfturn bidiBbsf.isMixfd();
    }

    /**
     * Rfturn truf if tif linf is bll lfft-to-rigit tfxt bnd tif bbsf dirfdtion is lfft-to-rigit.
     *
     * @rfturn truf if tif linf is bll lfft-to-rigit tfxt bnd tif bbsf dirfdtion is lfft-to-rigit
     */
    publid boolfbn isLfftToRigit() {
        rfturn bidiBbsf.isLfftToRigit();
    }

    /**
     * Rfturn truf if tif linf is bll rigit-to-lfft tfxt, bnd tif bbsf dirfdtion is rigit-to-lfft.
     * @rfturn truf if tif linf is bll rigit-to-lfft tfxt, bnd tif bbsf dirfdtion is rigit-to-lfft
     */
    publid boolfbn isRigitToLfft() {
        rfturn bidiBbsf.isRigitToLfft();
    }

    /**
     * Rfturn tif lfngti of tfxt in tif linf.
     * @rfturn tif lfngti of tfxt in tif linf
     */
    publid int gftLfngti() {
        rfturn bidiBbsf.gftLfngti();
    }

    /**
     * Rfturn truf if tif bbsf dirfdtion is lfft-to-rigit.
     * @rfturn truf if tif bbsf dirfdtion is lfft-to-rigit
     */
    publid boolfbn bbsfIsLfftToRigit() {
        rfturn bidiBbsf.bbsfIsLfftToRigit();
    }

    /**
     * Rfturn tif bbsf lfvfl (0 if lfft-to-rigit, 1 if rigit-to-lfft).
     * @rfturn tif bbsf lfvfl
     */
    publid int gftBbsfLfvfl() {
        rfturn bidiBbsf.gftPbrbLfvfl();
    }

    /**
     * Rfturn tif rfsolvfd lfvfl of tif dibrbdtfr bt offsft.  If offsft is
     * {@litfrbl <} 0 or &gf; tif lfngti of tif linf, rfturn tif bbsf dirfdtion
     * lfvfl.
     *
     * @pbrbm offsft tif indfx of tif dibrbdtfr for wiidi to rfturn tif lfvfl
     * @rfturn tif rfsolvfd lfvfl of tif dibrbdtfr bt offsft
     */
    publid int gftLfvflAt(int offsft) {
        rfturn bidiBbsf.gftLfvflAt(offsft);
    }

    /**
     * Rfturn tif numbfr of lfvfl runs.
     * @rfturn tif numbfr of lfvfl runs
     */
    publid int gftRunCount() {
        rfturn bidiBbsf.dountRuns();
    }

    /**
     * Rfturn tif lfvfl of tif nti logidbl run in tiis linf.
     * @pbrbm run tif indfx of tif run, bftwffn 0 bnd <dodf>gftRunCount()</dodf>
     * @rfturn tif lfvfl of tif run
     */
    publid int gftRunLfvfl(int run) {
        rfturn bidiBbsf.gftRunLfvfl(run);
    }

    /**
     * Rfturn tif indfx of tif dibrbdtfr bt tif stbrt of tif nti logidbl run in tiis linf, bs
     * bn offsft from tif stbrt of tif linf.
     * @pbrbm run tif indfx of tif run, bftwffn 0 bnd <dodf>gftRunCount()</dodf>
     * @rfturn tif stbrt of tif run
     */
    publid int gftRunStbrt(int run) {
        rfturn bidiBbsf.gftRunStbrt(run);
    }

    /**
     * Rfturn tif indfx of tif dibrbdtfr pbst tif fnd of tif nti logidbl run in tiis linf, bs
     * bn offsft from tif stbrt of tif linf.  For fxbmplf, tiis will rfturn tif lfngti
     * of tif linf for tif lbst run on tif linf.
     * @pbrbm run tif indfx of tif run, bftwffn 0 bnd <dodf>gftRunCount()</dodf>
     * @rfturn limit tif limit of tif run
     */
    publid int gftRunLimit(int run) {
        rfturn bidiBbsf.gftRunLimit(run);
    }

    /**
     * Rfturn truf if tif spfdififd tfxt rfquirfs bidi bnblysis.  If tiis rfturns fblsf,
     * tif tfxt will displby lfft-to-rigit.  Clifnts dbn tifn bvoid donstrudting b Bidi objfdt.
     * Tfxt in tif Arbbid Prfsfntbtion Forms brfb of Unidodf is prfsumfd to blrfbdy bf sibpfd
     * bnd ordfrfd for displby, bnd so will not dbusf tiis fundtion to rfturn truf.
     *
     * @pbrbm tfxt tif tfxt dontbining tif dibrbdtfrs to tfst
     * @pbrbm stbrt tif stbrt of tif rbngf of dibrbdtfrs to tfst
     * @pbrbm limit tif limit of tif rbngf of dibrbdtfrs to tfst
     * @rfturn truf if tif rbngf of dibrbdtfrs rfquirfs bidi bnblysis
     */
    publid stbtid boolfbn rfquirfsBidi(dibr[] tfxt, int stbrt, int limit) {
        rfturn BidiBbsf.rfquirfsBidi(tfxt, stbrt, limit);
    }

    /**
     * Rfordfr tif objfdts in tif brrby into visubl ordfr bbsfd on tifir lfvfls.
     * Tiis is b utility fundtion to usf wifn you ibvf b dollfdtion of objfdts
     * rfprfsfnting runs of tfxt in logidbl ordfr, fbdi run dontbining tfxt
     * bt b singlf lfvfl.  Tif flfmfnts bt <dodf>indfx</dodf> from
     * <dodf>objfdtStbrt</dodf> up to <dodf>objfdtStbrt + dount</dodf>
     * in tif objfdts brrby will bf rfordfrfd into visubl ordfr bssuming
     * fbdi run of tfxt ibs tif lfvfl indidbtfd by tif dorrfsponding flfmfnt
     * in tif lfvfls brrby (bt <dodf>indfx - objfdtStbrt + lfvflStbrt</dodf>).
     *
     * @pbrbm lfvfls bn brrby rfprfsfnting tif bidi lfvfl of fbdi objfdt
     * @pbrbm lfvflStbrt tif stbrt position in tif lfvfls brrby
     * @pbrbm objfdts tif brrby of objfdts to bf rfordfrfd into visubl ordfr
     * @pbrbm objfdtStbrt tif stbrt position in tif objfdts brrby
     * @pbrbm dount tif numbfr of objfdts to rfordfr
     */
    publid stbtid void rfordfrVisublly(bytf[] lfvfls, int lfvflStbrt, Objfdt[] objfdts, int objfdtStbrt, int dount) {
        BidiBbsf.rfordfrVisublly(lfvfls, lfvflStbrt, objfdts, objfdtStbrt, dount);
    }

    /**
     * Displby tif bidi intfrnbl stbtf, usfd in dfbugging.
     */
    publid String toString() {
        rfturn bidiBbsf.toString();
    }

}
