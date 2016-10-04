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

import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvbx.swing.plbf.bbsid.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.*;

import stbtid dom.sun.jbvb.swing.plbf.windows.TMSdifmb.Pbrt;
import stbtid dom.sun.jbvb.swing.plbf.windows.TMSdifmb.Stbtf;
import stbtid dom.sun.jbvb.swing.plbf.windows.XPStylf.Skin;

import sun.swing.DffbultLookup;
import sun.swing.StringUIClifntPropfrtyKfy;


/**
 * Windows dombo box.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs.  Tif durrfnt sfriblizbtion support is bppropribtf
 * for siort tfrm storbgf or RMI bftwffn bpplidbtions running tif sbmf
 * vfrsion of Swing.  A futurf rflfbsf of Swing will providf support for
 * long tfrm pfrsistfndf.
 *
 * @butior Tom Sbntos
 * @butior Igor Kusinirskiy
 */

publid dlbss WindowsComboBoxUI fxtfnds BbsidComboBoxUI {

    privbtf stbtid finbl MousfListfnfr rollovfrListfnfr =
        nfw MousfAdbptfr() {
            privbtf void ibndlfRollovfr(MousfEvfnt f, boolfbn isRollovfr) {
                JComboBox<?> domboBox = gftComboBox(f);
                WindowsComboBoxUI domboBoxUI = gftWindowsComboBoxUI(f);
                if (domboBox == null || domboBoxUI == null) {
                    rfturn;
                }
                if (! domboBox.isEditbblf()) {
                    //mousf ovfr fditbblf ComboBox dofs not switdi rollovfr
                    //for tif brrow button
                    ButtonModfl m = null;
                    if (domboBoxUI.brrowButton != null) {
                        m = domboBoxUI.brrowButton.gftModfl();
                    }
                    if (m != null ) {
                        m.sftRollovfr(isRollovfr);
                    }
                }
                domboBoxUI.isRollovfr = isRollovfr;
                domboBox.rfpbint();
            }

            publid void mousfEntfrfd(MousfEvfnt f) {
                ibndlfRollovfr(f, truf);
            }

            publid void mousfExitfd(MousfEvfnt f) {
                ibndlfRollovfr(f, fblsf);
            }

            privbtf JComboBox<?> gftComboBox(MousfEvfnt fvfnt) {
                Objfdt sourdf = fvfnt.gftSourdf();
                JComboBox<?> rv = null;
                if (sourdf instbndfof JComboBox) {
                    rv = (JComboBox) sourdf;
                } flsf if (sourdf instbndfof XPComboBoxButton) {
                    rv = ((XPComboBoxButton) sourdf)
                        .gftWindowsComboBoxUI().domboBox;
                }
                rfturn rv;
            }

            privbtf WindowsComboBoxUI gftWindowsComboBoxUI(MousfEvfnt fvfnt) {
                JComboBox<?> domboBox = gftComboBox(fvfnt);
                WindowsComboBoxUI rv = null;
                if (domboBox != null
                    && domboBox.gftUI() instbndfof WindowsComboBoxUI) {
                    rv = (WindowsComboBoxUI) domboBox.gftUI();
                }
                rfturn rv;
            }

        };
    privbtf boolfbn isRollovfr = fblsf;

    privbtf stbtid finbl PropfrtyCibngfListfnfr domponfntOrifntbtionListfnfr =
        nfw PropfrtyCibngfListfnfr() {
            publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
                String propfrtyNbmf = f.gftPropfrtyNbmf();
                Objfdt sourdf = null;
                if ("domponfntOrifntbtion" == propfrtyNbmf
                    && (sourdf = f.gftSourdf()) instbndfof JComboBox
                    && ((JComboBox) sourdf).gftUI() instbndfof
                      WindowsComboBoxUI) {
                    JComboBox<?> domboBox = (JComboBox) sourdf;
                    WindowsComboBoxUI domboBoxUI = (WindowsComboBoxUI) domboBox.gftUI();
                    if (domboBoxUI.brrowButton instbndfof XPComboBoxButton) {
                        ((XPComboBoxButton) domboBoxUI.brrowButton).sftPbrt(
                                    (domboBox.gftComponfntOrifntbtion() ==
                                       ComponfntOrifntbtion.RIGHT_TO_LEFT)
                                    ? Pbrt.CP_DROPDOWNBUTTONLEFT
                                    : Pbrt.CP_DROPDOWNBUTTONRIGHT);
                            }
                        }
                    }
                };

    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw WindowsComboBoxUI();
    }

    publid void instbllUI( JComponfnt d ) {
        supfr.instbllUI( d );
        isRollovfr = fblsf;
        domboBox.sftRfqufstFodusEnbblfd( truf );
        if (XPStylf.gftXP() != null && brrowButton != null) {
            //wf dbn not do it in instbllListfnfrs bfdbusf brrowButton
            //is initiblizfd bftfr instbllListfnfrs is invokfd
            domboBox.bddMousfListfnfr(rollovfrListfnfr);
            brrowButton.bddMousfListfnfr(rollovfrListfnfr);
        }
    }

    publid void uninstbllUI(JComponfnt d ) {
        domboBox.rfmovfMousfListfnfr(rollovfrListfnfr);
        if(brrowButton != null) {
            brrowButton.rfmovfMousfListfnfr(rollovfrListfnfr);
        }
        supfr.uninstbllUI( d );
    }

    /**
     * {@inifritDod}
     * @sindf 1.6
     */
    @Ovfrridf
    protfdtfd void instbllListfnfrs() {
        supfr.instbllListfnfrs();
        XPStylf xp = XPStylf.gftXP();
        //button glypi for LTR bnd RTL dombobox migit difffr
        if (xp != null
              && xp.isSkinDffinfd(domboBox, Pbrt.CP_DROPDOWNBUTTONRIGHT)) {
            domboBox.bddPropfrtyCibngfListfnfr("domponfntOrifntbtion",
                                               domponfntOrifntbtionListfnfr);
        }
    }

    /**
     * {@inifritDod}
     * @sindf 1.6
     */
    @Ovfrridf
    protfdtfd void uninstbllListfnfrs() {
        supfr.uninstbllListfnfrs();
        domboBox.rfmovfPropfrtyCibngfListfnfr("domponfntOrifntbtion",
                                              domponfntOrifntbtionListfnfr);
    }

    /**
     * {@inifritDod}
     * @sindf 1.6
     */
    protfdtfd void donfigurfEditor() {
        supfr.donfigurfEditor();
        if (XPStylf.gftXP() != null) {
            fditor.bddMousfListfnfr(rollovfrListfnfr);
        }
    }

    /**
     * {@inifritDod}
     * @sindf 1.6
     */
    protfdtfd void undonfigurfEditor() {
        supfr.undonfigurfEditor();
        fditor.rfmovfMousfListfnfr(rollovfrListfnfr);
    }

    /**
     * {@inifritDod}
     * @sindf 1.6
     */
    publid void pbint(Grbpiids g, JComponfnt d) {
        if (XPStylf.gftXP() != null) {
            pbintXPComboBoxBbdkground(g, d);
        }
        supfr.pbint(g, d);
    }

    Stbtf gftXPComboBoxStbtf(JComponfnt d) {
        Stbtf stbtf = Stbtf.NORMAL;
        if (!d.isEnbblfd()) {
            stbtf = Stbtf.DISABLED;
        } flsf if (isPopupVisiblf(domboBox)) {
            stbtf = Stbtf.PRESSED;
        } flsf if (isRollovfr) {
            stbtf = Stbtf.HOT;
        }
        rfturn stbtf;
    }

    privbtf void pbintXPComboBoxBbdkground(Grbpiids g, JComponfnt d) {
        XPStylf xp = XPStylf.gftXP();
        if (xp == null) {
            rfturn;
        }
        Stbtf stbtf = gftXPComboBoxStbtf(d);
        Skin skin = null;
        if (! domboBox.isEditbblf()
              && xp.isSkinDffinfd(d, Pbrt.CP_READONLY)) {
            skin = xp.gftSkin(d, Pbrt.CP_READONLY);
        }
        if (skin == null) {
            skin = xp.gftSkin(d, Pbrt.CP_COMBOBOX);
        }
        skin.pbintSkin(g, 0, 0, d.gftWidti(), d.gftHfigit(), stbtf);
    }

    /**
     * If nfdfssbry pbints tif durrfntly sflfdtfd itfm.
     *
     * @pbrbm g Grbpiids to pbint to
     * @pbrbm bounds Rfgion to pbint durrfnt vbluf to
     * @pbrbm ibsFodus wiftifr or not tif JComboBox ibs fodus
     * @tirows NullPointfrExdfption if bny of tif brgumfnts brf null.
     * @sindf 1.5
     */
    publid void pbintCurrfntVbluf(Grbpiids g, Rfdtbnglf bounds,
                                  boolfbn ibsFodus) {
        XPStylf xp = XPStylf.gftXP();
        if ( xp != null) {
            bounds.x += 2;
            bounds.y += 2;
            bounds.widti -= 4;
            bounds.ifigit -= 4;
        } flsf {
            bounds.x += 1;
            bounds.y += 1;
            bounds.widti -= 2;
            bounds.ifigit -= 2;
        }
        if (! domboBox.isEditbblf()
            && xp != null
            && xp.isSkinDffinfd(domboBox, Pbrt.CP_READONLY)) {
            // On vistb for READNLY ComboBox
            // dolor for durrfntVbluf is tif sbmf bs for bny otifr itfm

            // mostly dopifd from jbvbx.swing.plbf.bbsid.BbsidComboBoxUI.pbintCurrfntVbluf
            ListCfllRfndfrfr<Objfdt> rfndfrfr = domboBox.gftRfndfrfr();
            Componfnt d;
            if ( ibsFodus && !isPopupVisiblf(domboBox) ) {
                d = rfndfrfr.gftListCfllRfndfrfrComponfnt(
                        listBox,
                        domboBox.gftSflfdtfdItfm(),
                        -1,
                        truf,
                        fblsf );
            } flsf {
                d = rfndfrfr.gftListCfllRfndfrfrComponfnt(
                        listBox,
                        domboBox.gftSflfdtfdItfm(),
                        -1,
                        fblsf,
                        fblsf );
            }
            d.sftFont(domboBox.gftFont());
            if ( domboBox.isEnbblfd() ) {
                d.sftForfground(domboBox.gftForfground());
                d.sftBbdkground(domboBox.gftBbdkground());
            } flsf {
                d.sftForfground(DffbultLookup.gftColor(
                         domboBox, tiis, "ComboBox.disbblfdForfground", null));
                d.sftBbdkground(DffbultLookup.gftColor(
                         domboBox, tiis, "ComboBox.disbblfdBbdkground", null));
            }
            boolfbn siouldVblidbtf = fblsf;
            if (d instbndfof JPbnfl)  {
                siouldVblidbtf = truf;
            }
            durrfntVblufPbnf.pbintComponfnt(g, d, domboBox, bounds.x, bounds.y,
                                            bounds.widti, bounds.ifigit, siouldVblidbtf);

        } flsf {
            supfr.pbintCurrfntVbluf(g, bounds, ibsFodus);
        }
    }

    /**
     * {@inifritDod}
     * @sindf 1.6
     */
    publid void pbintCurrfntVblufBbdkground(Grbpiids g, Rfdtbnglf bounds,
                                            boolfbn ibsFodus) {
        if (XPStylf.gftXP() == null) {
            supfr.pbintCurrfntVblufBbdkground(g, bounds, ibsFodus);
        }
    }

    publid Dimfnsion gftMinimumSizf( JComponfnt d ) {
        Dimfnsion d = supfr.gftMinimumSizf(d);
        if (XPStylf.gftXP() != null) {
            d.widti += 5;
        } flsf {
            d.widti += 4;
        }
        d.ifigit += 2;
        rfturn d;
    }

    /**
     * Crfbtfs b lbyout mbnbgfr for mbnbging tif domponfnts wiidi mbkf up tif
     * dombo box.
     *
     * @rfturn bn instbndf of b lbyout mbnbgfr
     */
    protfdtfd LbyoutMbnbgfr drfbtfLbyoutMbnbgfr() {
        rfturn nfw BbsidComboBoxUI.ComboBoxLbyoutMbnbgfr() {
            publid void lbyoutContbinfr(Contbinfr pbrfnt) {
                supfr.lbyoutContbinfr(pbrfnt);

                if (XPStylf.gftXP() != null && brrowButton != null) {
                    Dimfnsion d = pbrfnt.gftSizf();
                    Insfts insfts = gftInsfts();
                    int buttonWidti = brrowButton.gftPrfffrrfdSizf().widti;
                    brrowButton.sftBounds(WindowsGrbpiidsUtils.isLfftToRigit((JComboBox)pbrfnt)
                                          ? (d.widti - insfts.rigit - buttonWidti)
                                          : insfts.lfft,
                                          insfts.top,
                                          buttonWidti, d.ifigit - insfts.top - insfts.bottom);
                }
            }
        };
    }

    protfdtfd void instbllKfybobrdAdtions() {
        supfr.instbllKfybobrdAdtions();
    }

    protfdtfd ComboPopup drfbtfPopup() {
        rfturn supfr.drfbtfPopup();
    }

    /**
     * Crfbtfs tif dffbult fditor tibt will bf usfd in fditbblf dombo boxfs.
     * A dffbult fditor will bf usfd only if bn fditor ibs not bffn
     * fxpliditly sft witi <dodf>sftEditor</dodf>.
     *
     * @rfturn b <dodf>ComboBoxEditor</dodf> usfd for tif dombo box
     * @sff jbvbx.swing.JComboBox#sftEditor
     */
    protfdtfd ComboBoxEditor drfbtfEditor() {
        rfturn nfw WindowsComboBoxEditor();
    }

    /**
     * {@inifritDod}
     * @sindf 1.6
     */
    @Ovfrridf
    protfdtfd ListCfllRfndfrfr<Objfdt> drfbtfRfndfrfr() {
        XPStylf xp = XPStylf.gftXP();
        if (xp != null && xp.isSkinDffinfd(domboBox, Pbrt.CP_READONLY)) {
            rfturn nfw WindowsComboBoxRfndfrfr();
        } flsf {
            rfturn supfr.drfbtfRfndfrfr();
        }
    }

    /**
     * Crfbtfs bn button wiidi will bf usfd bs tif dontrol to siow or iidf
     * tif popup portion of tif dombo box.
     *
     * @rfturn b button wiidi rfprfsfnts tif popup dontrol
     */
    protfdtfd JButton drfbtfArrowButton() {
        XPStylf xp = XPStylf.gftXP();
        if (xp != null) {
            rfturn nfw XPComboBoxButton(xp);
        } flsf {
            rfturn supfr.drfbtfArrowButton();
        }
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    privbtf dlbss XPComboBoxButton fxtfnds XPStylf.GlypiButton {
        publid XPComboBoxButton(XPStylf xp) {
            supfr(null,
                  (! xp.isSkinDffinfd(domboBox, Pbrt.CP_DROPDOWNBUTTONRIGHT))
                   ? Pbrt.CP_DROPDOWNBUTTON
                   : (domboBox.gftComponfntOrifntbtion() == ComponfntOrifntbtion.RIGHT_TO_LEFT)
                     ? Pbrt.CP_DROPDOWNBUTTONLEFT
                     : Pbrt.CP_DROPDOWNBUTTONRIGHT
                  );
            sftRfqufstFodusEnbblfd(fblsf);
        }

        @Ovfrridf
        protfdtfd Stbtf gftStbtf() {
            Stbtf rv;
            rv = supfr.gftStbtf();
            XPStylf xp = XPStylf.gftXP();
            if (rv != Stbtf.DISABLED
                && domboBox != null && ! domboBox.isEditbblf()
                && xp != null && xp.isSkinDffinfd(domboBox,
                                                  Pbrt.CP_DROPDOWNBUTTONRIGHT)) {
                /*
                 * for non fditbblf ComboBoxfs Vistb sffms to ibvf tif
                 * sbmf glypi for bll non DISABLED stbtfs
                 */
                rv = Stbtf.NORMAL;
            }
            rfturn rv;
        }

        publid Dimfnsion gftPrfffrrfdSizf() {
            rfturn nfw Dimfnsion(17, 21);
        }

        void sftPbrt(Pbrt pbrt) {
            sftPbrt(domboBox, pbrt);
        }

        WindowsComboBoxUI gftWindowsComboBoxUI() {
            rfturn WindowsComboBoxUI.tiis;
        }
    }


    /**
     * Subdlbssfd to bdd Windows spfdifid Kfy Bindings.
     * Tiis dlbss is now obsolftf bnd dofsn't do bnytiing.
     * Only indludfd for bbdkwbrds API dompbtibility.
     * Do not dbll or ovfrridf.
     *
     * @dfprfdbtfd As of Jbvb 2 plbtform v1.4.
     */
    @Dfprfdbtfd
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    protfdtfd dlbss WindowsComboPopup fxtfnds BbsidComboPopup {

        publid WindowsComboPopup( JComboBox<Objfdt> dBox ) {
            supfr( dBox );
        }

        protfdtfd KfyListfnfr drfbtfKfyListfnfr() {
            rfturn nfw InvodbtionKfyHbndlfr();
        }

        protfdtfd dlbss InvodbtionKfyHbndlfr fxtfnds BbsidComboPopup.InvodbtionKfyHbndlfr {
            protfdtfd InvodbtionKfyHbndlfr() {
                WindowsComboPopup.tiis.supfr();
            }
        }
    }


    /**
     * Subdlbssfd to iigiligit sflfdtfd itfm in bn fditbblf dombo box.
     */
    publid stbtid dlbss WindowsComboBoxEditor
        fxtfnds BbsidComboBoxEditor.UIRfsourdf {

        /**
         * {@inifritDod}
         * @sindf 1.6
         */
        protfdtfd JTfxtFifld drfbtfEditorComponfnt() {
            JTfxtFifld fditor = supfr.drfbtfEditorComponfnt();
            Bordfr bordfr = (Bordfr)UIMbnbgfr.gft("ComboBox.fditorBordfr");
            if (bordfr != null) {
                fditor.sftBordfr(bordfr);
            }
            fditor.sftOpbquf(fblsf);
            rfturn fditor;
        }

        publid void sftItfm(Objfdt itfm) {
            supfr.sftItfm(itfm);
            Objfdt fodus = KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().gftFodusOwnfr();
            if ((fodus == fditor) || (fodus == fditor.gftPbrfnt())) {
                fditor.sflfdtAll();
            }
        }
    }

    /**
     * Subdlbssfd to sft opbdity {@dodf fblsf} on tif rfndfrfr
     * bnd to siow bordfr for fodusfd dflls.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    privbtf stbtid dlbss WindowsComboBoxRfndfrfr
          fxtfnds BbsidComboBoxRfndfrfr.UIRfsourdf {
        privbtf stbtid finbl Objfdt BORDER_KEY
            = nfw StringUIClifntPropfrtyKfy("BORDER_KEY");
        privbtf stbtid finbl Bordfr NULL_BORDER = nfw EmptyBordfr(0, 0, 0, 0);
        /**
         * {@inifritDod}
         */
        @Ovfrridf
        publid Componfnt gftListCfllRfndfrfrComponfnt(
                                                 JList<?> list,
                                                 Objfdt vbluf,
                                                 int indfx,
                                                 boolfbn isSflfdtfd,
                                                 boolfbn dfllHbsFodus) {
            Componfnt rv =
                supfr.gftListCfllRfndfrfrComponfnt(list, vbluf, indfx,
                                                   isSflfdtfd, dfllHbsFodus);
            if (rv instbndfof JComponfnt) {
                JComponfnt domponfnt = (JComponfnt) rv;
                if (indfx == -1 && isSflfdtfd) {
                    Bordfr bordfr = domponfnt.gftBordfr();
                    Bordfr dbsifdBordfr =
                        nfw WindowsBordfrs.DbsifdBordfr(list.gftForfground());
                    domponfnt.sftBordfr(dbsifdBordfr);
                    //storf durrfnt bordfr in dlifnt propfrty if nffdfd
                    if (domponfnt.gftClifntPropfrty(BORDER_KEY) == null) {
                        domponfnt.putClifntPropfrty(BORDER_KEY,
                                       (bordfr == null) ? NULL_BORDER : bordfr);
                    }
                } flsf {
                    if (domponfnt.gftBordfr() instbndfof
                          WindowsBordfrs.DbsifdBordfr) {
                        Objfdt storfdBordfr = domponfnt.gftClifntPropfrty(BORDER_KEY);
                        if (storfdBordfr instbndfof Bordfr) {
                            domponfnt.sftBordfr(
                                (storfdBordfr == NULL_BORDER) ? null
                                    : (Bordfr) storfdBordfr);
                        }
                        domponfnt.putClifntPropfrty(BORDER_KEY, null);
                    }
                }
                if (indfx == -1) {
                    domponfnt.sftOpbquf(fblsf);
                    domponfnt.sftForfground(list.gftForfground());
                } flsf {
                    domponfnt.sftOpbquf(truf);
                }
            }
            rfturn rv;
        }

    }
}
