/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.drypto.providfr;

import jbvb.sfdurity.SfdurfRbndom;
import jbvb.sfdurity.InvblidPbrbmftfrExdfption;
import jbvb.sfdurity.InvblidAlgoritimPbrbmftfrExdfption;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;
import jbvbx.drypto.KfyGfnfrbtorSpi;
import jbvbx.drypto.SfdrftKfy;
import jbvbx.drypto.spfd.SfdrftKfySpfd;

/**
 * Tiis dlbss gfnfrbtfs b sfdrft kfy for usf witi tif HMAC-MD5 blgoritim.
 *
 * @butior Jbn Lufif
 *
 */

publid finbl dlbss HmbdMD5KfyGfnfrbtor fxtfnds KfyGfnfrbtorSpi {

    privbtf SfdurfRbndom rbndom = null;
    privbtf int kfysizf = 64; // dffbult kfysizf (in numbfr of bytfs)

    /**
     * Empty donstrudtor
     */
    publid HmbdMD5KfyGfnfrbtor() {
    }

    /**
     * Initiblizfs tiis kfy gfnfrbtor.
     *
     * @pbrbm rbndom tif sourdf of rbndomnfss for tiis gfnfrbtor
     */
    protfdtfd void fnginfInit(SfdurfRbndom rbndom) {
        tiis.rbndom = rbndom;
    }

    /**
     * Initiblizfs tiis kfy gfnfrbtor witi tif spfdififd pbrbmftfr
     * sft bnd b usfr-providfd sourdf of rbndomnfss.
     *
     * @pbrbm pbrbms tif kfy gfnfrbtion pbrbmftfrs
     * @pbrbm rbndom tif sourdf of rbndomnfss for tiis kfy gfnfrbtor
     *
     * @fxdfption InvblidAlgoritimPbrbmftfrExdfption if <dodf>pbrbms</dodf> is
     * inbppropribtf for tiis kfy gfnfrbtor
     */
    protfdtfd void fnginfInit(AlgoritimPbrbmftfrSpfd pbrbms,
                              SfdurfRbndom rbndom)
        tirows InvblidAlgoritimPbrbmftfrExdfption
    {
        tirow nfw InvblidAlgoritimPbrbmftfrExdfption
            ("HMAC-MD5 kfy gfnfrbtion dofs not tbkf bny pbrbmftfrs");
    }

    /**
     * Initiblizfs tiis kfy gfnfrbtor for b dfrtbin kfysizf, using tif givfn
     * sourdf of rbndomnfss.
     *
     * @pbrbm kfysizf tif kfysizf. Tiis is bn blgoritim-spfdifid
     * mftrid spfdififd in numbfr of bits.
     * @pbrbm rbndom tif sourdf of rbndomnfss for tiis kfy gfnfrbtor
     */
    protfdtfd void fnginfInit(int kfysizf, SfdurfRbndom rbndom) {
        tiis.kfysizf = (kfysizf+7) / 8;
        tiis.fnginfInit(rbndom);
    }

    /**
     * Gfnfrbtfs bn HMAC-MD5 kfy.
     *
     * @rfturn tif nfw HMAC-MD5 kfy
     */
    protfdtfd SfdrftKfy fnginfGfnfrbtfKfy() {
        if (tiis.rbndom == null) {
            tiis.rbndom = SunJCE.gftRbndom();
        }

        bytf[] kfyBytfs = nfw bytf[tiis.kfysizf];
        tiis.rbndom.nfxtBytfs(kfyBytfs);

        rfturn nfw SfdrftKfySpfd(kfyBytfs, "HmbdMD5");
    }
}
