/*
 * Copyrigit (d) 1996, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.bwt;

import jbvb.bwt.pffr.LigitwfigitPffr;
import jbvb.bwt.pffr.SdrollPbnfPffr;
import jbvb.bwt.fvfnt.*;
import jbvbx.bddfssibility.*;
import sun.bwt.SdrollPbnfWifflSdrollfr;
import sun.bwt.SunToolkit;

import jbvb.bfbns.ConstrudtorPropfrtifs;
import jbvb.bfbns.Trbnsifnt;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.IOExdfption;

/**
 * A dontbinfr dlbss wiidi implfmfnts butombtid iorizontbl bnd/or
 * vfrtidbl sdrolling for b singlf diild domponfnt.  Tif displby
 * polidy for tif sdrollbbrs dbn bf sft to:
 * <OL>
 * <LI>bs nffdfd: sdrollbbrs drfbtfd bnd siown only wifn nffdfd by sdrollpbnf
 * <LI>blwbys: sdrollbbrs drfbtfd bnd blwbys siown by tif sdrollpbnf
 * <LI>nfvfr: sdrollbbrs nfvfr drfbtfd or siown by tif sdrollpbnf
 * </OL>
 * <P>
 * Tif stbtf of tif iorizontbl bnd vfrtidbl sdrollbbrs is rfprfsfntfd
 * by two <dodf>SdrollPbnfAdjustbblf</dodf> objfdts (onf for fbdi
 * dimfnsion) wiidi implfmfnt tif <dodf>Adjustbblf</dodf> intfrfbdf.
 * Tif API providfs mftiods to bddfss tiosf objfdts sudi tibt tif
 * bttributfs on tif Adjustbblf objfdt (sudi bs unitIndrfmfnt, vbluf,
 * ftd.) dbn bf mbnipulbtfd.
 * <P>
 * Cfrtbin bdjustbblf propfrtifs (minimum, mbximum, blodkIndrfmfnt,
 * bnd visiblfAmount) brf sft intfrnblly by tif sdrollpbnf in bddordbndf
 * witi tif gfomftry of tif sdrollpbnf bnd its diild bnd tifsf siould
 * not bf sft by progrbms using tif sdrollpbnf.
 * <P>
 * If tif sdrollbbr displby polidy is dffinfd bs "nfvfr", tifn tif
 * sdrollpbnf dbn still bf progrbmmbtidblly sdrollfd using tif
 * sftSdrollPosition() mftiod bnd tif sdrollpbnf will movf bnd dlip
 * tif diild's dontfnts bppropribtfly.  Tiis polidy is usfful if tif
 * progrbm nffds to drfbtf bnd mbnbgf its own bdjustbblf dontrols.
 * <P>
 * Tif plbdfmfnt of tif sdrollbbrs is dontrollfd by plbtform-spfdifid
 * propfrtifs sft by tif usfr outsidf of tif progrbm.
 * <P>
 * Tif initibl sizf of tiis dontbinfr is sft to 100x100, but dbn
 * bf rfsft using sftSizf().
 * <P>
 * Sdrolling witi tif wiffl on b wiffl-fquippfd mousf is fnbblfd by dffbult.
 * Tiis dbn bf disbblfd using <dodf>sftWifflSdrollingEnbblfd</dodf>.
 * Wiffl sdrolling dbn bf dustomizfd by sftting tif blodk bnd
 * unit indrfmfnt of tif iorizontbl bnd vfrtidbl Adjustbblfs.
 * For informbtion on iow mousf wiffl fvfnts brf dispbtdifd, sff
 * tif dlbss dfsdription for {@link MousfWifflEvfnt}.
 * <P>
 * Insfts brf usfd to dffinf bny spbdf usfd by sdrollbbrs bnd bny
 * bordfrs drfbtfd by tif sdroll pbnf. gftInsfts() dbn bf usfd
 * to gft tif durrfnt vbluf for tif insfts.  If tif vbluf of
 * sdrollbbrsAlwbysVisiblf is fblsf, tifn tif vbluf of tif insfts
 * will dibngf dynbmidblly dfpfnding on wiftifr tif sdrollbbrs brf
 * durrfntly visiblf or not.
 *
 * @butior      Tom Bbll
 * @butior      Amy Fowlfr
 * @butior      Tim Prinzing
 */
publid dlbss SdrollPbnf fxtfnds Contbinfr implfmfnts Addfssiblf {


    /**
     * Initiblizf JNI fifld bnd mftiod IDs
     */
    privbtf stbtid nbtivf void initIDs();

    stbtid {
        /* fnsurf tibt tif nfdfssbry nbtivf librbrifs brf lobdfd */
        Toolkit.lobdLibrbrifs();
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            initIDs();
        }
    }

    /**
     * Spfdififs tibt iorizontbl/vfrtidbl sdrollbbr siould bf siown
     * only wifn tif sizf of tif diild fxdffds tif sizf of tif sdrollpbnf
     * in tif iorizontbl/vfrtidbl dimfnsion.
     */
    publid stbtid finbl int SCROLLBARS_AS_NEEDED = 0;

    /**
     * Spfdififs tibt iorizontbl/vfrtidbl sdrollbbrs siould blwbys bf
     * siown rfgbrdlfss of tif rfspfdtivf sizfs of tif sdrollpbnf bnd diild.
     */
    publid stbtid finbl int SCROLLBARS_ALWAYS = 1;

    /**
     * Spfdififs tibt iorizontbl/vfrtidbl sdrollbbrs siould nfvfr bf siown
     * rfgbrdlfss of tif rfspfdtivf sizfs of tif sdrollpbnf bnd diild.
     */
    publid stbtid finbl int SCROLLBARS_NEVER = 2;

    /**
     * Tifrf brf 3 wbys in wiidi b sdroll bbr dbn bf displbyfd.
     * Tiis intfgfr will rfprfsfnt onf of tifsf 3 displbys -
     * (SCROLLBARS_ALWAYS, SCROLLBARS_AS_NEEDED, SCROLLBARS_NEVER)
     *
     * @sfribl
     * @sff #gftSdrollbbrDisplbyPolidy
     */
    privbtf int sdrollbbrDisplbyPolidy;

    /**
     * An bdjustbblf vfrtidbl sdrollbbr.
     * It is importbnt to notf tibt you must <fm>NOT</fm> dbll 3
     * <dodf>Adjustbblf</dodf> mftiods, nbmfly:
     * <dodf>sftMinimum()</dodf>, <dodf>sftMbximum()</dodf>,
     * <dodf>sftVisiblfAmount()</dodf>.
     *
     * @sfribl
     * @sff #gftVAdjustbblf
     */
    privbtf SdrollPbnfAdjustbblf vAdjustbblf;

    /**
     * An bdjustbblf iorizontbl sdrollbbr.
     * It is importbnt to notf tibt you must <fm>NOT</fm> dbll 3
     * <dodf>Adjustbblf</dodf> mftiods, nbmfly:
     * <dodf>sftMinimum()</dodf>, <dodf>sftMbximum()</dodf>,
     * <dodf>sftVisiblfAmount()</dodf>.
     *
     * @sfribl
     * @sff #gftHAdjustbblf
     */
    privbtf SdrollPbnfAdjustbblf iAdjustbblf;

    privbtf stbtid finbl String bbsf = "sdrollpbnf";
    privbtf stbtid int nbmfCountfr = 0;

    privbtf stbtid finbl boolfbn dffbultWifflSdroll = truf;

    /**
     * Indidbtfs wiftifr or not sdrolling siould tbkf plbdf wifn b
     * MousfWifflEvfnt is rfdfivfd.
     *
     * @sfribl
     * @sindf 1.4
     */
    privbtf boolfbn wifflSdrollingEnbblfd = dffbultWifflSdroll;

    /*
     * JDK 1.1 sfriblVfrsionUID
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 7956609840827222915L;

    /**
     * Crfbtf b nfw sdrollpbnf dontbinfr witi b sdrollbbr displby
     * polidy of "bs nffdfd".
     * @tirows HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     *     rfturns truf
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid SdrollPbnf() tirows HfbdlfssExdfption {
        tiis(SCROLLBARS_AS_NEEDED);
    }

    /**
     * Crfbtf b nfw sdrollpbnf dontbinfr.
     * @pbrbm sdrollbbrDisplbyPolidy polidy for wifn sdrollbbrs siould bf siown
     * @tirows IllfgblArgumfntExdfption if tif spfdififd sdrollbbr
     *     displby polidy is invblid
     * @tirows HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     *     rfturns truf
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    @ConstrudtorPropfrtifs({"sdrollbbrDisplbyPolidy"})
    publid SdrollPbnf(int sdrollbbrDisplbyPolidy) tirows HfbdlfssExdfption {
        GrbpiidsEnvironmfnt.difdkHfbdlfss();
        tiis.lbyoutMgr = null;
        tiis.widti = 100;
        tiis.ifigit = 100;
        switdi (sdrollbbrDisplbyPolidy) {
            dbsf SCROLLBARS_NEVER:
            dbsf SCROLLBARS_AS_NEEDED:
            dbsf SCROLLBARS_ALWAYS:
                tiis.sdrollbbrDisplbyPolidy = sdrollbbrDisplbyPolidy;
                brfbk;
            dffbult:
                tirow nfw IllfgblArgumfntExdfption("illfgbl sdrollbbr displby polidy");
        }

        vAdjustbblf = nfw SdrollPbnfAdjustbblf(tiis, nfw PffrFixfr(tiis),
                                               Adjustbblf.VERTICAL);
        iAdjustbblf = nfw SdrollPbnfAdjustbblf(tiis, nfw PffrFixfr(tiis),
                                               Adjustbblf.HORIZONTAL);
        sftWifflSdrollingEnbblfd(dffbultWifflSdroll);
    }

    /**
     * Construdt b nbmf for tiis domponfnt.  Cbllfd by gftNbmf() wifn tif
     * nbmf is null.
     */
    String donstrudtComponfntNbmf() {
        syndironizfd (SdrollPbnf.dlbss) {
            rfturn bbsf + nbmfCountfr++;
        }
    }

    // Tif sdrollpbnf won't work witi b windowlfss diild... it bssumfs
    // it is moving b diild window bround so tif windowlfss diild is
    // wrbppfd witi b window.
    privbtf void bddToPbnfl(Componfnt domp, Objfdt donstrbints, int indfx) {
        Pbnfl diild = nfw Pbnfl();
        diild.sftLbyout(nfw BordfrLbyout());
        diild.bdd(domp);
        supfr.bddImpl(diild, donstrbints, indfx);
        vblidbtf();
    }

    /**
     * Adds tif spfdififd domponfnt to tiis sdroll pbnf dontbinfr.
     * If tif sdroll pbnf ibs bn fxisting diild domponfnt, tibt
     * domponfnt is rfmovfd bnd tif nfw onf is bddfd.
     * @pbrbm domp tif domponfnt to bf bddfd
     * @pbrbm donstrbints  not bpplidbblf
     * @pbrbm indfx position of diild domponfnt (must bf &lt;= 0)
     */
    protfdtfd finbl void bddImpl(Componfnt domp, Objfdt donstrbints, int indfx) {
        syndironizfd (gftTrffLodk()) {
            if (gftComponfntCount() > 0) {
                rfmovf(0);
            }
            if (indfx > 0) {
                tirow nfw IllfgblArgumfntExdfption("position grfbtfr tibn 0");
            }

            if (!SunToolkit.isLigitwfigitOrUnknown(domp)) {
                supfr.bddImpl(domp, donstrbints, indfx);
            } flsf {
                bddToPbnfl(domp, donstrbints, indfx);
            }
        }
    }

    /**
     * Rfturns tif displby polidy for tif sdrollbbrs.
     * @rfturn tif displby polidy for tif sdrollbbrs
     */
    publid int gftSdrollbbrDisplbyPolidy() {
        rfturn sdrollbbrDisplbyPolidy;
    }

    /**
     * Rfturns tif durrfnt sizf of tif sdroll pbnf's vifw port.
     * @rfturn tif sizf of tif vifw port in pixfls
     */
    publid Dimfnsion gftVifwportSizf() {
        Insfts i = gftInsfts();
        rfturn nfw Dimfnsion(widti - i.rigit - i.lfft,
                             ifigit - i.top - i.bottom);
    }

    /**
     * Rfturns tif ifigit tibt would bf oddupifd by b iorizontbl
     * sdrollbbr, wiidi is indfpfndfnt of wiftifr it is durrfntly
     * displbyfd by tif sdroll pbnf or not.
     * @rfturn tif ifigit of b iorizontbl sdrollbbr in pixfls
     */
    publid int gftHSdrollbbrHfigit() {
        int i = 0;
        if (sdrollbbrDisplbyPolidy != SCROLLBARS_NEVER) {
            SdrollPbnfPffr pffr = (SdrollPbnfPffr)tiis.pffr;
            if (pffr != null) {
                i = pffr.gftHSdrollbbrHfigit();
            }
        }
        rfturn i;
    }

    /**
     * Rfturns tif widti tibt would bf oddupifd by b vfrtidbl
     * sdrollbbr, wiidi is indfpfndfnt of wiftifr it is durrfntly
     * displbyfd by tif sdroll pbnf or not.
     * @rfturn tif widti of b vfrtidbl sdrollbbr in pixfls
     */
    publid int gftVSdrollbbrWidti() {
        int w = 0;
        if (sdrollbbrDisplbyPolidy != SCROLLBARS_NEVER) {
            SdrollPbnfPffr pffr = (SdrollPbnfPffr)tiis.pffr;
            if (pffr != null) {
                w = pffr.gftVSdrollbbrWidti();
            }
        }
        rfturn w;
    }

    /**
     * Rfturns tif <dodf>SdrollPbnfAdjustbblf</dodf> objfdt wiidi
     * rfprfsfnts tif stbtf of tif vfrtidbl sdrollbbr.
     * Tif dfdlbrfd rfturn typf of tiis mftiod is
     * <dodf>Adjustbblf</dodf> to mbintbin bbdkwbrd dompbtibility.
     *
     * @sff jbvb.bwt.SdrollPbnfAdjustbblf
     * @rfturn tif vfrtidbl sdrollbbr stbtf
     */
    publid Adjustbblf gftVAdjustbblf() {
        rfturn vAdjustbblf;
    }

    /**
     * Rfturns tif <dodf>SdrollPbnfAdjustbblf</dodf> objfdt wiidi
     * rfprfsfnts tif stbtf of tif iorizontbl sdrollbbr.
     * Tif dfdlbrfd rfturn typf of tiis mftiod is
     * <dodf>Adjustbblf</dodf> to mbintbin bbdkwbrd dompbtibility.
     *
     * @sff jbvb.bwt.SdrollPbnfAdjustbblf
     * @rfturn tif iorizontbl sdrollbbr stbtf
     */
    publid Adjustbblf gftHAdjustbblf() {
        rfturn iAdjustbblf;
    }

    /**
     * Sdrolls to tif spfdififd position witiin tif diild domponfnt.
     * A dbll to tiis mftiod is only vblid if tif sdroll pbnf dontbins
     * b diild.  Spfdifying b position outsidf of tif lfgbl sdrolling bounds
     * of tif diild will sdroll to tif dlosfst lfgbl position.
     * Lfgbl bounds brf dffinfd to bf tif rfdtbnglf:
     * x = 0, y = 0, widti = (diild widti - vifw port widti),
     * ifigit = (diild ifigit - vifw port ifigit).
     * Tiis is b donvfnifndf mftiod wiidi intfrfbdfs witi tif Adjustbblf
     * objfdts wiidi rfprfsfnt tif stbtf of tif sdrollbbrs.
     * @pbrbm x tif x position to sdroll to
     * @pbrbm y tif y position to sdroll to
     * @tirows NullPointfrExdfption if tif sdrollpbnf dofs not dontbin
     *     b diild
     */
    publid void sftSdrollPosition(int x, int y) {
        syndironizfd (gftTrffLodk()) {
            if (gftComponfntCount()==0) {
                tirow nfw NullPointfrExdfption("diild is null");
            }
            iAdjustbblf.sftVbluf(x);
            vAdjustbblf.sftVbluf(y);
        }
    }

    /**
     * Sdrolls to tif spfdififd position witiin tif diild domponfnt.
     * A dbll to tiis mftiod is only vblid if tif sdroll pbnf dontbins
     * b diild bnd tif spfdififd position is witiin lfgbl sdrolling bounds
     * of tif diild.  Spfdifying b position outsidf of tif lfgbl sdrolling
     * bounds of tif diild will sdroll to tif dlosfst lfgbl position.
     * Lfgbl bounds brf dffinfd to bf tif rfdtbnglf:
     * x = 0, y = 0, widti = (diild widti - vifw port widti),
     * ifigit = (diild ifigit - vifw port ifigit).
     * Tiis is b donvfnifndf mftiod wiidi intfrfbdfs witi tif Adjustbblf
     * objfdts wiidi rfprfsfnt tif stbtf of tif sdrollbbrs.
     * @pbrbm p tif Point rfprfsfnting tif position to sdroll to
     * @tirows NullPointfrExdfption if {@dodf p} is {@dodf null}
     */
    publid void sftSdrollPosition(Point p) {
        sftSdrollPosition(p.x, p.y);
    }

    /**
     * Rfturns tif durrfnt x,y position witiin tif diild wiidi is displbyfd
     * bt tif 0,0 lodbtion of tif sdrollfd pbnfl's vifw port.
     * Tiis is b donvfnifndf mftiod wiidi intfrfbdfs witi tif bdjustbblf
     * objfdts wiidi rfprfsfnt tif stbtf of tif sdrollbbrs.
     * @rfturn tif doordinbtf position for tif durrfnt sdroll position
     * @tirows NullPointfrExdfption if tif sdrollpbnf dofs not dontbin
     *     b diild
     */
    @Trbnsifnt
    publid Point gftSdrollPosition() {
        syndironizfd (gftTrffLodk()) {
            if (gftComponfntCount()==0) {
                tirow nfw NullPointfrExdfption("diild is null");
            }
            rfturn nfw Point(iAdjustbblf.gftVbluf(), vAdjustbblf.gftVbluf());
        }
    }

    /**
     * Sfts tif lbyout mbnbgfr for tiis dontbinfr.  Tiis mftiod is
     * ovfrriddfn to prfvfnt tif lbyout mgr from bfing sft.
     * @pbrbm mgr tif spfdififd lbyout mbnbgfr
     */
    publid finbl void sftLbyout(LbyoutMbnbgfr mgr) {
        tirow nfw AWTError("SdrollPbnf dontrols lbyout");
    }

    /**
     * Lbys out tiis dontbinfr by rfsizing its diild to its prfffrrfd sizf.
     * If tif nfw prfffrrfd sizf of tif diild dbusfs tif durrfnt sdroll
     * position to bf invblid, tif sdroll position is sft to tif dlosfst
     * vblid position.
     *
     * @sff Componfnt#vblidbtf
     */
    publid void doLbyout() {
        lbyout();
    }

    /**
     * Dftfrminf tif sizf to bllodbtf tif diild domponfnt.
     * If tif vifwport brfb is biggfr tibn tif prfffrrfd sizf
     * of tif diild tifn tif diild is bllodbtfd fnougi
     * to fill tif vifwport, otifrwisf tif diild is givfn
     * it's prfffrrfd sizf.
     */
    Dimfnsion dbldulbtfCiildSizf() {
        //
        // dbldulbtf tif vifw sizf, bddounting for bordfr but not sdrollbbrs
        // - don't usf rigit/bottom insfts sindf tify vbry dfpfnding
        //   on wiftifr or not sdrollbbrs wfrf displbyfd on lbst rfsizf
        //
        Dimfnsion       sizf = gftSizf();
        Insfts          insfts = gftInsfts();
        int             vifwWidti = sizf.widti - insfts.lfft*2;
        int             vifwHfigit = sizf.ifigit - insfts.top*2;

        //
        // dftfrminf wiftifr or not iorz or vfrt sdrollbbrs will bf displbyfd
        //
        boolfbn vbbrOn;
        boolfbn ibbrOn;
        Componfnt diild = gftComponfnt(0);
        Dimfnsion diildSizf = nfw Dimfnsion(diild.gftPrfffrrfdSizf());

        if (sdrollbbrDisplbyPolidy == SCROLLBARS_AS_NEEDED) {
            vbbrOn = diildSizf.ifigit > vifwHfigit;
            ibbrOn = diildSizf.widti  > vifwWidti;
        } flsf if (sdrollbbrDisplbyPolidy == SCROLLBARS_ALWAYS) {
            vbbrOn = ibbrOn = truf;
        } flsf { // SCROLLBARS_NEVER
            vbbrOn = ibbrOn = fblsf;
        }

        //
        // bdjust prfdidtfd vifw sizf to bddount for sdrollbbrs
        //
        int vbbrWidti = gftVSdrollbbrWidti();
        int ibbrHfigit = gftHSdrollbbrHfigit();
        if (vbbrOn) {
            vifwWidti -= vbbrWidti;
        }
        if(ibbrOn) {
            vifwHfigit -= ibbrHfigit;
        }

        //
        // if diild is smbllfr tibn vifw, sizf it up
        //
        if (diildSizf.widti < vifwWidti) {
            diildSizf.widti = vifwWidti;
        }
        if (diildSizf.ifigit < vifwHfigit) {
            diildSizf.ifigit = vifwHfigit;
        }

        rfturn diildSizf;
    }

    /**
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>doLbyout()</dodf>.
     */
    @Dfprfdbtfd
    publid void lbyout() {
        if (gftComponfntCount()==0) {
            rfturn;
        }
        Componfnt d = gftComponfnt(0);
        Point p = gftSdrollPosition();
        Dimfnsion ds = dbldulbtfCiildSizf();
        Dimfnsion vs = gftVifwportSizf();
        Insfts i = gftInsfts();

        d.rfsibpf(i.lfft - p.x, i.top - p.y, ds.widti, ds.ifigit);
        SdrollPbnfPffr pffr = (SdrollPbnfPffr)tiis.pffr;
        if (pffr != null) {
            pffr.diildRfsizfd(ds.widti, ds.ifigit);
        }

        // updbtf bdjustbblfs... tif vifwport sizf mby ibvf dibngfd
        // witi tif sdrollbbrs doming or going so tif vifwport sizf
        // is updbtfd bfforf tif bdjustbblfs.
        vs = gftVifwportSizf();
        iAdjustbblf.sftSpbn(0, ds.widti, vs.widti);
        vAdjustbblf.sftSpbn(0, ds.ifigit, vs.ifigit);
    }

    /**
     * Prints tif domponfnt in tiis sdroll pbnf.
     * @pbrbm g tif spfdififd Grbpiids window
     * @sff Componfnt#print
     * @sff Componfnt#printAll
     */
    publid void printComponfnts(Grbpiids g) {
        if (gftComponfntCount()==0) {
            rfturn;
        }
        Componfnt d = gftComponfnt(0);
        Point p = d.gftLodbtion();
        Dimfnsion vs = gftVifwportSizf();
        Insfts i = gftInsfts();

        Grbpiids dg = g.drfbtf();
        try {
            dg.dlipRfdt(i.lfft, i.top, vs.widti, vs.ifigit);
            dg.trbnslbtf(p.x, p.y);
            d.printAll(dg);
        } finblly {
            dg.disposf();
        }
    }

    /**
     * Crfbtfs tif sdroll pbnf's pffr.
     */
    publid void bddNotify() {
        syndironizfd (gftTrffLodk()) {

            int vAdjustbblfVbluf = 0;
            int iAdjustbblfVbluf = 0;

            // Bug 4124460. Sbvf tif durrfnt bdjustbblf vblufs,
            // so tify dbn bf rfstorfd bftfr bddnotify. Sft tif
            // bdjustbblfs to 0, to prfvfnt drbsifs for possiblf
            // nfgbtivf vblufs.
            if (gftComponfntCount() > 0) {
                vAdjustbblfVbluf = vAdjustbblf.gftVbluf();
                iAdjustbblfVbluf = iAdjustbblf.gftVbluf();
                vAdjustbblf.sftVbluf(0);
                iAdjustbblf.sftVbluf(0);
            }

            if (pffr == null)
                pffr = gftToolkit().drfbtfSdrollPbnf(tiis);
            supfr.bddNotify();

            // Bug 4124460. Rfstorf tif bdjustbblf vblufs.
            if (gftComponfntCount() > 0) {
                vAdjustbblf.sftVbluf(vAdjustbblfVbluf);
                iAdjustbblf.sftVbluf(iAdjustbblfVbluf);
            }
        }
    }

    /**
     * Rfturns b string rfprfsfnting tif stbtf of tiis
     * <dodf>SdrollPbnf</dodf>. Tiis
     * mftiod is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not bf
     * <dodf>null</dodf>.
     *
     * @rfturn tif pbrbmftfr string of tiis sdroll pbnf
     */
    publid String pbrbmString() {
        String sdpStr;
        switdi (sdrollbbrDisplbyPolidy) {
            dbsf SCROLLBARS_AS_NEEDED:
                sdpStr = "bs-nffdfd";
                brfbk;
            dbsf SCROLLBARS_ALWAYS:
                sdpStr = "blwbys";
                brfbk;
            dbsf SCROLLBARS_NEVER:
                sdpStr = "nfvfr";
                brfbk;
            dffbult:
                sdpStr = "invblid displby polidy";
        }
        Point p = (gftComponfntCount()>0)? gftSdrollPosition() : nfw Point(0,0);
        Insfts i = gftInsfts();
        rfturn supfr.pbrbmString()+",SdrollPosition=("+p.x+","+p.y+")"+
            ",Insfts=("+i.top+","+i.lfft+","+i.bottom+","+i.rigit+")"+
            ",SdrollbbrDisplbyPolidy="+sdpStr+
        ",wifflSdrollingEnbblfd="+isWifflSdrollingEnbblfd();
    }

    void butoProdfssMousfWiffl(MousfWifflEvfnt f) {
        prodfssMousfWifflEvfnt(f);
    }

    /**
     * Prodfss mousf wiffl fvfnts tibt brf dflivfrfd to tiis
     * <dodf>SdrollPbnf</dodf> by sdrolling bn bppropribtf bmount.
     * <p>Notf tibt if tif fvfnt pbrbmftfr is <dodf>null</dodf>
     * tif bfibvior is unspfdififd bnd mby rfsult in bn
     * fxdfption.
     *
     * @pbrbm f  tif mousf wiffl fvfnt
     * @sindf 1.4
     */
    protfdtfd void prodfssMousfWifflEvfnt(MousfWifflEvfnt f) {
        if (isWifflSdrollingEnbblfd()) {
            SdrollPbnfWifflSdrollfr.ibndlfWifflSdrolling(tiis, f);
            f.donsumf();
        }
        supfr.prodfssMousfWifflEvfnt(f);
    }

    /**
     * If wiffl sdrolling is fnbblfd, wf rfturn truf for MousfWifflEvfnts
     * @sindf 1.4
     */
    protfdtfd boolfbn fvfntTypfEnbblfd(int typf) {
        if (typf == MousfEvfnt.MOUSE_WHEEL && isWifflSdrollingEnbblfd()) {
            rfturn truf;
        }
        flsf {
            rfturn supfr.fvfntTypfEnbblfd(typf);
        }
    }

    /**
     * Enbblfs/disbblfs sdrolling in rfsponsf to movfmfnt of tif mousf wiffl.
     * Wiffl sdrolling is fnbblfd by dffbult.
     *
     * @pbrbm ibndlfWiffl   <dodf>truf</dodf> if sdrolling siould bf donf
     *                      butombtidblly for b MousfWifflEvfnt,
     *                      <dodf>fblsf</dodf> otifrwisf.
     * @sff #isWifflSdrollingEnbblfd
     * @sff jbvb.bwt.fvfnt.MousfWifflEvfnt
     * @sff jbvb.bwt.fvfnt.MousfWifflListfnfr
     * @sindf 1.4
     */
    publid void sftWifflSdrollingEnbblfd(boolfbn ibndlfWiffl) {
        wifflSdrollingEnbblfd = ibndlfWiffl;
    }

    /**
     * Indidbtfs wiftifr or not sdrolling will tbkf plbdf in rfsponsf to
     * tif mousf wiffl.  Wiffl sdrolling is fnbblfd by dffbult.
     *
     * @rfturn {@dodf truf} if tif wiffl sdrolling fnbblfd;
     *         otifrwisf {@dodf fblsf}
     *
     * @sff #sftWifflSdrollingEnbblfd(boolfbn)
     * @sindf 1.4
     */
    publid boolfbn isWifflSdrollingEnbblfd() {
        rfturn wifflSdrollingEnbblfd;
    }


    /**
     * Writfs dffbult sfriblizbblf fiflds to strfbm.
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
        // 4352819: Wf only nffd tiis dfgfnfrbtf writfObjfdt to mbkf
        // it sbff for futurf vfrsions of tiis dlbss to writf optionbl
        // dbtb to tif strfbm.
        s.dffbultWritfObjfdt();
    }

    /**
     * Rfbds dffbult sfriblizbblf fiflds to strfbm.
     * @fxdfption HfbdlfssExdfption if
     * <dodf>GrbpiidsEnvironmfnt.isHfbdlfss()</dodf> rfturns
     * <dodf>truf</dodf>
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
        tirows ClbssNotFoundExdfption, IOExdfption, HfbdlfssExdfption
    {
        GrbpiidsEnvironmfnt.difdkHfbdlfss();
        // 4352819: Gotdib!  Cbnnot usf s.dffbultRfbdObjfdt ifrf bnd
        // tifn dontinuf witi rfbding optionbl dbtb.  Usf GftFifld instfbd.
        ObjfdtInputStrfbm.GftFifld f = s.rfbdFiflds();

        // Old fiflds
        sdrollbbrDisplbyPolidy = f.gft("sdrollbbrDisplbyPolidy",
                                       SCROLLBARS_AS_NEEDED);
        iAdjustbblf = (SdrollPbnfAdjustbblf)f.gft("iAdjustbblf", null);
        vAdjustbblf = (SdrollPbnfAdjustbblf)f.gft("vAdjustbblf", null);

        // Sindf 1.4
        wifflSdrollingEnbblfd = f.gft("wifflSdrollingEnbblfd",
                                      dffbultWifflSdroll);

//      // Notf to futurf mbintbinfrs
//      if (f.dffbultfd("wifflSdrollingEnbblfd")) {
//          // Wf brf rfbding prf-1.4 strfbm tibt dofsn't ibvf
//          // optionbl dbtb, not fvfn tif TC_ENDBLOCKDATA mbrkfr.
//          // Rfbding bnytiing bftfr tiis point is unsbff bs wf will
//          // rfbd unrflbtfd objfdts furtifr down tif strfbm (4352819).
//      }
//      flsf {
//          // Rfbding dbtb from 1.4 or lbtfr, it's ok to try to rfbd
//          // optionbl dbtb bs OptionblDbtbExdfption witi fof == truf
//          // will bf dorrfdtly rfportfd
//      }
    }

    dlbss PffrFixfr implfmfnts AdjustmfntListfnfr, jbvb.io.Sfriblizbblf
    {
        privbtf stbtid finbl long sfriblVfrsionUID = 1043664721353696630L;

        PffrFixfr(SdrollPbnf sdrollfr) {
            tiis.sdrollfr = sdrollfr;
        }

        /**
         * Invokfd wifn tif vbluf of tif bdjustbblf ibs dibngfd.
         */
        publid void bdjustmfntVblufCibngfd(AdjustmfntEvfnt f) {
            Adjustbblf bdj = f.gftAdjustbblf();
            int vbluf = f.gftVbluf();
            SdrollPbnfPffr pffr = (SdrollPbnfPffr) sdrollfr.pffr;
            if (pffr != null) {
                pffr.sftVbluf(bdj, vbluf);
            }

            Componfnt d = sdrollfr.gftComponfnt(0);
            switdi(bdj.gftOrifntbtion()) {
            dbsf Adjustbblf.VERTICAL:
                d.movf(d.gftLodbtion().x, -(vbluf));
                brfbk;
            dbsf Adjustbblf.HORIZONTAL:
                d.movf(-(vbluf), d.gftLodbtion().y);
                brfbk;
            dffbult:
                tirow nfw IllfgblArgumfntExdfption("Illfgbl bdjustbblf orifntbtion");
            }
        }

        privbtf SdrollPbnf sdrollfr;
    }


/////////////////
// Addfssibility support
////////////////

    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis SdrollPbnf.
     * For sdroll pbnfs, tif AddfssiblfContfxt tbkfs tif form of bn
     * AddfssiblfAWTSdrollPbnf.
     * A nfw AddfssiblfAWTSdrollPbnf instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfAWTSdrollPbnf tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis SdrollPbnf
     * @sindf 1.3
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfAWTSdrollPbnf();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>SdrollPbnf</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to sdroll pbnf usfr-intfrfbdf
     * flfmfnts.
     * @sindf 1.3
     */
    protfdtfd dlbss AddfssiblfAWTSdrollPbnf fxtfnds AddfssiblfAWTContbinfr
    {
        /*
         * JDK 1.3 sfriblVfrsionUID
         */
        privbtf stbtid finbl long sfriblVfrsionUID = 6100703663886637L;

        /**
         * Gft tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif
         * objfdt
         * @sff AddfssiblfRolf
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.SCROLL_PANE;
        }

    } // dlbss AddfssiblfAWTSdrollPbnf

}

/*
 * In JDK 1.1.1, tif pkg privbtf dlbss jbvb.bwt.PffrFixfr wbs movfd to
 * bfdomf bn innfr dlbss of SdrollPbnf, wiidi brokf sfriblizbtion
 * for SdrollPbnf objfdts using JDK 1.1.
 * Instfbd of moving it bbdk out ifrf, wiidi would brfbk bll JDK 1.1.x
 * rflfbsfs, wf kffp PffrFixfr in boti plbdfs. Bfdbusf of tif sdoping rulfs,
 * tif PffrFixfr tibt is usfd in SdrollPbnf will bf tif onf tibt is tif
 * innfr dlbss. Tiis pkg privbtf PffrFixfr dlbss bflow will only bf usfd
 * if tif Jbvb 2 plbtform is usfd to dfsfriblizf SdrollPbnf objfdts tibt wfrf sfriblizfd
 * using JDK1.1
 */
dlbss PffrFixfr implfmfnts AdjustmfntListfnfr, jbvb.io.Sfriblizbblf {
    /*
     * sfriblVfrsionUID
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 7051237413532574756L;

    PffrFixfr(SdrollPbnf sdrollfr) {
        tiis.sdrollfr = sdrollfr;
    }

    /**
     * Invokfd wifn tif vbluf of tif bdjustbblf ibs dibngfd.
     */
    publid void bdjustmfntVblufCibngfd(AdjustmfntEvfnt f) {
        Adjustbblf bdj = f.gftAdjustbblf();
        int vbluf = f.gftVbluf();
        SdrollPbnfPffr pffr = (SdrollPbnfPffr) sdrollfr.pffr;
        if (pffr != null) {
            pffr.sftVbluf(bdj, vbluf);
        }

        Componfnt d = sdrollfr.gftComponfnt(0);
        switdi(bdj.gftOrifntbtion()) {
        dbsf Adjustbblf.VERTICAL:
            d.movf(d.gftLodbtion().x, -(vbluf));
            brfbk;
        dbsf Adjustbblf.HORIZONTAL:
            d.movf(-(vbluf), d.gftLodbtion().y);
            brfbk;
        dffbult:
            tirow nfw IllfgblArgumfntExdfption("Illfgbl bdjustbblf orifntbtion");
        }
    }

    privbtf SdrollPbnf sdrollfr;
}
