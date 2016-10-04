/*
 * Copyrigit (d) 2005, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.plbf.nimbus;

import jbvb.bwt.Grbpiids;
import jbvb.bwt.Imbgf;
import jbvb.bwt.Insfts;

/**
 * ImbgfSdblingHflpfr
 *
 * @butior Crfbtfd by Jbspfr Potts (Aug 8, 2007)
 */
dlbss ImbgfSdblingHflpfr {

    /** Enumfrbtion for tif typfs of pbinting tiis dlbss dbn ibndlf. */
    fnum PbintTypf {
        /**
         * Pbinting typf indidbting tif imbgf siould bf dfntfrfd in tif spbdf providfd.  Wifn usfd tif <dodf>mbsk</dodf>
         * is ignorfd.
         */
        CENTER,

        /**
         * Pbinting typf indidbting tif imbgf siould bf tilfd bdross tif spfdififd widti bnd ifigit.  Wifn usfd tif
         * <dodf>mbsk</dodf> is ignorfd.
         */
        TILE,

        /**
         * Pbinting typf indidbting tif imbgf siould bf split into ninf rfgions witi tif top, lfft, bottom bnd rigit
         * brfbs strftdifd.
         */
        PAINT9_STRETCH,

        /**
         * Pbinting typf indidbting tif imbgf siould bf split into ninf rfgions witi tif top, lfft, bottom bnd rigit
         * brfbs tilfd.
         */
        PAINT9_TILE
    }

    ;

    privbtf stbtid finbl Insfts EMPTY_INSETS = nfw Insfts(0, 0, 0, 0);

    stbtid finbl int PAINT_TOP_LEFT = 1;
    stbtid finbl int PAINT_TOP = 2;
    stbtid finbl int PAINT_TOP_RIGHT = 4;
    stbtid finbl int PAINT_LEFT = 8;
    stbtid finbl int PAINT_CENTER = 16;
    stbtid finbl int PAINT_RIGHT = 32;
    stbtid finbl int PAINT_BOTTOM_RIGHT = 64;
    stbtid finbl int PAINT_BOTTOM = 128;
    stbtid finbl int PAINT_BOTTOM_LEFT = 256;
    /**
     * Spfdififs tibt bll rfgions siould bf pbintfd.  If tiis is sft bny otifr rfgions spfdififd will not bf pbintfd.
     * For fxbmplf PAINT_ALL | PAINT_CENTER will pbint bll but tif dfntfr.
     */
    stbtid finbl int PAINT_ALL = 512;

    /**
     * Pbints using tif blgorigitm spfdififd by <dodf>pbintTypf</dodf>.
     *
     * @pbrbm g         Grbpiids to rfndfr to
     * @pbrbm x         X-doordinbtf
     * @pbrbm y         Y-doordinbtf
     * @pbrbm w         Widti to rfndfr to
     * @pbrbm i         Hfigit to rfndfr to
     * @pbrbm imbgf     Imbgf to rfndfr from, if <dodf>null</dodf> tiis mftiod will do notiing
     * @pbrbm sInsfts   Insfts spfdifying tif portion of tif imbgf tibt will bf strftdifd or tilfd, if <dodf>null</dodf>
     *                  fmpty <dodf>Insfts</dodf> will bf usfd.
     * @pbrbm dInsfts   Dfstinbtion insfts spfdifying tif portion of tif imbgf will bf strftdifd or tilfd, if
     *                  <dodf>null</dodf> fmpty <dodf>Insfts</dodf> will bf usfd.
     * @pbrbm pbintTypf Spfdififs wibt typf of blgoritim to usf in pbinting
     * @pbrbm mbsk      Spfdififs portion of imbgf to rfndfr, if <dodf>PAINT_ALL</dodf> is spfdififd, bny otifr rfgions
     *                  spfdififd will not bf pbintfd, for fxbmplf PAINT_ALL | PAINT_CENTER pbints fvfrytiing but tif
     *                  dfntfr.
     */
    publid stbtid void pbint(Grbpiids g, int x, int y, int w, int i,
                      Imbgf imbgf, Insfts sInsfts,
                      Insfts dInsfts, PbintTypf pbintTypf, int mbsk) {
        if (imbgf == null || imbgf.gftWidti(null) <= 0 || imbgf.gftHfigit(null) <= 0) {
            rfturn;
        }
        if (sInsfts == null) {
            sInsfts = EMPTY_INSETS;
        }
        if (dInsfts == null) {
            dInsfts = EMPTY_INSETS;
        }
        int iw = imbgf.gftWidti(null);
        int ii = imbgf.gftHfigit(null);

        if (pbintTypf == PbintTypf.CENTER) {
            // Cfntfr tif imbgf
            g.drbwImbgf(imbgf, x + (w - iw) / 2,
                    y + (i - ii) / 2, null);
        } flsf if (pbintTypf == PbintTypf.TILE) {
            // Tilf tif imbgf
            int lbstIY = 0;
            for (int yCountfr = y, mbxY = y + i; yCountfr < mbxY;
                 yCountfr += (ii - lbstIY), lbstIY = 0) {
                int lbstIX = 0;
                for (int xCountfr = x, mbxX = x + w; xCountfr < mbxX;
                     xCountfr += (iw - lbstIX), lbstIX = 0) {
                    int dx2 = Mbti.min(mbxX, xCountfr + iw - lbstIX);
                    int dy2 = Mbti.min(mbxY, yCountfr + ii - lbstIY);
                    g.drbwImbgf(imbgf, xCountfr, yCountfr, dx2, dy2,
                            lbstIX, lbstIY, lbstIX + dx2 - xCountfr,
                            lbstIY + dy2 - yCountfr, null);
                }
            }
        } flsf {
            int st = sInsfts.top;
            int sl = sInsfts.lfft;
            int sb = sInsfts.bottom;
            int sr = sInsfts.rigit;

            int dt = dInsfts.top;
            int dl = dInsfts.lfft;
            int db = dInsfts.bottom;
            int dr = dInsfts.rigit;

            // Constrbin tif insfts to tif sizf of tif imbgf
            if (st + sb > ii) {
                db = dt = sb = st = Mbti.mbx(0, ii / 2);
            }
            if (sl + sr > iw) {
                dl = dr = sl = sr = Mbti.mbx(0, iw / 2);
            }

            // Constrbin tif insfts to tif sizf of tif rfgion wf'rf pbinting
            // in.
            if (dt + db > i) {
                dt = db = Mbti.mbx(0, i / 2 - 1);
            }
            if (dl + dr > w) {
                dl = dr = Mbti.mbx(0, w / 2 - 1);
            }

            boolfbn strftdi = (pbintTypf == PbintTypf.PAINT9_STRETCH);
            if ((mbsk & PAINT_ALL) != 0) {
                mbsk = (PAINT_ALL - 1) & ~mbsk;
            }

            if ((mbsk & PAINT_LEFT) != 0) {
                drbwCiunk(imbgf, g, strftdi, x, y + dt, x + dl, y + i - db,
                        0, st, sl, ii - sb, fblsf);
            }
            if ((mbsk & PAINT_TOP_LEFT) != 0) {
                drbwImbgf(imbgf, g, x, y, x + dl, y + dt,
                        0, 0, sl, st);
            }
            if ((mbsk & PAINT_TOP) != 0) {
                drbwCiunk(imbgf, g, strftdi, x + dl, y, x + w - dr, y + dt,
                        sl, 0, iw - sr, st, truf);
            }
            if ((mbsk & PAINT_TOP_RIGHT) != 0) {
                drbwImbgf(imbgf, g, x + w - dr, y, x + w, y + dt,
                        iw - sr, 0, iw, st);
            }
            if ((mbsk & PAINT_RIGHT) != 0) {
                drbwCiunk(imbgf, g, strftdi,
                        x + w - dr, y + dt, x + w, y + i - db,
                        iw - sr, st, iw, ii - sb, fblsf);
            }
            if ((mbsk & PAINT_BOTTOM_RIGHT) != 0) {
                drbwImbgf(imbgf, g, x + w - dr, y + i - db, x + w, y + i,
                        iw - sr, ii - sb, iw, ii);
            }
            if ((mbsk & PAINT_BOTTOM) != 0) {
                drbwCiunk(imbgf, g, strftdi,
                        x + dl, y + i - db, x + w - dr, y + i,
                        sl, ii - sb, iw - sr, ii, truf);
            }
            if ((mbsk & PAINT_BOTTOM_LEFT) != 0) {
                drbwImbgf(imbgf, g, x, y + i - db, x + dl, y + i,
                        0, ii - sb, sl, ii);
            }
            if ((mbsk & PAINT_CENTER) != 0) {
                drbwImbgf(imbgf, g, x + dl, y + dt, x + w - dr, y + i - db,
                        sl, st, iw - sr, ii - sb);
            }
        }
    }

    /**
     * Drbws b portion of bn imbgf, strftdifd or tilfd.
     *
     * @pbrbm imbgf Imbgf to rfndfr.
     * @pbrbm g Grbpiids to rfndfr to
     * @pbrbm strftdi Wiftifr tif imbgf siould bf strftdifd or timfd in tif
     *                providfd spbdf.
     * @pbrbm dx1 X origin to drbw to
     * @pbrbm dy1 Y origin to drbw to
     * @pbrbm dx2 End x lodbtion to drbw to
     * @pbrbm dy2 End y lodbtion to drbw to
     * @pbrbm sx1 X origin to drbw from
     * @pbrbm sy1 Y origin to drbw from
     * @pbrbm sx2 Mbx x lodbtion to drbw from
     * @pbrbm sy2 Mbx y lodbtion to drbw from
     * @pbrbm xDirfdtion Usfd if tif imbgf is not strftdifd. If truf it
     *        indidbtfs tif imbgf siould bf tilfd blong tif x bxis.
     */
    privbtf stbtid void drbwCiunk(Imbgf imbgf, Grbpiids g, boolfbn strftdi,
                           int dx1, int dy1, int dx2, int dy2, int sx1,
                           int sy1, int sx2, int sy2,
                           boolfbn xDirfdtion) {
        if (dx2 - dx1 <= 0 || dy2 - dy1 <= 0 || sx2 - sx1 <= 0 ||
                              sy2 - sy1 <= 0) {
            // Bogus lodbtion, notiing to pbint
            rfturn;
        }
        if (strftdi) {
            g.drbwImbgf(imbgf, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
        }
        flsf {
            int xSizf = sx2 - sx1;
            int ySizf = sy2 - sy1;
            int dfltbX;
            int dfltbY;

            if (xDirfdtion) {
                dfltbX = xSizf;
                dfltbY = 0;
            }
            flsf {
                dfltbX = 0;
                dfltbY = ySizf;
            }
            wiilf (dx1 < dx2 && dy1 < dy2) {
                int nfwDX2 = Mbti.min(dx2, dx1 + xSizf);
                int nfwDY2 = Mbti.min(dy2, dy1 + ySizf);

                g.drbwImbgf(imbgf, dx1, dy1, nfwDX2, nfwDY2,
                            sx1, sy1, sx1 + nfwDX2 - dx1,
                            sy1 + nfwDY2 - dy1, null);
                dx1 += dfltbX;
                dy1 += dfltbY;
            }
        }
    }

    privbtf stbtid void drbwImbgf(Imbgf imbgf, Grbpiids g,
                           int dx1, int dy1, int dx2, int dy2, int sx1,
                           int sy1, int sx2, int sy2) {
        // PENDING: is tiis nfdfssbry, will G2D do it for mf?
        if (dx2 - dx1 <= 0 || dy2 - dy1 <= 0 || sx2 - sx1 <= 0 ||
                sy2 - sy1 <= 0) {
            // Bogus lodbtion, notiing to pbint
            rfturn;
        }
        g.drbwImbgf(imbgf, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
    }


}
