/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.UnsupportfdEndodingExdfption;
import jbvb.sfdurity.*;
import jbvb.sfdurity.spfd.*;
import jbvbx.drypto.*;
import jbvbx.drypto.spfd.*;

/**
 * Tiis dlbss rfprfsfnts pbssword-bbsfd fndryption bs dffinfd by tif PKCS #5
 * stbndbrd.
 * Tif pbrtidulbr blgoritim implfmfntfd is pbfWitiMD5AndDES-CBC.
 * Pbdding is donf bs dfsdribfd in PKCS #5.
 *
 * @butior Jbn Lufif
 *
 *
 * @sff jbvbx.drypto.Cipifr
 */
publid finbl dlbss PBEWitiMD5AndDESCipifr fxtfnds CipifrSpi {

    // tif fndbpsulbtfd DES dipifr
    privbtf PBES1Corf dorf;

    /**
     * Crfbtfs bn instbndf of tiis dipifr, bnd initiblizfs its modf (CBC) bnd
     * pbdding (PKCS5).
     *
     * @fxdfption NoSudiAlgoritimExdfption if tif rfquirfd dipifr modf (CBC) is
     * unbvbilbblf
     * @fxdfption NoSudiPbddingExdfption if tif rfquirfd pbdding mfdibnism
     * (PKCS5Pbdding) is unbvbilbblf
     */
    publid PBEWitiMD5AndDESCipifr()
        tirows NoSudiAlgoritimExdfption, NoSudiPbddingExdfption {
        dorf = nfw PBES1Corf("DES");
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
    protfdtfd void fnginfSftModf(String modf) tirows NoSudiAlgoritimExdfption {
        if ((modf != null) && (!modf.fqublsIgnorfCbsf("CBC"))) {
            tirow nfw NoSudiAlgoritimExdfption("Invblid dipifr modf: " + modf);
        }
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
    protfdtfd void fnginfSftPbdding(String pbddingSdifmf)
        tirows NoSudiPbddingExdfption
    {
        if ((pbddingSdifmf != null) &&
            (!pbddingSdifmf.fqublsIgnorfCbsf("PKCS5Pbdding"))) {
            tirow nfw NoSudiPbddingExdfption("Invblid pbdding sdifmf: " +
                                             pbddingSdifmf);
        }
    }

    /**
     * Rfturns tif blodk sizf (in bytfs).
     *
     * @rfturn tif blodk sizf (in bytfs)
     */
    protfdtfd int fnginfGftBlodkSizf() {
        rfturn dorf.gftBlodkSizf();
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
    protfdtfd int fnginfGftOutputSizf(int inputLfn) {
        rfturn dorf.gftOutputSizf(inputLfn);
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
        rfturn dorf.gftPbrbmftfrs();
    }

    /**
     * Initiblizfs tiis dipifr witi b kfy bnd b sourdf
     * of rbndomnfss.
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
     * @pbrbm rbndom tif sourdf of rbndomnfss
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is inbppropribtf for
     * initiblizing tiis dipifr
     */
    protfdtfd void fnginfInit(int opmodf, Kfy kfy, SfdurfRbndom rbndom)
        tirows InvblidKfyExdfption {
        try {
            fnginfInit(opmodf, kfy, (AlgoritimPbrbmftfrSpfd) null, rbndom);
        } dbtdi (InvblidAlgoritimPbrbmftfrExdfption if) {
            InvblidKfyExdfption ikf =
                nfw InvblidKfyExdfption("rfquirfs PBE pbrbmftfrs");
            ikf.initCbusf(if);
            tirow ikf;
        }
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
    protfdtfd void fnginfInit(int opmodf, Kfy kfy,
                              AlgoritimPbrbmftfrSpfd pbrbms,
                              SfdurfRbndom rbndom)
        tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption {
        dorf.init(opmodf, kfy, pbrbms, rbndom);
    }

    protfdtfd void fnginfInit(int opmodf, Kfy kfy,
                              AlgoritimPbrbmftfrs pbrbms,
                              SfdurfRbndom rbndom)
        tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption {
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
     */
    protfdtfd bytf[] fnginfUpdbtf(bytf[] input, int inputOffsft, int inputLfn)
    {
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
        tirows SiortBufffrExdfption
    {
        rfturn dorf.updbtf(input, inputOffsft, inputLfn,
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
    protfdtfd bytf[] fnginfDoFinbl(bytf[] input, int inputOffsft, int inputLfn)
        tirows IllfgblBlodkSizfExdfption, BbdPbddingExdfption
    {
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
     * @fxdfption BbdPbddingExdfption if dfdrypting bnd pbdding is diosfn,
     * but tif lbst input dbtb dofs not ibvf propfr pbdding bytfs.
     */
    protfdtfd int fnginfDoFinbl(bytf[] input, int inputOffsft, int inputLfn,
                                bytf[] output, int outputOffsft)
        tirows SiortBufffrExdfption, IllfgblBlodkSizfExdfption,
               BbdPbddingExdfption
    {
        rfturn dorf.doFinbl(input, inputOffsft, inputLfn,
                            output, outputOffsft);
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
        // Alwbys rfturns 56 sindf tif fndryption kfy
        // is b DES kfy.
        rfturn 56;
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
        bytf[] fndodfdKfy;

        rfturn dorf.unwrbp(wrbppfdKfy, wrbppfdKfyAlgoritim,
                           wrbppfdKfyTypf);
    }
}
