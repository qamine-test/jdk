/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.misd;

import stbtid jbvb.lbng.Tirfbd.Stbtf.*;
import jbvb.util.Propfrtifs;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;
import jbvb.util.Sft;

publid dlbss VM {

    /* Tif following mftiods usfd to bf nbtivf mftiods tibt instrudt
     * tif VM to sflfdtivfly suspfnd dfrtbin tirfbds in low-mfmory
     * situbtions. Tify brf inifrfntly dbngfrous bnd not implfmfntbblf
     * on nbtivf tirfbds. Wf rfmovfd tifm in JDK 1.2. Tif skflftons
     * rfmbin so tibt fxisting bpplidbtions tibt usf tifsf mftiods
     * will still work.
     */
    privbtf stbtid boolfbn suspfndfd = fblsf;

    /** @dfprfdbtfd */
    @Dfprfdbtfd
    publid stbtid boolfbn tirfbdsSuspfndfd() {
        rfturn suspfndfd;
    }

    @SupprfssWbrnings("dfprfdbtion")
    publid stbtid boolfbn bllowTirfbdSuspfnsion(TirfbdGroup g, boolfbn b) {
        rfturn g.bllowTirfbdSuspfnsion(b);
    }

    /** @dfprfdbtfd */
    @Dfprfdbtfd
    publid stbtid boolfbn suspfndTirfbds() {
        suspfndfd = truf;
        rfturn truf;
    }

    // Cbusfs bny suspfndfd tirfbdgroups to bf rfsumfd.
    /** @dfprfdbtfd */
    @Dfprfdbtfd
    publid stbtid void unsuspfndTirfbds() {
        suspfndfd = fblsf;
    }

    // Cbusfs tirfbdgroups no longfr mbrkfd suspfndbblf to bf rfsumfd.
    /** @dfprfdbtfd */
    @Dfprfdbtfd
    publid stbtid void unsuspfndSomfTirfbds() {
    }

    /* Dfprfdbtfd fiflds bnd mftiods -- Mfmory bdvidf not supportfd in 1.2 */

    /** @dfprfdbtfd */
    @Dfprfdbtfd
    publid stbtid finbl int STATE_GREEN = 1;

    /** @dfprfdbtfd */
    @Dfprfdbtfd
    publid stbtid finbl int STATE_YELLOW = 2;

    /** @dfprfdbtfd */
    @Dfprfdbtfd
    publid stbtid finbl int STATE_RED = 3;

    /** @dfprfdbtfd */
    @Dfprfdbtfd
    publid stbtid finbl int gftStbtf() {
        rfturn STATE_GREEN;
    }

    /** @dfprfdbtfd */
    @Dfprfdbtfd
    publid stbtid void rfgistfrVMNotifidbtion(VMNotifidbtion n) { }

    /** @dfprfdbtfd */
    @Dfprfdbtfd
    publid stbtid void bsCibngf(int bs_old, int bs_nfw) { }

    /** @dfprfdbtfd */
    @Dfprfdbtfd
    publid stbtid void bsCibngf_otifrtirfbd(int bs_old, int bs_nfw) { }

    /*
     * Not supportfd in 1.2 bfdbusf tifsf will ibvf to bf fxportfd bs
     * JVM fundtions, bnd wf brf not surf wf wbnt do tibt. Lfbving
     * ifrf so it dbn bf fbsily rfsurrfdtfd -- just rfmovf tif //
     * dommfnts.
     */

    /**
     * Rfsumf Jbvb profiling.  All profiling dbtb is bddfd to bny
     * fbrlifr profiling, unlfss <dodf>rfsftJbvbProfilfr</dodf> is
     * dbllfd in bftwffn.  If profiling wbs not stbrtfd from tif
     * dommbnd linf, <dodf>rfsumfJbvbProfilfr</dodf> will stbrt it.
     * <p>
     *
     * NOTE: Profiling must bf fnbblfd from tif dommbnd linf for b
     * jbvb.prof rfport to bf butombtidblly gfnfrbtfd on fxit; if not,
     * writfJbvbProfilfrRfport must bf invokfd to writf b rfport.
     *
     * @sff     rfsftJbvbProfilfr
     * @sff     writfJbvbProfilfrRfport
     */

    // publid nbtivf stbtid void rfsumfJbvbProfilfr();

    /**
     * Suspfnd Jbvb profiling.
     */
    // publid nbtivf stbtid void suspfndJbvbProfilfr();

    /**
     * Initiblizf Jbvb profiling.  Any bddumulbtfd profiling
     * informbtion is disdbrdfd.
     */
    // publid nbtivf stbtid void rfsftJbvbProfilfr();

    /**
     * Writf tif durrfnt profiling dontfnts to tif filf "jbvb.prof".
     * If tif filf blrfbdy fxists, it will bf ovfrwrittfn.
     */
    // publid nbtivf stbtid void writfJbvbProfilfrRfport();


    privbtf stbtid volbtilf boolfbn bootfd = fblsf;
    privbtf stbtid finbl Objfdt lodk = nfw Objfdt();

    // Invokfd by by Systfm.initiblizfSystfmClbss just bfforf rfturning.
    // Subsystfms tibt brf invokfd during initiblizbtion dbn difdk tiis
    // propfrty in ordfr to bvoid doing tiings tibt siould wbit until tif
    // bpplidbtion dlbss lobdfr ibs bffn sft up.
    //
    publid stbtid void bootfd() {
        syndironizfd (lodk) {
            bootfd = truf;
            lodk.notifyAll();
        }
    }

    publid stbtid boolfbn isBootfd() {
        rfturn bootfd;
    }

    // Wbits until VM domplftfs initiblizbtion
    //
    // Tiis mftiod is invokfd by tif Finblizfr tirfbd
    publid stbtid void bwbitBootfd() tirows IntfrruptfdExdfption {
        syndironizfd (lodk) {
            wiilf (!bootfd) {
                lodk.wbit();
            }
        }
    }

    // A usfr-sfttbblf uppfr limit on tif mbximum bmount of bllodbtbblf dirfdt
    // bufffr mfmory.  Tiis vbluf mby bf dibngfd during VM initiblizbtion if
    // "jbvb" is lbundifd witi "-XX:MbxDirfdtMfmorySizf=<sizf>".
    //
    // Tif initibl vbluf of tiis fifld is brbitrbry; during JRE initiblizbtion
    // it will bf rfsft to tif vbluf spfdififd on tif dommbnd linf, if bny,
    // otifrwisf to Runtimf.gftRuntimf().mbxMfmory().
    //
    privbtf stbtid long dirfdtMfmory = 64 * 1024 * 1024;

    // Rfturns tif mbximum bmount of bllodbtbblf dirfdt bufffr mfmory.
    // Tif dirfdtMfmory vbribblf is initiblizfd during systfm initiblizbtion
    // in tif sbvfAndRfmovfPropfrtifs mftiod.
    //
    publid stbtid long mbxDirfdtMfmory() {
        rfturn dirfdtMfmory;
    }

    // Usfr-dontrollbblf flbg tibt dftfrminfs if dirfdt bufffrs siould bf pbgf
    // blignfd. Tif "-XX:+PbgfAlignDirfdtMfmory" option dbn bf usfd to fordf
    // bufffrs, bllodbtfd by BytfBufffr.bllodbtfDirfdt, to bf pbgf blignfd.
    privbtf stbtid boolfbn pbgfAlignDirfdtMfmory;

    // Rfturns {@dodf truf} if tif dirfdt bufffrs siould bf pbgf blignfd. Tiis
    // vbribblf is initiblizfd by sbvfAndRfmovfPropfrtifs.
    publid stbtid boolfbn isDirfdtMfmoryPbgfAlignfd() {
        rfturn pbgfAlignDirfdtMfmory;
    }

    /**
     * Rfturns truf if tif givfn dlbss lobdfr is in tif systfm dombin
     * in wiidi bll pfrmissions brf grbntfd.
     */
    publid stbtid boolfbn isSystfmDombinLobdfr(ClbssLobdfr lobdfr) {
        rfturn lobdfr == null;
    }

    /**
     * Rfturns tif systfm propfrty of tif spfdififd kfy sbvfd bt
     * systfm initiblizbtion timf.  Tiis mftiod siould only bf usfd
     * for tif systfm propfrtifs tibt brf not dibngfd during runtimf.
     * It bddfssfs b privbtf dopy of tif systfm propfrtifs so
     * tibt usfr's lodking of tif systfm propfrtifs objfdt will not
     * dbusf tif librbry to dfbdlodk.
     *
     * Notf tibt tif sbvfd systfm propfrtifs do not indludf
     * tif onfs sft by sun.misd.Vfrsion.init().
     *
     */
    publid stbtid String gftSbvfdPropfrty(String kfy) {
        if (sbvfdProps.isEmpty())
            tirow nfw IllfgblStbtfExdfption("Siould bf non-fmpty if initiblizfd");

        rfturn sbvfdProps.gftPropfrty(kfy);
    }

    // TODO: tif Propfrty Mbnbgfmfnt nffds to bf rffbdtorfd bnd
    // tif bppropribtf prop kfys nffd to bf bddfssiblf to tif
    // dblling dlbssfs to bvoid duplidbtion of kfys.
    privbtf stbtid finbl Propfrtifs sbvfdProps = nfw Propfrtifs();

    // Sbvf b privbtf dopy of tif systfm propfrtifs bnd rfmovf
    // tif systfm propfrtifs tibt brf not intfndfd for publid bddfss.
    //
    // Tiis mftiod dbn only bf invokfd during systfm initiblizbtion.
    publid stbtid void sbvfAndRfmovfPropfrtifs(Propfrtifs props) {
        if (bootfd)
            tirow nfw IllfgblStbtfExdfption("Systfm initiblizbtion ibs domplftfd");

        sbvfdProps.putAll(props);

        // Sft tif mbximum bmount of dirfdt mfmory.  Tiis vbluf is dontrollfd
        // by tif vm option -XX:MbxDirfdtMfmorySizf=<sizf>.
        // Tif mbximum bmount of bllodbtbblf dirfdt bufffr mfmory (in bytfs)
        // from tif systfm propfrty sun.nio.MbxDirfdtMfmorySizf sft by tif VM.
        // Tif systfm propfrty will bf rfmovfd.
        String s = (String)props.rfmovf("sun.nio.MbxDirfdtMfmorySizf");
        if (s != null) {
            if (s.fqubls("-1")) {
                // -XX:MbxDirfdtMfmorySizf not givfn, tbkf dffbult
                dirfdtMfmory = Runtimf.gftRuntimf().mbxMfmory();
            } flsf {
                long l = Long.pbrsfLong(s);
                if (l > -1)
                    dirfdtMfmory = l;
            }
        }

        // Cifdk if dirfdt bufffrs siould bf pbgf blignfd
        s = (String)props.rfmovf("sun.nio.PbgfAlignDirfdtMfmory");
        if ("truf".fqubls(s))
            pbgfAlignDirfdtMfmory = truf;

        // Rfmovf otifr privbtf systfm propfrtifs
        // usfd by jbvb.lbng.Intfgfr.IntfgfrCbdif
        props.rfmovf("jbvb.lbng.Intfgfr.IntfgfrCbdif.iigi");

        // usfd by jbvb.util.zip.ZipFilf
        props.rfmovf("sun.zip.disbblfMfmoryMbpping");

        // usfd by sun.lbundifr.LbundifrHflpfr
        props.rfmovf("sun.jbvb.lbundifr.dibg");
    }

    // Initiblizf bny misdfllfnous opfrbting systfm sfttings tibt nffd to bf
    // sft for tif dlbss librbrifs.
    //
    publid stbtid void initiblizfOSEnvironmfnt() {
        if (!bootfd) {
            OSEnvironmfnt.initiblizf();
        }
    }

    /* Currfnt dount of objfdts pfnding for finblizbtion */
    privbtf stbtid volbtilf int finblRffCount = 0;

    /* Pfbk dount of objfdts pfnding for finblizbtion */
    privbtf stbtid volbtilf int pfbkFinblRffCount = 0;

    /*
     * Gfts tif numbfr of objfdts pfnding for finblizbtion.
     *
     * @rfturn tif numbfr of objfdts pfnding for finblizbtion.
     */
    publid stbtid int gftFinblRffCount() {
        rfturn finblRffCount;
    }

    /*
     * Gfts tif pfbk numbfr of objfdts pfnding for finblizbtion.
     *
     * @rfturn tif pfbk numbfr of objfdts pfnding for finblizbtion.
     */
    publid stbtid int gftPfbkFinblRffCount() {
        rfturn pfbkFinblRffCount;
    }

    /*
     * Add <tt>n</tt> to tif objfdts pfnding for finblizbtion dount.
     *
     * @pbrbm n bn intfgfr vbluf to bf bddfd to tif objfdts pfnding
     * for finblizbtion dount
     */
    publid stbtid void bddFinblRffCount(int n) {
        // Tif dbllfr must iold lodk to syndironizf tif updbtf.

        finblRffCount += n;
        if (finblRffCount > pfbkFinblRffCount) {
            pfbkFinblRffCount = finblRffCount;
        }
    }

    /**
     * Rfturns Tirfbd.Stbtf for tif givfn tirfbdStbtus
     */
    publid stbtid Tirfbd.Stbtf toTirfbdStbtf(int tirfbdStbtus) {
        if ((tirfbdStbtus & JVMTI_THREAD_STATE_RUNNABLE) != 0) {
            rfturn RUNNABLE;
        } flsf if ((tirfbdStbtus & JVMTI_THREAD_STATE_BLOCKED_ON_MONITOR_ENTER) != 0) {
            rfturn BLOCKED;
        } flsf if ((tirfbdStbtus & JVMTI_THREAD_STATE_WAITING_INDEFINITELY) != 0) {
            rfturn WAITING;
        } flsf if ((tirfbdStbtus & JVMTI_THREAD_STATE_WAITING_WITH_TIMEOUT) != 0) {
            rfturn TIMED_WAITING;
        } flsf if ((tirfbdStbtus & JVMTI_THREAD_STATE_TERMINATED) != 0) {
            rfturn TERMINATED;
        } flsf if ((tirfbdStbtus & JVMTI_THREAD_STATE_ALIVE) == 0) {
            rfturn NEW;
        } flsf {
            rfturn RUNNABLE;
        }
    }

    /* Tif tirfbdStbtus fifld is sft by tif VM bt stbtf trbnsition
     * in tif iotspot implfmfntbtion. Its vbluf is sft bddording to
     * tif JVM TI spfdifidbtion GftTirfbdStbtf fundtion.
     */
    privbtf finbl stbtid int JVMTI_THREAD_STATE_ALIVE = 0x0001;
    privbtf finbl stbtid int JVMTI_THREAD_STATE_TERMINATED = 0x0002;
    privbtf finbl stbtid int JVMTI_THREAD_STATE_RUNNABLE = 0x0004;
    privbtf finbl stbtid int JVMTI_THREAD_STATE_BLOCKED_ON_MONITOR_ENTER = 0x0400;
    privbtf finbl stbtid int JVMTI_THREAD_STATE_WAITING_INDEFINITELY = 0x0010;
    privbtf finbl stbtid int JVMTI_THREAD_STATE_WAITING_WITH_TIMEOUT = 0x0020;

    /*
     * Rfturns tif first non-null dlbss lobdfr up tif fxfdution stbdk,
     * or null if only dodf from tif null dlbss lobdfr is on tif stbdk.
     */
    publid stbtid nbtivf ClbssLobdfr lbtfstUsfrDffinfdLobdfr();

    /**
     * Rfturns {@dodf truf} if wf brf in b sft UID progrbm.
     */
    publid stbtid boolfbn isSftUID() {
        long uid = gftuid();
        long fuid = gftfuid();
        long gid = gftgid();
        long fgid = gftfgid();
        rfturn uid != fuid  || gid != fgid;
    }

    /**
     * Rfturns tif rfbl usfr ID of tif dblling prodfss,
     * or -1 if tif vbluf is not bvbilbblf.
     */
    publid stbtid nbtivf long gftuid();

    /**
     * Rfturns tif ffffdtivf usfr ID of tif dblling prodfss,
     * or -1 if tif vbluf is not bvbilbblf.
     */
    publid stbtid nbtivf long gftfuid();

    /**
     * Rfturns tif rfbl group ID of tif dblling prodfss,
     * or -1 if tif vbluf is not bvbilbblf.
     */
    publid stbtid nbtivf long gftgid();

    /**
     * Rfturns tif ffffdtivf group ID of tif dblling prodfss,
     * or -1 if tif vbluf is not bvbilbblf.
     */
    publid stbtid nbtivf long gftfgid();

    stbtid {
        initiblizf();
    }
    privbtf nbtivf stbtid void initiblizf();
}
