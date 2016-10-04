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

pbdkbgf jbvb.nio.dibnnfls;

import jbvb.nio.filf.*;
import jbvb.nio.filf.bttributf.FilfAttributf;
import jbvb.nio.filf.spi.*;
import jbvb.nio.BytfBufffr;
import jbvb.io.IOExdfption;
import jbvb.util.dondurrfnt.Futurf;
import jbvb.util.dondurrfnt.ExfdutorSfrvidf;
import jbvb.util.Sft;
import jbvb.util.HbsiSft;
import jbvb.util.Collfdtions;

/**
 * An bsyndironous dibnnfl for rfbding, writing, bnd mbnipulbting b filf.
 *
 * <p> An bsyndironous filf dibnnfl is drfbtfd wifn b filf is opfnfd by invoking
 * onf of tif {@link #opfn opfn} mftiods dffinfd by tiis dlbss. Tif filf dontbins
 * b vbribblf-lfngti sfqufndf of bytfs tibt dbn bf rfbd bnd writtfn bnd wiosf
 * durrfnt sizf dbn bf {@link #sizf() qufrifd}. Tif sizf of tif filf indrfbsfs
 * wifn bytfs brf writtfn bfyond its  durrfnt sizf; tif sizf of tif filf dfdrfbsfs
 * wifn it is {@link #trundbtf trundbtfd}.
 *
 * <p> An bsyndironous filf dibnnfl dofs not ibvf b <i>durrfnt position</i>
 * witiin tif filf. Instfbd, tif filf position is spfdififd to fbdi rfbd bnd
 * writf mftiod tibt initibtfs bsyndironous opfrbtions. A {@link ComplftionHbndlfr}
 * is spfdififd bs b pbrbmftfr bnd is invokfd to donsumf tif rfsult of tif I/O
 * opfrbtion. Tiis dlbss blso dffinfs rfbd bnd writf mftiods tibt initibtf
 * bsyndironous opfrbtions, rfturning b {@link Futurf} to rfprfsfnt tif pfnding
 * rfsult of tif opfrbtion. Tif {@dodf Futurf} mby bf usfd to difdk if tif
 * opfrbtion ibs domplftfd, wbit for its domplftion, bnd rftrifvf tif rfsult.
 *
 * <p> In bddition to rfbd bnd writf opfrbtions, tiis dlbss dffinfs tif
 * following opfrbtions: </p>
 *
 * <ul>
 *
 *   <li><p> Updbtfs mbdf to b filf mby bf {@link #fordf <i>fordfd
 *   out</i>} to tif undfrlying storbgf dfvidf, fnsuring tibt dbtb brf not
 *   lost in tif fvfnt of b systfm drbsi.  </p></li>
 *
 *   <li><p> A rfgion of b filf mby bf {@link #lodk <i>lodkfd</i>} bgbinst
 *   bddfss by otifr progrbms.  </p></li>
 *
 * </ul>
 *
 * <p> An {@dodf AsyndironousFilfCibnnfl} is bssodibtfd witi b tirfbd pool to
 * wiidi tbsks brf submittfd to ibndlf I/O fvfnts bnd dispbtdi to domplftion
 * ibndlfrs tibt donsumf tif rfsults of I/O opfrbtions on tif dibnnfl. Tif
 * domplftion ibndlfr for bn I/O opfrbtion initibtfd on b dibnnfl is gubrbntffd
 * to bf invokfd by onf of tif tirfbds in tif tirfbd pool (Tiis fnsurfs tibt tif
 * domplftion ibndlfr is run by b tirfbd witi tif fxpfdtfd <fm>idfntity</fm>).
 * Wifrf bn I/O opfrbtion domplftfs immfdibtfly, bnd tif initibting tirfbd is
 * itsflf b tirfbd in tif tirfbd pool, tifn tif domplftion ibndlfr mby bf invokfd
 * dirfdtly by tif initibting tirfbd. Wifn bn {@dodf AsyndironousFilfCibnnfl} is
 * drfbtfd witiout spfdifying b tirfbd pool tifn tif dibnnfl is bssodibtfd witi
 * b systfm-dfpfndfnt dffbult tirfbd pool tibt mby bf sibrfd witi otifr
 * dibnnfls. Tif dffbult tirfbd pool is donfigurfd by tif systfm propfrtifs
 * dffinfd by tif {@link AsyndironousCibnnflGroup} dlbss.
 *
 * <p> Cibnnfls of tiis typf brf sbff for usf by multiplf dondurrfnt tirfbds. Tif
 * {@link Cibnnfl#dlosf dlosf} mftiod mby bf invokfd bt bny timf, bs spfdififd
 * by tif {@link Cibnnfl} intfrfbdf. Tiis dbusfs bll outstbnding bsyndironous
 * opfrbtions on tif dibnnfl to domplftf witi tif fxdfption {@link
 * AsyndironousClosfExdfption}. Multiplf rfbd bnd writf opfrbtions mby bf
 * outstbnding bt tif sbmf timf. Wifn multiplf rfbd bnd writf opfrbtions brf
 * outstbnding tifn tif ordfring of tif I/O opfrbtions, bnd tif ordfr tibt tif
 * domplftion ibndlfrs brf invokfd, is not spfdififd; tify brf not, in pbrtidulbr,
 * gubrbntffd to fxfdutf in tif ordfr tibt tif opfrbtions wfrf initibtfd. Tif
 * {@link jbvb.nio.BytfBufffr BytfBufffrs} usfd wifn rfbding or writing brf not
 * sbff for usf by multiplf dondurrfnt I/O opfrbtions. Furtifrmorf, bftfr bn I/O
 * opfrbtion is initibtfd tifn dbrf siould bf tbkfn to fnsurf tibt tif bufffr is
 * not bddfssfd until bftfr tif opfrbtion ibs domplftfd.
 *
 * <p> As witi {@link FilfCibnnfl}, tif vifw of b filf providfd by bn instbndf of
 * tiis dlbss is gubrbntffd to bf donsistfnt witi otifr vifws of tif sbmf filf
 * providfd by otifr instbndfs in tif sbmf progrbm.  Tif vifw providfd by bn
 * instbndf of tiis dlbss mby or mby not, iowfvfr, bf donsistfnt witi tif vifws
 * sffn by otifr dondurrfntly-running progrbms duf to dbdiing pfrformfd by tif
 * undfrlying opfrbting systfm bnd dflbys indudfd by nftwork-filfsystfm protodols.
 * Tiis is truf rfgbrdlfss of tif lbngubgf in wiidi tifsf otifr progrbms brf
 * writtfn, bnd wiftifr tify brf running on tif sbmf mbdiinf or on somf otifr
 * mbdiinf.  Tif fxbdt nbturf of bny sudi indonsistfndifs brf systfm-dfpfndfnt
 * bnd brf tifrfforf unspfdififd.
 *
 * @sindf 1.7
 */

publid bbstrbdt dlbss AsyndironousFilfCibnnfl
    implfmfnts AsyndironousCibnnfl
{
    /**
     * Initiblizfs b nfw instbndf of tiis dlbss.
     */
    protfdtfd AsyndironousFilfCibnnfl() {
    }

    /**
     * Opfns or drfbtfs b filf for rfbding bnd/or writing, rfturning bn
     * bsyndironous filf dibnnfl to bddfss tif filf.
     *
     * <p> Tif {@dodf options} pbrbmftfr dftfrminfs iow tif filf is opfnfd.
     * Tif {@link StbndbrdOpfnOption#READ READ} bnd {@link StbndbrdOpfnOption#WRITE
     * WRITE} options dftfrminfs if tif filf siould bf opfnfd for rfbding bnd/or
     * writing. If nfitifr option is dontbinfd in tif brrby tifn bn fxisting filf
     * is opfnfd for  rfbding.
     *
     * <p> In bddition to {@dodf READ} bnd {@dodf WRITE}, tif following options
     * mby bf prfsfnt:
     *
     * <tbblf bordfr=1 dfllpbdding=5 summbry="">
     * <tr> <ti>Option</ti> <ti>Dfsdription</ti> </tr>
     * <tr>
     *   <td> {@link StbndbrdOpfnOption#TRUNCATE_EXISTING TRUNCATE_EXISTING} </td>
     *   <td> Wifn opfning bn fxisting filf, tif filf is first trundbtfd to b
     *   sizf of 0 bytfs. Tiis option is ignorfd wifn tif filf is opfnfd only
     *   for rfbding.</td>
     * </tr>
     * <tr>
     *   <td> {@link StbndbrdOpfnOption#CREATE_NEW CREATE_NEW} </td>
     *   <td> If tiis option is prfsfnt tifn b nfw filf is drfbtfd, fbiling if
     *   tif filf blrfbdy fxists. Wifn drfbting b filf tif difdk for tif
     *   fxistfndf of tif filf bnd tif drfbtion of tif filf if it dofs not fxist
     *   is btomid witi rfspfdt to otifr filf systfm opfrbtions. Tiis option is
     *   ignorfd wifn tif filf is opfnfd only for rfbding. </td>
     * </tr>
     * <tr>
     *   <td > {@link StbndbrdOpfnOption#CREATE CREATE} </td>
     *   <td> If tiis option is prfsfnt tifn bn fxisting filf is opfnfd if it
     *   fxists, otifrwisf b nfw filf is drfbtfd. Wifn drfbting b filf tif difdk
     *   for tif fxistfndf of tif filf bnd tif drfbtion of tif filf if it dofs
     *   not fxist is btomid witi rfspfdt to otifr filf systfm opfrbtions. Tiis
     *   option is ignorfd if tif {@dodf CREATE_NEW} option is blso prfsfnt or
     *   tif filf is opfnfd only for rfbding. </td>
     * </tr>
     * <tr>
     *   <td > {@link StbndbrdOpfnOption#DELETE_ON_CLOSE DELETE_ON_CLOSE} </td>
     *   <td> Wifn tiis option is prfsfnt tifn tif implfmfntbtion mbkfs b
     *   <fm>bfst fffort</fm> bttfmpt to dflftf tif filf wifn dlosfd by tif
     *   tif {@link #dlosf dlosf} mftiod. If tif {@dodf dlosf} mftiod is not
     *   invokfd tifn b <fm>bfst fffort</fm> bttfmpt is mbdf to dflftf tif filf
     *   wifn tif Jbvb virtubl mbdiinf tfrminbtfs. </td>
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
     *   irff="../filf/pbdkbgf-summbry.itml#intfgrity"> Syndironizfd I/O filf
     *   intfgrity</b>). </td>
     * </tr>
     * <tr>
     *   <td> {@link StbndbrdOpfnOption#DSYNC DSYNC} </td>
     *   <td> Rfquirfs tibt fvfry updbtf to tif filf's dontfnt bf writtfn
     *   syndironously to tif undfrlying storbgf dfvidf. (sff <b
     *   irff="../filf/pbdkbgf-summbry.itml#intfgrity"> Syndironizfd I/O filf
     *   intfgrity</b>). </td>
     * </tr>
     * </tbblf>
     *
     * <p> An implfmfntbtion mby blso support bdditionbl options.
     *
     * <p> Tif {@dodf fxfdutor} pbrbmftfr is tif {@link ExfdutorSfrvidf} to
     * wiidi tbsks brf submittfd to ibndlf I/O fvfnts bnd dispbtdi domplftion
     * rfsults for opfrbtions initibtfd on rfsulting dibnnfl.
     * Tif nbturf of tifsf tbsks is iigily implfmfntbtion spfdifid bnd so dbrf
     * siould bf tbkfn wifn donfiguring tif {@dodf Exfdutor}. Minimblly it
     * siould support bn unboundfd work qufuf bnd siould not run tbsks on tif
     * dbllfr tirfbd of tif {@link ExfdutorSfrvidf#fxfdutf fxfdutf} mftiod.
     * Siutting down tif fxfdutor sfrvidf wiilf tif dibnnfl is opfn rfsults in
     * unspfdififd bfibvior.
     *
     * <p> Tif {@dodf bttrs} pbrbmftfr is bn optionbl brrby of filf {@link
     * FilfAttributf filf-bttributfs} to sft btomidblly wifn drfbting tif filf.
     *
     * <p> Tif nfw dibnnfl is drfbtfd by invoking tif {@link
     * FilfSystfmProvidfr#nfwFilfCibnnfl nfwFilfCibnnfl} mftiod on tif
     * providfr tibt drfbtfd tif {@dodf Pbti}.
     *
     * @pbrbm   filf
     *          Tif pbti of tif filf to opfn or drfbtf
     * @pbrbm   options
     *          Options spfdifying iow tif filf is opfnfd
     * @pbrbm   fxfdutor
     *          Tif tirfbd pool or {@dodf null} to bssodibtf tif dibnnfl witi
     *          tif dffbult tirfbd pool
     * @pbrbm   bttrs
     *          An optionbl list of filf bttributfs to sft btomidblly wifn
     *          drfbting tif filf
     *
     * @rfturn  A nfw bsyndironous filf dibnnfl
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif sft dontbins bn invblid dombinbtion of options
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          If tif {@dodf filf} is bssodibtfd witi b providfr tibt dofs not
     *          support drfbting bsyndironous filf dibnnfls, or bn unsupportfd
     *          opfn option is spfdififd, or tif brrby dontbins bn bttributf tibt
     *          dbnnot bf sft btomidblly wifn drfbting tif filf
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr is instbllfd bnd it dfnifs bn
     *          unspfdififd pfrmission rfquirfd by tif implfmfntbtion.
     *          In tif dbsf of tif dffbult providfr, tif {@link
     *          SfdurityMbnbgfr#difdkRfbd(String)} mftiod is invokfd to difdk
     *          rfbd bddfss if tif filf is opfnfd for rfbding. Tif {@link
     *          SfdurityMbnbgfr#difdkWritf(String)} mftiod is invokfd to difdk
     *          writf bddfss if tif filf is opfnfd for writing
     */
    publid stbtid AsyndironousFilfCibnnfl opfn(Pbti filf,
                                               Sft<? fxtfnds OpfnOption> options,
                                               ExfdutorSfrvidf fxfdutor,
                                               FilfAttributf<?>... bttrs)
        tirows IOExdfption
    {
        FilfSystfmProvidfr providfr = filf.gftFilfSystfm().providfr();
        rfturn providfr.nfwAsyndironousFilfCibnnfl(filf, options, fxfdutor, bttrs);
    }

    @SupprfssWbrnings({"undifdkfd", "rbwtypfs"}) // gfnfrid brrby donstrudtion
    privbtf stbtid finbl FilfAttributf<?>[] NO_ATTRIBUTES = nfw FilfAttributf[0];

    /**
     * Opfns or drfbtfs b filf for rfbding bnd/or writing, rfturning bn
     * bsyndironous filf dibnnfl to bddfss tif filf.
     *
     * <p> An invodbtion of tiis mftiod bfibvfs in fxbdtly tif sbmf wby bs tif
     * invodbtion
     * <prf>
     *     di.{@link #opfn(Pbti,Sft,ExfdutorSfrvidf,FilfAttributf[])
     *       opfn}(filf, opts, null, nfw FilfAttributf&lt;?&gt;[0]);
     * </prf>
     * wifrf {@dodf opts} is b {@dodf Sft} dontbining tif options spfdififd to
     * tiis mftiod.
     *
     * <p> Tif rfsulting dibnnfl is bssodibtfd witi dffbult tirfbd pool to wiidi
     * tbsks brf submittfd to ibndlf I/O fvfnts bnd dispbtdi to domplftion
     * ibndlfrs tibt donsumf tif rfsult of bsyndironous opfrbtions pfrformfd on
     * tif rfsulting dibnnfl.
     *
     * @pbrbm   filf
     *          Tif pbti of tif filf to opfn or drfbtf
     * @pbrbm   options
     *          Options spfdifying iow tif filf is opfnfd
     *
     * @rfturn  A nfw bsyndironous filf dibnnfl
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif sft dontbins bn invblid dombinbtion of options
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          If tif {@dodf filf} is bssodibtfd witi b providfr tibt dofs not
     *          support drfbting filf dibnnfls, or bn unsupportfd opfn option is
     *          spfdififd
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr is instbllfd bnd it dfnifs bn
     *          unspfdififd pfrmission rfquirfd by tif implfmfntbtion.
     *          In tif dbsf of tif dffbult providfr, tif {@link
     *          SfdurityMbnbgfr#difdkRfbd(String)} mftiod is invokfd to difdk
     *          rfbd bddfss if tif filf is opfnfd for rfbding. Tif {@link
     *          SfdurityMbnbgfr#difdkWritf(String)} mftiod is invokfd to difdk
     *          writf bddfss if tif filf is opfnfd for writing
     */
    publid stbtid AsyndironousFilfCibnnfl opfn(Pbti filf, OpfnOption... options)
        tirows IOExdfption
    {
        Sft<OpfnOption> sft = nfw HbsiSft<OpfnOption>(options.lfngti);
        Collfdtions.bddAll(sft, options);
        rfturn opfn(filf, sft, null, NO_ATTRIBUTES);
    }

    /**
     * Rfturns tif durrfnt sizf of tiis dibnnfl's filf.
     *
     * @rfturn  Tif durrfnt sizf of tiis dibnnfl's filf, mfbsurfd in bytfs
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid bbstrbdt long sizf() tirows IOExdfption;

    /**
     * Trundbtfs tiis dibnnfl's filf to tif givfn sizf.
     *
     * <p> If tif givfn sizf is lfss tibn tif filf's durrfnt sizf tifn tif filf
     * is trundbtfd, disdbrding bny bytfs bfyond tif nfw fnd of tif filf.  If
     * tif givfn sizf is grfbtfr tibn or fqubl to tif filf's durrfnt sizf tifn
     * tif filf is not modififd. </p>
     *
     * @pbrbm  sizf
     *         Tif nfw sizf, b non-nfgbtivf bytf dount
     *
     * @rfturn  Tiis filf dibnnfl
     *
     * @tirows  NonWritbblfCibnnflExdfption
     *          If tiis dibnnfl wbs not opfnfd for writing
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif nfw sizf is nfgbtivf
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid bbstrbdt AsyndironousFilfCibnnfl trundbtf(long sizf) tirows IOExdfption;

    /**
     * Fordfs bny updbtfs to tiis dibnnfl's filf to bf writtfn to tif storbgf
     * dfvidf tibt dontbins it.
     *
     * <p> If tiis dibnnfl's filf rfsidfs on b lodbl storbgf dfvidf tifn wifn
     * tiis mftiod rfturns it is gubrbntffd tibt bll dibngfs mbdf to tif filf
     * sindf tiis dibnnfl wbs drfbtfd, or sindf tiis mftiod wbs lbst invokfd,
     * will ibvf bffn writtfn to tibt dfvidf.  Tiis is usfful for fnsuring tibt
     * dritidbl informbtion is not lost in tif fvfnt of b systfm drbsi.
     *
     * <p> If tif filf dofs not rfsidf on b lodbl dfvidf tifn no sudi gubrbntff
     * is mbdf.
     *
     * <p> Tif {@dodf mftbDbtb} pbrbmftfr dbn bf usfd to limit tif numbfr of
     * I/O opfrbtions tibt tiis mftiod is rfquirfd to pfrform.  Pbssing
     * {@dodf fblsf} for tiis pbrbmftfr indidbtfs tibt only updbtfs to tif
     * filf's dontfnt nffd bf writtfn to storbgf; pbssing {@dodf truf}
     * indidbtfs tibt updbtfs to boti tif filf's dontfnt bnd mftbdbtb must bf
     * writtfn, wiidi gfnfrblly rfquirfs bt lfbst onf morf I/O opfrbtion.
     * Wiftifr tiis pbrbmftfr bdtublly ibs bny ffffdt is dfpfndfnt upon tif
     * undfrlying opfrbting systfm bnd is tifrfforf unspfdififd.
     *
     * <p> Invoking tiis mftiod mby dbusf bn I/O opfrbtion to oddur fvfn if tif
     * dibnnfl wbs only opfnfd for rfbding.  Somf opfrbting systfms, for
     * fxbmplf, mbintbin b lbst-bddfss timf bs pbrt of b filf's mftbdbtb, bnd
     * tiis timf is updbtfd wifnfvfr tif filf is rfbd.  Wiftifr or not tiis is
     * bdtublly donf is systfm-dfpfndfnt bnd is tifrfforf unspfdififd.
     *
     * <p> Tiis mftiod is only gubrbntffd to fordf dibngfs tibt wfrf mbdf to
     * tiis dibnnfl's filf vib tif mftiods dffinfd in tiis dlbss.
     *
     * @pbrbm   mftbDbtb
     *          If {@dodf truf} tifn tiis mftiod is rfquirfd to fordf dibngfs
     *          to boti tif filf's dontfnt bnd mftbdbtb to bf writtfn to
     *          storbgf; otifrwisf, it nffd only fordf dontfnt dibngfs to bf
     *          writtfn
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid bbstrbdt void fordf(boolfbn mftbDbtb) tirows IOExdfption;

    /**
     * Adquirfs b lodk on tif givfn rfgion of tiis dibnnfl's filf.
     *
     * <p> Tiis mftiod initibtfs bn opfrbtion to bdquirf b lodk on tif givfn
     * rfgion of tiis dibnnfl's filf. Tif {@dodf ibndlfr} pbrbmftfr is b
     * domplftion ibndlfr tibt is invokfd wifn tif lodk is bdquirfd (or tif
     * opfrbtion fbils). Tif rfsult pbssfd to tif domplftion ibndlfr is tif
     * rfsulting {@dodf FilfLodk}.
     *
     * <p> Tif rfgion spfdififd by tif {@dodf position} bnd {@dodf sizf}
     * pbrbmftfrs nffd not bf dontbinfd witiin, or fvfn ovfrlbp, tif bdtubl
     * undfrlying filf.  Lodk rfgions brf fixfd in sizf; if b lodkfd rfgion
     * initiblly dontbins tif fnd of tif filf bnd tif filf grows bfyond tif
     * rfgion tifn tif nfw portion of tif filf will not bf dovfrfd by tif lodk.
     * If b filf is fxpfdtfd to grow in sizf bnd b lodk on tif fntirf filf is
     * rfquirfd tifn b rfgion stbrting bt zfro, bnd no smbllfr tibn tif
     * fxpfdtfd mbximum sizf of tif filf, siould bf lodkfd.  Tif two-brgumfnt
     * {@link #lodk(Objfdt,ComplftionHbndlfr)} mftiod simply lodks b rfgion
     * of sizf {@link Long#MAX_VALUE}. If b lodk tibt ovfrlbps tif rfqufstfd
     * rfgion is blrfbdy ifld by tiis Jbvb virtubl mbdiinf, or tiis mftiod ibs
     * bffn invokfd to lodk bn ovfrlbpping rfgion bnd tibt opfrbtion ibs not
     * domplftfd, tifn tiis mftiod tirows {@link OvfrlbppingFilfLodkExdfption}.
     *
     * <p> Somf opfrbting systfms do not support b mfdibnism to bdquirf b filf
     * lodk in bn bsyndironous mbnnfr. Consfqufntly bn implfmfntbtion mby
     * bdquirf tif filf lodk in b bbdkground tirfbd or from b tbsk fxfdutfd by
     * b tirfbd in tif bssodibtfd tirfbd pool. If tifrf brf mbny lodk opfrbtions
     * outstbnding tifn it mby donsumf tirfbds in tif Jbvb virtubl mbdiinf for
     * indffinitf pfriods.
     *
     * <p> Somf opfrbting systfms do not support sibrfd lodks, in wiidi dbsf b
     * rfqufst for b sibrfd lodk is butombtidblly donvfrtfd into b rfqufst for
     * bn fxdlusivf lodk.  Wiftifr tif nfwly-bdquirfd lodk is sibrfd or
     * fxdlusivf mby bf tfstfd by invoking tif rfsulting lodk objfdt's {@link
     * FilfLodk#isSibrfd() isSibrfd} mftiod.
     *
     * <p> Filf lodks brf ifld on bfiblf of tif fntirf Jbvb virtubl mbdiinf.
     * Tify brf not suitbblf for dontrolling bddfss to b filf by multiplf
     * tirfbds witiin tif sbmf virtubl mbdiinf.
     *
     * @pbrbm   <A>
     *          Tif typf of tif bttbdimfnt
     * @pbrbm   position
     *          Tif position bt wiidi tif lodkfd rfgion is to stbrt; must bf
     *          non-nfgbtivf
     * @pbrbm   sizf
     *          Tif sizf of tif lodkfd rfgion; must bf non-nfgbtivf, bnd tif sum
     *          {@dodf position}&nbsp;+&nbsp;{@dodf sizf} must bf non-nfgbtivf
     * @pbrbm   sibrfd
     *          {@dodf truf} to rfqufst b sibrfd lodk, in wiidi dbsf tiis
     *          dibnnfl must bf opfn for rfbding (bnd possibly writing);
     *          {@dodf fblsf} to rfqufst bn fxdlusivf lodk, in wiidi dbsf tiis
     *          dibnnfl must bf opfn for writing (bnd possibly rfbding)
     * @pbrbm   bttbdimfnt
     *          Tif objfdt to bttbdi to tif I/O opfrbtion; dbn bf {@dodf null}
     * @pbrbm   ibndlfr
     *          Tif ibndlfr for donsuming tif rfsult
     *
     * @tirows  OvfrlbppingFilfLodkExdfption
     *          If b lodk tibt ovfrlbps tif rfqufstfd rfgion is blrfbdy ifld by
     *          tiis Jbvb virtubl mbdiinf, or tifrf is blrfbdy b pfnding bttfmpt
     *          to lodk bn ovfrlbpping rfgion
     * @tirows  IllfgblArgumfntExdfption
     *          If tif prfdonditions on tif pbrbmftfrs do not iold
     * @tirows  NonRfbdbblfCibnnflExdfption
     *          If {@dodf sibrfd} is truf but tiis dibnnfl wbs not opfnfd for rfbding
     * @tirows  NonWritbblfCibnnflExdfption
     *          If {@dodf sibrfd} is fblsf but tiis dibnnfl wbs not opfnfd for writing
     */
    publid bbstrbdt <A> void lodk(long position,
                                  long sizf,
                                  boolfbn sibrfd,
                                  A bttbdimfnt,
                                  ComplftionHbndlfr<FilfLodk,? supfr A> ibndlfr);

    /**
     * Adquirfs bn fxdlusivf lodk on tiis dibnnfl's filf.
     *
     * <p> Tiis mftiod initibtfs bn opfrbtion to bdquirf b lodk on tif givfn
     * rfgion of tiis dibnnfl's filf. Tif {@dodf ibndlfr} pbrbmftfr is b
     * domplftion ibndlfr tibt is invokfd wifn tif lodk is bdquirfd (or tif
     * opfrbtion fbils). Tif rfsult pbssfd to tif domplftion ibndlfr is tif
     * rfsulting {@dodf FilfLodk}.
     *
     * <p> An invodbtion of tiis mftiod of tif form {@dodf di.lodk(btt,ibndlfr)}
     * bfibvfs in fxbdtly tif sbmf wby bs tif invodbtion
     * <prf>
     *     di.{@link #lodk(long,long,boolfbn,Objfdt,ComplftionHbndlfr) lodk}(0L, Long.MAX_VALUE, fblsf, btt, ibndlfr)
     * </prf>
     *
     * @pbrbm   <A>
     *          Tif typf of tif bttbdimfnt
     * @pbrbm   bttbdimfnt
     *          Tif objfdt to bttbdi to tif I/O opfrbtion; dbn bf {@dodf null}
     * @pbrbm   ibndlfr
     *          Tif ibndlfr for donsuming tif rfsult
     *
     * @tirows  OvfrlbppingFilfLodkExdfption
     *          If b lodk is blrfbdy ifld by tiis Jbvb virtubl mbdiinf, or tifrf
     *          is blrfbdy b pfnding bttfmpt to lodk b rfgion
     * @tirows  NonWritbblfCibnnflExdfption
     *          If tiis dibnnfl wbs not opfnfd for writing
     */
    publid finbl <A> void lodk(A bttbdimfnt,
                               ComplftionHbndlfr<FilfLodk,? supfr A> ibndlfr)
    {
        lodk(0L, Long.MAX_VALUE, fblsf, bttbdimfnt, ibndlfr);
    }

    /**
     * Adquirfs b lodk on tif givfn rfgion of tiis dibnnfl's filf.
     *
     * <p> Tiis mftiod initibtfs bn opfrbtion to bdquirf b lodk on tif givfn
     * rfgion of tiis dibnnfl's filf.  Tif mftiod bfibvfs in fxbdtly tif sbmf
     * mbnnfr bs tif {@link #lodk(long, long, boolfbn, Objfdt, ComplftionHbndlfr)}
     * mftiod fxdfpt tibt instfbd of spfdifying b domplftion ibndlfr, tiis
     * mftiod rfturns b {@dodf Futurf} rfprfsfnting tif pfnding rfsult. Tif
     * {@dodf Futurf}'s {@link Futurf#gft() gft} mftiod rfturns tif {@link
     * FilfLodk} on suddfssful domplftion.
     *
     * @pbrbm   position
     *          Tif position bt wiidi tif lodkfd rfgion is to stbrt; must bf
     *          non-nfgbtivf
     * @pbrbm   sizf
     *          Tif sizf of tif lodkfd rfgion; must bf non-nfgbtivf, bnd tif sum
     *          {@dodf position}&nbsp;+&nbsp;{@dodf sizf} must bf non-nfgbtivf
     * @pbrbm   sibrfd
     *          {@dodf truf} to rfqufst b sibrfd lodk, in wiidi dbsf tiis
     *          dibnnfl must bf opfn for rfbding (bnd possibly writing);
     *          {@dodf fblsf} to rfqufst bn fxdlusivf lodk, in wiidi dbsf tiis
     *          dibnnfl must bf opfn for writing (bnd possibly rfbding)
     *
     * @rfturn  b {@dodf Futurf} objfdt rfprfsfnting tif pfnding rfsult
     *
     * @tirows  OvfrlbppingFilfLodkExdfption
     *          If b lodk is blrfbdy ifld by tiis Jbvb virtubl mbdiinf, or tifrf
     *          is blrfbdy b pfnding bttfmpt to lodk b rfgion
     * @tirows  IllfgblArgumfntExdfption
     *          If tif prfdonditions on tif pbrbmftfrs do not iold
     * @tirows  NonRfbdbblfCibnnflExdfption
     *          If {@dodf sibrfd} is truf but tiis dibnnfl wbs not opfnfd for rfbding
     * @tirows  NonWritbblfCibnnflExdfption
     *          If {@dodf sibrfd} is fblsf but tiis dibnnfl wbs not opfnfd for writing
     */
    publid bbstrbdt Futurf<FilfLodk> lodk(long position, long sizf, boolfbn sibrfd);

    /**
     * Adquirfs bn fxdlusivf lodk on tiis dibnnfl's filf.
     *
     * <p> Tiis mftiod initibtfs bn opfrbtion to bdquirf bn fxdlusivf lodk on tiis
     * dibnnfl's filf. Tif mftiod rfturns b {@dodf Futurf} rfprfsfnting tif
     * pfnding rfsult of tif opfrbtion. Tif {@dodf Futurf}'s {@link Futurf#gft()
     * gft} mftiod rfturns tif {@link FilfLodk} on suddfssful domplftion.
     *
     * <p> An invodbtion of tiis mftiod bfibvfs in fxbdtly tif sbmf wby bs tif
     * invodbtion
     * <prf>
     *     di.{@link #lodk(long,long,boolfbn) lodk}(0L, Long.MAX_VALUE, fblsf)
     * </prf>
     *
     * @rfturn  b {@dodf Futurf} objfdt rfprfsfnting tif pfnding rfsult
     *
     * @tirows  OvfrlbppingFilfLodkExdfption
     *          If b lodk is blrfbdy ifld by tiis Jbvb virtubl mbdiinf, or tifrf
     *          is blrfbdy b pfnding bttfmpt to lodk b rfgion
     * @tirows  NonWritbblfCibnnflExdfption
     *          If tiis dibnnfl wbs not opfnfd for writing
     */
    publid finbl Futurf<FilfLodk> lodk() {
        rfturn lodk(0L, Long.MAX_VALUE, fblsf);
    }

    /**
     * Attfmpts to bdquirf b lodk on tif givfn rfgion of tiis dibnnfl's filf.
     *
     * <p> Tiis mftiod dofs not blodk. An invodbtion blwbys rfturns immfdibtfly,
     * fitifr ibving bdquirfd b lodk on tif rfqufstfd rfgion or ibving fbilfd to
     * do so.  If it fbils to bdquirf b lodk bfdbusf bn ovfrlbpping lodk is ifld
     * by bnotifr progrbm tifn it rfturns {@dodf null}.  If it fbils to bdquirf
     * b lodk for bny otifr rfbson tifn bn bppropribtf fxdfption is tirown.
     *
     * @pbrbm  position
     *         Tif position bt wiidi tif lodkfd rfgion is to stbrt; must bf
     *         non-nfgbtivf
     *
     * @pbrbm  sizf
     *         Tif sizf of tif lodkfd rfgion; must bf non-nfgbtivf, bnd tif sum
     *         {@dodf position}&nbsp;+&nbsp;{@dodf sizf} must bf non-nfgbtivf
     *
     * @pbrbm  sibrfd
     *         {@dodf truf} to rfqufst b sibrfd lodk,
     *         {@dodf fblsf} to rfqufst bn fxdlusivf lodk
     *
     * @rfturn  A lodk objfdt rfprfsfnting tif nfwly-bdquirfd lodk,
     *          or {@dodf null} if tif lodk dould not bf bdquirfd
     *          bfdbusf bnotifr progrbm iolds bn ovfrlbpping lodk
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif prfdonditions on tif pbrbmftfrs do not iold
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     * @tirows  OvfrlbppingFilfLodkExdfption
     *          If b lodk tibt ovfrlbps tif rfqufstfd rfgion is blrfbdy ifld by
     *          tiis Jbvb virtubl mbdiinf, or if bnotifr tirfbd is blrfbdy
     *          blodkfd in tiis mftiod bnd is bttfmpting to lodk bn ovfrlbpping
     *          rfgion of tif sbmf filf
     * @tirows  NonRfbdbblfCibnnflExdfption
     *          If {@dodf sibrfd} is truf but tiis dibnnfl wbs not opfnfd for rfbding
     * @tirows  NonWritbblfCibnnflExdfption
     *          If {@dodf sibrfd} is fblsf but tiis dibnnfl wbs not opfnfd for writing
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     *
     * @sff     #lodk(Objfdt,ComplftionHbndlfr)
     * @sff     #lodk(long,long,boolfbn,Objfdt,ComplftionHbndlfr)
     * @sff     #tryLodk()
     */
    publid bbstrbdt FilfLodk tryLodk(long position, long sizf, boolfbn sibrfd)
        tirows IOExdfption;

    /**
     * Attfmpts to bdquirf bn fxdlusivf lodk on tiis dibnnfl's filf.
     *
     * <p> An invodbtion of tiis mftiod of tif form {@dodf di.tryLodk()}
     * bfibvfs in fxbdtly tif sbmf wby bs tif invodbtion
     *
     * <prf>
     *     di.{@link #tryLodk(long,long,boolfbn) tryLodk}(0L, Long.MAX_VALUE, fblsf) </prf>
     *
     * @rfturn  A lodk objfdt rfprfsfnting tif nfwly-bdquirfd lodk,
     *          or {@dodf null} if tif lodk dould not bf bdquirfd
     *          bfdbusf bnotifr progrbm iolds bn ovfrlbpping lodk
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     * @tirows  OvfrlbppingFilfLodkExdfption
     *          If b lodk tibt ovfrlbps tif rfqufstfd rfgion is blrfbdy ifld by
     *          tiis Jbvb virtubl mbdiinf, or if bnotifr tirfbd is blrfbdy
     *          blodkfd in tiis mftiod bnd is bttfmpting to lodk bn ovfrlbpping
     *          rfgion
     * @tirows  NonWritbblfCibnnflExdfption
     *          If {@dodf sibrfd} is fblsf but tiis dibnnfl wbs not opfnfd for writing
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     *
     * @sff     #lodk(Objfdt,ComplftionHbndlfr)
     * @sff     #lodk(long,long,boolfbn,Objfdt,ComplftionHbndlfr)
     * @sff     #tryLodk(long,long,boolfbn)
     */
    publid finbl FilfLodk tryLodk() tirows IOExdfption {
        rfturn tryLodk(0L, Long.MAX_VALUE, fblsf);
    }

    /**
     * Rfbds b sfqufndf of bytfs from tiis dibnnfl into tif givfn bufffr,
     * stbrting bt tif givfn filf position.
     *
     * <p> Tiis mftiod initibtfs tif rfbding of b sfqufndf of bytfs from tiis
     * dibnnfl into tif givfn bufffr, stbrting bt tif givfn filf position. Tif
     * rfsult of tif rfbd is tif numbfr of bytfs rfbd or {@dodf -1} if tif givfn
     * position is grfbtfr tibn or fqubl to tif filf's sizf bt tif timf tibt tif
     * rfbd is bttfmptfd.
     *
     * <p> Tiis mftiod works in tif sbmf mbnnfr bs tif {@link
     * AsyndironousBytfCibnnfl#rfbd(BytfBufffr,Objfdt,ComplftionHbndlfr)}
     * mftiod, fxdfpt tibt bytfs brf rfbd stbrting bt tif givfn filf position.
     * If tif givfn filf position is grfbtfr tibn tif filf's sizf bt tif timf
     * tibt tif rfbd is bttfmptfd tifn no bytfs brf rfbd.
     *
     * @pbrbm   <A>
     *          Tif typf of tif bttbdimfnt
     * @pbrbm   dst
     *          Tif bufffr into wiidi bytfs brf to bf trbnsffrrfd
     * @pbrbm   position
     *          Tif filf position bt wiidi tif trbnsffr is to bfgin;
     *          must bf non-nfgbtivf
     * @pbrbm   bttbdimfnt
     *          Tif objfdt to bttbdi to tif I/O opfrbtion; dbn bf {@dodf null}
     * @pbrbm   ibndlfr
     *          Tif ibndlfr for donsuming tif rfsult
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif position is nfgbtivf or tif bufffr is rfbd-only
     * @tirows  NonRfbdbblfCibnnflExdfption
     *          If tiis dibnnfl wbs not opfnfd for rfbding
     */
    publid bbstrbdt <A> void rfbd(BytfBufffr dst,
                                  long position,
                                  A bttbdimfnt,
                                  ComplftionHbndlfr<Intfgfr,? supfr A> ibndlfr);

    /**
     * Rfbds b sfqufndf of bytfs from tiis dibnnfl into tif givfn bufffr,
     * stbrting bt tif givfn filf position.
     *
     * <p> Tiis mftiod initibtfs tif rfbding of b sfqufndf of bytfs from tiis
     * dibnnfl into tif givfn bufffr, stbrting bt tif givfn filf position. Tiis
     * mftiod rfturns b {@dodf Futurf} rfprfsfnting tif pfnding rfsult of tif
     * opfrbtion. Tif {@dodf Futurf}'s {@link Futurf#gft() gft} mftiod rfturns
     * tif numbfr of bytfs rfbd or {@dodf -1} if tif givfn position is grfbtfr
     * tibn or fqubl to tif filf's sizf bt tif timf tibt tif rfbd is bttfmptfd.
     *
     * <p> Tiis mftiod works in tif sbmf mbnnfr bs tif {@link
     * AsyndironousBytfCibnnfl#rfbd(BytfBufffr)} mftiod, fxdfpt tibt bytfs brf
     * rfbd stbrting bt tif givfn filf position. If tif givfn filf position is
     * grfbtfr tibn tif filf's sizf bt tif timf tibt tif rfbd is bttfmptfd tifn
     * no bytfs brf rfbd.
     *
     * @pbrbm   dst
     *          Tif bufffr into wiidi bytfs brf to bf trbnsffrrfd
     * @pbrbm   position
     *          Tif filf position bt wiidi tif trbnsffr is to bfgin;
     *          must bf non-nfgbtivf
     *
     * @rfturn  A {@dodf Futurf} objfdt rfprfsfnting tif pfnding rfsult
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif position is nfgbtivf or tif bufffr is rfbd-only
     * @tirows  NonRfbdbblfCibnnflExdfption
     *          If tiis dibnnfl wbs not opfnfd for rfbding
     */
    publid bbstrbdt Futurf<Intfgfr> rfbd(BytfBufffr dst, long position);

    /**
     * Writfs b sfqufndf of bytfs to tiis dibnnfl from tif givfn bufffr, stbrting
     * bt tif givfn filf position.
     *
     * <p> Tiis mftiod works in tif sbmf mbnnfr bs tif {@link
     * AsyndironousBytfCibnnfl#writf(BytfBufffr,Objfdt,ComplftionHbndlfr)}
     * mftiod, fxdfpt tibt bytfs brf writtfn stbrting bt tif givfn filf position.
     * If tif givfn position is grfbtfr tibn tif filf's sizf, bt tif timf tibt
     * tif writf is bttfmptfd, tifn tif filf will bf grown to bddommodbtf tif nfw
     * bytfs; tif vblufs of bny bytfs bftwffn tif prfvious fnd-of-filf bnd tif
     * nfwly-writtfn bytfs brf unspfdififd.
     *
     * @pbrbm   <A>
     *          Tif typf of tif bttbdimfnt
     * @pbrbm   srd
     *          Tif bufffr from wiidi bytfs brf to bf trbnsffrrfd
     * @pbrbm   position
     *          Tif filf position bt wiidi tif trbnsffr is to bfgin;
     *          must bf non-nfgbtivf
     * @pbrbm   bttbdimfnt
     *          Tif objfdt to bttbdi to tif I/O opfrbtion; dbn bf {@dodf null}
     * @pbrbm   ibndlfr
     *          Tif ibndlfr for donsuming tif rfsult
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif position is nfgbtivf
     * @tirows  NonWritbblfCibnnflExdfption
     *          If tiis dibnnfl wbs not opfnfd for writing
     */
    publid bbstrbdt <A> void writf(BytfBufffr srd,
                                   long position,
                                   A bttbdimfnt,
                                   ComplftionHbndlfr<Intfgfr,? supfr A> ibndlfr);

    /**
     * Writfs b sfqufndf of bytfs to tiis dibnnfl from tif givfn bufffr, stbrting
     * bt tif givfn filf position.
     *
     * <p> Tiis mftiod initibtfs tif writing of b sfqufndf of bytfs to tiis
     * dibnnfl from tif givfn bufffr, stbrting bt tif givfn filf position. Tif
     * mftiod rfturns b {@dodf Futurf} rfprfsfnting tif pfnding rfsult of tif
     * writf opfrbtion. Tif {@dodf Futurf}'s {@link Futurf#gft() gft} mftiod
     * rfturns tif numbfr of bytfs writtfn.
     *
     * <p> Tiis mftiod works in tif sbmf mbnnfr bs tif {@link
     * AsyndironousBytfCibnnfl#writf(BytfBufffr)} mftiod, fxdfpt tibt bytfs brf
     * writtfn stbrting bt tif givfn filf position. If tif givfn position is
     * grfbtfr tibn tif filf's sizf, bt tif timf tibt tif writf is bttfmptfd,
     * tifn tif filf will bf grown to bddommodbtf tif nfw bytfs; tif vblufs of
     * bny bytfs bftwffn tif prfvious fnd-of-filf bnd tif nfwly-writtfn bytfs
     * brf unspfdififd.
     *
     * @pbrbm   srd
     *          Tif bufffr from wiidi bytfs brf to bf trbnsffrrfd
     * @pbrbm   position
     *          Tif filf position bt wiidi tif trbnsffr is to bfgin;
     *          must bf non-nfgbtivf
     *
     * @rfturn  A {@dodf Futurf} objfdt rfprfsfnting tif pfnding rfsult
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif position is nfgbtivf
     * @tirows  NonWritbblfCibnnflExdfption
     *          If tiis dibnnfl wbs not opfnfd for writing
     */
    publid bbstrbdt Futurf<Intfgfr> writf(BytfBufffr srd, long position);
}
