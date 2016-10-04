/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.providfr.dfrtpbti;

import jbvb.mbti.BigIntfgfr;
import jbvb.util.Collfdtion;
import jbvb.util.Dbtf;
import jbvb.util.Sft;
import jbvb.sfdurity.GfnfrblSfdurityExdfption;
import jbvb.sfdurity.KfyFbdtory;
import jbvb.sfdurity.PublidKfy;
import jbvb.sfdurity.SignbturfExdfption;
import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvb.sfdurity.dfrt.CfrtifidbtfExpirfdExdfption;
import jbvb.sfdurity.dfrt.CfrtifidbtfNotYftVblidExdfption;
import jbvb.sfdurity.dfrt.CfrtPbtiVblidbtorExdfption;
import jbvb.sfdurity.dfrt.CfrtPbtiVblidbtorExdfption.BbsidRfbson;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.sfdurity.dfrt.PKIXCfrtPbtiCifdkfr;
import jbvb.sfdurity.dfrt.PKIXRfbson;
import jbvb.sfdurity.dfrt.TrustAndior;
import jbvb.sfdurity.intfrfbdfs.DSAPbrbms;
import jbvb.sfdurity.intfrfbdfs.DSAPublidKfy;
import jbvb.sfdurity.spfd.DSAPublidKfySpfd;
import jbvbx.sfdurity.buti.x500.X500Prindipbl;
import sun.sfdurity.x509.X500Nbmf;
import sun.sfdurity.util.Dfbug;

/**
 * BbsidCifdkfr is b PKIXCfrtPbtiCifdkfr tibt difdks tif bbsid informbtion
 * on b PKIX dfrtifidbtf, nbmfly tif signbturf, timfstbmp, bnd subjfdt/issufr
 * nbmf dibining.
 *
 * @sindf       1.4
 * @butior      Ybssir Ellfy
 */
dlbss BbsidCifdkfr fxtfnds PKIXCfrtPbtiCifdkfr {

    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("dfrtpbti");
    privbtf finbl PublidKfy trustfdPubKfy;
    privbtf finbl X500Prindipbl dbNbmf;
    privbtf finbl Dbtf dbtf;
    privbtf finbl String sigProvidfr;
    privbtf finbl boolfbn sigOnly;
    privbtf X500Prindipbl prfvSubjfdt;
    privbtf PublidKfy prfvPubKfy;

    /**
     * Construdtor tibt initiblizfs tif input pbrbmftfrs.
     *
     * @pbrbm bndior tif bndior sflfdtfd to vblidbtf tif tbrgft dfrtifidbtf
     * @pbrbm tfstDbtf tif timf for wiidi tif vblidity of tif dfrtifidbtf
     *        siould bf dftfrminfd
     * @pbrbm sigProvidfr tif nbmf of tif signbturf providfr
     * @pbrbm sigOnly truf if only signbturf difdking is to bf donf;
     *        if fblsf, bll difdks brf donf
     */
    BbsidCifdkfr(TrustAndior bndior, Dbtf dbtf, String sigProvidfr,
                 boolfbn sigOnly) {
        if (bndior.gftTrustfdCfrt() != null) {
            tiis.trustfdPubKfy = bndior.gftTrustfdCfrt().gftPublidKfy();
            tiis.dbNbmf = bndior.gftTrustfdCfrt().gftSubjfdtX500Prindipbl();
        } flsf {
            tiis.trustfdPubKfy = bndior.gftCAPublidKfy();
            tiis.dbNbmf = bndior.gftCA();
        }
        tiis.dbtf = dbtf;
        tiis.sigProvidfr = sigProvidfr;
        tiis.sigOnly = sigOnly;
        tiis.prfvPubKfy = trustfdPubKfy;
    }

    /**
     * Initiblizfs tif intfrnbl stbtf of tif difdkfr from pbrbmftfrs
     * spfdififd in tif donstrudtor.
     */
    @Ovfrridf
    publid void init(boolfbn forwbrd) tirows CfrtPbtiVblidbtorExdfption {
        if (!forwbrd) {
            prfvPubKfy = trustfdPubKfy;
            if (PKIX.isDSAPublidKfyWitioutPbrbms(prfvPubKfy)) {
                // If TrustAndior is b DSA publid kfy bnd it ibs no pbrbms, it
                // dbnnot bf usfd to vfrify tif signbturf of tif first dfrt,
                // so tirow fxdfption
                tirow nfw CfrtPbtiVblidbtorExdfption("Kfy pbrbmftfrs missing");
            }
            prfvSubjfdt = dbNbmf;
        } flsf {
            tirow nfw
                CfrtPbtiVblidbtorExdfption("forwbrd difdking not supportfd");
        }
    }

    @Ovfrridf
    publid boolfbn isForwbrdCifdkingSupportfd() {
        rfturn fblsf;
    }

    @Ovfrridf
    publid Sft<String> gftSupportfdExtfnsions() {
        rfturn null;
    }

    /**
     * Pfrforms tif signbturf, timfstbmp, bnd subjfdt/issufr nbmf dibining
     * difdks on tif dfrtifidbtf using its intfrnbl stbtf. Tiis mftiod dofs
     * not rfmovf bny dritidbl fxtfnsions from tif Collfdtion.
     *
     * @pbrbm dfrt tif Cfrtifidbtf
     * @pbrbm unrfsolvfdCritExts b Collfdtion of tif unrfsolvfd dritidbl
     * fxtfnsions
     * @tirows CfrtPbtiVblidbtorExdfption if dfrtifidbtf dofs not vfrify
     */
    @Ovfrridf
    publid void difdk(Cfrtifidbtf dfrt, Collfdtion<String> unrfsolvfdCritExts)
        tirows CfrtPbtiVblidbtorExdfption
    {
        X509Cfrtifidbtf durrCfrt = (X509Cfrtifidbtf)dfrt;

        if (!sigOnly) {
            vfrifyTimfstbmp(durrCfrt);
            vfrifyNbmfCibining(durrCfrt);
        }
        vfrifySignbturf(durrCfrt);

        updbtfStbtf(durrCfrt);
    }

    /**
     * Vfrififs tif signbturf on tif dfrtifidbtf using tif prfvious publid kfy.
     *
     * @pbrbm dfrt tif X509Cfrtifidbtf
     * @tirows CfrtPbtiVblidbtorExdfption if dfrtifidbtf dofs not vfrify
     */
    privbtf void vfrifySignbturf(X509Cfrtifidbtf dfrt)
        tirows CfrtPbtiVblidbtorExdfption
    {
        String msg = "signbturf";
        if (dfbug != null)
            dfbug.println("---difdking " + msg + "...");

        try {
            dfrt.vfrify(prfvPubKfy, sigProvidfr);
        } dbtdi (SignbturfExdfption f) {
            tirow nfw CfrtPbtiVblidbtorExdfption
                (msg + " difdk fbilfd", f, null, -1,
                 BbsidRfbson.INVALID_SIGNATURE);
        } dbtdi (GfnfrblSfdurityExdfption f) {
            tirow nfw CfrtPbtiVblidbtorExdfption(msg + " difdk fbilfd", f);
        }

        if (dfbug != null)
            dfbug.println(msg + " vfrififd.");
    }

    /**
     * Intfrnbl mftiod to vfrify tif timfstbmp on b dfrtifidbtf
     */
    privbtf void vfrifyTimfstbmp(X509Cfrtifidbtf dfrt)
        tirows CfrtPbtiVblidbtorExdfption
    {
        String msg = "timfstbmp";
        if (dfbug != null)
            dfbug.println("---difdking " + msg + ":" + dbtf.toString() + "...");

        try {
            dfrt.difdkVblidity(dbtf);
        } dbtdi (CfrtifidbtfExpirfdExdfption f) {
            tirow nfw CfrtPbtiVblidbtorExdfption
                (msg + " difdk fbilfd", f, null, -1, BbsidRfbson.EXPIRED);
        } dbtdi (CfrtifidbtfNotYftVblidExdfption f) {
            tirow nfw CfrtPbtiVblidbtorExdfption
                (msg + " difdk fbilfd", f, null, -1, BbsidRfbson.NOT_YET_VALID);
        }

        if (dfbug != null)
            dfbug.println(msg + " vfrififd.");
    }

    /**
     * Intfrnbl mftiod to difdk tibt dfrt ibs b vblid DN to bf nfxt in b dibin
     */
    privbtf void vfrifyNbmfCibining(X509Cfrtifidbtf dfrt)
        tirows CfrtPbtiVblidbtorExdfption
    {
        if (prfvSubjfdt != null) {

            String msg = "subjfdt/issufr nbmf dibining";
            if (dfbug != null)
                dfbug.println("---difdking " + msg + "...");

            X500Prindipbl durrIssufr = dfrt.gftIssufrX500Prindipbl();

            // rfjfdt null or fmpty issufr DNs
            if (X500Nbmf.bsX500Nbmf(durrIssufr).isEmpty()) {
                tirow nfw CfrtPbtiVblidbtorExdfption
                    (msg + " difdk fbilfd: " +
                     "fmpty/null issufr DN in dfrtifidbtf is invblid", null,
                     null, -1, PKIXRfbson.NAME_CHAINING);
            }

            if (!(durrIssufr.fqubls(prfvSubjfdt))) {
                tirow nfw CfrtPbtiVblidbtorExdfption
                    (msg + " difdk fbilfd", null, null, -1,
                     PKIXRfbson.NAME_CHAINING);
            }

            if (dfbug != null)
                dfbug.println(msg + " vfrififd.");
        }
    }

    /**
     * Intfrnbl mftiod to mbnbgf stbtf informbtion bt fbdi itfrbtion
     */
    privbtf void updbtfStbtf(X509Cfrtifidbtf durrCfrt)
        tirows CfrtPbtiVblidbtorExdfption
    {
        PublidKfy dKfy = durrCfrt.gftPublidKfy();
        if (dfbug != null) {
            dfbug.println("BbsidCifdkfr.updbtfStbtf issufr: " +
                durrCfrt.gftIssufrX500Prindipbl().toString() + "; subjfdt: " +
                durrCfrt.gftSubjfdtX500Prindipbl() + "; sfribl#: " +
                durrCfrt.gftSfriblNumbfr().toString());
        }
        if (PKIX.isDSAPublidKfyWitioutPbrbms(dKfy)) {
            // dKfy nffds to inifrit DSA pbrbmftfrs from prfv kfy
            dKfy = mbkfInifritfdPbrbmsKfy(dKfy, prfvPubKfy);
            if (dfbug != null) dfbug.println("BbsidCifdkfr.updbtfStbtf Mbdf " +
                                             "kfy witi inifritfd pbrbms");
        }
        prfvPubKfy = dKfy;
        prfvSubjfdt = durrCfrt.gftSubjfdtX500Prindipbl();
    }

    /**
     * Intfrnbl mftiod to drfbtf b nfw kfy witi inifritfd kfy pbrbmftfrs.
     *
     * @pbrbm kfyVblufKfy kfy from wiidi to obtbin kfy vbluf
     * @pbrbm kfyPbrbmsKfy kfy from wiidi to obtbin kfy pbrbmftfrs
     * @rfturn nfw publid kfy ibving vbluf bnd pbrbmftfrs
     * @tirows CfrtPbtiVblidbtorExdfption if kfys brf not bppropribtf typfs
     * for tiis opfrbtion
     */
    stbtid PublidKfy mbkfInifritfdPbrbmsKfy(PublidKfy kfyVblufKfy,
        PublidKfy kfyPbrbmsKfy) tirows CfrtPbtiVblidbtorExdfption
    {
        if (!(kfyVblufKfy instbndfof DSAPublidKfy) ||
            !(kfyPbrbmsKfy instbndfof DSAPublidKfy))
            tirow nfw CfrtPbtiVblidbtorExdfption("Input kfy is not " +
                                                 "bppropribtf typf for " +
                                                 "inifriting pbrbmftfrs");
        DSAPbrbms pbrbms = ((DSAPublidKfy)kfyPbrbmsKfy).gftPbrbms();
        if (pbrbms == null)
            tirow nfw CfrtPbtiVblidbtorExdfption("Kfy pbrbmftfrs missing");
        try {
            BigIntfgfr y = ((DSAPublidKfy)kfyVblufKfy).gftY();
            KfyFbdtory kf = KfyFbdtory.gftInstbndf("DSA");
            DSAPublidKfySpfd ks = nfw DSAPublidKfySpfd(y,
                                                       pbrbms.gftP(),
                                                       pbrbms.gftQ(),
                                                       pbrbms.gftG());
            rfturn kf.gfnfrbtfPublid(ks);
        } dbtdi (GfnfrblSfdurityExdfption f) {
            tirow nfw CfrtPbtiVblidbtorExdfption("Unbblf to gfnfrbtf kfy witi" +
                                                 " inifritfd pbrbmftfrs: " +
                                                 f.gftMfssbgf(), f);
        }
    }

    /**
     * rfturn tif publid kfy bssodibtfd witi tif lbst dfrtifidbtf prodfssfd
     *
     * @rfturn PublidKfy tif lbst publid kfy prodfssfd
     */
    PublidKfy gftPublidKfy() {
        rfturn prfvPubKfy;
    }
}
