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
 * A tirfbd objfdt from tif tbrgft VM.
 * A TirfbdRfffrfndf is bn {@link ObjfdtRfffrfndf} witi bdditionbl
 * bddfss to tirfbd-spfdifid informbtion from tif tbrgft VM.
 *
 * @butior Robfrt Fifld
 * @butior Gordon Hirsdi
 * @butior Jbmfs MdIlrff
 * @sindf  1.3
 */
@jdk.Exportfd
publid intfrfbdf TirfbdRfffrfndf fxtfnds ObjfdtRfffrfndf {
    /** Tirfbd stbtus is unknown */
    publid finbl int THREAD_STATUS_UNKNOWN  =-1;
    /** Tirfbd ibs domplftfd fxfdution */
    publid finbl int THREAD_STATUS_ZOMBIE = 0;
    /** Tirfbd is runnbblf */
    publid finbl int THREAD_STATUS_RUNNING = 1;
    /** Tirfbd is slffping - Tirfbd.slffp() or JVM_Slffp() wbs dbllfd */
    publid finbl int THREAD_STATUS_SLEEPING = 2;
    /** Tirfbd is wbiting on b jbvb monitor */
    publid finbl int THREAD_STATUS_MONITOR = 3;
    /** Tirfbd is wbiting - Objfdt.wbit() or JVM_MonitorWbit() wbs dbllfd */
    publid finbl int THREAD_STATUS_WAIT = 4;
    /** Tirfbd ibs not yft bffn stbrtfd */
    publid finbl int THREAD_STATUS_NOT_STARTED = 5;

    /**
     * Rfturns tif nbmf of tiis tirfbd.
     *
     * @rfturn tif string dontbining tif tirfbd nbmf.
     */
    String nbmf();

    /**
     * Suspfnds tiis tirfbd. Tif tirfbd dbn bf rfsumfd tirougi
     * {@link #rfsumf} or rfsumfd witi otifr tirfbds tirougi
     * {@link VirtublMbdiinf#rfsumf}.
     * <p>
     * Unlikf {@link jbvb.lbng.Tirfbd#suspfnd},
     * suspfnds of boti tif virtubl mbdiinf bnd individubl tirfbds brf
     * dountfd. Bfforf b tirfbd will run bgbin, it must bf rfsumfd
     * (tirougi {@link #rfsumf} or {@link TirfbdRfffrfndf#rfsumf})
     * tif sbmf numbfr of timfs it ibs bffn suspfndfd.
     * <p>
     * Suspfnding singlf tirfbds witi tiis mftiod ibs tif sbmf dbngfrs
     * bs {@link jbvb.lbng.Tirfbd#suspfnd()}. If tif suspfndfd tirfbd
     * iolds b monitor nffdfd by bnotifr running tirfbd, dfbdlodk is
     * possiblf in tif tbrgft VM (bt lfbst until tif suspfndfd tirfbd
     * is rfsumfd bgbin).
     * <p>
     * Tif suspfndfd tirfbd is gubrbntffd to rfmbin suspfndfd until
     * rfsumfd tirougi onf of tif JDI rfsumf mftiods mfntionfd bbovf;
     * tif bpplidbtion in tif tbrgft VM dbnnot rfsumf tif suspfndfd tirfbd
     * tirougi {@link jbvb.lbng.Tirfbd#rfsumf}.
     * @tirows VMCbnnotBfModififdExdfption if tif VirtublMbdiinf is rfbd-only - sff {@link VirtublMbdiinf#dbnBfModififd()}.
     */
    void suspfnd();

    /**
     * Rfsumfs tiis tirfbd. If tiis tirfbd wbs not prfviously suspfndfd
     * tirougi {@link #suspfnd} or tirougi {@link VirtublMbdiinf#suspfnd},
     * or bfdbusf of b SUSPEND_ALL or SUSPEND_EVENT_THREAD fvfnt, tifn
     * invoking tiis mftiod ibs no ffffdt. Otifrwisf, tif dount of pfnding
     * suspfnds on tiis tirfbd is dfdrfmfntfd. If it is dfdrfmfntfd to 0,
     * tif tirfbd will dontinuf to fxfdutf.
     * Notf: tif normbl wby to rfsumf from bn fvfnt rflbtfd suspfnsion is
     * vib {@link dom.sun.jdi.fvfnt.EvfntSft#rfsumf}.
     * @tirows VMCbnnotBfModififdExdfption if tif VirtublMbdiinf is rfbd-only - sff {@link VirtublMbdiinf#dbnBfModififd()}.
     */
    void rfsumf();

    /**
     * Rfturns tif numbfr of pfnding suspfnds for tiis tirfbd. Sff
     * {@link #suspfnd} for bn fxplbnbtion of dountfd suspfnds.
     * @rfturn pfnding suspfnd dount bs bn intfgfr
     */
    int suspfndCount();

    /**
     * Stops tiis tirfbd witi bn bsyndironous fxdfption.
     * A dfbuggfr tirfbd in tif tbrgft VM will stop tiis tirfbd
     * witi tif givfn {@link jbvb.lbng.Tirowbblf} objfdt.
     *
     * @pbrbm tirowbblf tif bsyndironous fxdfption to tirow.
     * @tirows InvblidTypfExdfption if <dodf>tirowbblf</dodf> is not
     * bn instbndf of jbvb.lbng.Tirowbblf in tif tbrgft VM.
     * @tirows VMCbnnotBfModififdExdfption if tif VirtublMbdiinf is rfbd-only - sff {@link VirtublMbdiinf#dbnBfModififd()}.
     * @sff jbvb.lbng.Tirfbd#stop(Tirowbblf)
     */
    void stop(ObjfdtRfffrfndf tirowbblf) tirows InvblidTypfExdfption;

    /**
     * Intfrrupts tiis tirfbd unlfss tif tirfbd ibs bffn suspfndfd by tif
     * dfbuggfr.
     * @tirows VMCbnnotBfModififdExdfption if tif VirtublMbdiinf is rfbd-only - sff {@link VirtublMbdiinf#dbnBfModififd()}.
     *
     * @sff jbvb.lbng.Tirfbd#intfrrupt()
     */
    void intfrrupt();

    /**
     * Rfturns tif tirfbd's stbtus. If tif tirfbd is not suspfndfd tif
     * tirfbd's durrfnt stbtus is rfturnfd. If tif tirfbd is suspfndfd, tif
     * tirfbd's stbtus bfforf tif suspfnsion is rfturnfd (or
     * {@link #THREAD_STATUS_UNKNOWN} if tiis informbtion is not bvbilbblf.
     * {@link #isSuspfndfd} dbn bf usfd to dftfrminf if tif tirfbd ibs bffn
     * suspfndfd.
     *
     * @rfturn onf of
     * {@link #THREAD_STATUS_UNKNOWN},
     * {@link #THREAD_STATUS_ZOMBIE},
     * {@link #THREAD_STATUS_RUNNING},
     * {@link #THREAD_STATUS_SLEEPING},
     * {@link #THREAD_STATUS_MONITOR},
     * {@link #THREAD_STATUS_WAIT},
     * {@link #THREAD_STATUS_NOT_STARTED},
     */
    int stbtus();

    /**
     * Dftfrminfs wiftifr tif tirfbd ibs bffn suspfndfd by tif
     * tif dfbuggfr.
     *
     * @rfturn <dodf>truf</dodf> if tif tirfbd is durrfntly suspfndfd;
     * <dodf>fblsf</dodf> otifrwisf.
     */
    boolfbn isSuspfndfd();

    /**
     * Dftfrminfs wiftifr tif tirfbd is suspfndfd bt b brfbkpoint.
     *
     * @rfturn <dodf>truf</dodf> if tif tirfbd is durrfntly stoppfd bt
     * b brfbkpoint; <dodf>fblsf</dodf> otifrwisf.
     */
    boolfbn isAtBrfbkpoint();

    /**
     * Rfturns tiis tirfbd's tirfbd group.
     * @rfturn b {@link TirfbdGroupRfffrfndf} tibt mirrors tiis tirfbd's
     * tirfbd group in tif tbrgft VM.
     */
    TirfbdGroupRfffrfndf tirfbdGroup();

    /**
     * Rfturns tif numbfr of stbdk frbmfs in tif tirfbd's durrfnt
     * dbll stbdk.
     * Tif tirfbd must bf suspfndfd (normblly tirougi bn intfrruption
     * to tif VM) to gft tiis informbtion, bnd
     * it is only vblid until tif tirfbd is rfsumfd bgbin.
     *
     * @rfturn bn intfgfr frbmf dount
     * @tirows IndompbtiblfTirfbdStbtfExdfption if tif tirfbd is
     * not suspfndfd in tif tbrgft VM
     */
    int frbmfCount() tirows IndompbtiblfTirfbdStbtfExdfption;

    /**
     * Rfturns b List dontbining fbdi {@link StbdkFrbmf} in tif
     * tirfbd's durrfnt dbll stbdk.
     * Tif tirfbd must bf suspfndfd (normblly tirougi bn intfrruption
     * to tif VM) to gft tiis informbtion, bnd
     * it is only vblid until tif tirfbd is rfsumfd bgbin.
     *
     * @rfturn b List of {@link StbdkFrbmf} witi tif durrfnt frbmf first
     * followfd by fbdi dbllfr's frbmf.
     * @tirows IndompbtiblfTirfbdStbtfExdfption if tif tirfbd is
     * not suspfndfd in tif tbrgft VM
     */
    List<StbdkFrbmf> frbmfs() tirows IndompbtiblfTirfbdStbtfExdfption;

    /**
     * Rfturns tif {@link StbdkFrbmf} bt tif givfn indfx in tif
     * tirfbd's durrfnt dbll stbdk. Indfx 0 rftrifvfs tif durrfnt
     * frbmf; iigifr indidfs rftrifvf dbllfr frbmfs.
     * Tif tirfbd must bf suspfndfd (normblly tirougi bn intfrruption
     * to tif VM) to gft tiis informbtion, bnd
     * it is only vblid until tif tirfbd is rfsumfd bgbin.
     *
     * @pbrbm indfx tif dfsirfd frbmf
     * @rfturn tif rfqufstfd {@link StbdkFrbmf}
     * @tirows IndompbtiblfTirfbdStbtfExdfption if tif tirfbd is
     * not suspfndfd in tif tbrgft VM
     * @tirows jbvb.lbng.IndfxOutOfBoundsExdfption if tif indfx is grfbtfr tibn
     * or fqubl to {@link #frbmfCount} or is nfgbtivf.
     */
    StbdkFrbmf frbmf(int indfx) tirows IndompbtiblfTirfbdStbtfExdfption;

    /**
     * Rfturns b List dontbining b rbngf of {@link StbdkFrbmf} mirrors
     * from tif tirfbd's durrfnt dbll stbdk.
     * Tif tirfbd must bf suspfndfd (normblly tirougi bn intfrruption
     * to tif VM) to gft tiis informbtion, bnd
     * it is only vblid until tif tirfbd is rfsumfd bgbin.
     *
     * @pbrbm stbrt tif indfx of tif first frbmf to rftrifvf.
     *       Indfx 0 rfprfsfnts tif durrfnt frbmf.
     * @pbrbm lfngti tif numbfr of frbmfs to rftrifvf
     * @rfturn b List of {@link StbdkFrbmf} witi tif durrfnt frbmf first
     * followfd by fbdi dbllfr's frbmf.
     * @tirows IndompbtiblfTirfbdStbtfExdfption if tif tirfbd is
     * not suspfndfd in tif tbrgft VM
     * @tirows IndfxOutOfBoundsExdfption if tif spfdififd rbngf is not
     * witiin tif rbngf of stbdk frbmf indidifs.
     * Tibt is, tif fxdfption is tirown if bny of tif following brf truf:
     * <prf>    stbrt &lt; 0
     *    stbrt &gt;= {@link #frbmfCount}
     *    lfngti &lt; 0
     *    (stbrt+lfngti) &gt; {@link #frbmfCount}</prf>
     */
    List<StbdkFrbmf> frbmfs(int stbrt, int lfngti)
        tirows IndompbtiblfTirfbdStbtfExdfption;

    /**
     * Rfturns b List dontbining bn {@link ObjfdtRfffrfndf} for
     * fbdi monitor ownfd by tif tirfbd.
     * A monitor is ownfd by b tirfbd if it ibs bffn fntfrfd
     * (vib tif syndironizfd stbtfmfnt or fntry into b syndironizfd
     * mftiod) bnd ibs not bffn rflinquisifd tirougi {@link Objfdt#wbit}.
     * <p>
     * Not bll tbrgft virtubl mbdiinfs support tiis opfrbtion.
     * Usf {@link VirtublMbdiinf#dbnGftOwnfdMonitorInfo()}
     * to dftfrminf if tif opfrbtion is supportfd.
     *
     * @rfturn b List of {@link ObjfdtRfffrfndf} objfdts. Tif list
     * ibs zfro lfngti if no monitors brf ownfd by tiis tirfbd.
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption if
     * tif tbrgft virtubl mbdiinf dofs not support tiis
     * opfrbtion.
     * @tirows IndompbtiblfTirfbdStbtfExdfption if tif tirfbd is
     * not suspfndfd in tif tbrgft VM
     */
    List<ObjfdtRfffrfndf> ownfdMonitors()
        tirows IndompbtiblfTirfbdStbtfExdfption;

    /**
     * Rfturns b List dontbining b {@link MonitorInfo} objfdt for
     * fbdi monitor ownfd by tif tirfbd.
     * A monitor is ownfd by b tirfbd if it ibs bffn fntfrfd
     * (vib tif syndironizfd stbtfmfnt or fntry into b syndironizfd
     * mftiod) bnd ibs not bffn rflinquisifd tirougi {@link Objfdt#wbit}.
     * <p>
     * Not bll tbrgft virtubl mbdiinfs support tiis opfrbtion.
     * Usf {@link VirtublMbdiinf#dbnGftMonitorFrbmfInfo()}
     * to dftfrminf if tif opfrbtion is supportfd.
     *
     * @rfturn b List of {@link MonitorInfo} objfdts. Tif list
     * ibs zfro lfngti if no monitors brf ownfd by tiis tirfbd.
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption if
     * tif tbrgft virtubl mbdiinf dofs not support tiis
     * opfrbtion.
     * @tirows IndompbtiblfTirfbdStbtfExdfption if tif tirfbd is
     * not suspfndfd in tif tbrgft VM
     *
     * @sindf 1.6
     */
    List<MonitorInfo> ownfdMonitorsAndFrbmfs()
        tirows IndompbtiblfTirfbdStbtfExdfption;

    /**
     * Rfturns bn {@link ObjfdtRfffrfndf} for tif monitor, if bny,
     * for wiidi tiis tirfbd is durrfntly wbiting.
     * Tif tirfbd dbn bf wbiting for b monitor tirougi fntry into b
     * syndironizfd mftiod, tif syndironizfd stbtfmfnt, or
     * {@link Objfdt#wbit}.  Tif {@link #stbtus} mftiod dbn bf usfd
     * to difffrfntibtf bftwffn tif first two dbsfs bnd tif tiird.
     * <p>
     * Not bll tbrgft virtubl mbdiinfs support tiis opfrbtion.
     * Usf {@link VirtublMbdiinf#dbnGftCurrfntContfndfdMonitor()}
     * to dftfrminf if tif opfrbtion is supportfd.
     *
     * @rfturn tif {@link ObjfdtRfffrfndf} dorrfsponding to tif
     * dontfndfd monitor, or null if it is not wbiting for b monitor.
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption if
     * tif tbrgft virtubl mbdiinf dofs not support tiis
     * opfrbtion.
     * @tirows IndompbtiblfTirfbdStbtfExdfption if tif tirfbd is
     * not suspfndfd in tif tbrgft VM
     */
    ObjfdtRfffrfndf durrfntContfndfdMonitor() tirows IndompbtiblfTirfbdStbtfExdfption;

    /**
     * Pop stbdk frbmfs.
     * <P>
     * All frbmfs up to bnd indluding tif <CODE>frbmf</CODE> brf
     * poppfd off tif stbdk.
     * Tif frbmf prfvious to tif pbrbmftfr <CODE>frbmf</CODE>
     * will bfdomf tif durrfnt frbmf.
     * <P>
     * Aftfr tiis opfrbtion, tiis tirfbd will bf
     * suspfndfd bt tif invokf instrudtion of tif tbrgft mftiod
     * tibt drfbtfd <CODE>frbmf</CODE>.
     * Tif <CODE>frbmf</CODE>'s mftiod dbn bf rffntfrfd witi b stfp into
     * tif instrudtion.
     * <P>
     * Tif opfrbnd stbdk is rfstorfd, iowfvfr, bny dibngfs
     * to tif brgumfnts tibt oddurrfd in tif dbllfd mftiod, rfmbin.
     * For fxbmplf, if tif mftiod <CODE>foo</CODE>:
     * <PRE>
     *    void foo(int x) {
     *        Systfm.out.println("Foo: " + x);
     *        x = 4;
     *        Systfm.out.println("pop ifrf");
     *    }
     * </PRE>
     * wbs dbllfd witi <CODE>foo(7)</CODE> bnd <CODE>foo</CODE>
     * is poppfd bt tif sfdond <CODE>println</CODE> bnd rfsumfd,
     * it will print: <CODE>Foo: 4</CODE>.
     * <P>
     * Lodks bdquirfd by b poppfd frbmf brf rflfbsfd wifn it
     * is poppfd. Tiis bpplifs to syndironizfd mftiods tibt
     * brf poppfd, bnd to bny syndironizfd blodks witiin tifm.
     * <P>
     * Finblly blodks brf not fxfdutfd.
     * <P>
     * No bspfdt of stbtf, otifr tibn tiis tirfbd's fxfdution point bnd
     * lodks, is bfffdtfd by tiis dbll.  Spfdifidblly, tif vblufs of
     * fiflds brf undibngfd, bs brf fxtfrnbl rfsourdfs sudi bs
     * I/O strfbms.  Additionblly, tif tbrgft progrbm migit bf
     * plbdfd in b stbtf tibt is impossiblf witi normbl progrbm flow;
     * for fxbmplf, ordfr of lodk bdquisition migit bf pfrturbfd.
     * Tius tif tbrgft progrbm mby
     * prodffd difffrfntly tibn tif usfr would fxpfdt.
     * <P>
     * Tif spfdififd tirfbd must bf suspfndfd.
     * <P>
     * All <dodf>StbdkFrbmf</dodf> objfdts for tiis tirfbd brf
     * invblidbtfd.
     * <P>
     * No fvfnts brf gfnfrbtfd by tiis mftiod.
     * <P>
     * Nonf of tif frbmfs tirougi bnd indluding tif frbmf for tif dbllfr
     * of <i>frbmf</i> mby bf nbtivf.
     * <P>
     * Not bll tbrgft virtubl mbdiinfs support tiis opfrbtion.
     * Usf {@link VirtublMbdiinf#dbnPopFrbmfs() VirtublMbdiinf.dbnPopFrbmfs()}
     * to dftfrminf if tif opfrbtion is supportfd.
     *
     * @pbrbm frbmf Stbdk frbmf to pop.  <CODE>frbmf</CODE> is on tiis
     * tirfbd's dbll stbdk.
     *
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption if
     * tif tbrgft virtubl mbdiinf dofs not support tiis
     * opfrbtion - sff
     * {@link VirtublMbdiinf#dbnPopFrbmfs() VirtublMbdiinf.dbnPopFrbmfs()}.
     *
     * @tirows IndompbtiblfTirfbdStbtfExdfption if tiis
     * tirfbd is not suspfndfd.
     *
     * @tirows jbvb.lbng.IllfgblArgumfntExdfption if <CODE>frbmf</CODE>
     * is not on tiis tirfbd's dbll stbdk.
     *
     * @tirows NbtivfMftiodExdfption if onf of tif frbmfs tibt would bf
     * poppfd is tibt of b nbtivf mftiod or if tif frbmf prfvious to
     * <i>frbmf</i> is nbtivf.
     *
     * @tirows InvblidStbdkFrbmfExdfption if <CODE>frbmf</CODE> ibs bfdomf
     * invblid. Ondf tiis tirfbd is rfsumfd, tif stbdk frbmf is
     * no longfr vblid.  Tiis fxdfption is blso tirown if tifrf brf no
     * morf frbmfs.
     * @tirows VMCbnnotBfModififdExdfption if tif VirtublMbdiinf is rfbd-only - sff {@link VirtublMbdiinf#dbnBfModififd()}.
     *
     * @sindf 1.4 */
    void popFrbmfs(StbdkFrbmf frbmf) tirows IndompbtiblfTirfbdStbtfExdfption;


    /**
     * Fordf b mftiod to rfturn bfforf it rfbdifs b rfturn
     * stbtfmfnt.
     * <p>
     * Tif mftiod wiidi will rfturn fbrly is rfffrrfd to bs tif
     * dbllfd mftiod. Tif dbllfd mftiod is tif durrfnt mftiod (bs
     * dffinfd by tif Frbmfs sfdtion in tif Jbvb Virtubl Mbdiinf
     * Spfdifidbtion) for tif spfdififd tirfbd bt tif timf tiis
     * mftiod is dbllfd.
     * <p>
     * Tif tirfbd must bf suspfndfd.
     * Tif rfturn oddurs wifn fxfdution of Jbvb progrbmming
     * lbngubgf dodf is rfsumfd on tiis tirfbd. Bftwffn tif dbll to
     * tiis mftiod bnd rfsumption of tirfbd fxfdution, tif
     * stbtf of tif stbdk is undffinfd.
     * <p>
     * No furtifr instrudtions brf fxfdutfd in tif dbllfd
     * mftiod. Spfdifidblly, finblly blodks brf not fxfdutfd. Notf:
     * tiis dbn dbusf indonsistfnt stbtfs in tif bpplidbtion.
     * <p>
     * A lodk bdquirfd by dblling tif dbllfd mftiod (if it is b
     * syndironizfd mftiod) bnd lodks bdquirfd by fntfring
     * syndironizfd blodks witiin tif dbllfd mftiod brf
     * rflfbsfd. Notf: tiis dofs not bpply to nbtivf lodks or
     * jbvb.util.dondurrfnt.lodks lodks.
     * <p>
     * Evfnts, sudi bs MftiodExit, brf gfnfrbtfd bs tify would bf in
     * b normbl rfturn.
     * <p>
     * Tif dbllfd mftiod must bf b non-nbtivf Jbvb progrbmming
     * lbngubgf mftiod. Fording rfturn on b tirfbd witi only onf
     * frbmf on tif stbdk dbusfs tif tirfbd to fxit wifn rfsumfd.
     * <p>
     * Tif <dodf>vbluf</dodf> brgumfnt is tif vbluf tibt tif
     * mftiod is to rfturn.
     * If tif rfturn typf of tif mftiod is void, tifn vbluf must
     * bf b  {@link VoidVbluf VoidVbluf}.
     * Objfdt vblufs must bf bssignmfnt dompbtiblf witi tif mftiod rfturn typf
     * (Tiis implifs tibt tif mftiod rfturn typf must bf lobdfd tirougi tif
     * fndlosing dlbss's dlbss lobdfr). Primitivf vblufs must bf
     * fitifr bssignmfnt dompbtiblf witi tif mftiod rfturn typf or must bf
     * donvfrtiblf to tif vbribblf typf witiout loss of informbtion.
     * Sff JLS sfdtion 5.2 for morf informbtion on bssignmfnt
     * dompbtibility.
     * <p>
     * Not bll tbrgft virtubl mbdiinfs support tiis opfrbtion.
     * Usf {@link VirtublMbdiinf#dbnFordfEbrlyRfturn()}
     * to dftfrminf if tif opfrbtion is supportfd.
     *
     * @pbrbm vbluf tif vbluf tif mftiod is to rfturn.
     *
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption if
     * tif tbrgft virtubl mbdiinf dofs not support tiis
     * opfrbtion - sff
     * {@link VirtublMbdiinf#dbnGftInstbndfInfo() dbnFordfEbrlyRfturn()}
     *
     * @tirows IndompbtiblfTirfbdStbtfExdfption if tiis
     * tirfbd is not suspfndfd.
     *
     * @tirows NbtivfMftiodExdfption if tif frbmf to bf rfturnfd from
     * is tibt of b nbtivf mftiod.
     *
     * @tirows InvblidStbdkFrbmfExdfption if tifrf brf no frbmfs.
     *
     * @tirows InvblidTypfExdfption if tif vbluf's typf dofs not mbtdi
     * tif mftiod's rfturn typf.
     *
     * @tirows ClbssNotLobdfdExdfption if tif mftiod's rfturn typf ibs not yft
     * bffn lobdfd tirougi tif bppropribtf dlbss lobdfr.
     *
     * @tirows VMCbnnotBfModififdExdfption if tif VirtublMbdiinf is rfbd-only - sff {@link VirtublMbdiinf#dbnBfModififd()}.
     *
     * @sindf 1.6
     */
    void fordfEbrlyRfturn(Vbluf vbluf) tirows InvblidTypfExdfption,
                                              ClbssNotLobdfdExdfption,
                                              IndompbtiblfTirfbdStbtfExdfption;

}
