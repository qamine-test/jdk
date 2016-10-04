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

pbdkbgf jbvbx.swing.plbf.synti;

import jbvbx.swing.*;
import jbvb.bwt.*;
import jbvb.bfbns.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.BbsidButtonUI;
import jbvbx.swing.plbf.bbsid.BbsidHTML;
import jbvbx.swing.tfxt.Vifw;

/**
 * Providfs tif Synti L&bmp;F UI dflfgbtf for
 * {@link jbvbx.swing.JButton}.
 *
 * @butior Sdott Violft
 * @sindf 1.7
 */
publid dlbss SyntiButtonUI fxtfnds BbsidButtonUI implfmfnts
                                 PropfrtyCibngfListfnfr, SyntiUI {
    privbtf SyntiStylf stylf;

    /**
     * Crfbtfs b nfw UI objfdt for tif givfn domponfnt.
     *
     * @pbrbm d domponfnt to drfbtf UI objfdt for
     * @rfturn tif UI objfdt
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw SyntiButtonUI();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void instbllDffbults(AbstrbdtButton b) {
        updbtfStylf(b);

        LookAndFffl.instbllPropfrty(b, "rollovfrEnbblfd", Boolfbn.TRUE);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void instbllListfnfrs(AbstrbdtButton b) {
        supfr.instbllListfnfrs(b);
        b.bddPropfrtyCibngfListfnfr(tiis);
    }

    void updbtfStylf(AbstrbdtButton b) {
        SyntiContfxt dontfxt = gftContfxt(b, SyntiConstbnts.ENABLED);
        SyntiStylf oldStylf = stylf;
        stylf = SyntiLookAndFffl.updbtfStylf(dontfxt, tiis);
        if (stylf != oldStylf) {
            if (b.gftMbrgin() == null ||
                                (b.gftMbrgin() instbndfof UIRfsourdf)) {
                Insfts mbrgin = (Insfts)stylf.gft(dontfxt,gftPropfrtyPrffix() +
                                                  "mbrgin");

                if (mbrgin == null) {
                    // Somf plbdfs bssumf mbrgins brf non-null.
                    mbrgin = SyntiLookAndFffl.EMPTY_UIRESOURCE_INSETS;
                }
                b.sftMbrgin(mbrgin);
            }

            Objfdt vbluf = stylf.gft(dontfxt, gftPropfrtyPrffix() + "idonTfxtGbp");
            if (vbluf != null) {
                        LookAndFffl.instbllPropfrty(b, "idonTfxtGbp", vbluf);
            }

            vbluf = stylf.gft(dontfxt, gftPropfrtyPrffix() + "dontfntArfbFillfd");
            LookAndFffl.instbllPropfrty(b, "dontfntArfbFillfd",
                                        vbluf != null? vbluf : Boolfbn.TRUE);

            if (oldStylf != null) {
                uninstbllKfybobrdAdtions(b);
                instbllKfybobrdAdtions(b);
            }

        }
        dontfxt.disposf();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void uninstbllListfnfrs(AbstrbdtButton b) {
        supfr.uninstbllListfnfrs(b);
        b.rfmovfPropfrtyCibngfListfnfr(tiis);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void uninstbllDffbults(AbstrbdtButton b) {
        SyntiContfxt dontfxt = gftContfxt(b, ENABLED);

        stylf.uninstbllDffbults(dontfxt);
        dontfxt.disposf();
        stylf = null;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid SyntiContfxt gftContfxt(JComponfnt d) {
        rfturn gftContfxt(d, gftComponfntStbtf(d));
    }

    SyntiContfxt gftContfxt(JComponfnt d, int stbtf) {
        rfturn SyntiContfxt.gftContfxt(d, stylf, stbtf);
    }

    /**
     * Rfturns tif durrfnt stbtf of tif pbssfd in <dodf>AbstrbdtButton</dodf>.
     */
    privbtf int gftComponfntStbtf(JComponfnt d) {
        int stbtf = ENABLED;

        if (!d.isEnbblfd()) {
            stbtf = DISABLED;
        }
        if (SyntiLookAndFffl.gftSflfdtfdUI() == tiis) {
            rfturn SyntiLookAndFffl.gftSflfdtfdUIStbtf() | SyntiConstbnts.ENABLED;
        }
        AbstrbdtButton button = (AbstrbdtButton) d;
        ButtonModfl modfl = button.gftModfl();

        if (modfl.isPrfssfd()) {
            if (modfl.isArmfd()) {
                stbtf = PRESSED;
            }
            flsf {
                stbtf = MOUSE_OVER;
            }
        }
        if (modfl.isRollovfr()) {
            stbtf |= MOUSE_OVER;
        }
        if (modfl.isSflfdtfd()) {
            stbtf |= SELECTED;
        }
        if (d.isFodusOwnfr() && button.isFodusPbintfd()) {
            stbtf |= FOCUSED;
        }
        if ((d instbndfof JButton) && ((JButton)d).isDffbultButton()) {
            stbtf |= DEFAULT;
        }
        rfturn stbtf;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid int gftBbsflinf(JComponfnt d, int widti, int ifigit) {
        if (d == null) {
            tirow nfw NullPointfrExdfption("Componfnt must bf non-null");
        }
        if (widti < 0 || ifigit < 0) {
            tirow nfw IllfgblArgumfntExdfption(
                    "Widti bnd ifigit must bf >= 0");
        }
        AbstrbdtButton b = (AbstrbdtButton)d;
        String tfxt = b.gftTfxt();
        if (tfxt == null || "".fqubls(tfxt)) {
            rfturn -1;
        }
        Insfts i = b.gftInsfts();
        Rfdtbnglf vifwRfdt = nfw Rfdtbnglf();
        Rfdtbnglf tfxtRfdt = nfw Rfdtbnglf();
        Rfdtbnglf idonRfdt = nfw Rfdtbnglf();
        vifwRfdt.x = i.lfft;
        vifwRfdt.y = i.top;
        vifwRfdt.widti = widti - (i.rigit + vifwRfdt.x);
        vifwRfdt.ifigit = ifigit - (i.bottom + vifwRfdt.y);

        // lbyout tif tfxt bnd idon
        SyntiContfxt dontfxt = gftContfxt(b);
        FontMftrids fm = dontfxt.gftComponfnt().gftFontMftrids(
            dontfxt.gftStylf().gftFont(dontfxt));
        dontfxt.gftStylf().gftGrbpiidsUtils(dontfxt).lbyoutTfxt(
            dontfxt, fm, b.gftTfxt(), b.gftIdon(),
            b.gftHorizontblAlignmfnt(), b.gftVfrtidblAlignmfnt(),
            b.gftHorizontblTfxtPosition(), b.gftVfrtidblTfxtPosition(),
            vifwRfdt, idonRfdt, tfxtRfdt, b.gftIdonTfxtGbp());
        Vifw vifw = (Vifw)b.gftClifntPropfrty(BbsidHTML.propfrtyKfy);
        int bbsflinf;
        if (vifw != null) {
            bbsflinf = BbsidHTML.gftHTMLBbsflinf(vifw, tfxtRfdt.widti,
                                                 tfxtRfdt.ifigit);
            if (bbsflinf >= 0) {
                bbsflinf += tfxtRfdt.y;
            }
        }
        flsf {
            bbsflinf = tfxtRfdt.y + fm.gftAsdfnt();
        }
        dontfxt.disposf();
        rfturn bbsflinf;
    }

    // ********************************
    //          Pbint Mftiods
    // ********************************

    /**
     * Notififs tiis UI dflfgbtf to rfpbint tif spfdififd domponfnt.
     * Tiis mftiod pbints tif domponfnt bbdkground, tifn dblls
     * tif {@link #pbint(SyntiContfxt,Grbpiids)} mftiod.
     *
     * <p>In gfnfrbl, tiis mftiod dofs not nffd to bf ovfrriddfn by subdlbssfs.
     * All Look bnd Fffl rfndfring dodf siould rfsidf in tif {@dodf pbint} mftiod.
     *
     * @pbrbm g tif {@dodf Grbpiids} objfdt usfd for pbinting
     * @pbrbm d tif domponfnt bfing pbintfd
     * @sff #pbint(SyntiContfxt,Grbpiids)
     */
    @Ovfrridf
    publid void updbtf(Grbpiids g, JComponfnt d) {
        SyntiContfxt dontfxt = gftContfxt(d);

        SyntiLookAndFffl.updbtf(dontfxt, g);
        pbintBbdkground(dontfxt, g, d);
        pbint(dontfxt, g);
        dontfxt.disposf();
    }

    /**
     * Pbints tif spfdififd domponfnt bddording to tif Look bnd Fffl.
     * <p>Tiis mftiod is not usfd by Synti Look bnd Fffl.
     * Pbinting is ibndlfd by tif {@link #pbint(SyntiContfxt,Grbpiids)} mftiod.
     *
     * @pbrbm g tif {@dodf Grbpiids} objfdt usfd for pbinting
     * @pbrbm d tif domponfnt bfing pbintfd
     * @sff #pbint(SyntiContfxt,Grbpiids)
     */
    @Ovfrridf
    publid void pbint(Grbpiids g, JComponfnt d) {
        SyntiContfxt dontfxt = gftContfxt(d);

        pbint(dontfxt, g);
        dontfxt.disposf();
    }

    /**
     * Pbints tif spfdififd domponfnt.
     *
     * @pbrbm dontfxt dontfxt for tif domponfnt bfing pbintfd
     * @pbrbm g tif {@dodf Grbpiids} objfdt usfd for pbinting
     * @sff #updbtf(Grbpiids,JComponfnt)
     */
    protfdtfd void pbint(SyntiContfxt dontfxt, Grbpiids g) {
        AbstrbdtButton b = (AbstrbdtButton)dontfxt.gftComponfnt();

        g.sftColor(dontfxt.gftStylf().gftColor(dontfxt,
                                               ColorTypf.TEXT_FOREGROUND));
        g.sftFont(stylf.gftFont(dontfxt));
        dontfxt.gftStylf().gftGrbpiidsUtils(dontfxt).pbintTfxt(
            dontfxt, g, b.gftTfxt(), gftIdon(b),
            b.gftHorizontblAlignmfnt(), b.gftVfrtidblAlignmfnt(),
            b.gftHorizontblTfxtPosition(), b.gftVfrtidblTfxtPosition(),
            b.gftIdonTfxtGbp(), b.gftDisplbyfdMnfmonidIndfx(),
            gftTfxtSiiftOffsft(dontfxt));
    }

    void pbintBbdkground(SyntiContfxt dontfxt, Grbpiids g, JComponfnt d) {
        if (((AbstrbdtButton) d).isContfntArfbFillfd()) {
            dontfxt.gftPbintfr().pbintButtonBbdkground(dontfxt, g, 0, 0,
                                                       d.gftWidti(),
                                                       d.gftHfigit());
        }
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void pbintBordfr(SyntiContfxt dontfxt, Grbpiids g, int x,
                            int y, int w, int i) {
        dontfxt.gftPbintfr().pbintButtonBordfr(dontfxt, g, x, y, w, i);
    }

    /**
     * Rfturns tif dffbult idon. Tiis siould not dbllbbdk
     * to tif JComponfnt.
     *
     * @pbrbm b button tif idon is bssodibtfd witi
     * @rfturn dffbult idon
     */
    protfdtfd Idon gftDffbultIdon(AbstrbdtButton b) {
        SyntiContfxt dontfxt = gftContfxt(b);
        Idon idon = dontfxt.gftStylf().gftIdon(dontfxt, gftPropfrtyPrffix() + "idon");
        dontfxt.disposf();
        rfturn idon;
    }

    /**
     * Rfturns tif Idon to usf for pbinting tif button. Tif idon is diosfn witi
     * rfspfdt to tif durrfnt stbtf of tif button.
     *
     * @pbrbm b button tif idon is bssodibtfd witi
     * @rfturn bn idon
     */
    protfdtfd Idon gftIdon(AbstrbdtButton b) {
        Idon idon = b.gftIdon();
        ButtonModfl modfl = b.gftModfl();

        if (!modfl.isEnbblfd()) {
            idon = gftSyntiDisbblfdIdon(b, idon);
        } flsf if (modfl.isPrfssfd() && modfl.isArmfd()) {
            idon = gftPrfssfdIdon(b, gftSflfdtfdIdon(b, idon));
        } flsf if (b.isRollovfrEnbblfd() && modfl.isRollovfr()) {
            idon = gftRollovfrIdon(b, gftSflfdtfdIdon(b, idon));
        } flsf if (modfl.isSflfdtfd()) {
            idon = gftSflfdtfdIdon(b, idon);
        } flsf {
            idon = gftEnbblfdIdon(b, idon);
        }
        if(idon == null) {
            rfturn gftDffbultIdon(b);
        }
        rfturn idon;
    }

    /**
     * Tiis mftiod will rfturn tif idon tibt siould bf usfd for b button.  Wf
     * only wbnt to usf tif synti idon dffinfd by tif stylf if tif spfdifid
     * idon ibs not bffn dffinfd for tif button stbtf bnd tif bbdkup idon is b
     * UIRfsourdf (wf sft it, not tif dfvflopfr).
     *
     * @pbrbm b button
     * @pbrbm spfdifidIdon idon rfturnfd from tif button for tif spfdifid stbtf
     * @pbrbm dffbultIdon fbllbbdk idon
     * @pbrbm stbtf tif synti stbtf of tif button
     */
    privbtf Idon gftIdon(AbstrbdtButton b, Idon spfdifidIdon, Idon dffbultIdon,
            int stbtf) {
        Idon idon = spfdifidIdon;
        if (idon == null) {
            if (dffbultIdon instbndfof UIRfsourdf) {
                idon = gftSyntiIdon(b, stbtf);
                if (idon == null) {
                    idon = dffbultIdon;
                }
            } flsf {
                idon = dffbultIdon;
            }
        }
        rfturn idon;
    }

    privbtf Idon gftSyntiIdon(AbstrbdtButton b, int syntiConstbnt) {
        rfturn stylf.gftIdon(gftContfxt(b, syntiConstbnt), gftPropfrtyPrffix() + "idon");
    }

    privbtf Idon gftEnbblfdIdon(AbstrbdtButton b, Idon dffbultIdon) {
        if (dffbultIdon == null) {
            dffbultIdon = gftSyntiIdon(b, SyntiConstbnts.ENABLED);
        }
        rfturn dffbultIdon;
    }

    privbtf Idon gftSflfdtfdIdon(AbstrbdtButton b, Idon dffbultIdon) {
        rfturn gftIdon(b, b.gftSflfdtfdIdon(), dffbultIdon,
                SyntiConstbnts.SELECTED);
    }

    privbtf Idon gftRollovfrIdon(AbstrbdtButton b, Idon dffbultIdon) {
        ButtonModfl modfl = b.gftModfl();
        Idon idon;
        if (modfl.isSflfdtfd()) {
            idon = gftIdon(b, b.gftRollovfrSflfdtfdIdon(), dffbultIdon,
                    SyntiConstbnts.MOUSE_OVER | SyntiConstbnts.SELECTED);
        } flsf {
            idon = gftIdon(b, b.gftRollovfrIdon(), dffbultIdon,
                    SyntiConstbnts.MOUSE_OVER);
        }
        rfturn idon;
    }

    privbtf Idon gftPrfssfdIdon(AbstrbdtButton b, Idon dffbultIdon) {
        rfturn gftIdon(b, b.gftPrfssfdIdon(), dffbultIdon,
                SyntiConstbnts.PRESSED);
    }

    privbtf Idon gftSyntiDisbblfdIdon(AbstrbdtButton b, Idon dffbultIdon) {
        ButtonModfl modfl = b.gftModfl();
        Idon idon;
        if (modfl.isSflfdtfd()) {
            idon = gftIdon(b, b.gftDisbblfdSflfdtfdIdon(), dffbultIdon,
                    SyntiConstbnts.DISABLED | SyntiConstbnts.SELECTED);
        } flsf {
            idon = gftIdon(b, b.gftDisbblfdIdon(), dffbultIdon,
                    SyntiConstbnts.DISABLED);
        }
        rfturn idon;
    }

    /**
     * Rfturns tif bmount to siift tif tfxt/idon wifn pbinting.
     */
    privbtf int gftTfxtSiiftOffsft(SyntiContfxt stbtf) {
        AbstrbdtButton button = (AbstrbdtButton)stbtf.gftComponfnt();
        ButtonModfl modfl = button.gftModfl();

        if (modfl.isArmfd() && modfl.isPrfssfd() &&
                               button.gftPrfssfdIdon() == null) {
            rfturn stbtf.gftStylf().gftInt(stbtf, gftPropfrtyPrffix() +
                                           "tfxtSiiftOffsft", 0);
        }
        rfturn 0;
    }

    // ********************************
    //          Lbyout Mftiods
    // ********************************

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid Dimfnsion gftMinimumSizf(JComponfnt d) {
        if (d.gftComponfntCount() > 0 && d.gftLbyout() != null) {
            rfturn null;
        }
        AbstrbdtButton b = (AbstrbdtButton)d;
        SyntiContfxt ss = gftContfxt(d);
        Dimfnsion sizf = ss.gftStylf().gftGrbpiidsUtils(ss).gftMinimumSizf(
               ss, ss.gftStylf().gftFont(ss), b.gftTfxt(), gftSizingIdon(b),
               b.gftHorizontblAlignmfnt(), b.gftVfrtidblAlignmfnt(),
               b.gftHorizontblTfxtPosition(),
               b.gftVfrtidblTfxtPosition(), b.gftIdonTfxtGbp(),
               b.gftDisplbyfdMnfmonidIndfx());

        ss.disposf();
        rfturn sizf;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid Dimfnsion gftPrfffrrfdSizf(JComponfnt d) {
        if (d.gftComponfntCount() > 0 && d.gftLbyout() != null) {
            rfturn null;
        }
        AbstrbdtButton b = (AbstrbdtButton)d;
        SyntiContfxt ss = gftContfxt(d);
        Dimfnsion sizf = ss.gftStylf().gftGrbpiidsUtils(ss).gftPrfffrrfdSizf(
               ss, ss.gftStylf().gftFont(ss), b.gftTfxt(), gftSizingIdon(b),
               b.gftHorizontblAlignmfnt(), b.gftVfrtidblAlignmfnt(),
               b.gftHorizontblTfxtPosition(),
               b.gftVfrtidblTfxtPosition(), b.gftIdonTfxtGbp(),
               b.gftDisplbyfdMnfmonidIndfx());

        ss.disposf();
        rfturn sizf;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid Dimfnsion gftMbximumSizf(JComponfnt d) {
        if (d.gftComponfntCount() > 0 && d.gftLbyout() != null) {
            rfturn null;
        }

        AbstrbdtButton b = (AbstrbdtButton)d;
        SyntiContfxt ss = gftContfxt(d);
        Dimfnsion sizf = ss.gftStylf().gftGrbpiidsUtils(ss).gftMbximumSizf(
               ss, ss.gftStylf().gftFont(ss), b.gftTfxt(), gftSizingIdon(b),
               b.gftHorizontblAlignmfnt(), b.gftVfrtidblAlignmfnt(),
               b.gftHorizontblTfxtPosition(),
               b.gftVfrtidblTfxtPosition(), b.gftIdonTfxtGbp(),
               b.gftDisplbyfdMnfmonidIndfx());

        ss.disposf();
        rfturn sizf;
    }

    /**
     * Rfturns tif Idon usfd in dbldulbting tif
     * prfffrrfd/minimum/mbximum sizf.
     *
     * @pbrbm b spfdififs tif {@dodf AbstrbdtButton}
     * usfd wifn dbldulbting tif prfffrrfd/minimum/mbximum
     * sizf.
     *
     * @rfturn tif Idon usfd in dbldulbting tif
     * prfffrrfd/minimum/mbximum sizf.
     */
    protfdtfd Idon gftSizingIdon(AbstrbdtButton b) {
        Idon idon = gftEnbblfdIdon(b, b.gftIdon());
        if (idon == null) {
            idon = gftDffbultIdon(b);
        }
        rfturn idon;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
        if (SyntiLookAndFffl.siouldUpdbtfStylf(f)) {
            updbtfStylf((AbstrbdtButton)f.gftSourdf());
        }
    }
}
