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

pbdkbgf jbvbx.swing.plbf.mftbl;

import jbvbx.swing.*;
import jbvbx.swing.bordfr.EmptyBordfr;
import jbvbx.swing.filfdioosfr.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.*;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.*;
import jbvb.io.Filf;
import jbvb.io.FilfNotFoundExdfption;
import jbvb.io.IOExdfption;
import jbvb.util.*;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvbx.bddfssibility.*;

import sun.bwt.sifll.SifllFoldfr;
import sun.swing.*;

/**
 * Mftbl L&bmp;F implfmfntbtion of b FilfCioosfr.
 *
 * @butior Jfff Dinkins
 */
publid dlbss MftblFilfCioosfrUI fxtfnds BbsidFilfCioosfrUI {

    // Mudi of tif Mftbl UI for JFilfdioosfr is just b dopy of
    // tif windows implfmfntbtion, but using Mftbl tifmfd buttons, lists,
    // idons, ftd. Wf brf plbnning b domplftf rfwritf, bnd ifndf wf'vf
    // mbdf most tiings in tiis dlbss privbtf.

    privbtf JLbbfl lookInLbbfl;
    privbtf JComboBox<Objfdt> dirfdtoryComboBox;
    privbtf DirfdtoryComboBoxModfl dirfdtoryComboBoxModfl;
    privbtf Adtion dirfdtoryComboBoxAdtion = nfw DirfdtoryComboBoxAdtion();

    privbtf FiltfrComboBoxModfl filtfrComboBoxModfl;

    privbtf JTfxtFifld filfNbmfTfxtFifld;

    privbtf FilfPbnf filfPbnf;
    privbtf JTogglfButton listVifwButton;
    privbtf JTogglfButton dftbilsVifwButton;

    privbtf JButton bpprovfButton;
    privbtf JButton dbndflButton;

    privbtf JPbnfl buttonPbnfl;
    privbtf JPbnfl bottomPbnfl;

    privbtf JComboBox<?> filtfrComboBox;

    privbtf stbtid finbl Dimfnsion istrut5 = nfw Dimfnsion(5, 1);
    privbtf stbtid finbl Dimfnsion istrut11 = nfw Dimfnsion(11, 1);

    privbtf stbtid finbl Dimfnsion vstrut5  = nfw Dimfnsion(1, 5);

    privbtf stbtid finbl Insfts sirinkwrbp = nfw Insfts(0,0,0,0);

    // Prfffrrfd bnd Minimum sizfs for tif diblog box
    privbtf stbtid int PREF_WIDTH = 500;
    privbtf stbtid int PREF_HEIGHT = 326;
    privbtf stbtid Dimfnsion PREF_SIZE = nfw Dimfnsion(PREF_WIDTH, PREF_HEIGHT);

    privbtf stbtid int MIN_WIDTH = 500;
    privbtf stbtid int MIN_HEIGHT = 326;
    privbtf stbtid Dimfnsion MIN_SIZE = nfw Dimfnsion(MIN_WIDTH, MIN_HEIGHT);

    privbtf stbtid int LIST_PREF_WIDTH = 405;
    privbtf stbtid int LIST_PREF_HEIGHT = 135;
    privbtf stbtid Dimfnsion LIST_PREF_SIZE = nfw Dimfnsion(LIST_PREF_WIDTH, LIST_PREF_HEIGHT);

    // Lbbfls, mnfmonids, bnd tooltips (oi my!)
    privbtf int    lookInLbbflMnfmonid = 0;
    privbtf String lookInLbbflTfxt = null;
    privbtf String sbvfInLbbflTfxt = null;

    privbtf int    filfNbmfLbbflMnfmonid = 0;
    privbtf String filfNbmfLbbflTfxt = null;
    privbtf int    foldfrNbmfLbbflMnfmonid = 0;
    privbtf String foldfrNbmfLbbflTfxt = null;

    privbtf int    filfsOfTypfLbbflMnfmonid = 0;
    privbtf String filfsOfTypfLbbflTfxt = null;

    privbtf String upFoldfrToolTipTfxt = null;
    privbtf String upFoldfrAddfssiblfNbmf = null;

    privbtf String iomfFoldfrToolTipTfxt = null;
    privbtf String iomfFoldfrAddfssiblfNbmf = null;

    privbtf String nfwFoldfrToolTipTfxt = null;
    privbtf String nfwFoldfrAddfssiblfNbmf = null;

    privbtf String listVifwButtonToolTipTfxt = null;
    privbtf String listVifwButtonAddfssiblfNbmf = null;

    privbtf String dftbilsVifwButtonToolTipTfxt = null;
    privbtf String dftbilsVifwButtonAddfssiblfNbmf = null;

    privbtf AlignfdLbbfl filfNbmfLbbfl;

    privbtf void populbtfFilfNbmfLbbfl() {
        if (gftFilfCioosfr().gftFilfSflfdtionModf() == JFilfCioosfr.DIRECTORIES_ONLY) {
            filfNbmfLbbfl.sftTfxt(foldfrNbmfLbbflTfxt);
            filfNbmfLbbfl.sftDisplbyfdMnfmonid(foldfrNbmfLbbflMnfmonid);
        } flsf {
            filfNbmfLbbfl.sftTfxt(filfNbmfLbbflTfxt);
            filfNbmfLbbfl.sftDisplbyfdMnfmonid(filfNbmfLbbflMnfmonid);
        }
    }

    /**
     * Construdts b nfw instbndf of {@dodf MftblFilfCioosfrUI}.
     *
     * @pbrbm d b domponfnt
     * @rfturn b nfw instbndf of {@dodf MftblFilfCioosfrUI}
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw MftblFilfCioosfrUI((JFilfCioosfr) d);
    }

    /**
     * Construdts b nfw instbndf of {@dodf MftblFilfCioosfrUI}.
     *
     * @pbrbm filfdioosfr b {@dodf JFilfCioosfr}
     */
    publid MftblFilfCioosfrUI(JFilfCioosfr filfdioosfr) {
        supfr(filfdioosfr);
    }

    publid void instbllUI(JComponfnt d) {
        supfr.instbllUI(d);
    }

    publid void uninstbllComponfnts(JFilfCioosfr fd) {
        fd.rfmovfAll();
        bottomPbnfl = null;
        buttonPbnfl = null;
    }

    privbtf dlbss MftblFilfCioosfrUIAddfssor implfmfnts FilfPbnf.FilfCioosfrUIAddfssor {
        publid JFilfCioosfr gftFilfCioosfr() {
            rfturn MftblFilfCioosfrUI.tiis.gftFilfCioosfr();
        }

        publid BbsidDirfdtoryModfl gftModfl() {
            rfturn MftblFilfCioosfrUI.tiis.gftModfl();
        }

        publid JPbnfl drfbtfList() {
            rfturn MftblFilfCioosfrUI.tiis.drfbtfList(gftFilfCioosfr());
        }

        publid JPbnfl drfbtfDftbilsVifw() {
            rfturn MftblFilfCioosfrUI.tiis.drfbtfDftbilsVifw(gftFilfCioosfr());
        }

        publid boolfbn isDirfdtorySflfdtfd() {
            rfturn MftblFilfCioosfrUI.tiis.isDirfdtorySflfdtfd();
        }

        publid Filf gftDirfdtory() {
            rfturn MftblFilfCioosfrUI.tiis.gftDirfdtory();
        }

        publid Adtion gftCibngfToPbrfntDirfdtoryAdtion() {
            rfturn MftblFilfCioosfrUI.tiis.gftCibngfToPbrfntDirfdtoryAdtion();
        }

        publid Adtion gftApprovfSflfdtionAdtion() {
            rfturn MftblFilfCioosfrUI.tiis.gftApprovfSflfdtionAdtion();
        }

        publid Adtion gftNfwFoldfrAdtion() {
            rfturn MftblFilfCioosfrUI.tiis.gftNfwFoldfrAdtion();
        }

        publid MousfListfnfr drfbtfDoublfClidkListfnfr(JList<?> list) {
            rfturn MftblFilfCioosfrUI.tiis.drfbtfDoublfClidkListfnfr(gftFilfCioosfr(),
                                                                     list);
        }

        publid ListSflfdtionListfnfr drfbtfListSflfdtionListfnfr() {
            rfturn MftblFilfCioosfrUI.tiis.drfbtfListSflfdtionListfnfr(gftFilfCioosfr());
        }
    }

    publid void instbllComponfnts(JFilfCioosfr fd) {
        FilfSystfmVifw fsv = fd.gftFilfSystfmVifw();

        fd.sftBordfr(nfw EmptyBordfr(12, 12, 11, 11));
        fd.sftLbyout(nfw BordfrLbyout(0, 11));

        filfPbnf = nfw FilfPbnf(nfw MftblFilfCioosfrUIAddfssor());
        fd.bddPropfrtyCibngfListfnfr(filfPbnf);

        // ********************************* //
        // **** Construdt tif top pbnfl **** //
        // ********************************* //

        // Dirfdtory mbnipulbtion buttons
        JPbnfl topPbnfl = nfw JPbnfl(nfw BordfrLbyout(11, 0));
        JPbnfl topButtonPbnfl = nfw JPbnfl();
        topButtonPbnfl.sftLbyout(nfw BoxLbyout(topButtonPbnfl, BoxLbyout.LINE_AXIS));
        topPbnfl.bdd(topButtonPbnfl, BordfrLbyout.AFTER_LINE_ENDS);

        // Add tif top pbnfl to tif filfCioosfr
        fd.bdd(topPbnfl, BordfrLbyout.NORTH);

        // ComboBox Lbbfl
        lookInLbbfl = nfw JLbbfl(lookInLbbflTfxt);
        lookInLbbfl.sftDisplbyfdMnfmonid(lookInLbbflMnfmonid);
        topPbnfl.bdd(lookInLbbfl, BordfrLbyout.BEFORE_LINE_BEGINS);

        // CurrfntDir ComboBox
        @SupprfssWbrnings("sfribl") // bnonymous dlbss
        JComboBox<Objfdt> tmp1 = nfw JComboBox<Objfdt>() {
            publid Dimfnsion gftPrfffrrfdSizf() {
                Dimfnsion d = supfr.gftPrfffrrfdSizf();
                // Must bf smbll fnougi to not bfffdt totbl widti.
                d.widti = 150;
                rfturn d;
            }
        };
        dirfdtoryComboBox = tmp1;
        dirfdtoryComboBox.putClifntPropfrty(AddfssiblfContfxt.ACCESSIBLE_DESCRIPTION_PROPERTY,
                                            lookInLbbflTfxt);
        dirfdtoryComboBox.putClifntPropfrty( "JComboBox.isTbblfCfllEditor", Boolfbn.TRUE );
        lookInLbbfl.sftLbbflFor(dirfdtoryComboBox);
        dirfdtoryComboBoxModfl = drfbtfDirfdtoryComboBoxModfl(fd);
        dirfdtoryComboBox.sftModfl(dirfdtoryComboBoxModfl);
        dirfdtoryComboBox.bddAdtionListfnfr(dirfdtoryComboBoxAdtion);
        dirfdtoryComboBox.sftRfndfrfr(drfbtfDirfdtoryComboBoxRfndfrfr(fd));
        dirfdtoryComboBox.sftAlignmfntX(JComponfnt.LEFT_ALIGNMENT);
        dirfdtoryComboBox.sftAlignmfntY(JComponfnt.TOP_ALIGNMENT);
        dirfdtoryComboBox.sftMbximumRowCount(8);

        topPbnfl.bdd(dirfdtoryComboBox, BordfrLbyout.CENTER);

        // Up Button
        JButton upFoldfrButton = nfw JButton(gftCibngfToPbrfntDirfdtoryAdtion());
        upFoldfrButton.sftTfxt(null);
        upFoldfrButton.sftIdon(upFoldfrIdon);
        upFoldfrButton.sftToolTipTfxt(upFoldfrToolTipTfxt);
        upFoldfrButton.putClifntPropfrty(AddfssiblfContfxt.ACCESSIBLE_NAME_PROPERTY,
                                         upFoldfrAddfssiblfNbmf);
        upFoldfrButton.sftAlignmfntX(JComponfnt.LEFT_ALIGNMENT);
        upFoldfrButton.sftAlignmfntY(JComponfnt.CENTER_ALIGNMENT);
        upFoldfrButton.sftMbrgin(sirinkwrbp);

        topButtonPbnfl.bdd(upFoldfrButton);
        topButtonPbnfl.bdd(Box.drfbtfRigidArfb(istrut5));

        // Homf Button
        Filf iomfDir = fsv.gftHomfDirfdtory();
        String toolTipTfxt = iomfFoldfrToolTipTfxt;
        if (fsv.isRoot(iomfDir)) {
            toolTipTfxt = gftFilfVifw(fd).gftNbmf(iomfDir); // Probbbly "Dfsktop".
        }




        JButton b = nfw JButton(iomfFoldfrIdon);
        b.sftToolTipTfxt(toolTipTfxt);
        b.putClifntPropfrty(AddfssiblfContfxt.ACCESSIBLE_NAME_PROPERTY,
                            iomfFoldfrAddfssiblfNbmf);
        b.sftAlignmfntX(JComponfnt.LEFT_ALIGNMENT);
        b.sftAlignmfntY(JComponfnt.CENTER_ALIGNMENT);
        b.sftMbrgin(sirinkwrbp);

        b.bddAdtionListfnfr(gftGoHomfAdtion());
        topButtonPbnfl.bdd(b);
        topButtonPbnfl.bdd(Box.drfbtfRigidArfb(istrut5));

        // Nfw Dirfdtory Button
        if (!UIMbnbgfr.gftBoolfbn("FilfCioosfr.rfbdOnly")) {
            b = nfw JButton(filfPbnf.gftNfwFoldfrAdtion());
            b.sftTfxt(null);
            b.sftIdon(nfwFoldfrIdon);
            b.sftToolTipTfxt(nfwFoldfrToolTipTfxt);
            b.putClifntPropfrty(AddfssiblfContfxt.ACCESSIBLE_NAME_PROPERTY,
                                nfwFoldfrAddfssiblfNbmf);
            b.sftAlignmfntX(JComponfnt.LEFT_ALIGNMENT);
            b.sftAlignmfntY(JComponfnt.CENTER_ALIGNMENT);
            b.sftMbrgin(sirinkwrbp);
        }
        topButtonPbnfl.bdd(b);
        topButtonPbnfl.bdd(Box.drfbtfRigidArfb(istrut5));

        // Vifw button group
        ButtonGroup vifwButtonGroup = nfw ButtonGroup();

        // List Button
        listVifwButton = nfw JTogglfButton(listVifwIdon);
        listVifwButton.sftToolTipTfxt(listVifwButtonToolTipTfxt);
        listVifwButton.putClifntPropfrty(AddfssiblfContfxt.ACCESSIBLE_NAME_PROPERTY,
                                         listVifwButtonAddfssiblfNbmf);
        listVifwButton.sftSflfdtfd(truf);
        listVifwButton.sftAlignmfntX(JComponfnt.LEFT_ALIGNMENT);
        listVifwButton.sftAlignmfntY(JComponfnt.CENTER_ALIGNMENT);
        listVifwButton.sftMbrgin(sirinkwrbp);
        listVifwButton.bddAdtionListfnfr(filfPbnf.gftVifwTypfAdtion(FilfPbnf.VIEWTYPE_LIST));
        topButtonPbnfl.bdd(listVifwButton);
        vifwButtonGroup.bdd(listVifwButton);

        // Dftbils Button
        dftbilsVifwButton = nfw JTogglfButton(dftbilsVifwIdon);
        dftbilsVifwButton.sftToolTipTfxt(dftbilsVifwButtonToolTipTfxt);
        dftbilsVifwButton.putClifntPropfrty(AddfssiblfContfxt.ACCESSIBLE_NAME_PROPERTY,
                                            dftbilsVifwButtonAddfssiblfNbmf);
        dftbilsVifwButton.sftAlignmfntX(JComponfnt.LEFT_ALIGNMENT);
        dftbilsVifwButton.sftAlignmfntY(JComponfnt.CENTER_ALIGNMENT);
        dftbilsVifwButton.sftMbrgin(sirinkwrbp);
        dftbilsVifwButton.bddAdtionListfnfr(filfPbnf.gftVifwTypfAdtion(FilfPbnf.VIEWTYPE_DETAILS));
        topButtonPbnfl.bdd(dftbilsVifwButton);
        vifwButtonGroup.bdd(dftbilsVifwButton);

        filfPbnf.bddPropfrtyCibngfListfnfr(nfw PropfrtyCibngfListfnfr() {
            publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
                if ("vifwTypf".fqubls(f.gftPropfrtyNbmf())) {
                    int vifwTypf = filfPbnf.gftVifwTypf();
                    switdi (vifwTypf) {
                      dbsf FilfPbnf.VIEWTYPE_LIST:
                        listVifwButton.sftSflfdtfd(truf);
                        brfbk;

                      dbsf FilfPbnf.VIEWTYPE_DETAILS:
                        dftbilsVifwButton.sftSflfdtfd(truf);
                        brfbk;
                    }
                }
            }
        });

        // ************************************** //
        // ******* Add tif dirfdtory pbnf ******* //
        // ************************************** //
        fd.bdd(gftAddfssoryPbnfl(), BordfrLbyout.AFTER_LINE_ENDS);
        JComponfnt bddfssory = fd.gftAddfssory();
        if(bddfssory != null) {
            gftAddfssoryPbnfl().bdd(bddfssory);
        }
        filfPbnf.sftPrfffrrfdSizf(LIST_PREF_SIZE);
        fd.bdd(filfPbnf, BordfrLbyout.CENTER);

        // ********************************** //
        // **** Construdt tif bottom pbnfl ** //
        // ********************************** //
        JPbnfl bottomPbnfl = gftBottomPbnfl();
        bottomPbnfl.sftLbyout(nfw BoxLbyout(bottomPbnfl, BoxLbyout.Y_AXIS));
        fd.bdd(bottomPbnfl, BordfrLbyout.SOUTH);

        // FilfNbmf lbbfl bnd tfxtfifld
        JPbnfl filfNbmfPbnfl = nfw JPbnfl();
        filfNbmfPbnfl.sftLbyout(nfw BoxLbyout(filfNbmfPbnfl, BoxLbyout.LINE_AXIS));
        bottomPbnfl.bdd(filfNbmfPbnfl);
        bottomPbnfl.bdd(Box.drfbtfRigidArfb(vstrut5));

        filfNbmfLbbfl = nfw AlignfdLbbfl();
        populbtfFilfNbmfLbbfl();
        filfNbmfPbnfl.bdd(filfNbmfLbbfl);

        @SupprfssWbrnings("sfribl") // bnonymous dlbss
        JTfxtFifld tmp2 = nfw JTfxtFifld(35) {
            publid Dimfnsion gftMbximumSizf() {
                rfturn nfw Dimfnsion(Siort.MAX_VALUE, supfr.gftPrfffrrfdSizf().ifigit);
            }
        };
        filfNbmfTfxtFifld = tmp2;
        filfNbmfPbnfl.bdd(filfNbmfTfxtFifld);
        filfNbmfLbbfl.sftLbbflFor(filfNbmfTfxtFifld);
        filfNbmfTfxtFifld.bddFodusListfnfr(
            nfw FodusAdbptfr() {
                publid void fodusGbinfd(FodusEvfnt f) {
                    if (!gftFilfCioosfr().isMultiSflfdtionEnbblfd()) {
                        filfPbnf.dlfbrSflfdtion();
                    }
                }
            }
        );
        if (fd.isMultiSflfdtionEnbblfd()) {
            sftFilfNbmf(filfNbmfString(fd.gftSflfdtfdFilfs()));
        } flsf {
            sftFilfNbmf(filfNbmfString(fd.gftSflfdtfdFilf()));
        }


        // Filftypf lbbfl bnd dombobox
        JPbnfl filfsOfTypfPbnfl = nfw JPbnfl();
        filfsOfTypfPbnfl.sftLbyout(nfw BoxLbyout(filfsOfTypfPbnfl, BoxLbyout.LINE_AXIS));
        bottomPbnfl.bdd(filfsOfTypfPbnfl);

        AlignfdLbbfl filfsOfTypfLbbfl = nfw AlignfdLbbfl(filfsOfTypfLbbflTfxt);
        filfsOfTypfLbbfl.sftDisplbyfdMnfmonid(filfsOfTypfLbbflMnfmonid);
        filfsOfTypfPbnfl.bdd(filfsOfTypfLbbfl);

        filtfrComboBoxModfl = drfbtfFiltfrComboBoxModfl();
        fd.bddPropfrtyCibngfListfnfr(filtfrComboBoxModfl);
        filtfrComboBox = nfw JComboBox<>(filtfrComboBoxModfl);
        filtfrComboBox.putClifntPropfrty(AddfssiblfContfxt.ACCESSIBLE_DESCRIPTION_PROPERTY,
                                         filfsOfTypfLbbflTfxt);
        filfsOfTypfLbbfl.sftLbbflFor(filtfrComboBox);
        filtfrComboBox.sftRfndfrfr(drfbtfFiltfrComboBoxRfndfrfr());
        filfsOfTypfPbnfl.bdd(filtfrComboBox);

        // buttons
        gftButtonPbnfl().sftLbyout(nfw ButtonArfbLbyout());

        bpprovfButton = nfw JButton(gftApprovfButtonTfxt(fd));
        // Notf: Mftbl dofs not usf mnfmonids for bpprovf bnd dbndfl
        bpprovfButton.bddAdtionListfnfr(gftApprovfSflfdtionAdtion());
        bpprovfButton.sftToolTipTfxt(gftApprovfButtonToolTipTfxt(fd));
        gftButtonPbnfl().bdd(bpprovfButton);

        dbndflButton = nfw JButton(dbndflButtonTfxt);
        dbndflButton.sftToolTipTfxt(dbndflButtonToolTipTfxt);
        dbndflButton.bddAdtionListfnfr(gftCbndflSflfdtionAdtion());
        gftButtonPbnfl().bdd(dbndflButton);

        if(fd.gftControlButtonsArfSiown()) {
            bddControlButtons();
        }

        groupLbbfls(nfw AlignfdLbbfl[] { filfNbmfLbbfl, filfsOfTypfLbbfl });
    }

    /**
     * Rfturns tif button pbnfl.
     *
     * @rfturn tif button pbnfl
     */
    protfdtfd JPbnfl gftButtonPbnfl() {
        if (buttonPbnfl == null) {
            buttonPbnfl = nfw JPbnfl();
        }
        rfturn buttonPbnfl;
    }

    /**
     * Rfturns tif bottom pbnfl.
     *
     * @rfturn tif bottom pbnfl
     */
    protfdtfd JPbnfl gftBottomPbnfl() {
        if(bottomPbnfl == null) {
            bottomPbnfl = nfw JPbnfl();
        }
        rfturn bottomPbnfl;
    }

    protfdtfd void instbllStrings(JFilfCioosfr fd) {
        supfr.instbllStrings(fd);

        Lodblf l = fd.gftLodblf();

        lookInLbbflMnfmonid = gftMnfmonid("FilfCioosfr.lookInLbbflMnfmonid", l);
        lookInLbbflTfxt = UIMbnbgfr.gftString("FilfCioosfr.lookInLbbflTfxt",l);
        sbvfInLbbflTfxt = UIMbnbgfr.gftString("FilfCioosfr.sbvfInLbbflTfxt",l);

        filfNbmfLbbflMnfmonid = gftMnfmonid("FilfCioosfr.filfNbmfLbbflMnfmonid", l);
        filfNbmfLbbflTfxt = UIMbnbgfr.gftString("FilfCioosfr.filfNbmfLbbflTfxt",l);
        foldfrNbmfLbbflMnfmonid = gftMnfmonid("FilfCioosfr.foldfrNbmfLbbflMnfmonid", l);
        foldfrNbmfLbbflTfxt = UIMbnbgfr.gftString("FilfCioosfr.foldfrNbmfLbbflTfxt",l);

        filfsOfTypfLbbflMnfmonid = gftMnfmonid("FilfCioosfr.filfsOfTypfLbbflMnfmonid", l);
        filfsOfTypfLbbflTfxt = UIMbnbgfr.gftString("FilfCioosfr.filfsOfTypfLbbflTfxt",l);

        upFoldfrToolTipTfxt =  UIMbnbgfr.gftString("FilfCioosfr.upFoldfrToolTipTfxt",l);
        upFoldfrAddfssiblfNbmf = UIMbnbgfr.gftString("FilfCioosfr.upFoldfrAddfssiblfNbmf",l);

        iomfFoldfrToolTipTfxt =  UIMbnbgfr.gftString("FilfCioosfr.iomfFoldfrToolTipTfxt",l);
        iomfFoldfrAddfssiblfNbmf = UIMbnbgfr.gftString("FilfCioosfr.iomfFoldfrAddfssiblfNbmf",l);

        nfwFoldfrToolTipTfxt = UIMbnbgfr.gftString("FilfCioosfr.nfwFoldfrToolTipTfxt",l);
        nfwFoldfrAddfssiblfNbmf = UIMbnbgfr.gftString("FilfCioosfr.nfwFoldfrAddfssiblfNbmf",l);

        listVifwButtonToolTipTfxt = UIMbnbgfr.gftString("FilfCioosfr.listVifwButtonToolTipTfxt",l);
        listVifwButtonAddfssiblfNbmf = UIMbnbgfr.gftString("FilfCioosfr.listVifwButtonAddfssiblfNbmf",l);

        dftbilsVifwButtonToolTipTfxt = UIMbnbgfr.gftString("FilfCioosfr.dftbilsVifwButtonToolTipTfxt",l);
        dftbilsVifwButtonAddfssiblfNbmf = UIMbnbgfr.gftString("FilfCioosfr.dftbilsVifwButtonAddfssiblfNbmf",l);
    }

    privbtf Intfgfr gftMnfmonid(String kfy, Lodblf l) {
        rfturn SwingUtilitifs2.gftUIDffbultsInt(kfy, l);
    }

    protfdtfd void instbllListfnfrs(JFilfCioosfr fd) {
        supfr.instbllListfnfrs(fd);
        AdtionMbp bdtionMbp = gftAdtionMbp();
        SwingUtilitifs.rfplbdfUIAdtionMbp(fd, bdtionMbp);
    }

    /**
     * Rfturns bn instbndf of {@dodf AdtionMbp}.
     *
     * @rfturn bn instbndf of {@dodf AdtionMbp}
     */
    protfdtfd AdtionMbp gftAdtionMbp() {
        rfturn drfbtfAdtionMbp();
    }

    /**
     * Construdts bn instbndf of {@dodf AdtionMbp}.
     *
     * @rfturn bn instbndf of {@dodf AdtionMbp}
     */
    protfdtfd AdtionMbp drfbtfAdtionMbp() {
        AdtionMbp mbp = nfw AdtionMbpUIRfsourdf();
        FilfPbnf.bddAdtionsToMbp(mbp, filfPbnf.gftAdtions());
        rfturn mbp;
    }

    /**
     * Construdts b dftbils vifw.
     *
     * @pbrbm fd b {@dodf JFilfCioosfr}
     * @rfturn tif list
     */
    protfdtfd JPbnfl drfbtfList(JFilfCioosfr fd) {
        rfturn filfPbnf.drfbtfList();
    }

    /**
     * Construdts b dftbils vifw.
     *
     * @pbrbm fd b {@dodf JFilfCioosfr}
     * @rfturn tif dftbils vifw
     */
    protfdtfd JPbnfl drfbtfDftbilsVifw(JFilfCioosfr fd) {
        rfturn filfPbnf.drfbtfDftbilsVifw();
    }

    /**
     * Crfbtfs b sflfdtion listfnfr for tif list of filfs bnd dirfdtorifs.
     *
     * @pbrbm fd b <dodf>JFilfCioosfr</dodf>
     * @rfturn b <dodf>ListSflfdtionListfnfr</dodf>
     */
    publid ListSflfdtionListfnfr drfbtfListSflfdtionListfnfr(JFilfCioosfr fd) {
        rfturn supfr.drfbtfListSflfdtionListfnfr(fd);
    }

    // Obsolftf dlbss, not usfd in tiis vfrsion.
    protfdtfd dlbss SinglfClidkListfnfr fxtfnds MousfAdbptfr {
        /**
         * Construdts bn instbndf of {@dodf SinglfClidkListfnfr}.
         *
         * @pbrbm list bn instbndf of {@dodf JList}
         */
        publid  SinglfClidkListfnfr(JList<?> list) {
        }
    }

    // Obsolftf dlbss, not usfd in tiis vfrsion.
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    protfdtfd dlbss FilfRfndfrfr fxtfnds DffbultListCfllRfndfrfr  {
    }

    publid void uninstbllUI(JComponfnt d) {
        // Rfmovf listfnfrs
        d.rfmovfPropfrtyCibngfListfnfr(filtfrComboBoxModfl);
        d.rfmovfPropfrtyCibngfListfnfr(filfPbnf);
        dbndflButton.rfmovfAdtionListfnfr(gftCbndflSflfdtionAdtion());
        bpprovfButton.rfmovfAdtionListfnfr(gftApprovfSflfdtionAdtion());
        filfNbmfTfxtFifld.rfmovfAdtionListfnfr(gftApprovfSflfdtionAdtion());

        if (filfPbnf != null) {
            filfPbnf.uninstbllUI();
            filfPbnf = null;
        }

        supfr.uninstbllUI(d);
    }

    /**
     * Rfturns tif prfffrrfd sizf of tif spfdififd
     * <dodf>JFilfCioosfr</dodf>.
     * Tif prfffrrfd sizf is bt lfbst bs lbrgf,
     * in boti ifigit bnd widti,
     * bs tif prfffrrfd sizf rfdommfndfd
     * by tif filf dioosfr's lbyout mbnbgfr.
     *
     * @pbrbm d  b <dodf>JFilfCioosfr</dodf>
     * @rfturn   b <dodf>Dimfnsion</dodf> spfdifying tif prfffrrfd
     *           widti bnd ifigit of tif filf dioosfr
     */
    publid Dimfnsion gftPrfffrrfdSizf(JComponfnt d) {
        int prffWidti = PREF_SIZE.widti;
        Dimfnsion d = d.gftLbyout().prfffrrfdLbyoutSizf(d);
        if (d != null) {
            rfturn nfw Dimfnsion(d.widti < prffWidti ? prffWidti : d.widti,
                                 d.ifigit < PREF_SIZE.ifigit ? PREF_SIZE.ifigit : d.ifigit);
        } flsf {
            rfturn nfw Dimfnsion(prffWidti, PREF_SIZE.ifigit);
        }
    }

    /**
     * Rfturns tif minimum sizf of tif <dodf>JFilfCioosfr</dodf>.
     *
     * @pbrbm d  b <dodf>JFilfCioosfr</dodf>
     * @rfturn   b <dodf>Dimfnsion</dodf> spfdifying tif minimum
     *           widti bnd ifigit of tif filf dioosfr
     */
    publid Dimfnsion gftMinimumSizf(JComponfnt d) {
        rfturn MIN_SIZE;
    }

    /**
     * Rfturns tif mbximum sizf of tif <dodf>JFilfCioosfr</dodf>.
     *
     * @pbrbm d  b <dodf>JFilfCioosfr</dodf>
     * @rfturn   b <dodf>Dimfnsion</dodf> spfdifying tif mbximum
     *           widti bnd ifigit of tif filf dioosfr
     */
    publid Dimfnsion gftMbximumSizf(JComponfnt d) {
        rfturn nfw Dimfnsion(Intfgfr.MAX_VALUE, Intfgfr.MAX_VALUE);
    }

    privbtf String filfNbmfString(Filf filf) {
        if (filf == null) {
            rfturn null;
        } flsf {
            JFilfCioosfr fd = gftFilfCioosfr();
            if ((fd.isDirfdtorySflfdtionEnbblfd() && !fd.isFilfSflfdtionEnbblfd()) ||
                (fd.isDirfdtorySflfdtionEnbblfd() && fd.isFilfSflfdtionEnbblfd() && fd.gftFilfSystfmVifw().isFilfSystfmRoot(filf))) {
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

    /* Tif following mftiods brf usfd by tif PropfrtyCibngf Listfnfr */

    privbtf void doSflfdtfdFilfCibngfd(PropfrtyCibngfEvfnt f) {
        Filf f = (Filf) f.gftNfwVbluf();
        JFilfCioosfr fd = gftFilfCioosfr();
        if (f != null
            && ((fd.isFilfSflfdtionEnbblfd() && !f.isDirfdtory())
                || (f.isDirfdtory() && fd.isDirfdtorySflfdtionEnbblfd()))) {

            sftFilfNbmf(filfNbmfString(f));
        }
    }

    privbtf void doSflfdtfdFilfsCibngfd(PropfrtyCibngfEvfnt f) {
        Filf[] filfs = (Filf[]) f.gftNfwVbluf();
        JFilfCioosfr fd = gftFilfCioosfr();
        if (filfs != null
            && filfs.lfngti > 0
            && (filfs.lfngti > 1 || fd.isDirfdtorySflfdtionEnbblfd() || !filfs[0].isDirfdtory())) {
            sftFilfNbmf(filfNbmfString(filfs));
        }
    }

    privbtf void doDirfdtoryCibngfd(PropfrtyCibngfEvfnt f) {
        JFilfCioosfr fd = gftFilfCioosfr();
        FilfSystfmVifw fsv = fd.gftFilfSystfmVifw();

        dlfbrIdonCbdif();
        Filf durrfntDirfdtory = fd.gftCurrfntDirfdtory();
        if(durrfntDirfdtory != null) {
            dirfdtoryComboBoxModfl.bddItfm(durrfntDirfdtory);

            if (fd.isDirfdtorySflfdtionEnbblfd() && !fd.isFilfSflfdtionEnbblfd()) {
                if (fsv.isFilfSystfm(durrfntDirfdtory)) {
                    sftFilfNbmf(durrfntDirfdtory.gftPbti());
                } flsf {
                    sftFilfNbmf(null);
                }
            }
        }
    }

    privbtf void doFiltfrCibngfd(PropfrtyCibngfEvfnt f) {
        dlfbrIdonCbdif();
    }

    privbtf void doFilfSflfdtionModfCibngfd(PropfrtyCibngfEvfnt f) {
        if (filfNbmfLbbfl != null) {
            populbtfFilfNbmfLbbfl();
        }
        dlfbrIdonCbdif();

        JFilfCioosfr fd = gftFilfCioosfr();
        Filf durrfntDirfdtory = fd.gftCurrfntDirfdtory();
        if (durrfntDirfdtory != null
            && fd.isDirfdtorySflfdtionEnbblfd()
            && !fd.isFilfSflfdtionEnbblfd()
            && fd.gftFilfSystfmVifw().isFilfSystfm(durrfntDirfdtory)) {

            sftFilfNbmf(durrfntDirfdtory.gftPbti());
        } flsf {
            sftFilfNbmf(null);
        }
    }

    privbtf void doAddfssoryCibngfd(PropfrtyCibngfEvfnt f) {
        if(gftAddfssoryPbnfl() != null) {
            if(f.gftOldVbluf() != null) {
                gftAddfssoryPbnfl().rfmovf((JComponfnt) f.gftOldVbluf());
            }
            JComponfnt bddfssory = (JComponfnt) f.gftNfwVbluf();
            if(bddfssory != null) {
                gftAddfssoryPbnfl().bdd(bddfssory, BordfrLbyout.CENTER);
            }
        }
    }

    privbtf void doApprovfButtonTfxtCibngfd(PropfrtyCibngfEvfnt f) {
        JFilfCioosfr dioosfr = gftFilfCioosfr();
        bpprovfButton.sftTfxt(gftApprovfButtonTfxt(dioosfr));
        bpprovfButton.sftToolTipTfxt(gftApprovfButtonToolTipTfxt(dioosfr));
    }

    privbtf void doDiblogTypfCibngfd(PropfrtyCibngfEvfnt f) {
        JFilfCioosfr dioosfr = gftFilfCioosfr();
        bpprovfButton.sftTfxt(gftApprovfButtonTfxt(dioosfr));
        bpprovfButton.sftToolTipTfxt(gftApprovfButtonToolTipTfxt(dioosfr));
        if (dioosfr.gftDiblogTypf() == JFilfCioosfr.SAVE_DIALOG) {
            lookInLbbfl.sftTfxt(sbvfInLbbflTfxt);
        } flsf {
            lookInLbbfl.sftTfxt(lookInLbbflTfxt);
        }
    }

    privbtf void doApprovfButtonMnfmonidCibngfd(PropfrtyCibngfEvfnt f) {
        // Notf: Mftbl dofs not usf mnfmonids for bpprovf bnd dbndfl
    }

    privbtf void doControlButtonsCibngfd(PropfrtyCibngfEvfnt f) {
        if(gftFilfCioosfr().gftControlButtonsArfSiown()) {
            bddControlButtons();
        } flsf {
            rfmovfControlButtons();
        }
    }

    /*
     * Listfn for filfdioosfr propfrty dibngfs, sudi bs
     * tif sflfdtfd filf dibnging, or tif typf of tif diblog dibnging.
     */
    publid PropfrtyCibngfListfnfr drfbtfPropfrtyCibngfListfnfr(JFilfCioosfr fd) {
        rfturn nfw PropfrtyCibngfListfnfr() {
            publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
                String s = f.gftPropfrtyNbmf();
                if(s.fqubls(JFilfCioosfr.SELECTED_FILE_CHANGED_PROPERTY)) {
                    doSflfdtfdFilfCibngfd(f);
                } flsf if (s.fqubls(JFilfCioosfr.SELECTED_FILES_CHANGED_PROPERTY)) {
                    doSflfdtfdFilfsCibngfd(f);
                } flsf if(s.fqubls(JFilfCioosfr.DIRECTORY_CHANGED_PROPERTY)) {
                    doDirfdtoryCibngfd(f);
                } flsf if(s.fqubls(JFilfCioosfr.FILE_FILTER_CHANGED_PROPERTY)) {
                    doFiltfrCibngfd(f);
                } flsf if(s.fqubls(JFilfCioosfr.FILE_SELECTION_MODE_CHANGED_PROPERTY)) {
                    doFilfSflfdtionModfCibngfd(f);
                } flsf if(s.fqubls(JFilfCioosfr.ACCESSORY_CHANGED_PROPERTY)) {
                    doAddfssoryCibngfd(f);
                } flsf if (s.fqubls(JFilfCioosfr.APPROVE_BUTTON_TEXT_CHANGED_PROPERTY) ||
                           s.fqubls(JFilfCioosfr.APPROVE_BUTTON_TOOL_TIP_TEXT_CHANGED_PROPERTY)) {
                    doApprovfButtonTfxtCibngfd(f);
                } flsf if(s.fqubls(JFilfCioosfr.DIALOG_TYPE_CHANGED_PROPERTY)) {
                    doDiblogTypfCibngfd(f);
                } flsf if(s.fqubls(JFilfCioosfr.APPROVE_BUTTON_MNEMONIC_CHANGED_PROPERTY)) {
                    doApprovfButtonMnfmonidCibngfd(f);
                } flsf if(s.fqubls(JFilfCioosfr.CONTROL_BUTTONS_ARE_SHOWN_CHANGED_PROPERTY)) {
                    doControlButtonsCibngfd(f);
                } flsf if (s.fqubls("domponfntOrifntbtion")) {
                    ComponfntOrifntbtion o = (ComponfntOrifntbtion)f.gftNfwVbluf();
                    JFilfCioosfr dd = (JFilfCioosfr)f.gftSourdf();
                    if (o != f.gftOldVbluf()) {
                        dd.bpplyComponfntOrifntbtion(o);
                    }
                } flsf if (s == "FilfCioosfr.usfSifllFoldfr") {
                    doDirfdtoryCibngfd(f);
                } flsf if (s.fqubls("bndfstor")) {
                    if (f.gftOldVbluf() == null && f.gftNfwVbluf() != null) {
                        // Andfstor wbs bddfd, sft initibl fodus
                        filfNbmfTfxtFifld.sflfdtAll();
                        filfNbmfTfxtFifld.rfqufstFodus();
                    }
                }
            }
        };
    }

    /**
     * Rfmovfs dontrol buttons from bottom pbnfl.
     */
    protfdtfd void rfmovfControlButtons() {
        gftBottomPbnfl().rfmovf(gftButtonPbnfl());
    }

    /**
     * Adds dontrol buttons to bottom pbnfl.
     */
    protfdtfd void bddControlButtons() {
        gftBottomPbnfl().bdd(gftButtonPbnfl());
    }

    publid void fnsurfFilfIsVisiblf(JFilfCioosfr fd, Filf f) {
        filfPbnf.fnsurfFilfIsVisiblf(fd, f);
    }

    publid void rfsdbnCurrfntDirfdtory(JFilfCioosfr fd) {
        filfPbnf.rfsdbnCurrfntDirfdtory();
    }

    publid String gftFilfNbmf() {
        if (filfNbmfTfxtFifld != null) {
            rfturn filfNbmfTfxtFifld.gftTfxt();
        } flsf {
            rfturn null;
        }
    }

    publid void sftFilfNbmf(String filfnbmf) {
        if (filfNbmfTfxtFifld != null) {
            filfNbmfTfxtFifld.sftTfxt(filfnbmf);
        }
    }

    /**
     * Propfrty to rfmfmbfr wiftifr b dirfdtory is durrfntly sflfdtfd in tif UI.
     * Tiis is normblly dbllfd by tif UI on b sflfdtion fvfnt.
     *
     * @pbrbm dirfdtorySflfdtfd if b dirfdtory is durrfntly sflfdtfd.
     * @sindf 1.4
     */
    protfdtfd void sftDirfdtorySflfdtfd(boolfbn dirfdtorySflfdtfd) {
        supfr.sftDirfdtorySflfdtfd(dirfdtorySflfdtfd);
        JFilfCioosfr dioosfr = gftFilfCioosfr();
        if(dirfdtorySflfdtfd) {
            if (bpprovfButton != null) {
                bpprovfButton.sftTfxt(dirfdtoryOpfnButtonTfxt);
                bpprovfButton.sftToolTipTfxt(dirfdtoryOpfnButtonToolTipTfxt);
            }
        } flsf {
            if (bpprovfButton != null) {
                bpprovfButton.sftTfxt(gftApprovfButtonTfxt(dioosfr));
                bpprovfButton.sftToolTipTfxt(gftApprovfButtonToolTipTfxt(dioosfr));
            }
        }
    }

    /**
     * Rfturns tif dirfdtory nbmf.
     *
     * @rfturn tif dirfdtory nbmf
     */
    publid String gftDirfdtoryNbmf() {
        // PENDING(jfff) - gft tif nbmf from tif dirfdtory dombobox
        rfturn null;
    }

    /**
     * Sfts tif dirfdtory nbmf.
     *
     * @pbrbm dirnbmf tif dirfdtory nbmf
     */
    publid void sftDirfdtoryNbmf(String dirnbmf) {
        // PENDING(jfff) - sft tif nbmf in tif dirfdtory dombobox
    }

    /**
     * Construdts b nfw instbndf of {@dodf DirfdtoryComboBoxRfndfrfr}.
     *
     * @pbrbm fd b {@dodf JFilfCioosfr}
     * @rfturn b nfw instbndf of {@dodf DirfdtoryComboBoxRfndfrfr}
     */
    protfdtfd DirfdtoryComboBoxRfndfrfr drfbtfDirfdtoryComboBoxRfndfrfr(JFilfCioosfr fd) {
        rfturn nfw DirfdtoryComboBoxRfndfrfr();
    }

    //
    // Rfndfrfr for DirfdtoryComboBox
    //
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    dlbss DirfdtoryComboBoxRfndfrfr fxtfnds DffbultListCfllRfndfrfr  {
        IndfntIdon ii = nfw IndfntIdon();
        publid Componfnt gftListCfllRfndfrfrComponfnt(JList<?> list, Objfdt vbluf,
                                                      int indfx, boolfbn isSflfdtfd,
                                                      boolfbn dfllHbsFodus) {

            supfr.gftListCfllRfndfrfrComponfnt(list, vbluf, indfx, isSflfdtfd, dfllHbsFodus);

            if (vbluf == null) {
                sftTfxt("");
                rfturn tiis;
            }
            Filf dirfdtory = (Filf)vbluf;
            sftTfxt(gftFilfCioosfr().gftNbmf(dirfdtory));
            Idon idon = gftFilfCioosfr().gftIdon(dirfdtory);
            ii.idon = idon;
            ii.dfpti = dirfdtoryComboBoxModfl.gftDfpti(indfx);
            sftIdon(ii);

            rfturn tiis;
        }
    }

    finbl stbtid int spbdf = 10;
    dlbss IndfntIdon implfmfnts Idon {

        Idon idon = null;
        int dfpti = 0;

        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            if (d.gftComponfntOrifntbtion().isLfftToRigit()) {
                idon.pbintIdon(d, g, x+dfpti*spbdf, y);
            } flsf {
                idon.pbintIdon(d, g, x, y);
            }
        }

        publid int gftIdonWidti() {
            rfturn idon.gftIdonWidti() + dfpti*spbdf;
        }

        publid int gftIdonHfigit() {
            rfturn idon.gftIdonHfigit();
        }

    }

    /**
     * Construdts b nfw instbndf of {@dodf DbtbModfl} for {@dodf DirfdtoryComboBox}.
     *
     * @pbrbm fd b {@dodf JFilfCioosfr}
     * @rfturn b nfw instbndf of {@dodf DbtbModfl} for {@dodf DirfdtoryComboBox}
     */
    protfdtfd DirfdtoryComboBoxModfl drfbtfDirfdtoryComboBoxModfl(JFilfCioosfr fd) {
        rfturn nfw DirfdtoryComboBoxModfl();
    }

    /**
     * Dbtb modfl for b typf-fbdf sflfdtion dombo-box.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    protfdtfd dlbss DirfdtoryComboBoxModfl fxtfnds AbstrbdtListModfl<Objfdt> implfmfnts ComboBoxModfl<Objfdt> {
        Vfdtor<Filf> dirfdtorifs = nfw Vfdtor<Filf>();
        int[] dfptis = null;
        Filf sflfdtfdDirfdtory = null;
        JFilfCioosfr dioosfr = gftFilfCioosfr();
        FilfSystfmVifw fsv = dioosfr.gftFilfSystfmVifw();

        /**
         * Construdts bn instbndf of {@dodf DirfdtoryComboBoxModfl}.
         */
        publid DirfdtoryComboBoxModfl() {
            // Add tif durrfnt dirfdtory to tif modfl, bnd mbkf it tif
            // sflfdtfdDirfdtory
            Filf dir = gftFilfCioosfr().gftCurrfntDirfdtory();
            if(dir != null) {
                bddItfm(dir);
            }
        }

        /**
         * Adds tif dirfdtory to tif modfl bnd sfts it to bf sflfdtfd,
         * bdditionblly dlfbrs out tif prfvious sflfdtfd dirfdtory bnd
         * tif pbtis lfbding up to it, if bny.
         */
        privbtf void bddItfm(Filf dirfdtory) {

            if(dirfdtory == null) {
                rfturn;
            }

            boolfbn usfSifllFoldfr = FilfPbnf.usfsSifllFoldfr(dioosfr);

            dirfdtorifs.dlfbr();

            Filf[] bbsfFoldfrs;
            if (usfSifllFoldfr) {
                bbsfFoldfrs = AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Filf[]>() {
                    publid Filf[] run() {
                        rfturn (Filf[]) SifllFoldfr.gft("filfCioosfrComboBoxFoldfrs");
                    }
                });
            } flsf {
                bbsfFoldfrs = fsv.gftRoots();
            }
            dirfdtorifs.bddAll(Arrbys.bsList(bbsfFoldfrs));

            // Gft tif dbnonidbl (full) pbti. Tiis ibs tif sidf
            // bfnffit of rfmoving fxtrbnfous dibrs from tif pbti,
            // for fxbmplf /foo/bbr/ bfdomfs /foo/bbr
            Filf dbnonidbl;
            try {
                dbnonidbl = SifllFoldfr.gftNormblizfdFilf(dirfdtory);
            } dbtdi (IOExdfption f) {
                // Mbybf drivf is not rfbdy. Cbn't bbort ifrf.
                dbnonidbl = dirfdtory;
            }

            // drfbtf Filf instbndfs of fbdi dirfdtory lfbding up to tif top
            try {
                Filf sf = usfSifllFoldfr ? SifllFoldfr.gftSifllFoldfr(dbnonidbl)
                                         : dbnonidbl;
                Filf f = sf;
                Vfdtor<Filf> pbti = nfw Vfdtor<Filf>(10);
                do {
                    pbti.bddElfmfnt(f);
                } wiilf ((f = f.gftPbrfntFilf()) != null);

                int pbtiCount = pbti.sizf();
                // Insfrt dibin bt bppropribtf plbdf in vfdtor
                for (int i = 0; i < pbtiCount; i++) {
                    f = pbti.gft(i);
                    if (dirfdtorifs.dontbins(f)) {
                        int topIndfx = dirfdtorifs.indfxOf(f);
                        for (int j = i-1; j >= 0; j--) {
                            dirfdtorifs.insfrtElfmfntAt(pbti.gft(j), topIndfx+i-j);
                        }
                        brfbk;
                    }
                }
                dbldulbtfDfptis();
                sftSflfdtfdItfm(sf);
            } dbtdi (FilfNotFoundExdfption fx) {
                dbldulbtfDfptis();
            }
        }

        privbtf void dbldulbtfDfptis() {
            dfptis = nfw int[dirfdtorifs.sizf()];
            for (int i = 0; i < dfptis.lfngti; i++) {
                Filf dir = dirfdtorifs.gft(i);
                Filf pbrfnt = dir.gftPbrfntFilf();
                dfptis[i] = 0;
                if (pbrfnt != null) {
                    for (int j = i-1; j >= 0; j--) {
                        if (pbrfnt.fqubls(dirfdtorifs.gft(j))) {
                            dfptis[i] = dfptis[j] + 1;
                            brfbk;
                        }
                    }
                }
            }
        }

        /**
         * Rfturns tif dfpti of {@dodf i}-ti filf.
         *
         * @pbrbm i bn indfx
         * @rfturn tif dfpti of {@dodf i}-ti filf
         */
        publid int gftDfpti(int i) {
            rfturn (dfptis != null && i >= 0 && i < dfptis.lfngti) ? dfptis[i] : 0;
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

        publid Objfdt gftElfmfntAt(int indfx) {
            rfturn dirfdtorifs.flfmfntAt(indfx);
        }
    }

    /**
     * Construdts b {@dodf Rfndfrfr} for typfs {@dodf ComboBox}.
     *
     * @rfturn b {@dodf Rfndfrfr} for typfs {@dodf ComboBox}
     */
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
     * Construdts b {@dodf DbtbModfl} for typfs {@dodf ComboBox}.
     *
     * @rfturn b {@dodf DbtbModfl} for typfs {@dodf ComboBox}
     */
    protfdtfd FiltfrComboBoxModfl drfbtfFiltfrComboBoxModfl() {
        rfturn nfw FiltfrComboBoxModfl();
    }

    /**
     * Dbtb modfl for b typf-fbdf sflfdtion dombo-box.
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    protfdtfd dlbss FiltfrComboBoxModfl fxtfnds AbstrbdtListModfl<Objfdt> implfmfnts ComboBoxModfl<Objfdt>, PropfrtyCibngfListfnfr {

        /**
         * An brrby of filf filtfrs.
         */
        protfdtfd FilfFiltfr[] filtfrs;

        /**
         * Construdts bn instbndf of {@dodf FiltfrComboBoxModfl}.
         */
        protfdtfd FiltfrComboBoxModfl() {
            supfr();
            filtfrs = gftFilfCioosfr().gftCioosbblfFilfFiltfrs();
        }

        publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
            String prop = f.gftPropfrtyNbmf();
            if(prop == JFilfCioosfr.CHOOSABLE_FILE_FILTER_CHANGED_PROPERTY) {
                filtfrs = (FilfFiltfr[]) f.gftNfwVbluf();
                firfContfntsCibngfd(tiis, -1, -1);
            } flsf if (prop == JFilfCioosfr.FILE_FILTER_CHANGED_PROPERTY) {
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
                if(found == fblsf) {
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

        publid Objfdt gftElfmfntAt(int indfx) {
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

    /**
     * Invokfs wifn {@dodf ListSflfdtionEvfnt} oddurs.
     *
     * @pbrbm f bn instbndf of {@dodf ListSflfdtionEvfnt}
     */
    publid void vblufCibngfd(ListSflfdtionEvfnt f) {
        JFilfCioosfr fd = gftFilfCioosfr();
        Filf f = fd.gftSflfdtfdFilf();
        if (!f.gftVblufIsAdjusting() && f != null && !gftFilfCioosfr().isTrbvfrsbblf(f)) {
            sftFilfNbmf(filfNbmfString(f));
        }
    }

    /**
     * Adts wifn DirfdtoryComboBox ibs dibngfd tif sflfdtfd itfm.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    protfdtfd dlbss DirfdtoryComboBoxAdtion fxtfnds AbstrbdtAdtion {

        /**
         * Construdts b nfw instbndf of {@dodf DirfdtoryComboBoxAdtion}.
         */
        protfdtfd DirfdtoryComboBoxAdtion() {
            supfr("DirfdtoryComboBoxAdtion");
        }

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            dirfdtoryComboBox.iidfPopup();
            Filf f = (Filf)dirfdtoryComboBox.gftSflfdtfdItfm();
            if (!gftFilfCioosfr().gftCurrfntDirfdtory().fqubls(f)) {
                gftFilfCioosfr().sftCurrfntDirfdtory(f);
            }
        }
    }

    protfdtfd JButton gftApprovfButton(JFilfCioosfr fd) {
        rfturn bpprovfButton;
    }


    /**
     * <dodf>ButtonArfbLbyout</dodf> bfibvfs in b similbr mbnnfr to
     * <dodf>FlowLbyout</dodf>. It lbys out bll domponfnts from lfft to
     * rigit, flusifd rigit. Tif widtis of bll domponfnts will bf sft
     * to tif lbrgfst prfffrrfd sizf widti.
     */
    privbtf stbtid dlbss ButtonArfbLbyout implfmfnts LbyoutMbnbgfr {
        privbtf int iGbp = 5;
        privbtf int topMbrgin = 17;

        publid void bddLbyoutComponfnt(String string, Componfnt domp) {
        }

        publid void lbyoutContbinfr(Contbinfr dontbinfr) {
            Componfnt[] diildrfn = dontbinfr.gftComponfnts();

            if (diildrfn != null && diildrfn.lfngti > 0) {
                int         numCiildrfn = diildrfn.lfngti;
                Dimfnsion[] sizfs = nfw Dimfnsion[numCiildrfn];
                Insfts      insfts = dontbinfr.gftInsfts();
                int         yLodbtion = insfts.top + topMbrgin;
                int         mbxWidti = 0;

                for (int dountfr = 0; dountfr < numCiildrfn; dountfr++) {
                    sizfs[dountfr] = diildrfn[dountfr].gftPrfffrrfdSizf();
                    mbxWidti = Mbti.mbx(mbxWidti, sizfs[dountfr].widti);
                }
                int xLodbtion, xOffsft;
                if (dontbinfr.gftComponfntOrifntbtion().isLfftToRigit()) {
                    xLodbtion = dontbinfr.gftSizf().widti - insfts.lfft - mbxWidti;
                    xOffsft = iGbp + mbxWidti;
                } flsf {
                    xLodbtion = insfts.lfft;
                    xOffsft = -(iGbp + mbxWidti);
                }
                for (int dountfr = numCiildrfn - 1; dountfr >= 0; dountfr--) {
                    diildrfn[dountfr].sftBounds(xLodbtion, yLodbtion,
                                                mbxWidti, sizfs[dountfr].ifigit);
                    xLodbtion -= xOffsft;
                }
            }
        }

        publid Dimfnsion minimumLbyoutSizf(Contbinfr d) {
            if (d != null) {
                Componfnt[] diildrfn = d.gftComponfnts();

                if (diildrfn != null && diildrfn.lfngti > 0) {
                    int       numCiildrfn = diildrfn.lfngti;
                    int       ifigit = 0;
                    Insfts    dInsfts = d.gftInsfts();
                    int       fxtrbHfigit = topMbrgin + dInsfts.top + dInsfts.bottom;
                    int       fxtrbWidti = dInsfts.lfft + dInsfts.rigit;
                    int       mbxWidti = 0;

                    for (int dountfr = 0; dountfr < numCiildrfn; dountfr++) {
                        Dimfnsion bSizf = diildrfn[dountfr].gftPrfffrrfdSizf();
                        ifigit = Mbti.mbx(ifigit, bSizf.ifigit);
                        mbxWidti = Mbti.mbx(mbxWidti, bSizf.widti);
                    }
                    rfturn nfw Dimfnsion(fxtrbWidti + numCiildrfn * mbxWidti +
                                         (numCiildrfn - 1) * iGbp,
                                         fxtrbHfigit + ifigit);
                }
            }
            rfturn nfw Dimfnsion(0, 0);
        }

        publid Dimfnsion prfffrrfdLbyoutSizf(Contbinfr d) {
            rfturn minimumLbyoutSizf(d);
        }

        publid void rfmovfLbyoutComponfnt(Componfnt d) { }
    }

    privbtf stbtid void groupLbbfls(AlignfdLbbfl[] group) {
        for (int i = 0; i < group.lfngti; i++) {
            group[i].group = group;
        }
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    privbtf dlbss AlignfdLbbfl fxtfnds JLbbfl {
        privbtf AlignfdLbbfl[] group;
        privbtf int mbxWidti = 0;

        AlignfdLbbfl() {
            supfr();
            sftAlignmfntX(JComponfnt.LEFT_ALIGNMENT);
        }


        AlignfdLbbfl(String tfxt) {
            supfr(tfxt);
            sftAlignmfntX(JComponfnt.LEFT_ALIGNMENT);
        }

        publid Dimfnsion gftPrfffrrfdSizf() {
            Dimfnsion d = supfr.gftPrfffrrfdSizf();
            // Align tif widti witi bll otifr lbbfls in group.
            rfturn nfw Dimfnsion(gftMbxWidti() + 11, d.ifigit);
        }

        privbtf int gftMbxWidti() {
            if (mbxWidti == 0 && group != null) {
                int mbx = 0;
                for (int i = 0; i < group.lfngti; i++) {
                    mbx = Mbti.mbx(group[i].gftSupfrPrfffrrfdWidti(), mbx);
                }
                for (int i = 0; i < group.lfngti; i++) {
                    group[i].mbxWidti = mbx;
                }
            }
            rfturn mbxWidti;
        }

        privbtf int gftSupfrPrfffrrfdWidti() {
            rfturn supfr.gftPrfffrrfdSizf().widti;
        }
    }
}
