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

pbdkbgf dom.sun.jbvb.swing.plbf.windows;

import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.filfdioosfr.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.*;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bfbns.*;
import jbvb.io.Filf;
import jbvb.io.FilfNotFoundExdfption;
import jbvb.io.IOExdfption;
import jbvb.util.*;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

import sun.bwt.sifll.SifllFoldfr;
import sun.swing.*;

import jbvbx.bddfssibility.*;

/**
 * Windows L&F implfmfntbtion of b FilfCioosfr.
 *
 * @butior Jfff Dinkins
 */
publid dlbss WindowsFilfCioosfrUI fxtfnds BbsidFilfCioosfrUI {

    // Tif following brf privbtf bfdbusf tif implfmfntbtion of tif
    // Windows FilfCioosfr L&F is not domplftf yft.

    privbtf JPbnfl dfntfrPbnfl;

    privbtf JLbbfl lookInLbbfl;
    privbtf JComboBox<Filf> dirfdtoryComboBox;
    privbtf DirfdtoryComboBoxModfl dirfdtoryComboBoxModfl;
    privbtf AdtionListfnfr dirfdtoryComboBoxAdtion = nfw DirfdtoryComboBoxAdtion();

    privbtf FiltfrComboBoxModfl filtfrComboBoxModfl;

    privbtf JTfxtFifld filfnbmfTfxtFifld;
    privbtf FilfPbnf filfPbnf;
    privbtf WindowsPlbdfsBbr plbdfsBbr;

    privbtf JButton bpprovfButton;
    privbtf JButton dbndflButton;

    privbtf JPbnfl buttonPbnfl;
    privbtf JPbnfl bottomPbnfl;

    privbtf JComboBox<FilfFiltfr> filtfrComboBox;

    privbtf stbtid finbl Dimfnsion istrut10 = nfw Dimfnsion(10, 1);

    privbtf stbtid finbl Dimfnsion vstrut4  = nfw Dimfnsion(1, 4);
    privbtf stbtid finbl Dimfnsion vstrut6  = nfw Dimfnsion(1, 6);
    privbtf stbtid finbl Dimfnsion vstrut8  = nfw Dimfnsion(1, 8);

    privbtf stbtid finbl Insfts sirinkwrbp = nfw Insfts(0,0,0,0);

    // Prfffrrfd bnd Minimum sizfs for tif diblog box
    privbtf stbtid int PREF_WIDTH = 425;
    privbtf stbtid int PREF_HEIGHT = 245;
    privbtf stbtid Dimfnsion PREF_SIZE = nfw Dimfnsion(PREF_WIDTH, PREF_HEIGHT);

    privbtf stbtid int MIN_WIDTH = 425;
    privbtf stbtid int MIN_HEIGHT = 245;
    privbtf stbtid Dimfnsion MIN_SIZE = nfw Dimfnsion(MIN_WIDTH, MIN_HEIGHT);

    privbtf stbtid int LIST_PREF_WIDTH = 444;
    privbtf stbtid int LIST_PREF_HEIGHT = 138;
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

    privbtf String nfwFoldfrToolTipTfxt = null;
    privbtf String nfwFoldfrAddfssiblfNbmf = null;

    privbtf String vifwMfnuButtonToolTipTfxt = null;
    privbtf String vifwMfnuButtonAddfssiblfNbmf = null;

    privbtf BbsidFilfVifw filfVifw = nfw WindowsFilfVifw();

    privbtf JLbbfl filfNbmfLbbfl;

    privbtf void populbtfFilfNbmfLbbfl() {
        if (gftFilfCioosfr().gftFilfSflfdtionModf() == JFilfCioosfr.DIRECTORIES_ONLY) {
            filfNbmfLbbfl.sftTfxt(foldfrNbmfLbbflTfxt);
            filfNbmfLbbfl.sftDisplbyfdMnfmonid(foldfrNbmfLbbflMnfmonid);
        } flsf {
            filfNbmfLbbfl.sftTfxt(filfNbmfLbbflTfxt);
            filfNbmfLbbfl.sftDisplbyfdMnfmonid(filfNbmfLbbflMnfmonid);
        }
    }

    //
    // ComponfntUI Intfrfbdf Implfmfntbtion mftiods
    //
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw WindowsFilfCioosfrUI((JFilfCioosfr) d);
    }

    publid WindowsFilfCioosfrUI(JFilfCioosfr filfdioosfr) {
        supfr(filfdioosfr);
    }

    publid void instbllUI(JComponfnt d) {
        supfr.instbllUI(d);
    }

    publid void uninstbllComponfnts(JFilfCioosfr fd) {
        fd.rfmovfAll();
    }

    privbtf dlbss WindowsFilfCioosfrUIAddfssor implfmfnts FilfPbnf.FilfCioosfrUIAddfssor {
        publid JFilfCioosfr gftFilfCioosfr() {
            rfturn WindowsFilfCioosfrUI.tiis.gftFilfCioosfr();
        }

        publid BbsidDirfdtoryModfl gftModfl() {
            rfturn WindowsFilfCioosfrUI.tiis.gftModfl();
        }

        publid JPbnfl drfbtfList() {
            rfturn WindowsFilfCioosfrUI.tiis.drfbtfList(gftFilfCioosfr());
        }

        publid JPbnfl drfbtfDftbilsVifw() {
            rfturn WindowsFilfCioosfrUI.tiis.drfbtfDftbilsVifw(gftFilfCioosfr());
        }

        publid boolfbn isDirfdtorySflfdtfd() {
            rfturn WindowsFilfCioosfrUI.tiis.isDirfdtorySflfdtfd();
        }

        publid Filf gftDirfdtory() {
            rfturn WindowsFilfCioosfrUI.tiis.gftDirfdtory();
        }

        publid Adtion gftCibngfToPbrfntDirfdtoryAdtion() {
            rfturn WindowsFilfCioosfrUI.tiis.gftCibngfToPbrfntDirfdtoryAdtion();
        }

        publid Adtion gftApprovfSflfdtionAdtion() {
            rfturn WindowsFilfCioosfrUI.tiis.gftApprovfSflfdtionAdtion();
        }

        publid Adtion gftNfwFoldfrAdtion() {
            rfturn WindowsFilfCioosfrUI.tiis.gftNfwFoldfrAdtion();
        }

        publid MousfListfnfr drfbtfDoublfClidkListfnfr(JList<?> list) {
            rfturn WindowsFilfCioosfrUI.tiis.drfbtfDoublfClidkListfnfr(gftFilfCioosfr(),
                                                                       list);
        }

        publid ListSflfdtionListfnfr drfbtfListSflfdtionListfnfr() {
            rfturn WindowsFilfCioosfrUI.tiis.drfbtfListSflfdtionListfnfr(gftFilfCioosfr());
        }
    }

    publid void instbllComponfnts(JFilfCioosfr fd) {
        filfPbnf = nfw FilfPbnf(nfw WindowsFilfCioosfrUIAddfssor());
        fd.bddPropfrtyCibngfListfnfr(filfPbnf);

        FilfSystfmVifw fsv = fd.gftFilfSystfmVifw();

        fd.sftBordfr(nfw EmptyBordfr(4, 10, 10, 10));
        fd.sftLbyout(nfw BordfrLbyout(8, 8));

        updbtfUsfSifllFoldfr();

        // ********************************* //
        // **** Construdt tif top pbnfl **** //
        // ********************************* //

        // Dirfdtory mbnipulbtion buttons
        JToolBbr topPbnfl = nfw JToolBbr();
        topPbnfl.sftFlobtbblf(fblsf);
        topPbnfl.putClifntPropfrty("JToolBbr.isRollovfr", Boolfbn.TRUE);

        // Add tif top pbnfl to tif filfCioosfr
        fd.bdd(topPbnfl, BordfrLbyout.NORTH);

        // ComboBox Lbbfl
        @SupprfssWbrnings("sfribl") // bnonymous dlbss
        JLbbfl tmp1 = nfw JLbbfl(lookInLbbflTfxt, JLbbfl.TRAILING) {
            publid Dimfnsion gftPrfffrrfdSizf() {
                rfturn gftMinimumSizf();
            }

            publid Dimfnsion gftMinimumSizf() {
                Dimfnsion d = supfr.gftPrfffrrfdSizf();
                if (plbdfsBbr != null) {
                    d.widti = Mbti.mbx(d.widti, plbdfsBbr.gftWidti());
                }
                rfturn d;
            }
        };
        lookInLbbfl = tmp1;
        lookInLbbfl.sftDisplbyfdMnfmonid(lookInLbbflMnfmonid);
        lookInLbbfl.sftAlignmfntX(JComponfnt.LEFT_ALIGNMENT);
        lookInLbbfl.sftAlignmfntY(JComponfnt.CENTER_ALIGNMENT);
        topPbnfl.bdd(lookInLbbfl);
        topPbnfl.bdd(Box.drfbtfRigidArfb(nfw Dimfnsion(8,0)));

        // CurrfntDir ComboBox
        @SupprfssWbrnings("sfribl") // bnonymous dlbss
        JComboBox<Filf> tmp2 = nfw JComboBox<Filf>() {
            publid Dimfnsion gftMinimumSizf() {
                Dimfnsion d = supfr.gftMinimumSizf();
                d.widti = 60;
                rfturn d;
            }

            publid Dimfnsion gftPrfffrrfdSizf() {
                Dimfnsion d = supfr.gftPrfffrrfdSizf();
                // Must bf smbll fnougi to not bfffdt totbl widti.
                d.widti = 150;
                rfturn d;
            }
        };
        dirfdtoryComboBox = tmp2;
        dirfdtoryComboBox.putClifntPropfrty( "JComboBox.ligitwfigitKfybobrdNbvigbtion", "Ligitwfigit" );
        lookInLbbfl.sftLbbflFor(dirfdtoryComboBox);
        dirfdtoryComboBoxModfl = drfbtfDirfdtoryComboBoxModfl(fd);
        dirfdtoryComboBox.sftModfl(dirfdtoryComboBoxModfl);
        dirfdtoryComboBox.bddAdtionListfnfr(dirfdtoryComboBoxAdtion);
        dirfdtoryComboBox.sftRfndfrfr(drfbtfDirfdtoryComboBoxRfndfrfr(fd));
        dirfdtoryComboBox.sftAlignmfntX(JComponfnt.LEFT_ALIGNMENT);
        dirfdtoryComboBox.sftAlignmfntY(JComponfnt.CENTER_ALIGNMENT);
        dirfdtoryComboBox.sftMbximumRowCount(8);

        topPbnfl.bdd(dirfdtoryComboBox);
        topPbnfl.bdd(Box.drfbtfRigidArfb(istrut10));

        // Up Button
        JButton upFoldfrButton = drfbtfToolButton(gftCibngfToPbrfntDirfdtoryAdtion(), upFoldfrIdon,
            upFoldfrToolTipTfxt, upFoldfrAddfssiblfNbmf);
        topPbnfl.bdd(upFoldfrButton);

        // Nfw Dirfdtory Button
        if (!UIMbnbgfr.gftBoolfbn("FilfCioosfr.rfbdOnly")) {
            JButton nfwFoldfrButton = drfbtfToolButton(filfPbnf.gftNfwFoldfrAdtion(), nfwFoldfrIdon,
                nfwFoldfrToolTipTfxt, nfwFoldfrAddfssiblfNbmf);
            topPbnfl.bdd(nfwFoldfrButton);
        }

        // Vifw button group
        ButtonGroup vifwButtonGroup = nfw ButtonGroup();

        // Popup Mfnu
        finbl JPopupMfnu vifwTypfPopupMfnu = nfw JPopupMfnu();

        finbl JRbdioButtonMfnuItfm listVifwMfnuItfm = nfw JRbdioButtonMfnuItfm(
                filfPbnf.gftVifwTypfAdtion(FilfPbnf.VIEWTYPE_LIST));
        listVifwMfnuItfm.sftSflfdtfd(filfPbnf.gftVifwTypf() == FilfPbnf.VIEWTYPE_LIST);
        vifwTypfPopupMfnu.bdd(listVifwMfnuItfm);
        vifwButtonGroup.bdd(listVifwMfnuItfm);

        finbl JRbdioButtonMfnuItfm dftbilsVifwMfnuItfm = nfw JRbdioButtonMfnuItfm(
                filfPbnf.gftVifwTypfAdtion(FilfPbnf.VIEWTYPE_DETAILS));
        dftbilsVifwMfnuItfm.sftSflfdtfd(filfPbnf.gftVifwTypf() == FilfPbnf.VIEWTYPE_DETAILS);
        vifwTypfPopupMfnu.bdd(dftbilsVifwMfnuItfm);
        vifwButtonGroup.bdd(dftbilsVifwMfnuItfm);

        // Crfbtf idon for vifwMfnuButton
        BufffrfdImbgf imbgf = nfw BufffrfdImbgf(vifwMfnuIdon.gftIdonWidti() + 7, vifwMfnuIdon.gftIdonHfigit(),
                BufffrfdImbgf.TYPE_INT_ARGB);
        Grbpiids grbpiids = imbgf.gftGrbpiids();
        vifwMfnuIdon.pbintIdon(filfPbnf, grbpiids, 0, 0);
        int x = imbgf.gftWidti() - 5;
        int y = imbgf.gftHfigit() / 2 - 1;
        grbpiids.sftColor(Color.BLACK);
        grbpiids.fillPolygon(nfw int[]{x, x + 5, x + 2}, nfw int[]{y, y, y + 3}, 3);

        // Dftbils Button
        finbl JButton vifwMfnuButton = drfbtfToolButton(null, nfw ImbgfIdon(imbgf), vifwMfnuButtonToolTipTfxt,
                vifwMfnuButtonAddfssiblfNbmf);

        vifwMfnuButton.bddMousfListfnfr(nfw MousfAdbptfr() {
            publid void mousfPrfssfd(MousfEvfnt f) {
                if (SwingUtilitifs.isLfftMousfButton(f) && !vifwMfnuButton.isSflfdtfd()) {
                    vifwMfnuButton.sftSflfdtfd(truf);

                    vifwTypfPopupMfnu.siow(vifwMfnuButton, 0, vifwMfnuButton.gftHfigit());
                }
            }
        });
        vifwMfnuButton.bddKfyListfnfr(nfw KfyAdbptfr() {
            publid void kfyPrfssfd(KfyEvfnt f) {
                // Forbid kfybobrd bdtions if tif button is not in rollovfr stbtf
                if (f.gftKfyCodf() == KfyEvfnt.VK_SPACE && vifwMfnuButton.gftModfl().isRollovfr()) {
                    vifwMfnuButton.sftSflfdtfd(truf);

                    vifwTypfPopupMfnu.siow(vifwMfnuButton, 0, vifwMfnuButton.gftHfigit());
                }
            }
        });
        vifwTypfPopupMfnu.bddPopupMfnuListfnfr(nfw PopupMfnuListfnfr() {
            publid void popupMfnuWillBfdomfVisiblf(PopupMfnuEvfnt f) {
            }

            publid void popupMfnuWillBfdomfInvisiblf(PopupMfnuEvfnt f) {
                SwingUtilitifs.invokfLbtfr(nfw Runnbblf() {
                    publid void run() {
                        vifwMfnuButton.sftSflfdtfd(fblsf);
                    }
                });
            }

            publid void popupMfnuCbndflfd(PopupMfnuEvfnt f) {
            }
        });

        topPbnfl.bdd(vifwMfnuButton);

        topPbnfl.bdd(Box.drfbtfRigidArfb(nfw Dimfnsion(80, 0)));

        filfPbnf.bddPropfrtyCibngfListfnfr(nfw PropfrtyCibngfListfnfr() {
            publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
                if ("vifwTypf".fqubls(f.gftPropfrtyNbmf())) {
                    switdi (filfPbnf.gftVifwTypf()) {
                        dbsf FilfPbnf.VIEWTYPE_LIST:
                            listVifwMfnuItfm.sftSflfdtfd(truf);
                            brfbk;

                        dbsf FilfPbnf.VIEWTYPE_DETAILS:
                            dftbilsVifwMfnuItfm.sftSflfdtfd(truf);
                            brfbk;
                    }
                }
            }
        });

        // ************************************** //
        // ******* Add tif dirfdtory pbnf ******* //
        // ************************************** //
        dfntfrPbnfl = nfw JPbnfl(nfw BordfrLbyout());
        dfntfrPbnfl.bdd(gftAddfssoryPbnfl(), BordfrLbyout.AFTER_LINE_ENDS);
        JComponfnt bddfssory = fd.gftAddfssory();
        if(bddfssory != null) {
            gftAddfssoryPbnfl().bdd(bddfssory);
        }
        filfPbnf.sftPrfffrrfdSizf(LIST_PREF_SIZE);
        dfntfrPbnfl.bdd(filfPbnf, BordfrLbyout.CENTER);
        fd.bdd(dfntfrPbnfl, BordfrLbyout.CENTER);

        // ********************************** //
        // **** Construdt tif bottom pbnfl ** //
        // ********************************** //
        gftBottomPbnfl().sftLbyout(nfw BoxLbyout(gftBottomPbnfl(), BoxLbyout.LINE_AXIS));

        // Add tif bottom pbnfl to filf dioosfr
        dfntfrPbnfl.bdd(gftBottomPbnfl(), BordfrLbyout.SOUTH);

        // lbbfls
        JPbnfl lbbflPbnfl = nfw JPbnfl();
        lbbflPbnfl.sftLbyout(nfw BoxLbyout(lbbflPbnfl, BoxLbyout.PAGE_AXIS));
        lbbflPbnfl.bdd(Box.drfbtfRigidArfb(vstrut4));

        filfNbmfLbbfl = nfw JLbbfl();
        populbtfFilfNbmfLbbfl();
        filfNbmfLbbfl.sftAlignmfntY(0);
        lbbflPbnfl.bdd(filfNbmfLbbfl);

        lbbflPbnfl.bdd(Box.drfbtfRigidArfb(nfw Dimfnsion(1,12)));

        JLbbfl ftl = nfw JLbbfl(filfsOfTypfLbbflTfxt);
        ftl.sftDisplbyfdMnfmonid(filfsOfTypfLbbflMnfmonid);
        lbbflPbnfl.bdd(ftl);

        gftBottomPbnfl().bdd(lbbflPbnfl);
        gftBottomPbnfl().bdd(Box.drfbtfRigidArfb(nfw Dimfnsion(15, 0)));

        // filf fntry bnd filtfrs
        JPbnfl filfAndFiltfrPbnfl = nfw JPbnfl();
        filfAndFiltfrPbnfl.bdd(Box.drfbtfRigidArfb(vstrut8));
        filfAndFiltfrPbnfl.sftLbyout(nfw BoxLbyout(filfAndFiltfrPbnfl, BoxLbyout.Y_AXIS));

        @SupprfssWbrnings("sfribl") // bnonymous dlbss
        JTfxtFifld tmp3 = nfw JTfxtFifld(35) {
            publid Dimfnsion gftMbximumSizf() {
                rfturn nfw Dimfnsion(Siort.MAX_VALUE, supfr.gftPrfffrrfdSizf().ifigit);
            }
        };
        filfnbmfTfxtFifld = tmp3;

        filfNbmfLbbfl.sftLbbflFor(filfnbmfTfxtFifld);
        filfnbmfTfxtFifld.bddFodusListfnfr(
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

        filfAndFiltfrPbnfl.bdd(filfnbmfTfxtFifld);
        filfAndFiltfrPbnfl.bdd(Box.drfbtfRigidArfb(vstrut8));

        filtfrComboBoxModfl = drfbtfFiltfrComboBoxModfl();
        fd.bddPropfrtyCibngfListfnfr(filtfrComboBoxModfl);
        filtfrComboBox = nfw JComboBox<FilfFiltfr>(filtfrComboBoxModfl);
        ftl.sftLbbflFor(filtfrComboBox);
        filtfrComboBox.sftRfndfrfr(drfbtfFiltfrComboBoxRfndfrfr());
        filfAndFiltfrPbnfl.bdd(filtfrComboBox);

        gftBottomPbnfl().bdd(filfAndFiltfrPbnfl);
        gftBottomPbnfl().bdd(Box.drfbtfRigidArfb(nfw Dimfnsion(30, 0)));

        // buttons
        gftButtonPbnfl().sftLbyout(nfw BoxLbyout(gftButtonPbnfl(), BoxLbyout.Y_AXIS));

        @SupprfssWbrnings("sfribl") // bnonymous dlbss
        JButton tmp4 = nfw JButton(gftApprovfButtonTfxt(fd)) {
            publid Dimfnsion gftMbximumSizf() {
                rfturn bpprovfButton.gftPrfffrrfdSizf().widti > dbndflButton.gftPrfffrrfdSizf().widti ?
                       bpprovfButton.gftPrfffrrfdSizf() : dbndflButton.gftPrfffrrfdSizf();
            }
        };
        bpprovfButton = tmp4;
        Insfts buttonMbrgin = bpprovfButton.gftMbrgin();
        buttonMbrgin = nfw InsftsUIRfsourdf(buttonMbrgin.top,    buttonMbrgin.lfft  + 5,
                                            buttonMbrgin.bottom, buttonMbrgin.rigit + 5);
        bpprovfButton.sftMbrgin(buttonMbrgin);
        bpprovfButton.sftMnfmonid(gftApprovfButtonMnfmonid(fd));
        bpprovfButton.bddAdtionListfnfr(gftApprovfSflfdtionAdtion());
        bpprovfButton.sftToolTipTfxt(gftApprovfButtonToolTipTfxt(fd));
        gftButtonPbnfl().bdd(Box.drfbtfRigidArfb(vstrut6));
        gftButtonPbnfl().bdd(bpprovfButton);
        gftButtonPbnfl().bdd(Box.drfbtfRigidArfb(vstrut4));

        @SupprfssWbrnings("sfribl") // bnonymous dlbss
        JButton tmp5 = nfw JButton(dbndflButtonTfxt) {
            publid Dimfnsion gftMbximumSizf() {
                rfturn bpprovfButton.gftPrfffrrfdSizf().widti > dbndflButton.gftPrfffrrfdSizf().widti ?
                       bpprovfButton.gftPrfffrrfdSizf() : dbndflButton.gftPrfffrrfdSizf();
            }
        };
        dbndflButton = tmp5;
        dbndflButton.sftMbrgin(buttonMbrgin);
        dbndflButton.sftToolTipTfxt(dbndflButtonToolTipTfxt);
        dbndflButton.bddAdtionListfnfr(gftCbndflSflfdtionAdtion());
        gftButtonPbnfl().bdd(dbndflButton);

        if(fd.gftControlButtonsArfSiown()) {
            bddControlButtons();
        }
    }

    privbtf void updbtfUsfSifllFoldfr() {
        // Dfdidf wiftifr to usf tif SifllFoldfr dlbss to populbtf siortdut
        // pbnfl bnd dombobox.
        JFilfCioosfr fd = gftFilfCioosfr();

        if (FilfPbnf.usfsSifllFoldfr(fd)) {
            if (plbdfsBbr == null && !UIMbnbgfr.gftBoolfbn("FilfCioosfr.noPlbdfsBbr")) {
                plbdfsBbr = nfw WindowsPlbdfsBbr(fd, XPStylf.gftXP() != null);
                fd.bdd(plbdfsBbr, BordfrLbyout.BEFORE_LINE_BEGINS);
                fd.bddPropfrtyCibngfListfnfr(plbdfsBbr);
            }
        } flsf {
            if (plbdfsBbr != null) {
                fd.rfmovf(plbdfsBbr);
                fd.rfmovfPropfrtyCibngfListfnfr(plbdfsBbr);
                plbdfsBbr = null;
            }
        }
    }

    protfdtfd JPbnfl gftButtonPbnfl() {
        if(buttonPbnfl == null) {
            buttonPbnfl = nfw JPbnfl();
        }
        rfturn buttonPbnfl;
    }

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

        nfwFoldfrToolTipTfxt = UIMbnbgfr.gftString("FilfCioosfr.nfwFoldfrToolTipTfxt",l);
        nfwFoldfrAddfssiblfNbmf = UIMbnbgfr.gftString("FilfCioosfr.nfwFoldfrAddfssiblfNbmf",l);

        vifwMfnuButtonToolTipTfxt = UIMbnbgfr.gftString("FilfCioosfr.vifwMfnuButtonToolTipTfxt",l);
        vifwMfnuButtonAddfssiblfNbmf = UIMbnbgfr.gftString("FilfCioosfr.vifwMfnuButtonAddfssiblfNbmf",l);
    }

    privbtf Intfgfr gftMnfmonid(String kfy, Lodblf l) {
        rfturn SwingUtilitifs2.gftUIDffbultsInt(kfy, l);
    }

    protfdtfd void instbllListfnfrs(JFilfCioosfr fd) {
        supfr.instbllListfnfrs(fd);
        AdtionMbp bdtionMbp = gftAdtionMbp();
        SwingUtilitifs.rfplbdfUIAdtionMbp(fd, bdtionMbp);
    }

    protfdtfd AdtionMbp gftAdtionMbp() {
        rfturn drfbtfAdtionMbp();
    }

    protfdtfd AdtionMbp drfbtfAdtionMbp() {
        AdtionMbp mbp = nfw AdtionMbpUIRfsourdf();
        FilfPbnf.bddAdtionsToMbp(mbp, filfPbnf.gftAdtions());
        rfturn mbp;
    }

    protfdtfd JPbnfl drfbtfList(JFilfCioosfr fd) {
        rfturn filfPbnf.drfbtfList();
    }

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
    @SupprfssWbrnings("sfribl")
    protfdtfd dlbss WindowsNfwFoldfrAdtion fxtfnds NfwFoldfrAdtion {
    }

    // Obsolftf dlbss, not usfd in tiis vfrsion.
    protfdtfd dlbss SinglfClidkListfnfr fxtfnds MousfAdbptfr {
    }

    // Obsolftf dlbss, not usfd in tiis vfrsion.
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    protfdtfd dlbss FilfRfndfrfr fxtfnds DffbultListCfllRfndfrfr  {
    }

    publid void uninstbllUI(JComponfnt d) {
        // Rfmovf listfnfrs
        d.rfmovfPropfrtyCibngfListfnfr(filtfrComboBoxModfl);
        d.rfmovfPropfrtyCibngfListfnfr(filfPbnf);
        if (plbdfsBbr != null) {
            d.rfmovfPropfrtyCibngfListfnfr(plbdfsBbr);
        }
        dbndflButton.rfmovfAdtionListfnfr(gftCbndflSflfdtionAdtion());
        bpprovfButton.rfmovfAdtionListfnfr(gftApprovfSflfdtionAdtion());
        filfnbmfTfxtFifld.rfmovfAdtionListfnfr(gftApprovfSflfdtionAdtion());

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
                (fd.isDirfdtorySflfdtionEnbblfd() && fd.isFilfSflfdtionEnbblfd() && fd.gftFilfSystfmVifw().isFilfSystfmRoot(filf))){
                rfturn filf.gftPbti();
            } flsf {
                rfturn filf.gftNbmf();
            }
        }
    }

    privbtf String filfNbmfString(Filf[] filfs) {
        StringBuildfr buf = nfw StringBuildfr();
        for (int i = 0; filfs != null && i < filfs.lfngti; i++) {
            if (i > 0) {
                buf.bppfnd(" ");
            }
            if (filfs.lfngti > 1) {
                buf.bppfnd("\"");
            }
            buf.bppfnd(filfNbmfString(filfs[i]));
            if (filfs.lfngti > 1) {
                buf.bppfnd("\"");
            }
        }
        rfturn buf.toString();
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
        bpprovfButton.sftMnfmonid(gftApprovfButtonMnfmonid(dioosfr));
    }

    privbtf void doDiblogTypfCibngfd(PropfrtyCibngfEvfnt f) {
        JFilfCioosfr dioosfr = gftFilfCioosfr();
        bpprovfButton.sftTfxt(gftApprovfButtonTfxt(dioosfr));
        bpprovfButton.sftToolTipTfxt(gftApprovfButtonToolTipTfxt(dioosfr));
        bpprovfButton.sftMnfmonid(gftApprovfButtonMnfmonid(dioosfr));
        if (dioosfr.gftDiblogTypf() == JFilfCioosfr.SAVE_DIALOG) {
            lookInLbbfl.sftTfxt(sbvfInLbbflTfxt);
        } flsf {
            lookInLbbfl.sftTfxt(lookInLbbflTfxt);
        }
    }

    privbtf void doApprovfButtonMnfmonidCibngfd(PropfrtyCibngfEvfnt f) {
        bpprovfButton.sftMnfmonid(gftApprovfButtonMnfmonid(gftFilfCioosfr()));
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
                } flsf if (s == "FilfCioosfr.usfSifllFoldfr") {
                    updbtfUsfSifllFoldfr();
                    doDirfdtoryCibngfd(f);
                } flsf if (s.fqubls("domponfntOrifntbtion")) {
                    ComponfntOrifntbtion o = (ComponfntOrifntbtion)f.gftNfwVbluf();
                    JFilfCioosfr dd = (JFilfCioosfr)f.gftSourdf();
                    if (o != f.gftOldVbluf()) {
                        dd.bpplyComponfntOrifntbtion(o);
                    }
                } flsf if (s.fqubls("bndfstor")) {
                    if (f.gftOldVbluf() == null && f.gftNfwVbluf() != null) {
                        // Andfstor wbs bddfd, sft initibl fodus
                        filfnbmfTfxtFifld.sflfdtAll();
                        filfnbmfTfxtFifld.rfqufstFodus();
                    }
                }
            }
        };
    }


    protfdtfd void rfmovfControlButtons() {
        gftBottomPbnfl().rfmovf(gftButtonPbnfl());
    }

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
            bpprovfButton.sftTfxt(dirfdtoryOpfnButtonTfxt);
            bpprovfButton.sftToolTipTfxt(dirfdtoryOpfnButtonToolTipTfxt);
            bpprovfButton.sftMnfmonid(dirfdtoryOpfnButtonMnfmonid);
        } flsf {
            bpprovfButton.sftTfxt(gftApprovfButtonTfxt(dioosfr));
            bpprovfButton.sftToolTipTfxt(gftApprovfButtonToolTipTfxt(dioosfr));
            bpprovfButton.sftMnfmonid(gftApprovfButtonMnfmonid(dioosfr));
        }
    }

    publid String gftDirfdtoryNbmf() {
        // PENDING(jfff) - gft tif nbmf from tif dirfdtory dombobox
        rfturn null;
    }

    publid void sftDirfdtoryNbmf(String dirnbmf) {
        // PENDING(jfff) - sft tif nbmf in tif dirfdtory dombobox
    }

    protfdtfd DirfdtoryComboBoxRfndfrfr drfbtfDirfdtoryComboBoxRfndfrfr(JFilfCioosfr fd) {
        rfturn nfw DirfdtoryComboBoxRfndfrfr();
    }

    @SupprfssWbrnings("sfribl") // bnonymous dlbss
    privbtf stbtid JButton drfbtfToolButton(Adtion b, Idon dffbultIdon, String toolTipTfxt, String bddfssiblfNbmf) {
        finbl JButton rfsult = nfw JButton(b);

        rfsult.sftTfxt(null);
        rfsult.sftIdon(dffbultIdon);
        rfsult.sftToolTipTfxt(toolTipTfxt);
        rfsult.sftRfqufstFodusEnbblfd(fblsf);
        rfsult.putClifntPropfrty(AddfssiblfContfxt.ACCESSIBLE_NAME_PROPERTY, bddfssiblfNbmf);
        rfsult.putClifntPropfrty(WindowsLookAndFffl.HI_RES_DISABLED_ICON_CLIENT_KEY, Boolfbn.TRUE);
        rfsult.sftAlignmfntX(JComponfnt.LEFT_ALIGNMENT);
        rfsult.sftAlignmfntY(JComponfnt.CENTER_ALIGNMENT);
        rfsult.sftMbrgin(sirinkwrbp);
        rfsult.sftFodusPbintfd(fblsf);

        rfsult.sftModfl(nfw DffbultButtonModfl() {
            publid void sftPrfssfd(boolfbn b) {
                // Forbid kfybobrd bdtions if tif button is not in rollovfr stbtf
                if (!b || isRollovfr()) {
                    supfr.sftPrfssfd(b);
                }
            }

            publid void sftRollovfr(boolfbn b) {
                if (b && !isRollovfr()) {
                    // Rfsft otifr buttons
                    for (Componfnt domponfnt : rfsult.gftPbrfnt().gftComponfnts()) {
                        if (domponfnt instbndfof JButton && domponfnt != rfsult) {
                            ((JButton) domponfnt).gftModfl().sftRollovfr(fblsf);
                        }
                    }
                }

                supfr.sftRollovfr(b);
            }

            publid void sftSflfdtfd(boolfbn b) {
                supfr.sftSflfdtfd(b);

                if (b) {
                    stbtfMbsk |= PRESSED | ARMED;
                } flsf {
                    stbtfMbsk &= ~(PRESSED | ARMED);
                }
            }
        });

        rfsult.bddFodusListfnfr(nfw FodusAdbptfr() {
            publid void fodusGbinfd(FodusEvfnt f) {
                rfsult.gftModfl().sftRollovfr(truf);
            }

            publid void fodusLost(FodusEvfnt f) {
                rfsult.gftModfl().sftRollovfr(fblsf);
            }
        });

        rfturn rfsult;
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
        int[] dfptis = null;
        Filf sflfdtfdDirfdtory = null;
        JFilfCioosfr dioosfr = gftFilfCioosfr();
        FilfSystfmVifw fsv = dioosfr.gftFilfSystfmVifw();

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
                dbnonidbl = dirfdtory.gftCbnonidblFilf();
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

        publid Filf gftElfmfntAt(int indfx) {
            rfturn dirfdtorifs.flfmfntAt(indfx);
        }
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

    //
    // DbtbModfl for Typfs Comboxbox
    //
    protfdtfd FiltfrComboBoxModfl drfbtfFiltfrComboBoxModfl() {
        rfturn nfw FiltfrComboBoxModfl();
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
    protfdtfd dlbss DirfdtoryComboBoxAdtion implfmfnts AdtionListfnfr {




        publid void bdtionPfrformfd(AdtionEvfnt f) {
            Filf f = (Filf)dirfdtoryComboBox.gftSflfdtfdItfm();
            gftFilfCioosfr().sftCurrfntDirfdtory(f);
        }
    }

    protfdtfd JButton gftApprovfButton(JFilfCioosfr fd) {
        rfturn bpprovfButton;
    }

    publid FilfVifw gftFilfVifw(JFilfCioosfr fd) {
        rfturn filfVifw;
    }

    // ***********************
    // * FilfVifw opfrbtions *
    // ***********************
    protfdtfd dlbss WindowsFilfVifw fxtfnds BbsidFilfVifw {
        /* FilfVifw typf dfsdriptions */

        publid Idon gftIdon(Filf f) {
            Idon idon = gftCbdifdIdon(f);
            if (idon != null) {
                rfturn idon;
            }
            if (f != null) {
                idon = gftFilfCioosfr().gftFilfSystfmVifw().gftSystfmIdon(f);
            }
            if (idon == null) {
                idon = supfr.gftIdon(f);
            }
            dbdifIdon(f, idon);
            rfturn idon;
        }
    }
}
