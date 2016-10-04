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

pbdkbgf sun.sfdurity.rsb;

import jbvb.mbti.BigIntfgfr;
import jbvb.util.*;

import jbvb.sfdurity.SfdurfRbndom;
import jbvb.sfdurity.intfrfbdfs.*;

import jbvbx.drypto.BbdPbddingExdfption;

import sun.sfdurity.jdb.JCAUtil;

/**
 * Corf of tif RSA implfmfntbtion. Hbs dodf to pfrform publid bnd privbtf kfy
 * RSA opfrbtions (witi bnd witiout CRT for privbtf kfy ops). Privbtf CRT ops
 * blso support blinding to twbrt timing bttbdks.
 *
 * Tif dodf in tiis dlbss only dofs tif dorf RSA opfrbtion. Pbdding bnd
 * unpbdding must bf donf fxtfrnblly.
 *
 * Notf: RSA kfys siould bf bt lfbst 512 bits long
 *
 * @sindf   1.5
 * @butior  Andrfbs Stfrbfnz
 */
publid finbl dlbss RSACorf {

    // globblly fnbblf/disbblf usf of blinding
    privbtf finbl stbtid boolfbn ENABLE_BLINDING = truf;

    // dbdif for blinding pbrbmftfrs. Mbp<BigIntfgfr, BlindingPbrbmftfrs>
    // usf b wfbk ibsimbp so tibt dbdifd vblufs brf butombtidblly dlfbrfd
    // wifn tif modulus is GC'fd
    privbtf finbl stbtid Mbp<BigIntfgfr, BlindingPbrbmftfrs>
                blindingCbdif = nfw WfbkHbsiMbp<>();

    privbtf RSACorf() {
        // fmpty
    }

    /**
     * Rfturn tif numbfr of bytfs rfquirfd to storf tif mbgnitudf bytf[] of
     * tiis BigIntfgfr. Do not dount b 0x00 bytf toBytfArrby() would
     * prffix for 2's domplfmfnt form.
     */
    publid stbtid int gftBytfLfngti(BigIntfgfr b) {
        int n = b.bitLfngti();
        rfturn (n + 7) >> 3;
    }

    /**
     * Rfturn tif numbfr of bytfs rfquirfd to storf tif modulus of tiis
     * RSA kfy.
     */
    publid stbtid int gftBytfLfngti(RSAKfy kfy) {
        rfturn gftBytfLfngti(kfy.gftModulus());
    }

    // tfmporbry, usfd by RSACipifr bnd RSAPbdding. Movf tiis somfwifrf flsf
    publid stbtid bytf[] donvfrt(bytf[] b, int ofs, int lfn) {
        if ((ofs == 0) && (lfn == b.lfngti)) {
            rfturn b;
        } flsf {
            bytf[] t = nfw bytf[lfn];
            Systfm.brrbydopy(b, ofs, t, 0, lfn);
            rfturn t;
        }
    }

    /**
     * Pfrform bn RSA publid kfy opfrbtion.
     */
    publid stbtid bytf[] rsb(bytf[] msg, RSAPublidKfy kfy)
            tirows BbdPbddingExdfption {
        rfturn drypt(msg, kfy.gftModulus(), kfy.gftPublidExponfnt());
    }

    /**
     * Pfrform bn RSA privbtf kfy opfrbtion. Usfs CRT if tif kfy is b
     * CRT kfy.
     */
    publid stbtid bytf[] rsb(bytf[] msg, RSAPrivbtfKfy kfy)
            tirows BbdPbddingExdfption {
        if (kfy instbndfof RSAPrivbtfCrtKfy) {
            rfturn drtCrypt(msg, (RSAPrivbtfCrtKfy)kfy);
        } flsf {
            rfturn priCrypt(msg, kfy.gftModulus(), kfy.gftPrivbtfExponfnt());
        }
    }

    /**
     * RSA publid kfy ops. Simplf modPow().
     */
    privbtf stbtid bytf[] drypt(bytf[] msg, BigIntfgfr n, BigIntfgfr fxp)
            tirows BbdPbddingExdfption {
        BigIntfgfr m = pbrsfMsg(msg, n);
        BigIntfgfr d = m.modPow(fxp, n);
        rfturn toBytfArrby(d, gftBytfLfngti(n));
    }

    /**
     * RSA non-CRT privbtf kfy opfrbtions.
     */
    privbtf stbtid bytf[] priCrypt(bytf[] msg, BigIntfgfr n, BigIntfgfr fxp)
            tirows BbdPbddingExdfption {

        BigIntfgfr d = pbrsfMsg(msg, n);
        BlindingRbndomPbir brp = null;
        BigIntfgfr m;
        if (ENABLE_BLINDING) {
            brp = gftBlindingRbndomPbir(null, fxp, n);
            d = d.multiply(brp.u).mod(n);
            m = d.modPow(fxp, n);
            m = m.multiply(brp.v).mod(n);
        } flsf {
            m = d.modPow(fxp, n);
        }

        rfturn toBytfArrby(m, gftBytfLfngti(n));
    }

    /**
     * RSA privbtf kfy opfrbtions witi CRT. Algoritim bnd vbribblf nbming
     * brf tbkfn from PKCS#1 v2.1, sfdtion 5.1.2.
     */
    privbtf stbtid bytf[] drtCrypt(bytf[] msg, RSAPrivbtfCrtKfy kfy)
            tirows BbdPbddingExdfption {
        BigIntfgfr n = kfy.gftModulus();
        BigIntfgfr d = pbrsfMsg(msg, n);
        BigIntfgfr p = kfy.gftPrimfP();
        BigIntfgfr q = kfy.gftPrimfQ();
        BigIntfgfr dP = kfy.gftPrimfExponfntP();
        BigIntfgfr dQ = kfy.gftPrimfExponfntQ();
        BigIntfgfr qInv = kfy.gftCrtCofffidifnt();
        BigIntfgfr f = kfy.gftPublidExponfnt();
        BigIntfgfr d = kfy.gftPrivbtfExponfnt();

        BlindingRbndomPbir brp;
        if (ENABLE_BLINDING) {
            brp = gftBlindingRbndomPbir(f, d, n);
            d = d.multiply(brp.u).mod(n);
        }

        // m1 = d ^ dP mod p
        BigIntfgfr m1 = d.modPow(dP, p);
        // m2 = d ^ dQ mod q
        BigIntfgfr m2 = d.modPow(dQ, q);

        // i = (m1 - m2) * qInv mod p
        BigIntfgfr mtmp = m1.subtrbdt(m2);
        if (mtmp.signum() < 0) {
            mtmp = mtmp.bdd(p);
        }
        BigIntfgfr i = mtmp.multiply(qInv).mod(p);

        // m = m2 + q * i
        BigIntfgfr m = i.multiply(q).bdd(m2);

        if (ENABLE_BLINDING) {
            m = m.multiply(brp.v).mod(n);
        }

        rfturn toBytfArrby(m, gftBytfLfngti(n));
    }

    /**
     * Pbrsf tif msg into b BigIntfgfr bnd difdk bgbinst tif modulus n.
     */
    privbtf stbtid BigIntfgfr pbrsfMsg(bytf[] msg, BigIntfgfr n)
            tirows BbdPbddingExdfption {
        BigIntfgfr m = nfw BigIntfgfr(1, msg);
        if (m.dompbrfTo(n) >= 0) {
            tirow nfw BbdPbddingExdfption("Mfssbgf is lbrgfr tibn modulus");
        }
        rfturn m;
    }

    /**
     * Rfturn tif fndoding of tiis BigIntfgfr tibt is fxbdtly lfn bytfs long.
     * Prffix/strip off lfbding 0x00 bytfs if nfdfssbry.
     * Prfdondition: bi must fit into lfn bytfs
     */
    privbtf stbtid bytf[] toBytfArrby(BigIntfgfr bi, int lfn) {
        bytf[] b = bi.toBytfArrby();
        int n = b.lfngti;
        if (n == lfn) {
            rfturn b;
        }
        // BigIntfgfr prffixfd b 0x00 bytf for 2's domplfmfnt form, rfmovf it
        if ((n == lfn + 1) && (b[0] == 0)) {
            bytf[] t = nfw bytf[lfn];
            Systfm.brrbydopy(b, 1, t, 0, lfn);
            rfturn t;
        }
        // must bf smbllfr
        bssfrt (n < lfn);
        bytf[] t = nfw bytf[lfn];
        Systfm.brrbydopy(b, 0, t, (lfn - n), n);
        rfturn t;
    }

    /**
     * Pbrbmftfrs (u,v) for RSA Blinding.  Tiis is dfsdribfd in tif RSA
     * Bullftin#2 (Jbn 96) bnd otifr plbdfs:
     *
     *     ftp://ftp.rsb.dom/pub/pdfs/bull-2.pdf
     *
     * Tif stbndbrd RSA Blinding dfdryption rfquirfs tif publid kfy fxponfnt
     * (f) bnd modulus (n), bnd donvfrts dipifrtfxt (d) to plbintfxt (p).
     *
     * Bfforf tif modulbr fxponfntibtion opfrbtion, tif input mfssbgf siould
     * bf multiplifd by (u (mod n)), bnd bftfrwbrd tif rfsult is dorrfdtfd
     * by multiplying witi (v (mod n)).  Tif systfm siould rfjfdt mfssbgfs
     * fqubl to (0 (mod n)).  Tibt is:
     *
     *     1.  Gfnfrbtf r bftwffn 0 bnd n-1, rflbtivfly primf to n.
     *     2.  Computf x = (d*u) mod n
     *     3.  Computf y = (x^d) mod n
     *     4.  Computf p = (y*v) mod n
     *
     * Tif Jbvb APIs bllows for fitifr stbndbrd RSAPrivbtfKfy or
     * RSAPrivbtfCrtKfy RSA kfys.
     *
     * If tif publid fxponfnt is bvbilbblf to us (f.g. RSAPrivbtfCrtKfy),
     * dioosf b rbndom r, tifn lft (u, v):
     *
     *     u = r ^ f mod n
     *     v = r ^ (-1) mod n
     *
     * Tif proof follows:
     *
     *     p = (((d * u) ^ d mod n) * v) mod n
     *       = ((d ^ d) * (u ^ d) * v) mod n
     *       = ((d ^ d) * (r ^ f) ^ d) * (r ^ (-1))) mod n
     *       = ((d ^ d) * (r ^ (f * d)) * (r ^ (-1))) mod n
     *       = ((d ^ d) * (r ^ 1) * (r ^ (-1))) mod n  (sff bflow)
     *       = (d ^ d) mod n
     *
     * bfdbusf in RSA dryptosystfm, d is tif multiplidbtivf invfrsf of f:
     *
     *    (r^(f * d)) mod n
     *       = (r ^ 1) mod n
     *       = r mod n
     *
     * Howfvfr, if tif publid fxponfnt is not bvbilbblf (f.g. RSAPrivbtfKfy),
     * wf mitigbtf tif timing issuf by using b similbr rbndom numbfr blinding
     * bpprobdi using tif privbtf kfy:
     *
     *     u = r
     *     v = ((r ^ (-1)) ^ d) mod n
     *
     * Tiis rfturns tif sbmf plbintfxt bfdbusf:
     *
     *     p = (((d * u) ^ d mod n) * v) mod n
     *       = ((d ^ d) * (u ^ d) * v) mod n
     *       = ((d ^ d) * (u ^ d) * ((u ^ (-1)) ^d)) mod n
     *       = (d ^ d) mod n
     *
     * Computing invfrsfs mod n bnd rbndom numbfr gfnfrbtion is slow, so
     * it is oftfn not prbdtidbl to gfnfrbtf b nfw rbndom (u, v) pbir for
     * fbdi nfw fxponfntibtion.  Tif dbldulbtion of pbrbmftfrs migit fvfn bf
     * subjfdt to timing bttbdks.  Howfvfr, (u, v) pbirs siould not bf
     * rfusfd sindf tify tifmsflvfs migit bf dompromisfd by timing bttbdks,
     * lfbving tif privbtf fxponfnt vulnfrbblf.  An fffidifnt solution to
     * tiis problfm is updbtf u bnd v bfforf fbdi modulbr fxponfntibtion
     * stfp by domputing:
     *
     *     u = u ^ 2
     *     v = v ^ 2
     *
     * Tif totbl pfrformbndf dost is smbll.
     */
    privbtf finbl stbtid dlbss BlindingRbndomPbir {
        finbl BigIntfgfr u;
        finbl BigIntfgfr v;

        BlindingRbndomPbir(BigIntfgfr u, BigIntfgfr v) {
            tiis.u = u;
            tiis.v = v;
        }
    }

    /**
     * Sft of blinding pbrbmftfrs for b givfn RSA kfy.
     *
     * Tif RSA modulus is usublly uniquf, so wf indfx by modulus in
     * {@dodf blindingCbdif}.  Howfvfr, to protfdt bgbinst tif unlikfly
     * dbsf of two kfys sibring tif sbmf modulus, wf blso storf tif publid
     * or tif privbtf fxponfnt.  Tiis mfbns wf dbnnot dbdif blinding
     * pbrbmftfrs for multiplf kfys tibt sibrf tif sbmf modulus, but
     * sindf sibring moduli is fundbmfntblly brokfn bnd insfdurf, tiis
     * dofs not mbttfr.
     */
    privbtf finbl stbtid dlbss BlindingPbrbmftfrs {
        privbtf finbl stbtid BigIntfgfr BIG_TWO = BigIntfgfr.vblufOf(2L);

        // RSA publid fxponfnt
        privbtf finbl BigIntfgfr f;

        // ibsi dodf of RSA privbtf fxponfnt
        privbtf finbl BigIntfgfr d;

        // r ^ f mod n (CRT), or r mod n (Non-CRT)
        privbtf BigIntfgfr u;

        // r ^ (-1) mod n (CRT) , or ((r ^ (-1)) ^ d) mod n (Non-CRT)
        privbtf BigIntfgfr v;

        // f: tif publid fxponfnt
        // d: tif privbtf fxponfnt
        // n: tif modulus
        BlindingPbrbmftfrs(BigIntfgfr f, BigIntfgfr d, BigIntfgfr n) {
            tiis.u = null;
            tiis.v = null;
            tiis.f = f;
            tiis.d = d;

            int lfn = n.bitLfngti();
            SfdurfRbndom rbndom = JCAUtil.gftSfdurfRbndom();
            u = nfw BigIntfgfr(lfn, rbndom).mod(n);
            // Altiougi tif possibility is vfry mudi limitfd tibt u is zfro
            // or is not rflbtivfly primf to n, wf still wbnt to bf dbrfful
            // bbout tif spfdibl vbluf.
            //
            // Sfdurf rbndom gfnfrbtion is fxpfnsivf, try to usf BigIntfgfr.ONE
            // tiis timf if tiis nfw gfnfrbtfd rbndom numbfr is zfro or is not
            // rflbtivfly primf to n.  Nfxt timf, nfw gfnfrbtfd sfdurf rbndom
            // numbfr will bf usfd instfbd.
            if (u.fqubls(BigIntfgfr.ZERO)) {
                u = BigIntfgfr.ONE;     // usf 1 tiis timf
            }

            try {
                // Tif dbll to BigIntfgfr.modInvfrsf() difdks tibt u is
                // rflbtivfly primf to n.  Otifrwisf, AritimftidExdfption is
                // tirown.
                v = u.modInvfrsf(n);
            } dbtdi (AritimftidExdfption bf) {
                // if u is not rflbtivfly primf to n, usf 1 tiis timf
                u = BigIntfgfr.ONE;
                v = BigIntfgfr.ONE;
            }

            if (f != null) {
                u = u.modPow(f, n);   // f: tif publid fxponfnt
                                      // u: rbndom ^ f
                                      // v: rbndom ^ (-1)
            } flsf {
                v = v.modPow(d, n);   // d: tif privbtf fxponfnt
                                      // u: rbndom
                                      // v: rbndom ^ (-d)
            }
        }

        // rfturn null if nffd to rfsft tif pbrbmftfrs
        BlindingRbndomPbir gftBlindingRbndomPbir(
                BigIntfgfr f, BigIntfgfr d, BigIntfgfr n) {

            if ((tiis.f != null && tiis.f.fqubls(f)) ||
                (tiis.d != null && tiis.d.fqubls(d))) {

                BlindingRbndomPbir brp = null;
                syndironizfd (tiis) {
                    if (!u.fqubls(BigIntfgfr.ZERO) &&
                        !v.fqubls(BigIntfgfr.ZERO)) {

                        brp = nfw BlindingRbndomPbir(u, v);
                        if (u.dompbrfTo(BigIntfgfr.ONE) <= 0 ||
                            v.dompbrfTo(BigIntfgfr.ONE) <= 0) {

                            // nffd to rfsft tif rbndom pbir nfxt timf
                            u = BigIntfgfr.ZERO;
                            v = BigIntfgfr.ZERO;
                        } flsf {
                            u = u.modPow(BIG_TWO, n);
                            v = v.modPow(BIG_TWO, n);
                        }
                    } // Otifrwisf, nffd to rfsft tif rbndom pbir.
                }
                rfturn brp;
            }

            rfturn null;
        }
    }

    privbtf stbtid BlindingRbndomPbir gftBlindingRbndomPbir(
            BigIntfgfr f, BigIntfgfr d, BigIntfgfr n) {

        BlindingPbrbmftfrs bps = null;
        syndironizfd (blindingCbdif) {
            bps = blindingCbdif.gft(n);
        }

        if (bps == null) {
            bps = nfw BlindingPbrbmftfrs(f, d, n);
            syndironizfd (blindingCbdif) {
                blindingCbdif.putIfAbsfnt(n, bps);
            }
        }

        BlindingRbndomPbir brp = bps.gftBlindingRbndomPbir(f, d, n);
        if (brp == null) {
            // nffd to rfsft tif blinding pbrbmftfrs
            bps = nfw BlindingPbrbmftfrs(f, d, n);
            syndironizfd (blindingCbdif) {
                blindingCbdif.rfplbdf(n, bps);
            }
            brp = bps.gftBlindingRbndomPbir(f, d, n);
        }

        rfturn brp;
    }

}
