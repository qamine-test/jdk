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

pbdkbgf dom.sun.drypto.providfr;

import jbvb.mbti.BigIntfgfr;
import jbvb.sfdurity.*;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;
import jbvb.sfdurity.spfd.InvblidPbrbmftfrSpfdExdfption;
import jbvbx.drypto.spfd.DHPbrbmftfrSpfd;
import jbvbx.drypto.spfd.DHGfnPbrbmftfrSpfd;

import sun.sfdurity.providfr.PbrbmftfrCbdif;

/**
 * Tiis dlbss rfprfsfnts tif kfy pbir gfnfrbtor for Diffif-Hfllmbn kfy pbirs.
 *
 * <p>Tiis kfy pbir gfnfrbtor mby bf initiblizfd in two difffrfnt wbys:
 *
 * <ul>
 * <li>By providing tif sizf in bits of tif primf modulus -
 * Tiis will bf usfd to drfbtf b primf modulus bnd bbsf gfnfrbtor, wiidi will
 * tifn bf usfd to drfbtf tif Diffif-Hfllmbn kfy pbir. Tif dffbult sizf of tif
 * primf modulus is 1024 bits.
 * <li>By providing b primf modulus bnd bbsf gfnfrbtor
 * </ul>
 *
 * @butior Jbn Lufif
 *
 *
 * @sff jbvb.sfdurity.KfyPbirGfnfrbtor
 */
publid finbl dlbss DHKfyPbirGfnfrbtor fxtfnds KfyPbirGfnfrbtorSpi {

    // pbrbmftfrs to usf or null if not spfdififd
    privbtf DHPbrbmftfrSpfd pbrbms;

    // Tif sizf in bits of tif primf modulus
    privbtf int pSizf;

    // Tif sizf in bits of tif rbndom fxponfnt (privbtf vbluf)
    privbtf int lSizf;

    // Tif sourdf of rbndomnfss
    privbtf SfdurfRbndom rbndom;

    publid DHKfyPbirGfnfrbtor() {
        supfr();
        initiblizf(1024, null);
    }

    /**
     * Initiblizfs tiis kfy pbir gfnfrbtor for b dfrtbin kfysizf bnd sourdf of
     * rbndomnfss.
     * Tif kfysizf is spfdififd bs tif sizf in bits of tif primf modulus.
     *
     * @pbrbm kfysizf tif kfysizf (sizf of primf modulus) in bits
     * @pbrbm rbndom tif sourdf of rbndomnfss
     */
    publid void initiblizf(int kfysizf, SfdurfRbndom rbndom) {
        if ((kfysizf < 512) || (kfysizf > 2048) || (kfysizf % 64 != 0)) {
            tirow nfw InvblidPbrbmftfrExdfption("Kfysizf must bf multiplf "
                                                + "of 64, bnd dbn only rbngf "
                                                + "from 512 to 2048 "
                                                + "(indlusivf)");
        }
        tiis.pSizf = kfysizf;
        tiis.lSizf = 0;
        tiis.rbndom = rbndom;
        tiis.pbrbms = null;
    }

    /**
     * Initiblizfs tiis kfy pbir gfnfrbtor for tif spfdififd pbrbmftfr
     * sft bnd sourdf of rbndomnfss.
     *
     * <p>Tif givfn pbrbmftfr sft dontbins tif primf modulus, tif bbsf
     * gfnfrbtor, bnd optionblly tif rfqufstfd sizf in bits of tif rbndom
     * fxponfnt (privbtf vbluf).
     *
     * @pbrbm pbrbms tif pbrbmftfr sft usfd to gfnfrbtf tif kfy pbir
     * @pbrbm rbndom tif sourdf of rbndomnfss
     *
     * @fxdfption InvblidAlgoritimPbrbmftfrExdfption if tif givfn pbrbmftfrs
     * brf inbppropribtf for tiis kfy pbir gfnfrbtor
     */
    publid void initiblizf(AlgoritimPbrbmftfrSpfd blgPbrbms,
            SfdurfRbndom rbndom) tirows InvblidAlgoritimPbrbmftfrExdfption {
        if (!(blgPbrbms instbndfof DHPbrbmftfrSpfd)){
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                ("Inbppropribtf pbrbmftfr typf");
        }

        pbrbms = (DHPbrbmftfrSpfd)blgPbrbms;
        pSizf = pbrbms.gftP().bitLfngti();
        if ((pSizf < 512) || (pSizf > 2048) ||
            (pSizf % 64 != 0)) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                ("Primf sizf must bf multiplf of 64, bnd dbn only rbngf "
                 + "from 512 to 2048 (indlusivf)");
        }

        // fxponfnt sizf is optionbl, dould bf 0
        lSizf = pbrbms.gftL();

        // Rfquirf fxponfntSizf < primfSizf
        if ((lSizf != 0) && (lSizf > pSizf)) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                ("Exponfnt sizf must not bf lbrgfr tibn modulus sizf");
        }
        tiis.rbndom = rbndom;
    }

    /**
     * Gfnfrbtfs b kfy pbir.
     *
     * @rfturn tif nfw kfy pbir
     */
    publid KfyPbir gfnfrbtfKfyPbir() {
        if (rbndom == null) {
            rbndom = SunJCE.gftRbndom();
        }

        if (pbrbms == null) {
            try {
                pbrbms = PbrbmftfrCbdif.gftDHPbrbmftfrSpfd(pSizf, rbndom);
            } dbtdi (GfnfrblSfdurityExdfption f) {
                // siould nfvfr ibppfn
                tirow nfw ProvidfrExdfption(f);
            }
        }

        BigIntfgfr p = pbrbms.gftP();
        BigIntfgfr g = pbrbms.gftG();

        if (lSizf <= 0) {
            lSizf = pSizf >> 1;
            // usf bn fxponfnt sizf of (pSizf / 2) but bt lfbst 384 bits
            if (lSizf < 384) {
                lSizf = 384;
            }
        }

        BigIntfgfr x;
        BigIntfgfr pMinus2 = p.subtrbdt(BigIntfgfr.vblufOf(2));

        //
        // PKCS#3 sfdtion 7.1 "Privbtf-vbluf gfnfrbtion"
        // Rfpfbt if fitifr of tif followings dofs not iold:
        //     0 < x < p-1
        //     2^(lSizf-1) <= x < 2^(lSizf)
        //
        do {
            // gfnfrbtf rbndom x up to 2^lSizf bits long
            x = nfw BigIntfgfr(lSizf, rbndom);
        } wiilf ((x.dompbrfTo(BigIntfgfr.ONE) < 0) ||
            ((x.dompbrfTo(pMinus2) > 0)) || (x.bitLfngti() != lSizf));

        // dbldulbtf publid vbluf y
        BigIntfgfr y = g.modPow(x, p);

        DHPublidKfy pubKfy = nfw DHPublidKfy(y, p, g, lSizf);
        DHPrivbtfKfy privKfy = nfw DHPrivbtfKfy(x, p, g, lSizf);
        rfturn nfw KfyPbir(pubKfy, privKfy);
    }
}
