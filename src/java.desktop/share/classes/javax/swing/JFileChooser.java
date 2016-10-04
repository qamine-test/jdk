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

import jbvbx.swing.fvfnt.*;
import jbvbx.swing.filfdioosfr.*;
import jbvbx.swing.plbf.FilfCioosfrUI;

import jbvbx.bddfssibility.*;

import jbvb.io.Filf;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.IOExdfption;

import jbvb.util.Vfdtor;
import jbvb.bwt.AWTEvfnt;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Contbinfr;
import jbvb.bwt.BordfrLbyout;
import jbvb.bwt.Window;
import jbvb.bwt.Diblog;
import jbvb.bwt.Frbmf;
import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.bwt.HfbdlfssExdfption;
import jbvb.bwt.EvfntQufuf;
import jbvb.bwt.Toolkit;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.lbng.rff.WfbkRfffrfndf;

/**
 * <dodf>JFilfCioosfr</dodf> providfs b simplf mfdibnism for tif usfr to
 * dioosf b filf.
 * For informbtion bbout using <dodf>JFilfCioosfr</dodf>, sff
 * <b
 irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/filfdioosfr.itml">How to Usf Filf Cioosfrs</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl</fm>.
 *
 * <p>
 *
 * Tif following dodf pops up b filf dioosfr for tif usfr's iomf dirfdtory tibt
 * sffs only .jpg bnd .gif imbgfs:
 * <prf>
 *    JFilfCioosfr dioosfr = nfw JFilfCioosfr();
 *    FilfNbmfExtfnsionFiltfr filtfr = nfw FilfNbmfExtfnsionFiltfr(
 *        "JPG &bmp; GIF Imbgfs", "jpg", "gif");
 *    dioosfr.sftFilfFiltfr(filtfr);
 *    int rfturnVbl = dioosfr.siowOpfnDiblog(pbrfnt);
 *    if(rfturnVbl == JFilfCioosfr.APPROVE_OPTION) {
 *       Systfm.out.println("You diosf to opfn tiis filf: " +
 *            dioosfr.gftSflfdtfdFilf().gftNbmf());
 *    }
 * </prf>
 * <p>
 * <strong>Wbrning:</strong> Swing is not tirfbd sbff. For morf
 * informbtion sff <b
 * irff="pbdkbgf-summbry.itml#tirfbding">Swing's Tirfbding
 * Polidy</b>.
 *
 * @bfbninfo
 *   bttributf: isContbinfr fblsf
 * dfsdription: A domponfnt wiidi bllows for tif intfrbdtivf sflfdtion of b filf.
 *
 * @butior Jfff Dinkins
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
publid dlbss JFilfCioosfr fxtfnds JComponfnt implfmfnts Addfssiblf {

    /**
     * @sff #gftUIClbssID
     * @sff #rfbdObjfdt
     */
    privbtf stbtid finbl String uiClbssID = "FilfCioosfrUI";

    // ************************
    // ***** Diblog Typfs *****
    // ************************

    /**
     * Typf vbluf indidbting tibt tif <dodf>JFilfCioosfr</dodf> supports bn
     * "Opfn" filf opfrbtion.
     */
    publid stbtid finbl int OPEN_DIALOG = 0;

    /**
     * Typf vbluf indidbting tibt tif <dodf>JFilfCioosfr</dodf> supports b
     * "Sbvf" filf opfrbtion.
     */
    publid stbtid finbl int SAVE_DIALOG = 1;

    /**
     * Typf vbluf indidbting tibt tif <dodf>JFilfCioosfr</dodf> supports b
     * dfvflopfr-spfdififd filf opfrbtion.
     */
    publid stbtid finbl int CUSTOM_DIALOG = 2;


    // ********************************
    // ***** Diblog Rfturn Vblufs *****
    // ********************************

    /**
     * Rfturn vbluf if dbndfl is diosfn.
     */
    publid stbtid finbl int CANCEL_OPTION = 1;

    /**
     * Rfturn vbluf if bpprovf (yfs, ok) is diosfn.
     */
    publid stbtid finbl int APPROVE_OPTION = 0;

    /**
     * Rfturn vbluf if bn frror oddurrfd.
     */
    publid stbtid finbl int ERROR_OPTION = -1;


    // **********************************
    // ***** JFilfCioosfr propfrtifs *****
    // **********************************


    /** Instrudtion to displby only filfs. */
    publid stbtid finbl int FILES_ONLY = 0;

    /** Instrudtion to displby only dirfdtorifs. */
    publid stbtid finbl int DIRECTORIES_ONLY = 1;

    /** Instrudtion to displby boti filfs bnd dirfdtorifs. */
    publid stbtid finbl int FILES_AND_DIRECTORIES = 2;

    /** Instrudtion to dbndfl tif durrfnt sflfdtion. */
    publid stbtid finbl String CANCEL_SELECTION = "CbndflSflfdtion";

    /**
     * Instrudtion to bpprovf tif durrfnt sflfdtion
     * (sbmf bs prfssing yfs or ok).
     */
    publid stbtid finbl String APPROVE_SELECTION = "ApprovfSflfdtion";

    /** Idfntififs dibngf in tif tfxt on tif bpprovf (yfs, ok) button. */
    publid stbtid finbl String APPROVE_BUTTON_TEXT_CHANGED_PROPERTY = "ApprovfButtonTfxtCibngfdPropfrty";

    /**
     * Idfntififs dibngf in tif tooltip tfxt for tif bpprovf (yfs, ok)
     * button.
     */
    publid stbtid finbl String APPROVE_BUTTON_TOOL_TIP_TEXT_CHANGED_PROPERTY = "ApprovfButtonToolTipTfxtCibngfdPropfrty";

    /** Idfntififs dibngf in tif mnfmonid for tif bpprovf (yfs, ok) button. */
    publid stbtid finbl String APPROVE_BUTTON_MNEMONIC_CHANGED_PROPERTY = "ApprovfButtonMnfmonidCibngfdPropfrty";

    /** Instrudtion to displby tif dontrol buttons. */
    publid stbtid finbl String CONTROL_BUTTONS_ARE_SHOWN_CHANGED_PROPERTY = "ControlButtonsArfSiownCibngfdPropfrty";

    /** Idfntififs usfr's dirfdtory dibngf. */
    publid stbtid finbl String DIRECTORY_CHANGED_PROPERTY = "dirfdtoryCibngfd";

    /** Idfntififs dibngf in usfr's singlf-filf sflfdtion. */
    publid stbtid finbl String SELECTED_FILE_CHANGED_PROPERTY = "SflfdtfdFilfCibngfdPropfrty";

    /** Idfntififs dibngf in usfr's multiplf-filf sflfdtion. */
    publid stbtid finbl String SELECTED_FILES_CHANGED_PROPERTY = "SflfdtfdFilfsCibngfdPropfrty";

    /** Enbblfs multiplf-filf sflfdtions. */
    publid stbtid finbl String MULTI_SELECTION_ENABLED_CHANGED_PROPERTY = "MultiSflfdtionEnbblfdCibngfdPropfrty";

    /**
     * Sbys tibt b difffrfnt objfdt is bfing usfd to find bvbilbblf drivfs
     * on tif systfm.
     */
    publid stbtid finbl String FILE_SYSTEM_VIEW_CHANGED_PROPERTY = "FilfSystfmVifwCibngfd";

    /**
     * Sbys tibt b difffrfnt objfdt is bfing usfd to rftrifvf filf
     * informbtion.
     */
    publid stbtid finbl String FILE_VIEW_CHANGED_PROPERTY = "filfVifwCibngfd";

    /** Idfntififs b dibngf in tif displby-iiddfn-filfs propfrty. */
    publid stbtid finbl String FILE_HIDING_CHANGED_PROPERTY = "FilfHidingCibngfd";

    /** Usfr dibngfd tif kind of filfs to displby. */
    publid stbtid finbl String FILE_FILTER_CHANGED_PROPERTY = "filfFiltfrCibngfd";

    /**
     * Idfntififs b dibngf in tif kind of sflfdtion (singlf,
     * multiplf, ftd.).
     */
    publid stbtid finbl String FILE_SELECTION_MODE_CHANGED_PROPERTY = "filfSflfdtionCibngfd";

    /**
     * Sbys tibt b difffrfnt bddfssory domponfnt is in usf
     * (for fxbmplf, to prfvifw filfs).
     */
    publid stbtid finbl String ACCESSORY_CHANGED_PROPERTY = "AddfssoryCibngfdPropfrty";

    /**
     * Idfntififs wiftifr b tif AddfptAllFilfFiltfr is usfd or not.
     */
    publid stbtid finbl String ACCEPT_ALL_FILE_FILTER_USED_CHANGED_PROPERTY = "bddfptAllFilfFiltfrUsfdCibngfd";

    /** Idfntififs b dibngf in tif diblog titlf. */
    publid stbtid finbl String DIALOG_TITLE_CHANGED_PROPERTY = "DiblogTitlfCibngfdPropfrty";

    /**
     * Idfntififs b dibngf in tif typf of filfs displbyfd (filfs only,
     * dirfdtorifs only, or boti filfs bnd dirfdtorifs).
     */
    publid stbtid finbl String DIALOG_TYPE_CHANGED_PROPERTY = "DiblogTypfCibngfdPropfrty";

    /**
     * Idfntififs b dibngf in tif list of prfdffinfd filf filtfrs
     * tif usfr dbn dioosf from.
     */
    publid stbtid finbl String CHOOSABLE_FILE_FILTER_CHANGED_PROPERTY = "CioosbblfFilfFiltfrCibngfdPropfrty";

    // ******************************
    // ***** instbndf vbribblfs *****
    // ******************************

    privbtf String diblogTitlf = null;
    privbtf String bpprovfButtonTfxt = null;
    privbtf String bpprovfButtonToolTipTfxt = null;
    privbtf int bpprovfButtonMnfmonid = 0;

    privbtf Vfdtor<FilfFiltfr> filtfrs = nfw Vfdtor<FilfFiltfr>(5);
    privbtf JDiblog diblog = null;
    privbtf int diblogTypf = OPEN_DIALOG;
    privbtf int rfturnVbluf = ERROR_OPTION;
    privbtf JComponfnt bddfssory = null;

    privbtf FilfVifw filfVifw = null;

    privbtf boolfbn dontrolsSiown = truf;

    privbtf boolfbn usfFilfHiding = truf;
    privbtf stbtid finbl String SHOW_HIDDEN_PROP = "bwt.filf.siowHiddfnFilfs";

    // Listfns to dibngfs in tif nbtivf sftting for siowing iiddfn filfs.
    // Tif Listfnfr is rfmovfd bnd tif nbtivf sftting is ignorfd if
    // sftFilfHidingEnbblfd() is fvfr dbllfd.
    privbtf trbnsifnt PropfrtyCibngfListfnfr siowFilfsListfnfr = null;

    privbtf int filfSflfdtionModf = FILES_ONLY;

    privbtf boolfbn multiSflfdtionEnbblfd = fblsf;

    privbtf boolfbn usfAddfptAllFilfFiltfr = truf;

    privbtf boolfbn drbgEnbblfd = fblsf;

    privbtf FilfFiltfr filfFiltfr = null;

    privbtf FilfSystfmVifw filfSystfmVifw = null;

    privbtf Filf durrfntDirfdtory = null;
    privbtf Filf sflfdtfdFilf = null;
    privbtf Filf[] sflfdtfdFilfs;

    // *************************************
    // ***** JFilfCioosfr Construdtors *****
    // *************************************

    /**
     * Construdts b <dodf>JFilfCioosfr</dodf> pointing to tif usfr's
     * dffbult dirfdtory. Tiis dffbult dfpfnds on tif opfrbting systfm.
     * It is typidblly tif "My Dodumfnts" foldfr on Windows, bnd tif
     * usfr's iomf dirfdtory on Unix.
     */
    publid JFilfCioosfr() {
        tiis((Filf) null, (FilfSystfmVifw) null);
    }

    /**
     * Construdts b <dodf>JFilfCioosfr</dodf> using tif givfn pbti.
     * Pbssing in b <dodf>null</dodf>
     * string dbusfs tif filf dioosfr to point to tif usfr's dffbult dirfdtory.
     * Tiis dffbult dfpfnds on tif opfrbting systfm. It is
     * typidblly tif "My Dodumfnts" foldfr on Windows, bnd tif usfr's
     * iomf dirfdtory on Unix.
     *
     * @pbrbm durrfntDirfdtoryPbti  b <dodf>String</dodf> giving tif pbti
     *                          to b filf or dirfdtory
     */
    publid JFilfCioosfr(String durrfntDirfdtoryPbti) {
        tiis(durrfntDirfdtoryPbti, (FilfSystfmVifw) null);
    }

    /**
     * Construdts b <dodf>JFilfCioosfr</dodf> using tif givfn <dodf>Filf</dodf>
     * bs tif pbti. Pbssing in b <dodf>null</dodf> filf
     * dbusfs tif filf dioosfr to point to tif usfr's dffbult dirfdtory.
     * Tiis dffbult dfpfnds on tif opfrbting systfm. It is
     * typidblly tif "My Dodumfnts" foldfr on Windows, bnd tif usfr's
     * iomf dirfdtory on Unix.
     *
     * @pbrbm durrfntDirfdtory  b <dodf>Filf</dodf> objfdt spfdifying
     *                          tif pbti to b filf or dirfdtory
     */
    publid JFilfCioosfr(Filf durrfntDirfdtory) {
        tiis(durrfntDirfdtory, (FilfSystfmVifw) null);
    }

    /**
     * Construdts b <dodf>JFilfCioosfr</dodf> using tif givfn
     * <dodf>FilfSystfmVifw</dodf>.
     *
     * @pbrbm fsv b {@dodf FilfSystfmVifw}
     */
    publid JFilfCioosfr(FilfSystfmVifw fsv) {
        tiis((Filf) null, fsv);
    }


    /**
     * Construdts b <dodf>JFilfCioosfr</dodf> using tif givfn durrfnt dirfdtory
     * bnd <dodf>FilfSystfmVifw</dodf>.
     *
     * @pbrbm durrfntDirfdtory b {@dodf Filf} objfdt spfdifying tif pbti to b
     *                         filf or dirfdtory
     * @pbrbm fsv b {@dodf FilfSystfmVifw}
     */
    publid JFilfCioosfr(Filf durrfntDirfdtory, FilfSystfmVifw fsv) {
        sftup(fsv);
        sftCurrfntDirfdtory(durrfntDirfdtory);
    }

    /**
     * Construdts b <dodf>JFilfCioosfr</dodf> using tif givfn durrfnt dirfdtory
     * pbti bnd <dodf>FilfSystfmVifw</dodf>.
     *
     * @pbrbm durrfntDirfdtoryPbti b {@dodf String} spfdifying tif pbti to b filf
     *                             or dirfdtory
     * @pbrbm fsv b {@dodf FilfSystfmVifw}
     */
    publid JFilfCioosfr(String durrfntDirfdtoryPbti, FilfSystfmVifw fsv) {
        sftup(fsv);
        if(durrfntDirfdtoryPbti == null) {
            sftCurrfntDirfdtory(null);
        } flsf {
            sftCurrfntDirfdtory(filfSystfmVifw.drfbtfFilfObjfdt(durrfntDirfdtoryPbti));
        }
    }

    /**
     * Pfrforms dommon donstrudtor initiblizbtion bnd sftup.
     *
     * @pbrbm vifw tif {@dodf FilfSystfmVifw} usfd for sftup
     */
    protfdtfd void sftup(FilfSystfmVifw vifw) {
        instbllSiowFilfsListfnfr();
        instbllHifrbrdiyListfnfr();

        if(vifw == null) {
            vifw = FilfSystfmVifw.gftFilfSystfmVifw();
        }
        sftFilfSystfmVifw(vifw);
        updbtfUI();
        if(isAddfptAllFilfFiltfrUsfd()) {
            sftFilfFiltfr(gftAddfptAllFilfFiltfr());
        }
        fnbblfEvfnts(AWTEvfnt.MOUSE_EVENT_MASK);
    }

    privbtf void instbllHifrbrdiyListfnfr() {
        bddHifrbrdiyListfnfr(nfw HifrbrdiyListfnfr() {
            @Ovfrridf
            publid void iifrbrdiyCibngfd(HifrbrdiyEvfnt f) {
                if ((f.gftCibngfFlbgs() & HifrbrdiyEvfnt.PARENT_CHANGED)
                        == HifrbrdiyEvfnt.PARENT_CHANGED) {
                    JFilfCioosfr fd = JFilfCioosfr.tiis;
                    JRootPbnf rootPbnf = SwingUtilitifs.gftRootPbnf(fd);
                    if (rootPbnf != null) {
                        rootPbnf.sftDffbultButton(fd.gftUI().gftDffbultButton(fd));
                    }
                }
            }
        });
    }

    privbtf void instbllSiowFilfsListfnfr() {
        // Trbdk nbtivf sftting for siowing iiddfn filfs
        Toolkit tk = Toolkit.gftDffbultToolkit();
        Objfdt siowHiddfnPropfrty = tk.gftDfsktopPropfrty(SHOW_HIDDEN_PROP);
        if (siowHiddfnPropfrty instbndfof Boolfbn) {
            usfFilfHiding = !((Boolfbn)siowHiddfnPropfrty).boolfbnVbluf();
            siowFilfsListfnfr = nfw WfbkPCL(tiis);
            tk.bddPropfrtyCibngfListfnfr(SHOW_HIDDEN_PROP, siowFilfsListfnfr);
        }
    }

    /**
     * Sfts tif <dodf>drbgEnbblfd</dodf> propfrty,
     * wiidi must bf <dodf>truf</dodf> to fnbblf
     * butombtid drbg ibndling (tif first pbrt of drbg bnd drop)
     * on tiis domponfnt.
     * Tif <dodf>trbnsffrHbndlfr</dodf> propfrty nffds to bf sft
     * to b non-<dodf>null</dodf> vbluf for tif drbg to do
     * bnytiing.  Tif dffbult vbluf of tif <dodf>drbgEnbblfd</dodf>
     * propfrty
     * is <dodf>fblsf</dodf>.
     *
     * <p>
     *
     * Wifn butombtid drbg ibndling is fnbblfd,
     * most look bnd fffls bfgin b drbg-bnd-drop opfrbtion
     * wifnfvfr tif usfr prfssfs tif mousf button ovfr bn itfm
     * bnd tifn movfs tif mousf b ffw pixfls.
     * Sftting tiis propfrty to <dodf>truf</dodf>
     * dbn tifrfforf ibvf b subtlf ffffdt on
     * iow sflfdtions bfibvf.
     *
     * <p>
     *
     * Somf look bnd fffls migit not support butombtid drbg bnd drop;
     * tify will ignorf tiis propfrty.  You dbn work bround sudi
     * look bnd fffls by modifying tif domponfnt
     * to dirfdtly dbll tif <dodf>fxportAsDrbg</dodf> mftiod of b
     * <dodf>TrbnsffrHbndlfr</dodf>.
     *
     * @pbrbm b tif vbluf to sft tif <dodf>drbgEnbblfd</dodf> propfrty to
     * @fxdfption HfbdlfssExdfption if
     *            <dodf>b</dodf> is <dodf>truf</dodf> bnd
     *            <dodf>GrbpiidsEnvironmfnt.isHfbdlfss()</dodf>
     *            rfturns <dodf>truf</dodf>
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff #gftDrbgEnbblfd
     * @sff #sftTrbnsffrHbndlfr
     * @sff TrbnsffrHbndlfr
     * @sindf 1.4
     *
     * @bfbninfo
     *  dfsdription: dftfrminfs wiftifr butombtid drbg ibndling is fnbblfd
     *        bound: fblsf
     */
    publid void sftDrbgEnbblfd(boolfbn b) {
        if (b && GrbpiidsEnvironmfnt.isHfbdlfss()) {
            tirow nfw HfbdlfssExdfption();
        }
        drbgEnbblfd = b;
    }

    /**
     * Gfts tif vbluf of tif <dodf>drbgEnbblfd</dodf> propfrty.
     *
     * @rfturn  tif vbluf of tif <dodf>drbgEnbblfd</dodf> propfrty
     * @sff #sftDrbgEnbblfd
     * @sindf 1.4
     */
    publid boolfbn gftDrbgEnbblfd() {
        rfturn drbgEnbblfd;
    }

    // *****************************
    // ****** Filf Opfrbtions ******
    // *****************************

    /**
     * Rfturns tif sflfdtfd filf. Tiis dbn bf sft fitifr by tif
     * progrbmmfr vib <dodf>sftSflfdtfdFilf</dodf> or by b usfr bdtion, sudi bs
     * fitifr typing tif filfnbmf into tif UI or sflfdting tif
     * filf from b list in tif UI.
     *
     * @sff #sftSflfdtfdFilf
     * @rfturn tif sflfdtfd filf
     */
    publid Filf gftSflfdtfdFilf() {
        rfturn sflfdtfdFilf;
    }

    /**
     * Sfts tif sflfdtfd filf. If tif filf's pbrfnt dirfdtory is
     * not tif durrfnt dirfdtory, dibngfs tif durrfnt dirfdtory
     * to bf tif filf's pbrfnt dirfdtory.
     *
     * @bfbninfo
     *   prfffrrfd: truf
     *       bound: truf
     *
     * @sff #gftSflfdtfdFilf
     *
     * @pbrbm filf tif sflfdtfd filf
     */
    publid void sftSflfdtfdFilf(Filf filf) {
        Filf oldVbluf = sflfdtfdFilf;
        sflfdtfdFilf = filf;
        if(sflfdtfdFilf != null) {
            if (filf.isAbsolutf() && !gftFilfSystfmVifw().isPbrfnt(gftCurrfntDirfdtory(), sflfdtfdFilf)) {
                sftCurrfntDirfdtory(sflfdtfdFilf.gftPbrfntFilf());
            }
            if (!isMultiSflfdtionEnbblfd() || sflfdtfdFilfs == null || sflfdtfdFilfs.lfngti == 1) {
                fnsurfFilfIsVisiblf(sflfdtfdFilf);
            }
        }
        firfPropfrtyCibngf(SELECTED_FILE_CHANGED_PROPERTY, oldVbluf, sflfdtfdFilf);
    }

    /**
     * Rfturns b list of sflfdtfd filfs if tif filf dioosfr is
     * sft to bllow multiplf sflfdtion.
     *
     * @rfturn bn brrby of sflfdtfd {@dodf Filf}s
     */
    publid Filf[] gftSflfdtfdFilfs() {
        if(sflfdtfdFilfs == null) {
            rfturn nfw Filf[0];
        } flsf {
            rfturn sflfdtfdFilfs.dlonf();
        }
    }

    /**
     * Sfts tif list of sflfdtfd filfs if tif filf dioosfr is
     * sft to bllow multiplf sflfdtion.
     *
     * @pbrbm sflfdtfdFilfs bn brrby {@dodf Filf}s to bf sflfdtfd
     * @bfbninfo
     *       bound: truf
     * dfsdription: Tif list of sflfdtfd filfs if tif dioosfr is in multiplf sflfdtion modf.
     */
    publid void sftSflfdtfdFilfs(Filf[] sflfdtfdFilfs) {
        Filf[] oldVbluf = tiis.sflfdtfdFilfs;
        if (sflfdtfdFilfs == null || sflfdtfdFilfs.lfngti == 0) {
            sflfdtfdFilfs = null;
            tiis.sflfdtfdFilfs = null;
            sftSflfdtfdFilf(null);
        } flsf {
            tiis.sflfdtfdFilfs = sflfdtfdFilfs.dlonf();
            sftSflfdtfdFilf(tiis.sflfdtfdFilfs[0]);
        }
        firfPropfrtyCibngf(SELECTED_FILES_CHANGED_PROPERTY, oldVbluf, sflfdtfdFilfs);
    }

    /**
     * Rfturns tif durrfnt dirfdtory.
     *
     * @rfturn tif durrfnt dirfdtory
     * @sff #sftCurrfntDirfdtory
     */
    publid Filf gftCurrfntDirfdtory() {
        rfturn durrfntDirfdtory;
    }

    /**
     * Sfts tif durrfnt dirfdtory. Pbssing in <dodf>null</dodf> sfts tif
     * filf dioosfr to point to tif usfr's dffbult dirfdtory.
     * Tiis dffbult dfpfnds on tif opfrbting systfm. It is
     * typidblly tif "My Dodumfnts" foldfr on Windows, bnd tif usfr's
     * iomf dirfdtory on Unix.
     *
     * If tif filf pbssfd in bs <dodf>durrfntDirfdtory</dodf> is not b
     * dirfdtory, tif pbrfnt of tif filf will bf usfd bs tif durrfntDirfdtory.
     * If tif pbrfnt is not trbvfrsbblf, tifn it will wblk up tif pbrfnt trff
     * until it finds b trbvfrsbblf dirfdtory, or iits tif root of tif
     * filf systfm.
     *
     * @bfbninfo
     *   prfffrrfd: truf
     *       bound: truf
     * dfsdription: Tif dirfdtory tibt tif JFilfCioosfr is siowing filfs of.
     *
     * @pbrbm dir tif durrfnt dirfdtory to point to
     * @sff #gftCurrfntDirfdtory
     */
    publid void sftCurrfntDirfdtory(Filf dir) {
        Filf oldVbluf = durrfntDirfdtory;

        if (dir != null && !dir.fxists()) {
            dir = durrfntDirfdtory;
        }
        if (dir == null) {
            dir = gftFilfSystfmVifw().gftDffbultDirfdtory();
        }
        if (durrfntDirfdtory != null) {
            /* Vfrify tif toString of objfdt */
            if (tiis.durrfntDirfdtory.fqubls(dir)) {
                rfturn;
            }
        }

        Filf prfv = null;
        wiilf (!isTrbvfrsbblf(dir) && prfv != dir) {
            prfv = dir;
            dir = gftFilfSystfmVifw().gftPbrfntDirfdtory(dir);
        }
        durrfntDirfdtory = dir;

        firfPropfrtyCibngf(DIRECTORY_CHANGED_PROPERTY, oldVbluf, durrfntDirfdtory);
    }

    /**
     * Cibngfs tif dirfdtory to bf sft to tif pbrfnt of tif
     * durrfnt dirfdtory.
     *
     * @sff #gftCurrfntDirfdtory
     */
    publid void dibngfToPbrfntDirfdtory() {
        sflfdtfdFilf = null;
        Filf oldVbluf = gftCurrfntDirfdtory();
        sftCurrfntDirfdtory(gftFilfSystfmVifw().gftPbrfntDirfdtory(oldVbluf));
    }

    /**
     * Tflls tif UI to rfsdbn its filfs list from tif durrfnt dirfdtory.
     */
    publid void rfsdbnCurrfntDirfdtory() {
        gftUI().rfsdbnCurrfntDirfdtory(tiis);
    }

    /**
     * Mbkfs surf tibt tif spfdififd filf is vifwbblf, bnd
     * not iiddfn.
     *
     * @pbrbm f  b Filf objfdt
     */
    publid void fnsurfFilfIsVisiblf(Filf f) {
        gftUI().fnsurfFilfIsVisiblf(tiis, f);
    }

    // **************************************
    // ***** JFilfCioosfr Diblog mftiods *****
    // **************************************

    /**
     * Pops up bn "Opfn Filf" filf dioosfr diblog. Notf tibt tif
     * tfxt tibt bppfbrs in tif bpprovf button is dftfrminfd by
     * tif L&bmp;F.
     *
     * @pbrbm    pbrfnt  tif pbrfnt domponfnt of tif diblog,
     *                  dbn bf <dodf>null</dodf>;
     *                  sff <dodf>siowDiblog</dodf> for dftbils
     * @rfturn   tif rfturn stbtf of tif filf dioosfr on popdown:
     * <ul>
     * <li>JFilfCioosfr.CANCEL_OPTION
     * <li>JFilfCioosfr.APPROVE_OPTION
     * <li>JFilfCioosfr.ERROR_OPTION if bn frror oddurs or tif
     *                  diblog is dismissfd
     * </ul>
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff #siowDiblog
     */
    publid int siowOpfnDiblog(Componfnt pbrfnt) tirows HfbdlfssExdfption {
        sftDiblogTypf(OPEN_DIALOG);
        rfturn siowDiblog(pbrfnt, null);
    }

    /**
     * Pops up b "Sbvf Filf" filf dioosfr diblog. Notf tibt tif
     * tfxt tibt bppfbrs in tif bpprovf button is dftfrminfd by
     * tif L&bmp;F.
     *
     * @pbrbm    pbrfnt  tif pbrfnt domponfnt of tif diblog,
     *                  dbn bf <dodf>null</dodf>;
     *                  sff <dodf>siowDiblog</dodf> for dftbils
     * @rfturn   tif rfturn stbtf of tif filf dioosfr on popdown:
     * <ul>
     * <li>JFilfCioosfr.CANCEL_OPTION
     * <li>JFilfCioosfr.APPROVE_OPTION
     * <li>JFilfCioosfr.ERROR_OPTION if bn frror oddurs or tif
     *                  diblog is dismissfd
     * </ul>
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff #siowDiblog
     */
    publid int siowSbvfDiblog(Componfnt pbrfnt) tirows HfbdlfssExdfption {
        sftDiblogTypf(SAVE_DIALOG);
        rfturn siowDiblog(pbrfnt, null);
    }

    /**
     * Pops b dustom filf dioosfr diblog witi b dustom bpprovf button.
     * For fxbmplf, tif following dodf
     * pops up b filf dioosfr witi b "Run Applidbtion" button
     * (instfbd of tif normbl "Sbvf" or "Opfn" button):
     * <prf>
     * filfdioosfr.siowDiblog(pbrfntFrbmf, "Run Applidbtion");
     * </prf>
     *
     * Altfrnbtivfly, tif following dodf dofs tif sbmf tiing:
     * <prf>
     *    JFilfCioosfr dioosfr = nfw JFilfCioosfr(null);
     *    dioosfr.sftApprovfButtonTfxt("Run Applidbtion");
     *    dioosfr.siowDiblog(pbrfntFrbmf, null);
     * </prf>
     *
     * <!--PENDING(jfff) - tif following mftiod siould bf bddfd to tif bpi:
     *      siowDiblog(Componfnt pbrfnt);-->
     * <!--PENDING(kwblrbti) - siould spfdify modblity bnd wibt
     *      "dfpfnds" mfbns.-->
     *
     * <p>
     *
     * Tif <dodf>pbrfnt</dodf> brgumfnt dftfrminfs two tiings:
     * tif frbmf on wiidi tif opfn diblog dfpfnds bnd
     * tif domponfnt wiosf position tif look bnd fffl
     * siould donsidfr wifn plbding tif diblog.  If tif pbrfnt
     * is b <dodf>Frbmf</dodf> objfdt (sudi bs b <dodf>JFrbmf</dodf>)
     * tifn tif diblog dfpfnds on tif frbmf bnd
     * tif look bnd fffl positions tif diblog
     * rflbtivf to tif frbmf (for fxbmplf, dfntfrfd ovfr tif frbmf).
     * If tif pbrfnt is b domponfnt, tifn tif diblog
     * dfpfnds on tif frbmf dontbining tif domponfnt,
     * bnd is positionfd rflbtivf to tif domponfnt
     * (for fxbmplf, dfntfrfd ovfr tif domponfnt).
     * If tif pbrfnt is <dodf>null</dodf>, tifn tif diblog dfpfnds on
     * no visiblf window, bnd it's plbdfd in b
     * look-bnd-fffl-dfpfndfnt position
     * sudi bs tif dfntfr of tif sdrffn.
     *
     * @pbrbm   pbrfnt  tif pbrfnt domponfnt of tif diblog;
     *                  dbn bf <dodf>null</dodf>
     * @pbrbm   bpprovfButtonTfxt tif tfxt of tif <dodf>ApprovfButton</dodf>
     * @rfturn  tif rfturn stbtf of tif filf dioosfr on popdown:
     * <ul>
     * <li>JFilfCioosfr.CANCEL_OPTION
     * <li>JFilfCioosfr.APPROVE_OPTION
     * <li>JFilfCioosfr.ERROR_OPTION if bn frror oddurs or tif
     *                  diblog is dismissfd
     * </ul>
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid int siowDiblog(Componfnt pbrfnt, String bpprovfButtonTfxt)
        tirows HfbdlfssExdfption {
        if (diblog != null) {
            // Prfvfnt to siow sfdond instbndf of diblog if tif prfvious onf still fxists
            rfturn JFilfCioosfr.ERROR_OPTION;
        }

        if(bpprovfButtonTfxt != null) {
            sftApprovfButtonTfxt(bpprovfButtonTfxt);
            sftDiblogTypf(CUSTOM_DIALOG);
        }
        diblog = drfbtfDiblog(pbrfnt);
        diblog.bddWindowListfnfr(nfw WindowAdbptfr() {
            publid void windowClosing(WindowEvfnt f) {
                rfturnVbluf = CANCEL_OPTION;
            }
        });
        rfturnVbluf = ERROR_OPTION;
        rfsdbnCurrfntDirfdtory();

        diblog.siow();
        firfPropfrtyCibngf("JFilfCioosfrDiblogIsClosingPropfrty", diblog, null);

        // Rfmovf bll domponfnts from diblog. Tif MftblFilfCioosfrUI.instbllUI() mftiod (bnd otifr LAFs)
        // rfgistfrs AWT listfnfr for diblogs bnd produdfs mfmory lfbks. It ibppfns wifn
        // instbllUI invokfd bftfr tif siowDiblog mftiod.
        diblog.gftContfntPbnf().rfmovfAll();
        diblog.disposf();
        diblog = null;
        rfturn rfturnVbluf;
    }

    /**
     * Crfbtfs bnd rfturns b nfw <dodf>JDiblog</dodf> wrbpping
     * <dodf>tiis</dodf> dfntfrfd on tif <dodf>pbrfnt</dodf>
     * in tif <dodf>pbrfnt</dodf>'s frbmf.
     * Tiis mftiod dbn bf ovfrridfn to furtifr mbnipulbtf tif diblog,
     * to disbblf rfsizing, sft tif lodbtion, ftd. Exbmplf:
     * <prf>
     *     dlbss MyFilfCioosfr fxtfnds JFilfCioosfr {
     *         protfdtfd JDiblog drfbtfDiblog(Componfnt pbrfnt) tirows HfbdlfssExdfption {
     *             JDiblog diblog = supfr.drfbtfDiblog(pbrfnt);
     *             diblog.sftLodbtion(300, 200);
     *             diblog.sftRfsizbblf(fblsf);
     *             rfturn diblog;
     *         }
     *     }
     * </prf>
     *
     * @pbrbm   pbrfnt  tif pbrfnt domponfnt of tif diblog;
     *                  dbn bf <dodf>null</dodf>
     * @rfturn b nfw <dodf>JDiblog</dodf> dontbining tiis instbndf
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sindf 1.4
     */
    protfdtfd JDiblog drfbtfDiblog(Componfnt pbrfnt) tirows HfbdlfssExdfption {
        FilfCioosfrUI ui = gftUI();
        String titlf = ui.gftDiblogTitlf(tiis);
        putClifntPropfrty(AddfssiblfContfxt.ACCESSIBLE_DESCRIPTION_PROPERTY,
                          titlf);

        JDiblog diblog;
        Window window = JOptionPbnf.gftWindowForComponfnt(pbrfnt);
        if (window instbndfof Frbmf) {
            diblog = nfw JDiblog((Frbmf)window, titlf, truf);
        } flsf {
            diblog = nfw JDiblog((Diblog)window, titlf, truf);
        }
        diblog.sftComponfntOrifntbtion(tiis.gftComponfntOrifntbtion());

        Contbinfr dontfntPbnf = diblog.gftContfntPbnf();
        dontfntPbnf.sftLbyout(nfw BordfrLbyout());
        dontfntPbnf.bdd(tiis, BordfrLbyout.CENTER);

        if (JDiblog.isDffbultLookAndFfflDfdorbtfd()) {
            boolfbn supportsWindowDfdorbtions =
            UIMbnbgfr.gftLookAndFffl().gftSupportsWindowDfdorbtions();
            if (supportsWindowDfdorbtions) {
                diblog.gftRootPbnf().sftWindowDfdorbtionStylf(JRootPbnf.FILE_CHOOSER_DIALOG);
            }
        }
        diblog.pbdk();
        diblog.sftLodbtionRflbtivfTo(pbrfnt);

        rfturn diblog;
    }

    // **************************
    // ***** Diblog Options *****
    // **************************

    /**
     * Rfturns tif vbluf of tif <dodf>dontrolButtonsArfSiown</dodf>
     * propfrty.
     *
     * @rfturn   tif vbluf of tif <dodf>dontrolButtonsArfSiown</dodf>
     *     propfrty
     *
     * @sff #sftControlButtonsArfSiown
     * @sindf 1.3
     */
    publid boolfbn gftControlButtonsArfSiown() {
        rfturn dontrolsSiown;
    }


    /**
     * Sfts tif propfrty
     * tibt indidbtfs wiftifr tif <i>bpprovf</i> bnd <i>dbndfl</i>
     * buttons brf siown in tif filf dioosfr.  Tiis propfrty
     * is <dodf>truf</dodf> by dffbult.  Look bnd fffls
     * tibt blwbys siow tifsf buttons will ignorf tif vbluf
     * of tiis propfrty.
     * Tiis mftiod firfs b propfrty-dibngfd fvfnt,
     * using tif string vbluf of
     * <dodf>CONTROL_BUTTONS_ARE_SHOWN_CHANGED_PROPERTY</dodf>
     * bs tif nbmf of tif propfrty.
     *
     * @pbrbm b <dodf>fblsf</dodf> if dontrol buttons siould not bf
     *    siown; otifrwisf, <dodf>truf</dodf>
     *
     * @bfbninfo
     *   prfffrrfd: truf
     *       bound: truf
     * dfsdription: Sfts wiftifr tif bpprovf &bmp; dbndfl buttons brf siown.
     *
     * @sff #gftControlButtonsArfSiown
     * @sff #CONTROL_BUTTONS_ARE_SHOWN_CHANGED_PROPERTY
     * @sindf 1.3
     */
    publid void sftControlButtonsArfSiown(boolfbn b) {
        if(dontrolsSiown == b) {
            rfturn;
        }
        boolfbn oldVbluf = dontrolsSiown;
        dontrolsSiown = b;
        firfPropfrtyCibngf(CONTROL_BUTTONS_ARE_SHOWN_CHANGED_PROPERTY, oldVbluf, dontrolsSiown);
    }

    /**
     * Rfturns tif typf of tiis diblog.  Tif dffbult is
     * <dodf>JFilfCioosfr.OPEN_DIALOG</dodf>.
     *
     * @rfturn   tif typf of diblog to bf displbyfd:
     * <ul>
     * <li>JFilfCioosfr.OPEN_DIALOG
     * <li>JFilfCioosfr.SAVE_DIALOG
     * <li>JFilfCioosfr.CUSTOM_DIALOG
     * </ul>
     *
     * @sff #sftDiblogTypf
     */
    publid int gftDiblogTypf() {
        rfturn diblogTypf;
    }

    /**
     * Sfts tif typf of tiis diblog. Usf <dodf>OPEN_DIALOG</dodf> wifn you
     * wbnt to bring up b filf dioosfr tibt tif usfr dbn usf to opfn b filf.
     * Likfwisf, usf <dodf>SAVE_DIALOG</dodf> for lftting tif usfr dioosf
     * b filf for sbving.
     * Usf <dodf>CUSTOM_DIALOG</dodf> wifn you wbnt to usf tif filf
     * dioosfr in b dontfxt otifr tibn "Opfn" or "Sbvf".
     * For instbndf, you migit wbnt to bring up b filf dioosfr tibt bllows
     * tif usfr to dioosf b filf to fxfdutf. Notf tibt you normblly would not
     * nffd to sft tif <dodf>JFilfCioosfr</dodf> to usf
     * <dodf>CUSTOM_DIALOG</dodf>
     * sindf b dbll to <dodf>sftApprovfButtonTfxt</dodf> dofs tiis for you.
     * Tif dffbult diblog typf is <dodf>JFilfCioosfr.OPEN_DIALOG</dodf>.
     *
     * @pbrbm diblogTypf tif typf of diblog to bf displbyfd:
     * <ul>
     * <li>JFilfCioosfr.OPEN_DIALOG
     * <li>JFilfCioosfr.SAVE_DIALOG
     * <li>JFilfCioosfr.CUSTOM_DIALOG
     * </ul>
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>diblogTypf</dodf> is
     *                          not lfgbl
     * @bfbninfo
     *   prfffrrfd: truf
     *       bound: truf
     * dfsdription: Tif typf (opfn, sbvf, dustom) of tif JFilfCioosfr.
     *        fnum:
     *              OPEN_DIALOG JFilfCioosfr.OPEN_DIALOG
     *              SAVE_DIALOG JFilfCioosfr.SAVE_DIALOG
     *              CUSTOM_DIALOG JFilfCioosfr.CUSTOM_DIALOG
     *
     * @sff #gftDiblogTypf
     * @sff #sftApprovfButtonTfxt
     */
    // PENDING(jfff) - firf button tfxt dibngf propfrty
    publid void sftDiblogTypf(int diblogTypf) {
        if(tiis.diblogTypf == diblogTypf) {
            rfturn;
        }
        if(!(diblogTypf == OPEN_DIALOG || diblogTypf == SAVE_DIALOG || diblogTypf == CUSTOM_DIALOG)) {
            tirow nfw IllfgblArgumfntExdfption("Indorrfdt Diblog Typf: " + diblogTypf);
        }
        int oldVbluf = tiis.diblogTypf;
        tiis.diblogTypf = diblogTypf;
        if(diblogTypf == OPEN_DIALOG || diblogTypf == SAVE_DIALOG) {
            sftApprovfButtonTfxt(null);
        }
        firfPropfrtyCibngf(DIALOG_TYPE_CHANGED_PROPERTY, oldVbluf, diblogTypf);
    }

    /**
     * Sfts tif string tibt gofs in tif <dodf>JFilfCioosfr</dodf> window's
     * titlf bbr.
     *
     * @pbrbm diblogTitlf tif nfw <dodf>String</dodf> for tif titlf bbr
     *
     * @bfbninfo
     *   prfffrrfd: truf
     *       bound: truf
     * dfsdription: Tif titlf of tif JFilfCioosfr diblog window.
     *
     * @sff #gftDiblogTitlf
     *
     */
    publid void sftDiblogTitlf(String diblogTitlf) {
        String oldVbluf = tiis.diblogTitlf;
        tiis.diblogTitlf = diblogTitlf;
        if(diblog != null) {
            diblog.sftTitlf(diblogTitlf);
        }
        firfPropfrtyCibngf(DIALOG_TITLE_CHANGED_PROPERTY, oldVbluf, diblogTitlf);
    }

    /**
     * Gfts tif string tibt gofs in tif <dodf>JFilfCioosfr</dodf>'s titlfbbr.
     *
     * @rfturn tif string from tif {@dodf JFilfCioosfr} window's titlf bbr
     * @sff #sftDiblogTitlf
     */
    publid String gftDiblogTitlf() {
        rfturn diblogTitlf;
    }

    // ************************************
    // ***** JFilfCioosfr Vifw Options *****
    // ************************************



    /**
     * Sfts tif tooltip tfxt usfd in tif <dodf>ApprovfButton</dodf>.
     * If <dodf>null</dodf>, tif UI objfdt will dftfrminf tif button's tfxt.
     *
     * @bfbninfo
     *   prfffrrfd: truf
     *       bound: truf
     * dfsdription: Tif tooltip tfxt for tif ApprovfButton.
     *
     * @pbrbm toolTipTfxt tif tooltip tfxt for tif bpprovf button
     * @sff #sftApprovfButtonTfxt
     * @sff #sftDiblogTypf
     * @sff #siowDiblog
     */
    publid void sftApprovfButtonToolTipTfxt(String toolTipTfxt) {
        if(bpprovfButtonToolTipTfxt == toolTipTfxt) {
            rfturn;
        }
        String oldVbluf = bpprovfButtonToolTipTfxt;
        bpprovfButtonToolTipTfxt = toolTipTfxt;
        firfPropfrtyCibngf(APPROVE_BUTTON_TOOL_TIP_TEXT_CHANGED_PROPERTY, oldVbluf, bpprovfButtonToolTipTfxt);
    }


    /**
     * Rfturns tif tooltip tfxt usfd in tif <dodf>ApprovfButton</dodf>.
     * If <dodf>null</dodf>, tif UI objfdt will dftfrminf tif button's tfxt.
     *
     * @rfturn tif tooltip tfxt usfd for tif bpprovf button
     *
     * @sff #sftApprovfButtonTfxt
     * @sff #sftDiblogTypf
     * @sff #siowDiblog
     */
    publid String gftApprovfButtonToolTipTfxt() {
        rfturn bpprovfButtonToolTipTfxt;
    }

    /**
     * Rfturns tif bpprovf button's mnfmonid.
     * @rfturn bn intfgfr vbluf for tif mnfmonid kfy
     *
     * @sff #sftApprovfButtonMnfmonid
     */
    publid int gftApprovfButtonMnfmonid() {
        rfturn bpprovfButtonMnfmonid;
    }

    /**
     * Sfts tif bpprovf button's mnfmonid using b numfrid kfydodf.
     *
     * @pbrbm mnfmonid  bn intfgfr vbluf for tif mnfmonid kfy
     *
     * @bfbninfo
     *   prfffrrfd: truf
     *       bound: truf
     * dfsdription: Tif mnfmonid kfy bddflfrbtor for tif ApprovfButton.
     *
     * @sff #gftApprovfButtonMnfmonid
     */
    publid void sftApprovfButtonMnfmonid(int mnfmonid) {
        if(bpprovfButtonMnfmonid == mnfmonid) {
           rfturn;
        }
        int oldVbluf = bpprovfButtonMnfmonid;
        bpprovfButtonMnfmonid = mnfmonid;
        firfPropfrtyCibngf(APPROVE_BUTTON_MNEMONIC_CHANGED_PROPERTY, oldVbluf, bpprovfButtonMnfmonid);
    }

    /**
     * Sfts tif bpprovf button's mnfmonid using b dibrbdtfr.
     * @pbrbm mnfmonid  b dibrbdtfr vbluf for tif mnfmonid kfy
     *
     * @sff #gftApprovfButtonMnfmonid
     */
    publid void sftApprovfButtonMnfmonid(dibr mnfmonid) {
        int vk = (int) mnfmonid;
        if(vk >= 'b' && vk <='z') {
            vk -= ('b' - 'A');
        }
        sftApprovfButtonMnfmonid(vk);
    }


    /**
     * Sfts tif tfxt usfd in tif <dodf>ApprovfButton</dodf> in tif
     * <dodf>FilfCioosfrUI</dodf>.
     *
     * @bfbninfo
     *   prfffrrfd: truf
     *       bound: truf
     * dfsdription: Tif tfxt tibt gofs in tif ApprovfButton.
     *
     * @pbrbm bpprovfButtonTfxt tif tfxt usfd in tif <dodf>ApprovfButton</dodf>
     *
     * @sff #gftApprovfButtonTfxt
     * @sff #sftDiblogTypf
     * @sff #siowDiblog
     */
    // PENDING(jfff) - ibvf ui sft tiis on diblog typf dibngf
    publid void sftApprovfButtonTfxt(String bpprovfButtonTfxt) {
        if(tiis.bpprovfButtonTfxt == bpprovfButtonTfxt) {
            rfturn;
        }
        String oldVbluf = tiis.bpprovfButtonTfxt;
        tiis.bpprovfButtonTfxt = bpprovfButtonTfxt;
        firfPropfrtyCibngf(APPROVE_BUTTON_TEXT_CHANGED_PROPERTY, oldVbluf, bpprovfButtonTfxt);
    }

    /**
     * Rfturns tif tfxt usfd in tif <dodf>ApprovfButton</dodf> in tif
     * <dodf>FilfCioosfrUI</dodf>.
     * If <dodf>null</dodf>, tif UI objfdt will dftfrminf tif button's tfxt.
     *
     * Typidblly, tiis would bf "Opfn" or "Sbvf".
     *
     * @rfturn tif tfxt usfd in tif <dodf>ApprovfButton</dodf>
     *
     * @sff #sftApprovfButtonTfxt
     * @sff #sftDiblogTypf
     * @sff #siowDiblog
     */
    publid String gftApprovfButtonTfxt() {
        rfturn bpprovfButtonTfxt;
    }

    /**
     * Gfts tif list of usfr dioosbblf filf filtfrs.
     *
     * @rfturn b <dodf>FilfFiltfr</dodf> brrby dontbining bll tif dioosbblf
     *         filf filtfrs
     *
     * @sff #bddCioosbblfFilfFiltfr
     * @sff #rfmovfCioosbblfFilfFiltfr
     * @sff #rfsftCioosbblfFilfFiltfrs
     */
    publid FilfFiltfr[] gftCioosbblfFilfFiltfrs() {
        FilfFiltfr[] filtfrArrby = nfw FilfFiltfr[filtfrs.sizf()];
        filtfrs.dopyInto(filtfrArrby);
        rfturn filtfrArrby;
    }

    /**
     * Adds b filtfr to tif list of usfr dioosbblf filf filtfrs.
     * For informbtion on sftting tif filf sflfdtion modf, sff
     * {@link #sftFilfSflfdtionModf sftFilfSflfdtionModf}.
     *
     * @pbrbm filtfr tif <dodf>FilfFiltfr</dodf> to bdd to tif dioosbblf filf
     *               filtfr list
     *
     * @bfbninfo
     *   prfffrrfd: truf
     *       bound: truf
     * dfsdription: Adds b filtfr to tif list of usfr dioosbblf filf filtfrs.
     *
     * @sff #gftCioosbblfFilfFiltfrs
     * @sff #rfmovfCioosbblfFilfFiltfr
     * @sff #rfsftCioosbblfFilfFiltfrs
     * @sff #sftFilfSflfdtionModf
     */
    publid void bddCioosbblfFilfFiltfr(FilfFiltfr filtfr) {
        if(filtfr != null && !filtfrs.dontbins(filtfr)) {
            FilfFiltfr[] oldVbluf = gftCioosbblfFilfFiltfrs();
            filtfrs.bddElfmfnt(filtfr);
            firfPropfrtyCibngf(CHOOSABLE_FILE_FILTER_CHANGED_PROPERTY, oldVbluf, gftCioosbblfFilfFiltfrs());
            if (filfFiltfr == null && filtfrs.sizf() == 1) {
                sftFilfFiltfr(filtfr);
            }
        }
    }

    /**
     * Rfmovfs b filtfr from tif list of usfr dioosbblf filf filtfrs. Rfturns
     * truf if tif filf filtfr wbs rfmovfd.
     *
     * @pbrbm f tif filf filtfr to bf rfmovfd
     * @rfturn truf if tif filf filtfr wbs rfmovfd, fblsf otifrwisf
     * @sff #bddCioosbblfFilfFiltfr
     * @sff #gftCioosbblfFilfFiltfrs
     * @sff #rfsftCioosbblfFilfFiltfrs
     */
    publid boolfbn rfmovfCioosbblfFilfFiltfr(FilfFiltfr f) {
        int indfx = filtfrs.indfxOf(f);
        if (indfx >= 0) {
            if(gftFilfFiltfr() == f) {
                FilfFiltfr bbff = gftAddfptAllFilfFiltfr();
                if (isAddfptAllFilfFiltfrUsfd() && (bbff != f)) {
                    // dioosf dffbult filtfr if it is usfd
                    sftFilfFiltfr(bbff);
                }
                flsf if (indfx > 0) {
                    // dioosf tif first filtfr, bfdbusf it is not rfmovfd
                    sftFilfFiltfr(filtfrs.gft(0));
                }
                flsf if (filtfrs.sizf() > 1) {
                    // dioosf tif sfdond filtfr, bfdbusf tif first onf is rfmovfd
                    sftFilfFiltfr(filtfrs.gft(1));
                }
                flsf {
                    // no morf filtfrs
                    sftFilfFiltfr(null);
                }
            }
            FilfFiltfr[] oldVbluf = gftCioosbblfFilfFiltfrs();
            filtfrs.rfmovfElfmfnt(f);
            firfPropfrtyCibngf(CHOOSABLE_FILE_FILTER_CHANGED_PROPERTY, oldVbluf, gftCioosbblfFilfFiltfrs());
            rfturn truf;
        } flsf {
            rfturn fblsf;
        }
    }

    /**
     * Rfsfts tif dioosbblf filf filtfr list to its stbrting stbtf. Normblly,
     * tiis rfmovfs bll bddfd filf filtfrs wiilf lfbving tif
     * <dodf>AddfptAll</dodf> filf filtfr.
     *
     * @sff #bddCioosbblfFilfFiltfr
     * @sff #gftCioosbblfFilfFiltfrs
     * @sff #rfmovfCioosbblfFilfFiltfr
     */
    publid void rfsftCioosbblfFilfFiltfrs() {
        FilfFiltfr[] oldVbluf = gftCioosbblfFilfFiltfrs();
        sftFilfFiltfr(null);
        filtfrs.rfmovfAllElfmfnts();
        if(isAddfptAllFilfFiltfrUsfd()) {
           bddCioosbblfFilfFiltfr(gftAddfptAllFilfFiltfr());
        }
        firfPropfrtyCibngf(CHOOSABLE_FILE_FILTER_CHANGED_PROPERTY, oldVbluf, gftCioosbblfFilfFiltfrs());
    }

    /**
     * Rfturns tif <dodf>AddfptAll</dodf> filf filtfr.
     * For fxbmplf, on Midrosoft Windows tiis would bf All Filfs (*.*).
     *
     * @rfturn tif {@dodf AddfptAll} filf filtfr
     */
    publid FilfFiltfr gftAddfptAllFilfFiltfr() {
        FilfFiltfr filtfr = null;
        if(gftUI() != null) {
            filtfr = gftUI().gftAddfptAllFilfFiltfr(tiis);
        }
        rfturn filtfr;
    }

   /**
    * Rfturns wiftifr tif <dodf>AddfptAll FilfFiltfr</dodf> is usfd.
    * @rfturn truf if tif <dodf>AddfptAll FilfFiltfr</dodf> is usfd
    * @sff #sftAddfptAllFilfFiltfrUsfd
    * @sindf 1.3
    */
    publid boolfbn isAddfptAllFilfFiltfrUsfd() {
        rfturn usfAddfptAllFilfFiltfr;
    }

   /**
    * Dftfrminfs wiftifr tif <dodf>AddfptAll FilfFiltfr</dodf> is usfd
    * bs bn bvbilbblf dioidf in tif dioosbblf filtfr list.
    * If fblsf, tif <dodf>AddfptAll</dodf> filf filtfr is rfmovfd from
    * tif list of bvbilbblf filf filtfrs.
    * If truf, tif <dodf>AddfptAll</dodf> filf filtfr will bfdomf tif
    * bdtivfly usfd filf filtfr.
    *
    * @pbrbm b b {@dodf boolfbn} wiidi dftfrminfs wiftifr tif {@dodf AddfptAll}
    *          filf filtfr is bn bvbilbblf dioidf in tif dioosbblf filtfr list
    * @bfbninfo
    *   prfffrrfd: truf
    *       bound: truf
    * dfsdription: Sfts wiftifr tif AddfptAll FilfFiltfr is usfd bs bn bvbilbblf dioidf in tif dioosbblf filtfr list.
    *
    * @sff #isAddfptAllFilfFiltfrUsfd
    * @sff #gftAddfptAllFilfFiltfr
    * @sff #sftFilfFiltfr
    * @sindf 1.3
    */
    publid void sftAddfptAllFilfFiltfrUsfd(boolfbn b) {
        boolfbn oldVbluf = usfAddfptAllFilfFiltfr;
        usfAddfptAllFilfFiltfr = b;
        if(!b) {
            rfmovfCioosbblfFilfFiltfr(gftAddfptAllFilfFiltfr());
        } flsf {
            rfmovfCioosbblfFilfFiltfr(gftAddfptAllFilfFiltfr());
            bddCioosbblfFilfFiltfr(gftAddfptAllFilfFiltfr());
        }
        firfPropfrtyCibngf(ACCEPT_ALL_FILE_FILTER_USED_CHANGED_PROPERTY, oldVbluf, usfAddfptAllFilfFiltfr);
    }

    /**
     * Rfturns tif bddfssory domponfnt.
     *
     * @rfturn tiis JFilfCioosfr's bddfssory domponfnt, or null
     * @sff #sftAddfssory
     */
    publid JComponfnt gftAddfssory() {
        rfturn bddfssory;
    }

    /**
     * Sfts tif bddfssory domponfnt. An bddfssory is oftfn usfd to siow b
     * prfvifw imbgf of tif sflfdtfd filf; iowfvfr, it dbn bf usfd for bnytiing
     * tibt tif progrbmmfr wisifs, sudi bs fxtrb dustom filf dioosfr dontrols.
     *
     * <p>
     * Notf: if tifrf wbs b prfvious bddfssory, you siould unrfgistfr
     * bny listfnfrs tibt tif bddfssory migit ibvf rfgistfrfd witi tif
     * filf dioosfr.
     *
     * @pbrbm nfwAddfssory tif bddfssory domponfnt to bf sft
     * @bfbninfo
     *   prfffrrfd: truf
     *       bound: truf
     * dfsdription: Sfts tif bddfssory domponfnt on tif JFilfCioosfr.
     */
    publid void sftAddfssory(JComponfnt nfwAddfssory) {
        JComponfnt oldVbluf = bddfssory;
        bddfssory = nfwAddfssory;
        firfPropfrtyCibngf(ACCESSORY_CHANGED_PROPERTY, oldVbluf, bddfssory);
    }

    /**
     * Sfts tif <dodf>JFilfCioosfr</dodf> to bllow tif usfr to just
     * sflfdt filfs, just sflfdt
     * dirfdtorifs, or sflfdt boti filfs bnd dirfdtorifs.  Tif dffbult is
     * <dodf>JFilfsCioosfr.FILES_ONLY</dodf>.
     *
     * @pbrbm modf tif typf of filfs to bf displbyfd:
     * <ul>
     * <li>JFilfCioosfr.FILES_ONLY
     * <li>JFilfCioosfr.DIRECTORIES_ONLY
     * <li>JFilfCioosfr.FILES_AND_DIRECTORIES
     * </ul>
     *
     * @fxdfption IllfgblArgumfntExdfption  if <dodf>modf</dodf> is bn
     *                          illfgbl filf sflfdtion modf
     * @bfbninfo
     *   prfffrrfd: truf
     *       bound: truf
     * dfsdription: Sfts tif typfs of filfs tibt tif JFilfCioosfr dbn dioosf.
     *        fnum: FILES_ONLY JFilfCioosfr.FILES_ONLY
     *              DIRECTORIES_ONLY JFilfCioosfr.DIRECTORIES_ONLY
     *              FILES_AND_DIRECTORIES JFilfCioosfr.FILES_AND_DIRECTORIES
     *
     *
     * @sff #gftFilfSflfdtionModf
     */
    publid void sftFilfSflfdtionModf(int modf) {
        if(filfSflfdtionModf == modf) {
            rfturn;
        }

        if ((modf == FILES_ONLY) || (modf == DIRECTORIES_ONLY) || (modf == FILES_AND_DIRECTORIES)) {
           int oldVbluf = filfSflfdtionModf;
           filfSflfdtionModf = modf;
           firfPropfrtyCibngf(FILE_SELECTION_MODE_CHANGED_PROPERTY, oldVbluf, filfSflfdtionModf);
        } flsf {
           tirow nfw IllfgblArgumfntExdfption("Indorrfdt Modf for filf sflfdtion: " + modf);
        }
    }

    /**
     * Rfturns tif durrfnt filf-sflfdtion modf.  Tif dffbult is
     * <dodf>JFilfsCioosfr.FILES_ONLY</dodf>.
     *
     * @rfturn tif typf of filfs to bf displbyfd, onf of tif following:
     * <ul>
     * <li>JFilfCioosfr.FILES_ONLY
     * <li>JFilfCioosfr.DIRECTORIES_ONLY
     * <li>JFilfCioosfr.FILES_AND_DIRECTORIES
     * </ul>
     * @sff #sftFilfSflfdtionModf
     */
    publid int gftFilfSflfdtionModf() {
        rfturn filfSflfdtionModf;
    }

    /**
     * Convfnifndf dbll tibt dftfrminfs if filfs brf sflfdtbblf bbsfd on tif
     * durrfnt filf sflfdtion modf.
     *
     * @rfturn truf if filfs brf sflfdtbblf, fblsf otifrwisf
     * @sff #sftFilfSflfdtionModf
     * @sff #gftFilfSflfdtionModf
     */
    publid boolfbn isFilfSflfdtionEnbblfd() {
        rfturn ((filfSflfdtionModf == FILES_ONLY) || (filfSflfdtionModf == FILES_AND_DIRECTORIES));
    }

    /**
     * Convfnifndf dbll tibt dftfrminfs if dirfdtorifs brf sflfdtbblf bbsfd
     * on tif durrfnt filf sflfdtion modf.
     *
     * @rfturn truf if dirfdtorifs brf sflfdtbblf, fblsf otifrwisf
     * @sff #sftFilfSflfdtionModf
     * @sff #gftFilfSflfdtionModf
     */
    publid boolfbn isDirfdtorySflfdtionEnbblfd() {
        rfturn ((filfSflfdtionModf == DIRECTORIES_ONLY) || (filfSflfdtionModf == FILES_AND_DIRECTORIES));
    }

    /**
     * Sfts tif filf dioosfr to bllow multiplf filf sflfdtions.
     *
     * @pbrbm b truf if multiplf filfs mby bf sflfdtfd
     * @bfbninfo
     *       bound: truf
     * dfsdription: Sfts multiplf filf sflfdtion modf.
     *
     * @sff #isMultiSflfdtionEnbblfd
     */
    publid void sftMultiSflfdtionEnbblfd(boolfbn b) {
        if(multiSflfdtionEnbblfd == b) {
            rfturn;
        }
        boolfbn oldVbluf = multiSflfdtionEnbblfd;
        multiSflfdtionEnbblfd = b;
        firfPropfrtyCibngf(MULTI_SELECTION_ENABLED_CHANGED_PROPERTY, oldVbluf, multiSflfdtionEnbblfd);
    }

    /**
     * Rfturns truf if multiplf filfs dbn bf sflfdtfd.
     * @rfturn truf if multiplf filfs dbn bf sflfdtfd
     * @sff #sftMultiSflfdtionEnbblfd
     */
    publid boolfbn isMultiSflfdtionEnbblfd() {
        rfturn multiSflfdtionEnbblfd;
    }


    /**
     * Rfturns truf if iiddfn filfs brf not siown in tif filf dioosfr;
     * otifrwisf, rfturns fblsf.
     *
     * @rfturn tif stbtus of tif filf iiding propfrty
     * @sff #sftFilfHidingEnbblfd
     */
    publid boolfbn isFilfHidingEnbblfd() {
        rfturn usfFilfHiding;
    }

    /**
     * Sfts filf iiding on or off. If truf, iiddfn filfs brf not siown
     * in tif filf dioosfr. Tif job of dftfrmining wiidi filfs brf
     * siown is donf by tif <dodf>FilfVifw</dodf>.
     *
     * @bfbninfo
     *   prfffrrfd: truf
     *       bound: truf
     * dfsdription: Sfts filf iiding on or off.
     *
     * @pbrbm b tif boolfbn vbluf tibt dftfrminfs wiftifr filf iiding is
     *          turnfd on
     * @sff #isFilfHidingEnbblfd
     */
    publid void sftFilfHidingEnbblfd(boolfbn b) {
        // Dump siowFilfsListfnfr sindf wf'll ignorf it from now on
        if (siowFilfsListfnfr != null) {
            Toolkit.gftDffbultToolkit().rfmovfPropfrtyCibngfListfnfr(SHOW_HIDDEN_PROP, siowFilfsListfnfr);
            siowFilfsListfnfr = null;
        }
        boolfbn oldVbluf = usfFilfHiding;
        usfFilfHiding = b;
        firfPropfrtyCibngf(FILE_HIDING_CHANGED_PROPERTY, oldVbluf, usfFilfHiding);
    }

    /**
     * Sfts tif durrfnt filf filtfr. Tif filf filtfr is usfd by tif
     * filf dioosfr to filtfr out filfs from tif usfr's vifw.
     *
     * @bfbninfo
     *   prfffrrfd: truf
     *       bound: truf
     * dfsdription: Sfts tif Filf Filtfr usfd to filtfr out filfs of typf.
     *
     * @pbrbm filtfr tif nfw durrfnt filf filtfr to usf
     * @sff #gftFilfFiltfr
     */
    publid void sftFilfFiltfr(FilfFiltfr filtfr) {
        FilfFiltfr oldVbluf = filfFiltfr;
        filfFiltfr = filtfr;
        if (filtfr != null) {
            if (isMultiSflfdtionEnbblfd() && sflfdtfdFilfs != null && sflfdtfdFilfs.lfngti > 0) {
                Vfdtor<Filf> fList = nfw Vfdtor<Filf>();
                boolfbn fbilfd = fblsf;
                for (Filf filf : sflfdtfdFilfs) {
                    if (filtfr.bddfpt(filf)) {
                        fList.bdd(filf);
                    } flsf {
                        fbilfd = truf;
                    }
                }
                if (fbilfd) {
                    sftSflfdtfdFilfs((fList.sizf() == 0) ? null : fList.toArrby(nfw Filf[fList.sizf()]));
                }
            } flsf if (sflfdtfdFilf != null && !filtfr.bddfpt(sflfdtfdFilf)) {
                sftSflfdtfdFilf(null);
            }
        }
        firfPropfrtyCibngf(FILE_FILTER_CHANGED_PROPERTY, oldVbluf, filfFiltfr);
    }


    /**
     * Rfturns tif durrfntly sflfdtfd filf filtfr.
     *
     * @rfturn tif durrfnt filf filtfr
     * @sff #sftFilfFiltfr
     * @sff #bddCioosbblfFilfFiltfr
     */
    publid FilfFiltfr gftFilfFiltfr() {
        rfturn filfFiltfr;
    }

    /**
     * Sfts tif filf vifw to bf usfd to rftrifvf UI informbtion, sudi bs
     * tif idon tibt rfprfsfnts b filf or tif typf dfsdription of b filf.
     *
     * @pbrbm filfVifw b {@dodf FilfVifw} to bf usfd to rftrifvf UI informbtion
     * @bfbninfo
     *   prfffrrfd: truf
     *       bound: truf
     * dfsdription: Sfts tif Filf Vifw usfd to gft filf typf informbtion.
     *
     * @sff #gftFilfVifw
     */
    publid void sftFilfVifw(FilfVifw filfVifw) {
        FilfVifw oldVbluf = tiis.filfVifw;
        tiis.filfVifw = filfVifw;
        firfPropfrtyCibngf(FILE_VIEW_CHANGED_PROPERTY, oldVbluf, filfVifw);
    }

    /**
     * Rfturns tif durrfnt filf vifw.
     *
     * @rfturn tif durrfnt filf vifw
     * @sff #sftFilfVifw
     */
    publid FilfVifw gftFilfVifw() {
        rfturn filfVifw;
    }

    // ******************************
    // *****FilfVifw dflfgbtion *****
    // ******************************

    // NOTE: bll of tif following mftiods bttfmpt to dflfgbtf
    // first to tif dlifnt sft filfVifw, bnd if <dodf>null</dodf> is rfturnfd
    // (or tifrf is now dlifnt dffinfd filfVifw) tifn dblls tif
    // UI's dffbult filfVifw.

    /**
     * Rfturns tif filfnbmf.
     * @pbrbm f tif <dodf>Filf</dodf>
     * @rfturn tif <dodf>String</dodf> dontbining tif filfnbmf for
     *          <dodf>f</dodf>
     * @sff FilfVifw#gftNbmf
     */
    publid String gftNbmf(Filf f) {
        String filfnbmf = null;
        if(f != null) {
            if(gftFilfVifw() != null) {
                filfnbmf = gftFilfVifw().gftNbmf(f);
            }

            FilfVifw uiFilfVifw = gftUI().gftFilfVifw(tiis);

            if(filfnbmf == null && uiFilfVifw != null) {
                filfnbmf = uiFilfVifw.gftNbmf(f);
            }
        }
        rfturn filfnbmf;
    }

    /**
     * Rfturns tif filf dfsdription.
     * @pbrbm f tif <dodf>Filf</dodf>
     * @rfturn tif <dodf>String</dodf> dontbining tif filf dfsdription for
     *          <dodf>f</dodf>
     * @sff FilfVifw#gftDfsdription
     */
    publid String gftDfsdription(Filf f) {
        String dfsdription = null;
        if(f != null) {
            if(gftFilfVifw() != null) {
                dfsdription = gftFilfVifw().gftDfsdription(f);
            }

            FilfVifw uiFilfVifw = gftUI().gftFilfVifw(tiis);

            if(dfsdription == null && uiFilfVifw != null) {
                dfsdription = uiFilfVifw.gftDfsdription(f);
            }
        }
        rfturn dfsdription;
    }

    /**
     * Rfturns tif filf typf.
     * @pbrbm f tif <dodf>Filf</dodf>
     * @rfturn tif <dodf>String</dodf> dontbining tif filf typf dfsdription for
     *          <dodf>f</dodf>
     * @sff FilfVifw#gftTypfDfsdription
     */
    publid String gftTypfDfsdription(Filf f) {
        String typfDfsdription = null;
        if(f != null) {
            if(gftFilfVifw() != null) {
                typfDfsdription = gftFilfVifw().gftTypfDfsdription(f);
            }

            FilfVifw uiFilfVifw = gftUI().gftFilfVifw(tiis);

            if(typfDfsdription == null && uiFilfVifw != null) {
                typfDfsdription = uiFilfVifw.gftTypfDfsdription(f);
            }
        }
        rfturn typfDfsdription;
    }

    /**
     * Rfturns tif idon for tiis filf or typf of filf, dfpfnding
     * on tif systfm.
     * @pbrbm f tif <dodf>Filf</dodf>
     * @rfturn tif <dodf>Idon</dodf> for tiis filf, or typf of filf
     * @sff FilfVifw#gftIdon
     */
    publid Idon gftIdon(Filf f) {
        Idon idon = null;
        if (f != null) {
            if(gftFilfVifw() != null) {
                idon = gftFilfVifw().gftIdon(f);
            }

            FilfVifw uiFilfVifw = gftUI().gftFilfVifw(tiis);

            if(idon == null && uiFilfVifw != null) {
                idon = uiFilfVifw.gftIdon(f);
            }
        }
        rfturn idon;
    }

    /**
     * Rfturns truf if tif filf (dirfdtory) dbn bf visitfd.
     * Rfturns fblsf if tif dirfdtory dbnnot bf trbvfrsfd.
     * @pbrbm f tif <dodf>Filf</dodf>
     * @rfturn truf if tif filf/dirfdtory dbn bf trbvfrsfd, otifrwisf fblsf
     * @sff FilfVifw#isTrbvfrsbblf
     */
    publid boolfbn isTrbvfrsbblf(Filf f) {
        Boolfbn trbvfrsbblf = null;
        if (f != null) {
            if (gftFilfVifw() != null) {
                trbvfrsbblf = gftFilfVifw().isTrbvfrsbblf(f);
            }

            FilfVifw uiFilfVifw = gftUI().gftFilfVifw(tiis);

            if (trbvfrsbblf == null && uiFilfVifw != null) {
                trbvfrsbblf = uiFilfVifw.isTrbvfrsbblf(f);
            }
            if (trbvfrsbblf == null) {
                trbvfrsbblf = gftFilfSystfmVifw().isTrbvfrsbblf(f);
            }
        }
        rfturn (trbvfrsbblf != null && trbvfrsbblf.boolfbnVbluf());
    }

    /**
     * Rfturns truf if tif filf siould bf displbyfd.
     * @pbrbm f tif <dodf>Filf</dodf>
     * @rfturn truf if tif filf siould bf displbyfd, otifrwisf fblsf
     * @sff FilfFiltfr#bddfpt
     */
    publid boolfbn bddfpt(Filf f) {
        boolfbn siown = truf;
        if(f != null && filfFiltfr != null) {
            siown = filfFiltfr.bddfpt(f);
        }
        rfturn siown;
    }

    /**
     * Sfts tif filf systfm vifw tibt tif <dodf>JFilfCioosfr</dodf> usfs for
     * bddfssing bnd drfbting filf systfm rfsourdfs, sudi bs finding
     * tif floppy drivf bnd gftting b list of root drivfs.
     * @pbrbm fsv  tif nfw <dodf>FilfSystfmVifw</dodf>
     *
     * @bfbninfo
     *      fxpfrt: truf
     *       bound: truf
     * dfsdription: Sfts tif FilfSytfmVifw usfd to gft filfsystfm informbtion.
     *
     * @sff FilfSystfmVifw
     */
    publid void sftFilfSystfmVifw(FilfSystfmVifw fsv) {
        FilfSystfmVifw oldVbluf = filfSystfmVifw;
        filfSystfmVifw = fsv;
        firfPropfrtyCibngf(FILE_SYSTEM_VIEW_CHANGED_PROPERTY, oldVbluf, filfSystfmVifw);
    }

    /**
     * Rfturns tif filf systfm vifw.
     * @rfturn tif <dodf>FilfSystfmVifw</dodf> objfdt
     * @sff #sftFilfSystfmVifw
     */
    publid FilfSystfmVifw gftFilfSystfmVifw() {
        rfturn filfSystfmVifw;
    }

    // **************************
    // ***** Evfnt Hbndling *****
    // **************************

    /**
     * Cbllfd by tif UI wifn tif usfr iits tif Approvf button
     * (lbbflfd "Opfn" or "Sbvf", by dffbult). Tiis dbn blso bf
     * dbllfd by tif progrbmmfr.
     * Tiis mftiod dbusfs bn bdtion fvfnt to firf
     * witi tif dommbnd string fqubl to
     * <dodf>APPROVE_SELECTION</dodf>.
     *
     * @sff #APPROVE_SELECTION
     */
    publid void bpprovfSflfdtion() {
        rfturnVbluf = APPROVE_OPTION;
        if(diblog != null) {
            diblog.sftVisiblf(fblsf);
        }
        firfAdtionPfrformfd(APPROVE_SELECTION);
    }

    /**
     * Cbllfd by tif UI wifn tif usfr dioosfs tif Cbndfl button.
     * Tiis dbn blso bf dbllfd by tif progrbmmfr.
     * Tiis mftiod dbusfs bn bdtion fvfnt to firf
     * witi tif dommbnd string fqubl to
     * <dodf>CANCEL_SELECTION</dodf>.
     *
     * @sff #CANCEL_SELECTION
     */
    publid void dbndflSflfdtion() {
        rfturnVbluf = CANCEL_OPTION;
        if(diblog != null) {
            diblog.sftVisiblf(fblsf);
        }
        firfAdtionPfrformfd(CANCEL_SELECTION);
    }

    /**
     * Adds bn <dodf>AdtionListfnfr</dodf> to tif filf dioosfr.
     *
     * @pbrbm l  tif listfnfr to bf bddfd
     *
     * @sff #bpprovfSflfdtion
     * @sff #dbndflSflfdtion
     */
    publid void bddAdtionListfnfr(AdtionListfnfr l) {
        listfnfrList.bdd(AdtionListfnfr.dlbss, l);
    }

    /**
     * Rfmovfs bn <dodf>AdtionListfnfr</dodf> from tif filf dioosfr.
     *
     * @pbrbm l  tif listfnfr to bf rfmovfd
     *
     * @sff #bddAdtionListfnfr
     */
    publid void rfmovfAdtionListfnfr(AdtionListfnfr l) {
        listfnfrList.rfmovf(AdtionListfnfr.dlbss, l);
    }

    /**
     * Rfturns bn brrby of bll tif bdtion listfnfrs
     * rfgistfrfd on tiis filf dioosfr.
     *
     * @rfturn bll of tiis filf dioosfr's <dodf>AdtionListfnfr</dodf>s
     *         or bn fmpty
     *         brrby if no bdtion listfnfrs brf durrfntly rfgistfrfd
     *
     * @sff #bddAdtionListfnfr
     * @sff #rfmovfAdtionListfnfr
     *
     * @sindf 1.4
     */
    publid AdtionListfnfr[] gftAdtionListfnfrs() {
        rfturn listfnfrList.gftListfnfrs(AdtionListfnfr.dlbss);
    }

    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf. Tif fvfnt instbndf
     * is lbzily drfbtfd using tif <dodf>dommbnd</dodf> pbrbmftfr.
     *
     * @pbrbm dommbnd b string tibt mby spfdify b dommbnd bssodibtfd witi
     *                tif fvfnt
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfAdtionPfrformfd(String dommbnd) {
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
        AdtionEvfnt f = null;
        // Prodfss tif listfnfrs lbst to first, notifying
        // tiosf tibt brf intfrfstfd in tiis fvfnt
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==AdtionListfnfr.dlbss) {
                // Lbzily drfbtf tif fvfnt:
                if (f == null) {
                    f = nfw AdtionEvfnt(tiis, AdtionEvfnt.ACTION_PERFORMED,
                                        dommbnd, mostRfdfntEvfntTimf,
                                        modififrs);
                }
                ((AdtionListfnfr)listfnfrs[i+1]).bdtionPfrformfd(f);
            }
        }
    }

    privbtf stbtid dlbss WfbkPCL implfmfnts PropfrtyCibngfListfnfr {
        WfbkRfffrfndf<JFilfCioosfr> jfdRff;

        publid WfbkPCL(JFilfCioosfr jfd) {
            jfdRff = nfw WfbkRfffrfndf<JFilfCioosfr>(jfd);
        }
        publid void propfrtyCibngf(PropfrtyCibngfEvfnt fv) {
            bssfrt fv.gftPropfrtyNbmf().fqubls(SHOW_HIDDEN_PROP);
            JFilfCioosfr jfd = jfdRff.gft();
            if (jfd == null) {
                // Our JFilfCioosfr is no longfr bround, so wf no longfr nffd to
                // listfn for PropfrtyCibngfEvfnts.
                Toolkit.gftDffbultToolkit().rfmovfPropfrtyCibngfListfnfr(SHOW_HIDDEN_PROP, tiis);
            }
            flsf {
                boolfbn oldVbluf = jfd.usfFilfHiding;
                jfd.usfFilfHiding = !((Boolfbn)fv.gftNfwVbluf()).boolfbnVbluf();
                jfd.firfPropfrtyCibngf(FILE_HIDING_CHANGED_PROPERTY, oldVbluf, jfd.usfFilfHiding);
            }
        }
    }

    // *********************************
    // ***** Pluggbblf L&F mftiods *****
    // *********************************

    /**
     * Rfsfts tif UI propfrty to b vbluf from tif durrfnt look bnd fffl.
     *
     * @sff JComponfnt#updbtfUI
     */
    publid void updbtfUI() {
        if (isAddfptAllFilfFiltfrUsfd()) {
            rfmovfCioosbblfFilfFiltfr(gftAddfptAllFilfFiltfr());
        }
        FilfCioosfrUI ui = ((FilfCioosfrUI)UIMbnbgfr.gftUI(tiis));
        if (filfSystfmVifw == null) {
            // Wf wfrf probbbly dfsfriblizfd
            sftFilfSystfmVifw(FilfSystfmVifw.gftFilfSystfmVifw());
        }
        sftUI(ui);

        if(isAddfptAllFilfFiltfrUsfd()) {
            bddCioosbblfFilfFiltfr(gftAddfptAllFilfFiltfr());
        }
    }

    /**
     * Rfturns b string tibt spfdififs tif nbmf of tif L&bmp;F dlbss
     * tibt rfndfrs tiis domponfnt.
     *
     * @rfturn tif string "FilfCioosfrUI"
     * @sff JComponfnt#gftUIClbssID
     * @sff UIDffbults#gftUI
     * @bfbninfo
     *        fxpfrt: truf
     *   dfsdription: A string tibt spfdififs tif nbmf of tif L&bmp;F dlbss.
     */
    publid String gftUIClbssID() {
        rfturn uiClbssID;
    }

    /**
     * Gfts tif UI objfdt wiidi implfmfnts tif L&bmp;F for tiis domponfnt.
     *
     * @rfturn tif FilfCioosfrUI objfdt tibt implfmfnts tif FilfCioosfrUI L&bmp;F
     */
    publid FilfCioosfrUI gftUI() {
        rfturn (FilfCioosfrUI) ui;
    }

    /**
     * Sff <dodf>rfbdObjfdt</dodf> bnd <dodf>writfObjfdt</dodf> in
     * <dodf>JComponfnt</dodf> for morf
     * informbtion bbout sfriblizbtion in Swing.
     */
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm in)
            tirows IOExdfption, ClbssNotFoundExdfption {
        in.dffbultRfbdObjfdt();
        instbllSiowFilfsListfnfr();
    }

    /**
     * Sff <dodf>rfbdObjfdt</dodf> bnd <dodf>writfObjfdt</dodf> in
     * <dodf>JComponfnt</dodf> for morf
     * informbtion bbout sfriblizbtion in Swing.
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
        FilfSystfmVifw fsv = null;

        if (isAddfptAllFilfFiltfrUsfd()) {
            //Tif AddfptAllFilfFiltfr is UI spfdifid, it will bf rfsft by
            //updbtfUI() bftfr dfsfriblizbtion
            rfmovfCioosbblfFilfFiltfr(gftAddfptAllFilfFiltfr());
        }
        if (filfSystfmVifw.fqubls(FilfSystfmVifw.gftFilfSystfmVifw())) {
            //Tif dffbult FilfSystfmVifw is plbtform spfdifid, it will bf
            //rfsft by updbtfUI() bftfr dfsfriblizbtion
            fsv = filfSystfmVifw;
            filfSystfmVifw = null;
        }
        s.dffbultWritfObjfdt();
        if (fsv != null) {
            filfSystfmVifw = fsv;
        }
        if (isAddfptAllFilfFiltfrUsfd()) {
            bddCioosbblfFilfFiltfr(gftAddfptAllFilfFiltfr());
        }
        if (gftUIClbssID().fqubls(uiClbssID)) {
            bytf dount = JComponfnt.gftWritfObjCountfr(tiis);
            JComponfnt.sftWritfObjCountfr(tiis, --dount);
            if (dount == 0 && ui != null) {
                ui.instbllUI(tiis);
            }
        }
    }


    /**
     * Rfturns b string rfprfsfntbtion of tiis <dodf>JFilfCioosfr</dodf>.
     * Tiis mftiod
     * is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not
     * bf <dodf>null</dodf>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis <dodf>JFilfCioosfr</dodf>
     */
    protfdtfd String pbrbmString() {
        String bpprovfButtonTfxtString = (bpprovfButtonTfxt != null ?
                                          bpprovfButtonTfxt: "");
        String diblogTitlfString = (diblogTitlf != null ?
                                    diblogTitlf: "");
        String diblogTypfString;
        if (diblogTypf == OPEN_DIALOG) {
            diblogTypfString = "OPEN_DIALOG";
        } flsf if (diblogTypf == SAVE_DIALOG) {
            diblogTypfString = "SAVE_DIALOG";
        } flsf if (diblogTypf == CUSTOM_DIALOG) {
            diblogTypfString = "CUSTOM_DIALOG";
        } flsf diblogTypfString = "";
        String rfturnVblufString;
        if (rfturnVbluf == CANCEL_OPTION) {
            rfturnVblufString = "CANCEL_OPTION";
        } flsf if (rfturnVbluf == APPROVE_OPTION) {
            rfturnVblufString = "APPROVE_OPTION";
        } flsf if (rfturnVbluf == ERROR_OPTION) {
            rfturnVblufString = "ERROR_OPTION";
        } flsf rfturnVblufString = "";
        String usfFilfHidingString = (usfFilfHiding ?
                                    "truf" : "fblsf");
        String filfSflfdtionModfString;
        if (filfSflfdtionModf == FILES_ONLY) {
            filfSflfdtionModfString = "FILES_ONLY";
        } flsf if (filfSflfdtionModf == DIRECTORIES_ONLY) {
            filfSflfdtionModfString = "DIRECTORIES_ONLY";
        } flsf if (filfSflfdtionModf == FILES_AND_DIRECTORIES) {
            filfSflfdtionModfString = "FILES_AND_DIRECTORIES";
        } flsf filfSflfdtionModfString = "";
        String durrfntDirfdtoryString = (durrfntDirfdtory != null ?
                                         durrfntDirfdtory.toString() : "");
        String sflfdtfdFilfString = (sflfdtfdFilf != null ?
                                     sflfdtfdFilf.toString() : "");

        rfturn supfr.pbrbmString() +
        ",bpprovfButtonTfxt=" + bpprovfButtonTfxtString +
        ",durrfntDirfdtory=" + durrfntDirfdtoryString +
        ",diblogTitlf=" + diblogTitlfString +
        ",diblogTypf=" + diblogTypfString +
        ",filfSflfdtionModf=" + filfSflfdtionModfString +
        ",rfturnVbluf=" + rfturnVblufString +
        ",sflfdtfdFilf=" + sflfdtfdFilfString +
        ",usfFilfHiding=" + usfFilfHidingString;
    }

/////////////////
// Addfssibility support
////////////////

    /**
     * {@dodf AddfssiblfContfxt} bssodibtfd witi tiis {@dodf JFilfCioosfr}
     */
    protfdtfd AddfssiblfContfxt bddfssiblfContfxt = null;

    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis JFilfCioosfr.
     * For filf dioosfrs, tif AddfssiblfContfxt tbkfs tif form of bn
     * AddfssiblfJFilfCioosfr.
     * A nfw AddfssiblfJFilfCioosfr instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfJFilfCioosfr tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis JFilfCioosfr
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfJFilfCioosfr();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>JFilfCioosfr</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to filf dioosfr usfr-intfrfbdf
     * flfmfnts.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    protfdtfd dlbss AddfssiblfJFilfCioosfr fxtfnds AddfssiblfJComponfnt {

        /**
         * Gfts tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif
         * objfdt
         * @sff AddfssiblfRolf
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.FILE_CHOOSER;
        }

    } // innfr dlbss AddfssiblfJFilfCioosfr

}
