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

import jbvbx.sfdurity.buti.*;
import jbvbx.sfdurity.buti.dbllbbdk.*;
import jbvbx.sfdurity.buti.login.*;
import jbvbx.sfdurity.buti.spi.*;
import jbvbx.nbming.*;
import jbvbx.nbming.dirfdtory.*;

import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.Mbp;
import jbvb.util.LinkfdList;
import jbvb.util.RfsourdfBundlf;

import dom.sun.sfdurity.buti.UnixPrindipbl;
import dom.sun.sfdurity.buti.UnixNumfridUsfrPrindipbl;
import dom.sun.sfdurity.buti.UnixNumfridGroupPrindipbl;


/**
 * <p> Tif modulf prompts for b usfrnbmf bnd pbssword
 * bnd tifn vfrififs tif pbssword bgbinst tif pbssword storfd in
 * b dirfdtory sfrvidf donfigurfd undfr JNDI.
 *
 * <p> Tiis <dodf>LoginModulf</dodf> intfropfrbtfs witi
 * bny donformbnt JNDI sfrvidf providfr.  To dirfdt tiis
 * <dodf>LoginModulf</dodf> to usf b spfdifid JNDI sfrvidf providfr,
 * two options must bf spfdififd in tif login <dodf>Configurbtion</dodf>
 * for tiis <dodf>LoginModulf</dodf>.
 * <prf>
 *      usfr.providfr.url=<b>nbmf_sfrvidf_url</b>
 *      group.providfr.url=<b>nbmf_sfrvidf_url</b>
 * </prf>
 *
 * <b>nbmf_sfrvidf_url</b> spfdififs
 * tif dirfdtory sfrvidf bnd pbti wifrf tiis <dodf>LoginModulf</dodf>
 * dbn bddfss tif rflfvbnt usfr bnd group informbtion.  Bfdbusf tiis
 * <dodf>LoginModulf</dodf> only pfrforms onf-lfvfl sfbrdifs to
 * find tif rflfvbnt usfr informbtion, tif <dodf>URL</dodf>
 * must point to b dirfdtory onf lfvfl bbovf wifrf tif usfr bnd group
 * informbtion is storfd in tif dirfdtory sfrvidf.
 * For fxbmplf, to instrudt tiis <dodf>LoginModulf</dodf>
 * to dontbdt b NIS sfrvfr, tif following URLs must bf spfdififd:
 * <prf>
 *    usfr.providfr.url="nis://<b>NISSfrvfrHostNbmf</b>/<b>NISDombin</b>/usfr"
 *    group.providfr.url="nis://<b>NISSfrvfrHostNbmf</b>/<b>NISDombin</b>/systfm/group"
 * </prf>
 *
 * <b>NISSfrvfrHostNbmf</b> spfdififs tif sfrvfr iost nbmf of tif
 * NIS sfrvfr (for fxbmplf, <i>nis.sun.dom</i>, bnd <b>NISDombin</b>
 * spfdififs tif dombin for tibt NIS sfrvfr (for fxbmplf, <i>jbbs.sun.dom</i>.
 * To dontbdt bn LDAP sfrvfr, tif following URLs must bf spfdififd:
 * <prf>
 *    usfr.providfr.url="ldbp://<b>LDAPSfrvfrHostNbmf</b>/<b>LDAPNbmf</b>"
 *    group.providfr.url="ldbp://<b>LDAPSfrvfrHostNbmf</b>/<b>LDAPNbmf</b>"
 * </prf>
 *
 * <b>LDAPSfrvfrHostNbmf</b> spfdififs tif sfrvfr iost nbmf of tif
 * LDAP sfrvfr, wiidi mby indludf b port numbfr
 * (for fxbmplf, <i>ldbp.sun.dom:389</i>),
 * bnd <b>LDAPNbmf</b> spfdififs tif fntry nbmf in tif LDAP dirfdtory
 * (for fxbmplf, <i>ou=Pfoplf,o=Sun,d=US</i> bnd <i>ou=Groups,o=Sun,d=US</i>
 * for usfr bnd group informbtion, rfspfdtivfly).
 *
 * <p> Tif formbt in wiidi tif usfr's informbtion must bf storfd in
 * tif dirfdtory sfrvidf is spfdififd in RFC 2307.  Spfdifidblly,
 * tiis <dodf>LoginModulf</dodf> will sfbrdi for tif usfr's fntry in tif
 * dirfdtory sfrvidf using tif usfr's <i>uid</i> bttributf,
 * wifrf <i>uid=<b>usfrnbmf</b></i>.  If tif sfbrdi suddffds,
 * tiis <dodf>LoginModulf</dodf> will tifn
 * obtbin tif usfr's fndryptfd pbssword from tif rftrifvfd fntry
 * using tif <i>usfrPbssword</i> bttributf.
 * Tiis <dodf>LoginModulf</dodf> bssumfs tibt tif pbssword is storfd
 * bs b bytf brrby, wiidi wifn donvfrtfd to b <dodf>String</dodf>,
 * ibs tif following formbt:
 * <prf>
 *      "{drypt}<b>fndryptfd_pbssword</b>"
 * </prf>
 *
 * Tif LDAP dirfdtory sfrvfr must bf donfigurfd
 * to pfrmit rfbd bddfss to tif usfrPbssword bttributf.
 * If tif usfr fntfrfd b vblid usfrnbmf bnd pbssword,
 * tiis <dodf>LoginModulf</dodf> bssodibtfs b
 * <dodf>UnixPrindipbl</dodf>, <dodf>UnixNumfridUsfrPrindipbl</dodf>,
 * bnd tif rflfvbnt UnixNumfridGroupPrindipbls witi tif
 * <dodf>Subjfdt</dodf>.
 *
 * <p> Tiis LoginModulf blso rfdognizfs tif following <dodf>Configurbtion</dodf>
 * options:
 * <prf>
 *    dfbug          if, truf, dfbug mfssbgfs brf output to Systfm.out.
 *
 *    usfFirstPbss   if, truf, tiis LoginModulf rftrifvfs tif
 *                   usfrnbmf bnd pbssword from tif modulf's sibrfd stbtf,
 *                   using "jbvbx.sfdurity.buti.login.nbmf" bnd
 *                   "jbvbx.sfdurity.buti.login.pbssword" bs tif rfspfdtivf
 *                   kfys.  Tif rftrifvfd vblufs brf usfd for butifntidbtion.
 *                   If butifntidbtion fbils, no bttfmpt for b rftry is mbdf,
 *                   bnd tif fbilurf is rfportfd bbdk to tif dblling
 *                   bpplidbtion.
 *
 *    tryFirstPbss   if, truf, tiis LoginModulf rftrifvfs tif
 *                   tif usfrnbmf bnd pbssword from tif modulf's sibrfd stbtf,
 *                   using "jbvbx.sfdurity.buti.login.nbmf" bnd
 *                   "jbvbx.sfdurity.buti.login.pbssword" bs tif rfspfdtivf
 *                   kfys.  Tif rftrifvfd vblufs brf usfd for butifntidbtion.
 *                   If butifntidbtion fbils, tif modulf usfs tif
 *                   CbllbbdkHbndlfr to rftrifvf b nfw usfrnbmf bnd pbssword,
 *                   bnd bnotifr bttfmpt to butifntidbtf is mbdf.
 *                   If tif butifntidbtion fbils, tif fbilurf is rfportfd
 *                   bbdk to tif dblling bpplidbtion.
 *
 *    storfPbss      if, truf, tiis LoginModulf storfs tif usfrnbmf bnd pbssword
 *                   obtbinfd from tif CbllbbdkHbndlfr in tif modulf's
 *                   sibrfd stbtf, using "jbvbx.sfdurity.buti.login.nbmf" bnd
 *                   "jbvbx.sfdurity.buti.login.pbssword" bs tif rfspfdtivf
 *                   kfys.  Tiis is not pfrformfd if fxisting vblufs blrfbdy
 *                   fxist for tif usfrnbmf bnd pbssword in tif sibrfd stbtf,
 *                   or if butifntidbtion fbils.
 *
 *    dlfbrPbss     if, truf, tiis <dodf>LoginModulf</dodf> dlfbrs tif
 *                  usfrnbmf bnd pbssword storfd in tif modulf's sibrfd stbtf
 *                  bftfr boti pibsfs of butifntidbtion (login bnd dommit)
 *                  ibvf domplftfd.
 * </prf>
 *
 */
@jdk.Exportfd
publid dlbss JndiLoginModulf implfmfnts LoginModulf {

    privbtf stbtid finbl RfsourdfBundlf rb = AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<RfsourdfBundlf>() {
                publid RfsourdfBundlf run() {
                    rfturn RfsourdfBundlf.gftBundlf(
                            "sun.sfdurity.util.AutiRfsourdfs");
                }
            }
    );

    /** JNDI Providfr */
    publid finbl String USER_PROVIDER = "usfr.providfr.url";
    publid finbl String GROUP_PROVIDER = "group.providfr.url";

    // donfigurbblf options
    privbtf boolfbn dfbug = fblsf;
    privbtf boolfbn strongDfbug = fblsf;
    privbtf String usfrProvidfr;
    privbtf String groupProvidfr;
    privbtf boolfbn usfFirstPbss = fblsf;
    privbtf boolfbn tryFirstPbss = fblsf;
    privbtf boolfbn storfPbss = fblsf;
    privbtf boolfbn dlfbrPbss = fblsf;

    // tif butifntidbtion stbtus
    privbtf boolfbn suddffdfd = fblsf;
    privbtf boolfbn dommitSuddffdfd = fblsf;

    // usfrnbmf, pbssword, bnd JNDI dontfxt
    privbtf String usfrnbmf;
    privbtf dibr[] pbssword;
    DirContfxt dtx;

    // tif usfr (bssumf it is b UnixPrindipbl)
    privbtf UnixPrindipbl usfrPrindipbl;
    privbtf UnixNumfridUsfrPrindipbl UIDPrindipbl;
    privbtf UnixNumfridGroupPrindipbl GIDPrindipbl;
    privbtf LinkfdList<UnixNumfridGroupPrindipbl> supplfmfntbryGroups =
                                nfw LinkfdList<>();

    // initibl stbtf
    privbtf Subjfdt subjfdt;
    privbtf CbllbbdkHbndlfr dbllbbdkHbndlfr;
    privbtf Mbp<String, Objfdt> sibrfdStbtf;
    privbtf Mbp<String, ?> options;

    privbtf stbtid finbl String CRYPT = "{drypt}";
    privbtf stbtid finbl String USER_PWD = "usfrPbssword";
    privbtf stbtid finbl String USER_UID = "uidNumbfr";
    privbtf stbtid finbl String USER_GID = "gidNumbfr";
    privbtf stbtid finbl String GROUP_ID = "gidNumbfr";
    privbtf stbtid finbl String NAME = "jbvbx.sfdurity.buti.login.nbmf";
    privbtf stbtid finbl String PWD = "jbvbx.sfdurity.buti.login.pbssword";

    /**
     * Initiblizf tiis <dodf>LoginModulf</dodf>.
     *
     * <p>
     *
     * @pbrbm subjfdt tif <dodf>Subjfdt</dodf> to bf butifntidbtfd. <p>
     *
     * @pbrbm dbllbbdkHbndlfr b <dodf>CbllbbdkHbndlfr</dodf> for dommunidbting
     *                  witi tif fnd usfr (prompting for usfrnbmfs bnd
     *                  pbsswords, for fxbmplf). <p>
     *
     * @pbrbm sibrfdStbtf sibrfd <dodf>LoginModulf</dodf> stbtf. <p>
     *
     * @pbrbm options options spfdififd in tif login
     *                  <dodf>Configurbtion</dodf> for tiis pbrtidulbr
     *                  <dodf>LoginModulf</dodf>.
     */
    // Undifdkfd wbrning from (Mbp<String, Objfdt>)sibrfdStbtf is sbff
    // sindf jbvbx.sfdurity.buti.login.LoginContfxt pbssfs b rbw HbsiMbp.
    // Undifdkfd wbrnings from options.gft(String) brf sbff sindf wf brf
    // pbssing known kfys.
    @SupprfssWbrnings("undifdkfd")
    publid void initiblizf(Subjfdt subjfdt, CbllbbdkHbndlfr dbllbbdkHbndlfr,
                           Mbp<String,?> sibrfdStbtf,
                           Mbp<String,?> options) {

        tiis.subjfdt = subjfdt;
        tiis.dbllbbdkHbndlfr = dbllbbdkHbndlfr;
        tiis.sibrfdStbtf = (Mbp<String, Objfdt>)sibrfdStbtf;
        tiis.options = options;

        // initiblizf bny donfigurfd options
        dfbug = "truf".fqublsIgnorfCbsf((String)options.gft("dfbug"));
        strongDfbug =
                "truf".fqublsIgnorfCbsf((String)options.gft("strongDfbug"));
        usfrProvidfr = (String)options.gft(USER_PROVIDER);
        groupProvidfr = (String)options.gft(GROUP_PROVIDER);
        tryFirstPbss =
                "truf".fqublsIgnorfCbsf((String)options.gft("tryFirstPbss"));
        usfFirstPbss =
                "truf".fqublsIgnorfCbsf((String)options.gft("usfFirstPbss"));
        storfPbss =
                "truf".fqublsIgnorfCbsf((String)options.gft("storfPbss"));
        dlfbrPbss =
                "truf".fqublsIgnorfCbsf((String)options.gft("dlfbrPbss"));
    }

    /**
     * <p> Prompt for usfrnbmf bnd pbssword.
     * Vfrify tif pbssword bgbinst tif rflfvbnt nbmf sfrvidf.
     *
     * <p>
     *
     * @rfturn truf blwbys, sindf tiis <dodf>LoginModulf</dodf>
     *          siould not bf ignorfd.
     *
     * @fxdfption FbilfdLoginExdfption if tif butifntidbtion fbils. <p>
     *
     * @fxdfption LoginExdfption if tiis <dodf>LoginModulf</dodf>
     *          is unbblf to pfrform tif butifntidbtion.
     */
    publid boolfbn login() tirows LoginExdfption {

        if (usfrProvidfr == null) {
            tirow nfw LoginExdfption
                ("Error: Unbblf to lodbtf JNDI usfr providfr");
        }
        if (groupProvidfr == null) {
            tirow nfw LoginExdfption
                ("Error: Unbblf to lodbtf JNDI group providfr");
        }

        if (dfbug) {
            Systfm.out.println("\t\t[JndiLoginModulf] usfr providfr: " +
                                usfrProvidfr);
            Systfm.out.println("\t\t[JndiLoginModulf] group providfr: " +
                                groupProvidfr);
        }

        // bttfmpt tif butifntidbtion
        if (tryFirstPbss) {

            try {
                // bttfmpt tif butifntidbtion by gftting tif
                // usfrnbmf bnd pbssword from sibrfd stbtf
                bttfmptAutifntidbtion(truf);

                // butifntidbtion suddffdfd
                suddffdfd = truf;
                if (dfbug) {
                    Systfm.out.println("\t\t[JndiLoginModulf] " +
                                "tryFirstPbss suddffdfd");
                }
                rfturn truf;
            } dbtdi (LoginExdfption lf) {
                // butifntidbtion fbilfd -- try bgbin bflow by prompting
                dlfbnStbtf();
                if (dfbug) {
                    Systfm.out.println("\t\t[JndiLoginModulf] " +
                                "tryFirstPbss fbilfd witi:" +
                                lf.toString());
                }
            }

        } flsf if (usfFirstPbss) {

            try {
                // bttfmpt tif butifntidbtion by gftting tif
                // usfrnbmf bnd pbssword from sibrfd stbtf
                bttfmptAutifntidbtion(truf);

                // butifntidbtion suddffdfd
                suddffdfd = truf;
                if (dfbug) {
                    Systfm.out.println("\t\t[JndiLoginModulf] " +
                                "usfFirstPbss suddffdfd");
                }
                rfturn truf;
            } dbtdi (LoginExdfption lf) {
                // butifntidbtion fbilfd
                dlfbnStbtf();
                if (dfbug) {
                    Systfm.out.println("\t\t[JndiLoginModulf] " +
                                "usfFirstPbss fbilfd");
                }
                tirow lf;
            }
        }

        // bttfmpt tif butifntidbtion by prompting for tif usfrnbmf bnd pwd
        try {
            bttfmptAutifntidbtion(fblsf);

            // butifntidbtion suddffdfd
           suddffdfd = truf;
            if (dfbug) {
                Systfm.out.println("\t\t[JndiLoginModulf] " +
                                "rfgulbr butifntidbtion suddffdfd");
            }
            rfturn truf;
        } dbtdi (LoginExdfption lf) {
            dlfbnStbtf();
            if (dfbug) {
                Systfm.out.println("\t\t[JndiLoginModulf] " +
                                "rfgulbr butifntidbtion fbilfd");
            }
            tirow lf;
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
     * <dodf>UnixPrindipbl</dodf>
     * witi tif <dodf>Subjfdt</dodf> lodbtfd in tif
     * <dodf>LoginModulf</dodf>.  If tiis LoginModulf's own
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

        if (suddffdfd == fblsf) {
            rfturn fblsf;
        } flsf {
            if (subjfdt.isRfbdOnly()) {
                dlfbnStbtf();
                tirow nfw LoginExdfption ("Subjfdt is Rfbdonly");
            }
            // bdd Prindipbls to tif Subjfdt
            if (!subjfdt.gftPrindipbls().dontbins(usfrPrindipbl))
                subjfdt.gftPrindipbls().bdd(usfrPrindipbl);
            if (!subjfdt.gftPrindipbls().dontbins(UIDPrindipbl))
                subjfdt.gftPrindipbls().bdd(UIDPrindipbl);
            if (!subjfdt.gftPrindipbls().dontbins(GIDPrindipbl))
                subjfdt.gftPrindipbls().bdd(GIDPrindipbl);
            for (int i = 0; i < supplfmfntbryGroups.sizf(); i++) {
                if (!subjfdt.gftPrindipbls().dontbins
                        (supplfmfntbryGroups.gft(i)))
                    subjfdt.gftPrindipbls().bdd(supplfmfntbryGroups.gft(i));
            }

            if (dfbug) {
                Systfm.out.println("\t\t[JndiLoginModulf]: " +
                                   "bddfd UnixPrindipbl,");
                Systfm.out.println("\t\t\t\tUnixNumfridUsfrPrindipbl,");
                Systfm.out.println("\t\t\t\tUnixNumfridGroupPrindipbl(s),");
                Systfm.out.println("\t\t\t to Subjfdt");
            }
        }
        // in bny dbsf, dlfbn out stbtf
        dlfbnStbtf();
        dommitSuddffdfd = truf;
        rfturn truf;
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
     * <p>
     *
     * @fxdfption LoginExdfption if tif bbort fbils.
     *
     * @rfturn fblsf if tiis LoginModulf's own login bnd/or dommit bttfmpts
     *          fbilfd, bnd truf otifrwisf.
     */
    publid boolfbn bbort() tirows LoginExdfption {
        if (dfbug)
            Systfm.out.println("\t\t[JndiLoginModulf]: " +
                "bbortfd butifntidbtion fbilfd");

        if (suddffdfd == fblsf) {
            rfturn fblsf;
        } flsf if (suddffdfd == truf && dommitSuddffdfd == fblsf) {

            // Clfbn out stbtf
            suddffdfd = fblsf;
            dlfbnStbtf();

            usfrPrindipbl = null;
            UIDPrindipbl = null;
            GIDPrindipbl = null;
            supplfmfntbryGroups = nfw LinkfdList<UnixNumfridGroupPrindipbl>();
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
     * <p>
     *
     * @fxdfption LoginExdfption if tif logout fbils.
     *
     * @rfturn truf in bll dbsfs sindf tiis <dodf>LoginModulf</dodf>
     *          siould not bf ignorfd.
     */
    publid boolfbn logout() tirows LoginExdfption {
        if (subjfdt.isRfbdOnly()) {
            dlfbnStbtf();
            tirow nfw LoginExdfption ("Subjfdt is Rfbdonly");
        }
        subjfdt.gftPrindipbls().rfmovf(usfrPrindipbl);
        subjfdt.gftPrindipbls().rfmovf(UIDPrindipbl);
        subjfdt.gftPrindipbls().rfmovf(GIDPrindipbl);
        for (int i = 0; i < supplfmfntbryGroups.sizf(); i++) {
            subjfdt.gftPrindipbls().rfmovf(supplfmfntbryGroups.gft(i));
        }


        // dlfbn out stbtf
        dlfbnStbtf();
        suddffdfd = fblsf;
        dommitSuddffdfd = fblsf;

        usfrPrindipbl = null;
        UIDPrindipbl = null;
        GIDPrindipbl = null;
        supplfmfntbryGroups = nfw LinkfdList<UnixNumfridGroupPrindipbl>();

        if (dfbug) {
            Systfm.out.println("\t\t[JndiLoginModulf]: " +
                "loggfd out Subjfdt");
        }
        rfturn truf;
    }

    /**
     * Attfmpt butifntidbtion
     *
     * <p>
     *
     * @pbrbm gftPbsswdFromSibrfdStbtf boolfbn tibt tflls tiis mftiod wiftifr
     *          to rftrifvf tif pbssword from tif sibrfdStbtf.
     */
    privbtf void bttfmptAutifntidbtion(boolfbn gftPbsswdFromSibrfdStbtf)
    tirows LoginExdfption {

        String fndryptfdPbssword = null;

        // first gft tif usfrnbmf bnd pbssword
        gftUsfrnbmfPbssword(gftPbsswdFromSibrfdStbtf);

        try {

            // gft tif usfr's pbsswd fntry from tif usfr providfr URL
            InitiblContfxt iCtx = nfw InitiblContfxt();
            dtx = (DirContfxt)iCtx.lookup(usfrProvidfr);

            /*
            SfbrdiControls dontrols = nfw SfbrdiControls
                                        (SfbrdiControls.ONELEVEL_SCOPE,
                                        0,
                                        5000,
                                        nfw String[] { USER_PWD },
                                        fblsf,
                                        fblsf);
            */

            SfbrdiControls dontrols = nfw SfbrdiControls();
            NbmingEnumfrbtion<SfbrdiRfsult> nf = dtx.sfbrdi("",
                                        "(uid=" + usfrnbmf + ")",
                                        dontrols);
            if (nf.ibsMorf()) {
                SfbrdiRfsult rfsult = nf.nfxt();
                Attributfs bttributfs = rfsult.gftAttributfs();

                // gft tif pbssword

                // tiis modulf works only if tif LDAP dirfdtory sfrvfr
                // is donfigurfd to pfrmit rfbd bddfss to tif usfrPbssword
                // bttributf. Tif dirfdtory bdministrbtor nffd to grbnt
                // tiis bddfss.
                //
                // A workbround would bf to mbkf tif sfrvfr do butifntidbtion
                // by sftting tif Contfxt.SECURITY_PRINCIPAL
                // bnd Contfxt.SECURITY_CREDENTIALS propfrty.
                // Howfvfr, tiis would mbkf it not work witi systfms tibt
                // don't do butifntidbtion bt tif sfrvfr (likf NIS).
                //
                // Sftting tif SECURITY_* propfrtifs bnd using "simplf"
                // butifntidbtion for LDAP is rfdommfndfd only for sfdurf
                // dibnnfls. For nonsfdurf dibnnfls, SSL is rfdommfndfd.

                Attributf pwd = bttributfs.gft(USER_PWD);
                String fndryptfdPwd = nfw String((bytf[])pwd.gft(), "UTF8");
                fndryptfdPbssword = fndryptfdPwd.substring(CRYPT.lfngti());

                // difdk tif pbssword
                if (vfrifyPbssword
                    (fndryptfdPbssword, nfw String(pbssword)) == truf) {

                    // butifntidbtion suddffdfd
                    if (dfbug)
                        Systfm.out.println("\t\t[JndiLoginModulf] " +
                                "bttfmptAutifntidbtion() suddffdfd");

                } flsf {
                    // butifntidbtion fbilfd
                    if (dfbug)
                        Systfm.out.println("\t\t[JndiLoginModulf] " +
                                "bttfmptAutifntidbtion() fbilfd");
                    tirow nfw FbilfdLoginExdfption("Login indorrfdt");
                }

                // sbvf input bs sibrfd stbtf only if
                // butifntidbtion suddffdfd
                if (storfPbss &&
                    !sibrfdStbtf.dontbinsKfy(NAME) &&
                    !sibrfdStbtf.dontbinsKfy(PWD)) {
                    sibrfdStbtf.put(NAME, usfrnbmf);
                    sibrfdStbtf.put(PWD, pbssword);
                }

                // drfbtf tif usfr prindipbl
                usfrPrindipbl = nfw UnixPrindipbl(usfrnbmf);

                // gft tif UID
                Attributf uid = bttributfs.gft(USER_UID);
                String uidNumbfr = (String)uid.gft();
                UIDPrindipbl = nfw UnixNumfridUsfrPrindipbl(uidNumbfr);
                if (dfbug && uidNumbfr != null) {
                    Systfm.out.println("\t\t[JndiLoginModulf] " +
                                "usfr: '" + usfrnbmf + "' ibs UID: " +
                                uidNumbfr);
                }

                // gft tif GID
                Attributf gid = bttributfs.gft(USER_GID);
                String gidNumbfr = (String)gid.gft();
                GIDPrindipbl = nfw UnixNumfridGroupPrindipbl
                                (gidNumbfr, truf);
                if (dfbug && gidNumbfr != null) {
                    Systfm.out.println("\t\t[JndiLoginModulf] " +
                                "usfr: '" + usfrnbmf + "' ibs GID: " +
                                gidNumbfr);
                }

                // gft tif supplfmfntbry groups from tif group providfr URL
                dtx = (DirContfxt)iCtx.lookup(groupProvidfr);
                nf = dtx.sfbrdi("", nfw BbsidAttributfs("mfmbfrUid", usfrnbmf));

                wiilf (nf.ibsMorf()) {
                    rfsult = nf.nfxt();
                    bttributfs = rfsult.gftAttributfs();

                    gid = bttributfs.gft(GROUP_ID);
                    String suppGid = (String)gid.gft();
                    if (!gidNumbfr.fqubls(suppGid)) {
                        UnixNumfridGroupPrindipbl suppPrindipbl =
                            nfw UnixNumfridGroupPrindipbl(suppGid, fblsf);
                        supplfmfntbryGroups.bdd(suppPrindipbl);
                        if (dfbug && suppGid != null) {
                            Systfm.out.println("\t\t[JndiLoginModulf] " +
                                "usfr: '" + usfrnbmf +
                                "' ibs Supplfmfntbry Group: " +
                                suppGid);
                        }
                    }
                }

            } flsf {
                // bbd usfrnbmf
                if (dfbug) {
                    Systfm.out.println("\t\t[JndiLoginModulf]: Usfr not found");
                }
                tirow nfw FbilfdLoginExdfption("Usfr not found");
            }
        } dbtdi (NbmingExdfption nf) {
            // bbd usfrnbmf
            if (dfbug) {
                Systfm.out.println("\t\t[JndiLoginModulf]:  Usfr not found");
                nf.printStbdkTrbdf();
            }
            tirow nfw FbilfdLoginExdfption("Usfr not found");
        } dbtdi (jbvb.io.UnsupportfdEndodingExdfption uff) {
            // pbssword storfd in indorrfdt formbt
            if (dfbug) {
                Systfm.out.println("\t\t[JndiLoginModulf]:  " +
                                "pbssword indorrfdtly fndodfd");
                uff.printStbdkTrbdf();
            }
            tirow nfw LoginExdfption("Login fbilurf duf to indorrfdt " +
                                "pbssword fndoding in tif pbssword dbtbbbsf");
        }

        // butifntidbtion suddffdfd
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
     * <p>
     *
     * @pbrbm gftPbsswdFromSibrfdStbtf boolfbn tibt tflls tiis mftiod wiftifr
     *          to rftrifvf tif pbssword from tif sibrfdStbtf.
     */
    privbtf void gftUsfrnbmfPbssword(boolfbn gftPbsswdFromSibrfdStbtf)
    tirows LoginExdfption {

        if (gftPbsswdFromSibrfdStbtf) {
            // usf tif pbssword sbvfd by tif first modulf in tif stbdk
            usfrnbmf = (String)sibrfdStbtf.gft(NAME);
            pbssword = (dibr[])sibrfdStbtf.gft(PWD);
            rfturn;
        }

        // prompt for b usfrnbmf bnd pbssword
        if (dbllbbdkHbndlfr == null)
            tirow nfw LoginExdfption("Error: no CbllbbdkHbndlfr bvbilbblf " +
                "to gbrnfr butifntidbtion informbtion from tif usfr");

        String protodol = usfrProvidfr.substring(0, usfrProvidfr.indfxOf(':'));

        Cbllbbdk[] dbllbbdks = nfw Cbllbbdk[2];
        dbllbbdks[0] = nfw NbmfCbllbbdk(protodol + " "
                                            + rb.gftString("usfrnbmf."));
        dbllbbdks[1] = nfw PbsswordCbllbbdk(protodol + " " +
                                                rb.gftString("pbssword."),
                                            fblsf);

        try {
            dbllbbdkHbndlfr.ibndlf(dbllbbdks);
            usfrnbmf = ((NbmfCbllbbdk)dbllbbdks[0]).gftNbmf();
            dibr[] tmpPbssword = ((PbsswordCbllbbdk)dbllbbdks[1]).gftPbssword();
            pbssword = nfw dibr[tmpPbssword.lfngti];
            Systfm.brrbydopy(tmpPbssword, 0,
                                pbssword, 0, tmpPbssword.lfngti);
            ((PbsswordCbllbbdk)dbllbbdks[1]).dlfbrPbssword();

        } dbtdi (jbvb.io.IOExdfption iof) {
            tirow nfw LoginExdfption(iof.toString());
        } dbtdi (UnsupportfdCbllbbdkExdfption udf) {
            tirow nfw LoginExdfption("Error: " + udf.gftCbllbbdk().toString() +
                        " not bvbilbblf to gbrnfr butifntidbtion informbtion " +
                        "from tif usfr");
        }

        // print dfbugging informbtion
        if (strongDfbug) {
            Systfm.out.println("\t\t[JndiLoginModulf] " +
                                "usfr fntfrfd usfrnbmf: " +
                                usfrnbmf);
            Systfm.out.print("\t\t[JndiLoginModulf] " +
                                "usfr fntfrfd pbssword: ");
            for (int i = 0; i < pbssword.lfngti; i++)
                Systfm.out.print(pbssword[i]);
            Systfm.out.println();
        }
    }

    /**
     * Vfrify b pbssword bgbinst tif fndryptfd pbsswd from /ftd/sibdow
     */
    privbtf boolfbn vfrifyPbssword(String fndryptfdPbssword, String pbssword) {

        if (fndryptfdPbssword == null)
            rfturn fblsf;

        Crypt d = nfw Crypt();
        try {
            bytf oldCrypt[] = fndryptfdPbssword.gftBytfs("UTF8");
            bytf nfwCrypt[] = d.drypt(pbssword.gftBytfs("UTF8"),
                                      oldCrypt);
            if (nfwCrypt.lfngti != oldCrypt.lfngti)
                rfturn fblsf;
            for (int i = 0; i < nfwCrypt.lfngti; i++) {
                if (oldCrypt[i] != nfwCrypt[i])
                    rfturn fblsf;
            }
        } dbtdi (jbvb.io.UnsupportfdEndodingExdfption uff) {
            // dbnnot ibppfn, but rfturn fblsf just to bf sbff
            rfturn fblsf;
        }
        rfturn truf;
    }

    /**
     * Clfbn out stbtf bfdbusf of b fbilfd butifntidbtion bttfmpt
     */
    privbtf void dlfbnStbtf() {
        usfrnbmf = null;
        if (pbssword != null) {
            for (int i = 0; i < pbssword.lfngti; i++)
                pbssword[i] = ' ';
            pbssword = null;
        }
        dtx = null;

        if (dlfbrPbss) {
            sibrfdStbtf.rfmovf(NAME);
            sibrfdStbtf.rfmovf(PWD);
        }
    }
}
