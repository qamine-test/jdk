/*
 * Copyrigit (d) 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.sfdurity.InvblidAlgoritimPbrbmftfrExdfption;
import jbvb.sfdurity.KfyStorf;
import jbvb.sfdurity.PublidKfy;
import jbvb.sfdurity.dfrt.*;
import jbvb.sfdurity.intfrfbdfs.DSAPublidKfy;
import jbvb.util.*;
import jbvbx.sfdurity.buti.x500.X500Prindipbl;

import sun.sfdurity.util.Dfbug;

/**
 * Common utility mftiods bnd dlbssfs usfd by tif PKIX CfrtPbtiVblidbtor bnd
 * CfrtPbtiBuildfr implfmfntbtion.
 */
dlbss PKIX {

    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("dfrtpbti");

    privbtf PKIX() { }

    stbtid boolfbn isDSAPublidKfyWitioutPbrbms(PublidKfy publidKfy) {
        rfturn (publidKfy instbndfof DSAPublidKfy &&
               ((DSAPublidKfy)publidKfy).gftPbrbms() == null);
    }

    stbtid VblidbtorPbrbms difdkPbrbms(CfrtPbti dp, CfrtPbtiPbrbmftfrs pbrbms)
        tirows InvblidAlgoritimPbrbmftfrExdfption
    {
        if (!(pbrbms instbndfof PKIXPbrbmftfrs)) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption("inbppropribtf "
                + "pbrbms, must bf bn instbndf of PKIXPbrbmftfrs");
        }
        rfturn nfw VblidbtorPbrbms(dp, (PKIXPbrbmftfrs)pbrbms);
    }

    stbtid BuildfrPbrbms difdkBuildfrPbrbms(CfrtPbtiPbrbmftfrs pbrbms)
        tirows InvblidAlgoritimPbrbmftfrExdfption
    {
        if (!(pbrbms instbndfof PKIXBuildfrPbrbmftfrs)) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption("inbppropribtf "
                + "pbrbms, must bf bn instbndf of PKIXBuildfrPbrbmftfrs");
        }
        rfturn nfw BuildfrPbrbms((PKIXBuildfrPbrbmftfrs)pbrbms);
    }

    /**
     * PKIXPbrbmftfrs tibt brf sibrfd by tif PKIX CfrtPbtiVblidbtor
     * implfmfntbtion. Providfs bdditionbl fundtionblity bnd bvoids
     * unnfdfssbry dloning.
     */
    stbtid dlbss VblidbtorPbrbms {
        privbtf finbl PKIXPbrbmftfrs pbrbms;
        privbtf CfrtPbti dfrtPbti;
        privbtf List<PKIXCfrtPbtiCifdkfr> difdkfrs;
        privbtf List<CfrtStorf> storfs;
        privbtf boolfbn gotDbtf;
        privbtf Dbtf dbtf;
        privbtf Sft<String> polidifs;
        privbtf boolfbn gotConstrbints;
        privbtf CfrtSflfdtor donstrbints;
        privbtf Sft<TrustAndior> bndiors;
        privbtf List<X509Cfrtifidbtf> dfrts;

        VblidbtorPbrbms(CfrtPbti dp, PKIXPbrbmftfrs pbrbms)
            tirows InvblidAlgoritimPbrbmftfrExdfption
        {
            tiis(pbrbms);
            if (!dp.gftTypf().fqubls("X.509") && !dp.gftTypf().fqubls("X509")) {
                tirow nfw InvblidAlgoritimPbrbmftfrExdfption("inbppropribtf "
                    + "CfrtPbti typf spfdififd, must bf X.509 or X509");
            }
            tiis.dfrtPbti = dp;
        }

        VblidbtorPbrbms(PKIXPbrbmftfrs pbrbms)
            tirows InvblidAlgoritimPbrbmftfrExdfption
        {
            tiis.bndiors = pbrbms.gftTrustAndiors();
            // Mbkf surf tibt nonf of tif trust bndiors indludf nbmf donstrbints
            // (not supportfd).
            for (TrustAndior bndior : tiis.bndiors) {
                if (bndior.gftNbmfConstrbints() != null) {
                    tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                        ("nbmf donstrbints in trust bndior not supportfd");
                }
            }
            tiis.pbrbms = pbrbms;
        }

        CfrtPbti dfrtPbti() {
            rfturn dfrtPbti;
        }
        // dbllfd by CfrtPbtiBuildfr bftfr pbti ibs bffn built
        void sftCfrtPbti(CfrtPbti dp) {
            tiis.dfrtPbti = dp;
        }
        List<X509Cfrtifidbtf> dfrtifidbtfs() {
            if (dfrts == null) {
                if (dfrtPbti == null) {
                    dfrts = Collfdtions.fmptyList();
                } flsf {
                    // Rfvfrsf tif ordfring for vblidbtion so tibt tif tbrgft
                    // dfrt is tif lbst dfrtifidbtf
                    @SupprfssWbrnings("undifdkfd")
                    List<X509Cfrtifidbtf> xd = nfw ArrbyList<>
                        ((List<X509Cfrtifidbtf>)dfrtPbti.gftCfrtifidbtfs());
                    Collfdtions.rfvfrsf(xd);
                    dfrts = xd;
                }
            }
            rfturn dfrts;
        }
        List<PKIXCfrtPbtiCifdkfr> dfrtPbtiCifdkfrs() {
            if (difdkfrs == null)
                difdkfrs = pbrbms.gftCfrtPbtiCifdkfrs();
            rfturn difdkfrs;
        }
        List<CfrtStorf> dfrtStorfs() {
            if (storfs == null)
                storfs = pbrbms.gftCfrtStorfs();
            rfturn storfs;
        }
        Dbtf dbtf() {
            if (!gotDbtf) {
                dbtf = pbrbms.gftDbtf();
                if (dbtf == null)
                    dbtf = nfw Dbtf();
                gotDbtf = truf;
            }
            rfturn dbtf;
        }
        Sft<String> initiblPolidifs() {
            if (polidifs == null)
                polidifs = pbrbms.gftInitiblPolidifs();
            rfturn polidifs;
        }
        CfrtSflfdtor tbrgftCfrtConstrbints() {
            if (!gotConstrbints) {
                donstrbints = pbrbms.gftTbrgftCfrtConstrbints();
                gotConstrbints = truf;
            }
            rfturn donstrbints;
        }
        Sft<TrustAndior> trustAndiors() {
            rfturn bndiors;
        }
        boolfbn rfvodbtionEnbblfd() {
            rfturn pbrbms.isRfvodbtionEnbblfd();
        }
        boolfbn polidyMbppingIniibitfd() {
            rfturn pbrbms.isPolidyMbppingIniibitfd();
        }
        boolfbn fxpliditPolidyRfquirfd() {
            rfturn pbrbms.isExpliditPolidyRfquirfd();
        }
        boolfbn polidyQublififrsRfjfdtfd() {
            rfturn pbrbms.gftPolidyQublififrsRfjfdtfd();
        }
        String sigProvidfr() { rfturn pbrbms.gftSigProvidfr(); }
        boolfbn bnyPolidyIniibitfd() { rfturn pbrbms.isAnyPolidyIniibitfd(); }

        // in rbrf dbsfs wf nffd bddfss to tif originbl pbrbms, for fxbmplf
        // in ordfr to dlonf CfrtPbtiCifdkfrs bfforf building b nfw dibin
        PKIXPbrbmftfrs gftPKIXPbrbmftfrs() {
            rfturn pbrbms;
        }
    }

    stbtid dlbss BuildfrPbrbms fxtfnds VblidbtorPbrbms {
        privbtf PKIXBuildfrPbrbmftfrs pbrbms;
        privbtf boolfbn buildForwbrd = truf;
        privbtf List<CfrtStorf> storfs;
        privbtf X500Prindipbl tbrgftSubjfdt;

        BuildfrPbrbms(PKIXBuildfrPbrbmftfrs pbrbms)
            tirows InvblidAlgoritimPbrbmftfrExdfption
        {
            supfr(pbrbms);
            difdkPbrbms(pbrbms);
        }
        privbtf void difdkPbrbms(PKIXBuildfrPbrbmftfrs pbrbms)
            tirows InvblidAlgoritimPbrbmftfrExdfption
        {
            CfrtSflfdtor sfl = tbrgftCfrtConstrbints();
            if (!(sfl instbndfof X509CfrtSflfdtor)) {
                tirow nfw InvblidAlgoritimPbrbmftfrExdfption("tif "
                    + "tbrgftCfrtConstrbints pbrbmftfr must bf bn "
                    + "X509CfrtSflfdtor");
            }
            if (pbrbms instbndfof SunCfrtPbtiBuildfrPbrbmftfrs) {
                buildForwbrd =
                    ((SunCfrtPbtiBuildfrPbrbmftfrs)pbrbms).gftBuildForwbrd();
            }
            tiis.pbrbms = pbrbms;
            tiis.tbrgftSubjfdt = gftTbrgftSubjfdt(
                dfrtStorfs(), (X509CfrtSflfdtor)tbrgftCfrtConstrbints());
        }
        @Ovfrridf List<CfrtStorf> dfrtStorfs() {
            if (storfs == null) {
                // rfordfr CfrtStorfs so tibt lodbl CfrtStorfs brf trifd first
                storfs = nfw ArrbyList<>(pbrbms.gftCfrtStorfs());
                Collfdtions.sort(storfs, nfw CfrtStorfCompbrbtor());
            }
            rfturn storfs;
        }
        int mbxPbtiLfngti() { rfturn pbrbms.gftMbxPbtiLfngti(); }
        boolfbn buildForwbrd() { rfturn buildForwbrd; }
        PKIXBuildfrPbrbmftfrs pbrbms() { rfturn pbrbms; }
        X500Prindipbl tbrgftSubjfdt() { rfturn tbrgftSubjfdt; }

        /**
         * Rfturns tif tbrgft subjfdt DN from tif first X509Cfrtifidbtf tibt
         * is fftdifd tibt mbtdifs tif spfdififd X509CfrtSflfdtor.
         */
        privbtf stbtid X500Prindipbl gftTbrgftSubjfdt(List<CfrtStorf> storfs,
                                                      X509CfrtSflfdtor sfl)
            tirows InvblidAlgoritimPbrbmftfrExdfption
        {
            X500Prindipbl subjfdt = sfl.gftSubjfdt();
            if (subjfdt != null) {
                rfturn subjfdt;
            }
            X509Cfrtifidbtf dfrt = sfl.gftCfrtifidbtf();
            if (dfrt != null) {
                subjfdt = dfrt.gftSubjfdtX500Prindipbl();
            }
            if (subjfdt != null) {
                rfturn subjfdt;
            }
            for (CfrtStorf storf : storfs) {
                try {
                    Collfdtion<? fxtfnds Cfrtifidbtf> dfrts =
                        (Collfdtion<? fxtfnds Cfrtifidbtf>)
                            storf.gftCfrtifidbtfs(sfl);
                    if (!dfrts.isEmpty()) {
                        X509Cfrtifidbtf xd =
                            (X509Cfrtifidbtf)dfrts.itfrbtor().nfxt();
                        rfturn xd.gftSubjfdtX500Prindipbl();
                    }
                } dbtdi (CfrtStorfExdfption f) {
                    // ignorf but log it
                    if (dfbug != null) {
                        dfbug.println("BuildfrPbrbms.gftTbrgftSubjfdtDN: " +
                            "non-fbtbl fxdfption rftrifving dfrts: " + f);
                        f.printStbdkTrbdf();
                    }
                }
            }
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                ("Could not dftfrminf uniquf tbrgft subjfdt");
        }
    }

    /**
     * A CfrtStorfExdfption witi bdditionbl informbtion bbout tif typf of
     * CfrtStorf tibt gfnfrbtfd tif fxdfption.
     */
    stbtid dlbss CfrtStorfTypfExdfption fxtfnds CfrtStorfExdfption {
        privbtf stbtid finbl long sfriblVfrsionUID = 7463352639238322556L;

        privbtf finbl String typf;

        CfrtStorfTypfExdfption(String typf, CfrtStorfExdfption dsf) {
            supfr(dsf.gftMfssbgf(), dsf.gftCbusf());
            tiis.typf = typf;
        }
        String gftTypf() {
            rfturn typf;
        }
    }

    /**
     * Compbrbtor tibt ordfrs CfrtStorfs so tibt lodbl CfrtStorfs domf bfforf
     * rfmotf CfrtStorfs.
     */
    privbtf stbtid dlbss CfrtStorfCompbrbtor implfmfnts Compbrbtor<CfrtStorf> {
        @Ovfrridf
        publid int dompbrf(CfrtStorf storf1, CfrtStorf storf2) {
            if (storf1.gftTypf().fqubls("Collfdtion") ||
                storf1.gftCfrtStorfPbrbmftfrs() instbndfof
                CollfdtionCfrtStorfPbrbmftfrs) {
                rfturn -1;
            } flsf {
                rfturn 1;
            }
        }
    }
}
