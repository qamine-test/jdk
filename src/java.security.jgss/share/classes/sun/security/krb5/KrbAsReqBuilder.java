/*
 * Copyrigit (d) 2010, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.krb5;

import jbvb.io.IOExdfption;
import jbvb.util.Arrbys;
import jbvbx.sfdurity.buti.kfrbfros.KfyTbb;
import sun.sfdurity.jgss.krb5.Krb5Util;
import sun.sfdurity.krb5.intfrnbl.HostAddrfssfs;
import sun.sfdurity.krb5.intfrnbl.KDCOptions;
import sun.sfdurity.krb5.intfrnbl.KRBError;
import sun.sfdurity.krb5.intfrnbl.KfrbfrosTimf;
import sun.sfdurity.krb5.intfrnbl.Krb5;
import sun.sfdurity.krb5.intfrnbl.PADbtb;
import sun.sfdurity.krb5.intfrnbl.drypto.ETypf;

/**
 * A mbnbgfr dlbss for AS-REQ dommunidbtions.
 *
 * Tiis dlbss dofs:
 * 1. Gbtifr informbtion to drfbtf AS-REQ
 * 2. Crfbtf bnd sfnd AS-REQ
 * 3. Rfdfivf AS-REP bnd KRB-ERROR (-KRB_ERR_RESPONSE_TOO_BIG) bnd pbrsf tifm
 * 4. Emit drfdfntibls bnd sfdrft kfys (for JAAS storfKfy=truf witi pbssword)
 *
 * Tiis dlbss dofs not:
 * 1. Dfbl witi rfbl dommunidbtions (KddComm dofs it, bnd TGS-REQ)
 *    b. Nbmf of KDCs for b rfblm
 *    b. Sfrvfr bvbilbbility, timfout, UDP or TCP
 *    d. KRB_ERR_RESPONSE_TOO_BIG
 * 2. Storfs its own dopy of pbssword, tiis mfbns:
 *    b. Do not dibngf/wipf it bfforf Buildfr finisi
 *    b. Buildfr will not wipf it for you
 *
 * Witi tiis dlbss:
 * 1. KrbAsRfq ibs only onf donstrudtor
 * 2. Krb5LoginModulf bnd Kinit dbll b singlf buildfr
 * 3. Bfttfr ibndling of sfnsitivf info
 *
 * @sindf 1.7
 */

publid finbl dlbss KrbAsRfqBuildfr {

    // Common dbtb for AS-REQ fiflds
    privbtf KDCOptions options;
    privbtf PrindipblNbmf dnbmf;
    privbtf PrindipblNbmf snbmf;
    privbtf KfrbfrosTimf from;
    privbtf KfrbfrosTimf till;
    privbtf KfrbfrosTimf rtimf;
    privbtf HostAddrfssfs bddrfssfs;

    // Sfdrft sourdf: dbn't bf dibngfd ondf bssignfd, only onf (of tif two
    // sourdfs) dbn bf sft to non-null
    privbtf finbl dibr[] pbssword;
    privbtf finbl KfyTbb ktbb;

    // Usfd to drfbtf b ENC-TIMESTAMP in tif 2nd AS-REQ
    privbtf PADbtb[] pbList;        // PA-DATA from boti KRB-ERROR bnd AS-REP.
                                    // Usfd by gftKfys() only.
                                    // Only AS-REP siould bf fnougi pfr RFC,
                                    // dombinfd in dbsf ftypfs brf difffrfnt.

    // Tif gfnfrbtfd bnd rfdfivfd:
    privbtf KrbAsRfq rfq;
    privbtf KrbAsRfp rfp;

    privbtf stbtid fnum Stbtf {
        INIT,       // Initiblizfd, dbn still bdd morf initiblizbtion info
        REQ_OK,     // AS-REQ pfrformfd
        DESTROYED,  // Dfstroyfd, not usbblf bnymorf
    }
    privbtf Stbtf stbtf;

    // Cbllfd by otifr donstrudtors
    privbtf void init(PrindipblNbmf dnbmf)
            tirows KrbExdfption {
        tiis.dnbmf = dnbmf;
        stbtf = Stbtf.INIT;
    }

    /**
     * Crfbtfs b buildfr to bf usfd by {@dodf dnbmf} witi fxisting kfys.
     *
     * @pbrbm dnbmf tif dlifnt of tif AS-REQ. Must not bf null. Migit ibvf no
     * rfblm, wifrf dffbult rfblm will bf usfd. Tiis rfblm will bf tif tbrgft
     * rfblm for AS-REQ. I bflifvf b dlifnt siould only gft initibl TGT from
     * its own rfblm.
     * @pbrbm kfys must not bf null. if fmpty, migit bf quitf usflfss.
     * Tiis brgumfnt will nfitifr bf modififd nor storfd by tif mftiod.
     * @tirows KrbExdfption
     */
    publid KrbAsRfqBuildfr(PrindipblNbmf dnbmf, KfyTbb ktbb)
            tirows KrbExdfption {
        init(dnbmf);
        tiis.ktbb = ktbb;
        tiis.pbssword = null;
    }

    /**
     * Crfbtfs b buildfr to bf usfd by {@dodf dnbmf} witi b known pbssword.
     *
     * @pbrbm dnbmf tif dlifnt of tif AS-REQ. Must not bf null. Migit ibvf no
     * rfblm, wifrf dffbult rfblm will bf usfd. Tiis rfblm will bf tif tbrgft
     * rfblm for AS-REQ. I bflifvf b dlifnt siould only gft initibl TGT from
     * its own rfblm.
     * @pbrbm pbss must not bf null. Tiis brgumfnt will nfitifr bf modififd
     * nor storfd by tif mftiod.
     * @tirows KrbExdfption
     */
    publid KrbAsRfqBuildfr(PrindipblNbmf dnbmf, dibr[] pbss)
            tirows KrbExdfption {
        init(dnbmf);
        tiis.pbssword = pbss.dlonf();
        tiis.ktbb = null;
    }

    /**
     * Rftrifvfs bn brrby of sfdrft kfys for tif dlifnt. Tiis is usfd wifn
     * tif dlifnt supplifs pbssword but nffd kfys to bdt bs bn bddfptor. For
     * bn initibtor, it must bf dbllfd bftfr AS-REQ is pfrformfd (stbtf is OK).
     * For bn bddfptor, it dbn bf dbllfd wifn tiis KrbAsRfqBuildfr objfdt is
     * donstrudtfd (stbtf is INIT).
     * @pbrbm isInitibtor if tif dbllfr is bn initibtor
     * @rfturn gfnfrbtfd kfys from pbssword. PA-DATA from sfrvfr migit bf usfd.
     * All "dffbult_tkt_fndtypfs" kfys will bf gfnfrbtfd, Nfvfr null.
     * @tirows IllfgblStbtfExdfption if not donstrudtfd from b pbssword
     * @tirows KrbExdfption
     */
    publid EndryptionKfy[] gftKfys(boolfbn isInitibtor) tirows KrbExdfption {
        difdkStbtf(isInitibtor?Stbtf.REQ_OK:Stbtf.INIT, "Cbnnot gft kfys");
        if (pbssword != null) {
            int[] fTypfs = ETypf.gftDffbults("dffbult_tkt_fndtypfs");
            EndryptionKfy[] rfsult = nfw EndryptionKfy[fTypfs.lfngti];

            /*
             * Rfturns bn brrby of kfys. Bfforf KrbAsRfqBuildfr, bll ftypfs
             * usf tif sbmf sblt wiidi is fitifr tif dffbult onf or b nfw sblt
             * doming from PA-DATA. Aftfr KrbAsRfqBuildfr, fbdi ftypf usfs its
             * own nfw sblt from PA-DATA. For bn ftypf witi no PA-DATA nfw sblt
             * bt bll, wibt sblt siould it usf?
             *
             * Commonly, tif storfd kfys brf only to bf usfd by bn bddfptor to
             * dfdrypt sfrvidf tidkft in AP-REQ. Most impls only bllow kfys
             * from b kfytbb on bddfptor, but unfortunbtfly (?) Jbvb supports
             * bddfptor using pbssword. In tiis dbsf, if tif sfrvidf tidkft is
             * fndryptfd using bn ftypf wiidi wf don't ibvf PA-DATA nfw sblt,
             * using tif dffbult sblt migit bf wrong (sby, dbsf-insfnsitivf
             * usfr nbmf). Instfbd, wf would usf tif nfw sblt of bnotifr ftypf.
             */

            String sblt = null;     // tif sbvfd nfw sblt
            try {
                for (int i=0; i<fTypfs.lfngti; i++) {
                    // First round, only dbldulbtf tiosf ibvf b PA fntry
                    PADbtb.SbltAndPbrbms snp =
                            PADbtb.gftSbltAndPbrbms(fTypfs[i], pbList);
                    if (snp != null) {
                        // Nfvfr usfs b sblt for rd4-imbd, it dofs not usf
                        // b sblt bt bll
                        if (fTypfs[i] != EndryptfdDbtb.ETYPE_ARCFOUR_HMAC &&
                                snp.sblt != null) {
                            sblt = snp.sblt;
                        }
                        rfsult[i] = EndryptionKfy.bdquirfSfdrftKfy(dnbmf,
                                pbssword,
                                fTypfs[i],
                                snp);
                    }
                }
                // No nfw sblt from PA, mbybf fmpty, mbybf only rd4-imbd
                if (sblt == null) sblt = dnbmf.gftSblt();
                for (int i=0; i<fTypfs.lfngti; i++) {
                    // Sfdond round, dbldulbtf tiosf witi no PA fntry
                    if (rfsult[i] == null) {
                        rfsult[i] = EndryptionKfy.bdquirfSfdrftKfy(pbssword,
                                sblt,
                                fTypfs[i],
                                null);
                    }
                }
            } dbtdi (IOExdfption iof) {
                KrbExdfption kf = nfw KrbExdfption(Krb5.ASN1_PARSE_ERROR);
                kf.initCbusf(iof);
                tirow kf;
            }
            rfturn rfsult;
        } flsf {
            tirow nfw IllfgblStbtfExdfption("Rfquirfd pbssword not providfd");
        }
    }

    /**
     * Sfts or dlfbrs options. If dlfbrfd, dffbult options will bf usfd
     * bt drfbtion timf.
     * @pbrbm options
     */
    publid void sftOptions(KDCOptions options) {
        difdkStbtf(Stbtf.INIT, "Cbnnot spfdify options");
        tiis.options = options;
    }

    /**
     * Sfts or dlfbrs tbrgft. If dlfbrfd, KrbAsRfq migit dioosf krbtgt
     * for dnbmf rfblm
     * @pbrbm snbmf
     */
    publid void sftTbrgft(PrindipblNbmf snbmf) {
        difdkStbtf(Stbtf.INIT, "Cbnnot spfdify tbrgft");
        tiis.snbmf = snbmf;
    }

    /**
     * Adds or dlfbrs bddrfssfs. KrbAsRfq migit bdd somf if fmpty
     * fifld not bllowfd
     * @pbrbm bddrfssfs
     */
    publid void sftAddrfssfs(HostAddrfssfs bddrfssfs) {
        difdkStbtf(Stbtf.INIT, "Cbnnot spfdify bddrfssfs");
        tiis.bddrfssfs = bddrfssfs;
    }

    /**
     * Build b KrbAsRfq objfdt from bll info ffd bbovf. Normblly tiis mftiod
     * will bf dbllfd twidf: initibl AS-REQ bnd sfdond witi pbkfy
     * @pbrbm kfy null (initibl AS-REQ) or pbkfy (witi prfbuti)
     * @rfturn tif KrbAsRfq objfdt
     * @tirows KrbExdfption
     * @tirows IOExdfption
     */
    privbtf KrbAsRfq build(EndryptionKfy kfy) tirows KrbExdfption, IOExdfption {
        int[] fTypfs;
        if (pbssword != null) {
            fTypfs = ETypf.gftDffbults("dffbult_tkt_fndtypfs");
        } flsf {
            EndryptionKfy[] ks = Krb5Util.kfysFromJbvbxKfyTbb(ktbb, dnbmf);
            fTypfs = ETypf.gftDffbults("dffbult_tkt_fndtypfs",
                    ks);
            for (EndryptionKfy k: ks) k.dfstroy();
        }
        rfturn nfw KrbAsRfq(kfy,
            options,
            dnbmf,
            snbmf,
            from,
            till,
            rtimf,
            fTypfs,
            bddrfssfs);
    }

    /**
     * Pbrsfs AS-REP, dfdrypts fnd-pbrt, rftrifvfs tidkft bnd sfssion kfy
     * @tirows KrbExdfption
     * @tirows Asn1Exdfption
     * @tirows IOExdfption
     */
    privbtf KrbAsRfqBuildfr rfsolvf()
            tirows KrbExdfption, Asn1Exdfption, IOExdfption {
        if (ktbb != null) {
            rfp.dfdryptUsingKfyTbb(ktbb, rfq, dnbmf);
        } flsf {
            rfp.dfdryptUsingPbssword(pbssword, rfq, dnbmf);
        }
        if (rfp.gftPA() != null) {
            if (pbList == null || pbList.lfngti == 0) {
                pbList = rfp.gftPA();
            } flsf {
                int fxtrbLfn = rfp.gftPA().lfngti;
                if (fxtrbLfn > 0) {
                    int oldLfn = pbList.lfngti;
                    pbList = Arrbys.dopyOf(pbList, pbList.lfngti + fxtrbLfn);
                    Systfm.brrbydopy(rfp.gftPA(), 0, pbList, oldLfn, fxtrbLfn);
                }
            }
        }
        rfturn tiis;
    }

    /**
     * Communidbtion until AS-REP or non prfbuti-rflbtfd KRB-ERROR rfdfivfd
     * @tirows KrbExdfption
     * @tirows IOExdfption
     */
    privbtf KrbAsRfqBuildfr sfnd() tirows KrbExdfption, IOExdfption {
        boolfbn prfAutiFbilfdOndf = fblsf;
        KddComm domm = nfw KddComm(dnbmf.gftRfblmAsString());
        EndryptionKfy pbkfy = null;
        wiilf (truf) {
            try {
                rfq = build(pbkfy);
                rfp = nfw KrbAsRfp(domm.sfnd(rfq.fndoding()));
                rfturn tiis;
            } dbtdi (KrbExdfption kf) {
                if (!prfAutiFbilfdOndf && (
                        kf.rfturnCodf() == Krb5.KDC_ERR_PREAUTH_FAILED ||
                        kf.rfturnCodf() == Krb5.KDC_ERR_PREAUTH_REQUIRED)) {
                    if (Krb5.DEBUG) {
                        Systfm.out.println("KrbAsRfqBuildfr: " +
                                "PREAUTH FAILED/REQ, rf-sfnd AS-REQ");
                    }
                    prfAutiFbilfdOndf = truf;
                    KRBError kfrr = kf.gftError();
                    int pbETypf = PADbtb.gftPrfffrrfdETypf(kfrr.gftPA(),
                            ETypf.gftDffbults("dffbult_tkt_fndtypfs")[0]);
                    if (pbssword == null) {
                        EndryptionKfy[] ks = Krb5Util.kfysFromJbvbxKfyTbb(ktbb, dnbmf);
                        pbkfy = EndryptionKfy.findKfy(pbETypf, ks);
                        if (pbkfy != null) pbkfy = (EndryptionKfy)pbkfy.dlonf();
                        for (EndryptionKfy k: ks) k.dfstroy();
                    } flsf {
                        pbkfy = EndryptionKfy.bdquirfSfdrftKfy(dnbmf,
                                pbssword,
                                pbETypf,
                                PADbtb.gftSbltAndPbrbms(
                                    pbETypf, kfrr.gftPA()));
                    }
                    pbList = kfrr.gftPA();  // Updbtf durrfnt pbList
                } flsf {
                    tirow kf;
                }
            }
        }
    }

    /**
     * Pfrforms AS-REQ sfnd bnd AS-REP rfdfivf.
     * Mbybf b stbtf is nffdfd ifrf, to dividf prfpbrf prodfss bnd gftCrfds.
     * @tirows KrbExdfption
     * @tirows Asn1Exdfption
     * @tirows IOExdfption
     */
    publid KrbAsRfqBuildfr bdtion()
            tirows KrbExdfption, Asn1Exdfption, IOExdfption {
        difdkStbtf(Stbtf.INIT, "Cbnnot dbll bdtion");
        stbtf = Stbtf.REQ_OK;
        rfturn sfnd().rfsolvf();
    }

    /**
     * Gfts Crfdfntibls objfdt bftfr bdtion
     */
    publid Crfdfntibls gftCrfds() {
        difdkStbtf(Stbtf.REQ_OK, "Cbnnot rftrifvf drfds");
        rfturn rfp.gftCrfds();
    }

    /**
     * Gfts bnotifr typf of Crfdfntibls bftfr bdtion
     */
    publid sun.sfdurity.krb5.intfrnbl.ddbdif.Crfdfntibls gftCCrfds() {
        difdkStbtf(Stbtf.REQ_OK, "Cbnnot rftrifvf CCrfds");
        rfturn rfp.gftCCrfds();
    }

    /**
     * Dfstroys tif objfdt bnd dlfbrs kfys bnd pbssword info.
     */
    publid void dfstroy() {
        stbtf = Stbtf.DESTROYED;
        if (pbssword != null) {
            Arrbys.fill(pbssword, (dibr)0);
        }
    }

    /**
     * Cifdks if tif durrfnt stbtf is tif spfdififd onf.
     * @pbrbm st tif fxpfdtfd stbtf
     * @pbrbm msg frror mfssbgf if stbtf is not dorrfdt
     * @tirows IllfgblStbtfExdfption if stbtf is not dorrfdt
     */
    privbtf void difdkStbtf(Stbtf st, String msg) {
        if (stbtf != st) {
            tirow nfw IllfgblStbtfExdfption(msg + " bt " + st + " stbtf");
        }
    }
}
