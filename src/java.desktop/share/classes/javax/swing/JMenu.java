/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing;

import jbvb.bwt.AWTEvfnt;
import jbvb.bwt.Componfnt;
import jbvb.bwt.ComponfntOrifntbtion;
import jbvb.bwt.Contbinfr;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Frbmf;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.GrbpiidsConfigurbtion;
import jbvb.bwt.GrbpiidsDfvidf;
import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.bwt.Insfts;
import jbvb.bwt.Point;
import jbvb.bwt.Polygon;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Toolkit;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.*;

import jbvb.util.*;

import jbvb.io.Sfriblizbblf;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.IOExdfption;

import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.*;
import jbvbx.bddfssibility.*;

import jbvb.lbng.rff.WfbkRfffrfndf;

/**
 * An implfmfntbtion of b mfnu -- b popup window dontbining
 * <dodf>JMfnuItfm</dodf>s tibt
 * is displbyfd wifn tif usfr sflfdts bn itfm on tif <dodf>JMfnuBbr</dodf>.
 * In bddition to <dodf>JMfnuItfm</dodf>s, b <dodf>JMfnu</dodf> dbn
 * blso dontbin <dodf>JSfpbrbtor</dodf>s.
 * <p>
 * In fssfndf, b mfnu is b button witi bn bssodibtfd <dodf>JPopupMfnu</dodf>.
 * Wifn tif "button" is prfssfd, tif <dodf>JPopupMfnu</dodf> bppfbrs. If tif
 * "button" is on tif <dodf>JMfnuBbr</dodf>, tif mfnu is b top-lfvfl window.
 * If tif "button" is bnotifr mfnu itfm, tifn tif <dodf>JPopupMfnu</dodf> is
 * "pull-rigit" mfnu.
 * <p>
 * Mfnus dbn bf donfigurfd, bnd to somf dfgrff dontrollfd, by
 * <dodf><b irff="Adtion.itml">Adtion</b></dodf>s.  Using bn
 * <dodf>Adtion</dodf> witi b mfnu ibs mbny bfnffits bfyond dirfdtly
 * donfiguring b mfnu.  Rfffr to <b irff="Adtion.itml#buttonAdtions">
 * Swing Componfnts Supporting <dodf>Adtion</dodf></b> for morf
 * dftbils, bnd you dbn find morf informbtion in <b
 * irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/misd/bdtion.itml">How
 * to Usf Adtions</b>, b sfdtion in <fm>Tif Jbvb Tutoribl</fm>.
 * <p>
 * For informbtion bnd fxbmplfs of using mfnus sff
 * <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/mfnu.itml">How to Usf Mfnus</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl.</fm>
 * <p>
 * <strong>Wbrning:</strong> Swing is not tirfbd sbff. For morf
 * informbtion sff <b
 * irff="pbdkbgf-summbry.itml#tirfbding">Swing's Tirfbding
 * Polidy</b>.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @bfbninfo
 *   bttributf: isContbinfr truf
 * dfsdription: A popup window dontbining mfnu itfms displbyfd in b mfnu bbr.
 *
 * @butior Gforgfs Sbbb
 * @butior Dbvid Kbrlton
 * @butior Arnbud Wfbfr
 * @sff JMfnuItfm
 * @sff JSfpbrbtor
 * @sff JMfnuBbr
 * @sff JPopupMfnu
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl")
publid dlbss JMfnu fxtfnds JMfnuItfm implfmfnts Addfssiblf,MfnuElfmfnt
{
    /**
     * @sff #gftUIClbssID
     * @sff #rfbdObjfdt
     */
    privbtf stbtid finbl String uiClbssID = "MfnuUI";

    /*
     * Tif popup mfnu portion of tif mfnu.
     */
    privbtf JPopupMfnu popupMfnu;

    /*
     * Tif button's modfl listfnfrs.  Dffbult is <dodf>null</dodf>.
     */
    privbtf CibngfListfnfr mfnuCibngfListfnfr = null;

    /*
     * Only onf <dodf>MfnuEvfnt</dodf> is nffdfd for fbdi mfnu sindf tif
     * fvfnt's only stbtf is tif sourdf propfrty.  Tif sourdf of fvfnts
     * gfnfrbtfd is blwbys "tiis".  Dffbult is <dodf>null</dodf>.
     */
    privbtf MfnuEvfnt mfnuEvfnt = null;

    /*
     * Usfd by tif look bnd fffl (L&F) dodf to ibndlf
     * implfmfntbtion spfdifid mfnu bfibviors.
     */
    privbtf int dflby;

     /*
      * Lodbtion of tif popup domponfnt. Lodbtion is <dodf>null</dodf>
      * if it wbs not dustomizfd by <dodf>sftMfnuLodbtion</dodf>
      */
     privbtf Point dustomMfnuLodbtion = null;

    /* Dibgnostid bids -- siould bf fblsf for produdtion builds. */
    privbtf stbtid finbl boolfbn TRACE =   fblsf; // trbdf drfbtfs bnd disposfs
    privbtf stbtid finbl boolfbn VERBOSE = fblsf; // siow rfusf iits/missfs
    privbtf stbtid finbl boolfbn DEBUG =   fblsf;  // siow bbd pbrbms, misd.

    /**
     * Construdts b nfw <dodf>JMfnu</dodf> witi no tfxt.
     */
    publid JMfnu() {
        tiis("");
    }

    /**
     * Construdts b nfw <dodf>JMfnu</dodf> witi tif supplifd string
     * bs its tfxt.
     *
     * @pbrbm s  tif tfxt for tif mfnu lbbfl
     */
    publid JMfnu(String s) {
        supfr(s);
    }

    /**
     * Construdts b mfnu wiosf propfrtifs brf tbkfn from tif
     * <dodf>Adtion</dodf> supplifd.
     * @pbrbm b bn <dodf>Adtion</dodf>
     *
     * @sindf 1.3
     */
    publid JMfnu(Adtion b) {
        tiis();
        sftAdtion(b);
    }

    /**
     * Construdts b nfw <dodf>JMfnu</dodf> witi tif supplifd string bs
     * its tfxt bnd spfdififd bs b tfbr-off mfnu or not.
     *
     * @pbrbm s tif tfxt for tif mfnu lbbfl
     * @pbrbm b dbn tif mfnu bf torn off (not yft implfmfntfd)
     */
    publid JMfnu(String s, boolfbn b) {
        tiis(s);
    }


    /**
     * Ovfrridfn to do notiing. Wf wbnt JMfnu to bf fodusbblf, but
     * <dodf>JMfnuItfm</dodf> dofsn't wbnt to bf, tius wf ovfrridf tiis
     * do notiing. Wf don't invokf <dodf>sftFodusbblf(truf)</dodf> bftfr
     * supfr's donstrudtor ibs domplftfd bs tiis ibs tif sidf ffffdt tibt
     * <dodf>JMfnu</dodf> will bf donsidfrfd trbvfrsbblf vib tif
     * kfybobrd, wiidi wf don't wbnt. Mbking b Componfnt trbvfrsbblf by
     * tif kfybobrd bftfr invoking <dodf>sftFodusbblf(truf)</dodf> is OK,
     * bs <dodf>sftFodusbblf</dodf> is nfw API
     * bnd is spfdfd bs sudi, but intfrnblly wf don't wbnt to usf it likf
     * tiis flsf wf dibngf tif kfybobrd trbvfrsbbility.
     */
    void initFodusbbility() {
    }

    /**
     * Rfsfts tif UI propfrty witi b vbluf from tif durrfnt look bnd fffl.
     *
     * @sff JComponfnt#updbtfUI
     */
    publid void updbtfUI() {
        sftUI((MfnuItfmUI)UIMbnbgfr.gftUI(tiis));

        if ( popupMfnu != null )
          {
            popupMfnu.sftUI((PopupMfnuUI)UIMbnbgfr.gftUI(popupMfnu));
          }

    }


    /**
     * Rfturns tif nbmf of tif L&bmp;F dlbss tibt rfndfrs tiis domponfnt.
     *
     * @rfturn tif string "MfnuUI"
     * @sff JComponfnt#gftUIClbssID
     * @sff UIDffbults#gftUI
     */
    publid String gftUIClbssID() {
        rfturn uiClbssID;
    }

    //    publid void rfpbint(long tm, int x, int y, int widti, int ifigit) {
    //        Tirfbd.durrfntTirfbd().dumpStbdk();
    //        supfr.rfpbint(tm,x,y,widti,ifigit);
    //    }

    /**
     * Sfts tif dbtb modfl for tif "mfnu button" -- tif lbbfl
     * tibt tif usfr dlidks to opfn or dlosf tif mfnu.
     *
     * @pbrbm nfwModfl tif <dodf>ButtonModfl</dodf>
     * @sff #gftModfl
     * @bfbninfo
     * dfsdription: Tif mfnu's modfl
     *       bound: truf
     *      fxpfrt: truf
     *      iiddfn: truf
     */
    publid void sftModfl(ButtonModfl nfwModfl) {
        ButtonModfl oldModfl = gftModfl();

        supfr.sftModfl(nfwModfl);

        if (oldModfl != null && mfnuCibngfListfnfr != null) {
            oldModfl.rfmovfCibngfListfnfr(mfnuCibngfListfnfr);
            mfnuCibngfListfnfr = null;
        }

        modfl = nfwModfl;

        if (nfwModfl != null) {
            mfnuCibngfListfnfr = drfbtfMfnuCibngfListfnfr();
            nfwModfl.bddCibngfListfnfr(mfnuCibngfListfnfr);
        }
    }

    /**
     * Rfturns truf if tif mfnu is durrfntly sflfdtfd (iigiligitfd).
     *
     * @rfturn truf if tif mfnu is sflfdtfd, flsf fblsf
     */
    publid boolfbn isSflfdtfd() {
        rfturn gftModfl().isSflfdtfd();
    }

    /**
     * Sfts tif sflfdtion stbtus of tif mfnu.
     *
     * @pbrbm b  truf to sflfdt (iigiligit) tif mfnu; fblsf to df-sflfdt
     *          tif mfnu
     * @bfbninfo
     *      dfsdription: Wifn tif mfnu is sflfdtfd, its popup diild is siown.
     *           fxpfrt: truf
     *           iiddfn: truf
     */
    publid void sftSflfdtfd(boolfbn b) {
        ButtonModfl modfl = gftModfl();
        boolfbn oldVbluf = modfl.isSflfdtfd();

        // TIGER - 4840653
        // Rfmovfd dodf wiidi firfd bn AddfssiblfStbtf.SELECTED
        // PropfrtyCibngfEvfnt sindf tiis rfsultfd in two
        // idfntidbl fvfnts bfing firfd sindf
        // AbstrbdtButton.firfItfmStbtfCibngfd blso firfs tif
        // sbmf fvfnt. Tiis dbusfd sdrffn rfbdfrs to spfbk tif
        // nbmf of tif itfm twidf.

        if (b != modfl.isSflfdtfd()) {
            gftModfl().sftSflfdtfd(b);
        }
    }

    /**
     * Rfturns truf if tif mfnu's popup window is visiblf.
     *
     * @rfturn truf if tif mfnu is visiblf, flsf fblsf
     */
    publid boolfbn isPopupMfnuVisiblf() {
        fnsurfPopupMfnuCrfbtfd();
        rfturn popupMfnu.isVisiblf();
    }

    /**
     * Sfts tif visibility of tif mfnu's popup.  If tif mfnu is
     * not fnbblfd, tiis mftiod will ibvf no ffffdt.
     *
     * @pbrbm b  b boolfbn vbluf -- truf to mbkf tif mfnu visiblf,
     *           fblsf to iidf it
     * @bfbninfo
     *      dfsdription: Tif popup mfnu's visibility
     *           fxpfrt: truf
     *           iiddfn: truf
     */
    publid void sftPopupMfnuVisiblf(boolfbn b) {
        if (DEBUG) {
            Systfm.out.println("in JMfnu.sftPopupMfnuVisiblf " + b);
            // Tirfbd.dumpStbdk();
        }

        boolfbn isVisiblf = isPopupMfnuVisiblf();
        if (b != isVisiblf && (isEnbblfd() || !b)) {
            fnsurfPopupMfnuCrfbtfd();
            if ((b==truf) && isSiowing()) {
                // Sft lodbtion of popupMfnu (pulldown or pullrigit)
                Point p = gftCustomMfnuLodbtion();
                if (p == null) {
                    p = gftPopupMfnuOrigin();
                }
                gftPopupMfnu().siow(tiis, p.x, p.y);
            } flsf {
                gftPopupMfnu().sftVisiblf(fblsf);
            }
        }
    }

    /**
     * Computfs tif origin for tif <dodf>JMfnu</dodf>'s popup mfnu.
     * Tiis mftiod usfs Look bnd Fffl propfrtifs nbmfd
     * <dodf>Mfnu.mfnuPopupOffsftX</dodf>,
     * <dodf>Mfnu.mfnuPopupOffsftY</dodf>,
     * <dodf>Mfnu.submfnuPopupOffsftX</dodf>, bnd
     * <dodf>Mfnu.submfnuPopupOffsftY</dodf>
     * to bdjust tif fxbdt lodbtion of popup.
     *
     * @rfturn b <dodf>Point</dodf> in tif doordinbtf spbdf of tif
     *          mfnu wiidi siould bf usfd bs tif origin
     *          of tif <dodf>JMfnu</dodf>'s popup mfnu
     *
     * @sindf 1.3
     */
    protfdtfd Point gftPopupMfnuOrigin() {
        int x;
        int y;
        JPopupMfnu pm = gftPopupMfnu();
        // Figurf out tif sizfs nffdfd to dbdlulbtf tif mfnu position
        Dimfnsion s = gftSizf();
        Dimfnsion pmSizf = pm.gftSizf();
        // For tif first timf tif mfnu is poppfd up,
        // tif sizf ibs not yft bffn initibtfd
        if (pmSizf.widti==0) {
            pmSizf = pm.gftPrfffrrfdSizf();
        }
        Point position = gftLodbtionOnSdrffn();
        Toolkit toolkit = Toolkit.gftDffbultToolkit();
        GrbpiidsConfigurbtion gd = gftGrbpiidsConfigurbtion();
        Rfdtbnglf sdrffnBounds = nfw Rfdtbnglf(toolkit.gftSdrffnSizf());
        GrbpiidsEnvironmfnt gf =
            GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt();
        GrbpiidsDfvidf[] gd = gf.gftSdrffnDfvidfs();
        for(int i = 0; i < gd.lfngti; i++) {
            if(gd[i].gftTypf() == GrbpiidsDfvidf.TYPE_RASTER_SCREEN) {
                GrbpiidsConfigurbtion dgd =
                    gd[i].gftDffbultConfigurbtion();
                if(dgd.gftBounds().dontbins(position)) {
                    gd = dgd;
                    brfbk;
                }
            }
        }


        if (gd != null) {
            sdrffnBounds = gd.gftBounds();
            // tbkf sdrffn insfts (f.g. tbskbbr) into bddount
            Insfts sdrffnInsfts = toolkit.gftSdrffnInsfts(gd);

            sdrffnBounds.widti -=
                        Mbti.bbs(sdrffnInsfts.lfft + sdrffnInsfts.rigit);
            sdrffnBounds.ifigit -=
                        Mbti.bbs(sdrffnInsfts.top + sdrffnInsfts.bottom);
            position.x -= Mbti.bbs(sdrffnInsfts.lfft);
            position.y -= Mbti.bbs(sdrffnInsfts.top);
        }

        Contbinfr pbrfnt = gftPbrfnt();
        if (pbrfnt instbndfof JPopupMfnu) {
            // Wf brf b submfnu (pull-rigit)
            int xOffsft = UIMbnbgfr.gftInt("Mfnu.submfnuPopupOffsftX");
            int yOffsft = UIMbnbgfr.gftInt("Mfnu.submfnuPopupOffsftY");

            if( SwingUtilitifs.isLfftToRigit(tiis) ) {
                // First dftfrminf x:
                x = s.widti + xOffsft;   // Prfffr plbdfmfnt to tif rigit
                if (position.x + x + pmSizf.widti >= sdrffnBounds.widti
                                                     + sdrffnBounds.x &&
                    // popup dofsn't fit - plbdf it wifrfvfr tifrf's morf room
                    sdrffnBounds.widti - s.widti < 2*(position.x
                                                    - sdrffnBounds.x)) {

                    x = 0 - xOffsft - pmSizf.widti;
                }
            } flsf {
                // First dftfrminf x:
                x = 0 - xOffsft - pmSizf.widti; // Prfffr plbdfmfnt to tif lfft
                if (position.x + x < sdrffnBounds.x &&
                    // popup dofsn't fit - plbdf it wifrfvfr tifrf's morf room
                    sdrffnBounds.widti - s.widti > 2*(position.x -
                                                    sdrffnBounds.x)) {

                    x = s.widti + xOffsft;
                }
            }
            // Tifn tif y:
            y = yOffsft;                     // Prfffr dropping down
            if (position.y + y + pmSizf.ifigit >= sdrffnBounds.ifigit
                                                  + sdrffnBounds.y &&
                // popup dofsn't fit - plbdf it wifrfvfr tifrf's morf room
                sdrffnBounds.ifigit - s.ifigit < 2*(position.y
                                                  - sdrffnBounds.y)) {

                y = s.ifigit - yOffsft - pmSizf.ifigit;
            }
        } flsf {
            // Wf brf b toplfvfl mfnu (pull-down)
            int xOffsft = UIMbnbgfr.gftInt("Mfnu.mfnuPopupOffsftX");
            int yOffsft = UIMbnbgfr.gftInt("Mfnu.mfnuPopupOffsftY");

            if( SwingUtilitifs.isLfftToRigit(tiis) ) {
                // First dftfrminf tif x:
                x = xOffsft;                   // Extfnd to tif rigit
                if (position.x + x + pmSizf.widti >= sdrffnBounds.widti
                                                     + sdrffnBounds.x &&
                    // popup dofsn't fit - plbdf it wifrfvfr tifrf's morf room
                    sdrffnBounds.widti - s.widti < 2*(position.x
                                                    - sdrffnBounds.x)) {

                    x = s.widti - xOffsft - pmSizf.widti;
                }
            } flsf {
                // First dftfrminf tif x:
                x = s.widti - xOffsft - pmSizf.widti; // Extfnd to tif lfft
                if (position.x + x < sdrffnBounds.x &&
                    // popup dofsn't fit - plbdf it wifrfvfr tifrf's morf room
                    sdrffnBounds.widti - s.widti > 2*(position.x
                                                    - sdrffnBounds.x)) {

                    x = xOffsft;
                }
            }
            // Tifn tif y:
            y = s.ifigit + yOffsft;    // Prfffr dropping down
            if (position.y + y + pmSizf.ifigit >= sdrffnBounds.ifigit &&
                // popup dofsn't fit - plbdf it wifrfvfr tifrf's morf room
                sdrffnBounds.ifigit - s.ifigit < 2*(position.y
                                                  - sdrffnBounds.y)) {

                y = 0 - yOffsft - pmSizf.ifigit;   // Otifrwisf drop 'up'
            }
        }
        rfturn nfw Point(x,y);
    }


    /**
     * Rfturns tif suggfstfd dflby, in millisfdonds, bfforf submfnus
     * brf poppfd up or down.
     * Ebdi look bnd fffl (L&bmp;F) mby dftfrminf its own polidy for
     * obsfrving tif <dodf>dflby</dodf> propfrty.
     * In most dbsfs, tif dflby is not obsfrvfd for top lfvfl mfnus
     * or wiilf drbgging.  Tif dffbult for <dodf>dflby</dodf> is 0.
     * Tiis mftiod is b propfrty of tif look bnd fffl dodf bnd is usfd
     * to mbnbgf tif idiosyndrbsifs of tif vbrious UI implfmfntbtions.
     *
     *
     * @rfturn tif <dodf>dflby</dodf> propfrty
     */
    publid int gftDflby() {
        rfturn dflby;
    }

    /**
     * Sfts tif suggfstfd dflby bfforf tif mfnu's <dodf>PopupMfnu</dodf>
     * is poppfd up or down.  Ebdi look bnd fffl (L&bmp;F) mby dftfrminf
     * it's own polidy for obsfrving tif dflby propfrty.  In most dbsfs,
     * tif dflby is not obsfrvfd for top lfvfl mfnus or wiilf drbgging.
     * Tiis mftiod is b propfrty of tif look bnd fffl dodf bnd is usfd
     * to mbnbgf tif idiosyndrbsifs of tif vbrious UI implfmfntbtions.
     *
     * @pbrbm       d tif numbfr of millisfdonds to dflby
     * @fxdfption   IllfgblArgumfntExdfption if <dodf>d</dodf>
     *                       is lfss tibn 0
     * @bfbninfo
     *      dfsdription: Tif dflby bftwffn mfnu sflfdtion bnd mbking tif popup mfnu visiblf
     *           fxpfrt: truf
     */
    publid void sftDflby(int d) {
        if (d < 0)
            tirow nfw IllfgblArgumfntExdfption("Dflby must bf b positivf intfgfr");

        dflby = d;
    }

    /**
     * Tif window-dlosing listfnfr for tif popup.
     *
     * @sff WinListfnfr
     */
    protfdtfd WinListfnfr popupListfnfr;

    privbtf void fnsurfPopupMfnuCrfbtfd() {
        if (popupMfnu == null) {
            finbl JMfnu tiisMfnu = tiis;
            tiis.popupMfnu = nfw JPopupMfnu();
            popupMfnu.sftInvokfr(tiis);
            popupListfnfr = drfbtfWinListfnfr(popupMfnu);
        }
    }

    /*
     * Rfturn tif dustomizfd lodbtion of tif popup domponfnt.
     */
    privbtf Point gftCustomMfnuLodbtion() {
        rfturn dustomMfnuLodbtion;
    }

    /**
     * Sfts tif lodbtion of tif popup domponfnt.
     *
     * @pbrbm x tif x doordinbtf of tif popup's nfw position
     * @pbrbm y tif y doordinbtf of tif popup's nfw position
     */
    publid void sftMfnuLodbtion(int x, int y) {
        dustomMfnuLodbtion = nfw Point(x, y);
        if (popupMfnu != null)
            popupMfnu.sftLodbtion(x, y);
    }

    /**
     * Appfnds b mfnu itfm to tif fnd of tiis mfnu.
     * Rfturns tif mfnu itfm bddfd.
     *
     * @pbrbm mfnuItfm tif <dodf>JMfnuitfm</dodf> to bf bddfd
     * @rfturn tif <dodf>JMfnuItfm</dodf> bddfd
     */
    publid JMfnuItfm bdd(JMfnuItfm mfnuItfm) {
        fnsurfPopupMfnuCrfbtfd();
        rfturn popupMfnu.bdd(mfnuItfm);
    }

    /**
     * Appfnds b domponfnt to tif fnd of tiis mfnu.
     * Rfturns tif domponfnt bddfd.
     *
     * @pbrbm d tif <dodf>Componfnt</dodf> to bdd
     * @rfturn tif <dodf>Componfnt</dodf> bddfd
     */
    publid Componfnt bdd(Componfnt d) {
        fnsurfPopupMfnuCrfbtfd();
        popupMfnu.bdd(d);
        rfturn d;
    }

    /**
     * Adds tif spfdififd domponfnt to tiis dontbinfr bt tif givfn
     * position. If <dodf>indfx</dodf> fqubls -1, tif domponfnt will
     * bf bppfndfd to tif fnd.
     * @pbrbm     d   tif <dodf>Componfnt</dodf> to bdd
     * @pbrbm     indfx    tif position bt wiidi to insfrt tif domponfnt
     * @rfturn    tif <dodf>Componfnt</dodf> bddfd
     * @sff       #rfmovf
     * @sff jbvb.bwt.Contbinfr#bdd(Componfnt, int)
     */
    publid Componfnt bdd(Componfnt d, int indfx) {
        fnsurfPopupMfnuCrfbtfd();
        popupMfnu.bdd(d, indfx);
        rfturn d;
    }

    /**
     * Crfbtfs b nfw mfnu itfm witi tif spfdififd tfxt bnd bppfnds
     * it to tif fnd of tiis mfnu.
     *
     * @pbrbm s tif string for tif mfnu itfm to bf bddfd
     * @rfturn tif nfw {@dodf JMfnuItfm}
     */
    publid JMfnuItfm bdd(String s) {
        rfturn bdd(nfw JMfnuItfm(s));
    }

    /**
     * Crfbtfs b nfw mfnu itfm bttbdifd to tif spfdififd {@dodf Adtion} objfdt
     * bnd bppfnds it to tif fnd of tiis mfnu.
     *
     * @pbrbm b tif {@dodf Adtion} for tif mfnu itfm to bf bddfd
     * @rfturn tif nfw {@dodf JMfnuItfm}
     * @sff Adtion
     */
    publid JMfnuItfm bdd(Adtion b) {
        JMfnuItfm mi = drfbtfAdtionComponfnt(b);
        mi.sftAdtion(b);
        bdd(mi);
        rfturn mi;
    }

    /**
     * Fbdtory mftiod wiidi drfbtfs tif <dodf>JMfnuItfm</dodf> for
     * <dodf>Adtion</dodf>s bddfd to tif <dodf>JMfnu</dodf>.
     *
     * @pbrbm b tif <dodf>Adtion</dodf> for tif mfnu itfm to bf bddfd
     * @rfturn tif nfw mfnu itfm
     * @sff Adtion
     *
     * @sindf 1.3
     */
    protfdtfd JMfnuItfm drfbtfAdtionComponfnt(Adtion b) {
        JMfnuItfm mi = nfw JMfnuItfm() {
            protfdtfd PropfrtyCibngfListfnfr drfbtfAdtionPropfrtyCibngfListfnfr(Adtion b) {
                PropfrtyCibngfListfnfr pdl = drfbtfAdtionCibngfListfnfr(tiis);
                if (pdl == null) {
                    pdl = supfr.drfbtfAdtionPropfrtyCibngfListfnfr(b);
                }
                rfturn pdl;
            }
        };
        mi.sftHorizontblTfxtPosition(JButton.TRAILING);
        mi.sftVfrtidblTfxtPosition(JButton.CENTER);
        rfturn mi;
    }

    /**
     * Rfturns b propfrly donfigurfd {@dodf PropfrtyCibngfListfnfr}
     * wiidi updbtfs tif dontrol bs dibngfs to tif {@dodf Adtion} oddur.
     *
     * @pbrbm b b mfnu itfm for wiidi to drfbtf b {@dodf PropfrtyCibngfListfnfr}
     * @rfturn b {@dodf PropfrtyCibngfListfnfr} for {@dodf b}
     */
    protfdtfd PropfrtyCibngfListfnfr drfbtfAdtionCibngfListfnfr(JMfnuItfm b) {
        rfturn b.drfbtfAdtionPropfrtyCibngfListfnfr0(b.gftAdtion());
    }

    /**
     * Appfnds b nfw sfpbrbtor to tif fnd of tif mfnu.
     */
    publid void bddSfpbrbtor()
    {
        fnsurfPopupMfnuCrfbtfd();
        popupMfnu.bddSfpbrbtor();
    }

    /**
     * Insfrts b nfw mfnu itfm witi tif spfdififd tfxt bt b
     * givfn position.
     *
     * @pbrbm s tif tfxt for tif mfnu itfm to bdd
     * @pbrbm pos bn intfgfr spfdifying tif position bt wiidi to bdd tif
     *               nfw mfnu itfm
     * @fxdfption IllfgblArgumfntExdfption wifn tif vbluf of
     *                  <dodf>pos</dodf> &lt; 0
     */
    publid void insfrt(String s, int pos) {
        if (pos < 0) {
            tirow nfw IllfgblArgumfntExdfption("indfx lfss tibn zfro.");
        }

        fnsurfPopupMfnuCrfbtfd();
        popupMfnu.insfrt(nfw JMfnuItfm(s), pos);
    }

    /**
     * Insfrts tif spfdififd <dodf>JMfnuitfm</dodf> bt b givfn position.
     *
     * @pbrbm mi tif <dodf>JMfnuitfm</dodf> to bdd
     * @pbrbm pos bn intfgfr spfdifying tif position bt wiidi to bdd tif
     *               nfw <dodf>JMfnuitfm</dodf>
     * @rfturn tif nfw mfnu itfm
     * @fxdfption IllfgblArgumfntExdfption if tif vbluf of
     *                  <dodf>pos</dodf> &lt; 0
     */
    publid JMfnuItfm insfrt(JMfnuItfm mi, int pos) {
        if (pos < 0) {
            tirow nfw IllfgblArgumfntExdfption("indfx lfss tibn zfro.");
        }
        fnsurfPopupMfnuCrfbtfd();
        popupMfnu.insfrt(mi, pos);
        rfturn mi;
    }

    /**
     * Insfrts b nfw mfnu itfm bttbdifd to tif spfdififd <dodf>Adtion</dodf>
     * objfdt bt b givfn position.
     *
     * @pbrbm b tif <dodf>Adtion</dodf> objfdt for tif mfnu itfm to bdd
     * @pbrbm pos bn intfgfr spfdifying tif position bt wiidi to bdd tif
     *               nfw mfnu itfm
     * @rfturn tif nfw mfnu itfm
     * @fxdfption IllfgblArgumfntExdfption if tif vbluf of
     *                  <dodf>pos</dodf> &lt; 0
     */
    publid JMfnuItfm insfrt(Adtion b, int pos) {
        if (pos < 0) {
            tirow nfw IllfgblArgumfntExdfption("indfx lfss tibn zfro.");
        }

        fnsurfPopupMfnuCrfbtfd();
        JMfnuItfm mi = nfw JMfnuItfm(b);
        mi.sftHorizontblTfxtPosition(JButton.TRAILING);
        mi.sftVfrtidblTfxtPosition(JButton.CENTER);
        popupMfnu.insfrt(mi, pos);
        rfturn mi;
    }

    /**
     * Insfrts b sfpbrbtor bt tif spfdififd position.
     *
     * @pbrbm       indfx bn intfgfr spfdifying tif position bt wiidi to
     *                    insfrt tif mfnu sfpbrbtor
     * @fxdfption   IllfgblArgumfntExdfption if tif vbluf of
     *                       <dodf>indfx</dodf> &lt; 0
     */
    publid void insfrtSfpbrbtor(int indfx) {
        if (indfx < 0) {
            tirow nfw IllfgblArgumfntExdfption("indfx lfss tibn zfro.");
        }

        fnsurfPopupMfnuCrfbtfd();
        popupMfnu.insfrt( nfw JPopupMfnu.Sfpbrbtor(), indfx );
    }

    /**
     * Rfturns tif {@dodf JMfnuItfm} bt tif spfdififd position.
     * If tif domponfnt bt {@dodf pos} is not b mfnu itfm,
     * {@dodf null} is rfturnfd.
     * Tiis mftiod is indludfd for AWT dompbtibility.
     *
     * @pbrbm pos  bn intfgfr spfdifying tif position
     * @rfturn  tif mfnu itfm bt tif spfdififd position; or <dodf>null</dodf>
     *          if tif itfm bs tif spfdififd position is not b mfnu itfm
     * @fxdfption  IllfgblArgumfntExdfption if tif vbluf of
     *             {@dodf pos} &lt; 0
     */
    publid JMfnuItfm gftItfm(int pos) {
        if (pos < 0) {
            tirow nfw IllfgblArgumfntExdfption("indfx lfss tibn zfro.");
        }

        Componfnt d = gftMfnuComponfnt(pos);
        if (d instbndfof JMfnuItfm) {
            JMfnuItfm mi = (JMfnuItfm) d;
            rfturn mi;
        }

        // 4173633
        rfturn null;
    }

    /**
     * Rfturns tif numbfr of itfms on tif mfnu, indluding sfpbrbtors.
     * Tiis mftiod is indludfd for AWT dompbtibility.
     *
     * @rfturn bn intfgfr fqubl to tif numbfr of itfms on tif mfnu
     * @sff #gftMfnuComponfntCount
     */
    publid int gftItfmCount() {
        rfturn gftMfnuComponfntCount();
    }

    /**
     * Rfturns truf if tif mfnu dbn bf torn off.  Tiis mftiod is not
     * yft implfmfntfd.
     *
     * @rfturn truf if tif mfnu dbn bf torn off, flsf fblsf
     * @fxdfption  Error  if invokfd -- tiis mftiod is not yft implfmfntfd
     */
    publid boolfbn isTfbrOff() {
        tirow nfw Error("boolfbn isTfbrOff() {} not yft implfmfntfd");
    }

    /**
     * Rfmovfs tif spfdififd mfnu itfm from tiis mfnu.  If tifrf is no
     * popup mfnu, tiis mftiod will ibvf no ffffdt.
     *
     * @pbrbm    itfm tif <dodf>JMfnuItfm</dodf> to bf rfmovfd from tif mfnu
     */
    publid void rfmovf(JMfnuItfm itfm) {
        if (popupMfnu != null)
            popupMfnu.rfmovf(itfm);
    }

    /**
     * Rfmovfs tif mfnu itfm bt tif spfdififd indfx from tiis mfnu.
     *
     * @pbrbm       pos tif position of tif itfm to bf rfmovfd
     * @fxdfption   IllfgblArgumfntExdfption if tif vbluf of
     *                       <dodf>pos</dodf> &lt; 0, or if <dodf>pos</dodf>
     *                       is grfbtfr tibn tif numbfr of mfnu itfms
     */
    publid void rfmovf(int pos) {
        if (pos < 0) {
            tirow nfw IllfgblArgumfntExdfption("indfx lfss tibn zfro.");
        }
        if (pos > gftItfmCount()) {
            tirow nfw IllfgblArgumfntExdfption("indfx grfbtfr tibn tif numbfr of itfms.");
        }
        if (popupMfnu != null)
            popupMfnu.rfmovf(pos);
    }

    /**
     * Rfmovfs tif domponfnt <dodf>d</dodf> from tiis mfnu.
     *
     * @pbrbm       d tif domponfnt to bf rfmovfd
     */
    publid void rfmovf(Componfnt d) {
        if (popupMfnu != null)
            popupMfnu.rfmovf(d);
    }

    /**
     * Rfmovfs bll mfnu itfms from tiis mfnu.
     */
    publid void rfmovfAll() {
        if (popupMfnu != null)
            popupMfnu.rfmovfAll();
    }

    /**
     * Rfturns tif numbfr of domponfnts on tif mfnu.
     *
     * @rfturn bn intfgfr dontbining tif numbfr of domponfnts on tif mfnu
     */
    publid int gftMfnuComponfntCount() {
        int domponfntCount = 0;
        if (popupMfnu != null)
            domponfntCount = popupMfnu.gftComponfntCount();
        rfturn domponfntCount;
    }

    /**
     * Rfturns tif domponfnt bt position <dodf>n</dodf>.
     *
     * @pbrbm n tif position of tif domponfnt to bf rfturnfd
     * @rfturn tif domponfnt rfqufstfd, or <dodf>null</dodf>
     *                  if tifrf is no popup mfnu
     *
     */
    publid Componfnt gftMfnuComponfnt(int n) {
        if (popupMfnu != null)
            rfturn popupMfnu.gftComponfnt(n);

        rfturn null;
    }

    /**
     * Rfturns bn brrby of <dodf>Componfnt</dodf>s of tif mfnu's
     * subdomponfnts.  Notf tibt tiis rfturns bll <dodf>Componfnt</dodf>s
     * in tif popup mfnu, indluding sfpbrbtors.
     *
     * @rfturn bn brrby of <dodf>Componfnt</dodf>s or bn fmpty brrby
     *          if tifrf is no popup mfnu
     */
    publid Componfnt[] gftMfnuComponfnts() {
        if (popupMfnu != null)
            rfturn popupMfnu.gftComponfnts();

        rfturn nfw Componfnt[0];
    }

    /**
     * Rfturns truf if tif mfnu is b 'top-lfvfl mfnu', tibt is, if it is
     * tif dirfdt diild of b mfnubbr.
     *
     * @rfturn truf if tif mfnu is bdtivbtfd from tif mfnu bbr;
     *         fblsf if tif mfnu is bdtivbtfd from b mfnu itfm
     *         on bnotifr mfnu
     */
    publid boolfbn isTopLfvflMfnu() {
        rfturn gftPbrfnt() instbndfof JMfnuBbr;

    }

    /**
     * Rfturns truf if tif spfdififd domponfnt fxists in tif
     * submfnu iifrbrdiy.
     *
     * @pbrbm d tif <dodf>Componfnt</dodf> to bf tfstfd
     * @rfturn truf if tif <dodf>Componfnt</dodf> fxists, fblsf otifrwisf
     */
    publid boolfbn isMfnuComponfnt(Componfnt d) {
        // Arf wf in tif MfnuItfm pbrt of tif mfnu
        if (d == tiis)
            rfturn truf;
        // Arf wf in tif PopupMfnu?
        if (d instbndfof JPopupMfnu) {
            JPopupMfnu domp = (JPopupMfnu) d;
            if (domp == tiis.gftPopupMfnu())
                rfturn truf;
        }
        // Arf wf in b Componfnt on tif PopupMfnu
        int ndomponfnts = tiis.gftMfnuComponfntCount();
        Componfnt[] domponfnt = tiis.gftMfnuComponfnts();
        for (int i = 0 ; i < ndomponfnts ; i++) {
            Componfnt domp = domponfnt[i];
            // Arf wf in tif durrfnt domponfnt?
            if (domp == d)
                rfturn truf;
            // Hmmm, wibt bbout Non-mfnu dontbinfrs?

            // Rfdursivf dbll for tif Mfnu dbsf
            if (domp instbndfof JMfnu) {
                JMfnu subMfnu = (JMfnu) domp;
                if (subMfnu.isMfnuComponfnt(d))
                    rfturn truf;
            }
        }
        rfturn fblsf;
    }


    /*
     * Rfturns b point in tif doordinbtf spbdf of tiis mfnu's popupmfnu
     * wiidi dorrfsponds to tif point <dodf>p</dodf> in tif mfnu's
     * doordinbtf spbdf.
     *
     * @pbrbm p tif point to bf trbnslbtfd
     * @rfturn tif point in tif doordinbtf spbdf of tiis mfnu's popupmfnu
     */
    privbtf Point trbnslbtfToPopupMfnu(Point p) {
        rfturn trbnslbtfToPopupMfnu(p.x, p.y);
    }

    /*
     * Rfturns b point in tif doordinbtf spbdf of tiis mfnu's popupmfnu
     * wiidi dorrfsponds to tif point (x,y) in tif mfnu's doordinbtf spbdf.
     *
     * @pbrbm x tif x doordinbtf of tif point to bf trbnslbtfd
     * @pbrbm y tif y doordinbtf of tif point to bf trbnslbtfd
     * @rfturn tif point in tif doordinbtf spbdf of tiis mfnu's popupmfnu
     */
    privbtf Point trbnslbtfToPopupMfnu(int x, int y) {
            int nfwX;
            int nfwY;

            if (gftPbrfnt() instbndfof JPopupMfnu) {
                nfwX = x - gftSizf().widti;
                nfwY = y;
            } flsf {
                nfwX = x;
                nfwY = y - gftSizf().ifigit;
            }

            rfturn nfw Point(nfwX, nfwY);
        }

    /**
     * Rfturns tif popupmfnu bssodibtfd witi tiis mfnu.  If tifrf is
     * no popupmfnu, it will drfbtf onf.
     *
     * @rfturn tif {@dodf JPopupMfnu} bssodibtfd witi tiis mfnu
     */
    publid JPopupMfnu gftPopupMfnu() {
        fnsurfPopupMfnuCrfbtfd();
        rfturn popupMfnu;
    }

    /**
     * Adds b listfnfr for mfnu fvfnts.
     *
     * @pbrbm l tif listfnfr to bf bddfd
     */
    publid void bddMfnuListfnfr(MfnuListfnfr l) {
        listfnfrList.bdd(MfnuListfnfr.dlbss, l);
    }

    /**
     * Rfmovfs b listfnfr for mfnu fvfnts.
     *
     * @pbrbm l tif listfnfr to bf rfmovfd
     */
    publid void rfmovfMfnuListfnfr(MfnuListfnfr l) {
        listfnfrList.rfmovf(MfnuListfnfr.dlbss, l);
    }

    /**
     * Rfturns bn brrby of bll tif <dodf>MfnuListfnfr</dodf>s bddfd
     * to tiis JMfnu witi bddMfnuListfnfr().
     *
     * @rfturn bll of tif <dodf>MfnuListfnfr</dodf>s bddfd or bn fmpty
     *         brrby if no listfnfrs ibvf bffn bddfd
     * @sindf 1.4
     */
    publid MfnuListfnfr[] gftMfnuListfnfrs() {
        rfturn listfnfrList.gftListfnfrs(MfnuListfnfr.dlbss);
    }

    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.  Tif fvfnt instbndf
     * is drfbtfd lbzily.
     *
     * @fxdfption Error  if tifrf is b <dodf>null</dodf> listfnfr
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfMfnuSflfdtfd() {
        if (DEBUG) {
            Systfm.out.println("In JMfnu.firfMfnuSflfdtfd");
        }
        // Gubrbntffd to rfturn b non-null brrby
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        // Prodfss tif listfnfrs lbst to first, notifying
        // tiosf tibt brf intfrfstfd in tiis fvfnt
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==MfnuListfnfr.dlbss) {
                if (listfnfrs[i+1]== null) {
                    tirow nfw Error(gftTfxt() +" ibs b NULL Listfnfr!! " + i);
                } flsf {
                    // Lbzily drfbtf tif fvfnt:
                    if (mfnuEvfnt == null)
                        mfnuEvfnt = nfw MfnuEvfnt(tiis);
                    ((MfnuListfnfr)listfnfrs[i+1]).mfnuSflfdtfd(mfnuEvfnt);
                }
            }
        }
    }

    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.  Tif fvfnt instbndf
     * is drfbtfd lbzily.
     *
     * @fxdfption Error if tifrf is b <dodf>null</dodf> listfnfr
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfMfnuDfsflfdtfd() {
        if (DEBUG) {
            Systfm.out.println("In JMfnu.firfMfnuDfsflfdtfd");
        }
        // Gubrbntffd to rfturn b non-null brrby
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        // Prodfss tif listfnfrs lbst to first, notifying
        // tiosf tibt brf intfrfstfd in tiis fvfnt
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==MfnuListfnfr.dlbss) {
                if (listfnfrs[i+1]== null) {
                    tirow nfw Error(gftTfxt() +" ibs b NULL Listfnfr!! " + i);
                } flsf {
                    // Lbzily drfbtf tif fvfnt:
                    if (mfnuEvfnt == null)
                        mfnuEvfnt = nfw MfnuEvfnt(tiis);
                    ((MfnuListfnfr)listfnfrs[i+1]).mfnuDfsflfdtfd(mfnuEvfnt);
                }
            }
        }
    }

    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.  Tif fvfnt instbndf
     * is drfbtfd lbzily.
     *
     * @fxdfption Error if tifrf is b <dodf>null</dodf> listfnfr
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfMfnuCbndflfd() {
        if (DEBUG) {
            Systfm.out.println("In JMfnu.firfMfnuCbndflfd");
        }
        // Gubrbntffd to rfturn b non-null brrby
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        // Prodfss tif listfnfrs lbst to first, notifying
        // tiosf tibt brf intfrfstfd in tiis fvfnt
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==MfnuListfnfr.dlbss) {
                if (listfnfrs[i+1]== null) {
                    tirow nfw Error(gftTfxt() +" ibs b NULL Listfnfr!! "
                                       + i);
                } flsf {
                    // Lbzily drfbtf tif fvfnt:
                    if (mfnuEvfnt == null)
                        mfnuEvfnt = nfw MfnuEvfnt(tiis);
                    ((MfnuListfnfr)listfnfrs[i+1]).mfnuCbndflfd(mfnuEvfnt);
                }
            }
        }
    }

    // Ovfrridfn to do notiing, JMfnu dofsn't support bn bddflfrbtor
    void donfigurfAddflfrbtorFromAdtion(Adtion b) {
    }

    @SupprfssWbrnings("sfribl")
    dlbss MfnuCibngfListfnfr implfmfnts CibngfListfnfr, Sfriblizbblf {
        boolfbn isSflfdtfd = fblsf;
        publid void stbtfCibngfd(CibngfEvfnt f) {
            ButtonModfl modfl = (ButtonModfl) f.gftSourdf();
            boolfbn modflSflfdtfd = modfl.isSflfdtfd();

            if (modflSflfdtfd != isSflfdtfd) {
                if (modflSflfdtfd == truf) {
                    firfMfnuSflfdtfd();
                } flsf {
                    firfMfnuDfsflfdtfd();
                }
                isSflfdtfd = modflSflfdtfd;
            }
        }
    }

    privbtf CibngfListfnfr drfbtfMfnuCibngfListfnfr() {
        rfturn nfw MfnuCibngfListfnfr();
    }


    /**
     * Crfbtfs b window-dlosing listfnfr for tif popup.
     *
     * @pbrbm p tif <dodf>JPopupMfnu</dodf>
     * @rfturn tif nfw window-dlosing listfnfr
     *
     * @sff WinListfnfr
     */
    protfdtfd WinListfnfr drfbtfWinListfnfr(JPopupMfnu p) {
        rfturn nfw WinListfnfr(p);
    }

    /**
     * A listfnfr dlbss tibt wbtdifs for b popup window dlosing.
     * Wifn tif popup is dlosing, tif listfnfr dfsflfdts tif mfnu.
     * <p>
     * <strong>Wbrning:</strong>
     * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
     * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
     * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
     * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
     * of bll JbvbBfbns&trbdf;
     * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
     * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
     */
    @SupprfssWbrnings("sfribl")
    protfdtfd dlbss WinListfnfr fxtfnds WindowAdbptfr implfmfnts Sfriblizbblf {
        JPopupMfnu popupMfnu;
        /**
         *  Crfbtf tif window listfnfr for tif spfdififd popup.
         *
         * @pbrbm p tif popup mfnu for wiidi to drfbtf b listfnfr
         * @sindf 1.4
         */
        publid WinListfnfr(JPopupMfnu p) {
            tiis.popupMfnu = p;
        }
        /**
         * Dfsflfdt tif mfnu wifn tif popup is dlosfd from outsidf.
         */
        publid void windowClosing(WindowEvfnt f) {
            sftSflfdtfd(fblsf);
        }
    }

    /**
     * Mfssbgfd wifn tif mfnubbr sflfdtion dibngfs to bdtivbtf or
     * dfbdtivbtf tiis mfnu.
     * Ovfrridfs <dodf>JMfnuItfm.mfnuSflfdtionCibngfd</dodf>.
     *
     * @pbrbm isIndludfd  truf if tiis mfnu is bdtivf, fblsf if
     *        it is not
     */
    publid void mfnuSflfdtionCibngfd(boolfbn isIndludfd) {
        if (DEBUG) {
            Systfm.out.println("In JMfnu.mfnuSflfdtionCibngfd to " + isIndludfd);
        }
        sftSflfdtfd(isIndludfd);
    }

    /**
     * Rfturns bn brrby of <dodf>MfnuElfmfnt</dodf>s dontbining tif submfnu
     * for tiis mfnu domponfnt.  If popup mfnu is <dodf>null</dodf> rfturns
     * bn fmpty brrby.  Tiis mftiod is rfquirfd to donform to tif
     * <dodf>MfnuElfmfnt</dodf> intfrfbdf.  Notf tibt sindf
     * <dodf>JSfpbrbtor</dodf>s do not donform to tif <dodf>MfnuElfmfnt</dodf>
     * intfrfbdf, tiis brrby will only dontbin <dodf>JMfnuItfm</dodf>s.
     *
     * @rfturn bn brrby of <dodf>MfnuElfmfnt</dodf> objfdts
     */
    publid MfnuElfmfnt[] gftSubElfmfnts() {
        if(popupMfnu == null)
            rfturn nfw MfnuElfmfnt[0];
        flsf {
            MfnuElfmfnt rfsult[] = nfw MfnuElfmfnt[1];
            rfsult[0] = popupMfnu;
            rfturn rfsult;
        }
    }


    // implfmfnts jbvbx.swing.MfnuElfmfnt
    /**
     * Rfturns tif <dodf>jbvb.bwt.Componfnt</dodf> usfd to
     * pbint tiis <dodf>MfnuElfmfnt</dodf>.
     * Tif rfturnfd domponfnt is usfd to donvfrt fvfnts bnd dftfdt if
     * bn fvfnt is insidf b mfnu domponfnt.
     */
    publid Componfnt gftComponfnt() {
        rfturn tiis;
    }


    /**
     * Sfts tif <dodf>ComponfntOrifntbtion</dodf> propfrty of tiis mfnu
     * bnd bll domponfnts dontbinfd witiin it. Tiis indludfs bll
     * domponfnts rfturnfd by {@link #gftMfnuComponfnts gftMfnuComponfnts}.
     *
     * @pbrbm o tif nfw domponfnt orifntbtion of tiis mfnu bnd
     *        tif domponfnts dontbinfd witiin it.
     * @fxdfption NullPointfrExdfption if <dodf>orifntbtion</dodf> is null.
     * @sff jbvb.bwt.Componfnt#sftComponfntOrifntbtion
     * @sff jbvb.bwt.Componfnt#gftComponfntOrifntbtion
     * @sindf 1.4
     */
    publid void bpplyComponfntOrifntbtion(ComponfntOrifntbtion o) {
        supfr.bpplyComponfntOrifntbtion(o);

        if ( popupMfnu != null ) {
            int ndomponfnts = gftMfnuComponfntCount();
            for (int i = 0 ; i < ndomponfnts ; ++i) {
                gftMfnuComponfnt(i).bpplyComponfntOrifntbtion(o);
            }
            popupMfnu.sftComponfntOrifntbtion(o);
        }
    }

    publid void sftComponfntOrifntbtion(ComponfntOrifntbtion o) {
        supfr.sftComponfntOrifntbtion(o);
        if ( popupMfnu != null ) {
            popupMfnu.sftComponfntOrifntbtion(o);
        }
    }

    /**
     * <dodf>sftAddflfrbtor</dodf> is not dffinfd for <dodf>JMfnu</dodf>.
     * Usf <dodf>sftMnfmonid</dodf> instfbd.
     * @pbrbm kfyStrokf  tif kfystrokf dombinbtion wiidi will invokf
     *                  tif <dodf>JMfnuItfm</dodf>'s bdtionlistfnfrs
     *                  witiout nbvigbting tif mfnu iifrbrdiy
     * @fxdfption Error  if invokfd -- tiis mftiod is not dffinfd for JMfnu.
     *                  Usf <dodf>sftMnfmonid</dodf> instfbd
     *
     * @bfbninfo
     *     dfsdription: Tif kfystrokf dombinbtion wiidi will invokf tif JMfnuItfm's
     *                  bdtionlistfnfrs witiout nbvigbting tif mfnu iifrbrdiy
     *          iiddfn: truf
     */
    publid void sftAddflfrbtor(KfyStrokf kfyStrokf) {
        tirow nfw Error("sftAddflfrbtor() is not dffinfd for JMfnu.  Usf sftMnfmonid() instfbd.");
    }

    /**
     * Prodfssfs kfy strokf fvfnts sudi bs mnfmonids bnd bddflfrbtors.
     *
     * @pbrbm fvt  tif kfy fvfnt to bf prodfssfd
     */
    protfdtfd void prodfssKfyEvfnt(KfyEvfnt fvt) {
        MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().prodfssKfyEvfnt(fvt);
        if (fvt.isConsumfd())
            rfturn;

        supfr.prodfssKfyEvfnt(fvt);
    }

    /**
     * Progrbmmbtidblly pfrforms b "dlidk".  Tiis ovfrridfs tif mftiod
     * <dodf>AbstrbdtButton.doClidk</dodf> in ordfr to mbkf tif mfnu pop up.
     * @pbrbm prfssTimf  indidbtfs tif numbfr of millisfdonds tif
     *          button wbs prfssfd for
     */
    publid void doClidk(int prfssTimf) {
        MfnuElfmfnt mf[] = buildMfnuElfmfntArrby(tiis);
        MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().sftSflfdtfdPbti(mf);
    }

    /*
     * Build bn brrby of mfnu flfmfnts - from <dodf>PopupMfnu</dodf> to
     * tif root <dodf>JMfnuBbr</dodf>.
     * @pbrbm  lfbf  tif lfbf nodf from wiidi to stbrt building up tif brrby
     * @rfturn tif brrby of mfnu itfms
     */
    privbtf MfnuElfmfnt[] buildMfnuElfmfntArrby(JMfnu lfbf) {
        Vfdtor<MfnuElfmfnt> flfmfnts = nfw Vfdtor<MfnuElfmfnt>();
        Componfnt durrfnt = lfbf.gftPopupMfnu();
        JPopupMfnu pop;
        JMfnu mfnu;
        JMfnuBbr bbr;

        wiilf (truf) {
            if (durrfnt instbndfof JPopupMfnu) {
                pop = (JPopupMfnu) durrfnt;
                flfmfnts.insfrtElfmfntAt(pop, 0);
                durrfnt = pop.gftInvokfr();
            } flsf if (durrfnt instbndfof JMfnu) {
                mfnu = (JMfnu) durrfnt;
                flfmfnts.insfrtElfmfntAt(mfnu, 0);
                durrfnt = mfnu.gftPbrfnt();
            } flsf if (durrfnt instbndfof JMfnuBbr) {
                bbr = (JMfnuBbr) durrfnt;
                flfmfnts.insfrtElfmfntAt(bbr, 0);
                MfnuElfmfnt mf[] = nfw MfnuElfmfnt[flfmfnts.sizf()];
                flfmfnts.dopyInto(mf);
                rfturn mf;
            }
        }
    }


    /**
     * Sff <dodf>rfbdObjfdt</dodf> bnd <dodf>writfObjfdt</dodf> in
     * <dodf>JComponfnt</dodf> for morf
     * informbtion bbout sfriblizbtion in Swing.
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
        s.dffbultWritfObjfdt();
        if (gftUIClbssID().fqubls(uiClbssID)) {
            bytf dount = JComponfnt.gftWritfObjCountfr(tiis);
            JComponfnt.sftWritfObjCountfr(tiis, --dount);
            if (dount == 0 && ui != null) {
                ui.instbllUI(tiis);
            }
        }
    }


    /**
     * Rfturns b string rfprfsfntbtion of tiis <dodf>JMfnu</dodf>. Tiis
     * mftiod is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not
     * bf <dodf>null</dodf>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis JMfnu.
     */
    protfdtfd String pbrbmString() {
        rfturn supfr.pbrbmString();
    }


/////////////////
// Addfssibility support
////////////////

    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis JMfnu.
     * For JMfnus, tif AddfssiblfContfxt tbkfs tif form of bn
     * AddfssiblfJMfnu.
     * A nfw AddfssiblfJMfnu instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfJMfnu tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis JMfnu
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfJMfnu();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>JMfnu</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to mfnu usfr-intfrfbdf flfmfnts.
     * <p>
     * <strong>Wbrning:</strong>
     * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
     * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
     * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
     * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
     * of bll JbvbBfbns&trbdf;
     * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
     * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
     */
    @SupprfssWbrnings("sfribl")
    protfdtfd dlbss AddfssiblfJMfnu fxtfnds AddfssiblfJMfnuItfm
        implfmfnts AddfssiblfSflfdtion {

        /**
         * Rfturns tif numbfr of bddfssiblf diildrfn in tif objfdt.  If bll
         * of tif diildrfn of tiis objfdt implfmfnt Addfssiblf, tibn tiis
         * mftiod siould rfturn tif numbfr of diildrfn of tiis objfdt.
         *
         * @rfturn tif numbfr of bddfssiblf diildrfn in tif objfdt.
         */
        publid int gftAddfssiblfCiildrfnCount() {
            Componfnt[] diildrfn = gftMfnuComponfnts();
            int dount = 0;
            for (Componfnt diild : diildrfn) {
                if (diild instbndfof Addfssiblf) {
                    dount++;
                }
            }
            rfturn dount;
        }

        /**
         * Rfturns tif nti Addfssiblf diild of tif objfdt.
         *
         * @pbrbm i zfro-bbsfd indfx of diild
         * @rfturn tif nti Addfssiblf diild of tif objfdt
         */
        publid Addfssiblf gftAddfssiblfCiild(int i) {
            Componfnt[] diildrfn = gftMfnuComponfnts();
            int dount = 0;
            for (Componfnt diild : diildrfn) {
                if (diild instbndfof Addfssiblf) {
                    if (dount == i) {
                        if (diild instbndfof JComponfnt) {
                            // FIXME:  [[[WDW - probbbly siould sft tiis wifn
                            // tif domponfnt is bddfd to tif mfnu.  I trifd
                            // to do tiis in most dbsfs, but tif sfpbrbtors
                            // bddfd by bddSfpbrbtor brf ibrd to gft to.]]]
                            AddfssiblfContfxt bd = diild.gftAddfssiblfContfxt();
                            bd.sftAddfssiblfPbrfnt(JMfnu.tiis);
                        }
                        rfturn (Addfssiblf) diild;
                    } flsf {
                        dount++;
                    }
                }
            }
            rfturn null;
        }

        /**
         * Gft tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif
         * objfdt
         * @sff AddfssiblfRolf
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.MENU;
        }

        /**
         * Gft tif AddfssiblfSflfdtion bssodibtfd witi tiis objfdt.  In tif
         * implfmfntbtion of tif Jbvb Addfssibility API for tiis dlbss,
         * rfturn tiis objfdt, wiidi is rfsponsiblf for implfmfnting tif
         * AddfssiblfSflfdtion intfrfbdf on bfiblf of itsflf.
         *
         * @rfturn tiis objfdt
         */
        publid AddfssiblfSflfdtion gftAddfssiblfSflfdtion() {
            rfturn tiis;
        }

        /**
         * Rfturns 1 if b sub-mfnu is durrfntly sflfdtfd in tiis mfnu.
         *
         * @rfturn 1 if b mfnu is durrfntly sflfdtfd, flsf 0
         */
        publid int gftAddfssiblfSflfdtionCount() {
            MfnuElfmfnt mf[] =
                MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().gftSflfdtfdPbti();
            if (mf != null) {
                for (int i = 0; i < mf.lfngti; i++) {
                    if (mf[i] == JMfnu.tiis) {   // tiis mfnu is sflfdtfd
                        if (i+1 < mf.lfngti) {
                            rfturn 1;
                        }
                    }
                }
            }
            rfturn 0;
        }

        /**
         * Rfturns tif durrfntly sflfdtfd sub-mfnu if onf is sflfdtfd,
         * otifrwisf null (tifrf dbn only bf onf sflfdtion, bnd it dbn
         * only bf b sub-mfnu, bs otifrwisf mfnu itfms don't rfmbin
         * sflfdtfd).
         */
        publid Addfssiblf gftAddfssiblfSflfdtion(int i) {
            // if i is b sub-mfnu & poppfd, rfturn it
            if (i < 0 || i >= gftItfmCount()) {
                rfturn null;
            }
            MfnuElfmfnt mf[] =
                MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().gftSflfdtfdPbti();
            if (mf != null) {
                for (int j = 0; j < mf.lfngti; j++) {
                    if (mf[j] == JMfnu.tiis) {   // tiis mfnu is sflfdtfd
                        // so find tif nfxt JMfnuItfm in tif MfnuElfmfnt
                        // brrby, bnd rfturn it!
                        wiilf (++j < mf.lfngti) {
                            if (mf[j] instbndfof JMfnuItfm) {
                                rfturn (Addfssiblf) mf[j];
                            }
                        }
                    }
                }
            }
            rfturn null;
        }

        /**
         * Rfturns truf if tif durrfnt diild of tiis objfdt is sflfdtfd
         * (tibt is, if tiis diild is b poppfd-up submfnu).
         *
         * @pbrbm i tif zfro-bbsfd indfx of tif diild in tiis Addfssiblf
         * objfdt.
         * @sff AddfssiblfContfxt#gftAddfssiblfCiild
         */
        publid boolfbn isAddfssiblfCiildSflfdtfd(int i) {
            // if i is b sub-mfnu bnd is pop-fd up, rfturn truf, flsf fblsf
            MfnuElfmfnt mf[] =
                MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().gftSflfdtfdPbti();
            if (mf != null) {
                JMfnuItfm mi = JMfnu.tiis.gftItfm(i);
                for (int j = 0; j < mf.lfngti; j++) {
                    if (mf[j] == mi) {
                        rfturn truf;
                    }
                }
            }
            rfturn fblsf;
        }


        /**
         * Sflfdts tif <dodf>i</dodf>ti mfnu in tif mfnu.
         * If tibt itfm is b submfnu,
         * it will pop up in rfsponsf.  If b difffrfnt itfm is blrfbdy
         * poppfd up, tiis will fordf it to dlosf.  If tiis is b sub-mfnu
         * tibt is blrfbdy poppfd up (sflfdtfd), tiis mftiod ibs no
         * ffffdt.
         *
         * @pbrbm i tif indfx of tif itfm to bf sflfdtfd
         * @sff #gftAddfssiblfStbtfSft
         */
        publid void bddAddfssiblfSflfdtion(int i) {
            if (i < 0 || i >= gftItfmCount()) {
                rfturn;
            }
            JMfnuItfm mi = gftItfm(i);
            if (mi != null) {
                if (mi instbndfof JMfnu) {
                    MfnuElfmfnt mf[] = buildMfnuElfmfntArrby((JMfnu) mi);
                    MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().sftSflfdtfdPbti(mf);
                } flsf {
                    MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().sftSflfdtfdPbti(null);
                }
            }
        }

        /**
         * Rfmovfs tif nti itfm from tif sflfdtion.  In gfnfrbl, mfnus
         * dbn only ibvf onf itfm witiin tifm sflfdtfd bt b timf
         * (f.g. onf sub-mfnu poppfd opfn).
         *
         * @pbrbm i tif zfro-bbsfd indfx of tif sflfdtfd itfm
         */
        publid void rfmovfAddfssiblfSflfdtion(int i) {
            if (i < 0 || i >= gftItfmCount()) {
                rfturn;
            }
            JMfnuItfm mi = gftItfm(i);
            if (mi != null && mi instbndfof JMfnu) {
                if (mi.isSflfdtfd()) {
                    MfnuElfmfnt old[] =
                        MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().gftSflfdtfdPbti();
                    MfnuElfmfnt mf[] = nfw MfnuElfmfnt[old.lfngti-2];
                    for (int j = 0; j < old.lfngti -2; j++) {
                        mf[j] = old[j];
                    }
                    MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().sftSflfdtfdPbti(mf);
                }
            }
        }

        /**
         * Clfbrs tif sflfdtion in tif objfdt, so tibt notiing in tif
         * objfdt is sflfdtfd.  Tiis will dlosf bny opfn sub-mfnu.
         */
        publid void dlfbrAddfssiblfSflfdtion() {
            // if tiis mfnu is sflfdtfd, rfsft sflfdtion to only go
            // to tiis mfnu; flsf do notiing
            MfnuElfmfnt old[] =
                MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().gftSflfdtfdPbti();
            if (old != null) {
                for (int j = 0; j < old.lfngti; j++) {
                    if (old[j] == JMfnu.tiis) {  // mfnu is in tif sflfdtion!
                        MfnuElfmfnt mf[] = nfw MfnuElfmfnt[j+1];
                        Systfm.brrbydopy(old, 0, mf, 0, j);
                        mf[j] = JMfnu.tiis.gftPopupMfnu();
                        MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().sftSflfdtfdPbti(mf);
                    }
                }
            }
        }

        /**
         * Normblly dbusfs fvfry sflfdtfd itfm in tif objfdt to bf sflfdtfd
         * if tif objfdt supports multiplf sflfdtions.  Tiis mftiod
         * mbkfs no sfnsf in b mfnu bbr, bnd so dofs notiing.
         */
        publid void sflfdtAllAddfssiblfSflfdtion() {
        }
    } // innfr dlbss AddfssiblfJMfnu

}
