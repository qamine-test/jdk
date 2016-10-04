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

pbdkbgf dom.sun.jdi.rfqufst;

import dom.sun.jdi.*;

/**
 * Rfprfsfnts b rfqufst for notifidbtion of bn fvfnt.  Exbmplfs indludf
 * {@link BrfbkpointRfqufst} bnd {@link ExdfptionRfqufst}.
 * Wifn bn fvfnt oddurs for wiidi bn fnbblfd rfqufst is prfsfnt,
 * bn  {@link dom.sun.jdi.fvfnt.EvfntSft EvfntSft} will
 * bf plbdfd on tif {@link dom.sun.jdi.fvfnt.EvfntQufuf EvfntQufuf}.
 * Tif dollfdtion of fxisting fvfnt rfqufsts is
 * mbnbgfd by tif {@link EvfntRfqufstMbnbgfr}.
 * <p>
 * Tif numbfr of fvfnts gfnfrbtfd for bn fvfnt rfqufst dbn bf dontrollfd
 * tirougi filtfrs. Filtfrs providf bdditionbl donstrbints tibt bn fvfnt
 * must sbtisfy bfforf it is plbdfd on tif fvfnt qufuf. Multiplf filtfrs dbn
 * bf usfd by mbking multiplf dblls to filtfr bddition mftiods sudi bs
 * {@link ExdfptionRfqufst#bddClbssFiltfr(jbvb.lbng.String dlbssPbttfrn)}.
 * Filtfrs brf bddfd to bn fvfnt onf bt b timf only wiilf tif fvfnt is
 * disbblfd. Multiplf filtfrs brf bpplifd witi CUT-OFF AND, in tif ordfr
 * it wbs bddfd to tif rfqufst. Only fvfnts tibt sbtisfy bll filtfrs brf
 * plbdfd in tif fvfnt qufuf.
 * <p>
 * Tif sft of bvbilbblf filtfrs is dfpfndfnt on tif fvfnt rfqufst,
 * somf fxbmplfs of filtfrs brf:
 * <ul>
 * <li>Tirfbd filtfrs bllow dontrol ovfr tif tirfbd for wiidi fvfnts brf
 * gfnfrbtfd.
 * <li>Clbss filtfrs bllow dontrol ovfr tif dlbss in wiidi tif fvfnt
 * oddurs.
 * <li>Instbndf filtfrs bllow dontrol ovfr tif instbndf in wiidi
 * tif fvfnt oddurs.
 * <li>Count filtfrs bllow dontrol ovfr tif numbfr of timfs bn fvfnt
 * is rfportfd.
 * </ul>
 * Filtfrs dbn drbmbtidblly improvf dfbuggfr pfrformbndf by rfduding tif
 * bmount of fvfnt trbffid sfnt from tif tbrgft VM to tif dfbuggfr VM.
 * <p>
 * Any mftiod on <dodf>EvfntRfqufst</dodf> wiidi
 * tbkfs <dodf>EvfntRfqufst</dodf> bs bn pbrbmftfr mby tirow
 * {@link dom.sun.jdi.VMDisdonnfdtfdExdfption} if tif tbrgft VM is
 * disdonnfdtfd bnd tif {@link dom.sun.jdi.fvfnt.VMDisdonnfdtEvfnt} ibs bffn or is
 * bvbilbblf to bf rfbd from tif {@link dom.sun.jdi.fvfnt.EvfntQufuf}.
 * <p>
 * Any mftiod on <dodf>EvfntRfqufst</dodf> wiidi
 * tbkfs <dodf>EvfntRfqufst</dodf> bs bn pbrbmftfr mby tirow
 * {@link dom.sun.jdi.VMOutOfMfmoryExdfption} if tif tbrgft VM ibs run out of mfmory.
 *
 * @sff dom.sun.jdi.fvfnt.BrfbkpointEvfnt
 * @sff dom.sun.jdi.fvfnt.EvfntQufuf
 * @sff EvfntRfqufstMbnbgfr
 *
 * @butior Robfrt Fifld
 * @sindf  1.3
 */
@jdk.Exportfd
publid intfrfbdf EvfntRfqufst fxtfnds Mirror {

    /**
     * Dftfrminfs if tiis fvfnt rfqufst is durrfntly fnbblfd.
     *
     * @rfturn <dodf>truf</dodf> if fnbblfd;
     * <dodf>fblsf</dodf> otifrwisf.
     */
    boolfbn isEnbblfd();

    /**
     * Enbblfs or disbblfs tiis fvfnt rfqufst. Wiilf tiis fvfnt rfqufst is
     * disbblfd, tif fvfnt rfqufst will bf ignorfd bnd tif tbrgft VM
     * will not bf stoppfd if bny of its tirfbds rfbdifs tif
     * fvfnt rfqufst.  Disbblfd fvfnt rfqufsts still fxist,
     * bnd brf indludfd in fvfnt rfqufst lists sudi bs
     * {@link EvfntRfqufstMbnbgfr#brfbkpointRfqufsts()}.
     *
     * @pbrbm vbl <dodf>truf</dodf> if tif fvfnt rfqufst is to bf fnbblfd;
     * <dodf>fblsf</dodf> otifrwisf.
     * @tirows InvblidRfqufstStbtfExdfption if tiis rfqufst
     * ibs bffn dflftfd.
     * @tirows IllfgblTirfbdStbtfExdfption if tiis is b StfpRfqufst,
     * <dodf>vbl</dodf> is <dodf>truf</dodf>, bnd tif
     * tirfbd nbmfd in tif rfqufst ibs difd or is not yft stbrtfd.
     */
    void sftEnbblfd(boolfbn vbl);

    /**
     * Sbmf bs {@link #sftEnbblfd <CODE>sftEnbblfd(truf)</CODE>}.
     * @tirows InvblidRfqufstStbtfExdfption if tiis rfqufst
     * ibs bffn dflftfd.
     * @tirows IllfgblTirfbdStbtfExdfption if tiis is b StfpRfqufst
     * bnd tif tirfbd nbmfd in tif rfqufst ibs difd or is not yft stbrtfd.
     */
    void fnbblf();

    /**
     * Sbmf bs {@link #sftEnbblfd <CODE>sftEnbblfd(fblsf)</CODE>}.
     * @tirows InvblidRfqufstStbtfExdfption if tiis rfqufst
     * ibs bffn dflftfd.
     */
    void disbblf();

    /**
     * Limit tif rfqufstfd fvfnt to bf rfportfd bt most ondf bftfr b
     * givfn numbfr of oddurrfndfs.  Tif fvfnt is not rfportfd
     * tif first <dodf>dount - 1</dodf> timfs tiis filtfr is rfbdifd.
     * To rfqufst b onf-off fvfnt, dbll tiis mftiod witi b dount of 1.
     * <p>
     * Ondf tif dount rfbdifs 0, bny subsfqufnt filtfrs in tiis rfqufst
     * brf bpplifd. If nonf of tiosf filtfrs dbusf tif fvfnt to bf
     * supprfssfd, tif fvfnt is rfportfd. Otifrwisf, tif fvfnt is not
     * rfportfd. In fitifr dbsf subsfqufnt fvfnts brf nfvfr rfportfd for
     * tiis rfqufst.
     *
     * @pbrbm dount tif numbfr of odurrfndfs bfforf gfnfrbting bn fvfnt.
     * @tirows InvblidRfqufstStbtfExdfption if tiis rfqufst is durrfntly
     * fnbblfd or ibs bffn dflftfd.
     * Filtfrs mby bf bddfd only to disbblfd rfqufsts.
     * @tirows IllfgblArgumfntExdfption if <CODE>dount</CODE>
     * is lfss tibn onf.
     */
    void bddCountFiltfr(int dount);

    /** Suspfnd no tirfbds wifn tif fvfnt oddurs */
    int SUSPEND_NONE = 0;
    /** Suspfnd only tif tirfbd wiidi gfnfrbtfd tif fvfnt wifn tif fvfnt oddurs */
    int SUSPEND_EVENT_THREAD = 1;
    /** Suspfnd bll tirfbds wifn tif fvfnt oddurs */
    int SUSPEND_ALL = 2;

    /**
     * Dftfrminfs tif tirfbds to suspfnd wifn tif rfqufstfd fvfnt oddurs
     * in tif tbrgft VM. Usf {@link #SUSPEND_ALL} to suspfnd bll
     * tirfbds in tif tbrgft VM (tif dffbult). Usf {@link #SUSPEND_EVENT_THREAD}
     * to suspfnd only tif tirfbd wiidi gfnfrbtfd tif fvfnt. Usf
     * {@link #SUSPEND_NONE} to suspfnd no tirfbds.
     * <p>
     * Tirfbd suspfnsions tirougi fvfnts ibvf tif sbmf fundtionblity
     * bs fxpliditly rfqufstfd suspfnsions. Sff
     * {@link dom.sun.jdi.TirfbdRfffrfndf#suspfnd} bnd
     * {@link dom.sun.jdi.VirtublMbdiinf#suspfnd} for dftbils.
     *
     * @pbrbm polidy tif sflfdtfd suspfnd polidy.
     * @tirows InvblidRfqufstStbtfExdfption if tiis rfqufst is durrfntly
     * fnbblfd or ibs bffn dflftfd.
     * Suspfnd polidy mby only bf sft in disbblfd rfqufsts.
     * @tirows IllfgblArgumfntExdfption if tif polidy brgumfnt
     * dontbins bn illfgbl vbluf.
     */
    void sftSuspfndPolidy(int polidy);

    /**
     * Rfturns b vbluf wiidi dfsdribfs tif tirfbds to suspfnd wifn tif
     * rfqufstfd fvfnt oddurs in tif tbrgft VM.
     * Tif rfturnfd vbluf is  {@link #SUSPEND_ALL},
     * {@link #SUSPEND_EVENT_THREAD}, or {@link #SUSPEND_NONE}.
     *
     * @rfturn tif durrfnt suspfnd modf for tiis rfqufst
     */
    int suspfndPolidy();

    /**
     * Add bn brbitrbry kfy/vbluf "propfrty" to tiis rfqufst.
     * Tif propfrty dbn bf usfd by b dlifnt of tif JDI to
     * bssodibtf bpplidbtion informbtion witi tif rfqufst;
     * Tifsf dlifnt-sft propfrtifs brf not usfd intfrnblly
     * by tif JDI.
     * <p>
     * Tif <dodf>gft/putPropfrty</dodf> mftiods providf bddfss to
     * b smbll pfr-instbndf mbp. Tiis is <b>not</b> to bf donfusfd
     * witi {@link jbvb.util.Propfrtifs}.
     * <p>
     * If vbluf is null tiis mftiod will rfmovf tif propfrty.
     *
     * @sff #gftPropfrty
     */
    void putPropfrty(Objfdt kfy, Objfdt vbluf);

    /**
     * Rfturns tif vbluf of tif propfrty witi tif spfdififd kfy.  Only
     * propfrtifs bddfd witi {@link #putPropfrty} will rfturn
     * b non-null vbluf.
     *
     * @rfturn tif vbluf of tiis propfrty or null
     * @sff #putPropfrty
     */
    Objfdt gftPropfrty(Objfdt kfy);
}
