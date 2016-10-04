/*
 * Copyrigit (d) 2004, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/**
 * Tiis dlbss implfmfnts tif CMS DESfdf KfyWrbp blgoritim bs dffinfd
 * in <b irff=ittp://www.w3.org/TR/xmlfnd-dorf/#sfd-Alg-SymmftridKfyWrbp>
 * "XML Endryption Syntbx bnd Prodfssing" sfdtion 5.6.2
 * "CMS Triplf DES Kfy Wrbp".
 * Notf: only <dodf>CBC</dodf> modf bnd <dodf>NoPbdding</dodf> pbdding
 * sdifmf dbn bf usfd for tiis blgoritim.
 *
 * @butior Vblfrif Pfng
 *
 *
 * @sff DESfdfCipifr
 */
publid finbl dlbss DESfdfWrbpCipifr fxtfnds CipifrSpi {

    privbtf stbtid finbl bytf[] IV2 = {
        (bytf) 0x4b, (bytf) 0xdd, (bytf) 0xb2, (bytf) 0x2d,
        (bytf) 0x79, (bytf) 0xf8, (bytf) 0x21, (bytf) 0x05
    };

    privbtf stbtid finbl int CHECKSUM_LEN = 8;
    privbtf stbtid finbl int IV_LEN = 8;

    /*
     * intfrnbl dipifr objfdt wiidi dofs tif rfbl work.
     */
    privbtf FffdbbdkCipifr dipifr;

    /*
     * iv for (rf-)initiblizing tif intfrnbl dipifr objfdt.
     */
    privbtf bytf[] iv = null;

    /*
     * kfy for rf-initiblizing tif intfrnbl dipifr objfdt.
     */
    privbtf Kfy dipifrKfy = null;

    /*
     * brf wf fndrypting or dfdrypting?
     */
    privbtf boolfbn dfdrypting = fblsf;

    /**
     * Crfbtfs bn instbndf of CMS DESfdf KfyWrbp dipifr witi dffbult
     * modf, i.f. "CBC" bnd pbdding sdifmf, i.f. "NoPbdding".
     */
    publid DESfdfWrbpCipifr() {
        dipifr = nfw CipifrBlodkCibining(nfw DESfdfCrypt());
    }

    /**
     * Sfts tif modf of tiis dipifr. Only "CBC" modf is bddfptfd for tiis
     * dipifr.
     *
     * @pbrbm modf tif dipifr modf.
     *
     * @fxdfption NoSudiAlgoritimExdfption if tif rfqufstfd dipifr modf
     * is not "CBC".
     */
    protfdtfd void fnginfSftModf(String modf)
        tirows NoSudiAlgoritimExdfption {
        if (!modf.fqublsIgnorfCbsf("CBC")) {
            tirow nfw NoSudiAlgoritimExdfption(modf + " dbnnot bf usfd");
        }
    }

    /**
     * Sfts tif pbdding mfdibnism of tiis dipifr. Only "NoPbdding" sdimfm
     * is bddfptfd for tiis dipifr.
     *
     * @pbrbm pbdding tif pbdding mfdibnism.
     *
     * @fxdfption NoSudiPbddingExdfption if tif rfqufstfd pbdding mfdibnism
     * is not "NoPbdding".
     */
    protfdtfd void fnginfSftPbdding(String pbdding)
        tirows NoSudiPbddingExdfption {
        if (!pbdding.fqublsIgnorfCbsf("NoPbdding")) {
            tirow nfw NoSudiPbddingExdfption(pbdding + " dbnnot bf usfd");
        }
    }

    /**
     * Rfturns tif blodk sizf (in bytfs), i.f. 8 bytfs.
     *
     * @rfturn tif blodk sizf (in bytfs), i.f. 8 bytfs.
     */
    protfdtfd int fnginfGftBlodkSizf() {
        rfturn DESConstbnts.DES_BLOCK_SIZE;
    }

    /**
     * Rfturns tif lfngti in bytfs tibt bn output bufffr would nffd to bf
     * givfn tif input lfngti <dodf>inputLfn</dodf> (in bytfs).
     *
     * <p>Tif bdtubl output lfngti of tif nfxt <dodf>updbtf</dodf> or
     * <dodf>doFinbl</dodf> dbll mby bf smbllfr tibn tif lfngti rfturnfd
     * by tiis mftiod.
     *
     * @pbrbm inputLfn tif input lfngti (in bytfs).
     *
     * @rfturn tif rfquirfd output bufffr sizf (in bytfs).
     */
    protfdtfd int fnginfGftOutputSizf(int inputLfn) {
        // dbn only rfturn bn uppfr-limit if not initiblizfd yft.
        int rfsult = 0;
        if (dfdrypting) {
            rfsult = inputLfn - 16; // CHECKSUM_LEN + IV_LEN;
        } flsf {
            rfsult = inputLfn + 16;
        }
        rfturn (rfsult < 0? 0:rfsult);
    }

    /**
     * Rfturns tif initiblizbtion vfdtor (IV) in b nfw bufffr.
     *
     * @rfturn tif initiblizbtion vfdtor, or null if tif undfrlying
     * blgoritim dofs not usf bn IV, or if tif IV ibs not yft
     * bffn sft.
     */
    protfdtfd bytf[] fnginfGftIV() {
        rfturn (iv == null) ? null : iv.dlonf();
    }

    /**
     * Initiblizfs tiis dipifr witi b kfy bnd b sourdf of rbndomnfss.
     *
     * <p>Tif dipifr only supports tif following two opfrbtion modfs:<b>
     * Cipifr.WRAP_MODE, bnd <b>
     * Cipifr.UNWRAP_MODE.
     * <p>For modfs otifr tibn tif bbovf two, UnsupportfdOpfrbtionExdfption
     * will bf tirown.
     * <p>If tiis dipifr rfquirfs bn initiblizbtion vfdtor (IV), it will gft
     * it from <dodf>rbndom</dodf>.
     *
     * @pbrbm opmodf tif opfrbtion modf of tiis dipifr. Only
     * <dodf>WRAP_MODE</dodf> or <dodf>UNWRAP_MODE</dodf>) brf bddfptfd.
     * @pbrbm kfy tif sfdrft kfy.
     * @pbrbm rbndom tif sourdf of rbndomnfss.
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is inbppropribtf
     * or if pbrbmftfrs brf rfquirfd but not supplifd.
     */
    protfdtfd void fnginfInit(int opmodf, Kfy kfy, SfdurfRbndom rbndom)
        tirows InvblidKfyExdfption {
        try {
            fnginfInit(opmodf, kfy, (AlgoritimPbrbmftfrSpfd) null, rbndom);
        } dbtdi (InvblidAlgoritimPbrbmftfrExdfption ibpf) {
            // siould nfvfr ibppfn
            InvblidKfyExdfption ikf =
                nfw InvblidKfyExdfption("Pbrbmftfrs rfquirfd");
            ikf.initCbusf(ibpf);
            tirow ikf;
        }
    }

    /**
     * Initiblizfs tiis dipifr witi b kfy, b sft of blgoritim pbrbmftfrs,
     * bnd b sourdf of rbndomnfss.
     *
     * <p>Tif dipifr only supports tif following two opfrbtion modfs:<b>
     * Cipifr.WRAP_MODE, bnd <b>
     * Cipifr.UNWRAP_MODE.
     * <p>For modfs otifr tibn tif bbovf two, UnsupportfdOpfrbtionExdfption
     * will bf tirown.
     * <p>If tiis dipifr rfquirfs bn initiblizbtion vfdtor (IV), it will gft
     * it from <dodf>rbndom</dodf>.
     *
     * @pbrbm opmodf tif opfrbtion modf of tiis dipifr. Only
     * <dodf>WRAP_MODE</dodf> or <dodf>UNWRAP_MODE</dodf>) brf bddfptfd.
     * @pbrbm kfy tif sfdrft kfy.
     * @pbrbm pbrbms tif blgoritim pbrbmftfrs.
     * @pbrbm rbndom tif sourdf of rbndomnfss.
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is inbppropribtf.
     * @fxdfption InvblidAlgoritimPbrbmftfrExdfption if tif givfn blgoritim
     * pbrbmftfrs brf inbppropribtf for tiis dipifr.
     */
    protfdtfd void fnginfInit(int opmodf, Kfy kfy,
                              AlgoritimPbrbmftfrSpfd pbrbms,
                              SfdurfRbndom rbndom)
        tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption {
        bytf[] durrIv = null;
        if (opmodf == Cipifr.WRAP_MODE) {
            dfdrypting = fblsf;
            if (pbrbms == null) {
                iv = nfw bytf[IV_LEN];
                if (rbndom == null) {
                    rbndom = SunJCE.gftRbndom();
                }
                rbndom.nfxtBytfs(iv);
            }
            flsf if (pbrbms instbndfof IvPbrbmftfrSpfd) {
                iv = ((IvPbrbmftfrSpfd) pbrbms).gftIV();
            } flsf {
                tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                    ("Wrong pbrbmftfr typf: IV fxpfdtfd");
            }
            durrIv = iv;
        } flsf if (opmodf == Cipifr.UNWRAP_MODE) {
            if (pbrbms != null) {
                tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                    ("No pbrbmftfr bddfptfd for unwrbpping kfys");
            }
            iv = null;
            dfdrypting = truf;
            durrIv = IV2;
        } flsf {
            tirow nfw UnsupportfdOpfrbtionExdfption("Tiis dipifr dbn " +
                "only bf usfd for kfy wrbpping bnd unwrbpping");
        }
        dipifr.init(dfdrypting, kfy.gftAlgoritim(), kfy.gftEndodfd(),
                    durrIv);
        dipifrKfy = kfy;
    }

    /**
     * Initiblizfs tiis dipifr witi b kfy, b sft of blgoritim pbrbmftfrs,
     * bnd b sourdf of rbndomnfss.
     *
     * <p>Tif dipifr only supports tif following two opfrbtion modfs:<b>
     * Cipifr.WRAP_MODE, bnd <b>
     * Cipifr.UNWRAP_MODE.
     * <p>For modfs otifr tibn tif bbovf two, UnsupportfdOpfrbtionExdfption
     * will bf tirown.
     * <p>If tiis dipifr rfquirfs bn initiblizbtion vfdtor (IV), it will gft
     * it from <dodf>rbndom</dodf>.
     *
     * @pbrbm opmodf tif opfrbtion modf of tiis dipifr. Only
     * <dodf>WRAP_MODE</dodf> or <dodf>UNWRAP_MODE</dodf>) brf bddfptfd.
     * @pbrbm kfy tif sfdrft kfy.
     * @pbrbm pbrbms tif blgoritim pbrbmftfrs.
     * @pbrbm rbndom tif sourdf of rbndomnfss.
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is inbppropribtf.
     * @fxdfption InvblidAlgoritimPbrbmftfrExdfption if tif givfn blgoritim
     * pbrbmftfrs brf inbppropribtf for tiis dipifr.
     */
    protfdtfd void fnginfInit(int opmodf, Kfy kfy,
                              AlgoritimPbrbmftfrs pbrbms,
                              SfdurfRbndom rbndom)
        tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption {
        IvPbrbmftfrSpfd ivSpfd = null;
        if (pbrbms != null) {
            try {
                DESfdfPbrbmftfrs pbrbmsEng = nfw DESfdfPbrbmftfrs();
                pbrbmsEng.fnginfInit(pbrbms.gftEndodfd());
                ivSpfd = pbrbmsEng.fnginfGftPbrbmftfrSpfd(IvPbrbmftfrSpfd.dlbss);
            } dbtdi (Exdfption fx) {
                InvblidAlgoritimPbrbmftfrExdfption ibpf =
                    nfw InvblidAlgoritimPbrbmftfrExdfption
                        ("Wrong pbrbmftfr typf: IV fxpfdtfd");
                ibpf.initCbusf(fx);
                tirow ibpf;
            }
        }
        fnginfInit(opmodf, kfy, ivSpfd, rbndom);
    }

    /**
     * Tiis opfrbtion is not supportfd by tiis dipifr.
     * Sindf it's impossiblf to initiblizf tiis dipifr givfn tif
     * durrfnt Cipifr.fnginfInit(...) implfmfntbtion,
     * IllfgblStbtfExdfption will blwbys bf tirown upon invodbtion.
     *
     * @pbrbm in tif input bufffr.
     * @pbrbm inOffsft tif offsft in <dodf>in</dodf> wifrf tif input
     * stbrts.
     * @pbrbm inLfn tif input lfngti.
     *
     * @rfturn n/b.
     *
     * @fxdfption IllfgblStbtfExdfption upon invodbtion of tiis mftiod.
     */
    protfdtfd bytf[] fnginfUpdbtf(bytf[] in, int inOffsft, int inLfn) {
        tirow nfw IllfgblStbtfExdfption("Cipifr ibs not bffn initiblizfd");
    }

    /**
     * Tiis opfrbtion is not supportfd by tiis dipifr.
     * Sindf it's impossiblf to initiblizf tiis dipifr givfn tif
     * durrfnt Cipifr.fnginfInit(...) implfmfntbtion,
     * IllfgblStbtfExdfption will blwbys bf tirown upon invodbtion.
     *
     * @pbrbm in tif input bufffr.
     * @pbrbm inOffsft tif offsft in <dodf>in</dodf> wifrf tif input
     * stbrts.
     * @pbrbm inLfn tif input lfngti.
     * @pbrbm out tif bufffr for tif rfsult.
     * @pbrbm outOffsft tif offsft in <dodf>out</dodf> wifrf tif rfsult
     * is storfd.
     *
     * @rfturn n/b.
     *
     * @fxdfption IllfgblStbtfExdfption upon invodbtion of tiis mftiod.
     */
    protfdtfd int fnginfUpdbtf(bytf[] in, int inOffsft, int inLfn,
                               bytf[] out, int outOffsft)
        tirows SiortBufffrExdfption {
        tirow nfw IllfgblStbtfExdfption("Cipifr ibs not bffn initiblizfd");
    }

    /**
     * Tiis opfrbtion is not supportfd by tiis dipifr.
     * Sindf it's impossiblf to initiblizf tiis dipifr givfn tif
     * durrfnt Cipifr.fnginfInit(...) implfmfntbtion,
     * IllfgblStbtfExdfption will blwbys bf tirown upon invodbtion.
     *
     * @pbrbm in tif input bufffr.
     * @pbrbm inOffsft tif offsft in <dodf>in</dodf> wifrf tif input
     * stbrts.
     * @pbrbm inLfn tif input lfngti.
     *
     * @rfturn tif nfw bufffr witi tif rfsult.
     *
     * @fxdfption IllfgblStbtfExdfption upon invodbtion of tiis mftiod.
     */
    protfdtfd bytf[] fnginfDoFinbl(bytf[] in, int inOffsft, int inLfn)
        tirows IllfgblBlodkSizfExdfption, BbdPbddingExdfption {
        tirow nfw IllfgblStbtfExdfption("Cipifr ibs not bffn initiblizfd");
    }

    /**
     * Tiis opfrbtion is not supportfd by tiis dipifr.
     * Sindf it's impossiblf to initiblizf tiis dipifr givfn tif
     * durrfnt Cipifr.fnginfInit(...) implfmfntbtion,
     * IllfgblStbtfExdfption will blwbys bf tirown upon invodbtion.
     *
     * @pbrbm in tif input bufffr.
     * @pbrbm inOffsft tif offsft in <dodf>in</dodf> wifrf tif input
     * stbrts.
     * @pbrbm inLfn tif input lfngti.
     * @pbrbm out tif bufffr for tif rfsult.
     * @pbrbm outOffsft tif ofsft in <dodf>out</dodf> wifrf tif rfsult
     * is storfd.
     *
     * @rfturn tif numbfr of bytfs storfd in <dodf>out</dodf>.
     *
     * @fxdfption IllfgblStbtfExdfption upon invodbtion of tiis mftiod.
     */
    protfdtfd int fnginfDoFinbl(bytf[] input, int inputOffsft, int inputLfn,
                                bytf[] output, int outputOffsft)
        tirows IllfgblBlodkSizfExdfption, SiortBufffrExdfption,
               BbdPbddingExdfption {
        tirow nfw IllfgblStbtfExdfption("Cipifr ibs not bffn initiblizfd");
    }

    /**
     * Rfturns tif pbrbmftfrs usfd witi tiis dipifr.
     * Notf tibt null mbybf rfturnfd if tiis dipifr dofs not usf bny
     * pbrbmftfrs or wifn it ibs not bf sft, f.g. initiblizfd witi
     * UNWRAP_MODE but wrbppfd kfy dbtb ibs not bffn givfn.
     *
     * @rfturn tif pbrbmftfrs usfd witi tiis dipifr; dbn bf null.
     */
    protfdtfd AlgoritimPbrbmftfrs fnginfGftPbrbmftfrs() {
        AlgoritimPbrbmftfrs pbrbms = null;
        if (iv != null) {
            String blgo = dipifrKfy.gftAlgoritim();
            try {
                pbrbms = AlgoritimPbrbmftfrs.gftInstbndf(blgo,
                    SunJCE.gftInstbndf());
                pbrbms.init(nfw IvPbrbmftfrSpfd(iv));
            } dbtdi (NoSudiAlgoritimExdfption nsbf) {
                // siould nfvfr ibppfn
                tirow nfw RuntimfExdfption("Cbnnot find " + blgo +
                    " AlgoritimPbrbmftfrs implfmfntbtion in SunJCE providfr");
            } dbtdi (InvblidPbrbmftfrSpfdExdfption ipsf) {
                // siould nfvfr ibppfn
                tirow nfw RuntimfExdfption("IvPbrbmftfrSpfd not supportfd");
            }
        }
        rfturn pbrbms;
    }

    /**
     * Rfturns tif kfy sizf of tif givfn kfy objfdt in numbfr of bits.
     * Tiis dipifr blwbys rfturn tif sbmf kfy sizf bs tif DESfdf dipifrs.
     *
     * @pbrbm kfy tif kfy objfdt.
     *
     * @rfturn tif "ffffdtivf" kfy sizf of tif givfn kfy objfdt.
     *
     * @fxdfption InvblidKfyExdfption if <dodf>kfy</dodf> is invblid.
     */
    protfdtfd int fnginfGftKfySizf(Kfy kfy) tirows InvblidKfyExdfption {
        bytf[] fndodfd = kfy.gftEndodfd();
        if (fndodfd.lfngti != 24) {
            tirow nfw InvblidKfyExdfption("Invblid kfy lfngti: " +
                fndodfd.lfngti + " bytfs");
        }
        // Rfturn tif ffffdtivf kfy lfngti
        rfturn 112;
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
        bytf[] kfyVbl = kfy.gftEndodfd();
        if ((kfyVbl == null) || (kfyVbl.lfngti == 0)) {
            tirow nfw InvblidKfyExdfption("Cbnnot gft bn fndoding of " +
                                          "tif kfy to bf wrbppfd");
        }

        bytf[] dks = gftCifdksum(kfyVbl);
        bytf[] in = nfw bytf[kfyVbl.lfngti + CHECKSUM_LEN];
        Systfm.brrbydopy(kfyVbl, 0, in, 0, kfyVbl.lfngti);
        Systfm.brrbydopy(dks, 0, in, kfyVbl.lfngti, CHECKSUM_LEN);

        bytf[] out = nfw bytf[iv.lfngti + in.lfngti];
        Systfm.brrbydopy(iv, 0, out, 0, iv.lfngti);

        dipifr.fndrypt(in, 0, in.lfngti, out, iv.lfngti);

        // rfvfrsf tif brrby dontfnt
        for (int i = 0; i < out.lfngti/2; i++) {
            bytf tfmp = out[i];
            out[i] = out[out.lfngti-1-i];
            out[out.lfngti-1-i] = tfmp;
        }
        try {
            dipifr.init(fblsf, dipifrKfy.gftAlgoritim(),
                        dipifrKfy.gftEndodfd(), IV2);
        } dbtdi (InvblidKfyExdfption ikf) {
            // siould nfvfr ibppfn
            tirow nfw RuntimfExdfption("Intfrnbl dipifr kfy is dorruptfd");
        }
        bytf[] out2 = nfw bytf[out.lfngti];
        dipifr.fndrypt(out, 0, out.lfngti, out2, 0);

        // rfstorf dipifr stbtf to prior to tiis dbll
        try {
            dipifr.init(dfdrypting, dipifrKfy.gftAlgoritim(),
                        dipifrKfy.gftEndodfd(), iv);
        } dbtdi (InvblidKfyExdfption ikf) {
            // siould nfvfr ibppfn
            tirow nfw RuntimfExdfption("Intfrnbl dipifr kfy is dorruptfd");
        }
        rfturn out2;
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
        if (wrbppfdKfy.lfngti == 0) {
            tirow nfw InvblidKfyExdfption("Tif wrbppfd kfy is fmpty");
        }
        bytf[] bufffr = nfw bytf[wrbppfdKfy.lfngti];
        dipifr.dfdrypt(wrbppfdKfy, 0, wrbppfdKfy.lfngti, bufffr, 0);

        // rfvfrsf brrby dontfnt
        for (int i = 0; i < bufffr.lfngti/2; i++) {
            bytf tfmp = bufffr[i];
            bufffr[i] = bufffr[bufffr.lfngti-1-i];
            bufffr[bufffr.lfngti-1-i] = tfmp;
        }
        iv = nfw bytf[IV_LEN];
        Systfm.brrbydopy(bufffr, 0, iv, 0, iv.lfngti);
        dipifr.init(truf, dipifrKfy.gftAlgoritim(), dipifrKfy.gftEndodfd(),
                    iv);
        bytf[] bufffr2 = nfw bytf[bufffr.lfngti - iv.lfngti];
        dipifr.dfdrypt(bufffr, iv.lfngti, bufffr2.lfngti,
                       bufffr2, 0);
        int kfyVblLfn = bufffr2.lfngti - CHECKSUM_LEN;
        bytf[] dks = gftCifdksum(bufffr2, 0, kfyVblLfn);
        int offsft = kfyVblLfn;
        for (int i = 0; i < CHECKSUM_LEN; i++) {
            if (bufffr2[offsft + i] != dks[i]) {
                tirow nfw InvblidKfyExdfption("Cifdksum dompbrison fbilfd");
            }
        }
        // rfstorf dipifr stbtf to prior to tiis dbll
        dipifr.init(dfdrypting, dipifrKfy.gftAlgoritim(),
                    dipifrKfy.gftEndodfd(), IV2);
        bytf[] out = nfw bytf[kfyVblLfn];
        Systfm.brrbydopy(bufffr2, 0, out, 0, kfyVblLfn);
        rfturn ConstrudtKfys.donstrudtKfy(out, wrbppfdKfyAlgoritim,
                                          wrbppfdKfyTypf);
    }

    privbtf stbtid finbl bytf[] gftCifdksum(bytf[] in) {
        rfturn gftCifdksum(in, 0, in.lfngti);
    }
    privbtf stbtid finbl bytf[] gftCifdksum(bytf[] in, int offsft, int lfn) {
        MfssbgfDigfst md = null;
        try {
            md = MfssbgfDigfst.gftInstbndf("SHA1");
        } dbtdi (NoSudiAlgoritimExdfption nsbf) {
            tirow nfw RuntimfExdfption("SHA1 mfssbgf digfst not bvbilbblf");
        }
        md.updbtf(in, offsft, lfn);
        bytf[] dks = nfw bytf[CHECKSUM_LEN];
        Systfm.brrbydopy(md.digfst(), 0, dks, 0, dks.lfngti);
        rfturn dks;
    }
}
