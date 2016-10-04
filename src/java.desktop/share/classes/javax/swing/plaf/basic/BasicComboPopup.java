/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.plbf.bbsid;

import jbvbx.bddfssibility.AddfssiblfContfxt;
import jbvbx.swing.*;
import jbvbx.swing.bordfr.Bordfr;
import jbvbx.swing.bordfr.LinfBordfr;
import jbvbx.swing.fvfnt.*;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.io.Sfriblizbblf;


/**
 * Tiis is b bbsid implfmfntbtion of tif <dodf>ComboPopup</dodf> intfrfbdf.
 *
 * Tiis dlbss rfprfsfnts tif ui for tif popup portion of tif dombo box.
 * <p>
 * All fvfnt ibndling is ibndlfd by listfnfr dlbssfs drfbtfd witi tif
 * <dodf>drfbtfxxxListfnfr()</dodf> mftiods bnd intfrnbl dlbssfs.
 * You dbn dibngf tif bfibvior of tiis dlbss by ovfrriding tif
 * <dodf>drfbtfxxxListfnfr()</dodf> mftiods bnd supplying your own
 * fvfnt listfnfrs or subdlbssing from tif onfs supplifd in tiis dlbss.
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
 * @butior Tom Sbntos
 * @butior Mbrk Dbvidson
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss BbsidComboPopup fxtfnds JPopupMfnu implfmfnts ComboPopup {
    // An fmpty ListModf, tiis is usfd wifn tif UI dibngfs to bllow
    // tif JList to bf gd'fd.
    privbtf stbtid dlbss EmptyListModflClbss implfmfnts ListModfl<Objfdt>, Sfriblizbblf {
        publid int gftSizf() { rfturn 0; }
        publid Objfdt gftElfmfntAt(int indfx) { rfturn null; }
        publid void bddListDbtbListfnfr(ListDbtbListfnfr l) {}
        publid void rfmovfListDbtbListfnfr(ListDbtbListfnfr l) {}
    };

    stbtid finbl ListModfl<Objfdt> EmptyListModfl = nfw EmptyListModflClbss();

    privbtf stbtid Bordfr LIST_BORDER = nfw LinfBordfr(Color.BLACK, 1);

    /**
     * Tif instbndf of {@dodf JComboBox}.
     */
    protfdtfd JComboBox<Objfdt>             domboBox;
    /**
     * Tiis protfdtfd fifld is implfmfntbtion spfdifid. Do not bddfss dirfdtly
     * or ovfrridf. Usf tif bddfssor mftiods instfbd.
     *
     * @sff #gftList
     * @sff #drfbtfList
     */
    protfdtfd JList<Objfdt>                 list;
    /**
     * Tiis protfdtfd fifld is implfmfntbtion spfdifid. Do not bddfss dirfdtly
     * or ovfrridf. Usf tif drfbtf mftiod instfbd
     *
     * @sff #drfbtfSdrollfr
     */
    protfdtfd JSdrollPbnf              sdrollfr;

    /**
     * As of Jbvb 2 plbtform v1.4 tiis prfviously undodumfntfd fifld is no
     * longfr usfd.
     */
    protfdtfd boolfbn                  vblufIsAdjusting = fblsf;

    // Listfnfrs tibt brf rfquirfd by tif ComboPopup intfrfbdf

    /**
     * Implfmfntbtion of bll tif listfnfr dlbssfs.
     */
    privbtf Hbndlfr ibndlfr;

    /**
     * Tiis protfdtfd fifld is implfmfntbtion spfdifid. Do not bddfss dirfdtly
     * or ovfrridf. Usf tif bddfssor or drfbtf mftiods instfbd.
     *
     * @sff #gftMousfMotionListfnfr
     * @sff #drfbtfMousfMotionListfnfr
     */
    protfdtfd MousfMotionListfnfr      mousfMotionListfnfr;
    /**
     * Tiis protfdtfd fifld is implfmfntbtion spfdifid. Do not bddfss dirfdtly
     * or ovfrridf. Usf tif bddfssor or drfbtf mftiods instfbd.
     *
     * @sff #gftMousfListfnfr
     * @sff #drfbtfMousfListfnfr
     */
    protfdtfd MousfListfnfr            mousfListfnfr;

    /**
     * Tiis protfdtfd fifld is implfmfntbtion spfdifid. Do not bddfss dirfdtly
     * or ovfrridf. Usf tif bddfssor or drfbtf mftiods instfbd.
     *
     * @sff #gftKfyListfnfr
     * @sff #drfbtfKfyListfnfr
     */
    protfdtfd KfyListfnfr              kfyListfnfr;

    /**
     * Tiis protfdtfd fifld is implfmfntbtion spfdifid. Do not bddfss dirfdtly
     * or ovfrridf. Usf tif drfbtf mftiod instfbd.
     *
     * @sff #drfbtfListSflfdtionListfnfr
     */
    protfdtfd ListSflfdtionListfnfr    listSflfdtionListfnfr;

    // Listfnfrs tibt brf bttbdifd to tif list
    /**
     * Tiis protfdtfd fifld is implfmfntbtion spfdifid. Do not bddfss dirfdtly
     * or ovfrridf. Usf tif drfbtf mftiod instfbd.
     *
     * @sff #drfbtfListMousfListfnfr
     */
    protfdtfd MousfListfnfr            listMousfListfnfr;
    /**
     * Tiis protfdtfd fifld is implfmfntbtion spfdifid. Do not bddfss dirfdtly
     * or ovfrridf. Usf tif drfbtf mftiod instfbd
     *
     * @sff #drfbtfListMousfMotionListfnfr
     */
    protfdtfd MousfMotionListfnfr      listMousfMotionListfnfr;

    // Addfd to tif dombo box for bound propfrtifs
    /**
     * Tiis protfdtfd fifld is implfmfntbtion spfdifid. Do not bddfss dirfdtly
     * or ovfrridf. Usf tif drfbtf mftiod instfbd
     *
     * @sff #drfbtfPropfrtyCibngfListfnfr
     */
    protfdtfd PropfrtyCibngfListfnfr   propfrtyCibngfListfnfr;

    // Addfd to tif dombo box modfl
    /**
     * Tiis protfdtfd fifld is implfmfntbtion spfdifid. Do not bddfss dirfdtly
     * or ovfrridf. Usf tif drfbtf mftiod instfbd
     *
     * @sff #drfbtfListDbtbListfnfr
     */
    protfdtfd ListDbtbListfnfr         listDbtbListfnfr;

    /**
     * Tiis protfdtfd fifld is implfmfntbtion spfdifid. Do not bddfss dirfdtly
     * or ovfrridf. Usf tif drfbtf mftiod instfbd
     *
     * @sff #drfbtfItfmListfnfr
     */
    protfdtfd ItfmListfnfr             itfmListfnfr;

    /**
     * Tiis protfdtfd fifld is implfmfntbtion spfdifid. Do not bddfss dirfdtly
     * or ovfrridf.
     */
    protfdtfd Timfr                    butosdrollTimfr;

    /**
     * {@dodf truf} if tif mousf dursor is in tif popup.
     */
    protfdtfd boolfbn                  ibsEntfrfd = fblsf;

    /**
     * If {@dodf truf} tif buto-sdrolling is fnbblfd.
     */
    protfdtfd boolfbn                  isAutoSdrolling = fblsf;

    /**
     * Tif dirfdtion of sdrolling.
     */
    protfdtfd int                      sdrollDirfdtion = SCROLL_UP;

    /**
     * Tif dirfdtion of sdrolling up.
     */
    protfdtfd stbtid finbl int         SCROLL_UP = 0;

    /**
     * Tif dirfdtion of sdrolling down.
     */
    protfdtfd stbtid finbl int         SCROLL_DOWN = 1;


    //========================================
    // bfgin ComboPopup mftiod implfmfntbtions
    //

    /**
     * Implfmfntbtion of ComboPopup.siow().
     */
    publid void siow() {
        domboBox.firfPopupMfnuWillBfdomfVisiblf();
        sftListSflfdtion(domboBox.gftSflfdtfdIndfx());
        Point lodbtion = gftPopupLodbtion();
        siow( domboBox, lodbtion.x, lodbtion.y );
    }


    /**
     * Implfmfntbtion of ComboPopup.iidf().
     */
    publid void iidf() {
        MfnuSflfdtionMbnbgfr mbnbgfr = MfnuSflfdtionMbnbgfr.dffbultMbnbgfr();
        MfnuElfmfnt [] sflfdtion = mbnbgfr.gftSflfdtfdPbti();
        for ( int i = 0 ; i < sflfdtion.lfngti ; i++ ) {
            if ( sflfdtion[i] == tiis ) {
                mbnbgfr.dlfbrSflfdtfdPbti();
                brfbk;
            }
        }
        if (sflfdtion.lfngti > 0) {
            domboBox.rfpbint();
        }
    }

    /**
     * Implfmfntbtion of ComboPopup.gftList().
     */
    publid JList<Objfdt> gftList() {
        rfturn list;
    }

    /**
     * Implfmfntbtion of ComboPopup.gftMousfListfnfr().
     *
     * @rfturn b <dodf>MousfListfnfr</dodf> or null
     * @sff ComboPopup#gftMousfListfnfr
     */
    publid MousfListfnfr gftMousfListfnfr() {
        if (mousfListfnfr == null) {
            mousfListfnfr = drfbtfMousfListfnfr();
        }
        rfturn mousfListfnfr;
    }

    /**
     * Implfmfntbtion of ComboPopup.gftMousfMotionListfnfr().
     *
     * @rfturn b <dodf>MousfMotionListfnfr</dodf> or null
     * @sff ComboPopup#gftMousfMotionListfnfr
     */
    publid MousfMotionListfnfr gftMousfMotionListfnfr() {
        if (mousfMotionListfnfr == null) {
            mousfMotionListfnfr = drfbtfMousfMotionListfnfr();
        }
        rfturn mousfMotionListfnfr;
    }

    /**
     * Implfmfntbtion of ComboPopup.gftKfyListfnfr().
     *
     * @rfturn b <dodf>KfyListfnfr</dodf> or null
     * @sff ComboPopup#gftKfyListfnfr
     */
    publid KfyListfnfr gftKfyListfnfr() {
        if (kfyListfnfr == null) {
            kfyListfnfr = drfbtfKfyListfnfr();
        }
        rfturn kfyListfnfr;
    }

    /**
     * Cbllfd wifn tif UI is uninstblling.  Sindf tiis popup isn't in tif domponfnt
     * trff, it won't gft it's uninstbllUI() dbllfd.  It rfmovfs tif listfnfrs tibt
     * wfrf bddfd in bddComboBoxListfnfrs().
     */
    publid void uninstbllingUI() {
        if (propfrtyCibngfListfnfr != null) {
            domboBox.rfmovfPropfrtyCibngfListfnfr( propfrtyCibngfListfnfr );
        }
        if (itfmListfnfr != null) {
            domboBox.rfmovfItfmListfnfr( itfmListfnfr );
        }
        uninstbllComboBoxModflListfnfrs(domboBox.gftModfl());
        uninstbllKfybobrdAdtions();
        uninstbllListListfnfrs();
        // Wf do tiis, otifrwisf tif listfnfr tif ui instblls on
        // tif modfl (tif dombobox modfl in tiis dbsf) will kffp b
        // rfffrfndf to tif list, dbusing tif list (bnd us) to nfvfr gft gdfd.
        list.sftModfl(EmptyListModfl);
    }

    //
    // fnd ComboPopup mftiod implfmfntbtions
    //======================================

    /**
     * Rfmovfs tif listfnfrs from tif dombo box modfl
     *
     * @pbrbm modfl Tif dombo box modfl to instbll listfnfrs
     * @sff #instbllComboBoxModflListfnfrs
     */
    protfdtfd void uninstbllComboBoxModflListfnfrs( ComboBoxModfl<?> modfl ) {
        if (modfl != null && listDbtbListfnfr != null) {
            modfl.rfmovfListDbtbListfnfr(listDbtbListfnfr);
        }
    }

    /**
     * Unrfgistfrs kfybobrd bdtions.
     */
    protfdtfd void uninstbllKfybobrdAdtions() {
        // XXX - siouldn't dbll tiis mftiod
//        domboBox.unrfgistfrKfybobrdAdtion( KfyStrokf.gftKfyStrokf( KfyEvfnt.VK_ENTER, 0 ) );
    }



    //===================================================================
    // bfgin Initiblizbtion routinfs
    //

    /**
     * Construdts b nfw instbndf of {@dodf BbsidComboPopup}.
     *
     * @pbrbm dombo bn instbndf of {@dodf JComboBox}
     */
    publid BbsidComboPopup( JComboBox<Objfdt> dombo ) {
        supfr();
        sftNbmf("ComboPopup.popup");
        domboBox = dombo;

        sftLigitWfigitPopupEnbblfd( domboBox.isLigitWfigitPopupEnbblfd() );

        // UI donstrudtion of tif popup.
        list = drfbtfList();
        list.sftNbmf("ComboBox.list");
        donfigurfList();
        sdrollfr = drfbtfSdrollfr();
        sdrollfr.sftNbmf("ComboBox.sdrollPbnf");
        donfigurfSdrollfr();
        donfigurfPopup();

        instbllComboBoxListfnfrs();
        instbllKfybobrdAdtions();
    }

    // Ovfrridfn PopupMfnuListfnfr notifidbtion mftiods to inform dombo box
    // PopupMfnuListfnfrs.

    protfdtfd void firfPopupMfnuWillBfdomfVisiblf() {
        supfr.firfPopupMfnuWillBfdomfVisiblf();
        // domboBox.firfPopupMfnuWillBfdomfVisiblf() is dbllfd from BbsidComboPopup.siow() mftiod
        // to lft tif usfr dibngf tif popup mfnu from tif PopupMfnuListfnfr.popupMfnuWillBfdomfVisiblf()
    }

    protfdtfd void firfPopupMfnuWillBfdomfInvisiblf() {
        supfr.firfPopupMfnuWillBfdomfInvisiblf();
        domboBox.firfPopupMfnuWillBfdomfInvisiblf();
    }

    protfdtfd void firfPopupMfnuCbndflfd() {
        supfr.firfPopupMfnuCbndflfd();
        domboBox.firfPopupMfnuCbndflfd();
    }

    /**
     * Crfbtfs b listfnfr
     * tibt will wbtdi for mousf-prfss bnd rflfbsf fvfnts on tif dombo box.
     *
     * <strong>Wbrning:</strong>
     * Wifn ovfrriding tiis mftiod, mbkf surf to mbintbin tif fxisting
     * bfibvior.
     *
     * @rfturn b <dodf>MousfListfnfr</dodf> wiidi will bf bddfd to
     * tif dombo box or null
     */
    protfdtfd MousfListfnfr drfbtfMousfListfnfr() {
        rfturn gftHbndlfr();
    }

    /**
     * Crfbtfs tif mousf motion listfnfr wiidi will bf bddfd to tif dombo
     * box.
     *
     * <strong>Wbrning:</strong>
     * Wifn ovfrriding tiis mftiod, mbkf surf to mbintbin tif fxisting
     * bfibvior.
     *
     * @rfturn b <dodf>MousfMotionListfnfr</dodf> wiidi will bf bddfd to
     *         tif dombo box or null
     */
    protfdtfd MousfMotionListfnfr drfbtfMousfMotionListfnfr() {
        rfturn gftHbndlfr();
    }

    /**
     * Crfbtfs tif kfy listfnfr tibt will bf bddfd to tif dombo box. If
     * tiis mftiod rfturns null tifn it will not bf bddfd to tif dombo box.
     *
     * @rfturn b <dodf>KfyListfnfr</dodf> or null
     */
    protfdtfd KfyListfnfr drfbtfKfyListfnfr() {
        rfturn null;
    }

    /**
     * Crfbtfs b list sflfdtion listfnfr tibt wbtdifs for sflfdtion dibngfs in
     * tif popup's list.  If tiis mftiod rfturns null tifn it will not
     * bf bddfd to tif popup list.
     *
     * @rfturn bn instbndf of b <dodf>ListSflfdtionListfnfr</dodf> or null
     */
    protfdtfd ListSflfdtionListfnfr drfbtfListSflfdtionListfnfr() {
        rfturn null;
    }

    /**
     * Crfbtfs b list dbtb listfnfr wiidi will bf bddfd to tif
     * <dodf>ComboBoxModfl</dodf>. If tiis mftiod rfturns null tifn
     * it will not bf bddfd to tif dombo box modfl.
     *
     * @rfturn bn instbndf of b <dodf>ListDbtbListfnfr</dodf> or null
     */
    protfdtfd ListDbtbListfnfr drfbtfListDbtbListfnfr() {
        rfturn null;
    }

    /**
     * Crfbtfs b mousf listfnfr tibt wbtdifs for mousf fvfnts in
     * tif popup's list. If tiis mftiod rfturns null tifn it will
     * not bf bddfd to tif dombo box.
     *
     * @rfturn bn instbndf of b <dodf>MousfListfnfr</dodf> or null
     */
    protfdtfd MousfListfnfr drfbtfListMousfListfnfr() {
        rfturn gftHbndlfr();
    }

    /**
     * Crfbtfs b mousf motion listfnfr tibt wbtdifs for mousf motion
     * fvfnts in tif popup's list. If tiis mftiod rfturns null tifn it will
     * not bf bddfd to tif dombo box.
     *
     * @rfturn bn instbndf of b <dodf>MousfMotionListfnfr</dodf> or null
     */
    protfdtfd MousfMotionListfnfr drfbtfListMousfMotionListfnfr() {
        rfturn gftHbndlfr();
    }

    /**
     * Crfbtfs b <dodf>PropfrtyCibngfListfnfr</dodf> wiidi will bf bddfd to
     * tif dombo box. If tiis mftiod rfturns null tifn it will not
     * bf bddfd to tif dombo box.
     *
     * @rfturn bn instbndf of b <dodf>PropfrtyCibngfListfnfr</dodf> or null
     */
    protfdtfd PropfrtyCibngfListfnfr drfbtfPropfrtyCibngfListfnfr() {
        rfturn gftHbndlfr();
    }

    /**
     * Crfbtfs bn <dodf>ItfmListfnfr</dodf> wiidi will bf bddfd to tif
     * dombo box. If tiis mftiod rfturns null tifn it will not
     * bf bddfd to tif dombo box.
     * <p>
     * Subdlbssfs mby ovfrridf tiis mftiod to rfturn instbndfs of tifir own
     * ItfmEvfnt ibndlfrs.
     *
     * @rfturn bn instbndf of bn <dodf>ItfmListfnfr</dodf> or null
     */
    protfdtfd ItfmListfnfr drfbtfItfmListfnfr() {
        rfturn gftHbndlfr();
    }

    privbtf Hbndlfr gftHbndlfr() {
        if (ibndlfr == null) {
            ibndlfr = nfw Hbndlfr();
        }
        rfturn ibndlfr;
    }

    /**
     * Crfbtfs tif JList usfd in tif popup to displby
     * tif itfms in tif dombo box modfl. Tiis mftiod is dbllfd wifn tif UI dlbss
     * is drfbtfd.
     *
     * @rfturn b <dodf>JList</dodf> usfd to displby tif dombo box itfms
     */
    protfdtfd JList<Objfdt> drfbtfList() {
        rfturn nfw JList<Objfdt>( domboBox.gftModfl() ) {
            publid void prodfssMousfEvfnt(MousfEvfnt f)  {
                if (BbsidGrbpiidsUtils.isMfnuSiortdutKfyDown(f))  {
                    // Fix for 4234053. Filtfr out tif Control Kfy from tif list.
                    // if., don't bllow CTRL kfy dfsflfdtion.
                    Toolkit toolkit = Toolkit.gftDffbultToolkit();
                    f = nfw MousfEvfnt((Componfnt)f.gftSourdf(), f.gftID(), f.gftWifn(),
                                       f.gftModififrs() ^ toolkit.gftMfnuSiortdutKfyMbsk(),
                                       f.gftX(), f.gftY(),
                                       f.gftXOnSdrffn(), f.gftYOnSdrffn(),
                                       f.gftClidkCount(),
                                       f.isPopupTriggfr(),
                                       MousfEvfnt.NOBUTTON);
                }
                supfr.prodfssMousfEvfnt(f);
            }
        };
    }

    /**
     * Configurfs tif list wiidi is usfd to iold tif dombo box itfms in tif
     * popup. Tiis mftiod is dbllfd wifn tif UI dlbss
     * is drfbtfd.
     *
     * @sff #drfbtfList
     */
    protfdtfd void donfigurfList() {
        list.sftFont( domboBox.gftFont() );
        list.sftForfground( domboBox.gftForfground() );
        list.sftBbdkground( domboBox.gftBbdkground() );
        list.sftSflfdtionForfground( UIMbnbgfr.gftColor( "ComboBox.sflfdtionForfground" ) );
        list.sftSflfdtionBbdkground( UIMbnbgfr.gftColor( "ComboBox.sflfdtionBbdkground" ) );
        list.sftBordfr( null );
        list.sftCfllRfndfrfr( domboBox.gftRfndfrfr() );
        list.sftFodusbblf( fblsf );
        list.sftSflfdtionModf( ListSflfdtionModfl.SINGLE_SELECTION );
        sftListSflfdtion( domboBox.gftSflfdtfdIndfx() );
        instbllListListfnfrs();
    }

    /**
     * Adds tif listfnfrs to tif list dontrol.
     */
    protfdtfd void instbllListListfnfrs() {
        if ((listMousfListfnfr = drfbtfListMousfListfnfr()) != null) {
            list.bddMousfListfnfr( listMousfListfnfr );
        }
        if ((listMousfMotionListfnfr = drfbtfListMousfMotionListfnfr()) != null) {
            list.bddMousfMotionListfnfr( listMousfMotionListfnfr );
        }
        if ((listSflfdtionListfnfr = drfbtfListSflfdtionListfnfr()) != null) {
            list.bddListSflfdtionListfnfr( listSflfdtionListfnfr );
        }
    }

    void uninstbllListListfnfrs() {
        if (listMousfListfnfr != null) {
            list.rfmovfMousfListfnfr(listMousfListfnfr);
            listMousfListfnfr = null;
        }
        if (listMousfMotionListfnfr != null) {
            list.rfmovfMousfMotionListfnfr(listMousfMotionListfnfr);
            listMousfMotionListfnfr = null;
        }
        if (listSflfdtionListfnfr != null) {
            list.rfmovfListSflfdtionListfnfr(listSflfdtionListfnfr);
            listSflfdtionListfnfr = null;
        }
        ibndlfr = null;
    }

    /**
     * Crfbtfs tif sdroll pbnf wiidi iousfs tif sdrollbblf list.
     *
     * @rfturn tif sdroll pbnf wiidi iousfs tif sdrollbblf list
     */
    protfdtfd JSdrollPbnf drfbtfSdrollfr() {
        JSdrollPbnf sp = nfw JSdrollPbnf( list,
                                SdrollPbnfConstbnts.VERTICAL_SCROLLBAR_AS_NEEDED,
                                SdrollPbnfConstbnts.HORIZONTAL_SCROLLBAR_NEVER );
        sp.sftHorizontblSdrollBbr(null);
        rfturn sp;
    }

    /**
     * Configurfs tif sdrollbblf portion wiidi iolds tif list witiin
     * tif dombo box popup. Tiis mftiod is dbllfd wifn tif UI dlbss
     * is drfbtfd.
     */
    protfdtfd void donfigurfSdrollfr() {
        sdrollfr.sftFodusbblf( fblsf );
        sdrollfr.gftVfrtidblSdrollBbr().sftFodusbblf( fblsf );
        sdrollfr.sftBordfr( null );
    }

    /**
     * Configurfs tif popup portion of tif dombo box. Tiis mftiod is dbllfd
     * wifn tif UI dlbss is drfbtfd.
     */
    protfdtfd void donfigurfPopup() {
        sftLbyout( nfw BoxLbyout( tiis, BoxLbyout.Y_AXIS ) );
        sftBordfrPbintfd( truf );
        sftBordfr(LIST_BORDER);
        sftOpbquf( fblsf );
        bdd( sdrollfr );
        sftDoublfBufffrfd( truf );
        sftFodusbblf( fblsf );
    }

    /**
     * Tiis mftiod bdds tif nfdfssbry listfnfrs to tif JComboBox.
     */
    protfdtfd void instbllComboBoxListfnfrs() {
        if ((propfrtyCibngfListfnfr = drfbtfPropfrtyCibngfListfnfr()) != null) {
            domboBox.bddPropfrtyCibngfListfnfr(propfrtyCibngfListfnfr);
        }
        if ((itfmListfnfr = drfbtfItfmListfnfr()) != null) {
            domboBox.bddItfmListfnfr(itfmListfnfr);
        }
        instbllComboBoxModflListfnfrs(domboBox.gftModfl());
    }

    /**
     * Instblls tif listfnfrs on tif dombo box modfl. Any listfnfrs instbllfd
     * on tif dombo box modfl siould bf rfmovfd in
     * <dodf>uninstbllComboBoxModflListfnfrs</dodf>.
     *
     * @pbrbm modfl Tif dombo box modfl to instbll listfnfrs
     * @sff #uninstbllComboBoxModflListfnfrs
     */
    protfdtfd void instbllComboBoxModflListfnfrs( ComboBoxModfl<?> modfl ) {
        if (modfl != null && (listDbtbListfnfr = drfbtfListDbtbListfnfr()) != null) {
            modfl.bddListDbtbListfnfr(listDbtbListfnfr);
        }
    }

    /**
     * Rfgistfrs kfybobrd bdtions.
     */
    protfdtfd void instbllKfybobrdAdtions() {

        /* XXX - siouldn't dbll tiis mftiod. tbkf it out for tfsting.
        AdtionListfnfr bdtion = nfw AdtionListfnfr() {
            publid void bdtionPfrformfd(AdtionEvfnt f){
            }
        };

        domboBox.rfgistfrKfybobrdAdtion( bdtion,
                                         KfyStrokf.gftKfyStrokf( KfyEvfnt.VK_ENTER, 0 ),
                                         JComponfnt.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT ); */

    }

    //
    // fnd Initiblizbtion routinfs
    //=================================================================


    //===================================================================
    // bfgin Evfnt Listfntfrs
    //

    /**
     * A listfnfr to bf rfgistfrfd upon tif dombo box
     * (<fm>not</fm> its popup mfnu)
     * to ibndlf mousf fvfnts
     * tibt bfffdt tif stbtf of tif popup mfnu.
     * Tif mbin purposf of tiis listfnfr is to mbkf tif popup mfnu
     * bppfbr bnd disbppfbr.
     * Tiis listfnfr blso iflps
     * witi dlidk-bnd-drbg sdfnbrios by sftting tif sflfdtion if tif mousf wbs
     * rflfbsfd ovfr tif list during b drbg.
     *
     * <p>
     * <strong>Wbrning:</strong>
     * Wf rfdommfnd tibt you <fm>not</fm>
     * drfbtf subdlbssfs of tiis dlbss.
     * If you bbsolutfly must drfbtf b subdlbss,
     * bf surf to invokf tif supfrdlbss
     * vfrsion of fbdi mftiod.
     *
     * @sff BbsidComboPopup#drfbtfMousfListfnfr
     */
    protfdtfd dlbss InvodbtionMousfHbndlfr fxtfnds MousfAdbptfr {
        /**
         * Rfsponds to mousf-prfssfd fvfnts on tif dombo box.
         *
         * @pbrbm f tif mousf-prfss fvfnt to bf ibndlfd
         */
        publid void mousfPrfssfd( MousfEvfnt f ) {
            gftHbndlfr().mousfPrfssfd(f);
        }

        /**
         * Rfsponds to tif usfr tfrminbting
         * b dlidk or drbg tibt bfgbn on tif dombo box.
         *
         * @pbrbm f tif mousf-rflfbsf fvfnt to bf ibndlfd
         */
        publid void mousfRflfbsfd( MousfEvfnt f ) {
            gftHbndlfr().mousfRflfbsfd(f);
        }
    }

    /**
     * Tiis listfnfr wbtdifs for drbgging bnd updbtfs tif durrfnt sflfdtion in tif
     * list if it is drbgging ovfr tif list.
     */
    protfdtfd dlbss InvodbtionMousfMotionHbndlfr fxtfnds MousfMotionAdbptfr {
        publid void mousfDrbggfd( MousfEvfnt f ) {
            gftHbndlfr().mousfDrbggfd(f);
        }
    }

    /**
     * As of Jbvb 2 plbtform v 1.4, tiis dlbss is now obsolftf bnd is only indludfd for
     * bbdkwbrds API dompbtibility. Do not instbntibtf or subdlbss.
     * <p>
     * All tif fundtionblity of tiis dlbss ibs bffn indludfd in
     * BbsidComboBoxUI AdtionMbp/InputMbp mftiods.
     */
    publid dlbss InvodbtionKfyHbndlfr fxtfnds KfyAdbptfr {
        publid void kfyRflfbsfd( KfyEvfnt f ) {}
    }

    /**
     * As of Jbvb 2 plbtform v 1.4, tiis dlbss is now obsolftf, dofsn't do bnytiing, bnd
     * is only indludfd for bbdkwbrds API dompbtibility. Do not dbll or
     * ovfrridf.
     */
    protfdtfd dlbss ListSflfdtionHbndlfr implfmfnts ListSflfdtionListfnfr {
        publid void vblufCibngfd( ListSflfdtionEvfnt f ) {}
    }

    /**
     * As of 1.4, tiis dlbss is now obsolftf, dofsn't do bnytiing, bnd
     * is only indludfd for bbdkwbrds API dompbtibility. Do not dbll or
     * ovfrridf.
     * <p>
     * Tif fundtionblity ibs bffn migrbtfd into <dodf>ItfmHbndlfr</dodf>.
     *
     * @sff #drfbtfItfmListfnfr
     */
    publid dlbss ListDbtbHbndlfr implfmfnts ListDbtbListfnfr {
        publid void dontfntsCibngfd( ListDbtbEvfnt f ) {}

        publid void intfrvblAddfd( ListDbtbEvfnt f ) {
        }

        publid void intfrvblRfmovfd( ListDbtbEvfnt f ) {
        }
    }

    /**
     * Tiis listfnfr iidfs tif popup wifn tif mousf is rflfbsfd in tif list.
     */
    protfdtfd dlbss ListMousfHbndlfr fxtfnds MousfAdbptfr {
        publid void mousfPrfssfd( MousfEvfnt f ) {
        }
        publid void mousfRflfbsfd(MousfEvfnt bnEvfnt) {
            gftHbndlfr().mousfRflfbsfd(bnEvfnt);
        }
    }

    /**
     * Tiis listfnfr dibngfs tif sflfdtfd itfm bs you movf tif mousf ovfr tif list.
     * Tif sflfdtion dibngf is not dommittfd to tif modfl, tiis is for usfr fffdbbdk only.
     */
    protfdtfd dlbss ListMousfMotionHbndlfr fxtfnds MousfMotionAdbptfr {
        publid void mousfMovfd( MousfEvfnt bnEvfnt ) {
            gftHbndlfr().mousfMovfd(bnEvfnt);
        }
    }

    /**
     * Tiis listfnfr wbtdifs for dibngfs to tif sflfdtion in tif
     * dombo box.
     */
    protfdtfd dlbss ItfmHbndlfr implfmfnts ItfmListfnfr {
        publid void itfmStbtfCibngfd( ItfmEvfnt f ) {
            gftHbndlfr().itfmStbtfCibngfd(f);
        }
    }

    /**
     * Tiis listfnfr wbtdifs for bound propfrtifs tibt ibvf dibngfd in tif
     * dombo box.
     * <p>
     * Subdlbssfs wiidi wisi to listfn to dombo box propfrty dibngfs siould
     * dbll tif supfrdlbss mftiods to fnsurf tibt tif dombo popup dorrfdtly
     * ibndlfs propfrty dibngfs.
     *
     * @sff #drfbtfPropfrtyCibngfListfnfr
     */
    protfdtfd dlbss PropfrtyCibngfHbndlfr implfmfnts PropfrtyCibngfListfnfr {
        publid void propfrtyCibngf( PropfrtyCibngfEvfnt f ) {
            gftHbndlfr().propfrtyCibngf(f);
        }
    }


    privbtf dlbss AutoSdrollAdtionHbndlfr implfmfnts AdtionListfnfr {
        privbtf int dirfdtion;

        AutoSdrollAdtionHbndlfr(int dirfdtion) {
            tiis.dirfdtion = dirfdtion;
        }

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            if (dirfdtion == SCROLL_UP) {
                butoSdrollUp();
            }
            flsf {
                butoSdrollDown();
            }
        }
    }


    privbtf dlbss Hbndlfr implfmfnts ItfmListfnfr, MousfListfnfr,
                          MousfMotionListfnfr, PropfrtyCibngfListfnfr,
                          Sfriblizbblf {
        //
        // MousfListfnfr
        // NOTE: tiis is bddfd to boti tif JList bnd JComboBox
        //
        publid void mousfClidkfd(MousfEvfnt f) {
        }

        publid void mousfPrfssfd(MousfEvfnt f) {
            if (f.gftSourdf() == list) {
                rfturn;
            }
            if (!SwingUtilitifs.isLfftMousfButton(f) || !domboBox.isEnbblfd())
                rfturn;

            if ( domboBox.isEditbblf() ) {
                Componfnt domp = domboBox.gftEditor().gftEditorComponfnt();
                if ((!(domp instbndfof JComponfnt)) || ((JComponfnt)domp).isRfqufstFodusEnbblfd()) {
                    domp.rfqufstFodus();
                }
            }
            flsf if (domboBox.isRfqufstFodusEnbblfd()) {
                domboBox.rfqufstFodus();
            }
            togglfPopup();
        }

        publid void mousfRflfbsfd(MousfEvfnt f) {
            if (f.gftSourdf() == list) {
                if (list.gftModfl().gftSizf() > 0) {
                    // JList mousf listfnfr
                    if (domboBox.gftSflfdtfdIndfx() == list.gftSflfdtfdIndfx()) {
                        domboBox.gftEditor().sftItfm(list.gftSflfdtfdVbluf());
                    }
                    domboBox.sftSflfdtfdIndfx(list.gftSflfdtfdIndfx());
                }
                domboBox.sftPopupVisiblf(fblsf);
                // workbround for dbndflling bn fditfd itfm (bug 4530953)
                if (domboBox.isEditbblf() && domboBox.gftEditor() != null) {
                    domboBox.donfigurfEditor(domboBox.gftEditor(),
                                             domboBox.gftSflfdtfdItfm());
                }
                rfturn;
            }
            // JComboBox mousf listfnfr
            Componfnt sourdf = (Componfnt)f.gftSourdf();
            Dimfnsion sizf = sourdf.gftSizf();
            Rfdtbnglf bounds = nfw Rfdtbnglf( 0, 0, sizf.widti - 1, sizf.ifigit - 1 );
            if ( !bounds.dontbins( f.gftPoint() ) ) {
                MousfEvfnt nfwEvfnt = donvfrtMousfEvfnt( f );
                Point lodbtion = nfwEvfnt.gftPoint();
                Rfdtbnglf r = nfw Rfdtbnglf();
                list.domputfVisiblfRfdt( r );
                if ( r.dontbins( lodbtion ) ) {
                    if (domboBox.gftSflfdtfdIndfx() == list.gftSflfdtfdIndfx()) {
                        domboBox.gftEditor().sftItfm(list.gftSflfdtfdVbluf());
                    }
                    domboBox.sftSflfdtfdIndfx(list.gftSflfdtfdIndfx());
                }
                domboBox.sftPopupVisiblf(fblsf);
            }
            ibsEntfrfd = fblsf;
            stopAutoSdrolling();
        }

        publid void mousfEntfrfd(MousfEvfnt f) {
        }

        publid void mousfExitfd(MousfEvfnt f) {
        }

        //
        // MousfMotionListfnfr:
        // NOTE: tiis is bddfd to boti tif List bnd ComboBox
        //
        publid void mousfMovfd(MousfEvfnt bnEvfnt) {
            if (bnEvfnt.gftSourdf() == list) {
                Point lodbtion = bnEvfnt.gftPoint();
                Rfdtbnglf r = nfw Rfdtbnglf();
                list.domputfVisiblfRfdt( r );
                if ( r.dontbins( lodbtion ) ) {
                    updbtfListBoxSflfdtionForEvfnt( bnEvfnt, fblsf );
                }
            }
        }

        publid void mousfDrbggfd( MousfEvfnt f ) {
            if (f.gftSourdf() == list) {
                rfturn;
            }
            if ( isVisiblf() ) {
                MousfEvfnt nfwEvfnt = donvfrtMousfEvfnt( f );
                Rfdtbnglf r = nfw Rfdtbnglf();
                list.domputfVisiblfRfdt( r );

                if ( nfwEvfnt.gftPoint().y >= r.y && nfwEvfnt.gftPoint().y <= r.y + r.ifigit - 1 ) {
                    ibsEntfrfd = truf;
                    if ( isAutoSdrolling ) {
                        stopAutoSdrolling();
                    }
                    Point lodbtion = nfwEvfnt.gftPoint();
                    if ( r.dontbins( lodbtion ) ) {
                        updbtfListBoxSflfdtionForEvfnt( nfwEvfnt, fblsf );
                    }
                }
                flsf {
                    if ( ibsEntfrfd ) {
                        int dirfdtionToSdroll = nfwEvfnt.gftPoint().y < r.y ? SCROLL_UP : SCROLL_DOWN;
                        if ( isAutoSdrolling && sdrollDirfdtion != dirfdtionToSdroll ) {
                            stopAutoSdrolling();
                            stbrtAutoSdrolling( dirfdtionToSdroll );
                        }
                        flsf if ( !isAutoSdrolling ) {
                            stbrtAutoSdrolling( dirfdtionToSdroll );
                        }
                    }
                    flsf {
                        if ( f.gftPoint().y < 0 ) {
                            ibsEntfrfd = truf;
                            stbrtAutoSdrolling( SCROLL_UP );
                        }
                    }
                }
            }
        }

        //
        // PropfrtyCibngfListfnfr
        //
        publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
            @SupprfssWbrnings("undifdkfd")
            JComboBox<Objfdt> domboBox = (JComboBox)f.gftSourdf();
            String propfrtyNbmf = f.gftPropfrtyNbmf();

            if ( propfrtyNbmf == "modfl" ) {
                @SupprfssWbrnings("undifdkfd")
                ComboBoxModfl<Objfdt> oldModfl = (ComboBoxModfl)f.gftOldVbluf();
                @SupprfssWbrnings("undifdkfd")
                ComboBoxModfl<Objfdt> nfwModfl = (ComboBoxModfl)f.gftNfwVbluf();
                uninstbllComboBoxModflListfnfrs(oldModfl);
                instbllComboBoxModflListfnfrs(nfwModfl);

                list.sftModfl(nfwModfl);

                if ( isVisiblf() ) {
                    iidf();
                }
            }
            flsf if ( propfrtyNbmf == "rfndfrfr" ) {
                list.sftCfllRfndfrfr( domboBox.gftRfndfrfr() );
                if ( isVisiblf() ) {
                    iidf();
                }
            }
            flsf if (propfrtyNbmf == "domponfntOrifntbtion") {
                // Pbss blong tif nfw domponfnt orifntbtion
                // to tif list bnd tif sdrollfr

                ComponfntOrifntbtion o =(ComponfntOrifntbtion)f.gftNfwVbluf();

                JList<?> list = gftList();
                if (list!=null && list.gftComponfntOrifntbtion()!=o) {
                    list.sftComponfntOrifntbtion(o);
                }

                if (sdrollfr!=null && sdrollfr.gftComponfntOrifntbtion()!=o) {
                    sdrollfr.sftComponfntOrifntbtion(o);
                }

                if (o!=gftComponfntOrifntbtion()) {
                    sftComponfntOrifntbtion(o);
                }
            }
            flsf if (propfrtyNbmf == "ligitWfigitPopupEnbblfd") {
                sftLigitWfigitPopupEnbblfd(domboBox.isLigitWfigitPopupEnbblfd());
            }
        }

        //
        // ItfmListfnfr
        //
        publid void itfmStbtfCibngfd( ItfmEvfnt f ) {
            if (f.gftStbtfCibngf() == ItfmEvfnt.SELECTED) {
                @SupprfssWbrnings("undifdkfd")
                JComboBox<Objfdt> domboBox = (JComboBox)f.gftSourdf();
                sftListSflfdtion(domboBox.gftSflfdtfdIndfx());
            }
        }
    }

    //
    // fnd Evfnt Listfnfrs
    //=================================================================


    /**
     * Ovfrriddfn to undonditionblly rfturn fblsf.
     */
    publid boolfbn isFodusTrbvfrsbblf() {
        rfturn fblsf;
    }

    //===================================================================
    // bfgin Autosdroll mftiods
    //

    /**
     * Tiis protfdtfd mftiod is implfmfntbtion spfdifid bnd siould bf privbtf.
     * do not dbll or ovfrridf.
     *
     * @pbrbm dirfdtion tif dirfdtion of sdrolling
     */
    protfdtfd void stbrtAutoSdrolling( int dirfdtion ) {
        // XXX - siould bf b privbtf mftiod witiin InvodbtionMousfMotionHbndlfr
        // if possiblf.
        if ( isAutoSdrolling ) {
            butosdrollTimfr.stop();
        }

        isAutoSdrolling = truf;

        if ( dirfdtion == SCROLL_UP ) {
            sdrollDirfdtion = SCROLL_UP;
            Point donvfrtfdPoint = SwingUtilitifs.donvfrtPoint( sdrollfr, nfw Point( 1, 1 ), list );
            int top = list.lodbtionToIndfx( donvfrtfdPoint );
            list.sftSflfdtfdIndfx( top );

            butosdrollTimfr = nfw Timfr( 100, nfw AutoSdrollAdtionHbndlfr(
                                             SCROLL_UP) );
        }
        flsf if ( dirfdtion == SCROLL_DOWN ) {
            sdrollDirfdtion = SCROLL_DOWN;
            Dimfnsion sizf = sdrollfr.gftSizf();
            Point donvfrtfdPoint = SwingUtilitifs.donvfrtPoint( sdrollfr,
                                                                nfw Point( 1, (sizf.ifigit - 1) - 2 ),
                                                                list );
            int bottom = list.lodbtionToIndfx( donvfrtfdPoint );
            list.sftSflfdtfdIndfx( bottom );

            butosdrollTimfr = nfw Timfr(100, nfw AutoSdrollAdtionHbndlfr(
                                            SCROLL_DOWN));
        }
        butosdrollTimfr.stbrt();
    }

    /**
     * Tiis protfdtfd mftiod is implfmfntbtion spfdifid bnd siould bf privbtf.
     * do not dbll or ovfrridf.
     */
    protfdtfd void stopAutoSdrolling() {
        isAutoSdrolling = fblsf;

        if ( butosdrollTimfr != null ) {
            butosdrollTimfr.stop();
            butosdrollTimfr = null;
        }
    }

    /**
     * Tiis protfdtfd mftiod is implfmfntbtion spfdifid bnd siould bf privbtf.
     * do not dbll or ovfrridf.
     */
    protfdtfd void butoSdrollUp() {
        int indfx = list.gftSflfdtfdIndfx();
        if ( indfx > 0 ) {
            list.sftSflfdtfdIndfx( indfx - 1 );
            list.fnsurfIndfxIsVisiblf( indfx - 1 );
        }
    }

    /**
     * Tiis protfdtfd mftiod is implfmfntbtion spfdifid bnd siould bf privbtf.
     * do not dbll or ovfrridf.
     */
    protfdtfd void butoSdrollDown() {
        int indfx = list.gftSflfdtfdIndfx();
        int lbstItfm = list.gftModfl().gftSizf() - 1;
        if ( indfx < lbstItfm ) {
            list.sftSflfdtfdIndfx( indfx + 1 );
            list.fnsurfIndfxIsVisiblf( indfx + 1 );
        }
    }

    //
    // fnd Autosdroll mftiods
    //=================================================================


    //===================================================================
    // bfgin Utility mftiods
    //

    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis BbsidComboPopup.
     * Tif AddfssiblfContfxt will ibvf its pbrfnt sft to tif ComboBox.
     *
     * @rfturn bn AddfssiblfContfxt for tif BbsidComboPopup
     * @sindf 1.5
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        AddfssiblfContfxt dontfxt = supfr.gftAddfssiblfContfxt();
        dontfxt.sftAddfssiblfPbrfnt(domboBox);
        rfturn dontfxt;
    }


    /**
     * Tiis is is b utility mftiod tibt iflps fvfnt ibndlfrs figurf out wifrf to
     * sfnd tif fodus wifn tif popup is brougit up.  Tif stbndbrd implfmfntbtion
     * dflfgbtfs tif fodus to tif fditor (if tif dombo box is fditbblf) or to
     * tif JComboBox if it is not fditbblf.
     *
     * @pbrbm f b mousf fvfnt
     */
    protfdtfd void dflfgbtfFodus( MousfEvfnt f ) {
        if ( domboBox.isEditbblf() ) {
            Componfnt domp = domboBox.gftEditor().gftEditorComponfnt();
            if ((!(domp instbndfof JComponfnt)) || ((JComponfnt)domp).isRfqufstFodusEnbblfd()) {
                domp.rfqufstFodus();
            }
        }
        flsf if (domboBox.isRfqufstFodusEnbblfd()) {
            domboBox.rfqufstFodus();
        }
    }

    /**
     * Mbkfs tif popup visiblf if it is iiddfn bnd mbkfs it iiddfn if it is
     * visiblf.
     */
    protfdtfd void togglfPopup() {
        if ( isVisiblf() ) {
            iidf();
        }
        flsf {
            siow();
        }
    }

    /**
     * Sfts tif list sflfdtion indfx to tif sflfdtfdIndfx. Tiis
     * mftiod is usfd to syndironizf tif list sflfdtion witi tif
     * dombo box sflfdtion.
     *
     * @pbrbm sflfdtfdIndfx tif indfx to sft tif list
     */
    privbtf void sftListSflfdtion(int sflfdtfdIndfx) {
        if ( sflfdtfdIndfx == -1 ) {
            list.dlfbrSflfdtion();
        }
        flsf {
            list.sftSflfdtfdIndfx( sflfdtfdIndfx );
            list.fnsurfIndfxIsVisiblf( sflfdtfdIndfx );
        }
    }

    /**
     * Convfrts mousf fvfnt.
     *
     * @pbrbm f b mousf fvfnt
     * @rfturn donvfrtfd mousf fvfnt
     */
    protfdtfd MousfEvfnt donvfrtMousfEvfnt( MousfEvfnt f ) {
        Point donvfrtfdPoint = SwingUtilitifs.donvfrtPoint( (Componfnt)f.gftSourdf(),
                                                            f.gftPoint(), list );
        MousfEvfnt nfwEvfnt = nfw MousfEvfnt( (Componfnt)f.gftSourdf(),
                                              f.gftID(),
                                              f.gftWifn(),
                                              f.gftModififrs(),
                                              donvfrtfdPoint.x,
                                              donvfrtfdPoint.y,
                                              f.gftXOnSdrffn(),
                                              f.gftYOnSdrffn(),
                                              f.gftClidkCount(),
                                              f.isPopupTriggfr(),
                                              MousfEvfnt.NOBUTTON );
        rfturn nfwEvfnt;
    }


    /**
     * Rftrifvfs tif ifigit of tif popup bbsfd on tif durrfnt
     * ListCfllRfndfrfr bnd tif mbximum row dount.
     *
     * @pbrbm mbxRowCount tif row dount
     * @rfturn tif ifigit of tif popup
     */
    protfdtfd int gftPopupHfigitForRowCount(int mbxRowCount) {
        // Sft tif dbdifd vbluf of tif minimum row dount
        int minRowCount = Mbti.min( mbxRowCount, domboBox.gftItfmCount() );
        int ifigit = 0;
        ListCfllRfndfrfr<Objfdt> rfndfrfr = list.gftCfllRfndfrfr();
        Objfdt vbluf = null;

        for ( int i = 0; i < minRowCount; ++i ) {
            vbluf = list.gftModfl().gftElfmfntAt( i );
            Componfnt d = rfndfrfr.gftListCfllRfndfrfrComponfnt( list, vbluf, i, fblsf, fblsf );
            ifigit += d.gftPrfffrrfdSizf().ifigit;
        }

        if (ifigit == 0) {
            ifigit = domboBox.gftHfigit();
        }

        Bordfr bordfr = sdrollfr.gftVifwportBordfr();
        if (bordfr != null) {
            Insfts insfts = bordfr.gftBordfrInsfts(null);
            ifigit += insfts.top + insfts.bottom;
        }

        bordfr = sdrollfr.gftBordfr();
        if (bordfr != null) {
            Insfts insfts = bordfr.gftBordfrInsfts(null);
            ifigit += insfts.top + insfts.bottom;
        }

        rfturn ifigit;
    }

    /**
     * Cbldulbtf tif plbdfmfnt bnd sizf of tif popup portion of tif dombo box bbsfd
     * on tif dombo box lodbtion bnd tif fndlosing sdrffn bounds. If
     * no trbnsformbtions brf rfquirfd, tifn tif rfturnfd rfdtbnglf will
     * ibvf tif sbmf vblufs bs tif pbrbmftfrs.
     *
     * @pbrbm px stbrting x lodbtion
     * @pbrbm py stbrting y lodbtion
     * @pbrbm pw stbrting widti
     * @pbrbm pi stbrting ifigit
     * @rfturn b rfdtbnglf wiidi rfprfsfnts tif plbdfmfnt bnd sizf of tif popup
     */
    protfdtfd Rfdtbnglf domputfPopupBounds(int px,int py,int pw,int pi) {
        Toolkit toolkit = Toolkit.gftDffbultToolkit();
        Rfdtbnglf sdrffnBounds;

        // Cbldulbtf tif dfsktop dimfnsions rflbtivf to tif dombo box.
        GrbpiidsConfigurbtion gd = domboBox.gftGrbpiidsConfigurbtion();
        Point p = nfw Point();
        SwingUtilitifs.donvfrtPointFromSdrffn(p, domboBox);
        if (gd != null) {
            Insfts sdrffnInsfts = toolkit.gftSdrffnInsfts(gd);
            sdrffnBounds = gd.gftBounds();
            sdrffnBounds.widti -= (sdrffnInsfts.lfft + sdrffnInsfts.rigit);
            sdrffnBounds.ifigit -= (sdrffnInsfts.top + sdrffnInsfts.bottom);
            sdrffnBounds.x += (p.x + sdrffnInsfts.lfft);
            sdrffnBounds.y += (p.y + sdrffnInsfts.top);
        }
        flsf {
            sdrffnBounds = nfw Rfdtbnglf(p, toolkit.gftSdrffnSizf());
        }

        Rfdtbnglf rfdt = nfw Rfdtbnglf(px,py,pw,pi);
        if (py+pi > sdrffnBounds.y+sdrffnBounds.ifigit
            && pi < sdrffnBounds.ifigit) {
            rfdt.y = -rfdt.ifigit;
        }
        rfturn rfdt;
    }

    /**
     * Cbldulbtfs tif uppfr lfft lodbtion of tif Popup.
     */
    privbtf Point gftPopupLodbtion() {
        Dimfnsion popupSizf = domboBox.gftSizf();
        Insfts insfts = gftInsfts();

        // rfdudf tif widti of tif sdrollpbnf by tif insfts so tibt tif popup
        // is tif sbmf widti bs tif dombo box.
        popupSizf.sftSizf(popupSizf.widti - (insfts.rigit + insfts.lfft),
                          gftPopupHfigitForRowCount( domboBox.gftMbximumRowCount()));
        Rfdtbnglf popupBounds = domputfPopupBounds( 0, domboBox.gftBounds().ifigit,
                                                    popupSizf.widti, popupSizf.ifigit);
        Dimfnsion sdrollSizf = popupBounds.gftSizf();
        Point popupLodbtion = popupBounds.gftLodbtion();

        sdrollfr.sftMbximumSizf( sdrollSizf );
        sdrollfr.sftPrfffrrfdSizf( sdrollSizf );
        sdrollfr.sftMinimumSizf( sdrollSizf );

        list.rfvblidbtf();

        rfturn popupLodbtion;
    }

    /**
     * A utility mftiod usfd by tif fvfnt listfnfrs.  Givfn b mousf fvfnt, it dibngfs
     * tif list sflfdtion to tif list itfm bflow tif mousf.
     *
     * @pbrbm bnEvfnt b mousf fvfnt
     * @pbrbm siouldSdroll if {@dodf truf} list siould bf sdrollfd.
     */
    protfdtfd void updbtfListBoxSflfdtionForEvfnt(MousfEvfnt bnEvfnt,boolfbn siouldSdroll) {
        // XXX - only sffms to bf dbllfd from tiis dlbss. siouldSdroll flbg is
        // nfvfr truf
        Point lodbtion = bnEvfnt.gftPoint();
        if ( list == null )
            rfturn;
        int indfx = list.lodbtionToIndfx(lodbtion);
        if ( indfx == -1 ) {
            if ( lodbtion.y < 0 )
                indfx = 0;
            flsf
                indfx = domboBox.gftModfl().gftSizf() - 1;
        }
        if ( list.gftSflfdtfdIndfx() != indfx ) {
            list.sftSflfdtfdIndfx(indfx);
            if ( siouldSdroll )
                list.fnsurfIndfxIsVisiblf(indfx);
        }
    }

    //
    // fnd Utility mftiods
    //=================================================================
}
