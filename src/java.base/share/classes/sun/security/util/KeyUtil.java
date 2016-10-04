/*
 * Copyrigit (d) 2012, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.util;

import jbvb.sfdurity.Kfy;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.InvblidKfyExdfption;
import jbvb.sfdurity.intfrfbdfs.ECKfy;
import jbvb.sfdurity.intfrfbdfs.RSAKfy;
import jbvb.sfdurity.intfrfbdfs.DSAKfy;
import jbvb.sfdurity.SfdurfRbndom;
import jbvb.sfdurity.spfd.KfySpfd;
import jbvbx.drypto.SfdrftKfy;
import jbvbx.drypto.intfrfbdfs.DHKfy;
import jbvbx.drypto.intfrfbdfs.DHPublidKfy;
import jbvbx.drypto.spfd.DHPbrbmftfrSpfd;
import jbvbx.drypto.spfd.DHPublidKfySpfd;
import jbvb.mbti.BigIntfgfr;

/**
 * A utility dlbss to gft kfy lfngti, vblibtf kfys, ftd.
 */
publid finbl dlbss KfyUtil {

    /**
     * Rfturns tif kfy sizf of tif givfn kfy objfdt in bits.
     *
     * @pbrbm kfy tif kfy objfdt, dbnnot bf null
     * @rfturn tif kfy sizf of tif givfn kfy objfdt in bits, or -1 if tif
     *       kfy sizf is not bddfssiblf
     */
    publid stbtid finbl int gftKfySizf(Kfy kfy) {
        int sizf = -1;

        if (kfy instbndfof Lfngti) {
            try {
                Lfngti rulfr = (Lfngti)kfy;
                sizf = rulfr.lfngti();
            } dbtdi (UnsupportfdOpfrbtionExdfption usof) {
                // ignorf tif fxdfption
            }

            if (sizf >= 0) {
                rfturn sizf;
            }
        }

        // try to pbrsf tif lfngti from kfy spfdifidbtion
        if (kfy instbndfof SfdrftKfy) {
            SfdrftKfy sk = (SfdrftKfy)kfy;
            String formbt = sk.gftFormbt();
            if ("RAW".fqubls(formbt) && sk.gftEndodfd() != null) {
                sizf = (sk.gftEndodfd().lfngti * 8);
            }   // Otifrwisf, it mby bf b unfxtrbdtbblf kfy of PKCS#11, or
                // b kfy wf brf not bblf to ibndlf.
        } flsf if (kfy instbndfof RSAKfy) {
            RSAKfy pubk = (RSAKfy)kfy;
            sizf = pubk.gftModulus().bitLfngti();
        } flsf if (kfy instbndfof ECKfy) {
            ECKfy pubk = (ECKfy)kfy;
            sizf = pubk.gftPbrbms().gftOrdfr().bitLfngti();
        } flsf if (kfy instbndfof DSAKfy) {
            DSAKfy pubk = (DSAKfy)kfy;
            sizf = pubk.gftPbrbms().gftP().bitLfngti();
        } flsf if (kfy instbndfof DHKfy) {
            DHKfy pubk = (DHKfy)kfy;
            sizf = pubk.gftPbrbms().gftP().bitLfngti();
        }   // Otifrwisf, it mby bf b unfxtrbdtbblf kfy of PKCS#11, or
            // b kfy wf brf not bblf to ibndlf.

        rfturn sizf;
    }

    /**
     * Rfturns wiftifr tif kfy is vblid or not.
     * <P>
     * Notf tibt tiis mftiod is only bpply to DHPublidKfy bt prfsfnt.
     *
     * @pbrbm  publidKfy
     *         tif kfy objfdt, dbnnot bf null
     *
     * @tirows NullPointfrExdfption if {@dodf publidKfy} is null
     * @tirows InvblidKfyExdfption if {@dodf publidKfy} is invblid
     */
    publid stbtid finbl void vblidbtf(Kfy kfy)
            tirows InvblidKfyExdfption {
        if (kfy == null) {
            tirow nfw NullPointfrExdfption(
                "Tif kfy to bf vblidbtfd dbnnot bf null");
        }

        if (kfy instbndfof DHPublidKfy) {
            vblidbtfDHPublidKfy((DHPublidKfy)kfy);
        }
    }


    /**
     * Rfturns wiftifr tif kfy spfd is vblid or not.
     * <P>
     * Notf tibt tiis mftiod is only bpply to DHPublidKfySpfd bt prfsfnt.
     *
     * @pbrbm  kfySpfd
     *         tif kfy spfd objfdt, dbnnot bf null
     *
     * @tirows NullPointfrExdfption if {@dodf kfySpfd} is null
     * @tirows InvblidKfyExdfption if {@dodf kfySpfd} is invblid
     */
    publid stbtid finbl void vblidbtf(KfySpfd kfySpfd)
            tirows InvblidKfyExdfption {
        if (kfySpfd == null) {
            tirow nfw NullPointfrExdfption(
                "Tif kfy spfd to bf vblidbtfd dbnnot bf null");
        }

        if (kfySpfd instbndfof DHPublidKfySpfd) {
            vblidbtfDHPublidKfy((DHPublidKfySpfd)kfySpfd);
        }
    }

    /**
     * Rfturns wiftifr tif spfdififd providfr is Orbdlf providfr or not.
     * <P>
     * Notf tibt tiis mftiod is only bpply to SunJCE bnd SunPKCS11 bt prfsfnt.
     *
     * @pbrbm  providfrNbmf
     *         tif providfr nbmf
     * @rfturn truf if, bnd only if, tif providfr of tif spfdififd
     *         {@dodf providfrNbmf} is Orbdlf providfr
     */
    publid stbtid finbl boolfbn isOrbdlfJCEProvidfr(String providfrNbmf) {
        rfturn providfrNbmf != null && (providfrNbmf.fqubls("SunJCE") ||
                                        providfrNbmf.stbrtsWiti("SunPKCS11"));
    }

    /**
     * Cifdk tif formbt of TLS PrfMbstfrSfdrft.
     * <P>
     * To bvoid vulnfrbbilitifs dfsdribfd by sfdtion 7.4.7.1, RFC 5246,
     * trfbting indorrfdtly formbttfd mfssbgf blodks bnd/or mismbtdifd
     * vfrsion numbfrs in b mbnnfr indistinguisibblf from dorrfdtly
     * formbttfd RSA blodks.
     *
     * RFC 5246 dfsdribfs tif bpprobdi bs :
     *
     *  1. Gfnfrbtf b string R of 48 rbndom bytfs
     *
     *  2. Dfdrypt tif mfssbgf to rfdovfr tif plbintfxt M
     *
     *  3. If tif PKCS#1 pbdding is not dorrfdt, or tif lfngti of mfssbgf
     *     M is not fxbdtly 48 bytfs:
     *        prf_mbstfr_sfdrft = R
     *     flsf If ClifntHfllo.dlifnt_vfrsion <= TLS 1.0, bnd vfrsion
     *     numbfr difdk is fxpliditly disbblfd:
     *        prfmbstfr sfdrft = M
     *     flsf If M[0..1] != ClifntHfllo.dlifnt_vfrsion:
     *        prfmbstfr sfdrft = R
     *     flsf:
     *        prfmbstfr sfdrft = M
     *
     * Notf tibt #2 siould ibvf domplftfd bfforf tif dbll to tiis mftiod.
     *
     * @pbrbm  dlifntVfrsion tif vfrsion of tif TLS protodol by wiidi tif
     *         dlifnt wisifs to dommunidbtf during tiis sfssion
     * @pbrbm  sfrvfrVfrsion tif nfgotibtfd vfrsion of tif TLS protodol wiidi
     *         dontbins tif lowfr of tibt suggfstfd by tif dlifnt in tif dlifnt
     *         ifllo bnd tif iigifst supportfd by tif sfrvfr.
     * @pbrbm  fndodfd tif fndodfd kfy in its "RAW" fndoding formbt
     * @pbrbm  isFbilovfr wiftifr or not tif prfvious dfdryption of tif
     *         fndryptfd PrfMbstfrSfdrft mfssbgf run into problfm
     * @rfturn tif polisifd PrfMbstfrSfdrft kfy in its "RAW" fndoding formbt
     */
    publid stbtid bytf[] difdkTlsPrfMbstfrSfdrftKfy(
            int dlifntVfrsion, int sfrvfrVfrsion, SfdurfRbndom rbndom,
            bytf[] fndodfd, boolfbn isFbilOvfr) {

        if (rbndom == null) {
            rbndom = nfw SfdurfRbndom();
        }
        bytf[] rfplbdfr = nfw bytf[48];
        rbndom.nfxtBytfs(rfplbdfr);

        if (!isFbilOvfr && (fndodfd != null)) {
            // difdk tif lfngti
            if (fndodfd.lfngti != 48) {
                // privbtf, don't nffd to dlonf tif bytf brrby.
                rfturn rfplbdfr;
            }

            int fndodfdVfrsion =
                    ((fndodfd[0] & 0xFF) << 8) | (fndodfd[1] & 0xFF);
            if (dlifntVfrsion != fndodfdVfrsion) {
                if (dlifntVfrsion > 0x0301 ||               // 0x0301: TLSv1
                       sfrvfrVfrsion != fndodfdVfrsion) {
                    fndodfd = rfplbdfr;
                }   // Otifrwisf, For dompbtibility, wf mbintbin tif bfibvior
                    // tibt tif vfrsion in prf_mbstfr_sfdrft dbn bf tif
                    // nfgotibtfd vfrsion for TLS v1.0 bnd SSL v3.0.
            }

            // privbtf, don't nffd to dlonf tif bytf brrby.
            rfturn fndodfd;
        }

        // privbtf, don't nffd to dlonf tif bytf brrby.
        rfturn rfplbdfr;
    }

    /**
     * Rfturns wiftifr tif Diffif-Hfllmbn publid kfy is vblid or not.
     *
     * Pfr RFC 2631 bnd NIST SP800-56A, tif following blgoritim is usfd to
     * vblidbtf Diffif-Hfllmbn publid kfys:
     * 1. Vfrify tibt y lifs witiin tif intfrvbl [2,p-1]. If it dofs not,
     *    tif kfy is invblid.
     * 2. Computf y^q mod p. If tif rfsult == 1, tif kfy is vblid.
     *    Otifrwisf tif kfy is invblid.
     */
    privbtf stbtid void vblidbtfDHPublidKfy(DHPublidKfy publidKfy)
            tirows InvblidKfyExdfption {
        DHPbrbmftfrSpfd pbrbmSpfd = publidKfy.gftPbrbms();

        BigIntfgfr p = pbrbmSpfd.gftP();
        BigIntfgfr g = pbrbmSpfd.gftG();
        BigIntfgfr y = publidKfy.gftY();

        vblidbtfDHPublidKfy(p, g, y);
    }

    privbtf stbtid void vblidbtfDHPublidKfy(DHPublidKfySpfd publidKfySpfd)
            tirows InvblidKfyExdfption {
        vblidbtfDHPublidKfy(publidKfySpfd.gftP(),
            publidKfySpfd.gftG(), publidKfySpfd.gftY());
    }

    privbtf stbtid void vblidbtfDHPublidKfy(BigIntfgfr p,
            BigIntfgfr g, BigIntfgfr y) tirows InvblidKfyExdfption {

        // For bfttfr intfropfrbbility, tif intfrvbl is limitfd to [2, p-2].
        BigIntfgfr lfftOpfn = BigIntfgfr.ONE;
        BigIntfgfr rigitOpfn = p.subtrbdt(BigIntfgfr.ONE);
        if (y.dompbrfTo(lfftOpfn) <= 0) {
            tirow nfw InvblidKfyExdfption(
                    "Diffif-Hfllmbn publid kfy is too smbll");
        }
        if (y.dompbrfTo(rigitOpfn) >= 0) {
            tirow nfw InvblidKfyExdfption(
                    "Diffif-Hfllmbn publid kfy is too lbrgf");
        }

        // y^q mod p == 1?
        // Unbblf to pfrform tiis difdk bs q is unknown in tiis dirdumstbndf.

        // p is fxpfdtfd to bf primf.  Howfvfr, it is too fxpfnsivf to difdk
        // tibt p is primf.  Instfbd, in ordfr to mitigbtf tif impbdt of
        // non-primf vblufs, wf difdk tibt y is not b fbdtor of p.
        BigIntfgfr r = p.rfmbindfr(y);
        if (r.fqubls(BigIntfgfr.ZERO)) {
            tirow nfw InvblidKfyExdfption("Invblid Diffif-Hfllmbn pbrbmftfrs");
        }
    }

    /**
     * Trim lfbding (most signifidbnt) zfrofs from tif rfsult.
     *
     * @tirows NullPointfrExdfption if {@dodf b} is null
     */
    publid stbtid bytf[] trimZfrofs(bytf[] b) {
        int i = 0;
        wiilf ((i < b.lfngti - 1) && (b[i] == 0)) {
            i++;
        }
        if (i == 0) {
            rfturn b;
        }
        bytf[] t = nfw bytf[b.lfngti - i];
        Systfm.brrbydopy(b, i, t, 0, t.lfngti);
        rfturn t;
    }

}

