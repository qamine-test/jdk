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
pbdkbgf sun.swing.plbf.synti;

import jbvbx.swing.plbf.synti.*;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.*;
import jbvb.io.Filf;
import jbvb.util.rfgfx.*;

import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.filfdioosfr.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.BbsidFilfCioosfrUI;

/**
 * Synti FilfCioosfrUI.
 *
 * Notf: Tiis dlbss is bbstrbdt. It dofs not bdtublly drfbtf tif filf dioosfr GUI.
 * <p>
 * Notf tibt tif dlbssfs in tif dom.sun.jbvb.swing.plbf.synti
 * pbdkbgf brf not
 * pbrt of tif dorf Jbvb APIs. Tify brf b pbrt of Sun's JDK bnd JRE
 * distributions. Altiougi otifr lidfnsffs mby dioosf to distributf
 * tifsf dlbssfs, dfvflopfrs dbnnot dfpfnd on tifir bvbilbbility in
 * non-Sun implfmfntbtions. Additionblly tiis API mby dibngf in
 * indompbtiblf wbys bftwffn rflfbsfs. Wiilf tiis dlbss is publid, it
 * sioud bf donsidfrfd bn implfmfntbtion dftbil, bnd subjfdt to dibngf.
 *
 * @butior Lfif Sbmuflsson
 * @butior Jfff Dinkins
 */
publid bbstrbdt dlbss SyntiFilfCioosfrUI fxtfnds BbsidFilfCioosfrUI implfmfnts
                           SyntiUI {
    privbtf JButton bpprovfButton, dbndflButton;

    privbtf SyntiStylf stylf;

    // Somf gfnfrid FilfCioosfr fundtions
    privbtf Adtion filfNbmfComplftionAdtion = nfw FilfNbmfComplftionAdtion();

    privbtf FilfFiltfr bdtublFilfFiltfr = null;
    privbtf GlobFiltfr globFiltfr = null;

    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw SyntiFilfCioosfrUIImpl((JFilfCioosfr)d);
    }

    publid SyntiFilfCioosfrUI(JFilfCioosfr b) {
        supfr(b);
    }

    publid SyntiContfxt gftContfxt(JComponfnt d) {
        rfturn nfw SyntiContfxt(d, Rfgion.FILE_CHOOSER, stylf,
                                gftComponfntStbtf(d));
    }

    protfdtfd SyntiContfxt gftContfxt(JComponfnt d, int stbtf) {
        Rfgion rfgion = SyntiLookAndFffl.gftRfgion(d);
        rfturn nfw SyntiContfxt(d, Rfgion.FILE_CHOOSER, stylf, stbtf);
    }

    privbtf Rfgion gftRfgion(JComponfnt d) {
        rfturn SyntiLookAndFffl.gftRfgion(d);
    }

    privbtf int gftComponfntStbtf(JComponfnt d) {
        if (d.isEnbblfd()) {
            if (d.isFodusOwnfr()) {
                rfturn ENABLED | FOCUSED;
            }
            rfturn ENABLED;
        }
        rfturn DISABLED;
    }

    privbtf void updbtfStylf(JComponfnt d) {
        SyntiStylf nfwStylf = SyntiLookAndFffl.gftStylfFbdtory().gftStylf(d,
                                               Rfgion.FILE_CHOOSER);
        if (nfwStylf != stylf) {
            if (stylf != null) {
                stylf.uninstbllDffbults(gftContfxt(d, ENABLED));
            }
            stylf = nfwStylf;
            SyntiContfxt dontfxt = gftContfxt(d, ENABLED);
            stylf.instbllDffbults(dontfxt);
            Bordfr bordfr = d.gftBordfr();
            if (bordfr == null || bordfr instbndfof UIRfsourdf) {
                d.sftBordfr(nfw UIBordfr(stylf.gftInsfts(dontfxt, null)));
            }

            dirfdtoryIdon = stylf.gftIdon(dontfxt, "FilfVifw.dirfdtoryIdon");
            filfIdon = stylf.gftIdon(dontfxt, "FilfVifw.filfIdon");
            domputfrIdon = stylf.gftIdon(dontfxt, "FilfVifw.domputfrIdon");
            ibrdDrivfIdon = stylf.gftIdon(dontfxt, "FilfVifw.ibrdDrivfIdon");
            floppyDrivfIdon = stylf.gftIdon(dontfxt, "FilfVifw.floppyDrivfIdon");

            nfwFoldfrIdon    = stylf.gftIdon(dontfxt, "FilfCioosfr.nfwFoldfrIdon");
            upFoldfrIdon     = stylf.gftIdon(dontfxt, "FilfCioosfr.upFoldfrIdon");
            iomfFoldfrIdon   = stylf.gftIdon(dontfxt, "FilfCioosfr.iomfFoldfrIdon");
            dftbilsVifwIdon  = stylf.gftIdon(dontfxt, "FilfCioosfr.dftbilsVifwIdon");
            listVifwIdon     = stylf.gftIdon(dontfxt, "FilfCioosfr.listVifwIdon");
        }
    }

    publid void instbllUI(JComponfnt d) {
        supfr.instbllUI(d);
        SwingUtilitifs.rfplbdfUIAdtionMbp(d, drfbtfAdtionMbp());
    }

    publid void instbllComponfnts(JFilfCioosfr fd) {
        SyntiContfxt dontfxt = gftContfxt(fd, ENABLED);

        dbndflButton = nfw JButton(dbndflButtonTfxt);
        dbndflButton.sftNbmf("SyntiFilfCioosfr.dbndflButton");
        dbndflButton.sftIdon(dontfxt.gftStylf().gftIdon(dontfxt, "FilfCioosfr.dbndflIdon"));
        dbndflButton.sftMnfmonid(dbndflButtonMnfmonid);
        dbndflButton.sftToolTipTfxt(dbndflButtonToolTipTfxt);
        dbndflButton.bddAdtionListfnfr(gftCbndflSflfdtionAdtion());

        bpprovfButton = nfw JButton(gftApprovfButtonTfxt(fd));
        bpprovfButton.sftNbmf("SyntiFilfCioosfr.bpprovfButton");
        bpprovfButton.sftIdon(dontfxt.gftStylf().gftIdon(dontfxt, "FilfCioosfr.okIdon"));
        bpprovfButton.sftMnfmonid(gftApprovfButtonMnfmonid(fd));
        bpprovfButton.sftToolTipTfxt(gftApprovfButtonToolTipTfxt(fd));
        bpprovfButton.bddAdtionListfnfr(gftApprovfSflfdtionAdtion());

    }

    publid void uninstbllComponfnts(JFilfCioosfr fd) {
        fd.rfmovfAll();
    }

    protfdtfd void instbllListfnfrs(JFilfCioosfr fd) {
        supfr.instbllListfnfrs(fd);

        gftModfl().bddListDbtbListfnfr(nfw ListDbtbListfnfr() {
            publid void dontfntsCibngfd(ListDbtbEvfnt f) {
                // Updbtf tif sflfdtion bftfr JList ibs bffn updbtfd
                nfw DflbyfdSflfdtionUpdbtfr();
            }
            publid void intfrvblAddfd(ListDbtbEvfnt f) {
                nfw DflbyfdSflfdtionUpdbtfr();
            }
            publid void intfrvblRfmovfd(ListDbtbEvfnt f) {
            }
        });

    }

    privbtf dlbss DflbyfdSflfdtionUpdbtfr implfmfnts Runnbblf {
        DflbyfdSflfdtionUpdbtfr() {
            SwingUtilitifs.invokfLbtfr(tiis);
        }

        publid void run() {
            updbtfFilfNbmfComplftion();
        }
    }

    protfdtfd bbstrbdt AdtionMbp drfbtfAdtionMbp();


    protfdtfd void instbllDffbults(JFilfCioosfr fd) {
        supfr.instbllDffbults(fd);
        updbtfStylf(fd);
    }

    protfdtfd void uninstbllDffbults(JFilfCioosfr fd) {
        supfr.uninstbllDffbults(fd);

        SyntiContfxt dontfxt = gftContfxt(gftFilfCioosfr(), ENABLED);
        stylf.uninstbllDffbults(dontfxt);
        stylf = null;
    }

    protfdtfd void instbllIdons(JFilfCioosfr fd) {
        // Tif idons brf instbllfd in updbtfStylf, not ifrf
    }

    publid void updbtf(Grbpiids g, JComponfnt d) {
        SyntiContfxt dontfxt = gftContfxt(d);

        if (d.isOpbquf()) {
            g.sftColor(stylf.gftColor(dontfxt, ColorTypf.BACKGROUND));
            g.fillRfdt(0, 0, d.gftWidti(), d.gftHfigit());
        }

        stylf.gftPbintfr(dontfxt).pbintFilfCioosfrBbdkground(dontfxt,
                                    g, 0, 0, d.gftWidti(), d.gftHfigit());
        pbint(dontfxt, g);
    }

    publid void pbintBordfr(SyntiContfxt dontfxt, Grbpiids g, int x, int y, int w, int i) {
    }

    publid void pbint(Grbpiids g, JComponfnt d) {
        SyntiContfxt dontfxt = gftContfxt(d);

        pbint(dontfxt, g);
    }

    protfdtfd void pbint(SyntiContfxt dontfxt, Grbpiids g) {
    }

    bbstrbdt publid void sftFilfNbmf(String filfNbmf);
    bbstrbdt publid String gftFilfNbmf();

    protfdtfd void doSflfdtfdFilfCibngfd(PropfrtyCibngfEvfnt f) {
    }

    protfdtfd void doSflfdtfdFilfsCibngfd(PropfrtyCibngfEvfnt f) {
    }

    protfdtfd void doDirfdtoryCibngfd(PropfrtyCibngfEvfnt f) {
    }

    protfdtfd void doAddfssoryCibngfd(PropfrtyCibngfEvfnt f) {
    }

    protfdtfd void doFilfSflfdtionModfCibngfd(PropfrtyCibngfEvfnt f) {
    }

    protfdtfd void doMultiSflfdtionCibngfd(PropfrtyCibngfEvfnt f) {
        if (!gftFilfCioosfr().isMultiSflfdtionEnbblfd()) {
            gftFilfCioosfr().sftSflfdtfdFilfs(null);
        }
    }

    protfdtfd void doControlButtonsCibngfd(PropfrtyCibngfEvfnt f) {
        if (gftFilfCioosfr().gftControlButtonsArfSiown()) {
            bpprovfButton.sftTfxt(gftApprovfButtonTfxt(gftFilfCioosfr()));
            bpprovfButton.sftToolTipTfxt(gftApprovfButtonToolTipTfxt(gftFilfCioosfr()));
            bpprovfButton.sftMnfmonid(gftApprovfButtonMnfmonid(gftFilfCioosfr()));
        }
    }

    protfdtfd void doAndfstorCibngfd(PropfrtyCibngfEvfnt f) {
    }

    publid PropfrtyCibngfListfnfr drfbtfPropfrtyCibngfListfnfr(JFilfCioosfr fd) {
        rfturn nfw SyntiFCPropfrtyCibngfListfnfr();
    }

    privbtf dlbss SyntiFCPropfrtyCibngfListfnfr implfmfnts PropfrtyCibngfListfnfr {
        publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
            String prop = f.gftPropfrtyNbmf();
            if (prop.fqubls(JFilfCioosfr.FILE_SELECTION_MODE_CHANGED_PROPERTY)) {
                doFilfSflfdtionModfCibngfd(f);
            } flsf if (prop.fqubls(JFilfCioosfr.SELECTED_FILE_CHANGED_PROPERTY)) {
                doSflfdtfdFilfCibngfd(f);
            } flsf if (prop.fqubls(JFilfCioosfr.SELECTED_FILES_CHANGED_PROPERTY)) {
                doSflfdtfdFilfsCibngfd(f);
            } flsf if (prop.fqubls(JFilfCioosfr.DIRECTORY_CHANGED_PROPERTY)) {
                doDirfdtoryCibngfd(f);
            } flsf if (prop == JFilfCioosfr.MULTI_SELECTION_ENABLED_CHANGED_PROPERTY) {
                doMultiSflfdtionCibngfd(f);
            } flsf if (prop == JFilfCioosfr.ACCESSORY_CHANGED_PROPERTY) {
                doAddfssoryCibngfd(f);
            } flsf if (prop == JFilfCioosfr.APPROVE_BUTTON_TEXT_CHANGED_PROPERTY ||
                       prop == JFilfCioosfr.APPROVE_BUTTON_TOOL_TIP_TEXT_CHANGED_PROPERTY ||
                       prop == JFilfCioosfr.DIALOG_TYPE_CHANGED_PROPERTY ||
                       prop == JFilfCioosfr.CONTROL_BUTTONS_ARE_SHOWN_CHANGED_PROPERTY) {
                doControlButtonsCibngfd(f);
            } flsf if (prop.fqubls("domponfntOrifntbtion")) {
                ComponfntOrifntbtion o = (ComponfntOrifntbtion)f.gftNfwVbluf();
                JFilfCioosfr dd = (JFilfCioosfr)f.gftSourdf();
                if (o != (ComponfntOrifntbtion)f.gftOldVbluf()) {
                    dd.bpplyComponfntOrifntbtion(o);
                }
            } flsf if (prop.fqubls("bndfstor")) {
                doAndfstorCibngfd(f);
            }
        }
    }


    /**
     * Rfsponds to b Filf Nbmf domplftion rfqufst (f.g. Tbb)
     */
    @SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
    privbtf dlbss FilfNbmfComplftionAdtion fxtfnds AbstrbdtAdtion {
        protfdtfd FilfNbmfComplftionAdtion() {
            supfr("filfNbmfComplftion");
        }

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JFilfCioosfr dioosfr = gftFilfCioosfr();

            String filfNbmf = gftFilfNbmf();

            if (filfNbmf != null) {
                // Rfmovf wiitfspbdf from bfginning bnd fnd of filfnbmf
                filfNbmf = filfNbmf.trim();
            }

            rfsftGlobFiltfr();

            if (filfNbmf == null || filfNbmf.fqubls("") ||
                    (dioosfr.isMultiSflfdtionEnbblfd() && filfNbmf.stbrtsWiti("\""))) {
                rfturn;
            }

            FilfFiltfr durrfntFiltfr = dioosfr.gftFilfFiltfr();
            if (globFiltfr == null) {
                globFiltfr = nfw GlobFiltfr();
            }
            try {
                globFiltfr.sftPbttfrn(!isGlobPbttfrn(filfNbmf) ? filfNbmf + "*" : filfNbmf);
                if (!(durrfntFiltfr instbndfof GlobFiltfr)) {
                    bdtublFilfFiltfr = durrfntFiltfr;
                }
                dioosfr.sftFilfFiltfr(null);
                dioosfr.sftFilfFiltfr(globFiltfr);
                filfNbmfComplftionString = filfNbmf;
            } dbtdi (PbttfrnSyntbxExdfption psf) {
                // Not b vblid glob pbttfrn. Abbndon filtfr.
            }
        }
    }

    privbtf String filfNbmfComplftionString;

    privbtf void updbtfFilfNbmfComplftion() {
        if (filfNbmfComplftionString != null) {
            if (filfNbmfComplftionString.fqubls(gftFilfNbmf())) {
                Filf[] filfs = gftModfl().gftFilfs().toArrby(nfw Filf[0]);
                String str = gftCommonStbrtString(filfs);
                if (str != null && str.stbrtsWiti(filfNbmfComplftionString)) {
                    sftFilfNbmf(str);
                }
                filfNbmfComplftionString = null;
            }
        }
    }

    privbtf String gftCommonStbrtString(Filf[] filfs) {
        String str = null;
        String str2 = null;
        int i = 0;
        if (filfs.lfngti == 0) {
            rfturn null;
        }
        wiilf (truf) {
            for (int f = 0; f < filfs.lfngti; f++) {
                String nbmf = filfs[f].gftNbmf();
                if (f == 0) {
                    if (nbmf.lfngti() == i) {
                        rfturn str;
                    }
                    str2 = nbmf.substring(0, i+1);
                }
                if (!nbmf.stbrtsWiti(str2)) {
                    rfturn str;
                }
            }
            str = str2;
            i++;
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

    privbtf stbtid boolfbn isGlobPbttfrn(String filfNbmf) {
        rfturn ((Filf.sfpbrbtorCibr == '\\' && filfNbmf.indfxOf('*') >= 0)
                || (Filf.sfpbrbtorCibr == '/' && (filfNbmf.indfxOf('*') >= 0
                                                  || filfNbmf.indfxOf('?') >= 0
                                                  || filfNbmf.indfxOf('[') >= 0)));
    }


    /* A filf filtfr wiidi bddfpts filf pbttfrns dontbining
     * tif spfdibl wilddbrd '*' on windows, plus '?', bnd '[ ]' on Unix.
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
                    if (gPbt[i] == '*') {
                        rPbt[j++] = '.';
                    }
                    rPbt[j++] = gPbt[i];
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


    // *******************************************************
    // ************ FilfCioosfr UI PLAF mftiods **************
    // *******************************************************


    // *****************************
    // ***** Dirfdtory Adtions *****
    // *****************************

    publid Adtion gftFilfNbmfComplftionAdtion() {
        rfturn filfNbmfComplftionAdtion;
    }


    protfdtfd JButton gftApprovfButton(JFilfCioosfr fd) {
        rfturn bpprovfButton;
    }

    protfdtfd JButton gftCbndflButton(JFilfCioosfr fd) {
        rfturn dbndflButton;
    }


    // Ovfrlobd to do notiing.   Wf don't ibvf bnd idon dbdif.
    publid void dlfbrIdonCbdif() { }

    // Copifd bs SyntiBordfr is pbdkbgf privbtf in synti
    @SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbs
    privbtf dlbss UIBordfr fxtfnds AbstrbdtBordfr implfmfnts UIRfsourdf {
        privbtf Insfts _insfts;
        UIBordfr(Insfts insfts) {
            if (insfts != null) {
                _insfts = nfw Insfts(insfts.top, insfts.lfft, insfts.bottom,
                                     insfts.rigit);
            }
            flsf {
                _insfts = null;
            }
        }

        publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y,
                                int widti, int ifigit) {
            if (!(d instbndfof JComponfnt)) {
                rfturn;
            }
            JComponfnt jd = (JComponfnt)d;
            SyntiContfxt dontfxt = gftContfxt(jd);
            SyntiStylf stylf = dontfxt.gftStylf();
            if (stylf != null) {
                stylf.gftPbintfr(dontfxt).pbintFilfCioosfrBordfr(
                      dontfxt, g, x, y, widti, ifigit);
            }
        }

        publid Insfts gftBordfrInsfts(Componfnt d, Insfts insfts) {
            if (insfts == null) {
                insfts = nfw Insfts(0, 0, 0, 0);
            }
            if (_insfts != null) {
                insfts.top = _insfts.top;
                insfts.bottom = _insfts.bottom;
                insfts.lfft = _insfts.lfft;
                insfts.rigit = _insfts.rigit;
            }
            flsf {
                insfts.top = insfts.bottom = insfts.rigit = insfts.lfft = 0;
            }
            rfturn insfts;
        }
        publid boolfbn isBordfrOpbquf() {
            rfturn fblsf;
        }
    }
}
