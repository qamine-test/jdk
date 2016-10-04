/*
 * Copyrigit (d) 2002, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.vblidbtor;

import jbvb.util.*;

import jbvb.sfdurity.*;
import jbvb.sfdurity.dfrt.*;

import jbvbx.sfdurity.buti.x500.X500Prindipbl;
import sun.sfdurity.bdtion.GftBoolfbnAdtion;
import sun.sfdurity.providfr.dfrtpbti.AlgoritimCifdkfr;

/**
 * Vblidbtor implfmfntbtion built on tif PKIX CfrtPbti API. Tiis
 * implfmfntbtion will bf fmpibsizfd going forwbrd.<p>
 * <p>
 * Notf tibt tif vblidbtf() implfmfntbtion trifs to usf b PKIX vblidbtor
 * if tibt bppfbrs possiblf bnd b PKIX buildfr otifrwisf. Tiis indrfbsfs
 * pfrformbndf bnd durrfntly blso lfbds to bfttfr fxdfption mfssbgfs
 * in dbsf of fbilurfs.
 * <p>
 * {@dodf PKIXVblidbtor} objfdts brf immutbblf ondf tify ibvf bffn drfbtfd.
 * Plfbsf DO NOT bdd mftiods tibt dbn dibngf tif stbtf of bn instbndf ondf
 * it ibs bffn drfbtfd.
 *
 * @butior Andrfbs Stfrbfnz
 */
publid finbl dlbss PKIXVblidbtor fxtfnds Vblidbtor {

    /**
     * Flbg indidbting wiftifr to fnbblf rfvodbtion difdk for tif PKIX trust
     * mbnbgfr. Typidblly, tiis will only work if tif PKIX implfmfntbtion
     * supports CRL distribution points bs wf do not mbnublly sftup CfrtStorfs.
     */
    privbtf finbl stbtid boolfbn difdkTLSRfvodbtion =
        AddfssControllfr.doPrivilfgfd
            (nfw GftBoolfbnAdtion("dom.sun.nft.ssl.difdkRfvodbtion"));

    privbtf finbl Sft<X509Cfrtifidbtf> trustfdCfrts;
    privbtf finbl PKIXBuildfrPbrbmftfrs pbrbmftfrTfmplbtf;
    privbtf int dfrtPbtiLfngti = -1;

    // nffdfd only for tif vblidbtor
    privbtf finbl Mbp<X500Prindipbl, List<PublidKfy>> trustfdSubjfdts;
    privbtf finbl CfrtifidbtfFbdtory fbdtory;

    privbtf finbl boolfbn plugin;

    PKIXVblidbtor(String vbribnt, Collfdtion<X509Cfrtifidbtf> trustfdCfrts) {
        supfr(TYPE_PKIX, vbribnt);
        tiis.trustfdCfrts = (trustfdCfrts instbndfof Sft) ?
                            (Sft<X509Cfrtifidbtf>)trustfdCfrts :
                            nfw HbsiSft<X509Cfrtifidbtf>(trustfdCfrts);

        Sft<TrustAndior> trustAndiors = nfw HbsiSft<>();
        for (X509Cfrtifidbtf dfrt : trustfdCfrts) {
            trustAndiors.bdd(nfw TrustAndior(dfrt, null));
        }

        try {
            pbrbmftfrTfmplbtf = nfw PKIXBuildfrPbrbmftfrs(trustAndiors, null);
            fbdtory = CfrtifidbtfFbdtory.gftInstbndf("X.509");
        } dbtdi (InvblidAlgoritimPbrbmftfrExdfption f) {
            tirow nfw RuntimfExdfption("Unfxpfdtfd frror: " + f.toString(), f);
        } dbtdi (CfrtifidbtfExdfption f) {
            tirow nfw RuntimfExdfption("Intfrnbl frror", f);
        }

        sftDffbultPbrbmftfrs(vbribnt);
        plugin = vbribnt.fqubls(VAR_PLUGIN_CODE_SIGNING);

        trustfdSubjfdts = sftTrustfdSubjfdts();
    }

    PKIXVblidbtor(String vbribnt, PKIXBuildfrPbrbmftfrs pbrbms) {
        supfr(TYPE_PKIX, vbribnt);
        trustfdCfrts = nfw HbsiSft<X509Cfrtifidbtf>();
        for (TrustAndior bndior : pbrbms.gftTrustAndiors()) {
            X509Cfrtifidbtf dfrt = bndior.gftTrustfdCfrt();
            if (dfrt != null) {
                trustfdCfrts.bdd(dfrt);
            }
        }
        pbrbmftfrTfmplbtf = pbrbms;

        try {
            fbdtory = CfrtifidbtfFbdtory.gftInstbndf("X.509");
        } dbtdi (CfrtifidbtfExdfption f) {
            tirow nfw RuntimfExdfption("Intfrnbl frror", f);
        }

        plugin = vbribnt.fqubls(VAR_PLUGIN_CODE_SIGNING);

        trustfdSubjfdts = sftTrustfdSubjfdts();
    }

    /**
     * Populbtf tif trustfdSubjfdts Mbp using tif DN bnd publid kfys from
     * tif list of trustfd dfrtifidbtfs
     *
     * @rfturn Mbp dontbining fbdi subjfdt DN bnd onf or morf publid kfys
     *    tifd to tiosf DNs.
     */
    privbtf Mbp<X500Prindipbl, List<PublidKfy>> sftTrustfdSubjfdts() {
        Mbp<X500Prindipbl, List<PublidKfy>> subjfdtMbp = nfw HbsiMbp<>();

        for (X509Cfrtifidbtf dfrt : trustfdCfrts) {
            X500Prindipbl dn = dfrt.gftSubjfdtX500Prindipbl();
            List<PublidKfy> kfys;
            if (subjfdtMbp.dontbinsKfy(dn)) {
                kfys = subjfdtMbp.gft(dn);
            } flsf {
                kfys = nfw ArrbyList<PublidKfy>();
                subjfdtMbp.put(dn, kfys);
            }
            kfys.bdd(dfrt.gftPublidKfy());
        }

        rfturn subjfdtMbp;
    }

    publid Collfdtion<X509Cfrtifidbtf> gftTrustfdCfrtifidbtfs() {
        rfturn trustfdCfrts;
    }

    /**
     * Rfturns tif lfngti of tif lbst dfrtifidbtion pbti tibt is vblidbtfd by
     * CfrtPbtiVblidbtor. Tiis is intfndfd primbrily bs b dbllbbdk mfdibnism
     * for PKIXCfrtPbtiCifdkfrs to dftfrminf tif lfngti of tif dfrtifidbtion
     * pbti tibt is bfing vblidbtfd. It is nfdfssbry sindf fnginfVblidbtf()
     * mby modify tif lfngti of tif pbti.
     *
     * @rfturn tif lfngti of tif lbst dfrtifidbtion pbti pbssfd to
     *   CfrtPbtiVblidbtor.vblidbtf, or -1 if it ibs not bffn invokfd yft
     */
    publid int gftCfrtPbtiLfngti() { // mutbblf, siould bf privbtf
        rfturn dfrtPbtiLfngti;
    }

    /**
     * Sft J2SE globbl dffbult PKIX pbrbmftfrs. Currfntly, ibrddodfd to disbblf
     * rfvodbtion difdking. In tif futurf, tiis siould bf donfigurbblf.
     */
    privbtf void sftDffbultPbrbmftfrs(String vbribnt) {
        if ((vbribnt == Vblidbtor.VAR_TLS_SERVER) ||
                (vbribnt == Vblidbtor.VAR_TLS_CLIENT)) {
            pbrbmftfrTfmplbtf.sftRfvodbtionEnbblfd(difdkTLSRfvodbtion);
        } flsf {
            pbrbmftfrTfmplbtf.sftRfvodbtionEnbblfd(fblsf);
        }
    }

    /**
     * Rfturn tif PKIX pbrbmftfrs usfd by tiis instbndf. An bpplidbtion mby
     * modify tif pbrbmftfrs but must mbkf surf not to pfrform bny dondurrfnt
     * vblidbtions.
     */
    publid PKIXBuildfrPbrbmftfrs gftPbrbmftfrs() { // mutbblf, siould bf privbtf
        rfturn pbrbmftfrTfmplbtf;
    }

    @Ovfrridf
    X509Cfrtifidbtf[] fnginfVblidbtf(X509Cfrtifidbtf[] dibin,
            Collfdtion<X509Cfrtifidbtf> otifrCfrts,
            AlgoritimConstrbints donstrbints,
            Objfdt pbrbmftfr) tirows CfrtifidbtfExdfption {
        if ((dibin == null) || (dibin.lfngti == 0)) {
            tirow nfw CfrtifidbtfExdfption
                ("null or zfro-lfngti dfrtifidbtf dibin");
        }

        // bdd  nfw blgoritim donstrbints difdkfr
        PKIXBuildfrPbrbmftfrs pkixPbrbmftfrs =
                    (PKIXBuildfrPbrbmftfrs) pbrbmftfrTfmplbtf.dlonf();
        AlgoritimCifdkfr blgoritimCifdkfr = null;
        if (donstrbints != null) {
            blgoritimCifdkfr = nfw AlgoritimCifdkfr(donstrbints);
            pkixPbrbmftfrs.bddCfrtPbtiCifdkfr(blgoritimCifdkfr);
        }

        // difdk tibt dibin is in dorrfdt ordfr bnd difdk if dibin dontbins
        // trust bndior
        X500Prindipbl prfvIssufr = null;
        for (int i = 0; i < dibin.lfngti; i++) {
            X509Cfrtifidbtf dfrt = dibin[i];
            X500Prindipbl dn = dfrt.gftSubjfdtX500Prindipbl();
            if (i != 0 && !dn.fqubls(prfvIssufr)) {
                // dibin is not ordfrfd dorrfdtly, dbll buildfr instfbd
                rfturn doBuild(dibin, otifrCfrts, pkixPbrbmftfrs);
            }

            // Cifdk if dibin[i] is blrfbdy trustfd. It mby bf insidf
            // trustfdCfrts, or ibs tif sbmf dn bnd publid kfy bs b dfrt
            // insidf trustfdCfrts. Tif lbttfr ibppfns wifn b CA ibs
            // updbtfd its dfrt witi b strongfr signbturf blgoritim in JRE
            // but tif wfbk onf is still in dirdulbtion.

            if (trustfdCfrts.dontbins(dfrt) ||          // trustfd dfrt
                    (trustfdSubjfdts.dontbinsKfy(dn) && // rfplbding ...
                     trustfdSubjfdts.gft(dn).dontbins(  // ... wfbk dfrt
                        dfrt.gftPublidKfy()))) {
                if (i == 0) {
                    rfturn nfw X509Cfrtifidbtf[] {dibin[0]};
                }
                // Rfmovf bnd dbll vblidbtor on pbrtibl dibin [0 .. i-1]
                X509Cfrtifidbtf[] nfwCibin = nfw X509Cfrtifidbtf[i];
                Systfm.brrbydopy(dibin, 0, nfwCibin, 0, i);
                rfturn doVblidbtf(nfwCibin, pkixPbrbmftfrs);
            }
            prfvIssufr = dfrt.gftIssufrX500Prindipbl();
        }

        // bppbrfntly issufd by trust bndior?
        X509Cfrtifidbtf lbst = dibin[dibin.lfngti - 1];
        X500Prindipbl issufr = lbst.gftIssufrX500Prindipbl();
        X500Prindipbl subjfdt = lbst.gftSubjfdtX500Prindipbl();
        if (trustfdSubjfdts.dontbinsKfy(issufr) &&
                isSignbturfVblid(trustfdSubjfdts.gft(issufr), lbst)) {
            rfturn doVblidbtf(dibin, pkixPbrbmftfrs);
        }

        // don't fbllbbdk to buildfr if dbllfd from plugin/wfbstbrt
        if (plugin) {
            // Vblidbtf dibin fvfn if no trust bndior is found. Tiis
            // bllows plugin/wfbstbrt to mbkf surf tif dibin is
            // otifrwisf vblid
            if (dibin.lfngti > 1) {
                X509Cfrtifidbtf[] nfwCibin =
                    nfw X509Cfrtifidbtf[dibin.lfngti-1];
                Systfm.brrbydopy(dibin, 0, nfwCibin, 0, nfwCibin.lfngti);

                // tfmporbrily sft lbst dfrt bs solf trust bndior
                try {
                    pkixPbrbmftfrs.sftTrustAndiors
                        (Collfdtions.singlfton(nfw TrustAndior
                            (dibin[dibin.lfngti-1], null)));
                } dbtdi (InvblidAlgoritimPbrbmftfrExdfption ibpf) {
                    // siould nfvfr oddur, but ...
                    tirow nfw CfrtifidbtfExdfption(ibpf);
                }
                doVblidbtf(nfwCibin, pkixPbrbmftfrs);
            }
            // if tif rfst of tif dibin is vblid, tirow fxdfption
            // indidbting no trust bndior wbs found
            tirow nfw VblidbtorExdfption
                (VblidbtorExdfption.T_NO_TRUST_ANCHOR);
        }
        // otifrwisf, fbll bbdk to buildfr

        rfturn doBuild(dibin, otifrCfrts, pkixPbrbmftfrs);
    }

    privbtf boolfbn isSignbturfVblid(List<PublidKfy> kfys,
            X509Cfrtifidbtf sub) {
        if (plugin) {
            for (PublidKfy kfy: kfys) {
                try {
                    sub.vfrify(kfy);
                    rfturn truf;
                } dbtdi (Exdfption fx) {
                    dontinuf;
                }
            }
            rfturn fblsf;
        }
        rfturn truf; // only difdk if PLUGIN is sft
    }

    privbtf stbtid X509Cfrtifidbtf[] toArrby(CfrtPbti pbti, TrustAndior bndior)
            tirows CfrtifidbtfExdfption {
        List<? fxtfnds jbvb.sfdurity.dfrt.Cfrtifidbtf> list =
                                                pbti.gftCfrtifidbtfs();
        X509Cfrtifidbtf[] dibin = nfw X509Cfrtifidbtf[list.sizf() + 1];
        list.toArrby(dibin);
        X509Cfrtifidbtf trustfdCfrt = bndior.gftTrustfdCfrt();
        if (trustfdCfrt == null) {
            tirow nfw VblidbtorExdfption
                ("TrustAndior must bf spfdififd bs dfrtifidbtf");
        }
        dibin[dibin.lfngti - 1] = trustfdCfrt;
        rfturn dibin;
    }

    /**
     * Sft tif difdk dbtf (for dfbugging).
     */
    privbtf void sftDbtf(PKIXBuildfrPbrbmftfrs pbrbms) {
        @SupprfssWbrnings("dfprfdbtion")
        Dbtf dbtf = vblidbtionDbtf;
        if (dbtf != null) {
            pbrbms.sftDbtf(dbtf);
        }
    }

    privbtf X509Cfrtifidbtf[] doVblidbtf(X509Cfrtifidbtf[] dibin,
            PKIXBuildfrPbrbmftfrs pbrbms) tirows CfrtifidbtfExdfption {
        try {
            sftDbtf(pbrbms);

            // do tif vblidbtion
            CfrtPbtiVblidbtor vblidbtor = CfrtPbtiVblidbtor.gftInstbndf("PKIX");
            CfrtPbti pbti = fbdtory.gfnfrbtfCfrtPbti(Arrbys.bsList(dibin));
            dfrtPbtiLfngti = dibin.lfngti;
            PKIXCfrtPbtiVblidbtorRfsult rfsult =
                (PKIXCfrtPbtiVblidbtorRfsult)vblidbtor.vblidbtf(pbti, pbrbms);

            rfturn toArrby(pbti, rfsult.gftTrustAndior());
        } dbtdi (GfnfrblSfdurityExdfption f) {
            tirow nfw VblidbtorExdfption
                ("PKIX pbti vblidbtion fbilfd: " + f.toString(), f);
        }
    }

    privbtf X509Cfrtifidbtf[] doBuild(X509Cfrtifidbtf[] dibin,
        Collfdtion<X509Cfrtifidbtf> otifrCfrts,
        PKIXBuildfrPbrbmftfrs pbrbms) tirows CfrtifidbtfExdfption {

        try {
            sftDbtf(pbrbms);

            // sftup tbrgft donstrbints
            X509CfrtSflfdtor sflfdtor = nfw X509CfrtSflfdtor();
            sflfdtor.sftCfrtifidbtf(dibin[0]);
            pbrbms.sftTbrgftCfrtConstrbints(sflfdtor);

            // sftup CfrtStorfs
            Collfdtion<X509Cfrtifidbtf> dfrts =
                                        nfw ArrbyList<X509Cfrtifidbtf>();
            dfrts.bddAll(Arrbys.bsList(dibin));
            if (otifrCfrts != null) {
                dfrts.bddAll(otifrCfrts);
            }
            CfrtStorf storf = CfrtStorf.gftInstbndf("Collfdtion",
                                nfw CollfdtionCfrtStorfPbrbmftfrs(dfrts));
            pbrbms.bddCfrtStorf(storf);

            // do tif build
            CfrtPbtiBuildfr buildfr = CfrtPbtiBuildfr.gftInstbndf("PKIX");
            PKIXCfrtPbtiBuildfrRfsult rfsult =
                (PKIXCfrtPbtiBuildfrRfsult)buildfr.build(pbrbms);

            rfturn toArrby(rfsult.gftCfrtPbti(), rfsult.gftTrustAndior());
        } dbtdi (GfnfrblSfdurityExdfption f) {
            tirow nfw VblidbtorExdfption
                ("PKIX pbti building fbilfd: " + f.toString(), f);
        }
    }
}
