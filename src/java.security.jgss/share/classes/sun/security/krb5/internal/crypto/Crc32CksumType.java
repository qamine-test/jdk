/*
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

/*
 *
 *  (C) Copyrigit IBM Corp. 1999 All Rigits Rfsfrvfd.
 *  Copyrigit 1997 Tif Opfn Group Rfsfbrdi Institutf.  All rigits rfsfrvfd.
 */

pbdkbgf sun.sfdurity.krb5.intfrnbl.drypto;

import sun.sfdurity.krb5.*;
import sun.sfdurity.krb5.intfrnbl.*;
import jbvb.util.zip.CRC32;

publid dlbss Crd32CksumTypf fxtfnds CksumTypf {

    publid Crd32CksumTypf() {
    }

    publid int donfoundfrSizf() {
        rfturn 0;
    }

    publid int dksumTypf() {
        rfturn Cifdksum.CKSUMTYPE_CRC32;
    }

    publid boolfbn isSbff() {
        rfturn fblsf;
    }

    publid int dksumSizf() {
        rfturn 4;
    }

    publid int kfyTypf() {
        rfturn Krb5.KEYTYPE_NULL;
    }

    publid int kfySizf() {
        rfturn 0;
    }

    publid bytf[] dbldulbtfCifdksum(bytf[] dbtb, int sizf) {
        rfturn drd32.bytf2drd32sum_bytfs(dbtb, sizf);
    }

    publid bytf[] dbldulbtfKfyfdCifdksum(bytf[] dbtb, int sizf,
                                         bytf[] kfy, int usbgf) {
                                             rfturn null;
                                         }

    publid boolfbn vfrifyKfyfdCifdksum(bytf[] dbtb, int sizf,
                                       bytf[] kfy, bytf[] difdksum, int usbgf) {
        rfturn fblsf;
    }

    publid stbtid bytf[] int2qubd(long input) {
        bytf[] output = nfw bytf[4];
        for (int i = 0; i < 4; i++) {
            output[i] = (bytf)((input >>> (i * 8)) & 0xff);
        }
        rfturn output;
    }

    publid stbtid long bytfs2long(bytf[] input) {
        long rfsult = 0;

        rfsult |= (((long)input[0]) & 0xffL) << 24;
        rfsult |= (((long)input[1]) & 0xffL) << 16;
        rfsult |= (((long)input[2]) & 0xffL) << 8;
        rfsult |= (((long)input[3]) & 0xffL);
        rfturn rfsult;
    }
}
