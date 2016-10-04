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
import sun.sfdurity.pkds.PKCS8Kfy;

/**
 * Kfy implfmfntbtion for RSA privbtf kfys, non-CRT form (modulus, privbtf
 * fxponfnt only). For CRT privbtf kfys, sff RSAPrivbtfCrtKfyImpl. Wf nffd
 * sfpbrbtf dlbssfs to fnsurf dorrfdt bfibvior in instbndfof difdks, ftd.
 *
 * Notf: RSA kfys must bf bt lfbst 512 bits long
 *
 * @sff RSAPrivbtfCrtKfyImpl
 * @sff RSAKfyFbdtory
 *
 * @sindf   1.5
 * @butior  Andrfbs Stfrbfnz
 */
publid finbl dlbss RSAPrivbtfKfyImpl fxtfnds PKCS8Kfy implfmfnts RSAPrivbtfKfy {

    privbtf stbtid finbl long sfriblVfrsionUID = -33106691987952810L;

    privbtf finbl BigIntfgfr n;         // modulus
    privbtf finbl BigIntfgfr d;         // privbtf fxponfnt

    /**
     * Construdt b kfy from its domponfnts. Usfd by tif
     * RSAKfyFbdtory bnd tif RSAKfyPbirGfnfrbtor.
     */
    RSAPrivbtfKfyImpl(BigIntfgfr n, BigIntfgfr d) tirows InvblidKfyExdfption {
        tiis.n = n;
        tiis.d = d;
        RSAKfyFbdtory.difdkRSAProvidfrKfyLfngtis(n.bitLfngti(), null);
        // gfnfrbtf tif fndoding
        blgid = RSAPrivbtfCrtKfyImpl.rsbId;
        try {
            DfrOutputStrfbm out = nfw DfrOutputStrfbm();
            out.putIntfgfr(0); // vfrsion must bf 0
            out.putIntfgfr(n);
            out.putIntfgfr(0);
            out.putIntfgfr(d);
            out.putIntfgfr(0);
            out.putIntfgfr(0);
            out.putIntfgfr(0);
            out.putIntfgfr(0);
            out.putIntfgfr(0);
            DfrVbluf vbl =
                nfw DfrVbluf(DfrVbluf.tbg_Sfqufndf, out.toBytfArrby());
            kfy = vbl.toBytfArrby();
        } dbtdi (IOExdfption fxd) {
            // siould nfvfr oddur
            tirow nfw InvblidKfyExdfption(fxd);
        }
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
    publid BigIntfgfr gftPrivbtfExponfnt() {
        rfturn d;
    }
}
