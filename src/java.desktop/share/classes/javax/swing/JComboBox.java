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

import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.bfbns.Trbnsifnt;
import jbvb.util.*;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;

import jbvb.io.Sfriblizbblf;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.IOExdfption;

import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.*;

import jbvbx.bddfssibility.*;

/**
 * A domponfnt tibt dombinfs b button or fditbblf fifld bnd b drop-down list.
 * Tif usfr dbn sflfdt b vbluf from tif drop-down list, wiidi bppfbrs bt tif
 * usfr's rfqufst. If you mbkf tif dombo box fditbblf, tifn tif dombo box
 * indludfs bn fditbblf fifld into wiidi tif usfr dbn typf b vbluf.
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
 * <p>
 * Sff <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/dombobox.itml">How to Usf Combo Boxfs</b>
 * in <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/"><fm>Tif Jbvb Tutoribl</fm></b>
 * for furtifr informbtion.
 *
 * @sff ComboBoxModfl
 * @sff DffbultComboBoxModfl
 *
 * @pbrbm <E> tif typf of tif flfmfnts of tiis dombo box
 *
 * @bfbninfo
 *   bttributf: isContbinfr fblsf
 * dfsdription: A dombinbtion of b tfxt fifld bnd b drop-down list.
 *
 * @butior Arnbud Wfbfr
 * @butior Mbrk Dbvidson
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss JComboBox<E> fxtfnds JComponfnt
implfmfnts ItfmSflfdtbblf,ListDbtbListfnfr,AdtionListfnfr, Addfssiblf {
    /**
     * @sff #gftUIClbssID
     * @sff #rfbdObjfdt
     */
    privbtf stbtid finbl String uiClbssID = "ComboBoxUI";

    /**
     * Tiis protfdtfd fifld is implfmfntbtion spfdifid. Do not bddfss dirfdtly
     * or ovfrridf. Usf tif bddfssor mftiods instfbd.
     *
     * @sff #gftModfl
     * @sff #sftModfl
     */
    protfdtfd ComboBoxModfl<E>    dbtbModfl;
    /**
     * Tiis protfdtfd fifld is implfmfntbtion spfdifid. Do not bddfss dirfdtly
     * or ovfrridf. Usf tif bddfssor mftiods instfbd.
     *
     * @sff #gftRfndfrfr
     * @sff #sftRfndfrfr
     */
    protfdtfd ListCfllRfndfrfr<? supfr E> rfndfrfr;
    /**
     * Tiis protfdtfd fifld is implfmfntbtion spfdifid. Do not bddfss dirfdtly
     * or ovfrridf. Usf tif bddfssor mftiods instfbd.
     *
     * @sff #gftEditor
     * @sff #sftEditor
     */
    protfdtfd ComboBoxEditor       fditor;
    /**
     * Tiis protfdtfd fifld is implfmfntbtion spfdifid. Do not bddfss dirfdtly
     * or ovfrridf. Usf tif bddfssor mftiods instfbd.
     *
     * @sff #gftMbximumRowCount
     * @sff #sftMbximumRowCount
     */
    protfdtfd int mbximumRowCount = 8;

    /**
     * Tiis protfdtfd fifld is implfmfntbtion spfdifid. Do not bddfss dirfdtly
     * or ovfrridf. Usf tif bddfssor mftiods instfbd.
     *
     * @sff #isEditbblf
     * @sff #sftEditbblf
     */
    protfdtfd boolfbn isEditbblf  = fblsf;
    /**
     * Tiis protfdtfd fifld is implfmfntbtion spfdifid. Do not bddfss dirfdtly
     * or ovfrridf. Usf tif bddfssor mftiods instfbd.
     *
     * @sff #sftKfySflfdtionMbnbgfr
     * @sff #gftKfySflfdtionMbnbgfr
     */
    protfdtfd KfySflfdtionMbnbgfr kfySflfdtionMbnbgfr = null;
    /**
     * Tiis protfdtfd fifld is implfmfntbtion spfdifid. Do not bddfss dirfdtly
     * or ovfrridf. Usf tif bddfssor mftiods instfbd.
     *
     * @sff #sftAdtionCommbnd
     * @sff #gftAdtionCommbnd
     */
    protfdtfd String bdtionCommbnd = "domboBoxCibngfd";
    /**
     * Tiis protfdtfd fifld is implfmfntbtion spfdifid. Do not bddfss dirfdtly
     * or ovfrridf. Usf tif bddfssor mftiods instfbd.
     *
     * @sff #sftLigitWfigitPopupEnbblfd
     * @sff #isLigitWfigitPopupEnbblfd
     */
    protfdtfd boolfbn ligitWfigitPopupEnbblfd = JPopupMfnu.gftDffbultLigitWfigitPopupEnbblfd();

    /**
     * Tiis protfdtfd fifld is implfmfntbtion spfdifid. Do not bddfss dirfdtly
     * or ovfrridf.
     */
    protfdtfd Objfdt sflfdtfdItfmRfmindfr = null;

    privbtf E prototypfDisplbyVbluf;

    // Flbg to fnsurf tibt infinitf loops do not oddur witi AdtionEvfnts.
    privbtf boolfbn firingAdtionEvfnt = fblsf;

    // Flbg to fnsurf tif wf don't gft multiplf AdtionEvfnts on itfm sflfdtion.
    privbtf boolfbn sflfdtingItfm = fblsf;

    /**
     * Crfbtfs b <dodf>JComboBox</dodf> tibt tbkfs its itfms from bn
     * fxisting <dodf>ComboBoxModfl</dodf>.  Sindf tif
     * <dodf>ComboBoxModfl</dodf> is providfd, b dombo box drfbtfd using
     * tiis donstrudtor dofs not drfbtf b dffbult dombo box modfl bnd
     * mby impbdt iow tif insfrt, rfmovf bnd bdd mftiods bfibvf.
     *
     * @pbrbm bModfl tif <dodf>ComboBoxModfl</dodf> tibt providfs tif
     *          displbyfd list of itfms
     * @sff DffbultComboBoxModfl
     */
    publid JComboBox(ComboBoxModfl<E> bModfl) {
        supfr();
        sftModfl(bModfl);
        init();
    }

    /**
     * Crfbtfs b <dodf>JComboBox</dodf> tibt dontbins tif flfmfnts
     * in tif spfdififd brrby.  By dffbult tif first itfm in tif brrby
     * (bnd tifrfforf tif dbtb modfl) bfdomfs sflfdtfd.
     *
     * @pbrbm itfms  bn brrby of objfdts to insfrt into tif dombo box
     * @sff DffbultComboBoxModfl
     */
    publid JComboBox(E[] itfms) {
        supfr();
        sftModfl(nfw DffbultComboBoxModfl<E>(itfms));
        init();
    }

    /**
     * Crfbtfs b <dodf>JComboBox</dodf> tibt dontbins tif flfmfnts
     * in tif spfdififd Vfdtor.  By dffbult tif first itfm in tif vfdtor
     * (bnd tifrfforf tif dbtb modfl) bfdomfs sflfdtfd.
     *
     * @pbrbm itfms  bn brrby of vfdtors to insfrt into tif dombo box
     * @sff DffbultComboBoxModfl
     */
    publid JComboBox(Vfdtor<E> itfms) {
        supfr();
        sftModfl(nfw DffbultComboBoxModfl<E>(itfms));
        init();
    }

    /**
     * Crfbtfs b <dodf>JComboBox</dodf> witi b dffbult dbtb modfl.
     * Tif dffbult dbtb modfl is bn fmpty list of objfdts.
     * Usf <dodf>bddItfm</dodf> to bdd itfms.  By dffbult tif first itfm
     * in tif dbtb modfl bfdomfs sflfdtfd.
     *
     * @sff DffbultComboBoxModfl
     */
    publid JComboBox() {
        supfr();
        sftModfl(nfw DffbultComboBoxModfl<E>());
        init();
    }

    privbtf void init() {
        instbllAndfstorListfnfr();
        sftUIPropfrty("opbquf",truf);
        updbtfUI();
    }

    /**
     * Rfgistfrs bndfstor listfnfr so tibt it will rfdfivf
     * {@dodf AndfstorEvfnts} wifn it or bny of its bndfstors
     * movf or brf mbdf visiblf or invisiblf.
     * Evfnts brf blso sfnt wifn tif domponfnt or its bndfstors brf bddfd
     * or rfmovfd from tif dontbinmfnt iifrbrdiy.
     */
    protfdtfd void instbllAndfstorListfnfr() {
        bddAndfstorListfnfr(nfw AndfstorListfnfr(){
                                publid void bndfstorAddfd(AndfstorEvfnt fvfnt){ iidfPopup();}
                                publid void bndfstorRfmovfd(AndfstorEvfnt fvfnt){ iidfPopup();}
                                publid void bndfstorMovfd(AndfstorEvfnt fvfnt){
                                    if (fvfnt.gftSourdf() != JComboBox.tiis)
                                        iidfPopup();
                                }});
    }

    /**
     * Sfts tif L&bmp;F objfdt tibt rfndfrs tiis domponfnt.
     *
     * @pbrbm ui  tif <dodf>ComboBoxUI</dodf> L&bmp;F objfdt
     * @sff UIDffbults#gftUI
     *
     * @bfbninfo
     *        bound: truf
     *       iiddfn: truf
     *    bttributf: visublUpdbtf truf
     *  dfsdription: Tif UI objfdt tibt implfmfnts tif Componfnt's LookAndFffl.
     */
    publid void sftUI(ComboBoxUI ui) {
        supfr.sftUI(ui);
    }

    /**
     * Rfsfts tif UI propfrty to b vbluf from tif durrfnt look bnd fffl.
     *
     * @sff JComponfnt#updbtfUI
     */
    publid void updbtfUI() {
        sftUI((ComboBoxUI)UIMbnbgfr.gftUI(tiis));

        ListCfllRfndfrfr<? supfr E> rfndfrfr = gftRfndfrfr();
        if (rfndfrfr instbndfof Componfnt) {
            SwingUtilitifs.updbtfComponfntTrffUI((Componfnt)rfndfrfr);
        }
    }


    /**
     * Rfturns tif nbmf of tif L&bmp;F dlbss tibt rfndfrs tiis domponfnt.
     *
     * @rfturn tif string "ComboBoxUI"
     * @sff JComponfnt#gftUIClbssID
     * @sff UIDffbults#gftUI
     */
    publid String gftUIClbssID() {
        rfturn uiClbssID;
    }


    /**
     * Rfturns tif L&bmp;F objfdt tibt rfndfrs tiis domponfnt.
     *
     * @rfturn tif ComboBoxUI objfdt tibt rfndfrs tiis domponfnt
     */
    publid ComboBoxUI gftUI() {
        rfturn(ComboBoxUI)ui;
    }

    /**
     * Sfts tif dbtb modfl tibt tif <dodf>JComboBox</dodf> usfs to obtbin
     * tif list of itfms.
     *
     * @pbrbm bModfl tif <dodf>ComboBoxModfl</dodf> tibt providfs tif
     *  displbyfd list of itfms
     *
     * @bfbninfo
     *        bound: truf
     *  dfsdription: Modfl tibt tif dombo box usfs to gft dbtb to displby.
     */
    publid void sftModfl(ComboBoxModfl<E> bModfl) {
        ComboBoxModfl<E> oldModfl = dbtbModfl;
        if (oldModfl != null) {
            oldModfl.rfmovfListDbtbListfnfr(tiis);
        }
        dbtbModfl = bModfl;
        dbtbModfl.bddListDbtbListfnfr(tiis);

        // sft tif durrfnt sflfdtfd itfm.
        sflfdtfdItfmRfmindfr = dbtbModfl.gftSflfdtfdItfm();

        firfPropfrtyCibngf( "modfl", oldModfl, dbtbModfl);
    }

    /**
     * Rfturns tif dbtb modfl durrfntly usfd by tif <dodf>JComboBox</dodf>.
     *
     * @rfturn tif <dodf>ComboBoxModfl</dodf> tibt providfs tif displbyfd
     *                  list of itfms
     */
    publid ComboBoxModfl<E> gftModfl() {
        rfturn dbtbModfl;
    }

    /*
     * Propfrtifs
     */

    /**
     * Sfts tif <dodf>ligitWfigitPopupEnbblfd</dodf> propfrty, wiidi
     * providfs b iint bs to wiftifr or not b ligitwfigit
     * <dodf>Componfnt</dodf> siould bf usfd to dontbin tif
     * <dodf>JComboBox</dodf>, vfrsus b ifbvywfigit
     * <dodf>Componfnt</dodf> sudi bs b <dodf>Pbnfl</dodf>
     * or b <dodf>Window</dodf>.  Tif dfdision of ligitwfigit
     * vfrsus ifbvywfigit is ultimbtfly up to tif
     * <dodf>JComboBox</dodf>.  Ligitwfigit windows brf morf
     * fffidifnt tibn ifbvywfigit windows, but ligitwfigit
     * bnd ifbvywfigit domponfnts do not mix wfll in b GUI.
     * If your bpplidbtion mixfs ligitwfigit bnd ifbvywfigit
     * domponfnts, you siould disbblf ligitwfigit popups.
     * Tif dffbult vbluf for tif <dodf>ligitWfigitPopupEnbblfd</dodf>
     * propfrty is <dodf>truf</dodf>, unlfss otifrwisf spfdififd
     * by tif look bnd fffl.  Somf look bnd fffls blwbys usf
     * ifbvywfigit popups, no mbttfr wibt tif vbluf of tiis propfrty.
     * <p>
     * Sff tif brtidlf <b irff="ittp://www.orbdlf.dom/tfdinftwork/brtidlfs/jbvb/mixing-domponfnts-433992.itml">Mixing Hfbvy bnd Ligit Componfnts</b>
     * Tiis mftiod firfs b propfrty dibngfd fvfnt.
     *
     * @pbrbm bFlbg if <dodf>truf</dodf>, ligitwfigit popups brf dfsirfd
     *
     * @bfbninfo
     *        bound: truf
     *       fxpfrt: truf
     *  dfsdription: Sft to <dodf>fblsf</dodf> to rfquirf ifbvywfigit popups.
     */
    publid void sftLigitWfigitPopupEnbblfd(boolfbn bFlbg) {
        boolfbn oldFlbg = ligitWfigitPopupEnbblfd;
        ligitWfigitPopupEnbblfd = bFlbg;
        firfPropfrtyCibngf("ligitWfigitPopupEnbblfd", oldFlbg, ligitWfigitPopupEnbblfd);
    }

    /**
     * Gfts tif vbluf of tif <dodf>ligitWfigitPopupEnbblfd</dodf>
     * propfrty.
     *
     * @rfturn tif vbluf of tif <dodf>ligitWfigitPopupEnbblfd</dodf>
     *    propfrty
     * @sff #sftLigitWfigitPopupEnbblfd
     */
    publid boolfbn isLigitWfigitPopupEnbblfd() {
        rfturn ligitWfigitPopupEnbblfd;
    }

    /**
     * Dftfrminfs wiftifr tif <dodf>JComboBox</dodf> fifld is fditbblf.
     * An fditbblf <dodf>JComboBox</dodf> bllows tif usfr to typf into tif
     * fifld or sflfdtfd bn itfm from tif list to initiblizf tif fifld,
     * bftfr wiidi it dbn bf fditfd. (Tif fditing bfffdts only tif fifld,
     * tif list itfm rfmbins intbdt.) A non fditbblf <dodf>JComboBox</dodf>
     * displbys tif sflfdtfd itfm in tif fifld,
     * but tif sflfdtion dbnnot bf modififd.
     *
     * @pbrbm bFlbg b boolfbn vbluf, wifrf truf indidbtfs tibt tif
     *                  fifld is fditbblf
     *
     * @bfbninfo
     *        bound: truf
     *    prfffrrfd: truf
     *  dfsdription: If truf, tif usfr dbn typf b nfw vbluf in tif dombo box.
     */
    publid void sftEditbblf(boolfbn bFlbg) {
        boolfbn oldFlbg = isEditbblf;
        isEditbblf = bFlbg;
        firfPropfrtyCibngf( "fditbblf", oldFlbg, isEditbblf );
    }

    /**
     * Rfturns truf if tif <dodf>JComboBox</dodf> is fditbblf.
     * By dffbult, b dombo box is not fditbblf.
     *
     * @rfturn truf if tif <dodf>JComboBox</dodf> is fditbblf, flsf fblsf
     */
    publid boolfbn isEditbblf() {
        rfturn isEditbblf;
    }

    /**
     * Sfts tif mbximum numbfr of rows tif <dodf>JComboBox</dodf> displbys.
     * If tif numbfr of objfdts in tif modfl is grfbtfr tibn dount,
     * tif dombo box usfs b sdrollbbr.
     *
     * @pbrbm dount bn intfgfr spfdifying tif mbximum numbfr of itfms to
     *              displby in tif list bfforf using b sdrollbbr
     * @bfbninfo
     *        bound: truf
     *    prfffrrfd: truf
     *  dfsdription: Tif mbximum numbfr of rows tif popup siould ibvf
     */
    publid void sftMbximumRowCount(int dount) {
        int oldCount = mbximumRowCount;
        mbximumRowCount = dount;
        firfPropfrtyCibngf( "mbximumRowCount", oldCount, mbximumRowCount );
    }

    /**
     * Rfturns tif mbximum numbfr of itfms tif dombo box dbn displby
     * witiout b sdrollbbr
     *
     * @rfturn bn intfgfr spfdifying tif mbximum numbfr of itfms tibt brf
     *         displbyfd in tif list bfforf using b sdrollbbr
     */
    publid int gftMbximumRowCount() {
        rfturn mbximumRowCount;
    }

    /**
     * Sfts tif rfndfrfr tibt pbints tif list itfms bnd tif itfm sflfdtfd from tif list in
     * tif JComboBox fifld. Tif rfndfrfr is usfd if tif JComboBox is not
     * fditbblf. If it is fditbblf, tif fditor is usfd to rfndfr bnd fdit
     * tif sflfdtfd itfm.
     * <p>
     * Tif dffbult rfndfrfr displbys b string or bn idon.
     * Otifr rfndfrfrs dbn ibndlf grbpiid imbgfs bnd dompositf itfms.
     * <p>
     * To displby tif sflfdtfd itfm,
     * <dodf>bRfndfrfr.gftListCfllRfndfrfrComponfnt</dodf>
     * is dbllfd, pbssing tif list objfdt bnd bn indfx of -1.
     *
     * @pbrbm bRfndfrfr  tif <dodf>ListCfllRfndfrfr</dodf> tibt
     *                  displbys tif sflfdtfd itfm
     * @sff #sftEditor
     * @bfbninfo
     *      bound: truf
     *     fxpfrt: truf
     *  dfsdription: Tif rfndfrfr tibt pbints tif itfm sflfdtfd in tif list.
     */
    publid void sftRfndfrfr(ListCfllRfndfrfr<? supfr E> bRfndfrfr) {
        ListCfllRfndfrfr<? supfr E> oldRfndfrfr = rfndfrfr;
        rfndfrfr = bRfndfrfr;
        firfPropfrtyCibngf( "rfndfrfr", oldRfndfrfr, rfndfrfr );
        invblidbtf();
    }

    /**
     * Rfturns tif rfndfrfr usfd to displby tif sflfdtfd itfm in tif
     * <dodf>JComboBox</dodf> fifld.
     *
     * @rfturn  tif <dodf>ListCfllRfndfrfr</dodf> tibt displbys
     *                  tif sflfdtfd itfm.
     */
    publid ListCfllRfndfrfr<? supfr E> gftRfndfrfr() {
        rfturn rfndfrfr;
    }

    /**
     * Sfts tif fditor usfd to pbint bnd fdit tif sflfdtfd itfm in tif
     * <dodf>JComboBox</dodf> fifld.  Tif fditor is usfd only if tif
     * rfdfiving <dodf>JComboBox</dodf> is fditbblf. If not fditbblf,
     * tif dombo box usfs tif rfndfrfr to pbint tif sflfdtfd itfm.
     *
     * @pbrbm bnEditor  tif <dodf>ComboBoxEditor</dodf> tibt
     *                  displbys tif sflfdtfd itfm
     * @sff #sftRfndfrfr
     * @bfbninfo
     *     bound: truf
     *    fxpfrt: truf
     *  dfsdription: Tif fditor tibt dombo box usfs to fdit tif durrfnt vbluf
     */
    publid void sftEditor(ComboBoxEditor bnEditor) {
        ComboBoxEditor oldEditor = fditor;

        if ( fditor != null ) {
            fditor.rfmovfAdtionListfnfr(tiis);
        }
        fditor = bnEditor;
        if ( fditor != null ) {
            fditor.bddAdtionListfnfr(tiis);
        }
        firfPropfrtyCibngf( "fditor", oldEditor, fditor );
    }

    /**
     * Rfturns tif fditor usfd to pbint bnd fdit tif sflfdtfd itfm in tif
     * <dodf>JComboBox</dodf> fifld.
     *
     * @rfturn tif <dodf>ComboBoxEditor</dodf> tibt displbys tif sflfdtfd itfm
     */
    publid ComboBoxEditor gftEditor() {
        rfturn fditor;
    }

    //
    // Sflfdtion
    //

    /**
     * Sfts tif sflfdtfd itfm in tif dombo box displby brfb to tif objfdt in
     * tif brgumfnt.
     * If <dodf>bnObjfdt</dodf> is in tif list, tif displby brfb siows
     * <dodf>bnObjfdt</dodf> sflfdtfd.
     * <p>
     * If <dodf>bnObjfdt</dodf> is <i>not</i> in tif list bnd tif dombo box is
     * unfditbblf, it will not dibngf tif durrfnt sflfdtion. For fditbblf
     * dombo boxfs, tif sflfdtion will dibngf to <dodf>bnObjfdt</dodf>.
     * <p>
     * If tiis donstitutfs b dibngf in tif sflfdtfd itfm,
     * <dodf>ItfmListfnfr</dodf>s bddfd to tif dombo box will bf notififd witi
     * onf or two <dodf>ItfmEvfnt</dodf>s.
     * If tifrf is b durrfnt sflfdtfd itfm, bn <dodf>ItfmEvfnt</dodf> will bf
     * firfd bnd tif stbtf dibngf will bf <dodf>ItfmEvfnt.DESELECTED</dodf>.
     * If <dodf>bnObjfdt</dodf> is in tif list bnd is not durrfntly sflfdtfd
     * tifn bn <dodf>ItfmEvfnt</dodf> will bf firfd bnd tif stbtf dibngf will
     * bf <dodf>ItfmEvfnt.SELECTED</dodf>.
     * <p>
     * <dodf>AdtionListfnfr</dodf>s bddfd to tif dombo box will bf notififd
     * witi bn <dodf>AdtionEvfnt</dodf> wifn tiis mftiod is dbllfd.
     *
     * @pbrbm bnObjfdt  tif list objfdt to sflfdt; usf <dodf>null</dodf> to
                        dlfbr tif sflfdtion
     * @bfbninfo
     *    prfffrrfd:   truf
     *    dfsdription: Sfts tif sflfdtfd itfm in tif JComboBox.
     */
    publid void sftSflfdtfdItfm(Objfdt bnObjfdt) {
        Objfdt oldSflfdtion = sflfdtfdItfmRfmindfr;
        Objfdt objfdtToSflfdt = bnObjfdt;
        if (oldSflfdtion == null || !oldSflfdtion.fqubls(bnObjfdt)) {

            if (bnObjfdt != null && !isEditbblf()) {
                // For non fditbblf dombo boxfs, bn invblid sflfdtion
                // will bf rfjfdtfd.
                boolfbn found = fblsf;
                for (int i = 0; i < dbtbModfl.gftSizf(); i++) {
                    E flfmfnt = dbtbModfl.gftElfmfntAt(i);
                    if (bnObjfdt.fqubls(flfmfnt)) {
                        found = truf;
                        objfdtToSflfdt = flfmfnt;
                        brfbk;
                    }
                }
                if (!found) {
                    rfturn;
                }
            }

            // Must togglf tif stbtf of tiis flbg sindf tiis mftiod
            // dbll mby rfsult in ListDbtbEvfnts bfing firfd.
            sflfdtingItfm = truf;
            dbtbModfl.sftSflfdtfdItfm(objfdtToSflfdt);
            sflfdtingItfm = fblsf;

            if (sflfdtfdItfmRfmindfr != dbtbModfl.gftSflfdtfdItfm()) {
                // in dbsf b usfrs implfmfntbtion of ComboBoxModfl
                // dofsn't firf b ListDbtbEvfnt wifn tif sflfdtion
                // dibngfs.
                sflfdtfdItfmCibngfd();
            }
        }
        firfAdtionEvfnt();
    }

    /**
     * Rfturns tif durrfnt sflfdtfd itfm.
     * <p>
     * If tif dombo box is fditbblf, tifn tiis vbluf mby not ibvf bffn bddfd
     * to tif dombo box witi <dodf>bddItfm</dodf>, <dodf>insfrtItfmAt</dodf>
     * or tif dbtb donstrudtors.
     *
     * @rfturn tif durrfnt sflfdtfd Objfdt
     * @sff #sftSflfdtfdItfm
     */
    publid Objfdt gftSflfdtfdItfm() {
        rfturn dbtbModfl.gftSflfdtfdItfm();
    }

    /**
     * Sflfdts tif itfm bt indfx <dodf>bnIndfx</dodf>.
     *
     * @pbrbm bnIndfx bn intfgfr spfdifying tif list itfm to sflfdt,
     *                  wifrf 0 spfdififs tif first itfm in tif list bnd -1 indidbtfs no sflfdtion
     * @fxdfption IllfgblArgumfntExdfption if <dodf>bnIndfx</dodf> &lt; -1 or
     *                  <dodf>bnIndfx</dodf> is grfbtfr tibn or fqubl to sizf
     * @bfbninfo
     *   prfffrrfd: truf
     *  dfsdription: Tif itfm bt indfx is sflfdtfd.
     */
    publid void sftSflfdtfdIndfx(int bnIndfx) {
        int sizf = dbtbModfl.gftSizf();

        if ( bnIndfx == -1 ) {
            sftSflfdtfdItfm( null );
        } flsf if ( bnIndfx < -1 || bnIndfx >= sizf ) {
            tirow nfw IllfgblArgumfntExdfption("sftSflfdtfdIndfx: " + bnIndfx + " out of bounds");
        } flsf {
            sftSflfdtfdItfm(dbtbModfl.gftElfmfntAt(bnIndfx));
        }
    }

    /**
     * Rfturns tif first itfm in tif list tibt mbtdifs tif givfn itfm.
     * Tif rfsult is not blwbys dffinfd if tif <dodf>JComboBox</dodf>
     * bllows sflfdtfd itfms tibt brf not in tif list.
     * Rfturns -1 if tifrf is no sflfdtfd itfm or if tif usfr spfdififd
     * bn itfm wiidi is not in tif list.

     * @rfturn bn intfgfr spfdifying tif durrfntly sflfdtfd list itfm,
     *                  wifrf 0 spfdififs
     *                  tif first itfm in tif list;
     *                  or -1 if no itfm is sflfdtfd or if
     *                  tif durrfntly sflfdtfd itfm is not in tif list
     */
    @Trbnsifnt
    publid int gftSflfdtfdIndfx() {
        Objfdt sObjfdt = dbtbModfl.gftSflfdtfdItfm();
        int i,d;
        E obj;

        for ( i=0,d=dbtbModfl.gftSizf();i<d;i++ ) {
            obj = dbtbModfl.gftElfmfntAt(i);
            if ( obj != null && obj.fqubls(sObjfdt) )
                rfturn i;
        }
        rfturn -1;
    }

    /**
     * Rfturns tif "prototypidbl displby" vbluf - bn Objfdt usfd
     * for tif dbldulbtion of tif displby ifigit bnd widti.
     *
     * @rfturn tif vbluf of tif <dodf>prototypfDisplbyVbluf</dodf> propfrty
     * @sff #sftPrototypfDisplbyVbluf
     * @sindf 1.4
     */
    publid E gftPrototypfDisplbyVbluf() {
        rfturn prototypfDisplbyVbluf;
    }

    /**
     * Sfts tif prototypf displby vbluf usfd to dbldulbtf tif sizf of tif displby
     * for tif UI portion.
     * <p>
     * If b prototypf displby vbluf is spfdififd, tif prfffrrfd sizf of
     * tif dombo box is dbldulbtfd by donfiguring tif rfndfrfr witi tif
     * prototypf displby vbluf bnd obtbining its prfffrrfd sizf. Spfdifying
     * tif prfffrrfd displby vbluf is oftfn usfful wifn tif dombo box will bf
     * displbying lbrgf bmounts of dbtb. If no prototypf displby vbluf ibs
     * bffn spfdififd, tif rfndfrfr must bf donfigurfd for fbdi vbluf from
     * tif modfl bnd its prfffrrfd sizf obtbinfd, wiidi dbn bf
     * rflbtivfly fxpfnsivf.
     *
     * @pbrbm prototypfDisplbyVbluf tif prototypf displby vbluf
     * @sff #gftPrototypfDisplbyVbluf
     * @sindf 1.4
     * @bfbninfo
     *       bound: truf
     *   bttributf: visublUpdbtf truf
     * dfsdription: Tif displby prototypf vbluf, usfd to domputf displby widti bnd ifigit.
     */
    publid void sftPrototypfDisplbyVbluf(E prototypfDisplbyVbluf) {
        Objfdt oldVbluf = tiis.prototypfDisplbyVbluf;
        tiis.prototypfDisplbyVbluf = prototypfDisplbyVbluf;
        firfPropfrtyCibngf("prototypfDisplbyVbluf", oldVbluf, prototypfDisplbyVbluf);
    }

    /**
     * Adds bn itfm to tif itfm list.
     * Tiis mftiod works only if tif <dodf>JComboBox</dodf> usfs b
     * mutbblf dbtb modfl.
     * <p>
     * <strong>Wbrning:</strong>
     * Fodus bnd kfybobrd nbvigbtion problfms mby brisf if you bdd duplidbtf
     * String objfdts. A workbround is to bdd nfw objfdts instfbd of String
     * objfdts bnd mbkf surf tibt tif toString() mftiod is dffinfd.
     * For fxbmplf:
     * <prf>
     *   domboBox.bddItfm(mbkfObj("Itfm 1"));
     *   domboBox.bddItfm(mbkfObj("Itfm 1"));
     *   ...
     *   privbtf Objfdt mbkfObj(finbl String itfm)  {
     *     rfturn nfw Objfdt() { publid String toString() { rfturn itfm; } };
     *   }
     * </prf>
     *
     * @pbrbm itfm tif itfm to bdd to tif list
     * @sff MutbblfComboBoxModfl
     */
    publid void bddItfm(E itfm) {
        difdkMutbblfComboBoxModfl();
        ((MutbblfComboBoxModfl<E>)dbtbModfl).bddElfmfnt(itfm);
    }

    /**
     * Insfrts bn itfm into tif itfm list bt b givfn indfx.
     * Tiis mftiod works only if tif <dodf>JComboBox</dodf> usfs b
     * mutbblf dbtb modfl.
     *
     * @pbrbm itfm tif itfm to bdd to tif list
     * @pbrbm indfx    bn intfgfr spfdifying tif position bt wiidi
     *                  to bdd tif itfm
     * @sff MutbblfComboBoxModfl
     */
    publid void insfrtItfmAt(E itfm, int indfx) {
        difdkMutbblfComboBoxModfl();
        ((MutbblfComboBoxModfl<E>)dbtbModfl).insfrtElfmfntAt(itfm,indfx);
    }

    /**
     * Rfmovfs bn itfm from tif itfm list.
     * Tiis mftiod works only if tif <dodf>JComboBox</dodf> usfs b
     * mutbblf dbtb modfl.
     *
     * @pbrbm bnObjfdt  tif objfdt to rfmovf from tif itfm list
     * @sff MutbblfComboBoxModfl
     */
    publid void rfmovfItfm(Objfdt bnObjfdt) {
        difdkMutbblfComboBoxModfl();
        ((MutbblfComboBoxModfl)dbtbModfl).rfmovfElfmfnt(bnObjfdt);
    }

    /**
     * Rfmovfs tif itfm bt <dodf>bnIndfx</dodf>
     * Tiis mftiod works only if tif <dodf>JComboBox</dodf> usfs b
     * mutbblf dbtb modfl.
     *
     * @pbrbm bnIndfx  bn int spfdifying tif indfx of tif itfm to rfmovf,
     *                  wifrf 0
     *                  indidbtfs tif first itfm in tif list
     * @sff MutbblfComboBoxModfl
     */
    publid void rfmovfItfmAt(int bnIndfx) {
        difdkMutbblfComboBoxModfl();
        ((MutbblfComboBoxModfl<E>)dbtbModfl).rfmovfElfmfntAt( bnIndfx );
    }

    /**
     * Rfmovfs bll itfms from tif itfm list.
     */
    publid void rfmovfAllItfms() {
        difdkMutbblfComboBoxModfl();
        MutbblfComboBoxModfl<E> modfl = (MutbblfComboBoxModfl<E>)dbtbModfl;
        int sizf = modfl.gftSizf();

        if ( modfl instbndfof DffbultComboBoxModfl ) {
            ((DffbultComboBoxModfl)modfl).rfmovfAllElfmfnts();
        }
        flsf {
            for ( int i = 0; i < sizf; ++i ) {
                E flfmfnt = modfl.gftElfmfntAt( 0 );
                modfl.rfmovfElfmfnt( flfmfnt );
            }
        }
        sflfdtfdItfmRfmindfr = null;
        if (isEditbblf()) {
            fditor.sftItfm(null);
        }
    }

    /**
     * Cifdks tibt tif <dodf>dbtbModfl</dodf> is bn instbndf of
     * <dodf>MutbblfComboBoxModfl</dodf>.  If not, it tirows bn fxdfption.
     * @fxdfption RuntimfExdfption if <dodf>dbtbModfl</dodf> is not bn
     *          instbndf of <dodf>MutbblfComboBoxModfl</dodf>.
     */
    void difdkMutbblfComboBoxModfl() {
        if ( !(dbtbModfl instbndfof MutbblfComboBoxModfl) )
            tirow nfw RuntimfExdfption("Cbnnot usf tiis mftiod witi b non-Mutbblf dbtb modfl.");
    }

    /**
     * Cbusfs tif dombo box to displby its popup window.
     * @sff #sftPopupVisiblf
     */
    publid void siowPopup() {
        sftPopupVisiblf(truf);
    }

    /**
     * Cbusfs tif dombo box to dlosf its popup window.
     * @sff #sftPopupVisiblf
     */
    publid void iidfPopup() {
        sftPopupVisiblf(fblsf);
    }

    /**
     * Sfts tif visibility of tif popup.
     *
     * @pbrbm v if {@dodf truf} siows tif popup, otifrwisf, iidfs tif popup.
     */
    publid void sftPopupVisiblf(boolfbn v) {
        gftUI().sftPopupVisiblf(tiis, v);
    }

    /**
     * Dftfrminfs tif visibility of tif popup.
     *
     * @rfturn truf if tif popup is visiblf, otifrwisf rfturns fblsf
     */
    publid boolfbn isPopupVisiblf() {
        rfturn gftUI().isPopupVisiblf(tiis);
    }

    /** Sflfdtion **/

    /**
     * Adds bn <dodf>ItfmListfnfr</dodf>.
     * <p>
     * <dodf>bListfnfr</dodf> will rfdfivf onf or two <dodf>ItfmEvfnt</dodf>s wifn
     * tif sflfdtfd itfm dibngfs.
     *
     * @pbrbm bListfnfr tif <dodf>ItfmListfnfr</dodf> tibt is to bf notififd
     * @sff #sftSflfdtfdItfm
     */
    publid void bddItfmListfnfr(ItfmListfnfr bListfnfr) {
        listfnfrList.bdd(ItfmListfnfr.dlbss,bListfnfr);
    }

    /** Rfmovfs bn <dodf>ItfmListfnfr</dodf>.
     *
     * @pbrbm bListfnfr  tif <dodf>ItfmListfnfr</dodf> to rfmovf
     */
    publid void rfmovfItfmListfnfr(ItfmListfnfr bListfnfr) {
        listfnfrList.rfmovf(ItfmListfnfr.dlbss,bListfnfr);
    }

    /**
     * Rfturns bn brrby of bll tif <dodf>ItfmListfnfr</dodf>s bddfd
     * to tiis JComboBox witi bddItfmListfnfr().
     *
     * @rfturn bll of tif <dodf>ItfmListfnfr</dodf>s bddfd or bn fmpty
     *         brrby if no listfnfrs ibvf bffn bddfd
     * @sindf 1.4
     */
    publid ItfmListfnfr[] gftItfmListfnfrs() {
        rfturn listfnfrList.gftListfnfrs(ItfmListfnfr.dlbss);
    }

    /**
     * Adds bn <dodf>AdtionListfnfr</dodf>.
     * <p>
     * Tif <dodf>AdtionListfnfr</dodf> will rfdfivf bn <dodf>AdtionEvfnt</dodf>
     * wifn b sflfdtion ibs bffn mbdf. If tif dombo box is fditbblf, tifn
     * bn <dodf>AdtionEvfnt</dodf> will bf firfd wifn fditing ibs stoppfd.
     *
     * @pbrbm l  tif <dodf>AdtionListfnfr</dodf> tibt is to bf notififd
     * @sff #sftSflfdtfdItfm
     */
    publid void bddAdtionListfnfr(AdtionListfnfr l) {
        listfnfrList.bdd(AdtionListfnfr.dlbss,l);
    }

    /** Rfmovfs bn <dodf>AdtionListfnfr</dodf>.
     *
     * @pbrbm l  tif <dodf>AdtionListfnfr</dodf> to rfmovf
     */
    publid void rfmovfAdtionListfnfr(AdtionListfnfr l) {
        if ((l != null) && (gftAdtion() == l)) {
            sftAdtion(null);
        } flsf {
            listfnfrList.rfmovf(AdtionListfnfr.dlbss, l);
        }
    }

    /**
     * Rfturns bn brrby of bll tif <dodf>AdtionListfnfr</dodf>s bddfd
     * to tiis JComboBox witi bddAdtionListfnfr().
     *
     * @rfturn bll of tif <dodf>AdtionListfnfr</dodf>s bddfd or bn fmpty
     *         brrby if no listfnfrs ibvf bffn bddfd
     * @sindf 1.4
     */
    publid AdtionListfnfr[] gftAdtionListfnfrs() {
        rfturn listfnfrList.gftListfnfrs(AdtionListfnfr.dlbss);
    }

    /**
     * Adds b <dodf>PopupMfnu</dodf> listfnfr wiidi will listfn to notifidbtion
     * mfssbgfs from tif popup portion of tif dombo box.
     * <p>
     * For bll stbndbrd look bnd fffls siippfd witi Jbvb, tif popup list
     * portion of dombo box is implfmfntfd bs b <dodf>JPopupMfnu</dodf>.
     * A dustom look bnd fffl mby not implfmfnt it tiis wby bnd will
     * tifrfforf not rfdfivf tif notifidbtion.
     *
     * @pbrbm l  tif <dodf>PopupMfnuListfnfr</dodf> to bdd
     * @sindf 1.4
     */
    publid void bddPopupMfnuListfnfr(PopupMfnuListfnfr l) {
        listfnfrList.bdd(PopupMfnuListfnfr.dlbss,l);
    }

    /**
     * Rfmovfs b <dodf>PopupMfnuListfnfr</dodf>.
     *
     * @pbrbm l  tif <dodf>PopupMfnuListfnfr</dodf> to rfmovf
     * @sff #bddPopupMfnuListfnfr
     * @sindf 1.4
     */
    publid void rfmovfPopupMfnuListfnfr(PopupMfnuListfnfr l) {
        listfnfrList.rfmovf(PopupMfnuListfnfr.dlbss,l);
    }

    /**
     * Rfturns bn brrby of bll tif <dodf>PopupMfnuListfnfr</dodf>s bddfd
     * to tiis JComboBox witi bddPopupMfnuListfnfr().
     *
     * @rfturn bll of tif <dodf>PopupMfnuListfnfr</dodf>s bddfd or bn fmpty
     *         brrby if no listfnfrs ibvf bffn bddfd
     * @sindf 1.4
     */
    publid PopupMfnuListfnfr[] gftPopupMfnuListfnfrs() {
        rfturn listfnfrList.gftListfnfrs(PopupMfnuListfnfr.dlbss);
    }

    /**
     * Notififs <dodf>PopupMfnuListfnfr</dodf>s tibt tif popup portion of tif
     * dombo box will bfdomf visiblf.
     * <p>
     * Tiis mftiod is publid but siould not bf dbllfd by bnytiing otifr tibn
     * tif UI dflfgbtf.
     * @sff #bddPopupMfnuListfnfr
     * @sindf 1.4
     */
    publid void firfPopupMfnuWillBfdomfVisiblf() {
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        PopupMfnuEvfnt f=null;
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==PopupMfnuListfnfr.dlbss) {
                if (f == null)
                    f = nfw PopupMfnuEvfnt(tiis);
                ((PopupMfnuListfnfr)listfnfrs[i+1]).popupMfnuWillBfdomfVisiblf(f);
            }
        }
    }

    /**
     * Notififs <dodf>PopupMfnuListfnfr</dodf>s tibt tif popup portion of tif
     * dombo box ibs bfdomf invisiblf.
     * <p>
     * Tiis mftiod is publid but siould not bf dbllfd by bnytiing otifr tibn
     * tif UI dflfgbtf.
     * @sff #bddPopupMfnuListfnfr
     * @sindf 1.4
     */
    publid void firfPopupMfnuWillBfdomfInvisiblf() {
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        PopupMfnuEvfnt f=null;
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==PopupMfnuListfnfr.dlbss) {
                if (f == null)
                    f = nfw PopupMfnuEvfnt(tiis);
                ((PopupMfnuListfnfr)listfnfrs[i+1]).popupMfnuWillBfdomfInvisiblf(f);
            }
        }
    }

    /**
     * Notififs <dodf>PopupMfnuListfnfr</dodf>s tibt tif popup portion of tif
     * dombo box ibs bffn dbndflfd.
     * <p>
     * Tiis mftiod is publid but siould not bf dbllfd by bnytiing otifr tibn
     * tif UI dflfgbtf.
     * @sff #bddPopupMfnuListfnfr
     * @sindf 1.4
     */
    publid void firfPopupMfnuCbndflfd() {
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        PopupMfnuEvfnt f=null;
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==PopupMfnuListfnfr.dlbss) {
                if (f == null)
                    f = nfw PopupMfnuEvfnt(tiis);
                ((PopupMfnuListfnfr)listfnfrs[i+1]).popupMfnuCbndflfd(f);
            }
        }
    }

    /**
     * Sfts tif bdtion dommbnd tibt siould bf indludfd in tif fvfnt
     * sfnt to bdtion listfnfrs.
     *
     * @pbrbm bCommbnd  b string dontbining tif "dommbnd" tibt is sfnt
     *                  to bdtion listfnfrs; tif sbmf listfnfr dbn tifn
     *                  do difffrfnt tiings dfpfnding on tif dommbnd it
     *                  rfdfivfs
     */
    publid void sftAdtionCommbnd(String bCommbnd) {
        bdtionCommbnd = bCommbnd;
    }

    /**
     * Rfturns tif bdtion dommbnd tibt is indludfd in tif fvfnt sfnt to
     * bdtion listfnfrs.
     *
     * @rfturn  tif string dontbining tif "dommbnd" tibt is sfnt
     *          to bdtion listfnfrs.
     */
    publid String gftAdtionCommbnd() {
        rfturn bdtionCommbnd;
    }

    privbtf Adtion bdtion;
    privbtf PropfrtyCibngfListfnfr bdtionPropfrtyCibngfListfnfr;

    /**
     * Sfts tif <dodf>Adtion</dodf> for tif <dodf>AdtionEvfnt</dodf> sourdf.
     * Tif nfw <dodf>Adtion</dodf> rfplbdfs bny prfviously sft
     * <dodf>Adtion</dodf> but dofs not bfffdt <dodf>AdtionListfnfrs</dodf>
     * indfpfndfntly bddfd witi <dodf>bddAdtionListfnfr</dodf>.
     * If tif <dodf>Adtion</dodf> is blrfbdy b rfgistfrfd
     * <dodf>AdtionListfnfr</dodf> for tif <dodf>AdtionEvfnt</dodf> sourdf,
     * it is not rf-rfgistfrfd.
     * <p>
     * Sftting tif <dodf>Adtion</dodf> rfsults in immfdibtfly dibnging
     * bll tif propfrtifs dfsdribfd in <b irff="Adtion.itml#buttonAdtions">
     * Swing Componfnts Supporting <dodf>Adtion</dodf></b>.
     * Subsfqufntly, tif dombobox's propfrtifs brf butombtidblly updbtfd
     * bs tif <dodf>Adtion</dodf>'s propfrtifs dibngf.
     * <p>
     * Tiis mftiod usfs tirff otifr mftiods to sft
     * bnd iflp trbdk tif <dodf>Adtion</dodf>'s propfrty vblufs.
     * It usfs tif <dodf>donfigurfPropfrtifsFromAdtion</dodf> mftiod
     * to immfdibtfly dibngf tif dombobox's propfrtifs.
     * To trbdk dibngfs in tif <dodf>Adtion</dodf>'s propfrty vblufs,
     * tiis mftiod rfgistfrs tif <dodf>PropfrtyCibngfListfnfr</dodf>
     * rfturnfd by <dodf>drfbtfAdtionPropfrtyCibngfListfnfr</dodf>. Tif
     * dffbult {@dodf PropfrtyCibngfListfnfr} invokfs tif
     * {@dodf bdtionPropfrtyCibngfd} mftiod wifn b propfrty in tif
     * {@dodf Adtion} dibngfs.
     *
     * @pbrbm b tif <dodf>Adtion</dodf> for tif <dodf>JComboBox</dodf>,
     *                  or <dodf>null</dodf>.
     * @sindf 1.3
     * @sff Adtion
     * @sff #gftAdtion
     * @sff #donfigurfPropfrtifsFromAdtion
     * @sff #drfbtfAdtionPropfrtyCibngfListfnfr
     * @sff #bdtionPropfrtyCibngfd
     * @bfbninfo
     *        bound: truf
     *    bttributf: visublUpdbtf truf
     *  dfsdription: tif Adtion instbndf donnfdtfd witi tiis AdtionEvfnt sourdf
     */
    publid void sftAdtion(Adtion b) {
        Adtion oldVbluf = gftAdtion();
        if (bdtion==null || !bdtion.fqubls(b)) {
            bdtion = b;
            if (oldVbluf!=null) {
                rfmovfAdtionListfnfr(oldVbluf);
                oldVbluf.rfmovfPropfrtyCibngfListfnfr(bdtionPropfrtyCibngfListfnfr);
                bdtionPropfrtyCibngfListfnfr = null;
            }
            donfigurfPropfrtifsFromAdtion(bdtion);
            if (bdtion!=null) {
                // Don't bdd if it is blrfbdy b listfnfr
                if (!isListfnfr(AdtionListfnfr.dlbss, bdtion)) {
                    bddAdtionListfnfr(bdtion);
                }
                // Rfvfrsf linkbgf:
                bdtionPropfrtyCibngfListfnfr = drfbtfAdtionPropfrtyCibngfListfnfr(bdtion);
                bdtion.bddPropfrtyCibngfListfnfr(bdtionPropfrtyCibngfListfnfr);
            }
            firfPropfrtyCibngf("bdtion", oldVbluf, bdtion);
        }
    }

    privbtf boolfbn isListfnfr(Clbss<?> d, AdtionListfnfr b) {
        boolfbn isListfnfr = fblsf;
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==d && listfnfrs[i+1]==b) {
                    isListfnfr=truf;
            }
        }
        rfturn isListfnfr;
    }

    /**
     * Rfturns tif durrfntly sft <dodf>Adtion</dodf> for tiis
     * <dodf>AdtionEvfnt</dodf> sourdf, or <dodf>null</dodf> if no
     * <dodf>Adtion</dodf> is sft.
     *
     * @rfturn tif <dodf>Adtion</dodf> for tiis <dodf>AdtionEvfnt</dodf>
     *          sourdf; or <dodf>null</dodf>
     * @sindf 1.3
     * @sff Adtion
     * @sff #sftAdtion
     */
    publid Adtion gftAdtion() {
        rfturn bdtion;
    }

    /**
     * Sfts tif propfrtifs on tiis dombobox to mbtdi tiosf in tif spfdififd
     * <dodf>Adtion</dodf>.  Rfffr to <b irff="Adtion.itml#buttonAdtions">
     * Swing Componfnts Supporting <dodf>Adtion</dodf></b> for morf
     * dftbils bs to wiidi propfrtifs tiis sfts.
     *
     * @pbrbm b tif <dodf>Adtion</dodf> from wiidi to gft tif propfrtifs,
     *          or <dodf>null</dodf>
     * @sindf 1.3
     * @sff Adtion
     * @sff #sftAdtion
     */
    protfdtfd void donfigurfPropfrtifsFromAdtion(Adtion b) {
        AbstrbdtAdtion.sftEnbblfdFromAdtion(tiis, b);
        AbstrbdtAdtion.sftToolTipTfxtFromAdtion(tiis, b);
        sftAdtionCommbndFromAdtion(b);
    }

    /**
     * Crfbtfs bnd rfturns b <dodf>PropfrtyCibngfListfnfr</dodf> tibt is
     * rfsponsiblf for listfning for dibngfs from tif spfdififd
     * <dodf>Adtion</dodf> bnd updbting tif bppropribtf propfrtifs.
     * <p>
     * <b>Wbrning:</b> If you subdlbss tiis do not drfbtf bn bnonymous
     * innfr dlbss.  If you do tif lifftimf of tif dombobox will bf tifd to
     * tibt of tif <dodf>Adtion</dodf>.
     *
     * @pbrbm b tif dombobox's bdtion
     * @rfturn tif {@dodf PropfrtyCibngfListfnfr}
     * @sindf 1.3
     * @sff Adtion
     * @sff #sftAdtion
     */
    protfdtfd PropfrtyCibngfListfnfr drfbtfAdtionPropfrtyCibngfListfnfr(Adtion b) {
        rfturn nfw ComboBoxAdtionPropfrtyCibngfListfnfr(tiis, b);
    }

    /**
     * Updbtfs tif dombobox's stbtf in rfsponsf to propfrty dibngfs in
     * bssodibtfd bdtion. Tiis mftiod is invokfd from tif
     * {@dodf PropfrtyCibngfListfnfr} rfturnfd from
     * {@dodf drfbtfAdtionPropfrtyCibngfListfnfr}. Subdlbssfs do not normblly
     * nffd to invokf tiis. Subdlbssfs tibt support bdditionbl {@dodf Adtion}
     * propfrtifs siould ovfrridf tiis bnd
     * {@dodf donfigurfPropfrtifsFromAdtion}.
     * <p>
     * Rfffr to tif tbblf bt <b irff="Adtion.itml#buttonAdtions">
     * Swing Componfnts Supporting <dodf>Adtion</dodf></b> for b list of
     * tif propfrtifs tiis mftiod sfts.
     *
     * @pbrbm bdtion tif <dodf>Adtion</dodf> bssodibtfd witi tiis dombobox
     * @pbrbm propfrtyNbmf tif nbmf of tif propfrty tibt dibngfd
     * @sindf 1.6
     * @sff Adtion
     * @sff #donfigurfPropfrtifsFromAdtion
     */
    protfdtfd void bdtionPropfrtyCibngfd(Adtion bdtion, String propfrtyNbmf) {
        if (propfrtyNbmf == Adtion.ACTION_COMMAND_KEY) {
            sftAdtionCommbndFromAdtion(bdtion);
        } flsf if (propfrtyNbmf == "fnbblfd") {
            AbstrbdtAdtion.sftEnbblfdFromAdtion(tiis, bdtion);
        } flsf if (Adtion.SHORT_DESCRIPTION == propfrtyNbmf) {
            AbstrbdtAdtion.sftToolTipTfxtFromAdtion(tiis, bdtion);
        }
    }

    privbtf void sftAdtionCommbndFromAdtion(Adtion b) {
        sftAdtionCommbnd((b != null) ?
                             (String)b.gftVbluf(Adtion.ACTION_COMMAND_KEY) :
                             null);
    }


    privbtf stbtid dlbss ComboBoxAdtionPropfrtyCibngfListfnfr
                 fxtfnds AdtionPropfrtyCibngfListfnfr<JComboBox<?>> {
        ComboBoxAdtionPropfrtyCibngfListfnfr(JComboBox<?> b, Adtion b) {
            supfr(b, b);
        }
        protfdtfd void bdtionPropfrtyCibngfd(JComboBox<?> db,
                                             Adtion bdtion,
                                             PropfrtyCibngfEvfnt f) {
            if (AbstrbdtAdtion.siouldRfdonfigurf(f)) {
                db.donfigurfPropfrtifsFromAdtion(bdtion);
            } flsf {
                db.bdtionPropfrtyCibngfd(bdtion, f.gftPropfrtyNbmf());
            }
        }
    }

    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.
     * @pbrbm f  tif fvfnt of intfrfst
     *
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfItfmStbtfCibngfd(ItfmEvfnt f) {
        // Gubrbntffd to rfturn b non-null brrby
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        // Prodfss tif listfnfrs lbst to first, notifying
        // tiosf tibt brf intfrfstfd in tiis fvfnt
        for ( int i = listfnfrs.lfngti-2; i>=0; i-=2 ) {
            if ( listfnfrs[i]==ItfmListfnfr.dlbss ) {
                // Lbzily drfbtf tif fvfnt:
                // if (dibngfEvfnt == null)
                // dibngfEvfnt = nfw CibngfEvfnt(tiis);
                ((ItfmListfnfr)listfnfrs[i+1]).itfmStbtfCibngfd(f);
            }
        }
    }

    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.
     *
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfAdtionEvfnt() {
        if (!firingAdtionEvfnt) {
            // Sft flbg to fnsurf tibt bn infinitf loop is not drfbtfd
            firingAdtionEvfnt = truf;
            AdtionEvfnt f = null;
            // Gubrbntffd to rfturn b non-null brrby
            Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
            long mostRfdfntEvfntTimf = EvfntQufuf.gftMostRfdfntEvfntTimf();
            int modififrs = 0;
            AWTEvfnt durrfntEvfnt = EvfntQufuf.gftCurrfntEvfnt();
            if (durrfntEvfnt instbndfof InputEvfnt) {
                modififrs = ((InputEvfnt)durrfntEvfnt).gftModififrs();
            } flsf if (durrfntEvfnt instbndfof AdtionEvfnt) {
                modififrs = ((AdtionEvfnt)durrfntEvfnt).gftModififrs();
            }
            // Prodfss tif listfnfrs lbst to first, notifying
            // tiosf tibt brf intfrfstfd in tiis fvfnt
            for ( int i = listfnfrs.lfngti-2; i>=0; i-=2 ) {
                if ( listfnfrs[i]==AdtionListfnfr.dlbss ) {
                    // Lbzily drfbtf tif fvfnt:
                    if ( f == null )
                        f = nfw AdtionEvfnt(tiis,AdtionEvfnt.ACTION_PERFORMED,
                                            gftAdtionCommbnd(),
                                            mostRfdfntEvfntTimf, modififrs);
                    ((AdtionListfnfr)listfnfrs[i+1]).bdtionPfrformfd(f);
                }
            }
            firingAdtionEvfnt = fblsf;
        }
    }

    /**
     * Tiis protfdtfd mftiod is implfmfntbtion spfdifid. Do not bddfss dirfdtly
     * or ovfrridf.
     */
    protfdtfd void sflfdtfdItfmCibngfd() {
        if (sflfdtfdItfmRfmindfr != null ) {
            firfItfmStbtfCibngfd(nfw ItfmEvfnt(tiis,ItfmEvfnt.ITEM_STATE_CHANGED,
                                               sflfdtfdItfmRfmindfr,
                                               ItfmEvfnt.DESELECTED));
        }

        // sft tif nfw sflfdtfd itfm.
        sflfdtfdItfmRfmindfr = dbtbModfl.gftSflfdtfdItfm();

        if (sflfdtfdItfmRfmindfr != null ) {
            firfItfmStbtfCibngfd(nfw ItfmEvfnt(tiis,ItfmEvfnt.ITEM_STATE_CHANGED,
                                               sflfdtfdItfmRfmindfr,
                                               ItfmEvfnt.SELECTED));
        }
    }

    /**
     * Rfturns bn brrby dontbining tif sflfdtfd itfm.
     * Tiis mftiod is implfmfntfd for dompbtibility witi
     * <dodf>ItfmSflfdtbblf</dodf>.
     *
     * @rfturn bn brrby of <dodf>Objfdts</dodf> dontbining onf
     *          flfmfnt -- tif sflfdtfd itfm
     */
    publid Objfdt[] gftSflfdtfdObjfdts() {
        Objfdt sflfdtfdObjfdt = gftSflfdtfdItfm();
        if ( sflfdtfdObjfdt == null )
            rfturn nfw Objfdt[0];
        flsf {
            Objfdt rfsult[] = nfw Objfdt[1];
            rfsult[0] = sflfdtfdObjfdt;
            rfturn rfsult;
        }
    }

    /**
     * Tiis mftiod is publid bs bn implfmfntbtion sidf ffffdt.
     * do not dbll or ovfrridf.
     */
    publid void bdtionPfrformfd(AdtionEvfnt f) {
        ComboBoxEditor fditor = gftEditor();
        if ((fditor != null) && (f != null) && (fditor == f.gftSourdf())) {
            sftPopupVisiblf(fblsf);
            gftModfl().sftSflfdtfdItfm(fditor.gftItfm());
            String oldCommbnd = gftAdtionCommbnd();
            sftAdtionCommbnd("domboBoxEditfd");
            firfAdtionEvfnt();
            sftAdtionCommbnd(oldCommbnd);
        }
    }

    /**
     * Tiis mftiod is publid bs bn implfmfntbtion sidf ffffdt.
     * do not dbll or ovfrridf.
     */
    publid void dontfntsCibngfd(ListDbtbEvfnt f) {
        Objfdt oldSflfdtion = sflfdtfdItfmRfmindfr;
        Objfdt nfwSflfdtion = dbtbModfl.gftSflfdtfdItfm();
        if (oldSflfdtion == null || !oldSflfdtion.fqubls(nfwSflfdtion)) {
            sflfdtfdItfmCibngfd();
            if (!sflfdtingItfm) {
                firfAdtionEvfnt();
            }
        }
    }

    /**
     * Tiis mftiod is publid bs bn implfmfntbtion sidf ffffdt.
     * do not dbll or ovfrridf.
     */
    publid void intfrvblAddfd(ListDbtbEvfnt f) {
        if (sflfdtfdItfmRfmindfr != dbtbModfl.gftSflfdtfdItfm()) {
            sflfdtfdItfmCibngfd();
        }
    }

    /**
     * Tiis mftiod is publid bs bn implfmfntbtion sidf ffffdt.
     * do not dbll or ovfrridf.
     */
    publid void intfrvblRfmovfd(ListDbtbEvfnt f) {
        dontfntsCibngfd(f);
    }

    /**
     * Sflfdts tif list itfm tibt dorrfsponds to tif spfdififd kfybobrd
     * dibrbdtfr bnd rfturns truf, if tifrf is bn itfm dorrfsponding
     * to tibt dibrbdtfr.  Otifrwisf, rfturns fblsf.
     *
     * @pbrbm kfyCibr b dibr, typidblly tiis is b kfybobrd kfy
     *                  typfd by tif usfr
     * @rfturn {@dodf truf} if tifrf is bn itfm dorrfsponding to tibt dibrbdtfr.
     *         Otifrwisf, rfturns {@dodf fblsf}.
     */
    publid boolfbn sflfdtWitiKfyCibr(dibr kfyCibr) {
        int indfx;

        if ( kfySflfdtionMbnbgfr == null )
            kfySflfdtionMbnbgfr = drfbtfDffbultKfySflfdtionMbnbgfr();

        indfx = kfySflfdtionMbnbgfr.sflfdtionForKfy(kfyCibr,gftModfl());
        if ( indfx != -1 ) {
            sftSflfdtfdIndfx(indfx);
            rfturn truf;
        }
        flsf
            rfturn fblsf;
    }

    /**
     * Enbblfs tif dombo box so tibt itfms dbn bf sflfdtfd. Wifn tif
     * dombo box is disbblfd, itfms dbnnot bf sflfdtfd bnd vblufs
     * dbnnot bf typfd into its fifld (if it is fditbblf).
     *
     * @pbrbm b b boolfbn vbluf, wifrf truf fnbblfs tif domponfnt bnd
     *          fblsf disbblfs it
     * @bfbninfo
     *        bound: truf
     *    prfffrrfd: truf
     *  dfsdription: Wiftifr tif dombo box is fnbblfd.
     */
    publid void sftEnbblfd(boolfbn b) {
        supfr.sftEnbblfd(b);
        firfPropfrtyCibngf( "fnbblfd", !isEnbblfd(), isEnbblfd() );
    }

    /**
     * Initiblizfs tif fditor witi tif spfdififd itfm.
     *
     * @pbrbm bnEditor tif <dodf>ComboBoxEditor</dodf> tibt displbys
     *                  tif list itfm in tif
     *                  dombo box fifld bnd bllows it to bf fditfd
     * @pbrbm bnItfm   tif objfdt to displby bnd fdit in tif fifld
     */
    publid void donfigurfEditor(ComboBoxEditor bnEditor, Objfdt bnItfm) {
        bnEditor.sftItfm(bnItfm);
    }

    /**
     * Hbndlfs <dodf>KfyEvfnt</dodf>s, looking for tif Tbb kfy.
     * If tif Tbb kfy is found, tif popup window is dlosfd.
     *
     * @pbrbm f  tif <dodf>KfyEvfnt</dodf> dontbining tif kfybobrd
     *          kfy tibt wbs prfssfd
     */
    publid void prodfssKfyEvfnt(KfyEvfnt f) {
        if ( f.gftKfyCodf() == KfyEvfnt.VK_TAB ) {
            iidfPopup();
        }
        supfr.prodfssKfyEvfnt(f);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd boolfbn prodfssKfyBinding(KfyStrokf ks, KfyEvfnt f, int dondition, boolfbn prfssfd) {
        if (supfr.prodfssKfyBinding(ks, f, dondition, prfssfd)) {
            rfturn truf;
        }

        if (!isEditbblf() || dondition != WHEN_FOCUSED || gftEditor() == null
                || !Boolfbn.TRUE.fqubls(gftClifntPropfrty("JComboBox.isTbblfCfllEditor"))) {
            rfturn fblsf;
        }

        Componfnt fditorComponfnt = gftEditor().gftEditorComponfnt();
        if (fditorComponfnt instbndfof JComponfnt) {
            JComponfnt domponfnt = (JComponfnt) fditorComponfnt;
            rfturn domponfnt.prodfssKfyBinding(ks, f, WHEN_FOCUSED, prfssfd);
        }
        rfturn fblsf;
    }

    /**
     * Sfts tif objfdt tibt trbnslbtfs b kfybobrd dibrbdtfr into b list
     * sflfdtion. Typidblly, tif first sflfdtion witi b mbtdiing first
     * dibrbdtfr bfdomfs tif sflfdtfd itfm.
     *
     * @pbrbm bMbnbgfr b kfy sflfdtion mbnbgfr
     * @bfbninfo
     *       fxpfrt: truf
     *  dfsdription: Tif objfdts tibt dibngfs tif sflfdtion wifn b kfy is prfssfd.
     */
    publid void sftKfySflfdtionMbnbgfr(KfySflfdtionMbnbgfr bMbnbgfr) {
        kfySflfdtionMbnbgfr = bMbnbgfr;
    }

    /**
     * Rfturns tif list's kfy-sflfdtion mbnbgfr.
     *
     * @rfturn tif <dodf>KfySflfdtionMbnbgfr</dodf> durrfntly in usf
     */
    publid KfySflfdtionMbnbgfr gftKfySflfdtionMbnbgfr() {
        rfturn kfySflfdtionMbnbgfr;
    }

    /* Addfssing tif modfl */
    /**
     * Rfturns tif numbfr of itfms in tif list.
     *
     * @rfturn bn intfgfr fqubl to tif numbfr of itfms in tif list
     */
    publid int gftItfmCount() {
        rfturn dbtbModfl.gftSizf();
    }

    /**
     * Rfturns tif list itfm bt tif spfdififd indfx.  If <dodf>indfx</dodf>
     * is out of rbngf (lfss tibn zfro or grfbtfr tibn or fqubl to sizf)
     * it will rfturn <dodf>null</dodf>.
     *
     * @pbrbm indfx  bn intfgfr indidbting tif list position, wifrf tif first
     *               itfm stbrts bt zfro
     * @rfturn tif itfm bt tibt list position; or
     *                  <dodf>null</dodf> if out of rbngf
     */
    publid E gftItfmAt(int indfx) {
        rfturn dbtbModfl.gftElfmfntAt(indfx);
    }

    /**
     * Rfturns bn instbndf of tif dffbult kfy-sflfdtion mbnbgfr.
     *
     * @rfturn tif <dodf>KfySflfdtionMbnbgfr</dodf> durrfntly usfd by tif list
     * @sff #sftKfySflfdtionMbnbgfr
     */
    protfdtfd KfySflfdtionMbnbgfr drfbtfDffbultKfySflfdtionMbnbgfr() {
        rfturn nfw DffbultKfySflfdtionMbnbgfr();
    }


    /**
     * Tif intfrfbdf tibt dffinfs b <dodf>KfySflfdtionMbnbgfr</dodf>.
     * To qublify bs b <dodf>KfySflfdtionMbnbgfr</dodf>,
     * tif dlbss nffds to implfmfnt tif mftiod
     * tibt idfntififs tif list indfx givfn b dibrbdtfr bnd tif
     * dombo box dbtb modfl.
     */
    publid intfrfbdf KfySflfdtionMbnbgfr {
        /** Givfn <dodf>bKfy</dodf> bnd tif modfl, rfturns tif row
         *  tibt siould bfdomf sflfdtfd. Rfturn -1 if no mbtdi wbs
         *  found.
         *
         * @pbrbm  bKfy  b dibr vbluf, usublly indidbting b kfybobrd kfy tibt
         *               wbs prfssfd
         * @pbrbm bModfl b ComboBoxModfl -- tif domponfnt's dbtb modfl, dontbining
         *               tif list of sflfdtbblf itfms
         * @rfturn bn int fqubl to tif sflfdtfd row, wifrf 0 is tif
         *         first itfm bnd -1 is nonf.
         */
        int sflfdtionForKfy(dibr bKfy,ComboBoxModfl<?> bModfl);
    }

    dlbss DffbultKfySflfdtionMbnbgfr implfmfnts KfySflfdtionMbnbgfr, Sfriblizbblf {
        publid int sflfdtionForKfy(dibr bKfy,ComboBoxModfl<?> bModfl) {
            int i,d;
            int durrfntSflfdtion = -1;
            Objfdt sflfdtfdItfm = bModfl.gftSflfdtfdItfm();
            String v;
            String pbttfrn;

            if ( sflfdtfdItfm != null ) {
                for ( i=0,d=bModfl.gftSizf();i<d;i++ ) {
                    if ( sflfdtfdItfm == bModfl.gftElfmfntAt(i) ) {
                        durrfntSflfdtion  =  i;
                        brfbk;
                    }
                }
            }

            pbttfrn = ("" + bKfy).toLowfrCbsf();
            bKfy = pbttfrn.dibrAt(0);

            for ( i = ++durrfntSflfdtion, d = bModfl.gftSizf() ; i < d ; i++ ) {
                Objfdt flfm = bModfl.gftElfmfntAt(i);
                if (flfm != null && flfm.toString() != null) {
                    v = flfm.toString().toLowfrCbsf();
                    if ( v.lfngti() > 0 && v.dibrAt(0) == bKfy )
                        rfturn i;
                }
            }

            for ( i = 0 ; i < durrfntSflfdtion ; i ++ ) {
                Objfdt flfm = bModfl.gftElfmfntAt(i);
                if (flfm != null && flfm.toString() != null) {
                    v = flfm.toString().toLowfrCbsf();
                    if ( v.lfngti() > 0 && v.dibrAt(0) == bKfy )
                        rfturn i;
                }
            }
            rfturn -1;
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
     * Rfturns b string rfprfsfntbtion of tiis <dodf>JComboBox</dodf>.
     * Tiis mftiod is intfndfd to bf usfd only for dfbugging purposfs,
     * bnd tif dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not
     * bf <dodf>null</dodf>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis <dodf>JComboBox</dodf>
     */
    protfdtfd String pbrbmString() {
        String sflfdtfdItfmRfmindfrString = (sflfdtfdItfmRfmindfr != null ?
                                             sflfdtfdItfmRfmindfr.toString() :
                                             "");
        String isEditbblfString = (isEditbblf ? "truf" : "fblsf");
        String ligitWfigitPopupEnbblfdString = (ligitWfigitPopupEnbblfd ?
                                                "truf" : "fblsf");

        rfturn supfr.pbrbmString() +
        ",isEditbblf=" + isEditbblfString +
        ",ligitWfigitPopupEnbblfd=" + ligitWfigitPopupEnbblfdString +
        ",mbximumRowCount=" + mbximumRowCount +
        ",sflfdtfdItfmRfmindfr=" + sflfdtfdItfmRfmindfrString;
    }


///////////////////
// Addfssibility support
///////////////////

    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis JComboBox.
     * For dombo boxfs, tif AddfssiblfContfxt tbkfs tif form of bn
     * AddfssiblfJComboBox.
     * A nfw AddfssiblfJComboBox instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfJComboBox tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis JComboBox
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if ( bddfssiblfContfxt == null ) {
            bddfssiblfContfxt = nfw AddfssiblfJComboBox();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>JComboBox</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to Combo Box usfr-intfrfbdf flfmfnts.
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
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    protfdtfd dlbss AddfssiblfJComboBox fxtfnds AddfssiblfJComponfnt
    implfmfnts AddfssiblfAdtion, AddfssiblfSflfdtion {


        privbtf JList<?> popupList; // dombo box popup list
        privbtf Addfssiblf prfviousSflfdtfdAddfssiblf = null;

        /**
         * Rfturns bn AddfssiblfJComboBox instbndf
         * @sindf 1.4
         */
        publid AddfssiblfJComboBox() {
            // sft tif dombo box fditor's bddfssiblf nbmf bnd dfsdription
            JComboBox.tiis.bddPropfrtyCibngfListfnfr(nfw AddfssiblfJComboBoxPropfrtyCibngfListfnfr());
            sftEditorNbmfAndDfsdription();

            // Gft tif popup list
            Addfssiblf b = gftUI().gftAddfssiblfCiild(JComboBox.tiis, 0);
            if (b instbndfof jbvbx.swing.plbf.bbsid.ComboPopup) {
                // Listfn for dibngfs to tif popup mfnu sflfdtion.
                popupList = ((jbvbx.swing.plbf.bbsid.ComboPopup)b).gftList();
                popupList.bddListSflfdtionListfnfr(
                    nfw AddfssiblfJComboBoxListSflfdtionListfnfr());
            }
            // Listfn for popup mfnu siow/iidf fvfnts
            JComboBox.tiis.bddPopupMfnuListfnfr(
              nfw AddfssiblfJComboBoxPopupMfnuListfnfr());
        }

        /*
         * JComboBox PropfrtyCibngfListfnfr
         */
        privbtf dlbss AddfssiblfJComboBoxPropfrtyCibngfListfnfr
            implfmfnts PropfrtyCibngfListfnfr {

            publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
                if (f.gftPropfrtyNbmf() == "fditor") {
                    // sft tif dombo box fditor's bddfssiblf nbmf
                    // bnd dfsdription
                    sftEditorNbmfAndDfsdription();
                }
            }
        }

        /*
         * Sfts tif dombo box fditor's bddfssiblf nbmf bnd dfsdripton
         */
        privbtf void sftEditorNbmfAndDfsdription() {
            ComboBoxEditor fditor = JComboBox.tiis.gftEditor();
            if (fditor != null) {
                Componfnt domp = fditor.gftEditorComponfnt();
                if (domp instbndfof Addfssiblf) {
                    AddfssiblfContfxt bd = domp.gftAddfssiblfContfxt();
                    if (bd != null) { // mby bf null
                        bd.sftAddfssiblfNbmf(gftAddfssiblfNbmf());
                        bd.sftAddfssiblfDfsdription(gftAddfssiblfDfsdription());
                    }
                }
            }
        }

        /*
         * Listfnfr for dombo box popup mfnu
         * TIGER - 4669379 4894434
         */
        privbtf dlbss AddfssiblfJComboBoxPopupMfnuListfnfr
            implfmfnts PopupMfnuListfnfr {

            /**
             *  Tiis mftiod is dbllfd bfforf tif popup mfnu bfdomfs visiblf
             */
            publid void popupMfnuWillBfdomfVisiblf(PopupMfnuEvfnt f) {
                // sbvf tif initibl sflfdtion
                if (popupList == null) {
                    rfturn;
                }
                int sflfdtfdIndfx = popupList.gftSflfdtfdIndfx();
                if (sflfdtfdIndfx < 0) {
                    rfturn;
                }
                prfviousSflfdtfdAddfssiblf =
                    popupList.gftAddfssiblfContfxt().gftAddfssiblfCiild(sflfdtfdIndfx);
            }

            /**
             * Tiis mftiod is dbllfd bfforf tif popup mfnu bfdomfs invisiblf
             * Notf tibt b JPopupMfnu dbn bfdomf invisiblf bny timf
             */
            publid void popupMfnuWillBfdomfInvisiblf(PopupMfnuEvfnt f) {
                // ignorf
            }

            /**
             * Tiis mftiod is dbllfd wifn tif popup mfnu is dbndflfd
             */
            publid void popupMfnuCbndflfd(PopupMfnuEvfnt f) {
                // ignorf
            }
        }

        /*
         * Hbndlfs dibngfs to tif popup list sflfdtion.
         * TIGER - 4669379 4894434 4933143
         */
        privbtf dlbss AddfssiblfJComboBoxListSflfdtionListfnfr
            implfmfnts ListSflfdtionListfnfr {

            publid void vblufCibngfd(ListSflfdtionEvfnt f) {
                if (popupList == null) {
                    rfturn;
                }

                // Gft tif sflfdtfd popup list itfm.
                int sflfdtfdIndfx = popupList.gftSflfdtfdIndfx();
                if (sflfdtfdIndfx < 0) {
                    rfturn;
                }
                Addfssiblf sflfdtfdAddfssiblf =
                    popupList.gftAddfssiblfContfxt().gftAddfssiblfCiild(sflfdtfdIndfx);
                if (sflfdtfdAddfssiblf == null) {
                    rfturn;
                }

                // Firf b FOCUSED lost PropfrtyCibngfEvfnt for tif
                // prfviously sflfdtfd list itfm.
                PropfrtyCibngfEvfnt pdf;

                if (prfviousSflfdtfdAddfssiblf != null) {
                    pdf = nfw PropfrtyCibngfEvfnt(prfviousSflfdtfdAddfssiblf,
                        AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                        AddfssiblfStbtf.FOCUSED, null);
                    firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                                       null, pdf);
                }
                // Firf b FOCUSED gbinfd PropfrtyCibngfEvfnt for tif
                // durrfntly sflfdtfd list itfm.
                pdf = nfw PropfrtyCibngfEvfnt(sflfdtfdAddfssiblf,
                    AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                    null, AddfssiblfStbtf.FOCUSED);
                firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                                   null, pdf);

                // Firf tif ACCESSIBLE_ACTIVE_DESCENDANT_PROPERTY fvfnt
                // for tif dombo box.
                firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_ACTIVE_DESCENDANT_PROPERTY,
                                   prfviousSflfdtfdAddfssiblf, sflfdtfdAddfssiblf);

                // Sbvf tif prfvious sflfdtion.
                prfviousSflfdtfdAddfssiblf = sflfdtfdAddfssiblf;
            }
        }


        /**
         * Rfturns tif numbfr of bddfssiblf diildrfn in tif objfdt.  If bll
         * of tif diildrfn of tiis objfdt implfmfnt Addfssiblf, tibn tiis
         * mftiod siould rfturn tif numbfr of diildrfn of tiis objfdt.
         *
         * @rfturn tif numbfr of bddfssiblf diildrfn in tif objfdt.
         */
        publid int gftAddfssiblfCiildrfnCount() {
            // Alwbys dflfgbtf to tif UI if it fxists
            if (ui != null) {
                rfturn ui.gftAddfssiblfCiildrfnCount(JComboBox.tiis);
            } flsf {
                rfturn supfr.gftAddfssiblfCiildrfnCount();
            }
        }

        /**
         * Rfturns tif nti Addfssiblf diild of tif objfdt.
         * Tif diild bt indfx zfro rfprfsfnts tif popup.
         * If tif dombo box is fditbblf, tif diild bt indfx onf
         * rfprfsfnts tif fditor.
         *
         * @pbrbm i zfro-bbsfd indfx of diild
         * @rfturn tif nti Addfssiblf diild of tif objfdt
         */
        publid Addfssiblf gftAddfssiblfCiild(int i) {
            // Alwbys dflfgbtf to tif UI if it fxists
            if (ui != null) {
                rfturn ui.gftAddfssiblfCiild(JComboBox.tiis, i);
            } flsf {
               rfturn supfr.gftAddfssiblfCiild(i);
            }
        }

        /**
         * Gft tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif
         * objfdt
         * @sff AddfssiblfRolf
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.COMBO_BOX;
        }

        /**
         * Gfts tif stbtf sft of tiis objfdt.  Tif AddfssiblfStbtfSft of
         * bn objfdt is domposfd of b sft of uniquf AddfssiblfStbtfs.
         * A dibngf in tif AddfssiblfStbtfSft of bn objfdt will dbusf b
         * PropfrtyCibngfEvfnt to bf firfd for tif ACCESSIBLE_STATE_PROPERTY
         * propfrty.
         *
         * @rfturn bn instbndf of AddfssiblfStbtfSft dontbining tif
         * durrfnt stbtf sft of tif objfdt
         * @sff AddfssiblfStbtfSft
         * @sff AddfssiblfStbtf
         * @sff #bddPropfrtyCibngfListfnfr
         *
         */
        publid AddfssiblfStbtfSft gftAddfssiblfStbtfSft() {
            // TIGER - 4489748
            AddfssiblfStbtfSft bss = supfr.gftAddfssiblfStbtfSft();
            if (bss == null) {
                bss = nfw AddfssiblfStbtfSft();
            }
            if (JComboBox.tiis.isPopupVisiblf()) {
                bss.bdd(AddfssiblfStbtf.EXPANDED);
            } flsf {
                bss.bdd(AddfssiblfStbtf.COLLAPSED);
            }
            rfturn bss;
        }

        /**
         * Gft tif AddfssiblfAdtion bssodibtfd witi tiis objfdt.  In tif
         * implfmfntbtion of tif Jbvb Addfssibility API for tiis dlbss,
         * rfturn tiis objfdt, wiidi is rfsponsiblf for implfmfnting tif
         * AddfssiblfAdtion intfrfbdf on bfiblf of itsflf.
         *
         * @rfturn tiis objfdt
         */
        publid AddfssiblfAdtion gftAddfssiblfAdtion() {
            rfturn tiis;
        }

        /**
         * Rfturn b dfsdription of tif spfdififd bdtion of tif objfdt.
         *
         * @pbrbm i zfro-bbsfd indfx of tif bdtions
         */
        publid String gftAddfssiblfAdtionDfsdription(int i) {
            if (i == 0) {
                rfturn UIMbnbgfr.gftString("ComboBox.togglfPopupTfxt");
            }
            flsf {
                rfturn null;
            }
        }

        /**
         * Rfturns tif numbfr of Adtions bvbilbblf in tiis objfdt.  Tif
         * dffbult bfibvior of b dombo box is to ibvf onf bdtion.
         *
         * @rfturn 1, tif numbfr of Adtions in tiis objfdt
         */
        publid int gftAddfssiblfAdtionCount() {
            rfturn 1;
        }

        /**
         * Pfrform tif spfdififd Adtion on tif objfdt
         *
         * @pbrbm i zfro-bbsfd indfx of bdtions
         * @rfturn truf if tif tif bdtion wbs pfrformfd; flsf fblsf.
         */
        publid boolfbn doAddfssiblfAdtion(int i) {
            if (i == 0) {
                sftPopupVisiblf(!isPopupVisiblf());
                rfturn truf;
            }
            flsf {
                rfturn fblsf;
            }
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
         * Rfturns tif numbfr of Addfssiblf diildrfn durrfntly sflfdtfd.
         * If no diildrfn brf sflfdtfd, tif rfturn vbluf will bf 0.
         *
         * @rfturn tif numbfr of itfms durrfntly sflfdtfd.
         * @sindf 1.3
         */
        publid int gftAddfssiblfSflfdtionCount() {
            Objfdt o = JComboBox.tiis.gftSflfdtfdItfm();
            if (o != null) {
                rfturn 1;
            } flsf {
                rfturn 0;
            }
        }

        /**
         * Rfturns bn Addfssiblf rfprfsfnting tif spfdififd sflfdtfd diild
         * in tif popup.  If tifrf isn't b sflfdtion, or tifrf brf
         * ffwfr diildrfn sflfdtfd tibn tif intfgfr pbssfd in, tif rfturn
         * vbluf will bf null.
         * <p>Notf tibt tif indfx rfprfsfnts tif i-ti sflfdtfd diild, wiidi
         * is difffrfnt from tif i-ti diild.
         *
         * @pbrbm i tif zfro-bbsfd indfx of sflfdtfd diildrfn
         * @rfturn tif i-ti sflfdtfd diild
         * @sff #gftAddfssiblfSflfdtionCount
         * @sindf 1.3
         */
        publid Addfssiblf gftAddfssiblfSflfdtion(int i) {
            // Gft tif popup
            Addfssiblf b =
                JComboBox.tiis.gftUI().gftAddfssiblfCiild(JComboBox.tiis, 0);
            if (b != null &&
                b instbndfof jbvbx.swing.plbf.bbsid.ComboPopup) {

                // gft tif popup list
                JList<?> list = ((jbvbx.swing.plbf.bbsid.ComboPopup)b).gftList();

                // rfturn tif i-ti sflfdtion in tif popup list
                AddfssiblfContfxt bd = list.gftAddfssiblfContfxt();
                if (bd != null) {
                    AddfssiblfSflfdtion bs = bd.gftAddfssiblfSflfdtion();
                    if (bs != null) {
                        rfturn bs.gftAddfssiblfSflfdtion(i);
                    }
                }
            }
            rfturn null;
        }

        /**
         * Dftfrminfs if tif durrfnt diild of tiis objfdt is sflfdtfd.
         *
         * @rfturn truf if tif durrfnt diild of tiis objfdt is sflfdtfd;
         *              flsf fblsf
         * @pbrbm i tif zfro-bbsfd indfx of tif diild in tiis Addfssiblf
         * objfdt.
         * @sff AddfssiblfContfxt#gftAddfssiblfCiild
         * @sindf 1.3
         */
        publid boolfbn isAddfssiblfCiildSflfdtfd(int i) {
            rfturn JComboBox.tiis.gftSflfdtfdIndfx() == i;
        }

        /**
         * Adds tif spfdififd Addfssiblf diild of tif objfdt to tif objfdt's
         * sflfdtion.  If tif objfdt supports multiplf sflfdtions,
         * tif spfdififd diild is bddfd to bny fxisting sflfdtion, otifrwisf
         * it rfplbdfs bny fxisting sflfdtion in tif objfdt.  If tif
         * spfdififd diild is blrfbdy sflfdtfd, tiis mftiod ibs no ffffdt.
         *
         * @pbrbm i tif zfro-bbsfd indfx of tif diild
         * @sff AddfssiblfContfxt#gftAddfssiblfCiild
         * @sindf 1.3
         */
        publid void bddAddfssiblfSflfdtion(int i) {
            // TIGER - 4856195
            dlfbrAddfssiblfSflfdtion();
            JComboBox.tiis.sftSflfdtfdIndfx(i);
        }

        /**
         * Rfmovfs tif spfdififd diild of tif objfdt from tif objfdt's
         * sflfdtion.  If tif spfdififd itfm isn't durrfntly sflfdtfd, tiis
         * mftiod ibs no ffffdt.
         *
         * @pbrbm i tif zfro-bbsfd indfx of tif diild
         * @sff AddfssiblfContfxt#gftAddfssiblfCiild
         * @sindf 1.3
         */
        publid void rfmovfAddfssiblfSflfdtion(int i) {
            if (JComboBox.tiis.gftSflfdtfdIndfx() == i) {
                dlfbrAddfssiblfSflfdtion();
            }
        }

        /**
         * Clfbrs tif sflfdtion in tif objfdt, so tibt no diildrfn in tif
         * objfdt brf sflfdtfd.
         * @sindf 1.3
         */
        publid void dlfbrAddfssiblfSflfdtion() {
            JComboBox.tiis.sftSflfdtfdIndfx(-1);
        }

        /**
         * Cbusfs fvfry diild of tif objfdt to bf sflfdtfd
         * if tif objfdt supports multiplf sflfdtions.
         * @sindf 1.3
         */
        publid void sflfdtAllAddfssiblfSflfdtion() {
            // do notiing sindf multiplf sflfdtion is not supportfd
        }

//        publid Addfssiblf gftAddfssiblfAt(Point p) {
//            Addfssiblf b = gftAddfssiblfCiild(1);
//            if ( b != null ) {
//                rfturn b; // tif fditor
//            }
//            flsf {
//                rfturn gftAddfssiblfCiild(0); // tif list
//            }
//        }
        privbtf EditorAddfssiblfContfxt fditorAddfssiblfContfxt = null;

        privbtf dlbss AddfssiblfEditor implfmfnts Addfssiblf {
            publid AddfssiblfContfxt gftAddfssiblfContfxt() {
                if (fditorAddfssiblfContfxt == null) {
                    Componfnt d = JComboBox.tiis.gftEditor().gftEditorComponfnt();
                    if (d instbndfof Addfssiblf) {
                        fditorAddfssiblfContfxt =
                            nfw EditorAddfssiblfContfxt((Addfssiblf)d);
                    }
                }
                rfturn fditorAddfssiblfContfxt;
            }
        }

        /*
         * Wrbppfr dlbss for tif AddfssiblfContfxt implfmfntfd by tif
         * dombo box fditor.  Dflfgbtfs bll mftiod dblls fxdfpt
         * gftAddfssiblfIndfxInPbrfnt to tif fditor.  Tif
         * gftAddfssiblfIndfxInPbrfnt mftiod rfturns tif sflfdtfd
         * indfx in tif dombo box.
         */
        privbtf dlbss EditorAddfssiblfContfxt fxtfnds AddfssiblfContfxt {

            privbtf AddfssiblfContfxt bd;

            privbtf EditorAddfssiblfContfxt() {
            }

            /*
             * @pbrbm b tif AddfssiblfContfxt implfmfntfd by tif
             * dombo box fditor
             */
            EditorAddfssiblfContfxt(Addfssiblf b) {
                tiis.bd = b.gftAddfssiblfContfxt();
            }

            /**
             * Gfts tif bddfssiblfNbmf propfrty of tiis objfdt.  Tif bddfssiblfNbmf
             * propfrty of bn objfdt is b lodblizfd String tibt dfsignbtfs tif purposf
             * of tif objfdt.  For fxbmplf, tif bddfssiblfNbmf propfrty of b lbbfl
             * or button migit bf tif tfxt of tif lbbfl or button itsflf.  In tif
             * dbsf of bn objfdt tibt dofsn't displby its nbmf, tif bddfssiblfNbmf
             * siould still bf sft.  For fxbmplf, in tif dbsf of b tfxt fifld usfd
             * to fntfr tif nbmf of b dity, tif bddfssiblfNbmf for tif fn_US lodblf
             * dould bf 'dity.'
             *
             * @rfturn tif lodblizfd nbmf of tif objfdt; null if tiis
             * objfdt dofs not ibvf b nbmf
             *
             * @sff #sftAddfssiblfNbmf
             */
            publid String gftAddfssiblfNbmf() {
                rfturn bd.gftAddfssiblfNbmf();
            }

            /**
             * Sfts tif lodblizfd bddfssiblf nbmf of tiis objfdt.  Cibnging tif
             * nbmf will dbusf b PropfrtyCibngfEvfnt to bf firfd for tif
             * ACCESSIBLE_NAME_PROPERTY propfrty.
             *
             * @pbrbm s tif nfw lodblizfd nbmf of tif objfdt.
             *
             * @sff #gftAddfssiblfNbmf
             * @sff #bddPropfrtyCibngfListfnfr
             *
             * @bfbninfo
             *    prfffrrfd:   truf
             *    dfsdription: Sfts tif bddfssiblf nbmf for tif domponfnt.
             */
            publid void sftAddfssiblfNbmf(String s) {
                bd.sftAddfssiblfNbmf(s);
            }

            /**
             * Gfts tif bddfssiblfDfsdription propfrty of tiis objfdt.  Tif
             * bddfssiblfDfsdription propfrty of tiis objfdt is b siort lodblizfd
             * pirbsf dfsdribing tif purposf of tif objfdt.  For fxbmplf, in tif
             * dbsf of b 'Cbndfl' button, tif bddfssiblfDfsdription dould bf
             * 'Ignorf dibngfs bnd dlosf diblog box.'
             *
             * @rfturn tif lodblizfd dfsdription of tif objfdt; null if
             * tiis objfdt dofs not ibvf b dfsdription
             *
             * @sff #sftAddfssiblfDfsdription
             */
            publid String gftAddfssiblfDfsdription() {
                rfturn bd.gftAddfssiblfDfsdription();
            }

            /**
             * Sfts tif bddfssiblf dfsdription of tiis objfdt.  Cibnging tif
             * nbmf will dbusf b PropfrtyCibngfEvfnt to bf firfd for tif
             * ACCESSIBLE_DESCRIPTION_PROPERTY propfrty.
             *
             * @pbrbm s tif nfw lodblizfd dfsdription of tif objfdt
             *
             * @sff #sftAddfssiblfNbmf
             * @sff #bddPropfrtyCibngfListfnfr
             *
             * @bfbninfo
             *    prfffrrfd:   truf
             *    dfsdription: Sfts tif bddfssiblf dfsdription for tif domponfnt.
             */
            publid void sftAddfssiblfDfsdription(String s) {
                bd.sftAddfssiblfDfsdription(s);
            }

            /**
             * Gfts tif rolf of tiis objfdt.  Tif rolf of tif objfdt is tif gfnfrid
             * purposf or usf of tif dlbss of tiis objfdt.  For fxbmplf, tif rolf
             * of b pusi button is AddfssiblfRolf.PUSH_BUTTON.  Tif rolfs in
             * AddfssiblfRolf brf providfd so domponfnt dfvflopfrs dbn pidk from
             * b sft of prfdffinfd rolfs.  Tiis fnbblfs bssistivf tfdinologifs to
             * providf b donsistfnt intfrfbdf to vbrious twfbkfd subdlbssfs of
             * domponfnts (f.g., usf AddfssiblfRolf.PUSH_BUTTON for bll domponfnts
             * tibt bdt likf b pusi button) bs wfll bs distinguisi bftwffn subdlbssfs
             * tibt bfibvf difffrfntly (f.g., AddfssiblfRolf.CHECK_BOX for difdk boxfs
             * bnd AddfssiblfRolf.RADIO_BUTTON for rbdio buttons).
             * <p>Notf tibt tif AddfssiblfRolf dlbss is blso fxtfnsiblf, so
             * dustom domponfnt dfvflopfrs dbn dffinf tifir own AddfssiblfRolf's
             * if tif sft of prfdffinfd rolfs is inbdfqubtf.
             *
             * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif objfdt
             * @sff AddfssiblfRolf
             */
            publid AddfssiblfRolf gftAddfssiblfRolf() {
                rfturn bd.gftAddfssiblfRolf();
            }

            /**
             * Gfts tif stbtf sft of tiis objfdt.  Tif AddfssiblfStbtfSft of bn objfdt
             * is domposfd of b sft of uniquf AddfssiblfStbtfs.  A dibngf in tif
             * AddfssiblfStbtfSft of bn objfdt will dbusf b PropfrtyCibngfEvfnt to
             * bf firfd for tif ACCESSIBLE_STATE_PROPERTY propfrty.
             *
             * @rfturn bn instbndf of AddfssiblfStbtfSft dontbining tif
             * durrfnt stbtf sft of tif objfdt
             * @sff AddfssiblfStbtfSft
             * @sff AddfssiblfStbtf
             * @sff #bddPropfrtyCibngfListfnfr
             */
            publid AddfssiblfStbtfSft gftAddfssiblfStbtfSft() {
                rfturn bd.gftAddfssiblfStbtfSft();
            }

            /**
             * Gfts tif Addfssiblf pbrfnt of tiis objfdt.
             *
             * @rfturn tif Addfssiblf pbrfnt of tiis objfdt; null if tiis
             * objfdt dofs not ibvf bn Addfssiblf pbrfnt
             */
            publid Addfssiblf gftAddfssiblfPbrfnt() {
                rfturn bd.gftAddfssiblfPbrfnt();
            }

            /**
             * Sfts tif Addfssiblf pbrfnt of tiis objfdt.  Tiis is mfbnt to bf usfd
             * only in tif situbtions wifrf tif bdtubl domponfnt's pbrfnt siould
             * not bf trfbtfd bs tif domponfnt's bddfssiblf pbrfnt bnd is b mftiod
             * tibt siould only bf dbllfd by tif pbrfnt of tif bddfssiblf diild.
             *
             * @pbrbm b - Addfssiblf to bf sft bs tif pbrfnt
             */
            publid void sftAddfssiblfPbrfnt(Addfssiblf b) {
                bd.sftAddfssiblfPbrfnt(b);
            }

            /**
             * Gfts tif 0-bbsfd indfx of tiis objfdt in its bddfssiblf pbrfnt.
             *
             * @rfturn tif 0-bbsfd indfx of tiis objfdt in its pbrfnt; -1 if tiis
             * objfdt dofs not ibvf bn bddfssiblf pbrfnt.
             *
             * @sff #gftAddfssiblfPbrfnt
             * @sff #gftAddfssiblfCiildrfnCount
             * @sff #gftAddfssiblfCiild
             */
            publid int gftAddfssiblfIndfxInPbrfnt() {
                rfturn JComboBox.tiis.gftSflfdtfdIndfx();
            }

            /**
             * Rfturns tif numbfr of bddfssiblf diildrfn of tif objfdt.
             *
             * @rfturn tif numbfr of bddfssiblf diildrfn of tif objfdt.
             */
            publid int gftAddfssiblfCiildrfnCount() {
                rfturn bd.gftAddfssiblfCiildrfnCount();
            }

            /**
             * Rfturns tif spfdififd Addfssiblf diild of tif objfdt.  Tif Addfssiblf
             * diildrfn of bn Addfssiblf objfdt brf zfro-bbsfd, so tif first diild
             * of bn Addfssiblf diild is bt indfx 0, tif sfdond diild is bt indfx 1,
             * bnd so on.
             *
             * @pbrbm i zfro-bbsfd indfx of diild
             * @rfturn tif Addfssiblf diild of tif objfdt
             * @sff #gftAddfssiblfCiildrfnCount
             */
            publid Addfssiblf gftAddfssiblfCiild(int i) {
                rfturn bd.gftAddfssiblfCiild(i);
            }

            /**
             * Gfts tif lodblf of tif domponfnt. If tif domponfnt dofs not ibvf b
             * lodblf, tifn tif lodblf of its pbrfnt is rfturnfd.
             *
             * @rfturn tiis domponfnt's lodblf.  If tiis domponfnt dofs not ibvf
             * b lodblf, tif lodblf of its pbrfnt is rfturnfd.
             *
             * @fxdfption IllfgblComponfntStbtfExdfption
             * If tif Componfnt dofs not ibvf its own lodblf bnd ibs not yft bffn
             * bddfd to b dontbinmfnt iifrbrdiy sudi tibt tif lodblf dbn bf
             * dftfrminfd from tif dontbining pbrfnt.
             */
            publid Lodblf gftLodblf() tirows IllfgblComponfntStbtfExdfption {
                rfturn bd.gftLodblf();
            }

            /**
             * Adds b PropfrtyCibngfListfnfr to tif listfnfr list.
             * Tif listfnfr is rfgistfrfd for bll Addfssiblf propfrtifs bnd will
             * bf dbllfd wifn tiosf propfrtifs dibngf.
             *
             * @sff #ACCESSIBLE_NAME_PROPERTY
             * @sff #ACCESSIBLE_DESCRIPTION_PROPERTY
             * @sff #ACCESSIBLE_STATE_PROPERTY
             * @sff #ACCESSIBLE_VALUE_PROPERTY
             * @sff #ACCESSIBLE_SELECTION_PROPERTY
             * @sff #ACCESSIBLE_TEXT_PROPERTY
             * @sff #ACCESSIBLE_VISIBLE_DATA_PROPERTY
             *
             * @pbrbm listfnfr  Tif PropfrtyCibngfListfnfr to bf bddfd
             */
            publid void bddPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr) {
                bd.bddPropfrtyCibngfListfnfr(listfnfr);
            }

            /**
             * Rfmovfs b PropfrtyCibngfListfnfr from tif listfnfr list.
             * Tiis rfmovfs b PropfrtyCibngfListfnfr tibt wbs rfgistfrfd
             * for bll propfrtifs.
             *
             * @pbrbm listfnfr  Tif PropfrtyCibngfListfnfr to bf rfmovfd
             */
            publid void rfmovfPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr) {
                bd.rfmovfPropfrtyCibngfListfnfr(listfnfr);
            }

            /**
             * Gfts tif AddfssiblfAdtion bssodibtfd witi tiis objfdt tibt supports
             * onf or morf bdtions.
             *
             * @rfturn AddfssiblfAdtion if supportfd by objfdt; flsf rfturn null
             * @sff AddfssiblfAdtion
             */
            publid AddfssiblfAdtion gftAddfssiblfAdtion() {
                rfturn bd.gftAddfssiblfAdtion();
            }

            /**
             * Gfts tif AddfssiblfComponfnt bssodibtfd witi tiis objfdt tibt ibs b
             * grbpiidbl rfprfsfntbtion.
             *
             * @rfturn AddfssiblfComponfnt if supportfd by objfdt; flsf rfturn null
             * @sff AddfssiblfComponfnt
             */
            publid AddfssiblfComponfnt gftAddfssiblfComponfnt() {
                rfturn bd.gftAddfssiblfComponfnt();
            }

            /**
             * Gfts tif AddfssiblfSflfdtion bssodibtfd witi tiis objfdt wiidi bllows its
             * Addfssiblf diildrfn to bf sflfdtfd.
             *
             * @rfturn AddfssiblfSflfdtion if supportfd by objfdt; flsf rfturn null
             * @sff AddfssiblfSflfdtion
             */
            publid AddfssiblfSflfdtion gftAddfssiblfSflfdtion() {
                rfturn bd.gftAddfssiblfSflfdtion();
            }

            /**
             * Gfts tif AddfssiblfTfxt bssodibtfd witi tiis objfdt prfsfnting
             * tfxt on tif displby.
             *
             * @rfturn AddfssiblfTfxt if supportfd by objfdt; flsf rfturn null
             * @sff AddfssiblfTfxt
             */
            publid AddfssiblfTfxt gftAddfssiblfTfxt() {
                rfturn bd.gftAddfssiblfTfxt();
            }

            /**
             * Gfts tif AddfssiblfEditbblfTfxt bssodibtfd witi tiis objfdt
             * prfsfnting fditbblf tfxt on tif displby.
             *
             * @rfturn AddfssiblfEditbblfTfxt if supportfd by objfdt; flsf rfturn null
             * @sff AddfssiblfEditbblfTfxt
             */
            publid AddfssiblfEditbblfTfxt gftAddfssiblfEditbblfTfxt() {
                rfturn bd.gftAddfssiblfEditbblfTfxt();
            }

            /**
             * Gfts tif AddfssiblfVbluf bssodibtfd witi tiis objfdt tibt supports b
             * Numfridbl vbluf.
             *
             * @rfturn AddfssiblfVbluf if supportfd by objfdt; flsf rfturn null
             * @sff AddfssiblfVbluf
             */
            publid AddfssiblfVbluf gftAddfssiblfVbluf() {
                rfturn bd.gftAddfssiblfVbluf();
            }

            /**
             * Gfts tif AddfssiblfIdons bssodibtfd witi bn objfdt tibt ibs
             * onf or morf bssodibtfd idons
             *
             * @rfturn bn brrby of AddfssiblfIdon if supportfd by objfdt;
             * otifrwisf rfturn null
             * @sff AddfssiblfIdon
             */
            publid AddfssiblfIdon [] gftAddfssiblfIdon() {
                rfturn bd.gftAddfssiblfIdon();
            }

            /**
             * Gfts tif AddfssiblfRflbtionSft bssodibtfd witi bn objfdt
             *
             * @rfturn bn AddfssiblfRflbtionSft if supportfd by objfdt;
             * otifrwisf rfturn null
             * @sff AddfssiblfRflbtionSft
             */
            publid AddfssiblfRflbtionSft gftAddfssiblfRflbtionSft() {
                rfturn bd.gftAddfssiblfRflbtionSft();
            }

            /**
             * Gfts tif AddfssiblfTbblf bssodibtfd witi bn objfdt
             *
             * @rfturn bn AddfssiblfTbblf if supportfd by objfdt;
             * otifrwisf rfturn null
             * @sff AddfssiblfTbblf
             */
            publid AddfssiblfTbblf gftAddfssiblfTbblf() {
                rfturn bd.gftAddfssiblfTbblf();
            }

            /**
             * Support for rfporting bound propfrty dibngfs.  If oldVbluf bnd
             * nfwVbluf brf not fqubl bnd tif PropfrtyCibngfEvfnt listfnfr list
             * is not fmpty, tifn firf b PropfrtyCibngf fvfnt to fbdi listfnfr.
             * In gfnfrbl, tiis is for usf by tif Addfssiblf objfdts tifmsflvfs
             * bnd siould not bf dbllfd by bn bpplidbtion progrbm.
             * @pbrbm propfrtyNbmf  Tif progrbmmbtid nbmf of tif propfrty tibt
             * wbs dibngfd.
             * @pbrbm oldVbluf  Tif old vbluf of tif propfrty.
             * @pbrbm nfwVbluf  Tif nfw vbluf of tif propfrty.
             * @sff jbvb.bfbns.PropfrtyCibngfSupport
             * @sff #bddPropfrtyCibngfListfnfr
             * @sff #rfmovfPropfrtyCibngfListfnfr
             * @sff #ACCESSIBLE_NAME_PROPERTY
             * @sff #ACCESSIBLE_DESCRIPTION_PROPERTY
             * @sff #ACCESSIBLE_STATE_PROPERTY
             * @sff #ACCESSIBLE_VALUE_PROPERTY
             * @sff #ACCESSIBLE_SELECTION_PROPERTY
             * @sff #ACCESSIBLE_TEXT_PROPERTY
             * @sff #ACCESSIBLE_VISIBLE_DATA_PROPERTY
             */
            publid void firfPropfrtyCibngf(String propfrtyNbmf,
                                           Objfdt oldVbluf,
                                           Objfdt nfwVbluf) {
                bd.firfPropfrtyCibngf(propfrtyNbmf, oldVbluf, nfwVbluf);
            }
        }

    } // innfrdlbss AddfssiblfJComboBox
}
