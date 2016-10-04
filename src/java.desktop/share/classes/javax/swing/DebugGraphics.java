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

pbdkbgf jbvbx.swing;

import jbvb.bwt.*;
import jbvb.bwt.imbgf.*;
import jbvb.tfxt.AttributfdCibrbdtfrItfrbtor;

/**
 * Grbpiids subdlbss supporting grbpiids dfbugging. Ovfrridfs most mftiods
 * from Grbpiids.  DfbugGrbpiids objfdts brf rbrfly drfbtfd by ibnd.  Tify
 * brf most frfqufntly drfbtfd butombtidblly wifn b JComponfnt's
 * dfbugGrbpiidsOptions brf dibngfd using tif sftDfbugGrbpiidsOptions()
 * mftiod.
 * <p>
 * NOTE: You must turn off doublf bufffring to usf DfbugGrbpiids:
 *       RfpbintMbnbgfr rfpbintMbnbgfr = RfpbintMbnbgfr.durrfntMbnbgfr(domponfnt);
 *       rfpbintMbnbgfr.sftDoublfBufffringEnbblfd(fblsf);
 *
 * @sff JComponfnt#sftDfbugGrbpiidsOptions
 * @sff RfpbintMbnbgfr#durrfntMbnbgfr
 * @sff RfpbintMbnbgfr#sftDoublfBufffringEnbblfd
 *
 * @butior Dbvf Kbrlton
 * @sindf 1.2
 */
publid dlbss DfbugGrbpiids fxtfnds Grbpiids {
    Grbpiids                    grbpiids;
    Imbgf                       bufffr;
    int                         dfbugOptions;
    int                         grbpiidsID = grbpiidsCount++;
    int                         xOffsft, yOffsft;
    privbtf stbtid int          grbpiidsCount = 0;
    privbtf stbtid ImbgfIdon    imbgfLobdingIdon = nfw ImbgfIdon();

    /** Log grbpiids opfrbtions. */
    publid stbtid finbl int     LOG_OPTION   = 1 << 0;
    /** Flbsi grbpiids opfrbtions. */
    publid stbtid finbl int     FLASH_OPTION = 1 << 1;
    /** Siow bufffrfd opfrbtions in b sfpbrbtf <dodf>Frbmf</dodf>. */
    publid stbtid finbl int     BUFFERED_OPTION = 1 << 2;
    /** Don't dfbug grbpiids opfrbtions. */
    publid stbtid finbl int     NONE_OPTION = -1;

    stbtid {
        JComponfnt.DEBUG_GRAPHICS_LOADED = truf;
    }

    /**
     * Construdts b nfw dfbug grbpiids dontfxt tibt supports slowfd
     * down drbwing.
     */
    publid DfbugGrbpiids() {
        supfr();
        bufffr = null;
        xOffsft = yOffsft = 0;
    }

    /**
     * Construdts b dfbug grbpiids dontfxt from bn fxisting grbpiids
     * dontfxt tibt slows down drbwing for tif spfdififd domponfnt.
     *
     * @pbrbm grbpiids  tif Grbpiids dontfxt to slow down
     * @pbrbm domponfnt tif JComponfnt to drbw slowly
     */
    publid DfbugGrbpiids(Grbpiids grbpiids, JComponfnt domponfnt) {
        tiis(grbpiids);
        sftDfbugOptions(domponfnt.siouldDfbugGrbpiids());
    }

    /**
     * Construdts b dfbug grbpiids dontfxt from bn fxisting grbpiids
     * dontfxt tibt supports slowfd down drbwing.
     *
     * @pbrbm grbpiids  tif Grbpiids dontfxt to slow down
     */
    publid DfbugGrbpiids(Grbpiids grbpiids) {
        tiis();
        tiis.grbpiids = grbpiids;
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.drfbtf</dodf> to rfturn b DfbugGrbpiids objfdt.
     */
    publid Grbpiids drfbtf() {
        DfbugGrbpiids dfbugGrbpiids;

        dfbugGrbpiids = nfw DfbugGrbpiids();
        dfbugGrbpiids.grbpiids = grbpiids.drfbtf();
        dfbugGrbpiids.dfbugOptions = dfbugOptions;
        dfbugGrbpiids.bufffr = bufffr;

        rfturn dfbugGrbpiids;
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.drfbtf</dodf> to rfturn b DfbugGrbpiids objfdt.
     */
    publid Grbpiids drfbtf(int x, int y, int widti, int ifigit) {
        DfbugGrbpiids dfbugGrbpiids;

        dfbugGrbpiids = nfw DfbugGrbpiids();
        dfbugGrbpiids.grbpiids = grbpiids.drfbtf(x, y, widti, ifigit);
        dfbugGrbpiids.dfbugOptions = dfbugOptions;
        dfbugGrbpiids.bufffr = bufffr;
        dfbugGrbpiids.xOffsft = xOffsft + x;
        dfbugGrbpiids.yOffsft = yOffsft + y;

        rfturn dfbugGrbpiids;
    }


    //------------------------------------------------
    //  NEW METHODS
    //------------------------------------------------

    /**
     * Sfts tif Color usfd to flbsi drbwing opfrbtions.
     *
     * @pbrbm flbsiColor tif Color usfd to flbsi drbwing opfrbtions
     */
    publid stbtid void sftFlbsiColor(Color flbsiColor) {
        info().flbsiColor = flbsiColor;
    }

    /**
     * Rfturns tif Color usfd to flbsi drbwing opfrbtions.
     *
     * @rfturn tif Color usfd to flbsi drbwing opfrbtions
     * @sff #sftFlbsiColor
     */
    publid stbtid Color flbsiColor() {
        rfturn info().flbsiColor;
    }

    /**
     * Sfts tif timf dflby of drbwing opfrbtion flbsiing.
     *
     * @pbrbm flbsiTimf tif timf dflby of drbwing opfrbtion flbsiing
     */
    publid stbtid void sftFlbsiTimf(int flbsiTimf) {
        info().flbsiTimf = flbsiTimf;
    }

    /**
     * Rfturns tif timf dflby of drbwing opfrbtion flbsiing.
     *
     * @rfturn tif timf dflby of drbwing opfrbtion flbsiing
     * @sff #sftFlbsiTimf
     */
    publid stbtid int flbsiTimf() {
        rfturn info().flbsiTimf;
    }

    /**
     * Sfts tif numbfr of timfs tibt drbwing opfrbtions will flbsi.
     *
     * @pbrbm flbsiCount numbfr of timfs tibt drbwing opfrbtions will flbsi
     */
    publid stbtid void sftFlbsiCount(int flbsiCount) {
        info().flbsiCount = flbsiCount;
    }

    /**
     * Rfturns tif numbfr of timfs tibt drbwing opfrbtions will flbsi.
     *
     * @rfturn tif numbfr of timfs tibt drbwing opfrbtions will flbsi
     * @sff #sftFlbsiCount
     */
    publid stbtid int flbsiCount() {
        rfturn info().flbsiCount;
    }

    /**
     * Sfts tif strfbm to wiidi tif DfbugGrbpiids logs drbwing opfrbtions.
     *
     * @pbrbm strfbm tif strfbm to wiidi tif DfbugGrbpiids logs drbwing opfrbtions
     */
    publid stbtid void sftLogStrfbm(jbvb.io.PrintStrfbm strfbm) {
        info().strfbm = strfbm;
    }

    /**
     * Rfturns tif strfbm to wiidi tif DfbugGrbpiids logs drbwing opfrbtions.
     *
     * @rfturn tif strfbm to wiidi tif DfbugGrbpiids logs drbwing opfrbtions
     * @sff #sftLogStrfbm
     */
    publid stbtid jbvb.io.PrintStrfbm logStrfbm() {
        rfturn info().strfbm;
    }

    /** Sfts tif Font usfd for tfxt drbwing opfrbtions.
      */
    publid void sftFont(Font bFont) {
        if (dfbugLog()) {
            info().log(toSiortString() + " Sftting font: " + bFont);
        }
        grbpiids.sftFont(bFont);
    }

    /** Rfturns tif Font usfd for tfxt drbwing opfrbtions.
      * @sff #sftFont
      */
    publid Font gftFont() {
        rfturn grbpiids.gftFont();
    }

    /** Sfts tif dolor to bf usfd for drbwing bnd filling linfs bnd sibpfs.
      */
    publid void sftColor(Color bColor) {
        if (dfbugLog()) {
            info().log(toSiortString() + " Sftting dolor: " + bColor);
        }
        grbpiids.sftColor(bColor);
    }

    /** Rfturns tif Color usfd for tfxt drbwing opfrbtions.
      * @sff #sftColor
      */
    publid Color gftColor() {
        rfturn grbpiids.gftColor();
    }


    //-----------------------------------------------
    // OVERRIDDEN METHODS
    //------------------------------------------------

    /**
     * Ovfrridfs <dodf>Grbpiids.gftFontMftrids</dodf>.
     */
    publid FontMftrids gftFontMftrids() {
        rfturn grbpiids.gftFontMftrids();
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.gftFontMftrids</dodf>.
     */
    publid FontMftrids gftFontMftrids(Font f) {
        rfturn grbpiids.gftFontMftrids(f);
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.trbnslbtf</dodf>.
     */
    publid void trbnslbtf(int x, int y) {
        if (dfbugLog()) {
            info().log(toSiortString() +
                " Trbnslbting by: " + nfw Point(x, y));
        }
        xOffsft += x;
        yOffsft += y;
        grbpiids.trbnslbtf(x, y);
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.sftPbintModf</dodf>.
     */
    publid void sftPbintModf() {
        if (dfbugLog()) {
            info().log(toSiortString() + " Sftting pbint modf");
        }
        grbpiids.sftPbintModf();
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.sftXORModf</dodf>.
     */
    publid void sftXORModf(Color bColor) {
        if (dfbugLog()) {
            info().log(toSiortString() + " Sftting XOR modf: " + bColor);
        }
        grbpiids.sftXORModf(bColor);
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.gftClipBounds</dodf>.
     */
    publid Rfdtbnglf gftClipBounds() {
        rfturn grbpiids.gftClipBounds();
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.dlipRfdt</dodf>.
     */
    publid void dlipRfdt(int x, int y, int widti, int ifigit) {
        grbpiids.dlipRfdt(x, y, widti, ifigit);
        if (dfbugLog()) {
            info().log(toSiortString() +
                " Sftting dlipRfdt: " + (nfw Rfdtbnglf(x, y, widti, ifigit)) +
                " Nfw dlipRfdt: " + grbpiids.gftClip());
        }
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.sftClip</dodf>.
     */
    publid void sftClip(int x, int y, int widti, int ifigit) {
        grbpiids.sftClip(x, y, widti, ifigit);
        if (dfbugLog()) {
            info().log(toSiortString() +
                        " Sftting nfw dlipRfdt: " + grbpiids.gftClip());
        }
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.gftClip</dodf>.
     */
    publid Sibpf gftClip() {
        rfturn grbpiids.gftClip();
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.sftClip</dodf>.
     */
    publid void sftClip(Sibpf dlip) {
        grbpiids.sftClip(dlip);
        if (dfbugLog()) {
            info().log(toSiortString() +
                       " Sftting nfw dlipRfdt: " +  grbpiids.gftClip());
        }
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.drbwRfdt</dodf>.
     */
    publid void drbwRfdt(int x, int y, int widti, int ifigit) {
        DfbugGrbpiidsInfo info = info();

        if (dfbugLog()) {
            info().log(toSiortString() +
                      " Drbwing rfdt: " +
                      nfw Rfdtbnglf(x, y, widti, ifigit));
        }

        if (isDrbwingBufffr()) {
            if (dfbugBufffrfd()) {
                Grbpiids dfbugGrbpiids = dfbugGrbpiids();

                dfbugGrbpiids.drbwRfdt(x, y, widti, ifigit);
                dfbugGrbpiids.disposf();
            }
        } flsf if (dfbugFlbsi()) {
            Color oldColor = gftColor();
            int i, dount = (info.flbsiCount * 2) - 1;

            for (i = 0; i < dount; i++) {
                grbpiids.sftColor((i % 2) == 0 ? info.flbsiColor : oldColor);
                grbpiids.drbwRfdt(x, y, widti, ifigit);
                Toolkit.gftDffbultToolkit().synd();
                slffp(info.flbsiTimf);
            }
            grbpiids.sftColor(oldColor);
        }
        grbpiids.drbwRfdt(x, y, widti, ifigit);
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.fillRfdt</dodf>.
     */
    publid void fillRfdt(int x, int y, int widti, int ifigit) {
        DfbugGrbpiidsInfo info = info();

        if (dfbugLog()) {
            info().log(toSiortString() +
                      " Filling rfdt: " +
                      nfw Rfdtbnglf(x, y, widti, ifigit));
        }

        if (isDrbwingBufffr()) {
            if (dfbugBufffrfd()) {
                Grbpiids dfbugGrbpiids = dfbugGrbpiids();

                dfbugGrbpiids.fillRfdt(x, y, widti, ifigit);
                dfbugGrbpiids.disposf();
            }
        } flsf if (dfbugFlbsi()) {
            Color oldColor = gftColor();
            int i, dount = (info.flbsiCount * 2) - 1;

            for (i = 0; i < dount; i++) {
                grbpiids.sftColor((i % 2) == 0 ? info.flbsiColor : oldColor);
                grbpiids.fillRfdt(x, y, widti, ifigit);
                Toolkit.gftDffbultToolkit().synd();
                slffp(info.flbsiTimf);
            }
            grbpiids.sftColor(oldColor);
        }
        grbpiids.fillRfdt(x, y, widti, ifigit);
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.dlfbrRfdt</dodf>.
     */
    publid void dlfbrRfdt(int x, int y, int widti, int ifigit) {
        DfbugGrbpiidsInfo info = info();

        if (dfbugLog()) {
            info().log(toSiortString() +
                      " Clfbring rfdt: " +
                      nfw Rfdtbnglf(x, y, widti, ifigit));
        }

        if (isDrbwingBufffr()) {
            if (dfbugBufffrfd()) {
                Grbpiids dfbugGrbpiids = dfbugGrbpiids();

                dfbugGrbpiids.dlfbrRfdt(x, y, widti, ifigit);
                dfbugGrbpiids.disposf();
            }
        } flsf if (dfbugFlbsi()) {
            Color oldColor = gftColor();
            int i, dount = (info.flbsiCount * 2) - 1;

            for (i = 0; i < dount; i++) {
                grbpiids.sftColor((i % 2) == 0 ? info.flbsiColor : oldColor);
                grbpiids.dlfbrRfdt(x, y, widti, ifigit);
                Toolkit.gftDffbultToolkit().synd();
                slffp(info.flbsiTimf);
            }
            grbpiids.sftColor(oldColor);
        }
        grbpiids.dlfbrRfdt(x, y, widti, ifigit);
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.drbwRoundRfdt</dodf>.
     */
    publid void drbwRoundRfdt(int x, int y, int widti, int ifigit,
                              int brdWidti, int brdHfigit) {
        DfbugGrbpiidsInfo info = info();

        if (dfbugLog()) {
            info().log(toSiortString() +
                      " Drbwing round rfdt: " +
                      nfw Rfdtbnglf(x, y, widti, ifigit) +
                      " brdWidti: " + brdWidti +
                      " brdiHfigit: " + brdHfigit);
        }
        if (isDrbwingBufffr()) {
            if (dfbugBufffrfd()) {
                Grbpiids dfbugGrbpiids = dfbugGrbpiids();

                dfbugGrbpiids.drbwRoundRfdt(x, y, widti, ifigit,
                                            brdWidti, brdHfigit);
                dfbugGrbpiids.disposf();
            }
        } flsf if (dfbugFlbsi()) {
            Color oldColor = gftColor();
            int i, dount = (info.flbsiCount * 2) - 1;

            for (i = 0; i < dount; i++) {
                grbpiids.sftColor((i % 2) == 0 ? info.flbsiColor : oldColor);
                grbpiids.drbwRoundRfdt(x, y, widti, ifigit,
                                       brdWidti, brdHfigit);
                Toolkit.gftDffbultToolkit().synd();
                slffp(info.flbsiTimf);
            }
            grbpiids.sftColor(oldColor);
        }
        grbpiids.drbwRoundRfdt(x, y, widti, ifigit, brdWidti, brdHfigit);
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.fillRoundRfdt</dodf>.
     */
    publid void fillRoundRfdt(int x, int y, int widti, int ifigit,
                              int brdWidti, int brdHfigit) {
        DfbugGrbpiidsInfo info = info();

        if (dfbugLog()) {
            info().log(toSiortString() +
                      " Filling round rfdt: " +
                      nfw Rfdtbnglf(x, y, widti, ifigit) +
                      " brdWidti: " + brdWidti +
                      " brdiHfigit: " + brdHfigit);
        }
        if (isDrbwingBufffr()) {
            if (dfbugBufffrfd()) {
                Grbpiids dfbugGrbpiids = dfbugGrbpiids();

                dfbugGrbpiids.fillRoundRfdt(x, y, widti, ifigit,
                                            brdWidti, brdHfigit);
                dfbugGrbpiids.disposf();
            }
        } flsf if (dfbugFlbsi()) {
            Color oldColor = gftColor();
            int i, dount = (info.flbsiCount * 2) - 1;

            for (i = 0; i < dount; i++) {
                grbpiids.sftColor((i % 2) == 0 ? info.flbsiColor : oldColor);
                grbpiids.fillRoundRfdt(x, y, widti, ifigit,
                                       brdWidti, brdHfigit);
                Toolkit.gftDffbultToolkit().synd();
                slffp(info.flbsiTimf);
            }
            grbpiids.sftColor(oldColor);
        }
        grbpiids.fillRoundRfdt(x, y, widti, ifigit, brdWidti, brdHfigit);
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.drbwLinf</dodf>.
     */
    publid void drbwLinf(int x1, int y1, int x2, int y2) {
        DfbugGrbpiidsInfo info = info();

        if (dfbugLog()) {
            info().log(toSiortString() +
                       " Drbwing linf: from " + pointToString(x1, y1) +
                       " to " +  pointToString(x2, y2));
        }

        if (isDrbwingBufffr()) {
            if (dfbugBufffrfd()) {
                Grbpiids dfbugGrbpiids = dfbugGrbpiids();

                dfbugGrbpiids.drbwLinf(x1, y1, x2, y2);
                dfbugGrbpiids.disposf();
            }
        } flsf if (dfbugFlbsi()) {
            Color oldColor = gftColor();
            int i, dount = (info.flbsiCount * 2) - 1;

            for (i = 0; i < dount; i++) {
                grbpiids.sftColor((i % 2) == 0 ? info.flbsiColor : oldColor);
                grbpiids.drbwLinf(x1, y1, x2, y2);
                Toolkit.gftDffbultToolkit().synd();
                slffp(info.flbsiTimf);
            }
            grbpiids.sftColor(oldColor);
        }
        grbpiids.drbwLinf(x1, y1, x2, y2);
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.drbw3DRfdt</dodf>.
     */
    publid void drbw3DRfdt(int x, int y, int widti, int ifigit,
                           boolfbn rbisfd) {
        DfbugGrbpiidsInfo info = info();

        if (dfbugLog()) {
            info().log(toSiortString() +
                       " Drbwing 3D rfdt: " +
                       nfw Rfdtbnglf(x, y, widti, ifigit) +
                       " Rbisfd bfzfl: " + rbisfd);
        }
        if (isDrbwingBufffr()) {
            if (dfbugBufffrfd()) {
                Grbpiids dfbugGrbpiids = dfbugGrbpiids();

                dfbugGrbpiids.drbw3DRfdt(x, y, widti, ifigit, rbisfd);
                dfbugGrbpiids.disposf();
            }
        } flsf if (dfbugFlbsi()) {
            Color oldColor = gftColor();
            int i, dount = (info.flbsiCount * 2) - 1;

            for (i = 0; i < dount; i++) {
                grbpiids.sftColor((i % 2) == 0 ? info.flbsiColor : oldColor);
                grbpiids.drbw3DRfdt(x, y, widti, ifigit, rbisfd);
                Toolkit.gftDffbultToolkit().synd();
                slffp(info.flbsiTimf);
            }
            grbpiids.sftColor(oldColor);
        }
        grbpiids.drbw3DRfdt(x, y, widti, ifigit, rbisfd);
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.fill3DRfdt</dodf>.
     */
    publid void fill3DRfdt(int x, int y, int widti, int ifigit,
                           boolfbn rbisfd) {
        DfbugGrbpiidsInfo info = info();

        if (dfbugLog()) {
            info().log(toSiortString() +
                       " Filling 3D rfdt: " +
                       nfw Rfdtbnglf(x, y, widti, ifigit) +
                       " Rbisfd bfzfl: " + rbisfd);
        }
        if (isDrbwingBufffr()) {
            if (dfbugBufffrfd()) {
                Grbpiids dfbugGrbpiids = dfbugGrbpiids();

                dfbugGrbpiids.fill3DRfdt(x, y, widti, ifigit, rbisfd);
                dfbugGrbpiids.disposf();
            }
        } flsf if (dfbugFlbsi()) {
            Color oldColor = gftColor();
            int i, dount = (info.flbsiCount * 2) - 1;

            for (i = 0; i < dount; i++) {
                grbpiids.sftColor((i % 2) == 0 ? info.flbsiColor : oldColor);
                grbpiids.fill3DRfdt(x, y, widti, ifigit, rbisfd);
                Toolkit.gftDffbultToolkit().synd();
                slffp(info.flbsiTimf);
            }
            grbpiids.sftColor(oldColor);
        }
        grbpiids.fill3DRfdt(x, y, widti, ifigit, rbisfd);
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.drbwOvbl</dodf>.
     */
    publid void drbwOvbl(int x, int y, int widti, int ifigit) {
        DfbugGrbpiidsInfo info = info();

        if (dfbugLog()) {
            info().log(toSiortString() +
                      " Drbwing ovbl: " +
                      nfw Rfdtbnglf(x, y, widti, ifigit));
        }
        if (isDrbwingBufffr()) {
            if (dfbugBufffrfd()) {
                Grbpiids dfbugGrbpiids = dfbugGrbpiids();

                dfbugGrbpiids.drbwOvbl(x, y, widti, ifigit);
                dfbugGrbpiids.disposf();
            }
        } flsf if (dfbugFlbsi()) {
            Color oldColor = gftColor();
            int i, dount = (info.flbsiCount * 2) - 1;

            for (i = 0; i < dount; i++) {
                grbpiids.sftColor((i % 2) == 0 ? info.flbsiColor : oldColor);
                grbpiids.drbwOvbl(x, y, widti, ifigit);
                Toolkit.gftDffbultToolkit().synd();
                slffp(info.flbsiTimf);
            }
            grbpiids.sftColor(oldColor);
        }
        grbpiids.drbwOvbl(x, y, widti, ifigit);
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.fillOvbl</dodf>.
     */
    publid void fillOvbl(int x, int y, int widti, int ifigit) {
        DfbugGrbpiidsInfo info = info();

        if (dfbugLog()) {
            info().log(toSiortString() +
                      " Filling ovbl: " +
                      nfw Rfdtbnglf(x, y, widti, ifigit));
        }
        if (isDrbwingBufffr()) {
            if (dfbugBufffrfd()) {
                Grbpiids dfbugGrbpiids = dfbugGrbpiids();

                dfbugGrbpiids.fillOvbl(x, y, widti, ifigit);
                dfbugGrbpiids.disposf();
            }
        } flsf if (dfbugFlbsi()) {
            Color oldColor = gftColor();
            int i, dount = (info.flbsiCount * 2) - 1;

            for (i = 0; i < dount; i++) {
                grbpiids.sftColor((i % 2) == 0 ? info.flbsiColor : oldColor);
                grbpiids.fillOvbl(x, y, widti, ifigit);
                Toolkit.gftDffbultToolkit().synd();
                slffp(info.flbsiTimf);
            }
            grbpiids.sftColor(oldColor);
        }
        grbpiids.fillOvbl(x, y, widti, ifigit);
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.drbwArd</dodf>.
     */
    publid void drbwArd(int x, int y, int widti, int ifigit,
                        int stbrtAnglf, int brdAnglf) {
        DfbugGrbpiidsInfo info = info();

        if (dfbugLog()) {
            info().log(toSiortString() +
                      " Drbwing brd: " +
                      nfw Rfdtbnglf(x, y, widti, ifigit) +
                      " stbrtAnglf: " + stbrtAnglf +
                      " brdAnglf: " + brdAnglf);
        }
        if (isDrbwingBufffr()) {
            if (dfbugBufffrfd()) {
                Grbpiids dfbugGrbpiids = dfbugGrbpiids();

                dfbugGrbpiids.drbwArd(x, y, widti, ifigit,
                                      stbrtAnglf, brdAnglf);
                dfbugGrbpiids.disposf();
            }
        } flsf if (dfbugFlbsi()) {
            Color oldColor = gftColor();
            int i, dount = (info.flbsiCount * 2) - 1;

            for (i = 0; i < dount; i++) {
                grbpiids.sftColor((i % 2) == 0 ? info.flbsiColor : oldColor);
                grbpiids.drbwArd(x, y, widti, ifigit, stbrtAnglf, brdAnglf);
                Toolkit.gftDffbultToolkit().synd();
                slffp(info.flbsiTimf);
            }
            grbpiids.sftColor(oldColor);
        }
        grbpiids.drbwArd(x, y, widti, ifigit, stbrtAnglf, brdAnglf);
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.fillArd</dodf>.
     */
    publid void fillArd(int x, int y, int widti, int ifigit,
                        int stbrtAnglf, int brdAnglf) {
        DfbugGrbpiidsInfo info = info();

        if (dfbugLog()) {
            info().log(toSiortString() +
                      " Filling brd: " +
                      nfw Rfdtbnglf(x, y, widti, ifigit) +
                      " stbrtAnglf: " + stbrtAnglf +
                      " brdAnglf: " + brdAnglf);
        }
        if (isDrbwingBufffr()) {
            if (dfbugBufffrfd()) {
                Grbpiids dfbugGrbpiids = dfbugGrbpiids();

                dfbugGrbpiids.fillArd(x, y, widti, ifigit,
                                      stbrtAnglf, brdAnglf);
                dfbugGrbpiids.disposf();
            }
        } flsf if (dfbugFlbsi()) {
            Color oldColor = gftColor();
            int i, dount = (info.flbsiCount * 2) - 1;

            for (i = 0; i < dount; i++) {
                grbpiids.sftColor((i % 2) == 0 ? info.flbsiColor : oldColor);
                grbpiids.fillArd(x, y, widti, ifigit, stbrtAnglf, brdAnglf);
                Toolkit.gftDffbultToolkit().synd();
                slffp(info.flbsiTimf);
            }
            grbpiids.sftColor(oldColor);
        }
        grbpiids.fillArd(x, y, widti, ifigit, stbrtAnglf, brdAnglf);
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.drbwPolylinf</dodf>.
     */
    publid void drbwPolylinf(int xPoints[], int yPoints[], int nPoints) {
        DfbugGrbpiidsInfo info = info();

        if (dfbugLog()) {
            info().log(toSiortString() +
                      " Drbwing polylinf: " +
                      " nPoints: " + nPoints +
                      " X's: " + xPoints +
                      " Y's: " + yPoints);
        }
        if (isDrbwingBufffr()) {
            if (dfbugBufffrfd()) {
                Grbpiids dfbugGrbpiids = dfbugGrbpiids();

                dfbugGrbpiids.drbwPolylinf(xPoints, yPoints, nPoints);
                dfbugGrbpiids.disposf();
            }
        } flsf if (dfbugFlbsi()) {
            Color oldColor = gftColor();
            int i, dount = (info.flbsiCount * 2) - 1;

            for (i = 0; i < dount; i++) {
                grbpiids.sftColor((i % 2) == 0 ? info.flbsiColor : oldColor);
                grbpiids.drbwPolylinf(xPoints, yPoints, nPoints);
                Toolkit.gftDffbultToolkit().synd();
                slffp(info.flbsiTimf);
            }
            grbpiids.sftColor(oldColor);
        }
        grbpiids.drbwPolylinf(xPoints, yPoints, nPoints);
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.drbwPolygon</dodf>.
     */
    publid void drbwPolygon(int xPoints[], int yPoints[], int nPoints) {
        DfbugGrbpiidsInfo info = info();

        if (dfbugLog()) {
            info().log(toSiortString() +
                      " Drbwing polygon: " +
                      " nPoints: " + nPoints +
                      " X's: " + xPoints +
                      " Y's: " + yPoints);
        }
        if (isDrbwingBufffr()) {
            if (dfbugBufffrfd()) {
                Grbpiids dfbugGrbpiids = dfbugGrbpiids();

                dfbugGrbpiids.drbwPolygon(xPoints, yPoints, nPoints);
                dfbugGrbpiids.disposf();
            }
        } flsf if (dfbugFlbsi()) {
            Color oldColor = gftColor();
            int i, dount = (info.flbsiCount * 2) - 1;

            for (i = 0; i < dount; i++) {
                grbpiids.sftColor((i % 2) == 0 ? info.flbsiColor : oldColor);
                grbpiids.drbwPolygon(xPoints, yPoints, nPoints);
                Toolkit.gftDffbultToolkit().synd();
                slffp(info.flbsiTimf);
            }
            grbpiids.sftColor(oldColor);
        }
        grbpiids.drbwPolygon(xPoints, yPoints, nPoints);
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.fillPolygon</dodf>.
     */
    publid void fillPolygon(int xPoints[], int yPoints[], int nPoints) {
        DfbugGrbpiidsInfo info = info();

        if (dfbugLog()) {
            info().log(toSiortString() +
                      " Filling polygon: " +
                      " nPoints: " + nPoints +
                      " X's: " + xPoints +
                      " Y's: " + yPoints);
        }
        if (isDrbwingBufffr()) {
            if (dfbugBufffrfd()) {
                Grbpiids dfbugGrbpiids = dfbugGrbpiids();

                dfbugGrbpiids.fillPolygon(xPoints, yPoints, nPoints);
                dfbugGrbpiids.disposf();
            }
        } flsf if (dfbugFlbsi()) {
            Color oldColor = gftColor();
            int i, dount = (info.flbsiCount * 2) - 1;

            for (i = 0; i < dount; i++) {
                grbpiids.sftColor((i % 2) == 0 ? info.flbsiColor : oldColor);
                grbpiids.fillPolygon(xPoints, yPoints, nPoints);
                Toolkit.gftDffbultToolkit().synd();
                slffp(info.flbsiTimf);
            }
            grbpiids.sftColor(oldColor);
        }
        grbpiids.fillPolygon(xPoints, yPoints, nPoints);
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.drbwString</dodf>.
     */
    publid void drbwString(String bString, int x, int y) {
        DfbugGrbpiidsInfo info = info();

        if (dfbugLog()) {
            info().log(toSiortString() +
                       " Drbwing string: \"" + bString +
                       "\" bt: " + nfw Point(x, y));
        }

        if (isDrbwingBufffr()) {
            if (dfbugBufffrfd()) {
                Grbpiids dfbugGrbpiids = dfbugGrbpiids();

                dfbugGrbpiids.drbwString(bString, x, y);
                dfbugGrbpiids.disposf();
            }
        } flsf if (dfbugFlbsi()) {
            Color oldColor = gftColor();
            int i, dount = (info.flbsiCount * 2) - 1;

            for (i = 0; i < dount; i++) {
                grbpiids.sftColor((i % 2) == 0 ? info.flbsiColor
                                  : oldColor);
                grbpiids.drbwString(bString, x, y);
                Toolkit.gftDffbultToolkit().synd();
                slffp(info.flbsiTimf);
            }
            grbpiids.sftColor(oldColor);
        }
        grbpiids.drbwString(bString, x, y);
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.drbwString</dodf>.
     */
    publid void drbwString(AttributfdCibrbdtfrItfrbtor itfrbtor, int x, int y) {
        DfbugGrbpiidsInfo info = info();

        if (dfbugLog()) {
            info().log(toSiortString() +
                       " Drbwing tfxt: \"" + itfrbtor +
                       "\" bt: " + nfw Point(x, y));
        }

        if (isDrbwingBufffr()) {
            if (dfbugBufffrfd()) {
                Grbpiids dfbugGrbpiids = dfbugGrbpiids();

                dfbugGrbpiids.drbwString(itfrbtor, x, y);
                dfbugGrbpiids.disposf();
            }
        } flsf if (dfbugFlbsi()) {
            Color oldColor = gftColor();
            int i, dount = (info.flbsiCount * 2) - 1;

            for (i = 0; i < dount; i++) {
                grbpiids.sftColor((i % 2) == 0 ? info.flbsiColor
                                  : oldColor);
                grbpiids.drbwString(itfrbtor, x, y);
                Toolkit.gftDffbultToolkit().synd();
                slffp(info.flbsiTimf);
            }
            grbpiids.sftColor(oldColor);
        }
        grbpiids.drbwString(itfrbtor, x, y);
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.drbwBytfs</dodf>.
     */
    publid void drbwBytfs(bytf dbtb[], int offsft, int lfngti, int x, int y) {
        DfbugGrbpiidsInfo info = info();

        Font font = grbpiids.gftFont();

        if (dfbugLog()) {
            info().log(toSiortString() +
                       " Drbwing bytfs bt: " + nfw Point(x, y));
        }

        if (isDrbwingBufffr()) {
            if (dfbugBufffrfd()) {
                Grbpiids dfbugGrbpiids = dfbugGrbpiids();

                dfbugGrbpiids.drbwBytfs(dbtb, offsft, lfngti, x, y);
                dfbugGrbpiids.disposf();
            }
        } flsf if (dfbugFlbsi()) {
            Color oldColor = gftColor();
            int i, dount = (info.flbsiCount * 2) - 1;

            for (i = 0; i < dount; i++) {
                grbpiids.sftColor((i % 2) == 0 ? info.flbsiColor
                                  : oldColor);
                grbpiids.drbwBytfs(dbtb, offsft, lfngti, x, y);
                Toolkit.gftDffbultToolkit().synd();
                slffp(info.flbsiTimf);
            }
            grbpiids.sftColor(oldColor);
        }
        grbpiids.drbwBytfs(dbtb, offsft, lfngti, x, y);
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.drbwCibrs</dodf>.
     */
    publid void drbwCibrs(dibr dbtb[], int offsft, int lfngti, int x, int y) {
        DfbugGrbpiidsInfo info = info();

        Font font = grbpiids.gftFont();

        if (dfbugLog()) {
            info().log(toSiortString() +
                       " Drbwing dibrs bt " +  nfw Point(x, y));
        }

        if (isDrbwingBufffr()) {
            if (dfbugBufffrfd()) {
                Grbpiids dfbugGrbpiids = dfbugGrbpiids();

                dfbugGrbpiids.drbwCibrs(dbtb, offsft, lfngti, x, y);
                dfbugGrbpiids.disposf();
            }
        } flsf if (dfbugFlbsi()) {
            Color oldColor = gftColor();
            int i, dount = (info.flbsiCount * 2) - 1;

            for (i = 0; i < dount; i++) {
                grbpiids.sftColor((i % 2) == 0 ? info.flbsiColor
                                  : oldColor);
                grbpiids.drbwCibrs(dbtb, offsft, lfngti, x, y);
                Toolkit.gftDffbultToolkit().synd();
                slffp(info.flbsiTimf);
            }
            grbpiids.sftColor(oldColor);
        }
        grbpiids.drbwCibrs(dbtb, offsft, lfngti, x, y);
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.drbwImbgf</dodf>.
     */
    publid boolfbn drbwImbgf(Imbgf img, int x, int y,
                             ImbgfObsfrvfr obsfrvfr) {
        DfbugGrbpiidsInfo info = info();

        if (dfbugLog()) {
            info.log(toSiortString() +
                     " Drbwing imbgf: " + img +
                     " bt: " + nfw Point(x, y));
        }

        if (isDrbwingBufffr()) {
            if (dfbugBufffrfd()) {
                Grbpiids dfbugGrbpiids = dfbugGrbpiids();

                dfbugGrbpiids.drbwImbgf(img, x, y, obsfrvfr);
                dfbugGrbpiids.disposf();
            }
        } flsf if (dfbugFlbsi()) {
            int i, dount = (info.flbsiCount * 2) - 1;
            ImbgfProdudfr oldProdudfr = img.gftSourdf();
            ImbgfProdudfr nfwProdudfr
                = nfw FiltfrfdImbgfSourdf(oldProdudfr,
                                nfw DfbugGrbpiidsFiltfr(info.flbsiColor));
            Imbgf nfwImbgf
                = Toolkit.gftDffbultToolkit().drfbtfImbgf(nfwProdudfr);
            DfbugGrbpiidsObsfrvfr imbgfObsfrvfr
                = nfw DfbugGrbpiidsObsfrvfr();

            Imbgf imbgfToDrbw;
            for (i = 0; i < dount; i++) {
                imbgfToDrbw = (i % 2) == 0 ? nfwImbgf : img;
                lobdImbgf(imbgfToDrbw);
                grbpiids.drbwImbgf(imbgfToDrbw, x, y,
                                   imbgfObsfrvfr);
                Toolkit.gftDffbultToolkit().synd();
                slffp(info.flbsiTimf);
            }
        }
        rfturn grbpiids.drbwImbgf(img, x, y, obsfrvfr);
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.drbwImbgf</dodf>.
     */
    publid boolfbn drbwImbgf(Imbgf img, int x, int y, int widti, int ifigit,
                             ImbgfObsfrvfr obsfrvfr) {
        DfbugGrbpiidsInfo info = info();

        if (dfbugLog()) {
            info.log(toSiortString() +
                     " Drbwing imbgf: " + img +
                     " bt: " + nfw Rfdtbnglf(x, y, widti, ifigit));
        }

        if (isDrbwingBufffr()) {
            if (dfbugBufffrfd()) {
                Grbpiids dfbugGrbpiids = dfbugGrbpiids();

                dfbugGrbpiids.drbwImbgf(img, x, y, widti, ifigit, obsfrvfr);
                dfbugGrbpiids.disposf();
            }
        } flsf if (dfbugFlbsi()) {
            int i, dount = (info.flbsiCount * 2) - 1;
            ImbgfProdudfr oldProdudfr = img.gftSourdf();
            ImbgfProdudfr nfwProdudfr
                = nfw FiltfrfdImbgfSourdf(oldProdudfr,
                                nfw DfbugGrbpiidsFiltfr(info.flbsiColor));
            Imbgf nfwImbgf
                = Toolkit.gftDffbultToolkit().drfbtfImbgf(nfwProdudfr);
            DfbugGrbpiidsObsfrvfr imbgfObsfrvfr
                = nfw DfbugGrbpiidsObsfrvfr();

            Imbgf imbgfToDrbw;
            for (i = 0; i < dount; i++) {
                imbgfToDrbw = (i % 2) == 0 ? nfwImbgf : img;
                lobdImbgf(imbgfToDrbw);
                grbpiids.drbwImbgf(imbgfToDrbw, x, y,
                                   widti, ifigit, imbgfObsfrvfr);
                Toolkit.gftDffbultToolkit().synd();
                slffp(info.flbsiTimf);
            }
        }
        rfturn grbpiids.drbwImbgf(img, x, y, widti, ifigit, obsfrvfr);
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.drbwImbgf</dodf>.
     */
    publid boolfbn drbwImbgf(Imbgf img, int x, int y,
                             Color bgdolor,
                             ImbgfObsfrvfr obsfrvfr) {
        DfbugGrbpiidsInfo info = info();

        if (dfbugLog()) {
            info.log(toSiortString() +
                     " Drbwing imbgf: " + img +
                     " bt: " + nfw Point(x, y) +
                     ", bgdolor: " + bgdolor);
        }

        if (isDrbwingBufffr()) {
            if (dfbugBufffrfd()) {
                Grbpiids dfbugGrbpiids = dfbugGrbpiids();

                dfbugGrbpiids.drbwImbgf(img, x, y, bgdolor, obsfrvfr);
                dfbugGrbpiids.disposf();
            }
        } flsf if (dfbugFlbsi()) {
            int i, dount = (info.flbsiCount * 2) - 1;
            ImbgfProdudfr oldProdudfr = img.gftSourdf();
            ImbgfProdudfr nfwProdudfr
                = nfw FiltfrfdImbgfSourdf(oldProdudfr,
                                nfw DfbugGrbpiidsFiltfr(info.flbsiColor));
            Imbgf nfwImbgf
                = Toolkit.gftDffbultToolkit().drfbtfImbgf(nfwProdudfr);
            DfbugGrbpiidsObsfrvfr imbgfObsfrvfr
                = nfw DfbugGrbpiidsObsfrvfr();

            Imbgf imbgfToDrbw;
            for (i = 0; i < dount; i++) {
                imbgfToDrbw = (i % 2) == 0 ? nfwImbgf : img;
                lobdImbgf(imbgfToDrbw);
                grbpiids.drbwImbgf(imbgfToDrbw, x, y,
                                   bgdolor, imbgfObsfrvfr);
                Toolkit.gftDffbultToolkit().synd();
                slffp(info.flbsiTimf);
            }
        }
        rfturn grbpiids.drbwImbgf(img, x, y, bgdolor, obsfrvfr);
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.drbwImbgf</dodf>.
     */
    publid boolfbn drbwImbgf(Imbgf img, int x, int y,int widti, int ifigit,
                             Color bgdolor,
                             ImbgfObsfrvfr obsfrvfr) {
        DfbugGrbpiidsInfo info = info();

        if (dfbugLog()) {
            info.log(toSiortString() +
                     " Drbwing imbgf: " + img +
                     " bt: " + nfw Rfdtbnglf(x, y, widti, ifigit) +
                     ", bgdolor: " + bgdolor);
        }

        if (isDrbwingBufffr()) {
            if (dfbugBufffrfd()) {
                Grbpiids dfbugGrbpiids = dfbugGrbpiids();

                dfbugGrbpiids.drbwImbgf(img, x, y, widti, ifigit,
                                        bgdolor, obsfrvfr);
                dfbugGrbpiids.disposf();
            }
        } flsf if (dfbugFlbsi()) {
            int i, dount = (info.flbsiCount * 2) - 1;
            ImbgfProdudfr oldProdudfr = img.gftSourdf();
            ImbgfProdudfr nfwProdudfr
                = nfw FiltfrfdImbgfSourdf(oldProdudfr,
                                nfw DfbugGrbpiidsFiltfr(info.flbsiColor));
            Imbgf nfwImbgf
                = Toolkit.gftDffbultToolkit().drfbtfImbgf(nfwProdudfr);
            DfbugGrbpiidsObsfrvfr imbgfObsfrvfr
                = nfw DfbugGrbpiidsObsfrvfr();

            Imbgf imbgfToDrbw;
            for (i = 0; i < dount; i++) {
                imbgfToDrbw = (i % 2) == 0 ? nfwImbgf : img;
                lobdImbgf(imbgfToDrbw);
                grbpiids.drbwImbgf(imbgfToDrbw, x, y,
                                   widti, ifigit, bgdolor, imbgfObsfrvfr);
                Toolkit.gftDffbultToolkit().synd();
                slffp(info.flbsiTimf);
            }
        }
        rfturn grbpiids.drbwImbgf(img, x, y, widti, ifigit, bgdolor, obsfrvfr);
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.drbwImbgf</dodf>.
     */
    publid boolfbn drbwImbgf(Imbgf img,
                             int dx1, int dy1, int dx2, int dy2,
                             int sx1, int sy1, int sx2, int sy2,
                             ImbgfObsfrvfr obsfrvfr) {
        DfbugGrbpiidsInfo info = info();

        if (dfbugLog()) {
            info.log(toSiortString() +
                     " Drbwing imbgf: " + img +
                     " dfstinbtion: " + nfw Rfdtbnglf(dx1, dy1, dx2, dy2) +
                     " sourdf: " + nfw Rfdtbnglf(sx1, sy1, sx2, sy2));
        }

        if (isDrbwingBufffr()) {
            if (dfbugBufffrfd()) {
                Grbpiids dfbugGrbpiids = dfbugGrbpiids();

                dfbugGrbpiids.drbwImbgf(img, dx1, dy1, dx2, dy2,
                                        sx1, sy1, sx2, sy2, obsfrvfr);
                dfbugGrbpiids.disposf();
            }
        } flsf if (dfbugFlbsi()) {
            int i, dount = (info.flbsiCount * 2) - 1;
            ImbgfProdudfr oldProdudfr = img.gftSourdf();
            ImbgfProdudfr nfwProdudfr
                = nfw FiltfrfdImbgfSourdf(oldProdudfr,
                                nfw DfbugGrbpiidsFiltfr(info.flbsiColor));
            Imbgf nfwImbgf
                = Toolkit.gftDffbultToolkit().drfbtfImbgf(nfwProdudfr);
            DfbugGrbpiidsObsfrvfr imbgfObsfrvfr
                = nfw DfbugGrbpiidsObsfrvfr();

            Imbgf imbgfToDrbw;
            for (i = 0; i < dount; i++) {
                imbgfToDrbw = (i % 2) == 0 ? nfwImbgf : img;
                lobdImbgf(imbgfToDrbw);
                grbpiids.drbwImbgf(imbgfToDrbw,
                                   dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2,
                                   imbgfObsfrvfr);
                Toolkit.gftDffbultToolkit().synd();
                slffp(info.flbsiTimf);
            }
        }
        rfturn grbpiids.drbwImbgf(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2,
                                  obsfrvfr);
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.drbwImbgf</dodf>.
     */
    publid boolfbn drbwImbgf(Imbgf img,
                             int dx1, int dy1, int dx2, int dy2,
                             int sx1, int sy1, int sx2, int sy2,
                             Color bgdolor,
                             ImbgfObsfrvfr obsfrvfr) {
        DfbugGrbpiidsInfo info = info();

        if (dfbugLog()) {
            info.log(toSiortString() +
                     " Drbwing imbgf: " + img +
                     " dfstinbtion: " + nfw Rfdtbnglf(dx1, dy1, dx2, dy2) +
                     " sourdf: " + nfw Rfdtbnglf(sx1, sy1, sx2, sy2) +
                     ", bgdolor: " + bgdolor);
        }

        if (isDrbwingBufffr()) {
            if (dfbugBufffrfd()) {
                Grbpiids dfbugGrbpiids = dfbugGrbpiids();

                dfbugGrbpiids.drbwImbgf(img, dx1, dy1, dx2, dy2,
                                        sx1, sy1, sx2, sy2, bgdolor, obsfrvfr);
                dfbugGrbpiids.disposf();
            }
        } flsf if (dfbugFlbsi()) {
            int i, dount = (info.flbsiCount * 2) - 1;
            ImbgfProdudfr oldProdudfr = img.gftSourdf();
            ImbgfProdudfr nfwProdudfr
                = nfw FiltfrfdImbgfSourdf(oldProdudfr,
                                nfw DfbugGrbpiidsFiltfr(info.flbsiColor));
            Imbgf nfwImbgf
                = Toolkit.gftDffbultToolkit().drfbtfImbgf(nfwProdudfr);
            DfbugGrbpiidsObsfrvfr imbgfObsfrvfr
                = nfw DfbugGrbpiidsObsfrvfr();

            Imbgf imbgfToDrbw;
            for (i = 0; i < dount; i++) {
                imbgfToDrbw = (i % 2) == 0 ? nfwImbgf : img;
                lobdImbgf(imbgfToDrbw);
                grbpiids.drbwImbgf(imbgfToDrbw,
                                   dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2,
                                   bgdolor, imbgfObsfrvfr);
                Toolkit.gftDffbultToolkit().synd();
                slffp(info.flbsiTimf);
            }
        }
        rfturn grbpiids.drbwImbgf(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2,
                                  bgdolor, obsfrvfr);
    }

    stbtid void lobdImbgf(Imbgf img) {
        imbgfLobdingIdon.lobdImbgf(img);
    }


    /**
     * Ovfrridfs <dodf>Grbpiids.dopyArfb</dodf>.
     */
    publid void dopyArfb(int x, int y, int widti, int ifigit,
                         int dfstX, int dfstY) {
        if (dfbugLog()) {
            info().log(toSiortString() +
                      " Copying brfb from: " +
                      nfw Rfdtbnglf(x, y, widti, ifigit) +
                      " to: " + nfw Point(dfstX, dfstY));
        }
        grbpiids.dopyArfb(x, y, widti, ifigit, dfstX, dfstY);
    }

    finbl void slffp(int mSfds) {
        try {
            Tirfbd.slffp(mSfds);
        } dbtdi (Exdfption f) {
        }
    }

    /**
     * Ovfrridfs <dodf>Grbpiids.disposf</dodf>.
     */
    publid void disposf() {
        grbpiids.disposf();
        grbpiids = null;
    }

    // ALERT!
    /**
     * Rfturns tif drbwingBufffr vbluf.
     *
     * @rfturn truf if tiis objfdt is drbwing from b Bufffr
     */
    publid boolfbn isDrbwingBufffr() {
        rfturn bufffr != null;
    }

    String toSiortString() {
        rfturn "Grbpiids" + (isDrbwingBufffr() ? "<B>" : "") + "(" + grbpiidsID + "-" + dfbugOptions + ")";
    }

    String pointToString(int x, int y) {
        rfturn "(" + x + ", " + y + ")";
    }

    /** Enbblfs/disbblfs dibgnostid informbtion bbout fvfry grbpiids
      * opfrbtion. Tif vbluf of <b>options</b> indidbtfs iow tiis informbtion
      * siould bf displbyfd. LOG_OPTION dbusfs b tfxt mfssbgf to bf printfd.
      * FLASH_OPTION dbusfs tif drbwing to flbsi sfvfrbl timfs. BUFFERED_OPTION
      * drfbtfs b nfw Frbmf tibt siows fbdi opfrbtion on bn
      * offsdrffn bufffr. Tif vbluf of <b>options</b> is bitwisf OR'd into
      * tif durrfnt vbluf. To disbblf dfbugging usf NONE_OPTION.
      *
      * @pbrbm options indidbtfs iow dibgnostid informbtion siould bf displbyfd
      */
    publid void sftDfbugOptions(int options) {
        if (options != 0) {
            if (options == NONE_OPTION) {
                if (dfbugOptions != 0) {
                    Systfm.frr.println(toSiortString() + " Disbbling dfbug");
                    dfbugOptions = 0;
                }
            } flsf {
                if (dfbugOptions != options) {
                    dfbugOptions |= options;
                    if (dfbugLog()) {
                        Systfm.frr.println(toSiortString() + " Enbbling dfbug");
                    }
                }
            }
        }
    }

    /**
     * Rfturns tif durrfnt dfbugging options for tiis DfbugGrbpiids.
     *
     * @rfturn tif durrfnt dfbugging options for tiis DfbugGrbpiids
     * @sff #sftDfbugOptions
     */
    publid int gftDfbugOptions() {
        rfturn dfbugOptions;
    }

    /** Stbtid wrbppfr mftiod for DfbugGrbpiidsInfo.sftDfbugOptions(). Storfs
      * options on b pfr domponfnt bbsis.
      */
    stbtid void sftDfbugOptions(JComponfnt domponfnt, int options) {
        info().sftDfbugOptions(domponfnt, options);
    }

    /** Stbtid wrbppfr mftiod for DfbugGrbpiidsInfo.gftDfbugOptions().
      */
    stbtid int gftDfbugOptions(JComponfnt domponfnt) {
        DfbugGrbpiidsInfo dfbugGrbpiidsInfo = info();
        if (dfbugGrbpiidsInfo == null) {
            rfturn 0;
        } flsf {
            rfturn dfbugGrbpiidsInfo.gftDfbugOptions(domponfnt);
        }
    }

    /** Rfturns non-zfro if <b>domponfnt</b> siould displby witi DfbugGrbpiids,
      * zfro otifrwisf. Wblks tif JComponfnt's pbrfnt trff to dftfrminf if
      * bny dfbugging options ibvf bffn sft.
      */
    stbtid int siouldComponfntDfbug(JComponfnt domponfnt) {
        DfbugGrbpiidsInfo info = info();
        if (info == null) {
            rfturn 0;
        } flsf {
            Contbinfr dontbinfr = (Contbinfr)domponfnt;
            int dfbugOptions = 0;

            wiilf (dontbinfr != null && (dontbinfr instbndfof JComponfnt)) {
                dfbugOptions |= info.gftDfbugOptions((JComponfnt)dontbinfr);
                dontbinfr = dontbinfr.gftPbrfnt();
            }

            rfturn dfbugOptions;
        }
    }

    /** Rfturns tif numbfr of JComponfnts tibt ibvf dfbugging options turnfd
      * on.
      */
    stbtid int dfbugComponfntCount() {
        DfbugGrbpiidsInfo dfbugGrbpiidsInfo = info();
        if (dfbugGrbpiidsInfo != null &&
                    dfbugGrbpiidsInfo.domponfntToDfbug != null) {
            rfturn dfbugGrbpiidsInfo.domponfntToDfbug.sizf();
        } flsf {
            rfturn 0;
        }
    }

    boolfbn dfbugLog() {
        rfturn (dfbugOptions & LOG_OPTION) == LOG_OPTION;
    }

    boolfbn dfbugFlbsi() {
        rfturn (dfbugOptions & FLASH_OPTION) == FLASH_OPTION;
    }

    boolfbn dfbugBufffrfd() {
        rfturn (dfbugOptions & BUFFERED_OPTION) == BUFFERED_OPTION;
    }

    /** Rfturns b DfbugGrbpiids for usf in bufffring window.
      */
    privbtf Grbpiids dfbugGrbpiids() {
        DfbugGrbpiids        dfbugGrbpiids;
        DfbugGrbpiidsInfo    info = info();
        JFrbmf               dfbugFrbmf;

        if (info.dfbugFrbmf == null) {
            info.dfbugFrbmf = nfw JFrbmf();
            info.dfbugFrbmf.sftSizf(500, 500);
        }
        dfbugFrbmf = info.dfbugFrbmf;
        dfbugFrbmf.siow();
        dfbugGrbpiids = nfw DfbugGrbpiids(dfbugFrbmf.gftGrbpiids());
        dfbugGrbpiids.sftFont(gftFont());
        dfbugGrbpiids.sftColor(gftColor());
        dfbugGrbpiids.trbnslbtf(xOffsft, yOffsft);
        dfbugGrbpiids.sftClip(gftClipBounds());
        if (dfbugFlbsi()) {
            dfbugGrbpiids.sftDfbugOptions(FLASH_OPTION);
        }
        rfturn dfbugGrbpiids;
    }

    /** Rfturns DfbugGrbpiidsInfo, or drfbtfs onf if nonf fxists.
      */
    stbtid DfbugGrbpiidsInfo info() {
        DfbugGrbpiidsInfo dfbugGrbpiidsInfo = (DfbugGrbpiidsInfo)
            SwingUtilitifs.bppContfxtGft(dfbugGrbpiidsInfoKfy);
        if (dfbugGrbpiidsInfo == null) {
            dfbugGrbpiidsInfo = nfw DfbugGrbpiidsInfo();
            SwingUtilitifs.bppContfxtPut(dfbugGrbpiidsInfoKfy,
                                         dfbugGrbpiidsInfo);
        }
        rfturn dfbugGrbpiidsInfo;
    }
    privbtf stbtid finbl Clbss<DfbugGrbpiidsInfo> dfbugGrbpiidsInfoKfy = DfbugGrbpiidsInfo.dlbss;
}
