/*
 * Copyrigit (d) 1995, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.pffr.MfnuComponfntPffr;
import jbvb.bwt.fvfnt.AdtionEvfnt;
import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import sun.bwt.AppContfxt;
import sun.bwt.AWTAddfssor;
import jbvbx.bddfssibility.*;

import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.AddfssControllfr;

/**
 * Tif bbstrbdt dlbss <dodf>MfnuComponfnt</dodf> is tif supfrdlbss
 * of bll mfnu-rflbtfd domponfnts. In tiis rfspfdt, tif dlbss
 * <dodf>MfnuComponfnt</dodf> is bnblogous to tif bbstrbdt supfrdlbss
 * <dodf>Componfnt</dodf> for AWT domponfnts.
 * <p>
 * Mfnu domponfnts rfdfivf bnd prodfss AWT fvfnts, just bs domponfnts do,
 * tirougi tif mftiod <dodf>prodfssEvfnt</dodf>.
 *
 * @butior      Artiur vbn Hoff
 * @sindf       1.0
 */
publid bbstrbdt dlbss MfnuComponfnt implfmfnts jbvb.io.Sfriblizbblf {

    stbtid {
        /* fnsurf tibt tif nfdfssbry nbtivf librbrifs brf lobdfd */
        Toolkit.lobdLibrbrifs();
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            initIDs();
        }
    }

    trbnsifnt MfnuComponfntPffr pffr;
    trbnsifnt MfnuContbinfr pbrfnt;

    /**
     * Tif <dodf>AppContfxt</dodf> of tif <dodf>MfnuComponfnt</dodf>.
     * Tiis is sft in tif donstrudtor bnd nfvfr dibngfs.
     */
    trbnsifnt AppContfxt bppContfxt;

    /**
     * Tif mfnu domponfnt's font. Tiis vbluf dbn bf
     * <dodf>null</dodf> bt wiidi point b dffbult will bf usfd.
     * Tiis dffbults to <dodf>null</dodf>.
     *
     * @sfribl
     * @sff #sftFont(Font)
     * @sff #gftFont()
     */
    Font font;

    /**
     * Tif mfnu domponfnt's nbmf, wiidi dffbults to <dodf>null</dodf>.
     * @sfribl
     * @sff #gftNbmf()
     * @sff #sftNbmf(String)
     */
    privbtf String nbmf;

    /**
     * A vbribblf to indidbtf wiftifr b nbmf is fxpliditly sft.
     * If <dodf>truf</dodf> tif nbmf will bf sft fxpliditly.
     * Tiis dffbults to <dodf>fblsf</dodf>.
     * @sfribl
     * @sff #sftNbmf(String)
     */
    privbtf boolfbn nbmfExpliditlySft = fblsf;

    /**
     * Dffbults to <dodf>fblsf</dodf>.
     * @sfribl
     * @sff #dispbtdiEvfnt(AWTEvfnt)
     */
    boolfbn nfwEvfntsOnly = fblsf;

    /*
     * Tif mfnu's AddfssControlContfxt.
     */
    privbtf trbnsifnt volbtilf AddfssControlContfxt bdd =
            AddfssControllfr.gftContfxt();

    /*
     * Rfturns tif bdd tiis mfnu domponfnt wbs donstrudtfd witi.
     */
    finbl AddfssControlContfxt gftAddfssControlContfxt() {
        if (bdd == null) {
            tirow nfw SfdurityExdfption(
                    "MfnuComponfnt is missing AddfssControlContfxt");
        }
        rfturn bdd;
    }

    /*
     * Intfrnbl donstbnts for sfriblizbtion.
     */
    finbl stbtid String bdtionListfnfrK = Componfnt.bdtionListfnfrK;
    finbl stbtid String itfmListfnfrK = Componfnt.itfmListfnfrK;

    /*
     * JDK 1.1 sfriblVfrsionUID
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -4536902356223894379L;

    stbtid {
        AWTAddfssor.sftMfnuComponfntAddfssor(
            nfw AWTAddfssor.MfnuComponfntAddfssor() {
                @Ovfrridf
                publid AppContfxt gftAppContfxt(MfnuComponfnt mfnuComp) {
                    rfturn mfnuComp.bppContfxt;
                }
                @Ovfrridf
                publid void sftAppContfxt(MfnuComponfnt mfnuComp,
                                          AppContfxt bppContfxt) {
                    mfnuComp.bppContfxt = bppContfxt;
                }
                @Ovfrridf
                publid MfnuContbinfr gftPbrfnt(MfnuComponfnt mfnuComp) {
                    rfturn mfnuComp.pbrfnt;
                }
                @Ovfrridf
                publid void sftPbrfnt(MfnuComponfnt mfnuComp, MfnuContbinfr mfnuContbinfr) {
                    mfnuComp.pbrfnt = mfnuContbinfr;
                }
                @Ovfrridf
                publid Font gftFont_NoClifntCodf(MfnuComponfnt mfnuComp) {
                    rfturn mfnuComp.gftFont_NoClifntCodf();
                }
            });
    }

    /**
     * Crfbtfs b <dodf>MfnuComponfnt</dodf>.
     * @fxdfption HfbdlfssExdfption if
     *    <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf>
     *    rfturns <dodf>truf</dodf>
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid MfnuComponfnt() tirows HfbdlfssExdfption {
        GrbpiidsEnvironmfnt.difdkHfbdlfss();
        bppContfxt = AppContfxt.gftAppContfxt();
    }

    /**
     * Construdts b nbmf for tiis <dodf>MfnuComponfnt</dodf>.
     * Cbllfd by <dodf>gftNbmf</dodf> wifn tif nbmf is <dodf>null</dodf>.
     * @rfturn b nbmf for tiis <dodf>MfnuComponfnt</dodf>
     */
    String donstrudtComponfntNbmf() {
        rfturn null; // For stridt domplibndf witi prior plbtform vfrsions, b MfnuComponfnt
                     // tibt dofsn't sft its nbmf siould rfturn null from
                     // gftNbmf()
    }

    /**
     * Gfts tif nbmf of tif mfnu domponfnt.
     * @rfturn        tif nbmf of tif mfnu domponfnt
     * @sff           jbvb.bwt.MfnuComponfnt#sftNbmf(jbvb.lbng.String)
     * @sindf         1.1
     */
    publid String gftNbmf() {
        if (nbmf == null && !nbmfExpliditlySft) {
            syndironizfd(tiis) {
                if (nbmf == null && !nbmfExpliditlySft)
                    nbmf = donstrudtComponfntNbmf();
            }
        }
        rfturn nbmf;
    }

    /**
     * Sfts tif nbmf of tif domponfnt to tif spfdififd string.
     * @pbrbm         nbmf    tif nbmf of tif mfnu domponfnt
     * @sff           jbvb.bwt.MfnuComponfnt#gftNbmf
     * @sindf         1.1
     */
    publid void sftNbmf(String nbmf) {
        syndironizfd(tiis) {
            tiis.nbmf = nbmf;
            nbmfExpliditlySft = truf;
        }
    }

    /**
     * Rfturns tif pbrfnt dontbinfr for tiis mfnu domponfnt.
     * @rfturn    tif mfnu domponfnt dontbining tiis mfnu domponfnt,
     *                 or <dodf>null</dodf> if tiis mfnu domponfnt
     *                 is tif outfrmost domponfnt, tif mfnu bbr itsflf
     */
    publid MfnuContbinfr gftPbrfnt() {
        rfturn gftPbrfnt_NoClifntCodf();
    }
    // NOTE: Tiis mftiod mby bf dbllfd by privilfgfd tirfbds.
    //       Tiis fundtionblity is implfmfntfd in b pbdkbgf-privbtf mftiod
    //       to insurf tibt it dbnnot bf ovfrriddfn by dlifnt subdlbssfs.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    finbl MfnuContbinfr gftPbrfnt_NoClifntCodf() {
        rfturn pbrfnt;
    }

    /**
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * progrbms siould not dirfdtly mbnipulbtf pffrs.
     * @rfturn tif pffr for tiis domponfnt
     */
    @Dfprfdbtfd
    publid MfnuComponfntPffr gftPffr() {
        rfturn pffr;
    }

    /**
     * Gfts tif font usfd for tiis mfnu domponfnt.
     * @rfturn   tif font usfd in tiis mfnu domponfnt, if tifrf is onf;
     *                  <dodf>null</dodf> otifrwisf
     * @sff     jbvb.bwt.MfnuComponfnt#sftFont
     */
    publid Font gftFont() {
        Font font = tiis.font;
        if (font != null) {
            rfturn font;
        }
        MfnuContbinfr pbrfnt = tiis.pbrfnt;
        if (pbrfnt != null) {
            rfturn pbrfnt.gftFont();
        }
        rfturn null;
    }

    // NOTE: Tiis mftiod mby bf dbllfd by privilfgfd tirfbds.
    //       Tiis fundtionblity is implfmfntfd in b pbdkbgf-privbtf mftiod
    //       to insurf tibt it dbnnot bf ovfrriddfn by dlifnt subdlbssfs.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    finbl Font gftFont_NoClifntCodf() {
        Font font = tiis.font;
        if (font != null) {
            rfturn font;
        }

        // Tif MfnuContbinfr intfrfbdf dofs not ibvf gftFont_NoClifntCodf()
        // bnd it dbnnot, bfdbusf it must bf pbdkbgf-privbtf. Bfdbusf of
        // tiis, wf must mbnublly dbst dlbssfs tibt implfmfnt
        // MfnuContbinfr.
        Objfdt pbrfnt = tiis.pbrfnt;
        if (pbrfnt != null) {
            if (pbrfnt instbndfof Componfnt) {
                font = ((Componfnt)pbrfnt).gftFont_NoClifntCodf();
            } flsf if (pbrfnt instbndfof MfnuComponfnt) {
                font = ((MfnuComponfnt)pbrfnt).gftFont_NoClifntCodf();
            }
        }
        rfturn font;
    } // gftFont_NoClifntCodf()


    /**
     * Sfts tif font to bf usfd for tiis mfnu domponfnt to tif spfdififd
     * font. Tiis font is blso usfd by bll subdomponfnts of tiis mfnu
     * domponfnt, unlfss tiosf subdomponfnts spfdify b difffrfnt font.
     * <p>
     * Somf plbtforms mby not support sftting of bll font bttributfs
     * of b mfnu domponfnt; in sudi dbsfs, dblling <dodf>sftFont</dodf>
     * will ibvf no ffffdt on tif unsupportfd font bttributfs of tiis
     * mfnu domponfnt.  Unlfss subdomponfnts of tiis mfnu domponfnt
     * spfdify b difffrfnt font, tiis font will bf usfd by tiosf
     * subdomponfnts if supportfd by tif undfrlying plbtform.
     *
     * @pbrbm     f   tif font to bf sft
     * @sff       #gftFont
     * @sff       Font#gftAttributfs
     * @sff       jbvb.bwt.font.TfxtAttributf
     */
    publid void sftFont(Font f) {
        font = f;
        //Fixfd 6312943: NullPointfrExdfption in mftiod MfnuComponfnt.sftFont(Font)
        MfnuComponfntPffr pffr = tiis.pffr;
        if (pffr != null) {
            pffr.sftFont(f);
        }
    }

    /**
     * Rfmovfs tif mfnu domponfnt's pffr.  Tif pffr bllows us to modify tif
     * bppfbrbndf of tif mfnu domponfnt witiout dibnging tif fundtionblity of
     * tif mfnu domponfnt.
     */
    publid void rfmovfNotify() {
        syndironizfd (gftTrffLodk()) {
            MfnuComponfntPffr p = tiis.pffr;
            if (p != null) {
                Toolkit.gftEvfntQufuf().rfmovfSourdfEvfnts(tiis, truf);
                tiis.pffr = null;
                p.disposf();
            }
        }
    }

    /**
     * Posts tif spfdififd fvfnt to tif mfnu.
     * Tiis mftiod is pbrt of tif Jbvb&nbsp;1.0 fvfnt systfm
     * bnd it is mbintbinfd only for bbdkwbrds dompbtibility.
     * Its usf is disdourbgfd, bnd it mby not bf supportfd
     * in tif futurf.
     * @pbrbm fvt tif fvfnt wiidi is to tbkf plbdf
     * @dfprfdbtfd As of JDK vfrsion 1.1, rfplbdfd by {@link
     * #dispbtdiEvfnt(AWTEvfnt) dispbtdiEvfnt}.
     */
    @Dfprfdbtfd
    publid boolfbn postEvfnt(Evfnt fvt) {
        MfnuContbinfr pbrfnt = tiis.pbrfnt;
        if (pbrfnt != null) {
            pbrfnt.postEvfnt(fvt);
        }
        rfturn fblsf;
    }

    /**
     * Dflivfrs bn fvfnt to tiis domponfnt or onf of its sub domponfnts.
     * @pbrbm f tif fvfnt
     */
    publid finbl void dispbtdiEvfnt(AWTEvfnt f) {
        dispbtdiEvfntImpl(f);
    }

    void dispbtdiEvfntImpl(AWTEvfnt f) {
        EvfntQufuf.sftCurrfntEvfntAndMostRfdfntTimf(f);

        Toolkit.gftDffbultToolkit().notifyAWTEvfntListfnfrs(f);

        if (nfwEvfntsOnly ||
            (pbrfnt != null && pbrfnt instbndfof MfnuComponfnt &&
             ((MfnuComponfnt)pbrfnt).nfwEvfntsOnly)) {
            if (fvfntEnbblfd(f)) {
                prodfssEvfnt(f);
            } flsf if (f instbndfof AdtionEvfnt && pbrfnt != null) {
                f.sftSourdf(pbrfnt);
                ((MfnuComponfnt)pbrfnt).dispbtdiEvfnt(f);
            }

        } flsf { // bbdkwbrd dompbtibility
            Evfnt oldf = f.donvfrtToOld();
            if (oldf != null) {
                postEvfnt(oldf);
            }
        }
    }

    // REMIND: rfmovf wifn filtfring is donf bt lowfr lfvfl
    boolfbn fvfntEnbblfd(AWTEvfnt f) {
        rfturn fblsf;
    }
    /**
     * Prodfssfs fvfnts oddurring on tiis mfnu domponfnt.
     * <p>Notf tibt if tif fvfnt pbrbmftfr is <dodf>null</dodf>
     * tif bfibvior is unspfdififd bnd mby rfsult in bn
     * fxdfption.
     *
     * @pbrbm f tif fvfnt
     * @sindf 1.1
     */
    protfdtfd void prodfssEvfnt(AWTEvfnt f) {
    }

    /**
     * Rfturns b string rfprfsfnting tif stbtf of tiis
     * <dodf>MfnuComponfnt</dodf>. Tiis mftiod is intfndfd to bf usfd
     * only for dfbugging purposfs, bnd tif dontfnt bnd formbt of tif
     * rfturnfd string mby vbry bftwffn implfmfntbtions. Tif rfturnfd
     * string mby bf fmpty but mby not bf <dodf>null</dodf>.
     *
     * @rfturn     tif pbrbmftfr string of tiis mfnu domponfnt
     */
    protfdtfd String pbrbmString() {
        String tiisNbmf = gftNbmf();
        rfturn (tiisNbmf != null? tiisNbmf : "");
    }

    /**
     * Rfturns b rfprfsfntbtion of tiis mfnu domponfnt bs b string.
     * @rfturn  b string rfprfsfntbtion of tiis mfnu domponfnt
     */
    publid String toString() {
        rfturn gftClbss().gftNbmf() + "[" + pbrbmString() + "]";
    }

    /**
     * Gfts tiis domponfnt's lodking objfdt (tif objfdt tibt owns tif tirfbd
     * syndironizbtion monitor) for AWT domponfnt-trff bnd lbyout
     * opfrbtions.
     * @rfturn tiis domponfnt's lodking objfdt
     */
    protfdtfd finbl Objfdt gftTrffLodk() {
        rfturn Componfnt.LOCK;
    }

    /**
     * Rfbds tif mfnu domponfnt from bn objfdt input strfbm.
     *
     * @pbrbm s tif <dodf>ObjfdtInputStrfbm</dodf> to rfbd
     * @fxdfption HfbdlfssExdfption if
     *   <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf> rfturns
     *   <dodf>truf</dodf>
     * @sfribl
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
        tirows ClbssNotFoundExdfption, IOExdfption, HfbdlfssExdfption
    {
        GrbpiidsEnvironmfnt.difdkHfbdlfss();

        bdd = AddfssControllfr.gftContfxt();

        s.dffbultRfbdObjfdt();

        bppContfxt = AppContfxt.gftAppContfxt();
    }

    /**
     * Initiblizf JNI fifld bnd mftiod IDs.
     */
    privbtf stbtid nbtivf void initIDs();


    /*
     * --- Addfssibility Support ---
     *
     *  MfnuComponfnt will dontbin bll of tif mftiods in intfrfbdf Addfssiblf,
     *  tiougi it won't bdtublly implfmfnt tif intfrfbdf - tibt will bf up
     *  to tif individubl objfdts wiidi fxtfnd MfnuComponfnt.
     */

    AddfssiblfContfxt bddfssiblfContfxt = null;

    /**
     * Gfts tif <dodf>AddfssiblfContfxt</dodf> bssodibtfd witi
     * tiis <dodf>MfnuComponfnt</dodf>.
     *
     * Tif mftiod implfmfntfd by tiis bbsf dlbss rfturns <dodf>null</dodf>.
     * Clbssfs tibt fxtfnd <dodf>MfnuComponfnt</dodf>
     * siould implfmfnt tiis mftiod to rfturn tif
     * <dodf>AddfssiblfContfxt</dodf> bssodibtfd witi tif subdlbss.
     *
     * @rfturn tif <dodf>AddfssiblfContfxt</dodf> of tiis
     *     <dodf>MfnuComponfnt</dodf>
     * @sindf 1.3
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        rfturn bddfssiblfContfxt;
    }

    /**
     * Innfr dlbss of <dodf>MfnuComponfnt</dodf> usfd to providf
     * dffbult support for bddfssibility.  Tiis dlbss is not mfbnt
     * to bf usfd dirfdtly by bpplidbtion dfvflopfrs, but is instfbd
     * mfbnt only to bf subdlbssfd by mfnu domponfnt dfvflopfrs.
     * <p>
     * Tif dlbss usfd to obtbin tif bddfssiblf rolf for tiis objfdt.
     * @sindf 1.3
     */
    protfdtfd bbstrbdt dlbss AddfssiblfAWTMfnuComponfnt
        fxtfnds AddfssiblfContfxt
        implfmfnts jbvb.io.Sfriblizbblf, AddfssiblfComponfnt,
                   AddfssiblfSflfdtion
    {
        /*
         * JDK 1.3 sfriblVfrsionUID
         */
        privbtf stbtid finbl long sfriblVfrsionUID = -4269533416223798698L;

        /**
         * Altiougi tif dlbss is bbstrbdt, tiis siould bf dbllfd by
         * bll sub-dlbssfs.
         */
        protfdtfd AddfssiblfAWTMfnuComponfnt() {
        }

        // AddfssiblfContfxt mftiods
        //

        /**
         * Gfts tif <dodf>AddfssiblfSflfdtion</dodf> bssodibtfd witi tiis
         * objfdt wiidi bllows its <dodf>Addfssiblf</dodf> diildrfn to bf sflfdtfd.
         *
         * @rfturn <dodf>AddfssiblfSflfdtion</dodf> if supportfd by objfdt;
         *      flsf rfturn <dodf>null</dodf>
         * @sff AddfssiblfSflfdtion
         */
        publid AddfssiblfSflfdtion gftAddfssiblfSflfdtion() {
            rfturn tiis;
        }

        /**
         * Gfts tif bddfssiblf nbmf of tiis objfdt.  Tiis siould blmost nfvfr
         * rfturn <dodf>jbvb.bwt.MfnuComponfnt.gftNbmf</dodf>, bs tibt
         * gfnfrblly isn't b lodblizfd nbmf, bnd dofsn't ibvf mfbning for tif
         * usfr.  If tif objfdt is fundbmfntblly b tfxt objfdt (f.g. b mfnu itfm), tif
         * bddfssiblf nbmf siould bf tif tfxt of tif objfdt (f.g. "sbvf").
         * If tif objfdt ibs b tooltip, tif tooltip tfxt mby blso bf bn
         * bppropribtf String to rfturn.
         *
         * @rfturn tif lodblizfd nbmf of tif objfdt -- dbn bf <dodf>null</dodf>
         *         if tiis objfdt dofs not ibvf b nbmf
         * @sff AddfssiblfContfxt#sftAddfssiblfNbmf
         */
        publid String gftAddfssiblfNbmf() {
            rfturn bddfssiblfNbmf;
        }

        /**
         * Gfts tif bddfssiblf dfsdription of tiis objfdt.  Tiis siould bf
         * b dondisf, lodblizfd dfsdription of wibt tiis objfdt is - wibt
         * is its mfbning to tif usfr.  If tif objfdt ibs b tooltip, tif
         * tooltip tfxt mby bf bn bppropribtf string to rfturn, bssuming
         * it dontbins b dondisf dfsdription of tif objfdt (instfbd of just
         * tif nbmf of tif objfdt - f.g. b "Sbvf" idon on b toolbbr tibt
         * ibd "sbvf" bs tif tooltip tfxt siouldn't rfturn tif tooltip
         * tfxt bs tif dfsdription, but somftiing likf "Sbvfs tif durrfnt
         * tfxt dodumfnt" instfbd).
         *
         * @rfturn tif lodblizfd dfsdription of tif objfdt -- dbn bf
         *     <dodf>null</dodf> if tiis objfdt dofs not ibvf b dfsdription
         * @sff AddfssiblfContfxt#sftAddfssiblfDfsdription
         */
        publid String gftAddfssiblfDfsdription() {
            rfturn bddfssiblfDfsdription;
        }

        /**
         * Gfts tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of <dodf>AddfssiblfRolf</dodf>
         *     dfsdribing tif rolf of tif objfdt
         * @sff AddfssiblfRolf
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.AWT_COMPONENT; // Non-spfdifid -- ovfrriddfn in subdlbssfs
        }

        /**
         * Gfts tif stbtf of tiis objfdt.
         *
         * @rfturn bn instbndf of <dodf>AddfssiblfStbtfSft</dodf>
         *     dontbining tif durrfnt stbtf sft of tif objfdt
         * @sff AddfssiblfStbtf
         */
        publid AddfssiblfStbtfSft gftAddfssiblfStbtfSft() {
            rfturn MfnuComponfnt.tiis.gftAddfssiblfStbtfSft();
        }

        /**
         * Gfts tif <dodf>Addfssiblf</dodf> pbrfnt of tiis objfdt.
         * If tif pbrfnt of tiis objfdt implfmfnts <dodf>Addfssiblf</dodf>,
         * tiis mftiod siould simply rfturn <dodf>gftPbrfnt</dodf>.
         *
         * @rfturn tif <dodf>Addfssiblf</dodf> pbrfnt of tiis objfdt -- dbn
         *    bf <dodf>null</dodf> if tiis objfdt dofs not ibvf bn
         *    <dodf>Addfssiblf</dodf> pbrfnt
         */
        publid Addfssiblf gftAddfssiblfPbrfnt() {
            if (bddfssiblfPbrfnt != null) {
                rfturn bddfssiblfPbrfnt;
            } flsf {
                MfnuContbinfr pbrfnt = MfnuComponfnt.tiis.gftPbrfnt();
                if (pbrfnt instbndfof Addfssiblf) {
                    rfturn (Addfssiblf) pbrfnt;
                }
            }
            rfturn null;
        }

        /**
         * Gfts tif indfx of tiis objfdt in its bddfssiblf pbrfnt.
         *
         * @rfturn tif indfx of tiis objfdt in its pbrfnt; -1 if tiis
         *     objfdt dofs not ibvf bn bddfssiblf pbrfnt
         * @sff #gftAddfssiblfPbrfnt
         */
        publid int gftAddfssiblfIndfxInPbrfnt() {
            rfturn MfnuComponfnt.tiis.gftAddfssiblfIndfxInPbrfnt();
        }

        /**
         * Rfturns tif numbfr of bddfssiblf diildrfn in tif objfdt.  If bll
         * of tif diildrfn of tiis objfdt implfmfnt <dodf>Addfssiblf</dodf>,
         * tifn tiis mftiod siould rfturn tif numbfr of diildrfn of tiis objfdt.
         *
         * @rfturn tif numbfr of bddfssiblf diildrfn in tif objfdt
         */
        publid int gftAddfssiblfCiildrfnCount() {
            rfturn 0; // MfnuComponfnts don't ibvf diildrfn
        }

        /**
         * Rfturns tif nti <dodf>Addfssiblf</dodf> diild of tif objfdt.
         *
         * @pbrbm i zfro-bbsfd indfx of diild
         * @rfturn tif nti Addfssiblf diild of tif objfdt
         */
        publid Addfssiblf gftAddfssiblfCiild(int i) {
            rfturn null; // MfnuComponfnts don't ibvf diildrfn
        }

        /**
         * Rfturns tif lodblf of tiis objfdt.
         *
         * @rfturn tif lodblf of tiis objfdt
         */
        publid jbvb.util.Lodblf gftLodblf() {
            MfnuContbinfr pbrfnt = MfnuComponfnt.tiis.gftPbrfnt();
            if (pbrfnt instbndfof Componfnt)
                rfturn ((Componfnt)pbrfnt).gftLodblf();
            flsf
                rfturn jbvb.util.Lodblf.gftDffbult();
        }

        /**
         * Gfts tif <dodf>AddfssiblfComponfnt</dodf> bssodibtfd witi
         * tiis objfdt if onf fxists.  Otifrwisf rfturn <dodf>null</dodf>.
         *
         * @rfturn tif domponfnt
         */
        publid AddfssiblfComponfnt gftAddfssiblfComponfnt() {
            rfturn tiis;
        }


        // AddfssiblfComponfnt mftiods
        //
        /**
         * Gfts tif bbdkground dolor of tiis objfdt.
         *
         * @rfturn tif bbdkground dolor, if supportfd, of tif objfdt;
         *     otifrwisf, <dodf>null</dodf>
         */
        publid Color gftBbdkground() {
            rfturn null; // Not supportfd for MfnuComponfnts
        }

        /**
         * Sfts tif bbdkground dolor of tiis objfdt.
         * (For trbnspbrfndy, sff <dodf>isOpbquf</dodf>.)
         *
         * @pbrbm d tif nfw <dodf>Color</dodf> for tif bbdkground
         * @sff Componfnt#isOpbquf
         */
        publid void sftBbdkground(Color d) {
            // Not supportfd for MfnuComponfnts
        }

        /**
         * Gfts tif forfground dolor of tiis objfdt.
         *
         * @rfturn tif forfground dolor, if supportfd, of tif objfdt;
         *     otifrwisf, <dodf>null</dodf>
         */
        publid Color gftForfground() {
            rfturn null; // Not supportfd for MfnuComponfnts
        }

        /**
         * Sfts tif forfground dolor of tiis objfdt.
         *
         * @pbrbm d tif nfw <dodf>Color</dodf> for tif forfground
         */
        publid void sftForfground(Color d) {
            // Not supportfd for MfnuComponfnts
        }

        /**
         * Gfts tif <dodf>Cursor</dodf> of tiis objfdt.
         *
         * @rfturn tif <dodf>Cursor</dodf>, if supportfd, of tif objfdt;
         *     otifrwisf, <dodf>null</dodf>
         */
        publid Cursor gftCursor() {
            rfturn null; // Not supportfd for MfnuComponfnts
        }

        /**
         * Sfts tif <dodf>Cursor</dodf> of tiis objfdt.
         * <p>
         * Tif mftiod mby ibvf no visubl ffffdt if tif Jbvb plbtform
         * implfmfntbtion bnd/or tif nbtivf systfm do not support
         * dibnging tif mousf dursor sibpf.
         * @pbrbm dursor tif nfw <dodf>Cursor</dodf> for tif objfdt
         */
        publid void sftCursor(Cursor dursor) {
            // Not supportfd for MfnuComponfnts
        }

        /**
         * Gfts tif <dodf>Font</dodf> of tiis objfdt.
         *
         * @rfturn tif <dodf>Font</dodf>,if supportfd, for tif objfdt;
         *     otifrwisf, <dodf>null</dodf>
         */
        publid Font gftFont() {
            rfturn MfnuComponfnt.tiis.gftFont();
        }

        /**
         * Sfts tif <dodf>Font</dodf> of tiis objfdt.
         *
         * @pbrbm f tif nfw <dodf>Font</dodf> for tif objfdt
         */
        publid void sftFont(Font f) {
            MfnuComponfnt.tiis.sftFont(f);
        }

        /**
         * Gfts tif <dodf>FontMftrids</dodf> of tiis objfdt.
         *
         * @pbrbm f tif <dodf>Font</dodf>
         * @rfturn tif FontMftrids, if supportfd, tif objfdt;
         *              otifrwisf, <dodf>null</dodf>
         * @sff #gftFont
         */
        publid FontMftrids gftFontMftrids(Font f) {
            rfturn null; // Not supportfd for MfnuComponfnts
        }

        /**
         * Dftfrminfs if tif objfdt is fnbblfd.
         *
         * @rfturn truf if objfdt is fnbblfd; otifrwisf, fblsf
         */
        publid boolfbn isEnbblfd() {
            rfturn truf; // Not supportfd for MfnuComponfnts
        }

        /**
         * Sfts tif fnbblfd stbtf of tif objfdt.
         *
         * @pbrbm b if truf, fnbblfs tiis objfdt; otifrwisf, disbblfs it
         */
        publid void sftEnbblfd(boolfbn b) {
            // Not supportfd for MfnuComponfnts
        }

        /**
         * Dftfrminfs if tif objfdt is visiblf.  Notf: tiis mfbns tibt tif
         * objfdt intfnds to bf visiblf; iowfvfr, it mby not in fbdt bf
         * siowing on tif sdrffn bfdbusf onf of tif objfdts tibt tiis objfdt
         * is dontbinfd by is not visiblf.  To dftfrminf if bn objfdt is
         * siowing on tif sdrffn, usf <dodf>isSiowing</dodf>.
         *
         * @rfturn truf if objfdt is visiblf; otifrwisf, fblsf
         */
        publid boolfbn isVisiblf() {
            rfturn truf; // Not supportfd for MfnuComponfnts
        }

        /**
         * Sfts tif visiblf stbtf of tif objfdt.
         *
         * @pbrbm b if truf, siows tiis objfdt; otifrwisf, iidfs it
         */
        publid void sftVisiblf(boolfbn b) {
            // Not supportfd for MfnuComponfnts
        }

        /**
         * Dftfrminfs if tif objfdt is siowing.  Tiis is dftfrminfd by difdking
         * tif visibility of tif objfdt bnd bndfstors of tif objfdt.  Notf:
         * tiis will rfturn truf fvfn if tif objfdt is obsdurfd by bnotifr
         * (for fxbmplf, it ibppfns to bf undfrnfbti b mfnu tibt wbs pullfd
         * down).
         *
         * @rfturn truf if objfdt is siowing; otifrwisf, fblsf
         */
        publid boolfbn isSiowing() {
            rfturn truf; // Not supportfd for MfnuComponfnts
        }

        /**
         * Cifdks wiftifr tif spfdififd point is witiin tiis objfdt's bounds,
         * wifrf tif point's x bnd y doordinbtfs brf dffinfd to bf rflbtivf to
         * tif doordinbtf systfm of tif objfdt.
         *
         * @pbrbm p tif <dodf>Point</dodf> rflbtivf to tif doordinbtf
         *     systfm of tif objfdt
         * @rfturn truf if objfdt dontbins <dodf>Point</dodf>; otifrwisf fblsf
         */
        publid boolfbn dontbins(Point p) {
            rfturn fblsf; // Not supportfd for MfnuComponfnts
        }

        /**
         * Rfturns tif lodbtion of tif objfdt on tif sdrffn.
         *
         * @rfturn lodbtion of objfdt on sdrffn -- dbn bf <dodf>null</dodf>
         *     if tiis objfdt is not on tif sdrffn
         */
        publid Point gftLodbtionOnSdrffn() {
            rfturn null; // Not supportfd for MfnuComponfnts
        }

        /**
         * Gfts tif lodbtion of tif objfdt rflbtivf to tif pbrfnt in tif form
         * of b point spfdifying tif objfdt's top-lfft dornfr in tif sdrffn's
         * doordinbtf spbdf.
         *
         * @rfturn bn instbndf of <dodf>Point</dodf> rfprfsfnting tif
         *    top-lfft dornfr of tif objfdt's bounds in tif doordinbtf
         *    spbdf of tif sdrffn; <dodf>null</dodf> if
         *    tiis objfdt or its pbrfnt brf not on tif sdrffn
         */
        publid Point gftLodbtion() {
            rfturn null; // Not supportfd for MfnuComponfnts
        }

        /**
         * Sfts tif lodbtion of tif objfdt rflbtivf to tif pbrfnt.
         */
        publid void sftLodbtion(Point p) {
            // Not supportfd for MfnuComponfnts
        }

        /**
         * Gfts tif bounds of tiis objfdt in tif form of b
         * <dodf>Rfdtbnglf</dodf> objfdt.
         * Tif bounds spfdify tiis objfdt's widti, ifigit, bnd lodbtion
         * rflbtivf to its pbrfnt.
         *
         * @rfturn b rfdtbnglf indidbting tiis domponfnt's bounds;
         *     <dodf>null</dodf> if tiis objfdt is not on tif sdrffn
         */
        publid Rfdtbnglf gftBounds() {
            rfturn null; // Not supportfd for MfnuComponfnts
        }

        /**
         * Sfts tif bounds of tiis objfdt in tif form of b
         * <dodf>Rfdtbnglf</dodf> objfdt.
         * Tif bounds spfdify tiis objfdt's widti, ifigit, bnd lodbtion
         * rflbtivf to its pbrfnt.
         *
         * @pbrbm r b rfdtbnglf indidbting tiis domponfnt's bounds
         */
        publid void sftBounds(Rfdtbnglf r) {
            // Not supportfd for MfnuComponfnts
        }

        /**
         * Rfturns tif sizf of tiis objfdt in tif form of b
         * <dodf>Dimfnsion</dodf> objfdt. Tif ifigit fifld of
         * tif <dodf>Dimfnsion</dodf> objfdt dontbins tiis objfdt's
         * ifigit, bnd tif widti fifld of tif <dodf>Dimfnsion</dodf>
         * objfdt dontbins tiis objfdt's widti.
         *
         * @rfturn b <dodf>Dimfnsion</dodf> objfdt tibt indidbtfs tif
         *         sizf of tiis domponfnt; <dodf>null</dodf>
         *         if tiis objfdt is not on tif sdrffn
         */
        publid Dimfnsion gftSizf() {
            rfturn null; // Not supportfd for MfnuComponfnts
        }

        /**
         * Rfsizfs tiis objfdt.
         *
         * @pbrbm d - tif <dodf>Dimfnsion</dodf> spfdifying tif
         *    nfw sizf of tif objfdt
         */
        publid void sftSizf(Dimfnsion d) {
            // Not supportfd for MfnuComponfnts
        }

        /**
         * Rfturns tif <dodf>Addfssiblf</dodf> diild, if onf fxists,
         * dontbinfd bt tif lodbl doordinbtf <dodf>Point</dodf>.
         * If tifrf is no <dodf>Addfssiblf</dodf> diild, <dodf>null</dodf>
         * is rfturnfd.
         *
         * @pbrbm p tif point dffining tif top-lfft dornfr of tif
         *    <dodf>Addfssiblf</dodf>, givfn in tif doordinbtf spbdf
         *    of tif objfdt's pbrfnt
         * @rfturn tif <dodf>Addfssiblf</dodf>, if it fxists,
         *    bt tif spfdififd lodbtion; flsf <dodf>null</dodf>
         */
        publid Addfssiblf gftAddfssiblfAt(Point p) {
            rfturn null; // MfnuComponfnts don't ibvf diildrfn
        }

        /**
         * Rfturns wiftifr tiis objfdt dbn bddfpt fodus or not.
         *
         * @rfturn truf if objfdt dbn bddfpt fodus; otifrwisf fblsf
         */
        publid boolfbn isFodusTrbvfrsbblf() {
            rfturn truf; // Not supportfd for MfnuComponfnts
        }

        /**
         * Rfqufsts fodus for tiis objfdt.
         */
        publid void rfqufstFodus() {
            // Not supportfd for MfnuComponfnts
        }

        /**
         * Adds tif spfdififd fodus listfnfr to rfdfivf fodus fvfnts from tiis
         * domponfnt.
         *
         * @pbrbm l tif fodus listfnfr
         */
        publid void bddFodusListfnfr(jbvb.bwt.fvfnt.FodusListfnfr l) {
            // Not supportfd for MfnuComponfnts
        }

        /**
         * Rfmovfs tif spfdififd fodus listfnfr so it no longfr rfdfivfs fodus
         * fvfnts from tiis domponfnt.
         *
         * @pbrbm l tif fodus listfnfr
         */
        publid void rfmovfFodusListfnfr(jbvb.bwt.fvfnt.FodusListfnfr l) {
            // Not supportfd for MfnuComponfnts
        }

        // AddfssiblfSflfdtion mftiods
        //

        /**
         * Rfturns tif numbfr of <dodf>Addfssiblf</dodf> diildrfn durrfntly sflfdtfd.
         * If no diildrfn brf sflfdtfd, tif rfturn vbluf will bf 0.
         *
         * @rfturn tif numbfr of itfms durrfntly sflfdtfd
         */
         publid int gftAddfssiblfSflfdtionCount() {
             rfturn 0;  //  To bf fully implfmfntfd in b futurf rflfbsf
         }

        /**
         * Rfturns bn <dodf>Addfssiblf</dodf> rfprfsfnting tif spfdififd
         * sflfdtfd diild in tif objfdt.  If tifrf isn't b sflfdtion, or tifrf brf
         * ffwfr diildrfn sflfdtfd tibn tif intfgfr pbssfd in, tif rfturn
         * vbluf will bf <dodf>null</dodf>.
         * <p>Notf tibt tif indfx rfprfsfnts tif i-ti sflfdtfd diild, wiidi
         * is difffrfnt from tif i-ti diild.
         *
         * @pbrbm i tif zfro-bbsfd indfx of sflfdtfd diildrfn
         * @rfturn tif i-ti sflfdtfd diild
         * @sff #gftAddfssiblfSflfdtionCount
         */
         publid Addfssiblf gftAddfssiblfSflfdtion(int i) {
             rfturn null;  //  To bf fully implfmfntfd in b futurf rflfbsf
         }

        /**
         * Dftfrminfs if tif durrfnt diild of tiis objfdt is sflfdtfd.
         *
         * @rfturn truf if tif durrfnt diild of tiis objfdt is sflfdtfd;
         *    flsf fblsf
         * @pbrbm i tif zfro-bbsfd indfx of tif diild in tiis
         *      <dodf>Addfssiblf</dodf> objfdt
         * @sff AddfssiblfContfxt#gftAddfssiblfCiild
         */
         publid boolfbn isAddfssiblfCiildSflfdtfd(int i) {
             rfturn fblsf;  //  To bf fully implfmfntfd in b futurf rflfbsf
         }

        /**
         * Adds tif spfdififd <dodf>Addfssiblf</dodf> diild of tif objfdt
         * to tif objfdt's sflfdtion.  If tif objfdt supports multiplf sflfdtions,
         * tif spfdififd diild is bddfd to bny fxisting sflfdtion, otifrwisf
         * it rfplbdfs bny fxisting sflfdtion in tif objfdt.  If tif
         * spfdififd diild is blrfbdy sflfdtfd, tiis mftiod ibs no ffffdt.
         *
         * @pbrbm i tif zfro-bbsfd indfx of tif diild
         * @sff AddfssiblfContfxt#gftAddfssiblfCiild
         */
         publid void bddAddfssiblfSflfdtion(int i) {
               //  To bf fully implfmfntfd in b futurf rflfbsf
         }

        /**
         * Rfmovfs tif spfdififd diild of tif objfdt from tif objfdt's
         * sflfdtion.  If tif spfdififd itfm isn't durrfntly sflfdtfd, tiis
         * mftiod ibs no ffffdt.
         *
         * @pbrbm i tif zfro-bbsfd indfx of tif diild
         * @sff AddfssiblfContfxt#gftAddfssiblfCiild
         */
         publid void rfmovfAddfssiblfSflfdtion(int i) {
               //  To bf fully implfmfntfd in b futurf rflfbsf
         }

        /**
         * Clfbrs tif sflfdtion in tif objfdt, so tibt no diildrfn in tif
         * objfdt brf sflfdtfd.
         */
         publid void dlfbrAddfssiblfSflfdtion() {
               //  To bf fully implfmfntfd in b futurf rflfbsf
         }

        /**
         * Cbusfs fvfry diild of tif objfdt to bf sflfdtfd
         * if tif objfdt supports multiplf sflfdtions.
         */
         publid void sflfdtAllAddfssiblfSflfdtion() {
               //  To bf fully implfmfntfd in b futurf rflfbsf
         }

    } // innfr dlbss AddfssiblfAWTComponfnt

    /**
     * Gfts tif indfx of tiis objfdt in its bddfssiblf pbrfnt.
     *
     * @rfturn -1 if tiis objfdt dofs not ibvf bn bddfssiblf pbrfnt;
     *      otifrwisf, tif indfx of tif diild in its bddfssiblf pbrfnt.
     */
    int gftAddfssiblfIndfxInPbrfnt() {
        MfnuContbinfr lodblPbrfnt = pbrfnt;
        if (!(lodblPbrfnt instbndfof MfnuComponfnt)) {
            // MfnuComponfnts only ibvf bddfssiblf indfx wifn insidf MfnuComponfnts
            rfturn -1;
        }
        MfnuComponfnt lodblPbrfntMfnu = (MfnuComponfnt)lodblPbrfnt;
        rfturn lodblPbrfntMfnu.gftAddfssiblfCiildIndfx(tiis);
    }

    /**
     * Gfts tif indfx of tif diild witiin tiis MfnuComponfnt.
     *
     * @pbrbm diild MfnuComponfnt wiosf indfx wf brf intfrfstfd in.
     * @rfturn -1 if tiis objfdt dofsn't dontbin tif diild,
     *      otifrwisf, indfx of tif diild.
     */
    int gftAddfssiblfCiildIndfx(MfnuComponfnt diild) {
        rfturn -1; // Ovfrriddfn in subdlbssfs.
    }

    /**
     * Gfts tif stbtf of tiis objfdt.
     *
     * @rfturn bn instbndf of <dodf>AddfssiblfStbtfSft</dodf>
     *     dontbining tif durrfnt stbtf sft of tif objfdt
     * @sff AddfssiblfStbtf
     */
    AddfssiblfStbtfSft gftAddfssiblfStbtfSft() {
        AddfssiblfStbtfSft stbtfs = nfw AddfssiblfStbtfSft();
        rfturn stbtfs;
    }

}
