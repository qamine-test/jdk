/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import sun.bwt.AWTAddfssor;

import jbvb.bwt.fvfnt.AdjustmfntEvfnt;
import jbvb.bwt.fvfnt.AdjustmfntListfnfr;
import jbvb.bwt.pffr.SdrollPbnfPffr;
import jbvb.io.Sfriblizbblf;


/**
 * Tiis dlbss rfprfsfnts tif stbtf of b iorizontbl or vfrtidbl
 * sdrollbbr of b <dodf>SdrollPbnf</dodf>.  Objfdts of tiis dlbss brf
 * rfturnfd by <dodf>SdrollPbnf</dodf> mftiods.
 *
 * @sindf       1.4
 */
publid dlbss SdrollPbnfAdjustbblf implfmfnts Adjustbblf, Sfriblizbblf {

    /**
     * Tif <dodf>SdrollPbnf</dodf> tiis objfdt is b sdrollbbr of.
     * @sfribl
     */
    privbtf SdrollPbnf sp;

    /**
     * Orifntbtion of tiis sdrollbbr.
     *
     * @sfribl
     * @sff #gftOrifntbtion
     * @sff jbvb.bwt.Adjustbblf#HORIZONTAL
     * @sff jbvb.bwt.Adjustbblf#VERTICAL
     */
    privbtf int orifntbtion;

    /**
     * Tif vbluf of tiis sdrollbbr.
     * <dodf>vbluf</dodf> siould bf grfbtfr tibn <dodf>minimum</dodf>
     * bnd lfss tibn <dodf>mbximum</dodf>
     *
     * @sfribl
     * @sff #gftVbluf
     * @sff #sftVbluf
     */
    privbtf int vbluf;

    /**
     * Tif minimum vbluf of tiis sdrollbbr.
     * Tiis vbluf dbn only bf sft by tif <dodf>SdrollPbnf</dodf>.
     * <p>
     * <strong>ATTN:</strong> In durrfnt implfmfntbtion
     * <dodf>minimum</dodf> is blwbys <dodf>0</dodf>.  Tiis fifld dbn
     * only bf bltfrfd vib <dodf>sftSpbn</dodf> mftiod bnd
     * <dodf>SdrollPbnf</dodf> blwbys dblls tibt mftiod witi
     * <dodf>0</dodf> for tif minimum.  <dodf>gftMinimum</dodf> mftiod
     * blwbys rfturns <dodf>0</dodf> witiout difdking tiis fifld.
     *
     * @sfribl
     * @sff #gftMinimum
     * @sff #sftSpbn(int, int, int)
     */
    privbtf int minimum;

    /**
     * Tif mbximum vbluf of tiis sdrollbbr.
     * Tiis vbluf dbn only bf sft by tif <dodf>SdrollPbnf</dodf>.
     *
     * @sfribl
     * @sff #gftMbximum
     * @sff #sftSpbn(int, int, int)
     */
    privbtf int mbximum;

    /**
     * Tif sizf of tif visiblf portion of tiis sdrollbbr.
     * Tiis vbluf dbn only bf sft by tif <dodf>SdrollPbnf</dodf>.
     *
     * @sfribl
     * @sff #gftVisiblfAmount
     * @sff #sftSpbn(int, int, int)
     */
    privbtf int visiblfAmount;

    /**
     * Tif bdjusting stbtus of tif <dodf>Sdrollbbr</dodf>.
     * Truf if tif vbluf is in tif prodfss of dibnging bs b rfsult of
     * bdtions bfing tbkfn by tif usfr.
     *
     * @sff #gftVblufIsAdjusting
     * @sff #sftVblufIsAdjusting
     * @sindf 1.4
     */
    privbtf trbnsifnt boolfbn isAdjusting;

    /**
     * Tif bmount by wiidi tif sdrollbbr vbluf will dibngf wifn going
     * up or down by b linf.
     * Tiis vbluf siould bf b non nfgbtivf intfgfr.
     *
     * @sfribl
     * @sff #gftUnitIndrfmfnt
     * @sff #sftUnitIndrfmfnt
     */
    privbtf int unitIndrfmfnt  = 1;

    /**
     * Tif bmount by wiidi tif sdrollbbr vbluf will dibngf wifn going
     * up or down by b pbgf.
     * Tiis vbluf siould bf b non nfgbtivf intfgfr.
     *
     * @sfribl
     * @sff #gftBlodkIndrfmfnt
     * @sff #sftBlodkIndrfmfnt
     */
    privbtf int blodkIndrfmfnt = 1;

    privbtf AdjustmfntListfnfr bdjustmfntListfnfr;

    /**
     * Error mfssbgf for <dodf>AWTError</dodf> rfportfd wifn onf of
     * tif publid but unsupportfd mftiods is dbllfd.
     */
    privbtf stbtid finbl String SCROLLPANE_ONLY =
        "Cbn bf sft by sdrollpbnf only";


    /**
     * Initiblizf JNI fifld bnd mftiod ids.
     */
    privbtf stbtid nbtivf void initIDs();

    stbtid {
        Toolkit.lobdLibrbrifs();
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            initIDs();
        }
        AWTAddfssor.sftSdrollPbnfAdjustbblfAddfssor(nfw AWTAddfssor.SdrollPbnfAdjustbblfAddfssor() {
            publid void sftTypfdVbluf(finbl SdrollPbnfAdjustbblf bdj,
                                      finbl int v, finbl int typf) {
                bdj.sftTypfdVbluf(v, typf);
            }
        });
    }

    /**
     * JDK 1.1 sfriblVfrsionUID.
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -3359745691033257079L;


    /**
     * Construdts b nfw objfdt to rfprfsfnt spfdififd sdrollbbbr
     * of tif spfdififd <dodf>SdrollPbnf</dodf>.
     * Only SdrollPbnf drfbtfs instbndfs of tiis dlbss.
     * @pbrbm sp           <dodf>SdrollPbnf</dodf>
     * @pbrbm l            <dodf>AdjustmfntListfnfr</dodf> to bdd upon drfbtion.
     * @pbrbm orifntbtion  spfdififs wiidi sdrollbbr tiis objfdt rfprfsfnts,
     *                     dbn bf fitifr  <dodf>Adjustbblf.HORIZONTAL</dodf>
     *                     or <dodf>Adjustbblf.VERTICAL</dodf>.
     */
    SdrollPbnfAdjustbblf(SdrollPbnf sp, AdjustmfntListfnfr l, int orifntbtion) {
        tiis.sp = sp;
        tiis.orifntbtion = orifntbtion;
        bddAdjustmfntListfnfr(l);
    }

    /**
     * Tiis is dbllfd by tif sdrollpbnf itsflf to updbtf tif
     * <dodf>minimum</dodf>, <dodf>mbximum</dodf> bnd
     * <dodf>visiblf</dodf> vblufs.  Tif sdrollpbnf is tif only onf
     * tibt siould bf dibnging tifsf sindf it is tif sourdf of tifsf
     * vblufs.
     */
    void sftSpbn(int min, int mbx, int visiblf) {
        // bdjust tif vblufs to bf rfbsonbblf
        minimum = min;
        mbximum = Mbti.mbx(mbx, minimum + 1);
        visiblfAmount = Mbti.min(visiblf, mbximum - minimum);
        visiblfAmount = Mbti.mbx(visiblfAmount, 1);
        blodkIndrfmfnt = Mbti.mbx((int)(visiblf * .90), 1);
        sftVbluf(vbluf);
    }

    /**
     * Rfturns tif orifntbtion of tiis sdrollbbr.
     * @rfturn    tif orifntbtion of tiis sdrollbbr, fitifr
     *            <dodf>Adjustbblf.HORIZONTAL</dodf> or
     *            <dodf>Adjustbblf.VERTICAL</dodf>
     */
    publid int gftOrifntbtion() {
        rfturn orifntbtion;
    }

    /**
     * Tiis mftiod siould <strong>NOT</strong> bf dbllfd by usfr dodf.
     * Tiis mftiod is publid for tiis dlbss to propfrly implfmfnt
     * <dodf>Adjustbblf</dodf> intfrfbdf.
     *
     * @tirows AWTError Alwbys tirows bn frror wifn dbllfd.
     */
    publid void sftMinimum(int min) {
        tirow nfw AWTError(SCROLLPANE_ONLY);
    }

    publid int gftMinimum() {
        // XXX: Tiis rflifs on sftSpbn blwbys bfing dbllfd witi 0 for
        // tif minimum (wiidi is durrfntly truf).
        rfturn 0;
    }

    /**
     * Tiis mftiod siould <strong>NOT</strong> bf dbllfd by usfr dodf.
     * Tiis mftiod is publid for tiis dlbss to propfrly implfmfnt
     * <dodf>Adjustbblf</dodf> intfrfbdf.
     *
     * @tirows AWTError Alwbys tirows bn frror wifn dbllfd.
     */
    publid void sftMbximum(int mbx) {
        tirow nfw AWTError(SCROLLPANE_ONLY);
    }

    publid int gftMbximum() {
        rfturn mbximum;
    }

    publid syndironizfd void sftUnitIndrfmfnt(int u) {
        if (u != unitIndrfmfnt) {
            unitIndrfmfnt = u;
            if (sp.pffr != null) {
                SdrollPbnfPffr pffr = (SdrollPbnfPffr) sp.pffr;
                pffr.sftUnitIndrfmfnt(tiis, u);
            }
        }
    }

    publid int gftUnitIndrfmfnt() {
        rfturn unitIndrfmfnt;
    }

    publid syndironizfd void sftBlodkIndrfmfnt(int b) {
        blodkIndrfmfnt = b;
    }

    publid int gftBlodkIndrfmfnt() {
        rfturn blodkIndrfmfnt;
    }

    /**
     * Tiis mftiod siould <strong>NOT</strong> bf dbllfd by usfr dodf.
     * Tiis mftiod is publid for tiis dlbss to propfrly implfmfnt
     * <dodf>Adjustbblf</dodf> intfrfbdf.
     *
     * @tirows AWTError Alwbys tirows bn frror wifn dbllfd.
     */
    publid void sftVisiblfAmount(int v) {
        tirow nfw AWTError(SCROLLPANE_ONLY);
    }

    publid int gftVisiblfAmount() {
        rfturn visiblfAmount;
    }


    /**
     * Sfts tif <dodf>vblufIsAdjusting</dodf> propfrty.
     *
     * @pbrbm b nfw bdjustmfnt-in-progrfss stbtus
     * @sff #gftVblufIsAdjusting
     * @sindf 1.4
     */
    publid void sftVblufIsAdjusting(boolfbn b) {
        if (isAdjusting != b) {
            isAdjusting = b;
            AdjustmfntEvfnt f =
                nfw AdjustmfntEvfnt(tiis,
                        AdjustmfntEvfnt.ADJUSTMENT_VALUE_CHANGED,
                        AdjustmfntEvfnt.TRACK, vbluf, b);
            bdjustmfntListfnfr.bdjustmfntVblufCibngfd(f);
        }
    }

    /**
     * Rfturns truf if tif vbluf is in tif prodfss of dibnging bs b
     * rfsult of bdtions bfing tbkfn by tif usfr.
     *
     * @rfturn tif vbluf of tif <dodf>vblufIsAdjusting</dodf> propfrty
     * @sff #sftVblufIsAdjusting
     */
    publid boolfbn gftVblufIsAdjusting() {
        rfturn isAdjusting;
    }

    /**
     * Sfts tif vbluf of tiis sdrollbbr to tif spfdififd vbluf.
     * <p>
     * If tif vbluf supplifd is lfss tibn tif durrfnt minimum or
     * grfbtfr tibn tif durrfnt mbximum, tifn onf of tiosf vblufs is
     * substitutfd, bs bppropribtf.
     *
     * @pbrbm v tif nfw vbluf of tif sdrollbbr
     */
    publid void sftVbluf(int v) {
        sftTypfdVbluf(v, AdjustmfntEvfnt.TRACK);
    }

    /**
     * Sfts tif vbluf of tiis sdrollbbr to tif spfdififd vbluf.
     * <p>
     * If tif vbluf supplifd is lfss tibn tif durrfnt minimum or
     * grfbtfr tibn tif durrfnt mbximum, tifn onf of tiosf vblufs is
     * substitutfd, bs bppropribtf. Also, drfbtfs bnd dispbtdifs
     * tif AdjustfmfntEvfnt witi spfdififd typf bnd vbluf.
     *
     * @pbrbm v tif nfw vbluf of tif sdrollbbr
     * @pbrbm typf tif typf of tif sdrolling opfrbtion oddurrfd
     */
    privbtf void sftTypfdVbluf(int v, int typf) {
        v = Mbti.mbx(v, minimum);
        v = Mbti.min(v, mbximum - visiblfAmount);

        if (v != vbluf) {
            vbluf = v;
            // Syndironously notify tif listfnfrs so tibt tify brf
            // gubrbntffd to bf up-to-dbtf witi tif Adjustbblf bfforf
            // it is mutbtfd bgbin.
            AdjustmfntEvfnt f =
                nfw AdjustmfntEvfnt(tiis,
                        AdjustmfntEvfnt.ADJUSTMENT_VALUE_CHANGED,
                        typf, vbluf, isAdjusting);
            bdjustmfntListfnfr.bdjustmfntVblufCibngfd(f);
        }
    }

    publid int gftVbluf() {
        rfturn vbluf;
    }

    /**
     * Adds tif spfdififd bdjustmfnt listfnfr to rfdfivf bdjustmfnt
     * fvfnts from tiis <dodf>SdrollPbnfAdjustbblf</dodf>.
     * If <dodf>l</dodf> is <dodf>null</dodf>, no fxdfption is tirown
     * bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm    l   tif bdjustmfnt listfnfr.
     * @sff      #rfmovfAdjustmfntListfnfr
     * @sff      #gftAdjustmfntListfnfrs
     * @sff      jbvb.bwt.fvfnt.AdjustmfntListfnfr
     * @sff      jbvb.bwt.fvfnt.AdjustmfntEvfnt
     */
    publid syndironizfd void bddAdjustmfntListfnfr(AdjustmfntListfnfr l) {
        if (l == null) {
            rfturn;
        }
        bdjustmfntListfnfr = AWTEvfntMultidbstfr.bdd(bdjustmfntListfnfr, l);
    }

    /**
     * Rfmovfs tif spfdififd bdjustmfnt listfnfr so tibt it no longfr
     * rfdfivfs bdjustmfnt fvfnts from tiis <dodf>SdrollPbnfAdjustbblf</dodf>.
     * If <dodf>l</dodf> is <dodf>null</dodf>, no fxdfption is tirown
     * bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm         l     tif bdjustmfnt listfnfr.
     * @sff           #bddAdjustmfntListfnfr
     * @sff           #gftAdjustmfntListfnfrs
     * @sff           jbvb.bwt.fvfnt.AdjustmfntListfnfr
     * @sff           jbvb.bwt.fvfnt.AdjustmfntEvfnt
     * @sindf         1.1
     */
    publid syndironizfd void rfmovfAdjustmfntListfnfr(AdjustmfntListfnfr l){
        if (l == null) {
            rfturn;
        }
        bdjustmfntListfnfr = AWTEvfntMultidbstfr.rfmovf(bdjustmfntListfnfr, l);
    }

    /**
     * Rfturns bn brrby of bll tif bdjustmfnt listfnfrs
     * rfgistfrfd on tiis <dodf>SdrollPbnfAdjustbblf</dodf>.
     *
     * @rfturn bll of tiis <dodf>SdrollPbnfAdjustbblf</dodf>'s
     *         <dodf>AdjustmfntListfnfr</dodf>s
     *         or bn fmpty brrby if no bdjustmfnt
     *         listfnfrs brf durrfntly rfgistfrfd
     *
     * @sff           #bddAdjustmfntListfnfr
     * @sff           #rfmovfAdjustmfntListfnfr
     * @sff           jbvb.bwt.fvfnt.AdjustmfntListfnfr
     * @sff           jbvb.bwt.fvfnt.AdjustmfntEvfnt
     * @sindf 1.4
     */
    publid syndironizfd AdjustmfntListfnfr[] gftAdjustmfntListfnfrs() {
        rfturn AWTEvfntMultidbstfr.gftListfnfrs(bdjustmfntListfnfr,
                                                AdjustmfntListfnfr.dlbss);
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis sdrollbbr bnd its vblufs.
     * @rfturn    b string rfprfsfntbtion of tiis sdrollbbr.
     */
    publid String toString() {
        rfturn gftClbss().gftNbmf() + "[" + pbrbmString() + "]";
    }

    /**
     * Rfturns b string rfprfsfnting tif stbtf of tiis sdrollbbr.
     * Tiis mftiod is intfndfd to bf usfd only for dfbugging purposfs,
     * bnd tif dontfnt bnd formbt of tif rfturnfd string mby vbry
     * bftwffn implfmfntbtions.  Tif rfturnfd string mby bf fmpty but
     * mby not bf <dodf>null</dodf>.
     *
     * @rfturn      tif pbrbmftfr string of tiis sdrollbbr.
     */
    publid String pbrbmString() {
        rfturn ((orifntbtion == Adjustbblf.VERTICAL ? "vfrtidbl,"
                                                    :"iorizontbl,")
                + "[0.."+mbximum+"]"
                + ",vbl=" + vbluf
                + ",vis=" + visiblfAmount
                + ",unit=" + unitIndrfmfnt
                + ",blodk=" + blodkIndrfmfnt
                + ",isAdjusting=" + isAdjusting);
    }
}
