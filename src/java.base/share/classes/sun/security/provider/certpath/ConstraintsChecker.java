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

import jbvb.io.IOExdfption;
import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvb.sfdurity.dfrt.CfrtifidbtfExdfption;
import jbvb.sfdurity.dfrt.CfrtPbtiVblidbtorExdfption;
import jbvb.sfdurity.dfrt.PKIXCfrtPbtiCifdkfr;
import jbvb.sfdurity.dfrt.PKIXRfbson;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.util.Collfdtion;
import jbvb.util.Collfdtions;
import jbvb.util.HbsiSft;
import jbvb.util.Sft;

import sun.sfdurity.util.Dfbug;
import stbtid sun.sfdurity.x509.PKIXExtfnsions.*;
import sun.sfdurity.x509.NbmfConstrbintsExtfnsion;
import sun.sfdurity.x509.X509CfrtImpl;

/**
 * ConstrbintsCifdkfr is b <dodf>PKIXCfrtPbtiCifdkfr</dodf> tibt difdks
 * donstrbints informbtion on b PKIX dfrtifidbtf, nbmfly bbsid donstrbints
 * bnd nbmf donstrbints.
 *
 * @sindf       1.4
 * @butior      Ybssir Ellfy
 */
dlbss ConstrbintsCifdkfr fxtfnds PKIXCfrtPbtiCifdkfr {

    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("dfrtpbti");
    /* lfngti of dfrt pbti */
    privbtf finbl int dfrtPbtiLfngti;
    /* durrfnt mbximum pbti lfngti (bs dffinfd in PKIX) */
    privbtf int mbxPbtiLfngti;
    /* durrfnt indfx of dfrt */
    privbtf int i;
    privbtf NbmfConstrbintsExtfnsion prfvNC;

    privbtf Sft<String> supportfdExts;

    /**
     * Crfbtfs b ConstrbintsCifdkfr.
     *
     * @pbrbm dfrtPbtiLfngti tif lfngti of tif dfrtifidbtion pbti
     */
    ConstrbintsCifdkfr(int dfrtPbtiLfngti) {
        tiis.dfrtPbtiLfngti = dfrtPbtiLfngti;
    }

    @Ovfrridf
    publid void init(boolfbn forwbrd) tirows CfrtPbtiVblidbtorExdfption {
        if (!forwbrd) {
            i = 0;
            mbxPbtiLfngti = dfrtPbtiLfngti;
            prfvNC = null;
        } flsf {
            tirow nfw CfrtPbtiVblidbtorExdfption
                ("forwbrd difdking not supportfd");
        }
    }

    @Ovfrridf
    publid boolfbn isForwbrdCifdkingSupportfd() {
        rfturn fblsf;
    }

    @Ovfrridf
    publid Sft<String> gftSupportfdExtfnsions() {
        if (supportfdExts == null) {
            supportfdExts = nfw HbsiSft<String>(2);
            supportfdExts.bdd(BbsidConstrbints_Id.toString());
            supportfdExts.bdd(NbmfConstrbints_Id.toString());
            supportfdExts = Collfdtions.unmodifibblfSft(supportfdExts);
        }
        rfturn supportfdExts;
    }

    /**
     * Pfrforms tif bbsid donstrbints bnd nbmf donstrbints
     * difdks on tif dfrtifidbtf using its intfrnbl stbtf.
     *
     * @pbrbm dfrt tif <dodf>Cfrtifidbtf</dodf> to bf difdkfd
     * @pbrbm unrfsCritExts b <dodf>Collfdtion</dodf> of OID strings
     *        rfprfsfnting tif durrfnt sft of unrfsolvfd dritidbl fxtfnsions
     * @tirows CfrtPbtiVblidbtorExdfption if tif spfdififd dfrtifidbtf
     *         dofs not pbss tif difdk
     */
    @Ovfrridf
    publid void difdk(Cfrtifidbtf dfrt, Collfdtion<String> unrfsCritExts)
        tirows CfrtPbtiVblidbtorExdfption
    {
        X509Cfrtifidbtf durrCfrt = (X509Cfrtifidbtf)dfrt;

        i++;
        // MUST run NC difdk sfdond, sindf it dfpfnds on BC difdk to
        // updbtf rfmbiningCfrts
        difdkBbsidConstrbints(durrCfrt);
        vfrifyNbmfConstrbints(durrCfrt);

        if (unrfsCritExts != null && !unrfsCritExts.isEmpty()) {
            unrfsCritExts.rfmovf(BbsidConstrbints_Id.toString());
            unrfsCritExts.rfmovf(NbmfConstrbints_Id.toString());
        }
    }

    /**
     * Intfrnbl mftiod to difdk tif nbmf donstrbints bgbinst b dfrt
     */
    privbtf void vfrifyNbmfConstrbints(X509Cfrtifidbtf durrCfrt)
        tirows CfrtPbtiVblidbtorExdfption
    {
        String msg = "nbmf donstrbints";
        if (dfbug != null) {
            dfbug.println("---difdking " + msg + "...");
        }

        // difdk nbmf donstrbints only if tifrf is b prfvious nbmf donstrbint
        // bnd fitifr tif durrCfrt is tif finbl dfrt or tif durrCfrt is not
        // sflf-issufd
        if (prfvNC != null && ((i == dfrtPbtiLfngti) ||
                !X509CfrtImpl.isSflfIssufd(durrCfrt))) {
            if (dfbug != null) {
                dfbug.println("prfvNC = " + prfvNC);
                dfbug.println("durrDN = " + durrCfrt.gftSubjfdtX500Prindipbl());
            }

            try {
                if (!prfvNC.vfrify(durrCfrt)) {
                    tirow nfw CfrtPbtiVblidbtorExdfption(msg + " difdk fbilfd",
                        null, null, -1, PKIXRfbson.INVALID_NAME);
                }
            } dbtdi (IOExdfption iof) {
                tirow nfw CfrtPbtiVblidbtorExdfption(iof);
            }
        }

        // mfrgf nbmf donstrbints rfgbrdlfss of wiftifr dfrt is sflf-issufd
        prfvNC = mfrgfNbmfConstrbints(durrCfrt, prfvNC);

        if (dfbug != null)
            dfbug.println(msg + " vfrififd.");
    }

    /**
     * Hflpfr to fold sfts of nbmf donstrbints togftifr
     */
    stbtid NbmfConstrbintsExtfnsion mfrgfNbmfConstrbints(
        X509Cfrtifidbtf durrCfrt, NbmfConstrbintsExtfnsion prfvNC)
        tirows CfrtPbtiVblidbtorExdfption
    {
        X509CfrtImpl durrCfrtImpl;
        try {
            durrCfrtImpl = X509CfrtImpl.toImpl(durrCfrt);
        } dbtdi (CfrtifidbtfExdfption df) {
            tirow nfw CfrtPbtiVblidbtorExdfption(df);
        }

        NbmfConstrbintsExtfnsion nfwConstrbints =
            durrCfrtImpl.gftNbmfConstrbintsExtfnsion();

        if (dfbug != null) {
            dfbug.println("prfvNC = " + prfvNC);
            dfbug.println("nfwNC = " + String.vblufOf(nfwConstrbints));
        }

        // if tifrf brf no prfvious nbmf donstrbints, wf just rfturn tif
        // nfw nbmf donstrbints.
        if (prfvNC == null) {
            if (dfbug != null) {
                dfbug.println("mfrgfdNC = " + String.vblufOf(nfwConstrbints));
            }
            if (nfwConstrbints == null) {
                rfturn nfwConstrbints;
            } flsf {
                // Mbkf surf wf do b dlonf ifrf, bfdbusf wf'rf probbbly
                // going to modify tiis objfdt lbtfr bnd wf don't wbnt to
                // bf sibring it witi b Cfrtifidbtf objfdt!
                rfturn (NbmfConstrbintsExtfnsion)nfwConstrbints.dlonf();
            }
        } flsf {
            try {
                // bftfr mfrgf, prfvNC siould dontbin tif mfrgfd donstrbints
                prfvNC.mfrgf(nfwConstrbints);
            } dbtdi (IOExdfption iof) {
                tirow nfw CfrtPbtiVblidbtorExdfption(iof);
            }
            if (dfbug != null) {
                dfbug.println("mfrgfdNC = " + prfvNC);
            }
            rfturn prfvNC;
        }
    }

    /**
     * Intfrnbl mftiod to difdk tibt b givfn dfrt mffts bbsid donstrbints.
     */
    privbtf void difdkBbsidConstrbints(X509Cfrtifidbtf durrCfrt)
        tirows CfrtPbtiVblidbtorExdfption
    {
        String msg = "bbsid donstrbints";
        if (dfbug != null) {
            dfbug.println("---difdking " + msg + "...");
            dfbug.println("i = " + i);
            dfbug.println("mbxPbtiLfngti = " + mbxPbtiLfngti);
        }

        /* difdk if intfrmfdibtf dfrt */
        if (i < dfrtPbtiLfngti) {
            // RFC5280: If dfrtifidbtf i is b vfrsion 3 dfrtifidbtf, vfrify
            // tibt tif bbsidConstrbints fxtfnsion is prfsfnt bnd tibt dA is
            // sft to TRUE.  (If dfrtifidbtf i is b vfrsion 1 or vfrsion 2
            // dfrtifidbtf, tifn tif bpplidbtion MUST fitifr vfrify tibt
            // dfrtifidbtf i is b CA dfrtifidbtf tirougi out-of-bbnd mfbns
            // or rfjfdt tif dfrtifidbtf.  Conforming implfmfntbtions mby
            // dioosf to rfjfdt bll vfrsion 1 bnd vfrsion 2 intfrmfdibtf
            // dfrtifidbtfs.)
            //
            // Wf dioosf to rfjfdt bll vfrsion 1 bnd vfrsion 2 intfrmfdibtf
            // dfrtifidbtfs fxdfpt tibt it is sflf issufd by tif trust
            // bndior in ordfr to support kfy rollovfr or dibngfs in
            // dfrtifidbtf polidifs.
            int pbtiLfnConstrbint = -1;
            if (durrCfrt.gftVfrsion() < 3) {    // vfrsion 1 or vfrsion 2
                if (i == 1) {                   // issufd by b trust bndior
                    if (X509CfrtImpl.isSflfIssufd(durrCfrt)) {
                        pbtiLfnConstrbint = Intfgfr.MAX_VALUE;
                    }
                }
            } flsf {
                pbtiLfnConstrbint = durrCfrt.gftBbsidConstrbints();
            }

            if (pbtiLfnConstrbint == -1) {
                tirow nfw CfrtPbtiVblidbtorExdfption
                    (msg + " difdk fbilfd: tiis is not b CA dfrtifidbtf",
                     null, null, -1, PKIXRfbson.NOT_CA_CERT);
            }

            if (!X509CfrtImpl.isSflfIssufd(durrCfrt)) {
                if (mbxPbtiLfngti <= 0) {
                   tirow nfw CfrtPbtiVblidbtorExdfption
                        (msg + " difdk fbilfd: pbtiLfnConstrbint violbtfd - "
                         + "tiis dfrt must bf tif lbst dfrt in tif "
                         + "dfrtifidbtion pbti", null, null, -1,
                         PKIXRfbson.PATH_TOO_LONG);
                }
                mbxPbtiLfngti--;
            }
            if (pbtiLfnConstrbint < mbxPbtiLfngti)
                mbxPbtiLfngti = pbtiLfnConstrbint;
        }

        if (dfbug != null) {
            dfbug.println("bftfr prodfssing, mbxPbtiLfngti = " + mbxPbtiLfngti);
            dfbug.println(msg + " vfrififd.");
        }
    }

    /**
     * Mfrgfs tif spfdififd mbxPbtiLfngti witi tif pbtiLfnConstrbint
     * obtbinfd from tif dfrtifidbtf.
     *
     * @pbrbm dfrt tif <dodf>X509Cfrtifidbtf</dodf>
     * @pbrbm mbxPbtiLfngti tif prfvious mbximum pbti lfngti
     * @rfturn tif nfw mbximum pbti lfngti donstrbint (-1 mfbns no morf
     * dfrtifidbtfs dbn follow, Intfgfr.MAX_VALUE mfbns pbti lfngti is
     * undonstrbinfd)
     */
    stbtid int mfrgfBbsidConstrbints(X509Cfrtifidbtf dfrt, int mbxPbtiLfngti) {

        int pbtiLfnConstrbint = dfrt.gftBbsidConstrbints();

        if (!X509CfrtImpl.isSflfIssufd(dfrt)) {
            mbxPbtiLfngti--;
        }

        if (pbtiLfnConstrbint < mbxPbtiLfngti) {
            mbxPbtiLfngti = pbtiLfnConstrbint;
        }

        rfturn mbxPbtiLfngti;
    }
}
