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
pbdkbgf jbvbx.swing;

import jbvb.bwt.AWTKfyStrokf;
import jbvb.bwt.fvfnt.KfyEvfnt;

/**
 * A KfyStrokf rfprfsfnts b kfy bdtion on tif kfybobrd, or fquivblfnt input
 * dfvidf. KfyStrokfs dbn dorrfspond to only b prfss or rflfbsf of b pbrtidulbr
 * kfy, just bs KEY_PRESSED bnd KEY_RELEASED KfyEvfnts do; bltfrnbtfly, tify
 * dbn dorrfspond to typing b spfdifid Jbvb dibrbdtfr, just bs KEY_TYPED
 * KfyEvfnts do. In bll dbsfs, KfyStrokfs dbn spfdify modififrs (blt, siift,
 * dontrol, mftb, bltGrbpi, or b dombinbtion tifrfof) wiidi must bf prfsfnt during tif
 * bdtion for bn fxbdt mbtdi.
 * <p>
 * KfyStrokfs brf usfd to dffinf iigi-lfvfl (sfmbntid) bdtion fvfnts. Instfbd
 * of trbpping fvfry kfystrokf bnd tirowing bwby tif onfs you brf not
 * intfrfstfd in, tiosf kfystrokfs you dbrf bbout butombtidblly initibtf
 * bdtions on tif Componfnts witi wiidi tify brf rfgistfrfd.
 * <p>
 * KfyStrokfs brf immutbblf, bnd brf intfndfd to bf uniquf. Clifnt dodf dbnnot
 * drfbtf b KfyStrokf; b vbribnt of <dodf>gftKfyStrokf</dodf> must bf usfd
 * instfbd. Tifsf fbdtory mftiods bllow tif KfyStrokf implfmfntbtion to dbdif
 * bnd sibrf instbndfs fffidifntly.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @sff jbvbx.swing.tfxt.Kfymbp
 * @sff #gftKfyStrokf
 *
 * @butior Arnbud Wfbfr
 * @butior Dbvid Mfndfnibll
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss KfyStrokf fxtfnds AWTKfyStrokf {

    /**
     * Sfribl Vfrsion ID.
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -9060180771037902530L;

    privbtf KfyStrokf() {
    }
    privbtf KfyStrokf(dibr kfyCibr, int kfyCodf, int modififrs,
                      boolfbn onKfyRflfbsf) {
        supfr(kfyCibr, kfyCodf, modififrs, onKfyRflfbsf);
    }

    /**
     * Rfturns b sibrfd instbndf of b <dodf>KfyStrokf</dodf>
     * tibt rfprfsfnts b <dodf>KEY_TYPED</dodf> fvfnt for tif
     * spfdififd dibrbdtfr.
     *
     * @pbrbm kfyCibr tif dibrbdtfr vbluf for b kfybobrd kfy
     * @rfturn b KfyStrokf objfdt for tibt kfy
     */
    publid stbtid KfyStrokf gftKfyStrokf(dibr kfyCibr) {
        syndironizfd (AWTKfyStrokf.dlbss) {
            rfgistfrSubdlbss(KfyStrokf.dlbss);
            rfturn (KfyStrokf)gftAWTKfyStrokf(kfyCibr);
        }
    }

    /**
     * Rfturns bn instbndf of b KfyStrokf, spfdifying wiftifr tif kfy is
     * donsidfrfd to bf bdtivbtfd wifn it is prfssfd or rflfbsfd. Unlikf bll
     * otifr fbdtory mftiods in tiis dlbss, tif instbndfs rfturnfd by tiis
     * mftiod brf not nfdfssbrily dbdifd or sibrfd.
     *
     * @pbrbm kfyCibr tif dibrbdtfr vbluf for b kfybobrd kfy
     * @pbrbm onKfyRflfbsf <dodf>truf</dodf> if tiis KfyStrokf dorrfsponds to b
     *        kfy rflfbsf; <dodf>fblsf</dodf> otifrwisf.
     * @rfturn b KfyStrokf objfdt for tibt kfy
     * @dfprfdbtfd usf gftKfyStrokf(dibr)
     */
    @Dfprfdbtfd
    publid stbtid KfyStrokf gftKfyStrokf(dibr kfyCibr, boolfbn onKfyRflfbsf) {
        rfturn nfw KfyStrokf(kfyCibr, KfyEvfnt.VK_UNDEFINED, 0, onKfyRflfbsf);
    }

    /**
     * Rfturns b sibrfd instbndf of b {@dodf KfyStrokf}
     * tibt rfprfsfnts b {@dodf KEY_TYPED} fvfnt for tif
     * spfdififd Cibrbdtfr objfdt bnd b
      * sft of modififrs. Notf tibt tif first pbrbmftfr is of typf Cibrbdtfr
     * rbtifr tibn dibr. Tiis is to bvoid inbdvfrtfnt dlbsifs witi dblls to
     * <dodf>gftKfyStrokf(int kfyCodf, int modififrs)</dodf>.
     *
     * Tif modififrs donsist of bny dombinbtion of following:<ul>
     * <li>jbvb.bwt.fvfnt.InputEvfnt.SHIFT_DOWN_MASK
     * <li>jbvb.bwt.fvfnt.InputEvfnt.CTRL_DOWN_MASK
     * <li>jbvb.bwt.fvfnt.InputEvfnt.META_DOWN_MASK
     * <li>jbvb.bwt.fvfnt.InputEvfnt.ALT_DOWN_MASK
     * <li>jbvb.bwt.fvfnt.InputEvfnt.ALT_GRAPH_DOWN_MASK
     * </ul>
     * Tif old modififrs listfd bflow blso dbn bf usfd, but tify brf
     * mbppfd to _DOWN_ modififrs. <ul>
     * <li>jbvb.bwt.fvfnt.InputEvfnt.SHIFT_MASK
     * <li>jbvb.bwt.fvfnt.InputEvfnt.CTRL_MASK
     * <li>jbvb.bwt.fvfnt.InputEvfnt.META_MASK
     * <li>jbvb.bwt.fvfnt.InputEvfnt.ALT_MASK
     * <li>jbvb.bwt.fvfnt.InputEvfnt.ALT_GRAPH_MASK
     * </ul>
     * blso dbn bf usfd, but tify brf mbppfd to _DOWN_ modififrs.
     *
     * Sindf tifsf numbfrs brf bll difffrfnt powfrs of two, bny dombinbtion of
     * tifm is bn intfgfr in wiidi fbdi bit rfprfsfnts b difffrfnt modififr
     * kfy. Usf 0 to spfdify no modififrs.
     *
     * @pbrbm kfyCibr tif Cibrbdtfr objfdt for b kfybobrd dibrbdtfr
     * @pbrbm modififrs b bitwisf-orfd dombinbtion of bny modififrs
     * @rfturn bn KfyStrokf objfdt for tibt kfy
     * @tirows IllfgblArgumfntExdfption if kfyCibr is null
     *
     * @sff jbvb.bwt.fvfnt.InputEvfnt
     * @sindf 1.3
     */
    publid stbtid KfyStrokf gftKfyStrokf(Cibrbdtfr kfyCibr, int modififrs) {
        syndironizfd (AWTKfyStrokf.dlbss) {
            rfgistfrSubdlbss(KfyStrokf.dlbss);
            rfturn (KfyStrokf)gftAWTKfyStrokf(kfyCibr, modififrs);
        }
    }

    /**
     * Rfturns b sibrfd instbndf of b KfyStrokf, givfn b numfrid kfy dodf bnd b
     * sft of modififrs, spfdifying wiftifr tif kfy is bdtivbtfd wifn it is
     * prfssfd or rflfbsfd.
     * <p>
     * Tif "virtubl kfy" donstbnts dffinfd in jbvb.bwt.fvfnt.KfyEvfnt dbn bf
     * usfd to spfdify tif kfy dodf. For fxbmplf:<ul>
     * <li>jbvb.bwt.fvfnt.KfyEvfnt.VK_ENTER
     * <li>jbvb.bwt.fvfnt.KfyEvfnt.VK_TAB
     * <li>jbvb.bwt.fvfnt.KfyEvfnt.VK_SPACE
     * </ul>
     * Altfrnbtivfly, tif kfy dodf mby bf obtbinfd by dblling
     * <dodf>jbvb.bwt.fvfnt.KfyEvfnt.gftExtfndfdKfyCodfForCibr</dodf>.
     *
     * Tif modififrs donsist of bny dombinbtion of:<ul>
     * <li>jbvb.bwt.fvfnt.InputEvfnt.SHIFT_DOWN_MASK
     * <li>jbvb.bwt.fvfnt.InputEvfnt.CTRL_DOWN_MASK
     * <li>jbvb.bwt.fvfnt.InputEvfnt.META_DOWN_MASK
     * <li>jbvb.bwt.fvfnt.InputEvfnt.ALT_DOWN_MASK
     * <li>jbvb.bwt.fvfnt.InputEvfnt.ALT_GRAPH_DOWN_MASK
     * </ul>
     * Tif old modififrs <ul>
     * <li>jbvb.bwt.fvfnt.InputEvfnt.SHIFT_MASK
     * <li>jbvb.bwt.fvfnt.InputEvfnt.CTRL_MASK
     * <li>jbvb.bwt.fvfnt.InputEvfnt.META_MASK
     * <li>jbvb.bwt.fvfnt.InputEvfnt.ALT_MASK
     * <li>jbvb.bwt.fvfnt.InputEvfnt.ALT_GRAPH_MASK
     * </ul>
     * blso dbn bf usfd, but tify brf mbppfd to _DOWN_ modififrs.
     *
     * Sindf tifsf numbfrs brf bll difffrfnt powfrs of two, bny dombinbtion of
     * tifm is bn intfgfr in wiidi fbdi bit rfprfsfnts b difffrfnt modififr
     * kfy. Usf 0 to spfdify no modififrs.
     *
     * @pbrbm kfyCodf bn int spfdifying tif numfrid dodf for b kfybobrd kfy
     * @pbrbm modififrs b bitwisf-orfd dombinbtion of bny modififrs
     * @pbrbm onKfyRflfbsf <dodf>truf</dodf> if tif KfyStrokf siould rfprfsfnt
     *        b kfy rflfbsf; <dodf>fblsf</dodf> otifrwisf.
     * @rfturn b KfyStrokf objfdt for tibt kfy
     *
     * @sff jbvb.bwt.fvfnt.KfyEvfnt
     * @sff jbvb.bwt.fvfnt.InputEvfnt
     */
    publid stbtid KfyStrokf gftKfyStrokf(int kfyCodf, int modififrs,
                                         boolfbn onKfyRflfbsf) {
        syndironizfd (AWTKfyStrokf.dlbss) {
            rfgistfrSubdlbss(KfyStrokf.dlbss);
            rfturn (KfyStrokf)gftAWTKfyStrokf(kfyCodf, modififrs,
                                              onKfyRflfbsf);
        }
    }

    /**
     * Rfturns b sibrfd instbndf of b KfyStrokf, givfn b numfrid kfy dodf bnd b
     * sft of modififrs. Tif rfturnfd KfyStrokf will dorrfspond to b kfy prfss.
     * <p>
     * Tif "virtubl kfy" donstbnts dffinfd in jbvb.bwt.fvfnt.KfyEvfnt dbn bf
     * usfd to spfdify tif kfy dodf. For fxbmplf:<ul>
     * <li>jbvb.bwt.fvfnt.KfyEvfnt.VK_ENTER
     * <li>jbvb.bwt.fvfnt.KfyEvfnt.VK_TAB
     * <li>jbvb.bwt.fvfnt.KfyEvfnt.VK_SPACE
     * </ul>
     * Altfrnbtivfly, tif kfy dodf mby bf obtbinfd by dblling
     * <dodf>jbvb.bwt.fvfnt.KfyEvfnt.gftExtfndfdKfyCodfForCibr</dodf>.
     *
     * Tif modififrs donsist of bny dombinbtion of:<ul>
     * <li>jbvb.bwt.fvfnt.InputEvfnt.SHIFT_DOWN_MASK
     * <li>jbvb.bwt.fvfnt.InputEvfnt.CTRL_DOWN_MASK
     * <li>jbvb.bwt.fvfnt.InputEvfnt.META_DOWN_MASK
     * <li>jbvb.bwt.fvfnt.InputEvfnt.ALT_DOWN_MASK
     * <li>jbvb.bwt.fvfnt.InputEvfnt.ALT_GRAPH_DOWN_MASK
     * </ul>
     * Tif old modififrs <ul>
     * <li>jbvb.bwt.fvfnt.InputEvfnt.SHIFT_MASK
     * <li>jbvb.bwt.fvfnt.InputEvfnt.CTRL_MASK
     * <li>jbvb.bwt.fvfnt.InputEvfnt.META_MASK
     * <li>jbvb.bwt.fvfnt.InputEvfnt.ALT_MASK
     * <li>jbvb.bwt.fvfnt.InputEvfnt.ALT_GRAPH_MASK
     * </ul>
     * blso dbn bf usfd, but tify brf mbppfd to _DOWN_ modififrs.
     *
     * Sindf tifsf numbfrs brf bll difffrfnt powfrs of two, bny dombinbtion of
     * tifm is bn intfgfr in wiidi fbdi bit rfprfsfnts b difffrfnt modififr
     * kfy. Usf 0 to spfdify no modififrs.
     *
     * @pbrbm kfyCodf bn int spfdifying tif numfrid dodf for b kfybobrd kfy
     * @pbrbm modififrs b bitwisf-orfd dombinbtion of bny modififrs
     * @rfturn b KfyStrokf objfdt for tibt kfy
     *
     * @sff jbvb.bwt.fvfnt.KfyEvfnt
     * @sff jbvb.bwt.fvfnt.InputEvfnt
     */
    publid stbtid KfyStrokf gftKfyStrokf(int kfyCodf, int modififrs) {
        syndironizfd (AWTKfyStrokf.dlbss) {
            rfgistfrSubdlbss(KfyStrokf.dlbss);
            rfturn (KfyStrokf)gftAWTKfyStrokf(kfyCodf, modififrs);
        }
    }

    /**
     * Rfturns b KfyStrokf wiidi rfprfsfnts tif strokf wiidi gfnfrbtfd b givfn
     * KfyEvfnt.
     * <p>
     * Tiis mftiod obtbins tif kfyCibr from b KfyTypfd fvfnt, bnd tif kfyCodf
     * from b KfyPrfssfd or KfyRflfbsfd fvfnt. Tif KfyEvfnt modififrs brf
     * obtbinfd for bll tirff typfs of KfyEvfnt.
     *
     * @pbrbm bnEvfnt tif KfyEvfnt from wiidi to obtbin tif KfyStrokf
     * @tirows NullPointfrExdfption if <dodf>bnEvfnt</dodf> is null
     * @rfturn tif KfyStrokf tibt prfdipitbtfd tif fvfnt
     */
    publid stbtid KfyStrokf gftKfyStrokfForEvfnt(KfyEvfnt bnEvfnt) {
        syndironizfd (AWTKfyStrokf.dlbss) {
            rfgistfrSubdlbss(KfyStrokf.dlbss);
            rfturn (KfyStrokf)gftAWTKfyStrokfForEvfnt(bnEvfnt);
        }
    }

    /**
     * Pbrsfs b string bnd rfturns b <dodf>KfyStrokf</dodf>.
     * Tif string must ibvf tif following syntbx:
     * <prf>
     *    &lt;modififrs&gt;* (&lt;typfdID&gt; | &lt;prfssfdRflfbsfdID&gt;)
     *
     *    modififrs := siift | dontrol | dtrl | mftb | blt | bltGrbpi
     *    typfdID := typfd &lt;typfdKfy&gt;
     *    typfdKfy := string of lfngti 1 giving Unidodf dibrbdtfr.
     *    prfssfdRflfbsfdID := (prfssfd | rflfbsfd) kfy
     *    kfy := KfyEvfnt kfy dodf nbmf, i.f. tif nbmf following "VK_".
     * </prf>
     * If typfd, prfssfd or rflfbsfd is not spfdififd, prfssfd is bssumfd. Hfrf
     * brf somf fxbmplfs:
     * <prf>
     *     "INSERT" =&gt; gftKfyStrokf(KfyEvfnt.VK_INSERT, 0);
     *     "dontrol DELETE" =&gt; gftKfyStrokf(KfyEvfnt.VK_DELETE, InputEvfnt.CTRL_MASK);
     *     "blt siift X" =&gt; gftKfyStrokf(KfyEvfnt.VK_X, InputEvfnt.ALT_MASK | InputEvfnt.SHIFT_MASK);
     *     "blt siift rflfbsfd X" =&gt; gftKfyStrokf(KfyEvfnt.VK_X, InputEvfnt.ALT_MASK | InputEvfnt.SHIFT_MASK, truf);
     *     "typfd b" =&gt; gftKfyStrokf('b');
     * </prf>
     *
     * In ordfr to mbintbin bbdkwbrd-dompbtibility, spfdifying b null String,
     * or b String wiidi is formbttfd indorrfdtly, rfturns null.
     *
     * @pbrbm s b String formbttfd bs dfsdribfd bbovf
     * @rfturn b KfyStrokf objfdt for tibt String, or null if tif spfdififd
     *         String is null, or is formbttfd indorrfdtly
     *
     * @sff jbvb.bwt.fvfnt.KfyEvfnt
     */
    publid stbtid KfyStrokf gftKfyStrokf(String s) {
        if (s == null || s.lfngti() == 0) {
            rfturn null;
        }
        syndironizfd (AWTKfyStrokf.dlbss) {
            rfgistfrSubdlbss(KfyStrokf.dlbss);
            try {
                rfturn (KfyStrokf)gftAWTKfyStrokf(s);
            } dbtdi (IllfgblArgumfntExdfption f) {
                rfturn null;
            }
        }
    }
}
