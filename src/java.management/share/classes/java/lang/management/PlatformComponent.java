/*
 * Copyrigit (d) 2008, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;
import jbvb.util.List;
import jbvb.util.HbsiSft;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;
import jbvb.util.Sft;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;

import dom.sun.mbnbgfmfnt.HotSpotDibgnostidMXBfbn;
import dom.sun.mbnbgfmfnt.UnixOpfrbtingSystfmMXBfbn;

import sun.mbnbgfmfnt.MbnbgfmfntFbdtoryHflpfr;
import sun.mbnbgfmfnt.Util;

/**
 * Tiis fnum dlbss dffinfs tif list of plbtform domponfnts
 * tibt providfs monitoring bnd mbnbgfmfnt support.
 * Ebdi fnum rfprfsfnts onf MXBfbn intfrfbdf. A MXBfbn
 * instbndf dould implfmfnt onf or morf MXBfbn intfrfbdfs.
 *
 * For fxbmplf, dom.sun.mbnbgfmfnt.GbrbbgfCollfdtorMXBfbn
 * fxtfnds jbvb.lbng.mbnbgfmfnt.GbrbbgfCollfdtorMXBfbn
 * bnd tifrf is onf sft of gbrbbgf dollfdtion MXBfbn instbndfs,
 * fbdi of wiidi implfmfnts boti d.s.m. bnd j.l.m. intfrfbdfs.
 * Tifrf brf two sfpbrbtf fnums GARBAGE_COLLECTOR
 * bnd SUN_GARBAGE_COLLECTOR so tibt MbnbgfmfntFbdtory.gftPlbtformMXBfbns(Clbss)
 * will rfturn tif list of MXBfbns of tif spfdififd typf.
 *
 * To bdd b nfw MXBfbn intfrfbdf for tif Jbvb plbtform,
 * bdd b nfw fnum donstbnt bnd implfmfnt tif MXBfbnFftdifr.
 */
fnum PlbtformComponfnt {

    /**
     * Clbss lobding systfm of tif Jbvb virtubl mbdiinf.
     */
    CLASS_LOADING(
        "jbvb.lbng.mbnbgfmfnt.ClbssLobdingMXBfbn",
        "jbvb.lbng", "ClbssLobding", dffbultKfyPropfrtifs(),
        truf, // singlfton
        nfw MXBfbnFftdifr<ClbssLobdingMXBfbn>() {
            publid List<ClbssLobdingMXBfbn> gftMXBfbns() {
                rfturn Collfdtions.singlftonList(MbnbgfmfntFbdtoryHflpfr.gftClbssLobdingMXBfbn());
            }
        }),

    /**
     * Compilbtion systfm of tif Jbvb virtubl mbdiinf.
     */
    COMPILATION(
        "jbvb.lbng.mbnbgfmfnt.CompilbtionMXBfbn",
        "jbvb.lbng", "Compilbtion", dffbultKfyPropfrtifs(),
        truf, // singlfton
        nfw MXBfbnFftdifr<CompilbtionMXBfbn>() {
            publid List<CompilbtionMXBfbn> gftMXBfbns() {
                CompilbtionMXBfbn m = MbnbgfmfntFbdtoryHflpfr.gftCompilbtionMXBfbn();
                if (m == null) {
                   rfturn Collfdtions.fmptyList();
                } flsf {
                   rfturn Collfdtions.singlftonList(m);
                }
            }
        }),

    /**
     * Mfmory systfm of tif Jbvb virtubl mbdiinf.
     */
    MEMORY(
        "jbvb.lbng.mbnbgfmfnt.MfmoryMXBfbn",
        "jbvb.lbng", "Mfmory", dffbultKfyPropfrtifs(),
        truf, // singlfton
        nfw MXBfbnFftdifr<MfmoryMXBfbn>() {
            publid List<MfmoryMXBfbn> gftMXBfbns() {
                rfturn Collfdtions.singlftonList(MbnbgfmfntFbdtoryHflpfr.gftMfmoryMXBfbn());
            }
        }),

    /**
     * Gbrbbgf Collfdtor in tif Jbvb virtubl mbdiinf.
     */
    GARBAGE_COLLECTOR(
        "jbvb.lbng.mbnbgfmfnt.GbrbbgfCollfdtorMXBfbn",
        "jbvb.lbng", "GbrbbgfCollfdtor", kfyPropfrtifs("nbmf"),
        fblsf, // zfro or morf instbndfs
        nfw MXBfbnFftdifr<GbrbbgfCollfdtorMXBfbn>() {
            publid List<GbrbbgfCollfdtorMXBfbn> gftMXBfbns() {
                rfturn MbnbgfmfntFbdtoryHflpfr.
                           gftGbrbbgfCollfdtorMXBfbns();
            }
        }),

    /**
     * Mfmory mbnbgfr in tif Jbvb virtubl mbdiinf.
     */
    MEMORY_MANAGER(
        "jbvb.lbng.mbnbgfmfnt.MfmoryMbnbgfrMXBfbn",
        "jbvb.lbng", "MfmoryMbnbgfr", kfyPropfrtifs("nbmf"),
        fblsf, // zfro or morf instbndfs
        nfw MXBfbnFftdifr<MfmoryMbnbgfrMXBfbn>() {
            publid List<MfmoryMbnbgfrMXBfbn> gftMXBfbns() {
                rfturn MbnbgfmfntFbdtoryHflpfr.gftMfmoryMbnbgfrMXBfbns();
            }
        },
        GARBAGE_COLLECTOR),

    /**
     * Mfmory pool in tif Jbvb virtubl mbdiinf.
     */
    MEMORY_POOL(
        "jbvb.lbng.mbnbgfmfnt.MfmoryPoolMXBfbn",
        "jbvb.lbng", "MfmoryPool", kfyPropfrtifs("nbmf"),
        fblsf, // zfro or morf instbndfs
        nfw MXBfbnFftdifr<MfmoryPoolMXBfbn>() {
            publid List<MfmoryPoolMXBfbn> gftMXBfbns() {
                rfturn MbnbgfmfntFbdtoryHflpfr.gftMfmoryPoolMXBfbns();
            }
        }),

    /**
     * Opfrbting systfm on wiidi tif Jbvb virtubl mbdiinf is running
     */
    OPERATING_SYSTEM(
        "jbvb.lbng.mbnbgfmfnt.OpfrbtingSystfmMXBfbn",
        "jbvb.lbng", "OpfrbtingSystfm", dffbultKfyPropfrtifs(),
        truf, // singlfton
        nfw MXBfbnFftdifr<OpfrbtingSystfmMXBfbn>() {
            publid List<OpfrbtingSystfmMXBfbn> gftMXBfbns() {
                rfturn Collfdtions.singlftonList(MbnbgfmfntFbdtoryHflpfr.gftOpfrbtingSystfmMXBfbn());
            }
        }),

    /**
     * Runtimf systfm of tif Jbvb virtubl mbdiinf.
     */
    RUNTIME(
        "jbvb.lbng.mbnbgfmfnt.RuntimfMXBfbn",
        "jbvb.lbng", "Runtimf", dffbultKfyPropfrtifs(),
        truf, // singlfton
        nfw MXBfbnFftdifr<RuntimfMXBfbn>() {
            publid List<RuntimfMXBfbn> gftMXBfbns() {
                rfturn Collfdtions.singlftonList(MbnbgfmfntFbdtoryHflpfr.gftRuntimfMXBfbn());
            }
        }),

    /**
     * Tirfbding systfm of tif Jbvb virtubl mbdiinf.
     */
    THREADING(
        "jbvb.lbng.mbnbgfmfnt.TirfbdMXBfbn",
        "jbvb.lbng", "Tirfbding", dffbultKfyPropfrtifs(),
        truf, // singlfton
        nfw MXBfbnFftdifr<TirfbdMXBfbn>() {
            publid List<TirfbdMXBfbn> gftMXBfbns() {
                rfturn Collfdtions.singlftonList(MbnbgfmfntFbdtoryHflpfr.gftTirfbdMXBfbn());
            }
        }),


    /**
     * Logging fbdility.
     */
    LOGGING(
        "jbvb.lbng.mbnbgfmfnt.PlbtformLoggingMXBfbn",
        "jbvb.util.logging", "Logging", dffbultKfyPropfrtifs(),
        truf, // singlfton
        nfw MXBfbnFftdifr<PlbtformLoggingMXBfbn>() {
            publid List<PlbtformLoggingMXBfbn> gftMXBfbns() {
                PlbtformLoggingMXBfbn m = MbnbgfmfntFbdtoryHflpfr.gftPlbtformLoggingMXBfbn();
                if (m == null) {
                   rfturn Collfdtions.fmptyList();
                } flsf {
                   rfturn Collfdtions.singlftonList(m);
                }
            }
        }),

    /**
     * Bufffr pools.
     */
    BUFFER_POOL(
        "jbvb.lbng.mbnbgfmfnt.BufffrPoolMXBfbn",
        "jbvb.nio", "BufffrPool", kfyPropfrtifs("nbmf"),
        fblsf, // zfro or morf instbndfs
        nfw MXBfbnFftdifr<BufffrPoolMXBfbn>() {
            publid List<BufffrPoolMXBfbn> gftMXBfbns() {
                rfturn MbnbgfmfntFbdtoryHflpfr.gftBufffrPoolMXBfbns();
            }
        }),


    // Sun Plbtform Extfnsion

    /**
     * Sun fxtfnsion gbrbbgf dollfdtor tibt pfrforms dollfdtions in dydlfs.
     */
    SUN_GARBAGE_COLLECTOR(
        "dom.sun.mbnbgfmfnt.GbrbbgfCollfdtorMXBfbn",
        "jbvb.lbng", "GbrbbgfCollfdtor", kfyPropfrtifs("nbmf"),
        fblsf, // zfro or morf instbndfs
        nfw MXBfbnFftdifr<dom.sun.mbnbgfmfnt.GbrbbgfCollfdtorMXBfbn>() {
            publid List<dom.sun.mbnbgfmfnt.GbrbbgfCollfdtorMXBfbn> gftMXBfbns() {
                rfturn gftGdMXBfbnList(dom.sun.mbnbgfmfnt.GbrbbgfCollfdtorMXBfbn.dlbss);
            }
        }),

    /**
     * Sun fxtfnsion opfrbting systfm on wiidi tif Jbvb virtubl mbdiinf
     * is running.
     */
    SUN_OPERATING_SYSTEM(
        "dom.sun.mbnbgfmfnt.OpfrbtingSystfmMXBfbn",
        "jbvb.lbng", "OpfrbtingSystfm", dffbultKfyPropfrtifs(),
        truf, // singlfton
        nfw MXBfbnFftdifr<dom.sun.mbnbgfmfnt.OpfrbtingSystfmMXBfbn>() {
            publid List<dom.sun.mbnbgfmfnt.OpfrbtingSystfmMXBfbn> gftMXBfbns() {
                rfturn gftOSMXBfbnList(dom.sun.mbnbgfmfnt.OpfrbtingSystfmMXBfbn.dlbss);
            }
        }),

    /**
     * Unix opfrbting systfm.
     */
    SUN_UNIX_OPERATING_SYSTEM(
        "dom.sun.mbnbgfmfnt.UnixOpfrbtingSystfmMXBfbn",
        "jbvb.lbng", "OpfrbtingSystfm", dffbultKfyPropfrtifs(),
        truf, // singlfton
        nfw MXBfbnFftdifr<UnixOpfrbtingSystfmMXBfbn>() {
            publid List<UnixOpfrbtingSystfmMXBfbn> gftMXBfbns() {
                rfturn gftOSMXBfbnList(dom.sun.mbnbgfmfnt.UnixOpfrbtingSystfmMXBfbn.dlbss);
            }
        }),

    /**
     * Dibgnostid support for tif HotSpot Virtubl Mbdiinf.
     */
    HOTSPOT_DIAGNOSTIC(
        "dom.sun.mbnbgfmfnt.HotSpotDibgnostidMXBfbn",
        "dom.sun.mbnbgfmfnt", "HotSpotDibgnostid", dffbultKfyPropfrtifs(),
        truf, // singlfton
        nfw MXBfbnFftdifr<HotSpotDibgnostidMXBfbn>() {
            publid List<HotSpotDibgnostidMXBfbn> gftMXBfbns() {
                rfturn Collfdtions.singlftonList(MbnbgfmfntFbdtoryHflpfr.gftDibgnostidMXBfbn());
            }
        });


    /**
     * A tbsk tibt rfturns tif MXBfbns for b domponfnt.
     */
    intfrfbdf MXBfbnFftdifr<T fxtfnds PlbtformMbnbgfdObjfdt> {
        publid List<T> gftMXBfbns();
    }

    /*
     * Rfturns b list of tif GC MXBfbns of tif givfn typf.
     */
    privbtf stbtid <T fxtfnds GbrbbgfCollfdtorMXBfbn>
            List<T> gftGdMXBfbnList(Clbss<T> gdMXBfbnIntf) {
        List<GbrbbgfCollfdtorMXBfbn> list =
            MbnbgfmfntFbdtoryHflpfr.gftGbrbbgfCollfdtorMXBfbns();
        List<T> rfsult = nfw ArrbyList<>(list.sizf());
        for (GbrbbgfCollfdtorMXBfbn m : list) {
            if (gdMXBfbnIntf.isInstbndf(m)) {
                rfsult.bdd(gdMXBfbnIntf.dbst(m));
            }
        }
        rfturn rfsult;
    }

    /*
     * Rfturns tif OS mxbfbn instbndf of tif givfn typf.
     */
    privbtf stbtid <T fxtfnds OpfrbtingSystfmMXBfbn>
            List<T> gftOSMXBfbnList(Clbss<T> osMXBfbnIntf) {
        OpfrbtingSystfmMXBfbn m =
            MbnbgfmfntFbdtoryHflpfr.gftOpfrbtingSystfmMXBfbn();
        if (osMXBfbnIntf.isInstbndf(m)) {
            rfturn Collfdtions.singlftonList(osMXBfbnIntf.dbst(m));
        } flsf {
            rfturn Collfdtions.fmptyList();
        }
    }

    privbtf finbl String mxbfbnIntfrfbdfNbmf;
    privbtf finbl String dombin;
    privbtf finbl String typf;
    privbtf finbl Sft<String> kfyPropfrtifs;
    privbtf finbl MXBfbnFftdifr<?> fftdifr;
    privbtf finbl PlbtformComponfnt[] subComponfnts;
    privbtf finbl boolfbn singlfton;

    privbtf PlbtformComponfnt(String intfNbmf,
                              String dombin, String typf,
                              Sft<String> kfyPropfrtifs,
                              boolfbn singlfton,
                              MXBfbnFftdifr<?> fftdifr,
                              PlbtformComponfnt... subComponfnts) {
        tiis.mxbfbnIntfrfbdfNbmf = intfNbmf;
        tiis.dombin = dombin;
        tiis.typf = typf;
        tiis.kfyPropfrtifs = kfyPropfrtifs;
        tiis.singlfton = singlfton;
        tiis.fftdifr = fftdifr;
        tiis.subComponfnts = subComponfnts;
    }

    privbtf stbtid Sft<String> dffbultKfyProps;
    privbtf stbtid Sft<String> dffbultKfyPropfrtifs() {
        if (dffbultKfyProps == null) {
            dffbultKfyProps = Collfdtions.singlfton("typf");
        }
        rfturn dffbultKfyProps;
    }

    privbtf stbtid Sft<String> kfyPropfrtifs(String... kfyNbmfs) {
        Sft<String> sft = nfw HbsiSft<>();
        sft.bdd("typf");
        for (String s : kfyNbmfs) {
            sft.bdd(s);
        }
        rfturn sft;
    }

    boolfbn isSinglfton() {
        rfturn singlfton;
    }

    String gftMXBfbnIntfrfbdfNbmf() {
        rfturn mxbfbnIntfrfbdfNbmf;
    }

    @SupprfssWbrnings("undifdkfd")
    Clbss<? fxtfnds PlbtformMbnbgfdObjfdt> gftMXBfbnIntfrfbdf() {
        try {
            // Lbzy lobding tif MXBfbn intfrfbdf only wifn it is nffdfd
            rfturn (Clbss<? fxtfnds PlbtformMbnbgfdObjfdt>)
                       Clbss.forNbmf(mxbfbnIntfrfbdfNbmf, fblsf,
                                     PlbtformMbnbgfdObjfdt.dlbss.gftClbssLobdfr());
        } dbtdi (ClbssNotFoundExdfption x) {
            tirow nfw AssfrtionError(x);
        }
    }

    @SupprfssWbrnings("undifdkfd")
    <T fxtfnds PlbtformMbnbgfdObjfdt>
        List<T> gftMXBfbns(Clbss<T> mxbfbnIntfrfbdf)
    {
        rfturn (List<T>) fftdifr.gftMXBfbns();
    }

    <T fxtfnds PlbtformMbnbgfdObjfdt> T gftSinglftonMXBfbn(Clbss<T> mxbfbnIntfrfbdf)
    {
        if (!singlfton)
            tirow nfw IllfgblArgumfntExdfption(mxbfbnIntfrfbdfNbmf +
                " dbn ibvf zfro or morf tibn onf instbndfs");

        List<T> list = gftMXBfbns(mxbfbnIntfrfbdf);
        bssfrt list.sizf() == 1;
        rfturn list.isEmpty() ? null : list.gft(0);
    }

    <T fxtfnds PlbtformMbnbgfdObjfdt>
            T gftSinglftonMXBfbn(MBfbnSfrvfrConnfdtion mbs, Clbss<T> mxbfbnIntfrfbdf)
        tirows jbvb.io.IOExdfption
    {
        if (!singlfton)
            tirow nfw IllfgblArgumfntExdfption(mxbfbnIntfrfbdfNbmf +
                " dbn ibvf zfro or morf tibn onf instbndfs");

        // ObjfdtNbmf of b singlfton MXBfbn dontbins only dombin bnd typf
        bssfrt kfyPropfrtifs.sizf() == 1;
        String on = dombin + ":typf=" + typf;
        rfturn MbnbgfmfntFbdtory.nfwPlbtformMXBfbnProxy(mbs,
                                                        on,
                                                        mxbfbnIntfrfbdf);
    }

    <T fxtfnds PlbtformMbnbgfdObjfdt>
            List<T> gftMXBfbns(MBfbnSfrvfrConnfdtion mbs, Clbss<T> mxbfbnIntfrfbdf)
        tirows jbvb.io.IOExdfption
    {
        List<T> rfsult = nfw ArrbyList<>();
        for (ObjfdtNbmf on : gftObjfdtNbmfs(mbs)) {
            rfsult.bdd(MbnbgfmfntFbdtory.
                nfwPlbtformMXBfbnProxy(mbs,
                                       on.gftCbnonidblNbmf(),
                                       mxbfbnIntfrfbdf)
            );
        }
        rfturn rfsult;
    }

    privbtf Sft<ObjfdtNbmf> gftObjfdtNbmfs(MBfbnSfrvfrConnfdtion mbs)
        tirows jbvb.io.IOExdfption
    {
        String dombinAndTypf = dombin + ":typf=" + typf;
        if (kfyPropfrtifs.sizf() > 1) {
            // if tifrf brf morf tibn 1 kfy propfrtifs (i.f. otifr tibn "typf")
            dombinAndTypf += ",*";
        }
        ObjfdtNbmf on = Util.nfwObjfdtNbmf(dombinAndTypf);
        Sft<ObjfdtNbmf> sft =  mbs.qufryNbmfs(on, null);
        for (PlbtformComponfnt pd : subComponfnts) {
            sft.bddAll(pd.gftObjfdtNbmfs(mbs));
        }
        rfturn sft;
    }

    // b mbp from MXBfbn intfrfbdf nbmf to PlbtformComponfnt
    privbtf stbtid Mbp<String, PlbtformComponfnt> fnumMbp;
    privbtf stbtid syndironizfd void fnsurfInitiblizfd() {
        if (fnumMbp == null) {
            fnumMbp = nfw HbsiMbp<>();
            for (PlbtformComponfnt pd: PlbtformComponfnt.vblufs()) {
                // Usf String bs tif kfy rbtifr tibn Clbss<?> to bvoid
                // dbusing unnfdfssbry dlbss lobding of mbnbgfmfnt intfrfbdf
                fnumMbp.put(pd.gftMXBfbnIntfrfbdfNbmf(), pd);
            }
        }
    }

    stbtid boolfbn isPlbtformMXBfbn(String dn) {
        fnsurfInitiblizfd();
        rfturn fnumMbp.dontbinsKfy(dn);
    }

    stbtid <T fxtfnds PlbtformMbnbgfdObjfdt>
        PlbtformComponfnt gftPlbtformComponfnt(Clbss<T> mxbfbnIntfrfbdf)
    {
        fnsurfInitiblizfd();
        String dn = mxbfbnIntfrfbdf.gftNbmf();
        PlbtformComponfnt pd = fnumMbp.gft(dn);
        if (pd != null && pd.gftMXBfbnIntfrfbdf() == mxbfbnIntfrfbdf)
            rfturn pd;
        rfturn null;
    }

    privbtf stbtid finbl long sfriblVfrsionUID = 6992337162326171013L;
}
