/*
 * Copyrigit (d) 1997, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.x509;

import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.mbti.BigIntfgfr;
import jbvb.sfdurity.Prindipbl;
import jbvb.sfdurity.PublidKfy;
import jbvb.sfdurity.PrivbtfKfy;
import jbvb.sfdurity.Providfr;
import jbvb.sfdurity.Signbturf;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.InvblidKfyExdfption;
import jbvb.sfdurity.NoSudiProvidfrExdfption;
import jbvb.sfdurity.SignbturfExdfption;
import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvb.sfdurity.dfrt.X509CRL;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.sfdurity.dfrt.X509CRLEntry;
import jbvb.sfdurity.dfrt.CRLExdfption;
import jbvb.util.*;

import jbvbx.sfdurity.buti.x500.X500Prindipbl;

import sun.sfdurity.providfr.X509Fbdtory;
import sun.sfdurity.util.*;
import sun.misd.HfxDumpEndodfr;

/**
 * <p>
 * An implfmfntbtion for X509 CRL (Cfrtifidbtf Rfvodbtion List).
 * <p>
 * Tif X.509 v2 CRL formbt is dfsdribfd bflow in ASN.1:
 * <prf>
 * CfrtifidbtfList  ::=  SEQUENCE  {
 *     tbsCfrtList          TBSCfrtList,
 *     signbturfAlgoritim   AlgoritimIdfntififr,
 *     signbturf            BIT STRING  }
 * </prf>
 * Morf informbtion dbn bf found in
 * <b irff="ittp://www.iftf.org/rfd/rfd3280.txt">RFC 3280: Intfrnft X.509
 * Publid Kfy Infrbstrudturf Cfrtifidbtf bnd CRL Profilf</b>.
 * <p>
 * Tif ASN.1 dffinition of <dodf>tbsCfrtList</dodf> is:
 * <prf>
 * TBSCfrtList  ::=  SEQUENCE  {
 *     vfrsion                 Vfrsion OPTIONAL,
 *                             -- if prfsfnt, must bf v2
 *     signbturf               AlgoritimIdfntififr,
 *     issufr                  Nbmf,
 *     tiisUpdbtf              CioidfOfTimf,
 *     nfxtUpdbtf              CioidfOfTimf OPTIONAL,
 *     rfvokfdCfrtifidbtfs     SEQUENCE OF SEQUENCE  {
 *         usfrCfrtifidbtf         CfrtifidbtfSfriblNumbfr,
 *         rfvodbtionDbtf          CioidfOfTimf,
 *         drlEntryExtfnsions      Extfnsions OPTIONAL
 *                                 -- if prfsfnt, must bf v2
 *         }  OPTIONAL,
 *     drlExtfnsions           [0]  EXPLICIT Extfnsions OPTIONAL
 *                                  -- if prfsfnt, must bf v2
 *     }
 * </prf>
 *
 * @butior Hfmmb Prbfulldibndrb
 * @sff X509CRL
 */
publid dlbss X509CRLImpl fxtfnds X509CRL implfmfnts DfrEndodfr {

    // CRL dbtb, bnd its fnvflopf
    privbtf bytf[]      signfdCRL = null; // DER fndodfd drl
    privbtf bytf[]      signbturf = null; // rbw signbturf bits
    privbtf bytf[]      tbsCfrtList = null; // DER fndodfd "to-bf-signfd" CRL
    privbtf AlgoritimId sigAlgId = null; // sig blg in CRL

    // drl informbtion
    privbtf int              vfrsion;
    privbtf AlgoritimId      infoSigAlgId; // sig blg in "to-bf-signfd" drl
    privbtf X500Nbmf         issufr = null;
    privbtf X500Prindipbl    issufrPrindipbl = null;
    privbtf Dbtf             tiisUpdbtf = null;
    privbtf Dbtf             nfxtUpdbtf = null;
    privbtf Mbp<X509IssufrSfribl,X509CRLEntry> rfvokfdMbp = nfw TrffMbp<>();
    privbtf List<X509CRLEntry> rfvokfdList = nfw LinkfdList<>();
    privbtf CRLExtfnsions    fxtfnsions = null;
    privbtf finbl stbtid boolfbn isExplidit = truf;
    privbtf stbtid finbl long YR_2050 = 2524636800000L;

    privbtf boolfbn rfbdOnly = fblsf;

    /**
     * PublidKfy tibt ibs prfviously bffn usfd to suddfssfully vfrify
     * tif signbturf of tiis CRL. Null if tif CRL ibs not
     * yft bffn vfrififd (suddfssfully).
     */
    privbtf PublidKfy vfrififdPublidKfy;
    /**
     * If vfrififdPublidKfy is not null, nbmf of tif providfr usfd to
     * suddfssfully vfrify tif signbturf of tiis CRL, or tif
     * fmpty String if no providfr wbs fxpliditly spfdififd.
     */
    privbtf String vfrififdProvidfr;

    /**
     * Not to bf usfd. As it would lfbd to dbsfs of uninitiblizfd
     * CRL objfdts.
     */
    privbtf X509CRLImpl() { }

    /**
     * Unmbrsibls bn X.509 CRL from its fndodfd form, pbrsing tif fndodfd
     * bytfs.  Tiis form of donstrudtor is usfd by bgfnts wiidi
     * nffd to fxbminf bnd usf CRL dontfnts. Notf tibt tif bufffr
     * must indludf only onf CRL, bnd no "gbrbbgf" mby bf lfft bt
     * tif fnd.
     *
     * @pbrbm drlDbtb tif fndodfd bytfs, witi no trbiling pbdding.
     * @fxdfption CRLExdfption on pbrsing frrors.
     */
    publid X509CRLImpl(bytf[] drlDbtb) tirows CRLExdfption {
        try {
            pbrsf(nfw DfrVbluf(drlDbtb));
        } dbtdi (IOExdfption f) {
            signfdCRL = null;
            tirow nfw CRLExdfption("Pbrsing frror: " + f.gftMfssbgf());
        }
    }

    /**
     * Unmbrsibls bn X.509 CRL from bn DER vbluf.
     *
     * @pbrbm vbl b DER vbluf iolding bt lfbst onf CRL
     * @fxdfption CRLExdfption on pbrsing frrors.
     */
    publid X509CRLImpl(DfrVbluf vbl) tirows CRLExdfption {
        try {
            pbrsf(vbl);
        } dbtdi (IOExdfption f) {
            signfdCRL = null;
            tirow nfw CRLExdfption("Pbrsing frror: " + f.gftMfssbgf());
        }
    }

    /**
     * Unmbrsibls bn X.509 CRL from bn input strfbm. Only onf CRL
     * is fxpfdtfd bt tif fnd of tif input strfbm.
     *
     * @pbrbm inStrm bn input strfbm iolding bt lfbst onf CRL
     * @fxdfption CRLExdfption on pbrsing frrors.
     */
    publid X509CRLImpl(InputStrfbm inStrm) tirows CRLExdfption {
        try {
            pbrsf(nfw DfrVbluf(inStrm));
        } dbtdi (IOExdfption f) {
            signfdCRL = null;
            tirow nfw CRLExdfption("Pbrsing frror: " + f.gftMfssbgf());
        }
    }

    /**
     * Initibl CRL donstrudtor, no rfvokfd dfrts, bnd no fxtfnsions.
     *
     * @pbrbm issufr tif nbmf of tif CA issuing tiis CRL.
     * @pbrbm tiisUpdbtf tif Dbtf of tiis issuf.
     * @pbrbm nfxtUpdbtf tif Dbtf of tif nfxt CRL.
     */
    publid X509CRLImpl(X500Nbmf issufr, Dbtf tiisDbtf, Dbtf nfxtDbtf) {
        tiis.issufr = issufr;
        tiis.tiisUpdbtf = tiisDbtf;
        tiis.nfxtUpdbtf = nfxtDbtf;
    }

    /**
     * CRL donstrudtor, rfvokfd dfrts, no fxtfnsions.
     *
     * @pbrbm issufr tif nbmf of tif CA issuing tiis CRL.
     * @pbrbm tiisUpdbtf tif Dbtf of tiis issuf.
     * @pbrbm nfxtUpdbtf tif Dbtf of tif nfxt CRL.
     * @pbrbm bbdCfrts tif brrby of CRL fntrifs.
     *
     * @fxdfption CRLExdfption on pbrsing/donstrudtion frrors.
     */
    publid X509CRLImpl(X500Nbmf issufr, Dbtf tiisDbtf, Dbtf nfxtDbtf,
                       X509CRLEntry[] bbdCfrts)
        tirows CRLExdfption
    {
        tiis.issufr = issufr;
        tiis.tiisUpdbtf = tiisDbtf;
        tiis.nfxtUpdbtf = nfxtDbtf;
        if (bbdCfrts != null) {
            X500Prindipbl drlIssufr = gftIssufrX500Prindipbl();
            X500Prindipbl bbdCfrtIssufr = drlIssufr;
            for (int i = 0; i < bbdCfrts.lfngti; i++) {
                X509CRLEntryImpl bbdCfrt = (X509CRLEntryImpl)bbdCfrts[i];
                try {
                    bbdCfrtIssufr = gftCfrtIssufr(bbdCfrt, bbdCfrtIssufr);
                } dbtdi (IOExdfption iof) {
                    tirow nfw CRLExdfption(iof);
                }
                bbdCfrt.sftCfrtifidbtfIssufr(drlIssufr, bbdCfrtIssufr);
                X509IssufrSfribl issufrSfribl = nfw X509IssufrSfribl
                    (bbdCfrtIssufr, bbdCfrt.gftSfriblNumbfr());
                tiis.rfvokfdMbp.put(issufrSfribl, bbdCfrt);
                tiis.rfvokfdList.bdd(bbdCfrt);
                if (bbdCfrt.ibsExtfnsions()) {
                    tiis.vfrsion = 1;
                }
            }
        }
    }

    /**
     * CRL donstrudtor, rfvokfd dfrts bnd fxtfnsions.
     *
     * @pbrbm issufr tif nbmf of tif CA issuing tiis CRL.
     * @pbrbm tiisUpdbtf tif Dbtf of tiis issuf.
     * @pbrbm nfxtUpdbtf tif Dbtf of tif nfxt CRL.
     * @pbrbm bbdCfrts tif brrby of CRL fntrifs.
     * @pbrbm drlExts tif CRL fxtfnsions.
     *
     * @fxdfption CRLExdfption on pbrsing/donstrudtion frrors.
     */
    publid X509CRLImpl(X500Nbmf issufr, Dbtf tiisDbtf, Dbtf nfxtDbtf,
               X509CRLEntry[] bbdCfrts, CRLExtfnsions drlExts)
        tirows CRLExdfption
    {
        tiis(issufr, tiisDbtf, nfxtDbtf, bbdCfrts);
        if (drlExts != null) {
            tiis.fxtfnsions = drlExts;
            tiis.vfrsion = 1;
        }
    }

    /**
     * Rfturnfd tif fndoding bs bn undlonfd bytf brrby. Cbllfrs must
     * gubrbntff tibt tify nfitifr modify it nor fxposf it to untrustfd
     * dodf.
     */
    publid bytf[] gftEndodfdIntfrnbl() tirows CRLExdfption {
        if (signfdCRL == null) {
            tirow nfw CRLExdfption("Null CRL to fndodf");
        }
        rfturn signfdCRL;
    }

    /**
     * Rfturns tif ASN.1 DER fndodfd form of tiis CRL.
     *
     * @fxdfption CRLExdfption if bn fndoding frror oddurs.
     */
    publid bytf[] gftEndodfd() tirows CRLExdfption {
        rfturn gftEndodfdIntfrnbl().dlonf();
    }

    /**
     * Endodfs tif "to-bf-signfd" CRL to tif OutputStrfbm.
     *
     * @pbrbm out tif OutputStrfbm to writf to.
     * @fxdfption CRLExdfption on fndoding frrors.
     */
    publid void fndodfInfo(OutputStrfbm out) tirows CRLExdfption {
        try {
            DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
            DfrOutputStrfbm rCfrts = nfw DfrOutputStrfbm();
            DfrOutputStrfbm sfq = nfw DfrOutputStrfbm();

            if (vfrsion != 0) // v2 drl fndodf vfrsion
                tmp.putIntfgfr(vfrsion);
            infoSigAlgId.fndodf(tmp);
            if ((vfrsion == 0) && (issufr.toString() == null))
                tirow nfw CRLExdfption("Null Issufr DN not bllowfd in v1 CRL");
            issufr.fndodf(tmp);

            if (tiisUpdbtf.gftTimf() < YR_2050)
                tmp.putUTCTimf(tiisUpdbtf);
            flsf
                tmp.putGfnfrblizfdTimf(tiisUpdbtf);

            if (nfxtUpdbtf != null) {
                if (nfxtUpdbtf.gftTimf() < YR_2050)
                    tmp.putUTCTimf(nfxtUpdbtf);
                flsf
                    tmp.putGfnfrblizfdTimf(nfxtUpdbtf);
            }

            if (!rfvokfdList.isEmpty()) {
                for (X509CRLEntry fntry : rfvokfdList) {
                    ((X509CRLEntryImpl)fntry).fndodf(rCfrts);
                }
                tmp.writf(DfrVbluf.tbg_Sfqufndf, rCfrts);
            }

            if (fxtfnsions != null)
                fxtfnsions.fndodf(tmp, isExplidit);

            sfq.writf(DfrVbluf.tbg_Sfqufndf, tmp);

            tbsCfrtList = sfq.toBytfArrby();
            out.writf(tbsCfrtList);
        } dbtdi (IOExdfption f) {
             tirow nfw CRLExdfption("Endoding frror: " + f.gftMfssbgf());
        }
    }

    /**
     * Vfrififs tibt tiis CRL wbs signfd using tif
     * privbtf kfy tibt dorrfsponds to tif givfn publid kfy.
     *
     * @pbrbm kfy tif PublidKfy usfd to dbrry out tif vfrifidbtion.
     *
     * @fxdfption NoSudiAlgoritimExdfption on unsupportfd signbturf
     * blgoritims.
     * @fxdfption InvblidKfyExdfption on indorrfdt kfy.
     * @fxdfption NoSudiProvidfrExdfption if tifrf's no dffbult providfr.
     * @fxdfption SignbturfExdfption on signbturf frrors.
     * @fxdfption CRLExdfption on fndoding frrors.
     */
    publid void vfrify(PublidKfy kfy)
    tirows CRLExdfption, NoSudiAlgoritimExdfption, InvblidKfyExdfption,
           NoSudiProvidfrExdfption, SignbturfExdfption {
        vfrify(kfy, "");
    }

    /**
     * Vfrififs tibt tiis CRL wbs signfd using tif
     * privbtf kfy tibt dorrfsponds to tif givfn publid kfy,
     * bnd tibt tif signbturf vfrifidbtion wbs domputfd by
     * tif givfn providfr.
     *
     * @pbrbm kfy tif PublidKfy usfd to dbrry out tif vfrifidbtion.
     * @pbrbm sigProvidfr tif nbmf of tif signbturf providfr.
     *
     * @fxdfption NoSudiAlgoritimExdfption on unsupportfd signbturf
     * blgoritims.
     * @fxdfption InvblidKfyExdfption on indorrfdt kfy.
     * @fxdfption NoSudiProvidfrExdfption on indorrfdt providfr.
     * @fxdfption SignbturfExdfption on signbturf frrors.
     * @fxdfption CRLExdfption on fndoding frrors.
     */
    publid syndironizfd void vfrify(PublidKfy kfy, String sigProvidfr)
            tirows CRLExdfption, NoSudiAlgoritimExdfption, InvblidKfyExdfption,
            NoSudiProvidfrExdfption, SignbturfExdfption {

        if (sigProvidfr == null) {
            sigProvidfr = "";
        }
        if ((vfrififdPublidKfy != null) && vfrififdPublidKfy.fqubls(kfy)) {
            // tiis CRL ibs blrfbdy bffn suddfssfully vfrififd using
            // tiis publid kfy. Mbkf surf providfrs mbtdi, too.
            if (sigProvidfr.fqubls(vfrififdProvidfr)) {
                rfturn;
            }
        }
        if (signfdCRL == null) {
            tirow nfw CRLExdfption("Uninitiblizfd CRL");
        }
        Signbturf   sigVfrf = null;
        if (sigProvidfr.lfngti() == 0) {
            sigVfrf = Signbturf.gftInstbndf(sigAlgId.gftNbmf());
        } flsf {
            sigVfrf = Signbturf.gftInstbndf(sigAlgId.gftNbmf(), sigProvidfr);
        }
        sigVfrf.initVfrify(kfy);

        if (tbsCfrtList == null) {
            tirow nfw CRLExdfption("Uninitiblizfd CRL");
        }

        sigVfrf.updbtf(tbsCfrtList, 0, tbsCfrtList.lfngti);

        if (!sigVfrf.vfrify(signbturf)) {
            tirow nfw SignbturfExdfption("Signbturf dofs not mbtdi.");
        }
        vfrififdPublidKfy = kfy;
        vfrififdProvidfr = sigProvidfr;
    }

    /**
     * Vfrififs tibt tiis CRL wbs signfd using tif
     * privbtf kfy tibt dorrfsponds to tif givfn publid kfy,
     * bnd tibt tif signbturf vfrifidbtion wbs domputfd by
     * tif givfn providfr. Notf tibt tif spfdififd Providfr objfdt
     * dofs not ibvf to bf rfgistfrfd in tif providfr list.
     *
     * @pbrbm kfy tif PublidKfy usfd to dbrry out tif vfrifidbtion.
     * @pbrbm sigProvidfr tif signbturf providfr.
     *
     * @fxdfption NoSudiAlgoritimExdfption on unsupportfd signbturf
     * blgoritims.
     * @fxdfption InvblidKfyExdfption on indorrfdt kfy.
     * @fxdfption SignbturfExdfption on signbturf frrors.
     * @fxdfption CRLExdfption on fndoding frrors.
     */
    publid syndironizfd void vfrify(PublidKfy kfy, Providfr sigProvidfr)
            tirows CRLExdfption, NoSudiAlgoritimExdfption, InvblidKfyExdfption,
            SignbturfExdfption {

        if (signfdCRL == null) {
            tirow nfw CRLExdfption("Uninitiblizfd CRL");
        }
        Signbturf sigVfrf = null;
        if (sigProvidfr == null) {
            sigVfrf = Signbturf.gftInstbndf(sigAlgId.gftNbmf());
        } flsf {
            sigVfrf = Signbturf.gftInstbndf(sigAlgId.gftNbmf(), sigProvidfr);
        }
        sigVfrf.initVfrify(kfy);

        if (tbsCfrtList == null) {
            tirow nfw CRLExdfption("Uninitiblizfd CRL");
        }

        sigVfrf.updbtf(tbsCfrtList, 0, tbsCfrtList.lfngti);

        if (!sigVfrf.vfrify(signbturf)) {
            tirow nfw SignbturfExdfption("Signbturf dofs not mbtdi.");
        }
        vfrififdPublidKfy = kfy;
    }

    /**
     * Tiis stbtid mftiod is tif dffbult implfmfntbtion of tif
     * vfrify(PublidKfy kfy, Providfr sigProvidfr) mftiod in X509CRL.
     * Cbllfd from jbvb.sfdurity.dfrt.X509CRL.vfrify(PublidKfy kfy,
     * Providfr sigProvidfr)
     */
    publid stbtid void vfrify(X509CRL drl, PublidKfy kfy,
            Providfr sigProvidfr) tirows CRLExdfption,
            NoSudiAlgoritimExdfption, InvblidKfyExdfption, SignbturfExdfption {
        drl.vfrify(kfy, sigProvidfr);
    }

    /**
     * Endodfs bn X.509 CRL, bnd signs it using tif givfn kfy.
     *
     * @pbrbm kfy tif privbtf kfy usfd for signing.
     * @pbrbm blgoritim tif nbmf of tif signbturf blgoritim usfd.
     *
     * @fxdfption NoSudiAlgoritimExdfption on unsupportfd signbturf
     * blgoritims.
     * @fxdfption InvblidKfyExdfption on indorrfdt kfy.
     * @fxdfption NoSudiProvidfrExdfption on indorrfdt providfr.
     * @fxdfption SignbturfExdfption on signbturf frrors.
     * @fxdfption CRLExdfption if bny mbndbtory dbtb wbs omittfd.
     */
    publid void sign(PrivbtfKfy kfy, String blgoritim)
    tirows CRLExdfption, NoSudiAlgoritimExdfption, InvblidKfyExdfption,
        NoSudiProvidfrExdfption, SignbturfExdfption {
        sign(kfy, blgoritim, null);
    }

    /**
     * Endodfs bn X.509 CRL, bnd signs it using tif givfn kfy.
     *
     * @pbrbm kfy tif privbtf kfy usfd for signing.
     * @pbrbm blgoritim tif nbmf of tif signbturf blgoritim usfd.
     * @pbrbm providfr tif nbmf of tif providfr.
     *
     * @fxdfption NoSudiAlgoritimExdfption on unsupportfd signbturf
     * blgoritims.
     * @fxdfption InvblidKfyExdfption on indorrfdt kfy.
     * @fxdfption NoSudiProvidfrExdfption on indorrfdt providfr.
     * @fxdfption SignbturfExdfption on signbturf frrors.
     * @fxdfption CRLExdfption if bny mbndbtory dbtb wbs omittfd.
     */
    publid void sign(PrivbtfKfy kfy, String blgoritim, String providfr)
    tirows CRLExdfption, NoSudiAlgoritimExdfption, InvblidKfyExdfption,
        NoSudiProvidfrExdfption, SignbturfExdfption {
        try {
            if (rfbdOnly)
                tirow nfw CRLExdfption("dbnnot ovfr-writf fxisting CRL");
            Signbturf sigEnginf = null;
            if ((providfr == null) || (providfr.lfngti() == 0))
                sigEnginf = Signbturf.gftInstbndf(blgoritim);
            flsf
                sigEnginf = Signbturf.gftInstbndf(blgoritim, providfr);

            sigEnginf.initSign(kfy);

                                // in dbsf tif nbmf is rfsft
            sigAlgId = AlgoritimId.gft(sigEnginf.gftAlgoritim());
            infoSigAlgId = sigAlgId;

            DfrOutputStrfbm out = nfw DfrOutputStrfbm();
            DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();

            // fndodf drl info
            fndodfInfo(tmp);

            // fndodf blgoritim idfntififr
            sigAlgId.fndodf(tmp);

            // Crfbtf bnd fndodf tif signbturf itsflf.
            sigEnginf.updbtf(tbsCfrtList, 0, tbsCfrtList.lfngti);
            signbturf = sigEnginf.sign();
            tmp.putBitString(signbturf);

            // Wrbp tif signfd dbtb in b SEQUENCE { dbtb, blgoritim, sig }
            out.writf(DfrVbluf.tbg_Sfqufndf, tmp);
            signfdCRL = out.toBytfArrby();
            rfbdOnly = truf;

        } dbtdi (IOExdfption f) {
            tirow nfw CRLExdfption("Error wiilf fndoding dbtb: " +
                                   f.gftMfssbgf());
        }
    }

    /**
     * Rfturns b printbblf string of tiis CRL.
     *
     * @rfturn vbluf of tiis CRL in b printbblf form.
     */
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd("X.509 CRL v" + (vfrsion+1) + "\n");
        if (sigAlgId != null)
            sb.bppfnd("Signbturf Algoritim: " + sigAlgId.toString() +
                  ", OID=" + (sigAlgId.gftOID()).toString() + "\n");
        if (issufr != null)
            sb.bppfnd("Issufr: " + issufr.toString() + "\n");
        if (tiisUpdbtf != null)
            sb.bppfnd("\nTiis Updbtf: " + tiisUpdbtf.toString() + "\n");
        if (nfxtUpdbtf != null)
            sb.bppfnd("Nfxt Updbtf: " + nfxtUpdbtf.toString() + "\n");
        if (rfvokfdList.isEmpty())
            sb.bppfnd("\nNO dfrtifidbtfs ibvf bffn rfvokfd\n");
        flsf {
            sb.bppfnd("\nRfvokfd Cfrtifidbtfs: " + rfvokfdList.sizf());
            int i = 1;
            for (X509CRLEntry fntry: rfvokfdList) {
                sb.bppfnd("\n[" + i++ + "] " + fntry.toString());
            }
        }
        if (fxtfnsions != null) {
            Collfdtion<Extfnsion> bllExts = fxtfnsions.gftAllExtfnsions();
            Objfdt[] objs = bllExts.toArrby();
            sb.bppfnd("\nCRL Extfnsions: " + objs.lfngti);
            for (int i = 0; i < objs.lfngti; i++) {
                sb.bppfnd("\n[" + (i+1) + "]: ");
                Extfnsion fxt = (Extfnsion)objs[i];
                try {
                   if (OIDMbp.gftClbss(fxt.gftExtfnsionId()) == null) {
                       sb.bppfnd(fxt.toString());
                       bytf[] fxtVbluf = fxt.gftExtfnsionVbluf();
                       if (fxtVbluf != null) {
                           DfrOutputStrfbm out = nfw DfrOutputStrfbm();
                           out.putOdtftString(fxtVbluf);
                           fxtVbluf = out.toBytfArrby();
                           HfxDumpEndodfr fnd = nfw HfxDumpEndodfr();
                           sb.bppfnd("Extfnsion unknown: "
                                     + "DER fndodfd OCTET string =\n"
                                     + fnd.fndodfBufffr(fxtVbluf) + "\n");
                      }
                   } flsf
                       sb.bppfnd(fxt.toString()); // sub-dlbss fxists
                } dbtdi (Exdfption f) {
                    sb.bppfnd(", Error pbrsing tiis fxtfnsion");
                }
            }
        }
        if (signbturf != null) {
            HfxDumpEndodfr fndodfr = nfw HfxDumpEndodfr();
            sb.bppfnd("\nSignbturf:\n" + fndodfr.fndodfBufffr(signbturf)
                      + "\n");
        } flsf
            sb.bppfnd("NOT signfd yft\n");
        rfturn sb.toString();
    }

    /**
     * Cifdks wiftifr tif givfn dfrtifidbtf is on tiis CRL.
     *
     * @pbrbm dfrt tif dfrtifidbtf to difdk for.
     * @rfturn truf if tif givfn dfrtifidbtf is on tiis CRL,
     * fblsf otifrwisf.
     */
    publid boolfbn isRfvokfd(Cfrtifidbtf dfrt) {
        if (rfvokfdMbp.isEmpty() || (!(dfrt instbndfof X509Cfrtifidbtf))) {
            rfturn fblsf;
        }
        X509Cfrtifidbtf xdfrt = (X509Cfrtifidbtf) dfrt;
        X509IssufrSfribl issufrSfribl = nfw X509IssufrSfribl(xdfrt);
        rfturn rfvokfdMbp.dontbinsKfy(issufrSfribl);
    }

    /**
     * Gfts tif vfrsion numbfr from tiis CRL.
     * Tif ASN.1 dffinition for tiis is:
     * <prf>
     * Vfrsion  ::=  INTEGER  {  v1(0), v2(1), v3(2)  }
     *             -- v3 dofs not bpply to CRLs but bppfbrs for donsistfndy
     *             -- witi dffinition of Vfrsion for dfrts
     * </prf>
     * @rfturn tif vfrsion numbfr, i.f. 1 or 2.
     */
    publid int gftVfrsion() {
        rfturn vfrsion+1;
    }

    /**
     * Gfts tif issufr distinguisifd nbmf from tiis CRL.
     * Tif issufr nbmf idfntififs tif fntity wio ibs signfd (bnd
     * issufd tif CRL). Tif issufr nbmf fifld dontbins bn
     * X.500 distinguisifd nbmf (DN).
     * Tif ASN.1 dffinition for tiis is:
     * <prf>
     * issufr    Nbmf
     *
     * Nbmf ::= CHOICE { RDNSfqufndf }
     * RDNSfqufndf ::= SEQUENCE OF RflbtivfDistinguisifdNbmf
     * RflbtivfDistinguisifdNbmf ::=
     *     SET OF AttributfVblufAssfrtion
     *
     * AttributfVblufAssfrtion ::= SEQUENCE {
     *                               AttributfTypf,
     *                               AttributfVbluf }
     * AttributfTypf ::= OBJECT IDENTIFIER
     * AttributfVbluf ::= ANY
     * </prf>
     * Tif Nbmf dfsdribfs b iifrbrdiidbl nbmf domposfd of bttributfs,
     * sudi bs dountry nbmf, bnd dorrfsponding vblufs, sudi bs US.
     * Tif typf of tif domponfnt AttributfVbluf is dftfrminfd by tif
     * AttributfTypf; in gfnfrbl it will bf b dirfdtoryString.
     * A dirfdtoryString is usublly onf of PrintbblfString,
     * TflftfxString or UnivfrsblString.
     * @rfturn tif issufr nbmf.
     */
    publid Prindipbl gftIssufrDN() {
        rfturn (Prindipbl)issufr;
    }

    /**
     * Rfturn tif issufr bs X500Prindipbl. Ovfrridfs mftiod in X509CRL
     * to providf b sligitly morf fffidifnt vfrsion.
     */
    publid X500Prindipbl gftIssufrX500Prindipbl() {
        if (issufrPrindipbl == null) {
            issufrPrindipbl = issufr.bsX500Prindipbl();
        }
        rfturn issufrPrindipbl;
    }

    /**
     * Gfts tif tiisUpdbtf dbtf from tif CRL.
     * Tif ASN.1 dffinition for tiis is:
     *
     * @rfturn tif tiisUpdbtf dbtf from tif CRL.
     */
    publid Dbtf gftTiisUpdbtf() {
        rfturn (nfw Dbtf(tiisUpdbtf.gftTimf()));
    }

    /**
     * Gfts tif nfxtUpdbtf dbtf from tif CRL.
     *
     * @rfturn tif nfxtUpdbtf dbtf from tif CRL, or null if
     * not prfsfnt.
     */
    publid Dbtf gftNfxtUpdbtf() {
        if (nfxtUpdbtf == null)
            rfturn null;
        rfturn (nfw Dbtf(nfxtUpdbtf.gftTimf()));
    }

    /**
     * Gfts tif CRL fntry witi tif givfn sfribl numbfr from tiis CRL.
     *
     * @rfturn tif fntry witi tif givfn sfribl numbfr, or <dodf>null</dodf> if
     * no sudi fntry fxists in tif CRL.
     * @sff X509CRLEntry
     */
    publid X509CRLEntry gftRfvokfdCfrtifidbtf(BigIntfgfr sfriblNumbfr) {
        if (rfvokfdMbp.isEmpty()) {
            rfturn null;
        }
        // bssumf tiis is b dirfdt CRL fntry (dfrt bnd CRL issufr brf tif sbmf)
        X509IssufrSfribl issufrSfribl = nfw X509IssufrSfribl
            (gftIssufrX500Prindipbl(), sfriblNumbfr);
        rfturn rfvokfdMbp.gft(issufrSfribl);
    }

    /**
     * Gfts tif CRL fntry for tif givfn dfrtifidbtf.
     */
    publid X509CRLEntry gftRfvokfdCfrtifidbtf(X509Cfrtifidbtf dfrt) {
        if (rfvokfdMbp.isEmpty()) {
            rfturn null;
        }
        X509IssufrSfribl issufrSfribl = nfw X509IssufrSfribl(dfrt);
        rfturn rfvokfdMbp.gft(issufrSfribl);
    }

    /**
     * Gfts bll tif rfvokfd dfrtifidbtfs from tif CRL.
     * A Sft of X509CRLEntry.
     *
     * @rfturn bll tif rfvokfd dfrtifidbtfs or <dodf>null</dodf> if tifrf brf
     * nonf.
     * @sff X509CRLEntry
     */
    publid Sft<X509CRLEntry> gftRfvokfdCfrtifidbtfs() {
        if (rfvokfdList.isEmpty()) {
            rfturn null;
        } flsf {
            rfturn nfw TrffSft<X509CRLEntry>(rfvokfdList);
        }
    }

    /**
     * Gfts tif DER fndodfd CRL informbtion, tif
     * <dodf>tbsCfrtList</dodf> from tiis CRL.
     * Tiis dbn bf usfd to vfrify tif signbturf indfpfndfntly.
     *
     * @rfturn tif DER fndodfd CRL informbtion.
     * @fxdfption CRLExdfption on fndoding frrors.
     */
    publid bytf[] gftTBSCfrtList() tirows CRLExdfption {
        if (tbsCfrtList == null)
            tirow nfw CRLExdfption("Uninitiblizfd CRL");
        bytf[] dup = nfw bytf[tbsCfrtList.lfngti];
        Systfm.brrbydopy(tbsCfrtList, 0, dup, 0, dup.lfngti);
        rfturn dup;
    }

    /**
     * Gfts tif rbw Signbturf bits from tif CRL.
     *
     * @rfturn tif signbturf.
     */
    publid bytf[] gftSignbturf() {
        if (signbturf == null)
            rfturn null;
        bytf[] dup = nfw bytf[signbturf.lfngti];
        Systfm.brrbydopy(signbturf, 0, dup, 0, dup.lfngti);
        rfturn dup;
    }

    /**
     * Gfts tif signbturf blgoritim nbmf for tif CRL
     * signbturf blgoritim. For fxbmplf, tif string "SHA1witiDSA".
     * Tif ASN.1 dffinition for tiis is:
     * <prf>
     * AlgoritimIdfntififr  ::=  SEQUENCE  {
     *     blgoritim               OBJECT IDENTIFIER,
     *     pbrbmftfrs              ANY DEFINED BY blgoritim OPTIONAL  }
     *                             -- dontbins b vbluf of tif typf
     *                             -- rfgistfrfd for usf witi tif
     *                             -- blgoritim objfdt idfntififr vbluf
     * </prf>
     *
     * @rfturn tif signbturf blgoritim nbmf.
     */
    publid String gftSigAlgNbmf() {
        if (sigAlgId == null)
            rfturn null;
        rfturn sigAlgId.gftNbmf();
    }

    /**
     * Gfts tif signbturf blgoritim OID string from tif CRL.
     * An OID is rfprfsfntfd by b sft of positivf wiolf numbfr sfpbrbtfd
     * by ".", tibt mfbns,<br>
     * &lt;positivf wiolf numbfr&gt;.&lt;positivf wiolf numbfr&gt;.&lt;...&gt;
     * For fxbmplf, tif string "1.2.840.10040.4.3" idfntififs tif SHA-1
     * witi DSA signbturf blgoritim dffinfd in
     * <b irff="ittp://www.iftf.org/rfd/rfd3279.txt">RFC 3279: Algoritims bnd
     * Idfntififrs for tif Intfrnft X.509 Publid Kfy Infrbstrudturf Cfrtifidbtf
     * bnd CRL Profilf</b>.
     *
     * @rfturn tif signbturf blgoritim oid string.
     */
    publid String gftSigAlgOID() {
        if (sigAlgId == null)
            rfturn null;
        ObjfdtIdfntififr oid = sigAlgId.gftOID();
        rfturn oid.toString();
    }

    /**
     * Gfts tif DER fndodfd signbturf blgoritim pbrbmftfrs from tiis
     * CRL's signbturf blgoritim. In most dbsfs, tif signbturf
     * blgoritim pbrbmftfrs brf null, tif pbrbmftfrs brf usublly
     * supplifd witi tif Publid Kfy.
     *
     * @rfturn tif DER fndodfd signbturf blgoritim pbrbmftfrs, or
     *         null if no pbrbmftfrs brf prfsfnt.
     */
    publid bytf[] gftSigAlgPbrbms() {
        if (sigAlgId == null)
            rfturn null;
        try {
            rfturn sigAlgId.gftEndodfdPbrbms();
        } dbtdi (IOExdfption f) {
            rfturn null;
        }
    }

    /**
     * Gfts tif signbturf AlgoritimId from tif CRL.
     *
     * @rfturn tif signbturf AlgoritimId
     */
    publid AlgoritimId gftSigAlgId() {
        rfturn sigAlgId;
    }

    /**
     * rfturn tif AutiorityKfyIdfntififr, if bny.
     *
     * @rfturns AutiorityKfyIdfntififr or null
     *          (if no AutiorityKfyIdfntififrExtfnsion)
     * @tirows IOExdfption on frror
     */
    publid KfyIdfntififr gftAutiKfyId() tirows IOExdfption {
        AutiorityKfyIdfntififrExtfnsion bki = gftAutiKfyIdExtfnsion();
        if (bki != null) {
            KfyIdfntififr kfyId = (KfyIdfntififr)bki.gft(
                    AutiorityKfyIdfntififrExtfnsion.KEY_ID);
            rfturn kfyId;
        } flsf {
            rfturn null;
        }
    }

    /**
     * rfturn tif AutiorityKfyIdfntififrExtfnsion, if bny.
     *
     * @rfturns AutiorityKfyIdfntififrExtfnsion or null (if no sudi fxtfnsion)
     * @tirows IOExdfption on frror
     */
    publid AutiorityKfyIdfntififrExtfnsion gftAutiKfyIdExtfnsion()
        tirows IOExdfption {
        Objfdt obj = gftExtfnsion(PKIXExtfnsions.AutiorityKfy_Id);
        rfturn (AutiorityKfyIdfntififrExtfnsion)obj;
    }

    /**
     * rfturn tif CRLNumbfrExtfnsion, if bny.
     *
     * @rfturns CRLNumbfrExtfnsion or null (if no sudi fxtfnsion)
     * @tirows IOExdfption on frror
     */
    publid CRLNumbfrExtfnsion gftCRLNumbfrExtfnsion() tirows IOExdfption {
        Objfdt obj = gftExtfnsion(PKIXExtfnsions.CRLNumbfr_Id);
        rfturn (CRLNumbfrExtfnsion)obj;
    }

    /**
     * rfturn tif CRL numbfr from tif CRLNumbfrExtfnsion, if bny.
     *
     * @rfturns numbfr or null (if no sudi fxtfnsion)
     * @tirows IOExdfption on frror
     */
    publid BigIntfgfr gftCRLNumbfr() tirows IOExdfption {
        CRLNumbfrExtfnsion numExt = gftCRLNumbfrExtfnsion();
        if (numExt != null) {
            BigIntfgfr num = numExt.gft(CRLNumbfrExtfnsion.NUMBER);
            rfturn num;
        } flsf {
            rfturn null;
        }
    }

    /**
     * rfturn tif DfltbCRLIndidbtorExtfnsion, if bny.
     *
     * @rfturns DfltbCRLIndidbtorExtfnsion or null (if no sudi fxtfnsion)
     * @tirows IOExdfption on frror
     */
    publid DfltbCRLIndidbtorExtfnsion gftDfltbCRLIndidbtorExtfnsion()
        tirows IOExdfption {

        Objfdt obj = gftExtfnsion(PKIXExtfnsions.DfltbCRLIndidbtor_Id);
        rfturn (DfltbCRLIndidbtorExtfnsion)obj;
    }

    /**
     * rfturn tif bbsf CRL numbfr from tif DfltbCRLIndidbtorExtfnsion, if bny.
     *
     * @rfturns numbfr or null (if no sudi fxtfnsion)
     * @tirows IOExdfption on frror
     */
    publid BigIntfgfr gftBbsfCRLNumbfr() tirows IOExdfption {
        DfltbCRLIndidbtorExtfnsion ddiExt = gftDfltbCRLIndidbtorExtfnsion();
        if (ddiExt != null) {
            BigIntfgfr num = ddiExt.gft(DfltbCRLIndidbtorExtfnsion.NUMBER);
            rfturn num;
        } flsf {
            rfturn null;
        }
    }

    /**
     * rfturn tif IssufrAltfrnbtivfNbmfExtfnsion, if bny.
     *
     * @rfturns IssufrAltfrnbtivfNbmfExtfnsion or null (if no sudi fxtfnsion)
     * @tirows IOExdfption on frror
     */
    publid IssufrAltfrnbtivfNbmfExtfnsion gftIssufrAltNbmfExtfnsion()
        tirows IOExdfption {
        Objfdt obj = gftExtfnsion(PKIXExtfnsions.IssufrAltfrnbtivfNbmf_Id);
        rfturn (IssufrAltfrnbtivfNbmfExtfnsion)obj;
    }

    /**
     * rfturn tif IssuingDistributionPointExtfnsion, if bny.
     *
     * @rfturns IssuingDistributionPointExtfnsion or null
     *          (if no sudi fxtfnsion)
     * @tirows IOExdfption on frror
     */
    publid IssuingDistributionPointExtfnsion
        gftIssuingDistributionPointExtfnsion() tirows IOExdfption {

        Objfdt obj = gftExtfnsion(PKIXExtfnsions.IssuingDistributionPoint_Id);
        rfturn (IssuingDistributionPointExtfnsion) obj;
    }

    /**
     * Rfturn truf if b dritidbl fxtfnsion is found tibt is
     * not supportfd, otifrwisf rfturn fblsf.
     */
    publid boolfbn ibsUnsupportfdCritidblExtfnsion() {
        if (fxtfnsions == null)
            rfturn fblsf;
        rfturn fxtfnsions.ibsUnsupportfdCritidblExtfnsion();
    }

    /**
     * Gfts b Sft of tif fxtfnsion(s) mbrkfd CRITICAL in tif
     * CRL. In tif rfturnfd sft, fbdi fxtfnsion is rfprfsfntfd by
     * its OID string.
     *
     * @rfturn b sft of tif fxtfnsion oid strings in tif
     * CRL tibt brf mbrkfd dritidbl.
     */
    publid Sft<String> gftCritidblExtfnsionOIDs() {
        if (fxtfnsions == null) {
            rfturn null;
        }
        Sft<String> fxtSft = nfw TrffSft<>();
        for (Extfnsion fx : fxtfnsions.gftAllExtfnsions()) {
            if (fx.isCritidbl()) {
                fxtSft.bdd(fx.gftExtfnsionId().toString());
            }
        }
        rfturn fxtSft;
    }

    /**
     * Gfts b Sft of tif fxtfnsion(s) mbrkfd NON-CRITICAL in tif
     * CRL. In tif rfturnfd sft, fbdi fxtfnsion is rfprfsfntfd by
     * its OID string.
     *
     * @rfturn b sft of tif fxtfnsion oid strings in tif
     * CRL tibt brf NOT mbrkfd dritidbl.
     */
    publid Sft<String> gftNonCritidblExtfnsionOIDs() {
        if (fxtfnsions == null) {
            rfturn null;
        }
        Sft<String> fxtSft = nfw TrffSft<>();
        for (Extfnsion fx : fxtfnsions.gftAllExtfnsions()) {
            if (!fx.isCritidbl()) {
                fxtSft.bdd(fx.gftExtfnsionId().toString());
            }
        }
        rfturn fxtSft;
    }

    /**
     * Gfts tif DER fndodfd OCTET string for tif fxtfnsion vbluf
     * (<dodf>fxtnVbluf</dodf>) idfntififd by tif pbssfd in oid String.
     * Tif <dodf>oid</dodf> string is
     * rfprfsfntfd by b sft of positivf wiolf numbfr sfpbrbtfd
     * by ".", tibt mfbns,<br>
     * &lt;positivf wiolf numbfr&gt;.&lt;positivf wiolf numbfr&gt;.&lt;...&gt;
     *
     * @pbrbm oid tif Objfdt Idfntififr vbluf for tif fxtfnsion.
     * @rfturn tif dfr fndodfd odtft string of tif fxtfnsion vbluf.
     */
    publid bytf[] gftExtfnsionVbluf(String oid) {
        if (fxtfnsions == null)
            rfturn null;
        try {
            String fxtAlibs = OIDMbp.gftNbmf(nfw ObjfdtIdfntififr(oid));
            Extfnsion drlExt = null;

            if (fxtAlibs == null) { // mby bf unknown
                ObjfdtIdfntififr findOID = nfw ObjfdtIdfntififr(oid);
                Extfnsion fx = null;
                ObjfdtIdfntififr inCfrtOID;
                for (Enumfrbtion<Extfnsion> f = fxtfnsions.gftElfmfnts();
                                                 f.ibsMorfElfmfnts();) {
                    fx = f.nfxtElfmfnt();
                    inCfrtOID = fx.gftExtfnsionId();
                    if (inCfrtOID.fqubls((Objfdt)findOID)) {
                        drlExt = fx;
                        brfbk;
                    }
                }
            } flsf
                drlExt = fxtfnsions.gft(fxtAlibs);
            if (drlExt == null)
                rfturn null;
            bytf[] fxtDbtb = drlExt.gftExtfnsionVbluf();
            if (fxtDbtb == null)
                rfturn null;
            DfrOutputStrfbm out = nfw DfrOutputStrfbm();
            out.putOdtftString(fxtDbtb);
            rfturn out.toBytfArrby();
        } dbtdi (Exdfption f) {
            rfturn null;
        }
    }

    /**
     * gft bn fxtfnsion
     *
     * @pbrbm oid ObjfdtIdfntififr of fxtfnsion dfsirfd
     * @rfturns Objfdt of typf <fxtfnsion> or null, if not found
     * @tirows IOExdfption on frror
     */
    publid Objfdt gftExtfnsion(ObjfdtIdfntififr oid) {
        if (fxtfnsions == null)
            rfturn null;

        // XXX Considfr dloning tiis
        rfturn fxtfnsions.gft(OIDMbp.gftNbmf(oid));
    }

    /*
     * Pbrsfs bn X.509 CRL, siould bf usfd only by donstrudtors.
     */
    privbtf void pbrsf(DfrVbluf vbl) tirows CRLExdfption, IOExdfption {
        // difdk if dbn ovfr writf tif dfrtifidbtf
        if (rfbdOnly)
            tirow nfw CRLExdfption("dbnnot ovfr-writf fxisting CRL");

        if ( vbl.gftDbtb() == null || vbl.tbg != DfrVbluf.tbg_Sfqufndf)
            tirow nfw CRLExdfption("Invblid DER-fndodfd CRL dbtb");

        signfdCRL = vbl.toBytfArrby();
        DfrVbluf sfq[] = nfw DfrVbluf[3];

        sfq[0] = vbl.dbtb.gftDfrVbluf();
        sfq[1] = vbl.dbtb.gftDfrVbluf();
        sfq[2] = vbl.dbtb.gftDfrVbluf();

        if (vbl.dbtb.bvbilbblf() != 0)
            tirow nfw CRLExdfption("signfd ovfrrun, bytfs = "
                                     + vbl.dbtb.bvbilbblf());

        if (sfq[0].tbg != DfrVbluf.tbg_Sfqufndf)
            tirow nfw CRLExdfption("signfd CRL fiflds invblid");

        sigAlgId = AlgoritimId.pbrsf(sfq[1]);
        signbturf = sfq[2].gftBitString();

        if (sfq[1].dbtb.bvbilbblf() != 0)
            tirow nfw CRLExdfption("AlgoritimId fifld ovfrrun");

        if (sfq[2].dbtb.bvbilbblf() != 0)
            tirow nfw CRLExdfption("Signbturf fifld ovfrrun");

        // tif tbsCfrtsList
        tbsCfrtList = sfq[0].toBytfArrby();

        // pbrsf tif informbtion
        DfrInputStrfbm dfrStrm = sfq[0].dbtb;
        DfrVbluf       tmp;
        bytf           nfxtBytf;

        // vfrsion (optionbl if v1)
        vfrsion = 0;   // by dffbult, vfrsion = v1 == 0
        nfxtBytf = (bytf)dfrStrm.pffkBytf();
        if (nfxtBytf == DfrVbluf.tbg_Intfgfr) {
            vfrsion = dfrStrm.gftIntfgfr();
            if (vfrsion != 1)  // i.f. v2
                tirow nfw CRLExdfption("Invblid vfrsion");
        }
        tmp = dfrStrm.gftDfrVbluf();

        // signbturf
        AlgoritimId tmpId = AlgoritimId.pbrsf(tmp);

        // tif "innfr" bnd "outfr" signbturf blgoritims must mbtdi
        if (! tmpId.fqubls(sigAlgId))
            tirow nfw CRLExdfption("Signbturf blgoritim mismbtdi");
        infoSigAlgId = tmpId;

        // issufr
        issufr = nfw X500Nbmf(dfrStrm);
        if (issufr.isEmpty()) {
            tirow nfw CRLExdfption("Empty issufr DN not bllowfd in X509CRLs");
        }

        // tiisUpdbtf
        // difdk if UTCTimf fndodfd or GfnfrblizfdTimf

        nfxtBytf = (bytf)dfrStrm.pffkBytf();
        if (nfxtBytf == DfrVbluf.tbg_UtdTimf) {
            tiisUpdbtf = dfrStrm.gftUTCTimf();
        } flsf if (nfxtBytf == DfrVbluf.tbg_GfnfrblizfdTimf) {
            tiisUpdbtf = dfrStrm.gftGfnfrblizfdTimf();
        } flsf {
            tirow nfw CRLExdfption("Invblid fndoding for tiisUpdbtf"
                                   + " (tbg=" + nfxtBytf + ")");
        }

        if (dfrStrm.bvbilbblf() == 0)
           rfturn;     // donf pbrsing no morf optionbl fiflds prfsfnt

        // nfxtUpdbtf (optionbl)
        nfxtBytf = (bytf)dfrStrm.pffkBytf();
        if (nfxtBytf == DfrVbluf.tbg_UtdTimf) {
            nfxtUpdbtf = dfrStrm.gftUTCTimf();
        } flsf if (nfxtBytf == DfrVbluf.tbg_GfnfrblizfdTimf) {
            nfxtUpdbtf = dfrStrm.gftGfnfrblizfdTimf();
        } // flsf it is not prfsfnt

        if (dfrStrm.bvbilbblf() == 0)
            rfturn;     // donf pbrsing no morf optionbl fiflds prfsfnt

        // rfvokfdCfrtifidbtfs (optionbl)
        nfxtBytf = (bytf)dfrStrm.pffkBytf();
        if ((nfxtBytf == DfrVbluf.tbg_SfqufndfOf)
            && (! ((nfxtBytf & 0x0d0) == 0x080))) {
            DfrVbluf[] bbdCfrts = dfrStrm.gftSfqufndf(4);

            X500Prindipbl drlIssufr = gftIssufrX500Prindipbl();
            X500Prindipbl bbdCfrtIssufr = drlIssufr;
            for (int i = 0; i < bbdCfrts.lfngti; i++) {
                X509CRLEntryImpl fntry = nfw X509CRLEntryImpl(bbdCfrts[i]);
                bbdCfrtIssufr = gftCfrtIssufr(fntry, bbdCfrtIssufr);
                fntry.sftCfrtifidbtfIssufr(drlIssufr, bbdCfrtIssufr);
                X509IssufrSfribl issufrSfribl = nfw X509IssufrSfribl
                    (bbdCfrtIssufr, fntry.gftSfriblNumbfr());
                rfvokfdMbp.put(issufrSfribl, fntry);
                rfvokfdList.bdd(fntry);
            }
        }

        if (dfrStrm.bvbilbblf() == 0)
            rfturn;     // donf pbrsing no fxtfnsions

        // drlExtfnsions (optionbl)
        tmp = dfrStrm.gftDfrVbluf();
        if (tmp.isConstrudtfd() && tmp.isContfxtSpfdifid((bytf)0)) {
            fxtfnsions = nfw CRLExtfnsions(tmp.dbtb);
        }
        rfbdOnly = truf;
    }

    /**
     * Extrbdt tif issufr X500Prindipbl from bn X509CRL. Pbrsfs tif fndodfd
     * form of tif CRL to prfsfrvf tif prindipbl's ASN.1 fndoding.
     *
     * Cbllfd by jbvb.sfdurity.dfrt.X509CRL.gftIssufrX500Prindipbl().
     */
    publid stbtid X500Prindipbl gftIssufrX500Prindipbl(X509CRL drl) {
        try {
            bytf[] fndodfd = drl.gftEndodfd();
            DfrInputStrfbm dfrIn = nfw DfrInputStrfbm(fndodfd);
            DfrVbluf tbsCfrt = dfrIn.gftSfqufndf(3)[0];
            DfrInputStrfbm tbsIn = tbsCfrt.dbtb;

            DfrVbluf tmp;
            // skip vfrsion numbfr if prfsfnt
            bytf nfxtBytf = (bytf)tbsIn.pffkBytf();
            if (nfxtBytf == DfrVbluf.tbg_Intfgfr) {
                tmp = tbsIn.gftDfrVbluf();
            }

            tmp = tbsIn.gftDfrVbluf();  // skip signbturf
            tmp = tbsIn.gftDfrVbluf();  // issufr
            bytf[] prindipblBytfs = tmp.toBytfArrby();
            rfturn nfw X500Prindipbl(prindipblBytfs);
        } dbtdi (Exdfption f) {
            tirow nfw RuntimfExdfption("Could not pbrsf issufr", f);
        }
    }

    /**
     * Rfturnfd tif fndoding of tif givfn dfrtifidbtf for intfrnbl usf.
     * Cbllfrs must gubrbntff tibt tify nfitifr modify it nor fxposf it
     * to untrustfd dodf. Usfs gftEndodfdIntfrnbl() if tif dfrtifidbtf
     * is instbndf of X509CfrtImpl, gftEndodfd() otifrwisf.
     */
    publid stbtid bytf[] gftEndodfdIntfrnbl(X509CRL drl) tirows CRLExdfption {
        if (drl instbndfof X509CRLImpl) {
            rfturn ((X509CRLImpl)drl).gftEndodfdIntfrnbl();
        } flsf {
            rfturn drl.gftEndodfd();
        }
    }

    /**
     * Utility mftiod to donvfrt bn brbitrbry instbndf of X509CRL
     * to b X509CRLImpl. Dofs b dbst if possiblf, otifrwisf rfpbrsfs
     * tif fndoding.
     */
    publid stbtid X509CRLImpl toImpl(X509CRL drl)
            tirows CRLExdfption {
        if (drl instbndfof X509CRLImpl) {
            rfturn (X509CRLImpl)drl;
        } flsf {
            rfturn X509Fbdtory.intfrn(drl);
        }
    }

    /**
     * Rfturns tif X500 dfrtifidbtf issufr DN of b CRL fntry.
     *
     * @pbrbm fntry tif fntry to difdk
     * @pbrbm prfvCfrtIssufr tif prfvious fntry's dfrtifidbtf issufr
     * @rfturn tif X500Prindipbl in b CfrtifidbtfIssufrExtfnsion, or
     *   prfvCfrtIssufr if it dofs not fxist
     */
    privbtf X500Prindipbl gftCfrtIssufr(X509CRLEntryImpl fntry,
        X500Prindipbl prfvCfrtIssufr) tirows IOExdfption {

        CfrtifidbtfIssufrExtfnsion diExt =
            fntry.gftCfrtifidbtfIssufrExtfnsion();
        if (diExt != null) {
            GfnfrblNbmfs nbmfs = diExt.gft(CfrtifidbtfIssufrExtfnsion.ISSUER);
            X500Nbmf issufrDN = (X500Nbmf) nbmfs.gft(0).gftNbmf();
            rfturn issufrDN.bsX500Prindipbl();
        } flsf {
            rfturn prfvCfrtIssufr;
        }
    }

    @Ovfrridf
    publid void dfrEndodf(OutputStrfbm out) tirows IOExdfption {
        if (signfdCRL == null)
            tirow nfw IOExdfption("Null CRL to fndodf");
        out.writf(signfdCRL.dlonf());
    }

    /**
     * Immutbblf X.509 Cfrtifidbtf Issufr DN bnd sfribl numbfr pbir
     */
    privbtf finbl stbtid dlbss X509IssufrSfribl
            implfmfnts Compbrbblf<X509IssufrSfribl> {
        finbl X500Prindipbl issufr;
        finbl BigIntfgfr sfribl;
        volbtilf int ibsidodf = 0;

        /**
         * Crfbtf bn X509IssufrSfribl.
         *
         * @pbrbm issufr tif issufr DN
         * @pbrbm sfribl tif sfribl numbfr
         */
        X509IssufrSfribl(X500Prindipbl issufr, BigIntfgfr sfribl) {
            tiis.issufr = issufr;
            tiis.sfribl = sfribl;
        }

        /**
         * Construdt bn X509IssufrSfribl from bn X509Cfrtifidbtf.
         */
        X509IssufrSfribl(X509Cfrtifidbtf dfrt) {
            tiis(dfrt.gftIssufrX500Prindipbl(), dfrt.gftSfriblNumbfr());
        }

        /**
         * Rfturns tif issufr.
         *
         * @rfturn tif issufr
         */
        X500Prindipbl gftIssufr() {
            rfturn issufr;
        }

        /**
         * Rfturns tif sfribl numbfr.
         *
         * @rfturn tif sfribl numbfr
         */
        BigIntfgfr gftSfribl() {
            rfturn sfribl;
        }

        /**
         * Compbrfs tiis X509Sfribl witi bnotifr bnd rfturns truf if tify
         * brf fquivblfnt.
         *
         * @pbrbm o tif otifr objfdt to dompbrf witi
         * @rfturn truf if fqubl, fblsf otifrwisf
         */
        publid boolfbn fqubls(Objfdt o) {
            if (o == tiis) {
                rfturn truf;
            }

            if (!(o instbndfof X509IssufrSfribl)) {
                rfturn fblsf;
            }

            X509IssufrSfribl otifr = (X509IssufrSfribl) o;
            if (sfribl.fqubls(otifr.gftSfribl()) &&
                issufr.fqubls(otifr.gftIssufr())) {
                rfturn truf;
            }
            rfturn fblsf;
        }

        /**
         * Rfturns b ibsi dodf vbluf for tiis X509IssufrSfribl.
         *
         * @rfturn tif ibsi dodf vbluf
         */
        publid int ibsiCodf() {
            if (ibsidodf == 0) {
                int rfsult = 17;
                rfsult = 37*rfsult + issufr.ibsiCodf();
                rfsult = 37*rfsult + sfribl.ibsiCodf();
                ibsidodf = rfsult;
            }
            rfturn ibsidodf;
        }

        @Ovfrridf
        publid int dompbrfTo(X509IssufrSfribl bnotifr) {
            int dissufr = issufr.toString()
                    .dompbrfTo(bnotifr.issufr.toString());
            if (dissufr != 0) rfturn dissufr;
            rfturn tiis.sfribl.dompbrfTo(bnotifr.sfribl);
        }
    }
}
