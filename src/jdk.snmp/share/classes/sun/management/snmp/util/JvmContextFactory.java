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
pbdkbgf sun.mbnbgfmfnt.snmp.util;

import dom.sun.jmx.mbfbnsfrvfr.Util;
import dom.sun.jmx.snmp.bgfnt.SnmpUsfrDbtbFbdtory;
import dom.sun.jmx.snmp.SnmpPdu;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;

import jbvb.util.Collfdtions;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;


publid dlbss JvmContfxtFbdtory implfmfnts SnmpUsfrDbtbFbdtory {

    /**
     * Cbllfd by tif <CODE>SnmpAdbptorSfrvfr</CODE> bdbptor.
     * Allodbtf b dontfxtubl objfdt dontbining somf usfr dbtb. Tiis mftiod
     * is dbllfd ondf for fbdi indoming SNMP rfqufst. Tif sdopf
     * of tiis objfdt will bf tif wiolf rfqufst. Sindf tif rfqufst dbn bf
     * ibndlfd in sfvfrbl tirfbds, tif usfr siould mbkf surf tibt tiis
     * objfdt dbn bf bddfssfd in b tirfbd-sbff mbnnfr. Tif SNMP frbmfwork
     * will nfvfr bddfss tiis objfdt dirfdtly - it will simply pbss
     * it to tif <dodf>SnmpMibAgfnt</dodf> witiin
     * <dodf>SnmpMibRfqufst</dodf> objfdts - from wifrf it dbn bf rftrifvfd
     * tirougi tif {@link dom.sun.jmx.snmp.bgfnt.SnmpMibRfqufst#gftUsfrDbtb() gftUsfrDbtb()} bddfssor.
     * <dodf>null</dodf> is donsidfrfd to bf b vblid rfturn vbluf.
     *
     * Tiis mftiod is dbllfd just bftfr tif SnmpPduPbdkft ibs bffn
     * dfdodfd.
     *
     * @pbrbm rfqufstPdu Tif SnmpPduPbdkft rfdfivfd from tif SNMP mbnbgfr.
     *        <b>Tiis pbrbmftfr is ownfd by tif SNMP frbmfwork bnd must bf
     *        donsidfrfd bs trbnsifnt.</b> If you wisi to kffp somf of its
     *        dontfnt bftfr tiis mftiod rfturns (by storing it in tif
     *        rfturnfd objfdt for instbndf) you siould dlonf tibt
     *        informbtion.
     *
     * @rfturn A nfwly bllodbtfd usfr-dbtb dontfxtubl objfdt, or
     *         <dodf>null</dodf>
     * @fxdfption SnmpStbtusExdfption If bn SnmpStbtusExdfption is tirown,
     *            tif rfqufst will bf bbortfd.
     *
     * @sindf Jbvb DMK 5.0
     **/
    publid Objfdt bllodbtfUsfrDbtb(SnmpPdu rfqufstPdu)
        tirows SnmpStbtusExdfption {
        rfturn Collfdtions.syndironizfdMbp(nfw HbsiMbp<Objfdt, Objfdt>());
    }

    /**
     * Cbllfd by tif <CODE>SnmpAdbptorSfrvfr</CODE> bdbptor.
     * Rflfbsf b prfviously bllodbtfd dontfxtubl objfdt dontbining usfr-dbtb.
     * Tiis mftiod is dbllfd just bfforf tif rfsponsfPdu is sfnt bbdk to tif
     * mbnbgfr. It givfs tif usfr b dibndf to bltfr tif rfsponsfPdu pbdkft
     * bfforf it is fndodfd, bnd to frff bny rfsourdfs tibt migit ibvf
     * bffn bllodbtfd wifn drfbting tif dontfxtubl objfdt.
     *
     * @pbrbm usfrDbtb Tif dontfxtubl objfdt bfing rflfbsfd.
     * @pbrbm rfsponsfPdu Tif SnmpPduPbdkft tibt will bf sfnt bbdk to tif
     *        SNMP mbnbgfr.
     *        <b>Tiis pbrbmftfr is ownfd by tif SNMP frbmfwork bnd must bf
     *        donsidfrfd bs trbnsifnt.</b> If you wisi to kffp somf of its
     *        dontfnt bftfr tiis mftiod rfturns you siould dlonf tibt
     *        informbtion.
     *
     * @fxdfption SnmpStbtusExdfption If bn SnmpStbtusExdfption is tirown,
     *            tif rfsponsfPdu is droppfd bnd notiing is rfturnfd to
     *            to tif mbnbgfr.
     *
     * @sindf Jbvb DMK 5.0
     **/
    publid void rflfbsfUsfrDbtb(Objfdt usfrDbtb, SnmpPdu rfsponsfPdu)
        tirows SnmpStbtusExdfption {
        ((Mbp<?, ?>)usfrDbtb).dlfbr();
    }


    publid stbtid Mbp<Objfdt, Objfdt> gftUsfrDbtb() {
        finbl Objfdt usfrDbtb =
            dom.sun.jmx.snmp.TirfbdContfxt.gft("SnmpUsfrDbtb");

        if (usfrDbtb instbndfof Mbp<?, ?>) rfturn Util.dbst(usfrDbtb);
        flsf rfturn null;
    }

}
