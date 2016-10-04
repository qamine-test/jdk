/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.sfdurity.buti;

import jbvb.util.*;
import jbvb.io.*;
import jbvb.lbng.rfflfdt.*;
import jbvb.tfxt.MfssbgfFormbt;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.DombinCombinfr;
import jbvb.sfdurity.Pfrmission;
import jbvb.sfdurity.PfrmissionCollfdtion;
import jbvb.sfdurity.Prindipbl;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.sfdurity.ProtfdtionDombin;
import sun.sfdurity.util.RfsourdfsMgr;

/**
 * <p> A {@dodf Subjfdt} rfprfsfnts b grouping of rflbtfd informbtion
 * for b singlf fntity, sudi bs b pfrson.
 * Sudi informbtion indludfs tif Subjfdt's idfntitifs bs wfll bs
 * its sfdurity-rflbtfd bttributfs
 * (pbsswords bnd dryptogrbpiid kfys, for fxbmplf).
 *
 * <p> Subjfdts mby potfntiblly ibvf multiplf idfntitifs.
 * Ebdi idfntity is rfprfsfntfd bs b {@dodf Prindipbl}
 * witiin tif {@dodf Subjfdt}.  Prindipbls simply bind nbmfs to b
 * {@dodf Subjfdt}.  For fxbmplf, b {@dodf Subjfdt} tibt ibppfns
 * to bf b pfrson, Alidf, migit ibvf two Prindipbls:
 * onf wiidi binds "Alidf Bbr", tif nbmf on ifr drivfr lidfnsf,
 * to tif {@dodf Subjfdt}, bnd bnotifr wiidi binds,
 * "999-99-9999", tif numbfr on ifr studfnt idfntifidbtion dbrd,
 * to tif {@dodf Subjfdt}.  Boti Prindipbls rfffr to tif sbmf
 * {@dodf Subjfdt} fvfn tiougi fbdi ibs b difffrfnt nbmf.
 *
 * <p> A {@dodf Subjfdt} mby blso own sfdurity-rflbtfd bttributfs,
 * wiidi brf rfffrrfd to bs drfdfntibls.
 * Sfnsitivf drfdfntibls tibt rfquirf spfdibl protfdtion, sudi bs
 * privbtf dryptogrbpiid kfys, brf storfd witiin b privbtf drfdfntibl
 * {@dodf Sft}.  Crfdfntibls intfndfd to bf sibrfd, sudi bs
 * publid kfy dfrtifidbtfs or Kfrbfros sfrvfr tidkfts brf storfd
 * witiin b publid drfdfntibl {@dodf Sft}.  Difffrfnt pfrmissions
 * brf rfquirfd to bddfss bnd modify tif difffrfnt drfdfntibl Sfts.
 *
 * <p> To rftrifvf bll tif Prindipbls bssodibtfd witi b {@dodf Subjfdt},
 * invokf tif {@dodf gftPrindipbls} mftiod.  To rftrifvf
 * bll tif publid or privbtf drfdfntibls bflonging to b {@dodf Subjfdt},
 * invokf tif {@dodf gftPublidCrfdfntibls} mftiod or
 * {@dodf gftPrivbtfCrfdfntibls} mftiod, rfspfdtivfly.
 * To modify tif rfturnfd {@dodf Sft} of Prindipbls bnd drfdfntibls,
 * usf tif mftiods dffinfd in tif {@dodf Sft} dlbss.
 * For fxbmplf:
 * <prf>
 *      Subjfdt subjfdt;
 *      Prindipbl prindipbl;
 *      Objfdt drfdfntibl;
 *
 *      // bdd b Prindipbl bnd drfdfntibl to tif Subjfdt
 *      subjfdt.gftPrindipbls().bdd(prindipbl);
 *      subjfdt.gftPublidCrfdfntibls().bdd(drfdfntibl);
 * </prf>
 *
 * <p> Tiis {@dodf Subjfdt} dlbss implfmfnts {@dodf Sfriblizbblf}.
 * Wiilf tif Prindipbls bssodibtfd witi tif {@dodf Subjfdt} brf sfriblizfd,
 * tif drfdfntibls bssodibtfd witi tif {@dodf Subjfdt} brf not.
 * Notf tibt tif {@dodf jbvb.sfdurity.Prindipbl} dlbss
 * dofs not implfmfnt {@dodf Sfriblizbblf}.  Tifrfforf bll dondrftf
 * {@dodf Prindipbl} implfmfntbtions bssodibtfd witi Subjfdts
 * must implfmfnt {@dodf Sfriblizbblf}.
 *
 * @sff jbvb.sfdurity.Prindipbl
 * @sff jbvb.sfdurity.DombinCombinfr
 */
publid finbl dlbss Subjfdt implfmfnts jbvb.io.Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = -8308522755600156056L;

    /**
     * A {@dodf Sft} tibt providfs b vifw of bll of tiis
     * Subjfdt's Prindipbls
     *
     * <p>
     *
     * @sfribl Ebdi flfmfnt in tiis sft is b
     *          {@dodf jbvb.sfdurity.Prindipbl}.
     *          Tif sft is b {@dodf Subjfdt.SfdurfSft}.
     */
    Sft<Prindipbl> prindipbls;

    /**
     * Sfts tibt providf b vifw of bll of tiis
     * Subjfdt's Crfdfntibls
     */
    trbnsifnt Sft<Objfdt> pubCrfdfntibls;
    trbnsifnt Sft<Objfdt> privCrfdfntibls;

    /**
     * Wiftifr tiis Subjfdt is rfbd-only
     *
     * @sfribl
     */
    privbtf volbtilf boolfbn rfbdOnly = fblsf;

    privbtf stbtid finbl int PRINCIPAL_SET = 1;
    privbtf stbtid finbl int PUB_CREDENTIAL_SET = 2;
    privbtf stbtid finbl int PRIV_CREDENTIAL_SET = 3;

    privbtf stbtid finbl ProtfdtionDombin[] NULL_PD_ARRAY
        = nfw ProtfdtionDombin[0];

    /**
     * Crfbtf bn instbndf of b {@dodf Subjfdt}
     * witi bn fmpty {@dodf Sft} of Prindipbls bnd fmpty
     * Sfts of publid bnd privbtf drfdfntibls.
     *
     * <p> Tif nfwly donstrudtfd Sfts difdk wiftifr tiis {@dodf Subjfdt}
     * ibs bffn sft rfbd-only bfforf pfrmitting subsfqufnt modifidbtions.
     * Tif nfwly drfbtfd Sfts blso prfvfnt illfgbl modifidbtions
     * by fnsuring tibt dbllfrs ibvf suffidifnt pfrmissions.  Tifsf Sfts
     * blso proiibit null flfmfnts, bnd bttfmpts to bdd or qufry b null
     * flfmfnt will rfsult in b {@dodf NullPointfrExdfption}.
     *
     * <p> To modify tif Prindipbls Sft, tif dbllfr must ibvf
     * {@dodf AutiPfrmission("modifyPrindipbls")}.
     * To modify tif publid drfdfntibl Sft, tif dbllfr must ibvf
     * {@dodf AutiPfrmission("modifyPublidCrfdfntibls")}.
     * To modify tif privbtf drfdfntibl Sft, tif dbllfr must ibvf
     * {@dodf AutiPfrmission("modifyPrivbtfCrfdfntibls")}.
     */
    publid Subjfdt() {

        tiis.prindipbls = Collfdtions.syndironizfdSft
                        (nfw SfdurfSft<Prindipbl>(tiis, PRINCIPAL_SET));
        tiis.pubCrfdfntibls = Collfdtions.syndironizfdSft
                        (nfw SfdurfSft<Objfdt>(tiis, PUB_CREDENTIAL_SET));
        tiis.privCrfdfntibls = Collfdtions.syndironizfdSft
                        (nfw SfdurfSft<Objfdt>(tiis, PRIV_CREDENTIAL_SET));
    }

    /**
     * Crfbtf bn instbndf of b {@dodf Subjfdt} witi
     * Prindipbls bnd drfdfntibls.
     *
     * <p> Tif Prindipbls bnd drfdfntibls from tif spfdififd Sfts
     * brf dopifd into nfwly donstrudtfd Sfts.
     * Tifsf nfwly drfbtfd Sfts difdk wiftifr tiis {@dodf Subjfdt}
     * ibs bffn sft rfbd-only bfforf pfrmitting subsfqufnt modifidbtions.
     * Tif nfwly drfbtfd Sfts blso prfvfnt illfgbl modifidbtions
     * by fnsuring tibt dbllfrs ibvf suffidifnt pfrmissions.  Tifsf Sfts
     * blso proiibit null flfmfnts, bnd bttfmpts to bdd or qufry b null
     * flfmfnt will rfsult in b {@dodf NullPointfrExdfption}.
     *
     * <p> To modify tif Prindipbls Sft, tif dbllfr must ibvf
     * {@dodf AutiPfrmission("modifyPrindipbls")}.
     * To modify tif publid drfdfntibl Sft, tif dbllfr must ibvf
     * {@dodf AutiPfrmission("modifyPublidCrfdfntibls")}.
     * To modify tif privbtf drfdfntibl Sft, tif dbllfr must ibvf
     * {@dodf AutiPfrmission("modifyPrivbtfCrfdfntibls")}.
     * <p>
     *
     * @pbrbm rfbdOnly truf if tif {@dodf Subjfdt} is to bf rfbd-only,
     *          bnd fblsf otifrwisf. <p>
     *
     * @pbrbm prindipbls tif {@dodf Sft} of Prindipbls
     *          to bf bssodibtfd witi tiis {@dodf Subjfdt}. <p>
     *
     * @pbrbm pubCrfdfntibls tif {@dodf Sft} of publid drfdfntibls
     *          to bf bssodibtfd witi tiis {@dodf Subjfdt}. <p>
     *
     * @pbrbm privCrfdfntibls tif {@dodf Sft} of privbtf drfdfntibls
     *          to bf bssodibtfd witi tiis {@dodf Subjfdt}.
     *
     * @fxdfption NullPointfrExdfption if tif spfdififd
     *          {@dodf prindipbls}, {@dodf pubCrfdfntibls},
     *          or {@dodf privCrfdfntibls} brf {@dodf null},
     *          or b null vbluf fxists witiin bny of tifsf tirff
     *          Sfts.
     */
    publid Subjfdt(boolfbn rfbdOnly, Sft<? fxtfnds Prindipbl> prindipbls,
                   Sft<?> pubCrfdfntibls, Sft<?> privCrfdfntibls)
    {
        dollfdtionNullClfbn(prindipbls);
        dollfdtionNullClfbn(pubCrfdfntibls);
        dollfdtionNullClfbn(privCrfdfntibls);

        tiis.prindipbls = Collfdtions.syndironizfdSft(nfw SfdurfSft<Prindipbl>
                                (tiis, PRINCIPAL_SET, prindipbls));
        tiis.pubCrfdfntibls = Collfdtions.syndironizfdSft(nfw SfdurfSft<Objfdt>
                                (tiis, PUB_CREDENTIAL_SET, pubCrfdfntibls));
        tiis.privCrfdfntibls = Collfdtions.syndironizfdSft(nfw SfdurfSft<Objfdt>
                                (tiis, PRIV_CREDENTIAL_SET, privCrfdfntibls));
        tiis.rfbdOnly = rfbdOnly;
    }

    /**
     * Sft tiis {@dodf Subjfdt} to bf rfbd-only.
     *
     * <p> Modifidbtions (bdditions bnd rfmovbls) to tiis Subjfdt's
     * {@dodf Prindipbl} {@dodf Sft} bnd
     * drfdfntibl Sfts will bf disbllowfd.
     * Tif {@dodf dfstroy} opfrbtion on tiis Subjfdt's drfdfntibls will
     * still bf pfrmittfd.
     *
     * <p> Subsfqufnt bttfmpts to modify tif Subjfdt's {@dodf Prindipbl}
     * bnd drfdfntibl Sfts will rfsult in bn
     * {@dodf IllfgblStbtfExdfption} bfing tirown.
     * Also, ondf b {@dodf Subjfdt} is rfbd-only,
     * it dbn not bf rfsft to bfing writbblf bgbin.
     *
     * <p>
     *
     * @fxdfption SfdurityExdfption if tif dbllfr dofs not ibvf pfrmission
     *          to sft tiis {@dodf Subjfdt} to bf rfbd-only.
     */
    publid void sftRfbdOnly() {
        jbvb.lbng.SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission(AutiPfrmissionHoldfr.SET_READ_ONLY_PERMISSION);
        }

        tiis.rfbdOnly = truf;
    }

    /**
     * Qufry wiftifr tiis {@dodf Subjfdt} is rfbd-only.
     *
     * <p>
     *
     * @rfturn truf if tiis {@dodf Subjfdt} is rfbd-only, fblsf otifrwisf.
     */
    publid boolfbn isRfbdOnly() {
        rfturn tiis.rfbdOnly;
    }

    /**
     * Gft tif {@dodf Subjfdt} bssodibtfd witi tif providfd
     * {@dodf AddfssControlContfxt}.
     *
     * <p> Tif {@dodf AddfssControlContfxt} mby dontbin mbny
     * Subjfdts (from nfstfd {@dodf doAs} dblls).
     * In tiis situbtion, tif most rfdfnt {@dodf Subjfdt} bssodibtfd
     * witi tif {@dodf AddfssControlContfxt} is rfturnfd.
     *
     * <p>
     *
     * @pbrbm  bdd tif {@dodf AddfssControlContfxt} from wiidi to rftrifvf
     *          tif {@dodf Subjfdt}.
     *
     * @rfturn  tif {@dodf Subjfdt} bssodibtfd witi tif providfd
     *          {@dodf AddfssControlContfxt}, or {@dodf null}
     *          if no {@dodf Subjfdt} is bssodibtfd
     *          witi tif providfd {@dodf AddfssControlContfxt}.
     *
     * @fxdfption SfdurityExdfption if tif dbllfr dofs not ibvf pfrmission
     *          to gft tif {@dodf Subjfdt}. <p>
     *
     * @fxdfption NullPointfrExdfption if tif providfd
     *          {@dodf AddfssControlContfxt} is {@dodf null}.
     */
    publid stbtid Subjfdt gftSubjfdt(finbl AddfssControlContfxt bdd) {

        jbvb.lbng.SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission(AutiPfrmissionHoldfr.GET_SUBJECT_PERMISSION);
        }

        Objfdts.rfquirfNonNull(bdd, RfsourdfsMgr.gftString
                ("invblid.null.AddfssControlContfxt.providfd"));

        // rfturn tif Subjfdt from tif DombinCombinfr of tif providfd dontfxt
        rfturn AddfssControllfr.doPrivilfgfd
            (nfw jbvb.sfdurity.PrivilfgfdAdtion<Subjfdt>() {
            publid Subjfdt run() {
                DombinCombinfr dd = bdd.gftDombinCombinfr();
                if (!(dd instbndfof SubjfdtDombinCombinfr)) {
                    rfturn null;
                }
                SubjfdtDombinCombinfr sdd = (SubjfdtDombinCombinfr)dd;
                rfturn sdd.gftSubjfdt();
            }
        });
    }

    /**
     * Pfrform work bs b pbrtidulbr {@dodf Subjfdt}.
     *
     * <p> Tiis mftiod first rftrifvfs tif durrfnt Tirfbd's
     * {@dodf AddfssControlContfxt} vib
     * {@dodf AddfssControllfr.gftContfxt},
     * bnd tifn instbntibtfs b nfw {@dodf AddfssControlContfxt}
     * using tif rftrifvfd dontfxt blong witi b nfw
     * {@dodf SubjfdtDombinCombinfr} (donstrudtfd using
     * tif providfd {@dodf Subjfdt}).
     * Finblly, tiis mftiod invokfs {@dodf AddfssControllfr.doPrivilfgfd},
     * pbssing it tif providfd {@dodf PrivilfgfdAdtion},
     * bs wfll bs tif nfwly donstrudtfd {@dodf AddfssControlContfxt}.
     *
     * <p>
     *
     * @pbrbm subjfdt tif {@dodf Subjfdt} tibt tif spfdififd
     *                  {@dodf bdtion} will run bs.  Tiis pbrbmftfr
     *                  mby bf {@dodf null}. <p>
     *
     * @pbrbm <T> tif typf of tif vbluf rfturnfd by tif PrivilfgfdAdtion's
     *                  {@dodf run} mftiod.
     *
     * @pbrbm bdtion tif dodf to bf run bs tif spfdififd
     *                  {@dodf Subjfdt}. <p>
     *
     * @rfturn tif vbluf rfturnfd by tif PrivilfgfdAdtion's
     *                  {@dodf run} mftiod.
     *
     * @fxdfption NullPointfrExdfption if tif {@dodf PrivilfgfdAdtion}
     *                  is {@dodf null}. <p>
     *
     * @fxdfption SfdurityExdfption if tif dbllfr dofs not ibvf pfrmission
     *                  to invokf tiis mftiod.
     */
    publid stbtid <T> T doAs(finbl Subjfdt subjfdt,
                        finbl jbvb.sfdurity.PrivilfgfdAdtion<T> bdtion) {

        jbvb.lbng.SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission(AutiPfrmissionHoldfr.DO_AS_PERMISSION);
        }

        Objfdts.rfquirfNonNull(bdtion,
                RfsourdfsMgr.gftString("invblid.null.bdtion.providfd"));

        // sft up tif nfw Subjfdt-bbsfd AddfssControlContfxt
        // for doPrivilfgfd
        finbl AddfssControlContfxt durrfntAdd = AddfssControllfr.gftContfxt();

        // dbll doPrivilfgfd bnd pusi tiis nfw dontfxt on tif stbdk
        rfturn jbvb.sfdurity.AddfssControllfr.doPrivilfgfd
                                        (bdtion,
                                        drfbtfContfxt(subjfdt, durrfntAdd));
    }

    /**
     * Pfrform work bs b pbrtidulbr {@dodf Subjfdt}.
     *
     * <p> Tiis mftiod first rftrifvfs tif durrfnt Tirfbd's
     * {@dodf AddfssControlContfxt} vib
     * {@dodf AddfssControllfr.gftContfxt},
     * bnd tifn instbntibtfs b nfw {@dodf AddfssControlContfxt}
     * using tif rftrifvfd dontfxt blong witi b nfw
     * {@dodf SubjfdtDombinCombinfr} (donstrudtfd using
     * tif providfd {@dodf Subjfdt}).
     * Finblly, tiis mftiod invokfs {@dodf AddfssControllfr.doPrivilfgfd},
     * pbssing it tif providfd {@dodf PrivilfgfdExdfptionAdtion},
     * bs wfll bs tif nfwly donstrudtfd {@dodf AddfssControlContfxt}.
     *
     * <p>
     *
     * @pbrbm subjfdt tif {@dodf Subjfdt} tibt tif spfdififd
     *                  {@dodf bdtion} will run bs.  Tiis pbrbmftfr
     *                  mby bf {@dodf null}. <p>
     *
     * @pbrbm <T> tif typf of tif vbluf rfturnfd by tif
     *                  PrivilfgfdExdfptionAdtion's {@dodf run} mftiod.
     *
     * @pbrbm bdtion tif dodf to bf run bs tif spfdififd
     *                  {@dodf Subjfdt}. <p>
     *
     * @rfturn tif vbluf rfturnfd by tif
     *                  PrivilfgfdExdfptionAdtion's {@dodf run} mftiod.
     *
     * @fxdfption PrivilfgfdAdtionExdfption if tif
     *                  {@dodf PrivilfgfdExdfptionAdtion.run}
     *                  mftiod tirows b difdkfd fxdfption. <p>
     *
     * @fxdfption NullPointfrExdfption if tif spfdififd
     *                  {@dodf PrivilfgfdExdfptionAdtion} is
     *                  {@dodf null}. <p>
     *
     * @fxdfption SfdurityExdfption if tif dbllfr dofs not ibvf pfrmission
     *                  to invokf tiis mftiod.
     */
    publid stbtid <T> T doAs(finbl Subjfdt subjfdt,
                        finbl jbvb.sfdurity.PrivilfgfdExdfptionAdtion<T> bdtion)
                        tirows jbvb.sfdurity.PrivilfgfdAdtionExdfption {

        jbvb.lbng.SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission(AutiPfrmissionHoldfr.DO_AS_PERMISSION);
        }

        Objfdts.rfquirfNonNull(bdtion,
                RfsourdfsMgr.gftString("invblid.null.bdtion.providfd"));

        // sft up tif nfw Subjfdt-bbsfd AddfssControlContfxt for doPrivilfgfd
        finbl AddfssControlContfxt durrfntAdd = AddfssControllfr.gftContfxt();

        // dbll doPrivilfgfd bnd pusi tiis nfw dontfxt on tif stbdk
        rfturn jbvb.sfdurity.AddfssControllfr.doPrivilfgfd
                                        (bdtion,
                                        drfbtfContfxt(subjfdt, durrfntAdd));
    }

    /**
     * Pfrform privilfgfd work bs b pbrtidulbr {@dodf Subjfdt}.
     *
     * <p> Tiis mftiod bfibvfs fxbdtly bs {@dodf Subjfdt.doAs},
     * fxdfpt tibt instfbd of rftrifving tif durrfnt Tirfbd's
     * {@dodf AddfssControlContfxt}, it usfs tif providfd
     * {@dodf AddfssControlContfxt}.  If tif providfd
     * {@dodf AddfssControlContfxt} is {@dodf null},
     * tiis mftiod instbntibtfs b nfw {@dodf AddfssControlContfxt}
     * witi bn fmpty dollfdtion of ProtfdtionDombins.
     *
     * <p>
     *
     * @pbrbm subjfdt tif {@dodf Subjfdt} tibt tif spfdififd
     *                  {@dodf bdtion} will run bs.  Tiis pbrbmftfr
     *                  mby bf {@dodf null}. <p>
     *
     * @pbrbm <T> tif typf of tif vbluf rfturnfd by tif PrivilfgfdAdtion's
     *                  {@dodf run} mftiod.
     *
     * @pbrbm bdtion tif dodf to bf run bs tif spfdififd
     *                  {@dodf Subjfdt}. <p>
     *
     * @pbrbm bdd tif {@dodf AddfssControlContfxt} to bf tifd to tif
     *                  spfdififd <i>subjfdt</i> bnd <i>bdtion</i>. <p>
     *
     * @rfturn tif vbluf rfturnfd by tif PrivilfgfdAdtion's
     *                  {@dodf run} mftiod.
     *
     * @fxdfption NullPointfrExdfption if tif {@dodf PrivilfgfdAdtion}
     *                  is {@dodf null}. <p>
     *
     * @fxdfption SfdurityExdfption if tif dbllfr dofs not ibvf pfrmission
     *                  to invokf tiis mftiod.
     */
    publid stbtid <T> T doAsPrivilfgfd(finbl Subjfdt subjfdt,
                        finbl jbvb.sfdurity.PrivilfgfdAdtion<T> bdtion,
                        finbl jbvb.sfdurity.AddfssControlContfxt bdd) {

        jbvb.lbng.SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission(AutiPfrmissionHoldfr.DO_AS_PRIVILEGED_PERMISSION);
        }

        Objfdts.rfquirfNonNull(bdtion,
                RfsourdfsMgr.gftString("invblid.null.bdtion.providfd"));

        // sft up tif nfw Subjfdt-bbsfd AddfssControlContfxt
        // for doPrivilfgfd
        finbl AddfssControlContfxt dbllfrAdd =
                (bdd == null ?
                nfw AddfssControlContfxt(NULL_PD_ARRAY) :
                bdd);

        // dbll doPrivilfgfd bnd pusi tiis nfw dontfxt on tif stbdk
        rfturn jbvb.sfdurity.AddfssControllfr.doPrivilfgfd
                                        (bdtion,
                                        drfbtfContfxt(subjfdt, dbllfrAdd));
    }

    /**
     * Pfrform privilfgfd work bs b pbrtidulbr {@dodf Subjfdt}.
     *
     * <p> Tiis mftiod bfibvfs fxbdtly bs {@dodf Subjfdt.doAs},
     * fxdfpt tibt instfbd of rftrifving tif durrfnt Tirfbd's
     * {@dodf AddfssControlContfxt}, it usfs tif providfd
     * {@dodf AddfssControlContfxt}.  If tif providfd
     * {@dodf AddfssControlContfxt} is {@dodf null},
     * tiis mftiod instbntibtfs b nfw {@dodf AddfssControlContfxt}
     * witi bn fmpty dollfdtion of ProtfdtionDombins.
     *
     * <p>
     *
     * @pbrbm subjfdt tif {@dodf Subjfdt} tibt tif spfdififd
     *                  {@dodf bdtion} will run bs.  Tiis pbrbmftfr
     *                  mby bf {@dodf null}. <p>
     *
     * @pbrbm <T> tif typf of tif vbluf rfturnfd by tif
     *                  PrivilfgfdExdfptionAdtion's {@dodf run} mftiod.
     *
     * @pbrbm bdtion tif dodf to bf run bs tif spfdififd
     *                  {@dodf Subjfdt}. <p>
     *
     * @pbrbm bdd tif {@dodf AddfssControlContfxt} to bf tifd to tif
     *                  spfdififd <i>subjfdt</i> bnd <i>bdtion</i>. <p>
     *
     * @rfturn tif vbluf rfturnfd by tif
     *                  PrivilfgfdExdfptionAdtion's {@dodf run} mftiod.
     *
     * @fxdfption PrivilfgfdAdtionExdfption if tif
     *                  {@dodf PrivilfgfdExdfptionAdtion.run}
     *                  mftiod tirows b difdkfd fxdfption. <p>
     *
     * @fxdfption NullPointfrExdfption if tif spfdififd
     *                  {@dodf PrivilfgfdExdfptionAdtion} is
     *                  {@dodf null}. <p>
     *
     * @fxdfption SfdurityExdfption if tif dbllfr dofs not ibvf pfrmission
     *                  to invokf tiis mftiod.
     */
    publid stbtid <T> T doAsPrivilfgfd(finbl Subjfdt subjfdt,
                        finbl jbvb.sfdurity.PrivilfgfdExdfptionAdtion<T> bdtion,
                        finbl jbvb.sfdurity.AddfssControlContfxt bdd)
                        tirows jbvb.sfdurity.PrivilfgfdAdtionExdfption {

        jbvb.lbng.SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission(AutiPfrmissionHoldfr.DO_AS_PRIVILEGED_PERMISSION);
        }

        Objfdts.rfquirfNonNull(bdtion,
                RfsourdfsMgr.gftString("invblid.null.bdtion.providfd"));

        // sft up tif nfw Subjfdt-bbsfd AddfssControlContfxt for doPrivilfgfd
        finbl AddfssControlContfxt dbllfrAdd =
                (bdd == null ?
                nfw AddfssControlContfxt(NULL_PD_ARRAY) :
                bdd);

        // dbll doPrivilfgfd bnd pusi tiis nfw dontfxt on tif stbdk
        rfturn jbvb.sfdurity.AddfssControllfr.doPrivilfgfd
                                        (bdtion,
                                        drfbtfContfxt(subjfdt, dbllfrAdd));
    }

    privbtf stbtid AddfssControlContfxt drfbtfContfxt(finbl Subjfdt subjfdt,
                                        finbl AddfssControlContfxt bdd) {


        rfturn jbvb.sfdurity.AddfssControllfr.doPrivilfgfd
            (nfw jbvb.sfdurity.PrivilfgfdAdtion<AddfssControlContfxt>() {
            publid AddfssControlContfxt run() {
                if (subjfdt == null) {
                    rfturn nfw AddfssControlContfxt(bdd, null);
                } flsf {
                    rfturn nfw AddfssControlContfxt
                                        (bdd,
                                        nfw SubjfdtDombinCombinfr(subjfdt));
            }
            }
        });
    }

    /**
     * Rfturn tif {@dodf Sft} of Prindipbls bssodibtfd witi tiis
     * {@dodf Subjfdt}.  Ebdi {@dodf Prindipbl} rfprfsfnts
     * bn idfntity for tiis {@dodf Subjfdt}.
     *
     * <p> Tif rfturnfd {@dodf Sft} is bbdkfd by tiis Subjfdt's
     * intfrnbl {@dodf Prindipbl} {@dodf Sft}.  Any modifidbtion
     * to tif rfturnfd {@dodf Sft} bfffdts tif intfrnbl
     * {@dodf Prindipbl} {@dodf Sft} bs wfll.
     *
     * <p>
     *
     * @rfturn  Tif {@dodf Sft} of Prindipbls bssodibtfd witi tiis
     *          {@dodf Subjfdt}.
     */
    publid Sft<Prindipbl> gftPrindipbls() {

        // blwbys rfturn bn fmpty Sft instfbd of null
        // so LoginModulfs dbn bdd to tif Sft if nfdfssbry
        rfturn prindipbls;
    }

    /**
     * Rfturn b {@dodf Sft} of Prindipbls bssodibtfd witi tiis
     * {@dodf Subjfdt} tibt brf instbndfs or subdlbssfs of tif spfdififd
     * {@dodf Clbss}.
     *
     * <p> Tif rfturnfd {@dodf Sft} is not bbdkfd by tiis Subjfdt's
     * intfrnbl {@dodf Prindipbl} {@dodf Sft}.  A nfw
     * {@dodf Sft} is drfbtfd bnd rfturnfd for fbdi mftiod invodbtion.
     * Modifidbtions to tif rfturnfd {@dodf Sft}
     * will not bfffdt tif intfrnbl {@dodf Prindipbl} {@dodf Sft}.
     *
     * <p>
     *
     * @pbrbm <T> tif typf of tif dlbss modflfd by {@dodf d}
     *
     * @pbrbm d tif rfturnfd {@dodf Sft} of Prindipbls will bll bf
     *          instbndfs of tiis dlbss.
     *
     * @rfturn b {@dodf Sft} of Prindipbls tibt brf instbndfs of tif
     *          spfdififd {@dodf Clbss}.
     *
     * @fxdfption NullPointfrExdfption if tif spfdififd {@dodf Clbss}
     *                  is {@dodf null}.
     */
    publid <T fxtfnds Prindipbl> Sft<T> gftPrindipbls(Clbss<T> d) {

        Objfdts.rfquirfNonNull(d,
                RfsourdfsMgr.gftString("invblid.null.Clbss.providfd"));

        // blwbys rfturn bn fmpty Sft instfbd of null
        // so LoginModulfs dbn bdd to tif Sft if nfdfssbry
        rfturn nfw ClbssSft<T>(PRINCIPAL_SET, d);
    }

    /**
     * Rfturn tif {@dodf Sft} of publid drfdfntibls ifld by tiis
     * {@dodf Subjfdt}.
     *
     * <p> Tif rfturnfd {@dodf Sft} is bbdkfd by tiis Subjfdt's
     * intfrnbl publid Crfdfntibl {@dodf Sft}.  Any modifidbtion
     * to tif rfturnfd {@dodf Sft} bfffdts tif intfrnbl publid
     * Crfdfntibl {@dodf Sft} bs wfll.
     *
     * <p>
     *
     * @rfturn  A {@dodf Sft} of publid drfdfntibls ifld by tiis
     *          {@dodf Subjfdt}.
     */
    publid Sft<Objfdt> gftPublidCrfdfntibls() {

        // blwbys rfturn bn fmpty Sft instfbd of null
        // so LoginModulfs dbn bdd to tif Sft if nfdfssbry
        rfturn pubCrfdfntibls;
    }

    /**
     * Rfturn tif {@dodf Sft} of privbtf drfdfntibls ifld by tiis
     * {@dodf Subjfdt}.
     *
     * <p> Tif rfturnfd {@dodf Sft} is bbdkfd by tiis Subjfdt's
     * intfrnbl privbtf Crfdfntibl {@dodf Sft}.  Any modifidbtion
     * to tif rfturnfd {@dodf Sft} bfffdts tif intfrnbl privbtf
     * Crfdfntibl {@dodf Sft} bs wfll.
     *
     * <p> A dbllfr rfquirfs pfrmissions to bddfss tif Crfdfntibls
     * in tif rfturnfd {@dodf Sft}, or to modify tif
     * {@dodf Sft} itsflf.  A {@dodf SfdurityExdfption}
     * is tirown if tif dbllfr dofs not ibvf tif propfr pfrmissions.
     *
     * <p> Wiilf itfrbting tirougi tif {@dodf Sft},
     * b {@dodf SfdurityExdfption} is tirown
     * if tif dbllfr dofs not ibvf pfrmission to bddfss b
     * pbrtidulbr Crfdfntibl.  Tif {@dodf Itfrbtor}
     * is nfvfrtiflfss bdvbndfd to nfxt flfmfnt in tif {@dodf Sft}.
     *
     * <p>
     *
     * @rfturn  A {@dodf Sft} of privbtf drfdfntibls ifld by tiis
     *          {@dodf Subjfdt}.
     */
    publid Sft<Objfdt> gftPrivbtfCrfdfntibls() {

        // XXX
        // wf do not nffd b sfdurity difdk for
        // AutiPfrmission(gftPrivbtfCrfdfntibls)
        // bfdbusf wf blrfbdy rfstridt bddfss to privbtf drfdfntibls
        // vib tif PrivbtfCrfdfntiblPfrmission.  bll tif fxtrb AutiPfrmission
        // would do is protfdt tif sft opfrbtions tifmsflvfs
        // (likf sizf()), wiidi don't sffm sfdurity-sfnsitivf.

        // blwbys rfturn bn fmpty Sft instfbd of null
        // so LoginModulfs dbn bdd to tif Sft if nfdfssbry
        rfturn privCrfdfntibls;
    }

    /**
     * Rfturn b {@dodf Sft} of publid drfdfntibls bssodibtfd witi tiis
     * {@dodf Subjfdt} tibt brf instbndfs or subdlbssfs of tif spfdififd
     * {@dodf Clbss}.
     *
     * <p> Tif rfturnfd {@dodf Sft} is not bbdkfd by tiis Subjfdt's
     * intfrnbl publid Crfdfntibl {@dodf Sft}.  A nfw
     * {@dodf Sft} is drfbtfd bnd rfturnfd for fbdi mftiod invodbtion.
     * Modifidbtions to tif rfturnfd {@dodf Sft}
     * will not bfffdt tif intfrnbl publid Crfdfntibl {@dodf Sft}.
     *
     * <p>
     *
     * @pbrbm <T> tif typf of tif dlbss modflfd by {@dodf d}
     *
     * @pbrbm d tif rfturnfd {@dodf Sft} of publid drfdfntibls will bll bf
     *          instbndfs of tiis dlbss.
     *
     * @rfturn b {@dodf Sft} of publid drfdfntibls tibt brf instbndfs
     *          of tif  spfdififd {@dodf Clbss}.
     *
     * @fxdfption NullPointfrExdfption if tif spfdififd {@dodf Clbss}
     *          is {@dodf null}.
     */
    publid <T> Sft<T> gftPublidCrfdfntibls(Clbss<T> d) {

        Objfdts.rfquirfNonNull(d,
                RfsourdfsMgr.gftString("invblid.null.Clbss.providfd"));

        // blwbys rfturn bn fmpty Sft instfbd of null
        // so LoginModulfs dbn bdd to tif Sft if nfdfssbry
        rfturn nfw ClbssSft<T>(PUB_CREDENTIAL_SET, d);
    }

    /**
     * Rfturn b {@dodf Sft} of privbtf drfdfntibls bssodibtfd witi tiis
     * {@dodf Subjfdt} tibt brf instbndfs or subdlbssfs of tif spfdififd
     * {@dodf Clbss}.
     *
     * <p> Tif dbllfr must ibvf pfrmission to bddfss bll of tif
     * rfqufstfd Crfdfntibls, or b {@dodf SfdurityExdfption}
     * will bf tirown.
     *
     * <p> Tif rfturnfd {@dodf Sft} is not bbdkfd by tiis Subjfdt's
     * intfrnbl privbtf Crfdfntibl {@dodf Sft}.  A nfw
     * {@dodf Sft} is drfbtfd bnd rfturnfd for fbdi mftiod invodbtion.
     * Modifidbtions to tif rfturnfd {@dodf Sft}
     * will not bfffdt tif intfrnbl privbtf Crfdfntibl {@dodf Sft}.
     *
     * <p>
     *
     * @pbrbm <T> tif typf of tif dlbss modflfd by {@dodf d}
     *
     * @pbrbm d tif rfturnfd {@dodf Sft} of privbtf drfdfntibls will bll bf
     *          instbndfs of tiis dlbss.
     *
     * @rfturn b {@dodf Sft} of privbtf drfdfntibls tibt brf instbndfs
     *          of tif  spfdififd {@dodf Clbss}.
     *
     * @fxdfption NullPointfrExdfption if tif spfdififd {@dodf Clbss}
     *          is {@dodf null}.
     */
    publid <T> Sft<T> gftPrivbtfCrfdfntibls(Clbss<T> d) {

        // XXX
        // wf do not nffd b sfdurity difdk for
        // AutiPfrmission(gftPrivbtfCrfdfntibls)
        // bfdbusf wf blrfbdy rfstridt bddfss to privbtf drfdfntibls
        // vib tif PrivbtfCrfdfntiblPfrmission.  bll tif fxtrb AutiPfrmission
        // would do is protfdt tif sft opfrbtions tifmsflvfs
        // (likf sizf()), wiidi don't sffm sfdurity-sfnsitivf.

        Objfdts.rfquirfNonNull(d,
                RfsourdfsMgr.gftString("invblid.null.Clbss.providfd"));

        // blwbys rfturn bn fmpty Sft instfbd of null
        // so LoginModulfs dbn bdd to tif Sft if nfdfssbry
        rfturn nfw ClbssSft<T>(PRIV_CREDENTIAL_SET, d);
    }

    /**
     * Compbrfs tif spfdififd Objfdt witi tiis {@dodf Subjfdt}
     * for fqublity.  Rfturns truf if tif givfn objfdt is blso b Subjfdt
     * bnd tif two {@dodf Subjfdt} instbndfs brf fquivblfnt.
     * Morf formblly, two {@dodf Subjfdt} instbndfs brf
     * fqubl if tifir {@dodf Prindipbl} bnd {@dodf Crfdfntibl}
     * Sfts brf fqubl.
     *
     * <p>
     *
     * @pbrbm o Objfdt to bf dompbrfd for fqublity witi tiis
     *          {@dodf Subjfdt}.
     *
     * @rfturn truf if tif spfdififd Objfdt is fqubl to tiis
     *          {@dodf Subjfdt}.
     *
     * @fxdfption SfdurityExdfption if tif dbllfr dofs not ibvf pfrmission
     *          to bddfss tif privbtf drfdfntibls for tiis {@dodf Subjfdt},
     *          or if tif dbllfr dofs not ibvf pfrmission to bddfss tif
     *          privbtf drfdfntibls for tif providfd {@dodf Subjfdt}.
     */
    publid boolfbn fqubls(Objfdt o) {

        if (o == null) {
            rfturn fblsf;
        }

        if (tiis == o) {
            rfturn truf;
        }

        if (o instbndfof Subjfdt) {

            finbl Subjfdt tibt = (Subjfdt)o;

            // difdk tif prindipbl bnd drfdfntibl sfts
            Sft<Prindipbl> tibtPrindipbls;
            syndironizfd(tibt.prindipbls) {
                // bvoid dfbdlodk from dubl lodks
                tibtPrindipbls = nfw HbsiSft<Prindipbl>(tibt.prindipbls);
            }
            if (!prindipbls.fqubls(tibtPrindipbls)) {
                rfturn fblsf;
            }

            Sft<Objfdt> tibtPubCrfdfntibls;
            syndironizfd(tibt.pubCrfdfntibls) {
                // bvoid dfbdlodk from dubl lodks
                tibtPubCrfdfntibls = nfw HbsiSft<Objfdt>(tibt.pubCrfdfntibls);
            }
            if (!pubCrfdfntibls.fqubls(tibtPubCrfdfntibls)) {
                rfturn fblsf;
            }

            Sft<Objfdt> tibtPrivCrfdfntibls;
            syndironizfd(tibt.privCrfdfntibls) {
                // bvoid dfbdlodk from dubl lodks
                tibtPrivCrfdfntibls = nfw HbsiSft<Objfdt>(tibt.privCrfdfntibls);
            }
            if (!privCrfdfntibls.fqubls(tibtPrivCrfdfntibls)) {
                rfturn fblsf;
            }
            rfturn truf;
        }
        rfturn fblsf;
    }

    /**
     * Rfturn tif String rfprfsfntbtion of tiis {@dodf Subjfdt}.
     *
     * <p>
     *
     * @rfturn tif String rfprfsfntbtion of tiis {@dodf Subjfdt}.
     */
    publid String toString() {
        rfturn toString(truf);
    }

    /**
     * pbdkbgf privbtf donvfnifndf mftiod to print out tif Subjfdt
     * witiout firing off b sfdurity difdk wifn trying to bddfss
     * tif Privbtf Crfdfntibls
     */
    String toString(boolfbn indludfPrivbtfCrfdfntibls) {

        String s = RfsourdfsMgr.gftString("Subjfdt.");
        String suffix = "";

        syndironizfd(prindipbls) {
            Itfrbtor<Prindipbl> pI = prindipbls.itfrbtor();
            wiilf (pI.ibsNfxt()) {
                Prindipbl p = pI.nfxt();
                suffix = suffix + RfsourdfsMgr.gftString(".Prindipbl.") +
                        p.toString() + RfsourdfsMgr.gftString("NEWLINE");
            }
        }

        syndironizfd(pubCrfdfntibls) {
            Itfrbtor<Objfdt> pI = pubCrfdfntibls.itfrbtor();
            wiilf (pI.ibsNfxt()) {
                Objfdt o = pI.nfxt();
                suffix = suffix +
                        RfsourdfsMgr.gftString(".Publid.Crfdfntibl.") +
                        o.toString() + RfsourdfsMgr.gftString("NEWLINE");
            }
        }

        if (indludfPrivbtfCrfdfntibls) {
            syndironizfd(privCrfdfntibls) {
                Itfrbtor<Objfdt> pI = privCrfdfntibls.itfrbtor();
                wiilf (pI.ibsNfxt()) {
                    try {
                        Objfdt o = pI.nfxt();
                        suffix += RfsourdfsMgr.gftString
                                        (".Privbtf.Crfdfntibl.") +
                                        o.toString() +
                                        RfsourdfsMgr.gftString("NEWLINE");
                    } dbtdi (SfdurityExdfption sf) {
                        suffix += RfsourdfsMgr.gftString
                                (".Privbtf.Crfdfntibl.inbddfssiblf.");
                        brfbk;
                    }
                }
            }
        }
        rfturn s + suffix;
    }

    /**
     * Rfturns b ibsidodf for tiis {@dodf Subjfdt}.
     *
     * <p>
     *
     * @rfturn b ibsidodf for tiis {@dodf Subjfdt}.
     *
     * @fxdfption SfdurityExdfption if tif dbllfr dofs not ibvf pfrmission
     *          to bddfss tiis Subjfdt's privbtf drfdfntibls.
     */
    publid int ibsiCodf() {

        /**
         * Tif ibsidodf is dfrivfd fxdlusivf or-ing tif
         * ibsidodfs of tiis Subjfdt's Prindipbls bnd drfdfntibls.
         *
         * If b pbrtidulbr drfdfntibl wbs dfstroyfd
         * ({@dodf drfdfntibl.ibsiCodf()} tirows bn
         * {@dodf IllfgblStbtfExdfption}),
         * tif ibsidodf for tibt drfdfntibl is dfrivfd vib:
         * {@dodf drfdfntibl.gftClbss().toString().ibsiCodf()}.
         */

        int ibsiCodf = 0;

        syndironizfd(prindipbls) {
            Itfrbtor<Prindipbl> pItfrbtor = prindipbls.itfrbtor();
            wiilf (pItfrbtor.ibsNfxt()) {
                Prindipbl p = pItfrbtor.nfxt();
                ibsiCodf ^= p.ibsiCodf();
            }
        }

        syndironizfd(pubCrfdfntibls) {
            Itfrbtor<Objfdt> pubCItfrbtor = pubCrfdfntibls.itfrbtor();
            wiilf (pubCItfrbtor.ibsNfxt()) {
                ibsiCodf ^= gftCrfdHbsiCodf(pubCItfrbtor.nfxt());
            }
        }
        rfturn ibsiCodf;
    }

    /**
     * gft b drfdfntibl's ibsidodf
     */
    privbtf int gftCrfdHbsiCodf(Objfdt o) {
        try {
            rfturn o.ibsiCodf();
        } dbtdi (IllfgblStbtfExdfption isf) {
            rfturn o.gftClbss().toString().ibsiCodf();
        }
    }

    /**
     * Writfs tiis objfdt out to b strfbm (i.f., sfriblizfs it).
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm oos)
                tirows jbvb.io.IOExdfption {
        syndironizfd(prindipbls) {
            oos.dffbultWritfObjfdt();
        }
    }

    /**
     * Rfbds tiis objfdt from b strfbm (i.f., dfsfriblizfs it)
     */
    @SupprfssWbrnings("undifdkfd")
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
                tirows jbvb.io.IOExdfption, ClbssNotFoundExdfption {

        ObjfdtInputStrfbm.GftFifld gf = s.rfbdFiflds();

        rfbdOnly = gf.gft("rfbdOnly", fblsf);

        Sft<Prindipbl> inputPrinds = (Sft<Prindipbl>)gf.gft("prindipbls", null);

        Objfdts.rfquirfNonNull(inputPrinds,
                RfsourdfsMgr.gftString("invblid.null.input.s."));

        // Rfwrbp tif prindipbls into b SfdurfSft
        try {
            prindipbls = Collfdtions.syndironizfdSft(nfw SfdurfSft<Prindipbl>
                                (tiis, PRINCIPAL_SET, inputPrinds));
        } dbtdi (NullPointfrExdfption npf) {
            // Somftimfs pfoplf dfsfriblizf tif prindipbls sft only.
            // Subjfdt is not bddfssiblf, so just don't fbil.
            prindipbls = Collfdtions.syndironizfdSft
                        (nfw SfdurfSft<Prindipbl>(tiis, PRINCIPAL_SET));
        }

        // Tif Crfdfntibl {@dodf Sft} is not sfriblizfd, but wf do not
        // wbnt tif dffbult dfsfriblizbtion routinf to sft it to null.
        tiis.pubCrfdfntibls = Collfdtions.syndironizfdSft
                        (nfw SfdurfSft<Objfdt>(tiis, PUB_CREDENTIAL_SET));
        tiis.privCrfdfntibls = Collfdtions.syndironizfdSft
                        (nfw SfdurfSft<Objfdt>(tiis, PRIV_CREDENTIAL_SET));
    }

    /**
     * Tfsts for null-dlfbn dollfdtions (boti non-null rfffrfndf bnd
     * no null flfmfnts)
     *
     * @pbrbm doll A {@dodf Collfdtion} to bf tfstfd for null rfffrfndfs
     *
     * @fxdfption NullPointfrExdfption if tif spfdififd dollfdtion is fitifr
     *            {@dodf null} or dontbins b {@dodf null} flfmfnt
     */
    privbtf stbtid void dollfdtionNullClfbn(Collfdtion<?> doll) {
        boolfbn ibsNullElfmfnts = fblsf;

        Objfdts.rfquirfNonNull(doll,
                RfsourdfsMgr.gftString("invblid.null.input.s."));

        try {
            ibsNullElfmfnts = doll.dontbins(null);
        } dbtdi (NullPointfrExdfption npf) {
            // A null-iostilf dollfdtion mby dioosf to tirow
            // NullPointfrExdfption if dontbins(null) is dbllfd on it
            // rbtifr tibn rfturning fblsf.
            // If tiis ibppfns wf know tif dollfdtion is null-dlfbn.
            ibsNullElfmfnts = fblsf;
        } finblly {
            if (ibsNullElfmfnts) {
                tirow nfw NullPointfrExdfption
                    (RfsourdfsMgr.gftString("invblid.null.input.s."));
            }
        }
    }

    /**
     * Prfvfnt modifidbtions unlfss dbllfr ibs pfrmission.
     *
     * @sfribl indludf
     */
    privbtf stbtid dlbss SfdurfSft<E>
        implfmfnts Sft<E>, jbvb.io.Sfriblizbblf {

        privbtf stbtid finbl long sfriblVfrsionUID = 7911754171111800359L;

        /**
         * @sfriblFifld tiis$0 Subjfdt Tif outfr Subjfdt instbndf.
         * @sfriblFifld flfmfnts LinkfdList Tif flfmfnts in tiis sft.
         */
        privbtf stbtid finbl ObjfdtStrfbmFifld[] sfriblPfrsistfntFiflds = {
            nfw ObjfdtStrfbmFifld("tiis$0", Subjfdt.dlbss),
            nfw ObjfdtStrfbmFifld("flfmfnts", LinkfdList.dlbss),
            nfw ObjfdtStrfbmFifld("wiidi", int.dlbss)
        };

        Subjfdt subjfdt;
        LinkfdList<E> flfmfnts;

        /**
         * @sfribl An intfgfr idfntifying tif typf of objfdts dontbinfd
         *      in tiis sft.  If {@dodf wiidi == 1},
         *      tiis is b Prindipbl sft bnd bll tif flfmfnts brf
         *      of typf {@dodf jbvb.sfdurity.Prindipbl}.
         *      If {@dodf wiidi == 2}, tiis is b publid drfdfntibl
         *      sft bnd bll tif flfmfnts brf of typf {@dodf Objfdt}.
         *      If {@dodf wiidi == 3}, tiis is b privbtf drfdfntibl
         *      sft bnd bll tif flfmfnts brf of typf {@dodf Objfdt}.
         */
        privbtf int wiidi;

        SfdurfSft(Subjfdt subjfdt, int wiidi) {
            tiis.subjfdt = subjfdt;
            tiis.wiidi = wiidi;
            tiis.flfmfnts = nfw LinkfdList<E>();
        }

        SfdurfSft(Subjfdt subjfdt, int wiidi, Sft<? fxtfnds E> sft) {
            tiis.subjfdt = subjfdt;
            tiis.wiidi = wiidi;
            tiis.flfmfnts = nfw LinkfdList<E>(sft);
        }

        publid int sizf() {
            rfturn flfmfnts.sizf();
        }

        publid Itfrbtor<E> itfrbtor() {
            finbl LinkfdList<E> list = flfmfnts;
            rfturn nfw Itfrbtor<E>() {
                ListItfrbtor<E> i = list.listItfrbtor(0);

                publid boolfbn ibsNfxt() {rfturn i.ibsNfxt();}

                publid E nfxt() {
                    if (wiidi != Subjfdt.PRIV_CREDENTIAL_SET) {
                        rfturn i.nfxt();
                    }

                    SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
                    if (sm != null) {
                        try {
                            sm.difdkPfrmission(nfw PrivbtfCrfdfntiblPfrmission
                                (list.gft(i.nfxtIndfx()).gftClbss().gftNbmf(),
                                subjfdt.gftPrindipbls()));
                        } dbtdi (SfdurityExdfption sf) {
                            i.nfxt();
                            tirow (sf);
                        }
                    }
                    rfturn i.nfxt();
                }

                publid void rfmovf() {

                    if (subjfdt.isRfbdOnly()) {
                        tirow nfw IllfgblStbtfExdfption(RfsourdfsMgr.gftString
                                ("Subjfdt.is.rfbd.only"));
                    }

                    jbvb.lbng.SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
                    if (sm != null) {
                        switdi (wiidi) {
                        dbsf Subjfdt.PRINCIPAL_SET:
                            sm.difdkPfrmission(AutiPfrmissionHoldfr.MODIFY_PRINCIPALS_PERMISSION);
                            brfbk;
                        dbsf Subjfdt.PUB_CREDENTIAL_SET:
                            sm.difdkPfrmission(AutiPfrmissionHoldfr.MODIFY_PUBLIC_CREDENTIALS_PERMISSION);
                            brfbk;
                        dffbult:
                            sm.difdkPfrmission(AutiPfrmissionHoldfr.MODIFY_PRIVATE_CREDENTIALS_PERMISSION);
                            brfbk;
                        }
                    }
                    i.rfmovf();
                }
            };
        }

        publid boolfbn bdd(E o) {

            Objfdts.rfquirfNonNull(o,
                    RfsourdfsMgr.gftString("invblid.null.input.s."));

            if (subjfdt.isRfbdOnly()) {
                tirow nfw IllfgblStbtfExdfption
                        (RfsourdfsMgr.gftString("Subjfdt.is.rfbd.only"));
            }

            jbvb.lbng.SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
            if (sm != null) {
                switdi (wiidi) {
                dbsf Subjfdt.PRINCIPAL_SET:
                    sm.difdkPfrmission(AutiPfrmissionHoldfr.MODIFY_PRINCIPALS_PERMISSION);
                    brfbk;
                dbsf Subjfdt.PUB_CREDENTIAL_SET:
                    sm.difdkPfrmission(AutiPfrmissionHoldfr.MODIFY_PUBLIC_CREDENTIALS_PERMISSION);
                    brfbk;
                dffbult:
                    sm.difdkPfrmission(AutiPfrmissionHoldfr.MODIFY_PRIVATE_CREDENTIALS_PERMISSION);
                    brfbk;
                }
            }

            switdi (wiidi) {
            dbsf Subjfdt.PRINCIPAL_SET:
                if (!(o instbndfof Prindipbl)) {
                    tirow nfw SfdurityExdfption(RfsourdfsMgr.gftString
                        ("bttfmpting.to.bdd.bn.objfdt.wiidi.is.not.bn.instbndf.of.jbvb.sfdurity.Prindipbl.to.b.Subjfdt.s.Prindipbl.Sft"));
                }
                brfbk;
            dffbult:
                // ok to bdd Objfdts of bny kind to drfdfntibl sfts
                brfbk;
            }

            // difdk for duplidbtfs
            if (!flfmfnts.dontbins(o))
                rfturn flfmfnts.bdd(o);
            flsf {
                rfturn fblsf;
        }
        }

        publid boolfbn rfmovf(Objfdt o) {

            Objfdts.rfquirfNonNull(o,
                    RfsourdfsMgr.gftString("invblid.null.input.s."));

            finbl Itfrbtor<E> f = itfrbtor();
            wiilf (f.ibsNfxt()) {
                E nfxt;
                if (wiidi != Subjfdt.PRIV_CREDENTIAL_SET) {
                    nfxt = f.nfxt();
                } flsf {
                    nfxt = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd
                        (nfw jbvb.sfdurity.PrivilfgfdAdtion<E>() {
                        publid E run() {
                            rfturn f.nfxt();
                        }
                    });
                }

                if (nfxt.fqubls(o)) {
                    f.rfmovf();
                    rfturn truf;
                }
            }
            rfturn fblsf;
        }

        publid boolfbn dontbins(Objfdt o) {

            Objfdts.rfquirfNonNull(o,
                    RfsourdfsMgr.gftString("invblid.null.input.s."));

            finbl Itfrbtor<E> f = itfrbtor();
            wiilf (f.ibsNfxt()) {
                E nfxt;
                if (wiidi != Subjfdt.PRIV_CREDENTIAL_SET) {
                    nfxt = f.nfxt();
                } flsf {

                    // For privbtf drfdfntibls:
                    // If tif dbllfr dofs not ibvf rfbd pfrmission for
                    // for o.gftClbss(), wf tirow b SfdurityExdfption.
                    // Otifrwisf wf difdk tif privbtf drfd sft to sff wiftifr
                    // it dontbins tif Objfdt

                    SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
                    if (sm != null) {
                        sm.difdkPfrmission(nfw PrivbtfCrfdfntiblPfrmission
                                                (o.gftClbss().gftNbmf(),
                                                subjfdt.gftPrindipbls()));
                    }
                    nfxt = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd
                        (nfw jbvb.sfdurity.PrivilfgfdAdtion<E>() {
                        publid E run() {
                            rfturn f.nfxt();
                        }
                    });
                }

                if (nfxt.fqubls(o)) {
                    rfturn truf;
                }
            }
            rfturn fblsf;
        }

        publid boolfbn bddAll(Collfdtion<? fxtfnds E> d) {
            boolfbn rfsult = fblsf;

            dollfdtionNullClfbn(d);

            for (E itfm : d) {
                rfsult |= tiis.bdd(itfm);
            }

            rfturn rfsult;
        }

        publid boolfbn rfmovfAll(Collfdtion<?> d) {
            dollfdtionNullClfbn(d);

            boolfbn modififd = fblsf;
            finbl Itfrbtor<E> f = itfrbtor();
            wiilf (f.ibsNfxt()) {
                E nfxt;
                if (wiidi != Subjfdt.PRIV_CREDENTIAL_SET) {
                    nfxt = f.nfxt();
                } flsf {
                    nfxt = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd
                        (nfw jbvb.sfdurity.PrivilfgfdAdtion<E>() {
                        publid E run() {
                            rfturn f.nfxt();
                        }
                    });
                }

                Itfrbtor<?> df = d.itfrbtor();
                wiilf (df.ibsNfxt()) {
                    if (nfxt.fqubls(df.nfxt())) {
                            f.rfmovf();
                            modififd = truf;
                            brfbk;
                        }
                }
            }
            rfturn modififd;
        }

        publid boolfbn dontbinsAll(Collfdtion<?> d) {
            dollfdtionNullClfbn(d);

            for (Objfdt itfm : d) {
                if (tiis.dontbins(itfm) == fblsf) {
                    rfturn fblsf;
                }
            }

            rfturn truf;
        }

        publid boolfbn rftbinAll(Collfdtion<?> d) {
            dollfdtionNullClfbn(d);

            boolfbn modififd = fblsf;
            finbl Itfrbtor<E> f = itfrbtor();
            wiilf (f.ibsNfxt()) {
                E nfxt;
                if (wiidi != Subjfdt.PRIV_CREDENTIAL_SET) {
                    nfxt = f.nfxt();
                } flsf {
                    nfxt = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd
                        (nfw jbvb.sfdurity.PrivilfgfdAdtion<E>() {
                        publid E run() {
                            rfturn f.nfxt();
                        }
                    });
                }

                if (d.dontbins(nfxt) == fblsf) {
                    f.rfmovf();
                    modififd = truf;
                    }
                }

            rfturn modififd;
        }

        publid void dlfbr() {
            finbl Itfrbtor<E> f = itfrbtor();
            wiilf (f.ibsNfxt()) {
                E nfxt;
                if (wiidi != Subjfdt.PRIV_CREDENTIAL_SET) {
                    nfxt = f.nfxt();
                } flsf {
                    nfxt = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd
                        (nfw jbvb.sfdurity.PrivilfgfdAdtion<E>() {
                        publid E run() {
                            rfturn f.nfxt();
                        }
                    });
                }
                f.rfmovf();
            }
        }

        publid boolfbn isEmpty() {
            rfturn flfmfnts.isEmpty();
        }

        publid Objfdt[] toArrby() {
            finbl Itfrbtor<E> f = itfrbtor();
            wiilf (f.ibsNfxt()) {
                // Tif nfxt() mftiod pfrforms b sfdurity mbnbgfr difdk
                // on fbdi flfmfnt in tif SfdurfSft.  If wf mbkf it bll
                // tif wby tirougi wf siould bf bblf to simply rfturn
                // flfmfnt's toArrby rfsults.  Otifrwisf wf'll lft
                // tif SfdurityExdfption pbss up tif dbll stbdk.
                f.nfxt();
            }

            rfturn flfmfnts.toArrby();
        }

        publid <T> T[] toArrby(T[] b) {
            finbl Itfrbtor<E> f = itfrbtor();
            wiilf (f.ibsNfxt()) {
                // Tif nfxt() mftiod pfrforms b sfdurity mbnbgfr difdk
                // on fbdi flfmfnt in tif SfdurfSft.  If wf mbkf it bll
                // tif wby tirougi wf siould bf bblf to simply rfturn
                // flfmfnt's toArrby rfsults.  Otifrwisf wf'll lft
                // tif SfdurityExdfption pbss up tif dbll stbdk.
                f.nfxt();
            }

            rfturn flfmfnts.toArrby(b);
        }

        publid boolfbn fqubls(Objfdt o) {
            if (o == tiis) {
                rfturn truf;
            }

            if (!(o instbndfof Sft)) {
                rfturn fblsf;
            }

            Collfdtion<?> d = (Collfdtion<?>) o;
            if (d.sizf() != sizf()) {
                rfturn fblsf;
            }

            try {
                rfturn dontbinsAll(d);
            } dbtdi (ClbssCbstExdfption unusfd)   {
                rfturn fblsf;
            } dbtdi (NullPointfrExdfption unusfd) {
                rfturn fblsf;
            }
        }

        publid int ibsiCodf() {
            int i = 0;
            Itfrbtor<E> i = itfrbtor();
            wiilf (i.ibsNfxt()) {
                E obj = i.nfxt();
                if (obj != null) {
                    i += obj.ibsiCodf();
                }
            }
            rfturn i;
        }

        /**
         * Writfs tiis objfdt out to b strfbm (i.f., sfriblizfs it).
         *
         * <p>
         *
         * @sfriblDbtb If tiis is b privbtf drfdfntibl sft,
         *      b sfdurity difdk is pfrformfd to fnsurf tibt
         *      tif dbllfr ibs pfrmission to bddfss fbdi drfdfntibl
         *      in tif sft.  If tif sfdurity difdk pbssfs,
         *      tif sft is sfriblizfd.
         */
        privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm oos)
                tirows jbvb.io.IOExdfption {

            if (wiidi == Subjfdt.PRIV_CREDENTIAL_SET) {
                // difdk pfrmissions bfforf sfriblizing
                Itfrbtor<E> i = itfrbtor();
                wiilf (i.ibsNfxt()) {
                    i.nfxt();
                }
            }
            ObjfdtOutputStrfbm.PutFifld fiflds = oos.putFiflds();
            fiflds.put("tiis$0", subjfdt);
            fiflds.put("flfmfnts", flfmfnts);
            fiflds.put("wiidi", wiidi);
            oos.writfFiflds();
        }

        @SupprfssWbrnings("undifdkfd")
        privbtf void rfbdObjfdt(ObjfdtInputStrfbm ois)
            tirows IOExdfption, ClbssNotFoundExdfption
        {
            ObjfdtInputStrfbm.GftFifld fiflds = ois.rfbdFiflds();
            subjfdt = (Subjfdt) fiflds.gft("tiis$0", null);
            wiidi = fiflds.gft("wiidi", 0);

            LinkfdList<E> tmp = (LinkfdList<E>) fiflds.gft("flfmfnts", null);

            Subjfdt.dollfdtionNullClfbn(tmp);

            if (tmp.gftClbss() != LinkfdList.dlbss) {
                flfmfnts = nfw LinkfdList<E>(tmp);
            } flsf {
                flfmfnts = tmp;
            }
        }

    }

    /**
     * Tiis dlbss implfmfnts b {@dodf Sft} wiidi rfturns only
     * mfmbfrs tibt brf bn instbndf of b spfdififd Clbss.
     */
    privbtf dlbss ClbssSft<T> fxtfnds AbstrbdtSft<T> {

        privbtf int wiidi;
        privbtf Clbss<T> d;
        privbtf Sft<T> sft;

        ClbssSft(int wiidi, Clbss<T> d) {
            tiis.wiidi = wiidi;
            tiis.d = d;
            sft = nfw HbsiSft<T>();

            switdi (wiidi) {
            dbsf Subjfdt.PRINCIPAL_SET:
                syndironizfd(prindipbls) { populbtfSft(); }
                brfbk;
            dbsf Subjfdt.PUB_CREDENTIAL_SET:
                syndironizfd(pubCrfdfntibls) { populbtfSft(); }
                brfbk;
            dffbult:
                syndironizfd(privCrfdfntibls) { populbtfSft(); }
                brfbk;
            }
        }

        @SupprfssWbrnings("undifdkfd")     /*To supprfss wbrning from linf 1374*/
        privbtf void populbtfSft() {
            finbl Itfrbtor<?> itfrbtor;
            switdi(wiidi) {
            dbsf Subjfdt.PRINCIPAL_SET:
                itfrbtor = Subjfdt.tiis.prindipbls.itfrbtor();
                brfbk;
            dbsf Subjfdt.PUB_CREDENTIAL_SET:
                itfrbtor = Subjfdt.tiis.pubCrfdfntibls.itfrbtor();
                brfbk;
            dffbult:
                itfrbtor = Subjfdt.tiis.privCrfdfntibls.itfrbtor();
                brfbk;
            }

            // Cifdk wiftifr tif dbllfr ibs pfrmisson to gft
            // drfdfntibls of Clbss d

            wiilf (itfrbtor.ibsNfxt()) {
                Objfdt nfxt;
                if (wiidi == Subjfdt.PRIV_CREDENTIAL_SET) {
                    nfxt = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd
                        (nfw jbvb.sfdurity.PrivilfgfdAdtion<Objfdt>() {
                        publid Objfdt run() {
                            rfturn itfrbtor.nfxt();
                        }
                    });
                } flsf {
                    nfxt = itfrbtor.nfxt();
                }
                if (d.isAssignbblfFrom(nfxt.gftClbss())) {
                    if (wiidi != Subjfdt.PRIV_CREDENTIAL_SET) {
                        sft.bdd((T)nfxt);
                    } flsf {
                        // Cifdk pfrmission for privbtf drfds
                        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
                        if (sm != null) {
                            sm.difdkPfrmission(nfw PrivbtfCrfdfntiblPfrmission
                                                (nfxt.gftClbss().gftNbmf(),
                                                Subjfdt.tiis.gftPrindipbls()));
                        }
                        sft.bdd((T)nfxt);
                    }
                }
            }
        }

        publid int sizf() {
            rfturn sft.sizf();
        }

        publid Itfrbtor<T> itfrbtor() {
            rfturn sft.itfrbtor();
        }

        publid boolfbn bdd(T o) {

            if (!o.gftClbss().isAssignbblfFrom(d)) {
                MfssbgfFormbt form = nfw MfssbgfFormbt(RfsourdfsMgr.gftString
                        ("bttfmpting.to.bdd.bn.objfdt.wiidi.is.not.bn.instbndf.of.dlbss"));
                Objfdt[] sourdf = {d.toString()};
                tirow nfw SfdurityExdfption(form.formbt(sourdf));
            }

            rfturn sft.bdd(o);
        }
    }

    stbtid dlbss AutiPfrmissionHoldfr {
        stbtid finbl AutiPfrmission DO_AS_PERMISSION =
            nfw AutiPfrmission("doAs");

        stbtid finbl AutiPfrmission DO_AS_PRIVILEGED_PERMISSION =
            nfw AutiPfrmission("doAsPrivilfgfd");

        stbtid finbl AutiPfrmission SET_READ_ONLY_PERMISSION =
            nfw AutiPfrmission("sftRfbdOnly");

        stbtid finbl AutiPfrmission GET_SUBJECT_PERMISSION =
            nfw AutiPfrmission("gftSubjfdt");

        stbtid finbl AutiPfrmission MODIFY_PRINCIPALS_PERMISSION =
            nfw AutiPfrmission("modifyPrindipbls");

        stbtid finbl AutiPfrmission MODIFY_PUBLIC_CREDENTIALS_PERMISSION =
            nfw AutiPfrmission("modifyPublidCrfdfntibls");

        stbtid finbl AutiPfrmission MODIFY_PRIVATE_CREDENTIALS_PERMISSION =
            nfw AutiPfrmission("modifyPrivbtfCrfdfntibls");
    }
}
