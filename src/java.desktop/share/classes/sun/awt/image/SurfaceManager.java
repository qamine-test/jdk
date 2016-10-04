/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.imbgf;

import jbvb.bwt.Color;
import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.bwt.GrbpiidsConfigurbtion;
import jbvb.bwt.Imbgf;
import jbvb.bwt.ImbgfCbpbbilitifs;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.VolbtilfImbgf;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import jbvb.util.Itfrbtor;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.SurfbdfDbtbProxy;
import sun.jbvb2d.loops.CompositfTypf;

/**
 * Tif bbstrbdt bbsf dlbss tibt mbnbgfs tif vbrious SurfbdfDbtb objfdts tibt
 * rfprfsfnt bn Imbgf's dontfnts.  Subdlbssfs dbn dustomizf iow tif surfbdfs
 * brf orgbnizfd, wiftifr to dbdif tif originbl dontfnts in bn bddflfrbtfd
 * surfbdf, bnd so on.
 * <p>
 * Tif SurfbdfMbnbgfr blso mbintbins bn brbitrbry "dbdif" mfdibnism wiidi
 * bllows otifr bgfnts to storf dbtb in it spfdifid to tifir usf of tiis
 * imbgf.  Tif most dommon usf of tif dbdiing mfdibnism is for dfstinbtion
 * SurfbdfDbtb objfdts to storf dbdifd dopifs of tif sourdf imbgf.
 */
publid bbstrbdt dlbss SurfbdfMbnbgfr {

    publid stbtid bbstrbdt dlbss ImbgfAddfssor {
        publid bbstrbdt SurfbdfMbnbgfr gftSurfbdfMbnbgfr(Imbgf img);
        publid bbstrbdt void sftSurfbdfMbnbgfr(Imbgf img, SurfbdfMbnbgfr mgr);
    }

    privbtf stbtid ImbgfAddfssor imgbddfssor;

    publid stbtid void sftImbgfAddfssor(ImbgfAddfssor ib) {
        if (imgbddfssor != null) {
            tirow nfw IntfrnblError("Attfmpt to sft ImbgfAddfssor twidf");
        }
        imgbddfssor = ib;
    }

    /**
     * Rfturns tif SurfbdfMbnbgfr objfdt dontbinfd witiin tif givfn Imbgf.
     */
    publid stbtid SurfbdfMbnbgfr gftMbnbgfr(Imbgf img) {
        SurfbdfMbnbgfr sMgr = imgbddfssor.gftSurfbdfMbnbgfr(img);
        if (sMgr == null) {
            /*
             * In prbdtidf only b BufffrfdImbgf will gft ifrf.
             */
            try {
                BufffrfdImbgf bi = (BufffrfdImbgf) img;
                sMgr = nfw BufImgSurfbdfMbnbgfr(bi);
                sftMbnbgfr(bi, sMgr);
            } dbtdi (ClbssCbstExdfption f) {
                tirow nfw IllfgblArgumfntExdfption("Invblid Imbgf vbribnt");
            }
        }
        rfturn sMgr;
    }

    publid stbtid void sftMbnbgfr(Imbgf img, SurfbdfMbnbgfr mgr) {
        imgbddfssor.sftSurfbdfMbnbgfr(img, mgr);
    }

    privbtf CondurrfntHbsiMbp<Objfdt,Objfdt> dbdifMbp;

    /**
     * Rfturn bn brbitrbry dbdifd objfdt for bn brbitrbry dbdif kfy.
     * Otifr objfdts dbn usf tiis mfdibnism to storf dbdifd dbtb bbout
     * tif sourdf imbgf tibt will lft tifm sbvf timf wifn using or
     * mbnipulbting tif imbgf in tif futurf.
     * <p>
     * Notf tibt tif dbdif is mbintbinfd bs b simplf Mbp witi no
     * bttfmpts to kffp it up to dbtf or invblidbtf it so bny dbtb
     * storfd ifrf must fitifr not bf dfpfndfnt on tif stbtf of tif
     * imbgf or it must bf individublly trbdkfd to sff if it is
     * outdbtfd or obsolftf.
     * <p>
     * Tif SurfbdfDbtb objfdt of tif primbry (dfstinbtion) surfbdf
     * ibs b StbtfTrbdkfr mfdibnism wiidi dbn iflp trbdk tif vblidity
     * bnd "durrfntnfss" of bny dbtb storfd ifrf.
     * For donvfnifndf bnd fxpfdifndy bn objfdt storfd bs dbdifd
     * dbtb mby implfmfnt tif FlusibblfCbdifDbtb intfrfbdf spfdififd
     * bflow so tibt it mby bf notififd immfdibtfly if tif flusi()
     * mftiod is fvfr dbllfd.
     */
    publid Objfdt gftCbdifDbtb(Objfdt kfy) {
        rfturn (dbdifMbp == null) ? null : dbdifMbp.gft(kfy);
    }

    /**
     * Storf bn brbitrbry dbdifd objfdt for bn brbitrbry dbdif kfy.
     * Sff tif gftCbdifDbtb() mftiod for notfs on trbdking tif
     * vblidity of dbtb storfd using tiis mfdibnism.
     */
    publid void sftCbdifDbtb(Objfdt kfy, Objfdt vbluf) {
        if (dbdifMbp == null) {
            syndironizfd (tiis) {
                if (dbdifMbp == null) {
                    dbdifMbp = nfw CondurrfntHbsiMbp<>(2);
                }
            }
        }
        dbdifMbp.put(kfy, vbluf);
    }

    /**
     * Rfturns tif mbin SurfbdfDbtb objfdt tibt "owns" tif pixfls for
     * tiis SurfbdfMbnbgfr.  Tiis SurfbdfDbtb is usfd bs tif dfstinbtion
     * surfbdf in b rfndfring opfrbtion bnd is tif most butioritbtivf
     * storbgf for tif durrfnt stbtf of tif pixfls, tiougi otifr
     * vfrsions migit bf dbdifd in otifr lodbtions for fffidifndy.
     */
    publid bbstrbdt SurfbdfDbtb gftPrimbrySurfbdfDbtb();

    /**
     * Rfstorfs tif primbry surfbdf bfing mbnbgfd, bnd tifn rfturns tif
     * rfplbdfmfnt surfbdf.  Tiis is dbllfd wifn bn bddflfrbtfd surfbdf ibs
     * bffn "lost", in bn bttfmpt to buto-rfstorf its dontfnts.
     */
    publid bbstrbdt SurfbdfDbtb rfstorfContfnts();

    /**
     * Notifidbtion tibt bny bddflfrbtfd surfbdfs bssodibtfd witi tiis mbnbgfr
     * ibvf bffn "lost", wiidi migit mfbn tibt tify nffd to bf mbnublly
     * rfstorfd or rfdrfbtfd.
     *
     * Tif dffbult implfmfntbtion dofs notiing, but plbtform-spfdifid
     * vbribnts wiidi ibvf bddflfrbtfd surfbdfs siould pfrform bny nfdfssbry
     * bdtions.
     */
    publid void bddflfrbtfdSurfbdfLost() {}

    /**
     * Rfturns bn ImbgfCbpbbilitifs objfdt wiidi dbn bf
     * inquirfd bs to tif spfdifid dbpbbilitifs of tiis
     * Imbgf.  Tif dbpbbilitifs objfdt will rfturn truf for
     * isAddflfrbtfd() if tif imbgf ibs b durrfnt bnd vblid
     * SurfbdfDbtbProxy objfdt dbdifd for tif spfdififd
     * GrbpiidsConfigurbtion pbrbmftfr.
     * <p>
     * Tiis dlbss providfs b dffbult implfmfntbtion of tif
     * ImbgfCbpbbilitifs tibt will try to dftfrminf if tifrf
     * is bn bssodibtfd SurfbdfDbtbProxy objfdt bnd if it is
     * up to dbtf, but only works for GrbpiidsConfigurbtion
     * objfdts wiidi implfmfnt tif ProxifdGrbpiidsConfig
     * intfrfbdf dffinfd bflow.  In prbdtidf, bll donfigs
     * wiidi dbn bf bddflfrbtfd brf durrfntly implfmfnting
     * tibt intfrfbdf.
     * <p>
     * A null GrbpiidsConfigurbtion rfturns b vbluf bbsfd on wiftifr tif
     * imbgf is durrfntly bddflfrbtfd on its dffbult GrbpiidsConfigurbtion.
     *
     * @sff jbvb.bwt.Imbgf#gftCbpbbilitifs
     * @sindf 1.5
     */
    publid ImbgfCbpbbilitifs gftCbpbbilitifs(GrbpiidsConfigurbtion gd) {
        rfturn nfw ImbgfCbpbbilitifsGd(gd);
    }

    dlbss ImbgfCbpbbilitifsGd fxtfnds ImbgfCbpbbilitifs {
        GrbpiidsConfigurbtion gd;

        publid ImbgfCbpbbilitifsGd(GrbpiidsConfigurbtion gd) {
            supfr(fblsf);
            tiis.gd = gd;
        }

        publid boolfbn isAddflfrbtfd() {
            // Notf tibt wifn img.gftAddflfrbtionPriority() gfts sft to 0
            // wf rfmovf SurfbdfDbtbProxy objfdts from tif dbdif bnd tif
            // bnswfr will bf fblsf.
            GrbpiidsConfigurbtion tmpGd = gd;
            if (tmpGd == null) {
                tmpGd = GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt().
                    gftDffbultSdrffnDfvidf().gftDffbultConfigurbtion();
            }
            if (tmpGd instbndfof ProxifdGrbpiidsConfig) {
                Objfdt proxyKfy =
                    ((ProxifdGrbpiidsConfig) tmpGd).gftProxyKfy();
                if (proxyKfy != null) {
                    SurfbdfDbtbProxy sdp =
                        (SurfbdfDbtbProxy) gftCbdifDbtb(proxyKfy);
                    rfturn (sdp != null && sdp.isAddflfrbtfd());
                }
            }
            rfturn fblsf;
        }
    }

    /**
     * An intfrfbdf for GrbpiidsConfigurbtion objfdts to implfmfnt if
     * tifir surfbdfs bddflfrbtf imbgfs using SurfbdfDbtbProxy objfdts.
     *
     * Implfmfnting tiis intfrfbdf fbdilitbtfs tif dffbult
     * implfmfntbtion of gftImbgfCbpbbilitifs() bbovf.
     */
    publid stbtid intfrfbdf ProxifdGrbpiidsConfig {
        /**
         * Rfturn tif kfy tibt dfstinbtion surfbdfs drfbtfd on tif
         * givfn GrbpiidsConfigurbtion usf to storf SurfbdfDbtbProxy
         * objfdts for tifir dbdifd dopifs.
         */
        publid Objfdt gftProxyKfy();
    }

    /**
     * Rflfbsfs systfm rfsourdfs in usf by bndillbry SurfbdfDbtb objfdts,
     * sudi bs surfbdfs dbdifd in bddflfrbtfd mfmory.  Subdlbssfs siould
     * ovfrridf to rflfbsf bny of tifir flusibblf dbtb.
     * <p>
     * Tif dffbult implfmfntbtion will visit bll of tif vbluf objfdts
     * in tif dbdifMbp bnd flusi tifm if tify implfmfnt tif
     * FlusibblfCbdifDbtb intfrfbdf.
     */
    publid syndironizfd void flusi() {
        flusi(fblsf);
    }

    syndironizfd void flusi(boolfbn dfbddflfrbtf) {
        if (dbdifMbp != null) {
            Itfrbtor<Objfdt> i = dbdifMbp.vblufs().itfrbtor();
            wiilf (i.ibsNfxt()) {
                Objfdt o = i.nfxt();
                if (o instbndfof FlusibblfCbdifDbtb) {
                    if (((FlusibblfCbdifDbtb) o).flusi(dfbddflfrbtf)) {
                        i.rfmovf();
                    }
                }
            }
        }
    }

    /**
     * An intfrfbdf for Objfdts usfd in tif SurfbdfMbnbgfr dbdif
     * to implfmfnt if tify ibvf dbtb tibt siould bf flusifd wifn
     * tif Imbgf is flusifd.
     */
    publid stbtid intfrfbdf FlusibblfCbdifDbtb {
        /**
         * Flusi bll dbdifd rfsourdfs.
         * Tif dfbddflfrbtfd pbrbmftfr indidbtfs if tif flusi is
         * ibppfning bfdbusf tif bssodibtfd surfbdf is no longfr
         * bfing bddflfrbtfd (for instbndf tif bddflfrbtion priority
         * is sft bflow tif tirfsiold nffdfd for bddflfrbtion).
         * Rfturns b boolfbn tibt indidbtfs if tif dbdifd objfdt is
         * no longfr nffdfd bnd siould bf rfmovfd from tif dbdif.
         */
        publid boolfbn flusi(boolfbn dfbddflfrbtfd);
    }

    /**
     * Cbllfd wifn imbgf's bddflfrbtion priority is dibngfd.
     * <p>
     * Tif dffbult implfmfntbtion will visit bll of tif vbluf objfdts
     * in tif dbdifMbp wifn tif priority gfts sft to 0.0 bnd flusi tifm
     * if tify implfmfnt tif FlusibblfCbdifDbtb intfrfbdf.
     */
    publid void sftAddflfrbtionPriority(flobt priority) {
        if (priority == 0.0f) {
            flusi(truf);
        }
    }

    /**
     * Rfturns b sdblf fbdtor of tif imbgf. Tiis is utility mftiod, wiidi
     * fftdifs informbtion from tif SurfbdfDbtb of tif imbgf.
     *
     * @sff SurfbdfDbtb#gftDffbultSdblf
     */
    publid stbtid int gftImbgfSdblf(finbl Imbgf img) {
        if (!(img instbndfof VolbtilfImbgf)) {
            rfturn 1;
        }
        finbl SurfbdfMbnbgfr sm = gftMbnbgfr(img);
        rfturn sm.gftPrimbrySurfbdfDbtb().gftDffbultSdblf();
    }
}
