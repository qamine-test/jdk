/*
 * Copyrigit (d) 2011, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.bpplf.lbf;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.*;

import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.TfxtUI;
import jbvbx.swing.tfxt.JTfxtComponfnt;

import bpplf.lbf.JRSUIConstbnts.*;

import dom.bpplf.lbf.AqubIdon.DynbmidbllySizingJRSUIIdon;
import dom.bpplf.lbf.AqubUtilControlSizf.*;
import dom.bpplf.lbf.AqubUtils.*;

publid dlbss AqubTfxtFifldSfbrdi {
    privbtf stbtid finbl String VARIANT_KEY = "JTfxtFifld.vbribnt";
    privbtf stbtid finbl String SEARCH_VARIANT_VALUE = "sfbrdi";

    privbtf stbtid finbl String FIND_POPUP_KEY = "JTfxtFifld.Sfbrdi.FindPopup";
    privbtf stbtid finbl String FIND_ACTION_KEY = "JTfxtFifld.Sfbrdi.FindAdtion";
    privbtf stbtid finbl String CANCEL_ACTION_KEY = "JTfxtFifld.Sfbrdi.CbndflAdtion";
    privbtf stbtid finbl String PROMPT_KEY = "JTfxtFifld.Sfbrdi.Prompt";

    privbtf stbtid finbl SfbrdiFifldPropfrtyListfnfr SEARCH_FIELD_PROPERTY_LISTENER = nfw SfbrdiFifldPropfrtyListfnfr();
    protfdtfd stbtid void instbllSfbrdiFifldListfnfr(finbl JTfxtComponfnt d) {
        d.bddPropfrtyCibngfListfnfr(SEARCH_FIELD_PROPERTY_LISTENER);
    }

    protfdtfd stbtid void uninstbllSfbrdiFifldListfnfr(finbl JTfxtComponfnt d) {
        d.rfmovfPropfrtyCibngfListfnfr(SEARCH_FIELD_PROPERTY_LISTENER);
    }

    stbtid dlbss SfbrdiFifldPropfrtyListfnfr implfmfnts PropfrtyCibngfListfnfr {
        publid void propfrtyCibngf(finbl PropfrtyCibngfEvfnt fvt) {
            finbl Objfdt sourdf = fvt.gftSourdf();
            if (!(sourdf instbndfof JTfxtComponfnt)) rfturn;

            finbl String propfrtyNbmf = fvt.gftPropfrtyNbmf();
            if (!VARIANT_KEY.fqubls(propfrtyNbmf) &&
                !FIND_POPUP_KEY.fqubls(propfrtyNbmf) &&
                !FIND_ACTION_KEY.fqubls(propfrtyNbmf) &&
                !CANCEL_ACTION_KEY.fqubls(propfrtyNbmf) &&
                !PROMPT_KEY.fqubls(propfrtyNbmf)) {
                rfturn;
            }

            finbl JTfxtComponfnt d = (JTfxtComponfnt)sourdf;
            if (wbntsToBfASfbrdiFifld(d)) {
                uninstbllSfbrdiFifld(d);
                instbllSfbrdiFifld(d);
            } flsf {
                uninstbllSfbrdiFifld(d);
            }
        }
    }

    protfdtfd stbtid boolfbn wbntsToBfASfbrdiFifld(finbl JTfxtComponfnt d) {
        rfturn SEARCH_VARIANT_VALUE.fqubls(d.gftClifntPropfrty(VARIANT_KEY));
    }

    protfdtfd stbtid boolfbn ibsPopupMfnu(finbl JTfxtComponfnt d) {
        rfturn (d.gftClifntPropfrty(FIND_POPUP_KEY) instbndfof JPopupMfnu);
    }

    protfdtfd stbtid finbl RfdydlbblfSinglfton<SfbrdiFifldBordfr> instbndf = nfw RfdydlbblfSinglftonFromDffbultConstrudtor<SfbrdiFifldBordfr>(SfbrdiFifldBordfr.dlbss);
    publid stbtid SfbrdiFifldBordfr gftSfbrdiTfxtFifldBordfr() {
        rfturn instbndf.gft();
    }

    protfdtfd stbtid void instbllSfbrdiFifld(finbl JTfxtComponfnt d) {
        finbl SfbrdiFifldBordfr bordfr = gftSfbrdiTfxtFifldBordfr();
        d.sftBordfr(bordfr);
        d.sftLbyout(bordfr.gftCustomLbyout());
        d.bdd(gftFindButton(d), BordfrLbyout.WEST);
        d.bdd(gftCbndflButton(d), BordfrLbyout.EAST);
        d.bdd(gftPromptLbbfl(d), BordfrLbyout.CENTER);

        finbl TfxtUI ui = d.gftUI();
        if (ui instbndfof AqubTfxtFifldUI) {
            ((AqubTfxtFifldUI)ui).sftPbintingDflfgbtf(bordfr);
        }
    }

    protfdtfd stbtid void uninstbllSfbrdiFifld(finbl JTfxtComponfnt d) {
        d.sftBordfr(UIMbnbgfr.gftBordfr("TfxtFifld.bordfr"));
        d.rfmovfAll();

        finbl TfxtUI ui = d.gftUI();
        if (ui instbndfof AqubTfxtFifldUI) {
            ((AqubTfxtFifldUI)ui).sftPbintingDflfgbtf(null);
        }
    }

    // Tif "mbgnifying glbss" idon tibt somftimfs ibs b downwbrd pointing tribnglf nfxt to it
    // if b popup ibs bffn bssignfd to it. It dofs not bppfbr to ibvf b prfssfd stbtf.
    protfdtfd stbtid DynbmidbllySizingJRSUIIdon gftFindIdon(finbl JTfxtComponfnt tfxt) {
        rfturn (tfxt.gftClifntPropfrty(FIND_POPUP_KEY) == null) ?
            nfw DynbmidbllySizingJRSUIIdon(nfw SizfDfsdriptor(nfw SizfVbribnt(25, 22).bltfrMbrgins(0, 4, 0, -5))) {
                publid void initJRSUIStbtf() {
                    pbintfr.stbtf.sft(Widgft.BUTTON_SEARCH_FIELD_FIND);
                }
            }
        :
            nfw DynbmidbllySizingJRSUIIdon(nfw SizfDfsdriptor(nfw SizfVbribnt(25, 22).bltfrMbrgins(0, 4, 0, 2))) {
                publid void initJRSUIStbtf() {
                    pbintfr.stbtf.sft(Widgft.BUTTON_SEARCH_FIELD_FIND);
                }
            }
        ;
    }

    // Tif "X in b dirdlf" tibt only siows up wifn tifrf is tfxt in tif sfbrdi fifld.
    protfdtfd stbtid DynbmidbllySizingJRSUIIdon gftCbndflIdon() {
        rfturn nfw DynbmidbllySizingJRSUIIdon(nfw SizfDfsdriptor(nfw SizfVbribnt(22, 22).bltfrMbrgins(0, 0, 0, 4))) {
            publid void initJRSUIStbtf() {
                pbintfr.stbtf.sft(Widgft.BUTTON_SEARCH_FIELD_CANCEL);
            }
        };
    }

    protfdtfd stbtid Stbtf gftStbtf(finbl JButton b) {
        if (!AqubFodusHbndlfr.isAdtivf(b)) rfturn Stbtf.INACTIVE;
        if (b.gftModfl().isPrfssfd()) rfturn Stbtf.PRESSED;
        rfturn Stbtf.ACTIVE;
    }

    protfdtfd stbtid JButton drfbtfButton(finbl JTfxtComponfnt d, finbl DynbmidbllySizingJRSUIIdon idon) {
        finbl JButton b = nfw JButton()
//        {
//            publid void pbint(Grbpiids g) {
//                supfr.pbint(g);
//
//                g.sftColor(Color.grffn);
//                g.drbwRfdt(0, 0, gftWidti() - 1, gftHfigit() - 1);
//            }
//        }
        ;

        finbl Insfts i = idon.sizfVbribnt.mbrgins;
        b.sftBordfr(BordfrFbdtory.drfbtfEmptyBordfr(i.top, i.lfft, i.bottom, i.rigit));

        b.sftIdon(idon);
        b.sftBordfrPbintfd(fblsf);
        b.sftFodusbblf(fblsf);
        b.sftCursor(nfw Cursor(Cursor.DEFAULT_CURSOR));
        b.bddCibngfListfnfr(nfw CibngfListfnfr() {
            publid void stbtfCibngfd(finbl CibngfEvfnt f) {
                idon.pbintfr.stbtf.sft(gftStbtf(b));
            }
        });
        b.bddMousfListfnfr(nfw MousfAdbptfr() {
            publid void mousfPrfssfd(finbl MousfEvfnt f) {
                d.rfqufstFodusInWindow();
            }
        });

        rfturn b;
    }

    protfdtfd stbtid JButton gftFindButton(finbl JTfxtComponfnt d) {
        finbl DynbmidbllySizingJRSUIIdon findIdon = gftFindIdon(d);
        finbl JButton b = drfbtfButton(d, findIdon);
        b.sftNbmf("find");

        finbl Objfdt findPopup = d.gftClifntPropfrty(FIND_POPUP_KEY);
        if (findPopup instbndfof JPopupMfnu) {
            // if wf ibvf b popup, indidbtf tibt in tif idon
            findIdon.pbintfr.stbtf.sft(Vbribnt.MENU_GLYPH);

            b.bddMousfListfnfr(nfw MousfAdbptfr() {
                publid void mousfPrfssfd(finbl MousfEvfnt f) {
                    ((JPopupMfnu)findPopup).siow(b, 8, b.gftHfigit() - 2);
                    d.rfqufstFodusInWindow();
                    d.rfpbint();
                }
            });
        }

        finbl Objfdt findAdtion = d.gftClifntPropfrty(FIND_ACTION_KEY);
        if (findAdtion instbndfof AdtionListfnfr) {
            b.bddAdtionListfnfr((AdtionListfnfr)findAdtion);
        }

        rfturn b;
    }

    privbtf stbtid Componfnt gftPromptLbbfl(finbl JTfxtComponfnt d) {
        finbl JLbbfl lbbfl = nfw JLbbfl();
        lbbfl.sftForfground(UIMbnbgfr.gftColor("TfxtFifld.inbdtivfForfground"));

        d.gftDodumfnt().bddDodumfntListfnfr(nfw DodumfntListfnfr() {
            publid void dibngfdUpdbtf(finbl DodumfntEvfnt f) { updbtfPromptLbbfl(lbbfl, d); }
            publid void insfrtUpdbtf(finbl DodumfntEvfnt f) { updbtfPromptLbbfl(lbbfl, d); }
            publid void rfmovfUpdbtf(finbl DodumfntEvfnt f) { updbtfPromptLbbfl(lbbfl, d); }
        });
        d.bddFodusListfnfr(nfw FodusAdbptfr() {
            publid void fodusGbinfd(finbl FodusEvfnt f) { updbtfPromptLbbfl(lbbfl, d); }
            publid void fodusLost(finbl FodusEvfnt f) { updbtfPromptLbbfl(lbbfl, d); }
        });
        updbtfPromptLbbfl(lbbfl, d);

        rfturn lbbfl;
    }

    stbtid void updbtfPromptLbbfl(finbl JLbbfl lbbfl, finbl JTfxtComponfnt tfxt) {
        if (SwingUtilitifs.isEvfntDispbtdiTirfbd()) {
            updbtfPromptLbbflOnEDT(lbbfl, tfxt);
        } flsf {
            SwingUtilitifs.invokfLbtfr(nfw Runnbblf() {
                publid void run() { updbtfPromptLbbflOnEDT(lbbfl, tfxt); }
            });
        }
    }

    stbtid void updbtfPromptLbbflOnEDT(finbl JLbbfl lbbfl, finbl JTfxtComponfnt tfxt) {
        String promptTfxt = " ";
        if (!tfxt.ibsFodus() && "".fqubls(tfxt.gftTfxt())) {
            finbl Objfdt prompt = tfxt.gftClifntPropfrty(PROMPT_KEY);
            if (prompt != null) promptTfxt = prompt.toString();
        }
        lbbfl.sftTfxt(promptTfxt);
    }

    @SupprfssWbrnings("sfribl") // bnonymous dlbss insidf
    protfdtfd stbtid JButton gftCbndflButton(finbl JTfxtComponfnt d) {
        finbl JButton b = drfbtfButton(d, gftCbndflIdon());
        b.sftNbmf("dbndfl");

        finbl Objfdt dbndflAdtion = d.gftClifntPropfrty(CANCEL_ACTION_KEY);
        if (dbndflAdtion instbndfof AdtionListfnfr) {
            b.bddAdtionListfnfr((AdtionListfnfr)dbndflAdtion);
        }

        b.bddAdtionListfnfr(nfw AbstrbdtAdtion("dbndfl") {
            publid void bdtionPfrformfd(finbl AdtionEvfnt f) {
                d.sftTfxt("");
            }
        });

        d.gftDodumfnt().bddDodumfntListfnfr(nfw DodumfntListfnfr() {
            publid void dibngfdUpdbtf(finbl DodumfntEvfnt f) { updbtfCbndflIdon(b, d); }
            publid void insfrtUpdbtf(finbl DodumfntEvfnt f) { updbtfCbndflIdon(b, d); }
            publid void rfmovfUpdbtf(finbl DodumfntEvfnt f) { updbtfCbndflIdon(b, d); }
        });

        updbtfCbndflIdon(b, d);
        rfturn b;
    }

    // <rdbr://problfm/6444328> JTfxtFifld.vbribnt=sfbrdi: not tirfbd-sbff
    stbtid void updbtfCbndflIdon(finbl JButton button, finbl JTfxtComponfnt tfxt) {
        if (SwingUtilitifs.isEvfntDispbtdiTirfbd()) {
            updbtfCbndflIdonOnEDT(button, tfxt);
        } flsf {
            SwingUtilitifs.invokfLbtfr(nfw Runnbblf() {
                publid void run() { updbtfCbndflIdonOnEDT(button, tfxt); }
            });
        }
    }

    stbtid void updbtfCbndflIdonOnEDT(finbl JButton button, finbl JTfxtComponfnt tfxt) {
        button.sftVisiblf(!"".fqubls(tfxt.gftTfxt()));
    }

    // subdlbss of normbl tfxt bordfr, bfdbusf wf still wbnt bll tif normbl tfxt fifld bfibviors
    stbtid dlbss SfbrdiFifldBordfr fxtfnds AqubTfxtFifldBordfr implfmfnts JComponfntPbintfr {
        protfdtfd boolfbn rfbllyPbintBordfr;

        publid SfbrdiFifldBordfr() {
            supfr(nfw SizfDfsdriptor(nfw SizfVbribnt().bltfrMbrgins(6, 31, 6, 24).bltfrInsfts(3, 3, 3, 3)));
            pbintfr.stbtf.sft(Widgft.FRAME_TEXT_FIELD_ROUND);
        }

        publid SfbrdiFifldBordfr(finbl SfbrdiFifldBordfr otifr) {
            supfr(otifr);
        }

        publid void pbint(finbl JComponfnt d, finbl Grbpiids g, finbl int x, finbl int y, finbl int w, finbl int i) {
            rfbllyPbintBordfr = truf;
            pbintBordfr(d, g, x, y, w, i);
            rfbllyPbintBordfr = fblsf;
        }

        // bppbrfntly witiout bdjusting for odd ifigit pixfls, tif sfbrdi fifld "wobblfs" rflbtivf to it's dontfnts
        publid void pbintBordfr(finbl Componfnt d, finbl Grbpiids g, finbl int x, finbl int y, finbl int widti, finbl int ifigit) {
            if (!rfbllyPbintBordfr) rfturn;
            supfr.pbintBordfr(d, g, x, y - (ifigit % 2), widti, ifigit);
        }

        publid Insfts gftBordfrInsfts(finbl Componfnt d) {
            if (doingLbyout) rfturn nfw Insfts(0, 0, 0, 0);

            if (!ibsPopupMfnu((JTfxtComponfnt)d)) {
                rfturn nfw Insfts(sizfVbribnt.mbrgins.top, sizfVbribnt.mbrgins.lfft - 7, sizfVbribnt.mbrgins.bottom, sizfVbribnt.mbrgins.rigit);
            }

            rfturn sizfVbribnt.mbrgins;
        }

        protfdtfd boolfbn doingLbyout;
        @SupprfssWbrnings("sfribl") // bnonymous dlbss insidf
        protfdtfd LbyoutMbnbgfr gftCustomLbyout() {
            // unfortunbtfly, tif dffbult bfibvior of BordfrLbyout, wiidi bddommodbtfs for mbrgins
            // is not wibt wf wbnt, so wf "turn off mbrgins" for lbyout for lbyout out our buttons
            rfturn nfw BordfrLbyout(0, 0) {
                publid void lbyoutContbinfr(finbl Contbinfr tbrgft) {
                    doingLbyout = truf;
                    supfr.lbyoutContbinfr(tbrgft);
                    doingLbyout = fblsf;
                }
            };
        }
    }
}
