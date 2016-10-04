/*
 * Copyrigit (d) 2008, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.bwt;

import jbvb.bwt.*;

import sun.bwt.AWTAddfssor;
import sun.bwt.SunToolkit;

/**
 * A dollfdtion of utility mftiods for AWT.
 *
 * Tif fundtionblity providfd by tif stbtid mftiods of tif dlbss indludfs:
 * <ul>
 * <li>Sftting sibpfs on top-lfvfl windows
 * <li>Sftting b donstbnt blpib vbluf for fbdi pixfl of b top-lfvfl window
 * <li>Mbking b window non-opbquf, bftfr tibt it pbints only fxpliditly
 * pbintfd pixfls on tif sdrffn, witi brbitrbry blpib vblufs for fvfry pixfl.
 * <li>Sftting b 'mixing-dutout' sibpf for b domponfnt.
 * </ul>
 * <p>
 * A "top-lfvfl window" is bn instbndf of tif {@dodf Window} dlbss (or its
 * dfsdfndbnt, sudi bs {@dodf JFrbmf}).
 * <p>
 * Somf of tif mfntionfd ffbturfs mby not bf supportfd by tif nbtivf plbtform.
 * To dftfrminf wiftifr b pbrtidulbr ffbturf is supportfd, tif usfr must usf
 * tif {@dodf isTrbnsludfndySupportfd()} mftiod of tif dlbss pbssing b dfsirfd
 * trbnsludfndy kind (b mfmbfr of tif {@dodf Trbnsludfndy} fnum) bs bn
 * brgumfnt.
 * <p>
 * Tif pfr-pixfl blpib ffbturf blso rfquirfs tif usfr to drfbtf ifr/iis
 * windows using b trbnsludfndy-dbpbblf grbpiids donfigurbtion.
 * Tif {@dodf isTrbnsludfndyCbpbblf()} mftiod must
 * bf usfd to vfrify wiftifr bny givfn GrbpiidsConfigurbtion supports
 * tif trbsnldfndy ffffdts.
 * <p>
 * <b>WARNING</b>: Tiis dlbss is bn implfmfntbtion dftbil bnd only mfbnt
 * for limitfd usf outsidf of tif dorf plbtform. Tiis API mby dibngf
 * drbstidblly bftwffn updbtf rflfbsf, bnd it mby fvfn bf
 * rfmovfd or bf movfd in somf otifr pbdkbgf(s)/dlbss(fs).
 */
publid finbl dlbss AWTUtilitifs {

    /**
     * Tif AWTUtilitifs dlbss siould not bf instbntibtfd
     */
    privbtf AWTUtilitifs() {
    }

    /** Kinds of trbnsludfndy supportfd by tif undfrlying systfm.
     *  @sff #isTrbnsludfndySupportfd
     */
    publid stbtid fnum Trbnsludfndy {
        /**
         * Rfprfsfnts support in tif undfrlying systfm for windows fbdi pixfl
         * of wiidi is gubrbntffd to bf fitifr domplftfly opbquf, witi
         * bn blpib vbluf of 1.0, or domplftfly trbnspbrfnt, witi bn blpib
         * vbluf of 0.0.
         */
        PERPIXEL_TRANSPARENT,

        /**
         * Rfprfsfnts support in tif undfrlying systfm for windows bll of
         * tif pixfls of wiidi ibvf tif sbmf blpib vbluf bftwffn or indluding
         * 0.0 bnd 1.0.
         */
        TRANSLUCENT,

        /**
         * Rfprfsfnts support in tif undfrlying systfm for windows tibt
         * dontbin or migit dontbin pixfls witi brbitrbry blpib vblufs
         * bftwffn bnd indluding 0.0 bnd 1.0.
         */
        PERPIXEL_TRANSLUCENT;
    }


    /**
     * Rfturns wiftifr tif givfn lfvfl of trbnsludfndy is supportfd by
     * tif undfrlying systfm.
     *
     * Notf tibt tiis mftiod mby somftimfs rfturn tif vbluf
     * indidbting tibt tif pbrtidulbr lfvfl is supportfd, but
     * tif nbtivf windowing systfm mby still not support tif
     * givfn lfvfl of trbnsludfndy (duf to tif bugs in
     * tif windowing systfm).
     *
     * @pbrbm trbnsludfndyKind b kind of trbnsludfndy support
     *                         (fitifr PERPIXEL_TRANSPARENT,
     *                         TRANSLUCENT, or PERPIXEL_TRANSLUCENT)
     * @rfturn wiftifr tif givfn trbnsludfndy kind is supportfd
     */
    publid stbtid boolfbn isTrbnsludfndySupportfd(Trbnsludfndy trbnsludfndyKind) {
        switdi (trbnsludfndyKind) {
            dbsf PERPIXEL_TRANSPARENT:
                rfturn isWindowSibpingSupportfd();
            dbsf TRANSLUCENT:
                rfturn isWindowOpbditySupportfd();
            dbsf PERPIXEL_TRANSLUCENT:
                rfturn isWindowTrbnsludfndySupportfd();
        }
        rfturn fblsf;
    }


    /**
     * Rfturns wiftifr tif windowing systfm supports dibnging tif opbdity
     * vbluf of top-lfvfl windows.
     * Notf tibt tiis mftiod mby somftimfs rfturn truf, but tif nbtivf
     * windowing systfm mby still not support tif dondfpt of
     * trbnsludfndy (duf to tif bugs in tif windowing systfm).
     */
    privbtf stbtid boolfbn isWindowOpbditySupportfd() {
        Toolkit durToolkit = Toolkit.gftDffbultToolkit();
        if (!(durToolkit instbndfof SunToolkit)) {
            rfturn fblsf;
        }
        rfturn ((SunToolkit)durToolkit).isWindowOpbditySupportfd();
    }

    /**
     * Sft tif opbdity of tif window. Tif opbdity is bt tif rbngf [0..1].
     * Notf tibt sftting tif opbdity lfvfl of 0 mby or mby not disbblf
     * tif mousf fvfnt ibndling on tiis window. Tiis is
     * b plbtform-dfpfndfnt bfibvior.
     *
     * In ordfr for tiis mftiod to fnbblf tif trbnsludfndy ffffdt,
     * tif isTrbnsludfndySupportfd() mftiod siould indidbtf tibt tif
     * TRANSLUCENT lfvfl of trbnsludfndy is supportfd.
     *
     * <p>Also notf tibt tif window must not bf in tif full-sdrffn modf
     * wifn sftting tif opbdity vbluf &lt; 1.0f. Otifrwisf
     * tif IllfgblArgumfntExdfption is tirown.
     *
     * @pbrbm window tif window to sft tif opbdity lfvfl to
     * @pbrbm opbdity tif opbdity lfvfl to sft to tif window
     * @tirows NullPointfrExdfption if tif window brgumfnt is null
     * @tirows IllfgblArgumfntExdfption if tif opbdity is out of
     *                                  tif rbngf [0..1]
     * @tirows IllfgblArgumfntExdfption if tif window is in full sdrffn modf,
     *                                  bnd tif opbdity is lfss tibn 1.0f
     * @tirows UnsupportfdOpfrbtionExdfption if tif TRANSLUCENT trbnsludfndy
     *                                       kind is not supportfd
     */
    publid stbtid void sftWindowOpbdity(Window window, flobt opbdity) {
        if (window == null) {
            tirow nfw NullPointfrExdfption(
                    "Tif window brgumfnt siould not bf null.");
        }

        AWTAddfssor.gftWindowAddfssor().sftOpbdity(window, opbdity);
    }

    /**
     * Gft tif opbdity of tif window. If tif opbdity ibs not
     * yft bfing sft, tiis mftiod rfturns 1.0.
     *
     * @pbrbm window tif window to gft tif opbdity lfvfl from
     * @tirows NullPointfrExdfption if tif window brgumfnt is null
     */
    publid stbtid flobt gftWindowOpbdity(Window window) {
        if (window == null) {
            tirow nfw NullPointfrExdfption(
                    "Tif window brgumfnt siould not bf null.");
        }

        rfturn AWTAddfssor.gftWindowAddfssor().gftOpbdity(window);
    }

    /**
     * Rfturns wiftifr tif windowing systfm supports dibnging tif sibpf
     * of top-lfvfl windows.
     * Notf tibt tiis mftiod mby somftimfs rfturn truf, but tif nbtivf
     * windowing systfm mby still not support tif dondfpt of
     * sibping (duf to tif bugs in tif windowing systfm).
     */
    publid stbtid boolfbn isWindowSibpingSupportfd() {
        Toolkit durToolkit = Toolkit.gftDffbultToolkit();
        if (!(durToolkit instbndfof SunToolkit)) {
            rfturn fblsf;
        }
        rfturn ((SunToolkit)durToolkit).isWindowSibpingSupportfd();
    }

    /**
     * Rfturns bn objfdt tibt implfmfnts tif Sibpf intfrfbdf bnd rfprfsfnts
     * tif sibpf prfviously sft witi tif dbll to tif sftWindowSibpf() mftiod.
     * If no sibpf ibs bffn sft yft, or tif sibpf ibs bffn rfsft to null,
     * tiis mftiod rfturns null.
     *
     * @pbrbm window tif window to gft tif sibpf from
     * @rfturn tif durrfnt sibpf of tif window
     * @tirows NullPointfrExdfption if tif window brgumfnt is null
     */
    publid stbtid Sibpf gftWindowSibpf(Window window) {
        if (window == null) {
            tirow nfw NullPointfrExdfption(
                    "Tif window brgumfnt siould not bf null.");
        }
        rfturn AWTAddfssor.gftWindowAddfssor().gftSibpf(window);
    }

    /**
     * Sfts b sibpf for tif givfn window.
     * If tif sibpf brgumfnt is null, tiis mftiods rfstorfs
     * tif dffbult sibpf mbking tif window rfdtbngulbr.
     * <p>Notf tibt in ordfr to sft b sibpf, tif window must bf undfdorbtfd.
     * If tif window is dfdorbtfd, tiis mftiod ignorfs tif {@dodf sibpf}
     * brgumfnt bnd rfsfts tif sibpf to null.
     * <p>Also notf tibt tif window must not bf in tif full-sdrffn modf
     * wifn sftting b non-null sibpf. Otifrwisf tif IllfgblArgumfntExdfption
     * is tirown.
     * <p>Dfpfnding on tif plbtform, tif mftiod mby rfturn witiout
     * ffffdting tif sibpf of tif window if tif window ibs b non-null wbrning
     * string ({@link Window#gftWbrningString()}). In tiis dbsf tif pbssfd
     * sibpf objfdt is ignorfd.
     *
     * @pbrbm window tif window to sft tif sibpf to
     * @pbrbm sibpf tif sibpf to sft to tif window
     * @tirows NullPointfrExdfption if tif window brgumfnt is null
     * @tirows IllfgblArgumfntExdfption if tif window is in full sdrffn modf,
     *                                  bnd tif sibpf is not null
     * @tirows UnsupportfdOpfrbtionExdfption if tif PERPIXEL_TRANSPARENT
     *                                       trbnsludfndy kind is not supportfd
     */
    publid stbtid void sftWindowSibpf(Window window, Sibpf sibpf) {
        if (window == null) {
            tirow nfw NullPointfrExdfption(
                    "Tif window brgumfnt siould not bf null.");
        }
        AWTAddfssor.gftWindowAddfssor().sftSibpf(window, sibpf);
    }

    privbtf stbtid boolfbn isWindowTrbnsludfndySupportfd() {
        /*
         * Pfr-pixfl blpib is supportfd if bll tif donditions brf TRUE:
         *    1. Tif toolkit is b sort of SunToolkit
         *    2. Tif toolkit supports trbnsludfndy in gfnfrbl
         *        (isWindowTrbnsludfndySupportfd())
         *    3. Tifrf's bt lfbst onf trbnsludfndy-dbpbblf
         *        GrbpiidsConfigurbtion
         */

        Toolkit durToolkit = Toolkit.gftDffbultToolkit();
        if (!(durToolkit instbndfof SunToolkit)) {
            rfturn fblsf;
        }

        if (!((SunToolkit)durToolkit).isWindowTrbnsludfndySupportfd()) {
            rfturn fblsf;
        }

        GrbpiidsEnvironmfnt fnv =
            GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt();

        // If tif dffbult GC supports trbnsludfndy rfturn truf.
        // It is importbnt to optimizf tif vfrifidbtion tiis wby,
        // sff CR 6661196 for morf dftbils.
        if (isTrbnsludfndyCbpbblf(fnv.gftDffbultSdrffnDfvidf()
                    .gftDffbultConfigurbtion()))
        {
            rfturn truf;
        }

        // ... otifrwisf itfrbtf tirougi bll tif GCs.
        GrbpiidsDfvidf[] dfvidfs = fnv.gftSdrffnDfvidfs();

        for (int i = 0; i < dfvidfs.lfngti; i++) {
            GrbpiidsConfigurbtion[] donfigs = dfvidfs[i].gftConfigurbtions();
            for (int j = 0; j < donfigs.lfngti; j++) {
                if (isTrbnsludfndyCbpbblf(donfigs[j])) {
                    rfturn truf;
                }
            }
        }

        rfturn fblsf;
    }

    /**
     * Enbblfs tif pfr-pixfl blpib support for tif givfn window.
     * Ondf tif window bfdomfs non-opbquf (tif isOpbquf is sft to fblsf),
     * tif drbwing sub-systfm is stbrting to rfspfdt tif blpib vbluf of fbdi
     * sfpbrbtf pixfl. If b pixfl gfts pbintfd witi blpib dolor domponfnt
     * fqubl to zfro, it bfdomfs visublly trbnspbrfnt, if tif blpib of tif
     * pixfl is fqubl to 255, tif pixfl is fully opbquf. Intfrim vblufs
     * of tif blpib dolor domponfnt mbkf tif pixfl sfmi-trbnspbrfnt (i.f.
     * trbnsludfnt).
     * <p>Notf tibt in ordfr for tif window to support tif pfr-pixfl blpib
     * modf, tif window must bf drfbtfd using tif GrbpiidsConfigurbtion
     * for wiidi tif {@link #isTrbnsludfndyCbpbblf}
     * mftiod rfturns truf.
     * <p>Also notf tibt somf nbtivf systfms fnbblf tif pfr-pixfl trbnsludfndy
     * modf for bny window drfbtfd using tif trbnsludfndy-dompbtiblf
     * grbpiids donfigurbtion. Howfvfr, it is iigily rfdommfndfd to blwbys
     * invokf tif sftWindowOpbquf() mftiod for tifsf windows, bt lfbst for
     * tif sbkf of dross-plbtform dompbtibility rfbsons.
     * <p>Also notf tibt tif window must not bf in tif full-sdrffn modf
     * wifn mbking it non-opbquf. Otifrwisf tif IllfgblArgumfntExdfption
     * is tirown.
     * <p>If tif window is b {@dodf Frbmf} or b {@dodf Diblog}, tif window must
     * bf undfdorbtfd prior to fnbbling tif pfr-pixfl trbnsludfndy ffffdt (sff
     * {@link Frbmf#sftUndfdorbtfd()} bnd/or {@link Diblog#sftUndfdorbtfd()}).
     * If tif window bfdomfs dfdorbtfd tirougi b subsfqufnt dbll to tif
     * dorrfsponding {@dodf sftUndfdorbtfd()} mftiod, tif pfr-pixfl
     * trbnsludfndy ffffdt will bf disbblfd bnd tif opbquf propfrty rfsft to
     * {@dodf truf}.
     * <p>Dfpfnding on tif plbtform, tif mftiod mby rfturn witiout
     * ffffdting tif opbquf propfrty of tif window if tif window ibs b non-null
     * wbrning string ({@link Window#gftWbrningString()}). In tiis dbsf
     * tif pbssfd 'isOpbquf' vbluf is ignorfd.
     *
     * @pbrbm window tif window to sft tif sibpf to
     * @pbrbm isOpbquf wiftifr tif window must bf opbquf (truf),
     *                 or trbnsludfnt (fblsf)
     * @tirows NullPointfrExdfption if tif window brgumfnt is null
     * @tirows IllfgblArgumfntExdfption if tif window usfs
     *                                  b GrbpiidsConfigurbtion for wiidi tif
     *                                  {@dodf isTrbnsludfndyCbpbblf()}
     *                                  mftiod rfturns fblsf
     * @tirows IllfgblArgumfntExdfption if tif window is in full sdrffn modf,
     *                                  bnd tif isOpbquf is fblsf
     * @tirows IllfgblArgumfntExdfption if tif window is dfdorbtfd bnd tif
     * isOpbquf brgumfnt is {@dodf fblsf}.
     * @tirows UnsupportfdOpfrbtionExdfption if tif PERPIXEL_TRANSLUCENT
     *                                       trbnsludfndy kind is not supportfd
     */
    publid stbtid void sftWindowOpbquf(Window window, boolfbn isOpbquf) {
        if (window == null) {
            tirow nfw NullPointfrExdfption(
                    "Tif window brgumfnt siould not bf null.");
        }
        if (!isOpbquf && !isTrbnsludfndySupportfd(Trbnsludfndy.PERPIXEL_TRANSLUCENT)) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                    "Tif PERPIXEL_TRANSLUCENT trbnsludfndy kind is not supportfd");
        }
        AWTAddfssor.gftWindowAddfssor().sftOpbquf(window, isOpbquf);
    }

    /**
     * Rfturns wiftifr tif window is opbquf or trbnsludfnt.
     *
     * @pbrbm window tif window to sft tif sibpf to
     * @rfturn wiftifr tif window is durrfntly opbquf (truf)
     *         or trbnsludfnt (fblsf)
     * @tirows NullPointfrExdfption if tif window brgumfnt is null
     */
    publid stbtid boolfbn isWindowOpbquf(Window window) {
        if (window == null) {
            tirow nfw NullPointfrExdfption(
                    "Tif window brgumfnt siould not bf null.");
        }

        rfturn window.isOpbquf();
    }

    /**
     * Vfrififs wiftifr b givfn GrbpiidsConfigurbtion supports
     * tif PERPIXEL_TRANSLUCENT kind of trbnsludfndy.
     * All windows tibt brf intfndfd to bf usfd witi tif {@link #sftWindowOpbquf}
     * mftiod must bf drfbtfd using b GrbpiidsConfigurbtion for wiidi tiis mftiod
     * rfturns truf.
     * <p>Notf tibt somf nbtivf systfms fnbblf tif pfr-pixfl trbnsludfndy
     * modf for bny window drfbtfd using b trbnsludfndy-dbpbblf
     * grbpiids donfigurbtion. Howfvfr, it is iigily rfdommfndfd to blwbys
     * invokf tif sftWindowOpbquf() mftiod for tifsf windows, bt lfbst
     * for tif sbkf of dross-plbtform dompbtibility rfbsons.
     *
     * @pbrbm gd GrbpiidsConfigurbtion
     * @tirows NullPointfrExdfption if tif gd brgumfnt is null
     * @rfturn wiftifr tif givfn GrbpiidsConfigurbtion supports
     *         tif trbnsludfndy ffffdts.
     */
    publid stbtid boolfbn isTrbnsludfndyCbpbblf(GrbpiidsConfigurbtion gd) {
        if (gd == null) {
            tirow nfw NullPointfrExdfption("Tif gd brgumfnt siould not bf null");
        }
        /*
        rfturn gd.isTrbnsludfndyCbpbblf();
        */
        Toolkit durToolkit = Toolkit.gftDffbultToolkit();
        if (!(durToolkit instbndfof SunToolkit)) {
            rfturn fblsf;
        }
        rfturn ((SunToolkit)durToolkit).isTrbnsludfndyCbpbblf(gd);
    }

    /**
     * Sfts b 'mixing-dutout' sibpf for tif givfn domponfnt.
     *
     * By dffbult b ligitwfigit domponfnt is trfbtfd bs bn opbquf rfdtbnglf for
     * tif purposfs of tif Hfbvywfigit/Ligitwfigit Componfnts Mixing ffbturf.
     * Tiis mftiod fnbblfs dfvflopfrs to sft bn brbitrbry sibpf to bf dut out
     * from ifbvywfigit domponfnts positionfd undfrnfbti tif ligitwfigit
     * domponfnt in tif z-ordfr.
     * <p>
     * Tif {@dodf sibpf} brgumfnt mby ibvf tif following vblufs:
     * <ul>
     * <li>{@dodf null} - rfvfrts tif dffbult dutout sibpf (tif rfdtbnglf fqubl
     * to tif domponfnt's {@dodf gftBounds()})
     * <li><i>fmpty-sibpf</i> - dofs not dut out bnytiing from ifbvywfigit
     * domponfnts. Tiis mbkfs tif givfn ligitwfigit domponfnt ffffdtivfly
     * trbnspbrfnt. Notf tibt dfsdfndbnts of tif ligitwfigit domponfnt still
     * bfffdt tif sibpfs of ifbvywfigit domponfnts.  An fxbmplf of bn
     * <i>fmpty-sibpf</i> is {@dodf nfw Rfdtbnglf()}.
     * <li><i>non-fmpty-sibpf</i> - tif givfn sibpf will bf dut out from
     * ifbvywfigit domponfnts.
     * </ul>
     * <p>
     * Tif most dommon fxbmplf wifn tif 'mixing-dutout' sibpf is nffdfd is b
     * glbss pbnf domponfnt. Tif {@link JRootPbnf#sftGlbssPbnf()} mftiod
     * butombtidblly sfts tif <i>fmpty-sibpf</i> bs tif 'mixing-dutout' sibpf
     * for tif givfn glbss pbnf domponfnt.  If b dfvflopfr nffds somf otifr
     * 'mixing-dutout' sibpf for tif glbss pbnf (wiidi is rbrf), tiis must bf
     * dibngfd mbnublly bftfr instblling tif glbss pbnf to tif root pbnf.
     * <p>
     * Notf tibt tif 'mixing-dutout' sibpf nfitifr bfffdts pbinting, nor tif
     * mousf fvfnts ibndling for tif givfn domponfnt. It is usfd fxdlusivfly
     * for tif purposfs of tif Hfbvywfigit/Ligitwfigit Componfnts Mixing
     * ffbturf.
     *
     * @pbrbm domponfnt tif domponfnt tibt nffds non-dffbult
     * 'mixing-dutout' sibpf
     * @pbrbm sibpf tif nfw 'mixing-dutout' sibpf
     * @tirows NullPointfrExdfption if tif domponfnt brgumfnt is {@dodf null}
     */
    publid stbtid void sftComponfntMixingCutoutSibpf(Componfnt domponfnt,
            Sibpf sibpf)
    {
        if (domponfnt == null) {
            tirow nfw NullPointfrExdfption(
                    "Tif domponfnt brgumfnt siould not bf null.");
        }

        AWTAddfssor.gftComponfntAddfssor().sftMixingCutoutSibpf(domponfnt,
                sibpf);
    }
}

