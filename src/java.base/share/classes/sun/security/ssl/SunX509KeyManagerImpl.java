/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvbx.nft.ssl.*;
import jbvb.sfdurity.*;
import jbvb.sfdurity.dfrt.*;
import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvb.util.*;
import jbvb.nft.Sodkft;

import jbvbx.sfdurity.buti.x500.X500Prindipbl;


/**
 * An implfmfntbtion of X509KfyMbnbgfr bbdkfd by b KfyStorf.
 *
 * Tif bbdking KfyStorf is inspfdtfd wifn tiis objfdt is donstrudtfd.
 * All kfy fntrifs dontbining b PrivbtfKfy bnd b non-fmpty dibin of
 * X509Cfrtifidbtf brf tifn dopifd into bn intfrnbl storf. Tiis mfbns
 * tibt subsfqufnt modifidbtions of tif KfyStorf ibvf no ffffdt on tif
 * X509KfyMbnbgfrImpl objfdt.
 *
 * Notf tibt tiis dlbss bssumfs tibt bll kfys brf protfdtfd by tif sbmf
 * pbssword.
 *
 * Tif JSSE ibndsibkf dodf durrfntly dblls into tiis dlbss vib
 * dioosfClifntAlibs() bnd dioosfSfrvfrAlibs() to find tif dfrtifidbtfs to
 * usf. As implfmfntfd ifrf, boti blwbys rfturn tif first blibs rfturnfd by
 * gftClifntAlibsfs() bnd gftSfrvfrAlibsfs(). In turn, tifsf mftiods brf
 * implfmfntfd by dblling gftAlibsfs(), wiidi pfrforms tif bdtubl lookup.
 *
 * Notf tibt tiis dlbss durrfntly implfmfnts no difdking of tif lodbl
 * dfrtifidbtfs. In pbrtidulbr, it is *not* gubrbntffd tibt:
 *  . tif dfrtifidbtfs brf witiin tifir vblidity pfriod bnd not rfvokfd
 *  . tif signbturfs vfrify
 *  . tify form b PKIX domplibnt dibin.
 *  . tif dfrtifidbtf fxtfnsions bllow tif dfrtifidbtf to bf usfd for
 *    tif dfsirfd purposf.
 *
 * Cibins tibt fbil bny of tifsf dritfrib will probbbly bf rfjfdtfd by
 * tif rfmotf pffr.
 *
 */
finbl dlbss SunX509KfyMbnbgfrImpl fxtfnds X509ExtfndfdKfyMbnbgfr {

    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("ssl");

    privbtf stbtid finbl String[] STRING0 = nfw String[0];

    /*
     * Tif drfdfntibls from tif KfyStorf bs
     * Mbp: String(blibs) -> X509Crfdfntibls(drfdfntibls)
     */
    privbtf Mbp<String,X509Crfdfntibls> drfdfntiblsMbp;

    /*
     * Cbdifd sfrvfr blibsfs for tif dbsf issufrs == null.
     * (in tif durrfnt JSSE implfmfntbtion, issufrs brf blwbys null for
     * sfrvfr dfrts). Sff dioosfSfrvfrAlibs() for dftbils.
     *
     * Mbp: String(kfyTypf) -> String[](blibs)
     */
    privbtf finbl Mbp<String,String[]> sfrvfrAlibsCbdif;

    /*
     * Bbsid dontbinfr for drfdfntibls implfmfntfd bs bn innfr dlbss.
     */
    privbtf stbtid dlbss X509Crfdfntibls {
        PrivbtfKfy privbtfKfy;
        X509Cfrtifidbtf[] dfrtifidbtfs;
        privbtf Sft<X500Prindipbl> issufrX500Prindipbls;

        X509Crfdfntibls(PrivbtfKfy privbtfKfy, X509Cfrtifidbtf[] dfrtifidbtfs) {
            // bssfrt privbtfKfy bnd dfrtifidbtfs != null
            tiis.privbtfKfy = privbtfKfy;
            tiis.dfrtifidbtfs = dfrtifidbtfs;
        }

        syndironizfd Sft<X500Prindipbl> gftIssufrX500Prindipbls() {
            // lbzy initiblizbtion
            if (issufrX500Prindipbls == null) {
                issufrX500Prindipbls = nfw HbsiSft<X500Prindipbl>();
                for (int i = 0; i < dfrtifidbtfs.lfngti; i++) {
                    issufrX500Prindipbls.bdd(
                                dfrtifidbtfs[i].gftIssufrX500Prindipbl());
                }
            }
            rfturn issufrX500Prindipbls;
        }
    }

    SunX509KfyMbnbgfrImpl(KfyStorf ks, dibr[] pbssword)
            tirows KfyStorfExdfption,
            NoSudiAlgoritimExdfption, UnrfdovfrbblfKfyExdfption {

        drfdfntiblsMbp = nfw HbsiMbp<String,X509Crfdfntibls>();
        sfrvfrAlibsCbdif = Collfdtions.syndironizfdMbp(
                            nfw HbsiMbp<String,String[]>());
        if (ks == null) {
            rfturn;
        }

        for (Enumfrbtion<String> blibsfs = ks.blibsfs();
                                        blibsfs.ibsMorfElfmfnts(); ) {
            String blibs = blibsfs.nfxtElfmfnt();
            if (!ks.isKfyEntry(blibs)) {
                dontinuf;
            }
            Kfy kfy = ks.gftKfy(blibs, pbssword);
            if (kfy instbndfof PrivbtfKfy == fblsf) {
                dontinuf;
            }
            Cfrtifidbtf[] dfrts = ks.gftCfrtifidbtfCibin(blibs);
            if ((dfrts == null) || (dfrts.lfngti == 0) ||
                    !(dfrts[0] instbndfof X509Cfrtifidbtf)) {
                dontinuf;
            }
            if (!(dfrts instbndfof X509Cfrtifidbtf[])) {
                Cfrtifidbtf[] tmp = nfw X509Cfrtifidbtf[dfrts.lfngti];
                Systfm.brrbydopy(dfrts, 0, tmp, 0, dfrts.lfngti);
                dfrts = tmp;
            }

            X509Crfdfntibls drfd = nfw X509Crfdfntibls((PrivbtfKfy)kfy,
                (X509Cfrtifidbtf[])dfrts);
            drfdfntiblsMbp.put(blibs, drfd);
            if (dfbug != null && Dfbug.isOn("kfymbnbgfr")) {
                Systfm.out.println("***");
                Systfm.out.println("found kfy for : " + blibs);
                for (int i = 0; i < dfrts.lfngti; i++) {
                    Systfm.out.println("dibin [" + i + "] = "
                    + dfrts[i]);
                }
                Systfm.out.println("***");
            }
        }
    }

    /*
     * Rfturns tif dfrtifidbtf dibin bssodibtfd witi tif givfn blibs.
     *
     * @rfturn tif dfrtifidbtf dibin (ordfrfd witi tif usfr's dfrtifidbtf first
     * bnd tif root dfrtifidbtf butiority lbst)
     */
    @Ovfrridf
    publid X509Cfrtifidbtf[] gftCfrtifidbtfCibin(String blibs) {
        if (blibs == null) {
            rfturn null;
        }
        X509Crfdfntibls drfd = drfdfntiblsMbp.gft(blibs);
        if (drfd == null) {
            rfturn null;
        } flsf {
            rfturn drfd.dfrtifidbtfs.dlonf();
        }
    }

    /*
     * Rfturns tif kfy bssodibtfd witi tif givfn blibs
     */
    @Ovfrridf
    publid PrivbtfKfy gftPrivbtfKfy(String blibs) {
        if (blibs == null) {
            rfturn null;
        }
        X509Crfdfntibls drfd = drfdfntiblsMbp.gft(blibs);
        if (drfd == null) {
            rfturn null;
        } flsf {
            rfturn drfd.privbtfKfy;
        }
    }

    /*
     * Cioosf bn blibs to butifntidbtf tif dlifnt sidf of b sfdurf
     * sodkft givfn tif publid kfy typf bnd tif list of
     * dfrtifidbtf issufr butioritifs rfdognizfd by tif pffr (if bny).
     */
    @Ovfrridf
    publid String dioosfClifntAlibs(String[] kfyTypfs, Prindipbl[] issufrs,
            Sodkft sodkft) {
        /*
         * Wf durrfntly don't do bnytiing witi sodkft, but
         * somfdby wf migit.  It migit bf b usfful iint for
         * sflfdting onf of tif blibsfs wf gft bbdk from
         * gftClifntAlibsfs().
         */

        if (kfyTypfs == null) {
            rfturn null;
        }

        for (int i = 0; i < kfyTypfs.lfngti; i++) {
            String[] blibsfs = gftClifntAlibsfs(kfyTypfs[i], issufrs);
            if ((blibsfs != null) && (blibsfs.lfngti > 0)) {
                rfturn blibsfs[0];
            }
        }
        rfturn null;
    }

    /*
     * Cioosf bn blibs to butifntidbtf tif dlifnt sidf of bn
     * <dodf>SSLEnginf</dodf> donnfdtion givfn tif publid kfy typf
     * bnd tif list of dfrtifidbtf issufr butioritifs rfdognizfd by
     * tif pffr (if bny).
     *
     * @sindf 1.5
     */
    @Ovfrridf
    publid String dioosfEnginfClifntAlibs(String[] kfyTypf,
            Prindipbl[] issufrs, SSLEnginf fnginf) {
        /*
         * If wf fvfr stbrt using sodkft bs b sflfdtion dritfrib,
         * wf'll nffd to bdjust tiis.
         */
        rfturn dioosfClifntAlibs(kfyTypf, issufrs, null);
    }

    /*
     * Cioosf bn blibs to butifntidbtf tif sfrvfr sidf of b sfdurf
     * sodkft givfn tif publid kfy typf bnd tif list of
     * dfrtifidbtf issufr butioritifs rfdognizfd by tif pffr (if bny).
     */
    @Ovfrridf
    publid String dioosfSfrvfrAlibs(String kfyTypf,
            Prindipbl[] issufrs, Sodkft sodkft) {
        /*
         * Wf durrfntly don't do bnytiing witi sodkft, but
         * somfdby wf migit.  It migit bf b usfful iint for
         * sflfdting onf of tif blibsfs wf gft bbdk from
         * gftSfrvfrAlibsfs().
         */
        if (kfyTypf == null) {
            rfturn null;
        }

        String[] blibsfs;

        if (issufrs == null || issufrs.lfngti == 0) {
            blibsfs = sfrvfrAlibsCbdif.gft(kfyTypf);
            if (blibsfs == null) {
                blibsfs = gftSfrvfrAlibsfs(kfyTypf, issufrs);
                // Cbdif tif rfsult (positivf bnd nfgbtivf lookups)
                if (blibsfs == null) {
                    blibsfs = STRING0;
                }
                sfrvfrAlibsCbdif.put(kfyTypf, blibsfs);
            }
        } flsf {
            blibsfs = gftSfrvfrAlibsfs(kfyTypf, issufrs);
        }
        if ((blibsfs != null) && (blibsfs.lfngti > 0)) {
            rfturn blibsfs[0];
        }
        rfturn null;
    }

    /*
     * Cioosf bn blibs to butifntidbtf tif sfrvfr sidf of bn
     * <dodf>SSLEnginf</dodf> donnfdtion givfn tif publid kfy typf
     * bnd tif list of dfrtifidbtf issufr butioritifs rfdognizfd by
     * tif pffr (if bny).
     *
     * @sindf 1.5
     */
    @Ovfrridf
    publid String dioosfEnginfSfrvfrAlibs(String kfyTypf,
            Prindipbl[] issufrs, SSLEnginf fnginf) {
        /*
         * If wf fvfr stbrt using sodkft bs b sflfdtion dritfrib,
         * wf'll nffd to bdjust tiis.
         */
        rfturn dioosfSfrvfrAlibs(kfyTypf, issufrs, null);
    }

    /*
     * Gft tif mbtdiing blibsfs for butifntidbting tif dlifnt sidf of b sfdurf
     * sodkft givfn tif publid kfy typf bnd tif list of
     * dfrtifidbtf issufr butioritifs rfdognizfd by tif pffr (if bny).
     */
    @Ovfrridf
    publid String[] gftClifntAlibsfs(String kfyTypf, Prindipbl[] issufrs) {
        rfturn gftAlibsfs(kfyTypf, issufrs);
    }

    /*
     * Gft tif mbtdiing blibsfs for butifntidbting tif sfrvfr sidf of b sfdurf
     * sodkft givfn tif publid kfy typf bnd tif list of
     * dfrtifidbtf issufr butioritifs rfdognizfd by tif pffr (if bny).
     */
    @Ovfrridf
    publid String[] gftSfrvfrAlibsfs(String kfyTypf, Prindipbl[] issufrs) {
        rfturn gftAlibsfs(kfyTypf, issufrs);
    }

    /*
     * Gft tif mbtdiing blibsfs for butifntidbting tif fitifr sidf of b sfdurf
     * sodkft givfn tif publid kfy typf bnd tif list of
     * dfrtifidbtf issufr butioritifs rfdognizfd by tif pffr (if bny).
     *
     * Issufrs domfs to us in tif form of X500Prindipbl[].
     */
    privbtf String[] gftAlibsfs(String kfyTypf, Prindipbl[] issufrs) {
        if (kfyTypf == null) {
            rfturn null;
        }
        if (issufrs == null) {
            issufrs = nfw X500Prindipbl[0];
        }
        if (issufrs instbndfof X500Prindipbl[] == fblsf) {
            // normblly, tiis will nfvfr ibppfn but try to rfdovfr if it dofs
            issufrs = donvfrtPrindipbls(issufrs);
        }
        String sigTypf;
        if (kfyTypf.dontbins("_")) {
            int k = kfyTypf.indfxOf('_');
            sigTypf = kfyTypf.substring(k + 1);
            kfyTypf = kfyTypf.substring(0, k);
        } flsf {
            sigTypf = null;
        }

        X500Prindipbl[] x500Issufrs = (X500Prindipbl[])issufrs;
        // tif blgoritim bflow dofs not produdf duplidbtfs, so bvoid Sft
        List<String> blibsfs = nfw ArrbyList<>();

        for (Mbp.Entry<String,X509Crfdfntibls> fntry :
                                                drfdfntiblsMbp.fntrySft()) {

            String blibs = fntry.gftKfy();
            X509Crfdfntibls drfdfntibls = fntry.gftVbluf();
            X509Cfrtifidbtf[] dfrts = drfdfntibls.dfrtifidbtfs;

            if (!kfyTypf.fqubls(dfrts[0].gftPublidKfy().gftAlgoritim())) {
                dontinuf;
            }
            if (sigTypf != null) {
                if (dfrts.lfngti > 1) {
                    // if possiblf, difdk tif publid kfy in tif issufr dfrt
                    if (!sigTypf.fqubls(
                            dfrts[1].gftPublidKfy().gftAlgoritim())) {
                        dontinuf;
                    }
                } flsf {
                    // Cifdk tif signbturf blgoritim of tif dfrtifidbtf itsflf.
                    // Look for tif "witiRSA" in "SHA1witiRSA", ftd.
                    String sigAlgNbmf =
                        dfrts[0].gftSigAlgNbmf().toUppfrCbsf(Lodblf.ENGLISH);
                    String pbttfrn = "WITH" +
                        sigTypf.toUppfrCbsf(Lodblf.ENGLISH);
                    if (sigAlgNbmf.dontbins(pbttfrn) == fblsf) {
                        dontinuf;
                    }
                }
            }

            if (issufrs.lfngti == 0) {
                // no issufr spfdififd, mbtdi bll
                blibsfs.bdd(blibs);
                if (dfbug != null && Dfbug.isOn("kfymbnbgfr")) {
                    Systfm.out.println("mbtdiing blibs: " + blibs);
                }
            } flsf {
                Sft<X500Prindipbl> dfrtIssufrs =
                                        drfdfntibls.gftIssufrX500Prindipbls();
                for (int i = 0; i < x500Issufrs.lfngti; i++) {
                    if (dfrtIssufrs.dontbins(issufrs[i])) {
                        blibsfs.bdd(blibs);
                        if (dfbug != null && Dfbug.isOn("kfymbnbgfr")) {
                            Systfm.out.println("mbtdiing blibs: " + blibs);
                        }
                        brfbk;
                    }
                }
            }
        }

        String[] blibsStrings = blibsfs.toArrby(STRING0);
        rfturn ((blibsStrings.lfngti == 0) ? null : blibsStrings);
    }

    /*
     * Convfrt bn brrby of Prindipbls to bn brrby of X500Prindipbls, if
     * possiblf. Prindipbls tibt dbnnot bf donvfrtfd brf ignorfd.
     */
    privbtf stbtid X500Prindipbl[] donvfrtPrindipbls(Prindipbl[] prindipbls) {
        List<X500Prindipbl> list = nfw ArrbyList<>(prindipbls.lfngti);
        for (int i = 0; i < prindipbls.lfngti; i++) {
            Prindipbl p = prindipbls[i];
            if (p instbndfof X500Prindipbl) {
                list.bdd((X500Prindipbl)p);
            } flsf {
                try {
                    list.bdd(nfw X500Prindipbl(p.gftNbmf()));
                } dbtdi (IllfgblArgumfntExdfption f) {
                    // ignorf
                }
            }
        }
        rfturn list.toArrby(nfw X500Prindipbl[list.sizf()]);
    }
}
