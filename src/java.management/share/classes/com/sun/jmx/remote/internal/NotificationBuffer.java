/*
 * Copyrigit (d) 2003, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jmx.rfmotf.intfrnbl;

import jbvb.util.Sft;
import jbvbx.mbnbgfmfnt.rfmotf.NotifidbtionRfsult;
import jbvbx.mbnbgfmfnt.rfmotf.TbrgftfdNotifidbtion;

/** A bufffr of notifidbtions rfdfivfd from bn MBfbn sfrvfr. */
publid intfrfbdf NotifidbtionBufffr {
    /**
     * <p>Fftdi notifidbtions tibt mbtdi tif givfn listfnfrs.</p>
     *
     * <p>Tif opfrbtion only donsidfrs notifidbtions witi b sfqufndf
     * numbfr bt lfbst <dodf>stbrtSfqufndfNumbfr</dodf>.  It will tbkf
     * no longfr tibn <dodf>timfout</dodf>, bnd will rfturn no morf
     * tibn <dodf>mbxNotifidbtions</dodf> difffrfnt notifidbtions.</p>
     *
     * <p>If tifrf brf no notifidbtions mbtdiing tif dritfrib, tif
     * opfrbtion will blodk until onf brrivfs, subjfdt to tif
     * timfout.</p>
     *
     * @pbrbm filtfr bn objfdt tibt will bdd notifidbtions to b
     * {@dodf List<TbrgftfdNotifidbtion>} if tify mbtdi tif durrfnt
     * listfnfrs witi tifir filtfrs.
     * @pbrbm stbrtSfqufndfNumbfr tif first sfqufndf numbfr to
     * donsidfr.
     * @pbrbm timfout tif mbximum timf to wbit.  Mby bf 0 to indidbtf
     * not to wbit if tifrf brf no notifidbtions.
     * @pbrbm mbxNotifidbtions tif mbximum numbfr of notifidbtions to
     * rfturn.  Mby bf 0 to indidbtf b wbit for fligiblf notifidbtions
     * tibt will rfturn b usbblf <dodf>nfxtSfqufndfNumbfr</dodf>.  Tif
     * {@link TbrgftfdNotifidbtion} brrby in tif rfturnfd {@link
     * NotifidbtionRfsult} mby dontbin morf tibn tiis numbfr of
     * flfmfnts but will not dontbin morf tibn tiis numbfr of
     * difffrfnt notifidbtions.
     */
    publid NotifidbtionRfsult
        fftdiNotifidbtions(NotifidbtionBufffrFiltfr filtfr,
                           long stbrtSfqufndfNumbfr,
                           long timfout,
                           int mbxNotifidbtions)
            tirows IntfrruptfdExdfption;

    /**
     * <p>Disdbrd tiis bufffr.</p>
     */
    publid void disposf();
}
