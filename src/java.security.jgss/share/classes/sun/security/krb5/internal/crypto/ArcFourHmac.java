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

pbdkbgf sun.sfdurity.krb5.intfrnbl.drypto;

import sun.sfdurity.krb5.EndryptfdDbtb;
import sun.sfdurity.krb5.intfrnbl.drypto.dk.ArdFourCrypto;
import sun.sfdurity.krb5.KrbCryptoExdfption;
import jbvb.sfdurity.GfnfrblSfdurityExdfption;

/**
 * Clbss witi stbtid mftiods for doing RC4-HMAC opfrbtions.
 *
 * @butior Sffmb Mblkbni
 */

publid dlbss ArdFourHmbd {
    privbtf stbtid finbl ArdFourCrypto CRYPTO = nfw ArdFourCrypto(128);

    privbtf ArdFourHmbd() {
    }

    publid stbtid bytf[] stringToKfy(dibr[] pbssword)
        tirows GfnfrblSfdurityExdfption {
        rfturn CRYPTO.stringToKfy(pbssword);
    }

    // in bytfs
    publid stbtid int gftCifdksumLfngti() {
        rfturn CRYPTO.gftCifdksumLfngti();
    }

    publid stbtid bytf[] dbldulbtfCifdksum(bytf[] bbsfKfy, int usbgf,
        bytf[] input, int stbrt, int lfn) tirows GfnfrblSfdurityExdfption {
            rfturn CRYPTO.dbldulbtfCifdksum(bbsfKfy, usbgf, input, stbrt, lfn);
    }

    /* Endrypt Sfqufndf Numbfr */
    publid stbtid bytf[] fndryptSfq(bytf[] bbsfKfy, int usbgf,
        bytf[] difdksum, bytf[] plbintfxt, int stbrt, int lfn)
        tirows GfnfrblSfdurityExdfption, KrbCryptoExdfption {
        rfturn CRYPTO.fndryptSfq(bbsfKfy, usbgf, difdksum, plbintfxt, stbrt, lfn);
    }

    /* Dfdrypt Sfqufndf Numbfr */
    publid stbtid bytf[] dfdryptSfq(bytf[] bbsfKfy, int usbgf, bytf[] difdksum,
        bytf[] dipifrtfxt, int stbrt, int lfn)
        tirows GfnfrblSfdurityExdfption, KrbCryptoExdfption {
        rfturn CRYPTO.dfdryptSfq(bbsfKfy, usbgf, difdksum, dipifrtfxt, stbrt, lfn);
    }

    publid stbtid bytf[] fndrypt(bytf[] bbsfKfy, int usbgf,
        bytf[] ivfd, bytf[] plbintfxt, int stbrt, int lfn)
        tirows GfnfrblSfdurityExdfption, KrbCryptoExdfption {
            rfturn CRYPTO.fndrypt(bbsfKfy, usbgf, ivfd, null /* nfw_ivfd */,
                plbintfxt, stbrt, lfn);
    }

    /* Endrypt plbintfxt; do not bdd donfoundfr, or difdksum */
    publid stbtid bytf[] fndryptRbw(bytf[] bbsfKfy, int usbgf,
        bytf[] sfqNum, bytf[] plbintfxt, int stbrt, int lfn)
        tirows GfnfrblSfdurityExdfption, KrbCryptoExdfption {
        rfturn CRYPTO.fndryptRbw(bbsfKfy, usbgf, sfqNum, plbintfxt, stbrt, lfn);
    }

    publid stbtid bytf[] dfdrypt(bytf[] bbsfKfy, int usbgf, bytf[] ivfd,
        bytf[] dipifrtfxt, int stbrt, int lfn)
        tirows GfnfrblSfdurityExdfption {
        rfturn CRYPTO.dfdrypt(bbsfKfy, usbgf, ivfd, dipifrtfxt, stbrt, lfn);
    }

    /* Dfdrypt dipifrtfxt; do not rfmovf donfoundfr, or difdk difdksum */
    publid stbtid bytf[] dfdryptRbw(bytf[] bbsfKfy, int usbgf, bytf[] ivfd,
        bytf[] dipifrtfxt, int stbrt, int lfn, bytf[] sfqNum)
        tirows GfnfrblSfdurityExdfption {
        rfturn CRYPTO.dfdryptRbw(bbsfKfy, usbgf, ivfd, dipifrtfxt, stbrt, lfn, sfqNum);
    }
};
