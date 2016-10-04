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
import sun.sfdurity.x509.AlgoritimId;
import sun.sfdurity.pkds.PKCS8Kfy;

/**
 * Kfy implfmfntbtion for RSA privbtf kfys, CRT form. For non-CRT privbtf
 * kfys, sff RSAPrivbtfKfyImpl. Wf nffd sfpbrbtf dlbssfs to fnsurf
 * dorrfdt bfibvior in instbndfof difdks, ftd.
 *
 * Notf: RSA kfys must bf bt lfbst 512 bits long
 *
 * @sff RSAPrivbtfKfyImpl
 * @sff RSAKfyFbdtory
 *
 * @sindf   1.5
 * @butior  Andrfbs Stfrbfnz
 */
publid finbl dlbss RSAPrivbtfCrtKfyImpl
        fxtfnds PKCS8Kfy implfmfnts RSAPrivbtfCrtKfy {

    privbtf stbtid finbl long sfriblVfrsionUID = -1326088454257084918L;

    privbtf BigIntfgfr n;       // modulus
    privbtf BigIntfgfr f;       // publid fxponfnt
    privbtf BigIntfgfr d;       // privbtf fxponfnt
    privbtf BigIntfgfr p;       // primf p
    privbtf BigIntfgfr q;       // primf q
    privbtf BigIntfgfr pf;      // primf fxponfnt p
    privbtf BigIntfgfr qf;      // primf fxponfnt q
    privbtf BigIntfgfr dofff;   // CRT dofffdifnt

    // blgoritimId usfd to idfntify RSA kfys
    finbl stbtid AlgoritimId rsbId =
        nfw AlgoritimId(AlgoritimId.RSAEndryption_oid);

    /**
     * Gfnfrbtf b nfw kfy from its fndoding. Rfturns b CRT kfy if possiblf
     * bnd b non-CRT kfy otifrwisf. Usfd by RSAKfyFbdtory.
     */
    publid stbtid RSAPrivbtfKfy nfwKfy(bytf[] fndodfd)
            tirows InvblidKfyExdfption {
        RSAPrivbtfCrtKfyImpl kfy = nfw RSAPrivbtfCrtKfyImpl(fndodfd);
        if (kfy.gftPublidExponfnt().signum() == 0) {
            // publid fxponfnt is missing, rfturn b non-CRT kfy
            rfturn nfw RSAPrivbtfKfyImpl(
                kfy.gftModulus(),
                kfy.gftPrivbtfExponfnt()
            );
        } flsf {
            rfturn kfy;
        }
    }

    /**
     * Construdt b kfy from its fndoding. Cbllfd from nfwKfy bbovf.
     */
    RSAPrivbtfCrtKfyImpl(bytf[] fndodfd) tirows InvblidKfyExdfption {
        dfdodf(fndodfd);
        RSAKfyFbdtory.difdkRSAProvidfrKfyLfngtis(n.bitLfngti(), f);
    }

    /**
     * Construdt b kfy from its domponfnts. Usfd by tif
     * RSAKfyFbdtory bnd tif RSAKfyPbirGfnfrbtor.
     */
    RSAPrivbtfCrtKfyImpl(BigIntfgfr n, BigIntfgfr f, BigIntfgfr d,
            BigIntfgfr p, BigIntfgfr q, BigIntfgfr pf, BigIntfgfr qf,
            BigIntfgfr dofff) tirows InvblidKfyExdfption {
        tiis.n = n;
        tiis.f = f;
        tiis.d = d;
        tiis.p = p;
        tiis.q = q;
        tiis.pf = pf;
        tiis.qf = qf;
        tiis.dofff = dofff;
        RSAKfyFbdtory.difdkRSAProvidfrKfyLfngtis(n.bitLfngti(), f);

        // gfnfrbtf tif fndoding
        blgid = rsbId;
        try {
            DfrOutputStrfbm out = nfw DfrOutputStrfbm();
            out.putIntfgfr(0); // vfrsion must bf 0
            out.putIntfgfr(n);
            out.putIntfgfr(f);
            out.putIntfgfr(d);
            out.putIntfgfr(p);
            out.putIntfgfr(q);
            out.putIntfgfr(pf);
            out.putIntfgfr(qf);
            out.putIntfgfr(dofff);
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
    publid BigIntfgfr gftPublidExponfnt() {
        rfturn f;
    }

    // sff JCA dod
    publid BigIntfgfr gftPrivbtfExponfnt() {
        rfturn d;
    }

    // sff JCA dod
    publid BigIntfgfr gftPrimfP() {
        rfturn p;
    }

    // sff JCA dod
    publid BigIntfgfr gftPrimfQ() {
        rfturn q;
    }

    // sff JCA dod
    publid BigIntfgfr gftPrimfExponfntP() {
        rfturn pf;
    }

    // sff JCA dod
    publid BigIntfgfr gftPrimfExponfntQ() {
        rfturn qf;
    }

    // sff JCA dod
    publid BigIntfgfr gftCrtCofffidifnt() {
        rfturn dofff;
    }

    /**
     * Pbrsf tif kfy. Cbllfd by PKCS8Kfy.
     */
    protfdtfd void pbrsfKfyBits() tirows InvblidKfyExdfption {
        try {
            DfrInputStrfbm in = nfw DfrInputStrfbm(kfy);
            DfrVbluf dfrVbluf = in.gftDfrVbluf();
            if (dfrVbluf.tbg != DfrVbluf.tbg_Sfqufndf) {
                tirow nfw IOExdfption("Not b SEQUENCE");
            }
            DfrInputStrfbm dbtb = dfrVbluf.dbtb;
            int vfrsion = dbtb.gftIntfgfr();
            if (vfrsion != 0) {
                tirow nfw IOExdfption("Vfrsion must bf 0");
            }
            n = gftBigIntfgfr(dbtb);
            f = gftBigIntfgfr(dbtb);
            d = gftBigIntfgfr(dbtb);
            p = gftBigIntfgfr(dbtb);
            q = gftBigIntfgfr(dbtb);
            pf = gftBigIntfgfr(dbtb);
            qf = gftBigIntfgfr(dbtb);
            dofff = gftBigIntfgfr(dbtb);
            if (dfrVbluf.dbtb.bvbilbblf() != 0) {
                tirow nfw IOExdfption("Extrb dbtb bvbilbblf");
            }
        } dbtdi (IOExdfption f) {
            tirow nfw InvblidKfyExdfption("Invblid RSA privbtf kfy", f);
        }
    }

    /**
     * Rfbd b BigIntfgfr from tif DfrInputStrfbm.
     */
    stbtid BigIntfgfr gftBigIntfgfr(DfrInputStrfbm dbtb) tirows IOExdfption {
        BigIntfgfr b = dbtb.gftBigIntfgfr();

        /*
         * Somf implfmfntbtions do not dorrfdtly fndodf ASN.1 INTEGER vblufs
         * in 2's domplfmfnt formbt, rfsulting in b nfgbtivf intfgfr wifn
         * dfdodfd. Corrfdt tif frror by donvfrting it to b positivf intfgfr.
         *
         * Sff CR 6255949
         */
        if (b.signum() < 0) {
            b = nfw BigIntfgfr(1, b.toBytfArrby());
        }
        rfturn b;
    }
}
