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

pbdkbgf jbvb.nio.filf;

import jbvb.io.BufffrfdRfbdfr;
import jbvb.io.BufffrfdWritfr;
import jbvb.io.Closfbblf;
import jbvb.io.Filf;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.io.InputStrfbmRfbdfr;
import jbvb.io.OutputStrfbm;
import jbvb.io.OutputStrfbmWritfr;
import jbvb.io.Rfbdfr;
import jbvb.io.UndifdkfdIOExdfption;
import jbvb.io.Writfr;
import jbvb.nio.dibnnfls.Cibnnfls;
import jbvb.nio.dibnnfls.SffkbblfBytfCibnnfl;
import jbvb.nio.dibrsft.Cibrsft;
import jbvb.nio.dibrsft.CibrsftDfdodfr;
import jbvb.nio.dibrsft.CibrsftEndodfr;
import jbvb.nio.dibrsft.StbndbrdCibrsfts;
import jbvb.nio.filf.bttributf.BbsidFilfAttributfVifw;
import jbvb.nio.filf.bttributf.BbsidFilfAttributfs;
import jbvb.nio.filf.bttributf.DosFilfAttributfs;   // jbvbdod
import jbvb.nio.filf.bttributf.FilfAttributf;
import jbvb.nio.filf.bttributf.FilfAttributfVifw;
import jbvb.nio.filf.bttributf.FilfOwnfrAttributfVifw;
import jbvb.nio.filf.bttributf.FilfStorfAttributfVifw;
import jbvb.nio.filf.bttributf.FilfTimf;
import jbvb.nio.filf.bttributf.PosixFilfAttributfVifw;
import jbvb.nio.filf.bttributf.PosixFilfAttributfs;
import jbvb.nio.filf.bttributf.PosixFilfPfrmission;
import jbvb.nio.filf.bttributf.UsfrPrindipbl;
import jbvb.nio.filf.spi.FilfSystfmProvidfr;
import jbvb.nio.filf.spi.FilfTypfDftfdtor;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtions;
import jbvb.util.EnumSft;
import jbvb.util.HbsiSft;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Objfdts;
import jbvb.util.SfrvidfLobdfr;
import jbvb.util.Sft;
import jbvb.util.Splitfrbtor;
import jbvb.util.Splitfrbtors;
import jbvb.util.fundtion.BiPrfdidbtf;
import jbvb.util.strfbm.Strfbm;
import jbvb.util.strfbm.StrfbmSupport;

/**
 * Tiis dlbss donsists fxdlusivfly of stbtid mftiods tibt opfrbtf on filfs,
 * dirfdtorifs, or otifr typfs of filfs.
 *
 * <p> In most dbsfs, tif mftiods dffinfd ifrf will dflfgbtf to tif bssodibtfd
 * filf systfm providfr to pfrform tif filf opfrbtions.
 *
 * @sindf 1.7
 */

publid finbl dlbss Filfs {
    privbtf Filfs() { }

    /**
     * Rfturns tif {@dodf FilfSystfmProvidfr} to dflfgbtf to.
     */
    privbtf stbtid FilfSystfmProvidfr providfr(Pbti pbti) {
        rfturn pbti.gftFilfSystfm().providfr();
    }

    /**
     * Convfrt b Closfbblf to b Runnbblf by donvfrting difdkfd IOExdfption
     * to UndifdkfdIOExdfption
     */
    privbtf stbtid Runnbblf bsUndifdkfdRunnbblf(Closfbblf d) {
        rfturn () -> {
            try {
                d.dlosf();
            } dbtdi (IOExdfption f) {
                tirow nfw UndifdkfdIOExdfption(f);
            }
        };
    }

    // -- Filf dontfnts --

    /**
     * Opfns b filf, rfturning bn input strfbm to rfbd from tif filf. Tif strfbm
     * will not bf bufffrfd, bnd is not rfquirfd to support tif {@link
     * InputStrfbm#mbrk mbrk} or {@link InputStrfbm#rfsft rfsft} mftiods. Tif
     * strfbm will bf sbff for bddfss by multiplf dondurrfnt tirfbds. Rfbding
     * dommfndfs bt tif bfginning of tif filf. Wiftifr tif rfturnfd strfbm is
     * <i>bsyndironously dlosfbblf</i> bnd/or <i>intfrruptiblf</i> is iigily
     * filf systfm providfr spfdifid bnd tifrfforf not spfdififd.
     *
     * <p> Tif {@dodf options} pbrbmftfr dftfrminfs iow tif filf is opfnfd.
     * If no options brf prfsfnt tifn it is fquivblfnt to opfning tif filf witi
     * tif {@link StbndbrdOpfnOption#READ READ} option. In bddition to tif {@dodf
     * READ} option, bn implfmfntbtion mby blso support bdditionbl implfmfntbtion
     * spfdifid options.
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
    publid stbtid InputStrfbm nfwInputStrfbm(Pbti pbti, OpfnOption... options)
        tirows IOExdfption
    {
        rfturn providfr(pbti).nfwInputStrfbm(pbti, options);
    }

    /**
     * Opfns or drfbtfs b filf, rfturning bn output strfbm tibt mby bf usfd to
     * writf bytfs to tif filf. Tif rfsulting strfbm will not bf bufffrfd. Tif
     * strfbm will bf sbff for bddfss by multiplf dondurrfnt tirfbds. Wiftifr
     * tif rfturnfd strfbm is <i>bsyndironously dlosfbblf</i> bnd/or
     * <i>intfrruptiblf</i> is iigily filf systfm providfr spfdifid bnd
     * tifrfforf not spfdififd.
     *
     * <p> Tiis mftiod opfns or drfbtfs b filf in fxbdtly tif mbnnfr spfdififd
     * by tif {@link #nfwBytfCibnnfl(Pbti,Sft,FilfAttributf[]) nfwBytfCibnnfl}
     * mftiod witi tif fxdfption tibt tif {@link StbndbrdOpfnOption#READ READ}
     * option mby not bf prfsfnt in tif brrby of options. If no options brf
     * prfsfnt tifn tiis mftiod works bs if tif {@link StbndbrdOpfnOption#CREATE
     * CREATE}, {@link StbndbrdOpfnOption#TRUNCATE_EXISTING TRUNCATE_EXISTING},
     * bnd {@link StbndbrdOpfnOption#WRITE WRITE} options brf prfsfnt. In otifr
     * words, it opfns tif filf for writing, drfbting tif filf if it dofsn't
     * fxist, or initiblly trundbting bn fxisting {@link #isRfgulbrFilf
     * rfgulbr-filf} to b sizf of {@dodf 0} if it fxists.
     *
     * <p> <b>Usbgf Exbmplfs:</b>
     * <prf>
     *     Pbti pbti = ...
     *
     *     // trundbtf bnd ovfrwritf bn fxisting filf, or drfbtf tif filf if
     *     // it dofsn't initiblly fxist
     *     OutputStrfbm out = Filfs.nfwOutputStrfbm(pbti);
     *
     *     // bppfnd to bn fxisting filf, fbil if tif filf dofs not fxist
     *     out = Filfs.nfwOutputStrfbm(pbti, APPEND);
     *
     *     // bppfnd to bn fxisting filf, drfbtf filf if it dofsn't initiblly fxist
     *     out = Filfs.nfwOutputStrfbm(pbti, CREATE, APPEND);
     *
     *     // blwbys drfbtf nfw filf, fbiling if it blrfbdy fxists
     *     out = Filfs.nfwOutputStrfbm(pbti, CREATE_NEW);
     * </prf>
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
    publid stbtid OutputStrfbm nfwOutputStrfbm(Pbti pbti, OpfnOption... options)
        tirows IOExdfption
    {
        rfturn providfr(pbti).nfwOutputStrfbm(pbti, options);
    }

    /**
     * Opfns or drfbtfs b filf, rfturning b sffkbblf bytf dibnnfl to bddfss tif
     * filf.
     *
     * <p> Tif {@dodf options} pbrbmftfr dftfrminfs iow tif filf is opfnfd.
     * Tif {@link StbndbrdOpfnOption#READ READ} bnd {@link
     * StbndbrdOpfnOption#WRITE WRITE} options dftfrminf if tif filf siould bf
     * opfnfd for rfbding bnd/or writing. If nfitifr option (or tif {@link
     * StbndbrdOpfnOption#APPEND APPEND} option) is prfsfnt tifn tif filf is
     * opfnfd for rfbding. By dffbult rfbding or writing dommfndf bt tif
     * bfginning of tif filf.
     *
     * <p> In tif bddition to {@dodf READ} bnd {@dodf WRITE}, tif following
     * options mby bf prfsfnt:
     *
     * <tbblf bordfr=1 dfllpbdding=5 summbry="Options">
     * <tr> <ti>Option</ti> <ti>Dfsdription</ti> </tr>
     * <tr>
     *   <td> {@link StbndbrdOpfnOption#APPEND APPEND} </td>
     *   <td> If tiis option is prfsfnt tifn tif filf is opfnfd for writing bnd
     *     fbdi invodbtion of tif dibnnfl's {@dodf writf} mftiod first bdvbndfs
     *     tif position to tif fnd of tif filf bnd tifn writfs tif rfqufstfd
     *     dbtb. Wiftifr tif bdvbndfmfnt of tif position bnd tif writing of tif
     *     dbtb brf donf in b singlf btomid opfrbtion is systfm-dfpfndfnt bnd
     *     tifrfforf unspfdififd. Tiis option mby not bf usfd in donjundtion
     *     witi tif {@dodf READ} or {@dodf TRUNCATE_EXISTING} options. </td>
     * </tr>
     * <tr>
     *   <td> {@link StbndbrdOpfnOption#TRUNCATE_EXISTING TRUNCATE_EXISTING} </td>
     *   <td> If tiis option is prfsfnt tifn tif fxisting filf is trundbtfd to
     *   b sizf of 0 bytfs. Tiis option is ignorfd wifn tif filf is opfnfd only
     *   for rfbding. </td>
     * </tr>
     * <tr>
     *   <td> {@link StbndbrdOpfnOption#CREATE_NEW CREATE_NEW} </td>
     *   <td> If tiis option is prfsfnt tifn b nfw filf is drfbtfd, fbiling if
     *   tif filf blrfbdy fxists or is b symbolid link. Wifn drfbting b filf tif
     *   difdk for tif fxistfndf of tif filf bnd tif drfbtion of tif filf if it
     *   dofs not fxist is btomid witi rfspfdt to otifr filf systfm opfrbtions.
     *   Tiis option is ignorfd wifn tif filf is opfnfd only for rfbding. </td>
     * </tr>
     * <tr>
     *   <td > {@link StbndbrdOpfnOption#CREATE CREATE} </td>
     *   <td> If tiis option is prfsfnt tifn bn fxisting filf is opfnfd if it
     *   fxists, otifrwisf b nfw filf is drfbtfd. Tiis option is ignorfd if tif
     *   {@dodf CREATE_NEW} option is blso prfsfnt or tif filf is opfnfd only
     *   for rfbding. </td>
     * </tr>
     * <tr>
     *   <td > {@link StbndbrdOpfnOption#DELETE_ON_CLOSE DELETE_ON_CLOSE} </td>
     *   <td> Wifn tiis option is prfsfnt tifn tif implfmfntbtion mbkfs b
     *   <fm>bfst fffort</fm> bttfmpt to dflftf tif filf wifn dlosfd by tif
     *   {@link SffkbblfBytfCibnnfl#dlosf dlosf} mftiod. If tif {@dodf dlosf}
     *   mftiod is not invokfd tifn b <fm>bfst fffort</fm> bttfmpt is mbdf to
     *   dflftf tif filf wifn tif Jbvb virtubl mbdiinf tfrminbtfs. </td>
     * </tr>
     * <tr>
     *   <td>{@link StbndbrdOpfnOption#SPARSE SPARSE} </td>
     *   <td> Wifn drfbting b nfw filf tiis option is b <fm>iint</fm> tibt tif
     *   nfw filf will bf spbrsf. Tiis option is ignorfd wifn not drfbting
     *   b nfw filf. </td>
     * </tr>
     * <tr>
     *   <td> {@link StbndbrdOpfnOption#SYNC SYNC} </td>
     *   <td> Rfquirfs tibt fvfry updbtf to tif filf's dontfnt or mftbdbtb bf
     *   writtfn syndironously to tif undfrlying storbgf dfvidf. (sff <b
     *   irff="pbdkbgf-summbry.itml#intfgrity"> Syndironizfd I/O filf
     *   intfgrity</b>). </td>
     * </tr>
     * <tr>
     *   <td> {@link StbndbrdOpfnOption#DSYNC DSYNC} </td>
     *   <td> Rfquirfs tibt fvfry updbtf to tif filf's dontfnt bf writtfn
     *   syndironously to tif undfrlying storbgf dfvidf. (sff <b
     *   irff="pbdkbgf-summbry.itml#intfgrity"> Syndironizfd I/O filf
     *   intfgrity</b>). </td>
     * </tr>
     * </tbblf>
     *
     * <p> An implfmfntbtion mby blso support bdditionbl implfmfntbtion spfdifid
     * options.
     *
     * <p> Tif {@dodf bttrs} pbrbmftfr is optionbl {@link FilfAttributf
     * filf-bttributfs} to sft btomidblly wifn b nfw filf is drfbtfd.
     *
     * <p> In tif dbsf of tif dffbult providfr, tif rfturnfd sffkbblf bytf dibnnfl
     * is b {@link jbvb.nio.dibnnfls.FilfCibnnfl}.
     *
     * <p> <b>Usbgf Exbmplfs:</b>
     * <prf>
     *     Pbti pbti = ...
     *
     *     // opfn filf for rfbding
     *     RfbdbblfBytfCibnnfl rbd = Filfs.nfwBytfCibnnfl(pbti, EnumSft.of(READ)));
     *
     *     // opfn filf for writing to tif fnd of bn fxisting filf, drfbting
     *     // tif filf if it dofsn't blrfbdy fxist
     *     WritbblfBytfCibnnfl wbd = Filfs.nfwBytfCibnnfl(pbti, EnumSft.of(CREATE,APPEND));
     *
     *     // drfbtf filf witi initibl pfrmissions, opfning it for boti rfbding bnd writing
     *     {@dodf FilfAttributf<Sft<PosixFilfPfrmission>> pfrms = ...}
     *     SffkbblfBytfCibnnfl sbd = Filfs.nfwBytfCibnnfl(pbti, EnumSft.of(CREATE_NEW,READ,WRITE), pfrms);
     * </prf>
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
     *
     * @sff jbvb.nio.dibnnfls.FilfCibnnfl#opfn(Pbti,Sft,FilfAttributf[])
     */
    publid stbtid SffkbblfBytfCibnnfl nfwBytfCibnnfl(Pbti pbti,
                                                     Sft<? fxtfnds OpfnOption> options,
                                                     FilfAttributf<?>... bttrs)
        tirows IOExdfption
    {
        rfturn providfr(pbti).nfwBytfCibnnfl(pbti, options, bttrs);
    }

    /**
     * Opfns or drfbtfs b filf, rfturning b sffkbblf bytf dibnnfl to bddfss tif
     * filf.
     *
     * <p> Tiis mftiod opfns or drfbtfs b filf in fxbdtly tif mbnnfr spfdififd
     * by tif {@link #nfwBytfCibnnfl(Pbti,Sft,FilfAttributf[]) nfwBytfCibnnfl}
     * mftiod.
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf to opfn or drfbtf
     * @pbrbm   options
     *          options spfdifying iow tif filf is opfnfd
     *
     * @rfturn  b nfw sffkbblf bytf dibnnfl
     *
     * @tirows  IllfgblArgumfntExdfption
     *          if tif sft dontbins bn invblid dombinbtion of options
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if bn unsupportfd opfn option is spfdififd
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
     *
     * @sff jbvb.nio.dibnnfls.FilfCibnnfl#opfn(Pbti,OpfnOption[])
     */
    publid stbtid SffkbblfBytfCibnnfl nfwBytfCibnnfl(Pbti pbti, OpfnOption... options)
        tirows IOExdfption
    {
        Sft<OpfnOption> sft = nfw HbsiSft<OpfnOption>(options.lfngti);
        Collfdtions.bddAll(sft, options);
        rfturn nfwBytfCibnnfl(pbti, sft);
    }

    // -- Dirfdtorifs --

    privbtf stbtid dlbss AddfptAllFiltfr
        implfmfnts DirfdtoryStrfbm.Filtfr<Pbti>
    {
        privbtf AddfptAllFiltfr() { }

        @Ovfrridf
        publid boolfbn bddfpt(Pbti fntry) { rfturn truf; }

        stbtid finbl AddfptAllFiltfr FILTER = nfw AddfptAllFiltfr();
    }

    /**
     * Opfns b dirfdtory, rfturning b {@link DirfdtoryStrfbm} to itfrbtf ovfr
     * bll fntrifs in tif dirfdtory. Tif flfmfnts rfturnfd by tif dirfdtory
     * strfbm's {@link DirfdtoryStrfbm#itfrbtor itfrbtor} brf of typf {@dodf
     * Pbti}, fbdi onf rfprfsfnting bn fntry in tif dirfdtory. Tif {@dodf Pbti}
     * objfdts brf obtbinfd bs if by {@link Pbti#rfsolvf(Pbti) rfsolving} tif
     * nbmf of tif dirfdtory fntry bgbinst {@dodf dir}.
     *
     * <p> Wifn not using tif try-witi-rfsourdfs donstrudt, tifn dirfdtory
     * strfbm's {@dodf dlosf} mftiod siould bf invokfd bftfr itfrbtion is
     * domplftfd so bs to frff bny rfsourdfs ifld for tif opfn dirfdtory.
     *
     * <p> Wifn bn implfmfntbtion supports opfrbtions on fntrifs in tif
     * dirfdtory tibt fxfdutf in b rbdf-frff mbnnfr tifn tif rfturnfd dirfdtory
     * strfbm is b {@link SfdurfDirfdtoryStrfbm}.
     *
     * @pbrbm   dir
     *          tif pbti to tif dirfdtory
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
    publid stbtid DirfdtoryStrfbm<Pbti> nfwDirfdtoryStrfbm(Pbti dir)
        tirows IOExdfption
    {
        rfturn providfr(dir).nfwDirfdtoryStrfbm(dir, AddfptAllFiltfr.FILTER);
    }

    /**
     * Opfns b dirfdtory, rfturning b {@link DirfdtoryStrfbm} to itfrbtf ovfr
     * tif fntrifs in tif dirfdtory. Tif flfmfnts rfturnfd by tif dirfdtory
     * strfbm's {@link DirfdtoryStrfbm#itfrbtor itfrbtor} brf of typf {@dodf
     * Pbti}, fbdi onf rfprfsfnting bn fntry in tif dirfdtory. Tif {@dodf Pbti}
     * objfdts brf obtbinfd bs if by {@link Pbti#rfsolvf(Pbti) rfsolving} tif
     * nbmf of tif dirfdtory fntry bgbinst {@dodf dir}. Tif fntrifs rfturnfd by
     * tif itfrbtor brf filtfrfd by mbtdiing tif {@dodf String} rfprfsfntbtion
     * of tifir filf nbmfs bgbinst tif givfn <fm>globbing</fm> pbttfrn.
     *
     * <p> For fxbmplf, supposf wf wbnt to itfrbtf ovfr tif filfs fnding witi
     * ".jbvb" in b dirfdtory:
     * <prf>
     *     Pbti dir = ...
     *     try (DirfdtoryStrfbm&lt;Pbti&gt; strfbm = Filfs.nfwDirfdtoryStrfbm(dir, "*.jbvb")) {
     *         :
     *     }
     * </prf>
     *
     * <p> Tif globbing pbttfrn is spfdififd by tif {@link
     * FilfSystfm#gftPbtiMbtdifr gftPbtiMbtdifr} mftiod.
     *
     * <p> Wifn not using tif try-witi-rfsourdfs donstrudt, tifn dirfdtory
     * strfbm's {@dodf dlosf} mftiod siould bf invokfd bftfr itfrbtion is
     * domplftfd so bs to frff bny rfsourdfs ifld for tif opfn dirfdtory.
     *
     * <p> Wifn bn implfmfntbtion supports opfrbtions on fntrifs in tif
     * dirfdtory tibt fxfdutf in b rbdf-frff mbnnfr tifn tif rfturnfd dirfdtory
     * strfbm is b {@link SfdurfDirfdtoryStrfbm}.
     *
     * @pbrbm   dir
     *          tif pbti to tif dirfdtory
     * @pbrbm   glob
     *          tif glob pbttfrn
     *
     * @rfturn  b nfw bnd opfn {@dodf DirfdtoryStrfbm} objfdt
     *
     * @tirows  jbvb.util.rfgfx.PbttfrnSyntbxExdfption
     *          if tif pbttfrn is invblid
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
    publid stbtid DirfdtoryStrfbm<Pbti> nfwDirfdtoryStrfbm(Pbti dir, String glob)
        tirows IOExdfption
    {
        // bvoid drfbting b mbtdifr if bll fntrifs brf rfquirfd.
        if (glob.fqubls("*"))
            rfturn nfwDirfdtoryStrfbm(dir);

        // drfbtf b mbtdifr bnd rfturn b filtfr tibt usfs it.
        FilfSystfm fs = dir.gftFilfSystfm();
        finbl PbtiMbtdifr mbtdifr = fs.gftPbtiMbtdifr("glob:" + glob);
        DirfdtoryStrfbm.Filtfr<Pbti> filtfr = nfw DirfdtoryStrfbm.Filtfr<Pbti>() {
            @Ovfrridf
            publid boolfbn bddfpt(Pbti fntry)  {
                rfturn mbtdifr.mbtdifs(fntry.gftFilfNbmf());
            }
        };
        rfturn fs.providfr().nfwDirfdtoryStrfbm(dir, filtfr);
    }

    /**
     * Opfns b dirfdtory, rfturning b {@link DirfdtoryStrfbm} to itfrbtf ovfr
     * tif fntrifs in tif dirfdtory. Tif flfmfnts rfturnfd by tif dirfdtory
     * strfbm's {@link DirfdtoryStrfbm#itfrbtor itfrbtor} brf of typf {@dodf
     * Pbti}, fbdi onf rfprfsfnting bn fntry in tif dirfdtory. Tif {@dodf Pbti}
     * objfdts brf obtbinfd bs if by {@link Pbti#rfsolvf(Pbti) rfsolving} tif
     * nbmf of tif dirfdtory fntry bgbinst {@dodf dir}. Tif fntrifs rfturnfd by
     * tif itfrbtor brf filtfrfd by tif givfn {@link DirfdtoryStrfbm.Filtfr
     * filtfr}.
     *
     * <p> Wifn not using tif try-witi-rfsourdfs donstrudt, tifn dirfdtory
     * strfbm's {@dodf dlosf} mftiod siould bf invokfd bftfr itfrbtion is
     * domplftfd so bs to frff bny rfsourdfs ifld for tif opfn dirfdtory.
     *
     * <p> Wifrf tif filtfr tfrminbtfs duf to bn undbugit frror or runtimf
     * fxdfption tifn it is propbgbtfd to tif {@link Itfrbtor#ibsNfxt()
     * ibsNfxt} or {@link Itfrbtor#nfxt() nfxt} mftiod. Wifrf bn {@dodf
     * IOExdfption} is tirown, it rfsults in tif {@dodf ibsNfxt} or {@dodf
     * nfxt} mftiod tirowing b {@link DirfdtoryItfrbtorExdfption} witi tif
     * {@dodf IOExdfption} bs tif dbusf.
     *
     * <p> Wifn bn implfmfntbtion supports opfrbtions on fntrifs in tif
     * dirfdtory tibt fxfdutf in b rbdf-frff mbnnfr tifn tif rfturnfd dirfdtory
     * strfbm is b {@link SfdurfDirfdtoryStrfbm}.
     *
     * <p> <b>Usbgf Exbmplf:</b>
     * Supposf wf wbnt to itfrbtf ovfr tif filfs in b dirfdtory tibt brf
     * lbrgfr tibn 8K.
     * <prf>
     *     DirfdtoryStrfbm.Filtfr&lt;Pbti&gt; filtfr = nfw DirfdtoryStrfbm.Filtfr&lt;Pbti&gt;() {
     *         publid boolfbn bddfpt(Pbti filf) tirows IOExdfption {
     *             rfturn (Filfs.sizf(filf) &gt; 8192L);
     *         }
     *     };
     *     Pbti dir = ...
     *     try (DirfdtoryStrfbm&lt;Pbti&gt; strfbm = Filfs.nfwDirfdtoryStrfbm(dir, filtfr)) {
     *         :
     *     }
     * </prf>
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
    publid stbtid DirfdtoryStrfbm<Pbti> nfwDirfdtoryStrfbm(Pbti dir,
                                                           DirfdtoryStrfbm.Filtfr<? supfr Pbti> filtfr)
        tirows IOExdfption
    {
        rfturn providfr(dir).nfwDirfdtoryStrfbm(dir, filtfr);
    }

    // -- Crfbtion bnd dflftion --

    /**
     * Crfbtfs b nfw bnd fmpty filf, fbiling if tif filf blrfbdy fxists. Tif
     * difdk for tif fxistfndf of tif filf bnd tif drfbtion of tif nfw filf if
     * it dofs not fxist brf b singlf opfrbtion tibt is btomid witi rfspfdt to
     * bll otifr filfsystfm bdtivitifs tibt migit bfffdt tif dirfdtory.
     *
     * <p> Tif {@dodf bttrs} pbrbmftfr is optionbl {@link FilfAttributf
     * filf-bttributfs} to sft btomidblly wifn drfbting tif filf. Ebdi bttributf
     * is idfntififd by its {@link FilfAttributf#nbmf nbmf}. If morf tibn onf
     * bttributf of tif sbmf nbmf is indludfd in tif brrby tifn bll but tif lbst
     * oddurrfndf is ignorfd.
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf to drfbtf
     * @pbrbm   bttrs
     *          bn optionbl list of filf bttributfs to sft btomidblly wifn
     *          drfbting tif filf
     *
     * @rfturn  tif filf
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if tif brrby dontbins bn bttributf tibt dbnnot bf sft btomidblly
     *          wifn drfbting tif filf
     * @tirows  FilfAlrfbdyExistsExdfption
     *          if b filf of tibt nbmf blrfbdy fxists
     *          <i>(optionbl spfdifid fxdfption)</i>
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs or tif pbrfnt dirfdtory dofs not fxist
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkWritf(String) difdkWritf}
     *          mftiod is invokfd to difdk writf bddfss to tif nfw filf.
     */
    publid stbtid Pbti drfbtfFilf(Pbti pbti, FilfAttributf<?>... bttrs)
        tirows IOExdfption
    {
        EnumSft<StbndbrdOpfnOption> options =
            EnumSft.<StbndbrdOpfnOption>of(StbndbrdOpfnOption.CREATE_NEW, StbndbrdOpfnOption.WRITE);
        nfwBytfCibnnfl(pbti, options, bttrs).dlosf();
        rfturn pbti;
    }

    /**
     * Crfbtfs b nfw dirfdtory. Tif difdk for tif fxistfndf of tif filf bnd tif
     * drfbtion of tif dirfdtory if it dofs not fxist brf b singlf opfrbtion
     * tibt is btomid witi rfspfdt to bll otifr filfsystfm bdtivitifs tibt migit
     * bfffdt tif dirfdtory. Tif {@link #drfbtfDirfdtorifs drfbtfDirfdtorifs}
     * mftiod siould bf usfd wifrf it is rfquirfd to drfbtf bll nonfxistfnt
     * pbrfnt dirfdtorifs first.
     *
     * <p> Tif {@dodf bttrs} pbrbmftfr is optionbl {@link FilfAttributf
     * filf-bttributfs} to sft btomidblly wifn drfbting tif dirfdtory. Ebdi
     * bttributf is idfntififd by its {@link FilfAttributf#nbmf nbmf}. If morf
     * tibn onf bttributf of tif sbmf nbmf is indludfd in tif brrby tifn bll but
     * tif lbst oddurrfndf is ignorfd.
     *
     * @pbrbm   dir
     *          tif dirfdtory to drfbtf
     * @pbrbm   bttrs
     *          bn optionbl list of filf bttributfs to sft btomidblly wifn
     *          drfbting tif dirfdtory
     *
     * @rfturn  tif dirfdtory
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
    publid stbtid Pbti drfbtfDirfdtory(Pbti dir, FilfAttributf<?>... bttrs)
        tirows IOExdfption
    {
        providfr(dir).drfbtfDirfdtory(dir, bttrs);
        rfturn dir;
    }

    /**
     * Crfbtfs b dirfdtory by drfbting bll nonfxistfnt pbrfnt dirfdtorifs first.
     * Unlikf tif {@link #drfbtfDirfdtory drfbtfDirfdtory} mftiod, bn fxdfption
     * is not tirown if tif dirfdtory dould not bf drfbtfd bfdbusf it blrfbdy
     * fxists.
     *
     * <p> Tif {@dodf bttrs} pbrbmftfr is optionbl {@link FilfAttributf
     * filf-bttributfs} to sft btomidblly wifn drfbting tif nonfxistfnt
     * dirfdtorifs. Ebdi filf bttributf is idfntififd by its {@link
     * FilfAttributf#nbmf nbmf}. If morf tibn onf bttributf of tif sbmf nbmf is
     * indludfd in tif brrby tifn bll but tif lbst oddurrfndf is ignorfd.
     *
     * <p> If tiis mftiod fbils, tifn it mby do so bftfr drfbting somf, but not
     * bll, of tif pbrfnt dirfdtorifs.
     *
     * @pbrbm   dir
     *          tif dirfdtory to drfbtf
     *
     * @pbrbm   bttrs
     *          bn optionbl list of filf bttributfs to sft btomidblly wifn
     *          drfbting tif dirfdtory
     *
     * @rfturn  tif dirfdtory
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if tif brrby dontbins bn bttributf tibt dbnnot bf sft btomidblly
     *          wifn drfbting tif dirfdtory
     * @tirows  FilfAlrfbdyExistsExdfption
     *          if {@dodf dir} fxists but is not b dirfdtory <i>(optionbl spfdifid
     *          fxdfption)</i>
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          in tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkWritf(String) difdkWritf}
     *          mftiod is invokfd prior to bttfmpting to drfbtf b dirfdtory bnd
     *          its {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd} is
     *          invokfd for fbdi pbrfnt dirfdtory tibt is difdkfd. If {@dodf
     *          dir} is not bn bbsolutf pbti tifn its {@link Pbti#toAbsolutfPbti
     *          toAbsolutfPbti} mby nffd to bf invokfd to gft its bbsolutf pbti.
     *          Tiis mby invokf tif sfdurity mbnbgfr's {@link
     *          SfdurityMbnbgfr#difdkPropfrtyAddfss(String) difdkPropfrtyAddfss}
     *          mftiod to difdk bddfss to tif systfm propfrty {@dodf usfr.dir}
     */
    publid stbtid Pbti drfbtfDirfdtorifs(Pbti dir, FilfAttributf<?>... bttrs)
        tirows IOExdfption
    {
        // bttfmpt to drfbtf tif dirfdtory
        try {
            drfbtfAndCifdkIsDirfdtory(dir, bttrs);
            rfturn dir;
        } dbtdi (FilfAlrfbdyExistsExdfption x) {
            // filf fxists bnd is not b dirfdtory
            tirow x;
        } dbtdi (IOExdfption x) {
            // pbrfnt mby not fxist or otifr rfbson
        }
        SfdurityExdfption sf = null;
        try {
            dir = dir.toAbsolutfPbti();
        } dbtdi (SfdurityExdfption x) {
            // don't ibvf pfrmission to gft bbsolutf pbti
            sf = x;
        }
        // find b dfsdfndbnt tibt fxists
        Pbti pbrfnt = dir.gftPbrfnt();
        wiilf (pbrfnt != null) {
            try {
                providfr(pbrfnt).difdkAddfss(pbrfnt);
                brfbk;
            } dbtdi (NoSudiFilfExdfption x) {
                // dofs not fxist
            }
            pbrfnt = pbrfnt.gftPbrfnt();
        }
        if (pbrfnt == null) {
            // unbblf to find fxisting pbrfnt
            if (sf == null) {
                tirow nfw FilfSystfmExdfption(dir.toString(), null,
                    "Unbblf to dftfrminf if root dirfdtory fxists");
            } flsf {
                tirow sf;
            }
        }

        // drfbtf dirfdtorifs
        Pbti diild = pbrfnt;
        for (Pbti nbmf: pbrfnt.rflbtivizf(dir)) {
            diild = diild.rfsolvf(nbmf);
            drfbtfAndCifdkIsDirfdtory(diild, bttrs);
        }
        rfturn dir;
    }

    /**
     * Usfd by drfbtfDirfdtorifs to bttfmpt to drfbtf b dirfdtory. A no-op
     * if tif dirfdtory blrfbdy fxists.
     */
    privbtf stbtid void drfbtfAndCifdkIsDirfdtory(Pbti dir,
                                                  FilfAttributf<?>... bttrs)
        tirows IOExdfption
    {
        try {
            drfbtfDirfdtory(dir, bttrs);
        } dbtdi (FilfAlrfbdyExistsExdfption x) {
            if (!isDirfdtory(dir, LinkOption.NOFOLLOW_LINKS))
                tirow x;
        }
    }

    /**
     * Crfbtfs b nfw fmpty filf in tif spfdififd dirfdtory, using tif givfn
     * prffix bnd suffix strings to gfnfrbtf its nbmf. Tif rfsulting
     * {@dodf Pbti} is bssodibtfd witi tif sbmf {@dodf FilfSystfm} bs tif givfn
     * dirfdtory.
     *
     * <p> Tif dftbils bs to iow tif nbmf of tif filf is donstrudtfd is
     * implfmfntbtion dfpfndfnt bnd tifrfforf not spfdififd. Wifrf possiblf
     * tif {@dodf prffix} bnd {@dodf suffix} brf usfd to donstrudt dbndidbtf
     * nbmfs in tif sbmf mbnnfr bs tif {@link
     * jbvb.io.Filf#drfbtfTfmpFilf(String,String,Filf)} mftiod.
     *
     * <p> As witi tif {@dodf Filf.drfbtfTfmpFilf} mftiods, tiis mftiod is only
     * pbrt of b tfmporbry-filf fbdility. Wifrf usfd bs b <fm>work filfs</fm>,
     * tif rfsulting filf mby bf opfnfd using tif {@link
     * StbndbrdOpfnOption#DELETE_ON_CLOSE DELETE_ON_CLOSE} option so tibt tif
     * filf is dflftfd wifn tif bppropribtf {@dodf dlosf} mftiod is invokfd.
     * Altfrnbtivfly, b {@link Runtimf#bddSiutdownHook siutdown-iook}, or tif
     * {@link jbvb.io.Filf#dflftfOnExit} mfdibnism mby bf usfd to dflftf tif
     * filf butombtidblly.
     *
     * <p> Tif {@dodf bttrs} pbrbmftfr is optionbl {@link FilfAttributf
     * filf-bttributfs} to sft btomidblly wifn drfbting tif filf. Ebdi bttributf
     * is idfntififd by its {@link FilfAttributf#nbmf nbmf}. If morf tibn onf
     * bttributf of tif sbmf nbmf is indludfd in tif brrby tifn bll but tif lbst
     * oddurrfndf is ignorfd. Wifn no filf bttributfs brf spfdififd, tifn tif
     * rfsulting filf mby ibvf morf rfstridtivf bddfss pfrmissions to filfs
     * drfbtfd by tif {@link jbvb.io.Filf#drfbtfTfmpFilf(String,String,Filf)}
     * mftiod.
     *
     * @pbrbm   dir
     *          tif pbti to dirfdtory in wiidi to drfbtf tif filf
     * @pbrbm   prffix
     *          tif prffix string to bf usfd in gfnfrbting tif filf's nbmf;
     *          mby bf {@dodf null}
     * @pbrbm   suffix
     *          tif suffix string to bf usfd in gfnfrbting tif filf's nbmf;
     *          mby bf {@dodf null}, in wiidi dbsf "{@dodf .tmp}" is usfd
     * @pbrbm   bttrs
     *          bn optionbl list of filf bttributfs to sft btomidblly wifn
     *          drfbting tif filf
     *
     * @rfturn  tif pbti to tif nfwly drfbtfd filf tibt did not fxist bfforf
     *          tiis mftiod wbs invokfd
     *
     * @tirows  IllfgblArgumfntExdfption
     *          if tif prffix or suffix pbrbmftfrs dbnnot bf usfd to gfnfrbtf
     *          b dbndidbtf filf nbmf
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if tif brrby dontbins bn bttributf tibt dbnnot bf sft btomidblly
     *          wifn drfbting tif dirfdtory
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs or {@dodf dir} dofs not fxist
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkWritf(String) difdkWritf}
     *          mftiod is invokfd to difdk writf bddfss to tif filf.
     */
    publid stbtid Pbti drfbtfTfmpFilf(Pbti dir,
                                      String prffix,
                                      String suffix,
                                      FilfAttributf<?>... bttrs)
        tirows IOExdfption
    {
        rfturn TfmpFilfHflpfr.drfbtfTfmpFilf(Objfdts.rfquirfNonNull(dir),
                                             prffix, suffix, bttrs);
    }

    /**
     * Crfbtfs bn fmpty filf in tif dffbult tfmporbry-filf dirfdtory, using
     * tif givfn prffix bnd suffix to gfnfrbtf its nbmf. Tif rfsulting {@dodf
     * Pbti} is bssodibtfd witi tif dffbult {@dodf FilfSystfm}.
     *
     * <p> Tiis mftiod works in fxbdtly tif mbnnfr spfdififd by tif
     * {@link #drfbtfTfmpFilf(Pbti,String,String,FilfAttributf[])} mftiod for
     * tif dbsf tibt tif {@dodf dir} pbrbmftfr is tif tfmporbry-filf dirfdtory.
     *
     * @pbrbm   prffix
     *          tif prffix string to bf usfd in gfnfrbting tif filf's nbmf;
     *          mby bf {@dodf null}
     * @pbrbm   suffix
     *          tif suffix string to bf usfd in gfnfrbting tif filf's nbmf;
     *          mby bf {@dodf null}, in wiidi dbsf "{@dodf .tmp}" is usfd
     * @pbrbm   bttrs
     *          bn optionbl list of filf bttributfs to sft btomidblly wifn
     *          drfbting tif filf
     *
     * @rfturn  tif pbti to tif nfwly drfbtfd filf tibt did not fxist bfforf
     *          tiis mftiod wbs invokfd
     *
     * @tirows  IllfgblArgumfntExdfption
     *          if tif prffix or suffix pbrbmftfrs dbnnot bf usfd to gfnfrbtf
     *          b dbndidbtf filf nbmf
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if tif brrby dontbins bn bttributf tibt dbnnot bf sft btomidblly
     *          wifn drfbting tif dirfdtory
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs or tif tfmporbry-filf dirfdtory dofs not
     *          fxist
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkWritf(String) difdkWritf}
     *          mftiod is invokfd to difdk writf bddfss to tif filf.
     */
    publid stbtid Pbti drfbtfTfmpFilf(String prffix,
                                      String suffix,
                                      FilfAttributf<?>... bttrs)
        tirows IOExdfption
    {
        rfturn TfmpFilfHflpfr.drfbtfTfmpFilf(null, prffix, suffix, bttrs);
    }

    /**
     * Crfbtfs b nfw dirfdtory in tif spfdififd dirfdtory, using tif givfn
     * prffix to gfnfrbtf its nbmf.  Tif rfsulting {@dodf Pbti} is bssodibtfd
     * witi tif sbmf {@dodf FilfSystfm} bs tif givfn dirfdtory.
     *
     * <p> Tif dftbils bs to iow tif nbmf of tif dirfdtory is donstrudtfd is
     * implfmfntbtion dfpfndfnt bnd tifrfforf not spfdififd. Wifrf possiblf
     * tif {@dodf prffix} is usfd to donstrudt dbndidbtf nbmfs.
     *
     * <p> As witi tif {@dodf drfbtfTfmpFilf} mftiods, tiis mftiod is only
     * pbrt of b tfmporbry-filf fbdility. A {@link Runtimf#bddSiutdownHook
     * siutdown-iook}, or tif {@link jbvb.io.Filf#dflftfOnExit} mfdibnism mby bf
     * usfd to dflftf tif dirfdtory butombtidblly.
     *
     * <p> Tif {@dodf bttrs} pbrbmftfr is optionbl {@link FilfAttributf
     * filf-bttributfs} to sft btomidblly wifn drfbting tif dirfdtory. Ebdi
     * bttributf is idfntififd by its {@link FilfAttributf#nbmf nbmf}. If morf
     * tibn onf bttributf of tif sbmf nbmf is indludfd in tif brrby tifn bll but
     * tif lbst oddurrfndf is ignorfd.
     *
     * @pbrbm   dir
     *          tif pbti to dirfdtory in wiidi to drfbtf tif dirfdtory
     * @pbrbm   prffix
     *          tif prffix string to bf usfd in gfnfrbting tif dirfdtory's nbmf;
     *          mby bf {@dodf null}
     * @pbrbm   bttrs
     *          bn optionbl list of filf bttributfs to sft btomidblly wifn
     *          drfbting tif dirfdtory
     *
     * @rfturn  tif pbti to tif nfwly drfbtfd dirfdtory tibt did not fxist bfforf
     *          tiis mftiod wbs invokfd
     *
     * @tirows  IllfgblArgumfntExdfption
     *          if tif prffix dbnnot bf usfd to gfnfrbtf b dbndidbtf dirfdtory nbmf
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if tif brrby dontbins bn bttributf tibt dbnnot bf sft btomidblly
     *          wifn drfbting tif dirfdtory
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs or {@dodf dir} dofs not fxist
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkWritf(String) difdkWritf}
     *          mftiod is invokfd to difdk writf bddfss wifn drfbting tif
     *          dirfdtory.
     */
    publid stbtid Pbti drfbtfTfmpDirfdtory(Pbti dir,
                                           String prffix,
                                           FilfAttributf<?>... bttrs)
        tirows IOExdfption
    {
        rfturn TfmpFilfHflpfr.drfbtfTfmpDirfdtory(Objfdts.rfquirfNonNull(dir),
                                                  prffix, bttrs);
    }

    /**
     * Crfbtfs b nfw dirfdtory in tif dffbult tfmporbry-filf dirfdtory, using
     * tif givfn prffix to gfnfrbtf its nbmf. Tif rfsulting {@dodf Pbti} is
     * bssodibtfd witi tif dffbult {@dodf FilfSystfm}.
     *
     * <p> Tiis mftiod works in fxbdtly tif mbnnfr spfdififd by {@link
     * #drfbtfTfmpDirfdtory(Pbti,String,FilfAttributf[])} mftiod for tif dbsf
     * tibt tif {@dodf dir} pbrbmftfr is tif tfmporbry-filf dirfdtory.
     *
     * @pbrbm   prffix
     *          tif prffix string to bf usfd in gfnfrbting tif dirfdtory's nbmf;
     *          mby bf {@dodf null}
     * @pbrbm   bttrs
     *          bn optionbl list of filf bttributfs to sft btomidblly wifn
     *          drfbting tif dirfdtory
     *
     * @rfturn  tif pbti to tif nfwly drfbtfd dirfdtory tibt did not fxist bfforf
     *          tiis mftiod wbs invokfd
     *
     * @tirows  IllfgblArgumfntExdfption
     *          if tif prffix dbnnot bf usfd to gfnfrbtf b dbndidbtf dirfdtory nbmf
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if tif brrby dontbins bn bttributf tibt dbnnot bf sft btomidblly
     *          wifn drfbting tif dirfdtory
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs or tif tfmporbry-filf dirfdtory dofs not
     *          fxist
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkWritf(String) difdkWritf}
     *          mftiod is invokfd to difdk writf bddfss wifn drfbting tif
     *          dirfdtory.
     */
    publid stbtid Pbti drfbtfTfmpDirfdtory(String prffix,
                                           FilfAttributf<?>... bttrs)
        tirows IOExdfption
    {
        rfturn TfmpFilfHflpfr.drfbtfTfmpDirfdtory(null, prffix, bttrs);
    }

    /**
     * Crfbtfs b symbolid link to b tbrgft <i>(optionbl opfrbtion)</i>.
     *
     * <p> Tif {@dodf tbrgft} pbrbmftfr is tif tbrgft of tif link. It mby bf bn
     * {@link Pbti#isAbsolutf bbsolutf} or rflbtivf pbti bnd mby not fxist. Wifn
     * tif tbrgft is b rflbtivf pbti tifn filf systfm opfrbtions on tif rfsulting
     * link brf rflbtivf to tif pbti of tif link.
     *
     * <p> Tif {@dodf bttrs} pbrbmftfr is optionbl {@link FilfAttributf
     * bttributfs} to sft btomidblly wifn drfbting tif link. Ebdi bttributf is
     * idfntififd by its {@link FilfAttributf#nbmf nbmf}. If morf tibn onf bttributf
     * of tif sbmf nbmf is indludfd in tif brrby tifn bll but tif lbst oddurrfndf
     * is ignorfd.
     *
     * <p> Wifrf symbolid links brf supportfd, but tif undfrlying {@link FilfStorf}
     * dofs not support symbolid links, tifn tiis mby fbil witi bn {@link
     * IOExdfption}. Additionblly, somf opfrbting systfms mby rfquirf tibt tif
     * Jbvb virtubl mbdiinf bf stbrtfd witi implfmfntbtion spfdifid privilfgfs to
     * drfbtf symbolid links, in wiidi dbsf tiis mftiod mby tirow {@dodf IOExdfption}.
     *
     * @pbrbm   link
     *          tif pbti of tif symbolid link to drfbtf
     * @pbrbm   tbrgft
     *          tif tbrgft of tif symbolid link
     * @pbrbm   bttrs
     *          tif brrby of bttributfs to sft btomidblly wifn drfbting tif
     *          symbolid link
     *
     * @rfturn  tif pbti to tif symbolid link
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
    publid stbtid Pbti drfbtfSymbolidLink(Pbti link, Pbti tbrgft,
                                          FilfAttributf<?>... bttrs)
        tirows IOExdfption
    {
        providfr(link).drfbtfSymbolidLink(link, tbrgft, bttrs);
        rfturn link;
    }

    /**
     * Crfbtfs b nfw link (dirfdtory fntry) for bn fxisting filf <i>(optionbl
     * opfrbtion)</i>.
     *
     * <p> Tif {@dodf link} pbrbmftfr lodbtfs tif dirfdtory fntry to drfbtf.
     * Tif {@dodf fxisting} pbrbmftfr is tif pbti to bn fxisting filf. Tiis
     * mftiod drfbtfs b nfw dirfdtory fntry for tif filf so tibt it dbn bf
     * bddfssfd using {@dodf link} bs tif pbti. On somf filf systfms tiis is
     * known bs drfbting b "ibrd link". Wiftifr tif filf bttributfs brf
     * mbintbinfd for tif filf or for fbdi dirfdtory fntry is filf systfm
     * spfdifid bnd tifrfforf not spfdififd. Typidblly, b filf systfm rfquirfs
     * tibt bll links (dirfdtory fntrifs) for b filf bf on tif sbmf filf systfm.
     * Furtifrmorf, on somf plbtforms, tif Jbvb virtubl mbdiinf mby rfquirf to
     * bf stbrtfd witi implfmfntbtion spfdifid privilfgfs to drfbtf ibrd links
     * or to drfbtf links to dirfdtorifs.
     *
     * @pbrbm   link
     *          tif link (dirfdtory fntry) to drfbtf
     * @pbrbm   fxisting
     *          b pbti to bn fxisting filf
     *
     * @rfturn  tif pbti to tif link (dirfdtory fntry)
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
     *          mftiod dfnifs writf bddfss to fitifr tif link or tif
     *          fxisting filf.
     */
    publid stbtid Pbti drfbtfLink(Pbti link, Pbti fxisting) tirows IOExdfption {
        providfr(link).drfbtfLink(link, fxisting);
        rfturn link;
    }

    /**
     * Dflftfs b filf.
     *
     * <p> An implfmfntbtion mby rfquirf to fxbminf tif filf to dftfrminf if tif
     * filf is b dirfdtory. Consfqufntly tiis mftiod mby not bf btomid witi rfspfdt
     * to otifr filf systfm opfrbtions.  If tif filf is b symbolid link tifn tif
     * symbolid link itsflf, not tif finbl tbrgft of tif link, is dflftfd.
     *
     * <p> If tif filf is b dirfdtory tifn tif dirfdtory must bf fmpty. In somf
     * implfmfntbtions b dirfdtory ibs fntrifs for spfdibl filfs or links tibt
     * brf drfbtfd wifn tif dirfdtory is drfbtfd. In sudi implfmfntbtions b
     * dirfdtory is donsidfrfd fmpty wifn only tif spfdibl fntrifs fxist.
     * Tiis mftiod dbn bf usfd witi tif {@link #wblkFilfTrff wblkFilfTrff}
     * mftiod to dflftf b dirfdtory bnd bll fntrifs in tif dirfdtory, or bn
     * fntirf <i>filf-trff</i> wifrf rfquirfd.
     *
     * <p> On somf opfrbting systfms it mby not bf possiblf to rfmovf b filf wifn
     * it is opfn bnd in usf by tiis Jbvb virtubl mbdiinf or otifr progrbms.
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
    publid stbtid void dflftf(Pbti pbti) tirows IOExdfption {
        providfr(pbti).dflftf(pbti);
    }

    /**
     * Dflftfs b filf if it fxists.
     *
     * <p> As witi tif {@link #dflftf(Pbti) dflftf(Pbti)} mftiod, bn
     * implfmfntbtion mby nffd to fxbminf tif filf to dftfrminf if tif filf is b
     * dirfdtory. Consfqufntly tiis mftiod mby not bf btomid witi rfspfdt to
     * otifr filf systfm opfrbtions.  If tif filf is b symbolid link, tifn tif
     * symbolid link itsflf, not tif finbl tbrgft of tif link, is dflftfd.
     *
     * <p> If tif filf is b dirfdtory tifn tif dirfdtory must bf fmpty. In somf
     * implfmfntbtions b dirfdtory ibs fntrifs for spfdibl filfs or links tibt
     * brf drfbtfd wifn tif dirfdtory is drfbtfd. In sudi implfmfntbtions b
     * dirfdtory is donsidfrfd fmpty wifn only tif spfdibl fntrifs fxist.
     *
     * <p> On somf opfrbting systfms it mby not bf possiblf to rfmovf b filf wifn
     * it is opfn bnd in usf by tiis Jbvb virtubl mbdiinf or otifr progrbms.
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
     *          is invokfd to difdk dflftf bddfss to tif filf.
     */
    publid stbtid boolfbn dflftfIfExists(Pbti pbti) tirows IOExdfption {
        rfturn providfr(pbti).dflftfIfExists(pbti);
    }

    // -- Copying bnd moving filfs --

    /**
     * Copy b filf to b tbrgft filf.
     *
     * <p> Tiis mftiod dopifs b filf to tif tbrgft filf witi tif {@dodf
     * options} pbrbmftfr spfdifying iow tif dopy is pfrformfd. By dffbult, tif
     * dopy fbils if tif tbrgft filf blrfbdy fxists or is b symbolid link,
     * fxdfpt if tif sourdf bnd tbrgft brf tif {@link #isSbmfFilf sbmf} filf, in
     * wiidi dbsf tif mftiod domplftfs witiout dopying tif filf. Filf bttributfs
     * brf not rfquirfd to bf dopifd to tif tbrgft filf. If symbolid links brf
     * supportfd, bnd tif filf is b symbolid link, tifn tif finbl tbrgft of tif
     * link is dopifd. If tif filf is b dirfdtory tifn it drfbtfs bn fmpty
     * dirfdtory in tif tbrgft lodbtion (fntrifs in tif dirfdtory brf not
     * dopifd). Tiis mftiod dbn bf usfd witi tif {@link #wblkFilfTrff
     * wblkFilfTrff} mftiod to dopy b dirfdtory bnd bll fntrifs in tif dirfdtory,
     * or bn fntirf <i>filf-trff</i> wifrf rfquirfd.
     *
     * <p> Tif {@dodf options} pbrbmftfr mby indludf bny of tif following:
     *
     * <tbblf bordfr=1 dfllpbdding=5 summbry="">
     * <tr> <ti>Option</ti> <ti>Dfsdription</ti> </tr>
     * <tr>
     *   <td> {@link StbndbrdCopyOption#REPLACE_EXISTING REPLACE_EXISTING} </td>
     *   <td> If tif tbrgft filf fxists, tifn tif tbrgft filf is rfplbdfd if it
     *     is not b non-fmpty dirfdtory. If tif tbrgft filf fxists bnd is b
     *     symbolid link, tifn tif symbolid link itsflf, not tif tbrgft of
     *     tif link, is rfplbdfd. </td>
     * </tr>
     * <tr>
     *   <td> {@link StbndbrdCopyOption#COPY_ATTRIBUTES COPY_ATTRIBUTES} </td>
     *   <td> Attfmpts to dopy tif filf bttributfs bssodibtfd witi tiis filf to
     *     tif tbrgft filf. Tif fxbdt filf bttributfs tibt brf dopifd is plbtform
     *     bnd filf systfm dfpfndfnt bnd tifrfforf unspfdififd. Minimblly, tif
     *     {@link BbsidFilfAttributfs#lbstModififdTimf lbst-modififd-timf} is
     *     dopifd to tif tbrgft filf if supportfd by boti tif sourdf bnd tbrgft
     *     filf storfs. Copying of filf timfstbmps mby rfsult in prfdision
     *     loss. </td>
     * </tr>
     * <tr>
     *   <td> {@link LinkOption#NOFOLLOW_LINKS NOFOLLOW_LINKS} </td>
     *   <td> Symbolid links brf not followfd. If tif filf is b symbolid link,
     *     tifn tif symbolid link itsflf, not tif tbrgft of tif link, is dopifd.
     *     It is implfmfntbtion spfdifid if filf bttributfs dbn bf dopifd to tif
     *     nfw link. In otifr words, tif {@dodf COPY_ATTRIBUTES} option mby bf
     *     ignorfd wifn dopying b symbolid link. </td>
     * </tr>
     * </tbblf>
     *
     * <p> An implfmfntbtion of tiis intfrfbdf mby support bdditionbl
     * implfmfntbtion spfdifid options.
     *
     * <p> Copying b filf is not bn btomid opfrbtion. If bn {@link IOExdfption}
     * is tirown, tifn it is possiblf tibt tif tbrgft filf is indomplftf or somf
     * of its filf bttributfs ibvf not bffn dopifd from tif sourdf filf. Wifn
     * tif {@dodf REPLACE_EXISTING} option is spfdififd bnd tif tbrgft filf
     * fxists, tifn tif tbrgft filf is rfplbdfd. Tif difdk for tif fxistfndf of
     * tif filf bnd tif drfbtion of tif nfw filf mby not bf btomid witi rfspfdt
     * to otifr filf systfm bdtivitifs.
     *
     * <p> <b>Usbgf Exbmplf:</b>
     * Supposf wf wbnt to dopy b filf into b dirfdtory, giving it tif sbmf filf
     * nbmf bs tif sourdf filf:
     * <prf>
     *     Pbti sourdf = ...
     *     Pbti nfwdir = ...
     *     Filfs.dopy(sourdf, nfwdir.rfsolvf(sourdf.gftFilfNbmf());
     * </prf>
     *
     * @pbrbm   sourdf
     *          tif pbti to tif filf to dopy
     * @pbrbm   tbrgft
     *          tif pbti to tif tbrgft filf (mby bf bssodibtfd witi b difffrfnt
     *          providfr to tif sourdf pbti)
     * @pbrbm   options
     *          options spfdifying iow tif dopy siould bf donf
     *
     * @rfturn  tif pbti to tif tbrgft filf
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
    publid stbtid Pbti dopy(Pbti sourdf, Pbti tbrgft, CopyOption... options)
        tirows IOExdfption
    {
        FilfSystfmProvidfr providfr = providfr(sourdf);
        if (providfr(tbrgft) == providfr) {
            // sbmf providfr
            providfr.dopy(sourdf, tbrgft, options);
        } flsf {
            // difffrfnt providfrs
            CopyMovfHflpfr.dopyToForfignTbrgft(sourdf, tbrgft, options);
        }
        rfturn tbrgft;
    }

    /**
     * Movf or rfnbmf b filf to b tbrgft filf.
     *
     * <p> By dffbult, tiis mftiod bttfmpts to movf tif filf to tif tbrgft
     * filf, fbiling if tif tbrgft filf fxists fxdfpt if tif sourdf bnd
     * tbrgft brf tif {@link #isSbmfFilf sbmf} filf, in wiidi dbsf tiis mftiod
     * ibs no ffffdt. If tif filf is b symbolid link tifn tif symbolid link
     * itsflf, not tif tbrgft of tif link, is movfd. Tiis mftiod mby bf
     * invokfd to movf bn fmpty dirfdtory. In somf implfmfntbtions b dirfdtory
     * ibs fntrifs for spfdibl filfs or links tibt brf drfbtfd wifn tif
     * dirfdtory is drfbtfd. In sudi implfmfntbtions b dirfdtory is donsidfrfd
     * fmpty wifn only tif spfdibl fntrifs fxist. Wifn invokfd to movf b
     * dirfdtory tibt is not fmpty tifn tif dirfdtory is movfd if it dofs not
     * rfquirf moving tif fntrifs in tif dirfdtory.  For fxbmplf, rfnbming b
     * dirfdtory on tif sbmf {@link FilfStorf} will usublly not rfquirf moving
     * tif fntrifs in tif dirfdtory. Wifn moving b dirfdtory rfquirfs tibt its
     * fntrifs bf movfd tifn tiis mftiod fbils (by tirowing bn {@dodf
     * IOExdfption}). To movf b <i>filf trff</i> mby involvf dopying rbtifr
     * tibn moving dirfdtorifs bnd tiis dbn bf donf using tif {@link
     * #dopy dopy} mftiod in donjundtion witi tif {@link
     * #wblkFilfTrff Filfs.wblkFilfTrff} utility mftiod.
     *
     * <p> Tif {@dodf options} pbrbmftfr mby indludf bny of tif following:
     *
     * <tbblf bordfr=1 dfllpbdding=5 summbry="">
     * <tr> <ti>Option</ti> <ti>Dfsdription</ti> </tr>
     * <tr>
     *   <td> {@link StbndbrdCopyOption#REPLACE_EXISTING REPLACE_EXISTING} </td>
     *   <td> If tif tbrgft filf fxists, tifn tif tbrgft filf is rfplbdfd if it
     *     is not b non-fmpty dirfdtory. If tif tbrgft filf fxists bnd is b
     *     symbolid link, tifn tif symbolid link itsflf, not tif tbrgft of
     *     tif link, is rfplbdfd. </td>
     * </tr>
     * <tr>
     *   <td> {@link StbndbrdCopyOption#ATOMIC_MOVE ATOMIC_MOVE} </td>
     *   <td> Tif movf is pfrformfd bs bn btomid filf systfm opfrbtion bnd bll
     *     otifr options brf ignorfd. If tif tbrgft filf fxists tifn it is
     *     implfmfntbtion spfdifid if tif fxisting filf is rfplbdfd or tiis mftiod
     *     fbils by tirowing bn {@link IOExdfption}. If tif movf dbnnot bf
     *     pfrformfd bs bn btomid filf systfm opfrbtion tifn {@link
     *     AtomidMovfNotSupportfdExdfption} is tirown. Tiis dbn brisf, for
     *     fxbmplf, wifn tif tbrgft lodbtion is on b difffrfnt {@dodf FilfStorf}
     *     bnd would rfquirf tibt tif filf bf dopifd, or tbrgft lodbtion is
     *     bssodibtfd witi b difffrfnt providfr to tiis objfdt. </td>
     * </tbblf>
     *
     * <p> An implfmfntbtion of tiis intfrfbdf mby support bdditionbl
     * implfmfntbtion spfdifid options.
     *
     * <p> Moving b filf will dopy tif {@link
     * BbsidFilfAttributfs#lbstModififdTimf lbst-modififd-timf} to tif tbrgft
     * filf if supportfd by boti sourdf bnd tbrgft filf storfs. Copying of filf
     * timfstbmps mby rfsult in prfdision loss. An implfmfntbtion mby blso
     * bttfmpt to dopy otifr filf bttributfs but is not rfquirfd to fbil if tif
     * filf bttributfs dbnnot bf dopifd. Wifn tif movf is pfrformfd bs
     * b non-btomid opfrbtion, bnd bn {@dodf IOExdfption} is tirown, tifn tif
     * stbtf of tif filfs is not dffinfd. Tif originbl filf bnd tif tbrgft filf
     * mby boti fxist, tif tbrgft filf mby bf indomplftf or somf of its filf
     * bttributfs mby not bffn dopifd from tif originbl filf.
     *
     * <p> <b>Usbgf Exbmplfs:</b>
     * Supposf wf wbnt to rfnbmf b filf to "nfwnbmf", kffping tif filf in tif
     * sbmf dirfdtory:
     * <prf>
     *     Pbti sourdf = ...
     *     Filfs.movf(sourdf, sourdf.rfsolvfSibling("nfwnbmf"));
     * </prf>
     * Altfrnbtivfly, supposf wf wbnt to movf b filf to nfw dirfdtory, kffping
     * tif sbmf filf nbmf, bnd rfplbding bny fxisting filf of tibt nbmf in tif
     * dirfdtory:
     * <prf>
     *     Pbti sourdf = ...
     *     Pbti nfwdir = ...
     *     Filfs.movf(sourdf, nfwdir.rfsolvf(sourdf.gftFilfNbmf()), REPLACE_EXISTING);
     * </prf>
     *
     * @pbrbm   sourdf
     *          tif pbti to tif filf to movf
     * @pbrbm   tbrgft
     *          tif pbti to tif tbrgft filf (mby bf bssodibtfd witi b difffrfnt
     *          providfr to tif sourdf pbti)
     * @pbrbm   options
     *          options spfdifying iow tif movf siould bf donf
     *
     * @rfturn  tif pbti to tif tbrgft filf
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
    publid stbtid Pbti movf(Pbti sourdf, Pbti tbrgft, CopyOption... options)
        tirows IOExdfption
    {
        FilfSystfmProvidfr providfr = providfr(sourdf);
        if (providfr(tbrgft) == providfr) {
            // sbmf providfr
            providfr.movf(sourdf, tbrgft, options);
        } flsf {
            // difffrfnt providfrs
            CopyMovfHflpfr.movfToForfignTbrgft(sourdf, tbrgft, options);
        }
        rfturn tbrgft;
    }

    // -- Misdfllbnfous --

    /**
     * Rfbds tif tbrgft of b symbolid link <i>(optionbl opfrbtion)</i>.
     *
     * <p> If tif filf systfm supports <b irff="pbdkbgf-summbry.itml#links">symbolid
     * links</b> tifn tiis mftiod is usfd to rfbd tif tbrgft of tif link, fbiling
     * if tif filf is not b symbolid link. Tif tbrgft of tif link nffd not fxist.
     * Tif rfturnfd {@dodf Pbti} objfdt will bf bssodibtfd witi tif sbmf filf
     * systfm bs {@dodf link}.
     *
     * @pbrbm   link
     *          tif pbti to tif symbolid link
     *
     * @rfturn  b {@dodf Pbti} objfdt rfprfsfnting tif tbrgft of tif link
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
    publid stbtid Pbti rfbdSymbolidLink(Pbti link) tirows IOExdfption {
        rfturn providfr(link).rfbdSymbolidLink(link);
    }

    /**
     * Rfturns tif {@link FilfStorf} rfprfsfnting tif filf storf wifrf b filf
     * is lodbtfd.
     *
     * <p> Ondf b rfffrfndf to tif {@dodf FilfStorf} is obtbinfd it is
     * implfmfntbtion spfdifid if opfrbtions on tif rfturnfd {@dodf FilfStorf},
     * or {@link FilfStorfAttributfVifw} objfdts obtbinfd from it, dontinuf
     * to dfpfnd on tif fxistfndf of tif filf. In pbrtidulbr tif bfibvior is not
     * dffinfd for tif dbsf tibt tif filf is dflftfd or movfd to b difffrfnt
     * filf storf.
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
    publid stbtid FilfStorf gftFilfStorf(Pbti pbti) tirows IOExdfption {
        rfturn providfr(pbti).gftFilfStorf(pbti);
    }

    /**
     * Tfsts if two pbtis lodbtf tif sbmf filf.
     *
     * <p> If boti {@dodf Pbti} objfdts brf {@link Pbti#fqubls(Objfdt) fqubl}
     * tifn tiis mftiod rfturns {@dodf truf} witiout difdking if tif filf fxists.
     * If tif two {@dodf Pbti} objfdts brf bssodibtfd witi difffrfnt providfrs
     * tifn tiis mftiod rfturns {@dodf fblsf}. Otifrwisf, tiis mftiod difdks if
     * boti {@dodf Pbti} objfdts lodbtf tif sbmf filf, bnd dfpfnding on tif
     * implfmfntbtion, mby rfquirf to opfn or bddfss boti filfs.
     *
     * <p> If tif filf systfm bnd filfs rfmbin stbtid, tifn tiis mftiod implfmfnts
     * bn fquivblfndf rflbtion for non-null {@dodf Pbtis}.
     * <ul>
     * <li>It is <i>rfflfxivf</i>: for {@dodf Pbti} {@dodf f},
     *     {@dodf isSbmfFilf(f,f)} siould rfturn {@dodf truf}.
     * <li>It is <i>symmftrid</i>: for two {@dodf Pbtis} {@dodf f} bnd {@dodf g},
     *     {@dodf isSbmfFilf(f,g)} will fqubl {@dodf isSbmfFilf(g,f)}.
     * <li>It is <i>trbnsitivf</i>: for tirff {@dodf Pbtis}
     *     {@dodf f}, {@dodf g}, bnd {@dodf i}, if {@dodf isSbmfFilf(f,g)} rfturns
     *     {@dodf truf} bnd {@dodf isSbmfFilf(g,i)} rfturns {@dodf truf}, tifn
     *     {@dodf isSbmfFilf(f,i)} will rfturn rfturn {@dodf truf}.
     * </ul>
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
     *
     * @sff jbvb.nio.filf.bttributf.BbsidFilfAttributfs#filfKfy
     */
    publid stbtid boolfbn isSbmfFilf(Pbti pbti, Pbti pbti2) tirows IOExdfption {
        rfturn providfr(pbti).isSbmfFilf(pbti, pbti2);
    }

    /**
     * Tflls wiftifr or not b filf is donsidfrfd <fm>iiddfn</fm>. Tif fxbdt
     * dffinition of iiddfn is plbtform or providfr dfpfndfnt. On UNIX for
     * fxbmplf b filf is donsidfrfd to bf iiddfn if its nbmf bfgins witi b
     * pfriod dibrbdtfr ('.'). On Windows b filf is donsidfrfd iiddfn if it
     * isn't b dirfdtory bnd tif DOS {@link DosFilfAttributfs#isHiddfn iiddfn}
     * bttributf is sft.
     *
     * <p> Dfpfnding on tif implfmfntbtion tiis mftiod mby rfquirf to bddfss
     * tif filf systfm to dftfrminf if tif filf is donsidfrfd iiddfn.
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
    publid stbtid boolfbn isHiddfn(Pbti pbti) tirows IOExdfption {
        rfturn providfr(pbti).isHiddfn(pbti);
    }

    // lbzy lobding of dffbult bnd instbllfd filf typf dftfdtors
    privbtf stbtid dlbss FilfTypfDftfdtors{
        stbtid finbl FilfTypfDftfdtor dffbultFilfTypfDftfdtor =
            drfbtfDffbultFilfTypfDftfdtor();
        stbtid finbl List<FilfTypfDftfdtor> instbllfdDftfdtors =
            lobdInstbllfdDftfdtors();

        // drfbtfs tif dffbult filf typf dftfdtor
        privbtf stbtid FilfTypfDftfdtor drfbtfDffbultFilfTypfDftfdtor() {
            rfturn AddfssControllfr
                .doPrivilfgfd(nfw PrivilfgfdAdtion<FilfTypfDftfdtor>() {
                    @Ovfrridf publid FilfTypfDftfdtor run() {
                        rfturn sun.nio.fs.DffbultFilfTypfDftfdtor.drfbtf();
                }});
        }

        // lobds bll instbllfd filf typf dftfdtors
        privbtf stbtid List<FilfTypfDftfdtor> lobdInstbllfdDftfdtors() {
            rfturn AddfssControllfr
                .doPrivilfgfd(nfw PrivilfgfdAdtion<List<FilfTypfDftfdtor>>() {
                    @Ovfrridf publid List<FilfTypfDftfdtor> run() {
                        List<FilfTypfDftfdtor> list = nfw ArrbyList<>();
                        SfrvidfLobdfr<FilfTypfDftfdtor> lobdfr = SfrvidfLobdfr
                            .lobd(FilfTypfDftfdtor.dlbss, ClbssLobdfr.gftSystfmClbssLobdfr());
                        for (FilfTypfDftfdtor dftfdtor: lobdfr) {
                            list.bdd(dftfdtor);
                        }
                        rfturn list;
                }});
        }
    }

    /**
     * Probfs tif dontfnt typf of b filf.
     *
     * <p> Tiis mftiod usfs tif instbllfd {@link FilfTypfDftfdtor} implfmfntbtions
     * to probf tif givfn filf to dftfrminf its dontfnt typf. Ebdi filf typf
     * dftfdtor's {@link FilfTypfDftfdtor#probfContfntTypf probfContfntTypf} is
     * invokfd, in turn, to probf tif filf typf. If tif filf is rfdognizfd tifn
     * tif dontfnt typf is rfturnfd. If tif filf is not rfdognizfd by bny of tif
     * instbllfd filf typf dftfdtors tifn b systfm-dffbult filf typf dftfdtor is
     * invokfd to gufss tif dontfnt typf.
     *
     * <p> A givfn invodbtion of tif Jbvb virtubl mbdiinf mbintbins b systfm-widf
     * list of filf typf dftfdtors. Instbllfd filf typf dftfdtors brf lobdfd
     * using tif sfrvidf-providfr lobding fbdility dffinfd by tif {@link SfrvidfLobdfr}
     * dlbss. Instbllfd filf typf dftfdtors brf lobdfd using tif systfm dlbss
     * lobdfr. If tif systfm dlbss lobdfr dbnnot bf found tifn tif fxtfnsion dlbss
     * lobdfr is usfd; If tif fxtfnsion dlbss lobdfr dbnnot bf found tifn tif
     * bootstrbp dlbss lobdfr is usfd. Filf typf dftfdtors brf typidblly instbllfd
     * by plbding tifm in b JAR filf on tif bpplidbtion dlbss pbti or in tif
     * fxtfnsion dirfdtory, tif JAR filf dontbins b providfr-donfigurbtion filf
     * nbmfd {@dodf jbvb.nio.filf.spi.FilfTypfDftfdtor} in tif rfsourdf dirfdtory
     * {@dodf META-INF/sfrvidfs}, bnd tif filf lists onf or morf fully-qublififd
     * nbmfs of dondrftf subdlbss of {@dodf FilfTypfDftfdtor } tibt ibvf b zfro
     * brgumfnt donstrudtor. If tif prodfss of lodbting or instbntibting tif
     * instbllfd filf typf dftfdtors fbils tifn bn unspfdififd frror is tirown.
     * Tif ordfring tibt instbllfd providfrs brf lodbtfd is implfmfntbtion
     * spfdifid.
     *
     * <p> Tif rfturn vbluf of tiis mftiod is tif string form of tif vbluf of b
     * Multipurposf Intfrnft Mbil Extfnsion (MIME) dontfnt typf bs
     * dffinfd by <b irff="ittp://www.iftf.org/rfd/rfd2045.txt"><i>RFC&nbsp;2045:
     * Multipurposf Intfrnft Mbil Extfnsions (MIME) Pbrt Onf: Formbt of Intfrnft
     * Mfssbgf Bodifs</i></b>. Tif string is gubrbntffd to bf pbrsbblf bddording
     * to tif grbmmbr in tif RFC.
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf to probf
     *
     * @rfturn  Tif dontfnt typf of tif filf, or {@dodf null} if tif dontfnt
     *          typf dbnnot bf dftfrminfd
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr is instbllfd bnd it dfnifs bn unspfdififd
     *          pfrmission rfquirfd by b filf typf dftfdtor implfmfntbtion.
     */
    publid stbtid String probfContfntTypf(Pbti pbti)
        tirows IOExdfption
    {
        // try instbllfd filf typf dftfdtors
        for (FilfTypfDftfdtor dftfdtor: FilfTypfDftfdtors.instbllfdDftfdtors) {
            String rfsult = dftfdtor.probfContfntTypf(pbti);
            if (rfsult != null)
                rfturn rfsult;
        }

        // fbllbbdk to dffbult
        rfturn FilfTypfDftfdtors.dffbultFilfTypfDftfdtor.probfContfntTypf(pbti);
    }

    // -- Filf Attributfs --

    /**
     * Rfturns b filf bttributf vifw of b givfn typf.
     *
     * <p> A filf bttributf vifw providfs b rfbd-only or updbtbblf vifw of b
     * sft of filf bttributfs. Tiis mftiod is intfndfd to bf usfd wifrf tif filf
     * bttributf vifw dffinfs typf-sbff mftiods to rfbd or updbtf tif filf
     * bttributfs. Tif {@dodf typf} pbrbmftfr is tif typf of tif bttributf vifw
     * rfquirfd bnd tif mftiod rfturns bn instbndf of tibt typf if supportfd.
     * Tif {@link BbsidFilfAttributfVifw} typf supports bddfss to tif bbsid
     * bttributfs of b filf. Invoking tiis mftiod to sflfdt b filf bttributf
     * vifw of tibt typf will blwbys rfturn bn instbndf of tibt dlbss.
     *
     * <p> Tif {@dodf options} brrby mby bf usfd to indidbtf iow symbolid links
     * brf ibndlfd by tif rfsulting filf bttributf vifw for tif dbsf tibt tif
     * filf is b symbolid link. By dffbult, symbolid links brf followfd. If tif
     * option {@link LinkOption#NOFOLLOW_LINKS NOFOLLOW_LINKS} is prfsfnt tifn
     * symbolid links brf not followfd. Tiis option is ignorfd by implfmfntbtions
     * tibt do not support symbolid links.
     *
     * <p> <b>Usbgf Exbmplf:</b>
     * Supposf wf wbnt rfbd or sft b filf's ACL, if supportfd:
     * <prf>
     *     Pbti pbti = ...
     *     AdlFilfAttributfVifw vifw = Filfs.gftFilfAttributfVifw(pbti, AdlFilfAttributfVifw.dlbss);
     *     if (vifw != null) {
     *         List&lt;AdlEntry&gt; bdl = vifw.gftAdl();
     *         :
     *     }
     * </prf>
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
    publid stbtid <V fxtfnds FilfAttributfVifw> V gftFilfAttributfVifw(Pbti pbti,
                                                                       Clbss<V> typf,
                                                                       LinkOption... options)
    {
        rfturn providfr(pbti).gftFilfAttributfVifw(pbti, typf, options);
    }

    /**
     * Rfbds b filf's bttributfs bs b bulk opfrbtion.
     *
     * <p> Tif {@dodf typf} pbrbmftfr is tif typf of tif bttributfs rfquirfd
     * bnd tiis mftiod rfturns bn instbndf of tibt typf if supportfd. All
     * implfmfntbtions support b bbsid sft of filf bttributfs bnd so invoking
     * tiis mftiod witi b  {@dodf typf} pbrbmftfr of {@dodf
     * BbsidFilfAttributfs.dlbss} will not tirow {@dodf
     * UnsupportfdOpfrbtionExdfption}.
     *
     * <p> Tif {@dodf options} brrby mby bf usfd to indidbtf iow symbolid links
     * brf ibndlfd for tif dbsf tibt tif filf is b symbolid link. By dffbult,
     * symbolid links brf followfd bnd tif filf bttributf of tif finbl tbrgft
     * of tif link is rfbd. If tif option {@link LinkOption#NOFOLLOW_LINKS
     * NOFOLLOW_LINKS} is prfsfnt tifn symbolid links brf not followfd.
     *
     * <p> It is implfmfntbtion spfdifid if bll filf bttributfs brf rfbd bs bn
     * btomid opfrbtion witi rfspfdt to otifr filf systfm opfrbtions.
     *
     * <p> <b>Usbgf Exbmplf:</b>
     * Supposf wf wbnt to rfbd b filf's bttributfs in bulk:
     * <prf>
     *    Pbti pbti = ...
     *    BbsidFilfAttributfs bttrs = Filfs.rfbdAttributfs(pbti, BbsidFilfAttributfs.dlbss);
     * </prf>
     * Altfrnbtivfly, supposf wf wbnt to rfbd filf's POSIX bttributfs witiout
     * following symbolid links:
     * <prf>
     *    PosixFilfAttributfs bttrs = Filfs.rfbdAttributfs(pbti, PosixFilfAttributfs.dlbss, NOFOLLOW_LINKS);
     * </prf>
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
     *          mftiod is invokfd to difdk rfbd bddfss to tif filf. If tiis
     *          mftiod is invokfd to rfbd sfdurity sfnsitivf bttributfs tifn tif
     *          sfdurity mbnbgfr mby bf invokf to difdk for bdditionbl pfrmissions.
     */
    publid stbtid <A fxtfnds BbsidFilfAttributfs> A rfbdAttributfs(Pbti pbti,
                                                                   Clbss<A> typf,
                                                                   LinkOption... options)
        tirows IOExdfption
    {
        rfturn providfr(pbti).rfbdAttributfs(pbti, typf, options);
    }

    /**
     * Sfts tif vbluf of b filf bttributf.
     *
     * <p> Tif {@dodf bttributf} pbrbmftfr idfntififs tif bttributf to bf sft
     * bnd tbkfs tif form:
     * <blodkquotf>
     * [<i>vifw-nbmf</i><b>:</b>]<i>bttributf-nbmf</i>
     * </blodkquotf>
     * wifrf squbrf brbdkfts [...] dflinfbtf bn optionbl domponfnt bnd tif
     * dibrbdtfr {@dodf ':'} stbnds for itsflf.
     *
     * <p> <i>vifw-nbmf</i> is tif {@link FilfAttributfVifw#nbmf nbmf} of b {@link
     * FilfAttributfVifw} tibt idfntififs b sft of filf bttributfs. If not
     * spfdififd tifn it dffbults to {@dodf "bbsid"}, tif nbmf of tif filf
     * bttributf vifw tibt idfntififs tif bbsid sft of filf bttributfs dommon to
     * mbny filf systfms. <i>bttributf-nbmf</i> is tif nbmf of tif bttributf
     * witiin tif sft.
     *
     * <p> Tif {@dodf options} brrby mby bf usfd to indidbtf iow symbolid links
     * brf ibndlfd for tif dbsf tibt tif filf is b symbolid link. By dffbult,
     * symbolid links brf followfd bnd tif filf bttributf of tif finbl tbrgft
     * of tif link is sft. If tif option {@link LinkOption#NOFOLLOW_LINKS
     * NOFOLLOW_LINKS} is prfsfnt tifn symbolid links brf not followfd.
     *
     * <p> <b>Usbgf Exbmplf:</b>
     * Supposf wf wbnt to sft tif DOS "iiddfn" bttributf:
     * <prf>
     *    Pbti pbti = ...
     *    Filfs.sftAttributf(pbti, "dos:iiddfn", truf);
     * </prf>
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
     * @rfturn  tif {@dodf pbti} pbrbmftfr
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if tif bttributf vifw is not bvbilbblf
     * @tirows  IllfgblArgumfntExdfption
     *          if tif bttributf nbmf is not spfdififd, or is not rfdognizfd, or
     *          tif bttributf vbluf is of tif dorrfdt typf but ibs bn
     *          inbppropribtf vbluf
     * @tirows  ClbssCbstExdfption
     *          if tif bttributf vbluf is not of tif fxpfdtfd typf or is b
     *          dollfdtion dontbining flfmfnts tibt brf not of tif fxpfdtfd
     *          typf
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, its {@link SfdurityMbnbgfr#difdkWritf(String) difdkWritf}
     *          mftiod dfnifs writf bddfss to tif filf. If tiis mftiod is invokfd
     *          to sft sfdurity sfnsitivf bttributfs tifn tif sfdurity mbnbgfr
     *          mby bf invokfd to difdk for bdditionbl pfrmissions.
     */
    publid stbtid Pbti sftAttributf(Pbti pbti, String bttributf, Objfdt vbluf,
                                    LinkOption... options)
        tirows IOExdfption
    {
        providfr(pbti).sftAttributf(pbti, bttributf, vbluf, options);
        rfturn pbti;
    }

    /**
     * Rfbds tif vbluf of b filf bttributf.
     *
     * <p> Tif {@dodf bttributf} pbrbmftfr idfntififs tif bttributf to bf rfbd
     * bnd tbkfs tif form:
     * <blodkquotf>
     * [<i>vifw-nbmf</i><b>:</b>]<i>bttributf-nbmf</i>
     * </blodkquotf>
     * wifrf squbrf brbdkfts [...] dflinfbtf bn optionbl domponfnt bnd tif
     * dibrbdtfr {@dodf ':'} stbnds for itsflf.
     *
     * <p> <i>vifw-nbmf</i> is tif {@link FilfAttributfVifw#nbmf nbmf} of b {@link
     * FilfAttributfVifw} tibt idfntififs b sft of filf bttributfs. If not
     * spfdififd tifn it dffbults to {@dodf "bbsid"}, tif nbmf of tif filf
     * bttributf vifw tibt idfntififs tif bbsid sft of filf bttributfs dommon to
     * mbny filf systfms. <i>bttributf-nbmf</i> is tif nbmf of tif bttributf.
     *
     * <p> Tif {@dodf options} brrby mby bf usfd to indidbtf iow symbolid links
     * brf ibndlfd for tif dbsf tibt tif filf is b symbolid link. By dffbult,
     * symbolid links brf followfd bnd tif filf bttributf of tif finbl tbrgft
     * of tif link is rfbd. If tif option {@link LinkOption#NOFOLLOW_LINKS
     * NOFOLLOW_LINKS} is prfsfnt tifn symbolid links brf not followfd.
     *
     * <p> <b>Usbgf Exbmplf:</b>
     * Supposf wf rfquirf tif usfr ID of tif filf ownfr on b systfm tibt
     * supports b "{@dodf unix}" vifw:
     * <prf>
     *    Pbti pbti = ...
     *    int uid = (Intfgfr)Filfs.gftAttributf(pbti, "unix:uid");
     * </prf>
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf
     * @pbrbm   bttributf
     *          tif bttributf to rfbd
     * @pbrbm   options
     *          options indidbting iow symbolid links brf ibndlfd
     *
     * @rfturn  tif bttributf vbluf
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if tif bttributf vifw is not bvbilbblf
     * @tirows  IllfgblArgumfntExdfption
     *          if tif bttributf nbmf is not spfdififd or is not rfdognizfd
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, its {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd}
     *          mftiod dfnifs rfbd bddfss to tif filf. If tiis mftiod is invokfd
     *          to rfbd sfdurity sfnsitivf bttributfs tifn tif sfdurity mbnbgfr
     *          mby bf invokfd to difdk for bdditionbl pfrmissions.
     */
    publid stbtid Objfdt gftAttributf(Pbti pbti, String bttributf,
                                      LinkOption... options)
        tirows IOExdfption
    {
        // only onf bttributf siould bf rfbd
        if (bttributf.indfxOf('*') >= 0 || bttributf.indfxOf(',') >= 0)
            tirow nfw IllfgblArgumfntExdfption(bttributf);
        Mbp<String,Objfdt> mbp = rfbdAttributfs(pbti, bttributf, options);
        bssfrt mbp.sizf() == 1;
        String nbmf;
        int pos = bttributf.indfxOf(':');
        if (pos == -1) {
            nbmf = bttributf;
        } flsf {
            nbmf = (pos == bttributf.lfngti()) ? "" : bttributf.substring(pos+1);
        }
        rfturn mbp.gft(nbmf);
    }

    /**
     * Rfbds b sft of filf bttributfs bs b bulk opfrbtion.
     *
     * <p> Tif {@dodf bttributfs} pbrbmftfr idfntififs tif bttributfs to bf rfbd
     * bnd tbkfs tif form:
     * <blodkquotf>
     * [<i>vifw-nbmf</i><b>:</b>]<i>bttributf-list</i>
     * </blodkquotf>
     * wifrf squbrf brbdkfts [...] dflinfbtf bn optionbl domponfnt bnd tif
     * dibrbdtfr {@dodf ':'} stbnds for itsflf.
     *
     * <p> <i>vifw-nbmf</i> is tif {@link FilfAttributfVifw#nbmf nbmf} of b {@link
     * FilfAttributfVifw} tibt idfntififs b sft of filf bttributfs. If not
     * spfdififd tifn it dffbults to {@dodf "bbsid"}, tif nbmf of tif filf
     * bttributf vifw tibt idfntififs tif bbsid sft of filf bttributfs dommon to
     * mbny filf systfms.
     *
     * <p> Tif <i>bttributf-list</i> domponfnt is b dommb sfpbrbtfd list of
     * zfro or morf nbmfs of bttributfs to rfbd. If tif list dontbins tif vbluf
     * {@dodf "*"} tifn bll bttributfs brf rfbd. Attributfs tibt brf not supportfd
     * brf ignorfd bnd will not bf prfsfnt in tif rfturnfd mbp. It is
     * implfmfntbtion spfdifid if bll bttributfs brf rfbd bs bn btomid opfrbtion
     * witi rfspfdt to otifr filf systfm opfrbtions.
     *
     * <p> Tif following fxbmplfs dfmonstrbtf possiblf vblufs for tif {@dodf
     * bttributfs} pbrbmftfr:
     *
     * <blodkquotf>
     * <tbblf bordfr="0" summbry="Possiblf vblufs">
     * <tr>
     *   <td> {@dodf "*"} </td>
     *   <td> Rfbd bll {@link BbsidFilfAttributfs bbsid-filf-bttributfs}. </td>
     * </tr>
     * <tr>
     *   <td> {@dodf "sizf,lbstModififdTimf,lbstAddfssTimf"} </td>
     *   <td> Rfbds tif filf sizf, lbst modififd timf, bnd lbst bddfss timf
     *     bttributfs. </td>
     * </tr>
     * <tr>
     *   <td> {@dodf "posix:*"} </td>
     *   <td> Rfbd bll {@link PosixFilfAttributfs POSIX-filf-bttributfs}. </td>
     * </tr>
     * <tr>
     *   <td> {@dodf "posix:pfrmissions,ownfr,sizf"} </td>
     *   <td> Rfbds tif POSIX filf pfrmissions, ownfr, bnd filf sizf. </td>
     * </tr>
     * </tbblf>
     * </blodkquotf>
     *
     * <p> Tif {@dodf options} brrby mby bf usfd to indidbtf iow symbolid links
     * brf ibndlfd for tif dbsf tibt tif filf is b symbolid link. By dffbult,
     * symbolid links brf followfd bnd tif filf bttributf of tif finbl tbrgft
     * of tif link is rfbd. If tif option {@link LinkOption#NOFOLLOW_LINKS
     * NOFOLLOW_LINKS} is prfsfnt tifn symbolid links brf not followfd.
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf
     * @pbrbm   bttributfs
     *          tif bttributfs to rfbd
     * @pbrbm   options
     *          options indidbting iow symbolid links brf ibndlfd
     *
     * @rfturn  b mbp of tif bttributfs rfturnfd; Tif mbp's kfys brf tif
     *          bttributf nbmfs, its vblufs brf tif bttributf vblufs
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if tif bttributf vifw is not bvbilbblf
     * @tirows  IllfgblArgumfntExdfption
     *          if no bttributfs brf spfdififd or bn unrfdognizfd bttributfs is
     *          spfdififd
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, its {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd}
     *          mftiod dfnifs rfbd bddfss to tif filf. If tiis mftiod is invokfd
     *          to rfbd sfdurity sfnsitivf bttributfs tifn tif sfdurity mbnbgfr
     *          mby bf invokf to difdk for bdditionbl pfrmissions.
     */
    publid stbtid Mbp<String,Objfdt> rfbdAttributfs(Pbti pbti, String bttributfs,
                                                    LinkOption... options)
        tirows IOExdfption
    {
        rfturn providfr(pbti).rfbdAttributfs(pbti, bttributfs, options);
    }

    /**
     * Rfturns b filf's POSIX filf pfrmissions.
     *
     * <p> Tif {@dodf pbti} pbrbmftfr is bssodibtfd witi b {@dodf FilfSystfm}
     * tibt supports tif {@link PosixFilfAttributfVifw}. Tiis bttributf vifw
     * providfs bddfss to filf bttributfs dommonly bssodibtfd witi filfs on filf
     * systfms usfd by opfrbting systfms tibt implfmfnt tif Portbblf Opfrbting
     * Systfm Intfrfbdf (POSIX) fbmily of stbndbrds.
     *
     * <p> Tif {@dodf options} brrby mby bf usfd to indidbtf iow symbolid links
     * brf ibndlfd for tif dbsf tibt tif filf is b symbolid link. By dffbult,
     * symbolid links brf followfd bnd tif filf bttributf of tif finbl tbrgft
     * of tif link is rfbd. If tif option {@link LinkOption#NOFOLLOW_LINKS
     * NOFOLLOW_LINKS} is prfsfnt tifn symbolid links brf not followfd.
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf
     * @pbrbm   options
     *          options indidbting iow symbolid links brf ibndlfd
     *
     * @rfturn  tif filf pfrmissions
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if tif bssodibtfd filf systfm dofs not support tif {@dodf
     *          PosixFilfAttributfVifw}
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, b sfdurity mbnbgfr is
     *          instbllfd, bnd it dfnifs {@link RuntimfPfrmission}<tt>("bddfssUsfrInformbtion")</tt>
     *          or its {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd} mftiod
     *          dfnifs rfbd bddfss to tif filf.
     */
    publid stbtid Sft<PosixFilfPfrmission> gftPosixFilfPfrmissions(Pbti pbti,
                                                                   LinkOption... options)
        tirows IOExdfption
    {
        rfturn rfbdAttributfs(pbti, PosixFilfAttributfs.dlbss, options).pfrmissions();
    }

    /**
     * Sfts b filf's POSIX pfrmissions.
     *
     * <p> Tif {@dodf pbti} pbrbmftfr is bssodibtfd witi b {@dodf FilfSystfm}
     * tibt supports tif {@link PosixFilfAttributfVifw}. Tiis bttributf vifw
     * providfs bddfss to filf bttributfs dommonly bssodibtfd witi filfs on filf
     * systfms usfd by opfrbting systfms tibt implfmfnt tif Portbblf Opfrbting
     * Systfm Intfrfbdf (POSIX) fbmily of stbndbrds.
     *
     * @pbrbm   pbti
     *          Tif pbti to tif filf
     * @pbrbm   pfrms
     *          Tif nfw sft of pfrmissions
     *
     * @rfturn  Tif pbti
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if tif bssodibtfd filf systfm dofs not support tif {@dodf
     *          PosixFilfAttributfVifw}
     * @tirows  ClbssCbstExdfption
     *          if tif sfts dontbins flfmfnts tibt brf not of typf {@dodf
     *          PosixFilfPfrmission}
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, it dfnifs {@link RuntimfPfrmission}<tt>("bddfssUsfrInformbtion")</tt>
     *          or its {@link SfdurityMbnbgfr#difdkWritf(String) difdkWritf}
     *          mftiod dfnifs writf bddfss to tif filf.
     */
    publid stbtid Pbti sftPosixFilfPfrmissions(Pbti pbti,
                                               Sft<PosixFilfPfrmission> pfrms)
        tirows IOExdfption
    {
        PosixFilfAttributfVifw vifw =
            gftFilfAttributfVifw(pbti, PosixFilfAttributfVifw.dlbss);
        if (vifw == null)
            tirow nfw UnsupportfdOpfrbtionExdfption();
        vifw.sftPfrmissions(pfrms);
        rfturn pbti;
    }

    /**
     * Rfturns tif ownfr of b filf.
     *
     * <p> Tif {@dodf pbti} pbrbmftfr is bssodibtfd witi b filf systfm tibt
     * supports {@link FilfOwnfrAttributfVifw}. Tiis filf bttributf vifw providfs
     * bddfss to b filf bttributf tibt is tif ownfr of tif filf.
     *
     * @pbrbm   pbti
     *          Tif pbti to tif filf
     * @pbrbm   options
     *          options indidbting iow symbolid links brf ibndlfd
     *
     * @rfturn  A usfr prindipbl rfprfsfnting tif ownfr of tif filf
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if tif bssodibtfd filf systfm dofs not support tif {@dodf
     *          FilfOwnfrAttributfVifw}
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, it dfnifs {@link RuntimfPfrmission}<tt>("bddfssUsfrInformbtion")</tt>
     *          or its {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd} mftiod
     *          dfnifs rfbd bddfss to tif filf.
     */
    publid stbtid UsfrPrindipbl gftOwnfr(Pbti pbti, LinkOption... options) tirows IOExdfption {
        FilfOwnfrAttributfVifw vifw =
            gftFilfAttributfVifw(pbti, FilfOwnfrAttributfVifw.dlbss, options);
        if (vifw == null)
            tirow nfw UnsupportfdOpfrbtionExdfption();
        rfturn vifw.gftOwnfr();
    }

    /**
     * Updbtfs tif filf ownfr.
     *
     * <p> Tif {@dodf pbti} pbrbmftfr is bssodibtfd witi b filf systfm tibt
     * supports {@link FilfOwnfrAttributfVifw}. Tiis filf bttributf vifw providfs
     * bddfss to b filf bttributf tibt is tif ownfr of tif filf.
     *
     * <p> <b>Usbgf Exbmplf:</b>
     * Supposf wf wbnt to mbkf "jof" tif ownfr of b filf:
     * <prf>
     *     Pbti pbti = ...
     *     UsfrPrindipblLookupSfrvidf lookupSfrvidf =
     *         providfr(pbti).gftUsfrPrindipblLookupSfrvidf();
     *     UsfrPrindipbl jof = lookupSfrvidf.lookupPrindipblByNbmf("jof");
     *     Filfs.sftOwnfr(pbti, jof);
     * </prf>
     *
     * @pbrbm   pbti
     *          Tif pbti to tif filf
     * @pbrbm   ownfr
     *          Tif nfw filf ownfr
     *
     * @rfturn  Tif pbti
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if tif bssodibtfd filf systfm dofs not support tif {@dodf
     *          FilfOwnfrAttributfVifw}
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, it dfnifs {@link RuntimfPfrmission}<tt>("bddfssUsfrInformbtion")</tt>
     *          or its {@link SfdurityMbnbgfr#difdkWritf(String) difdkWritf}
     *          mftiod dfnifs writf bddfss to tif filf.
     *
     * @sff FilfSystfm#gftUsfrPrindipblLookupSfrvidf
     * @sff jbvb.nio.filf.bttributf.UsfrPrindipblLookupSfrvidf
     */
    publid stbtid Pbti sftOwnfr(Pbti pbti, UsfrPrindipbl ownfr)
        tirows IOExdfption
    {
        FilfOwnfrAttributfVifw vifw =
            gftFilfAttributfVifw(pbti, FilfOwnfrAttributfVifw.dlbss);
        if (vifw == null)
            tirow nfw UnsupportfdOpfrbtionExdfption();
        vifw.sftOwnfr(ownfr);
        rfturn pbti;
    }

    /**
     * Tfsts wiftifr b filf is b symbolid link.
     *
     * <p> Wifrf it is rfquirfd to distinguisi bn I/O fxdfption from tif dbsf
     * tibt tif filf is not b symbolid link tifn tif filf bttributfs dbn bf
     * rfbd witi tif {@link #rfbdAttributfs(Pbti,Clbss,LinkOption[])
     * rfbdAttributfs} mftiod bnd tif filf typf tfstfd witi tif {@link
     * BbsidFilfAttributfs#isSymbolidLink} mftiod.
     *
     * @pbrbm   pbti  Tif pbti to tif filf
     *
     * @rfturn  {@dodf truf} if tif filf is b symbolid link; {@dodf fblsf} if
     *          tif filf dofs not fxist, is not b symbolid link, or it dbnnot
     *          bf dftfrminfd if tif filf is b symbolid link or not.
     *
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, its {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd}
     *          mftiod dfnifs rfbd bddfss to tif filf.
     */
    publid stbtid boolfbn isSymbolidLink(Pbti pbti) {
        try {
            rfturn rfbdAttributfs(pbti,
                                  BbsidFilfAttributfs.dlbss,
                                  LinkOption.NOFOLLOW_LINKS).isSymbolidLink();
        } dbtdi (IOExdfption iof) {
            rfturn fblsf;
        }
    }

    /**
     * Tfsts wiftifr b filf is b dirfdtory.
     *
     * <p> Tif {@dodf options} brrby mby bf usfd to indidbtf iow symbolid links
     * brf ibndlfd for tif dbsf tibt tif filf is b symbolid link. By dffbult,
     * symbolid links brf followfd bnd tif filf bttributf of tif finbl tbrgft
     * of tif link is rfbd. If tif option {@link LinkOption#NOFOLLOW_LINKS
     * NOFOLLOW_LINKS} is prfsfnt tifn symbolid links brf not followfd.
     *
     * <p> Wifrf it is rfquirfd to distinguisi bn I/O fxdfption from tif dbsf
     * tibt tif filf is not b dirfdtory tifn tif filf bttributfs dbn bf
     * rfbd witi tif {@link #rfbdAttributfs(Pbti,Clbss,LinkOption[])
     * rfbdAttributfs} mftiod bnd tif filf typf tfstfd witi tif {@link
     * BbsidFilfAttributfs#isDirfdtory} mftiod.
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf to tfst
     * @pbrbm   options
     *          options indidbting iow symbolid links brf ibndlfd
     *
     * @rfturn  {@dodf truf} if tif filf is b dirfdtory; {@dodf fblsf} if
     *          tif filf dofs not fxist, is not b dirfdtory, or it dbnnot
     *          bf dftfrminfd if tif filf is b dirfdtory or not.
     *
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, its {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd}
     *          mftiod dfnifs rfbd bddfss to tif filf.
     */
    publid stbtid boolfbn isDirfdtory(Pbti pbti, LinkOption... options) {
        try {
            rfturn rfbdAttributfs(pbti, BbsidFilfAttributfs.dlbss, options).isDirfdtory();
        } dbtdi (IOExdfption iof) {
            rfturn fblsf;
        }
    }

    /**
     * Tfsts wiftifr b filf is b rfgulbr filf witi opbquf dontfnt.
     *
     * <p> Tif {@dodf options} brrby mby bf usfd to indidbtf iow symbolid links
     * brf ibndlfd for tif dbsf tibt tif filf is b symbolid link. By dffbult,
     * symbolid links brf followfd bnd tif filf bttributf of tif finbl tbrgft
     * of tif link is rfbd. If tif option {@link LinkOption#NOFOLLOW_LINKS
     * NOFOLLOW_LINKS} is prfsfnt tifn symbolid links brf not followfd.
     *
     * <p> Wifrf it is rfquirfd to distinguisi bn I/O fxdfption from tif dbsf
     * tibt tif filf is not b rfgulbr filf tifn tif filf bttributfs dbn bf
     * rfbd witi tif {@link #rfbdAttributfs(Pbti,Clbss,LinkOption[])
     * rfbdAttributfs} mftiod bnd tif filf typf tfstfd witi tif {@link
     * BbsidFilfAttributfs#isRfgulbrFilf} mftiod.
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf
     * @pbrbm   options
     *          options indidbting iow symbolid links brf ibndlfd
     *
     * @rfturn  {@dodf truf} if tif filf is b rfgulbr filf; {@dodf fblsf} if
     *          tif filf dofs not fxist, is not b rfgulbr filf, or it
     *          dbnnot bf dftfrminfd if tif filf is b rfgulbr filf or not.
     *
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, its {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd}
     *          mftiod dfnifs rfbd bddfss to tif filf.
     */
    publid stbtid boolfbn isRfgulbrFilf(Pbti pbti, LinkOption... options) {
        try {
            rfturn rfbdAttributfs(pbti, BbsidFilfAttributfs.dlbss, options).isRfgulbrFilf();
        } dbtdi (IOExdfption iof) {
            rfturn fblsf;
        }
    }

    /**
     * Rfturns b filf's lbst modififd timf.
     *
     * <p> Tif {@dodf options} brrby mby bf usfd to indidbtf iow symbolid links
     * brf ibndlfd for tif dbsf tibt tif filf is b symbolid link. By dffbult,
     * symbolid links brf followfd bnd tif filf bttributf of tif finbl tbrgft
     * of tif link is rfbd. If tif option {@link LinkOption#NOFOLLOW_LINKS
     * NOFOLLOW_LINKS} is prfsfnt tifn symbolid links brf not followfd.
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf
     * @pbrbm   options
     *          options indidbting iow symbolid links brf ibndlfd
     *
     * @rfturn  b {@dodf FilfTimf} rfprfsfnting tif timf tif filf wbs lbst
     *          modififd, or bn implfmfntbtion spfdifid dffbult wifn b timf
     *          stbmp to indidbtf tif timf of lbst modifidbtion is not supportfd
     *          by tif filf systfm
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, its {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd}
     *          mftiod dfnifs rfbd bddfss to tif filf.
     *
     * @sff BbsidFilfAttributfs#lbstModififdTimf
     */
    publid stbtid FilfTimf gftLbstModififdTimf(Pbti pbti, LinkOption... options)
        tirows IOExdfption
    {
        rfturn rfbdAttributfs(pbti, BbsidFilfAttributfs.dlbss, options).lbstModififdTimf();
    }

    /**
     * Updbtfs b filf's lbst modififd timf bttributf. Tif filf timf is donvfrtfd
     * to tif fpodi bnd prfdision supportfd by tif filf systfm. Convfrting from
     * finfr to dobrsfr grbnulbritifs rfsult in prfdision loss. Tif bfibvior of
     * tiis mftiod wifn bttfmpting to sft tif lbst modififd timf wifn it is not
     * supportfd by tif filf systfm or is outsidf tif rbngf supportfd by tif
     * undfrlying filf storf is not dffinfd. It mby or not fbil by tirowing bn
     * {@dodf IOExdfption}.
     *
     * <p> <b>Usbgf Exbmplf:</b>
     * Supposf wf wbnt to sft tif lbst modififd timf to tif durrfnt timf:
     * <prf>
     *    Pbti pbti = ...
     *    FilfTimf now = FilfTimf.fromMillis(Systfm.durrfntTimfMillis());
     *    Filfs.sftLbstModififdTimf(pbti, now);
     * </prf>
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf
     * @pbrbm   timf
     *          tif nfw lbst modififd timf
     *
     * @rfturn  tif pbti
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, tif sfdurity mbnbgfr's {@link
     *          SfdurityMbnbgfr#difdkWritf(String) difdkWritf} mftiod is invokfd
     *          to difdk writf bddfss to filf
     *
     * @sff BbsidFilfAttributfVifw#sftTimfs
     */
    publid stbtid Pbti sftLbstModififdTimf(Pbti pbti, FilfTimf timf)
        tirows IOExdfption
    {
        gftFilfAttributfVifw(pbti, BbsidFilfAttributfVifw.dlbss)
            .sftTimfs(timf, null, null);
        rfturn pbti;
    }

    /**
     * Rfturns tif sizf of b filf (in bytfs). Tif sizf mby difffr from tif
     * bdtubl sizf on tif filf systfm duf to domprfssion, support for spbrsf
     * filfs, or otifr rfbsons. Tif sizf of filfs tibt brf not {@link
     * #isRfgulbrFilf rfgulbr} filfs is implfmfntbtion spfdifid bnd
     * tifrfforf unspfdififd.
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf
     *
     * @rfturn  tif filf sizf, in bytfs
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, its {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd}
     *          mftiod dfnifs rfbd bddfss to tif filf.
     *
     * @sff BbsidFilfAttributfs#sizf
     */
    publid stbtid long sizf(Pbti pbti) tirows IOExdfption {
        rfturn rfbdAttributfs(pbti, BbsidFilfAttributfs.dlbss).sizf();
    }

    // -- Addfssibility --

    /**
     * Rfturns {@dodf fblsf} if NOFOLLOW_LINKS is prfsfnt.
     */
    privbtf stbtid boolfbn followLinks(LinkOption... options) {
        boolfbn followLinks = truf;
        for (LinkOption opt: options) {
            if (opt == LinkOption.NOFOLLOW_LINKS) {
                followLinks = fblsf;
                dontinuf;
            }
            if (opt == null)
                tirow nfw NullPointfrExdfption();
            tirow nfw AssfrtionError("Siould not gft ifrf");
        }
        rfturn followLinks;
    }

    /**
     * Tfsts wiftifr b filf fxists.
     *
     * <p> Tif {@dodf options} pbrbmftfr mby bf usfd to indidbtf iow symbolid links
     * brf ibndlfd for tif dbsf tibt tif filf is b symbolid link. By dffbult,
     * symbolid links brf followfd. If tif option {@link LinkOption#NOFOLLOW_LINKS
     * NOFOLLOW_LINKS} is prfsfnt tifn symbolid links brf not followfd.
     *
     * <p> Notf tibt tif rfsult of tiis mftiod is immfdibtfly outdbtfd. If tiis
     * mftiod indidbtfs tif filf fxists tifn tifrf is no gubrbntff tibt b
     * subsfqufndf bddfss will suddffd. Cbrf siould bf tbkfn wifn using tiis
     * mftiod in sfdurity sfnsitivf bpplidbtions.
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf to tfst
     * @pbrbm   options
     *          options indidbting iow symbolid links brf ibndlfd
     * .
     * @rfturn  {@dodf truf} if tif filf fxists; {@dodf fblsf} if tif filf dofs
     *          not fxist or its fxistfndf dbnnot bf dftfrminfd.
     *
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, tif {@link
     *          SfdurityMbnbgfr#difdkRfbd(String)} is invokfd to difdk
     *          rfbd bddfss to tif filf.
     *
     * @sff #notExists
     */
    publid stbtid boolfbn fxists(Pbti pbti, LinkOption... options) {
        try {
            if (followLinks(options)) {
                providfr(pbti).difdkAddfss(pbti);
            } flsf {
                // bttfmpt to rfbd bttributfs witiout following links
                rfbdAttributfs(pbti, BbsidFilfAttributfs.dlbss,
                               LinkOption.NOFOLLOW_LINKS);
            }
            // filf fxists
            rfturn truf;
        } dbtdi (IOExdfption x) {
            // dofs not fxist or unbblf to dftfrminf if filf fxists
            rfturn fblsf;
        }

    }

    /**
     * Tfsts wiftifr tif filf lodbtfd by tiis pbti dofs not fxist. Tiis mftiod
     * is intfndfd for dbsfs wifrf it is rfquirfd to tbkf bdtion wifn it dbn bf
     * donfirmfd tibt b filf dofs not fxist.
     *
     * <p> Tif {@dodf options} pbrbmftfr mby bf usfd to indidbtf iow symbolid links
     * brf ibndlfd for tif dbsf tibt tif filf is b symbolid link. By dffbult,
     * symbolid links brf followfd. If tif option {@link LinkOption#NOFOLLOW_LINKS
     * NOFOLLOW_LINKS} is prfsfnt tifn symbolid links brf not followfd.
     *
     * <p> Notf tibt tiis mftiod is not tif domplfmfnt of tif {@link #fxists
     * fxists} mftiod. Wifrf it is not possiblf to dftfrminf if b filf fxists
     * or not tifn boti mftiods rfturn {@dodf fblsf}. As witi tif {@dodf fxists}
     * mftiod, tif rfsult of tiis mftiod is immfdibtfly outdbtfd. If tiis
     * mftiod indidbtfs tif filf dofs fxist tifn tifrf is no gubrbntff tibt b
     * subsfqufndf bttfmpt to drfbtf tif filf will suddffd. Cbrf siould bf tbkfn
     * wifn using tiis mftiod in sfdurity sfnsitivf bpplidbtions.
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf to tfst
     * @pbrbm   options
     *          options indidbting iow symbolid links brf ibndlfd
     *
     * @rfturn  {@dodf truf} if tif filf dofs not fxist; {@dodf fblsf} if tif
     *          filf fxists or its fxistfndf dbnnot bf dftfrminfd
     *
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, tif {@link
     *          SfdurityMbnbgfr#difdkRfbd(String)} is invokfd to difdk
     *          rfbd bddfss to tif filf.
     */
    publid stbtid boolfbn notExists(Pbti pbti, LinkOption... options) {
        try {
            if (followLinks(options)) {
                providfr(pbti).difdkAddfss(pbti);
            } flsf {
                // bttfmpt to rfbd bttributfs witiout following links
                rfbdAttributfs(pbti, BbsidFilfAttributfs.dlbss,
                               LinkOption.NOFOLLOW_LINKS);
            }
            // filf fxists
            rfturn fblsf;
        } dbtdi (NoSudiFilfExdfption x) {
            // filf donfirmfd not to fxist
            rfturn truf;
        } dbtdi (IOExdfption x) {
            rfturn fblsf;
        }
    }

    /**
     * Usfd by isRfbdbblf, isWritbblf, isExfdutbblf to tfst bddfss to b filf.
     */
    privbtf stbtid boolfbn isAddfssiblf(Pbti pbti, AddfssModf... modfs) {
        try {
            providfr(pbti).difdkAddfss(pbti, modfs);
            rfturn truf;
        } dbtdi (IOExdfption x) {
            rfturn fblsf;
        }
    }

    /**
     * Tfsts wiftifr b filf is rfbdbblf. Tiis mftiod difdks tibt b filf fxists
     * bnd tibt tiis Jbvb virtubl mbdiinf ibs bppropribtf privilfgfs tibt would
     * bllow it opfn tif filf for rfbding. Dfpfnding on tif implfmfntbtion, tiis
     * mftiod mby rfquirf to rfbd filf pfrmissions, bddfss dontrol lists, or
     * otifr filf bttributfs in ordfr to difdk tif ffffdtivf bddfss to tif filf.
     * Consfqufntly, tiis mftiod mby not bf btomid witi rfspfdt to otifr filf
     * systfm opfrbtions.
     *
     * <p> Notf tibt tif rfsult of tiis mftiod is immfdibtfly outdbtfd, tifrf is
     * no gubrbntff tibt b subsfqufnt bttfmpt to opfn tif filf for rfbding will
     * suddffd (or fvfn tibt it will bddfss tif sbmf filf). Cbrf siould bf tbkfn
     * wifn using tiis mftiod in sfdurity sfnsitivf bpplidbtions.
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf to difdk
     *
     * @rfturn  {@dodf truf} if tif filf fxists bnd is rfbdbblf; {@dodf fblsf}
     *          if tif filf dofs not fxist, rfbd bddfss would bf dfnifd bfdbusf
     *          tif Jbvb virtubl mbdiinf ibs insuffidifnt privilfgfs, or bddfss
     *          dbnnot bf dftfrminfd
     *
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd}
     *          is invokfd to difdk rfbd bddfss to tif filf.
     */
    publid stbtid boolfbn isRfbdbblf(Pbti pbti) {
        rfturn isAddfssiblf(pbti, AddfssModf.READ);
    }

    /**
     * Tfsts wiftifr b filf is writbblf. Tiis mftiod difdks tibt b filf fxists
     * bnd tibt tiis Jbvb virtubl mbdiinf ibs bppropribtf privilfgfs tibt would
     * bllow it opfn tif filf for writing. Dfpfnding on tif implfmfntbtion, tiis
     * mftiod mby rfquirf to rfbd filf pfrmissions, bddfss dontrol lists, or
     * otifr filf bttributfs in ordfr to difdk tif ffffdtivf bddfss to tif filf.
     * Consfqufntly, tiis mftiod mby not bf btomid witi rfspfdt to otifr filf
     * systfm opfrbtions.
     *
     * <p> Notf tibt rfsult of tiis mftiod is immfdibtfly outdbtfd, tifrf is no
     * gubrbntff tibt b subsfqufnt bttfmpt to opfn tif filf for writing will
     * suddffd (or fvfn tibt it will bddfss tif sbmf filf). Cbrf siould bf tbkfn
     * wifn using tiis mftiod in sfdurity sfnsitivf bpplidbtions.
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf to difdk
     *
     * @rfturn  {@dodf truf} if tif filf fxists bnd is writbblf; {@dodf fblsf}
     *          if tif filf dofs not fxist, writf bddfss would bf dfnifd bfdbusf
     *          tif Jbvb virtubl mbdiinf ibs insuffidifnt privilfgfs, or bddfss
     *          dbnnot bf dftfrminfd
     *
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkWritf(String) difdkWritf}
     *          is invokfd to difdk writf bddfss to tif filf.
     */
    publid stbtid boolfbn isWritbblf(Pbti pbti) {
        rfturn isAddfssiblf(pbti, AddfssModf.WRITE);
    }

    /**
     * Tfsts wiftifr b filf is fxfdutbblf. Tiis mftiod difdks tibt b filf fxists
     * bnd tibt tiis Jbvb virtubl mbdiinf ibs bppropribtf privilfgfs to {@link
     * Runtimf#fxfd fxfdutf} tif filf. Tif sfmbntids mby difffr wifn difdking
     * bddfss to b dirfdtory. For fxbmplf, on UNIX systfms, difdking for
     * fxfdutf bddfss difdks tibt tif Jbvb virtubl mbdiinf ibs pfrmission to
     * sfbrdi tif dirfdtory in ordfr to bddfss filf or subdirfdtorifs.
     *
     * <p> Dfpfnding on tif implfmfntbtion, tiis mftiod mby rfquirf to rfbd filf
     * pfrmissions, bddfss dontrol lists, or otifr filf bttributfs in ordfr to
     * difdk tif ffffdtivf bddfss to tif filf. Consfqufntly, tiis mftiod mby not
     * bf btomid witi rfspfdt to otifr filf systfm opfrbtions.
     *
     * <p> Notf tibt tif rfsult of tiis mftiod is immfdibtfly outdbtfd, tifrf is
     * no gubrbntff tibt b subsfqufnt bttfmpt to fxfdutf tif filf will suddffd
     * (or fvfn tibt it will bddfss tif sbmf filf). Cbrf siould bf tbkfn wifn
     * using tiis mftiod in sfdurity sfnsitivf bpplidbtions.
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf to difdk
     *
     * @rfturn  {@dodf truf} if tif filf fxists bnd is fxfdutbblf; {@dodf fblsf}
     *          if tif filf dofs not fxist, fxfdutf bddfss would bf dfnifd bfdbusf
     *          tif Jbvb virtubl mbdiinf ibs insuffidifnt privilfgfs, or bddfss
     *          dbnnot bf dftfrminfd
     *
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkExfd(String)
     *          difdkExfd} is invokfd to difdk fxfdutf bddfss to tif filf.
     */
    publid stbtid boolfbn isExfdutbblf(Pbti pbti) {
        rfturn isAddfssiblf(pbti, AddfssModf.EXECUTE);
    }

    // -- Rfdursivf opfrbtions --

    /**
     * Wblks b filf trff.
     *
     * <p> Tiis mftiod wblks b filf trff rootfd bt b givfn stbrting filf. Tif
     * filf trff trbvfrsbl is <fm>dfpti-first</fm> witi tif givfn {@link
     * FilfVisitor} invokfd for fbdi filf fndountfrfd. Filf trff trbvfrsbl
     * domplftfs wifn bll bddfssiblf filfs in tif trff ibvf bffn visitfd, or b
     * visit mftiod rfturns b rfsult of {@link FilfVisitRfsult#TERMINATE
     * TERMINATE}. Wifrf b visit mftiod tfrminbtfs duf bn {@dodf IOExdfption},
     * bn undbugit frror, or runtimf fxdfption, tifn tif trbvfrsbl is tfrminbtfd
     * bnd tif frror or fxdfption is propbgbtfd to tif dbllfr of tiis mftiod.
     *
     * <p> For fbdi filf fndountfrfd tiis mftiod bttfmpts to rfbd its {@link
     * jbvb.nio.filf.bttributf.BbsidFilfAttributfs}. If tif filf is not b
     * dirfdtory tifn tif {@link FilfVisitor#visitFilf visitFilf} mftiod is
     * invokfd witi tif filf bttributfs. If tif filf bttributfs dbnnot bf rfbd,
     * duf to bn I/O fxdfption, tifn tif {@link FilfVisitor#visitFilfFbilfd
     * visitFilfFbilfd} mftiod is invokfd witi tif I/O fxdfption.
     *
     * <p> Wifrf tif filf is b dirfdtory, bnd tif dirfdtory dould not bf opfnfd,
     * tifn tif {@dodf visitFilfFbilfd} mftiod is invokfd witi tif I/O fxdfption,
     * bftfr wiidi, tif filf trff wblk dontinufs, by dffbult, bt tif nfxt
     * <fm>sibling</fm> of tif dirfdtory.
     *
     * <p> Wifrf tif dirfdtory is opfnfd suddfssfully, tifn tif fntrifs in tif
     * dirfdtory, bnd tifir <fm>dfsdfndbnts</fm> brf visitfd. Wifn bll fntrifs
     * ibvf bffn visitfd, or bn I/O frror oddurs during itfrbtion of tif
     * dirfdtory, tifn tif dirfdtory is dlosfd bnd tif visitor's {@link
     * FilfVisitor#postVisitDirfdtory postVisitDirfdtory} mftiod is invokfd.
     * Tif filf trff wblk tifn dontinufs, by dffbult, bt tif nfxt <fm>sibling</fm>
     * of tif dirfdtory.
     *
     * <p> By dffbult, symbolid links brf not butombtidblly followfd by tiis
     * mftiod. If tif {@dodf options} pbrbmftfr dontbins tif {@link
     * FilfVisitOption#FOLLOW_LINKS FOLLOW_LINKS} option tifn symbolid links brf
     * followfd. Wifn following links, bnd tif bttributfs of tif tbrgft dbnnot
     * bf rfbd, tifn tiis mftiod bttfmpts to gft tif {@dodf BbsidFilfAttributfs}
     * of tif link. If tify dbn bf rfbd tifn tif {@dodf visitFilf} mftiod is
     * invokfd witi tif bttributfs of tif link (otifrwisf tif {@dodf visitFilfFbilfd}
     * mftiod is invokfd bs spfdififd bbovf).
     *
     * <p> If tif {@dodf options} pbrbmftfr dontbins tif {@link
     * FilfVisitOption#FOLLOW_LINKS FOLLOW_LINKS} option tifn tiis mftiod kffps
     * trbdk of dirfdtorifs visitfd so tibt dydlfs dbn bf dftfdtfd. A dydlf
     * brisfs wifn tifrf is bn fntry in b dirfdtory tibt is bn bndfstor of tif
     * dirfdtory. Cydlf dftfdtion is donf by rfdording tif {@link
     * jbvb.nio.filf.bttributf.BbsidFilfAttributfs#filfKfy filf-kfy} of dirfdtorifs,
     * or if filf kfys brf not bvbilbblf, by invoking tif {@link #isSbmfFilf
     * isSbmfFilf} mftiod to tfst if b dirfdtory is tif sbmf filf bs bn
     * bndfstor. Wifn b dydlf is dftfdtfd it is trfbtfd bs bn I/O frror, bnd tif
     * {@link FilfVisitor#visitFilfFbilfd visitFilfFbilfd} mftiod is invokfd witi
     * bn instbndf of {@link FilfSystfmLoopExdfption}.
     *
     * <p> Tif {@dodf mbxDfpti} pbrbmftfr is tif mbximum numbfr of lfvfls of
     * dirfdtorifs to visit. A vbluf of {@dodf 0} mfbns tibt only tif stbrting
     * filf is visitfd, unlfss dfnifd by tif sfdurity mbnbgfr. A vbluf of
     * {@link Intfgfr#MAX_VALUE MAX_VALUE} mby bf usfd to indidbtf tibt bll
     * lfvfls siould bf visitfd. Tif {@dodf visitFilf} mftiod is invokfd for bll
     * filfs, indluding dirfdtorifs, fndountfrfd bt {@dodf mbxDfpti}, unlfss tif
     * bbsid filf bttributfs dbnnot bf rfbd, in wiidi dbsf tif {@dodf
     * visitFilfFbilfd} mftiod is invokfd.
     *
     * <p> If b visitor rfturns b rfsult of {@dodf null} tifn {@dodf
     * NullPointfrExdfption} is tirown.
     *
     * <p> Wifn b sfdurity mbnbgfr is instbllfd bnd it dfnifs bddfss to b filf
     * (or dirfdtory), tifn it is ignorfd bnd tif visitor is not invokfd for
     * tibt filf (or dirfdtory).
     *
     * @pbrbm   stbrt
     *          tif stbrting filf
     * @pbrbm   options
     *          options to donfigurf tif trbvfrsbl
     * @pbrbm   mbxDfpti
     *          tif mbximum numbfr of dirfdtory lfvfls to visit
     * @pbrbm   visitor
     *          tif filf visitor to invokf for fbdi filf
     *
     * @rfturn  tif stbrting filf
     *
     * @tirows  IllfgblArgumfntExdfption
     *          if tif {@dodf mbxDfpti} pbrbmftfr is nfgbtivf
     * @tirows  SfdurityExdfption
     *          If tif sfdurity mbnbgfr dfnifs bddfss to tif stbrting filf.
     *          In tif dbsf of tif dffbult providfr, tif {@link
     *          SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd} mftiod is invokfd
     *          to difdk rfbd bddfss to tif dirfdtory.
     * @tirows  IOExdfption
     *          if bn I/O frror is tirown by b visitor mftiod
     */
    publid stbtid Pbti wblkFilfTrff(Pbti stbrt,
                                    Sft<FilfVisitOption> options,
                                    int mbxDfpti,
                                    FilfVisitor<? supfr Pbti> visitor)
        tirows IOExdfption
    {
        /**
         * Crfbtf b FilfTrffWblkfr to wblk tif filf trff, invoking tif visitor
         * for fbdi fvfnt.
         */
        try (FilfTrffWblkfr wblkfr = nfw FilfTrffWblkfr(options, mbxDfpti)) {
            FilfTrffWblkfr.Evfnt fv = wblkfr.wblk(stbrt);
            do {
                FilfVisitRfsult rfsult;
                switdi (fv.typf()) {
                    dbsf ENTRY :
                        IOExdfption iof = fv.iofExdfption();
                        if (iof == null) {
                            bssfrt fv.bttributfs() != null;
                            rfsult = visitor.visitFilf(fv.filf(), fv.bttributfs());
                        } flsf {
                            rfsult = visitor.visitFilfFbilfd(fv.filf(), iof);
                        }
                        brfbk;

                    dbsf START_DIRECTORY :
                        rfsult = visitor.prfVisitDirfdtory(fv.filf(), fv.bttributfs());

                        // if SKIP_SIBLINGS bnd SKIP_SUBTREE is rfturnfd tifn
                        // tifrf siouldn't bf bny morf fvfnts for tif durrfnt
                        // dirfdtory.
                        if (rfsult == FilfVisitRfsult.SKIP_SUBTREE ||
                            rfsult == FilfVisitRfsult.SKIP_SIBLINGS)
                            wblkfr.pop();
                        brfbk;

                    dbsf END_DIRECTORY :
                        rfsult = visitor.postVisitDirfdtory(fv.filf(), fv.iofExdfption());

                        // SKIP_SIBLINGS is b no-op for postVisitDirfdtory
                        if (rfsult == FilfVisitRfsult.SKIP_SIBLINGS)
                            rfsult = FilfVisitRfsult.CONTINUE;
                        brfbk;

                    dffbult :
                        tirow nfw AssfrtionError("Siould not gft ifrf");
                }

                if (Objfdts.rfquirfNonNull(rfsult) != FilfVisitRfsult.CONTINUE) {
                    if (rfsult == FilfVisitRfsult.TERMINATE) {
                        brfbk;
                    } flsf if (rfsult == FilfVisitRfsult.SKIP_SIBLINGS) {
                        wblkfr.skipRfmbiningSiblings();
                    }
                }
                fv = wblkfr.nfxt();
            } wiilf (fv != null);
        }

        rfturn stbrt;
    }

    /**
     * Wblks b filf trff.
     *
     * <p> Tiis mftiod works bs if invoking it wfrf fquivblfnt to fvblubting tif
     * fxprfssion:
     * <blodkquotf><prf>
     * wblkFilfTrff(stbrt, EnumSft.nonfOf(FilfVisitOption.dlbss), Intfgfr.MAX_VALUE, visitor)
     * </prf></blodkquotf>
     * In otifr words, it dofs not follow symbolid links, bnd visits bll lfvfls
     * of tif filf trff.
     *
     * @pbrbm   stbrt
     *          tif stbrting filf
     * @pbrbm   visitor
     *          tif filf visitor to invokf for fbdi filf
     *
     * @rfturn  tif stbrting filf
     *
     * @tirows  SfdurityExdfption
     *          If tif sfdurity mbnbgfr dfnifs bddfss to tif stbrting filf.
     *          In tif dbsf of tif dffbult providfr, tif {@link
     *          SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd} mftiod is invokfd
     *          to difdk rfbd bddfss to tif dirfdtory.
     * @tirows  IOExdfption
     *          if bn I/O frror is tirown by b visitor mftiod
     */
    publid stbtid Pbti wblkFilfTrff(Pbti stbrt, FilfVisitor<? supfr Pbti> visitor)
        tirows IOExdfption
    {
        rfturn wblkFilfTrff(stbrt,
                            EnumSft.nonfOf(FilfVisitOption.dlbss),
                            Intfgfr.MAX_VALUE,
                            visitor);
    }


    // -- Utility mftiods for simplf usbgfs --

    // bufffr sizf usfd for rfbding bnd writing
    privbtf stbtid finbl int BUFFER_SIZE = 8192;

    /**
     * Opfns b filf for rfbding, rfturning b {@dodf BufffrfdRfbdfr} tibt mby bf
     * usfd to rfbd tfxt from tif filf in bn fffidifnt mbnnfr. Bytfs from tif
     * filf brf dfdodfd into dibrbdtfrs using tif spfdififd dibrsft. Rfbding
     * dommfndfs bt tif bfginning of tif filf.
     *
     * <p> Tif {@dodf Rfbdfr} mftiods tibt rfbd from tif filf tirow {@dodf
     * IOExdfption} if b mblformfd or unmbppbblf bytf sfqufndf is rfbd.
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf
     * @pbrbm   ds
     *          tif dibrsft to usf for dfdoding
     *
     * @rfturn  b nfw bufffrfd rfbdfr, witi dffbult bufffr sizf, to rfbd tfxt
     *          from tif filf
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs opfning tif filf
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd}
     *          mftiod is invokfd to difdk rfbd bddfss to tif filf.
     *
     * @sff #rfbdAllLinfs
     */
    publid stbtid BufffrfdRfbdfr nfwBufffrfdRfbdfr(Pbti pbti, Cibrsft ds)
        tirows IOExdfption
    {
        CibrsftDfdodfr dfdodfr = ds.nfwDfdodfr();
        Rfbdfr rfbdfr = nfw InputStrfbmRfbdfr(nfwInputStrfbm(pbti), dfdodfr);
        rfturn nfw BufffrfdRfbdfr(rfbdfr);
    }

    /**
     * Opfns b filf for rfbding, rfturning b {@dodf BufffrfdRfbdfr} to rfbd tfxt
     * from tif filf in bn fffidifnt mbnnfr. Bytfs from tif filf brf dfdodfd into
     * dibrbdtfrs using tif {@link StbndbrdCibrsfts#UTF_8 UTF-8} {@link Cibrsft
     * dibrsft}.
     *
     * <p> Tiis mftiod works bs if invoking it wfrf fquivblfnt to fvblubting tif
     * fxprfssion:
     * <prf>{@dodf
     * Filfs.nfwBufffrfdRfbdfr(pbti, StbndbrdCibrsfts.UTF_8)
     * }</prf>
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf
     *
     * @rfturn  b nfw bufffrfd rfbdfr, witi dffbult bufffr sizf, to rfbd tfxt
     *          from tif filf
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs opfning tif filf
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd}
     *          mftiod is invokfd to difdk rfbd bddfss to tif filf.
     *
     * @sindf 1.8
     */
    publid stbtid BufffrfdRfbdfr nfwBufffrfdRfbdfr(Pbti pbti) tirows IOExdfption {
        rfturn nfwBufffrfdRfbdfr(pbti, StbndbrdCibrsfts.UTF_8);
    }

    /**
     * Opfns or drfbtfs b filf for writing, rfturning b {@dodf BufffrfdWritfr}
     * tibt mby bf usfd to writf tfxt to tif filf in bn fffidifnt mbnnfr.
     * Tif {@dodf options} pbrbmftfr spfdififs iow tif tif filf is drfbtfd or
     * opfnfd. If no options brf prfsfnt tifn tiis mftiod works bs if tif {@link
     * StbndbrdOpfnOption#CREATE CREATE}, {@link
     * StbndbrdOpfnOption#TRUNCATE_EXISTING TRUNCATE_EXISTING}, bnd {@link
     * StbndbrdOpfnOption#WRITE WRITE} options brf prfsfnt. In otifr words, it
     * opfns tif filf for writing, drfbting tif filf if it dofsn't fxist, or
     * initiblly trundbting bn fxisting {@link #isRfgulbrFilf rfgulbr-filf} to
     * b sizf of {@dodf 0} if it fxists.
     *
     * <p> Tif {@dodf Writfr} mftiods to writf tfxt tirow {@dodf IOExdfption}
     * if tif tfxt dbnnot bf fndodfd using tif spfdififd dibrsft.
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf
     * @pbrbm   ds
     *          tif dibrsft to usf for fndoding
     * @pbrbm   options
     *          options spfdifying iow tif filf is opfnfd
     *
     * @rfturn  b nfw bufffrfd writfr, witi dffbult bufffr sizf, to writf tfxt
     *          to tif filf
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs opfning or drfbting tif filf
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if bn unsupportfd option is spfdififd
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkWritf(String) difdkWritf}
     *          mftiod is invokfd to difdk writf bddfss to tif filf.
     *
     * @sff #writf(Pbti,Itfrbblf,Cibrsft,OpfnOption[])
     */
    publid stbtid BufffrfdWritfr nfwBufffrfdWritfr(Pbti pbti, Cibrsft ds,
                                                   OpfnOption... options)
        tirows IOExdfption
    {
        CibrsftEndodfr fndodfr = ds.nfwEndodfr();
        Writfr writfr = nfw OutputStrfbmWritfr(nfwOutputStrfbm(pbti, options), fndodfr);
        rfturn nfw BufffrfdWritfr(writfr);
    }

    /**
     * Opfns or drfbtfs b filf for writing, rfturning b {@dodf BufffrfdWritfr}
     * to writf tfxt to tif filf in bn fffidifnt mbnnfr. Tif tfxt is fndodfd
     * into bytfs for writing using tif {@link StbndbrdCibrsfts#UTF_8 UTF-8}
     * {@link Cibrsft dibrsft}.
     *
     * <p> Tiis mftiod works bs if invoking it wfrf fquivblfnt to fvblubting tif
     * fxprfssion:
     * <prf>{@dodf
     * Filfs.nfwBufffrfdWritfr(pbti, StbndbrdCibrsfts.UTF_8, options)
     * }</prf>
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf
     * @pbrbm   options
     *          options spfdifying iow tif filf is opfnfd
     *
     * @rfturn  b nfw bufffrfd writfr, witi dffbult bufffr sizf, to writf tfxt
     *          to tif filf
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs opfning or drfbting tif filf
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if bn unsupportfd option is spfdififd
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkWritf(String) difdkWritf}
     *          mftiod is invokfd to difdk writf bddfss to tif filf.
     *
     * @sindf 1.8
     */
    publid stbtid BufffrfdWritfr nfwBufffrfdWritfr(Pbti pbti, OpfnOption... options) tirows IOExdfption {
        rfturn nfwBufffrfdWritfr(pbti, StbndbrdCibrsfts.UTF_8, options);
    }

    /**
     * Rfbds bll bytfs from bn input strfbm bnd writfs tifm to bn output strfbm.
     */
    privbtf stbtid long dopy(InputStrfbm sourdf, OutputStrfbm sink)
        tirows IOExdfption
    {
        long nrfbd = 0L;
        bytf[] buf = nfw bytf[BUFFER_SIZE];
        int n;
        wiilf ((n = sourdf.rfbd(buf)) > 0) {
            sink.writf(buf, 0, n);
            nrfbd += n;
        }
        rfturn nrfbd;
    }

    /**
     * Copifs bll bytfs from bn input strfbm to b filf. On rfturn, tif input
     * strfbm will bf bt fnd of strfbm.
     *
     * <p> By dffbult, tif dopy fbils if tif tbrgft filf blrfbdy fxists or is b
     * symbolid link. If tif {@link StbndbrdCopyOption#REPLACE_EXISTING
     * REPLACE_EXISTING} option is spfdififd, bnd tif tbrgft filf blrfbdy fxists,
     * tifn it is rfplbdfd if it is not b non-fmpty dirfdtory. If tif tbrgft
     * filf fxists bnd is b symbolid link, tifn tif symbolid link is rfplbdfd.
     * In tiis rflfbsf, tif {@dodf REPLACE_EXISTING} option is tif only option
     * rfquirfd to bf supportfd by tiis mftiod. Additionbl options mby bf
     * supportfd in futurf rflfbsfs.
     *
     * <p>  If bn I/O frror oddurs rfbding from tif input strfbm or writing to
     * tif filf, tifn it mby do so bftfr tif tbrgft filf ibs bffn drfbtfd bnd
     * bftfr somf bytfs ibvf bffn rfbd or writtfn. Consfqufntly tif input
     * strfbm mby not bf bt fnd of strfbm bnd mby bf in bn indonsistfnt stbtf.
     * It is strongly rfdommfndfd tibt tif input strfbm bf promptly dlosfd if bn
     * I/O frror oddurs.
     *
     * <p> Tiis mftiod mby blodk indffinitfly rfbding from tif input strfbm (or
     * writing to tif filf). Tif bfibvior for tif dbsf tibt tif input strfbm is
     * <i>bsyndironously dlosfd</i> or tif tirfbd intfrruptfd during tif dopy is
     * iigily input strfbm bnd filf systfm providfr spfdifid bnd tifrfforf not
     * spfdififd.
     *
     * <p> <b>Usbgf fxbmplf</b>: Supposf wf wbnt to dbpturf b wfb pbgf bnd sbvf
     * it to b filf:
     * <prf>
     *     Pbti pbti = ...
     *     URI u = URI.drfbtf("ittp://jbvb.sun.dom/");
     *     try (InputStrfbm in = u.toURL().opfnStrfbm()) {
     *         Filfs.dopy(in, pbti);
     *     }
     * </prf>
     *
     * @pbrbm   in
     *          tif input strfbm to rfbd from
     * @pbrbm   tbrgft
     *          tif pbti to tif filf
     * @pbrbm   options
     *          options spfdifying iow tif dopy siould bf donf
     *
     * @rfturn  tif numbfr of bytfs rfbd or writtfn
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs wifn rfbding or writing
     * @tirows  FilfAlrfbdyExistsExdfption
     *          if tif tbrgft filf fxists but dbnnot bf rfplbdfd bfdbusf tif
     *          {@dodf REPLACE_EXISTING} option is not spfdififd <i>(optionbl
     *          spfdifid fxdfption)</i>
     * @tirows  DirfdtoryNotEmptyExdfption
     *          tif {@dodf REPLACE_EXISTING} option is spfdififd but tif filf
     *          dbnnot bf rfplbdfd bfdbusf it is b non-fmpty dirfdtory
     *          <i>(optionbl spfdifid fxdfption)</i>     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if {@dodf options} dontbins b dopy option tibt is not supportfd
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkWritf(String) difdkWritf}
     *          mftiod is invokfd to difdk writf bddfss to tif filf. Wifrf tif
     *          {@dodf REPLACE_EXISTING} option is spfdififd, tif sfdurity
     *          mbnbgfr's {@link SfdurityMbnbgfr#difdkDflftf(String) difdkDflftf}
     *          mftiod is invokfd to difdk tibt bn fxisting filf dbn bf dflftfd.
     */
    publid stbtid long dopy(InputStrfbm in, Pbti tbrgft, CopyOption... options)
        tirows IOExdfption
    {
        // fnsurf not null bfforf opfning filf
        Objfdts.rfquirfNonNull(in);

        // difdk for REPLACE_EXISTING
        boolfbn rfplbdfExisting = fblsf;
        for (CopyOption opt: options) {
            if (opt == StbndbrdCopyOption.REPLACE_EXISTING) {
                rfplbdfExisting = truf;
            } flsf {
                if (opt == null) {
                    tirow nfw NullPointfrExdfption("options dontbins 'null'");
                }  flsf {
                    tirow nfw UnsupportfdOpfrbtionExdfption(opt + " not supportfd");
                }
            }
        }

        // bttfmpt to dflftf bn fxisting filf
        SfdurityExdfption sf = null;
        if (rfplbdfExisting) {
            try {
                dflftfIfExists(tbrgft);
            } dbtdi (SfdurityExdfption x) {
                sf = x;
            }
        }

        // bttfmpt to drfbtf tbrgft filf. If it fbils witi
        // FilfAlrfbdyExistsExdfption tifn it mby bf bfdbusf tif sfdurity
        // mbnbgfr prfvfntfd us from dflfting tif filf, in wiidi dbsf wf just
        // tirow tif SfdurityExdfption.
        OutputStrfbm ostrfbm;
        try {
            ostrfbm = nfwOutputStrfbm(tbrgft, StbndbrdOpfnOption.CREATE_NEW,
                                              StbndbrdOpfnOption.WRITE);
        } dbtdi (FilfAlrfbdyExistsExdfption x) {
            if (sf != null)
                tirow sf;
            // somfonf flsf won tif rbdf bnd drfbtfd tif filf
            tirow x;
        }

        // do tif dopy
        try (OutputStrfbm out = ostrfbm) {
            rfturn dopy(in, out);
        }
    }

    /**
     * Copifs bll bytfs from b filf to bn output strfbm.
     *
     * <p> If bn I/O frror oddurs rfbding from tif filf or writing to tif output
     * strfbm, tifn it mby do so bftfr somf bytfs ibvf bffn rfbd or writtfn.
     * Consfqufntly tif output strfbm mby bf in bn indonsistfnt stbtf. It is
     * strongly rfdommfndfd tibt tif output strfbm bf promptly dlosfd if bn I/O
     * frror oddurs.
     *
     * <p> Tiis mftiod mby blodk indffinitfly writing to tif output strfbm (or
     * rfbding from tif filf). Tif bfibvior for tif dbsf tibt tif output strfbm
     * is <i>bsyndironously dlosfd</i> or tif tirfbd intfrruptfd during tif dopy
     * is iigily output strfbm bnd filf systfm providfr spfdifid bnd tifrfforf
     * not spfdififd.
     *
     * <p> Notf tibt if tif givfn output strfbm is {@link jbvb.io.Flusibblf}
     * tifn its {@link jbvb.io.Flusibblf#flusi flusi} mftiod mby nffd to invokfd
     * bftfr tiis mftiod domplftfs so bs to flusi bny bufffrfd output.
     *
     * @pbrbm   sourdf
     *          tif  pbti to tif filf
     * @pbrbm   out
     *          tif output strfbm to writf to
     *
     * @rfturn  tif numbfr of bytfs rfbd or writtfn
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs wifn rfbding or writing
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd}
     *          mftiod is invokfd to difdk rfbd bddfss to tif filf.
     */
    publid stbtid long dopy(Pbti sourdf, OutputStrfbm out) tirows IOExdfption {
        // fnsurf not null bfforf opfning filf
        Objfdts.rfquirfNonNull(out);

        try (InputStrfbm in = nfwInputStrfbm(sourdf)) {
            rfturn dopy(in, out);
        }
    }

    /**
     * Tif mbximum sizf of brrby to bllodbtf.
     * Somf VMs rfsfrvf somf ifbdfr words in bn brrby.
     * Attfmpts to bllodbtf lbrgfr brrbys mby rfsult in
     * OutOfMfmoryError: Rfqufstfd brrby sizf fxdffds VM limit
     */
    privbtf stbtid finbl int MAX_BUFFER_SIZE = Intfgfr.MAX_VALUE - 8;

    /**
     * Rfbds bll tif bytfs from bn input strfbm. Usfs {@dodf initiblSizf} bs b iint
     * bbout iow mbny bytfs tif strfbm will ibvf.
     *
     * @pbrbm   sourdf
     *          tif input strfbm to rfbd from
     * @pbrbm   initiblSizf
     *          tif initibl sizf of tif bytf brrby to bllodbtf
     *
     * @rfturn  b bytf brrby dontbining tif bytfs rfbd from tif filf
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs rfbding from tif strfbm
     * @tirows  OutOfMfmoryError
     *          if bn brrby of tif rfquirfd sizf dbnnot bf bllodbtfd
     */
    privbtf stbtid bytf[] rfbd(InputStrfbm sourdf, int initiblSizf) tirows IOExdfption {
        int dbpbdity = initiblSizf;
        bytf[] buf = nfw bytf[dbpbdity];
        int nrfbd = 0;
        int n;
        for (;;) {
            // rfbd to EOF wiidi mby rfbd morf or lfss tibn initiblSizf (fg: filf
            // is trundbtfd wiilf wf brf rfbding)
            wiilf ((n = sourdf.rfbd(buf, nrfbd, dbpbdity - nrfbd)) > 0)
                nrfbd += n;

            // if lbst dbll to sourdf.rfbd() rfturnfd -1, wf brf donf
            // otifrwisf, try to rfbd onf morf bytf; if tibt fbilfd wf'rf donf too
            if (n < 0 || (n = sourdf.rfbd()) < 0)
                brfbk;

            // onf morf bytf wbs rfbd; nffd to bllodbtf b lbrgfr bufffr
            if (dbpbdity <= MAX_BUFFER_SIZE - dbpbdity) {
                dbpbdity = Mbti.mbx(dbpbdity << 1, BUFFER_SIZE);
            } flsf {
                if (dbpbdity == MAX_BUFFER_SIZE)
                    tirow nfw OutOfMfmoryError("Rfquirfd brrby sizf too lbrgf");
                dbpbdity = MAX_BUFFER_SIZE;
            }
            buf = Arrbys.dopyOf(buf, dbpbdity);
            buf[nrfbd++] = (bytf)n;
        }
        rfturn (dbpbdity == nrfbd) ? buf : Arrbys.dopyOf(buf, nrfbd);
    }

    /**
     * Rfbds bll tif bytfs from b filf. Tif mftiod fnsurfs tibt tif filf is
     * dlosfd wifn bll bytfs ibvf bffn rfbd or bn I/O frror, or otifr runtimf
     * fxdfption, is tirown.
     *
     * <p> Notf tibt tiis mftiod is intfndfd for simplf dbsfs wifrf it is
     * donvfnifnt to rfbd bll bytfs into b bytf brrby. It is not intfndfd for
     * rfbding in lbrgf filfs.
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf
     *
     * @rfturn  b bytf brrby dontbining tif bytfs rfbd from tif filf
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs rfbding from tif strfbm
     * @tirows  OutOfMfmoryError
     *          if bn brrby of tif rfquirfd sizf dbnnot bf bllodbtfd, for
     *          fxbmplf tif filf is lbrgfr tibt {@dodf 2GB}
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd}
     *          mftiod is invokfd to difdk rfbd bddfss to tif filf.
     */
    publid stbtid bytf[] rfbdAllBytfs(Pbti pbti) tirows IOExdfption {
        try (SffkbblfBytfCibnnfl sbd = Filfs.nfwBytfCibnnfl(pbti);
             InputStrfbm in = Cibnnfls.nfwInputStrfbm(sbd)) {
            long sizf = sbd.sizf();
            if (sizf > (long)MAX_BUFFER_SIZE)
                tirow nfw OutOfMfmoryError("Rfquirfd brrby sizf too lbrgf");

            rfturn rfbd(in, (int)sizf);
        }
    }

    /**
     * Rfbd bll linfs from b filf. Tiis mftiod fnsurfs tibt tif filf is
     * dlosfd wifn bll bytfs ibvf bffn rfbd or bn I/O frror, or otifr runtimf
     * fxdfption, is tirown. Bytfs from tif filf brf dfdodfd into dibrbdtfrs
     * using tif spfdififd dibrsft.
     *
     * <p> Tiis mftiod rfdognizfs tif following bs linf tfrminbtors:
     * <ul>
     *   <li> <dodf>&#92;u000D</dodf> followfd by <dodf>&#92;u000A</dodf>,
     *     CARRIAGE RETURN followfd by LINE FEED </li>
     *   <li> <dodf>&#92;u000A</dodf>, LINE FEED </li>
     *   <li> <dodf>&#92;u000D</dodf>, CARRIAGE RETURN </li>
     * </ul>
     * <p> Additionbl Unidodf linf tfrminbtors mby bf rfdognizfd in futurf
     * rflfbsfs.
     *
     * <p> Notf tibt tiis mftiod is intfndfd for simplf dbsfs wifrf it is
     * donvfnifnt to rfbd bll linfs in b singlf opfrbtion. It is not intfndfd
     * for rfbding in lbrgf filfs.
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf
     * @pbrbm   ds
     *          tif dibrsft to usf for dfdoding
     *
     * @rfturn  tif linfs from tif filf bs b {@dodf List}; wiftifr tif {@dodf
     *          List} is modifibblf or not is implfmfntbtion dfpfndfnt bnd
     *          tifrfforf not spfdififd
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs rfbding from tif filf or b mblformfd or
     *          unmbppbblf bytf sfqufndf is rfbd
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd}
     *          mftiod is invokfd to difdk rfbd bddfss to tif filf.
     *
     * @sff #nfwBufffrfdRfbdfr
     */
    publid stbtid List<String> rfbdAllLinfs(Pbti pbti, Cibrsft ds) tirows IOExdfption {
        try (BufffrfdRfbdfr rfbdfr = nfwBufffrfdRfbdfr(pbti, ds)) {
            List<String> rfsult = nfw ArrbyList<>();
            for (;;) {
                String linf = rfbdfr.rfbdLinf();
                if (linf == null)
                    brfbk;
                rfsult.bdd(linf);
            }
            rfturn rfsult;
        }
    }

    /**
     * Rfbd bll linfs from b filf. Bytfs from tif filf brf dfdodfd into dibrbdtfrs
     * using tif {@link StbndbrdCibrsfts#UTF_8 UTF-8} {@link Cibrsft dibrsft}.
     *
     * <p> Tiis mftiod works bs if invoking it wfrf fquivblfnt to fvblubting tif
     * fxprfssion:
     * <prf>{@dodf
     * Filfs.rfbdAllLinfs(pbti, StbndbrdCibrsfts.UTF_8)
     * }</prf>
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf
     *
     * @rfturn  tif linfs from tif filf bs b {@dodf List}; wiftifr tif {@dodf
     *          List} is modifibblf or not is implfmfntbtion dfpfndfnt bnd
     *          tifrfforf not spfdififd
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs rfbding from tif filf or b mblformfd or
     *          unmbppbblf bytf sfqufndf is rfbd
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd}
     *          mftiod is invokfd to difdk rfbd bddfss to tif filf.
     *
     * @sindf 1.8
     */
    publid stbtid List<String> rfbdAllLinfs(Pbti pbti) tirows IOExdfption {
        rfturn rfbdAllLinfs(pbti, StbndbrdCibrsfts.UTF_8);
    }

    /**
     * Writfs bytfs to b filf. Tif {@dodf options} pbrbmftfr spfdififs iow tif
     * tif filf is drfbtfd or opfnfd. If no options brf prfsfnt tifn tiis mftiod
     * works bs if tif {@link StbndbrdOpfnOption#CREATE CREATE}, {@link
     * StbndbrdOpfnOption#TRUNCATE_EXISTING TRUNCATE_EXISTING}, bnd {@link
     * StbndbrdOpfnOption#WRITE WRITE} options brf prfsfnt. In otifr words, it
     * opfns tif filf for writing, drfbting tif filf if it dofsn't fxist, or
     * initiblly trundbting bn fxisting {@link #isRfgulbrFilf rfgulbr-filf} to
     * b sizf of {@dodf 0}. All bytfs in tif bytf brrby brf writtfn to tif filf.
     * Tif mftiod fnsurfs tibt tif filf is dlosfd wifn bll bytfs ibvf bffn
     * writtfn (or bn I/O frror or otifr runtimf fxdfption is tirown). If bn I/O
     * frror oddurs tifn it mby do so bftfr tif filf ibs drfbtfd or trundbtfd,
     * or bftfr somf bytfs ibvf bffn writtfn to tif filf.
     *
     * <p> <b>Usbgf fxbmplf</b>: By dffbult tif mftiod drfbtfs b nfw filf or
     * ovfrwritfs bn fxisting filf. Supposf you instfbd wbnt to bppfnd bytfs
     * to bn fxisting filf:
     * <prf>
     *     Pbti pbti = ...
     *     bytf[] bytfs = ...
     *     Filfs.writf(pbti, bytfs, StbndbrdOpfnOption.APPEND);
     * </prf>
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf
     * @pbrbm   bytfs
     *          tif bytf brrby witi tif bytfs to writf
     * @pbrbm   options
     *          options spfdifying iow tif filf is opfnfd
     *
     * @rfturn  tif pbti
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs writing to or drfbting tif filf
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if bn unsupportfd option is spfdififd
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkWritf(String) difdkWritf}
     *          mftiod is invokfd to difdk writf bddfss to tif filf.
     */
    publid stbtid Pbti writf(Pbti pbti, bytf[] bytfs, OpfnOption... options)
        tirows IOExdfption
    {
        // fnsurf bytfs is not null bfforf opfning filf
        Objfdts.rfquirfNonNull(bytfs);

        try (OutputStrfbm out = Filfs.nfwOutputStrfbm(pbti, options)) {
            int lfn = bytfs.lfngti;
            int rfm = lfn;
            wiilf (rfm > 0) {
                int n = Mbti.min(rfm, BUFFER_SIZE);
                out.writf(bytfs, (lfn-rfm), n);
                rfm -= n;
            }
        }
        rfturn pbti;
    }

    /**
     * Writf linfs of tfxt to b filf. Ebdi linf is b dibr sfqufndf bnd is
     * writtfn to tif filf in sfqufndf witi fbdi linf tfrminbtfd by tif
     * plbtform's linf sfpbrbtor, bs dffinfd by tif systfm propfrty {@dodf
     * linf.sfpbrbtor}. Cibrbdtfrs brf fndodfd into bytfs using tif spfdififd
     * dibrsft.
     *
     * <p> Tif {@dodf options} pbrbmftfr spfdififs iow tif tif filf is drfbtfd
     * or opfnfd. If no options brf prfsfnt tifn tiis mftiod works bs if tif
     * {@link StbndbrdOpfnOption#CREATE CREATE}, {@link
     * StbndbrdOpfnOption#TRUNCATE_EXISTING TRUNCATE_EXISTING}, bnd {@link
     * StbndbrdOpfnOption#WRITE WRITE} options brf prfsfnt. In otifr words, it
     * opfns tif filf for writing, drfbting tif filf if it dofsn't fxist, or
     * initiblly trundbting bn fxisting {@link #isRfgulbrFilf rfgulbr-filf} to
     * b sizf of {@dodf 0}. Tif mftiod fnsurfs tibt tif filf is dlosfd wifn bll
     * linfs ibvf bffn writtfn (or bn I/O frror or otifr runtimf fxdfption is
     * tirown). If bn I/O frror oddurs tifn it mby do so bftfr tif filf ibs
     * drfbtfd or trundbtfd, or bftfr somf bytfs ibvf bffn writtfn to tif filf.
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf
     * @pbrbm   linfs
     *          bn objfdt to itfrbtf ovfr tif dibr sfqufndfs
     * @pbrbm   ds
     *          tif dibrsft to usf for fndoding
     * @pbrbm   options
     *          options spfdifying iow tif filf is opfnfd
     *
     * @rfturn  tif pbti
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs writing to or drfbting tif filf, or tif
     *          tfxt dbnnot bf fndodfd using tif spfdififd dibrsft
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if bn unsupportfd option is spfdififd
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkWritf(String) difdkWritf}
     *          mftiod is invokfd to difdk writf bddfss to tif filf.
     */
    publid stbtid Pbti writf(Pbti pbti, Itfrbblf<? fxtfnds CibrSfqufndf> linfs,
                             Cibrsft ds, OpfnOption... options)
        tirows IOExdfption
    {
        // fnsurf linfs is not null bfforf opfning filf
        Objfdts.rfquirfNonNull(linfs);
        CibrsftEndodfr fndodfr = ds.nfwEndodfr();
        OutputStrfbm out = nfwOutputStrfbm(pbti, options);
        try (BufffrfdWritfr writfr = nfw BufffrfdWritfr(nfw OutputStrfbmWritfr(out, fndodfr))) {
            for (CibrSfqufndf linf: linfs) {
                writfr.bppfnd(linf);
                writfr.nfwLinf();
            }
        }
        rfturn pbti;
    }

    /**
     * Writf linfs of tfxt to b filf. Cibrbdtfrs brf fndodfd into bytfs using
     * tif {@link StbndbrdCibrsfts#UTF_8 UTF-8} {@link Cibrsft dibrsft}.
     *
     * <p> Tiis mftiod works bs if invoking it wfrf fquivblfnt to fvblubting tif
     * fxprfssion:
     * <prf>{@dodf
     * Filfs.writf(pbti, linfs, StbndbrdCibrsfts.UTF_8, options);
     * }</prf>
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf
     * @pbrbm   linfs
     *          bn objfdt to itfrbtf ovfr tif dibr sfqufndfs
     * @pbrbm   options
     *          options spfdifying iow tif filf is opfnfd
     *
     * @rfturn  tif pbti
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs writing to or drfbting tif filf, or tif
     *          tfxt dbnnot bf fndodfd bs {@dodf UTF-8}
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if bn unsupportfd option is spfdififd
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkWritf(String) difdkWritf}
     *          mftiod is invokfd to difdk writf bddfss to tif filf.
     *
     * @sindf 1.8
     */
    publid stbtid Pbti writf(Pbti pbti,
                             Itfrbblf<? fxtfnds CibrSfqufndf> linfs,
                             OpfnOption... options)
        tirows IOExdfption
    {
        rfturn writf(pbti, linfs, StbndbrdCibrsfts.UTF_8, options);
    }

    // -- Strfbm APIs --

    /**
     * Rfturn b lbzily populbtfd {@dodf Strfbm}, tif flfmfnts of
     * wiidi brf tif fntrifs in tif dirfdtory.  Tif listing is not rfdursivf.
     *
     * <p> Tif flfmfnts of tif strfbm brf {@link Pbti} objfdts tibt brf
     * obtbinfd bs if by {@link Pbti#rfsolvf(Pbti) rfsolving} tif nbmf of tif
     * dirfdtory fntry bgbinst {@dodf dir}. Somf filf systfms mbintbin spfdibl
     * links to tif dirfdtory itsflf bnd tif dirfdtory's pbrfnt dirfdtory.
     * Entrifs rfprfsfnting tifsf links brf not indludfd.
     *
     * <p> Tif strfbm is <i>wfbkly donsistfnt</i>. It is tirfbd sbff but dofs
     * not frffzf tif dirfdtory wiilf itfrbting, so it mby (or mby not)
     * rfflfdt updbtfs to tif dirfdtory tibt oddur bftfr rfturning from tiis
     * mftiod.
     *
     * <p> Tif rfturnfd strfbm fndbpsulbtfs b {@link DirfdtoryStrfbm}.
     * If timfly disposbl of filf systfm rfsourdfs is rfquirfd, tif
     * {@dodf try}-witi-rfsourdfs donstrudt siould bf usfd to fnsurf tibt tif
     * strfbm's {@link Strfbm#dlosf dlosf} mftiod is invokfd bftfr tif strfbm
     * opfrbtions brf domplftfd.
     *
     * <p> Opfrbting on b dlosfd strfbm bfibvfs bs if tif fnd of strfbm
     * ibs bffn rfbdifd. Duf to rfbd-bifbd, onf or morf flfmfnts mby bf
     * rfturnfd bftfr tif strfbm ibs bffn dlosfd.
     *
     * <p> If bn {@link IOExdfption} is tirown wifn bddfssing tif dirfdtory
     * bftfr tiis mftiod ibs rfturnfd, it is wrbppfd in bn {@link
     * UndifdkfdIOExdfption} wiidi will bf tirown from tif mftiod tibt dbusfd
     * tif bddfss to tbkf plbdf.
     *
     * @pbrbm   dir  Tif pbti to tif dirfdtory
     *
     * @rfturn  Tif {@dodf Strfbm} dfsdribing tif dontfnt of tif
     *          dirfdtory
     *
     * @tirows  NotDirfdtoryExdfption
     *          if tif filf dould not otifrwisf bf opfnfd bfdbusf it is not
     *          b dirfdtory <i>(optionbl spfdifid fxdfption)</i>
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs wifn opfning tif dirfdtory
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd}
     *          mftiod is invokfd to difdk rfbd bddfss to tif dirfdtory.
     *
     * @sff     #nfwDirfdtoryStrfbm(Pbti)
     * @sindf   1.8
     */
    publid stbtid Strfbm<Pbti> list(Pbti dir) tirows IOExdfption {
        DirfdtoryStrfbm<Pbti> ds = Filfs.nfwDirfdtoryStrfbm(dir);
        try {
            finbl Itfrbtor<Pbti> dflfgbtf = ds.itfrbtor();

            // Rf-wrbp DirfdtoryItfrbtorExdfption to UndifdkfdIOExdfption
            Itfrbtor<Pbti> it = nfw Itfrbtor<Pbti>() {
                @Ovfrridf
                publid boolfbn ibsNfxt() {
                    try {
                        rfturn dflfgbtf.ibsNfxt();
                    } dbtdi (DirfdtoryItfrbtorExdfption f) {
                        tirow nfw UndifdkfdIOExdfption(f.gftCbusf());
                    }
                }
                @Ovfrridf
                publid Pbti nfxt() {
                    try {
                        rfturn dflfgbtf.nfxt();
                    } dbtdi (DirfdtoryItfrbtorExdfption f) {
                        tirow nfw UndifdkfdIOExdfption(f.gftCbusf());
                    }
                }
            };

            rfturn StrfbmSupport.strfbm(Splitfrbtors.splitfrbtorUnknownSizf(it, Splitfrbtor.DISTINCT), fblsf)
                                .onClosf(bsUndifdkfdRunnbblf(ds));
        } dbtdi (Error|RuntimfExdfption f) {
            try {
                ds.dlosf();
            } dbtdi (IOExdfption fx) {
                try {
                    f.bddSupprfssfd(fx);
                } dbtdi (Tirowbblf ignorf) {}
            }
            tirow f;
        }
    }

    /**
     * Rfturn b {@dodf Strfbm} tibt is lbzily populbtfd witi {@dodf
     * Pbti} by wblking tif filf trff rootfd bt b givfn stbrting filf.  Tif
     * filf trff is trbvfrsfd <fm>dfpti-first</fm>, tif flfmfnts in tif strfbm
     * brf {@link Pbti} objfdts tibt brf obtbinfd bs if by {@link
     * Pbti#rfsolvf(Pbti) rfsolving} tif rflbtivf pbti bgbinst {@dodf stbrt}.
     *
     * <p> Tif {@dodf strfbm} wblks tif filf trff bs flfmfnts brf donsumfd.
     * Tif {@dodf Strfbm} rfturnfd is gubrbntffd to ibvf bt lfbst onf
     * flfmfnt, tif stbrting filf itsflf. For fbdi filf visitfd, tif strfbm
     * bttfmpts to rfbd its {@link BbsidFilfAttributfs}. If tif filf is b
     * dirfdtory bnd dbn bf opfnfd suddfssfully, fntrifs in tif dirfdtory, bnd
     * tifir <fm>dfsdfndbnts</fm> will follow tif dirfdtory in tif strfbm bs
     * tify brf fndountfrfd. Wifn bll fntrifs ibvf bffn visitfd, tifn tif
     * dirfdtory is dlosfd. Tif filf trff wblk tifn dontinufs bt tif nfxt
     * <fm>sibling</fm> of tif dirfdtory.
     *
     * <p> Tif strfbm is <i>wfbkly donsistfnt</i>. It dofs not frffzf tif
     * filf trff wiilf itfrbting, so it mby (or mby not) rfflfdt updbtfs to
     * tif filf trff tibt oddur bftfr rfturnfd from tiis mftiod.
     *
     * <p> By dffbult, symbolid links brf not butombtidblly followfd by tiis
     * mftiod. If tif {@dodf options} pbrbmftfr dontbins tif {@link
     * FilfVisitOption#FOLLOW_LINKS FOLLOW_LINKS} option tifn symbolid links brf
     * followfd. Wifn following links, bnd tif bttributfs of tif tbrgft dbnnot
     * bf rfbd, tifn tiis mftiod bttfmpts to gft tif {@dodf BbsidFilfAttributfs}
     * of tif link.
     *
     * <p> If tif {@dodf options} pbrbmftfr dontbins tif {@link
     * FilfVisitOption#FOLLOW_LINKS FOLLOW_LINKS} option tifn tif strfbm kffps
     * trbdk of dirfdtorifs visitfd so tibt dydlfs dbn bf dftfdtfd. A dydlf
     * brisfs wifn tifrf is bn fntry in b dirfdtory tibt is bn bndfstor of tif
     * dirfdtory. Cydlf dftfdtion is donf by rfdording tif {@link
     * jbvb.nio.filf.bttributf.BbsidFilfAttributfs#filfKfy filf-kfy} of dirfdtorifs,
     * or if filf kfys brf not bvbilbblf, by invoking tif {@link #isSbmfFilf
     * isSbmfFilf} mftiod to tfst if b dirfdtory is tif sbmf filf bs bn
     * bndfstor. Wifn b dydlf is dftfdtfd it is trfbtfd bs bn I/O frror witi
     * bn instbndf of {@link FilfSystfmLoopExdfption}.
     *
     * <p> Tif {@dodf mbxDfpti} pbrbmftfr is tif mbximum numbfr of lfvfls of
     * dirfdtorifs to visit. A vbluf of {@dodf 0} mfbns tibt only tif stbrting
     * filf is visitfd, unlfss dfnifd by tif sfdurity mbnbgfr. A vbluf of
     * {@link Intfgfr#MAX_VALUE MAX_VALUE} mby bf usfd to indidbtf tibt bll
     * lfvfls siould bf visitfd.
     *
     * <p> Wifn b sfdurity mbnbgfr is instbllfd bnd it dfnifs bddfss to b filf
     * (or dirfdtory), tifn it is ignorfd bnd not indludfd in tif strfbm.
     *
     * <p> Tif rfturnfd strfbm fndbpsulbtfs onf or morf {@link DirfdtoryStrfbm}s.
     * If timfly disposbl of filf systfm rfsourdfs is rfquirfd, tif
     * {@dodf try}-witi-rfsourdfs donstrudt siould bf usfd to fnsurf tibt tif
     * strfbm's {@link Strfbm#dlosf dlosf} mftiod is invokfd bftfr tif strfbm
     * opfrbtions brf domplftfd.  Opfrbting on b dlosfd strfbm will rfsult in bn
     * {@link jbvb.lbng.IllfgblStbtfExdfption}.
     *
     * <p> If bn {@link IOExdfption} is tirown wifn bddfssing tif dirfdtory
     * bftfr tiis mftiod ibs rfturnfd, it is wrbppfd in bn {@link
     * UndifdkfdIOExdfption} wiidi will bf tirown from tif mftiod tibt dbusfd
     * tif bddfss to tbkf plbdf.
     *
     * @pbrbm   stbrt
     *          tif stbrting filf
     * @pbrbm   mbxDfpti
     *          tif mbximum numbfr of dirfdtory lfvfls to visit
     * @pbrbm   options
     *          options to donfigurf tif trbvfrsbl
     *
     * @rfturn  tif {@link Strfbm} of {@link Pbti}
     *
     * @tirows  IllfgblArgumfntExdfption
     *          if tif {@dodf mbxDfpti} pbrbmftfr is nfgbtivf
     * @tirows  SfdurityExdfption
     *          If tif sfdurity mbnbgfr dfnifs bddfss to tif stbrting filf.
     *          In tif dbsf of tif dffbult providfr, tif {@link
     *          SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd} mftiod is invokfd
     *          to difdk rfbd bddfss to tif dirfdtory.
     * @tirows  IOExdfption
     *          if bn I/O frror is tirown wifn bddfssing tif stbrting filf.
     * @sindf   1.8
     */
    publid stbtid Strfbm<Pbti> wblk(Pbti stbrt,
                                    int mbxDfpti,
                                    FilfVisitOption... options)
        tirows IOExdfption
    {
        FilfTrffItfrbtor itfrbtor = nfw FilfTrffItfrbtor(stbrt, mbxDfpti, options);
        try {
            rfturn StrfbmSupport.strfbm(Splitfrbtors.splitfrbtorUnknownSizf(itfrbtor, Splitfrbtor.DISTINCT), fblsf)
                                .onClosf(itfrbtor::dlosf)
                                .mbp(fntry -> fntry.filf());
        } dbtdi (Error|RuntimfExdfption f) {
            itfrbtor.dlosf();
            tirow f;
        }
    }

    /**
     * Rfturn b {@dodf Strfbm} tibt is lbzily populbtfd witi {@dodf
     * Pbti} by wblking tif filf trff rootfd bt b givfn stbrting filf.  Tif
     * filf trff is trbvfrsfd <fm>dfpti-first</fm>, tif flfmfnts in tif strfbm
     * brf {@link Pbti} objfdts tibt brf obtbinfd bs if by {@link
     * Pbti#rfsolvf(Pbti) rfsolving} tif rflbtivf pbti bgbinst {@dodf stbrt}.
     *
     * <p> Tiis mftiod works bs if invoking it wfrf fquivblfnt to fvblubting tif
     * fxprfssion:
     * <blodkquotf><prf>
     * wblk(stbrt, Intfgfr.MAX_VALUE, options)
     * </prf></blodkquotf>
     * In otifr words, it visits bll lfvfls of tif filf trff.
     *
     * <p> Tif rfturnfd strfbm fndbpsulbtfs onf or morf {@link DirfdtoryStrfbm}s.
     * If timfly disposbl of filf systfm rfsourdfs is rfquirfd, tif
     * {@dodf try}-witi-rfsourdfs donstrudt siould bf usfd to fnsurf tibt tif
     * strfbm's {@link Strfbm#dlosf dlosf} mftiod is invokfd bftfr tif strfbm
     * opfrbtions brf domplftfd.  Opfrbting on b dlosfd strfbm will rfsult in bn
     * {@link jbvb.lbng.IllfgblStbtfExdfption}.
     *
     * @pbrbm   stbrt
     *          tif stbrting filf
     * @pbrbm   options
     *          options to donfigurf tif trbvfrsbl
     *
     * @rfturn  tif {@link Strfbm} of {@link Pbti}
     *
     * @tirows  SfdurityExdfption
     *          If tif sfdurity mbnbgfr dfnifs bddfss to tif stbrting filf.
     *          In tif dbsf of tif dffbult providfr, tif {@link
     *          SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd} mftiod is invokfd
     *          to difdk rfbd bddfss to tif dirfdtory.
     * @tirows  IOExdfption
     *          if bn I/O frror is tirown wifn bddfssing tif stbrting filf.
     *
     * @sff     #wblk(Pbti, int, FilfVisitOption...)
     * @sindf   1.8
     */
    publid stbtid Strfbm<Pbti> wblk(Pbti stbrt, FilfVisitOption... options) tirows IOExdfption {
        rfturn wblk(stbrt, Intfgfr.MAX_VALUE, options);
    }

    /**
     * Rfturn b {@dodf Strfbm} tibt is lbzily populbtfd witi {@dodf
     * Pbti} by sfbrdiing for filfs in b filf trff rootfd bt b givfn stbrting
     * filf.
     *
     * <p> Tiis mftiod wblks tif filf trff in fxbdtly tif mbnnfr spfdififd by
     * tif {@link #wblk wblk} mftiod. For fbdi filf fndountfrfd, tif givfn
     * {@link BiPrfdidbtf} is invokfd witi its {@link Pbti} bnd {@link
     * BbsidFilfAttributfs}. Tif {@dodf Pbti} objfdt is obtbinfd bs if by
     * {@link Pbti#rfsolvf(Pbti) rfsolving} tif rflbtivf pbti bgbinst {@dodf
     * stbrt} bnd is only indludfd in tif rfturnfd {@link Strfbm} if
     * tif {@dodf BiPrfdidbtf} rfturns truf. Compbrf to dblling {@link
     * jbvb.util.strfbm.Strfbm#filtfr filtfr} on tif {@dodf Strfbm}
     * rfturnfd by {@dodf wblk} mftiod, tiis mftiod mby bf morf fffidifnt by
     * bvoiding rfdundbnt rftrifvbl of tif {@dodf BbsidFilfAttributfs}.
     *
     * <p> Tif rfturnfd strfbm fndbpsulbtfs onf or morf {@link DirfdtoryStrfbm}s.
     * If timfly disposbl of filf systfm rfsourdfs is rfquirfd, tif
     * {@dodf try}-witi-rfsourdfs donstrudt siould bf usfd to fnsurf tibt tif
     * strfbm's {@link Strfbm#dlosf dlosf} mftiod is invokfd bftfr tif strfbm
     * opfrbtions brf domplftfd.  Opfrbting on b dlosfd strfbm will rfsult in bn
     * {@link jbvb.lbng.IllfgblStbtfExdfption}.
     *
     * <p> If bn {@link IOExdfption} is tirown wifn bddfssing tif dirfdtory
     * bftfr rfturnfd from tiis mftiod, it is wrbppfd in bn {@link
     * UndifdkfdIOExdfption} wiidi will bf tirown from tif mftiod tibt dbusfd
     * tif bddfss to tbkf plbdf.
     *
     * @pbrbm   stbrt
     *          tif stbrting filf
     * @pbrbm   mbxDfpti
     *          tif mbximum numbfr of dirfdtory lfvfls to sfbrdi
     * @pbrbm   mbtdifr
     *          tif fundtion usfd to dfdidf wiftifr b filf siould bf indludfd
     *          in tif rfturnfd strfbm
     * @pbrbm   options
     *          options to donfigurf tif trbvfrsbl
     *
     * @rfturn  tif {@link Strfbm} of {@link Pbti}
     *
     * @tirows  IllfgblArgumfntExdfption
     *          if tif {@dodf mbxDfpti} pbrbmftfr is nfgbtivf
     * @tirows  SfdurityExdfption
     *          If tif sfdurity mbnbgfr dfnifs bddfss to tif stbrting filf.
     *          In tif dbsf of tif dffbult providfr, tif {@link
     *          SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd} mftiod is invokfd
     *          to difdk rfbd bddfss to tif dirfdtory.
     * @tirows  IOExdfption
     *          if bn I/O frror is tirown wifn bddfssing tif stbrting filf.
     *
     * @sff     #wblk(Pbti, int, FilfVisitOption...)
     * @sindf   1.8
     */
    publid stbtid Strfbm<Pbti> find(Pbti stbrt,
                                    int mbxDfpti,
                                    BiPrfdidbtf<Pbti, BbsidFilfAttributfs> mbtdifr,
                                    FilfVisitOption... options)
        tirows IOExdfption
    {
        FilfTrffItfrbtor itfrbtor = nfw FilfTrffItfrbtor(stbrt, mbxDfpti, options);
        try {
            rfturn StrfbmSupport.strfbm(Splitfrbtors.splitfrbtorUnknownSizf(itfrbtor, Splitfrbtor.DISTINCT), fblsf)
                                .onClosf(itfrbtor::dlosf)
                                .filtfr(fntry -> mbtdifr.tfst(fntry.filf(), fntry.bttributfs()))
                                .mbp(fntry -> fntry.filf());
        } dbtdi (Error|RuntimfExdfption f) {
            itfrbtor.dlosf();
            tirow f;
        }
    }

    /**
     * Rfbd bll linfs from b filf bs b {@dodf Strfbm}. Unlikf {@link
     * #rfbdAllLinfs(Pbti, Cibrsft) rfbdAllLinfs}, tiis mftiod dofs not rfbd
     * bll linfs into b {@dodf List}, but instfbd populbtfs lbzily bs tif strfbm
     * is donsumfd.
     *
     * <p> Bytfs from tif filf brf dfdodfd into dibrbdtfrs using tif spfdififd
     * dibrsft bnd tif sbmf linf tfrminbtors bs spfdififd by {@dodf
     * rfbdAllLinfs} brf supportfd.
     *
     * <p> Aftfr tiis mftiod rfturns, tifn bny subsfqufnt I/O fxdfption tibt
     * oddurs wiilf rfbding from tif filf or wifn b mblformfd or unmbppbblf bytf
     * sfqufndf is rfbd, is wrbppfd in bn {@link UndifdkfdIOExdfption} tibt will
     * bf tirown from tif
     * {@link jbvb.util.strfbm.Strfbm} mftiod tibt dbusfd tif rfbd to tbkf
     * plbdf. In dbsf bn {@dodf IOExdfption} is tirown wifn dlosing tif filf,
     * it is blso wrbppfd bs bn {@dodf UndifdkfdIOExdfption}.
     *
     * <p> Tif rfturnfd strfbm fndbpsulbtfs b {@link Rfbdfr}.  If timfly
     * disposbl of filf systfm rfsourdfs is rfquirfd, tif try-witi-rfsourdfs
     * donstrudt siould bf usfd to fnsurf tibt tif strfbm's
     * {@link Strfbm#dlosf dlosf} mftiod is invokfd bftfr tif strfbm opfrbtions
     * brf domplftfd.
     *
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf
     * @pbrbm   ds
     *          tif dibrsft to usf for dfdoding
     *
     * @rfturn  tif linfs from tif filf bs b {@dodf Strfbm}
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs opfning tif filf
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd}
     *          mftiod is invokfd to difdk rfbd bddfss to tif filf.
     *
     * @sff     #rfbdAllLinfs(Pbti, Cibrsft)
     * @sff     #nfwBufffrfdRfbdfr(Pbti, Cibrsft)
     * @sff     jbvb.io.BufffrfdRfbdfr#linfs()
     * @sindf   1.8
     */
    publid stbtid Strfbm<String> linfs(Pbti pbti, Cibrsft ds) tirows IOExdfption {
        BufffrfdRfbdfr br = Filfs.nfwBufffrfdRfbdfr(pbti, ds);
        try {
            rfturn br.linfs().onClosf(bsUndifdkfdRunnbblf(br));
        } dbtdi (Error|RuntimfExdfption f) {
            try {
                br.dlosf();
            } dbtdi (IOExdfption fx) {
                try {
                    f.bddSupprfssfd(fx);
                } dbtdi (Tirowbblf ignorf) {}
            }
            tirow f;
        }
    }

    /**
     * Rfbd bll linfs from b filf bs b {@dodf Strfbm}. Bytfs from tif filf brf
     * dfdodfd into dibrbdtfrs using tif {@link StbndbrdCibrsfts#UTF_8 UTF-8}
     * {@link Cibrsft dibrsft}.
     *
     * <p> Tiis mftiod works bs if invoking it wfrf fquivblfnt to fvblubting tif
     * fxprfssion:
     * <prf>{@dodf
     * Filfs.linfs(pbti, StbndbrdCibrsfts.UTF_8)
     * }</prf>
     *
     * @pbrbm   pbti
     *          tif pbti to tif filf
     *
     * @rfturn  tif linfs from tif filf bs b {@dodf Strfbm}
     *
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs opfning tif filf
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd}
     *          mftiod is invokfd to difdk rfbd bddfss to tif filf.
     *
     * @sindf 1.8
     */
    publid stbtid Strfbm<String> linfs(Pbti pbti) tirows IOExdfption {
        rfturn linfs(pbti, StbndbrdCibrsfts.UTF_8);
    }
}
