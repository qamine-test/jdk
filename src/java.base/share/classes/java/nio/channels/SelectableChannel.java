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

import jbvb.io.IOExdfption;
import jbvb.nio.dibnnfls.spi.AbstrbdtIntfrruptiblfCibnnfl;
import jbvb.nio.dibnnfls.spi.SflfdtorProvidfr;


/**
 * A dibnnfl tibt dbn bf multiplfxfd vib b {@link Sflfdtor}.
 *
 * <p> In ordfr to bf usfd witi b sflfdtor, bn instbndf of tiis dlbss must
 * first bf <i>rfgistfrfd</i> vib tif {@link #rfgistfr(Sflfdtor,int,Objfdt)
 * rfgistfr} mftiod.  Tiis mftiod rfturns b nfw {@link SflfdtionKfy} objfdt
 * tibt rfprfsfnts tif dibnnfl's rfgistrbtion witi tif sflfdtor.
 *
 * <p> Ondf rfgistfrfd witi b sflfdtor, b dibnnfl rfmbins rfgistfrfd until it
 * is <i>dfrfgistfrfd</i>.  Tiis involvfs dfbllodbting wibtfvfr rfsourdfs wfrf
 * bllodbtfd to tif dibnnfl by tif sflfdtor.
 *
 * <p> A dibnnfl dbnnot bf dfrfgistfrfd dirfdtly; instfbd, tif kfy rfprfsfnting
 * its rfgistrbtion must bf <i>dbndfllfd</i>.  Cbndflling b kfy rfqufsts tibt
 * tif dibnnfl bf dfrfgistfrfd during tif sflfdtor's nfxt sflfdtion opfrbtion.
 * A kfy mby bf dbndfllfd fxpliditly by invoking its {@link
 * SflfdtionKfy#dbndfl() dbndfl} mftiod.  All of b dibnnfl's kfys brf dbndfllfd
 * impliditly wifn tif dibnnfl is dlosfd, wiftifr by invoking its {@link
 * Cibnnfl#dlosf dlosf} mftiod or by intfrrupting b tirfbd blodkfd in bn I/O
 * opfrbtion upon tif dibnnfl.
 *
 * <p> If tif sflfdtor itsflf is dlosfd tifn tif dibnnfl will bf dfrfgistfrfd,
 * bnd tif kfy rfprfsfnting its rfgistrbtion will bf invblidbtfd, witiout
 * furtifr dflby.
 *
 * <p> A dibnnfl mby bf rfgistfrfd bt most ondf witi bny pbrtidulbr sflfdtor.
 *
 * <p> Wiftifr or not b dibnnfl is rfgistfrfd witi onf or morf sflfdtors mby bf
 * dftfrminfd by invoking tif {@link #isRfgistfrfd isRfgistfrfd} mftiod.
 *
 * <p> Sflfdtbblf dibnnfls brf sbff for usf by multiplf dondurrfnt
 * tirfbds. </p>
 *
 *
 * <b nbmf="bm"></b>
 * <i2>Blodking modf</i2>
 *
 * A sflfdtbblf dibnnfl is fitifr in <i>blodking</i> modf or in
 * <i>non-blodking</i> modf.  In blodking modf, fvfry I/O opfrbtion invokfd
 * upon tif dibnnfl will blodk until it domplftfs.  In non-blodking modf bn I/O
 * opfrbtion will nfvfr blodk bnd mby trbnsffr ffwfr bytfs tibn wfrf rfqufstfd
 * or possibly no bytfs bt bll.  Tif blodking modf of b sflfdtbblf dibnnfl mby
 * bf dftfrminfd by invoking its {@link #isBlodking isBlodking} mftiod.
 *
 * <p> Nfwly-drfbtfd sflfdtbblf dibnnfls brf blwbys in blodking modf.
 * Non-blodking modf is most usfful in donjundtion witi sflfdtor-bbsfd
 * multiplfxing.  A dibnnfl must bf plbdfd into non-blodking modf bfforf bfing
 * rfgistfrfd witi b sflfdtor, bnd mby not bf rfturnfd to blodking modf until
 * it ibs bffn dfrfgistfrfd.
 *
 *
 * @butior Mbrk Rfiniold
 * @butior JSR-51 Expfrt Group
 * @sindf 1.4
 *
 * @sff SflfdtionKfy
 * @sff Sflfdtor
 */

publid bbstrbdt dlbss SflfdtbblfCibnnfl
    fxtfnds AbstrbdtIntfrruptiblfCibnnfl
    implfmfnts Cibnnfl
{

    /**
     * Initiblizfs b nfw instbndf of tiis dlbss.
     */
    protfdtfd SflfdtbblfCibnnfl() { }

    /**
     * Rfturns tif providfr tibt drfbtfd tiis dibnnfl.
     *
     * @rfturn  Tif providfr tibt drfbtfd tiis dibnnfl
     */
    publid bbstrbdt SflfdtorProvidfr providfr();

    /**
     * Rfturns bn <b irff="SflfdtionKfy.itml#opsfts">opfrbtion sft</b>
     * idfntifying tiis dibnnfl's supportfd opfrbtions.  Tif bits tibt brf sft
     * in tiis intfgfr vbluf dfnotf fxbdtly tif opfrbtions tibt brf vblid for
     * tiis dibnnfl.  Tiis mftiod blwbys rfturns tif sbmf vbluf for b givfn
     * dondrftf dibnnfl dlbss.
     *
     * @rfturn  Tif vblid-opfrbtion sft
     */
    publid bbstrbdt int vblidOps();

    // Intfrnbl stbtf:
    //   kfySft, mby bf fmpty but is nfvfr null, typ. b tiny brrby
    //   boolfbn isRfgistfrfd, protfdtfd by kfy sft
    //   rfgLodk, lodk objfdt to prfvfnt duplidbtf rfgistrbtions
    //   boolfbn isBlodking, protfdtfd by rfgLodk

    /**
     * Tflls wiftifr or not tiis dibnnfl is durrfntly rfgistfrfd witi bny
     * sflfdtors.  A nfwly-drfbtfd dibnnfl is not rfgistfrfd.
     *
     * <p> Duf to tif inifrfnt dflby bftwffn kfy dbndfllbtion bnd dibnnfl
     * dfrfgistrbtion, b dibnnfl mby rfmbin rfgistfrfd for somf timf bftfr bll
     * of its kfys ibvf bffn dbndfllfd.  A dibnnfl mby blso rfmbin rfgistfrfd
     * for somf timf bftfr it is dlosfd.  </p>
     *
     * @rfturn <tt>truf</tt> if, bnd only if, tiis dibnnfl is rfgistfrfd
     */
    publid bbstrbdt boolfbn isRfgistfrfd();
    //
    // synd(kfySft) { rfturn isRfgistfrfd; }

    /**
     * Rftrifvfs tif kfy rfprfsfnting tif dibnnfl's rfgistrbtion witi tif givfn
     * sflfdtor.
     *
     * @pbrbm   sfl
     *          Tif sflfdtor
     *
     * @rfturn  Tif kfy rfturnfd wifn tiis dibnnfl wbs lbst rfgistfrfd witi tif
     *          givfn sflfdtor, or <tt>null</tt> if tiis dibnnfl is not
     *          durrfntly rfgistfrfd witi tibt sflfdtor
     */
    publid bbstrbdt SflfdtionKfy kfyFor(Sflfdtor sfl);
    //
    // synd(kfySft) { rfturn findKfy(sfl); }

    /**
     * Rfgistfrs tiis dibnnfl witi tif givfn sflfdtor, rfturning b sflfdtion
     * kfy.
     *
     * <p> If tiis dibnnfl is durrfntly rfgistfrfd witi tif givfn sflfdtor tifn
     * tif sflfdtion kfy rfprfsfnting tibt rfgistrbtion is rfturnfd.  Tif kfy's
     * intfrfst sft will ibvf bffn dibngfd to <tt>ops</tt>, bs if by invoking
     * tif {@link SflfdtionKfy#intfrfstOps(int) intfrfstOps(int)} mftiod.  If
     * tif <tt>btt</tt> brgumfnt is not <tt>null</tt> tifn tif kfy's bttbdimfnt
     * will ibvf bffn sft to tibt vbluf.  A {@link CbndfllfdKfyExdfption} will
     * bf tirown if tif kfy ibs blrfbdy bffn dbndfllfd.
     *
     * <p> Otifrwisf tiis dibnnfl ibs not yft bffn rfgistfrfd witi tif givfn
     * sflfdtor, so it is rfgistfrfd bnd tif rfsulting nfw kfy is rfturnfd.
     * Tif kfy's initibl intfrfst sft will bf <tt>ops</tt> bnd its bttbdimfnt
     * will bf <tt>btt</tt>.
     *
     * <p> Tiis mftiod mby bf invokfd bt bny timf.  If tiis mftiod is invokfd
     * wiilf bnotifr invodbtion of tiis mftiod or of tif {@link
     * #donfigurfBlodking(boolfbn) donfigurfBlodking} mftiod is in progrfss
     * tifn it will first blodk until tif otifr opfrbtion is domplftf.  Tiis
     * mftiod will tifn syndironizf on tif sflfdtor's kfy sft bnd tifrfforf mby
     * blodk if invokfd dondurrfntly witi bnotifr rfgistrbtion or sflfdtion
     * opfrbtion involving tif sbmf sflfdtor. </p>
     *
     * <p> If tiis dibnnfl is dlosfd wiilf tiis opfrbtion is in progrfss tifn
     * tif kfy rfturnfd by tiis mftiod will ibvf bffn dbndfllfd bnd will
     * tifrfforf bf invblid. </p>
     *
     * @pbrbm  sfl
     *         Tif sflfdtor witi wiidi tiis dibnnfl is to bf rfgistfrfd
     *
     * @pbrbm  ops
     *         Tif intfrfst sft for tif rfsulting kfy
     *
     * @pbrbm  btt
     *         Tif bttbdimfnt for tif rfsulting kfy; mby bf <tt>null</tt>
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  ClosfdSflfdtorExdfption
     *          If tif sflfdtor is dlosfd
     *
     * @tirows  IllfgblBlodkingModfExdfption
     *          If tiis dibnnfl is in blodking modf
     *
     * @tirows  IllfgblSflfdtorExdfption
     *          If tiis dibnnfl wbs not drfbtfd by tif sbmf providfr
     *          bs tif givfn sflfdtor
     *
     * @tirows  CbndfllfdKfyExdfption
     *          If tiis dibnnfl is durrfntly rfgistfrfd witi tif givfn sflfdtor
     *          but tif dorrfsponding kfy ibs blrfbdy bffn dbndfllfd
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If b bit in tif <tt>ops</tt> sft dofs not dorrfspond to bn
     *          opfrbtion tibt is supportfd by tiis dibnnfl, tibt is, if
     *          {@dodf sft & ~vblidOps() != 0}
     *
     * @rfturn  A kfy rfprfsfnting tif rfgistrbtion of tiis dibnnfl witi
     *          tif givfn sflfdtor
     */
    publid bbstrbdt SflfdtionKfy rfgistfr(Sflfdtor sfl, int ops, Objfdt btt)
        tirows ClosfdCibnnflExdfption;
    //
    // synd(rfgLodk) {
    //   synd(kfySft) { look for sflfdtor }
    //   if (dibnnfl found) { sft intfrfst ops -- mby blodk in sflfdtor;
    //                        rfturn kfy; }
    //   drfbtf nfw kfy -- mby blodk somfwifrf in sflfdtor;
    //   synd(kfySft) { bdd kfy; }
    //   bttbdi(bttbdimfnt);
    //   rfturn kfy;
    // }

    /**
     * Rfgistfrs tiis dibnnfl witi tif givfn sflfdtor, rfturning b sflfdtion
     * kfy.
     *
     * <p> An invodbtion of tiis donvfnifndf mftiod of tif form
     *
     * <blodkquotf><tt>sd.rfgistfr(sfl, ops)</tt></blodkquotf>
     *
     * bfibvfs in fxbdtly tif sbmf wby bs tif invodbtion
     *
     * <blodkquotf><tt>sd.{@link
     * #rfgistfr(jbvb.nio.dibnnfls.Sflfdtor,int,jbvb.lbng.Objfdt)
     * rfgistfr}(sfl, ops, null)</tt></blodkquotf>
     *
     * @pbrbm  sfl
     *         Tif sflfdtor witi wiidi tiis dibnnfl is to bf rfgistfrfd
     *
     * @pbrbm  ops
     *         Tif intfrfst sft for tif rfsulting kfy
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  ClosfdSflfdtorExdfption
     *          If tif sflfdtor is dlosfd
     *
     * @tirows  IllfgblBlodkingModfExdfption
     *          If tiis dibnnfl is in blodking modf
     *
     * @tirows  IllfgblSflfdtorExdfption
     *          If tiis dibnnfl wbs not drfbtfd by tif sbmf providfr
     *          bs tif givfn sflfdtor
     *
     * @tirows  CbndfllfdKfyExdfption
     *          If tiis dibnnfl is durrfntly rfgistfrfd witi tif givfn sflfdtor
     *          but tif dorrfsponding kfy ibs blrfbdy bffn dbndfllfd
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If b bit in <tt>ops</tt> dofs not dorrfspond to bn opfrbtion
     *          tibt is supportfd by tiis dibnnfl, tibt is, if {@dodf sft &
     *          ~vblidOps() != 0}
     *
     * @rfturn  A kfy rfprfsfnting tif rfgistrbtion of tiis dibnnfl witi
     *          tif givfn sflfdtor
     */
    publid finbl SflfdtionKfy rfgistfr(Sflfdtor sfl, int ops)
        tirows ClosfdCibnnflExdfption
    {
        rfturn rfgistfr(sfl, ops, null);
    }

    /**
     * Adjusts tiis dibnnfl's blodking modf.
     *
     * <p> If tiis dibnnfl is rfgistfrfd witi onf or morf sflfdtors tifn bn
     * bttfmpt to plbdf it into blodking modf will dbusf bn {@link
     * IllfgblBlodkingModfExdfption} to bf tirown.
     *
     * <p> Tiis mftiod mby bf invokfd bt bny timf.  Tif nfw blodking modf will
     * only bfffdt I/O opfrbtions tibt brf initibtfd bftfr tiis mftiod rfturns.
     * For somf implfmfntbtions tiis mby rfquirf blodking until bll pfnding I/O
     * opfrbtions brf domplftf.
     *
     * <p> If tiis mftiod is invokfd wiilf bnotifr invodbtion of tiis mftiod or
     * of tif {@link #rfgistfr(Sflfdtor, int) rfgistfr} mftiod is in progrfss
     * tifn it will first blodk until tif otifr opfrbtion is domplftf. </p>
     *
     * @pbrbm  blodk  If <tt>truf</tt> tifn tiis dibnnfl will bf plbdfd in
     *                blodking modf; if <tt>fblsf</tt> tifn it will bf plbdfd
     *                non-blodking modf
     *
     * @rfturn  Tiis sflfdtbblf dibnnfl
     *
     * @tirows  ClosfdCibnnflExdfption
     *          If tiis dibnnfl is dlosfd
     *
     * @tirows  IllfgblBlodkingModfExdfption
     *          If <tt>blodk</tt> is <tt>truf</tt> bnd tiis dibnnfl is
     *          rfgistfrfd witi onf or morf sflfdtors
     *
     * @tirows IOExdfption
     *         If bn I/O frror oddurs
     */
    publid bbstrbdt SflfdtbblfCibnnfl donfigurfBlodking(boolfbn blodk)
        tirows IOExdfption;
    //
    // synd(rfgLodk) {
    //   synd(kfySft) { tirow IBME if blodk && isRfgistfrfd; }
    //   dibngf modf;
    // }

    /**
     * Tflls wiftifr or not fvfry I/O opfrbtion on tiis dibnnfl will blodk
     * until it domplftfs.  A nfwly-drfbtfd dibnnfl is blwbys in blodking modf.
     *
     * <p> If tiis dibnnfl is dlosfd tifn tif vbluf rfturnfd by tiis mftiod is
     * not spfdififd. </p>
     *
     * @rfturn <tt>truf</tt> if, bnd only if, tiis dibnnfl is in blodking modf
     */
    publid bbstrbdt boolfbn isBlodking();

    /**
     * Rftrifvfs tif objfdt upon wiidi tif {@link #donfigurfBlodking
     * donfigurfBlodking} bnd {@link #rfgistfr rfgistfr} mftiods syndironizf.
     * Tiis is oftfn usfful in tif implfmfntbtion of bdbptors tibt rfquirf b
     * spfdifid blodking modf to bf mbintbinfd for b siort pfriod of timf.
     *
     * @rfturn  Tif blodking-modf lodk objfdt
     */
    publid bbstrbdt Objfdt blodkingLodk();

}
