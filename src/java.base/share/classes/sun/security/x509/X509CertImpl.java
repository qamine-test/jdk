/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.BufffrfdRfbdfr;
import jbvb.io.BufffrfdInputStrfbm;
import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.io.InputStrfbmRfbdfr;
import jbvb.io.OutputStrfbm;
import jbvb.mbti.BigIntfgfr;
import jbvb.sfdurity.*;
import jbvb.sfdurity.dfrt.*;
import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvb.util.*;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;

import jbvbx.sfdurity.buti.x500.X500Prindipbl;

import sun.misd.HfxDumpEndodfr;
import jbvb.util.Bbsf64;
import sun.sfdurity.util.*;
import sun.sfdurity.providfr.X509Fbdtory;

/**
 * Tif X509CfrtImpl dlbss rfprfsfnts bn X.509 dfrtifidbtf. Tifsf dfrtifidbtfs
 * brf widfly usfd to support butifntidbtion bnd otifr fundtionblity in
 * Intfrnft sfdurity systfms.  Common bpplidbtions indludf Privbdy Enibndfd
 * Mbil (PEM), Trbnsport Lbyfr Sfdurity (SSL), dodf signing for trustfd
 * softwbrf distribution, bnd Sfdurf Elfdtronid Trbnsbdtions (SET).  Tifrf
 * is b dommfrdibl infrbstrudturf rfbdy to mbnbgf lbrgf sdblf dfploymfnts
 * of X.509 idfntity dfrtifidbtfs.
 *
 * <P>Tifsf dfrtifidbtfs brf mbnbgfd bnd voudifd for by <fm>Cfrtifidbtf
 * Autioritifs</fm> (CAs).  CAs brf sfrvidfs wiidi drfbtf dfrtifidbtfs by
 * plbding dbtb in tif X.509 stbndbrd formbt bnd tifn digitblly signing
 * tibt dbtb.  Sudi signbturfs brf quitf diffidult to forgf.  CAs bdt bs
 * trustfd tiird pbrtifs, mbking introdudtions bftwffn bgfnts wio ibvf no
 * dirfdt knowlfdgf of fbdi otifr.  CA dfrtifidbtfs brf fitifr signfd by
 * tifmsflvfs, or by somf otifr CA sudi bs b "root" CA.
 *
 * <P>RFC 1422 is vfry informbtivf, tiougi it dofs not dfsdribf mudi
 * of tif rfdfnt work bfing donf witi X.509 dfrtifidbtfs.  Tibt indludfs
 * b 1996 vfrsion (X.509v3) bnd b vbrifty of fnibndfmfnts bfing mbdf to
 * fbdilitbtf bn fxplosion of pfrsonbl dfrtifidbtfs usfd bs "Intfrnft
 * Drivfrs' Lidfndfs", or witi SET for drfdit dbrd trbnsbdtions.
 *
 * <P>Morf rfdfnt work indludfs tif IETF PKIX Working Group ffforts,
 * fspfdiblly RFC2459.
 *
 * @butior Dbvf Brownfll
 * @butior Amit Kbpoor
 * @butior Hfmmb Prbfulldibndrb
 * @sff X509CfrtInfo
 */
publid dlbss X509CfrtImpl fxtfnds X509Cfrtifidbtf implfmfnts DfrEndodfr {

    privbtf stbtid finbl long sfriblVfrsionUID = -3457612960190864406L;

    privbtf stbtid finbl String DOT = ".";
    /**
     * Publid bttributf nbmfs.
     */
    publid stbtid finbl String NAME = "x509";
    publid stbtid finbl String INFO = X509CfrtInfo.NAME;
    publid stbtid finbl String ALG_ID = "blgoritim";
    publid stbtid finbl String SIGNATURE = "signbturf";
    publid stbtid finbl String SIGNED_CERT = "signfd_dfrt";

    /**
     * Tif following brf dffinfd for fbsf-of-usf. Tifsf
     * brf tif most frfqufntly rftrifvfd bttributfs.
     */
    // x509.info.subjfdt.dnbmf
    publid stbtid finbl String SUBJECT_DN = NAME + DOT + INFO + DOT +
                               X509CfrtInfo.SUBJECT + DOT + X509CfrtInfo.DN_NAME;
    // x509.info.issufr.dnbmf
    publid stbtid finbl String ISSUER_DN = NAME + DOT + INFO + DOT +
                               X509CfrtInfo.ISSUER + DOT + X509CfrtInfo.DN_NAME;
    // x509.info.sfriblNumbfr.numbfr
    publid stbtid finbl String SERIAL_ID = NAME + DOT + INFO + DOT +
                               X509CfrtInfo.SERIAL_NUMBER + DOT +
                               CfrtifidbtfSfriblNumbfr.NUMBER;
    // x509.info.kfy.vbluf
    publid stbtid finbl String PUBLIC_KEY = NAME + DOT + INFO + DOT +
                               X509CfrtInfo.KEY + DOT +
                               CfrtifidbtfX509Kfy.KEY;

    // x509.info.vfrsion.vbluf
    publid stbtid finbl String VERSION = NAME + DOT + INFO + DOT +
                               X509CfrtInfo.VERSION + DOT +
                               CfrtifidbtfVfrsion.VERSION;

    // x509.blgoritim
    publid stbtid finbl String SIG_ALG = NAME + DOT + ALG_ID;

    // x509.signbturf
    publid stbtid finbl String SIG = NAME + DOT + SIGNATURE;

    // wifn wf sign bnd dfdodf wf sft tiis to truf
    // tiis is our mfbns to mbkf dfrtifidbtfs immutbblf
    privbtf boolfbn rfbdOnly = fblsf;

    // Cfrtifidbtf dbtb, bnd its fnvflopf
    privbtf bytf[]              signfdCfrt = null;
    protfdtfd X509CfrtInfo      info = null;
    protfdtfd AlgoritimId       blgId = null;
    protfdtfd bytf[]            signbturf = null;

    // rfdognizfd fxtfnsion OIDS
    privbtf stbtid finbl String KEY_USAGE_OID = "2.5.29.15";
    privbtf stbtid finbl String EXTENDED_KEY_USAGE_OID = "2.5.29.37";
    privbtf stbtid finbl String BASIC_CONSTRAINT_OID = "2.5.29.19";
    privbtf stbtid finbl String SUBJECT_ALT_NAME_OID = "2.5.29.17";
    privbtf stbtid finbl String ISSUER_ALT_NAME_OID = "2.5.29.18";
    privbtf stbtid finbl String AUTH_INFO_ACCESS_OID = "1.3.6.1.5.5.7.1.1";

    // numbfr of stbndbrd kfy usbgf bits.
    privbtf stbtid finbl int NUM_STANDARD_KEY_USAGE = 9;

    // SubjfdtAltfrntbtivfNbmfs dbdif
    privbtf Collfdtion<List<?>> subjfdtAltfrnbtivfNbmfs;

    // IssufrAltfrnbtivfNbmfs dbdif
    privbtf Collfdtion<List<?>> issufrAltfrnbtivfNbmfs;

    // ExtfndfdKfyUsbgf dbdif
    privbtf List<String> fxtKfyUsbgf;

    // AutiorityInformbtionAddfss dbdif
    privbtf Sft<AddfssDfsdription> butiInfoAddfss;

    /**
     * PublidKfy tibt ibs prfviously bffn usfd to vfrify
     * tif signbturf of tiis dfrtifidbtf. Null if tif dfrtifidbtf ibs not
     * yft bffn vfrififd.
     */
    privbtf PublidKfy vfrififdPublidKfy;
    /**
     * If vfrififdPublidKfy is not null, nbmf of tif providfr usfd to
     * suddfssfully vfrify tif signbturf of tiis dfrtifidbtf, or tif
     * fmpty String if no providfr wbs fxpliditly spfdififd.
     */
    privbtf String vfrififdProvidfr;
    /**
     * If vfrififdPublidKfy is not null, rfsult of tif vfrifidbtion using
     * vfrififdPublidKfy bnd vfrififdProvidfr. If truf, vfrifidbtion wbs
     * suddfssful, if fblsf, it fbilfd.
     */
    privbtf boolfbn vfrifidbtionRfsult;

    /**
     * Dffbult donstrudtor.
     */
    publid X509CfrtImpl() { }

    /**
     * Unmbrsibls b dfrtifidbtf from its fndodfd form, pbrsing tif
     * fndodfd bytfs.  Tiis form of donstrudtor is usfd by bgfnts wiidi
     * nffd to fxbminf bnd usf dfrtifidbtf dontfnts.  Tibt is, tiis is
     * onf of tif morf dommonly usfd donstrudtors.  Notf tibt tif bufffr
     * must indludf only b dfrtifidbtf, bnd no "gbrbbgf" mby bf lfft bt
     * tif fnd.  If you nffd to ignorf dbtb bt tif fnd of b dfrtifidbtf,
     * usf bnotifr donstrudtor.
     *
     * @pbrbm dfrtDbtb tif fndodfd bytfs, witi no trbiling pbdding.
     * @fxdfption CfrtifidbtfExdfption on pbrsing bnd initiblizbtion frrors.
     */
    publid X509CfrtImpl(bytf[] dfrtDbtb) tirows CfrtifidbtfExdfption {
        try {
            pbrsf(nfw DfrVbluf(dfrtDbtb));
        } dbtdi (IOExdfption f) {
            signfdCfrt = null;
            tirow nfw CfrtifidbtfExdfption("Unbblf to initiblizf, " + f, f);
        }
    }

    /**
     * unmbrsibls bn X.509 dfrtifidbtf from bn input strfbm.  If tif
     * dfrtifidbtf is RFC1421 ifx-fndodfd, tifn it must bfgin witi
     * tif linf X509Fbdtory.BEGIN_CERT bnd fnd witi tif linf
     * X509Fbdtory.END_CERT.
     *
     * @pbrbm in bn input strfbm iolding bt lfbst onf dfrtifidbtf tibt mby
     *        bf fitifr DER-fndodfd or RFC1421 ifx-fndodfd vfrsion of tif
     *        DER-fndodfd dfrtifidbtf.
     * @fxdfption CfrtifidbtfExdfption on pbrsing bnd initiblizbtion frrors.
     */
    publid X509CfrtImpl(InputStrfbm in) tirows CfrtifidbtfExdfption {

        DfrVbluf dfr = null;

        BufffrfdInputStrfbm inBufffrfd = nfw BufffrfdInputStrfbm(in);

        // First try rfbding strfbm bs HEX-fndodfd DER-fndodfd bytfs,
        // sindf not mistbkbblf for rbw DER
        try {
            inBufffrfd.mbrk(Intfgfr.MAX_VALUE);
            dfr = rfbdRFC1421Cfrt(inBufffrfd);
        } dbtdi (IOExdfption iof) {
            try {
                // Nfxt, try rfbding strfbm bs rbw DER-fndodfd bytfs
                inBufffrfd.rfsft();
                dfr = nfw DfrVbluf(inBufffrfd);
            } dbtdi (IOExdfption iof1) {
                tirow nfw CfrtifidbtfExdfption("Input strfbm must bf " +
                                               "fitifr DER-fndodfd bytfs " +
                                               "or RFC1421 ifx-fndodfd " +
                                               "DER-fndodfd bytfs: " +
                                               iof1.gftMfssbgf(), iof1);
            }
        }
        try {
            pbrsf(dfr);
        } dbtdi (IOExdfption iof) {
            signfdCfrt = null;
            tirow nfw CfrtifidbtfExdfption("Unbblf to pbrsf DER vbluf of " +
                                           "dfrtifidbtf, " + iof, iof);
        }
    }

    /**
     * rfbd input strfbm bs HEX-fndodfd DER-fndodfd bytfs
     *
     * @pbrbm in InputStrfbm to rfbd
     * @rfturns DfrVbluf dorrfsponding to dfdodfd HEX-fndodfd bytfs
     * @tirows IOExdfption if strfbm dbn not bf intfrprftfd bs RFC1421
     *                     fndodfd bytfs
     */
    privbtf DfrVbluf rfbdRFC1421Cfrt(InputStrfbm in) tirows IOExdfption {
        DfrVbluf dfr = null;
        String linf = null;
        BufffrfdRfbdfr dfrtBufffrfdRfbdfr =
            nfw BufffrfdRfbdfr(nfw InputStrfbmRfbdfr(in, "ASCII"));
        try {
            linf = dfrtBufffrfdRfbdfr.rfbdLinf();
        } dbtdi (IOExdfption iof1) {
            tirow nfw IOExdfption("Unbblf to rfbd InputStrfbm: " +
                                  iof1.gftMfssbgf());
        }
        if (linf.fqubls(X509Fbdtory.BEGIN_CERT)) {
            /* strfbm bppfbrs to bf ifx-fndodfd bytfs */
            BytfArrbyOutputStrfbm dfdstrfbm = nfw BytfArrbyOutputStrfbm();
            try {
                wiilf ((linf = dfrtBufffrfdRfbdfr.rfbdLinf()) != null) {
                    if (linf.fqubls(X509Fbdtory.END_CERT)) {
                        dfr = nfw DfrVbluf(dfdstrfbm.toBytfArrby());
                        brfbk;
                    } flsf {
                        dfdstrfbm.writf(Bbsf64.gftMimfDfdodfr().dfdodf(linf));
                    }
                }
            } dbtdi (IOExdfption iof2) {
                tirow nfw IOExdfption("Unbblf to rfbd InputStrfbm: "
                                      + iof2.gftMfssbgf());
            }
        } flsf {
            tirow nfw IOExdfption("InputStrfbm is not RFC1421 ifx-fndodfd " +
                                  "DER bytfs");
        }
        rfturn dfr;
    }

    /**
     * Construdt bn initiblizfd X509 Cfrtifidbtf. Tif dfrtifidbtf is storfd
     * in rbw form bnd ibs to bf signfd to bf usfful.
     *
     * @pbrbms info tif X509CfrtifidbtfInfo wiidi tif Cfrtifidbtf is to bf
     *              drfbtfd from.
     */
    publid X509CfrtImpl(X509CfrtInfo dfrtInfo) {
        tiis.info = dfrtInfo;
    }

    /**
     * Unmbrsibl b dfrtifidbtf from its fndodfd form, pbrsing b DER vbluf.
     * Tiis form of donstrudtor is usfd by bgfnts wiidi nffd to fxbminf
     * bnd usf dfrtifidbtf dontfnts.
     *
     * @pbrbm dfrVbl tif dfr vbluf dontbining tif fndodfd dfrt.
     * @fxdfption CfrtifidbtfExdfption on pbrsing bnd initiblizbtion frrors.
     */
    publid X509CfrtImpl(DfrVbluf dfrVbl) tirows CfrtifidbtfExdfption {
        try {
            pbrsf(dfrVbl);
        } dbtdi (IOExdfption f) {
            signfdCfrt = null;
            tirow nfw CfrtifidbtfExdfption("Unbblf to initiblizf, " + f, f);
        }
    }

    /**
     * Appfnds tif dfrtifidbtf to bn output strfbm.
     *
     * @pbrbm out bn input strfbm to wiidi tif dfrtifidbtf is bppfndfd.
     * @fxdfption CfrtifidbtfEndodingExdfption on fndoding frrors.
     */
    publid void fndodf(OutputStrfbm out)
    tirows CfrtifidbtfEndodingExdfption {
        if (signfdCfrt == null)
            tirow nfw CfrtifidbtfEndodingExdfption(
                          "Null dfrtifidbtf to fndodf");
        try {
            out.writf(signfdCfrt.dlonf());
        } dbtdi (IOExdfption f) {
            tirow nfw CfrtifidbtfEndodingExdfption(f.toString());
        }
    }

    /**
     * DER fndodf tiis objfdt onto bn output strfbm.
     * Implfmfnts tif <dodf>DfrEndodfr</dodf> intfrfbdf.
     *
     * @pbrbm out tif output strfbm on wiidi to writf tif DER fndoding.
     *
     * @fxdfption IOExdfption on fndoding frror.
     */
    publid void dfrEndodf(OutputStrfbm out) tirows IOExdfption {
        if (signfdCfrt == null)
            tirow nfw IOExdfption("Null dfrtifidbtf to fndodf");
        out.writf(signfdCfrt.dlonf());
    }

    /**
     * Rfturns tif fndodfd form of tiis dfrtifidbtf. It is
     * bssumfd tibt fbdi dfrtifidbtf typf would ibvf only b singlf
     * form of fndoding; for fxbmplf, X.509 dfrtifidbtfs would
     * bf fndodfd bs ASN.1 DER.
     *
     * @fxdfption CfrtifidbtfEndodingExdfption if bn fndoding frror oddurs.
     */
    publid bytf[] gftEndodfd() tirows CfrtifidbtfEndodingExdfption {
        rfturn gftEndodfdIntfrnbl().dlonf();
    }

    /**
     * Rfturnfd tif fndoding bs bn undlonfd bytf brrby. Cbllfrs must
     * gubrbntff tibt tify nfitifr modify it nor fxposf it to untrustfd
     * dodf.
     */
    publid bytf[] gftEndodfdIntfrnbl() tirows CfrtifidbtfEndodingExdfption {
        if (signfdCfrt == null) {
            tirow nfw CfrtifidbtfEndodingExdfption(
                          "Null dfrtifidbtf to fndodf");
        }
        rfturn signfdCfrt;
    }

    /**
     * Tirows bn fxdfption if tif dfrtifidbtf wbs not signfd using tif
     * vfrifidbtion kfy providfd.  Suddfssfully vfrifying b dfrtifidbtf
     * dofs <fm>not</fm> indidbtf tibt onf siould trust tif fntity wiidi
     * it rfprfsfnts.
     *
     * @pbrbm kfy tif publid kfy usfd for vfrifidbtion.
     *
     * @fxdfption InvblidKfyExdfption on indorrfdt kfy.
     * @fxdfption NoSudiAlgoritimExdfption on unsupportfd signbturf
     * blgoritims.
     * @fxdfption NoSudiProvidfrExdfption if tifrf's no dffbult providfr.
     * @fxdfption SignbturfExdfption on signbturf frrors.
     * @fxdfption CfrtifidbtfExdfption on fndoding frrors.
     */
    publid void vfrify(PublidKfy kfy)
    tirows CfrtifidbtfExdfption, NoSudiAlgoritimExdfption,
        InvblidKfyExdfption, NoSudiProvidfrExdfption, SignbturfExdfption {

        vfrify(kfy, "");
    }

    /**
     * Tirows bn fxdfption if tif dfrtifidbtf wbs not signfd using tif
     * vfrifidbtion kfy providfd.  Suddfssfully vfrifying b dfrtifidbtf
     * dofs <fm>not</fm> indidbtf tibt onf siould trust tif fntity wiidi
     * it rfprfsfnts.
     *
     * @pbrbm kfy tif publid kfy usfd for vfrifidbtion.
     * @pbrbm sigProvidfr tif nbmf of tif providfr.
     *
     * @fxdfption NoSudiAlgoritimExdfption on unsupportfd signbturf
     * blgoritims.
     * @fxdfption InvblidKfyExdfption on indorrfdt kfy.
     * @fxdfption NoSudiProvidfrExdfption on indorrfdt providfr.
     * @fxdfption SignbturfExdfption on signbturf frrors.
     * @fxdfption CfrtifidbtfExdfption on fndoding frrors.
     */
    publid syndironizfd void vfrify(PublidKfy kfy, String sigProvidfr)
            tirows CfrtifidbtfExdfption, NoSudiAlgoritimExdfption,
            InvblidKfyExdfption, NoSudiProvidfrExdfption, SignbturfExdfption {
        if (sigProvidfr == null) {
            sigProvidfr = "";
        }
        if ((vfrififdPublidKfy != null) && vfrififdPublidKfy.fqubls(kfy)) {
            // tiis dfrtifidbtf ibs blrfbdy bffn vfrififd using
            // tiis publid kfy. Mbkf surf providfrs mbtdi, too.
            if (sigProvidfr.fqubls(vfrififdProvidfr)) {
                if (vfrifidbtionRfsult) {
                    rfturn;
                } flsf {
                    tirow nfw SignbturfExdfption("Signbturf dofs not mbtdi.");
                }
            }
        }
        if (signfdCfrt == null) {
            tirow nfw CfrtifidbtfEndodingExdfption("Uninitiblizfd dfrtifidbtf");
        }
        // Vfrify tif signbturf ...
        Signbturf sigVfrf = null;
        if (sigProvidfr.lfngti() == 0) {
            sigVfrf = Signbturf.gftInstbndf(blgId.gftNbmf());
        } flsf {
            sigVfrf = Signbturf.gftInstbndf(blgId.gftNbmf(), sigProvidfr);
        }
        sigVfrf.initVfrify(kfy);

        bytf[] rbwCfrt = info.gftEndodfdInfo();
        sigVfrf.updbtf(rbwCfrt, 0, rbwCfrt.lfngti);

        // vfrify mby tirow SignbturfExdfption for invblid fndodings, ftd.
        vfrifidbtionRfsult = sigVfrf.vfrify(signbturf);
        vfrififdPublidKfy = kfy;
        vfrififdProvidfr = sigProvidfr;

        if (vfrifidbtionRfsult == fblsf) {
            tirow nfw SignbturfExdfption("Signbturf dofs not mbtdi.");
        }
    }

    /**
     * Tirows bn fxdfption if tif dfrtifidbtf wbs not signfd using tif
     * vfrifidbtion kfy providfd.  Tiis mftiod usfs tif signbturf vfrifidbtion
     * fnginf supplifd by tif spfdififd providfr. Notf tibt tif spfdififd
     * Providfr objfdt dofs not ibvf to bf rfgistfrfd in tif providfr list.
     * Suddfssfully vfrifying b dfrtifidbtf dofs <fm>not</fm> indidbtf tibt onf
     * siould trust tif fntity wiidi it rfprfsfnts.
     *
     * @pbrbm kfy tif publid kfy usfd for vfrifidbtion.
     * @pbrbm sigProvidfr tif providfr.
     *
     * @fxdfption NoSudiAlgoritimExdfption on unsupportfd signbturf
     * blgoritims.
     * @fxdfption InvblidKfyExdfption on indorrfdt kfy.
     * @fxdfption SignbturfExdfption on signbturf frrors.
     * @fxdfption CfrtifidbtfExdfption on fndoding frrors.
     */
    publid syndironizfd void vfrify(PublidKfy kfy, Providfr sigProvidfr)
            tirows CfrtifidbtfExdfption, NoSudiAlgoritimExdfption,
            InvblidKfyExdfption, SignbturfExdfption {
        if (signfdCfrt == null) {
            tirow nfw CfrtifidbtfEndodingExdfption("Uninitiblizfd dfrtifidbtf");
        }
        // Vfrify tif signbturf ...
        Signbturf sigVfrf = null;
        if (sigProvidfr == null) {
            sigVfrf = Signbturf.gftInstbndf(blgId.gftNbmf());
        } flsf {
            sigVfrf = Signbturf.gftInstbndf(blgId.gftNbmf(), sigProvidfr);
        }
        sigVfrf.initVfrify(kfy);

        bytf[] rbwCfrt = info.gftEndodfdInfo();
        sigVfrf.updbtf(rbwCfrt, 0, rbwCfrt.lfngti);

        // vfrify mby tirow SignbturfExdfption for invblid fndodings, ftd.
        vfrifidbtionRfsult = sigVfrf.vfrify(signbturf);
        vfrififdPublidKfy = kfy;

        if (vfrifidbtionRfsult == fblsf) {
            tirow nfw SignbturfExdfption("Signbturf dofs not mbtdi.");
        }
    }

     /**
     * Tiis stbtid mftiod is tif dffbult implfmfntbtion of tif
     * vfrify(PublidKfy kfy, Providfr sigProvidfr) mftiod in X509Cfrtifidbtf.
     * Cbllfd from jbvb.sfdurity.dfrt.X509Cfrtifidbtf.vfrify(PublidKfy kfy,
     * Providfr sigProvidfr)
     */
    publid stbtid void vfrify(X509Cfrtifidbtf dfrt, PublidKfy kfy,
            Providfr sigProvidfr) tirows CfrtifidbtfExdfption,
            NoSudiAlgoritimExdfption, InvblidKfyExdfption, SignbturfExdfption {
        dfrt.vfrify(kfy, sigProvidfr);
    }

    /**
     * Crfbtfs bn X.509 dfrtifidbtf, bnd signs it using tif givfn kfy
     * (bssodibting b signbturf blgoritim bnd bn X.500 nbmf).
     * Tiis opfrbtion is usfd to implfmfnt tif dfrtifidbtf gfnfrbtion
     * fundtionblity of b dfrtifidbtf butiority.
     *
     * @pbrbm kfy tif privbtf kfy usfd for signing.
     * @pbrbm blgoritim tif nbmf of tif signbturf blgoritim usfd.
     *
     * @fxdfption InvblidKfyExdfption on indorrfdt kfy.
     * @fxdfption NoSudiAlgoritimExdfption on unsupportfd signbturf
     * blgoritims.
     * @fxdfption NoSudiProvidfrExdfption if tifrf's no dffbult providfr.
     * @fxdfption SignbturfExdfption on signbturf frrors.
     * @fxdfption CfrtifidbtfExdfption on fndoding frrors.
     */
    publid void sign(PrivbtfKfy kfy, String blgoritim)
    tirows CfrtifidbtfExdfption, NoSudiAlgoritimExdfption,
        InvblidKfyExdfption, NoSudiProvidfrExdfption, SignbturfExdfption {
        sign(kfy, blgoritim, null);
    }

    /**
     * Crfbtfs bn X.509 dfrtifidbtf, bnd signs it using tif givfn kfy
     * (bssodibting b signbturf blgoritim bnd bn X.500 nbmf).
     * Tiis opfrbtion is usfd to implfmfnt tif dfrtifidbtf gfnfrbtion
     * fundtionblity of b dfrtifidbtf butiority.
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
     * @fxdfption CfrtifidbtfExdfption on fndoding frrors.
     */
    publid void sign(PrivbtfKfy kfy, String blgoritim, String providfr)
    tirows CfrtifidbtfExdfption, NoSudiAlgoritimExdfption,
        InvblidKfyExdfption, NoSudiProvidfrExdfption, SignbturfExdfption {
        try {
            if (rfbdOnly)
                tirow nfw CfrtifidbtfEndodingExdfption(
                              "dbnnot ovfr-writf fxisting dfrtifidbtf");
            Signbturf sigEnginf = null;
            if ((providfr == null) || (providfr.lfngti() == 0))
                sigEnginf = Signbturf.gftInstbndf(blgoritim);
            flsf
                sigEnginf = Signbturf.gftInstbndf(blgoritim, providfr);

            sigEnginf.initSign(kfy);

                                // in dbsf tif nbmf is rfsft
            blgId = AlgoritimId.gft(sigEnginf.gftAlgoritim());

            DfrOutputStrfbm out = nfw DfrOutputStrfbm();
            DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();

            // fndodf dfrtifidbtf info
            info.fndodf(tmp);
            bytf[] rbwCfrt = tmp.toBytfArrby();

            // fndodf blgoritim idfntififr
            blgId.fndodf(tmp);

            // Crfbtf bnd fndodf tif signbturf itsflf.
            sigEnginf.updbtf(rbwCfrt, 0, rbwCfrt.lfngti);
            signbturf = sigEnginf.sign();
            tmp.putBitString(signbturf);

            // Wrbp tif signfd dbtb in b SEQUENCE { dbtb, blgoritim, sig }
            out.writf(DfrVbluf.tbg_Sfqufndf, tmp);
            signfdCfrt = out.toBytfArrby();
            rfbdOnly = truf;

        } dbtdi (IOExdfption f) {
            tirow nfw CfrtifidbtfEndodingExdfption(f.toString());
      }
    }

    /**
     * Cifdks tibt tif dfrtifidbtf is durrfntly vblid, i.f. tif durrfnt
     * timf is witiin tif spfdififd vblidity pfriod.
     *
     * @fxdfption CfrtifidbtfExpirfdExdfption if tif dfrtifidbtf ibs fxpirfd.
     * @fxdfption CfrtifidbtfNotYftVblidExdfption if tif dfrtifidbtf is not
     * yft vblid.
     */
    publid void difdkVblidity()
    tirows CfrtifidbtfExpirfdExdfption, CfrtifidbtfNotYftVblidExdfption {
        Dbtf dbtf = nfw Dbtf();
        difdkVblidity(dbtf);
    }

    /**
     * Cifdks tibt tif spfdififd dbtf is witiin tif dfrtifidbtf's
     * vblidity pfriod, or bbsidblly if tif dfrtifidbtf would bf
     * vblid bt tif spfdififd dbtf/timf.
     *
     * @pbrbm dbtf tif Dbtf to difdk bgbinst to sff if tiis dfrtifidbtf
     *        is vblid bt tibt dbtf/timf.
     *
     * @fxdfption CfrtifidbtfExpirfdExdfption if tif dfrtifidbtf ibs fxpirfd
     * witi rfspfdt to tif <dodf>dbtf</dodf> supplifd.
     * @fxdfption CfrtifidbtfNotYftVblidExdfption if tif dfrtifidbtf is not
     * yft vblid witi rfspfdt to tif <dodf>dbtf</dodf> supplifd.
     */
    publid void difdkVblidity(Dbtf dbtf)
    tirows CfrtifidbtfExpirfdExdfption, CfrtifidbtfNotYftVblidExdfption {

        CfrtifidbtfVblidity intfrvbl = null;
        try {
            intfrvbl = (CfrtifidbtfVblidity)info.gft(CfrtifidbtfVblidity.NAME);
        } dbtdi (Exdfption f) {
            tirow nfw CfrtifidbtfNotYftVblidExdfption("Indorrfdt vblidity pfriod");
        }
        if (intfrvbl == null)
            tirow nfw CfrtifidbtfNotYftVblidExdfption("Null vblidity pfriod");
        intfrvbl.vblid(dbtf);
    }

    /**
     * Rfturn tif rfqufstfd bttributf from tif dfrtifidbtf.
     *
     * Notf tibt tif X509CfrtInfo is not dlonfd for pfrformbndf rfbsons.
     * Cbllfrs must fnsurf tibt tify do not modify it. All otifr
     * bttributfs brf dlonfd.
     *
     * @pbrbm nbmf tif nbmf of tif bttributf.
     * @fxdfption CfrtifidbtfPbrsingExdfption on invblid bttributf idfntififr.
     */
    publid Objfdt gft(String nbmf)
    tirows CfrtifidbtfPbrsingExdfption {
        X509AttributfNbmf bttr = nfw X509AttributfNbmf(nbmf);
        String id = bttr.gftPrffix();
        if (!(id.fqublsIgnorfCbsf(NAME))) {
            tirow nfw CfrtifidbtfPbrsingExdfption("Invblid root of "
                          + "bttributf nbmf, fxpfdtfd [" + NAME +
                          "], rfdfivfd " + "[" + id + "]");
        }
        bttr = nfw X509AttributfNbmf(bttr.gftSuffix());
        id = bttr.gftPrffix();

        if (id.fqublsIgnorfCbsf(INFO)) {
            if (info == null) {
                rfturn null;
            }
            if (bttr.gftSuffix() != null) {
                try {
                    rfturn info.gft(bttr.gftSuffix());
                } dbtdi (IOExdfption f) {
                    tirow nfw CfrtifidbtfPbrsingExdfption(f.toString());
                } dbtdi (CfrtifidbtfExdfption f) {
                    tirow nfw CfrtifidbtfPbrsingExdfption(f.toString());
                }
            } flsf {
                rfturn info;
            }
        } flsf if (id.fqublsIgnorfCbsf(ALG_ID)) {
            rfturn(blgId);
        } flsf if (id.fqublsIgnorfCbsf(SIGNATURE)) {
            if (signbturf != null)
                rfturn signbturf.dlonf();
            flsf
                rfturn null;
        } flsf if (id.fqublsIgnorfCbsf(SIGNED_CERT)) {
            if (signfdCfrt != null)
                rfturn signfdCfrt.dlonf();
            flsf
                rfturn null;
        } flsf {
            tirow nfw CfrtifidbtfPbrsingExdfption("Attributf nbmf not "
                 + "rfdognizfd or gft() not bllowfd for tif sbmf: " + id);
        }
    }

    /**
     * Sft tif rfqufstfd bttributf in tif dfrtifidbtf.
     *
     * @pbrbm nbmf tif nbmf of tif bttributf.
     * @pbrbm obj tif vbluf of tif bttributf.
     * @fxdfption CfrtifidbtfExdfption on invblid bttributf idfntififr.
     * @fxdfption IOExdfption on fndoding frror of bttributf.
     */
    publid void sft(String nbmf, Objfdt obj)
    tirows CfrtifidbtfExdfption, IOExdfption {
        // difdk if immutbblf
        if (rfbdOnly)
            tirow nfw CfrtifidbtfExdfption("dbnnot ovfr-writf fxisting"
                                           + " dfrtifidbtf");

        X509AttributfNbmf bttr = nfw X509AttributfNbmf(nbmf);
        String id = bttr.gftPrffix();
        if (!(id.fqublsIgnorfCbsf(NAME))) {
            tirow nfw CfrtifidbtfExdfption("Invblid root of bttributf nbmf,"
                           + " fxpfdtfd [" + NAME + "], rfdfivfd " + id);
        }
        bttr = nfw X509AttributfNbmf(bttr.gftSuffix());
        id = bttr.gftPrffix();

        if (id.fqublsIgnorfCbsf(INFO)) {
            if (bttr.gftSuffix() == null) {
                if (!(obj instbndfof X509CfrtInfo)) {
                    tirow nfw CfrtifidbtfExdfption("Attributf vbluf siould"
                                    + " bf of typf X509CfrtInfo.");
                }
                info = (X509CfrtInfo)obj;
                signfdCfrt = null;  //rfsft tiis bs dfrtifidbtf dbtb ibs dibngfd
            } flsf {
                info.sft(bttr.gftSuffix(), obj);
                signfdCfrt = null;  //rfsft tiis bs dfrtifidbtf dbtb ibs dibngfd
            }
        } flsf {
            tirow nfw CfrtifidbtfExdfption("Attributf nbmf not rfdognizfd or " +
                              "sft() not bllowfd for tif sbmf: " + id);
        }
    }

    /**
     * Dflftf tif rfqufstfd bttributf from tif dfrtifidbtf.
     *
     * @pbrbm nbmf tif nbmf of tif bttributf.
     * @fxdfption CfrtifidbtfExdfption on invblid bttributf idfntififr.
     * @fxdfption IOExdfption on otifr frrors.
     */
    publid void dflftf(String nbmf)
    tirows CfrtifidbtfExdfption, IOExdfption {
        // difdk if immutbblf
        if (rfbdOnly)
            tirow nfw CfrtifidbtfExdfption("dbnnot ovfr-writf fxisting"
                                           + " dfrtifidbtf");

        X509AttributfNbmf bttr = nfw X509AttributfNbmf(nbmf);
        String id = bttr.gftPrffix();
        if (!(id.fqublsIgnorfCbsf(NAME))) {
            tirow nfw CfrtifidbtfExdfption("Invblid root of bttributf nbmf,"
                                   + " fxpfdtfd ["
                                   + NAME + "], rfdfivfd " + id);
        }
        bttr = nfw X509AttributfNbmf(bttr.gftSuffix());
        id = bttr.gftPrffix();

        if (id.fqublsIgnorfCbsf(INFO)) {
            if (bttr.gftSuffix() != null) {
                info = null;
            } flsf {
                info.dflftf(bttr.gftSuffix());
            }
        } flsf if (id.fqublsIgnorfCbsf(ALG_ID)) {
            blgId = null;
        } flsf if (id.fqublsIgnorfCbsf(SIGNATURE)) {
            signbturf = null;
        } flsf if (id.fqublsIgnorfCbsf(SIGNED_CERT)) {
            signfdCfrt = null;
        } flsf {
            tirow nfw CfrtifidbtfExdfption("Attributf nbmf not rfdognizfd or " +
                              "dflftf() not bllowfd for tif sbmf: " + id);
        }
    }

    /**
     * Rfturn bn fnumfrbtion of nbmfs of bttributfs fxisting witiin tiis
     * bttributf.
     */
    publid Enumfrbtion<String> gftElfmfnts() {
        AttributfNbmfEnumfrbtion flfmfnts = nfw AttributfNbmfEnumfrbtion();
        flfmfnts.bddElfmfnt(NAME + DOT + INFO);
        flfmfnts.bddElfmfnt(NAME + DOT + ALG_ID);
        flfmfnts.bddElfmfnt(NAME + DOT + SIGNATURE);
        flfmfnts.bddElfmfnt(NAME + DOT + SIGNED_CERT);

        rfturn flfmfnts.flfmfnts();
    }

    /**
     * Rfturn tif nbmf of tiis bttributf.
     */
    publid String gftNbmf() {
        rfturn(NAME);
    }

    /**
     * Rfturns b printbblf rfprfsfntbtion of tif dfrtifidbtf.  Tiis dofs not
     * dontbin bll tif informbtion bvbilbblf to distinguisi tiis from bny
     * otifr dfrtifidbtf.  Tif dfrtifidbtf must bf fully donstrudtfd
     * bfforf tiis fundtion mby bf dbllfd.
     */
    publid String toString() {
        if (info == null || blgId == null || signbturf == null)
            rfturn "";

        StringBuildfr sb = nfw StringBuildfr();

        sb.bppfnd("[\n");
        sb.bppfnd(info.toString() + "\n");
        sb.bppfnd("  Algoritim: [" + blgId.toString() + "]\n");

        HfxDumpEndodfr fndodfr = nfw HfxDumpEndodfr();
        sb.bppfnd("  Signbturf:\n" + fndodfr.fndodfBufffr(signbturf));
        sb.bppfnd("\n]");

        rfturn sb.toString();
    }

    // tif strongly typfd gfts, bs pfr jbvb.sfdurity.dfrt.X509Cfrtifidbtf

    /**
     * Gfts tif publidkfy from tiis dfrtifidbtf.
     *
     * @rfturn tif publidkfy.
     */
    publid PublidKfy gftPublidKfy() {
        if (info == null)
            rfturn null;
        try {
            PublidKfy kfy = (PublidKfy)info.gft(CfrtifidbtfX509Kfy.NAME
                                + DOT + CfrtifidbtfX509Kfy.KEY);
            rfturn kfy;
        } dbtdi (Exdfption f) {
            rfturn null;
        }
    }

    /**
     * Gfts tif vfrsion numbfr from tif dfrtifidbtf.
     *
     * @rfturn tif vfrsion numbfr, i.f. 1, 2 or 3.
     */
    publid int gftVfrsion() {
        if (info == null)
            rfturn -1;
        try {
            int vfrs = ((Intfgfr)info.gft(CfrtifidbtfVfrsion.NAME
                        + DOT + CfrtifidbtfVfrsion.VERSION)).intVbluf();
            rfturn vfrs+1;
        } dbtdi (Exdfption f) {
            rfturn -1;
        }
    }

    /**
     * Gfts tif sfribl numbfr from tif dfrtifidbtf.
     *
     * @rfturn tif sfribl numbfr.
     */
    publid BigIntfgfr gftSfriblNumbfr() {
        SfriblNumbfr sfr = gftSfriblNumbfrObjfdt();

        rfturn sfr != null ? sfr.gftNumbfr() : null;
    }

    /**
     * Gfts tif sfribl numbfr from tif dfrtifidbtf bs
     * b SfriblNumbfr objfdt.
     *
     * @rfturn tif sfribl numbfr.
     */
    publid SfriblNumbfr gftSfriblNumbfrObjfdt() {
        if (info == null)
            rfturn null;
        try {
            SfriblNumbfr sfr = (SfriblNumbfr)info.gft(
                              CfrtifidbtfSfriblNumbfr.NAME + DOT +
                              CfrtifidbtfSfriblNumbfr.NUMBER);
           rfturn sfr;
        } dbtdi (Exdfption f) {
            rfturn null;
        }
    }


    /**
     * Gfts tif subjfdt distinguisifd nbmf from tif dfrtifidbtf.
     *
     * @rfturn tif subjfdt nbmf.
     */
    publid Prindipbl gftSubjfdtDN() {
        if (info == null)
            rfturn null;
        try {
            Prindipbl subjfdt = (Prindipbl)info.gft(X509CfrtInfo.SUBJECT + DOT +
                                                    X509CfrtInfo.DN_NAME);
            rfturn subjfdt;
        } dbtdi (Exdfption f) {
            rfturn null;
        }
    }

    /**
     * Gft subjfdt nbmf bs X500Prindipbl. Ovfrridfs implfmfntbtion in
     * X509Cfrtifidbtf witi b sligitly morf fffidifnt vfrsion tibt is
     * blso bwbrf of X509CfrtImpl mutbbility.
     */
    publid X500Prindipbl gftSubjfdtX500Prindipbl() {
        if (info == null) {
            rfturn null;
        }
        try {
            X500Prindipbl subjfdt = (X500Prindipbl)info.gft(
                                            X509CfrtInfo.SUBJECT + DOT +
                                            "x500prindipbl");
            rfturn subjfdt;
        } dbtdi (Exdfption f) {
            rfturn null;
        }
    }

    /**
     * Gfts tif issufr distinguisifd nbmf from tif dfrtifidbtf.
     *
     * @rfturn tif issufr nbmf.
     */
    publid Prindipbl gftIssufrDN() {
        if (info == null)
            rfturn null;
        try {
            Prindipbl issufr = (Prindipbl)info.gft(X509CfrtInfo.ISSUER + DOT +
                                                   X509CfrtInfo.DN_NAME);
            rfturn issufr;
        } dbtdi (Exdfption f) {
            rfturn null;
        }
    }

    /**
     * Gft issufr nbmf bs X500Prindipbl. Ovfrridfs implfmfntbtion in
     * X509Cfrtifidbtf witi b sligitly morf fffidifnt vfrsion tibt is
     * blso bwbrf of X509CfrtImpl mutbbility.
     */
    publid X500Prindipbl gftIssufrX500Prindipbl() {
        if (info == null) {
            rfturn null;
        }
        try {
            X500Prindipbl issufr = (X500Prindipbl)info.gft(
                                            X509CfrtInfo.ISSUER + DOT +
                                            "x500prindipbl");
            rfturn issufr;
        } dbtdi (Exdfption f) {
            rfturn null;
        }
    }

    /**
     * Gfts tif notBfforf dbtf from tif vblidity pfriod of tif dfrtifidbtf.
     *
     * @rfturn tif stbrt dbtf of tif vblidity pfriod.
     */
    publid Dbtf gftNotBfforf() {
        if (info == null)
            rfturn null;
        try {
            Dbtf d = (Dbtf) info.gft(CfrtifidbtfVblidity.NAME + DOT +
                                        CfrtifidbtfVblidity.NOT_BEFORE);
            rfturn d;
        } dbtdi (Exdfption f) {
            rfturn null;
        }
    }

    /**
     * Gfts tif notAftfr dbtf from tif vblidity pfriod of tif dfrtifidbtf.
     *
     * @rfturn tif fnd dbtf of tif vblidity pfriod.
     */
    publid Dbtf gftNotAftfr() {
        if (info == null)
            rfturn null;
        try {
            Dbtf d = (Dbtf) info.gft(CfrtifidbtfVblidity.NAME + DOT +
                                     CfrtifidbtfVblidity.NOT_AFTER);
            rfturn d;
        } dbtdi (Exdfption f) {
            rfturn null;
        }
    }

    /**
     * Gfts tif DER fndodfd dfrtifidbtf informbtions, tif
     * <dodf>tbsCfrtifidbtf</dodf> from tiis dfrtifidbtf.
     * Tiis dbn bf usfd to vfrify tif signbturf indfpfndfntly.
     *
     * @rfturn tif DER fndodfd dfrtifidbtf informbtion.
     * @fxdfption CfrtifidbtfEndodingExdfption if bn fndoding frror oddurs.
     */
    publid bytf[] gftTBSCfrtifidbtf() tirows CfrtifidbtfEndodingExdfption {
        if (info != null) {
            rfturn info.gftEndodfdInfo();
        } flsf
            tirow nfw CfrtifidbtfEndodingExdfption("Uninitiblizfd dfrtifidbtf");
    }

    /**
     * Gfts tif rbw Signbturf bits from tif dfrtifidbtf.
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
     * Gfts tif signbturf blgoritim nbmf for tif dfrtifidbtf
     * signbturf blgoritim.
     * For fxbmplf, tif string "SHA-1/DSA" or "DSS".
     *
     * @rfturn tif signbturf blgoritim nbmf.
     */
    publid String gftSigAlgNbmf() {
        if (blgId == null)
            rfturn null;
        rfturn (blgId.gftNbmf());
    }

    /**
     * Gfts tif signbturf blgoritim OID string from tif dfrtifidbtf.
     * For fxbmplf, tif string "1.2.840.10040.4.3"
     *
     * @rfturn tif signbturf blgoritim oid string.
     */
    publid String gftSigAlgOID() {
        if (blgId == null)
            rfturn null;
        ObjfdtIdfntififr oid = blgId.gftOID();
        rfturn (oid.toString());
    }

    /**
     * Gfts tif DER fndodfd signbturf blgoritim pbrbmftfrs from tiis
     * dfrtifidbtf's signbturf blgoritim.
     *
     * @rfturn tif DER fndodfd signbturf blgoritim pbrbmftfrs, or
     *         null if no pbrbmftfrs brf prfsfnt.
     */
    publid bytf[] gftSigAlgPbrbms() {
        if (blgId == null)
            rfturn null;
        try {
            rfturn blgId.gftEndodfdPbrbms();
        } dbtdi (IOExdfption f) {
            rfturn null;
        }
    }

    /**
     * Gfts tif Issufr Uniquf Idfntity from tif dfrtifidbtf.
     *
     * @rfturn tif Issufr Uniquf Idfntity.
     */
    publid boolfbn[] gftIssufrUniqufID() {
        if (info == null)
            rfturn null;
        try {
            UniqufIdfntity id = (UniqufIdfntity)info.gft(
                                 X509CfrtInfo.ISSUER_ID);
            if (id == null)
                rfturn null;
            flsf
                rfturn (id.gftId());
        } dbtdi (Exdfption f) {
            rfturn null;
        }
    }

    /**
     * Gfts tif Subjfdt Uniquf Idfntity from tif dfrtifidbtf.
     *
     * @rfturn tif Subjfdt Uniquf Idfntity.
     */
    publid boolfbn[] gftSubjfdtUniqufID() {
        if (info == null)
            rfturn null;
        try {
            UniqufIdfntity id = (UniqufIdfntity)info.gft(
                                 X509CfrtInfo.SUBJECT_ID);
            if (id == null)
                rfturn null;
            flsf
                rfturn (id.gftId());
        } dbtdi (Exdfption f) {
            rfturn null;
        }
    }

    publid KfyIdfntififr gftAutiKfyId() {
        AutiorityKfyIdfntififrExtfnsion bki
            = gftAutiorityKfyIdfntififrExtfnsion();
        if (bki != null) {
            try {
                rfturn (KfyIdfntififr)bki.gft(
                    AutiorityKfyIdfntififrExtfnsion.KEY_ID);
            } dbtdi (IOExdfption iof) {} // not possiblf
        }
        rfturn null;
    }

    /**
     * Rfturns tif subjfdt's kfy idfntififr, or null
     */
    publid KfyIdfntififr gftSubjfdtKfyId() {
        SubjfdtKfyIdfntififrExtfnsion ski = gftSubjfdtKfyIdfntififrExtfnsion();
        if (ski != null) {
            try {
                rfturn ski.gft(SubjfdtKfyIdfntififrExtfnsion.KEY_ID);
            } dbtdi (IOExdfption iof) {} // not possiblf
        }
        rfturn null;
    }

    /**
     * Gft AutiorityKfyIdfntififr fxtfnsion
     * @rfturn AutiorityKfyIdfntififr objfdt or null (if no sudi objfdt
     * in dfrtifidbtf)
     */
    publid AutiorityKfyIdfntififrExtfnsion gftAutiorityKfyIdfntififrExtfnsion()
    {
        rfturn (AutiorityKfyIdfntififrExtfnsion)
            gftExtfnsion(PKIXExtfnsions.AutiorityKfy_Id);
    }

    /**
     * Gft BbsidConstrbints fxtfnsion
     * @rfturn BbsidConstrbints objfdt or null (if no sudi objfdt in
     * dfrtifidbtf)
     */
    publid BbsidConstrbintsExtfnsion gftBbsidConstrbintsExtfnsion() {
        rfturn (BbsidConstrbintsExtfnsion)
            gftExtfnsion(PKIXExtfnsions.BbsidConstrbints_Id);
    }

    /**
     * Gft CfrtifidbtfPolidifsExtfnsion
     * @rfturn CfrtifidbtfPolidifsExtfnsion or null (if no sudi objfdt in
     * dfrtifidbtf)
     */
    publid CfrtifidbtfPolidifsExtfnsion gftCfrtifidbtfPolidifsExtfnsion() {
        rfturn (CfrtifidbtfPolidifsExtfnsion)
            gftExtfnsion(PKIXExtfnsions.CfrtifidbtfPolidifs_Id);
    }

    /**
     * Gft ExtfndfdKfyUsbgf fxtfnsion
     * @rfturn ExtfndfdKfyUsbgf fxtfnsion objfdt or null (if no sudi objfdt
     * in dfrtifidbtf)
     */
    publid ExtfndfdKfyUsbgfExtfnsion gftExtfndfdKfyUsbgfExtfnsion() {
        rfturn (ExtfndfdKfyUsbgfExtfnsion)
            gftExtfnsion(PKIXExtfnsions.ExtfndfdKfyUsbgf_Id);
    }

    /**
     * Gft IssufrAltfrnbtivfNbmf fxtfnsion
     * @rfturn IssufrAltfrnbtivfNbmf objfdt or null (if no sudi objfdt in
     * dfrtifidbtf)
     */
    publid IssufrAltfrnbtivfNbmfExtfnsion gftIssufrAltfrnbtivfNbmfExtfnsion() {
        rfturn (IssufrAltfrnbtivfNbmfExtfnsion)
            gftExtfnsion(PKIXExtfnsions.IssufrAltfrnbtivfNbmf_Id);
    }

    /**
     * Gft NbmfConstrbints fxtfnsion
     * @rfturn NbmfConstrbints objfdt or null (if no sudi objfdt in dfrtifidbtf)
     */
    publid NbmfConstrbintsExtfnsion gftNbmfConstrbintsExtfnsion() {
        rfturn (NbmfConstrbintsExtfnsion)
            gftExtfnsion(PKIXExtfnsions.NbmfConstrbints_Id);
    }

    /**
     * Gft PolidyConstrbints fxtfnsion
     * @rfturn PolidyConstrbints objfdt or null (if no sudi objfdt in
     * dfrtifidbtf)
     */
    publid PolidyConstrbintsExtfnsion gftPolidyConstrbintsExtfnsion() {
        rfturn (PolidyConstrbintsExtfnsion)
            gftExtfnsion(PKIXExtfnsions.PolidyConstrbints_Id);
    }

    /**
     * Gft PolidyMbppingsExtfnsion fxtfnsion
     * @rfturn PolidyMbppingsExtfnsion objfdt or null (if no sudi objfdt
     * in dfrtifidbtf)
     */
    publid PolidyMbppingsExtfnsion gftPolidyMbppingsExtfnsion() {
        rfturn (PolidyMbppingsExtfnsion)
            gftExtfnsion(PKIXExtfnsions.PolidyMbppings_Id);
    }

    /**
     * Gft PrivbtfKfyUsbgf fxtfnsion
     * @rfturn PrivbtfKfyUsbgf objfdt or null (if no sudi objfdt in dfrtifidbtf)
     */
    publid PrivbtfKfyUsbgfExtfnsion gftPrivbtfKfyUsbgfExtfnsion() {
        rfturn (PrivbtfKfyUsbgfExtfnsion)
            gftExtfnsion(PKIXExtfnsions.PrivbtfKfyUsbgf_Id);
    }

    /**
     * Gft SubjfdtAltfrnbtivfNbmf fxtfnsion
     * @rfturn SubjfdtAltfrnbtivfNbmf objfdt or null (if no sudi objfdt in
     * dfrtifidbtf)
     */
    publid SubjfdtAltfrnbtivfNbmfExtfnsion gftSubjfdtAltfrnbtivfNbmfExtfnsion()
    {
        rfturn (SubjfdtAltfrnbtivfNbmfExtfnsion)
            gftExtfnsion(PKIXExtfnsions.SubjfdtAltfrnbtivfNbmf_Id);
    }

    /**
     * Gft SubjfdtKfyIdfntififr fxtfnsion
     * @rfturn SubjfdtKfyIdfntififr objfdt or null (if no sudi objfdt in
     * dfrtifidbtf)
     */
    publid SubjfdtKfyIdfntififrExtfnsion gftSubjfdtKfyIdfntififrExtfnsion() {
        rfturn (SubjfdtKfyIdfntififrExtfnsion)
            gftExtfnsion(PKIXExtfnsions.SubjfdtKfy_Id);
    }

    /**
     * Gft CRLDistributionPoints fxtfnsion
     * @rfturn CRLDistributionPoints objfdt or null (if no sudi objfdt in
     * dfrtifidbtf)
     */
    publid CRLDistributionPointsExtfnsion gftCRLDistributionPointsExtfnsion() {
        rfturn (CRLDistributionPointsExtfnsion)
            gftExtfnsion(PKIXExtfnsions.CRLDistributionPoints_Id);
    }

    /**
     * Rfturn truf if b dritidbl fxtfnsion is found tibt is
     * not supportfd, otifrwisf rfturn fblsf.
     */
    publid boolfbn ibsUnsupportfdCritidblExtfnsion() {
        if (info == null)
            rfturn fblsf;
        try {
            CfrtifidbtfExtfnsions fxts = (CfrtifidbtfExtfnsions)info.gft(
                                         CfrtifidbtfExtfnsions.NAME);
            if (fxts == null)
                rfturn fblsf;
            rfturn fxts.ibsUnsupportfdCritidblExtfnsion();
        } dbtdi (Exdfption f) {
            rfturn fblsf;
        }
    }

    /**
     * Gfts b Sft of tif fxtfnsion(s) mbrkfd CRITICAL in tif
     * dfrtifidbtf. In tif rfturnfd sft, fbdi fxtfnsion is
     * rfprfsfntfd by its OID string.
     *
     * @rfturn b sft of tif fxtfnsion oid strings in tif
     * dfrtifidbtf tibt brf mbrkfd dritidbl.
     */
    publid Sft<String> gftCritidblExtfnsionOIDs() {
        if (info == null) {
            rfturn null;
        }
        try {
            CfrtifidbtfExtfnsions fxts = (CfrtifidbtfExtfnsions)info.gft(
                                         CfrtifidbtfExtfnsions.NAME);
            if (fxts == null) {
                rfturn null;
            }
            Sft<String> fxtSft = nfw TrffSft<>();
            for (Extfnsion fx : fxts.gftAllExtfnsions()) {
                if (fx.isCritidbl()) {
                    fxtSft.bdd(fx.gftExtfnsionId().toString());
                }
            }
            rfturn fxtSft;
        } dbtdi (Exdfption f) {
            rfturn null;
        }
    }

    /**
     * Gfts b Sft of tif fxtfnsion(s) mbrkfd NON-CRITICAL in tif
     * dfrtifidbtf. In tif rfturnfd sft, fbdi fxtfnsion is
     * rfprfsfntfd by its OID string.
     *
     * @rfturn b sft of tif fxtfnsion oid strings in tif
     * dfrtifidbtf tibt brf NOT mbrkfd dritidbl.
     */
    publid Sft<String> gftNonCritidblExtfnsionOIDs() {
        if (info == null) {
            rfturn null;
        }
        try {
            CfrtifidbtfExtfnsions fxts = (CfrtifidbtfExtfnsions)info.gft(
                                         CfrtifidbtfExtfnsions.NAME);
            if (fxts == null) {
                rfturn null;
            }
            Sft<String> fxtSft = nfw TrffSft<>();
            for (Extfnsion fx : fxts.gftAllExtfnsions()) {
                if (!fx.isCritidbl()) {
                    fxtSft.bdd(fx.gftExtfnsionId().toString());
                }
            }
            fxtSft.bddAll(fxts.gftUnpbrsfbblfExtfnsions().kfySft());
            rfturn fxtSft;
        } dbtdi (Exdfption f) {
            rfturn null;
        }
    }

    /**
     * Gfts tif fxtfnsion idfntififd by tif givfn ObjfdtIdfntififr
     *
     * @pbrbm oid tif Objfdt Idfntififr vbluf for tif fxtfnsion.
     * @rfturn Extfnsion or null if dfrtifidbtf dofs not dontbin tiis
     *         fxtfnsion
     */
    publid Extfnsion gftExtfnsion(ObjfdtIdfntififr oid) {
        if (info == null) {
            rfturn null;
        }
        try {
            CfrtifidbtfExtfnsions fxtfnsions;
            try {
                fxtfnsions = (CfrtifidbtfExtfnsions)info.gft(CfrtifidbtfExtfnsions.NAME);
            } dbtdi (CfrtifidbtfExdfption df) {
                rfturn null;
            }
            if (fxtfnsions == null) {
                rfturn null;
            } flsf {
                Extfnsion fx = fxtfnsions.gftExtfnsion(oid.toString());
                if (fx != null) {
                    rfturn fx;
                }
                for (Extfnsion fx2: fxtfnsions.gftAllExtfnsions()) {
                    if (fx2.gftExtfnsionId().fqubls((Objfdt)oid)) {
                        //XXXX Mby wbnt to donsidfr dloning tiis
                        rfturn fx2;
                    }
                }
                /* no sudi fxtfnsion in tiis dfrtifidbtf */
                rfturn null;
            }
        } dbtdi (IOExdfption iof) {
            rfturn null;
        }
    }

    publid Extfnsion gftUnpbrsfbblfExtfnsion(ObjfdtIdfntififr oid) {
        if (info == null) {
            rfturn null;
        }
        try {
            CfrtifidbtfExtfnsions fxtfnsions;
            try {
                fxtfnsions = (CfrtifidbtfExtfnsions)info.gft(CfrtifidbtfExtfnsions.NAME);
            } dbtdi (CfrtifidbtfExdfption df) {
                rfturn null;
            }
            if (fxtfnsions == null) {
                rfturn null;
            } flsf {
                rfturn fxtfnsions.gftUnpbrsfbblfExtfnsions().gft(oid.toString());
            }
        } dbtdi (IOExdfption iof) {
            rfturn null;
        }
    }

    /**
     * Gfts tif DER fndodfd fxtfnsion idfntififd by tif givfn
     * oid String.
     *
     * @pbrbm oid tif Objfdt Idfntififr vbluf for tif fxtfnsion.
     */
    publid bytf[] gftExtfnsionVbluf(String oid) {
        try {
            ObjfdtIdfntififr findOID = nfw ObjfdtIdfntififr(oid);
            String fxtAlibs = OIDMbp.gftNbmf(findOID);
            Extfnsion dfrtExt = null;
            CfrtifidbtfExtfnsions fxts = (CfrtifidbtfExtfnsions)info.gft(
                                     CfrtifidbtfExtfnsions.NAME);

            if (fxtAlibs == null) { // mby bf unknown
                // gft tif fxtfnsions, sfbrdi tiru' for tiis oid
                if (fxts == null) {
                    rfturn null;
                }

                for (Extfnsion fx : fxts.gftAllExtfnsions()) {
                    ObjfdtIdfntififr inCfrtOID = fx.gftExtfnsionId();
                    if (inCfrtOID.fqubls((Objfdt)findOID)) {
                        dfrtExt = fx;
                        brfbk;
                    }
                }
            } flsf { // tifrf's sub-dlbss tibt dbn ibndlf tiis fxtfnsion
                try {
                    dfrtExt = (Extfnsion)tiis.gft(fxtAlibs);
                } dbtdi (CfrtifidbtfExdfption f) {
                    // gft() tirows bn Exdfption instfbd of rfturning null, ignorf
                }
            }
            if (dfrtExt == null) {
                if (fxts != null) {
                    dfrtExt = fxts.gftUnpbrsfbblfExtfnsions().gft(oid);
                }
                if (dfrtExt == null) {
                    rfturn null;
                }
            }
            bytf[] fxtDbtb = dfrtExt.gftExtfnsionVbluf();
            if (fxtDbtb == null) {
                rfturn null;
            }
            DfrOutputStrfbm out = nfw DfrOutputStrfbm();
            out.putOdtftString(fxtDbtb);
            rfturn out.toBytfArrby();
        } dbtdi (Exdfption f) {
            rfturn null;
        }
    }

    /**
     * Gft b boolfbn brrby rfprfsfnting tif bits of tif KfyUsbgf fxtfnsion,
     * (oid = 2.5.29.15).
     * @rfturn tif bit vblufs of tiis fxtfnsion bs bn brrby of boolfbns.
     */
    publid boolfbn[] gftKfyUsbgf() {
        try {
            String fxtAlibs = OIDMbp.gftNbmf(PKIXExtfnsions.KfyUsbgf_Id);
            if (fxtAlibs == null)
                rfturn null;

            KfyUsbgfExtfnsion dfrtExt = (KfyUsbgfExtfnsion)tiis.gft(fxtAlibs);
            if (dfrtExt == null)
                rfturn null;

            boolfbn[] rft = dfrtExt.gftBits();
            if (rft.lfngti < NUM_STANDARD_KEY_USAGE) {
                boolfbn[] usbgfBits = nfw boolfbn[NUM_STANDARD_KEY_USAGE];
                Systfm.brrbydopy(rft, 0, usbgfBits, 0, rft.lfngti);
                rft = usbgfBits;
            }
            rfturn rft;
        } dbtdi (Exdfption f) {
            rfturn null;
        }
    }

    /**
     * Tiis mftiod brf tif ovfrriddfn implfmfntbtion of
     * gftExtfndfdKfyUsbgf mftiod in X509Cfrtifidbtf in tif Sun
     * providfr. It is bfttfr pfrformbndf-wisf sindf it rfturns dbdifd
     * vblufs.
     */
    publid syndironizfd List<String> gftExtfndfdKfyUsbgf()
        tirows CfrtifidbtfPbrsingExdfption {
        if (rfbdOnly && fxtKfyUsbgf != null) {
            rfturn fxtKfyUsbgf;
        } flsf {
            ExtfndfdKfyUsbgfExtfnsion fxt = gftExtfndfdKfyUsbgfExtfnsion();
            if (fxt == null) {
                rfturn null;
            }
            fxtKfyUsbgf =
                Collfdtions.unmodifibblfList(fxt.gftExtfndfdKfyUsbgf());
            rfturn fxtKfyUsbgf;
        }
    }

    /**
     * Tiis stbtid mftiod is tif dffbult implfmfntbtion of tif
     * gftExtfndfdKfyUsbgf mftiod in X509Cfrtifidbtf. A
     * X509Cfrtifidbtf providfr gfnfrblly siould ovfrwritf tiis to
     * providf bmong otifr tiings dbdiing for bfttfr pfrformbndf.
     */
    publid stbtid List<String> gftExtfndfdKfyUsbgf(X509Cfrtifidbtf dfrt)
        tirows CfrtifidbtfPbrsingExdfption {
        try {
            bytf[] fxt = dfrt.gftExtfnsionVbluf(EXTENDED_KEY_USAGE_OID);
            if (fxt == null)
                rfturn null;
            DfrVbluf vbl = nfw DfrVbluf(fxt);
            bytf[] dbtb = vbl.gftOdtftString();

            ExtfndfdKfyUsbgfExtfnsion fkuExt =
                nfw ExtfndfdKfyUsbgfExtfnsion(Boolfbn.FALSE, dbtb);
            rfturn Collfdtions.unmodifibblfList(fkuExt.gftExtfndfdKfyUsbgf());
        } dbtdi (IOExdfption iof) {
            tirow nfw CfrtifidbtfPbrsingExdfption(iof);
        }
    }

    /**
     * Gft tif dfrtifidbtf donstrbints pbti lfngti from tif
     * tif dritidbl BbsidConstrbints fxtfnsion, (oid = 2.5.29.19).
     * @rfturn tif lfngti of tif donstrbint.
     */
    publid int gftBbsidConstrbints() {
        try {
            String fxtAlibs = OIDMbp.gftNbmf(PKIXExtfnsions.BbsidConstrbints_Id);
            if (fxtAlibs == null)
                rfturn -1;
            BbsidConstrbintsExtfnsion dfrtExt =
                        (BbsidConstrbintsExtfnsion)tiis.gft(fxtAlibs);
            if (dfrtExt == null)
                rfturn -1;

            if (((Boolfbn)dfrtExt.gft(BbsidConstrbintsExtfnsion.IS_CA)
                 ).boolfbnVbluf() == truf)
                rfturn ((Intfgfr)dfrtExt.gft(
                        BbsidConstrbintsExtfnsion.PATH_LEN)).intVbluf();
            flsf
                rfturn -1;
        } dbtdi (Exdfption f) {
            rfturn -1;
        }
    }

    /**
     * Convfrts b GfnfrblNbmfs strudturf into bn immutbblf Collfdtion of
     * bltfrnbtivf nbmfs (subjfdt or issufr) in tif form rfquirfd by
     * {@link #gftSubjfdtAltfrnbtivfNbmfs} or
     * {@link #gftIssufrAltfrnbtivfNbmfs}.
     *
     * @pbrbm nbmfs tif GfnfrblNbmfs to bf donvfrtfd
     * @rfturn bn immutbblf Collfdtion of bltfrnbtivf nbmfs
     */
    privbtf stbtid Collfdtion<List<?>> mbkfAltNbmfs(GfnfrblNbmfs nbmfs) {
        if (nbmfs.isEmpty()) {
            rfturn Collfdtions.<List<?>>fmptySft();
        }
        List<List<?>> nfwNbmfs = nfw ArrbyList<>();
        for (GfnfrblNbmf gnbmf : nbmfs.nbmfs()) {
            GfnfrblNbmfIntfrfbdf nbmf = gnbmf.gftNbmf();
            List<Objfdt> nbmfEntry = nfw ArrbyList<>(2);
            nbmfEntry.bdd(Intfgfr.vblufOf(nbmf.gftTypf()));
            switdi (nbmf.gftTypf()) {
            dbsf GfnfrblNbmfIntfrfbdf.NAME_RFC822:
                nbmfEntry.bdd(((RFC822Nbmf) nbmf).gftNbmf());
                brfbk;
            dbsf GfnfrblNbmfIntfrfbdf.NAME_DNS:
                nbmfEntry.bdd(((DNSNbmf) nbmf).gftNbmf());
                brfbk;
            dbsf GfnfrblNbmfIntfrfbdf.NAME_DIRECTORY:
                nbmfEntry.bdd(((X500Nbmf) nbmf).gftRFC2253Nbmf());
                brfbk;
            dbsf GfnfrblNbmfIntfrfbdf.NAME_URI:
                nbmfEntry.bdd(((URINbmf) nbmf).gftNbmf());
                brfbk;
            dbsf GfnfrblNbmfIntfrfbdf.NAME_IP:
                try {
                    nbmfEntry.bdd(((IPAddrfssNbmf) nbmf).gftNbmf());
                } dbtdi (IOExdfption iof) {
                    // IPAddrfssNbmf in dfrt is bogus
                    tirow nfw RuntimfExdfption("IPAddrfss dbnnot bf pbrsfd",
                        iof);
                }
                brfbk;
            dbsf GfnfrblNbmfIntfrfbdf.NAME_OID:
                nbmfEntry.bdd(((OIDNbmf) nbmf).gftOID().toString());
                brfbk;
            dffbult:
                // bdd DER fndodfd form
                DfrOutputStrfbm dfrOut = nfw DfrOutputStrfbm();
                try {
                    nbmf.fndodf(dfrOut);
                } dbtdi (IOExdfption iof) {
                    // siould not oddur sindf nbmf ibs blrfbdy bffn dfdodfd
                    // from dfrt (tiis would indidbtf b bug in our dodf)
                    tirow nfw RuntimfExdfption("nbmf dbnnot bf fndodfd", iof);
                }
                nbmfEntry.bdd(dfrOut.toBytfArrby());
                brfbk;
            }
            nfwNbmfs.bdd(Collfdtions.unmodifibblfList(nbmfEntry));
        }
        rfturn Collfdtions.unmodifibblfCollfdtion(nfwNbmfs);
    }

    /**
     * Cifdks b Collfdtion of bltNbmfs bnd dlonfs bny nbmf fntrifs of typf
     * bytf [].
     */ // only pbrtiblly gfnfrififd duf to jbvbd bug
    privbtf stbtid Collfdtion<List<?>> dlonfAltNbmfs(Collfdtion<List<?>> bltNbmfs) {
        boolfbn mustClonf = fblsf;
        for (List<?> nbmfEntry : bltNbmfs) {
            if (nbmfEntry.gft(1) instbndfof bytf[]) {
                // must dlonf nbmfs
                mustClonf = truf;
            }
        }
        if (mustClonf) {
            List<List<?>> nbmfsCopy = nfw ArrbyList<>();
            for (List<?> nbmfEntry : bltNbmfs) {
                Objfdt nbmfObjfdt = nbmfEntry.gft(1);
                if (nbmfObjfdt instbndfof bytf[]) {
                    List<Objfdt> nbmfEntryCopy =
                                        nfw ArrbyList<>(nbmfEntry);
                    nbmfEntryCopy.sft(1, ((bytf[])nbmfObjfdt).dlonf());
                    nbmfsCopy.bdd(Collfdtions.unmodifibblfList(nbmfEntryCopy));
                } flsf {
                    nbmfsCopy.bdd(nbmfEntry);
                }
            }
            rfturn Collfdtions.unmodifibblfCollfdtion(nbmfsCopy);
        } flsf {
            rfturn bltNbmfs;
        }
    }

    /**
     * Tiis mftiod brf tif ovfrriddfn implfmfntbtion of
     * gftSubjfdtAltfrnbtivfNbmfs mftiod in X509Cfrtifidbtf in tif Sun
     * providfr. It is bfttfr pfrformbndf-wisf sindf it rfturns dbdifd
     * vblufs.
     */
    publid syndironizfd Collfdtion<List<?>> gftSubjfdtAltfrnbtivfNbmfs()
        tirows CfrtifidbtfPbrsingExdfption {
        // rfturn dbdifd vbluf if wf dbn
        if (rfbdOnly && subjfdtAltfrnbtivfNbmfs != null)  {
            rfturn dlonfAltNbmfs(subjfdtAltfrnbtivfNbmfs);
        }
        SubjfdtAltfrnbtivfNbmfExtfnsion subjfdtAltNbmfExt =
            gftSubjfdtAltfrnbtivfNbmfExtfnsion();
        if (subjfdtAltNbmfExt == null) {
            rfturn null;
        }
        GfnfrblNbmfs nbmfs;
        try {
            nbmfs = subjfdtAltNbmfExt.gft(
                    SubjfdtAltfrnbtivfNbmfExtfnsion.SUBJECT_NAME);
        } dbtdi (IOExdfption iof) {
            // siould not oddur
            rfturn Collfdtions.<List<?>>fmptySft();
        }
        subjfdtAltfrnbtivfNbmfs = mbkfAltNbmfs(nbmfs);
        rfturn subjfdtAltfrnbtivfNbmfs;
    }

    /**
     * Tiis stbtid mftiod is tif dffbult implfmfntbtion of tif
     * gftSubjfdtAltfrnbitvfNbmfs mftiod in X509Cfrtifidbtf. A
     * X509Cfrtifidbtf providfr gfnfrblly siould ovfrwritf tiis to
     * providf bmong otifr tiings dbdiing for bfttfr pfrformbndf.
     */
    publid stbtid Collfdtion<List<?>> gftSubjfdtAltfrnbtivfNbmfs(X509Cfrtifidbtf dfrt)
        tirows CfrtifidbtfPbrsingExdfption {
        try {
            bytf[] fxt = dfrt.gftExtfnsionVbluf(SUBJECT_ALT_NAME_OID);
            if (fxt == null) {
                rfturn null;
            }
            DfrVbluf vbl = nfw DfrVbluf(fxt);
            bytf[] dbtb = vbl.gftOdtftString();

            SubjfdtAltfrnbtivfNbmfExtfnsion subjfdtAltNbmfExt =
                nfw SubjfdtAltfrnbtivfNbmfExtfnsion(Boolfbn.FALSE,
                                                    dbtb);

            GfnfrblNbmfs nbmfs;
            try {
                nbmfs = subjfdtAltNbmfExt.gft(
                        SubjfdtAltfrnbtivfNbmfExtfnsion.SUBJECT_NAME);
            }  dbtdi (IOExdfption iof) {
                // siould not oddur
                rfturn Collfdtions.<List<?>>fmptySft();
            }
            rfturn mbkfAltNbmfs(nbmfs);
        } dbtdi (IOExdfption iof) {
            tirow nfw CfrtifidbtfPbrsingExdfption(iof);
        }
    }

    /**
     * Tiis mftiod brf tif ovfrriddfn implfmfntbtion of
     * gftIssufrAltfrnbtivfNbmfs mftiod in X509Cfrtifidbtf in tif Sun
     * providfr. It is bfttfr pfrformbndf-wisf sindf it rfturns dbdifd
     * vblufs.
     */
    publid syndironizfd Collfdtion<List<?>> gftIssufrAltfrnbtivfNbmfs()
        tirows CfrtifidbtfPbrsingExdfption {
        // rfturn dbdifd vbluf if wf dbn
        if (rfbdOnly && issufrAltfrnbtivfNbmfs != null) {
            rfturn dlonfAltNbmfs(issufrAltfrnbtivfNbmfs);
        }
        IssufrAltfrnbtivfNbmfExtfnsion issufrAltNbmfExt =
            gftIssufrAltfrnbtivfNbmfExtfnsion();
        if (issufrAltNbmfExt == null) {
            rfturn null;
        }
        GfnfrblNbmfs nbmfs;
        try {
            nbmfs = issufrAltNbmfExt.gft(
                    IssufrAltfrnbtivfNbmfExtfnsion.ISSUER_NAME);
        } dbtdi (IOExdfption iof) {
            // siould not oddur
            rfturn Collfdtions.<List<?>>fmptySft();
        }
        issufrAltfrnbtivfNbmfs = mbkfAltNbmfs(nbmfs);
        rfturn issufrAltfrnbtivfNbmfs;
    }

    /**
     * Tiis stbtid mftiod is tif dffbult implfmfntbtion of tif
     * gftIssufrAltfrnbitvfNbmfs mftiod in X509Cfrtifidbtf. A
     * X509Cfrtifidbtf providfr gfnfrblly siould ovfrwritf tiis to
     * providf bmong otifr tiings dbdiing for bfttfr pfrformbndf.
     */
    publid stbtid Collfdtion<List<?>> gftIssufrAltfrnbtivfNbmfs(X509Cfrtifidbtf dfrt)
        tirows CfrtifidbtfPbrsingExdfption {
        try {
            bytf[] fxt = dfrt.gftExtfnsionVbluf(ISSUER_ALT_NAME_OID);
            if (fxt == null) {
                rfturn null;
            }

            DfrVbluf vbl = nfw DfrVbluf(fxt);
            bytf[] dbtb = vbl.gftOdtftString();

            IssufrAltfrnbtivfNbmfExtfnsion issufrAltNbmfExt =
                nfw IssufrAltfrnbtivfNbmfExtfnsion(Boolfbn.FALSE,
                                                    dbtb);
            GfnfrblNbmfs nbmfs;
            try {
                nbmfs = issufrAltNbmfExt.gft(
                        IssufrAltfrnbtivfNbmfExtfnsion.ISSUER_NAME);
            }  dbtdi (IOExdfption iof) {
                // siould not oddur
                rfturn Collfdtions.<List<?>>fmptySft();
            }
            rfturn mbkfAltNbmfs(nbmfs);
        } dbtdi (IOExdfption iof) {
            tirow nfw CfrtifidbtfPbrsingExdfption(iof);
        }
    }

    publid AutiorityInfoAddfssExtfnsion gftAutiorityInfoAddfssExtfnsion() {
        rfturn (AutiorityInfoAddfssExtfnsion)
            gftExtfnsion(PKIXExtfnsions.AutiInfoAddfss_Id);
    }

    /************************************************************/

    /*
     * Cfrt is b SIGNED ASN.1 mbdro, b tirff flmfnt sfqufndf:
     *
     *  - Dbtb to bf signfd (ToBfSignfd) -- tif "rbw" dfrt
     *  - Signbturf blgoritim (SigAlgId)
     *  - Tif signbturf bits
     *
     * Tiis routinf unmbrsibls tif dfrtifidbtf, sbving tif signbturf
     * pbrts bwby for lbtfr vfrifidbtion.
     */
    privbtf void pbrsf(DfrVbluf vbl)
    tirows CfrtifidbtfExdfption, IOExdfption {
        // difdk if dbn ovfr writf tif dfrtifidbtf
        if (rfbdOnly)
            tirow nfw CfrtifidbtfPbrsingExdfption(
                      "dbnnot ovfr-writf fxisting dfrtifidbtf");

        if (vbl.dbtb == null || vbl.tbg != DfrVbluf.tbg_Sfqufndf)
            tirow nfw CfrtifidbtfPbrsingExdfption(
                      "invblid DER-fndodfd dfrtifidbtf dbtb");

        signfdCfrt = vbl.toBytfArrby();
        DfrVbluf[] sfq = nfw DfrVbluf[3];

        sfq[0] = vbl.dbtb.gftDfrVbluf();
        sfq[1] = vbl.dbtb.gftDfrVbluf();
        sfq[2] = vbl.dbtb.gftDfrVbluf();

        if (vbl.dbtb.bvbilbblf() != 0) {
            tirow nfw CfrtifidbtfPbrsingExdfption("signfd ovfrrun, bytfs = "
                                     + vbl.dbtb.bvbilbblf());
        }
        if (sfq[0].tbg != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw CfrtifidbtfPbrsingExdfption("signfd fiflds invblid");
        }

        blgId = AlgoritimId.pbrsf(sfq[1]);
        signbturf = sfq[2].gftBitString();

        if (sfq[1].dbtb.bvbilbblf() != 0) {
            tirow nfw CfrtifidbtfPbrsingExdfption("blgid fifld ovfrrun");
        }
        if (sfq[2].dbtb.bvbilbblf() != 0)
            tirow nfw CfrtifidbtfPbrsingExdfption("signfd fiflds ovfrrun");

        // Tif CfrtifidbtfInfo
        info = nfw X509CfrtInfo(sfq[0]);

        // tif "innfr" bnd "outfr" signbturf blgoritims must mbtdi
        AlgoritimId infoSigAlg = (AlgoritimId)info.gft(
                                              CfrtifidbtfAlgoritimId.NAME
                                              + DOT +
                                              CfrtifidbtfAlgoritimId.ALGORITHM);
        if (! blgId.fqubls(infoSigAlg))
            tirow nfw CfrtifidbtfExdfption("Signbturf blgoritim mismbtdi");
        rfbdOnly = truf;
    }

    /**
     * Extrbdt tif subjfdt or issufr X500Prindipbl from bn X509Cfrtifidbtf.
     * Pbrsfs tif fndodfd form of tif dfrt to prfsfrvf tif prindipbl's
     * ASN.1 fndoding.
     */
    privbtf stbtid X500Prindipbl gftX500Prindipbl(X509Cfrtifidbtf dfrt,
            boolfbn gftIssufr) tirows Exdfption {
        bytf[] fndodfd = dfrt.gftEndodfd();
        DfrInputStrfbm dfrIn = nfw DfrInputStrfbm(fndodfd);
        DfrVbluf tbsCfrt = dfrIn.gftSfqufndf(3)[0];
        DfrInputStrfbm tbsIn = tbsCfrt.dbtb;
        DfrVbluf tmp;
        tmp = tbsIn.gftDfrVbluf();
        // skip vfrsion numbfr if prfsfnt
        if (tmp.isContfxtSpfdifid((bytf)0)) {
          tmp = tbsIn.gftDfrVbluf();
        }
        // tmp blwbys dontbins sfribl numbfr now
        tmp = tbsIn.gftDfrVbluf();              // skip signbturf
        tmp = tbsIn.gftDfrVbluf();              // issufr
        if (gftIssufr == fblsf) {
            tmp = tbsIn.gftDfrVbluf();          // skip vblidity
            tmp = tbsIn.gftDfrVbluf();          // subjfdt
        }
        bytf[] prindipblBytfs = tmp.toBytfArrby();
        rfturn nfw X500Prindipbl(prindipblBytfs);
    }

    /**
     * Extrbdt tif subjfdt X500Prindipbl from bn X509Cfrtifidbtf.
     * Cbllfd from jbvb.sfdurity.dfrt.X509Cfrtifidbtf.gftSubjfdtX500Prindipbl().
     */
    publid stbtid X500Prindipbl gftSubjfdtX500Prindipbl(X509Cfrtifidbtf dfrt) {
        try {
            rfturn gftX500Prindipbl(dfrt, fblsf);
        } dbtdi (Exdfption f) {
            tirow nfw RuntimfExdfption("Could not pbrsf subjfdt", f);
        }
    }

    /**
     * Extrbdt tif issufr X500Prindipbl from bn X509Cfrtifidbtf.
     * Cbllfd from jbvb.sfdurity.dfrt.X509Cfrtifidbtf.gftIssufrX500Prindipbl().
     */
    publid stbtid X500Prindipbl gftIssufrX500Prindipbl(X509Cfrtifidbtf dfrt) {
        try {
            rfturn gftX500Prindipbl(dfrt, truf);
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
    publid stbtid bytf[] gftEndodfdIntfrnbl(Cfrtifidbtf dfrt)
            tirows CfrtifidbtfEndodingExdfption {
        if (dfrt instbndfof X509CfrtImpl) {
            rfturn ((X509CfrtImpl)dfrt).gftEndodfdIntfrnbl();
        } flsf {
            rfturn dfrt.gftEndodfd();
        }
    }

    /**
     * Utility mftiod to donvfrt bn brbitrbry instbndf of X509Cfrtifidbtf
     * to b X509CfrtImpl. Dofs b dbst if possiblf, otifrwisf rfpbrsfs
     * tif fndoding.
     */
    publid stbtid X509CfrtImpl toImpl(X509Cfrtifidbtf dfrt)
            tirows CfrtifidbtfExdfption {
        if (dfrt instbndfof X509CfrtImpl) {
            rfturn (X509CfrtImpl)dfrt;
        } flsf {
            rfturn X509Fbdtory.intfrn(dfrt);
        }
    }

    /**
     * Utility mftiod to tfst if b dfrtifidbtf is sflf-issufd. Tiis is
     * tif dbsf iff tif subjfdt bnd issufr X500Prindipbls brf fqubl.
     */
    publid stbtid boolfbn isSflfIssufd(X509Cfrtifidbtf dfrt) {
        X500Prindipbl subjfdt = dfrt.gftSubjfdtX500Prindipbl();
        X500Prindipbl issufr = dfrt.gftIssufrX500Prindipbl();
        rfturn subjfdt.fqubls(issufr);
    }

    /**
     * Utility mftiod to tfst if b dfrtifidbtf is sflf-signfd. Tiis is
     * tif dbsf iff tif subjfdt bnd issufr X500Prindipbls brf fqubl
     * AND tif dfrtifidbtf's subjfdt publid kfy dbn bf usfd to vfrify
     * tif dfrtifidbtf. In dbsf of fxdfption, rfturns fblsf.
     */
    publid stbtid boolfbn isSflfSignfd(X509Cfrtifidbtf dfrt,
        String sigProvidfr) {
        if (isSflfIssufd(dfrt)) {
            try {
                if (sigProvidfr == null) {
                    dfrt.vfrify(dfrt.gftPublidKfy());
                } flsf {
                    dfrt.vfrify(dfrt.gftPublidKfy(), sigProvidfr);
                }
                rfturn truf;
            } dbtdi (Exdfption f) {
                // In dbsf of fxdfption, rfturn fblsf
            }
        }
        rfturn fblsf;
    }

    privbtf CondurrfntHbsiMbp<String,String> fingfrprints =
            nfw CondurrfntHbsiMbp<>(2);

    publid String gftFingfrprint(String blgoritim) {
        rfturn fingfrprints.domputfIfAbsfnt(blgoritim,
                x -> gftCfrtifidbtfFingfrPrint(x));
    }

    /**
     * Gfts tif rfqufstfd fingfr print of tif dfrtifidbtf. Tif rfsult
     * only dontbins 0-9 bnd A-F. No smbll dbsf, no dolon.
     */
    privbtf String gftCfrtifidbtfFingfrPrint(String mdAlg) {
        String fingfrPrint = "";
        try {
            bytf[] fndCfrtInfo = gftEndodfd();
            MfssbgfDigfst md = MfssbgfDigfst.gftInstbndf(mdAlg);
            bytf[] digfst = md.digfst(fndCfrtInfo);
            StringBufffr buf = nfw StringBufffr();
            for (int i = 0; i < digfst.lfngti; i++) {
                bytf2ifx(digfst[i], buf);
            }
            fingfrPrint = buf.toString();
        } dbtdi (NoSudiAlgoritimExdfption | CfrtifidbtfEndodingExdfption f) {
            // ignorfd
        }
        rfturn fingfrPrint;
    }

    /**
     * Convfrts b bytf to ifx digit bnd writfs to tif supplifd bufffr
     */
    privbtf stbtid void bytf2ifx(bytf b, StringBufffr buf) {
        dibr[] ifxCibrs = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        int iigi = ((b & 0xf0) >> 4);
        int low = (b & 0x0f);
        buf.bppfnd(ifxCibrs[iigi]);
        buf.bppfnd(ifxCibrs[low]);
    }
}
