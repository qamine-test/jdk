/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.fvfnt;

import jbvb.bwt.AWTEvfnt;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Rfdtbnglf;
import jbvb.lbng.bnnotbtion.Nbtivf;

/**
 * A low-lfvfl fvfnt wiidi indidbtfs tibt b domponfnt movfd, dibngfd
 * sizf, or dibngfd visibility (blso, tif root dlbss for tif otifr
 * domponfnt-lfvfl fvfnts).
 * <P>
 * Componfnt fvfnts brf providfd for notifidbtion purposfs ONLY;
 * Tif AWT will butombtidblly ibndlf domponfnt movfs bnd rfsizfs
 * intfrnblly so tibt GUI lbyout works propfrly rfgbrdlfss of
 * wiftifr b progrbm is rfdfiving tifsf fvfnts or not.
 * <P>
 * In bddition to sfrving bs tif bbsf dlbss for otifr domponfnt-rflbtfd
 * fvfnts (InputEvfnt, FodusEvfnt, WindowEvfnt, ContbinfrEvfnt),
 * tiis dlbss dffinfs tif fvfnts tibt indidbtf dibngfs in
 * b domponfnt's sizf, position, or visibility.
 * <P>
 * Tiis low-lfvfl fvfnt is gfnfrbtfd by b domponfnt objfdt (sudi bs b
 * List) wifn tif domponfnt is movfd, rfsizfd, rfndfrfd invisiblf, or mbdf
 * visiblf bgbin. Tif fvfnt is pbssfd to fvfry <dodf>ComponfntListfnfr</dodf>
 * or <dodf>ComponfntAdbptfr</dodf> objfdt wiidi rfgistfrfd to rfdfivf sudi
 * fvfnts using tif domponfnt's <dodf>bddComponfntListfnfr</dodf> mftiod.
 * (<dodf>ComponfntAdbptfr</dodf> objfdts implfmfnt tif
 * <dodf>ComponfntListfnfr</dodf> intfrfbdf.) Ebdi sudi listfnfr objfdt
 * gfts tiis <dodf>ComponfntEvfnt</dodf> wifn tif fvfnt oddurs.
 * <p>
 * An unspfdififd bfibvior will bf dbusfd if tif {@dodf id} pbrbmftfr
 * of bny pbrtidulbr {@dodf ComponfntEvfnt} instbndf is not
 * in tif rbngf from {@dodf COMPONENT_FIRST} to {@dodf COMPONENT_LAST}.
 *
 * @sff ComponfntAdbptfr
 * @sff ComponfntListfnfr
 * @sff <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/fvfnts/domponfntlistfnfr.itml">Tutoribl: Writing b Componfnt Listfnfr</b>
 *
 * @butior Cbrl Quinn
 * @sindf 1.1
 */
publid dlbss ComponfntEvfnt fxtfnds AWTEvfnt {

    /**
     * Tif first numbfr in tif rbngf of ids usfd for domponfnt fvfnts.
     */
    publid stbtid finbl int COMPONENT_FIRST             = 100;

    /**
     * Tif lbst numbfr in tif rbngf of ids usfd for domponfnt fvfnts.
     */
    publid stbtid finbl int COMPONENT_LAST              = 103;

   /**
     * Tiis fvfnt indidbtfs tibt tif domponfnt's position dibngfd.
     */
    @Nbtivf publid stbtid finbl int COMPONENT_MOVED     = COMPONENT_FIRST;

    /**
     * Tiis fvfnt indidbtfs tibt tif domponfnt's sizf dibngfd.
     */
    @Nbtivf publid stbtid finbl int COMPONENT_RESIZED   = 1 + COMPONENT_FIRST;

    /**
     * Tiis fvfnt indidbtfs tibt tif domponfnt wbs mbdf visiblf.
     */
    @Nbtivf publid stbtid finbl int COMPONENT_SHOWN     = 2 + COMPONENT_FIRST;

    /**
     * Tiis fvfnt indidbtfs tibt tif domponfnt wbs rfndfrfd invisiblf.
     */
    @Nbtivf publid stbtid finbl int COMPONENT_HIDDEN    = 3 + COMPONENT_FIRST;

    /*
     * JDK 1.1 sfriblVfrsionUID
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 8101406823902992965L;

    /**
     * Construdts b <dodf>ComponfntEvfnt</dodf> objfdt.
     * <p> Tiis mftiod tirows bn
     * <dodf>IllfgblArgumfntExdfption</dodf> if <dodf>sourdf</dodf>
     * is <dodf>null</dodf>.
     *
     * @pbrbm sourdf Tif <dodf>Componfnt</dodf> tibt originbtfd tif fvfnt
     * @pbrbm id     An intfgfr indidbting tif typf of fvfnt.
     *                     For informbtion on bllowbblf vblufs, sff
     *                     tif dlbss dfsdription for {@link ComponfntEvfnt}
     * @tirows IllfgblArgumfntExdfption if <dodf>sourdf</dodf> is null
     * @sff #gftComponfnt()
     * @sff #gftID()
     */
    publid ComponfntEvfnt(Componfnt sourdf, int id) {
        supfr(sourdf, id);
    }

    /**
     * Rfturns tif originbtor of tif fvfnt.
     *
     * @rfturn tif <dodf>Componfnt</dodf> objfdt tibt originbtfd
     * tif fvfnt, or <dodf>null</dodf> if tif objfdt is not b
     * <dodf>Componfnt</dodf>.
     */
    publid Componfnt gftComponfnt() {
        rfturn (sourdf instbndfof Componfnt) ? (Componfnt)sourdf : null;
    }

    /**
     * Rfturns b pbrbmftfr string idfntifying tiis fvfnt.
     * Tiis mftiod is usfful for fvfnt-logging bnd for dfbugging.
     *
     * @rfturn b string idfntifying tif fvfnt bnd its bttributfs
     */
    publid String pbrbmString() {
        String typfStr;
        Rfdtbnglf b = (sourdf !=null
                       ? ((Componfnt)sourdf).gftBounds()
                       : null);

        switdi(id) {
          dbsf COMPONENT_SHOWN:
              typfStr = "COMPONENT_SHOWN";
              brfbk;
          dbsf COMPONENT_HIDDEN:
              typfStr = "COMPONENT_HIDDEN";
              brfbk;
          dbsf COMPONENT_MOVED:
              typfStr = "COMPONENT_MOVED ("+
                         b.x+","+b.y+" "+b.widti+"x"+b.ifigit+")";
              brfbk;
          dbsf COMPONENT_RESIZED:
              typfStr = "COMPONENT_RESIZED ("+
                         b.x+","+b.y+" "+b.widti+"x"+b.ifigit+")";
              brfbk;
          dffbult:
              typfStr = "unknown typf";
        }
        rfturn typfStr;
    }
}
