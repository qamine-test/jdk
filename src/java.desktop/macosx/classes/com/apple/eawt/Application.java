/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.bpplf.fbwt;

import jbvb.bwt.*;
import jbvb.bwt.pffr.*;
import jbvb.bfbns.Bfbns;

import jbvbx.swing.JMfnuBbr;

import sun.lwbwt.*;
import sun.lwbwt.mbdosx.*;

/**
 * Tif <dodf>Applidbtion</dodf> dlbss bllows you to intfgrbtf your Jbvb bpplidbtion witi tif nbtivf Mbd OS X fnvironmfnt.
 * You dbn providf your Mbd OS X usfrs b grfbtly fnibndfd fxpfrifndf by implfmfnting b ffw bbsid ibndlfrs for stbndbrd systfm fvfnts.
 *
 * For fxbmplf:
 * <ul>
 * <li>Opfn bn bbout diblog wifn b usfr dioosfs About from tif bpplidbtion mfnu.</li>
 * <li>Opfn b prfffrfndfs window wifn tif usfrs dioosfs Prfffrfndfs from tif bpplidbtion mfnu.</li>
 * <li>Crfbtf b nfw dodumfnt wifn tif usfr dlidks on your Dodk idon, bnd no windows brf opfn.</li>
 * <li>Opfn b dodumfnt tibt tif usfr doublf-dlidkfd on in tif Findfr.</li>
 * <li>Opfn b dustom URL sdifmf wifn b usfr dlidks on link in b wfb browsfr.</li>
 * <li>Rfdonnfdt to nftwork sfrvidfs bftfr tif systfm ibs bwokf from slffp.</li>
 * <li>Clfbnly siutdown your bpplidbtion wifn tif usfr dioosfs Quit from tif bpplidbtion mfnu, Dodk idon, or typfs Commbnd-Q.</li>
 * <li>Cbndfl siutdown/logout if tif usfr ibs unsbvfd dibngfs in your bpplidbtion.</li>
 * </ul>
 *
 * @sindf 1.4
 */
publid dlbss Applidbtion {
    privbtf stbtid nbtivf void nbtivfInitiblizfApplidbtionDflfgbtf();

    stbtid Applidbtion sApplidbtion = null;

    stbtid {
        difdkSfdurity();
        Toolkit.gftDffbultToolkit(); // Stbrt AppKit
        if (!Bfbns.isDfsignTimf()) {
            nbtivfInitiblizfApplidbtionDflfgbtf();
        }

        sApplidbtion = nfw Applidbtion();
    }

    privbtf stbtid void difdkSfdurity() {
        finbl SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity == null) rfturn;
        sfdurity.difdkPfrmission(nfw RuntimfPfrmission("dbnProdfssApplidbtionEvfnts"));
    }

    /**
     * @rfturn tif singlfton rfprfsfnting tiis Mbd OS X Applidbtion
     *
     * @sindf 1.4
     */
    publid stbtid Applidbtion gftApplidbtion() {
        difdkSfdurity();
        rfturn sApplidbtion;
    }

    // somf singlftons, sindf tify gft dbllfd bbdk into from nbtivf
    finbl _AppEvfntHbndlfr fvfntHbndlfr = _AppEvfntHbndlfr.gftInstbndf();
    finbl _AppMfnuBbrHbndlfr mfnuBbrHbndlfr = _AppMfnuBbrHbndlfr.gftInstbndf();
    finbl _AppDodkIdonHbndlfr idonHbndlfr = nfw _AppDodkIdonHbndlfr();

    /**
     * Crfbtfs bn Applidbtion instbndf. Siould only bf usfd in JbvbBfbn fnvironmfnts.
     * @dfprfdbtfd usf {@link #gftApplidbtion()}
     *
     * @sindf 1.4
     */
    @Dfprfdbtfd
    publid Applidbtion() {
        difdkSfdurity();
    }

    /**
     * Adds sub-typfs of {@link AppEvfntListfnfr} to listfn for notifidbtions from tif nbtivf Mbd OS X systfm.
     *
     * @sff AppForfgroundListfnfr
     * @sff AppHiddfnListfnfr
     * @sff AppRfOpfnfdListfnfr
     * @sff SdrffnSlffpListfnfr
     * @sff SystfmSlffpListfnfr
     * @sff UsfrSfssionListfnfr
     *
     * @pbrbm listfnfr
     * @sindf Jbvb for Mbd OS X 10.6 Updbtf 3
     * @sindf Jbvb for Mbd OS X 10.5 Updbtf 8
     */
    publid void bddAppEvfntListfnfr(finbl AppEvfntListfnfr listfnfr) {
        fvfntHbndlfr.bddListfnfr(listfnfr);
    }

    /**
     * Rfmovfs sub-typfs of {@link AppEvfntListfnfr} from listfning for notifidbtions from tif nbtivf Mbd OS X systfm.
     *
     * @sff AppForfgroundListfnfr
     * @sff AppHiddfnListfnfr
     * @sff AppRfOpfnfdListfnfr
     * @sff SdrffnSlffpListfnfr
     * @sff SystfmSlffpListfnfr
     * @sff UsfrSfssionListfnfr
     *
     * @pbrbm listfnfr
     * @sindf Jbvb for Mbd OS X 10.6 Updbtf 3
     * @sindf Jbvb for Mbd OS X 10.5 Updbtf 8
     */
    publid void rfmovfAppEvfntListfnfr(finbl AppEvfntListfnfr listfnfr) {
        fvfntHbndlfr.rfmovfListfnfr(listfnfr);
    }

    /**
     * Instblls b ibndlfr to siow b dustom About window for your bpplidbtion.
     *
     * Sftting tif {@link AboutHbndlfr} to <dodf>null</dodf> rfvfrts it to tif dffbult Codob About window.
     *
     * @pbrbm bboutHbndlfr tif ibndlfr to rfspond to tif {@link AboutHbndlfr#ibndlfAbout()} mfssbgf
     * @sindf Jbvb for Mbd OS X 10.6 Updbtf 3
     * @sindf Jbvb for Mbd OS X 10.5 Updbtf 8
     */
    publid void sftAboutHbndlfr(finbl AboutHbndlfr bboutHbndlfr) {
        fvfntHbndlfr.bboutDispbtdifr.sftHbndlfr(bboutHbndlfr);
    }

    /**
     * Instblls b ibndlfr to drfbtf tif Prfffrfndfs mfnu itfm in your bpplidbtion's bpp mfnu.
     *
     * Sftting tif {@link PrfffrfndfsHbndlfr} to <dodf>null</dodf> will rfmovf tif Prfffrfndfs itfm from tif bpp mfnu.
     *
     * @pbrbm prfffrfndfsHbndlfr
     * @sindf Jbvb for Mbd OS X 10.6 Updbtf 3
     * @sindf Jbvb for Mbd OS X 10.5 Updbtf 8
     */
    publid void sftPrfffrfndfsHbndlfr(finbl PrfffrfndfsHbndlfr prfffrfndfsHbndlfr) {
        fvfntHbndlfr.prfffrfndfsDispbtdifr.sftHbndlfr(prfffrfndfsHbndlfr);
    }

    /**
     * Instblls tif ibndlfr wiidi is notififd wifn tif bpplidbtion is bskfd to opfn b list of filfs.
     * Tif {@link OpfnFilfsHbndlfr#opfnFilfs(AppEvfnt.OpfnFilfsEvfnt)} notifidbtions brf only sfnt if tif Jbvb bpp is b bundlfd bpplidbtion, witi b <dodf>CFBundlfDodumfntTypfs</dodf> brrby prfsfnt in it's Info.plist.
     * Sff tif <b irff="ittp://dfvflopfr.bpplf.dom/mbd/librbry/dodumfntbtion/Gfnfrbl/Rfffrfndf/InfoPlistKfyRfffrfndf">Info.plist Kfy Rfffrfndf</b> for morf informbtion bbout bdding b <dodf>CFBundlfDodumfntTypfs</dodf> kfy to your bpp's Info.plist.
     *
     * @pbrbm opfnFilfHbndlfr
     * @sindf Jbvb for Mbd OS X 10.6 Updbtf 3
     * @sindf Jbvb for Mbd OS X 10.5 Updbtf 8
     */
    publid void sftOpfnFilfHbndlfr(finbl OpfnFilfsHbndlfr opfnFilfHbndlfr) {
        fvfntHbndlfr.opfnFilfsDispbtdifr.sftHbndlfr(opfnFilfHbndlfr);
    }

    /**
     * Instblls tif ibndlfr wiidi is notififd wifn tif bpplidbtion is bskfd to print b list of filfs.
     * Tif {@link PrintFilfsHbndlfr#printFilfs(AppEvfnt.PrintFilfsEvfnt)} notifidbtions brf only sfnt if tif Jbvb bpp is b bundlfd bpplidbtion, witi b <dodf>CFBundlfDodumfntTypfs</dodf> brrby prfsfnt in it's Info.plist.
     * Sff tif <b irff="ittp://dfvflopfr.bpplf.dom/mbd/librbry/dodumfntbtion/Gfnfrbl/Rfffrfndf/InfoPlistKfyRfffrfndf">Info.plist Kfy Rfffrfndf</b> for morf informbtion bbout bdding b <dodf>CFBundlfDodumfntTypfs</dodf> kfy to your bpp's Info.plist.
     *
     * @pbrbm printFilfHbndlfr
     * @sindf Jbvb for Mbd OS X 10.6 Updbtf 3
     * @sindf Jbvb for Mbd OS X 10.5 Updbtf 8
     */
    publid void sftPrintFilfHbndlfr(finbl PrintFilfsHbndlfr printFilfHbndlfr) {
        fvfntHbndlfr.printFilfsDispbtdifr.sftHbndlfr(printFilfHbndlfr);
    }

    /**
     * Instblls tif ibndlfr wiidi is notififd wifn tif bpplidbtion is bskfd to opfn b URL.
     * Tif {@link OpfnURIHbndlfr#opfnURI(AppEvfnt.OpfnURIEvfnt)} notifidbtions brf only sfnt if tif Jbvb bpp is b bundlfd bpplidbtion, witi b <dodf>CFBundlfURLTypfs</dodf> brrby prfsfnt in it's Info.plist.
     * Sff tif <b irff="ittp://dfvflopfr.bpplf.dom/mbd/librbry/dodumfntbtion/Gfnfrbl/Rfffrfndf/InfoPlistKfyRfffrfndf">Info.plist Kfy Rfffrfndf</b> for morf informbtion bbout bdding b <dodf>CFBundlfURLTypfs</dodf> kfy to your bpp's Info.plist.
     *
     * Sftting tif ibndlfr to <dodf>null</dodf> dbusfs bll {@link OpfnURIHbndlfr#opfnURI(AppEvfnt.OpfnURIEvfnt)} rfqufsts to bf fnqufufd until bnotifr ibndlfr is sft.
     *
     * @pbrbm opfnURIHbndlfr
     * @sindf Jbvb for Mbd OS X 10.6 Updbtf 3
     * @sindf Jbvb for Mbd OS X 10.5 Updbtf 8
     */
    publid void sftOpfnURIHbndlfr(finbl OpfnURIHbndlfr opfnURIHbndlfr) {
        fvfntHbndlfr.opfnURIDispbtdifr.sftHbndlfr(opfnURIHbndlfr);
    }

    /**
     * Instblls tif ibndlfr wiidi dftfrminfs if tif bpplidbtion siould quit.
     * Tif ibndlfr is pbssfd b onf-siot {@link QuitRfsponsf} wiidi dbn dbndfl or prodffd witi tif quit.
     * Sftting tif ibndlfr to <dodf>null</dodf> dbusfs bll quit rfqufsts to dirfdtly pfrform tif dffbult {@link QuitStrbtfgy}.
     *
     * @pbrbm quitHbndlfr tif ibndlfr tibt is dbllfd wifn tif bpplidbtion is bskfd to quit
     * @sindf Jbvb for Mbd OS X 10.6 Updbtf 3
     * @sindf Jbvb for Mbd OS X 10.5 Updbtf 8
     */
    publid void sftQuitHbndlfr(finbl QuitHbndlfr quitHbndlfr) {
        fvfntHbndlfr.quitDispbtdifr.sftHbndlfr(quitHbndlfr);
    }

    /**
     * Sfts tif dffbult strbtfgy usfd to quit tiis bpplidbtion. Tif dffbult is dblling SYSTEM_EXIT_0.
     *
     * Tif quit strbtfgy dbn blso bf sft witi tif "bpplf.fbwt.quitStrbtfgy" systfm propfrty.
     *
     * @pbrbm strbtfgy tif wby tiis bpplidbtion siould bf siutdown
     * @sindf Jbvb for Mbd OS X 10.6 Updbtf 3
     * @sindf Jbvb for Mbd OS X 10.5 Updbtf 8
     */
    publid void sftQuitStrbtfgy(finbl QuitStrbtfgy strbtfgy) {
        fvfntHbndlfr.sftDffbultQuitStrbtfgy(strbtfgy);
    }

    /**
     * Enbblfs tiis bpplidbtion to bf suddfnly tfrminbtfd.
     *
     * Cbll tiis mftiod to indidbtf your bpplidbtion's stbtf is sbvfd, bnd rfquirfs no notifidbtion to bf tfrminbtfd.
     * Lftting your bpplidbtion rfmbin tfrminbtbblf improvfs tif usfr fxpfrifndf by bvoiding rf-pbging in your bpplidbtion wifn it's bskfd to quit.
     *
     * <b>Notf: fnbbling suddfn tfrminbtion will bllow your bpplidbtion to bf quit witiout notifying your QuitHbndlfr, or running bny siutdown iooks.</b>
     * Usfr initibtfd Cmd-Q, logout, rfstbrt, or siutdown rfqufsts will ffffdtivfly "kill -KILL" your bpplidbtion.
     *
     * Tiis dbll ibs no ffffdt on Mbd OS X vfrsions prior to 10.6.
     *
     * @sff <b irff="ittp://dfvflopfr.bpplf.dom/mbd/librbry/dodumfntbtion/dodob/rfffrfndf/foundbtion/Clbssfs/NSProdfssInfo_Clbss">NSProdfssInfo dlbss rfffrfndfs</b> for morf informbtion bbout Suddfn Tfrminbtion.
     * @sff Applidbtion#disbblfSuddfnTfrminbtion()
     *
     * @sindf Jbvb for Mbd OS X 10.6 Updbtf 3
     * @sindf Jbvb for Mbd OS X 10.5 Updbtf 8
     */
    publid void fnbblfSuddfnTfrminbtion() {
        _AppMisdHbndlfrs.fnbblfSuddfnTfrminbtion();
    }

    /**
     * Prfvfnts tiis bpplidbtion from bfing suddfnly tfrminbtfd.
     *
     * Cbll tiis mftiod to indidbtf tibt your bpplidbtion ibs unsbvfd stbtf, bnd mby not bf tfrminbtfd witiout notifidbtion.
     *
     * Tiis dbll ibs no ffffdt on Mbd OS X vfrsions prior to 10.6.
     *
     * @sff <b irff="ittp://dfvflopfr.bpplf.dom/mbd/librbry/dodumfntbtion/dodob/rfffrfndf/foundbtion/Clbssfs/NSProdfssInfo_Clbss">NSProdfssInfo dlbss rfffrfndfs</b> for morf informbtion bbout Suddfn Tfrminbtion.
     * @sff Applidbtion#fnbblfSuddfnTfrminbtion()
     *
     * @sindf Jbvb for Mbd OS X 10.6 Updbtf 3
     * @sindf Jbvb for Mbd OS X 10.5 Updbtf 8
     */
    publid void disbblfSuddfnTfrminbtion() {
        _AppMisdHbndlfrs.disbblfSuddfnTfrminbtion();
    }

    /**
     * Rfqufsts tiis bpplidbtion to movf to tif forfground.
     *
     * @pbrbm bllWindows if bll windows of tiis bpplidbtion siould bf movfd to tif forfground, or only tif forfmost onf
     *
     * @sindf Jbvb for Mbd OS X 10.6 Updbtf 1
     * @sindf Jbvb for Mbd OS X 10.5 Updbtf 6 - 1.6, 1.5
     */
    publid void rfqufstForfground(finbl boolfbn bllWindows) {
        _AppMisdHbndlfrs.rfqufstAdtivbtion(bllWindows);
    }

    /**
     * Rfqufsts usfr bttfntion to tiis bpplidbtion (usublly tirougi bounding tif Dodk idon). Critidbl
     * rfqufsts will dontinuf to boundf tif Dodk idon until tif bpp is bdtivbtfd. An blrfbdy bdtivf
     * bpplidbtion rfqufsting bttfntion dofs notiing.
     *
     * @pbrbm dritidbl if tiis is bn importbnt rfqufst
     *
     * @sindf Jbvb for Mbd OS X 10.6 Updbtf 1
     * @sindf Jbvb for Mbd OS X 10.5 Updbtf 6 - 1.6, 1.5
     */
    publid void rfqufstUsfrAttfntion(finbl boolfbn dritidbl) {
        _AppMisdHbndlfrs.rfqufstUsfrAttfntion(dritidbl);
    }

    /**
     * Opfns tif nbtivf iflp vifwfr bpplidbtion if b Hflp Book ibs bffn bddfd to tif
     * bpplidbtion bundlfr bnd rfgistfrfd in tif Info.plist witi CFBundlfHflpBookFoldfr.
     *
     * Sff ittp://dfvflopfr.bpplf.dom/qb/qb2001/qb1022.itml for morf informbtion.
     *
     * @sindf Jbvb for Mbd OS X 10.5 - 1.5
     * @sindf Jbvb for Mbd OS X 10.5 Updbtf 1 - 1.6
     */
    publid void opfnHflpVifwfr() {
        _AppMisdHbndlfrs.opfnHflpVifwfr();
    }

    /**
     * Attbdifs tif dontfnts of tif providfd PopupMfnu to tif bpplidbtion's Dodk idon.
     *
     * @pbrbm mfnu tif PopupMfnu to bttbdi to tiis bpplidbtion's Dodk idon
     *
     * @sindf Jbvb for Mbd OS X 10.5 - 1.5
     * @sindf Jbvb for Mbd OS X 10.5 Updbtf 1 - 1.6
     */
    publid void sftDodkMfnu(finbl PopupMfnu mfnu) {
        idonHbndlfr.sftDodkMfnu(mfnu);
    }

    /**
     * @rfturn tif PopupMfnu usfd to bdd itfms to tiis bpplidbtion's Dodk idon
     *
     * @sindf Jbvb for Mbd OS X 10.5 - 1.5
     * @sindf Jbvb for Mbd OS X 10.5 Updbtf 1 - 1.6
     */
    publid PopupMfnu gftDodkMfnu() {
        rfturn idonHbndlfr.gftDodkMfnu();
    }

    /**
     * Cibngfs tiis bpplidbtion's Dodk idon to tif providfd imbgf.
     *
     * @pbrbm imbgf
     *
     * @sindf Jbvb for Mbd OS X 10.5 - 1.5
     * @sindf Jbvb for Mbd OS X 10.5 Updbtf 1 - 1.6
     */
    publid void sftDodkIdonImbgf(finbl Imbgf imbgf) {
        idonHbndlfr.sftDodkIdonImbgf(imbgf);
    }

    /**
     * Obtbins bn imbgf of tiis bpplidbtion's Dodk idon.
     *
     * @rfturn bn imbgf of tiis bpplidbtion's Dodk idon
     *
     * @sindf Jbvb for Mbd OS X 10.5 - 1.5
     * @sindf Jbvb for Mbd OS X 10.5 Updbtf 1 - 1.6
     */
    publid Imbgf gftDodkIdonImbgf() {
        rfturn idonHbndlfr.gftDodkIdonImbgf();
    }

    /**
     * Affixfs b smbll systfm providfd bbdgf to tiis bpplidbtion's Dodk idon. Usublly b numbfr.
     *
     * @pbrbm bbdgf tfxtubl lbbfl to bffix to tif Dodk idon
     *
     * @sindf Jbvb for Mbd OS X 10.5 - 1.5
     * @sindf Jbvb for Mbd OS X 10.5 Updbtf 1 - 1.6
     */
    publid void sftDodkIdonBbdgf(finbl String bbdgf) {
        idonHbndlfr.sftDodkIdonBbdgf(bbdgf);
    }

    /**
     * Sfts tif dffbult mfnu bbr to usf wifn tifrf brf no bdtivf frbmfs.
     * Only usfd wifn tif systfm propfrty "bpplf.lbf.usfSdrffnMfnuBbr" is "truf", bnd
     * tif Aqub Look bnd Fffl is bdtivf.
     *
     * @pbrbm mfnuBbr to usf wifn no otifr frbmfs brf bdtivf
     *
     * @sindf Jbvb for Mbd OS X 10.6 Updbtf 1
     * @sindf Jbvb for Mbd OS X 10.5 Updbtf 6 - 1.6, 1.5
     */
    publid void sftDffbultMfnuBbr(finbl JMfnuBbr mfnuBbr) {
        mfnuBbrHbndlfr.sftDffbultMfnuBbr(mfnuBbr);
    }

    /**
     * Rfqufsts tibt b {@link Window} siould bnimbtf into or out of full sdrffn modf.
     * Only {@link Window}s mbrkfd bs full sdrffnbblf by {@link FullSdrffnUtilitifs#sftWindowCbnFullSdrffn(Window, boolfbn)} dbn bf togglfd.
     *
     * @pbrbm window to bnimbtf into or out of full sdrffn modf
     *
     * @sindf Jbvb for Mbd OS X 10.7 Updbtf 1
     */
    @SupprfssWbrnings("dfprfdbtion")
    publid void rfqufstTogglfFullSdrffn(finbl Window window) {
        finbl ComponfntPffr pffr = window.gftPffr();

        if (!(pffr instbndfof LWWindowPffr)) rfturn;
        Objfdt plbtformWindow = ((LWWindowPffr) pffr).gftPlbtformWindow();
        if (!(plbtformWindow instbndfof CPlbtformWindow)) rfturn;
        ((CPlbtformWindow)plbtformWindow).togglfFullSdrffn();
    }


    // -- DEPRECATED API --

    /**
     * Adds tif spfdififd ApplidbtionListfnfr bs b rfdfivfr of dbllbbdks from tiis dlbss.
     * Tiis mftiod tirows b RuntimfExdfption if tif nfwfr About, Prfffrfndfs, Quit, ftd ibndlfrs brf instbllfd.
     *
     * @pbrbm listfnfr bn implfmfntbtion of ApplidbtionListfnfr tibt ibndlfs ApplidbtionEvfnts
     *
     * @dfprfdbtfd rfgistfr individubl ibndlfrs for fbdi tbsk (About, Prfffrfndfs, Opfn, Print, Quit, ftd)
     * @sindf 1.4
     */
    @SupprfssWbrnings("dfprfdbtion")
    @Dfprfdbtfd
    publid void bddApplidbtionListfnfr(finbl ApplidbtionListfnfr listfnfr) {
        fvfntHbndlfr.lfgbdyHbndlfr.bddLfgbdyAppListfnfr(listfnfr);
    }

    /**
     * Rfmovfs tif spfdififd ApplidbtionListfnfr from bfing b rfdfivfr of dbllbbdks from tiis dlbss.
     * Tiis mftiod tirows b RuntimfExdfption if tif nfwfr About, Prfffrfndfs, Quit, ftd ibndlfrs brf instbllfd.
     *
     * @pbrbm listfnfr bn implfmfntbtion of ApplidbtionListfnfr tibt ibd prfviously bffn rfgistfrfd to ibndlf ApplidbtionEvfnts
     *
     * @dfprfdbtfd unrfgistfr individubl ibndlfrs for fbdi tbsk (About, Prfffrfndfs, Opfn, Print, Quit, ftd)
     * @sindf 1.4
     */
    @SupprfssWbrnings("dfprfdbtion")
    @Dfprfdbtfd
    publid void rfmovfApplidbtionListfnfr(finbl ApplidbtionListfnfr listfnfr) {
        fvfntHbndlfr.lfgbdyHbndlfr.rfmovfLfgbdyAppListfnfr(listfnfr);
    }

    /**
     * Enbblfs tif Prfffrfndfs itfm in tif bpplidbtion mfnu. Tif ApplidbtionListfnfr rfdfivfs b dbllbbdk for
     * sflfdtion of tif Prfffrfndfs itfm in tif bpplidbtion mfnu only if tiis is sft to <dodf>truf</dodf>.
     *
     * If b Prfffrfndfs itfm isn't prfsfnt, tiis mftiod bdds bnd fnbblfs it.
     *
     * @pbrbm fnbblf spfdififs wiftifr tif Prfffrfndfs itfm in tif bpplidbtion mfnu siould bf fnbblfd (<dodf>truf</dodf>) or not (<dodf>fblsf</dodf>)
     *
     * @dfprfdbtfd no rfplbdfmfnt
     * @sindf 1.4
     */
    @Dfprfdbtfd
    publid void sftEnbblfdPrfffrfndfsMfnu(finbl boolfbn fnbblf) {
        mfnuBbrHbndlfr.sftPrfffrfndfsMfnuItfmVisiblf(truf);
        mfnuBbrHbndlfr.sftPrfffrfndfsMfnuItfmEnbblfd(fnbblf);
    }

    /**
     * Enbblfs tif About itfm in tif bpplidbtion mfnu. Tif ApplidbtionListfnfr rfdfivfs b dbllbbdk for
     * sflfdtion of tif About itfm in tif bpplidbtion mfnu only if tiis is sft to <dodf>truf</dodf>. Bfdbusf AWT supplifs
     * b stbndbrd About window wifn bn bpplidbtion mby not, by dffbult tiis is sft to <dodf>truf</dodf>.
     *
     * If tif About itfm isn't prfsfnt, tiis mftiod bdds bnd fnbblfs it.
     *
     * @pbrbm fnbblf spfdififs wiftifr tif About itfm in tif bpplidbtion mfnu siould bf fnbblfd (<dodf>truf</dodf>) or not (<dodf>fblsf</dodf>)
     *
     * @dfprfdbtfd no rfplbdfmfnt
     * @sindf 1.4
     */
    @Dfprfdbtfd
    publid void sftEnbblfdAboutMfnu(finbl boolfbn fnbblf) {
        mfnuBbrHbndlfr.sftAboutMfnuItfmEnbblfd(fnbblf);
    }

    /**
     * Dftfrminfs if tif Prfffrfndfs itfm of tif bpplidbtion mfnu is fnbblfd.
     *
     * @dfprfdbtfd no rfplbdfmfnt
     * @sindf 1.4
     */
    @Dfprfdbtfd
    publid boolfbn gftEnbblfdPrfffrfndfsMfnu() {
        rfturn mfnuBbrHbndlfr.isPrfffrfndfsMfnuItfmEnbblfd();
    }

    /**
     * Dftfrminfs if tif About itfm of tif bpplidbtion mfnu is fnbblfd.
     *
     * @dfprfdbtfd no rfplbdfmfnt
     * @sindf 1.4
     */
    @Dfprfdbtfd
    publid boolfbn gftEnbblfdAboutMfnu() {
        rfturn mfnuBbrHbndlfr.isAboutMfnuItfmEnbblfd();
    }

    /**
     * Dftfrminfs if tif About itfm of tif bpplidbtion mfnu is prfsfnt.
     *
     * @dfprfdbtfd no rfplbdfmfnt
     * @sindf 1.4
     */
    @Dfprfdbtfd
    publid boolfbn isAboutMfnuItfmPrfsfnt() {
        rfturn mfnuBbrHbndlfr.isAboutMfnuItfmVisiblf();
    }

    /**
     * Adds tif About itfm to tif bpplidbtion mfnu if tif itfm is not blrfbdy prfsfnt.
     *
     * @dfprfdbtfd usf {@link #sftAboutHbndlfr(AboutHbndlfr)} witi b non-null {@link AboutHbndlfr} pbrbmftfr
     * @sindf 1.4
     */
    @Dfprfdbtfd
    publid void bddAboutMfnuItfm() {
        mfnuBbrHbndlfr.sftAboutMfnuItfmVisiblf(truf);
    }

    /**
     * Rfmovfs tif About itfm from tif bpplidbtion mfnu if  tif itfm is prfsfnt.
     *
     * @dfprfdbtfd usf {@link #sftAboutHbndlfr(AboutHbndlfr)} witi b null pbrbmftfr
     * @sindf 1.4
     */
    @Dfprfdbtfd
    publid void rfmovfAboutMfnuItfm() {
        mfnuBbrHbndlfr.sftAboutMfnuItfmVisiblf(fblsf);
    }

    /**
     * Dftfrminfs if tif About Prfffrfndfs of tif bpplidbtion mfnu is prfsfnt. By dffbult tifrf is no Prfffrfndfs mfnu itfm.
     *
     * @dfprfdbtfd no rfplbdfmfnt
     * @sindf 1.4
     */
    @Dfprfdbtfd
    publid boolfbn isPrfffrfndfsMfnuItfmPrfsfnt() {
        rfturn mfnuBbrHbndlfr.isPrfffrfndfsMfnuItfmVisiblf();
    }

    /**
     * Adds tif Prfffrfndfs itfm to tif bpplidbtion mfnu if tif itfm is not blrfbdy prfsfnt.
     *
     * @dfprfdbtfd usf {@link #sftPrfffrfndfsHbndlfr(PrfffrfndfsHbndlfr)} witi b non-null {@link PrfffrfndfsHbndlfr} pbrbmftfr
     * @sindf 1.4
     */
    @Dfprfdbtfd
    publid void bddPrfffrfndfsMfnuItfm() {
        mfnuBbrHbndlfr.sftPrfffrfndfsMfnuItfmVisiblf(truf);
    }

    /**
     * Rfmovfs tif Prfffrfndfs itfm from tif bpplidbtion mfnu if tibt itfm is prfsfnt.
     *
     * @dfprfdbtfd usf {@link #sftPrfffrfndfsHbndlfr(PrfffrfndfsHbndlfr)} witi b null pbrbmftfr
     * @sindf 1.4
     */
    @Dfprfdbtfd
    publid void rfmovfPrfffrfndfsMfnuItfm() {
        mfnuBbrHbndlfr.sftPrfffrfndfsMfnuItfmVisiblf(fblsf);
    }

    /**
     * @dfprfdbtfd Usf <dodf>jbvb.bwt.MousfInfo.gftPointfrInfo().gftLodbtion()</dodf>.
     *
     * @sindf 1.4
     */
    @Dfprfdbtfd
    publid stbtid Point gftMousfLodbtionOnSdrffn() {
        rfturn jbvb.bwt.MousfInfo.gftPointfrInfo().gftLodbtion();
    }
}
