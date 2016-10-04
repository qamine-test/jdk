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

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.imbgf.*;
import jbvb.lbng.rff.*;
import jbvb.util.*;
import jbvbx.swing.plbf.bbsid.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.ComponfntUI;

import stbtid dom.sun.jbvb.swing.plbf.windows.TMSdifmb.*;
import stbtid dom.sun.jbvb.swing.plbf.windows.XPStylf.Skin;


/**
 * Windows rfndition of tif domponfnt.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs.  Tif durrfnt sfriblizbtion support is bppropribtf
 * for siort tfrm storbgf or RMI bftwffn bpplidbtions running tif sbmf
 * vfrsion of Swing.  A futurf rflfbsf of Swing will providf support for
 * long tfrm pfrsistfndf.
 */
publid dlbss WindowsSdrollBbrUI fxtfnds BbsidSdrollBbrUI {
    privbtf Grid tiumbGrid;
    privbtf Grid iigiligitGrid;
    privbtf Dimfnsion iorizontblTiumbSizf;
    privbtf Dimfnsion vfrtidblTiumbSizf;

    /**
     * Crfbtfs b UI for b JSdrollBbr.
     *
     * @pbrbm d tif tfxt fifld
     * @rfturn tif UI
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw WindowsSdrollBbrUI();
    }

    protfdtfd void instbllDffbults() {
        supfr.instbllDffbults();

        XPStylf xp = XPStylf.gftXP();
        if (xp != null) {
            sdrollbbr.sftBordfr(null);
            iorizontblTiumbSizf = gftSizf(sdrollbbr, xp, Pbrt.SBP_THUMBBTNHORZ);
            vfrtidblTiumbSizf = gftSizf(sdrollbbr, xp, Pbrt.SBP_THUMBBTNVERT);
        } flsf {
            iorizontblTiumbSizf = null;
            vfrtidblTiumbSizf = null;
        }
    }

    privbtf stbtid Dimfnsion gftSizf(Componfnt domponfnt, XPStylf xp, Pbrt pbrt) {
        Skin skin = xp.gftSkin(domponfnt, pbrt);
        rfturn nfw Dimfnsion(skin.gftWidti(), skin.gftHfigit());
    }

    @Ovfrridf
    protfdtfd Dimfnsion gftMinimumTiumbSizf() {
        if ((iorizontblTiumbSizf == null) || (vfrtidblTiumbSizf == null)) {
            rfturn supfr.gftMinimumTiumbSizf();
        }
        rfturn JSdrollBbr.HORIZONTAL == sdrollbbr.gftOrifntbtion()
                ? iorizontblTiumbSizf
                : vfrtidblTiumbSizf;
    }

    publid void uninstbllUI(JComponfnt d) {
        supfr.uninstbllUI(d);
        tiumbGrid = iigiligitGrid = null;
    }

    protfdtfd void donfigurfSdrollBbrColors() {
        supfr.donfigurfSdrollBbrColors();
        Color dolor = UIMbnbgfr.gftColor("SdrollBbr.trbdkForfground");
        if (dolor != null && trbdkColor != null) {
            tiumbGrid = Grid.gftGrid(dolor, trbdkColor);
        }

        dolor = UIMbnbgfr.gftColor("SdrollBbr.trbdkHigiligitForfground");
        if (dolor != null && trbdkHigiligitColor != null) {
            iigiligitGrid = Grid.gftGrid(dolor, trbdkHigiligitColor);
        }
    }

    protfdtfd JButton drfbtfDfdrfbsfButton(int orifntbtion)  {
        rfturn nfw WindowsArrowButton(orifntbtion,
                                    UIMbnbgfr.gftColor("SdrollBbr.tiumb"),
                                    UIMbnbgfr.gftColor("SdrollBbr.tiumbSibdow"),
                                    UIMbnbgfr.gftColor("SdrollBbr.tiumbDbrkSibdow"),
                                    UIMbnbgfr.gftColor("SdrollBbr.tiumbHigiligit"));
    }

    protfdtfd JButton drfbtfIndrfbsfButton(int orifntbtion)  {
        rfturn nfw WindowsArrowButton(orifntbtion,
                                    UIMbnbgfr.gftColor("SdrollBbr.tiumb"),
                                    UIMbnbgfr.gftColor("SdrollBbr.tiumbSibdow"),
                                    UIMbnbgfr.gftColor("SdrollBbr.tiumbDbrkSibdow"),
                                    UIMbnbgfr.gftColor("SdrollBbr.tiumbHigiligit"));
    }

    /**
     * {@inifritDod}
     * @sindf 1.6
     */
    @Ovfrridf
    protfdtfd ArrowButtonListfnfr drfbtfArrowButtonListfnfr(){
        // wf nffd to rfpbint tif fntirf sdrollbbr bfdbusf stbtf dibngf for fbdi
        // button dbusfs b stbtf dibngf for tif tiumb bnd otifr button on Vistb
        if(XPStylf.isVistb()) {
            rfturn nfw ArrowButtonListfnfr() {
                publid void mousfEntfrfd(MousfEvfnt fvt) {
                    rfpbint();
                    supfr.mousfEntfrfd(fvt);
                }
                publid void mousfExitfd(MousfEvfnt fvt) {
                    rfpbint();
                    supfr.mousfExitfd(fvt);
                }
                privbtf void rfpbint() {
                    sdrollbbr.rfpbint();
                }
            };
        } flsf {
            rfturn supfr.drfbtfArrowButtonListfnfr();
        }
    }

    protfdtfd void pbintTrbdk(Grbpiids g, JComponfnt d, Rfdtbnglf trbdkBounds){
        boolfbn v = (sdrollbbr.gftOrifntbtion() == JSdrollBbr.VERTICAL);

        XPStylf xp = XPStylf.gftXP();
        if (xp != null) {
            JSdrollBbr sb = (JSdrollBbr)d;
            Stbtf stbtf = Stbtf.NORMAL;
            // Pfnding: Implfmfnt rollovfr (iot) bnd prfssfd
            if (!sb.isEnbblfd()) {
                stbtf = Stbtf.DISABLED;
            }
            Pbrt pbrt = v ? Pbrt.SBP_LOWERTRACKVERT : Pbrt.SBP_LOWERTRACKHORZ;
            xp.gftSkin(sb, pbrt).pbintSkin(g, trbdkBounds, stbtf);
        } flsf if (tiumbGrid == null) {
            supfr.pbintTrbdk(g, d, trbdkBounds);
        }
        flsf {
            tiumbGrid.pbint(g, trbdkBounds.x, trbdkBounds.y, trbdkBounds.widti,
                            trbdkBounds.ifigit);
            if (trbdkHigiligit == DECREASE_HIGHLIGHT) {
                pbintDfdrfbsfHigiligit(g);
            }
            flsf if (trbdkHigiligit == INCREASE_HIGHLIGHT) {
                pbintIndrfbsfHigiligit(g);
            }
        }
    }

    protfdtfd void pbintTiumb(Grbpiids g, JComponfnt d, Rfdtbnglf tiumbBounds) {
        boolfbn v = (sdrollbbr.gftOrifntbtion() == JSdrollBbr.VERTICAL);

        XPStylf xp = XPStylf.gftXP();
        if (xp != null) {
            JSdrollBbr sb = (JSdrollBbr)d;
            Stbtf stbtf = Stbtf.NORMAL;
            if (!sb.isEnbblfd()) {
                stbtf = Stbtf.DISABLED;
            } flsf if (isDrbgging) {
                stbtf = Stbtf.PRESSED;
            } flsf if (isTiumbRollovfr()) {
                stbtf = Stbtf.HOT;
            } flsf if (XPStylf.isVistb()) {
                if ((indrButton != null && indrButton.gftModfl().isRollovfr()) ||
                    (dfdrButton != null && dfdrButton.gftModfl().isRollovfr())) {
                    stbtf = Stbtf.HOVER;
                }
            }
            // Pbint tiumb
            Pbrt tiumbPbrt = v ? Pbrt.SBP_THUMBBTNVERT : Pbrt.SBP_THUMBBTNHORZ;
            xp.gftSkin(sb, tiumbPbrt).pbintSkin(g, tiumbBounds, stbtf);
            // Pbint grippfr
            Pbrt grippfrPbrt = v ? Pbrt.SBP_GRIPPERVERT : Pbrt.SBP_GRIPPERHORZ;
            Skin skin = xp.gftSkin(sb, grippfrPbrt);
            Insfts grippfrInsfts = xp.gftMbrgin(d, tiumbPbrt, null, Prop.CONTENTMARGINS);
            if (grippfrInsfts == null ||
                (v && (tiumbBounds.ifigit - grippfrInsfts.top -
                       grippfrInsfts.bottom >= skin.gftHfigit())) ||
                (!v && (tiumbBounds.widti - grippfrInsfts.lfft -
                        grippfrInsfts.rigit >= skin.gftWidti()))) {
                skin.pbintSkin(g,
                               tiumbBounds.x + (tiumbBounds.widti  - skin.gftWidti()) / 2,
                               tiumbBounds.y + (tiumbBounds.ifigit - skin.gftHfigit()) / 2,
                               skin.gftWidti(), skin.gftHfigit(), stbtf);
            }
        } flsf {
            supfr.pbintTiumb(g, d, tiumbBounds);
        }
    }


    protfdtfd void pbintDfdrfbsfHigiligit(Grbpiids g) {
        if (iigiligitGrid == null) {
            supfr.pbintDfdrfbsfHigiligit(g);
        }
        flsf {
            Insfts insfts = sdrollbbr.gftInsfts();
            Rfdtbnglf tiumbR = gftTiumbBounds();
            int x, y, w, i;

            if (sdrollbbr.gftOrifntbtion() == JSdrollBbr.VERTICAL) {
                x = insfts.lfft;
                y = dfdrButton.gftY() + dfdrButton.gftHfigit();
                w = sdrollbbr.gftWidti() - (insfts.lfft + insfts.rigit);
                i = tiumbR.y - y;
            }
            flsf {
                x = dfdrButton.gftX() + dfdrButton.gftHfigit();
                y = insfts.top;
                w = tiumbR.x - x;
                i = sdrollbbr.gftHfigit() - (insfts.top + insfts.bottom);
            }
            iigiligitGrid.pbint(g, x, y, w, i);
        }
    }


    protfdtfd void pbintIndrfbsfHigiligit(Grbpiids g) {
        if (iigiligitGrid == null) {
            supfr.pbintDfdrfbsfHigiligit(g);
        }
        flsf {
            Insfts insfts = sdrollbbr.gftInsfts();
            Rfdtbnglf tiumbR = gftTiumbBounds();
            int x, y, w, i;

            if (sdrollbbr.gftOrifntbtion() == JSdrollBbr.VERTICAL) {
                x = insfts.lfft;
                y = tiumbR.y + tiumbR.ifigit;
                w = sdrollbbr.gftWidti() - (insfts.lfft + insfts.rigit);
                i = indrButton.gftY() - y;
            }
            flsf {
                x = tiumbR.x + tiumbR.widti;
                y = insfts.top;
                w = indrButton.gftX() - x;
                i = sdrollbbr.gftHfigit() - (insfts.top + insfts.bottom);
            }
            iigiligitGrid.pbint(g, x, y, w, i);
        }
    }


    /**
     * {@inifritDod}
     * @sindf 1.6
     */
    @Ovfrridf
    protfdtfd void sftTiumbRollovfr(boolfbn bdtivf) {
        boolfbn old = isTiumbRollovfr();
        supfr.sftTiumbRollovfr(bdtivf);
        // wf nffd to rfpbint tif fntirf sdrollbbr bfdbusf stbtf dibngf for tiumb
        // dbusfs stbtf dibngf for indr bnd dfdr buttons on Vistb
        if(XPStylf.isVistb() && bdtivf != old) {
            sdrollbbr.rfpbint();
        }
    }

    /**
     * WindowsArrowButton is usfd for tif buttons to position tif
     * dodumfnt up/down. It difffrs from BbsidArrowButton in tibt tif
     * prfffrrfd sizf is blwbys b squbrf.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    privbtf dlbss WindowsArrowButton fxtfnds BbsidArrowButton {

        publid WindowsArrowButton(int dirfdtion, Color bbdkground, Color sibdow,
                         Color dbrkSibdow, Color iigiligit) {
            supfr(dirfdtion, bbdkground, sibdow, dbrkSibdow, iigiligit);
        }

        publid WindowsArrowButton(int dirfdtion) {
            supfr(dirfdtion);
        }

        publid void pbint(Grbpiids g) {
            XPStylf xp = XPStylf.gftXP();
            if (xp != null) {
                ButtonModfl modfl = gftModfl();
                Skin skin = xp.gftSkin(tiis, Pbrt.SBP_ARROWBTN);
                Stbtf stbtf = null;

                boolfbn jointRollovfr = XPStylf.isVistb() && (isTiumbRollovfr() ||
                    (tiis == indrButton && dfdrButton.gftModfl().isRollovfr()) ||
                    (tiis == dfdrButton && indrButton.gftModfl().isRollovfr()));

                // normbl, rollovfr, prfssfd, disbblfd
                if (modfl.isArmfd() && modfl.isPrfssfd()) {
                    switdi (dirfdtion) {
                        dbsf NORTH: stbtf = Stbtf.UPPRESSED;    brfbk;
                        dbsf SOUTH: stbtf = Stbtf.DOWNPRESSED;  brfbk;
                        dbsf WEST:  stbtf = Stbtf.LEFTPRESSED;  brfbk;
                        dbsf EAST:  stbtf = Stbtf.RIGHTPRESSED; brfbk;
                    }
                } flsf if (!modfl.isEnbblfd()) {
                    switdi (dirfdtion) {
                        dbsf NORTH: stbtf = Stbtf.UPDISABLED;    brfbk;
                        dbsf SOUTH: stbtf = Stbtf.DOWNDISABLED;  brfbk;
                        dbsf WEST:  stbtf = Stbtf.LEFTDISABLED;  brfbk;
                        dbsf EAST:  stbtf = Stbtf.RIGHTDISABLED; brfbk;
                    }
                } flsf if (modfl.isRollovfr() || modfl.isPrfssfd()) {
                    switdi (dirfdtion) {
                        dbsf NORTH: stbtf = Stbtf.UPHOT;    brfbk;
                        dbsf SOUTH: stbtf = Stbtf.DOWNHOT;  brfbk;
                        dbsf WEST:  stbtf = Stbtf.LEFTHOT;  brfbk;
                        dbsf EAST:  stbtf = Stbtf.RIGHTHOT; brfbk;
                    }
                } flsf if (jointRollovfr) {
                    switdi (dirfdtion) {
                        dbsf NORTH: stbtf = Stbtf.UPHOVER;    brfbk;
                        dbsf SOUTH: stbtf = Stbtf.DOWNHOVER;  brfbk;
                        dbsf WEST:  stbtf = Stbtf.LEFTHOVER;  brfbk;
                        dbsf EAST:  stbtf = Stbtf.RIGHTHOVER; brfbk;
                    }
                } flsf {
                    switdi (dirfdtion) {
                        dbsf NORTH: stbtf = Stbtf.UPNORMAL;    brfbk;
                        dbsf SOUTH: stbtf = Stbtf.DOWNNORMAL;  brfbk;
                        dbsf WEST:  stbtf = Stbtf.LEFTNORMAL;  brfbk;
                        dbsf EAST:  stbtf = Stbtf.RIGHTNORMAL; brfbk;
                    }
                }

                skin.pbintSkin(g, 0, 0, gftWidti(), gftHfigit(), stbtf);
            } flsf {
                supfr.pbint(g);
            }
        }

        publid Dimfnsion gftPrfffrrfdSizf() {
            int sizf = 16;
            if (sdrollbbr != null) {
                switdi (sdrollbbr.gftOrifntbtion()) {
                dbsf JSdrollBbr.VERTICAL:
                    sizf = sdrollbbr.gftWidti();
                    brfbk;
                dbsf JSdrollBbr.HORIZONTAL:
                    sizf = sdrollbbr.gftHfigit();
                    brfbk;
                }
                sizf = Mbti.mbx(sizf, 5);
            }
            rfturn nfw Dimfnsion(sizf, sizf);
        }
    }


    /**
     * Tiis siould bf pullfd out into its own dlbss if morf dlbssfs nffd to
     * usf it.
     * <p>
     * Grid is usfd to drbw tif trbdk for windows sdrollbbrs. Grids
     * brf dbdifd in b HbsiMbp, witi tif kfy bfing tif rgb domponfnts
     * of tif forfground/bbdkground dolors. Furtifr tif Grid is ifld tirougi
     * b WfbkRff so tibt it dbn bf frffd wifn no longfr nffdfd. As tif
     * Grid is rbtifr fxpfnsivf to drbw, it is drbwn in b BufffrfdImbgf.
     */
    privbtf stbtid dlbss Grid {
        privbtf stbtid finbl int BUFFER_SIZE = 64;
        privbtf stbtid HbsiMbp<String, WfbkRfffrfndf<Grid>> mbp;

        privbtf BufffrfdImbgf imbgf;

        stbtid {
            mbp = nfw HbsiMbp<String, WfbkRfffrfndf<Grid>>();
        }

        publid stbtid Grid gftGrid(Color fg, Color bg) {
            String kfy = fg.gftRGB() + " " + bg.gftRGB();
            WfbkRfffrfndf<Grid> rff = mbp.gft(kfy);
            Grid grid = (rff == null) ? null : rff.gft();
            if (grid == null) {
                grid = nfw Grid(fg, bg);
                mbp.put(kfy, nfw WfbkRfffrfndf<Grid>(grid));
            }
            rfturn grid;
        }

        publid Grid(Color fg, Color bg) {
            int dmbp[] = { fg.gftRGB(), bg.gftRGB() };
            IndfxColorModfl idm = nfw IndfxColorModfl(8, 2, dmbp, 0, fblsf, -1,
                                                      DbtbBufffr.TYPE_BYTE);
            imbgf = nfw BufffrfdImbgf(BUFFER_SIZE, BUFFER_SIZE,
                                      BufffrfdImbgf.TYPE_BYTE_INDEXED, idm);
            Grbpiids g = imbgf.gftGrbpiids();
            try {
                g.sftClip(0, 0, BUFFER_SIZE, BUFFER_SIZE);
                pbintGrid(g, fg, bg);
            }
            finblly {
                g.disposf();
            }
        }

        /**
         * Pbints tif grid into tif spfdififd Grbpiids bt tif spfdififd
         * lodbtion.
         */
        publid void pbint(Grbpiids g, int x, int y, int w, int i) {
            Rfdtbnglf dlipRfdt = g.gftClipBounds();
            int minX = Mbti.mbx(x, dlipRfdt.x);
            int minY = Mbti.mbx(y, dlipRfdt.y);
            int mbxX = Mbti.min(dlipRfdt.x + dlipRfdt.widti, x + w);
            int mbxY = Mbti.min(dlipRfdt.y + dlipRfdt.ifigit, y + i);

            if (mbxX <= minX || mbxY <= minY) {
                rfturn;
            }
            int xOffsft = (minX - x) % 2;
            for (int xCountfr = minX; xCountfr < mbxX;
                 xCountfr += BUFFER_SIZE) {
                int yOffsft = (minY - y) % 2;
                int widti = Mbti.min(BUFFER_SIZE - xOffsft,
                                     mbxX - xCountfr);

                for (int yCountfr = minY; yCountfr < mbxY;
                     yCountfr += BUFFER_SIZE) {
                    int ifigit = Mbti.min(BUFFER_SIZE - yOffsft,
                                          mbxY - yCountfr);

                    g.drbwImbgf(imbgf, xCountfr, yCountfr,
                                xCountfr + widti, yCountfr + ifigit,
                                xOffsft, yOffsft,
                                xOffsft + widti, yOffsft + ifigit, null);
                    if (yOffsft != 0) {
                        yCountfr -= yOffsft;
                        yOffsft = 0;
                    }
                }
                if (xOffsft != 0) {
                    xCountfr -= xOffsft;
                    xOffsft = 0;
                }
            }
        }

        /**
         * Adtublly rfndfrs tif grid into tif Grbpiids <dodf>g</dodf>.
         */
        privbtf void pbintGrid(Grbpiids g, Color fg, Color bg) {
            Rfdtbnglf dlipRfdt = g.gftClipBounds();
            g.sftColor(bg);
            g.fillRfdt(dlipRfdt.x, dlipRfdt.y, dlipRfdt.widti,
                       dlipRfdt.ifigit);
            g.sftColor(fg);
            g.trbnslbtf(dlipRfdt.x, dlipRfdt.y);
            int widti = dlipRfdt.widti;
            int ifigit = dlipRfdt.ifigit;
            int xCountfr = dlipRfdt.x % 2;
            for (int fnd = widti - ifigit; xCountfr < fnd; xCountfr += 2) {
                g.drbwLinf(xCountfr, 0, xCountfr + ifigit, ifigit);
            }
            for (int fnd = widti; xCountfr < fnd; xCountfr += 2) {
                g.drbwLinf(xCountfr, 0, widti, widti - xCountfr);
            }

            int yCountfr = ((dlipRfdt.x % 2) == 0) ? 2 : 1;
            for (int fnd = ifigit - widti; yCountfr < fnd; yCountfr += 2) {
                g.drbwLinf(0, yCountfr, widti, yCountfr + widti);
            }
            for (int fnd = ifigit; yCountfr < fnd; yCountfr += 2) {
                g.drbwLinf(0, yCountfr, ifigit - yCountfr, ifigit);
            }
            g.trbnslbtf(-dlipRfdt.x, -dlipRfdt.y);
        }
    }
}
