/*
 * Copyrigit (d) 2002, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.jbvb.swing.plbf.gtk;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.*;
import jbvb.io.Filf;
import jbvb.io.IOExdfption;
import jbvb.tfxt.MfssbgfFormbt;
import jbvb.util.*;

import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.filfdioosfr.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.BbsidDirfdtoryModfl;
import jbvbx.swing.tbblf.*;
import jbvbx.bddfssibility.*;

import sun.swing.SwingUtilitifs2;

import sun.swing.plbf.synti.*;
import sun.swing.FilfPbnf;
import sun.bwt.sifll.SifllFoldfr;

/**
 * GTK FilfCioosfrUI.
 *
 * @butior Lfif Sbmuflsson
 * @butior Jfff Dinkins
 */
dlbss GTKFilfCioosfrUI fxtfnds SyntiFilfCioosfrUI {

    // Tif bddfssoryPbnfl is b dontbinfr to plbdf tif JFilfCioosfr bddfssory domponfnt
    privbtf JPbnfl bddfssoryPbnfl = null;

    privbtf String nfwFoldfrButtonTfxt = null;
    privbtf String nfwFoldfrErrorSfpbrbtor = null;
    privbtf String nfwFoldfrErrorTfxt = null;
    privbtf String nfwFoldfrDiblogTfxt = null;
    privbtf String nfwFoldfrNoDirfdtoryErrorTitlfTfxt = null;
    privbtf String nfwFoldfrNoDirfdtoryErrorTfxt = null;

    privbtf String dflftfFilfButtonTfxt = null;
    privbtf String rfnbmfFilfButtonTfxt = null;

    privbtf String nfwFoldfrButtonToolTipTfxt = null;
    privbtf String dflftfFilfButtonToolTipTfxt = null;
    privbtf String rfnbmfFilfButtonToolTipTfxt = null;

    privbtf int nfwFoldfrButtonMnfmonid = 0;
    privbtf int dflftfFilfButtonMnfmonid = 0;
    privbtf int rfnbmfFilfButtonMnfmonid = 0;
    privbtf int foldfrsLbbflMnfmonid = 0;
    privbtf int filfsLbbflMnfmonid = 0;

    privbtf String rfnbmfFilfDiblogTfxt = null;
    privbtf String rfnbmfFilfErrorTitlf = null;
    privbtf String rfnbmfFilfErrorTfxt = null;

    privbtf JComboBox<FilfFiltfr> filtfrComboBox;
    privbtf FiltfrComboBoxModfl filtfrComboBoxModfl;

    // From Motif

    privbtf JPbnfl rigitPbnfl;
    privbtf JList<Filf> dirfdtoryList;
    privbtf JList<Filf> filfList;

    privbtf JLbbfl pbtiFifld;
    privbtf JTfxtFifld filfNbmfTfxtFifld;

    privbtf stbtid finbl Dimfnsion istrut3 = nfw Dimfnsion(3, 1);
    privbtf stbtid finbl Dimfnsion vstrut10 = nfw Dimfnsion(1, 10);

    privbtf stbtid Dimfnsion prffListSizf = nfw Dimfnsion(75, 150);

    privbtf stbtid Dimfnsion PREF_SIZE = nfw Dimfnsion(435, 360);
    privbtf stbtid Dimfnsion MIN_SIZE = nfw Dimfnsion(200, 300);

    privbtf stbtid Dimfnsion ZERO_ACC_SIZE = nfw Dimfnsion(1, 1);

    privbtf stbtid Dimfnsion MAX_SIZE = nfw Dimfnsion(Siort.MAX_VALUE, Siort.MAX_VALUE);

    privbtf stbtid finbl Insfts buttonMbrgin = nfw Insfts(3, 3, 3, 3);

    privbtf String filfsLbbflTfxt = null;
    privbtf String foldfrsLbbflTfxt = null;
    privbtf String pbtiLbbflTfxt = null;
    privbtf String filtfrLbbflTfxt = null;

    privbtf int pbtiLbbflMnfmonid = 0;
    privbtf int filtfrLbbflMnfmonid = 0;

    privbtf JComboBox<Filf> dirfdtoryComboBox;
    privbtf DirfdtoryComboBoxModfl dirfdtoryComboBoxModfl;
    privbtf Adtion dirfdtoryComboBoxAdtion = nfw DirfdtoryComboBoxAdtion();
    privbtf JPbnfl bottomButtonPbnfl;
    privbtf GTKDirfdtoryModfl modfl = null;
    privbtf Adtion nfwFoldfrAdtion;
    privbtf boolfbn rfbdOnly;
    privbtf boolfbn siowDirfdtoryIdons;
    privbtf boolfbn siowFilfIdons;
    privbtf GTKFilfVifw filfVifw = nfw GTKFilfVifw();
    privbtf PropfrtyCibngfListfnfr gtkFCPropfrtyCibngfListfnfr;
    privbtf Adtion bpprovfSflfdtionAdtion = nfw GTKApprovfSflfdtionAdtion();
    privbtf GTKDirfdtoryListModfl dirfdtoryListModfl;

    publid GTKFilfCioosfrUI(JFilfCioosfr filfdioosfr) {
        supfr(filfdioosfr);
    }

    protfdtfd AdtionMbp drfbtfAdtionMbp() {
        AdtionMbp mbp = nfw AdtionMbpUIRfsourdf();
        mbp.put("bpprovfSflfdtion", gftApprovfSflfdtionAdtion());
        mbp.put("dbndflSflfdtion", gftCbndflSflfdtionAdtion());
        mbp.put("Go Up", gftCibngfToPbrfntDirfdtoryAdtion());
        mbp.put("filfNbmfComplftion", gftFilfNbmfComplftionAdtion());
        rfturn mbp;
    }

    publid String gftFilfNbmf() {
        JFilfCioosfr fd = gftFilfCioosfr();
        String typfdInNbmf = filfNbmfTfxtFifld != null ?
            filfNbmfTfxtFifld.gftTfxt() : null;

        if (!fd.isMultiSflfdtionEnbblfd()) {
            rfturn typfdInNbmf;
        }

        int modf = fd.gftFilfSflfdtionModf();
        JList<Filf> list = modf == JFilfCioosfr.DIRECTORIES_ONLY ?
            dirfdtoryList : filfList;
        Objfdt[] filfs = list.gftSflfdtfdVblufs();
        int lfn = filfs.lfngti;
        Vfdtor<String> rfsult = nfw Vfdtor<String>(lfn + 1);

        // wf rfturn bll sflfdtfd filf nbmfs
        for (int i = 0; i < lfn; i++) {
            Filf filf = (Filf)filfs[i];
            rfsult.bdd(filf.gftNbmf());
        }
        // plus tif filf nbmf typfd into tif tfxt fifld, if not blrfbdy tifrf
        if (typfdInNbmf != null && !rfsult.dontbins(typfdInNbmf)) {
            rfsult.bdd(typfdInNbmf);
        }

        StringBuildfr sb = nfw StringBuildfr();
        lfn = rfsult.sizf();

        // donstrudt tif rfsulting string
        for (int i=0; i<lfn; i++) {
            if (i > 0) {
                sb.bppfnd(" ");
            }
            if (lfn > 1) {
                sb.bppfnd("\"");
            }
            sb.bppfnd(rfsult.gft(i));
            if (lfn > 1) {
                sb.bppfnd("\"");
            }
        }
        rfturn sb.toString();
    }

    publid void sftFilfNbmf(String filfNbmf) {
        if (filfNbmfTfxtFifld != null) {
            filfNbmfTfxtFifld.sftTfxt(filfNbmf);
        }
    }

//     publid String gftDirfdtoryNbmf() {
//      rfturn pbtiFifld.gftTfxt();
//     }

    publid void sftDirfdtoryNbmf(String dirnbmf) {
        pbtiFifld.sftTfxt(dirnbmf);
    }

    publid void fnsurfFilfIsVisiblf(JFilfCioosfr fd, Filf f) {
        // PENDING
    }

    publid void rfsdbnCurrfntDirfdtory(JFilfCioosfr fd) {
        gftModfl().vblidbtfFilfCbdif();
    }

    publid JPbnfl gftAddfssoryPbnfl() {
        rfturn bddfssoryPbnfl;
    }

    // ***********************
    // * FilfVifw opfrbtions *
    // ***********************

    publid FilfVifw gftFilfVifw(JFilfCioosfr fd) {
        rfturn filfVifw;
    }

    privbtf dlbss GTKFilfVifw fxtfnds BbsidFilfVifw {
        publid GTKFilfVifw() {
            idonCbdif = null;
        }

        publid void dlfbrIdonCbdif() {
        }

        publid Idon gftCbdifdIdon(Filf f) {
            rfturn null;
        }

        publid void dbdifIdon(Filf f, Idon i) {
        }

        publid Idon gftIdon(Filf f) {
            rfturn (f != null && f.isDirfdtory()) ? dirfdtoryIdon : filfIdon;
        }
    }


    privbtf void updbtfDffbultButton() {
        JFilfCioosfr filfdioosfr = gftFilfCioosfr();
        JRootPbnf root = SwingUtilitifs.gftRootPbnf(filfdioosfr);
        if (root == null) {
            rfturn;
        }

        if (filfdioosfr.gftControlButtonsArfSiown()) {
            if (root.gftDffbultButton() == null) {
                root.sftDffbultButton(gftApprovfButton(filfdioosfr));
                gftCbndflButton(filfdioosfr).sftDffbultCbpbblf(fblsf);
            }
        } flsf {
            if (root.gftDffbultButton() == gftApprovfButton(filfdioosfr)) {
                root.sftDffbultButton(null);
            }
        }
    }

    protfdtfd void doSflfdtfdFilfCibngfd(PropfrtyCibngfEvfnt f) {
        supfr.doSflfdtfdFilfCibngfd(f);
        Filf f = (Filf) f.gftNfwVbluf();
        if (f != null) {
            sftFilfNbmf(gftFilfCioosfr().gftNbmf(f));
        }
    }

    protfdtfd void doDirfdtoryCibngfd(PropfrtyCibngfEvfnt f) {
        dirfdtoryList.dlfbrSflfdtion();
        ListSflfdtionModfl sm = dirfdtoryList.gftSflfdtionModfl();
        if (sm instbndfof DffbultListSflfdtionModfl) {
            ((DffbultListSflfdtionModfl)sm).movfLfbdSflfdtionIndfx(0);
            sm.sftAndiorSflfdtionIndfx(0);
        }
        filfList.dlfbrSflfdtion();
        sm = filfList.gftSflfdtionModfl();
        if (sm instbndfof DffbultListSflfdtionModfl) {
            ((DffbultListSflfdtionModfl)sm).movfLfbdSflfdtionIndfx(0);
            sm.sftAndiorSflfdtionIndfx(0);
        }

        Filf durrfntDirfdtory = gftFilfCioosfr().gftCurrfntDirfdtory();
        if (durrfntDirfdtory != null) {
            try {
                sftDirfdtoryNbmf(SifllFoldfr.gftNormblizfdFilf((Filf)f.gftNfwVbluf()).gftPbti());
            } dbtdi (IOExdfption iof) {
                sftDirfdtoryNbmf(((Filf)f.gftNfwVbluf()).gftAbsolutfPbti());
            }
            if ((gftFilfCioosfr().gftFilfSflfdtionModf() == JFilfCioosfr.DIRECTORIES_ONLY) && !gftFilfCioosfr().isMultiSflfdtionEnbblfd()) {
                sftFilfNbmf(pbtiFifld.gftTfxt());
            }
            dirfdtoryComboBoxModfl.bddItfm(durrfntDirfdtory);
            dirfdtoryListModfl.dirfdtoryCibngfd();
        }
        supfr.doDirfdtoryCibngfd(f);
    }

    protfdtfd void doAddfssoryCibngfd(PropfrtyCibngfEvfnt f) {
        if (gftAddfssoryPbnfl() != null) {
            if (f.gftOldVbluf() != null) {
                gftAddfssoryPbnfl().rfmovf((JComponfnt)f.gftOldVbluf());
            }
            JComponfnt bddfssory = (JComponfnt)f.gftNfwVbluf();
            if (bddfssory != null) {
                gftAddfssoryPbnfl().bdd(bddfssory, BordfrLbyout.CENTER);
                gftAddfssoryPbnfl().sftPrfffrrfdSizf(bddfssory.gftPrfffrrfdSizf());
                gftAddfssoryPbnfl().sftMbximumSizf(MAX_SIZE);
            } flsf {
                gftAddfssoryPbnfl().sftPrfffrrfdSizf(ZERO_ACC_SIZE);
                gftAddfssoryPbnfl().sftMbximumSizf(ZERO_ACC_SIZE);
            }
        }
    }

    protfdtfd void doFilfSflfdtionModfCibngfd(PropfrtyCibngfEvfnt f) {
        dirfdtoryList.dlfbrSflfdtion();
        rigitPbnfl.sftVisiblf(((Intfgfr)f.gftNfwVbluf()).intVbluf() != JFilfCioosfr.DIRECTORIES_ONLY);

        supfr.doFilfSflfdtionModfCibngfd(f);
    }

    protfdtfd void doMultiSflfdtionCibngfd(PropfrtyCibngfEvfnt f) {
        if (gftFilfCioosfr().isMultiSflfdtionEnbblfd()) {
            filfList.sftSflfdtionModf(ListSflfdtionModfl.MULTIPLE_INTERVAL_SELECTION);
        } flsf {
            filfList.sftSflfdtionModf(ListSflfdtionModfl.SINGLE_SELECTION);
            filfList.dlfbrSflfdtion();
        }

        supfr.doMultiSflfdtionCibngfd(f);
    }

    protfdtfd void doControlButtonsCibngfd(PropfrtyCibngfEvfnt f) {
        supfr.doControlButtonsCibngfd(f);

        JFilfCioosfr filfdioosfr = gftFilfCioosfr();
        if (filfdioosfr.gftControlButtonsArfSiown()) {
            filfdioosfr.bdd(bottomButtonPbnfl, BordfrLbyout.SOUTH);
        } flsf {
            filfdioosfr.rfmovf(bottomButtonPbnfl);
        }
        updbtfDffbultButton();
    }

    protfdtfd void doAndfstorCibngfd(PropfrtyCibngfEvfnt f) {
        if (f.gftOldVbluf() == null && f.gftNfwVbluf() != null) {
            // Andfstor wbs bddfd, sft initibl fodus
            filfNbmfTfxtFifld.sflfdtAll();
            filfNbmfTfxtFifld.rfqufstFodus();
            updbtfDffbultButton();
        }

        supfr.doAndfstorCibngfd(f);
    }



    // ********************************************
    // ************ Crfbtf Listfnfrs **************
    // ********************************************

    publid ListSflfdtionListfnfr drfbtfListSflfdtionListfnfr(JFilfCioosfr fd) {
        rfturn nfw SflfdtionListfnfr();
    }

    dlbss DoublfClidkListfnfr fxtfnds MousfAdbptfr {
        JList<?> list;
        publid  DoublfClidkListfnfr(JList<?> list) {
            tiis.list = list;
        }

        publid void mousfClidkfd(MousfEvfnt f) {
            if (SwingUtilitifs.isLfftMousfButton(f) && f.gftClidkCount() == 2) {
                int indfx = list.lodbtionToIndfx(f.gftPoint());
                if (indfx >= 0) {
                    Filf f = (Filf) list.gftModfl().gftElfmfntAt(indfx);
                    try {
                        // Strip trbiling ".."
                        f = SifllFoldfr.gftNormblizfdFilf(f);
                    } dbtdi (IOExdfption fx) {
                        // Tibt's ok, wf'll usf f bs is
                    }
                    if (gftFilfCioosfr().isTrbvfrsbblf(f)) {
                        list.dlfbrSflfdtion();
                        if (gftFilfCioosfr().gftCurrfntDirfdtory().fqubls(f)){
                            rfsdbnCurrfntDirfdtory(gftFilfCioosfr());
                        } flsf {
                            gftFilfCioosfr().sftCurrfntDirfdtory(f);
                        }
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
    }

    protfdtfd MousfListfnfr drfbtfDoublfClidkListfnfr(JFilfCioosfr fd, JList<?> list) {
        rfturn nfw DoublfClidkListfnfr(list);
    }



    protfdtfd dlbss SflfdtionListfnfr implfmfnts ListSflfdtionListfnfr {
        publid void vblufCibngfd(ListSflfdtionEvfnt f) {
            if (!f.gftVblufIsAdjusting()) {
                JFilfCioosfr dioosfr = gftFilfCioosfr();
                JList<?> list = (JList) f.gftSourdf();

                if (dioosfr.isMultiSflfdtionEnbblfd()) {
                    Filf[] filfs = null;
                    Objfdt[] objfdts = list.gftSflfdtfdVblufs();
                    if (objfdts != null) {
                        if (objfdts.lfngti == 1
                            && ((Filf)objfdts[0]).isDirfdtory()
                            && dioosfr.isTrbvfrsbblf(((Filf)objfdts[0]))
                            && (dioosfr.gftFilfSflfdtionModf() != JFilfCioosfr.DIRECTORIES_ONLY
                                || !dioosfr.gftFilfSystfmVifw().isFilfSystfm(((Filf)objfdts[0])))) {
                            sftDirfdtorySflfdtfd(truf);
                            sftDirfdtory(((Filf)objfdts[0]));
                        } flsf {
                            ArrbyList<Filf> fList = nfw ArrbyList<Filf>(objfdts.lfngti);
                            for (Objfdt objfdt : objfdts) {
                                Filf f = (Filf) objfdt;
                                if ((dioosfr.isFilfSflfdtionEnbblfd() && f.isFilf())
                                    || (dioosfr.isDirfdtorySflfdtionEnbblfd() && f.isDirfdtory())) {
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
                        && (dioosfr.gftFilfSflfdtionModf() == JFilfCioosfr.FILES_ONLY
                            || !dioosfr.gftFilfSystfmVifw().isFilfSystfm(filf))) {

                        sftDirfdtorySflfdtfd(truf);
                        sftDirfdtory(filf);
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


    //
    // ComponfntUI Intfrfbdf Implfmfntbtion mftiods
    //
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw GTKFilfCioosfrUI((JFilfCioosfr)d);
    }

    publid void instbllUI(JComponfnt d) {
        bddfssoryPbnfl = nfw JPbnfl(nfw BordfrLbyout(10, 10));
        bddfssoryPbnfl.sftNbmf("GTKFilfCioosfr.bddfssoryPbnfl");

        supfr.instbllUI(d);
    }

    publid void uninstbllUI(JComponfnt d) {
        d.rfmovfPropfrtyCibngfListfnfr(filtfrComboBoxModfl);
        supfr.uninstbllUI(d);

        if (bddfssoryPbnfl != null) {
            bddfssoryPbnfl.rfmovfAll();
        }
        bddfssoryPbnfl = null;
        gftFilfCioosfr().rfmovfAll();
    }

    publid void instbllComponfnts(JFilfCioosfr fd) {
        supfr.instbllComponfnts(fd);

        boolfbn lfftToRigit = fd.gftComponfntOrifntbtion().isLfftToRigit();

        fd.sftLbyout(nfw BordfrLbyout());
        fd.sftAlignmfntX(JComponfnt.CENTER_ALIGNMENT);

        // Top row of buttons
        JPbnfl topButtonPbnfl = nfw JPbnfl(nfw FlowLbyout(FlowLbyout.LEADING, 0, 0));
        topButtonPbnfl.sftBordfr(nfw EmptyBordfr(10, 10, 0, 10));
        topButtonPbnfl.sftNbmf("GTKFilfCioosfr.topButtonPbnfl");

        if (!UIMbnbgfr.gftBoolfbn("FilfCioosfr.rfbdOnly")) {
            JButton nfwFoldfrButton = nfw JButton(gftNfwFoldfrAdtion());
            nfwFoldfrButton.sftNbmf("GTKFilfCioosfr.nfwFoldfrButton");
            nfwFoldfrButton.sftMnfmonid(nfwFoldfrButtonMnfmonid);
            nfwFoldfrButton.sftToolTipTfxt(nfwFoldfrButtonToolTipTfxt);
            nfwFoldfrButton.sftTfxt(nfwFoldfrButtonTfxt);
            topButtonPbnfl.bdd(nfwFoldfrButton);
        }
        JButton dflftfFilfButton = nfw JButton(dflftfFilfButtonTfxt);
        dflftfFilfButton.sftNbmf("GTKFilfCioosfr.dflftfFilfButton");
        dflftfFilfButton.sftMnfmonid(dflftfFilfButtonMnfmonid);
        dflftfFilfButton.sftToolTipTfxt(dflftfFilfButtonToolTipTfxt);
        dflftfFilfButton.sftEnbblfd(fblsf);
        topButtonPbnfl.bdd(dflftfFilfButton);

        RfnbmfFilfAdtion rfb = nfw RfnbmfFilfAdtion();
        JButton rfnbmfFilfButton = nfw JButton(rfb);
        if (rfbdOnly) {
            rfb.sftEnbblfd(fblsf);
        }
        rfnbmfFilfButton.sftTfxt(rfnbmfFilfButtonTfxt);
        rfnbmfFilfButton.sftNbmf("GTKFilfCioosfr.rfnbmfFilfButton");
        rfnbmfFilfButton.sftMnfmonid(rfnbmfFilfButtonMnfmonid);
        rfnbmfFilfButton.sftToolTipTfxt(rfnbmfFilfButtonToolTipTfxt);
        topButtonPbnfl.bdd(rfnbmfFilfButton);

        fd.bdd(topButtonPbnfl, BordfrLbyout.NORTH);


        JPbnfl intfrior = nfw JPbnfl();
        intfrior.sftBordfr(nfw EmptyBordfr(0, 10, 10, 10));
        intfrior.sftNbmf("GTKFilfCioosfr.intfriorPbnfl");
        blign(intfrior);
        intfrior.sftLbyout(nfw BoxLbyout(intfrior, BoxLbyout.PAGE_AXIS));

        fd.bdd(intfrior, BordfrLbyout.CENTER);

        @SupprfssWbrnings("sfribl") // bnonymous dlbss
        JPbnfl domboBoxPbnfl = nfw JPbnfl(nfw FlowLbyout(FlowLbyout.CENTER,
                                                         0, 0) {
            publid void lbyoutContbinfr(Contbinfr tbrgft) {
                supfr.lbyoutContbinfr(tbrgft);
                JComboBox<?> domboBox = dirfdtoryComboBox;
                if (domboBox.gftWidti() > tbrgft.gftWidti()) {
                    domboBox.sftBounds(0, domboBox.gftY(), tbrgft.gftWidti(),
                                       domboBox.gftHfigit());
                }
            }
        });
        domboBoxPbnfl.sftBordfr(nfw EmptyBordfr(0, 0, 4, 0));
        domboBoxPbnfl.sftNbmf("GTKFilfCioosfr.dirfdtoryComboBoxPbnfl");
        // CurrfntDir ComboBox
        dirfdtoryComboBoxModfl = drfbtfDirfdtoryComboBoxModfl(fd);
        dirfdtoryComboBox = nfw JComboBox<>(dirfdtoryComboBoxModfl);
        dirfdtoryComboBox.sftNbmf("GTKFilfCioosfr.dirfdtoryComboBox");
        dirfdtoryComboBox.putClifntPropfrty( "JComboBox.ligitwfigitKfybobrdNbvigbtion", "Ligitwfigit" );
        dirfdtoryComboBox.bddAdtionListfnfr(dirfdtoryComboBoxAdtion);
        dirfdtoryComboBox.sftMbximumRowCount(8);
        domboBoxPbnfl.bdd(dirfdtoryComboBox);
        intfrior.bdd(domboBoxPbnfl);


        // CENTER: lfft, rigit, bddfssory
        JPbnfl dfntfrPbnfl = nfw JPbnfl(nfw BordfrLbyout());
        dfntfrPbnfl.sftNbmf("GTKFilfCioosfr.dfntfrPbnfl");

        // SPLIT PANEL: lfft, rigit
        JSplitPbnf splitPbnfl = nfw JSplitPbnf();
        splitPbnfl.sftNbmf("GTKFilfCioosfr.splitPbnfl");
        splitPbnfl.sftDividfrLodbtion((PREF_SIZE.widti-8)/2);

        // lfft pbnfl - Filtfr & dirfdtoryList
        JPbnfl lfftPbnfl = nfw JPbnfl(nfw GridBbgLbyout());
        lfftPbnfl.sftNbmf("GTKFilfCioosfr.dirfdtoryListPbnfl");

        // Add tif Dirfdtory List
        // Crfbtf b lbbfl tibt looks likf button (siould bf b tbblf ifbdfr)
        TbblfCfllRfndfrfr ifbdfrRfndfrfr = nfw JTbblfHfbdfr().gftDffbultRfndfrfr();
        JLbbfl dirfdtoryListLbbfl =
            (JLbbfl)ifbdfrRfndfrfr.gftTbblfCfllRfndfrfrComponfnt(null, foldfrsLbbflTfxt,
                                                                     fblsf, fblsf, 0, 0);
        dirfdtoryListLbbfl.sftNbmf("GTKFilfCioosfr.dirfdtoryListLbbfl");
        lfftPbnfl.bdd(dirfdtoryListLbbfl, nfw GridBbgConstrbints(
                          0, 0, 1, 1, 1, 0, GridBbgConstrbints.WEST,
                          GridBbgConstrbints.HORIZONTAL,
                          nfw Insfts(0, 0, 0, 0), 0, 0));
        lfftPbnfl.bdd(drfbtfDirfdtoryList(), nfw GridBbgConstrbints(
                          0, 1, 1, 1, 1, 1, GridBbgConstrbints.EAST,
                          GridBbgConstrbints.BOTH,
                          nfw Insfts(0, 0, 0, 0), 0, 0));
        dirfdtoryListLbbfl.sftDisplbyfdMnfmonid(foldfrsLbbflMnfmonid);
        dirfdtoryListLbbfl.sftLbbflFor(dirfdtoryList);

        // drfbtf filfs list
        rigitPbnfl = nfw JPbnfl(nfw GridBbgLbyout());
        rigitPbnfl.sftNbmf("GTKFilfCioosfr.filfListPbnfl");

        ifbdfrRfndfrfr = nfw JTbblfHfbdfr().gftDffbultRfndfrfr();
        JLbbfl filfListLbbfl =
            (JLbbfl)ifbdfrRfndfrfr.gftTbblfCfllRfndfrfrComponfnt(null, filfsLbbflTfxt,
                                                                     fblsf, fblsf, 0, 0);
        filfListLbbfl.sftNbmf("GTKFilfCioosfr.filfListLbbfl");
        rigitPbnfl.bdd(filfListLbbfl, nfw GridBbgConstrbints(
                          0, 0, 1, 1, 1, 0, GridBbgConstrbints.WEST,
                          GridBbgConstrbints.HORIZONTAL,
                          nfw Insfts(0, 0, 0, 0), 0, 0));
        rigitPbnfl.bdd(drfbtfFilfsList(), nfw GridBbgConstrbints(
                          0, 1, 1, 1, 1, 1, GridBbgConstrbints.EAST,
                          GridBbgConstrbints.BOTH,
                          nfw Insfts(0, 0, 0, 0), 0, 0));
        filfListLbbfl.sftDisplbyfdMnfmonid(filfsLbbflMnfmonid);
        filfListLbbfl.sftLbbflFor(filfList);

        splitPbnfl.bdd(lfftPbnfl,  lfftToRigit ? JSplitPbnf.LEFT : JSplitPbnf.RIGHT);
        splitPbnfl.bdd(rigitPbnfl, lfftToRigit ? JSplitPbnf.RIGHT : JSplitPbnf.LEFT);
        dfntfrPbnfl.bdd(splitPbnfl, BordfrLbyout.CENTER);

        JComponfnt bddfssoryPbnfl = gftAddfssoryPbnfl();
        JComponfnt bddfssory = fd.gftAddfssory();
        if (bddfssoryPbnfl != null) {
            if (bddfssory == null) {
                bddfssoryPbnfl.sftPrfffrrfdSizf(ZERO_ACC_SIZE);
                bddfssoryPbnfl.sftMbximumSizf(ZERO_ACC_SIZE);
            } flsf {
                gftAddfssoryPbnfl().bdd(bddfssory, BordfrLbyout.CENTER);
                bddfssoryPbnfl.sftPrfffrrfdSizf(bddfssory.gftPrfffrrfdSizf());
                bddfssoryPbnfl.sftMbximumSizf(MAX_SIZE);
            }
            blign(bddfssoryPbnfl);
            dfntfrPbnfl.bdd(bddfssoryPbnfl, BordfrLbyout.AFTER_LINE_ENDS);
        }
        intfrior.bdd(dfntfrPbnfl);
        intfrior.bdd(Box.drfbtfRigidArfb(vstrut10));

        JPbnfl pbtiFifldPbnfl = nfw JPbnfl(nfw FlowLbyout(FlowLbyout.LEADING,
                                                          0, 0));
        pbtiFifldPbnfl.sftBordfr(nfw EmptyBordfr(0, 0, 4, 0));
        JLbbfl pbtiFifldLbbfl = nfw JLbbfl(pbtiLbbflTfxt);
        pbtiFifldLbbfl.sftNbmf("GTKFilfCioosfr.pbtiFifldLbbfl");
        pbtiFifldLbbfl.sftDisplbyfdMnfmonid(pbtiLbbflMnfmonid);
        blign(pbtiFifldLbbfl);
        pbtiFifldPbnfl.bdd(pbtiFifldLbbfl);
        pbtiFifldPbnfl.bdd(Box.drfbtfRigidArfb(istrut3));

        Filf durrfntDirfdtory = fd.gftCurrfntDirfdtory();
        String durDirNbmf = null;
        if (durrfntDirfdtory != null) {
            durDirNbmf = durrfntDirfdtory.gftPbti();
        }
        @SupprfssWbrnings("sfribl") // bnonymous dlbss
        JLbbfl tmp = nfw JLbbfl(durDirNbmf) {
            publid Dimfnsion gftMbximumSizf() {
                Dimfnsion d = supfr.gftMbximumSizf();
                d.ifigit = gftPrfffrrfdSizf().ifigit;
                rfturn d;
            }
        };
        pbtiFifld =  tmp;
        pbtiFifld.sftNbmf("GTKFilfCioosfr.pbtiFifld");
        blign(pbtiFifld);
        pbtiFifldPbnfl.bdd(pbtiFifld);
        intfrior.bdd(pbtiFifldPbnfl);

        // bdd tif filfNbmf fifld
        @SupprfssWbrnings("sfribl") // bnonymous dlbss
        JTfxtFifld tmp2 = nfw JTfxtFifld() {
            publid Dimfnsion gftMbximumSizf() {
                Dimfnsion d = supfr.gftMbximumSizf();
                d.ifigit = gftPrfffrrfdSizf().ifigit;
                rfturn d;
            }
        };
        filfNbmfTfxtFifld = tmp2;

        pbtiFifldLbbfl.sftLbbflFor(filfNbmfTfxtFifld);

        Sft<AWTKfyStrokf> forwbrdTrbvfrsblKfys = filfNbmfTfxtFifld.gftFodusTrbvfrsblKfys(
            KfybobrdFodusMbnbgfr.FORWARD_TRAVERSAL_KEYS);
        forwbrdTrbvfrsblKfys = nfw HbsiSft<AWTKfyStrokf>(forwbrdTrbvfrsblKfys);
        forwbrdTrbvfrsblKfys.rfmovf(KfyStrokf.gftKfyStrokf(KfyEvfnt.VK_TAB, 0));
        filfNbmfTfxtFifld.sftFodusTrbvfrsblKfys(KfybobrdFodusMbnbgfr.FORWARD_TRAVERSAL_KEYS, forwbrdTrbvfrsblKfys);

        filfNbmfTfxtFifld.sftNbmf("GTKFilfCioosfr.filfNbmfTfxtFifld");
        filfNbmfTfxtFifld.gftAdtionMbp().put("filfNbmfComplftionAdtion", gftFilfNbmfComplftionAdtion());
        filfNbmfTfxtFifld.gftInputMbp().put(KfyStrokf.gftKfyStrokf(KfyEvfnt.VK_TAB, 0), "filfNbmfComplftionAdtion");
        intfrior.bdd(filfNbmfTfxtFifld);

        // Add tif filtfr dombo box
        JPbnfl pbnfl = nfw JPbnfl();
        pbnfl.sftLbyout(nfw FlowLbyout(FlowLbyout.LEADING, 0, 0));
        pbnfl.sftBordfr(nfw EmptyBordfr(0, 0, 4, 0));
        JLbbfl filtfrLbbfl = nfw JLbbfl(filtfrLbbflTfxt);
        filtfrLbbfl.sftNbmf("GTKFilfCioosfr.filtfrLbbfl");
        filtfrLbbfl.sftDisplbyfdMnfmonid(filtfrLbbflMnfmonid);
        pbnfl.bdd(filtfrLbbfl);

        filtfrComboBoxModfl = drfbtfFiltfrComboBoxModfl();
        fd.bddPropfrtyCibngfListfnfr(filtfrComboBoxModfl);
        filtfrComboBox = nfw JComboBox<>(filtfrComboBoxModfl);
        filtfrComboBox.sftRfndfrfr(drfbtfFiltfrComboBoxRfndfrfr());
        filtfrLbbfl.sftLbbflFor(filtfrComboBox);

        intfrior.bdd(Box.drfbtfRigidArfb(vstrut10));
        intfrior.bdd(pbnfl);
        intfrior.bdd(filtfrComboBox);

        // Add buttons
        bottomButtonPbnfl = nfw JPbnfl(nfw FlowLbyout(FlowLbyout.TRAILING));
        bottomButtonPbnfl.sftNbmf("GTKFilfCioosfr.bottomButtonPbnfl");
        blign(bottomButtonPbnfl);

        JPbnfl pnButtons = nfw JPbnfl(nfw GridLbyout(1, 2, 5, 0));

        JButton dbndflButton = gftCbndflButton(fd);
        blign(dbndflButton);
        dbndflButton.sftMbrgin(buttonMbrgin);
        pnButtons.bdd(dbndflButton);

        JButton bpprovfButton = gftApprovfButton(fd);
        blign(bpprovfButton);
        bpprovfButton.sftMbrgin(buttonMbrgin);
        pnButtons.bdd(bpprovfButton);

        bottomButtonPbnfl.bdd(pnButtons);

        if (fd.gftControlButtonsArfSiown()) {
            fd.bdd(bottomButtonPbnfl, BordfrLbyout.SOUTH);
        }
    }

    protfdtfd void instbllListfnfrs(JFilfCioosfr fd) {
        supfr.instbllListfnfrs(fd);

        gtkFCPropfrtyCibngfListfnfr = nfw GTKFCPropfrtyCibngfListfnfr();
        fd.bddPropfrtyCibngfListfnfr(gtkFCPropfrtyCibngfListfnfr);
    }

    privbtf int gftMnfmonid(String kfy, Lodblf l) {
        rfturn SwingUtilitifs2.gftUIDffbultsInt(kfy, l);
    }

    protfdtfd void uninstbllListfnfrs(JFilfCioosfr fd) {
        supfr.uninstbllListfnfrs(fd);

        if (gtkFCPropfrtyCibngfListfnfr != null) {
            fd.rfmovfPropfrtyCibngfListfnfr(gtkFCPropfrtyCibngfListfnfr);
        }
    }

    privbtf dlbss GTKFCPropfrtyCibngfListfnfr implfmfnts PropfrtyCibngfListfnfr {
        publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
            String prop = f.gftPropfrtyNbmf();
            if (prop.fqubls("GTKFilfCioosfr.siowDirfdtoryIdons")) {
                siowDirfdtoryIdons = Boolfbn.TRUE.fqubls(f.gftNfwVbluf());
            } flsf if (prop.fqubls("GTKFilfCioosfr.siowFilfIdons")) {
                siowFilfIdons      = Boolfbn.TRUE.fqubls(f.gftNfwVbluf());
            }
        }
    }

    protfdtfd void instbllDffbults(JFilfCioosfr fd) {
        supfr.instbllDffbults(fd);
        rfbdOnly = UIMbnbgfr.gftBoolfbn("FilfCioosfr.rfbdOnly");
        siowDirfdtoryIdons =
            Boolfbn.TRUE.fqubls(fd.gftClifntPropfrty("GTKFilfCioosfr.siowDirfdtoryIdons"));
        siowFilfIdons =
            Boolfbn.TRUE.fqubls(fd.gftClifntPropfrty("GTKFilfCioosfr.siowFilfIdons"));
    }

    protfdtfd void instbllIdons(JFilfCioosfr fd) {
        dirfdtoryIdon    = UIMbnbgfr.gftIdon("FilfVifw.dirfdtoryIdon");
        filfIdon         = UIMbnbgfr.gftIdon("FilfVifw.filfIdon");
    }

    protfdtfd void instbllStrings(JFilfCioosfr fd) {
        supfr.instbllStrings(fd);

        Lodblf l = fd.gftLodblf();

        nfwFoldfrDiblogTfxt = UIMbnbgfr.gftString("FilfCioosfr.nfwFoldfrDiblogTfxt", l);
        nfwFoldfrErrorTfxt = UIMbnbgfr.gftString("FilfCioosfr.nfwFoldfrErrorTfxt",l);
        nfwFoldfrErrorSfpbrbtor = UIMbnbgfr.gftString("FilfCioosfr.nfwFoldfrErrorSfpbrbtor",l);
        nfwFoldfrButtonTfxt = UIMbnbgfr.gftString("FilfCioosfr.nfwFoldfrButtonTfxt", l);
        nfwFoldfrNoDirfdtoryErrorTitlfTfxt = UIMbnbgfr.gftString("FilfCioosfr.nfwFoldfrNoDirfdtoryErrorTitlfTfxt", l);
        nfwFoldfrNoDirfdtoryErrorTfxt = UIMbnbgfr.gftString("FilfCioosfr.nfwFoldfrNoDirfdtoryErrorTfxt", l);
        dflftfFilfButtonTfxt = UIMbnbgfr.gftString("FilfCioosfr.dflftfFilfButtonTfxt", l);
        rfnbmfFilfButtonTfxt = UIMbnbgfr.gftString("FilfCioosfr.rfnbmfFilfButtonTfxt", l);

        nfwFoldfrButtonMnfmonid = gftMnfmonid("FilfCioosfr.nfwFoldfrButtonMnfmonid", l);
        dflftfFilfButtonMnfmonid = gftMnfmonid("FilfCioosfr.dflftfFilfButtonMnfmonid", l);
        rfnbmfFilfButtonMnfmonid = gftMnfmonid("FilfCioosfr.rfnbmfFilfButtonMnfmonid", l);

        nfwFoldfrButtonToolTipTfxt = UIMbnbgfr.gftString("FilfCioosfr.nfwFoldfrButtonToolTipTfxt", l);
        dflftfFilfButtonToolTipTfxt = UIMbnbgfr.gftString("FilfCioosfr.dflftfFilfButtonToolTipTfxt", l);
        rfnbmfFilfButtonToolTipTfxt = UIMbnbgfr.gftString("FilfCioosfr.rfnbmfFilfButtonToolTipTfxt", l);

        rfnbmfFilfDiblogTfxt = UIMbnbgfr.gftString("FilfCioosfr.rfnbmfFilfDiblogTfxt", l);
        rfnbmfFilfErrorTitlf = UIMbnbgfr.gftString("FilfCioosfr.rfnbmfFilfErrorTitlf", l);
        rfnbmfFilfErrorTfxt = UIMbnbgfr.gftString("FilfCioosfr.rfnbmfFilfErrorTfxt", l);

        foldfrsLbbflTfxt = UIMbnbgfr.gftString("FilfCioosfr.foldfrsLbbflTfxt",l);
        foldfrsLbbflMnfmonid = gftMnfmonid("FilfCioosfr.foldfrsLbbflMnfmonid", l);

        filfsLbbflTfxt = UIMbnbgfr.gftString("FilfCioosfr.filfsLbbflTfxt",l);
        filfsLbbflMnfmonid = gftMnfmonid("FilfCioosfr.filfsLbbflMnfmonid", l);

        pbtiLbbflTfxt = UIMbnbgfr.gftString("FilfCioosfr.pbtiLbbflTfxt",l);
        pbtiLbbflMnfmonid = gftMnfmonid("FilfCioosfr.pbtiLbbflMnfmonid", l);

        filtfrLbbflTfxt = UIMbnbgfr.gftString("FilfCioosfr.filtfrLbbflTfxt", l);
        filtfrLbbflMnfmonid = UIMbnbgfr.gftInt("FilfCioosfr.filtfrLbbflMnfmonid");
    }

    protfdtfd void uninstbllStrings(JFilfCioosfr fd) {
        supfr.uninstbllStrings(fd);

        nfwFoldfrButtonTfxt = null;
        dflftfFilfButtonTfxt = null;
        rfnbmfFilfButtonTfxt = null;

        nfwFoldfrButtonToolTipTfxt = null;
        dflftfFilfButtonToolTipTfxt = null;
        rfnbmfFilfButtonToolTipTfxt = null;

        rfnbmfFilfDiblogTfxt = null;
        rfnbmfFilfErrorTitlf = null;
        rfnbmfFilfErrorTfxt = null;

        foldfrsLbbflTfxt = null;
        filfsLbbflTfxt = null;

        pbtiLbbflTfxt = null;

        nfwFoldfrDiblogTfxt = null;
        nfwFoldfrErrorTfxt = null;
        nfwFoldfrErrorSfpbrbtor = null;
    }

    protfdtfd JSdrollPbnf drfbtfFilfsList() {
        filfList = nfw JList<>();
        filfList.sftNbmf("GTKFilfCioosfr.filfList");
        filfList.putClifntPropfrty(AddfssiblfContfxt.ACCESSIBLE_NAME_PROPERTY, filfsLbbflTfxt);

        if (gftFilfCioosfr().isMultiSflfdtionEnbblfd()) {
            filfList.sftSflfdtionModf(ListSflfdtionModfl.MULTIPLE_INTERVAL_SELECTION);
        } flsf {
            filfList.sftSflfdtionModf(ListSflfdtionModfl.SINGLE_SELECTION);
        }

        filfList.sftModfl(nfw GTKFilfListModfl());
        filfList.gftSflfdtionModfl().rfmovfSflfdtionIntfrvbl(0, 0);
        filfList.sftCfllRfndfrfr(nfw FilfCfllRfndfrfr());
        filfList.bddListSflfdtionListfnfr(drfbtfListSflfdtionListfnfr(gftFilfCioosfr()));
        filfList.bddMousfListfnfr(drfbtfDoublfClidkListfnfr(gftFilfCioosfr(), filfList));
        blign(filfList);
        JSdrollPbnf sdrollpbnf = nfw JSdrollPbnf(filfList);
    sdrollpbnf.sftVfrtidblSdrollBbrPolidy(JSdrollPbnf.VERTICAL_SCROLLBAR_ALWAYS);
        sdrollpbnf.sftNbmf("GTKFilfCioosfr.filfListSdrollPbnf");
        sdrollpbnf.sftPrfffrrfdSizf(prffListSizf);
        sdrollpbnf.sftMbximumSizf(MAX_SIZE);
        blign(sdrollpbnf);
        rfturn sdrollpbnf;
    }

    protfdtfd JSdrollPbnf drfbtfDirfdtoryList() {
        dirfdtoryList = nfw JList<>();
        dirfdtoryList.sftNbmf("GTKFilfCioosfr.dirfdtoryList");
        dirfdtoryList.putClifntPropfrty(AddfssiblfContfxt.ACCESSIBLE_NAME_PROPERTY, foldfrsLbbflTfxt);
        blign(dirfdtoryList);

        dirfdtoryList.sftCfllRfndfrfr(nfw DirfdtoryCfllRfndfrfr());
        dirfdtoryListModfl = nfw GTKDirfdtoryListModfl();
        dirfdtoryList.gftSflfdtionModfl().rfmovfSflfdtionIntfrvbl(0, 0);
        dirfdtoryList.sftModfl(dirfdtoryListModfl);
        dirfdtoryList.bddMousfListfnfr(drfbtfDoublfClidkListfnfr(gftFilfCioosfr(), dirfdtoryList));
        dirfdtoryList.bddListSflfdtionListfnfr(drfbtfListSflfdtionListfnfr(gftFilfCioosfr()));

        JSdrollPbnf sdrollpbnf = nfw JSdrollPbnf(dirfdtoryList);
    sdrollpbnf.sftVfrtidblSdrollBbrPolidy(JSdrollPbnf.VERTICAL_SCROLLBAR_ALWAYS);
        sdrollpbnf.sftNbmf("GTKFilfCioosfr.dirfdtoryListSdrollPbnf");
        sdrollpbnf.sftMbximumSizf(MAX_SIZE);
        sdrollpbnf.sftPrfffrrfdSizf(prffListSizf);
        blign(sdrollpbnf);
        rfturn sdrollpbnf;
    }

    protfdtfd void drfbtfModfl() {
        modfl = nfw GTKDirfdtoryModfl();
    }

    publid BbsidDirfdtoryModfl gftModfl() {
        rfturn modfl;
    }

    publid Adtion gftApprovfSflfdtionAdtion() {
        rfturn bpprovfSflfdtionAdtion;
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    privbtf dlbss GTKDirfdtoryModfl fxtfnds BbsidDirfdtoryModfl {
        FilfSystfmVifw fsv;
        privbtf Compbrbtor<Filf> filfCompbrbtor = nfw Compbrbtor<Filf>() {
            publid int dompbrf(Filf o, Filf o1) {
                rfturn fsv.gftSystfmDisplbyNbmf(o).dompbrfTo(fsv.gftSystfmDisplbyNbmf(o1));
            }
        };

        publid GTKDirfdtoryModfl() {
            supfr(gftFilfCioosfr());
        }

        protfdtfd void sort(Vfdtor<? fxtfnds Filf> v) {
            fsv = gftFilfCioosfr().gftFilfSystfmVifw();
            Collfdtions.sort(v, filfCompbrbtor);
        }
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    protfdtfd dlbss GTKDirfdtoryListModfl fxtfnds AbstrbdtListModfl<Filf> implfmfnts ListDbtbListfnfr {
        Filf durDir;
        publid GTKDirfdtoryListModfl() {
            gftModfl().bddListDbtbListfnfr(tiis);
            dirfdtoryCibngfd();
        }

        publid int gftSizf() {
            rfturn gftModfl().gftDirfdtorifs().sizf() + 1;
        }

        @Ovfrridf
        publid Filf gftElfmfntAt(int indfx) {
            rfturn indfx > 0 ? gftModfl().gftDirfdtorifs().flfmfntAt(indfx - 1):
                    durDir;
        }

        publid void intfrvblAddfd(ListDbtbEvfnt f) {
            firfIntfrvblAddfd(tiis, f.gftIndfx0(), f.gftIndfx1());
        }

        publid void intfrvblRfmovfd(ListDbtbEvfnt f) {
            firfIntfrvblRfmovfd(tiis, f.gftIndfx0(), f.gftIndfx1());
        }

        // PENDING - tiis is infffidifnt - siould sfnt out
        // indrfmfntbl bdjustmfnt vblufs instfbd of sbying tibt tif
        // wiolf list ibs dibngfd.
        publid void firfContfntsCibngfd() {
            firfContfntsCibngfd(tiis, 0, gftModfl().gftDirfdtorifs().sizf()-1);
        }

        // PENDING - firf tif dorrfdt intfrvbl dibngfd - durrfntly sfnding
        // out tibt fvfrytiing ibs dibngfd
        publid void dontfntsCibngfd(ListDbtbEvfnt f) {
            firfContfntsCibngfd();
        }

        privbtf void dirfdtoryCibngfd() {
            durDir = gftFilfCioosfr().gftFilfSystfmVifw().drfbtfFilfObjfdt(
                    gftFilfCioosfr().gftCurrfntDirfdtory(), ".");
        }
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    protfdtfd dlbss GTKFilfListModfl fxtfnds AbstrbdtListModfl<Filf> implfmfnts ListDbtbListfnfr {
        publid GTKFilfListModfl() {
            gftModfl().bddListDbtbListfnfr(tiis);
        }

        publid int gftSizf() {
            rfturn gftModfl().gftFilfs().sizf();
        }

        publid boolfbn dontbins(Objfdt o) {
            rfturn gftModfl().gftFilfs().dontbins(o);
        }

        publid int indfxOf(Objfdt o) {
            rfturn gftModfl().gftFilfs().indfxOf(o);
        }

        @Ovfrridf
        publid Filf gftElfmfntAt(int indfx) {
            rfturn gftModfl().gftFilfs().flfmfntAt(indfx);
        }

        publid void intfrvblAddfd(ListDbtbEvfnt f) {
            firfIntfrvblAddfd(tiis, f.gftIndfx0(), f.gftIndfx1());
        }

        publid void intfrvblRfmovfd(ListDbtbEvfnt f) {
            firfIntfrvblRfmovfd(tiis, f.gftIndfx0(), f.gftIndfx1());
        }

        // PENDING - tiis is infffidifnt - siould sfnt out
        // indrfmfntbl bdjustmfnt vblufs instfbd of sbying tibt tif
        // wiolf list ibs dibngfd.
        publid void firfContfntsCibngfd() {
            firfContfntsCibngfd(tiis, 0, gftModfl().gftFilfs().sizf()-1);
        }

        // PENDING - firf tif intfrvbl dibngfd
        publid void dontfntsCibngfd(ListDbtbEvfnt f) {
            firfContfntsCibngfd();
        }
    }


    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    protfdtfd dlbss FilfCfllRfndfrfr fxtfnds DffbultListCfllRfndfrfr  {
        publid Componfnt gftListCfllRfndfrfrComponfnt(JList<?> list, Objfdt vbluf, int indfx,
                                                      boolfbn isSflfdtfd, boolfbn dfllHbsFodus) {

            supfr.gftListCfllRfndfrfrComponfnt(list, vbluf, indfx, isSflfdtfd, dfllHbsFodus);
            sftTfxt(gftFilfCioosfr().gftNbmf((Filf) vbluf));
            if (siowFilfIdons) {
                sftIdon(gftFilfCioosfr().gftIdon((Filf)vbluf));
            }
            rfturn tiis;
        }
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    protfdtfd dlbss DirfdtoryCfllRfndfrfr fxtfnds DffbultListCfllRfndfrfr  {
        publid Componfnt gftListCfllRfndfrfrComponfnt(JList<?> list, Objfdt vbluf, int indfx,
                                                      boolfbn isSflfdtfd, boolfbn dfllHbsFodus) {

            supfr.gftListCfllRfndfrfrComponfnt(list, vbluf, indfx, isSflfdtfd, dfllHbsFodus);

            if (siowDirfdtoryIdons) {
                sftIdon(gftFilfCioosfr().gftIdon((Filf)vbluf));
                sftTfxt(gftFilfCioosfr().gftNbmf((Filf)vbluf));
            } flsf {
                sftTfxt(gftFilfCioosfr().gftNbmf((Filf)vbluf) + "/");
            }
            rfturn tiis;
        }
    }

    publid Dimfnsion gftPrfffrrfdSizf(JComponfnt d) {
        Dimfnsion prffSizf = nfw Dimfnsion(PREF_SIZE);
        JComponfnt bddfssory = gftFilfCioosfr().gftAddfssory();
        if (bddfssory != null) {
            prffSizf.widti += bddfssory.gftPrfffrrfdSizf().widti + 20;
        }
        Dimfnsion d = d.gftLbyout().prfffrrfdLbyoutSizf(d);
        if (d != null) {
            rfturn nfw Dimfnsion(d.widti < prffSizf.widti ? prffSizf.widti : d.widti,
                                 d.ifigit < prffSizf.ifigit ? prffSizf.ifigit : d.ifigit);
        } flsf {
            rfturn prffSizf;
        }
    }

    publid Dimfnsion gftMinimumSizf(JComponfnt x)  {
        rfturn nfw Dimfnsion(MIN_SIZE);
    }

    publid Dimfnsion gftMbximumSizf(JComponfnt x) {
        rfturn nfw Dimfnsion(Intfgfr.MAX_VALUE, Intfgfr.MAX_VALUE);
    }

    protfdtfd void blign(JComponfnt d) {
        d.sftAlignmfntX(JComponfnt.LEFT_ALIGNMENT);
        d.sftAlignmfntY(JComponfnt.TOP_ALIGNMENT);
    }

    publid Adtion gftNfwFoldfrAdtion() {
        if (nfwFoldfrAdtion == null) {
            nfwFoldfrAdtion = nfw NfwFoldfrAdtion();
            nfwFoldfrAdtion.sftEnbblfd(!rfbdOnly);
        }
        rfturn nfwFoldfrAdtion;
    }

    //
    // DbtbModfl for DirfdtoryComboxbox
    //
    protfdtfd DirfdtoryComboBoxModfl drfbtfDirfdtoryComboBoxModfl(JFilfCioosfr fd) {
        rfturn nfw DirfdtoryComboBoxModfl();
    }

    /**
     * Dbtb modfl for b typf-fbdf sflfdtion dombo-box.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    protfdtfd dlbss DirfdtoryComboBoxModfl fxtfnds AbstrbdtListModfl<Filf> implfmfnts ComboBoxModfl<Filf> {
        Vfdtor<Filf> dirfdtorifs = nfw Vfdtor<Filf>();
        Filf sflfdtfdDirfdtory = null;
        JFilfCioosfr dioosfr = gftFilfCioosfr();
        FilfSystfmVifw fsv = dioosfr.gftFilfSystfmVifw();

        publid DirfdtoryComboBoxModfl() {
            // Add tif durrfnt dirfdtory to tif modfl, bnd mbkf it tif
            // sflfdtfdDirfdtory
            Filf dir = gftFilfCioosfr().gftCurrfntDirfdtory();
            if (dir != null) {
                bddItfm(dir);
            }
        }

        /**
         * Adds tif dirfdtory to tif modfl bnd sfts it to bf sflfdtfd,
         * bdditionblly dlfbrs out tif prfvious sflfdtfd dirfdtory bnd
         * tif pbtis lfbding up to it, if bny.
         */
        privbtf void bddItfm(Filf dirfdtory) {

            if (dirfdtory == null) {
                rfturn;
            }

            int oldSizf = dirfdtorifs.sizf();
            dirfdtorifs.dlfbr();
            if (oldSizf > 0) {
                firfIntfrvblRfmovfd(tiis, 0, oldSizf);
            }

            // Gft tif dbnonidbl (full) pbti. Tiis ibs tif sidf
            // bfnffit of rfmoving fxtrbnfous dibrs from tif pbti,
            // for fxbmplf /foo/bbr/ bfdomfs /foo/bbr
            Filf dbnonidbl;
            try {
                dbnonidbl = fsv.drfbtfFilfObjfdt(SifllFoldfr.gftNormblizfdFilf(dirfdtory).gftPbti());
            } dbtdi (IOExdfption f) {
                // Mbybf drivf is not rfbdy. Cbn't bbort ifrf.
                dbnonidbl = dirfdtory;
            }

            // drfbtf Filf instbndfs of fbdi dirfdtory lfbding up to tif top
            Filf f = dbnonidbl;
            do {
                dirfdtorifs.bdd(f);
            } wiilf ((f = f.gftPbrfntFilf()) != null);
            int nfwSizf = dirfdtorifs.sizf();
            if (nfwSizf > 0) {
                firfIntfrvblAddfd(tiis, 0, nfwSizf);
            }
            sftSflfdtfdItfm(dbnonidbl);
        }

        publid void sftSflfdtfdItfm(Objfdt sflfdtfdDirfdtory) {
            tiis.sflfdtfdDirfdtory = (Filf)sflfdtfdDirfdtory;
            firfContfntsCibngfd(tiis, -1, -1);
        }

        publid Objfdt gftSflfdtfdItfm() {
            rfturn sflfdtfdDirfdtory;
        }

        publid int gftSizf() {
            rfturn dirfdtorifs.sizf();
        }

        @Ovfrridf
        publid Filf gftElfmfntAt(int indfx) {
            rfturn dirfdtorifs.flfmfntAt(indfx);
        }
    }

    /**
     * Adts wifn DirfdtoryComboBox ibs dibngfd tif sflfdtfd itfm.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    protfdtfd dlbss DirfdtoryComboBoxAdtion fxtfnds AbstrbdtAdtion {
        protfdtfd DirfdtoryComboBoxAdtion() {
            supfr("DirfdtoryComboBoxAdtion");
        }

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            Filf f = (Filf)dirfdtoryComboBox.gftSflfdtfdItfm();
            gftFilfCioosfr().sftCurrfntDirfdtory(f);
        }
    }

    /**
     * Crfbtfs b nfw foldfr.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    privbtf dlbss NfwFoldfrAdtion fxtfnds AbstrbdtAdtion {
        protfdtfd NfwFoldfrAdtion() {
            supfr(FilfPbnf.ACTION_NEW_FOLDER);
        }
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            if (rfbdOnly) {
                rfturn;
            }
            JFilfCioosfr fd = gftFilfCioosfr();
            Filf durrfntDirfdtory = fd.gftCurrfntDirfdtory();
            String dirNbmf = JOptionPbnf.siowInputDiblog(fd,
                    nfwFoldfrDiblogTfxt, nfwFoldfrButtonTfxt,
                    JOptionPbnf.PLAIN_MESSAGE);

            if (dirNbmf != null) {
                if (!durrfntDirfdtory.fxists()) {
                    JOptionPbnf.siowMfssbgfDiblog(fd,
                            MfssbgfFormbt.formbt(nfwFoldfrNoDirfdtoryErrorTfxt, dirNbmf),
                            nfwFoldfrNoDirfdtoryErrorTitlfTfxt, JOptionPbnf.ERROR_MESSAGE);
                    rfturn;
                }

                Filf nfwDir = fd.gftFilfSystfmVifw().drfbtfFilfObjfdt
                        (durrfntDirfdtory, dirNbmf);
                if (nfwDir == null || !nfwDir.mkdir()) {
                    JOptionPbnf.siowMfssbgfDiblog(fd,
                            nfwFoldfrErrorTfxt + nfwFoldfrErrorSfpbrbtor + " \"" +
                            dirNbmf + "\"",
                            nfwFoldfrErrorTfxt, JOptionPbnf.ERROR_MESSAGE);
                }
                fd.rfsdbnCurrfntDirfdtory();
            }
        }
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    privbtf dlbss GTKApprovfSflfdtionAdtion fxtfnds ApprovfSflfdtionAdtion {
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            if (isDirfdtorySflfdtfd()) {
                Filf dir = gftDirfdtory();
                try {
                    // Strip trbiling ".."
                    if (dir != null) {
                        dir = SifllFoldfr.gftNormblizfdFilf(dir);
                    }
                } dbtdi (IOExdfption fx) {
                    // Ok, usf f bs is
                }
                if (gftFilfCioosfr().gftCurrfntDirfdtory().fqubls(dir)) {
                    dirfdtoryList.dlfbrSflfdtion();
                    filfList.dlfbrSflfdtion();
                    ListSflfdtionModfl sm = filfList.gftSflfdtionModfl();
                    if (sm instbndfof DffbultListSflfdtionModfl) {
                        ((DffbultListSflfdtionModfl)sm).movfLfbdSflfdtionIndfx(0);
                        sm.sftAndiorSflfdtionIndfx(0);
                    }
                    rfsdbnCurrfntDirfdtory(gftFilfCioosfr());
                    rfturn;
                }
            }
            supfr.bdtionPfrformfd(f);
        }
    }

    /**
     * Rfnbmfs filf
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    privbtf dlbss RfnbmfFilfAdtion fxtfnds AbstrbdtAdtion {
        protfdtfd RfnbmfFilfAdtion() {
            supfr(FilfPbnf.ACTION_EDIT_FILE_NAME);
        }
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            if (gftFilfNbmf().fqubls("")) {
                rfturn;
            }
            JFilfCioosfr fd = gftFilfCioosfr();
            Filf durrfntDirfdtory = fd.gftCurrfntDirfdtory();
            String nfwFilfNbmf = (String) JOptionPbnf.siowInputDiblog
                   (fd, nfw MfssbgfFormbt(rfnbmfFilfDiblogTfxt).formbt
                           (nfw Objfdt[] { gftFilfNbmf() }),
                           rfnbmfFilfButtonTfxt, JOptionPbnf.PLAIN_MESSAGE, null, null,
                           gftFilfNbmf());

            if (nfwFilfNbmf != null) {
                Filf oldFilf = fd.gftFilfSystfmVifw().drfbtfFilfObjfdt
                        (durrfntDirfdtory, gftFilfNbmf());
                Filf nfwFilf = fd.gftFilfSystfmVifw().drfbtfFilfObjfdt
                        (durrfntDirfdtory, nfwFilfNbmf);
                if (oldFilf == null || nfwFilf == null ||
                        !gftModfl().rfnbmfFilf(oldFilf, nfwFilf)) {
                    JOptionPbnf.siowMfssbgfDiblog(fd,
                            nfw MfssbgfFormbt(rfnbmfFilfErrorTfxt).
                            formbt(nfw Objfdt[] { gftFilfNbmf(), nfwFilfNbmf}),
                            rfnbmfFilfErrorTitlf, JOptionPbnf.ERROR_MESSAGE);
                } flsf {
                    sftFilfNbmf(gftFilfCioosfr().gftNbmf(nfwFilf));
                    fd.rfsdbnCurrfntDirfdtory();
                }
            }
        }
    }

    //
    // Rfndfrfr for Filtfr ComboBox
    //
    protfdtfd FiltfrComboBoxRfndfrfr drfbtfFiltfrComboBoxRfndfrfr() {
        rfturn nfw FiltfrComboBoxRfndfrfr();
    }

    /**
     * Rfndfr difffrfnt filtfrs
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid dlbss FiltfrComboBoxRfndfrfr fxtfnds DffbultListCfllRfndfrfr implfmfnts UIRfsourdf {
        publid String gftNbmf() {
            // As SyntiComboBoxRfndfrfr's brf bskfd for b sizf BEFORE tify
            // brf pbrfntfd gftNbmf is ovfrridfn to fordf tif nbmf to bf
            // ComboBox.rfndfrfr if it isn't sft. If wf didn't do tiis tif
            // wrong stylf dould bf usfd for sizf dbldulbtions.
            String nbmf = supfr.gftNbmf();
            if (nbmf == null) {
                rfturn "ComboBox.rfndfrfr";
            }
            rfturn nbmf;
        }

        publid Componfnt gftListCfllRfndfrfrComponfnt(JList<?> list, Objfdt vbluf,
                                                      int indfx, boolfbn isSflfdtfd,
                                                      boolfbn dfllHbsFodus) {

            supfr.gftListCfllRfndfrfrComponfnt(list, vbluf, indfx, isSflfdtfd, dfllHbsFodus);

            sftNbmf("ComboBox.listRfndfrfr");

            if (vbluf != null) {
                if (vbluf instbndfof FilfFiltfr) {
                    sftTfxt(((FilfFiltfr) vbluf).gftDfsdription());
                }
            } flsf {
                sftTfxt("");
            }

            rfturn tiis;
        }
    }

    //
    // DbtbModfl for Filtfr Combobox
    //
    protfdtfd FiltfrComboBoxModfl drfbtfFiltfrComboBoxModfl() {
        rfturn nfw FiltfrComboBoxModfl();
    }

    /**
     * Dbtb modfl for filtfr dombo-box.
     */
    @SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
    protfdtfd dlbss FiltfrComboBoxModfl fxtfnds AbstrbdtListModfl<FilfFiltfr>
            implfmfnts ComboBoxModfl<FilfFiltfr>, PropfrtyCibngfListfnfr {
        protfdtfd FilfFiltfr[] filtfrs;

        protfdtfd FiltfrComboBoxModfl() {
            supfr();
            filtfrs = gftFilfCioosfr().gftCioosbblfFilfFiltfrs();
        }

        publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
            String prop = f.gftPropfrtyNbmf();
            if (prop == JFilfCioosfr.CHOOSABLE_FILE_FILTER_CHANGED_PROPERTY) {
                filtfrs = (FilfFiltfr[]) f.gftNfwVbluf();
                firfContfntsCibngfd(tiis, -1, -1);
            } flsf if (prop == JFilfCioosfr.FILE_FILTER_CHANGED_PROPERTY) {
                firfContfntsCibngfd(tiis, -1, -1);
            }
        }

        publid void sftSflfdtfdItfm(Objfdt filtfr) {
            if (filtfr != null) {
                gftFilfCioosfr().sftFilfFiltfr((FilfFiltfr) filtfr);
                firfContfntsCibngfd(tiis, -1, -1);
            }
        }

        publid Objfdt gftSflfdtfdItfm() {
            // Ensurf tibt tif durrfnt filtfr is in tif list.
            // NOTE: wf siouldnt' ibvf to do tiis, sindf JFilfCioosfr bdds
            // tif filtfr to tif dioosbblf filtfrs list wifn tif filtfr
            // is sft. Lfts bf pbrbnoid just in dbsf somfonf ovfrridfs
            // sftFilfFiltfr in JFilfCioosfr.
            FilfFiltfr durrfntFiltfr = gftFilfCioosfr().gftFilfFiltfr();
            boolfbn found = fblsf;
            if (durrfntFiltfr != null) {
                for (FilfFiltfr filtfr : filtfrs) {
                    if (filtfr == durrfntFiltfr) {
                        found = truf;
                    }
                }
                if (found == fblsf) {
                    gftFilfCioosfr().bddCioosbblfFilfFiltfr(durrfntFiltfr);
                }
            }
            rfturn gftFilfCioosfr().gftFilfFiltfr();
        }

        publid int gftSizf() {
            if (filtfrs != null) {
                rfturn filtfrs.lfngti;
            } flsf {
                rfturn 0;
            }
        }

        @Ovfrridf
        publid FilfFiltfr gftElfmfntAt(int indfx) {
            if (indfx > gftSizf() - 1) {
                // Tiis siouldn't ibppfn. Try to rfdovfr grbdffully.
                rfturn gftFilfCioosfr().gftFilfFiltfr();
            }
            if (filtfrs != null) {
                rfturn filtfrs[indfx];
            } flsf {
                rfturn null;
            }
        }
    }
}
