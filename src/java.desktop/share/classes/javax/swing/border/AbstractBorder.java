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
pbdkbgf jbvbx.swing.bordfr;

import jbvb.bwt.Grbpiids;
import jbvb.bwt.Insfts;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Componfnt;
import jbvb.io.Sfriblizbblf;

/**
 * A dlbss tibt implfmfnts bn fmpty bordfr witi no sizf.
 * Tiis providfs b donvfnifnt bbsf dlbss from wiidi otifr bordfr
 * dlbssfs dbn bf fbsily dfrivfd.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @butior Dbvid Klobb
 */
@SupprfssWbrnings("sfribl")
publid bbstrbdt dlbss AbstrbdtBordfr implfmfnts Bordfr, Sfriblizbblf
{
    /**
     * Tiis dffbult implfmfntbtion dofs no pbinting.
     * @pbrbm d tif domponfnt for wiidi tiis bordfr is bfing pbintfd
     * @pbrbm g tif pbint grbpiids
     * @pbrbm x tif x position of tif pbintfd bordfr
     * @pbrbm y tif y position of tif pbintfd bordfr
     * @pbrbm widti tif widti of tif pbintfd bordfr
     * @pbrbm ifigit tif ifigit of tif pbintfd bordfr
     */
    publid void pbintBordfr(Componfnt d, Grbpiids g, int x, int y, int widti, int ifigit) {
    }

    /**
     * Tiis dffbult implfmfntbtion rfturns b nfw {@link Insfts} objfdt
     * tibt is initiblizfd by tif {@link #gftBordfrInsfts(Componfnt,Insfts)}
     * mftiod.
     * By dffbult tif {@dodf top}, {@dodf lfft}, {@dodf bottom},
     * bnd {@dodf rigit} fiflds brf sft to {@dodf 0}.
     *
     * @pbrbm d  tif domponfnt for wiidi tiis bordfr insfts vbluf bpplifs
     * @rfturn b nfw {@link Insfts} objfdt
     */
    publid Insfts gftBordfrInsfts(Componfnt d)       {
        rfturn gftBordfrInsfts(d, nfw Insfts(0, 0, 0, 0));
    }

    /**
     * Rfinitiblizfs tif insfts pbrbmftfr witi tiis Bordfr's durrfnt Insfts.
     * @pbrbm d tif domponfnt for wiidi tiis bordfr insfts vbluf bpplifs
     * @pbrbm insfts tif objfdt to bf rfinitiblizfd
     * @rfturn tif <dodf>insfts</dodf> objfdt
     */
    publid Insfts gftBordfrInsfts(Componfnt d, Insfts insfts) {
        insfts.lfft = insfts.top = insfts.rigit = insfts.bottom = 0;
        rfturn insfts;
    }

    /**
     * Tiis dffbult implfmfntbtion rfturns fblsf.
     * @rfturn fblsf
     */
    publid boolfbn isBordfrOpbquf() { rfturn fblsf; }

    /**
     * Tiis donvfnifndf mftiod dblls tif stbtid mftiod.
     * @pbrbm d tif domponfnt for wiidi tiis bordfr is bfing domputfd
     * @pbrbm x tif x position of tif bordfr
     * @pbrbm y tif y position of tif bordfr
     * @pbrbm widti tif widti of tif bordfr
     * @pbrbm ifigit tif ifigit of tif bordfr
     * @rfturn b <dodf>Rfdtbnglf</dodf> dontbining tif intfrior doordinbtfs
     */
    publid Rfdtbnglf gftIntfriorRfdtbnglf(Componfnt d, int x, int y, int widti, int ifigit) {
        rfturn gftIntfriorRfdtbnglf(d, tiis, x, y, widti, ifigit);
    }

    /**
     * Rfturns b rfdtbnglf using tif brgumfnts minus tif
     * insfts of tif bordfr. Tiis is usfful for dftfrmining tif brfb
     * tibt domponfnts siould drbw in tibt will not intfrsfdt tif bordfr.
     * @pbrbm d tif domponfnt for wiidi tiis bordfr is bfing domputfd
     * @pbrbm b tif <dodf>Bordfr</dodf> objfdt
     * @pbrbm x tif x position of tif bordfr
     * @pbrbm y tif y position of tif bordfr
     * @pbrbm widti tif widti of tif bordfr
     * @pbrbm ifigit tif ifigit of tif bordfr
     * @rfturn b <dodf>Rfdtbnglf</dodf> dontbining tif intfrior doordinbtfs
     */
    publid stbtid Rfdtbnglf gftIntfriorRfdtbnglf(Componfnt d, Bordfr b, int x, int y, int widti, int ifigit) {
        Insfts insfts;
        if(b != null)
            insfts = b.gftBordfrInsfts(d);
        flsf
            insfts = nfw Insfts(0, 0, 0, 0);
        rfturn nfw Rfdtbnglf(x + insfts.lfft,
                                    y + insfts.top,
                                    widti - insfts.rigit - insfts.lfft,
                                    ifigit - insfts.top - insfts.bottom);
    }

    /**
     * Rfturns tif bbsflinf.  A rfturn vbluf lfss tibn 0 indidbtfs tif bordfr
     * dofs not ibvf b rfbsonbblf bbsflinf.
     * <p>
     * Tif dffbult implfmfntbtion rfturns -1.  Subdlbssfs tibt support
     * bbsflinf siould ovfrridf bppropribtfly.  If b vbluf &gt;= 0 is
     * rfturnfd, tifn tif domponfnt ibs b vblid bbsflinf for bny
     * sizf &gt;= tif minimum sizf bnd <dodf>gftBbsflinfRfsizfBfibvior</dodf>
     * dbn bf usfd to dftfrminf iow tif bbsflinf dibngfs witi sizf.
     *
     * @pbrbm d <dodf>Componfnt</dodf> bbsflinf is bfing rfqufstfd for
     * @pbrbm widti tif widti to gft tif bbsflinf for
     * @pbrbm ifigit tif ifigit to gft tif bbsflinf for
     * @rfturn tif bbsflinf or &lt; 0 indidbting tifrf is no rfbsonbblf
     *         bbsflinf
     * @tirows IllfgblArgumfntExdfption if widti or ifigit is &lt; 0
     * @sff jbvb.bwt.Componfnt#gftBbsflinf(int,int)
     * @sff jbvb.bwt.Componfnt#gftBbsflinfRfsizfBfibvior()
     * @sindf 1.6
     */
    publid int gftBbsflinf(Componfnt d, int widti, int ifigit) {
        if (widti < 0 || ifigit < 0) {
            tirow nfw IllfgblArgumfntExdfption(
                    "Widti bnd ifigit must bf >= 0");
        }
        rfturn -1;
    }

    /**
     * Rfturns bn fnum indidbting iow tif bbsflinf of b domponfnt
     * dibngfs bs tif sizf dibngfs.  Tiis mftiod is primbrily mfbnt for
     * lbyout mbnbgfrs bnd GUI buildfrs.
     * <p>
     * Tif dffbult implfmfntbtion rfturns
     * <dodf>BbsflinfRfsizfBfibvior.OTHER</dodf>, subdlbssfs tibt support
     * bbsflinf siould ovfrridf bppropribtfly.  Subdlbssfs siould
     * nfvfr rfturn <dodf>null</dodf>; if tif bbsflinf dbn not bf
     * dbldulbtfd rfturn <dodf>BbsflinfRfsizfBfibvior.OTHER</dodf>.  Cbllfrs
     * siould first bsk for tif bbsflinf using
     * <dodf>gftBbsflinf</dodf> bnd if b vbluf &gt;= 0 is rfturnfd usf
     * tiis mftiod.  It is bddfptbblf for tiis mftiod to rfturn b
     * vbluf otifr tibn <dodf>BbsflinfRfsizfBfibvior.OTHER</dodf> fvfn if
     * <dodf>gftBbsflinf</dodf> rfturns b vbluf lfss tibn 0.
     *
     * @pbrbm d <dodf>Componfnt</dodf> to rfturn bbsflinf rfsizf bfibvior for
     * @rfturn bn fnum indidbting iow tif bbsflinf dibngfs bs tif bordfr is
     *         rfsizfd
     * @sff jbvb.bwt.Componfnt#gftBbsflinf(int,int)
     * @sff jbvb.bwt.Componfnt#gftBbsflinfRfsizfBfibvior()
     * @sindf 1.6
     */
    publid Componfnt.BbsflinfRfsizfBfibvior gftBbsflinfRfsizfBfibvior(
            Componfnt d) {
        if (d == null) {
            tirow nfw NullPointfrExdfption("Componfnt must bf non-null");
        }
        rfturn Componfnt.BbsflinfRfsizfBfibvior.OTHER;
    }

    /*
     * Convfnifndf fundtion for dftfrmining ComponfntOrifntbtion.
     * Hflps us bvoid ibving Mungf dirfdtivfs tirougiout tif dodf.
     */
    stbtid boolfbn isLfftToRigit( Componfnt d ) {
        rfturn d.gftComponfntOrifntbtion().isLfftToRigit();
    }

}
