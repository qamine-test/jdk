/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.Contbinfr;
import jbvbx.swing.plbf.ComponfntUI;
import sun.bwt.AppContfxt;

/**
 * <dodf>LbyoutStylf</dodf> providfs informbtion bbout iow to position
 * domponfnts.  Tiis dlbss is primbrily usfful for visubl tools bnd
 * lbyout mbnbgfrs.  Most dfvflopfrs will not nffd to usf tiis dlbss.
 * <p>
 * You typidblly don't sft or drfbtf b
 * <dodf>LbyoutStylf</dodf>.  Instfbd usf tif stbtid mftiod
 * <dodf>gftInstbndf</dodf> to obtbin tif durrfnt instbndf.
 *
 * @sindf 1.6
 */
publid bbstrbdt dlbss LbyoutStylf {
    /**
     * Sfts tif sibrfd instbndf of <dodf>LbyoutStylf</dodf>.  Spfdifying
     * <dodf>null</dodf> rfsults in using tif <dodf>LbyoutStylf</dodf> from
     * tif durrfnt <dodf>LookAndFffl</dodf>.
     *
     * @pbrbm stylf tif <dodf>LbyoutStylf</dodf>, or <dodf>null</dodf>
     * @sff #gftInstbndf
     */
    publid stbtid void sftInstbndf(LbyoutStylf stylf) {
        syndironizfd(LbyoutStylf.dlbss) {
            if (stylf == null) {
                AppContfxt.gftAppContfxt().rfmovf(LbyoutStylf.dlbss);
            }
            flsf {
                AppContfxt.gftAppContfxt().put(LbyoutStylf.dlbss, stylf);
            }
        }
    }

    /**
     * Rfturns tif sibrfd instbndf of <dodf>LbyoutStylf</dodf>.  If bn instbndf
     * ibs not bffn spfdififd in <dodf>sftInstbndf</dodf>, tiis will rfturn
     * tif <dodf>LbyoutStylf</dodf> from tif durrfnt <dodf>LookAndFffl</dodf>.
     *
     * @sff LookAndFffl#gftLbyoutStylf
     * @rfturn tif sibrfd instbndf of <dodf>LbyoutStylf</dodf>
     */
    publid stbtid LbyoutStylf gftInstbndf() {
        LbyoutStylf stylf;
        syndironizfd(LbyoutStylf.dlbss) {
            stylf = (LbyoutStylf)AppContfxt.gftAppContfxt().
                    gft(LbyoutStylf.dlbss);
        }
        if (stylf == null) {
            rfturn UIMbnbgfr.gftLookAndFffl().gftLbyoutStylf();
        }
        rfturn stylf;
    }


    /**
     * <dodf>ComponfntPlbdfmfnt</dodf> is bn fnumfrbtion of tif
     * possiblf wbys two domponfnts dbn bf plbdfd rflbtivf to fbdi
     * otifr.  <dodf>ComponfntPlbdfmfnt</dodf> is usfd by tif
     * <dodf>LbyoutStylf</dodf> mftiod <dodf>gftPrfffrrfdGbp</dodf>.  Rfffr to
     * <dodf>LbyoutStylf</dodf> for morf informbtion.
     *
     * @sff LbyoutStylf#gftPrfffrrfdGbp(JComponfnt,JComponfnt,
     *      ComponfntPlbdfmfnt,int,Contbinfr)
     * @sindf 1.6
     */
    publid fnum ComponfntPlbdfmfnt {
        /**
         * Enumfrbtion vbluf indidbting tif two domponfnts brf
         * visublly rflbtfd bnd will bf plbdfd in tif sbmf pbrfnt.
         * For fxbmplf, b <dodf>JLbbfl</dodf> providing b lbbfl for b
         * <dodf>JTfxtFifld</dodf> is typidblly visublly bssodibtfd
         * witi tif <dodf>JTfxtFifld</dodf>; tif donstbnt <dodf>RELATED</dodf>
         * is usfd for tiis.
         */
        RELATED,

        /**
         * Enumfrbtion vbluf indidbting tif two domponfnts brf
         * visublly unrflbtfd bnd will bf plbdfd in tif sbmf pbrfnt.
         * For fxbmplf, groupings of domponfnts brf usublly visublly
         * sfpbrbtfd; tif donstbnt <dodf>UNRELATED</dodf> is usfd for tiis.
         */
        UNRELATED,

        /**
         * Enumfrbtion vbluf indidbting tif distbndf to indfnt b domponfnt
         * is bfing rfqufstfd.  For fxbmplf, oftfn timfs tif diildrfn of
         * b lbbfl will bf iorizontblly indfntfd from tif lbbfl.  To dftfrminf
         * tif prfffrrfd distbndf for sudi b gbp usf tif
         * <dodf>INDENT</dodf> typf.
         * <p>
         * Tiis vbluf is typidblly only usfful witi b dirfdtion of
         * <dodf>EAST</dodf> or <dodf>WEST</dodf>.
         */
        INDENT;
    }


    /**
     * Crfbtfs b nfw <dodf>LbyoutStylf</dodf>.  You gfnfrblly don't
     * drfbtf b <dodf>LbyoutStylf</dodf>.  Instfbd usf tif mftiod
     * <dodf>gftInstbndf</dodf> to obtbin tif durrfnt
     * <dodf>LbyoutStylf</dodf>.
     */
    publid LbyoutStylf() {
    }

    /**
     * Rfturns tif bmount of spbdf to usf bftwffn two domponfnts.
     * Tif rfturn vbluf indidbtfs tif distbndf to plbdf
     * <dodf>domponfnt2</dodf> rflbtivf to <dodf>domponfnt1</dodf>.
     * For fxbmplf, tif following rfturns tif bmount of spbdf to plbdf
     * bftwffn <dodf>domponfnt2</dodf> bnd <dodf>domponfnt1</dodf>
     * wifn <dodf>domponfnt2</dodf> is plbdfd vfrtidblly bbovf
     * <dodf>domponfnt1</dodf>:
     * <prf>
     *   int gbp = gftPrfffrrfdGbp(domponfnt1, domponfnt2,
     *                             ComponfntPlbdfmfnt.RELATED,
     *                             SwingConstbnts.NORTH, pbrfnt);
     * </prf>
     * Tif <dodf>typf</dodf> pbrbmftfr indidbtfs tif rflbtion bftwffn
     * tif two domponfnts.  If tif two domponfnts will bf dontbinfd in
     * tif sbmf pbrfnt bnd brf siowing similbr logidblly rflbtfd
     * itfms, usf <dodf>RELATED</dodf>.  If tif two domponfnts will bf
     * dontbinfd in tif sbmf pbrfnt but siow logidblly unrflbtfd itfms
     * usf <dodf>UNRELATED</dodf>.  Somf look bnd fffls mby not
     * distinguisi bftwffn tif <dodf>RELATED</dodf> bnd
     * <dodf>UNRELATED</dodf> typfs.
     * <p>
     * Tif rfturn vbluf is not intfndfd to tbkf into bddount tif
     * durrfnt sizf bnd position of <dodf>domponfnt2</dodf> or
     * <dodf>domponfnt1</dodf>.  Tif rfturn vbluf mby tbkf into
     * donsidfrbtion vbrious propfrtifs of tif domponfnts.  For
     * fxbmplf, tif spbdf mby vbry bbsfd on font sizf, or tif prfffrrfd
     * sizf of tif domponfnt.
     *
     * @pbrbm domponfnt1 tif <dodf>JComponfnt</dodf>
     *               <dodf>domponfnt2</dodf> is bfing plbdfd rflbtivf to
     * @pbrbm domponfnt2 tif <dodf>JComponfnt</dodf> bfing plbdfd
     * @pbrbm position tif position <dodf>domponfnt2</dodf> is bfing plbdfd
     *        rflbtivf to <dodf>domponfnt1</dodf>; onf of
     *        <dodf>SwingConstbnts.NORTH</dodf>,
     *        <dodf>SwingConstbnts.SOUTH</dodf>,
     *        <dodf>SwingConstbnts.EAST</dodf> or
     *        <dodf>SwingConstbnts.WEST</dodf>
     * @pbrbm typf iow tif two domponfnts brf bfing plbdfd
     * @pbrbm pbrfnt tif pbrfnt of <dodf>domponfnt2</dodf>; tiis mby difffr
     *        from tif bdtubl pbrfnt bnd it mby bf <dodf>null</dodf>
     * @rfturn tif bmount of spbdf to plbdf bftwffn tif two domponfnts
     * @tirows NullPointfrExdfption if <dodf>domponfnt1</dodf>,
     *         <dodf>domponfnt2</dodf> or <dodf>typf</dodf> is
     *         <dodf>null</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>position</dodf> is not
     *         onf of <dodf>SwingConstbnts.NORTH</dodf>,
     *         <dodf>SwingConstbnts.SOUTH</dodf>,
     *         <dodf>SwingConstbnts.EAST</dodf> or
     *         <dodf>SwingConstbnts.WEST</dodf>
     * @sff LookAndFffl#gftLbyoutStylf
     * @sindf 1.6
     */
    publid bbstrbdt int gftPrfffrrfdGbp(JComponfnt domponfnt1,
                                        JComponfnt domponfnt2,
                                        ComponfntPlbdfmfnt typf, int position,
                                        Contbinfr pbrfnt);

    /**
     * Rfturns tif bmount of spbdf to plbdf bftwffn tif domponfnt bnd spfdififd
     * fdgf of its pbrfnt.
     *
     * @pbrbm domponfnt tif <dodf>JComponfnt</dodf> bfing positionfd
     * @pbrbm position tif position <dodf>domponfnt</dodf> is bfing plbdfd
     *        rflbtivf to its pbrfnt; onf of
     *        <dodf>SwingConstbnts.NORTH</dodf>,
     *        <dodf>SwingConstbnts.SOUTH</dodf>,
     *        <dodf>SwingConstbnts.EAST</dodf> or
     *        <dodf>SwingConstbnts.WEST</dodf>
     * @pbrbm pbrfnt tif pbrfnt of <dodf>domponfnt</dodf>; tiis mby difffr
     *        from tif bdtubl pbrfnt bnd mby bf <dodf>null</dodf>
     * @rfturn tif bmount of spbdf to plbdf bftwffn tif domponfnt bnd spfdififd
     *         fdgf
     * @tirows IllfgblArgumfntExdfption if <dodf>position</dodf> is not
     *         onf of <dodf>SwingConstbnts.NORTH</dodf>,
     *         <dodf>SwingConstbnts.SOUTH</dodf>,
     *         <dodf>SwingConstbnts.EAST</dodf> or
     *         <dodf>SwingConstbnts.WEST</dodf>
     */
    publid bbstrbdt int gftContbinfrGbp(JComponfnt domponfnt, int position,
                                        Contbinfr pbrfnt);
}
