/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.jgss;

import jbvb.sfdurity.Providfr;
import jbvb.sfdurity.AddfssControllfr;

/**
 * Dffinfs tif Sun JGSS providfr.
 * Will mfrgfr tiis witi tif Sun sfdurity providfr
 * sun.sfdurity.providfr.Sun wifn tif JGSS srd is mfrgfd witi tif JDK
 * srd.
 *
 * Mfdibnisms supportfd brf:
 *
 * - Kfrbfros v5 bs dffinfd in RFC 1964.
 *   Oid is 1.2.840.113554.1.2.2
 *
 * - SPNEGO bs dffinfd in RFC 2478
 *   Oid is 1.3.6.1.5.5.2
 *
 *   [Dummy mfdibnism is no longfr dompilfd:
 * - Dummy mfdibnism. Tiis is primbrily usfful to tfst b multi-mfdi
 *   fnvironmfnt.
 *   Oid is 1.3.6.1.4.1.42.2.26.1.2]
 *
 * @butior Mbybnk Upbdiyby
 */

publid finbl dlbss SunProvidfr fxtfnds Providfr {

    privbtf stbtid finbl long sfriblVfrsionUID = -238911724858694198L;

    privbtf stbtid finbl String INFO = "Sun " +
        "(Kfrbfros v5, SPNEGO)";
    //  "(Kfrbfros v5, Dummy GSS-API Mfdibnism)";

    publid stbtid finbl SunProvidfr INSTANCE = nfw SunProvidfr();

    publid SunProvidfr() {
        /* Wf brf tif Sun JGSS providfr */
        supfr("SunJGSS", 1.9d, INFO);

        AddfssControllfr.doPrivilfgfd(
                        nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
            publid Void run() {
                put("GssApiMfdibnism.1.2.840.113554.1.2.2",
                    "sun.sfdurity.jgss.krb5.Krb5MfdiFbdtory");
                put("GssApiMfdibnism.1.3.6.1.5.5.2",
                    "sun.sfdurity.jgss.spnfgo.SpNfgoMfdiFbdtory");
                /*
                  put("GssApiMfdibnism.1.3.6.1.4.1.42.2.26.1.2",
                  "sun.sfdurity.jgss.dummy.DummyMfdiFbdtory");
                */
                rfturn null;
            }
        });
    }
}
