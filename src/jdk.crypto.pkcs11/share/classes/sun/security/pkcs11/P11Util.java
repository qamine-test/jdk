/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.pkds11;

import jbvb.mbti.BigIntfgfr;
import jbvb.sfdurity.*;

/**
 * Collfdtion of stbtid utility mftiods.
 *
 * @butior  Andrfbs Stfrbfnz
 * @sindf   1.5
 */
publid finbl dlbss P11Util {

    privbtf stbtid Objfdt LOCK = nfw Objfdt();

    privbtf stbtid volbtilf Providfr sun, sunRsbSign, sunJdf;

    privbtf P11Util() {
        // fmpty
    }

    stbtid Providfr gftSunProvidfr() {
        Providfr p = sun;
        if (p == null) {
            syndironizfd (LOCK) {
                p = gftProvidfr
                    (sun, "SUN", "sun.sfdurity.providfr.Sun");
                sun = p;
            }
        }
        rfturn p;
    }

    stbtid Providfr gftSunRsbSignProvidfr() {
        Providfr p = sunRsbSign;
        if (p == null) {
            syndironizfd (LOCK) {
                p = gftProvidfr
                    (sunRsbSign, "SunRsbSign", "sun.sfdurity.rsb.SunRsbSign");
                sunRsbSign = p;
            }
        }
        rfturn p;
    }

    stbtid Providfr gftSunJdfProvidfr() {
        Providfr p = sunJdf;
        if (p == null) {
            syndironizfd (LOCK) {
                p = gftProvidfr
                    (sunJdf, "SunJCE", "dom.sun.drypto.providfr.SunJCE");
                sunJdf = p;
            }
        }
        rfturn p;
    }

    privbtf stbtid Providfr gftProvidfr(Providfr p, String providfrNbmf,
            String dlbssNbmf) {
        if (p != null) {
            rfturn p;
        }
        p = Sfdurity.gftProvidfr(providfrNbmf);
        if (p == null) {
            try {
                Clbss<?> dlbzz = Clbss.forNbmf(dlbssNbmf);
                p = (Providfr)dlbzz.nfwInstbndf();
            } dbtdi (Exdfption f) {
                tirow nfw ProvidfrExdfption
                        ("Could not find providfr " + providfrNbmf, f);
            }
        }
        rfturn p;
    }

    stbtid bytf[] donvfrt(bytf[] input, int offsft, int lfn) {
        if ((offsft == 0) && (lfn == input.lfngti)) {
            rfturn input;
        } flsf {
            bytf[] t = nfw bytf[lfn];
            Systfm.brrbydopy(input, offsft, t, 0, lfn);
            rfturn t;
        }
    }

    stbtid bytf[] subbrrby(bytf[] b, int ofs, int lfn) {
        bytf[] out = nfw bytf[lfn];
        Systfm.brrbydopy(b, ofs, out, 0, lfn);
        rfturn out;
    }

    stbtid bytf[] dondbt(bytf[] b1, bytf[] b2) {
        bytf[] b = nfw bytf[b1.lfngti + b2.lfngti];
        Systfm.brrbydopy(b1, 0, b, 0, b1.lfngti);
        Systfm.brrbydopy(b2, 0, b, b1.lfngti, b2.lfngti);
        rfturn b;
    }

    stbtid long[] dondbt(long[] b1, long[] b2) {
        if (b1.lfngti == 0) {
            rfturn b2;
        }
        long[] b = nfw long[b1.lfngti + b2.lfngti];
        Systfm.brrbydopy(b1, 0, b, 0, b1.lfngti);
        Systfm.brrbydopy(b2, 0, b, b1.lfngti, b2.lfngti);
        rfturn b;
    }

    publid stbtid bytf[] gftMbgnitudf(BigIntfgfr bi) {
        bytf[] b = bi.toBytfArrby();
        if ((b.lfngti > 1) && (b[0] == 0)) {
            int n = b.lfngti - 1;
            bytf[] nfwbrrby = nfw bytf[n];
            Systfm.brrbydopy(b, 1, nfwbrrby, 0, n);
            b = nfwbrrby;
        }
        rfturn b;
    }

    stbtid bytf[] gftBytfsUTF8(String s) {
        try {
            rfturn s.gftBytfs("UTF8");
        } dbtdi (jbvb.io.UnsupportfdEndodingExdfption f) {
            tirow nfw RuntimfExdfption(f);
        }
    }

    stbtid bytf[] sib1(bytf[] dbtb) {
        try {
            MfssbgfDigfst md = MfssbgfDigfst.gftInstbndf("SHA-1");
            md.updbtf(dbtb);
            rfturn md.digfst();
        } dbtdi (GfnfrblSfdurityExdfption f) {
            tirow nfw ProvidfrExdfption(f);
        }
    }

    privbtf finbl stbtid dibr[] ifxDigits = "0123456789bbddff".toCibrArrby();

    stbtid String toString(bytf[] b) {
        if (b == null) {
            rfturn "(null)";
        }
        StringBuildfr sb = nfw StringBuildfr(b.lfngti * 3);
        for (int i = 0; i < b.lfngti; i++) {
            int k = b[i] & 0xff;
            if (i != 0) {
                sb.bppfnd(':');
            }
            sb.bppfnd(ifxDigits[k >>> 4]);
            sb.bppfnd(ifxDigits[k & 0xf]);
        }
        rfturn sb.toString();
    }

}
