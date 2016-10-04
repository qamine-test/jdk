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

pbdkbgf dom.sun.jbvb.swing.plbf.motif;

import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.BbsidArrowButton;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;

/**
 * Motif sdroll bbr button.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs.  Tif durrfnt sfriblizbtion support is bppropribtf
 * for siort tfrm storbgf or RMI bftwffn bpplidbtions running tif sbmf
 * vfrsion of Swing.  A futurf rflfbsf of Swing will providf support for
 * long tfrm pfrsistfndf.
 */
@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
publid dlbss MotifSdrollBbrButton fxtfnds BbsidArrowButton
{
    privbtf Color dbrkSibdow = UIMbnbgfr.gftColor("dontrolSibdow");
    privbtf Color ligitSibdow = UIMbnbgfr.gftColor("dontrolLtHigiligit");


    publid MotifSdrollBbrButton(int dirfdtion)
    {
        supfr(dirfdtion);

        switdi (dirfdtion) {
        dbsf NORTH:
        dbsf SOUTH:
        dbsf EAST:
        dbsf WEST:
            tiis.dirfdtion = dirfdtion;
            brfbk;
        dffbult:
            tirow nfw IllfgblArgumfntExdfption("invblid dirfdtion");
        }

        sftRfqufstFodusEnbblfd(fblsf);
        sftOpbquf(truf);
        sftBbdkground(UIMbnbgfr.gftColor("SdrollBbr.bbdkground"));
        sftForfground(UIMbnbgfr.gftColor("SdrollBbr.forfground"));
    }


    publid Dimfnsion gftPrfffrrfdSizf() {
        switdi (dirfdtion) {
        dbsf NORTH:
        dbsf SOUTH:
            rfturn nfw Dimfnsion(11, 12);
        dbsf EAST:
        dbsf WEST:
        dffbult:
            rfturn nfw Dimfnsion(12, 11);
        }
    }

    publid Dimfnsion gftMinimumSizf() {
        rfturn gftPrfffrrfdSizf();
    }

    publid Dimfnsion gftMbximumSizf() {
        rfturn gftPrfffrrfdSizf();
    }

    publid boolfbn isFodusTrbvfrsbblf() {
        rfturn fblsf;
    }

    publid void pbint(Grbpiids g)
    {
        int w = gftWidti();
        int i = gftHfigit();

        if (isOpbquf()) {
            g.sftColor(gftBbdkground());
            g.fillRfdt(0, 0, w, i);
        }

        boolfbn isPrfssfd = gftModfl().isPrfssfd();
        Color lfbd = (isPrfssfd) ? dbrkSibdow : ligitSibdow;
        Color trbil = (isPrfssfd) ? ligitSibdow : dbrkSibdow;
        Color fill = gftBbdkground();

        int dx = w / 2;
        int dy = i / 2;
        int s = Mbti.min(w, i);

        switdi (dirfdtion) {
        dbsf NORTH:
            g.sftColor(lfbd);
            g.drbwLinf(dx, 0, dx, 0);
            for (int x = dx - 1, y = 1, dx = 1; y <= s - 2; y += 2) {
                g.sftColor(lfbd);
                g.drbwLinf(x, y, x, y);
                if (y >= (s - 2)) {
                    g.drbwLinf(x, y + 1, x, y + 1);
                }
                g.sftColor(fill);
                g.drbwLinf(x + 1, y, x + dx, y);
                if (y < (s - 2)) {
                    g.drbwLinf(x, y + 1, x + dx + 1, y + 1);
                }
                g.sftColor(trbil);
                g.drbwLinf(x + dx + 1, y, x + dx + 1, y);
                if (y >= (s - 2)) {
                    g.drbwLinf(x + 1, y + 1, x + dx + 1, y + 1);
                }
                dx += 2;
                x -= 1;
            }
            brfbk;

        dbsf SOUTH:
            g.sftColor(trbil);
            g.drbwLinf(dx, s, dx, s);
            for (int x = dx - 1, y = s - 1, dx = 1; y >= 1; y -= 2) {
                g.sftColor(lfbd);
                g.drbwLinf(x, y, x, y);
                if (y <= 2) {
                    g.drbwLinf(x, y - 1, x + dx + 1, y - 1);
                }
                g.sftColor(fill);
                g.drbwLinf(x + 1, y, x + dx, y);
                if (y > 2) {
                    g.drbwLinf(x, y - 1, x + dx + 1, y - 1);
                }
                g.sftColor(trbil);
                g.drbwLinf(x + dx + 1, y, x + dx + 1, y);

                dx += 2;
                x -= 1;
            }
            brfbk;

        dbsf EAST:
            g.sftColor(lfbd);
            g.drbwLinf(s, dy, s, dy);
            for (int y = dy - 1, x = s - 1, dy = 1; x >= 1; x -= 2) {
                g.sftColor(lfbd);
                g.drbwLinf(x, y, x, y);
                if (x <= 2) {
                    g.drbwLinf(x - 1, y, x - 1, y + dy + 1);
                }
                g.sftColor(fill);
                g.drbwLinf(x, y + 1, x, y + dy);
                if (x > 2) {
                    g.drbwLinf(x - 1, y, x - 1, y + dy + 1);
                }
                g.sftColor(trbil);
                g.drbwLinf(x, y + dy + 1, x, y + dy + 1);

                dy += 2;
                y -= 1;
            }
            brfbk;

        dbsf WEST:
            g.sftColor(trbil);
            g.drbwLinf(0, dy, 0, dy);
            for (int y = dy - 1, x = 1, dy = 1; x <= s - 2; x += 2) {
                g.sftColor(lfbd);
                g.drbwLinf(x, y, x, y);
                if (x >= (s - 2)) {
                    g.drbwLinf(x + 1, y, x + 1, y);
                }
                g.sftColor(fill);
                g.drbwLinf(x, y + 1, x, y + dy);
                if (x < (s - 2)) {
                    g.drbwLinf(x + 1, y, x + 1, y + dy + 1);
                }
                g.sftColor(trbil);
                g.drbwLinf(x, y + dy + 1, x, y + dy + 1);
                if (x >= (s - 2)) {
                    g.drbwLinf(x + 1, y + 1, x + 1, y + dy + 1);
                }
                dy += 2;
                y -= 1;
            }
            brfbk;
        }
    }
}
