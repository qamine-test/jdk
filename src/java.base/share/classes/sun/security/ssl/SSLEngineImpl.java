/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.nio.*;
import jbvb.util.*;
import jbvb.sfdurity.*;

import jbvbx.drypto.BbdPbddingExdfption;

import jbvbx.nft.ssl.*;
import jbvbx.nft.ssl.SSLEnginfRfsult.*;

/**
 * Implfmfntbtion of bn non-blodking SSLEnginf.
 *
 * *Currfntly*, tif SSLEnginf dodf fxists in pbrbllfl witi tif durrfnt
 * SSLSodkft.  As sudi, tif durrfnt implfmfntbtion is using lfgbdy dodf
 * witi mbny of tif sbmf bbstrbdtions.  Howfvfr, it vbrifs in mbny
 * brfbs, most drbmbtidblly in tif IO ibndling.
 *
 * Tifrf brf tirff mbin I/O tirfbds tibt dbn bf fxisting in pbrbllfl:
 * wrbp(), unwrbp(), bnd bfginHbndsibkf().  Wf brf fndourbging usfrs to
 * not dbll multiplf instbndfs of wrbp or unwrbp, bfdbusf tif dbtb dould
 * bppfbr to flow out of tif SSLEnginf in b non-sfqufntibl ordfr.  Wf
 * tbkf bll stfps wf dbn to bt lfbst mbkf surf tif ordfring rfmbins
 * donsistfnt, but ondf tif dblls rfturns, bnytiing dbn ibppfn.  For
 * fxbmplf, tirfbd1 bnd tirfbd2 boti dbll wrbp, tirfbd1 gfts tif first
 * pbdkft, tirfbd2 gfts tif sfdond pbdkft, but tirfbd2 gfts dontrol bbdk
 * bfforf tirfbd1, bnd sfnds tif dbtb.  Tif rfdfiving sidf would sff bn
 * out-of-ordfr frror.
 *
 * Hbndsibking is still donf tif sbmf wby bs SSLSodkft using tif normbl
 * InputStrfbm/OutputStrfbm bbstbdtions.  Wf drfbtf
 * ClifntHbndsibkfrs/SfrvfrHbndsibkfrs, wiidi produdf/donsumf tif
 * ibndsibking dbtb.  Tif trbnsffr of tif dbtb is lbrgfly ibndlfd by tif
 * HbndsibkfInStrfbm/HbndsibkfOutStrfbms.  Lbstly, tif
 * InputRfdord/OutputRfdords still ibvf tif sbmf fundtionblity, fxdfpt
 * tibt tify brf ovfrriddfn witi EnginfInputRfdord/EnginfOutputRfdord,
 * wiidi providf SSLEnginf-spfdifid fundtionblity.
 *
 * Somf of tif mbjor difffrfndfs brf:
 *
 * EnginfInputRfdord/EnginfOutputRfdord/EnginfWritfr:
 *
 *      In ordfr to bvoid writing wiolf nfw dontrol flows for
 *      ibndsibking, bnd to rfusf most of tif sbmf dodf, wf kfpt most
 *      of tif bdtubl ibndsibkf dodf tif sbmf.  As usubl, rfbding
 *      ibndsibkf dbtb mby triggfr output of morf ibndsibkf dbtb, so
 *      wibt wf do is writf tiis dbtb to intfrnbl bufffrs, bnd wbit for
 *      wrbp() to bf dbllfd to givf tibt dbtb b ridf.
 *
 *      All dbtb is routfd tirougi
 *      EnginfInputRfdord/EnginfOutputRfdord.  Howfvfr, bll ibndsibkf
 *      dbtb (dt_blfrt/dt_dibngf_dipifr_spfd/dt_ibndsibkf) brf pbssfd
 *      tirougi to tif tif undfrlying InputRfdord/OutputRfdord, bnd
 *      tif dbtb usfs tif intfrnbl bufffrs.
 *
 *      Applidbtion dbtb is ibndlfd sligitly difffrfnt, wf dopy tif dbtb
 *      dirfdtly from tif srd to tif dst bufffrs, bnd do bll opfrbtions
 *      on tiosf bufffrs, sbving tif ovfrifbd of multiplf dopifs.
 *
 *      In tif dbsf of bn inbound rfdord, unwrbp pbssfs tif inbound
 *      BytfBufffr to tif InputRfdord.  If tif dbtb is ibndsibkf dbtb,
 *      tif dbtb is rfbd into tif InputRfdord's intfrnbl bufffr.  If
 *      tif dbtb is bpplidbtion dbtb, tif dbtb is dfdodfd dirfdtly into
 *      tif dst bufffr.
 *
 *      In tif dbsf of bn outbound rfdord, wifn tif writf to tif
 *      "rfbl" OutputStrfbm's would normblly tbkf plbdf, instfbd wf
 *      dbll bbdk up to tif EnginfOutputRfdord's vfrsion of
 *      writfBufffr, bt wiidi timf wf dbpturf tif rfsulting output in b
 *      BytfBufffr, bnd sfnd tibt bbdk to tif EnginfWritfr for intfrnbl
 *      storbgf.
 *
 *      EnginfWritfr is rfsponsiblf for "ibndling" bll outbound
 *      dbtb, bf it ibndsibkf or bpp dbtb, bnd for rfturning tif dbtb
 *      to wrbp() in tif propfr ordfr.
 *
 * ClifntHbndsibkfr/SfrvfrHbndsibkfr/Hbndsibkfr:
 *      Mftiods wiidi rflifd on SSLSodkft now ibvf work on fitifr
 *      SSLSodkfts or SSLEnginfs.
 *
 * @butior Brbd Wftmorf
 */
finbl publid dlbss SSLEnginfImpl fxtfnds SSLEnginf {

    //
    // Fiflds bnd globbl dommfnts
    //

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
     * - CLOSED wifn onf sidf dlosfs down, usfd to stbrt tif siutdown
     *          prodfss.  SSL donnfdtion objfdts brf not rfusfd.
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
     *     <-----<    ^            ^  <-----<               |
     *START>----->HANDSHAKE>----->DATA>----->RENEGOTIATE    |
     *                v            v               v        |
     *                |            |               |        |
     *                +------------+---------------+        |
     *                |                                     |
     *                v                                     |
     *               ERROR>------>----->CLOSED<--------<----+
     *
     * ALSO, notf tibt tif tif purposf of ibndsibking (rfnfgotibtion is
     * indludfd) is to bssign b difffrfnt, bnd pfribps nfw, sfssion to
     * tif donnfdtion.  Tif SSLv3 spfd is b bit donfusing on tibt nfw
     * protodol ffbturf.
     */
    privbtf int                 donnfdtionStbtf;

    privbtf stbtid finbl int    ds_START = 0;
    privbtf stbtid finbl int    ds_HANDSHAKE = 1;
    privbtf stbtid finbl int    ds_DATA = 2;
    privbtf stbtid finbl int    ds_RENEGOTIATE = 3;
    privbtf stbtid finbl int    ds_ERROR = 4;
    privbtf stbtid finbl int    ds_CLOSED = 6;

    /*
     * Ondf wf'rf in stbtf ds_CLOSED, wf dbn dontinuf to
     * wrbp/unwrbp until wf finisi sfnding/rfdfiving tif mfssbgfs
     * for dlosf_notify.  EnginfWritfr ibndlfs outboundDonf.
     */
    privbtf boolfbn             inboundDonf = fblsf;

    EnginfWritfr                writfr;

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
     * Clifnt butifntidbtion bf off, rfqufstfd, or rfquirfd.
     *
     * Tiis will bf usfd by boti tiis dlbss bnd SSLSodkft's vbribnts.
     */
    stbtid finbl bytf           dlbuti_nonf = 0;
    stbtid finbl bytf           dlbuti_rfqufstfd = 1;
    stbtid finbl bytf           dlbuti_rfquirfd = 2;

    /*
     * Flbg indidbting if tif nfxt rfdord wf rfdfivf MUST bf b Finisifd
     * mfssbgf. Tfmporbrily sft during tif ibndsibkf to fnsurf tibt
     * b dibngf dipifr spfd mfssbgf is followfd by b finisifd mfssbgf.
     */
    privbtf boolfbn             fxpfdtingFinisifd;


    /*
     * If somfonf trifs to dlosfInbound() (sby bt End-Of-Strfbm)
     * our fnginf ibving rfdfivfd b dlosf_notify, wf nffd to
     * notify tif bpp tibt wf mby ibvf b trundbtion bttbdk undfrwby.
     */
    privbtf boolfbn             rfdvCN;

    /*
     * For improvfd dibgnostids, wf dftbil donnfdtion dlosurf
     * If tif fnginf is dlosfd (donnfdtionStbtf >= ds_ERROR),
     * dlosfRfbson != null indidbtfs if tif fnginf wbs dlosfd
     * bfdbusf of bn frror or bfdbusf or normbl siutdown.
     */
    privbtf SSLExdfption        dlosfRfbson;

    /*
     * Pfr-donnfdtion privbtf stbtf tibt dofsn't dibngf wifn tif
     * sfssion is dibngfd.
     */
    privbtf bytf                        doClifntAuti;
    privbtf boolfbn                     fnbblfSfssionCrfbtion = truf;
    EnginfInputRfdord                   inputRfdord;
    EnginfOutputRfdord                  outputRfdord;
    privbtf AddfssControlContfxt        bdd;

    // Tif dipifr suitfs fnbblfd for usf on tiis donnfdtion.
    privbtf CipifrSuitfList             fnbblfdCipifrSuitfs;

    // tif fndpoint idfntifidbtion protodol
    privbtf String                      idfntifidbtionProtodol = null;

    // Tif dryptogrbpiid blgoritim donstrbints
    privbtf AlgoritimConstrbints        blgoritimConstrbints = null;

    // Tif sfrvfr nbmf indidbtion bnd mbtdifrs
    List<SNISfrvfrNbmf>         sfrvfrNbmfs =
                                    Collfdtions.<SNISfrvfrNbmf>fmptyList();
    Collfdtion<SNIMbtdifr>      sniMbtdifrs =
                                    Collfdtions.<SNIMbtdifr>fmptyList();

    // Hbvf wf bffn told wiftifr wf'rf dlifnt or sfrvfr?
    privbtf boolfbn                     sfrvfrModfSft = fblsf;
    privbtf boolfbn                     rolfIsSfrvfr;

    /*
     * Tif protodol vfrsions fnbblfd for usf on tiis donnfdtion.
     *
     * Notf: wf support b psfudo protodol dbllfd SSLv2Hfllo wiidi wifn
     * sft will rfsult in bn SSL v2 Hfllo bfing sfnt witi SSL (vfrsion 3.0)
     * or TLS (vfrsion 3.1, 3.2, ftd.) vfrsion info.
     */
    privbtf ProtodolList        fnbblfdProtodols;

    /*
     * Tif SSL vfrsion bssodibtfd witi tiis donnfdtion.
     */
    privbtf ProtodolVfrsion     protodolVfrsion = ProtodolVfrsion.DEFAULT;

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
     * Notf tibt wf must nfvfr bdquirf tif <dodf>tiis</dodf> lodk bftfr
     * <dodf>writfLodk</dodf> or run tif risk of dfbdlodk.
     *
     * Grbb somf doffff, bnd bf dbrfful witi bny dodf dibngfs.
     */
    privbtf Objfdt              wrbpLodk;
    privbtf Objfdt              unwrbpLodk;
    Objfdt                      writfLodk;

    /*
     * Is it tif first bpplidbtion rfdord to writf?
     */
    privbtf boolfbn isFirstAppOutputRfdord = truf;

    /*
     * Wiftifr lodbl dipifr suitfs prfffrfndf in sfrvfr sidf siould bf
     * ionorfd during ibndsibking?
     */
    privbtf boolfbn prfffrLodblCipifrSuitfs = fblsf;

    /*
     * Clbss bnd subdlbss dynbmid dfbugging support
     */
    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("ssl");

    //
    // Initiblizbtion/Construdtors
    //

    /**
     * Construdtor for bn SSLEnginf from SSLContfxt, witiout
     * iost/port iints.  Tiis Enginf will not bf bblf to dbdif
     * sfssions, but must rfnfgotibtf fvfrytiing by ibnd.
     */
    SSLEnginfImpl(SSLContfxtImpl dtx) {
        supfr();
        init(dtx);
    }

    /**
     * Construdtor for bn SSLEnginf from SSLContfxt.
     */
    SSLEnginfImpl(SSLContfxtImpl dtx, String iost, int port) {
        supfr(iost, port);
        init(dtx);
    }

    /**
     * Initiblizfs tif Enginf
     */
    privbtf void init(SSLContfxtImpl dtx) {
        if (dfbug != null && Dfbug.isOn("ssl")) {
            Systfm.out.println("Using SSLEnginfImpl.");
        }

        sslContfxt = dtx;
        sfss = SSLSfssionImpl.nullSfssion;
        ibndsibkfSfssion = null;

        /*
         * Stbtf is ds_START until wf initiblizf tif ibndsibkfr.
         *
         * Apps using SSLEnginf brf probbbly going to bf sfrvfr.
         * Somfwibt brbitrbry dioidf.
         */
        rolfIsSfrvfr = truf;
        donnfdtionStbtf = ds_START;

        // dffbult sfrvfr nbmf indidbtion
        sfrvfrNbmfs =
            Utilitifs.bddToSNISfrvfrNbmfList(sfrvfrNbmfs, gftPffrHost());

        /*
         * dffbult rfbd bnd writf sidf dipifr bnd MAC support
         *
         * Notf:  domprfssion support would go ifrf too
         */
        rfbdCipifr = CipifrBox.NULL;
        rfbdAutifntidbtor = MAC.NULL;
        writfCipifr = CipifrBox.NULL;
        writfAutifntidbtor = MAC.NULL;

        // dffbult sfdurity pbrbmftfrs for sfdurf rfnfgotibtion
        sfdurfRfnfgotibtion = fblsf;
        dlifntVfrifyDbtb = nfw bytf[0];
        sfrvfrVfrifyDbtb = nfw bytf[0];

        fnbblfdCipifrSuitfs =
                sslContfxt.gftDffbultCipifrSuitfList(rolfIsSfrvfr);
        fnbblfdProtodols =
                sslContfxt.gftDffbultProtodolList(rolfIsSfrvfr);

        wrbpLodk = nfw Objfdt();
        unwrbpLodk = nfw Objfdt();
        writfLodk = nfw Objfdt();

        /*
         * Sbvf tif Addfss Control Contfxt.  Tiis will bf usfd lbtfr
         * for b douplf of tiings, indluding providing b dontfxt to
         * run tbsks in, bnd for dftfrmining wiidi drfdfntibls
         * to usf for Subjfdt bbsfd (JAAS) dfdisions
         */
        bdd = AddfssControllfr.gftContfxt();

        /*
         * All outbound bpplidbtion dbtb gofs tirougi tiis OutputRfdord,
         * otifr dbtb gofs tirougi tifir rfspfdtivf rfdords drfbtfd
         * flsfwifrf.  All inbound dbtb gofs tirougi tiis onf
         * input rfdord.
         */
        outputRfdord =
            nfw EnginfOutputRfdord(Rfdord.dt_bpplidbtion_dbtb, tiis);
        inputRfdord = nfw EnginfInputRfdord(tiis);
        inputRfdord.fnbblfFormbtCifdks();

        writfr = nfw EnginfWritfr();
    }

    /**
     * Initiblizf tif ibndsibkfr objfdt. Tiis mfbns:
     *
     *  . if b ibndsibkf is blrfbdy in progrfss (stbtf is ds_HANDSHAKE
     *    or ds_RENEGOTIATE), do notiing bnd rfturn
     *
     *  . if tif fnginf is blrfbdy dlosfd, tirow bn Exdfption (intfrnbl frror)
     *
     *  . otifrwisf (ds_START or ds_DATA), drfbtf tif bppropribtf ibndsibkfr
     *    objfdt bnd bdvbndf tif donnfdtion stbtf (to ds_HANDSHAKE or
     *    ds_RENEGOTIATE, rfspfdtivfly).
     *
     * Tiis mftiod is dbllfd rigit bftfr b nfw fnginf is drfbtfd, wifn
     * stbrting rfnfgotibtion, or wifn dibnging dlifnt/sfrvfr modf of tif
     * fnginf.
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

    /*
     * Rfport tif durrfnt stbtus of tif Hbndsibkfr
     */
    privbtf HbndsibkfStbtus gftHSStbtus(HbndsibkfStbtus iss) {

        if (iss != null) {
            rfturn iss;
        }

        syndironizfd (tiis) {
            if (writfr.ibsOutboundDbtb()) {
                rfturn HbndsibkfStbtus.NEED_WRAP;
            } flsf if (ibndsibkfr != null) {
                if (ibndsibkfr.tbskOutstbnding()) {
                    rfturn HbndsibkfStbtus.NEED_TASK;
                } flsf {
                    rfturn HbndsibkfStbtus.NEED_UNWRAP;
                }
            } flsf if (donnfdtionStbtf == ds_CLOSED) {
                /*
                 * Spfdibl dbsf wifrf wf'rf dlosing, but
                 * still nffd tif dlosf_notify bfforf wf
                 * dbn offidiblly bf dlosfd.
                 *
                 * Notf isOutboundDonf is tbkfn dbrf of by
                 * ibsOutboundDbtb() bbovf.
                 */
                if (!isInboundDonf()) {
                    rfturn HbndsibkfStbtus.NEED_UNWRAP;
                } // flsf not ibndsibking
            }

            rfturn HbndsibkfStbtus.NOT_HANDSHAKING;
        }
    }

    syndironizfd privbtf void difdkTbskTirown() tirows SSLExdfption {
        if (ibndsibkfr != null) {
            ibndsibkfr.difdkTirown();
        }
    }

    //
    // Hbndsibking bnd donnfdtion stbtf dodf
    //

    /*
     * Providfs "tiis" syndironizbtion for donnfdtion stbtf.
     * Otifrwisf, you dbn bddfss it dirfdtly.
     */
    syndironizfd privbtf int gftConnfdtionStbtf() {
        rfturn donnfdtionStbtf;
    }

    syndironizfd privbtf void sftConnfdtionStbtf(int stbtf) {
        donnfdtionStbtf = stbtf;
    }

    /*
     * Gft tif Addfss Control Contfxt.
     *
     * Usfd for b known dontfxt to
     * run tbsks in, bnd for dftfrmining wiidi drfdfntibls
     * to usf for Subjfdt-bbsfd (JAAS) dfdisions.
     */
    AddfssControlContfxt gftAdd() {
        rfturn bdd;
    }

    /*
     * Is b ibndsibkf durrfntly undfrwby?
     */
    @Ovfrridf
    publid SSLEnginfRfsult.HbndsibkfStbtus gftHbndsibkfStbtus() {
        rfturn gftHSStbtus(null);
    }

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
     *
     * Syndironizfd on "tiis" from rfbdRfdord.
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

    /*
     * usfd by Hbndsibkfr to dibngf tif bdtivf writf dipifr, follows
     * tif output of tif CCS mfssbgf.
     *
     * Also syndironizfd on "tiis" from rfbdRfdord/dflfgbtfdTbsk.
     */
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
        outputRfdord.sftVfrsion(protodolVfrsion);
    }


    /**
     * Kidkstbrt tif ibndsibkf if it is not blrfbdy in progrfss.
     * Tiis mfbns:
     *
     *  . if ibndsibking is blrfbdy undfrwby, do notiing bnd rfturn
     *
     *  . if tif fnginf is not donnfdtfd or blrfbdy dlosfd, tirow bn
     *    Exdfption.
     *
     *  . otifrwisf, dbll initHbndsibkf() to initiblizf tif ibndsibkfr
     *    objfdt bnd progrfss tif stbtf. Tifn, sfnd tif initibl
     *    ibndsibking mfssbgf if bppropribtf (blwbys on dlifnts bnd
     *    on sfrvfrs wifn rfnfgotibting).
     */
    privbtf syndironizfd void kidkstbrtHbndsibkf() tirows IOExdfption {
        switdi (donnfdtionStbtf) {

        dbsf ds_START:
            if (!sfrvfrModfSft) {
                tirow nfw IllfgblStbtfExdfption(
                    "Clifnt/Sfrvfr modf not yft sft.");
            }
            initHbndsibkfr();
            brfbk;

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

        dffbult:
            // ds_ERROR/ds_CLOSED
            tirow nfw SSLExdfption("SSLEnginf is dlosing/dlosfd");
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
            } flsf {    // instbndfof SfrvfrHbndsibkfr
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

    /*
     * Stbrt b SSLEnginf ibndsibkf
     */
    @Ovfrridf
    publid void bfginHbndsibkf() tirows SSLExdfption {
        try {
            kidkstbrtHbndsibkf();
        } dbtdi (Exdfption f) {
            fbtbl(Alfrts.blfrt_ibndsibkf_fbilurf,
                "Couldn't kidkstbrt ibndsibking", f);
        }
    }


    //
    // Rfbd/unwrbp sidf
    //


    /**
     * Unwrbps b bufffr.  Dofs b vbrifty of difdks bfforf grbbbing
     * tif unwrbpLodk, wiidi blodks multiplf unwrbps from oddurring.
     */
    @Ovfrridf
    publid SSLEnginfRfsult unwrbp(BytfBufffr nftDbtb, BytfBufffr [] bppDbtb,
            int offsft, int lfngti) tirows SSLExdfption {

        EnginfArgs fb = nfw EnginfArgs(nftDbtb, bppDbtb, offsft, lfngti);

        try {
            syndironizfd (unwrbpLodk) {
                rfturn rfbdNftRfdord(fb);
            }
        } dbtdi (Exdfption f) {
            /*
             * Don't rfsft position so it looks likf wf didn't
             * donsumf bnytiing.  Wf did donsumf somftiing, bnd it
             * got us into tiis situbtion, so rfport tibt mudi bbdk.
             * Our dbys of donsuming brf now ovfr bnywby.
             */
            fbtbl(Alfrts.blfrt_intfrnbl_frror,
                "problfm unwrbpping nft rfdord", f);
            rfturn null;  // mbkf dompilfr ibppy
        } finblly {
            /*
             * Just in dbsf somftiing fbilfd to rfsft limits propfrly.
             */
            fb.rfsftLim();
        }
    }

    /*
     * Mbkfs bdditionbl difdks for unwrbp, but tiis timf morf
     * spfdifid to tiis pbdkft bnd tif durrfnt stbtf of tif mbdiinf.
     */
    privbtf SSLEnginfRfsult rfbdNftRfdord(EnginfArgs fb) tirows IOExdfption {

        Stbtus stbtus = null;
        HbndsibkfStbtus isStbtus = null;

        /*
         * Sff if tif ibndsibkfr nffds to rfport bbdk somf SSLExdfption.
         */
        difdkTbskTirown();

        /*
         * Cifdk if wf brf dlosing/dlosfd.
         */
        if (isInboundDonf()) {
            rfturn nfw SSLEnginfRfsult(Stbtus.CLOSED, gftHSStbtus(null), 0, 0);
        }

        /*
         * If wf'rf still in ds_HANDSHAKE, mbkf surf it's bffn
         * stbrtfd.
         */
        syndironizfd (tiis) {
            if ((donnfdtionStbtf == ds_HANDSHAKE) ||
                    (donnfdtionStbtf == ds_START)) {
                kidkstbrtHbndsibkf();

                /*
                 * If tifrf's still outbound dbtb to flusi, wf
                 * dbn rfturn witiout trying to unwrbp bnytiing.
                 */
                isStbtus = gftHSStbtus(null);

                if (isStbtus == HbndsibkfStbtus.NEED_WRAP) {
                    rfturn nfw SSLEnginfRfsult(Stbtus.OK, isStbtus, 0, 0);
                }
            }
        }

        /*
         * Grbb b dopy of tiis if it dofsn't blrfbdy fxist,
         * bnd wf dbn usf it sfvfrbl plbdfs bfforf bnytiing mbjor
         * ibppfns on tiis sidf.  Rbdfs brfn't dritidbl
         * ifrf.
         */
        if (isStbtus == null) {
            isStbtus = gftHSStbtus(null);
        }

        /*
         * If wf ibvf b tbsk outstbnding, tiis *MUST* bf donf bfforf
         * doing bny morf unwrbpping, bfdbusf wf dould bf in tif middlf
         * of rfdfiving b ibndsibkf mfssbgf, for fxbmplf, b finisifd
         * mfssbgf wiidi would dibngf tif dipifrs.
         */
        if (isStbtus == HbndsibkfStbtus.NEED_TASK) {
            rfturn nfw SSLEnginfRfsult(
                Stbtus.OK, isStbtus, 0, 0);
        }

        /*
         * Cifdk tif pbdkft to mbkf surf fnougi is ifrf.
         * Tiis will blso indirfdtly difdk for 0 lfn pbdkfts.
         */
        int pbdkftLfn = inputRfdord.bytfsInComplftfPbdkft(fb.nftDbtb);

        // Is tiis pbdkft biggfr tibn SSL/TLS normblly bllows?
        if (pbdkftLfn > sfss.gftPbdkftBufffrSizf()) {
            if (pbdkftLfn > Rfdord.mbxLbrgfRfdordSizf) {
                tirow nfw SSLProtodolExdfption(
                    "Input SSL/TLS rfdord too big: mbx = " +
                    Rfdord.mbxLbrgfRfdordSizf +
                    " lfn = " + pbdkftLfn);
            } flsf {
                // Expbnd tif fxpfdtfd mbximum pbdkft/bpplidbtion bufffr
                // sizfs.
                sfss.fxpbndBufffrSizfs();
            }
        }

        /*
         * Cifdk for OVERFLOW.
         *
         * To bf donsidfrfd: Wf dould dflby fnfording tif bpplidbtion bufffr
         * frff spbdf rfquirfmfnt until bftfr tif initibl ibndsibking.
         */
        if ((pbdkftLfn - Rfdord.ifbdfrSizf) > fb.gftAppRfmbining()) {
            rfturn nfw SSLEnginfRfsult(Stbtus.BUFFER_OVERFLOW, isStbtus, 0, 0);
        }

        // difdk for UNDERFLOW.
        if ((pbdkftLfn == -1) || (fb.nftDbtb.rfmbining() < pbdkftLfn)) {
            rfturn nfw SSLEnginfRfsult(
                Stbtus.BUFFER_UNDERFLOW, isStbtus, 0, 0);
        }

        /*
         * Wf'rf now rfbdy to bdtublly do tif rfbd.
         * Tif only rfsult dodf wf rfblly nffd to bf fxbdtly
         * rigit is tif HS finisifd, for signbling to
         * HbndsibkfComplftfdListfnfrs.
         */
        try {
            isStbtus = rfbdRfdord(fb);
        } dbtdi (SSLExdfption f) {
            tirow f;
        } dbtdi (IOExdfption f) {
            tirow nfw SSLExdfption("rfbdRfdord", f);
        }

        /*
         * Cifdk tif vbrious dondition tibt wf dould bf rfporting.
         *
         * It's *possiblf* somftiing migit ibvf ibppfnfd bftwffn tif
         * bbovf bnd now, but it wbs bfttfr to minimblly lodk "tiis"
         * during tif rfbd prodfss.  Wf'll rfturn tif durrfnt
         * stbtus, wiidi is morf rfprfsfntbtivf of tif durrfnt stbtf.
         *
         * stbtus bbovf siould dovfr:  FINISHED, NEED_TASK
         */
        stbtus = (isInboundDonf() ? Stbtus.CLOSED : Stbtus.OK);
        isStbtus = gftHSStbtus(isStbtus);

        rfturn nfw SSLEnginfRfsult(stbtus, isStbtus,
            fb.dfltbNft(), fb.dfltbApp());
    }

    /*
     * Adtublly do tif rfbd rfdord prodfssing.
     *
     * Rfturns b Stbtus if it dbn mbkf spfdifid dftfrminbtions
     * of tif fnginf stbtf.  In pbrtidulbr, wf nffd to signbl
     * tibt b ibndsibkf just domplftfd.
     *
     * It would bf nidf to bf symmftridbl witi tif writf sidf bnd movf
     * tif mbjority of tiis to EnginfInputRfdord, but tifrf's too mudi
     * SSLEnginf stbtf to do tibt dlfbnly.  It must still livf ifrf.
     */
    privbtf HbndsibkfStbtus rfbdRfdord(EnginfArgs fb) tirows IOExdfption {

        HbndsibkfStbtus isStbtus = null;

        /*
         * Tif vbrious opfrbtions will rfturn nfw slidfd BB's,
         * tiis will bvoid ibving to worry bbout positions bnd
         * limits in tif nftBB.
         */
        BytfBufffr rfbdBB = null;
        BytfBufffr dfdryptfdBB = null;

        if (gftConnfdtionStbtf() != ds_ERROR) {

            /*
             * Rfbd b rfdord ... mbybf fmitting bn blfrt if wf gft b
             * domprfifnsiblf but unsupportfd "ifllo" mfssbgf during
             * formbt difdking (f.g. V2).
             */
            try {
                rfbdBB = inputRfdord.rfbd(fb.nftDbtb);
            } dbtdi (IOExdfption f) {
                fbtbl(Alfrts.blfrt_unfxpfdtfd_mfssbgf, f);
            }

            /*
             * Tif bbsid SSLv3 rfdord protfdtion involvfs (optionbl)
             * fndryption for privbdy, bnd bn intfgrity difdk fnsuring
             * dbtb origin butifntidbtion.  Wf do tifm boti ifrf, bnd
             * tirow b fbtbl blfrt if tif intfgrity difdk fbils.
             */
            try {
                dfdryptfdBB = inputRfdord.dfdrypt(
                                    rfbdAutifntidbtor, rfbdCipifr, rfbdBB);
            } dbtdi (BbdPbddingExdfption f) {
                bytf blfrtTypf = (inputRfdord.dontfntTypf() ==
                    Rfdord.dt_ibndsibkf) ?
                        Alfrts.blfrt_ibndsibkf_fbilurf :
                        Alfrts.blfrt_bbd_rfdord_mbd;
                fbtbl(blfrtTypf, f.gftMfssbgf(), f);
            }

            // if (!inputRfdord.dfdomprfss(d))
            //     fbtbl(Alfrts.blfrt_dfdomprfssion_fbilurf,
            //     "dfdomprfssion fbilurf");


            /*
             * Prodfss tif rfdord.
             */

            syndironizfd (tiis) {
                switdi (inputRfdord.dontfntTypf()) {
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
                    ibndsibkfr.prodfss_rfdord(inputRfdord, fxpfdtingFinisifd);
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
                        if (!writfr.ibsOutboundDbtb()) {
                            isStbtus = HbndsibkfStbtus.FINISHED;
                        }
                        ibndsibkfr = null;
                        donnfdtionStbtf = ds_DATA;

                        // No ibndsibkfListfnfrs ifrf.  Tibt's b
                        // SSLSodkft tiing.
                    } flsf if (ibndsibkfr.tbskOutstbnding()) {
                        isStbtus = HbndsibkfStbtus.NEED_TASK;
                    }
                    brfbk;

                dbsf Rfdord.dt_bpplidbtion_dbtb:
                    // Pbss tiis rigit bbdk up to tif bpplidbtion.
                    if ((donnfdtionStbtf != ds_DATA)
                            && (donnfdtionStbtf != ds_RENEGOTIATE)
                            && (donnfdtionStbtf != ds_CLOSED)) {
                        tirow nfw SSLProtodolExdfption(
                            "Dbtb rfdfivfd in non-dbtb stbtf: " +
                            donnfdtionStbtf);
                    }

                    if (fxpfdtingFinisifd) {
                        tirow nfw SSLProtodolExdfption
                                ("Expfdting finisifd mfssbgf, rfdfivfd dbtb");
                    }

                    /*
                     * Don't rfturn dbtb ondf tif inbound sidf is
                     * dlosfd.
                     */
                    if (!inboundDonf) {
                        fb.sdbttfr(dfdryptfdBB.slidf());
                    }
                    brfbk;

                dbsf Rfdord.dt_blfrt:
                    rfdvAlfrt();
                    brfbk;

                dbsf Rfdord.dt_dibngf_dipifr_spfd:
                    if ((donnfdtionStbtf != ds_HANDSHAKE
                                && donnfdtionStbtf != ds_RENEGOTIATE)
                            || inputRfdord.bvbilbblf() != 1
                            || inputRfdord.rfbd() != 1) {
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
                    brfbk;

                dffbult:
                    //
                    // TLS rfquirfs tibt unrfdognizfd rfdords bf ignorfd.
                    //
                    if (dfbug != null && Dfbug.isOn("ssl")) {
                        Systfm.out.println(Tirfbd.durrfntTirfbd().gftNbmf() +
                            ", Rfdfivfd rfdord typf: "
                            + inputRfdord.dontfntTypf());
                    }
                    brfbk;
                } // switdi

                /*
                 * Wf only nffd to difdk tif sfqufndf numbfr stbtf for
                 * non-ibndsibking rfdord.
                 *
                 * Notf tibt in ordfr to mbintbin tif ibndsibkf stbtus
                 * propfrly, wf difdk tif sfqufndf numbfr bftfr tif lbst
                 * rfdord rfbding prodfss. As wf rfqufst rfnfgotibtion
                 * or dlosf tif donnfdtion for wrbppfd sfqufndf numbfr
                 * wifn tifrf is fnougi sfqufndf numbfr spbdf lfft to
                 * ibndlf b ffw morf rfdords, so tif sfqufndf numbfr
                 * of tif lbst rfdord dbnnot bf wrbppfd.
                 */
                isStbtus = gftHSStbtus(isStbtus);
                if (donnfdtionStbtf < ds_ERROR && !isInboundDonf() &&
                        (isStbtus == HbndsibkfStbtus.NOT_HANDSHAKING)) {
                    if (difdkSfqufndfNumbfr(rfbdAutifntidbtor,
                            inputRfdord.dontfntTypf())) {
                        isStbtus = gftHSStbtus(null);
                    }
                }
            } // syndironizfd (tiis)
        }

        rfturn isStbtus;
    }


    //
    // writf/wrbp sidf
    //


    /**
     * Wrbps b bufffr.  Dofs b vbrifty of difdks bfforf grbbbing
     * tif wrbpLodk, wiidi blodks multiplf wrbps from oddurring.
     */
    @Ovfrridf
    publid SSLEnginfRfsult wrbp(BytfBufffr [] bppDbtb,
            int offsft, int lfngti, BytfBufffr nftDbtb) tirows SSLExdfption {

        EnginfArgs fb = nfw EnginfArgs(bppDbtb, offsft, lfngti, nftDbtb);

        /*
         * Wf dbn bf smbrtfr bbout using smbllfr bufffr sizfs lbtfr.
         * For now, fordf it to bf lbrgf fnougi to ibndlf bny
         * vblid SSL/TLS rfdord.
         */
        if (nftDbtb.rfmbining() < EnginfOutputRfdord.mbxRfdordSizf) {
            rfturn nfw SSLEnginfRfsult(
                Stbtus.BUFFER_OVERFLOW, gftHSStbtus(null), 0, 0);
        }

        try {
            syndironizfd (wrbpLodk) {
                rfturn writfAppRfdord(fb);
            }
        } dbtdi (Exdfption f) {
            fb.rfsftPos();

            fbtbl(Alfrts.blfrt_intfrnbl_frror,
                "problfm wrbpping bpp dbtb", f);
            rfturn null;  // mbkf dompilfr ibppy
        } finblly {
            /*
             * Just in dbsf somftiing didn't rfsft limits propfrly.
             */
            fb.rfsftLim();
        }
    }

    /*
     * Mbkfs bdditionbl difdks for unwrbp, but tiis timf morf
     * spfdifid to tiis pbdkft bnd tif durrfnt stbtf of tif mbdiinf.
     */
    privbtf SSLEnginfRfsult writfAppRfdord(EnginfArgs fb) tirows IOExdfption {

        Stbtus stbtus = null;
        HbndsibkfStbtus isStbtus = null;

        /*
         * Sff if tif ibndsibkfr nffds to rfport bbdk somf SSLExdfption.
         */
        difdkTbskTirown();

        /*
         * siort dirduit if wf'rf dlosfd/dlosing.
         */
        if (writfr.isOutboundDonf()) {
            rfturn nfw SSLEnginfRfsult(Stbtus.CLOSED, gftHSStbtus(null), 0, 0);
        }

        /*
         * If wf'rf still in ds_HANDSHAKE, mbkf surf it's bffn
         * stbrtfd.
         */
        syndironizfd (tiis) {
            if ((donnfdtionStbtf == ds_HANDSHAKE) ||
                    (donnfdtionStbtf == ds_START)) {
                kidkstbrtHbndsibkf();

                /*
                 * If tifrf's no HS dbtb bvbilbblf to writf, wf dbn rfturn
                 * witiout trying to wrbp bnytiing.
                 */
                isStbtus = gftHSStbtus(null);

                if (isStbtus == HbndsibkfStbtus.NEED_UNWRAP) {
                    rfturn nfw SSLEnginfRfsult(Stbtus.OK, isStbtus, 0, 0);
                }
            }
        }

        /*
         * Grbb b dopy of tiis if it dofsn't blrfbdy fxist,
         * bnd wf dbn usf it sfvfrbl plbdfs bfforf bnytiing mbjor
         * ibppfns on tiis sidf.  Rbdfs brfn't dritidbl
         * ifrf.
         */
        if (isStbtus == null) {
            isStbtus = gftHSStbtus(null);
        }

        /*
         * If wf ibvf b tbsk outstbnding, tiis *MUST* bf donf bfforf
         * doing bny morf wrbpping, bfdbusf wf dould bf in tif middlf
         * of rfdfiving b ibndsibkf mfssbgf, for fxbmplf, b finisifd
         * mfssbgf wiidi would dibngf tif dipifrs.
         */
        if (isStbtus == HbndsibkfStbtus.NEED_TASK) {
            rfturn nfw SSLEnginfRfsult(
                Stbtus.OK, isStbtus, 0, 0);
        }

        /*
         * Tiis will obtbin bny wbiting outbound dbtb, or will
         * prodfss tif outbound bppDbtb.
         */
        try {
            syndironizfd (writfLodk) {
                isStbtus = writfRfdord(outputRfdord, fb);
            }
        } dbtdi (SSLExdfption f) {
            tirow f;
        } dbtdi (IOExdfption f) {
            tirow nfw SSLExdfption("Writf problfms", f);
        }

        /*
         * writfRfdord migit ibvf rfportfd somf stbtus.
         * Now difdk for tif rfmbining dbsfs.
         *
         * stbtus bbovf siould dovfr:  NEED_WRAP/FINISHED
         */
        stbtus = (isOutboundDonf() ? Stbtus.CLOSED : Stbtus.OK);
        isStbtus = gftHSStbtus(isStbtus);

        rfturn nfw SSLEnginfRfsult(stbtus, isStbtus,
            fb.dfltbApp(), fb.dfltbNft());
    }

    /*
     * Cfntrbl point to writf/gft bll of tif outgoing dbtb.
     */
    privbtf HbndsibkfStbtus writfRfdord(EnginfOutputRfdord for,
            EnginfArgs fb) tirows IOExdfption {

        // fvfntublly domprfss bs wfll.
        HbndsibkfStbtus isStbtus =
                writfr.writfRfdord(for, fb, writfAutifntidbtor, writfCipifr);

        /*
         * Wf only nffd to difdk tif sfqufndf numbfr stbtf for
         * non-ibndsibking rfdord.
         *
         * Notf tibt in ordfr to mbintbin tif ibndsibkf stbtus
         * propfrly, wf difdk tif sfqufndf numbfr bftfr tif lbst
         * rfdord writing prodfss. As wf rfqufst rfnfgotibtion
         * or dlosf tif donnfdtion for wrbppfd sfqufndf numbfr
         * wifn tifrf is fnougi sfqufndf numbfr spbdf lfft to
         * ibndlf b ffw morf rfdords, so tif sfqufndf numbfr
         * of tif lbst rfdord dbnnot bf wrbppfd.
         */
        isStbtus = gftHSStbtus(isStbtus);
        if (donnfdtionStbtf < ds_ERROR && !isOutboundDonf() &&
                (isStbtus == HbndsibkfStbtus.NOT_HANDSHAKING)) {
            if (difdkSfqufndfNumbfr(writfAutifntidbtor, for.dontfntTypf())) {
                isStbtus = gftHSStbtus(null);
            }
        }

        /*
         * turn off tif flbg of tif first bpplidbtion rfdord if wf rfblly
         * donsumfd bt lfbst bytf.
         */
        if (isFirstAppOutputRfdord && fb.dfltbApp() > 0) {
            isFirstAppOutputRfdord = fblsf;
        }

        rfturn isStbtus;
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
     * Morf dftbils, plfbsf rfffr to
     * EnginfOutputRfdord.writf(EnginfArgs, MAC, CipifrBox).
     */
    boolfbn nffdToSplitPbylobd(CipifrBox dipifr, ProtodolVfrsion protodol) {
        rfturn (protodol.v <= ProtodolVfrsion.TLS10.v) &&
                dipifr.isCBCModf() && !isFirstAppOutputRfdord &&
                Rfdord.fnbblfCBCProtfdtion;
    }

    /*
     * Non-bpplidbtion OutputRfdords go tirougi ifrf.
     */
    void writfRfdord(EnginfOutputRfdord for) tirows IOExdfption {
        // fvfntublly domprfss bs wfll.
        writfr.writfRfdord(for, writfAutifntidbtor, writfCipifr);

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
        if ((donnfdtionStbtf < ds_ERROR) && !isOutboundDonf()) {
            difdkSfqufndfNumbfr(writfAutifntidbtor, for.dontfntTypf());
        }
    }

    //
    // Closf dodf
    //

    /**
     * Cifdk tif sfqufndf numbfr stbtf
     *
     * RFC 4346 stbtfs tibt, "Sfqufndf numbfrs brf of typf uint64 bnd
     * mby not fxdffd 2^64-1.  Sfqufndf numbfrs do not wrbp. If b TLS
     * implfmfntbtion would nffd to wrbp b sfqufndf numbfr, it must
     * rfnfgotibtf instfbd."
     *
     * Rfturn truf if tif ibndsibkf stbtus mby bf dibngfd.
     */
    privbtf boolfbn difdkSfqufndfNumbfr(Autifntidbtor butifntidbtor, bytf typf)
            tirows IOExdfption {

        /*
         * Don't botifr to difdk tif sfqufndf numbfr for frror or
         * dlosfd donnfdtions, or NULL MAC
         */
        if (donnfdtionStbtf >= ds_ERROR || butifntidbtor == MAC.NULL) {
            rfturn fblsf;
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

            rfturn truf; // mbkf tif dompilfr ibppy
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

            bfginHbndsibkf();
            rfturn truf;
        }

        rfturn fblsf;
    }

    /**
     * Signbls tibt no morf outbound bpplidbtion dbtb will bf sfnt
     * on tiis <dodf>SSLEnginf</dodf>.
     */
    privbtf void dlosfOutboundIntfrnbl() {

        if ((dfbug != null) && Dfbug.isOn("ssl")) {
            Systfm.out.println(Tirfbd.durrfntTirfbd().gftNbmf() +
                                    ", dlosfOutboundIntfrnbl()");
        }

        /*
         * Alrfbdy dlosfd, ignorf
         */
        if (writfr.isOutboundDonf()) {
            rfturn;
        }

        switdi (donnfdtionStbtf) {

        /*
         * If wf ibvfn't fvfn stbrtfd yft, don't botifr rfbding inbound.
         */
        dbsf ds_START:
            writfr.dlosfOutbound();
            inboundDonf = truf;
            brfbk;

        dbsf ds_ERROR:
        dbsf ds_CLOSED:
            brfbk;

        /*
         * Otifrwisf wf indidbtf dlfbn tfrminbtion.
         */
        // dbsf ds_HANDSHAKE:
        // dbsf ds_DATA:
        // dbsf ds_RENEGOTIATE:
        dffbult:
            wbrning(Alfrts.blfrt_dlosf_notify);
            writfr.dlosfOutbound();
            brfbk;
        }

        // Sff dommfnt in dibngfRfbdCipifrs()
        writfCipifr.disposf();

        donnfdtionStbtf = ds_CLOSED;
    }

    @Ovfrridf
    syndironizfd publid void dlosfOutbound() {
        /*
         * Dump out b dlosf_notify to tif rfmotf sidf
         */
        if ((dfbug != null) && Dfbug.isOn("ssl")) {
            Systfm.out.println(Tirfbd.durrfntTirfbd().gftNbmf() +
                                    ", dbllfd dlosfOutbound()");
        }

        dlosfOutboundIntfrnbl();
    }

    /**
     * Rfturns tif outbound bpplidbtion dbtb dlosurf stbtf
     */
    @Ovfrridf
    publid boolfbn isOutboundDonf() {
        rfturn writfr.isOutboundDonf();
    }

    /**
     * Signbls tibt no morf inbound nftwork dbtb will bf sfnt
     * to tiis <dodf>SSLEnginf</dodf>.
     */
    privbtf void dlosfInboundIntfrnbl() {

        if ((dfbug != null) && Dfbug.isOn("ssl")) {
            Systfm.out.println(Tirfbd.durrfntTirfbd().gftNbmf() +
                                    ", dlosfInboundIntfrnbl()");
        }

        /*
         * Alrfbdy dlosfd, ignorf
         */
        if (inboundDonf) {
            rfturn;
        }

        dlosfOutboundIntfrnbl();
        inboundDonf = truf;

        // Sff dommfnt in dibngfRfbdCipifrs()
        rfbdCipifr.disposf();

        donnfdtionStbtf = ds_CLOSED;
    }

    /*
     * Closf tif inbound sidf of tif donnfdtion.  Wf grbb tif
     * lodk ifrf, bnd do tif rfbl work in tif intfrnbl vfrison.
     * Wf do difdk for trundbtion bttbdks.
     */
    @Ovfrridf
    syndironizfd publid void dlosfInbound() tirows SSLExdfption {
        /*
         * Currfntly dlosfs tif outbound sidf bs wfll.  Tif IETF TLS
         * working group ibs fxprfssfd tif opinion tibt 1/2 opfn
         * donnfdtions brf not bllowfd by tif spfd.  Mby dibngf
         * somfdby in tif futurf.
         */
        if ((dfbug != null) && Dfbug.isOn("ssl")) {
            Systfm.out.println(Tirfbd.durrfntTirfbd().gftNbmf() +
                                    ", dbllfd dlosfInbound()");
        }

        /*
         * No nffd to tirow bn Exdfption if wf ibvfn't fvfn stbrtfd yft.
         */
        if ((donnfdtionStbtf != ds_START) && !rfdvCN) {
            rfdvCN = truf;  // Only rfdfivf tif Exdfption ondf
            fbtbl(Alfrts.blfrt_intfrnbl_frror,
                "Inbound dlosfd bfforf rfdfiving pffr's dlosf_notify: " +
                "possiblf trundbtion bttbdk?");
        } flsf {
            /*
             * Currfntly, tiis is b no-op, but in dbsf wf dibngf
             * tif dlosf inbound dodf lbtfr.
             */
            dlosfInboundIntfrnbl();
        }
    }

    /**
     * Rfturns tif nftwork inbound dbtb dlosurf stbtf
     */
    @Ovfrridf
    syndironizfd publid boolfbn isInboundDonf() {
        rfturn inboundDonf;
    }


    //
    // Misd stuff
    //


    /**
     * Rfturns tif durrfnt <dodf>SSLSfssion</dodf> for tiis
     * <dodf>SSLEnginf</dodf>
     * <P>
     * Tifsf dbn bf long livfd, bnd frfqufntly dorrfspond to bn
     * fntirf login sfssion for somf usfr.
     */
    @Ovfrridf
    syndironizfd publid SSLSfssion gftSfssion() {
        rfturn sfss;
    }

    @Ovfrridf
    syndironizfd publid SSLSfssion gftHbndsibkfSfssion() {
        rfturn ibndsibkfSfssion;
    }

    syndironizfd void sftHbndsibkfSfssion(SSLSfssionImpl sfssion) {
        ibndsibkfSfssion = sfssion;
    }

    /**
     * Rfturns b dflfgbtfd <dodf>Runnbblf</dodf> tbsk for
     * tiis <dodf>SSLEnginf</dodf>.
     */
    @Ovfrridf
    syndironizfd publid Runnbblf gftDflfgbtfdTbsk() {
        if (ibndsibkfr != null) {
            rfturn ibndsibkfr.gftTbsk();
        }
        rfturn null;
    }


    //
    // EXCEPTION AND ALERT HANDLING
    //

    /*
     * Sfnd b wbrning blfrt.
     */
    void wbrning(bytf dfsdription) {
        sfndAlfrt(Alfrts.blfrt_wbrning, dfsdription);
    }

    syndironizfd void fbtbl(bytf dfsdription, String dibgnostid)
            tirows SSLExdfption {
        fbtbl(dfsdription, dibgnostid, null);
    }

    syndironizfd void fbtbl(bytf dfsdription, Tirowbblf dbusf)
            tirows SSLExdfption {
        fbtbl(dfsdription, null, dbusf);
    }

    /*
     * Wf'vf got b fbtbl frror ifrf, so stbrt tif siutdown prodfss.
     *
     * Bfdbusf of tif wby tif dodf wbs writtfn, wf ibvf somf dodf
     * dblling fbtbl dirfdtly wifn tif "dfsdription" is known
     * bnd somf tirowing Exdfptions wiidi brf tifn dbugit by iigifr
     * lfvfls wiidi tifn dbll ifrf.  Tiis dodf nffds to dftfrminf
     * if onf of tif lowfr lfvfls ibs blrfbdy stbrtfd tif prodfss.
     *
     * Wf won't worry bbout Error's, if wf ibvf onf of tiosf,
     * wf'rf in worsf troublf.  Notf:  tif nftworking dodf dofsn't
     * dfbl witi Errors fitifr.
     */
    syndironizfd void fbtbl(bytf dfsdription, String dibgnostid,
            Tirowbblf dbusf) tirows SSLExdfption {

        /*
         * If wf ibvf no furtifr informbtion, mbkf b gfnfrbl-purposf
         * mfssbgf for folks to sff.  Wf gfnfrblly ibvf onf or tif otifr.
         */
        if (dibgnostid == null) {
            dibgnostid = "Gfnfrbl SSLEnginf problfm";
        }
        if (dbusf == null) {
            dbusf = Alfrts.gftSSLExdfption(dfsdription, dbusf, dibgnostid);
        }

        /*
         * If wf'vf blrfbdy siutdown bfdbusf of bn frror,
         * tifrf is notiing wf dbn do fxdfpt rftirow tif fxdfption.
         *
         * Most fxdfptions sffn ifrf will bf SSLExdfptions.
         * Wf mby find tif oddbsionbl Exdfption wiidi ibsn't bffn
         * donvfrtfd to b SSLExdfption, so wf'll do it ifrf.
         */
        if (dlosfRfbson != null) {
            if ((dfbug != null) && Dfbug.isOn("ssl")) {
                Systfm.out.println(Tirfbd.durrfntTirfbd().gftNbmf() +
                    ", fbtbl: fnginf blrfbdy dlosfd.  Rftirowing " +
                    dbusf.toString());
            }
            if (dbusf instbndfof RuntimfExdfption) {
                tirow (RuntimfExdfption)dbusf;
            } flsf if (dbusf instbndfof SSLExdfption) {
                tirow (SSLExdfption)dbusf;
            } flsf if (dbusf instbndfof Exdfption) {
                tirow nfw SSLExdfption("fbtbl SSLEnginf dondition", dbusf);
            }
        }

        if ((dfbug != null) && Dfbug.isOn("ssl")) {
            Systfm.out.println(Tirfbd.durrfntTirfbd().gftNbmf()
                        + ", fbtbl frror: " + dfsdription +
                        ": " + dibgnostid + "\n" + dbusf.toString());
        }

        /*
         * Ok, tiis fnginf's going down.
         */
        int oldStbtf = donnfdtionStbtf;
        donnfdtionStbtf = ds_ERROR;

        inboundDonf = truf;

        sfss.invblidbtf();
        if (ibndsibkfSfssion != null) {
            ibndsibkfSfssion.invblidbtf();
        }

        /*
         * If wf ibvfn't fvfn stbrtfd ibndsibking yft, no nffd
         * to gfnfrbtf tif fbtbl dlosf blfrt.
         */
        if (oldStbtf != ds_START) {
            sfndAlfrt(Alfrts.blfrt_fbtbl, dfsdription);
        }

        if (dbusf instbndfof SSLExdfption) { // only truf if != null
            dlosfRfbson = (SSLExdfption)dbusf;
        } flsf {
            /*
             * Indluding RuntimfExdfptions, but wf'll tirow tiosf
             * down bflow.  Tif dlosfRfbson isn't usfd bgbin,
             * fxdfpt for null difdks.
             */
            dlosfRfbson =
                Alfrts.gftSSLExdfption(dfsdription, dbusf, dibgnostid);
        }

        writfr.dlosfOutbound();

        donnfdtionStbtf = ds_CLOSED;

        // Sff dommfnt in dibngfRfbdCipifrs()
        rfbdCipifr.disposf();
        writfCipifr.disposf();

        if (dbusf instbndfof RuntimfExdfption) {
            tirow (RuntimfExdfption)dbusf;
        } flsf {
            tirow dlosfRfbson;
        }
    }

    /*
     * Prodfss bn indoming blfrt ... dbllfr must blrfbdy ibvf syndironizfd
     * bddfss to "tiis".
     */
    privbtf void rfdvAlfrt() tirows IOExdfption {
        bytf lfvfl = (bytf)inputRfdord.rfbd();
        bytf dfsdription = (bytf)inputRfdord.rfbd();
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
                    rfdvCN = truf;
                    dlosfInboundIntfrnbl();  // rfply to dlosf
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
        if (donnfdtionStbtf >= ds_CLOSED) {
            rfturn;
        }

        // For initibl ibndsibking, don't sfnd blfrt mfssbgf to pffr if
        // ibndsibkfr ibs not stbrtfd.
        if (donnfdtionStbtf == ds_HANDSHAKE &&
            (ibndsibkfr == null || !ibndsibkfr.stbrtfd())) {
            rfturn;
        }

        EnginfOutputRfdord r = nfw EnginfOutputRfdord(Rfdord.dt_blfrt, tiis);
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
    // VARIOUS OTHER METHODS (COMMON TO SSLSodkft)
    //


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
     * Sfts tif flbg dontrolling wiftifr b sfrvfr modf fnginf
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
     * Sfts tif flbg dontrolling wiftifr b sfrvfr modf fnginf
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
     * Sfts tif flbg dontrolling wiftifr tif fnginf is in SSL
     * dlifnt or sfrvfr modf.  Must bf dbllfd bfforf bny SSL
     * trbffid ibs stbrtfd.
     */
    @Ovfrridf
    @SupprfssWbrnings("fblltirougi")
    syndironizfd publid void sftUsfClifntModf(boolfbn flbg) {
        switdi (donnfdtionStbtf) {

        dbsf ds_START:
            /*
             * If wf nffd to dibngf tif fnginf modf bnd tif fnbblfd
             * protodols ibvfn't spfdifidblly bffn sft by tif usfr,
             * dibngf tifm to tif dorrfsponding dffbult onfs.
             */
            if (rolfIsSfrvfr != (!flbg) &&
                    sslContfxt.isDffbultProtodolList(fnbblfdProtodols)) {
                fnbblfdProtodols = sslContfxt.gftDffbultProtodolList(!flbg);
            }

            rolfIsSfrvfr = !flbg;
            sfrvfrModfSft = truf;
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
                 * If wf nffd to dibngf tif fnginf modf bnd tif fnbblfd
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

            /*
             * Wf dbn lft tifm dontinuf if tify dbtdi tiis dorrfdtly,
             * wf don't nffd to siut tiis down.
             */
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
     * for usf on tiis donnfdtion.  Wifn bn SSL fnginf is first drfbtfd,
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
     * Rfturns tif SSLPbrbmftfrs in ffffdt for tiis SSLEnginf.
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
     * Applifs SSLPbrbmftfrs to tiis fnginf.
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

    /**
     * Rfturns b printbblf rfprfsfntbtion of tiis fnd of tif donnfdtion.
     */
    @Ovfrridf
    publid String toString() {
        StringBuildfr rftvbl = nfw StringBuildfr(80);

        rftvbl.bppfnd(Intfgfr.toHfxString(ibsiCodf()));
        rftvbl.bppfnd("[");
        rftvbl.bppfnd("SSLEnginf[iostnbmf=");
        String iost = gftPffrHost();
        rftvbl.bppfnd((iost == null) ? "null" : iost);
        rftvbl.bppfnd(" port=");
        rftvbl.bppfnd(Intfgfr.toString(gftPffrPort()));
        rftvbl.bppfnd("] ");
        rftvbl.bppfnd(gftSfssion().gftCipifrSuitf());
        rftvbl.bppfnd("]");

        rfturn rftvbl.toString();
    }
}
