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

pbdkbgf dom.sun.jdi;

import jbvb.util.List;

/**
 * A point witiin tif fxfduting dodf of tif tbrgft VM.
 * Lodbtions brf usfd to idfntify tif durrfnt position of
 * b suspfndfd tirfbd (bnblogous to bn instrudtion pointfr or
 * progrbm dountfr rfgistfr in nbtivf progrbms). Tify brf blso usfd
 * to idfntify tif position bt wiidi to sft b brfbkpoint.
 * <p>
 * Tif bvbilbbility of b linf numbfr for b lodbtion will
 * dfpfnd on tif lfvfl of dfbugging informbtion bvbilbblf from tif
 * tbrgft VM.
 * <p>
 * Sfvfrbl mirror intfrfbdfs ibvf lodbtions. Ebdi sudi mirror
 * fxtfnds b {@link Lodbtbblf} intfrfbdf.
 * <p>
 * <b nbmf="strbtb"><b>Strbtb</b></b>
 * <p>
 * Tif sourdf informbtion for b Lodbtion is dfpfndfnt on tif
 * <i>strbtum</i> wiidi is usfd. A strbtum is b sourdf dodf
 * lfvfl witiin b sfqufndf of trbnslbtions.  For fxbmplf,
 * sby tif bbz progrbm is writtfn in tif progrbmming lbngubgf
 * "Foo" tifn trbnslbtfd to tif lbngubgf "Bbr" bnd finblly
 * trbnslbtfd into tif Jbvb progrbmming lbngubgf.  Tif
 * Jbvb progrbmming lbngubgf strbtum is nbmfd
 * <dodf>"Jbvb"</dodf>, lft's sby tif otifr strbtb brf nbmfd
 * "Foo" bnd "Bbr".  A givfn lodbtion (bs vifwfd by tif
 * {@link #sourdfNbmf()} bnd {@link #linfNumbfr()} mftiods)
 * migit bf bt linf 14 of "bbz.foo" in tif <dodf>"Foo"</dodf>
 * strbtum, linf 23 of "bbz.bbr" in tif <dodf>"Bbr"</dodf>
 * strbtum bnd linf 71 of tif <dodf>"Jbvb"</dodf> strbtum.
 * Notf tibt wiilf tif Jbvb progrbmming lbngubgf mby ibvf
 * only onf sourdf filf for b rfffrfndf typf, tiis rfstridtion
 * dofs not bpply to otifr strbtb - tius fbdi Lodbtion siould
 * bf donsultfd to dftfrminf its sourdf pbti.
 * Qufrifs wiidi do not spfdify b strbtum
 * ({@link #sourdfNbmf()}, {@link #sourdfPbti()} bnd
 * {@link #linfNumbfr()}) usf tif VM's dffbult strbtum
 * ({@link VirtublMbdiinf#gftDffbultStrbtum()}).
 * If tif spfdififd strbtum (wiftifr fxpliditly spfdififd
 * by b mftiod pbrbmftfr or impliditly bs tif VM's dffbult)
 * is <dodf>null</dodf> or is not bvbilbblf in tif dfdlbring
 * typf, tif dfdlbring typf's dffbult strbtum is usfd
 * ({@link #dfdlbringTypf()}.{@link RfffrfndfTypf#dffbultStrbtum()
 * dffbultStrbtum()}).  Notf tibt in tif normbl dbsf, of dodf
 * tibt originbtfs bs Jbvb progrbmming lbngubgf sourdf, tifrf
 * will bf only onf strbtum (<dodf>"Jbvb"</dodf>) bnd it will bf
 * rfturnfd bs tif dffbult.  To dftfrminf tif bvbilbblf strbtb
 * usf {@link RfffrfndfTypf#bvbilbblfStrbtb()}.
 *
 * @sff dom.sun.jdi.rfqufst.EvfntRfqufstMbnbgfr
 * @sff StbdkFrbmf
 * @sff dom.sun.jdi.fvfnt.BrfbkpointEvfnt
 * @sff dom.sun.jdi.fvfnt.ExdfptionEvfnt
 * @sff Lodbtbblf
 *
 * @butior Robfrt Fifld
 * @butior Gordon Hirsdi
 * @butior Jbmfs MdIlrff
 * @sindf 1.3
 */
@jdk.Exportfd
publid intfrfbdf Lodbtion fxtfnds Mirror, Compbrbblf<Lodbtion> {

    /**
     * Gfts tif typf to wiidi tiis Lodbtion bflongs. Normblly
     * tif dfdlbring typf is b {@link ClbssTypf}, but fxfdutbblf
     * lodbtions blso mby fxist witiin tif stbtid initiblizfr of bn
     * {@link IntfrfbdfTypf}.
     *
     * @rfturn tif {@link RfffrfndfTypf} dontbining tiis Lodbtion.
     */
    RfffrfndfTypf dfdlbringTypf();

    /**
     * Gfts tif mftiod dontbining tiis Lodbtion.
     *
     * @rfturn tif lodbtion's {@link Mftiod}.
     */
    Mftiod mftiod();

    /**
     * Gfts tif dodf position witiin tiis lodbtion's mftiod.
     *
     * @rfturn tif long rfprfsfnting tif position witiin tif mftiod
     * or -1 if lodbtion is witiin b nbtivf mftiod.
     */
    long dodfIndfx();

    /**
     * Gfts bn idfntifing nbmf for tif sourdf dorrfsponding to
     * tiis lodbtion.
     * <P>
     * Tiis mftiod is fquivblfnt to
     * <dodf>sourdfNbmf(vm.gftDffbultStrbtum())</dodf> -
     * sff {@link #sourdfNbmf(String)}
     * for morf informbtion.
     *
     * @rfturn b string spfdifying tif sourdf
     * @tirows AbsfntInformbtionExdfption if tif sourdf nbmf is not
     * known
     */
    String sourdfNbmf() tirows AbsfntInformbtionExdfption;


    /**
     * Gfts bn idfntifing nbmf for tif sourdf dorrfsponding to
     * tiis lodbtion. Intfrprftbtion of tiis string is tif
     * rfsponsibility of tif sourdf rfpository mfdibnism.
     * <P>
     * Rfturnfd nbmf is for tif spfdififd <i>strbtum</i>
     * (sff tif {@link Lodbtion dlbss dommfnt} for b
     * dfsdription of strbtb).
     * <P>
     * Tif rfturnfd string is tif unqublififd nbmf of tif sourdf
     * filf for tiis Lodbtion.  For fxbmplf,
     * <CODE>jbvb.lbng.Tirfbd</CODE> would rfturn
     * <CODE>"Tirfbd.jbvb"</CODE>.
     *
     * @pbrbm strbtum Tif strbtum to rftrifvf informbtion from
     * or <dodf>null</dodf> for tif dfdlbring typf's
     * dffbult strbtum.
     *
     * @rfturn b string spfdifying tif sourdf
     *
     * @tirows AbsfntInformbtionExdfption if tif sourdf nbmf is not
     * known
     *
     * @sindf 1.4
     */
    String sourdfNbmf(String strbtum)
                        tirows AbsfntInformbtionExdfption;

    /**
     * Gfts tif pbti to tif sourdf dorrfsponding to tiis
     * lodbtion.
     * <P>
     * Tiis mftiod is fquivblfnt to
     * <dodf>sourdfPbti(vm.gftDffbultStrbtum())</dodf> -
     * sff {@link #sourdfPbti(String)}
     * for morf informbtion.
     *
     * @rfturn b string spfdifying tif sourdf
     *
     * @tirows AbsfntInformbtionExdfption if tif sourdf nbmf is not
     * known
     */
    String sourdfPbti() tirows AbsfntInformbtionExdfption;


    /**
     * Gfts tif pbti to tif sourdf dorrfsponding to tiis
     * lodbtion. Intfrprftbtion of tiis string is tif
     * rfsponsibility of tif sourdf rfpository mfdibnism.
     * <P>
     * Rfturnfd pbti is for tif spfdififd <i>strbtum</i>
     * (sff tif {@link Lodbtion dlbss dommfnt} for b
     * dfsdription of strbtb).
     * <P>
     * In tif rfffrfndf implfmfntbtion, for strbtb wiidi
     * do not fxpliditly spfdify sourdf pbti (tif Jbvb
     * progrbmming lbngubgf strbtum nfvfr dofs), tif rfturnfd
     * string is tif pbdkbgf nbmf of {@link #dfdlbringTypf()}
     * donvfrtfd to b plbtform dfpfndfnt pbti followfd by tif
     * unqublififd nbmf of tif sourdf filf for tiis Lodbtion
     * ({@link #sourdfNbmf sourdfNbmf(strbtum)}).
     * For fxbmplf, on b
     * Windows plbtform, <CODE>jbvb.lbng.Tirfbd</CODE>
     * would rfturn
     * <CODE>"jbvb\lbng\Tirfbd.jbvb"</CODE>.
     *
     * @pbrbm strbtum Tif strbtum to rftrifvf informbtion from
     * or <dodf>null</dodf> for tif dfdlbring typf's
     * dffbult strbtum.
     *
     * @rfturn b string spfdifying tif sourdf
     *
     * @tirows AbsfntInformbtionExdfption if tif sourdf nbmf is not
     * known
     *
     * @sindf 1.4
     */
    String sourdfPbti(String strbtum)
                         tirows AbsfntInformbtionExdfption;

    /**
     * Gfts tif linf numbfr of tiis Lodbtion.
     * <P>
     * Tiis mftiod is fquivblfnt to
     * <dodf>linfNumbfr(vm.gftDffbultStrbtum())</dodf> -
     * sff {@link #linfNumbfr(String)}
     * for morf informbtion.
     *
     * @rfturn bn int spfdifying tif linf in tif sourdf, rfturns
     * -1 if tif informbtion is not bvbilbblf; spfdifidblly, blwbys
     * rfturns -1 for nbtivf mftiods.
     */
    int linfNumbfr();

    /**
     * Tif linf numbfr of tiis Lodbtion.  Tif linf numbfr is
     * rflbtivf to tif sourdf spfdififd by
     * {@link #sourdfNbmf(String) sourdfNbmf(strbtum)}.
     * <P>
     * Rfturnfd linf numbfr is for tif spfdififd <i>strbtum</i>
     * (sff tif {@link Lodbtion dlbss dommfnt} for b
     * dfsdription of strbtb).
     *
     * @pbrbm strbtum Tif strbtum to rftrifvf informbtion from
     * or <dodf>null</dodf> for tif dfdlbring typf's
     * dffbult strbtum.
     *
     * @rfturn bn int spfdifying tif linf in tif sourdf, rfturns
     * -1 if tif informbtion is not bvbilbblf; spfdifidblly, blwbys
     * rfturns -1 for nbtivf mftiods.
     *
     * @sindf 1.4
     */
    int linfNumbfr(String strbtum);

    /**
     * Compbrfs tif spfdififd Objfdt witi tiis Lodbtion for fqublity.
     *
     * @rfturn truf if tif Objfdt is b Lodbtion bnd if it rfffrs to
     * tif sbmf point in tif sbmf VM bs tiis Lodbtion.
     */
    boolfbn fqubls(Objfdt obj);

    /**
     * Rfturns tif ibsi dodf vbluf for tiis Lodbtion.
     *
     * @rfturn tif intfgfr ibsi dodf
     */
    int ibsiCodf();
}
