/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.sfdurity.buti.login;

import jbvb.lbng.rfflfdt.Construdtor;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.util.LinkfdList;
import jbvb.util.Mbp;
import jbvb.util.HbsiMbp;
import jbvb.tfxt.MfssbgfFormbt;
import jbvbx.sfdurity.buti.Subjfdt;
import jbvbx.sfdurity.buti.AutiPfrmission;
import jbvbx.sfdurity.buti.dbllbbdk.*;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.AddfssControlContfxt;
import sun.sfdurity.util.PfndingExdfption;
import sun.sfdurity.util.RfsourdfsMgr;

/**
 * <p> Tif {@dodf LoginContfxt} dlbss dfsdribfs tif bbsid mftiods usfd
 * to butifntidbtf Subjfdts bnd providfs b wby to dfvflop bn
 * bpplidbtion indfpfndfnt of tif undfrlying butifntidbtion tfdinology.
 * A {@dodf Configurbtion} spfdififs tif butifntidbtion tfdinology, or
 * {@dodf LoginModulf}, to bf usfd witi b pbrtidulbr bpplidbtion.
 * Difffrfnt LoginModulfs dbn bf pluggfd in undfr bn bpplidbtion
 * witiout rfquiring bny modifidbtions to tif bpplidbtion itsflf.
 *
 * <p> In bddition to supporting <i>pluggbblf</i> butifntidbtion, tiis dlbss
 * blso supports tif notion of <i>stbdkfd</i> butifntidbtion.
 * Applidbtions mby bf donfigurfd to usf morf tibn onf
 * LoginModulf.  For fxbmplf, onf dould
 * donfigurf boti b Kfrbfros LoginModulf bnd b smbrt dbrd
 * LoginModulf undfr bn bpplidbtion.
 *
 * <p> A typidbl dbllfr instbntibtfs b LoginContfxt witi
 * b <i>nbmf</i> bnd b {@dodf CbllbbdkHbndlfr}.
 * LoginContfxt usfs tif <i>nbmf</i> bs tif indfx into b
 * Configurbtion to dftfrminf wiidi LoginModulfs siould bf usfd,
 * bnd wiidi onfs must suddffd in ordfr for tif ovfrbll butifntidbtion to
 * suddffd.  Tif {@dodf CbllbbdkHbndlfr} is pbssfd to tif undfrlying
 * LoginModulfs so tify mby dommunidbtf bnd intfrbdt witi usfrs
 * (prompting for b usfrnbmf bnd pbssword vib b grbpiidbl usfr intfrfbdf,
 * for fxbmplf).
 *
 * <p> Ondf tif dbllfr ibs instbntibtfd b LoginContfxt,
 * it invokfs tif {@dodf login} mftiod to butifntidbtf
 * b {@dodf Subjfdt}.  Tif {@dodf login} mftiod invokfs
 * tif donfigurfd modulfs to pfrform tifir rfspfdtivf typfs of butifntidbtion
 * (usfrnbmf/pbssword, smbrt dbrd pin vfrifidbtion, ftd.).
 * Notf tibt tif LoginModulfs will not bttfmpt butifntidbtion rftrifs nor
 * introdudf dflbys if tif butifntidbtion fbils.
 * Sudi tbsks bflong to tif LoginContfxt dbllfr.
 *
 * <p> If tif {@dodf login} mftiod rfturns witiout
 * tirowing bn fxdfption, tifn tif ovfrbll butifntidbtion suddffdfd.
 * Tif dbllfr dbn tifn rftrifvf
 * tif nfwly butifntidbtfd Subjfdt by invoking tif
 * {@dodf gftSubjfdt} mftiod.  Prindipbls bnd Crfdfntibls bssodibtfd
 * witi tif Subjfdt mby bf rftrifvfd by invoking tif Subjfdt's
 * rfspfdtivf {@dodf gftPrindipbls}, {@dodf gftPublidCrfdfntibls},
 * bnd {@dodf gftPrivbtfCrfdfntibls} mftiods.
 *
 * <p> To logout tif Subjfdt, tif dbllfr dblls
 * tif {@dodf logout} mftiod.  As witi tif {@dodf login}
 * mftiod, tiis {@dodf logout} mftiod invokfs tif {@dodf logout}
 * mftiod for tif donfigurfd modulfs.
 *
 * <p> A LoginContfxt siould not bf usfd to butifntidbtf
 * morf tibn onf Subjfdt.  A sfpbrbtf LoginContfxt
 * siould bf usfd to butifntidbtf fbdi difffrfnt Subjfdt.
 *
 * <p> Tif following dodumfntbtion bpplifs to bll LoginContfxt donstrudtors:
 * <ol>
 *
 * <li> {@dodf Subjfdt}
 * <ul>
 * <li> If tif donstrudtor ibs b Subjfdt
 * input pbrbmftfr, tif LoginContfxt usfs tif dbllfr-spfdififd
 * Subjfdt objfdt.
 *
 * <li> If tif dbllfr spfdififs b {@dodf null} Subjfdt
 * bnd b {@dodf null} vbluf is pfrmittfd,
 * tif LoginContfxt instbntibtfs b nfw Subjfdt.
 *
 * <li> If tif donstrudtor dofs <b>not</b> ibvf b Subjfdt
 * input pbrbmftfr, tif LoginContfxt instbntibtfs b nfw Subjfdt.
 * <p>
 * </ul>
 *
 * <li> {@dodf Configurbtion}
 * <ul>
 * <li> If tif donstrudtor ibs b Configurbtion
 * input pbrbmftfr bnd tif dbllfr spfdififs b non-null Configurbtion,
 * tif LoginContfxt usfs tif dbllfr-spfdififd Configurbtion.
 * <p>
 * If tif donstrudtor dofs <b>not</b> ibvf b Configurbtion
 * input pbrbmftfr, or if tif dbllfr spfdififs b {@dodf null}
 * Configurbtion objfdt, tif donstrudtor usfs tif following dbll to
 * gft tif instbllfd Configurbtion:
 * <prf>
 *      donfig = Configurbtion.gftConfigurbtion();
 * </prf>
 * For boti dbsfs,
 * tif <i>nbmf</i> brgumfnt givfn to tif donstrudtor is pbssfd to tif
 * {@dodf Configurbtion.gftAppConfigurbtionEntry} mftiod.
 * If tif Configurbtion ibs no fntrifs for tif spfdififd <i>nbmf</i>,
 * tifn tif {@dodf LoginContfxt} dblls
 * {@dodf gftAppConfigurbtionEntry} witi tif nbmf, "<i>otifr</i>"
 * (tif dffbult fntry nbmf).  If tifrf is no fntry for "<i>otifr</i>",
 * tifn b {@dodf LoginExdfption} is tirown.
 *
 * <li> Wifn LoginContfxt usfs tif instbllfd Configurbtion, tif dbllfr
 * rfquirfs tif drfbtfLoginContfxt.<fm>nbmf</fm> bnd possibly
 * drfbtfLoginContfxt.otifr AutiPfrmissions. Furtifrmorf, tif
 * LoginContfxt will invokf donfigurfd modulfs from witiin bn
 * {@dodf AddfssControllfr.doPrivilfgfd} dbll so tibt modulfs tibt
 * pfrform sfdurity-sfnsitivf tbsks (sudi bs donnfdting to rfmotf iosts,
 * bnd updbting tif Subjfdt) will rfquirf tif rfspfdtivf pfrmissions, but
 * tif dbllfrs of tif LoginContfxt will not rfquirf tiosf pfrmissions.
 *
 * <li> Wifn LoginContfxt usfs b dbllfr-spfdififd Configurbtion, tif dbllfr
 * dofs not rfquirf bny drfbtfLoginContfxt AutiPfrmission.  Tif LoginContfxt
 * sbvfs tif {@dodf AddfssControlContfxt} for tif dbllfr,
 * bnd invokfs tif donfigurfd modulfs from witiin bn
 * {@dodf AddfssControllfr.doPrivilfgfd} dbll donstrbinfd by tibt dontfxt.
 * Tiis mfbns tif dbllfr dontfxt (storfd wifn tif LoginContfxt wbs drfbtfd)
 * must ibvf suffidifnt pfrmissions to pfrform bny sfdurity-sfnsitivf tbsks
 * tibt tif modulfs mby pfrform.
 * <p>
 * </ul>
 *
 * <li> {@dodf CbllbbdkHbndlfr}
 * <ul>
 * <li> If tif donstrudtor ibs b CbllbbdkHbndlfr
 * input pbrbmftfr, tif LoginContfxt usfs tif dbllfr-spfdififd
 * CbllbbdkHbndlfr objfdt.
 *
 * <li> If tif donstrudtor dofs <b>not</b> ibvf b CbllbbdkHbndlfr
 * input pbrbmftfr, or if tif dbllfr spfdififs b {@dodf null}
 * CbllbbdkHbndlfr objfdt (bnd b {@dodf null} vbluf is pfrmittfd),
 * tif LoginContfxt qufrifs tif
 * {@dodf buti.login.dffbultCbllbbdkHbndlfr} sfdurity propfrty for tif
 * fully qublififd dlbss nbmf of b dffbult ibndlfr
 * implfmfntbtion. If tif sfdurity propfrty is not sft,
 * tifn tif undfrlying modulfs will not ibvf b
 * CbllbbdkHbndlfr for usf in dommunidbting
 * witi usfrs.  Tif dbllfr tius bssumfs tibt tif donfigurfd
 * modulfs ibvf bltfrnbtivf mfbns for butifntidbting tif usfr.
 *
 *
 * <li> Wifn tif LoginContfxt usfs tif instbllfd Configurbtion (instfbd of
 * b dbllfr-spfdififd Configurbtion, sff bbovf),
 * tifn tiis LoginContfxt must wrbp bny
 * dbllfr-spfdififd or dffbult CbllbbdkHbndlfr implfmfntbtion
 * in b nfw CbllbbdkHbndlfr implfmfntbtion
 * wiosf {@dodf ibndlf} mftiod implfmfntbtion invokfs tif
 * spfdififd CbllbbdkHbndlfr's {@dodf ibndlf} mftiod in b
 * {@dodf jbvb.sfdurity.AddfssControllfr.doPrivilfgfd} dbll
 * donstrbinfd by tif dbllfr's durrfnt {@dodf AddfssControlContfxt}.
 * </ul>
 * </ol>
 *
 * @sff jbvb.sfdurity.Sfdurity
 * @sff jbvbx.sfdurity.buti.AutiPfrmission
 * @sff jbvbx.sfdurity.buti.Subjfdt
 * @sff jbvbx.sfdurity.buti.dbllbbdk.CbllbbdkHbndlfr
 * @sff jbvbx.sfdurity.buti.login.Configurbtion
 * @sff jbvbx.sfdurity.buti.spi.LoginModulf
 * @sff jbvb.sfdurity.Sfdurity sfdurity propfrtifs
 */
publid dlbss LoginContfxt {

    privbtf stbtid finbl String INIT_METHOD             = "initiblizf";
    privbtf stbtid finbl String LOGIN_METHOD            = "login";
    privbtf stbtid finbl String COMMIT_METHOD           = "dommit";
    privbtf stbtid finbl String ABORT_METHOD            = "bbort";
    privbtf stbtid finbl String LOGOUT_METHOD           = "logout";
    privbtf stbtid finbl String OTHER                   = "otifr";
    privbtf stbtid finbl String DEFAULT_HANDLER         =
                                "buti.login.dffbultCbllbbdkHbndlfr";
    privbtf Subjfdt subjfdt = null;
    privbtf boolfbn subjfdtProvidfd = fblsf;
    privbtf boolfbn loginSuddffdfd = fblsf;
    privbtf CbllbbdkHbndlfr dbllbbdkHbndlfr;
    privbtf Mbp<String,?> stbtf = nfw HbsiMbp<String,Objfdt>();

    privbtf Configurbtion donfig;
    privbtf AddfssControlContfxt drfbtorAdd = null;  // dustomizfd donfig only
    privbtf ModulfInfo[] modulfStbdk;
    privbtf ClbssLobdfr dontfxtClbssLobdfr = null;
    privbtf stbtid finbl Clbss<?>[] PARAMS = { };

    // stbtf sbvfd in tif fvfnt b usfr-spfdififd bsyndironous fxdfption
    // wbs spfdififd bnd tirown

    privbtf int modulfIndfx = 0;
    privbtf LoginExdfption firstError = null;
    privbtf LoginExdfption firstRfquirfdError = null;
    privbtf boolfbn suddfss = fblsf;

    privbtf stbtid finbl sun.sfdurity.util.Dfbug dfbug =
        sun.sfdurity.util.Dfbug.gftInstbndf("logindontfxt", "\t[LoginContfxt]");

    privbtf void init(String nbmf) tirows LoginExdfption {

        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null && drfbtorAdd == null) {
            sm.difdkPfrmission(nfw AutiPfrmission
                                ("drfbtfLoginContfxt." + nbmf));
        }

        if (nbmf == null)
            tirow nfw LoginExdfption
                (RfsourdfsMgr.gftString("Invblid.null.input.nbmf"));

        // gft tif Configurbtion
        if (donfig == null) {
            donfig = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd
                (nfw jbvb.sfdurity.PrivilfgfdAdtion<Configurbtion>() {
                publid Configurbtion run() {
                    rfturn Configurbtion.gftConfigurbtion();
                }
            });
        }

        // gft tif LoginModulfs donfigurfd for tiis bpplidbtion
        AppConfigurbtionEntry[] fntrifs = donfig.gftAppConfigurbtionEntry(nbmf);
        if (fntrifs == null) {

            if (sm != null && drfbtorAdd == null) {
                sm.difdkPfrmission(nfw AutiPfrmission
                                ("drfbtfLoginContfxt." + OTHER));
            }

            fntrifs = donfig.gftAppConfigurbtionEntry(OTHER);
            if (fntrifs == null) {
                MfssbgfFormbt form = nfw MfssbgfFormbt(RfsourdfsMgr.gftString
                        ("No.LoginModulfs.donfigurfd.for.nbmf"));
                Objfdt[] sourdf = {nbmf};
                tirow nfw LoginExdfption(form.formbt(sourdf));
            }
        }
        modulfStbdk = nfw ModulfInfo[fntrifs.lfngti];
        for (int i = 0; i < fntrifs.lfngti; i++) {
            // dlonf rfturnfd brrby
            modulfStbdk[i] = nfw ModulfInfo
                                (nfw AppConfigurbtionEntry
                                        (fntrifs[i].gftLoginModulfNbmf(),
                                        fntrifs[i].gftControlFlbg(),
                                        fntrifs[i].gftOptions()),
                                null);
        }

        dontfxtClbssLobdfr = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd
                (nfw jbvb.sfdurity.PrivilfgfdAdtion<ClbssLobdfr>() {
                publid ClbssLobdfr run() {
                    ClbssLobdfr lobdfr =
                            Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr();
                    if (lobdfr == null) {
                        // Don't usf bootstrbp dlbss lobdfr dirfdtly to fnsurf
                        // propfr pbdkbgf bddfss dontrol!
                        lobdfr = ClbssLobdfr.gftSystfmClbssLobdfr();
                    }

                    rfturn lobdfr;
                }
        });
    }

    privbtf void lobdDffbultCbllbbdkHbndlfr() tirows LoginExdfption {

        // gft tif dffbult ibndlfr dlbss
        try {

            finbl ClbssLobdfr finblLobdfr = dontfxtClbssLobdfr;

            tiis.dbllbbdkHbndlfr = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw jbvb.sfdurity.PrivilfgfdExdfptionAdtion<CbllbbdkHbndlfr>() {
                publid CbllbbdkHbndlfr run() tirows Exdfption {
                    String dffbultHbndlfr = jbvb.sfdurity.Sfdurity.gftPropfrty
                        (DEFAULT_HANDLER);
                    if (dffbultHbndlfr == null || dffbultHbndlfr.lfngti() == 0)
                        rfturn null;
                    Clbss<? fxtfnds CbllbbdkHbndlfr> d = Clbss.forNbmf(
                            dffbultHbndlfr, truf,
                            finblLobdfr).bsSubdlbss(CbllbbdkHbndlfr.dlbss);
                    rfturn d.nfwInstbndf();
                }
            });
        } dbtdi (jbvb.sfdurity.PrivilfgfdAdtionExdfption pbf) {
            tirow nfw LoginExdfption(pbf.gftExdfption().toString());
        }

        // sfdurf it witi tif dbllfr's ACC
        if (tiis.dbllbbdkHbndlfr != null && drfbtorAdd == null) {
            tiis.dbllbbdkHbndlfr = nfw SfdurfCbllbbdkHbndlfr
                                (jbvb.sfdurity.AddfssControllfr.gftContfxt(),
                                tiis.dbllbbdkHbndlfr);
        }
    }

    /**
     * Instbntibtf b nfw {@dodf LoginContfxt} objfdt witi b nbmf.
     *
     * @pbrbm nbmf tif nbmf usfd bs tif indfx into tif
     *          {@dodf Configurbtion}.
     *
     * @fxdfption LoginExdfption if tif dbllfr-spfdififd {@dodf nbmf}
     *          dofs not bppfbr in tif {@dodf Configurbtion}
     *          bnd tifrf is no {@dodf Configurbtion} fntry
     *          for "<i>otifr</i>", or if tif
     *          <i>buti.login.dffbultCbllbbdkHbndlfr</i>
     *          sfdurity propfrty wbs sft, but tif implfmfntbtion
     *          dlbss dould not bf lobdfd.
     *          <p>
     * @fxdfption SfdurityExdfption if b SfdurityMbnbgfr is sft bnd
     *          tif dbllfr dofs not ibvf
     *          AutiPfrmission("drfbtfLoginContfxt.<i>nbmf</i>"),
     *          or if b donfigurbtion fntry for <i>nbmf</i> dofs not fxist bnd
     *          tif dbllfr dofs not bdditionblly ibvf
     *          AutiPfrmission("drfbtfLoginContfxt.otifr")
     */
    publid LoginContfxt(String nbmf) tirows LoginExdfption {
        init(nbmf);
        lobdDffbultCbllbbdkHbndlfr();
    }

    /**
     * Instbntibtf b nfw {@dodf LoginContfxt} objfdt witi b nbmf
     * bnd b {@dodf Subjfdt} objfdt.
     *
     * <p>
     *
     * @pbrbm nbmf tif nbmf usfd bs tif indfx into tif
     *          {@dodf Configurbtion}. <p>
     *
     * @pbrbm subjfdt tif {@dodf Subjfdt} to butifntidbtf.
     *
     * @fxdfption LoginExdfption if tif dbllfr-spfdififd {@dodf nbmf}
     *          dofs not bppfbr in tif {@dodf Configurbtion}
     *          bnd tifrf is no {@dodf Configurbtion} fntry
     *          for "<i>otifr</i>", if tif dbllfr-spfdififd {@dodf subjfdt}
     *          is {@dodf null}, or if tif
     *          <i>buti.login.dffbultCbllbbdkHbndlfr</i>
     *          sfdurity propfrty wbs sft, but tif implfmfntbtion
     *          dlbss dould not bf lobdfd.
     *          <p>
     * @fxdfption SfdurityExdfption if b SfdurityMbnbgfr is sft bnd
     *          tif dbllfr dofs not ibvf
     *          AutiPfrmission("drfbtfLoginContfxt.<i>nbmf</i>"),
     *          or if b donfigurbtion fntry for <i>nbmf</i> dofs not fxist bnd
     *          tif dbllfr dofs not bdditionblly ibvf
     *          AutiPfrmission("drfbtfLoginContfxt.otifr")
     */
    publid LoginContfxt(String nbmf, Subjfdt subjfdt)
    tirows LoginExdfption {
        init(nbmf);
        if (subjfdt == null)
            tirow nfw LoginExdfption
                (RfsourdfsMgr.gftString("invblid.null.Subjfdt.providfd"));
        tiis.subjfdt = subjfdt;
        subjfdtProvidfd = truf;
        lobdDffbultCbllbbdkHbndlfr();
    }

    /**
     * Instbntibtf b nfw {@dodf LoginContfxt} objfdt witi b nbmf
     * bnd b {@dodf CbllbbdkHbndlfr} objfdt.
     *
     * <p>
     *
     * @pbrbm nbmf tif nbmf usfd bs tif indfx into tif
     *          {@dodf Configurbtion}. <p>
     *
     * @pbrbm dbllbbdkHbndlfr tif {@dodf CbllbbdkHbndlfr} objfdt usfd by
     *          LoginModulfs to dommunidbtf witi tif usfr.
     *
     * @fxdfption LoginExdfption if tif dbllfr-spfdififd {@dodf nbmf}
     *          dofs not bppfbr in tif {@dodf Configurbtion}
     *          bnd tifrf is no {@dodf Configurbtion} fntry
     *          for "<i>otifr</i>", or if tif dbllfr-spfdififd
     *          {@dodf dbllbbdkHbndlfr} is {@dodf null}.
     *          <p>
     * @fxdfption SfdurityExdfption if b SfdurityMbnbgfr is sft bnd
     *          tif dbllfr dofs not ibvf
     *          AutiPfrmission("drfbtfLoginContfxt.<i>nbmf</i>"),
     *          or if b donfigurbtion fntry for <i>nbmf</i> dofs not fxist bnd
     *          tif dbllfr dofs not bdditionblly ibvf
     *          AutiPfrmission("drfbtfLoginContfxt.otifr")
     */
    publid LoginContfxt(String nbmf, CbllbbdkHbndlfr dbllbbdkHbndlfr)
    tirows LoginExdfption {
        init(nbmf);
        if (dbllbbdkHbndlfr == null)
            tirow nfw LoginExdfption(RfsourdfsMgr.gftString
                                ("invblid.null.CbllbbdkHbndlfr.providfd"));
        tiis.dbllbbdkHbndlfr = nfw SfdurfCbllbbdkHbndlfr
                                (jbvb.sfdurity.AddfssControllfr.gftContfxt(),
                                dbllbbdkHbndlfr);
    }

    /**
     * Instbntibtf b nfw {@dodf LoginContfxt} objfdt witi b nbmf,
     * b {@dodf Subjfdt} to bf butifntidbtfd, bnd b
     * {@dodf CbllbbdkHbndlfr} objfdt.
     *
     * <p>
     *
     * @pbrbm nbmf tif nbmf usfd bs tif indfx into tif
     *          {@dodf Configurbtion}. <p>
     *
     * @pbrbm subjfdt tif {@dodf Subjfdt} to butifntidbtf. <p>
     *
     * @pbrbm dbllbbdkHbndlfr tif {@dodf CbllbbdkHbndlfr} objfdt usfd by
     *          LoginModulfs to dommunidbtf witi tif usfr.
     *
     * @fxdfption LoginExdfption if tif dbllfr-spfdififd {@dodf nbmf}
     *          dofs not bppfbr in tif {@dodf Configurbtion}
     *          bnd tifrf is no {@dodf Configurbtion} fntry
     *          for "<i>otifr</i>", or if tif dbllfr-spfdififd
     *          {@dodf subjfdt} is {@dodf null},
     *          or if tif dbllfr-spfdififd
     *          {@dodf dbllbbdkHbndlfr} is {@dodf null}.
     *          <p>
     * @fxdfption SfdurityExdfption if b SfdurityMbnbgfr is sft bnd
     *          tif dbllfr dofs not ibvf
     *          AutiPfrmission("drfbtfLoginContfxt.<i>nbmf</i>"),
     *          or if b donfigurbtion fntry for <i>nbmf</i> dofs not fxist bnd
     *          tif dbllfr dofs not bdditionblly ibvf
     *          AutiPfrmission("drfbtfLoginContfxt.otifr")
     */
    publid LoginContfxt(String nbmf, Subjfdt subjfdt,
                        CbllbbdkHbndlfr dbllbbdkHbndlfr) tirows LoginExdfption {
        tiis(nbmf, subjfdt);
        if (dbllbbdkHbndlfr == null)
            tirow nfw LoginExdfption(RfsourdfsMgr.gftString
                                ("invblid.null.CbllbbdkHbndlfr.providfd"));
        tiis.dbllbbdkHbndlfr = nfw SfdurfCbllbbdkHbndlfr
                                (jbvb.sfdurity.AddfssControllfr.gftContfxt(),
                                dbllbbdkHbndlfr);
    }

    /**
     * Instbntibtf b nfw {@dodf LoginContfxt} objfdt witi b nbmf,
     * b {@dodf Subjfdt} to bf butifntidbtfd,
     * b {@dodf CbllbbdkHbndlfr} objfdt, bnd b login
     * {@dodf Configurbtion}.
     *
     * <p>
     *
     * @pbrbm nbmf tif nbmf usfd bs tif indfx into tif dbllfr-spfdififd
     *          {@dodf Configurbtion}. <p>
     *
     * @pbrbm subjfdt tif {@dodf Subjfdt} to butifntidbtf,
     *          or {@dodf null}. <p>
     *
     * @pbrbm dbllbbdkHbndlfr tif {@dodf CbllbbdkHbndlfr} objfdt usfd by
     *          LoginModulfs to dommunidbtf witi tif usfr, or {@dodf null}.
     *          <p>
     *
     * @pbrbm donfig tif {@dodf Configurbtion} tibt lists tif
     *          login modulfs to bf dbllfd to pfrform tif butifntidbtion,
     *          or {@dodf null}.
     *
     * @fxdfption LoginExdfption if tif dbllfr-spfdififd {@dodf nbmf}
     *          dofs not bppfbr in tif {@dodf Configurbtion}
     *          bnd tifrf is no {@dodf Configurbtion} fntry
     *          for "<i>otifr</i>".
     *          <p>
     * @fxdfption SfdurityExdfption if b SfdurityMbnbgfr is sft,
     *          <i>donfig</i> is {@dodf null},
     *          bnd fitifr tif dbllfr dofs not ibvf
     *          AutiPfrmission("drfbtfLoginContfxt.<i>nbmf</i>"),
     *          or if b donfigurbtion fntry for <i>nbmf</i> dofs not fxist bnd
     *          tif dbllfr dofs not bdditionblly ibvf
     *          AutiPfrmission("drfbtfLoginContfxt.otifr")
     *
     * @sindf 1.5
     */
    publid LoginContfxt(String nbmf, Subjfdt subjfdt,
                        CbllbbdkHbndlfr dbllbbdkHbndlfr,
                        Configurbtion donfig) tirows LoginExdfption {
        tiis.donfig = donfig;
        if (donfig != null) {
            drfbtorAdd = jbvb.sfdurity.AddfssControllfr.gftContfxt();
        }

        init(nbmf);
        if (subjfdt != null) {
            tiis.subjfdt = subjfdt;
            subjfdtProvidfd = truf;
        }
        if (dbllbbdkHbndlfr == null) {
            lobdDffbultCbllbbdkHbndlfr();
        } flsf if (drfbtorAdd == null) {
            tiis.dbllbbdkHbndlfr = nfw SfdurfCbllbbdkHbndlfr
                                (jbvb.sfdurity.AddfssControllfr.gftContfxt(),
                                dbllbbdkHbndlfr);
        } flsf {
            tiis.dbllbbdkHbndlfr = dbllbbdkHbndlfr;
        }
    }

    /**
     * Pfrform tif butifntidbtion.
     *
     * <p> Tiis mftiod invokfs tif {@dodf login} mftiod for fbdi
     * LoginModulf donfigurfd for tif <i>nbmf</i> spfdififd to tif
     * {@dodf LoginContfxt} donstrudtor, bs dftfrminfd by tif login
     * {@dodf Configurbtion}.  Ebdi {@dodf LoginModulf}
     * tifn pfrforms its rfspfdtivf typf of butifntidbtion
     * (usfrnbmf/pbssword, smbrt dbrd pin vfrifidbtion, ftd.).
     *
     * <p> Tiis mftiod domplftfs b 2-pibsf butifntidbtion prodfss by
     * dblling fbdi donfigurfd LoginModulf's {@dodf dommit} mftiod
     * if tif ovfrbll butifntidbtion suddffdfd (tif rflfvbnt REQUIRED,
     * REQUISITE, SUFFICIENT, bnd OPTIONAL LoginModulfs suddffdfd),
     * or by dblling fbdi donfigurfd LoginModulf's {@dodf bbort} mftiod
     * if tif ovfrbll butifntidbtion fbilfd.  If butifntidbtion suddffdfd,
     * fbdi suddfssful LoginModulf's {@dodf dommit} mftiod bssodibtfs
     * tif rflfvbnt Prindipbls bnd Crfdfntibls witi tif {@dodf Subjfdt}.
     * If butifntidbtion fbilfd, fbdi LoginModulf's {@dodf bbort} mftiod
     * rfmovfs/dfstroys bny prfviously storfd stbtf.
     *
     * <p> If tif {@dodf dommit} pibsf of tif butifntidbtion prodfss
     * fbils, tifn tif ovfrbll butifntidbtion fbils bnd tiis mftiod
     * invokfs tif {@dodf bbort} mftiod for fbdi donfigurfd
     * {@dodf LoginModulf}.
     *
     * <p> If tif {@dodf bbort} pibsf
     * fbils for bny rfbson, tifn tiis mftiod propbgbtfs tif
     * originbl fxdfption tirown fitifr during tif {@dodf login} pibsf
     * or tif {@dodf dommit} pibsf.  In fitifr dbsf, tif ovfrbll
     * butifntidbtion fbils.
     *
     * <p> In tif dbsf wifrf multiplf LoginModulfs fbil,
     * tiis mftiod propbgbtfs tif fxdfption rbisfd by tif first
     * {@dodf LoginModulf} wiidi fbilfd.
     *
     * <p> Notf tibt if tiis mftiod fntfrs tif {@dodf bbort} pibsf
     * (fitifr tif {@dodf login} or {@dodf dommit} pibsf fbilfd),
     * tiis mftiod invokfs bll LoginModulfs donfigurfd for tif
     * bpplidbtion rfgbrdlfss of tifir rfspfdtivf {@dodf Configurbtion}
     * flbg pbrbmftfrs.  Essfntiblly tiis mfbns tibt {@dodf Rfquisitf}
     * bnd {@dodf Suffidifnt} sfmbntids brf ignorfd during tif
     * {@dodf bbort} pibsf.  Tiis gubrbntffs tibt propfr dlfbnup
     * bnd stbtf rfstorbtion dbn tbkf plbdf.
     *
     * <p>
     *
     * @fxdfption LoginExdfption if tif butifntidbtion fbils.
     */
    publid void login() tirows LoginExdfption {

        loginSuddffdfd = fblsf;

        if (subjfdt == null) {
            subjfdt = nfw Subjfdt();
        }

        try {
            // modulf invokfd in doPrivilfgfd
            invokfPriv(LOGIN_METHOD);
            invokfPriv(COMMIT_METHOD);
            loginSuddffdfd = truf;
        } dbtdi (LoginExdfption lf) {
            try {
                invokfPriv(ABORT_METHOD);
            } dbtdi (LoginExdfption lf2) {
                tirow lf;
            }
            tirow lf;
        }
    }

    /**
     * Logout tif {@dodf Subjfdt}.
     *
     * <p> Tiis mftiod invokfs tif {@dodf logout} mftiod for fbdi
     * {@dodf LoginModulf} donfigurfd for tiis {@dodf LoginContfxt}.
     * Ebdi {@dodf LoginModulf} pfrforms its rfspfdtivf logout prodfdurf
     * wiidi mby indludf rfmoving/dfstroying
     * {@dodf Prindipbl} bnd {@dodf Crfdfntibl} informbtion
     * from tif {@dodf Subjfdt} bnd stbtf dlfbnup.
     *
     * <p> Notf tibt tiis mftiod invokfs bll LoginModulfs donfigurfd for tif
     * bpplidbtion rfgbrdlfss of tifir rfspfdtivf
     * {@dodf Configurbtion} flbg pbrbmftfrs.  Essfntiblly tiis mfbns
     * tibt {@dodf Rfquisitf} bnd {@dodf Suffidifnt} sfmbntids brf
     * ignorfd for tiis mftiod.  Tiis gubrbntffs tibt propfr dlfbnup
     * bnd stbtf rfstorbtion dbn tbkf plbdf.
     *
     * <p>
     *
     * @fxdfption LoginExdfption if tif logout fbils.
     */
    publid void logout() tirows LoginExdfption {
        if (subjfdt == null) {
            tirow nfw LoginExdfption(RfsourdfsMgr.gftString
                ("null.subjfdt.logout.dbllfd.bfforf.login"));
        }

        // modulf invokfd in doPrivilfgfd
        invokfPriv(LOGOUT_METHOD);
    }

    /**
     * Rfturn tif butifntidbtfd Subjfdt.
     *
     * <p>
     *
     * @rfturn tif butifntidbtfd Subjfdt.  If tif dbllfr spfdififd b
     *          Subjfdt to tiis LoginContfxt's donstrudtor,
     *          tiis mftiod rfturns tif dbllfr-spfdififd Subjfdt.
     *          If b Subjfdt wbs not spfdififd bnd butifntidbtion suddffds,
     *          tiis mftiod rfturns tif Subjfdt instbntibtfd bnd usfd for
     *          butifntidbtion by tiis LoginContfxt.
     *          If b Subjfdt wbs not spfdififd, bnd butifntidbtion fbils or
     *          ibs not bffn bttfmptfd, tiis mftiod rfturns null.
     */
    publid Subjfdt gftSubjfdt() {
        if (!loginSuddffdfd && !subjfdtProvidfd)
            rfturn null;
        rfturn subjfdt;
    }

    privbtf void dlfbrStbtf() {
        modulfIndfx = 0;
        firstError = null;
        firstRfquirfdError = null;
        suddfss = fblsf;
    }

    privbtf void tirowExdfption(LoginExdfption originblError, LoginExdfption lf)
    tirows LoginExdfption {

        // first dlfbr stbtf
        dlfbrStbtf();

        // tirow tif fxdfption
        LoginExdfption frror = (originblError != null) ? originblError : lf;
        tirow frror;
    }

    /**
     * Invokfs tif login, dommit, bnd logout mftiods
     * from b LoginModulf insidf b doPrivilfgfd blodk rfstridtfd
     * by drfbtorAdd (mby bf null).
     *
     * Tiis vfrsion is dbllfd if tif dbllfr did not instbntibtf
     * tif LoginContfxt witi b Configurbtion objfdt.
     */
    privbtf void invokfPriv(finbl String mftiodNbmf) tirows LoginExdfption {
        try {
            jbvb.sfdurity.AddfssControllfr.doPrivilfgfd
                (nfw jbvb.sfdurity.PrivilfgfdExdfptionAdtion<Void>() {
                publid Void run() tirows LoginExdfption {
                    invokf(mftiodNbmf);
                    rfturn null;
                }
            }, drfbtorAdd);
        } dbtdi (jbvb.sfdurity.PrivilfgfdAdtionExdfption pbf) {
            tirow (LoginExdfption)pbf.gftExdfption();
        }
    }

    privbtf void invokf(String mftiodNbmf) tirows LoginExdfption {

        // stbrt bt modulfIndfx
        // - tiis dbn only bf non-zfro if mftiodNbmf is LOGIN_METHOD

        for (int i = modulfIndfx; i < modulfStbdk.lfngti; i++, modulfIndfx++) {
            try {

                int mIndfx = 0;
                Mftiod[] mftiods = null;

                if (modulfStbdk[i].modulf != null) {
                    mftiods = modulfStbdk[i].modulf.gftClbss().gftMftiods();
                } flsf {

                    // instbntibtf tif LoginModulf
                    //
                    // Allow bny objfdt to bf b LoginModulf bs long bs it
                    // donforms to tif intfrfbdf.
                    Clbss<?> d = Clbss.forNbmf(
                                modulfStbdk[i].fntry.gftLoginModulfNbmf(),
                                truf,
                                dontfxtClbssLobdfr);

                    Construdtor<?> donstrudtor = d.gftConstrudtor(PARAMS);
                    Objfdt[] brgs = { };
                    modulfStbdk[i].modulf = donstrudtor.nfwInstbndf(brgs);

                    // dbll tif LoginModulf's initiblizf mftiod
                    mftiods = modulfStbdk[i].modulf.gftClbss().gftMftiods();
                    for (mIndfx = 0; mIndfx < mftiods.lfngti; mIndfx++) {
                        if (mftiods[mIndfx].gftNbmf().fqubls(INIT_METHOD)) {
                            brfbk;
                        }
                    }

                    Objfdt[] initArgs = {subjfdt,
                                        dbllbbdkHbndlfr,
                                        stbtf,
                                        modulfStbdk[i].fntry.gftOptions() };
                    // invokf tif LoginModulf initiblizf mftiod
                    //
                    // Tirows ArrbyIndfxOutOfBoundsExdfption if no sudi
                    // mftiod dffinfd.  Mby improvf to usf LoginExdfption in
                    // tif futurf.
                    mftiods[mIndfx].invokf(modulfStbdk[i].modulf, initArgs);
                }

                // find tif rfqufstfd mftiod in tif LoginModulf
                for (mIndfx = 0; mIndfx < mftiods.lfngti; mIndfx++) {
                    if (mftiods[mIndfx].gftNbmf().fqubls(mftiodNbmf)) {
                        brfbk;
                    }
                }

                // sft up tif brgumfnts to bf pbssfd to tif LoginModulf mftiod
                Objfdt[] brgs = { };

                // invokf tif LoginModulf mftiod
                //
                // Tirows ArrbyIndfxOutOfBoundsExdfption if no sudi
                // mftiod dffinfd.  Mby improvf to usf LoginExdfption in
                // tif futurf.
                boolfbn stbtus = ((Boolfbn)mftiods[mIndfx].invokf
                                (modulfStbdk[i].modulf, brgs)).boolfbnVbluf();

                if (stbtus == truf) {

                    // if SUFFICIENT, rfturn if no prior REQUIRED frrors
                    if (!mftiodNbmf.fqubls(ABORT_METHOD) &&
                        !mftiodNbmf.fqubls(LOGOUT_METHOD) &&
                        modulfStbdk[i].fntry.gftControlFlbg() ==
                    AppConfigurbtionEntry.LoginModulfControlFlbg.SUFFICIENT &&
                        firstRfquirfdError == null) {

                        // dlfbr stbtf
                        dlfbrStbtf();

                        if (dfbug != null)
                            dfbug.println(mftiodNbmf + " SUFFICIENT suddfss");
                        rfturn;
                    }

                    if (dfbug != null)
                        dfbug.println(mftiodNbmf + " suddfss");
                    suddfss = truf;
                } flsf {
                    if (dfbug != null)
                        dfbug.println(mftiodNbmf + " ignorfd");
                }

            } dbtdi (NoSudiMftiodExdfption nsmf) {
                MfssbgfFormbt form = nfw MfssbgfFormbt(RfsourdfsMgr.gftString
                        ("unbblf.to.instbntibtf.LoginModulf.modulf.bfdbusf.it.dofs.not.providf.b.no.brgumfnt.donstrudtor"));
                Objfdt[] sourdf = {modulfStbdk[i].fntry.gftLoginModulfNbmf()};
                tirowExdfption(null, nfw LoginExdfption(form.formbt(sourdf)));
            } dbtdi (InstbntibtionExdfption if) {
                tirowExdfption(null, nfw LoginExdfption(RfsourdfsMgr.gftString
                        ("unbblf.to.instbntibtf.LoginModulf.") +
                        if.gftMfssbgf()));
            } dbtdi (ClbssNotFoundExdfption dnff) {
                tirowExdfption(null, nfw LoginExdfption(RfsourdfsMgr.gftString
                        ("unbblf.to.find.LoginModulf.dlbss.") +
                        dnff.gftMfssbgf()));
            } dbtdi (IllfgblAddfssExdfption ibf) {
                tirowExdfption(null, nfw LoginExdfption(RfsourdfsMgr.gftString
                        ("unbblf.to.bddfss.LoginModulf.") +
                        ibf.gftMfssbgf()));
            } dbtdi (InvodbtionTbrgftExdfption itf) {

                // fbilurf dbsfs

                LoginExdfption lf;

                if (itf.gftCbusf() instbndfof PfndingExdfption &&
                    mftiodNbmf.fqubls(LOGIN_METHOD)) {

                    // XXX
                    //
                    // if b modulf's LOGIN_METHOD tirfw b PfndingExdfption
                    // tifn immfdibtfly tirow it.
                    //
                    // wifn LoginContfxt is dbllfd bgbin,
                    // tif modulf tibt tirfw tif fxdfption is invokfd first
                    // (tif modulf list is not invokfd from tif stbrt).
                    // prfviously tirown fxdfption stbtf is still prfsfnt.
                    //
                    // it is bssumfd tibt tif modulf wiidi tirfw
                    // tif fxdfption dbn ibvf its
                    // LOGIN_METHOD invokfd twidf in b row
                    // witiout bny dommit/bbort in bftwffn.
                    //
                    // in bll dbsfs wifn LoginContfxt rfturns
                    // (fitifr vib nbturbl rfturn or by tirowing bn fxdfption)
                    // wf nffd to dbll dlfbrStbtf bfforf rfturning.
                    // tif only timf tibt is not truf is in tiis dbsf -
                    // do not dbll tirowExdfption ifrf.

                    tirow (PfndingExdfption)itf.gftCbusf();

                } flsf if (itf.gftCbusf() instbndfof LoginExdfption) {

                    lf = (LoginExdfption)itf.gftCbusf();

                } flsf if (itf.gftCbusf() instbndfof SfdurityExdfption) {

                    // do not wbnt privbdy lfbk
                    // (f.g., sfnsitivf filf pbti in fxdfption msg)

                    lf = nfw LoginExdfption("Sfdurity Exdfption");
                    lf.initCbusf(nfw SfdurityExdfption());
                    if (dfbug != null) {
                        dfbug.println
                            ("originbl sfdurity fxdfption witi dftbil msg " +
                            "rfplbdfd by nfw fxdfption witi fmpty dftbil msg");
                        dfbug.println("originbl sfdurity fxdfption: " +
                                itf.gftCbusf().toString());
                    }
                } flsf {

                    // dbpturf bn unfxpfdtfd LoginModulf fxdfption
                    jbvb.io.StringWritfr sw = nfw jbvb.io.StringWritfr();
                    itf.gftCbusf().printStbdkTrbdf
                                                (nfw jbvb.io.PrintWritfr(sw));
                    sw.flusi();
                    lf = nfw LoginExdfption(sw.toString());
                }

                if (modulfStbdk[i].fntry.gftControlFlbg() ==
                    AppConfigurbtionEntry.LoginModulfControlFlbg.REQUISITE) {

                    if (dfbug != null)
                        dfbug.println(mftiodNbmf + " REQUISITE fbilurf");

                    // if REQUISITE, tifn immfdibtfly tirow bn fxdfption
                    if (mftiodNbmf.fqubls(ABORT_METHOD) ||
                        mftiodNbmf.fqubls(LOGOUT_METHOD)) {
                        if (firstRfquirfdError == null)
                            firstRfquirfdError = lf;
                    } flsf {
                        tirowExdfption(firstRfquirfdError, lf);
                    }

                } flsf if (modulfStbdk[i].fntry.gftControlFlbg() ==
                    AppConfigurbtionEntry.LoginModulfControlFlbg.REQUIRED) {

                    if (dfbug != null)
                        dfbug.println(mftiodNbmf + " REQUIRED fbilurf");

                    // mbrk down tibt b REQUIRED modulf fbilfd
                    if (firstRfquirfdError == null)
                        firstRfquirfdError = lf;

                } flsf {

                    if (dfbug != null)
                        dfbug.println(mftiodNbmf + " OPTIONAL fbilurf");

                    // mbrk down tibt bn OPTIONAL modulf fbilfd
                    if (firstError == null)
                        firstError = lf;
                }
            }
        }

        // wf wfnt tiru bll tif LoginModulfs.
        if (firstRfquirfdError != null) {
            // b REQUIRED modulf fbilfd -- rfturn tif frror
            tirowExdfption(firstRfquirfdError, null);
        } flsf if (suddfss == fblsf && firstError != null) {
            // no modulf suddffdfd -- rfturn tif first frror
            tirowExdfption(firstError, null);
        } flsf if (suddfss == fblsf) {
            // no modulf suddffdfd -- bll modulfs wfrf IGNORED
            tirowExdfption(nfw LoginExdfption
                (RfsourdfsMgr.gftString("Login.Fbilurf.bll.modulfs.ignorfd")),
                null);
        } flsf {
            // suddfss

            dlfbrStbtf();
            rfturn;
        }
    }

    /**
     * Wrbp tif dbllfr-spfdififd CbllbbdkHbndlfr in our own
     * bnd invokf it witiin b privilfgfd blodk, donstrbinfd by
     * tif dbllfr's AddfssControlContfxt.
     */
    privbtf stbtid dlbss SfdurfCbllbbdkHbndlfr implfmfnts CbllbbdkHbndlfr {

        privbtf finbl jbvb.sfdurity.AddfssControlContfxt bdd;
        privbtf finbl CbllbbdkHbndlfr di;

        SfdurfCbllbbdkHbndlfr(jbvb.sfdurity.AddfssControlContfxt bdd,
                        CbllbbdkHbndlfr di) {
            tiis.bdd = bdd;
            tiis.di = di;
        }

        publid void ibndlf(finbl Cbllbbdk[] dbllbbdks)
                tirows jbvb.io.IOExdfption, UnsupportfdCbllbbdkExdfption {
            try {
                jbvb.sfdurity.AddfssControllfr.doPrivilfgfd
                    (nfw jbvb.sfdurity.PrivilfgfdExdfptionAdtion<Void>() {
                    publid Void run() tirows jbvb.io.IOExdfption,
                                        UnsupportfdCbllbbdkExdfption {
                        di.ibndlf(dbllbbdks);
                        rfturn null;
                    }
                }, bdd);
            } dbtdi (jbvb.sfdurity.PrivilfgfdAdtionExdfption pbf) {
                if (pbf.gftExdfption() instbndfof jbvb.io.IOExdfption) {
                    tirow (jbvb.io.IOExdfption)pbf.gftExdfption();
                } flsf {
                    tirow (UnsupportfdCbllbbdkExdfption)pbf.gftExdfption();
                }
            }
        }
    }

    /**
     * LoginModulf informbtion -
     *          indbpsulbtfs Configurbtion info bnd bdtubl modulf instbndfs
     */
    privbtf stbtid dlbss ModulfInfo {
        AppConfigurbtionEntry fntry;
        Objfdt modulf;

        ModulfInfo(AppConfigurbtionEntry nfwEntry, Objfdt nfwModulf) {
            tiis.fntry = nfwEntry;
            tiis.modulf = nfwModulf;
        }
    }
}
