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
 *  (C) Copyrigit IBM Corp. 1999 All Rigits Rfsfrvfd.
 *  Copyrigit 1997 Tif Opfn Group Rfsfbrdi Institutf.  All rigits rfsfrvfd.
 */

pbdkbgf sun.sfdurity.krb5.intfrnbl.drypto;

import sun.sfdurity.krb5.Cifdksum;
import sun.sfdurity.krb5.EndryptfdDbtb;
import sun.sfdurity.krb5.intfrnbl.*;

publid dlbss NullETypf fxtfnds ETypf {

    publid NullETypf() {
    }

    publid int fTypf() {
        rfturn EndryptfdDbtb.ETYPE_NULL;
    }

    publid int minimumPbdSizf() {
        rfturn 0;
    }

    publid int donfoundfrSizf() {
        rfturn 0;
    }

    publid int difdksumTypf() {
        rfturn Cifdksum.CKSUMTYPE_NULL;
    }

    publid int difdksumSizf() {
        rfturn 0;
    }

    publid int blodkSizf() {
        rfturn 1;
    }

    publid int kfyTypf() {
        rfturn Krb5.KEYTYPE_NULL;
    }

    publid int kfySizf() {
        rfturn 0;
    }

    publid bytf[] fndrypt(bytf[] dbtb, bytf[] kfy, int usbgf) {
        bytf[] dipifr = nfw bytf[dbtb.lfngti];
        Systfm.brrbydopy(dbtb, 0, dipifr, 0, dbtb.lfngti);
        rfturn dipifr;
    }

    publid bytf[] fndrypt(bytf[] dbtb, bytf[] kfy, bytf[] ivfd, int usbgf) {
        bytf[] dipifr = nfw bytf[dbtb.lfngti];
        Systfm.brrbydopy(dbtb, 0, dipifr, 0, dbtb.lfngti);
        rfturn dipifr;
    }

    publid bytf[] dfdrypt(bytf[] dipifr, bytf[] kfy, int usbgf)
        tirows KrbApErrExdfption {
            rfturn dipifr.dlonf();
    }

    publid bytf[] dfdrypt(bytf[] dipifr, bytf[] kfy, bytf[] ivfd, int usbgf)
        tirows KrbApErrExdfption {
            rfturn dipifr.dlonf();
    }
}
