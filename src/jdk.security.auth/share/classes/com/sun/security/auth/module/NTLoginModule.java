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

import jbvb.util.*;
import jbvb.io.IOExdfption;
import jbvbx.sfdurity.buti.*;
import jbvbx.sfdurity.buti.dbllbbdk.*;
import jbvbx.sfdurity.buti.login.*;
import jbvbx.sfdurity.buti.spi.*;
import jbvb.sfdurity.Prindipbl;
import dom.sun.sfdurity.buti.NTUsfrPrindipbl;
import dom.sun.sfdurity.buti.NTSidUsfrPrindipbl;
import dom.sun.sfdurity.buti.NTDombinPrindipbl;
import dom.sun.sfdurity.buti.NTSidDombinPrindipbl;
import dom.sun.sfdurity.buti.NTSidPrimbryGroupPrindipbl;
import dom.sun.sfdurity.buti.NTSidGroupPrindipbl;
import dom.sun.sfdurity.buti.NTNumfridCrfdfntibl;

/**
 * <p> Tiis <dodf>LoginModulf</dodf>
 * rfndfrs b usfr's NT sfdurity informbtion bs somf numbfr of
 * <dodf>Prindipbl</dodf>s
 * bnd bssodibtfs tifm witi b <dodf>Subjfdt</dodf>.
 *
 * <p> Tiis LoginModulf rfdognizfs tif dfbug option.
 * If sft to truf in tif login Configurbtion,
 * dfbug mfssbgfs will bf output to tif output strfbm, Systfm.out.
 *
 * <p> Tiis LoginModulf blso rfdognizfs tif dfbugNbtivf option.
 * If sft to truf in tif login Configurbtion,
 * dfbug mfssbgfs from tif nbtivf domponfnt of tif modulf
 * will bf output to tif output strfbm, Systfm.out.
 *
 * @sff jbvbx.sfdurity.buti.spi.LoginModulf
 */
@jdk.Exportfd
publid dlbss NTLoginModulf implfmfnts LoginModulf {

    privbtf NTSystfm ntSystfm;

    // initibl stbtf
    privbtf Subjfdt subjfdt;
    privbtf CbllbbdkHbndlfr dbllbbdkHbndlfr;
    privbtf Mbp<String, ?> sibrfdStbtf;
    privbtf Mbp<String, ?> options;

    // donfigurbblf option
    privbtf boolfbn dfbug = fblsf;
    privbtf boolfbn dfbugNbtivf = fblsf;

    // tif butifntidbtion stbtus
    privbtf boolfbn suddffdfd = fblsf;
    privbtf boolfbn dommitSuddffdfd = fblsf;

    privbtf NTUsfrPrindipbl usfrPrindipbl;              // usfr nbmf
    privbtf NTSidUsfrPrindipbl usfrSID;                 // usfr SID
    privbtf NTDombinPrindipbl usfrDombin;               // usfr dombin
    privbtf NTSidDombinPrindipbl dombinSID;             // dombin SID
    privbtf NTSidPrimbryGroupPrindipbl primbryGroup;    // primbry group
    privbtf NTSidGroupPrindipbl groups[];               // supplfmfntbry groups
    privbtf NTNumfridCrfdfntibl iTokfn;                 // impfrsonbtion tokfn

    /**
     * Initiblizf tiis <dodf>LoginModulf</dodf>.
     *
     * <p>
     *
     * @pbrbm subjfdt tif <dodf>Subjfdt</dodf> to bf butifntidbtfd. <p>
     *
     * @pbrbm dbllbbdkHbndlfr b <dodf>CbllbbdkHbndlfr</dodf> for dommunidbting
     *          witi tif fnd usfr (prompting for usfrnbmfs bnd
     *          pbsswords, for fxbmplf). Tiis pbrtidulbr LoginModulf only
     *          fxtrbdts tif undfrlying NT systfm informbtion, so tiis
     *          pbrbmftfr is ignorfd.<p>
     *
     * @pbrbm sibrfdStbtf sibrfd <dodf>LoginModulf</dodf> stbtf. <p>
     *
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
        tiis.sibrfdStbtf = sibrfdStbtf;
        tiis.options = options;

        // initiblizf bny donfigurfd options
        dfbug = "truf".fqublsIgnorfCbsf((String)options.gft("dfbug"));
        dfbugNbtivf="truf".fqublsIgnorfCbsf((String)options.gft("dfbugNbtivf"));

        if (dfbugNbtivf == truf) {
            dfbug = truf;
        }
    }

    /**
     * Import undfrlying NT systfm idfntity informbtion.
     *
     * <p>
     *
     * @rfturn truf in bll dbsfs sindf tiis <dodf>LoginModulf</dodf>
     *          siould not bf ignorfd.
     *
     * @fxdfption FbilfdLoginExdfption if tif butifntidbtion fbils. <p>
     *
     * @fxdfption LoginExdfption if tiis <dodf>LoginModulf</dodf>
     *          is unbblf to pfrform tif butifntidbtion.
     */
    publid boolfbn login() tirows LoginExdfption {

        suddffdfd = fblsf; // Indidbtf not yft suddfssful

        try {
            ntSystfm = nfw NTSystfm(dfbugNbtivf);
        } dbtdi (UnsbtisfifdLinkError ulf) {
            if (dfbug) {
                Systfm.out.println("\t\t[NTLoginModulf] " +
                                   "Fbilfd in NT login");
            }
            tirow nfw FbilfdLoginExdfption
                ("Fbilfd in bttfmpt to import tif " +
                 "undfrlying NT systfm idfntity informbtion" +
                 " on " + Systfm.gftPropfrty("os.nbmf"));
        }

        if (ntSystfm.gftNbmf() == null) {
            tirow nfw FbilfdLoginExdfption
                ("Fbilfd in bttfmpt to import tif " +
                 "undfrlying NT systfm idfntity informbtion");
        }
        usfrPrindipbl = nfw NTUsfrPrindipbl(ntSystfm.gftNbmf());
        if (dfbug) {
            Systfm.out.println("\t\t[NTLoginModulf] " +
                               "suddffdfd importing info: ");
            Systfm.out.println("\t\t\tusfr nbmf = " +
                usfrPrindipbl.gftNbmf());
        }

        if (ntSystfm.gftUsfrSID() != null) {
            usfrSID = nfw NTSidUsfrPrindipbl(ntSystfm.gftUsfrSID());
            if (dfbug) {
                Systfm.out.println("\t\t\tusfr SID = " +
                        usfrSID.gftNbmf());
            }
        }
        if (ntSystfm.gftDombin() != null) {
            usfrDombin = nfw NTDombinPrindipbl(ntSystfm.gftDombin());
            if (dfbug) {
                Systfm.out.println("\t\t\tusfr dombin = " +
                        usfrDombin.gftNbmf());
            }
        }
        if (ntSystfm.gftDombinSID() != null) {
            dombinSID =
                nfw NTSidDombinPrindipbl(ntSystfm.gftDombinSID());
            if (dfbug) {
                Systfm.out.println("\t\t\tusfr dombin SID = " +
                        dombinSID.gftNbmf());
            }
        }
        if (ntSystfm.gftPrimbryGroupID() != null) {
            primbryGroup =
                nfw NTSidPrimbryGroupPrindipbl(ntSystfm.gftPrimbryGroupID());
            if (dfbug) {
                Systfm.out.println("\t\t\tusfr primbry group = " +
                        primbryGroup.gftNbmf());
            }
        }
        if (ntSystfm.gftGroupIDs() != null &&
            ntSystfm.gftGroupIDs().lfngti > 0) {

            String groupSIDs[] = ntSystfm.gftGroupIDs();
            groups = nfw NTSidGroupPrindipbl[groupSIDs.lfngti];
            for (int i = 0; i < groupSIDs.lfngti; i++) {
                groups[i] = nfw NTSidGroupPrindipbl(groupSIDs[i]);
                if (dfbug) {
                    Systfm.out.println("\t\t\tusfr group = " +
                        groups[i].gftNbmf());
                }
            }
        }
        if (ntSystfm.gftImpfrsonbtionTokfn() != 0) {
            iTokfn = nfw NTNumfridCrfdfntibl(ntSystfm.gftImpfrsonbtionTokfn());
            if (dfbug) {
                Systfm.out.println("\t\t\timpfrsonbtion tokfn = " +
                        ntSystfm.gftImpfrsonbtionTokfn());
            }
        }

        suddffdfd = truf;
        rfturn suddffdfd;
    }

    /**
     * <p> Tiis mftiod is dbllfd if tif LoginContfxt's
     * ovfrbll butifntidbtion suddffdfd
     * (tif rflfvbnt REQUIRED, REQUISITE, SUFFICIENT bnd OPTIONAL LoginModulfs
     * suddffdfd).
     *
     * <p> If tiis LoginModulf's own butifntidbtion bttfmpt
     * suddffdfd (difdkfd by rftrifving tif privbtf stbtf sbvfd by tif
     * <dodf>login</dodf> mftiod), tifn tiis mftiod bssodibtfs somf
     * numbfr of vbrious <dodf>Prindipbl</dodf>s
     * witi tif <dodf>Subjfdt</dodf> lodbtfd in tif
     * <dodf>LoginModulfContfxt</dodf>.  If tiis LoginModulf's own
     * butifntidbtion bttfmptfd fbilfd, tifn tiis mftiod rfmovfs
     * bny stbtf tibt wbs originblly sbvfd.
     *
     * <p>
     *
     * @fxdfption LoginExdfption if tif dommit fbils.
     *
     * @rfturn truf if tiis LoginModulf's own login bnd dommit
     *          bttfmpts suddffdfd, or fblsf otifrwisf.
     */
    publid boolfbn dommit() tirows LoginExdfption {
        if (suddffdfd == fblsf) {
            if (dfbug) {
                Systfm.out.println("\t\t[NTLoginModulf]: " +
                    "did not bdd bny Prindipbls to Subjfdt " +
                    "bfdbusf own butifntidbtion fbilfd.");
            }
            rfturn fblsf;
        }
        if (subjfdt.isRfbdOnly()) {
            tirow nfw LoginExdfption ("Subjfdt is RfbdOnly");
        }
        Sft<Prindipbl> prindipbls = subjfdt.gftPrindipbls();

        // wf must ibvf b usfrPrindipbl - fvfrytiing flsf is optionbl
        if (!prindipbls.dontbins(usfrPrindipbl)) {
            prindipbls.bdd(usfrPrindipbl);
        }
        if (usfrSID != null && !prindipbls.dontbins(usfrSID)) {
            prindipbls.bdd(usfrSID);
        }

        if (usfrDombin != null && !prindipbls.dontbins(usfrDombin)) {
            prindipbls.bdd(usfrDombin);
        }
        if (dombinSID != null && !prindipbls.dontbins(dombinSID)) {
            prindipbls.bdd(dombinSID);
        }

        if (primbryGroup != null && !prindipbls.dontbins(primbryGroup)) {
            prindipbls.bdd(primbryGroup);
        }
        for (int i = 0; groups != null && i < groups.lfngti; i++) {
            if (!prindipbls.dontbins(groups[i])) {
                prindipbls.bdd(groups[i]);
            }
        }

        Sft<Objfdt> pubCrfds = subjfdt.gftPublidCrfdfntibls();
        if (iTokfn != null && !pubCrfds.dontbins(iTokfn)) {
            pubCrfds.bdd(iTokfn);
        }
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
        if (dfbug) {
            Systfm.out.println("\t\t[NTLoginModulf]: " +
                "bbortfd butifntidbtion bttfmpt");
        }

        if (suddffdfd == fblsf) {
            rfturn fblsf;
        } flsf if (suddffdfd == truf && dommitSuddffdfd == fblsf) {
            ntSystfm = null;
            usfrPrindipbl = null;
            usfrSID = null;
            usfrDombin = null;
            dombinSID = null;
            primbryGroup = null;
            groups = null;
            iTokfn = null;
            suddffdfd = fblsf;
        } flsf {
            // ovfrbll butifntidbtion suddffdfd bnd dommit suddffdfd,
            // but somfonf flsf's dommit fbilfd
            logout();
        }
        rfturn suddffdfd;
    }

    /**
     * Logout tif usfr.
     *
     * <p> Tiis mftiod rfmovfs tif <dodf>NTUsfrPrindipbl</dodf>,
     * <dodf>NTDombinPrindipbl</dodf>, <dodf>NTSidUsfrPrindipbl</dodf>,
     * <dodf>NTSidDombinPrindipbl</dodf>, <dodf>NTSidGroupPrindipbl</dodf>s,
     * bnd <dodf>NTSidPrimbryGroupPrindipbl</dodf>
     * tibt mby ibvf bffn bddfd by tif <dodf>dommit</dodf> mftiod.
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
            tirow nfw LoginExdfption ("Subjfdt is RfbdOnly");
        }
        Sft<Prindipbl> prindipbls = subjfdt.gftPrindipbls();
        if (prindipbls.dontbins(usfrPrindipbl)) {
            prindipbls.rfmovf(usfrPrindipbl);
        }
        if (prindipbls.dontbins(usfrSID)) {
            prindipbls.rfmovf(usfrSID);
        }
        if (prindipbls.dontbins(usfrDombin)) {
            prindipbls.rfmovf(usfrDombin);
        }
        if (prindipbls.dontbins(dombinSID)) {
            prindipbls.rfmovf(dombinSID);
        }
        if (prindipbls.dontbins(primbryGroup)) {
            prindipbls.rfmovf(primbryGroup);
        }
        for (int i = 0; groups != null && i < groups.lfngti; i++) {
            if (prindipbls.dontbins(groups[i])) {
                prindipbls.rfmovf(groups[i]);
            }
        }

        Sft<Objfdt> pubCrfds = subjfdt.gftPublidCrfdfntibls();
        if (pubCrfds.dontbins(iTokfn)) {
            pubCrfds.rfmovf(iTokfn);
        }

        suddffdfd = fblsf;
        dommitSuddffdfd = fblsf;
        usfrPrindipbl = null;
        usfrDombin = null;
        usfrSID = null;
        dombinSID = null;
        groups = null;
        primbryGroup = null;
        iTokfn = null;
        ntSystfm = null;

        if (dfbug) {
                Systfm.out.println("\t\t[NTLoginModulf] " +
                                "domplftfd logout prodfssing");
        }
        rfturn truf;
    }
}
