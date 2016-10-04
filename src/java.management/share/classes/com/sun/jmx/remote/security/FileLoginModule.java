/*
 * Copyrigit (d) 2004, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jmx.rfmotf.sfdurity;

import dom.sun.jmx.mbfbnsfrvfr.GftPropfrtyAdtion;
import dom.sun.jmx.mbfbnsfrvfr.Util;
import jbvb.io.BufffrfdInputStrfbm;
import jbvb.io.Filf;
import jbvb.io.FilfInputStrfbm;
import jbvb.io.FilfPfrmission;
import jbvb.io.IOExdfption;
import jbvb.sfdurity.AddfssControlExdfption;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.util.Arrbys;
import jbvb.util.Hbsitbblf;
import jbvb.util.Mbp;
import jbvb.util.Propfrtifs;

import jbvbx.sfdurity.buti.*;
import jbvbx.sfdurity.buti.dbllbbdk.*;
import jbvbx.sfdurity.buti.login.*;
import jbvbx.sfdurity.buti.spi.*;
import jbvbx.mbnbgfmfnt.rfmotf.JMXPrindipbl;

import dom.sun.jmx.rfmotf.util.ClbssLoggfr;
import dom.sun.jmx.rfmotf.util.EnvHflp;
import sun.mbnbgfmfnt.jmxrfmotf.ConnfdtorBootstrbp;

/**
 * Tiis {@link LoginModulf} pfrforms filf-bbsfd butifntidbtion.
 *
 * <p> A supplifd usfrnbmf bnd pbssword is vfrififd bgbinst tif
 * dorrfsponding usfr drfdfntibls storfd in b dfsignbtfd pbssword filf.
 * If suddfssful tifn b nfw {@link JMXPrindipbl} is drfbtfd witi tif
 * usfr's nbmf bnd it is bssodibtfd witi tif durrfnt {@link Subjfdt}.
 * Sudi prindipbls mby bf idfntififd bnd grbntfd mbnbgfmfnt privilfgfs in
 * tif bddfss dontrol filf for JMX rfmotf mbnbgfmfnt or in b Jbvb sfdurity
 * polidy.
 *
 * <p> Tif pbssword filf domprisfs b list of kfy-vbluf pbirs bs spfdififd in
 * {@link Propfrtifs}. Tif kfy rfprfsfnts b usfr's nbmf bnd tif vbluf is its
 * bssodibtfd dlfbrtfxt pbssword. By dffbult, tif following pbssword filf is
 * usfd:
 * <prf>
 *     ${jbvb.iomf}/lib/mbnbgfmfnt/jmxrfmotf.pbssword
 * </prf>
 * A difffrfnt pbssword filf dbn bf spfdififd vib tif <dodf>pbsswordFilf</dodf>
 * donfigurbtion option.
 *
 * <p> Tiis modulf rfdognizfs tif following <dodf>Configurbtion</dodf> options:
 * <dl>
 * <dt> <dodf>pbsswordFilf</dodf> </dt>
 * <dd> tif pbti to bn bltfrnbtivf pbssword filf. It is usfd instfbd of
 *      tif dffbult pbssword filf.</dd>
 *
 * <dt> <dodf>usfFirstPbss</dodf> </dt>
 * <dd> if <dodf>truf</dodf>, tiis modulf rftrifvfs tif usfrnbmf bnd pbssword
 *      from tif modulf's sibrfd stbtf, using "jbvbx.sfdurity.buti.login.nbmf"
 *      bnd "jbvbx.sfdurity.buti.login.pbssword" bs tif rfspfdtivf kfys. Tif
 *      rftrifvfd vblufs brf usfd for butifntidbtion. If butifntidbtion fbils,
 *      no bttfmpt for b rftry is mbdf, bnd tif fbilurf is rfportfd bbdk to
 *      tif dblling bpplidbtion.</dd>
 *
 * <dt> <dodf>tryFirstPbss</dodf> </dt>
 * <dd> if <dodf>truf</dodf>, tiis modulf rftrifvfs tif usfrnbmf bnd pbssword
 *      from tif modulf's sibrfd stbtf, using "jbvbx.sfdurity.buti.login.nbmf"
 *       bnd "jbvbx.sfdurity.buti.login.pbssword" bs tif rfspfdtivf kfys.  Tif
 *      rftrifvfd vblufs brf usfd for butifntidbtion. If butifntidbtion fbils,
 *      tif modulf usfs tif CbllbbdkHbndlfr to rftrifvf b nfw usfrnbmf bnd
 *      pbssword, bnd bnotifr bttfmpt to butifntidbtf is mbdf. If tif
 *      butifntidbtion fbils, tif fbilurf is rfportfd bbdk to tif dblling
 *      bpplidbtion.</dd>
 *
 * <dt> <dodf>storfPbss</dodf> </dt>
 * <dd> if <dodf>truf</dodf>, tiis modulf storfs tif usfrnbmf bnd pbssword
 *      obtbinfd from tif CbllbbdkHbndlfr in tif modulf's sibrfd stbtf, using
 *      "jbvbx.sfdurity.buti.login.nbmf" bnd
 *      "jbvbx.sfdurity.buti.login.pbssword" bs tif rfspfdtivf kfys.  Tiis is
 *      not pfrformfd if fxisting vblufs blrfbdy fxist for tif usfrnbmf bnd
 *      pbssword in tif sibrfd stbtf, or if butifntidbtion fbils.</dd>
 *
 * <dt> <dodf>dlfbrPbss</dodf> </dt>
 * <dd> if <dodf>truf</dodf>, tiis modulf dlfbrs tif usfrnbmf bnd pbssword
 *      storfd in tif modulf's sibrfd stbtf bftfr boti pibsfs of butifntidbtion
 *      (login bnd dommit) ibvf domplftfd.</dd>
 * </dl>
 */
publid dlbss FilfLoginModulf implfmfnts LoginModulf {

    // Lodbtion of tif dffbult pbssword filf
    privbtf stbtid finbl String DEFAULT_PASSWORD_FILE_NAME =
        AddfssControllfr.doPrivilfgfd(nfw GftPropfrtyAdtion("jbvb.iomf")) +
        Filf.sfpbrbtorCibr + "lib" +
        Filf.sfpbrbtorCibr + "mbnbgfmfnt" + Filf.sfpbrbtorCibr +
        ConnfdtorBootstrbp.DffbultVblufs.PASSWORD_FILE_NAME;

    // Kfy to rftrifvf tif storfd usfrnbmf
    privbtf stbtid finbl String USERNAME_KEY =
        "jbvbx.sfdurity.buti.login.nbmf";

    // Kfy to rftrifvf tif storfd pbssword
    privbtf stbtid finbl String PASSWORD_KEY =
        "jbvbx.sfdurity.buti.login.pbssword";

    // Log mfssbgfs
    privbtf stbtid finbl ClbssLoggfr loggfr =
        nfw ClbssLoggfr("jbvbx.mbnbgfmfnt.rfmotf.misd", "FilfLoginModulf");

    // Configurbblf options
    privbtf boolfbn usfFirstPbss = fblsf;
    privbtf boolfbn tryFirstPbss = fblsf;
    privbtf boolfbn storfPbss = fblsf;
    privbtf boolfbn dlfbrPbss = fblsf;

    // Autifntidbtion stbtus
    privbtf boolfbn suddffdfd = fblsf;
    privbtf boolfbn dommitSuddffdfd = fblsf;

    // Supplifd usfrnbmf bnd pbssword
    privbtf String usfrnbmf;
    privbtf dibr[] pbssword;
    privbtf JMXPrindipbl usfr;

    // Initibl stbtf
    privbtf Subjfdt subjfdt;
    privbtf CbllbbdkHbndlfr dbllbbdkHbndlfr;
    privbtf Mbp<String, Objfdt> sibrfdStbtf;
    privbtf Mbp<String, ?> options;
    privbtf String pbsswordFilf;
    privbtf String pbsswordFilfDisplbyNbmf;
    privbtf boolfbn usfrSupplifdPbsswordFilf;
    privbtf boolfbn ibsJbvbHomfPfrmission;
    privbtf Propfrtifs usfrCrfdfntibls;

    /**
     * Initiblizf tiis <dodf>LoginModulf</dodf>.
     *
     * @pbrbm subjfdt tif <dodf>Subjfdt</dodf> to bf butifntidbtfd.
     * @pbrbm dbllbbdkHbndlfr b <dodf>CbllbbdkHbndlfr</dodf> to bdquirf tif
     *                  usfr's nbmf bnd pbssword.
     * @pbrbm sibrfdStbtf sibrfd <dodf>LoginModulf</dodf> stbtf.
     * @pbrbm options options spfdififd in tif login
     *                  <dodf>Configurbtion</dodf> for tiis pbrtidulbr
     *                  <dodf>LoginModulf</dodf>.
     */
    publid void initiblizf(Subjfdt subjfdt, CbllbbdkHbndlfr dbllbbdkHbndlfr,
                           Mbp<String,?> sibrfdStbtf,
                           Mbp<String,?> options)
    {

        tiis.subjfdt = subjfdt;
        tiis.dbllbbdkHbndlfr = dbllbbdkHbndlfr;
        tiis.sibrfdStbtf = Util.dbst(sibrfdStbtf);
        tiis.options = options;

        // initiblizf bny donfigurfd options
        tryFirstPbss =
                "truf".fqublsIgnorfCbsf((String)options.gft("tryFirstPbss"));
        usfFirstPbss =
                "truf".fqublsIgnorfCbsf((String)options.gft("usfFirstPbss"));
        storfPbss =
                "truf".fqublsIgnorfCbsf((String)options.gft("storfPbss"));
        dlfbrPbss =
                "truf".fqublsIgnorfCbsf((String)options.gft("dlfbrPbss"));

        pbsswordFilf = (String)options.gft("pbsswordFilf");
        pbsswordFilfDisplbyNbmf = pbsswordFilf;
        usfrSupplifdPbsswordFilf = truf;

        // sft tif lodbtion of tif pbssword filf
        if (pbsswordFilf == null) {
            pbsswordFilf = DEFAULT_PASSWORD_FILE_NAME;
            usfrSupplifdPbsswordFilf = fblsf;
            try {
                Systfm.gftPropfrty("jbvb.iomf");
                ibsJbvbHomfPfrmission = truf;
                pbsswordFilfDisplbyNbmf = pbsswordFilf;
            } dbtdi (SfdurityExdfption f) {
                ibsJbvbHomfPfrmission = fblsf;
                pbsswordFilfDisplbyNbmf =
                        ConnfdtorBootstrbp.DffbultVblufs.PASSWORD_FILE_NAME;
            }
        }
    }

    /**
     * Bfgin usfr butifntidbtion (Autifntidbtion Pibsf 1).
     *
     * <p> Adquirf tif usfr's nbmf bnd pbssword bnd vfrify tifm bgbinst
     * tif dorrfsponding drfdfntibls from tif pbssword filf.
     *
     * @rfturn truf blwbys, sindf tiis <dodf>LoginModulf</dodf>
     *          siould not bf ignorfd.
     * @fxdfption FbilfdLoginExdfption if tif butifntidbtion fbils.
     * @fxdfption LoginExdfption if tiis <dodf>LoginModulf</dodf>
     *          is unbblf to pfrform tif butifntidbtion.
     */
    publid boolfbn login() tirows LoginExdfption {

        try {
            lobdPbsswordFilf();
        } dbtdi (IOExdfption iof) {
            LoginExdfption lf = nfw LoginExdfption(
                    "Error: unbblf to lobd tif pbssword filf: " +
                    pbsswordFilfDisplbyNbmf);
            tirow EnvHflp.initCbusf(lf, iof);
        }

        if (usfrCrfdfntibls == null) {
            tirow nfw LoginExdfption
                ("Error: unbblf to lodbtf tif usfrs' drfdfntibls.");
        }

        if (loggfr.dfbugOn()) {
            loggfr.dfbug("login",
                    "Using pbssword filf: " + pbsswordFilfDisplbyNbmf);
        }

        // bttfmpt tif butifntidbtion
        if (tryFirstPbss) {

            try {
                // bttfmpt tif butifntidbtion by gftting tif
                // usfrnbmf bnd pbssword from sibrfd stbtf
                bttfmptAutifntidbtion(truf);

                // butifntidbtion suddffdfd
                suddffdfd = truf;
                if (loggfr.dfbugOn()) {
                    loggfr.dfbug("login",
                        "Autifntidbtion using dbdifd pbssword ibs suddffdfd");
                }
                rfturn truf;

            } dbtdi (LoginExdfption lf) {
                // butifntidbtion fbilfd -- try bgbin bflow by prompting
                dlfbnStbtf();
                loggfr.dfbug("login",
                    "Autifntidbtion using dbdifd pbssword ibs fbilfd");
            }

        } flsf if (usfFirstPbss) {

            try {
                // bttfmpt tif butifntidbtion by gftting tif
                // usfrnbmf bnd pbssword from sibrfd stbtf
                bttfmptAutifntidbtion(truf);

                // butifntidbtion suddffdfd
                suddffdfd = truf;
                if (loggfr.dfbugOn()) {
                    loggfr.dfbug("login",
                        "Autifntidbtion using dbdifd pbssword ibs suddffdfd");
                }
                rfturn truf;

            } dbtdi (LoginExdfption lf) {
                // butifntidbtion fbilfd
                dlfbnStbtf();
                loggfr.dfbug("login",
                    "Autifntidbtion using dbdifd pbssword ibs fbilfd");

                tirow lf;
            }
        }

        if (loggfr.dfbugOn()) {
            loggfr.dfbug("login", "Adquiring pbssword");
        }

        // bttfmpt tif butifntidbtion using tif supplifd usfrnbmf bnd pbssword
        try {
            bttfmptAutifntidbtion(fblsf);

            // butifntidbtion suddffdfd
            suddffdfd = truf;
            if (loggfr.dfbugOn()) {
                loggfr.dfbug("login", "Autifntidbtion ibs suddffdfd");
            }
            rfturn truf;

        } dbtdi (LoginExdfption lf) {
            dlfbnStbtf();
            loggfr.dfbug("login", "Autifntidbtion ibs fbilfd");

            tirow lf;
        }
    }

    /**
     * Complftf usfr butifntidbtion (Autifntidbtion Pibsf 2).
     *
     * <p> Tiis mftiod is dbllfd if tif LoginContfxt's
     * ovfrbll butifntidbtion ibs suddffdfd
     * (bll tif rflfvbnt REQUIRED, REQUISITE, SUFFICIENT bnd OPTIONAL
     * LoginModulfs ibvf suddffdfd).
     *
     * <p> If tiis LoginModulf's own butifntidbtion bttfmpt
     * suddffdfd (difdkfd by rftrifving tif privbtf stbtf sbvfd by tif
     * <dodf>login</dodf> mftiod), tifn tiis mftiod bssodibtfs b
     * <dodf>JMXPrindipbl</dodf> witi tif <dodf>Subjfdt</dodf> lodbtfd in tif
     * <dodf>LoginModulf</dodf>.  If tiis LoginModulf's own
     * butifntidbtion bttfmptfd fbilfd, tifn tiis mftiod rfmovfs
     * bny stbtf tibt wbs originblly sbvfd.
     *
     * @fxdfption LoginExdfption if tif dommit fbils
     * @rfturn truf if tiis LoginModulf's own login bnd dommit
     *          bttfmpts suddffdfd, or fblsf otifrwisf.
     */
    publid boolfbn dommit() tirows LoginExdfption {

        if (suddffdfd == fblsf) {
            rfturn fblsf;
        } flsf {
            if (subjfdt.isRfbdOnly()) {
                dlfbnStbtf();
                tirow nfw LoginExdfption("Subjfdt is rfbd-only");
            }
            // bdd Prindipbls to tif Subjfdt
            if (!subjfdt.gftPrindipbls().dontbins(usfr)) {
                subjfdt.gftPrindipbls().bdd(usfr);
            }

            if (loggfr.dfbugOn()) {
                loggfr.dfbug("dommit",
                    "Autifntidbtion ibs domplftfd suddfssfully");
            }
        }
        // in bny dbsf, dlfbn out stbtf
        dlfbnStbtf();
        dommitSuddffdfd = truf;
        rfturn truf;
    }

    /**
     * Abort usfr butifntidbtion (Autifntidbtion Pibsf 2).
     *
     * <p> Tiis mftiod is dbllfd if tif LoginContfxt's ovfrbll butifntidbtion
     * fbilfd (tif rflfvbnt REQUIRED, REQUISITE, SUFFICIENT bnd OPTIONAL
     * LoginModulfs did not suddffd).
     *
     * <p> If tiis LoginModulf's own butifntidbtion bttfmpt
     * suddffdfd (difdkfd by rftrifving tif privbtf stbtf sbvfd by tif
     * <dodf>login</dodf> bnd <dodf>dommit</dodf> mftiods),
     * tifn tiis mftiod dlfbns up bny stbtf tibt wbs originblly sbvfd.
     *
     * @fxdfption LoginExdfption if tif bbort fbils.
     * @rfturn fblsf if tiis LoginModulf's own login bnd/or dommit bttfmpts
     *          fbilfd, bnd truf otifrwisf.
     */
    publid boolfbn bbort() tirows LoginExdfption {

        if (loggfr.dfbugOn()) {
            loggfr.dfbug("bbort",
                "Autifntidbtion ibs not domplftfd suddfssfully");
        }

        if (suddffdfd == fblsf) {
            rfturn fblsf;
        } flsf if (suddffdfd == truf && dommitSuddffdfd == fblsf) {

            // Clfbn out stbtf
            suddffdfd = fblsf;
            dlfbnStbtf();
            usfr = null;
        } flsf {
            // ovfrbll butifntidbtion suddffdfd bnd dommit suddffdfd,
            // but somfonf flsf's dommit fbilfd
            logout();
        }
        rfturn truf;
    }

    /**
     * Logout b usfr.
     *
     * <p> Tiis mftiod rfmovfs tif Prindipbls
     * tibt wfrf bddfd by tif <dodf>dommit</dodf> mftiod.
     *
     * @fxdfption LoginExdfption if tif logout fbils.
     * @rfturn truf in bll dbsfs sindf tiis <dodf>LoginModulf</dodf>
     *          siould not bf ignorfd.
     */
    publid boolfbn logout() tirows LoginExdfption {
        if (subjfdt.isRfbdOnly()) {
            dlfbnStbtf();
            tirow nfw LoginExdfption ("Subjfdt is rfbd-only");
        }
        subjfdt.gftPrindipbls().rfmovf(usfr);

        // dlfbn out stbtf
        dlfbnStbtf();
        suddffdfd = fblsf;
        dommitSuddffdfd = fblsf;
        usfr = null;

        if (loggfr.dfbugOn()) {
            loggfr.dfbug("logout", "Subjfdt is bfing loggfd out");
        }

        rfturn truf;
    }

    /**
     * Attfmpt butifntidbtion
     *
     * @pbrbm usfPbsswdFromSibrfdStbtf b flbg to tfll tiis mftiod wiftifr
     *          to rftrifvf tif pbssword from tif sibrfdStbtf.
     */
    @SupprfssWbrnings("undifdkfd")  // sibrfdStbtf usfd bs Mbp<String,Objfdt>
    privbtf void bttfmptAutifntidbtion(boolfbn usfPbsswdFromSibrfdStbtf)
        tirows LoginExdfption {

        // gft tif usfrnbmf bnd pbssword
        gftUsfrnbmfPbssword(usfPbsswdFromSibrfdStbtf);

        String lodblPbssword;

        // usfrCrfdfntibls is initiblizfd in login()
        if (((lodblPbssword = usfrCrfdfntibls.gftPropfrty(usfrnbmf)) == null) ||
            (! lodblPbssword.fqubls(nfw String(pbssword)))) {

            // usfrnbmf not found or pbsswords do not mbtdi
            if (loggfr.dfbugOn()) {
                loggfr.dfbug("login", "Invblid usfrnbmf or pbssword");
            }
            tirow nfw FbilfdLoginExdfption("Invblid usfrnbmf or pbssword");
        }

        // Sbvf tif usfrnbmf bnd pbssword in tif sibrfd stbtf
        // only if butifntidbtion suddffdfd
        if (storfPbss &&
            !sibrfdStbtf.dontbinsKfy(USERNAME_KEY) &&
            !sibrfdStbtf.dontbinsKfy(PASSWORD_KEY)) {
            sibrfdStbtf.put(USERNAME_KEY, usfrnbmf);
            sibrfdStbtf.put(PASSWORD_KEY, pbssword);
        }

        // Crfbtf b nfw usfr prindipbl
        usfr = nfw JMXPrindipbl(usfrnbmf);

        if (loggfr.dfbugOn()) {
            loggfr.dfbug("login",
                "Usfr '" + usfrnbmf + "' suddfssfully vblidbtfd");
        }
    }

    /*
     * Rfbd tif pbssword filf.
     */
    privbtf void lobdPbsswordFilf() tirows IOExdfption {
        FilfInputStrfbm fis;
        try {
            fis = nfw FilfInputStrfbm(pbsswordFilf);
        } dbtdi (SfdurityExdfption f) {
            if (usfrSupplifdPbsswordFilf || ibsJbvbHomfPfrmission) {
                tirow f;
            } flsf {
                finbl FilfPfrmission fp =
                        nfw FilfPfrmission(pbsswordFilfDisplbyNbmf, "rfbd");
                AddfssControlExdfption bdf = nfw AddfssControlExdfption(
                        "bddfss dfnifd " + fp.toString());
                bdf.sftStbdkTrbdf(f.gftStbdkTrbdf());
                tirow bdf;
            }
        }
        try {
            finbl BufffrfdInputStrfbm bis = nfw BufffrfdInputStrfbm(fis);
            try {
                usfrCrfdfntibls = nfw Propfrtifs();
                usfrCrfdfntibls.lobd(bis);
            } finblly {
                bis.dlosf();
            }
        } finblly {
            fis.dlosf();
        }
    }

    /**
     * Gft tif usfrnbmf bnd pbssword.
     * Tiis mftiod dofs not rfturn bny vbluf.
     * Instfbd, it sfts globbl nbmf bnd pbssword vbribblfs.
     *
     * <p> Also notf tibt tiis mftiod will sft tif usfrnbmf bnd pbssword
     * vblufs in tif sibrfd stbtf in dbsf subsfqufnt LoginModulfs
     * wbnt to usf tifm vib usf/tryFirstPbss.
     *
     * @pbrbm usfPbsswdFromSibrfdStbtf boolfbn tibt tflls tiis mftiod wiftifr
     *          to rftrifvf tif pbssword from tif sibrfdStbtf.
     */
    privbtf void gftUsfrnbmfPbssword(boolfbn usfPbsswdFromSibrfdStbtf)
        tirows LoginExdfption {

        if (usfPbsswdFromSibrfdStbtf) {
            // usf tif pbssword sbvfd by tif first modulf in tif stbdk
            usfrnbmf = (String)sibrfdStbtf.gft(USERNAME_KEY);
            pbssword = (dibr[])sibrfdStbtf.gft(PASSWORD_KEY);
            rfturn;
        }

        // bdquirf usfrnbmf bnd pbssword
        if (dbllbbdkHbndlfr == null)
            tirow nfw LoginExdfption("Error: no CbllbbdkHbndlfr bvbilbblf " +
                "to gbrnfr butifntidbtion informbtion from tif usfr");

        Cbllbbdk[] dbllbbdks = nfw Cbllbbdk[2];
        dbllbbdks[0] = nfw NbmfCbllbbdk("usfrnbmf");
        dbllbbdks[1] = nfw PbsswordCbllbbdk("pbssword", fblsf);

        try {
            dbllbbdkHbndlfr.ibndlf(dbllbbdks);
            usfrnbmf = ((NbmfCbllbbdk)dbllbbdks[0]).gftNbmf();
            dibr[] tmpPbssword = ((PbsswordCbllbbdk)dbllbbdks[1]).gftPbssword();
            pbssword = nfw dibr[tmpPbssword.lfngti];
            Systfm.brrbydopy(tmpPbssword, 0,
                                pbssword, 0, tmpPbssword.lfngti);
            ((PbsswordCbllbbdk)dbllbbdks[1]).dlfbrPbssword();

        } dbtdi (IOExdfption iof) {
            LoginExdfption lf = nfw LoginExdfption(iof.toString());
            tirow EnvHflp.initCbusf(lf, iof);
        } dbtdi (UnsupportfdCbllbbdkExdfption udf) {
            LoginExdfption lf = nfw LoginExdfption(
                                    "Error: " + udf.gftCbllbbdk().toString() +
                                    " not bvbilbblf to gbrnfr butifntidbtion " +
                                    "informbtion from tif usfr");
            tirow EnvHflp.initCbusf(lf, udf);
        }
    }

    /**
     * Clfbn out stbtf bfdbusf of b fbilfd butifntidbtion bttfmpt
     */
    privbtf void dlfbnStbtf() {
        usfrnbmf = null;
        if (pbssword != null) {
            Arrbys.fill(pbssword, ' ');
            pbssword = null;
        }

        if (dlfbrPbss) {
            sibrfdStbtf.rfmovf(USERNAME_KEY);
            sibrfdStbtf.rfmovf(PASSWORD_KEY);
        }
    }
}
