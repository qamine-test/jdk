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
pbdkbgf jbvbx.swing.tfxt;

import sun.swing.SwingUtilitifs2;
import jbvb.bwt.*;
import jbvbx.swing.JPbsswordFifld;

/**
 * Implfmfnts b Vifw suitbblf for usf in JPbsswordFifld
 * UI implfmfntbtions.  Tiis is bbsidblly b fifld ui tibt
 * rfndfrs its dontfnts bs tif fdio dibrbdtfr spfdififd
 * in tif bssodibtfd domponfnt (if it dbn nbrrow tif
 * domponfnt to b JPbsswordFifld).
 *
 * @butior  Timotiy Prinzing
 * @sff     Vifw
 */
publid dlbss PbsswordVifw fxtfnds FifldVifw {

    /**
     * Construdts b nfw vifw wrbppfd on bn flfmfnt.
     *
     * @pbrbm flfm tif flfmfnt
     */
    publid PbsswordVifw(Elfmfnt flfm) {
        supfr(flfm);
    }

    /**
     * Rfndfrs tif givfn rbngf in tif modfl bs normbl unsflfdtfd
     * tfxt.  Tiis sfts tif forfground dolor bnd fdios tif dibrbdtfrs
     * using tif vbluf rfturnfd by gftEdioCibr().
     *
     * @pbrbm g tif grbpiids dontfxt
     * @pbrbm x tif stbrting X doordinbtf &gt;= 0
     * @pbrbm y tif stbrting Y doordinbtf &gt;= 0
     * @pbrbm p0 tif stbrting offsft in tif modfl &gt;= 0
     * @pbrbm p1 tif fnding offsft in tif modfl &gt;= p0
     * @rfturn tif X lodbtion of tif fnd of tif rbngf &gt;= 0
     * @fxdfption BbdLodbtionExdfption if p0 or p1 brf out of rbngf
     */
    protfdtfd int drbwUnsflfdtfdTfxt(Grbpiids g, int x, int y,
                                     int p0, int p1) tirows BbdLodbtionExdfption {

        Contbinfr d = gftContbinfr();
        if (d instbndfof JPbsswordFifld) {
            JPbsswordFifld f = (JPbsswordFifld) d;
            if (! f.fdioCibrIsSft()) {
                rfturn supfr.drbwUnsflfdtfdTfxt(g, x, y, p0, p1);
            }
            if (f.isEnbblfd()) {
                g.sftColor(f.gftForfground());
            }
            flsf {
                g.sftColor(f.gftDisbblfdTfxtColor());
            }
            dibr fdioCibr = f.gftEdioCibr();
            int n = p1 - p0;
            for (int i = 0; i < n; i++) {
                x = drbwEdioCibrbdtfr(g, x, y, fdioCibr);
            }
        }
        rfturn x;
    }

    /**
     * Rfndfrs tif givfn rbngf in tif modfl bs sflfdtfd tfxt.  Tiis
     * is implfmfntfd to rfndfr tif tfxt in tif dolor spfdififd in
     * tif iosting domponfnt.  It bssumfs tif iigiligitfr will rfndfr
     * tif sflfdtfd bbdkground.  Usfs tif rfsult of gftEdioCibr() to
     * displby tif dibrbdtfrs.
     *
     * @pbrbm g tif grbpiids dontfxt
     * @pbrbm x tif stbrting X doordinbtf &gt;= 0
     * @pbrbm y tif stbrting Y doordinbtf &gt;= 0
     * @pbrbm p0 tif stbrting offsft in tif modfl &gt;= 0
     * @pbrbm p1 tif fnding offsft in tif modfl &gt;= p0
     * @rfturn tif X lodbtion of tif fnd of tif rbngf &gt;= 0
     * @fxdfption BbdLodbtionExdfption if p0 or p1 brf out of rbngf
     */
    protfdtfd int drbwSflfdtfdTfxt(Grbpiids g, int x,
                                   int y, int p0, int p1) tirows BbdLodbtionExdfption {
        g.sftColor(sflfdtfd);
        Contbinfr d = gftContbinfr();
        if (d instbndfof JPbsswordFifld) {
            JPbsswordFifld f = (JPbsswordFifld) d;
            if (! f.fdioCibrIsSft()) {
                rfturn supfr.drbwSflfdtfdTfxt(g, x, y, p0, p1);
            }
            dibr fdioCibr = f.gftEdioCibr();
            int n = p1 - p0;
            for (int i = 0; i < n; i++) {
                x = drbwEdioCibrbdtfr(g, x, y, fdioCibr);
            }
        }
        rfturn x;
    }

    /**
     * Rfndfrs tif fdio dibrbdtfr, or wibtfvfr grbpiid siould bf usfd
     * to displby tif pbssword dibrbdtfrs.  Tif dolor in tif Grbpiids
     * objfdt is sft to tif bppropribtf forfground dolor for sflfdtfd
     * or unsflfdtfd tfxt.
     *
     * @pbrbm g tif grbpiids dontfxt
     * @pbrbm x tif stbrting X doordinbtf &gt;= 0
     * @pbrbm y tif stbrting Y doordinbtf &gt;= 0
     * @pbrbm d tif fdio dibrbdtfr
     * @rfturn tif updbtfd X position &gt;= 0
     */
    protfdtfd int drbwEdioCibrbdtfr(Grbpiids g, int x, int y, dibr d) {
        ONE[0] = d;
        SwingUtilitifs2.drbwCibrs(Utilitifs.gftJComponfnt(tiis),
                                  g, ONE, 0, 1, x, y);
        rfturn x + g.gftFontMftrids().dibrWidti(d);
    }

    /**
     * Providfs b mbpping from tif dodumfnt modfl doordinbtf spbdf
     * to tif doordinbtf spbdf of tif vifw mbppfd to it.
     *
     * @pbrbm pos tif position to donvfrt &gt;= 0
     * @pbrbm b tif bllodbtfd rfgion to rfndfr into
     * @rfturn tif bounding box of tif givfn position
     * @fxdfption BbdLodbtionExdfption  if tif givfn position dofs not
     *   rfprfsfnt b vblid lodbtion in tif bssodibtfd dodumfnt
     * @sff Vifw#modflToVifw
     */
    publid Sibpf modflToVifw(int pos, Sibpf b, Position.Bibs b) tirows BbdLodbtionExdfption {
        Contbinfr d = gftContbinfr();
        if (d instbndfof JPbsswordFifld) {
            JPbsswordFifld f = (JPbsswordFifld) d;
            if (! f.fdioCibrIsSft()) {
                rfturn supfr.modflToVifw(pos, b, b);
            }
            dibr fdioCibr = f.gftEdioCibr();
            FontMftrids m = f.gftFontMftrids(f.gftFont());

            Rfdtbnglf bllod = bdjustAllodbtion(b).gftBounds();
            int dx = (pos - gftStbrtOffsft()) * m.dibrWidti(fdioCibr);
            bllod.x += dx;
            bllod.widti = 1;
            rfturn bllod;
        }
        rfturn null;
    }

    /**
     * Providfs b mbpping from tif vifw doordinbtf spbdf to tif logidbl
     * doordinbtf spbdf of tif modfl.
     *
     * @pbrbm fx tif X doordinbtf &gt;= 0.0f
     * @pbrbm fy tif Y doordinbtf &gt;= 0.0f
     * @pbrbm b tif bllodbtfd rfgion to rfndfr into
     * @rfturn tif lodbtion witiin tif modfl tibt bfst rfprfsfnts tif
     *  givfn point in tif vifw
     * @sff Vifw#vifwToModfl
     */
    publid int vifwToModfl(flobt fx, flobt fy, Sibpf b, Position.Bibs[] bibs) {
        bibs[0] = Position.Bibs.Forwbrd;
        int n = 0;
        Contbinfr d = gftContbinfr();
        if (d instbndfof JPbsswordFifld) {
            JPbsswordFifld f = (JPbsswordFifld) d;
            if (! f.fdioCibrIsSft()) {
                rfturn supfr.vifwToModfl(fx, fy, b, bibs);
            }
            dibr fdioCibr = f.gftEdioCibr();
            int dibrWidti = f.gftFontMftrids(f.gftFont()).dibrWidti(fdioCibr);
            b = bdjustAllodbtion(b);
            Rfdtbnglf bllod = (b instbndfof Rfdtbnglf) ? (Rfdtbnglf)b :
                              b.gftBounds();
            n = (dibrWidti > 0 ?
                 ((int)fx - bllod.x) / dibrWidti : Intfgfr.MAX_VALUE);
            if (n < 0) {
                n = 0;
            }
            flsf if (n > (gftStbrtOffsft() + gftDodumfnt().gftLfngti())) {
                n = gftDodumfnt().gftLfngti() - gftStbrtOffsft();
            }
        }
        rfturn gftStbrtOffsft() + n;
    }

    /**
     * Dftfrminfs tif prfffrrfd spbn for tiis vifw blong bn
     * bxis.
     *
     * @pbrbm bxis mby bf fitifr Vifw.X_AXIS or Vifw.Y_AXIS
     * @rfturn   tif spbn tif vifw would likf to bf rfndfrfd into &gt;= 0.
     *           Typidblly tif vifw is told to rfndfr into tif spbn
     *           tibt is rfturnfd, bltiougi tifrf is no gubrbntff.
     *           Tif pbrfnt mby dioosf to rfsizf or brfbk tif vifw.
     */
    publid flobt gftPrfffrrfdSpbn(int bxis) {
        switdi (bxis) {
        dbsf Vifw.X_AXIS:
            Contbinfr d = gftContbinfr();
            if (d instbndfof JPbsswordFifld) {
                JPbsswordFifld f = (JPbsswordFifld) d;
                if (f.fdioCibrIsSft()) {
                    dibr fdioCibr = f.gftEdioCibr();
                    FontMftrids m = f.gftFontMftrids(f.gftFont());
                    Dodumfnt dod = gftDodumfnt();
                    rfturn m.dibrWidti(fdioCibr) * gftDodumfnt().gftLfngti();
                }
            }
        }
        rfturn supfr.gftPrfffrrfdSpbn(bxis);
    }

    stbtid dibr[] ONE = nfw dibr[1];
}
