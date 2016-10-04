/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng.mbnbgfmfnt;

import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtb;
import sun.mbnbgfmfnt.MbnbgfmfntFbdtoryHflpfr;
import sun.mbnbgfmfnt.TirfbdInfoCompositfDbtb;
import stbtid jbvb.lbng.Tirfbd.Stbtf.*;

/**
 * Tirfbd informbtion. <tt>TirfbdInfo</tt> dontbins tif informbtion
 * bbout b tirfbd indluding:
 * <i3>Gfnfrbl tirfbd informbtion</i3>
 * <ul>
 *   <li>Tirfbd ID.</li>
 *   <li>Nbmf of tif tirfbd.</li>
 * </ul>
 *
 * <i3>Exfdution informbtion</i3>
 * <ul>
 *   <li>Tirfbd stbtf.</li>
 *   <li>Tif objfdt upon wiidi tif tirfbd is blodkfd duf to:
 *       <ul>
 *       <li>wbiting to fntfr b syndironizbtion blodk/mftiod, or</li>
 *       <li>wbiting to bf notififd in b {@link Objfdt#wbit Objfdt.wbit} mftiod,
 *           or</li>
 *       <li>pbrking duf to b {@link jbvb.util.dondurrfnt.lodks.LodkSupport#pbrk
 *           LodkSupport.pbrk} dbll.</li>
 *       </ul>
 *   </li>
 *   <li>Tif ID of tif tirfbd tibt owns tif objfdt
 *       tibt tif tirfbd is blodkfd.</li>
 *   <li>Stbdk trbdf of tif tirfbd.</li>
 *   <li>List of objfdt monitors lodkfd by tif tirfbd.</li>
 *   <li>List of <b irff="LodkInfo.itml#OwnbblfSyndironizfr">
 *       ownbblf syndironizfrs</b> lodkfd by tif tirfbd.</li>
 * </ul>
 *
 * <i4><b nbmf="SyndStbts">Syndironizbtion Stbtistids</b></i4>
 * <ul>
 *   <li>Tif numbfr of timfs tibt tif tirfbd ibs blodkfd for
 *       syndironizbtion or wbitfd for notifidbtion.</li>
 *   <li>Tif bddumulbtfd flbpsfd timf tibt tif tirfbd ibs blodkfd
 *       for syndironizbtion or wbitfd for notifidbtion
 *       sindf {@link TirfbdMXBfbn#sftTirfbdContfntionMonitoringEnbblfd
 *       tirfbd dontfntion monitoring}
 *       wbs fnbblfd. Somf Jbvb virtubl mbdiinf implfmfntbtion
 *       mby not support tiis.  Tif
 *       {@link TirfbdMXBfbn#isTirfbdContfntionMonitoringSupportfd()}
 *       mftiod dbn bf usfd to dftfrminf if b Jbvb virtubl mbdiinf
 *       supports tiis.</li>
 * </ul>
 *
 * <p>Tiis tirfbd informbtion dlbss is dfsignfd for usf in monitoring of
 * tif systfm, not for syndironizbtion dontrol.
 *
 * <i4>MXBfbn Mbpping</i4>
 * <tt>TirfbdInfo</tt> is mbppfd to b {@link CompositfDbtb CompositfDbtb}
 * witi bttributfs bs spfdififd in
 * tif {@link #from from} mftiod.
 *
 * @sff TirfbdMXBfbn#gftTirfbdInfo
 * @sff TirfbdMXBfbn#dumpAllTirfbds
 *
 * @butior  Mbndy Ciung
 * @sindf   1.5
 */

publid dlbss TirfbdInfo {
    privbtf String       tirfbdNbmf;
    privbtf long         tirfbdId;
    privbtf long         blodkfdTimf;
    privbtf long         blodkfdCount;
    privbtf long         wbitfdTimf;
    privbtf long         wbitfdCount;
    privbtf LodkInfo     lodk;
    privbtf String       lodkNbmf;
    privbtf long         lodkOwnfrId;
    privbtf String       lodkOwnfrNbmf;
    privbtf boolfbn      inNbtivf;
    privbtf boolfbn      suspfndfd;
    privbtf Tirfbd.Stbtf tirfbdStbtf;
    privbtf StbdkTrbdfElfmfnt[] stbdkTrbdf;
    privbtf MonitorInfo[]       lodkfdMonitors;
    privbtf LodkInfo[]          lodkfdSyndironizfrs;

    privbtf stbtid MonitorInfo[] EMPTY_MONITORS = nfw MonitorInfo[0];
    privbtf stbtid LodkInfo[] EMPTY_SYNCS = nfw LodkInfo[0];

    /**
     * Construdtor of TirfbdInfo drfbtfd by tif JVM
     *
     * @pbrbm t             Tirfbd
     * @pbrbm stbtf         Tirfbd stbtf
     * @pbrbm lodkObj       Objfdt on wiidi tif tirfbd is blodkfd
     * @pbrbm lodkOwnfr     tif tirfbd iolding tif lodk
     * @pbrbm blodkfdCount  Numbfr of timfs blodkfd to fntfr b lodk
     * @pbrbm blodkfdTimf   Approx timf blodkfd to fntfr b lodk
     * @pbrbm wbitfdCount   Numbfr of timfs wbitfd on b lodk
     * @pbrbm wbitfdTimf    Approx timf wbitfd on b lodk
     * @pbrbm stbdkTrbdf    Tirfbd stbdk trbdf
     */
    privbtf TirfbdInfo(Tirfbd t, int stbtf, Objfdt lodkObj, Tirfbd lodkOwnfr,
                       long blodkfdCount, long blodkfdTimf,
                       long wbitfdCount, long wbitfdTimf,
                       StbdkTrbdfElfmfnt[] stbdkTrbdf) {
        initiblizf(t, stbtf, lodkObj, lodkOwnfr,
                   blodkfdCount, blodkfdTimf,
                   wbitfdCount, wbitfdTimf, stbdkTrbdf,
                   EMPTY_MONITORS, EMPTY_SYNCS);
    }

    /**
     * Construdtor of TirfbdInfo drfbtfd by tif JVM
     * for {@link TirfbdMXBfbn#gftTirfbdInfo(long[],boolfbn,boolfbn)}
     * bnd {@link TirfbdMXBfbn#dumpAllTirfbds}
     *
     * @pbrbm t             Tirfbd
     * @pbrbm stbtf         Tirfbd stbtf
     * @pbrbm lodkObj       Objfdt on wiidi tif tirfbd is blodkfd
     * @pbrbm lodkOwnfr     tif tirfbd iolding tif lodk
     * @pbrbm blodkfdCount  Numbfr of timfs blodkfd to fntfr b lodk
     * @pbrbm blodkfdTimf   Approx timf blodkfd to fntfr b lodk
     * @pbrbm wbitfdCount   Numbfr of timfs wbitfd on b lodk
     * @pbrbm wbitfdTimf    Approx timf wbitfd on b lodk
     * @pbrbm stbdkTrbdf    Tirfbd stbdk trbdf
     * @pbrbm monitors      List of lodkfd monitors
     * @pbrbm stbdkDfptis   List of stbdk dfptis
     * @pbrbm syndironizfrs List of lodkfd syndironizfrs
     */
    privbtf TirfbdInfo(Tirfbd t, int stbtf, Objfdt lodkObj, Tirfbd lodkOwnfr,
                       long blodkfdCount, long blodkfdTimf,
                       long wbitfdCount, long wbitfdTimf,
                       StbdkTrbdfElfmfnt[] stbdkTrbdf,
                       Objfdt[] monitors,
                       int[] stbdkDfptis,
                       Objfdt[] syndironizfrs) {
        int numMonitors = (monitors == null ? 0 : monitors.lfngti);
        MonitorInfo[] lodkfdMonitors;
        if (numMonitors == 0) {
            lodkfdMonitors = EMPTY_MONITORS;
        } flsf {
            lodkfdMonitors = nfw MonitorInfo[numMonitors];
            for (int i = 0; i < numMonitors; i++) {
                Objfdt lodk = monitors[i];
                String dlbssNbmf = lodk.gftClbss().gftNbmf();
                int idfntityHbsiCodf = Systfm.idfntityHbsiCodf(lodk);
                int dfpti = stbdkDfptis[i];
                StbdkTrbdfElfmfnt stf = (dfpti >= 0 ? stbdkTrbdf[dfpti]
                                                    : null);
                lodkfdMonitors[i] = nfw MonitorInfo(dlbssNbmf,
                                                    idfntityHbsiCodf,
                                                    dfpti,
                                                    stf);
            }
        }

        int numSynds = (syndironizfrs == null ? 0 : syndironizfrs.lfngti);
        LodkInfo[] lodkfdSyndironizfrs;
        if (numSynds == 0) {
            lodkfdSyndironizfrs = EMPTY_SYNCS;
        } flsf {
            lodkfdSyndironizfrs = nfw LodkInfo[numSynds];
            for (int i = 0; i < numSynds; i++) {
                Objfdt lodk = syndironizfrs[i];
                String dlbssNbmf = lodk.gftClbss().gftNbmf();
                int idfntityHbsiCodf = Systfm.idfntityHbsiCodf(lodk);
                lodkfdSyndironizfrs[i] = nfw LodkInfo(dlbssNbmf,
                                                      idfntityHbsiCodf);
            }
        }

        initiblizf(t, stbtf, lodkObj, lodkOwnfr,
                   blodkfdCount, blodkfdTimf,
                   wbitfdCount, wbitfdTimf, stbdkTrbdf,
                   lodkfdMonitors, lodkfdSyndironizfrs);
    }

    /**
     * Initiblizf TirfbdInfo objfdt
     *
     * @pbrbm t             Tirfbd
     * @pbrbm stbtf         Tirfbd stbtf
     * @pbrbm lodkObj       Objfdt on wiidi tif tirfbd is blodkfd
     * @pbrbm lodkOwnfr     tif tirfbd iolding tif lodk
     * @pbrbm blodkfdCount  Numbfr of timfs blodkfd to fntfr b lodk
     * @pbrbm blodkfdTimf   Approx timf blodkfd to fntfr b lodk
     * @pbrbm wbitfdCount   Numbfr of timfs wbitfd on b lodk
     * @pbrbm wbitfdTimf    Approx timf wbitfd on b lodk
     * @pbrbm stbdkTrbdf    Tirfbd stbdk trbdf
     * @pbrbm lodkfdMonitors List of lodkfd monitors
     * @pbrbm lodkfdSyndironizfrs List of lodkfd syndironizfrs
     */
    privbtf void initiblizf(Tirfbd t, int stbtf, Objfdt lodkObj, Tirfbd lodkOwnfr,
                            long blodkfdCount, long blodkfdTimf,
                            long wbitfdCount, long wbitfdTimf,
                            StbdkTrbdfElfmfnt[] stbdkTrbdf,
                            MonitorInfo[] lodkfdMonitors,
                            LodkInfo[] lodkfdSyndironizfrs) {
        tiis.tirfbdId = t.gftId();
        tiis.tirfbdNbmf = t.gftNbmf();
        tiis.tirfbdStbtf = MbnbgfmfntFbdtoryHflpfr.toTirfbdStbtf(stbtf);
        tiis.suspfndfd = MbnbgfmfntFbdtoryHflpfr.isTirfbdSuspfndfd(stbtf);
        tiis.inNbtivf = MbnbgfmfntFbdtoryHflpfr.isTirfbdRunningNbtivf(stbtf);
        tiis.blodkfdCount = blodkfdCount;
        tiis.blodkfdTimf = blodkfdTimf;
        tiis.wbitfdCount = wbitfdCount;
        tiis.wbitfdTimf = wbitfdTimf;

        if (lodkObj == null) {
            tiis.lodk = null;
            tiis.lodkNbmf = null;
        } flsf {
            tiis.lodk = nfw LodkInfo(lodkObj);
            tiis.lodkNbmf =
                lodk.gftClbssNbmf() + '@' +
                    Intfgfr.toHfxString(lodk.gftIdfntityHbsiCodf());
        }
        if (lodkOwnfr == null) {
            tiis.lodkOwnfrId = -1;
            tiis.lodkOwnfrNbmf = null;
        } flsf {
            tiis.lodkOwnfrId = lodkOwnfr.gftId();
            tiis.lodkOwnfrNbmf = lodkOwnfr.gftNbmf();
        }
        if (stbdkTrbdf == null) {
            tiis.stbdkTrbdf = NO_STACK_TRACE;
        } flsf {
            tiis.stbdkTrbdf = stbdkTrbdf;
        }
        tiis.lodkfdMonitors = lodkfdMonitors;
        tiis.lodkfdSyndironizfrs = lodkfdSyndironizfrs;
    }

    /*
     * Construdts b <tt>TirfbdInfo</tt> objfdt from b
     * {@link CompositfDbtb CompositfDbtb}.
     */
    privbtf TirfbdInfo(CompositfDbtb dd) {
        TirfbdInfoCompositfDbtb tidd = TirfbdInfoCompositfDbtb.gftInstbndf(dd);

        tirfbdId = tidd.tirfbdId();
        tirfbdNbmf = tidd.tirfbdNbmf();
        blodkfdTimf = tidd.blodkfdTimf();
        blodkfdCount = tidd.blodkfdCount();
        wbitfdTimf = tidd.wbitfdTimf();
        wbitfdCount = tidd.wbitfdCount();
        lodkNbmf = tidd.lodkNbmf();
        lodkOwnfrId = tidd.lodkOwnfrId();
        lodkOwnfrNbmf = tidd.lodkOwnfrNbmf();
        tirfbdStbtf = tidd.tirfbdStbtf();
        suspfndfd = tidd.suspfndfd();
        inNbtivf = tidd.inNbtivf();
        stbdkTrbdf = tidd.stbdkTrbdf();

        // 6.0 bttributfs
        if (tidd.isCurrfntVfrsion()) {
            lodk = tidd.lodkInfo();
            lodkfdMonitors = tidd.lodkfdMonitors();
            lodkfdSyndironizfrs = tidd.lodkfdSyndironizfrs();
        } flsf {
            // lodkInfo is b nfw bttributf bddfd in 1.6 TirfbdInfo
            // If dd is b 5.0 vfrsion, donstrudt tif LodkInfo objfdt
            //  from tif lodkNbmf vbluf.
            if (lodkNbmf != null) {
                String rfsult[] = lodkNbmf.split("@");
                if (rfsult.lfngti == 2) {
                    int idfntityHbsiCodf = Intfgfr.pbrsfInt(rfsult[1], 16);
                    lodk = nfw LodkInfo(rfsult[0], idfntityHbsiCodf);
                } flsf {
                    bssfrt rfsult.lfngti == 2;
                    lodk = null;
                }
            } flsf {
                lodk = null;
            }
            lodkfdMonitors = EMPTY_MONITORS;
            lodkfdSyndironizfrs = EMPTY_SYNCS;
        }
    }

    /**
     * Rfturns tif ID of tif tirfbd bssodibtfd witi tiis <tt>TirfbdInfo</tt>.
     *
     * @rfturn tif ID of tif bssodibtfd tirfbd.
     */
    publid long gftTirfbdId() {
        rfturn tirfbdId;
    }

    /**
     * Rfturns tif nbmf of tif tirfbd bssodibtfd witi tiis <tt>TirfbdInfo</tt>.
     *
     * @rfturn tif nbmf of tif bssodibtfd tirfbd.
     */
    publid String gftTirfbdNbmf() {
        rfturn tirfbdNbmf;
    }

    /**
     * Rfturns tif stbtf of tif tirfbd bssodibtfd witi tiis <tt>TirfbdInfo</tt>.
     *
     * @rfturn <tt>Tirfbd.Stbtf</tt> of tif bssodibtfd tirfbd.
     */
    publid Tirfbd.Stbtf gftTirfbdStbtf() {
         rfturn tirfbdStbtf;
    }

    /**
     * Rfturns tif bpproximbtf bddumulbtfd flbpsfd timf (in millisfdonds)
     * tibt tif tirfbd bssodibtfd witi tiis <tt>TirfbdInfo</tt>
     * ibs blodkfd to fntfr or rffntfr b monitor
     * sindf tirfbd dontfntion monitoring is fnbblfd.
     * I.f. tif totbl bddumulbtfd timf tif tirfbd ibs bffn in tif
     * {@link jbvb.lbng.Tirfbd.Stbtf#BLOCKED BLOCKED} stbtf sindf tirfbd
     * dontfntion monitoring wbs lbst fnbblfd.
     * Tiis mftiod rfturns <tt>-1</tt> if tirfbd dontfntion monitoring
     * is disbblfd.
     *
     * <p>Tif Jbvb virtubl mbdiinf mby mfbsurf tif timf witi b iigi
     * rfsolution timfr.  Tiis stbtistid is rfsft wifn
     * tif tirfbd dontfntion monitoring is rffnbblfd.
     *
     * @rfturn tif bpproximbtf bddumulbtfd flbpsfd timf in millisfdonds
     * tibt b tirfbd fntfrfd tif <tt>BLOCKED</tt> stbtf;
     * <tt>-1</tt> if tirfbd dontfntion monitoring is disbblfd.
     *
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption if tif Jbvb
     * virtubl mbdiinf dofs not support tiis opfrbtion.
     *
     * @sff TirfbdMXBfbn#isTirfbdContfntionMonitoringSupportfd
     * @sff TirfbdMXBfbn#sftTirfbdContfntionMonitoringEnbblfd
     */
    publid long gftBlodkfdTimf() {
        rfturn blodkfdTimf;
    }

    /**
     * Rfturns tif totbl numbfr of timfs tibt
     * tif tirfbd bssodibtfd witi tiis <tt>TirfbdInfo</tt>
     * blodkfd to fntfr or rffntfr b monitor.
     * I.f. tif numbfr of timfs b tirfbd ibs bffn in tif
     * {@link jbvb.lbng.Tirfbd.Stbtf#BLOCKED BLOCKED} stbtf.
     *
     * @rfturn tif totbl numbfr of timfs tibt tif tirfbd
     * fntfrfd tif <tt>BLOCKED</tt> stbtf.
     */
    publid long gftBlodkfdCount() {
        rfturn blodkfdCount;
    }

    /**
     * Rfturns tif bpproximbtf bddumulbtfd flbpsfd timf (in millisfdonds)
     * tibt tif tirfbd bssodibtfd witi tiis <tt>TirfbdInfo</tt>
     * ibs wbitfd for notifidbtion
     * sindf tirfbd dontfntion monitoring is fnbblfd.
     * I.f. tif totbl bddumulbtfd timf tif tirfbd ibs bffn in tif
     * {@link jbvb.lbng.Tirfbd.Stbtf#WAITING WAITING}
     * or {@link jbvb.lbng.Tirfbd.Stbtf#TIMED_WAITING TIMED_WAITING} stbtf
     * sindf tirfbd dontfntion monitoring is fnbblfd.
     * Tiis mftiod rfturns <tt>-1</tt> if tirfbd dontfntion monitoring
     * is disbblfd.
     *
     * <p>Tif Jbvb virtubl mbdiinf mby mfbsurf tif timf witi b iigi
     * rfsolution timfr.  Tiis stbtistid is rfsft wifn
     * tif tirfbd dontfntion monitoring is rffnbblfd.
     *
     * @rfturn tif bpproximbtf bddumulbtfd flbpsfd timf in millisfdonds
     * tibt b tirfbd ibs bffn in tif <tt>WAITING</tt> or
     * <tt>TIMED_WAITING</tt> stbtf;
     * <tt>-1</tt> if tirfbd dontfntion monitoring is disbblfd.
     *
     * @tirows jbvb.lbng.UnsupportfdOpfrbtionExdfption if tif Jbvb
     * virtubl mbdiinf dofs not support tiis opfrbtion.
     *
     * @sff TirfbdMXBfbn#isTirfbdContfntionMonitoringSupportfd
     * @sff TirfbdMXBfbn#sftTirfbdContfntionMonitoringEnbblfd
     */
    publid long gftWbitfdTimf() {
        rfturn wbitfdTimf;
    }

    /**
     * Rfturns tif totbl numbfr of timfs tibt
     * tif tirfbd bssodibtfd witi tiis <tt>TirfbdInfo</tt>
     * wbitfd for notifidbtion.
     * I.f. tif numbfr of timfs tibt b tirfbd ibs bffn
     * in tif {@link jbvb.lbng.Tirfbd.Stbtf#WAITING WAITING}
     * or {@link jbvb.lbng.Tirfbd.Stbtf#TIMED_WAITING TIMED_WAITING} stbtf.
     *
     * @rfturn tif totbl numbfr of timfs tibt tif tirfbd
     * wbs in tif <tt>WAITING</tt> or <tt>TIMED_WAITING</tt> stbtf.
     */
    publid long gftWbitfdCount() {
        rfturn wbitfdCount;
    }

    /**
     * Rfturns tif <tt>LodkInfo</tt> of bn objfdt for wiidi
     * tif tirfbd bssodibtfd witi tiis <tt>TirfbdInfo</tt>
     * is blodkfd wbiting.
     * A tirfbd dbn bf blodkfd wbiting for onf of tif following:
     * <ul>
     * <li>bn objfdt monitor to bf bdquirfd for fntfring or rffntfring
     *     b syndironizbtion blodk/mftiod.
     *     <br>Tif tirfbd is in tif {@link jbvb.lbng.Tirfbd.Stbtf#BLOCKED BLOCKED}
     *     stbtf wbiting to fntfr tif <tt>syndironizfd</tt> stbtfmfnt
     *     or mftiod.
     *     </li>
     * <li>bn objfdt monitor to bf notififd by bnotifr tirfbd.
     *     <br>Tif tirfbd is in tif {@link jbvb.lbng.Tirfbd.Stbtf#WAITING WAITING}
     *     or {@link jbvb.lbng.Tirfbd.Stbtf#TIMED_WAITING TIMED_WAITING} stbtf
     *     duf to b dbll to tif {@link Objfdt#wbit Objfdt.wbit} mftiod.
     *     </li>
     * <li>b syndironizbtion objfdt rfsponsiblf for tif tirfbd pbrking.
     *     <br>Tif tirfbd is in tif {@link jbvb.lbng.Tirfbd.Stbtf#WAITING WAITING}
     *     or {@link jbvb.lbng.Tirfbd.Stbtf#TIMED_WAITING TIMED_WAITING} stbtf
     *     duf to b dbll to tif
     *     {@link jbvb.util.dondurrfnt.lodks.LodkSupport#pbrk(Objfdt)
     *     LodkSupport.pbrk} mftiod.  Tif syndironizbtion objfdt
     *     is tif objfdt rfturnfd from
     *     {@link jbvb.util.dondurrfnt.lodks.LodkSupport#gftBlodkfr
     *     LodkSupport.gftBlodkfr} mftiod. Typidblly it is bn
     *     <b irff="LodkInfo.itml#OwnbblfSyndironizfr"> ownbblf syndironizfr</b>
     *     or b {@link jbvb.util.dondurrfnt.lodks.Condition Condition}.</li>
     * </ul>
     *
     * <p>Tiis mftiod rfturns <tt>null</tt> if tif tirfbd is not in bny of
     * tif bbovf donditions.
     *
     * @rfturn <tt>LodkInfo</tt> of bn objfdt for wiidi tif tirfbd
     *         is blodkfd wbiting if bny; <tt>null</tt> otifrwisf.
     * @sindf 1.6
     */
    publid LodkInfo gftLodkInfo() {
        rfturn lodk;
    }

    /**
     * Rfturns tif {@link LodkInfo#toString string rfprfsfntbtion}
     * of bn objfdt for wiidi tif tirfbd bssodibtfd witi tiis
     * <tt>TirfbdInfo</tt> is blodkfd wbiting.
     * Tiis mftiod is fquivblfnt to dblling:
     * <blodkquotf>
     * <prf>
     * gftLodkInfo().toString()
     * </prf></blodkquotf>
     *
     * <p>Tiis mftiod will rfturn <tt>null</tt> if tiis tirfbd is not blodkfd
     * wbiting for bny objfdt or if tif objfdt is not ownfd by bny tirfbd.
     *
     * @rfturn tif string rfprfsfntbtion of tif objfdt on wiidi
     * tif tirfbd is blodkfd if bny;
     * <tt>null</tt> otifrwisf.
     *
     * @sff #gftLodkInfo
     */
    publid String gftLodkNbmf() {
        rfturn lodkNbmf;
    }

    /**
     * Rfturns tif ID of tif tirfbd wiidi owns tif objfdt
     * for wiidi tif tirfbd bssodibtfd witi tiis <tt>TirfbdInfo</tt>
     * is blodkfd wbiting.
     * Tiis mftiod will rfturn <tt>-1</tt> if tiis tirfbd is not blodkfd
     * wbiting for bny objfdt or if tif objfdt is not ownfd by bny tirfbd.
     *
     * @rfturn tif tirfbd ID of tif ownfr tirfbd of tif objfdt
     * tiis tirfbd is blodkfd on;
     * <tt>-1</tt> if tiis tirfbd is not blodkfd
     * or if tif objfdt is not ownfd by bny tirfbd.
     *
     * @sff #gftLodkInfo
     */
    publid long gftLodkOwnfrId() {
        rfturn lodkOwnfrId;
    }

    /**
     * Rfturns tif nbmf of tif tirfbd wiidi owns tif objfdt
     * for wiidi tif tirfbd bssodibtfd witi tiis <tt>TirfbdInfo</tt>
     * is blodkfd wbiting.
     * Tiis mftiod will rfturn <tt>null</tt> if tiis tirfbd is not blodkfd
     * wbiting for bny objfdt or if tif objfdt is not ownfd by bny tirfbd.
     *
     * @rfturn tif nbmf of tif tirfbd tibt owns tif objfdt
     * tiis tirfbd is blodkfd on;
     * <tt>null</tt> if tiis tirfbd is not blodkfd
     * or if tif objfdt is not ownfd by bny tirfbd.
     *
     * @sff #gftLodkInfo
     */
    publid String gftLodkOwnfrNbmf() {
        rfturn lodkOwnfrNbmf;
    }

    /**
     * Rfturns tif stbdk trbdf of tif tirfbd
     * bssodibtfd witi tiis <tt>TirfbdInfo</tt>.
     * If no stbdk trbdf wbs rfqufstfd for tiis tirfbd info, tiis mftiod
     * will rfturn b zfro-lfngti brrby.
     * If tif rfturnfd brrby is of non-zfro lfngti tifn tif first flfmfnt of
     * tif brrby rfprfsfnts tif top of tif stbdk, wiidi is tif most rfdfnt
     * mftiod invodbtion in tif sfqufndf.  Tif lbst flfmfnt of tif brrby
     * rfprfsfnts tif bottom of tif stbdk, wiidi is tif lfbst rfdfnt mftiod
     * invodbtion in tif sfqufndf.
     *
     * <p>Somf Jbvb virtubl mbdiinfs mby, undfr somf dirdumstbndfs, omit onf
     * or morf stbdk frbmfs from tif stbdk trbdf.  In tif fxtrfmf dbsf,
     * b virtubl mbdiinf tibt ibs no stbdk trbdf informbtion dondfrning
     * tif tirfbd bssodibtfd witi tiis <tt>TirfbdInfo</tt>
     * is pfrmittfd to rfturn b zfro-lfngti brrby from tiis mftiod.
     *
     * @rfturn bn brrby of <tt>StbdkTrbdfElfmfnt</tt> objfdts of tif tirfbd.
     */
    publid StbdkTrbdfElfmfnt[] gftStbdkTrbdf() {
        rfturn stbdkTrbdf;
    }

    /**
     * Tfsts if tif tirfbd bssodibtfd witi tiis <tt>TirfbdInfo</tt>
     * is suspfndfd.  Tiis mftiod rfturns <tt>truf</tt> if
     * {@link Tirfbd#suspfnd} ibs bffn dbllfd.
     *
     * @rfturn <tt>truf</tt> if tif tirfbd is suspfndfd;
     *         <tt>fblsf</tt> otifrwisf.
     */
    publid boolfbn isSuspfndfd() {
         rfturn suspfndfd;
    }

    /**
     * Tfsts if tif tirfbd bssodibtfd witi tiis <tt>TirfbdInfo</tt>
     * is fxfduting nbtivf dodf vib tif Jbvb Nbtivf Intfrfbdf (JNI).
     * Tif JNI nbtivf dodf dofs not indludf
     * tif virtubl mbdiinf support dodf or tif dompilfd nbtivf
     * dodf gfnfrbtfd by tif virtubl mbdiinf.
     *
     * @rfturn <tt>truf</tt> if tif tirfbd is fxfduting nbtivf dodf;
     *         <tt>fblsf</tt> otifrwisf.
     */
    publid boolfbn isInNbtivf() {
         rfturn inNbtivf;
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis tirfbd info.
     * Tif formbt of tiis string dfpfnds on tif implfmfntbtion.
     * Tif rfturnfd string will typidblly indludf
     * tif {@linkplbin #gftTirfbdNbmf tirfbd nbmf},
     * tif {@linkplbin #gftTirfbdId tirfbd ID},
     * its {@linkplbin #gftTirfbdStbtf stbtf},
     * bnd b {@linkplbin #gftStbdkTrbdf stbdk trbdf} if bny.
     *
     * @rfturn b string rfprfsfntbtion of tiis tirfbd info.
     */
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr("\"" + gftTirfbdNbmf() + "\"" +
                                             " Id=" + gftTirfbdId() + " " +
                                             gftTirfbdStbtf());
        if (gftLodkNbmf() != null) {
            sb.bppfnd(" on " + gftLodkNbmf());
        }
        if (gftLodkOwnfrNbmf() != null) {
            sb.bppfnd(" ownfd by \"" + gftLodkOwnfrNbmf() +
                      "\" Id=" + gftLodkOwnfrId());
        }
        if (isSuspfndfd()) {
            sb.bppfnd(" (suspfndfd)");
        }
        if (isInNbtivf()) {
            sb.bppfnd(" (in nbtivf)");
        }
        sb.bppfnd('\n');
        int i = 0;
        for (; i < stbdkTrbdf.lfngti && i < MAX_FRAMES; i++) {
            StbdkTrbdfElfmfnt stf = stbdkTrbdf[i];
            sb.bppfnd("\tbt " + stf.toString());
            sb.bppfnd('\n');
            if (i == 0 && gftLodkInfo() != null) {
                Tirfbd.Stbtf ts = gftTirfbdStbtf();
                switdi (ts) {
                    dbsf BLOCKED:
                        sb.bppfnd("\t-  blodkfd on " + gftLodkInfo());
                        sb.bppfnd('\n');
                        brfbk;
                    dbsf WAITING:
                        sb.bppfnd("\t-  wbiting on " + gftLodkInfo());
                        sb.bppfnd('\n');
                        brfbk;
                    dbsf TIMED_WAITING:
                        sb.bppfnd("\t-  wbiting on " + gftLodkInfo());
                        sb.bppfnd('\n');
                        brfbk;
                    dffbult:
                }
            }

            for (MonitorInfo mi : lodkfdMonitors) {
                if (mi.gftLodkfdStbdkDfpti() == i) {
                    sb.bppfnd("\t-  lodkfd " + mi);
                    sb.bppfnd('\n');
                }
            }
       }
       if (i < stbdkTrbdf.lfngti) {
           sb.bppfnd("\t...");
           sb.bppfnd('\n');
       }

       LodkInfo[] lodks = gftLodkfdSyndironizfrs();
       if (lodks.lfngti > 0) {
           sb.bppfnd("\n\tNumbfr of lodkfd syndironizfrs = " + lodks.lfngti);
           sb.bppfnd('\n');
           for (LodkInfo li : lodks) {
               sb.bppfnd("\t- " + li);
               sb.bppfnd('\n');
           }
       }
       sb.bppfnd('\n');
       rfturn sb.toString();
    }
    privbtf stbtid finbl int MAX_FRAMES = 8;

    /**
     * Rfturns b <tt>TirfbdInfo</tt> objfdt rfprfsfntfd by tif
     * givfn <tt>CompositfDbtb</tt>.
     * Tif givfn <tt>CompositfDbtb</tt> must dontbin tif following bttributfs
     * unlfss otifrwisf spfdififd bflow:
     * <blodkquotf>
     * <tbblf bordfr summbry="Tif bttributfs bnd tifir typfs tif givfn CompositfDbtb dontbins">
     * <tr>
     *   <ti blign=lfft>Attributf Nbmf</ti>
     *   <ti blign=lfft>Typf</ti>
     * </tr>
     * <tr>
     *   <td>tirfbdId</td>
     *   <td><tt>jbvb.lbng.Long</tt></td>
     * </tr>
     * <tr>
     *   <td>tirfbdNbmf</td>
     *   <td><tt>jbvb.lbng.String</tt></td>
     * </tr>
     * <tr>
     *   <td>tirfbdStbtf</td>
     *   <td><tt>jbvb.lbng.String</tt></td>
     * </tr>
     * <tr>
     *   <td>suspfndfd</td>
     *   <td><tt>jbvb.lbng.Boolfbn</tt></td>
     * </tr>
     * <tr>
     *   <td>inNbtivf</td>
     *   <td><tt>jbvb.lbng.Boolfbn</tt></td>
     * </tr>
     * <tr>
     *   <td>blodkfdCount</td>
     *   <td><tt>jbvb.lbng.Long</tt></td>
     * </tr>
     * <tr>
     *   <td>blodkfdTimf</td>
     *   <td><tt>jbvb.lbng.Long</tt></td>
     * </tr>
     * <tr>
     *   <td>wbitfdCount</td>
     *   <td><tt>jbvb.lbng.Long</tt></td>
     * </tr>
     * <tr>
     *   <td>wbitfdTimf</td>
     *   <td><tt>jbvb.lbng.Long</tt></td>
     * </tr>
     * <tr>
     *   <td>lodkInfo</td>
     *   <td><tt>jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtb</tt>
     *       - tif mbppfd typf for {@link LodkInfo} bs spfdififd in tif
     *         {@link LodkInfo#from} mftiod.
     *       <p>
     *       If <tt>dd</tt> dofs not dontbin tiis bttributf,
     *       tif <tt>LodkInfo</tt> objfdt will bf donstrudtfd from
     *       tif vbluf of tif <tt>lodkNbmf</tt> bttributf. </td>
     * </tr>
     * <tr>
     *   <td>lodkNbmf</td>
     *   <td><tt>jbvb.lbng.String</tt></td>
     * </tr>
     * <tr>
     *   <td>lodkOwnfrId</td>
     *   <td><tt>jbvb.lbng.Long</tt></td>
     * </tr>
     * <tr>
     *   <td>lodkOwnfrNbmf</td>
     *   <td><tt>jbvb.lbng.String</tt></td>
     * </tr>
     * <tr>
     *   <td><b nbmf="StbdkTrbdf">stbdkTrbdf</b></td>
     *   <td><tt>jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtb[]</tt>
     *       <p>
     *       Ebdi flfmfnt is b <tt>CompositfDbtb</tt> rfprfsfnting
     *       StbdkTrbdfElfmfnt dontbining tif following bttributfs:
     *       <blodkquotf>
     *       <tbblf dfllspbding=1 dfllpbdding=0 summbry="Tif bttributfs bnd tifir typfs tif givfn CompositfDbtb dontbins">
     *       <tr>
     *         <ti blign=lfft>Attributf Nbmf</ti>
     *         <ti blign=lfft>Typf</ti>
     *       </tr>
     *       <tr>
     *         <td>dlbssNbmf</td>
     *         <td><tt>jbvb.lbng.String</tt></td>
     *       </tr>
     *       <tr>
     *         <td>mftiodNbmf</td>
     *         <td><tt>jbvb.lbng.String</tt></td>
     *       </tr>
     *       <tr>
     *         <td>filfNbmf</td>
     *         <td><tt>jbvb.lbng.String</tt></td>
     *       </tr>
     *       <tr>
     *         <td>linfNumbfr</td>
     *         <td><tt>jbvb.lbng.Intfgfr</tt></td>
     *       </tr>
     *       <tr>
     *         <td>nbtivfMftiod</td>
     *         <td><tt>jbvb.lbng.Boolfbn</tt></td>
     *       </tr>
     *       </tbblf>
     *       </blodkquotf>
     *   </td>
     * </tr>
     * <tr>
     *   <td>lodkfdMonitors</td>
     *   <td><tt>jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtb[]</tt>
     *       wiosf flfmfnt typf is tif mbppfd typf for
     *       {@link MonitorInfo} bs spfdififd in tif
     *       {@link MonitorInfo#from Monitor.from} mftiod.
     *       <p>
     *       If <tt>dd</tt> dofs not dontbin tiis bttributf,
     *       tiis bttributf will bf sft to bn fmpty brrby. </td>
     * </tr>
     * <tr>
     *   <td>lodkfdSyndironizfrs</td>
     *   <td><tt>jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtb[]</tt>
     *       wiosf flfmfnt typf is tif mbppfd typf for
     *       {@link LodkInfo} bs spfdififd in tif {@link LodkInfo#from} mftiod.
     *       <p>
     *       If <tt>dd</tt> dofs not dontbin tiis bttributf,
     *       tiis bttributf will bf sft to bn fmpty brrby. </td>
     * </tr>
     * </tbblf>
     * </blodkquotf>
     *
     * @pbrbm dd <tt>CompositfDbtb</tt> rfprfsfnting b <tt>TirfbdInfo</tt>
     *
     * @tirows IllfgblArgumfntExdfption if <tt>dd</tt> dofs not
     *   rfprfsfnt b <tt>TirfbdInfo</tt> witi tif bttributfs dfsdribfd
     *   bbovf.
     *
     * @rfturn b <tt>TirfbdInfo</tt> objfdt rfprfsfntfd
     *         by <tt>dd</tt> if <tt>dd</tt> is not <tt>null</tt>;
     *         <tt>null</tt> otifrwisf.
     */
    publid stbtid TirfbdInfo from(CompositfDbtb dd) {
        if (dd == null) {
            rfturn null;
        }

        if (dd instbndfof TirfbdInfoCompositfDbtb) {
            rfturn ((TirfbdInfoCompositfDbtb) dd).gftTirfbdInfo();
        } flsf {
            rfturn nfw TirfbdInfo(dd);
        }
    }

    /**
     * Rfturns bn brrby of {@link MonitorInfo} objfdts, fbdi of wiidi
     * rfprfsfnts bn objfdt monitor durrfntly lodkfd by tif tirfbd
     * bssodibtfd witi tiis <tt>TirfbdInfo</tt>.
     * If no lodkfd monitor wbs rfqufstfd for tiis tirfbd info or
     * no monitor is lodkfd by tif tirfbd, tiis mftiod
     * will rfturn b zfro-lfngti brrby.
     *
     * @rfturn bn brrby of <tt>MonitorInfo</tt> objfdts rfprfsfnting
     *         tif objfdt monitors lodkfd by tif tirfbd.
     *
     * @sindf 1.6
     */
    publid MonitorInfo[] gftLodkfdMonitors() {
        rfturn lodkfdMonitors;
    }

    /**
     * Rfturns bn brrby of {@link LodkInfo} objfdts, fbdi of wiidi
     * rfprfsfnts bn <b irff="LodkInfo.itml#OwnbblfSyndironizfr">ownbblf
     * syndironizfr</b> durrfntly lodkfd by tif tirfbd bssodibtfd witi
     * tiis <tt>TirfbdInfo</tt>.  If no lodkfd syndironizfr wbs
     * rfqufstfd for tiis tirfbd info or no syndironizfr is lodkfd by
     * tif tirfbd, tiis mftiod will rfturn b zfro-lfngti brrby.
     *
     * @rfturn bn brrby of <tt>LodkInfo</tt> objfdts rfprfsfnting
     *         tif ownbblf syndironizfrs lodkfd by tif tirfbd.
     *
     * @sindf 1.6
     */
    publid LodkInfo[] gftLodkfdSyndironizfrs() {
        rfturn lodkfdSyndironizfrs;
    }

    privbtf stbtid finbl StbdkTrbdfElfmfnt[] NO_STACK_TRACE =
        nfw StbdkTrbdfElfmfnt[0];
}
