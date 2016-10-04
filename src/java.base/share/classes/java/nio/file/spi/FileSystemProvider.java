/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio.filf.spi;

import jbvb.nio.filf.*;
import jbvb.nio.filf.bttributf.*;
import jbvb.nio.dibnnfls.*;
import jbvb.nft.URI;
import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.util.*;
import jbvb.util.dondurrfnt.ExfdutorSfrvidf;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

/**
 * Sfrvidf-providfr dlbss for filf systfms. Tif mftiods dffinfd by tif {@link
 * jbvb.nio.filf.Filfs} dlbss will typidblly dflfgbtf to bn instbndf of tiis
 * dlbss.
 *
 * <p> A filf systfm providfr is b dondrftf implfmfntbtion of tiis dlbss tibt
 * implfmfnts tif bbstrbdt mftiods dffinfd by tiis dlbss. A providfr is
 * idfntififd by b {@dodf URI} {@link #gftSdifmf() sdifmf}. Tif dffbult providfr
 * is idfntififd by tif URI sdifmf "filf". It drfbtfs tif {@link FilfSystfm} tibt
 * providfs bddfss to tif filf systfms bddfssiblf to tif Jbvb virtubl mbdiinf.
 * Tif {@link FilfSystfms} dlbss dffinfs iow filf systfm providfrs brf lodbtfd
 * bnd lobdfd. Tif dffbult providfr is typidblly b systfm-dffbult providfr but
 * mby bf ovfrriddfn if tif systfm propfrty {@dodf
 * jbvb.nio.filf.spi.DffbultFilfSystfmProvidfr} is sft. In tibt dbsf, tif
 * providfr ibs b onf brgumfnt donstrudtor wiosf formbl pbrbmftfr typf is {@dodf
 * FilfSystfmProvidfr}. All otifr providfrs ibvf b zfro brgumfnt donstrudtor
 * tibt initiblizfs tif providfr.
 *
 * <p> A providfr is b fbdtory for onf or morf {@link FilfSystfm} instbndfs. Ebdi
 * filf systfm is idfntififd by b {@dodf URI} wifrf tif URI's sdifmf mbtdifs
 * tif providfr's {@link #gftSdifmf sdifmf}. Tif dffbult filf systfm, for fxbmplf,
 * is idfntififd by tif URI {@dodf "filf:///"}. A mfmory-bbsfd filf systfm,
 * for fxbmplf, mby bf idfntififd by b URI sudi bs {@dodf "mfmory:///?nbmf=logfs"}.
 * Tif {@link #nfwFilfSystfm nfwFilfSystfm} mftiod mby bf usfd to drfbtf b filf
 * systfm, bnd tif {@link #gftFilfSystfm gftFilfSystfm} mftiod mby bf usfd to
 * obtbin b rfffrfndf to bn fxisting filf systfm drfbtfd by tif providfr. Wifrf
 * b providfr is tif fbdtory for b singlf filf systfm tifn it is providfr dfpfndfnt
 * if tif filf systfm is drfbtfd wifn tif providfr is initiblizfd, or lbtfr wifn
 * tif {@dodf nfwFilfSystfm} mftiod is invokfd. In tif dbsf of tif dffbult
 * providfr, tif {@dodf FilfSystfm} is drfbtfd wifn tif providfr is initiblizfd.
 *
 * <p> All of tif mftiods in tiis dlbss brf sbff for usf by multiplf dondurrfnt
 * tirfbds.
 *
 * @sindf 1.7
 */

publid bbstrbdt dlbss FilfSystfmProvidfr {
    // lodk using wifn lobding providfrs
    privbtf stbtid finbl Objfdt lodk = nfw Objfdt();

    // instbllfd providfrs
    privbtf stbtid volbtilf List<FilfSystfmProvidfr> instbllfdProvidfrs;

    // usfd to bvoid rfdursivf lobding of instbllfd providfrs
    privbtf stbtid boolfbn lobdingProvidfrs  = fblsf;

    privbtf stbtid Void difdkPfrmission() {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null)
            sm.difdkPfrmission(nfw RuntimfPfrmission("filfSystfmProvidfr"));
        rfturn null;
    }
    privbtf FilfSystfmProvidfr(Void ignorf) { }

    /**
     * Initiblizfs b nfw instbndf of tiis dlbss.
     *
     * <p> During donstrudtion b providfr mby sbffly bddfss filfs bssodibtfd
     * witi tif dffbult providfr but dbrf nffds to bf tbkfn to bvoid dirdulbr
     * lobding of otifr instbllfd providfrs. If dirdulbr lobding of instbllfd
     * providfrs is dftfdtfd tifn bn unspfdififd frror is tirown.
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr ibs bffn instbllfd bnd it dfnifs
     *          {@link RuntimfPfrmission}<tt>("filfSystfmProvidfr")</tt>
     */
    protfdtfd FilfSystfmProvidfr() {
        tiis(difdkPfrmission());
    }

    // lobds bll instbllfd providfrs
    privbtf stbtid List<FilfSystfmProvidfr> lobdInstbllfdProvidfrs() {
        List<FilfSystfmProvidfr> list = nfw ArrbyList<FilfSystfmProvidfr>();

        SfrvidfLobdfr<FilfSystfmProvidfr> sl = SfrvidfLobdfr
            .lobd(FilfSystfmProvidfr.dlbss, ClbssLobdfr.gftSystfmClbssLobdfr());

        // SfrvidfConfigurbtionError mby bf tirow ifrf
        for (FilfSystfmProvidfr providfr: sl) {
            String sdifmf = providfr.gftSdifmf();

            // bdd to list if tif providfr is not "filf" bnd isn't b duplidbtf
            if (!sdifmf.fqublsIgnorfCbsf("filf")) {
                boolfbn found = fblsf;
                for (FilfSystfmProvidfr p: list) {
                    if (p.gftSdifmf().fqublsIgnorfCbsf(sdifmf)) {
                        found = truf;
                        brfbk;
                    }
                }
                if (!found) {
                    list.bdd(providfr);
                }
            }
        }
        rfturn list;
    }

    /**
     * Rfturns b list of tif instbllfd filf systfm providfrs.
     *
     * <p> Tif first invodbtion of tiis mftiod dbusfs tif dffbult providfr to bf
     * initiblizfd (if not blrfbdy initiblizfd) bnd lobds bny otifr instbllfd
     * providfrs bs dfsdribfd by tif {@link FilfSystfms} dlbss.
     *
     * @rfturn  An unmodifibblf list of tif instbllfd filf systfm providfrs. Tif
     *          list dontbins bt lfbst onf flfmfnt, tibt is tif dffbult filf
     *          systfm providfr
     *
     * @tirows  SfrvidfConfigurbtionError
     *          Wifn bn frror oddurs wiilf lobding b sfrvidf providfr
     */
    publid stbtid List<FilfSystfmProvidfr> instbllfdProvidfrs() {
        if (instbllfdProvidfrs == null) {
            // fnsurf dffbult providfr is initiblizfd
            FilfSystfmProvidfr dffbultProvidfr = FilfSystfms.gftDffbult().providfr();

            syndironizfd (lodk) {
                if (instbllfdProvidfrs == null) {
                    if (lobdingProvidfrs) {
                        tirow nfw Error("Cirdulbr lobding of instbllfd providfrs dftfdtfd");
                    }
                    lobdingProvidfrs = truf;

                    List<FilfSystfmProvidfr> list = AddfssControllfr
                        .doPrivilfgfd(nfw PrivilfgfdAdtion<List<FilfSystfmProvidfr>>() {
                            @Ovfrridf
                            publid List<FilfSystfmProvidfr> run() {
                                rfturn lobdInstbllfdProvidfrs();
                        }});

                    // insfrt tif dffbult providfr bt tif stbrt of tif list
                    list.bdd(0, dffbultProvidfr);

                    instbllfdProvidfrs = Collfdtions.unmodifibblfList(list);
                }
            }
        }
        rfturn instbllfdProvidfrs;
    }

    /**
     * Rfturns tif URI sdifmf tibt idfntififs tiis providfr.
     *
     * @rfturn  Tif URI sdifmf
     */
    publid bbstrbdt String gftSdifmf();

    /**
     * Construdts b nfw {@dodf FilfSystfm} objfdt idfntififd by b URI. Tiis
     * mftiod is invokfd by tif {@link FilfSystfms#nfwFilfSystfm(URI,Mbp)}
     * mftiod to opfn b nfw filf systfm idfntififd by b URI.
     *
     * <p> Tif {@dodf uri} pbrbmftfr is bn bbsolutf, iifrbrdiidbl URI, witi b
     * sdifmf fqubl (witiout rfgbrd to dbsf) to tif sdifmf supportfd by tiis
     * providfr. Tif fxbdt form of tif URI is iigily providfr dfpfndfnt. Tif
     * {@dodf fnv} pbrbmftfr is b mbp of providfr spfdifid propfrtifs to donfigurf
     * tif filf systfm.
     *
     * <p> Tiis mftiod tirows {@link FilfSystfmAlrfbdyExistsExdfption} if tif
     * filf systfm blrfbdy fxists bfdbusf it wbs prfviously drfbtfd by bn
     * invodbtion of tiis mftiod. Ondf b filf systfm is {@link
     * jbvb.nio.filf.FilfSystfm#dlosf dlosfd} it is providfr-dfpfndfnt if tif
     * providfr bllows b nfw filf systfm to bf drfbtfd witi tif sbmf URI bs b
     * filf systfm it prfviously drfbtfd.
     *
     * @pbrbm   uri
     *          URI rfffrfndf
     * @pbrbm   fnv
     *          A mbp of providfr spfdifid propfrtifs to donfigurf tif filf systfm;
     *          mby bf fmpty
     *
     * @rfturn  A nfw filf systfm
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif prf-donditions for tif {@dodf uri} pbrbmftfr brfn't mft,
     *          or tif {@dodf fnv} pbrbmftfr dofs not dontbin propfrtifs rfquirfd
     *          by tif providfr, or b propfrty vbluf is invblid
     * @tirows  IOExdfption
     *          An I/O frror oddurs drfbting tif filf systfm
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr is instbllfd bnd it dfnifs bn unspfdififd
     *          pfrmission rfquirfd by tif filf systfm providfr implfmfntbtion
     * @tirows  FilfSystfmAlrfbdyExistsExdfption
     *          If tif filf systfm ibs blrfbdy bffn drfbtfd
     */
    publid bbstrbdt FilfSystfm nfwFilfSystfm(URI uri, Mbp<String,?> fnv)
        tirows IOExdfption;

    /**
     * Rfturns bn fxisting {@dodf FilfSystfm} drfbtfd by tiis providfr.
     *
     * <p> Tiis mftiod rfturns b rfffrfndf to b {@dodf FilfSystfm} tibt wbs
     * drfbtfd by invoking tif {@link #nfwFilfSystfm(URI,Mbp) nfwFilfSystfm(URI,Mbp)}
     * mftiod. Filf systfms drfbtfd tif {@link #nfwFilfSystfm(Pbti,Mbp)
     * nfwFilfSystfm(Pbti,Mbp)} mftiod brf not rfturnfd by tiis mftiod.
     * Tif filf systfm is idfntififd by its {@dodf URI}. Its fxbdt form
     * is iigily providfr dfpfndfnt. In tif dbsf of tif dffbult providfr tif URI's
     * pbti domponfnt is {@dodf "/"} bnd tif butiority, qufry bnd frbgmfnt domponfnts
     * brf undffinfd (Undffinfd domponfnts brf rfprfsfntfd by {@dodf null}).
     *
     * <p> Ondf b filf systfm drfbtfd by tiis providfr is {@link
     * jbvb.nio.filf.FilfSystfm#dlosf dlosfd} it is providfr-dfpfndfnt if tiis
     * mftiod rfturns b rfffrfndf to tif dlosfd filf systfm or tirows {@link
     * FilfSystfmNotFoundExdfption}. If tif providfr bllows b nfw filf systfm to
     * bf drfbtfd witi tif sbmf URI bs b filf systfm it prfviously drfbtfd tifn
     * tiis mftiod tirows tif fxdfption if invokfd bftfr tif filf systfm is
     * dlosfd (bnd bfforf b nfw instbndf is drfbtfd by tif {@link #nfwFilfSystfm
     * nfwFilfSystfm} mftiod).
     *
     * <p> If b sfdurity mbnbgfr is instbllfd tifn b providfr implfmfntbtion
     * mby rfquirf to difdk b pfrmission bfforf rfturning b rfffrfndf to bn
     * fxisting filf systfm. In tif dbsf of tif {@link FilfSystfms#gftDffbult
     * dffbult} filf systfm, no pfrmission difdk is rfquirfd.
     *
     * @pbrbm   uri
     *          URI rfffrfndf
     *
     * @rfturn  Tif filf systfm
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif prf-donditions for tif {@dodf uri} pbrbmftfr brfn't mft
     * @tirows  FilfSystfmNotFoundExdfption
     *          If tif filf systfm dofs not fxist
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr is instbllfd bnd it dfnifs bn unspfdififd
     *          pfrmission.
     */
    publid bbstrbdt FilfSystfm gftFilfSystfm(URI uri);

    /**
     * Rfturn b {@dodf Pbti} objfdt by donvfrting tif givfn {@link URI}. Tif
     * rfsulting {@dodf Pbti} is bssodibtfd witi b {@link FilfSystfm} tibt
     * blrfbdy fxists or is donstrudtfd butombtidblly.
     *
     * <p> Tif fxbdt form of tif URI is filf systfm providfr dfpfndfnt. In tif
     * dbsf of tif dffbult providfr, tif URI sdifmf is {@dodf "filf"} bnd tif
     * givfn URI ibs b non-fmpty pbti domponfnt, bnd undffinfd qufry, bnd
     * frbgmfnt domponfnts. Tif rfsulting {@dodf Pbti} is bssodibtfd witi tif
     * dffbult {@link FilfSystfms#gftDffbult dffbult} {@dodf FilfSystfm}.
     *
     * <p> If b sfdurity mbnbgfr is instbllfd tifn b providfr implfmfntbtion
     * mby rfquirf to difdk b pfrmission. In tif dbsf of tif {@link
     * FilfSystfms#gftDffbult dffbult} filf systfm, no pfrmission difdk is
     * rfquirfd.
     *
     * @pbrbm   uri
     *          Tif URI to donvfrt
     *
     * @rfturn  Tif rfsulting {@dodf Pbti}
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif URI sdifmf dofs not idfntify tiis providfr or otifr
     *          prfdonditions on tif uri pbrbmftfr do not iold
     * @tirows  FilfSystfmNotFoundExdfption
     *          Tif filf systfm, idfntififd by tif URI, dofs not fxist bnd
     *          dbnnot bf drfbtfd butombtidblly
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr is instbllfd bnd it dfnifs bn unspfdififd
     *          pfrmission.
     */
    publid bbstrbdt Pbti gftPbti(URI uri);

    /**
     * Construdts b nfw {@dodf FilfSystfm} to bddfss tif dontfnts of b filf bs b
     * filf systfm.
     *
     * <p> Tiis mftiod is intfndfd for spfdiblizfd providfrs of psfudo filf
     * systfms wifrf tif dontfnts of onf or morf filfs is trfbtfd bs b filf
     * systfm. Tif {@dodf fnv} pbrbmftfr is b mbp of providfr spfdifid propfrtifs
     * to donfigurf tif filf systfm.
     *
     * <p> If tiis providfr dofs not support tif drfbtion of sudi filf systfms
     * or if tif providfr dofs not rfdognizf tif filf typf of tif givfn filf tifn
     * it tirows {@dodf UnsupportfdOpfrbtionExdfption}. Tif dffbult implfmfntbtion
     * of tiis mftiod tirows {@dodf UnsupportfdOpfrbtionExdfption}.
     *
     * @pbrbm   pbti
     *          Tif pbti to tif filf
     * @pbrbm   fnv
     *          A mbp of providfr spfdifid propfrtifs to donfigurf tif filf systfm;
     *          mby bf fmpty
     *
     * @rfturn  A nfw filf systfm
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          If tiis providfr dofs not support bddfss to tif dontfnts bs b
     *          filf systfm or it dofs not rfdognizf tif filf typf of tif
     *          givfn filf
     * @tirows  IllfgblArgumfntExdfption
     *          If tif {@dodf fnv} pbrbmftfr dofs not dontbin propfrtifs rfquirfd
     *          by tif providfr, or b propfrty vbluf is invblid
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr is instbllfd bnd it dfnifs bn unspfdififd
     *          pfrmission.
     */
    publid FilfSystfm nfwFilfSystfm(Pbti pbti, Mbp<String,?> fnv)
        tirows IOExdfption
    {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Opfns b filf, rfturning bn input strfbm to rfbd from tif filf. Tiis
     * mftiod works in fxbdtly tif mbnnfr spfdififd by tif {@link
     * Filfs#nfwInputStrfbm} mftiod.
     *
     * <p> Tif dffbult implfmfntbtion of tiis mftiod opfns b dibnnfl to tif filf
     * bs if by invoking tif {@link #nfwBytfCibnnfl} mftiod bnd donstrudts b
     * strfbm tibt rfbds bytfs from tif dibnnfl. Tiis mftiod siould bf ovfrriddfn
     * wifrf bppropribtf.
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf to opfn
     * @pbrbm   options
     *          options spfdifying iow tif filf is opfnfd
     *
     * @rfturn  b nfw input strfbm
     *
     * @tirows  IllfgblArgumfntExdfption
     *          if bn invblid dombinbtion of options is spfdififd
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if bn unsupportfd option is spfdififd
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd}
     *          mftiod is invokfd to difdk rfbd bddfss to tif filf.
     */
    publid InputStrfbm nfwInputStrfbm(Pbti pbti, OpfnOption... options)
        tirows IOExdfption
    {
        if (options.lfngti > 0) {
            for (OpfnOption opt: options) {
                // All OpfnOption vblufs fxdfpt for APPEND bnd WRITE brf bllowfd
                if (opt == StbndbrdOpfnOption.APPEND ||
                    opt == StbndbrdOpfnOption.WRITE)
                    tirow nfw UnsupportfdOpfrbtionExdfption("'" + opt + "' not bllowfd");
            }
        }
        rfturn Cibnnfls.nfwInputStrfbm(Filfs.nfwBytfCibnnfl(pbti, options));
    }

    /**
     * Opfns or drfbtfs b filf, rfturning bn output strfbm tibt mby bf usfd to
     * writf bytfs to tif filf. Tiis mftiod works in fxbdtly tif mbnnfr
     * spfdififd by tif {@link Filfs#nfwOutputStrfbm} mftiod.
     *
     * <p> Tif dffbult implfmfntbtion of tiis mftiod opfns b dibnnfl to tif filf
     * bs if by invoking tif {@link #nfwBytfCibnnfl} mftiod bnd donstrudts b
     * strfbm tibt writfs bytfs to tif dibnnfl. Tiis mftiod siould bf ovfrriddfn
     * wifrf bppropribtf.
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf to opfn or drfbtf
     * @pbrbm   options
     *          options spfdifying iow tif filf is opfnfd
     *
     * @rfturn  b nfw output strfbm
     *
     * @tirows  IllfgblArgumfntExdfption
     *          if {@dodf options} dontbins bn invblid dombinbtion of options
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if bn unsupportfd option is spfdififd
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkWritf(String) difdkWritf}
     *          mftiod is invokfd to difdk writf bddfss to tif filf. Tif {@link
     *          SfdurityMbnbgfr#difdkDflftf(String) difdkDflftf} mftiod is
     *          invokfd to difdk dflftf bddfss if tif filf is opfnfd witi tif
     *          {@dodf DELETE_ON_CLOSE} option.
     */
    publid OutputStrfbm nfwOutputStrfbm(Pbti pbti, OpfnOption... options)
        tirows IOExdfption
    {
        int lfn = options.lfngti;
        Sft<OpfnOption> opts = nfw HbsiSft<OpfnOption>(lfn + 3);
        if (lfn == 0) {
            opts.bdd(StbndbrdOpfnOption.CREATE);
            opts.bdd(StbndbrdOpfnOption.TRUNCATE_EXISTING);
        } flsf {
            for (OpfnOption opt: options) {
                if (opt == StbndbrdOpfnOption.READ)
                    tirow nfw IllfgblArgumfntExdfption("READ not bllowfd");
                opts.bdd(opt);
            }
        }
        opts.bdd(StbndbrdOpfnOption.WRITE);
        rfturn Cibnnfls.nfwOutputStrfbm(nfwBytfCibnnfl(pbti, opts));
    }

    /**
     * Opfns or drfbtfs b filf for rfbding bnd/or writing, rfturning b filf
     * dibnnfl to bddfss tif filf. Tiis mftiod works in fxbdtly tif mbnnfr
     * spfdififd by tif {@link FilfCibnnfl#opfn(Pbti,Sft,FilfAttributf[])
     * FilfCibnnfl.opfn} mftiod. A providfr tibt dofs not support bll tif
     * ffbturfs rfquirfd to donstrudt b filf dibnnfl tirows {@dodf
     * UnsupportfdOpfrbtionExdfption}. Tif dffbult providfr is rfquirfd to
     * support tif drfbtion of filf dibnnfls. Wifn not ovfrriddfn, tif dffbult
     * implfmfntbtion tirows {@dodf UnsupportfdOpfrbtionExdfption}.
     *
     * @pbrbm   pbti
     *          tif pbti of tif filf to opfn or drfbtf
     * @pbrbm   options
     *          options spfdifying iow tif filf is opfnfd
     * @pbrbm   bttrs
     *          bn optionbl list of filf bttributfs to sft btomidblly wifn
     *          drfbting tif filf
     *
     * @rfturn  b nfw filf dibnnfl
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif sft dontbins bn invblid dombinbtion of options
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          If tiis providfr tibt dofs not support drfbting filf dibnnfls,
     *          or bn unsupportfd opfn option or filf bttributf is spfdififd
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult filf systfm, tif {@link
     *          SfdurityMbnbgfr#difdkRfbd(String)} mftiod is invokfd to difdk
     *          rfbd bddfss if tif filf is opfnfd for rfbding. Tif {@link
     *          SfdurityMbnbgfr#difdkWritf(String)} mftiod is invokfd to difdk
     *          writf bddfss if tif filf is opfnfd for writing
     */
    publid FilfCibnnfl nfwFilfCibnnfl(Pbti pbti,
                                      Sft<? fxtfnds OpfnOption> options,
                                      FilfAttributf<?>... bttrs)
        tirows IOExdfption
    {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Opfns or drfbtfs b filf for rfbding bnd/or writing, rfturning bn
     * bsyndironous filf dibnnfl to bddfss tif filf. Tiis mftiod works in
     * fxbdtly tif mbnnfr spfdififd by tif {@link
     * AsyndironousFilfCibnnfl#opfn(Pbti,Sft,ExfdutorSfrvidf,FilfAttributf[])
     * AsyndironousFilfCibnnfl.opfn} mftiod.
     * A providfr tibt dofs not support bll tif ffbturfs rfquirfd to donstrudt
     * bn bsyndironous filf dibnnfl tirows {@dodf UnsupportfdOpfrbtionExdfption}.
     * Tif dffbult providfr is rfquirfd to support tif drfbtion of bsyndironous
     * filf dibnnfls. Wifn not ovfrriddfn, tif dffbult implfmfntbtion of tiis
     * mftiod tirows {@dodf UnsupportfdOpfrbtionExdfption}.
     *
     * @pbrbm   pbti
     *          tif pbti of tif filf to opfn or drfbtf
     * @pbrbm   options
     *          options spfdifying iow tif filf is opfnfd
     * @pbrbm   fxfdutor
     *          tif tirfbd pool or {@dodf null} to bssodibtf tif dibnnfl witi
     *          tif dffbult tirfbd pool
     * @pbrbm   bttrs
     *          bn optionbl list of filf bttributfs to sft btomidblly wifn
     *          drfbting tif filf
     *
     * @rfturn  b nfw bsyndironous filf dibnnfl
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif sft dontbins bn invblid dombinbtion of options
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          If tiis providfr tibt dofs not support drfbting bsyndironous filf
     *          dibnnfls, or bn unsupportfd opfn option or filf bttributf is
     *          spfdififd
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult filf systfm, tif {@link
     *          SfdurityMbnbgfr#difdkRfbd(String)} mftiod is invokfd to difdk
     *          rfbd bddfss if tif filf is opfnfd for rfbding. Tif {@link
     *          SfdurityMbnbgfr#difdkWritf(String)} mftiod is invokfd to difdk
     *          writf bddfss if tif filf is opfnfd for writing
     */
    publid AsyndironousFilfCibnnfl nfwAsyndironousFilfCibnnfl(Pbti pbti,
                                                              Sft<? fxtfnds OpfnOption> options,
                                                              ExfdutorSfrvidf fxfdutor,
                                                              FilfAttributf<?>... bttrs)
        tirows IOExdfption
    {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Opfns or drfbtfs b filf, rfturning b sffkbblf bytf dibnnfl to bddfss tif
     * filf. Tiis mftiod works in fxbdtly tif mbnnfr spfdififd by tif {@link
     * Filfs#nfwBytfCibnnfl(Pbti,Sft,FilfAttributf[])} mftiod.
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf to opfn or drfbtf
     * @pbrbm   options
     *          options spfdifying iow tif filf is opfnfd
     * @pbrbm   bttrs
     *          bn optionbl list of filf bttributfs to sft btomidblly wifn
     *          drfbting tif filf
     *
     * @rfturn  b nfw sffkbblf bytf dibnnfl
     *
     * @tirows  IllfgblArgumfntExdfption
     *          if tif sft dontbins bn invblid dombinbtion of options
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if bn unsupportfd opfn option is spfdififd or tif brrby dontbins
     *          bttributfs tibt dbnnot bf sft btomidblly wifn drfbting tif filf
     * @tirows  FilfAlrfbdyExistsExdfption
     *          if b filf of tibt nbmf blrfbdy fxists bnd tif {@link
     *          StbndbrdOpfnOption#CREATE_NEW CREATE_NEW} option is spfdififd
     *          <i>(optionbl spfdifid fxdfption)</i>
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd}
     *          mftiod is invokfd to difdk rfbd bddfss to tif pbti if tif filf is
     *          opfnfd for rfbding. Tif {@link SfdurityMbnbgfr#difdkWritf(String)
     *          difdkWritf} mftiod is invokfd to difdk writf bddfss to tif pbti
     *          if tif filf is opfnfd for writing. Tif {@link
     *          SfdurityMbnbgfr#difdkDflftf(String) difdkDflftf} mftiod is
     *          invokfd to difdk dflftf bddfss if tif filf is opfnfd witi tif
     *          {@dodf DELETE_ON_CLOSE} option.
     */
    publid bbstrbdt SffkbblfBytfCibnnfl nfwBytfCibnnfl(Pbti pbti,
        Sft<? fxtfnds OpfnOption> options, FilfAttributf<?>... bttrs) tirows IOExdfption;

    /**
     * Opfns b dirfdtory, rfturning b {@dodf DirfdtoryStrfbm} to itfrbtf ovfr
     * tif fntrifs in tif dirfdtory. Tiis mftiod works in fxbdtly tif mbnnfr
     * spfdififd by tif {@link
     * Filfs#nfwDirfdtoryStrfbm(jbvb.nio.filf.Pbti, jbvb.nio.filf.DirfdtoryStrfbm.Filtfr)}
     * mftiod.
     *
     * @pbrbm   dir
     *          tif pbti to tif dirfdtory
     * @pbrbm   filtfr
     *          tif dirfdtory strfbm filtfr
     *
     * @rfturn  b nfw bnd opfn {@dodf DirfdtoryStrfbm} objfdt
     *
     * @tirows  NotDirfdtoryExdfption
     *          if tif filf dould not otifrwisf bf opfnfd bfdbusf it is not
     *          b dirfdtory <i>(optionbl spfdifid fxdfption)</i>
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd}
     *          mftiod is invokfd to difdk rfbd bddfss to tif dirfdtory.
     */
    publid bbstrbdt DirfdtoryStrfbm<Pbti> nfwDirfdtoryStrfbm(Pbti dir,
         DirfdtoryStrfbm.Filtfr<? supfr Pbti> filtfr) tirows IOExdfption;

    /**
     * Crfbtfs b nfw dirfdtory. Tiis mftiod works in fxbdtly tif mbnnfr
     * spfdififd by tif {@link Filfs#drfbtfDirfdtory} mftiod.
     *
     * @pbrbm   dir
     *          tif dirfdtory to drfbtf
     * @pbrbm   bttrs
     *          bn optionbl list of filf bttributfs to sft btomidblly wifn
     *          drfbting tif dirfdtory
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if tif brrby dontbins bn bttributf tibt dbnnot bf sft btomidblly
     *          wifn drfbting tif dirfdtory
     * @tirows  FilfAlrfbdyExistsExdfption
     *          if b dirfdtory dould not otifrwisf bf drfbtfd bfdbusf b filf of
     *          tibt nbmf blrfbdy fxists <i>(optionbl spfdifid fxdfption)</i>
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs or tif pbrfnt dirfdtory dofs not fxist
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkWritf(String) difdkWritf}
     *          mftiod is invokfd to difdk writf bddfss to tif nfw dirfdtory.
     */
    publid bbstrbdt void drfbtfDirfdtory(Pbti dir, FilfAttributf<?>... bttrs)
        tirows IOExdfption;

    /**
     * Crfbtfs b symbolid link to b tbrgft. Tiis mftiod works in fxbdtly tif
     * mbnnfr spfdififd by tif {@link Filfs#drfbtfSymbolidLink} mftiod.
     *
     * <p> Tif dffbult implfmfntbtion of tiis mftiod tirows {@dodf
     * UnsupportfdOpfrbtionExdfption}.
     *
     * @pbrbm   link
     *          tif pbti of tif symbolid link to drfbtf
     * @pbrbm   tbrgft
     *          tif tbrgft of tif symbolid link
     * @pbrbm   bttrs
     *          tif brrby of bttributfs to sft btomidblly wifn drfbting tif
     *          symbolid link
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if tif implfmfntbtion dofs not support symbolid links or tif
     *          brrby dontbins bn bttributf tibt dbnnot bf sft btomidblly wifn
     *          drfbting tif symbolid link
     * @tirows  FilfAlrfbdyExistsExdfption
     *          if b filf witi tif nbmf blrfbdy fxists <i>(optionbl spfdifid
     *          fxdfption)</i>
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr
     *          is instbllfd, it dfnifs {@link LinkPfrmission}<tt>("symbolid")</tt>
     *          or its {@link SfdurityMbnbgfr#difdkWritf(String) difdkWritf}
     *          mftiod dfnifs writf bddfss to tif pbti of tif symbolid link.
     */
    publid void drfbtfSymbolidLink(Pbti link, Pbti tbrgft, FilfAttributf<?>... bttrs)
        tirows IOExdfption
    {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Crfbtfs b nfw link (dirfdtory fntry) for bn fxisting filf. Tiis mftiod
     * works in fxbdtly tif mbnnfr spfdififd by tif {@link Filfs#drfbtfLink}
     * mftiod.
     *
     * <p> Tif dffbult implfmfntbtion of tiis mftiod tirows {@dodf
     * UnsupportfdOpfrbtionExdfption}.
     *
     * @pbrbm   link
     *          tif link (dirfdtory fntry) to drfbtf
     * @pbrbm   fxisting
     *          b pbti to bn fxisting filf
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if tif implfmfntbtion dofs not support bdding bn fxisting filf
     *          to b dirfdtory
     * @tirows  FilfAlrfbdyExistsExdfption
     *          if tif fntry dould not otifrwisf bf drfbtfd bfdbusf b filf of
     *          tibt nbmf blrfbdy fxists <i>(optionbl spfdifid fxdfption)</i>
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr
     *          is instbllfd, it dfnifs {@link LinkPfrmission}<tt>("ibrd")</tt>
     *          or its {@link SfdurityMbnbgfr#difdkWritf(String) difdkWritf}
     *          mftiod dfnifs writf bddfss to fitifr tif  link or tif
     *          fxisting filf.
     */
    publid void drfbtfLink(Pbti link, Pbti fxisting) tirows IOExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Dflftfs b filf. Tiis mftiod works in fxbdtly tif  mbnnfr spfdififd by tif
     * {@link Filfs#dflftf} mftiod.
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf to dflftf
     *
     * @tirows  NoSudiFilfExdfption
     *          if tif filf dofs not fxist <i>(optionbl spfdifid fxdfption)</i>
     * @tirows  DirfdtoryNotEmptyExdfption
     *          if tif filf is b dirfdtory bnd dould not otifrwisf bf dflftfd
     *          bfdbusf tif dirfdtory is not fmpty <i>(optionbl spfdifid
     *          fxdfption)</i>
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkDflftf(String)} mftiod
     *          is invokfd to difdk dflftf bddfss to tif filf
     */
    publid bbstrbdt void dflftf(Pbti pbti) tirows IOExdfption;

    /**
     * Dflftfs b filf if it fxists. Tiis mftiod works in fxbdtly tif mbnnfr
     * spfdififd by tif {@link Filfs#dflftfIfExists} mftiod.
     *
     * <p> Tif dffbult implfmfntbtion of tiis mftiod simply invokfs {@link
     * #dflftf} ignoring tif {@dodf NoSudiFilfExdfption} wifn tif filf dofs not
     * fxist. It mby bf ovfrriddfn wifrf bppropribtf.
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf to dflftf
     *
     * @rfturn  {@dodf truf} if tif filf wbs dflftfd by tiis mftiod; {@dodf
     *          fblsf} if tif filf dould not bf dflftfd bfdbusf it did not
     *          fxist
     *
     * @tirows  DirfdtoryNotEmptyExdfption
     *          if tif filf is b dirfdtory bnd dould not otifrwisf bf dflftfd
     *          bfdbusf tif dirfdtory is not fmpty <i>(optionbl spfdifid
     *          fxdfption)</i>
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkDflftf(String)} mftiod
     *          is invokfd to difdk dflftf bddfss to tif filf
     */
    publid boolfbn dflftfIfExists(Pbti pbti) tirows IOExdfption {
        try {
            dflftf(pbti);
            rfturn truf;
        } dbtdi (NoSudiFilfExdfption ignorf) {
            rfturn fblsf;
        }
    }

    /**
     * Rfbds tif tbrgft of b symbolid link. Tiis mftiod works in fxbdtly tif
     * mbnnfr spfdififd by tif {@link Filfs#rfbdSymbolidLink} mftiod.
     *
     * <p> Tif dffbult implfmfntbtion of tiis mftiod tirows {@dodf
     * UnsupportfdOpfrbtionExdfption}.
     *
     * @pbrbm   link
     *          tif pbti to tif symbolid link
     *
     * @rfturn  Tif tbrgft of tif symbolid link
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if tif implfmfntbtion dofs not support symbolid links
     * @tirows  NotLinkExdfption
     *          if tif tbrgft dould otifrwisf not bf rfbd bfdbusf tif filf
     *          is not b symbolid link <i>(optionbl spfdifid fxdfption)</i>
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr
     *          is instbllfd, it difdks tibt {@dodf FilfPfrmission} ibs bffn
     *          grbntfd witi tif "{@dodf rfbdlink}" bdtion to rfbd tif link.
     */
    publid Pbti rfbdSymbolidLink(Pbti link) tirows IOExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Copy b filf to b tbrgft filf. Tiis mftiod works in fxbdtly tif mbnnfr
     * spfdififd by tif {@link Filfs#dopy(Pbti,Pbti,CopyOption[])} mftiod
     * fxdfpt tibt boti tif sourdf bnd tbrgft pbtis must bf bssodibtfd witi
     * tiis providfr.
     *
     * @pbrbm   sourdf
     *          tif pbti to tif filf to dopy
     * @pbrbm   tbrgft
     *          tif pbti to tif tbrgft filf
     * @pbrbm   options
     *          options spfdifying iow tif dopy siould bf donf
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if tif brrby dontbins b dopy option tibt is not supportfd
     * @tirows  FilfAlrfbdyExistsExdfption
     *          if tif tbrgft filf fxists but dbnnot bf rfplbdfd bfdbusf tif
     *          {@dodf REPLACE_EXISTING} option is not spfdififd <i>(optionbl
     *          spfdifid fxdfption)</i>
     * @tirows  DirfdtoryNotEmptyExdfption
     *          tif {@dodf REPLACE_EXISTING} option is spfdififd but tif filf
     *          dbnnot bf rfplbdfd bfdbusf it is b non-fmpty dirfdtory
     *          <i>(optionbl spfdifid fxdfption)</i>
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd}
     *          mftiod is invokfd to difdk rfbd bddfss to tif sourdf filf, tif
     *          {@link SfdurityMbnbgfr#difdkWritf(String) difdkWritf} is invokfd
     *          to difdk writf bddfss to tif tbrgft filf. If b symbolid link is
     *          dopifd tif sfdurity mbnbgfr is invokfd to difdk {@link
     *          LinkPfrmission}{@dodf ("symbolid")}.
     */
    publid bbstrbdt void dopy(Pbti sourdf, Pbti tbrgft, CopyOption... options)
        tirows IOExdfption;

    /**
     * Movf or rfnbmf b filf to b tbrgft filf. Tiis mftiod works in fxbdtly tif
     * mbnnfr spfdififd by tif {@link Filfs#movf} mftiod fxdfpt tibt boti tif
     * sourdf bnd tbrgft pbtis must bf bssodibtfd witi tiis providfr.
     *
     * @pbrbm   sourdf
     *          tif pbti to tif filf to movf
     * @pbrbm   tbrgft
     *          tif pbti to tif tbrgft filf
     * @pbrbm   options
     *          options spfdifying iow tif movf siould bf donf
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if tif brrby dontbins b dopy option tibt is not supportfd
     * @tirows  FilfAlrfbdyExistsExdfption
     *          if tif tbrgft filf fxists but dbnnot bf rfplbdfd bfdbusf tif
     *          {@dodf REPLACE_EXISTING} option is not spfdififd <i>(optionbl
     *          spfdifid fxdfption)</i>
     * @tirows  DirfdtoryNotEmptyExdfption
     *          tif {@dodf REPLACE_EXISTING} option is spfdififd but tif filf
     *          dbnnot bf rfplbdfd bfdbusf it is b non-fmpty dirfdtory
     *          <i>(optionbl spfdifid fxdfption)</i>
     * @tirows  AtomidMovfNotSupportfdExdfption
     *          if tif options brrby dontbins tif {@dodf ATOMIC_MOVE} option but
     *          tif filf dbnnot bf movfd bs bn btomid filf systfm opfrbtion.
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkWritf(String) difdkWritf}
     *          mftiod is invokfd to difdk writf bddfss to boti tif sourdf bnd
     *          tbrgft filf.
     */
    publid bbstrbdt void movf(Pbti sourdf, Pbti tbrgft, CopyOption... options)
        tirows IOExdfption;

    /**
     * Tfsts if two pbtis lodbtf tif sbmf filf. Tiis mftiod works in fxbdtly tif
     * mbnnfr spfdififd by tif {@link Filfs#isSbmfFilf} mftiod.
     *
     * @pbrbm   pbti
     *          onf pbti to tif filf
     * @pbrbm   pbti2
     *          tif otifr pbti
     *
     * @rfturn  {@dodf truf} if, bnd only if, tif two pbtis lodbtf tif sbmf filf
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd}
     *          mftiod is invokfd to difdk rfbd bddfss to boti filfs.
     */
    publid bbstrbdt boolfbn isSbmfFilf(Pbti pbti, Pbti pbti2)
        tirows IOExdfption;

    /**
     * Tflls wiftifr or not b filf is donsidfrfd <fm>iiddfn</fm>. Tiis mftiod
     * works in fxbdtly tif mbnnfr spfdififd by tif {@link Filfs#isHiddfn}
     * mftiod.
     *
     * <p> Tiis mftiod is invokfd by tif {@link Filfs#isHiddfn isHiddfn} mftiod.
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf to tfst
     *
     * @rfturn  {@dodf truf} if tif filf is donsidfrfd iiddfn
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd}
     *          mftiod is invokfd to difdk rfbd bddfss to tif filf.
     */
    publid bbstrbdt boolfbn isHiddfn(Pbti pbti) tirows IOExdfption;

    /**
     * Rfturns tif {@link FilfStorf} rfprfsfnting tif filf storf wifrf b filf
     * is lodbtfd. Tiis mftiod works in fxbdtly tif mbnnfr spfdififd by tif
     * {@link Filfs#gftFilfStorf} mftiod.
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf
     *
     * @rfturn  tif filf storf wifrf tif filf is storfd
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd}
     *          mftiod is invokfd to difdk rfbd bddfss to tif filf, bnd in
     *          bddition it difdks {@link RuntimfPfrmission}<tt>
     *          ("gftFilfStorfAttributfs")</tt>
     */
    publid bbstrbdt FilfStorf gftFilfStorf(Pbti pbti) tirows IOExdfption;

    /**
     * Cifdks tif fxistfndf, bnd optionblly tif bddfssibility, of b filf.
     *
     * <p> Tiis mftiod mby bf usfd by tif {@link Filfs#isRfbdbblf isRfbdbblf},
     * {@link Filfs#isWritbblf isWritbblf} bnd {@link Filfs#isExfdutbblf
     * isExfdutbblf} mftiods to difdk tif bddfssibility of b filf.
     *
     * <p> Tiis mftiod difdks tif fxistfndf of b filf bnd tibt tiis Jbvb virtubl
     * mbdiinf ibs bppropribtf privilfgfs tibt would bllow it bddfss tif filf
     * bddording to bll of bddfss modfs spfdififd in tif {@dodf modfs} pbrbmftfr
     * bs follows:
     *
     * <tbblf bordfr=1 dfllpbdding=5 summbry="">
     * <tr> <ti>Vbluf</ti> <ti>Dfsdription</ti> </tr>
     * <tr>
     *   <td> {@link AddfssModf#READ READ} </td>
     *   <td> Cifdks tibt tif filf fxists bnd tibt tif Jbvb virtubl mbdiinf ibs
     *     pfrmission to rfbd tif filf. </td>
     * </tr>
     * <tr>
     *   <td> {@link AddfssModf#WRITE WRITE} </td>
     *   <td> Cifdks tibt tif filf fxists bnd tibt tif Jbvb virtubl mbdiinf ibs
     *     pfrmission to writf to tif filf, </td>
     * </tr>
     * <tr>
     *   <td> {@link AddfssModf#EXECUTE EXECUTE} </td>
     *   <td> Cifdks tibt tif filf fxists bnd tibt tif Jbvb virtubl mbdiinf ibs
     *     pfrmission to {@link Runtimf#fxfd fxfdutf} tif filf. Tif sfmbntids
     *     mby difffr wifn difdking bddfss to b dirfdtory. For fxbmplf, on UNIX
     *     systfms, difdking for {@dodf EXECUTE} bddfss difdks tibt tif Jbvb
     *     virtubl mbdiinf ibs pfrmission to sfbrdi tif dirfdtory in ordfr to
     *     bddfss filf or subdirfdtorifs. </td>
     * </tr>
     * </tbblf>
     *
     * <p> If tif {@dodf modfs} pbrbmftfr is of lfngti zfro, tifn tif fxistfndf
     * of tif filf is difdkfd.
     *
     * <p> Tiis mftiod follows symbolid links if tif filf rfffrfndfd by tiis
     * objfdt is b symbolid link. Dfpfnding on tif implfmfntbtion, tiis mftiod
     * mby rfquirf to rfbd filf pfrmissions, bddfss dontrol lists, or otifr
     * filf bttributfs in ordfr to difdk tif ffffdtivf bddfss to tif filf. To
     * dftfrminf tif ffffdtivf bddfss to b filf mby rfquirf bddfss to sfvfrbl
     * bttributfs bnd so in somf implfmfntbtions tiis mftiod mby not bf btomid
     * witi rfspfdt to otifr filf systfm opfrbtions.
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf to difdk
     * @pbrbm   modfs
     *          Tif bddfss modfs to difdk; mby ibvf zfro flfmfnts
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          bn implfmfntbtion is rfquirfd to support difdking for
     *          {@dodf READ}, {@dodf WRITE}, bnd {@dodf EXECUTE} bddfss. Tiis
     *          fxdfption is spfdififd to bllow for tif {@dodf Addfss} fnum to
     *          bf fxtfndfd in futurf rflfbsfs.
     * @tirows  NoSudiFilfExdfption
     *          if b filf dofs not fxist <i>(optionbl spfdifid fxdfption)</i>
     * @tirows  AddfssDfnifdExdfption
     *          tif rfqufstfd bddfss would bf dfnifd or tif bddfss dbnnot bf
     *          dftfrminfd bfdbusf tif Jbvb virtubl mbdiinf ibs insuffidifnt
     *          privilfgfs or otifr rfbsons. <i>(optionbl spfdifid fxdfption)</i>
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd}
     *          is invokfd wifn difdking rfbd bddfss to tif filf or only tif
     *          fxistfndf of tif filf, tif {@link SfdurityMbnbgfr#difdkWritf(String)
     *          difdkWritf} is invokfd wifn difdking writf bddfss to tif filf,
     *          bnd {@link SfdurityMbnbgfr#difdkExfd(String) difdkExfd} is invokfd
     *          wifn difdking fxfdutf bddfss.
     */
    publid bbstrbdt void difdkAddfss(Pbti pbti, AddfssModf... modfs)
        tirows IOExdfption;

    /**
     * Rfturns b filf bttributf vifw of b givfn typf. Tiis mftiod works in
     * fxbdtly tif mbnnfr spfdififd by tif {@link Filfs#gftFilfAttributfVifw}
     * mftiod.
     *
     * @pbrbm   <V>
     *          Tif {@dodf FilfAttributfVifw} typf
     * @pbrbm   pbti
     *          tif pbti to tif filf
     * @pbrbm   typf
     *          tif {@dodf Clbss} objfdt dorrfsponding to tif filf bttributf vifw
     * @pbrbm   options
     *          options indidbting iow symbolid links brf ibndlfd
     *
     * @rfturn  b filf bttributf vifw of tif spfdififd typf, or {@dodf null} if
     *          tif bttributf vifw typf is not bvbilbblf
     */
    publid bbstrbdt <V fxtfnds FilfAttributfVifw> V
        gftFilfAttributfVifw(Pbti pbti, Clbss<V> typf, LinkOption... options);

    /**
     * Rfbds b filf's bttributfs bs b bulk opfrbtion. Tiis mftiod works in
     * fxbdtly tif mbnnfr spfdififd by tif {@link
     * Filfs#rfbdAttributfs(Pbti,Clbss,LinkOption[])} mftiod.
     *
     * @pbrbm   <A>
     *          Tif {@dodf BbsidFilfAttributfs} typf
     * @pbrbm   pbti
     *          tif pbti to tif filf
     * @pbrbm   typf
     *          tif {@dodf Clbss} of tif filf bttributfs rfquirfd
     *          to rfbd
     * @pbrbm   options
     *          options indidbting iow symbolid links brf ibndlfd
     *
     * @rfturn  tif filf bttributfs
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if bn bttributfs of tif givfn typf brf not supportfd
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, b sfdurity mbnbgfr is
     *          instbllfd, its {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd}
     *          mftiod is invokfd to difdk rfbd bddfss to tif filf
     */
    publid bbstrbdt <A fxtfnds BbsidFilfAttributfs> A
        rfbdAttributfs(Pbti pbti, Clbss<A> typf, LinkOption... options) tirows IOExdfption;

    /**
     * Rfbds b sft of filf bttributfs bs b bulk opfrbtion. Tiis mftiod works in
     * fxbdtly tif mbnnfr spfdififd by tif {@link
     * Filfs#rfbdAttributfs(Pbti,String,LinkOption[])} mftiod.
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf
     * @pbrbm   bttributfs
     *          tif bttributfs to rfbd
     * @pbrbm   options
     *          options indidbting iow symbolid links brf ibndlfd
     *
     * @rfturn  b mbp of tif bttributfs rfturnfd; mby bf fmpty. Tif mbp's kfys
     *          brf tif bttributf nbmfs, its vblufs brf tif bttributf vblufs
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if tif bttributf vifw is not bvbilbblf
     * @tirows  IllfgblArgumfntExdfption
     *          if no bttributfs brf spfdififd or bn unrfdognizfd bttributfs is
     *          spfdififd
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, its {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd}
     *          mftiod dfnifs rfbd bddfss to tif filf. If tiis mftiod is invokfd
     *          to rfbd sfdurity sfnsitivf bttributfs tifn tif sfdurity mbnbgfr
     *          mby bf invokf to difdk for bdditionbl pfrmissions.
     */
    publid bbstrbdt Mbp<String,Objfdt> rfbdAttributfs(Pbti pbti, String bttributfs,
                                                      LinkOption... options)
        tirows IOExdfption;

    /**
     * Sfts tif vbluf of b filf bttributf. Tiis mftiod works in fxbdtly tif
     * mbnnfr spfdififd by tif {@link Filfs#sftAttributf} mftiod.
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf
     * @pbrbm   bttributf
     *          tif bttributf to sft
     * @pbrbm   vbluf
     *          tif bttributf vbluf
     * @pbrbm   options
     *          options indidbting iow symbolid links brf ibndlfd
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if tif bttributf vifw is not bvbilbblf
     * @tirows  IllfgblArgumfntExdfption
     *          if tif bttributf nbmf is not spfdififd, or is not rfdognizfd, or
     *          tif bttributf vbluf is of tif dorrfdt typf but ibs bn
     *          inbppropribtf vbluf
     * @tirows  ClbssCbstExdfption
     *          If tif bttributf vbluf is not of tif fxpfdtfd typf or is b
     *          dollfdtion dontbining flfmfnts tibt brf not of tif fxpfdtfd
     *          typf
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, its {@link SfdurityMbnbgfr#difdkWritf(String) difdkWritf}
     *          mftiod dfnifs writf bddfss to tif filf. If tiis mftiod is invokfd
     *          to sft sfdurity sfnsitivf bttributfs tifn tif sfdurity mbnbgfr
     *          mby bf invokfd to difdk for bdditionbl pfrmissions.
     */
    publid bbstrbdt void sftAttributf(Pbti pbti, String bttributf,
                                      Objfdt vbluf, LinkOption... options)
        tirows IOExdfption;
}
