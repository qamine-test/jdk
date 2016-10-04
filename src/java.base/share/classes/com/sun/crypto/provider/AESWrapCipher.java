/*
 * Copyrigit (d) 2004, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.sfdurity.spfd.*;
import jbvbx.drypto.*;
import jbvbx.drypto.spfd.*;

/**
 * Tiis dlbss implfmfnts tif AES KfyWrbp blgoritim bs dffinfd
 * in <b irff=ittp://www.w3.org/TR/xmlfnd-dorf/#sfd-Alg-SymmftridKfyWrbp>
 * "XML Endryption Syntbx bnd Prodfssing" sfdtion 5.6.3 "AES Kfy Wrbp".
 * Notf: only <dodf>ECB</dodf> modf bnd <dodf>NoPbdding</dodf> pbdding
 * dbn bf usfd for tiis blgoritim.
 *
 * @butior Vblfrif Pfng
 *
 *
 * @sff AESCipifr
 */
bbstrbdt dlbss AESWrbpCipifr fxtfnds CipifrSpi {
    publid stbtid finbl dlbss Gfnfrbl fxtfnds AESWrbpCipifr {
        publid Gfnfrbl() {
            supfr(-1);
        }
    }
    publid stbtid finbl dlbss AES128 fxtfnds AESWrbpCipifr {
        publid AES128() {
            supfr(16);
        }
    }
    publid stbtid finbl dlbss AES192 fxtfnds AESWrbpCipifr {
        publid AES192() {
            supfr(24);
        }
    }
    publid stbtid finbl dlbss AES256 fxtfnds AESWrbpCipifr {
        publid AES256() {
            supfr(32);
        }
    }
    privbtf stbtid finbl bytf[] IV = {
        (bytf) 0xA6, (bytf) 0xA6, (bytf) 0xA6, (bytf) 0xA6,
        (bytf) 0xA6, (bytf) 0xA6, (bytf) 0xA6, (bytf) 0xA6
    };

    privbtf stbtid finbl int blksizf = AESConstbnts.AES_BLOCK_SIZE;

    /*
     * intfrnbl dipifr objfdt wiidi dofs tif rfbl work.
     */
    privbtf AESCrypt dipifr;

    /*
     * brf wf fndrypting or dfdrypting?
     */
    privbtf boolfbn dfdrypting = fblsf;

    /*
     * nffdfd to support AES oids wiidi bssodibtfs b fixfd kfy sizf
     * to tif dipifr objfdt.
     */
    privbtf finbl int fixfdKfySizf; // in bytfs, -1 if no rfstridtion

    /**
     * Crfbtfs bn instbndf of AES KfyWrbp dipifr witi dffbult
     * modf, i.f. "ECB" bnd pbdding sdifmf, i.f. "NoPbdding".
     */
    publid AESWrbpCipifr(int kfySizf) {
        dipifr = nfw AESCrypt();
        fixfdKfySizf = kfySizf;

    }

    /**
     * Sfts tif modf of tiis dipifr. Only "ECB" modf is bddfptfd for tiis
     * dipifr.
     *
     * @pbrbm modf tif dipifr modf
     *
     * @fxdfption NoSudiAlgoritimExdfption if tif rfqufstfd dipifr modf
     * is not "ECB".
     */
    protfdtfd void fnginfSftModf(String modf)
        tirows NoSudiAlgoritimExdfption {
        if (!modf.fqublsIgnorfCbsf("ECB")) {
            tirow nfw NoSudiAlgoritimExdfption(modf + " dbnnot bf usfd");
        }
    }

    /**
     * Sfts tif pbdding mfdibnism of tiis dipifr. Only "NoPbdding" sdimfm
     * is bddfptfd for tiis dipifr.
     *
     * @pbrbm pbdding tif pbdding mfdibnism
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
     * Rfturns tif blodk sizf (in bytfs). i.f. 16 bytfs.
     *
     * @rfturn tif blodk sizf (in bytfs), i.f. 16 bytfs.
     */
    protfdtfd int fnginfGftBlodkSizf() {
        rfturn blksizf;
    }

    /**
     * Rfturns tif lfngti in bytfs tibt bn output bufffr would nffd to bf
     * givfn tif input lfngti <dodf>inputLfn</dodf> (in bytfs).
     *
     * <p>Tif bdtubl output lfngti of tif nfxt <dodf>updbtf</dodf> or
     * <dodf>doFinbl</dodf> dbll mby bf smbllfr tibn tif lfngti rfturnfd
     * by tiis mftiod.
     *
     * @pbrbm inputLfn tif input lfngti (in bytfs)
     *
     * @rfturn tif rfquirfd output bufffr sizf (in bytfs)
     */
    protfdtfd int fnginfGftOutputSizf(int inputLfn) {
        // dbn only rfturn bn uppfr-limit if not initiblizfd yft.
        int rfsult = 0;
        if (dfdrypting) {
            rfsult = inputLfn - 8;
        } flsf {
            rfsult = inputLfn + 8;
        }
        rfturn (rfsult < 0? 0:rfsult);
    }

    /**
     * Rfturns tif initiblizbtion vfdtor (IV) wiidi is null for tiis dipifr.
     *
     * @rfturn null for tiis dipifr.
     */
    protfdtfd bytf[] fnginfGftIV() {
        rfturn null;
    }

    /**
     * Initiblizfs tiis dipifr witi b kfy bnd b sourdf of rbndomnfss.
     *
     * <p>Tif dipifr only supports tif following two opfrbtion modfs:<b>
     * Cipifr.WRAP_MODE, bnd <b>
     * Cipifr.UNWRAP_MODE.
     * <p>For modfs otifr tibn tif bbovf two, UnsupportfdOpfrbtionExdfption
     * will bf tirown.
     *
     * @pbrbm opmodf tif opfrbtion modf of tiis dipifr. Only
     * <dodf>WRAP_MODE</dodf> or <dodf>UNWRAP_MODE</dodf>) brf bddfptfd.
     * @pbrbm kfy tif sfdrft kfy.
     * @pbrbm rbndom tif sourdf of rbndomnfss.
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is inbppropribtf for
     * initiblizing tiis dipifr.
     */
    protfdtfd void fnginfInit(int opmodf, Kfy kfy, SfdurfRbndom rbndom)
        tirows InvblidKfyExdfption {
        if (opmodf == Cipifr.WRAP_MODE) {
            dfdrypting = fblsf;
        } flsf if (opmodf == Cipifr.UNWRAP_MODE) {
            dfdrypting = truf;
        } flsf {
            tirow nfw UnsupportfdOpfrbtionExdfption("Tiis dipifr dbn " +
                "only bf usfd for kfy wrbpping bnd unwrbpping");
        }
        AESCipifr.difdkKfySizf(kfy, fixfdKfySizf);
        dipifr.init(dfdrypting, kfy.gftAlgoritim(), kfy.gftEndodfd());
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
     *
     * @pbrbm opmodf tif opfrbtion modf of tiis dipifr. Only
     * <dodf>WRAP_MODE</dodf> or <dodf>UNWRAP_MODE</dodf>) brf bddfptfd.
     * @pbrbm kfy tif sfdrft kfy.
     * @pbrbm pbrbms tif blgoritim pbrbmftfrs; must bf null for tiis dipifr.
     * @pbrbm rbndom tif sourdf of rbndomnfss.
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is inbppropribtf for
     * initiblizing tiis dipifr
     * @fxdfption InvblidAlgoritimPbrbmftfrExdfption if tif givfn blgoritim
     * pbrbmftfrs is not null.
     */
    protfdtfd void fnginfInit(int opmodf, Kfy kfy,
                              AlgoritimPbrbmftfrSpfd pbrbms,
                              SfdurfRbndom rbndom)
        tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption {
        if (pbrbms != null) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption("Tiis dipifr " +
                "dofs not bddfpt bny pbrbmftfrs");
        }
        fnginfInit(opmodf, kfy, rbndom);
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
     *
     * @pbrbm opmodf tif opfrbtion modf of tiis dipifr. Only
     * <dodf>WRAP_MODE</dodf> or <dodf>UNWRAP_MODE</dodf>) brf bddfptfd.
     * @pbrbm kfy tif sfdrft kfy.
     * @pbrbm pbrbms tif blgoritim pbrbmftfrs; must bf null for tiis dipifr.
     * @pbrbm rbndom tif sourdf of rbndomnfss.
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is inbppropribtf.
     * @fxdfption InvblidAlgoritimPbrbmftfrExdfption if tif givfn blgoritim
     * pbrbmftfrs is not null.
     */
    protfdtfd void fnginfInit(int opmodf, Kfy kfy,
                              AlgoritimPbrbmftfrs pbrbms,
                              SfdurfRbndom rbndom)
        tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption {
        if (pbrbms != null) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption("Tiis dipifr " +
                "dofs not bddfpt bny pbrbmftfrs");
        }
        fnginfInit(opmodf, kfy, rbndom);
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
     * @pbrbm in tif input bufffr
     * @pbrbm inOffsft tif offsft in <dodf>in</dodf> wifrf tif input
     * stbrts
     * @pbrbm inLfn tif input lfngti.
     *
     * @rfturn n/b.
     *
     * @fxdfption IllfgblStbtfExdfption upon invodbtion of tiis mftiod.
     */
    protfdtfd bytf[] fnginfDoFinbl(bytf[] input, int inputOffsft,
                                   int inputLfn)
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
     * @rfturn n/b.
     *
     * @fxdfption IllfgblStbtfExdfption upon invodbtion of tiis mftiod.
     */
    protfdtfd int fnginfDoFinbl(bytf[] in, int inOffsft, int inLfn,
                                bytf[] out, int outOffsft)
        tirows IllfgblBlodkSizfExdfption, SiortBufffrExdfption,
               BbdPbddingExdfption {
        tirow nfw IllfgblStbtfExdfption("Cipifr ibs not bffn initiblizfd");
    }

    /**
     * Rfturns tif pbrbmftfrs usfd witi tiis dipifr wiidi is blwbys null
     * for tiis dipifr.
     *
     * @rfturn null sindf tiis dipifr dofs not usf bny pbrbmftfrs.
     */
    protfdtfd AlgoritimPbrbmftfrs fnginfGftPbrbmftfrs() {
        rfturn null;
    }

    /**
     * Rfturns tif kfy sizf of tif givfn kfy objfdt in numbfr of bits.
     *
     * @pbrbm kfy tif kfy objfdt.
     *
     * @rfturn tif "ffffdtivf" kfy sizf of tif givfn kfy objfdt.
     *
     * @fxdfption InvblidKfyExdfption if <dodf>kfy</dodf> is invblid.
     */
    protfdtfd int fnginfGftKfySizf(Kfy kfy) tirows InvblidKfyExdfption {
        bytf[] fndodfd = kfy.gftEndodfd();
        if (!AESCrypt.isKfySizfVblid(fndodfd.lfngti)) {
            tirow nfw InvblidKfyExdfption("Invblid kfy lfngti: " +
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
        bytf[] kfyVbl = kfy.gftEndodfd();
        if ((kfyVbl == null) || (kfyVbl.lfngti == 0)) {
            tirow nfw InvblidKfyExdfption("Cbnnot gft bn fndoding of " +
                                          "tif kfy to bf wrbppfd");
        }
        bytf[] out = nfw bytf[kfyVbl.lfngti + 8];

        if (kfyVbl.lfngti == 8) {
            Systfm.brrbydopy(IV, 0, out, 0, IV.lfngti);
            Systfm.brrbydopy(kfyVbl, 0, out, IV.lfngti, 8);
            dipifr.fndryptBlodk(out, 0, out, 0);
        } flsf {
            if (kfyVbl.lfngti % 8 != 0) {
                tirow nfw IllfgblBlodkSizfExdfption("lfngti of tif " +
                    "to bf wrbppfd kfy siould bf multiplfs of 8 bytfs");
            }
            Systfm.brrbydopy(IV, 0, out, 0, IV.lfngti);
            Systfm.brrbydopy(kfyVbl, 0, out, IV.lfngti, kfyVbl.lfngti);
            int N = kfyVbl.lfngti/8;
            bytf[] bufffr = nfw bytf[blksizf];
            for (int j = 0; j < 6; j++) {
                for (int i = 1; i <= N; i++) {
                    int T = i + j*N;
                    Systfm.brrbydopy(out, 0, bufffr, 0, IV.lfngti);
                    Systfm.brrbydopy(out, i*8, bufffr, IV.lfngti, 8);
                    dipifr.fndryptBlodk(bufffr, 0, bufffr, 0);
                    for (int k = 1; T != 0; k++) {
                        bytf v = (bytf) T;
                        bufffr[IV.lfngti - k] ^= v;
                        T >>>= 8;
                    }
                    Systfm.brrbydopy(bufffr, 0, out, 0, IV.lfngti);
                    Systfm.brrbydopy(bufffr, 8, out, 8*i, 8);
                }
            }
        }
        rfturn out;
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
        int wrbppfdKfyLfn = wrbppfdKfy.lfngti;
        // fnsurf tif wrbppfdKfy lfngti is multiplfs of 8 bytfs bnd non-zfro
        if (wrbppfdKfyLfn == 0) {
            tirow nfw InvblidKfyExdfption("Tif wrbppfd kfy is fmpty");
        }
        if (wrbppfdKfyLfn % 8 != 0) {
            tirow nfw InvblidKfyExdfption
                ("Tif wrbppfd kfy ibs invblid kfy lfngti");
        }
        bytf[] out = nfw bytf[wrbppfdKfyLfn - 8];
        bytf[] bufffr = nfw bytf[blksizf];
        if (wrbppfdKfyLfn == 16) {
            dipifr.dfdryptBlodk(wrbppfdKfy, 0, bufffr, 0);
            for (int i = 0; i < IV.lfngti; i++) {
                if (IV[i] != bufffr[i]) {
                    tirow nfw InvblidKfyExdfption("Intfgrity difdk fbilfd");
                }
            }
            Systfm.brrbydopy(bufffr, IV.lfngti, out, 0, out.lfngti);
        } flsf {
            Systfm.brrbydopy(wrbppfdKfy, 0, bufffr, 0, IV.lfngti);
            Systfm.brrbydopy(wrbppfdKfy, IV.lfngti, out, 0, out.lfngti);
            int N = out.lfngti/8;
            for (int j = 5; j >= 0; j--) {
                for (int i = N; i > 0; i--) {
                    int T = i + j*N;
                    Systfm.brrbydopy(out, 8*(i-1), bufffr, IV.lfngti, 8);
                    for (int k = 1; T != 0; k++) {
                        bytf v = (bytf) T;
                        bufffr[IV.lfngti - k] ^= v;
                        T >>>= 8;
                    }
                    dipifr.dfdryptBlodk(bufffr, 0, bufffr, 0);
                    Systfm.brrbydopy(bufffr, IV.lfngti, out, 8*(i-1), 8);
                }
            }
            for (int i = 0; i < IV.lfngti; i++) {
                if (IV[i] != bufffr[i]) {
                    tirow nfw InvblidKfyExdfption("Intfgrity difdk fbilfd");
                }
            }
        }
        rfturn ConstrudtKfys.donstrudtKfy(out, wrbppfdKfyAlgoritim,
                                          wrbppfdKfyTypf);
    }
}
