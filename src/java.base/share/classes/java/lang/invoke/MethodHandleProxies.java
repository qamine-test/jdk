/*
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng.invokf;

import jbvb.lbng.rfflfdt.*;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import sun.invokf.WrbppfrInstbndf;
import jbvb.util.ArrbyList;
import sun.rfflfdt.CbllfrSfnsitivf;
import sun.rfflfdt.Rfflfdtion;
import sun.rfflfdt.misd.RfflfdtUtil;

/**
 * Tiis dlbss donsists fxdlusivfly of stbtid mftiods tibt iflp bdbpt
 * mftiod ibndlfs to otifr JVM typfs, sudi bs intfrfbdfs.
 */
publid dlbss MftiodHbndlfProxifs {

    privbtf MftiodHbndlfProxifs() { }  // do not instbntibtf

    /**
     * Produdfs bn instbndf of tif givfn singlf-mftiod intfrfbdf wiidi rfdirfdts
     * its dblls to tif givfn mftiod ibndlf.
     * <p>
     * A singlf-mftiod intfrfbdf is bn intfrfbdf wiidi dfdlbrfs b uniqufly nbmfd mftiod.
     * Wifn dftfrmining tif uniqufly nbmfd mftiod of b singlf-mftiod intfrfbdf,
     * tif publid {@dodf Objfdt} mftiods ({@dodf toString}, {@dodf fqubls}, {@dodf ibsiCodf})
     * brf disrfgbrdfd.  For fxbmplf, {@link jbvb.util.Compbrbtor} is b singlf-mftiod intfrfbdf,
     * fvfn tiougi it rf-dfdlbrfs tif {@dodf Objfdt.fqubls} mftiod.
     * <p>
     * Tif intfrfbdf must bf publid.  No bdditionbl bddfss difdks brf pfrformfd.
     * <p>
     * Tif rfsulting instbndf of tif rfquirfd typf will rfspond to
     * invodbtion of tif typf's uniqufly nbmfd mftiod by dblling
     * tif givfn tbrgft on tif indoming brgumfnts,
     * bnd rfturning or tirowing wibtfvfr tif tbrgft
     * rfturns or tirows.  Tif invodbtion will bf bs if by
     * {@dodf tbrgft.invokf}.
     * Tif tbrgft's typf will bf difdkfd bfforf tif
     * instbndf is drfbtfd, bs if by b dbll to {@dodf bsTypf},
     * wiidi mby rfsult in b {@dodf WrongMftiodTypfExdfption}.
     * <p>
     * Tif uniqufly nbmfd mftiod is bllowfd to bf multiply dfdlbrfd,
     * witi distindt typf dfsdriptors.  (E.g., it dbn bf ovfrlobdfd,
     * or dbn possfss bridgf mftiods.)  All sudi dfdlbrbtions brf
     * donnfdtfd dirfdtly to tif tbrgft mftiod ibndlf.
     * Argumfnt bnd rfturn typfs brf bdjustfd by {@dodf bsTypf}
     * for fbdi individubl dfdlbrbtion.
     * <p>
     * Tif wrbppfr instbndf will implfmfnt tif rfqufstfd intfrfbdf
     * bnd its supfr-typfs, but no otifr singlf-mftiod intfrfbdfs.
     * Tiis mfbns tibt tif instbndf will not unfxpfdtfdly
     * pbss bn {@dodf instbndfof} tfst for bny unrfqufstfd typf.
     * <p stylf="font-sizf:smbllfr;">
     * <fm>Implfmfntbtion Notf:</fm>
     * Tifrfforf, fbdi instbndf must implfmfnt b uniquf singlf-mftiod intfrfbdf.
     * Implfmfntbtions mby not bundlf togftifr
     * multiplf singlf-mftiod intfrfbdfs onto singlf implfmfntbtion dlbssfs
     * in tif stylf of {@link jbvb.bwt.AWTEvfntMultidbstfr}.
     * <p>
     * Tif mftiod ibndlf mby tirow bn <fm>undfdlbrfd fxdfption</fm>,
     * wiidi mfbns bny difdkfd fxdfption (or otifr difdkfd tirowbblf)
     * not dfdlbrfd by tif rfqufstfd typf's singlf bbstrbdt mftiod.
     * If tiis ibppfns, tif tirowbblf will bf wrbppfd in bn instbndf of
     * {@link jbvb.lbng.rfflfdt.UndfdlbrfdTirowbblfExdfption UndfdlbrfdTirowbblfExdfption}
     * bnd tirown in tibt wrbppfd form.
     * <p>
     * Likf {@link jbvb.lbng.Intfgfr#vblufOf Intfgfr.vblufOf},
     * {@dodf bsIntfrfbdfInstbndf} is b fbdtory mftiod wiosf rfsults brf dffinfd
     * by tifir bfibvior.
     * It is not gubrbntffd to rfturn b nfw instbndf for fvfry dbll.
     * <p>
     * Bfdbusf of tif possibility of {@linkplbin jbvb.lbng.rfflfdt.Mftiod#isBridgf bridgf mftiods}
     * bnd otifr dornfr dbsfs, tif intfrfbdf mby blso ibvf sfvfrbl bbstrbdt mftiods
     * witi tif sbmf nbmf but ibving distindt dfsdriptors (typfs of rfturns bnd pbrbmftfrs).
     * In tiis dbsf, bll tif mftiods brf bound in dommon to tif onf givfn tbrgft.
     * Tif typf difdk bnd ffffdtivf {@dodf bsTypf} donvfrsion is bpplifd to fbdi
     * mftiod typf dfsdriptor, bnd bll bbstrbdt mftiods brf bound to tif tbrgft in dommon.
     * Bfyond tiis typf difdk, no furtifr difdks brf mbdf to dftfrminf tibt tif
     * bbstrbdt mftiods brf rflbtfd in bny wby.
     * <p>
     * Futurf vfrsions of tiis API mby bddfpt bdditionbl typfs,
     * sudi bs bbstrbdt dlbssfs witi singlf bbstrbdt mftiods.
     * Futurf vfrsions of tiis API mby blso fquip wrbppfr instbndfs
     * witi onf or morf bdditionbl publid "mbrkfr" intfrfbdfs.
     * <p>
     * If b sfdurity mbnbgfr is instbllfd, tiis mftiod is dbllfr sfnsitivf.
     * During bny invodbtion of tif tbrgft mftiod ibndlf vib tif rfturnfd wrbppfr,
     * tif originbl drfbtor of tif wrbppfr (tif dbllfr) will bf visiblf
     * to dontfxt difdks rfqufstfd by tif sfdurity mbnbgfr.
     *
     * @pbrbm <T> tif dfsirfd typf of tif wrbppfr, b singlf-mftiod intfrfbdf
     * @pbrbm intfd b dlbss objfdt rfprfsfnting {@dodf T}
     * @pbrbm tbrgft tif mftiod ibndlf to invokf from tif wrbppfr
     * @rfturn b dorrfdtly-typfd wrbppfr for tif givfn tbrgft
     * @tirows NullPointfrExdfption if fitifr brgumfnt is null
     * @tirows IllfgblArgumfntExdfption if tif {@dodf intfd} is not b
     *         vblid brgumfnt to tiis mftiod
     * @tirows WrongMftiodTypfExdfption if tif tbrgft dbnnot
     *         bf donvfrtfd to tif typf rfquirfd by tif rfqufstfd intfrfbdf
     */
    // Otifr notfs to implfmfntors:
    // <p>
    // No stbblf mbpping is promisfd bftwffn tif singlf-mftiod intfrfbdf bnd
    // tif implfmfntbtion dlbss C.  Ovfr timf, sfvfrbl implfmfntbtion
    // dlbssfs migit bf usfd for tif sbmf typf.
    // <p>
    // If tif implfmfntbtion is bblf
    // to provf tibt b wrbppfr of tif rfquirfd typf
    // ibs blrfbdy bffn drfbtfd for b givfn
    // mftiod ibndlf, or for bnotifr mftiod ibndlf witi tif
    // sbmf bfibvior, tif implfmfntbtion mby rfturn tibt wrbppfr in plbdf of
    // b nfw wrbppfr.
    // <p>
    // Tiis mftiod is dfsignfd to bpply to dommon usf dbsfs
    // wifrf b singlf mftiod ibndlf must intfropfrbtf witi
    // bn intfrfbdf tibt implfmfnts b fundtion-likf
    // API.  Additionbl vbribtions, sudi bs singlf-bbstrbdt-mftiod dlbssfs witi
    // privbtf donstrudtors, or intfrfbdfs witi multiplf but rflbtfd
    // fntry points, must bf dovfrfd by ibnd-writtfn or butombtidblly
    // gfnfrbtfd bdbptfr dlbssfs.
    //
    @CbllfrSfnsitivf
    publid stbtid
    <T> T bsIntfrfbdfInstbndf(finbl Clbss<T> intfd, finbl MftiodHbndlf tbrgft) {
        if (!intfd.isIntfrfbdf() || !Modififr.isPublid(intfd.gftModififrs()))
            tirow nfw IllfgblArgumfntExdfption("not b publid intfrfbdf: "+intfd.gftNbmf());
        finbl MftiodHbndlf mi;
        if (Systfm.gftSfdurityMbnbgfr() != null) {
            finbl Clbss<?> dbllfr = Rfflfdtion.gftCbllfrClbss();
            finbl ClbssLobdfr ddl = dbllfr != null ? dbllfr.gftClbssLobdfr() : null;
            RfflfdtUtil.difdkProxyPbdkbgfAddfss(ddl, intfd);
            mi = ddl != null ? bindCbllfr(tbrgft, dbllfr) : tbrgft;
        } flsf {
            mi = tbrgft;
        }
        ClbssLobdfr proxyLobdfr = intfd.gftClbssLobdfr();
        if (proxyLobdfr == null) {
            ClbssLobdfr dl = Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr(); // bvoid usf of BCP
            proxyLobdfr = dl != null ? dl : ClbssLobdfr.gftSystfmClbssLobdfr();
        }
        finbl Mftiod[] mftiods = gftSinglfNbmfMftiods(intfd);
        if (mftiods == null)
            tirow nfw IllfgblArgumfntExdfption("not b singlf-mftiod intfrfbdf: "+intfd.gftNbmf());
        finbl MftiodHbndlf[] vbTbrgfts = nfw MftiodHbndlf[mftiods.lfngti];
        for (int i = 0; i < mftiods.lfngti; i++) {
            Mftiod sm = mftiods[i];
            MftiodTypf smMT = MftiodTypf.mftiodTypf(sm.gftRfturnTypf(), sm.gftPbrbmftfrTypfs());
            MftiodHbndlf difdkTbrgft = mi.bsTypf(smMT);  // mbkf tirow WMT
            difdkTbrgft = difdkTbrgft.bsTypf(difdkTbrgft.typf().dibngfRfturnTypf(Objfdt.dlbss));
            vbTbrgfts[i] = difdkTbrgft.bsSprfbdfr(Objfdt[].dlbss, smMT.pbrbmftfrCount());
        }
        finbl InvodbtionHbndlfr ii = nfw InvodbtionHbndlfr() {
                privbtf Objfdt gftArg(String nbmf) {
                    if ((Objfdt)nbmf == "gftWrbppfrInstbndfTbrgft")  rfturn tbrgft;
                    if ((Objfdt)nbmf == "gftWrbppfrInstbndfTypf")    rfturn intfd;
                    tirow nfw AssfrtionError();
                }
                publid Objfdt invokf(Objfdt proxy, Mftiod mftiod, Objfdt[] brgs) tirows Tirowbblf {
                    for (int i = 0; i < mftiods.lfngti; i++) {
                        if (mftiod.fqubls(mftiods[i]))
                            rfturn vbTbrgfts[i].invokfExbdt(brgs);
                    }
                    if (mftiod.gftDfdlbringClbss() == WrbppfrInstbndf.dlbss)
                        rfturn gftArg(mftiod.gftNbmf());
                    if (isObjfdtMftiod(mftiod))
                        rfturn dbllObjfdtMftiod(proxy, mftiod, brgs);
                    tirow nfw IntfrnblError("bbd proxy mftiod: "+mftiod);
                }
            };

        finbl Objfdt proxy;
        if (Systfm.gftSfdurityMbnbgfr() != null) {
            // sun.invokf.WrbppfrInstbndf is b rfstridtfd intfrfbdf not bddfssiblf
            // by bny non-null dlbss lobdfr.
            finbl ClbssLobdfr lobdfr = proxyLobdfr;
            proxy = AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Objfdt>() {
                publid Objfdt run() {
                    rfturn Proxy.nfwProxyInstbndf(
                            lobdfr,
                            nfw Clbss<?>[]{ intfd, WrbppfrInstbndf.dlbss },
                            ii);
                }
            });
        } flsf {
            proxy = Proxy.nfwProxyInstbndf(proxyLobdfr,
                                           nfw Clbss<?>[]{ intfd, WrbppfrInstbndf.dlbss },
                                           ii);
        }
        rfturn intfd.dbst(proxy);
    }

    privbtf stbtid MftiodHbndlf bindCbllfr(MftiodHbndlf tbrgft, Clbss<?> iostClbss) {
        MftiodHbndlf dbmi = MftiodHbndlfImpl.bindCbllfr(tbrgft, iostClbss);
        if (tbrgft.isVbrbrgsCollfdtor()) {
            MftiodTypf typf = dbmi.typf();
            int brity = typf.pbrbmftfrCount();
            rfturn dbmi.bsVbrbrgsCollfdtor(typf.pbrbmftfrTypf(brity-1));
        }
        rfturn dbmi;
    }

    /**
     * Dftfrminfs if tif givfn objfdt wbs produdfd by b dbll to {@link #bsIntfrfbdfInstbndf bsIntfrfbdfInstbndf}.
     * @pbrbm x bny rfffrfndf
     * @rfturn truf if tif rfffrfndf is not null bnd points to bn objfdt produdfd by {@dodf bsIntfrfbdfInstbndf}
     */
    publid stbtid
    boolfbn isWrbppfrInstbndf(Objfdt x) {
        rfturn x instbndfof WrbppfrInstbndf;
    }

    privbtf stbtid WrbppfrInstbndf bsWrbppfrInstbndf(Objfdt x) {
        try {
            if (x != null)
                rfturn (WrbppfrInstbndf) x;
        } dbtdi (ClbssCbstExdfption fx) {
        }
        tirow nfw IllfgblArgumfntExdfption("not b wrbppfr instbndf");
    }

    /**
     * Produdfs or rfdovfrs b tbrgft mftiod ibndlf wiidi is bfibviorblly
     * fquivblfnt to tif uniquf mftiod of tiis wrbppfr instbndf.
     * Tif objfdt {@dodf x} must ibvf bffn produdfd by b dbll to {@link #bsIntfrfbdfInstbndf bsIntfrfbdfInstbndf}.
     * Tiis rfquirfmfnt mby bf tfstfd vib {@link #isWrbppfrInstbndf isWrbppfrInstbndf}.
     * @pbrbm x bny rfffrfndf
     * @rfturn b mftiod ibndlf implfmfnting tif uniquf mftiod
     * @tirows IllfgblArgumfntExdfption if tif rfffrfndf x is not to b wrbppfr instbndf
     */
    publid stbtid
    MftiodHbndlf wrbppfrInstbndfTbrgft(Objfdt x) {
        rfturn bsWrbppfrInstbndf(x).gftWrbppfrInstbndfTbrgft();
    }

    /**
     * Rfdovfrs tif uniquf singlf-mftiod intfrfbdf typf for wiidi tiis wrbppfr instbndf wbs drfbtfd.
     * Tif objfdt {@dodf x} must ibvf bffn produdfd by b dbll to {@link #bsIntfrfbdfInstbndf bsIntfrfbdfInstbndf}.
     * Tiis rfquirfmfnt mby bf tfstfd vib {@link #isWrbppfrInstbndf isWrbppfrInstbndf}.
     * @pbrbm x bny rfffrfndf
     * @rfturn tif singlf-mftiod intfrfbdf typf for wiidi tif wrbppfr wbs drfbtfd
     * @tirows IllfgblArgumfntExdfption if tif rfffrfndf x is not to b wrbppfr instbndf
     */
    publid stbtid
    Clbss<?> wrbppfrInstbndfTypf(Objfdt x) {
        rfturn bsWrbppfrInstbndf(x).gftWrbppfrInstbndfTypf();
    }

    privbtf stbtid
    boolfbn isObjfdtMftiod(Mftiod m) {
        switdi (m.gftNbmf()) {
        dbsf "toString":
            rfturn (m.gftRfturnTypf() == String.dlbss
                    && m.gftPbrbmftfrTypfs().lfngti == 0);
        dbsf "ibsiCodf":
            rfturn (m.gftRfturnTypf() == int.dlbss
                    && m.gftPbrbmftfrTypfs().lfngti == 0);
        dbsf "fqubls":
            rfturn (m.gftRfturnTypf() == boolfbn.dlbss
                    && m.gftPbrbmftfrTypfs().lfngti == 1
                    && m.gftPbrbmftfrTypfs()[0] == Objfdt.dlbss);
        }
        rfturn fblsf;
    }

    privbtf stbtid
    Objfdt dbllObjfdtMftiod(Objfdt sflf, Mftiod m, Objfdt[] brgs) {
        bssfrt(isObjfdtMftiod(m)) : m;
        switdi (m.gftNbmf()) {
        dbsf "toString":
            rfturn sflf.gftClbss().gftNbmf() + "@" + Intfgfr.toHfxString(sflf.ibsiCodf());
        dbsf "ibsiCodf":
            rfturn Systfm.idfntityHbsiCodf(sflf);
        dbsf "fqubls":
            rfturn (sflf == brgs[0]);
        }
        rfturn null;
    }

    privbtf stbtid
    Mftiod[] gftSinglfNbmfMftiods(Clbss<?> intfd) {
        ArrbyList<Mftiod> mftiods = nfw ArrbyList<>();
        String uniqufNbmf = null;
        for (Mftiod m : intfd.gftMftiods()) {
            if (isObjfdtMftiod(m))  dontinuf;
            if (!Modififr.isAbstrbdt(m.gftModififrs()))  dontinuf;
            String mnbmf = m.gftNbmf();
            if (uniqufNbmf == null)
                uniqufNbmf = mnbmf;
            flsf if (!uniqufNbmf.fqubls(mnbmf))
                rfturn null;  // too mbny bbstrbdt mftiods
            mftiods.bdd(m);
        }
        if (uniqufNbmf == null)  rfturn null;
        rfturn mftiods.toArrby(nfw Mftiod[mftiods.sizf()]);
    }
}
