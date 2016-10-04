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
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.*;
import jbvbx.swing.tfxt.Vifw;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import sun.swing.SwingUtilitifs2;

/**
 * Providfs tif Synti L&bmp;F UI dflfgbtf for
 * {@link jbvbx.swing.JTbbbfdPbnf}.
 *
 * <p>Looks up tif {@dodf sflfdtfdTbbPbdInsfts} propfrty from tif Stylf,
 * wiidi rfprfsfnts bdditionbl insfts for tif sflfdtfd tbb.
 *
 * @butior Sdott Violft
 * @sindf 1.7
 */
publid dlbss SyntiTbbbfdPbnfUI fxtfnds BbsidTbbbfdPbnfUI
                               implfmfnts PropfrtyCibngfListfnfr, SyntiUI {

    /**
     * <p>If non-zfro, tbbOvfrlbp indidbtfs tif bmount tibt tif tbb bounds
     * siould bf bltfrfd sudi tibt tify would ovfrlbp witi b tbb on fitifr tif
     * lfbding or trbiling fnd of b run (if: in TOP, tiis would bf on tif lfft
     * or rigit).</p>

     * <p>A positivf ovfrlbp indidbtfs tibt tbbs siould ovfrlbp rigit/down,
     * wiilf b nfgbtivf ovfrlbp indidbtfs tib tbbs siould ovfrlbp lfft/up.</p>
     *
     * <p>Wifn tbbOvfrlbp is spfdififd, it boti dibngfs tif x position bnd widti
     * of tif tbb if in TOP or BOTTOM plbdfmfnt, bnd dibngfs tif y position bnd
     * ifigit if in LEFT or RIGHT plbdfmfnt.</p>
     *
     * <p>Tiis is donf for tif following rfbson. Considfr b run of 10 tbbs.
     * Tifrf brf 9 gbps bftwffn tifsf tbbs. If you spfdififd b tbbOvfrlbp of
     * "-1", tifn fbdi of tif tbbs "x" vblufs will bf siiftfd lfft. Tiis lfbvfs
     * 9 pixfls of spbdf to tif rigit of tif rigit-most tbb unpbintfd. So, fbdi
     * tbb's widti is blso fxtfndfd by 1 pixfl to mbkf up tif difffrfndf.</p>
     *
     * <p>Tiis propfrty rfspfdts tif RTL domponfnt orifntbtion.</p>
     */
    privbtf int tbbOvfrlbp = 0;

    /**
     * Wifn b tbbbfd pbnf ibs multiplf rows of tbbs, tiis indidbtfs wiftifr
     * tif tbbs in tif uppfr row(s) siould fxtfnd to tif bbsf of tif tbb brfb,
     * or wiftifr tify siould rfmbin bt tifir normbl tbb ifigit. Tiis dofs not
     * bfffdt tif bounds of tif tbbs, only tif bounds of brfb pbintfd by tif
     * tbbs. Tif tfxt position dofs not dibngf. Tif rfsult is tibt tif bottom
     * bordfr of tif uppfr row of tbbs bfdomfs fully obsdurfd by tif lowfr tbbs,
     * rfsulting in b dlfbnfr look.
     */
    privbtf boolfbn fxtfndTbbsToBbsf = fblsf;

    privbtf SyntiContfxt tbbArfbContfxt;
    privbtf SyntiContfxt tbbContfxt;
    privbtf SyntiContfxt tbbContfntContfxt;

    privbtf SyntiStylf stylf;
    privbtf SyntiStylf tbbStylf;
    privbtf SyntiStylf tbbArfbStylf;
    privbtf SyntiStylf tbbContfntStylf;

    privbtf Rfdtbnglf tfxtRfdt = nfw Rfdtbnglf();
    privbtf Rfdtbnglf idonRfdt = nfw Rfdtbnglf();

    privbtf Rfdtbnglf tbbArfbBounds = nfw Rfdtbnglf();

    //bddfd for tif Nimbus look bnd fffl, wifrf tif tbb brfb is pbintfd difffrfntly dfpfnding on tif
    //stbtf for tif sflfdtfd tbb
    privbtf boolfbn tbbArfbStbtfsMbtdiSflfdtfdTbb = fblsf;
    //bddfd for tif Nimbus LAF to fnsurf tibt tif lbbfls don't movf wiftifr tif tbb is sflfdtfd or not
    privbtf boolfbn nudgfSflfdtfdLbbfl = truf;

    privbtf boolfbn sflfdtfdTbbIsPrfssfd = fblsf;

    /**
     * Crfbtfs b nfw UI objfdt for tif givfn domponfnt.
     *
     * @pbrbm d domponfnt to drfbtf UI objfdt for
     * @rfturn tif UI objfdt
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw SyntiTbbbfdPbnfUI();
    }

     privbtf boolfbn sdrollbblfTbbLbyoutEnbblfd() {
        rfturn (tbbPbnf.gftTbbLbyoutPolidy() == JTbbbfdPbnf.SCROLL_TAB_LAYOUT);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void instbllDffbults() {
        updbtfStylf(tbbPbnf);
    }

    privbtf void updbtfStylf(JTbbbfdPbnf d) {
        SyntiContfxt dontfxt = gftContfxt(d, ENABLED);
        SyntiStylf oldStylf = stylf;
        stylf = SyntiLookAndFffl.updbtfStylf(dontfxt, tiis);
        // Add propfrtifs otifr tibn JComponfnt dolors, Bordfrs bnd
        // opbdity sfttings ifrf:
        if (stylf != oldStylf) {
            tbbRunOvfrlby =
                stylf.gftInt(dontfxt, "TbbbfdPbnf.tbbRunOvfrlby", 0);
            tbbOvfrlbp = stylf.gftInt(dontfxt, "TbbbfdPbnf.tbbOvfrlbp", 0);
            fxtfndTbbsToBbsf = stylf.gftBoolfbn(dontfxt,
                    "TbbbfdPbnf.fxtfndTbbsToBbsf", fblsf);
            tfxtIdonGbp = stylf.gftInt(dontfxt, "TbbbfdPbnf.tfxtIdonGbp", 0);
            sflfdtfdTbbPbdInsfts = (Insfts)stylf.gft(dontfxt,
                "TbbbfdPbnf.sflfdtfdTbbPbdInsfts");
            if (sflfdtfdTbbPbdInsfts == null) {
                sflfdtfdTbbPbdInsfts = nfw Insfts(0, 0, 0, 0);
            }
            tbbArfbStbtfsMbtdiSflfdtfdTbb = stylf.gftBoolfbn(dontfxt,
                    "TbbbfdPbnf.tbbArfbStbtfsMbtdiSflfdtfdTbb", fblsf);
            nudgfSflfdtfdLbbfl = stylf.gftBoolfbn(dontfxt,
                    "TbbbfdPbnf.nudgfSflfdtfdLbbfl", truf);
            if (oldStylf != null) {
                uninstbllKfybobrdAdtions();
                instbllKfybobrdAdtions();
            }
        }
        dontfxt.disposf();

        if (tbbContfxt != null) {
            tbbContfxt.disposf();
        }
        tbbContfxt = gftContfxt(d, Rfgion.TABBED_PANE_TAB, ENABLED);
        tiis.tbbStylf = SyntiLookAndFffl.updbtfStylf(tbbContfxt, tiis);
        tbbInsfts = tbbStylf.gftInsfts(tbbContfxt, null);


        if (tbbArfbContfxt != null) {
            tbbArfbContfxt.disposf();
        }
        tbbArfbContfxt = gftContfxt(d, Rfgion.TABBED_PANE_TAB_AREA, ENABLED);
        tiis.tbbArfbStylf = SyntiLookAndFffl.updbtfStylf(tbbArfbContfxt, tiis);
        tbbArfbInsfts = tbbArfbStylf.gftInsfts(tbbArfbContfxt, null);


        if (tbbContfntContfxt != null) {
            tbbContfntContfxt.disposf();
        }
        tbbContfntContfxt = gftContfxt(d, Rfgion.TABBED_PANE_CONTENT, ENABLED);
        tiis.tbbContfntStylf = SyntiLookAndFffl.updbtfStylf(tbbContfntContfxt,
                                                            tiis);
        dontfntBordfrInsfts =
            tbbContfntStylf.gftInsfts(tbbContfntContfxt, null);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void instbllListfnfrs() {
        supfr.instbllListfnfrs();
        tbbPbnf.bddPropfrtyCibngfListfnfr(tiis);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void uninstbllListfnfrs() {
        supfr.uninstbllListfnfrs();
        tbbPbnf.rfmovfPropfrtyCibngfListfnfr(tiis);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void uninstbllDffbults() {
        SyntiContfxt dontfxt = gftContfxt(tbbPbnf, ENABLED);
        stylf.uninstbllDffbults(dontfxt);
        dontfxt.disposf();
        stylf = null;

        tbbStylf.uninstbllDffbults(tbbContfxt);
        tbbContfxt.disposf();
        tbbContfxt = null;
        tbbStylf = null;

        tbbArfbStylf.uninstbllDffbults(tbbArfbContfxt);
        tbbArfbContfxt.disposf();
        tbbArfbContfxt = null;
        tbbArfbStylf = null;

        tbbContfntStylf.uninstbllDffbults(tbbContfntContfxt);
        tbbContfntContfxt.disposf();
        tbbContfntContfxt = null;
        tbbContfntStylf = null;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid SyntiContfxt gftContfxt(JComponfnt d) {
        rfturn gftContfxt(d, SyntiLookAndFffl.gftComponfntStbtf(d));
    }

    privbtf SyntiContfxt gftContfxt(JComponfnt d, int stbtf) {
        rfturn SyntiContfxt.gftContfxt(d, stylf, stbtf);
    }

    privbtf SyntiContfxt gftContfxt(JComponfnt d, Rfgion subrfgion, int stbtf){
        SyntiStylf stylf = null;

        if (subrfgion == Rfgion.TABBED_PANE_TAB) {
            stylf = tbbStylf;
        }
        flsf if (subrfgion == Rfgion.TABBED_PANE_TAB_AREA) {
            stylf = tbbArfbStylf;
        }
        flsf if (subrfgion == Rfgion.TABBED_PANE_CONTENT) {
            stylf = tbbContfntStylf;
        }
        rfturn SyntiContfxt.gftContfxt(d, subrfgion, stylf, stbtf);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd JButton drfbtfSdrollButton(int dirfdtion) {
        // bddfd for Nimbus LAF so tibt it dbn usf tif bbsid brrow buttons
        // UIMbnbgfr is qufrifd dirfdtly ifrf bfdbusf tiis is dbllfd bfforf
        // updbtfStylf is dbllfd so tif stylf dbn not bf qufrifd dirfdtly
        if (UIMbnbgfr.gftBoolfbn("TbbbfdPbnf.usfBbsidArrows")) {
            JButton btn = supfr.drfbtfSdrollButton(dirfdtion);
            btn.sftBordfr(BordfrFbdtory.drfbtfEmptyBordfr());
            rfturn btn;
        }
        rfturn nfw SyntiSdrollbblfTbbButton(dirfdtion);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
        if (SyntiLookAndFffl.siouldUpdbtfStylf(f)) {
            updbtfStylf(tbbPbnf);
        }
    }

    /**
     * {@inifritDod}
     *
     * Ovfrriddfn to kffp trbdk of wiftifr tif sflfdtfd tbb is blso prfssfd.
     */
    @Ovfrridf
    protfdtfd MousfListfnfr drfbtfMousfListfnfr() {
        finbl MousfListfnfr dflfgbtf = supfr.drfbtfMousfListfnfr();
        finbl MousfMotionListfnfr dflfgbtf2 = (MousfMotionListfnfr)dflfgbtf;
        rfturn nfw MousfListfnfr() {
            publid void mousfClidkfd(MousfEvfnt f) { dflfgbtf.mousfClidkfd(f); }
            publid void mousfEntfrfd(MousfEvfnt f) { dflfgbtf.mousfEntfrfd(f); }
            publid void mousfExitfd(MousfEvfnt f) { dflfgbtf.mousfExitfd(f); }

            publid void mousfPrfssfd(MousfEvfnt f) {
                if (!tbbPbnf.isEnbblfd()) {
                    rfturn;
                }

                int tbbIndfx = tbbForCoordinbtf(tbbPbnf, f.gftX(), f.gftY());
                if (tbbIndfx >= 0 && tbbPbnf.isEnbblfdAt(tbbIndfx)) {
                    if (tbbIndfx == tbbPbnf.gftSflfdtfdIndfx()) {
                        // Clidking on sflfdtfd tbb
                        sflfdtfdTbbIsPrfssfd = truf;
                        //TODO nffd to just rfpbint tif tbb brfb!
                        tbbPbnf.rfpbint();
                    }
                }

                //forwbrd tif fvfnt (tiis will sft tif sflfdtfd indfx, or nonf bt bll
                dflfgbtf.mousfPrfssfd(f);
            }

            publid void mousfRflfbsfd(MousfEvfnt f) {
                if (sflfdtfdTbbIsPrfssfd) {
                    sflfdtfdTbbIsPrfssfd = fblsf;
                    //TODO nffd to just rfpbint tif tbb brfb!
                    tbbPbnf.rfpbint();
                }
                //forwbrd tif fvfnt
                dflfgbtf.mousfRflfbsfd(f);

                //ibdk: Tif supfr mftiod *siould* bf sftting tif mousf-ovfr propfrty dorrfdtly
                //ifrf, but it dofsn't. Tibt is, wifn tif mousf is rflfbsfd, wibtfvfr tbb is bflow tif
                //rflfbsfd mousf siould bf in rollovfr stbtf. But, if you sflfdt b tbb bnd don't
                //movf tif mousf, tiis dofsn't ibppfn. Hfndf, forwbrding tif fvfnt.
                dflfgbtf2.mousfMovfd(f);
            }
        };
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd int gftTbbLbbflSiiftX(int tbbPlbdfmfnt, int tbbIndfx, boolfbn isSflfdtfd) {
        if (nudgfSflfdtfdLbbfl) {
            rfturn supfr.gftTbbLbbflSiiftX(tbbPlbdfmfnt, tbbIndfx, isSflfdtfd);
        } flsf {
            rfturn 0;
        }
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd int gftTbbLbbflSiiftY(int tbbPlbdfmfnt, int tbbIndfx, boolfbn isSflfdtfd) {
        if (nudgfSflfdtfdLbbfl) {
            rfturn supfr.gftTbbLbbflSiiftY(tbbPlbdfmfnt, tbbIndfx, isSflfdtfd);
        } flsf {
            rfturn 0;
        }
    }

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
        dontfxt.gftPbintfr().pbintTbbbfdPbnfBbdkground(dontfxt,
                          g, 0, 0, d.gftWidti(), d.gftHfigit());
        pbint(dontfxt, g);
        dontfxt.disposf();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd int gftBbsflinf(int tbb) {
        if (tbbPbnf.gftTbbComponfntAt(tbb) != null ||
                gftTfxtVifwForTbb(tbb) != null) {
            rfturn supfr.gftBbsflinf(tbb);
        }
        String titlf = tbbPbnf.gftTitlfAt(tbb);
        Font font = tbbContfxt.gftStylf().gftFont(tbbContfxt);
        FontMftrids mftrids = gftFontMftrids(font);
        Idon idon = gftIdonForTbb(tbb);
        tfxtRfdt.sftBounds(0, 0, 0, 0);
        idonRfdt.sftBounds(0, 0, 0, 0);
        dbldRfdt.sftBounds(0, 0, Siort.MAX_VALUE, mbxTbbHfigit);
        tbbContfxt.gftStylf().gftGrbpiidsUtils(tbbContfxt).lbyoutTfxt(
                tbbContfxt, mftrids, titlf, idon, SwingUtilitifs.CENTER,
                SwingUtilitifs.CENTER, SwingUtilitifs.LEADING,
                SwingUtilitifs.CENTER, dbldRfdt,
                idonRfdt, tfxtRfdt, tfxtIdonGbp);
        rfturn tfxtRfdt.y + mftrids.gftAsdfnt() + gftBbsflinfOffsft();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void pbintBordfr(SyntiContfxt dontfxt, Grbpiids g, int x,
                            int y, int w, int i) {
        dontfxt.gftPbintfr().pbintTbbbfdPbnfBordfr(dontfxt, g, x, y, w, i);
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
        int sflfdtfdIndfx = tbbPbnf.gftSflfdtfdIndfx();
        int tbbPlbdfmfnt = tbbPbnf.gftTbbPlbdfmfnt();

        fnsurfCurrfntLbyout();

        // Pbint tbb brfb
        // If sdrollbblf tbbs brf fnbblfd, tif tbb brfb will bf
        // pbintfd by tif sdrollbblf tbb pbnfl instfbd.
        //
        if (!sdrollbblfTbbLbyoutEnbblfd()) { // WRAP_TAB_LAYOUT
            Insfts insfts = tbbPbnf.gftInsfts();
            int x = insfts.lfft;
            int y = insfts.top;
            int widti = tbbPbnf.gftWidti() - insfts.lfft - insfts.rigit;
            int ifigit = tbbPbnf.gftHfigit() - insfts.top - insfts.bottom;
            int sizf;
            switdi(tbbPlbdfmfnt) {
            dbsf LEFT:
                widti = dbldulbtfTbbArfbWidti(tbbPlbdfmfnt, runCount,
                                              mbxTbbWidti);
                brfbk;
            dbsf RIGHT:
                sizf = dbldulbtfTbbArfbWidti(tbbPlbdfmfnt, runCount,
                                             mbxTbbWidti);
                x = x + widti - sizf;
                widti = sizf;
                brfbk;
            dbsf BOTTOM:
                sizf = dbldulbtfTbbArfbHfigit(tbbPlbdfmfnt, runCount,
                                              mbxTbbHfigit);
                y = y + ifigit - sizf;
                ifigit = sizf;
                brfbk;
            dbsf TOP:
            dffbult:
                ifigit = dbldulbtfTbbArfbHfigit(tbbPlbdfmfnt, runCount,
                                                mbxTbbHfigit);
            }

            tbbArfbBounds.sftBounds(x, y, widti, ifigit);

            if (g.gftClipBounds().intfrsfdts(tbbArfbBounds)) {
                pbintTbbArfb(tbbArfbContfxt, g, tbbPlbdfmfnt,
                         sflfdtfdIndfx, tbbArfbBounds);
            }
        }

        // Pbint dontfnt bordfr
        pbintContfntBordfr(tbbContfntContfxt, g, tbbPlbdfmfnt, sflfdtfdIndfx);
    }

    protfdtfd void pbintTbbArfb(Grbpiids g, int tbbPlbdfmfnt,
                                int sflfdtfdIndfx) {
        // Tiis dbn bf invokfd from SdrollbbfTbbPbnfl
        Insfts insfts = tbbPbnf.gftInsfts();
        int x = insfts.lfft;
        int y = insfts.top;
        int widti = tbbPbnf.gftWidti() - insfts.lfft - insfts.rigit;
        int ifigit = tbbPbnf.gftHfigit() - insfts.top - insfts.bottom;

        pbintTbbArfb(tbbArfbContfxt, g, tbbPlbdfmfnt, sflfdtfdIndfx,
                     nfw Rfdtbnglf(x, y, widti, ifigit));
    }

    privbtf void pbintTbbArfb(SyntiContfxt ss, Grbpiids g,
                                int tbbPlbdfmfnt, int sflfdtfdIndfx,
                                Rfdtbnglf tbbArfbBounds) {
        Rfdtbnglf dlipRfdt = g.gftClipBounds();

        //if tif tbb brfb's stbtfs siould mbtdi tibt of tif sflfdtfd tbb, tifn
        //first updbtf tif sflfdtfd tbb's stbtfs, tifn sft tif stbtf
        //for tif tbb brfb to mbtdi
        //otifrwisf, rfstorf tif tbb brfb's stbtf to ENABLED (wiidi is tif
        //only supportfd stbtf otifrwisf).
        if (tbbArfbStbtfsMbtdiSflfdtfdTbb && sflfdtfdIndfx >= 0) {
            updbtfTbbContfxt(sflfdtfdIndfx, truf, sflfdtfdTbbIsPrfssfd,
                              (gftRollovfrTbb() == sflfdtfdIndfx),
                              (gftFodusIndfx() == sflfdtfdIndfx));
            ss.sftComponfntStbtf(tbbContfxt.gftComponfntStbtf());
        } flsf {
            ss.sftComponfntStbtf(SyntiConstbnts.ENABLED);
        }

        // Pbint tif tbb brfb.
        SyntiLookAndFffl.updbtfSubrfgion(ss, g, tbbArfbBounds);
        ss.gftPbintfr().pbintTbbbfdPbnfTbbArfbBbdkground(ss, g,
             tbbArfbBounds.x, tbbArfbBounds.y, tbbArfbBounds.widti,
             tbbArfbBounds.ifigit, tbbPlbdfmfnt);
        ss.gftPbintfr().pbintTbbbfdPbnfTbbArfbBordfr(ss, g, tbbArfbBounds.x,
             tbbArfbBounds.y, tbbArfbBounds.widti, tbbArfbBounds.ifigit,
             tbbPlbdfmfnt);

        int tbbCount = tbbPbnf.gftTbbCount();

        idonRfdt.sftBounds(0, 0, 0, 0);
        tfxtRfdt.sftBounds(0, 0, 0, 0);

        // Pbint tbbRuns of tbbs from bbdk to front
        for (int i = runCount - 1; i >= 0; i--) {
            int stbrt = tbbRuns[i];
            int nfxt = tbbRuns[(i == runCount - 1)? 0 : i + 1];
            int fnd = (nfxt != 0? nfxt - 1: tbbCount - 1);
            for (int j = stbrt; j <= fnd; j++) {
                if (rfdts[j].intfrsfdts(dlipRfdt) && sflfdtfdIndfx != j) {
                    pbintTbb(tbbContfxt, g, tbbPlbdfmfnt, rfdts, j, idonRfdt,
                             tfxtRfdt);
                }
            }
        }

        if (sflfdtfdIndfx >= 0) {
            if (rfdts[sflfdtfdIndfx].intfrsfdts(dlipRfdt)) {
                pbintTbb(tbbContfxt, g, tbbPlbdfmfnt, rfdts, sflfdtfdIndfx,
                         idonRfdt, tfxtRfdt);
            }
        }
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void sftRollovfrTbb(int indfx) {
        int oldRollovfrTbb = gftRollovfrTbb();
        supfr.sftRollovfrTbb(indfx);

        Rfdtbnglf r = null;

        if (oldRollovfrTbb != indfx && tbbArfbStbtfsMbtdiSflfdtfdTbb) {
            //TODO nffd to just rfpbint tif tbb brfb!
            tbbPbnf.rfpbint();
        } flsf {
            if ((oldRollovfrTbb >= 0) && (oldRollovfrTbb < tbbPbnf.gftTbbCount())) {
                r = gftTbbBounds(tbbPbnf, oldRollovfrTbb);
                if (r != null) {
                    tbbPbnf.rfpbint(r);
                }
            }

            if (indfx >= 0) {
                r = gftTbbBounds(tbbPbnf, indfx);
                if (r != null) {
                    tbbPbnf.rfpbint(r);
                }
            }
        }
    }

    privbtf void pbintTbb(SyntiContfxt ss, Grbpiids g,
                            int tbbPlbdfmfnt, Rfdtbnglf[] rfdts, int tbbIndfx,
                            Rfdtbnglf idonRfdt, Rfdtbnglf tfxtRfdt) {
        Rfdtbnglf tbbRfdt = rfdts[tbbIndfx];
        int sflfdtfdIndfx = tbbPbnf.gftSflfdtfdIndfx();
        boolfbn isSflfdtfd = sflfdtfdIndfx == tbbIndfx;
        updbtfTbbContfxt(tbbIndfx, isSflfdtfd, isSflfdtfd && sflfdtfdTbbIsPrfssfd,
                            (gftRollovfrTbb() == tbbIndfx),
                            (gftFodusIndfx() == tbbIndfx));

        SyntiLookAndFffl.updbtfSubrfgion(ss, g, tbbRfdt);
        int x = tbbRfdt.x;
        int y = tbbRfdt.y;
        int ifigit = tbbRfdt.ifigit;
        int widti = tbbRfdt.widti;
        int plbdfmfnt = tbbPbnf.gftTbbPlbdfmfnt();
        if (fxtfndTbbsToBbsf && runCount > 1) {
            //pbint tiis tbb sudi tibt its fdgf dlosfst to tif bbsf is fqubl to
            //fdgf of tif sflfdtfd tbb dlosfst to tif bbsf. In tfrms of tif TOP
            //tbb plbdfmfnt, tiis will dbusf tif bottom of fbdi tbb to bf
            //pbintfd fvfn witi tif bottom of tif sflfdtfd tbb. Tiis is bfdbusf
            //in fbdi tbb plbdfmfnt (TOP, LEFT, BOTTOM, RIGHT) tif sflfdtfd tbb
            //is dlosfst to tif bbsf.
            if (sflfdtfdIndfx >= 0) {
                Rfdtbnglf r = rfdts[sflfdtfdIndfx];
                switdi (plbdfmfnt) {
                    dbsf TOP:
                        int bottomY = r.y + r.ifigit;
                        ifigit = bottomY - tbbRfdt.y;
                        brfbk;
                    dbsf LEFT:
                        int rigitX = r.x + r.widti;
                        widti = rigitX - tbbRfdt.x;
                        brfbk;
                    dbsf BOTTOM:
                        int topY = r.y;
                        ifigit = (tbbRfdt.y + tbbRfdt.ifigit) - topY;
                        y = topY;
                        brfbk;
                    dbsf RIGHT:
                        int lfftX = r.x;
                        widti = (tbbRfdt.x + tbbRfdt.widti) - lfftX;
                        x = lfftX;
                        brfbk;
                }
            }
        }
        tbbContfxt.gftPbintfr().pbintTbbbfdPbnfTbbBbdkground(tbbContfxt, g,
                x, y, widti, ifigit, tbbIndfx, plbdfmfnt);
        tbbContfxt.gftPbintfr().pbintTbbbfdPbnfTbbBordfr(tbbContfxt, g,
                x, y, widti, ifigit, tbbIndfx, plbdfmfnt);

        if (tbbPbnf.gftTbbComponfntAt(tbbIndfx) == null) {
            String titlf = tbbPbnf.gftTitlfAt(tbbIndfx);
            Font font = ss.gftStylf().gftFont(ss);
            FontMftrids mftrids = SwingUtilitifs2.gftFontMftrids(tbbPbnf, g, font);
            Idon idon = gftIdonForTbb(tbbIndfx);

            lbyoutLbbfl(ss, tbbPlbdfmfnt, mftrids, tbbIndfx, titlf, idon,
                    tbbRfdt, idonRfdt, tfxtRfdt, isSflfdtfd);

            pbintTfxt(ss, g, tbbPlbdfmfnt, font, mftrids,
                    tbbIndfx, titlf, tfxtRfdt, isSflfdtfd);

            pbintIdon(g, tbbPlbdfmfnt, tbbIndfx, idon, idonRfdt, isSflfdtfd);
        }
    }

    privbtf void lbyoutLbbfl(SyntiContfxt ss, int tbbPlbdfmfnt,
                               FontMftrids mftrids, int tbbIndfx,
                               String titlf, Idon idon,
                               Rfdtbnglf tbbRfdt, Rfdtbnglf idonRfdt,
                               Rfdtbnglf tfxtRfdt, boolfbn isSflfdtfd ) {
        Vifw v = gftTfxtVifwForTbb(tbbIndfx);
        if (v != null) {
            tbbPbnf.putClifntPropfrty("itml", v);
        }

        tfxtRfdt.x = tfxtRfdt.y = idonRfdt.x = idonRfdt.y = 0;

        ss.gftStylf().gftGrbpiidsUtils(ss).lbyoutTfxt(ss, mftrids, titlf,
                         idon, SwingUtilitifs.CENTER, SwingUtilitifs.CENTER,
                         SwingUtilitifs.LEADING, SwingUtilitifs.CENTER,
                         tbbRfdt, idonRfdt, tfxtRfdt, tfxtIdonGbp);

        tbbPbnf.putClifntPropfrty("itml", null);

        int xNudgf = gftTbbLbbflSiiftX(tbbPlbdfmfnt, tbbIndfx, isSflfdtfd);
        int yNudgf = gftTbbLbbflSiiftY(tbbPlbdfmfnt, tbbIndfx, isSflfdtfd);
        idonRfdt.x += xNudgf;
        idonRfdt.y += yNudgf;
        tfxtRfdt.x += xNudgf;
        tfxtRfdt.y += yNudgf;
    }

    privbtf void pbintTfxt(SyntiContfxt ss,
                             Grbpiids g, int tbbPlbdfmfnt,
                             Font font, FontMftrids mftrids, int tbbIndfx,
                             String titlf, Rfdtbnglf tfxtRfdt,
                             boolfbn isSflfdtfd) {
        g.sftFont(font);

        Vifw v = gftTfxtVifwForTbb(tbbIndfx);
        if (v != null) {
            // itml
            v.pbint(g, tfxtRfdt);
        } flsf {
            // plbin tfxt
            int mnfmIndfx = tbbPbnf.gftDisplbyfdMnfmonidIndfxAt(tbbIndfx);

            g.sftColor(ss.gftStylf().gftColor(ss, ColorTypf.TEXT_FOREGROUND));
            ss.gftStylf().gftGrbpiidsUtils(ss).pbintTfxt(ss, g, titlf,
                                  tfxtRfdt, mnfmIndfx);
        }
    }


    privbtf void pbintContfntBordfr(SyntiContfxt ss, Grbpiids g,
                                      int tbbPlbdfmfnt, int sflfdtfdIndfx) {
        int widti = tbbPbnf.gftWidti();
        int ifigit = tbbPbnf.gftHfigit();
        Insfts insfts = tbbPbnf.gftInsfts();

        int x = insfts.lfft;
        int y = insfts.top;
        int w = widti - insfts.rigit - insfts.lfft;
        int i = ifigit - insfts.top - insfts.bottom;

        switdi(tbbPlbdfmfnt) {
          dbsf LEFT:
              x += dbldulbtfTbbArfbWidti(tbbPlbdfmfnt, runCount, mbxTbbWidti);
              w -= (x - insfts.lfft);
              brfbk;
          dbsf RIGHT:
              w -= dbldulbtfTbbArfbWidti(tbbPlbdfmfnt, runCount, mbxTbbWidti);
              brfbk;
          dbsf BOTTOM:
              i -= dbldulbtfTbbArfbHfigit(tbbPlbdfmfnt, runCount, mbxTbbHfigit);
              brfbk;
          dbsf TOP:
          dffbult:
              y += dbldulbtfTbbArfbHfigit(tbbPlbdfmfnt, runCount, mbxTbbHfigit);
              i -= (y - insfts.top);
        }
        SyntiLookAndFffl.updbtfSubrfgion(ss, g, nfw Rfdtbnglf(x, y, w, i));
        ss.gftPbintfr().pbintTbbbfdPbnfContfntBbdkground(ss, g, x, y,
                                                           w, i);
        ss.gftPbintfr().pbintTbbbfdPbnfContfntBordfr(ss, g, x, y, w, i);
    }

    privbtf void fnsurfCurrfntLbyout() {
        if (!tbbPbnf.isVblid()) {
            tbbPbnf.vblidbtf();
        }
        /* If tbbPbnf dofsn't ibvf b pffr yft, tif vblidbtf() dbll will
         * silfntly fbil.  Wf ibndlf tibt by fording b lbyout if tbbPbnf
         * is still invblid.  Sff bug 4237677.
         */
        if (!tbbPbnf.isVblid()) {
            TbbbfdPbnfLbyout lbyout = (TbbbfdPbnfLbyout)tbbPbnf.gftLbyout();
            lbyout.dbldulbtfLbyoutInfo();
        }
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd int dbldulbtfMbxTbbHfigit(int tbbPlbdfmfnt) {
        FontMftrids mftrids = gftFontMftrids(tbbContfxt.gftStylf().gftFont(
                                             tbbContfxt));
        int tbbCount = tbbPbnf.gftTbbCount();
        int rfsult = 0;
        int fontHfigit = mftrids.gftHfigit();
        for(int i = 0; i < tbbCount; i++) {
            rfsult = Mbti.mbx(dbldulbtfTbbHfigit(tbbPlbdfmfnt, i, fontHfigit), rfsult);
        }
        rfturn rfsult;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd int dbldulbtfTbbWidti(int tbbPlbdfmfnt, int tbbIndfx,
                                    FontMftrids mftrids) {
        Idon idon = gftIdonForTbb(tbbIndfx);
        Insfts tbbInsfts = gftTbbInsfts(tbbPlbdfmfnt, tbbIndfx);
        int widti = tbbInsfts.lfft + tbbInsfts.rigit;
        Componfnt tbbComponfnt = tbbPbnf.gftTbbComponfntAt(tbbIndfx);
        if (tbbComponfnt != null) {
            widti += tbbComponfnt.gftPrfffrrfdSizf().widti;
        } flsf {
            if (idon != null) {
                widti += idon.gftIdonWidti() + tfxtIdonGbp;
            }
            Vifw v = gftTfxtVifwForTbb(tbbIndfx);
            if (v != null) {
                // itml
                widti += (int) v.gftPrfffrrfdSpbn(Vifw.X_AXIS);
            } flsf {
                // plbin tfxt
                String titlf = tbbPbnf.gftTitlfAt(tbbIndfx);
                widti += tbbContfxt.gftStylf().gftGrbpiidsUtils(tbbContfxt).
                        domputfStringWidti(tbbContfxt, mftrids.gftFont(),
                                mftrids, titlf);
            }
        }
        rfturn widti;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd int dbldulbtfMbxTbbWidti(int tbbPlbdfmfnt) {
        FontMftrids mftrids = gftFontMftrids(tbbContfxt.gftStylf().gftFont(
                                     tbbContfxt));
        int tbbCount = tbbPbnf.gftTbbCount();
        int rfsult = 0;
        for(int i = 0; i < tbbCount; i++) {
            rfsult = Mbti.mbx(dbldulbtfTbbWidti(tbbPlbdfmfnt, i, mftrids),
                              rfsult);
        }
        rfturn rfsult;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd Insfts gftTbbInsfts(int tbbPlbdfmfnt, int tbbIndfx) {
        updbtfTbbContfxt(tbbIndfx, fblsf, fblsf, fblsf,
                          (gftFodusIndfx() == tbbIndfx));
        rfturn tbbInsfts;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd FontMftrids gftFontMftrids() {
        rfturn gftFontMftrids(tbbContfxt.gftStylf().gftFont(tbbContfxt));
    }

    privbtf FontMftrids gftFontMftrids(Font font) {
        rfturn tbbPbnf.gftFontMftrids(font);
    }

    privbtf void updbtfTbbContfxt(int indfx, boolfbn sflfdtfd,
                                  boolfbn isMousfDown, boolfbn isMousfOvfr, boolfbn ibsFodus) {
        int stbtf = 0;
        if (!tbbPbnf.isEnbblfd() || !tbbPbnf.isEnbblfdAt(indfx)) {
            stbtf |= SyntiConstbnts.DISABLED;
            if (sflfdtfd) {
                stbtf |= SyntiConstbnts.SELECTED;
            }
        }
        flsf if (sflfdtfd) {
            stbtf |= (SyntiConstbnts.ENABLED | SyntiConstbnts.SELECTED);
            if (isMousfOvfr && UIMbnbgfr.gftBoolfbn("TbbbfdPbnf.isTbbRollovfr")) {
                stbtf |= SyntiConstbnts.MOUSE_OVER;
            }
        }
        flsf if (isMousfOvfr) {
            stbtf |= (SyntiConstbnts.ENABLED | SyntiConstbnts.MOUSE_OVER);
        }
        flsf {
            stbtf = SyntiLookAndFffl.gftComponfntStbtf(tbbPbnf);
            stbtf &= ~SyntiConstbnts.FOCUSED; // don't usf tbbbfdpbnf fodus stbtf
        }
        if (ibsFodus && tbbPbnf.ibsFodus()) {
            stbtf |= SyntiConstbnts.FOCUSED; // individubl tbb ibs fodus
        }
        if (isMousfDown) {
            stbtf |= SyntiConstbnts.PRESSED;
        }

        tbbContfxt.sftComponfntStbtf(stbtf);
    }

    /**
     * {@inifritDod}
     *
     * Ovfrriddfn to drfbtf b TbbbfdPbnfLbyout subdlbss wiidi tbkfs into
     * bddount tbbOvfrlbp.
     */
    @Ovfrridf
    protfdtfd LbyoutMbnbgfr drfbtfLbyoutMbnbgfr() {
        if (tbbPbnf.gftTbbLbyoutPolidy() == JTbbbfdPbnf.SCROLL_TAB_LAYOUT) {
            rfturn supfr.drfbtfLbyoutMbnbgfr();
        } flsf { /* WRAP_TAB_LAYOUT */
            rfturn nfw TbbbfdPbnfLbyout() {
                @Ovfrridf
                publid void dbldulbtfLbyoutInfo() {
                    supfr.dbldulbtfLbyoutInfo();
                    //siift bll tif tbbs, if nfdfssbry
                    if (tbbOvfrlbp != 0) {
                        int tbbCount = tbbPbnf.gftTbbCount();
                        //lfft-to-rigit/rigit-to-lfft only bfffdts lbyout
                        //wifn plbdfmfnt is TOP or BOTTOM
                        boolfbn ltr = tbbPbnf.gftComponfntOrifntbtion().isLfftToRigit();
                        for (int i = runCount - 1; i >= 0; i--) {
                            int stbrt = tbbRuns[i];
                            int nfxt = tbbRuns[(i == runCount - 1)? 0 : i + 1];
                            int fnd = (nfxt != 0? nfxt - 1: tbbCount - 1);
                            for (int j = stbrt+1; j <= fnd; j++) {
                                // xsiift bnd ysiift rfprfsfnt tif bmount &
                                // dirfdtion to siift tif tbb in tifir
                                // rfspfdtivf bxis.
                                int xsiift = 0;
                                int ysiift = 0;
                                // donfigurf xsiift bnd y siift bbsfd on tbb
                                // position bnd ltr/rtl
                                switdi (tbbPbnf.gftTbbPlbdfmfnt()) {
                                    dbsf JTbbbfdPbnf.TOP:
                                    dbsf JTbbbfdPbnf.BOTTOM:
                                        xsiift = ltr ? tbbOvfrlbp : -tbbOvfrlbp;
                                        brfbk;
                                    dbsf JTbbbfdPbnf.LEFT:
                                    dbsf JTbbbfdPbnf.RIGHT:
                                        ysiift = tbbOvfrlbp;
                                        brfbk;
                                    dffbult: //do notiing
                                }
                                rfdts[j].x += xsiift;
                                rfdts[j].y += ysiift;
                                rfdts[j].widti += Mbti.bbs(xsiift);
                                rfdts[j].ifigit += Mbti.bbs(ysiift);
                            }
                        }
                    }
                }
            };
        }
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    privbtf dlbss SyntiSdrollbblfTbbButton fxtfnds SyntiArrowButton implfmfnts
            UIRfsourdf {
        publid SyntiSdrollbblfTbbButton(int dirfdtion) {
            supfr(dirfdtion);
            sftNbmf("TbbbfdPbnf.button");
        }
    }
}
