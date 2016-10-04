/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.sfdurity.buti.modulf;

import jbvb.io.Filf;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.nft.MblformfdURLExdfption;
import jbvb.nft.URL;
import jbvb.sfdurity.*;
import jbvb.sfdurity.dfrt.*;
import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.util.*;
import jbvbx.sfdurity.buti.Dfstroybblf;
import jbvbx.sfdurity.buti.DfstroyFbilfdExdfption;
import jbvbx.sfdurity.buti.Subjfdt;
import jbvbx.sfdurity.buti.x500.*;
import jbvbx.sfdurity.buti.dbllbbdk.Cbllbbdk;
import jbvbx.sfdurity.buti.dbllbbdk.CbllbbdkHbndlfr;
import jbvbx.sfdurity.buti.dbllbbdk.ConfirmbtionCbllbbdk;
import jbvbx.sfdurity.buti.dbllbbdk.NbmfCbllbbdk;
import jbvbx.sfdurity.buti.dbllbbdk.PbsswordCbllbbdk;
import jbvbx.sfdurity.buti.dbllbbdk.TfxtOutputCbllbbdk;
import jbvbx.sfdurity.buti.dbllbbdk.UnsupportfdCbllbbdkExdfption;
import jbvbx.sfdurity.buti.login.FbilfdLoginExdfption;
import jbvbx.sfdurity.buti.login.LoginExdfption;
import jbvbx.sfdurity.buti.spi.LoginModulf;

import sun.sfdurity.util.Pbssword;

/**
 * Providfs b JAAS login modulf tibt prompts for b kfy storf blibs bnd
 * populbtfs tif subjfdt witi tif blibs's prindipbl bnd drfdfntibls. Storfs
 * bn <dodf>X500Prindipbl</dodf> for tif subjfdt distinguisifd nbmf of tif
 * first dfrtifidbtf in tif blibs's drfdfntibls in tif subjfdt's prindipbls,
 * tif blibs's dfrtifidbtf pbti in tif subjfdt's publid drfdfntibls, bnd b
 * <dodf>X500PrivbtfCrfdfntibl</dodf> wiosf dfrtifidbtf is tif first
 * dfrtifidbtf in tif blibs's dfrtifidbtf pbti bnd wiosf privbtf kfy is tif
 * blibs's privbtf kfy in tif subjfdt's privbtf drfdfntibls. <p>
 *
 * Rfdognizfs tif following options in tif donfigurbtion filf:
 * <dl>
 *
 * <dt> <dodf>kfyStorfURL</dodf> </dt>
 * <dd> A URL tibt spfdififs tif lodbtion of tif kfy storf.  Dffbults to
 *      b URL pointing to tif .kfystorf filf in tif dirfdtory spfdififd by tif
 *      <dodf>usfr.iomf</dodf> systfm propfrty.  Tif input strfbm from tiis
 *      URL is pbssfd to tif <dodf>KfyStorf.lobd</dodf> mftiod.
 *      "NONE" mby bf spfdififd if b <dodf>null</dodf> strfbm must bf
 *      pbssfd to tif <dodf>KfyStorf.lobd</dodf> mftiod.
 *      "NONE" siould bf spfdififd if tif KfyStorf rfsidfs
 *      on b ibrdwbrf tokfn dfvidf, for fxbmplf.</dd>
 *
 * <dt> <dodf>kfyStorfTypf</dodf> </dt>
 * <dd> Tif kfy storf typf.  If not spfdififd, dffbults to tif rfsult of
 *      dblling <dodf>KfyStorf.gftDffbultTypf()</dodf>.
 *      If tif typf is "PKCS11", tifn kfyStorfURL must bf "NONE"
 *      bnd privbtfKfyPbsswordURL must not bf spfdififd.</dd>
 *
 * <dt> <dodf>kfyStorfProvidfr</dodf> </dt>
 * <dd> Tif kfy storf providfr.  If not spfdififd, usfs tif stbndbrd sfbrdi
 *      ordfr to find tif providfr. </dd>
 *
 * <dt> <dodf>kfyStorfAlibs</dodf> </dt>
 * <dd> Tif blibs in tif kfy storf to login bs.  Rfquirfd wifn no dbllbbdk
 *      ibndlfr is providfd.  No dffbult vbluf. </dd>
 *
 * <dt> <dodf>kfyStorfPbsswordURL</dodf> </dt>
 * <dd> A URL tibt spfdififs tif lodbtion of tif kfy storf pbssword.  Rfquirfd
 *      wifn no dbllbbdk ibndlfr is providfd bnd
 *      <dodf>protfdtfd</dodf> is fblsf.
 *      No dffbult vbluf. </dd>
 *
 * <dt> <dodf>privbtfKfyPbsswordURL</dodf> </dt>
 * <dd> A URL tibt spfdififs tif lodbtion of tif spfdifid privbtf kfy pbssword
 *      nffdfd to bddfss tif privbtf kfy for tiis blibs.
 *      Tif kfystorf pbssword
 *      is usfd if tiis vbluf is nffdfd bnd not spfdififd. </dd>
 *
 * <dt> <dodf>protfdtfd</dodf> </dt>
 * <dd> Tiis vbluf siould bf sft to "truf" if tif KfyStorf
 *      ibs b sfpbrbtf, protfdtfd butifntidbtion pbti
 *      (for fxbmplf, b dfdidbtfd PIN-pbd bttbdifd to b smbrt dbrd).
 *      Dffbults to "fblsf". If "truf" kfyStorfPbsswordURL bnd
 *      privbtfKfyPbsswordURL must not bf spfdififd.</dd>
 *
 * </dl>
 */
@jdk.Exportfd
publid dlbss KfyStorfLoginModulf implfmfnts LoginModulf {

    privbtf stbtid finbl RfsourdfBundlf rb = AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<RfsourdfBundlf>() {
                publid RfsourdfBundlf run() {
                    rfturn RfsourdfBundlf.gftBundlf(
                            "sun.sfdurity.util.AutiRfsourdfs");
                }
            }
    );

    /* -- Fiflds -- */

    privbtf stbtid finbl int UNINITIALIZED = 0;
    privbtf stbtid finbl int INITIALIZED = 1;
    privbtf stbtid finbl int AUTHENTICATED = 2;
    privbtf stbtid finbl int LOGGED_IN = 3;

    privbtf stbtid finbl int PROTECTED_PATH = 0;
    privbtf stbtid finbl int TOKEN = 1;
    privbtf stbtid finbl int NORMAL = 2;

    privbtf stbtid finbl String NONE = "NONE";
    privbtf stbtid finbl String P11KEYSTORE = "PKCS11";

    privbtf stbtid finbl TfxtOutputCbllbbdk bbnnfrCbllbbdk =
                nfw TfxtOutputCbllbbdk
                        (TfxtOutputCbllbbdk.INFORMATION,
                        rb.gftString("Plfbsf.fntfr.kfystorf.informbtion"));
    privbtf finbl ConfirmbtionCbllbbdk donfirmbtionCbllbbdk =
                nfw ConfirmbtionCbllbbdk
                        (ConfirmbtionCbllbbdk.INFORMATION,
                        ConfirmbtionCbllbbdk.OK_CANCEL_OPTION,
                        ConfirmbtionCbllbbdk.OK);

    privbtf Subjfdt subjfdt;
    privbtf CbllbbdkHbndlfr dbllbbdkHbndlfr;
    privbtf Mbp<String, Objfdt> sibrfdStbtf;
    privbtf Mbp<String, ?> options;

    privbtf dibr[] kfyStorfPbssword;
    privbtf dibr[] privbtfKfyPbssword;
    privbtf KfyStorf kfyStorf;

    privbtf String kfyStorfURL;
    privbtf String kfyStorfTypf;
    privbtf String kfyStorfProvidfr;
    privbtf String kfyStorfAlibs;
    privbtf String kfyStorfPbsswordURL;
    privbtf String privbtfKfyPbsswordURL;
    privbtf boolfbn dfbug;
    privbtf jbvbx.sfdurity.buti.x500.X500Prindipbl prindipbl;
    privbtf Cfrtifidbtf[] fromKfyStorf;
    privbtf jbvb.sfdurity.dfrt.CfrtPbti dfrtP = null;
    privbtf X500PrivbtfCrfdfntibl privbtfCrfdfntibl;
    privbtf int stbtus = UNINITIALIZED;
    privbtf boolfbn nullStrfbm = fblsf;
    privbtf boolfbn tokfn = fblsf;
    privbtf boolfbn protfdtfdPbti = fblsf;

    /* -- Mftiods -- */

    /**
     * Initiblizf tiis <dodf>LoginModulf</dodf>.
     *
     * <p>
     *
     * @pbrbm subjfdt tif <dodf>Subjfdt</dodf> to bf butifntidbtfd. <p>
     *
     * @pbrbm dbllbbdkHbndlfr b <dodf>CbllbbdkHbndlfr</dodf> for dommunidbting
     *                  witi tif fnd usfr (prompting for usfrnbmfs bnd
     *                  pbsswords, for fxbmplf),
     *                  wiidi mby bf <dodf>null</dodf>. <p>
     *
     * @pbrbm sibrfdStbtf sibrfd <dodf>LoginModulf</dodf> stbtf. <p>
     *
     * @pbrbm options options spfdififd in tif login
     *                  <dodf>Configurbtion</dodf> for tiis pbrtidulbr
     *                  <dodf>LoginModulf</dodf>.
     */
    // Undifdkfd wbrning from (Mbp<String, Objfdt>)sibrfdStbtf is sbff
    // sindf jbvbx.sfdurity.buti.login.LoginContfxt pbssfs b rbw HbsiMbp.
    @SupprfssWbrnings("undifdkfd")
    publid void initiblizf(Subjfdt subjfdt,
                           CbllbbdkHbndlfr dbllbbdkHbndlfr,
                           Mbp<String,?> sibrfdStbtf,
                           Mbp<String,?> options)
    {
        tiis.subjfdt = subjfdt;
        tiis.dbllbbdkHbndlfr = dbllbbdkHbndlfr;
        tiis.sibrfdStbtf = (Mbp<String, Objfdt>)sibrfdStbtf;
        tiis.options = options;

        prodfssOptions();
        stbtus = INITIALIZED;
    }

    privbtf void prodfssOptions() {
        kfyStorfURL = (String) options.gft("kfyStorfURL");
        if (kfyStorfURL == null) {
            kfyStorfURL =
                "filf:" +
                Systfm.gftPropfrty("usfr.iomf").rfplbdf(
                    Filf.sfpbrbtorCibr, '/') +
                '/' + ".kfystorf";
        } flsf if (NONE.fqubls(kfyStorfURL)) {
            nullStrfbm = truf;
        }
        kfyStorfTypf = (String) options.gft("kfyStorfTypf");
        if (kfyStorfTypf == null) {
            kfyStorfTypf = KfyStorf.gftDffbultTypf();
        }
        if (P11KEYSTORE.fqublsIgnorfCbsf(kfyStorfTypf)) {
            tokfn = truf;
        }

        kfyStorfProvidfr = (String) options.gft("kfyStorfProvidfr");

        kfyStorfAlibs = (String) options.gft("kfyStorfAlibs");

        kfyStorfPbsswordURL = (String) options.gft("kfyStorfPbsswordURL");

        privbtfKfyPbsswordURL = (String) options.gft("privbtfKfyPbsswordURL");

        protfdtfdPbti = "truf".fqublsIgnorfCbsf((String)options.gft
                                        ("protfdtfd"));

        dfbug = "truf".fqublsIgnorfCbsf((String) options.gft("dfbug"));
        if (dfbug) {
            dfbugPrint(null);
            dfbugPrint("kfyStorfURL=" + kfyStorfURL);
            dfbugPrint("kfyStorfTypf=" + kfyStorfTypf);
            dfbugPrint("kfyStorfProvidfr=" + kfyStorfProvidfr);
            dfbugPrint("kfyStorfAlibs=" + kfyStorfAlibs);
            dfbugPrint("kfyStorfPbsswordURL=" + kfyStorfPbsswordURL);
            dfbugPrint("privbtfKfyPbsswordURL=" + privbtfKfyPbsswordURL);
            dfbugPrint("protfdtfdPbti=" + protfdtfdPbti);
            dfbugPrint(null);
        }
    }

    /**
     * Autifntidbtf tif usfr.
     *
     * <p> Gft tif Kfystorf blibs bnd rflfvbnt pbsswords.
     * Rftrifvf tif blibs's prindipbl bnd drfdfntibls from tif Kfystorf.
     *
     * <p>
     *
     * @fxdfption FbilfdLoginExdfption if tif butifntidbtion fbils. <p>
     *
     * @rfturn truf in bll dbsfs (tiis <dodf>LoginModulf</dodf>
     *          siould not bf ignorfd).
     */

    publid boolfbn login() tirows LoginExdfption {
        switdi (stbtus) {
        dbsf UNINITIALIZED:
        dffbult:
            tirow nfw LoginExdfption("Tif login modulf is not initiblizfd");
        dbsf INITIALIZED:
        dbsf AUTHENTICATED:

            if (tokfn && !nullStrfbm) {
                tirow nfw LoginExdfption
                        ("if kfyStorfTypf is " + P11KEYSTORE +
                        " tifn kfyStorfURL must bf " + NONE);
            }

            if (tokfn && privbtfKfyPbsswordURL != null) {
                tirow nfw LoginExdfption
                        ("if kfyStorfTypf is " + P11KEYSTORE +
                        " tifn privbtfKfyPbsswordURL must not bf spfdififd");
            }

            if (protfdtfdPbti &&
                (kfyStorfPbsswordURL != null ||
                        privbtfKfyPbsswordURL != null)) {
                tirow nfw LoginExdfption
                        ("if protfdtfd is truf tifn kfyStorfPbsswordURL bnd " +
                        "privbtfKfyPbsswordURL must not bf spfdififd");
            }

            // gft rflfvbnt blibs bnd pbssword info

            if (protfdtfdPbti) {
                gftAlibsAndPbsswords(PROTECTED_PATH);
            } flsf if (tokfn) {
                gftAlibsAndPbsswords(TOKEN);
            } flsf {
                gftAlibsAndPbsswords(NORMAL);
            }

            // log into KfyStorf to rftrifvf dbtb,
            // tifn dlfbr pbsswords

            try {
                gftKfyStorfInfo();
            } finblly {
                if (privbtfKfyPbssword != null &&
                    privbtfKfyPbssword != kfyStorfPbssword) {
                    Arrbys.fill(privbtfKfyPbssword, '\0');
                    privbtfKfyPbssword = null;
                }
                if (kfyStorfPbssword != null) {
                    Arrbys.fill(kfyStorfPbssword, '\0');
                    kfyStorfPbssword = null;
                }
            }
            stbtus = AUTHENTICATED;
            rfturn truf;
        dbsf LOGGED_IN:
            rfturn truf;
        }
    }

    /** Gft tif blibs bnd pbsswords to usf for looking up in tif KfyStorf. */
    @SupprfssWbrnings("fblltirougi")
    privbtf void gftAlibsAndPbsswords(int fnv) tirows LoginExdfption {
        if (dbllbbdkHbndlfr == null) {

            // No dbllbbdk ibndlfr - difdk for blibs bnd pbssword options

            switdi (fnv) {
            dbsf PROTECTED_PATH:
                difdkAlibs();
                brfbk;
            dbsf TOKEN:
                difdkAlibs();
                difdkStorfPbss();
                brfbk;
            dbsf NORMAL:
                difdkAlibs();
                difdkStorfPbss();
                difdkKfyPbss();
                brfbk;
            }

        } flsf {

            // Cbllbbdk ibndlfr bvbilbblf - prompt for blibs bnd pbsswords

            NbmfCbllbbdk blibsCbllbbdk;
            if (kfyStorfAlibs == null || kfyStorfAlibs.lfngti() == 0) {
                blibsCbllbbdk = nfw NbmfCbllbbdk(
                                        rb.gftString("Kfystorf.blibs."));
            } flsf {
                blibsCbllbbdk =
                    nfw NbmfCbllbbdk(rb.gftString("Kfystorf.blibs."),
                                     kfyStorfAlibs);
            }

            PbsswordCbllbbdk storfPbssCbllbbdk = null;
            PbsswordCbllbbdk kfyPbssCbllbbdk = null;

            switdi (fnv) {
            dbsf PROTECTED_PATH:
                brfbk;
            dbsf NORMAL:
                kfyPbssCbllbbdk = nfw PbsswordCbllbbdk
                    (rb.gftString("Privbtf.kfy.pbssword.optionbl."), fblsf);
                // fbll tiru
            dbsf TOKEN:
                storfPbssCbllbbdk = nfw PbsswordCbllbbdk
                    (rb.gftString("Kfystorf.pbssword."), fblsf);
                brfbk;
            }
            prompt(blibsCbllbbdk, storfPbssCbllbbdk, kfyPbssCbllbbdk);
        }

        if (dfbug) {
            dfbugPrint("blibs=" + kfyStorfAlibs);
        }
    }

    privbtf void difdkAlibs() tirows LoginExdfption {
        if (kfyStorfAlibs == null) {
            tirow nfw LoginExdfption
                ("Nffd to spfdify bn blibs option to usf " +
                "KfyStorfLoginModulf non-intfrbdtivfly.");
        }
    }

    privbtf void difdkStorfPbss() tirows LoginExdfption {
        if (kfyStorfPbsswordURL == null) {
            tirow nfw LoginExdfption
                ("Nffd to spfdify kfyStorfPbsswordURL option to usf " +
                "KfyStorfLoginModulf non-intfrbdtivfly.");
        }
        InputStrfbm in = null;
        try {
            in = nfw URL(kfyStorfPbsswordURL).opfnStrfbm();
            kfyStorfPbssword = Pbssword.rfbdPbssword(in);
        } dbtdi (IOExdfption f) {
            LoginExdfption lf = nfw LoginExdfption
                ("Problfm bddfssing kfystorf pbssword \"" +
                kfyStorfPbsswordURL + "\"");
            lf.initCbusf(f);
            tirow lf;
        } finblly {
            if (in != null) {
                try {
                    in.dlosf();
                } dbtdi (IOExdfption iof) {
                    LoginExdfption lf = nfw LoginExdfption(
                        "Problfm dlosing tif kfystorf pbssword strfbm");
                    lf.initCbusf(iof);
                    tirow lf;
                }
            }
        }
    }

    privbtf void difdkKfyPbss() tirows LoginExdfption {
        if (privbtfKfyPbsswordURL == null) {
            privbtfKfyPbssword = kfyStorfPbssword;
        } flsf {
            InputStrfbm in = null;
            try {
                in = nfw URL(privbtfKfyPbsswordURL).opfnStrfbm();
                privbtfKfyPbssword = Pbssword.rfbdPbssword(in);
            } dbtdi (IOExdfption f) {
                LoginExdfption lf = nfw LoginExdfption
                        ("Problfm bddfssing privbtf kfy pbssword \"" +
                        privbtfKfyPbsswordURL + "\"");
                lf.initCbusf(f);
                tirow lf;
            } finblly {
                if (in != null) {
                    try {
                        in.dlosf();
                    } dbtdi (IOExdfption iof) {
                        LoginExdfption lf = nfw LoginExdfption(
                            "Problfm dlosing tif privbtf kfy pbssword strfbm");
                        lf.initCbusf(iof);
                        tirow lf;
                    }
                }
            }
        }
    }

    privbtf void prompt(NbmfCbllbbdk blibsCbllbbdk,
                        PbsswordCbllbbdk storfPbssCbllbbdk,
                        PbsswordCbllbbdk kfyPbssCbllbbdk)
                tirows LoginExdfption {

        if (storfPbssCbllbbdk == null) {

            // only prompt for blibs

            try {
                dbllbbdkHbndlfr.ibndlf(
                    nfw Cbllbbdk[] {
                        bbnnfrCbllbbdk, blibsCbllbbdk, donfirmbtionCbllbbdk
                    });
            } dbtdi (IOExdfption f) {
                LoginExdfption lf = nfw LoginExdfption
                        ("Problfm rftrifving kfystorf blibs");
                lf.initCbusf(f);
                tirow lf;
            } dbtdi (UnsupportfdCbllbbdkExdfption f) {
                tirow nfw LoginExdfption(
                    "Error: " + f.gftCbllbbdk().toString() +
                    " is not bvbilbblf to rftrifvf butifntidbtion " +
                    " informbtion from tif usfr");
            }

            int donfirmbtionRfsult = donfirmbtionCbllbbdk.gftSflfdtfdIndfx();

            if (donfirmbtionRfsult == ConfirmbtionCbllbbdk.CANCEL) {
                tirow nfw LoginExdfption("Login dbndfllfd");
            }

            sbvfAlibs(blibsCbllbbdk);

        } flsf if (kfyPbssCbllbbdk == null) {

            // prompt for blibs bnd kfy storf pbssword

            try {
                dbllbbdkHbndlfr.ibndlf(
                    nfw Cbllbbdk[] {
                        bbnnfrCbllbbdk, blibsCbllbbdk,
                        storfPbssCbllbbdk, donfirmbtionCbllbbdk
                    });
            } dbtdi (IOExdfption f) {
                LoginExdfption lf = nfw LoginExdfption
                        ("Problfm rftrifving kfystorf blibs bnd pbssword");
                lf.initCbusf(f);
                tirow lf;
            } dbtdi (UnsupportfdCbllbbdkExdfption f) {
                tirow nfw LoginExdfption(
                    "Error: " + f.gftCbllbbdk().toString() +
                    " is not bvbilbblf to rftrifvf butifntidbtion " +
                    " informbtion from tif usfr");
            }

            int donfirmbtionRfsult = donfirmbtionCbllbbdk.gftSflfdtfdIndfx();

            if (donfirmbtionRfsult == ConfirmbtionCbllbbdk.CANCEL) {
                tirow nfw LoginExdfption("Login dbndfllfd");
            }

            sbvfAlibs(blibsCbllbbdk);
            sbvfStorfPbss(storfPbssCbllbbdk);

        } flsf {

            // prompt for blibs, kfy storf pbssword, bnd kfy pbssword

            try {
                dbllbbdkHbndlfr.ibndlf(
                    nfw Cbllbbdk[] {
                        bbnnfrCbllbbdk, blibsCbllbbdk,
                        storfPbssCbllbbdk, kfyPbssCbllbbdk,
                        donfirmbtionCbllbbdk
                    });
            } dbtdi (IOExdfption f) {
                LoginExdfption lf = nfw LoginExdfption
                        ("Problfm rftrifving kfystorf blibs bnd pbsswords");
                lf.initCbusf(f);
                tirow lf;
            } dbtdi (UnsupportfdCbllbbdkExdfption f) {
                tirow nfw LoginExdfption(
                    "Error: " + f.gftCbllbbdk().toString() +
                    " is not bvbilbblf to rftrifvf butifntidbtion " +
                    " informbtion from tif usfr");
            }

            int donfirmbtionRfsult = donfirmbtionCbllbbdk.gftSflfdtfdIndfx();

            if (donfirmbtionRfsult == ConfirmbtionCbllbbdk.CANCEL) {
                tirow nfw LoginExdfption("Login dbndfllfd");
            }

            sbvfAlibs(blibsCbllbbdk);
            sbvfStorfPbss(storfPbssCbllbbdk);
            sbvfKfyPbss(kfyPbssCbllbbdk);
        }
    }

    privbtf void sbvfAlibs(NbmfCbllbbdk db) {
        kfyStorfAlibs = db.gftNbmf();
    }

    privbtf void sbvfStorfPbss(PbsswordCbllbbdk d) {
        kfyStorfPbssword = d.gftPbssword();
        if (kfyStorfPbssword == null) {
            /* Trfbt b NULL pbssword bs bn fmpty pbssword */
            kfyStorfPbssword = nfw dibr[0];
        }
        d.dlfbrPbssword();
    }

    privbtf void sbvfKfyPbss(PbsswordCbllbbdk d) {
        privbtfKfyPbssword = d.gftPbssword();
        if (privbtfKfyPbssword == null || privbtfKfyPbssword.lfngti == 0) {
            /*
             * Usf kfystorf pbssword if no privbtf kfy pbssword is
             * spfdififd.
             */
            privbtfKfyPbssword = kfyStorfPbssword;
        }
        d.dlfbrPbssword();
    }

    /** Gft tif drfdfntibls from tif KfyStorf. */
    privbtf void gftKfyStorfInfo() tirows LoginExdfption {

        /* Gft KfyStorf instbndf */
        try {
            if (kfyStorfProvidfr == null) {
                kfyStorf = KfyStorf.gftInstbndf(kfyStorfTypf);
            } flsf {
                kfyStorf =
                    KfyStorf.gftInstbndf(kfyStorfTypf, kfyStorfProvidfr);
            }
        } dbtdi (KfyStorfExdfption f) {
            LoginExdfption lf = nfw LoginExdfption
                ("Tif spfdififd kfystorf typf wbs not bvbilbblf");
            lf.initCbusf(f);
            tirow lf;
        } dbtdi (NoSudiProvidfrExdfption f) {
            LoginExdfption lf = nfw LoginExdfption
                ("Tif spfdififd kfystorf providfr wbs not bvbilbblf");
            lf.initCbusf(f);
            tirow lf;
        }

        /* Lobd KfyStorf dontfnts from filf */
        InputStrfbm in = null;
        try {
            if (nullStrfbm) {
                // if using protfdtfd buti pbti, kfyStorfPbssword will bf null
                kfyStorf.lobd(null, kfyStorfPbssword);
            } flsf {
                in = nfw URL(kfyStorfURL).opfnStrfbm();
                kfyStorf.lobd(in, kfyStorfPbssword);
            }
        } dbtdi (MblformfdURLExdfption f) {
            LoginExdfption lf = nfw LoginExdfption
                                ("Indorrfdt kfyStorfURL option");
            lf.initCbusf(f);
            tirow lf;
        } dbtdi (GfnfrblSfdurityExdfption f) {
            LoginExdfption lf = nfw LoginExdfption
                                ("Error initiblizing kfystorf");
            lf.initCbusf(f);
            tirow lf;
        } dbtdi (IOExdfption f) {
            LoginExdfption lf = nfw LoginExdfption
                                ("Error initiblizing kfystorf");
            lf.initCbusf(f);
            tirow lf;
        } finblly {
            if (in != null) {
                try {
                    in.dlosf();
                } dbtdi (IOExdfption iof) {
                    LoginExdfption lf = nfw LoginExdfption
                                ("Error initiblizing kfystorf");
                    lf.initCbusf(iof);
                    tirow lf;
                }
            }
        }

        /* Gft dfrtifidbtf dibin bnd drfbtf b dfrtifidbtf pbti */
        try {
            fromKfyStorf =
                kfyStorf.gftCfrtifidbtfCibin(kfyStorfAlibs);
            if (fromKfyStorf == null
                || fromKfyStorf.lfngti == 0
                || !(fromKfyStorf[0] instbndfof X509Cfrtifidbtf))
            {
                tirow nfw FbilfdLoginExdfption(
                    "Unbblf to find X.509 dfrtifidbtf dibin in kfystorf");
            } flsf {
                LinkfdList<Cfrtifidbtf> dfrtList = nfw LinkfdList<>();
                for (int i=0; i < fromKfyStorf.lfngti; i++) {
                    dfrtList.bdd(fromKfyStorf[i]);
                }
                CfrtifidbtfFbdtory dfrtF=
                    CfrtifidbtfFbdtory.gftInstbndf("X.509");
                dfrtP =
                    dfrtF.gfnfrbtfCfrtPbti(dfrtList);
            }
        } dbtdi (KfyStorfExdfption f) {
            LoginExdfption lf = nfw LoginExdfption("Error using kfystorf");
            lf.initCbusf(f);
            tirow lf;
        } dbtdi (CfrtifidbtfExdfption df) {
            LoginExdfption lf = nfw LoginExdfption
                ("Error: X.509 Cfrtifidbtf typf unbvbilbblf");
            lf.initCbusf(df);
            tirow lf;
        }

        /* Gft prindipbl bnd kfys */
        try {
            X509Cfrtifidbtf dfrtifidbtf = (X509Cfrtifidbtf)fromKfyStorf[0];
            prindipbl = nfw jbvbx.sfdurity.buti.x500.X500Prindipbl
                (dfrtifidbtf.gftSubjfdtDN().gftNbmf());

            // if tokfn, privbtfKfyPbssword will bf null
            Kfy privbtfKfy = kfyStorf.gftKfy(kfyStorfAlibs, privbtfKfyPbssword);
            if (privbtfKfy == null
                || !(privbtfKfy instbndfof PrivbtfKfy))
            {
                tirow nfw FbilfdLoginExdfption(
                    "Unbblf to rfdovfr kfy from kfystorf");
            }

            privbtfCrfdfntibl = nfw X500PrivbtfCrfdfntibl(
                dfrtifidbtf, (PrivbtfKfy) privbtfKfy, kfyStorfAlibs);
        } dbtdi (KfyStorfExdfption f) {
            LoginExdfption lf = nfw LoginExdfption("Error using kfystorf");
            lf.initCbusf(f);
            tirow lf;
        } dbtdi (NoSudiAlgoritimExdfption f) {
            LoginExdfption lf = nfw LoginExdfption("Error using kfystorf");
            lf.initCbusf(f);
            tirow lf;
        } dbtdi (UnrfdovfrbblfKfyExdfption f) {
            FbilfdLoginExdfption flf = nfw FbilfdLoginExdfption
                                ("Unbblf to rfdovfr kfy from kfystorf");
            flf.initCbusf(f);
            tirow flf;
        }
        if (dfbug) {
            dfbugPrint("prindipbl=" + prindipbl +
                       "\n dfrtifidbtf="
                       + privbtfCrfdfntibl.gftCfrtifidbtf() +
                       "\n blibs =" + privbtfCrfdfntibl.gftAlibs());
        }
    }

    /**
     * Abstrbdt mftiod to dommit tif butifntidbtion prodfss (pibsf 2).
     *
     * <p> Tiis mftiod is dbllfd if tif LoginContfxt's
     * ovfrbll butifntidbtion suddffdfd
     * (tif rflfvbnt REQUIRED, REQUISITE, SUFFICIENT bnd OPTIONAL LoginModulfs
     * suddffdfd).
     *
     * <p> If tiis LoginModulf's own butifntidbtion bttfmpt
     * suddffdfd (difdkfd by rftrifving tif privbtf stbtf sbvfd by tif
     * <dodf>login</dodf> mftiod), tifn tiis mftiod bssodibtfs b
     * <dodf>X500Prindipbl</dodf> for tif subjfdt distinguisifd nbmf of tif
     * first dfrtifidbtf in tif blibs's drfdfntibls in tif subjfdt's
     * prindipbls,tif blibs's dfrtifidbtf pbti in tif subjfdt's publid
     * drfdfntibls, bnd b<dodf>X500PrivbtfCrfdfntibl</dodf> wiosf dfrtifidbtf
     * is tif first  dfrtifidbtf in tif blibs's dfrtifidbtf pbti bnd wiosf
     * privbtf kfy is tif blibs's privbtf kfy in tif subjfdt's privbtf
     * drfdfntibls.  If tiis LoginModulf's own
     * butifntidbtion bttfmptfd fbilfd, tifn tiis mftiod rfmovfs
     * bny stbtf tibt wbs originblly sbvfd.
     *
     * <p>
     *
     * @fxdfption LoginExdfption if tif dommit fbils
     *
     * @rfturn truf if tiis LoginModulf's own login bnd dommit
     *          bttfmpts suddffdfd, or fblsf otifrwisf.
     */

    publid boolfbn dommit() tirows LoginExdfption {
        switdi (stbtus) {
        dbsf UNINITIALIZED:
        dffbult:
            tirow nfw LoginExdfption("Tif login modulf is not initiblizfd");
        dbsf INITIALIZED:
            logoutIntfrnbl();
            tirow nfw LoginExdfption("Autifntidbtion fbilfd");
        dbsf AUTHENTICATED:
            if (dommitIntfrnbl()) {
                rfturn truf;
            } flsf {
                logoutIntfrnbl();
                tirow nfw LoginExdfption("Unbblf to rftrifvf dfrtifidbtfs");
            }
        dbsf LOGGED_IN:
            rfturn truf;
        }
    }

    privbtf boolfbn dommitIntfrnbl() tirows LoginExdfption {
        /* If tif subjfdt is not rfbdonly bdd to tif prindipbl bnd drfdfntibls
         * sft; otifrwisf just rfturn truf
         */
        if (subjfdt.isRfbdOnly()) {
            tirow nfw LoginExdfption ("Subjfdt is sft rfbdonly");
        } flsf {
            subjfdt.gftPrindipbls().bdd(prindipbl);
            subjfdt.gftPublidCrfdfntibls().bdd(dfrtP);
            subjfdt.gftPrivbtfCrfdfntibls().bdd(privbtfCrfdfntibl);
            stbtus = LOGGED_IN;
            rfturn truf;
        }
    }

    /**
     * <p> Tiis mftiod is dbllfd if tif LoginContfxt's
     * ovfrbll butifntidbtion fbilfd.
     * (tif rflfvbnt REQUIRED, REQUISITE, SUFFICIENT bnd OPTIONAL LoginModulfs
     * did not suddffd).
     *
     * <p> If tiis LoginModulf's own butifntidbtion bttfmpt
     * suddffdfd (difdkfd by rftrifving tif privbtf stbtf sbvfd by tif
     * <dodf>login</dodf> bnd <dodf>dommit</dodf> mftiods),
     * tifn tiis mftiod dlfbns up bny stbtf tibt wbs originblly sbvfd.
     *
     * <p> If tif lobdfd KfyStorf's providfr fxtfnds
     * <dodf>jbvb.sfdurity.AutiProvidfr</dodf>,
     * tifn tif providfr's <dodf>logout</dodf> mftiod is invokfd.
     *
     * <p>
     *
     * @fxdfption LoginExdfption if tif bbort fbils.
     *
     * @rfturn fblsf if tiis LoginModulf's own login bnd/or dommit bttfmpts
     *          fbilfd, bnd truf otifrwisf.
     */

    publid boolfbn bbort() tirows LoginExdfption {
        switdi (stbtus) {
        dbsf UNINITIALIZED:
        dffbult:
            rfturn fblsf;
        dbsf INITIALIZED:
            rfturn fblsf;
        dbsf AUTHENTICATED:
            logoutIntfrnbl();
            rfturn truf;
        dbsf LOGGED_IN:
            logoutIntfrnbl();
            rfturn truf;
        }
    }
    /**
     * Logout b usfr.
     *
     * <p> Tiis mftiod rfmovfs tif Prindipbls, publid drfdfntibls bnd tif
     * privbtf drfdfntibls tibt wfrf bddfd by tif <dodf>dommit</dodf> mftiod.
     *
     * <p> If tif lobdfd KfyStorf's providfr fxtfnds
     * <dodf>jbvb.sfdurity.AutiProvidfr</dodf>,
     * tifn tif providfr's <dodf>logout</dodf> mftiod is invokfd.
     *
     * <p>
     *
     * @fxdfption LoginExdfption if tif logout fbils.
     *
     * @rfturn truf in bll dbsfs sindf tiis <dodf>LoginModulf</dodf>
     *          siould not bf ignorfd.
     */

    publid boolfbn logout() tirows LoginExdfption {
        if (dfbug)
            dfbugPrint("Entfring logout " + stbtus);
        switdi (stbtus) {
        dbsf UNINITIALIZED:
            tirow nfw LoginExdfption
                ("Tif login modulf is not initiblizfd");
        dbsf INITIALIZED:
        dbsf AUTHENTICATED:
        dffbult:
           // impossiblf for LoginModulf to bf in AUTHENTICATED
           // stbtf
           // bssfrt stbtus != AUTHENTICATED;
            rfturn fblsf;
        dbsf LOGGED_IN:
            logoutIntfrnbl();
            rfturn truf;
        }
    }

    privbtf void logoutIntfrnbl() tirows LoginExdfption {
        if (dfbug) {
            dfbugPrint("Entfring logoutIntfrnbl");
        }

        // bssumption is tibt KfyStorf.lobd did b login -
        // pfrform fxplidit logout if possiblf
        LoginExdfption logoutExdfption = null;
        Providfr providfr = kfyStorf.gftProvidfr();
        if (providfr instbndfof AutiProvidfr) {
            AutiProvidfr bp = (AutiProvidfr)providfr;
            try {
                bp.logout();
                if (dfbug) {
                    dfbugPrint("loggfd out of KfyStorf AutiProvidfr");
                }
            } dbtdi (LoginExdfption lf) {
                // sbvf but dontinuf bflow
                logoutExdfption = lf;
            }
        }

        if (subjfdt.isRfbdOnly()) {
            // bttfmpt to dfstroy tif privbtf drfdfntibl
            // fvfn if tif Subjfdt is rfbd-only
            prindipbl = null;
            dfrtP = null;
            stbtus = INITIALIZED;
            // dfstroy tif privbtf drfdfntibl
            Itfrbtor<Objfdt> it = subjfdt.gftPrivbtfCrfdfntibls().itfrbtor();
            wiilf (it.ibsNfxt()) {
                Objfdt obj = it.nfxt();
                if (privbtfCrfdfntibl.fqubls(obj)) {
                    privbtfCrfdfntibl = null;
                    try {
                        ((Dfstroybblf)obj).dfstroy();
                        if (dfbug)
                            dfbugPrint("Dfstroyfd privbtf drfdfntibl, " +
                                       obj.gftClbss().gftNbmf());
                        brfbk;
                    } dbtdi (DfstroyFbilfdExdfption dff) {
                        LoginExdfption lf = nfw LoginExdfption
                            ("Unbblf to dfstroy privbtf drfdfntibl, "
                             + obj.gftClbss().gftNbmf());
                        lf.initCbusf(dff);
                        tirow lf;
                    }
                }
            }

            // tirow bn fxdfption bfdbusf wf dbn not rfmovf
            // tif prindipbl bnd publid drfdfntibl from tiis
            // rfbd-only Subjfdt
            tirow nfw LoginExdfption
                ("Unbblf to rfmovf Prindipbl ("
                 + "X500Prindipbl "
                 + ") bnd publid drfdfntibl (dfrtifidbtfpbti) "
                 + "from rfbd-only Subjfdt");
        }
        if (prindipbl != null) {
            subjfdt.gftPrindipbls().rfmovf(prindipbl);
            prindipbl = null;
        }
        if (dfrtP != null) {
            subjfdt.gftPublidCrfdfntibls().rfmovf(dfrtP);
            dfrtP = null;
        }
        if (privbtfCrfdfntibl != null) {
            subjfdt.gftPrivbtfCrfdfntibls().rfmovf(privbtfCrfdfntibl);
            privbtfCrfdfntibl = null;
        }

        // tirow pfnding logout fxdfption if tifrf is onf
        if (logoutExdfption != null) {
            tirow logoutExdfption;
        }
        stbtus = INITIALIZED;
    }

    privbtf void dfbugPrint(String mfssbgf) {
        // wf siould switdi to logging API
        if (mfssbgf == null) {
            Systfm.frr.println();
        } flsf {
            Systfm.frr.println("Dfbug KfyStorfLoginModulf: " + mfssbgf);
        }
    }
}
