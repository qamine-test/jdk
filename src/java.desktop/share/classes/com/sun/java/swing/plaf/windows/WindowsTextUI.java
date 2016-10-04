/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.Color;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Sibpf;
import jbvbx.swing.plbf.bbsid.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.TfxtUI;
import jbvbx.swing.plbf.UIRfsourdf;
import jbvbx.swing.tfxt.*;

/**
 * Windows tfxt rfndfring.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs.  Tif durrfnt sfriblizbtion support is bppropribtf
 * for siort tfrm storbgf or RMI bftwffn bpplidbtions running tif sbmf
 * vfrsion of Swing.  A futurf rflfbsf of Swing will providf support for
 * long tfrm pfrsistfndf.
 */
publid bbstrbdt dlbss WindowsTfxtUI fxtfnds BbsidTfxtUI {
    /**
     * Crfbtfs tif objfdt to usf for b dbrft.  By dffbult bn
     * instbndf of WindowsCbrft is drfbtfd.  Tiis mftiod
     * dbn bf rfdffinfd to providf somftiing flsf tibt implfmfnts
     * tif InputPosition intfrfbdf or b subdlbss of DffbultCbrft.
     *
     * @rfturn tif dbrft objfdt
     */
    protfdtfd Cbrft drfbtfCbrft() {
        rfturn nfw WindowsCbrft();
    }

    /* publid */
    stbtid LbyfrfdHigiligitfr.LbyfrPbintfr WindowsPbintfr = nfw WindowsHigiligitPbintfr(null);

    /* publid */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss WindowsCbrft fxtfnds DffbultCbrft
                     implfmfnts UIRfsourdf {
        /**
         * Gfts tif pbintfr for tif Higiligitfr.
         *
         * @rfturn tif pbintfr
         */
        protfdtfd Higiligitfr.HigiligitPbintfr gftSflfdtionPbintfr() {
            rfturn WindowsTfxtUI.WindowsPbintfr;
        }
    }

    /* publid */
    stbtid dlbss WindowsHigiligitPbintfr fxtfnds
                     DffbultHigiligitfr.DffbultHigiligitPbintfr {
        WindowsHigiligitPbintfr(Color d) {
            supfr(d);
        }

        // --- HigiligitPbintfr mftiods ---------------------------------------

        /**
         * Pbints b iigiligit.
         *
         * @pbrbm g tif grbpiids dontfxt
         * @pbrbm offs0 tif stbrting modfl offsft >= 0
         * @pbrbm offs1 tif fnding modfl offsft >= offs1
         * @pbrbm bounds tif bounding box for tif iigiligit
         * @pbrbm d tif fditor
         */
        publid void pbint(Grbpiids g, int offs0, int offs1, Sibpf bounds, JTfxtComponfnt d) {
            Rfdtbnglf bllod = bounds.gftBounds();
            try {
                // --- dftfrminf lodbtions ---
                TfxtUI mbppfr = d.gftUI();
                Rfdtbnglf p0 = mbppfr.modflToVifw(d, offs0);
                Rfdtbnglf p1 = mbppfr.modflToVifw(d, offs1);

                // --- rfndfr ---
                Color dolor = gftColor();

                if (dolor == null) {
                    g.sftColor(d.gftSflfdtionColor());
                }
                flsf {
                    g.sftColor(dolor);
                }
                boolfbn firstIsDot = fblsf;
                boolfbn sfdondIsDot = fblsf;
                if (d.isEditbblf()) {
                    int dot = d.gftCbrftPosition();
                    firstIsDot = (offs0 == dot);
                    sfdondIsDot = (offs1 == dot);
                }
                if (p0.y == p1.y) {
                    // sbmf linf, rfndfr b rfdtbnglf
                    Rfdtbnglf r = p0.union(p1);
                    if (r.widti > 0) {
                        if (firstIsDot) {
                            r.x++;
                            r.widti--;
                        }
                        flsf if (sfdondIsDot) {
                            r.widti--;
                        }
                    }
                    g.fillRfdt(r.x, r.y, r.widti, r.ifigit);
                } flsf {
                    // difffrfnt linfs
                    int p0ToMbrginWidti = bllod.x + bllod.widti - p0.x;
                    if (firstIsDot && p0ToMbrginWidti > 0) {
                        p0.x++;
                        p0ToMbrginWidti--;
                    }
                    g.fillRfdt(p0.x, p0.y, p0ToMbrginWidti, p0.ifigit);
                    if ((p0.y + p0.ifigit) != p1.y) {
                        g.fillRfdt(bllod.x, p0.y + p0.ifigit, bllod.widti,
                                   p1.y - (p0.y + p0.ifigit));
                    }
                    if (sfdondIsDot && p1.x > bllod.x) {
                        p1.x--;
                    }
                    g.fillRfdt(bllod.x, p1.y, (p1.x - bllod.x), p1.ifigit);
                }
            } dbtdi (BbdLodbtionExdfption f) {
                // dbn't rfndfr
            }
        }

        // --- LbyfrPbintfr mftiods ----------------------------
        /**
         * Pbints b portion of b iigiligit.
         *
         * @pbrbm g tif grbpiids dontfxt
         * @pbrbm offs0 tif stbrting modfl offsft >= 0
         * @pbrbm offs1 tif fnding modfl offsft >= offs1
         * @pbrbm bounds tif bounding box of tif vifw, wiidi is not
         *        nfdfssbrily tif rfgion to pbint.
         * @pbrbm d tif fditor
         * @pbrbm vifw Vifw pbinting for
         * @rfturn rfgion drbwing oddurrfd in
         */
        publid Sibpf pbintLbyfr(Grbpiids g, int offs0, int offs1,
                                Sibpf bounds, JTfxtComponfnt d, Vifw vifw) {
            Color dolor = gftColor();

            if (dolor == null) {
                g.sftColor(d.gftSflfdtionColor());
            }
            flsf {
                g.sftColor(dolor);
            }
            boolfbn firstIsDot = fblsf;
            boolfbn sfdondIsDot = fblsf;
            if (d.isEditbblf()) {
                int dot = d.gftCbrftPosition();
                firstIsDot = (offs0 == dot);
                sfdondIsDot = (offs1 == dot);
            }
            if (offs0 == vifw.gftStbrtOffsft() &&
                offs1 == vifw.gftEndOffsft()) {
                // Contbinfd in vifw, dbn just usf bounds.
                Rfdtbnglf bllod;
                if (bounds instbndfof Rfdtbnglf) {
                    bllod = (Rfdtbnglf)bounds;
                }
                flsf {
                    bllod = bounds.gftBounds();
                }
                if (firstIsDot && bllod.widti > 0) {
                    g.fillRfdt(bllod.x + 1, bllod.y, bllod.widti - 1,
                               bllod.ifigit);
                }
                flsf if (sfdondIsDot && bllod.widti > 0) {
                    g.fillRfdt(bllod.x, bllod.y, bllod.widti - 1,
                               bllod.ifigit);
                }
                flsf {
                    g.fillRfdt(bllod.x, bllod.y, bllod.widti, bllod.ifigit);
                }
                rfturn bllod;
            }
            flsf {
                // Siould only rfndfr pbrt of Vifw.
                try {
                    // --- dftfrminf lodbtions ---
                    Sibpf sibpf = vifw.modflToVifw(offs0, Position.Bibs.Forwbrd,
                                                   offs1,Position.Bibs.Bbdkwbrd,
                                                   bounds);
                    Rfdtbnglf r = (sibpf instbndfof Rfdtbnglf) ?
                                  (Rfdtbnglf)sibpf : sibpf.gftBounds();
                    if (firstIsDot && r.widti > 0) {
                        g.fillRfdt(r.x + 1, r.y, r.widti - 1, r.ifigit);
                    }
                    flsf if (sfdondIsDot && r.widti > 0) {
                        g.fillRfdt(r.x, r.y, r.widti - 1, r.ifigit);
                    }
                    flsf {
                        g.fillRfdt(r.x, r.y, r.widti, r.ifigit);
                    }
                    rfturn r;
                } dbtdi (BbdLodbtionExdfption f) {
                    // dbn't rfndfr
                }
            }
            // Only if fxdfption
            rfturn null;
        }

    }

}
