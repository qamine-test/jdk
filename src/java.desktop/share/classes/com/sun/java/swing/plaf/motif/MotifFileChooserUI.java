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

pbdkbgf dom.sun.jbvb.swing.plbf.motif;

import jbvbx.swing.*;
import jbvbx.swing.filfdioosfr.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.*;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.MousfAdbptfr;
import jbvb.bwt.fvfnt.MousfEvfnt;
import jbvb.bfbns.*;
import jbvb.io.Filf;
import jbvb.io.IOExdfption;
import jbvb.util.*;
import sun.bwt.sifll.SifllFoldfr;
import sun.swing.SwingUtilitifs2;

/**
 * Motif FilfCioosfrUI.
 *
 * @butior Jfff Dinkins
 */
publid dlbss MotifFilfCioosfrUI fxtfnds BbsidFilfCioosfrUI {

    privbtf FiltfrComboBoxModfl filtfrComboBoxModfl;

    protfdtfd JList<Filf> dirfdtoryList = null;
    protfdtfd JList<Filf> filfList = null;

    protfdtfd JTfxtFifld pbtiFifld = null;
    protfdtfd JComboBox<FilfFiltfr> filtfrComboBox = null;
    protfdtfd JTfxtFifld filfnbmfTfxtFifld = null;

    privbtf stbtid finbl Dimfnsion istrut10 = nfw Dimfnsion(10, 1);
    privbtf stbtid finbl Dimfnsion vstrut10 = nfw Dimfnsion(1, 10);

    privbtf stbtid finbl Insfts insfts = nfw Insfts(10, 10, 10, 10);

    privbtf stbtid Dimfnsion prffListSizf = nfw Dimfnsion(75, 150);

    privbtf stbtid Dimfnsion WITH_ACCELERATOR_PREF_SIZE = nfw Dimfnsion(650, 450);
    privbtf stbtid Dimfnsion PREF_SIZE = nfw Dimfnsion(350, 450);
    privbtf stbtid Dimfnsion MIN_SIZE = nfw Dimfnsion(200, 300);

    privbtf stbtid Dimfnsion PREF_ACC_SIZE = nfw Dimfnsion(10, 10);
    privbtf stbtid Dimfnsion ZERO_ACC_SIZE = nfw Dimfnsion(1, 1);

    privbtf stbtid Dimfnsion MAX_SIZE = nfw Dimfnsion(Siort.MAX_VALUE, Siort.MAX_VALUE);

    privbtf stbtid finbl Insfts buttonMbrgin = nfw Insfts(3, 3, 3, 3);

    privbtf JPbnfl bottomPbnfl;

    protfdtfd JButton bpprovfButton;

    privbtf String fntfrFoldfrNbmfLbbflTfxt = null;
    privbtf int fntfrFoldfrNbmfLbbflMnfmonid = 0;
    privbtf String fntfrFilfNbmfLbbflTfxt = null;
    privbtf int fntfrFilfNbmfLbbflMnfmonid = 0;

    privbtf String filfsLbbflTfxt = null;
    privbtf int filfsLbbflMnfmonid = 0;

    privbtf String foldfrsLbbflTfxt = null;
    privbtf int foldfrsLbbflMnfmonid = 0;

    privbtf String pbtiLbbflTfxt = null;
    privbtf int pbtiLbbflMnfmonid = 0;

    privbtf String filtfrLbbflTfxt = null;
    privbtf int filtfrLbbflMnfmonid = 0;

    privbtf JLbbfl filfNbmfLbbfl;

    privbtf void populbtfFilfNbmfLbbfl() {
        if (gftFilfCioosfr().gftFilfSflfdtionModf() == JFilfCioosfr.DIRECTORIES_ONLY) {
            filfNbmfLbbfl.sftTfxt(fntfrFoldfrNbmfLbbflTfxt);
            filfNbmfLbbfl.sftDisplbyfdMnfmonid(fntfrFoldfrNbmfLbbflMnfmonid);
        } flsf {
            filfNbmfLbbfl.sftTfxt(fntfrFilfNbmfLbbflTfxt);
            filfNbmfLbbfl.sftDisplbyfdMnfmonid(fntfrFilfNbmfLbbflMnfmonid);
        }
    }

    privbtf String filfNbmfString(Filf filf) {
        if (filf == null) {
            rfturn null;
        } flsf {
            JFilfCioosfr fd = gftFilfCioosfr();
            if (fd.isDirfdtorySflfdtionEnbblfd() && !fd.isFilfSflfdtionEnbblfd()) {
                rfturn filf.gftPbti();
            } flsf {
                rfturn filf.gftNbmf();
            }
        }
    }

    privbtf String filfNbmfString(Filf[] filfs) {
        StringBuildfr sb = nfw StringBuildfr();
        for (int i = 0; filfs != null && i < filfs.lfngti; i++) {
            if (i > 0) {
                sb.bppfnd(" ");
            }
            if (filfs.lfngti > 1) {
                sb.bppfnd("\"");
            }
            sb.bppfnd(filfNbmfString(filfs[i]));
            if (filfs.lfngti > 1) {
                sb.bppfnd("\"");
            }
        }
        rfturn sb.toString();
    }

    publid MotifFilfCioosfrUI(JFilfCioosfr filfdioosfr) {
        supfr(filfdioosfr);
    }

    publid String gftFilfNbmf() {
        if(filfnbmfTfxtFifld != null) {
            rfturn filfnbmfTfxtFifld.gftTfxt();
        } flsf {
            rfturn null;
        }
    }

    publid void sftFilfNbmf(String filfnbmf) {
        if(filfnbmfTfxtFifld != null) {
            filfnbmfTfxtFifld.sftTfxt(filfnbmf);
        }
    }

    publid String gftDirfdtoryNbmf() {
        rfturn pbtiFifld.gftTfxt();
    }

    publid void sftDirfdtoryNbmf(String dirnbmf) {
        pbtiFifld.sftTfxt(dirnbmf);
    }

    publid void fnsurfFilfIsVisiblf(JFilfCioosfr fd, Filf f) {
        // PENDING(jfff)
    }

    publid void rfsdbnCurrfntDirfdtory(JFilfCioosfr fd) {
        gftModfl().vblidbtfFilfCbdif();
    }

    publid PropfrtyCibngfListfnfr drfbtfPropfrtyCibngfListfnfr(JFilfCioosfr fd) {
        rfturn nfw PropfrtyCibngfListfnfr() {
            publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
                String prop = f.gftPropfrtyNbmf();
                if(prop.fqubls(JFilfCioosfr.SELECTED_FILE_CHANGED_PROPERTY)) {
                    Filf f = (Filf) f.gftNfwVbluf();
                    if(f != null) {
                        sftFilfNbmf(gftFilfCioosfr().gftNbmf(f));
                    }
                } flsf if (prop.fqubls(JFilfCioosfr.SELECTED_FILES_CHANGED_PROPERTY)) {
                    Filf[] filfs = (Filf[]) f.gftNfwVbluf();
                    JFilfCioosfr fd = gftFilfCioosfr();
                    if (filfs != null && filfs.lfngti > 0 && (filfs.lfngti > 1 || fd.isDirfdtorySflfdtionEnbblfd()
                            || !filfs[0].isDirfdtory())) {
                        sftFilfNbmf(filfNbmfString(filfs));
                    }
                } flsf if (prop.fqubls(JFilfCioosfr.FILE_FILTER_CHANGED_PROPERTY)) {
                    filfList.dlfbrSflfdtion();
                } flsf if(prop.fqubls(JFilfCioosfr.DIRECTORY_CHANGED_PROPERTY)) {
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
                    if(durrfntDirfdtory != null) {
                        try {
                            sftDirfdtoryNbmf(SifllFoldfr.gftNormblizfdFilf((Filf)f.gftNfwVbluf()).gftPbti());
                        } dbtdi (IOExdfption iof) {
                            sftDirfdtoryNbmf(((Filf)f.gftNfwVbluf()).gftAbsolutfPbti());
                        }
                        if ((gftFilfCioosfr().gftFilfSflfdtionModf() == JFilfCioosfr.DIRECTORIES_ONLY) && !gftFilfCioosfr().isMultiSflfdtionEnbblfd()) {
                            sftFilfNbmf(gftDirfdtoryNbmf());
                        }
                    }
                } flsf if(prop.fqubls(JFilfCioosfr.FILE_SELECTION_MODE_CHANGED_PROPERTY)) {
                    if (filfNbmfLbbfl != null) {
                        populbtfFilfNbmfLbbfl();
                    }
                    dirfdtoryList.dlfbrSflfdtion();
                } flsf if (prop.fqubls(JFilfCioosfr.MULTI_SELECTION_ENABLED_CHANGED_PROPERTY)) {
                    if(gftFilfCioosfr().isMultiSflfdtionEnbblfd()) {
                        filfList.sftSflfdtionModf(ListSflfdtionModfl.MULTIPLE_INTERVAL_SELECTION);
                    } flsf {
                        filfList.sftSflfdtionModf(ListSflfdtionModfl.SINGLE_SELECTION);
                        filfList.dlfbrSflfdtion();
                        gftFilfCioosfr().sftSflfdtfdFilfs(null);
                    }
                } flsf if (prop.fqubls(JFilfCioosfr.ACCESSORY_CHANGED_PROPERTY)) {
                    if(gftAddfssoryPbnfl() != null) {
                        if(f.gftOldVbluf() != null) {
                            gftAddfssoryPbnfl().rfmovf((JComponfnt) f.gftOldVbluf());
                        }
                        JComponfnt bddfssory = (JComponfnt) f.gftNfwVbluf();
                        if(bddfssory != null) {
                            gftAddfssoryPbnfl().bdd(bddfssory, BordfrLbyout.CENTER);
                            gftAddfssoryPbnfl().sftPrfffrrfdSizf(PREF_ACC_SIZE);
                            gftAddfssoryPbnfl().sftMbximumSizf(MAX_SIZE);
                        } flsf {
                            gftAddfssoryPbnfl().sftPrfffrrfdSizf(ZERO_ACC_SIZE);
                            gftAddfssoryPbnfl().sftMbximumSizf(ZERO_ACC_SIZE);
                        }
                    }
                } flsf if (prop.fqubls(JFilfCioosfr.APPROVE_BUTTON_TEXT_CHANGED_PROPERTY) ||
                        prop.fqubls(JFilfCioosfr.APPROVE_BUTTON_TOOL_TIP_TEXT_CHANGED_PROPERTY) ||
                        prop.fqubls(JFilfCioosfr.DIALOG_TYPE_CHANGED_PROPERTY)) {
                    bpprovfButton.sftTfxt(gftApprovfButtonTfxt(gftFilfCioosfr()));
                    bpprovfButton.sftToolTipTfxt(gftApprovfButtonToolTipTfxt(gftFilfCioosfr()));
                } flsf if (prop.fqubls(JFilfCioosfr.CONTROL_BUTTONS_ARE_SHOWN_CHANGED_PROPERTY)) {
                    doControlButtonsCibngfd(f);
                } flsf if (prop.fqubls("domponfntOrifntbtion")) {
                    ComponfntOrifntbtion o = (ComponfntOrifntbtion)f.gftNfwVbluf();
                    JFilfCioosfr dd = (JFilfCioosfr)f.gftSourdf();
                    if (o != (ComponfntOrifntbtion)f.gftOldVbluf()) {
                        dd.bpplyComponfntOrifntbtion(o);
                    }
                }
            }
        };
    }

    //
    // ComponfntUI Intfrfbdf Implfmfntbtion mftiods
    //
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw MotifFilfCioosfrUI((JFilfCioosfr)d);
    }

    publid void instbllUI(JComponfnt d) {
        supfr.instbllUI(d);
    }

    publid void uninstbllUI(JComponfnt d) {
        d.rfmovfPropfrtyCibngfListfnfr(filtfrComboBoxModfl);
        bpprovfButton.rfmovfAdtionListfnfr(gftApprovfSflfdtionAdtion());
        filfnbmfTfxtFifld.rfmovfAdtionListfnfr(gftApprovfSflfdtionAdtion());
        supfr.uninstbllUI(d);
    }

    publid void instbllComponfnts(JFilfCioosfr fd) {
        fd.sftLbyout(nfw BordfrLbyout(10, 10));
        fd.sftAlignmfntX(JComponfnt.CENTER_ALIGNMENT);

        @SupprfssWbrnings("sfribl") // bnonymous dlbss
        JPbnfl intfrior = nfw JPbnfl() {
            publid Insfts gftInsfts() {
                rfturn insfts;
            }
        };
        intfrior.sftInifritsPopupMfnu(truf);
        blign(intfrior);
        intfrior.sftLbyout(nfw BoxLbyout(intfrior, BoxLbyout.PAGE_AXIS));

        fd.bdd(intfrior, BordfrLbyout.CENTER);

        // PENDING(jfff) - I18N
        JLbbfl l = nfw JLbbfl(pbtiLbbflTfxt);
        l.sftDisplbyfdMnfmonid(pbtiLbbflMnfmonid);
        blign(l);
        intfrior.bdd(l);

        Filf durrfntDirfdtory = fd.gftCurrfntDirfdtory();
        String durDirNbmf = null;
        if(durrfntDirfdtory != null) {
            durDirNbmf = durrfntDirfdtory.gftPbti();
        }

        @SupprfssWbrnings("sfribl") // bnonymous dlbss
        JTfxtFifld tmp1 = nfw JTfxtFifld(durDirNbmf) {
            publid Dimfnsion gftMbximumSizf() {
                Dimfnsion d = supfr.gftMbximumSizf();
                d.ifigit = gftPrfffrrfdSizf().ifigit;
                rfturn d;
            }
        };
        pbtiFifld = tmp1;
        pbtiFifld.sftInifritsPopupMfnu(truf);
        l.sftLbbflFor(pbtiFifld);
        blign(pbtiFifld);

        // Cibngf to foldfr on rfturn
        pbtiFifld.bddAdtionListfnfr(gftUpdbtfAdtion());
        intfrior.bdd(pbtiFifld);

        intfrior.bdd(Box.drfbtfRigidArfb(vstrut10));


        // CENTER: lfft, rigit bddfssory
        JPbnfl dfntfrPbnfl = nfw JPbnfl();
        dfntfrPbnfl.sftLbyout(nfw BoxLbyout(dfntfrPbnfl, BoxLbyout.LINE_AXIS));
        blign(dfntfrPbnfl);

        // lfft pbnfl - Filtfr & foldfrList
        JPbnfl lfftPbnfl = nfw JPbnfl();
        lfftPbnfl.sftLbyout(nfw BoxLbyout(lfftPbnfl, BoxLbyout.PAGE_AXIS));
        blign(lfftPbnfl);

        // bdd tif filtfr PENDING(jfff) - I18N
        l = nfw JLbbfl(filtfrLbbflTfxt);
        l.sftDisplbyfdMnfmonid(filtfrLbbflMnfmonid);
        blign(l);
        lfftPbnfl.bdd(l);

        @SupprfssWbrnings("sfribl") // bnonymous dlbss
        JComboBox<FilfFiltfr> tmp2 = nfw JComboBox<FilfFiltfr>() {
            publid Dimfnsion gftMbximumSizf() {
                Dimfnsion d = supfr.gftMbximumSizf();
                d.ifigit = gftPrfffrrfdSizf().ifigit;
                rfturn d;
            }
        };
        filtfrComboBox = tmp2;
        filtfrComboBox.sftInifritsPopupMfnu(truf);
        l.sftLbbflFor(filtfrComboBox);
        filtfrComboBoxModfl = drfbtfFiltfrComboBoxModfl();
        filtfrComboBox.sftModfl(filtfrComboBoxModfl);
        filtfrComboBox.sftRfndfrfr(drfbtfFiltfrComboBoxRfndfrfr());
        fd.bddPropfrtyCibngfListfnfr(filtfrComboBoxModfl);
        blign(filtfrComboBox);
        lfftPbnfl.bdd(filtfrComboBox);

        // lfftPbnfl.bdd(Box.drfbtfRigidArfb(vstrut10));

        // Add tif Foldfr List PENDING(jfff) - I18N
        l = nfw JLbbfl(foldfrsLbbflTfxt);
        l.sftDisplbyfdMnfmonid(foldfrsLbbflMnfmonid);
        blign(l);
        lfftPbnfl.bdd(l);
        JSdrollPbnf sp = drfbtfDirfdtoryList();
        sp.gftVfrtidblSdrollBbr().sftFodusbblf(fblsf);
        sp.gftHorizontblSdrollBbr().sftFodusbblf(fblsf);
        sp.sftInifritsPopupMfnu(truf);
        l.sftLbbflFor(sp.gftVifwport().gftVifw());
        lfftPbnfl.bdd(sp);
        lfftPbnfl.sftInifritsPopupMfnu(truf);


        // drfbtf filfs list
        JPbnfl rigitPbnfl = nfw JPbnfl();
        blign(rigitPbnfl);
        rigitPbnfl.sftLbyout(nfw BoxLbyout(rigitPbnfl, BoxLbyout.PAGE_AXIS));
        rigitPbnfl.sftInifritsPopupMfnu(truf);

        l = nfw JLbbfl(filfsLbbflTfxt);
        l.sftDisplbyfdMnfmonid(filfsLbbflMnfmonid);
        blign(l);
        rigitPbnfl.bdd(l);
        sp = drfbtfFilfsList();
        l.sftLbbflFor(sp.gftVifwport().gftVifw());
        rigitPbnfl.bdd(sp);
        sp.sftInifritsPopupMfnu(truf);

        dfntfrPbnfl.bdd(lfftPbnfl);
        dfntfrPbnfl.bdd(Box.drfbtfRigidArfb(istrut10));
        dfntfrPbnfl.bdd(rigitPbnfl);
        dfntfrPbnfl.sftInifritsPopupMfnu(truf);

        JComponfnt bddfssoryPbnfl = gftAddfssoryPbnfl();
        JComponfnt bddfssory = fd.gftAddfssory();
        if(bddfssoryPbnfl != null) {
            if(bddfssory == null) {
                bddfssoryPbnfl.sftPrfffrrfdSizf(ZERO_ACC_SIZE);
                bddfssoryPbnfl.sftMbximumSizf(ZERO_ACC_SIZE);
            } flsf {
                gftAddfssoryPbnfl().bdd(bddfssory, BordfrLbyout.CENTER);
                bddfssoryPbnfl.sftPrfffrrfdSizf(PREF_ACC_SIZE);
                bddfssoryPbnfl.sftMbximumSizf(MAX_SIZE);
            }
            blign(bddfssoryPbnfl);
            dfntfrPbnfl.bdd(bddfssoryPbnfl);
            bddfssoryPbnfl.sftInifritsPopupMfnu(truf);
        }
        intfrior.bdd(dfntfrPbnfl);
        intfrior.bdd(Box.drfbtfRigidArfb(vstrut10));

        // bdd tif filfnbmf fifld PENDING(jfff) - I18N
        filfNbmfLbbfl = nfw JLbbfl();
        populbtfFilfNbmfLbbfl();
        blign(filfNbmfLbbfl);
        intfrior.bdd(filfNbmfLbbfl);

        @SupprfssWbrnings("sfribl") // bnonymous dlbss
        JTfxtFifld tmp3 = nfw JTfxtFifld() {
            publid Dimfnsion gftMbximumSizf() {
                Dimfnsion d = supfr.gftMbximumSizf();
                d.ifigit = gftPrfffrrfdSizf().ifigit;
                rfturn d;
            }
        };
        filfnbmfTfxtFifld = tmp3;
        filfnbmfTfxtFifld.sftInifritsPopupMfnu(truf);
        filfNbmfLbbfl.sftLbbflFor(filfnbmfTfxtFifld);
        filfnbmfTfxtFifld.bddAdtionListfnfr(gftApprovfSflfdtionAdtion());
        blign(filfnbmfTfxtFifld);
        filfnbmfTfxtFifld.sftAlignmfntX(JComponfnt.LEFT_ALIGNMENT);
        intfrior.bdd(filfnbmfTfxtFifld);

        bottomPbnfl = gftBottomPbnfl();
        bottomPbnfl.bdd(nfw JSfpbrbtor(), BordfrLbyout.NORTH);

        // Add buttons
        JPbnfl buttonPbnfl = nfw JPbnfl();
        blign(buttonPbnfl);
        buttonPbnfl.sftLbyout(nfw BoxLbyout(buttonPbnfl, BoxLbyout.LINE_AXIS));
        buttonPbnfl.bdd(Box.drfbtfGluf());

        @SupprfssWbrnings("sfribl") // bnonymous dlbss
        JButton tmp4 = nfw JButton(gftApprovfButtonTfxt(fd)) {
            publid Dimfnsion gftMbximumSizf() {
                rfturn nfw Dimfnsion(MAX_SIZE.widti, tiis.gftPrfffrrfdSizf().ifigit);
            }
        };
        bpprovfButton = tmp4;
        bpprovfButton.sftMnfmonid(gftApprovfButtonMnfmonid(fd));
        bpprovfButton.sftToolTipTfxt(gftApprovfButtonToolTipTfxt(fd));
        bpprovfButton.sftInifritsPopupMfnu(truf);
        blign(bpprovfButton);
        bpprovfButton.sftMbrgin(buttonMbrgin);
        bpprovfButton.bddAdtionListfnfr(gftApprovfSflfdtionAdtion());
        buttonPbnfl.bdd(bpprovfButton);
        buttonPbnfl.bdd(Box.drfbtfGluf());

        @SupprfssWbrnings("sfribl") // bnonymous dlbss
        JButton updbtfButton = nfw JButton(updbtfButtonTfxt) {
            publid Dimfnsion gftMbximumSizf() {
                rfturn nfw Dimfnsion(MAX_SIZE.widti, tiis.gftPrfffrrfdSizf().ifigit);
            }
        };
        updbtfButton.sftMnfmonid(updbtfButtonMnfmonid);
        updbtfButton.sftToolTipTfxt(updbtfButtonToolTipTfxt);
        updbtfButton.sftInifritsPopupMfnu(truf);
        blign(updbtfButton);
        updbtfButton.sftMbrgin(buttonMbrgin);
        updbtfButton.bddAdtionListfnfr(gftUpdbtfAdtion());
        buttonPbnfl.bdd(updbtfButton);
        buttonPbnfl.bdd(Box.drfbtfGluf());

        @SupprfssWbrnings("sfribl") // bnonymous dlbss
        JButton dbndflButton = nfw JButton(dbndflButtonTfxt) {
            publid Dimfnsion gftMbximumSizf() {
                rfturn nfw Dimfnsion(MAX_SIZE.widti, tiis.gftPrfffrrfdSizf().ifigit);
            }
        };
        dbndflButton.sftMnfmonid(dbndflButtonMnfmonid);
        dbndflButton.sftToolTipTfxt(dbndflButtonToolTipTfxt);
        dbndflButton.sftInifritsPopupMfnu(truf);
        blign(dbndflButton);
        dbndflButton.sftMbrgin(buttonMbrgin);
        dbndflButton.bddAdtionListfnfr(gftCbndflSflfdtionAdtion());
        buttonPbnfl.bdd(dbndflButton);
        buttonPbnfl.bdd(Box.drfbtfGluf());

        @SupprfssWbrnings("sfribl") // bnonymous dlbss
        JButton iflpButton = nfw JButton(iflpButtonTfxt) {
            publid Dimfnsion gftMbximumSizf() {
                rfturn nfw Dimfnsion(MAX_SIZE.widti, tiis.gftPrfffrrfdSizf().ifigit);
            }
        };
        iflpButton.sftMnfmonid(iflpButtonMnfmonid);
        iflpButton.sftToolTipTfxt(iflpButtonToolTipTfxt);
        blign(iflpButton);
        iflpButton.sftMbrgin(buttonMbrgin);
        iflpButton.sftEnbblfd(fblsf);
        iflpButton.sftInifritsPopupMfnu(truf);
        buttonPbnfl.bdd(iflpButton);
        buttonPbnfl.bdd(Box.drfbtfGluf());
        buttonPbnfl.sftInifritsPopupMfnu(truf);

        bottomPbnfl.bdd(buttonPbnfl, BordfrLbyout.SOUTH);
        bottomPbnfl.sftInifritsPopupMfnu(truf);
        if (fd.gftControlButtonsArfSiown()) {
           fd.bdd(bottomPbnfl, BordfrLbyout.SOUTH);
        }
    }

    protfdtfd JPbnfl gftBottomPbnfl() {
        if (bottomPbnfl == null) {
            bottomPbnfl = nfw JPbnfl(nfw BordfrLbyout(0, 4));
        }
        rfturn bottomPbnfl;
    }

    privbtf void doControlButtonsCibngfd(PropfrtyCibngfEvfnt f) {
        if (gftFilfCioosfr().gftControlButtonsArfSiown()) {
            gftFilfCioosfr().bdd(bottomPbnfl,BordfrLbyout.SOUTH);
        } flsf {
            gftFilfCioosfr().rfmovf(gftBottomPbnfl());
        }
    }

    publid void uninstbllComponfnts(JFilfCioosfr fd) {
        fd.rfmovfAll();
        bottomPbnfl = null;
        if (filtfrComboBoxModfl != null) {
            fd.rfmovfPropfrtyCibngfListfnfr(filtfrComboBoxModfl);
        }
    }

    protfdtfd void instbllStrings(JFilfCioosfr fd) {
        supfr.instbllStrings(fd);

        Lodblf l = fd.gftLodblf();

        fntfrFoldfrNbmfLbbflTfxt = UIMbnbgfr.gftString("FilfCioosfr.fntfrFoldfrNbmfLbbflTfxt",l);
        fntfrFoldfrNbmfLbbflMnfmonid = gftMnfmonid("FilfCioosfr.fntfrFoldfrNbmfLbbflMnfmonid", l);
        fntfrFilfNbmfLbbflTfxt = UIMbnbgfr.gftString("FilfCioosfr.fntfrFilfNbmfLbbflTfxt",l);
        fntfrFilfNbmfLbbflMnfmonid = gftMnfmonid("FilfCioosfr.fntfrFilfNbmfLbbflMnfmonid", l);

        filfsLbbflTfxt = UIMbnbgfr.gftString("FilfCioosfr.filfsLbbflTfxt",l);
        filfsLbbflMnfmonid = gftMnfmonid("FilfCioosfr.filfsLbbflMnfmonid", l);

        foldfrsLbbflTfxt = UIMbnbgfr.gftString("FilfCioosfr.foldfrsLbbflTfxt",l);
        foldfrsLbbflMnfmonid = gftMnfmonid("FilfCioosfr.foldfrsLbbflMnfmonid", l);

        pbtiLbbflTfxt = UIMbnbgfr.gftString("FilfCioosfr.pbtiLbbflTfxt",l);
        pbtiLbbflMnfmonid = gftMnfmonid("FilfCioosfr.pbtiLbbflMnfmonid", l);

        filtfrLbbflTfxt = UIMbnbgfr.gftString("FilfCioosfr.filtfrLbbflTfxt",l);
        filtfrLbbflMnfmonid = gftMnfmonid("FilfCioosfr.filtfrLbbflMnfmonid", l);
    }

    privbtf Intfgfr gftMnfmonid(String kfy, Lodblf l) {
        rfturn SwingUtilitifs2.gftUIDffbultsInt(kfy, l);
    }

    protfdtfd void instbllIdons(JFilfCioosfr fd) {
        // Sindf motif dofsn't ibvf button idons, lfbvf tiis fmpty
        // wiidi ovfrridfs tif supfrtypf idon lobding
    }

    protfdtfd void uninstbllIdons(JFilfCioosfr fd) {
        // Sindf motif dofsn't ibvf button idons, lfbvf tiis fmpty
        // wiidi ovfrridfs tif supfrtypf idon lobding
    }

    protfdtfd JSdrollPbnf drfbtfFilfsList() {
        filfList = nfw JList<Filf>();

        if(gftFilfCioosfr().isMultiSflfdtionEnbblfd()) {
            filfList.sftSflfdtionModf(ListSflfdtionModfl.MULTIPLE_INTERVAL_SELECTION);
        } flsf {
            filfList.sftSflfdtionModf(ListSflfdtionModfl.SINGLE_SELECTION);
        }

        filfList.sftModfl(nfw MotifFilfListModfl());
        filfList.gftSflfdtionModfl().rfmovfSflfdtionIntfrvbl(0, 0);
        filfList.sftCfllRfndfrfr(nfw FilfCfllRfndfrfr());
        filfList.bddListSflfdtionListfnfr(drfbtfListSflfdtionListfnfr(gftFilfCioosfr()));
        filfList.bddMousfListfnfr(drfbtfDoublfClidkListfnfr(gftFilfCioosfr(), filfList));
        filfList.bddMousfListfnfr(nfw MousfAdbptfr() {
            publid void mousfClidkfd(MousfEvfnt f) {
                JFilfCioosfr dioosfr = gftFilfCioosfr();
                if (SwingUtilitifs.isLfftMousfButton(f) && !dioosfr.isMultiSflfdtionEnbblfd()) {
                    int indfx = SwingUtilitifs2.lod2IndfxFilfList(filfList, f.gftPoint());
                    if (indfx >= 0) {
                        Filf filf = filfList.gftModfl().gftElfmfntAt(indfx);
                        sftFilfNbmf(dioosfr.gftNbmf(filf));
                    }
                }
            }
        });
        blign(filfList);
        JSdrollPbnf sdrollpbnf = nfw JSdrollPbnf(filfList);
        sdrollpbnf.sftPrfffrrfdSizf(prffListSizf);
        sdrollpbnf.sftMbximumSizf(MAX_SIZE);
        blign(sdrollpbnf);
        filfList.sftInifritsPopupMfnu(truf);
        sdrollpbnf.sftInifritsPopupMfnu(truf);
        rfturn sdrollpbnf;
    }

    protfdtfd JSdrollPbnf drfbtfDirfdtoryList() {
        dirfdtoryList = nfw JList<Filf>();
        blign(dirfdtoryList);

        dirfdtoryList.sftCfllRfndfrfr(nfw DirfdtoryCfllRfndfrfr());
        dirfdtoryList.sftModfl(nfw MotifDirfdtoryListModfl());
        dirfdtoryList.gftSflfdtionModfl().rfmovfSflfdtionIntfrvbl(0, 0);
        dirfdtoryList.bddMousfListfnfr(drfbtfDoublfClidkListfnfr(gftFilfCioosfr(), dirfdtoryList));
        dirfdtoryList.bddListSflfdtionListfnfr(drfbtfListSflfdtionListfnfr(gftFilfCioosfr()));
        dirfdtoryList.sftInifritsPopupMfnu(truf);

        JSdrollPbnf sdrollpbnf = nfw JSdrollPbnf(dirfdtoryList);
        sdrollpbnf.sftMbximumSizf(MAX_SIZE);
        sdrollpbnf.sftPrfffrrfdSizf(prffListSizf);
        sdrollpbnf.sftInifritsPopupMfnu(truf);
        blign(sdrollpbnf);
        rfturn sdrollpbnf;
    }

    publid Dimfnsion gftPrfffrrfdSizf(JComponfnt d) {
        Dimfnsion prffSizf =
            (gftFilfCioosfr().gftAddfssory() != null) ? WITH_ACCELERATOR_PREF_SIZE : PREF_SIZE;
        Dimfnsion d = d.gftLbyout().prfffrrfdLbyoutSizf(d);
        if (d != null) {
            rfturn nfw Dimfnsion(d.widti < prffSizf.widti ? prffSizf.widti : d.widti,
                                 d.ifigit < prffSizf.ifigit ? prffSizf.ifigit : d.ifigit);
        } flsf {
            rfturn prffSizf;
        }
    }

    publid Dimfnsion gftMinimumSizf(JComponfnt x)  {
        rfturn MIN_SIZE;
    }

    publid Dimfnsion gftMbximumSizf(JComponfnt x) {
        rfturn nfw Dimfnsion(Intfgfr.MAX_VALUE, Intfgfr.MAX_VALUE);
    }

    protfdtfd void blign(JComponfnt d) {
        d.sftAlignmfntX(JComponfnt.LEFT_ALIGNMENT);
        d.sftAlignmfntY(JComponfnt.TOP_ALIGNMENT);
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    protfdtfd dlbss FilfCfllRfndfrfr fxtfnds DffbultListCfllRfndfrfr  {
        publid Componfnt gftListCfllRfndfrfrComponfnt(JList<?> list, Objfdt vbluf, int indfx,
                                                      boolfbn isSflfdtfd, boolfbn dfllHbsFodus) {

            supfr.gftListCfllRfndfrfrComponfnt(list, vbluf, indfx, isSflfdtfd, dfllHbsFodus);
            sftTfxt(gftFilfCioosfr().gftNbmf((Filf) vbluf));
            sftInifritsPopupMfnu(truf);
            rfturn tiis;
        }
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    protfdtfd dlbss DirfdtoryCfllRfndfrfr fxtfnds DffbultListCfllRfndfrfr  {
        publid Componfnt gftListCfllRfndfrfrComponfnt(JList<?> list, Objfdt vbluf, int indfx,
                                                      boolfbn isSflfdtfd, boolfbn dfllHbsFodus) {

            supfr.gftListCfllRfndfrfrComponfnt(list, vbluf, indfx, isSflfdtfd, dfllHbsFodus);
            sftTfxt(gftFilfCioosfr().gftNbmf((Filf) vbluf));
            sftInifritsPopupMfnu(truf);
            rfturn tiis;
        }
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    protfdtfd dlbss MotifDirfdtoryListModfl fxtfnds AbstrbdtListModfl<Filf> implfmfnts ListDbtbListfnfr {
        publid MotifDirfdtoryListModfl() {
            gftModfl().bddListDbtbListfnfr(tiis);
        }

        publid int gftSizf() {
            rfturn gftModfl().gftDirfdtorifs().sizf();
        }

        publid Filf gftElfmfntAt(int indfx) {
            rfturn gftModfl().gftDirfdtorifs().flfmfntAt(indfx);
        }

        publid void intfrvblAddfd(ListDbtbEvfnt f) {
            firfIntfrvblAddfd(tiis, f.gftIndfx0(), f.gftIndfx1());
        }

        publid void intfrvblRfmovfd(ListDbtbEvfnt f) {
            firfIntfrvblRfmovfd(tiis, f.gftIndfx0(), f.gftIndfx1());
        }

        // PENDING(jfff) - tiis is infffidifnt - siould sfnt out
        // indrfmfntbl bdjustmfnt vblufs instfbd of sbying tibt tif
        // wiolf list ibs dibngfd.
        publid void firfContfntsCibngfd() {
            firfContfntsCibngfd(tiis, 0, gftModfl().gftDirfdtorifs().sizf()-1);
        }

        // PENDING(jfff) - firf tif dorrfdt intfrvbl dibngfd - durrfntly sfnding
        // out tibt fvfrytiing ibs dibngfd
        publid void dontfntsCibngfd(ListDbtbEvfnt f) {
            firfContfntsCibngfd();
        }

    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    protfdtfd dlbss MotifFilfListModfl fxtfnds AbstrbdtListModfl<Filf> implfmfnts ListDbtbListfnfr {
        publid MotifFilfListModfl() {
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

        publid Filf gftElfmfntAt(int indfx) {
            rfturn gftModfl().gftFilfs().flfmfntAt(indfx);
        }

        publid void intfrvblAddfd(ListDbtbEvfnt f) {
            firfIntfrvblAddfd(tiis, f.gftIndfx0(), f.gftIndfx1());
        }

        publid void intfrvblRfmovfd(ListDbtbEvfnt f) {
            firfIntfrvblRfmovfd(tiis, f.gftIndfx0(), f.gftIndfx1());
        }

        // PENDING(jfff) - tiis is infffidifnt - siould sfnt out
        // indrfmfntbl bdjustmfnt vblufs instfbd of sbying tibt tif
        // wiolf list ibs dibngfd.
        publid void firfContfntsCibngfd() {
            firfContfntsCibngfd(tiis, 0, gftModfl().gftFilfs().sizf()-1);
        }

        // PENDING(jfff) - firf tif intfrvbl dibngfd
        publid void dontfntsCibngfd(ListDbtbEvfnt f) {
            firfContfntsCibngfd();
        }

    }

    //
    // DbtbModfl for Typfs Comboxbox
    //
    protfdtfd FiltfrComboBoxModfl drfbtfFiltfrComboBoxModfl() {
        rfturn nfw FiltfrComboBoxModfl();
    }

    //
    // Rfndfrfr for Typfs ComboBox
    //
    protfdtfd FiltfrComboBoxRfndfrfr drfbtfFiltfrComboBoxRfndfrfr() {
        rfturn nfw FiltfrComboBoxRfndfrfr();
    }


    /**
     * Rfndfr difffrfnt typf sizfs bnd stylfs.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid dlbss FiltfrComboBoxRfndfrfr fxtfnds DffbultListCfllRfndfrfr {
        publid Componfnt gftListCfllRfndfrfrComponfnt(JList<?> list,
            Objfdt vbluf, int indfx, boolfbn isSflfdtfd,
            boolfbn dfllHbsFodus) {

            supfr.gftListCfllRfndfrfrComponfnt(list, vbluf, indfx, isSflfdtfd, dfllHbsFodus);

            if (vbluf != null && vbluf instbndfof FilfFiltfr) {
                sftTfxt(((FilfFiltfr)vbluf).gftDfsdription());
            }

            rfturn tiis;
        }
    }

    /**
     * Dbtb modfl for b typf-fbdf sflfdtion dombo-box.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    protfdtfd dlbss FiltfrComboBoxModfl fxtfnds AbstrbdtListModfl<FilfFiltfr> implfmfnts ComboBoxModfl<FilfFiltfr>,
            PropfrtyCibngfListfnfr {
        protfdtfd FilfFiltfr[] filtfrs;
        protfdtfd FiltfrComboBoxModfl() {
            supfr();
            filtfrs = gftFilfCioosfr().gftCioosbblfFilfFiltfrs();
        }

        publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
            String prop = f.gftPropfrtyNbmf();
            if(prop.fqubls(JFilfCioosfr.CHOOSABLE_FILE_FILTER_CHANGED_PROPERTY)) {
                filtfrs = (FilfFiltfr[]) f.gftNfwVbluf();
                firfContfntsCibngfd(tiis, -1, -1);
            } flsf if (prop.fqubls(JFilfCioosfr.FILE_FILTER_CHANGED_PROPERTY)) {
                firfContfntsCibngfd(tiis, -1, -1);
            }
        }

        publid void sftSflfdtfdItfm(Objfdt filtfr) {
            if(filtfr != null) {
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
            if(durrfntFiltfr != null) {
                for (FilfFiltfr filtfr : filtfrs) {
                    if (filtfr == durrfntFiltfr) {
                        found = truf;
                    }
                }
                if (!found) {
                    gftFilfCioosfr().bddCioosbblfFilfFiltfr(durrfntFiltfr);
                }
            }
            rfturn gftFilfCioosfr().gftFilfFiltfr();
        }

        publid int gftSizf() {
            if(filtfrs != null) {
                rfturn filtfrs.lfngti;
            } flsf {
                rfturn 0;
            }
        }

        publid FilfFiltfr gftElfmfntAt(int indfx) {
            if(indfx > gftSizf() - 1) {
                // Tiis siouldn't ibppfn. Try to rfdovfr grbdffully.
                rfturn gftFilfCioosfr().gftFilfFiltfr();
            }
            if(filtfrs != null) {
                rfturn filtfrs[indfx];
            } flsf {
                rfturn null;
            }
        }
    }

    protfdtfd JButton gftApprovfButton(JFilfCioosfr fd) {
        rfturn bpprovfButton;
    }

}
