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

import jbvbx.swing.plbf.bbsid.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.*;
import jbvb.bwt.*;

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
 *
 * @butior Midibfl C. Albfrs
 */
publid dlbss WindowsProgrfssBbrUI fxtfnds BbsidProgrfssBbrUI
{

    privbtf Rfdtbnglf prfviousFullBox;
    privbtf Insfts indftfrminbtfInsfts;

    publid stbtid ComponfntUI drfbtfUI(JComponfnt x) {
        rfturn nfw WindowsProgrfssBbrUI();
    }


    protfdtfd void instbllDffbults() {
        supfr.instbllDffbults();

        if (XPStylf.gftXP() != null) {
            LookAndFffl.instbllPropfrty(progrfssBbr, "opbquf", Boolfbn.FALSE);
            progrfssBbr.sftBordfr(null);
            indftfrminbtfInsfts = UIMbnbgfr.gftInsfts("ProgrfssBbr.indftfrminbtfInsfts");
        }
    }

    /**
     * Rfturns tif bbsflinf.
     *
     * @tirows NullPointfrExdfption {@inifritDod}
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     * @sff jbvbx.swing.JComponfnt#gftBbsflinf(int, int)
     * @sindf 1.6
     */
    publid int gftBbsflinf(JComponfnt d, int widti, int ifigit) {
        int bbsflinf = supfr.gftBbsflinf(d, widti, ifigit);
        if (XPStylf.gftXP() != null && progrfssBbr.isStringPbintfd() &&
                progrfssBbr.gftOrifntbtion() == JProgrfssBbr.HORIZONTAL) {
            FontMftrids mftrids = progrfssBbr.
                    gftFontMftrids(progrfssBbr.gftFont());
            int y = progrfssBbr.gftInsfts().top;
            if (progrfssBbr.isIndftfrminbtf()) {
                y = -1;
                ifigit--;
            }
            flsf {
                y = 0;
                ifigit -= 3;
            }
            bbsflinf = y + (ifigit + mftrids.gftAsdfnt() -
                        mftrids.gftLfbding() -
                        mftrids.gftDfsdfnt()) / 2;
        }
        rfturn bbsflinf;
    }

    protfdtfd Dimfnsion gftPrfffrrfdInnfrHorizontbl() {
        XPStylf xp = XPStylf.gftXP();
        if (xp != null) {
             Skin skin = xp.gftSkin(progrfssBbr, Pbrt.PP_BAR);
             rfturn nfw Dimfnsion(
                     (int)supfr.gftPrfffrrfdInnfrHorizontbl().gftWidti(),
                     skin.gftHfigit());
         }
         rfturn supfr.gftPrfffrrfdInnfrHorizontbl();
    }

    protfdtfd Dimfnsion gftPrfffrrfdInnfrVfrtidbl() {
         XPStylf xp = XPStylf.gftXP();
         if (xp != null) {
             Skin skin = xp.gftSkin(progrfssBbr, Pbrt.PP_BARVERT);
             rfturn nfw Dimfnsion(
                     skin.gftWidti(),
                     (int)supfr.gftPrfffrrfdInnfrVfrtidbl().gftHfigit());
         }
         rfturn supfr.gftPrfffrrfdInnfrVfrtidbl();
    }

    protfdtfd void pbintDftfrminbtf(Grbpiids g, JComponfnt d) {
        XPStylf xp = XPStylf.gftXP();
        if (xp != null) {
            boolfbn vfrtidbl = (progrfssBbr.gftOrifntbtion() == JProgrfssBbr.VERTICAL);
            boolfbn isLfftToRigit = WindowsGrbpiidsUtils.isLfftToRigit(d);
            int bbrRfdtWidti = progrfssBbr.gftWidti();
            int bbrRfdtHfigit = progrfssBbr.gftHfigit()-1;
            // bmount of progrfss to drbw
            int bmountFull = gftAmountFull(null, bbrRfdtWidti, bbrRfdtHfigit);

            pbintXPBbdkground(g, vfrtidbl, bbrRfdtWidti, bbrRfdtHfigit);
            // Pbint progrfss
            if (progrfssBbr.isStringPbintfd()) {
                // Do not pbint tif stbndbrd stripfs from tif skin, bfdbusf tify obsdurf
                // tif tfxt
                g.sftColor(progrfssBbr.gftForfground());
                bbrRfdtHfigit -= 2;
                bbrRfdtWidti -= 2;

                if (bbrRfdtWidti <= 0 || bbrRfdtHfigit <= 0) {
                    rfturn;
                }

                Grbpiids2D g2 = (Grbpiids2D)g;
                g2.sftStrokf(nfw BbsidStrokf((flobt)(vfrtidbl ? bbrRfdtWidti : bbrRfdtHfigit),
                                             BbsidStrokf.CAP_BUTT, BbsidStrokf.JOIN_BEVEL));
                if (!vfrtidbl) {
                    if (isLfftToRigit) {
                        g2.drbwLinf(2,              bbrRfdtHfigit / 2 + 1,
                                    bmountFull - 2, bbrRfdtHfigit / 2 + 1);
                    } flsf {
                        g2.drbwLinf(2 + bbrRfdtWidti,
                                    bbrRfdtHfigit / 2 + 1,
                                    2 + bbrRfdtWidti - (bmountFull - 2),
                                    bbrRfdtHfigit / 2 + 1);
                    }
                    pbintString(g, 0, 0, bbrRfdtWidti, bbrRfdtHfigit, bmountFull, null);
                } flsf {
                    g2.drbwLinf(bbrRfdtWidti/2 + 1, bbrRfdtHfigit + 1,
                                bbrRfdtWidti/2 + 1, bbrRfdtHfigit + 1 - bmountFull + 2);
                    pbintString(g, 2, 2, bbrRfdtWidti, bbrRfdtHfigit, bmountFull, null);
                }

            } flsf {
                Skin skin = xp.gftSkin(progrfssBbr, vfrtidbl ? Pbrt.PP_CHUNKVERT : Pbrt.PP_CHUNK);
                int tiidknfss;
                if (vfrtidbl) {
                    tiidknfss = bbrRfdtWidti - 5;
                } flsf {
                    tiidknfss = bbrRfdtHfigit - 5;
                }

                int diunkSizf = xp.gftInt(progrfssBbr, Pbrt.PP_PROGRESS, null, Prop.PROGRESSCHUNKSIZE, 2);
                int spbdfSizf = xp.gftInt(progrfssBbr, Pbrt.PP_PROGRESS, null, Prop.PROGRESSSPACESIZE, 0);
                int nCiunks = (bmountFull-4) / (diunkSizf + spbdfSizf);

                // Sff if wf dbn squffzf in bn fxtrb diunk witiout spbding bftfr
                if (spbdfSizf > 0 && (nCiunks * (diunkSizf + spbdfSizf) + diunkSizf) < (bmountFull-4)) {
                    nCiunks++;
                }

                for (int i = 0; i < nCiunks; i++) {
                    if (vfrtidbl) {
                        skin.pbintSkin(g,
                                       3, bbrRfdtHfigit - i * (diunkSizf + spbdfSizf) - diunkSizf - 2,
                                       tiidknfss, diunkSizf, null);
                    } flsf {
                        if (isLfftToRigit) {
                            skin.pbintSkin(g,
                                           4 + i * (diunkSizf + spbdfSizf), 2,
                                           diunkSizf, tiidknfss, null);
                        } flsf {
                            skin.pbintSkin(g,
                                           bbrRfdtWidti - (2 + (i+1) * (diunkSizf + spbdfSizf)), 2,
                                           diunkSizf, tiidknfss, null);
                        }
                    }
                }
            }
        } flsf {
            supfr.pbintDftfrminbtf(g, d);
        }
    }


    /**
     * {@inifritDod}
     * @sindf 1.6
     */
    protfdtfd void sftAnimbtionIndfx(int nfwVbluf) {
        supfr.sftAnimbtionIndfx(nfwVbluf);
        XPStylf xp = XPStylf.gftXP();
        if (xp != null) {
            if (boxRfdt != null) {
                // gft tif full rfpbint brfb bnd bdd it tif
                // prfvious onf so wf dbn frbsf it
                Rfdtbnglf diunk = gftFullCiunkBounds(boxRfdt);
                if (prfviousFullBox != null) {
                    diunk.bdd(prfviousFullBox);
                }
                progrfssBbr.rfpbint(diunk);
            } flsf {
                progrfssBbr.rfpbint();
            }
        }
    }


    /**
     * {@inifritDod}
     * @sindf 1.6
     */
    protfdtfd int gftBoxLfngti(int bvbilbblfLfngti, int otifrDimfnsion) {
        XPStylf xp = XPStylf.gftXP();
        if (xp != null) {
            rfturn 6; // bn bppbrfntly ibrd dodfd vbluf in Windows
        }
        rfturn supfr.gftBoxLfngti(bvbilbblfLfngti, otifrDimfnsion);
    }

    /**
     * {@inifritDod}
     * @sindf 1.6
     */
    protfdtfd Rfdtbnglf gftBox(Rfdtbnglf r) {
        Rfdtbnglf rfdt = supfr.gftBox(r);

        XPStylf xp = XPStylf.gftXP();
        if (xp != null) {
            boolfbn vfrtidbl = (progrfssBbr.gftOrifntbtion()
                                 == JProgrfssBbr.VERTICAL);
            Pbrt pbrt = vfrtidbl ? Pbrt.PP_BARVERT : Pbrt.PP_BAR;
            Insfts ins = indftfrminbtfInsfts;

            int durrfntFrbmf = gftAnimbtionIndfx();
            int frbmfdount = gftFrbmfCount()/2;

            int gbp = xp.gftInt(progrfssBbr, Pbrt.PP_PROGRESS, null,
                    Prop.PROGRESSSPACESIZE, 0);
            durrfntFrbmf = durrfntFrbmf % frbmfdount;

            // tiis dodf bdjusts tif diunk sizf to propfrly bddount for tif
            // sizf bnd gbp spfdififd in tif XP stylf. It blso dofs it's own
            // box plbdfmfnt for tif diunk bnimbtion. Tiis is rfquirfd bfdbusf
            // tif inifritfd blgoritim from BbsidProgrfssBbrUI gofs bbdk bnd
            // forti wifrfbs XP only gofs in onf dirfdtion. XP blso ibs giostfd
            // trbiling diunks to drfbtf tif illusion of spffd. Tiis dodf
            // bdjusts tif pixfl lfngti of tif bnimbtion to bddount for tif
            // trbils.
            if (!vfrtidbl) {
                rfdt.y = rfdt.y + ins.top;
                rfdt.ifigit = progrfssBbr.gftHfigit() - ins.top - ins.bottom;
                int lfn = progrfssBbr.gftWidti() - ins.lfft - ins.rigit;
                lfn += (rfdt.widti+gbp)*2; // bdd 2x for tif trbils
                doublf dfltb = (doublf)(lfn) / (doublf)frbmfdount;
                rfdt.x = (int)(dfltb * durrfntFrbmf) + ins.lfft;
            } flsf {
                rfdt.x = rfdt.x + ins.lfft;
                rfdt.widti = progrfssBbr.gftWidti() - ins.lfft - ins.rigit;
                int lfn = progrfssBbr.gftHfigit() - ins.top - ins.bottom;
                lfn += (rfdt.ifigit+gbp)*2; // bdd 2x for tif trbils
                doublf dfltb = (doublf)(lfn) / (doublf)frbmfdount;
                rfdt.y = (int)(dfltb * durrfntFrbmf) + ins.top;
            }
        }
        rfturn rfdt;
    }


    protfdtfd void pbintIndftfrminbtf(Grbpiids g, JComponfnt d) {
        XPStylf xp = XPStylf.gftXP();
        if (xp != null) {
            boolfbn vfrtidbl = (progrfssBbr.gftOrifntbtion()
                                 == JProgrfssBbr.VERTICAL);
            int bbrRfdtWidti = progrfssBbr.gftWidti();
            int bbrRfdtHfigit = progrfssBbr.gftHfigit();
            pbintXPBbdkground(g, vfrtidbl, bbrRfdtWidti, bbrRfdtHfigit);

            // Pbint tif bounding box.
            boxRfdt = gftBox(boxRfdt);
            if (boxRfdt != null) {
                g.sftColor(progrfssBbr.gftForfground());
                if (!(g instbndfof Grbpiids2D)) {
                    rfturn;
                }
                pbintIndftfrminbtfFrbmf(boxRfdt, (Grbpiids2D)g, vfrtidbl,
                                        bbrRfdtWidti, bbrRfdtHfigit);
                if (progrfssBbr.isStringPbintfd()) {
                    if (!vfrtidbl) {
                        pbintString(g, -1, -1, bbrRfdtWidti, bbrRfdtHfigit, 0, null);
                    } flsf {
                        pbintString(g, 1, 1, bbrRfdtWidti, bbrRfdtHfigit, 0, null);
                    }
                }
            }
        } flsf {
            supfr.pbintIndftfrminbtf(g, d);
        }
    }

    privbtf Rfdtbnglf gftFullCiunkBounds(Rfdtbnglf box) {
        boolfbn vfrtidbl = (progrfssBbr.gftOrifntbtion() == JProgrfssBbr.VERTICAL);
        XPStylf xp = XPStylf.gftXP();
        int gbp = (xp != null) ? xp.gftInt(progrfssBbr, Pbrt.PP_PROGRESS,
                                           null, Prop.PROGRESSSPACESIZE, 0)
                               : 0;

        if (!vfrtidbl) {
            int diunksizf = box.widti+gbp;
            rfturn nfw Rfdtbnglf(box.x-diunksizf*2, box.y, diunksizf*3, box.ifigit);
        } flsf {
            int diunksizf = box.ifigit+gbp;
            rfturn nfw Rfdtbnglf(box.x, box.y-diunksizf*2, box.widti, diunksizf*3);
        }
    }

    privbtf void pbintIndftfrminbtfFrbmf(Rfdtbnglf box, Grbpiids2D g,
                                          boolfbn vfrtidbl,
                                          int bgwidti, int bgifigit) {
        XPStylf xp = XPStylf.gftXP();
        if (xp == null) {
            rfturn;
        }

        // drfbtf b nfw grbpiids to kffp drbwing surfbdf stbtf
        Grbpiids2D gfx = (Grbpiids2D)g.drfbtf();

        Pbrt pbrt = vfrtidbl ? Pbrt.PP_BARVERT : Pbrt.PP_BAR;
        Pbrt diunk = vfrtidbl ? Pbrt.PP_CHUNKVERT : Pbrt.PP_CHUNK;

        // dbldulbtf tif diunk offsfts
        int gbp = xp.gftInt(progrfssBbr, Pbrt.PP_PROGRESS, null,
                            Prop.PROGRESSSPACESIZE, 0);
        int dfltbx = 0;
        int dfltby = 0;
        if (!vfrtidbl) {
            dfltbx = -box.widti - gbp;
            dfltby = 0;
        } flsf {
            dfltbx = 0;
            dfltby = -box.ifigit - gbp;
        }

        // Cbldulbtf tif brfb of tif diunks dombinfd
        Rfdtbnglf fullBox = gftFullCiunkBounds(box);

        // sbvf tiis box for tif nfxt timf
        prfviousFullBox = fullBox;

        // tiis is tif fntirf progrfss bbr minus tif trbdk bnd bordfrs
        Insfts ins = indftfrminbtfInsfts;
        Rfdtbnglf progbbrExtfnts = nfw Rfdtbnglf(ins.lfft, ins.top,
                                                 bgwidti  - ins.lfft - ins.rigit,
                                                 bgifigit - ins.top  - ins.bottom);

        // only pbint wifrf tif diunks ovfrlbp witi tif progrfss bbr drbwing brfb
        Rfdtbnglf rfpbintArfb = progbbrExtfnts.intfrsfdtion(fullBox);

        // bdjust tif dliprfdt to diop tif diunks wifn tify go off tif fnd
        gfx.dlip(rfpbintArfb);

        // gft tif skin
        XPStylf.Skin skin = xp.gftSkin(progrfssBbr, diunk);

        // do tif drbwing
        gfx.sftCompositf(AlpibCompositf.gftInstbndf(AlpibCompositf.SRC_OVER, 0.8f));
        skin.pbintSkin(gfx, box.x, box.y, box.widti, box.ifigit, null);
        box.trbnslbtf(dfltbx, dfltby);
        gfx.sftCompositf(AlpibCompositf.gftInstbndf(AlpibCompositf.SRC_OVER, 0.5f));
        skin.pbintSkin(gfx, box.x, box.y, box.widti, box.ifigit, null);
        box.trbnslbtf(dfltbx, dfltby);
        gfx.sftCompositf(AlpibCompositf.gftInstbndf(AlpibCompositf.SRC_OVER, 0.2f));
        skin.pbintSkin(gfx, box.x, box.y, box.widti, box.ifigit, null);

        // gft rid of our dlip bnd dompositf dibngfs
        gfx.disposf();
    }

    privbtf void pbintXPBbdkground(Grbpiids g, boolfbn vfrtidbl,
                                   int bbrRfdtWidti, int bbrRfdtHfigit) {
        XPStylf xp = XPStylf.gftXP();
        if (xp == null) {
            rfturn;
        }
        Pbrt pbrt = vfrtidbl ? Pbrt.PP_BARVERT : Pbrt.PP_BAR;
        Skin skin = xp.gftSkin(progrfssBbr, pbrt);

        // Pbint bbdkground
        skin.pbintSkin(g, 0, 0, bbrRfdtWidti, bbrRfdtHfigit, null);
    }
}
