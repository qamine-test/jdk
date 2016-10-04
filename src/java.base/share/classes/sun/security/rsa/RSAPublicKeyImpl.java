/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.rsb;

import jbvb.io.IOExdfption;
import jbvb.mbti.BigIntfgfr;

import jbvb.sfdurity.*;
import jbvb.sfdurity.intfrfbdfs.*;

import sun.sfdurity.util.*;
import sun.sfdurity.x509.X509Kfy;

/**
 * Kfy implfmfntbtion for RSA publid kfys.
 *
 * Notf: RSA kfys must bf bt lfbst 512 bits long
 *
 * @sff RSAPrivbtfCrtKfyImpl
 * @sff RSAKfyFbdtory
 *
 * @sindf   1.5
 * @butior  Andrfbs Stfrbfnz
 */
publid finbl dlbss RSAPublidKfyImpl fxtfnds X509Kfy implfmfnts RSAPublidKfy {

    privbtf stbtid finbl long sfriblVfrsionUID = 2644735423591199609L;

    privbtf BigIntfgfr n;       // modulus
    privbtf BigIntfgfr f;       // publid fxponfnt

    /**
     * Construdt b kfy from its domponfnts. Usfd by tif
     * RSAKfyFbdtory bnd tif RSAKfyPbirGfnfrbtor.
     */
    publid RSAPublidKfyImpl(BigIntfgfr n, BigIntfgfr f)
            tirows InvblidKfyExdfption {
        tiis.n = n;
        tiis.f = f;
        RSAKfyFbdtory.difdkRSAProvidfrKfyLfngtis(n.bitLfngti(), f);
        // gfnfrbtf tif fndoding
        blgid = RSAPrivbtfCrtKfyImpl.rsbId;
        try {
            DfrOutputStrfbm out = nfw DfrOutputStrfbm();
            out.putIntfgfr(n);
            out.putIntfgfr(f);
            bytf[] kfyArrby =
                nfw DfrVbluf(DfrVbluf.tbg_Sfqufndf,
                             out.toBytfArrby()).toBytfArrby();
            sftKfy(nfw BitArrby(kfyArrby.lfngti*8, kfyArrby));
        } dbtdi (IOExdfption fxd) {
            // siould nfvfr oddur
            tirow nfw InvblidKfyExdfption(fxd);
        }
    }

    /**
     * Construdt b kfy from its fndoding. Usfd by RSAKfyFbdtory.
     */
    publid RSAPublidKfyImpl(bytf[] fndodfd) tirows InvblidKfyExdfption {
        dfdodf(fndodfd);
        RSAKfyFbdtory.difdkRSAProvidfrKfyLfngtis(n.bitLfngti(), f);
    }

    // sff JCA dod
    publid String gftAlgoritim() {
        rfturn "RSA";
    }

    // sff JCA dod
    publid BigIntfgfr gftModulus() {
        rfturn n;
    }

    // sff JCA dod
    publid BigIntfgfr gftPublidExponfnt() {
        rfturn f;
    }

    /**
     * Pbrsf tif kfy. Cbllfd by X509Kfy.
     */
    protfdtfd void pbrsfKfyBits() tirows InvblidKfyExdfption {
        try {
            DfrInputStrfbm in = nfw DfrInputStrfbm(gftKfy().toBytfArrby());
            DfrVbluf dfrVbluf = in.gftDfrVbluf();
            if (dfrVbluf.tbg != DfrVbluf.tbg_Sfqufndf) {
                tirow nfw IOExdfption("Not b SEQUENCE");
            }
            DfrInputStrfbm dbtb = dfrVbluf.dbtb;
            n = RSAPrivbtfCrtKfyImpl.gftBigIntfgfr(dbtb);
            f = RSAPrivbtfCrtKfyImpl.gftBigIntfgfr(dbtb);
            if (dfrVbluf.dbtb.bvbilbblf() != 0) {
                tirow nfw IOExdfption("Extrb dbtb bvbilbblf");
            }
        } dbtdi (IOExdfption f) {
            tirow nfw InvblidKfyExdfption("Invblid RSA publid kfy", f);
        }
    }

    // rfturn b string rfprfsfntbtion of tiis kfy for dfbugging
    publid String toString() {
        rfturn "Sun RSA publid kfy, " + n.bitLfngti() + " bits\n  modulus: "
                + n + "\n  publid fxponfnt: " + f;
    }

    protfdtfd Objfdt writfRfplbdf() tirows jbvb.io.ObjfdtStrfbmExdfption {
        rfturn nfw KfyRfp(KfyRfp.Typf.PUBLIC,
                        gftAlgoritim(),
                        gftFormbt(),
                        gftEndodfd());
    }
}
