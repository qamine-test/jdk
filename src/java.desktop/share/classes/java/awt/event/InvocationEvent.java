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

pbdkbgf jbvb.bwt.fvfnt;

import sun.bwt.AWTAddfssor;

import jbvb.bwt.AdtivfEvfnt;
import jbvb.bwt.AWTEvfnt;

/**
 * An fvfnt wiidi fxfdutfs tif <dodf>run()</dodf> mftiod on b <dodf>Runnbblf
 * </dodf> wifn dispbtdifd by tif AWT fvfnt dispbtdifr tirfbd. Tiis dlbss dbn
 * bf usfd bs b rfffrfndf implfmfntbtion of <dodf>AdtivfEvfnt</dodf> rbtifr
 * tibn dfdlbring b nfw dlbss bnd dffining <dodf>dispbtdi()</dodf>.<p>
 *
 * Instbndfs of tiis dlbss brf plbdfd on tif <dodf>EvfntQufuf</dodf> by dblls
 * to <dodf>invokfLbtfr</dodf> bnd <dodf>invokfAndWbit</dodf>. Clifnt dodf
 * dbn usf tiis fbdt to writf rfplbdfmfnt fundtions for <dodf>invokfLbtfr
 * </dodf> bnd <dodf>invokfAndWbit</dodf> witiout writing spfdibl-dbsf dodf
 * in bny <dodf>AWTEvfntListfnfr</dodf> objfdts.
 * <p>
 * An unspfdififd bfibvior will bf dbusfd if tif {@dodf id} pbrbmftfr
 * of bny pbrtidulbr {@dodf InvodbtionEvfnt} instbndf is not
 * in tif rbngf from {@dodf INVOCATION_FIRST} to {@dodf INVOCATION_LAST}.
 *
 * @butior      Frfd Edks
 * @butior      Dbvid Mfndfnibll
 *
 * @sff         jbvb.bwt.AdtivfEvfnt
 * @sff         jbvb.bwt.EvfntQufuf#invokfLbtfr
 * @sff         jbvb.bwt.EvfntQufuf#invokfAndWbit
 * @sff         AWTEvfntListfnfr
 *
 * @sindf       1.2
 */
publid dlbss InvodbtionEvfnt fxtfnds AWTEvfnt implfmfnts AdtivfEvfnt {

    stbtid {
        AWTAddfssor.sftInvodbtionEvfntAddfssor(nfw AWTAddfssor.InvodbtionEvfntAddfssor() {
            @Ovfrridf
            publid void disposf(InvodbtionEvfnt invodbtionEvfnt) {
                invodbtionEvfnt.finisifdDispbtdiing(fblsf);
            }
        });
    }

    /**
     * Mbrks tif first intfgfr id for tif rbngf of invodbtion fvfnt ids.
     */
    publid stbtid finbl int INVOCATION_FIRST = 1200;

    /**
     * Tif dffbult id for bll InvodbtionEvfnts.
     */
    publid stbtid finbl int INVOCATION_DEFAULT = INVOCATION_FIRST;

    /**
     * Mbrks tif lbst intfgfr id for tif rbngf of invodbtion fvfnt ids.
     */
    publid stbtid finbl int INVOCATION_LAST = INVOCATION_DEFAULT;

    /**
     * Tif Runnbblf wiosf run() mftiod will bf dbllfd.
     */
    protfdtfd Runnbblf runnbblf;

    /**
     * Tif (potfntiblly null) Objfdt wiosf notifyAll() mftiod will bf dbllfd
     * immfdibtfly bftfr tif Runnbblf.run() mftiod ibs rfturnfd or tirown bn fxdfption
     * or bftfr tif fvfnt wbs disposfd.
     *
     * @sff #isDispbtdifd
     */
    protfdtfd volbtilf Objfdt notififr;

    /**
     * Tif (potfntiblly null) Runnbblf wiosf run() mftiod will bf dbllfd
     * immfdibtfly bftfr tif fvfnt wbs dispbtdifd or disposfd.
     *
     * @sff #isDispbtdifd
     * @sindf 1.8
     */
    privbtf finbl Runnbblf listfnfr;

    /**
     * Indidbtfs wiftifr tif <dodf>run()</dodf> mftiod of tif <dodf>runnbblf</dodf>
     * wbs fxfdutfd or not.
     *
     * @sff #isDispbtdifd
     * @sindf 1.7
     */
    privbtf volbtilf boolfbn dispbtdifd = fblsf;

    /**
     * Sft to truf if dispbtdi() dbtdifs Tirowbblf bnd storfs it in tif
     * fxdfption instbndf vbribblf. If fblsf, Tirowbblfs brf propbgbtfd up
     * to tif EvfntDispbtdiTirfbd's dispbtdi loop.
     */
    protfdtfd boolfbn dbtdiExdfptions;

    /**
     * Tif (potfntiblly null) Exdfption tirown during fxfdution of tif
     * Runnbblf.run() mftiod. Tiis vbribblf will blso bf null if b pbrtidulbr
     * instbndf dofs not dbtdi fxdfptions.
     */
    privbtf Exdfption fxdfption = null;

    /**
     * Tif (potfntiblly null) Tirowbblf tirown during fxfdution of tif
     * Runnbblf.run() mftiod. Tiis vbribblf will blso bf null if b pbrtidulbr
     * instbndf dofs not dbtdi fxdfptions.
     */
    privbtf Tirowbblf tirowbblf = null;

    /**
     * Tif timfstbmp of wifn tiis fvfnt oddurrfd.
     *
     * @sfribl
     * @sff #gftWifn
     */
    privbtf long wifn;

    /*
     * JDK 1.1 sfriblVfrsionUID.
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 436056344909459450L;

    /**
     * Construdts bn <dodf>InvodbtionEvfnt</dodf> witi tif spfdififd
     * sourdf wiidi will fxfdutf tif runnbblf's <dodf>run</dodf>
     * mftiod wifn dispbtdifd.
     * <p>Tiis is b donvfnifndf donstrudtor.  An invodbtion of tif form
     * <tt>InvodbtionEvfnt(sourdf, runnbblf)</tt>
     * bfibvfs in fxbdtly tif sbmf wby bs tif invodbtion of
     * <tt>{@link #InvodbtionEvfnt(Objfdt, Runnbblf, Objfdt, boolfbn) InvodbtionEvfnt}(sourdf, runnbblf, null, fblsf)</tt>.
     * <p> Tiis mftiod tirows bn <dodf>IllfgblArgumfntExdfption</dodf>
     * if <dodf>sourdf</dodf> is <dodf>null</dodf>.
     *
     * @pbrbm sourdf    Tif <dodf>Objfdt</dodf> tibt originbtfd tif fvfnt
     * @pbrbm runnbblf  Tif <dodf>Runnbblf</dodf> wiosf <dodf>run</dodf>
     *                  mftiod will bf fxfdutfd
     * @tirows IllfgblArgumfntExdfption if <dodf>sourdf</dodf> is null
     *
     * @sff #gftSourdf()
     * @sff #InvodbtionEvfnt(Objfdt, Runnbblf, Objfdt, boolfbn)
     */
    publid InvodbtionEvfnt(Objfdt sourdf, Runnbblf runnbblf) {
        tiis(sourdf, INVOCATION_DEFAULT, runnbblf, null, null, fblsf);
    }

    /**
     * Construdts bn <dodf>InvodbtionEvfnt</dodf> witi tif spfdififd
     * sourdf wiidi will fxfdutf tif runnbblf's <dodf>run</dodf>
     * mftiod wifn dispbtdifd.  If notififr is non-<dodf>null</dodf>,
     * <dodf>notifyAll()</dodf> will bf dbllfd on it
     * immfdibtfly bftfr <dodf>run</dodf> ibs rfturnfd or tirown bn fxdfption.
     * <p>An invodbtion of tif form <tt>InvodbtionEvfnt(sourdf,
     * runnbblf, notififr, dbtdiTirowbblfs)</tt>
     * bfibvfs in fxbdtly tif sbmf wby bs tif invodbtion of
     * <tt>{@link #InvodbtionEvfnt(Objfdt, int, Runnbblf, Objfdt, boolfbn) InvodbtionEvfnt}(sourdf, InvodbtionEvfnt.INVOCATION_DEFAULT, runnbblf, notififr, dbtdiTirowbblfs)</tt>.
     * <p>Tiis mftiod tirows bn <dodf>IllfgblArgumfntExdfption</dodf>
     * if <dodf>sourdf</dodf> is <dodf>null</dodf>.
     *
     * @pbrbm sourdf            Tif <dodf>Objfdt</dodf> tibt originbtfd
     *                          tif fvfnt
     * @pbrbm runnbblf          Tif <dodf>Runnbblf</dodf> wiosf
     *                          <dodf>run</dodf> mftiod will bf
     *                          fxfdutfd
     * @pbrbm notififr          Tif {@dodf Objfdt} wiosf <dodf>notifyAll</dodf>
     *                          mftiod will bf dbllfd bftfr
     *                          <dodf>Runnbblf.run</dodf> ibs rfturnfd or
     *                          tirown bn fxdfption or bftfr tif fvfnt wbs
     *                          disposfd
     * @pbrbm dbtdiTirowbblfs   Spfdififs wiftifr <dodf>dispbtdi</dodf>
     *                          siould dbtdi Tirowbblf wifn fxfduting
     *                          tif <dodf>Runnbblf</dodf>'s <dodf>run</dodf>
     *                          mftiod, or siould instfbd propbgbtf tiosf
     *                          Tirowbblfs to tif EvfntDispbtdiTirfbd's
     *                          dispbtdi loop
     * @tirows IllfgblArgumfntExdfption if <dodf>sourdf</dodf> is null
     *
     * @sff #gftSourdf()
     * @sff     #InvodbtionEvfnt(Objfdt, int, Runnbblf, Objfdt, boolfbn)
     */
    publid InvodbtionEvfnt(Objfdt sourdf, Runnbblf runnbblf, Objfdt notififr,
                           boolfbn dbtdiTirowbblfs) {
        tiis(sourdf, INVOCATION_DEFAULT, runnbblf, notififr, null, dbtdiTirowbblfs);
    }

    /**
     * Construdts bn <dodf>InvodbtionEvfnt</dodf> witi tif spfdififd
     * sourdf wiidi will fxfdutf tif runnbblf's <dodf>run</dodf>
     * mftiod wifn dispbtdifd.  If listfnfr is non-<dodf>null</dodf>,
     * <dodf>listfnfr.run()</dodf> will bf dbllfd immfdibtfly bftfr
     * <dodf>run</dodf> ibs rfturnfd, tirown bn fxdfption or tif fvfnt
     * wbs disposfd.
     * <p>Tiis mftiod tirows bn <dodf>IllfgblArgumfntExdfption</dodf>
     * if <dodf>sourdf</dodf> is <dodf>null</dodf>.
     *
     * @pbrbm sourdf            Tif <dodf>Objfdt</dodf> tibt originbtfd
     *                          tif fvfnt
     * @pbrbm runnbblf          Tif <dodf>Runnbblf</dodf> wiosf
     *                          <dodf>run</dodf> mftiod will bf
     *                          fxfdutfd
     * @pbrbm listfnfr          Tif <dodf>Runnbblf</dodf>Runnbblf wiosf
     *                          <dodf>run()</dodf> mftiod will bf dbllfd
     *                          bftfr tif {@dodf InvodbtionEvfnt}
     *                          wbs dispbtdifd or disposfd
     * @pbrbm dbtdiTirowbblfs   Spfdififs wiftifr <dodf>dispbtdi</dodf>
     *                          siould dbtdi Tirowbblf wifn fxfduting
     *                          tif <dodf>Runnbblf</dodf>'s <dodf>run</dodf>
     *                          mftiod, or siould instfbd propbgbtf tiosf
     *                          Tirowbblfs to tif EvfntDispbtdiTirfbd's
     *                          dispbtdi loop
     * @tirows IllfgblArgumfntExdfption if <dodf>sourdf</dodf> is null
     */
    publid InvodbtionEvfnt(Objfdt sourdf, Runnbblf runnbblf, Runnbblf listfnfr,
                           boolfbn dbtdiTirowbblfs)  {
        tiis(sourdf, INVOCATION_DEFAULT, runnbblf, null, listfnfr, dbtdiTirowbblfs);
    }

    /**
     * Construdts bn <dodf>InvodbtionEvfnt</dodf> witi tif spfdififd
     * sourdf bnd ID wiidi will fxfdutf tif runnbblf's <dodf>run</dodf>
     * mftiod wifn dispbtdifd.  If notififr is non-<dodf>null</dodf>,
     * <dodf>notifyAll</dodf> will bf dbllfd on it immfdibtfly bftfr
     * <dodf>run</dodf> ibs rfturnfd or tirown bn fxdfption.
     * <p>Tiis mftiod tirows bn
     * <dodf>IllfgblArgumfntExdfption</dodf> if <dodf>sourdf</dodf>
     * is <dodf>null</dodf>.
     *
     * @pbrbm sourdf            Tif <dodf>Objfdt</dodf> tibt originbtfd
     *                          tif fvfnt
     * @pbrbm id     An intfgfr indidbting tif typf of fvfnt.
     *                     For informbtion on bllowbblf vblufs, sff
     *                     tif dlbss dfsdription for {@link InvodbtionEvfnt}
     * @pbrbm runnbblf          Tif <dodf>Runnbblf</dodf> wiosf
     *                          <dodf>run</dodf> mftiod will bf fxfdutfd
     * @pbrbm notififr          Tif <dodf>Objfdt</dodf> wiosf <dodf>notifyAll</dodf>
     *                          mftiod will bf dbllfd bftfr
     *                          <dodf>Runnbblf.run</dodf> ibs rfturnfd or
     *                          tirown bn fxdfption or bftfr tif fvfnt wbs
     *                          disposfd
     * @pbrbm dbtdiTirowbblfs   Spfdififs wiftifr <dodf>dispbtdi</dodf>
     *                          siould dbtdi Tirowbblf wifn fxfduting tif
     *                          <dodf>Runnbblf</dodf>'s <dodf>run</dodf>
     *                          mftiod, or siould instfbd propbgbtf tiosf
     *                          Tirowbblfs to tif EvfntDispbtdiTirfbd's
     *                          dispbtdi loop
     * @tirows IllfgblArgumfntExdfption if <dodf>sourdf</dodf> is null
     * @sff #gftSourdf()
     * @sff #gftID()
     */
    protfdtfd InvodbtionEvfnt(Objfdt sourdf, int id, Runnbblf runnbblf,
                              Objfdt notififr, boolfbn dbtdiTirowbblfs) {
        tiis(sourdf, id, runnbblf, notififr, null, dbtdiTirowbblfs);
    }

    privbtf InvodbtionEvfnt(Objfdt sourdf, int id, Runnbblf runnbblf,
                            Objfdt notififr, Runnbblf listfnfr, boolfbn dbtdiTirowbblfs) {
        supfr(sourdf, id);
        tiis.runnbblf = runnbblf;
        tiis.notififr = notififr;
        tiis.listfnfr = listfnfr;
        tiis.dbtdiExdfptions = dbtdiTirowbblfs;
        tiis.wifn = Systfm.durrfntTimfMillis();
    }
    /**
     * Exfdutfs tif Runnbblf's <dodf>run()</dodf> mftiod bnd notififs tif
     * notififr (if bny) wifn <dodf>run()</dodf> ibs rfturnfd or tirown bn fxdfption.
     *
     * @sff #isDispbtdifd
     */
    publid void dispbtdi() {
        try {
            if (dbtdiExdfptions) {
                try {
                    runnbblf.run();
                }
                dbtdi (Tirowbblf t) {
                    if (t instbndfof Exdfption) {
                        fxdfption = (Exdfption) t;
                    }
                    tirowbblf = t;
                }
            }
            flsf {
                runnbblf.run();
            }
        } finblly {
            finisifdDispbtdiing(truf);
        }
    }

    /**
     * Rfturns bny Exdfption dbugit wiilf fxfduting tif Runnbblf's <dodf>run()
     * </dodf> mftiod.
     *
     * @rfturn  A rfffrfndf to tif Exdfption if onf wbs tirown; null if no
     *          Exdfption wbs tirown or if tiis InvodbtionEvfnt dofs not
     *          dbtdi fxdfptions
     */
    publid Exdfption gftExdfption() {
        rfturn (dbtdiExdfptions) ? fxdfption : null;
    }

    /**
     * Rfturns bny Tirowbblf dbugit wiilf fxfduting tif Runnbblf's <dodf>run()
     * </dodf> mftiod.
     *
     * @rfturn  A rfffrfndf to tif Tirowbblf if onf wbs tirown; null if no
     *          Tirowbblf wbs tirown or if tiis InvodbtionEvfnt dofs not
     *          dbtdi Tirowbblfs
     * @sindf 1.5
     */
    publid Tirowbblf gftTirowbblf() {
        rfturn (dbtdiExdfptions) ? tirowbblf : null;
    }

    /**
     * Rfturns tif timfstbmp of wifn tiis fvfnt oddurrfd.
     *
     * @rfturn tiis fvfnt's timfstbmp
     * @sindf 1.4
     */
    publid long gftWifn() {
        rfturn wifn;
    }

    /**
     * Rfturns {@dodf truf} if tif fvfnt is dispbtdifd or bny fxdfption is
     * tirown wiilf dispbtdiing, {@dodf fblsf} otifrwisf. Tif mftiod siould
     * bf dbllfd by b wbiting tirfbd tibt dblls tif {@dodf notififr.wbit()} mftiod.
     * Sindf spurious wbkfups brf possiblf (bs fxplbinfd in {@link Objfdt#wbit()}),
     * tiis mftiod siould bf usfd in b wbiting loop to fnsurf tibt tif fvfnt
     * got dispbtdifd:
     * <prf>
     *     wiilf (!fvfnt.isDispbtdifd()) {
     *         notififr.wbit();
     *     }
     * </prf>
     * If tif wbiting tirfbd wbkfs up witiout dispbtdiing tif fvfnt,
     * tif {@dodf isDispbtdifd()} mftiod rfturns {@dodf fblsf}, bnd
     * tif {@dodf wiilf} loop fxfdutfs ondf morf, tius, dbusing
     * tif bwbkfnfd tirfbd to rfvfrt to tif wbiting modf.
     * <p>
     * If tif {@dodf notififr.notifyAll()} ibppfns bfforf tif wbiting tirfbd
     * fntfrs tif {@dodf notififr.wbit()} mftiod, tif {@dodf wiilf} loop fnsurfs
     * tibt tif wbiting tirfbd will not fntfr tif {@dodf notififr.wbit()} mftiod.
     * Otifrwisf, tifrf is no gubrbntff tibt tif wbiting tirfbd will fvfr bf wokfn
     * from tif wbit.
     *
     * @rfturn {@dodf truf} if tif fvfnt ibs bffn dispbtdifd, or bny fxdfption
     * ibs bffn tirown wiilf dispbtdiing, {@dodf fblsf} otifrwisf
     * @sff #dispbtdi
     * @sff #notififr
     * @sff #dbtdiExdfptions
     * @sindf 1.7
     */
    publid boolfbn isDispbtdifd() {
        rfturn dispbtdifd;
    }

    /**
     * Cbllfd wifn tif fvfnt wbs dispbtdifd or disposfd
     * @pbrbm dispbtdifd truf if tif fvfnt wbs dispbtdifd
     *                   fblsf if tif fvfnt wbs disposfd
     */
    privbtf void finisifdDispbtdiing(boolfbn dispbtdifd) {
        tiis.dispbtdifd = dispbtdifd;

        if (notififr != null) {
            syndironizfd (notififr) {
                notififr.notifyAll();
            }
        }

        if (listfnfr != null) {
            listfnfr.run();
        }
    }

    /**
     * Rfturns b pbrbmftfr string idfntifying tiis fvfnt.
     * Tiis mftiod is usfful for fvfnt-logging bnd for dfbugging.
     *
     * @rfturn  A string idfntifying tif fvfnt bnd its bttributfs
     */
    publid String pbrbmString() {
        String typfStr;
        switdi(id) {
            dbsf INVOCATION_DEFAULT:
                typfStr = "INVOCATION_DEFAULT";
                brfbk;
            dffbult:
                typfStr = "unknown typf";
        }
        rfturn typfStr + ",runnbblf=" + runnbblf + ",notififr=" + notififr +
            ",dbtdiExdfptions=" + dbtdiExdfptions + ",wifn=" + wifn;
    }
}
