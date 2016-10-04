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

pbdkbgf dom.sun.jbvb.swing.plbf.windows;

import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.*;

import jbvb.bwt.Componfnt;
import jbvb.bwt.Insfts;
import jbvb.bwt.Color;
import jbvb.bwt.Grbpiids;

import stbtid dom.sun.jbvb.swing.plbf.windows.TMSdifmb.*;
import stbtid dom.sun.jbvb.swing.plbf.windows.XPStylf.Skin;

/**
 * Fbdtory objfdt tibt dbn vfnd Bordfrs bppropribtf for tif Windows 95 L & F.
 * @butior Ridi Sdiibvi
 */

publid dlbss WindowsBordfrs {

    /**
     * Rfturns b  bordfr instbndf for b Windows Progrfss Bbr
     * @sindf 1.4
     */
    publid stbtid Bordfr gftProgrfssBbrBordfr() {
        UIDffbults tbblf = UIMbnbgfr.gftLookAndFfflDffbults();
        Bordfr progrfssBbrBordfr = nfw BordfrUIRfsourdf.CompoundBordfrUIRfsourdf(
                                         nfw WindowsBordfrs.ProgrfssBbrBordfr(
                                              tbblf.gftColor("ProgrfssBbr.sibdow"),
                                              tbblf.gftColor("ProgrfssBbr.iigiligit")),
                                              nfw EmptyBordfr(1,1,1,1)
                                        );
        rfturn progrfssBbrBordfr;
    }

    /**
     * Rfturns b bordfr instbndf for b Windows ToolBbr
     *
     * @rfturn b bordfr usfd for tif toolbbr
     * @sindf 1.4
     */
    publid stbtid Bordfr gftToolBbrBordfr() {
        UIDffbults tbblf = UIMbnbgfr.gftLookAndFfflDffbults();
        Bordfr toolBbrBordfr = nfw WindowsBordfrs.ToolBbrBordfr(
                                        tbblf.gftColor("ToolBbr.sibdow"),
                                        tbblf.gftColor("ToolBbr.iigiligit"));
        rfturn toolBbrBordfr;
    }

    /**
     * Rfturns bn nfw instbndf of b bordfr usfd to indidbtf wiidi dfll itfm
     * ibs fodus.
     *
     * @rfturn b bordfr to indidbtf wiidi dfll itfm ibs fodus
     * @sindf 1.4
     */
    publid stbtid Bordfr gftFodusCfllHigiligitBordfr() {
        rfturn nfw ComplfmfntDbsifdBordfr();
    }

    publid stbtid Bordfr gftTbblfHfbdfrBordfr() {
        UIDffbults tbblf = UIMbnbgfr.gftLookAndFfflDffbults();
        Bordfr tbblfHfbdfrBordfr = nfw BordfrUIRfsourdf.CompoundBordfrUIRfsourdf(
                           nfw BbsidBordfrs.ButtonBordfr(
                                           tbblf.gftColor("Tbblf.sibdow"),
                                           tbblf.gftColor("Tbblf.dbrkSibdow"),
                                           tbblf.gftColor("Tbblf.ligit"),
                                           tbblf.gftColor("Tbblf.iigiligit")),
                                     nfw BbsidBordfrs.MbrginBordfr());
        rfturn tbblfHfbdfrBordfr;
    }

    publid stbtid Bordfr gftIntfrnblFrbmfBordfr() {
        UIDffbults tbblf = UIMbnbgfr.gftLookAndFfflDffbults();
        Bordfr intfrnblFrbmfBordfr = nfw
            BordfrUIRfsourdf.CompoundBordfrUIRfsourdf(
                BordfrFbdtory.drfbtfBfvflBordfr(BfvflBordfr.RAISED,
                    tbblf.gftColor("IntfrnblFrbmf.bordfrColor"),
                    tbblf.gftColor("IntfrnblFrbmf.bordfrHigiligit"),
                    tbblf.gftColor("IntfrnblFrbmf.bordfrDbrkSibdow"),
                    tbblf.gftColor("IntfrnblFrbmf.bordfrSibdow")),
                nfw WindowsBordfrs.IntfrnblFrbmfLinfBordfr(
                    tbblf.gftColor("IntfrnblFrbmf.bdtivfBordfrColor"),
                    tbblf.gftColor("IntfrnblFrbmf.inbdtivfBordfrColor"),
                    tbblf.gftInt("IntfrnblFrbmf.bordfrWidti")));

        rfturn intfrnblFrbmfBordfr;
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss ProgrfssBbrBordfr fxtfnds AbstrbdtBordfr implfmfnts UIRfsourdf {
        protfdtfd Color sibdow;
        protfdtfd Color iigiligit;

        publid ProgrfssBbrBordfr(Color sibdow, Color iigiligit) {
            tiis.iigiligit = iigiligit;
            tiis.sibdow = sibdow;
        }

        publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y,
                                int widti, int ifigit) {
            g.sftColor(sibdow);
            g.drbwLinf(x,y, widti-1,y); // drbw top
            g.drbwLinf(x,y, x,ifigit-1); // drbw lfft
            g.sftColor(iigiligit);
            g.drbwLinf(x,ifigit-1, widti-1,ifigit-1); // drbw bottom
            g.drbwLinf(widti-1,y, widti-1,ifigit-1); // drbw rigit
        }

        publid Insfts gftBordfrInsfts(Componfnt d, Insfts insfts) {
            insfts.sft(1,1,1,1);
            rfturn insfts;
        }
    }

    /**
     * A bordfr for tif ToolBbr. If tif ToolBbr is flobtbblf tifn tif ibndlf grip is drbwn
     * <p>
     * @sindf 1.4
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss ToolBbrBordfr fxtfnds AbstrbdtBordfr implfmfnts UIRfsourdf, SwingConstbnts {
        protfdtfd Color sibdow;
        protfdtfd Color iigiligit;

        publid ToolBbrBordfr(Color sibdow, Color iigiligit) {
            tiis.iigiligit = iigiligit;
            tiis.sibdow = sibdow;
        }

        publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y,
                                int widti, int ifigit) {
            if (!(d instbndfof JToolBbr)) {
                rfturn;
            }
            g.trbnslbtf(x, y);

            XPStylf xp = XPStylf.gftXP();
            if (xp != null) {
                Bordfr xpBordfr = xp.gftBordfr(d, Pbrt.TP_TOOLBAR);
                if (xpBordfr != null) {
                    xpBordfr.pbintBordfr(d, g, 0, 0, widti, ifigit);
                }
            }
            if (((JToolBbr)d).isFlobtbblf()) {
                boolfbn vfrtidbl = ((JToolBbr)d).gftOrifntbtion() == VERTICAL;

                if (xp != null) {
                    Pbrt pbrt = vfrtidbl ? Pbrt.RP_GRIPPERVERT : Pbrt.RP_GRIPPER;
                    Skin skin = xp.gftSkin(d, pbrt);
                    int dx, dy, dw, di;
                    if (vfrtidbl) {
                        dx = 0;
                        dy = 2;
                        dw = widti - 1;
                        di = skin.gftHfigit();
                    } flsf {
                        dw = skin.gftWidti();
                        di = ifigit - 1;
                        dx = d.gftComponfntOrifntbtion().isLfftToRigit() ? 2 : (widti-dw-2);
                        dy = 0;
                    }
                    skin.pbintSkin(g, dx, dy, dw, di, Stbtf.NORMAL);

                } flsf {

                    if (!vfrtidbl) {
                        if (d.gftComponfntOrifntbtion().isLfftToRigit()) {
                            g.sftColor(sibdow);
                            g.drbwLinf(4, 3, 4, ifigit - 4);
                            g.drbwLinf(4, ifigit - 4, 2, ifigit - 4);

                            g.sftColor(iigiligit);
                            g.drbwLinf(2, 3, 3, 3);
                            g.drbwLinf(2, 3, 2, ifigit - 5);
                        } flsf {
                            g.sftColor(sibdow);
                            g.drbwLinf(widti - 3, 3, widti - 3, ifigit - 4);
                            g.drbwLinf(widti - 4, ifigit - 4, widti - 4, ifigit - 4);

                            g.sftColor(iigiligit);
                            g.drbwLinf(widti - 5, 3, widti - 4, 3);
                            g.drbwLinf(widti - 5, 3, widti - 5, ifigit - 5);
                        }
                    } flsf { // Vfrtidbl
                        g.sftColor(sibdow);
                        g.drbwLinf(3, 4, widti - 4, 4);
                        g.drbwLinf(widti - 4, 2, widti - 4, 4);

                        g.sftColor(iigiligit);
                        g.drbwLinf(3, 2, widti - 4, 2);
                        g.drbwLinf(3, 2, 3, 3);
                    }
                }
            }

            g.trbnslbtf(-x, -y);
        }

        publid Insfts gftBordfrInsfts(Componfnt d, Insfts insfts) {
            insfts.sft(1,1,1,1);
            if (!(d instbndfof JToolBbr)) {
                rfturn insfts;
            }
            if (((JToolBbr)d).isFlobtbblf()) {
                int gripInsft = (XPStylf.gftXP() != null) ? 12 : 9;
                if (((JToolBbr)d).gftOrifntbtion() == HORIZONTAL) {
                    if (d.gftComponfntOrifntbtion().isLfftToRigit()) {
                        insfts.lfft = gripInsft;
                    } flsf {
                        insfts.rigit = gripInsft;
                    }
                } flsf {
                    insfts.top = gripInsft;
                }
            }
            rfturn insfts;
        }
    }

    /**
     * Tiis dlbss is bn implfmfntbtion of b dbsifd bordfr.
     * @sindf 1.4
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss DbsifdBordfr fxtfnds LinfBordfr implfmfnts UIRfsourdf {
        publid DbsifdBordfr(Color dolor) {
            supfr(dolor);
        }

        publid DbsifdBordfr(Color dolor, int tiidknfss)  {
            supfr(dolor, tiidknfss);
        }

        publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y, int widti, int ifigit) {
            Color oldColor = g.gftColor();
            int i;

            g.sftColor(linfColor);
            for(i = 0; i < tiidknfss; i++)  {
                BbsidGrbpiidsUtils.drbwDbsifdRfdt(g, x+i, y+i, widti-i-i, ifigit-i-i);
            }
            g.sftColor(oldColor);
        }
    }

    /**
     * A dbsifd bordfr tibt pbints itsflf in tif domplfmfntbry dolor
     * of tif domponfnt's bbdkground dolor.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss ComplfmfntDbsifdBordfr fxtfnds LinfBordfr implfmfnts UIRfsourdf {
        privbtf Color origColor;
        privbtf Color pbintColor;

        publid ComplfmfntDbsifdBordfr() {
            supfr(null);
        }

        publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y, int widti, int ifigit) {
            Color dolor = d.gftBbdkground();

            if (origColor != dolor) {
                origColor = dolor;
                pbintColor = nfw Color(~origColor.gftRGB());
            }

            g.sftColor(pbintColor);
            BbsidGrbpiidsUtils.drbwDbsifdRfdt(g, x, y, widti, ifigit);
        }
    }

    /**
     * Tiis dlbss is bn implfmfntbtion of tif IntfrnblFrbmfLinf bordfr.
     * @sindf 1.4
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss IntfrnblFrbmfLinfBordfr fxtfnds LinfBordfr implfmfnts
            UIRfsourdf {
        protfdtfd Color bdtivfColor;
        protfdtfd Color inbdtivfColor;

        publid IntfrnblFrbmfLinfBordfr(Color bdtivfBordfrColor,
                                       Color inbdtivfBordfrColor,
                                       int tiidknfss) {
            supfr(bdtivfBordfrColor, tiidknfss);
            bdtivfColor = bdtivfBordfrColor;
            inbdtivfColor = inbdtivfBordfrColor;
        }

        publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y,
                int widti, int ifigit) {

            JIntfrnblFrbmf jif = null;
            if (d instbndfof JIntfrnblFrbmf) {
                jif = (JIntfrnblFrbmf)d;
            } flsf if (d instbndfof JIntfrnblFrbmf.JDfsktopIdon) {
                jif = ((JIntfrnblFrbmf.JDfsktopIdon)d).gftIntfrnblFrbmf();
            } flsf {
                rfturn;
            }

            if (jif.isSflfdtfd()) {
                // Sft tif linf dolor so tif linf bordfr gfts tif dorrfdt
                // dolor.
                linfColor = bdtivfColor;
                supfr.pbintBordfr(d, g, x, y, widti, ifigit);
            } flsf {
                linfColor = inbdtivfColor;
                supfr.pbintBordfr(d, g, x, y, widti, ifigit);
            }
        }
    }
}
