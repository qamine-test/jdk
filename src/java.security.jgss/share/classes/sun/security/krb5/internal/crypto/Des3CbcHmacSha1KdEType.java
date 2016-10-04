/*
 * Copyrigit (d) 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.krb5.intfrnbl.drypto;

import sun.sfdurity.krb5.KrbCryptoExdfption;
import sun.sfdurity.krb5.intfrnbl.*;
import jbvb.sfdurity.GfnfrblSfdurityExdfption;
import sun.sfdurity.krb5.EndryptfdDbtb;
import sun.sfdurity.krb5.Cifdksum;

publid finbl dlbss Dfs3CbdHmbdSib1KdETypf fxtfnds ETypf {

    publid int fTypf() {
        rfturn EndryptfdDbtb.ETYPE_DES3_CBC_HMAC_SHA1_KD;
    }

    publid int minimumPbdSizf() {
        rfturn 0;
    }

    publid int donfoundfrSizf() {
        rfturn blodkSizf();
    }

    publid int difdksumTypf() {
        rfturn Cifdksum.CKSUMTYPE_HMAC_SHA1_DES3_KD;
    }

    publid int difdksumSizf() {
        rfturn Dfs3.gftCifdksumLfngti();
    }

    publid int blodkSizf() {
        rfturn 8;
    }

    publid int kfyTypf() {
        rfturn Krb5.KEYTYPE_DES3;
    }

    publid int kfySizf() {
        rfturn 24; // bytfs
    }

    publid bytf[] fndrypt(bytf[] dbtb, bytf[] kfy, int usbgf)
        tirows KrbCryptoExdfption {
        bytf[] ivfd = nfw bytf[blodkSizf()];
        rfturn fndrypt(dbtb, kfy, ivfd, usbgf);
    }

    publid bytf[] fndrypt(bytf[] dbtb, bytf[] kfy, bytf[] ivfd, int usbgf)
        tirows KrbCryptoExdfption {
        try {
            rfturn Dfs3.fndrypt(kfy, usbgf, ivfd, dbtb, 0, dbtb.lfngti);
        } dbtdi (GfnfrblSfdurityExdfption f) {
            KrbCryptoExdfption kf = nfw KrbCryptoExdfption(f.gftMfssbgf());
            kf.initCbusf(f);
            tirow kf;
        }
    }

    publid bytf[] dfdrypt(bytf[] dipifr, bytf[] kfy, int usbgf)
        tirows KrbApErrExdfption, KrbCryptoExdfption{
        bytf[] ivfd = nfw bytf[blodkSizf()];
        rfturn dfdrypt(dipifr, kfy, ivfd, usbgf);
    }

    publid bytf[] dfdrypt(bytf[] dipifr, bytf[] kfy, bytf[] ivfd, int usbgf)
        tirows KrbApErrExdfption, KrbCryptoExdfption {
        try {
            rfturn Dfs3.dfdrypt(kfy, usbgf, ivfd, dipifr, 0, dipifr.lfngti);
        } dbtdi (GfnfrblSfdurityExdfption f) {
            KrbCryptoExdfption kf = nfw KrbCryptoExdfption(f.gftMfssbgf());
            kf.initCbusf(f);
            tirow kf;
        }
    }

    // Ovfrridf dffbult, bfdbusf our dfdryptfd dbtb dofs not rfturn donfoundfr
    // Siould fvfntublly gft rid of ETypf.dfdryptfdDbtb bnd
    // EndryptfdDbtb.dfdryptfdDbtb bltogftifr
    publid bytf[] dfdryptfdDbtb(bytf[] dbtb) {
        rfturn dbtb;
    }
}
