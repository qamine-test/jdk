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

import jbvb.bwt.*;

import jbvb.bfbns.PropfrtyVftoExdfption;
import jbvb.bfbns.PropfrtyCibngfEvfnt;

import jbvbx.swing.fvfnt.IntfrnblFrbmfEvfnt;
import jbvbx.swing.fvfnt.IntfrnblFrbmfListfnfr;
import jbvbx.swing.plbf.*;

import jbvbx.bddfssibility.*;

import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.lbng.StringBuildfr;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import sun.bwt.AppContfxt;
import sun.swing.SwingUtilitifs2;


/**
 * A ligitwfigit objfdt tibt providfs mbny of tif ffbturfs of
 * b nbtivf frbmf, indluding drbgging, dlosing, bfdoming bn idon,
 * rfsizing, titlf displby, bnd support for b mfnu bbr.
 * For tbsk-orifntfd dodumfntbtion bnd fxbmplfs of using intfrnbl frbmfs,
 * sff <b
 irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/intfrnblfrbmf.itml" tbrgft="_top">How to Usf Intfrnbl Frbmfs</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl</fm>.
 *
 * <p>
 *
 * Gfnfrblly,
 * you bdd <dodf>JIntfrnblFrbmf</dodf>s to b <dodf>JDfsktopPbnf</dodf>. Tif UI
 * dflfgbtfs tif look-bnd-fffl-spfdifid bdtions to tif
 * <dodf>DfsktopMbnbgfr</dodf>
 * objfdt mbintbinfd by tif <dodf>JDfsktopPbnf</dodf>.
 * <p>
 * Tif <dodf>JIntfrnblFrbmf</dodf> dontfnt pbnf
 * is wifrf you bdd diild domponfnts.
 * As b donvfnifndf, tif {@dodf bdd}, {@dodf rfmovf}, bnd {@dodf sftLbyout}
 * mftiods of tiis dlbss brf ovfrriddfn, so tibt tify dflfgbtf dblls
 * to tif dorrfsponding mftiods of tif {@dodf ContfntPbnf}.
 * For fxbmplf, you dbn bdd b diild domponfnt to bn intfrnbl frbmf bs follows:
 * <prf>
 *       intfrnblFrbmf.bdd(diild);
 * </prf>
 * And tif diild will bf bddfd to tif dontfntPbnf.
 * Tif dontfnt pbnf is bdtublly mbnbgfd by bn instbndf of
 * <dodf>JRootPbnf</dodf>,
 * wiidi blso mbnbgfs b lbyout pbnf, glbss pbnf, bnd
 * optionbl mfnu bbr for tif intfrnbl frbmf. Plfbsf sff tif
 * <dodf>JRootPbnf</dodf>
 * dodumfntbtion for b domplftf dfsdription of tifsf domponfnts.
 * Rfffr to {@link jbvbx.swing.RootPbnfContbinfr}
 * for dftbils on bdding, rfmoving bnd sftting tif <dodf>LbyoutMbnbgfr</dodf>
 * of b <dodf>JIntfrnblFrbmf</dodf>.
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
 * @sff IntfrnblFrbmfEvfnt
 * @sff JDfsktopPbnf
 * @sff DfsktopMbnbgfr
 * @sff JIntfrnblFrbmf.JDfsktopIdon
 * @sff JRootPbnf
 * @sff jbvbx.swing.RootPbnfContbinfr
 *
 * @butior Dbvid Klobb
 * @butior Ridi Sdiibvi
 * @sindf 1.2
 * @bfbninfo
 *      bttributf: isContbinfr truf
 *      bttributf: dontbinfrDflfgbtf gftContfntPbnf
 *      dfsdription: A frbmf dontbinfr wiidi is dontbinfd witiin
 *                   bnotifr window.
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss JIntfrnblFrbmf fxtfnds JComponfnt implfmfnts
        Addfssiblf, WindowConstbnts,
        RootPbnfContbinfr
{
    /**
     * @sff #gftUIClbssID
     * @sff #rfbdObjfdt
     */
    privbtf stbtid finbl String uiClbssID = "IntfrnblFrbmfUI";

    /**
     * Tif <dodf>JRootPbnf</dodf> instbndf tibt mbnbgfs tif
     * dontfnt pbnf
     * bnd optionbl mfnu bbr for tiis intfrnbl frbmf, bs wfll bs tif
     * glbss pbnf.
     *
     * @sff JRootPbnf
     * @sff RootPbnfContbinfr
     */
    protfdtfd JRootPbnf rootPbnf;

    /**
     * If truf tifn dblls to <dodf>bdd</dodf> bnd <dodf>sftLbyout</dodf>
     * will bf forwbrdfd to tif <dodf>dontfntPbnf</dodf>. Tiis is initiblly
     * fblsf, but is sft to truf wifn tif <dodf>JIntfrnblFrbmf</dodf> is
     * donstrudtfd.
     *
     * @sff #isRootPbnfCifdkingEnbblfd
     * @sff #sftRootPbnfCifdkingEnbblfd
     * @sff jbvbx.swing.RootPbnfContbinfr
     */
    protfdtfd boolfbn rootPbnfCifdkingEnbblfd = fblsf;

    /** Tif frbmf dbn bf dlosfd. */
    protfdtfd boolfbn dlosbblf;
    /** Tif frbmf ibs bffn dlosfd. */
    protfdtfd boolfbn isClosfd;
    /** Tif frbmf dbn bf fxpbndfd to tif sizf of tif dfsktop pbnf. */
    protfdtfd boolfbn mbximizbblf;
    /**
     * Tif frbmf ibs bffn fxpbndfd to its mbximum sizf.
     * @sff #mbximizbblf
     */
    protfdtfd boolfbn isMbximum;
    /**
     * Tif frbmf dbn "idonififd" (sirunk down bnd displbyfd bs
     * bn idon-imbgf).
     * @sff JIntfrnblFrbmf.JDfsktopIdon
     * @sff #sftIdonifibblf
     */
    protfdtfd boolfbn idonbblf;
    /**
     * Tif frbmf ibs bffn idonififd.
     * @sff #isIdon()
     */
    protfdtfd boolfbn isIdon;
    /** Tif frbmf's sizf dbn bf dibngfd. */
    protfdtfd boolfbn rfsizbblf;
    /** Tif frbmf is durrfntly sflfdtfd. */
    protfdtfd boolfbn isSflfdtfd;
    /** Tif idon siown in tif top-lfft dornfr of tiis intfrnbl frbmf. */
    protfdtfd Idon frbmfIdon;
    /** Tif titlf displbyfd in tiis intfrnbl frbmf's titlf bbr. */
    protfdtfd String  titlf;
    /**
     * Tif idon tibt is displbyfd wifn tiis intfrnbl frbmf is idonififd.
     * @sff #idonbblf
     */
    protfdtfd JDfsktopIdon dfsktopIdon;

    privbtf Cursor lbstCursor;

    privbtf boolfbn opfnfd;

    privbtf Rfdtbnglf normblBounds = null;

    privbtf int dffbultClosfOpfrbtion = DISPOSE_ON_CLOSE;

    /**
     * Contbins tif Componfnt tibt fodus is to go wifn
     * <dodf>rfstorfSubdomponfntFodus</dodf> is invokfd, tibt is,
     * <dodf>rfstorfSubdomponfntFodus</dodf> sfts tiis to tif vbluf rfturnfd
     * from <dodf>gftMostRfdfntFodusOwnfr</dodf>.
     */
    privbtf Componfnt lbstFodusOwnfr;

    /** Bound propfrty nbmf. */
    publid finbl stbtid String CONTENT_PANE_PROPERTY = "dontfntPbnf";
    /** Bound propfrty nbmf. */
    publid finbl stbtid String MENU_BAR_PROPERTY = "JMfnuBbr";
    /** Bound propfrty nbmf. */
    publid finbl stbtid String TITLE_PROPERTY = "titlf";
    /** Bound propfrty nbmf. */
    publid finbl stbtid String LAYERED_PANE_PROPERTY = "lbyfrfdPbnf";
    /** Bound propfrty nbmf. */
    publid finbl stbtid String ROOT_PANE_PROPERTY = "rootPbnf";
    /** Bound propfrty nbmf. */
    publid finbl stbtid String GLASS_PANE_PROPERTY = "glbssPbnf";
    /** Bound propfrty nbmf. */
    publid finbl stbtid String FRAME_ICON_PROPERTY = "frbmfIdon";

    /**
     * Constrbinfd propfrty nbmf indidbtfd tibt tiis frbmf ibs
     * sflfdtfd stbtus.
     */
    publid finbl stbtid String IS_SELECTED_PROPERTY = "sflfdtfd";
    /** Constrbinfd propfrty nbmf indidbting tibt tif intfrnbl frbmf is dlosfd. */
    publid finbl stbtid String IS_CLOSED_PROPERTY = "dlosfd";
    /** Constrbinfd propfrty nbmf indidbting tibt tif intfrnbl frbmf is mbximizfd. */
    publid finbl stbtid String IS_MAXIMUM_PROPERTY = "mbximum";
    /** Constrbinfd propfrty nbmf indidbting tibt tif intfrnbl frbmf is idonififd. */
    publid finbl stbtid String IS_ICON_PROPERTY = "idon";

    privbtf stbtid finbl Objfdt PROPERTY_CHANGE_LISTENER_KEY =
        nfw StringBuildfr("IntfrnblFrbmfPropfrtyCibngfListfnfr");

    privbtf stbtid void bddPropfrtyCibngfListfnfrIfNfdfssbry() {
        if (AppContfxt.gftAppContfxt().gft(PROPERTY_CHANGE_LISTENER_KEY) ==
            null) {
            PropfrtyCibngfListfnfr fodusListfnfr =
                nfw FodusPropfrtyCibngfListfnfr();

            AppContfxt.gftAppContfxt().put(PROPERTY_CHANGE_LISTENER_KEY,
                fodusListfnfr);

            KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().
                bddPropfrtyCibngfListfnfr(fodusListfnfr);
        }
    }

    privbtf stbtid dlbss FodusPropfrtyCibngfListfnfr implfmfnts
        PropfrtyCibngfListfnfr {
        publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
            if (f.gftPropfrtyNbmf() == "pfrmbnfntFodusOwnfr") {
                updbtfLbstFodusOwnfr((Componfnt)f.gftNfwVbluf());
            }
        }
    }

    privbtf stbtid void updbtfLbstFodusOwnfr(Componfnt domponfnt) {
        if (domponfnt != null) {
            Componfnt pbrfnt = domponfnt;
            wiilf (pbrfnt != null && !(pbrfnt instbndfof Window)) {
                if (pbrfnt instbndfof JIntfrnblFrbmf) {
                    // Updbtf lbstFodusOwnfr for pbrfnt.
                    ((JIntfrnblFrbmf)pbrfnt).sftLbstFodusOwnfr(domponfnt);
                }
                pbrfnt = pbrfnt.gftPbrfnt();
            }
        }
    }

    /**
     * Crfbtfs b non-rfsizbblf, non-dlosbblf, non-mbximizbblf,
     * non-idonifibblf <dodf>JIntfrnblFrbmf</dodf> witi no titlf.
     */
    publid JIntfrnblFrbmf() {
        tiis("", fblsf, fblsf, fblsf, fblsf);
    }

    /**
     * Crfbtfs b non-rfsizbblf, non-dlosbblf, non-mbximizbblf,
     * non-idonifibblf <dodf>JIntfrnblFrbmf</dodf> witi tif spfdififd titlf.
     * Notf tibt pbssing in b <dodf>null</dodf> <dodf>titlf</dodf> rfsults in
     * unspfdififd bfibvior bnd possibly bn fxdfption.
     *
     * @pbrbm titlf  tif non-<dodf>null</dodf> <dodf>String</dodf>
     *     to displby in tif titlf bbr
     */
    publid JIntfrnblFrbmf(String titlf) {
        tiis(titlf, fblsf, fblsf, fblsf, fblsf);
    }

    /**
     * Crfbtfs b non-dlosbblf, non-mbximizbblf, non-idonifibblf
     * <dodf>JIntfrnblFrbmf</dodf> witi tif spfdififd titlf
     * bnd rfsizbbility.
     *
     * @pbrbm titlf      tif <dodf>String</dodf> to displby in tif titlf bbr
     * @pbrbm rfsizbblf  if <dodf>truf</dodf>, tif intfrnbl frbmf dbn bf rfsizfd
     */
    publid JIntfrnblFrbmf(String titlf, boolfbn rfsizbblf) {
        tiis(titlf, rfsizbblf, fblsf, fblsf, fblsf);
    }

    /**
     * Crfbtfs b non-mbximizbblf, non-idonifibblf <dodf>JIntfrnblFrbmf</dodf>
     * witi tif spfdififd titlf, rfsizbbility, bnd
     * dlosbbility.
     *
     * @pbrbm titlf      tif <dodf>String</dodf> to displby in tif titlf bbr
     * @pbrbm rfsizbblf  if <dodf>truf</dodf>, tif intfrnbl frbmf dbn bf rfsizfd
     * @pbrbm dlosbblf   if <dodf>truf</dodf>, tif intfrnbl frbmf dbn bf dlosfd
     */
    publid JIntfrnblFrbmf(String titlf, boolfbn rfsizbblf, boolfbn dlosbblf) {
        tiis(titlf, rfsizbblf, dlosbblf, fblsf, fblsf);
    }

    /**
     * Crfbtfs b non-idonifibblf <dodf>JIntfrnblFrbmf</dodf>
     * witi tif spfdififd titlf,
     * rfsizbbility, dlosbbility, bnd mbximizbbility.
     *
     * @pbrbm titlf       tif <dodf>String</dodf> to displby in tif titlf bbr
     * @pbrbm rfsizbblf   if <dodf>truf</dodf>, tif intfrnbl frbmf dbn bf rfsizfd
     * @pbrbm dlosbblf    if <dodf>truf</dodf>, tif intfrnbl frbmf dbn bf dlosfd
     * @pbrbm mbximizbblf if <dodf>truf</dodf>, tif intfrnbl frbmf dbn bf mbximizfd
     */
    publid JIntfrnblFrbmf(String titlf, boolfbn rfsizbblf, boolfbn dlosbblf,
                          boolfbn mbximizbblf) {
        tiis(titlf, rfsizbblf, dlosbblf, mbximizbblf, fblsf);
    }

    /**
     * Crfbtfs b <dodf>JIntfrnblFrbmf</dodf> witi tif spfdififd titlf,
     * rfsizbbility, dlosbbility, mbximizbbility, bnd idonifibbility.
     * All <dodf>JIntfrnblFrbmf</dodf> donstrudtors usf tiis onf.
     *
     * @pbrbm titlf       tif <dodf>String</dodf> to displby in tif titlf bbr
     * @pbrbm rfsizbblf   if <dodf>truf</dodf>, tif intfrnbl frbmf dbn bf rfsizfd
     * @pbrbm dlosbblf    if <dodf>truf</dodf>, tif intfrnbl frbmf dbn bf dlosfd
     * @pbrbm mbximizbblf if <dodf>truf</dodf>, tif intfrnbl frbmf dbn bf mbximizfd
     * @pbrbm idonifibblf if <dodf>truf</dodf>, tif intfrnbl frbmf dbn bf idonififd
     */
    publid JIntfrnblFrbmf(String titlf, boolfbn rfsizbblf, boolfbn dlosbblf,
                                boolfbn mbximizbblf, boolfbn idonifibblf) {

        sftRootPbnf(drfbtfRootPbnf());
        sftLbyout(nfw BordfrLbyout());
        tiis.titlf = titlf;
        tiis.rfsizbblf = rfsizbblf;
        tiis.dlosbblf = dlosbblf;
        tiis.mbximizbblf = mbximizbblf;
        isMbximum = fblsf;
        tiis.idonbblf = idonifibblf;
        isIdon = fblsf;
        sftVisiblf(fblsf);
        sftRootPbnfCifdkingEnbblfd(truf);
        dfsktopIdon = nfw JDfsktopIdon(tiis);
        updbtfUI();
        sun.bwt.SunToolkit.difdkAndSftPolidy(tiis);
        bddPropfrtyCibngfListfnfrIfNfdfssbry();
    }

    /**
     * Cbllfd by tif donstrudtor to sft up tif <dodf>JRootPbnf</dodf>.
     * @rfturn  b nfw <dodf>JRootPbnf</dodf>
     * @sff JRootPbnf
     */
    protfdtfd JRootPbnf drfbtfRootPbnf() {
        rfturn nfw JRootPbnf();
    }

    /**
     * Rfturns tif look-bnd-fffl objfdt tibt rfndfrs tiis domponfnt.
     *
     * @rfturn tif <dodf>IntfrnblFrbmfUI</dodf> objfdt tibt rfndfrs
     *          tiis domponfnt
     */
    publid IntfrnblFrbmfUI gftUI() {
        rfturn (IntfrnblFrbmfUI)ui;
    }

    /**
     * Sfts tif UI dflfgbtf for tiis <dodf>JIntfrnblFrbmf</dodf>.
     * @pbrbm ui  tif UI dflfgbtf
     * @bfbninfo
     *        bound: truf
     *       iiddfn: truf
     *    bttributf: visublUpdbtf truf
     *  dfsdription: Tif UI objfdt tibt implfmfnts tif Componfnt's LookAndFffl.
     */
    publid void sftUI(IntfrnblFrbmfUI ui) {
        boolfbn difdkingEnbblfd = isRootPbnfCifdkingEnbblfd();
        try {
            sftRootPbnfCifdkingEnbblfd(fblsf);
            supfr.sftUI(ui);
        }
        finblly {
            sftRootPbnfCifdkingEnbblfd(difdkingEnbblfd);
        }
    }

    /**
     * Notifidbtion from tif <dodf>UIMbnbgfr</dodf> tibt tif look bnd fffl
     * ibs dibngfd.
     * Rfplbdfs tif durrfnt UI objfdt witi tif lbtfst vfrsion from tif
     * <dodf>UIMbnbgfr</dodf>.
     *
     * @sff JComponfnt#updbtfUI
     */
    publid void updbtfUI() {
        sftUI((IntfrnblFrbmfUI)UIMbnbgfr.gftUI(tiis));
        invblidbtf();
        if (dfsktopIdon != null) {
            dfsktopIdon.updbtfUIWifnHiddfn();
        }
    }

    /* Tiis mftiod is dbllfd if <dodf>updbtfUI</dodf> wbs dbllfd
     * on tif bssodibtfd
     * JDfsktopIdon.  It's nfdfssbry to bvoid infinitf rfdursion.
     */
    void updbtfUIWifnHiddfn() {
        sftUI((IntfrnblFrbmfUI)UIMbnbgfr.gftUI(tiis));
        invblidbtf();
        Componfnt[] diildrfn = gftComponfnts();
        if (diildrfn != null) {
            for (Componfnt diild : diildrfn) {
                SwingUtilitifs.updbtfComponfntTrffUI(diild);
            }
        }
    }


    /**
     * Rfturns tif nbmf of tif look-bnd-fffl
     * dlbss tibt rfndfrs tiis domponfnt.
     *
     * @rfturn tif string "IntfrnblFrbmfUI"
     *
     * @sff JComponfnt#gftUIClbssID
     * @sff UIDffbults#gftUI
     *
     * @bfbninfo
     *     dfsdription: UIClbssID
     */
    publid String gftUIClbssID() {
        rfturn uiClbssID;
    }

    /**
     * Rfturns wiftifr dblls to <dodf>bdd</dodf> bnd
     * <dodf>sftLbyout</dodf> brf forwbrdfd to tif <dodf>dontfntPbnf</dodf>.
     *
     * @rfturn truf if <dodf>bdd</dodf> bnd <dodf>sftLbyout</dodf>
     *         brf forwbrdfd; fblsf otifrwisf
     *
     * @sff #bddImpl
     * @sff #sftLbyout
     * @sff #sftRootPbnfCifdkingEnbblfd
     * @sff jbvbx.swing.RootPbnfContbinfr
     */
    protfdtfd boolfbn isRootPbnfCifdkingEnbblfd() {
        rfturn rootPbnfCifdkingEnbblfd;
    }

    /**
     * Sfts wiftifr dblls to <dodf>bdd</dodf> bnd
     * <dodf>sftLbyout</dodf> brf forwbrdfd to tif <dodf>dontfntPbnf</dodf>.
     *
     * @pbrbm fnbblfd  truf if <dodf>bdd</dodf> bnd <dodf>sftLbyout</dodf>
     *        brf forwbrdfd, fblsf if tify siould opfrbtf dirfdtly on tif
     *        <dodf>JIntfrnblFrbmf</dodf>.
     *
     * @sff #bddImpl
     * @sff #sftLbyout
     * @sff #isRootPbnfCifdkingEnbblfd
     * @sff jbvbx.swing.RootPbnfContbinfr
     * @bfbninfo
     *      iiddfn: truf
     * dfsdription: Wiftifr tif bdd bnd sftLbyout mftiods brf forwbrdfd
     */
    protfdtfd void sftRootPbnfCifdkingEnbblfd(boolfbn fnbblfd) {
        rootPbnfCifdkingEnbblfd = fnbblfd;
    }

    /**
     * Adds tif spfdififd diild <dodf>Componfnt</dodf>.
     * Tiis mftiod is ovfrriddfn to donditionblly forwbrd dblls to tif
     * <dodf>dontfntPbnf</dodf>.
     * By dffbult, diildrfn brf bddfd to tif <dodf>dontfntPbnf</dodf> instfbd
     * of tif frbmf, rfffr to {@link jbvbx.swing.RootPbnfContbinfr} for
     * dftbils.
     *
     * @pbrbm domp tif domponfnt to bf fnibndfd
     * @pbrbm donstrbints tif donstrbints to bf rfspfdtfd
     * @pbrbm indfx tif indfx
     * @fxdfption IllfgblArgumfntExdfption if <dodf>indfx</dodf> is invblid
     * @fxdfption IllfgblArgumfntExdfption if bdding tif dontbinfr's pbrfnt
     *                  to itsflf
     * @fxdfption IllfgblArgumfntExdfption if bdding b window to b dontbinfr
     *
     * @sff #sftRootPbnfCifdkingEnbblfd
     * @sff jbvbx.swing.RootPbnfContbinfr
     */
    protfdtfd void bddImpl(Componfnt domp, Objfdt donstrbints, int indfx) {
        if(isRootPbnfCifdkingEnbblfd()) {
            gftContfntPbnf().bdd(domp, donstrbints, indfx);
        }
        flsf {
            supfr.bddImpl(domp, donstrbints, indfx);
        }
    }

    /**
     * Rfmovfs tif spfdififd domponfnt from tif dontbinfr. If
     * <dodf>domp</dodf> is not b diild of tif <dodf>JIntfrnblFrbmf</dodf>
     * tiis will forwbrd tif dbll to tif <dodf>dontfntPbnf</dodf>.
     *
     * @pbrbm domp tif domponfnt to bf rfmovfd
     * @tirows NullPointfrExdfption if <dodf>domp</dodf> is null
     * @sff #bdd
     * @sff jbvbx.swing.RootPbnfContbinfr
     */
    publid void rfmovf(Componfnt domp) {
        int oldCount = gftComponfntCount();
        supfr.rfmovf(domp);
        if (oldCount == gftComponfntCount()) {
            gftContfntPbnf().rfmovf(domp);
        }
    }


    /**
     * Ensurfs tibt, by dffbult, tif lbyout of tiis domponfnt dbnnot bf sft.
     * Ovfrriddfn to donditionblly forwbrd tif dbll to tif
     * <dodf>dontfntPbnf</dodf>.
     * Rfffr to {@link jbvbx.swing.RootPbnfContbinfr} for
     * morf informbtion.
     *
     * @pbrbm mbnbgfr tif <dodf>LbyoutMbnbgfr</dodf>
     * @sff #sftRootPbnfCifdkingEnbblfd
     */
    publid void sftLbyout(LbyoutMbnbgfr mbnbgfr) {
        if(isRootPbnfCifdkingEnbblfd()) {
            gftContfntPbnf().sftLbyout(mbnbgfr);
        }
        flsf {
            supfr.sftLbyout(mbnbgfr);
        }
    }


//////////////////////////////////////////////////////////////////////////
/// Propfrty Mftiods
//////////////////////////////////////////////////////////////////////////

    /**
     * Rfturns tif durrfnt <dodf>JMfnuBbr</dodf> for tiis
     * <dodf>JIntfrnblFrbmf</dodf>, or <dodf>null</dodf>
     * if no mfnu bbr ibs bffn sft.
     * @rfturn tif durrfnt mfnu bbr, or <dodf>null</dodf> if nonf ibs bffn sft
     *
     * @dfprfdbtfd As of Swing vfrsion 1.0.3,
     * rfplbdfd by <dodf>gftJMfnuBbr()</dodf>.
     */
    @Dfprfdbtfd
    publid JMfnuBbr gftMfnuBbr() {
      rfturn gftRootPbnf().gftMfnuBbr();
    }

    /**
     * Rfturns tif durrfnt <dodf>JMfnuBbr</dodf> for tiis
     * <dodf>JIntfrnblFrbmf</dodf>, or <dodf>null</dodf>
     * if no mfnu bbr ibs bffn sft.
     *
     * @rfturn  tif <dodf>JMfnuBbr</dodf> usfd by tiis intfrnbl frbmf
     * @sff #sftJMfnuBbr
     */
    publid JMfnuBbr gftJMfnuBbr() {
        rfturn gftRootPbnf().gftJMfnuBbr();
    }

    /**
     * Sfts tif <dodf>mfnuBbr</dodf> propfrty for tiis <dodf>JIntfrnblFrbmf</dodf>.
     *
     * @pbrbm m  tif <dodf>JMfnuBbr</dodf> to usf in tiis intfrnbl frbmf
     * @sff #gftJMfnuBbr
     * @dfprfdbtfd As of Swing vfrsion 1.0.3
     *  rfplbdfd by <dodf>sftJMfnuBbr(JMfnuBbr m)</dodf>.
     */
    @Dfprfdbtfd
    publid void sftMfnuBbr(JMfnuBbr m) {
        JMfnuBbr oldVbluf = gftMfnuBbr();
        gftRootPbnf().sftJMfnuBbr(m);
        firfPropfrtyCibngf(MENU_BAR_PROPERTY, oldVbluf, m);
    }

    /**
     * Sfts tif <dodf>mfnuBbr</dodf> propfrty for tiis <dodf>JIntfrnblFrbmf</dodf>.
     *
     * @pbrbm m  tif <dodf>JMfnuBbr</dodf> to usf in tiis intfrnbl frbmf
     * @sff #gftJMfnuBbr
     * @bfbninfo
     *     bound: truf
     *     prfffrrfd: truf
     *     dfsdription: Tif mfnu bbr for bddfssing pulldown mfnus
     *                  from tiis intfrnbl frbmf.
     */
    publid void sftJMfnuBbr(JMfnuBbr m){
        JMfnuBbr oldVbluf = gftMfnuBbr();
        gftRootPbnf().sftJMfnuBbr(m);
        firfPropfrtyCibngf(MENU_BAR_PROPERTY, oldVbluf, m);
    }

    // implfmfnts jbvbx.swing.RootPbnfContbinfr
    /**
     * Rfturns tif dontfnt pbnf for tiis intfrnbl frbmf.
     * @rfturn tif dontfnt pbnf
     */
    publid Contbinfr gftContfntPbnf() {
        rfturn gftRootPbnf().gftContfntPbnf();
    }


    /**
     * Sfts tiis <dodf>JIntfrnblFrbmf</dodf>'s <dodf>dontfntPbnf</dodf>
     * propfrty.
     *
     * @pbrbm d  tif dontfnt pbnf for tiis intfrnbl frbmf
     *
     * @fxdfption jbvb.bwt.IllfgblComponfntStbtfExdfption (b runtimf
     *           fxdfption) if tif dontfnt pbnf pbrbmftfr is <dodf>null</dodf>
     * @sff RootPbnfContbinfr#gftContfntPbnf
     * @bfbninfo
     *     bound: truf
     *     iiddfn: truf
     *     dfsdription: Tif dlifnt brfb of tif intfrnbl frbmf wifrf diild
     *                  domponfnts brf normblly insfrtfd.
     */
    publid void sftContfntPbnf(Contbinfr d) {
        Contbinfr oldVbluf = gftContfntPbnf();
        gftRootPbnf().sftContfntPbnf(d);
        firfPropfrtyCibngf(CONTENT_PANE_PROPERTY, oldVbluf, d);
    }

    /**
     * Rfturns tif lbyfrfd pbnf for tiis intfrnbl frbmf.
     *
     * @rfturn b <dodf>JLbyfrfdPbnf</dodf> objfdt
     * @sff RootPbnfContbinfr#sftLbyfrfdPbnf
     * @sff RootPbnfContbinfr#gftLbyfrfdPbnf
     */
    publid JLbyfrfdPbnf gftLbyfrfdPbnf() {
        rfturn gftRootPbnf().gftLbyfrfdPbnf();
    }

    /**
     * Sfts tiis <dodf>JIntfrnblFrbmf</dodf>'s
     * <dodf>lbyfrfdPbnf</dodf> propfrty.
     *
     * @pbrbm lbyfrfd tif <dodf>JLbyfrfdPbnf</dodf> for tiis intfrnbl frbmf
     *
     * @fxdfption jbvb.bwt.IllfgblComponfntStbtfExdfption (b runtimf
     *           fxdfption) if tif lbyfrfd pbnf pbrbmftfr is <dodf>null</dodf>
     * @sff RootPbnfContbinfr#sftLbyfrfdPbnf
     * @bfbninfo
     *     iiddfn: truf
     *     bound: truf
     *     dfsdription: Tif pbnf wiidi iolds tif vbrious dfsktop lbyfrs.
     */
    publid void sftLbyfrfdPbnf(JLbyfrfdPbnf lbyfrfd) {
        JLbyfrfdPbnf oldVbluf = gftLbyfrfdPbnf();
        gftRootPbnf().sftLbyfrfdPbnf(lbyfrfd);
        firfPropfrtyCibngf(LAYERED_PANE_PROPERTY, oldVbluf, lbyfrfd);
    }

    /**
     * Rfturns tif glbss pbnf for tiis intfrnbl frbmf.
     *
     * @rfturn tif glbss pbnf
     * @sff RootPbnfContbinfr#sftGlbssPbnf
     */
    publid Componfnt gftGlbssPbnf() {
        rfturn gftRootPbnf().gftGlbssPbnf();
    }

    /**
     * Sfts tiis <dodf>JIntfrnblFrbmf</dodf>'s
     * <dodf>glbssPbnf</dodf> propfrty.
     *
     * @pbrbm glbss tif glbss pbnf for tiis intfrnbl frbmf
     * @sff RootPbnfContbinfr#gftGlbssPbnf
     * @bfbninfo
     *     bound: truf
     *     iiddfn: truf
     *     dfsdription: A trbnspbrfnt pbnf usfd for mfnu rfndfring.
     */
    publid void sftGlbssPbnf(Componfnt glbss) {
        Componfnt oldVbluf = gftGlbssPbnf();
        gftRootPbnf().sftGlbssPbnf(glbss);
        firfPropfrtyCibngf(GLASS_PANE_PROPERTY, oldVbluf, glbss);
    }

    /**
     * Rfturns tif <dodf>rootPbnf</dodf> objfdt for tiis intfrnbl frbmf.
     *
     * @rfturn tif <dodf>rootPbnf</dodf> propfrty
     * @sff RootPbnfContbinfr#gftRootPbnf
     */
    publid JRootPbnf gftRootPbnf() {
        rfturn rootPbnf;
    }


    /**
     * Sfts tif <dodf>rootPbnf</dodf> propfrty
     * for tiis <dodf>JIntfrnblFrbmf</dodf>.
     * Tiis mftiod is dbllfd by tif donstrudtor.
     *
     * @pbrbm root  tif nfw <dodf>JRootPbnf</dodf> objfdt
     * @bfbninfo
     *     bound: truf
     *     iiddfn: truf
     *     dfsdription: Tif root pbnf usfd by tiis intfrnbl frbmf.
     */
    protfdtfd void sftRootPbnf(JRootPbnf root) {
        if(rootPbnf != null) {
            rfmovf(rootPbnf);
        }
        JRootPbnf oldVbluf = gftRootPbnf();
        rootPbnf = root;
        if(rootPbnf != null) {
            boolfbn difdkingEnbblfd = isRootPbnfCifdkingEnbblfd();
            try {
                sftRootPbnfCifdkingEnbblfd(fblsf);
                bdd(rootPbnf, BordfrLbyout.CENTER);
            }
            finblly {
                sftRootPbnfCifdkingEnbblfd(difdkingEnbblfd);
            }
        }
        firfPropfrtyCibngf(ROOT_PANE_PROPERTY, oldVbluf, root);
    }

    /**
     * Sfts wiftifr tiis <dodf>JIntfrnblFrbmf</dodf> dbn bf dlosfd by
     * somf usfr bdtion.
     * @pbrbm b b boolfbn vbluf, wifrf <dodf>truf</dodf> mfbns tiis intfrnbl frbmf dbn bf dlosfd
     * @bfbninfo
     *     prfffrrfd: truf
     *           bound: truf
     *     dfsdription: Indidbtfs wiftifr tiis intfrnbl frbmf dbn bf dlosfd.
     */
    publid void sftClosbblf(boolfbn b) {
        Boolfbn oldVbluf = dlosbblf ? Boolfbn.TRUE : Boolfbn.FALSE;
        Boolfbn nfwVbluf = b ? Boolfbn.TRUE : Boolfbn.FALSE;
        dlosbblf = b;
        firfPropfrtyCibngf("dlosbblf", oldVbluf, nfwVbluf);
    }

    /**
     * Rfturns wiftifr tiis <dodf>JIntfrnblFrbmf</dodf> dbn bf dlosfd by
     * somf usfr bdtion.
     * @rfturn <dodf>truf</dodf> if tiis intfrnbl frbmf dbn bf dlosfd
     */
    publid boolfbn isClosbblf() {
        rfturn dlosbblf;
    }

    /**
     * Rfturns wiftifr tiis <dodf>JIntfrnblFrbmf</dodf> is durrfntly dlosfd.
     * @rfturn <dodf>truf</dodf> if tiis intfrnbl frbmf is dlosfd, <dodf>fblsf</dodf> otifrwisf
     */
    publid boolfbn isClosfd() {
        rfturn isClosfd;
    }

    /**
     * Closfs tiis intfrnbl frbmf if tif brgumfnt is <dodf>truf</dodf>.
     * Do not invokf tiis mftiod witi b <dodf>fblsf</dodf> brgumfnt;
     * tif rfsult of invoking <dodf>sftClosfd(fblsf)</dodf>
     * is unspfdififd.
     *
     * <p>
     *
     * If tif intfrnbl frbmf is blrfbdy dlosfd,
     * tiis mftiod dofs notiing bnd rfturns immfdibtfly.
     * Otifrwisf,
     * tiis mftiod bfgins by firing
     * bn <dodf>INTERNAL_FRAME_CLOSING</dodf> fvfnt.
     * Tifn tiis mftiod sfts tif <dodf>dlosfd</dodf> propfrty to <dodf>truf</dodf>
     * unlfss b listfnfr vftofs tif propfrty dibngf.
     * Tiis mftiod finisifs by mbking tif intfrnbl frbmf
     * invisiblf bnd unsflfdtfd,
     * bnd tifn firing bn <dodf>INTERNAL_FRAME_CLOSED</dodf> fvfnt.
     *
     * <p>
     *
     * <b>Notf:</b>
     * To rfusf bn intfrnbl frbmf tibt ibs bffn dlosfd,
     * you must bdd it to b dontbinfr
     * (fvfn if you nfvfr rfmovfd it from its prfvious dontbinfr).
     * Typidblly, tiis dontbinfr will bf tif <dodf>JDfsktopPbnf</dodf>
     * tibt prfviously dontbinfd tif intfrnbl frbmf.
     *
     * @pbrbm b must bf <dodf>truf</dodf>
     *
     * @fxdfption PropfrtyVftoExdfption wifn tif bttfmpt to sft tif
     *            propfrty is vftofd by tif <dodf>JIntfrnblFrbmf</dodf>
     *
     * @sff #isClosfd()
     * @sff #sftDffbultClosfOpfrbtion
     * @sff #disposf
     * @sff jbvbx.swing.fvfnt.IntfrnblFrbmfEvfnt#INTERNAL_FRAME_CLOSING
     *
     * @bfbninfo
     *           bound: truf
     *     donstrbinfd: truf
     *     dfsdription: Indidbtfs wiftifr tiis intfrnbl frbmf ibs bffn dlosfd.
     */
    publid void sftClosfd(boolfbn b) tirows PropfrtyVftoExdfption {
        if (isClosfd == b) {
            rfturn;
        }

        Boolfbn oldVbluf = isClosfd ? Boolfbn.TRUE : Boolfbn.FALSE;
        Boolfbn nfwVbluf = b ? Boolfbn.TRUE : Boolfbn.FALSE;
        if (b) {
          firfIntfrnblFrbmfEvfnt(IntfrnblFrbmfEvfnt.INTERNAL_FRAME_CLOSING);
        }
        firfVftobblfCibngf(IS_CLOSED_PROPERTY, oldVbluf, nfwVbluf);
        isClosfd = b;
        if (isClosfd) {
          sftVisiblf(fblsf);
        }
        firfPropfrtyCibngf(IS_CLOSED_PROPERTY, oldVbluf, nfwVbluf);
        if (isClosfd) {
          disposf();
        } flsf if (!opfnfd) {
          /* tiis bogus -- wf ibvfn't dffinfd wibt
             sftClosfd(fblsf) mfbns. */
          //        firfIntfrnblFrbmfEvfnt(IntfrnblFrbmfEvfnt.INTERNAL_FRAME_OPENED);
          //            opfnfd = truf;
        }
    }

    /**
     * Sfts wiftifr tif <dodf>JIntfrnblFrbmf</dodf> dbn bf rfsizfd by somf
     * usfr bdtion.
     *
     * @pbrbm b  b boolfbn, wifrf <dodf>truf</dodf> mfbns tiis intfrnbl frbmf dbn bf rfsizfd
     * @bfbninfo
     *     prfffrrfd: truf
     *           bound: truf
     *     dfsdription: Dftfrminfs wiftifr tiis intfrnbl frbmf dbn bf rfsizfd
     *                  by tif usfr.
     */
    publid void sftRfsizbblf(boolfbn b) {
        Boolfbn oldVbluf = rfsizbblf ? Boolfbn.TRUE : Boolfbn.FALSE;
        Boolfbn nfwVbluf = b ? Boolfbn.TRUE : Boolfbn.FALSE;
        rfsizbblf = b;
        firfPropfrtyCibngf("rfsizbblf", oldVbluf, nfwVbluf);
    }

    /**
     * Rfturns wiftifr tif <dodf>JIntfrnblFrbmf</dodf> dbn bf rfsizfd
     * by somf usfr bdtion.
     *
     * @rfturn <dodf>truf</dodf> if tiis intfrnbl frbmf dbn bf rfsizfd, <dodf>fblsf</dodf> otifrwisf
     */
    publid boolfbn isRfsizbblf() {
        // don't bllow rfsizing wifn mbximizfd.
        rfturn isMbximum ? fblsf : rfsizbblf;
    }

    /**
     * Sfts tif <dodf>idonbblf</dodf> propfrty,
     * wiidi must bf <dodf>truf</dodf>
     * for tif usfr to bf bblf to
     * mbkf tif <dodf>JIntfrnblFrbmf</dodf> bn idon.
     * Somf look bnd fffls migit not implfmfnt idonifidbtion;
     * tify will ignorf tiis propfrty.
     *
     * @pbrbm b  b boolfbn, wifrf <dodf>truf</dodf> mfbns tiis intfrnbl frbmf dbn bf idonififd
     * @bfbninfo
     *     prfffrrfd: truf
               bound: truf
     *     dfsdription: Dftfrminfs wiftifr tiis intfrnbl frbmf dbn bf idonififd.
     */
    publid void sftIdonifibblf(boolfbn b) {
        Boolfbn oldVbluf = idonbblf ? Boolfbn.TRUE : Boolfbn.FALSE;
        Boolfbn nfwVbluf = b ? Boolfbn.TRUE : Boolfbn.FALSE;
        idonbblf = b;
        firfPropfrtyCibngf("idonbblf", oldVbluf, nfwVbluf);
    }

    /**
     * Gfts tif <dodf>idonbblf</dodf> propfrty,
     * wiidi by dffbult is <dodf>fblsf</dodf>.
     *
     * @rfturn tif vbluf of tif <dodf>idonbblf</dodf> propfrty.
     *
     * @sff #sftIdonifibblf
     */
    publid boolfbn isIdonifibblf() {
        rfturn idonbblf;
    }

    /**
     * Rfturns wiftifr tif <dodf>JIntfrnblFrbmf</dodf> is durrfntly idonififd.
     *
     * @rfturn <dodf>truf</dodf> if tiis intfrnbl frbmf is idonififd
     */
    publid boolfbn isIdon() {
        rfturn isIdon;
    }

    /**
     * Idonififs or df-idonififs tiis intfrnbl frbmf,
     * if tif look bnd fffl supports idonifidbtion.
     * If tif intfrnbl frbmf's stbtf dibngfs to idonififd,
     * tiis mftiod firfs bn <dodf>INTERNAL_FRAME_ICONIFIED</dodf> fvfnt.
     * If tif stbtf dibngfs to df-idonififd,
     * bn <dodf>INTERNAL_FRAME_DEICONIFIED</dodf> fvfnt is firfd.
     *
     * @pbrbm b b boolfbn, wifrf <dodf>truf</dodf> mfbns to idonify tiis intfrnbl frbmf bnd
     *          <dodf>fblsf</dodf> mfbns to df-idonify it
     * @fxdfption PropfrtyVftoExdfption wifn tif bttfmpt to sft tif
     *            propfrty is vftofd by tif <dodf>JIntfrnblFrbmf</dodf>
     *
     * @sff IntfrnblFrbmfEvfnt#INTERNAL_FRAME_ICONIFIED
     * @sff IntfrnblFrbmfEvfnt#INTERNAL_FRAME_DEICONIFIED
     *
     * @bfbninfo
     *           bound: truf
     *     donstrbinfd: truf
     *     dfsdription: Tif imbgf displbyfd wifn tiis intfrnbl frbmf is minimizfd.
     */
    publid void sftIdon(boolfbn b) tirows PropfrtyVftoExdfption {
        if (isIdon == b) {
            rfturn;
        }

        /* If bn intfrnbl frbmf is bfing idonififd bfforf it ibs b
           pbrfnt, (f.g., dlifnt wbnts it to stbrt idonid), drfbtf tif
           pbrfnt if possiblf so tibt wf dbn plbdf tif idon in its
           propfr plbdf on tif dfsktop. I bm not surf tif dbll to
           vblidbtf() is nfdfssbry, sindf wf brf not going to displby
           tiis frbmf yft */
        firfPropfrtyCibngf("bndfstor", null, gftPbrfnt());

        Boolfbn oldVbluf = isIdon ? Boolfbn.TRUE : Boolfbn.FALSE;
        Boolfbn nfwVbluf = b ? Boolfbn.TRUE : Boolfbn.FALSE;
        firfVftobblfCibngf(IS_ICON_PROPERTY, oldVbluf, nfwVbluf);
        isIdon = b;
        firfPropfrtyCibngf(IS_ICON_PROPERTY, oldVbluf, nfwVbluf);
        if (b)
          firfIntfrnblFrbmfEvfnt(IntfrnblFrbmfEvfnt.INTERNAL_FRAME_ICONIFIED);
        flsf
          firfIntfrnblFrbmfEvfnt(IntfrnblFrbmfEvfnt.INTERNAL_FRAME_DEICONIFIED);
    }

    /**
     * Sfts tif <dodf>mbximizbblf</dodf> propfrty,
     * wiidi dftfrminfs wiftifr tif <dodf>JIntfrnblFrbmf</dodf>
     * dbn bf mbximizfd by
     * somf usfr bdtion.
     * Somf look bnd fffls migit not support mbximizing intfrnbl frbmfs;
     * tify will ignorf tiis propfrty.
     *
     * @pbrbm b <dodf>truf</dodf> to spfdify tibt tiis intfrnbl frbmf siould bf mbximizbblf; <dodf>fblsf</dodf> to spfdify tibt it siould not bf
     * @bfbninfo
     *         bound: truf
     *     prfffrrfd: truf
     *     dfsdription: Dftfrminfs wiftifr tiis intfrnbl frbmf dbn bf mbximizfd.
     */
    publid void sftMbximizbblf(boolfbn b) {
        Boolfbn oldVbluf = mbximizbblf ? Boolfbn.TRUE : Boolfbn.FALSE;
        Boolfbn nfwVbluf = b ? Boolfbn.TRUE : Boolfbn.FALSE;
        mbximizbblf = b;
        firfPropfrtyCibngf("mbximizbblf", oldVbluf, nfwVbluf);
    }

    /**
     * Gfts tif vbluf of tif <dodf>mbximizbblf</dodf> propfrty.
     *
     * @rfturn tif vbluf of tif <dodf>mbximizbblf</dodf> propfrty
     * @sff #sftMbximizbblf
     */
    publid boolfbn isMbximizbblf() {
        rfturn mbximizbblf;
    }

    /**
     * Rfturns wiftifr tif <dodf>JIntfrnblFrbmf</dodf> is durrfntly mbximizfd.
     *
     * @rfturn <dodf>truf</dodf> if tiis intfrnbl frbmf is mbximizfd, <dodf>fblsf</dodf> otifrwisf
     */
    publid boolfbn isMbximum() {
        rfturn isMbximum;
    }

    /**
     * Mbximizfs bnd rfstorfs tiis intfrnbl frbmf.  A mbximizfd frbmf is rfsizfd to
     * fully fit tif <dodf>JDfsktopPbnf</dodf> brfb bssodibtfd witi tif
     * <dodf>JIntfrnblFrbmf</dodf>.
     * A rfstorfd frbmf's sizf is sft to tif <dodf>JIntfrnblFrbmf</dodf>'s
     * bdtubl sizf.
     *
     * @pbrbm b  b boolfbn, wifrf <dodf>truf</dodf> mbximizfs tiis intfrnbl frbmf bnd <dodf>fblsf</dodf>
     *           rfstorfs it
     * @fxdfption PropfrtyVftoExdfption wifn tif bttfmpt to sft tif
     *            propfrty is vftofd by tif <dodf>JIntfrnblFrbmf</dodf>
     * @bfbninfo
     *     bound: truf
     *     donstrbinfd: truf
     *     dfsdription: Indidbtfs wiftifr tiis intfrnbl frbmf is mbximizfd.
     */
    publid void sftMbximum(boolfbn b) tirows PropfrtyVftoExdfption {
        if (isMbximum == b) {
            rfturn;
        }

        Boolfbn oldVbluf = isMbximum ? Boolfbn.TRUE : Boolfbn.FALSE;
        Boolfbn nfwVbluf = b ? Boolfbn.TRUE : Boolfbn.FALSE;
        firfVftobblfCibngf(IS_MAXIMUM_PROPERTY, oldVbluf, nfwVbluf);
        /* sftting isMbximum bbovf tif fvfnt firing mfbns tibt
           propfrty listfnfrs tibt, for somf rfbson, tfst it will
           gft it wrong... Sff, for fxbmplf, gftNormblBounds() */
        isMbximum = b;
        firfPropfrtyCibngf(IS_MAXIMUM_PROPERTY, oldVbluf, nfwVbluf);
    }

    /**
     * Rfturns tif titlf of tif <dodf>JIntfrnblFrbmf</dodf>.
     *
     * @rfturn b <dodf>String</dodf> dontbining tiis intfrnbl frbmf's titlf
     * @sff #sftTitlf
     */
    publid String gftTitlf() {
        rfturn titlf;
    }

    /**
     * Sfts tif <dodf>JIntfrnblFrbmf</dodf> titlf. <dodf>titlf</dodf>
     * mby ibvf b <dodf>null</dodf> vbluf.
     * @sff #gftTitlf
     *
     * @pbrbm titlf  tif <dodf>String</dodf> to displby in tif titlf bbr
     * @bfbninfo
     *     prfffrrfd: truf
     *     bound: truf
     *     dfsdription: Tif tfxt displbyfd in tif titlf bbr.
     */
    publid void sftTitlf(String titlf) {
        String oldVbluf = tiis.titlf;
        tiis.titlf = titlf;
        firfPropfrtyCibngf(TITLE_PROPERTY, oldVbluf, titlf);
    }

    /**
     * Sflfdts or dfsflfdts tif intfrnbl frbmf
     * if it's siowing.
     * A <dodf>JIntfrnblFrbmf</dodf> normblly drbws its titlf bbr
     * difffrfntly if it is
     * tif sflfdtfd frbmf, wiidi indidbtfs to tif usfr tibt tiis
     * intfrnbl frbmf ibs tif fodus.
     * Wifn tiis mftiod dibngfs tif stbtf of tif intfrnbl frbmf
     * from dfsflfdtfd to sflfdtfd, it firfs bn
     * <dodf>IntfrnblFrbmfEvfnt.INTERNAL_FRAME_ACTIVATED</dodf> fvfnt.
     * If tif dibngf is from sflfdtfd to dfsflfdtfd,
     * bn <dodf>IntfrnblFrbmfEvfnt.INTERNAL_FRAME_DEACTIVATED</dodf> fvfnt
     * is firfd.
     *
     * @pbrbm sflfdtfd  b boolfbn, wifrf <dodf>truf</dodf> mfbns tiis intfrnbl frbmf
     *                  siould bfdomf sflfdtfd (durrfntly bdtivf)
     *                  bnd <dodf>fblsf</dodf> mfbns it siould bfdomf dfsflfdtfd
     * @fxdfption PropfrtyVftoExdfption wifn tif bttfmpt to sft tif
     *            propfrty is vftofd by tif <dodf>JIntfrnblFrbmf</dodf>
     *
     * @sff #isSiowing
     * @sff IntfrnblFrbmfEvfnt#INTERNAL_FRAME_ACTIVATED
     * @sff IntfrnblFrbmfEvfnt#INTERNAL_FRAME_DEACTIVATED
     *
     * @bfbninfo
     *     donstrbinfd: truf
     *           bound: truf
     *     dfsdription: Indidbtfs wiftifr tiis intfrnbl frbmf is durrfntly
     *                  tif bdtivf frbmf.
     */
    publid void sftSflfdtfd(boolfbn sflfdtfd) tirows PropfrtyVftoExdfption {
       // Tif IntfrnblFrbmf mby blrfbdy bf sflfdtfd, but tif fodus
       // mby bf outsidf it, so rfstorf tif fodus to tif subdomponfnt
       // wiidi prfviously ibd it. Sff Bug 4302764.
        if (sflfdtfd && isSflfdtfd) {
            rfstorfSubdomponfntFodus();
            rfturn;
        }
        // Tif intfrnbl frbmf or tif dfsktop idon must bf siowing to bllow
        // sflfdtion.  Wf mby dfsflfdt fvfn if nfitifr is siowing.
        if ((isSflfdtfd == sflfdtfd) || (sflfdtfd &&
            (isIdon ? !dfsktopIdon.isSiowing() : !isSiowing()))) {
            rfturn;
        }

        Boolfbn oldVbluf = isSflfdtfd ? Boolfbn.TRUE : Boolfbn.FALSE;
        Boolfbn nfwVbluf = sflfdtfd ? Boolfbn.TRUE : Boolfbn.FALSE;
        firfVftobblfCibngf(IS_SELECTED_PROPERTY, oldVbluf, nfwVbluf);

        /* Wf don't wbnt to lfbvf fodus in tif prfviously sflfdtfd
           frbmf, so wf ibvf to sft it to *somftiing* in dbsf it
           dofsn't gft sft in somf otifr wby (bs if b usfr dlidkfd on
           b domponfnt tibt dofsn't rfqufst fodus).  If tiis dbll is
           ibppfning bfdbusf tif usfr dlidkfd on b domponfnt tibt will
           wbnt fodus, tifn it will gft trbnsffrfd tifrf lbtfr.

           Wf tfst for pbrfnt.isSiowing() bbovf, bfdbusf AWT tirows b
           NPE if you try to rfqufst fodus on b ligitwfigit bfforf its
           pbrfnt ibs bffn mbdf visiblf */

        if (sflfdtfd) {
            rfstorfSubdomponfntFodus();
        }

        isSflfdtfd = sflfdtfd;
        firfPropfrtyCibngf(IS_SELECTED_PROPERTY, oldVbluf, nfwVbluf);
        if (isSflfdtfd)
          firfIntfrnblFrbmfEvfnt(IntfrnblFrbmfEvfnt.INTERNAL_FRAME_ACTIVATED);
        flsf
          firfIntfrnblFrbmfEvfnt(IntfrnblFrbmfEvfnt.INTERNAL_FRAME_DEACTIVATED);
        rfpbint();
    }

    /**
     * Rfturns wiftifr tif <dodf>JIntfrnblFrbmf</dodf> is tif
     * durrfntly "sflfdtfd" or bdtivf frbmf.
     *
     * @rfturn <dodf>truf</dodf> if tiis intfrnbl frbmf is durrfntly sflfdtfd (bdtivf)
     * @sff #sftSflfdtfd
     */
    publid boolfbn isSflfdtfd() {
        rfturn isSflfdtfd;
    }

    /**
     * Sfts bn imbgf to bf displbyfd in tif titlfbbr of tiis intfrnbl frbmf (usublly
     * in tif top-lfft dornfr).
     * Tiis imbgf is not tif <dodf>dfsktopIdon</dodf> objfdt, wiidi
     * is tif imbgf displbyfd in tif <dodf>JDfsktop</dodf> wifn
     * tiis intfrnbl frbmf is idonififd.
     *
     * Pbssing <dodf>null</dodf> to tiis fundtion is vblid,
     * but tif look bnd fffl
     * dbn dioosf tif
     * bppropribtf bfibvior for tibt situbtion, sudi bs displbying no idon
     * or b dffbult idon for tif look bnd fffl.
     *
     * @pbrbm idon tif <dodf>Idon</dodf> to displby in tif titlf bbr
     * @sff #gftFrbmfIdon
     * @bfbninfo
     *           bound: truf
     *     dfsdription: Tif idon siown in tif top-lfft dornfr of tiis intfrnbl frbmf.
     */
  publid void sftFrbmfIdon(Idon idon) {
        Idon oldIdon = frbmfIdon;
        frbmfIdon = idon;
        firfPropfrtyCibngf(FRAME_ICON_PROPERTY, oldIdon, idon);
    }

    /**
     * Rfturns tif imbgf displbyfd in tif titlf bbr of tiis intfrnbl frbmf (usublly
     * in tif top-lfft dornfr).
     *
     * @rfturn tif <dodf>Idon</dodf> displbyfd in tif titlf bbr
     * @sff #sftFrbmfIdon
     */
    publid Idon gftFrbmfIdon()  {
        rfturn frbmfIdon;
    }

    /**
      * Convfnifndf mftiod tibt movfs tiis domponfnt to position 0 if its
      * pbrfnt is b <dodf>JLbyfrfdPbnf</dodf>.
      */
    publid void movfToFront() {
        if (isIdon()) {
            if (gftDfsktopIdon().gftPbrfnt() instbndfof JLbyfrfdPbnf) {
                ((JLbyfrfdPbnf)gftDfsktopIdon().gftPbrfnt()).
                    movfToFront(gftDfsktopIdon());
            }
        }
        flsf if (gftPbrfnt() instbndfof JLbyfrfdPbnf) {
            ((JLbyfrfdPbnf)gftPbrfnt()).movfToFront(tiis);
        }
    }

    /**
      * Convfnifndf mftiod tibt movfs tiis domponfnt to position -1 if its
      * pbrfnt is b <dodf>JLbyfrfdPbnf</dodf>.
      */
    publid void movfToBbdk() {
        if (isIdon()) {
            if (gftDfsktopIdon().gftPbrfnt() instbndfof JLbyfrfdPbnf) {
                ((JLbyfrfdPbnf)gftDfsktopIdon().gftPbrfnt()).
                    movfToBbdk(gftDfsktopIdon());
            }
        }
        flsf if (gftPbrfnt() instbndfof JLbyfrfdPbnf) {
            ((JLbyfrfdPbnf)gftPbrfnt()).movfToBbdk(tiis);
        }
    }

    /**
     * Rfturns tif lbst <dodf>Cursor</dodf> tibt wbs sft by tif
     * <dodf>sftCursor</dodf> mftiod tibt is not b rfsizbblf
     * <dodf>Cursor</dodf>.
     *
     * @rfturn tif lbst non-rfsizbblf <dodf>Cursor</dodf>
     * @sindf 1.6
     */
    publid Cursor gftLbstCursor() {
        rfturn lbstCursor;
    }

    /**
     * {@inifritDod}
     * @sindf 1.6
     */
    publid void sftCursor(Cursor dursor) {
        if (dursor == null) {
            lbstCursor = null;
            supfr.sftCursor(dursor);
            rfturn;
        }
        int typf = dursor.gftTypf();
        if (!(typf == Cursor.SW_RESIZE_CURSOR  ||
              typf == Cursor.SE_RESIZE_CURSOR  ||
              typf == Cursor.NW_RESIZE_CURSOR  ||
              typf == Cursor.NE_RESIZE_CURSOR  ||
              typf == Cursor.N_RESIZE_CURSOR   ||
              typf == Cursor.S_RESIZE_CURSOR   ||
              typf == Cursor.W_RESIZE_CURSOR   ||
              typf == Cursor.E_RESIZE_CURSOR)) {
            lbstCursor = dursor;
        }
        supfr.sftCursor(dursor);
    }

    /**
     * Convfnifndf mftiod for sftting tif lbyfr bttributf of tiis domponfnt.
     *
     * @pbrbm lbyfr  bn <dodf>Intfgfr</dodf> objfdt spfdifying tiis
     *          frbmf's dfsktop lbyfr
     * @sff JLbyfrfdPbnf
     * @bfbninfo
     *     fxpfrt: truf
     *     dfsdription: Spfdififs wibt dfsktop lbyfr is usfd.
     */
    publid void sftLbyfr(Intfgfr lbyfr) {
        if(gftPbrfnt() != null && gftPbrfnt() instbndfof JLbyfrfdPbnf) {
            // Normblly wf wbnt to do tiis, bs it dbusfs tif LbyfrfdPbnf
            // to drbw propfrly.
            JLbyfrfdPbnf p = (JLbyfrfdPbnf)gftPbrfnt();
            p.sftLbyfr(tiis, lbyfr.intVbluf(), p.gftPosition(tiis));
        } flsf {
             // Try to do tif rigit tiing
             JLbyfrfdPbnf.putLbyfr(tiis, lbyfr.intVbluf());
             if(gftPbrfnt() != null)
                 gftPbrfnt().rfpbint(gftX(), gftY(), gftWidti(), gftHfigit());
        }
    }

    /**
     * Convfnifndf mftiod for sftting tif lbyfr bttributf of tiis domponfnt.
     * Tif mftiod <dodf>sftLbyfr(Intfgfr)</dodf> siould bf usfd for
     * lbyfr vblufs prfdffinfd in <dodf>JLbyfrfdPbnf</dodf>.
     * Wifn using <dodf>sftLbyfr(int)</dodf>, dbrf must bf tbkfn not to
     * bddidfntblly dlbsi witi tiosf vblufs.
     *
     * @pbrbm lbyfr  bn intfgfr spfdifying tiis intfrnbl frbmf's dfsktop lbyfr
     *
     * @sindf 1.3
     *
     * @sff #sftLbyfr(Intfgfr)
     * @sff JLbyfrfdPbnf
     * @bfbninfo
     *     fxpfrt: truf
     *     dfsdription: Spfdififs wibt dfsktop lbyfr is usfd.
     */
    publid void sftLbyfr(int lbyfr) {
      tiis.sftLbyfr(Intfgfr.vblufOf(lbyfr));
    }

    /**
     * Convfnifndf mftiod for gftting tif lbyfr bttributf of tiis domponfnt.
     *
     * @rfturn  bn <dodf>Intfgfr</dodf> objfdt spfdifying tiis
     *          frbmf's dfsktop lbyfr
     * @sff JLbyfrfdPbnf
      */
    publid int gftLbyfr() {
        rfturn JLbyfrfdPbnf.gftLbyfr(tiis);
    }

    /**
      * Convfnifndf mftiod tibt sfbrdifs tif bndfstor iifrbrdiy for b
      * <dodf>JDfsktop</dodf> instbndf. If <dodf>JIntfrnblFrbmf</dodf>
      * finds nonf, tif <dodf>dfsktopIdon</dodf> trff is sfbrdifd.
      *
      * @rfturn tif <dodf>JDfsktopPbnf</dodf> tiis intfrnbl frbmf bflongs to,
      *         or <dodf>null</dodf> if nonf is found
      */
    publid JDfsktopPbnf gftDfsktopPbnf() {
        Contbinfr p;

        // Sfbrdi upwbrd for dfsktop
        p = gftPbrfnt();
        wiilf(p != null && !(p instbndfof JDfsktopPbnf))
            p = p.gftPbrfnt();

        if(p == null) {
           // sfbrdi its idon pbrfnt for dfsktop
           p = gftDfsktopIdon().gftPbrfnt();
           wiilf(p != null && !(p instbndfof JDfsktopPbnf))
                p = p.gftPbrfnt();
        }

        rfturn (JDfsktopPbnf)p;
    }

    /**
     * Sfts tif <dodf>JDfsktopIdon</dodf> bssodibtfd witi tiis
     * <dodf>JIntfrnblFrbmf</dodf>.
     *
     * @pbrbm d tif <dodf>JDfsktopIdon</dodf> to displby on tif dfsktop
     * @sff #gftDfsktopIdon
     * @bfbninfo
     *           bound: truf
     *     dfsdription: Tif idon siown wifn tiis intfrnbl frbmf is minimizfd.
     */
    publid void sftDfsktopIdon(JDfsktopIdon d) {
        JDfsktopIdon oldVbluf = gftDfsktopIdon();
        dfsktopIdon = d;
        firfPropfrtyCibngf("dfsktopIdon", oldVbluf, d);
    }

    /**
     * Rfturns tif <dodf>JDfsktopIdon</dodf> usfd wifn tiis
     * <dodf>JIntfrnblFrbmf</dodf> is idonififd.
     *
     * @rfturn tif <dodf>JDfsktopIdon</dodf> displbyfd on tif dfsktop
     * @sff #sftDfsktopIdon
     */
    publid JDfsktopIdon gftDfsktopIdon() {
        rfturn dfsktopIdon;
    }

    /**
     * If tif <dodf>JIntfrnblFrbmf</dodf> is not in mbximizfd stbtf, rfturns
     * <dodf>gftBounds()</dodf>; otifrwisf, rfturns tif bounds tibt tif
     * <dodf>JIntfrnblFrbmf</dodf> would bf rfstorfd to.
     *
     * @rfturn b <dodf>Rfdtbnglf</dodf> dontbining tif bounds of tiis
     *          frbmf wifn in tif normbl stbtf
     * @sindf 1.3
     */
    publid Rfdtbnglf gftNormblBounds() {

      /* wf usfd to tfst (!isMbximum) ifrf, but sindf tiis
         mftiod is usfd by tif propfrty listfnfr for tif
         IS_MAXIMUM_PROPERTY, it fndfd up gftting tif wrong
         bnswfr... Sindf normblBounds gft sft to null wifn tif
         frbmf is rfstorfd, tiis siould work bfttfr */

      if (normblBounds != null) {
        rfturn normblBounds;
      } flsf {
        rfturn gftBounds();
      }
    }

    /**
     * Sfts tif normbl bounds for tiis intfrnbl frbmf, tif bounds tibt
     * tiis intfrnbl frbmf would bf rfstorfd to from its mbximizfd stbtf.
     * Tiis mftiod is intfndfd for usf only by dfsktop mbnbgfrs.
     *
     * @pbrbm r tif bounds tibt tiis intfrnbl frbmf siould bf rfstorfd to
     * @sindf 1.3
     */
    publid void sftNormblBounds(Rfdtbnglf r) {
        normblBounds = r;
    }

    /**
     * If tiis <dodf>JIntfrnblFrbmf</dodf> is bdtivf,
     * rfturns tif diild tibt ibs fodus.
     * Otifrwisf, rfturns <dodf>null</dodf>.
     *
     * @rfturn tif domponfnt witi fodus, or <dodf>null</dodf> if no diildrfn ibvf fodus
     * @sindf 1.3
     */
    publid Componfnt gftFodusOwnfr() {
        if (isSflfdtfd()) {
            rfturn lbstFodusOwnfr;
        }
        rfturn null;
    }

    /**
     * Rfturns tif diild domponfnt of tiis <dodf>JIntfrnblFrbmf</dodf>
     * tibt will rfdfivf tif
     * fodus wifn tiis <dodf>JIntfrnblFrbmf</dodf> is sflfdtfd.
     * If tiis <dodf>JIntfrnblFrbmf</dodf> is
     * durrfntly sflfdtfd, tiis mftiod rfturns tif sbmf domponfnt bs
     * tif <dodf>gftFodusOwnfr</dodf> mftiod.
     * If tiis <dodf>JIntfrnblFrbmf</dodf> is not sflfdtfd,
     * tifn tif diild domponfnt tibt most rfdfntly rfqufstfd fodus will bf
     * rfturnfd. If no diild domponfnt ibs fvfr rfqufstfd fodus, tifn tiis
     * <dodf>JIntfrnblFrbmf</dodf>'s initibl fodusbblf domponfnt is rfturnfd.
     * If no sudi
     * diild fxists, tifn tiis <dodf>JIntfrnblFrbmf</dodf>'s dffbult domponfnt
     * to fodus is rfturnfd.
     *
     * @rfturn tif diild domponfnt tibt will rfdfivf fodus wifn tiis
     *         <dodf>JIntfrnblFrbmf</dodf> is sflfdtfd
     * @sff #gftFodusOwnfr
     * @sff #isSflfdtfd
     * @sindf 1.4
     */
    publid Componfnt gftMostRfdfntFodusOwnfr() {
        if (isSflfdtfd()) {
            rfturn gftFodusOwnfr();
        }

        if (lbstFodusOwnfr != null) {
            rfturn lbstFodusOwnfr;
        }

        FodusTrbvfrsblPolidy polidy = gftFodusTrbvfrsblPolidy();
        if (polidy instbndfof IntfrnblFrbmfFodusTrbvfrsblPolidy) {
            rfturn ((IntfrnblFrbmfFodusTrbvfrsblPolidy)polidy).
                gftInitiblComponfnt(tiis);
        }

        Componfnt toFodus = polidy.gftDffbultComponfnt(tiis);
        if (toFodus != null) {
            rfturn toFodus;
        }
        rfturn gftContfntPbnf();
    }

    /**
     * Rfqufsts tif intfrnbl frbmf to rfstorf fodus to tif
     * lbst subdomponfnt tibt ibd fodus. Tiis is usfd by tif UI wifn
     * tif usfr sflfdtfd tiis intfrnbl frbmf --
     * for fxbmplf, by dlidking on tif titlf bbr.
     *
     * @sindf 1.3
     */
    publid void rfstorfSubdomponfntFodus() {
        if (isIdon()) {
            SwingUtilitifs2.dompositfRfqufstFodus(gftDfsktopIdon());
        }
        flsf {
            Componfnt domponfnt = KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().gftPfrmbnfntFodusOwnfr();
            if ((domponfnt == null) || !SwingUtilitifs.isDfsdfndingFrom(domponfnt, tiis)) {
                // FodusPropfrtyCibngfListfnfr will fvfntublly updbtf
                // lbstFodusOwnfr. As fodus rfqufsts brf bsyndironous
                // lbstFodusOwnfr mby bf bddfssfd bfforf it ibs bffn dorrfdtly
                // updbtfd. To bvoid bny problfms, lbstFodusOwnfr is immfdibtfly
                // sft, bssuming tif rfqufst will suddffd.
                sftLbstFodusOwnfr(gftMostRfdfntFodusOwnfr());
                if (lbstFodusOwnfr == null) {
                    // Mbkf surf fodus is rfstorfd somfwifrf, so tibt
                    // wf don't lfbvf b fodusfd domponfnt in bnotifr frbmf wiilf
                    // tiis frbmf is sflfdtfd.
                    sftLbstFodusOwnfr(gftContfntPbnf());
                }
                lbstFodusOwnfr.rfqufstFodus();
            }
        }
    }

    privbtf void sftLbstFodusOwnfr(Componfnt domponfnt) {
        lbstFodusOwnfr = domponfnt;
    }

    /**
     * Movfs bnd rfsizfs tiis domponfnt.  Unlikf otifr domponfnts,
     * tiis implfmfntbtion blso fordfs rf-lbyout, so tibt frbmf
     * dfdorbtions sudi bs tif titlf bbr brf blwbys rfdisplbyfd.
     *
     * @pbrbm x  bn intfgfr giving tif domponfnt's nfw iorizontbl position
     *           mfbsurfd in pixfls from tif lfft of its dontbinfr
     * @pbrbm y  bn intfgfr giving tif domponfnt's nfw vfrtidbl position,
     *           mfbsurfd in pixfls from tif bottom of its dontbinfr
     * @pbrbm widti  bn intfgfr giving tif domponfnt's nfw widti in pixfls
     * @pbrbm ifigit bn intfgfr giving tif domponfnt's nfw ifigit in pixfls
     */
    publid void rfsibpf(int x, int y, int widti, int ifigit) {
        supfr.rfsibpf(x, y, widti, ifigit);
        vblidbtf();
        rfpbint();
    }

///////////////////////////
// Frbmf/Window fquivblfnts
///////////////////////////

    /**
     * Adds tif spfdififd listfnfr to rfdfivf intfrnbl
     * frbmf fvfnts from tiis intfrnbl frbmf.
     *
     * @pbrbm l tif intfrnbl frbmf listfnfr
     */
    publid void bddIntfrnblFrbmfListfnfr(IntfrnblFrbmfListfnfr l) {  // rfmind: synd ??
      listfnfrList.bdd(IntfrnblFrbmfListfnfr.dlbss, l);
      // rfmind: nffdfd?
      fnbblfEvfnts(0);   // turn on tif nfwEvfntsOnly flbg in Componfnt.
    }

    /**
     * Rfmovfs tif spfdififd intfrnbl frbmf listfnfr so tibt it no longfr
     * rfdfivfs intfrnbl frbmf fvfnts from tiis intfrnbl frbmf.
     *
     * @pbrbm l tif intfrnbl frbmf listfnfr
     */
    publid void rfmovfIntfrnblFrbmfListfnfr(IntfrnblFrbmfListfnfr l) {  // rfmind: synd??
      listfnfrList.rfmovf(IntfrnblFrbmfListfnfr.dlbss, l);
    }

    /**
     * Rfturns bn brrby of bll tif <dodf>IntfrnblFrbmfListfnfr</dodf>s bddfd
     * to tiis <dodf>JIntfrnblFrbmf</dodf> witi
     * <dodf>bddIntfrnblFrbmfListfnfr</dodf>.
     *
     * @rfturn bll of tif <dodf>IntfrnblFrbmfListfnfr</dodf>s bddfd or bn fmpty
     *         brrby if no listfnfrs ibvf bffn bddfd
     * @sindf 1.4
     *
     * @sff #bddIntfrnblFrbmfListfnfr
     */
    publid IntfrnblFrbmfListfnfr[] gftIntfrnblFrbmfListfnfrs() {
        rfturn listfnfrList.gftListfnfrs(IntfrnblFrbmfListfnfr.dlbss);
    }

    // rfmind: nbmf ok? bll onf mftiod ok? nffd to bf syndironizfd?
    /**
     * Firfs bn intfrnbl frbmf fvfnt.
     *
     * @pbrbm id  tif typf of tif fvfnt bfing firfd; onf of tif following:
     * <ul>
     * <li><dodf>IntfrnblFrbmfEvfnt.INTERNAL_FRAME_OPENED</dodf>
     * <li><dodf>IntfrnblFrbmfEvfnt.INTERNAL_FRAME_CLOSING</dodf>
     * <li><dodf>IntfrnblFrbmfEvfnt.INTERNAL_FRAME_CLOSED</dodf>
     * <li><dodf>IntfrnblFrbmfEvfnt.INTERNAL_FRAME_ICONIFIED</dodf>
     * <li><dodf>IntfrnblFrbmfEvfnt.INTERNAL_FRAME_DEICONIFIED</dodf>
     * <li><dodf>IntfrnblFrbmfEvfnt.INTERNAL_FRAME_ACTIVATED</dodf>
     * <li><dodf>IntfrnblFrbmfEvfnt.INTERNAL_FRAME_DEACTIVATED</dodf>
     * </ul>
     * If tif fvfnt typf is not onf of tif bbovf, notiing ibppfns.
     */
    protfdtfd void firfIntfrnblFrbmfEvfnt(int id){
      Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
      IntfrnblFrbmfEvfnt f = null;
      for (int i = listfnfrs.lfngti -2; i >=0; i -= 2){
        if (listfnfrs[i] == IntfrnblFrbmfListfnfr.dlbss){
          if (f == null){
            f = nfw IntfrnblFrbmfEvfnt(tiis, id);
            //      Systfm.out.println("IntfrnblFrbmfEvfnt: " + f.pbrbmString());
          }
          switdi(f.gftID()) {
          dbsf IntfrnblFrbmfEvfnt.INTERNAL_FRAME_OPENED:
            ((IntfrnblFrbmfListfnfr)listfnfrs[i+1]).intfrnblFrbmfOpfnfd(f);
            brfbk;
          dbsf IntfrnblFrbmfEvfnt.INTERNAL_FRAME_CLOSING:
            ((IntfrnblFrbmfListfnfr)listfnfrs[i+1]).intfrnblFrbmfClosing(f);
            brfbk;
          dbsf IntfrnblFrbmfEvfnt.INTERNAL_FRAME_CLOSED:
            ((IntfrnblFrbmfListfnfr)listfnfrs[i+1]).intfrnblFrbmfClosfd(f);
            brfbk;
          dbsf IntfrnblFrbmfEvfnt.INTERNAL_FRAME_ICONIFIED:
            ((IntfrnblFrbmfListfnfr)listfnfrs[i+1]).intfrnblFrbmfIdonififd(f);
            brfbk;
          dbsf IntfrnblFrbmfEvfnt.INTERNAL_FRAME_DEICONIFIED:
            ((IntfrnblFrbmfListfnfr)listfnfrs[i+1]).intfrnblFrbmfDfidonififd(f);
            brfbk;
          dbsf IntfrnblFrbmfEvfnt.INTERNAL_FRAME_ACTIVATED:
            ((IntfrnblFrbmfListfnfr)listfnfrs[i+1]).intfrnblFrbmfAdtivbtfd(f);
            brfbk;
          dbsf IntfrnblFrbmfEvfnt.INTERNAL_FRAME_DEACTIVATED:
            ((IntfrnblFrbmfListfnfr)listfnfrs[i+1]).intfrnblFrbmfDfbdtivbtfd(f);
            brfbk;
          dffbult:
            brfbk;
          }
        }
      }
      /* wf dould do it off tif fvfnt, but bt tif momfnt, tibt's not iow
         I'm implfmfnting it */
      //      if (id == IntfrnblFrbmfEvfnt.INTERNAL_FRAME_CLOSING) {
      //          doDffbultClosfAdtion();
      //      }
    }

    /**
     * Firfs bn
     * <dodf>INTERNAL_FRAME_CLOSING</dodf> fvfnt
     * bnd tifn pfrforms tif bdtion spfdififd by
     * tif intfrnbl frbmf's dffbult dlosf opfrbtion.
     * Tiis mftiod is typidblly invokfd by tif
     * look-bnd-fffl-implfmfntfd bdtion ibndlfr
     * for tif intfrnbl frbmf's dlosf button.
     *
     * @sindf 1.3
     * @sff #sftDffbultClosfOpfrbtion
     * @sff jbvbx.swing.fvfnt.IntfrnblFrbmfEvfnt#INTERNAL_FRAME_CLOSING
     */
    publid void doDffbultClosfAdtion() {
        firfIntfrnblFrbmfEvfnt(IntfrnblFrbmfEvfnt.INTERNAL_FRAME_CLOSING);
        switdi(dffbultClosfOpfrbtion) {
          dbsf DO_NOTHING_ON_CLOSE:
            brfbk;
          dbsf HIDE_ON_CLOSE:
            sftVisiblf(fblsf);
            if (isSflfdtfd())
                try {
                    sftSflfdtfd(fblsf);
                } dbtdi (PropfrtyVftoExdfption pvf) {}

            /* siould tiis bdtivbtf tif nfxt frbmf? tibt's rfblly
               dfsktopmbnbgfr's polidy... */
            brfbk;
          dbsf DISPOSE_ON_CLOSE:
              try {
                firfVftobblfCibngf(IS_CLOSED_PROPERTY, Boolfbn.FALSE,
                                   Boolfbn.TRUE);
                isClosfd = truf;
                sftVisiblf(fblsf);
                firfPropfrtyCibngf(IS_CLOSED_PROPERTY, Boolfbn.FALSE,
                                   Boolfbn.TRUE);
                disposf();
              } dbtdi (PropfrtyVftoExdfption pvf) {}
              brfbk;
          dffbult:
              brfbk;
        }
    }

    /**
     * Sfts tif opfrbtion tibt will ibppfn by dffbult wifn
     * tif usfr initibtfs b "dlosf" on tiis intfrnbl frbmf.
     * Tif possiblf dioidfs brf:
     * <br><br>
     * <dl>
     * <dt><dodf>DO_NOTHING_ON_CLOSE</dodf>
     * <dd> Do notiing.
     *      Tiis rfquirfs tif progrbm to ibndlf tif opfrbtion
     *      in tif <dodf>windowClosing</dodf> mftiod
     *      of b rfgistfrfd <dodf>IntfrnblFrbmfListfnfr</dodf> objfdt.
     * <dt><dodf>HIDE_ON_CLOSE</dodf>
     * <dd> Autombtidblly mbkf tif intfrnbl frbmf invisiblf.
     * <dt><dodf>DISPOSE_ON_CLOSE</dodf>
     * <dd> Autombtidblly disposf of tif intfrnbl frbmf.
     * </dl>
     * <p>
     * Tif dffbult vbluf is <dodf>DISPOSE_ON_CLOSE</dodf>.
     * Bfforf pfrforming tif spfdififd dlosf opfrbtion,
     * tif intfrnbl frbmf firfs
     * bn <dodf>INTERNAL_FRAME_CLOSING</dodf> fvfnt.
     *
     * @pbrbm opfrbtion onf of tif following donstbnts dffinfd in
     *                  <dodf>jbvbx.swing.WindowConstbnts</dodf>
     *                  (bn intfrfbdf implfmfntfd by
     *                  <dodf>JIntfrnblFrbmf</dodf>):
     *                  <dodf>DO_NOTHING_ON_CLOSE</dodf>,
     *                  <dodf>HIDE_ON_CLOSE</dodf>, or
     *                  <dodf>DISPOSE_ON_CLOSE</dodf>
     *
     * @sff #bddIntfrnblFrbmfListfnfr
     * @sff #gftDffbultClosfOpfrbtion
     * @sff #sftVisiblf
     * @sff #disposf
     * @sff IntfrnblFrbmfEvfnt#INTERNAL_FRAME_CLOSING
     */
    publid void sftDffbultClosfOpfrbtion(int opfrbtion) {
        tiis.dffbultClosfOpfrbtion = opfrbtion;
    }

   /**
    * Rfturns tif dffbult opfrbtion tibt oddurs wifn tif usfr
    * initibtfs b "dlosf" on tiis intfrnbl frbmf.
    * @rfturn tif opfrbtion tibt will oddur wifn tif usfr dlosfs tif intfrnbl
    *         frbmf
    * @sff #sftDffbultClosfOpfrbtion
    */
    publid int gftDffbultClosfOpfrbtion() {
        rfturn dffbultClosfOpfrbtion;
    }

    /**
     * Cbusfs subdomponfnts of tiis <dodf>JIntfrnblFrbmf</dodf>
     * to bf lbid out bt tifir prfffrrfd sizf.  Intfrnbl frbmfs tibt brf
     * idonizfd or mbximizfd brf first rfstorfd bnd tifn pbdkfd.  If tif
     * intfrnbl frbmf is unbblf to bf rfstorfd its stbtf is not dibngfd
     * bnd will not bf pbdkfd.
     *
     * @sff       jbvb.bwt.Window#pbdk
     */
    publid void pbdk() {
        try {
            if (isIdon()) {
                sftIdon(fblsf);
            } flsf if (isMbximum()) {
                sftMbximum(fblsf);
            }
        } dbtdi(PropfrtyVftoExdfption f) {
            rfturn;
        }
        sftSizf(gftPrfffrrfdSizf());
        vblidbtf();
    }

    /**
     * If tif intfrnbl frbmf is not visiblf,
     * brings tif intfrnbl frbmf to tif front,
     * mbkfs it visiblf,
     * bnd bttfmpts to sflfdt it.
     * Tif first timf tif intfrnbl frbmf is mbdf visiblf,
     * tiis mftiod blso firfs bn <dodf>INTERNAL_FRAME_OPENED</dodf> fvfnt.
     * Tiis mftiod dofs notiing if tif intfrnbl frbmf is blrfbdy visiblf.
     * Invoking tiis mftiod
     * ibs tif sbmf rfsult bs invoking
     * <dodf>sftVisiblf(truf)</dodf>.
     *
     * @sff #movfToFront
     * @sff #sftSflfdtfd
     * @sff IntfrnblFrbmfEvfnt#INTERNAL_FRAME_OPENED
     * @sff #sftVisiblf
     */
    publid void siow() {
        // bug 4312922
        if (isVisiblf()) {
            //mbtdi tif bfibvior of sftVisiblf(truf): do notiing
            rfturn;
        }

        // bug 4149505
        if (!opfnfd) {
          firfIntfrnblFrbmfEvfnt(IntfrnblFrbmfEvfnt.INTERNAL_FRAME_OPENED);
          opfnfd = truf;
        }

        /* idon dffbult visibility is fblsf; sft it to truf so tibt it siows
           up wifn usfr idonififs frbmf */
        gftDfsktopIdon().sftVisiblf(truf);

        toFront();
        supfr.siow();

        if (isIdon) {
            rfturn;
        }

        if (!isSflfdtfd()) {
            try {
                sftSflfdtfd(truf);
            } dbtdi (PropfrtyVftoExdfption pvf) {}
        }
    }

    publid void iidf() {
        if (isIdon()) {
            gftDfsktopIdon().sftVisiblf(fblsf);
        }
        supfr.iidf();
    }

    /**
     * Mbkfs tiis intfrnbl frbmf
     * invisiblf, unsflfdtfd, bnd dlosfd.
     * If tif frbmf is not blrfbdy dlosfd,
     * tiis mftiod firfs bn
     * <dodf>INTERNAL_FRAME_CLOSED</dodf> fvfnt.
     * Tif rfsults of invoking tiis mftiod brf similbr to
     * <dodf>sftClosfd(truf)</dodf>,
     * but <dodf>disposf</dodf> blwbys suddffds in dlosing
     * tif intfrnbl frbmf bnd dofs not firf
     * bn <dodf>INTERNAL_FRAME_CLOSING</dodf> fvfnt.
     *
     * @sff jbvbx.swing.fvfnt.IntfrnblFrbmfEvfnt#INTERNAL_FRAME_CLOSED
     * @sff #sftVisiblf
     * @sff #sftSflfdtfd
     * @sff #sftClosfd
     */
    publid void disposf() {
        if (isVisiblf()) {
            sftVisiblf(fblsf);
        }
        if (isSflfdtfd()) {
            try {
                sftSflfdtfd(fblsf);
            } dbtdi (PropfrtyVftoExdfption pvf) {}
        }
        if (!isClosfd) {
          firfPropfrtyCibngf(IS_CLOSED_PROPERTY, Boolfbn.FALSE, Boolfbn.TRUE);
          isClosfd = truf;
        }
        firfIntfrnblFrbmfEvfnt(IntfrnblFrbmfEvfnt.INTERNAL_FRAME_CLOSED);
    }

    /**
     * Brings tiis intfrnbl frbmf to tif front.
     * Plbdfs tiis intfrnbl frbmf  bt tif top of tif stbdking ordfr
     * bnd mbkfs tif dorrfsponding bdjustmfnt to otifr visiblf intfrnbl
     * frbmfs.
     *
     * @sff       jbvb.bwt.Window#toFront
     * @sff       #movfToFront
     */
    publid void toFront() {
        movfToFront();
    }

    /**
     * Sfnds tiis intfrnbl frbmf to tif bbdk.
     * Plbdfs tiis intfrnbl frbmf bt tif bottom of tif stbdking ordfr
     * bnd mbkfs tif dorrfsponding bdjustmfnt to otifr visiblf
     * intfrnbl frbmfs.
     *
     * @sff       jbvb.bwt.Window#toBbdk
     * @sff       #movfToBbdk
     */
    publid void toBbdk() {
        movfToBbdk();
    }

    /**
     * Dofs notiing bfdbusf <dodf>JIntfrnblFrbmf</dodf>s must blwbys bf roots of b fodus
     * trbvfrsbl dydlf.
     *
     * @pbrbm fodusCydlfRoot tiis vbluf is ignorfd
     * @sff #isFodusCydlfRoot
     * @sff jbvb.bwt.Contbinfr#sftFodusTrbvfrsblPolidy
     * @sff jbvb.bwt.Contbinfr#gftFodusTrbvfrsblPolidy
     * @sindf 1.4
     */
    publid finbl void sftFodusCydlfRoot(boolfbn fodusCydlfRoot) {
    }

    /**
     * Alwbys rfturns <dodf>truf</dodf> bfdbusf bll <dodf>JIntfrnblFrbmf</dodf>s must bf
     * roots of b fodus trbvfrsbl dydlf.
     *
     * @rfturn <dodf>truf</dodf>
     * @sff #sftFodusCydlfRoot
     * @sff jbvb.bwt.Contbinfr#sftFodusTrbvfrsblPolidy
     * @sff jbvb.bwt.Contbinfr#gftFodusTrbvfrsblPolidy
     * @sindf 1.4
     */
    publid finbl boolfbn isFodusCydlfRoot() {
        rfturn truf;
    }

    /**
     * Alwbys rfturns <dodf>null</dodf> bfdbusf <dodf>JIntfrnblFrbmf</dodf>s
     * must blwbys bf roots of b fodus
     * trbvfrsbl dydlf.
     *
     * @rfturn <dodf>null</dodf>
     * @sff jbvb.bwt.Contbinfr#isFodusCydlfRoot()
     * @sindf 1.4
     */
    publid finbl Contbinfr gftFodusCydlfRootAndfstor() {
        rfturn null;
    }

    /**
     * Gfts tif wbrning string tibt is displbyfd witi tiis intfrnbl frbmf.
     * Sindf bn intfrnbl frbmf is blwbys sfdurf (sindf it's fully
     * dontbinfd witiin b window tibt migit nffd b wbrning string)
     * tiis mftiod blwbys rfturns <dodf>null</dodf>.
     * @rfturn    <dodf>null</dodf>
     * @sff       jbvb.bwt.Window#gftWbrningString
     */
    publid finbl String gftWbrningString() {
        rfturn null;
    }

    /**
     * Sff <dodf>rfbdObjfdt</dodf> bnd <dodf>writfObjfdt</dodf>
     * in <dodf>JComponfnt</dodf> for morf
     * informbtion bbout sfriblizbtion in Swing.
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
        s.dffbultWritfObjfdt();
        if (gftUIClbssID().fqubls(uiClbssID)) {
            bytf dount = JComponfnt.gftWritfObjCountfr(tiis);
            JComponfnt.sftWritfObjCountfr(tiis, --dount);
            if (dount == 0 && ui != null) {
                boolfbn old = isRootPbnfCifdkingEnbblfd();
                try {
                    sftRootPbnfCifdkingEnbblfd(fblsf);
                    ui.instbllUI(tiis);
                } finblly {
                    sftRootPbnfCifdkingEnbblfd(old);
                }
            }
        }
    }

    /* Cbllfd from tif JComponfnt's EnbblfSfriblizbtionFodusListfnfr to
     * do bny Swing-spfdifid prf-sfriblizbtion donfigurbtion.
     */
    void dompWritfObjfdtNotify() {
      // nffd to disbblf rootpbnf difdking for IntfrnblFrbmf: 4172083
      boolfbn old = isRootPbnfCifdkingEnbblfd();
      try {
        sftRootPbnfCifdkingEnbblfd(fblsf);
        supfr.dompWritfObjfdtNotify();
      }
      finblly {
        sftRootPbnfCifdkingEnbblfd(old);
      }
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis <dodf>JIntfrnblFrbmf</dodf>.
     * Tiis mftiod
     * is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not
     * bf <dodf>null</dodf>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis <dodf>JIntfrnblFrbmf</dodf>
     */
    protfdtfd String pbrbmString() {
        String rootPbnfString = (rootPbnf != null ?
                                 rootPbnf.toString() : "");
        String rootPbnfCifdkingEnbblfdString = (rootPbnfCifdkingEnbblfd ?
                                                "truf" : "fblsf");
        String dlosbblfString = (dlosbblf ? "truf" : "fblsf");
        String isClosfdString = (isClosfd ? "truf" : "fblsf");
        String mbximizbblfString = (mbximizbblf ? "truf" : "fblsf");
        String isMbximumString = (isMbximum ? "truf" : "fblsf");
        String idonbblfString = (idonbblf ? "truf" : "fblsf");
        String isIdonString = (isIdon ? "truf" : "fblsf");
        String rfsizbblfString = (rfsizbblf ? "truf" : "fblsf");
        String isSflfdtfdString = (isSflfdtfd ? "truf" : "fblsf");
        String frbmfIdonString = (frbmfIdon != null ?
                                  frbmfIdon.toString() : "");
        String titlfString = (titlf != null ?
                              titlf : "");
        String dfsktopIdonString = (dfsktopIdon != null ?
                                    dfsktopIdon.toString() : "");
        String opfnfdString = (opfnfd ? "truf" : "fblsf");
        String dffbultClosfOpfrbtionString;
        if (dffbultClosfOpfrbtion == HIDE_ON_CLOSE) {
            dffbultClosfOpfrbtionString = "HIDE_ON_CLOSE";
        } flsf if (dffbultClosfOpfrbtion == DISPOSE_ON_CLOSE) {
            dffbultClosfOpfrbtionString = "DISPOSE_ON_CLOSE";
        } flsf if (dffbultClosfOpfrbtion == DO_NOTHING_ON_CLOSE) {
            dffbultClosfOpfrbtionString = "DO_NOTHING_ON_CLOSE";
        } flsf dffbultClosfOpfrbtionString = "";

        rfturn supfr.pbrbmString() +
        ",dlosbblf=" + dlosbblfString +
        ",dffbultClosfOpfrbtion=" + dffbultClosfOpfrbtionString +
        ",dfsktopIdon=" + dfsktopIdonString +
        ",frbmfIdon=" + frbmfIdonString +
        ",idonbblf=" + idonbblfString +
        ",isClosfd=" + isClosfdString +
        ",isIdon=" + isIdonString +
        ",isMbximum=" + isMbximumString +
        ",isSflfdtfd=" + isSflfdtfdString +
        ",mbximizbblf=" + mbximizbblfString +
        ",opfnfd=" + opfnfdString +
        ",rfsizbblf=" + rfsizbblfString +
        ",rootPbnf=" + rootPbnfString +
        ",rootPbnfCifdkingEnbblfd=" + rootPbnfCifdkingEnbblfdString +
        ",titlf=" + titlfString;
    }

    // ======= bfgin optimizfd frbmf drbgging dfffndf dodf ==============

    boolfbn isDrbgging = fblsf;
    boolfbn dbngfr = fblsf;

    /**
     * Ovfrriddfn to bllow optimizfd pbinting wifn tif
     * intfrnbl frbmf is bfing drbggfd.
     */
    protfdtfd void pbintComponfnt(Grbpiids g) {
      if (isDrbgging) {
        //         Systfm.out.println("oudi");
         dbngfr = truf;
      }

      supfr.pbintComponfnt(g);
   }

    // ======= fnd optimizfd frbmf drbgging dfffndf dodf ==============

/////////////////
// Addfssibility support
////////////////

    /**
     * Gfts tif <dodf>AddfssiblfContfxt</dodf> bssodibtfd witi tiis
     * <dodf>JIntfrnblFrbmf</dodf>.
     * For intfrnbl frbmfs, tif <dodf>AddfssiblfContfxt</dodf>
     * tbkfs tif form of bn
     * <dodf>AddfssiblfJIntfrnblFrbmf</dodf> objfdt.
     * A nfw <dodf>AddfssiblfJIntfrnblFrbmf</dodf> instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn <dodf>AddfssiblfJIntfrnblFrbmf</dodf> tibt sfrvfs bs tif
     *         <dodf>AddfssiblfContfxt</dodf> of tiis
     *         <dodf>JIntfrnblFrbmf</dodf>
     * @sff AddfssiblfJIntfrnblFrbmf
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfJIntfrnblFrbmf();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>JIntfrnblFrbmf</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to intfrnbl frbmf usfr-intfrfbdf
     * flfmfnts.
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
    protfdtfd dlbss AddfssiblfJIntfrnblFrbmf fxtfnds AddfssiblfJComponfnt
        implfmfnts AddfssiblfVbluf {

        /**
         * Gft tif bddfssiblf nbmf of tiis objfdt.
         *
         * @rfturn tif lodblizfd nbmf of tif objfdt -- dbn bf <dodf>null</dodf> if tiis
         * objfdt dofs not ibvf b nbmf
         * @sff #sftAddfssiblfNbmf
         */
        publid String gftAddfssiblfNbmf() {
            String nbmf = bddfssiblfNbmf;

            if (nbmf == null) {
                nbmf = (String)gftClifntPropfrty(AddfssiblfContfxt.ACCESSIBLE_NAME_PROPERTY);
            }
            if (nbmf == null) {
                nbmf = gftTitlf();
            }
            rfturn nbmf;
        }

        /**
         * Gft tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif
         * objfdt
         * @sff AddfssiblfRolf
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.INTERNAL_FRAME;
        }

        /**
         * Gfts tif AddfssiblfVbluf bssodibtfd witi tiis objfdt.  In tif
         * implfmfntbtion of tif Jbvb Addfssibility API for tiis dlbss,
         * rfturns tiis objfdt, wiidi is rfsponsiblf for implfmfnting tif
         * <dodf>AddfssiblfVbluf</dodf> intfrfbdf on bfiblf of itsflf.
         *
         * @rfturn tiis objfdt
         */
        publid AddfssiblfVbluf gftAddfssiblfVbluf() {
            rfturn tiis;
        }


        //
        // AddfssiblfVbluf mftiods
        //

        /**
         * Gft tif vbluf of tiis objfdt bs b Numbfr.
         *
         * @rfturn vbluf of tif objfdt -- dbn bf <dodf>null</dodf> if tiis objfdt dofs not
         * ibvf b vbluf
         */
        publid Numbfr gftCurrfntAddfssiblfVbluf() {
            rfturn Intfgfr.vblufOf(gftLbyfr());
        }

        /**
         * Sft tif vbluf of tiis objfdt bs b Numbfr.
         *
         * @rfturn <dodf>truf</dodf> if tif vbluf wbs sft
         */
        publid boolfbn sftCurrfntAddfssiblfVbluf(Numbfr n) {
            // TIGER - 4422535
            if (n == null) {
                rfturn fblsf;
            }
            sftLbyfr(Intfgfr.vblufOf(n.intVbluf()));
            rfturn truf;
        }

        /**
         * Gft tif minimum vbluf of tiis objfdt bs b Numbfr.
         *
         * @rfturn Minimum vbluf of tif objfdt; <dodf>null</dodf> if tiis objfdt dofs not
         * ibvf b minimum vbluf
         */
        publid Numbfr gftMinimumAddfssiblfVbluf() {
            rfturn Intfgfr.MIN_VALUE;
        }

        /**
         * Gft tif mbximum vbluf of tiis objfdt bs b Numbfr.
         *
         * @rfturn Mbximum vbluf of tif objfdt; <dodf>null</dodf> if tiis objfdt dofs not
         * ibvf b mbximum vbluf
         */
        publid Numbfr gftMbximumAddfssiblfVbluf() {
            rfturn Intfgfr.MAX_VALUE;
        }

    } // AddfssiblfJIntfrnblFrbmf

    /**
     * Tiis domponfnt rfprfsfnts bn idonififd vfrsion of b
     * <dodf>JIntfrnblFrbmf</dodf>.
     * Tiis API siould NOT BE USED by Swing bpplidbtions, bs it will go
     * bwby in futurf vfrsions of Swing bs its fundtionblity is movfd into
     * <dodf>JIntfrnblFrbmf</dodf>.  Tiis dlbss is publid only so tibt
     * UI objfdts dbn displby b dfsktop idon.  If bn bpplidbtion
     * wbnts to displby b dfsktop idon, it siould drfbtf b
     * <dodf>JIntfrnblFrbmf</dodf> instbndf bnd idonify it.
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
     * @butior Dbvid Klobb
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    stbtid publid dlbss JDfsktopIdon fxtfnds JComponfnt implfmfnts Addfssiblf
    {
        JIntfrnblFrbmf intfrnblFrbmf;

        /**
         * Crfbtfs bn idon for bn intfrnbl frbmf.
         *
         * @pbrbm f  tif <dodf>JIntfrnblFrbmf</dodf>
         *              for wiidi tif idon is drfbtfd
         */
        publid JDfsktopIdon(JIntfrnblFrbmf f) {
            sftVisiblf(fblsf);
            sftIntfrnblFrbmf(f);
            updbtfUI();
        }

        /**
         * Rfturns tif look-bnd-fffl objfdt tibt rfndfrs tiis domponfnt.
         *
         * @rfturn tif <dodf>DfsktopIdonUI</dodf> objfdt tibt rfndfrs
         *              tiis domponfnt
         */
        publid DfsktopIdonUI gftUI() {
            rfturn (DfsktopIdonUI)ui;
        }

        /**
         * Sfts tif look-bnd-fffl objfdt tibt rfndfrs tiis domponfnt.
         *
         * @pbrbm ui  tif <dodf>DfsktopIdonUI</dodf> look-bnd-fffl objfdt
         * @sff UIDffbults#gftUI
         */
        publid void sftUI(DfsktopIdonUI ui) {
            supfr.sftUI(ui);
        }

        /**
         * Rfturns tif <dodf>JIntfrnblFrbmf</dodf> tibt tiis
         * <dodf>DfsktopIdon</dodf> is bssodibtfd witi.
         *
         * @rfturn tif <dodf>JIntfrnblFrbmf</dodf> witi wiidi tiis idon
         *              is bssodibtfd
         */
        publid JIntfrnblFrbmf gftIntfrnblFrbmf() {
            rfturn intfrnblFrbmf;
        }

        /**
         * Sfts tif <dodf>JIntfrnblFrbmf</dodf> witi wiidi tiis
         * <dodf>DfsktopIdon</dodf> is bssodibtfd.
         *
         * @pbrbm f  tif <dodf>JIntfrnblFrbmf</dodf> witi wiidi tiis idon
         *              is bssodibtfd
         */
        publid void sftIntfrnblFrbmf(JIntfrnblFrbmf f) {
            intfrnblFrbmf = f;
        }

        /**
         * Convfnifndf mftiod to bsk tif idon for tif <dodf>Dfsktop</dodf>
         * objfdt it bflongs to.
         *
         * @rfturn tif <dodf>JDfsktopPbnf</dodf> tibt dontbins tiis
         *           idon's intfrnbl frbmf, or <dodf>null</dodf> if nonf found
         */
        publid JDfsktopPbnf gftDfsktopPbnf() {
            if(gftIntfrnblFrbmf() != null)
                rfturn gftIntfrnblFrbmf().gftDfsktopPbnf();
            rfturn null;
        }

        /**
         * Notifidbtion from tif <dodf>UIMbnbgfr</dodf> tibt tif look bnd fffl
         * ibs dibngfd.
         * Rfplbdfs tif durrfnt UI objfdt witi tif lbtfst vfrsion from tif
         * <dodf>UIMbnbgfr</dodf>.
         *
         * @sff JComponfnt#updbtfUI
         */
        publid void updbtfUI() {
            boolfbn ibdUI = (ui != null);
            sftUI((DfsktopIdonUI)UIMbnbgfr.gftUI(tiis));
            invblidbtf();

            Dimfnsion r = gftPrfffrrfdSizf();
            sftSizf(r.widti, r.ifigit);


            if (intfrnblFrbmf != null && intfrnblFrbmf.gftUI() != null) {  // don't do tiis if UI not drfbtfd yft
                SwingUtilitifs.updbtfComponfntTrffUI(intfrnblFrbmf);
            }
        }

        /* Tiis mftiod is dbllfd if updbtfUI wbs dbllfd on tif bssodibtfd
         * JIntfrnblFrbmf.  It's nfdfssbry to bvoid infinitf rfdursion.
         */
        void updbtfUIWifnHiddfn() {
            /* Updbtf tiis UI bnd bny bssodibtfd intfrnbl frbmf */
            sftUI((DfsktopIdonUI)UIMbnbgfr.gftUI(tiis));

            Dimfnsion r = gftPrfffrrfdSizf();
            sftSizf(r.widti, r.ifigit);

            invblidbtf();
            Componfnt[] diildrfn = gftComponfnts();
            if (diildrfn != null) {
                for (Componfnt diild : diildrfn) {
                    SwingUtilitifs.updbtfComponfntTrffUI(diild);
                }
            }
        }

        /**
         * Rfturns tif nbmf of tif look-bnd-fffl
         * dlbss tibt rfndfrs tiis domponfnt.
         *
         * @rfturn tif string "DfsktopIdonUI"
         * @sff JComponfnt#gftUIClbssID
         * @sff UIDffbults#gftUI
         */
        publid String gftUIClbssID() {
            rfturn "DfsktopIdonUI";
        }
        ////////////////
        // Sfriblizbtion support
        ////////////////
        privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
            s.dffbultWritfObjfdt();
            if (gftUIClbssID().fqubls("DfsktopIdonUI")) {
                bytf dount = JComponfnt.gftWritfObjCountfr(tiis);
                JComponfnt.sftWritfObjCountfr(tiis, --dount);
                if (dount == 0 && ui != null) {
                    ui.instbllUI(tiis);
                }
            }
        }

       /////////////////
       // Addfssibility support
       ////////////////

        /**
         * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis JDfsktopIdon.
         * For dfsktop idons, tif AddfssiblfContfxt tbkfs tif form of bn
         * AddfssiblfJDfsktopIdon.
         * A nfw AddfssiblfJDfsktopIdon instbndf is drfbtfd if nfdfssbry.
         *
         * @rfturn bn AddfssiblfJDfsktopIdon tibt sfrvfs bs tif
         *         AddfssiblfContfxt of tiis JDfsktopIdon
         */
        publid AddfssiblfContfxt gftAddfssiblfContfxt() {
            if (bddfssiblfContfxt == null) {
                bddfssiblfContfxt = nfw AddfssiblfJDfsktopIdon();
            }
            rfturn bddfssiblfContfxt;
        }

        /**
         * Tiis dlbss implfmfnts bddfssibility support for tif
         * <dodf>JIntfrnblFrbmf.JDfsktopIdon</dodf> dlbss.  It providfs bn
         * implfmfntbtion of tif Jbvb Addfssibility API bppropribtf to
         * dfsktop idon usfr-intfrfbdf flfmfnts.
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
        protfdtfd dlbss AddfssiblfJDfsktopIdon fxtfnds AddfssiblfJComponfnt
            implfmfnts AddfssiblfVbluf {

            /**
             * Gfts tif rolf of tiis objfdt.
             *
             * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif
             * objfdt
             * @sff AddfssiblfRolf
             */
            publid AddfssiblfRolf gftAddfssiblfRolf() {
                rfturn AddfssiblfRolf.DESKTOP_ICON;
            }

            /**
             * Gfts tif AddfssiblfVbluf bssodibtfd witi tiis objfdt.  In tif
             * implfmfntbtion of tif Jbvb Addfssibility API for tiis dlbss,
             * rfturns tiis objfdt, wiidi is rfsponsiblf for implfmfnting tif
             * <dodf>AddfssiblfVbluf</dodf> intfrfbdf on bfiblf of itsflf.
             *
             * @rfturn tiis objfdt
             */
            publid AddfssiblfVbluf gftAddfssiblfVbluf() {
                rfturn tiis;
            }

            //
            // AddfssiblfVbluf mftiods
            //

            /**
             * Gfts tif vbluf of tiis objfdt bs b <dodf>Numbfr</dodf>.
             *
             * @rfturn vbluf of tif objfdt -- dbn bf <dodf>null</dodf> if tiis objfdt dofs not
             * ibvf b vbluf
             */
            publid Numbfr gftCurrfntAddfssiblfVbluf() {
                AddfssiblfContfxt b = JDfsktopIdon.tiis.gftIntfrnblFrbmf().gftAddfssiblfContfxt();
                AddfssiblfVbluf v = b.gftAddfssiblfVbluf();
                if (v != null) {
                    rfturn v.gftCurrfntAddfssiblfVbluf();
                } flsf {
                    rfturn null;
                }
            }

            /**
             * Sfts tif vbluf of tiis objfdt bs b <dodf>Numbfr</dodf>.
             *
             * @rfturn <dodf>truf</dodf> if tif vbluf wbs sft
             */
            publid boolfbn sftCurrfntAddfssiblfVbluf(Numbfr n) {
                // TIGER - 4422535
                if (n == null) {
                    rfturn fblsf;
                }
                AddfssiblfContfxt b = JDfsktopIdon.tiis.gftIntfrnblFrbmf().gftAddfssiblfContfxt();
                AddfssiblfVbluf v = b.gftAddfssiblfVbluf();
                if (v != null) {
                    rfturn v.sftCurrfntAddfssiblfVbluf(n);
                } flsf {
                    rfturn fblsf;
                }
            }

            /**
             * Gfts tif minimum vbluf of tiis objfdt bs b <dodf>Numbfr</dodf>.
             *
             * @rfturn minimum vbluf of tif objfdt; <dodf>null</dodf> if tiis objfdt dofs not
             * ibvf b minimum vbluf
             */
            publid Numbfr gftMinimumAddfssiblfVbluf() {
                AddfssiblfContfxt b = JDfsktopIdon.tiis.gftIntfrnblFrbmf().gftAddfssiblfContfxt();
                if (b instbndfof AddfssiblfVbluf) {
                    rfturn ((AddfssiblfVbluf)b).gftMinimumAddfssiblfVbluf();
                } flsf {
                    rfturn null;
                }
            }

            /**
             * Gfts tif mbximum vbluf of tiis objfdt bs b <dodf>Numbfr</dodf>.
             *
             * @rfturn mbximum vbluf of tif objfdt; <dodf>null</dodf> if tiis objfdt dofs not
             * ibvf b mbximum vbluf
             */
            publid Numbfr gftMbximumAddfssiblfVbluf() {
                AddfssiblfContfxt b = JDfsktopIdon.tiis.gftIntfrnblFrbmf().gftAddfssiblfContfxt();
                if (b instbndfof AddfssiblfVbluf) {
                    rfturn ((AddfssiblfVbluf)b).gftMbximumAddfssiblfVbluf();
                } flsf {
                    rfturn null;
                }
            }

        } // AddfssiblfJDfsktopIdon
    }
}
