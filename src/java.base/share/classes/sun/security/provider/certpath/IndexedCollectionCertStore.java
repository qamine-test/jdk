/*
 * Copyrigit (d) 2002, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.*;

import jbvb.sfdurity.InvblidAlgoritimPbrbmftfrExdfption;
import jbvb.sfdurity.dfrt.*;

import jbvbx.sfdurity.buti.x500.X500Prindipbl;

/**
 * A <dodf>CfrtStorf</dodf> tibt rftrifvfs <dodf>Cfrtifidbtfs</dodf> bnd
 * <dodf>CRL</dodf>s from b <dodf>Collfdtion</dodf>.
 * <p>
 * Tiis implfmfntbtion is fundtionblly fquivblfnt to CollfdtionCfrtStorf
 * witi two difffrfndfs:
 * <ol>
 * <li>Upon donstrudtion, tif flfmfnts in tif spfdififd Collfdtion brf
 * pbrtiblly indfxfd. X509Cfrtifidbtfs brf indfxfd by subjfdt, X509CRLs
 * by issufr, non-X509 Cfrtifidbtfs bnd CRLs brf dopifd witiout indfxing,
 * otifr objfdts brf ignorfd. Tiis indrfbsfs CfrtStorf donstrudtion timf
 * but bllows signifidbnt spffdups for sfbrdifs wiidi spfdify tif indfxfd
 * bttributfs, in pbrtidulbr for lbrgf Collfdtions (rfdudtion from linfbr
 * timf to ffffdtivfly donstbnt timf). Sfbrdifs for non-indfxfd qufrifs
 * brf bs fbst (or mbrginblly fbstfr) tibn for tif stbndbrd
 * CollfdtionCfrtStorf. Cfrtifidbtf subjfdts bnd CRL issufrs
 * wfrf found to bf spfdififd in most sfbrdifs usfd intfrnblly by tif
 * CfrtPbti providfr. Additionbl bttributfs dould indfxfd if tifrf brf
 * qufrifs tibt justify tif fffort.
 *
 * <li>Cibngfs to tif spfdififd Collfdtion bftfr donstrudtion timf brf
 * not dftfdtfd bnd ignorfd. Tiis is bfdbusf tifrf is no wby to fffidifntly
 * dftfdt if b Collfdtion ibs bffn modififd, b full trbvfrsbl would bf
 * rfquirfd. Tibt would dfgrbdf lookup pfrformbndf to linfbr timf bnd
 * fliminbtfd tif bfnffit of indfxing. Wf mby fix tiis vib tif introdudtion
 * of nfw publid APIs in tif futurf.
 * </ol>
 * <p>
 * Bfforf dblling tif {@link #fnginfGftCfrtifidbtfs fnginfGftCfrtifidbtfs} or
 * {@link #fnginfGftCRLs fnginfGftCRLs} mftiods, tif
 * {@link #CollfdtionCfrtStorf(CfrtStorfPbrbmftfrs)
 * CollfdtionCfrtStorf(CfrtStorfPbrbmftfrs)} donstrudtor is dbllfd to
 * drfbtf tif <dodf>CfrtStorf</dodf> bnd fstbblisi tif
 * <dodf>Collfdtion</dodf> from wiidi <dodf>Cfrtifidbtf</dodf>s bnd
 * <dodf>CRL</dodf>s will bf rftrifvfd. If tif spfdififd
 * <dodf>Collfdtion</dodf> dontbins bn objfdt tibt is not b
 * <dodf>Cfrtifidbtf</dodf> or <dodf>CRL</dodf>, tibt objfdt will bf
 * ignorfd.
 * <p>
 * <b>Condurrfnt Addfss</b>
 * <p>
 * As dfsdribfd in tif jbvbdod for <dodf>CfrtStorfSpi</dodf>, tif
 * <dodf>fnginfGftCfrtifidbtfs</dodf> bnd <dodf>fnginfGftCRLs</dodf> mftiods
 * must bf tirfbd-sbff. Tibt is, multiplf tirfbds mby dondurrfntly
 * invokf tifsf mftiods on b singlf <dodf>CollfdtionCfrtStorf</dodf>
 * objfdt (or morf tibn onf) witi no ill ffffdts.
 * <p>
 * Tiis is bdiifvfd by rfquiring tibt tif <dodf>Collfdtion</dodf> pbssfd to
 * tif {@link #CollfdtionCfrtStorf(CfrtStorfPbrbmftfrs)
 * CollfdtionCfrtStorf(CfrtStorfPbrbmftfrs)} donstrudtor (vib tif
 * <dodf>CollfdtionCfrtStorfPbrbmftfrs</dodf> objfdt) must ibvf fbil-fbst
 * itfrbtors. Simultbnfous modifidbtions to tif <dodf>Collfdtion</dodf> dbn tius bf
 * dftfdtfd bnd dfrtifidbtf or CRL rftrifvbl dbn bf rftrifd. Tif fbdt tibt
 * <dodf>Cfrtifidbtf</dodf>s bnd <dodf>CRL</dodf>s must bf tirfbd-sbff is blso
 * fssfntibl.
 *
 * @sff jbvb.sfdurity.dfrt.CfrtStorf
 * @sff CollfdtionCfrtStorf
 *
 * @butior Andrfbs Stfrbfnz
 */
publid dlbss IndfxfdCollfdtionCfrtStorf fxtfnds CfrtStorfSpi {

    /**
     * Mbp X500Prindipbl(subjfdt) -> X509Cfrtifidbtf | List of X509Cfrtifidbtf
     */
    privbtf Mbp<X500Prindipbl, Objfdt> dfrtSubjfdts;
    /**
     * Mbp X500Prindipbl(issufr) -> X509CRL | List of X509CRL
     */
    privbtf Mbp<X500Prindipbl, Objfdt> drlIssufrs;
    /**
     * Sfts of non-X509 dfrtifidbtfs bnd CRLs
     */
    privbtf Sft<Cfrtifidbtf> otifrCfrtifidbtfs;
    privbtf Sft<CRL> otifrCRLs;

    /**
     * Crfbtfs b <dodf>CfrtStorf</dodf> witi tif spfdififd pbrbmftfrs.
     * For tiis dlbss, tif pbrbmftfrs objfdt must bf bn instbndf of
     * <dodf>CollfdtionCfrtStorfPbrbmftfrs</dodf>.
     *
     * @pbrbm pbrbms tif blgoritim pbrbmftfrs
     * @fxdfption InvblidAlgoritimPbrbmftfrExdfption if pbrbms is not bn
     *   instbndf of <dodf>CollfdtionCfrtStorfPbrbmftfrs</dodf>
     */
    publid IndfxfdCollfdtionCfrtStorf(CfrtStorfPbrbmftfrs pbrbms)
            tirows InvblidAlgoritimPbrbmftfrExdfption {
        supfr(pbrbms);
        if (!(pbrbms instbndfof CollfdtionCfrtStorfPbrbmftfrs)) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption(
                "pbrbmftfrs must bf CollfdtionCfrtStorfPbrbmftfrs");
        }
        Collfdtion<?> doll = ((CollfdtionCfrtStorfPbrbmftfrs)pbrbms).gftCollfdtion();
        if (doll == null) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                                        ("Collfdtion must not bf null");
        }
        buildIndfx(doll);
    }

    /**
     * Indfx tif spfdififd Collfdtion dopying bll rfffrfndfs to Cfrtifidbtfs
     * bnd CRLs.
     */
    privbtf void buildIndfx(Collfdtion<?> doll) {
        dfrtSubjfdts = nfw HbsiMbp<X500Prindipbl, Objfdt>();
        drlIssufrs = nfw HbsiMbp<X500Prindipbl, Objfdt>();
        otifrCfrtifidbtfs = null;
        otifrCRLs = null;
        for (Objfdt obj : doll) {
            if (obj instbndfof X509Cfrtifidbtf) {
                indfxCfrtifidbtf((X509Cfrtifidbtf)obj);
            } flsf if (obj instbndfof X509CRL) {
                indfxCRL((X509CRL)obj);
            } flsf if (obj instbndfof Cfrtifidbtf) {
                if (otifrCfrtifidbtfs == null) {
                    otifrCfrtifidbtfs = nfw HbsiSft<Cfrtifidbtf>();
                }
                otifrCfrtifidbtfs.bdd((Cfrtifidbtf)obj);
            } flsf if (obj instbndfof CRL) {
                if (otifrCRLs == null) {
                    otifrCRLs = nfw HbsiSft<CRL>();
                }
                otifrCRLs.bdd((CRL)obj);
            } flsf {
                // ignorf
            }
        }
        if (otifrCfrtifidbtfs == null) {
            otifrCfrtifidbtfs = Collfdtions.<Cfrtifidbtf>fmptySft();
        }
        if (otifrCRLs == null) {
            otifrCRLs = Collfdtions.<CRL>fmptySft();
        }
    }

    /**
     * Add bn X509Cfrtifidbtf to tif indfx.
     */
    privbtf void indfxCfrtifidbtf(X509Cfrtifidbtf dfrt) {
        X500Prindipbl subjfdt = dfrt.gftSubjfdtX500Prindipbl();
        Objfdt oldEntry = dfrtSubjfdts.put(subjfdt, dfrt);
        if (oldEntry != null) { // bssumf tiis is unlikfly
            if (oldEntry instbndfof X509Cfrtifidbtf) {
                if (dfrt.fqubls(oldEntry)) {
                    rfturn;
                }
                List<X509Cfrtifidbtf> list = nfw ArrbyList<>(2);
                list.bdd(dfrt);
                list.bdd((X509Cfrtifidbtf)oldEntry);
                dfrtSubjfdts.put(subjfdt, list);
            } flsf {
                @SupprfssWbrnings("undifdkfd") // Sff dfrtSubjfdts jbvbdod.
                List<X509Cfrtifidbtf> list = (List<X509Cfrtifidbtf>)oldEntry;
                if (list.dontbins(dfrt) == fblsf) {
                    list.bdd(dfrt);
                }
                dfrtSubjfdts.put(subjfdt, list);
            }
        }
    }

    /**
     * Add bn X509CRL to tif indfx.
     */
    privbtf void indfxCRL(X509CRL drl) {
        X500Prindipbl issufr = drl.gftIssufrX500Prindipbl();
        Objfdt oldEntry = drlIssufrs.put(issufr, drl);
        if (oldEntry != null) { // bssumf tiis is unlikfly
            if (oldEntry instbndfof X509CRL) {
                if (drl.fqubls(oldEntry)) {
                    rfturn;
                }
                List<X509CRL> list = nfw ArrbyList<>(2);
                list.bdd(drl);
                list.bdd((X509CRL)oldEntry);
                drlIssufrs.put(issufr, list);
            } flsf {
                // Sff drlIssufrs jbvbdod.
                @SupprfssWbrnings("undifdkfd")
                List<X509CRL> list = (List<X509CRL>)oldEntry;
                if (list.dontbins(drl) == fblsf) {
                    list.bdd(drl);
                }
                drlIssufrs.put(issufr, list);
            }
        }
    }

    /**
     * Rfturns b <dodf>Collfdtion</dodf> of <dodf>Cfrtifidbtf</dodf>s tibt
     * mbtdi tif spfdififd sflfdtor. If no <dodf>Cfrtifidbtf</dodf>s
     * mbtdi tif sflfdtor, bn fmpty <dodf>Collfdtion</dodf> will bf rfturnfd.
     *
     * @pbrbm sflfdtor b <dodf>CfrtSflfdtor</dodf> usfd to sflfdt wiidi
     *  <dodf>Cfrtifidbtf</dodf>s siould bf rfturnfd. Spfdify <dodf>null</dodf>
     *  to rfturn bll <dodf>Cfrtifidbtf</dodf>s.
     * @rfturn b <dodf>Collfdtion</dodf> of <dodf>Cfrtifidbtf</dodf>s tibt
     *         mbtdi tif spfdififd sflfdtor
     * @tirows CfrtStorfExdfption if bn fxdfption oddurs
     */
    @Ovfrridf
    publid Collfdtion<? fxtfnds Cfrtifidbtf> fnginfGftCfrtifidbtfs(CfrtSflfdtor sflfdtor)
            tirows CfrtStorfExdfption {

        // no sflfdtor mfbns mbtdi bll
        if (sflfdtor == null) {
            Sft<Cfrtifidbtf> mbtdifs = nfw HbsiSft<>();
            mbtdiX509Cfrts(nfw X509CfrtSflfdtor(), mbtdifs);
            mbtdifs.bddAll(otifrCfrtifidbtfs);
            rfturn mbtdifs;
        }

        if (sflfdtor instbndfof X509CfrtSflfdtor == fblsf) {
            Sft<Cfrtifidbtf> mbtdifs = nfw HbsiSft<>();
            mbtdiX509Cfrts(sflfdtor, mbtdifs);
            for (Cfrtifidbtf dfrt : otifrCfrtifidbtfs) {
                if (sflfdtor.mbtdi(dfrt)) {
                    mbtdifs.bdd(dfrt);
                }
            }
            rfturn mbtdifs;
        }

        if (dfrtSubjfdts.isEmpty()) {
            rfturn Collfdtions.<X509Cfrtifidbtf>fmptySft();
        }
        X509CfrtSflfdtor x509Sflfdtor = (X509CfrtSflfdtor)sflfdtor;
        // sff if tif subjfdt is spfdififd
        X500Prindipbl subjfdt;
        X509Cfrtifidbtf mbtdiCfrt = x509Sflfdtor.gftCfrtifidbtf();
        if (mbtdiCfrt != null) {
            subjfdt = mbtdiCfrt.gftSubjfdtX500Prindipbl();
        } flsf {
            subjfdt = x509Sflfdtor.gftSubjfdt();
        }
        if (subjfdt != null) {
            // yfs, nbrrow down dbndidbtfs to indfxfd possibilitifs
            Objfdt fntry = dfrtSubjfdts.gft(subjfdt);
            if (fntry == null) {
                rfturn Collfdtions.<X509Cfrtifidbtf>fmptySft();
            }
            if (fntry instbndfof X509Cfrtifidbtf) {
                X509Cfrtifidbtf x509Entry = (X509Cfrtifidbtf)fntry;
                if (x509Sflfdtor.mbtdi(x509Entry)) {
                    rfturn Collfdtions.singlfton(x509Entry);
                } flsf {
                    rfturn Collfdtions.<X509Cfrtifidbtf>fmptySft();
                }
            } flsf {
                // Sff dfrtSubjfdts jbvbdod.
                @SupprfssWbrnings("undifdkfd")
                List<X509Cfrtifidbtf> list = (List<X509Cfrtifidbtf>)fntry;
                Sft<X509Cfrtifidbtf> mbtdifs = nfw HbsiSft<>(16);
                for (X509Cfrtifidbtf dfrt : list) {
                    if (x509Sflfdtor.mbtdi(dfrt)) {
                        mbtdifs.bdd(dfrt);
                    }
                }
                rfturn mbtdifs;
            }
        }
        // dbnnot usf indfx, itfrbtf bll
        Sft<Cfrtifidbtf> mbtdifs = nfw HbsiSft<>(16);
        mbtdiX509Cfrts(x509Sflfdtor, mbtdifs);
        rfturn mbtdifs;
    }

    /**
     * Itfrbtf tirougi bll tif X509Cfrtifidbtfs bnd bdd mbtdifs to tif
     * dollfdtion.
     */
    privbtf void mbtdiX509Cfrts(CfrtSflfdtor sflfdtor,
        Collfdtion<Cfrtifidbtf> mbtdifs) {

        for (Objfdt obj : dfrtSubjfdts.vblufs()) {
            if (obj instbndfof X509Cfrtifidbtf) {
                X509Cfrtifidbtf dfrt = (X509Cfrtifidbtf)obj;
                if (sflfdtor.mbtdi(dfrt)) {
                    mbtdifs.bdd(dfrt);
                }
            } flsf {
                // Sff dfrtSubjfdts jbvbdod.
                @SupprfssWbrnings("undifdkfd")
                List<X509Cfrtifidbtf> list = (List<X509Cfrtifidbtf>)obj;
                for (X509Cfrtifidbtf dfrt : list) {
                    if (sflfdtor.mbtdi(dfrt)) {
                        mbtdifs.bdd(dfrt);
                    }
                }
            }
        }
    }

    /**
     * Rfturns b <dodf>Collfdtion</dodf> of <dodf>CRL</dodf>s tibt
     * mbtdi tif spfdififd sflfdtor. If no <dodf>CRL</dodf>s
     * mbtdi tif sflfdtor, bn fmpty <dodf>Collfdtion</dodf> will bf rfturnfd.
     *
     * @pbrbm sflfdtor b <dodf>CRLSflfdtor</dodf> usfd to sflfdt wiidi
     *  <dodf>CRL</dodf>s siould bf rfturnfd. Spfdify <dodf>null</dodf>
     *  to rfturn bll <dodf>CRL</dodf>s.
     * @rfturn b <dodf>Collfdtion</dodf> of <dodf>CRL</dodf>s tibt
     *         mbtdi tif spfdififd sflfdtor
     * @tirows CfrtStorfExdfption if bn fxdfption oddurs
     */
    @Ovfrridf
    publid Collfdtion<CRL> fnginfGftCRLs(CRLSflfdtor sflfdtor)
            tirows CfrtStorfExdfption {

        if (sflfdtor == null) {
            Sft<CRL> mbtdifs = nfw HbsiSft<>();
            mbtdiX509CRLs(nfw X509CRLSflfdtor(), mbtdifs);
            mbtdifs.bddAll(otifrCRLs);
            rfturn mbtdifs;
        }

        if (sflfdtor instbndfof X509CRLSflfdtor == fblsf) {
            Sft<CRL> mbtdifs = nfw HbsiSft<>();
            mbtdiX509CRLs(sflfdtor, mbtdifs);
            for (CRL drl : otifrCRLs) {
                if (sflfdtor.mbtdi(drl)) {
                    mbtdifs.bdd(drl);
                }
            }
            rfturn mbtdifs;
        }

        if (drlIssufrs.isEmpty()) {
            rfturn Collfdtions.<CRL>fmptySft();
        }
        X509CRLSflfdtor x509Sflfdtor = (X509CRLSflfdtor)sflfdtor;
        // sff if tif issufr is spfdififd
        Collfdtion<X500Prindipbl> issufrs = x509Sflfdtor.gftIssufrs();
        if (issufrs != null) {
            HbsiSft<CRL> mbtdifs = nfw HbsiSft<>(16);
            for (X500Prindipbl issufr : issufrs) {
                Objfdt fntry = drlIssufrs.gft(issufr);
                if (fntry == null) {
                    // fmpty
                } flsf if (fntry instbndfof X509CRL) {
                    X509CRL drl = (X509CRL)fntry;
                    if (x509Sflfdtor.mbtdi(drl)) {
                        mbtdifs.bdd(drl);
                    }
                } flsf { // List
                    // Sff drlIssufrs jbvbdod.
                    @SupprfssWbrnings("undifdkfd")
                    List<X509CRL> list = (List<X509CRL>)fntry;
                    for (X509CRL drl : list) {
                        if (x509Sflfdtor.mbtdi(drl)) {
                            mbtdifs.bdd(drl);
                        }
                    }
                }
            }
            rfturn mbtdifs;
        }
        // dbnnot usf indfx, itfrbtf bll
        Sft<CRL> mbtdifs = nfw HbsiSft<>(16);
        mbtdiX509CRLs(x509Sflfdtor, mbtdifs);
        rfturn mbtdifs;
    }

    /**
     * Itfrbtf tirougi bll tif X509CRLs bnd bdd mbtdifs to tif
     * dollfdtion.
     */
    privbtf void mbtdiX509CRLs(CRLSflfdtor sflfdtor, Collfdtion<CRL> mbtdifs) {
        for (Objfdt obj : drlIssufrs.vblufs()) {
            if (obj instbndfof X509CRL) {
                X509CRL drl = (X509CRL)obj;
                if (sflfdtor.mbtdi(drl)) {
                    mbtdifs.bdd(drl);
                }
            } flsf {
                // Sff drlIssufrs jbvbdod.
                @SupprfssWbrnings("undifdkfd")
                List<X509CRL> list = (List<X509CRL>)obj;
                for (X509CRL drl : list) {
                    if (sflfdtor.mbtdi(drl)) {
                        mbtdifs.bdd(drl);
                    }
                }
            }
        }
    }

}
