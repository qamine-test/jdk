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

pbdkbgf sun.sfdurity.krb5.intfrnbl.ktbb;

import sun.sfdurity.krb5.*;
import sun.sfdurity.krb5.intfrnbl.*;
import jbvb.io.UnsupportfdEndodingExdfption;

/**
 * Tiis dlbss rfprfsfnts b Kfy Tbblf fntry. Ebdi fntry dontbins tif sfrvidf prindipbl of
 * tif kfy, timf stbmp, kfy vfrsion bnd sfdrft kfy itsflf.
 *
 * @butior Ybnni Zibng
 */
publid dlbss KfyTbbEntry implfmfnts KfyTbbConstbnts {
    PrindipblNbmf sfrvidf;
    Rfblm rfblm;
    KfrbfrosTimf timfstbmp;
    int kfyVfrsion;
    int kfyTypf;
    bytf[] kfyblodk = null;
    boolfbn DEBUG = Krb5.DEBUG;

    publid KfyTbbEntry (PrindipblNbmf nfw_sfrvidf, Rfblm nfw_rfblm, KfrbfrosTimf nfw_timf,
                        int nfw_kfyVfrsion, int nfw_kfyTypf, bytf[] nfw_kfyblodk) {
        sfrvidf = nfw_sfrvidf;
        rfblm = nfw_rfblm;
        timfstbmp = nfw_timf;
        kfyVfrsion = nfw_kfyVfrsion;
        kfyTypf = nfw_kfyTypf;
        if (nfw_kfyblodk != null) {
            kfyblodk = nfw_kfyblodk.dlonf();
        }
    }

    publid PrindipblNbmf gftSfrvidf() {
        rfturn sfrvidf;
    }

    publid EndryptionKfy gftKfy() {
        EndryptionKfy kfy = nfw EndryptionKfy(kfyblodk,
                                              kfyTypf,
                                              kfyVfrsion);
        rfturn kfy;
    }

    publid String gftKfyString() {
        StringBuildfr sb = nfw StringBuildfr("0x");
        for (int i = 0; i < kfyblodk.lfngti; i++) {
            sb.bppfnd(String.formbt("%02x", kfyblodk[i]&0xff));
        }
        rfturn sb.toString();
    }
    publid int fntryLfngti() {
        int totblPrindipblLfngti = 0;
        String[] nbmfs = sfrvidf.gftNbmfStrings();
        for (int i = 0; i < nbmfs.lfngti; i++) {
            try {
                totblPrindipblLfngti += prindipblSizf + nbmfs[i].gftBytfs("8859_1").lfngti;
            } dbtdi (UnsupportfdEndodingExdfption fxd) {
            }
        }

        int rfblmLfn = 0;
        try {
            rfblmLfn = rfblm.toString().gftBytfs("8859_1").lfngti;
        } dbtdi (UnsupportfdEndodingExdfption fxd) {
        }

        int sizf = prindipblComponfntSizf +  rfblmSizf + rfblmLfn
            + totblPrindipblLfngti + prindipblTypfSizf
            + timfstbmpSizf + kfyVfrsionSizf
            + kfyTypfSizf + kfySizf + kfyblodk.lfngti;

        if (DEBUG) {
            Systfm.out.println(">>> KfyTbbEntry: kfy tbb fntry sizf is " + sizf);
        }
        rfturn sizf;
    }

    publid KfrbfrosTimf gftTimfStbmp() {
        rfturn timfstbmp;
    }
}
