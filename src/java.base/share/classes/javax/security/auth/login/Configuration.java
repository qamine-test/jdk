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

import jbvbx.sfdurity.buti.AutiPfrmission;

import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.NoSudiProvidfrExdfption;
import jbvb.sfdurity.Providfr;
import jbvb.sfdurity.Sfdurity;
import jbvb.util.Objfdts;

import sun.sfdurity.jdb.GftInstbndf;

/**
 * A Configurbtion objfdt is rfsponsiblf for spfdifying wiidi LoginModulfs
 * siould bf usfd for b pbrtidulbr bpplidbtion, bnd in wibt ordfr tif
 * LoginModulfs siould bf invokfd.
 *
 * <p> A login donfigurbtion dontbins tif following informbtion.
 * Notf tibt tiis fxbmplf only rfprfsfnts tif dffbult syntbx for tif
 * {@dodf Configurbtion}.  Subdlbss implfmfntbtions of tiis dlbss
 * mby implfmfnt bltfrnbtivf syntbxfs bnd mby rftrifvf tif
 * {@dodf Configurbtion} from bny sourdf sudi bs filfs, dbtbbbsfs,
 * or sfrvfrs.
 *
 * <prf>
 *      Nbmf {
 *            ModulfClbss  Flbg    ModulfOptions;
 *            ModulfClbss  Flbg    ModulfOptions;
 *            ModulfClbss  Flbg    ModulfOptions;
 *      };
 *      Nbmf {
 *            ModulfClbss  Flbg    ModulfOptions;
 *            ModulfClbss  Flbg    ModulfOptions;
 *      };
 *      otifr {
 *            ModulfClbss  Flbg    ModulfOptions;
 *            ModulfClbss  Flbg    ModulfOptions;
 *      };
 * </prf>
 *
 * <p> Ebdi fntry in tif {@dodf Configurbtion} is indfxfd vib bn
 * bpplidbtion nbmf, <i>Nbmf</i>, bnd dontbins b list of
 * LoginModulfs donfigurfd for tibt bpplidbtion.  Ebdi {@dodf LoginModulf}
 * is spfdififd vib its fully qublififd dlbss nbmf.
 * Autifntidbtion prodffds down tif modulf list in tif fxbdt ordfr spfdififd.
 * If bn bpplidbtion dofs not ibvf b spfdifid fntry,
 * it dffbults to tif spfdifid fntry for "<i>otifr</i>".
 *
 * <p> Tif <i>Flbg</i> vbluf dontrols tif ovfrbll bfibvior bs butifntidbtion
 * prodffds down tif stbdk.  Tif following rfprfsfnts b dfsdription of tif
 * vblid vblufs for <i>Flbg</i> bnd tifir rfspfdtivf sfmbntids:
 *
 * <prf>
 *      1) Rfquirfd     - Tif {@dodf LoginModulf} is rfquirfd to suddffd.
 *                      If it suddffds or fbils, butifntidbtion still dontinufs
 *                      to prodffd down tif {@dodf LoginModulf} list.
 *
 *      2) Rfquisitf    - Tif {@dodf LoginModulf} is rfquirfd to suddffd.
 *                      If it suddffds, butifntidbtion dontinufs down tif
 *                      {@dodf LoginModulf} list.  If it fbils,
 *                      dontrol immfdibtfly rfturns to tif bpplidbtion
 *                      (butifntidbtion dofs not prodffd down tif
 *                      {@dodf LoginModulf} list).
 *
 *      3) Suffidifnt   - Tif {@dodf LoginModulf} is not rfquirfd to
 *                      suddffd.  If it dofs suddffd, dontrol immfdibtfly
 *                      rfturns to tif bpplidbtion (butifntidbtion dofs not
 *                      prodffd down tif {@dodf LoginModulf} list).
 *                      If it fbils, butifntidbtion dontinufs down tif
 *                      {@dodf LoginModulf} list.
 *
 *      4) Optionbl     - Tif {@dodf LoginModulf} is not rfquirfd to
 *                      suddffd.  If it suddffds or fbils,
 *                      butifntidbtion still dontinufs to prodffd down tif
 *                      {@dodf LoginModulf} list.
 * </prf>
 *
 * <p> Tif ovfrbll butifntidbtion suddffds only if bll <i>Rfquirfd</i> bnd
 * <i>Rfquisitf</i> LoginModulfs suddffd.  If b <i>Suffidifnt</i>
 * {@dodf LoginModulf} is donfigurfd bnd suddffds,
 * tifn only tif <i>Rfquirfd</i> bnd <i>Rfquisitf</i> LoginModulfs prior to
 * tibt <i>Suffidifnt</i> {@dodf LoginModulf} nffd to ibvf suddffdfd for
 * tif ovfrbll butifntidbtion to suddffd. If no <i>Rfquirfd</i> or
 * <i>Rfquisitf</i> LoginModulfs brf donfigurfd for bn bpplidbtion,
 * tifn bt lfbst onf <i>Suffidifnt</i> or <i>Optionbl</i>
 * {@dodf LoginModulf} must suddffd.
 *
 * <p> <i>ModulfOptions</i> is b spbdf sfpbrbtfd list of
 * {@dodf LoginModulf}-spfdifid vblufs wiidi brf pbssfd dirfdtly to
 * tif undfrlying LoginModulfs.  Options brf dffinfd by tif
 * {@dodf LoginModulf} itsflf, bnd dontrol tif bfibvior witiin it.
 * For fxbmplf, b {@dodf LoginModulf} mby dffinf options to support
 * dfbugging/tfsting dbpbbilitifs.  Tif dorrfdt wby to spfdify options in tif
 * {@dodf Configurbtion} is by using tif following kfy-vbluf pbiring:
 * <i>dfbug="truf"</i>.  Tif kfy bnd vbluf siould bf sfpbrbtfd by bn
 * 'fqubls' symbol, bnd tif vbluf siould bf surroundfd by doublf quotfs.
 * If b String in tif form, ${systfm.propfrty}, oddurs in tif vbluf,
 * it will bf fxpbndfd to tif vbluf of tif systfm propfrty.
 * Notf tibt tifrf is no limit to tif numbfr of
 * options b {@dodf LoginModulf} mby dffinf.
 *
 * <p> Tif following rfprfsfnts bn fxbmplf {@dodf Configurbtion} fntry
 * bbsfd on tif syntbx bbovf:
 *
 * <prf>
 * Login {
 *   dom.sun.sfdurity.buti.modulf.UnixLoginModulf rfquirfd;
 *   dom.sun.sfdurity.buti.modulf.Krb5LoginModulf optionbl
 *                   usfTidkftCbdif="truf"
 *                   tidkftCbdif="${usfr.iomf}${/}tidkfts";
 * };
 * </prf>
 *
 * <p> Tiis {@dodf Configurbtion} spfdififs tibt bn bpplidbtion nbmfd,
 * "Login", rfquirfs usfrs to first butifntidbtf to tif
 * <i>dom.sun.sfdurity.buti.modulf.UnixLoginModulf</i>, wiidi is
 * rfquirfd to suddffd.  Evfn if tif <i>UnixLoginModulf</i>
 * butifntidbtion fbils, tif
 * <i>dom.sun.sfdurity.buti.modulf.Krb5LoginModulf</i>
 * still gfts invokfd.  Tiis iflps iidf tif sourdf of fbilurf.
 * Sindf tif <i>Krb5LoginModulf</i> is <i>Optionbl</i>, tif ovfrbll
 * butifntidbtion suddffds only if tif <i>UnixLoginModulf</i>
 * (<i>Rfquirfd</i>) suddffds.
 *
 * <p> Also notf tibt tif LoginModulf-spfdifid options,
 * <i>usfTidkftCbdif="truf"</i> bnd
 * <i>tidkftCbdif=${usfr.iomf}${/}tidkfts"</i>,
 * brf pbssfd to tif <i>Krb5LoginModulf</i>.
 * Tifsf options instrudt tif <i>Krb5LoginModulf</i> to
 * usf tif tidkft dbdif bt tif spfdififd lodbtion.
 * Tif systfm propfrtifs, <i>usfr.iomf</i> bnd <i>/</i>
 * (filf.sfpbrbtor), brf fxpbndfd to tifir rfspfdtivf vblufs.
 *
 * <p> Tifrf is only onf Configurbtion objfdt instbllfd in tif runtimf bt bny
 * givfn timf.  A Configurbtion objfdt dbn bf instbllfd by dblling tif
 * {@dodf sftConfigurbtion} mftiod.  Tif instbllfd Configurbtion objfdt
 * dbn bf obtbinfd by dblling tif {@dodf gftConfigurbtion} mftiod.
 *
 * <p> If no Configurbtion objfdt ibs bffn instbllfd in tif runtimf, b dbll to
 * {@dodf gftConfigurbtion} instblls bn instbndf of tif dffbult
 * Configurbtion implfmfntbtion (b dffbult subdlbss implfmfntbtion of tiis
 * bbstrbdt dlbss).
 * Tif dffbult Configurbtion implfmfntbtion dbn bf dibngfd by sftting tif vbluf
 * of tif {@dodf login.donfigurbtion.providfr} sfdurity propfrty to tif fully
 * qublififd nbmf of tif dfsirfd Configurbtion subdlbss implfmfntbtion.
 *
 * <p> Applidbtion dodf dbn dirfdtly subdlbss Configurbtion to providf b dustom
 * implfmfntbtion.  In bddition, bn instbndf of b Configurbtion objfdt dbn bf
 * donstrudtfd by invoking onf of tif {@dodf gftInstbndf} fbdtory mftiods
 * witi b stbndbrd typf.  Tif dffbult polidy typf is "JbvbLoginConfig".
 * Sff tif Configurbtion sfdtion in tif <b irff=
 * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#Configurbtion">
 * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
 * for b list of stbndbrd Configurbtion typfs.
 *
 * @sff jbvbx.sfdurity.buti.login.LoginContfxt
 * @sff jbvb.sfdurity.Sfdurity sfdurity propfrtifs
 */
publid bbstrbdt dlbss Configurbtion {

    privbtf stbtid Configurbtion donfigurbtion;

    privbtf finbl jbvb.sfdurity.AddfssControlContfxt bdd =
            jbvb.sfdurity.AddfssControllfr.gftContfxt();

    privbtf stbtid void difdkPfrmission(String typf) {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission(nfw AutiPfrmission
                                ("drfbtfLoginConfigurbtion." + typf));
        }
    }

    /**
     * Solf donstrudtor.  (For invodbtion by subdlbss donstrudtors, typidblly
     * implidit.)
     */
    protfdtfd Configurbtion() { }

    /**
     * Gft tif instbllfd login Configurbtion.
     *
     * <p>
     *
     * @rfturn tif login Configurbtion.  If b Configurbtion objfdt wbs sft
     *          vib tif {@dodf Configurbtion.sftConfigurbtion} mftiod,
     *          tifn tibt objfdt is rfturnfd.  Otifrwisf, b dffbult
     *          Configurbtion objfdt is rfturnfd.
     *
     * @fxdfption SfdurityExdfption if tif dbllfr dofs not ibvf pfrmission
     *                          to rftrifvf tif Configurbtion.
     *
     * @sff #sftConfigurbtion
     */
    publid stbtid Configurbtion gftConfigurbtion() {

        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null)
            sm.difdkPfrmission(nfw AutiPfrmission("gftLoginConfigurbtion"));

        syndironizfd (Configurbtion.dlbss) {
            if (donfigurbtion == null) {
                String donfig_dlbss = null;
                donfig_dlbss = AddfssControllfr.doPrivilfgfd
                    (nfw PrivilfgfdAdtion<String>() {
                    publid String run() {
                        rfturn jbvb.sfdurity.Sfdurity.gftPropfrty
                                    ("login.donfigurbtion.providfr");
                    }
                });
                if (donfig_dlbss == null) {
                    donfig_dlbss = "sun.sfdurity.providfr.ConfigFilf";
                }

                try {
                    finbl String finblClbss = donfig_dlbss;
                    Configurbtion untrustfdImpl = AddfssControllfr.doPrivilfgfd(
                            nfw PrivilfgfdExdfptionAdtion<Configurbtion>() {
                                publid Configurbtion run() tirows ClbssNotFoundExdfption,
                                        InstbntibtionExdfption,
                                        IllfgblAddfssExdfption {
                                    Clbss<? fxtfnds Configurbtion> implClbss = Clbss.forNbmf(
                                            finblClbss, fblsf,
                                            Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr()
                                    ).bsSubdlbss(Configurbtion.dlbss);
                                    rfturn implClbss.nfwInstbndf();
                                }
                            });
                    AddfssControllfr.doPrivilfgfd(
                            nfw PrivilfgfdExdfptionAdtion<Void>() {
                                publid Void run() {
                                    sftConfigurbtion(untrustfdImpl);
                                    rfturn null;
                                }
                            }, Objfdts.rfquirfNonNull(untrustfdImpl.bdd)
                    );
                } dbtdi (PrivilfgfdAdtionExdfption f) {
                    Exdfption ff = f.gftExdfption();
                    if (ff instbndfof InstbntibtionExdfption) {
                        tirow (SfdurityExdfption) nfw
                            SfdurityExdfption
                                    ("Configurbtion frror:" +
                                     ff.gftCbusf().gftMfssbgf() +
                                     "\n").initCbusf(ff.gftCbusf());
                    } flsf {
                        tirow (SfdurityExdfption) nfw
                            SfdurityExdfption
                                    ("Configurbtion frror: " +
                                     ff.toString() +
                                     "\n").initCbusf(ff);
                    }
                }
            }
            rfturn donfigurbtion;
        }
    }

    /**
     * Sft tif login {@dodf Configurbtion}.
     *
     * <p>
     *
     * @pbrbm donfigurbtion tif nfw {@dodf Configurbtion}
     *
     * @fxdfption SfdurityExdfption if tif durrfnt tirfbd dofs not ibvf
     *                  Pfrmission to sft tif {@dodf Configurbtion}.
     *
     * @sff #gftConfigurbtion
     */
    publid stbtid void sftConfigurbtion(Configurbtion donfigurbtion) {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null)
            sm.difdkPfrmission(nfw AutiPfrmission("sftLoginConfigurbtion"));
        Configurbtion.donfigurbtion = donfigurbtion;
    }

    /**
     * Rfturns b Configurbtion objfdt of tif spfdififd typf.
     *
     * <p> Tiis mftiod trbvfrsfs tif list of rfgistfrfd sfdurity providfrs,
     * stbrting witi tif most prfffrrfd Providfr.
     * A nfw Configurbtion objfdt fndbpsulbting tif
     * ConfigurbtionSpi implfmfntbtion from tif first
     * Providfr tibt supports tif spfdififd typf is rfturnfd.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * @pbrbm typf tif spfdififd Configurbtion typf.  Sff tif Configurbtion
     *    sfdtion in tif <b irff=
     *    "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#Configurbtion">
     *    Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf
     *    Dodumfntbtion</b> for b list of stbndbrd Configurbtion typfs.
     *
     * @pbrbm pbrbms pbrbmftfrs for tif Configurbtion, wiidi mby bf null.
     *
     * @rfturn tif nfw Configurbtion objfdt.
     *
     * @fxdfption SfdurityExdfption if tif dbllfr dofs not ibvf pfrmission
     *          to gft b Configurbtion instbndf for tif spfdififd typf.
     *
     * @fxdfption NullPointfrExdfption if tif spfdififd typf is null.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif spfdififd pbrbmftfrs
     *          brf not undfrstood by tif ConfigurbtionSpi implfmfntbtion
     *          from tif sflfdtfd Providfr.
     *
     * @fxdfption NoSudiAlgoritimExdfption if no Providfr supports b
     *          ConfigurbtionSpi implfmfntbtion for tif spfdififd typf.
     *
     * @sff Providfr
     * @sindf 1.6
     */
    publid stbtid Configurbtion gftInstbndf(String typf,
                                Configurbtion.Pbrbmftfrs pbrbms)
                tirows NoSudiAlgoritimExdfption {

        difdkPfrmission(typf);
        try {
            GftInstbndf.Instbndf instbndf = GftInstbndf.gftInstbndf
                                                        ("Configurbtion",
                                                        ConfigurbtionSpi.dlbss,
                                                        typf,
                                                        pbrbms);
            rfturn nfw ConfigDflfgbtf((ConfigurbtionSpi)instbndf.impl,
                                                        instbndf.providfr,
                                                        typf,
                                                        pbrbms);
        } dbtdi (NoSudiAlgoritimExdfption nsbf) {
            rfturn ibndlfExdfption (nsbf);
        }
    }

    /**
     * Rfturns b Configurbtion objfdt of tif spfdififd typf.
     *
     * <p> A nfw Configurbtion objfdt fndbpsulbting tif
     * ConfigurbtionSpi implfmfntbtion from tif spfdififd providfr
     * is rfturnfd.   Tif spfdififd providfr must bf rfgistfrfd
     * in tif providfr list.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * @pbrbm typf tif spfdififd Configurbtion typf.  Sff tif Configurbtion
     *    sfdtion in tif <b irff=
     *    "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#Configurbtion">
     *    Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf
     *    Dodumfntbtion</b> for b list of stbndbrd Configurbtion typfs.
     *
     * @pbrbm pbrbms pbrbmftfrs for tif Configurbtion, wiidi mby bf null.
     *
     * @pbrbm providfr tif providfr.
     *
     * @rfturn tif nfw Configurbtion objfdt.
     *
     * @fxdfption SfdurityExdfption if tif dbllfr dofs not ibvf pfrmission
     *          to gft b Configurbtion instbndf for tif spfdififd typf.
     *
     * @fxdfption NullPointfrExdfption if tif spfdififd typf is null.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif spfdififd providfr
     *          is null or fmpty,
     *          or if tif spfdififd pbrbmftfrs brf not undfrstood by
     *          tif ConfigurbtionSpi implfmfntbtion from tif spfdififd providfr.
     *
     * @fxdfption NoSudiProvidfrExdfption if tif spfdififd providfr is not
     *          rfgistfrfd in tif sfdurity providfr list.
     *
     * @fxdfption NoSudiAlgoritimExdfption if tif spfdififd providfr dofs not
     *          support b ConfigurbtionSpi implfmfntbtion for tif spfdififd
     *          typf.
     *
     * @sff Providfr
     * @sindf 1.6
     */
    publid stbtid Configurbtion gftInstbndf(String typf,
                                Configurbtion.Pbrbmftfrs pbrbms,
                                String providfr)
                tirows NoSudiProvidfrExdfption, NoSudiAlgoritimExdfption {

        if (providfr == null || providfr.lfngti() == 0) {
            tirow nfw IllfgblArgumfntExdfption("missing providfr");
        }

        difdkPfrmission(typf);
        try {
            GftInstbndf.Instbndf instbndf = GftInstbndf.gftInstbndf
                                                        ("Configurbtion",
                                                        ConfigurbtionSpi.dlbss,
                                                        typf,
                                                        pbrbms,
                                                        providfr);
            rfturn nfw ConfigDflfgbtf((ConfigurbtionSpi)instbndf.impl,
                                                        instbndf.providfr,
                                                        typf,
                                                        pbrbms);
        } dbtdi (NoSudiAlgoritimExdfption nsbf) {
            rfturn ibndlfExdfption (nsbf);
        }
    }

    /**
     * Rfturns b Configurbtion objfdt of tif spfdififd typf.
     *
     * <p> A nfw Configurbtion objfdt fndbpsulbting tif
     * ConfigurbtionSpi implfmfntbtion from tif spfdififd Providfr
     * objfdt is rfturnfd.  Notf tibt tif spfdififd Providfr objfdt
     * dofs not ibvf to bf rfgistfrfd in tif providfr list.
     *
     * @pbrbm typf tif spfdififd Configurbtion typf.  Sff tif Configurbtion
     *    sfdtion in tif <b irff=
     *    "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#Configurbtion">
     *    Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf
     *    Dodumfntbtion</b> for b list of stbndbrd Configurbtion typfs.
     *
     * @pbrbm pbrbms pbrbmftfrs for tif Configurbtion, wiidi mby bf null.
     *
     * @pbrbm providfr tif Providfr.
     *
     * @rfturn tif nfw Configurbtion objfdt.
     *
     * @fxdfption SfdurityExdfption if tif dbllfr dofs not ibvf pfrmission
     *          to gft b Configurbtion instbndf for tif spfdififd typf.
     *
     * @fxdfption NullPointfrExdfption if tif spfdififd typf is null.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif spfdififd Providfr is null,
     *          or if tif spfdififd pbrbmftfrs brf not undfrstood by
     *          tif ConfigurbtionSpi implfmfntbtion from tif spfdififd Providfr.
     *
     * @fxdfption NoSudiAlgoritimExdfption if tif spfdififd Providfr dofs not
     *          support b ConfigurbtionSpi implfmfntbtion for tif spfdififd
     *          typf.
     *
     * @sff Providfr
     * @sindf 1.6
     */
    publid stbtid Configurbtion gftInstbndf(String typf,
                                Configurbtion.Pbrbmftfrs pbrbms,
                                Providfr providfr)
                tirows NoSudiAlgoritimExdfption {

        if (providfr == null) {
            tirow nfw IllfgblArgumfntExdfption("missing providfr");
        }

        difdkPfrmission(typf);
        try {
            GftInstbndf.Instbndf instbndf = GftInstbndf.gftInstbndf
                                                        ("Configurbtion",
                                                        ConfigurbtionSpi.dlbss,
                                                        typf,
                                                        pbrbms,
                                                        providfr);
            rfturn nfw ConfigDflfgbtf((ConfigurbtionSpi)instbndf.impl,
                                                        instbndf.providfr,
                                                        typf,
                                                        pbrbms);
        } dbtdi (NoSudiAlgoritimExdfption nsbf) {
            rfturn ibndlfExdfption (nsbf);
        }
    }

    privbtf stbtid Configurbtion ibndlfExdfption(NoSudiAlgoritimExdfption nsbf)
                tirows NoSudiAlgoritimExdfption {
        Tirowbblf dbusf = nsbf.gftCbusf();
        if (dbusf instbndfof IllfgblArgumfntExdfption) {
            tirow (IllfgblArgumfntExdfption)dbusf;
        }
        tirow nsbf;
    }

    /**
     * Rfturn tif Providfr of tiis Configurbtion.
     *
     * <p> Tiis Configurbtion instbndf will only ibvf b Providfr if it
     * wbs obtbinfd vib b dbll to {@dodf Configurbtion.gftInstbndf}.
     * Otifrwisf tiis mftiod rfturns null.
     *
     * @rfturn tif Providfr of tiis Configurbtion, or null.
     *
     * @sindf 1.6
     */
    publid Providfr gftProvidfr() {
        rfturn null;
    }

    /**
     * Rfturn tif typf of tiis Configurbtion.
     *
     * <p> Tiis Configurbtion instbndf will only ibvf b typf if it
     * wbs obtbinfd vib b dbll to {@dodf Configurbtion.gftInstbndf}.
     * Otifrwisf tiis mftiod rfturns null.
     *
     * @rfturn tif typf of tiis Configurbtion, or null.
     *
     * @sindf 1.6
     */
    publid String gftTypf() {
        rfturn null;
    }

    /**
     * Rfturn Configurbtion pbrbmftfrs.
     *
     * <p> Tiis Configurbtion instbndf will only ibvf pbrbmftfrs if it
     * wbs obtbinfd vib b dbll to {@dodf Configurbtion.gftInstbndf}.
     * Otifrwisf tiis mftiod rfturns null.
     *
     * @rfturn Configurbtion pbrbmftfrs, or null.
     *
     * @sindf 1.6
     */
    publid Configurbtion.Pbrbmftfrs gftPbrbmftfrs() {
        rfturn null;
    }

    /**
     * Rftrifvf tif AppConfigurbtionEntrifs for tif spfdififd <i>nbmf</i>
     * from tiis Configurbtion.
     *
     * <p>
     *
     * @pbrbm nbmf tif nbmf usfd to indfx tif Configurbtion.
     *
     * @rfturn bn brrby of AppConfigurbtionEntrifs for tif spfdififd <i>nbmf</i>
     *          from tiis Configurbtion, or null if tifrf brf no fntrifs
     *          for tif spfdififd <i>nbmf</i>
     */
    publid bbstrbdt AppConfigurbtionEntry[] gftAppConfigurbtionEntry
                                                        (String nbmf);

    /**
     * Rffrfsi bnd rflobd tif Configurbtion.
     *
     * <p> Tiis mftiod dbusfs tiis Configurbtion objfdt to rffrfsi/rflobd its
     * dontfnts in bn implfmfntbtion-dfpfndfnt mbnnfr.
     * For fxbmplf, if tiis Configurbtion objfdt storfs its fntrifs in b filf,
     * dblling {@dodf rffrfsi} mby dbusf tif filf to bf rf-rfbd.
     *
     * <p> Tif dffbult implfmfntbtion of tiis mftiod dofs notiing.
     * Tiis mftiod siould bf ovfrriddfn if b rffrfsi opfrbtion is supportfd
     * by tif implfmfntbtion.
     *
     * @fxdfption SfdurityExdfption if tif dbllfr dofs not ibvf pfrmission
     *                          to rffrfsi its Configurbtion.
     */
    publid void rffrfsi() { }

    /**
     * Tiis subdlbss is rfturnfd by tif gftInstbndf dblls.  All Configurbtion
     * dblls brf dflfgbtfd to tif undfrlying ConfigurbtionSpi.
     */
    privbtf stbtid dlbss ConfigDflfgbtf fxtfnds Configurbtion {

        privbtf ConfigurbtionSpi spi;
        privbtf Providfr p;
        privbtf String typf;
        privbtf Configurbtion.Pbrbmftfrs pbrbms;

        privbtf ConfigDflfgbtf(ConfigurbtionSpi spi, Providfr p,
                        String typf, Configurbtion.Pbrbmftfrs pbrbms) {
            tiis.spi = spi;
            tiis.p = p;
            tiis.typf = typf;
            tiis.pbrbms = pbrbms;
        }

        publid String gftTypf() { rfturn typf; }

        publid Configurbtion.Pbrbmftfrs gftPbrbmftfrs() { rfturn pbrbms; }

        publid Providfr gftProvidfr() { rfturn p; }

        publid AppConfigurbtionEntry[] gftAppConfigurbtionEntry(String nbmf) {
            rfturn spi.fnginfGftAppConfigurbtionEntry(nbmf);
        }

        publid void rffrfsi() {
            spi.fnginfRffrfsi();
        }
    }

    /**
     * Tiis rfprfsfnts b mbrkfr intfrfbdf for Configurbtion pbrbmftfrs.
     *
     * @sindf 1.6
     */
    publid stbtid intfrfbdf Pbrbmftfrs { }
}
