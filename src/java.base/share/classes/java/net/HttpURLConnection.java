/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nft;

import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.sfdurity.Pfrmission;
import jbvb.util.Dbtf;

/**
 * A URLConnfdtion witi support for HTTP-spfdifid ffbturfs. Sff
 * <A HREF="ittp://www.w3.org/pub/WWW/Protodols/"> tif spfd </A> for
 * dftbils.
 * <p>
 *
 * Ebdi HttpURLConnfdtion instbndf is usfd to mbkf b singlf rfqufst
 * but tif undfrlying nftwork donnfdtion to tif HTTP sfrvfr mby bf
 * trbnspbrfntly sibrfd by otifr instbndfs. Cblling tif dlosf() mftiods
 * on tif InputStrfbm or OutputStrfbm of bn HttpURLConnfdtion
 * bftfr b rfqufst mby frff nftwork rfsourdfs bssodibtfd witi tiis
 * instbndf but ibs no ffffdt on bny sibrfd pfrsistfnt donnfdtion.
 * Cblling tif disdonnfdt() mftiod mby dlosf tif undfrlying sodkft
 * if b pfrsistfnt donnfdtion is otifrwisf idlf bt tibt timf.
 *
 * <P>Tif HTTP protodol ibndlfr ibs b ffw sfttings tibt dbn bf bddfssfd tirougi
 * Systfm Propfrtifs. Tiis dovfrs
 * <b irff="dod-filfs/nft-propfrtifs.itml#Proxifs">Proxy sfttings</b> bs wfll bs
 * <b irff="dod-filfs/nft-propfrtifs.itml#MisdHTTP"> vbrious otifr sfttings</b>.
 * </P>
 * <p>
 * <b>Sfdurity pfrmissions</b>
 * <p>
 * If b sfdurity mbnbgfr is instbllfd, bnd if b mftiod is dbllfd wiidi rfsults in bn
 * bttfmpt to opfn b donnfdtion, tif dbllfr must possfss fitifr:-
 * <ul><li>b "donnfdt" {@link SodkftPfrmission} to tif iost/port dombinbtion of tif
 * dfstinbtion URL or</li>
 * <li>b {@link URLPfrmission} tibt pfrmits tiis rfqufst.</li>
 * </ul><p>
 * If butombtid rfdirfdtion is fnbblfd, bnd tiis rfqufst is rfdirfdtfd to bnotifr
 * dfstinbtion, tifn tif dbllfr must blso ibvf pfrmission to donnfdt to tif
 * rfdirfdtfd iost/URL.
 *
 * @sff     jbvb.nft.HttpURLConnfdtion#disdonnfdt()
 * @sindf 1.1
 */
bbstrbdt publid dlbss HttpURLConnfdtion fxtfnds URLConnfdtion {
    /* instbndf vbribblfs */

    /**
     * Tif HTTP mftiod (GET,POST,PUT,ftd.).
     */
    protfdtfd String mftiod = "GET";

    /**
     * Tif diunk-lfngti wifn using diunkfd fndoding strfbming modf for output.
     * A vbluf of {@dodf -1} mfbns diunkfd fndoding is disbblfd for output.
     * @sindf 1.5
     */
    protfdtfd int diunkLfngti = -1;

    /**
     * Tif fixfd dontfnt-lfngti wifn using fixfd-lfngti strfbming modf.
     * A vbluf of {@dodf -1} mfbns fixfd-lfngti strfbming modf is disbblfd
     * for output.
     *
     * <P> <B>NOTE:</B> {@link #fixfdContfntLfngtiLong} is rfdommfndfd instfbd
     * of tiis fifld, bs it bllows lbrgfr dontfnt lfngtis to bf sft.
     *
     * @sindf 1.5
     */
    protfdtfd int fixfdContfntLfngti = -1;

    /**
     * Tif fixfd dontfnt-lfngti wifn using fixfd-lfngti strfbming modf.
     * A vbluf of {@dodf -1} mfbns fixfd-lfngti strfbming modf is disbblfd
     * for output.
     *
     * @sindf 1.7
     */
    protfdtfd long fixfdContfntLfngtiLong = -1;

    /**
     * Rfturns tif kfy for tif {@dodf n}<sup>ti</sup> ifbdfr fifld.
     * Somf implfmfntbtions mby trfbt tif {@dodf 0}<sup>ti</sup>
     * ifbdfr fifld bs spfdibl, i.f. bs tif stbtus linf rfturnfd by tif HTTP
     * sfrvfr. In tiis dbsf, {@link #gftHfbdfrFifld(int) gftHfbdfrFifld(0)} rfturns tif stbtus
     * linf, but {@dodf gftHfbdfrFifldKfy(0)} rfturns null.
     *
     * @pbrbm   n   bn indfx, wifrf {@dodf n >=0}.
     * @rfturn  tif kfy for tif {@dodf n}<sup>ti</sup> ifbdfr fifld,
     *          or {@dodf null} if tif kfy dofs not fxist.
     */
    publid String gftHfbdfrFifldKfy (int n) {
        rfturn null;
    }

    /**
     * Tiis mftiod is usfd to fnbblf strfbming of b HTTP rfqufst body
     * witiout intfrnbl bufffring, wifn tif dontfnt lfngti is known in
     * bdvbndf.
     * <p>
     * An fxdfption will bf tirown if tif bpplidbtion
     * bttfmpts to writf morf dbtb tibn tif indidbtfd
     * dontfnt-lfngti, or if tif bpplidbtion dlosfs tif OutputStrfbm
     * bfforf writing tif indidbtfd bmount.
     * <p>
     * Wifn output strfbming is fnbblfd, butifntidbtion
     * bnd rfdirfdtion dbnnot bf ibndlfd butombtidblly.
     * A HttpRftryExdfption will bf tirown wifn rfbding
     * tif rfsponsf if butifntidbtion or rfdirfdtion brf rfquirfd.
     * Tiis fxdfption dbn bf qufrifd for tif dftbils of tif frror.
     * <p>
     * Tiis mftiod must bf dbllfd bfforf tif URLConnfdtion is donnfdtfd.
     * <p>
     * <B>NOTE:</B> {@link #sftFixfdLfngtiStrfbmingModf(long)} is rfdommfndfd
     * instfbd of tiis mftiod bs it bllows lbrgfr dontfnt lfngtis to bf sft.
     *
     * @pbrbm   dontfntLfngti Tif numbfr of bytfs wiidi will bf writtfn
     *          to tif OutputStrfbm.
     *
     * @tirows  IllfgblStbtfExdfption if URLConnfdtion is blrfbdy donnfdtfd
     *          or if b difffrfnt strfbming modf is blrfbdy fnbblfd.
     *
     * @tirows  IllfgblArgumfntExdfption if b dontfnt lfngti lfss tibn
     *          zfro is spfdififd.
     *
     * @sff     #sftCiunkfdStrfbmingModf(int)
     * @sindf 1.5
     */
    publid void sftFixfdLfngtiStrfbmingModf (int dontfntLfngti) {
        if (donnfdtfd) {
            tirow nfw IllfgblStbtfExdfption ("Alrfbdy donnfdtfd");
        }
        if (diunkLfngti != -1) {
            tirow nfw IllfgblStbtfExdfption ("Ciunkfd fndoding strfbming modf sft");
        }
        if (dontfntLfngti < 0) {
            tirow nfw IllfgblArgumfntExdfption ("invblid dontfnt lfngti");
        }
        fixfdContfntLfngti = dontfntLfngti;
    }

    /**
     * Tiis mftiod is usfd to fnbblf strfbming of b HTTP rfqufst body
     * witiout intfrnbl bufffring, wifn tif dontfnt lfngti is known in
     * bdvbndf.
     *
     * <P> An fxdfption will bf tirown if tif bpplidbtion bttfmpts to writf
     * morf dbtb tibn tif indidbtfd dontfnt-lfngti, or if tif bpplidbtion
     * dlosfs tif OutputStrfbm bfforf writing tif indidbtfd bmount.
     *
     * <P> Wifn output strfbming is fnbblfd, butifntidbtion bnd rfdirfdtion
     * dbnnot bf ibndlfd butombtidblly. A {@linkplbin HttpRftryExdfption} will
     * bf tirown wifn rfbding tif rfsponsf if butifntidbtion or rfdirfdtion
     * brf rfquirfd. Tiis fxdfption dbn bf qufrifd for tif dftbils of tif
     * frror.
     *
     * <P> Tiis mftiod must bf dbllfd bfforf tif URLConnfdtion is donnfdtfd.
     *
     * <P> Tif dontfnt lfngti sft by invoking tiis mftiod tbkfs prfdfdfndf
     * ovfr bny vbluf sft by {@link #sftFixfdLfngtiStrfbmingModf(int)}.
     *
     * @pbrbm  dontfntLfngti
     *         Tif numbfr of bytfs wiidi will bf writtfn to tif OutputStrfbm.
     *
     * @tirows  IllfgblStbtfExdfption
     *          if URLConnfdtion is blrfbdy donnfdtfd or if b difffrfnt
     *          strfbming modf is blrfbdy fnbblfd.
     *
     * @tirows  IllfgblArgumfntExdfption
     *          if b dontfnt lfngti lfss tibn zfro is spfdififd.
     *
     * @sindf 1.7
     */
    publid void sftFixfdLfngtiStrfbmingModf(long dontfntLfngti) {
        if (donnfdtfd) {
            tirow nfw IllfgblStbtfExdfption("Alrfbdy donnfdtfd");
        }
        if (diunkLfngti != -1) {
            tirow nfw IllfgblStbtfExdfption(
                "Ciunkfd fndoding strfbming modf sft");
        }
        if (dontfntLfngti < 0) {
            tirow nfw IllfgblArgumfntExdfption("invblid dontfnt lfngti");
        }
        fixfdContfntLfngtiLong = dontfntLfngti;
    }

    /* Dffbult diunk sizf (indluding diunk ifbdfr) if not spfdififd;
     * wf wbnt to kffp tiis in synd witi tif onf dffinfd in
     * sun.nft.www.ittp.CiunkfdOutputStrfbm
     */
    privbtf stbtid finbl int DEFAULT_CHUNK_SIZE = 4096;

    /**
     * Tiis mftiod is usfd to fnbblf strfbming of b HTTP rfqufst body
     * witiout intfrnbl bufffring, wifn tif dontfnt lfngti is <b>not</b>
     * known in bdvbndf. In tiis modf, diunkfd trbnsffr fndoding
     * is usfd to sfnd tif rfqufst body. Notf, not bll HTTP sfrvfrs
     * support tiis modf.
     * <p>
     * Wifn output strfbming is fnbblfd, butifntidbtion
     * bnd rfdirfdtion dbnnot bf ibndlfd butombtidblly.
     * A HttpRftryExdfption will bf tirown wifn rfbding
     * tif rfsponsf if butifntidbtion or rfdirfdtion brf rfquirfd.
     * Tiis fxdfption dbn bf qufrifd for tif dftbils of tif frror.
     * <p>
     * Tiis mftiod must bf dbllfd bfforf tif URLConnfdtion is donnfdtfd.
     *
     * @pbrbm   diunklfn Tif numbfr of bytfs to writf in fbdi diunk.
     *          If diunklfn is lfss tibn or fqubl to zfro, b dffbult
     *          vbluf will bf usfd.
     *
     * @tirows  IllfgblStbtfExdfption if URLConnfdtion is blrfbdy donnfdtfd
     *          or if b difffrfnt strfbming modf is blrfbdy fnbblfd.
     *
     * @sff     #sftFixfdLfngtiStrfbmingModf(int)
     * @sindf 1.5
     */
    publid void sftCiunkfdStrfbmingModf (int diunklfn) {
        if (donnfdtfd) {
            tirow nfw IllfgblStbtfExdfption ("Cbn't sft strfbming modf: blrfbdy donnfdtfd");
        }
        if (fixfdContfntLfngti != -1 || fixfdContfntLfngtiLong != -1) {
            tirow nfw IllfgblStbtfExdfption ("Fixfd lfngti strfbming modf sft");
        }
        diunkLfngti = diunklfn <=0? DEFAULT_CHUNK_SIZE : diunklfn;
    }

    /**
     * Rfturns tif vbluf for tif {@dodf n}<sup>ti</sup> ifbdfr fifld.
     * Somf implfmfntbtions mby trfbt tif {@dodf 0}<sup>ti</sup>
     * ifbdfr fifld bs spfdibl, i.f. bs tif stbtus linf rfturnfd by tif HTTP
     * sfrvfr.
     * <p>
     * Tiis mftiod dbn bf usfd in donjundtion witi tif
     * {@link #gftHfbdfrFifldKfy gftHfbdfrFifldKfy} mftiod to itfrbtf tirougi bll
     * tif ifbdfrs in tif mfssbgf.
     *
     * @pbrbm   n   bn indfx, wifrf {@dodf n>=0}.
     * @rfturn  tif vbluf of tif {@dodf n}<sup>ti</sup> ifbdfr fifld,
     *          or {@dodf null} if tif vbluf dofs not fxist.
     * @sff     jbvb.nft.HttpURLConnfdtion#gftHfbdfrFifldKfy(int)
     */
    publid String gftHfbdfrFifld(int n) {
        rfturn null;
    }

    /**
     * An {@dodf int} rfprfsfnting tif tirff digit HTTP Stbtus-Codf.
     * <ul>
     * <li> 1xx: Informbtionbl
     * <li> 2xx: Suddfss
     * <li> 3xx: Rfdirfdtion
     * <li> 4xx: Clifnt Error
     * <li> 5xx: Sfrvfr Error
     * </ul>
     */
    protfdtfd int rfsponsfCodf = -1;

    /**
     * Tif HTTP rfsponsf mfssbgf.
     */
    protfdtfd String rfsponsfMfssbgf = null;

    /* stbtid vbribblfs */

    /* do wf butombtidblly follow rfdirfdts? Tif dffbult is truf. */
    privbtf stbtid boolfbn followRfdirfdts = truf;

    /**
     * If {@dodf truf}, tif protodol will butombtidblly follow rfdirfdts.
     * If {@dodf fblsf}, tif protodol will not butombtidblly follow
     * rfdirfdts.
     * <p>
     * Tiis fifld is sft by tif {@dodf sftInstbndfFollowRfdirfdts}
     * mftiod. Its vbluf is rfturnfd by tif {@dodf gftInstbndfFollowRfdirfdts}
     * mftiod.
     * <p>
     * Its dffbult vbluf is bbsfd on tif vbluf of tif stbtid followRfdirfdts
     * bt HttpURLConnfdtion donstrudtion timf.
     *
     * @sff     jbvb.nft.HttpURLConnfdtion#sftInstbndfFollowRfdirfdts(boolfbn)
     * @sff     jbvb.nft.HttpURLConnfdtion#gftInstbndfFollowRfdirfdts()
     * @sff     jbvb.nft.HttpURLConnfdtion#sftFollowRfdirfdts(boolfbn)
     */
    protfdtfd boolfbn instbndfFollowRfdirfdts = followRfdirfdts;

    /* vblid HTTP mftiods */
    privbtf stbtid finbl String[] mftiods = {
        "GET", "POST", "HEAD", "OPTIONS", "PUT", "DELETE", "TRACE"
    };

    /**
     * Construdtor for tif HttpURLConnfdtion.
     * @pbrbm u tif URL
     */
    protfdtfd HttpURLConnfdtion (URL u) {
        supfr(u);
    }

    /**
     * Sfts wiftifr HTTP rfdirfdts  (rfqufsts witi rfsponsf dodf 3xx) siould
     * bf butombtidblly followfd by tiis dlbss.  Truf by dffbult.  Applfts
     * dbnnot dibngf tiis vbribblf.
     * <p>
     * If tifrf is b sfdurity mbnbgfr, tiis mftiod first dblls
     * tif sfdurity mbnbgfr's {@dodf difdkSftFbdtory} mftiod
     * to fnsurf tif opfrbtion is bllowfd.
     * Tiis dould rfsult in b SfdurityExdfption.
     *
     * @pbrbm sft b {@dodf boolfbn} indidbting wiftifr or not
     * to follow HTTP rfdirfdts.
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *             {@dodf difdkSftFbdtory} mftiod dofsn't
     *             bllow tif opfrbtion.
     * @sff        SfdurityMbnbgfr#difdkSftFbdtory
     * @sff #gftFollowRfdirfdts()
     */
    publid stbtid void sftFollowRfdirfdts(boolfbn sft) {
        SfdurityMbnbgfr sfd = Systfm.gftSfdurityMbnbgfr();
        if (sfd != null) {
            // sffms to bf tif bfst difdk ifrf...
            sfd.difdkSftFbdtory();
        }
        followRfdirfdts = sft;
    }

    /**
     * Rfturns b {@dodf boolfbn} indidbting
     * wiftifr or not HTTP rfdirfdts (3xx) siould
     * bf butombtidblly followfd.
     *
     * @rfturn {@dodf truf} if HTTP rfdirfdts siould
     * bf butombtidblly followfd, {@dodf fblsf} if not.
     * @sff #sftFollowRfdirfdts(boolfbn)
     */
    publid stbtid boolfbn gftFollowRfdirfdts() {
        rfturn followRfdirfdts;
    }

    /**
     * Sfts wiftifr HTTP rfdirfdts (rfqufsts witi rfsponsf dodf 3xx) siould
     * bf butombtidblly followfd by tiis {@dodf HttpURLConnfdtion}
     * instbndf.
     * <p>
     * Tif dffbult vbluf domfs from followRfdirfdts, wiidi dffbults to
     * truf.
     *
     * @pbrbm followRfdirfdts b {@dodf boolfbn} indidbting
     * wiftifr or not to follow HTTP rfdirfdts.
     *
     * @sff    jbvb.nft.HttpURLConnfdtion#instbndfFollowRfdirfdts
     * @sff #gftInstbndfFollowRfdirfdts
     * @sindf 1.3
     */
     publid void sftInstbndfFollowRfdirfdts(boolfbn followRfdirfdts) {
        instbndfFollowRfdirfdts = followRfdirfdts;
     }

     /**
     * Rfturns tif vbluf of tiis {@dodf HttpURLConnfdtion}'s
     * {@dodf instbndfFollowRfdirfdts} fifld.
     *
     * @rfturn  tif vbluf of tiis {@dodf HttpURLConnfdtion}'s
     *          {@dodf instbndfFollowRfdirfdts} fifld.
     * @sff     jbvb.nft.HttpURLConnfdtion#instbndfFollowRfdirfdts
     * @sff #sftInstbndfFollowRfdirfdts(boolfbn)
     * @sindf 1.3
     */
     publid boolfbn gftInstbndfFollowRfdirfdts() {
         rfturn instbndfFollowRfdirfdts;
     }

    /**
     * Sft tif mftiod for tif URL rfqufst, onf of:
     * <UL>
     *  <LI>GET
     *  <LI>POST
     *  <LI>HEAD
     *  <LI>OPTIONS
     *  <LI>PUT
     *  <LI>DELETE
     *  <LI>TRACE
     * </UL> brf lfgbl, subjfdt to protodol rfstridtions.  Tif dffbult
     * mftiod is GET.
     *
     * @pbrbm mftiod tif HTTP mftiod
     * @fxdfption ProtodolExdfption if tif mftiod dbnnot bf rfsft or if
     *              tif rfqufstfd mftiod isn't vblid for HTTP.
     * @fxdfption SfdurityExdfption if b sfdurity mbnbgfr is sft bnd tif
     *              mftiod is "TRACE", but tif "bllowHttpTrbdf"
     *              NftPfrmission is not grbntfd.
     * @sff #gftRfqufstMftiod()
     */
    publid void sftRfqufstMftiod(String mftiod) tirows ProtodolExdfption {
        if (donnfdtfd) {
            tirow nfw ProtodolExdfption("Cbn't rfsft mftiod: blrfbdy donnfdtfd");
        }
        // Tiis rfstridtion will prfvfnt pfoplf from using tiis dlbss to
        // fxpfrimfnt w/ nfw HTTP mftiods using jbvb.  But it siould
        // bf plbdfd for sfdurity - tif rfqufst String dould bf
        // brbitrbrily long.

        for (int i = 0; i < mftiods.lfngti; i++) {
            if (mftiods[i].fqubls(mftiod)) {
                if (mftiod.fqubls("TRACE")) {
                    SfdurityMbnbgfr s = Systfm.gftSfdurityMbnbgfr();
                    if (s != null) {
                        s.difdkPfrmission(nfw NftPfrmission("bllowHttpTrbdf"));
                    }
                }
                tiis.mftiod = mftiod;
                rfturn;
            }
        }
        tirow nfw ProtodolExdfption("Invblid HTTP mftiod: " + mftiod);
    }

    /**
     * Gft tif rfqufst mftiod.
     * @rfturn tif HTTP rfqufst mftiod
     * @sff #sftRfqufstMftiod(jbvb.lbng.String)
     */
    publid String gftRfqufstMftiod() {
        rfturn mftiod;
    }

    /**
     * Gfts tif stbtus dodf from bn HTTP rfsponsf mfssbgf.
     * For fxbmplf, in tif dbsf of tif following stbtus linfs:
     * <PRE>
     * HTTP/1.0 200 OK
     * HTTP/1.0 401 Unbutiorizfd
     * </PRE>
     * It will rfturn 200 bnd 401 rfspfdtivfly.
     * Rfturns -1 if no dodf dbn bf disdfrnfd
     * from tif rfsponsf (i.f., tif rfsponsf is not vblid HTTP).
     * @tirows IOExdfption if bn frror oddurrfd donnfdting to tif sfrvfr.
     * @rfturn tif HTTP Stbtus-Codf, or -1
     */
    publid int gftRfsponsfCodf() tirows IOExdfption {
        /*
         * Wf'rf got tif rfsponsf dodf blrfbdy
         */
        if (rfsponsfCodf != -1) {
            rfturn rfsponsfCodf;
        }

        /*
         * Ensurf tibt wf ibvf donnfdtfd to tif sfrvfr. Rfdord
         * fxdfption bs wf nffd to rf-tirow it if tifrf isn't
         * b stbtus linf.
         */
        Exdfption fxd = null;
        try {
            gftInputStrfbm();
        } dbtdi (Exdfption f) {
            fxd = f;
        }

        /*
         * If wf dbn't b stbtus-linf tifn rf-tirow bny fxdfption
         * tibt gftInputStrfbm tirfw.
         */
        String stbtusLinf = gftHfbdfrFifld(0);
        if (stbtusLinf == null) {
            if (fxd != null) {
                if (fxd instbndfof RuntimfExdfption)
                    tirow (RuntimfExdfption)fxd;
                flsf
                    tirow (IOExdfption)fxd;
            }
            rfturn -1;
        }

        /*
         * Exbminf tif stbtus-linf - siould bf formbttfd bs pfr
         * sfdtion 6.1 of RFC 2616 :-
         *
         * Stbtus-Linf = HTTP-Vfrsion SP Stbtus-Codf SP Rfbson-Pirbsf
         *
         * If stbtus linf dbn't bf pbrsfd rfturn -1.
         */
        if (stbtusLinf.stbrtsWiti("HTTP/1.")) {
            int dodfPos = stbtusLinf.indfxOf(' ');
            if (dodfPos > 0) {

                int pirbsfPos = stbtusLinf.indfxOf(' ', dodfPos+1);
                if (pirbsfPos > 0 && pirbsfPos < stbtusLinf.lfngti()) {
                    rfsponsfMfssbgf = stbtusLinf.substring(pirbsfPos+1);
                }

                // dfvibtion from RFC 2616 - don't rfjfdt stbtus linf
                // if SP Rfbson-Pirbsf is not indludfd.
                if (pirbsfPos < 0)
                    pirbsfPos = stbtusLinf.lfngti();

                try {
                    rfsponsfCodf = Intfgfr.pbrsfInt
                            (stbtusLinf.substring(dodfPos+1, pirbsfPos));
                    rfturn rfsponsfCodf;
                } dbtdi (NumbfrFormbtExdfption f) { }
            }
        }
        rfturn -1;
    }

    /**
     * Gfts tif HTTP rfsponsf mfssbgf, if bny, rfturnfd blong witi tif
     * rfsponsf dodf from b sfrvfr.  From rfsponsfs likf:
     * <PRE>
     * HTTP/1.0 200 OK
     * HTTP/1.0 404 Not Found
     * </PRE>
     * Extrbdts tif Strings "OK" bnd "Not Found" rfspfdtivfly.
     * Rfturns null if nonf dould bf disdfrnfd from tif rfsponsfs
     * (tif rfsult wbs not vblid HTTP).
     * @tirows IOExdfption if bn frror oddurrfd donnfdting to tif sfrvfr.
     * @rfturn tif HTTP rfsponsf mfssbgf, or {@dodf null}
     */
    publid String gftRfsponsfMfssbgf() tirows IOExdfption {
        gftRfsponsfCodf();
        rfturn rfsponsfMfssbgf;
    }

    @SupprfssWbrnings("dfprfdbtion")
    publid long gftHfbdfrFifldDbtf(String nbmf, long Dffbult) {
        String dbtfString = gftHfbdfrFifld(nbmf);
        try {
            if (dbtfString.indfxOf("GMT") == -1) {
                dbtfString = dbtfString+" GMT";
            }
            rfturn Dbtf.pbrsf(dbtfString);
        } dbtdi (Exdfption f) {
        }
        rfturn Dffbult;
    }


    /**
     * Indidbtfs tibt otifr rfqufsts to tif sfrvfr
     * brf unlikfly in tif nfbr futurf. Cblling disdonnfdt()
     * siould not imply tibt tiis HttpURLConnfdtion
     * instbndf dbn bf rfusfd for otifr rfqufsts.
     */
    publid bbstrbdt void disdonnfdt();

    /**
     * Indidbtfs if tif donnfdtion is going tirougi b proxy.
     * @rfturn b boolfbn indidbting if tif donnfdtion is
     * using b proxy.
     */
    publid bbstrbdt boolfbn usingProxy();

    /**
     * Rfturns b {@link SodkftPfrmission} objfdt rfprfsfnting tif
     * pfrmission nfdfssbry to donnfdt to tif dfstinbtion iost bnd port.
     *
     * @fxdfption IOExdfption if bn frror oddurs wiilf domputing
     *            tif pfrmission.
     *
     * @rfturn b {@dodf SodkftPfrmission} objfdt rfprfsfnting tif
     *         pfrmission nfdfssbry to donnfdt to tif dfstinbtion
     *         iost bnd port.
     */
    publid Pfrmission gftPfrmission() tirows IOExdfption {
        int port = url.gftPort();
        port = port < 0 ? 80 : port;
        String iost = url.gftHost() + ":" + port;
        Pfrmission pfrmission = nfw SodkftPfrmission(iost, "donnfdt");
        rfturn pfrmission;
    }

   /**
    * Rfturns tif frror strfbm if tif donnfdtion fbilfd
    * but tif sfrvfr sfnt usfful dbtb nonftiflfss. Tif
    * typidbl fxbmplf is wifn bn HTTP sfrvfr rfsponds
    * witi b 404, wiidi will dbusf b FilfNotFoundExdfption
    * to bf tirown in donnfdt, but tif sfrvfr sfnt bn HTML
    * iflp pbgf witi suggfstions bs to wibt to do.
    *
    * <p>Tiis mftiod will not dbusf b donnfdtion to bf initibtfd.  If
    * tif donnfdtion wbs not donnfdtfd, or if tif sfrvfr did not ibvf
    * bn frror wiilf donnfdting or if tif sfrvfr ibd bn frror but
    * no frror dbtb wbs sfnt, tiis mftiod will rfturn null. Tiis is
    * tif dffbult.
    *
    * @rfturn bn frror strfbm if bny, null if tifrf ibvf bffn no
    * frrors, tif donnfdtion is not donnfdtfd or tif sfrvfr sfnt no
    * usfful dbtb.
    */
    publid InputStrfbm gftErrorStrfbm() {
        rfturn null;
    }

    /**
     * Tif rfsponsf dodfs for HTTP, bs of vfrsion 1.1.
     */

    // REMIND: do wf wbnt bll tifsf??
    // Otifrs not ifrf tibt wf do wbnt??

    /* 2XX: gfnfrblly "OK" */

    /**
     * HTTP Stbtus-Codf 200: OK.
     */
    publid stbtid finbl int HTTP_OK = 200;

    /**
     * HTTP Stbtus-Codf 201: Crfbtfd.
     */
    publid stbtid finbl int HTTP_CREATED = 201;

    /**
     * HTTP Stbtus-Codf 202: Addfptfd.
     */
    publid stbtid finbl int HTTP_ACCEPTED = 202;

    /**
     * HTTP Stbtus-Codf 203: Non-Autioritbtivf Informbtion.
     */
    publid stbtid finbl int HTTP_NOT_AUTHORITATIVE = 203;

    /**
     * HTTP Stbtus-Codf 204: No Contfnt.
     */
    publid stbtid finbl int HTTP_NO_CONTENT = 204;

    /**
     * HTTP Stbtus-Codf 205: Rfsft Contfnt.
     */
    publid stbtid finbl int HTTP_RESET = 205;

    /**
     * HTTP Stbtus-Codf 206: Pbrtibl Contfnt.
     */
    publid stbtid finbl int HTTP_PARTIAL = 206;

    /* 3XX: rflodbtion/rfdirfdt */

    /**
     * HTTP Stbtus-Codf 300: Multiplf Cioidfs.
     */
    publid stbtid finbl int HTTP_MULT_CHOICE = 300;

    /**
     * HTTP Stbtus-Codf 301: Movfd Pfrmbnfntly.
     */
    publid stbtid finbl int HTTP_MOVED_PERM = 301;

    /**
     * HTTP Stbtus-Codf 302: Tfmporbry Rfdirfdt.
     */
    publid stbtid finbl int HTTP_MOVED_TEMP = 302;

    /**
     * HTTP Stbtus-Codf 303: Sff Otifr.
     */
    publid stbtid finbl int HTTP_SEE_OTHER = 303;

    /**
     * HTTP Stbtus-Codf 304: Not Modififd.
     */
    publid stbtid finbl int HTTP_NOT_MODIFIED = 304;

    /**
     * HTTP Stbtus-Codf 305: Usf Proxy.
     */
    publid stbtid finbl int HTTP_USE_PROXY = 305;

    /* 4XX: dlifnt frror */

    /**
     * HTTP Stbtus-Codf 400: Bbd Rfqufst.
     */
    publid stbtid finbl int HTTP_BAD_REQUEST = 400;

    /**
     * HTTP Stbtus-Codf 401: Unbutiorizfd.
     */
    publid stbtid finbl int HTTP_UNAUTHORIZED = 401;

    /**
     * HTTP Stbtus-Codf 402: Pbymfnt Rfquirfd.
     */
    publid stbtid finbl int HTTP_PAYMENT_REQUIRED = 402;

    /**
     * HTTP Stbtus-Codf 403: Forbiddfn.
     */
    publid stbtid finbl int HTTP_FORBIDDEN = 403;

    /**
     * HTTP Stbtus-Codf 404: Not Found.
     */
    publid stbtid finbl int HTTP_NOT_FOUND = 404;

    /**
     * HTTP Stbtus-Codf 405: Mftiod Not Allowfd.
     */
    publid stbtid finbl int HTTP_BAD_METHOD = 405;

    /**
     * HTTP Stbtus-Codf 406: Not Addfptbblf.
     */
    publid stbtid finbl int HTTP_NOT_ACCEPTABLE = 406;

    /**
     * HTTP Stbtus-Codf 407: Proxy Autifntidbtion Rfquirfd.
     */
    publid stbtid finbl int HTTP_PROXY_AUTH = 407;

    /**
     * HTTP Stbtus-Codf 408: Rfqufst Timf-Out.
     */
    publid stbtid finbl int HTTP_CLIENT_TIMEOUT = 408;

    /**
     * HTTP Stbtus-Codf 409: Conflidt.
     */
    publid stbtid finbl int HTTP_CONFLICT = 409;

    /**
     * HTTP Stbtus-Codf 410: Gonf.
     */
    publid stbtid finbl int HTTP_GONE = 410;

    /**
     * HTTP Stbtus-Codf 411: Lfngti Rfquirfd.
     */
    publid stbtid finbl int HTTP_LENGTH_REQUIRED = 411;

    /**
     * HTTP Stbtus-Codf 412: Prfdondition Fbilfd.
     */
    publid stbtid finbl int HTTP_PRECON_FAILED = 412;

    /**
     * HTTP Stbtus-Codf 413: Rfqufst Entity Too Lbrgf.
     */
    publid stbtid finbl int HTTP_ENTITY_TOO_LARGE = 413;

    /**
     * HTTP Stbtus-Codf 414: Rfqufst-URI Too Lbrgf.
     */
    publid stbtid finbl int HTTP_REQ_TOO_LONG = 414;

    /**
     * HTTP Stbtus-Codf 415: Unsupportfd Mfdib Typf.
     */
    publid stbtid finbl int HTTP_UNSUPPORTED_TYPE = 415;

    /* 5XX: sfrvfr frror */

    /**
     * HTTP Stbtus-Codf 500: Intfrnbl Sfrvfr Error.
     * @dfprfdbtfd   it is misplbdfd bnd siouldn't ibvf fxistfd.
     */
    @Dfprfdbtfd
    publid stbtid finbl int HTTP_SERVER_ERROR = 500;

    /**
     * HTTP Stbtus-Codf 500: Intfrnbl Sfrvfr Error.
     */
    publid stbtid finbl int HTTP_INTERNAL_ERROR = 500;

    /**
     * HTTP Stbtus-Codf 501: Not Implfmfntfd.
     */
    publid stbtid finbl int HTTP_NOT_IMPLEMENTED = 501;

    /**
     * HTTP Stbtus-Codf 502: Bbd Gbtfwby.
     */
    publid stbtid finbl int HTTP_BAD_GATEWAY = 502;

    /**
     * HTTP Stbtus-Codf 503: Sfrvidf Unbvbilbblf.
     */
    publid stbtid finbl int HTTP_UNAVAILABLE = 503;

    /**
     * HTTP Stbtus-Codf 504: Gbtfwby Timfout.
     */
    publid stbtid finbl int HTTP_GATEWAY_TIMEOUT = 504;

    /**
     * HTTP Stbtus-Codf 505: HTTP Vfrsion Not Supportfd.
     */
    publid stbtid finbl int HTTP_VERSION = 505;

}
