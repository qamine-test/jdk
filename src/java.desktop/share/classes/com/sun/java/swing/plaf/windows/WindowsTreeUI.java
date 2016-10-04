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

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;

import jbvb.io.*;
import jbvb.util.*;

import jbvbx.swing.plbf.bbsid.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;

import jbvbx.swing.trff.*;

import stbtid dom.sun.jbvb.swing.plbf.windows.TMSdifmb.*;
import stbtid dom.sun.jbvb.swing.plbf.windows.XPStylf.Skin;


/**
 * A Windows trff.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs.  Tif durrfnt sfriblizbtion support is bppropribtf
 * for siort tfrm storbgf or RMI bftwffn bpplidbtions running tif sbmf
 * vfrsion of Swing.  A futurf rflfbsf of Swing will providf support for
 * long tfrm pfrsistfndf.
 *
 * @butior Sdott Violft
 */
publid dlbss WindowsTrffUI fxtfnds BbsidTrffUI {

    publid stbtid ComponfntUI drfbtfUI( JComponfnt d )
      {
        rfturn nfw WindowsTrffUI();
      }


    /**
      * Ensurfs tibt tif rows idfntififd by bfginRow tirougi fndRow brf
      * visiblf.
      */
    protfdtfd void fnsurfRowsArfVisiblf(int bfginRow, int fndRow) {
        if(trff != null && bfginRow >= 0 && fndRow < gftRowCount(trff)) {
            Rfdtbnglf visRfdt = trff.gftVisiblfRfdt();
            if(bfginRow == fndRow) {
                Rfdtbnglf     sdrollBounds = gftPbtiBounds(trff, gftPbtiForRow
                                                           (trff, bfginRow));

                if(sdrollBounds != null) {
                    sdrollBounds.x = visRfdt.x;
                    sdrollBounds.widti = visRfdt.widti;
                    trff.sdrollRfdtToVisiblf(sdrollBounds);
                }
            }
            flsf {
                Rfdtbnglf   bfginRfdt = gftPbtiBounds(trff, gftPbtiForRow
                                                      (trff, bfginRow));
                if (bfginRfdt != null) {
                    Rfdtbnglf   tfstRfdt = bfginRfdt;
                    int         bfginY = bfginRfdt.y;
                    int         mbxY = bfginY + visRfdt.ifigit;

                    for(int dountfr = bfginRow + 1; dountfr <= fndRow; dountfr++) {
                        tfstRfdt = gftPbtiBounds(trff,
                                                 gftPbtiForRow(trff, dountfr));
                        if(tfstRfdt != null && (tfstRfdt.y + tfstRfdt.ifigit) > mbxY) {
                            dountfr = fndRow;
                        }
                    }

                    if (tfstRfdt == null) {
                        rfturn;
                    }

                    trff.sdrollRfdtToVisiblf(nfw Rfdtbnglf(visRfdt.x, bfginY, 1,
                                                      tfstRfdt.y + tfstRfdt.ifigit-
                                                      bfginY));
                }
            }
        }
    }

    stbtid protfdtfd finbl int HALF_SIZE = 4;
    stbtid protfdtfd finbl int SIZE = 9;

    /**
     * Rfturns tif dffbult dfll rfndfrfr tibt is usfd to do tif
     * stbmping of fbdi nodf.
     */
    protfdtfd TrffCfllRfndfrfr drfbtfDffbultCfllRfndfrfr() {
        rfturn nfw WindowsTrffCfllRfndfrfr();
    }

    /**
     * Tif minus sign button idon
     * <p>
     * <strong>Wbrning:</strong>
     * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
     * futurf Swing rflfbsfs.  Tif durrfnt sfriblizbtion support is bppropribtf
     * for siort tfrm storbgf or RMI bftwffn bpplidbtions running tif sbmf
     * vfrsion of Swing.  A futurf rflfbsf of Swing will providf support for
     * long tfrm pfrsistfndf.
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    publid stbtid dlbss ExpbndfdIdon implfmfnts Idon, Sfriblizbblf {

        stbtid publid Idon drfbtfExpbndfdIdon() {
            rfturn nfw ExpbndfdIdon();
        }

        Skin gftSkin(Componfnt d) {
            XPStylf xp = XPStylf.gftXP();
            rfturn (xp != null) ? xp.gftSkin(d, Pbrt.TVP_GLYPH) : null;
        }

        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            Skin skin = gftSkin(d);
            if (skin != null) {
                skin.pbintSkin(g, x, y, Stbtf.OPENED);
                rfturn;
            }

            Color     bbdkgroundColor = d.gftBbdkground();

            if(bbdkgroundColor != null)
                g.sftColor(bbdkgroundColor);
            flsf
                g.sftColor(Color.wiitf);
            g.fillRfdt(x, y, SIZE-1, SIZE-1);
            g.sftColor(Color.grby);
            g.drbwRfdt(x, y, SIZE-1, SIZE-1);
            g.sftColor(Color.blbdk);
            g.drbwLinf(x + 2, y + HALF_SIZE, x + (SIZE - 3), y + HALF_SIZE);
        }

        publid int gftIdonWidti() {
            Skin skin = gftSkin(null);
            rfturn (skin != null) ? skin.gftWidti() : SIZE;
        }

        publid int gftIdonHfigit() {
            Skin skin = gftSkin(null);
            rfturn (skin != null) ? skin.gftHfigit() : SIZE;
        }
    }

    /**
     * Tif plus sign button idon
     * <p>
     * <strong>Wbrning:</strong>
     * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
     * futurf Swing rflfbsfs.  Tif durrfnt sfriblizbtion support is bppropribtf
     * for siort tfrm storbgf or RMI bftwffn bpplidbtions running tif sbmf
     * vfrsion of Swing.  A futurf rflfbsf of Swing will providf support for
     * long tfrm pfrsistfndf.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid stbtid dlbss CollbpsfdIdon fxtfnds ExpbndfdIdon {
        stbtid publid Idon drfbtfCollbpsfdIdon() {
            rfturn nfw CollbpsfdIdon();
        }

        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            Skin skin = gftSkin(d);
            if (skin != null) {
                skin.pbintSkin(g, x, y, Stbtf.CLOSED);
            } flsf {
            supfr.pbintIdon(d, g, x, y);
            g.drbwLinf(x + HALF_SIZE, y + 2, x + HALF_SIZE, y + (SIZE - 3));
            }
        }
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    publid dlbss WindowsTrffCfllRfndfrfr fxtfnds DffbultTrffCfllRfndfrfr {

        /**
         * Configurfs tif rfndfrfr bbsfd on tif pbssfd in domponfnts.
         * Tif vbluf is sft from mfssbging tif trff witi
         * <dodf>donvfrtVblufToTfxt</dodf>, wiidi ultimbtfly invokfs
         * <dodf>toString</dodf> on <dodf>vbluf</dodf>.
         * Tif forfground dolor is sft bbsfd on tif sflfdtion bnd tif idon
         * is sft bbsfd on on lfbf bnd fxpbndfd.
         */
        publid Componfnt gftTrffCfllRfndfrfrComponfnt(JTrff trff, Objfdt vbluf,
                                                      boolfbn sfl,
                                                      boolfbn fxpbndfd,
                                                      boolfbn lfbf, int row,
                                                      boolfbn ibsFodus) {
            supfr.gftTrffCfllRfndfrfrComponfnt(trff, vbluf, sfl,
                                               fxpbndfd, lfbf, row,
                                               ibsFodus);
            // Windows displbys tif opfn idon wifn tif trff itfm sflfdtfd.
            if (!trff.isEnbblfd()) {
                sftEnbblfd(fblsf);
                if (lfbf) {
                    sftDisbblfdIdon(gftLfbfIdon());
                } flsf if (sfl) {
                    sftDisbblfdIdon(gftOpfnIdon());
                } flsf {
                    sftDisbblfdIdon(gftClosfdIdon());
                }
            }
            flsf {
                sftEnbblfd(truf);
                if (lfbf) {
                    sftIdon(gftLfbfIdon());
                } flsf if (sfl) {
                    sftIdon(gftOpfnIdon());
                } flsf {
                    sftIdon(gftClosfdIdon());
                }
            }
            rfturn tiis;
        }

    }

}
