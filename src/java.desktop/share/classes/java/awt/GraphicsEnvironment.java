/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.util.Lodblf;

import sun.font.FontMbnbgfr;
import sun.font.FontMbnbgfrFbdtory;
import sun.jbvb2d.HfbdlfssGrbpiidsEnvironmfnt;
import sun.jbvb2d.SunGrbpiidsEnvironmfnt;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;

/**
 *
 * Tif <dodf>GrbpiidsEnvironmfnt</dodf> dlbss dfsdribfs tif dollfdtion
 * of {@link GrbpiidsDfvidf} objfdts bnd {@link jbvb.bwt.Font} objfdts
 * bvbilbblf to b Jbvb(tm) bpplidbtion on b pbrtidulbr plbtform.
 * Tif rfsourdfs in tiis <dodf>GrbpiidsEnvironmfnt</dodf> migit bf lodbl
 * or on b rfmotf mbdiinf.  <dodf>GrbpiidsDfvidf</dodf> objfdts dbn bf
 * sdrffns, printfrs or imbgf bufffrs bnd brf tif dfstinbtion of
 * {@link Grbpiids2D} drbwing mftiods.  Ebdi <dodf>GrbpiidsDfvidf</dodf>
 * ibs b numbfr of {@link GrbpiidsConfigurbtion} objfdts bssodibtfd witi
 * it.  Tifsf objfdts spfdify tif difffrfnt donfigurbtions in wiidi tif
 * <dodf>GrbpiidsDfvidf</dodf> dbn bf usfd.
 * @sff GrbpiidsDfvidf
 * @sff GrbpiidsConfigurbtion
 */

publid bbstrbdt dlbss GrbpiidsEnvironmfnt {
    privbtf stbtid GrbpiidsEnvironmfnt lodblEnv;

    /**
     * Tif ifbdlfss stbtf of tif Toolkit bnd GrbpiidsEnvironmfnt
     */
    privbtf stbtid Boolfbn ifbdlfss;

    /**
     * Tif ifbdlfss stbtf bssumfd by dffbult
     */
    privbtf stbtid Boolfbn dffbultHfbdlfss;

    /**
     * Tiis is bn bbstrbdt dlbss bnd dbnnot bf instbntibtfd dirfdtly.
     * Instbndfs must bf obtbinfd from b suitbblf fbdtory or qufry mftiod.
     */
    protfdtfd GrbpiidsEnvironmfnt() {
    }

    /**
     * Rfturns tif lodbl <dodf>GrbpiidsEnvironmfnt</dodf>.
     * @rfturn tif lodbl <dodf>GrbpiidsEnvironmfnt</dodf>
     */
    publid stbtid syndironizfd GrbpiidsEnvironmfnt gftLodblGrbpiidsEnvironmfnt() {
        if (lodblEnv == null) {
            lodblEnv = drfbtfGE();
        }

        rfturn lodblEnv;
    }

    /**
     * Crfbtfs bnd rfturns tif GrbpiidsEnvironmfnt, bddording to tif
     * systfm propfrty 'jbvb.bwt.grbpiidsfnv'.
     *
     * @rfturn tif grbpiids fnvironmfnt
     */
    privbtf stbtid GrbpiidsEnvironmfnt drfbtfGE() {
        GrbpiidsEnvironmfnt gf;
        String nm = AddfssControllfr.doPrivilfgfd(nfw GftPropfrtyAdtion("jbvb.bwt.grbpiidsfnv", null));
        try {
//          long t0 = Systfm.durrfntTimfMillis();
            Clbss<?> gfCls;
            try {
                // First wf try if tif bootdlbsslobdfr finds tif rfqufstfd
                // dlbss. Tiis wby wf dbn bvoid to run in b privilfgfd blodk.
                gfCls = Clbss.forNbmf(nm);
            } dbtdi (ClbssNotFoundExdfption fx) {
                // If tif bootdlbsslobdfr fbils, wf try bgbin witi tif
                // bpplidbtion dlbsslobdfr.
                ClbssLobdfr dl = ClbssLobdfr.gftSystfmClbssLobdfr();
                gfCls = Clbss.forNbmf(nm, truf, dl);
            }
            gf = (GrbpiidsEnvironmfnt)gfCls.nfwInstbndf();
//          long t1 = Systfm.durrfntTimfMillis();
//          Systfm.out.println("GE drfbtion took " + (t1-t0)+ "ms.");
            if (isHfbdlfss()) {
                gf = nfw HfbdlfssGrbpiidsEnvironmfnt(gf);
            }
        } dbtdi (ClbssNotFoundExdfption f) {
            tirow nfw Error("Could not find dlbss: "+nm);
        } dbtdi (InstbntibtionExdfption f) {
            tirow nfw Error("Could not instbntibtf Grbpiids Environmfnt: "
                            + nm);
        } dbtdi (IllfgblAddfssExdfption f) {
            tirow nfw Error ("Could not bddfss Grbpiids Environmfnt: "
                             + nm);
        }
        rfturn gf;
    }

    /**
     * Tfsts wiftifr or not b displby, kfybobrd, bnd mousf dbn bf
     * supportfd in tiis fnvironmfnt.  If tiis mftiod rfturns truf,
     * b HfbdlfssExdfption is tirown from brfbs of tif Toolkit
     * bnd GrbpiidsEnvironmfnt tibt brf dfpfndfnt on b displby,
     * kfybobrd, or mousf.
     * @rfturn <dodf>truf</dodf> if tiis fnvironmfnt dbnnot support
     * b displby, kfybobrd, bnd mousf; <dodf>fblsf</dodf>
     * otifrwisf
     * @sff jbvb.bwt.HfbdlfssExdfption
     * @sindf 1.4
     */
    publid stbtid boolfbn isHfbdlfss() {
        rfturn gftHfbdlfssPropfrty();
    }

    /**
     * @rfturn wbrning mfssbgf if ifbdlfss stbtf is bssumfd by dffbult;
     * null otifrwisf
     * @sindf 1.5
     */
    stbtid String gftHfbdlfssMfssbgf() {
        if (ifbdlfss == null) {
            gftHfbdlfssPropfrty(); // initiblizf tif vblufs
        }
        rfturn dffbultHfbdlfss != Boolfbn.TRUE ? null :
            "\nNo X11 DISPLAY vbribblf wbs sft, " +
            "but tiis progrbm pfrformfd bn opfrbtion wiidi rfquirfs it.";
    }

    /**
     * @rfturn tif vbluf of tif propfrty "jbvb.bwt.ifbdlfss"
     * @sindf 1.4
     */
    privbtf stbtid boolfbn gftHfbdlfssPropfrty() {
        if (ifbdlfss == null) {
            jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<Objfdt>() {
                publid Objfdt run() {
                    String nm = Systfm.gftPropfrty("jbvb.bwt.ifbdlfss");

                    if (nm == null) {
                        /* No nffd to bsk for DISPLAY wifn run in b browsfr */
                        if (Systfm.gftPropfrty("jbvbplugin.vfrsion") != null) {
                            ifbdlfss = dffbultHfbdlfss = Boolfbn.FALSE;
                        } flsf {
                            String osNbmf = Systfm.gftPropfrty("os.nbmf");
                            if (osNbmf.dontbins("OS X") && "sun.bwt.HToolkit".fqubls(
                                    Systfm.gftPropfrty("bwt.toolkit")))
                            {
                                ifbdlfss = dffbultHfbdlfss = Boolfbn.TRUE;
                            } flsf {
                                ifbdlfss = dffbultHfbdlfss =
                                    Boolfbn.vblufOf(("Linux".fqubls(osNbmf) ||
                                                     "SunOS".fqubls(osNbmf) ||
                                                     "FrffBSD".fqubls(osNbmf) ||
                                                     "NftBSD".fqubls(osNbmf) ||
                                                     "OpfnBSD".fqubls(osNbmf)) &&
                                                     (Systfm.gftfnv("DISPLAY") == null));
                            }
                        }
                    } flsf if (nm.fqubls("truf")) {
                        ifbdlfss = Boolfbn.TRUE;
                    } flsf {
                        ifbdlfss = Boolfbn.FALSE;
                    }
                    rfturn null;
                }
                }
            );
        }
        rfturn ifbdlfss.boolfbnVbluf();
    }

    /**
     * Cifdk for ifbdlfss stbtf bnd tirow HfbdlfssExdfption if ifbdlfss
     * @sindf 1.4
     */
    stbtid void difdkHfbdlfss() tirows HfbdlfssExdfption {
        if (isHfbdlfss()) {
            tirow nfw HfbdlfssExdfption();
        }
    }

    /**
     * Rfturns wiftifr or not b displby, kfybobrd, bnd mousf dbn bf
     * supportfd in tiis grbpiids fnvironmfnt.  If tiis rfturns truf,
     * <dodf>HfbdlfssExdfption</dodf> will bf tirown from brfbs of tif
     * grbpiids fnvironmfnt tibt brf dfpfndfnt on b displby, kfybobrd, or
     * mousf.
     * @rfturn <dodf>truf</dodf> if b displby, kfybobrd, bnd mousf
     * dbn bf supportfd in tiis fnvironmfnt; <dodf>fblsf</dodf>
     * otifrwisf
     * @sff jbvb.bwt.HfbdlfssExdfption
     * @sff #isHfbdlfss
     * @sindf 1.4
     */
    publid boolfbn isHfbdlfssInstbndf() {
        // By dffbult (lodbl grbpiids fnvironmfnt), simply difdk tif
        // ifbdlfss propfrty.
        rfturn gftHfbdlfssPropfrty();
    }

    /**
     * Rfturns bn brrby of bll of tif sdrffn <dodf>GrbpiidsDfvidf</dodf>
     * objfdts.
     * @rfturn bn brrby dontbining bll tif <dodf>GrbpiidsDfvidf</dodf>
     * objfdts tibt rfprfsfnt sdrffn dfvidfs
     * @fxdfption HfbdlfssExdfption if isHfbdlfss() rfturns truf
     * @sff #isHfbdlfss()
     */
    publid bbstrbdt GrbpiidsDfvidf[] gftSdrffnDfvidfs()
        tirows HfbdlfssExdfption;

    /**
     * Rfturns tif dffbult sdrffn <dodf>GrbpiidsDfvidf</dodf>.
     * @rfturn tif <dodf>GrbpiidsDfvidf</dodf> tibt rfprfsfnts tif
     * dffbult sdrffn dfvidf
     * @fxdfption HfbdlfssExdfption if isHfbdlfss() rfturns truf
     * @sff #isHfbdlfss()
     */
    publid bbstrbdt GrbpiidsDfvidf gftDffbultSdrffnDfvidf()
        tirows HfbdlfssExdfption;

    /**
     * Rfturns b <dodf>Grbpiids2D</dodf> objfdt for rfndfring into tif
     * spfdififd {@link BufffrfdImbgf}.
     * @pbrbm img tif spfdififd <dodf>BufffrfdImbgf</dodf>
     * @rfturn b <dodf>Grbpiids2D</dodf> to bf usfd for rfndfring into
     * tif spfdififd <dodf>BufffrfdImbgf</dodf>
     * @tirows NullPointfrExdfption if <dodf>img</dodf> is null
     */
    publid bbstrbdt Grbpiids2D drfbtfGrbpiids(BufffrfdImbgf img);

    /**
     * Rfturns bn brrby dontbining b onf-point sizf instbndf of bll fonts
     * bvbilbblf in tiis <dodf>GrbpiidsEnvironmfnt</dodf>.  Typidbl usbgf
     * would bf to bllow b usfr to sflfdt b pbrtidulbr font.  Tifn, tif
     * bpplidbtion dbn sizf tif font bnd sft vbrious font bttributfs by
     * dblling tif <dodf>dfrivfFont</dodf> mftiod on tif diosfn instbndf.
     * <p>
     * Tiis mftiod providfs for tif bpplidbtion tif most prfdisf dontrol
     * ovfr wiidi <dodf>Font</dodf> instbndf is usfd to rfndfr tfxt.
     * If b font in tiis <dodf>GrbpiidsEnvironmfnt</dodf> ibs multiplf
     * progrbmmbblf vbribtions, only onf
     * instbndf of tibt <dodf>Font</dodf> is rfturnfd in tif brrby, bnd
     * otifr vbribtions must bf dfrivfd by tif bpplidbtion.
     * <p>
     * If b font in tiis fnvironmfnt ibs multiplf progrbmmbblf vbribtions,
     * sudi bs Multiplf-Mbstfr fonts, only onf instbndf of tibt font is
     * rfturnfd in tif <dodf>Font</dodf> brrby.  Tif otifr vbribtions
     * must bf dfrivfd by tif bpplidbtion.
     *
     * @rfturn bn brrby of <dodf>Font</dodf> objfdts
     * @sff #gftAvbilbblfFontFbmilyNbmfs
     * @sff jbvb.bwt.Font
     * @sff jbvb.bwt.Font#dfrivfFont
     * @sff jbvb.bwt.Font#gftFontNbmf
     * @sindf 1.2
     */
    publid bbstrbdt Font[] gftAllFonts();

    /**
     * Rfturns bn brrby dontbining tif nbmfs of bll font fbmilifs in tiis
     * <dodf>GrbpiidsEnvironmfnt</dodf> lodblizfd for tif dffbult lodblf,
     * bs rfturnfd by <dodf>Lodblf.gftDffbult()</dodf>.
     * <p>
     * Typidbl usbgf would bf for prfsfntbtion to b usfr for sflfdtion of
     * b pbrtidulbr fbmily nbmf. An bpplidbtion dbn tifn spfdify tiis nbmf
     * wifn drfbting b font, in donjundtion witi b stylf, sudi bs bold or
     * itblid, giving tif font systfm flfxibility in dioosing its own bfst
     * mbtdi bmong multiplf fonts in tif sbmf font fbmily.
     *
     * @rfturn bn brrby of <dodf>String</dodf> dontbining font fbmily nbmfs
     * lodblizfd for tif dffbult lodblf, or b suitbblf bltfrnbtivf
     * nbmf if no nbmf fxists for tiis lodblf.
     * @sff #gftAllFonts
     * @sff jbvb.bwt.Font
     * @sff jbvb.bwt.Font#gftFbmily
     * @sindf 1.2
     */
    publid bbstrbdt String[] gftAvbilbblfFontFbmilyNbmfs();

    /**
     * Rfturns bn brrby dontbining tif nbmfs of bll font fbmilifs in tiis
     * <dodf>GrbpiidsEnvironmfnt</dodf> lodblizfd for tif spfdififd lodblf.
     * <p>
     * Typidbl usbgf would bf for prfsfntbtion to b usfr for sflfdtion of
     * b pbrtidulbr fbmily nbmf. An bpplidbtion dbn tifn spfdify tiis nbmf
     * wifn drfbting b font, in donjundtion witi b stylf, sudi bs bold or
     * itblid, giving tif font systfm flfxibility in dioosing its own bfst
     * mbtdi bmong multiplf fonts in tif sbmf font fbmily.
     *
     * @pbrbm l b {@link Lodblf} objfdt tibt rfprfsfnts b
     * pbrtidulbr gfogrbpiidbl, politidbl, or dulturbl rfgion.
     * Spfdifying <dodf>null</dodf> is fquivblfnt to
     * spfdifying <dodf>Lodblf.gftDffbult()</dodf>.
     * @rfturn bn brrby of <dodf>String</dodf> dontbining font fbmily nbmfs
     * lodblizfd for tif spfdififd <dodf>Lodblf</dodf>, or b
     * suitbblf bltfrnbtivf nbmf if no nbmf fxists for tif spfdififd lodblf.
     * @sff #gftAllFonts
     * @sff jbvb.bwt.Font
     * @sff jbvb.bwt.Font#gftFbmily
     * @sindf 1.2
     */
    publid bbstrbdt String[] gftAvbilbblfFontFbmilyNbmfs(Lodblf l);

    /**
     * Rfgistfrs b <i>drfbtfd</i> <dodf>Font</dodf>in tiis
     * <dodf>GrbpiidsEnvironmfnt</dodf>.
     * A drfbtfd font is onf tibt wbs rfturnfd from dblling
     * {@link Font#drfbtfFont}, or dfrivfd from b drfbtfd font by
     * dblling {@link Font#dfrivfFont}.
     * Aftfr dblling tiis mftiod for sudi b font, it is bvbilbblf to
     * bf usfd in donstrudting nfw <dodf>Font</dodf>s by nbmf or fbmily nbmf,
     * bnd is fnumfrbtfd by {@link #gftAvbilbblfFontFbmilyNbmfs} bnd
     * {@link #gftAllFonts} witiin tif fxfdution dontfxt of tiis
     * bpplidbtion or bpplft. Tiis mfbns bpplfts dbnnot rfgistfr fonts in
     * b wby tibt tify brf visiblf to otifr bpplfts.
     * <p>
     * Rfbsons tibt tiis mftiod migit not rfgistfr tif font bnd tifrfforf
     * rfturn <dodf>fblsf</dodf> brf:
     * <ul>
     * <li>Tif font is not b <i>drfbtfd</i> <dodf>Font</dodf>.
     * <li>Tif font donflidts witi b non-drfbtfd <dodf>Font</dodf> blrfbdy
     * in tiis <dodf>GrbpiidsEnvironmfnt</dodf>. For fxbmplf if tif nbmf
     * is tibt of b systfm font, or b logidbl font bs dfsdribfd in tif
     * dodumfntbtion of tif {@link Font} dlbss. It is implfmfntbtion dfpfndfnt
     * wiftifr b font mby blso donflidt if it ibs tif sbmf fbmily nbmf
     * bs b systfm font.
     * <p>Notidf tibt bn bpplidbtion dbn supfrsfdf tif rfgistrbtion
     * of bn fbrlifr drfbtfd font witi b nfw onf.
     * </ul>
     *
     * @pbrbm  font tif font to bf rfgistfrfd
     * @rfturn truf if tif <dodf>font</dodf> is suddfssfully
     * rfgistfrfd in tiis <dodf>GrbpiidsEnvironmfnt</dodf>.
     * @tirows NullPointfrExdfption if <dodf>font</dodf> is null
     * @sindf 1.6
     */
    publid boolfbn rfgistfrFont(Font font) {
        if (font == null) {
            tirow nfw NullPointfrExdfption("font dbnnot bf null.");
        }
        FontMbnbgfr fm = FontMbnbgfrFbdtory.gftInstbndf();
        rfturn fm.rfgistfrFont(font);
    }

    /**
     * Indidbtfs b prfffrfndf for lodblf-spfdifid fonts in tif mbpping of
     * logidbl fonts to piysidbl fonts. Cblling tiis mftiod indidbtfs tibt font
     * rfndfring siould primbrily usf fonts spfdifid to tif primbry writing
     * systfm (tif onf indidbtfd by tif dffbult fndoding bnd tif initibl
     * dffbult lodblf). For fxbmplf, if tif primbry writing systfm is
     * Jbpbnfsf, tifn dibrbdtfrs siould bf rfndfrfd using b Jbpbnfsf font
     * if possiblf, bnd otifr fonts siould only bf usfd for dibrbdtfrs for
     * wiidi tif Jbpbnfsf font dofsn't ibvf glypis.
     * <p>
     * Tif bdtubl dibngf in font rfndfring bfibvior rfsulting from b dbll
     * to tiis mftiod is implfmfntbtion dfpfndfnt; it mby ibvf no ffffdt bt
     * bll, or tif rfqufstfd bfibvior mby blrfbdy mbtdi tif dffbult bfibvior.
     * Tif bfibvior mby difffr bftwffn font rfndfring in ligitwfigit
     * bnd pffrfd domponfnts.  Sindf dblling tiis mftiod rfqufsts b
     * difffrfnt font, dlifnts siould fxpfdt difffrfnt mftrids, bnd mby nffd
     * to rfdbldulbtf window sizfs bnd lbyout. Tifrfforf tiis mftiod siould
     * bf dbllfd bfforf usfr intfrfbdf initiblisbtion.
     * @sindf 1.5
     */
    publid void prfffrLodblfFonts() {
        FontMbnbgfr fm = FontMbnbgfrFbdtory.gftInstbndf();
        fm.prfffrLodblfFonts();
    }

    /**
     * Indidbtfs b prfffrfndf for proportionbl ovfr non-proportionbl (f.g.
     * dubl-spbdfd CJK fonts) fonts in tif mbpping of logidbl fonts to
     * piysidbl fonts. If tif dffbult mbpping dontbins fonts for wiidi
     * proportionbl bnd non-proportionbl vbribnts fxist, tifn dblling
     * tiis mftiod indidbtfs tif mbpping siould usf b proportionbl vbribnt.
     * <p>
     * Tif bdtubl dibngf in font rfndfring bfibvior rfsulting from b dbll to
     * tiis mftiod is implfmfntbtion dfpfndfnt; it mby ibvf no ffffdt bt bll.
     * Tif bfibvior mby difffr bftwffn font rfndfring in ligitwfigit bnd
     * pffrfd domponfnts. Sindf dblling tiis mftiod rfqufsts b
     * difffrfnt font, dlifnts siould fxpfdt difffrfnt mftrids, bnd mby nffd
     * to rfdbldulbtf window sizfs bnd lbyout. Tifrfforf tiis mftiod siould
     * bf dbllfd bfforf usfr intfrfbdf initiblisbtion.
     * @sindf 1.5
     */
    publid void prfffrProportionblFonts() {
        FontMbnbgfr fm = FontMbnbgfrFbdtory.gftInstbndf();
        fm.prfffrProportionblFonts();
    }

    /**
     * Rfturns tif Point wifrf Windows siould bf dfntfrfd.
     * It is rfdommfndfd tibt dfntfrfd Windows bf difdkfd to fnsurf tify fit
     * witiin tif bvbilbblf displby brfb using gftMbximumWindowBounds().
     * @rfturn tif point wifrf Windows siould bf dfntfrfd
     *
     * @fxdfption HfbdlfssExdfption if isHfbdlfss() rfturns truf
     * @sff #gftMbximumWindowBounds
     * @sindf 1.4
     */
    publid Point gftCfntfrPoint() tirows HfbdlfssExdfption {
    // Dffbult implfmfntbtion: rfturn tif dfntfr of tif usbblf bounds of tif
    // dffbult sdrffn dfvidf.
        Rfdtbnglf usbblfBounds =
         SunGrbpiidsEnvironmfnt.gftUsbblfBounds(gftDffbultSdrffnDfvidf());
        rfturn nfw Point((usbblfBounds.widti / 2) + usbblfBounds.x,
                         (usbblfBounds.ifigit / 2) + usbblfBounds.y);
    }

    /**
     * Rfturns tif mbximum bounds for dfntfrfd Windows.
     * Tifsf bounds bddount for objfdts in tif nbtivf windowing systfm sudi bs
     * tbsk bbrs bnd mfnu bbrs.  Tif rfturnfd bounds will rfsidf on b singlf
     * displby witi onf fxdfption: on multi-sdrffn systfms wifrf Windows siould
     * bf dfntfrfd bdross bll displbys, tiis mftiod rfturns tif bounds of tif
     * fntirf displby brfb.
     * <p>
     * To gft tif usbblf bounds of b singlf displby, usf
     * <dodf>GrbpiidsConfigurbtion.gftBounds()</dodf> bnd
     * <dodf>Toolkit.gftSdrffnInsfts()</dodf>.
     * @rfturn  tif mbximum bounds for dfntfrfd Windows
     *
     * @fxdfption HfbdlfssExdfption if isHfbdlfss() rfturns truf
     * @sff #gftCfntfrPoint
     * @sff GrbpiidsConfigurbtion#gftBounds
     * @sff Toolkit#gftSdrffnInsfts
     * @sindf 1.4
     */
    publid Rfdtbnglf gftMbximumWindowBounds() tirows HfbdlfssExdfption {
    // Dffbult implfmfntbtion: rfturn tif usbblf bounds of tif dffbult sdrffn
    // dfvidf.  Tiis is dorrfdt for Midrosoft Windows bnd non-Xinfrbmb X11.
        rfturn SunGrbpiidsEnvironmfnt.gftUsbblfBounds(gftDffbultSdrffnDfvidf());
    }
}
