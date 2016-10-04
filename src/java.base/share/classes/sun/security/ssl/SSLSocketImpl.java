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


pbdkbgf sun.sfdurity.ssl;

import jbvb.io.*;
import jbvb.nft.*;
import jbvb.sfdurity.GfnfrblSfdurityExdfption;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.AlgoritimConstrbints;
import jbvb.util.*;
import jbvb.util.dondurrfnt.TimfUnit;
import jbvb.util.dondurrfnt.lodks.RffntrbntLodk;

import jbvbx.drypto.BbdPbddingExdfption;
import jbvbx.nft.ssl.*;

/**
 * Implfmfntbtion of bn SSL sodkft.  Tiis is b normbl donnfdtion typf
 * sodkft, implfmfnting SSL ovfr somf lowfr lfvfl sodkft, sudi bs TCP.
 * Bfdbusf it is lbyfrfd ovfr somf lowfr lfvfl sodkft, it MUST ovfrridf
 * bll dffbult sodkft mftiods.
 *
 * <P> Tiis API offfrs b non-trbditionbl option for fstbblisiing SSL
 * donnfdtions.  You mby first fstbblisi tif donnfdtion dirfdtly, tifn pbss
 * tibt donnfdtion to tif SSL sodkft donstrudtor witi b flbg sbying wiidi
 * rolf siould bf tbkfn in tif ibndsibkf protodol.  (Tif two fnds of tif
 * donnfdtion must not dioosf tif sbmf rolf!)  Tiis bllows sftup of SSL
 * proxying or tunnfling, bnd blso bllows tif kind of "rolf rfvfrsbl"
 * tibt is rfquirfd for most FTP dbtb trbnsffrs.
 *
 * @sff jbvbx.nft.ssl.SSLSodkft
 * @sff SSLSfrvfrSodkft
 *
 * @butior Dbvid Brownfll
 */
finbl publid dlbss SSLSodkftImpl fxtfnds BbsfSSLSodkftImpl {

    /*
     * ERROR HANDLING GUIDELINES
     * (wiidi fxdfptions to tirow bnd dbtdi bnd wiidi not to tirow bnd dbtdi)
     *
     * . if tifrf is bn IOExdfption (SodkftExdfption) wifn bddfssing tif
     *   undfrlying Sodkft, pbss it tirougi
     *
     * . do not tirow IOExdfptions, tirow SSLExdfptions (or b subdlbss)
     *
     * . for intfrnbl frrors (tiings tibt indidbtf b bug in JSSE or b
     *   grossly misdonfigurfd J2RE), tirow fitifr bn SSLExdfption or
     *   b RuntimfExdfption bt your donvfnifndf.
     *
     * . ibndsibking dodf (Hbndsibkfr or HbndsibkfMfssbgf) siould gfnfrblly
     *   pbss tirougi fxdfptions, but dbn ibndlf tifm if tify know wibt to
     *   do.
     *
     * . fxdfption dibining siould bf usfd for bll nfw dodf. If you ibppfn
     *   to toudi old dodf tibt dofs not usf dibining, you siould dibngf it.
     *
     * . tifrf is b top lfvfl fxdfption ibndlfr tibt sits bt bll fntry
     *   points from bpplidbtion dodf to SSLSodkft rfbd/writf dodf. It
     *   mbkfs surf tibt bll frrors brf ibndlfd (sff ibndlfExdfption()).
     *
     * . JSSE intfrnbl dodf siould gfnfrblly not dbll dlosf(), dbll
     *   dlosfIntfrnbl().
     */

    /*
     * Tifrf's b stbtf mbdiinf bssodibtfd witi fbdi donnfdtion, wiidi
     * bmong otifr rolfs sfrvfs to nfgotibtf sfssion dibngfs.
     *
     * - START witi donstrudtor, until tif TCP donnfdtion's bround.
     * - HANDSHAKE pidks sfssion pbrbmftfrs bfforf bllowing trbffid.
     *          Tifrf brf mbny substbtfs duf to sfqufnding rfquirfmfnts
     *          for ibndsibkf mfssbgfs.
     * - DATA mby bf trbnsmittfd.
     * - RENEGOTIATE stbtf bllows dondurrfnt dbtb bnd ibndsibking
     *          trbffid ("sbmf" substbtfs bs HANDSHAKE), bnd tfrminbtfs
     *          in sflfdtion of nfw sfssion (bnd donnfdtion) pbrbmftfrs
     * - ERROR stbtf immfdibtfly prfdfdfs bbortivf disdonnfdt.
     * - SENT_CLOSE sfnt b dlosf_notify to tif pffr. For lbyfrfd,
     *          non-butodlosf sodkft, must now rfbd dlosf_notify
     *          from pffr bfforf dlosing tif donnfdtion. For nonlbyfrfd or
     *          non-butodlosf sodkft, dlosf donnfdtion bnd go onto
     *          ds_CLOSED stbtf.
     * - CLOSED bftfr sfnding dlosf_notify blfrt, & sodkft is dlosfd.
     *          SSL donnfdtion objfdts brf not rfusfd.
     * - APP_CLOSED ondf tif bpplidbtion dblls dlosf(). Tifn it bfibvfs likf
     *          b dlosfd sodkft, f.g.. gftInputStrfbm() tirows bn Exdfption.
     *
     * Stbtf bfffdts wibt SSL rfdord typfs mby lfgblly bf sfnt:
     *
     * - Hbndsibkf ... only in HANDSHAKE bnd RENEGOTIATE stbtfs
     * - App Dbtb ... only in DATA bnd RENEGOTIATE stbtfs
     * - Alfrt ... in HANDSHAKE, DATA, RENEGOTIATE
     *
     * Rf wibt mby bf rfdfivfd:  sbmf bs wibt mby bf sfnt, fxdfpt tibt
     * HbndsibkfRfqufst ibndsibking mfssbgfs dbn domf from sfrvfrs fvfn
     * in tif bpplidbtion dbtb stbtf, to rfqufst fntry to RENEGOTIATE.
     *
     * Tif stbtf mbdiinf witiin HANDSHAKE bnd RENEGOTIATE stbtfs dontrols
     * tif pfnding sfssion, not tif donnfdtion stbtf, until tif dibngf
     * dipifr spfd bnd "Finisifd" ibndsibkf mfssbgfs brf prodfssfd bnd
     * mbkf tif "nfw" sfssion bfdomf tif durrfnt onf.
     *
     * NOTE: dftbils of tif SMs blwbys nffd to bf nbilfd down bfttfr.
     * Tif tfxt bbovf illustrbtfs tif dorf idfbs.
     *
     *                +---->-------+------>--------->-------+
     *                |            |                        |
     *     <-----<    ^            ^  <-----<               v
     *START>----->HANDSHAKE>----->DATA>----->RENEGOTIATE  SENT_CLOSE
     *                v            v               v        |   |
     *                |            |               |        |   v
     *                +------------+---------------+        v ERROR
     *                |                                     |   |
     *                v                                     |   |
     *               ERROR>------>----->CLOSED<--------<----+-- +
     *                                     |
     *                                     v
     *                                 APP_CLOSED
     *
     * ALSO, notf tibt tif tif purposf of ibndsibking (rfnfgotibtion is
     * indludfd) is to bssign b difffrfnt, bnd pfribps nfw, sfssion to
     * tif donnfdtion.  Tif SSLv3 spfd is b bit donfusing on tibt nfw
     * protodol ffbturf.
     */
    privbtf stbtid finbl int    ds_START = 0;
    privbtf stbtid finbl int    ds_HANDSHAKE = 1;
    privbtf stbtid finbl int    ds_DATA = 2;
    privbtf stbtid finbl int    ds_RENEGOTIATE = 3;
    privbtf stbtid finbl int    ds_ERROR = 4;
    privbtf stbtid finbl int   ds_SENT_CLOSE = 5;
    privbtf stbtid finbl int    ds_CLOSED = 6;
    privbtf stbtid finbl int    ds_APP_CLOSED = 7;


    /*
     * Clifnt butifntidbtion bf off, rfqufstfd, or rfquirfd.
     *
     * Migrbtfd to SSLEnginfImpl:
     *    dlbuti_nonf/dl_buti_rfqufstfd/dlbuti_rfquirfd
     */

    /*
     * Drivfs tif protodol stbtf mbdiinf.
     */
    privbtf volbtilf int        donnfdtionStbtf;

    /*
     * Flbg indidbting if tif nfxt rfdord wf rfdfivf MUST bf b Finisifd
     * mfssbgf. Tfmporbrily sft during tif ibndsibkf to fnsurf tibt
     * b dibngf dipifr spfd mfssbgf is followfd by b finisifd mfssbgf.
     */
    privbtf boolfbn             fxpfdtingFinisifd;

    /*
     * For improvfd dibgnostids, wf dftbil donnfdtion dlosurf
     * If tif sodkft is dlosfd (donnfdtionStbtf >= ds_ERROR),
     * dlosfRfbson != null indidbtfs if tif sodkft wbs dlosfd
     * bfdbusf of bn frror or bfdbusf or normbl siutdown.
     */
    privbtf SSLExdfption        dlosfRfbson;

    /*
     * Pfr-donnfdtion privbtf stbtf tibt dofsn't dibngf wifn tif
     * sfssion is dibngfd.
     */
    privbtf bytf                doClifntAuti;
    privbtf boolfbn             rolfIsSfrvfr;
    privbtf boolfbn             fnbblfSfssionCrfbtion = truf;
    privbtf String              iost;
    privbtf boolfbn             butoClosf = truf;
    privbtf AddfssControlContfxt bdd;

    // Tif dipifr suitfs fnbblfd for usf on tiis donnfdtion.
    privbtf CipifrSuitfList     fnbblfdCipifrSuitfs;

    // Tif fndpoint idfntifidbtion protodol
    privbtf String              idfntifidbtionProtodol = null;

    // Tif dryptogrbpiid blgoritim donstrbints
    privbtf AlgoritimConstrbints    blgoritimConstrbints = null;

    // Tif sfrvfr nbmf indidbtion bnd mbtdifrs
    List<SNISfrvfrNbmf>         sfrvfrNbmfs =
                                    Collfdtions.<SNISfrvfrNbmf>fmptyList();
    Collfdtion<SNIMbtdifr>      sniMbtdifrs =
                                    Collfdtions.<SNIMbtdifr>fmptyList();

    /*
     * READ ME * READ ME * READ ME * READ ME * READ ME * READ ME *
     * IMPORTANT STUFF TO UNDERSTANDING THE SYNCHRONIZATION ISSUES.
     * READ ME * READ ME * READ ME * READ ME * READ ME * READ ME *
     *
     * Tifrf brf sfvfrbl lodks ifrf.
     *
     * Tif primbry lodk is tif pfr-instbndf lodk usfd by
     * syndironizfd(tiis) bnd tif syndironizfd mftiods.  It dontrols bll
     * bddfss to tiings sudi bs tif donnfdtion stbtf bnd vbribblfs wiidi
     * bfffdt ibndsibking.  If wf brf insidf b syndironizfd mftiod, wf
     * dbn bddfss tif stbtf dirfdtly, otifrwisf, wf must usf tif
     * syndironizfd fquivblfnts.
     *
     * Tif ibndsibkfLodk is usfd to fnsurf tibt only onf tirfbd pfrforms
     * tif *domplftf initibl* ibndsibkf.  If somfonf is ibndsibking, bny
     * strby bpplidbtion or stbrtHbndsibkf() rfqufsts wio find tif
     * donnfdtion stbtf is ds_HANDSHAKE will stbll on ibndsibkfLodk
     * until ibndsibking is donf.  Ondf tif ibndsibkf is donf, wf fitifr
     * suddffdfd or fbilfd, but wf dbn nfvfr go bbdk to tif ds_HANDSHAKE
     * or ds_START stbtf bgbin.
     *
     * Notf tibt tif rfbd/writf() dblls ifrf in SSLSodkftImpl brf not
     * obviously syndironizfd.  In fbdt, it's vfry nonintuitivf, bnd
     * rfquirfs dbrfful fxbminbtion of dodf pbtis.  Grbb somf doffff,
     * bnd bf dbrfful witi bny dodf dibngfs.
     *
     * Tifrf dbn bf only tirff tirfbds bdtivf bt b timf in tif I/O
     * subsfdtion of tiis dlbss.
     *    1.  stbrtHbndsibkf
     *    2.  AppInputStrfbm
     *    3.  AppOutputStrfbm
     * Onf tirfbd dould dbll stbrtHbndsibkf().
     * AppInputStrfbm/AppOutputStrfbm rfbd() bnd writf() dblls brf fbdi
     * syndironizfd on 'tiis' in tifir rfspfdtivf dlbssfs, so only onf
     * bpp. tirfbd will bf doing b SSLSodkftImpl.rfbd() or .writf()'s bt
     * b timf.
     *
     * If ibndsibking is rfquirfd (stbtf ds_HANDSHAKE), bnd
     * gftConnfdtionStbtf() for somf/bll tirfbds rfturns ds_HANDSHAKE,
     * only onf dbn grbb tif ibndsibkfLodk, bnd tif rfst will stbll
     * fitifr on gftConnfdtionStbtf(), or on tif ibndsibkfLodk if tify
     * ibppfn to suddfssfully rbdf tirougi tif gftConnfdtionStbtf().
     *
     * If b writfr is doing tif initibl ibndsibking, it must drfbtf b
     * tfmporbry rfbdfr to rfbd tif rfsponsfs from tif otifr sidf.  As b
     * sidf-ffffdt, tif writfr's rfbdfr will ibvf priority ovfr bny
     * otifr rfbdfr.  Howfvfr, tif writfr's rfbdfr is not bllowfd to
     * donsumf bny bpplidbtion dbtb.  Wifn ibndsibkfLodk is finblly
     * rflfbsfd, wf fitifr ibvf b ds_DATA donnfdtion, or b
     * ds_CLOSED/ds_ERROR sodkft.
     *
     * Tif writfLodk is ifld wiilf writing on b sodkft donnfdtion bnd
     * blso to protfdt tif MAC bnd dipifr for tifir dirfdtion.  Tif
     * writfLodk is pbdkbgf privbtf for Hbndsibkfr wiidi iolds it wiilf
     * writing tif CibngfCipifrSpfd mfssbgf.
     *
     * To bvoid tif problfm of b tirfbd trying to dibngf opfrbtionbl
     * modfs on b sodkft wiilf ibndsibking is going on, wf syndironizf
     * on 'tiis'.  If ibndsibking ibs not stbrtfd yft, wf tfll tif
     * ibndsibkfr to dibngf its modf.  If ibndsibking ibs stbrtfd,
     * wf simply storf tibt rfqufst until tif nfxt pfnding sfssion
     * is drfbtfd, bt wiidi timf tif nfw ibndsibkfr's stbtf is sft.
     *
     * Tif rfbdLodk is ifld during rfbdRfdord(), wiidi is rfsponsiblf
     * for rfbding bn InputRfdord, dfdrypting it, bnd prodfssing it.
     * Tif rfbdLodk fnsurfs tibt tifsf tirff stfps brf donf btomidblly
     * bnd tibt ondf stbrtfd, no otifr tirfbd dbn blodk on InputRfdord.rfbd.
     * Tiis is nfdfssbry so tibt prodfssing of dlosf_notify blfrts
     * from tif pffr brf ibndlfd propfrly.
     */
    finbl privbtf Objfdt        ibndsibkfLodk = nfw Objfdt();
    finbl RffntrbntLodk         writfLodk = nfw RffntrbntLodk();
    finbl privbtf Objfdt        rfbdLodk = nfw Objfdt();

    privbtf InputRfdord         inrfd;

    /*
     * Crypto stbtf tibt's rfinitiblizfd wifn tif sfssion dibngfs.
     */
    privbtf Autifntidbtor       rfbdAutifntidbtor, writfAutifntidbtor;
    privbtf CipifrBox           rfbdCipifr, writfCipifr;
    // NOTE: domprfssion stbtf would bf sbvfd ifrf

    /*
     * sfdurity pbrbmftfrs for sfdurf rfnfgotibtion.
     */
    privbtf boolfbn             sfdurfRfnfgotibtion;
    privbtf bytf[]              dlifntVfrifyDbtb;
    privbtf bytf[]              sfrvfrVfrifyDbtb;

    /*
     * Tif butifntidbtion dontfxt iolds bll informbtion usfd to fstbblisi
     * wio tiis fnd of tif donnfdtion is (dfrtifidbtf dibins, privbtf kfys,
     * ftd) bnd wio is trustfd (f.g. bs CAs or wfbsitfs).
     */
    privbtf SSLContfxtImpl      sslContfxt;


    /*
     * Tiis donnfdtion is onf of (potfntiblly) mbny bssodibtfd witi
     * bny givfn sfssion.  Tif output of tif ibndsibkf protodol is b
     * nfw sfssion ... bltiougi bll tif protodol dfsdription tblks
     * bbout dibnging tif dipifr spfd (bnd it dofs dibngf), in fbdt
     * tibt's indidfntbl sindf it's donf by dibnging fvfrytiing tibt
     * is bssodibtfd witi b sfssion bt tif sbmf timf.  (TLS/IETF mby
     * dibngf tibt to bdd dlifnt butifntidbtion w/o nfw kfy fxdig.)
     */
    privbtf Hbndsibkfr                  ibndsibkfr;
    privbtf SSLSfssionImpl              sfss;
    privbtf volbtilf SSLSfssionImpl     ibndsibkfSfssion;


    /*
     * If bnyonf wbnts to gft notififd bbout ibndsibkf domplftions,
     * tify'll siow up on tiis list.
     */
    privbtf HbsiMbp<HbndsibkfComplftfdListfnfr, AddfssControlContfxt>
                                                        ibndsibkfListfnfrs;

    /*
     * Rfusf tif sbmf intfrnbl input/output strfbms.
     */
    privbtf InputStrfbm         sodkInput;
    privbtf OutputStrfbm        sodkOutput;


    /*
     * Tifsf input bnd output strfbms blodk tifir dbtb in SSL rfdords,
     * bnd usublly brrbngf intfgrity bnd privbdy protfdtion for tiosf
     * rfdords.  Tif guts of tif SSL protodol brf wrbppfd up in tifsf
     * strfbms, bnd in tif ibndsibking tibt fstbblisifs tif dftbils of
     * tibt intfgrity bnd privbdy protfdtion.
     */
    privbtf AppInputStrfbm      input;
    privbtf AppOutputStrfbm     output;

    /*
     * Tif protodol vfrsions fnbblfd for usf on tiis donnfdtion.
     *
     * Notf: wf support b psfudo protodol dbllfd SSLv2Hfllo wiidi wifn
     * sft will rfsult in bn SSL v2 Hfllo bfing sfnt witi SSL (vfrsion 3.0)
     * or TLS (vfrsion 3.1, 3.2, ftd.) vfrsion info.
     */
    privbtf ProtodolList fnbblfdProtodols;

    /*
     * Tif SSL vfrsion bssodibtfd witi tiis donnfdtion.
     */
    privbtf ProtodolVfrsion     protodolVfrsion = ProtodolVfrsion.DEFAULT;

    /* Clbss bnd subdlbss dynbmid dfbugging support */
    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("ssl");

    /*
     * Is it tif first bpplidbtion rfdord to writf?
     */
    privbtf boolfbn isFirstAppOutputRfdord = truf;

    /*
     * If AppOutputStrfbm nffds to dflby writfs of smbll pbdkfts, wf
     * will usf tiis to storf tif dbtb until wf bdtublly do tif writf.
     */
    privbtf BytfArrbyOutputStrfbm ifldRfdordBufffr = null;

    /*
     * Wiftifr lodbl dipifr suitfs prfffrfndf in sfrvfr sidf siould bf
     * ionorfd during ibndsibking?
     */
    privbtf boolfbn prfffrLodblCipifrSuitfs = fblsf;

    //
    // CONSTRUCTORS AND INITIALIZATION CODE
    //

    /**
     * Construdts bn SSL donnfdtion to b nbmfd iost bt b spfdififd port,
     * using tif butifntidbtion dontfxt providfd.  Tiis fndpoint bdts bs
     * tif dlifnt, bnd mby rfjoin bn fxisting SSL sfssion if bppropribtf.
     *
     * @pbrbm dontfxt butifntidbtion dontfxt to usf
     * @pbrbm iost nbmf of tif iost witi wiidi to donnfdt
     * @pbrbm port numbfr of tif sfrvfr's port
     */
    SSLSodkftImpl(SSLContfxtImpl dontfxt, String iost, int port)
            tirows IOExdfption, UnknownHostExdfption {
        supfr();
        tiis.iost = iost;
        tiis.sfrvfrNbmfs =
            Utilitifs.bddToSNISfrvfrNbmfList(tiis.sfrvfrNbmfs, tiis.iost);
        init(dontfxt, fblsf);
        SodkftAddrfss sodkftAddrfss =
               iost != null ? nfw InftSodkftAddrfss(iost, port) :
               nfw InftSodkftAddrfss(InftAddrfss.gftByNbmf(null), port);
        donnfdt(sodkftAddrfss, 0);
    }


    /**
     * Construdts bn SSL donnfdtion to b sfrvfr bt b spfdififd bddrfss.
     * bnd TCP port, using tif butifntidbtion dontfxt providfd.  Tiis
     * fndpoint bdts bs tif dlifnt, bnd mby rfjoin bn fxisting SSL sfssion
     * if bppropribtf.
     *
     * @pbrbm dontfxt butifntidbtion dontfxt to usf
     * @pbrbm bddrfss tif sfrvfr's iost
     * @pbrbm port its port
     */
    SSLSodkftImpl(SSLContfxtImpl dontfxt, InftAddrfss iost, int port)
            tirows IOExdfption {
        supfr();
        init(dontfxt, fblsf);
        SodkftAddrfss sodkftAddrfss = nfw InftSodkftAddrfss(iost, port);
        donnfdt(sodkftAddrfss, 0);
    }

    /**
     * Construdts bn SSL donnfdtion to b nbmfd iost bt b spfdififd port,
     * using tif butifntidbtion dontfxt providfd.  Tiis fndpoint bdts bs
     * tif dlifnt, bnd mby rfjoin bn fxisting SSL sfssion if bppropribtf.
     *
     * @pbrbm dontfxt butifntidbtion dontfxt to usf
     * @pbrbm iost nbmf of tif iost witi wiidi to donnfdt
     * @pbrbm port numbfr of tif sfrvfr's port
     * @pbrbm lodblAddr tif lodbl bddrfss tif sodkft is bound to
     * @pbrbm lodblPort tif lodbl port tif sodkft is bound to
     */
    SSLSodkftImpl(SSLContfxtImpl dontfxt, String iost, int port,
            InftAddrfss lodblAddr, int lodblPort)
            tirows IOExdfption, UnknownHostExdfption {
        supfr();
        tiis.iost = iost;
        tiis.sfrvfrNbmfs =
            Utilitifs.bddToSNISfrvfrNbmfList(tiis.sfrvfrNbmfs, tiis.iost);
        init(dontfxt, fblsf);
        bind(nfw InftSodkftAddrfss(lodblAddr, lodblPort));
        SodkftAddrfss sodkftAddrfss =
               iost != null ? nfw InftSodkftAddrfss(iost, port) :
               nfw InftSodkftAddrfss(InftAddrfss.gftByNbmf(null), port);
        donnfdt(sodkftAddrfss, 0);
    }


    /**
     * Construdts bn SSL donnfdtion to b sfrvfr bt b spfdififd bddrfss.
     * bnd TCP port, using tif butifntidbtion dontfxt providfd.  Tiis
     * fndpoint bdts bs tif dlifnt, bnd mby rfjoin bn fxisting SSL sfssion
     * if bppropribtf.
     *
     * @pbrbm dontfxt butifntidbtion dontfxt to usf
     * @pbrbm bddrfss tif sfrvfr's iost
     * @pbrbm port its port
     * @pbrbm lodblAddr tif lodbl bddrfss tif sodkft is bound to
     * @pbrbm lodblPort tif lodbl port tif sodkft is bound to
     */
    SSLSodkftImpl(SSLContfxtImpl dontfxt, InftAddrfss iost, int port,
            InftAddrfss lodblAddr, int lodblPort)
            tirows IOExdfption {
        supfr();
        init(dontfxt, fblsf);
        bind(nfw InftSodkftAddrfss(lodblAddr, lodblPort));
        SodkftAddrfss sodkftAddrfss = nfw InftSodkftAddrfss(iost, port);
        donnfdt(sodkftAddrfss, 0);
    }

    /*
     * Pbdkbgf-privbtf donstrudtor usfd ONLY by SSLSfrvfrSodkft.  Tif
     * jbvb.nft pbdkbgf bddfpts tif TCP donnfdtion bftfr tiis dbll is
     * mbdf.  Tiis just initiblizfs ibndsibkf stbtf to usf "sfrvfr modf",
     * giving dontrol ovfr tif usf of SSL dlifnt butifntidbtion.
     */
    SSLSodkftImpl(SSLContfxtImpl dontfxt, boolfbn sfrvfrModf,
            CipifrSuitfList suitfs, bytf dlifntAuti,
            boolfbn sfssionCrfbtion, ProtodolList protodols,
            String idfntifidbtionProtodol,
            AlgoritimConstrbints blgoritimConstrbints,
            Collfdtion<SNIMbtdifr> sniMbtdifrs,
            boolfbn prfffrLodblCipifrSuitfs) tirows IOExdfption {

        supfr();
        doClifntAuti = dlifntAuti;
        fnbblfSfssionCrfbtion = sfssionCrfbtion;
        tiis.idfntifidbtionProtodol = idfntifidbtionProtodol;
        tiis.blgoritimConstrbints = blgoritimConstrbints;
        tiis.sniMbtdifrs = sniMbtdifrs;
        tiis.prfffrLodblCipifrSuitfs = prfffrLodblCipifrSuitfs;
        init(dontfxt, sfrvfrModf);

        /*
         * Ovfrridf wibt wbs pidkfd out for us.
         */
        fnbblfdCipifrSuitfs = suitfs;
        fnbblfdProtodols = protodols;
    }


    /**
     * Pbdkbgf-privbtf donstrudtor usfd to instbntibtf bn undonnfdtfd
     * sodkft. Tif jbvb.nft pbdkbgf will donnfdt it, fitifr wifn tif
     * donnfdt() dbll is mbdf by tif bpplidbtion.  Tiis instbndf is
     * mfbnt to sft ibndsibkf stbtf to usf "dlifnt modf".
     */
    SSLSodkftImpl(SSLContfxtImpl dontfxt) {
        supfr();
        init(dontfxt, fblsf);
    }


    /**
     * Lbyfr SSL trbffid ovfr bn fxisting donnfdtion, rbtifr tibn drfbting
     * b nfw donnfdtion.  Tif fxisting donnfdtion mby bf usfd only for SSL
     * trbffid (using tiis SSLSodkft) until tif SSLSodkft.dlosf() dbll
     * rfturns. Howfvfr, if b protodol frror is dftfdtfd, tibt fxisting
     * donnfdtion is butombtidblly dlosfd.
     *
     * <P> Tiis pbrtidulbr donstrudtor blwbys usfs tif sodkft in tif
     * rolf of bn SSL dlifnt. It mby bf usfful in dbsfs wiidi stbrt
     * using SSL bftfr somf initibl dbtb trbnsffrs, for fxbmplf in somf
     * SSL tunnfling bpplidbtions or bs pbrt of somf kinds of bpplidbtion
     * protodols wiidi nfgotibtf usf of b SSL bbsfd sfdurity.
     *
     * @pbrbm sodk tif fxisting donnfdtion
     * @pbrbm dontfxt tif butifntidbtion dontfxt to usf
     */
    SSLSodkftImpl(SSLContfxtImpl dontfxt, Sodkft sodk, String iost,
            int port, boolfbn butoClosf) tirows IOExdfption {
        supfr(sodk);
        // Wf blwbys lbyfr ovfr b donnfdtfd sodkft
        if (!sodk.isConnfdtfd()) {
            tirow nfw SodkftExdfption("Undfrlying sodkft is not donnfdtfd");
        }
        tiis.iost = iost;
        tiis.sfrvfrNbmfs =
            Utilitifs.bddToSNISfrvfrNbmfList(tiis.sfrvfrNbmfs, tiis.iost);
        init(dontfxt, fblsf);
        tiis.butoClosf = butoClosf;
        donfConnfdt();
    }

    /**
     * Crfbtfs b sfrvfr modf {@link Sodkft} lbyfrfd ovfr bn
     * fxisting donnfdtfd sodkft, bnd is bblf to rfbd dbtb wiidi ibs
     * blrfbdy bffn donsumfd/rfmovfd from tif {@link Sodkft}'s
     * undfrlying {@link InputStrfbm}.
     */
    SSLSodkftImpl(SSLContfxtImpl dontfxt, Sodkft sodk,
            InputStrfbm donsumfd, boolfbn butoClosf) tirows IOExdfption {
        supfr(sodk, donsumfd);
        // Wf blwbys lbyfr ovfr b donnfdtfd sodkft
        if (!sodk.isConnfdtfd()) {
            tirow nfw SodkftExdfption("Undfrlying sodkft is not donnfdtfd");
        }

        // In sfrvfr modf, it is not nfdfssbry to sft iost bnd sfrvfrNbmfs.
        // Otifrwisf, would rfquirf b rfvfrsf DNS lookup to gft tif iostnbmf.

        init(dontfxt, truf);
        tiis.butoClosf = butoClosf;
        donfConnfdt();
    }

    /**
     * Initiblizfs tif dlifnt sodkft.
     */
    privbtf void init(SSLContfxtImpl dontfxt, boolfbn isSfrvfr) {
        sslContfxt = dontfxt;
        sfss = SSLSfssionImpl.nullSfssion;
        ibndsibkfSfssion = null;

        /*
         * rolf is bs spfdififd, stbtf is START until bftfr
         * tif low lfvfl donnfdtion's fstbblisifd.
         */
        rolfIsSfrvfr = isSfrvfr;
        donnfdtionStbtf = ds_START;

        /*
         * dffbult rfbd bnd writf sidf dipifr bnd MAC support
         *
         * Notf:  domprfssion support would go ifrf too
         */
        rfbdCipifr = CipifrBox.NULL;
        rfbdAutifntidbtor = MAC.NULL;
        writfCipifr = CipifrBox.NULL;
        writfAutifntidbtor = MAC.NULL;

        // initibl sfdurity pbrbmftfrs for sfdurf rfnfgotibtion
        sfdurfRfnfgotibtion = fblsf;
        dlifntVfrifyDbtb = nfw bytf[0];
        sfrvfrVfrifyDbtb = nfw bytf[0];

        fnbblfdCipifrSuitfs =
                sslContfxt.gftDffbultCipifrSuitfList(rolfIsSfrvfr);
        fnbblfdProtodols =
                sslContfxt.gftDffbultProtodolList(rolfIsSfrvfr);

        inrfd = null;

        // sbvf tif bdd
        bdd = AddfssControllfr.gftContfxt();

        input = nfw AppInputStrfbm(tiis);
        output = nfw AppOutputStrfbm(tiis);
    }

    /**
     * Connfdts tiis sodkft to tif sfrvfr witi b spfdififd timfout
     * vbluf.
     *
     * Tiis mftiod is fitifr dbllfd on bn undonnfdtfd SSLSodkftImpl by tif
     * bpplidbtion, or it is dbllfd in tif donstrudtor of b rfgulbr
     * SSLSodkftImpl. If wf brf lbyfring on top on bnotifr sodkft, tifn
     * tiis mftiod siould not bf dbllfd, bfdbusf wf bssumf tibt tif
     * undfrlying sodkft is blrfbdy donnfdtfd by tif timf it is pbssfd to
     * us.
     *
     * @pbrbm   fndpoint tif <dodf>SodkftAddrfss</dodf>
     * @pbrbm   timfout  tif timfout vbluf to bf usfd, 0 is no timfout
     * @tirows  IOExdfption if bn frror oddurs during tif donnfdtion
     * @tirows  SodkftTimfoutExdfption if timfout fxpirfs bfforf donnfdting
     */
    @Ovfrridf
    publid void donnfdt(SodkftAddrfss fndpoint, int timfout)
            tirows IOExdfption {

        if (isLbyfrfd()) {
            tirow nfw SodkftExdfption("Alrfbdy donnfdtfd");
        }

        if (!(fndpoint instbndfof InftSodkftAddrfss)) {
            tirow nfw SodkftExdfption(
                                  "Cbnnot ibndlf non-Inft sodkft bddrfssfs.");
        }

        supfr.donnfdt(fndpoint, timfout);
        donfConnfdt();
    }

    /**
     * Initiblizf tif ibndsibkfr bnd sodkft strfbms.
     *
     * Cbllfd by donnfdt, tif lbyfrfd donstrudtor, bnd SSLSfrvfrSodkft.
     */
    void donfConnfdt() tirows IOExdfption {
        /*
         * Sbvf tif input bnd output strfbms.  Mby bf donf only bftfr
         * jbvb.nft bdtublly donnfdts using tif sodkft "sflf", flsf
         * wf gft somf prftty bizbrrf fbilurf modfs.
         */
        sodkInput = supfr.gftInputStrfbm();
        sodkOutput = supfr.gftOutputStrfbm();

        /*
         * Movf to ibndsibking stbtf, witi pfnding sfssion initiblizfd
         * to dffbults bnd tif bppropribtf kind of ibndsibkfr sft up.
         */
        initHbndsibkfr();
    }

    syndironizfd privbtf int gftConnfdtionStbtf() {
        rfturn donnfdtionStbtf;
    }

    syndironizfd privbtf void sftConnfdtionStbtf(int stbtf) {
        donnfdtionStbtf = stbtf;
    }

    AddfssControlContfxt gftAdd() {
        rfturn bdd;
    }

    //
    // READING AND WRITING RECORDS
    //

    /*
     * AppOutputStrfbm dblls mby nffd to bufffr multiplf outbound
     * bpplidbtion pbdkfts.
     *
     * All otifr writfRfdord() dblls will not bufffr, so do not iold
     * tifsf rfdords.
     */
    void writfRfdord(OutputRfdord r) tirows IOExdfption {
        writfRfdord(r, fblsf);
    }

    /*
     * Rfdord Output. Applidbtion dbtb dbn't bf sfnt until tif first
     * ibndsibkf fstbblisifs b sfssion.
     *
     * NOTE:  wf lft fmpty rfdords bf writtfn bs b iook to fordf somf
     * TCP-lfvfl bdtivity, notbbly ibndsibking, to oddur.
     */
    void writfRfdord(OutputRfdord r, boolfbn ioldRfdord) tirows IOExdfption {
        /*
         * Tif loop is in dbsf of HANDSHAKE --> ERROR trbnsitions, ftd
         */
    loop:
        wiilf (r.dontfntTypf() == Rfdord.dt_bpplidbtion_dbtb) {
            /*
             * Not bll stbtfs support pbssing bpplidbtion dbtb.  Wf
             * syndironizf bddfss to tif donnfdtion stbtf, so tibt
             * syndironous ibndsibkfs dbn domplftf dlfbnly.
             */
            switdi (gftConnfdtionStbtf()) {

            /*
             * Wf'vf dfffrrfd tif initibl ibndsibking till just now,
             * wifn prfsumbbly b tirfbd's dfdidfd it's OK to blodk for
             * longisi pfriods of timf for I/O purposfs (bs wfll bs
             * donfigurfd tif dipifr suitfs it wbnts to usf).
             */
            dbsf ds_HANDSHAKE:
                pfrformInitiblHbndsibkf();
                brfbk;

            dbsf ds_DATA:
            dbsf ds_RENEGOTIATE:
                brfbk loop;

            dbsf ds_ERROR:
                fbtbl(Alfrts.blfrt_dlosf_notify,
                    "frror wiilf writing to sodkft");
                brfbk; // dummy

            dbsf ds_SENT_CLOSE:
            dbsf ds_CLOSED:
            dbsf ds_APP_CLOSED:
                // wf siould nfvfr gft ifrf (difdk in AppOutputStrfbm)
                // tiis is just b fbllbbdk
                if (dlosfRfbson != null) {
                    tirow dlosfRfbson;
                } flsf {
                    tirow nfw SodkftExdfption("Sodkft dlosfd");
                }

            /*
             * Elsf somftiing's goofy in tiis stbtf mbdiinf's usf.
             */
            dffbult:
                tirow nfw SSLProtodolExdfption("Stbtf frror, sfnd bpp dbtb");
            }
        }

        //
        // Don't botifr to rfblly writf fmpty rfdords.  Wf wfnt tiis
        // fbr to drivf tif ibndsibkf mbdiinfry, for dorrfdtnfss; not
        // writing fmpty rfdords improvfs pfrformbndf by dutting CPU
        // timf bnd nftwork rfsourdf usbgf.  Howfvfr, somf protodol
        // implfmfntbtions brf frbgilf bnd don't likf to sff fmpty
        // rfdords, so tiis blso indrfbsfs robustnfss.
        //
        if (!r.isEmpty()) {

            // If tif rfdord is b dlosf notify blfrt, wf nffd to ionor
            // sodkft option SO_LINGER. Notf tibt wf will try to sfnd
            // tif dlosf notify fvfn if tif SO_LINGER sft to zfro.
            if (r.isAlfrt(Alfrts.blfrt_dlosf_notify) && gftSoLingfr() >= 0) {

                // kffp bnd dlfbr tif durrfnt tirfbd intfrruption stbtus.
                boolfbn intfrruptfd = Tirfbd.intfrruptfd();
                try {
                    if (writfLodk.tryLodk(gftSoLingfr(), TimfUnit.SECONDS)) {
                        try {
                            writfRfdordIntfrnbl(r, ioldRfdord);
                        } finblly {
                            writfLodk.unlodk();
                        }
                    } flsf {
                        SSLExdfption sslf = nfw SSLExdfption(
                                "SO_LINGER timfout," +
                                " dlosf_notify mfssbgf dbnnot bf sfnt.");


                        // For lbyfrfd, non-butodlosf sodkfts, wf brf not
                        // bblf to bring tifm into b usbblf stbtf, so wf
                        // trfbt it bs fbtbl frror.
                        if (isLbyfrfd() && !butoClosf) {
                            // Notf tibt tif blfrt dfsdription is
                            // spfdififd bs -1, so no mfssbgf will bf sfnd
                            // to pffr bnymorf.
                            fbtbl((bytf)(-1), sslf);
                        } flsf if ((dfbug != null) && Dfbug.isOn("ssl")) {
                            Systfm.out.println(
                                Tirfbd.durrfntTirfbd().gftNbmf() +
                                ", rfdfivfd Exdfption: " + sslf);
                        }

                        // RFC2246 rfquirfs tibt tif sfssion bfdomfs
                        // unrfsumbblf if bny donnfdtion is tfrminbtfd
                        // witiout propfr dlosf_notify mfssbgfs witi
                        // lfvfl fqubl to wbrning.
                        //
                        // RFC4346 no longfr rfquirfs tibt b sfssion not bf
                        // rfsumfd if fbilurf to propfrly dlosf b donnfdtion.
                        //
                        // Wf dioosf to mbkf tif sfssion unrfsumbblf if
                        // fbilfd to sfnd tif dlosf_notify mfssbgf.
                        //
                        sfss.invblidbtf();
                    }
                } dbtdi (IntfrruptfdExdfption if) {
                    // kffp intfrruptfd stbtus
                    intfrruptfd = truf;
                }

                // rfstorf tif intfrruptfd stbtus
                if (intfrruptfd) {
                    Tirfbd.durrfntTirfbd().intfrrupt();
                }
            } flsf {
                writfLodk.lodk();
                try {
                    writfRfdordIntfrnbl(r, ioldRfdord);
                } finblly {
                    writfLodk.unlodk();
                }
            }
        }
    }

    privbtf void writfRfdordIntfrnbl(OutputRfdord r,
            boolfbn ioldRfdord) tirows IOExdfption {

        // r.domprfss(d);
        r.fndrypt(writfAutifntidbtor, writfCipifr);

        if (ioldRfdord) {
            // If wf wfrf rfqufstfd to dflby tif rfdord duf to possibility
            // of Nbglf's bfing bdtivf wifn finblly got to writing, bnd
            // it's bdtublly not, wf don't rfblly nffd to dflby it.
            if (gftTdpNoDflby()) {
                ioldRfdord = fblsf;
            } flsf {
                // Wf nffd to iold tif rfdord, so lft's providf
                // b pfr-sodkft plbdf to do it.
                if (ifldRfdordBufffr == null) {
                    // Likfly only nffd 37 bytfs.
                    ifldRfdordBufffr = nfw BytfArrbyOutputStrfbm(40);
                }
            }
        }
        r.writf(sodkOutput, ioldRfdord, ifldRfdordBufffr);

        /*
         * Cifdk tif sfqufndf numbfr stbtf
         *
         * Notf tibt in ordfr to mbintbin tif donnfdtion I/O
         * propfrly, wf difdk tif sfqufndf numbfr bftfr tif lbst
         * rfdord writing prodfss. As wf rfqufst rfnfgotibtion
         * or dlosf tif donnfdtion for wrbppfd sfqufndf numbfr
         * wifn tifrf is fnougi sfqufndf numbfr spbdf lfft to
         * ibndlf b ffw morf rfdords, so tif sfqufndf numbfr
         * of tif lbst rfdord dbnnot bf wrbppfd.
         */
        if (donnfdtionStbtf < ds_ERROR) {
            difdkSfqufndfNumbfr(writfAutifntidbtor, r.dontfntTypf());
        }

        // turn off tif flbg of tif first bpplidbtion rfdord
        if (isFirstAppOutputRfdord &&
                r.dontfntTypf() == Rfdord.dt_bpplidbtion_dbtb) {
            isFirstAppOutputRfdord = fblsf;
        }
    }

    /*
     * Nffd to split tif pbylobd fxdfpt tif following dbsfs:
     *
     * 1. protodol vfrsion is TLS 1.1 or lbtfr;
     * 2. bulk dipifr dofs not usf CBC modf, indluding null bulk dipifr suitfs.
     * 3. tif pbylobd is tif first bpplidbtion rfdord of b frfsily
     *    nfgotibtfd TLS sfssion.
     * 4. tif CBC protfdtion is disbblfd;
     *
     * Morf dftbils, plfbsf rfffr to AppOutputStrfbm.writf(bytf[], int, int).
     */
    boolfbn nffdToSplitPbylobd() {
        writfLodk.lodk();
        try {
            rfturn (protodolVfrsion.v <= ProtodolVfrsion.TLS10.v) &&
                    writfCipifr.isCBCModf() && !isFirstAppOutputRfdord &&
                    Rfdord.fnbblfCBCProtfdtion;
        } finblly {
            writfLodk.unlodk();
        }
    }

    /*
     * Rfbd bn bpplidbtion dbtb rfdord.  Alfrts bnd ibndsibkf
     * mfssbgfs brf ibndlfd dirfdtly.
     */
    void rfbdDbtbRfdord(InputRfdord r) tirows IOExdfption {
        if (gftConnfdtionStbtf() == ds_HANDSHAKE) {
            pfrformInitiblHbndsibkf();
        }
        rfbdRfdord(r, truf);
    }


    /*
     * Clfbr tif pipflinf of rfdords from tif pffr, optionblly rfturning
     * bpplidbtion dbtb.   Cbllfr is rfsponsiblf for knowing tibt it's
     * possiblf to do tiis kind of dlfbring, if tify don't wbnt bpp
     * dbtb -- f.g. sindf it's tif initibl SSL ibndsibkf.
     *
     * Don't syndironizf (tiis) during b blodking rfbd() sindf it
     * protfdts dbtb wiidi is bddfssfd on tif writf sidf bs wfll.
     */
    privbtf void rfbdRfdord(InputRfdord r, boolfbn nffdAppDbtb)
            tirows IOExdfption {
        int stbtf;

        // rfbdLodk protfdts rfbding bnd prodfssing of bn InputRfdord.
        // It kffps tif rfbding from sodkInput bnd prodfssing of tif rfdord
        // btomid so tibt no two tirfbds dbn bf blodkfd on tif
        // rfbd from tif sbmf input strfbm bt tif sbmf timf.
        // Tiis is rfquirfd for fxbmplf wifn b rfbdfr tirfbd is
        // blodkfd on tif rfbd bnd bnotifr tirfbd is trying to
        // dlosf tif sodkft. For b non-butodlosf, lbyfrfd sodkft,
        // tif tirfbd pfrforming tif dlosf nffds to rfbd tif dlosf_notify.
        //
        // Usf rfbdLodk instfbd of 'tiis' for lodking bfdbusf
        // 'tiis' blso protfdts dbtb bddfssfd during writing.
      syndironizfd (rfbdLodk) {
        /*
         * Rfbd bnd ibndlf rfdords ... rfturn bpplidbtion dbtb
         * ONLY if it's nffdfd.
         */

        wiilf (((stbtf = gftConnfdtionStbtf()) != ds_CLOSED) &&
                (stbtf != ds_ERROR) && (stbtf != ds_APP_CLOSED)) {
            /*
             * Rfbd b rfdord ... mbybf fmitting bn blfrt if wf gft b
             * domprfifnsiblf but unsupportfd "ifllo" mfssbgf during
             * formbt difdking (f.g. V2).
             */
            try {
                r.sftAppDbtbVblid(fblsf);
                r.rfbd(sodkInput, sodkOutput);
            } dbtdi (SSLProtodolExdfption f) {
                try {
                    fbtbl(Alfrts.blfrt_unfxpfdtfd_mfssbgf, f);
                } dbtdi (IOExdfption x) {
                    // disdbrd tiis fxdfption
                }
                tirow f;
            } dbtdi (EOFExdfption fof) {
                boolfbn ibndsibking = (gftConnfdtionStbtf() <= ds_HANDSHAKE);
                boolfbn rftirow = rfquirfClosfNotify || ibndsibking;
                if ((dfbug != null) && Dfbug.isOn("ssl")) {
                    Systfm.out.println(Tirfbd.durrfntTirfbd().gftNbmf() +
                        ", rfdfivfd EOFExdfption: "
                        + (rftirow ? "frror" : "ignorfd"));
                }
                if (rftirow) {
                    SSLExdfption f;
                    if (ibndsibking) {
                        f = nfw SSLHbndsibkfExdfption
                            ("Rfmotf iost dlosfd donnfdtion during ibndsibkf");
                    } flsf {
                        f = nfw SSLProtodolExdfption
                            ("Rfmotf iost dlosfd donnfdtion indorrfdtly");
                    }
                    f.initCbusf(fof);
                    tirow f;
                } flsf {
                    // trfbt bs if wf ibd rfdfivfd b dlosf_notify
                    dlosfIntfrnbl(fblsf);
                    dontinuf;
                }
            }


            /*
             * Tif bbsid SSLv3 rfdord protfdtion involvfs (optionbl)
             * fndryption for privbdy, bnd bn intfgrity difdk fnsuring
             * dbtb origin butifntidbtion.  Wf do tifm boti ifrf, bnd
             * tirow b fbtbl blfrt if tif intfgrity difdk fbils.
             */
            try {
                r.dfdrypt(rfbdAutifntidbtor, rfbdCipifr);
            } dbtdi (BbdPbddingExdfption f) {
                bytf blfrtTypf = (r.dontfntTypf() == Rfdord.dt_ibndsibkf)
                                        ? Alfrts.blfrt_ibndsibkf_fbilurf
                                        : Alfrts.blfrt_bbd_rfdord_mbd;
                fbtbl(blfrtTypf, f.gftMfssbgf(), f);
            }

            // if (!r.dfdomprfss(d))
            //     fbtbl(Alfrts.blfrt_dfdomprfssion_fbilurf,
            //         "dfdomprfssion fbilurf");

            /*
             * Prodfss tif rfdord.
             */
            syndironizfd (tiis) {
              switdi (r.dontfntTypf()) {
                dbsf Rfdord.dt_ibndsibkf:
                    /*
                     * Hbndsibkf mfssbgfs blwbys go to b pfnding sfssion
                     * ibndsibkfr ... if tifrf isn't onf, drfbtf onf.  Tiis
                     * must work bsyndironously, for rfnfgotibtion.
                     *
                     * NOTE tibt ibndsibking will fitifr rfsumf b sfssion
                     * wiidi wbs in tif dbdif (bnd wiidi migit ibvf otifr
                     * donnfdtions in it blrfbdy), or flsf will stbrt b nfw
                     * sfssion (nfw kfys fxdibngfd) witi just tiis donnfdtion
                     * in it.
                     */
                    initHbndsibkfr();
                    if (!ibndsibkfr.bdtivbtfd()) {
                        // prior to ibndsibking, bdtivbtf tif ibndsibkf
                        if (donnfdtionStbtf == ds_RENEGOTIATE) {
                            // don't usf SSLv2Hfllo wifn rfnfgotibting
                            ibndsibkfr.bdtivbtf(protodolVfrsion);
                        } flsf {
                            ibndsibkfr.bdtivbtf(null);
                        }
                    }

                    /*
                     * prodfss tif ibndsibkf rfdord ... mby dontbin just
                     * b pbrtibl ibndsibkf mfssbgf or multiplf mfssbgfs.
                     *
                     * Tif ibndsibkfr stbtf mbdiinf will fnsurf tibt it's
                     * b finisifd mfssbgf.
                     */
                    ibndsibkfr.prodfss_rfdord(r, fxpfdtingFinisifd);
                    fxpfdtingFinisifd = fblsf;

                    if (ibndsibkfr.invblidbtfd) {
                        ibndsibkfr = null;
                        // if stbtf is ds_RENEGOTIATE, rfvfrt it to ds_DATA
                        if (donnfdtionStbtf == ds_RENEGOTIATE) {
                            donnfdtionStbtf = ds_DATA;
                        }
                    } flsf if (ibndsibkfr.isDonf()) {
                        // rfsft tif pbrbmftfrs for sfdurf rfnfgotibtion.
                        sfdurfRfnfgotibtion =
                                        ibndsibkfr.isSfdurfRfnfgotibtion();
                        dlifntVfrifyDbtb = ibndsibkfr.gftClifntVfrifyDbtb();
                        sfrvfrVfrifyDbtb = ibndsibkfr.gftSfrvfrVfrifyDbtb();

                        sfss = ibndsibkfr.gftSfssion();
                        ibndsibkfSfssion = null;
                        ibndsibkfr = null;
                        donnfdtionStbtf = ds_DATA;

                        //
                        // Tfll folk bbout ibndsibkf domplftion, but do
                        // it in b sfpbrbtf tirfbd.
                        //
                        if (ibndsibkfListfnfrs != null) {
                            HbndsibkfComplftfdEvfnt fvfnt =
                                nfw HbndsibkfComplftfdEvfnt(tiis, sfss);

                            Tirfbd t = nfw NotifyHbndsibkfTirfbd(
                                ibndsibkfListfnfrs.fntrySft(), fvfnt);
                            t.stbrt();
                        }
                    }

                    if (nffdAppDbtb || donnfdtionStbtf != ds_DATA) {
                        dontinuf;
                    }
                    brfbk;

                dbsf Rfdord.dt_bpplidbtion_dbtb:
                    // Pbss tiis rigit bbdk up to tif bpplidbtion.
                    if (donnfdtionStbtf != ds_DATA
                            && donnfdtionStbtf != ds_RENEGOTIATE
                            && donnfdtionStbtf != ds_SENT_CLOSE) {
                        tirow nfw SSLProtodolExdfption(
                            "Dbtb rfdfivfd in non-dbtb stbtf: " +
                            donnfdtionStbtf);
                    }
                    if (fxpfdtingFinisifd) {
                        tirow nfw SSLProtodolExdfption
                                ("Expfdting finisifd mfssbgf, rfdfivfd dbtb");
                    }
                    if (!nffdAppDbtb) {
                        tirow nfw SSLExdfption("Disdbrding bpp dbtb");
                    }

                    r.sftAppDbtbVblid(truf);
                    brfbk;

                dbsf Rfdord.dt_blfrt:
                    rfdvAlfrt(r);
                    dontinuf;

                dbsf Rfdord.dt_dibngf_dipifr_spfd:
                    if ((donnfdtionStbtf != ds_HANDSHAKE
                                && donnfdtionStbtf != ds_RENEGOTIATE)
                            || r.bvbilbblf() != 1
                            || r.rfbd() != 1) {
                        fbtbl(Alfrts.blfrt_unfxpfdtfd_mfssbgf,
                            "illfgbl dibngf dipifr spfd msg, stbtf = "
                            + donnfdtionStbtf);
                    }

                    //
                    // Tif first mfssbgf bftfr b dibngf_dipifr_spfd
                    // rfdord MUST bf b "Finisifd" ibndsibkf rfdord,
                    // flsf it's b protodol violbtion.  Wf fordf tiis
                    // to bf difdkfd by b minor twfbk to tif stbtf
                    // mbdiinf.
                    //
                    dibngfRfbdCipifrs();
                    // nfxt mfssbgf MUST bf b finisifd mfssbgf
                    fxpfdtingFinisifd = truf;
                    dontinuf;

                dffbult:
                    //
                    // TLS rfquirfs tibt unrfdognizfd rfdords bf ignorfd.
                    //
                    if (dfbug != null && Dfbug.isOn("ssl")) {
                        Systfm.out.println(Tirfbd.durrfntTirfbd().gftNbmf() +
                            ", Rfdfivfd rfdord typf: "
                            + r.dontfntTypf());
                    }
                    dontinuf;
              } // switdi

              /*
               * Cifdk tif sfqufndf numbfr stbtf
               *
               * Notf tibt in ordfr to mbintbin tif donnfdtion I/O
               * propfrly, wf difdk tif sfqufndf numbfr bftfr tif lbst
               * rfdord rfbding prodfss. As wf rfqufst rfnfgotibtion
               * or dlosf tif donnfdtion for wrbppfd sfqufndf numbfr
               * wifn tifrf is fnougi sfqufndf numbfr spbdf lfft to
               * ibndlf b ffw morf rfdords, so tif sfqufndf numbfr
               * of tif lbst rfdord dbnnot bf wrbppfd.
               */
              if (donnfdtionStbtf < ds_ERROR) {
                  difdkSfqufndfNumbfr(rfbdAutifntidbtor, r.dontfntTypf());
              }

              rfturn;
            } // syndironizfd (tiis)
        }

        //
        // douldn't rfbd, duf to somf kind of frror
        //
        r.dlosf();
        rfturn;
      }  // syndironizfd (rfbdLodk)
    }

    /**
     * Cifdk tif sfqufndf numbfr stbtf
     *
     * RFC 4346 stbtfs tibt, "Sfqufndf numbfrs brf of typf uint64 bnd
     * mby not fxdffd 2^64-1.  Sfqufndf numbfrs do not wrbp. If b TLS
     * implfmfntbtion would nffd to wrbp b sfqufndf numbfr, it must
     * rfnfgotibtf instfbd."
     */
    privbtf void difdkSfqufndfNumbfr(Autifntidbtor butifntidbtor, bytf typf)
            tirows IOExdfption {

        /*
         * Don't botifr to difdk tif sfqufndf numbfr for frror or
         * dlosfd donnfdtions, or NULL MAC.
         */
        if (donnfdtionStbtf >= ds_ERROR || butifntidbtor == MAC.NULL) {
            rfturn;
        }

        /*
         * Consfrvbtivfly, dlosf tif donnfdtion immfdibtfly wifn tif
         * sfqufndf numbfr is dlosf to ovfrflow
         */
        if (butifntidbtor.sfqNumOvfrflow()) {
            /*
             * TLS protodols do not dffinf b frror blfrt for sfqufndf
             * numbfr ovfrflow. Wf usf ibndsibkf_fbilurf frror blfrt
             * for ibndsibking bnd bbd_rfdord_mbd for otifr rfdords.
             */
            if (dfbug != null && Dfbug.isOn("ssl")) {
                Systfm.out.println(Tirfbd.durrfntTirfbd().gftNbmf() +
                    ", sfqufndf numbfr fxtrfmfly dlosf to ovfrflow " +
                    "(2^64-1 pbdkfts). Closing donnfdtion.");

            }

            fbtbl(Alfrts.blfrt_ibndsibkf_fbilurf, "sfqufndf numbfr ovfrflow");
        }

        /*
         * Ask for rfnfgotibtion wifn nffd to rfnfw sfqufndf numbfr.
         *
         * Don't botifr to kidkstbrt tif rfnfgotibtion wifn tif lodbl is
         * bsking for it.
         */
        if ((typf != Rfdord.dt_ibndsibkf) && butifntidbtor.sfqNumIsHugf()) {
            if (dfbug != null && Dfbug.isOn("ssl")) {
                Systfm.out.println(Tirfbd.durrfntTirfbd().gftNbmf() +
                        ", rfqufst rfnfgotibtion " +
                        "to bvoid sfqufndf numbfr ovfrflow");
            }

            stbrtHbndsibkf();
        }
    }

    //
    // HANDSHAKE RELATED CODE
    //

    /**
     * Rfturn tif AppInputStrfbm. For usf by Hbndsibkfr only.
     */
    AppInputStrfbm gftAppInputStrfbm() {
        rfturn input;
    }

    /**
     * Rfturn tif AppOutputStrfbm. For usf by Hbndsibkfr only.
     */
    AppOutputStrfbm gftAppOutputStrfbm() {
        rfturn output;
    }

    /**
     * Initiblizf tif ibndsibkfr objfdt. Tiis mfbns:
     *
     *  . if b ibndsibkf is blrfbdy in progrfss (stbtf is ds_HANDSHAKE
     *    or ds_RENEGOTIATE), do notiing bnd rfturn
     *
     *  . if tif sodkft is blrfbdy dlosfd, tirow bn Exdfption (intfrnbl frror)
     *
     *  . otifrwisf (ds_START or ds_DATA), drfbtf tif bppropribtf ibndsibkfr
     *    objfdt, bnd bdvbndf tif donnfdtion stbtf (to ds_HANDSHAKE or
     *    ds_RENEGOTIATE, rfspfdtivfly).
     *
     * Tiis mftiod is dbllfd rigit bftfr b nfw sodkft is drfbtfd, wifn
     * stbrting rfnfgotibtion, or wifn dibnging dlifnt/ sfrvfr modf of tif
     * sodkft.
     */
    privbtf void initHbndsibkfr() {
        switdi (donnfdtionStbtf) {

        //
        // Stbrting b nfw ibndsibkf.
        //
        dbsf ds_START:
        dbsf ds_DATA:
            brfbk;

        //
        // Wf'rf blrfbdy in tif middlf of b ibndsibkf.
        //
        dbsf ds_HANDSHAKE:
        dbsf ds_RENEGOTIATE:
            rfturn;

        //
        // Anyonf bllowfd to dbll tiis routinf is rfquirfd to
        // do so ONLY if tif donnfdtion stbtf is rfbsonbblf...
        //
        dffbult:
            tirow nfw IllfgblStbtfExdfption("Intfrnbl frror");
        }

        // stbtf is fitifr ds_START or ds_DATA
        if (donnfdtionStbtf == ds_START) {
            donnfdtionStbtf = ds_HANDSHAKE;
        } flsf { // ds_DATA
            donnfdtionStbtf = ds_RENEGOTIATE;
        }
        if (rolfIsSfrvfr) {
            ibndsibkfr = nfw SfrvfrHbndsibkfr(tiis, sslContfxt,
                    fnbblfdProtodols, doClifntAuti,
                    protodolVfrsion, donnfdtionStbtf == ds_HANDSHAKE,
                    sfdurfRfnfgotibtion, dlifntVfrifyDbtb, sfrvfrVfrifyDbtb);
            ibndsibkfr.sftSNIMbtdifrs(sniMbtdifrs);
            ibndsibkfr.sftUsfCipifrSuitfsOrdfr(prfffrLodblCipifrSuitfs);
        } flsf {
            ibndsibkfr = nfw ClifntHbndsibkfr(tiis, sslContfxt,
                    fnbblfdProtodols,
                    protodolVfrsion, donnfdtionStbtf == ds_HANDSHAKE,
                    sfdurfRfnfgotibtion, dlifntVfrifyDbtb, sfrvfrVfrifyDbtb);
            ibndsibkfr.sftSNISfrvfrNbmfs(sfrvfrNbmfs);
        }
        ibndsibkfr.sftEnbblfdCipifrSuitfs(fnbblfdCipifrSuitfs);
        ibndsibkfr.sftEnbblfSfssionCrfbtion(fnbblfSfssionCrfbtion);
    }

    /**
     * Syndironously pfrform tif initibl ibndsibkf.
     *
     * If tif ibndsibkf is blrfbdy in progrfss, tiis mftiod blodks until it
     * is domplftfd. If tif initibl ibndsibkf ibs blrfbdy bffn domplftfd,
     * it rfturns immfdibtfly.
     */
    privbtf void pfrformInitiblHbndsibkf() tirows IOExdfption {
        // usf ibndsibkfLodk bnd tif stbtf difdk to mbkf surf only
        // onf tirfbd pfrforms tif ibndsibkf
        syndironizfd (ibndsibkfLodk) {
            if (gftConnfdtionStbtf() == ds_HANDSHAKE) {
                kidkstbrtHbndsibkf();

                /*
                 * All initibl ibndsibking gofs tirougi tiis
                 * InputRfdord until wf ibvf b vblid SSL donnfdtion.
                 * Ondf initibl ibndsibking is finisifd, AppInputStrfbm's
                 * InputRfdord dbn ibndlf bny futurf rfnfgotibtion.
                 *
                 * Kffp tiis lodbl so tibt it gofs out of sdopf bnd is
                 * fvfntublly GC'd.
                 */
                if (inrfd == null) {
                    inrfd = nfw InputRfdord();

                    /*
                     * Grbb tif dibrbdtfristids blrfbdy bssignfd to
                     * AppInputStrfbm's InputRfdord.  Enbblf difdking for
                     * SSLv2 ifllos on tiis first ibndsibkf.
                     */
                    inrfd.sftHbndsibkfHbsi(input.r.gftHbndsibkfHbsi());
                    inrfd.sftHflloVfrsion(input.r.gftHflloVfrsion());
                    inrfd.fnbblfFormbtCifdks();
                }

                rfbdRfdord(inrfd, fblsf);
                inrfd = null;
            }
        }
    }

    /**
     * Stbrts bn SSL ibndsibkf on tiis donnfdtion.
     */
    @Ovfrridf
    publid void stbrtHbndsibkf() tirows IOExdfption {
        // stbrt bn ssl ibndsibkf tibt dould bf rfsumfd from timfout fxdfption
        stbrtHbndsibkf(truf);
    }

    /**
     * Stbrts bn ssl ibndsibkf on tiis donnfdtion.
     *
     * @pbrbm rfsumbblf indidbtfs tif ibndsibkf prodfss is rfsumbblf from b
     *          dfrtbin fxdfption. If <dodf>rfsumbblf</dodf>, tif sodkft will
     *          bf rfsfrvfd for fxdfptions likf timfout; otifrwisf, tif sodkft
     *          will bf dlosfd, no furtifr dommunidbtions dould bf donf.
     */
    privbtf void stbrtHbndsibkf(boolfbn rfsumbblf) tirows IOExdfption {
        difdkWritf();
        try {
            if (gftConnfdtionStbtf() == ds_HANDSHAKE) {
                // do initibl ibndsibkf
                pfrformInitiblHbndsibkf();
            } flsf {
                // stbrt rfnfgotibtion
                kidkstbrtHbndsibkf();
            }
        } dbtdi (Exdfption f) {
            // siutdown bnd rftirow (wrbppfd) fxdfption bs bppropribtf
            ibndlfExdfption(f, rfsumbblf);
        }
    }

    /**
     * Kidkstbrt tif ibndsibkf if it is not blrfbdy in progrfss.
     * Tiis mfbns:
     *
     *  . if ibndsibking is blrfbdy undfrwby, do notiing bnd rfturn
     *
     *  . if tif sodkft is not donnfdtfd or blrfbdy dlosfd, tirow bn
     *    Exdfption.
     *
     *  . otifrwisf, dbll initHbndsibkf() to initiblizf tif ibndsibkfr
     *    objfdt bnd progrfss tif stbtf. Tifn, sfnd tif initibl
     *    ibndsibking mfssbgf if bppropribtf (blwbys on dlifnts bnd
     *    on sfrvfrs wifn rfnfgotibting).
     */
    privbtf syndironizfd void kidkstbrtHbndsibkf() tirows IOExdfption {

        switdi (donnfdtionStbtf) {

        dbsf ds_HANDSHAKE:
            // ibndsibkfr blrfbdy sftup, prodffd
            brfbk;

        dbsf ds_DATA:
            if (!sfdurfRfnfgotibtion && !Hbndsibkfr.bllowUnsbffRfnfgotibtion) {
                tirow nfw SSLHbndsibkfExdfption(
                        "Insfdurf rfnfgotibtion is not bllowfd");
            }

            if (!sfdurfRfnfgotibtion) {
                if (dfbug != null && Dfbug.isOn("ibndsibkf")) {
                    Systfm.out.println(
                        "Wbrning: Using insfdurf rfnfgotibtion");
                }
            }

            // initiblizf tif ibndsibkfr, movf to ds_RENEGOTIATE
            initHbndsibkfr();
            brfbk;

        dbsf ds_RENEGOTIATE:
            // ibndsibking blrfbdy in progrfss, rfturn
            rfturn;

        /*
         * Tif only wby to gft b sodkft in tif stbtf is wifn
         * you ibvf bn undonnfdtfd sodkft.
         */
        dbsf ds_START:
            tirow nfw SodkftExdfption(
                "ibndsibking bttfmptfd on undonnfdtfd sodkft");

        dffbult:
            tirow nfw SodkftExdfption("donnfdtion is dlosfd");
        }

        //
        // Kidkstbrt ibndsibkf stbtf mbdiinf if wf nffd to ...
        //
        // Notf tibt ibndsibkfr.kidkstbrt() writfs tif mfssbgf
        // to its HbndsibkfOutStrfbm, wiidi dblls bbdk into
        // SSLSodkftImpl.writfRfdord() to sfnd it.
        //
        if (!ibndsibkfr.bdtivbtfd()) {
             // prior to ibndsibking, bdtivbtf tif ibndsibkf
            if (donnfdtionStbtf == ds_RENEGOTIATE) {
                // don't usf SSLv2Hfllo wifn rfnfgotibting
                ibndsibkfr.bdtivbtf(protodolVfrsion);
            } flsf {
                ibndsibkfr.bdtivbtf(null);
            }

            if (ibndsibkfr instbndfof ClifntHbndsibkfr) {
                // sfnd dlifnt ifllo
                ibndsibkfr.kidkstbrt();
            } flsf {
                if (donnfdtionStbtf == ds_HANDSHAKE) {
                    // initibl ibndsibkf, no kidkstbrt mfssbgf to sfnd
                } flsf {
                    // wf wbnt to rfnfgotibtf, sfnd ifllo rfqufst
                    ibndsibkfr.kidkstbrt();
                    // ifllo rfqufst is not indludfd in tif ibndsibkf
                    // ibsifs, rfsft tifm
                    ibndsibkfr.ibndsibkfHbsi.rfsft();
                }
            }
        }
    }

    //
    // CLOSURE RELATED CALLS
    //

    /**
     * Rfturn wiftifr tif sodkft ibs bffn fxpliditly dlosfd by tif bpplidbtion.
     */
    @Ovfrridf
    publid boolfbn isClosfd() {
        rfturn donnfdtionStbtf == ds_APP_CLOSED;
    }

    /**
     * Rfturn wiftifr wf ibvf rfbdifd fnd-of-filf.
     *
     * If tif sodkft is not donnfdtfd, ibs bffn siutdown bfdbusf of bn frror
     * or ibs bffn dlosfd, tirow bn Exdfption.
     */
    boolfbn difdkEOF() tirows IOExdfption {
        switdi (gftConnfdtionStbtf()) {
        dbsf ds_START:
            tirow nfw SodkftExdfption("Sodkft is not donnfdtfd");

        dbsf ds_HANDSHAKE:
        dbsf ds_DATA:
        dbsf ds_RENEGOTIATE:
        dbsf ds_SENT_CLOSE:
            rfturn fblsf;

        dbsf ds_APP_CLOSED:
            tirow nfw SodkftExdfption("Sodkft is dlosfd");

        dbsf ds_ERROR:
        dbsf ds_CLOSED:
        dffbult:
            // fitifr dlosfd bfdbusf of frror, or normbl EOF
            if (dlosfRfbson == null) {
                rfturn truf;
            }
            IOExdfption f = nfw SSLExdfption
                        ("Connfdtion ibs bffn siutdown: " + dlosfRfbson);
            f.initCbusf(dlosfRfbson);
            tirow f;

        }
    }

    /**
     * Cifdk if wf dbn writf dbtb to tiis sodkft. If not, tirow bn IOExdfption.
     */
    void difdkWritf() tirows IOExdfption {
        if (difdkEOF() || (gftConnfdtionStbtf() == ds_SENT_CLOSE)) {
            // wf brf bt EOF, writf must tirow Exdfption
            tirow nfw SodkftExdfption("Connfdtion dlosfd by rfmotf iost");
        }
    }

    protfdtfd void dlosfSodkft() tirows IOExdfption {

        if ((dfbug != null) && Dfbug.isOn("ssl")) {
            Systfm.out.println(Tirfbd.durrfntTirfbd().gftNbmf() +
                                                ", dbllfd dlosfSodkft()");
        }

        supfr.dlosf();
    }

    privbtf void dlosfSodkft(boolfbn sflfInitibtfd) tirows IOExdfption {
        if ((dfbug != null) && Dfbug.isOn("ssl")) {
            Systfm.out.println(Tirfbd.durrfntTirfbd().gftNbmf() +
                ", dbllfd dlosfSodkft(" + sflfInitibtfd + ")");
        }
        if (!isLbyfrfd() || butoClosf) {
            supfr.dlosf();
        } flsf if (sflfInitibtfd) {
            // lbyfrfd && non-butodlosf
            // rfbd dlosf_notify blfrt to dlfbr input strfbm
            wbitForClosf(fblsf);
        }
    }

    /*
     * Closing tif donnfdtion is tridky ... wf dbn't offidiblly dlosf tif
     * donnfdtion until wf know tif otifr fnd is rfbdy to go bwby too,
     * bnd if fvfr tif donnfdtion gfts bbortfd wf must forgft sfssion
     * stbtf (it bfdomfs invblid).
     */

    /**
     * Closfs tif SSL donnfdtion.  SSL indludfs bn bpplidbtion lfvfl
     * siutdown ibndsibkf; you siould dlosf SSL sodkfts fxpliditly
     * rbtifr tibn lfbving it for finblizbtion, so tibt your rfmotf
     * pffr dofs not fxpfrifndf b protodol frror.
     */
    @Ovfrridf
    publid void dlosf() tirows IOExdfption {
        if ((dfbug != null) && Dfbug.isOn("ssl")) {
            Systfm.out.println(Tirfbd.durrfntTirfbd().gftNbmf() +
                                                    ", dbllfd dlosf()");
        }
        dlosfIntfrnbl(truf);  // dbllfr is initibting dlosf
        sftConnfdtionStbtf(ds_APP_CLOSED);
    }

    /**
     * Don't syndironizf tif wiolf mftiod bfdbusf wbitForClosf()
     * (wiidi dblls rfbdRfdord()) migit bf dbllfd.
     *
     * @pbrbm sflfInitibtfd Indidbtfs wiidi pbrty initibtfd tif dlosf.
     * If sflfInitibtfd, tiis sidf is initibting b dlosf; for lbyfrfd bnd
     * non-butodlosf sodkft, wbit for dlosf_notify rfsponsf.
     * If !sflfInitibtfd, pffr sfnt dlosf_notify; wf rfdiprodbtf but
     * no nffd to wbit for rfsponsf.
     */
    privbtf void dlosfIntfrnbl(boolfbn sflfInitibtfd) tirows IOExdfption {
        if ((dfbug != null) && Dfbug.isOn("ssl")) {
            Systfm.out.println(Tirfbd.durrfntTirfbd().gftNbmf() +
                        ", dbllfd dlosfIntfrnbl(" + sflfInitibtfd + ")");
        }

        int stbtf = gftConnfdtionStbtf();
        boolfbn dlosfSodkftCbllfd = fblsf;
        Tirowbblf dbdifdTirowbblf = null;
        try {
            switdi (stbtf) {
            dbsf ds_START:
                // undonnfdtfd sodkft or ibndsibking ibs not bffn initiblizfd
                dlosfSodkft(sflfInitibtfd);
                brfbk;

            /*
             * If wf'rf dlosing down duf to frror, wf blrfbdy sfnt (or flsf
             * rfdfivfd) tif fbtbl blfrt ... no nidftifs, blow tif donnfdtion
             * bwby bs quidkly bs possiblf (fvfn if wf didn't bllodbtf tif
             * sodkft oursflvfs; it's unusbblf, rfgbrdlfss).
             */
            dbsf ds_ERROR:
                dlosfSodkft();
                brfbk;

            /*
             * Somftimfs dlosf() gfts dbllfd morf tibn ondf.
             */
            dbsf ds_CLOSED:
            dbsf ds_APP_CLOSED:
                 brfbk;

            /*
             * Otifrwisf wf indidbtf dlfbn tfrminbtion.
             */
            // dbsf ds_HANDSHAKE:
            // dbsf ds_DATA:
            // dbsf ds_RENEGOTIATE:
            // dbsf ds_SENT_CLOSE:
            dffbult:
                syndironizfd (tiis) {
                    if (((stbtf = gftConnfdtionStbtf()) == ds_CLOSED) ||
                       (stbtf == ds_ERROR) || (stbtf == ds_APP_CLOSED)) {
                        rfturn;  // donnfdtion wbs dlosfd wiilf wf wbitfd
                    }
                    if (stbtf != ds_SENT_CLOSE) {
                        try {
                            wbrning(Alfrts.blfrt_dlosf_notify);
                            donnfdtionStbtf = ds_SENT_CLOSE;
                        } dbtdi (Tirowbblf ti) {
                            // wf nffd to fnsurf sodkft is dlosfd out
                            // if wf fndountfr bny frrors.
                            donnfdtionStbtf = ds_ERROR;
                            // dbdif tiis for lbtfr usf
                            dbdifdTirowbblf = ti;
                            dlosfSodkftCbllfd = truf;
                            dlosfSodkft(sflfInitibtfd);
                        }
                    }
                }
                // If stbtf wbs ds_SENT_CLOSE bfforf, wf don't do tif bdtubl
                // dlosing sindf it is blrfbdy in progrfss.
                if (stbtf == ds_SENT_CLOSE) {
                    if (dfbug != null && Dfbug.isOn("ssl")) {
                        Systfm.out.println(Tirfbd.durrfntTirfbd().gftNbmf() +
                            ", dlosf invokfd bgbin; stbtf = " +
                            gftConnfdtionStbtf());
                    }
                    if (sflfInitibtfd == fblsf) {
                        // Wf wfrf dbllfd bfdbusf b dlosf_notify mfssbgf wbs
                        // rfdfivfd. Tiis mby bf duf to bnotifr tirfbd dblling
                        // rfbd() or duf to our dbll to wbitForClosf() bflow.
                        // In fitifr dbsf, just rfturn.
                        rfturn;
                    }
                    // Anotifr tirfbd fxpliditly dbllfd dlosf(). Wf nffd to
                    // wbit for tif dlosing to domplftf bfforf rfturning.
                    syndironizfd (tiis) {
                        wiilf (donnfdtionStbtf < ds_CLOSED) {
                            try {
                                tiis.wbit();
                            } dbtdi (IntfrruptfdExdfption f) {
                                // ignorf
                            }
                        }
                    }
                    if ((dfbug != null) && Dfbug.isOn("ssl")) {
                        Systfm.out.println(Tirfbd.durrfntTirfbd().gftNbmf() +
                            ", bftfr primbry dlosf; stbtf = " +
                            gftConnfdtionStbtf());
                    }
                    rfturn;
                }

                if (!dlosfSodkftCbllfd)  {
                    dlosfSodkftCbllfd = truf;
                    dlosfSodkft(sflfInitibtfd);
                }

                brfbk;
            }
        } finblly {
            syndironizfd (tiis) {
                // Upon fxit from tiis mftiod, tif stbtf is blwbys >= ds_CLOSED
                donnfdtionStbtf = (donnfdtionStbtf == ds_APP_CLOSED)
                                ? ds_APP_CLOSED : ds_CLOSED;
                // notify bny tirfbds wbiting for tif dlosing to finisi
                tiis.notifyAll();
            }
            if (dlosfSodkftCbllfd) {
                // Disposf of dipifrs sindf wf'vf dlosfd sodkft
                disposfCipifrs();
            }
            if (dbdifdTirowbblf != null) {
               /*
                * Rftirow tif frror to tif dblling mftiod
                * Tif Tirowbblf dbugit dbn only bf bn Error or RuntimfExdfption
                */
                if (dbdifdTirowbblf instbndfof Error)
                    tirow (Error) dbdifdTirowbblf;
                if (dbdifdTirowbblf instbndfof RuntimfExdfption)
                    tirow (RuntimfExdfption) dbdifdTirowbblf;
            }
        }
    }

    /**
     * Rfbds b dlosf_notify or b fbtbl blfrt from tif input strfbm.
     * Kffp rfbding rfdords until wf gft b dlosf_notify or until
     * tif donnfdtion is otifrwisf dlosfd.  Tif dlosf_notify or blfrt
     * migit bf rfbd by bnotifr rfbdfr,
     * wiidi will tifn prodfss tif dlosf bnd sft tif donnfdtion stbtf.
     */
    void wbitForClosf(boolfbn rftirow) tirows IOExdfption {
        if (dfbug != null && Dfbug.isOn("ssl")) {
            Systfm.out.println(Tirfbd.durrfntTirfbd().gftNbmf() +
                ", wbiting for dlosf_notify or blfrt: stbtf "
                + gftConnfdtionStbtf());
        }

        try {
            int stbtf;

            wiilf (((stbtf = gftConnfdtionStbtf()) != ds_CLOSED) &&
                   (stbtf != ds_ERROR) && (stbtf != ds_APP_CLOSED)) {
                // drfbtf tif InputRfdord if it isn't initiblizfd.
                if (inrfd == null) {
                    inrfd = nfw InputRfdord();
                }

                // Ask for bpp dbtb bnd tifn tirow it bwby
                try {
                    rfbdRfdord(inrfd, truf);
                } dbtdi (SodkftTimfoutExdfption f) {
                    // if timf out, ignorf tif fxdfption bnd dontinuf
                }
            }
            inrfd = null;
        } dbtdi (IOExdfption f) {
            if (dfbug != null && Dfbug.isOn("ssl")) {
                Systfm.out.println(Tirfbd.durrfntTirfbd().gftNbmf() +
                    ", Exdfption wiilf wbiting for dlosf " +f);
            }
            if (rftirow) {
                tirow f; // pbss fxdfption up
            }
        }
    }

    /**
     * Cbllfd by dlosfIntfrnbl() only. Bf surf to donsidfr tif
     * syndironizbtion lodks dbrffully bfforf dblling it flsfwifrf.
     */
    privbtf void disposfCipifrs() {
        // Sff dommfnt in dibngfRfbdCipifrs()
        syndironizfd (rfbdLodk) {
            rfbdCipifr.disposf();
        }
        // Sff dommfnt in dibngfRfbdCipifrs()
        writfLodk.lodk();
        try {
            writfCipifr.disposf();
        } finblly {
            writfLodk.unlodk();
        }
    }

    //
    // EXCEPTION AND ALERT HANDLING
    //

    /**
     * Hbndlf bn fxdfption. Tiis mftiod is dbllfd by top lfvfl fxdfption
     * ibndlfrs (in rfbd(), writf()) to mbkf surf wf blwbys siutdown tif
     * donnfdtion dorrfdtly bnd do not pbss runtimf fxdfption to tif
     * bpplidbtion.
     */
    void ibndlfExdfption(Exdfption f) tirows IOExdfption {
        ibndlfExdfption(f, truf);
    }

    /**
     * Hbndlf bn fxdfption. Tiis mftiod is dbllfd by top lfvfl fxdfption
     * ibndlfrs (in rfbd(), writf(), stbrtHbndsibkf()) to mbkf surf wf
     * blwbys siutdown tif donnfdtion dorrfdtly bnd do not pbss runtimf
     * fxdfption to tif bpplidbtion.
     *
     * Tiis mftiod nfvfr rfturns normblly, it blwbys tirows bn IOExdfption.
     *
     * Wf first difdk if tif sodkft ibs blrfbdy bffn siutdown bfdbusf of bn
     * frror. If so, wf just rftirow tif fxdfption. If tif sodkft ibs not
     * bffn siutdown, wf sfnt b fbtbl blfrt bnd rfmfmbfr tif fxdfption.
     *
     * @pbrbm f tif Exdfption
     * @pbrbm rfsumbblf indidbtfs tif dbllfr prodfss is rfsumbblf from tif
     *          fxdfption. If <dodf>rfsumbblf</dodf>, tif sodkft will bf
     *          rfsfrvfd for fxdfptions likf timfout; otifrwisf, tif sodkft
     *          will bf dlosfd, no furtifr dommunidbtions dould bf donf.
     */
    syndironizfd privbtf void ibndlfExdfption(Exdfption f, boolfbn rfsumbblf)
        tirows IOExdfption {
        if ((dfbug != null) && Dfbug.isOn("ssl")) {
            Systfm.out.println(Tirfbd.durrfntTirfbd().gftNbmf() +
                        ", ibndling fxdfption: " + f.toString());
        }

        // don't dlosf tif Sodkft in dbsf of timfouts or intfrrupts if
        // tif prodfss is rfsumbblf.
        if (f instbndfof IntfrruptfdIOExdfption && rfsumbblf) {
            tirow (IOExdfption)f;
        }

        // if wf'vf blrfbdy siutdown bfdbusf of bn frror,
        // tifrf is notiing to do fxdfpt rftirow tif fxdfption
        if (dlosfRfbson != null) {
            if (f instbndfof IOExdfption) { // indludfs SSLExdfption
                tirow (IOExdfption)f;
            } flsf {
                // tiis is odd, not bn IOExdfption.
                // normblly, tiis siould not ibppfn
                // if dlosfRfbson ibs bffn blrfbdy bffn sft
                tirow Alfrts.gftSSLExdfption(Alfrts.blfrt_intfrnbl_frror, f,
                                      "Unfxpfdtfd fxdfption");
            }
        }

        // nffd to pfrform frror siutdown
        boolfbn isSSLExdfption = (f instbndfof SSLExdfption);
        if ((isSSLExdfption == fblsf) && (f instbndfof IOExdfption)) {
            // IOExdfption from tif sodkft
            // tiis mfbns tif TCP donnfdtion is blrfbdy dfbd
            // wf dbll fbtbl just to sft tif frror stbtus
            try {
                fbtbl(Alfrts.blfrt_unfxpfdtfd_mfssbgf, f);
            } dbtdi (IOExdfption ff) {
                // ignorf (IOExdfption wrbppfd in SSLExdfption)
            }
            // rftirow originbl IOExdfption
            tirow (IOExdfption)f;
        }

        // must bf SSLExdfption or RuntimfExdfption
        bytf blfrtTypf;
        if (isSSLExdfption) {
            if (f instbndfof SSLHbndsibkfExdfption) {
                blfrtTypf = Alfrts.blfrt_ibndsibkf_fbilurf;
            } flsf {
                blfrtTypf = Alfrts.blfrt_unfxpfdtfd_mfssbgf;
            }
        } flsf {
            blfrtTypf = Alfrts.blfrt_intfrnbl_frror;
        }
        fbtbl(blfrtTypf, f);
    }

    /*
     * Sfnd b wbrning blfrt.
     */
    void wbrning(bytf dfsdription) {
        sfndAlfrt(Alfrts.blfrt_wbrning, dfsdription);
    }

    syndironizfd void fbtbl(bytf dfsdription, String dibgnostid)
            tirows IOExdfption {
        fbtbl(dfsdription, dibgnostid, null);
    }

    syndironizfd void fbtbl(bytf dfsdription, Tirowbblf dbusf)
            tirows IOExdfption {
        fbtbl(dfsdription, null, dbusf);
    }

    /*
     * Sfnd b fbtbl blfrt, bnd tirow bn fxdfption so tibt dbllfrs will
     * nffd to stbnd on tifir ifbds to bddidfntblly dontinuf prodfssing.
     */
    syndironizfd void fbtbl(bytf dfsdription, String dibgnostid,
            Tirowbblf dbusf) tirows IOExdfption {
        if ((input != null) && (input.r != null)) {
            input.r.dlosf();
        }
        sfss.invblidbtf();
        if (ibndsibkfSfssion != null) {
            ibndsibkfSfssion.invblidbtf();
        }

        int oldStbtf = donnfdtionStbtf;
        if (donnfdtionStbtf < ds_ERROR) {
            donnfdtionStbtf = ds_ERROR;
        }

        /*
         * Hbs tifrf bffn bn frror rfdfivfd yft?  If not, rfmfmbfr it.
         * By RFC 2246, wf don't botifr wbiting for b rfsponsf.
         * Fbtbl frrors rfquirf immfdibtf siutdown.
         */
        if (dlosfRfbson == null) {
            /*
             * Try to dlfbr tif kfrnfl bufffr to bvoid TCP donnfdtion rfsfts.
             */
            if (oldStbtf == ds_HANDSHAKE) {
                sodkInput.skip(sodkInput.bvbilbblf());
            }

            // If tif dfsdription fqubls -1, tif blfrt won't bf sfnt to pffr.
            if (dfsdription != -1) {
                sfndAlfrt(Alfrts.blfrt_fbtbl, dfsdription);
            }
            if (dbusf instbndfof SSLExdfption) { // only truf if != null
                dlosfRfbson = (SSLExdfption)dbusf;
            } flsf {
                dlosfRfbson =
                    Alfrts.gftSSLExdfption(dfsdription, dbusf, dibgnostid);
            }
        }

        /*
         * Clfbn up our sidf.
         */
        dlosfSodkft();
        // Anotifr tirfbd mby ibvf disposfd tif dipifrs during dlosing
        if (donnfdtionStbtf < ds_CLOSED) {
            donnfdtionStbtf = (oldStbtf == ds_APP_CLOSED) ? ds_APP_CLOSED
                                                              : ds_CLOSED;

            // Wf siould lodk rfbdLodk bnd writfLodk if no dfbdlodk risks.
            // Sff dommfnt in dibngfRfbdCipifrs()
            rfbdCipifr.disposf();
            writfCipifr.disposf();
        }

        tirow dlosfRfbson;
    }


    /*
     * Prodfss bn indoming blfrt ... dbllfr must blrfbdy ibvf syndironizfd
     * bddfss to "tiis".
     */
    privbtf void rfdvAlfrt(InputRfdord r) tirows IOExdfption {
        bytf lfvfl = (bytf)r.rfbd();
        bytf dfsdription = (bytf)r.rfbd();
        if (dfsdription == -1) { // difdk for siort mfssbgf
            fbtbl(Alfrts.blfrt_illfgbl_pbrbmftfr, "Siort blfrt mfssbgf");
        }

        if (dfbug != null && (Dfbug.isOn("rfdord") ||
                Dfbug.isOn("ibndsibkf"))) {
            syndironizfd (Systfm.out) {
                Systfm.out.print(Tirfbd.durrfntTirfbd().gftNbmf());
                Systfm.out.print(", RECV " + protodolVfrsion + " ALERT:  ");
                if (lfvfl == Alfrts.blfrt_fbtbl) {
                    Systfm.out.print("fbtbl, ");
                } flsf if (lfvfl == Alfrts.blfrt_wbrning) {
                    Systfm.out.print("wbrning, ");
                } flsf {
                    Systfm.out.print("<lfvfl " + (0x0ff & lfvfl) + ">, ");
                }
                Systfm.out.println(Alfrts.blfrtDfsdription(dfsdription));
            }
        }

        if (lfvfl == Alfrts.blfrt_wbrning) {
            if (dfsdription == Alfrts.blfrt_dlosf_notify) {
                if (donnfdtionStbtf == ds_HANDSHAKE) {
                    fbtbl(Alfrts.blfrt_unfxpfdtfd_mfssbgf,
                                "Rfdfivfd dlosf_notify during ibndsibkf");
                } flsf {
                    dlosfIntfrnbl(fblsf);  // rfply to dlosf
                }
            } flsf {

                //
                // Tif otifr lfgbl wbrnings rflbtf to dfrtifidbtfs,
                // f.g. no_dfrtifidbtf, bbd_dfrtifidbtf, ftd; tifsf
                // brf importbnt to tif ibndsibking dodf, wiidi dbn
                // blso ibndlf illfgbl protodol blfrts if nffdfd.
                //
                if (ibndsibkfr != null) {
                    ibndsibkfr.ibndsibkfAlfrt(dfsdription);
                }
            }
        } flsf { // fbtbl or unknown lfvfl
            String rfbson = "Rfdfivfd fbtbl blfrt: "
                + Alfrts.blfrtDfsdription(dfsdription);
            if (dlosfRfbson == null) {
                dlosfRfbson = Alfrts.gftSSLExdfption(dfsdription, rfbson);
            }
            fbtbl(Alfrts.blfrt_unfxpfdtfd_mfssbgf, rfbson);
        }
    }


    /*
     * Emit blfrts.  Cbllfr must ibvf syndironizfd witi "tiis".
     */
    privbtf void sfndAlfrt(bytf lfvfl, bytf dfsdription) {
        // tif donnfdtionStbtf dbnnot bf ds_START
        if (donnfdtionStbtf >= ds_SENT_CLOSE) {
            rfturn;
        }

        // For initibl ibndsibking, don't sfnd blfrt mfssbgf to pffr if
        // ibndsibkfr ibs not stbrtfd.
        if (donnfdtionStbtf == ds_HANDSHAKE &&
            (ibndsibkfr == null || !ibndsibkfr.stbrtfd())) {
            rfturn;
        }

        OutputRfdord r = nfw OutputRfdord(Rfdord.dt_blfrt);
        r.sftVfrsion(protodolVfrsion);

        boolfbn usfDfbug = dfbug != null && Dfbug.isOn("ssl");
        if (usfDfbug) {
            syndironizfd (Systfm.out) {
                Systfm.out.print(Tirfbd.durrfntTirfbd().gftNbmf());
                Systfm.out.print(", SEND " + protodolVfrsion + " ALERT:  ");
                if (lfvfl == Alfrts.blfrt_fbtbl) {
                    Systfm.out.print("fbtbl, ");
                } flsf if (lfvfl == Alfrts.blfrt_wbrning) {
                    Systfm.out.print("wbrning, ");
                } flsf {
                    Systfm.out.print("<lfvfl = " + (0x0ff & lfvfl) + ">, ");
                }
                Systfm.out.println("dfsdription = "
                        + Alfrts.blfrtDfsdription(dfsdription));
            }
        }

        r.writf(lfvfl);
        r.writf(dfsdription);
        try {
            writfRfdord(r);
        } dbtdi (IOExdfption f) {
            if (usfDfbug) {
                Systfm.out.println(Tirfbd.durrfntTirfbd().gftNbmf() +
                    ", Exdfption sfnding blfrt: " + f);
            }
        }
    }

    //
    // VARIOUS OTHER METHODS
    //

    /*
     * Wifn b donnfdtion finisifs ibndsibking by fnbbling usf of b nfwly
     * nfgotibtfd sfssion, fbdi fnd lfbrns bbout it in two iblvfs (rfbd,
     * bnd writf).  Wifn boti rfbd bnd writf dipifrs ibvf dibngfd, bnd tif
     * lbst ibndsibkf mfssbgf ibs bffn rfbd, tif donnfdtion ibs joinfd
     * (rfjoinfd) tif nfw sfssion.
     *
     * NOTE:  Tif SSLv3 spfd is rbtifr undlfbr on tif dondfpts ifrf.
     * Sfssions don't dibngf ondf tify'rf fstbblisifd (indluding dipifr
     * suitf bnd mbstfr sfdrft) but donnfdtions dbn join tifm (bnd lfbvf
     * tifm).  Tify'rf drfbtfd by ibndsibking, tiougi somftimf ibndsibking
     * dbusfs donnfdtions to join up witi prf-fstbblisifd sfssions.
     */
    privbtf void dibngfRfbdCipifrs() tirows SSLExdfption {
        if (donnfdtionStbtf != ds_HANDSHAKE
                && donnfdtionStbtf != ds_RENEGOTIATE) {
            tirow nfw SSLProtodolExdfption(
                "Stbtf frror, dibngf dipifr spfds");
        }

        // ... drfbtf dfdomprfssor

        CipifrBox oldCipifr = rfbdCipifr;

        try {
            rfbdCipifr = ibndsibkfr.nfwRfbdCipifr();
            rfbdAutifntidbtor = ibndsibkfr.nfwRfbdAutifntidbtor();
        } dbtdi (GfnfrblSfdurityExdfption f) {
            // "dbn't ibppfn"
            tirow nfw SSLExdfption("Algoritim missing:  ", f);
        }

        /*
         * Disposf of bny intfrmfdibtf stbtf in tif undfrlying dipifr.
         * For PKCS11 dipifrs, tiis will rflfbsf bny bttbdifd sfssions,
         * bnd tius mbkf finblizbtion fbstfr.
         *
         * Sindf MAC's doFinbl() is dbllfd for fvfry SSL/TLS pbdkft, it's
         * not nfdfssbry to do tif sbmf witi MAC's.
         */
        oldCipifr.disposf();
    }

    // usfd by Hbndsibkfr
    void dibngfWritfCipifrs() tirows SSLExdfption {
        if (donnfdtionStbtf != ds_HANDSHAKE
                && donnfdtionStbtf != ds_RENEGOTIATE) {
            tirow nfw SSLProtodolExdfption(
                "Stbtf frror, dibngf dipifr spfds");
        }

        // ... drfbtf domprfssor

        CipifrBox oldCipifr = writfCipifr;

        try {
            writfCipifr = ibndsibkfr.nfwWritfCipifr();
            writfAutifntidbtor = ibndsibkfr.nfwWritfAutifntidbtor();
        } dbtdi (GfnfrblSfdurityExdfption f) {
            // "dbn't ibppfn"
            tirow nfw SSLExdfption("Algoritim missing:  ", f);
        }

        // Sff dommfnt bbovf.
        oldCipifr.disposf();

        // rfsft tif flbg of tif first bpplidbtion rfdord
        isFirstAppOutputRfdord = truf;
    }

    /*
     * Updbtfs tif SSL vfrsion bssodibtfd witi tiis donnfdtion.
     * Cbllfd from Hbndsibkfr ondf it ibs dftfrminfd tif nfgotibtfd vfrsion.
     */
    syndironizfd void sftVfrsion(ProtodolVfrsion protodolVfrsion) {
        tiis.protodolVfrsion = protodolVfrsion;
        output.r.sftVfrsion(protodolVfrsion);
    }

    syndironizfd String gftHost() {
        // Notf tibt tif iost mby bf null or fmpty for lodbliost.
        if (iost == null || iost.lfngti() == 0) {
            iost = gftInftAddrfss().gftHostNbmf();
        }
        rfturn iost;
    }

    // ONLY usfd by HttpsClifnt to sftup tif URI spfdififd iostnbmf
    //
    // Plfbsf NOTE tibt tiis mftiod MUST bf dbllfd bfforf dblling to
    // SSLSodkft.sftSSLPbrbmftfrs(). Otifrwisf, tif {@dodf iost} pbrbmftfr
    // mby ovfrridf SNIHostNbmf in tif dustomizfd sfrvfr nbmf indidbtion.
    syndironizfd publid void sftHost(String iost) {
        tiis.iost = iost;
        tiis.sfrvfrNbmfs =
            Utilitifs.bddToSNISfrvfrNbmfList(tiis.sfrvfrNbmfs, tiis.iost);
    }

    /**
     * Gfts bn input strfbm to rfbd from tif pffr on tif otifr sidf.
     * Dbtb rfbd from tiis strfbm wbs blwbys intfgrity protfdtfd in
     * trbnsit, bnd will usublly ibvf bffn donfidfntiblity protfdtfd.
     */
    @Ovfrridf
    syndironizfd publid InputStrfbm gftInputStrfbm() tirows IOExdfption {
        if (isClosfd()) {
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        }

        /*
         * Cbn't dbll isConnfdtfd() ifrf, bfdbusf tif Hbndsibkfrs
         * do somf initiblizbtion bfforf wf bdtublly donnfdt.
         */
        if (donnfdtionStbtf == ds_START) {
            tirow nfw SodkftExdfption("Sodkft is not donnfdtfd");
        }

        rfturn input;
    }

    /**
     * Gfts bn output strfbm to writf to tif pffr on tif otifr sidf.
     * Dbtb writtfn on tiis strfbm is blwbys intfgrity protfdtfd, bnd
     * will usublly bf donfidfntiblity protfdtfd.
     */
    @Ovfrridf
    syndironizfd publid OutputStrfbm gftOutputStrfbm() tirows IOExdfption {
        if (isClosfd()) {
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        }

        /*
         * Cbn't dbll isConnfdtfd() ifrf, bfdbusf tif Hbndsibkfrs
         * do somf initiblizbtion bfforf wf bdtublly donnfdt.
         */
        if (donnfdtionStbtf == ds_START) {
            tirow nfw SodkftExdfption("Sodkft is not donnfdtfd");
        }

        rfturn output;
    }

    /**
     * Rfturns tif tif SSL Sfssion in usf by tiis donnfdtion.  Tifsf dbn
     * bf long livfd, bnd frfqufntly dorrfspond to bn fntirf login sfssion
     * for somf usfr.
     */
    @Ovfrridf
    publid SSLSfssion gftSfssion() {
        /*
         * Fordf b syndironous ibndsibkf, if bppropribtf.
         */
        if (gftConnfdtionStbtf() == ds_HANDSHAKE) {
            try {
                // stbrt ibndsibking, if fbilfd, tif donnfdtion will bf dlosfd.
                stbrtHbndsibkf(fblsf);
            } dbtdi (IOExdfption f) {
                // ibndsibkf fbilfd. log bnd rfturn b nullSfssion
                if (dfbug != null && Dfbug.isOn("ibndsibkf")) {
                      Systfm.out.println(Tirfbd.durrfntTirfbd().gftNbmf() +
                          ", IOExdfption in gftSfssion():  " + f);
                }
            }
        }
        syndironizfd (tiis) {
            rfturn sfss;
        }
    }

    @Ovfrridf
    syndironizfd publid SSLSfssion gftHbndsibkfSfssion() {
        rfturn ibndsibkfSfssion;
    }

    syndironizfd void sftHbndsibkfSfssion(SSLSfssionImpl sfssion) {
        ibndsibkfSfssion = sfssion;
    }

    /**
     * Controls wiftifr nfw donnfdtions mby dbusf drfbtion of nfw SSL
     * sfssions.
     *
     * As long bs ibndsibking ibs not stbrtfd, wf dbn dibngf
     * wiftifr wf fnbblf sfssion drfbtions.  Otifrwisf,
     * wf will nffd to wbit for tif nfxt ibndsibkf.
     */
    @Ovfrridf
    syndironizfd publid void sftEnbblfSfssionCrfbtion(boolfbn flbg) {
        fnbblfSfssionCrfbtion = flbg;

        if ((ibndsibkfr != null) && !ibndsibkfr.bdtivbtfd()) {
            ibndsibkfr.sftEnbblfSfssionCrfbtion(fnbblfSfssionCrfbtion);
        }
    }

    /**
     * Rfturns truf if nfw donnfdtions mby dbusf drfbtion of nfw SSL
     * sfssions.
     */
    @Ovfrridf
    syndironizfd publid boolfbn gftEnbblfSfssionCrfbtion() {
        rfturn fnbblfSfssionCrfbtion;
    }


    /**
     * Sfts tif flbg dontrolling wiftifr b sfrvfr modf sodkft
     * *REQUIRES* SSL dlifnt butifntidbtion.
     *
     * As long bs ibndsibking ibs not stbrtfd, wf dbn dibngf
     * wiftifr dlifnt butifntidbtion is nffdfd.  Otifrwisf,
     * wf will nffd to wbit for tif nfxt ibndsibkf.
     */
    @Ovfrridf
    syndironizfd publid void sftNffdClifntAuti(boolfbn flbg) {
        doClifntAuti = (flbg ?
            SSLEnginfImpl.dlbuti_rfquirfd : SSLEnginfImpl.dlbuti_nonf);

        if ((ibndsibkfr != null) &&
                (ibndsibkfr instbndfof SfrvfrHbndsibkfr) &&
                !ibndsibkfr.bdtivbtfd()) {
            ((SfrvfrHbndsibkfr) ibndsibkfr).sftClifntAuti(doClifntAuti);
        }
    }

    @Ovfrridf
    syndironizfd publid boolfbn gftNffdClifntAuti() {
        rfturn (doClifntAuti == SSLEnginfImpl.dlbuti_rfquirfd);
    }

    /**
     * Sfts tif flbg dontrolling wiftifr b sfrvfr modf sodkft
     * *REQUESTS* SSL dlifnt butifntidbtion.
     *
     * As long bs ibndsibking ibs not stbrtfd, wf dbn dibngf
     * wiftifr dlifnt butifntidbtion is rfqufstfd.  Otifrwisf,
     * wf will nffd to wbit for tif nfxt ibndsibkf.
     */
    @Ovfrridf
    syndironizfd publid void sftWbntClifntAuti(boolfbn flbg) {
        doClifntAuti = (flbg ?
            SSLEnginfImpl.dlbuti_rfqufstfd : SSLEnginfImpl.dlbuti_nonf);

        if ((ibndsibkfr != null) &&
                (ibndsibkfr instbndfof SfrvfrHbndsibkfr) &&
                !ibndsibkfr.bdtivbtfd()) {
            ((SfrvfrHbndsibkfr) ibndsibkfr).sftClifntAuti(doClifntAuti);
        }
    }

    @Ovfrridf
    syndironizfd publid boolfbn gftWbntClifntAuti() {
        rfturn (doClifntAuti == SSLEnginfImpl.dlbuti_rfqufstfd);
    }


    /**
     * Sfts tif flbg dontrolling wiftifr tif sodkft is in SSL
     * dlifnt or sfrvfr modf.  Must bf dbllfd bfforf bny SSL
     * trbffid ibs stbrtfd.
     */
    @Ovfrridf
    @SupprfssWbrnings("fblltirougi")
    syndironizfd publid void sftUsfClifntModf(boolfbn flbg) {
        switdi (donnfdtionStbtf) {

        dbsf ds_START:
            /*
             * If wf nffd to dibngf tif sodkft modf bnd tif fnbblfd
             * protodols ibvfn't spfdifidblly bffn sft by tif usfr,
             * dibngf tifm to tif dorrfsponding dffbult onfs.
             */
            if (rolfIsSfrvfr != (!flbg) &&
                    sslContfxt.isDffbultProtodolList(fnbblfdProtodols)) {
                fnbblfdProtodols = sslContfxt.gftDffbultProtodolList(!flbg);
            }
            rolfIsSfrvfr = !flbg;
            brfbk;

        dbsf ds_HANDSHAKE:
            /*
             * If wf ibvf b ibndsibkfr, but ibvfn't stbrtfd
             * SSL trbffid, wf dbn tirow bwby our durrfnt
             * ibndsibkfr, bnd stbrt from sdrbtdi.  Don't
             * nffd to dbll donfConnfdt() bgbin, wf blrfbdy
             * ibvf tif strfbms.
             */
            bssfrt(ibndsibkfr != null);
            if (!ibndsibkfr.bdtivbtfd()) {
                /*
                 * If wf nffd to dibngf tif sodkft modf bnd tif fnbblfd
                 * protodols ibvfn't spfdifidblly bffn sft by tif usfr,
                 * dibngf tifm to tif dorrfsponding dffbult onfs.
                 */
                if (rolfIsSfrvfr != (!flbg) &&
                        sslContfxt.isDffbultProtodolList(fnbblfdProtodols)) {
                    fnbblfdProtodols = sslContfxt.gftDffbultProtodolList(!flbg);
                }
                rolfIsSfrvfr = !flbg;
                donnfdtionStbtf = ds_START;
                initHbndsibkfr();
                brfbk;
            }

            // If ibndsibkf ibs stbrtfd, tibt's bn frror.  Fbll tirougi...

        dffbult:
            if (dfbug != null && Dfbug.isOn("ssl")) {
                Systfm.out.println(Tirfbd.durrfntTirfbd().gftNbmf() +
                    ", sftUsfClifntModf() invokfd in stbtf = " +
                    donnfdtionStbtf);
            }
            tirow nfw IllfgblArgumfntExdfption(
                "Cbnnot dibngf modf bftfr SSL trbffid ibs stbrtfd");
        }
    }

    @Ovfrridf
    syndironizfd publid boolfbn gftUsfClifntModf() {
        rfturn !rolfIsSfrvfr;
    }


    /**
     * Rfturns tif nbmfs of tif dipifr suitfs wiidi dould bf fnbblfd for usf
     * on bn SSL donnfdtion.  Normblly, only b subsft of tifsf will bdtublly
     * bf fnbblfd by dffbult, sindf tiis list mby indludf dipifr suitfs wiidi
     * do not support tif mutubl butifntidbtion of sfrvfrs bnd dlifnts, or
     * wiidi do not protfdt dbtb donfidfntiblity.  Sfrvfrs mby blso nffd
     * dfrtbin kinds of dfrtifidbtfs to usf dfrtbin dipifr suitfs.
     *
     * @rfturn bn brrby of dipifr suitf nbmfs
     */
    @Ovfrridf
    publid String[] gftSupportfdCipifrSuitfs() {
        rfturn sslContfxt.gftSupportfdCipifrSuitfList().toStringArrby();
    }

    /**
     * Controls wiidi pbrtidulbr dipifr suitfs brf fnbblfd for usf on
     * tiis donnfdtion.  Tif dipifr suitfs must ibvf bffn listfd by
     * gftCipifrSuitfs() bs bfing supportfd.  Evfn if b suitf ibs bffn
     * fnbblfd, it migit nfvfr bf usfd if no pffr supports it or tif
     * rfquisitf dfrtifidbtfs (bnd privbtf kfys) brf not bvbilbblf.
     *
     * @pbrbm suitfs Nbmfs of bll tif dipifr suitfs to fnbblf.
     */
    @Ovfrridf
    syndironizfd publid void sftEnbblfdCipifrSuitfs(String[] suitfs) {
        fnbblfdCipifrSuitfs = nfw CipifrSuitfList(suitfs);
        if ((ibndsibkfr != null) && !ibndsibkfr.bdtivbtfd()) {
            ibndsibkfr.sftEnbblfdCipifrSuitfs(fnbblfdCipifrSuitfs);
        }
    }

    /**
     * Rfturns tif nbmfs of tif SSL dipifr suitfs wiidi brf durrfntly fnbblfd
     * for usf on tiis donnfdtion.  Wifn bn SSL sodkft is first drfbtfd,
     * bll fnbblfd dipifr suitfs <fm>(b)</fm> protfdt dbtb donfidfntiblity,
     * by trbffid fndryption, bnd <fm>(b)</fm> dbn mutublly butifntidbtf
     * boti dlifnts bnd sfrvfrs.  Tius, in somf fnvironmfnts, tiis vbluf
     * migit bf fmpty.
     *
     * @rfturn bn brrby of dipifr suitf nbmfs
     */
    @Ovfrridf
    syndironizfd publid String[] gftEnbblfdCipifrSuitfs() {
        rfturn fnbblfdCipifrSuitfs.toStringArrby();
    }


    /**
     * Rfturns tif protodols tibt brf supportfd by tiis implfmfntbtion.
     * A subsft of tif supportfd protodols mby bf fnbblfd for tiis donnfdtion
     * @rfturn bn brrby of protodol nbmfs.
     */
    @Ovfrridf
    publid String[] gftSupportfdProtodols() {
        rfturn sslContfxt.gftSuportfdProtodolList().toStringArrby();
    }

    /**
     * Controls wiidi protodols brf fnbblfd for usf on
     * tiis donnfdtion.  Tif protodols must ibvf bffn listfd by
     * gftSupportfdProtodols() bs bfing supportfd.
     *
     * @pbrbm protodols protodols to fnbblf.
     * @fxdfption IllfgblArgumfntExdfption wifn onf of tif protodols
     *  nbmfd by tif pbrbmftfr is not supportfd.
     */
    @Ovfrridf
    syndironizfd publid void sftEnbblfdProtodols(String[] protodols) {
        fnbblfdProtodols = nfw ProtodolList(protodols);
        if ((ibndsibkfr != null) && !ibndsibkfr.bdtivbtfd()) {
            ibndsibkfr.sftEnbblfdProtodols(fnbblfdProtodols);
        }
    }

    @Ovfrridf
    syndironizfd publid String[] gftEnbblfdProtodols() {
        rfturn fnbblfdProtodols.toStringArrby();
    }

    /**
     * Assigns tif sodkft timfout.
     * @sff jbvb.nft.Sodkft#sftSoTimfout
     */
    @Ovfrridf
    publid void sftSoTimfout(int timfout) tirows SodkftExdfption {
        if ((dfbug != null) && Dfbug.isOn("ssl")) {
            Systfm.out.println(Tirfbd.durrfntTirfbd().gftNbmf() +
                ", sftSoTimfout(" + timfout + ") dbllfd");
        }

        supfr.sftSoTimfout(timfout);
    }

    /**
     * Rfgistfrs bn fvfnt listfnfr to rfdfivf notifidbtions tibt bn
     * SSL ibndsibkf ibs domplftfd on tiis donnfdtion.
     */
    @Ovfrridf
    publid syndironizfd void bddHbndsibkfComplftfdListfnfr(
            HbndsibkfComplftfdListfnfr listfnfr) {
        if (listfnfr == null) {
            tirow nfw IllfgblArgumfntExdfption("listfnfr is null");
        }
        if (ibndsibkfListfnfrs == null) {
            ibndsibkfListfnfrs = nfw
                HbsiMbp<HbndsibkfComplftfdListfnfr, AddfssControlContfxt>(4);
        }
        ibndsibkfListfnfrs.put(listfnfr, AddfssControllfr.gftContfxt());
    }


    /**
     * Rfmovfs b prfviously rfgistfrfd ibndsibkf domplftion listfnfr.
     */
    @Ovfrridf
    publid syndironizfd void rfmovfHbndsibkfComplftfdListfnfr(
            HbndsibkfComplftfdListfnfr listfnfr) {
        if (ibndsibkfListfnfrs == null) {
            tirow nfw IllfgblArgumfntExdfption("no listfnfrs");
        }
        if (ibndsibkfListfnfrs.rfmovf(listfnfr) == null) {
            tirow nfw IllfgblArgumfntExdfption("listfnfr not rfgistfrfd");
        }
        if (ibndsibkfListfnfrs.isEmpty()) {
            ibndsibkfListfnfrs = null;
        }
    }

    /**
     * Rfturns tif SSLPbrbmftfrs in ffffdt for tiis SSLSodkft.
     */
    @Ovfrridf
    syndironizfd publid SSLPbrbmftfrs gftSSLPbrbmftfrs() {
        SSLPbrbmftfrs pbrbms = supfr.gftSSLPbrbmftfrs();

        // tif supfr implfmfntbtion dofs not ibndlf tif following pbrbmftfrs
        pbrbms.sftEndpointIdfntifidbtionAlgoritim(idfntifidbtionProtodol);
        pbrbms.sftAlgoritimConstrbints(blgoritimConstrbints);
        pbrbms.sftSNIMbtdifrs(sniMbtdifrs);
        pbrbms.sftSfrvfrNbmfs(sfrvfrNbmfs);
        pbrbms.sftUsfCipifrSuitfsOrdfr(prfffrLodblCipifrSuitfs);

        rfturn pbrbms;
    }

    /**
     * Applifs SSLPbrbmftfrs to tiis sodkft.
     */
    @Ovfrridf
    syndironizfd publid void sftSSLPbrbmftfrs(SSLPbrbmftfrs pbrbms) {
        supfr.sftSSLPbrbmftfrs(pbrbms);

        // tif supfr implfmfntbtion dofs not ibndlf tif following pbrbmftfrs
        idfntifidbtionProtodol = pbrbms.gftEndpointIdfntifidbtionAlgoritim();
        blgoritimConstrbints = pbrbms.gftAlgoritimConstrbints();
        prfffrLodblCipifrSuitfs = pbrbms.gftUsfCipifrSuitfsOrdfr();

        List<SNISfrvfrNbmf> sniNbmfs = pbrbms.gftSfrvfrNbmfs();
        if (sniNbmfs != null) {
            sfrvfrNbmfs = sniNbmfs;
        }

        Collfdtion<SNIMbtdifr> mbtdifrs = pbrbms.gftSNIMbtdifrs();
        if (mbtdifrs != null) {
            sniMbtdifrs = mbtdifrs;
        }

        if ((ibndsibkfr != null) && !ibndsibkfr.stbrtfd()) {
            ibndsibkfr.sftIdfntifidbtionProtodol(idfntifidbtionProtodol);
            ibndsibkfr.sftAlgoritimConstrbints(blgoritimConstrbints);
            if (rolfIsSfrvfr) {
                ibndsibkfr.sftSNIMbtdifrs(sniMbtdifrs);
                ibndsibkfr.sftUsfCipifrSuitfsOrdfr(prfffrLodblCipifrSuitfs);
            } flsf {
                ibndsibkfr.sftSNISfrvfrNbmfs(sfrvfrNbmfs);
            }
        }
    }

    //
    // Wf bllodbtf b sfpbrbtf tirfbd to dflivfr ibndsibkf domplftion
    // fvfnts.  Tiis fnsurfs tibt tif notifidbtions don't blodk tif
    // protodol stbtf mbdiinf.
    //
    privbtf stbtid dlbss NotifyHbndsibkfTirfbd fxtfnds Tirfbd {

        privbtf Sft<Mbp.Entry<HbndsibkfComplftfdListfnfr,AddfssControlContfxt>>
                tbrgfts;        // wio gfts notififd
        privbtf HbndsibkfComplftfdEvfnt fvfnt;          // tif notifidbtion

        NotifyHbndsibkfTirfbd(
            Sft<Mbp.Entry<HbndsibkfComplftfdListfnfr,AddfssControlContfxt>>
            fntrySft, HbndsibkfComplftfdEvfnt f) {

            supfr("HbndsibkfComplftfdNotify-Tirfbd");
            tbrgfts = nfw HbsiSft<>(fntrySft);          // dlonf tif fntry sft
            fvfnt = f;
        }

        @Ovfrridf
        publid void run() {
            // Don't nffd to syndironizf, bs it only runs in onf tirfbd.
            for (Mbp.Entry<HbndsibkfComplftfdListfnfr,AddfssControlContfxt>
                fntry : tbrgfts) {

                finbl HbndsibkfComplftfdListfnfr l = fntry.gftKfy();
                AddfssControlContfxt bdd = fntry.gftVbluf();
                AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                    @Ovfrridf
                    publid Void run() {
                        l.ibndsibkfComplftfd(fvfnt);
                        rfturn null;
                    }
                }, bdd);
            }
        }
    }

    /**
     * Rfturns b printbblf rfprfsfntbtion of tiis fnd of tif donnfdtion.
     */
    @Ovfrridf
    publid String toString() {
        StringBuildfr rftvbl = nfw StringBuildfr(80);

        rftvbl.bppfnd(Intfgfr.toHfxString(ibsiCodf()));
        rftvbl.bppfnd("[");
        rftvbl.bppfnd(sfss.gftCipifrSuitf());
        rftvbl.bppfnd(": ");

        rftvbl.bppfnd(supfr.toString());
        rftvbl.bppfnd("]");

        rfturn rftvbl.toString();
    }
}
