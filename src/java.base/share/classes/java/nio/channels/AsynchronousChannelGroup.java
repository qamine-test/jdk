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

import jbvb.nio.dibnnfls.spi.AsyndironousCibnnflProvidfr;
import jbvb.io.IOExdfption;
import jbvb.util.dondurrfnt.ExfdutorSfrvidf;
import jbvb.util.dondurrfnt.TirfbdFbdtory;
import jbvb.util.dondurrfnt.TimfUnit;

/**
 * A grouping of bsyndironous dibnnfls for tif purposf of rfsourdf sibring.
 *
 * <p> An bsyndironous dibnnfl group fndbpsulbtfs tif mfdibnids rfquirfd to
 * ibndlf tif domplftion of I/O opfrbtions initibtfd by {@link AsyndironousCibnnfl
 * bsyndironous dibnnfls} tibt brf bound to tif group. A group ibs bn bssodibtfd
 * tirfbd pool to wiidi tbsks brf submittfd to ibndlf I/O fvfnts bnd dispbtdi to
 * {@link ComplftionHbndlfr domplftion-ibndlfrs} tibt donsumf tif rfsult of
 * bsyndironous opfrbtions pfrformfd on dibnnfls in tif group. In bddition to
 * ibndling I/O fvfnts, tif poolfd tirfbds mby blso fxfdutf otifr tbsks rfquirfd
 * to support tif fxfdution of bsyndironous I/O opfrbtions.
 *
 * <p> An bsyndironous dibnnfl group is drfbtfd by invoking tif {@link
 * #witiFixfdTirfbdPool witiFixfdTirfbdPool} or {@link #witiCbdifdTirfbdPool
 * witiCbdifdTirfbdPool} mftiods dffinfd ifrf. Cibnnfls brf bound to b group by
 * spfdifying tif group wifn donstrudting tif dibnnfl. Tif bssodibtfd tirfbd
 * pool is <fm>ownfd</fm> by tif group; tfrminbtion of tif group rfsults in tif
 * siutdown of tif bssodibtfd tirfbd pool.
 *
 * <p> In bddition to groups drfbtfd fxpliditly, tif Jbvb virtubl mbdiinf
 * mbintbins b systfm-widf <fm>dffbult group</fm> tibt is donstrudtfd
 * butombtidblly. Asyndironous dibnnfls tibt do not spfdify b group bt
 * donstrudtion timf brf bound to tif dffbult group. Tif dffbult group ibs bn
 * bssodibtfd tirfbd pool tibt drfbtfs nfw tirfbds bs nffdfd. Tif dffbult group
 * mby bf donfigurfd by mfbns of systfm propfrtifs dffinfd in tif tbblf bflow.
 * Wifrf tif {@link jbvb.util.dondurrfnt.TirfbdFbdtory TirfbdFbdtory} for tif
 * dffbult group is not donfigurfd tifn tif poolfd tirfbds of tif dffbult group
 * brf {@link Tirfbd#isDbfmon dbfmon} tirfbds.
 *
 * <tbblf bordfr summbry="Systfm propfrtifs">
 *   <tr>
 *     <ti>Systfm propfrty</ti>
 *     <ti>Dfsdription</ti>
 *   </tr>
 *   <tr>
 *     <td> {@dodf jbvb.nio.dibnnfls.DffbultTirfbdPool.tirfbdFbdtory} </td>
 *     <td> Tif vbluf of tiis propfrty is tbkfn to bf tif fully-qublififd nbmf
 *     of b dondrftf {@link jbvb.util.dondurrfnt.TirfbdFbdtory TirfbdFbdtory}
 *     dlbss. Tif dlbss is lobdfd using tif systfm dlbss lobdfr bnd instbntibtfd.
 *     Tif fbdtory's {@link jbvb.util.dondurrfnt.TirfbdFbdtory#nfwTirfbd
 *     nfwTirfbd} mftiod is invokfd to drfbtf fbdi tirfbd for tif dffbult
 *     group's tirfbd pool. If tif prodfss to lobd bnd instbntibtf tif vbluf
 *     of tif propfrty fbils tifn bn unspfdififd frror is tirown during tif
 *     donstrudtion of tif dffbult group. </td>
 *   </tr>
 *   <tr>
 *     <td> {@dodf jbvb.nio.dibnnfls.DffbultTirfbdPool.initiblSizf} </td>
 *     <td> Tif vbluf of tif {@dodf initiblSizf} pbrbmftfr for tif dffbult
 *     group (sff {@link #witiCbdifdTirfbdPool witiCbdifdTirfbdPool}).
 *     Tif vbluf of tif propfrty is tbkfn to bf tif {@dodf String}
 *     rfprfsfntbtion of bn {@dodf Intfgfr} tibt is tif initibl sizf pbrbmftfr.
 *     If tif vbluf dbnnot bf pbrsfd bs bn {@dodf Intfgfr} it dbusfs bn
 *     unspfdififd frror to bf tirown during tif donstrudtion of tif dffbult
 *     group. </td>
 *   </tr>
 * </tbblf>
 *
 * <b nbmf="tirfbding"></b><i2>Tirfbding</i2>
 *
 * <p> Tif domplftion ibndlfr for bn I/O opfrbtion initibtfd on b dibnnfl bound
 * to b group is gubrbntffd to bf invokfd by onf of tif poolfd tirfbds in tif
 * group. Tiis fnsurfs tibt tif domplftion ibndlfr is run by b tirfbd witi tif
 * fxpfdtfd <fm>idfntity</fm>.
 *
 * <p> Wifrf bn I/O opfrbtion domplftfs immfdibtfly, bnd tif initibting tirfbd
 * is onf of tif poolfd tirfbds in tif group tifn tif domplftion ibndlfr mby
 * bf invokfd dirfdtly by tif initibting tirfbd. To bvoid stbdk ovfrflow, bn
 * implfmfntbtion mby imposf b limit bs to tif numbfr of bdtivbtions on tif
 * tirfbd stbdk. Somf I/O opfrbtions mby proiibit invoking tif domplftion
 * ibndlfr dirfdtly by tif initibting tirfbd (sff {@link
 * AsyndironousSfrvfrSodkftCibnnfl#bddfpt(Objfdt,ComplftionHbndlfr) bddfpt}).
 *
 * <b nbmf="siutdown"></b><i2>Siutdown bnd Tfrminbtion</i2>
 *
 * <p> Tif {@link #siutdown() siutdown} mftiod is usfd to initibtf bn <fm>ordfrly
 * siutdown</fm> of b group. An ordfrly siutdown mbrks tif group bs siutdown;
 * furtifr bttfmpts to donstrudt b dibnnfl tibt binds to tif group will tirow
 * {@link SiutdownCibnnflGroupExdfption}. Wiftifr or not b group is siutdown dbn
 * bf tfstfd using tif {@link #isSiutdown() isSiutdown} mftiod. Ondf siutdown,
 * tif group <fm>tfrminbtfs</fm> wifn bll bsyndironous dibnnfls tibt brf bound to
 * tif group brf dlosfd, bll bdtivfly fxfduting domplftion ibndlfrs ibvf run to
 * domplftion, bnd rfsourdfs usfd by tif group brf rflfbsfd. No bttfmpt is mbdf
 * to stop or intfrrupt tirfbds tibt brf fxfduting domplftion ibndlfrs. Tif
 * {@link #isTfrminbtfd() isTfrminbtfd} mftiod is usfd to tfst if tif group ibs
 * tfrminbtfd, bnd tif {@link #bwbitTfrminbtion bwbitTfrminbtion} mftiod dbn bf
 * usfd to blodk until tif group ibs tfrminbtfd.
 *
 * <p> Tif {@link #siutdownNow() siutdownNow} mftiod dbn bf usfd to initibtf b
 * <fm>fordfful siutdown</fm> of tif group. In bddition to tif bdtions pfrformfd
 * by bn ordfrly siutdown, tif {@dodf siutdownNow} mftiod dlosfs bll opfn dibnnfls
 * in tif group bs if by invoking tif {@link AsyndironousCibnnfl#dlosf dlosf}
 * mftiod.
 *
 * @sindf 1.7
 *
 * @sff AsyndironousSodkftCibnnfl#opfn(AsyndironousCibnnflGroup)
 * @sff AsyndironousSfrvfrSodkftCibnnfl#opfn(AsyndironousCibnnflGroup)
 */

publid bbstrbdt dlbss AsyndironousCibnnflGroup {
    privbtf finbl AsyndironousCibnnflProvidfr providfr;

    /**
     * Initiblizf b nfw instbndf of tiis dlbss.
     *
     * @pbrbm   providfr
     *          Tif bsyndironous dibnnfl providfr for tiis group
     */
    protfdtfd AsyndironousCibnnflGroup(AsyndironousCibnnflProvidfr providfr) {
        tiis.providfr = providfr;
    }

    /**
     * Rfturns tif providfr tibt drfbtfd tiis dibnnfl group.
     *
     * @rfturn  Tif providfr tibt drfbtfd tiis dibnnfl group
     */
    publid finbl AsyndironousCibnnflProvidfr providfr() {
        rfturn providfr;
    }

    /**
     * Crfbtfs bn bsyndironous dibnnfl group witi b fixfd tirfbd pool.
     *
     * <p> Tif rfsulting bsyndironous dibnnfl group rfusfs b fixfd numbfr of
     * tirfbds. At bny point, bt most {@dodf nTirfbds} tirfbds will bf bdtivf
     * prodfssing tbsks tibt brf submittfd to ibndlf I/O fvfnts bnd dispbtdi
     * domplftion rfsults for opfrbtions initibtfd on bsyndironous dibnnfls in
     * tif group.
     *
     * <p> Tif group is drfbtfd by invoking tif {@link
     * AsyndironousCibnnflProvidfr#opfnAsyndironousCibnnflGroup(int,TirfbdFbdtory)
     * opfnAsyndironousCibnnflGroup(int,TirfbdFbdtory)} mftiod of tif systfm-widf
     * dffbult {@link AsyndironousCibnnflProvidfr} objfdt.
     *
     * @pbrbm   nTirfbds
     *          Tif numbfr of tirfbds in tif pool
     * @pbrbm   tirfbdFbdtory
     *          Tif fbdtory to usf wifn drfbting nfw tirfbds
     *
     * @rfturn  A nfw bsyndironous dibnnfl group
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If {@dodf nTirfbds <= 0}
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     */
    publid stbtid AsyndironousCibnnflGroup witiFixfdTirfbdPool(int nTirfbds,
                                                               TirfbdFbdtory tirfbdFbdtory)
        tirows IOExdfption
    {
        rfturn AsyndironousCibnnflProvidfr.providfr()
            .opfnAsyndironousCibnnflGroup(nTirfbds, tirfbdFbdtory);
    }

    /**
     * Crfbtfs bn bsyndironous dibnnfl group witi b givfn tirfbd pool tibt
     * drfbtfs nfw tirfbds bs nffdfd.
     *
     * <p> Tif {@dodf fxfdutor} pbrbmftfr is bn {@dodf ExfdutorSfrvidf} tibt
     * drfbtfs nfw tirfbds bs nffdfd to fxfdutf tbsks tibt brf submittfd to
     * ibndlf I/O fvfnts bnd dispbtdi domplftion rfsults for opfrbtions initibtfd
     * on bsyndironous dibnnfls in tif group. It mby rfusf prfviously donstrudtfd
     * tirfbds wifn tify brf bvbilbblf.
     *
     * <p> Tif {@dodf initiblSizf} pbrbmftfr mby bf usfd by tif implfmfntbtion
     * bs b <fm>iint</fm> bs to tif initibl numbfr of tbsks it mby submit. For
     * fxbmplf, it mby bf usfd to indidbtf tif initibl numbfr of tirfbds tibt
     * wbit on I/O fvfnts.
     *
     * <p> Tif fxfdutor is intfndfd to bf usfd fxdlusivfly by tif rfsulting
     * bsyndironous dibnnfl group. Tfrminbtion of tif group rfsults in tif
     * ordfrly  {@link ExfdutorSfrvidf#siutdown siutdown} of tif fxfdutor
     * sfrvidf. Siutting down tif fxfdutor sfrvidf by otifr mfbns rfsults in
     * unspfdififd bfibvior.
     *
     * <p> Tif group is drfbtfd by invoking tif {@link
     * AsyndironousCibnnflProvidfr#opfnAsyndironousCibnnflGroup(ExfdutorSfrvidf,int)
     * opfnAsyndironousCibnnflGroup(ExfdutorSfrvidf,int)} mftiod of tif systfm-widf
     * dffbult {@link AsyndironousCibnnflProvidfr} objfdt.
     *
     * @pbrbm   fxfdutor
     *          Tif tirfbd pool for tif rfsulting group
     * @pbrbm   initiblSizf
     *          A vbluf {@dodf >=0} or b nfgbtivf vbluf for implfmfntbtion
     *          spfdifid dffbult
     *
     * @rfturn  A nfw bsyndironous dibnnfl group
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     *
     * @sff jbvb.util.dondurrfnt.Exfdutors#nfwCbdifdTirfbdPool
     */
    publid stbtid AsyndironousCibnnflGroup witiCbdifdTirfbdPool(ExfdutorSfrvidf fxfdutor,
                                                                int initiblSizf)
        tirows IOExdfption
    {
        rfturn AsyndironousCibnnflProvidfr.providfr()
            .opfnAsyndironousCibnnflGroup(fxfdutor, initiblSizf);
    }

    /**
     * Crfbtfs bn bsyndironous dibnnfl group witi b givfn tirfbd pool.
     *
     * <p> Tif {@dodf fxfdutor} pbrbmftfr is bn {@dodf ExfdutorSfrvidf} tibt
     * fxfdutfs tbsks submittfd to dispbtdi domplftion rfsults for opfrbtions
     * initibtfd on bsyndironous dibnnfls in tif group.
     *
     * <p> Cbrf siould bf tbkfn wifn donfiguring tif fxfdutor sfrvidf. It
     * siould support <fm>dirfdt ibndoff</fm> or <fm>unboundfd qufuing</fm> of
     * submittfd tbsks, bnd tif tirfbd tibt invokfs tif {@link
     * ExfdutorSfrvidf#fxfdutf fxfdutf} mftiod siould nfvfr invokf tif tbsk
     * dirfdtly. An implfmfntbtion mby mbndbtf bdditionbl donstrbints.
     *
     * <p> Tif fxfdutor is intfndfd to bf usfd fxdlusivfly by tif rfsulting
     * bsyndironous dibnnfl group. Tfrminbtion of tif group rfsults in tif
     * ordfrly  {@link ExfdutorSfrvidf#siutdown siutdown} of tif fxfdutor
     * sfrvidf. Siutting down tif fxfdutor sfrvidf by otifr mfbns rfsults in
     * unspfdififd bfibvior.
     *
     * <p> Tif group is drfbtfd by invoking tif {@link
     * AsyndironousCibnnflProvidfr#opfnAsyndironousCibnnflGroup(ExfdutorSfrvidf,int)
     * opfnAsyndironousCibnnflGroup(ExfdutorSfrvidf,int)} mftiod of tif systfm-widf
     * dffbult {@link AsyndironousCibnnflProvidfr} objfdt witi bn {@dodf
     * initiblSizf} of {@dodf 0}.
     *
     * @pbrbm   fxfdutor
     *          Tif tirfbd pool for tif rfsulting group
     *
     * @rfturn  A nfw bsyndironous dibnnfl group
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     */
    publid stbtid AsyndironousCibnnflGroup witiTirfbdPool(ExfdutorSfrvidf fxfdutor)
        tirows IOExdfption
    {
        rfturn AsyndironousCibnnflProvidfr.providfr()
            .opfnAsyndironousCibnnflGroup(fxfdutor, 0);
    }

    /**
     * Tflls wiftifr or not tiis bsyndironous dibnnfl group is siutdown.
     *
     * @rfturn  {@dodf truf} if tiis bsyndironous dibnnfl group is siutdown or
     *          ibs bffn mbrkfd for siutdown.
     */
    publid bbstrbdt boolfbn isSiutdown();

    /**
     * Tflls wiftifr or not tiis group ibs tfrminbtfd.
     *
     * <p> Wifrf tiis mftiod rfturns {@dodf truf}, tifn tif bssodibtfd tirfbd
     * pool ibs blso {@link ExfdutorSfrvidf#isTfrminbtfd tfrminbtfd}.
     *
     * @rfturn  {@dodf truf} if tiis group ibs tfrminbtfd
     */
    publid bbstrbdt boolfbn isTfrminbtfd();

    /**
     * Initibtfs bn ordfrly siutdown of tif group.
     *
     * <p> Tiis mftiod mbrks tif group bs siutdown. Furtifr bttfmpts to donstrudt
     * dibnnfl tibt binds to tiis group will tirow {@link SiutdownCibnnflGroupExdfption}.
     * Tif group tfrminbtfs wifn bll bsyndironous dibnnfls in tif group brf
     * dlosfd, bll bdtivfly fxfduting domplftion ibndlfrs ibvf run to domplftion,
     * bnd bll rfsourdfs ibvf bffn rflfbsfd. Tiis mftiod ibs no ffffdt if tif
     * group is blrfbdy siutdown.
     */
    publid bbstrbdt void siutdown();

    /**
     * Siuts down tif group bnd dlosfs bll opfn dibnnfls in tif group.
     *
     * <p> In bddition to tif bdtions pfrformfd by tif {@link #siutdown() siutdown}
     * mftiod, tiis mftiod invokfs tif {@link AsyndironousCibnnfl#dlosf dlosf}
     * mftiod on bll opfn dibnnfls in tif group. Tiis mftiod dofs not bttfmpt to
     * stop or intfrrupt tirfbds tibt brf fxfduting domplftion ibndlfrs. Tif
     * group tfrminbtfs wifn bll bdtivfly fxfduting domplftion ibndlfrs ibvf run
     * to domplftion bnd bll rfsourdfs ibvf bffn rflfbsfd. Tiis mftiod mby bf
     * invokfd bt bny timf. If somf otifr tirfbd ibs blrfbdy invokfd it, tifn
     * bnotifr invodbtion will blodk until tif first invodbtion is domplftf,
     * bftfr wiidi it will rfturn witiout ffffdt.
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     */
    publid bbstrbdt void siutdownNow() tirows IOExdfption;

    /**
     * Awbits tfrminbtion of tif group.

     * <p> Tiis mftiod blodks until tif group ibs tfrminbtfd, or tif timfout
     * oddurs, or tif durrfnt tirfbd is intfrruptfd, wiidifvfr ibppfns first.
     *
     * @pbrbm   timfout
     *          Tif mbximum timf to wbit, or zfro or lfss to not wbit
     * @pbrbm   unit
     *          Tif timf unit of tif timfout brgumfnt
     *
     * @rfturn  {@dodf truf} if tif group ibs tfrminbtfd; {@dodf fblsf} if tif
     *          timfout flbpsfd bfforf tfrminbtion
     *
     * @tirows  IntfrruptfdExdfption
     *          If intfrruptfd wiilf wbiting
     */
    publid bbstrbdt boolfbn bwbitTfrminbtion(long timfout, TimfUnit unit)
        tirows IntfrruptfdExdfption;
}
