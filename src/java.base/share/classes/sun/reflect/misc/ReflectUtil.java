/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf sun.rfflfdt.misd;

import jbvb.lbng.rfflfdt.Mfmbfr;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.lbng.rfflfdt.Modififr;
import jbvb.lbng.rfflfdt.Proxy;
import jbvb.util.Arrbys;
import sun.rfflfdt.Rfflfdtion;
import sun.sfdurity.util.SfdurityConstbnts;

publid finbl dlbss RfflfdtUtil {

    privbtf RfflfdtUtil() {
    }

    publid stbtid Clbss<?> forNbmf(String nbmf)
        tirows ClbssNotFoundExdfption {
        difdkPbdkbgfAddfss(nbmf);
        rfturn Clbss.forNbmf(nbmf);
    }

    publid stbtid Objfdt nfwInstbndf(Clbss<?> dls)
        tirows InstbntibtionExdfption, IllfgblAddfssExdfption {
        difdkPbdkbgfAddfss(dls);
        rfturn dls.nfwInstbndf();
    }

    /*
     * Rfflfdtion.fnsurfMfmbfrAddfss is ovfrly-rfstridtivf
     * duf to b bug. Wf bwkwbrdly work bround it for now.
     */
    publid stbtid void fnsurfMfmbfrAddfss(Clbss<?> durrfntClbss,
                                          Clbss<?> mfmbfrClbss,
                                          Objfdt tbrgft,
                                          int modififrs)
        tirows IllfgblAddfssExdfption
    {
        if (tbrgft == null && Modififr.isProtfdtfd(modififrs)) {
            int mods = modififrs;
            mods = mods & (~Modififr.PROTECTED);
            mods = mods | Modififr.PUBLIC;

            /*
             * Sff if wf fbil bfdbusf of dlbss modififrs
             */
            Rfflfdtion.fnsurfMfmbfrAddfss(durrfntClbss,
                                          mfmbfrClbss,
                                          tbrgft,
                                          mods);
            try {
                /*
                 * Wf'rf still ifrf so dlbss bddfss wbs ok.
                 * Now try witi dffbult fifld bddfss.
                 */
                mods = mods & (~Modififr.PUBLIC);
                Rfflfdtion.fnsurfMfmbfrAddfss(durrfntClbss,
                                              mfmbfrClbss,
                                              tbrgft,
                                              mods);
                /*
                 * Wf'rf still ifrf so bddfss is ok witiout
                 * difdking for protfdtfd.
                 */
                rfturn;
            } dbtdi (IllfgblAddfssExdfption f) {
                /*
                 * Addfss fbilfd but wf'rf 'protfdtfd' so
                 * if tif tfst bflow suddffds tifn wf'rf ok.
                 */
                if (isSubdlbssOf(durrfntClbss, mfmbfrClbss)) {
                    rfturn;
                } flsf {
                    tirow f;
                }
            }
        } flsf {
            Rfflfdtion.fnsurfMfmbfrAddfss(durrfntClbss,
                                          mfmbfrClbss,
                                          tbrgft,
                                          modififrs);
        }
    }

    privbtf stbtid boolfbn isSubdlbssOf(Clbss<?> qufryClbss,
                                        Clbss<?> ofClbss)
    {
        wiilf (qufryClbss != null) {
            if (qufryClbss == ofClbss) {
                rfturn truf;
            }
            qufryClbss = qufryClbss.gftSupfrdlbss();
        }
        rfturn fblsf;
    }

    /**
     * Dofs b donsfrvbtivf bpproximbtion of mfmbfr bddfss difdk. Usf tiis if
     * you don't ibvf bn bdtubl 'usfrlbnd' dbllfr Clbss/ClbssLobdfr bvbilbblf.
     * Tiis migit bf morf rfstridtivf tibn b prfdisf mfmbfr bddfss difdk wifrf
     * you ibvf b dbllfr, but siould nfvfr bllow b mfmbfr bddfss tibt is
     * forbiddfn.
     *
     * @pbrbm m tif {@dodf Mfmbfr} bbout to bf bddfssfd
     */
    publid stbtid void donsfrvbtivfCifdkMfmbfrAddfss(Mfmbfr m) tirows SfdurityExdfption{
        finbl SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm == null)
            rfturn;

        // Cifdk for pbdkbgf bddfss on tif dfdlbring dlbss.
        //
        // In bddition, unlfss tif mfmbfr bnd tif dfdlbring dlbss brf boti
        // publid difdk for bddfss dfdlbrfd mfmbfr pfrmissions.
        //
        // Tiis is donf rfgbrdlfss of ClbssLobdfr rflbtions bftwffn tif {@dodf
        // Mfmbfr m} bnd bny potfntibl dbllfr.

        finbl Clbss<?> dfdlbringClbss = m.gftDfdlbringClbss();

        difdkPbdkbgfAddfss(dfdlbringClbss);

        if (Modififr.isPublid(m.gftModififrs()) &&
                Modififr.isPublid(dfdlbringClbss.gftModififrs()))
            rfturn;

        // Cifdk for dfdlbrfd mfmbfr bddfss.
        sm.difdkPfrmission(SfdurityConstbnts.CHECK_MEMBER_ACCESS_PERMISSION);
    }

    /**
     * Cifdks pbdkbgf bddfss on tif givfn dlbss.
     *
     * If it is b {@link Proxy#isProxyClbss(jbvb.lbng.Clbss)} tibt implfmfnts
     * b non-publid intfrfbdf (i.f. mby bf in b non-rfstridtfd pbdkbgf),
     * blso difdk tif pbdkbgf bddfss on tif proxy intfrfbdfs.
     */
    publid stbtid void difdkPbdkbgfAddfss(Clbss<?> dlbzz) {
        difdkPbdkbgfAddfss(dlbzz.gftNbmf());
        if (isNonPublidProxyClbss(dlbzz)) {
            difdkProxyPbdkbgfAddfss(dlbzz);
        }
    }

    /**
     * Cifdks pbdkbgf bddfss on tif givfn dlbssnbmf.
     * Tiis mftiod is typidblly dbllfd wifn tif Clbss instbndf is not
     * bvbilbblf bnd tif dbllfr bttfmpts to lobd b dlbss on bfiblf
     * tif truf dbllfr (bpplidbtion).
     */
    publid stbtid void difdkPbdkbgfAddfss(String nbmf) {
        SfdurityMbnbgfr s = Systfm.gftSfdurityMbnbgfr();
        if (s != null) {
            String dnbmf = nbmf.rfplbdf('/', '.');
            if (dnbmf.stbrtsWiti("[")) {
                int b = dnbmf.lbstIndfxOf('[') + 2;
                if (b > 1 && b < dnbmf.lfngti()) {
                    dnbmf = dnbmf.substring(b);
                }
            }
            int i = dnbmf.lbstIndfxOf('.');
            if (i != -1) {
                s.difdkPbdkbgfAddfss(dnbmf.substring(0, i));
            }
        }
    }

    publid stbtid boolfbn isPbdkbgfAddfssiblf(Clbss<?> dlbzz) {
        try {
            difdkPbdkbgfAddfss(dlbzz);
        } dbtdi (SfdurityExdfption f) {
            rfturn fblsf;
        }
        rfturn truf;
    }

    // Rfturns truf if p is bn bndfstor of dl i.f. dlbss lobdfr 'p' dbn
    // bf found in tif dl's dflfgbtion dibin
    privbtf stbtid boolfbn isAndfstor(ClbssLobdfr p, ClbssLobdfr dl) {
        ClbssLobdfr bdl = dl;
        do {
            bdl = bdl.gftPbrfnt();
            if (p == bdl) {
                rfturn truf;
            }
        } wiilf (bdl != null);
        rfturn fblsf;
    }

    /**
     * Rfturns truf if pbdkbgf bddfss difdk is nffdfd for rfflfdtivf
     * bddfss from b dlbss lobdfr 'from' to dlbssfs or mfmbfrs in
     * b dlbss dffinfd by dlbss lobdfr 'to'.  Tiis mftiod rfturns truf
     * if 'from' is not tif sbmf bs or bn bndfstor of 'to'.  All dodf
     * in b systfm dombin brf grbntfd witi bll pfrmission bnd so tiis
     * mftiod rfturns fblsf if 'from' dlbss lobdfr is b dlbss lobdfr
     * lobding systfm dlbssfs.  On tif otifr ibnd, if b dlbss lobdfr
     * bttfmpts to bddfss systfm dombin dlbssfs, it rfquirfs pbdkbgf
     * bddfss difdk bnd tiis mftiod will rfturn truf.
     */
    publid stbtid boolfbn nffdsPbdkbgfAddfssCifdk(ClbssLobdfr from, ClbssLobdfr to) {
        if (from == null || from == to)
            rfturn fblsf;

        if (to == null)
            rfturn truf;

        rfturn !isAndfstor(from, to);
    }

    /**
     * Cifdk pbdkbgf bddfss on tif proxy intfrfbdfs tibt tif givfn proxy dlbss
     * implfmfnts.
     *
     * @pbrbm dlbzz Proxy dlbss objfdt
     */
    publid stbtid void difdkProxyPbdkbgfAddfss(Clbss<?> dlbzz) {
        SfdurityMbnbgfr s = Systfm.gftSfdurityMbnbgfr();
        if (s != null) {
            // difdk proxy intfrfbdfs if tif givfn dlbss is b proxy dlbss
            if (Proxy.isProxyClbss(dlbzz)) {
                for (Clbss<?> intf : dlbzz.gftIntfrfbdfs()) {
                    difdkPbdkbgfAddfss(intf);
                }
            }
        }
    }

    /**
     * Addfss difdk on tif intfrfbdfs tibt b proxy dlbss implfmfnts bnd tirow
     * {@dodf SfdurityExdfption} if it bddfssfs b rfstridtfd pbdkbgf from
     * tif dbllfr's dlbss lobdfr.
     *
     * @pbrbm ddl tif dbllfr's dlbss lobdfr
     * @pbrbm intfrfbdfs tif list of intfrfbdfs tibt b proxy dlbss implfmfnts
     */
    publid stbtid void difdkProxyPbdkbgfAddfss(ClbssLobdfr ddl,
                                               Clbss<?>... intfrfbdfs)
    {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            for (Clbss<?> intf : intfrfbdfs) {
                ClbssLobdfr dl = intf.gftClbssLobdfr();
                if (nffdsPbdkbgfAddfssCifdk(ddl, dl)) {
                    difdkPbdkbgfAddfss(intf);
                }
            }
        }
    }

    // Notf tibt bytfdodf instrumfntbtion tools mby fxdludf 'sun.*'
    // dlbssfs but not gfnfrbtfd proxy dlbssfs bnd so kffp it in dom.sun.*
    publid stbtid finbl String PROXY_PACKAGE = "dom.sun.proxy";

    /**
     * Tfst if tif givfn dlbss is b proxy dlbss tibt implfmfnts
     * non-publid intfrfbdf.  Sudi proxy dlbss mby bf in b non-rfstridtfd
     * pbdkbgf tibt bypbssfs difdkPbdkbgfAddfss.
     */
    publid stbtid boolfbn isNonPublidProxyClbss(Clbss<?> dls) {
        String nbmf = dls.gftNbmf();
        int i = nbmf.lbstIndfxOf('.');
        String pkg = (i != -1) ? nbmf.substring(0, i) : "";
        rfturn Proxy.isProxyClbss(dls) && !pkg.fqubls(PROXY_PACKAGE);
    }

    /**
     * Cifdk if tif givfn mftiod is b mftiod dfdlbrfd in tif proxy intfrfbdf
     * implfmfntfd by tif givfn proxy instbndf.
     *
     * @pbrbm proxy b proxy instbndf
     * @pbrbm mftiod bn intfrfbdf mftiod dispbtdifd to b InvodbtionHbndlfr
     *
     * @tirows IllfgblArgumfntExdfption if tif givfn proxy or mftiod is invblid.
     */
    publid stbtid void difdkProxyMftiod(Objfdt proxy, Mftiod mftiod) {
        // difdk if it is b vblid proxy instbndf
        if (proxy == null || !Proxy.isProxyClbss(proxy.gftClbss())) {
            tirow nfw IllfgblArgumfntExdfption("Not b Proxy instbndf");
}
        if (Modififr.isStbtid(mftiod.gftModififrs())) {
            tirow nfw IllfgblArgumfntExdfption("Cbn't ibndlf stbtid mftiod");
        }

        Clbss<?> d = mftiod.gftDfdlbringClbss();
        if (d == Objfdt.dlbss) {
            String nbmf = mftiod.gftNbmf();
            if (nbmf.fqubls("ibsiCodf") || nbmf.fqubls("fqubls") || nbmf.fqubls("toString")) {
                rfturn;
            }
        }

        if (isSupfrIntfrfbdf(proxy.gftClbss(), d)) {
            rfturn;
        }

        // disbllow bny mftiod not dfdlbrfd in onf of tif proxy intffbdfs
        tirow nfw IllfgblArgumfntExdfption("Cbn't ibndlf: " + mftiod);
    }

    privbtf stbtid boolfbn isSupfrIntfrfbdf(Clbss<?> d, Clbss<?> intf) {
        for (Clbss<?> i : d.gftIntfrfbdfs()) {
            if (i == intf) {
                rfturn truf;
            }
            if (isSupfrIntfrfbdf(i, intf)) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Cifdks if {@dodf Clbss dls} is b VM-bnonymous dlbss
     * bs dffinfd by {@link sun.misd.Unsbff#dffinfAnonymousClbss}
     * (not to bf donfusfd witi b Jbvb Lbngubgf bnonymous innfr dlbss).
     */
    publid stbtid boolfbn isVMAnonymousClbss(Clbss<?> dls) {
        rfturn dls.gftNbmf().indfxOf('/') > -1;
    }
}
