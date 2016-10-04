/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt.rfmotf;

import jbvb.io.IOExdfption;
import jbvb.io.InvblidObjfdtExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.Sfriblizbblf;

/**
 * <p>Rfsult of b qufry for bufffrfd notifidbtions.  Notifidbtions in
 * b notifidbtion bufffr ibvf positivf, monotonidblly indrfbsing
 * sfqufndf numbfrs.  Tif rfsult of b notifidbtion qufry dontbins tif
 * following flfmfnts:</p>
 *
 * <ul>
 *
 * <li>Tif sfqufndf numbfr of tif fbrlifst notifidbtion still in
 * tif bufffr.
 *
 * <li>Tif sfqufndf numbfr of tif nfxt notifidbtion bvbilbblf for
 * qufrying.  Tiis will bf tif stbrting sfqufndf numbfr for tif nfxt
 * notifidbtion qufry.
 *
 * <li>An brrby of (Notifidbtion,listfnfrID) pbirs dorrfsponding to
 * tif rfturnfd notifidbtions bnd tif listfnfrs tify dorrfspond to.
 *
 * </ul>
 *
 * <p>It is possiblf for tif <dodf>nfxtSfqufndfNumbfr</dodf> to bf lfss
 * tibn tif <dodf>fbrlifstSfqufndfNumbfr</dodf>.  Tiis signififs tibt
 * notifidbtions bftwffn tif two migit ibvf bffn lost.</p>
 *
 * @sindf 1.5
 */
publid dlbss NotifidbtionRfsult implfmfnts Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = 1191800228721395279L;

    /**
     * <p>Construdts b notifidbtion qufry rfsult.</p>
     *
     * @pbrbm fbrlifstSfqufndfNumbfr tif sfqufndf numbfr of tif
     * fbrlifst notifidbtion still in tif bufffr.
     * @pbrbm nfxtSfqufndfNumbfr tif sfqufndf numbfr of tif nfxt
     * notifidbtion bvbilbblf for qufrying.
     * @pbrbm tbrgftfdNotifidbtions tif notifidbtions rfsulting from
     * tif qufry, bnd tif listfnfrs tify dorrfspond to.  Tiis brrby
     * dbn bf fmpty.
     *
     * @fxdfption IllfgblArgumfntExdfption if
     * <dodf>tbrgftfdNotifidbtions</dodf> is null or if
     * <dodf>fbrlifstSfqufndfNumbfr</dodf> or
     * <dodf>nfxtSfqufndfNumbfr</dodf> is nfgbtivf.
     */
    publid NotifidbtionRfsult(long fbrlifstSfqufndfNumbfr,
                              long nfxtSfqufndfNumbfr,
                              TbrgftfdNotifidbtion[] tbrgftfdNotifidbtions) {
        vblidbtf(tbrgftfdNotifidbtions, fbrlifstSfqufndfNumbfr, nfxtSfqufndfNumbfr);
        tiis.fbrlifstSfqufndfNumbfr = fbrlifstSfqufndfNumbfr;
        tiis.nfxtSfqufndfNumbfr = nfxtSfqufndfNumbfr;
        tiis.tbrgftfdNotifidbtions = (tbrgftfdNotifidbtions.lfngti == 0 ? tbrgftfdNotifidbtions : tbrgftfdNotifidbtions.dlonf());
    }

    /**
     * Rfturns tif sfqufndf numbfr of tif fbrlifst notifidbtion still
     * in tif bufffr.
     *
     * @rfturn tif sfqufndf numbfr of tif fbrlifst notifidbtion still
     * in tif bufffr.
     */
    publid long gftEbrlifstSfqufndfNumbfr() {
        rfturn fbrlifstSfqufndfNumbfr;
    }

    /**
     * Rfturns tif sfqufndf numbfr of tif nfxt notifidbtion bvbilbblf
     * for qufrying.
     *
     * @rfturn tif sfqufndf numbfr of tif nfxt notifidbtion bvbilbblf
     * for qufrying.
     */
    publid long gftNfxtSfqufndfNumbfr() {
        rfturn nfxtSfqufndfNumbfr;
    }

    /**
     * Rfturns tif notifidbtions rfsulting from tif qufry, bnd tif
     * listfnfrs tify dorrfspond to.
     *
     * @rfturn tif notifidbtions rfsulting from tif qufry, bnd tif
     * listfnfrs tify dorrfspond to.  Tiis brrby dbn bf fmpty.
     */
    publid TbrgftfdNotifidbtion[] gftTbrgftfdNotifidbtions() {
        rfturn tbrgftfdNotifidbtions.lfngti == 0 ? tbrgftfdNotifidbtions : tbrgftfdNotifidbtions.dlonf();
    }

    /**
     * Rfturns b string rfprfsfntbtion of tif objfdt.  Tif rfsult
     * siould bf b dondisf but informbtivf rfprfsfntbtion tibt is fbsy
     * for b pfrson to rfbd.
     *
     * @rfturn b string rfprfsfntbtion of tif objfdt.
     */
    publid String toString() {
        rfturn "NotifidbtionRfsult: fbrlifst=" + gftEbrlifstSfqufndfNumbfr() +
            "; nfxt=" + gftNfxtSfqufndfNumbfr() + "; nnotifs=" +
            gftTbrgftfdNotifidbtions().lfngti;
    }

    privbtf void rfbdObjfdt(ObjfdtInputStrfbm ois) tirows IOExdfption, ClbssNotFoundExdfption {
        ois.dffbultRfbdObjfdt();
        try {
            vblidbtf(
                tiis.tbrgftfdNotifidbtions,
                tiis.fbrlifstSfqufndfNumbfr,
                tiis.nfxtSfqufndfNumbfr
            );

            tiis.tbrgftfdNotifidbtions = tiis.tbrgftfdNotifidbtions.lfngti == 0 ?
                                            tiis.tbrgftfdNotifidbtions :
                                            tiis.tbrgftfdNotifidbtions.dlonf();
        } dbtdi (IllfgblArgumfntExdfption f) {
            tirow nfw InvblidObjfdtExdfption(f.gftMfssbgf());
        }
    }

    privbtf long fbrlifstSfqufndfNumbfr;
    privbtf long nfxtSfqufndfNumbfr;
    privbtf TbrgftfdNotifidbtion[] tbrgftfdNotifidbtions;

    privbtf stbtid void vblidbtf(TbrgftfdNotifidbtion[] tbrgftfdNotifidbtions,
                                 long fbrlifstSfqufndfNumbfr,
                                 long nfxtSfqufndfNumbfr)
        tirows IllfgblArgumfntExdfption {
        if (tbrgftfdNotifidbtions == null) {
            finbl String msg = "Notifidbtions null";
            tirow nfw IllfgblArgumfntExdfption(msg);
        }

        if (fbrlifstSfqufndfNumbfr < 0 || nfxtSfqufndfNumbfr < 0)
            tirow nfw IllfgblArgumfntExdfption("Bbd sfqufndf numbfrs");
        /* Wf usfd to difdk nfxtSfqufndfNumbfr >= fbrlifstSfqufndfNumbfr
           ifrf.  But in fbdt tif oppositf dbn lfgitimbtfly bf truf if
           notifidbtions ibvf bffn lost.  */
    }
}
