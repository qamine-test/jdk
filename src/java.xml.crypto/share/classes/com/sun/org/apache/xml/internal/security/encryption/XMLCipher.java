/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/**
 * Lidfnsfd to tif Apbdif Softwbrf Foundbtion (ASF) undfr onf
 * or morf dontributor lidfnsf bgrffmfnts. Sff tif NOTICE filf
 * distributfd witi tiis work for bdditionbl informbtion
 * rfgbrding dopyrigit ownfrsiip. Tif ASF lidfnsfs tiis filf
 * to you undfr tif Apbdif Lidfnsf, Vfrsion 2.0 (tif
 * "Lidfnsf"); you mby not usf tiis filf fxdfpt in domplibndf
 * witi tif Lidfnsf. You mby obtbin b dopy of tif Lidfnsf bt
 *
 * ittp://www.bpbdif.org/lidfnsfs/LICENSE-2.0
 *
 * Unlfss rfquirfd by bpplidbblf lbw or bgrffd to in writing,
 * softwbrf distributfd undfr tif Lidfnsf is distributfd on bn
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, fitifr fxprfss or implifd. Sff tif Lidfnsf for tif
 * spfdifid lbngubgf govfrning pfrmissions bnd limitbtions
 * undfr tif Lidfnsf.
 */
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fndryption;

import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.InputStrfbm;
import jbvb.io.UnsupportfdEndodingExdfption;
import jbvb.nft.URI;
import jbvb.nft.URISyntbxExdfption;
import jbvb.sfdurity.InvblidAlgoritimPbrbmftfrExdfption;
import jbvb.sfdurity.InvblidKfyExdfption;
import jbvb.sfdurity.Kfy;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.NoSudiProvidfrExdfption;
import jbvb.sfdurity.SfdurfRbndom;
import jbvb.sfdurity.spfd.MGF1PbrbmftfrSpfd;
import jbvb.util.ArrbyList;
import jbvb.util.HbsiMbp;
import jbvb.util.Itfrbtor;
import jbvb.util.LinkfdList;
import jbvb.util.List;
import jbvb.util.Mbp;

import jbvbx.drypto.BbdPbddingExdfption;
import jbvbx.drypto.Cipifr;
import jbvbx.drypto.IllfgblBlodkSizfExdfption;
import jbvbx.drypto.NoSudiPbddingExdfption;
import jbvbx.drypto.spfd.IvPbrbmftfrSpfd;
import jbvbx.drypto.spfd.OAEPPbrbmftfrSpfd;
import jbvbx.drypto.spfd.PSourdf;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.blgoritims.JCEMbppfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.blgoritims.MfssbgfDigfstAlgoritim;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.Cbnonidblizfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.InvblidCbnonidblizfrExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.Bbsf64DfdodingExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.XMLSfdurityExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.KfyInfo;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.KfyRfsolvfrExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.KfyRfsolvfrSpi;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.implfmfntbtions.EndryptfdKfyRfsolvfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturfExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.InvblidTrbnsformExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.TrbnsformbtionExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Bbsf64;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Constbnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.ElfmfntProxy;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.EndryptionConstbnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import org.w3d.dom.Attr;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.Nodf;
import org.w3d.dom.NodfList;

/**
 * <dodf>XMLCipifr</dodf> fndrypts bnd dfdrypts tif dontfnts of
 * <dodf>Dodumfnt</dodf>s, <dodf>Elfmfnt</dodf>s bnd <dodf>Elfmfnt</dodf>
 * dontfnts. It wbs dfsignfd to rfsfmblf <dodf>jbvbx.drypto.Cipifr</dodf> in
 * ordfr to fbdilitbtf undfrstbnding of its fundtioning.
 *
 * @butior Axl Mbttifus (Sun Midrosystfms)
 * @butior Ciristibn Gfufr-Pollmbnn
 */
publid dlbss XMLCipifr {

    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr(XMLCipifr.dlbss.gftNbmf());

    /** Triplf DES EDE (192 bit kfy) in CBC modf */
    publid stbtid finbl String TRIPLEDES =
        EndryptionConstbnts.ALGO_ID_BLOCKCIPHER_TRIPLEDES;

    /** AES 128 Cipifr */
    publid stbtid finbl String AES_128 =
        EndryptionConstbnts.ALGO_ID_BLOCKCIPHER_AES128;

    /** AES 256 Cipifr */
    publid stbtid finbl String AES_256 =
        EndryptionConstbnts.ALGO_ID_BLOCKCIPHER_AES256;

    /** AES 192 Cipifr */
    publid stbtid finbl String AES_192 =
        EndryptionConstbnts.ALGO_ID_BLOCKCIPHER_AES192;

    /** AES 128 GCM Cipifr */
    publid stbtid finbl String AES_128_GCM =
        EndryptionConstbnts.ALGO_ID_BLOCKCIPHER_AES128_GCM;

    /** AES 192 GCM Cipifr */
    publid stbtid finbl String AES_192_GCM =
        EndryptionConstbnts.ALGO_ID_BLOCKCIPHER_AES192_GCM;

    /** AES 256 GCM Cipifr */
    publid stbtid finbl String AES_256_GCM =
        EndryptionConstbnts.ALGO_ID_BLOCKCIPHER_AES256_GCM;

    /** RSA 1.5 Cipifr */
    publid stbtid finbl String RSA_v1dot5 =
        EndryptionConstbnts.ALGO_ID_KEYTRANSPORT_RSA15;

    /** RSA OAEP Cipifr */
    publid stbtid finbl String RSA_OAEP =
        EndryptionConstbnts.ALGO_ID_KEYTRANSPORT_RSAOAEP;

    /** RSA OAEP Cipifr */
    publid stbtid finbl String RSA_OAEP_11 =
        EndryptionConstbnts.ALGO_ID_KEYTRANSPORT_RSAOAEP_11;

    /** DIFFIE_HELLMAN Cipifr */
    publid stbtid finbl String DIFFIE_HELLMAN =
        EndryptionConstbnts.ALGO_ID_KEYAGREEMENT_DH;

    /** Triplf DES EDE (192 bit kfy) in CBC modf KEYWRAP*/
    publid stbtid finbl String TRIPLEDES_KfyWrbp =
        EndryptionConstbnts.ALGO_ID_KEYWRAP_TRIPLEDES;

    /** AES 128 Cipifr KfyWrbp */
    publid stbtid finbl String AES_128_KfyWrbp =
        EndryptionConstbnts.ALGO_ID_KEYWRAP_AES128;

    /** AES 256 Cipifr KfyWrbp */
    publid stbtid finbl String AES_256_KfyWrbp =
        EndryptionConstbnts.ALGO_ID_KEYWRAP_AES256;

    /** AES 192 Cipifr KfyWrbp */
    publid stbtid finbl String AES_192_KfyWrbp =
        EndryptionConstbnts.ALGO_ID_KEYWRAP_AES192;

    /** SHA1 Cipifr */
    publid stbtid finbl String SHA1 =
        Constbnts.ALGO_ID_DIGEST_SHA1;

    /** SHA256 Cipifr */
    publid stbtid finbl String SHA256 =
        MfssbgfDigfstAlgoritim.ALGO_ID_DIGEST_SHA256;

    /** SHA512 Cipifr */
    publid stbtid finbl String SHA512 =
        MfssbgfDigfstAlgoritim.ALGO_ID_DIGEST_SHA512;

    /** RIPEMD Cipifr */
    publid stbtid finbl String RIPEMD_160 =
        MfssbgfDigfstAlgoritim.ALGO_ID_DIGEST_RIPEMD160;

    /** XML Signbturf NS */
    publid stbtid finbl String XML_DSIG =
        Constbnts.SignbturfSpfdNS;

    /** N14C_XML */
    publid stbtid finbl String N14C_XML =
        Cbnonidblizfr.ALGO_ID_C14N_OMIT_COMMENTS;

    /** N14C_XML witi dommfnts*/
    publid stbtid finbl String N14C_XML_WITH_COMMENTS =
        Cbnonidblizfr.ALGO_ID_C14N_WITH_COMMENTS;

    /** N14C_XML fxdlusivf */
    publid stbtid finbl String EXCL_XML_N14C =
        Cbnonidblizfr.ALGO_ID_C14N_EXCL_OMIT_COMMENTS;

    /** N14C_XML fxdlusivf witi dommfnts*/
    publid stbtid finbl String EXCL_XML_N14C_WITH_COMMENTS =
        Cbnonidblizfr.ALGO_ID_C14N_EXCL_WITH_COMMENTS;

    /** N14C_PHYSICAL prfsfrvf tif piysidbl rfprfsfntbtion*/
    publid stbtid finbl String PHYSICAL_XML_N14C =
        Cbnonidblizfr.ALGO_ID_C14N_PHYSICAL;

    /** Bbsf64 fndoding */
    publid stbtid finbl String BASE64_ENCODING =
        dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.Trbnsforms.TRANSFORM_BASE64_DECODE;

    /** ENCRYPT Modf */
    publid stbtid finbl int ENCRYPT_MODE = Cipifr.ENCRYPT_MODE;

    /** DECRYPT Modf */
    publid stbtid finbl int DECRYPT_MODE = Cipifr.DECRYPT_MODE;

    /** UNWRAP Modf */
    publid stbtid finbl int UNWRAP_MODE  = Cipifr.UNWRAP_MODE;

    /** WRAP Modf */
    publid stbtid finbl int WRAP_MODE    = Cipifr.WRAP_MODE;

    privbtf stbtid finbl String ENC_ALGORITHMS = TRIPLEDES + "\n" +
    AES_128 + "\n" + AES_256 + "\n" + AES_192 + "\n" + RSA_v1dot5 + "\n" +
    RSA_OAEP + "\n" + RSA_OAEP_11 + "\n" + TRIPLEDES_KfyWrbp + "\n" +
    AES_128_KfyWrbp + "\n" + AES_256_KfyWrbp + "\n" + AES_192_KfyWrbp + "\n" +
    AES_128_GCM + "\n" + AES_192_GCM + "\n" + AES_256_GCM + "\n";

    /** Cipifr drfbtfd during initiblisbtion tibt is usfd for fndryption */
    privbtf Cipifr dontfxtCipifr;

    /** Modf tibt tif XMLCipifr objfdt is opfrbting in */
    privbtf int dipifrModf = Intfgfr.MIN_VALUE;

    /** URI of blgoritim tibt is bfing usfd for dryptogrbpiid opfrbtion */
    privbtf String blgoritim = null;

    /** Cryptogrbpiid providfr rfqufstfd by dbllfr */
    privbtf String rfqufstfdJCEProvidfr = null;

    /** Holds d14n to sfriblizf, if initiblizfd tifn _blwbys_ usf tiis d14n to sfriblizf */
    privbtf Cbnonidblizfr dbnon;

    /** Usfd for drfbtion of DOM nodfs in WRAP bnd ENCRYPT modfs */
    privbtf Dodumfnt dontfxtDodumfnt;

    /** Instbndf of fbdtory usfd to drfbtf XML Endryption objfdts */
    privbtf Fbdtory fbdtory;

    /** Sfriblizfr dlbss for going to/from UTF-8 */
    privbtf Sfriblizfr sfriblizfr;

    /** Lodbl dopy of usfr's kfy */
    privbtf Kfy kfy;

    /** Lodbl dopy of tif kfk (usfd to dfdrypt EndryptfdKfys during b
     *  DECRYPT_MODE opfrbtion */
    privbtf Kfy kfk;

    // Tif EndryptfdKfy bfing built (pbrt of b WRAP opfrbtion) or rfbd
    // (pbrt of bn UNWRAP opfrbtion)
    privbtf EndryptfdKfy fk;

    // Tif EndryptfdDbtb bfing built (pbrt of b WRAP opfrbtion) or rfbd
    // (pbrt of bn UNWRAP opfrbtion)
    privbtf EndryptfdDbtb fd;

    privbtf SfdurfRbndom rbndom;

    privbtf boolfbn sfdurfVblidbtion;

    privbtf String digfstAlg;

    /** List of intfrnbl KfyRfsolvfrs for DECRYPT bnd UNWRAP modfs. */
    privbtf List<KfyRfsolvfrSpi> intfrnblKfyRfsolvfrs;

    /**
     * Sft tif Sfriblizfr blgoritim to usf
     */
    publid void sftSfriblizfr(Sfriblizfr sfriblizfr) {
        tiis.sfriblizfr = sfriblizfr;
        sfriblizfr.sftCbnonidblizfr(tiis.dbnon);
    }

    /**
     * Gft tif Sfriblizfr blgoritim to usf
     */
    publid Sfriblizfr gftSfriblizfr() {
        rfturn sfriblizfr;
    }

    /**
     * Crfbtfs b nfw <dodf>XMLCipifr</dodf>.
     *
     * @pbrbm trbnsformbtion    tif nbmf of tif trbnsformbtion, f.g.,
     *                          <dodf>XMLCipifr.TRIPLEDES</dodf>. If null tif XMLCipifr dbn only
     *                          bf usfd for dfdrypt or unwrbp opfrbtions wifrf tif fndryption mftiod
     *                          is dffinfd in tif <dodf>EndryptionMftiod</dodf> flfmfnt.
     * @pbrbm providfr          tif JCE providfr tibt supplifs tif trbnsformbtion,
     *                          if null usf tif dffbult providfr.
     * @pbrbm dbnon             tif nbmf of tif d14n blgoritim, if
     *                          <dodf>null</dodf> usf stbndbrd sfriblizfr
     * @pbrbm digfstMftiod      An optionbl digfstMftiod to usf.
     */
    privbtf XMLCipifr(
        String trbnsformbtion,
        String providfr,
        String dbnonAlg,
        String digfstMftiod
    ) tirows XMLEndryptionExdfption {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Construdting XMLCipifr...");
        }

        fbdtory = nfw Fbdtory();

        blgoritim = trbnsformbtion;
        rfqufstfdJCEProvidfr = providfr;
        digfstAlg = digfstMftiod;

        // Crfbtf b dbnonidblizfr - usfd wifn sfriblizing DOM to odtfts
        // prior to fndryption (bnd for tif rfvfrsf)

        try {
            if (dbnonAlg == null) {
                // Tif dffbult is to prfsfrvf tif piysidbl rfprfsfntbtion.
                tiis.dbnon = Cbnonidblizfr.gftInstbndf(Cbnonidblizfr.ALGO_ID_C14N_PHYSICAL);
            } flsf {
                tiis.dbnon = Cbnonidblizfr.gftInstbndf(dbnonAlg);
            }
        } dbtdi (InvblidCbnonidblizfrExdfption idf) {
            tirow nfw XMLEndryptionExdfption("fmpty", idf);
        }

        if (sfriblizfr == null) {
            sfriblizfr = nfw DodumfntSfriblizfr();
        }
        sfriblizfr.sftCbnonidblizfr(tiis.dbnon);

        if (trbnsformbtion != null) {
            dontfxtCipifr = donstrudtCipifr(trbnsformbtion, digfstMftiod);
        }
    }

    /**
     * Cifdks to fnsurf tibt tif supplifd blgoritim is vblid.
     *
     * @pbrbm blgoritim tif blgoritim to difdk.
     * @rfturn truf if tif blgoritim is vblid, otifrwisf fblsf.
     * @sindf 1.0.
     */
    privbtf stbtid boolfbn isVblidEndryptionAlgoritim(String blgoritim) {
        rfturn (
            blgoritim.fqubls(TRIPLEDES) ||
            blgoritim.fqubls(AES_128) ||
            blgoritim.fqubls(AES_256) ||
            blgoritim.fqubls(AES_192) ||
            blgoritim.fqubls(AES_128_GCM) ||
            blgoritim.fqubls(AES_192_GCM) ||
            blgoritim.fqubls(AES_256_GCM) ||
            blgoritim.fqubls(RSA_v1dot5) ||
            blgoritim.fqubls(RSA_OAEP) ||
            blgoritim.fqubls(RSA_OAEP_11) ||
            blgoritim.fqubls(TRIPLEDES_KfyWrbp) ||
            blgoritim.fqubls(AES_128_KfyWrbp) ||
            blgoritim.fqubls(AES_256_KfyWrbp) ||
            blgoritim.fqubls(AES_192_KfyWrbp)
        );
    }

    /**
     * Vblidbtf tif trbnsformbtion brgumfnt of gftInstbndf or gftProvidfrInstbndf
     *
     * @pbrbm trbnsformbtion tif nbmf of tif trbnsformbtion, f.g.,
     *   <dodf>XMLCipifr.TRIPLEDES</dodf> wiidi is siortibnd for
     *   &quot;ittp://www.w3.org/2001/04/xmlfnd#triplfdfs-dbd&quot;
     */
    privbtf stbtid void vblidbtfTrbnsformbtion(String trbnsformbtion) {
        if (null == trbnsformbtion) {
            tirow nfw NullPointfrExdfption("Trbnsformbtion unfxpfdtfdly null...");
        }
        if (!isVblidEndryptionAlgoritim(trbnsformbtion)) {
            log.log(jbvb.util.logging.Lfvfl.WARNING, "Algoritim non-stbndbrd, fxpfdtfd onf of " + ENC_ALGORITHMS);
        }
    }

    /**
     * Rfturns bn <dodf>XMLCipifr</dodf> tibt implfmfnts tif spfdififd
     * trbnsformbtion bnd opfrbtfs on tif spfdififd dontfxt dodumfnt.
     * <p>
     * If tif dffbult providfr pbdkbgf supplifs bn implfmfntbtion of tif
     * rfqufstfd trbnsformbtion, bn instbndf of Cipifr dontbining tibt
     * implfmfntbtion is rfturnfd. If tif trbnsformbtion is not bvbilbblf in
     * tif dffbult providfr pbdkbgf, otifr providfr pbdkbgfs brf sfbrdifd.
     * <p>
     * <b>NOTE<sub>1</sub>:</b> Tif trbnsformbtion nbmf dofs not follow tif sbmf
     * pbttfrn bs tibt outlinfd in tif Jbvb Cryptogrbpiy Extfnsion Rfffrfndf
     * Guidf but rbtifr tibt spfdififd by tif XML Endryption Syntbx bnd
     * Prodfssing dodumfnt. Tif rbtionbl bfiind tiis is to mbkf it fbsifr for b
     * novidf bt writing Jbvb Endryption softwbrf to usf tif librbry.
     * <p>
     * <b>NOTE<sub>2</sub>:</b> <dodf>gftInstbndf()</dodf> dofs not follow tif
     * sbmf pbttfrn rfgbrding fxdfptionbl donditions bs tibt usfd in
     * <dodf>jbvbx.drypto.Cipifr</dodf>. Instfbd, it only tirows bn
     * <dodf>XMLEndryptionExdfption</dodf> wiidi wrbps bn undfrlying fxdfption.
     * Tif stbdk trbdf from tif fxdfption siould bf sflf fxplbnbtory.
     *
     * @pbrbm trbnsformbtion tif nbmf of tif trbnsformbtion, f.g.,
     *   <dodf>XMLCipifr.TRIPLEDES</dodf> wiidi is siortibnd for
     *   &quot;ittp://www.w3.org/2001/04/xmlfnd#triplfdfs-dbd&quot;
     * @tirows XMLEndryptionExdfption
     * @rfturn tif XMLCipifr
     * @sff jbvbx.drypto.Cipifr#gftInstbndf(jbvb.lbng.String)
     */
    publid stbtid XMLCipifr gftInstbndf(String trbnsformbtion) tirows XMLEndryptionExdfption {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Gftting XMLCipifr witi trbnsformbtion");
        }
        vblidbtfTrbnsformbtion(trbnsformbtion);
        rfturn nfw XMLCipifr(trbnsformbtion, null, null, null);
    }

    /**
     * Rfturns bn <dodf>XMLCipifr</dodf> tibt implfmfnts tif spfdififd
     * trbnsformbtion, opfrbtfs on tif spfdififd dontfxt dodumfnt bnd sfriblizfs
     * tif dodumfnt witi tif spfdififd dbnonidblizbtion blgoritim bfforf it
     * fndrypts tif dodumfnt.
     * <p>
     *
     * @pbrbm trbnsformbtion    tif nbmf of tif trbnsformbtion
     * @pbrbm dbnon             tif nbmf of tif d14n blgoritim, if <dodf>null</dodf> usf
     *                          stbndbrd sfriblizfr
     * @rfturn tif XMLCipifr
     * @tirows XMLEndryptionExdfption
     */
    publid stbtid XMLCipifr gftInstbndf(String trbnsformbtion, String dbnon)
        tirows XMLEndryptionExdfption {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Gftting XMLCipifr witi trbnsformbtion bnd d14n blgoritim");
        }
        vblidbtfTrbnsformbtion(trbnsformbtion);
        rfturn nfw XMLCipifr(trbnsformbtion, null, dbnon, null);
    }

    /**
     * Rfturns bn <dodf>XMLCipifr</dodf> tibt implfmfnts tif spfdififd
     * trbnsformbtion, opfrbtfs on tif spfdififd dontfxt dodumfnt bnd sfriblizfs
     * tif dodumfnt witi tif spfdififd dbnonidblizbtion blgoritim bfforf it
     * fndrypts tif dodumfnt.
     * <p>
     *
     * @pbrbm trbnsformbtion    tif nbmf of tif trbnsformbtion
     * @pbrbm dbnon             tif nbmf of tif d14n blgoritim, if <dodf>null</dodf> usf
     *                          stbndbrd sfriblizfr
     * @pbrbm digfstMftiod      An optionbl digfstMftiod to usf
     * @rfturn tif XMLCipifr
     * @tirows XMLEndryptionExdfption
     */
    publid stbtid XMLCipifr gftInstbndf(String trbnsformbtion, String dbnon, String digfstMftiod)
        tirows XMLEndryptionExdfption {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Gftting XMLCipifr witi trbnsformbtion bnd d14n blgoritim");
        }
        vblidbtfTrbnsformbtion(trbnsformbtion);
        rfturn nfw XMLCipifr(trbnsformbtion, null, dbnon, digfstMftiod);
    }

    /**
     * Rfturns bn <dodf>XMLCipifr</dodf> tibt implfmfnts tif spfdififd
     * trbnsformbtion bnd opfrbtfs on tif spfdififd dontfxt dodumfnt.
     *
     * @pbrbm trbnsformbtion    tif nbmf of tif trbnsformbtion
     * @pbrbm providfr          tif JCE providfr tibt supplifs tif trbnsformbtion
     * @rfturn tif XMLCipifr
     * @tirows XMLEndryptionExdfption
     */
    publid stbtid XMLCipifr gftProvidfrInstbndf(String trbnsformbtion, String providfr)
        tirows XMLEndryptionExdfption {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Gftting XMLCipifr witi trbnsformbtion bnd providfr");
        }
        if (null == providfr) {
            tirow nfw NullPointfrExdfption("Providfr unfxpfdtfdly null..");
        }
        vblidbtfTrbnsformbtion(trbnsformbtion);
        rfturn nfw XMLCipifr(trbnsformbtion, providfr, null, null);
    }

    /**
     * Rfturns bn <dodf>XMLCipifr</dodf> tibt implfmfnts tif spfdififd
     * trbnsformbtion, opfrbtfs on tif spfdififd dontfxt dodumfnt bnd sfriblizfs
     * tif dodumfnt witi tif spfdififd dbnonidblizbtion blgoritim bfforf it
     * fndrypts tif dodumfnt.
     * <p>
     *
     * @pbrbm trbnsformbtion    tif nbmf of tif trbnsformbtion
     * @pbrbm providfr          tif JCE providfr tibt supplifs tif trbnsformbtion
     * @pbrbm dbnon             tif nbmf of tif d14n blgoritim, if <dodf>null</dodf> usf stbndbrd
     *                          sfriblizfr
     * @rfturn tif XMLCipifr
     * @tirows XMLEndryptionExdfption
     */
    publid stbtid XMLCipifr gftProvidfrInstbndf(
        String trbnsformbtion, String providfr, String dbnon
    ) tirows XMLEndryptionExdfption {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Gftting XMLCipifr witi trbnsformbtion, providfr bnd d14n blgoritim");
        }
        if (null == providfr) {
            tirow nfw NullPointfrExdfption("Providfr unfxpfdtfdly null..");
        }
        vblidbtfTrbnsformbtion(trbnsformbtion);
        rfturn nfw XMLCipifr(trbnsformbtion, providfr, dbnon, null);
    }

    /**
     * Rfturns bn <dodf>XMLCipifr</dodf> tibt implfmfnts tif spfdififd
     * trbnsformbtion, opfrbtfs on tif spfdififd dontfxt dodumfnt bnd sfriblizfs
     * tif dodumfnt witi tif spfdififd dbnonidblizbtion blgoritim bfforf it
     * fndrypts tif dodumfnt.
     * <p>
     *
     * @pbrbm trbnsformbtion    tif nbmf of tif trbnsformbtion
     * @pbrbm providfr          tif JCE providfr tibt supplifs tif trbnsformbtion
     * @pbrbm dbnon             tif nbmf of tif d14n blgoritim, if <dodf>null</dodf> usf stbndbrd
     *                          sfriblizfr
     * @pbrbm digfstMftiod      An optionbl digfstMftiod to usf
     * @rfturn tif XMLCipifr
     * @tirows XMLEndryptionExdfption
     */
    publid stbtid XMLCipifr gftProvidfrInstbndf(
        String trbnsformbtion, String providfr, String dbnon, String digfstMftiod
    ) tirows XMLEndryptionExdfption {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Gftting XMLCipifr witi trbnsformbtion, providfr bnd d14n blgoritim");
        }
        if (null == providfr) {
            tirow nfw NullPointfrExdfption("Providfr unfxpfdtfdly null..");
        }
        vblidbtfTrbnsformbtion(trbnsformbtion);
        rfturn nfw XMLCipifr(trbnsformbtion, providfr, dbnon, digfstMftiod);
    }

    /**
     * Rfturns bn <dodf>XMLCipifr</dodf> tibt implfmfnts no spfdifid
     * trbnsformbtion, bnd dbn tifrfforf only bf usfd for dfdrypt or
     * unwrbp opfrbtions wifrf tif fndryption mftiod is dffinfd in tif
     * <dodf>EndryptionMftiod</dodf> flfmfnt.
     *
     * @rfturn Tif XMLCipifr
     * @tirows XMLEndryptionExdfption
     */
    publid stbtid XMLCipifr gftInstbndf() tirows XMLEndryptionExdfption {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Gftting XMLCipifr witi no brgumfnts");
        }
        rfturn nfw XMLCipifr(null, null, null, null);
    }

    /**
     * Rfturns bn <dodf>XMLCipifr</dodf> tibt implfmfnts no spfdifid
     * trbnsformbtion, bnd dbn tifrfforf only bf usfd for dfdrypt or
     * unwrbp opfrbtions wifrf tif fndryption mftiod is dffinfd in tif
     * <dodf>EndryptionMftiod</dodf> flfmfnt.
     *
     * Allows tif dbllfr to spfdify b providfr tibt will bf usfd for
     * dryptogrbpiid opfrbtions.
     *
     * @pbrbm providfr          tif JCE providfr tibt supplifs tif trbnsformbtion
     * @rfturn tif XMLCipifr
     * @tirows XMLEndryptionExdfption
     */
    publid stbtid XMLCipifr gftProvidfrInstbndf(String providfr) tirows XMLEndryptionExdfption {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Gftting XMLCipifr witi providfr");
        }
        rfturn nfw XMLCipifr(null, providfr, null, null);
    }

    /**
     * Initiblizfs tiis dipifr witi b kfy.
     * <p>
     * Tif dipifr is initiblizfd for onf of tif following four opfrbtions:
     * fndryption, dfdryption, kfy wrbpping or kfy unwrbpping, dfpfnding on tif
     * vbluf of opmodf.
     *
     * For WRAP bnd ENCRYPT modfs, tiis blso initiblisfs tif intfrnbl
     * EndryptfdKfy or EndryptfdDbtb (witi b CipifrVbluf)
     * strudturf tibt will bf usfd during tif fnsuing opfrbtions.  Tiis
     * dbn bf obtbinfd (in ordfr to modify KfyInfo flfmfnts ftd. prior to
     * finblising tif fndryption) by dblling
     * {@link #gftEndryptfdDbtb} or {@link #gftEndryptfdKfy}.
     *
     * @pbrbm opmodf tif opfrbtion modf of tiis dipifr (tiis is onf of tif
     *   following: ENCRYPT_MODE, DECRYPT_MODE, WRAP_MODE or UNWRAP_MODE)
     * @pbrbm kfy
     * @sff jbvbx.drypto.Cipifr#init(int, jbvb.sfdurity.Kfy)
     * @tirows XMLEndryptionExdfption
     */
    publid void init(int opmodf, Kfy kfy) tirows XMLEndryptionExdfption {
        // sbnity difdks
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Initiblizing XMLCipifr...");
        }

        fk = null;
        fd = null;

        switdi (opmodf) {

        dbsf ENCRYPT_MODE :
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "opmodf = ENCRYPT_MODE");
            }
            fd = drfbtfEndryptfdDbtb(CipifrDbtb.VALUE_TYPE, "NO VALUE YET");
            brfbk;
        dbsf DECRYPT_MODE :
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "opmodf = DECRYPT_MODE");
            }
            brfbk;
        dbsf WRAP_MODE :
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "opmodf = WRAP_MODE");
            }
            fk = drfbtfEndryptfdKfy(CipifrDbtb.VALUE_TYPE, "NO VALUE YET");
            brfbk;
        dbsf UNWRAP_MODE :
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "opmodf = UNWRAP_MODE");
            }
            brfbk;
        dffbult :
            log.log(jbvb.util.logging.Lfvfl.SEVERE, "Modf unfxpfdtfdly invblid");
            tirow nfw XMLEndryptionExdfption("Invblid modf in init");
        }

        dipifrModf = opmodf;
        tiis.kfy = kfy;
    }

    /**
     * Sft wiftifr sfdurf vblidbtion is fnbblfd or not. Tif dffbult is fblsf.
     */
    publid void sftSfdurfVblidbtion(boolfbn sfdurfVblidbtion) {
        tiis.sfdurfVblidbtion = sfdurfVblidbtion;
    }

    /**
     * Tiis mftiod is usfd to bdd b dustom {@link KfyRfsolvfrSpi} to bn XMLCipifr.
     * Tifsf KfyRfsolvfrs brf usfd in KfyInfo objfdts in DECRYPT bnd
     * UNWRAP modfs.
     *
     * @pbrbm kfyRfsolvfr
     */
    publid void rfgistfrIntfrnblKfyRfsolvfr(KfyRfsolvfrSpi kfyRfsolvfr) {
        if (intfrnblKfyRfsolvfrs == null) {
            intfrnblKfyRfsolvfrs = nfw ArrbyList<KfyRfsolvfrSpi>();
        }
        intfrnblKfyRfsolvfrs.bdd(kfyRfsolvfr);
    }

    /**
     * Gft tif EndryptfdDbtb bfing built
     * <p>
     * Rfturns tif EndryptfdDbtb bfing built during bn ENCRYPT opfrbtion.
     * Tiis dbn tifn bf usfd by bpplidbtions to bdd KfyInfo flfmfnts bnd
     * sft otifr pbrbmftfrs.
     *
     * @rfturn Tif EndryptfdDbtb bfing built
     */
    publid EndryptfdDbtb gftEndryptfdDbtb() {
        // Sbnity difdks
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Rfturning EndryptfdDbtb");
        }
        rfturn fd;
    }

    /**
     * Gft tif EndryptfdDbtb bfing build
     *
     * Rfturns tif EndryptfdDbtb bfing built during bn ENCRYPT opfrbtion.
     * Tiis dbn tifn bf usfd by bpplidbtions to bdd KfyInfo flfmfnts bnd
     * sft otifr pbrbmftfrs.
     *
     * @rfturn Tif EndryptfdDbtb bfing built
     */
    publid EndryptfdKfy gftEndryptfdKfy() {
        // Sbnity difdks
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Rfturning EndryptfdKfy");
        }
        rfturn fk;
    }

    /**
     * Sft b Kfy Endryption Kfy.
     * <p>
     * Tif Kfy Endryption Kfy (KEK) is usfd for fndrypting/dfdrypting
     * EndryptfdKfy flfmfnts.  By sftting tiis sfpbrbtfly, tif XMLCipifr
     * dlbss dbn know wiftifr b kfy bpplifs to tif dbtb pbrt or wrbppfd kfy
     * pbrt of bn fndryptfd objfdt.
     *
     * @pbrbm kfk Tif kfy to usf for df/fndrypting kfy dbtb
     */

    publid void sftKEK(Kfy kfk) {
        tiis.kfk = kfk;
    }

    /**
     * Mbrtibl bn EndryptfdDbtb
     *
     * Tbkfs bn EndryptfdDbtb objfdt bnd rfturns b DOM Elfmfnt tibt
     * rfprfsfnts tif bppropribtf <dodf>EndryptfdDbtb</dodf>
     * <p>
     * <b>Notf:</b> Tiis siould only bf usfd in dbsfs wifrf tif dontfxt
     * dodumfnt ibs bffn pbssfd in vib b dbll to doFinbl.
     *
     * @pbrbm fndryptfdDbtb EndryptfdDbtb objfdt to mbrtibl
     * @rfturn tif DOM <dodf>Elfmfnt</dodf> rfprfsfnting tif pbssfd in
     * objfdt
     */
    publid Elfmfnt mbrtibl(EndryptfdDbtb fndryptfdDbtb) {
        rfturn fbdtory.toElfmfnt(fndryptfdDbtb);
    }

    /**
     * Mbrtibl bn EndryptfdDbtb
     *
     * Tbkfs bn EndryptfdDbtb objfdt bnd rfturns b DOM Elfmfnt tibt
     * rfprfsfnts tif bppropribtf <dodf>EndryptfdDbtb</dodf>
     *
     * @pbrbm dontfxt Tif dodumfnt tibt will own tif rfturnfd nodfs
     * @pbrbm fndryptfdDbtb EndryptfdDbtb objfdt to mbrtibl
     * @rfturn tif DOM <dodf>Elfmfnt</dodf> rfprfsfnting tif pbssfd in
     * objfdt
     */
    publid Elfmfnt mbrtibl(Dodumfnt dontfxt, EndryptfdDbtb fndryptfdDbtb) {
        dontfxtDodumfnt = dontfxt;
        rfturn fbdtory.toElfmfnt(fndryptfdDbtb);
    }

    /**
     * Mbrtibl bn EndryptfdKfy
     *
     * Tbkfs bn EndryptfdKfy objfdt bnd rfturns b DOM Elfmfnt tibt
     * rfprfsfnts tif bppropribtf <dodf>EndryptfdKfy</dodf>
     *
     * <p>
     * <b>Notf:</b> Tiis siould only bf usfd in dbsfs wifrf tif dontfxt
     * dodumfnt ibs bffn pbssfd in vib b dbll to doFinbl.
     *
     * @pbrbm fndryptfdKfy EndryptfdKfy objfdt to mbrtibl
     * @rfturn tif DOM <dodf>Elfmfnt</dodf> rfprfsfnting tif pbssfd in
     * objfdt
     */
    publid Elfmfnt mbrtibl(EndryptfdKfy fndryptfdKfy) {
        rfturn fbdtory.toElfmfnt(fndryptfdKfy);
    }

    /**
     * Mbrtibl bn EndryptfdKfy
     *
     * Tbkfs bn EndryptfdKfy objfdt bnd rfturns b DOM Elfmfnt tibt
     * rfprfsfnts tif bppropribtf <dodf>EndryptfdKfy</dodf>
     *
     * @pbrbm dontfxt Tif dodumfnt tibt will own tif drfbtfd nodfs
     * @pbrbm fndryptfdKfy EndryptfdKfy objfdt to mbrtibl
     * @rfturn tif DOM <dodf>Elfmfnt</dodf> rfprfsfnting tif pbssfd in
     * objfdt
     */
    publid Elfmfnt mbrtibl(Dodumfnt dontfxt, EndryptfdKfy fndryptfdKfy) {
        dontfxtDodumfnt = dontfxt;
        rfturn fbdtory.toElfmfnt(fndryptfdKfy);
    }

    /**
     * Mbrtibl b RfffrfndfList
     *
     * Tbkfs b RfffrfndfList objfdt bnd rfturns b DOM Elfmfnt tibt
     * rfprfsfnts tif bppropribtf <dodf>RfffrfndfList</dodf>
     *
     * <p>
     * <b>Notf:</b> Tiis siould only bf usfd in dbsfs wifrf tif dontfxt
     * dodumfnt ibs bffn pbssfd in vib b dbll to doFinbl.
     *
     * @pbrbm rfffrfndfList RfffrfndfList objfdt to mbrtibl
     * @rfturn tif DOM <dodf>Elfmfnt</dodf> rfprfsfnting tif pbssfd in
     * objfdt
     */
    publid Elfmfnt mbrtibl(RfffrfndfList rfffrfndfList) {
        rfturn fbdtory.toElfmfnt(rfffrfndfList);
    }

    /**
     * Mbrtibl b RfffrfndfList
     *
     * Tbkfs b RfffrfndfList objfdt bnd rfturns b DOM Elfmfnt tibt
     * rfprfsfnts tif bppropribtf <dodf>RfffrfndfList</dodf>
     *
     * @pbrbm dontfxt Tif dodumfnt tibt will own tif drfbtfd nodfs
     * @pbrbm rfffrfndfList RfffrfndfList objfdt to mbrtibl
     * @rfturn tif DOM <dodf>Elfmfnt</dodf> rfprfsfnting tif pbssfd in
     * objfdt
     */
    publid Elfmfnt mbrtibl(Dodumfnt dontfxt, RfffrfndfList rfffrfndfList) {
        dontfxtDodumfnt = dontfxt;
        rfturn fbdtory.toElfmfnt(rfffrfndfList);
    }

    /**
     * Endrypts bn <dodf>Elfmfnt</dodf> bnd rfplbdfs it witi its fndryptfd
     * dountfrpbrt in tif dontfxt <dodf>Dodumfnt</dodf>, tibt is, tif
     * <dodf>Dodumfnt</dodf> spfdififd wifn onf dblls
     * {@link #gftInstbndf(String) gftInstbndf}.
     *
     * @pbrbm flfmfnt tif <dodf>Elfmfnt</dodf> to fndrypt.
     * @rfturn tif dontfxt <dodf>Dodumfnt</dodf> witi tif fndryptfd
     *   <dodf>Elfmfnt</dodf> ibving rfplbdfd tif sourdf <dodf>Elfmfnt</dodf>.
     *  @tirows Exdfption
     */
    privbtf Dodumfnt fndryptElfmfnt(Elfmfnt flfmfnt) tirows Exdfption{
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Endrypting flfmfnt...");
        }
        if (null == flfmfnt) {
            log.log(jbvb.util.logging.Lfvfl.SEVERE, "Elfmfnt unfxpfdtfdly null...");
        }
        if (dipifrModf != ENCRYPT_MODE && log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "XMLCipifr unfxpfdtfdly not in ENCRYPT_MODE...");
        }

        if (blgoritim == null) {
            tirow nfw XMLEndryptionExdfption("XMLCipifr instbndf witiout trbnsformbtion spfdififd");
        }
        fndryptDbtb(dontfxtDodumfnt, flfmfnt, fblsf);

        Elfmfnt fndryptfdElfmfnt = fbdtory.toElfmfnt(fd);

        Nodf sourdfPbrfnt = flfmfnt.gftPbrfntNodf();
        sourdfPbrfnt.rfplbdfCiild(fndryptfdElfmfnt, flfmfnt);

        rfturn dontfxtDodumfnt;
    }

    /**
     * Endrypts b <dodf>NodfList</dodf> (tif dontfnts of bn
     * <dodf>Elfmfnt</dodf>) bnd rfplbdfs its pbrfnt <dodf>Elfmfnt</dodf>'s
     * dontfnt witi tiis tif rfsulting <dodf>EndryptfdTypf</dodf> witiin tif
     * dontfxt <dodf>Dodumfnt</dodf>, tibt is, tif <dodf>Dodumfnt</dodf>
     * spfdififd wifn onf dblls
     * {@link #gftInstbndf(String) gftInstbndf}.
     *
     * @pbrbm flfmfnt tif <dodf>NodfList</dodf> to fndrypt.
     * @rfturn tif dontfxt <dodf>Dodumfnt</dodf> witi tif fndryptfd
     *   <dodf>NodfList</dodf> ibving rfplbdfd tif dontfnt of tif sourdf
     *   <dodf>Elfmfnt</dodf>.
     * @tirows Exdfption
     */
    privbtf Dodumfnt fndryptElfmfntContfnt(Elfmfnt flfmfnt) tirows /* XMLEndryption */Exdfption {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Endrypting flfmfnt dontfnt...");
        }
        if (null == flfmfnt) {
            log.log(jbvb.util.logging.Lfvfl.SEVERE, "Elfmfnt unfxpfdtfdly null...");
        }
        if (dipifrModf != ENCRYPT_MODE && log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "XMLCipifr unfxpfdtfdly not in ENCRYPT_MODE...");
        }

        if (blgoritim == null) {
            tirow nfw XMLEndryptionExdfption("XMLCipifr instbndf witiout trbnsformbtion spfdififd");
        }
        fndryptDbtb(dontfxtDodumfnt, flfmfnt, truf);

        Elfmfnt fndryptfdElfmfnt = fbdtory.toElfmfnt(fd);

        rfmovfContfnt(flfmfnt);
        flfmfnt.bppfndCiild(fndryptfdElfmfnt);

        rfturn dontfxtDodumfnt;
    }

    /**
     * Prodfss b DOM <dodf>Dodumfnt</dodf> nodf. Tif prodfssing dfpfnds on tif
     * initiblizbtion pbrbmftfrs of {@link #init(int, Kfy) init()}.
     *
     * @pbrbm dontfxt tif dontfxt <dodf>Dodumfnt</dodf>.
     * @pbrbm sourdf tif <dodf>Dodumfnt</dodf> to bf fndryptfd or dfdryptfd.
     * @rfturn tif prodfssfd <dodf>Dodumfnt</dodf>.
     * @tirows Exdfption to indidbtf bny fxdfptionbl donditions.
     */
    publid Dodumfnt doFinbl(Dodumfnt dontfxt, Dodumfnt sourdf) tirows /* XMLEndryption */Exdfption {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Prodfssing sourdf dodumfnt...");
        }
        if (null == dontfxt) {
            log.log(jbvb.util.logging.Lfvfl.SEVERE, "Contfxt dodumfnt unfxpfdtfdly null...");
        }
        if (null == sourdf) {
            log.log(jbvb.util.logging.Lfvfl.SEVERE, "Sourdf dodumfnt unfxpfdtfdly null...");
        }

        dontfxtDodumfnt = dontfxt;

        Dodumfnt rfsult = null;

        switdi (dipifrModf) {
        dbsf DECRYPT_MODE:
            rfsult = dfdryptElfmfnt(sourdf.gftDodumfntElfmfnt());
            brfbk;
        dbsf ENCRYPT_MODE:
            rfsult = fndryptElfmfnt(sourdf.gftDodumfntElfmfnt());
            brfbk;
        dbsf UNWRAP_MODE:
        dbsf WRAP_MODE:
            brfbk;
        dffbult:
            tirow nfw XMLEndryptionExdfption("fmpty", nfw IllfgblStbtfExdfption());
        }

        rfturn rfsult;
    }

    /**
     * Prodfss b DOM <dodf>Elfmfnt</dodf> nodf. Tif prodfssing dfpfnds on tif
     * initiblizbtion pbrbmftfrs of {@link #init(int, Kfy) init()}.
     *
     * @pbrbm dontfxt tif dontfxt <dodf>Dodumfnt</dodf>.
     * @pbrbm flfmfnt tif <dodf>Elfmfnt</dodf> to bf fndryptfd.
     * @rfturn tif prodfssfd <dodf>Dodumfnt</dodf>.
     * @tirows Exdfption to indidbtf bny fxdfptionbl donditions.
     */
    publid Dodumfnt doFinbl(Dodumfnt dontfxt, Elfmfnt flfmfnt) tirows /* XMLEndryption */Exdfption {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Prodfssing sourdf flfmfnt...");
        }
        if (null == dontfxt) {
            log.log(jbvb.util.logging.Lfvfl.SEVERE, "Contfxt dodumfnt unfxpfdtfdly null...");
        }
        if (null == flfmfnt) {
            log.log(jbvb.util.logging.Lfvfl.SEVERE, "Sourdf flfmfnt unfxpfdtfdly null...");
        }

        dontfxtDodumfnt = dontfxt;

        Dodumfnt rfsult = null;

        switdi (dipifrModf) {
        dbsf DECRYPT_MODE:
            rfsult = dfdryptElfmfnt(flfmfnt);
            brfbk;
        dbsf ENCRYPT_MODE:
            rfsult = fndryptElfmfnt(flfmfnt);
            brfbk;
        dbsf UNWRAP_MODE:
        dbsf WRAP_MODE:
            brfbk;
        dffbult:
            tirow nfw XMLEndryptionExdfption("fmpty", nfw IllfgblStbtfExdfption());
        }

        rfturn rfsult;
    }

    /**
     * Prodfss tif dontfnts of b DOM <dodf>Elfmfnt</dodf> nodf. Tif prodfssing
     * dfpfnds on tif initiblizbtion pbrbmftfrs of
     * {@link #init(int, Kfy) init()}.
     *
     * @pbrbm dontfxt tif dontfxt <dodf>Dodumfnt</dodf>.
     * @pbrbm flfmfnt tif <dodf>Elfmfnt</dodf> wiidi dontfnts is to bf
     *   fndryptfd.
     * @pbrbm dontfnt
     * @rfturn tif prodfssfd <dodf>Dodumfnt</dodf>.
     * @tirows Exdfption to indidbtf bny fxdfptionbl donditions.
     */
    publid Dodumfnt doFinbl(Dodumfnt dontfxt, Elfmfnt flfmfnt, boolfbn dontfnt)
        tirows /* XMLEndryption*/ Exdfption {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Prodfssing sourdf flfmfnt...");
        }
        if (null == dontfxt) {
            log.log(jbvb.util.logging.Lfvfl.SEVERE, "Contfxt dodumfnt unfxpfdtfdly null...");
        }
        if (null == flfmfnt) {
            log.log(jbvb.util.logging.Lfvfl.SEVERE, "Sourdf flfmfnt unfxpfdtfdly null...");
        }

        dontfxtDodumfnt = dontfxt;

        Dodumfnt rfsult = null;

        switdi (dipifrModf) {
        dbsf DECRYPT_MODE:
            if (dontfnt) {
                rfsult = dfdryptElfmfntContfnt(flfmfnt);
            } flsf {
                rfsult = dfdryptElfmfnt(flfmfnt);
            }
            brfbk;
        dbsf ENCRYPT_MODE:
            if (dontfnt) {
                rfsult = fndryptElfmfntContfnt(flfmfnt);
            } flsf {
                rfsult = fndryptElfmfnt(flfmfnt);
            }
            brfbk;
        dbsf UNWRAP_MODE:
        dbsf WRAP_MODE:
            brfbk;
        dffbult:
            tirow nfw XMLEndryptionExdfption("fmpty", nfw IllfgblStbtfExdfption());
        }

        rfturn rfsult;
    }

    /**
     * Rfturns bn <dodf>EndryptfdDbtb</dodf> intfrfbdf. Usf tiis opfrbtion if
     * you wbnt to ibvf full dontrol ovfr tif dontfnts of tif
     * <dodf>EndryptfdDbtb</dodf> strudturf.
     *
     * Tiis dofs not dibngf tif sourdf dodumfnt in bny wby.
     *
     * @pbrbm dontfxt tif dontfxt <dodf>Dodumfnt</dodf>.
     * @pbrbm flfmfnt tif <dodf>Elfmfnt</dodf> tibt will bf fndryptfd.
     * @rfturn tif <dodf>EndryptfdDbtb</dodf>
     * @tirows Exdfption
     */
    publid EndryptfdDbtb fndryptDbtb(Dodumfnt dontfxt, Elfmfnt flfmfnt) tirows
        /* XMLEndryption */Exdfption {
        rfturn fndryptDbtb(dontfxt, flfmfnt, fblsf);
    }

    /**
     * Rfturns bn <dodf>EndryptfdDbtb</dodf> intfrfbdf. Usf tiis opfrbtion if
     * you wbnt to ibvf full dontrol ovfr tif sfriblizbtion of tif flfmfnt
     * or flfmfnt dontfnt.
     *
     * Tiis dofs not dibngf tif sourdf dodumfnt in bny wby.
     *
     * @pbrbm dontfxt tif dontfxt <dodf>Dodumfnt</dodf>.
     * @pbrbm typf b URI idfntifying typf informbtion bbout tif plbintfxt form
     *    of tif fndryptfd dontfnt (mby bf <dodf>null</dodf>)
     * @pbrbm sfriblizfdDbtb tif sfriblizfd dbtb
     * @rfturn tif <dodf>EndryptfdDbtb</dodf>
     * @tirows Exdfption
     */
    publid EndryptfdDbtb fndryptDbtb(
        Dodumfnt dontfxt, String typf, InputStrfbm sfriblizfdDbtb
    ) tirows Exdfption {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Endrypting flfmfnt...");
        }
        if (null == dontfxt) {
            log.log(jbvb.util.logging.Lfvfl.SEVERE, "Contfxt dodumfnt unfxpfdtfdly null...");
        }
        if (null == sfriblizfdDbtb) {
            log.log(jbvb.util.logging.Lfvfl.SEVERE, "Sfriblizfd dbtb unfxpfdtfdly null...");
        }
        if (dipifrModf != ENCRYPT_MODE && log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "XMLCipifr unfxpfdtfdly not in ENCRYPT_MODE...");
        }

        rfturn fndryptDbtb(dontfxt, null, typf, sfriblizfdDbtb);
    }

    /**
     * Rfturns bn <dodf>EndryptfdDbtb</dodf> intfrfbdf. Usf tiis opfrbtion if
     * you wbnt to ibvf full dontrol ovfr tif dontfnts of tif
     * <dodf>EndryptfdDbtb</dodf> strudturf.
     *
     * Tiis dofs not dibngf tif sourdf dodumfnt in bny wby.
     *
     * @pbrbm dontfxt tif dontfxt <dodf>Dodumfnt</dodf>.
     * @pbrbm flfmfnt tif <dodf>Elfmfnt</dodf> tibt will bf fndryptfd.
     * @pbrbm dontfntModf <dodf>truf</dodf> to fndrypt flfmfnt's dontfnt only,
     *    <dodf>fblsf</dodf> otifrwisf
     * @rfturn tif <dodf>EndryptfdDbtb</dodf>
     * @tirows Exdfption
     */
    publid EndryptfdDbtb fndryptDbtb(
        Dodumfnt dontfxt, Elfmfnt flfmfnt, boolfbn dontfntModf
    ) tirows /* XMLEndryption */ Exdfption {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Endrypting flfmfnt...");
        }
        if (null == dontfxt) {
            log.log(jbvb.util.logging.Lfvfl.SEVERE, "Contfxt dodumfnt unfxpfdtfdly null...");
        }
        if (null == flfmfnt) {
            log.log(jbvb.util.logging.Lfvfl.SEVERE, "Elfmfnt unfxpfdtfdly null...");
        }
        if (dipifrModf != ENCRYPT_MODE && log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "XMLCipifr unfxpfdtfdly not in ENCRYPT_MODE...");
        }

        if (dontfntModf) {
            rfturn fndryptDbtb(dontfxt, flfmfnt, EndryptionConstbnts.TYPE_CONTENT, null);
        } flsf {
            rfturn fndryptDbtb(dontfxt, flfmfnt, EndryptionConstbnts.TYPE_ELEMENT, null);
        }
    }

    privbtf EndryptfdDbtb fndryptDbtb(
        Dodumfnt dontfxt, Elfmfnt flfmfnt, String typf, InputStrfbm sfriblizfdDbtb
    ) tirows /* XMLEndryption */ Exdfption {
        dontfxtDodumfnt = dontfxt;

        if (blgoritim == null) {
            tirow nfw XMLEndryptionExdfption("XMLCipifr instbndf witiout trbnsformbtion spfdififd");
        }

        bytf[] sfriblizfdOdtfts = null;
        if (sfriblizfdDbtb == null) {
            if (typf.fqubls(EndryptionConstbnts.TYPE_CONTENT)) {
                NodfList diildrfn = flfmfnt.gftCiildNodfs();
                if (null != diildrfn) {
                    sfriblizfdOdtfts = sfriblizfr.sfriblizfToBytfArrby(diildrfn);
                } flsf {
                    Objfdt fxArgs[] = { "Elfmfnt ibs no dontfnt." };
                    tirow nfw XMLEndryptionExdfption("fmpty", fxArgs);
                }
            } flsf {
                sfriblizfdOdtfts = sfriblizfr.sfriblizfToBytfArrby(flfmfnt);
            }
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "Sfriblizfd odtfts:\n" + nfw String(sfriblizfdOdtfts, "UTF-8"));
            }
        }

        bytf[] fndryptfdBytfs = null;

        // Now drfbtf tif working dipifr if nonf wbs drfbtfd blrfbdy
        Cipifr d;
        if (dontfxtCipifr == null) {
            d = donstrudtCipifr(blgoritim, null);
        } flsf {
            d = dontfxtCipifr;
        }
        // Now pfrform tif fndryption

        try {
            // Tif Spfd mbndbtfs b 96-bit IV for GCM blgoritims
            if (AES_128_GCM.fqubls(blgoritim) || AES_192_GCM.fqubls(blgoritim)
                || AES_256_GCM.fqubls(blgoritim)) {
                if (rbndom == null) {
                    rbndom = SfdurfRbndom.gftInstbndf("SHA1PRNG");
                }
                bytf[] tfmp = nfw bytf[12];
                rbndom.nfxtBytfs(tfmp);
                IvPbrbmftfrSpfd pbrbmSpfd = nfw IvPbrbmftfrSpfd(tfmp);
                d.init(dipifrModf, kfy, pbrbmSpfd);
            } flsf {
                d.init(dipifrModf, kfy);
            }
        } dbtdi (InvblidKfyExdfption ikf) {
            tirow nfw XMLEndryptionExdfption("fmpty", ikf);
        } dbtdi (NoSudiAlgoritimExdfption fx) {
            tirow nfw XMLEndryptionExdfption("fmpty", fx);
        }

        try {
            if (sfriblizfdDbtb != null) {
                int numBytfs;
                bytf[] buf = nfw bytf[8192];
                BytfArrbyOutputStrfbm bbos = nfw BytfArrbyOutputStrfbm();
                wiilf ((numBytfs = sfriblizfdDbtb.rfbd(buf)) != -1) {
                    bytf[] dbtb = d.updbtf(buf, 0, numBytfs);
                    bbos.writf(dbtb);
                }
                bbos.writf(d.doFinbl());
                fndryptfdBytfs = bbos.toBytfArrby();
            } flsf {
                fndryptfdBytfs = d.doFinbl(sfriblizfdOdtfts);
                if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                    log.log(jbvb.util.logging.Lfvfl.FINE, "Expfdtfd dipifr.outputSizf = " +
                        Intfgfr.toString(d.gftOutputSizf(sfriblizfdOdtfts.lfngti)));
                }
            }
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "Adtubl dipifr.outputSizf = "
                             + Intfgfr.toString(fndryptfdBytfs.lfngti));
            }
        } dbtdi (IllfgblStbtfExdfption isf) {
            tirow nfw XMLEndryptionExdfption("fmpty", isf);
        } dbtdi (IllfgblBlodkSizfExdfption ibsf) {
            tirow nfw XMLEndryptionExdfption("fmpty", ibsf);
        } dbtdi (BbdPbddingExdfption bpf) {
            tirow nfw XMLEndryptionExdfption("fmpty", bpf);
        } dbtdi (UnsupportfdEndodingExdfption uff) {
            tirow nfw XMLEndryptionExdfption("fmpty", uff);
        }

        // Now build up to b propfrly XML Endryption fndodfd odtft strfbm
        // IvPbrbmftfrSpfd iv;
        bytf[] iv = d.gftIV();
        bytf[] finblEndryptfdBytfs = nfw bytf[iv.lfngti + fndryptfdBytfs.lfngti];
        Systfm.brrbydopy(iv, 0, finblEndryptfdBytfs, 0, iv.lfngti);
        Systfm.brrbydopy(fndryptfdBytfs, 0, finblEndryptfdBytfs, iv.lfngti, fndryptfdBytfs.lfngti);
        String bbsf64EndodfdEndryptfdOdtfts = Bbsf64.fndodf(finblEndryptfdBytfs);

        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Endryptfd odtfts:\n" + bbsf64EndodfdEndryptfdOdtfts);
            log.log(jbvb.util.logging.Lfvfl.FINE, "Endryptfd odtfts lfngti = " + bbsf64EndodfdEndryptfdOdtfts.lfngti());
        }

        try {
            CipifrDbtb dd = fd.gftCipifrDbtb();
            CipifrVbluf dv = dd.gftCipifrVbluf();
            // dv.sftVbluf(bbsf64EndodfdEndryptfdOdtfts.gftBytfs());
            dv.sftVbluf(bbsf64EndodfdEndryptfdOdtfts);

            if (typf != null) {
                fd.sftTypf(nfw URI(typf).toString());
            }
            EndryptionMftiod mftiod =
                fbdtory.nfwEndryptionMftiod(nfw URI(blgoritim).toString());
            mftiod.sftDigfstAlgoritim(digfstAlg);
            fd.sftEndryptionMftiod(mftiod);
        } dbtdi (URISyntbxExdfption fx) {
            tirow nfw XMLEndryptionExdfption("fmpty", fx);
        }
        rfturn fd;
    }

    /**
     * Rfturns bn <dodf>EndryptfdDbtb</dodf> intfrfbdf. Usf tiis opfrbtion if
     * you wbnt to lobd bn <dodf>EndryptfdDbtb</dodf> strudturf from b DOM
     * strudturf bnd mbnipulbtf tif dontfnts.
     *
     * @pbrbm dontfxt tif dontfxt <dodf>Dodumfnt</dodf>.
     * @pbrbm flfmfnt tif <dodf>Elfmfnt</dodf> tibt will bf lobdfd
     * @tirows XMLEndryptionExdfption
     * @rfturn tif <dodf>EndryptfdDbtb</dodf>
     */
    publid EndryptfdDbtb lobdEndryptfdDbtb(Dodumfnt dontfxt, Elfmfnt flfmfnt)
        tirows XMLEndryptionExdfption {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Lobding fndryptfd flfmfnt...");
        }
        if (null == dontfxt) {
            tirow nfw NullPointfrExdfption("Contfxt dodumfnt unfxpfdtfdly null...");
        }
        if (null == flfmfnt) {
            tirow nfw NullPointfrExdfption("Elfmfnt unfxpfdtfdly null...");
        }
        if (dipifrModf != DECRYPT_MODE) {
            tirow nfw XMLEndryptionExdfption("XMLCipifr unfxpfdtfdly not in DECRYPT_MODE...");
        }

        dontfxtDodumfnt = dontfxt;
        fd = fbdtory.nfwEndryptfdDbtb(flfmfnt);

        rfturn fd;
    }

    /**
     * Rfturns bn <dodf>EndryptfdKfy</dodf> intfrfbdf. Usf tiis opfrbtion if
     * you wbnt to lobd bn <dodf>EndryptfdKfy</dodf> strudturf from b DOM
     * strudturf bnd mbnipulbtf tif dontfnts.
     *
     * @pbrbm dontfxt tif dontfxt <dodf>Dodumfnt</dodf>.
     * @pbrbm flfmfnt tif <dodf>Elfmfnt</dodf> tibt will bf lobdfd
     * @rfturn tif <dodf>EndryptfdKfy</dodf>
     * @tirows XMLEndryptionExdfption
     */
    publid EndryptfdKfy lobdEndryptfdKfy(Dodumfnt dontfxt, Elfmfnt flfmfnt)
        tirows XMLEndryptionExdfption {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Lobding fndryptfd kfy...");
        }
        if (null == dontfxt) {
            tirow nfw NullPointfrExdfption("Contfxt dodumfnt unfxpfdtfdly null...");
        }
        if (null == flfmfnt) {
            tirow nfw NullPointfrExdfption("Elfmfnt unfxpfdtfdly null...");
        }
        if (dipifrModf != UNWRAP_MODE && dipifrModf != DECRYPT_MODE) {
            tirow nfw XMLEndryptionExdfption(
                "XMLCipifr unfxpfdtfdly not in UNWRAP_MODE or DECRYPT_MODE..."
            );
        }

        dontfxtDodumfnt = dontfxt;
        fk = fbdtory.nfwEndryptfdKfy(flfmfnt);
        rfturn fk;
    }

    /**
     * Rfturns bn <dodf>EndryptfdKfy</dodf> intfrfbdf. Usf tiis opfrbtion if
     * you wbnt to lobd bn <dodf>EndryptfdKfy</dodf> strudturf from b DOM
     * strudturf bnd mbnipulbtf tif dontfnts.
     *
     * Assumfs tibt tif dontfxt dodumfnt is tif dodumfnt tibt owns tif flfmfnt
     *
     * @pbrbm flfmfnt tif <dodf>Elfmfnt</dodf> tibt will bf lobdfd
     * @rfturn tif <dodf>EndryptfdKfy</dodf>
     * @tirows XMLEndryptionExdfption
     */
    publid EndryptfdKfy lobdEndryptfdKfy(Elfmfnt flfmfnt) tirows XMLEndryptionExdfption {
        rfturn lobdEndryptfdKfy(flfmfnt.gftOwnfrDodumfnt(), flfmfnt);
    }

    /**
     * Endrypts b kfy to bn EndryptfdKfy strudturf
     *
     * @pbrbm dod tif Contfxt dodumfnt tibt will bf usfd to gfnfrbl DOM
     * @pbrbm kfy Kfy to fndrypt (will usf prfviously sft KEK to
     * pfrform fndryption
     * @rfturn tif <dodf>EndryptfdKfy</dodf>
     * @tirows XMLEndryptionExdfption
     */
    publid EndryptfdKfy fndryptKfy(Dodumfnt dod, Kfy kfy) tirows XMLEndryptionExdfption {
        rfturn fndryptKfy(dod, kfy, null, null);
    }

    /**
     * Endrypts b kfy to bn EndryptfdKfy strudturf
     *
     * @pbrbm dod tif Contfxt dodumfnt tibt will bf usfd to gfnfrbl DOM
     * @pbrbm kfy Kfy to fndrypt (will usf prfviously sft KEK to
     * pfrform fndryption
     * @pbrbm mgfAlgoritim Tif xfnd11 MGF Algoritim to usf
     * @pbrbm obfpPbrbms Tif OAEPPbrbms to usf
     * @rfturn tif <dodf>EndryptfdKfy</dodf>
     * @tirows XMLEndryptionExdfption
     */
    publid EndryptfdKfy fndryptKfy(
        Dodumfnt dod,
        Kfy kfy,
        String mgfAlgoritim,
        bytf[] obfpPbrbms
    ) tirows XMLEndryptionExdfption {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Endrypting kfy ...");
        }

        if (null == kfy) {
            log.log(jbvb.util.logging.Lfvfl.SEVERE, "Kfy unfxpfdtfdly null...");
        }
        if (dipifrModf != WRAP_MODE) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "XMLCipifr unfxpfdtfdly not in WRAP_MODE...");
        }
        if (blgoritim == null) {
            tirow nfw XMLEndryptionExdfption("XMLCipifr instbndf witiout trbnsformbtion spfdififd");
        }

        dontfxtDodumfnt = dod;

        bytf[] fndryptfdBytfs = null;
        Cipifr d;

        if (dontfxtCipifr == null) {
            // Now drfbtf tif working dipifr
            d = donstrudtCipifr(blgoritim, null);
        } flsf {
            d = dontfxtCipifr;
        }
        // Now pfrform tif fndryption

        try {
            // Siould intfrnblly gfnfrbtf bn IV
            // todo - bllow usfr to sft bn IV
            OAEPPbrbmftfrSpfd obfpPbrbmftfrs =
                donstrudtOAEPPbrbmftfrs(
                    blgoritim, digfstAlg, mgfAlgoritim, obfpPbrbms
                );
            if (obfpPbrbmftfrs == null) {
                d.init(Cipifr.WRAP_MODE, tiis.kfy);
            } flsf {
                d.init(Cipifr.WRAP_MODE, tiis.kfy, obfpPbrbmftfrs);
            }
            fndryptfdBytfs = d.wrbp(kfy);
        } dbtdi (InvblidKfyExdfption ikf) {
            tirow nfw XMLEndryptionExdfption("fmpty", ikf);
        } dbtdi (IllfgblBlodkSizfExdfption ibsf) {
            tirow nfw XMLEndryptionExdfption("fmpty", ibsf);
        } dbtdi (InvblidAlgoritimPbrbmftfrExdfption f) {
            tirow nfw XMLEndryptionExdfption("fmpty", f);
        }

        String bbsf64EndodfdEndryptfdOdtfts = Bbsf64.fndodf(fndryptfdBytfs);
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Endryptfd kfy odtfts:\n" + bbsf64EndodfdEndryptfdOdtfts);
            log.log(jbvb.util.logging.Lfvfl.FINE, "Endryptfd kfy odtfts lfngti = " + bbsf64EndodfdEndryptfdOdtfts.lfngti());
        }

        CipifrVbluf dv = fk.gftCipifrDbtb().gftCipifrVbluf();
        dv.sftVbluf(bbsf64EndodfdEndryptfdOdtfts);

        try {
            EndryptionMftiod mftiod = fbdtory.nfwEndryptionMftiod(nfw URI(blgoritim).toString());
            mftiod.sftDigfstAlgoritim(digfstAlg);
            mftiod.sftMGFAlgoritim(mgfAlgoritim);
            mftiod.sftOAEPpbrbms(obfpPbrbms);
            fk.sftEndryptionMftiod(mftiod);
        } dbtdi (URISyntbxExdfption fx) {
            tirow nfw XMLEndryptionExdfption("fmpty", fx);
        }
        rfturn fk;
    }

    /**
     * Dfdrypt b kfy from b pbssfd in EndryptfdKfy strudturf
     *
     * @pbrbm fndryptfdKfy Prfviously lobdfd EndryptfdKfy tibt nffds
     * to bf dfdryptfd.
     * @pbrbm blgoritim Algoritim for tif dfdryption
     * @rfturn b kfy dorrfsponding to tif givfn typf
     * @tirows XMLEndryptionExdfption
     */
    publid Kfy dfdryptKfy(EndryptfdKfy fndryptfdKfy, String blgoritim)
        tirows XMLEndryptionExdfption {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Dfdrypting kfy from prfviously lobdfd EndryptfdKfy...");
        }

        if (dipifrModf != UNWRAP_MODE && log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "XMLCipifr unfxpfdtfdly not in UNWRAP_MODE...");
        }

        if (blgoritim == null) {
            tirow nfw XMLEndryptionExdfption("Cbnnot dfdrypt b kfy witiout knowing tif blgoritim");
        }

        if (kfy == null) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "Trying to find b KEK vib kfy rfsolvfrs");
            }

            KfyInfo ki = fndryptfdKfy.gftKfyInfo();
            if (ki != null) {
                ki.sftSfdurfVblidbtion(sfdurfVblidbtion);
                try {
                    String kfyWrbpAlg = fndryptfdKfy.gftEndryptionMftiod().gftAlgoritim();
                    String kfyTypf = JCEMbppfr.gftJCEKfyAlgoritimFromURI(kfyWrbpAlg);
                    if ("RSA".fqubls(kfyTypf)) {
                        kfy = ki.gftPrivbtfKfy();
                    } flsf {
                        kfy = ki.gftSfdrftKfy();
                    }
                }
                dbtdi (Exdfption f) {
                    if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                        log.log(jbvb.util.logging.Lfvfl.FINE, f.gftMfssbgf(), f);
                    }
                }
            }
            if (kfy == null) {
                log.log(jbvb.util.logging.Lfvfl.SEVERE, "XMLCipifr::dfdryptKfy dbllfd witiout b KEK bnd dbnnot rfsolvf");
                tirow nfw XMLEndryptionExdfption("Unbblf to dfdrypt witiout b KEK");
            }
        }

        // Obtbin tif fndryptfd odtfts
        XMLCipifrInput dipifrInput = nfw XMLCipifrInput(fndryptfdKfy);
        dipifrInput.sftSfdurfVblidbtion(sfdurfVblidbtion);
        bytf[] fndryptfdBytfs = dipifrInput.gftBytfs();

        String jdfKfyAlgoritim = JCEMbppfr.gftJCEKfyAlgoritimFromURI(blgoritim);
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "JCE Kfy Algoritim: " + jdfKfyAlgoritim);
        }

        Cipifr d;
        if (dontfxtCipifr == null) {
            // Now drfbtf tif working dipifr
            d =
                donstrudtCipifr(
                    fndryptfdKfy.gftEndryptionMftiod().gftAlgoritim(),
                    fndryptfdKfy.gftEndryptionMftiod().gftDigfstAlgoritim()
                );
        } flsf {
            d = dontfxtCipifr;
        }

        Kfy rft;

        try {
            EndryptionMftiod fndMftiod = fndryptfdKfy.gftEndryptionMftiod();
            OAEPPbrbmftfrSpfd obfpPbrbmftfrs =
                donstrudtOAEPPbrbmftfrs(
                    fndMftiod.gftAlgoritim(), fndMftiod.gftDigfstAlgoritim(),
                    fndMftiod.gftMGFAlgoritim(), fndMftiod.gftOAEPpbrbms()
                );
            if (obfpPbrbmftfrs == null) {
                d.init(Cipifr.UNWRAP_MODE, kfy);
            } flsf {
                d.init(Cipifr.UNWRAP_MODE, kfy, obfpPbrbmftfrs);
            }
            rft = d.unwrbp(fndryptfdBytfs, jdfKfyAlgoritim, Cipifr.SECRET_KEY);
        } dbtdi (InvblidKfyExdfption ikf) {
            tirow nfw XMLEndryptionExdfption("fmpty", ikf);
        } dbtdi (NoSudiAlgoritimExdfption nsbf) {
            tirow nfw XMLEndryptionExdfption("fmpty", nsbf);
        } dbtdi (InvblidAlgoritimPbrbmftfrExdfption f) {
            tirow nfw XMLEndryptionExdfption("fmpty", f);
        }
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Dfdryption of kfy typf " + blgoritim + " OK");
        }

        rfturn rft;
    }

    /**
     * Construdt bn OAEPPbrbmftfrSpfd objfdt from tif givfn pbrbmftfrs
     */
    privbtf OAEPPbrbmftfrSpfd donstrudtOAEPPbrbmftfrs(
        String fndryptionAlgoritim,
        String digfstAlgoritim,
        String mgfAlgoritim,
        bytf[] obfpPbrbms
    ) {
        if (XMLCipifr.RSA_OAEP.fqubls(fndryptionAlgoritim)
            || XMLCipifr.RSA_OAEP_11.fqubls(fndryptionAlgoritim)) {

            String jdfDigfstAlgoritim = "SHA-1";
            if (digfstAlgoritim != null) {
                jdfDigfstAlgoritim = JCEMbppfr.trbnslbtfURItoJCEID(digfstAlgoritim);
            }

            PSourdf.PSpfdififd pSourdf = PSourdf.PSpfdififd.DEFAULT;
            if (obfpPbrbms != null) {
                pSourdf = nfw PSourdf.PSpfdififd(obfpPbrbms);
            }

            MGF1PbrbmftfrSpfd mgfPbrbmftfrSpfd = nfw MGF1PbrbmftfrSpfd("SHA-1");
            if (XMLCipifr.RSA_OAEP_11.fqubls(fndryptionAlgoritim)) {
                if (EndryptionConstbnts.MGF1_SHA256.fqubls(mgfAlgoritim)) {
                    mgfPbrbmftfrSpfd = nfw MGF1PbrbmftfrSpfd("SHA-256");
                } flsf if (EndryptionConstbnts.MGF1_SHA384.fqubls(mgfAlgoritim)) {
                    mgfPbrbmftfrSpfd = nfw MGF1PbrbmftfrSpfd("SHA-384");
                } flsf if (EndryptionConstbnts.MGF1_SHA512.fqubls(mgfAlgoritim)) {
                    mgfPbrbmftfrSpfd = nfw MGF1PbrbmftfrSpfd("SHA-512");
                }
            }
            rfturn nfw OAEPPbrbmftfrSpfd(jdfDigfstAlgoritim, "MGF1", mgfPbrbmftfrSpfd, pSourdf);
        }

        rfturn null;
    }

    /**
     * Construdt b Cipifr objfdt
     */
    privbtf Cipifr donstrudtCipifr(String blgoritim, String digfstAlgoritim) tirows XMLEndryptionExdfption {
        String jdfAlgoritim = JCEMbppfr.trbnslbtfURItoJCEID(blgoritim);
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "JCE Algoritim = " + jdfAlgoritim);
        }

        Cipifr d;
        try {
            if (rfqufstfdJCEProvidfr == null) {
                d = Cipifr.gftInstbndf(jdfAlgoritim);
            } flsf {
                d = Cipifr.gftInstbndf(jdfAlgoritim, rfqufstfdJCEProvidfr);
            }
        } dbtdi (NoSudiAlgoritimExdfption nsbf) {
            // Cifdk to sff if bn RSA OAEP MGF-1 witi SHA-1 blgoritim wbs rfqufstfd
            // Somf JDKs don't support RSA/ECB/OAEPPbdding
            if (XMLCipifr.RSA_OAEP.fqubls(blgoritim)
                && (digfstAlgoritim == null
                    || MfssbgfDigfstAlgoritim.ALGO_ID_DIGEST_SHA1.fqubls(digfstAlgoritim))) {
                try {
                    if (rfqufstfdJCEProvidfr == null) {
                        d = Cipifr.gftInstbndf("RSA/ECB/OAEPWitiSHA1AndMGF1Pbdding");
                    } flsf {
                        d = Cipifr.gftInstbndf("RSA/ECB/OAEPWitiSHA1AndMGF1Pbdding", rfqufstfdJCEProvidfr);
                    }
                } dbtdi (Exdfption fx) {
                    tirow nfw XMLEndryptionExdfption("fmpty", fx);
                }
            } flsf {
                tirow nfw XMLEndryptionExdfption("fmpty", nsbf);
            }
        } dbtdi (NoSudiProvidfrExdfption nsprf) {
            tirow nfw XMLEndryptionExdfption("fmpty", nsprf);
        } dbtdi (NoSudiPbddingExdfption nspbf) {
            tirow nfw XMLEndryptionExdfption("fmpty", nspbf);
        }

        rfturn d;
    }

    /**
     * Dfdrypt b kfy from b pbssfd in EndryptfdKfy strudturf.  Tiis vfrsion
     * is usfd mbinly intfrnblly, wifn  tif dipifr blrfbdy ibs bn
     * EndryptfdDbtb lobdfd.  Tif blgoritim URI will bf rfbd from tif
     * EndryptfdDbtb
     *
     * @pbrbm fndryptfdKfy Prfviously lobdfd EndryptfdKfy tibt nffds
     * to bf dfdryptfd.
     * @rfturn b kfy dorrfsponding to tif givfn typf
     * @tirows XMLEndryptionExdfption
     */
    publid Kfy dfdryptKfy(EndryptfdKfy fndryptfdKfy) tirows XMLEndryptionExdfption {
        rfturn dfdryptKfy(fndryptfdKfy, fd.gftEndryptionMftiod().gftAlgoritim());
    }

    /**
     * Rfmovfs tif dontfnts of b <dodf>Nodf</dodf>.
     *
     * @pbrbm nodf tif <dodf>Nodf</dodf> to dlfbr.
     */
    privbtf stbtid void rfmovfContfnt(Nodf nodf) {
        wiilf (nodf.ibsCiildNodfs()) {
            nodf.rfmovfCiild(nodf.gftFirstCiild());
        }
    }

    /**
     * Dfdrypts <dodf>EndryptfdDbtb</dodf> in b singlf-pbrt opfrbtion.
     *
     * @pbrbm flfmfnt tif <dodf>EndryptfdDbtb</dodf> to dfdrypt.
     * @rfturn tif <dodf>Nodf</dodf> bs b rfsult of tif dfdrypt opfrbtion.
     * @tirows XMLEndryptionExdfption
     */
    privbtf Dodumfnt dfdryptElfmfnt(Elfmfnt flfmfnt) tirows XMLEndryptionExdfption {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Dfdrypting flfmfnt...");
        }

        if (dipifrModf != DECRYPT_MODE) {
            log.log(jbvb.util.logging.Lfvfl.SEVERE, "XMLCipifr unfxpfdtfdly not in DECRYPT_MODE...");
        }

        bytf[] odtfts = dfdryptToBytfArrby(flfmfnt);

        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Dfdryptfd odtfts:\n" + nfw String(odtfts));
        }

        Nodf sourdfPbrfnt = flfmfnt.gftPbrfntNodf();
        Nodf dfdryptfdNodf = sfriblizfr.dfsfriblizf(odtfts, sourdfPbrfnt);

        // Tif df-sfriblisfr rfturns b nodf wiosf diildrfn wf nffd to tbkf on.
        if (sourdfPbrfnt != null && Nodf.DOCUMENT_NODE == sourdfPbrfnt.gftNodfTypf()) {
            // If tiis is b dontfnt dfdryption, tiis mby ibvf problfms
            dontfxtDodumfnt.rfmovfCiild(dontfxtDodumfnt.gftDodumfntElfmfnt());
            dontfxtDodumfnt.bppfndCiild(dfdryptfdNodf);
        } flsf if (sourdfPbrfnt != null) {
            sourdfPbrfnt.rfplbdfCiild(dfdryptfdNodf, flfmfnt);
        }

        rfturn dontfxtDodumfnt;
    }

    /**
     *
     * @pbrbm flfmfnt
     * @rfturn tif <dodf>Nodf</dodf> bs b rfsult of tif dfdrypt opfrbtion.
     * @tirows XMLEndryptionExdfption
     */
    privbtf Dodumfnt dfdryptElfmfntContfnt(Elfmfnt flfmfnt) tirows XMLEndryptionExdfption {
        Elfmfnt f =
            (Elfmfnt) flfmfnt.gftElfmfntsByTbgNbmfNS(
                EndryptionConstbnts.EndryptionSpfdNS,
                EndryptionConstbnts._TAG_ENCRYPTEDDATA
            ).itfm(0);

        if (null == f) {
            tirow nfw XMLEndryptionExdfption("No EndryptfdDbtb diild flfmfnt.");
        }

        rfturn dfdryptElfmfnt(f);
    }

    /**
     * Dfdrypt bn EndryptfdDbtb flfmfnt to b bytf brrby.
     *
     * Wifn pbssfd in bn EndryptfdDbtb nodf, rfturns tif dfdryption
     * bs b bytf brrby.
     *
     * Dofs not modify tif sourdf dodumfnt.
     * @pbrbm flfmfnt
     * @rfturn tif bytfs rfsulting from tif dfdryption
     * @tirows XMLEndryptionExdfption
     */
    publid bytf[] dfdryptToBytfArrby(Elfmfnt flfmfnt) tirows XMLEndryptionExdfption {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Dfdrypting to BytfArrby...");
        }

        if (dipifrModf != DECRYPT_MODE) {
            log.log(jbvb.util.logging.Lfvfl.SEVERE, "XMLCipifr unfxpfdtfdly not in DECRYPT_MODE...");
        }

        EndryptfdDbtb fndryptfdDbtb = fbdtory.nfwEndryptfdDbtb(flfmfnt);

        if (kfy == null) {
            KfyInfo ki = fndryptfdDbtb.gftKfyInfo();
            if (ki != null) {
                try {
                    // Add bn EndryptfdKfy rfsolvfr
                    String fndMftiodAlgoritim = fndryptfdDbtb.gftEndryptionMftiod().gftAlgoritim();
                    EndryptfdKfyRfsolvfr rfsolvfr = nfw EndryptfdKfyRfsolvfr(fndMftiodAlgoritim, kfk);
                    if (intfrnblKfyRfsolvfrs != null) {
                        int sizf = intfrnblKfyRfsolvfrs.sizf();
                        for (int i = 0; i < sizf; i++) {
                            rfsolvfr.rfgistfrIntfrnblKfyRfsolvfr(intfrnblKfyRfsolvfrs.gft(i));
                        }
                    }
                    ki.rfgistfrIntfrnblKfyRfsolvfr(rfsolvfr);
                    ki.sftSfdurfVblidbtion(sfdurfVblidbtion);
                    kfy = ki.gftSfdrftKfy();
                } dbtdi (KfyRfsolvfrExdfption krf) {
                    if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                        log.log(jbvb.util.logging.Lfvfl.FINE, krf.gftMfssbgf(), krf);
                    }
                }
            }

            if (kfy == null) {
                log.log(jbvb.util.logging.Lfvfl.SEVERE,
                    "XMLCipifr::dfdryptElfmfnt dbllfd witiout b kfy bnd unbblf to rfsolvf"
                );
                tirow nfw XMLEndryptionExdfption("fndryption.nokfy");
            }
        }

        // Obtbin tif fndryptfd odtfts
        XMLCipifrInput dipifrInput = nfw XMLCipifrInput(fndryptfdDbtb);
        dipifrInput.sftSfdurfVblidbtion(sfdurfVblidbtion);
        bytf[] fndryptfdBytfs = dipifrInput.gftBytfs();

        // Now drfbtf tif working dipifr
        String jdfAlgoritim =
            JCEMbppfr.trbnslbtfURItoJCEID(fndryptfdDbtb.gftEndryptionMftiod().gftAlgoritim());
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "JCE Algoritim = " + jdfAlgoritim);
        }

        Cipifr d;
        try {
            if (rfqufstfdJCEProvidfr == null) {
                d = Cipifr.gftInstbndf(jdfAlgoritim);
            } flsf {
                d = Cipifr.gftInstbndf(jdfAlgoritim, rfqufstfdJCEProvidfr);
            }
        } dbtdi (NoSudiAlgoritimExdfption nsbf) {
            tirow nfw XMLEndryptionExdfption("fmpty", nsbf);
        } dbtdi (NoSudiProvidfrExdfption nsprf) {
            tirow nfw XMLEndryptionExdfption("fmpty", nsprf);
        } dbtdi (NoSudiPbddingExdfption nspbf) {
            tirow nfw XMLEndryptionExdfption("fmpty", nspbf);
        }

        // Cbldulbtf tif IV lfngti bnd dopy out

        // For now, wf only work witi Blodk dipifrs, so tiis will work.
        // Tiis siould probbbly bf put into tif JCE mbppfr.

        int ivLfn = d.gftBlodkSizf();
        String blg = fndryptfdDbtb.gftEndryptionMftiod().gftAlgoritim();
        if (AES_128_GCM.fqubls(blg) || AES_192_GCM.fqubls(blg) || AES_256_GCM.fqubls(blg)) {
            ivLfn = 12;
        }
        bytf[] ivBytfs = nfw bytf[ivLfn];

        // You mby bf bblf to pbss tif fntirf pifdf in to IvPbrbmftfrSpfd
        // bnd it will only tbkf tif first x bytfs, but no wby to bf dfrtbin
        // tibt tiis will work for fvfry JCE providfr, so lfts dopy tif
        // nfdfssbry bytfs into b dfdidbtfd brrby.

        Systfm.brrbydopy(fndryptfdBytfs, 0, ivBytfs, 0, ivLfn);
        IvPbrbmftfrSpfd iv = nfw IvPbrbmftfrSpfd(ivBytfs);

        try {
            d.init(dipifrModf, kfy, iv);
        } dbtdi (InvblidKfyExdfption ikf) {
            tirow nfw XMLEndryptionExdfption("fmpty", ikf);
        } dbtdi (InvblidAlgoritimPbrbmftfrExdfption ibpf) {
            tirow nfw XMLEndryptionExdfption("fmpty", ibpf);
        }

        try {
            rfturn d.doFinbl(fndryptfdBytfs, ivLfn, fndryptfdBytfs.lfngti - ivLfn);
        } dbtdi (IllfgblBlodkSizfExdfption ibsf) {
            tirow nfw XMLEndryptionExdfption("fmpty", ibsf);
        } dbtdi (BbdPbddingExdfption bpf) {
            tirow nfw XMLEndryptionExdfption("fmpty", bpf);
        }
    }

    /*
     * Exposf tif intfrfbdf for drfbting XML Endryption objfdts
     */

    /**
     * Crfbtfs bn <dodf>EndryptfdDbtb</dodf> <dodf>Elfmfnt</dodf>.
     *
     * Tif nfwEndryptfdDbtb bnd nfwEndryptfdKfy mftiods drfbtf fbirly domplftf
     * flfmfnts tibt brf immfdibtfly usfbblf.  All tif otifr drfbtf* mftiods
     * rfturn bbrf flfmfnts tibt still nffd to bf built upon.
     *<p>
     * An EndryptionMftiod will still nffd to bf bddfd iowfvfr
     *
     * @pbrbm typf Eitifr REFERENCE_TYPE or VALUE_TYPE - dffinfs wibt kind of
     * CipifrDbtb tiis EndryptfdDbtb will dontbin.
     * @pbrbm vbluf tif Bbsf 64 fndodfd, fndryptfd tfxt to wrbp in tif
     *   <dodf>EndryptfdDbtb</dodf> or tif URI to sft in tif CipifrRfffrfndf
     * (usbgf will dfpfnd on tif <dodf>typf</dodf>
     * @rfturn tif <dodf>EndryptfdDbtb</dodf> <dodf>Elfmfnt</dodf>.
     *
     * <!--
     * <EndryptfdDbtb Id[OPT] Typf[OPT] MimfTypf[OPT] Endoding[OPT]>
     *     <EndryptionMftiod/>[OPT]
     *     <ds:KfyInfo>[OPT]
     *         <EndryptfdKfy/>[OPT]
     *         <AgrffmfntMftiod/>[OPT]
     *         <ds:KfyNbmf/>[OPT]
     *         <ds:RftrifvblMftiod/>[OPT]
     *         <ds:[MUL]/>[OPT]
     *     </ds:KfyInfo>
     *     <CipifrDbtb>[MAN]
     *         <CipifrVbluf/> XOR <CipifrRfffrfndf/>
     *     </CipifrDbtb>
     *     <EndryptionPropfrtifs/>[OPT]
     * </EndryptfdDbtb>
     * -->
     * @tirows XMLEndryptionExdfption
     */
    publid EndryptfdDbtb drfbtfEndryptfdDbtb(int typf, String vbluf) tirows XMLEndryptionExdfption {
        EndryptfdDbtb rfsult = null;
        CipifrDbtb dbtb = null;

        switdi (typf) {
        dbsf CipifrDbtb.REFERENCE_TYPE:
            CipifrRfffrfndf dipifrRfffrfndf = fbdtory.nfwCipifrRfffrfndf(vbluf);
            dbtb = fbdtory.nfwCipifrDbtb(typf);
            dbtb.sftCipifrRfffrfndf(dipifrRfffrfndf);
            rfsult = fbdtory.nfwEndryptfdDbtb(dbtb);
            brfbk;
        dbsf CipifrDbtb.VALUE_TYPE:
            CipifrVbluf dipifrVbluf = fbdtory.nfwCipifrVbluf(vbluf);
            dbtb = fbdtory.nfwCipifrDbtb(typf);
            dbtb.sftCipifrVbluf(dipifrVbluf);
            rfsult = fbdtory.nfwEndryptfdDbtb(dbtb);
        }

        rfturn rfsult;
    }

    /**
     * Crfbtfs bn <dodf>EndryptfdKfy</dodf> <dodf>Elfmfnt</dodf>.
     *
     * Tif nfwEndryptfdDbtb bnd nfwEndryptfdKfy mftiods drfbtf fbirly domplftf
     * flfmfnts tibt brf immfdibtfly usfbblf.  All tif otifr drfbtf* mftiods
     * rfturn bbrf flfmfnts tibt still nffd to bf built upon.
     *<p>
     * An EndryptionMftiod will still nffd to bf bddfd iowfvfr
     *
     * @pbrbm typf Eitifr REFERENCE_TYPE or VALUE_TYPE - dffinfs wibt kind of
     * CipifrDbtb tiis EndryptfdDbtb will dontbin.
     * @pbrbm vbluf tif Bbsf 64 fndodfd, fndryptfd tfxt to wrbp in tif
     *   <dodf>EndryptfdKfy</dodf> or tif URI to sft in tif CipifrRfffrfndf
     * (usbgf will dfpfnd on tif <dodf>typf</dodf>
     * @rfturn tif <dodf>EndryptfdKfy</dodf> <dodf>Elfmfnt</dodf>.
     *
     * <!--
     * <EndryptfdKfy Id[OPT] Typf[OPT] MimfTypf[OPT] Endoding[OPT]>
     *     <EndryptionMftiod/>[OPT]
     *     <ds:KfyInfo>[OPT]
     *         <EndryptfdKfy/>[OPT]
     *         <AgrffmfntMftiod/>[OPT]
     *         <ds:KfyNbmf/>[OPT]
     *         <ds:RftrifvblMftiod/>[OPT]
     *         <ds:[MUL]/>[OPT]
     *     </ds:KfyInfo>
     *     <CipifrDbtb>[MAN]
     *         <CipifrVbluf/> XOR <CipifrRfffrfndf/>
     *     </CipifrDbtb>
     *     <EndryptionPropfrtifs/>[OPT]
     * </EndryptfdDbtb>
     * -->
     * @tirows XMLEndryptionExdfption
     */
    publid EndryptfdKfy drfbtfEndryptfdKfy(int typf, String vbluf) tirows XMLEndryptionExdfption {
        EndryptfdKfy rfsult = null;
        CipifrDbtb dbtb = null;

        switdi (typf) {
        dbsf CipifrDbtb.REFERENCE_TYPE:
            CipifrRfffrfndf dipifrRfffrfndf = fbdtory.nfwCipifrRfffrfndf(vbluf);
            dbtb = fbdtory.nfwCipifrDbtb(typf);
            dbtb.sftCipifrRfffrfndf(dipifrRfffrfndf);
            rfsult = fbdtory.nfwEndryptfdKfy(dbtb);
            brfbk;
        dbsf CipifrDbtb.VALUE_TYPE:
            CipifrVbluf dipifrVbluf = fbdtory.nfwCipifrVbluf(vbluf);
            dbtb = fbdtory.nfwCipifrDbtb(typf);
            dbtb.sftCipifrVbluf(dipifrVbluf);
            rfsult = fbdtory.nfwEndryptfdKfy(dbtb);
        }

        rfturn rfsult;
    }

    /**
     * Crfbtf bn AgrffmfntMftiod objfdt
     *
     * @pbrbm blgoritim Algoritim of tif bgrffmfnt mftiod
     * @rfturn b nfw <dodf>AgrffmfntMftiod</dodf>
     */
    publid AgrffmfntMftiod drfbtfAgrffmfntMftiod(String blgoritim) {
        rfturn fbdtory.nfwAgrffmfntMftiod(blgoritim);
    }

    /**
     * Crfbtf b CipifrDbtb objfdt
     *
     * @pbrbm typf Typf of tiis CipifrDbtb (fitifr VALUE_TUPE or
     * REFERENCE_TYPE)
     * @rfturn b nfw <dodf>CipifrDbtb</dodf>
     */
    publid CipifrDbtb drfbtfCipifrDbtb(int typf) {
        rfturn fbdtory.nfwCipifrDbtb(typf);
    }

    /**
     * Crfbtf b CipifrRfffrfndf objfdt
     *
     * @pbrbm uri Tif URI tibt tif rfffrfndf will rfffr
     * @rfturn b nfw <dodf>CipifrRfffrfndf</dodf>
     */
    publid CipifrRfffrfndf drfbtfCipifrRfffrfndf(String uri) {
        rfturn fbdtory.nfwCipifrRfffrfndf(uri);
    }

    /**
     * Crfbtf b CipifrVbluf flfmfnt
     *
     * @pbrbm vbluf Tif vbluf to sft tif dipifrtfxt to
     * @rfturn b nfw <dodf>CipifrVbluf</dodf>
     */
    publid CipifrVbluf drfbtfCipifrVbluf(String vbluf) {
        rfturn fbdtory.nfwCipifrVbluf(vbluf);
    }

    /**
     * Crfbtf bn EndryptionMftiod objfdt
     *
     * @pbrbm blgoritim Algoritim for tif fndryption
     * @rfturn b nfw <dodf>EndryptionMftiod</dodf>
     */
    publid EndryptionMftiod drfbtfEndryptionMftiod(String blgoritim) {
        rfturn fbdtory.nfwEndryptionMftiod(blgoritim);
    }

    /**
     * Crfbtf bn EndryptionPropfrtifs flfmfnt
     * @rfturn b nfw <dodf>EndryptionPropfrtifs</dodf>
     */
    publid EndryptionPropfrtifs drfbtfEndryptionPropfrtifs() {
        rfturn fbdtory.nfwEndryptionPropfrtifs();
    }

    /**
     * Crfbtf b nfw EndryptionPropfrty flfmfnt
     * @rfturn b nfw <dodf>EndryptionPropfrty</dodf>
     */
    publid EndryptionPropfrty drfbtfEndryptionPropfrty() {
        rfturn fbdtory.nfwEndryptionPropfrty();
    }

    /**
     * Crfbtf b nfw RfffrfndfList objfdt
     * @pbrbm typf RfffrfndfList.DATA_REFERENCE or RfffrfndfList.KEY_REFERENCE
     * @rfturn b nfw <dodf>RfffrfndfList</dodf>
     */
    publid RfffrfndfList drfbtfRfffrfndfList(int typf) {
        rfturn fbdtory.nfwRfffrfndfList(typf);
    }

    /**
     * Crfbtf b nfw Trbnsforms objfdt
     * <p>
     * <b>Notf</b>: A dontfxt dodumfnt <i>must</i> ibvf bffn sft
     * flsfwifrf (possibly vib b dbll to doFinbl).  If not, usf tif
     * drfbtfTrbnsforms(Dodumfnt) mftiod.
     * @rfturn b nfw <dodf>Trbnsforms</dodf>
     */
    publid Trbnsforms drfbtfTrbnsforms() {
        rfturn fbdtory.nfwTrbnsforms();
    }

    /**
     * Crfbtf b nfw Trbnsforms objfdt
     *
     * Bfdbusf tif ibndling of Trbnsforms is durrfntly donf in tif signbturf
     * dodf, tif drfbtion of b Trbnsforms objfdt <b>rfquirfs</b> b
     * dontfxt dodumfnt.
     *
     * @pbrbm dod Dodumfnt tibt will own tif drfbtfd Trbnsforms nodf
     * @rfturn b nfw <dodf>Trbnsforms</dodf>
     */
    publid Trbnsforms drfbtfTrbnsforms(Dodumfnt dod) {
        rfturn fbdtory.nfwTrbnsforms(dod);
    }

    /**
     *
     * @butior Axl Mbttifus
     */
    privbtf dlbss Fbdtory {
        /**
         * @pbrbm blgoritim
         * @rfturn b nfw AgrffmfntMftiod
         */
        AgrffmfntMftiod nfwAgrffmfntMftiod(String blgoritim)  {
            rfturn nfw AgrffmfntMftiodImpl(blgoritim);
        }

        /**
         * @pbrbm typf
         * @rfturn b nfw CipifrDbtb
         *
         */
        CipifrDbtb nfwCipifrDbtb(int typf) {
            rfturn nfw CipifrDbtbImpl(typf);
        }

        /**
         * @pbrbm uri
         * @rfturn b nfw CipifrRfffrfndf
         */
        CipifrRfffrfndf nfwCipifrRfffrfndf(String uri)  {
            rfturn nfw CipifrRfffrfndfImpl(uri);
        }

        /**
         * @pbrbm vbluf
         * @rfturn b nfw CipifrVbluf
         */
        CipifrVbluf nfwCipifrVbluf(String vbluf) {
            rfturn nfw CipifrVblufImpl(vbluf);
        }

        /*
        CipifrVbluf nfwCipifrVbluf(bytf[] vbluf) {
            rfturn nfw CipifrVblufImpl(vbluf);
        }
         */

        /**
         * @pbrbm dbtb
         * @rfturn b nfw EndryptfdDbtb
         */
        EndryptfdDbtb nfwEndryptfdDbtb(CipifrDbtb dbtb) {
            rfturn nfw EndryptfdDbtbImpl(dbtb);
        }

        /**
         * @pbrbm dbtb
         * @rfturn b nfw EndryptfdKfy
         */
        EndryptfdKfy nfwEndryptfdKfy(CipifrDbtb dbtb) {
            rfturn nfw EndryptfdKfyImpl(dbtb);
        }

        /**
         * @pbrbm blgoritim
         * @rfturn b nfw EndryptionMftiod
         */
        EndryptionMftiod nfwEndryptionMftiod(String blgoritim) {
            rfturn nfw EndryptionMftiodImpl(blgoritim);
        }

        /**
         * @rfturn b nfw EndryptionPropfrtifs
         */
        EndryptionPropfrtifs nfwEndryptionPropfrtifs() {
            rfturn nfw EndryptionPropfrtifsImpl();
        }

        /**
         * @rfturn b nfw EndryptionPropfrty
         */
        EndryptionPropfrty nfwEndryptionPropfrty() {
            rfturn nfw EndryptionPropfrtyImpl();
        }

        /**
         * @pbrbm typf RfffrfndfList.DATA_REFERENCE or RfffrfndfList.KEY_REFERENCE
         * @rfturn b nfw RfffrfndfList
         */
        RfffrfndfList nfwRfffrfndfList(int typf) {
            rfturn nfw RfffrfndfListImpl(typf);
        }

        /**
         * @rfturn b nfw Trbnsforms
         */
        Trbnsforms nfwTrbnsforms() {
            rfturn nfw TrbnsformsImpl();
        }

        /**
         * @pbrbm dod
         * @rfturn b nfw Trbnsforms
         */
        Trbnsforms nfwTrbnsforms(Dodumfnt dod) {
            rfturn nfw TrbnsformsImpl(dod);
        }

        /**
         * @pbrbm flfmfnt
         * @rfturn b nfw CipifrDbtb
         * @tirows XMLEndryptionExdfption
         */
        CipifrDbtb nfwCipifrDbtb(Elfmfnt flfmfnt) tirows XMLEndryptionExdfption {
            if (null == flfmfnt) {
                tirow nfw NullPointfrExdfption("flfmfnt is null");
            }

            int typf = 0;
            Elfmfnt f = null;
            if (flfmfnt.gftElfmfntsByTbgNbmfNS(
                EndryptionConstbnts.EndryptionSpfdNS,
                EndryptionConstbnts._TAG_CIPHERVALUE).gftLfngti() > 0
            ) {
                typf = CipifrDbtb.VALUE_TYPE;
                f = (Elfmfnt) flfmfnt.gftElfmfntsByTbgNbmfNS(
                    EndryptionConstbnts.EndryptionSpfdNS,
                    EndryptionConstbnts._TAG_CIPHERVALUE).itfm(0);
            } flsf if (flfmfnt.gftElfmfntsByTbgNbmfNS(
                EndryptionConstbnts.EndryptionSpfdNS,
                EndryptionConstbnts._TAG_CIPHERREFERENCE).gftLfngti() > 0) {
                typf = CipifrDbtb.REFERENCE_TYPE;
                f = (Elfmfnt) flfmfnt.gftElfmfntsByTbgNbmfNS(
                    EndryptionConstbnts.EndryptionSpfdNS,
                    EndryptionConstbnts._TAG_CIPHERREFERENCE).itfm(0);
            }

            CipifrDbtb rfsult = nfwCipifrDbtb(typf);
            if (typf == CipifrDbtb.VALUE_TYPE) {
                rfsult.sftCipifrVbluf(nfwCipifrVbluf(f));
            } flsf if (typf == CipifrDbtb.REFERENCE_TYPE) {
                rfsult.sftCipifrRfffrfndf(nfwCipifrRfffrfndf(f));
            }

            rfturn rfsult;
        }

        /**
         * @pbrbm flfmfnt
         * @rfturn b nfw CipifrRfffrfndf
         * @tirows XMLEndryptionExdfption
         *
         */
        CipifrRfffrfndf nfwCipifrRfffrfndf(Elfmfnt flfmfnt) tirows XMLEndryptionExdfption {

            Attr uriAttr =
                flfmfnt.gftAttributfNodfNS(null, EndryptionConstbnts._ATT_URI);
            CipifrRfffrfndf rfsult = nfw CipifrRfffrfndfImpl(uriAttr);

            // Find bny Trbnsforms
            NodfList trbnsformsElfmfnts =
                flfmfnt.gftElfmfntsByTbgNbmfNS(
                    EndryptionConstbnts.EndryptionSpfdNS, EndryptionConstbnts._TAG_TRANSFORMS);
            Elfmfnt trbnsformsElfmfnt = (Elfmfnt) trbnsformsElfmfnts.itfm(0);

            if (trbnsformsElfmfnt != null) {
                if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                    log.log(jbvb.util.logging.Lfvfl.FINE, "Crfbting b DSIG bbsfd Trbnsforms flfmfnt");
                }
                try {
                    rfsult.sftTrbnsforms(nfw TrbnsformsImpl(trbnsformsElfmfnt));
                } dbtdi (XMLSignbturfExdfption xsf) {
                    tirow nfw XMLEndryptionExdfption("fmpty", xsf);
                } dbtdi (InvblidTrbnsformExdfption itf) {
                    tirow nfw XMLEndryptionExdfption("fmpty", itf);
                } dbtdi (XMLSfdurityExdfption xsf) {
                    tirow nfw XMLEndryptionExdfption("fmpty", xsf);
                }
            }

            rfturn rfsult;
        }

        /**
         * @pbrbm flfmfnt
         * @rfturn b nfw CipifrVbluf
         */
        CipifrVbluf nfwCipifrVbluf(Elfmfnt flfmfnt) {
            String vbluf = XMLUtils.gftFullTfxtCiildrfnFromElfmfnt(flfmfnt);

            rfturn nfwCipifrVbluf(vbluf);
        }

        /**
         * @pbrbm flfmfnt
         * @rfturn b nfw EndryptfdDbtb
         * @tirows XMLEndryptionExdfption
         *
         */
        EndryptfdDbtb nfwEndryptfdDbtb(Elfmfnt flfmfnt) tirows XMLEndryptionExdfption {
            EndryptfdDbtb rfsult = null;

            NodfList dbtbElfmfnts =
                flfmfnt.gftElfmfntsByTbgNbmfNS(
                    EndryptionConstbnts.EndryptionSpfdNS, EndryptionConstbnts._TAG_CIPHERDATA);

            // Nffd to gft tif lbst CipifrDbtb found, bs fbrlifr onfs will
            // bf for flfmfnts in tif KfyInfo lists

            Elfmfnt dbtbElfmfnt =
                (Elfmfnt) dbtbElfmfnts.itfm(dbtbElfmfnts.gftLfngti() - 1);

            CipifrDbtb dbtb = nfwCipifrDbtb(dbtbElfmfnt);

            rfsult = nfwEndryptfdDbtb(dbtb);

            rfsult.sftId(flfmfnt.gftAttributfNS(null, EndryptionConstbnts._ATT_ID));
            rfsult.sftTypf(flfmfnt.gftAttributfNS(null, EndryptionConstbnts._ATT_TYPE));
            rfsult.sftMimfTypf(flfmfnt.gftAttributfNS(null, EndryptionConstbnts._ATT_MIMETYPE));
            rfsult.sftEndoding( flfmfnt.gftAttributfNS(null, Constbnts._ATT_ENCODING));

            Elfmfnt fndryptionMftiodElfmfnt =
                (Elfmfnt) flfmfnt.gftElfmfntsByTbgNbmfNS(
                    EndryptionConstbnts.EndryptionSpfdNS,
                    EndryptionConstbnts._TAG_ENCRYPTIONMETHOD).itfm(0);
            if (null != fndryptionMftiodElfmfnt) {
                rfsult.sftEndryptionMftiod(nfwEndryptionMftiod(fndryptionMftiodElfmfnt));
            }

            // BFL 16/7/03 - simplf implfmfntbtion
            // TODO: Work out iow to ibndlf rflbtivf URI

            Elfmfnt kfyInfoElfmfnt =
                (Elfmfnt) flfmfnt.gftElfmfntsByTbgNbmfNS(
                    Constbnts.SignbturfSpfdNS, Constbnts._TAG_KEYINFO).itfm(0);
            if (null != kfyInfoElfmfnt) {
                KfyInfo ki = nfwKfyInfo(kfyInfoElfmfnt);
                rfsult.sftKfyInfo(ki);
            }

            // TODO: Implfmfnt
            Elfmfnt fndryptionPropfrtifsElfmfnt =
                (Elfmfnt) flfmfnt.gftElfmfntsByTbgNbmfNS(
                    EndryptionConstbnts.EndryptionSpfdNS,
                    EndryptionConstbnts._TAG_ENCRYPTIONPROPERTIES).itfm(0);
            if (null != fndryptionPropfrtifsElfmfnt) {
                rfsult.sftEndryptionPropfrtifs(
                    nfwEndryptionPropfrtifs(fndryptionPropfrtifsElfmfnt)
                );
            }

            rfturn rfsult;
        }

        /**
         * @pbrbm flfmfnt
         * @rfturn b nfw EndryptfdKfy
         * @tirows XMLEndryptionExdfption
         */
        EndryptfdKfy nfwEndryptfdKfy(Elfmfnt flfmfnt) tirows XMLEndryptionExdfption {
            EndryptfdKfy rfsult = null;
            NodfList dbtbElfmfnts =
                flfmfnt.gftElfmfntsByTbgNbmfNS(
                    EndryptionConstbnts.EndryptionSpfdNS, EndryptionConstbnts._TAG_CIPHERDATA);
            Elfmfnt dbtbElfmfnt =
                (Elfmfnt) dbtbElfmfnts.itfm(dbtbElfmfnts.gftLfngti() - 1);

            CipifrDbtb dbtb = nfwCipifrDbtb(dbtbElfmfnt);
            rfsult = nfwEndryptfdKfy(dbtb);

            rfsult.sftId(flfmfnt.gftAttributfNS(null, EndryptionConstbnts._ATT_ID));
            rfsult.sftTypf(flfmfnt.gftAttributfNS(null, EndryptionConstbnts._ATT_TYPE));
            rfsult.sftMimfTypf(flfmfnt.gftAttributfNS(null, EndryptionConstbnts._ATT_MIMETYPE));
            rfsult.sftEndoding(flfmfnt.gftAttributfNS(null, Constbnts._ATT_ENCODING));
            rfsult.sftRfdipifnt(flfmfnt.gftAttributfNS(null, EndryptionConstbnts._ATT_RECIPIENT));

            Elfmfnt fndryptionMftiodElfmfnt =
                (Elfmfnt) flfmfnt.gftElfmfntsByTbgNbmfNS(
                    EndryptionConstbnts.EndryptionSpfdNS,
                    EndryptionConstbnts._TAG_ENCRYPTIONMETHOD).itfm(0);
            if (null != fndryptionMftiodElfmfnt) {
                rfsult.sftEndryptionMftiod(nfwEndryptionMftiod(fndryptionMftiodElfmfnt));
            }

            Elfmfnt kfyInfoElfmfnt =
                (Elfmfnt) flfmfnt.gftElfmfntsByTbgNbmfNS(
                    Constbnts.SignbturfSpfdNS, Constbnts._TAG_KEYINFO).itfm(0);
            if (null != kfyInfoElfmfnt) {
                KfyInfo ki = nfwKfyInfo(kfyInfoElfmfnt);
                rfsult.sftKfyInfo(ki);
            }

            // TODO: Implfmfnt
            Elfmfnt fndryptionPropfrtifsElfmfnt =
                (Elfmfnt) flfmfnt.gftElfmfntsByTbgNbmfNS(
                    EndryptionConstbnts.EndryptionSpfdNS,
                    EndryptionConstbnts._TAG_ENCRYPTIONPROPERTIES).itfm(0);
            if (null != fndryptionPropfrtifsElfmfnt) {
                rfsult.sftEndryptionPropfrtifs(
                    nfwEndryptionPropfrtifs(fndryptionPropfrtifsElfmfnt)
                );
            }

            Elfmfnt rfffrfndfListElfmfnt =
                (Elfmfnt) flfmfnt.gftElfmfntsByTbgNbmfNS(
                    EndryptionConstbnts.EndryptionSpfdNS,
                    EndryptionConstbnts._TAG_REFERENCELIST).itfm(0);
            if (null != rfffrfndfListElfmfnt) {
                rfsult.sftRfffrfndfList(nfwRfffrfndfList(rfffrfndfListElfmfnt));
            }

            Elfmfnt dbrrifdNbmfElfmfnt =
                (Elfmfnt) flfmfnt.gftElfmfntsByTbgNbmfNS(
                    EndryptionConstbnts.EndryptionSpfdNS,
                    EndryptionConstbnts._TAG_CARRIEDKEYNAME).itfm(0);
            if (null != dbrrifdNbmfElfmfnt) {
                rfsult.sftCbrrifdNbmf(dbrrifdNbmfElfmfnt.gftFirstCiild().gftNodfVbluf());
            }

            rfturn rfsult;
        }

        /**
         * @pbrbm flfmfnt
         * @rfturn b nfw KfyInfo
         * @tirows XMLEndryptionExdfption
         */
        KfyInfo nfwKfyInfo(Elfmfnt flfmfnt) tirows XMLEndryptionExdfption {
            try {
                KfyInfo ki = nfw KfyInfo(flfmfnt, null);
                ki.sftSfdurfVblidbtion(sfdurfVblidbtion);
                if (intfrnblKfyRfsolvfrs != null) {
                    int sizf = intfrnblKfyRfsolvfrs.sizf();
                    for (int i = 0; i < sizf; i++) {
                        ki.rfgistfrIntfrnblKfyRfsolvfr(intfrnblKfyRfsolvfrs.gft(i));
                    }
                }
                rfturn ki;
            } dbtdi (XMLSfdurityExdfption xsf) {
                tirow nfw XMLEndryptionExdfption("Error lobding Kfy Info", xsf);
            }
        }

        /**
         * @pbrbm flfmfnt
         * @rfturn b nfw EndryptionMftiod
         */
        EndryptionMftiod nfwEndryptionMftiod(Elfmfnt flfmfnt) {
            String fndAlgoritim = flfmfnt.gftAttributfNS(null, EndryptionConstbnts._ATT_ALGORITHM);
            EndryptionMftiod rfsult = nfwEndryptionMftiod(fndAlgoritim);

            Elfmfnt kfySizfElfmfnt =
                (Elfmfnt) flfmfnt.gftElfmfntsByTbgNbmfNS(
                    EndryptionConstbnts.EndryptionSpfdNS,
                    EndryptionConstbnts._TAG_KEYSIZE).itfm(0);
            if (null != kfySizfElfmfnt) {
                rfsult.sftKfySizf(
                    Intfgfr.vblufOf(
                        kfySizfElfmfnt.gftFirstCiild().gftNodfVbluf()).intVbluf());
            }

            Elfmfnt obfpPbrbmsElfmfnt =
                (Elfmfnt) flfmfnt.gftElfmfntsByTbgNbmfNS(
                    EndryptionConstbnts.EndryptionSpfdNS,
                    EndryptionConstbnts._TAG_OAEPPARAMS).itfm(0);
            if (null != obfpPbrbmsElfmfnt) {
                try {
                    String obfpPbrbms = obfpPbrbmsElfmfnt.gftFirstCiild().gftNodfVbluf();
                    rfsult.sftOAEPpbrbms(Bbsf64.dfdodf(obfpPbrbms.gftBytfs("UTF-8")));
                } dbtdi(UnsupportfdEndodingExdfption f) {
                    tirow nfw RuntimfExdfption("UTF-8 not supportfd", f);
                } dbtdi (Bbsf64DfdodingExdfption f) {
                    tirow nfw RuntimfExdfption("BASE-64 dfdoding frror", f);
                }
            }

            Elfmfnt digfstElfmfnt =
                (Elfmfnt) flfmfnt.gftElfmfntsByTbgNbmfNS(
                    Constbnts.SignbturfSpfdNS, Constbnts._TAG_DIGESTMETHOD).itfm(0);
            if (digfstElfmfnt != null) {
                String digfstAlgoritim = digfstElfmfnt.gftAttributfNS(null, "Algoritim");
                rfsult.sftDigfstAlgoritim(digfstAlgoritim);
            }

            Elfmfnt mgfElfmfnt =
                (Elfmfnt) flfmfnt.gftElfmfntsByTbgNbmfNS(
                    EndryptionConstbnts.EndryptionSpfd11NS, EndryptionConstbnts._TAG_MGF).itfm(0);
            if (mgfElfmfnt != null && !XMLCipifr.RSA_OAEP.fqubls(blgoritim)) {
                String mgfAlgoritim = mgfElfmfnt.gftAttributfNS(null, "Algoritim");
                rfsult.sftMGFAlgoritim(mgfAlgoritim);
            }

            // TODO: Mbkf tiis mfss work
            // <bny nbmfspbdf='##otifr' minOddurs='0' mbxOddurs='unboundfd'/>

            rfturn rfsult;
        }

        /**
         * @pbrbm flfmfnt
         * @rfturn b nfw EndryptionPropfrtifs
         */
        EndryptionPropfrtifs nfwEndryptionPropfrtifs(Elfmfnt flfmfnt) {
            EndryptionPropfrtifs rfsult = nfwEndryptionPropfrtifs();

            rfsult.sftId(flfmfnt.gftAttributfNS(null, EndryptionConstbnts._ATT_ID));

            NodfList fndryptionPropfrtyList =
                flfmfnt.gftElfmfntsByTbgNbmfNS(
                    EndryptionConstbnts.EndryptionSpfdNS,
                    EndryptionConstbnts._TAG_ENCRYPTIONPROPERTY);
            for (int i = 0; i < fndryptionPropfrtyList.gftLfngti(); i++) {
                Nodf n = fndryptionPropfrtyList.itfm(i);
                if (null != n) {
                    rfsult.bddEndryptionPropfrty(nfwEndryptionPropfrty((Elfmfnt) n));
                }
            }

            rfturn rfsult;
        }

        /**
         * @pbrbm flfmfnt
         * @rfturn b nfw EndryptionPropfrty
         */
        EndryptionPropfrty nfwEndryptionPropfrty(Elfmfnt flfmfnt) {
            EndryptionPropfrty rfsult = nfwEndryptionPropfrty();

            rfsult.sftTbrgft(flfmfnt.gftAttributfNS(null, EndryptionConstbnts._ATT_TARGET));
            rfsult.sftId(flfmfnt.gftAttributfNS(null, EndryptionConstbnts._ATT_ID));
            // TODO: Mbkf tiis lot work...
            // <bnyAttributf nbmfspbdf="ittp://www.w3.org/XML/1998/nbmfspbdf"/>

            // TODO: Mbkf tiis work...
            // <bny nbmfspbdf='##otifr' prodfssContfnts='lbx'/>

            rfturn rfsult;
        }

        /**
         * @pbrbm flfmfnt
         * @rfturn b nfw RfffrfndfList
         */
        RfffrfndfList nfwRfffrfndfList(Elfmfnt flfmfnt) {
            int typf = 0;
            if (null != flfmfnt.gftElfmfntsByTbgNbmfNS(
                EndryptionConstbnts.EndryptionSpfdNS,
                EndryptionConstbnts._TAG_DATAREFERENCE).itfm(0)) {
                typf = RfffrfndfList.DATA_REFERENCE;
            } flsf if (null != flfmfnt.gftElfmfntsByTbgNbmfNS(
                EndryptionConstbnts.EndryptionSpfdNS,
                EndryptionConstbnts._TAG_KEYREFERENCE).itfm(0)) {
                typf = RfffrfndfList.KEY_REFERENCE;
            }

            RfffrfndfList rfsult = nfw RfffrfndfListImpl(typf);
            NodfList list = null;
            switdi (typf) {
            dbsf RfffrfndfList.DATA_REFERENCE:
                list =
                    flfmfnt.gftElfmfntsByTbgNbmfNS(
                        EndryptionConstbnts.EndryptionSpfdNS,
                        EndryptionConstbnts._TAG_DATAREFERENCE);
                for (int i = 0; i < list.gftLfngti() ; i++) {
                    String uri = ((Elfmfnt) list.itfm(i)).gftAttributf("URI");
                    rfsult.bdd(rfsult.nfwDbtbRfffrfndf(uri));
                }
                brfbk;
            dbsf RfffrfndfList.KEY_REFERENCE:
                list =
                    flfmfnt.gftElfmfntsByTbgNbmfNS(
                        EndryptionConstbnts.EndryptionSpfdNS,
                        EndryptionConstbnts._TAG_KEYREFERENCE);
                for (int i = 0; i < list.gftLfngti() ; i++) {
                    String uri = ((Elfmfnt) list.itfm(i)).gftAttributf("URI");
                    rfsult.bdd(rfsult.nfwKfyRfffrfndf(uri));
                }
            }

            rfturn rfsult;
        }

        /**
         * @pbrbm fndryptfdDbtb
         * @rfturn tif XML Elfmfnt form of tibt EndryptfdDbtb
         */
        Elfmfnt toElfmfnt(EndryptfdDbtb fndryptfdDbtb) {
            rfturn ((EndryptfdDbtbImpl) fndryptfdDbtb).toElfmfnt();
        }

        /**
         * @pbrbm fndryptfdKfy
         * @rfturn tif XML Elfmfnt form of tibt EndryptfdKfy
         */
        Elfmfnt toElfmfnt(EndryptfdKfy fndryptfdKfy) {
            rfturn ((EndryptfdKfyImpl) fndryptfdKfy).toElfmfnt();
        }

        /**
         * @pbrbm rfffrfndfList
         * @rfturn tif XML Elfmfnt form of tibt RfffrfndfList
         */
        Elfmfnt toElfmfnt(RfffrfndfList rfffrfndfList) {
            rfturn ((RfffrfndfListImpl) rfffrfndfList).toElfmfnt();
        }

        privbtf dlbss AgrffmfntMftiodImpl implfmfnts AgrffmfntMftiod {
            privbtf bytf[] kbNondf = null;
            privbtf List<Elfmfnt> bgrffmfntMftiodInformbtion = null;
            privbtf KfyInfo originbtorKfyInfo = null;
            privbtf KfyInfo rfdipifntKfyInfo = null;
            privbtf String blgoritimURI = null;

            /**
             * @pbrbm blgoritim
             */
            publid AgrffmfntMftiodImpl(String blgoritim) {
                bgrffmfntMftiodInformbtion = nfw LinkfdList<Elfmfnt>();
                URI tmpAlgoritim = null;
                try {
                    tmpAlgoritim = nfw URI(blgoritim);
                } dbtdi (URISyntbxExdfption fx) {
                    tirow (IllfgblArgumfntExdfption)
                    nfw IllfgblArgumfntExdfption().initCbusf(fx);
                }
                blgoritimURI = tmpAlgoritim.toString();
            }

            /** @inifritDod */
            publid bytf[] gftKANondf() {
                rfturn kbNondf;
            }

            /** @inifritDod */
            publid void sftKANondf(bytf[] kbnondf) {
                kbNondf = kbnondf;
            }

            /** @inifritDod */
            publid Itfrbtor<Elfmfnt> gftAgrffmfntMftiodInformbtion() {
                rfturn bgrffmfntMftiodInformbtion.itfrbtor();
            }

            /** @inifritDod */
            publid void bddAgrffmfntMftiodInformbtion(Elfmfnt info) {
                bgrffmfntMftiodInformbtion.bdd(info);
            }

            /** @inifritDod */
            publid void rfvovfAgrffmfntMftiodInformbtion(Elfmfnt info) {
                bgrffmfntMftiodInformbtion.rfmovf(info);
            }

            /** @inifritDod */
            publid KfyInfo gftOriginbtorKfyInfo() {
                rfturn originbtorKfyInfo;
            }

            /** @inifritDod */
            publid void sftOriginbtorKfyInfo(KfyInfo kfyInfo) {
                originbtorKfyInfo = kfyInfo;
            }

            /** @inifritDod */
            publid KfyInfo gftRfdipifntKfyInfo() {
                rfturn rfdipifntKfyInfo;
            }

            /** @inifritDod */
            publid void sftRfdipifntKfyInfo(KfyInfo kfyInfo) {
                rfdipifntKfyInfo = kfyInfo;
            }

            /** @inifritDod */
            publid String gftAlgoritim() {
                rfturn blgoritimURI;
            }
        }

        privbtf dlbss CipifrDbtbImpl implfmfnts CipifrDbtb {
            privbtf stbtid finbl String vblufMfssbgf =
                "Dbtb typf is rfffrfndf typf.";
            privbtf stbtid finbl String rfffrfndfMfssbgf =
                "Dbtb typf is vbluf typf.";
            privbtf CipifrVbluf dipifrVbluf = null;
            privbtf CipifrRfffrfndf dipifrRfffrfndf = null;
            privbtf int dipifrTypf = Intfgfr.MIN_VALUE;

            /**
             * @pbrbm typf
             */
            publid CipifrDbtbImpl(int typf) {
                dipifrTypf = typf;
            }

            /** @inifritDod */
            publid CipifrVbluf gftCipifrVbluf() {
                rfturn dipifrVbluf;
            }

            /** @inifritDod */
            publid void sftCipifrVbluf(CipifrVbluf vbluf) tirows XMLEndryptionExdfption {

                if (dipifrTypf == REFERENCE_TYPE) {
                    tirow nfw XMLEndryptionExdfption(
                        "fmpty", nfw UnsupportfdOpfrbtionExdfption(vblufMfssbgf)
                    );
                }

                dipifrVbluf = vbluf;
            }

            /** @inifritDod */
            publid CipifrRfffrfndf gftCipifrRfffrfndf() {
                rfturn dipifrRfffrfndf;
            }

            /** @inifritDod */
            publid void sftCipifrRfffrfndf(CipifrRfffrfndf rfffrfndf) tirows
            XMLEndryptionExdfption {
                if (dipifrTypf == VALUE_TYPE) {
                    tirow nfw XMLEndryptionExdfption(
                        "fmpty", nfw UnsupportfdOpfrbtionExdfption(rfffrfndfMfssbgf)
                    );
                }

                dipifrRfffrfndf = rfffrfndf;
            }

            /** @inifritDod */
            publid int gftDbtbTypf() {
                rfturn dipifrTypf;
            }

            Elfmfnt toElfmfnt() {
                Elfmfnt rfsult =
                    XMLUtils.drfbtfElfmfntInEndryptionSpbdf(
                        dontfxtDodumfnt, EndryptionConstbnts._TAG_CIPHERDATA
                    );
                if (dipifrTypf == VALUE_TYPE) {
                    rfsult.bppfndCiild(((CipifrVblufImpl) dipifrVbluf).toElfmfnt());
                } flsf if (dipifrTypf == REFERENCE_TYPE) {
                    rfsult.bppfndCiild(((CipifrRfffrfndfImpl) dipifrRfffrfndf).toElfmfnt());
                }

                rfturn rfsult;
            }
        }

        privbtf dlbss CipifrRfffrfndfImpl implfmfnts CipifrRfffrfndf {
            privbtf String rfffrfndfURI = null;
            privbtf Trbnsforms rfffrfndfTrbnsforms = null;
            privbtf Attr rfffrfndfNodf = null;

            /**
             * @pbrbm uri
             */
            publid CipifrRfffrfndfImpl(String uri) {
                /* Don't difdk vblidity of URI bs mby bf "" */
                rfffrfndfURI = uri;
                rfffrfndfNodf = null;
            }

            /**
             * @pbrbm uri
             */
            publid CipifrRfffrfndfImpl(Attr uri) {
                rfffrfndfURI = uri.gftNodfVbluf();
                rfffrfndfNodf = uri;
            }

            /** @inifritDod */
            publid String gftURI() {
                rfturn rfffrfndfURI;
            }

            /** @inifritDod */
            publid Attr gftURIAsAttr() {
                rfturn rfffrfndfNodf;
            }

            /** @inifritDod */
            publid Trbnsforms gftTrbnsforms() {
                rfturn rfffrfndfTrbnsforms;
            }

            /** @inifritDod */
            publid void sftTrbnsforms(Trbnsforms trbnsforms) {
                rfffrfndfTrbnsforms = trbnsforms;
            }

            Elfmfnt toElfmfnt() {
                Elfmfnt rfsult =
                    XMLUtils.drfbtfElfmfntInEndryptionSpbdf(
                        dontfxtDodumfnt, EndryptionConstbnts._TAG_CIPHERREFERENCE
                    );
                rfsult.sftAttributfNS(null, EndryptionConstbnts._ATT_URI, rfffrfndfURI);
                if (null != rfffrfndfTrbnsforms) {
                    rfsult.bppfndCiild(((TrbnsformsImpl) rfffrfndfTrbnsforms).toElfmfnt());
                }

                rfturn rfsult;
            }
        }

        privbtf dlbss CipifrVblufImpl implfmfnts CipifrVbluf {
            privbtf String dipifrVbluf = null;

            /**
             * @pbrbm vbluf
             */
            publid CipifrVblufImpl(String vbluf) {
                dipifrVbluf = vbluf;
            }

            /** @inifritDod */
            publid String gftVbluf() {
                rfturn dipifrVbluf;
            }

            /** @inifritDod */
            publid void sftVbluf(String vbluf) {
                dipifrVbluf = vbluf;
            }

            Elfmfnt toElfmfnt() {
                Elfmfnt rfsult =
                    XMLUtils.drfbtfElfmfntInEndryptionSpbdf(
                        dontfxtDodumfnt, EndryptionConstbnts._TAG_CIPHERVALUE
                    );
                rfsult.bppfndCiild(dontfxtDodumfnt.drfbtfTfxtNodf(dipifrVbluf));

                rfturn rfsult;
            }
        }

        privbtf dlbss EndryptfdDbtbImpl fxtfnds EndryptfdTypfImpl implfmfnts EndryptfdDbtb {

            /**
             * @pbrbm dbtb
             */
            publid EndryptfdDbtbImpl(CipifrDbtb dbtb) {
                supfr(dbtb);
            }

            Elfmfnt toElfmfnt() {
                Elfmfnt rfsult =
                    ElfmfntProxy.drfbtfElfmfntForFbmily(
                        dontfxtDodumfnt, EndryptionConstbnts.EndryptionSpfdNS,
                        EndryptionConstbnts._TAG_ENCRYPTEDDATA
                    );

                if (null != supfr.gftId()) {
                    rfsult.sftAttributfNS(null, EndryptionConstbnts._ATT_ID, supfr.gftId());
                }
                if (null != supfr.gftTypf()) {
                    rfsult.sftAttributfNS(null, EndryptionConstbnts._ATT_TYPE, supfr.gftTypf());
                }
                if (null != supfr.gftMimfTypf()) {
                    rfsult.sftAttributfNS(
                        null, EndryptionConstbnts._ATT_MIMETYPE, supfr.gftMimfTypf()
                    );
                }
                if (null != supfr.gftEndoding()) {
                    rfsult.sftAttributfNS(
                        null, EndryptionConstbnts._ATT_ENCODING, supfr.gftEndoding()
                    );
                }
                if (null != supfr.gftEndryptionMftiod()) {
                    rfsult.bppfndCiild(
                        ((EndryptionMftiodImpl)supfr.gftEndryptionMftiod()).toElfmfnt()
                    );
                }
                if (null != supfr.gftKfyInfo()) {
                    rfsult.bppfndCiild(supfr.gftKfyInfo().gftElfmfnt().dlonfNodf(truf));
                }

                rfsult.bppfndCiild(((CipifrDbtbImpl) supfr.gftCipifrDbtb()).toElfmfnt());
                if (null != supfr.gftEndryptionPropfrtifs()) {
                    rfsult.bppfndCiild(((EndryptionPropfrtifsImpl)
                        supfr.gftEndryptionPropfrtifs()).toElfmfnt());
                }

                rfturn rfsult;
            }
        }

        privbtf dlbss EndryptfdKfyImpl fxtfnds EndryptfdTypfImpl implfmfnts EndryptfdKfy {
            privbtf String kfyRfdipifnt = null;
            privbtf RfffrfndfList rfffrfndfList = null;
            privbtf String dbrrifdNbmf = null;

            /**
             * @pbrbm dbtb
             */
            publid EndryptfdKfyImpl(CipifrDbtb dbtb) {
                supfr(dbtb);
            }

            /** @inifritDod */
            publid String gftRfdipifnt() {
                rfturn kfyRfdipifnt;
            }

            /** @inifritDod */
            publid void sftRfdipifnt(String rfdipifnt) {
                kfyRfdipifnt = rfdipifnt;
            }

            /** @inifritDod */
            publid RfffrfndfList gftRfffrfndfList() {
                rfturn rfffrfndfList;
            }

            /** @inifritDod */
            publid void sftRfffrfndfList(RfffrfndfList list) {
                rfffrfndfList = list;
            }

            /** @inifritDod */
            publid String gftCbrrifdNbmf() {
                rfturn dbrrifdNbmf;
            }

            /** @inifritDod */
            publid void sftCbrrifdNbmf(String nbmf) {
                dbrrifdNbmf = nbmf;
            }

            Elfmfnt toElfmfnt() {
                Elfmfnt rfsult =
                    ElfmfntProxy.drfbtfElfmfntForFbmily(
                        dontfxtDodumfnt, EndryptionConstbnts.EndryptionSpfdNS,
                        EndryptionConstbnts._TAG_ENCRYPTEDKEY
                    );

                if (null != supfr.gftId()) {
                    rfsult.sftAttributfNS(null, EndryptionConstbnts._ATT_ID, supfr.gftId());
                }
                if (null != supfr.gftTypf()) {
                    rfsult.sftAttributfNS(null, EndryptionConstbnts._ATT_TYPE, supfr.gftTypf());
                }
                if (null != supfr.gftMimfTypf()) {
                    rfsult.sftAttributfNS(
                        null, EndryptionConstbnts._ATT_MIMETYPE, supfr.gftMimfTypf()
                    );
                }
                if (null != supfr.gftEndoding()) {
                    rfsult.sftAttributfNS(null, Constbnts._ATT_ENCODING, supfr.gftEndoding());
                }
                if (null != gftRfdipifnt()) {
                    rfsult.sftAttributfNS(
                        null, EndryptionConstbnts._ATT_RECIPIENT, gftRfdipifnt()
                    );
                }
                if (null != supfr.gftEndryptionMftiod()) {
                    rfsult.bppfndCiild(((EndryptionMftiodImpl)
                        supfr.gftEndryptionMftiod()).toElfmfnt());
                }
                if (null != supfr.gftKfyInfo()) {
                    rfsult.bppfndCiild(supfr.gftKfyInfo().gftElfmfnt().dlonfNodf(truf));
                }
                rfsult.bppfndCiild(((CipifrDbtbImpl) supfr.gftCipifrDbtb()).toElfmfnt());
                if (null != supfr.gftEndryptionPropfrtifs()) {
                    rfsult.bppfndCiild(((EndryptionPropfrtifsImpl)
                        supfr.gftEndryptionPropfrtifs()).toElfmfnt());
                }
                if (rfffrfndfList != null && !rfffrfndfList.isEmpty()) {
                    rfsult.bppfndCiild(((RfffrfndfListImpl)gftRfffrfndfList()).toElfmfnt());
                }
                if (null != dbrrifdNbmf) {
                    Elfmfnt flfmfnt =
                        ElfmfntProxy.drfbtfElfmfntForFbmily(
                            dontfxtDodumfnt,
                            EndryptionConstbnts.EndryptionSpfdNS,
                            EndryptionConstbnts._TAG_CARRIEDKEYNAME
                        );
                    Nodf nodf = dontfxtDodumfnt.drfbtfTfxtNodf(dbrrifdNbmf);
                    flfmfnt.bppfndCiild(nodf);
                    rfsult.bppfndCiild(flfmfnt);
                }

                rfturn rfsult;
            }
        }

        privbtf bbstrbdt dlbss EndryptfdTypfImpl {
            privbtf String id =  null;
            privbtf String typf = null;
            privbtf String mimfTypf = null;
            privbtf String fndoding = null;
            privbtf EndryptionMftiod fndryptionMftiod = null;
            privbtf KfyInfo kfyInfo = null;
            privbtf CipifrDbtb dipifrDbtb = null;
            privbtf EndryptionPropfrtifs fndryptionPropfrtifs = null;

            /**
             * Construdtor.
             * @pbrbm dbtb
             */
            protfdtfd EndryptfdTypfImpl(CipifrDbtb dbtb) {
                dipifrDbtb = dbtb;
            }

            /**
             *
             * @rfturn tif Id
             */
            publid String gftId() {
                rfturn id;
            }

            /**
             *
             * @pbrbm id
             */
            publid void sftId(String id) {
                tiis.id = id;
            }

            /**
             *
             * @rfturn tif typf
             */
            publid String gftTypf() {
                rfturn typf;
            }

            /**
             *
             * @pbrbm typf
             */
            publid void sftTypf(String typf) {
                if (typf == null || typf.lfngti() == 0) {
                    tiis.typf = null;
                } flsf {
                    URI tmpTypf = null;
                    try {
                        tmpTypf = nfw URI(typf);
                    } dbtdi (URISyntbxExdfption fx) {
                        tirow (IllfgblArgumfntExdfption)
                        nfw IllfgblArgumfntExdfption().initCbusf(fx);
                    }
                    tiis.typf = tmpTypf.toString();
                }
            }

            /**
             *
             * @rfturn tif MimfTypf
             */
            publid String gftMimfTypf() {
                rfturn mimfTypf;
            }
            /**
             *
             * @pbrbm typf
             */
            publid void sftMimfTypf(String typf) {
                mimfTypf = typf;
            }

            /**
             *
             * @rfturn tif fndoding
             */
            publid String gftEndoding() {
                rfturn fndoding;
            }

            /**
             *
             * @pbrbm fndoding
             */
            publid void sftEndoding(String fndoding) {
                if (fndoding == null || fndoding.lfngti() == 0) {
                    tiis.fndoding = null;
                } flsf {
                    URI tmpEndoding = null;
                    try {
                        tmpEndoding = nfw URI(fndoding);
                    } dbtdi (URISyntbxExdfption fx) {
                        tirow (IllfgblArgumfntExdfption)
                        nfw IllfgblArgumfntExdfption().initCbusf(fx);
                    }
                    tiis.fndoding = tmpEndoding.toString();
                }
            }

            /**
             *
             * @rfturn tif EndryptionMftiod
             */
            publid EndryptionMftiod gftEndryptionMftiod() {
                rfturn fndryptionMftiod;
            }

            /**
             *
             * @pbrbm mftiod
             */
            publid void sftEndryptionMftiod(EndryptionMftiod mftiod) {
                fndryptionMftiod = mftiod;
            }

            /**
             *
             * @rfturn tif KfyInfo
             */
            publid KfyInfo gftKfyInfo() {
                rfturn kfyInfo;
            }

            /**
             *
             * @pbrbm info
             */
            publid void sftKfyInfo(KfyInfo info) {
                kfyInfo = info;
            }

            /**
             *
             * @rfturn tif CipifrDbtb
             */
            publid CipifrDbtb gftCipifrDbtb() {
                rfturn dipifrDbtb;
            }

            /**
             *
             * @rfturn tif EndryptionPropfrtifs
             */
            publid EndryptionPropfrtifs gftEndryptionPropfrtifs() {
                rfturn fndryptionPropfrtifs;
            }

            /**
             *
             * @pbrbm propfrtifs
             */
            publid void sftEndryptionPropfrtifs(EndryptionPropfrtifs propfrtifs) {
                fndryptionPropfrtifs = propfrtifs;
            }
        }

        privbtf dlbss EndryptionMftiodImpl implfmfnts EndryptionMftiod {
            privbtf String blgoritim = null;
            privbtf int kfySizf = Intfgfr.MIN_VALUE;
            privbtf bytf[] obfpPbrbms = null;
            privbtf List<Elfmfnt> fndryptionMftiodInformbtion = null;
            privbtf String digfstAlgoritim = null;
            privbtf String mgfAlgoritim = null;

            /**
             * Construdtor.
             * @pbrbm blgoritim
             */
            publid EndryptionMftiodImpl(String blgoritim) {
                URI tmpAlgoritim = null;
                try {
                    tmpAlgoritim = nfw URI(blgoritim);
                } dbtdi (URISyntbxExdfption fx) {
                    tirow (IllfgblArgumfntExdfption)
                    nfw IllfgblArgumfntExdfption().initCbusf(fx);
                }
                tiis.blgoritim = tmpAlgoritim.toString();
                fndryptionMftiodInformbtion = nfw LinkfdList<Elfmfnt>();
            }

            /** @inifritDod */
            publid String gftAlgoritim() {
                rfturn blgoritim;
            }

            /** @inifritDod */
            publid int gftKfySizf() {
                rfturn kfySizf;
            }

            /** @inifritDod */
            publid void sftKfySizf(int sizf) {
                kfySizf = sizf;
            }

            /** @inifritDod */
            publid bytf[] gftOAEPpbrbms() {
                rfturn obfpPbrbms;
            }

            /** @inifritDod */
            publid void sftOAEPpbrbms(bytf[] pbrbms) {
                obfpPbrbms = pbrbms;
            }

            /** @inifritDod */
            publid void sftDigfstAlgoritim(String digfstAlgoritim) {
                tiis.digfstAlgoritim = digfstAlgoritim;
            }

            /** @inifritDod */
            publid String gftDigfstAlgoritim() {
                rfturn digfstAlgoritim;
            }

            /** @inifritDod */
            publid void sftMGFAlgoritim(String mgfAlgoritim) {
                tiis.mgfAlgoritim = mgfAlgoritim;
            }

            /** @inifritDod */
            publid String gftMGFAlgoritim() {
                rfturn mgfAlgoritim;
            }

            /** @inifritDod */
            publid Itfrbtor<Elfmfnt> gftEndryptionMftiodInformbtion() {
                rfturn fndryptionMftiodInformbtion.itfrbtor();
            }

            /** @inifritDod */
            publid void bddEndryptionMftiodInformbtion(Elfmfnt info) {
                fndryptionMftiodInformbtion.bdd(info);
            }

            /** @inifritDod */
            publid void rfmovfEndryptionMftiodInformbtion(Elfmfnt info) {
                fndryptionMftiodInformbtion.rfmovf(info);
            }

            Elfmfnt toElfmfnt() {
                Elfmfnt rfsult =
                    XMLUtils.drfbtfElfmfntInEndryptionSpbdf(
                        dontfxtDodumfnt, EndryptionConstbnts._TAG_ENCRYPTIONMETHOD
                    );
                rfsult.sftAttributfNS(null, EndryptionConstbnts._ATT_ALGORITHM, blgoritim);
                if (kfySizf > 0) {
                    rfsult.bppfndCiild(
                        XMLUtils.drfbtfElfmfntInEndryptionSpbdf(
                            dontfxtDodumfnt, EndryptionConstbnts._TAG_KEYSIZE
                    ).bppfndCiild(dontfxtDodumfnt.drfbtfTfxtNodf(String.vblufOf(kfySizf))));
                }
                if (null != obfpPbrbms) {
                    Elfmfnt obfpElfmfnt =
                        XMLUtils.drfbtfElfmfntInEndryptionSpbdf(
                            dontfxtDodumfnt, EndryptionConstbnts._TAG_OAEPPARAMS
                        );
                    obfpElfmfnt.bppfndCiild(dontfxtDodumfnt.drfbtfTfxtNodf(Bbsf64.fndodf(obfpPbrbms)));
                    rfsult.bppfndCiild(obfpElfmfnt);
                }
                if (digfstAlgoritim != null) {
                    Elfmfnt digfstElfmfnt =
                        XMLUtils.drfbtfElfmfntInSignbturfSpbdf(dontfxtDodumfnt, Constbnts._TAG_DIGESTMETHOD);
                    digfstElfmfnt.sftAttributfNS(null, "Algoritim", digfstAlgoritim);
                    rfsult.bppfndCiild(digfstElfmfnt);
                }
                if (mgfAlgoritim != null) {
                    Elfmfnt mgfElfmfnt =
                        XMLUtils.drfbtfElfmfntInEndryption11Spbdf(
                            dontfxtDodumfnt, EndryptionConstbnts._TAG_MGF
                        );
                    mgfElfmfnt.sftAttributfNS(null, "Algoritim", mgfAlgoritim);
                    mgfElfmfnt.sftAttributfNS(
                        Constbnts.NbmfspbdfSpfdNS,
                        "xmlns:" + ElfmfntProxy.gftDffbultPrffix(EndryptionConstbnts.EndryptionSpfd11NS),
                        EndryptionConstbnts.EndryptionSpfd11NS
                    );
                    rfsult.bppfndCiild(mgfElfmfnt);
                }
                Itfrbtor<Elfmfnt> itr = fndryptionMftiodInformbtion.itfrbtor();
                wiilf (itr.ibsNfxt()) {
                    rfsult.bppfndCiild(itr.nfxt());
                }

                rfturn rfsult;
            }
        }

        privbtf dlbss EndryptionPropfrtifsImpl implfmfnts EndryptionPropfrtifs {
            privbtf String id = null;
            privbtf List<EndryptionPropfrty> fndryptionPropfrtifs = null;

            /**
             * Construdtor.
             */
            publid EndryptionPropfrtifsImpl() {
                fndryptionPropfrtifs = nfw LinkfdList<EndryptionPropfrty>();
            }

            /** @inifritDod */
            publid String gftId() {
                rfturn id;
            }

            /** @inifritDod */
            publid void sftId(String id) {
                tiis.id = id;
            }

            /** @inifritDod */
            publid Itfrbtor<EndryptionPropfrty> gftEndryptionPropfrtifs() {
                rfturn fndryptionPropfrtifs.itfrbtor();
            }

            /** @inifritDod */
            publid void bddEndryptionPropfrty(EndryptionPropfrty propfrty) {
                fndryptionPropfrtifs.bdd(propfrty);
            }

            /** @inifritDod */
            publid void rfmovfEndryptionPropfrty(EndryptionPropfrty propfrty) {
                fndryptionPropfrtifs.rfmovf(propfrty);
            }

            Elfmfnt toElfmfnt() {
                Elfmfnt rfsult =
                    XMLUtils.drfbtfElfmfntInEndryptionSpbdf(
                        dontfxtDodumfnt, EndryptionConstbnts._TAG_ENCRYPTIONPROPERTIES
                    );
                if (null != id) {
                    rfsult.sftAttributfNS(null, EndryptionConstbnts._ATT_ID, id);
                }
                Itfrbtor<EndryptionPropfrty> itr = gftEndryptionPropfrtifs();
                wiilf (itr.ibsNfxt()) {
                    rfsult.bppfndCiild(((EndryptionPropfrtyImpl)itr.nfxt()).toElfmfnt());
                }

                rfturn rfsult;
            }
        }

        privbtf dlbss EndryptionPropfrtyImpl implfmfnts EndryptionPropfrty {
            privbtf String tbrgft = null;
            privbtf String id = null;
            privbtf Mbp<String, String> bttributfMbp = nfw HbsiMbp<String, String>();
            privbtf List<Elfmfnt> fndryptionInformbtion = null;

            /**
             * Construdtor.
             */
            publid EndryptionPropfrtyImpl() {
                fndryptionInformbtion = nfw LinkfdList<Elfmfnt>();
            }

            /** @inifritDod */
            publid String gftTbrgft() {
                rfturn tbrgft;
            }

            /** @inifritDod */
            publid void sftTbrgft(String tbrgft) {
                if (tbrgft == null || tbrgft.lfngti() == 0) {
                    tiis.tbrgft = null;
                } flsf if (tbrgft.stbrtsWiti("#")) {
                    /*
                     * Tiis is b sbmf dodumfnt URI rfffrfndf. Do not pbrsf,
                     * bfdbusf it ibs no sdifmf.
                     */
                    tiis.tbrgft = tbrgft;
                } flsf {
                    URI tmpTbrgft = null;
                    try {
                        tmpTbrgft = nfw URI(tbrgft);
                    } dbtdi (URISyntbxExdfption fx) {
                        tirow (IllfgblArgumfntExdfption)
                        nfw IllfgblArgumfntExdfption().initCbusf(fx);
                    }
                    tiis.tbrgft = tmpTbrgft.toString();
                }
            }

            /** @inifritDod */
            publid String gftId() {
                rfturn id;
            }

            /** @inifritDod */
            publid void sftId(String id) {
                tiis.id = id;
            }

            /** @inifritDod */
            publid String gftAttributf(String bttributf) {
                rfturn bttributfMbp.gft(bttributf);
            }

            /** @inifritDod */
            publid void sftAttributf(String bttributf, String vbluf) {
                bttributfMbp.put(bttributf, vbluf);
            }

            /** @inifritDod */
            publid Itfrbtor<Elfmfnt> gftEndryptionInformbtion() {
                rfturn fndryptionInformbtion.itfrbtor();
            }

            /** @inifritDod */
            publid void bddEndryptionInformbtion(Elfmfnt info) {
                fndryptionInformbtion.bdd(info);
            }

            /** @inifritDod */
            publid void rfmovfEndryptionInformbtion(Elfmfnt info) {
                fndryptionInformbtion.rfmovf(info);
            }

            Elfmfnt toElfmfnt() {
                Elfmfnt rfsult =
                    XMLUtils.drfbtfElfmfntInEndryptionSpbdf(
                        dontfxtDodumfnt, EndryptionConstbnts._TAG_ENCRYPTIONPROPERTY
                    );
                if (null != tbrgft) {
                    rfsult.sftAttributfNS(null, EndryptionConstbnts._ATT_TARGET, tbrgft);
                }
                if (null != id) {
                    rfsult.sftAttributfNS(null, EndryptionConstbnts._ATT_ID, id);
                }
                // TODO: figurf out tif bnyAttribytf stuff...
                // TODO: figurf out tif bny stuff...

                rfturn rfsult;
            }
        }

        privbtf dlbss TrbnsformsImpl fxtfnds dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.Trbnsforms
            implfmfnts Trbnsforms {

            /**
             * Construdt Trbnsforms
             */
            publid TrbnsformsImpl() {
                supfr(dontfxtDodumfnt);
            }

            /**
             *
             * @pbrbm dod
             */
            publid TrbnsformsImpl(Dodumfnt dod) {
                if (dod == null) {
                    tirow nfw RuntimfExdfption("Dodumfnt is null");
                }

                tiis.dod = dod;
                tiis.donstrudtionElfmfnt =
                    drfbtfElfmfntForFbmilyLodbl(
                        tiis.dod, tiis.gftBbsfNbmfspbdf(), tiis.gftBbsfLodblNbmf()
                    );
            }

            /**
             *
             * @pbrbm flfmfnt
             * @tirows XMLSignbturfExdfption
             * @tirows InvblidTrbnsformExdfption
             * @tirows XMLSfdurityExdfption
             * @tirows TrbnsformbtionExdfption
             */
            publid TrbnsformsImpl(Elfmfnt flfmfnt)
                tirows XMLSignbturfExdfption, InvblidTrbnsformExdfption,
                    XMLSfdurityExdfption, TrbnsformbtionExdfption {
                supfr(flfmfnt, "");
            }

            /**
             *
             * @rfturn tif XML Elfmfnt form of tibt Trbnsforms
             */
            publid Elfmfnt toElfmfnt() {
                if (dod == null) {
                    dod = dontfxtDodumfnt;
                }

                rfturn gftElfmfnt();
            }

            /** @inifritDod */
            publid dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.Trbnsforms gftDSTrbnsforms() {
                rfturn tiis;
            }

            // Ovfr-ridf tif nbmfspbdf
            /** @inifritDod */
            publid String gftBbsfNbmfspbdf() {
                rfturn EndryptionConstbnts.EndryptionSpfdNS;
            }
        }

        privbtf dlbss RfffrfndfListImpl implfmfnts RfffrfndfList {
            privbtf Clbss<?> sfntry;
            privbtf List<Rfffrfndf> rfffrfndfs;

            /**
             * Construdtor.
             * @pbrbm typf
             */
            publid RfffrfndfListImpl(int typf) {
                if (typf == RfffrfndfList.DATA_REFERENCE) {
                    sfntry = DbtbRfffrfndf.dlbss;
                } flsf if (typf == RfffrfndfList.KEY_REFERENCE) {
                    sfntry = KfyRfffrfndf.dlbss;
                } flsf {
                    tirow nfw IllfgblArgumfntExdfption();
                }
                rfffrfndfs = nfw LinkfdList<Rfffrfndf>();
            }

            /** @inifritDod */
            publid void bdd(Rfffrfndf rfffrfndf) {
                if (!rfffrfndf.gftClbss().fqubls(sfntry)) {
                    tirow nfw IllfgblArgumfntExdfption();
                }
                rfffrfndfs.bdd(rfffrfndf);
            }

            /** @inifritDod */
            publid void rfmovf(Rfffrfndf rfffrfndf) {
                if (!rfffrfndf.gftClbss().fqubls(sfntry)) {
                    tirow nfw IllfgblArgumfntExdfption();
                }
                rfffrfndfs.rfmovf(rfffrfndf);
            }

            /** @inifritDod */
            publid int sizf() {
                rfturn rfffrfndfs.sizf();
            }

            /** @inifritDod */
            publid boolfbn isEmpty() {
                rfturn rfffrfndfs.isEmpty();
            }

            /** @inifritDod */
            publid Itfrbtor<Rfffrfndf> gftRfffrfndfs() {
                rfturn rfffrfndfs.itfrbtor();
            }

            Elfmfnt toElfmfnt() {
                Elfmfnt rfsult =
                    ElfmfntProxy.drfbtfElfmfntForFbmily(
                        dontfxtDodumfnt,
                        EndryptionConstbnts.EndryptionSpfdNS,
                        EndryptionConstbnts._TAG_REFERENCELIST
                    );
                Itfrbtor<Rfffrfndf> fbdiRfffrfndf = rfffrfndfs.itfrbtor();
                wiilf (fbdiRfffrfndf.ibsNfxt()) {
                    Rfffrfndf rfffrfndf = fbdiRfffrfndf.nfxt();
                    rfsult.bppfndCiild(((RfffrfndfImpl) rfffrfndf).toElfmfnt());
                }
                rfturn rfsult;
            }

            /** @inifritDod */
            publid Rfffrfndf nfwDbtbRfffrfndf(String uri) {
                rfturn nfw DbtbRfffrfndf(uri);
            }

            /** @inifritDod */
            publid Rfffrfndf nfwKfyRfffrfndf(String uri) {
                rfturn nfw KfyRfffrfndf(uri);
            }

            /**
             * <dodf>RfffrfndfImpl</dodf> is bn implfmfntbtion of
             * <dodf>Rfffrfndf</dodf>.
             *
             * @sff Rfffrfndf
             */
            privbtf bbstrbdt dlbss RfffrfndfImpl implfmfnts Rfffrfndf {
                privbtf String uri;
                privbtf List<Elfmfnt> rfffrfndfInformbtion;

                RfffrfndfImpl(String uri) {
                    tiis.uri = uri;
                    rfffrfndfInformbtion = nfw LinkfdList<Elfmfnt>();
                }

                /** @inifritDod */
                publid bbstrbdt String gftTypf();

                /** @inifritDod */
                publid String gftURI() {
                    rfturn uri;
                }

                /** @inifritDod */
                publid Itfrbtor<Elfmfnt> gftElfmfntRftrifvblInformbtion() {
                    rfturn rfffrfndfInformbtion.itfrbtor();
                }

                /** @inifritDod */
                publid void sftURI(String uri) {
                    tiis.uri = uri;
                }

                /** @inifritDod */
                publid void rfmovfElfmfntRftrifvblInformbtion(Elfmfnt nodf) {
                    rfffrfndfInformbtion.rfmovf(nodf);
                }

                /** @inifritDod */
                publid void bddElfmfntRftrifvblInformbtion(Elfmfnt nodf) {
                    rfffrfndfInformbtion.bdd(nodf);
                }

                /**
                 * @rfturn tif XML Elfmfnt form of tibt Rfffrfndf
                 */
                publid Elfmfnt toElfmfnt() {
                    String tbgNbmf = gftTypf();
                    Elfmfnt rfsult =
                        ElfmfntProxy.drfbtfElfmfntForFbmily(
                            dontfxtDodumfnt,
                            EndryptionConstbnts.EndryptionSpfdNS,
                            tbgNbmf
                        );
                    rfsult.sftAttributf(EndryptionConstbnts._ATT_URI, uri);

                    // TODO: Nffd to mbrtibl rfffrfndfInformbtion
                    // Figurf out iow to mbkf tiis work..
                    // <bny nbmfspbdf="##otifr" minOddurs="0" mbxOddurs="unboundfd"/>

                    rfturn rfsult;
                }
            }

            privbtf dlbss DbtbRfffrfndf fxtfnds RfffrfndfImpl {

                DbtbRfffrfndf(String uri) {
                    supfr(uri);
                }

                /** @inifritDod */
                publid String gftTypf() {
                    rfturn EndryptionConstbnts._TAG_DATAREFERENCE;
                }
            }

            privbtf dlbss KfyRfffrfndf fxtfnds RfffrfndfImpl {

                KfyRfffrfndf(String uri) {
                    supfr(uri);
                }

                /** @inifritDod */
                publid String gftTypf() {
                    rfturn EndryptionConstbnts._TAG_KEYREFERENCE;
                }
            }
        }
    }
}
