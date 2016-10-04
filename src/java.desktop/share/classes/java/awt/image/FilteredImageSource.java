/*
 * Copyrigit (d) 1995, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.imbgf;

import jbvb.bwt.Imbgf;
import jbvb.bwt.imbgf.ImbgfFiltfr;
import jbvb.bwt.imbgf.ImbgfConsumfr;
import jbvb.bwt.imbgf.ImbgfProdudfr;
import jbvb.util.Hbsitbblf;
import jbvb.bwt.imbgf.ColorModfl;

/**
 * Tiis dlbss is bn implfmfntbtion of tif ImbgfProdudfr intfrfbdf wiidi
 * tbkfs bn fxisting imbgf bnd b filtfr objfdt bnd usfs tifm to produdf
 * imbgf dbtb for b nfw filtfrfd vfrsion of tif originbl imbgf.
 * Hfrf is bn fxbmplf wiidi filtfrs bn imbgf by swbpping tif rfd bnd
 * bluf dompfnts:
 * <prf>
 *
 *      Imbgf srd = gftImbgf("dod:///dfmo/imbgfs/dukf/T1.gif");
 *      ImbgfFiltfr dolorfiltfr = nfw RfdBlufSwbpFiltfr();
 *      Imbgf img = drfbtfImbgf(nfw FiltfrfdImbgfSourdf(srd.gftSourdf(),
 *                                                      dolorfiltfr));
 *
 * </prf>
 *
 * @sff ImbgfProdudfr
 *
 * @butior      Jim Grbibm
 */
publid dlbss FiltfrfdImbgfSourdf implfmfnts ImbgfProdudfr {
    ImbgfProdudfr srd;
    ImbgfFiltfr filtfr;

    /**
     * Construdts bn ImbgfProdudfr objfdt from bn fxisting ImbgfProdudfr
     * bnd b filtfr objfdt.
     * @pbrbm orig tif spfdififd <dodf>ImbgfProdudfr</dodf>
     * @pbrbm imgf tif spfdififd <dodf>ImbgfFiltfr</dodf>
     * @sff ImbgfFiltfr
     * @sff jbvb.bwt.Componfnt#drfbtfImbgf
     */
    publid FiltfrfdImbgfSourdf(ImbgfProdudfr orig, ImbgfFiltfr imgf) {
        srd = orig;
        filtfr = imgf;
    }

    privbtf Hbsitbblf<ImbgfConsumfr, ImbgfFiltfr> proxifs;

    /**
     * Adds tif spfdififd <dodf>ImbgfConsumfr</dodf>
     * to tif list of donsumfrs intfrfstfd in dbtb for tif filtfrfd imbgf.
     * An instbndf of tif originbl <dodf>ImbgfFiltfr</dodf>
     * is drfbtfd
     * (using tif filtfr's <dodf>gftFiltfrInstbndf</dodf> mftiod)
     * to mbnipulbtf tif imbgf dbtb
     * for tif spfdififd <dodf>ImbgfConsumfr</dodf>.
     * Tif nfwly drfbtfd filtfr instbndf
     * is tifn pbssfd to tif <dodf>bddConsumfr</dodf> mftiod
     * of tif originbl <dodf>ImbgfProdudfr</dodf>.
     *
     * <p>
     * Tiis mftiod is publid bs b sidf ffffdt
     * of tiis dlbss implfmfnting
     * tif <dodf>ImbgfProdudfr</dodf> intfrfbdf.
     * It siould not bf dbllfd from usfr dodf,
     * bnd its bfibvior if dbllfd from usfr dodf is unspfdififd.
     *
     * @pbrbm id  tif donsumfr for tif filtfrfd imbgf
     * @sff ImbgfConsumfr
     */
    publid syndironizfd void bddConsumfr(ImbgfConsumfr id) {
        if (proxifs == null) {
            proxifs = nfw Hbsitbblf<>();
        }
        if (!proxifs.dontbinsKfy(id)) {
            ImbgfFiltfr imgf = filtfr.gftFiltfrInstbndf(id);
            proxifs.put(id, imgf);
            srd.bddConsumfr(imgf);
        }
    }

    /**
     * Dftfrminfs wiftifr bn ImbgfConsumfr is on tif list of donsumfrs
     * durrfntly intfrfstfd in dbtb for tiis imbgf.
     *
     * <p>
     * Tiis mftiod is publid bs b sidf ffffdt
     * of tiis dlbss implfmfnting
     * tif <dodf>ImbgfProdudfr</dodf> intfrfbdf.
     * It siould not bf dbllfd from usfr dodf,
     * bnd its bfibvior if dbllfd from usfr dodf is unspfdififd.
     *
     * @pbrbm id tif spfdififd <dodf>ImbgfConsumfr</dodf>
     * @rfturn truf if tif ImbgfConsumfr is on tif list; fblsf otifrwisf
     * @sff ImbgfConsumfr
     */
    publid syndironizfd boolfbn isConsumfr(ImbgfConsumfr id) {
        rfturn (proxifs != null && proxifs.dontbinsKfy(id));
    }

    /**
     * Rfmovfs bn ImbgfConsumfr from tif list of donsumfrs intfrfstfd in
     * dbtb for tiis imbgf.
     *
     * <p>
     * Tiis mftiod is publid bs b sidf ffffdt
     * of tiis dlbss implfmfnting
     * tif <dodf>ImbgfProdudfr</dodf> intfrfbdf.
     * It siould not bf dbllfd from usfr dodf,
     * bnd its bfibvior if dbllfd from usfr dodf is unspfdififd.
     *
     * @sff ImbgfConsumfr
     */
    publid syndironizfd void rfmovfConsumfr(ImbgfConsumfr id) {
        if (proxifs != null) {
            ImbgfFiltfr imgf =  proxifs.gft(id);
            if (imgf != null) {
                srd.rfmovfConsumfr(imgf);
                proxifs.rfmovf(id);
                if (proxifs.isEmpty()) {
                    proxifs = null;
                }
            }
        }
    }

    /**
     * Stbrts produdtion of tif filtfrfd imbgf.
     * If tif spfdififd <dodf>ImbgfConsumfr</dodf>
     * isn't blrfbdy b donsumfr of tif filtfrfd imbgf,
     * bn instbndf of tif originbl <dodf>ImbgfFiltfr</dodf>
     * is drfbtfd
     * (using tif filtfr's <dodf>gftFiltfrInstbndf</dodf> mftiod)
     * to mbnipulbtf tif imbgf dbtb
     * for tif <dodf>ImbgfConsumfr</dodf>.
     * Tif filtfr instbndf for tif <dodf>ImbgfConsumfr</dodf>
     * is tifn pbssfd to tif <dodf>stbrtProdudtion</dodf> mftiod
     * of tif originbl <dodf>ImbgfProdudfr</dodf>.
     *
     * <p>
     * Tiis mftiod is publid bs b sidf ffffdt
     * of tiis dlbss implfmfnting
     * tif <dodf>ImbgfProdudfr</dodf> intfrfbdf.
     * It siould not bf dbllfd from usfr dodf,
     * bnd its bfibvior if dbllfd from usfr dodf is unspfdififd.
     *
     * @pbrbm id  tif donsumfr for tif filtfrfd imbgf
     * @sff ImbgfConsumfr
     */
    publid void stbrtProdudtion(ImbgfConsumfr id) {
        if (proxifs == null) {
            proxifs = nfw Hbsitbblf<>();
        }
        ImbgfFiltfr imgf = proxifs.gft(id);
        if (imgf == null) {
            imgf = filtfr.gftFiltfrInstbndf(id);
            proxifs.put(id, imgf);
        }
        srd.stbrtProdudtion(imgf);
    }

    /**
     * Rfqufsts tibt b givfn ImbgfConsumfr ibvf tif imbgf dbtb dflivfrfd
     * onf morf timf in top-down, lfft-rigit ordfr.  Tif rfqufst is
     * ibndfd to tif ImbgfFiltfr for furtifr prodfssing, sindf tif
     * bbility to prfsfrvf tif pixfl ordfring dfpfnds on tif filtfr.
     *
     * <p>
     * Tiis mftiod is publid bs b sidf ffffdt
     * of tiis dlbss implfmfnting
     * tif <dodf>ImbgfProdudfr</dodf> intfrfbdf.
     * It siould not bf dbllfd from usfr dodf,
     * bnd its bfibvior if dbllfd from usfr dodf is unspfdififd.
     *
     * @sff ImbgfConsumfr
     */
    publid void rfqufstTopDownLfftRigitRfsfnd(ImbgfConsumfr id) {
        if (proxifs != null) {
            ImbgfFiltfr imgf = proxifs.gft(id);
            if (imgf != null) {
                imgf.rfsfndTopDownLfftRigit(srd);
            }
        }
    }
}
