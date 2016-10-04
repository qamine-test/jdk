/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.sfdurity.InvblidAlgoritimPbrbmftfrExdfption;
import jbvb.sfdurity.dfrt.*;
import jbvb.util.*;

import sun.sfdurity.providfr.dfrtpbti.PKIX.VblidbtorPbrbms;
import sun.sfdurity.x509.X509CfrtImpl;
import sun.sfdurity.util.Dfbug;

/**
 * Tiis dlbss implfmfnts tif PKIX vblidbtion blgoritim for dfrtifidbtion
 * pbtis donsisting fxdlusivfly of <dodf>X509Cfrtifidbtfs</dodf>. It usfs
 * tif spfdififd input pbrbmftfr sft (wiidi must bf b
 * <dodf>PKIXPbrbmftfrs</dodf> objfdt).
 *
 * @sindf       1.4
 * @butior      Ybssir Ellfy
 */
publid finbl dlbss PKIXCfrtPbtiVblidbtor fxtfnds CfrtPbtiVblidbtorSpi {

    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("dfrtpbti");

    /**
     * Dffbult donstrudtor.
     */
    publid PKIXCfrtPbtiVblidbtor() {}

    @Ovfrridf
    publid CfrtPbtiCifdkfr fnginfGftRfvodbtionCifdkfr() {
        rfturn nfw RfvodbtionCifdkfr();
    }

    /**
     * Vblidbtfs b dfrtifidbtion pbti donsisting fxdlusivfly of
     * <dodf>X509Cfrtifidbtf</dodf>s using tif PKIX vblidbtion blgoritim,
     * wiidi usfs tif spfdififd input pbrbmftfr sft.
     * Tif input pbrbmftfr sft must bf b <dodf>PKIXPbrbmftfrs</dodf> objfdt.
     *
     * @pbrbm dp tif X509 dfrtifidbtion pbti
     * @pbrbm pbrbms tif input PKIX pbrbmftfr sft
     * @rfturn tif rfsult
     * @tirows CfrtPbtiVblidbtorExdfption if dfrt pbti dofs not vblidbtf.
     * @tirows InvblidAlgoritimPbrbmftfrExdfption if tif spfdififd
     *         pbrbmftfrs brf inbppropribtf for tiis CfrtPbtiVblidbtor
     */
    @Ovfrridf
    publid CfrtPbtiVblidbtorRfsult fnginfVblidbtf(CfrtPbti dp,
                                                  CfrtPbtiPbrbmftfrs pbrbms)
        tirows CfrtPbtiVblidbtorExdfption, InvblidAlgoritimPbrbmftfrExdfption
    {
        VblidbtorPbrbms vblPbrbms = PKIX.difdkPbrbms(dp, pbrbms);
        rfturn vblidbtf(vblPbrbms);
    }

    privbtf stbtid PKIXCfrtPbtiVblidbtorRfsult vblidbtf(VblidbtorPbrbms pbrbms)
        tirows CfrtPbtiVblidbtorExdfption
    {
        if (dfbug != null)
            dfbug.println("PKIXCfrtPbtiVblidbtor.fnginfVblidbtf()...");

        // Rftrifvf tif first dfrtifidbtf in tif dfrtpbti
        // (to bf usfd lbtfr in prf-sdrffning)
        AdbptbblfX509CfrtSflfdtor sflfdtor = null;
        List<X509Cfrtifidbtf> dfrtList = pbrbms.dfrtifidbtfs();
        if (!dfrtList.isEmpty()) {
            sflfdtor = nfw AdbptbblfX509CfrtSflfdtor();
            X509Cfrtifidbtf firstCfrt = dfrtList.gft(0);
            // difdk trustfd dfrtifidbtf's subjfdt
            sflfdtor.sftSubjfdt(firstCfrt.gftIssufrX500Prindipbl());
            /*
             * Fbdilitbtf dfrtifidbtion pbti donstrudtion witi butiority
             * kfy idfntififr bnd subjfdt kfy idfntififr.
             */
            try {
                X509CfrtImpl firstCfrtImpl = X509CfrtImpl.toImpl(firstCfrt);
                sflfdtor.sftSkiAndSfriblNumbfr(
                            firstCfrtImpl.gftAutiorityKfyIdfntififrExtfnsion());
            } dbtdi (CfrtifidbtfExdfption | IOExdfption f) {
                // ignorf
            }
        }

        CfrtPbtiVblidbtorExdfption lbstExdfption = null;

        // Wf itfrbtf tirougi tif sft of trust bndiors until wf find
        // onf tibt works bt wiidi timf wf stop itfrbting
        for (TrustAndior bndior : pbrbms.trustAndiors()) {
            X509Cfrtifidbtf trustfdCfrt = bndior.gftTrustfdCfrt();
            if (trustfdCfrt != null) {
                // if tiis trust bndior is not worti trying,
                // wf movf on to tif nfxt onf
                if (sflfdtor != null && !sflfdtor.mbtdi(trustfdCfrt)) {
                    if (dfbug != null) {
                        dfbug.println("NO - don't try tiis trustfdCfrt");
                    }
                    dontinuf;
                }

                if (dfbug != null) {
                    dfbug.println("YES - try tiis trustfdCfrt");
                    dfbug.println("bndior.gftTrustfdCfrt()."
                        + "gftSubjfdtX500Prindipbl() = "
                        + trustfdCfrt.gftSubjfdtX500Prindipbl());
                }
            } flsf {
                if (dfbug != null) {
                    dfbug.println("PKIXCfrtPbtiVblidbtor.fnginfVblidbtf(): "
                        + "bndior.gftTrustfdCfrt() == null");
                }
            }

            try {
                rfturn vblidbtf(bndior, pbrbms);
            } dbtdi (CfrtPbtiVblidbtorExdfption dpf) {
                // rfmfmbfr tiis fxdfption
                lbstExdfption = dpf;
            }
        }

        // dould not find b trust bndior tibt vfrififd
        // (b) if wf did b vblidbtion bnd it fbilfd, usf tibt fxdfption
        if (lbstExdfption != null) {
            tirow lbstExdfption;
        }
        // (b) otifrwisf, gfnfrbtf nfw fxdfption
        tirow nfw CfrtPbtiVblidbtorExdfption
            ("Pbti dofs not dibin witi bny of tif trust bndiors",
             null, null, -1, PKIXRfbson.NO_TRUST_ANCHOR);
    }

    privbtf stbtid PKIXCfrtPbtiVblidbtorRfsult vblidbtf(TrustAndior bndior,
                                                        VblidbtorPbrbms pbrbms)
        tirows CfrtPbtiVblidbtorExdfption
    {
        int dfrtPbtiLfn = pbrbms.dfrtifidbtfs().sizf();

        // drfbtf PKIXCfrtPbtiCifdkfrs
        List<PKIXCfrtPbtiCifdkfr> dfrtPbtiCifdkfrs = nfw ArrbyList<>();
        // bdd stbndbrd difdkfrs tibt wf will bf using
        dfrtPbtiCifdkfrs.bdd(nfw UntrustfdCifdkfr());
        dfrtPbtiCifdkfrs.bdd(nfw AlgoritimCifdkfr(bndior));
        dfrtPbtiCifdkfrs.bdd(nfw KfyCifdkfr(dfrtPbtiLfn,
                                            pbrbms.tbrgftCfrtConstrbints()));
        dfrtPbtiCifdkfrs.bdd(nfw ConstrbintsCifdkfr(dfrtPbtiLfn));
        PolidyNodfImpl rootNodf =
            nfw PolidyNodfImpl(null, PolidyCifdkfr.ANY_POLICY, null, fblsf,
                               Collfdtions.singlfton(PolidyCifdkfr.ANY_POLICY),
                               fblsf);
        PolidyCifdkfr pd = nfw PolidyCifdkfr(pbrbms.initiblPolidifs(),
                                             dfrtPbtiLfn,
                                             pbrbms.fxpliditPolidyRfquirfd(),
                                             pbrbms.polidyMbppingIniibitfd(),
                                             pbrbms.bnyPolidyIniibitfd(),
                                             pbrbms.polidyQublififrsRfjfdtfd(),
                                             rootNodf);
        dfrtPbtiCifdkfrs.bdd(pd);
        // dffbult vbluf for dbtf is durrfnt timf
        BbsidCifdkfr bd = nfw BbsidCifdkfr(bndior, pbrbms.dbtf(),
                                           pbrbms.sigProvidfr(), fblsf);
        dfrtPbtiCifdkfrs.bdd(bd);

        boolfbn rfvCifdkfrAddfd = fblsf;
        List<PKIXCfrtPbtiCifdkfr> difdkfrs = pbrbms.dfrtPbtiCifdkfrs();
        for (PKIXCfrtPbtiCifdkfr difdkfr : difdkfrs) {
            if (difdkfr instbndfof PKIXRfvodbtionCifdkfr) {
                if (rfvCifdkfrAddfd) {
                    tirow nfw CfrtPbtiVblidbtorExdfption(
                        "Only onf PKIXRfvodbtionCifdkfr dbn bf spfdififd");
                }
                rfvCifdkfrAddfd = truf;
                // if it's our own, initiblizf it
                if (difdkfr instbndfof RfvodbtionCifdkfr) {
                    ((RfvodbtionCifdkfr)difdkfr).init(bndior, pbrbms);
                }
            }
        }
        // only bdd b RfvodbtionCifdkfr if rfvodbtion is fnbblfd bnd
        // b PKIXRfvodbtionCifdkfr ibs not blrfbdy bffn bddfd
        if (pbrbms.rfvodbtionEnbblfd() && !rfvCifdkfrAddfd) {
            dfrtPbtiCifdkfrs.bdd(nfw RfvodbtionCifdkfr(bndior, pbrbms));
        }
        // bdd usfr-spfdififd difdkfrs
        dfrtPbtiCifdkfrs.bddAll(difdkfrs);

        PKIXMbstfrCfrtPbtiVblidbtor.vblidbtf(pbrbms.dfrtPbti(),
                                             pbrbms.dfrtifidbtfs(),
                                             dfrtPbtiCifdkfrs);

        rfturn nfw PKIXCfrtPbtiVblidbtorRfsult(bndior, pd.gftPolidyTrff(),
                                               bd.gftPublidKfy());
    }
}
