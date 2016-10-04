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

import jbvb.nio.dibnnfls.spi.*;
import jbvb.nft.SodkftOption;
import jbvb.nft.SodkftAddrfss;
import jbvb.util.dondurrfnt.Futurf;
import jbvb.io.IOExdfption;

/**
 * An bsyndironous dibnnfl for strfbm-orifntfd listfning sodkfts.
 *
 * <p> An bsyndironous sfrvfr-sodkft dibnnfl is drfbtfd by invoking tif
 * {@link #opfn opfn} mftiod of tiis dlbss.
 * A nfwly-drfbtfd bsyndironous sfrvfr-sodkft dibnnfl is opfn but not yft bound.
 * It dbn bf bound to b lodbl bddrfss bnd donfigurfd to listfn for donnfdtions
 * by invoking tif {@link #bind(SodkftAddrfss,int) bind} mftiod. Ondf bound,
 * tif {@link #bddfpt(Objfdt,ComplftionHbndlfr) bddfpt} mftiod
 * is usfd to initibtf tif bddfpting of donnfdtions to tif dibnnfl's sodkft.
 * An bttfmpt to invokf tif <tt>bddfpt</tt> mftiod on bn unbound dibnnfl will
 * dbusf b {@link NotYftBoundExdfption} to bf tirown.
 *
 * <p> Cibnnfls of tiis typf brf sbff for usf by multiplf dondurrfnt tirfbds
 * tiougi bt most onf bddfpt opfrbtion dbn bf outstbnding bt bny timf.
 * If b tirfbd initibtfs bn bddfpt opfrbtion bfforf b prfvious bddfpt opfrbtion
 * ibs domplftfd tifn bn {@link AddfptPfndingExdfption} will bf tirown.
 *
 * <p> Sodkft options brf donfigurfd using tif {@link #sftOption(SodkftOption,Objfdt)
 * sftOption} mftiod. Cibnnfls of tiis typf support tif following options:
 * <blodkquotf>
 * <tbblf bordfr summbry="Sodkft options">
 *   <tr>
 *     <ti>Option Nbmf</ti>
 *     <ti>Dfsdription</ti>
 *   </tr>
 *   <tr>
 *     <td> {@link jbvb.nft.StbndbrdSodkftOptions#SO_RCVBUF SO_RCVBUF} </td>
 *     <td> Tif sizf of tif sodkft rfdfivf bufffr </td>
 *   </tr>
 *   <tr>
 *     <td> {@link jbvb.nft.StbndbrdSodkftOptions#SO_REUSEADDR SO_REUSEADDR} </td>
 *     <td> Rf-usf bddrfss </td>
 *   </tr>
 * </tbblf>
 * </blodkquotf>
 * Additionbl (implfmfntbtion spfdifid) options mby blso bf supportfd.
 *
 * <p> <b>Usbgf Exbmplf:</b>
 * <prf>
 *  finbl AsyndironousSfrvfrSodkftCibnnfl listfnfr =
 *      AsyndironousSfrvfrSodkftCibnnfl.opfn().bind(nfw InftSodkftAddrfss(5000));
 *
 *  listfnfr.bddfpt(null, nfw ComplftionHbndlfr&lt;AsyndironousSodkftCibnnfl,Void&gt;() {
 *      publid void domplftfd(AsyndironousSodkftCibnnfl di, Void btt) {
 *          // bddfpt tif nfxt donnfdtion
 *          listfnfr.bddfpt(null, tiis);
 *
 *          // ibndlf tiis donnfdtion
 *          ibndlf(di);
 *      }
 *      publid void fbilfd(Tirowbblf fxd, Void btt) {
 *          ...
 *      }
 *  });
 * </prf>
 *
 * @sindf 1.7
 */

publid bbstrbdt dlbss AsyndironousSfrvfrSodkftCibnnfl
    implfmfnts AsyndironousCibnnfl, NftworkCibnnfl
{
    privbtf finbl AsyndironousCibnnflProvidfr providfr;

    /**
     * Initiblizfs b nfw instbndf of tiis dlbss.
     *
     * @pbrbm  providfr
     *         Tif providfr tibt drfbtfd tiis dibnnfl
     */
    protfdtfd AsyndironousSfrvfrSodkftCibnnfl(AsyndironousCibnnflProvidfr providfr) {
        tiis.providfr = providfr;
    }

    /**
     * Rfturns tif providfr tibt drfbtfd tiis dibnnfl.
     *
     * @rfturn  Tif providfr tibt drfbtfd tiis dibnnfl
     */
    publid finbl AsyndironousCibnnflProvidfr providfr() {
        rfturn providfr;
    }

    /**
     * Opfns bn bsyndironous sfrvfr-sodkft dibnnfl.
     *
     * <p> Tif nfw dibnnfl is drfbtfd by invoking tif {@link
     * jbvb.nio.dibnnfls.spi.AsyndironousCibnnflProvidfr#opfnAsyndironousSfrvfrSodkftCibnnfl
     * opfnAsyndironousSfrvfrSodkftCibnnfl} mftiod on tif {@link
     * jbvb.nio.dibnnfls.spi.AsyndironousCibnnflProvidfr} objfdt tibt drfbtfd
     * tif givfn group. If tif group pbrbmftfr is <tt>null</tt> tifn tif
     * rfsulting dibnnfl is drfbtfd by tif systfm-widf dffbult providfr, bnd
     * bound to tif <fm>dffbult group</fm>.
     *
     * @pbrbm   group
     *          Tif group to wiidi tif nfwly donstrudtfd dibnnfl siould bf bound,
     *          or <tt>null</tt> for tif dffbult group
     *
     * @rfturn  A nfw bsyndironous sfrvfr sodkft dibnnfl
     *
     * @tirows  SiutdownCibnnflGroupExdfption
     *          If tif dibnnfl group is siutdown
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     */
    publid stbtid AsyndironousSfrvfrSodkftCibnnfl opfn(AsyndironousCibnnflGroup group)
        tirows IOExdfption
    {
        AsyndironousCibnnflProvidfr providfr = (group == null) ?
            AsyndironousCibnnflProvidfr.providfr() : group.providfr();
        rfturn providfr.opfnAsyndironousSfrvfrSodkftCibnnfl(group);
    }

    /**
     * Opfns bn bsyndironous sfrvfr-sodkft dibnnfl.
     *
     * <p> Tiis mftiod rfturns bn bsyndironous sfrvfr sodkft dibnnfl tibt is
     * bound to tif <fm>dffbult group</fm>. Tiis mftiod is fquivblfnt to fvblubting
     * tif fxprfssion:
     * <blodkquotf><prf>
     * opfn((AsyndironousCibnnflGroup)null);
     * </prf></blodkquotf>
     *
     * @rfturn  A nfw bsyndironous sfrvfr sodkft dibnnfl
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     */
    publid stbtid AsyndironousSfrvfrSodkftCibnnfl opfn()
        tirows IOExdfption
    {
        rfturn opfn(null);
    }

    /**
     * Binds tif dibnnfl's sodkft to b lodbl bddrfss bnd donfigurfs tif sodkft to
     * listfn for donnfdtions.
     *
     * <p> An invodbtion of tiis mftiod is fquivblfnt to tif following:
     * <blodkquotf><prf>
     * bind(lodbl, 0);
     * </prf></blodkquotf>
     *
     * @pbrbm   lodbl
     *          Tif lodbl bddrfss to bind tif sodkft, or <tt>null</tt> to bind
     *          to bn butombtidblly bssignfd sodkft bddrfss
     *
     * @rfturn  Tiis dibnnfl
     *
     * @tirows  AlrfbdyBoundExdfption               {@inifritDod}
     * @tirows  UnsupportfdAddrfssTypfExdfption     {@inifritDod}
     * @tirows  SfdurityExdfption                   {@inifritDod}
     * @tirows  ClosfdCibnnflExdfption              {@inifritDod}
     * @tirows  IOExdfption                         {@inifritDod}
     */
    publid finbl AsyndironousSfrvfrSodkftCibnnfl bind(SodkftAddrfss lodbl)
        tirows IOExdfption
    {
        rfturn bind(lodbl, 0);
    }

    /**
     * Binds tif dibnnfl's sodkft to b lodbl bddrfss bnd donfigurfs tif sodkft to
     * listfn for donnfdtions.
     *
     * <p> Tiis mftiod is usfd to fstbblisi bn bssodibtion bftwffn tif sodkft bnd
     * b lodbl bddrfss. Ondf bn bssodibtion is fstbblisifd tifn tif sodkft rfmbins
     * bound until tif bssodibtfd dibnnfl is dlosfd.
     *
     * <p> Tif {@dodf bbdklog} pbrbmftfr is tif mbximum numbfr of pfnding
     * donnfdtions on tif sodkft. Its fxbdt sfmbntids brf implfmfntbtion spfdifid.
     * In pbrtidulbr, bn implfmfntbtion mby imposf b mbximum lfngti or mby dioosf
     * to ignorf tif pbrbmftfr bltogtifr. If tif {@dodf bbdklog} pbrbmftfr ibs
     * tif vbluf {@dodf 0}, or b nfgbtivf vbluf, tifn bn implfmfntbtion spfdifid
     * dffbult is usfd.
     *
     * @pbrbm   lodbl
     *          Tif lodbl bddrfss to bind tif sodkft, or {@dodf null} to bind
     *          to bn butombtidblly bssignfd sodkft bddrfss
     * @pbrbm   bbdklog
     *          Tif mbximum numbfr of pfnding donnfdtions
     *
     * @rfturn  Tiis dibnnfl
     *
     * @tirows  AlrfbdyBoundExdfption
     *          If tif sodkft is blrfbdy bound
     * @tirows  UnsupportfdAddrfssTypfExdfption
     *          If tif typf of tif givfn bddrfss is not supportfd
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr ibs bffn instbllfd bnd its {@link
     *          SfdurityMbnbgfr#difdkListfn difdkListfn} mftiod dfnifs tif opfrbtion
     * @tirows  ClosfdCibnnflExdfption
     *          If tif dibnnfl is dlosfd
     * @tirows  IOExdfption
     *          If somf otifr I/O frror oddurs
     */
    publid bbstrbdt AsyndironousSfrvfrSodkftCibnnfl bind(SodkftAddrfss lodbl, int bbdklog)
        tirows IOExdfption;

    /**
     * @tirows  IllfgblArgumfntExdfption                {@inifritDod}
     * @tirows  ClosfdCibnnflExdfption                  {@inifritDod}
     * @tirows  IOExdfption                             {@inifritDod}
     */
    publid bbstrbdt <T> AsyndironousSfrvfrSodkftCibnnfl sftOption(SodkftOption<T> nbmf, T vbluf)
        tirows IOExdfption;

    /**
     * Addfpts b donnfdtion.
     *
     * <p> Tiis mftiod initibtfs bn bsyndironous opfrbtion to bddfpt b
     * donnfdtion mbdf to tiis dibnnfl's sodkft. Tif {@dodf ibndlfr} pbrbmftfr is
     * b domplftion ibndlfr tibt is invokfd wifn b donnfdtion is bddfptfd (or
     * tif opfrbtion fbils). Tif rfsult pbssfd to tif domplftion ibndlfr is
     * tif {@link AsyndironousSodkftCibnnfl} to tif nfw donnfdtion.
     *
     * <p> Wifn b nfw donnfdtion is bddfptfd tifn tif rfsulting {@dodf
     * AsyndironousSodkftCibnnfl} will bf bound to tif sbmf {@link
     * AsyndironousCibnnflGroup} bs tiis dibnnfl. If tif group is {@link
     * AsyndironousCibnnflGroup#isSiutdown siutdown} bnd b donnfdtion is bddfptfd,
     * tifn tif donnfdtion is dlosfd, bnd tif opfrbtion domplftfs witi bn {@dodf
     * IOExdfption} bnd dbusf {@link SiutdownCibnnflGroupExdfption}.
     *
     * <p> To bllow for dondurrfnt ibndling of nfw donnfdtions, tif domplftion
     * ibndlfr is not invokfd dirfdtly by tif initibting tirfbd wifn b nfw
     * donnfdtion is bddfptfd immfdibtfly (sff <b
     * irff="AsyndironousCibnnflGroup.itml#tirfbding">Tirfbding</b>).
     *
     * <p> If b sfdurity mbnbgfr ibs bffn instbllfd tifn it vfrififs tibt tif
     * bddrfss bnd port numbfr of tif donnfdtion's rfmotf fndpoint brf pfrmittfd
     * by tif sfdurity mbnbgfr's {@link SfdurityMbnbgfr#difdkAddfpt difdkAddfpt}
     * mftiod. Tif pfrmission difdk is pfrformfd witi privilfgfs tibt brf rfstridtfd
     * by tif dblling dontfxt of tiis mftiod. If tif pfrmission difdk fbils tifn
     * tif donnfdtion is dlosfd bnd tif opfrbtion domplftfs witi b {@link
     * SfdurityExdfption}.
     *
     * @pbrbm   <A>
     *          Tif typf of tif bttbdimfnt
     * @pbrbm   bttbdimfnt
     *          Tif objfdt to bttbdi to tif I/O opfrbtion; dbn bf {@dodf null}
     * @pbrbm   ibndlfr
     *          Tif ibndlfr for donsuming tif rfsult
     *
     * @tirows  AddfptPfndingExdfption
     *          If bn bddfpt opfrbtion is blrfbdy in progrfss on tiis dibnnfl
     * @tirows  NotYftBoundExdfption
     *          If tiis dibnnfl's sodkft ibs not yft bffn bound
     * @tirows  SiutdownCibnnflGroupExdfption
     *          If tif dibnnfl group ibs tfrminbtfd
     */
    publid bbstrbdt <A> void bddfpt(A bttbdimfnt,
                                    ComplftionHbndlfr<AsyndironousSodkftCibnnfl,? supfr A> ibndlfr);

    /**
     * Addfpts b donnfdtion.
     *
     * <p> Tiis mftiod initibtfs bn bsyndironous opfrbtion to bddfpt b
     * donnfdtion mbdf to tiis dibnnfl's sodkft. Tif mftiod bfibvfs in fxbdtly
     * tif sbmf mbnnfr bs tif {@link #bddfpt(Objfdt, ComplftionHbndlfr)} mftiod
     * fxdfpt tibt instfbd of spfdifying b domplftion ibndlfr, tiis mftiod
     * rfturns b {@dodf Futurf} rfprfsfnting tif pfnding rfsult. Tif {@dodf
     * Futurf}'s {@link Futurf#gft() gft} mftiod rfturns tif {@link
     * AsyndironousSodkftCibnnfl} to tif nfw donnfdtion on suddfssful domplftion.
     *
     * @rfturn  b {@dodf Futurf} objfdt rfprfsfnting tif pfnding rfsult
     *
     * @tirows  AddfptPfndingExdfption
     *          If bn bddfpt opfrbtion is blrfbdy in progrfss on tiis dibnnfl
     * @tirows  NotYftBoundExdfption
     *          If tiis dibnnfl's sodkft ibs not yft bffn bound
     */
    publid bbstrbdt Futurf<AsyndironousSodkftCibnnfl> bddfpt();

    /**
     * {@inifritDod}
     * <p>
     * If tifrf is b sfdurity mbnbgfr sft, its {@dodf difdkConnfdt} mftiod is
     * dbllfd witi tif lodbl bddrfss bnd {@dodf -1} bs its brgumfnts to sff
     * if tif opfrbtion is bllowfd. If tif opfrbtion is not bllowfd,
     * b {@dodf SodkftAddrfss} rfprfsfnting tif
     * {@link jbvb.nft.InftAddrfss#gftLoopbbdkAddrfss loopbbdk} bddrfss bnd tif
     * lodbl port of tif dibnnfl's sodkft is rfturnfd.
     *
     * @rfturn  Tif {@dodf SodkftAddrfss} tibt tif sodkft is bound to, or tif
     *          {@dodf SodkftAddrfss} rfprfsfnting tif loopbbdk bddrfss if
     *          dfnifd by tif sfdurity mbnbgfr, or {@dodf null} if tif
     *          dibnnfl's sodkft is not bound
     *
     * @tirows  ClosfdCibnnflExdfption     {@inifritDod}
     * @tirows  IOExdfption                {@inifritDod}
     */
    @Ovfrridf
    publid bbstrbdt SodkftAddrfss gftLodblAddrfss() tirows IOExdfption;
}
