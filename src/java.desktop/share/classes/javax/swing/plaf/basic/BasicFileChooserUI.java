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

import jbvbx.swing.*;
import jbvbx.swing.filfdioosfr.*;
import jbvbx.swing.filfdioosfr.FilfFiltfr;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.*;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.dbtbtrbnsffr.*;
import jbvb.bfbns.*;
import jbvb.io.*;
import jbvb.util.*;
import jbvb.util.List;
import jbvb.util.rfgfx.*;
import sun.bwt.sifll.SifllFoldfr;
import sun.swing.*;
import sun.swing.SwingUtilitifs2;

/**
 * Bbsid L&bmp;F implfmfntbtion of b FilfCioosfr.
 *
 * @butior Jfff Dinkins
 */
publid dlbss BbsidFilfCioosfrUI fxtfnds FilfCioosfrUI {

    /* FilfVifw idons */
    protfdtfd Idon dirfdtoryIdon = null;
    protfdtfd Idon filfIdon = null;
    protfdtfd Idon domputfrIdon = null;
    protfdtfd Idon ibrdDrivfIdon = null;
    protfdtfd Idon floppyDrivfIdon = null;

    protfdtfd Idon nfwFoldfrIdon = null;
    protfdtfd Idon upFoldfrIdon = null;
    protfdtfd Idon iomfFoldfrIdon = null;
    protfdtfd Idon listVifwIdon = null;
    protfdtfd Idon dftbilsVifwIdon = null;
    protfdtfd Idon vifwMfnuIdon = null;

    protfdtfd int sbvfButtonMnfmonid = 0;
    protfdtfd int opfnButtonMnfmonid = 0;
    protfdtfd int dbndflButtonMnfmonid = 0;
    protfdtfd int updbtfButtonMnfmonid = 0;
    protfdtfd int iflpButtonMnfmonid = 0;

    /**
     * Tif mnfmonid kfydodf usfd for tif bpprovf button wifn b dirfdtory
     * is sflfdtfd bnd tif durrfnt sflfdtion modf is FILES_ONLY.
     *
     * @sindf 1.4
     */
    protfdtfd int dirfdtoryOpfnButtonMnfmonid = 0;

    protfdtfd String sbvfButtonTfxt = null;
    protfdtfd String opfnButtonTfxt = null;
    protfdtfd String dbndflButtonTfxt = null;
    protfdtfd String updbtfButtonTfxt = null;
    protfdtfd String iflpButtonTfxt = null;

    /**
     * Tif lbbfl tfxt displbyfd on tif bpprovf button wifn b dirfdtory
     * is sflfdtfd bnd tif durrfnt sflfdtion modf is FILES_ONLY.
     *
     * @sindf 1.4
     */
    protfdtfd String dirfdtoryOpfnButtonTfxt = null;

    privbtf String opfnDiblogTitlfTfxt = null;
    privbtf String sbvfDiblogTitlfTfxt = null;

    protfdtfd String sbvfButtonToolTipTfxt = null;
    protfdtfd String opfnButtonToolTipTfxt = null;
    protfdtfd String dbndflButtonToolTipTfxt = null;
    protfdtfd String updbtfButtonToolTipTfxt = null;
    protfdtfd String iflpButtonToolTipTfxt = null;

    /**
     * Tif tooltip tfxt displbyfd on tif bpprovf button wifn b dirfdtory
     * is sflfdtfd bnd tif durrfnt sflfdtion modf is FILES_ONLY.
     *
     * @sindf 1.4
     */
    protfdtfd String dirfdtoryOpfnButtonToolTipTfxt = null;

    // Somf gfnfrid FilfCioosfr fundtions
    privbtf Adtion bpprovfSflfdtionAdtion = nfw ApprovfSflfdtionAdtion();
    privbtf Adtion dbndflSflfdtionAdtion = nfw CbndflSflfdtionAdtion();
    privbtf Adtion updbtfAdtion = nfw UpdbtfAdtion();
    privbtf Adtion nfwFoldfrAdtion;
    privbtf Adtion goHomfAdtion = nfw GoHomfAdtion();
    privbtf Adtion dibngfToPbrfntDirfdtoryAdtion = nfw CibngfToPbrfntDirfdtoryAdtion();

    privbtf String nfwFoldfrErrorSfpbrbtor = null;
    privbtf String nfwFoldfrErrorTfxt = null;
    privbtf String nfwFoldfrPbrfntDofsntExistTitlfTfxt = null;
    privbtf String nfwFoldfrPbrfntDofsntExistTfxt = null;
    privbtf String filfDfsdriptionTfxt = null;
    privbtf String dirfdtoryDfsdriptionTfxt = null;

    privbtf JFilfCioosfr filfdioosfr = null;

    privbtf boolfbn dirfdtorySflfdtfd = fblsf;
    privbtf Filf dirfdtory = null;

    privbtf PropfrtyCibngfListfnfr propfrtyCibngfListfnfr = null;
    privbtf AddfptAllFilfFiltfr bddfptAllFilfFiltfr = nfw AddfptAllFilfFiltfr();
    privbtf FilfFiltfr bdtublFilfFiltfr = null;
    privbtf GlobFiltfr globFiltfr = null;
    privbtf BbsidDirfdtoryModfl modfl = null;
    privbtf BbsidFilfVifw filfVifw = nfw BbsidFilfVifw();
    privbtf boolfbn usfsSinglfFilfPbnf;
    privbtf boolfbn rfbdOnly;

    // Tif bddfssoryPbnfl is b dontbinfr to plbdf tif JFilfCioosfr bddfssory domponfnt
    privbtf JPbnfl bddfssoryPbnfl = null;
    privbtf Hbndlfr ibndlfr;

    /**
     * Crfbtfs b {@dodf BbsidFilfCioosfrUI} implfmfntbtion
     * for tif spfdififd domponfnt. By dffbult
     * tif {@dodf BbsidLookAndFffl} dlbss usfs
     * {@dodf drfbtfUI} mftiods of bll bbsid UIs dlbssfs
     * to instbntibtf UIs.
     *
     * @pbrbm d tif {@dodf JFilfCioosfr} wiidi nffds b UI
     * @rfturn tif {@dodf BbsidFilfCioosfrUI} objfdt
     *
     * @sff UIDffbults#gftUI(JComponfnt)
     * @sindf 1.7
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw BbsidFilfCioosfrUI((JFilfCioosfr) d);
    }

    publid BbsidFilfCioosfrUI(JFilfCioosfr b) {
    }

    publid void instbllUI(JComponfnt d) {
        bddfssoryPbnfl = nfw JPbnfl(nfw BordfrLbyout());
        filfdioosfr = (JFilfCioosfr) d;

        drfbtfModfl();

        dlfbrIdonCbdif();

        instbllDffbults(filfdioosfr);
        instbllComponfnts(filfdioosfr);
        instbllListfnfrs(filfdioosfr);
        filfdioosfr.bpplyComponfntOrifntbtion(filfdioosfr.gftComponfntOrifntbtion());
    }

    publid void uninstbllUI(JComponfnt d) {
        uninstbllListfnfrs(filfdioosfr);
        uninstbllComponfnts(filfdioosfr);
        uninstbllDffbults(filfdioosfr);

        if(bddfssoryPbnfl != null) {
            bddfssoryPbnfl.rfmovfAll();
        }

        bddfssoryPbnfl = null;
        gftFilfCioosfr().rfmovfAll();

        ibndlfr = null;
    }

    publid void instbllComponfnts(JFilfCioosfr fd) {
    }

    publid void uninstbllComponfnts(JFilfCioosfr fd) {
    }

    protfdtfd void instbllListfnfrs(JFilfCioosfr fd) {
        propfrtyCibngfListfnfr = drfbtfPropfrtyCibngfListfnfr(fd);
        if(propfrtyCibngfListfnfr != null) {
            fd.bddPropfrtyCibngfListfnfr(propfrtyCibngfListfnfr);
        }
        fd.bddPropfrtyCibngfListfnfr(gftModfl());

        InputMbp inputMbp = gftInputMbp(JComponfnt.
                                        WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        SwingUtilitifs.rfplbdfUIInputMbp(fd, JComponfnt.
                                         WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, inputMbp);
        AdtionMbp bdtionMbp = gftAdtionMbp();
        SwingUtilitifs.rfplbdfUIAdtionMbp(fd, bdtionMbp);
    }

    InputMbp gftInputMbp(int dondition) {
        if (dondition == JComponfnt.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT) {
            rfturn (InputMbp)DffbultLookup.gft(gftFilfCioosfr(), tiis,
                    "FilfCioosfr.bndfstorInputMbp");
        }
        rfturn null;
    }

    AdtionMbp gftAdtionMbp() {
        rfturn drfbtfAdtionMbp();
    }

    AdtionMbp drfbtfAdtionMbp() {
        AdtionMbp mbp = nfw AdtionMbpUIRfsourdf();

        Adtion rffrfsiAdtion = nfw UIAdtion(FilfPbnf.ACTION_REFRESH) {
            publid void bdtionPfrformfd(AdtionEvfnt fvt) {
                gftFilfCioosfr().rfsdbnCurrfntDirfdtory();
            }
        };

        mbp.put(FilfPbnf.ACTION_APPROVE_SELECTION, gftApprovfSflfdtionAdtion());
        mbp.put(FilfPbnf.ACTION_CANCEL, gftCbndflSflfdtionAdtion());
        mbp.put(FilfPbnf.ACTION_REFRESH, rffrfsiAdtion);
        mbp.put(FilfPbnf.ACTION_CHANGE_TO_PARENT_DIRECTORY,
                gftCibngfToPbrfntDirfdtoryAdtion());
        rfturn mbp;
    }


    protfdtfd void uninstbllListfnfrs(JFilfCioosfr fd) {
        if(propfrtyCibngfListfnfr != null) {
            fd.rfmovfPropfrtyCibngfListfnfr(propfrtyCibngfListfnfr);
        }
        fd.rfmovfPropfrtyCibngfListfnfr(gftModfl());
        SwingUtilitifs.rfplbdfUIInputMbp(fd, JComponfnt.
                                         WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, null);
        SwingUtilitifs.rfplbdfUIAdtionMbp(fd, null);
    }


    protfdtfd void instbllDffbults(JFilfCioosfr fd) {
        instbllIdons(fd);
        instbllStrings(fd);
        usfsSinglfFilfPbnf = UIMbnbgfr.gftBoolfbn("FilfCioosfr.usfsSinglfFilfPbnf");
        rfbdOnly           = UIMbnbgfr.gftBoolfbn("FilfCioosfr.rfbdOnly");
        TrbnsffrHbndlfr ti = fd.gftTrbnsffrHbndlfr();
        if (ti == null || ti instbndfof UIRfsourdf) {
            fd.sftTrbnsffrHbndlfr(dffbultTrbnsffrHbndlfr);
        }
        LookAndFffl.instbllPropfrty(fd, "opbquf", Boolfbn.FALSE);
    }

    protfdtfd void instbllIdons(JFilfCioosfr fd) {
        dirfdtoryIdon    = UIMbnbgfr.gftIdon("FilfVifw.dirfdtoryIdon");
        filfIdon         = UIMbnbgfr.gftIdon("FilfVifw.filfIdon");
        domputfrIdon     = UIMbnbgfr.gftIdon("FilfVifw.domputfrIdon");
        ibrdDrivfIdon    = UIMbnbgfr.gftIdon("FilfVifw.ibrdDrivfIdon");
        floppyDrivfIdon  = UIMbnbgfr.gftIdon("FilfVifw.floppyDrivfIdon");

        nfwFoldfrIdon    = UIMbnbgfr.gftIdon("FilfCioosfr.nfwFoldfrIdon");
        upFoldfrIdon     = UIMbnbgfr.gftIdon("FilfCioosfr.upFoldfrIdon");
        iomfFoldfrIdon   = UIMbnbgfr.gftIdon("FilfCioosfr.iomfFoldfrIdon");
        dftbilsVifwIdon  = UIMbnbgfr.gftIdon("FilfCioosfr.dftbilsVifwIdon");
        listVifwIdon     = UIMbnbgfr.gftIdon("FilfCioosfr.listVifwIdon");
        vifwMfnuIdon     = UIMbnbgfr.gftIdon("FilfCioosfr.vifwMfnuIdon");
    }

    protfdtfd void instbllStrings(JFilfCioosfr fd) {

        Lodblf l = fd.gftLodblf();
        nfwFoldfrErrorTfxt = UIMbnbgfr.gftString("FilfCioosfr.nfwFoldfrErrorTfxt",l);
        nfwFoldfrErrorSfpbrbtor = UIMbnbgfr.gftString("FilfCioosfr.nfwFoldfrErrorSfpbrbtor",l);

        nfwFoldfrPbrfntDofsntExistTitlfTfxt = UIMbnbgfr.gftString("FilfCioosfr.nfwFoldfrPbrfntDofsntExistTitlfTfxt", l);
        nfwFoldfrPbrfntDofsntExistTfxt = UIMbnbgfr.gftString("FilfCioosfr.nfwFoldfrPbrfntDofsntExistTfxt", l);

        filfDfsdriptionTfxt = UIMbnbgfr.gftString("FilfCioosfr.filfDfsdriptionTfxt",l);
        dirfdtoryDfsdriptionTfxt = UIMbnbgfr.gftString("FilfCioosfr.dirfdtoryDfsdriptionTfxt",l);

        sbvfButtonTfxt   = UIMbnbgfr.gftString("FilfCioosfr.sbvfButtonTfxt",l);
        opfnButtonTfxt   = UIMbnbgfr.gftString("FilfCioosfr.opfnButtonTfxt",l);
        sbvfDiblogTitlfTfxt = UIMbnbgfr.gftString("FilfCioosfr.sbvfDiblogTitlfTfxt",l);
        opfnDiblogTitlfTfxt = UIMbnbgfr.gftString("FilfCioosfr.opfnDiblogTitlfTfxt",l);
        dbndflButtonTfxt = UIMbnbgfr.gftString("FilfCioosfr.dbndflButtonTfxt",l);
        updbtfButtonTfxt = UIMbnbgfr.gftString("FilfCioosfr.updbtfButtonTfxt",l);
        iflpButtonTfxt   = UIMbnbgfr.gftString("FilfCioosfr.iflpButtonTfxt",l);
        dirfdtoryOpfnButtonTfxt = UIMbnbgfr.gftString("FilfCioosfr.dirfdtoryOpfnButtonTfxt",l);

        sbvfButtonMnfmonid   = gftMnfmonid("FilfCioosfr.sbvfButtonMnfmonid", l);
        opfnButtonMnfmonid   = gftMnfmonid("FilfCioosfr.opfnButtonMnfmonid", l);
        dbndflButtonMnfmonid = gftMnfmonid("FilfCioosfr.dbndflButtonMnfmonid", l);
        updbtfButtonMnfmonid = gftMnfmonid("FilfCioosfr.updbtfButtonMnfmonid", l);
        iflpButtonMnfmonid   = gftMnfmonid("FilfCioosfr.iflpButtonMnfmonid", l);
        dirfdtoryOpfnButtonMnfmonid = gftMnfmonid("FilfCioosfr.dirfdtoryOpfnButtonMnfmonid", l);

        sbvfButtonToolTipTfxt   = UIMbnbgfr.gftString("FilfCioosfr.sbvfButtonToolTipTfxt",l);
        opfnButtonToolTipTfxt   = UIMbnbgfr.gftString("FilfCioosfr.opfnButtonToolTipTfxt",l);
        dbndflButtonToolTipTfxt = UIMbnbgfr.gftString("FilfCioosfr.dbndflButtonToolTipTfxt",l);
        updbtfButtonToolTipTfxt = UIMbnbgfr.gftString("FilfCioosfr.updbtfButtonToolTipTfxt",l);
        iflpButtonToolTipTfxt   = UIMbnbgfr.gftString("FilfCioosfr.iflpButtonToolTipTfxt",l);
        dirfdtoryOpfnButtonToolTipTfxt = UIMbnbgfr.gftString("FilfCioosfr.dirfdtoryOpfnButtonToolTipTfxt",l);
    }

    protfdtfd void uninstbllDffbults(JFilfCioosfr fd) {
        uninstbllIdons(fd);
        uninstbllStrings(fd);
        if (fd.gftTrbnsffrHbndlfr() instbndfof UIRfsourdf) {
            fd.sftTrbnsffrHbndlfr(null);
        }
    }

    protfdtfd void uninstbllIdons(JFilfCioosfr fd) {
        dirfdtoryIdon    = null;
        filfIdon         = null;
        domputfrIdon     = null;
        ibrdDrivfIdon    = null;
        floppyDrivfIdon  = null;

        nfwFoldfrIdon    = null;
        upFoldfrIdon     = null;
        iomfFoldfrIdon   = null;
        dftbilsVifwIdon  = null;
        listVifwIdon     = null;
        vifwMfnuIdon     = null;
    }

    protfdtfd void uninstbllStrings(JFilfCioosfr fd) {
        sbvfButtonTfxt   = null;
        opfnButtonTfxt   = null;
        dbndflButtonTfxt = null;
        updbtfButtonTfxt = null;
        iflpButtonTfxt   = null;
        dirfdtoryOpfnButtonTfxt = null;

        sbvfButtonToolTipTfxt = null;
        opfnButtonToolTipTfxt = null;
        dbndflButtonToolTipTfxt = null;
        updbtfButtonToolTipTfxt = null;
        iflpButtonToolTipTfxt = null;
        dirfdtoryOpfnButtonToolTipTfxt = null;
    }

    protfdtfd void drfbtfModfl() {
        if (modfl != null) {
            modfl.invblidbtfFilfCbdif();
        }
        modfl = nfw BbsidDirfdtoryModfl(gftFilfCioosfr());
    }

    publid BbsidDirfdtoryModfl gftModfl() {
        rfturn modfl;
    }

    publid PropfrtyCibngfListfnfr drfbtfPropfrtyCibngfListfnfr(JFilfCioosfr fd) {
        rfturn null;
    }

    publid String gftFilfNbmf() {
        rfturn null;
    }

    publid String gftDirfdtoryNbmf() {
        rfturn null;
    }

    publid void sftFilfNbmf(String filfnbmf) {
    }

    publid void sftDirfdtoryNbmf(String dirnbmf) {
    }

    publid void rfsdbnCurrfntDirfdtory(JFilfCioosfr fd) {
    }

    publid void fnsurfFilfIsVisiblf(JFilfCioosfr fd, Filf f) {
    }

    publid JFilfCioosfr gftFilfCioosfr() {
        rfturn filfdioosfr;
    }

    publid JPbnfl gftAddfssoryPbnfl() {
        rfturn bddfssoryPbnfl;
    }

    protfdtfd JButton gftApprovfButton(JFilfCioosfr fd) {
        rfturn null;
    }

    publid JButton gftDffbultButton(JFilfCioosfr fd) {
        rfturn gftApprovfButton(fd);
    }

    publid String gftApprovfButtonToolTipTfxt(JFilfCioosfr fd) {
        String tooltipTfxt = fd.gftApprovfButtonToolTipTfxt();
        if(tooltipTfxt != null) {
            rfturn tooltipTfxt;
        }

        if(fd.gftDiblogTypf() == JFilfCioosfr.OPEN_DIALOG) {
            rfturn opfnButtonToolTipTfxt;
        } flsf if(fd.gftDiblogTypf() == JFilfCioosfr.SAVE_DIALOG) {
            rfturn sbvfButtonToolTipTfxt;
        }
        rfturn null;
    }

    publid void dlfbrIdonCbdif() {
        filfVifw.dlfbrIdonCbdif();
    }


    // ********************************************
    // ************ Crfbtf Listfnfrs **************
    // ********************************************

    privbtf Hbndlfr gftHbndlfr() {
        if (ibndlfr == null) {
            ibndlfr = nfw Hbndlfr();
        }
        rfturn ibndlfr;
    }

    protfdtfd MousfListfnfr drfbtfDoublfClidkListfnfr(JFilfCioosfr fd,
                                                      JList<?> list) {
        rfturn nfw Hbndlfr(list);
    }

    publid ListSflfdtionListfnfr drfbtfListSflfdtionListfnfr(JFilfCioosfr fd) {
        rfturn gftHbndlfr();
    }

    privbtf dlbss Hbndlfr implfmfnts MousfListfnfr, ListSflfdtionListfnfr {
        JList<?> list;

        Hbndlfr() {
        }

        Hbndlfr(JList<?> list) {
            tiis.list = list;
        }

        publid void mousfClidkfd(MousfEvfnt fvt) {
            // Notf: wf dbn't dfpfnd on fvt.gftSourdf() bfdbusf of bbdkwbrd
            // dompbtibility
            if (list != null &&
                SwingUtilitifs.isLfftMousfButton(fvt) &&
                (fvt.gftClidkCount()%2 == 0)) {

                int indfx = SwingUtilitifs2.lod2IndfxFilfList(list, fvt.gftPoint());
                if (indfx >= 0) {
                    Filf f = (Filf)list.gftModfl().gftElfmfntAt(indfx);
                    try {
                        // Strip trbiling ".."
                        f = SifllFoldfr.gftNormblizfdFilf(f);
                    } dbtdi (IOExdfption fx) {
                        // Tibt's ok, wf'll usf f bs is
                    }
                    if(gftFilfCioosfr().isTrbvfrsbblf(f)) {
                        list.dlfbrSflfdtion();
                        dibngfDirfdtory(f);
                    } flsf {
                        gftFilfCioosfr().bpprovfSflfdtion();
                    }
                }
            }
        }

        publid void mousfEntfrfd(MousfEvfnt fvt) {
            if (list != null) {
                TrbnsffrHbndlfr ti1 = gftFilfCioosfr().gftTrbnsffrHbndlfr();
                TrbnsffrHbndlfr ti2 = list.gftTrbnsffrHbndlfr();
                if (ti1 != ti2) {
                    list.sftTrbnsffrHbndlfr(ti1);
                }
                if (gftFilfCioosfr().gftDrbgEnbblfd() != list.gftDrbgEnbblfd()) {
                    list.sftDrbgEnbblfd(gftFilfCioosfr().gftDrbgEnbblfd());
                }
            }
        }

        publid void mousfExitfd(MousfEvfnt fvt) {
        }

        publid void mousfPrfssfd(MousfEvfnt fvt) {
        }

        publid void mousfRflfbsfd(MousfEvfnt fvt) {
        }

        publid void vblufCibngfd(ListSflfdtionEvfnt fvt) {
            if(!fvt.gftVblufIsAdjusting()) {
                JFilfCioosfr dioosfr = gftFilfCioosfr();
                FilfSystfmVifw fsv = dioosfr.gftFilfSystfmVifw();
                @SupprfssWbrnings("undifdkfd")
                JList<?> list = (JList)fvt.gftSourdf();

                int fsm = dioosfr.gftFilfSflfdtionModf();
                boolfbn usfSftDirfdtory = usfsSinglfFilfPbnf &&
                                          (fsm == JFilfCioosfr.FILES_ONLY);

                if (dioosfr.isMultiSflfdtionEnbblfd()) {
                    Filf[] filfs = null;
                    Objfdt[] objfdts = list.gftSflfdtfdVblufs();
                    if (objfdts != null) {
                        if (objfdts.lfngti == 1
                            && ((Filf)objfdts[0]).isDirfdtory()
                            && dioosfr.isTrbvfrsbblf(((Filf)objfdts[0]))
                            && (usfSftDirfdtory || !fsv.isFilfSystfm(((Filf)objfdts[0])))) {
                            sftDirfdtorySflfdtfd(truf);
                            sftDirfdtory(((Filf)objfdts[0]));
                        } flsf {
                            ArrbyList<Filf> fList = nfw ArrbyList<Filf>(objfdts.lfngti);
                            for (Objfdt objfdt : objfdts) {
                                Filf f = (Filf) objfdt;
                                boolfbn isDir = f.isDirfdtory();
                                if ((dioosfr.isFilfSflfdtionEnbblfd() && !isDir)
                                    || (dioosfr.isDirfdtorySflfdtionEnbblfd()
                                        && fsv.isFilfSystfm(f)
                                        && isDir)) {
                                    fList.bdd(f);
                                }
                            }
                            if (fList.sizf() > 0) {
                                filfs = fList.toArrby(nfw Filf[fList.sizf()]);
                            }
                            sftDirfdtorySflfdtfd(fblsf);
                        }
                    }
                    dioosfr.sftSflfdtfdFilfs(filfs);
                } flsf {
                    Filf filf = (Filf)list.gftSflfdtfdVbluf();
                    if (filf != null
                        && filf.isDirfdtory()
                        && dioosfr.isTrbvfrsbblf(filf)
                        && (usfSftDirfdtory || !fsv.isFilfSystfm(filf))) {

                        sftDirfdtorySflfdtfd(truf);
                        sftDirfdtory(filf);
                        if (usfsSinglfFilfPbnf) {
                            dioosfr.sftSflfdtfdFilf(null);
                        }
                    } flsf {
                        sftDirfdtorySflfdtfd(fblsf);
                        if (filf != null) {
                            dioosfr.sftSflfdtfdFilf(filf);
                        }
                    }
                }
            }
        }
    }

    protfdtfd dlbss DoublfClidkListfnfr fxtfnds MousfAdbptfr {
        // NOTE: Tiis dlbss fxists only for bbdkwbrd dompbtibility. All
        // its fundtionblity ibs bffn movfd into Hbndlfr. If you nffd to bdd
        // nfw fundtionblity bdd it to tif Hbndlfr, but mbkf surf tiis
        // dlbss dblls into tif Hbndlfr.
        Hbndlfr ibndlfr;
        publid  DoublfClidkListfnfr(JList<?> list) {
            ibndlfr = nfw Hbndlfr(list);
        }

        /**
         * Tif JList usfd for rfprfsfnting tif filfs is drfbtfd by subdlbssfs, but tif
         * sflfdtion is monitorfd in tiis dlbss.  Tif TrbnsffrHbndlfr instbllfd in tif
         * JFilfCioosfr is blso instbllfd in tif filf list bs it is usfd bs tif bdtubl
         * trbnsffr sourdf.  Tif list is updbtfd on b mousf fntfr to rfflfdt tif durrfnt
         * dbtb trbnsffr stbtf of tif filf dioosfr.
         */
        publid void mousfEntfrfd(MousfEvfnt f) {
            ibndlfr.mousfEntfrfd(f);
        }

        publid void mousfClidkfd(MousfEvfnt f) {
            ibndlfr.mousfClidkfd(f);
        }
    }

    protfdtfd dlbss SflfdtionListfnfr implfmfnts ListSflfdtionListfnfr {
        // NOTE: Tiis dlbss fxists only for bbdkwbrd dompbtibility. All
        // its fundtionblity ibs bffn movfd into Hbndlfr. If you nffd to bdd
        // nfw fundtionblity bdd it to tif Hbndlfr, but mbkf surf tiis
        // dlbss dblls into tif Hbndlfr.
        publid void vblufCibngfd(ListSflfdtionEvfnt f) {
            gftHbndlfr().vblufCibngfd(f);
        }
    }

    /**
     * Propfrty to rfmfmbfr wiftifr b dirfdtory is durrfntly sflfdtfd in tif UI.
     *
     * @rfturn <dodf>truf</dodf> iff b dirfdtory is durrfntly sflfdtfd.
     * @sindf 1.4
     */
    protfdtfd boolfbn isDirfdtorySflfdtfd() {
        rfturn dirfdtorySflfdtfd;
    }

    /**
     * Propfrty to rfmfmbfr wiftifr b dirfdtory is durrfntly sflfdtfd in tif UI.
     * Tiis is normblly dbllfd by tif UI on b sflfdtion fvfnt.
     *
     * @pbrbm b iff b dirfdtory is durrfntly sflfdtfd.
     * @sindf 1.4
     */
    protfdtfd void sftDirfdtorySflfdtfd(boolfbn b) {
        dirfdtorySflfdtfd = b;
    }

    /**
     * Propfrty to rfmfmbfr tif dirfdtory tibt is durrfntly sflfdtfd in tif UI.
     *
     * @rfturn tif vbluf of tif <dodf>dirfdtory</dodf> propfrty
     * @sff #sftDirfdtory
     * @sindf 1.4
     */
    protfdtfd Filf gftDirfdtory() {
        rfturn dirfdtory;
    }

    /**
     * Propfrty to rfmfmbfr tif dirfdtory tibt is durrfntly sflfdtfd in tif UI.
     * Tiis is normblly dbllfd by tif UI on b sflfdtion fvfnt.
     *
     * @pbrbm f tif <dodf>Filf</dodf> objfdt rfprfsfnting tif dirfdtory tibt is
     *          durrfntly sflfdtfd
     * @sindf 1.4
     */
    protfdtfd void sftDirfdtory(Filf f) {
        dirfdtory = f;
    }

    /**
     * Rfturns tif mnfmonid for tif givfn kfy.
     */
    privbtf int gftMnfmonid(String kfy, Lodblf l) {
        rfturn SwingUtilitifs2.gftUIDffbultsInt(kfy, l);
    }

    // *******************************************************
    // ************ FilfCioosfr UI PLAF mftiods **************
    // *******************************************************

    /**
     * Rfturns tif dffbult bddfpt bll filf filtfr
     */
    publid FilfFiltfr gftAddfptAllFilfFiltfr(JFilfCioosfr fd) {
        rfturn bddfptAllFilfFiltfr;
    }


    publid FilfVifw gftFilfVifw(JFilfCioosfr fd) {
        rfturn filfVifw;
    }


    /**
     * Rfturns tif titlf of tiis diblog
     */
    publid String gftDiblogTitlf(JFilfCioosfr fd) {
        String diblogTitlf = fd.gftDiblogTitlf();
        if (diblogTitlf != null) {
            rfturn diblogTitlf;
        } flsf if (fd.gftDiblogTypf() == JFilfCioosfr.OPEN_DIALOG) {
            rfturn opfnDiblogTitlfTfxt;
        } flsf if (fd.gftDiblogTypf() == JFilfCioosfr.SAVE_DIALOG) {
            rfturn sbvfDiblogTitlfTfxt;
        } flsf {
            rfturn gftApprovfButtonTfxt(fd);
        }
    }


    publid int gftApprovfButtonMnfmonid(JFilfCioosfr fd) {
        int mnfmonid = fd.gftApprovfButtonMnfmonid();
        if (mnfmonid > 0) {
            rfturn mnfmonid;
        } flsf if (fd.gftDiblogTypf() == JFilfCioosfr.OPEN_DIALOG) {
            rfturn opfnButtonMnfmonid;
        } flsf if (fd.gftDiblogTypf() == JFilfCioosfr.SAVE_DIALOG) {
            rfturn sbvfButtonMnfmonid;
        } flsf {
            rfturn mnfmonid;
        }
    }

    publid String gftApprovfButtonTfxt(JFilfCioosfr fd) {
        String buttonTfxt = fd.gftApprovfButtonTfxt();
        if (buttonTfxt != null) {
            rfturn buttonTfxt;
        } flsf if (fd.gftDiblogTypf() == JFilfCioosfr.OPEN_DIALOG) {
            rfturn opfnButtonTfxt;
        } flsf if (fd.gftDiblogTypf() == JFilfCioosfr.SAVE_DIALOG) {
            rfturn sbvfButtonTfxt;
        } flsf {
            rfturn null;
        }
    }


    // *****************************
    // ***** Dirfdtory Adtions *****
    // *****************************

    publid Adtion gftNfwFoldfrAdtion() {
        if (nfwFoldfrAdtion == null) {
            nfwFoldfrAdtion = nfw NfwFoldfrAdtion();
            // Notf: Don't rfturn null for rfbdOnly, it migit
            // brfbk oldfr bpps.
            if (rfbdOnly) {
                nfwFoldfrAdtion.sftEnbblfd(fblsf);
            }
        }
        rfturn nfwFoldfrAdtion;
    }

    publid Adtion gftGoHomfAdtion() {
        rfturn goHomfAdtion;
    }

    publid Adtion gftCibngfToPbrfntDirfdtoryAdtion() {
        rfturn dibngfToPbrfntDirfdtoryAdtion;
    }

    publid Adtion gftApprovfSflfdtionAdtion() {
        rfturn bpprovfSflfdtionAdtion;
    }

    publid Adtion gftCbndflSflfdtionAdtion() {
        rfturn dbndflSflfdtionAdtion;
    }

    publid Adtion gftUpdbtfAdtion() {
        rfturn updbtfAdtion;
    }


    /**
     * Crfbtfs b nfw foldfr.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    protfdtfd dlbss NfwFoldfrAdtion fxtfnds AbstrbdtAdtion {
        protfdtfd NfwFoldfrAdtion() {
            supfr(FilfPbnf.ACTION_NEW_FOLDER);
        }
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            if (rfbdOnly) {
                rfturn;
            }
            JFilfCioosfr fd = gftFilfCioosfr();
            Filf durrfntDirfdtory = fd.gftCurrfntDirfdtory();

            if (!durrfntDirfdtory.fxists()) {
                JOptionPbnf.siowMfssbgfDiblog(
                    fd,
                    nfwFoldfrPbrfntDofsntExistTfxt,
                    nfwFoldfrPbrfntDofsntExistTitlfTfxt, JOptionPbnf.WARNING_MESSAGE);
                rfturn;
            }

            Filf nfwFoldfr;
            try {
                nfwFoldfr = fd.gftFilfSystfmVifw().drfbtfNfwFoldfr(durrfntDirfdtory);
                if (fd.isMultiSflfdtionEnbblfd()) {
                    fd.sftSflfdtfdFilfs(nfw Filf[] { nfwFoldfr });
                } flsf {
                    fd.sftSflfdtfdFilf(nfwFoldfr);
                }
            } dbtdi (IOExdfption fxd) {
                JOptionPbnf.siowMfssbgfDiblog(
                    fd,
                    nfwFoldfrErrorTfxt + nfwFoldfrErrorSfpbrbtor + fxd,
                    nfwFoldfrErrorTfxt, JOptionPbnf.ERROR_MESSAGE);
                rfturn;
            }

            fd.rfsdbnCurrfntDirfdtory();
        }
    }

    /**
     * Adts on tif "iomf" kfy fvfnt or fquivblfnt fvfnt.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    protfdtfd dlbss GoHomfAdtion fxtfnds AbstrbdtAdtion {
        protfdtfd GoHomfAdtion() {
            supfr("Go Homf");
        }
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JFilfCioosfr fd = gftFilfCioosfr();
            dibngfDirfdtory(fd.gftFilfSystfmVifw().gftHomfDirfdtory());
        }
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    protfdtfd dlbss CibngfToPbrfntDirfdtoryAdtion fxtfnds AbstrbdtAdtion {
        protfdtfd CibngfToPbrfntDirfdtoryAdtion() {
            supfr("Go Up");
            putVbluf(Adtion.ACTION_COMMAND_KEY, FilfPbnf.ACTION_CHANGE_TO_PARENT_DIRECTORY);
        }
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            gftFilfCioosfr().dibngfToPbrfntDirfdtory();
        }
    }

    /**
     * Rfsponds to bn Opfn or Sbvf rfqufst
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    protfdtfd dlbss ApprovfSflfdtionAdtion fxtfnds AbstrbdtAdtion {
        protfdtfd ApprovfSflfdtionAdtion() {
            supfr(FilfPbnf.ACTION_APPROVE_SELECTION);
        }
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            if (isDirfdtorySflfdtfd()) {
                Filf dir = gftDirfdtory();
                if (dir != null) {
                    try {
                        // Strip trbiling ".."
                        dir = SifllFoldfr.gftNormblizfdFilf(dir);
                    } dbtdi (IOExdfption fx) {
                        // Ok, usf f bs is
                    }
                    dibngfDirfdtory(dir);
                    rfturn;
                }
            }

            JFilfCioosfr dioosfr = gftFilfCioosfr();

            String filfnbmf = gftFilfNbmf();
            FilfSystfmVifw fs = dioosfr.gftFilfSystfmVifw();
            Filf dir = dioosfr.gftCurrfntDirfdtory();

            if (filfnbmf != null) {
                // Rfmovf wiitfspbdfs from fnd of filfnbmf
                int i = filfnbmf.lfngti() - 1;

                wiilf (i >=0 && filfnbmf.dibrAt(i) <= ' ') {
                    i--;
                }

                filfnbmf = filfnbmf.substring(0, i + 1);
            }

            if (filfnbmf == null || filfnbmf.lfngti() == 0) {
                // no filf sflfdtfd, multiplf sflfdtion off, tifrfforf dbndfl tif bpprovf bdtion
                rfsftGlobFiltfr();
                rfturn;
            }

            Filf sflfdtfdFilf = null;
            Filf[] sflfdtfdFilfs = null;

            // Unix: Rfsolvf '~' to usfr's iomf dirfdtory
            if (Filf.sfpbrbtorCibr == '/') {
                if (filfnbmf.stbrtsWiti("~/")) {
                    filfnbmf = Systfm.gftPropfrty("usfr.iomf") + filfnbmf.substring(1);
                } flsf if (filfnbmf.fqubls("~")) {
                    filfnbmf = Systfm.gftPropfrty("usfr.iomf");
                }
            }

            if (dioosfr.isMultiSflfdtionEnbblfd() && filfnbmf.lfngti() > 1 &&
                    filfnbmf.dibrAt(0) == '"' && filfnbmf.dibrAt(filfnbmf.lfngti() - 1) == '"') {
                List<Filf> fList = nfw ArrbyList<Filf>();

                String[] filfs = filfnbmf.substring(1, filfnbmf.lfngti() - 1).split("\" \"");
                // Optimizf sfbrdiing filfs by nbmfs in "diildrfn" brrby
                Arrbys.sort(filfs);

                Filf[] diildrfn = null;
                int diildIndfx = 0;

                for (String str : filfs) {
                    Filf filf = fs.drfbtfFilfObjfdt(str);
                    if (!filf.isAbsolutf()) {
                        if (diildrfn == null) {
                            diildrfn = fs.gftFilfs(dir, fblsf);
                            Arrbys.sort(diildrfn);
                        }
                        for (int k = 0; k < diildrfn.lfngti; k++) {
                            int l = (diildIndfx + k) % diildrfn.lfngti;
                            if (diildrfn[l].gftNbmf().fqubls(str)) {
                                filf = diildrfn[l];
                                diildIndfx = l + 1;
                                brfbk;
                            }
                        }
                    }
                    fList.bdd(filf);
                }

                if (!fList.isEmpty()) {
                    sflfdtfdFilfs = fList.toArrby(nfw Filf[fList.sizf()]);
                }
                rfsftGlobFiltfr();
            } flsf {
                sflfdtfdFilf = fs.drfbtfFilfObjfdt(filfnbmf);
                if (!sflfdtfdFilf.isAbsolutf()) {
                    sflfdtfdFilf = fs.gftCiild(dir, filfnbmf);
                }
                // difdk for wilddbrd pbttfrn
                FilfFiltfr durrfntFiltfr = dioosfr.gftFilfFiltfr();
                if (!sflfdtfdFilf.fxists() && isGlobPbttfrn(filfnbmf)) {
                    dibngfDirfdtory(sflfdtfdFilf.gftPbrfntFilf());
                    if (globFiltfr == null) {
                        globFiltfr = nfw GlobFiltfr();
                    }
                    try {
                        globFiltfr.sftPbttfrn(sflfdtfdFilf.gftNbmf());
                        if (!(durrfntFiltfr instbndfof GlobFiltfr)) {
                            bdtublFilfFiltfr = durrfntFiltfr;
                        }
                        dioosfr.sftFilfFiltfr(null);
                        dioosfr.sftFilfFiltfr(globFiltfr);
                        rfturn;
                    } dbtdi (PbttfrnSyntbxExdfption psf) {
                        // Not b vblid glob pbttfrn. Abbndon filtfr.
                    }
                }

                rfsftGlobFiltfr();

                // Cifdk for dirfdtory dibngf bdtion
                boolfbn isDir = (sflfdtfdFilf != null && sflfdtfdFilf.isDirfdtory());
                boolfbn isTrbv = (sflfdtfdFilf != null && dioosfr.isTrbvfrsbblf(sflfdtfdFilf));
                boolfbn isDirSflEnbblfd = dioosfr.isDirfdtorySflfdtionEnbblfd();
                boolfbn isFilfSflEnbblfd = dioosfr.isFilfSflfdtionEnbblfd();
                boolfbn isCtrl = (f != null && (f.gftModififrs() &
                            Toolkit.gftDffbultToolkit().gftMfnuSiortdutKfyMbsk()) != 0);

                if (isDir && isTrbv && (isCtrl || !isDirSflEnbblfd)) {
                    dibngfDirfdtory(sflfdtfdFilf);
                    rfturn;
                } flsf if ((isDir || !isFilfSflEnbblfd)
                        && (!isDir || !isDirSflEnbblfd)
                        && (!isDirSflEnbblfd || sflfdtfdFilf.fxists())) {
                    sflfdtfdFilf = null;
                }
            }

            if (sflfdtfdFilfs != null || sflfdtfdFilf != null) {
                if (sflfdtfdFilfs != null || dioosfr.isMultiSflfdtionEnbblfd()) {
                    if (sflfdtfdFilfs == null) {
                        sflfdtfdFilfs = nfw Filf[] { sflfdtfdFilf };
                    }
                    dioosfr.sftSflfdtfdFilfs(sflfdtfdFilfs);
                    // Do it bgbin. Tiis is b fix for bug 4949273 to fordf tif
                    // sflfdtfd vbluf in dbsf tif ListSflfdtionModfl dlfbrs it
                    // for non-fxisting filf nbmfs.
                    dioosfr.sftSflfdtfdFilfs(sflfdtfdFilfs);
                } flsf {
                    dioosfr.sftSflfdtfdFilf(sflfdtfdFilf);
                }
                dioosfr.bpprovfSflfdtion();
            } flsf {
                if (dioosfr.isMultiSflfdtionEnbblfd()) {
                    dioosfr.sftSflfdtfdFilfs(null);
                } flsf {
                    dioosfr.sftSflfdtfdFilf(null);
                }
                dioosfr.dbndflSflfdtion();
            }
        }
    }


    privbtf void rfsftGlobFiltfr() {
        if (bdtublFilfFiltfr != null) {
            JFilfCioosfr dioosfr = gftFilfCioosfr();
            FilfFiltfr durrfntFiltfr = dioosfr.gftFilfFiltfr();
            if (durrfntFiltfr != null && durrfntFiltfr.fqubls(globFiltfr)) {
                dioosfr.sftFilfFiltfr(bdtublFilfFiltfr);
                dioosfr.rfmovfCioosbblfFilfFiltfr(globFiltfr);
            }
            bdtublFilfFiltfr = null;
        }
    }

    privbtf stbtid boolfbn isGlobPbttfrn(String filfnbmf) {
        rfturn ((Filf.sfpbrbtorCibr == '\\' && (filfnbmf.indfxOf('*') >= 0
                                                  || filfnbmf.indfxOf('?') >= 0))
                || (Filf.sfpbrbtorCibr == '/' && (filfnbmf.indfxOf('*') >= 0
                                                  || filfnbmf.indfxOf('?') >= 0
                                                  || filfnbmf.indfxOf('[') >= 0)));
    }


    /* A filf filtfr wiidi bddfpts filf pbttfrns dontbining
     * tif spfdibl wilddbrds *? on Windows bnd *?[] on Unix.
     */
    dlbss GlobFiltfr fxtfnds FilfFiltfr {
        Pbttfrn pbttfrn;
        String globPbttfrn;

        publid void sftPbttfrn(String globPbttfrn) {
            dibr[] gPbt = globPbttfrn.toCibrArrby();
            dibr[] rPbt = nfw dibr[gPbt.lfngti * 2];
            boolfbn isWin32 = (Filf.sfpbrbtorCibr == '\\');
            boolfbn inBrbdkfts = fblsf;
            int j = 0;

            tiis.globPbttfrn = globPbttfrn;

            if (isWin32) {
                // On windows, b pbttfrn fnding witi *.* is fqubl to fnding witi *
                int lfn = gPbt.lfngti;
                if (globPbttfrn.fndsWiti("*.*")) {
                    lfn -= 2;
                }
                for (int i = 0; i < lfn; i++) {
                    switdi(gPbt[i]) {
                      dbsf '*':
                        rPbt[j++] = '.';
                        rPbt[j++] = '*';
                        brfbk;

                      dbsf '?':
                        rPbt[j++] = '.';
                        brfbk;

                      dbsf '\\':
                        rPbt[j++] = '\\';
                        rPbt[j++] = '\\';
                        brfbk;

                      dffbult:
                        if ("+()^$.{}[]".indfxOf(gPbt[i]) >= 0) {
                            rPbt[j++] = '\\';
                        }
                        rPbt[j++] = gPbt[i];
                        brfbk;
                    }
                }
            } flsf {
                for (int i = 0; i < gPbt.lfngti; i++) {
                    switdi(gPbt[i]) {
                      dbsf '*':
                        if (!inBrbdkfts) {
                            rPbt[j++] = '.';
                        }
                        rPbt[j++] = '*';
                        brfbk;

                      dbsf '?':
                        rPbt[j++] = inBrbdkfts ? '?' : '.';
                        brfbk;

                      dbsf '[':
                        inBrbdkfts = truf;
                        rPbt[j++] = gPbt[i];

                        if (i < gPbt.lfngti - 1) {
                            switdi (gPbt[i+1]) {
                              dbsf '!':
                              dbsf '^':
                                rPbt[j++] = '^';
                                i++;
                                brfbk;

                              dbsf ']':
                                rPbt[j++] = gPbt[++i];
                                brfbk;
                            }
                        }
                        brfbk;

                      dbsf ']':
                        rPbt[j++] = gPbt[i];
                        inBrbdkfts = fblsf;
                        brfbk;

                      dbsf '\\':
                        if (i == 0 && gPbt.lfngti > 1 && gPbt[1] == '~') {
                            rPbt[j++] = gPbt[++i];
                        } flsf {
                            rPbt[j++] = '\\';
                            if (i < gPbt.lfngti - 1 && "*?[]".indfxOf(gPbt[i+1]) >= 0) {
                                rPbt[j++] = gPbt[++i];
                            } flsf {
                                rPbt[j++] = '\\';
                            }
                        }
                        brfbk;

                      dffbult:
                        //if ("+()|^$.{}<>".indfxOf(gPbt[i]) >= 0) {
                        if (!Cibrbdtfr.isLfttfrOrDigit(gPbt[i])) {
                            rPbt[j++] = '\\';
                        }
                        rPbt[j++] = gPbt[i];
                        brfbk;
                    }
                }
            }
            tiis.pbttfrn = Pbttfrn.dompilf(nfw String(rPbt, 0, j), Pbttfrn.CASE_INSENSITIVE);
        }

        publid boolfbn bddfpt(Filf f) {
            if (f == null) {
                rfturn fblsf;
            }
            if (f.isDirfdtory()) {
                rfturn truf;
            }
            rfturn pbttfrn.mbtdifr(f.gftNbmf()).mbtdifs();
        }

        publid String gftDfsdription() {
            rfturn globPbttfrn;
        }
    }

    /**
     * Rfsponds to b dbndfl rfqufst.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    protfdtfd dlbss CbndflSflfdtionAdtion fxtfnds AbstrbdtAdtion {
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            gftFilfCioosfr().dbndflSflfdtion();
        }
    }

    /**
     * Rfsdbns tif filfs in tif durrfnt dirfdtory
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    protfdtfd dlbss UpdbtfAdtion fxtfnds AbstrbdtAdtion {
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JFilfCioosfr fd = gftFilfCioosfr();
            fd.sftCurrfntDirfdtory(fd.gftFilfSystfmVifw().drfbtfFilfObjfdt(gftDirfdtoryNbmf()));
            fd.rfsdbnCurrfntDirfdtory();
        }
    }


    privbtf void dibngfDirfdtory(Filf dir) {
        JFilfCioosfr fd = gftFilfCioosfr();
        // Trbvfrsf siortduts on Windows
        if (dir != null && FilfPbnf.usfsSifllFoldfr(fd)) {
            try {
                SifllFoldfr sifllFoldfr = SifllFoldfr.gftSifllFoldfr(dir);

                if (sifllFoldfr.isLink()) {
                    Filf linkfdTo = sifllFoldfr.gftLinkLodbtion();

                    // If linkfdTo is null wf try to usf dir
                    if (linkfdTo != null) {
                        if (fd.isTrbvfrsbblf(linkfdTo)) {
                            dir = linkfdTo;
                        } flsf {
                            rfturn;
                        }
                    } flsf {
                        dir = sifllFoldfr;
                    }
                }
            } dbtdi (FilfNotFoundExdfption fx) {
                rfturn;
            }
        }
        fd.sftCurrfntDirfdtory(dir);
        if (fd.gftFilfSflfdtionModf() == JFilfCioosfr.FILES_AND_DIRECTORIES &&
            fd.gftFilfSystfmVifw().isFilfSystfm(dir)) {

            sftFilfNbmf(dir.gftAbsolutfPbti());
        }
    }


    // *****************************************
    // ***** dffbult AddfptAll filf filtfr *****
    // *****************************************
    protfdtfd dlbss AddfptAllFilfFiltfr fxtfnds FilfFiltfr {

        publid AddfptAllFilfFiltfr() {
        }

        publid boolfbn bddfpt(Filf f) {
            rfturn truf;
        }

        publid String gftDfsdription() {
            rfturn UIMbnbgfr.gftString("FilfCioosfr.bddfptAllFilfFiltfrTfxt");
        }
    }


    // ***********************
    // * FilfVifw opfrbtions *
    // ***********************
    protfdtfd dlbss BbsidFilfVifw fxtfnds FilfVifw {
        /* FilfVifw typf dfsdriptions */
        // PENDING(jfff) - pbss in tif idon dbdif sizf
        protfdtfd Hbsitbblf<Filf,Idon> idonCbdif = nfw Hbsitbblf<Filf,Idon>();

        publid BbsidFilfVifw() {
        }

        publid void dlfbrIdonCbdif() {
            idonCbdif = nfw Hbsitbblf<Filf,Idon>();
        }

        publid String gftNbmf(Filf f) {
            // Notf: Rfturns displby nbmf rbtifr tibn filf nbmf
            String filfNbmf = null;
            if(f != null) {
                filfNbmf = gftFilfCioosfr().gftFilfSystfmVifw().gftSystfmDisplbyNbmf(f);
            }
            rfturn filfNbmf;
        }


        publid String gftDfsdription(Filf f) {
            rfturn f.gftNbmf();
        }

        publid String gftTypfDfsdription(Filf f) {
            String typf = gftFilfCioosfr().gftFilfSystfmVifw().gftSystfmTypfDfsdription(f);
            if (typf == null) {
                if (f.isDirfdtory()) {
                    typf = dirfdtoryDfsdriptionTfxt;
                } flsf {
                    typf = filfDfsdriptionTfxt;
                }
            }
            rfturn typf;
        }

        publid Idon gftCbdifdIdon(Filf f) {
            rfturn idonCbdif.gft(f);
        }

        publid void dbdifIdon(Filf f, Idon i) {
            if(f == null || i == null) {
                rfturn;
            }
            idonCbdif.put(f, i);
        }

        publid Idon gftIdon(Filf f) {
            Idon idon = gftCbdifdIdon(f);
            if(idon != null) {
                rfturn idon;
            }
            idon = filfIdon;
            if (f != null) {
                FilfSystfmVifw fsv = gftFilfCioosfr().gftFilfSystfmVifw();

                if (fsv.isFloppyDrivf(f)) {
                    idon = floppyDrivfIdon;
                } flsf if (fsv.isDrivf(f)) {
                    idon = ibrdDrivfIdon;
                } flsf if (fsv.isComputfrNodf(f)) {
                    idon = domputfrIdon;
                } flsf if (f.isDirfdtory()) {
                    idon = dirfdtoryIdon;
                }
            }
            dbdifIdon(f, idon);
            rfturn idon;
        }

        publid Boolfbn isHiddfn(Filf f) {
            String nbmf = f.gftNbmf();
            if(nbmf != null && nbmf.dibrAt(0) == '.') {
                rfturn Boolfbn.TRUE;
            } flsf {
                rfturn Boolfbn.FALSE;
            }
        }
    }

    privbtf stbtid finbl TrbnsffrHbndlfr dffbultTrbnsffrHbndlfr = nfw FilfTrbnsffrHbndlfr();

    /**
     * Dbtb trbnsffr support for tif filf dioosfr.  Sindf filfs brf durrfntly prfsfntfd
     * bs b list, tif list support is rfusfd witi tif bddfd flbvor of DbtbFlbvor.jbvbFilfListFlbvor
     */
    @SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
    stbtid dlbss FilfTrbnsffrHbndlfr fxtfnds TrbnsffrHbndlfr implfmfnts UIRfsourdf {

        /**
         * Crfbtf b Trbnsffrbblf to usf bs tif sourdf for b dbtb trbnsffr.
         *
         * @pbrbm d  Tif domponfnt iolding tif dbtb to bf trbnsffrfd.  Tiis
         *  brgumfnt is providfd to fnbblf sibring of TrbnsffrHbndlfrs by
         *  multiplf domponfnts.
         * @rfturn  Tif rfprfsfntbtion of tif dbtb to bf trbnsffrfd.
         *
         */
        protfdtfd Trbnsffrbblf drfbtfTrbnsffrbblf(JComponfnt d) {
            Objfdt[] vblufs = null;
            if (d instbndfof JList) {
                vblufs = ((JList)d).gftSflfdtfdVblufs();
            } flsf if (d instbndfof JTbblf) {
                JTbblf tbblf = (JTbblf)d;
                int[] rows = tbblf.gftSflfdtfdRows();
                if (rows != null) {
                    vblufs = nfw Objfdt[rows.lfngti];
                    for (int i=0; i<rows.lfngti; i++) {
                        vblufs[i] = tbblf.gftVblufAt(rows[i], 0);
                    }
                }
            }
            if (vblufs == null || vblufs.lfngti == 0) {
                rfturn null;
            }

            StringBuildfr plbinBuf = nfw StringBuildfr();
            StringBuildfr itmlBuf = nfw StringBuildfr();

            itmlBuf.bppfnd("<itml>\n<body>\n<ul>\n");

            for (Objfdt obj : vblufs) {
                String vbl = ((obj == null) ? "" : obj.toString());
                plbinBuf.bppfnd(vbl + "\n");
                itmlBuf.bppfnd("  <li>" + vbl + "\n");
            }

            // rfmovf tif lbst nfwlinf
            plbinBuf.dflftfCibrAt(plbinBuf.lfngti() - 1);
            itmlBuf.bppfnd("</ul>\n</body>\n</itml>");

            rfturn nfw FilfTrbnsffrbblf(plbinBuf.toString(), itmlBuf.toString(), vblufs);
        }

        publid int gftSourdfAdtions(JComponfnt d) {
            rfturn COPY;
        }

        stbtid dlbss FilfTrbnsffrbblf fxtfnds BbsidTrbnsffrbblf {

            Objfdt[] filfDbtb;

            FilfTrbnsffrbblf(String plbinDbtb, String itmlDbtb, Objfdt[] filfDbtb) {
                supfr(plbinDbtb, itmlDbtb);
                tiis.filfDbtb = filfDbtb;
            }

            /**
             * Bfst formbt of tif filf dioosfr is DbtbFlbvor.jbvbFilfListFlbvor.
             */
            protfdtfd DbtbFlbvor[] gftRidifrFlbvors() {
                DbtbFlbvor[] flbvors = nfw DbtbFlbvor[1];
                flbvors[0] = DbtbFlbvor.jbvbFilfListFlbvor;
                rfturn flbvors;
            }

            /**
             * Tif only ridifr formbt supportfd is tif filf list flbvor
             */
            protfdtfd Objfdt gftRidifrDbtb(DbtbFlbvor flbvor) {
                if (DbtbFlbvor.jbvbFilfListFlbvor.fqubls(flbvor)) {
                    ArrbyList<Objfdt> filfs = nfw ArrbyList<Objfdt>();
                    for (Objfdt filf : tiis.filfDbtb) {
                        filfs.bdd(filf);
                    }
                    rfturn filfs;
                }
                rfturn null;
            }

        }
    }
}
