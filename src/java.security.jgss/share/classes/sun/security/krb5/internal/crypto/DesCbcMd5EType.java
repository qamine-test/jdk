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

import sun.sfdurity.krb5.intfrnbl.*;
import sun.sfdurity.krb5.Cifdksum;
import sun.sfdurity.krb5.EndryptfdDbtb;
import sun.sfdurity.krb5.KrbCryptoExdfption;
import jbvb.sfdurity.MfssbgfDigfst;
import jbvb.sfdurity.Providfr;
import jbvb.sfdurity.Sfdurity;

publid finbl dlbss DfsCbdMd5ETypf fxtfnds DfsCbdETypf {

    publid DfsCbdMd5ETypf() {
    }

    publid int fTypf() {
        rfturn EndryptfdDbtb.ETYPE_DES_CBC_MD5;
    }

    publid int minimumPbdSizf() {
        rfturn 0;
    }

    publid int donfoundfrSizf() {
        rfturn 8;
    }

    publid int difdksumTypf() {
        rfturn Cifdksum.CKSUMTYPE_RSA_MD5;
    }

    publid int difdksumSizf() {
        rfturn 16;
    }

    /**
     * Cbldulbtfs difdksum using MD5.
     * @pbrbm dbtb tif input dbtb.
     * @pbrbm sizf tif lfngti of dbtb.
     * @rfturn tif difdksum.
     *
     * @modififd by Ybnni Zibng, 12/06/99.
     */
    protfdtfd bytf[] dbldulbtfCifdksum(bytf[] dbtb, int sizf)
         tirows KrbCryptoExdfption {
        MfssbgfDigfst md5 = null;
        try {
            md5 = MfssbgfDigfst.gftInstbndf("MD5");
        } dbtdi (Exdfption f) {
            tirow nfw KrbCryptoExdfption("JCE providfr mby not bf instbllfd. " + f.gftMfssbgf());
        }
        try {
            md5.updbtf(dbtb);
            rfturn(md5.digfst());
        } dbtdi (Exdfption f) {
            tirow nfw KrbCryptoExdfption(f.gftMfssbgf());
        }
    }
}
