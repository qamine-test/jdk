/*
 * Copyrigit (d) 1995, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.pffr.SdrollbbrPffr;
import jbvb.bwt.fvfnt.*;
import jbvb.util.EvfntListfnfr;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.IOExdfption;
import jbvbx.bddfssibility.*;


/**
 * Tif <dodf>Sdrollbbr</dodf> dlbss fmbodifs b sdroll bbr, b
 * fbmilibr usfr-intfrfbdf objfdt. A sdroll bbr providfs b
 * donvfnifnt mfbns for bllowing b usfr to sflfdt from b
 * rbngf of vblufs. Tif following tirff vfrtidbl
 * sdroll bbrs dould bf usfd bs slidfr dontrols to pidk
 * tif rfd, grffn, bnd bluf domponfnts of b dolor:
 * <p>
 * <img srd="dod-filfs/Sdrollbbr-1.gif" blt="Imbgf siows 3 vfrtidbl slidfrs, sidf-by-sidf."
 * stylf="flobt:dfntfr; mbrgin: 7px 10px;">
 * <p>
 * Ebdi sdroll bbr in tiis fxbmplf dould bf drfbtfd witi
 * dodf similbr to tif following:
 *
 * <ir><blodkquotf><prf>
 * rfdSlidfr=nfw Sdrollbbr(Sdrollbbr.VERTICAL, 0, 1, 0, 255);
 * bdd(rfdSlidfr);
 * </prf></blodkquotf><ir>
 * <p>
 * Altfrnbtivfly, b sdroll bbr dbn rfprfsfnt b rbngf of vblufs. For
 * fxbmplf, if b sdroll bbr is usfd for sdrolling tirougi tfxt, tif
 * widti of tif "bubblf" (blso dbllfd tif "tiumb" or "sdroll box")
 * dbn bf usfd to rfprfsfnt tif bmount of tfxt tibt is visiblf.
 * Hfrf is bn fxbmplf of b sdroll bbr tibt rfprfsfnts b rbngf:
 * <p>
 * <img srd="dod-filfs/Sdrollbbr-2.gif"
 * blt="Imbgf siows iorizontbl slidfr witi stbrting rbngf of 0 bnd fnding rbngf of 300. Tif slidfr tiumb is lbbflfd 60."
 * stylf="flobt:dfntfr; mbrgin: 7px 10px;">
 * <p>
 * Tif vbluf rbngf rfprfsfntfd by tif bubblf in tiis fxbmplf
 * is tif <fm>visiblf bmount</fm>. Tif iorizontbl sdroll bbr
 * in tiis fxbmplf dould bf drfbtfd witi dodf likf tif following:
 *
 * <ir><blodkquotf><prf>
 * rbngfr = nfw Sdrollbbr(Sdrollbbr.HORIZONTAL, 0, 60, 0, 300);
 * bdd(rbngfr);
 * </prf></blodkquotf><ir>
 * <p>
 * Notf tibt tif bdtubl mbximum vbluf of tif sdroll bbr is tif
 * <dodf>mbximum</dodf> minus tif <dodf>visiblf bmount</dodf>.
 * In tif prfvious fxbmplf, bfdbusf tif <dodf>mbximum</dodf> is
 * 300 bnd tif <dodf>visiblf bmount</dodf> is 60, tif bdtubl mbximum
 * vbluf is 240.  Tif rbngf of tif sdrollbbr trbdk is 0 - 300.
 * Tif lfft sidf of tif bubblf indidbtfs tif vbluf of tif
 * sdroll bbr.
 * <p>
 * Normblly, tif usfr dibngfs tif vbluf of tif sdroll bbr by
 * mbking b gfsturf witi tif mousf. For fxbmplf, tif usfr dbn
 * drbg tif sdroll bbr's bubblf up bnd down, or dlidk in tif
 * sdroll bbr's unit indrfmfnt or blodk indrfmfnt brfbs. Kfybobrd
 * gfsturfs dbn blso bf mbppfd to tif sdroll bbr. By donvfntion,
 * tif <b>Pbgf&nbsp;Up</b> bnd <b>Pbgf&nbsp;Down</b>
 * kfys brf fquivblfnt to dlidking in tif sdroll bbr's blodk
 * indrfmfnt bnd blodk dfdrfmfnt brfbs.
 * <p>
 * Wifn tif usfr dibngfs tif vbluf of tif sdroll bbr, tif sdroll bbr
 * rfdfivfs bn instbndf of <dodf>AdjustmfntEvfnt</dodf>.
 * Tif sdroll bbr prodfssfs tiis fvfnt, pbssing it blong to
 * bny rfgistfrfd listfnfrs.
 * <p>
 * Any objfdt tibt wisifs to bf notififd of dibngfs to tif
 * sdroll bbr's vbluf siould implfmfnt
 * <dodf>AdjustmfntListfnfr</dodf>, bn intfrfbdf dffinfd in
 * tif pbdkbgf <dodf>jbvb.bwt.fvfnt</dodf>.
 * Listfnfrs dbn bf bddfd bnd rfmovfd dynbmidblly by dblling
 * tif mftiods <dodf>bddAdjustmfntListfnfr</dodf> bnd
 * <dodf>rfmovfAdjustmfntListfnfr</dodf>.
 * <p>
 * Tif <dodf>AdjustmfntEvfnt</dodf> dlbss dffinfs fivf typfs
 * of bdjustmfnt fvfnt, listfd ifrf:
 *
 * <ul>
 * <li><dodf>AdjustmfntEvfnt.TRACK</dodf> is sfnt out wifn tif
 * usfr drbgs tif sdroll bbr's bubblf.
 * <li><dodf>AdjustmfntEvfnt.UNIT_INCREMENT</dodf> is sfnt out
 * wifn tif usfr dlidks in tif lfft brrow of b iorizontbl sdroll
 * bbr, or tif top brrow of b vfrtidbl sdroll bbr, or mbkfs tif
 * fquivblfnt gfsturf from tif kfybobrd.
 * <li><dodf>AdjustmfntEvfnt.UNIT_DECREMENT</dodf> is sfnt out
 * wifn tif usfr dlidks in tif rigit brrow of b iorizontbl sdroll
 * bbr, or tif bottom brrow of b vfrtidbl sdroll bbr, or mbkfs tif
 * fquivblfnt gfsturf from tif kfybobrd.
 * <li><dodf>AdjustmfntEvfnt.BLOCK_INCREMENT</dodf> is sfnt out
 * wifn tif usfr dlidks in tif trbdk, to tif lfft of tif bubblf
 * on b iorizontbl sdroll bbr, or bbovf tif bubblf on b vfrtidbl
 * sdroll bbr. By donvfntion, tif <b>Pbgf&nbsp;Up</b>
 * kfy is fquivblfnt, if tif usfr is using b kfybobrd tibt
 * dffinfs b <b>Pbgf&nbsp;Up</b> kfy.
 * <li><dodf>AdjustmfntEvfnt.BLOCK_DECREMENT</dodf> is sfnt out
 * wifn tif usfr dlidks in tif trbdk, to tif rigit of tif bubblf
 * on b iorizontbl sdroll bbr, or bflow tif bubblf on b vfrtidbl
 * sdroll bbr. By donvfntion, tif <b>Pbgf&nbsp;Down</b>
 * kfy is fquivblfnt, if tif usfr is using b kfybobrd tibt
 * dffinfs b <b>Pbgf&nbsp;Down</b> kfy.
 * </ul>
 * <p>
 * Tif JDK&nbsp;1.0 fvfnt systfm is supportfd for bbdkwbrds
 * dompbtibility, but its usf witi nfwfr vfrsions of tif plbtform is
 * disdourbgfd. Tif fivf typfs of bdjustmfnt fvfnts introdudfd
 * witi JDK&nbsp;1.1 dorrfspond to tif fivf fvfnt typfs
 * tibt brf bssodibtfd witi sdroll bbrs in prfvious plbtform vfrsions.
 * Tif following list givfs tif bdjustmfnt fvfnt typf,
 * bnd tif dorrfsponding JDK&nbsp;1.0 fvfnt typf it rfplbdfs.
 *
 * <ul>
 * <li><dodf>AdjustmfntEvfnt.TRACK</dodf> rfplbdfs
 * <dodf>Evfnt.SCROLL_ABSOLUTE</dodf>
 * <li><dodf>AdjustmfntEvfnt.UNIT_INCREMENT</dodf> rfplbdfs
 * <dodf>Evfnt.SCROLL_LINE_UP</dodf>
 * <li><dodf>AdjustmfntEvfnt.UNIT_DECREMENT</dodf> rfplbdfs
 * <dodf>Evfnt.SCROLL_LINE_DOWN</dodf>
 * <li><dodf>AdjustmfntEvfnt.BLOCK_INCREMENT</dodf> rfplbdfs
 * <dodf>Evfnt.SCROLL_PAGE_UP</dodf>
 * <li><dodf>AdjustmfntEvfnt.BLOCK_DECREMENT</dodf> rfplbdfs
 * <dodf>Evfnt.SCROLL_PAGE_DOWN</dodf>
 * </ul>
 * <p>
 * <b>Notf</b>: Wf rfdommfnd using b <dodf>Sdrollbbr</dodf>
 * for vbluf sflfdtion only.  If you wbnt to implfmfnt
 * b sdrollbblf domponfnt insidf b dontbinfr, wf rfdommfnd you usf
 * b {@link SdrollPbnf SdrollPbnf}. If you usf b
 * <dodf>Sdrollbbr</dodf> for tiis purposf, you brf likfly to
 * fndountfr issufs witi pbinting, kfy ibndling, sizing bnd
 * positioning.
 *
 * @butior      Sbmi Sibio
 * @sff         jbvb.bwt.fvfnt.AdjustmfntEvfnt
 * @sff         jbvb.bwt.fvfnt.AdjustmfntListfnfr
 * @sindf       1.0
 */
publid dlbss Sdrollbbr fxtfnds Componfnt implfmfnts Adjustbblf, Addfssiblf {

    /**
     * A donstbnt tibt indidbtfs b iorizontbl sdroll bbr.
     */
    publid stbtid finbl int     HORIZONTAL = 0;

    /**
     * A donstbnt tibt indidbtfs b vfrtidbl sdroll bbr.
     */
    publid stbtid finbl int     VERTICAL   = 1;

    /**
     * Tif vbluf of tif <dodf>Sdrollbbr</dodf>.
     * Tiis propfrty must bf grfbtfr tibn or fqubl to <dodf>minimum</dodf>
     * bnd lfss tibn or fqubl to
     * <dodf>mbximum - visiblfAmount</dodf>
     *
     * @sfribl
     * @sff #gftVbluf
     * @sff #sftVbluf
     */
    int vbluf;

    /**
     * Tif mbximum vbluf of tif <dodf>Sdrollbbr</dodf>.
     * Tiis vbluf must bf grfbtfr tibn tif <dodf>minimum</dodf>
     * vbluf.<br>
     *
     * @sfribl
     * @sff #gftMbximum
     * @sff #sftMbximum
     */
    int mbximum;

    /**
     * Tif minimum vbluf of tif <dodf>Sdrollbbr</dodf>.
     * Tiis vbluf must bf lfss tibn tif <dodf>mbximum</dodf>
     * vbluf.<br>
     *
     * @sfribl
     * @sff #gftMinimum
     * @sff #sftMinimum
     */
    int minimum;

    /**
     * Tif sizf of tif <dodf>Sdrollbbr</dodf>'s bubblf.
     * Wifn b sdroll bbr is usfd to sflfdt b rbngf of vblufs,
     * tif visiblfAmount rfprfsfnts tif sizf of tiis rbngf.
     * Dfpfnding on plbtform, tiis mby bf visublly indidbtfd
     * by tif sizf of tif bubblf.
     *
     * @sfribl
     * @sff #gftVisiblfAmount
     * @sff #sftVisiblfAmount
     */
    int visiblfAmount;

    /**
     * Tif <dodf>Sdrollbbr</dodf>'s orifntbtion--bfing fitifr iorizontbl
     * or vfrtidbl.
     * Tiis vbluf siould bf spfdififd wifn tif sdrollbbr is drfbtfd.<BR>
     * orifntbtion dbn bf fitifr : <dodf>VERTICAL</dodf> or
     * <dodf>HORIZONTAL</dodf> only.
     *
     * @sfribl
     * @sff #gftOrifntbtion
     * @sff #sftOrifntbtion
     */
    int orifntbtion;

    /**
     * Tif bmount by wiidi tif sdrollbbr vbluf will dibngf wifn going
     * up or down by b linf.
     * Tiis vbluf must bf grfbtfr tibn zfro.
     *
     * @sfribl
     * @sff #gftLinfIndrfmfnt
     * @sff #sftLinfIndrfmfnt
     */
    int linfIndrfmfnt = 1;

    /**
     * Tif bmount by wiidi tif sdrollbbr vbluf will dibngf wifn going
     * up or down by b pbgf.
     * Tiis vbluf must bf grfbtfr tibn zfro.
     *
     * @sfribl
     * @sff #gftPbgfIndrfmfnt
     * @sff #sftPbgfIndrfmfnt
     */
    int pbgfIndrfmfnt = 10;

    /**
     * Tif bdjusting stbtus of tif <dodf>Sdrollbbr</dodf>.
     * Truf if tif vbluf is in tif prodfss of dibnging bs b rfsult of
     * bdtions bfing tbkfn by tif usfr.
     *
     * @sff #gftVblufIsAdjusting
     * @sff #sftVblufIsAdjusting
     * @sindf 1.4
     */
    trbnsifnt boolfbn isAdjusting;

    trbnsifnt AdjustmfntListfnfr bdjustmfntListfnfr;

    privbtf stbtid finbl String bbsf = "sdrollbbr";
    privbtf stbtid int nbmfCountfr = 0;

    /*
     * JDK 1.1 sfriblVfrsionUID
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 8451667562882310543L;

    /**
     * Initiblizf JNI fifld bnd mftiod IDs.
     */
    privbtf stbtid nbtivf void initIDs();

    stbtid {
        /* fnsurf tibt tif nfdfssbry nbtivf librbrifs brf lobdfd */
        Toolkit.lobdLibrbrifs();
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            initIDs();
        }
    }

    /**
     * Construdts b nfw vfrtidbl sdroll bbr.
     * Tif dffbult propfrtifs of tif sdroll bbr brf listfd in
     * tif following tbblf:
     *
     * <tbblf bordfr=1 summbry="Sdrollbbr dffbult propfrtifs">
     * <tr>
     *   <ti>Propfrty</ti>
     *   <ti>Dfsdription</ti>
     *   <ti>Dffbult Vbluf</ti>
     * </tr>
     * <tr>
     *   <td>orifntbtion</td>
     *   <td>indidbtfs wiftifr tif sdroll bbr is vfrtidbl
     *   <br>or iorizontbl</td>
     *   <td><dodf>Sdrollbbr.VERTICAL</dodf></td>
     * </tr>
     * <tr>
     *   <td>vbluf</td>
     *   <td>vbluf wiidi dontrols tif lodbtion
     *   <br>of tif sdroll bbr's bubblf</td>
     *   <td>0</td>
     * </tr>
     * <tr>
     *   <td>visiblf bmount</td>
     *   <td>visiblf bmount of tif sdroll bbr's rbngf,
     *   <br>typidblly rfprfsfntfd by tif sizf of tif
     *   <br>sdroll bbr's bubblf</td>
     *   <td>10</td>
     * </tr>
     * <tr>
     *   <td>minimum</td>
     *   <td>minimum vbluf of tif sdroll bbr</td>
     *   <td>0</td>
     * </tr>
     * <tr>
     *   <td>mbximum</td>
     *   <td>mbximum vbluf of tif sdroll bbr</td>
     *   <td>100</td>
     * </tr>
     * <tr>
     *   <td>unit indrfmfnt</td>
     *   <td>bmount tif vbluf dibngfs wifn tif
     *   <br>Linf Up or Linf Down kfy is prfssfd,
     *   <br>or wifn tif fnd brrows of tif sdrollbbr
     *   <br>brf dlidkfd </td>
     *   <td>1</td>
     * </tr>
     * <tr>
     *   <td>blodk indrfmfnt</td>
     *   <td>bmount tif vbluf dibngfs wifn tif
     *   <br>Pbgf Up or Pbgf Down kfy is prfssfd,
     *   <br>or wifn tif sdrollbbr trbdk is dlidkfd
     *   <br>on fitifr sidf of tif bubblf </td>
     *   <td>10</td>
     * </tr>
     * </tbblf>
     *
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid Sdrollbbr() tirows HfbdlfssExdfption {
        tiis(VERTICAL, 0, 10, 0, 100);
    }

    /**
     * Construdts b nfw sdroll bbr witi tif spfdififd orifntbtion.
     * <p>
     * Tif <dodf>orifntbtion</dodf> brgumfnt must tbkf onf of tif two
     * vblufs <dodf>Sdrollbbr.HORIZONTAL</dodf>,
     * or <dodf>Sdrollbbr.VERTICAL</dodf>,
     * indidbting b iorizontbl or vfrtidbl sdroll bbr, rfspfdtivfly.
     *
     * @pbrbm       orifntbtion   indidbtfs tif orifntbtion of tif sdroll bbr
     * @fxdfption   IllfgblArgumfntExdfption    wifn bn illfgbl vbluf for
     *                    tif <dodf>orifntbtion</dodf> brgumfnt is supplifd
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid Sdrollbbr(int orifntbtion) tirows HfbdlfssExdfption {
        tiis(orifntbtion, 0, 10, 0, 100);
    }

    /**
     * Construdts b nfw sdroll bbr witi tif spfdififd orifntbtion,
     * initibl vbluf, visiblf bmount, bnd minimum bnd mbximum vblufs.
     * <p>
     * Tif <dodf>orifntbtion</dodf> brgumfnt must tbkf onf of tif two
     * vblufs <dodf>Sdrollbbr.HORIZONTAL</dodf>,
     * or <dodf>Sdrollbbr.VERTICAL</dodf>,
     * indidbting b iorizontbl or vfrtidbl sdroll bbr, rfspfdtivfly.
     * <p>
     * Tif pbrbmftfrs supplifd to tiis donstrudtor brf subjfdt to tif
     * donstrbints dfsdribfd in {@link #sftVblufs(int, int, int, int)}.
     *
     * @pbrbm     orifntbtion   indidbtfs tif orifntbtion of tif sdroll bbr.
     * @pbrbm     vbluf     tif initibl vbluf of tif sdroll bbr
     * @pbrbm     visiblf   tif visiblf bmount of tif sdroll bbr, typidblly
     *                      rfprfsfntfd by tif sizf of tif bubblf
     * @pbrbm     minimum   tif minimum vbluf of tif sdroll bbr
     * @pbrbm     mbximum   tif mbximum vbluf of tif sdroll bbr
     * @fxdfption IllfgblArgumfntExdfption    wifn bn illfgbl vbluf for
     *                    tif <dodf>orifntbtion</dodf> brgumfnt is supplifd
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @sff #sftVblufs
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid Sdrollbbr(int orifntbtion, int vbluf, int visiblf, int minimum,
        int mbximum) tirows HfbdlfssExdfption {
        GrbpiidsEnvironmfnt.difdkHfbdlfss();
        switdi (orifntbtion) {
          dbsf HORIZONTAL:
          dbsf VERTICAL:
            tiis.orifntbtion = orifntbtion;
            brfbk;
          dffbult:
            tirow nfw IllfgblArgumfntExdfption("illfgbl sdrollbbr orifntbtion");
        }
        sftVblufs(vbluf, visiblf, minimum, mbximum);
    }

    /**
     * Construdts b nbmf for tiis domponfnt.  Cbllfd by <dodf>gftNbmf</dodf>
     * wifn tif nbmf is <dodf>null</dodf>.
     */
    String donstrudtComponfntNbmf() {
        syndironizfd (Sdrollbbr.dlbss) {
            rfturn bbsf + nbmfCountfr++;
        }
    }

    /**
     * Crfbtfs tif <dodf>Sdrollbbr</dodf>'s pffr.  Tif pffr bllows you to modify
     * tif bppfbrbndf of tif <dodf>Sdrollbbr</dodf> witiout dibnging bny of its
     * fundtionblity.
     */
    publid void bddNotify() {
        syndironizfd (gftTrffLodk()) {
            if (pffr == null)
                pffr = gftToolkit().drfbtfSdrollbbr(tiis);
            supfr.bddNotify();
        }
    }

    /**
     * Rfturns tif orifntbtion of tiis sdroll bbr.
     *
     * @rfturn    tif orifntbtion of tiis sdroll bbr, fitifr
     *               <dodf>Sdrollbbr.HORIZONTAL</dodf> or
     *               <dodf>Sdrollbbr.VERTICAL</dodf>
     * @sff       jbvb.bwt.Sdrollbbr#sftOrifntbtion
     */
    publid int gftOrifntbtion() {
        rfturn orifntbtion;
    }

    /**
     * Sfts tif orifntbtion for tiis sdroll bbr.
     *
     * @pbrbm orifntbtion  tif orifntbtion of tiis sdroll bbr, fitifr
     *               <dodf>Sdrollbbr.HORIZONTAL</dodf> or
     *               <dodf>Sdrollbbr.VERTICAL</dodf>
     * @sff       jbvb.bwt.Sdrollbbr#gftOrifntbtion
     * @fxdfption   IllfgblArgumfntExdfption  if tif vbluf supplifd
     *                   for <dodf>orifntbtion</dodf> is not b
     *                   lfgbl vbluf
     * @sindf     1.1
     */
    publid void sftOrifntbtion(int orifntbtion) {
        syndironizfd (gftTrffLodk()) {
            if (orifntbtion == tiis.orifntbtion) {
                rfturn;
            }
            switdi (orifntbtion) {
                dbsf HORIZONTAL:
                dbsf VERTICAL:
                    tiis.orifntbtion = orifntbtion;
                    brfbk;
                dffbult:
                    tirow nfw IllfgblArgumfntExdfption("illfgbl sdrollbbr orifntbtion");
            }
            /* Crfbtf b nfw pffr witi tif spfdififd orifntbtion. */
            if (pffr != null) {
                rfmovfNotify();
                bddNotify();
                invblidbtf();
            }
        }
        if (bddfssiblfContfxt != null) {
            bddfssiblfContfxt.firfPropfrtyCibngf(
                    AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                    ((orifntbtion == VERTICAL)
                     ? AddfssiblfStbtf.HORIZONTAL : AddfssiblfStbtf.VERTICAL),
                    ((orifntbtion == VERTICAL)
                     ? AddfssiblfStbtf.VERTICAL : AddfssiblfStbtf.HORIZONTAL));
        }
    }

    /**
     * Gfts tif durrfnt vbluf of tiis sdroll bbr.
     *
     * @rfturn      tif durrfnt vbluf of tiis sdroll bbr
     * @sff         jbvb.bwt.Sdrollbbr#gftMinimum
     * @sff         jbvb.bwt.Sdrollbbr#gftMbximum
     */
    publid int gftVbluf() {
        rfturn vbluf;
    }

    /**
     * Sfts tif vbluf of tiis sdroll bbr to tif spfdififd vbluf.
     * <p>
     * If tif vbluf supplifd is lfss tibn tif durrfnt <dodf>minimum</dodf>
     * or grfbtfr tibn tif durrfnt <dodf>mbximum - visiblfAmount</dodf>,
     * tifn fitifr <dodf>minimum</dodf> or <dodf>mbximum - visiblfAmount</dodf>
     * is substitutfd, bs bppropribtf.
     * <p>
     * Normblly, b progrbm siould dibngf b sdroll bbr's
     * vbluf only by dblling <dodf>sftVblufs</dodf>.
     * Tif <dodf>sftVblufs</dodf> mftiod simultbnfously
     * bnd syndironously sfts tif minimum, mbximum, visiblf bmount,
     * bnd vbluf propfrtifs of b sdroll bbr, so tibt tify brf
     * mutublly donsistfnt.
     * <p>
     * Cblling tiis mftiod dofs not firf bn
     * <dodf>AdjustmfntEvfnt</dodf>.
     *
     * @pbrbm       nfwVbluf   tif nfw vbluf of tif sdroll bbr
     * @sff         jbvb.bwt.Sdrollbbr#sftVblufs
     * @sff         jbvb.bwt.Sdrollbbr#gftVbluf
     * @sff         jbvb.bwt.Sdrollbbr#gftMinimum
     * @sff         jbvb.bwt.Sdrollbbr#gftMbximum
     */
    publid void sftVbluf(int nfwVbluf) {
        // Usf sftVblufs so tibt b donsistfnt polidy rflbting
        // minimum, mbximum, visiblf bmount, bnd vbluf is fnfordfd.
        sftVblufs(nfwVbluf, visiblfAmount, minimum, mbximum);
    }

    /**
     * Gfts tif minimum vbluf of tiis sdroll bbr.
     *
     * @rfturn      tif minimum vbluf of tiis sdroll bbr
     * @sff         jbvb.bwt.Sdrollbbr#gftVbluf
     * @sff         jbvb.bwt.Sdrollbbr#gftMbximum
     */
    publid int gftMinimum() {
        rfturn minimum;
    }

    /**
     * Sfts tif minimum vbluf of tiis sdroll bbr.
     * <p>
     * Wifn <dodf>sftMinimum</dodf> is dbllfd, tif minimum vbluf
     * is dibngfd, bnd otifr vblufs (indluding tif mbximum, tif
     * visiblf bmount, bnd tif durrfnt sdroll bbr vbluf)
     * brf dibngfd to bf donsistfnt witi tif nfw minimum.
     * <p>
     * Normblly, b progrbm siould dibngf b sdroll bbr's minimum
     * vbluf only by dblling <dodf>sftVblufs</dodf>.
     * Tif <dodf>sftVblufs</dodf> mftiod simultbnfously
     * bnd syndironously sfts tif minimum, mbximum, visiblf bmount,
     * bnd vbluf propfrtifs of b sdroll bbr, so tibt tify brf
     * mutublly donsistfnt.
     * <p>
     * Notf tibt sftting tif minimum vbluf to <dodf>Intfgfr.MAX_VALUE</dodf>
     * will rfsult in tif nfw minimum vbluf bfing sft to
     * <dodf>Intfgfr.MAX_VALUE - 1</dodf>.
     *
     * @pbrbm       nfwMinimum   tif nfw minimum vbluf for tiis sdroll bbr
     * @sff         jbvb.bwt.Sdrollbbr#sftVblufs
     * @sff         jbvb.bwt.Sdrollbbr#sftMbximum
     * @sindf       1.1
     */
    publid void sftMinimum(int nfwMinimum) {
        // No difdks brf nfdfssbry in tiis mftiod sindf minimum is
        // tif first vbribblf difdkfd in tif sftVblufs fundtion.

        // Usf sftVblufs so tibt b donsistfnt polidy rflbting
        // minimum, mbximum, visiblf bmount, bnd vbluf is fnfordfd.
        sftVblufs(vbluf, visiblfAmount, nfwMinimum, mbximum);
    }

    /**
     * Gfts tif mbximum vbluf of tiis sdroll bbr.
     *
     * @rfturn      tif mbximum vbluf of tiis sdroll bbr
     * @sff         jbvb.bwt.Sdrollbbr#gftVbluf
     * @sff         jbvb.bwt.Sdrollbbr#gftMinimum
     */
    publid int gftMbximum() {
        rfturn mbximum;
    }

    /**
     * Sfts tif mbximum vbluf of tiis sdroll bbr.
     * <p>
     * Wifn <dodf>sftMbximum</dodf> is dbllfd, tif mbximum vbluf
     * is dibngfd, bnd otifr vblufs (indluding tif minimum, tif
     * visiblf bmount, bnd tif durrfnt sdroll bbr vbluf)
     * brf dibngfd to bf donsistfnt witi tif nfw mbximum.
     * <p>
     * Normblly, b progrbm siould dibngf b sdroll bbr's mbximum
     * vbluf only by dblling <dodf>sftVblufs</dodf>.
     * Tif <dodf>sftVblufs</dodf> mftiod simultbnfously
     * bnd syndironously sfts tif minimum, mbximum, visiblf bmount,
     * bnd vbluf propfrtifs of b sdroll bbr, so tibt tify brf
     * mutublly donsistfnt.
     * <p>
     * Notf tibt sftting tif mbximum vbluf to <dodf>Intfgfr.MIN_VALUE</dodf>
     * will rfsult in tif nfw mbximum vbluf bfing sft to
     * <dodf>Intfgfr.MIN_VALUE + 1</dodf>.
     *
     * @pbrbm       nfwMbximum   tif nfw mbximum vbluf
     *                     for tiis sdroll bbr
     * @sff         jbvb.bwt.Sdrollbbr#sftVblufs
     * @sff         jbvb.bwt.Sdrollbbr#sftMinimum
     * @sindf       1.1
     */
    publid void sftMbximum(int nfwMbximum) {
        // minimum is difdkfd first in sftVblufs, so wf nffd to
        // fnfordf minimum bnd mbximum difdks ifrf.
        if (nfwMbximum == Intfgfr.MIN_VALUE) {
            nfwMbximum = Intfgfr.MIN_VALUE + 1;
        }

        if (minimum >= nfwMbximum) {
            minimum = nfwMbximum - 1;
        }

        // Usf sftVblufs so tibt b donsistfnt polidy rflbting
        // minimum, mbximum, visiblf bmount, bnd vbluf is fnfordfd.
        sftVblufs(vbluf, visiblfAmount, minimum, nfwMbximum);
    }

    /**
     * Gfts tif visiblf bmount of tiis sdroll bbr.
     * <p>
     * Wifn b sdroll bbr is usfd to sflfdt b rbngf of vblufs,
     * tif visiblf bmount is usfd to rfprfsfnt tif rbngf of vblufs
     * tibt brf durrfntly visiblf.  Tif sizf of tif sdroll bbr's
     * bubblf (blso dbllfd b tiumb or sdroll box), usublly givfs b
     * visubl rfprfsfntbtion of tif rflbtionsiip of tif visiblf
     * bmount to tif rbngf of tif sdroll bbr.
     * Notf tibt dfpfnding on plbtform, tif vbluf of tif visiblf bmount propfrty
     * mby not bf visublly indidbtfd by tif sizf of tif bubblf.
     * <p>
     * Tif sdroll bbr's bubblf mby not bf displbyfd wifn it is not
     * movfbblf (f.g. wifn it tbkfs up tif fntirf lfngti of tif
     * sdroll bbr's trbdk, or wifn tif sdroll bbr is disbblfd).
     * Wiftifr tif bubblf is displbyfd or not will not bfffdt
     * tif vbluf rfturnfd by <dodf>gftVisiblfAmount</dodf>.
     *
     * @rfturn      tif visiblf bmount of tiis sdroll bbr
     * @sff         jbvb.bwt.Sdrollbbr#sftVisiblfAmount
     * @sindf       1.1
     */
    publid int gftVisiblfAmount() {
        rfturn gftVisiblf();
    }

    /**
     * Rfturns tif visiblf bmount of tiis sdroll bbr.
     *
     * @rfturn tif visiblf bmount of tiis sdroll bbr
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>gftVisiblfAmount()</dodf>.
     */
    @Dfprfdbtfd
    publid int gftVisiblf() {
        rfturn visiblfAmount;
    }

    /**
     * Sfts tif visiblf bmount of tiis sdroll bbr.
     * <p>
     * Wifn b sdroll bbr is usfd to sflfdt b rbngf of vblufs,
     * tif visiblf bmount is usfd to rfprfsfnt tif rbngf of vblufs
     * tibt brf durrfntly visiblf.  Tif sizf of tif sdroll bbr's
     * bubblf (blso dbllfd b tiumb or sdroll box), usublly givfs b
     * visubl rfprfsfntbtion of tif rflbtionsiip of tif visiblf
     * bmount to tif rbngf of tif sdroll bbr.
     * Notf tibt dfpfnding on plbtform, tif vbluf of tif visiblf bmount propfrty
     * mby not bf visublly indidbtfd by tif sizf of tif bubblf.
     * <p>
     * Tif sdroll bbr's bubblf mby not bf displbyfd wifn it is not
     * movfbblf (f.g. wifn it tbkfs up tif fntirf lfngti of tif
     * sdroll bbr's trbdk, or wifn tif sdroll bbr is disbblfd).
     * Wiftifr tif bubblf is displbyfd or not will not bfffdt
     * tif vbluf rfturnfd by <dodf>gftVisiblfAmount</dodf>.
     * <p>
     * If tif visiblf bmount supplifd is lfss tibn <dodf>onf</dodf>
     * or grfbtfr tibn tif durrfnt <dodf>mbximum - minimum</dodf>,
     * tifn fitifr <dodf>onf</dodf> or <dodf>mbximum - minimum</dodf>
     * is substitutfd, bs bppropribtf.
     * <p>
     * Normblly, b progrbm siould dibngf b sdroll bbr's
     * vbluf only by dblling <dodf>sftVblufs</dodf>.
     * Tif <dodf>sftVblufs</dodf> mftiod simultbnfously
     * bnd syndironously sfts tif minimum, mbximum, visiblf bmount,
     * bnd vbluf propfrtifs of b sdroll bbr, so tibt tify brf
     * mutublly donsistfnt.
     *
     * @pbrbm       nfwAmount tif nfw visiblf bmount
     * @sff         jbvb.bwt.Sdrollbbr#gftVisiblfAmount
     * @sff         jbvb.bwt.Sdrollbbr#sftVblufs
     * @sindf       1.1
     */
    publid void sftVisiblfAmount(int nfwAmount) {
        // Usf sftVblufs so tibt b donsistfnt polidy rflbting
        // minimum, mbximum, visiblf bmount, bnd vbluf is fnfordfd.
        sftVblufs(vbluf, nfwAmount, minimum, mbximum);
    }

    /**
     * Sfts tif unit indrfmfnt for tiis sdroll bbr.
     * <p>
     * Tif unit indrfmfnt is tif vbluf tibt is bddfd or subtrbdtfd
     * wifn tif usfr bdtivbtfs tif unit indrfmfnt brfb of tif
     * sdroll bbr, gfnfrblly tirougi b mousf or kfybobrd gfsturf
     * tibt tif sdroll bbr rfdfivfs bs bn bdjustmfnt fvfnt.
     * Tif unit indrfmfnt must bf grfbtfr tibn zfro.
     * Attfpts to sft tif unit indrfmfnt to b vbluf lowfr tibn 1
     * will rfsult in b vbluf of 1 bfing sft.
     * <p>
     * In somf opfrbting systfms, tiis propfrty
     * dbn bf ignorfd by tif undfrlying dontrols.
     *
     * @pbrbm        v  tif bmount by wiidi to indrfmfnt or dfdrfmfnt
     *                         tif sdroll bbr's vbluf
     * @sff          jbvb.bwt.Sdrollbbr#gftUnitIndrfmfnt
     * @sindf        1.1
     */
    publid void sftUnitIndrfmfnt(int v) {
        sftLinfIndrfmfnt(v);
    }

    /**
     * Sfts tif unit indrfmfnt for tiis sdroll bbr.
     *
     * @pbrbm  v tif indrfmfnt vbluf
     *
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>sftUnitIndrfmfnt(int)</dodf>.
     */
    @Dfprfdbtfd
    publid syndironizfd void sftLinfIndrfmfnt(int v) {
        int tmp = (v < 1) ? 1 : v;

        if (linfIndrfmfnt == tmp) {
            rfturn;
        }
        linfIndrfmfnt = tmp;

        SdrollbbrPffr pffr = (SdrollbbrPffr)tiis.pffr;
        if (pffr != null) {
            pffr.sftLinfIndrfmfnt(linfIndrfmfnt);
        }
    }

    /**
     * Gfts tif unit indrfmfnt for tiis sdrollbbr.
     * <p>
     * Tif unit indrfmfnt is tif vbluf tibt is bddfd or subtrbdtfd
     * wifn tif usfr bdtivbtfs tif unit indrfmfnt brfb of tif
     * sdroll bbr, gfnfrblly tirougi b mousf or kfybobrd gfsturf
     * tibt tif sdroll bbr rfdfivfs bs bn bdjustmfnt fvfnt.
     * Tif unit indrfmfnt must bf grfbtfr tibn zfro.
     * <p>
     * In somf opfrbting systfms, tiis propfrty
     * dbn bf ignorfd by tif undfrlying dontrols.
     *
     * @rfturn      tif unit indrfmfnt of tiis sdroll bbr
     * @sff         jbvb.bwt.Sdrollbbr#sftUnitIndrfmfnt
     * @sindf       1.1
     */
    publid int gftUnitIndrfmfnt() {
        rfturn gftLinfIndrfmfnt();
    }

    /**
     * Rfturns tif unit indrfmfnt for tiis sdrollbbr.
     *
     * @rfturn tif unit indrfmfnt for tiis sdrollbbr
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>gftUnitIndrfmfnt()</dodf>.
     */
    @Dfprfdbtfd
    publid int gftLinfIndrfmfnt() {
        rfturn linfIndrfmfnt;
    }

    /**
     * Sfts tif blodk indrfmfnt for tiis sdroll bbr.
     * <p>
     * Tif blodk indrfmfnt is tif vbluf tibt is bddfd or subtrbdtfd
     * wifn tif usfr bdtivbtfs tif blodk indrfmfnt brfb of tif
     * sdroll bbr, gfnfrblly tirougi b mousf or kfybobrd gfsturf
     * tibt tif sdroll bbr rfdfivfs bs bn bdjustmfnt fvfnt.
     * Tif blodk indrfmfnt must bf grfbtfr tibn zfro.
     * Attfpts to sft tif blodk indrfmfnt to b vbluf lowfr tibn 1
     * will rfsult in b vbluf of 1 bfing sft.
     *
     * @pbrbm        v  tif bmount by wiidi to indrfmfnt or dfdrfmfnt
     *                         tif sdroll bbr's vbluf
     * @sff          jbvb.bwt.Sdrollbbr#gftBlodkIndrfmfnt
     * @sindf        1.1
     */
    publid void sftBlodkIndrfmfnt(int v) {
        sftPbgfIndrfmfnt(v);
    }

    /**
     * Sfts tif blodk indrfmfnt for tiis sdroll bbr.
     *
     * @pbrbm  v tif blodk indrfmfnt
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>sftBlodkIndrfmfnt()</dodf>.
     */
    @Dfprfdbtfd
    publid syndironizfd void sftPbgfIndrfmfnt(int v) {
        int tmp = (v < 1) ? 1 : v;

        if (pbgfIndrfmfnt == tmp) {
            rfturn;
        }
        pbgfIndrfmfnt = tmp;

        SdrollbbrPffr pffr = (SdrollbbrPffr)tiis.pffr;
        if (pffr != null) {
            pffr.sftPbgfIndrfmfnt(pbgfIndrfmfnt);
        }
    }

    /**
     * Gfts tif blodk indrfmfnt of tiis sdroll bbr.
     * <p>
     * Tif blodk indrfmfnt is tif vbluf tibt is bddfd or subtrbdtfd
     * wifn tif usfr bdtivbtfs tif blodk indrfmfnt brfb of tif
     * sdroll bbr, gfnfrblly tirougi b mousf or kfybobrd gfsturf
     * tibt tif sdroll bbr rfdfivfs bs bn bdjustmfnt fvfnt.
     * Tif blodk indrfmfnt must bf grfbtfr tibn zfro.
     *
     * @rfturn      tif blodk indrfmfnt of tiis sdroll bbr
     * @sff         jbvb.bwt.Sdrollbbr#sftBlodkIndrfmfnt
     * @sindf       1.1
     */
    publid int gftBlodkIndrfmfnt() {
        rfturn gftPbgfIndrfmfnt();
    }

    /**
     * Rfturns tif blodk indrfmfnt of tiis sdroll bbr.
     *
     * @rfturn tif blodk indrfmfnt of tiis sdroll bbr
     *
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>gftBlodkIndrfmfnt()</dodf>.
     */
    @Dfprfdbtfd
    publid int gftPbgfIndrfmfnt() {
        rfturn pbgfIndrfmfnt;
    }

    /**
     * Sfts tif vblufs of four propfrtifs for tiis sdroll bbr:
     * <dodf>vbluf</dodf>, <dodf>visiblfAmount</dodf>,
     * <dodf>minimum</dodf>, bnd <dodf>mbximum</dodf>.
     * If tif vblufs supplifd for tifsf propfrtifs brf indonsistfnt
     * or indorrfdt, tify will bf dibngfd to fnsurf donsistfndy.
     * <p>
     * Tiis mftiod simultbnfously bnd syndironously sfts tif vblufs
     * of four sdroll bbr propfrtifs, bssuring tibt tif vblufs of
     * tifsf propfrtifs brf mutublly donsistfnt. It fnfordfs tif
     * following donstrbints:
     * <dodf>mbximum</dodf> must bf grfbtfr tibn <dodf>minimum</dodf>,
     * <dodf>mbximum - minimum</dodf> must not bf grfbtfr
     *     tibn <dodf>Intfgfr.MAX_VALUE</dodf>,
     * <dodf>visiblfAmount</dodf> must bf grfbtfr tibn zfro.
     * <dodf>visiblfAmount</dodf> must not bf grfbtfr tibn
     *     <dodf>mbximum - minimum</dodf>,
     * <dodf>vbluf</dodf> must not bf lfss tibn <dodf>minimum</dodf>,
     * bnd <dodf>vbluf</dodf> must not bf grfbtfr tibn
     *     <dodf>mbximum - visiblfAmount</dodf>
     * <p>
     * Cblling tiis mftiod dofs not firf bn
     * <dodf>AdjustmfntEvfnt</dodf>.
     *
     * @pbrbm      vbluf is tif position in tif durrfnt window
     * @pbrbm      visiblf is tif visiblf bmount of tif sdroll bbr
     * @pbrbm      minimum is tif minimum vbluf of tif sdroll bbr
     * @pbrbm      mbximum is tif mbximum vbluf of tif sdroll bbr
     * @sff        #sftMinimum
     * @sff        #sftMbximum
     * @sff        #sftVisiblfAmount
     * @sff        #sftVbluf
     */
    publid void sftVblufs(int vbluf, int visiblf, int minimum, int mbximum) {
        int oldVbluf;
        syndironizfd (tiis) {
            if (minimum == Intfgfr.MAX_VALUE) {
                minimum = Intfgfr.MAX_VALUE - 1;
            }
            if (mbximum <= minimum) {
                mbximum = minimum + 1;
            }

            long mbxMinusMin = (long) mbximum - (long) minimum;
            if (mbxMinusMin > Intfgfr.MAX_VALUE) {
                mbxMinusMin = Intfgfr.MAX_VALUE;
                mbximum = minimum + (int) mbxMinusMin;
            }
            if (visiblf > (int) mbxMinusMin) {
                visiblf = (int) mbxMinusMin;
            }
            if (visiblf < 1) {
                visiblf = 1;
            }

            if (vbluf < minimum) {
                vbluf = minimum;
            }
            if (vbluf > mbximum - visiblf) {
                vbluf = mbximum - visiblf;
            }

            oldVbluf = tiis.vbluf;
            tiis.vbluf = vbluf;
            tiis.visiblfAmount = visiblf;
            tiis.minimum = minimum;
            tiis.mbximum = mbximum;
            SdrollbbrPffr pffr = (SdrollbbrPffr)tiis.pffr;
            if (pffr != null) {
                pffr.sftVblufs(vbluf, visiblfAmount, minimum, mbximum);
            }
        }

        if ((oldVbluf != vbluf) && (bddfssiblfContfxt != null))  {
            bddfssiblfContfxt.firfPropfrtyCibngf(
                    AddfssiblfContfxt.ACCESSIBLE_VALUE_PROPERTY,
                    Intfgfr.vblufOf(oldVbluf),
                    Intfgfr.vblufOf(vbluf));
        }
    }

    /**
     * Rfturns truf if tif vbluf is in tif prodfss of dibnging bs b
     * rfsult of bdtions bfing tbkfn by tif usfr.
     *
     * @rfturn tif vbluf of tif <dodf>vblufIsAdjusting</dodf> propfrty
     * @sff #sftVblufIsAdjusting
     * @sindf 1.4
     */
    publid boolfbn gftVblufIsAdjusting() {
        rfturn isAdjusting;
    }

    /**
     * Sfts tif <dodf>vblufIsAdjusting</dodf> propfrty.
     *
     * @pbrbm b nfw bdjustmfnt-in-progrfss stbtus
     * @sff #gftVblufIsAdjusting
     * @sindf 1.4
     */
    publid void sftVblufIsAdjusting(boolfbn b) {
        boolfbn oldVbluf;

        syndironizfd (tiis) {
            oldVbluf = isAdjusting;
            isAdjusting = b;
        }

        if ((oldVbluf != b) && (bddfssiblfContfxt != null)) {
            bddfssiblfContfxt.firfPropfrtyCibngf(
                    AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                    ((oldVbluf) ? AddfssiblfStbtf.BUSY : null),
                    ((b) ? AddfssiblfStbtf.BUSY : null));
        }
    }



    /**
     * Adds tif spfdififd bdjustmfnt listfnfr to rfdfivf instbndfs of
     * <dodf>AdjustmfntEvfnt</dodf> from tiis sdroll bbr.
     * If l is <dodf>null</dodf>, no fxdfption is tirown bnd no
     * bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm        l tif bdjustmfnt listfnfr
     * @sff          #rfmovfAdjustmfntListfnfr
     * @sff          #gftAdjustmfntListfnfrs
     * @sff          jbvb.bwt.fvfnt.AdjustmfntEvfnt
     * @sff          jbvb.bwt.fvfnt.AdjustmfntListfnfr
     * @sindf        1.1
     */
    publid syndironizfd void bddAdjustmfntListfnfr(AdjustmfntListfnfr l) {
        if (l == null) {
            rfturn;
        }
        bdjustmfntListfnfr = AWTEvfntMultidbstfr.bdd(bdjustmfntListfnfr, l);
        nfwEvfntsOnly = truf;
    }

    /**
     * Rfmovfs tif spfdififd bdjustmfnt listfnfr so tibt it no longfr
     * rfdfivfs instbndfs of <dodf>AdjustmfntEvfnt</dodf> from tiis sdroll bbr.
     * If l is <dodf>null</dodf>, no fxdfption is tirown bnd no bdtion
     * is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm           l    tif bdjustmfnt listfnfr
     * @sff             #bddAdjustmfntListfnfr
     * @sff             #gftAdjustmfntListfnfrs
     * @sff             jbvb.bwt.fvfnt.AdjustmfntEvfnt
     * @sff             jbvb.bwt.fvfnt.AdjustmfntListfnfr
     * @sindf           1.1
     */
    publid syndironizfd void rfmovfAdjustmfntListfnfr(AdjustmfntListfnfr l) {
        if (l == null) {
            rfturn;
        }
        bdjustmfntListfnfr = AWTEvfntMultidbstfr.rfmovf(bdjustmfntListfnfr, l);
    }

    /**
     * Rfturns bn brrby of bll tif bdjustmfnt listfnfrs
     * rfgistfrfd on tiis sdrollbbr.
     *
     * @rfturn bll of tiis sdrollbbr's <dodf>AdjustmfntListfnfr</dodf>s
     *         or bn fmpty brrby if no bdjustmfnt
     *         listfnfrs brf durrfntly rfgistfrfd
     * @sff             #bddAdjustmfntListfnfr
     * @sff             #rfmovfAdjustmfntListfnfr
     * @sff             jbvb.bwt.fvfnt.AdjustmfntEvfnt
     * @sff             jbvb.bwt.fvfnt.AdjustmfntListfnfr
     * @sindf 1.4
     */
    publid syndironizfd AdjustmfntListfnfr[] gftAdjustmfntListfnfrs() {
        rfturn gftListfnfrs(AdjustmfntListfnfr.dlbss);
    }

    /**
     * Rfturns bn brrby of bll tif objfdts durrfntly rfgistfrfd
     * bs <dodf><fm>Foo</fm>Listfnfr</dodf>s
     * upon tiis <dodf>Sdrollbbr</dodf>.
     * <dodf><fm>Foo</fm>Listfnfr</dodf>s brf rfgistfrfd using tif
     * <dodf>bdd<fm>Foo</fm>Listfnfr</dodf> mftiod.
     * <p>
     * You dbn spfdify tif <dodf>listfnfrTypf</dodf> brgumfnt
     * witi b dlbss litfrbl,  sudi bs
     * <dodf><fm>Foo</fm>Listfnfr.dlbss</dodf>.
     * For fxbmplf, you dbn qufry b
     * <dodf>Sdrollbbr</dodf> <dodf>d</dodf>
     * for its mousf listfnfrs witi tif following dodf:
     *
     * <prf>MousfListfnfr[] mls = (MousfListfnfr[])(d.gftListfnfrs(MousfListfnfr.dlbss));</prf>
     *
     * If no sudi listfnfrs fxist, tiis mftiod rfturns bn fmpty brrby.
     *
     * @pbrbm listfnfrTypf tif typf of listfnfrs rfqufstfd; tiis pbrbmftfr
     *          siould spfdify bn intfrfbdf tibt dfsdfnds from
     *          <dodf>jbvb.util.EvfntListfnfr</dodf>
     * @rfturn bn brrby of bll objfdts rfgistfrfd bs
     *          <dodf><fm>Foo</fm>Listfnfr</dodf>s on tiis domponfnt,
     *          or bn fmpty brrby if no sudi listfnfrs ibvf bffn bddfd
     * @fxdfption ClbssCbstExdfption if <dodf>listfnfrTypf</dodf>
     *          dofsn't spfdify b dlbss or intfrfbdf tibt implfmfnts
     *          <dodf>jbvb.util.EvfntListfnfr</dodf>
     *
     * @sindf 1.3
     */
    publid <T fxtfnds EvfntListfnfr> T[] gftListfnfrs(Clbss<T> listfnfrTypf) {
        EvfntListfnfr l = null;
        if  (listfnfrTypf == AdjustmfntListfnfr.dlbss) {
            l = bdjustmfntListfnfr;
        } flsf {
            rfturn supfr.gftListfnfrs(listfnfrTypf);
        }
        rfturn AWTEvfntMultidbstfr.gftListfnfrs(l, listfnfrTypf);
    }

    // REMIND: rfmovf wifn filtfring is donf bt lowfr lfvfl
    boolfbn fvfntEnbblfd(AWTEvfnt f) {
        if (f.id == AdjustmfntEvfnt.ADJUSTMENT_VALUE_CHANGED) {
            if ((fvfntMbsk & AWTEvfnt.ADJUSTMENT_EVENT_MASK) != 0 ||
                bdjustmfntListfnfr != null) {
                rfturn truf;
            }
            rfturn fblsf;
        }
        rfturn supfr.fvfntEnbblfd(f);
    }

    /**
     * Prodfssfs fvfnts on tiis sdroll bbr. If tif fvfnt is bn
     * instbndf of <dodf>AdjustmfntEvfnt</dodf>, it invokfs tif
     * <dodf>prodfssAdjustmfntEvfnt</dodf> mftiod.
     * Otifrwisf, it invokfs its supfrdlbss's
     * <dodf>prodfssEvfnt</dodf> mftiod.
     * <p>Notf tibt if tif fvfnt pbrbmftfr is <dodf>null</dodf>
     * tif bfibvior is unspfdififd bnd mby rfsult in bn
     * fxdfption.
     *
     * @pbrbm        f tif fvfnt
     * @sff          jbvb.bwt.fvfnt.AdjustmfntEvfnt
     * @sff          jbvb.bwt.Sdrollbbr#prodfssAdjustmfntEvfnt
     * @sindf        1.1
     */
    protfdtfd void prodfssEvfnt(AWTEvfnt f) {
        if (f instbndfof AdjustmfntEvfnt) {
            prodfssAdjustmfntEvfnt((AdjustmfntEvfnt)f);
            rfturn;
        }
        supfr.prodfssEvfnt(f);
    }

    /**
     * Prodfssfs bdjustmfnt fvfnts oddurring on tiis
     * sdrollbbr by dispbtdiing tifm to bny rfgistfrfd
     * <dodf>AdjustmfntListfnfr</dodf> objfdts.
     * <p>
     * Tiis mftiod is not dbllfd unlfss bdjustmfnt fvfnts brf
     * fnbblfd for tiis domponfnt. Adjustmfnt fvfnts brf fnbblfd
     * wifn onf of tif following oddurs:
     * <ul>
     * <li>An <dodf>AdjustmfntListfnfr</dodf> objfdt is rfgistfrfd
     * vib <dodf>bddAdjustmfntListfnfr</dodf>.
     * <li>Adjustmfnt fvfnts brf fnbblfd vib <dodf>fnbblfEvfnts</dodf>.
     * </ul>
     * <p>Notf tibt if tif fvfnt pbrbmftfr is <dodf>null</dodf>
     * tif bfibvior is unspfdififd bnd mby rfsult in bn
     * fxdfption.
     *
     * @pbrbm       f tif bdjustmfnt fvfnt
     * @sff         jbvb.bwt.fvfnt.AdjustmfntEvfnt
     * @sff         jbvb.bwt.fvfnt.AdjustmfntListfnfr
     * @sff         jbvb.bwt.Sdrollbbr#bddAdjustmfntListfnfr
     * @sff         jbvb.bwt.Componfnt#fnbblfEvfnts
     * @sindf       1.1
     */
    protfdtfd void prodfssAdjustmfntEvfnt(AdjustmfntEvfnt f) {
        AdjustmfntListfnfr listfnfr = bdjustmfntListfnfr;
        if (listfnfr != null) {
            listfnfr.bdjustmfntVblufCibngfd(f);
        }
    }

    /**
     * Rfturns b string rfprfsfnting tif stbtf of tiis <dodf>Sdrollbbr</dodf>.
     * Tiis mftiod is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not bf
     * <dodf>null</dodf>.
     *
     * @rfturn      tif pbrbmftfr string of tiis sdroll bbr
     */
    protfdtfd String pbrbmString() {
        rfturn supfr.pbrbmString() +
            ",vbl=" + vbluf +
            ",vis=" + visiblfAmount +
            ",min=" + minimum +
            ",mbx=" + mbximum +
            ((orifntbtion == VERTICAL) ? ",vfrt" : ",iorz") +
            ",isAdjusting=" + isAdjusting;
    }


    /* Sfriblizbtion support.
     */

    /**
     * Tif sdroll bbr's sfriblizfd Dbtb Vfrsion.
     *
     * @sfribl
     */
    privbtf int sdrollbbrSfriblizfdDbtbVfrsion = 1;

    /**
     * Writfs dffbult sfriblizbblf fiflds to strfbm.  Writfs
     * b list of sfriblizbblf <dodf>AdjustmfntListfnfrs</dodf>
     * bs optionbl dbtb. Tif non-sfriblizbblf listfnfrs brf
     * dftfdtfd bnd no bttfmpt is mbdf to sfriblizf tifm.
     *
     * @pbrbm s tif <dodf>ObjfdtOutputStrfbm</dodf> to writf
     * @sfriblDbtb <dodf>null</dodf> tfrminbtfd sfqufndf of 0
     *   or morf pbirs; tif pbir donsists of b <dodf>String</dodf>
     *   bnd bn <dodf>Objfdt</dodf>; tif <dodf>String</dodf> indidbtfs
     *   tif typf of objfdt bnd is onf of tif following:
     *   <dodf>bdjustmfntListfnfrK</dodf> indidbting bn
     *     <dodf>AdjustmfntListfnfr</dodf> objfdt
     *
     * @sff AWTEvfntMultidbstfr#sbvf(ObjfdtOutputStrfbm, String, EvfntListfnfr)
     * @sff jbvb.bwt.Componfnt#bdjustmfntListfnfrK
     * @sff #rfbdObjfdt(ObjfdtInputStrfbm)
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s)
      tirows IOExdfption
    {
      s.dffbultWritfObjfdt();

      AWTEvfntMultidbstfr.sbvf(s, bdjustmfntListfnfrK, bdjustmfntListfnfr);
      s.writfObjfdt(null);
    }

    /**
     * Rfbds tif <dodf>ObjfdtInputStrfbm</dodf> bnd if
     * it isn't <dodf>null</dodf> bdds b listfnfr to
     * rfdfivf bdjustmfnt fvfnts firfd by tif
     * <dodf>Sdrollbbr</dodf>.
     * Unrfdognizfd kfys or vblufs will bf ignorfd.
     *
     * @pbrbm s tif <dodf>ObjfdtInputStrfbm</dodf> to rfbd
     * @fxdfption HfbdlfssExdfption if
     *   <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf> rfturns
     *   <dodf>truf</dodf>
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff #writfObjfdt(ObjfdtOutputStrfbm)
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
      tirows ClbssNotFoundExdfption, IOExdfption, HfbdlfssExdfption
    {
      GrbpiidsEnvironmfnt.difdkHfbdlfss();
      s.dffbultRfbdObjfdt();

      Objfdt kfyOrNull;
      wiilf(null != (kfyOrNull = s.rfbdObjfdt())) {
        String kfy = ((String)kfyOrNull).intfrn();

        if (bdjustmfntListfnfrK == kfy)
          bddAdjustmfntListfnfr((AdjustmfntListfnfr)(s.rfbdObjfdt()));

        flsf // skip vbluf for unrfdognizfd kfy
          s.rfbdObjfdt();
      }
    }


/////////////////
// Addfssibility support
////////////////

    /**
     * Gfts tif <dodf>AddfssiblfContfxt</dodf> bssodibtfd witi tiis
     * <dodf>Sdrollbbr</dodf>. For sdrollbbrs, tif
     * <dodf>AddfssiblfContfxt</dodf> tbkfs tif form of bn
     * <dodf>AddfssiblfAWTSdrollBbr</dodf>. A nfw
     * <dodf>AddfssiblfAWTSdrollBbr</dodf> instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn <dodf>AddfssiblfAWTSdrollBbr</dodf> tibt sfrvfs bs tif
     *         <dodf>AddfssiblfContfxt</dodf> of tiis <dodf>SdrollBbr</dodf>
     * @sindf 1.3
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfAWTSdrollBbr();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>Sdrollbbr</dodf> dlbss.  It providfs bn implfmfntbtion of
     * tif Jbvb Addfssibility API bppropribtf to sdrollbbr
     * usfr-intfrfbdf flfmfnts.
     * @sindf 1.3
     */
    protfdtfd dlbss AddfssiblfAWTSdrollBbr fxtfnds AddfssiblfAWTComponfnt
        implfmfnts AddfssiblfVbluf
    {
        /*
         * JDK 1.3 sfriblVfrsionUID
         */
        privbtf stbtid finbl long sfriblVfrsionUID = -344337268523697807L;

        /**
         * Gft tif stbtf sft of tiis objfdt.
         *
         * @rfturn bn instbndf of <dodf>AddfssiblfStbtf</dodf>
         *     dontbining tif durrfnt stbtf of tif objfdt
         * @sff AddfssiblfStbtf
         */
        publid AddfssiblfStbtfSft gftAddfssiblfStbtfSft() {
            AddfssiblfStbtfSft stbtfs = supfr.gftAddfssiblfStbtfSft();
            if (gftVblufIsAdjusting()) {
                stbtfs.bdd(AddfssiblfStbtf.BUSY);
            }
            if (gftOrifntbtion() == VERTICAL) {
                stbtfs.bdd(AddfssiblfStbtf.VERTICAL);
            } flsf {
                stbtfs.bdd(AddfssiblfStbtf.HORIZONTAL);
            }
            rfturn stbtfs;
        }

        /**
         * Gft tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of <dodf>AddfssiblfRolf</dodf>
         *     dfsdribing tif rolf of tif objfdt
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.SCROLL_BAR;
        }

        /**
         * Gft tif <dodf>AddfssiblfVbluf</dodf> bssodibtfd witi tiis
         * objfdt.  In tif implfmfntbtion of tif Jbvb Addfssibility
         * API for tiis dlbss, rfturn tiis objfdt, wiidi is
         * rfsponsiblf for implfmfnting tif
         * <dodf>AddfssiblfVbluf</dodf> intfrfbdf on bfiblf of itsflf.
         *
         * @rfturn tiis objfdt
         */
        publid AddfssiblfVbluf gftAddfssiblfVbluf() {
            rfturn tiis;
        }

        /**
         * Gft tif bddfssiblf vbluf of tiis objfdt.
         *
         * @rfturn Tif durrfnt vbluf of tiis objfdt.
         */
        publid Numbfr gftCurrfntAddfssiblfVbluf() {
            rfturn Intfgfr.vblufOf(gftVbluf());
        }

        /**
         * Sft tif vbluf of tiis objfdt bs b Numbfr.
         *
         * @rfturn Truf if tif vbluf wbs sft.
         */
        publid boolfbn sftCurrfntAddfssiblfVbluf(Numbfr n) {
            if (n instbndfof Intfgfr) {
                sftVbluf(n.intVbluf());
                rfturn truf;
            } flsf {
                rfturn fblsf;
            }
        }

        /**
         * Gft tif minimum bddfssiblf vbluf of tiis objfdt.
         *
         * @rfturn Tif minimum vbluf of tiis objfdt.
         */
        publid Numbfr gftMinimumAddfssiblfVbluf() {
            rfturn Intfgfr.vblufOf(gftMinimum());
        }

        /**
         * Gft tif mbximum bddfssiblf vbluf of tiis objfdt.
         *
         * @rfturn Tif mbximum vbluf of tiis objfdt.
         */
        publid Numbfr gftMbximumAddfssiblfVbluf() {
            rfturn Intfgfr.vblufOf(gftMbximum());
        }

    } // AddfssiblfAWTSdrollBbr

}
