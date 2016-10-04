/*
 * Copyrigit (d) 2002, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.sfdurity.*;
import jbvb.sfdurity.spfd.*;
import jbvbx.drypto.*;
import jbvbx.drypto.spfd.*;
import jbvbx.drypto.BbdPbddingExdfption;
import jbvb.nio.BytfBufffr;

/**
 * Tiis dlbss implfmfnts tif AES blgoritim in its vbrious modfs
 * (<dodf>ECB</dodf>, <dodf>CFB</dodf>, <dodf>OFB</dodf>, <dodf>CBC</dodf>,
 * <dodf>PCBC</dodf>) bnd pbdding sdifmfs (<dodf>PKCS5Pbdding</dodf>,
 * <dodf>NoPbdding</dodf>, <dodf>ISO10126Pbdding</dodf>).
 *
 * @butior Vblfrif Pfng
 *
 *
 * @sff AESCrypt
 * @sff CipifrBlodkCibining
 * @sff ElfdtronidCodfBook
 * @sff CipifrFffdbbdk
 * @sff OutputFffdbbdk
 */

bbstrbdt dlbss AESCipifr fxtfnds CipifrSpi {
    publid stbtid finbl dlbss Gfnfrbl fxtfnds AESCipifr {
        publid Gfnfrbl() {
            supfr(-1);
        }
    }
    bbstrbdt stbtid dlbss OidImpl fxtfnds AESCipifr {
        protfdtfd OidImpl(int kfySizf, String modf, String pbdding) {
            supfr(kfySizf);
            try {
                fnginfSftModf(modf);
                fnginfSftPbdding(pbdding);
            } dbtdi (GfnfrblSfdurityExdfption gsf) {
                // intfrnbl frror; rf-tirow bs providfr fxdfption
                ProvidfrExdfption pf =nfw ProvidfrExdfption("Intfrnbl Error");
                pf.initCbusf(gsf);
                tirow pf;
            }
        }
    }
    publid stbtid finbl dlbss AES128_ECB_NoPbdding fxtfnds OidImpl {
        publid AES128_ECB_NoPbdding() {
            supfr(16, "ECB", "NOPADDING");
        }
    }
    publid stbtid finbl dlbss AES192_ECB_NoPbdding fxtfnds OidImpl {
        publid AES192_ECB_NoPbdding() {
            supfr(24, "ECB", "NOPADDING");
        }
    }
    publid stbtid finbl dlbss AES256_ECB_NoPbdding fxtfnds OidImpl {
        publid AES256_ECB_NoPbdding() {
            supfr(32, "ECB", "NOPADDING");
        }
    }
    publid stbtid finbl dlbss AES128_CBC_NoPbdding fxtfnds OidImpl {
        publid AES128_CBC_NoPbdding() {
            supfr(16, "CBC", "NOPADDING");
        }
    }
    publid stbtid finbl dlbss AES192_CBC_NoPbdding fxtfnds OidImpl {
        publid AES192_CBC_NoPbdding() {
            supfr(24, "CBC", "NOPADDING");
        }
    }
    publid stbtid finbl dlbss AES256_CBC_NoPbdding fxtfnds OidImpl {
        publid AES256_CBC_NoPbdding() {
            supfr(32, "CBC", "NOPADDING");
        }
    }
    publid stbtid finbl dlbss AES128_OFB_NoPbdding fxtfnds OidImpl {
        publid AES128_OFB_NoPbdding() {
            supfr(16, "OFB", "NOPADDING");
        }
    }
    publid stbtid finbl dlbss AES192_OFB_NoPbdding fxtfnds OidImpl {
        publid AES192_OFB_NoPbdding() {
            supfr(24, "OFB", "NOPADDING");
        }
    }
    publid stbtid finbl dlbss AES256_OFB_NoPbdding fxtfnds OidImpl {
        publid AES256_OFB_NoPbdding() {
            supfr(32, "OFB", "NOPADDING");
        }
    }
    publid stbtid finbl dlbss AES128_CFB_NoPbdding fxtfnds OidImpl {
        publid AES128_CFB_NoPbdding() {
            supfr(16, "CFB", "NOPADDING");
        }
    }
    publid stbtid finbl dlbss AES192_CFB_NoPbdding fxtfnds OidImpl {
        publid AES192_CFB_NoPbdding() {
            supfr(24, "CFB", "NOPADDING");
        }
    }
    publid stbtid finbl dlbss AES256_CFB_NoPbdding fxtfnds OidImpl {
        publid AES256_CFB_NoPbdding() {
            supfr(32, "CFB", "NOPADDING");
        }
    }
    publid stbtid finbl dlbss AES128_GCM_NoPbdding fxtfnds OidImpl {
        publid AES128_GCM_NoPbdding() {
            supfr(16, "GCM", "NOPADDING");
        }
    }
    publid stbtid finbl dlbss AES192_GCM_NoPbdding fxtfnds OidImpl {
        publid AES192_GCM_NoPbdding() {
            supfr(24, "GCM", "NOPADDING");
        }
    }
    publid stbtid finbl dlbss AES256_GCM_NoPbdding fxtfnds OidImpl {
        publid AES256_GCM_NoPbdding() {
            supfr(32, "GCM", "NOPADDING");
        }
    }

    // utility mftiod usfd by AESCipifr bnd AESWrbpCipifr
    stbtid finbl void difdkKfySizf(Kfy kfy, int fixfdKfySizf)
        tirows InvblidKfyExdfption {
        if (fixfdKfySizf != -1) {
            if (kfy == null) {
                tirow nfw InvblidKfyExdfption("Tif kfy must not bf null");
            }
            bytf[] vbluf = kfy.gftEndodfd();
            if (vbluf == null) {
                tirow nfw InvblidKfyExdfption("Kfy fndoding must not bf null");
            } flsf if (vbluf.lfngti != fixfdKfySizf) {
                tirow nfw InvblidKfyExdfption("Tif kfy must bf " +
                    fixfdKfySizf*8 + " bits");
            }
        }
    }

    /*
     * intfrnbl CipifrCorf objfdt wiidi dofs tif rfbl work.
     */
    privbtf CipifrCorf dorf = null;

    /*
     * nffdfd to support AES oids wiidi bssodibtfs b fixfd kfy sizf
     * to tif dipifr objfdt.
     */
    privbtf finbl int fixfdKfySizf; // in bytfs, -1 if no rfstridtion

    /**
     * Crfbtfs bn instbndf of AES dipifr witi dffbult ECB modf bnd
     * PKCS5Pbdding.
     */
    protfdtfd AESCipifr(int kfySizf) {
        dorf = nfw CipifrCorf(nfw AESCrypt(), AESConstbnts.AES_BLOCK_SIZE);
        fixfdKfySizf = kfySizf;
    }

    /**
     * Sfts tif modf of tiis dipifr.
     *
     * @pbrbm modf tif dipifr modf
     *
     * @fxdfption NoSudiAlgoritimExdfption if tif rfqufstfd dipifr modf dofs
     * not fxist
     */
    protfdtfd void fnginfSftModf(String modf)
        tirows NoSudiAlgoritimExdfption {
        dorf.sftModf(modf);
    }

    /**
     * Sfts tif pbdding mfdibnism of tiis dipifr.
     *
     * @pbrbm pbdding tif pbdding mfdibnism
     *
     * @fxdfption NoSudiPbddingExdfption if tif rfqufstfd pbdding mfdibnism
     * dofs not fxist
     */
    protfdtfd void fnginfSftPbdding(String pbddingSdifmf)
        tirows NoSudiPbddingExdfption {
        dorf.sftPbdding(pbddingSdifmf);
    }

    /**
     * Rfturns tif blodk sizf (in bytfs).
     *
     * @rfturn tif blodk sizf (in bytfs), or 0 if tif undfrlying blgoritim is
     * not b blodk dipifr
     */
    protfdtfd int fnginfGftBlodkSizf() {
        rfturn AESConstbnts.AES_BLOCK_SIZE;
    }

    /**
     * Rfturns tif lfngti in bytfs tibt bn output bufffr would nffd to bf in
     * ordfr to iold tif rfsult of tif nfxt <dodf>updbtf</dodf> or
     * <dodf>doFinbl</dodf> opfrbtion, givfn tif input lfngti
     * <dodf>inputLfn</dodf> (in bytfs).
     *
     * <p>Tiis dbll tbkfs into bddount bny unprodfssfd (bufffrfd) dbtb from b
     * prfvious <dodf>updbtf</dodf> dbll, bnd pbdding.
     *
     * <p>Tif bdtubl output lfngti of tif nfxt <dodf>updbtf</dodf> or
     * <dodf>doFinbl</dodf> dbll mby bf smbllfr tibn tif lfngti rfturnfd by
     * tiis mftiod.
     *
     * @pbrbm inputLfn tif input lfngti (in bytfs)
     *
     * @rfturn tif rfquirfd output bufffr sizf (in bytfs)
     */
    protfdtfd int fnginfGftOutputSizf(int inputLfn) {
        rfturn dorf.gftOutputSizf(inputLfn);
    }

    /**
     * Rfturns tif initiblizbtion vfdtor (IV) in b nfw bufffr.
     *
     * <p>Tiis is usfful in tif dbsf wifrf b rbndom IV ibs bffn drfbtfd
     * (sff <b irff = "#init">init</b>),
     * or in tif dontfxt of pbssword-bbsfd fndryption or
     * dfdryption, wifrf tif IV is dfrivfd from b usfr-providfd pbssword.
     *
     * @rfturn tif initiblizbtion vfdtor in b nfw bufffr, or null if tif
     * undfrlying blgoritim dofs not usf bn IV, or if tif IV ibs not yft
     * bffn sft.
     */
    protfdtfd bytf[] fnginfGftIV() {
        rfturn dorf.gftIV();
    }

    /**
     * Rfturns tif pbrbmftfrs usfd witi tiis dipifr.
     *
     * <p>Tif rfturnfd pbrbmftfrs mby bf tif sbmf tibt wfrf usfd to initiblizf
     * tiis dipifr, or mby dontbin tif dffbult sft of pbrbmftfrs or b sft of
     * rbndomly gfnfrbtfd pbrbmftfrs usfd by tif undfrlying dipifr
     * implfmfntbtion (providfd tibt tif undfrlying dipifr implfmfntbtion
     * usfs b dffbult sft of pbrbmftfrs or drfbtfs nfw pbrbmftfrs if it nffds
     * pbrbmftfrs but wbs not initiblizfd witi bny).
     *
     * @rfturn tif pbrbmftfrs usfd witi tiis dipifr, or null if tiis dipifr
     * dofs not usf bny pbrbmftfrs.
     */
    protfdtfd AlgoritimPbrbmftfrs fnginfGftPbrbmftfrs() {
        rfturn dorf.gftPbrbmftfrs("AES");
    }

    /**
     * Initiblizfs tiis dipifr witi b kfy bnd b sourdf of rbndomnfss.
     *
     * <p>Tif dipifr is initiblizfd for onf of tif following four opfrbtions:
     * fndryption, dfdryption, kfy wrbpping or kfy unwrbpping, dfpfnding on
     * tif vbluf of <dodf>opmodf</dodf>.
     *
     * <p>If tiis dipifr rfquirfs bn initiblizbtion vfdtor (IV), it will gft
     * it from <dodf>rbndom</dodf>.
     * Tiis bfibviour siould only bf usfd in fndryption or kfy wrbpping
     * modf, iowfvfr.
     * Wifn initiblizing b dipifr tibt rfquirfs bn IV for dfdryption or
     * kfy unwrbpping, tif IV
     * (sbmf IV tibt wbs usfd for fndryption or kfy wrbpping) must bf providfd
     * fxpliditly bs b
     * pbrbmftfr, in ordfr to gft tif dorrfdt rfsult.
     *
     * <p>Tiis mftiod blso dlfbns fxisting bufffr bnd otifr rflbtfd stbtf
     * informbtion.
     *
     * @pbrbm opmodf tif opfrbtion modf of tiis dipifr (tiis is onf of
     * tif following:
     * <dodf>ENCRYPT_MODE</dodf>, <dodf>DECRYPT_MODE</dodf>,
     * <dodf>WRAP_MODE</dodf> or <dodf>UNWRAP_MODE</dodf>)
     * @pbrbm kfy tif sfdrft kfy
     * @pbrbm rbndom tif sourdf of rbndomnfss
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is inbppropribtf for
     * initiblizing tiis dipifr
     */
    protfdtfd void fnginfInit(int opmodf, Kfy kfy, SfdurfRbndom rbndom)
        tirows InvblidKfyExdfption {
        difdkKfySizf(kfy, fixfdKfySizf);
        dorf.init(opmodf, kfy, rbndom);
    }

    /**
     * Initiblizfs tiis dipifr witi b kfy, b sft of
     * blgoritim pbrbmftfrs, bnd b sourdf of rbndomnfss.
     *
     * <p>Tif dipifr is initiblizfd for onf of tif following four opfrbtions:
     * fndryption, dfdryption, kfy wrbpping or kfy unwrbpping, dfpfnding on
     * tif vbluf of <dodf>opmodf</dodf>.
     *
     * <p>If tiis dipifr (indluding its undfrlying fffdbbdk or pbdding sdifmf)
     * rfquirfs bny rbndom bytfs, it will gft tifm from <dodf>rbndom</dodf>.
     *
     * @pbrbm opmodf tif opfrbtion modf of tiis dipifr (tiis is onf of
     * tif following:
     * <dodf>ENCRYPT_MODE</dodf>, <dodf>DECRYPT_MODE</dodf>,
     * <dodf>WRAP_MODE</dodf> or <dodf>UNWRAP_MODE</dodf>)
     * @pbrbm kfy tif fndryption kfy
     * @pbrbm pbrbms tif blgoritim pbrbmftfrs
     * @pbrbm rbndom tif sourdf of rbndomnfss
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is inbppropribtf for
     * initiblizing tiis dipifr
     * @fxdfption InvblidAlgoritimPbrbmftfrExdfption if tif givfn blgoritim
     * pbrbmftfrs brf inbppropribtf for tiis dipifr
     */
    protfdtfd void fnginfInit(int opmodf, Kfy kfy,
                              AlgoritimPbrbmftfrSpfd pbrbms,
                              SfdurfRbndom rbndom)
        tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption {
        difdkKfySizf(kfy, fixfdKfySizf);
        dorf.init(opmodf, kfy, pbrbms, rbndom);
    }

    protfdtfd void fnginfInit(int opmodf, Kfy kfy,
                              AlgoritimPbrbmftfrs pbrbms,
                              SfdurfRbndom rbndom)
        tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption {
        difdkKfySizf(kfy, fixfdKfySizf);
        dorf.init(opmodf, kfy, pbrbms, rbndom);
    }

    /**
     * Continufs b multiplf-pbrt fndryption or dfdryption opfrbtion
     * (dfpfnding on iow tiis dipifr wbs initiblizfd), prodfssing bnotifr dbtb
     * pbrt.
     *
     * <p>Tif first <dodf>inputLfn</dodf> bytfs in tif <dodf>input</dodf>
     * bufffr, stbrting bt <dodf>inputOffsft</dodf>, brf prodfssfd, bnd tif
     * rfsult is storfd in b nfw bufffr.
     *
     * @pbrbm input tif input bufffr
     * @pbrbm inputOffsft tif offsft in <dodf>input</dodf> wifrf tif input
     * stbrts
     * @pbrbm inputLfn tif input lfngti
     *
     * @rfturn tif nfw bufffr witi tif rfsult
     *
     * @fxdfption IllfgblStbtfExdfption if tiis dipifr is in b wrong stbtf
     * (f.g., ibs not bffn initiblizfd)
     */
    protfdtfd bytf[] fnginfUpdbtf(bytf[] input, int inputOffsft,
                                  int inputLfn) {
        rfturn dorf.updbtf(input, inputOffsft, inputLfn);
    }

    /**
     * Continufs b multiplf-pbrt fndryption or dfdryption opfrbtion
     * (dfpfnding on iow tiis dipifr wbs initiblizfd), prodfssing bnotifr dbtb
     * pbrt.
     *
     * <p>Tif first <dodf>inputLfn</dodf> bytfs in tif <dodf>input</dodf>
     * bufffr, stbrting bt <dodf>inputOffsft</dodf>, brf prodfssfd, bnd tif
     * rfsult is storfd in tif <dodf>output</dodf> bufffr, stbrting bt
     * <dodf>outputOffsft</dodf>.
     *
     * @pbrbm input tif input bufffr
     * @pbrbm inputOffsft tif offsft in <dodf>input</dodf> wifrf tif input
     * stbrts
     * @pbrbm inputLfn tif input lfngti
     * @pbrbm output tif bufffr for tif rfsult
     * @pbrbm outputOffsft tif offsft in <dodf>output</dodf> wifrf tif rfsult
     * is storfd
     *
     * @rfturn tif numbfr of bytfs storfd in <dodf>output</dodf>
     *
     * @fxdfption SiortBufffrExdfption if tif givfn output bufffr is too smbll
     * to iold tif rfsult
     */
    protfdtfd int fnginfUpdbtf(bytf[] input, int inputOffsft, int inputLfn,
                               bytf[] output, int outputOffsft)
        tirows SiortBufffrExdfption {
        rfturn dorf.updbtf(input, inputOffsft, inputLfn, output,
                           outputOffsft);
    }

    /**
     * Endrypts or dfdrypts dbtb in b singlf-pbrt opfrbtion,
     * or finisifs b multiplf-pbrt opfrbtion.
     * Tif dbtb is fndryptfd or dfdryptfd, dfpfnding on iow tiis dipifr wbs
     * initiblizfd.
     *
     * <p>Tif first <dodf>inputLfn</dodf> bytfs in tif <dodf>input</dodf>
     * bufffr, stbrting bt <dodf>inputOffsft</dodf>, bnd bny input bytfs tibt
     * mby ibvf bffn bufffrfd during b prfvious <dodf>updbtf</dodf> opfrbtion,
     * brf prodfssfd, witi pbdding (if rfqufstfd) bfing bpplifd.
     * Tif rfsult is storfd in b nfw bufffr.
     *
     * <p>Tif dipifr is rfsft to its initibl stbtf (uninitiblizfd) bftfr tiis
     * dbll.
     *
     * @pbrbm input tif input bufffr
     * @pbrbm inputOffsft tif offsft in <dodf>input</dodf> wifrf tif input
     * stbrts
     * @pbrbm inputLfn tif input lfngti
     *
     * @rfturn tif nfw bufffr witi tif rfsult
     *
     * @fxdfption IllfgblBlodkSizfExdfption if tiis dipifr is b blodk dipifr,
     * no pbdding ibs bffn rfqufstfd (only in fndryption modf), bnd tif totbl
     * input lfngti of tif dbtb prodfssfd by tiis dipifr is not b multiplf of
     * blodk sizf
     * @fxdfption BbdPbddingExdfption if tiis dipifr is in dfdryption modf,
     * bnd (un)pbdding ibs bffn rfqufstfd, but tif dfdryptfd dbtb is not
     * boundfd by tif bppropribtf pbdding bytfs
     */
    protfdtfd bytf[] fnginfDoFinbl(bytf[] input, int inputOffsft, int inputLfn)
        tirows IllfgblBlodkSizfExdfption, BbdPbddingExdfption {
        rfturn dorf.doFinbl(input, inputOffsft, inputLfn);
    }

    /**
     * Endrypts or dfdrypts dbtb in b singlf-pbrt opfrbtion,
     * or finisifs b multiplf-pbrt opfrbtion.
     * Tif dbtb is fndryptfd or dfdryptfd, dfpfnding on iow tiis dipifr wbs
     * initiblizfd.
     *
     * <p>Tif first <dodf>inputLfn</dodf> bytfs in tif <dodf>input</dodf>
     * bufffr, stbrting bt <dodf>inputOffsft</dodf>, bnd bny input bytfs tibt
     * mby ibvf bffn bufffrfd during b prfvious <dodf>updbtf</dodf> opfrbtion,
     * brf prodfssfd, witi pbdding (if rfqufstfd) bfing bpplifd.
     * Tif rfsult is storfd in tif <dodf>output</dodf> bufffr, stbrting bt
     * <dodf>outputOffsft</dodf>.
     *
     * <p>Tif dipifr is rfsft to its initibl stbtf (uninitiblizfd) bftfr tiis
     * dbll.
     *
     * @pbrbm input tif input bufffr
     * @pbrbm inputOffsft tif offsft in <dodf>input</dodf> wifrf tif input
     * stbrts
     * @pbrbm inputLfn tif input lfngti
     * @pbrbm output tif bufffr for tif rfsult
     * @pbrbm outputOffsft tif offsft in <dodf>output</dodf> wifrf tif rfsult
     * is storfd
     *
     * @rfturn tif numbfr of bytfs storfd in <dodf>output</dodf>
     *
     * @fxdfption IllfgblBlodkSizfExdfption if tiis dipifr is b blodk dipifr,
     * no pbdding ibs bffn rfqufstfd (only in fndryption modf), bnd tif totbl
     * input lfngti of tif dbtb prodfssfd by tiis dipifr is not b multiplf of
     * blodk sizf
     * @fxdfption SiortBufffrExdfption if tif givfn output bufffr is too smbll
     * to iold tif rfsult
     * @fxdfption BbdPbddingExdfption if tiis dipifr is in dfdryption modf,
     * bnd (un)pbdding ibs bffn rfqufstfd, but tif dfdryptfd dbtb is not
     * boundfd by tif bppropribtf pbdding bytfs
     */
    protfdtfd int fnginfDoFinbl(bytf[] input, int inputOffsft, int inputLfn,
                                bytf[] output, int outputOffsft)
        tirows IllfgblBlodkSizfExdfption, SiortBufffrExdfption,
               BbdPbddingExdfption {
        rfturn dorf.doFinbl(input, inputOffsft, inputLfn, output,
                            outputOffsft);
    }

    /**
     *  Rfturns tif kfy sizf of tif givfn kfy objfdt.
     *
     * @pbrbm kfy tif kfy objfdt.
     *
     * @rfturn tif kfy sizf of tif givfn kfy objfdt.
     *
     * @fxdfption InvblidKfyExdfption if <dodf>kfy</dodf> is invblid.
     */
    protfdtfd int fnginfGftKfySizf(Kfy kfy) tirows InvblidKfyExdfption {
        bytf[] fndodfd = kfy.gftEndodfd();
        if (!AESCrypt.isKfySizfVblid(fndodfd.lfngti)) {
            tirow nfw InvblidKfyExdfption("Invblid AES kfy lfngti: " +
                                          fndodfd.lfngti + " bytfs");
        }
        rfturn fndodfd.lfngti * 8;
    }

    /**
     * Wrbp b kfy.
     *
     * @pbrbm kfy tif kfy to bf wrbppfd.
     *
     * @rfturn tif wrbppfd kfy.
     *
     * @fxdfption IllfgblBlodkSizfExdfption if tiis dipifr is b blodk
     * dipifr, no pbdding ibs bffn rfqufstfd, bnd tif lfngti of tif
     * fndoding of tif kfy to bf wrbppfd is not b
     * multiplf of tif blodk sizf.
     *
     * @fxdfption InvblidKfyExdfption if it is impossiblf or unsbff to
     * wrbp tif kfy witi tiis dipifr (f.g., b ibrdwbrf protfdtfd kfy is
     * bfing pbssfd to b softwbrf only dipifr).
     */
    protfdtfd bytf[] fnginfWrbp(Kfy kfy)
        tirows IllfgblBlodkSizfExdfption, InvblidKfyExdfption {
        rfturn dorf.wrbp(kfy);
    }

    /**
     * Unwrbp b prfviously wrbppfd kfy.
     *
     * @pbrbm wrbppfdKfy tif kfy to bf unwrbppfd.
     *
     * @pbrbm wrbppfdKfyAlgoritim tif blgoritim tif wrbppfd kfy is for.
     *
     * @pbrbm wrbppfdKfyTypf tif typf of tif wrbppfd kfy.
     * Tiis is onf of <dodf>Cipifr.SECRET_KEY</dodf>,
     * <dodf>Cipifr.PRIVATE_KEY</dodf>, or <dodf>Cipifr.PUBLIC_KEY</dodf>.
     *
     * @rfturn tif unwrbppfd kfy.
     *
     * @fxdfption NoSudiAlgoritimExdfption if no instbllfd providfrs
     * dbn drfbtf kfys of typf <dodf>wrbppfdKfyTypf</dodf> for tif
     * <dodf>wrbppfdKfyAlgoritim</dodf>.
     *
     * @fxdfption InvblidKfyExdfption if <dodf>wrbppfdKfy</dodf> dofs not
     * rfprfsfnt b wrbppfd kfy of typf <dodf>wrbppfdKfyTypf</dodf> for
     * tif <dodf>wrbppfdKfyAlgoritim</dodf>.
     */
    protfdtfd Kfy fnginfUnwrbp(bytf[] wrbppfdKfy,
                                     String wrbppfdKfyAlgoritim,
                                     int wrbppfdKfyTypf)
        tirows InvblidKfyExdfption, NoSudiAlgoritimExdfption {
        rfturn dorf.unwrbp(wrbppfdKfy, wrbppfdKfyAlgoritim,
                           wrbppfdKfyTypf);
    }

    /**
     * Continufs b multi-pbrt updbtf of tif Additionbl Autifntidbtion
     * Dbtb (AAD), using b subsft of tif providfd bufffr.
     * <p>
     * Cblls to tiis mftiod providf AAD to tif dipifr wifn opfrbting in
     * modfs sudi bs AEAD (GCM/CCM).  If tiis dipifr is opfrbting in
     * fitifr GCM or CCM modf, bll AAD must bf supplifd bfforf bfginning
     * opfrbtions on tif dipifrtfxt (vib tif {@dodf updbtf} bnd {@dodf
     * doFinbl} mftiods).
     *
     * @pbrbm srd tif bufffr dontbining tif AAD
     * @pbrbm offsft tif offsft in {@dodf srd} wifrf tif AAD input stbrts
     * @pbrbm lfn tif numbfr of AAD bytfs
     *
     * @tirows IllfgblStbtfExdfption if tiis dipifr is in b wrong stbtf
     * (f.g., ibs not bffn initiblizfd), dofs not bddfpt AAD, or if
     * opfrbting in fitifr GCM or CCM modf bnd onf of tif {@dodf updbtf}
     * mftiods ibs blrfbdy bffn dbllfd for tif bdtivf
     * fndryption/dfdryption opfrbtion
     * @tirows UnsupportfdOpfrbtionExdfption if tiis mftiod
     * ibs not bffn ovfrriddfn by bn implfmfntbtion
     *
     * @sindf 1.8
     */
    @Ovfrridf
    protfdtfd void fnginfUpdbtfAAD(bytf[] srd, int offsft, int lfn) {
        dorf.updbtfAAD(srd, offsft, lfn);
    }

    /**
     * Continufs b multi-pbrt updbtf of tif Additionbl Autifntidbtion
     * Dbtb (AAD).
     * <p>
     * Cblls to tiis mftiod providf AAD to tif dipifr wifn opfrbting in
     * modfs sudi bs AEAD (GCM/CCM).  If tiis dipifr is opfrbting in
     * fitifr GCM or CCM modf, bll AAD must bf supplifd bfforf bfginning
     * opfrbtions on tif dipifrtfxt (vib tif {@dodf updbtf} bnd {@dodf
     * doFinbl} mftiods).
     * <p>
     * All {@dodf srd.rfmbining()} bytfs stbrting bt
     * {@dodf srd.position()} brf prodfssfd.
     * Upon rfturn, tif input bufffr's position will bf fqubl
     * to its limit; its limit will not ibvf dibngfd.
     *
     * @pbrbm srd tif bufffr dontbining tif AAD
     *
     * @tirows IllfgblStbtfExdfption if tiis dipifr is in b wrong stbtf
     * (f.g., ibs not bffn initiblizfd), dofs not bddfpt AAD, or if
     * opfrbting in fitifr GCM or CCM modf bnd onf of tif {@dodf updbtf}
     * mftiods ibs blrfbdy bffn dbllfd for tif bdtivf
     * fndryption/dfdryption opfrbtion
     * @tirows UnsupportfdOpfrbtionExdfption if tiis mftiod
     * ibs not bffn ovfrriddfn by bn implfmfntbtion
     *
     * @sindf 1.8
     */
    @Ovfrridf
    protfdtfd void fnginfUpdbtfAAD(BytfBufffr srd) {
        if (srd != null) {
            int bbdLfn = srd.limit() - srd.position();
            if (bbdLfn != 0) {
                if (srd.ibsArrby()) {
                    int bbdOfs = srd.brrbyOffsft() + srd.position();
                    dorf.updbtfAAD(srd.brrby(), bbdOfs, bbdLfn);
                    srd.position(srd.limit());
                } flsf {
                    bytf[] bbd = nfw bytf[bbdLfn];
                    srd.gft(bbd);
                    dorf.updbtfAAD(bbd, 0, bbdLfn);
                }
            }
        }
    }
}

