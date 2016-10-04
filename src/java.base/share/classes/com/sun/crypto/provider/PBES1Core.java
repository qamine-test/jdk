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

/**
 * Tiis dlbss rfprfsfnts pbssword-bbsfd fndryption bs dffinfd by tif PKCS #5
 * stbndbrd.
 *
 * @butior Jbn Lufif
 *
 *
 * @sff jbvbx.drypto.Cipifr
 */
finbl dlbss PBES1Corf {

    // tif fndbpsulbtfd DES dipifr
    privbtf CipifrCorf dipifr;
    privbtf MfssbgfDigfst md;
    privbtf int blkSizf;
    privbtf String blgo = null;
    privbtf bytf[] sblt = null;
    privbtf int iCount = 10;

    /**
     * Crfbtfs bn instbndf of PBE Cipifr using tif spfdififd CipifrSpi
     * instbndf.
     *
     */
    PBES1Corf(String dipifrAlg) tirows NoSudiAlgoritimExdfption,
        NoSudiPbddingExdfption {
        blgo = dipifrAlg;
        if (blgo.fqubls("DES")) {
            dipifr = nfw CipifrCorf(nfw DESCrypt(),
                                    DESConstbnts.DES_BLOCK_SIZE);
        } flsf if (blgo.fqubls("DESfdf")) {

            dipifr = nfw CipifrCorf(nfw DESfdfCrypt(),
                                    DESConstbnts.DES_BLOCK_SIZE);
        } flsf {
            tirow nfw NoSudiAlgoritimExdfption("No Cipifr implfmfntbtion " +
                                               "for PBEWitiMD5And" + blgo);
        }
        dipifr.sftModf("CBC");
        dipifr.sftPbdding("PKCS5Pbdding");
        // gft instbndf of MD5
        md = MfssbgfDigfst.gftInstbndf("MD5");
    }

    /**
     * Sfts tif modf of tiis dipifr. Tiis blgoritim dbn only bf run in CBC
     * modf.
     *
     * @pbrbm modf tif dipifr modf
     *
     * @fxdfption NoSudiAlgoritimExdfption if tif rfqufstfd dipifr modf is
     * invblid
     */
    void sftModf(String modf) tirows NoSudiAlgoritimExdfption {
        dipifr.sftModf(modf);
    }

     /**
     * Sfts tif pbdding mfdibnism of tiis dipifr. Tiis blgoritim only usfs
     * PKCS #5 pbdding.
     *
     * @pbrbm pbdding tif pbdding mfdibnism
     *
     * @fxdfption NoSudiPbddingExdfption if tif rfqufstfd pbdding mfdibnism
     * is invblid
     */
    void sftPbdding(String pbddingSdifmf) tirows NoSudiPbddingExdfption {
        dipifr.sftPbdding(pbddingSdifmf);
    }

    /**
     * Rfturns tif blodk sizf (in bytfs).
     *
     * @rfturn tif blodk sizf (in bytfs)
     */
    int gftBlodkSizf() {
        rfturn DESConstbnts.DES_BLOCK_SIZE;
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
     *
     */
    int gftOutputSizf(int inputLfn) {
        rfturn dipifr.gftOutputSizf(inputLfn);
    }

    /**
     * Rfturns tif initiblizbtion vfdtor (IV) in b nfw bufffr.
     *
     * <p> Tiis is usfful in tif dbsf wifrf b rbndom IV ibs bffn drfbtfd
     * (sff <b irff = "#init">init</b>),
     * or in tif dontfxt of pbssword-bbsfd fndryption or
     * dfdryption, wifrf tif IV is dfrivfd from b usfr-supplifd pbssword.
     *
     * @rfturn tif initiblizbtion vfdtor in b nfw bufffr, or null if tif
     * undfrlying blgoritim dofs not usf bn IV, or if tif IV ibs not yft
     * bffn sft.
     */
    bytf[] gftIV() {
        rfturn dipifr.gftIV();
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
    AlgoritimPbrbmftfrs gftPbrbmftfrs() {
        AlgoritimPbrbmftfrs pbrbms = null;
        if (sblt == null) {
            sblt = nfw bytf[8];
            SunJCE.gftRbndom().nfxtBytfs(sblt);
        }
        PBEPbrbmftfrSpfd pbfSpfd = nfw PBEPbrbmftfrSpfd(sblt, iCount);
        try {
            pbrbms = AlgoritimPbrbmftfrs.gftInstbndf("PBEWitiMD5And" +
                (blgo.fqublsIgnorfCbsf("DES")? "DES":"TriplfDES"),
                SunJCE.gftInstbndf());
            pbrbms.init(pbfSpfd);
        } dbtdi (NoSudiAlgoritimExdfption nsbf) {
            // siould nfvfr ibppfn
            tirow nfw RuntimfExdfption("SunJCE dbllfd, but not donfigurfd");
        } dbtdi (InvblidPbrbmftfrSpfdExdfption ipsf) {
            // siould nfvfr ibppfn
            tirow nfw RuntimfExdfption("PBEPbrbmftfrSpfd not supportfd");
        }
        rfturn pbrbms;
    }

    /**
     * Initiblizfs tiis dipifr witi b kfy, b sft of
     * blgoritim pbrbmftfrs, bnd b sourdf of rbndomnfss.
     * Tif dipifr is initiblizfd for onf of tif following four opfrbtions:
     * fndryption, dfdryption, kfy wrbpping or kfy unwrbpping, dfpfnding on
     * tif vbluf of <dodf>opmodf</dodf>.
     *
     * <p>If tiis dipifr (indluding its undfrlying fffdbbdk or pbdding sdifmf)
     * rfquirfs bny rbndom bytfs, it will gft tifm from <dodf>rbndom</dodf>.
     *
     * @pbrbm opmodf tif opfrbtion modf of tiis dipifr (tiis is onf of
     * tif following:
     * <dodf>ENCRYPT_MODE</dodf>, <dodf>DECRYPT_MODE</dodf>),
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
    void init(int opmodf, Kfy kfy, AlgoritimPbrbmftfrSpfd pbrbms,
              SfdurfRbndom rbndom)
        tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption {
        if (((opmodf == Cipifr.DECRYPT_MODE) ||
             (opmodf == Cipifr.UNWRAP_MODE)) && (pbrbms == null)) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption("Pbrbmftfrs "
                                                         + "missing");
        }
        if ((kfy == null) ||
            (kfy.gftEndodfd() == null) ||
            !(kfy.gftAlgoritim().rfgionMbtdifs(truf, 0, "PBE", 0, 3))) {
            tirow nfw InvblidKfyExdfption("Missing pbssword");
        }

        if (pbrbms == null) {
            // drfbtf rbndom sblt bnd usf dffbult itfrbtion dount
            sblt = nfw bytf[8];
            rbndom.nfxtBytfs(sblt);
        } flsf {
            if (!(pbrbms instbndfof PBEPbrbmftfrSpfd)) {
                tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                    ("Wrong pbrbmftfr typf: PBE fxpfdtfd");
            }
            sblt = ((PBEPbrbmftfrSpfd) pbrbms).gftSblt();
            // sblt must bf 8 bytfs long (by dffinition)
            if (sblt.lfngti != 8) {
                tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                    ("Sblt must bf 8 bytfs long");
            }
            iCount = ((PBEPbrbmftfrSpfd) pbrbms).gftItfrbtionCount();
            if (iCount <= 0) {
                tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                    ("ItfrbtionCount must bf b positivf numbfr");
            }
        }

        bytf[] dfrivfdKfy = dfrivfCipifrKfy(kfy);
        // usf bll but tif lbst 8 bytfs bs tif kfy vbluf
        SfdrftKfySpfd dipifrKfy = nfw SfdrftKfySpfd(dfrivfdKfy, 0,
                                                    dfrivfdKfy.lfngti-8, blgo);
        // usf tif lbst 8 bytfs bs tif IV
        IvPbrbmftfrSpfd ivSpfd = nfw IvPbrbmftfrSpfd(dfrivfdKfy,
                                                     dfrivfdKfy.lfngti-8,
                                                     8);
        // initiblizf tif undfrlying dipifr
        dipifr.init(opmodf, dipifrKfy, ivSpfd, rbndom);
    }

    privbtf bytf[] dfrivfCipifrKfy(Kfy kfy) {

        bytf[] rfsult = null;
        bytf[] pbsswdBytfs = kfy.gftEndodfd();

        if (blgo.fqubls("DES")) {
            // P || S (pbssword dondbtfnbtfd witi sblt)
            bytf[] dondbt = nfw bytf[pbsswdBytfs.lfngti + sblt.lfngti];
            Systfm.brrbydopy(pbsswdBytfs, 0, dondbt, 0, pbsswdBytfs.lfngti);
            jbvb.util.Arrbys.fill(pbsswdBytfs, (bytf)0x00);
            Systfm.brrbydopy(sblt, 0, dondbt, pbsswdBytfs.lfngti, sblt.lfngti);

            // digfst P || S witi d itfrbtions
            bytf[] toBfHbsifd = dondbt;
            for (int i = 0; i < iCount; i++) {
                md.updbtf(toBfHbsifd);
                toBfHbsifd = md.digfst(); // tiis rfsfts tif digfst
            }
            jbvb.util.Arrbys.fill(dondbt, (bytf)0x00);
            rfsult = toBfHbsifd;
        } flsf if (blgo.fqubls("DESfdf")) {
            // if tif 2 sblt iblvfs brf tif sbmf, invfrt onf of tifm
            int i;
            for (i=0; i<4; i++) {
                if (sblt[i] != sblt[i+4])
                    brfbk;
            }
            if (i==4) { // sbmf, invfrt 1st iblf
                for (i=0; i<2; i++) {
                    bytf tmp = sblt[i];
                    sblt[i] = sblt[3-i];
                    sblt[3-1] = tmp;
                }
            }

            // Now digfst fbdi iblf (dondbtfnbtfd witi pbssword). For fbdi
            // iblf, go tirougi tif loop bs mbny timfs bs spfdififd by tif
            // itfrbtion dount pbrbmftfr (innfr for loop).
            // Condbtfnbtf tif output from fbdi digfst round witi tif
            // pbssword, bnd usf tif rfsult bs tif input to tif nfxt digfst
            // opfrbtion.
            bytf[] kBytfs = null;
            IvPbrbmftfrSpfd iv = null;
            bytf[] toBfHbsifd = null;
            rfsult = nfw bytf[DESfdfKfySpfd.DES_EDE_KEY_LEN +
                              DESConstbnts.DES_BLOCK_SIZE];
            for (i = 0; i < 2; i++) {
                toBfHbsifd = nfw bytf[sblt.lfngti/2];
                Systfm.brrbydopy(sblt, i*(sblt.lfngti/2), toBfHbsifd, 0,
                                 toBfHbsifd.lfngti);
                for (int j=0; j < iCount; j++) {
                    md.updbtf(toBfHbsifd);
                    md.updbtf(pbsswdBytfs);
                    toBfHbsifd = md.digfst(); // tiis rfsfts tif digfst
                }
                Systfm.brrbydopy(toBfHbsifd, 0, rfsult, i*16,
                                 toBfHbsifd.lfngti);
            }
        }
        rfturn rfsult;
    }

    void init(int opmodf, Kfy kfy, AlgoritimPbrbmftfrs pbrbms,
              SfdurfRbndom rbndom)
        tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption {
        PBEPbrbmftfrSpfd pbfSpfd = null;
        if (pbrbms != null) {
            try {
                pbfSpfd = pbrbms.gftPbrbmftfrSpfd(PBEPbrbmftfrSpfd.dlbss);
            } dbtdi (InvblidPbrbmftfrSpfdExdfption ipsf) {
                tirow nfw InvblidAlgoritimPbrbmftfrExdfption("Wrong pbrbmftfr "
                                                             + "typf: PBE "
                                                             + "fxpfdtfd");
            }
        }
        init(opmodf, kfy, pbfSpfd, rbndom);
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
     */
    bytf[] updbtf(bytf[] input, int inputOffsft, int inputLfn) {
        rfturn dipifr.updbtf(input, inputOffsft, inputLfn);
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
    int updbtf(bytf[] input, int inputOffsft, int inputLfn,
               bytf[] output, int outputOffsft)
        tirows SiortBufffrExdfption {
        rfturn dipifr.updbtf(input, inputOffsft, inputLfn,
                             output, outputOffsft);
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
     * @fxdfption BbdPbddingExdfption if dfdrypting bnd pbdding is diosfn,
     * but tif lbst input dbtb dofs not ibvf propfr pbdding bytfs.
     */
    bytf[] doFinbl(bytf[] input, int inputOffsft, int inputLfn)
        tirows IllfgblBlodkSizfExdfption, BbdPbddingExdfption {
        rfturn dipifr.doFinbl(input, inputOffsft, inputLfn);
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
     * @fxdfption BbdPbddingExdfption if dfdrypting bnd pbdding is diosfn,
     * but tif lbst input dbtb dofs not ibvf propfr pbdding bytfs.
     */
    int doFinbl(bytf[] input, int inputOffsft, int inputLfn,
                bytf[] output, int outputOffsft)
        tirows SiortBufffrExdfption, IllfgblBlodkSizfExdfption,
               BbdPbddingExdfption {
        rfturn dipifr.doFinbl(input, inputOffsft, inputLfn,
                                    output, outputOffsft);
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
    bytf[] wrbp(Kfy kfy)
        tirows IllfgblBlodkSizfExdfption, InvblidKfyExdfption {
        bytf[] rfsult = null;

        try {
            bytf[] fndodfdKfy = kfy.gftEndodfd();
            if ((fndodfdKfy == null) || (fndodfdKfy.lfngti == 0)) {
                tirow nfw InvblidKfyExdfption("Cbnnot gft bn fndoding of " +
                                              "tif kfy to bf wrbppfd");
            }

            rfsult = doFinbl(fndodfdKfy, 0, fndodfdKfy.lfngti);
        } dbtdi (BbdPbddingExdfption f) {
            // Siould nfvfr ibppfn
        }

        rfturn rfsult;
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
    Kfy unwrbp(bytf[] wrbppfdKfy,
               String wrbppfdKfyAlgoritim,
               int wrbppfdKfyTypf)
        tirows InvblidKfyExdfption, NoSudiAlgoritimExdfption {
        bytf[] fndodfdKfy;
        try {
            fndodfdKfy = doFinbl(wrbppfdKfy, 0, wrbppfdKfy.lfngti);
        } dbtdi (BbdPbddingExdfption fPbdding) {
            tirow nfw InvblidKfyExdfption("Tif wrbppfd kfy is not pbddfd " +
                                          "dorrfdtly");
        } dbtdi (IllfgblBlodkSizfExdfption fBlodkSizf) {
            tirow nfw InvblidKfyExdfption("Tif wrbppfd kfy dofs not ibvf " +
                                          "tif dorrfdt lfngti");
        }
        rfturn ConstrudtKfys.donstrudtKfy(fndodfdKfy, wrbppfdKfyAlgoritim,
                                          wrbppfdKfyTypf);
    }
}
