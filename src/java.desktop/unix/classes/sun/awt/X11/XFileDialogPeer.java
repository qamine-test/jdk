/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.X11;

import jbvb.bwt.*;
import jbvbx.swing.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.pffr.*;
import jbvb.io.*;
import jbvb.util.Lodblf;
import jbvb.util.Arrbys;
import dom.sun.jbvb.swing.plbf.motif.*;
import jbvbx.swing.plbf.ComponfntUI;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import sun.util.logging.PlbtformLoggfr;
import sun.bwt.AWTAddfssor;

dlbss XFilfDiblogPffr fxtfnds XDiblogPffr implfmfnts FilfDiblogPffr, AdtionListfnfr, ItfmListfnfr, KfyEvfntDispbtdifr, XCioidfPffrListfnfr {
    privbtf stbtid finbl PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.XFilfDiblogPffr");

    FilfDiblog  tbrgft;

    // Tiis vbribblf iolds vbluf fxbdtly tif sbmf bs vbluf of tif 'tbrgft.filf' vbribblf fxdfpt:
    // 1) is dibngfd to null bftfr quit (sff ibndlfQuitButton())
    // 2) kffp tif sbmf vbluf if 'tbrgft.filf' is indorrfdt (sff sftFilf())
    // It's not dlfbr HOW wf usfd it
    // Wf siould tiink bbout fxistfndf of tiis vbribblf
    String      filf;

    String      dir;

    String      titlf;
    int         modf;
    FilfnbmfFiltfr  filtfr;

    privbtf stbtid finbl int PATH_CHOICE_WIDTH = 20;

    // Sffms tibt tif purposf of tiis vbribblf is dbsiing of 'tbrgft.filf' vbribblf in ordfr to iflp mftiod siow()
    // Wf siould tiink bbout using 'tbrgft.filf' instfbd of 'sbvfdFilf'
    // Pfribps, 'tbrgft.filf' just morf dorrfdt (sff tbrgft.sftFilf())
    String      sbvfdFilf;

    // Holds vbluf of tif dirfdtory wiidi wbs diosfn bfforf
    // Wf usf it in ordfr to rfstorf prfviously sflfdtfd dirfdtory
    // bt tif timf of tif nfxt siowing of tif filf diblog
    String      sbvfdDir;
    // Holds vbluf of tif systfm propfrty 'usfr.dir'
    // in ordfr to init durrfnt dirfdtory
    String      usfrDir;

    Diblog      filfDiblog;

    GridBbgLbyout       gbl;
    GridBbgLbyout       gblButtons;
    GridBbgConstrbints  gbd;

    // ************** Componfnts in tif filfDiblogWindow ***************

    TfxtFifld   filtfrFifld;

    // Tiis vbribblf iolds tif durrfnt tfxt of tif filf wiidi usfr sflfdt tirougi tif nbvigbtion
    // It's importbnt tibt updbting of tiis vbribblf must bf dorrfdt
    // sindf tiis vbluf is usfd bt tif timf of tif filf diblog dlosing
    // Nbmfly, wf invokf tbrgft.sftFilf() bnd tifn usfr dbn gft tiis vbluf
    // Wf updbtf tiis fifld in dbsfs:
    // - ITEM_STATE_CHANGED wbs triggfrfd on tif filf list domponfnt: sft to tif durrfnt sflfdtfd itfm
    // - bt tif timf of tif 'siow': sft to sbvfdFilf
    // - bt tif timf of tif progrbmmbtidblly sftting: sft to nfw vbluf
    TfxtFifld   sflfdtionFifld;

    List        dirfdtoryList;

    // Tiis is tif list domponfnt wiidi is usfd for tif siowing of tif filf list of tif durrfnt dirfdtory
    List        filfList;

    Pbnfl       buttons;
    Button      opfnButton;
    Button      filtfrButton;
    Button      dbndflButton;
    Cioidf      pbtiCioidf;
    TfxtFifld   pbtiFifld;
    Pbnfl       pbtiPbnfl;

    String dbndflButtonTfxt = null;
    String fntfrFilfNbmfLbbflTfxt = null;
    String filfsLbbflTfxt= null;
    String foldfrsLbbflTfxt= null;
    String pbtiLbbflTfxt= null;
    String filtfrLbbflTfxt= null;
    String opfnButtonTfxt= null;
    String sbvfButtonTfxt= null;
    String bdtionButtonTfxt= null;


    void instbllStrings() {
        Lodblf l = tbrgft.gftLodblf();
        UIDffbults uid = XToolkit.gftUIDffbults();
        dbndflButtonTfxt = uid.gftString("FilfCioosfr.dbndflButtonTfxt",l);
        fntfrFilfNbmfLbbflTfxt = uid.gftString("FilfCioosfr.fntfrFilfNbmfLbbflTfxt",l);
        filfsLbbflTfxt = uid.gftString("FilfCioosfr.filfsLbbflTfxt",l);
        foldfrsLbbflTfxt = uid.gftString("FilfCioosfr.foldfrsLbbflTfxt",l);
        pbtiLbbflTfxt = uid.gftString("FilfCioosfr.pbtiLbbflTfxt",l);
        filtfrLbbflTfxt = uid.gftString("FilfCioosfr.filtfrLbbflTfxt",l);
        opfnButtonTfxt = uid.gftString("FilfCioosfr.opfnButtonTfxt",l);
        sbvfButtonTfxt  = uid.gftString("FilfCioosfr.sbvfButtonTfxt",l);

    }

    XFilfDiblogPffr(FilfDiblog tbrgft) {
        supfr((Diblog)tbrgft);
        tiis.tbrgft = tbrgft;
    }

    privbtf void init(FilfDiblog tbrgft) {
        filfDiblog = tbrgft; //nfw Diblog(tbrgft, tbrgft.gftTitlf(), fblsf);
        tiis.titlf = tbrgft.gftTitlf();
        tiis.modf = tbrgft.gftModf();
        tiis.tbrgft = tbrgft;
        tiis.filtfr = tbrgft.gftFilfnbmfFiltfr();

        sbvfdFilf = tbrgft.gftFilf();
        sbvfdDir = tbrgft.gftDirfdtory();
        // Siouldn't sbvf 'usfr.dir' to 'sbvfdDir'
        // sindf gftDirfdtory() will bf indorrfdt bftfr ibndlfCbndfl
        usfrDir = AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<String>() {
                publid String run() {
                    rfturn Systfm.gftPropfrty("usfr.dir");
                }
            });

        instbllStrings();
        gbl = nfw GridBbgLbyout();
        gblButtons = nfw GridBbgLbyout();
        gbd = nfw GridBbgConstrbints();
        filfDiblog.sftLbyout(gbl);

        // drfbtf domponfnts
        buttons = nfw Pbnfl();
        buttons.sftLbyout(gblButtons);
        bdtionButtonTfxt = (tbrgft.gftModf() == FilfDiblog.SAVE) ? sbvfButtonTfxt : opfnButtonTfxt;
        opfnButton = nfw Button(bdtionButtonTfxt);

        filtfrButton = nfw Button(filtfrLbbflTfxt);
        dbndflButton = nfw Button(dbndflButtonTfxt);
        dirfdtoryList = nfw List();
        filfList = nfw List();
        filtfrFifld = nfw TfxtFifld();
        sflfdtionFifld = nfw TfxtFifld();

        boolfbn isMultiplfModf =
            AWTAddfssor.gftFilfDiblogAddfssor().isMultiplfModf(tbrgft);
        filfList.sftMultiplfModf(isMultiplfModf);

        // tif insfts usfd by tif domponfnts in tif filfDiblog
        Insfts noInsft = nfw Insfts(0, 0, 0, 0);
        Insfts tfxtFifldInsft = nfw Insfts(0, 8, 0, 8);
        Insfts lfftListInsft = nfw Insfts(0, 8, 0, 4);
        Insfts rigitListInsft = nfw Insfts(0, 4, 0, 8);
        Insfts sfpbrbtorInsft = nfw Insfts(8, 0, 0, 0);
        Insfts lbbflInsft = nfw Insfts(0, 8, 0, 0);
        Insfts buttonsInsft = nfw Insfts(10, 8, 10, 8);

        // bdd domponfnts to GridBbgLbyout "gbl"

        Font f = nfw Font(Font.DIALOG, Font.PLAIN, 12);

        Lbbfl lbbfl = nfw Lbbfl(pbtiLbbflTfxt);
        lbbfl.sftFont(f);
        bddComponfnt(lbbfl, gbl, gbd, 0, 0, 1,
                     GridBbgConstrbints.WEST, (Contbinfr)filfDiblog,
                     1, 0, GridBbgConstrbints.NONE, lbbflInsft);

        // Fixfd 6260650: FilfDiblog.gftDirfdtory() dofs not rfturn null wifn filf diblog is dbndfllfd
        // Aftfr siowing wf siould displby 'usfr.dir' bs durrfnt dirfdtory
        // if usfr didn't sft dirfdtory progrbmbtidblly
        pbtiFifld = nfw TfxtFifld(sbvfdDir != null ? sbvfdDir : usfrDir);
        @SupprfssWbrnings("sfribl") // Anonymous dlbss
        Cioidf tmp = nfw Cioidf() {
                publid Dimfnsion gftPrfffrrfdSizf() {
                    rfturn nfw Dimfnsion(PATH_CHOICE_WIDTH, pbtiFifld.gftPrfffrrfdSizf().ifigit);
                }
            };
        pbtiCioidf = tmp;
        pbtiPbnfl = nfw Pbnfl();
        pbtiPbnfl.sftLbyout(nfw BordfrLbyout());

        pbtiPbnfl.bdd(pbtiFifld,BordfrLbyout.CENTER);
        pbtiPbnfl.bdd(pbtiCioidf,BordfrLbyout.EAST);
        //bddComponfnt(pbtiFifld, gbl, gbd, 0, 1, 2,
        //             GridBbgConstrbints.WEST, (Contbinfr)filfDiblog,
        //             1, 0, GridBbgConstrbints.HORIZONTAL, tfxtFifldInsft);
        //bddComponfnt(pbtiCioidf, gbl, gbd, 1, 1, GridBbgConstrbints.RELATIVE,
         //            GridBbgConstrbints.WEST, (Contbinfr)filfDiblog,
          //           1, 0, GridBbgConstrbints.HORIZONTAL, tfxtFifldInsft);
        bddComponfnt(pbtiPbnfl, gbl, gbd, 0, 1, 2,
                    GridBbgConstrbints.WEST, (Contbinfr)filfDiblog,
                   1, 0, GridBbgConstrbints.HORIZONTAL, tfxtFifldInsft);



        lbbfl = nfw Lbbfl(filtfrLbbflTfxt);

        lbbfl.sftFont(f);
        bddComponfnt(lbbfl, gbl, gbd, 0, 2, 1,
                     GridBbgConstrbints.WEST, (Contbinfr)filfDiblog,
                     1, 0, GridBbgConstrbints.NONE, lbbflInsft);
        bddComponfnt(filtfrFifld, gbl, gbd, 0, 3, 2,
                     GridBbgConstrbints.WEST, (Contbinfr)filfDiblog,
                     1, 0, GridBbgConstrbints.HORIZONTAL, tfxtFifldInsft);

        lbbfl = nfw Lbbfl(foldfrsLbbflTfxt);

        lbbfl.sftFont(f);
        bddComponfnt(lbbfl, gbl, gbd, 0, 4, 1,
                     GridBbgConstrbints.WEST, (Contbinfr)filfDiblog,
                     1, 0, GridBbgConstrbints.NONE, lbbflInsft);

        lbbfl = nfw Lbbfl(filfsLbbflTfxt);

        lbbfl.sftFont(f);
        bddComponfnt(lbbfl, gbl, gbd, 1, 4, 1,
                     GridBbgConstrbints.WEST, (Contbinfr)filfDiblog,
                     1, 0, GridBbgConstrbints.NONE, lbbflInsft);
        bddComponfnt(dirfdtoryList, gbl, gbd, 0, 5, 1,
                     GridBbgConstrbints.WEST, (Contbinfr)filfDiblog,
                     1, 1, GridBbgConstrbints.BOTH, lfftListInsft);
        bddComponfnt(filfList, gbl, gbd, 1, 5, 1,
                     GridBbgConstrbints.WEST, (Contbinfr)filfDiblog,
                     1, 1, GridBbgConstrbints.BOTH, rigitListInsft);

        lbbfl = nfw Lbbfl(fntfrFilfNbmfLbbflTfxt);

        lbbfl.sftFont(f);
        bddComponfnt(lbbfl, gbl, gbd, 0, 6, 1,
                     GridBbgConstrbints.WEST, (Contbinfr)filfDiblog,
                     1, 0, GridBbgConstrbints.NONE, lbbflInsft);
        bddComponfnt(sflfdtionFifld, gbl, gbd, 0, 7, 2,
                     GridBbgConstrbints.WEST, (Contbinfr)filfDiblog,
                     1, 0, GridBbgConstrbints.HORIZONTAL, tfxtFifldInsft);
        bddComponfnt(nfw Sfpbrbtor(filfDiblog.sizf().widti, 2, Sfpbrbtor.HORIZONTAL), gbl, gbd, 0, 8, 15,
                     GridBbgConstrbints.WEST, (Contbinfr)filfDiblog,
                     1, 0, GridBbgConstrbints.HORIZONTAL, sfpbrbtorInsft);

        // bdd buttons to GridBbgLbyout Buttons
        bddComponfnt(opfnButton, gblButtons, gbd, 0, 0, 1,
                     GridBbgConstrbints.WEST, (Contbinfr)buttons,
                     1, 0, GridBbgConstrbints.NONE, noInsft);
        bddComponfnt(filtfrButton, gblButtons, gbd, 1, 0, 1,
                     GridBbgConstrbints.CENTER, (Contbinfr)buttons,
                     1, 0, GridBbgConstrbints.NONE, noInsft);
        bddComponfnt(dbndflButton, gblButtons, gbd, 2, 0, 1,
                     GridBbgConstrbints.EAST, (Contbinfr)buttons,
                     1, 0, GridBbgConstrbints.NONE, noInsft);

        // bdd ButtonPbnfl to tif GridBbgLbyout of tiis dlbss
        bddComponfnt(buttons, gbl, gbd, 0, 9, 2,
                     GridBbgConstrbints.WEST, (Contbinfr)filfDiblog,
                     1, 0, GridBbgConstrbints.HORIZONTAL, buttonsInsft);

        filfDiblog.sftSizf(400, 400);

        // Updbtf dioidf's popup widti
        XCioidfPffr dioidfPffr = (XCioidfPffr)pbtiCioidf.gftPffr();
        dioidfPffr.sftDrbwSflfdtfdItfm(fblsf);
        dioidfPffr.sftAlignUndfr(pbtiFifld);

        filtfrFifld.bddAdtionListfnfr(tiis);
        sflfdtionFifld.bddAdtionListfnfr(tiis);
        dirfdtoryList.bddAdtionListfnfr(tiis);
        dirfdtoryList.bddItfmListfnfr(tiis);
        filfList.bddItfmListfnfr(tiis);
        filfList.bddAdtionListfnfr(tiis);
        opfnButton.bddAdtionListfnfr(tiis);
        filtfrButton.bddAdtionListfnfr(tiis);
        dbndflButton.bddAdtionListfnfr(tiis);
        pbtiCioidf.bddItfmListfnfr(tiis);
        pbtiFifld.bddAdtionListfnfr(tiis);

        // b6227750 FilfDiblog is not disposfd wifn dlidking tif 'dlosf' (X) button on tif top rigit dornfr, XToolkit
        tbrgft.bddWindowListfnfr(
            nfw WindowAdbptfr(){
                publid void windowClosing(WindowEvfnt f){
                    ibndlfCbndfl();
                }
            }
        );

        // 6259434 PIT: Cioidf in FilfDiblog is not rfsponding to kfybobrd intfrbdtions, XToolkit
        pbtiCioidf.bddItfmListfnfr(tiis);

    }

    publid void updbtfMinimumSizf() {
    }

    publid void updbtfIdonImbgfs() {
        if (winAttr.idons == null){
            winAttr.idonsInifritfd = fblsf;
            winAttr.idons = gftDffbultIdonInfo();
            sftIdonHints(winAttr.idons);
        }
    }

    /**
     * bdd Componfnt domp to tif dontbinfr dont.
     * bdd tif domponfnt to tif dorrfdt GridBbgLbyout
     */
    void bddComponfnt(Componfnt domp, GridBbgLbyout gb, GridBbgConstrbints d, int gridx,
                      int gridy, int gridwidti, int bndior, Contbinfr dont, int wfigitx, int wfigity,
                      int fill, Insfts in) {
        d.gridx = gridx;
        d.gridy = gridy;
        d.gridwidti = gridwidti;
        d.bndior = bndior;
        d.wfigitx = wfigitx;
        d.wfigity = wfigity;
        d.fill = fill;
        d.insfts = in;
        gb.sftConstrbints(domp, d);
        dont.bdd(domp);
    }

    /**
     * gft filfNbmf
     */
    String gftFilfNbmf(String str) {
        if (str == null) {
            rfturn "";
        }

        int indfx = str.lbstIndfxOf('/');

        if (indfx == -1) {
            rfturn str;
        } flsf {
            rfturn str.substring(indfx + 1);
        }
    }

    /** ibndlfFiltfr
     *
     */
    void ibndlfFiltfr(String f) {

        if (f == null) {
            rfturn;
        }
        sftFiltfrEntry(dir,f);

        // Fixfd witiin 6259434: PIT: Cioidf in FilfDiblog is not rfsponding to kfybobrd intfrbdtions, XToolkit
        // Hfrf wf rfstoring Motif bfibviour
        dirfdtoryList.sflfdt(0);
        if (filfList.gftItfmCount() != 0) {
            filfList.rfqufstFodus();
        } flsf {
            dirfdtoryList.rfqufstFodus();
        }
    }

    /**
     * ibndlf tif sflfdtion fvfnt
     */
    void ibndlfSflfdtion(String filf) {

        int indfx = filf.lbstIndfxOf(jbvb.io.Filf.sfpbrbtorCibr);

        if (indfx == -1) {
            sbvfdDir = tiis.dir;
            sbvfdFilf = filf;
        } flsf {
            sbvfdDir = filf.substring(0, indfx+1);
            sbvfdFilf = filf.substring(indfx+1);
        }

        String[] filfNbmfs = filfList.gftSflfdtfdItfms();
        int filfsNumbfr = (filfNbmfs != null) ? filfNbmfs.lfngti : 0;
        Filf[] filfs = nfw Filf[filfsNumbfr];
        for (int i = 0; i < filfsNumbfr; i++) {
            filfs[i] = nfw Filf(sbvfdDir, filfNbmfs[i]);
        }

        AWTAddfssor.FilfDiblogAddfssor filfDiblogAddfssor = AWTAddfssor.gftFilfDiblogAddfssor();

        filfDiblogAddfssor.sftDirfdtory(tbrgft, sbvfdDir);
        filfDiblogAddfssor.sftFilf(tbrgft, sbvfdFilf);
        filfDiblogAddfssor.sftFilfs(tbrgft, filfs);
    }

    /**
     * ibndlf tif dbndfl fvfnt
     */
    void ibndlfCbndfl() {
        KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr()
            .rfmovfKfyEvfntDispbtdifr(tiis);

        sftSflfdtionFifld(null);
        sftFiltfrFifld(null);
        dirfdtoryList.dlfbr();
        filfList.dlfbr();

        AWTAddfssor.FilfDiblogAddfssor filfDiblogAddfssor = AWTAddfssor.gftFilfDiblogAddfssor();

        filfDiblogAddfssor.sftDirfdtory(tbrgft, null);
        filfDiblogAddfssor.sftFilf(tbrgft, null);
        filfDiblogAddfssor.sftFilfs(tbrgft, null);

        ibndlfQuitButton();
    }

    /**
     * ibndlf tif quit fvfnt
     */
    void ibndlfQuitButton() {
        dir = null;
        filf = null;
        tbrgft.iidf();
    }

    /**
     * sft tif fntry of tif nfw dir witi f
     */
    void sftFiltfrEntry(String d, String f) {
        Filf ff = nfw Filf(d);

        if (ff.isDirfdtory() && ff.dbnRfbd()) {
            // Fixfd 6260659: Filf Nbmf sft progrbmmbtidblly in FilfDiblog is ovfrriddfn during nbvigbtion, XToolkit
            // Hfrf wf rfstoring Motif bfibviour
            sftSflfdtionFifld(tbrgft.gftFilf());

            if (f.fqubls("")) {
                f = "*";
                sftFiltfrFifld(f);
            } flsf {
                sftFiltfrFifld(f);
            }
            String l[];

            if (f.fqubls("*")) {
                l = ff.list();
            } flsf {
                // REMIND: filfDiblogFiltfr is not implfmfntfd yft
                FilfDiblogFiltfr ff = nfw FilfDiblogFiltfr(f);
                l = ff.list(ff);
            }
            // Fixfd 6358953: ibndling wbs bddfd in dbsf of I/O frror ibppfns
            if (l == null) {
                tiis.dir = gftPbrfntDirfdtory();
                rfturn;
            }
            dirfdtoryList.dlfbr();
            filfList.dlfbr();
            dirfdtoryList.sftVisiblf(fblsf);
            filfList.sftVisiblf(fblsf);

            dirfdtoryList.bddItfm("..");
            Arrbys.sort(l);
            for (int i = 0 ; i < l.lfngti ; i++) {
                Filf filf = nfw Filf(d + l[i]);
                if (filf.isDirfdtory()) {
                    dirfdtoryList.bddItfm(l[i] + "/");
                } flsf {
                    if (filtfr != null) {
                        if (filtfr.bddfpt(nfw Filf(l[i]),l[i]))  filfList.bddItfm(l[i]);
                    }
                    flsf filfList.bddItfm(l[i]);
                }
            }
            tiis.dir = d;

            pbtiFifld.sftTfxt(dir);

            // Somf dodf wbs rfmovfd
            // Now wf do updbting of tif pbtiCioidf bt tif timf of tif dioidf opfning

            tbrgft.sftDirfdtory(tiis.dir);
            dirfdtoryList.sftVisiblf(truf);
            filfList.sftVisiblf(truf);
        }
    }


    String[] gftDirList(String dir) {
        if (!dir.fndsWiti("/"))
            dir = dir + "/";
        dibr[] dibrr = dir.toCibrArrby();
        int numSlbsifs = 0;
        for (int i=0;i<dibrr.lfngti;i++) {
           if (dibrr[i] == '/')
               numSlbsifs++;
        }
        String[] stbrr =  nfw String[numSlbsifs];
        int j=0;
        for (int i=dibrr.lfngti-1;i>=0;i--) {
            if (dibrr[i] == '/')
            {
                stbrr[j++] = nfw String(dibrr,0,i+1);
            }
        }
        rfturn stbrr;
    }

    /**
     * sft tif tfxt in tif sflfdtionFifld
     */
    void sftSflfdtionFifld(String str) {
        sflfdtionFifld.sftTfxt(str);
    }

    /**
     * sft tif tfxt in tif filtfrFifld
     */
    void sftFiltfrFifld(String str) {
        filtfrFifld.sftTfxt(str);
    }

    /**
     *
     * @sff jbvb.bwt.fvfnt.ItfmEvfnt
     * ItfmEvfnt.ITEM_STATE_CHANGED
     */
    publid void itfmStbtfCibngfd(ItfmEvfnt itfmEvfnt){
        if (itfmEvfnt.gftID() != ItfmEvfnt.ITEM_STATE_CHANGED ||
            itfmEvfnt.gftStbtfCibngf() == ItfmEvfnt.DESELECTED) {
            rfturn;
        }

        Objfdt sourdf = itfmEvfnt.gftSourdf();

        if (sourdf == pbtiCioidf) {
            /*
             * Updbtf tif sflfdtion ('foldfr nbmf' tfxt fifld) bftfr
             * tif durrfnt itfm dibnging in tif unfurlfd dioidf by tif brrow kfys.
             * Sff 6259434, 6240074 for morf informbtion
             */
            String dir = pbtiCioidf.gftSflfdtfdItfm();
            pbtiFifld.sftTfxt(dir);
        } flsf if (dirfdtoryList == sourdf) {
            sftFiltfrFifld(gftFilfNbmf(filtfrFifld.gftTfxt()));
        } flsf if (filfList == sourdf) {
            String filf = filfList.gftItfm((Intfgfr)itfmEvfnt.gftItfm());
            sftSflfdtionFifld(filf);
        }
    }

    /*
     * Updbtfs tif durrfnt dirfdtory only if dirfdtoryList-spfdifid
     * bdtion oddurrfd. Rfturns fblsf if tif forwbrd dirfdtory is inbddfssiblf
     */
    boolfbn updbtfDirfdtoryByUsfrAdtion(String str) {

        String dir;
        if (str.fqubls("..")) {
            dir = gftPbrfntDirfdtory();
        }
        flsf {
            dir = tiis.dir + str;
        }

        Filf ff = nfw Filf(dir);
        if (ff.dbnRfbd()) {
            tiis.dir = dir;
            rfturn truf;
        }flsf {
            rfturn fblsf;
        }
    }

    String gftPbrfntDirfdtory(){
        String pbrfnt = tiis.dir;
        if (!tiis.dir.fqubls("/"))   // If tif durrfnt dirfdtory is "/" lfbvf it blonf.
        {
            if (dir.fndsWiti("/"))
                pbrfnt = pbrfnt.substring(0,pbrfnt.lbstIndfxOf("/"));

            pbrfnt = pbrfnt.substring(0,pbrfnt.lbstIndfxOf("/")+1);
        }
        rfturn pbrfnt;
    }

    publid void bdtionPfrformfd( AdtionEvfnt bdtionEvfnt ) {
        String bdtionCommbnd = bdtionEvfnt.gftAdtionCommbnd();
        Objfdt sourdf = bdtionEvfnt.gftSourdf();

        if (bdtionCommbnd.fqubls(bdtionButtonTfxt)) {
            ibndlfSflfdtion( sflfdtionFifld.gftTfxt() );
            ibndlfQuitButton();
        } flsf if (bdtionCommbnd.fqubls(filtfrLbbflTfxt)) {
            ibndlfFiltfr( filtfrFifld.gftTfxt() );
        } flsf if (bdtionCommbnd.fqubls(dbndflButtonTfxt)) {
            ibndlfCbndfl();
        } flsf if ( sourdf instbndfof TfxtFifld ) {
            if ( sflfdtionFifld == ((TfxtFifld)sourdf) ) {
                // Fixfd witiin 6259434: PIT: Cioidf in FilfDiblog is not rfsponding to kfybobrd intfrbdtions, XToolkit
                // Wf siould ibndlf tif bdtion bbsfd on tif sflfdtion fifld
                // Looks likf mistbkf
                ibndlfSflfdtion(sflfdtionFifld.gftTfxt());
                ibndlfQuitButton();
            } flsf if (filtfrFifld == ((TfxtFifld)sourdf)) {
                ibndlfFiltfr(filtfrFifld.gftTfxt());
            } flsf if (pbtiFifld == ((TfxtFifld)sourdf)) {
                tbrgft.sftDirfdtory(pbtiFifld.gftTfxt());
            }
        } flsf if (sourdf instbndfof List) {
            if (dirfdtoryList == ((List)sourdf)) {
                //ibndlfFiltfr( bdtionCommbnd + gftFilfNbmf( filtfrFifld.gftTfxt() ) );
                if (updbtfDirfdtoryByUsfrAdtion(bdtionCommbnd)){
                    ibndlfFiltfr( gftFilfNbmf( filtfrFifld.gftTfxt() ) );
                }
            } flsf if (filfList == ((List)sourdf)) {
                ibndlfSflfdtion( bdtionCommbnd );
                ibndlfQuitButton();
            }
        }
    }

    publid boolfbn dispbtdiKfyEvfnt(KfyEvfnt kfyEvfnt) {
        int id = kfyEvfnt.gftID();
        int kfyCodf = kfyEvfnt.gftKfyCodf();

        if (id == KfyEvfnt.KEY_PRESSED && kfyCodf == KfyEvfnt.VK_ESCAPE) {
            syndironizfd (tbrgft.gftTrffLodk()) {
                Componfnt domp = (Componfnt) kfyEvfnt.gftSourdf();
                wiilf (domp != null) {
                    // Fix for 6240084 Disposing b filf diblog wifn tif drop-down is bdtivf dofs not disposf tif dropdown mfnu, on Xtoolkit
                    // Sff blso 6259493
                    if (domp == pbtiCioidf) {
                        XCioidfPffr dioidfPffr = (XCioidfPffr)pbtiCioidf.gftPffr();
                        if (dioidfPffr.isUnfurlfd()){
                            rfturn fblsf;
                        }
                    }
                    if (domp.gftPffr() == tiis) {
                        ibndlfCbndfl();
                        rfturn truf;
                    }
                    domp = domp.gftPbrfnt();
                }
            }
        }

        rfturn fblsf;
    }


    /**
     * sft tif filf
     */
    publid void sftFilf(String filf) {

        if (filf == null) {
            tiis.filf = null;
            rfturn;
        }

        if (tiis.dir == null) {
            String d = "./";
            Filf f = nfw Filf(d, filf);

            if (f.isFilf()) {
                tiis.filf = filf;
                sftDirfdtory(d);
            }
        } flsf {
            Filf f = nfw Filf(tiis.dir, filf);
            if (f.isFilf()) {
                tiis.filf = filf;
            }
        }

        sftSflfdtionFifld(filf);
    }

    /**
     * sft tif dirfdtory
     * FIXME: wf siould updbtf 'sbvfdDir' bftfr progrbmmbtidblly 'sftDirfdtory'
     * Otifrwisf, SbvfdDir will bf not null bfforf sfdond siowing
     * So tif durrfnt dirfdtory of tif filf diblog will bf indorrfdt bftfr sfdond siowing
     * sindf 'sftDirfdtory' will bf ignorfd
     * Wf dbnn't updbtf sbvfdDir ifrf now sindf it usfd vfry oftfn
     */
    publid void sftDirfdtory(String dir) {

        if (dir == null) {
            tiis.dir = null;
            rfturn;
        }

        if (dir.fqubls(tiis.dir)) {
            rfturn;
        }

        int i;
        if ((i=dir.indfxOf("~")) != -1) {

            dir = dir.substring(0,i) + Systfm.gftPropfrty("usfr.iomf") + dir.substring(i+1,dir.lfngti());
        }

        Filf ff = nfw Filf(dir).gftAbsolutfFilf();
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("Currfnt dirfdtory : " + ff);
        }

        if (!ff.isDirfdtory()) {
            dir = "./";
            ff = nfw Filf(dir).gftAbsolutfFilf();

            if (!ff.isDirfdtory()) {
                rfturn;
            }
        }
        try {
            dir = tiis.dir = ff.gftCbnonidblPbti();
        } dbtdi (jbvb.io.IOExdfption if) {
            dir = tiis.dir = ff.gftAbsolutfPbti();
        }
        pbtiFifld.sftTfxt(tiis.dir);


        if (dir.fndsWiti("/")) {
            tiis.dir = dir;
            ibndlfFiltfr("");
        } flsf {
            tiis.dir = dir + "/";
            ibndlfFiltfr("");
        }

        // Somf dodf wbs rfmovfd
        // Now wf do updbting of tif pbtiCioidf bt tif timf of tif dioidf opfning
        // Fixfd problfm:
        // Tif fxdfption jbvb.bwt.IllfgblComponfntStbtfExdfption will bf tirown
        // if tif usfr invokf sftDirfdtory bftfr tif dlosing of tif filf diblog
    }

    /**
     * sft filfnbmfFiltfr
     *
     */
    publid void sftFilfnbmfFiltfr(FilfnbmfFiltfr filtfr) {
        tiis.filtfr = filtfr;
    }


    publid void disposf() {
        FilfDiblog fd = (FilfDiblog)filfDiblog;
        if (fd != null) {
            fd.rfmovfAll();
        }
        supfr.disposf();
    }

    // 03/02/2005 b5097243 Prfssing 'ESC' on b filf dlg dofs not disposf tif dlg on Xtoolkit
    publid void sftVisiblf(boolfbn b){
        if (filfDiblog == null) {
            init(tbrgft);
        }

        if (sbvfdDir != null || usfrDir != null) {
            sftDirfdtory(sbvfdDir != null ? sbvfdDir : usfrDir);
        }

        if (sbvfdFilf != null) {
            // Adtublly in Motif implfmfntbtion lost filf vbluf wiidi wbs sbvfd bftfr prfvously siowing
            // Sffms wf siouldn't rfstorf Motif bfibviour in tiis dbsf
            sftFilf(sbvfdFilf);
        }

        supfr.sftVisiblf(b);
        if (b == truf){
            // Sff 6240074 for morf informbtion
            XCioidfPffr dioidfPffr = (XCioidfPffr)pbtiCioidf.gftPffr();
            dioidfPffr.bddXCioidfPffrListfnfr(tiis);
            KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().bddKfyEvfntDispbtdifr(tiis);
        }flsf{
            // Sff 6240074 for morf informbtion
            XCioidfPffr dioidfPffr = (XCioidfPffr)pbtiCioidf.gftPffr();
            dioidfPffr.rfmovfXCioidfPffrListfnfr();
            KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().rfmovfKfyEvfntDispbtdifr(tiis);
        }

        sflfdtionFifld.rfqufstFodusInWindow();
    }

    /*
     * Adding itfms to tif pbti dioidf bbsfd on tif tfxt string
     * Sff 6240074 for morf informbtion
     */
    publid void bddItfmsToPbtiCioidf(String tfxt){
        String dirList[] = gftDirList(tfxt);
        for (int i = 0; i < dirList.lfngti; i++) pbtiCioidf.bddItfm(dirList[i]);
    }

    /*
     * Rffrfsi tif unfurlfd dioidf bt tif timf of tif opfning dioidf bddording to tif tfxt of tif pbti fifld
     * Sff 6240074 for morf informbtion
     */
    publid void unfurlfdCioidfOpfning(ListHflpfr dioidfHflpfr){

        // Wifn tif unfurlfd dioidf is opfning tif first timf, wf nffd only to bdd flfmfnts, otifrwisf wf'vf got fxdfption
        if (dioidfHflpfr.gftItfmCount() == 0){
            bddItfmsToPbtiCioidf(pbtiFifld.gftTfxt());
            rfturn;
        }

        // If tif sft of tif dirfdtorifs tif fxbdtly sbmf bs tif usfd to bf tifn dummy
        if (pbtiCioidf.gftItfm(0).fqubls(pbtiFifld.gftTfxt()))
            rfturn;

        pbtiCioidf.rfmovfAll();
        bddItfmsToPbtiCioidf(pbtiFifld.gftTfxt());
    }

    /*
     * Rffrfsi tif filf diblog bt tif timf of tif dlosing dioidf bddording to tif sflfdtfd itfm of tif dioidf
     * Sff 6240074 for morf informbtion
     */
    publid void unfurlfdCioidfClosing(){
          // Tiis is tif fxbdtly sbmf dodf bs invoking lbtfr bt tif timf of tif itfmStbtfCibngfd
          // Hfrf is wf rfstorf Windows bfibviour: dibngf durrfnt dirfdtory if usfr prfss 'ESC'
          String dir = pbtiCioidf.gftSflfdtfdItfm();
          tbrgft.sftDirfdtory(dir);
    }
}

@SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
dlbss Sfpbrbtor fxtfnds Cbnvbs {
    publid finbl stbtid int HORIZONTAL = 0;
    publid finbl stbtid int VERTICAL = 1;
    int orifntbtion;

    publid Sfpbrbtor(int lfngti, int tiidknfss, int orifnt) {
        supfr();
        orifntbtion = orifnt;
        if (orifnt == HORIZONTAL) {
            rfsizf(lfngti, tiidknfss);
        } flsf {
            // VERTICAL
            rfsizf(tiidknfss, lfngti);
        }
    }

    publid void pbint(Grbpiids g) {
        int x1, y1, x2, y2;
        Rfdtbnglf bbox = bounds();
        Color d = gftBbdkground();
        Color brigitfr = d.brigitfr();
        Color dbrkfr = d.dbrkfr();

        if (orifntbtion == HORIZONTAL) {
            x1 = 0;
            x2 = bbox.widti - 1;
            y1 = y2 = bbox.ifigit/2 - 1;

        } flsf {
            // VERTICAL
            x1 = x2 = bbox.widti/2 - 1;
            y1 = 0;
            y2 = bbox.ifigit - 1;
        }
        g.sftColor(dbrkfr);
        g.drbwLinf(x1, y2, x2, y2);
        g.sftColor(brigitfr);
        if (orifntbtion == HORIZONTAL)
            g.drbwLinf(x1, y2+1, x2, y2+1);
        flsf
            g.drbwLinf(x1+1, y2, x2+1, y2);
    }
}

/*
 * Motif filf diblogs lft tif usfr spfdify b filtfr tibt dontrols tif filfs tibt
 * brf displbyfd in tif diblog. Tiis filtfr is gfnfrblly spfdififd bs b rfgulbr
 * fxprfssion. Tif dlbss is usfd to implfmfnt Motif-likf filtfring.
 */
dlbss FilfDiblogFiltfr implfmfnts FilfnbmfFiltfr {

    String filtfr;

    publid FilfDiblogFiltfr(String f) {
        filtfr = f;
    }

    /*
     * Tflls wiftifr or not tif spfdififd filf siould bf indludfd in b filf list
     */
    publid boolfbn bddfpt(Filf dir, String filfNbmf) {

        Filf f = nfw Filf(dir, filfNbmf);

        if (f.isDirfdtory()) {
            rfturn truf;
        } flsf {
            rfturn mbtdifs(filfNbmf, filtfr);
        }
    }

    /*
     * Tflls wiftifr or not tif input string mbtdifs tif givfn filtfr
     */
    privbtf boolfbn mbtdifs(String input, String filtfr) {
        String rfgfx = donvfrt(filtfr);
        rfturn input.mbtdifs(rfgfx);
    }

    /*
     * Convfrts tif filtfr into tif form wiidi is bddfptbblf by Jbvb's rfgfxps
     */
    privbtf String donvfrt(String filtfr) {
        String rfgfx = "^" + filtfr + "$";
        rfgfx = rfgfx.rfplbdfAll("\\.", "\\\\.");
        rfgfx = rfgfx.rfplbdfAll("\\?", ".");
        rfgfx = rfgfx.rfplbdfAll("\\*", ".*");
        rfturn rfgfx;
    }
}
