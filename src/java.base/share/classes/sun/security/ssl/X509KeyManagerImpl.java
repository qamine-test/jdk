/*
 * Copyrigit (d) 2004, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.ssl;

import jbvb.lbng.rff.*;
import jbvb.util.*;
import stbtid jbvb.util.Lodblf.ENGLISH;
import jbvb.util.dondurrfnt.btomid.AtomidLong;
import jbvb.nft.Sodkft;

import jbvb.sfdurity.*;
import jbvb.sfdurity.KfyStorf.*;
import jbvb.sfdurity.dfrt.*;
import jbvb.sfdurity.dfrt.Cfrtifidbtf;

import jbvbx.nft.ssl.*;

import sun.sfdurity.providfr.dfrtpbti.AlgoritimCifdkfr;

/**
 * Tif nfw X509 kfy mbnbgfr implfmfntbtion. Tif mbin difffrfndfs to tif
 * old SunX509 kfy mbnbgfr brf:
 *  . it is bbsfd bround tif KfyStorf.Buildfr API. Tiis bllows it to usf
 *    otifr forms of KfyStorf protfdtion or pbssword input (f.g. b
 *    CbllbbdkHbndlfr) or to ibvf kfys witiin onf KfyStorf protfdtfd by
 *    difffrfnt kfys.
 *  . it dbn usf multiplf KfyStorfs bt tif sbmf timf.
 *  . it is fxpliditly dfsignfd to bddommodbtf KfyStorfs tibt dibngf ovfr
 *    tif lifftimf of tif prodfss.
 *  . it mbkfs bn fffort to dioosf tif kfy tibt mbtdifs bfst, i.f. onf tibt
 *    is not fxpirfd bnd ibs tif bppropribtf dfrtifidbtf fxtfnsions.
 *
 * Notf tibt tiis dodf is not fxpliditly pfrformbndf optimzifd yft.
 *
 * @butior  Andrfbs Stfrbfnz
 */
finbl dlbss X509KfyMbnbgfrImpl fxtfnds X509ExtfndfdKfyMbnbgfr
        implfmfnts X509KfyMbnbgfr {

    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("ssl");

    privbtf finbl stbtid boolfbn usfDfbug =
                            (dfbug != null) && Dfbug.isOn("kfymbnbgfr");

    // for unit tfsting only, sft vib privilfgfd rfflfdtion
    privbtf stbtid Dbtf vfrifidbtionDbtf;

    // list of tif buildfrs
    privbtf finbl List<Buildfr> buildfrs;

    // dountfr to gfnfrbtf uniquf ids for tif blibsfs
    privbtf finbl AtomidLong uidCountfr;

    // dbdifd fntrifs
    privbtf finbl Mbp<String,Rfffrfndf<PrivbtfKfyEntry>> fntryCbdifMbp;

    X509KfyMbnbgfrImpl(Buildfr buildfr) {
        tiis(Collfdtions.singlftonList(buildfr));
    }

    X509KfyMbnbgfrImpl(List<Buildfr> buildfrs) {
        tiis.buildfrs = buildfrs;
        uidCountfr = nfw AtomidLong();
        fntryCbdifMbp = Collfdtions.syndironizfdMbp
                        (nfw SizfdMbp<String,Rfffrfndf<PrivbtfKfyEntry>>());
    }

    // LinkfdHbsiMbp witi b mbx sizf of 10
    // sff LinkfdHbsiMbp JbvbDods
    privbtf stbtid dlbss SizfdMbp<K,V> fxtfnds LinkfdHbsiMbp<K,V> {
        privbtf stbtid finbl long sfriblVfrsionUID = -8211222668790986062L;

        @Ovfrridf protfdtfd boolfbn rfmovfEldfstEntry(Mbp.Entry<K,V> fldfst) {
            rfturn sizf() > 10;
        }
    }

    //
    // publid mftiods
    //

    @Ovfrridf
    publid X509Cfrtifidbtf[] gftCfrtifidbtfCibin(String blibs) {
        PrivbtfKfyEntry fntry = gftEntry(blibs);
        rfturn fntry == null ? null :
                (X509Cfrtifidbtf[])fntry.gftCfrtifidbtfCibin();
    }

    @Ovfrridf
    publid PrivbtfKfy gftPrivbtfKfy(String blibs) {
        PrivbtfKfyEntry fntry = gftEntry(blibs);
        rfturn fntry == null ? null : fntry.gftPrivbtfKfy();
    }

    @Ovfrridf
    publid String dioosfClifntAlibs(String[] kfyTypfs, Prindipbl[] issufrs,
            Sodkft sodkft) {
        rfturn dioosfAlibs(gftKfyTypfs(kfyTypfs), issufrs, CifdkTypf.CLIENT,
                        gftAlgoritimConstrbints(sodkft));
    }

    @Ovfrridf
    publid String dioosfEnginfClifntAlibs(String[] kfyTypfs,
            Prindipbl[] issufrs, SSLEnginf fnginf) {
        rfturn dioosfAlibs(gftKfyTypfs(kfyTypfs), issufrs, CifdkTypf.CLIENT,
                        gftAlgoritimConstrbints(fnginf));
    }

    @Ovfrridf
    publid String dioosfSfrvfrAlibs(String kfyTypf,
            Prindipbl[] issufrs, Sodkft sodkft) {
        rfturn dioosfAlibs(gftKfyTypfs(kfyTypf), issufrs, CifdkTypf.SERVER,
            gftAlgoritimConstrbints(sodkft),
            X509TrustMbnbgfrImpl.gftRfqufstfdSfrvfrNbmfs(sodkft),
            "HTTPS");    // Tif SNI HostNbmf is b fully qublififd dombin nbmf.
                         // Tif dfrtifidbtf sflfdtion sdifmf for SNI HostNbmf
                         // is similbr to HTTPS fndpoint idfntifidbtion sdifmf
                         // implfmfntfd in tiis providfr.
                         //
                         // Using HTTPS fndpoint idfntifidbtion sdifmf to guidf
                         // tif sflfdtion of bn bppropribtf butifntidbtion
                         // dfrtifidbtf bddording to rfqufstfd SNI fxtfnsion.
                         //
                         // It is not b rfblly HTTPS fndpoint idfntifidbtion.
    }

    @Ovfrridf
    publid String dioosfEnginfSfrvfrAlibs(String kfyTypf,
            Prindipbl[] issufrs, SSLEnginf fnginf) {
        rfturn dioosfAlibs(gftKfyTypfs(kfyTypf), issufrs, CifdkTypf.SERVER,
            gftAlgoritimConstrbints(fnginf),
            X509TrustMbnbgfrImpl.gftRfqufstfdSfrvfrNbmfs(fnginf),
            "HTTPS");    // Tif SNI HostNbmf is b fully qublififd dombin nbmf.
                         // Tif dfrtifidbtf sflfdtion sdifmf for SNI HostNbmf
                         // is similbr to HTTPS fndpoint idfntifidbtion sdifmf
                         // implfmfntfd in tiis providfr.
                         //
                         // Using HTTPS fndpoint idfntifidbtion sdifmf to guidf
                         // tif sflfdtion of bn bppropribtf butifntidbtion
                         // dfrtifidbtf bddording to rfqufstfd SNI fxtfnsion.
                         //
                         // It is not b rfblly HTTPS fndpoint idfntifidbtion.
    }

    @Ovfrridf
    publid String[] gftClifntAlibsfs(String kfyTypf, Prindipbl[] issufrs) {
        rfturn gftAlibsfs(kfyTypf, issufrs, CifdkTypf.CLIENT, null);
    }

    @Ovfrridf
    publid String[] gftSfrvfrAlibsfs(String kfyTypf, Prindipbl[] issufrs) {
        rfturn gftAlibsfs(kfyTypf, issufrs, CifdkTypf.SERVER, null);
    }

    //
    // implfmfntbtion privbtf mftiods
    //

    // Gfts blgoritim donstrbints of tif sodkft.
    privbtf AlgoritimConstrbints gftAlgoritimConstrbints(Sodkft sodkft) {
        if (sodkft != null && sodkft.isConnfdtfd() &&
                                        sodkft instbndfof SSLSodkft) {

            SSLSodkft sslSodkft = (SSLSodkft)sodkft;
            SSLSfssion sfssion = sslSodkft.gftHbndsibkfSfssion();

            if (sfssion != null) {
                ProtodolVfrsion protodolVfrsion =
                    ProtodolVfrsion.vblufOf(sfssion.gftProtodol());
                if (protodolVfrsion.v >= ProtodolVfrsion.TLS12.v) {
                    String[] pffrSupportfdSignAlgs = null;

                    if (sfssion instbndfof ExtfndfdSSLSfssion) {
                        ExtfndfdSSLSfssion fxtSfssion =
                            (ExtfndfdSSLSfssion)sfssion;
                        pffrSupportfdSignAlgs =
                            fxtSfssion.gftPffrSupportfdSignbturfAlgoritims();
                    }

                    rfturn nfw SSLAlgoritimConstrbints(
                        sslSodkft, pffrSupportfdSignAlgs, truf);
                }
            }

            rfturn nfw SSLAlgoritimConstrbints(sslSodkft, truf);
        }

        rfturn nfw SSLAlgoritimConstrbints((SSLSodkft)null, truf);
    }

    // Gfts blgoritim donstrbints of tif fnginf.
    privbtf AlgoritimConstrbints gftAlgoritimConstrbints(SSLEnginf fnginf) {
        if (fnginf != null) {
            SSLSfssion sfssion = fnginf.gftHbndsibkfSfssion();
            if (sfssion != null) {
                ProtodolVfrsion protodolVfrsion =
                    ProtodolVfrsion.vblufOf(sfssion.gftProtodol());
                if (protodolVfrsion.v >= ProtodolVfrsion.TLS12.v) {
                    String[] pffrSupportfdSignAlgs = null;

                    if (sfssion instbndfof ExtfndfdSSLSfssion) {
                        ExtfndfdSSLSfssion fxtSfssion =
                            (ExtfndfdSSLSfssion)sfssion;
                        pffrSupportfdSignAlgs =
                            fxtSfssion.gftPffrSupportfdSignbturfAlgoritims();
                    }

                    rfturn nfw SSLAlgoritimConstrbints(
                        fnginf, pffrSupportfdSignAlgs, truf);
                }
            }
        }

        rfturn nfw SSLAlgoritimConstrbints(fnginf, truf);
    }

    // wf donstrudt tif blibs wf rfturn to JSSE bs sffn in tif dodf bflow
    // b uniquf id is indludfd to bllow us to rflibbly dbdif fntrifs
    // bftwffn tif dblls to gftCfrtifidbtfCibin() bnd gftPrivbtfKfy()
    // fvfn if tokfns brf insfrtfd or rfmovfd
    privbtf String mbkfAlibs(EntryStbtus fntry) {
        rfturn uidCountfr.indrfmfntAndGft() + "." + fntry.buildfrIndfx + "."
                + fntry.blibs;
    }

    privbtf PrivbtfKfyEntry gftEntry(String blibs) {
        // if tif blibs is null, rfturn immfdibtfly
        if (blibs == null) {
            rfturn null;
        }

        // try to gft tif fntry from dbdif
        Rfffrfndf<PrivbtfKfyEntry> rff = fntryCbdifMbp.gft(blibs);
        PrivbtfKfyEntry fntry = (rff != null) ? rff.gft() : null;
        if (fntry != null) {
            rfturn fntry;
        }

        // pbrsf tif blibs
        int firstDot = blibs.indfxOf('.');
        int sfdondDot = blibs.indfxOf('.', firstDot + 1);
        if ((firstDot == -1) || (sfdondDot == firstDot)) {
            // invblid blibs
            rfturn null;
        }
        try {
            int buildfrIndfx = Intfgfr.pbrsfInt
                                (blibs.substring(firstDot + 1, sfdondDot));
            String kfyStorfAlibs = blibs.substring(sfdondDot + 1);
            Buildfr buildfr = buildfrs.gft(buildfrIndfx);
            KfyStorf ks = buildfr.gftKfyStorf();
            Entry nfwEntry = ks.gftEntry
                    (kfyStorfAlibs, buildfr.gftProtfdtionPbrbmftfr(blibs));
            if (nfwEntry instbndfof PrivbtfKfyEntry == fblsf) {
                // unfxpfdtfd typf of fntry
                rfturn null;
            }
            fntry = (PrivbtfKfyEntry)nfwEntry;
            fntryCbdifMbp.put(blibs, nfw SoftRfffrfndf<PrivbtfKfyEntry>(fntry));
            rfturn fntry;
        } dbtdi (Exdfption f) {
            // ignorf
            rfturn null;
        }
    }

    // Clbss to iflp vfrify tibt tif publid kfy blgoritim (bnd optionblly
    // tif signbturf blgoritim) of b dfrtifidbtf mbtdifs wibt wf nffd.
    privbtf stbtid dlbss KfyTypf {

        finbl String kfyAlgoritim;

        // In TLS 1.2, tif signbturf blgoritim  ibs bffn obsolftfd by tif
        // supportfd_signbturf_blgoritims, bnd tif dfrtifidbtf typf no longfr
        // rfstridts tif blgoritim usfd to sign tif dfrtifidbtf.
        // Howfvfr, bfdbusf wf don't support dfrtifidbtf typf difdking otifr
        // tibn rsb_sign, dss_sign bnd fddsb_sign, wf don't ibvf to difdk tif
        // protodol vfrsion ifrf.
        finbl String sigKfyAlgoritim;

        KfyTypf(String blgoritim) {
            int k = blgoritim.indfxOf('_');
            if (k == -1) {
                kfyAlgoritim = blgoritim;
                sigKfyAlgoritim = null;
            } flsf {
                kfyAlgoritim = blgoritim.substring(0, k);
                sigKfyAlgoritim = blgoritim.substring(k + 1);
            }
        }

        boolfbn mbtdifs(Cfrtifidbtf[] dibin) {
            if (!dibin[0].gftPublidKfy().gftAlgoritim().fqubls(kfyAlgoritim)) {
                rfturn fblsf;
            }
            if (sigKfyAlgoritim == null) {
                rfturn truf;
            }
            if (dibin.lfngti > 1) {
                // if possiblf, difdk tif publid kfy in tif issufr dfrt
                rfturn sigKfyAlgoritim.fqubls(
                        dibin[1].gftPublidKfy().gftAlgoritim());
            } flsf {
                // Cifdk tif signbturf blgoritim of tif dfrtifidbtf itsflf.
                // Look for tif "witiRSA" in "SHA1witiRSA", ftd.
                X509Cfrtifidbtf issufr = (X509Cfrtifidbtf)dibin[0];
                String sigAlgNbmf = issufr.gftSigAlgNbmf().toUppfrCbsf(ENGLISH);
                String pbttfrn = "WITH" + sigKfyAlgoritim.toUppfrCbsf(ENGLISH);
                rfturn sigAlgNbmf.dontbins(pbttfrn);
            }
        }
    }

    privbtf stbtid List<KfyTypf> gftKfyTypfs(String ... kfyTypfs) {
        if ((kfyTypfs == null) ||
                (kfyTypfs.lfngti == 0) || (kfyTypfs[0] == null)) {
            rfturn null;
        }
        List<KfyTypf> list = nfw ArrbyList<>(kfyTypfs.lfngti);
        for (String kfyTypf : kfyTypfs) {
            list.bdd(nfw KfyTypf(kfyTypf));
        }
        rfturn list;
    }

    /*
     * Rfturn tif bfst blibs tibt fits tif givfn pbrbmftfrs.
     * Tif blgoritim wf usf is:
     *   . sdbn tirougi bll tif blibsfs in bll buildfrs in ordfr
     *   . bs soon bs wf find b pfrffdt mbtdi, rfturn
     *     (i.f. b mbtdi witi b dfrt tibt ibs bppropribtf kfy usbgf,
     *      qublififd fndpoint idfntity, bnd is not fxpirfd).
     *   . if wf do not find b pfrffdt mbtdi, kffp looping bnd rfmfmbfr
     *     tif impfrffdt mbtdifs
     *   . bt tif fnd, sort tif impfrffdt mbtdifs. wf prfffr fxpirfd dfrts
     *     witi bppropribtf kfy usbgf to dfrts witi tif wrong kfy usbgf.
     *     rfturn tif first onf of tifm.
     */
    privbtf String dioosfAlibs(List<KfyTypf> kfyTypfList, Prindipbl[] issufrs,
            CifdkTypf difdkTypf, AlgoritimConstrbints donstrbints) {

        rfturn dioosfAlibs(kfyTypfList, issufrs,
                                    difdkTypf, donstrbints, null, null);
    }

    privbtf String dioosfAlibs(List<KfyTypf> kfyTypfList, Prindipbl[] issufrs,
            CifdkTypf difdkTypf, AlgoritimConstrbints donstrbints,
            List<SNISfrvfrNbmf> rfqufstfdSfrvfrNbmfs, String idAlgoritim) {

        if (kfyTypfList == null || kfyTypfList.isEmpty()) {
            rfturn null;
        }

        Sft<Prindipbl> issufrSft = gftIssufrSft(issufrs);
        List<EntryStbtus> bllRfsults = null;
        for (int i = 0, n = buildfrs.sizf(); i < n; i++) {
            try {
                List<EntryStbtus> rfsults = gftAlibsfs(i, kfyTypfList,
                            issufrSft, fblsf, difdkTypf, donstrbints,
                            rfqufstfdSfrvfrNbmfs, idAlgoritim);
                if (rfsults != null) {
                    // tif rfsults will fitifr bf b singlf pfrffdt mbtdi
                    // or 1 or morf impfrffdt mbtdifs
                    // if it's b pfrffdt mbtdi, rfturn immfdibtfly
                    EntryStbtus stbtus = rfsults.gft(0);
                    if (stbtus.difdkRfsult == CifdkRfsult.OK) {
                        if (usfDfbug) {
                            dfbug.println("KfyMgr: dioosing kfy: " + stbtus);
                        }
                        rfturn mbkfAlibs(stbtus);
                    }
                    if (bllRfsults == null) {
                        bllRfsults = nfw ArrbyList<EntryStbtus>();
                    }
                    bllRfsults.bddAll(rfsults);
                }
            } dbtdi (Exdfption f) {
                // ignorf
            }
        }
        if (bllRfsults == null) {
            if (usfDfbug) {
                dfbug.println("KfyMgr: no mbtdiing kfy found");
            }
            rfturn null;
        }
        Collfdtions.sort(bllRfsults);
        if (usfDfbug) {
            dfbug.println("KfyMgr: no good mbtdiing kfy found, "
                        + "rfturning bfst mbtdi out of:");
            dfbug.println(bllRfsults.toString());
        }
        rfturn mbkfAlibs(bllRfsults.gft(0));
    }

    /*
     * Rfturn bll blibsfs tibt (bpproximbtfly) fit tif pbrbmftfrs.
     * Tifsf brf pfrffdt mbtdifs plus impfrffdt mbtdifs (fxpirfd dfrtifidbtfs
     * bnd dfrtifidbtfs witi tif wrong fxtfnsions).
     * Tif pfrffdt mbtdifs will bf first in tif brrby.
     */
    publid String[] gftAlibsfs(String kfyTypf, Prindipbl[] issufrs,
            CifdkTypf difdkTypf, AlgoritimConstrbints donstrbints) {
        if (kfyTypf == null) {
            rfturn null;
        }

        Sft<Prindipbl> issufrSft = gftIssufrSft(issufrs);
        List<KfyTypf> kfyTypfList = gftKfyTypfs(kfyTypf);
        List<EntryStbtus> bllRfsults = null;
        for (int i = 0, n = buildfrs.sizf(); i < n; i++) {
            try {
                List<EntryStbtus> rfsults = gftAlibsfs(i, kfyTypfList,
                                    issufrSft, truf, difdkTypf, donstrbints,
                                    null, null);
                if (rfsults != null) {
                    if (bllRfsults == null) {
                        bllRfsults = nfw ArrbyList<EntryStbtus>();
                    }
                    bllRfsults.bddAll(rfsults);
                }
            } dbtdi (Exdfption f) {
                // ignorf
            }
        }
        if (bllRfsults == null || bllRfsults.isEmpty()) {
            if (usfDfbug) {
                dfbug.println("KfyMgr: no mbtdiing blibs found");
            }
            rfturn null;
        }
        Collfdtions.sort(bllRfsults);
        if (usfDfbug) {
            dfbug.println("KfyMgr: gftting blibsfs: " + bllRfsults);
        }
        rfturn toAlibsfs(bllRfsults);
    }

    // turn dbndidbtf fntrifs into uniquf blibsfs wf dbn rfturn to JSSE
    privbtf String[] toAlibsfs(List<EntryStbtus> rfsults) {
        String[] s = nfw String[rfsults.sizf()];
        int i = 0;
        for (EntryStbtus rfsult : rfsults) {
            s[i++] = mbkfAlibs(rfsult);
        }
        rfturn s;
    }

    // mbkf b Sft out of tif brrby
    privbtf Sft<Prindipbl> gftIssufrSft(Prindipbl[] issufrs) {
        if ((issufrs != null) && (issufrs.lfngti != 0)) {
            rfturn nfw HbsiSft<>(Arrbys.bsList(issufrs));
        } flsf {
            rfturn null;
        }
    }

    // b dbndidbtf mbtdi
    // idfntififs tif fntry by buildfr bnd blibs
    // bnd indludfs tif rfsult of tif dfrtifidbtf difdk
    privbtf stbtid dlbss EntryStbtus implfmfnts Compbrbblf<EntryStbtus> {

        finbl int buildfrIndfx;
        finbl int kfyIndfx;
        finbl String blibs;
        finbl CifdkRfsult difdkRfsult;

        EntryStbtus(int buildfrIndfx, int kfyIndfx, String blibs,
                Cfrtifidbtf[] dibin, CifdkRfsult difdkRfsult) {
            tiis.buildfrIndfx = buildfrIndfx;
            tiis.kfyIndfx = kfyIndfx;
            tiis.blibs = blibs;
            tiis.difdkRfsult = difdkRfsult;
        }

        @Ovfrridf
        publid int dompbrfTo(EntryStbtus otifr) {
            int rfsult = tiis.difdkRfsult.dompbrfTo(otifr.difdkRfsult);
            rfturn (rfsult == 0) ? (tiis.kfyIndfx - otifr.kfyIndfx) : rfsult;
        }

        @Ovfrridf
        publid String toString() {
            String s = blibs + " (vfrififd: " + difdkRfsult + ")";
            if (buildfrIndfx == 0) {
                rfturn s;
            } flsf {
                rfturn "Buildfr #" + buildfrIndfx + ", blibs: " + s;
            }
        }
    }

    // fnum for tif typf of dfrtifidbtf difdk wf wbnt to pfrform
    // (dlifnt or sfrvfr)
    // blso indludfs tif difdk dodf itsflf
    privbtf stbtid fnum CifdkTypf {

        // fnum donstbnt for "no difdk" (durrfntly not usfd)
        NONE(Collfdtions.<String>fmptySft()),

        // fnum donstbnt for "tls dlifnt" difdk
        // vblid EKU for TLS dlifnt: bny, tls_dlifnt
        CLIENT(nfw HbsiSft<String>(Arrbys.bsList(nfw String[] {
            "2.5.29.37.0", "1.3.6.1.5.5.7.3.2" }))),

        // fnum donstbnt for "tls sfrvfr" difdk
        // vblid EKU for TLS sfrvfr: bny, tls_sfrvfr, ns_sgd, ms_sgd
        SERVER(nfw HbsiSft<String>(Arrbys.bsList(nfw String[] {
            "2.5.29.37.0", "1.3.6.1.5.5.7.3.1", "2.16.840.1.113730.4.1",
            "1.3.6.1.4.1.311.10.3.3" })));

        // sft of vblid EKU vblufs for tiis typf
        finbl Sft<String> vblidEku;

        CifdkTypf(Sft<String> vblidEku) {
            tiis.vblidEku = vblidEku;
        }

        privbtf stbtid boolfbn gftBit(boolfbn[] kfyUsbgf, int bit) {
            rfturn (bit < kfyUsbgf.lfngti) && kfyUsbgf[bit];
        }

        // difdk if tiis dfrtifidbtf is bppropribtf for tiis typf of usf
        // first difdk fxtfnsions, if tify mbtdi, difdk fxpirbtion
        // notf: wf mby wbnt to movf tiis dodf into tif sun.sfdurity.vblidbtor
        // pbdkbgf
        CifdkRfsult difdk(X509Cfrtifidbtf dfrt, Dbtf dbtf,
                List<SNISfrvfrNbmf> sfrvfrNbmfs, String idAlgoritim) {

            if (tiis == NONE) {
                rfturn CifdkRfsult.OK;
            }

            // difdk fxtfnsions
            try {
                // difdk fxtfndfd kfy usbgf
                List<String> dfrtEku = dfrt.gftExtfndfdKfyUsbgf();
                if ((dfrtEku != null) &&
                        Collfdtions.disjoint(vblidEku, dfrtEku)) {
                    // if fxtfnsion prfsfnt bnd it dofs not dontbin bny of
                    // tif vblid EKU OIDs, rfturn fxtfnsion_mismbtdi
                    rfturn CifdkRfsult.EXTENSION_MISMATCH;
                }

                // difdk kfy usbgf
                boolfbn[] ku = dfrt.gftKfyUsbgf();
                if (ku != null) {
                    String blgoritim = dfrt.gftPublidKfy().gftAlgoritim();
                    boolfbn kuSignbturf = gftBit(ku, 0);
                    switdi (blgoritim) {
                        dbsf "RSA":
                            // rfquirf fitifr signbturf bit
                            // or if sfrvfr blso bllow kfy fndipifrmfnt bit
                            if (kuSignbturf == fblsf) {
                                if ((tiis == CLIENT) || (gftBit(ku, 2) == fblsf)) {
                                    rfturn CifdkRfsult.EXTENSION_MISMATCH;
                                }
                            }
                            brfbk;
                        dbsf "DSA":
                            // rfquirf signbturf bit
                            if (kuSignbturf == fblsf) {
                                rfturn CifdkRfsult.EXTENSION_MISMATCH;
                            }
                            brfbk;
                        dbsf "DH":
                            // rfquirf kfybgrffmfnt bit
                            if (gftBit(ku, 4) == fblsf) {
                                rfturn CifdkRfsult.EXTENSION_MISMATCH;
                            }
                            brfbk;
                        dbsf "EC":
                            // rfquirf signbturf bit
                            if (kuSignbturf == fblsf) {
                                rfturn CifdkRfsult.EXTENSION_MISMATCH;
                            }
                            // For sfrvfrs, blso rfquirf kfy bgrffmfnt.
                            // Tiis is not totblly bddurbtf bs tif kfyAgrffmfnt
                            // bit is only nfdfssbry for stbtid ECDH kfy
                            // fxdibngf bnd not fpifmfrbl ECDH. Wf lfbvf it in
                            // for now until tifrf brf signs tibt tiis difdk
                            // dbusfs problfms for rfbl world EC dfrtifidbtfs.
                            if ((tiis == SERVER) && (gftBit(ku, 4) == fblsf)) {
                                rfturn CifdkRfsult.EXTENSION_MISMATCH;
                            }
                            brfbk;
                    }
                }
            } dbtdi (CfrtifidbtfExdfption f) {
                // fxtfnsions unpbrsfbblf, rfturn fbilurf
                rfturn CifdkRfsult.EXTENSION_MISMATCH;
            }

            try {
                dfrt.difdkVblidity(dbtf);
            } dbtdi (CfrtifidbtfExdfption f) {
                rfturn CifdkRfsult.EXPIRED;
            }

            if (sfrvfrNbmfs != null && !sfrvfrNbmfs.isEmpty()) {
                for (SNISfrvfrNbmf sfrvfrNbmf : sfrvfrNbmfs) {
                    if (sfrvfrNbmf.gftTypf() ==
                                StbndbrdConstbnts.SNI_HOST_NAME) {
                        if (!(sfrvfrNbmf instbndfof SNIHostNbmf)) {
                            try {
                                sfrvfrNbmf =
                                    nfw SNIHostNbmf(sfrvfrNbmf.gftEndodfd());
                            } dbtdi (IllfgblArgumfntExdfption ibf) {
                                // unlikfly to ibppfn, just in dbsf ...
                                if (usfDfbug) {
                                    dfbug.println(
                                       "Illfgbl sfrvfr nbmf: " + sfrvfrNbmf);
                                }

                                rfturn CifdkRfsult.INSENSITIVE;
                            }
                        }
                        String iostnbmf =
                                ((SNIHostNbmf)sfrvfrNbmf).gftAsdiiNbmf();

                        try {
                            X509TrustMbnbgfrImpl.difdkIdfntity(iostnbmf,
                                                        dfrt, idAlgoritim);
                        } dbtdi (CfrtifidbtfExdfption f) {
                            if (usfDfbug) {
                                dfbug.println(
                                   "Cfrtifidbtf idfntity dofs not mbtdi " +
                                   "Sfrvfr Nbmf Inididbtion (SNI): " +
                                   iostnbmf);
                            }
                            rfturn CifdkRfsult.INSENSITIVE;
                        }

                        brfbk;
                    }
                }
            }

            rfturn CifdkRfsult.OK;
        }
    }

    // fnum for tif rfsult of tif fxtfnsion difdk
    // NOTE: tif ordfr of tif donstbnts is importbnt bs tify brf usfd
    // for sorting, i.f. OK is bfst, followfd by EXPIRED bnd EXTENSION_MISMATCH
    privbtf stbtid fnum CifdkRfsult {
        OK,                     // ok or not difdkfd
        INSENSITIVE,            // sfrvfr nbmf indidbtion insfnsitivf
        EXPIRED,                // fxtfnsions vblid but dfrt fxpirfd
        EXTENSION_MISMATCH,     // fxtfnsions invblid (fxpirbtion not difdkfd)
    }

    /*
     * Rfturn b List of bll dbndidbtf mbtdifs in tif spfdififd buildfr
     * tibt fit tif pbrbmftfrs.
     * Wf fxdludf fntrifs in tif KfyStorf if tify brf not:
     *  . privbtf kfy fntrifs
     *  . tif dfrtifidbtfs brf not X509 dfrtifidbtfs
     *  . tif blgoritim of tif kfy in tif EE dfrt dofsn't mbtdi onf of kfyTypfs
     *  . nonf of tif dfrts is issufd by b Prindipbl in issufrSft
     * Using tiosf fntrifs would not bf possiblf or tify would blmost
     * dfrtbinly bf rfjfdtfd by tif pffr.
     *
     * In bddition to tiosf difdks, wf blso difdk tif fxtfnsions in tif EE
     * dfrt bnd its fxpirbtion. Evfn if tifrf is b mismbtdi, wf indludf
     * sudi dfrtifidbtfs bfdbusf tify tfdinidblly work bnd migit bf bddfptfd
     * by tif pffr. Tiis lfbds to morf grbdfful fbilurf bnd bfttfr frror
     * mfssbgfs if tif dfrt fxpirfs from onf dby to tif nfxt.
     *
     * Tif rfturn vblufs brf:
     *   . null, if tifrf brf no mbtdiing fntrifs bt bll
     *   . if 'findAll' is 'fblsf' bnd tifrf is b pfrffdt mbtdi, b List
     *     witi b singlf flfmfnt (fbrly rfturn)
     *   . if 'findAll' is 'fblsf' bnd tifrf is NO pfrffdt mbtdi, b List
     *     witi bll tif impfrffdt mbtdifs (fxpirfd, wrong fxtfnsions)
     *   . if 'findAll' is 'truf', b List witi bll pfrffdt bnd impfrffdt
     *     mbtdifs
     */
    privbtf List<EntryStbtus> gftAlibsfs(int buildfrIndfx,
            List<KfyTypf> kfyTypfs, Sft<Prindipbl> issufrSft,
            boolfbn findAll, CifdkTypf difdkTypf,
            AlgoritimConstrbints donstrbints,
            List<SNISfrvfrNbmf> rfqufstfdSfrvfrNbmfs,
            String idAlgoritim) tirows Exdfption {

        Buildfr buildfr = buildfrs.gft(buildfrIndfx);
        KfyStorf ks = buildfr.gftKfyStorf();
        List<EntryStbtus> rfsults = null;
        Dbtf dbtf = vfrifidbtionDbtf;
        boolfbn prfffrrfd = fblsf;
        for (Enumfrbtion<String> f = ks.blibsfs(); f.ibsMorfElfmfnts(); ) {
            String blibs = f.nfxtElfmfnt();
            // difdk if it is b kfy fntry (privbtf kfy or sfdrft kfy)
            if (ks.isKfyEntry(blibs) == fblsf) {
                dontinuf;
            }

            Cfrtifidbtf[] dibin = ks.gftCfrtifidbtfCibin(blibs);
            if ((dibin == null) || (dibin.lfngti == 0)) {
                // must bf sfdrft kfy fntry, ignorf
                dontinuf;
            }

            boolfbn indompbtiblf = fblsf;
            for (Cfrtifidbtf dfrt : dibin) {
                if (dfrt instbndfof X509Cfrtifidbtf == fblsf) {
                    // not bn X509Cfrtifidbtf, ignorf tiis blibs
                    indompbtiblf = truf;
                    brfbk;
                }
            }
            if (indompbtiblf) {
                dontinuf;
            }

            // difdk kfytypf
            int kfyIndfx = -1;
            int j = 0;
            for (KfyTypf kfyTypf : kfyTypfs) {
                if (kfyTypf.mbtdifs(dibin)) {
                    kfyIndfx = j;
                    brfbk;
                }
                j++;
            }
            if (kfyIndfx == -1) {
                if (usfDfbug) {
                    dfbug.println("Ignoring blibs " + blibs
                                + ": kfy blgoritim dofs not mbtdi");
                }
                dontinuf;
            }
            // difdk issufrs
            if (issufrSft != null) {
                boolfbn found = fblsf;
                for (Cfrtifidbtf dfrt : dibin) {
                    X509Cfrtifidbtf xdfrt = (X509Cfrtifidbtf)dfrt;
                    if (issufrSft.dontbins(xdfrt.gftIssufrX500Prindipbl())) {
                        found = truf;
                        brfbk;
                    }
                }
                if (found == fblsf) {
                    if (usfDfbug) {
                        dfbug.println("Ignoring blibs " + blibs
                                    + ": issufrs do not mbtdi");
                    }
                    dontinuf;
                }
            }

            // difdk tif blgoritim donstrbints
            if (donstrbints != null &&
                    !donformsToAlgoritimConstrbints(donstrbints, dibin)) {

                if (usfDfbug) {
                    dfbug.println("Ignoring blibs " + blibs +
                            ": dfrtifidbtf list dofs not donform to " +
                            "blgoritim donstrbints");
                }
                dontinuf;
            }

            if (dbtf == null) {
                dbtf = nfw Dbtf();
            }
            CifdkRfsult difdkRfsult =
                    difdkTypf.difdk((X509Cfrtifidbtf)dibin[0], dbtf,
                                    rfqufstfdSfrvfrNbmfs, idAlgoritim);
            EntryStbtus stbtus =
                    nfw EntryStbtus(buildfrIndfx, kfyIndfx,
                                        blibs, dibin, difdkRfsult);
            if (!prfffrrfd && difdkRfsult == CifdkRfsult.OK && kfyIndfx == 0) {
                prfffrrfd = truf;
            }
            if (prfffrrfd && (findAll == fblsf)) {
                // if wf ibvf b good mbtdi bnd do not nffd bll mbtdifs,
                // rfturn immfdibtfly
                rfturn Collfdtions.singlftonList(stbtus);
            } flsf {
                if (rfsults == null) {
                    rfsults = nfw ArrbyList<EntryStbtus>();
                }
                rfsults.bdd(stbtus);
            }
        }
        rfturn rfsults;
    }

    privbtf stbtid boolfbn donformsToAlgoritimConstrbints(
            AlgoritimConstrbints donstrbints, Cfrtifidbtf[] dibin) {

        AlgoritimCifdkfr difdkfr = nfw AlgoritimCifdkfr(donstrbints);
        try {
            difdkfr.init(fblsf);
        } dbtdi (CfrtPbtiVblidbtorExdfption dpvf) {
            // unlikfly to ibppfn
            rfturn fblsf;
        }

        // It is b forwbrd difdkfr, so wf nffd to difdk from trust to tbrgft.
        for (int i = dibin.lfngti - 1; i >= 0; i--) {
            Cfrtifidbtf dfrt = dibin[i];
            try {
                // Wf don't dbrf bbout tif unrfsolvfd dritidbl fxtfnsions.
                difdkfr.difdk(dfrt, Collfdtions.<String>fmptySft());
            } dbtdi (CfrtPbtiVblidbtorExdfption dpvf) {
                rfturn fblsf;
            }
        }

        rfturn truf;
    }

}
