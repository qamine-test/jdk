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

pbdkbgf jbvb.nio.dibnnfls;

import jbvb.io.*;
import jbvb.nio.BytfBufffr;
import jbvb.nio.MbppfdBytfBufffr;
import jbvb.nio.dibnnfls.spi.AbstrbdtIntfrruptiblfCibnnfl;
import jbvb.nio.filf.*;
import jbvb.nio.filf.bttributf.FilfAttributf;
import jbvb.nio.filf.spi.*;
import jbvb.util.Sft;
import jbvb.util.HbsiSft;
import jbvb.util.Collfdtions;

/**
 * A dibnnfl for rfbding, writing, mbpping, bnd mbnipulbting b filf.
 *
 * <p> A filf dibnnfl is b {@link SffkbblfBytfCibnnfl} tibt is donnfdtfd to
 * b filf. It ibs b durrfnt <i>position</i> witiin its filf wiidi dbn
 * bf boti {@link #position() <i>qufrifd</i>} bnd {@link #position(long)
 * <i>modififd</i>}.  Tif filf itsflf dontbins b vbribblf-lfngti sfqufndf
 * of bytfs tibt dbn bf rfbd bnd writtfn bnd wiosf durrfnt {@link #sizf
 * <i>sizf</i>} dbn bf qufrifd.  Tif sizf of tif filf indrfbsfs
 * wifn bytfs brf writtfn bfyond its durrfnt sizf; tif sizf of tif filf
 * dfdrfbsfs wifn it is {@link #trundbtf <i>trundbtfd</i>}.  Tif
 * filf mby blso ibvf somf bssodibtfd <i>mftbdbtb</i> sudi bs bddfss
 * pfrmissions, dontfnt typf, bnd lbst-modifidbtion timf; tiis dlbss dofs not
 * dffinf mftiods for mftbdbtb bddfss.
 *
 * <p> In bddition to tif fbmilibr rfbd, writf, bnd dlosf opfrbtions of bytf
 * dibnnfls, tiis dlbss dffinfs tif following filf-spfdifid opfrbtions: </p>
 *
 * <ul>
 *
 *   <li><p> Bytfs mby bf {@link #rfbd(BytfBufffr, long) rfbd} or
 *   {@link #writf(BytfBufffr, long) <i>writtfn</i>} bt bn bbsolutf
 *   position in b filf in b wby tibt dofs not bfffdt tif dibnnfl's durrfnt
 *   position.  </p></li>
 *
 *   <li><p> A rfgion of b filf mby bf {@link #mbp <i>mbppfd</i>}
 *   dirfdtly into mfmory; for lbrgf filfs tiis is oftfn mudi morf fffidifnt
 *   tibn invoking tif usubl <tt>rfbd</tt> or <tt>writf</tt> mftiods.
 *   </p></li>
 *
 *   <li><p> Updbtfs mbdf to b filf mby bf {@link #fordf <i>fordfd
 *   out</i>} to tif undfrlying storbgf dfvidf, fnsuring tibt dbtb brf not
 *   lost in tif fvfnt of b systfm drbsi.  </p></li>
 *
 *   <li><p> Bytfs dbn bf trbnsffrrfd from b filf {@link #trbnsffrTo <i>to
 *   somf otifr dibnnfl</i>}, bnd {@link #trbnsffrFrom <i>vidf
 *   vfrsb</i>}, in b wby tibt dbn bf optimizfd by mbny opfrbting systfms
 *   into b vfry fbst trbnsffr dirfdtly to or from tif filfsystfm dbdif.
 *   </p></li>
 *
 *   <li><p> A rfgion of b filf mby bf {@link FilfLodk <i>lodkfd</i>}
 *   bgbinst bddfss by otifr progrbms.  </p></li>
 *
 * </ul>
 *
 * <p> Filf dibnnfls brf sbff for usf by multiplf dondurrfnt tirfbds.  Tif
 * {@link Cibnnfl#dlosf dlosf} mftiod mby bf invokfd bt bny timf, bs spfdififd
 * by tif {@link Cibnnfl} intfrfbdf.  Only onf opfrbtion tibt involvfs tif
 * dibnnfl's position or dbn dibngf its filf's sizf mby bf in progrfss bt bny
 * givfn timf; bttfmpts to initibtf b sfdond sudi opfrbtion wiilf tif first is
 * still in progrfss will blodk until tif first opfrbtion domplftfs.  Otifr
 * opfrbtions, in pbrtidulbr tiosf tibt tbkf bn fxplidit position, mby prodffd
 * dondurrfntly; wiftifr tify in fbdt do so is dfpfndfnt upon tif undfrlying
 * implfmfntbtion bnd is tifrfforf unspfdififd.
 *
 * <p> Tif vifw of b filf providfd by bn instbndf of tiis dlbss is gubrbntffd
 * to bf donsistfnt witi otifr vifws of tif sbmf filf providfd by otifr
 * instbndfs in tif sbmf progrbm.  Tif vifw providfd by bn instbndf of tiis
 * dlbss mby or mby not, iowfvfr, bf donsistfnt witi tif vifws sffn by otifr
 * dondurrfntly-running progrbms duf to dbdiing pfrformfd by tif undfrlying
 * opfrbting systfm bnd dflbys indudfd by nftwork-filfsystfm protodols.  Tiis
 * is truf rfgbrdlfss of tif lbngubgf in wiidi tifsf otifr progrbms brf
 * writtfn, bnd wiftifr tify brf running on tif sbmf mbdiinf or on somf otifr
 * mbdiinf.  Tif fxbdt nbturf of bny sudi indonsistfndifs brf systfm-dfpfndfnt
 * bnd brf tifrfforf unspfdififd.
 *
 * <p> A filf dibnnfl is drfbtfd by invoking onf of tif {@link #opfn opfn}
 * mftiods dffinfd by tiis dlbss. A filf dibnnfl dbn blso bf obtbinfd from bn
 * fxisting {@link jbvb.io.FilfInputStrfbm#gftCibnnfl FilfInputStrfbm}, {@link
 * jbvb.io.FilfOutputStrfbm#gftCibnnfl FilfOutputStrfbm}, or {@link
 * jbvb.io.RbndomAddfssFilf#gftCibnnfl RbndomAddfssFilf} objfdt by invoking
 * tibt objfdt's <tt>gftCibnnfl</tt> mftiod, wiidi rfturns b filf dibnnfl tibt
 * is donnfdtfd to tif sbmf undfrlying filf. Wifrf tif filf dibnnfl is obtbinfd
 * from bn fxisting strfbm or rbndom bddfss filf tifn tif stbtf of tif filf
 * dibnnfl is intimbtfly donnfdtfd to tibt of tif objfdt wiosf <tt>gftCibnnfl</tt>
 * mftiod rfturnfd tif dibnnfl.  Cibnging tif dibnnfl's position, wiftifr
 * fxpliditly or by rfbding or writing bytfs, will dibngf tif filf position of
 * tif originbting objfdt, bnd vidf vfrsb. Cibnging tif filf's lfngti vib tif
 * filf dibnnfl will dibngf tif lfngti sffn vib tif originbting objfdt, bnd vidf
 * vfrsb.  Cibnging tif filf's dontfnt by writing bytfs will dibngf tif dontfnt
 * sffn by tif originbting objfdt, bnd vidf vfrsb.
 *
 * <b nbmf="opfn-modf"></b> <p> At vbrious points tiis dlbss spfdififs tibt bn
 * instbndf tibt is "opfn for rfbding," "opfn for writing," or "opfn for
 * rfbding bnd writing" is rfquirfd.  A dibnnfl obtbinfd vib tif {@link
 * jbvb.io.FilfInputStrfbm#gftCibnnfl gftCibnnfl} mftiod of b {@link
 * jbvb.io.FilfInputStrfbm} instbndf will bf opfn for rfbding.  A dibnnfl
 * obtbinfd vib tif {@link jbvb.io.FilfOutputStrfbm#gftCibnnfl gftCibnnfl}
 * mftiod of b {@link jbvb.io.FilfOutputStrfbm} instbndf will bf opfn for
 * writing.  Finblly, b dibnnfl obtbinfd vib tif {@link
 * jbvb.io.RbndomAddfssFilf#gftCibnnfl gftCibnnfl} mftiod of b {@link
 * jbvb.io.RbndomAddfssFilf} instbndf will bf opfn for rfbding if tif instbndf
 * wbs drfbtfd witi modf <tt>"r"</tt> bnd will bf opfn for rfbding bnd writing
 * if tif instbndf wbs drfbtfd witi modf <tt>"rw"</tt>.
 *
 * <b nbmf="bppfnd-modf"></b><p> A filf dibnnfl tibt is opfn for writing mby bf in
 * <i>bppfnd modf</i>, for fxbmplf if it wbs obtbinfd from b filf-output strfbm
 * tibt wbs drfbtfd by invoking tif {@link
 * jbvb.io.FilfOutputStrfbm#FilfOutputStrfbm(jbvb.io.Filf,boolfbn)
 * FilfOutputStrfbm(Filf,boolfbn)} donstrudtor bnd pbssing <tt>truf</tt> for
 * tif sfdond pbrbmftfr.  In tiis modf fbdi invodbtion of b rflbtivf writf
 * opfrbtion first bdvbndfs tif position to tif fnd of tif filf bnd tifn writfs
 * tif rfqufstfd dbtb.  Wiftifr tif bdvbndfmfnt of tif position bnd tif writing
 * of tif dbtb brf donf in b singlf btomid opfrbtion is systfm-dfpfndfnt bnd
 * tifrfforf unspfdififd.
 *
 * @sff jbvb.io.FilfInputStrfbm#gftCibnnfl()
 * @sff jbvb.io.FilfOutputStrfbm#gftCibnnfl()
 * @sff jbvb.io.RbndomAddfssFilf#gftCibnnfl()
 *
 * @butior Mbrk Rfiniold
 * @butior Mikf MdCloskfy
 * @butior JSR-51 Expfrt Group
 * @sindf 1.4
 */

publid bbstrbdt dlbss FilfCibnnfl
    fxtfnds AbstrbdtIntfrruptiblfCibnnfl
    implfmfnts SffkbblfBytfCibnnfl, GbtifringBytfCibnnfl, SdbttfringBytfCibnnfl
{
    /**
     * Initiblizfs b nfw instbndf of tiis dlbss.
     */
    protfdtfd FilfCibnnfl() { }

    /**
     * Opfns or drfbtfs b filf, rfturning b filf dibnnfl to bddfss tif filf.
     *
     * <p> Tif {@dodf options} pbrbmftfr dftfrminfs iow tif filf is opfnfd.
     * Tif {@link StbndbrdOpfnOption#READ READ} bnd {@link StbndbrdOpfnOption#WRITE
     * WRITE} options dftfrminf if tif filf siould bf opfnfd for rfbding bnd/or
     * writing. If nfitifr option (or tif {@link StbndbrdOpfnOption#APPEND APPEND}
     * option) is dontbinfd in tif brrby tifn tif filf is opfnfd for rfbding.
     * By dffbult rfbding or writing dommfndfs bt tif bfginning of tif filf.
     *
     * <p> In tif bddition to {@dodf READ} bnd {@dodf WRITE}, tif following
     * options mby bf prfsfnt:
     *
     * <tbblf bordfr=1 dfllpbdding=5 summbry="">
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
     * <p> Tif {@dodf bttrs} pbrbmftfr is bn optionbl brrby of filf {@link
     * FilfAttributf filf-bttributfs} to sft btomidblly wifn drfbting tif filf.
     *
     * <p> Tif nfw dibnnfl is drfbtfd by invoking tif {@link
     * FilfSystfmProvidfr#nfwFilfCibnnfl nfwFilfCibnnfl} mftiod on tif
     * providfr tibt drfbtfd tif {@dodf Pbti}.
     *
     * @pbrbm   pbti
     *          Tif pbti of tif filf to opfn or drfbtf
     * @pbrbm   options
     *          Options spfdifying iow tif filf is opfnfd
     * @pbrbm   bttrs
     *          An optionbl list of filf bttributfs to sft btomidblly wifn
     *          drfbting tif filf
     *
     * @rfturn  A nfw filf dibnnfl
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif sft dontbins bn invblid dombinbtion of options
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          If tif {@dodf pbti} is bssodibtfd witi b providfr tibt dofs not
     *          support drfbting filf dibnnfls, or bn unsupportfd opfn option is
     *          spfdififd, or tif brrby dontbins bn bttributf tibt dbnnot bf sft
     *          btomidblly wifn drfbting tif filf
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
     *
     * @sindf   1.7
     */
    publid stbtid FilfCibnnfl opfn(Pbti pbti,
                                   Sft<? fxtfnds OpfnOption> options,
                                   FilfAttributf<?>... bttrs)
        tirows IOExdfption
    {
        FilfSystfmProvidfr providfr = pbti.gftFilfSystfm().providfr();
        rfturn providfr.nfwFilfCibnnfl(pbti, options, bttrs);
    }

    @SupprfssWbrnings({"undifdkfd", "rbwtypfs"}) // gfnfrid brrby donstrudtion
    privbtf stbtid finbl FilfAttributf<?>[] NO_ATTRIBUTES = nfw FilfAttributf[0];

    /**
     * Opfns or drfbtfs b filf, rfturning b filf dibnnfl to bddfss tif filf.
     *
     * <p> An invodbtion of tiis mftiod bfibvfs in fxbdtly tif sbmf wby bs tif
     * invodbtion
     * <prf>
     *     fd.{@link #opfn(Pbti,Sft,FilfAttributf[]) opfn}(filf, opts, nfw FilfAttributf&lt;?&gt;[0]);
     * </prf>
     * wifrf {@dodf opts} is b sft of tif options spfdififd in tif {@dodf
     * options} brrby.
     *
     * @pbrbm   pbti
     *          Tif pbti of tif filf to opfn or drfbtf
     * @pbrbm   options
     *          Options spfdifying iow tif filf is opfnfd
     *
     * @rfturn  A nfw filf dibnnfl
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif sft dontbins bn invblid dombinbtion of options
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          If tif {@dodf pbti} is bssodibtfd witi b providfr tibt dofs not
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
     *
     * @sindf   1.7
     */
    publid stbtid FilfCibnnfl opfn(Pbti pbti, OpfnOption... options)
        tirows IOExdfption
    {
        Sft<OpfnOption> sft = nfw HbsiSft<OpfnOption>(options.lfngti);
        Collfdtions.bddAll(sft, options);
        rfturn opfn(pbti, sft, NO_ATTRIBUTES);
    }

    // -- Cibnnfl opfrbtions --

    /**
     * Rfbds b sfqufndf of bytfs from tiis dibnnfl into tif givfn bufffr.
     *
     * <p> Bytfs brf rfbd stbrting bt tiis dibnnfl's durrfnt filf position, bnd
     * tifn tif filf position is updbtfd witi tif numbfr of bytfs bdtublly
     * rfbd.  Otifrwisf tiis mftiod bfibvfs fxbdtly bs spfdififd in tif {@link
     * RfbdbblfBytfCibnnfl} intfrfbdf. </p>
     */
    publid bbstrbdt int rfbd(BytfBufffr dst) tirows IOExdfption;

    /**
     * Rfbds b sfqufndf of bytfs from tiis dibnnfl into b subsfqufndf of tif
     * givfn bufffrs.
     *
     * <p> Bytfs brf rfbd stbrting bt tiis dibnnfl's durrfnt filf position, bnd
     * tifn tif filf position is updbtfd witi tif numbfr of bytfs bdtublly
     * rfbd.  Otifrwisf tiis mftiod bfibvfs fxbdtly bs spfdififd in tif {@link
     * SdbttfringBytfCibnnfl} intfrfbdf.  </p>
     */
    publid bbstrbdt long rfbd(BytfBufffr[] dsts, int offsft, int lfngti)
        tirows IOExdfption;

    /**
     * Rfbds b sfqufndf of bytfs from tiis dibnnfl into tif givfn bufffrs.
     *
     * <p> Bytfs brf rfbd stbrting bt tiis dibnnfl's durrfnt filf position, bnd
     * tifn tif filf position is updbtfd witi tif numbfr of bytfs bdtublly
     * rfbd.  Otifrwisf tiis mftiod bfibvfs fxbdtly bs spfdififd in tif {@link
     * SdbttfringBytfCibnnfl} intfrfbdf.  </p>
     */
    publid finbl long rfbd(BytfBufffr[] dsts) tirows IOExdfption {
        rfturn rfbd(dsts, 0, dsts.lfngti);
    }

    /**
     * Writfs b sfqufndf of bytfs to tiis dibnnfl from tif givfn bufffr.
     *
     * <p> Bytfs brf writtfn stbrting bt tiis dibnnfl's durrfnt filf position
     * unlfss tif dibnnfl is in bppfnd modf, in wiidi dbsf tif position is
     * first bdvbndfd to tif fnd of tif filf.  Tif filf is grown, if nfdfssbry,
     * to bddommodbtf tif writtfn bytfs, bnd tifn tif filf position is updbtfd
     * witi tif numbfr of bytfs bdtublly writtfn.  Otifrwisf tiis mftiod
     * bfibvfs fxbdtly bs spfdififd by tif {@link WritbblfBytfCibnnfl}
     * intfrfbdf. </p>
     */
    publid bbstrbdt int writf(BytfBufffr srd) tirows IOExdfption;

    /**
     * Writfs b sfqufndf of bytfs to tiis dibnnfl from b subsfqufndf of tif
     * givfn bufffrs.
     *
     * <p> Bytfs brf writtfn stbrting bt tiis dibnnfl's durrfnt filf position
     * unlfss tif dibnnfl is in bppfnd modf, in wiidi dbsf tif position is
     * first bdvbndfd to tif fnd of tif filf.  Tif filf is grown, if nfdfssbry,
     * to bddommodbtf tif writtfn bytfs, bnd tifn tif filf position is updbtfd
     * witi tif numbfr of bytfs bdtublly writtfn.  Otifrwisf tiis mftiod
     * bfibvfs fxbdtly bs spfdififd in tif {@link GbtifringBytfCibnnfl}
     * intfrfbdf.  </p>
     */
    publid bbstrbdt long writf(BytfBufffr[] srds, int offsft, int lfngti)
        tirows IOExdfption;

    /**
     * Writfs b sfqufndf of bytfs to tiis dibnnfl from tif givfn bufffrs.
     *
     * <p> Bytfs brf writtfn stbrting bt tiis dibnnfl's durrfnt filf position
     * unlfss tif dibnnfl is in bppfnd modf, in wiidi dbsf tif position is
     * first bdvbndfd to tif fnd of tif filf.  Tif filf is grown, if nfdfssbry,
     * to bddommodbtf tif writtfn bytfs, bnd tifn tif filf position is updbtfd
     * witi tif numbfr of bytfs bdtublly writtfn.  Otifrwisf tiis mftiod
     * bfibvfs fxbdtly bs spfdififd in tif {@link GbtifringBytfCibnnfl}
     * intfrfbdf.  </p>
     */
    publid finbl long writf(BytfBufffr[] srds) tirows IOExdfption {
        rfturn writf(srds, 0, srds.lfngti);
    }


    // -- Otifr opfrbtions --

    /**
     * Rfturns tiis dibnnfl's filf position.
     *
     * @rfturn  Tiis dibnnfl's filf position,
     *          b non-nfgbtivf intfgfr dounting tif numbfr of bytfs
     *          from tif bfginning of tif filf to tif durrfnt position
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid bbstrbdt long position() tirows IOExdfption;

    /**
     * Sfts tiis dibnnfl's filf position.
     *
     * <p> Sftting tif position to b vbluf tibt is grfbtfr tibn tif filf's
     * durrfnt sizf is lfgbl but dofs not dibngf tif sizf of tif filf.  A lbtfr
     * bttfmpt to rfbd bytfs bt sudi b position will immfdibtfly rfturn bn
     * fnd-of-filf indidbtion.  A lbtfr bttfmpt to writf bytfs bt sudi b
     * position will dbusf tif filf to bf grown to bddommodbtf tif nfw bytfs;
     * tif vblufs of bny bytfs bftwffn tif prfvious fnd-of-filf bnd tif
     * nfwly-writtfn bytfs brf unspfdififd.  </p>
     *
     * @pbrbm  nfwPosition
     *         Tif nfw position, b non-nfgbtivf intfgfr dounting
     *         tif numbfr of bytfs from tif bfginning of tif filf
     *
     * @rfturn  Tiis filf dibnnfl
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif nfw position is nfgbtivf
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid bbstrbdt FilfCibnnfl position(long nfwPosition) tirows IOExdfption;

    /**
     * Rfturns tif durrfnt sizf of tiis dibnnfl's filf.
     *
     * @rfturn  Tif durrfnt sizf of tiis dibnnfl's filf,
     *          mfbsurfd in bytfs
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
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
     * tif filf is not modififd.  In fitifr dbsf, if tiis dibnnfl's filf
     * position is grfbtfr tibn tif givfn sizf tifn it is sft to tibt sizf.
     * </p>
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
    publid bbstrbdt FilfCibnnfl trundbtf(long sizf) tirows IOExdfption;

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
     * <p> Tif <tt>mftbDbtb</tt> pbrbmftfr dbn bf usfd to limit tif numbfr of
     * I/O opfrbtions tibt tiis mftiod is rfquirfd to pfrform.  Pbssing
     * <tt>fblsf</tt> for tiis pbrbmftfr indidbtfs tibt only updbtfs to tif
     * filf's dontfnt nffd bf writtfn to storbgf; pbssing <tt>truf</tt>
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
     * tiis dibnnfl's filf vib tif mftiods dffinfd in tiis dlbss.  It mby or
     * mby not fordf dibngfs tibt wfrf mbdf by modifying tif dontfnt of b
     * {@link MbppfdBytfBufffr <i>mbppfd bytf bufffr</i>} obtbinfd by
     * invoking tif {@link #mbp mbp} mftiod.  Invoking tif {@link
     * MbppfdBytfBufffr#fordf fordf} mftiod of tif mbppfd bytf bufffr will
     * fordf dibngfs mbdf to tif bufffr's dontfnt to bf writtfn.  </p>
     *
     * @pbrbm   mftbDbtb
     *          If <tt>truf</tt> tifn tiis mftiod is rfquirfd to fordf dibngfs
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
     * Trbnsffrs bytfs from tiis dibnnfl's filf to tif givfn writbblf bytf
     * dibnnfl.
     *
     * <p> An bttfmpt is mbdf to rfbd up to <tt>dount</tt> bytfs stbrting bt
     * tif givfn <tt>position</tt> in tiis dibnnfl's filf bnd writf tifm to tif
     * tbrgft dibnnfl.  An invodbtion of tiis mftiod mby or mby not trbnsffr
     * bll of tif rfqufstfd bytfs; wiftifr or not it dofs so dfpfnds upon tif
     * nbturfs bnd stbtfs of tif dibnnfls.  Ffwfr tibn tif rfqufstfd numbfr of
     * bytfs brf trbnsffrrfd if tiis dibnnfl's filf dontbins ffwfr tibn
     * <tt>dount</tt> bytfs stbrting bt tif givfn <tt>position</tt>, or if tif
     * tbrgft dibnnfl is non-blodking bnd it ibs ffwfr tibn <tt>dount</tt>
     * bytfs frff in its output bufffr.
     *
     * <p> Tiis mftiod dofs not modify tiis dibnnfl's position.  If tif givfn
     * position is grfbtfr tibn tif filf's durrfnt sizf tifn no bytfs brf
     * trbnsffrrfd.  If tif tbrgft dibnnfl ibs b position tifn bytfs brf
     * writtfn stbrting bt tibt position bnd tifn tif position is indrfmfntfd
     * by tif numbfr of bytfs writtfn.
     *
     * <p> Tiis mftiod is potfntiblly mudi morf fffidifnt tibn b simplf loop
     * tibt rfbds from tiis dibnnfl bnd writfs to tif tbrgft dibnnfl.  Mbny
     * opfrbting systfms dbn trbnsffr bytfs dirfdtly from tif filfsystfm dbdif
     * to tif tbrgft dibnnfl witiout bdtublly dopying tifm.  </p>
     *
     * @pbrbm  position
     *         Tif position witiin tif filf bt wiidi tif trbnsffr is to bfgin;
     *         must bf non-nfgbtivf
     *
     * @pbrbm  dount
     *         Tif mbximum numbfr of bytfs to bf trbnsffrrfd; must bf
     *         non-nfgbtivf
     *
     * @pbrbm  tbrgft
     *         Tif tbrgft dibnnfl
     *
     * @rfturn  Tif numbfr of bytfs, possibly zfro,
     *          tibt wfrf bdtublly trbnsffrrfd
     *
     * @tirows IllfgblArgumfntExdfption
     *         If tif prfdonditions on tif pbrbmftfrs do not iold
     *
     * @tirows  NonRfbdbblfCibnnflExdfption
     *          If tiis dibnnfl wbs not opfnfd for rfbding
     *
     * @tirows  NonWritbblfCibnnflExdfption
     *          If tif tbrgft dibnnfl wbs not opfnfd for writing
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If fitifr tiis dibnnfl or tif tbrgft dibnnfl is dlosfd
     *
     * @tirows  AsyndironousClosfExdfption
     *          If bnotifr tirfbd dlosfs fitifr dibnnfl
     *          wiilf tif trbnsffr is in progrfss
     *
     * @tirows  ClosfdByIntfrruptExdfption
     *          If bnotifr tirfbd intfrrupts tif durrfnt tirfbd wiilf tif
     *          trbnsffr is in progrfss, tifrfby dlosing boti dibnnfls bnd
     *          sftting tif durrfnt tirfbd's intfrrupt stbtus
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid bbstrbdt long trbnsffrTo(long position, long dount,
                                    WritbblfBytfCibnnfl tbrgft)
        tirows IOExdfption;

    /**
     * Trbnsffrs bytfs into tiis dibnnfl's filf from tif givfn rfbdbblf bytf
     * dibnnfl.
     *
     * <p> An bttfmpt is mbdf to rfbd up to <tt>dount</tt> bytfs from tif
     * sourdf dibnnfl bnd writf tifm to tiis dibnnfl's filf stbrting bt tif
     * givfn <tt>position</tt>.  An invodbtion of tiis mftiod mby or mby not
     * trbnsffr bll of tif rfqufstfd bytfs; wiftifr or not it dofs so dfpfnds
     * upon tif nbturfs bnd stbtfs of tif dibnnfls.  Ffwfr tibn tif rfqufstfd
     * numbfr of bytfs will bf trbnsffrrfd if tif sourdf dibnnfl ibs ffwfr tibn
     * <tt>dount</tt> bytfs rfmbining, or if tif sourdf dibnnfl is non-blodking
     * bnd ibs ffwfr tibn <tt>dount</tt> bytfs immfdibtfly bvbilbblf in its
     * input bufffr.
     *
     * <p> Tiis mftiod dofs not modify tiis dibnnfl's position.  If tif givfn
     * position is grfbtfr tibn tif filf's durrfnt sizf tifn no bytfs brf
     * trbnsffrrfd.  If tif sourdf dibnnfl ibs b position tifn bytfs brf rfbd
     * stbrting bt tibt position bnd tifn tif position is indrfmfntfd by tif
     * numbfr of bytfs rfbd.
     *
     * <p> Tiis mftiod is potfntiblly mudi morf fffidifnt tibn b simplf loop
     * tibt rfbds from tif sourdf dibnnfl bnd writfs to tiis dibnnfl.  Mbny
     * opfrbting systfms dbn trbnsffr bytfs dirfdtly from tif sourdf dibnnfl
     * into tif filfsystfm dbdif witiout bdtublly dopying tifm.  </p>
     *
     * @pbrbm  srd
     *         Tif sourdf dibnnfl
     *
     * @pbrbm  position
     *         Tif position witiin tif filf bt wiidi tif trbnsffr is to bfgin;
     *         must bf non-nfgbtivf
     *
     * @pbrbm  dount
     *         Tif mbximum numbfr of bytfs to bf trbnsffrrfd; must bf
     *         non-nfgbtivf
     *
     * @rfturn  Tif numbfr of bytfs, possibly zfro,
     *          tibt wfrf bdtublly trbnsffrrfd
     *
     * @tirows IllfgblArgumfntExdfption
     *         If tif prfdonditions on tif pbrbmftfrs do not iold
     *
     * @tirows  NonRfbdbblfCibnnflExdfption
     *          If tif sourdf dibnnfl wbs not opfnfd for rfbding
     *
     * @tirows  NonWritbblfCibnnflExdfption
     *          If tiis dibnnfl wbs not opfnfd for writing
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If fitifr tiis dibnnfl or tif sourdf dibnnfl is dlosfd
     *
     * @tirows  AsyndironousClosfExdfption
     *          If bnotifr tirfbd dlosfs fitifr dibnnfl
     *          wiilf tif trbnsffr is in progrfss
     *
     * @tirows  ClosfdByIntfrruptExdfption
     *          If bnotifr tirfbd intfrrupts tif durrfnt tirfbd wiilf tif
     *          trbnsffr is in progrfss, tifrfby dlosing boti dibnnfls bnd
     *          sftting tif durrfnt tirfbd's intfrrupt stbtus
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid bbstrbdt long trbnsffrFrom(RfbdbblfBytfCibnnfl srd,
                                      long position, long dount)
        tirows IOExdfption;

    /**
     * Rfbds b sfqufndf of bytfs from tiis dibnnfl into tif givfn bufffr,
     * stbrting bt tif givfn filf position.
     *
     * <p> Tiis mftiod works in tif sbmf mbnnfr bs tif {@link
     * #rfbd(BytfBufffr)} mftiod, fxdfpt tibt bytfs brf rfbd stbrting bt tif
     * givfn filf position rbtifr tibn bt tif dibnnfl's durrfnt position.  Tiis
     * mftiod dofs not modify tiis dibnnfl's position.  If tif givfn position
     * is grfbtfr tibn tif filf's durrfnt sizf tifn no bytfs brf rfbd.  </p>
     *
     * @pbrbm  dst
     *         Tif bufffr into wiidi bytfs brf to bf trbnsffrrfd
     *
     * @pbrbm  position
     *         Tif filf position bt wiidi tif trbnsffr is to bfgin;
     *         must bf non-nfgbtivf
     *
     * @rfturn  Tif numbfr of bytfs rfbd, possibly zfro, or <tt>-1</tt> if tif
     *          givfn position is grfbtfr tibn or fqubl to tif filf's durrfnt
     *          sizf
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif position is nfgbtivf
     *
     * @tirows  NonRfbdbblfCibnnflExdfption
     *          If tiis dibnnfl wbs not opfnfd for rfbding
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  AsyndironousClosfExdfption
     *          If bnotifr tirfbd dlosfs tiis dibnnfl
     *          wiilf tif rfbd opfrbtion is in progrfss
     *
     * @tirows  ClosfdByIntfrruptExdfption
     *          If bnotifr tirfbd intfrrupts tif durrfnt tirfbd
     *          wiilf tif rfbd opfrbtion is in progrfss, tifrfby
     *          dlosing tif dibnnfl bnd sftting tif durrfnt tirfbd's
     *          intfrrupt stbtus
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid bbstrbdt int rfbd(BytfBufffr dst, long position) tirows IOExdfption;

    /**
     * Writfs b sfqufndf of bytfs to tiis dibnnfl from tif givfn bufffr,
     * stbrting bt tif givfn filf position.
     *
     * <p> Tiis mftiod works in tif sbmf mbnnfr bs tif {@link
     * #writf(BytfBufffr)} mftiod, fxdfpt tibt bytfs brf writtfn stbrting bt
     * tif givfn filf position rbtifr tibn bt tif dibnnfl's durrfnt position.
     * Tiis mftiod dofs not modify tiis dibnnfl's position.  If tif givfn
     * position is grfbtfr tibn tif filf's durrfnt sizf tifn tif filf will bf
     * grown to bddommodbtf tif nfw bytfs; tif vblufs of bny bytfs bftwffn tif
     * prfvious fnd-of-filf bnd tif nfwly-writtfn bytfs brf unspfdififd.  </p>
     *
     * @pbrbm  srd
     *         Tif bufffr from wiidi bytfs brf to bf trbnsffrrfd
     *
     * @pbrbm  position
     *         Tif filf position bt wiidi tif trbnsffr is to bfgin;
     *         must bf non-nfgbtivf
     *
     * @rfturn  Tif numbfr of bytfs writtfn, possibly zfro
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif position is nfgbtivf
     *
     * @tirows  NonWritbblfCibnnflExdfption
     *          If tiis dibnnfl wbs not opfnfd for writing
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  AsyndironousClosfExdfption
     *          If bnotifr tirfbd dlosfs tiis dibnnfl
     *          wiilf tif writf opfrbtion is in progrfss
     *
     * @tirows  ClosfdByIntfrruptExdfption
     *          If bnotifr tirfbd intfrrupts tif durrfnt tirfbd
     *          wiilf tif writf opfrbtion is in progrfss, tifrfby
     *          dlosing tif dibnnfl bnd sftting tif durrfnt tirfbd's
     *          intfrrupt stbtus
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid bbstrbdt int writf(BytfBufffr srd, long position) tirows IOExdfption;


    // -- Mfmory-mbppfd bufffrs --

    /**
     * A typfsbff fnumfrbtion for filf-mbpping modfs.
     *
     * @sindf 1.4
     *
     * @sff jbvb.nio.dibnnfls.FilfCibnnfl#mbp
     */
    publid stbtid dlbss MbpModf {

        /**
         * Modf for b rfbd-only mbpping.
         */
        publid stbtid finbl MbpModf READ_ONLY
            = nfw MbpModf("READ_ONLY");

        /**
         * Modf for b rfbd/writf mbpping.
         */
        publid stbtid finbl MbpModf READ_WRITE
            = nfw MbpModf("READ_WRITE");

        /**
         * Modf for b privbtf (dopy-on-writf) mbpping.
         */
        publid stbtid finbl MbpModf PRIVATE
            = nfw MbpModf("PRIVATE");

        privbtf finbl String nbmf;

        privbtf MbpModf(String nbmf) {
            tiis.nbmf = nbmf;
        }

        /**
         * Rfturns b string dfsdribing tiis filf-mbpping modf.
         *
         * @rfturn  A dfsdriptivf string
         */
        publid String toString() {
            rfturn nbmf;
        }

    }

    /**
     * Mbps b rfgion of tiis dibnnfl's filf dirfdtly into mfmory.
     *
     * <p> A rfgion of b filf mby bf mbppfd into mfmory in onf of tirff modfs:
     * </p>
     *
     * <ul>
     *
     *   <li><p> <i>Rfbd-only:</i> Any bttfmpt to modify tif rfsulting bufffr
     *   will dbusf b {@link jbvb.nio.RfbdOnlyBufffrExdfption} to bf tirown.
     *   ({@link MbpModf#READ_ONLY MbpModf.READ_ONLY}) </p></li>
     *
     *   <li><p> <i>Rfbd/writf:</i> Cibngfs mbdf to tif rfsulting bufffr will
     *   fvfntublly bf propbgbtfd to tif filf; tify mby or mby not bf mbdf
     *   visiblf to otifr progrbms tibt ibvf mbppfd tif sbmf filf.  ({@link
     *   MbpModf#READ_WRITE MbpModf.READ_WRITE}) </p></li>
     *
     *   <li><p> <i>Privbtf:</i> Cibngfs mbdf to tif rfsulting bufffr will not
     *   bf propbgbtfd to tif filf bnd will not bf visiblf to otifr progrbms
     *   tibt ibvf mbppfd tif sbmf filf; instfbd, tify will dbusf privbtf
     *   dopifs of tif modififd portions of tif bufffr to bf drfbtfd.  ({@link
     *   MbpModf#PRIVATE MbpModf.PRIVATE}) </p></li>
     *
     * </ul>
     *
     * <p> For b rfbd-only mbpping, tiis dibnnfl must ibvf bffn opfnfd for
     * rfbding; for b rfbd/writf or privbtf mbpping, tiis dibnnfl must ibvf
     * bffn opfnfd for boti rfbding bnd writing.
     *
     * <p> Tif {@link MbppfdBytfBufffr <i>mbppfd bytf bufffr</i>}
     * rfturnfd by tiis mftiod will ibvf b position of zfro bnd b limit bnd
     * dbpbdity of <tt>sizf</tt>; its mbrk will bf undffinfd.  Tif bufffr bnd
     * tif mbpping tibt it rfprfsfnts will rfmbin vblid until tif bufffr itsflf
     * is gbrbbgf-dollfdtfd.
     *
     * <p> A mbpping, ondf fstbblisifd, is not dfpfndfnt upon tif filf dibnnfl
     * tibt wbs usfd to drfbtf it.  Closing tif dibnnfl, in pbrtidulbr, ibs no
     * ffffdt upon tif vblidity of tif mbpping.
     *
     * <p> Mbny of tif dftbils of mfmory-mbppfd filfs brf inifrfntly dfpfndfnt
     * upon tif undfrlying opfrbting systfm bnd brf tifrfforf unspfdififd.  Tif
     * bfibvior of tiis mftiod wifn tif rfqufstfd rfgion is not domplftfly
     * dontbinfd witiin tiis dibnnfl's filf is unspfdififd.  Wiftifr dibngfs
     * mbdf to tif dontfnt or sizf of tif undfrlying filf, by tiis progrbm or
     * bnotifr, brf propbgbtfd to tif bufffr is unspfdififd.  Tif rbtf bt wiidi
     * dibngfs to tif bufffr brf propbgbtfd to tif filf is unspfdififd.
     *
     * <p> For most opfrbting systfms, mbpping b filf into mfmory is morf
     * fxpfnsivf tibn rfbding or writing b ffw tfns of kilobytfs of dbtb vib
     * tif usubl {@link #rfbd rfbd} bnd {@link #writf writf} mftiods.  From tif
     * stbndpoint of pfrformbndf it is gfnfrblly only worti mbpping rflbtivfly
     * lbrgf filfs into mfmory.  </p>
     *
     * @pbrbm  modf
     *         Onf of tif donstbnts {@link MbpModf#READ_ONLY READ_ONLY}, {@link
     *         MbpModf#READ_WRITE READ_WRITE}, or {@link MbpModf#PRIVATE
     *         PRIVATE} dffinfd in tif {@link MbpModf} dlbss, bddording to
     *         wiftifr tif filf is to bf mbppfd rfbd-only, rfbd/writf, or
     *         privbtfly (dopy-on-writf), rfspfdtivfly
     *
     * @pbrbm  position
     *         Tif position witiin tif filf bt wiidi tif mbppfd rfgion
     *         is to stbrt; must bf non-nfgbtivf
     *
     * @pbrbm  sizf
     *         Tif sizf of tif rfgion to bf mbppfd; must bf non-nfgbtivf bnd
     *         no grfbtfr tibn {@link jbvb.lbng.Intfgfr#MAX_VALUE}
     *
     * @rfturn  Tif mbppfd bytf bufffr
     *
     * @tirows NonRfbdbblfCibnnflExdfption
     *         If tif <tt>modf</tt> is {@link MbpModf#READ_ONLY READ_ONLY} but
     *         tiis dibnnfl wbs not opfnfd for rfbding
     *
     * @tirows NonWritbblfCibnnflExdfption
     *         If tif <tt>modf</tt> is {@link MbpModf#READ_WRITE READ_WRITE} or
     *         {@link MbpModf#PRIVATE PRIVATE} but tiis dibnnfl wbs not opfnfd
     *         for boti rfbding bnd writing
     *
     * @tirows IllfgblArgumfntExdfption
     *         If tif prfdonditions on tif pbrbmftfrs do not iold
     *
     * @tirows IOExdfption
     *         If somf otifr I/O frror oddurs
     *
     * @sff jbvb.nio.dibnnfls.FilfCibnnfl.MbpModf
     * @sff jbvb.nio.MbppfdBytfBufffr
     */
    publid bbstrbdt MbppfdBytfBufffr mbp(MbpModf modf,
                                         long position, long sizf)
        tirows IOExdfption;


    // -- Lodks --

    /**
     * Adquirfs b lodk on tif givfn rfgion of tiis dibnnfl's filf.
     *
     * <p> An invodbtion of tiis mftiod will blodk until tif rfgion dbn bf
     * lodkfd, tiis dibnnfl is dlosfd, or tif invoking tirfbd is intfrruptfd,
     * wiidifvfr domfs first.
     *
     * <p> If tiis dibnnfl is dlosfd by bnotifr tirfbd during bn invodbtion of
     * tiis mftiod tifn bn {@link AsyndironousClosfExdfption} will bf tirown.
     *
     * <p> If tif invoking tirfbd is intfrruptfd wiilf wbiting to bdquirf tif
     * lodk tifn its intfrrupt stbtus will bf sft bnd b {@link
     * FilfLodkIntfrruptionExdfption} will bf tirown.  If tif invokfr's
     * intfrrupt stbtus is sft wifn tiis mftiod is invokfd tifn tibt fxdfption
     * will bf tirown immfdibtfly; tif tirfbd's intfrrupt stbtus will not bf
     * dibngfd.
     *
     * <p> Tif rfgion spfdififd by tif <tt>position</tt> bnd <tt>sizf</tt>
     * pbrbmftfrs nffd not bf dontbinfd witiin, or fvfn ovfrlbp, tif bdtubl
     * undfrlying filf.  Lodk rfgions brf fixfd in sizf; if b lodkfd rfgion
     * initiblly dontbins tif fnd of tif filf bnd tif filf grows bfyond tif
     * rfgion tifn tif nfw portion of tif filf will not bf dovfrfd by tif lodk.
     * If b filf is fxpfdtfd to grow in sizf bnd b lodk on tif fntirf filf is
     * rfquirfd tifn b rfgion stbrting bt zfro, bnd no smbllfr tibn tif
     * fxpfdtfd mbximum sizf of tif filf, siould bf lodkfd.  Tif zfro-brgumfnt
     * {@link #lodk()} mftiod simply lodks b rfgion of sizf {@link
     * Long#MAX_VALUE}.
     *
     * <p> Somf opfrbting systfms do not support sibrfd lodks, in wiidi dbsf b
     * rfqufst for b sibrfd lodk is butombtidblly donvfrtfd into b rfqufst for
     * bn fxdlusivf lodk.  Wiftifr tif nfwly-bdquirfd lodk is sibrfd or
     * fxdlusivf mby bf tfstfd by invoking tif rfsulting lodk objfdt's {@link
     * FilfLodk#isSibrfd() isSibrfd} mftiod.
     *
     * <p> Filf lodks brf ifld on bfiblf of tif fntirf Jbvb virtubl mbdiinf.
     * Tify brf not suitbblf for dontrolling bddfss to b filf by multiplf
     * tirfbds witiin tif sbmf virtubl mbdiinf.  </p>
     *
     * @pbrbm  position
     *         Tif position bt wiidi tif lodkfd rfgion is to stbrt; must bf
     *         non-nfgbtivf
     *
     * @pbrbm  sizf
     *         Tif sizf of tif lodkfd rfgion; must bf non-nfgbtivf, bnd tif sum
     *         <tt>position</tt>&nbsp;+&nbsp;<tt>sizf</tt> must bf non-nfgbtivf
     *
     * @pbrbm  sibrfd
     *         <tt>truf</tt> to rfqufst b sibrfd lodk, in wiidi dbsf tiis
     *         dibnnfl must bf opfn for rfbding (bnd possibly writing);
     *         <tt>fblsf</tt> to rfqufst bn fxdlusivf lodk, in wiidi dbsf tiis
     *         dibnnfl must bf opfn for writing (bnd possibly rfbding)
     *
     * @rfturn  A lodk objfdt rfprfsfnting tif nfwly-bdquirfd lodk
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif prfdonditions on tif pbrbmftfrs do not iold
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  AsyndironousClosfExdfption
     *          If bnotifr tirfbd dlosfs tiis dibnnfl wiilf tif invoking
     *          tirfbd is blodkfd in tiis mftiod
     *
     * @tirows  FilfLodkIntfrruptionExdfption
     *          If tif invoking tirfbd is intfrruptfd wiilf blodkfd in tiis
     *          mftiod
     *
     * @tirows  OvfrlbppingFilfLodkExdfption
     *          If b lodk tibt ovfrlbps tif rfqufstfd rfgion is blrfbdy ifld by
     *          tiis Jbvb virtubl mbdiinf, or if bnotifr tirfbd is blrfbdy
     *          blodkfd in tiis mftiod bnd is bttfmpting to lodk bn ovfrlbpping
     *          rfgion
     *
     * @tirows  NonRfbdbblfCibnnflExdfption
     *          If <tt>sibrfd</tt> is <tt>truf</tt> tiis dibnnfl wbs not
     *          opfnfd for rfbding
     *
     * @tirows  NonWritbblfCibnnflExdfption
     *          If <tt>sibrfd</tt> is <tt>fblsf</tt> but tiis dibnnfl wbs not
     *          opfnfd for writing
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     *
     * @sff     #lodk()
     * @sff     #tryLodk()
     * @sff     #tryLodk(long,long,boolfbn)
     */
    publid bbstrbdt FilfLodk lodk(long position, long sizf, boolfbn sibrfd)
        tirows IOExdfption;

    /**
     * Adquirfs bn fxdlusivf lodk on tiis dibnnfl's filf.
     *
     * <p> An invodbtion of tiis mftiod of tif form <tt>fd.lodk()</tt> bfibvfs
     * in fxbdtly tif sbmf wby bs tif invodbtion
     *
     * <prf>
     *     fd.{@link #lodk(long,long,boolfbn) lodk}(0L, Long.MAX_VALUE, fblsf) </prf>
     *
     * @rfturn  A lodk objfdt rfprfsfnting tif nfwly-bdquirfd lodk
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  AsyndironousClosfExdfption
     *          If bnotifr tirfbd dlosfs tiis dibnnfl wiilf tif invoking
     *          tirfbd is blodkfd in tiis mftiod
     *
     * @tirows  FilfLodkIntfrruptionExdfption
     *          If tif invoking tirfbd is intfrruptfd wiilf blodkfd in tiis
     *          mftiod
     *
     * @tirows  OvfrlbppingFilfLodkExdfption
     *          If b lodk tibt ovfrlbps tif rfqufstfd rfgion is blrfbdy ifld by
     *          tiis Jbvb virtubl mbdiinf, or if bnotifr tirfbd is blrfbdy
     *          blodkfd in tiis mftiod bnd is bttfmpting to lodk bn ovfrlbpping
     *          rfgion of tif sbmf filf
     *
     * @tirows  NonWritbblfCibnnflExdfption
     *          If tiis dibnnfl wbs not opfnfd for writing
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     *
     * @sff     #lodk(long,long,boolfbn)
     * @sff     #tryLodk()
     * @sff     #tryLodk(long,long,boolfbn)
     */
    publid finbl FilfLodk lodk() tirows IOExdfption {
        rfturn lodk(0L, Long.MAX_VALUE, fblsf);
    }

    /**
     * Attfmpts to bdquirf b lodk on tif givfn rfgion of tiis dibnnfl's filf.
     *
     * <p> Tiis mftiod dofs not blodk.  An invodbtion blwbys rfturns
     * immfdibtfly, fitifr ibving bdquirfd b lodk on tif rfqufstfd rfgion or
     * ibving fbilfd to do so.  If it fbils to bdquirf b lodk bfdbusf bn
     * ovfrlbpping lodk is ifld by bnotifr progrbm tifn it rfturns
     * <tt>null</tt>.  If it fbils to bdquirf b lodk for bny otifr rfbson tifn
     * bn bppropribtf fxdfption is tirown.
     *
     * <p> Tif rfgion spfdififd by tif <tt>position</tt> bnd <tt>sizf</tt>
     * pbrbmftfrs nffd not bf dontbinfd witiin, or fvfn ovfrlbp, tif bdtubl
     * undfrlying filf.  Lodk rfgions brf fixfd in sizf; if b lodkfd rfgion
     * initiblly dontbins tif fnd of tif filf bnd tif filf grows bfyond tif
     * rfgion tifn tif nfw portion of tif filf will not bf dovfrfd by tif lodk.
     * If b filf is fxpfdtfd to grow in sizf bnd b lodk on tif fntirf filf is
     * rfquirfd tifn b rfgion stbrting bt zfro, bnd no smbllfr tibn tif
     * fxpfdtfd mbximum sizf of tif filf, siould bf lodkfd.  Tif zfro-brgumfnt
     * {@link #tryLodk()} mftiod simply lodks b rfgion of sizf {@link
     * Long#MAX_VALUE}.
     *
     * <p> Somf opfrbting systfms do not support sibrfd lodks, in wiidi dbsf b
     * rfqufst for b sibrfd lodk is butombtidblly donvfrtfd into b rfqufst for
     * bn fxdlusivf lodk.  Wiftifr tif nfwly-bdquirfd lodk is sibrfd or
     * fxdlusivf mby bf tfstfd by invoking tif rfsulting lodk objfdt's {@link
     * FilfLodk#isSibrfd() isSibrfd} mftiod.
     *
     * <p> Filf lodks brf ifld on bfiblf of tif fntirf Jbvb virtubl mbdiinf.
     * Tify brf not suitbblf for dontrolling bddfss to b filf by multiplf
     * tirfbds witiin tif sbmf virtubl mbdiinf.  </p>
     *
     * @pbrbm  position
     *         Tif position bt wiidi tif lodkfd rfgion is to stbrt; must bf
     *         non-nfgbtivf
     *
     * @pbrbm  sizf
     *         Tif sizf of tif lodkfd rfgion; must bf non-nfgbtivf, bnd tif sum
     *         <tt>position</tt>&nbsp;+&nbsp;<tt>sizf</tt> must bf non-nfgbtivf
     *
     * @pbrbm  sibrfd
     *         <tt>truf</tt> to rfqufst b sibrfd lodk,
     *         <tt>fblsf</tt> to rfqufst bn fxdlusivf lodk
     *
     * @rfturn  A lodk objfdt rfprfsfnting tif nfwly-bdquirfd lodk,
     *          or <tt>null</tt> if tif lodk dould not bf bdquirfd
     *          bfdbusf bnotifr progrbm iolds bn ovfrlbpping lodk
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif prfdonditions on tif pbrbmftfrs do not iold
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  OvfrlbppingFilfLodkExdfption
     *          If b lodk tibt ovfrlbps tif rfqufstfd rfgion is blrfbdy ifld by
     *          tiis Jbvb virtubl mbdiinf, or if bnotifr tirfbd is blrfbdy
     *          blodkfd in tiis mftiod bnd is bttfmpting to lodk bn ovfrlbpping
     *          rfgion of tif sbmf filf
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     *
     * @sff     #lodk()
     * @sff     #lodk(long,long,boolfbn)
     * @sff     #tryLodk()
     */
    publid bbstrbdt FilfLodk tryLodk(long position, long sizf, boolfbn sibrfd)
        tirows IOExdfption;

    /**
     * Attfmpts to bdquirf bn fxdlusivf lodk on tiis dibnnfl's filf.
     *
     * <p> An invodbtion of tiis mftiod of tif form <tt>fd.tryLodk()</tt>
     * bfibvfs in fxbdtly tif sbmf wby bs tif invodbtion
     *
     * <prf>
     *     fd.{@link #tryLodk(long,long,boolfbn) tryLodk}(0L, Long.MAX_VALUE, fblsf) </prf>
     *
     * @rfturn  A lodk objfdt rfprfsfnting tif nfwly-bdquirfd lodk,
     *          or <tt>null</tt> if tif lodk dould not bf bdquirfd
     *          bfdbusf bnotifr progrbm iolds bn ovfrlbpping lodk
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  OvfrlbppingFilfLodkExdfption
     *          If b lodk tibt ovfrlbps tif rfqufstfd rfgion is blrfbdy ifld by
     *          tiis Jbvb virtubl mbdiinf, or if bnotifr tirfbd is blrfbdy
     *          blodkfd in tiis mftiod bnd is bttfmpting to lodk bn ovfrlbpping
     *          rfgion
     *
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     *
     * @sff     #lodk()
     * @sff     #lodk(long,long,boolfbn)
     * @sff     #tryLodk(long,long,boolfbn)
     */
    publid finbl FilfLodk tryLodk() tirows IOExdfption {
        rfturn tryLodk(0L, Long.MAX_VALUE, fblsf);
    }

}
