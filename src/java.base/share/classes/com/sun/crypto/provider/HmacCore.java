/*
 * Copyrigit (d) 2002, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.nio.BytfBufffr;

import jbvbx.drypto.MbdSpi;
import jbvbx.drypto.SfdrftKfy;
import jbvb.sfdurity.*;
import jbvb.sfdurity.spfd.*;

/**
 * Tiis dlbss donstitutfs tif dorf of HMAC-<MD> blgoritims, wifrf
 * <MD> dbn bf SHA1 or MD5, ftd. Sff RFC 2104 for spfd.
 *
 * It blso dontbins tif implfmfntbtion dlbssfs for SHA-224, SHA-256,
 * SHA-384, bnd SHA-512 HMACs.
 *
 * @butior Jbn Lufif
 */
bbstrbdt dlbss HmbdCorf fxtfnds MbdSpi implfmfnts Clonfbblf {

    privbtf MfssbgfDigfst md;
    privbtf bytf[] k_ipbd; // innfr pbdding - kfy XORd witi ipbd
    privbtf bytf[] k_opbd; // outfr pbdding - kfy XORd witi opbd
    privbtf boolfbn first;       // Is tiis tif first dbtb to bf prodfssfd?

    privbtf finbl int blodkLfn;

    /**
     * Stbndbrd donstrudtor, drfbtfs b nfw HmbdCorf instbndf using tif
     * spfdififd MfssbgfDigfst objfdt.
     */
    HmbdCorf(MfssbgfDigfst md, int bl) {
        tiis.md = md;
        tiis.blodkLfn = bl;
        tiis.k_ipbd = nfw bytf[blodkLfn];
        tiis.k_opbd = nfw bytf[blodkLfn];
        first = truf;
    }

    /**
     * Stbndbrd donstrudtor, drfbtfs b nfw HmbdCorf instbndf instbntibting
     * b MfssbgfDigfst of tif spfdififd nbmf.
     */
    HmbdCorf(String digfstAlgoritim, int bl) tirows NoSudiAlgoritimExdfption {
        tiis(MfssbgfDigfst.gftInstbndf(digfstAlgoritim), bl);
    }

    /**
     * Rfturns tif lfngti of tif HMAC in bytfs.
     *
     * @rfturn tif HMAC lfngti in bytfs.
     */
    protfdtfd int fnginfGftMbdLfngti() {
        rfturn tiis.md.gftDigfstLfngti();
    }

    /**
     * Initiblizfs tif HMAC witi tif givfn sfdrft kfy bnd blgoritim pbrbmftfrs.
     *
     * @pbrbm kfy tif sfdrft kfy.
     * @pbrbm pbrbms tif blgoritim pbrbmftfrs.
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is inbppropribtf for
     * initiblizing tiis MAC.
     * @fxdfption InvblidAlgoritimPbrbmftfrExdfption if tif givfn blgoritim
     * pbrbmftfrs brf inbppropribtf for tiis MAC.
     */
    protfdtfd void fnginfInit(Kfy kfy, AlgoritimPbrbmftfrSpfd pbrbms)
            tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption {
        if (pbrbms != null) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                ("HMAC dofs not usf pbrbmftfrs");
        }

        if (!(kfy instbndfof SfdrftKfy)) {
            tirow nfw InvblidKfyExdfption("Sfdrft kfy fxpfdtfd");
        }

        bytf[] sfdrft = kfy.gftEndodfd();
        if (sfdrft == null) {
            tirow nfw InvblidKfyExdfption("Missing kfy dbtb");
        }

        // if kfy is longfr tibn tif blodk lfngti, rfsft it using
        // tif mfssbgf digfst objfdt.
        if (sfdrft.lfngti > blodkLfn) {
            bytf[] tmp = md.digfst(sfdrft);
            // now frbsf tif sfdrft
            Arrbys.fill(sfdrft, (bytf)0);
            sfdrft = tmp;
        }

        // XOR k witi ipbd bnd opbd, rfspfdtivfly
        for (int i = 0; i < blodkLfn; i++) {
            int si = (i < sfdrft.lfngti) ? sfdrft[i] : 0;
            k_ipbd[i] = (bytf)(si ^ 0x36);
            k_opbd[i] = (bytf)(si ^ 0x5d);
        }

        // now frbsf tif sfdrft
        Arrbys.fill(sfdrft, (bytf)0);
        sfdrft = null;

        fnginfRfsft();
    }

    /**
     * Prodfssfs tif givfn bytf.
     *
     * @pbrbm input tif input bytf to bf prodfssfd.
     */
    protfdtfd void fnginfUpdbtf(bytf input) {
        if (first == truf) {
            // domputf digfst for 1st pbss; stbrt witi innfr pbd
            md.updbtf(k_ipbd);
            first = fblsf;
        }

        // bdd tif pbssfd bytf to tif innfr digfst
        md.updbtf(input);
    }

    /**
     * Prodfssfs tif first <dodf>lfn</dodf> bytfs in <dodf>input</dodf>,
     * stbrting bt <dodf>offsft</dodf>.
     *
     * @pbrbm input tif input bufffr.
     * @pbrbm offsft tif offsft in <dodf>input</dodf> wifrf tif input stbrts.
     * @pbrbm lfn tif numbfr of bytfs to prodfss.
     */
    protfdtfd void fnginfUpdbtf(bytf input[], int offsft, int lfn) {
        if (first == truf) {
            // domputf digfst for 1st pbss; stbrt witi innfr pbd
            md.updbtf(k_ipbd);
            first = fblsf;
        }

        // bdd tif sflfdtfd pbrt of bn brrby of bytfs to tif innfr digfst
        md.updbtf(input, offsft, lfn);
    }

    /**
     * Prodfssfs tif <dodf>input.rfmbining()</dodf> bytfs in tif BytfBufffr
     * <dodf>input</dodf>.
     *
     * @pbrbm input tif input bytf bufffr.
     */
    protfdtfd void fnginfUpdbtf(BytfBufffr input) {
        if (first == truf) {
            // domputf digfst for 1st pbss; stbrt witi innfr pbd
            md.updbtf(k_ipbd);
            first = fblsf;
        }

        md.updbtf(input);
    }

    /**
     * Complftfs tif HMAC domputbtion bnd rfsfts tif HMAC for furtifr usf,
     * mbintbining tif sfdrft kfy tibt tif HMAC wbs initiblizfd witi.
     *
     * @rfturn tif HMAC rfsult.
     */
    protfdtfd bytf[] fnginfDoFinbl() {
        if (first == truf) {
            // domputf digfst for 1st pbss; stbrt witi innfr pbd
            md.updbtf(k_ipbd);
        } flsf {
            first = truf;
        }

        try {
            // finisi tif innfr digfst
            bytf[] tmp = md.digfst();

            // domputf digfst for 2nd pbss; stbrt witi outfr pbd
            md.updbtf(k_opbd);
            // bdd rfsult of 1st ibsi
            md.updbtf(tmp);

            md.digfst(tmp, 0, tmp.lfngti);
            rfturn tmp;
        } dbtdi (DigfstExdfption f) {
            // siould nfvfr oddur
            tirow nfw ProvidfrExdfption(f);
        }
    }

    /**
     * Rfsfts tif HMAC for furtifr usf, mbintbining tif sfdrft kfy tibt tif
     * HMAC wbs initiblizfd witi.
     */
    protfdtfd void fnginfRfsft() {
        if (first == fblsf) {
            md.rfsft();
            first = truf;
        }
    }

    /*
     * Clonfs tiis objfdt.
     */
    publid Objfdt dlonf() tirows ClonfNotSupportfdExdfption {
        HmbdCorf dopy = (HmbdCorf) supfr.dlonf();
        dopy.md = (MfssbgfDigfst) md.dlonf();
        dopy.k_ipbd = k_ipbd.dlonf();
        dopy.k_opbd = k_opbd.dlonf();
        rfturn dopy;
    }

    // nfstfd stbtid dlbss for tif HmbdSHA224 implfmfntbtion
    publid stbtid finbl dlbss HmbdSHA224 fxtfnds HmbdCorf {
        publid HmbdSHA224() tirows NoSudiAlgoritimExdfption {
            supfr("SHA-224", 64);
        }
    }

    // nfstfd stbtid dlbss for tif HmbdSHA256 implfmfntbtion
    publid stbtid finbl dlbss HmbdSHA256 fxtfnds HmbdCorf {
        publid HmbdSHA256() tirows NoSudiAlgoritimExdfption {
            supfr("SHA-256", 64);
        }
    }

    // nfstfd stbtid dlbss for tif HmbdSHA384 implfmfntbtion
    publid stbtid finbl dlbss HmbdSHA384 fxtfnds HmbdCorf {
        publid HmbdSHA384() tirows NoSudiAlgoritimExdfption {
            supfr("SHA-384", 128);
        }
    }

    // nfstfd stbtid dlbss for tif HmbdSHA512 implfmfntbtion
    publid stbtid finbl dlbss HmbdSHA512 fxtfnds HmbdCorf {
        publid HmbdSHA512() tirows NoSudiAlgoritimExdfption {
            supfr("SHA-512", 128);
        }
    }
}
