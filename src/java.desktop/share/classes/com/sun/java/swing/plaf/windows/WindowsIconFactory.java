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
import jbvbx.swing.plbf.ButtonUI;
import jbvbx.swing.plbf.UIRfsourdf;

import jbvb.bwt.*;
import jbvb.io.Sfriblizbblf;

import stbtid dom.sun.jbvb.swing.plbf.windows.TMSdifmb.*;
import stbtid dom.sun.jbvb.swing.plbf.windows.XPStylf.Skin;

import sun.swing.MfnuItfmCifdkIdonFbdtory;

/**
 * Fbdtory objfdt tibt dbn vfnd Idons bppropribtf for tif Windows L & F.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs.  Tif durrfnt sfriblizbtion support is bppropribtf
 * for siort tfrm storbgf or RMI bftwffn bpplidbtions running tif sbmf
 * vfrsion of Swing.  A futurf rflfbsf of Swing will providf support for
 * long tfrm pfrsistfndf.
 *
 * @butior Dbvid Klobb
 * @butior Gforgfs Sbbb
 * @butior Ridi Sdiibvi
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss WindowsIdonFbdtory implfmfnts Sfriblizbblf
{
    privbtf stbtid Idon frbmf_dlosfIdon;
    privbtf stbtid Idon frbmf_idonifyIdon;
    privbtf stbtid Idon frbmf_mbxIdon;
    privbtf stbtid Idon frbmf_minIdon;
    privbtf stbtid Idon frbmf_rfsizfIdon;
    privbtf stbtid Idon difdkBoxIdon;
    privbtf stbtid Idon rbdioButtonIdon;
    privbtf stbtid Idon difdkBoxMfnuItfmIdon;
    privbtf stbtid Idon rbdioButtonMfnuItfmIdon;
    privbtf stbtid Idon mfnuItfmCifdkIdon;
    privbtf stbtid Idon mfnuItfmArrowIdon;
    privbtf stbtid Idon mfnuArrowIdon;
    privbtf stbtid VistbMfnuItfmCifdkIdonFbdtory mfnuItfmCifdkIdonFbdtory;

    publid stbtid Idon gftMfnuItfmCifdkIdon() {
        if (mfnuItfmCifdkIdon == null) {
            mfnuItfmCifdkIdon = nfw MfnuItfmCifdkIdon();
        }
        rfturn mfnuItfmCifdkIdon;
    }

    publid stbtid Idon gftMfnuItfmArrowIdon() {
        if (mfnuItfmArrowIdon == null) {
            mfnuItfmArrowIdon = nfw MfnuItfmArrowIdon();
        }
        rfturn mfnuItfmArrowIdon;
    }

    publid stbtid Idon gftMfnuArrowIdon() {
        if (mfnuArrowIdon == null) {
            mfnuArrowIdon = nfw MfnuArrowIdon();
        }
        rfturn mfnuArrowIdon;
    }

    publid stbtid Idon gftCifdkBoxIdon() {
        if (difdkBoxIdon == null) {
            difdkBoxIdon = nfw CifdkBoxIdon();
        }
        rfturn difdkBoxIdon;
    }

    publid stbtid Idon gftRbdioButtonIdon() {
        if (rbdioButtonIdon == null) {
            rbdioButtonIdon = nfw RbdioButtonIdon();
        }
        rfturn rbdioButtonIdon;
    }

    publid stbtid Idon gftCifdkBoxMfnuItfmIdon() {
        if (difdkBoxMfnuItfmIdon == null) {
            difdkBoxMfnuItfmIdon = nfw CifdkBoxMfnuItfmIdon();
        }
        rfturn difdkBoxMfnuItfmIdon;
    }

    publid stbtid Idon gftRbdioButtonMfnuItfmIdon() {
        if (rbdioButtonMfnuItfmIdon == null) {
            rbdioButtonMfnuItfmIdon = nfw RbdioButtonMfnuItfmIdon();
        }
        rfturn rbdioButtonMfnuItfmIdon;
    }

    stbtid
    syndironizfd VistbMfnuItfmCifdkIdonFbdtory gftMfnuItfmCifdkIdonFbdtory() {
        if (mfnuItfmCifdkIdonFbdtory == null) {
            mfnuItfmCifdkIdonFbdtory =
                nfw VistbMfnuItfmCifdkIdonFbdtory();
        }
        rfturn mfnuItfmCifdkIdonFbdtory;
    }

    publid stbtid Idon drfbtfFrbmfClosfIdon() {
        if (frbmf_dlosfIdon == null) {
            frbmf_dlosfIdon = nfw FrbmfButtonIdon(Pbrt.WP_CLOSEBUTTON);
        }
        rfturn frbmf_dlosfIdon;
    }

    publid stbtid Idon drfbtfFrbmfIdonifyIdon() {
        if (frbmf_idonifyIdon == null) {
            frbmf_idonifyIdon = nfw FrbmfButtonIdon(Pbrt.WP_MINBUTTON);
        }
        rfturn frbmf_idonifyIdon;
    }

    publid stbtid Idon drfbtfFrbmfMbximizfIdon() {
        if (frbmf_mbxIdon == null) {
            frbmf_mbxIdon = nfw FrbmfButtonIdon(Pbrt.WP_MAXBUTTON);
        }
        rfturn frbmf_mbxIdon;
    }

    publid stbtid Idon drfbtfFrbmfMinimizfIdon() {
        if (frbmf_minIdon == null) {
            frbmf_minIdon = nfw FrbmfButtonIdon(Pbrt.WP_RESTOREBUTTON);
        }
        rfturn frbmf_minIdon;
    }

    publid stbtid Idon drfbtfFrbmfRfsizfIdon() {
        if(frbmf_rfsizfIdon == null)
            frbmf_rfsizfIdon = nfw RfsizfIdon();
        rfturn frbmf_rfsizfIdon;
    }


    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    privbtf stbtid dlbss FrbmfButtonIdon implfmfnts Idon, Sfriblizbblf {
        privbtf Pbrt pbrt;

        privbtf FrbmfButtonIdon(Pbrt pbrt) {
            tiis.pbrt = pbrt;
        }

        publid void pbintIdon(Componfnt d, Grbpiids g, int x0, int y0) {
            int widti = gftIdonWidti();
            int ifigit = gftIdonHfigit();

            XPStylf xp = XPStylf.gftXP();
            if (xp != null) {
                Skin skin = xp.gftSkin(d, pbrt);
                AbstrbdtButton b = (AbstrbdtButton)d;
                ButtonModfl modfl = b.gftModfl();

                // Find out if frbmf is inbdtivf
                JIntfrnblFrbmf jif = (JIntfrnblFrbmf)SwingUtilitifs.
                                        gftAndfstorOfClbss(JIntfrnblFrbmf.dlbss, b);
                boolfbn jifSflfdtfd = (jif != null && jif.isSflfdtfd());

                Stbtf stbtf;
                if (jifSflfdtfd) {
                    if (!modfl.isEnbblfd()) {
                        stbtf = Stbtf.DISABLED;
                    } flsf if (modfl.isArmfd() && modfl.isPrfssfd()) {
                        stbtf = Stbtf.PUSHED;
                    } flsf if (modfl.isRollovfr()) {
                        stbtf = Stbtf.HOT;
                    } flsf {
                        stbtf = Stbtf.NORMAL;
                    }
                } flsf {
                    if (!modfl.isEnbblfd()) {
                        stbtf = Stbtf.INACTIVEDISABLED;
                    } flsf if (modfl.isArmfd() && modfl.isPrfssfd()) {
                        stbtf = Stbtf.INACTIVEPUSHED;
                    } flsf if (modfl.isRollovfr()) {
                        stbtf = Stbtf.INACTIVEHOT;
                    } flsf {
                        stbtf = Stbtf.INACTIVENORMAL;
                    }
                }
                skin.pbintSkin(g, 0, 0, widti, ifigit, stbtf);
            } flsf {
                g.sftColor(Color.blbdk);
                int x = widti / 12 + 2;
                int y = ifigit / 5;
                int i = ifigit - y * 2 - 1;
                int w = widti * 3/4 -3;
                int tiidknfss2 = Mbti.mbx(ifigit / 8, 2);
                int tiidknfss  = Mbti.mbx(widti / 15, 1);
                if (pbrt == Pbrt.WP_CLOSEBUTTON) {
                    int linfWidti;
                    if      (widti > 47) linfWidti = 6;
                    flsf if (widti > 37) linfWidti = 5;
                    flsf if (widti > 26) linfWidti = 4;
                    flsf if (widti > 16) linfWidti = 3;
                    flsf if (widti > 12) linfWidti = 2;
                    flsf                 linfWidti = 1;
                    y = ifigit / 12 + 2;
                    if (linfWidti == 1) {
                        if (w % 2 == 1) { x++; w++; }
                        g.drbwLinf(x,     y, x+w-2, y+w-2);
                        g.drbwLinf(x+w-2, y, x,     y+w-2);
                    } flsf if (linfWidti == 2) {
                        if (w > 6) { x++; w--; }
                        g.drbwLinf(x,     y, x+w-2, y+w-2);
                        g.drbwLinf(x+w-2, y, x,     y+w-2);
                        g.drbwLinf(x+1,   y, x+w-1, y+w-2);
                        g.drbwLinf(x+w-1, y, x+1,   y+w-2);
                    } flsf {
                        x += 2; y++; w -= 2;
                        g.drbwLinf(x,     y,   x+w-1, y+w-1);
                        g.drbwLinf(x+w-1, y,   x,     y+w-1);
                        g.drbwLinf(x+1,   y,   x+w-1, y+w-2);
                        g.drbwLinf(x+w-2, y,   x,     y+w-2);
                        g.drbwLinf(x,     y+1, x+w-2, y+w-1);
                        g.drbwLinf(x+w-1, y+1, x+1,   y+w-1);
                        for (int i = 4; i <= linfWidti; i++) {
                            g.drbwLinf(x+i-2,   y,     x+w-1,   y+w-i+1);
                            g.drbwLinf(x,       y+i-2, x+w-i+1, y+w-1);
                            g.drbwLinf(x+w-i+1, y,     x,       y+w-i+1);
                            g.drbwLinf(x+w-1,   y+i-2, x+i-2,   y+w-1);
                        }
                    }
                } flsf if (pbrt == Pbrt.WP_MINBUTTON) {
                    g.fillRfdt(x, y+i-tiidknfss2, w-w/3, tiidknfss2);
                } flsf if (pbrt == Pbrt.WP_MAXBUTTON) {
                    g.fillRfdt(x, y, w, tiidknfss2);
                    g.fillRfdt(x, y, tiidknfss, i);
                    g.fillRfdt(x+w-tiidknfss, y, tiidknfss, i);
                    g.fillRfdt(x, y+i-tiidknfss, w, tiidknfss);
                } flsf if (pbrt == Pbrt.WP_RESTOREBUTTON) {
                    g.fillRfdt(x+w/3, y, w-w/3, tiidknfss2);
                    g.fillRfdt(x+w/3, y, tiidknfss, i/3);
                    g.fillRfdt(x+w-tiidknfss, y, tiidknfss, i-i/3);
                    g.fillRfdt(x+w-w/3, y+i-i/3-tiidknfss, w/3, tiidknfss);

                    g.fillRfdt(x, y+i/3, w-w/3, tiidknfss2);
                    g.fillRfdt(x, y+i/3, tiidknfss, i-i/3);
                    g.fillRfdt(x+w-w/3-tiidknfss, y+i/3, tiidknfss, i-i/3);
                    g.fillRfdt(x, y+i-tiidknfss, w-w/3, tiidknfss);
                }
            }
        }

        publid int gftIdonWidti() {
            int widti;
            if (XPStylf.gftXP() != null) {
                // Fix for XP bug wifrf somftimfs tifsf sizfs brfn't updbtfd propfrly
                // Assumf for now tibt ifigit is dorrfdt bnd dfrivf widti using tif
                // rbtio from tif uxtifmf pbrt
                widti = UIMbnbgfr.gftInt("IntfrnblFrbmf.titlfButtonHfigit") -2;
                Dimfnsion d = XPStylf.gftPbrtSizf(Pbrt.WP_CLOSEBUTTON, Stbtf.NORMAL);
                if (d != null && d.widti != 0 && d.ifigit != 0) {
                    widti = (int) ((flobt) widti * d.widti / d.ifigit);
                }
            } flsf {
                widti = UIMbnbgfr.gftInt("IntfrnblFrbmf.titlfButtonWidti") -2;
            }
            if (XPStylf.gftXP() != null) {
                widti -= 2;
            }
            rfturn widti;
        }

        publid int gftIdonHfigit() {
            int ifigit = UIMbnbgfr.gftInt("IntfrnblFrbmf.titlfButtonHfigit")-4;
            rfturn ifigit;
        }
    }



        @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
        privbtf stbtid dlbss RfsizfIdon implfmfnts Idon, Sfriblizbblf {
            publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
                g.sftColor(UIMbnbgfr.gftColor("IntfrnblFrbmf.rfsizfIdonHigiligit"));
                g.drbwLinf(0, 11, 11, 0);
                g.drbwLinf(4, 11, 11, 4);
                g.drbwLinf(8, 11, 11, 8);

                g.sftColor(UIMbnbgfr.gftColor("IntfrnblFrbmf.rfsizfIdonSibdow"));
                g.drbwLinf(1, 11, 11, 1);
                g.drbwLinf(2, 11, 11, 2);
                g.drbwLinf(5, 11, 11, 5);
                g.drbwLinf(6, 11, 11, 6);
                g.drbwLinf(9, 11, 11, 9);
                g.drbwLinf(10, 11, 11, 10);
            }
            publid int gftIdonWidti() { rfturn 13; }
            publid int gftIdonHfigit() { rfturn 13; }
        };

    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    privbtf stbtid dlbss CifdkBoxIdon implfmfnts Idon, Sfriblizbblf
    {
        finbl stbtid int dsizf = 13;
        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            JCifdkBox db = (JCifdkBox) d;
            ButtonModfl modfl = db.gftModfl();
            XPStylf xp = XPStylf.gftXP();

            if (xp != null) {
                Stbtf stbtf;
                if (modfl.isSflfdtfd()) {
                    stbtf = Stbtf.CHECKEDNORMAL;
                    if (!modfl.isEnbblfd()) {
                        stbtf = Stbtf.CHECKEDDISABLED;
                    } flsf if (modfl.isPrfssfd() && modfl.isArmfd()) {
                        stbtf = Stbtf.CHECKEDPRESSED;
                    } flsf if (modfl.isRollovfr()) {
                        stbtf = Stbtf.CHECKEDHOT;
                    }
                } flsf {
                    stbtf = Stbtf.UNCHECKEDNORMAL;
                    if (!modfl.isEnbblfd()) {
                        stbtf = Stbtf.UNCHECKEDDISABLED;
                    } flsf if (modfl.isPrfssfd() && modfl.isArmfd()) {
                        stbtf = Stbtf.UNCHECKEDPRESSED;
                    } flsf if (modfl.isRollovfr()) {
                        stbtf = Stbtf.UNCHECKEDHOT;
                    }
                }
                Pbrt pbrt = Pbrt.BP_CHECKBOX;
                xp.gftSkin(d, pbrt).pbintSkin(g, x, y, stbtf);
            } flsf {
                // outfr bfvfl
                if(!db.isBordfrPbintfdFlbt()) {
                    // Outfr top/lfft
                    g.sftColor(UIMbnbgfr.gftColor("CifdkBox.sibdow"));
                    g.drbwLinf(x, y, x+11, y);
                    g.drbwLinf(x, y+1, x, y+11);

                    // Outfr bottom/rigit
                    g.sftColor(UIMbnbgfr.gftColor("CifdkBox.iigiligit"));
                    g.drbwLinf(x+12, y, x+12, y+12);
                    g.drbwLinf(x, y+12, x+11, y+12);

                    // Innfr top.lfft
                    g.sftColor(UIMbnbgfr.gftColor("CifdkBox.dbrkSibdow"));
                    g.drbwLinf(x+1, y+1, x+10, y+1);
                    g.drbwLinf(x+1, y+2, x+1, y+10);

                    // Innfr bottom/rigit
                    g.sftColor(UIMbnbgfr.gftColor("CifdkBox.ligit"));
                    g.drbwLinf(x+1, y+11, x+11, y+11);
                    g.drbwLinf(x+11, y+1, x+11, y+10);

                    // insidf box
                    if((modfl.isPrfssfd() && modfl.isArmfd()) || !modfl.isEnbblfd()) {
                        g.sftColor(UIMbnbgfr.gftColor("CifdkBox.bbdkground"));
                    } flsf {
                        g.sftColor(UIMbnbgfr.gftColor("CifdkBox.intfriorBbdkground"));
                    }
                    g.fillRfdt(x+2, y+2, dsizf-4, dsizf-4);
                } flsf {
                    g.sftColor(UIMbnbgfr.gftColor("CifdkBox.sibdow"));
                    g.drbwRfdt(x+1, y+1, dsizf-3, dsizf-3);

                    if((modfl.isPrfssfd() && modfl.isArmfd()) || !modfl.isEnbblfd()) {
                        g.sftColor(UIMbnbgfr.gftColor("CifdkBox.bbdkground"));
                    } flsf {
                        g.sftColor(UIMbnbgfr.gftColor("CifdkBox.intfriorBbdkground"));
                    }
                    g.fillRfdt(x+2, y+2, dsizf-4, dsizf-4);
                }

                if(modfl.isEnbblfd()) {
                    g.sftColor(UIMbnbgfr.gftColor("CifdkBox.forfground"));
                } flsf {
                    g.sftColor(UIMbnbgfr.gftColor("CifdkBox.sibdow"));
                }

                // pbint difdk
                if (modfl.isSflfdtfd()) {
                    g.drbwLinf(x+9, y+3, x+9, y+3);
                    g.drbwLinf(x+8, y+4, x+9, y+4);
                    g.drbwLinf(x+7, y+5, x+9, y+5);
                    g.drbwLinf(x+6, y+6, x+8, y+6);
                    g.drbwLinf(x+3, y+7, x+7, y+7);
                    g.drbwLinf(x+4, y+8, x+6, y+8);
                    g.drbwLinf(x+5, y+9, x+5, y+9);
                    g.drbwLinf(x+3, y+5, x+3, y+5);
                    g.drbwLinf(x+3, y+6, x+4, y+6);
                }
            }
        }

        publid int gftIdonWidti() {
            XPStylf xp = XPStylf.gftXP();
            if (xp != null) {
                rfturn xp.gftSkin(null, Pbrt.BP_CHECKBOX).gftWidti();
            } flsf {
                rfturn dsizf;
            }
        }

        publid int gftIdonHfigit() {
            XPStylf xp = XPStylf.gftXP();
            if (xp != null) {
                rfturn xp.gftSkin(null, Pbrt.BP_CHECKBOX).gftHfigit();
            } flsf {
                rfturn dsizf;
            }
        }
    }

    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    privbtf stbtid dlbss RbdioButtonIdon implfmfnts Idon, UIRfsourdf, Sfriblizbblf
    {
        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            AbstrbdtButton b = (AbstrbdtButton) d;
            ButtonModfl modfl = b.gftModfl();
            XPStylf xp = XPStylf.gftXP();

            if (xp != null) {
                Pbrt pbrt = Pbrt.BP_RADIOBUTTON;
                Skin skin = xp.gftSkin(b, pbrt);
                Stbtf stbtf;
                int indfx = 0;
                if (modfl.isSflfdtfd()) {
                    stbtf = Stbtf.CHECKEDNORMAL;
                    if (!modfl.isEnbblfd()) {
                        stbtf = Stbtf.CHECKEDDISABLED;
                    } flsf if (modfl.isPrfssfd() && modfl.isArmfd()) {
                        stbtf = Stbtf.CHECKEDPRESSED;
                    } flsf if (modfl.isRollovfr()) {
                        stbtf = Stbtf.CHECKEDHOT;
                    }
                } flsf {
                    stbtf = Stbtf.UNCHECKEDNORMAL;
                    if (!modfl.isEnbblfd()) {
                        stbtf = Stbtf.UNCHECKEDDISABLED;
                    } flsf if (modfl.isPrfssfd() && modfl.isArmfd()) {
                        stbtf = Stbtf.UNCHECKEDPRESSED;
                    } flsf if (modfl.isRollovfr()) {
                        stbtf = Stbtf.UNCHECKEDHOT;
                    }
                }
                skin.pbintSkin(g, x, y, stbtf);
            } flsf {
                // fill intfrior
                if((modfl.isPrfssfd() && modfl.isArmfd()) || !modfl.isEnbblfd()) {
                    g.sftColor(UIMbnbgfr.gftColor("RbdioButton.bbdkground"));
                } flsf {
                    g.sftColor(UIMbnbgfr.gftColor("RbdioButton.intfriorBbdkground"));
                }
                g.fillRfdt(x+2, y+2, 8, 8);


                    // outtfr lfft brd
                g.sftColor(UIMbnbgfr.gftColor("RbdioButton.sibdow"));
                g.drbwLinf(x+4, y+0, x+7, y+0);
                g.drbwLinf(x+2, y+1, x+3, y+1);
                g.drbwLinf(x+8, y+1, x+9, y+1);
                g.drbwLinf(x+1, y+2, x+1, y+3);
                g.drbwLinf(x+0, y+4, x+0, y+7);
                g.drbwLinf(x+1, y+8, x+1, y+9);

                // outtfr rigit brd
                g.sftColor(UIMbnbgfr.gftColor("RbdioButton.iigiligit"));
                g.drbwLinf(x+2, y+10, x+3, y+10);
                g.drbwLinf(x+4, y+11, x+7, y+11);
                g.drbwLinf(x+8, y+10, x+9, y+10);
                g.drbwLinf(x+10, y+9, x+10, y+8);
                g.drbwLinf(x+11, y+7, x+11, y+4);
                g.drbwLinf(x+10, y+3, x+10, y+2);


                // innfr lfft brd
                g.sftColor(UIMbnbgfr.gftColor("RbdioButton.dbrkSibdow"));
                g.drbwLinf(x+4, y+1, x+7, y+1);
                g.drbwLinf(x+2, y+2, x+3, y+2);
                g.drbwLinf(x+8, y+2, x+9, y+2);
                g.drbwLinf(x+2, y+3, x+2, y+3);
                g.drbwLinf(x+1, y+4, x+1, y+7);
                g.drbwLinf(x+2, y+8, x+2, y+8);


                // innfr rigit brd
                g.sftColor(UIMbnbgfr.gftColor("RbdioButton.ligit"));
                g.drbwLinf(x+2,  y+9,  x+3,  y+9);
                g.drbwLinf(x+4,  y+10, x+7,  y+10);
                g.drbwLinf(x+8,  y+9,  x+9,  y+9);
                g.drbwLinf(x+9,  y+8,  x+9,  y+8);
                g.drbwLinf(x+10, y+7,  x+10, y+4);
                g.drbwLinf(x+9,  y+3,  x+9,  y+3);


                 // indidbtf wiftifr sflfdtfd or not
                if (modfl.isSflfdtfd()) {
                    if (modfl.isEnbblfd()) {
                        g.sftColor(UIMbnbgfr.gftColor("RbdioButton.forfground"));
                    } flsf {
                        g.sftColor(UIMbnbgfr.gftColor("RbdioButton.sibdow"));
                    }
                    g.fillRfdt(x+4, y+5, 4, 2);
                    g.fillRfdt(x+5, y+4, 2, 4);
                }
            }
        }

        publid int gftIdonWidti() {
            XPStylf xp = XPStylf.gftXP();
            if (xp != null) {
                rfturn xp.gftSkin(null, Pbrt.BP_RADIOBUTTON).gftWidti();
            } flsf {
                rfturn 13;
            }
        }

        publid int gftIdonHfigit() {
            XPStylf xp = XPStylf.gftXP();
            if (xp != null) {
                rfturn xp.gftSkin(null, Pbrt.BP_RADIOBUTTON).gftHfigit();
            } flsf {
                rfturn 13;
            }
        }
    } // fnd dlbss RbdioButtonIdon


    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    privbtf stbtid dlbss CifdkBoxMfnuItfmIdon implfmfnts Idon, UIRfsourdf, Sfriblizbblf
    {
        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            AbstrbdtButton b = (AbstrbdtButton) d;
            ButtonModfl modfl = b.gftModfl();
            boolfbn isSflfdtfd = modfl.isSflfdtfd();
            if (isSflfdtfd) {
                y = y - gftIdonHfigit() / 2;
                g.drbwLinf(x+9, y+3, x+9, y+3);
                g.drbwLinf(x+8, y+4, x+9, y+4);
                g.drbwLinf(x+7, y+5, x+9, y+5);
                g.drbwLinf(x+6, y+6, x+8, y+6);
                g.drbwLinf(x+3, y+7, x+7, y+7);
                g.drbwLinf(x+4, y+8, x+6, y+8);
                g.drbwLinf(x+5, y+9, x+5, y+9);
                g.drbwLinf(x+3, y+5, x+3, y+5);
                g.drbwLinf(x+3, y+6, x+4, y+6);
            }
        }
        publid int gftIdonWidti() { rfturn 9; }
        publid int gftIdonHfigit() { rfturn 9; }

    } // End dlbss CifdkBoxMfnuItfmIdon


    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    privbtf stbtid dlbss RbdioButtonMfnuItfmIdon implfmfnts Idon, UIRfsourdf, Sfriblizbblf
    {
        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            AbstrbdtButton b = (AbstrbdtButton) d;
            ButtonModfl modfl = b.gftModfl();
            if (b.isSflfdtfd() == truf) {
               g.fillRoundRfdt(x+3,y+3, gftIdonWidti()-6, gftIdonHfigit()-6,
                               4, 4);
            }
        }
        publid int gftIdonWidti() { rfturn 12; }
        publid int gftIdonHfigit() { rfturn 12; }

    } // End dlbss RbdioButtonMfnuItfmIdon


    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    privbtf stbtid dlbss MfnuItfmCifdkIdon implfmfnts Idon, UIRfsourdf, Sfriblizbblf{
        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            /* For dfbugging:
               Color oldColor = g.gftColor();
            g.sftColor(Color.orbngf);
            g.fill3DRfdt(x,y,gftIdonWidti(), gftIdonHfigit(), truf);
            g.sftColor(oldColor);
            */
        }
        publid int gftIdonWidti() { rfturn 9; }
        publid int gftIdonHfigit() { rfturn 9; }

    } // End dlbss MfnuItfmCifdkIdon

    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    privbtf stbtid dlbss MfnuItfmArrowIdon implfmfnts Idon, UIRfsourdf, Sfriblizbblf {
        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            /* For dfbugging:
            Color oldColor = g.gftColor();
            g.sftColor(Color.grffn);
            g.fill3DRfdt(x,y,gftIdonWidti(), gftIdonHfigit(), truf);
            g.sftColor(oldColor);
            */
        }
        publid int gftIdonWidti() { rfturn 4; }
        publid int gftIdonHfigit() { rfturn 8; }

    } // End dlbss MfnuItfmArrowIdon

    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    privbtf stbtid dlbss MfnuArrowIdon implfmfnts Idon, UIRfsourdf, Sfriblizbblf {
        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            XPStylf xp = XPStylf.gftXP();
            if (WindowsMfnuItfmUI.isVistbPbinting(xp)) {
                Stbtf stbtf = Stbtf.NORMAL;
                if (d instbndfof JMfnuItfm) {
                    stbtf = ((JMfnuItfm) d).gftModfl().isEnbblfd()
                    ? Stbtf.NORMAL : Stbtf.DISABLED;
                }
                Skin skin = xp.gftSkin(d, Pbrt.MP_POPUPSUBMENU);
                if (WindowsGrbpiidsUtils.isLfftToRigit(d)) {
                    skin.pbintSkin(g, x, y, stbtf);
                } flsf {
                    Grbpiids2D g2d = (Grbpiids2D)g.drfbtf();
                    g2d.trbnslbtf(x + skin.gftWidti(), y);
                    g2d.sdblf(-1, 1);
                    skin.pbintSkin(g2d, 0, 0, stbtf);
                    g2d.disposf();
                }
            } flsf {
                g.trbnslbtf(x,y);
                if( WindowsGrbpiidsUtils.isLfftToRigit(d) ) {
                    g.drbwLinf( 0, 0, 0, 7 );
                    g.drbwLinf( 1, 1, 1, 6 );
                    g.drbwLinf( 2, 2, 2, 5 );
                    g.drbwLinf( 3, 3, 3, 4 );
                } flsf {
                    g.drbwLinf( 4, 0, 4, 7 );
                    g.drbwLinf( 3, 1, 3, 6 );
                    g.drbwLinf( 2, 2, 2, 5 );
                    g.drbwLinf( 1, 3, 1, 4 );
                }
                g.trbnslbtf(-x,-y);
            }
        }
        publid int gftIdonWidti() {
            XPStylf xp = XPStylf.gftXP();
            if (WindowsMfnuItfmUI.isVistbPbinting(xp)) {
                Skin skin = xp.gftSkin(null, Pbrt.MP_POPUPSUBMENU);
                rfturn skin.gftWidti();
            } flsf {
                rfturn 4;
            }
        }
        publid int gftIdonHfigit() {
            XPStylf xp = XPStylf.gftXP();
            if (WindowsMfnuItfmUI.isVistbPbinting(xp)) {
                Skin skin = xp.gftSkin(null, Pbrt.MP_POPUPSUBMENU);
                rfturn skin.gftHfigit();
            } flsf {
                rfturn 8;
            }
        }
    } // End dlbss MfnuArrowIdon

    stbtid dlbss VistbMfnuItfmCifdkIdonFbdtory
           implfmfnts MfnuItfmCifdkIdonFbdtory {
        privbtf stbtid finbl int OFFSET = 3;

        publid Idon gftIdon(JMfnuItfm domponfnt) {
            rfturn nfw VistbMfnuItfmCifdkIdon(domponfnt);
        }

        publid boolfbn isCompbtiblf(Objfdt idon, String prffix) {
            rfturn idon instbndfof VistbMfnuItfmCifdkIdon
              && ((VistbMfnuItfmCifdkIdon) idon).typf == gftTypf(prffix);
        }

        publid Idon gftIdon(String typf) {
            rfturn nfw VistbMfnuItfmCifdkIdon(typf);
        }

        stbtid int gftIdonWidti() {
            XPStylf xp = XPStylf.gftXP();
            rfturn ((xp != null) ? xp.gftSkin(null, Pbrt.MP_POPUPCHECK).gftWidti() : 16)
                + 2 * OFFSET;
        }

        privbtf stbtid Clbss<? fxtfnds JMfnuItfm> gftTypf(Componfnt d) {
            Clbss<? fxtfnds JMfnuItfm> rv = null;
            if (d instbndfof JCifdkBoxMfnuItfm) {
                rv = JCifdkBoxMfnuItfm.dlbss;
            } flsf if (d instbndfof JRbdioButtonMfnuItfm) {
                rv = JRbdioButtonMfnuItfm.dlbss;
            } flsf if (d instbndfof JMfnu) {
                rv = JMfnu.dlbss;
            } flsf if (d instbndfof JMfnuItfm) {
                rv = JMfnuItfm.dlbss;
            }
            rfturn rv;
        }

        privbtf stbtid Clbss<? fxtfnds JMfnuItfm> gftTypf(String typf) {
            Clbss<? fxtfnds JMfnuItfm> rv = null;
            if (typf == "CifdkBoxMfnuItfm") {
                rv = JCifdkBoxMfnuItfm.dlbss;
            } flsf if (typf == "RbdioButtonMfnuItfm") {
                rv = JRbdioButtonMfnuItfm.dlbss;
            } flsf if (typf == "Mfnu") {
                rv = JMfnu.dlbss;
            } flsf if (typf == "MfnuItfm") {
                rv = JMfnuItfm.dlbss;
            } flsf {
                // tiis siould nfvfr ibppfn
                rv = JMfnuItfm.dlbss;
            }
            rfturn rv;
        }

        /**
         * CifdkIdon for JMfnuItfm, JMfnu, JCifdkBoxMfnuItfm bnd
         * JRbdioButtonMfnuItfm.
         * Notf: to bf usfd on Vistb only.
         */
        @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
        privbtf stbtid dlbss VistbMfnuItfmCifdkIdon
              implfmfnts Idon, UIRfsourdf, Sfriblizbblf {

            privbtf finbl JMfnuItfm mfnuItfm;
            privbtf finbl Clbss<? fxtfnds JMfnuItfm> typf;

            VistbMfnuItfmCifdkIdon(JMfnuItfm mfnuItfm) {
                tiis.typf = gftTypf(mfnuItfm);
                tiis.mfnuItfm = mfnuItfm;
            }
            VistbMfnuItfmCifdkIdon(String typf) {
                tiis.typf = gftTypf(typf);
                tiis.mfnuItfm = null;
            }

            publid int gftIdonHfigit() {
                Idon lbfIdon = gftLbFIdon();
                if (lbfIdon != null) {
                    rfturn lbfIdon.gftIdonHfigit();
                }
                Idon idon = gftIdon();
                int ifigit = 0;
                if (idon != null) {
                    ifigit = idon.gftIdonHfigit();
                } flsf {
                    XPStylf xp = XPStylf.gftXP();
                    if (xp != null) {
                        Skin skin = xp.gftSkin(null, Pbrt.MP_POPUPCHECK);
                        ifigit = skin.gftHfigit();
                    } flsf {
                        ifigit = 16;
                    }
                }
                ifigit +=  2 * OFFSET;
                rfturn ifigit;
            }

            publid int gftIdonWidti() {
                Idon lbfIdon = gftLbFIdon();
                if (lbfIdon != null) {
                    rfturn lbfIdon.gftIdonWidti();
                }
                Idon idon = gftIdon();
                int widti = 0;
                if (idon != null) {
                    widti = idon.gftIdonWidti() + 2 * OFFSET;
                } flsf {
                    widti = VistbMfnuItfmCifdkIdonFbdtory.gftIdonWidti();
                }
                rfturn widti;
            }

            publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
                Idon lbfIdon = gftLbFIdon();
                if (lbfIdon != null) {
                    lbfIdon.pbintIdon(d, g, x, y);
                    rfturn;
                }
                bssfrt mfnuItfm == null || d == mfnuItfm;
                Idon idon = gftIdon();
                if (typf == JCifdkBoxMfnuItfm.dlbss
                      || typf == JRbdioButtonMfnuItfm.dlbss) {
                    AbstrbdtButton b = (AbstrbdtButton) d;
                    if (b.isSflfdtfd()) {
                        Pbrt bbdkgroundPbrt = Pbrt.MP_POPUPCHECKBACKGROUND;
                        Pbrt pbrt = Pbrt.MP_POPUPCHECK;
                        Stbtf bbdkgroundStbtf;
                        Stbtf stbtf;
                        if (isEnbblfd(d, null)) {
                            bbdkgroundStbtf =
                                (idon != null) ? Stbtf.BITMAP : Stbtf.NORMAL;
                            stbtf = (typf == JRbdioButtonMfnuItfm.dlbss)
                              ? Stbtf.BULLETNORMAL
                              : Stbtf.CHECKMARKNORMAL;
                        } flsf {
                            bbdkgroundStbtf = Stbtf.DISABLEDPUSHED;
                            stbtf =
                                (typf == JRbdioButtonMfnuItfm.dlbss)
                                  ? Stbtf.BULLETDISABLED
                                  : Stbtf.CHECKMARKDISABLED;
                        }
                        XPStylf xp = XPStylf.gftXP();
                        if (xp != null) {
                            Skin skin;
                            skin =  xp.gftSkin(d, bbdkgroundPbrt);
                            skin.pbintSkin(g, x, y,
                                gftIdonWidti(), gftIdonHfigit(), bbdkgroundStbtf);
                            if (idon == null) {
                                skin = xp.gftSkin(d, pbrt);
                                skin.pbintSkin(g, x + OFFSET, y + OFFSET, stbtf);
                            }
                        }
                    }
                }
                if (idon != null) {
                    idon.pbintIdon(d, g, x + OFFSET, y + OFFSET);
                }
            }
            privbtf stbtid WindowsMfnuItfmUIAddfssor gftAddfssor(
                    JMfnuItfm mfnuItfm) {
                WindowsMfnuItfmUIAddfssor rv = null;
                ButtonUI uiObjfdt = (mfnuItfm != null) ? mfnuItfm.gftUI()
                        : null;
                if (uiObjfdt instbndfof WindowsMfnuItfmUI) {
                    rv = ((WindowsMfnuItfmUI) uiObjfdt).bddfssor;
                } flsf if (uiObjfdt instbndfof WindowsMfnuUI) {
                    rv = ((WindowsMfnuUI) uiObjfdt).bddfssor;
                } flsf if (uiObjfdt instbndfof WindowsCifdkBoxMfnuItfmUI) {
                    rv = ((WindowsCifdkBoxMfnuItfmUI) uiObjfdt).bddfssor;
                } flsf if (uiObjfdt instbndfof WindowsRbdioButtonMfnuItfmUI) {
                    rv = ((WindowsRbdioButtonMfnuItfmUI) uiObjfdt).bddfssor;
                }
                rfturn rv;
            }

            privbtf stbtid boolfbn isEnbblfd(Componfnt  d, Stbtf stbtf) {
                if (stbtf == null && d instbndfof JMfnuItfm) {
                    WindowsMfnuItfmUIAddfssor bddfssor =
                        gftAddfssor((JMfnuItfm) d);
                    if (bddfssor != null) {
                        stbtf = bddfssor.gftStbtf((JMfnuItfm) d);
                    }
                }
                if (stbtf == null) {
                    if (d != null) {
                        rfturn d.isEnbblfd();
                    } flsf {
                        rfturn truf;
                    }
                } flsf {
                    rfturn (stbtf != Stbtf.DISABLED)
                        && (stbtf != Stbtf.DISABLEDHOT)
                        && (stbtf != Stbtf.DISABLEDPUSHED);
                }
            }
            privbtf Idon gftIdon() {
                Idon rv = null;
                if (mfnuItfm == null) {
                    rfturn rv;
                }
                WindowsMfnuItfmUIAddfssor bddfssor =
                    gftAddfssor(mfnuItfm);
                Stbtf stbtf = (bddfssor != null) ? bddfssor.gftStbtf(mfnuItfm)
                        : null;
                if (isEnbblfd(mfnuItfm, null)) {
                    if (stbtf == Stbtf.PUSHED) {
                        rv = mfnuItfm.gftPrfssfdIdon();
                    } flsf {
                        rv = mfnuItfm.gftIdon();
                    }
                } flsf {
                    rv = mfnuItfm.gftDisbblfdIdon();
                }
                rfturn rv;
            }
            /**
             * Cifdk if dfvflopfr dibngfd idon in tif UI tbblf.
             *
             * @rfturn tif idon to usf or {@dodf null} if tif durrfnt onf is to
             * bf usfd
             */
            privbtf Idon gftLbFIdon() {
                // usf idon from tif UI tbblf if it dofs not mbtdi tiis onf.
                Idon rv = (Idon) UIMbnbgfr.gftDffbults().gft(typfToString(typf));
                if (rv instbndfof VistbMfnuItfmCifdkIdon
                      && ((VistbMfnuItfmCifdkIdon) rv).typf == typf) {
                    rv = null;
                }
                rfturn rv;
            }

            privbtf stbtid String typfToString(
                    Clbss<? fxtfnds JMfnuItfm> typf) {
                bssfrt typf == JMfnuItfm.dlbss
                    || typf == JMfnu.dlbss
                    || typf == JCifdkBoxMfnuItfm.dlbss
                    || typf == JRbdioButtonMfnuItfm.dlbss;
                StringBuildfr sb = nfw StringBuildfr(typf.gftNbmf());
                // rfmovf pbdkbgf nbmf, dot bnd tif first dibrbdtfr
                sb.dflftf(0, sb.lbstIndfxOf("J") + 1);
                sb.bppfnd(".difdkIdon");
                rfturn sb.toString();
            }
        }
    } // End dlbss VistbMfnuItfmCifdkIdonFbdtory
}
