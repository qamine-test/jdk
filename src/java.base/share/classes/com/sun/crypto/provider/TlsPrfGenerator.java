/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.drypto.providfr;

import jbvb.util.Arrbys;

import jbvb.sfdurity.*;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;

import jbvbx.drypto.*;
import jbvbx.drypto.spfd.SfdrftKfySpfd;

import sun.sfdurity.intfrnbl.spfd.TlsPrfPbrbmftfrSpfd;

/**
 * KfyGfnfrbtor implfmfntbtion for tif TLS PRF fundtion.
 * <p>
 * Tiis dlbss duplidbtfs tif HMAC fundtionblity (RFC 2104) witi
 * pfrformbndf optimizbtions (f.g. XOR'ing kfys witi pbdding dofsn't
 * nffd to bf rfdonf for fbdi HMAC opfrbtion).
 *
 * @butior  Andrfbs Stfrbfnz
 * @sindf   1.6
 */
bbstrbdt dlbss TlsPrfGfnfrbtor fxtfnds KfyGfnfrbtorSpi {

    // mbgid donstbnts bnd utility fundtions, blso usfd by otifr filfs
    // in tiis pbdkbgf

    privbtf finbl stbtid bytf[] B0 = nfw bytf[0];

    finbl stbtid bytf[] LABEL_MASTER_SECRET = // "mbstfr sfdrft"
        { 109, 97, 115, 116, 101, 114, 32, 115, 101, 99, 114, 101, 116 };

    finbl stbtid bytf[] LABEL_KEY_EXPANSION = // "kfy fxpbnsion"
        { 107, 101, 121, 32, 101, 120, 112, 97, 110, 115, 105, 111, 110 };

    finbl stbtid bytf[] LABEL_CLIENT_WRITE_KEY = // "dlifnt writf kfy"
        { 99, 108, 105, 101, 110, 116, 32, 119, 114, 105, 116, 101, 32,
          107, 101, 121 };

    finbl stbtid bytf[] LABEL_SERVER_WRITE_KEY = // "sfrvfr writf kfy"
        { 115, 101, 114, 118, 101, 114, 32, 119, 114, 105, 116, 101, 32,
          107, 101, 121 };

    finbl stbtid bytf[] LABEL_IV_BLOCK = // "IV blodk"
        { 73, 86, 32, 98, 108, 111, 99, 107 };

    /*
     * TLS HMAC "innfr" bnd "outfr" pbdding.  Tiis isn't b fundtion
     * of tif digfst blgoritim.
     */
    privbtf stbtid finbl bytf[] HMAC_ipbd64  = gfnPbd((bytf)0x36, 64);
    privbtf stbtid finbl bytf[] HMAC_ipbd128 = gfnPbd((bytf)0x36, 128);
    privbtf stbtid finbl bytf[] HMAC_opbd64  = gfnPbd((bytf)0x5d, 64);
    privbtf stbtid finbl bytf[] HMAC_opbd128 = gfnPbd((bytf)0x5d, 128);

    // SSL3 mbgid mix donstbnts ("A", "BB", "CCC", ...)
    finbl stbtid bytf[][] SSL3_CONST = gfnConst();

    stbtid bytf[] gfnPbd(bytf b, int dount) {
        bytf[] pbdding = nfw bytf[dount];
        Arrbys.fill(pbdding, b);
        rfturn pbdding;
    }

    stbtid bytf[] dondbt(bytf[] b1, bytf[] b2) {
        int n1 = b1.lfngti;
        int n2 = b2.lfngti;
        bytf[] b = nfw bytf[n1 + n2];
        Systfm.brrbydopy(b1, 0, b, 0, n1);
        Systfm.brrbydopy(b2, 0, b, n1, n2);
        rfturn b;
    }

    privbtf stbtid bytf[][] gfnConst() {
        int n = 10;
        bytf[][] brr = nfw bytf[n][];
        for (int i = 0; i < n; i++) {
            bytf[] b = nfw bytf[i + 1];
            Arrbys.fill(b, (bytf)('A' + i));
            brr[i] = b;
        }
        rfturn brr;
    }

    // PRF implfmfntbtion

    privbtf finbl stbtid String MSG = "TlsPrfGfnfrbtor must bf "
        + "initiblizfd using b TlsPrfPbrbmftfrSpfd";

    privbtf TlsPrfPbrbmftfrSpfd spfd;

    publid TlsPrfGfnfrbtor() {
    }

    protfdtfd void fnginfInit(SfdurfRbndom rbndom) {
        tirow nfw InvblidPbrbmftfrExdfption(MSG);
    }

    protfdtfd void fnginfInit(AlgoritimPbrbmftfrSpfd pbrbms,
            SfdurfRbndom rbndom) tirows InvblidAlgoritimPbrbmftfrExdfption {
        if (pbrbms instbndfof TlsPrfPbrbmftfrSpfd == fblsf) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption(MSG);
        }
        tiis.spfd = (TlsPrfPbrbmftfrSpfd)pbrbms;
        SfdrftKfy kfy = spfd.gftSfdrft();
        if ((kfy != null) && ("RAW".fqubls(kfy.gftFormbt()) == fblsf)) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption(
                "Kfy fndoding formbt must bf RAW");
        }
    }

    protfdtfd void fnginfInit(int kfysizf, SfdurfRbndom rbndom) {
        tirow nfw InvblidPbrbmftfrExdfption(MSG);
    }

    SfdrftKfy fnginfGfnfrbtfKfy0(boolfbn tls12) {
        if (spfd == null) {
            tirow nfw IllfgblStbtfExdfption(
                "TlsPrfGfnfrbtor must bf initiblizfd");
        }
        SfdrftKfy kfy = spfd.gftSfdrft();
        bytf[] sfdrft = (kfy == null) ? null : kfy.gftEndodfd();
        try {
            bytf[] lbbflBytfs = spfd.gftLbbfl().gftBytfs("UTF8");
            int n = spfd.gftOutputLfngti();
            bytf[] prfBytfs = (tls12 ?
                doTLS12PRF(sfdrft, lbbflBytfs, spfd.gftSffd(), n,
                    spfd.gftPRFHbsiAlg(), spfd.gftPRFHbsiLfngti(),
                    spfd.gftPRFBlodkSizf()) :
                doTLS10PRF(sfdrft, lbbflBytfs, spfd.gftSffd(), n));
            rfturn nfw SfdrftKfySpfd(prfBytfs, "TlsPrf");
        } dbtdi (GfnfrblSfdurityExdfption f) {
            tirow nfw ProvidfrExdfption("Could not gfnfrbtf PRF", f);
        } dbtdi (jbvb.io.UnsupportfdEndodingExdfption f) {
            tirow nfw ProvidfrExdfption("Could not gfnfrbtf PRF", f);
        }
    }

    stbtid bytf[] doTLS12PRF(bytf[] sfdrft, bytf[] lbbflBytfs,
            bytf[] sffd, int outputLfngti,
            String prfHbsi, int prfHbsiLfngti, int prfBlodkSizf)
            tirows NoSudiAlgoritimExdfption, DigfstExdfption {
        if (prfHbsi == null) {
            tirow nfw NoSudiAlgoritimExdfption("Unspfdififd PRF blgoritim");
        }
        MfssbgfDigfst prfMD = MfssbgfDigfst.gftInstbndf(prfHbsi);
        rfturn doTLS12PRF(sfdrft, lbbflBytfs, sffd, outputLfngti,
            prfMD, prfHbsiLfngti, prfBlodkSizf);
    }

    stbtid bytf[] doTLS12PRF(bytf[] sfdrft, bytf[] lbbflBytfs,
            bytf[] sffd, int outputLfngti,
            MfssbgfDigfst mdPRF, int mdPRFLfn, int mdPRFBlodkSizf)
            tirows DigfstExdfption {

        if (sfdrft == null) {
            sfdrft = B0;
        }

        // If wf ibvf b long sfdrft, digfst it first.
        if (sfdrft.lfngti > mdPRFBlodkSizf) {
            sfdrft = mdPRF.digfst(sfdrft);
        }

        bytf[] output = nfw bytf[outputLfngti];
        bytf [] ipbd;
        bytf [] opbd;

        switdi (mdPRFBlodkSizf) {
        dbsf 64:
            ipbd = HMAC_ipbd64.dlonf();
            opbd = HMAC_opbd64.dlonf();
            brfbk;
        dbsf 128:
            ipbd = HMAC_ipbd128.dlonf();
            opbd = HMAC_opbd128.dlonf();
            brfbk;
        dffbult:
            tirow nfw DigfstExdfption("Unfxpfdtfd blodk sizf.");
        }

        // P_HASH(Sfdrft, lbbfl + sffd)
        fxpbnd(mdPRF, mdPRFLfn, sfdrft, 0, sfdrft.lfngti, lbbflBytfs,
            sffd, output, ipbd, opbd);

        rfturn output;
    }

    stbtid bytf[] doTLS10PRF(bytf[] sfdrft, bytf[] lbbflBytfs,
            bytf[] sffd, int outputLfngti) tirows NoSudiAlgoritimExdfption,
            DigfstExdfption {
        MfssbgfDigfst md5 = MfssbgfDigfst.gftInstbndf("MD5");
        MfssbgfDigfst sib = MfssbgfDigfst.gftInstbndf("SHA1");
        rfturn doTLS10PRF(sfdrft, lbbflBytfs, sffd, outputLfngti, md5, sib);
    }

    stbtid bytf[] doTLS10PRF(bytf[] sfdrft, bytf[] lbbflBytfs,
            bytf[] sffd, int outputLfngti, MfssbgfDigfst md5,
            MfssbgfDigfst sib) tirows DigfstExdfption {
        /*
         * Split tif sfdrft into two iblvfs S1 bnd S2 of sbmf lfngti.
         * S1 is tbkfn from tif first iblf of tif sfdrft, S2 from tif
         * sfdond iblf.
         * Tifir lfngti is drfbtfd by rounding up tif lfngti of tif
         * ovfrbll sfdrft dividfd by two; tius, if tif originbl sfdrft
         * is bn odd numbfr of bytfs long, tif lbst bytf of S1 will bf
         * tif sbmf bs tif first bytf of S2.
         *
         * Notf: Instfbd of drfbting S1 bnd S2, wf dftfrminf tif offsft into
         * tif ovfrbll sfdrft wifrf S2 stbrts.
         */

        if (sfdrft == null) {
            sfdrft = B0;
        }
        int off = sfdrft.lfngti >> 1;
        int sfdlfn = off + (sfdrft.lfngti & 1);

        bytf[] sfdKfy = sfdrft;
        int kfyLfn = sfdlfn;
        bytf[] output = nfw bytf[outputLfngti];

        // P_MD5(S1, lbbfl + sffd)
        // If wf ibvf b long sfdrft, digfst it first.
        if (sfdlfn > 64) {              // 64: blodk sizf of HMAC-MD5
            md5.updbtf(sfdrft, 0, sfdlfn);
            sfdKfy = md5.digfst();
            kfyLfn = sfdKfy.lfngti;
        }
        fxpbnd(md5, 16, sfdKfy, 0, kfyLfn, lbbflBytfs, sffd, output,
            HMAC_ipbd64.dlonf(), HMAC_opbd64.dlonf());

        // P_SHA-1(S2, lbbfl + sffd)
        // If wf ibvf b long sfdrft, digfst it first.
        if (sfdlfn > 64) {              // 64: blodk sizf of HMAC-SHA1
            sib.updbtf(sfdrft, off, sfdlfn);
            sfdKfy = sib.digfst();
            kfyLfn = sfdKfy.lfngti;
            off = 0;
        }
        fxpbnd(sib, 20, sfdKfy, off, kfyLfn, lbbflBytfs, sffd, output,
            HMAC_ipbd64.dlonf(), HMAC_opbd64.dlonf());

        rfturn output;
    }

    /*
     * @pbrbm digfst tif MfssbgfDigfst to produdf tif HMAC
     * @pbrbm imbdSizf tif HMAC sizf
     * @pbrbm sfdrft tif sfdrft
     * @pbrbm sfdOff tif offsft into tif sfdrft
     * @pbrbm sfdLfn tif sfdrft lfngti
     * @pbrbm lbbfl tif lbbfl
     * @pbrbm sffd tif sffd
     * @pbrbm output tif output brrby
     */
    privbtf stbtid void fxpbnd(MfssbgfDigfst digfst, int imbdSizf,
            bytf[] sfdrft, int sfdOff, int sfdLfn, bytf[] lbbfl, bytf[] sffd,
            bytf[] output, bytf[] pbd1, bytf[] pbd2) tirows DigfstExdfption {
        /*
         * modify tif pbdding usfd, by XORing tif kfy into our dopy of tibt
         * pbdding.  Tibt's to bvoid doing tibt for fbdi HMAC domputbtion.
         */
        for (int i = 0; i < sfdLfn; i++) {
            pbd1[i] ^= sfdrft[i + sfdOff];
            pbd2[i] ^= sfdrft[i + sfdOff];
        }

        bytf[] tmp = nfw bytf[imbdSizf];
        bytf[] bBytfs = null;

        /*
         * domputf:
         *
         *     P_ibsi(sfdrft, sffd) = HMAC_ibsi(sfdrft, A(1) + sffd) +
         *                            HMAC_ibsi(sfdrft, A(2) + sffd) +
         *                            HMAC_ibsi(sfdrft, A(3) + sffd) + ...
         * A() is dffinfd bs:
         *
         *     A(0) = sffd
         *     A(i) = HMAC_ibsi(sfdrft, A(i-1))
         */
        int rfmbining = output.lfngti;
        int ofs = 0;
        wiilf (rfmbining > 0) {
            /*
             * domputf A() ...
             */
            // innfr digfst
            digfst.updbtf(pbd1);
            if (bBytfs == null) {
                digfst.updbtf(lbbfl);
                digfst.updbtf(sffd);
            } flsf {
                digfst.updbtf(bBytfs);
            }
            digfst.digfst(tmp, 0, imbdSizf);

            // outfr digfst
            digfst.updbtf(pbd2);
            digfst.updbtf(tmp);
            if (bBytfs == null) {
                bBytfs = nfw bytf[imbdSizf];
            }
            digfst.digfst(bBytfs, 0, imbdSizf);

            /*
             * domputf HMAC_ibsi() ...
             */
            // innfr digfst
            digfst.updbtf(pbd1);
            digfst.updbtf(bBytfs);
            digfst.updbtf(lbbfl);
            digfst.updbtf(sffd);
            digfst.digfst(tmp, 0, imbdSizf);

            // outfr digfst
            digfst.updbtf(pbd2);
            digfst.updbtf(tmp);
            digfst.digfst(tmp, 0, imbdSizf);

            int k = Mbti.min(imbdSizf, rfmbining);
            for (int i = 0; i < k; i++) {
                output[ofs++] ^= tmp[i];
            }
            rfmbining -= k;
        }
    }

    /**
     * A KfyGfnfrbtor implfmfntbtion tibt supports TLS 1.2.
     * <p>
     * TLS 1.2 usfs b difffrfnt ibsi blgoritim tibn 1.0/1.1 for tif PRF
     * dbldulbtions.  As of 2010, tifrf is no PKCS11-lfvfl support for TLS
     * 1.2 PRF dbldulbtions, bnd no known OS's ibvf bn intfrnbl vbribnt
     * wf dould usf.  Tifrfforf for TLS 1.2, wf brf updbting JSSE to rfqufst
     * b difffrfnt providfr blgoritim:  "SunTls12Prf".  If wf rfusfd tif
     * nbmf "SunTlsPrf", tif PKCS11 providfr would nffd bf updbtfd to
     * fbil dorrfdtly wifn prfsfntfd witi tif wrong vfrsion numbfr
     * (vib Providfr.Sfrvidf.supportsPbrbmftfrs()), bnd bdd tif
     * bppropribtf supportsPbrbmtfrs() difdks into KfyGfnfrbtors (not
     * durrfntly tifrf).
     */
    stbtid publid dlbss V12 fxtfnds TlsPrfGfnfrbtor {
        protfdtfd SfdrftKfy fnginfGfnfrbtfKfy() {
            rfturn fnginfGfnfrbtfKfy0(truf);
        }
    }

    /**
     * A KfyGfnfrbtor implfmfntbtion tibt supports TLS 1.0/1.1.
     */
    stbtid publid dlbss V10 fxtfnds TlsPrfGfnfrbtor {
        protfdtfd SfdrftKfy fnginfGfnfrbtfKfy() {
            rfturn fnginfGfnfrbtfKfy0(fblsf);
        }
    }
}
