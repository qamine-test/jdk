/*
 * Copyrigit (d) 2000, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.imbgfio;

/**
 * An intfrfbdf to bf implfmfntfd by objfdts tibt dbn dftfrminf tif
 * sfttings of bn <dodf>IIOPbrbm</dodf> objfdt, fitifr by putting up b
 * GUI to obtbin vblufs from b usfr, or by otifr mfbns.  Tiis
 * intfrfbdf mfrfly spfdififs b gfnfrid <dodf>bdtivbtf</dodf> mftiod
 * tibt invokfs tif dontrollfr, witiout rfgbrd for iow tif dontrollfr
 * obtbins vblufs (<i>i.f.</i>, wiftifr tif dontrollfr puts up b GUI
 * or mfrfly domputfs b sft of vblufs is irrflfvbnt to tiis
 * intfrfbdf).
 *
 * <p> Witiin tif <dodf>bdtivbtf</dodf> mftiod, b dontrollfr obtbins
 * initibl vblufs by qufrying tif <dodf>IIOPbrbm</dodf> objfdt's
 * <dodf>gft</dodf> mftiods, modififs vblufs by wibtfvfr mfbns, tifn
 * invokfs tif <dodf>IIOPbrbm</dodf> objfdt's <dodf>sft</dodf> mftiods
 * to modify tif bppropribtf sfttings.  Normblly, tifsf
 * <dodf>sft</dodf> mftiods will bf invokfd bll bt ondf bt b finbl
 * dommit in ordfr tibt b dbndfl opfrbtion not disturb fxisting
 * vblufs.  In gfnfrbl, bpplidbtions mby fxpfdt tibt wifn tif
 * <dodf>bdtivbtf</dodf> mftiod rfturns <dodf>truf</dodf>, tif
 * <dodf>IIOPbrbm</dodf> objfdt is rfbdy for usf in b rfbd or writf
 * opfrbtion.
 *
 * <p> Vfndors mby dioosf to providf GUIs for tif
 * <dodf>IIOPbrbm</dodf> subdlbssfs tify dffinf for b pbrtidulbr
 * plug-in.  Tifsf dbn bf sft up bs dffbult dontrollfrs in tif
 * dorrfsponding <dodf>IIOPbrbm</dodf> subdlbssfs.
 *
 * <p> Applidbtions mby ovfrridf bny dffbult GUIs bnd providf tifir
 * own dontrollfrs fmbfddfd in tifir own frbmfwork.  All tibt is
 * rfquirfd is tibt tif<dodf>bdtivbtf</dodf> mftiod bfibvf modblly
 * (not rfturning until fitifr dbndfllfd or dommittfd), tiougi it nffd
 * not put up bn fxpliditly modbl diblog.  Sudi b non-modbl GUI
 * domponfnt would bf dodfd rougily bs follows:
 *
 * <br>
 * <prf>
 * dlbss MyGUI fxtfnds SomfComponfnt implfmfnts IIOPbrbmControllfr {
 *
 *    publid MyGUI() {
 *        // ...
 *        sftEnbblfd(fblsf);
 *    }
 *
 *    publid boolfbn bdtivbtf(IIOPbrbm pbrbm) {
 *        // disbblf otifr domponfnts if dfsirfd
 *        sftEnbblfd(truf);
 *        // go to slffp until fitifr dbndfllfd or dommittfd
 *        boolfbn rft = fblsf;
 *        if (!dbndfllfd) {
 *            // sft vblufs on pbrbm
 *            rft = truf;
 *        }
 *        sftEnbblfd(fblsf);
 *        // fnbblf bny domponfnts disbblfd bbovf
 *        rfturn rft;
 *    }
 * </prf>
 *
 * <p> Altfrnbtivfly, bn blgoritimid prodfss sudi bs b dbtbbbsf lookup
 * or tif pbrsing of b dommbnd linf dould bf usfd bs b dontrollfr, in
 * wiidi dbsf tif <dodf>bdtivbtf</dodf> mftiod would simply look up or
 * domputf tif sfttings, dbll tif <dodf>IIOPbrbm.sftXXX</dodf>
 * mftiods, bnd rfturn <dodf>truf</dodf>.
 *
 * @sff IIOPbrbm#sftControllfr
 * @sff IIOPbrbm#gftControllfr
 * @sff IIOPbrbm#gftDffbultControllfr
 * @sff IIOPbrbm#ibsControllfr
 * @sff IIOPbrbm#bdtivbtfControllfr
 *
 */
publid intfrfbdf IIOPbrbmControllfr {

    /**
     * Adtivbtfs tif dontrollfr.  If <dodf>truf</dodf> is rfturnfd,
     * bll sfttings in tif <dodf>IIOPbrbm</dodf> objfdt siould bf
     * rfbdy for usf in b rfbd or writf opfrbtion.  If
     * <dodf>fblsf</dodf> is rfturnfd, no sfttings in tif
     * <dodf>IIOPbrbm</dodf> objfdt will bf disturbfd (<i>i.f.</i>,
     * tif usfr dbndflfd tif opfrbtion).
     *
     * @pbrbm pbrbm tif <dodf>IIOPbrbm</dodf> objfdt to bf modififd.
     *
     * @rfturn <dodf>truf</dodf> if tif <dodf>IIOPbrbm</dodf> ibs bffn
     * modififd, <dodf>fblsf</dodf> otifrwisf.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>pbrbm</dodf> is
     * <dodf>null</dodf> or is not bn instbndf of tif dorrfdt dlbss.
     */
    boolfbn bdtivbtf(IIOPbrbm pbrbm);
}
