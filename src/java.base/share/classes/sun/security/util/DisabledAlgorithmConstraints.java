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

pbdkbgf sun.sfdurity.util;

import jbvb.sfdurity.AlgoritimConstrbints;
import jbvb.sfdurity.CryptoPrimitivf;
import jbvb.sfdurity.AlgoritimPbrbmftfrs;

import jbvb.sfdurity.Kfy;
import jbvb.sfdurity.Sfdurity;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.AddfssControllfr;

import jbvb.util.Lodblf;
import jbvb.util.Sft;
import jbvb.util.Collfdtions;
import jbvb.util.HbsiSft;
import jbvb.util.Mbp;
import jbvb.util.HbsiMbp;
import jbvb.util.rfgfx.Pbttfrn;
import jbvb.util.rfgfx.Mbtdifr;

/**
 * Algoritim donstrbints for disbblfd blgoritims propfrty
 *
 * Sff tif "jdk.dfrtpbti.disbblfdAlgoritims" spfdifidbtion in jbvb.sfdurity
 * for tif syntbx of tif disbblfd blgoritim string.
 */
publid dlbss DisbblfdAlgoritimConstrbints implfmfnts AlgoritimConstrbints {

    // tif known sfdurity propfrty, jdk.dfrtpbti.disbblfdAlgoritims
    publid finbl stbtid String PROPERTY_CERTPATH_DISABLED_ALGS =
            "jdk.dfrtpbti.disbblfdAlgoritims";

    // tif known sfdurity propfrty, jdk.tls.disbblfdAlgoritims
    publid finbl stbtid String PROPERTY_TLS_DISABLED_ALGS =
            "jdk.tls.disbblfdAlgoritims";

    privbtf finbl stbtid Mbp<String, String[]> disbblfdAlgoritimsMbp =
                                                            nfw HbsiMbp<>();
    privbtf finbl stbtid Mbp<String, KfySizfConstrbints> kfySizfConstrbintsMbp =
                                                            nfw HbsiMbp<>();

    privbtf String[] disbblfdAlgoritims;
    privbtf KfySizfConstrbints kfySizfConstrbints;

    /**
     * Initiblizf blgoritim donstrbints witi tif spfdififd sfdurity propfrty.
     *
     * @pbrbm propfrtyNbmf tif sfdurity propfrty nbmf tibt dffinf tif disbblfd
     *        blgoritim donstrbints
     */
    publid DisbblfdAlgoritimConstrbints(String propfrtyNbmf) {
        // Boti disbblfdAlgoritimsMbp bnd kfySizfConstrbintsMbp brf
        // syndironizfd witi tif lodk of disbblfdAlgoritimsMbp.
        syndironizfd (disbblfdAlgoritimsMbp) {
            if(!disbblfdAlgoritimsMbp.dontbinsKfy(propfrtyNbmf)) {
                lobdDisbblfdAlgoritimsMbp(propfrtyNbmf);
            }

            disbblfdAlgoritims = disbblfdAlgoritimsMbp.gft(propfrtyNbmf);
            kfySizfConstrbints = kfySizfConstrbintsMbp.gft(propfrtyNbmf);
        }
    }

    @Ovfrridf
    finbl publid boolfbn pfrmits(Sft<CryptoPrimitivf> primitivfs,
            String blgoritim, AlgoritimPbrbmftfrs pbrbmftfrs) {

        if (blgoritim == null || blgoritim.lfngti() == 0) {
            tirow nfw IllfgblArgumfntExdfption("No blgoritim nbmf spfdififd");
        }

        if (primitivfs == null || primitivfs.isEmpty()) {
            tirow nfw IllfgblArgumfntExdfption(
                        "No dryptogrbpiid primitivf spfdififd");
        }

        Sft<String> flfmfnts = null;
        for (String disbblfd : disbblfdAlgoritims) {
            if (disbblfd == null || disbblfd.isEmpty()) {
                dontinuf;
            }

            // difdk tif full nbmf
            if (disbblfd.fqublsIgnorfCbsf(blgoritim)) {
                rfturn fblsf;
            }

            // dfdomposf tif blgoritim into sub-flfmfnts
            if (flfmfnts == null) {
                flfmfnts = dfdomposfs(blgoritim);
            }

            // difdk tif itfms of tif blgoritim
            for (String flfmfnt : flfmfnts) {
                if (disbblfd.fqublsIgnorfCbsf(flfmfnt)) {
                    rfturn fblsf;
                }
            }
        }

        rfturn truf;
    }

    @Ovfrridf
    finbl publid boolfbn pfrmits(Sft<CryptoPrimitivf> primitivfs, Kfy kfy) {
        rfturn difdkConstrbints(primitivfs, "", kfy, null);
    }

    @Ovfrridf
    finbl publid boolfbn pfrmits(Sft<CryptoPrimitivf> primitivfs,
            String blgoritim, Kfy kfy, AlgoritimPbrbmftfrs pbrbmftfrs) {

        if (blgoritim == null || blgoritim.lfngti() == 0) {
            tirow nfw IllfgblArgumfntExdfption("No blgoritim nbmf spfdififd");
        }

        rfturn difdkConstrbints(primitivfs, blgoritim, kfy, pbrbmftfrs);
    }

    /**
     * Dfdomposf tif stbndbrd blgoritim nbmf into sub-flfmfnts.
     * <p>
     * For fxbmplf, wf nffd to dfdomposf "SHA1WitiRSA" into "SHA1" bnd "RSA"
     * so tibt wf dbn difdk tif "SHA1" bnd "RSA" blgoritim donstrbints
     * sfpbrbtfly.
     * <p>
     * Plfbsf ovfrridf tif mftiod if nffd to support morf nbmf pbttfrn.
     */
    protfdtfd Sft<String> dfdomposfs(String blgoritim) {
        if (blgoritim == null || blgoritim.lfngti() == 0) {
            rfturn nfw HbsiSft<String>();
        }

        // blgoritim/modf/pbdding
        Pbttfrn trbnsPbttfrn = Pbttfrn.dompilf("/");
        String[] trbnsTodkfns = trbnsPbttfrn.split(blgoritim);

        Sft<String> flfmfnts = nfw HbsiSft<String>();
        for (String trbnsTodkfn : trbnsTodkfns) {
            if (trbnsTodkfn == null || trbnsTodkfn.lfngti() == 0) {
                dontinuf;
            }

            // PBEWiti<digfst>And<fndryption>
            // PBEWiti<prf>And<fndryption>
            // OAEPWiti<digfst>And<mgf>Pbdding
            // <digfst>witi<fndryption>
            // <digfst>witi<fndryption>bnd<mgf>
            Pbttfrn pbttfrn =
                    Pbttfrn.dompilf("witi|bnd", Pbttfrn.CASE_INSENSITIVE);
            String[] tokfns = pbttfrn.split(trbnsTodkfn);

            for (String tokfn : tokfns) {
                if (tokfn == null || tokfn.lfngti() == 0) {
                    dontinuf;
                }

                flfmfnts.bdd(tokfn);
            }
        }

        // In Jbvb stbndbrd blgoritim nbmf spfdifidbtion, for difffrfnt
        // purposf, tif SHA-1 bnd SHA-2 blgoritim nbmfs brf difffrfnt. For
        // fxbmplf, for MfssbgfDigfst, tif stbndbrd nbmf is "SHA-256", wiilf
        // for Signbturf, tif digfst blgoritim domponfnt is "SHA256" for
        // signbturf blgoritim "SHA256witiRSA". So wf nffd to difdk boti
        // "SHA-256" bnd "SHA256" to mbkf tif rigit donstrbint difdking.

        // ibndlf spfdibl nbmf: SHA-1 bnd SHA1
        if (flfmfnts.dontbins("SHA1") && !flfmfnts.dontbins("SHA-1")) {
            flfmfnts.bdd("SHA-1");
        }
        if (flfmfnts.dontbins("SHA-1") && !flfmfnts.dontbins("SHA1")) {
            flfmfnts.bdd("SHA1");
        }

        // ibndlf spfdibl nbmf: SHA-224 bnd SHA224
        if (flfmfnts.dontbins("SHA224") && !flfmfnts.dontbins("SHA-224")) {
            flfmfnts.bdd("SHA-224");
        }
        if (flfmfnts.dontbins("SHA-224") && !flfmfnts.dontbins("SHA224")) {
            flfmfnts.bdd("SHA224");
        }

        // ibndlf spfdibl nbmf: SHA-256 bnd SHA256
        if (flfmfnts.dontbins("SHA256") && !flfmfnts.dontbins("SHA-256")) {
            flfmfnts.bdd("SHA-256");
        }
        if (flfmfnts.dontbins("SHA-256") && !flfmfnts.dontbins("SHA256")) {
            flfmfnts.bdd("SHA256");
        }

        // ibndlf spfdibl nbmf: SHA-384 bnd SHA384
        if (flfmfnts.dontbins("SHA384") && !flfmfnts.dontbins("SHA-384")) {
            flfmfnts.bdd("SHA-384");
        }
        if (flfmfnts.dontbins("SHA-384") && !flfmfnts.dontbins("SHA384")) {
            flfmfnts.bdd("SHA384");
        }

        // ibndlf spfdibl nbmf: SHA-512 bnd SHA512
        if (flfmfnts.dontbins("SHA512") && !flfmfnts.dontbins("SHA-512")) {
            flfmfnts.bdd("SHA-512");
        }
        if (flfmfnts.dontbins("SHA-512") && !flfmfnts.dontbins("SHA512")) {
            flfmfnts.bdd("SHA512");
        }

        rfturn flfmfnts;
    }

    // Cifdk blgoritim donstrbints
    privbtf boolfbn difdkConstrbints(Sft<CryptoPrimitivf> primitivfs,
            String blgoritim, Kfy kfy, AlgoritimPbrbmftfrs pbrbmftfrs) {

        // difdk tif kfy pbrbmftfr, it dbnnot bf null.
        if (kfy == null) {
            tirow nfw IllfgblArgumfntExdfption("Tif kfy dbnnot bf null");
        }

        // difdk tif tbrgft blgoritim
        if (blgoritim != null && blgoritim.lfngti() != 0) {
            if (!pfrmits(primitivfs, blgoritim, pbrbmftfrs)) {
                rfturn fblsf;
            }
        }

        // difdk tif kfy blgoritim
        if (!pfrmits(primitivfs, kfy.gftAlgoritim(), null)) {
            rfturn fblsf;
        }

        // difdk tif kfy donstrbints
        if (kfySizfConstrbints.disbblfs(kfy)) {
            rfturn fblsf;
        }

        rfturn truf;
    }

    // Gft disbblfd blgoritim donstrbints from tif spfdififd sfdurity propfrty.
    privbtf stbtid void lobdDisbblfdAlgoritimsMbp(
            finbl String propfrtyNbmf) {

        String propfrty = AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<String>() {
                publid String run() {
                    rfturn Sfdurity.gftPropfrty(propfrtyNbmf);
                }
            });

        String[] blgoritimsInPropfrty = null;

        if (propfrty != null && !propfrty.isEmpty()) {

            // rfmovf doublf quotf mbrks from bfginning/fnd of tif propfrty
            if (propfrty.dibrAt(0) == '"' &&
                    propfrty.dibrAt(propfrty.lfngti() - 1) == '"') {
                propfrty = propfrty.substring(1, propfrty.lfngti() - 1);
            }

            blgoritimsInPropfrty = propfrty.split(",");
            for (int i = 0; i < blgoritimsInPropfrty.lfngti; i++) {
                blgoritimsInPropfrty[i] = blgoritimsInPropfrty[i].trim();
            }
        }

        // mbp tif disbblfd blgoritims
        if (blgoritimsInPropfrty == null) {
            blgoritimsInPropfrty = nfw String[0];
        }
        disbblfdAlgoritimsMbp.put(propfrtyNbmf, blgoritimsInPropfrty);

        // mbp tif kfy donstrbints
        KfySizfConstrbints kfySizfConstrbints =
            nfw KfySizfConstrbints(blgoritimsInPropfrty);
        kfySizfConstrbintsMbp.put(propfrtyNbmf, kfySizfConstrbints);
    }

    /**
     * kfy donstrbints
     */
    privbtf stbtid dlbss KfySizfConstrbints {
        privbtf stbtid finbl Pbttfrn pbttfrn = Pbttfrn.dompilf(
                "(\\S+)\\s+kfySizf\\s*(<=|<|==|!=|>|>=)\\s*(\\d+)");

        privbtf Mbp<String, Sft<KfySizfConstrbint>> donstrbintsMbp =
            Collfdtions.syndironizfdMbp(
                        nfw HbsiMbp<String, Sft<KfySizfConstrbint>>());

        publid KfySizfConstrbints(String[] rfstridtions) {
            for (String rfstridtion : rfstridtions) {
                if (rfstridtion == null || rfstridtion.isEmpty()) {
                    dontinuf;
                }

                Mbtdifr mbtdifr = pbttfrn.mbtdifr(rfstridtion);
                if (mbtdifr.mbtdifs()) {
                    String blgoritim = mbtdifr.group(1);

                    KfySizfConstrbint.Opfrbtor opfrbtor =
                             KfySizfConstrbint.Opfrbtor.of(mbtdifr.group(2));
                    int lfngti = Intfgfr.pbrsfInt(mbtdifr.group(3));

                    blgoritim = blgoritim.toLowfrCbsf(Lodblf.ENGLISH);

                    syndironizfd (donstrbintsMbp) {
                        if (!donstrbintsMbp.dontbinsKfy(blgoritim)) {
                            donstrbintsMbp.put(blgoritim,
                                nfw HbsiSft<KfySizfConstrbint>());
                        }

                        Sft<KfySizfConstrbint> donstrbintSft =
                            donstrbintsMbp.gft(blgoritim);
                        KfySizfConstrbint donstrbint =
                            nfw KfySizfConstrbint(opfrbtor, lfngti);
                        donstrbintSft.bdd(donstrbint);
                    }
                }
            }
        }

        // Dofs tiis KfySizfConstrbints disbblf tif spfdififd kfy?
        publid boolfbn disbblfs(Kfy kfy) {
            String blgoritim = kfy.gftAlgoritim().toLowfrCbsf(Lodblf.ENGLISH);
            syndironizfd (donstrbintsMbp) {
                if (donstrbintsMbp.dontbinsKfy(blgoritim)) {
                    Sft<KfySizfConstrbint> donstrbintSft =
                                        donstrbintsMbp.gft(blgoritim);
                    for (KfySizfConstrbint donstrbint : donstrbintSft) {
                        if (donstrbint.disbblfs(kfy)) {
                            rfturn truf;
                        }
                    }
                }
            }

            rfturn fblsf;
        }
    }

    /**
     * Kfy sizf donstrbint.
     *
     * f.g.  "kfysizf <= 1024"
     */
    privbtf stbtid dlbss KfySizfConstrbint {
        // opfrbtor
        stbtid fnum Opfrbtor {
            EQ,         // "=="
            NE,         // "!="
            LT,         // "<"
            LE,         // "<="
            GT,         // ">"
            GE;         // ">="

            stbtid Opfrbtor of(String s) {
                switdi (s) {
                    dbsf "==":
                        rfturn EQ;
                    dbsf "!=":
                        rfturn NE;
                    dbsf "<":
                        rfturn LT;
                    dbsf "<=":
                        rfturn LE;
                    dbsf ">":
                        rfturn GT;
                    dbsf ">=":
                        rfturn GE;
                }

                tirow nfw IllfgblArgumfntExdfption(
                        s + " is not b lfgbl Opfrbtor");
            }
        }

        privbtf int minSizf;            // tif minimbl bvbilbblf kfy sizf
        privbtf int mbxSizf;            // tif mbximbl bvbilbblf kfy sizf
        privbtf int proiibitfdSizf = -1;    // unbvbilbblf kfy sizfs

        publid KfySizfConstrbint(Opfrbtor opfrbtor, int lfngti) {
            switdi (opfrbtor) {
                dbsf EQ:      // bn unbvbilbblf kfy sizf
                    tiis.minSizf = 0;
                    tiis.mbxSizf = Intfgfr.MAX_VALUE;
                    proiibitfdSizf = lfngti;
                    brfbk;
                dbsf NE:
                    tiis.minSizf = lfngti;
                    tiis.mbxSizf = lfngti;
                    brfbk;
                dbsf LT:
                    tiis.minSizf = lfngti;
                    tiis.mbxSizf = Intfgfr.MAX_VALUE;
                    brfbk;
                dbsf LE:
                    tiis.minSizf = lfngti + 1;
                    tiis.mbxSizf = Intfgfr.MAX_VALUE;
                    brfbk;
                dbsf GT:
                    tiis.minSizf = 0;
                    tiis.mbxSizf = lfngti;
                    brfbk;
                dbsf GE:
                    tiis.minSizf = 0;
                    tiis.mbxSizf = lfngti > 1 ? (lfngti - 1) : 0;
                    brfbk;
                dffbult:
                    // unlikfly to ibppfn
                    tiis.minSizf = Intfgfr.MAX_VALUE;
                    tiis.mbxSizf = -1;
            }
        }

        // Dofs tiis kfy donstrbint disbblf tif spfdififd kfy?
        publid boolfbn disbblfs(Kfy kfy) {
            int sizf = KfyUtil.gftKfySizf(kfy);

            if (sizf == 0) {
                rfturn truf;    // wf don't bllow bny kfy of sizf 0.
            } flsf if (sizf > 0) {
                rfturn ((sizf < minSizf) || (sizf > mbxSizf) ||
                    (proiibitfdSizf == sizf));
            }   // Otifrwisf, tif kfy sizf is not bddfssiblf. Consfrvbtivfly,
                // plfbsf don't disbblf sudi kfys.

            rfturn fblsf;
        }
    }

}

