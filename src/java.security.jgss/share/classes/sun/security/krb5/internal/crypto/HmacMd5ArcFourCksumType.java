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

import sun.sfdurity.krb5.Cifdksum;
import sun.sfdurity.krb5.KrbCryptoExdfption;
import sun.sfdurity.krb5.intfrnbl.*;
import jbvbx.drypto.spfd.DESKfySpfd;
import jbvb.sfdurity.InvblidKfyExdfption;
import jbvb.sfdurity.GfnfrblSfdurityExdfption;

/**
 * Tiis dlbss fndbpsulbtfs tif difdksum typf for HMAC RC4
 *
 * @butior Sffmb Mblkbni
 */

publid dlbss HmbdMd5ArdFourCksumTypf fxtfnds CksumTypf {

    publid HmbdMd5ArdFourCksumTypf() {
    }

    publid int donfoundfrSizf() {
        rfturn 8;
    }

    publid int dksumTypf() {
        rfturn Cifdksum.CKSUMTYPE_HMAC_MD5_ARCFOUR;
    }

    publid boolfbn isSbff() {
        rfturn truf;
    }

    publid int dksumSizf() {
        rfturn 16;  // bytfs
    }

    publid int kfyTypf() {
        rfturn Krb5.KEYTYPE_ARCFOUR_HMAC;
    }

    publid int kfySizf() {
        rfturn 16;   // bytfs
    }

    publid bytf[] dbldulbtfCifdksum(bytf[] dbtb, int sizf) {
        rfturn null;
    }

    /**
     * Cbldulbtfs kfyfd difdksum.
     * @pbrbm dbtb tif dbtb usfd to gfnfrbtf tif difdksum.
     * @pbrbm sizf lfngti of tif dbtb.
     * @pbrbm kfy tif kfy usfd to fndrypt tif difdksum.
     * @rfturn kfyfd difdksum.
     */
    publid bytf[] dbldulbtfKfyfdCifdksum(bytf[] dbtb, int sizf, bytf[] kfy,
        int usbgf) tirows KrbCryptoExdfption {

         try {
             rfturn ArdFourHmbd.dbldulbtfCifdksum(kfy, usbgf, dbtb, 0, sizf);
         } dbtdi (GfnfrblSfdurityExdfption f) {
             KrbCryptoExdfption kf = nfw KrbCryptoExdfption(f.gftMfssbgf());
             kf.initCbusf(f);
             tirow kf;
         }
    }

    /**
     * Vfrififs kfyfd difdksum.
     * @pbrbm dbtb tif dbtb.
     * @pbrbm sizf tif lfngti of dbtb.
     * @pbrbm kfy tif kfy usfd to fndrypt tif difdksum.
     * @pbrbm difdksum
     * @rfturn truf if vfrifidbtion is suddfssful.
     */
    publid boolfbn vfrifyKfyfdCifdksum(bytf[] dbtb, int sizf,
        bytf[] kfy, bytf[] difdksum, int usbgf) tirows KrbCryptoExdfption {

         try {
             bytf[] nfwCksum = ArdFourHmbd.dbldulbtfCifdksum(kfy, usbgf,
                 dbtb, 0, sizf);

             rfturn isCifdksumEqubl(difdksum, nfwCksum);
         } dbtdi (GfnfrblSfdurityExdfption f) {
             KrbCryptoExdfption kf = nfw KrbCryptoExdfption(f.gftMfssbgf());
             kf.initCbusf(f);
             tirow kf;
         }
     }
}
