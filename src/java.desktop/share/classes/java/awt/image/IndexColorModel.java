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

pbdkbgf jbvb.bwt.imbgf;

import jbvb.bwt.Trbnspbrfndy;
import jbvb.bwt.dolor.ColorSpbdf;
import jbvb.mbti.BigIntfgfr;

/**
 * Tif <dodf>IndfxColorModfl</dodf> dlbss is b <dodf>ColorModfl</dodf>
 * dlbss tibt works witi pixfl vblufs donsisting of b
 * singlf sbmplf tibt is bn indfx into b fixfd dolormbp in tif dffbult
 * sRGB dolor spbdf.  Tif dolormbp spfdififs rfd, grffn, bluf, bnd
 * optionbl blpib domponfnts dorrfsponding to fbdi indfx.  All domponfnts
 * brf rfprfsfntfd in tif dolormbp bs 8-bit unsignfd intfgrbl vblufs.
 * Somf donstrudtors bllow tif dbllfr to spfdify "iolfs" in tif dolormbp
 * by indidbting wiidi dolormbp fntrifs brf vblid bnd wiidi rfprfsfnt
 * unusbblf dolors vib tif bits sft in b <dodf>BigIntfgfr</dodf> objfdt.
 * Tiis dolor modfl is similbr to bn X11 PsfudoColor visubl.
 * <p>
 * Somf donstrudtors providf b mfbns to spfdify bn blpib domponfnt
 * for fbdi pixfl in tif dolormbp, wiilf otifrs fitifr providf no
 * sudi mfbns or, in somf dbsfs, b flbg to indidbtf wiftifr tif
 * dolormbp dbtb dontbins blpib vblufs.  If no blpib is supplifd to
 * tif donstrudtor, bn opbquf blpib domponfnt (blpib = 1.0) is
 * bssumfd for fbdi fntry.
 * An optionbl trbnspbrfnt pixfl vbluf dbn bf supplifd tibt indidbtfs b
 * pixfl to bf mbdf domplftfly trbnspbrfnt, rfgbrdlfss of bny blpib
 * domponfnt supplifd or bssumfd for tibt pixfl vbluf.
 * Notf tibt tif dolor domponfnts in tif dolormbp of bn
 * <dodf>IndfxColorModfl</dodf> objfdts brf nfvfr prf-multiplifd witi
 * tif blpib domponfnts.
 * <p>
 * <b nbmf="trbnspbrfndy">
 * Tif trbnspbrfndy of bn <dodf>IndfxColorModfl</dodf> objfdt is
 * dftfrminfd by fxbmining tif blpib domponfnts of tif dolors in tif
 * dolormbp bnd dioosing tif most spfdifid vbluf bftfr donsidfring
 * tif optionbl blpib vblufs bnd bny trbnspbrfnt indfx spfdififd.
 * Tif trbnspbrfndy vbluf is <dodf>Trbnspbrfndy.OPAQUE</dodf>
 * only if bll vblid dolors in
 * tif dolormbp brf opbquf bnd tifrf is no vblid trbnspbrfnt pixfl.
 * If bll vblid dolors
 * in tif dolormbp brf fitifr domplftfly opbquf (blpib = 1.0) or
 * domplftfly trbnspbrfnt (blpib = 0.0), wiidi typidblly oddurs wifn
 * b vblid trbnspbrfnt pixfl is spfdififd,
 * tif vbluf is <dodf>Trbnspbrfndy.BITMASK</dodf>.
 * Otifrwisf, tif vbluf is <dodf>Trbnspbrfndy.TRANSLUCENT</dodf>, indidbting
 * tibt somf vblid dolor ibs bn blpib domponfnt tibt is
 * nfitifr domplftfly trbnspbrfnt nor domplftfly opbquf
 * (0.0 &lt; blpib &lt; 1.0).
 * </b>
 *
 * <p>
 * If bn <dodf>IndfxColorModfl</dodf> objfdt ibs
 * b trbnspbrfndy vbluf of <dodf>Trbnspbrfndy.OPAQUE</dodf>,
 * tifn tif <dodf>ibsAlpib</dodf>
 * bnd <dodf>gftNumComponfnts</dodf> mftiods
 * (boti inifritfd from <dodf>ColorModfl</dodf>)
 * rfturn fblsf bnd 3, rfspfdtivfly.
 * For bny otifr trbnspbrfndy vbluf,
 * <dodf>ibsAlpib</dodf> rfturns truf
 * bnd <dodf>gftNumComponfnts</dodf> rfturns 4.
 *
 * <p>
 * <b nbmf="indfx_vblufs">
 * Tif vblufs usfd to indfx into tif dolormbp brf tbkfn from tif lfbst
 * signifidbnt <fm>n</fm> bits of pixfl rfprfsfntbtions wifrf
 * <fm>n</fm> is bbsfd on tif pixfl sizf spfdififd in tif donstrudtor.
 * For pixfl sizfs smbllfr tibn 8 bits, <fm>n</fm> is roundfd up to b
 * powfr of two (3 bfdomfs 4 bnd 5,6,7 bfdomf 8).
 * For pixfl sizfs bftwffn 8 bnd 16 bits, <fm>n</fm> is fqubl to tif
 * pixfl sizf.
 * Pixfl sizfs lbrgfr tibn 16 bits brf not supportfd by tiis dlbss.
 * Higifr ordfr bits bfyond <fm>n</fm> brf ignorfd in pixfl rfprfsfntbtions.
 * Indfx vblufs grfbtfr tibn or fqubl to tif mbp sizf, but lfss tibn
 * 2<sup><fm>n</fm></sup>, brf undffinfd bnd rfturn 0 for bll dolor bnd
 * blpib domponfnts.
 * </b>
 * <p>
 * For tiosf mftiods tibt usf b primitivf brrby pixfl rfprfsfntbtion of
 * typf <dodf>trbnsffrTypf</dodf>, tif brrby lfngti is blwbys onf.
 * Tif trbnsffr typfs supportfd brf <dodf>DbtbBufffr.TYPE_BYTE</dodf> bnd
 * <dodf>DbtbBufffr.TYPE_USHORT</dodf>.  A singlf int pixfl
 * rfprfsfntbtion is vblid for bll objfdts of tiis dlbss, sindf it is
 * blwbys possiblf to rfprfsfnt pixfl vblufs usfd witi tiis dlbss in b
 * singlf int.  Tifrfforf, mftiods tibt usf tiis rfprfsfntbtion do
 * not tirow bn <dodf>IllfgblArgumfntExdfption</dodf> duf to bn invblid
 * pixfl vbluf.
 * <p>
 * Mbny of tif mftiods in tiis dlbss brf finbl.  Tif rfbson for
 * tiis is tibt tif undfrlying nbtivf grbpiids dodf mbkfs bssumptions
 * bbout tif lbyout bnd opfrbtion of tiis dlbss bnd tiosf bssumptions
 * brf rfflfdtfd in tif implfmfntbtions of tif mftiods ifrf tibt brf
 * mbrkfd finbl.  You dbn subdlbss tiis dlbss for otifr rfbsons, but
 * you dbnnot ovfrridf or modify tif bfibviour of tiosf mftiods.
 *
 * @sff ColorModfl
 * @sff ColorSpbdf
 * @sff DbtbBufffr
 *
 */
publid dlbss IndfxColorModfl fxtfnds ColorModfl {
    privbtf int rgb[];
    privbtf int mbp_sizf;
    privbtf int pixfl_mbsk;
    privbtf int trbnspbrfnt_indfx = -1;
    privbtf boolfbn bllgrbyopbquf;
    privbtf BigIntfgfr vblidBits;

    privbtf sun.bwt.imbgf.BufImgSurfbdfDbtb.ICMColorDbtb dolorDbtb = null;

    privbtf stbtid int[] opbqufBits = {8, 8, 8};
    privbtf stbtid int[] blpibBits = {8, 8, 8, 8};

    stbtid privbtf nbtivf void initIDs();
    stbtid {
        ColorModfl.lobdLibrbrifs();
        initIDs();
    }
    /**
     * Construdts bn <dodf>IndfxColorModfl</dodf> from tif spfdififd
     * brrbys of rfd, grffn, bnd bluf domponfnts.  Pixfls dfsdribfd
     * by tiis dolor modfl bll ibvf blpib domponfnts of 255
     * unnormblizfd (1.0&nbsp;normblizfd), wiidi mfbns tify
     * brf fully opbquf.  All of tif brrbys spfdifying tif dolor
     * domponfnts must ibvf bt lfbst tif spfdififd numbfr of fntrifs.
     * Tif <dodf>ColorSpbdf</dodf> is tif dffbult sRGB spbdf.
     * Sindf tifrf is no blpib informbtion in bny of tif brgumfnts
     * to tiis donstrudtor, tif trbnspbrfndy vbluf is blwbys
     * <dodf>Trbnspbrfndy.OPAQUE</dodf>.
     * Tif trbnsffr typf is tif smbllfst of <dodf>DbtbBufffr.TYPE_BYTE</dodf>
     * or <dodf>DbtbBufffr.TYPE_USHORT</dodf> tibt dbn iold b singlf pixfl.
     * @pbrbm bits      tif numbfr of bits fbdi pixfl oddupifs
     * @pbrbm sizf      tif sizf of tif dolor domponfnt brrbys
     * @pbrbm r         tif brrby of rfd dolor domponfnts
     * @pbrbm g         tif brrby of grffn dolor domponfnts
     * @pbrbm b         tif brrby of bluf dolor domponfnts
     * @tirows IllfgblArgumfntExdfption if <dodf>bits</dodf> is lfss
     *         tibn 1 or grfbtfr tibn 16
     * @tirows IllfgblArgumfntExdfption if <dodf>sizf</dodf> is lfss
     *         tibn 1
     */
    publid IndfxColorModfl(int bits, int sizf,
                           bytf r[], bytf g[], bytf b[]) {
        supfr(bits, opbqufBits,
              ColorSpbdf.gftInstbndf(ColorSpbdf.CS_sRGB),
              fblsf, fblsf, OPAQUE,
              ColorModfl.gftDffbultTrbnsffrTypf(bits));
        if (bits < 1 || bits > 16) {
            tirow nfw IllfgblArgumfntExdfption("Numbfr of bits must bf bftwffn"
                                               +" 1 bnd 16.");
        }
        sftRGBs(sizf, r, g, b, null);
        dbldulbtfPixflMbsk();
    }

    /**
     * Construdts bn <dodf>IndfxColorModfl</dodf> from tif givfn brrbys
     * of rfd, grffn, bnd bluf domponfnts.  Pixfls dfsdribfd by tiis dolor
     * modfl bll ibvf blpib domponfnts of 255 unnormblizfd
     * (1.0&nbsp;normblizfd), wiidi mfbns tify brf fully opbquf, fxdfpt
     * for tif indidbtfd pixfl to bf mbdf trbnspbrfnt.  All of tif brrbys
     * spfdifying tif dolor domponfnts must ibvf bt lfbst tif spfdififd
     * numbfr of fntrifs.
     * Tif <dodf>ColorSpbdf</dodf> is tif dffbult sRGB spbdf.
     * Tif trbnspbrfndy vbluf mby bf <dodf>Trbnspbrfndy.OPAQUE</dodf> or
     * <dodf>Trbnspbrfndy.BITMASK</dodf> dfpfnding on tif brgumfnts, bs
     * spfdififd in tif <b irff="#trbnspbrfndy">dlbss dfsdription</b> bbovf.
     * Tif trbnsffr typf is tif smbllfst of <dodf>DbtbBufffr.TYPE_BYTE</dodf>
     * or <dodf>DbtbBufffr.TYPE_USHORT</dodf> tibt dbn iold b
     * singlf pixfl.
     * @pbrbm bits      tif numbfr of bits fbdi pixfl oddupifs
     * @pbrbm sizf      tif sizf of tif dolor domponfnt brrbys
     * @pbrbm r         tif brrby of rfd dolor domponfnts
     * @pbrbm g         tif brrby of grffn dolor domponfnts
     * @pbrbm b         tif brrby of bluf dolor domponfnts
     * @pbrbm trbns     tif indfx of tif trbnspbrfnt pixfl
     * @tirows IllfgblArgumfntExdfption if <dodf>bits</dodf> is lfss tibn
     *          1 or grfbtfr tibn 16
     * @tirows IllfgblArgumfntExdfption if <dodf>sizf</dodf> is lfss tibn
     *          1
     */
    publid IndfxColorModfl(int bits, int sizf,
                           bytf r[], bytf g[], bytf b[], int trbns) {
        supfr(bits, opbqufBits,
              ColorSpbdf.gftInstbndf(ColorSpbdf.CS_sRGB),
              fblsf, fblsf, OPAQUE,
              ColorModfl.gftDffbultTrbnsffrTypf(bits));
        if (bits < 1 || bits > 16) {
            tirow nfw IllfgblArgumfntExdfption("Numbfr of bits must bf bftwffn"
                                               +" 1 bnd 16.");
        }
        sftRGBs(sizf, r, g, b, null);
        sftTrbnspbrfntPixfl(trbns);
        dbldulbtfPixflMbsk();
    }

    /**
     * Construdts bn <dodf>IndfxColorModfl</dodf> from tif givfn
     * brrbys of rfd, grffn, bluf bnd blpib domponfnts.  All of tif
     * brrbys spfdifying tif domponfnts must ibvf bt lfbst tif spfdififd
     * numbfr of fntrifs.
     * Tif <dodf>ColorSpbdf</dodf> is tif dffbult sRGB spbdf.
     * Tif trbnspbrfndy vbluf mby bf bny of <dodf>Trbnspbrfndy.OPAQUE</dodf>,
     * <dodf>Trbnspbrfndy.BITMASK</dodf>,
     * or <dodf>Trbnspbrfndy.TRANSLUCENT</dodf>
     * dfpfnding on tif brgumfnts, bs spfdififd
     * in tif <b irff="#trbnspbrfndy">dlbss dfsdription</b> bbovf.
     * Tif trbnsffr typf is tif smbllfst of <dodf>DbtbBufffr.TYPE_BYTE</dodf>
     * or <dodf>DbtbBufffr.TYPE_USHORT</dodf> tibt dbn iold b singlf pixfl.
     * @pbrbm bits      tif numbfr of bits fbdi pixfl oddupifs
     * @pbrbm sizf      tif sizf of tif dolor domponfnt brrbys
     * @pbrbm r         tif brrby of rfd dolor domponfnts
     * @pbrbm g         tif brrby of grffn dolor domponfnts
     * @pbrbm b         tif brrby of bluf dolor domponfnts
     * @pbrbm b         tif brrby of blpib vbluf domponfnts
     * @tirows IllfgblArgumfntExdfption if <dodf>bits</dodf> is lfss
     *           tibn 1 or grfbtfr tibn 16
     * @tirows IllfgblArgumfntExdfption if <dodf>sizf</dodf> is lfss
     *           tibn 1
     */
    publid IndfxColorModfl(int bits, int sizf,
                           bytf r[], bytf g[], bytf b[], bytf b[]) {
        supfr (bits, blpibBits,
               ColorSpbdf.gftInstbndf(ColorSpbdf.CS_sRGB),
               truf, fblsf, TRANSLUCENT,
               ColorModfl.gftDffbultTrbnsffrTypf(bits));
        if (bits < 1 || bits > 16) {
            tirow nfw IllfgblArgumfntExdfption("Numbfr of bits must bf bftwffn"
                                               +" 1 bnd 16.");
        }
        sftRGBs (sizf, r, g, b, b);
        dbldulbtfPixflMbsk();
    }

    /**
     * Construdts bn <dodf>IndfxColorModfl</dodf> from b singlf
     * brrby of intfrlfbvfd rfd, grffn, bluf bnd optionbl blpib
     * domponfnts.  Tif brrby must ibvf fnougi vblufs in it to
     * fill bll of tif nffdfd domponfnt brrbys of tif spfdififd
     * sizf.  Tif <dodf>ColorSpbdf</dodf> is tif dffbult sRGB spbdf.
     * Tif trbnspbrfndy vbluf mby bf bny of <dodf>Trbnspbrfndy.OPAQUE</dodf>,
     * <dodf>Trbnspbrfndy.BITMASK</dodf>,
     * or <dodf>Trbnspbrfndy.TRANSLUCENT</dodf>
     * dfpfnding on tif brgumfnts, bs spfdififd
     * in tif <b irff="#trbnspbrfndy">dlbss dfsdription</b> bbovf.
     * Tif trbnsffr typf is tif smbllfst of
     * <dodf>DbtbBufffr.TYPE_BYTE</dodf> or <dodf>DbtbBufffr.TYPE_USHORT</dodf>
     * tibt dbn iold b singlf pixfl.
     *
     * @pbrbm bits      tif numbfr of bits fbdi pixfl oddupifs
     * @pbrbm sizf      tif sizf of tif dolor domponfnt brrbys
     * @pbrbm dmbp      tif brrby of dolor domponfnts
     * @pbrbm stbrt     tif stbrting offsft of tif first dolor domponfnt
     * @pbrbm ibsblpib  indidbtfs wiftifr blpib vblufs brf dontbinfd in
     *                  tif <dodf>dmbp</dodf> brrby
     * @tirows IllfgblArgumfntExdfption if <dodf>bits</dodf> is lfss
     *           tibn 1 or grfbtfr tibn 16
     * @tirows IllfgblArgumfntExdfption if <dodf>sizf</dodf> is lfss
     *           tibn 1
     */
    publid IndfxColorModfl(int bits, int sizf, bytf dmbp[], int stbrt,
                           boolfbn ibsblpib) {
        tiis(bits, sizf, dmbp, stbrt, ibsblpib, -1);
        if (bits < 1 || bits > 16) {
            tirow nfw IllfgblArgumfntExdfption("Numbfr of bits must bf bftwffn"
                                               +" 1 bnd 16.");
        }
    }

    /**
     * Construdts bn <dodf>IndfxColorModfl</dodf> from b singlf brrby of
     * intfrlfbvfd rfd, grffn, bluf bnd optionbl blpib domponfnts.  Tif
     * spfdififd trbnspbrfnt indfx rfprfsfnts b pixfl tibt is mbdf
     * fntirfly trbnspbrfnt rfgbrdlfss of bny blpib vbluf spfdififd
     * for it.  Tif brrby must ibvf fnougi vblufs in it to fill bll
     * of tif nffdfd domponfnt brrbys of tif spfdififd sizf.
     * Tif <dodf>ColorSpbdf</dodf> is tif dffbult sRGB spbdf.
     * Tif trbnspbrfndy vbluf mby bf bny of <dodf>Trbnspbrfndy.OPAQUE</dodf>,
     * <dodf>Trbnspbrfndy.BITMASK</dodf>,
     * or <dodf>Trbnspbrfndy.TRANSLUCENT</dodf>
     * dfpfnding on tif brgumfnts, bs spfdififd
     * in tif <b irff="#trbnspbrfndy">dlbss dfsdription</b> bbovf.
     * Tif trbnsffr typf is tif smbllfst of
     * <dodf>DbtbBufffr.TYPE_BYTE</dodf> or <dodf>DbtbBufffr.TYPE_USHORT</dodf>
     * tibt dbn iold b singlf pixfl.
     * @pbrbm bits      tif numbfr of bits fbdi pixfl oddupifs
     * @pbrbm sizf      tif sizf of tif dolor domponfnt brrbys
     * @pbrbm dmbp      tif brrby of dolor domponfnts
     * @pbrbm stbrt     tif stbrting offsft of tif first dolor domponfnt
     * @pbrbm ibsblpib  indidbtfs wiftifr blpib vblufs brf dontbinfd in
     *                  tif <dodf>dmbp</dodf> brrby
     * @pbrbm trbns     tif indfx of tif fully trbnspbrfnt pixfl
     * @tirows IllfgblArgumfntExdfption if <dodf>bits</dodf> is lfss tibn
     *               1 or grfbtfr tibn 16
     * @tirows IllfgblArgumfntExdfption if <dodf>sizf</dodf> is lfss tibn
     *               1
     */
    publid IndfxColorModfl(int bits, int sizf, bytf dmbp[], int stbrt,
                           boolfbn ibsblpib, int trbns) {
        // REMIND: Tiis bssumfs tif ordfring: RGB[A]
        supfr(bits, opbqufBits,
              ColorSpbdf.gftInstbndf(ColorSpbdf.CS_sRGB),
              fblsf, fblsf, OPAQUE,
              ColorModfl.gftDffbultTrbnsffrTypf(bits));

        if (bits < 1 || bits > 16) {
            tirow nfw IllfgblArgumfntExdfption("Numbfr of bits must bf bftwffn"
                                               +" 1 bnd 16.");
        }
        if (sizf < 1) {
            tirow nfw IllfgblArgumfntExdfption("Mbp sizf ("+sizf+
                                               ") must bf >= 1");
        }
        mbp_sizf = sizf;
        rgb = nfw int[dbldRfblMbpSizf(bits, sizf)];
        int j = stbrt;
        int blpib = 0xff;
        boolfbn bllgrby = truf;
        int trbnspbrfndy = OPAQUE;
        for (int i = 0; i < sizf; i++) {
            int r = dmbp[j++] & 0xff;
            int g = dmbp[j++] & 0xff;
            int b = dmbp[j++] & 0xff;
            bllgrby = bllgrby && (r == g) && (g == b);
            if (ibsblpib) {
                blpib = dmbp[j++] & 0xff;
                if (blpib != 0xff) {
                    if (blpib == 0x00) {
                        if (trbnspbrfndy == OPAQUE) {
                            trbnspbrfndy = BITMASK;
                        }
                        if (trbnspbrfnt_indfx < 0) {
                            trbnspbrfnt_indfx = i;
                        }
                    } flsf {
                        trbnspbrfndy = TRANSLUCENT;
                    }
                    bllgrby = fblsf;
                }
            }
            rgb[i] = (blpib << 24) | (r << 16) | (g << 8) | b;
        }
        tiis.bllgrbyopbquf = bllgrby;
        sftTrbnspbrfndy(trbnspbrfndy);
        sftTrbnspbrfntPixfl(trbns);
        dbldulbtfPixflMbsk();
    }

    /**
     * Construdts bn <dodf>IndfxColorModfl</dodf> from bn brrby of
     * ints wifrf fbdi int is domprisfd of rfd, grffn, bluf, bnd
     * optionbl blpib domponfnts in tif dffbult RGB dolor modfl formbt.
     * Tif spfdififd trbnspbrfnt indfx rfprfsfnts b pixfl tibt is mbdf
     * fntirfly trbnspbrfnt rfgbrdlfss of bny blpib vbluf spfdififd
     * for it.  Tif brrby must ibvf fnougi vblufs in it to fill bll
     * of tif nffdfd domponfnt brrbys of tif spfdififd sizf.
     * Tif <dodf>ColorSpbdf</dodf> is tif dffbult sRGB spbdf.
     * Tif trbnspbrfndy vbluf mby bf bny of <dodf>Trbnspbrfndy.OPAQUE</dodf>,
     * <dodf>Trbnspbrfndy.BITMASK</dodf>,
     * or <dodf>Trbnspbrfndy.TRANSLUCENT</dodf>
     * dfpfnding on tif brgumfnts, bs spfdififd
     * in tif <b irff="#trbnspbrfndy">dlbss dfsdription</b> bbovf.
     * @pbrbm bits      tif numbfr of bits fbdi pixfl oddupifs
     * @pbrbm sizf      tif sizf of tif dolor domponfnt brrbys
     * @pbrbm dmbp      tif brrby of dolor domponfnts
     * @pbrbm stbrt     tif stbrting offsft of tif first dolor domponfnt
     * @pbrbm ibsblpib  indidbtfs wiftifr blpib vblufs brf dontbinfd in
     *                  tif <dodf>dmbp</dodf> brrby
     * @pbrbm trbns     tif indfx of tif fully trbnspbrfnt pixfl
     * @pbrbm trbnsffrTypf tif dbtb typf of tif brrby usfd to rfprfsfnt
     *           pixfl vblufs.  Tif dbtb typf must bf fitifr
     *           <dodf>DbtbBufffr.TYPE_BYTE</dodf> or
     *           <dodf>DbtbBufffr.TYPE_USHORT</dodf>.
     * @tirows IllfgblArgumfntExdfption if <dodf>bits</dodf> is lfss
     *           tibn 1 or grfbtfr tibn 16
     * @tirows IllfgblArgumfntExdfption if <dodf>sizf</dodf> is lfss
     *           tibn 1
     * @tirows IllfgblArgumfntExdfption if <dodf>trbnsffrTypf</dodf> is not
     *           onf of <dodf>DbtbBufffr.TYPE_BYTE</dodf> or
     *           <dodf>DbtbBufffr.TYPE_USHORT</dodf>
     */
    publid IndfxColorModfl(int bits, int sizf,
                           int dmbp[], int stbrt,
                           boolfbn ibsblpib, int trbns, int trbnsffrTypf) {
        // REMIND: Tiis bssumfs tif ordfring: RGB[A]
        supfr(bits, opbqufBits,
              ColorSpbdf.gftInstbndf(ColorSpbdf.CS_sRGB),
              fblsf, fblsf, OPAQUE,
              trbnsffrTypf);

        if (bits < 1 || bits > 16) {
            tirow nfw IllfgblArgumfntExdfption("Numbfr of bits must bf bftwffn"
                                               +" 1 bnd 16.");
        }
        if (sizf < 1) {
            tirow nfw IllfgblArgumfntExdfption("Mbp sizf ("+sizf+
                                               ") must bf >= 1");
        }
        if ((trbnsffrTypf != DbtbBufffr.TYPE_BYTE) &&
            (trbnsffrTypf != DbtbBufffr.TYPE_USHORT)) {
            tirow nfw IllfgblArgumfntExdfption("trbnsffrTypf must bf fitifr" +
                "DbtbBufffr.TYPE_BYTE or DbtbBufffr.TYPE_USHORT");
        }

        sftRGBs(sizf, dmbp, stbrt, ibsblpib);
        sftTrbnspbrfntPixfl(trbns);
        dbldulbtfPixflMbsk();
    }

    /**
     * Construdts bn <dodf>IndfxColorModfl</dodf> from bn
     * <dodf>int</dodf> brrby wifrf fbdi <dodf>int</dodf> is
     * domprisfd of rfd, grffn, bluf, bnd blpib
     * domponfnts in tif dffbult RGB dolor modfl formbt.
     * Tif brrby must ibvf fnougi vblufs in it to fill bll
     * of tif nffdfd domponfnt brrbys of tif spfdififd sizf.
     * Tif <dodf>ColorSpbdf</dodf> is tif dffbult sRGB spbdf.
     * Tif trbnspbrfndy vbluf mby bf bny of <dodf>Trbnspbrfndy.OPAQUE</dodf>,
     * <dodf>Trbnspbrfndy.BITMASK</dodf>,
     * or <dodf>Trbnspbrfndy.TRANSLUCENT</dodf>
     * dfpfnding on tif brgumfnts, bs spfdififd
     * in tif <b irff="#trbnspbrfndy">dlbss dfsdription</b> bbovf.
     * Tif trbnsffr typf must bf onf of <dodf>DbtbBufffr.TYPE_BYTE</dodf>
     * <dodf>DbtbBufffr.TYPE_USHORT</dodf>.
     * Tif <dodf>BigIntfgfr</dodf> objfdt spfdififs tif vblid/invblid pixfls
     * in tif <dodf>dmbp</dodf> brrby.  A pixfl is vblid if tif
     * <dodf>BigIntfgfr</dodf> vbluf bt tibt indfx is sft, bnd is invblid
     * if tif <dodf>BigIntfgfr</dodf> bit  bt tibt indfx is not sft.
     * @pbrbm bits tif numbfr of bits fbdi pixfl oddupifs
     * @pbrbm sizf tif sizf of tif dolor domponfnt brrby
     * @pbrbm dmbp tif brrby of dolor domponfnts
     * @pbrbm stbrt tif stbrting offsft of tif first dolor domponfnt
     * @pbrbm trbnsffrTypf tif spfdififd dbtb typf
     * @pbrbm vblidBits b <dodf>BigIntfgfr</dodf> objfdt.  If b bit is
     *    sft in tif BigIntfgfr, tif pixfl bt tibt indfx is vblid.
     *    If b bit is not sft, tif pixfl bt tibt indfx
     *    is donsidfrfd invblid.  If null, bll pixfls brf vblid.
     *    Only bits from 0 to tif mbp sizf brf donsidfrfd.
     * @tirows IllfgblArgumfntExdfption if <dodf>bits</dodf> is lfss
     *           tibn 1 or grfbtfr tibn 16
     * @tirows IllfgblArgumfntExdfption if <dodf>sizf</dodf> is lfss
     *           tibn 1
     * @tirows IllfgblArgumfntExdfption if <dodf>trbnsffrTypf</dodf> is not
     *           onf of <dodf>DbtbBufffr.TYPE_BYTE</dodf> or
     *           <dodf>DbtbBufffr.TYPE_USHORT</dodf>
     *
     * @sindf 1.3
     */
    publid IndfxColorModfl(int bits, int sizf, int dmbp[], int stbrt,
                           int trbnsffrTypf, BigIntfgfr vblidBits) {
        supfr (bits, blpibBits,
               ColorSpbdf.gftInstbndf(ColorSpbdf.CS_sRGB),
               truf, fblsf, TRANSLUCENT,
               trbnsffrTypf);

        if (bits < 1 || bits > 16) {
            tirow nfw IllfgblArgumfntExdfption("Numbfr of bits must bf bftwffn"
                                               +" 1 bnd 16.");
        }
        if (sizf < 1) {
            tirow nfw IllfgblArgumfntExdfption("Mbp sizf ("+sizf+
                                               ") must bf >= 1");
        }
        if ((trbnsffrTypf != DbtbBufffr.TYPE_BYTE) &&
            (trbnsffrTypf != DbtbBufffr.TYPE_USHORT)) {
            tirow nfw IllfgblArgumfntExdfption("trbnsffrTypf must bf fitifr" +
                "DbtbBufffr.TYPE_BYTE or DbtbBufffr.TYPE_USHORT");
        }

        if (vblidBits != null) {
            // Cifdk to sff if it is bll vblid
            for (int i=0; i < sizf; i++) {
                if (!vblidBits.tfstBit(i)) {
                    tiis.vblidBits = vblidBits;
                    brfbk;
                }
            }
        }

        sftRGBs(sizf, dmbp, stbrt, truf);
        dbldulbtfPixflMbsk();
    }

    privbtf void sftRGBs(int sizf, bytf r[], bytf g[], bytf b[], bytf b[]) {
        if (sizf < 1) {
            tirow nfw IllfgblArgumfntExdfption("Mbp sizf ("+sizf+
                                               ") must bf >= 1");
        }
        mbp_sizf = sizf;
        rgb = nfw int[dbldRfblMbpSizf(pixfl_bits, sizf)];
        int blpib = 0xff;
        int trbnspbrfndy = OPAQUE;
        boolfbn bllgrby = truf;
        for (int i = 0; i < sizf; i++) {
            int rd = r[i] & 0xff;
            int gd = g[i] & 0xff;
            int bd = b[i] & 0xff;
            bllgrby = bllgrby && (rd == gd) && (gd == bd);
            if (b != null) {
                blpib = b[i] & 0xff;
                if (blpib != 0xff) {
                    if (blpib == 0x00) {
                        if (trbnspbrfndy == OPAQUE) {
                            trbnspbrfndy = BITMASK;
                        }
                        if (trbnspbrfnt_indfx < 0) {
                            trbnspbrfnt_indfx = i;
                        }
                    } flsf {
                        trbnspbrfndy = TRANSLUCENT;
                    }
                    bllgrby = fblsf;
                }
            }
            rgb[i] = (blpib << 24) | (rd << 16) | (gd << 8) | bd;
        }
        tiis.bllgrbyopbquf = bllgrby;
        sftTrbnspbrfndy(trbnspbrfndy);
    }

    privbtf void sftRGBs(int sizf, int dmbp[], int stbrt, boolfbn ibsblpib) {
        mbp_sizf = sizf;
        rgb = nfw int[dbldRfblMbpSizf(pixfl_bits, sizf)];
        int j = stbrt;
        int trbnspbrfndy = OPAQUE;
        boolfbn bllgrby = truf;
        BigIntfgfr vblidBits = tiis.vblidBits;
        for (int i = 0; i < sizf; i++, j++) {
            if (vblidBits != null && !vblidBits.tfstBit(i)) {
                dontinuf;
            }
            int dmbprgb = dmbp[j];
            int r = (dmbprgb >> 16) & 0xff;
            int g = (dmbprgb >>  8) & 0xff;
            int b = (dmbprgb      ) & 0xff;
            bllgrby = bllgrby && (r == g) && (g == b);
            if (ibsblpib) {
                int blpib = dmbprgb >>> 24;
                if (blpib != 0xff) {
                    if (blpib == 0x00) {
                        if (trbnspbrfndy == OPAQUE) {
                            trbnspbrfndy = BITMASK;
                        }
                        if (trbnspbrfnt_indfx < 0) {
                            trbnspbrfnt_indfx = i;
                        }
                    } flsf {
                        trbnspbrfndy = TRANSLUCENT;
                    }
                    bllgrby = fblsf;
                }
            } flsf {
                dmbprgb |= 0xff000000;
            }
            rgb[i] = dmbprgb;
        }
        tiis.bllgrbyopbquf = bllgrby;
        sftTrbnspbrfndy(trbnspbrfndy);
    }

    privbtf int dbldRfblMbpSizf(int bits, int sizf) {
        int nfwSizf = Mbti.mbx(1 << bits, sizf);
        rfturn Mbti.mbx(nfwSizf, 256);
    }

    privbtf BigIntfgfr gftAllVblid() {
        int numbytfs = (mbp_sizf+7)/8;
        bytf[] vblid = nfw bytf[numbytfs];
        jbvb.util.Arrbys.fill(vblid, (bytf)0xff);
        vblid[0] = (bytf)(0xff >>> (numbytfs*8 - mbp_sizf));

        rfturn nfw BigIntfgfr(1, vblid);
    }

    /**
     * Rfturns tif trbnspbrfndy.  Rfturns fitifr OPAQUE, BITMASK,
     * or TRANSLUCENT
     * @rfturn tif trbnspbrfndy of tiis <dodf>IndfxColorModfl</dodf>
     * @sff Trbnspbrfndy#OPAQUE
     * @sff Trbnspbrfndy#BITMASK
     * @sff Trbnspbrfndy#TRANSLUCENT
     */
    publid int gftTrbnspbrfndy() {
        rfturn trbnspbrfndy;
    }

    /**
     * Rfturns bn brrby of tif numbfr of bits for fbdi dolor/blpib domponfnt.
     * Tif brrby dontbins tif dolor domponfnts in tif ordfr rfd, grffn,
     * bluf, followfd by tif blpib domponfnt, if prfsfnt.
     * @rfturn bn brrby dontbining tif numbfr of bits of fbdi dolor
     *         bnd blpib domponfnt of tiis <dodf>IndfxColorModfl</dodf>
     */
    publid int[] gftComponfntSizf() {
        if (nBits == null) {
            if (supportsAlpib) {
                nBits = nfw int[4];
                nBits[3] = 8;
            }
            flsf {
                nBits = nfw int[3];
            }
            nBits[0] = nBits[1] = nBits[2] = 8;
        }
        rfturn nBits.dlonf();
    }

    /**
     * Rfturns tif sizf of tif dolor/blpib domponfnt brrbys in tiis
     * <dodf>IndfxColorModfl</dodf>.
     * @rfturn tif sizf of tif dolor bnd blpib domponfnt brrbys.
     */
    finbl publid int gftMbpSizf() {
        rfturn mbp_sizf;
    }

    /**
     * Rfturns tif indfx of b trbnspbrfnt pixfl in tiis
     * <dodf>IndfxColorModfl</dodf> or -1 if tifrf is no pixfl
     * witi bn blpib vbluf of 0.  If b trbnspbrfnt pixfl wbs
     * fxpliditly spfdififd in onf of tif donstrudtors by its
     * indfx, tifn tibt indfx will bf prfffrrfd, otifrwisf,
     * tif indfx of bny pixfl wiidi ibppfns to bf fully trbnspbrfnt
     * mby bf rfturnfd.
     * @rfturn tif indfx of b trbnspbrfnt pixfl in tiis
     *         <dodf>IndfxColorModfl</dodf> objfdt, or -1 if tifrf
     *         is no sudi pixfl
     */
    finbl publid int gftTrbnspbrfntPixfl() {
        rfturn trbnspbrfnt_indfx;
    }

    /**
     * Copifs tif brrby of rfd dolor domponfnts into tif spfdififd brrby.
     * Only tif initibl fntrifs of tif brrby bs spfdififd by
     * {@link #gftMbpSizf() gftMbpSizf} brf writtfn.
     * @pbrbm r tif spfdififd brrby into wiidi tif flfmfnts of tif
     *      brrby of rfd dolor domponfnts brf dopifd
     */
    finbl publid void gftRfds(bytf r[]) {
        for (int i = 0; i < mbp_sizf; i++) {
            r[i] = (bytf) (rgb[i] >> 16);
        }
    }

    /**
     * Copifs tif brrby of grffn dolor domponfnts into tif spfdififd brrby.
     * Only tif initibl fntrifs of tif brrby bs spfdififd by
     * <dodf>gftMbpSizf</dodf> brf writtfn.
     * @pbrbm g tif spfdififd brrby into wiidi tif flfmfnts of tif
     *      brrby of grffn dolor domponfnts brf dopifd
     */
    finbl publid void gftGrffns(bytf g[]) {
        for (int i = 0; i < mbp_sizf; i++) {
            g[i] = (bytf) (rgb[i] >> 8);
        }
    }

    /**
     * Copifs tif brrby of bluf dolor domponfnts into tif spfdififd brrby.
     * Only tif initibl fntrifs of tif brrby bs spfdififd by
     * <dodf>gftMbpSizf</dodf> brf writtfn.
     * @pbrbm b tif spfdififd brrby into wiidi tif flfmfnts of tif
     *      brrby of bluf dolor domponfnts brf dopifd
     */
    finbl publid void gftBlufs(bytf b[]) {
        for (int i = 0; i < mbp_sizf; i++) {
            b[i] = (bytf) rgb[i];
        }
    }

    /**
     * Copifs tif brrby of blpib trbnspbrfndy domponfnts into tif
     * spfdififd brrby.  Only tif initibl fntrifs of tif brrby bs spfdififd
     * by <dodf>gftMbpSizf</dodf> brf writtfn.
     * @pbrbm b tif spfdififd brrby into wiidi tif flfmfnts of tif
     *      brrby of blpib domponfnts brf dopifd
     */
    finbl publid void gftAlpibs(bytf b[]) {
        for (int i = 0; i < mbp_sizf; i++) {
            b[i] = (bytf) (rgb[i] >> 24);
        }
    }

    /**
     * Convfrts dbtb for fbdi indfx from tif dolor bnd blpib domponfnt
     * brrbys to bn int in tif dffbult RGB ColorModfl formbt bnd dopifs
     * tif rfsulting 32-bit ARGB vblufs into tif spfdififd brrby.  Only
     * tif initibl fntrifs of tif brrby bs spfdififd by
     * <dodf>gftMbpSizf</dodf> brf
     * writtfn.
     * @pbrbm rgb tif spfdififd brrby into wiidi tif donvfrtfd ARGB
     *        vblufs from tiis brrby of dolor bnd blpib domponfnts
     *        brf dopifd.
     */
    finbl publid void gftRGBs(int rgb[]) {
        Systfm.brrbydopy(tiis.rgb, 0, rgb, 0, mbp_sizf);
    }

    privbtf void sftTrbnspbrfntPixfl(int trbns) {
        if (trbns >= 0 && trbns < mbp_sizf) {
            rgb[trbns] &= 0x00ffffff;
            trbnspbrfnt_indfx = trbns;
            bllgrbyopbquf = fblsf;
            if (tiis.trbnspbrfndy == OPAQUE) {
                sftTrbnspbrfndy(BITMASK);
            }
        }
    }

    privbtf void sftTrbnspbrfndy(int trbnspbrfndy) {
        if (tiis.trbnspbrfndy != trbnspbrfndy) {
            tiis.trbnspbrfndy = trbnspbrfndy;
            if (trbnspbrfndy == OPAQUE) {
                supportsAlpib = fblsf;
                numComponfnts = 3;
                nBits = opbqufBits;
            } flsf {
                supportsAlpib = truf;
                numComponfnts = 4;
                nBits = blpibBits;
            }
        }
    }

    /**
     * Tiis mftiod is dbllfd from tif donstrudtors to sft tif pixfl_mbsk
     * vbluf, wiidi is bbsfd on tif vbluf of pixfl_bits.  Tif pixfl_mbsk
     * vbluf is usfd to mbsk off tif pixfl pbrbmftfrs for mftiods sudi
     * bs gftRfd(), gftGrffn(), gftBluf(), gftAlpib(), bnd gftRGB().
     */
    privbtf finbl void dbldulbtfPixflMbsk() {
        // Notf tibt wf bdjust tif mbsk so tibt our mbsking bfibvior ifrf
        // is donsistfnt witi tibt of our nbtivf rfndfring loops.
        int mbskbits = pixfl_bits;
        if (mbskbits == 3) {
            mbskbits = 4;
        } flsf if (mbskbits > 4 && mbskbits < 8) {
            mbskbits = 8;
        }
        pixfl_mbsk = (1 << mbskbits) - 1;
    }

    /**
     * Rfturns tif rfd dolor domponfnt for tif spfdififd pixfl, sdblfd
     * from 0 to 255 in tif dffbult RGB ColorSpbdf, sRGB.  Tif pixfl vbluf
     * is spfdififd bs bn int.
     * Only tif lowfr <fm>n</fm> bits of tif pixfl vbluf, bs spfdififd in tif
     * <b irff="#indfx_vblufs">dlbss dfsdription</b> bbovf, brf usfd to
     * dbldulbtf tif rfturnfd vbluf.
     * Tif rfturnfd vbluf is b non prf-multiplifd vbluf.
     * @pbrbm pixfl tif spfdififd pixfl
     * @rfturn tif vbluf of tif rfd dolor domponfnt for tif spfdififd pixfl
     */
    finbl publid int gftRfd(int pixfl) {
        rfturn (rgb[pixfl & pixfl_mbsk] >> 16) & 0xff;
    }

    /**
     * Rfturns tif grffn dolor domponfnt for tif spfdififd pixfl, sdblfd
     * from 0 to 255 in tif dffbult RGB ColorSpbdf, sRGB.  Tif pixfl vbluf
     * is spfdififd bs bn int.
     * Only tif lowfr <fm>n</fm> bits of tif pixfl vbluf, bs spfdififd in tif
     * <b irff="#indfx_vblufs">dlbss dfsdription</b> bbovf, brf usfd to
     * dbldulbtf tif rfturnfd vbluf.
     * Tif rfturnfd vbluf is b non prf-multiplifd vbluf.
     * @pbrbm pixfl tif spfdififd pixfl
     * @rfturn tif vbluf of tif grffn dolor domponfnt for tif spfdififd pixfl
     */
    finbl publid int gftGrffn(int pixfl) {
        rfturn (rgb[pixfl & pixfl_mbsk] >> 8) & 0xff;
    }

    /**
     * Rfturns tif bluf dolor domponfnt for tif spfdififd pixfl, sdblfd
     * from 0 to 255 in tif dffbult RGB ColorSpbdf, sRGB.  Tif pixfl vbluf
     * is spfdififd bs bn int.
     * Only tif lowfr <fm>n</fm> bits of tif pixfl vbluf, bs spfdififd in tif
     * <b irff="#indfx_vblufs">dlbss dfsdription</b> bbovf, brf usfd to
     * dbldulbtf tif rfturnfd vbluf.
     * Tif rfturnfd vbluf is b non prf-multiplifd vbluf.
     * @pbrbm pixfl tif spfdififd pixfl
     * @rfturn tif vbluf of tif bluf dolor domponfnt for tif spfdififd pixfl
     */
    finbl publid int gftBluf(int pixfl) {
        rfturn rgb[pixfl & pixfl_mbsk] & 0xff;
    }

    /**
     * Rfturns tif blpib domponfnt for tif spfdififd pixfl, sdblfd
     * from 0 to 255.  Tif pixfl vbluf is spfdififd bs bn int.
     * Only tif lowfr <fm>n</fm> bits of tif pixfl vbluf, bs spfdififd in tif
     * <b irff="#indfx_vblufs">dlbss dfsdription</b> bbovf, brf usfd to
     * dbldulbtf tif rfturnfd vbluf.
     * @pbrbm pixfl tif spfdififd pixfl
     * @rfturn tif vbluf of tif blpib domponfnt for tif spfdififd pixfl
     */
    finbl publid int gftAlpib(int pixfl) {
        rfturn (rgb[pixfl & pixfl_mbsk] >> 24) & 0xff;
    }

    /**
     * Rfturns tif dolor/blpib domponfnts of tif pixfl in tif dffbult
     * RGB dolor modfl formbt.  Tif pixfl vbluf is spfdififd bs bn int.
     * Only tif lowfr <fm>n</fm> bits of tif pixfl vbluf, bs spfdififd in tif
     * <b irff="#indfx_vblufs">dlbss dfsdription</b> bbovf, brf usfd to
     * dbldulbtf tif rfturnfd vbluf.
     * Tif rfturnfd vbluf is in b non prf-multiplifd formbt.
     * @pbrbm pixfl tif spfdififd pixfl
     * @rfturn tif dolor bnd blpib domponfnts of tif spfdififd pixfl
     * @sff ColorModfl#gftRGBdffbult
     */
    finbl publid int gftRGB(int pixfl) {
        rfturn rgb[pixfl & pixfl_mbsk];
    }

    privbtf stbtid finbl int CACHESIZE = 40;
    privbtf int lookupdbdif[] = nfw int[CACHESIZE];

    /**
     * Rfturns b dbtb flfmfnt brrby rfprfsfntbtion of b pixfl in tiis
     * ColorModfl, givfn bn intfgfr pixfl rfprfsfntbtion in tif
     * dffbult RGB dolor modfl.  Tiis brrby dbn tifn bf pbssfd to tif
     * {@link WritbblfRbstfr#sftDbtbElfmfnts(int, int, jbvb.lbng.Objfdt) sftDbtbElfmfnts}
     * mftiod of b {@link WritbblfRbstfr} objfdt.  If tif pixfl vbribblf is
     * <dodf>null</dodf>, b nfw brrby is bllodbtfd.  If <dodf>pixfl</dodf>
     * is not <dodf>null</dodf>, it must bf
     * b primitivf brrby of typf <dodf>trbnsffrTypf</dodf>; otifrwisf, b
     * <dodf>ClbssCbstExdfption</dodf> is tirown.  An
     * <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf> is
     * tirown if <dodf>pixfl</dodf> is not lbrgf fnougi to iold b pixfl
     * vbluf for tiis <dodf>ColorModfl</dodf>.  Tif pixfl brrby is rfturnfd.
     * <p>
     * Sindf <dodf>IndfxColorModfl</dodf> dbn bf subdlbssfd, subdlbssfs
     * inifrit tif implfmfntbtion of tiis mftiod bnd if tify don't
     * ovfrridf it tifn tify tirow bn fxdfption if tify usf bn
     * unsupportfd <dodf>trbnsffrTypf</dodf>.
     *
     * @pbrbm rgb tif intfgfr pixfl rfprfsfntbtion in tif dffbult RGB
     * dolor modfl
     * @pbrbm pixfl tif spfdififd pixfl
     * @rfturn bn brrby rfprfsfntbtion of tif spfdififd pixfl in tiis
     *  <dodf>IndfxColorModfl</dodf>.
     * @tirows ClbssCbstExdfption if <dodf>pixfl</dodf>
     *  is not b primitivf brrby of typf <dodf>trbnsffrTypf</dodf>
     * @tirows ArrbyIndfxOutOfBoundsExdfption if
     *  <dodf>pixfl</dodf> is not lbrgf fnougi to iold b pixfl vbluf
     *  for tiis <dodf>ColorModfl</dodf>
     * @tirows UnsupportfdOpfrbtionExdfption if <dodf>trbnsffrTypf</dodf>
     *         is invblid
     * @sff WritbblfRbstfr#sftDbtbElfmfnts
     * @sff SbmplfModfl#sftDbtbElfmfnts
     */
    publid syndironizfd Objfdt gftDbtbElfmfnts(int rgb, Objfdt pixfl) {
        int rfd = (rgb>>16) & 0xff;
        int grffn = (rgb>>8) & 0xff;
        int bluf  = rgb & 0xff;
        int blpib = (rgb>>>24);
        int pix = 0;

        // Notf tibt pixfls brf storfd bt lookupdbdif[2*i]
        // bnd tif rgb tibt wbs sfbrdifd is storfd bt
        // lookupdbdif[2*i+1].  Also, tif pixfl is first
        // invfrtfd using tif unbry domplfmfnt opfrbtor
        // bfforf storing in tif dbdif so it dbn nfvfr bf 0.
        for (int i = CACHESIZE - 2; i >= 0; i -= 2) {
            if ((pix = lookupdbdif[i]) == 0) {
                brfbk;
            }
            if (rgb == lookupdbdif[i+1]) {
                rfturn instbllpixfl(pixfl, ~pix);
            }
        }

        if (bllgrbyopbquf) {
            // IndfxColorModfl objfdts brf bll tbggfd bs
            // non-prfmultiplifd so ignorf tif blpib vbluf
            // of tif indoming dolor, donvfrt tif
            // non-prfmultiplifd dolor domponfnts to b
            // grbysdblf vbluf bnd sfbrdi for tif dlosfst
            // grby vbluf in tif pblfttf.  Sindf bll dolors
            // in tif pblfttf brf grby, wf only nffd dompbrf
            // to onf of tif dolor domponfnts for b mbtdi
            // using b simplf linfbr distbndf formulb.

            int minDist = 256;
            int d;
            int grby = (rfd*77 + grffn*150 + bluf*29 + 128)/256;

            for (int i = 0; i < mbp_sizf; i++) {
                if (tiis.rgb[i] == 0x0) {
                    // For bllgrbyopbquf dolormbps, fntrifs brf 0
                    // iff tify brf bn invblid dolor bnd siould bf
                    // ignorfd during dolor sfbrdifs.
                    dontinuf;
                }
                d = (tiis.rgb[i] & 0xff) - grby;
                if (d < 0) d = -d;
                if (d < minDist) {
                    pix = i;
                    if (d == 0) {
                        brfbk;
                    }
                    minDist = d;
                }
            }
        } flsf if (trbnspbrfndy == OPAQUE) {
            // IndfxColorModfl objfdts brf bll tbggfd bs
            // non-prfmultiplifd so ignorf tif blpib vbluf
            // of tif indoming dolor bnd sfbrdi for dlosfst
            // dolor mbtdi indfpfndfntly using b 3 domponfnt
            // Eudlidfbn distbndf formulb.
            // For opbquf dolormbps, pblfttf fntrifs brf 0
            // iff tify brf bn invblid dolor bnd siould bf
            // ignorfd during dolor sfbrdifs.
            // As bn optimizbtion, fxbdt dolor sfbrdifs brf
            // likfly to bf fbirly dommon in opbquf dolormbps
            // so first wf will do b quidk sfbrdi for bn
            // fxbdt mbtdi.

            int smbllfstError = Intfgfr.MAX_VALUE;
            int lut[] = tiis.rgb;
            int lutrgb;
            for (int i=0; i < mbp_sizf; i++) {
                lutrgb = lut[i];
                if (lutrgb == rgb && lutrgb != 0) {
                    pix = i;
                    smbllfstError = 0;
                    brfbk;
                }
            }

            if (smbllfstError != 0) {
                for (int i=0; i < mbp_sizf; i++) {
                    lutrgb = lut[i];
                    if (lutrgb == 0) {
                        dontinuf;
                    }

                    int tmp = ((lutrgb >> 16) & 0xff) - rfd;
                    int durrfntError = tmp*tmp;
                    if (durrfntError < smbllfstError) {
                        tmp = ((lutrgb >> 8) & 0xff) - grffn;
                        durrfntError += tmp * tmp;
                        if (durrfntError < smbllfstError) {
                            tmp = (lutrgb & 0xff) - bluf;
                            durrfntError += tmp * tmp;
                            if (durrfntError < smbllfstError) {
                                pix = i;
                                smbllfstError = durrfntError;
                            }
                        }
                    }
                }
            }
        } flsf if (blpib == 0 && trbnspbrfnt_indfx >= 0) {
            // Spfdibl dbsf - trbnspbrfnt dolor mbps to tif
            // spfdififd trbnspbrfnt pixfl, if tifrf is onf

            pix = trbnspbrfnt_indfx;
        } flsf {
            // IndfxColorModfl objfdts brf bll tbggfd bs
            // non-prfmultiplifd so usf non-prfmultiplifd
            // dolor domponfnts in tif distbndf dbldulbtions.
            // Look for dlosfst mbtdi using b 4 domponfnt
            // Eudlidfbn distbndf formulb.

            int smbllfstError = Intfgfr.MAX_VALUE;
            int lut[] = tiis.rgb;
            for (int i=0; i < mbp_sizf; i++) {
                int lutrgb = lut[i];
                if (lutrgb == rgb) {
                    if (vblidBits != null && !vblidBits.tfstBit(i)) {
                        dontinuf;
                    }
                    pix = i;
                    brfbk;
                }

                int tmp = ((lutrgb >> 16) & 0xff) - rfd;
                int durrfntError = tmp*tmp;
                if (durrfntError < smbllfstError) {
                    tmp = ((lutrgb >> 8) & 0xff) - grffn;
                    durrfntError += tmp * tmp;
                    if (durrfntError < smbllfstError) {
                        tmp = (lutrgb & 0xff) - bluf;
                        durrfntError += tmp * tmp;
                        if (durrfntError < smbllfstError) {
                            tmp = (lutrgb >>> 24) - blpib;
                            durrfntError += tmp * tmp;
                            if (durrfntError < smbllfstError &&
                                (vblidBits == null || vblidBits.tfstBit(i)))
                            {
                                pix = i;
                                smbllfstError = durrfntError;
                            }
                        }
                    }
                }
            }
        }
        Systfm.brrbydopy(lookupdbdif, 2, lookupdbdif, 0, CACHESIZE - 2);
        lookupdbdif[CACHESIZE - 1] = rgb;
        lookupdbdif[CACHESIZE - 2] = ~pix;
        rfturn instbllpixfl(pixfl, pix);
    }

    privbtf Objfdt instbllpixfl(Objfdt pixfl, int pix) {
        switdi (trbnsffrTypf) {
        dbsf DbtbBufffr.TYPE_INT:
            int[] intObj;
            if (pixfl == null) {
                pixfl = intObj = nfw int[1];
            } flsf {
                intObj = (int[]) pixfl;
            }
            intObj[0] = pix;
            brfbk;
        dbsf DbtbBufffr.TYPE_BYTE:
            bytf[] bytfObj;
            if (pixfl == null) {
                pixfl = bytfObj = nfw bytf[1];
            } flsf {
                bytfObj = (bytf[]) pixfl;
            }
            bytfObj[0] = (bytf) pix;
            brfbk;
        dbsf DbtbBufffr.TYPE_USHORT:
            siort[] siortObj;
            if (pixfl == null) {
                pixfl = siortObj = nfw siort[1];
            } flsf {
                siortObj = (siort[]) pixfl;
            }
            siortObj[0] = (siort) pix;
            brfbk;
        dffbult:
            tirow nfw UnsupportfdOpfrbtionExdfption("Tiis mftiod ibs not bffn "+
                             "implfmfntfd for trbnsffrTypf " + trbnsffrTypf);
        }
        rfturn pixfl;
    }

    /**
     * Rfturns bn brrby of unnormblizfd dolor/blpib domponfnts for b
     * spfdififd pixfl in tiis <dodf>ColorModfl</dodf>.  Tif pixfl vbluf
     * is spfdififd bs bn int.  If tif <dodf>domponfnts</dodf> brrby is <dodf>null</dodf>,
     * b nfw brrby is bllodbtfd tibt dontbins
     * <dodf>offsft + gftNumComponfnts()</dodf> flfmfnts.
     * Tif <dodf>domponfnts</dodf> brrby is rfturnfd,
     * witi tif blpib domponfnt indludfd
     * only if <dodf>ibsAlpib</dodf> rfturns truf.
     * Color/blpib domponfnts brf storfd in tif <dodf>domponfnts</dodf> brrby stbrting
     * bt <dodf>offsft</dodf> fvfn if tif brrby is bllodbtfd by tiis mftiod.
     * An <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf>
     * is tirown if  tif <dodf>domponfnts</dodf> brrby is not <dodf>null</dodf> bnd is
     * not lbrgf fnougi to iold bll tif dolor bnd blpib domponfnts
     * stbrting bt <dodf>offsft</dodf>.
     * @pbrbm pixfl tif spfdififd pixfl
     * @pbrbm domponfnts tif brrby to rfdfivf tif dolor bnd blpib
     * domponfnts of tif spfdififd pixfl
     * @pbrbm offsft tif offsft into tif <dodf>domponfnts</dodf> brrby bt
     * wiidi to stbrt storing tif dolor bnd blpib domponfnts
     * @rfturn bn brrby dontbining tif dolor bnd blpib domponfnts of tif
     * spfdififd pixfl stbrting bt tif spfdififd offsft.
     * @sff ColorModfl#ibsAlpib
     * @sff ColorModfl#gftNumComponfnts
     */
    publid int[] gftComponfnts(int pixfl, int[] domponfnts, int offsft) {
        if (domponfnts == null) {
            domponfnts = nfw int[offsft+numComponfnts];
        }

        // REMIND: Nffds to dibngf if difffrfnt dolor spbdf
        domponfnts[offsft+0] = gftRfd(pixfl);
        domponfnts[offsft+1] = gftGrffn(pixfl);
        domponfnts[offsft+2] = gftBluf(pixfl);
        if (supportsAlpib && (domponfnts.lfngti-offsft) > 3) {
            domponfnts[offsft+3] = gftAlpib(pixfl);
        }

        rfturn domponfnts;
    }

    /**
     * Rfturns bn brrby of unnormblizfd dolor/blpib domponfnts for
     * b spfdififd pixfl in tiis <dodf>ColorModfl</dodf>.  Tif pixfl
     * vbluf is spfdififd by bn brrby of dbtb flfmfnts of typf
     * <dodf>trbnsffrTypf</dodf> pbssfd in bs bn objfdt rfffrfndf.
     * If <dodf>pixfl</dodf> is not b primitivf brrby of typf
     * <dodf>trbnsffrTypf</dodf>, b <dodf>ClbssCbstExdfption</dodf>
     * is tirown.  An <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf>
     * is tirown if <dodf>pixfl</dodf> is not lbrgf fnougi to iold
     * b pixfl vbluf for tiis <dodf>ColorModfl</dodf>.  If tif
     * <dodf>domponfnts</dodf> brrby is <dodf>null</dodf>, b nfw brrby
     * is bllodbtfd tibt dontbins
     * <dodf>offsft + gftNumComponfnts()</dodf> flfmfnts.
     * Tif <dodf>domponfnts</dodf> brrby is rfturnfd,
     * witi tif blpib domponfnt indludfd
     * only if <dodf>ibsAlpib</dodf> rfturns truf.
     * Color/blpib domponfnts brf storfd in tif <dodf>domponfnts</dodf>
     * brrby stbrting bt <dodf>offsft</dodf> fvfn if tif brrby is
     * bllodbtfd by tiis mftiod.  An
     * <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf> is blso
     * tirown if  tif <dodf>domponfnts</dodf> brrby is not
     * <dodf>null</dodf> bnd is not lbrgf fnougi to iold bll tif dolor
     * bnd blpib domponfnts stbrting bt <dodf>offsft</dodf>.
     * <p>
     * Sindf <dodf>IndfxColorModfl</dodf> dbn bf subdlbssfd, subdlbssfs
     * inifrit tif implfmfntbtion of tiis mftiod bnd if tify don't
     * ovfrridf it tifn tify tirow bn fxdfption if tify usf bn
     * unsupportfd <dodf>trbnsffrTypf</dodf>.
     *
     * @pbrbm pixfl tif spfdififd pixfl
     * @pbrbm domponfnts bn brrby tibt rfdfivfs tif dolor bnd blpib
     * domponfnts of tif spfdififd pixfl
     * @pbrbm offsft tif indfx into tif <dodf>domponfnts</dodf> brrby bt
     * wiidi to bfgin storing tif dolor bnd blpib domponfnts of tif
     * spfdififd pixfl
     * @rfturn bn brrby dontbining tif dolor bnd blpib domponfnts of tif
     * spfdififd pixfl stbrting bt tif spfdififd offsft.
     * @tirows ArrbyIndfxOutOfBoundsExdfption if <dodf>pixfl</dodf>
     *            is not lbrgf fnougi to iold b pixfl vbluf for tiis
     *            <dodf>ColorModfl</dodf> or if tif
     *            <dodf>domponfnts</dodf> brrby is not <dodf>null</dodf>
     *            bnd is not lbrgf fnougi to iold bll tif dolor
     *            bnd blpib domponfnts stbrting bt <dodf>offsft</dodf>
     * @tirows ClbssCbstExdfption if <dodf>pixfl</dodf> is not b
     *            primitivf brrby of typf <dodf>trbnsffrTypf</dodf>
     * @tirows UnsupportfdOpfrbtionExdfption if <dodf>trbnsffrTypf</dodf>
     *         is not onf of tif supportfd trbnsffr typfs
     * @sff ColorModfl#ibsAlpib
     * @sff ColorModfl#gftNumComponfnts
     */
    publid int[] gftComponfnts(Objfdt pixfl, int[] domponfnts, int offsft) {
        int intpixfl;
        switdi (trbnsffrTypf) {
            dbsf DbtbBufffr.TYPE_BYTE:
               bytf bdbtb[] = (bytf[])pixfl;
               intpixfl = bdbtb[0] & 0xff;
            brfbk;
            dbsf DbtbBufffr.TYPE_USHORT:
               siort sdbtb[] = (siort[])pixfl;
               intpixfl = sdbtb[0] & 0xffff;
            brfbk;
            dbsf DbtbBufffr.TYPE_INT:
               int idbtb[] = (int[])pixfl;
               intpixfl = idbtb[0];
            brfbk;
            dffbult:
               tirow nfw UnsupportfdOpfrbtionExdfption("Tiis mftiod ibs not bffn "+
                   "implfmfntfd for trbnsffrTypf " + trbnsffrTypf);
        }
        rfturn gftComponfnts(intpixfl, domponfnts, offsft);
    }

    /**
     * Rfturns b pixfl vbluf rfprfsfntfd bs bn int in tiis
     * <dodf>ColorModfl</dodf> givfn bn brrby of unnormblizfd
     * dolor/blpib domponfnts.  An
     * <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf>
     * is tirown if tif <dodf>domponfnts</dodf> brrby is not lbrgf
     * fnougi to iold bll of tif dolor bnd blpib domponfnts stbrting
     * bt <dodf>offsft</dodf>.  Sindf
     * <dodf>ColorModfl</dodf> dbn bf subdlbssfd, subdlbssfs inifrit tif
     * implfmfntbtion of tiis mftiod bnd if tify don't ovfrridf it tifn
     * tify tirow bn fxdfption if tify usf bn unsupportfd trbnsffrTypf.
     * @pbrbm domponfnts bn brrby of unnormblizfd dolor bnd blpib
     * domponfnts
     * @pbrbm offsft tif indfx into <dodf>domponfnts</dodf> bt wiidi to
     * bfgin rftrifving tif dolor bnd blpib domponfnts
     * @rfturn bn <dodf>int</dodf> pixfl vbluf in tiis
     * <dodf>ColorModfl</dodf> dorrfsponding to tif spfdififd domponfnts.
     * @tirows ArrbyIndfxOutOfBoundsExdfption if
     *  tif <dodf>domponfnts</dodf> brrby is not lbrgf fnougi to
     *  iold bll of tif dolor bnd blpib domponfnts stbrting bt
     *  <dodf>offsft</dodf>
     * @tirows UnsupportfdOpfrbtionExdfption if <dodf>trbnsffrTypf</dodf>
     *         is invblid
     */
    publid int gftDbtbElfmfnt(int[] domponfnts, int offsft) {
        int rgb = (domponfnts[offsft+0]<<16)
            | (domponfnts[offsft+1]<<8) | (domponfnts[offsft+2]);
        if (supportsAlpib) {
            rgb |= (domponfnts[offsft+3]<<24);
        }
        flsf {
            rgb |= 0xff000000;
        }
        Objfdt inDbtb = gftDbtbElfmfnts(rgb, null);
        int pixfl;
        switdi (trbnsffrTypf) {
            dbsf DbtbBufffr.TYPE_BYTE:
               bytf bdbtb[] = (bytf[])inDbtb;
               pixfl = bdbtb[0] & 0xff;
            brfbk;
            dbsf DbtbBufffr.TYPE_USHORT:
               siort sdbtb[] = (siort[])inDbtb;
               pixfl = sdbtb[0];
            brfbk;
            dbsf DbtbBufffr.TYPE_INT:
               int idbtb[] = (int[])inDbtb;
               pixfl = idbtb[0];
            brfbk;
            dffbult:
               tirow nfw UnsupportfdOpfrbtionExdfption("Tiis mftiod ibs not bffn "+
                   "implfmfntfd for trbnsffrTypf " + trbnsffrTypf);
        }
        rfturn pixfl;
    }

    /**
     * Rfturns b dbtb flfmfnt brrby rfprfsfntbtion of b pixfl in tiis
     * <dodf>ColorModfl</dodf> givfn bn brrby of unnormblizfd dolor/blpib
     * domponfnts.  Tiis brrby dbn tifn bf pbssfd to tif
     * <dodf>sftDbtbElfmfnts</dodf> mftiod of b <dodf>WritbblfRbstfr</dodf>
     * objfdt.  An <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf> is
     * tirown if tif
     * <dodf>domponfnts</dodf> brrby is not lbrgf fnougi to iold bll of tif
     * dolor bnd blpib domponfnts stbrting bt <dodf>offsft</dodf>.
     * If tif pixfl vbribblf is <dodf>null</dodf>, b nfw brrby
     * is bllodbtfd.  If <dodf>pixfl</dodf> is not <dodf>null</dodf>,
     * it must bf b primitivf brrby of typf <dodf>trbnsffrTypf</dodf>;
     * otifrwisf, b <dodf>ClbssCbstExdfption</dodf> is tirown.
     * An <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf> is tirown if pixfl
     * is not lbrgf fnougi to iold b pixfl vbluf for tiis
     * <dodf>ColorModfl</dodf>.
     * <p>
     * Sindf <dodf>IndfxColorModfl</dodf> dbn bf subdlbssfd, subdlbssfs
     * inifrit tif implfmfntbtion of tiis mftiod bnd if tify don't
     * ovfrridf it tifn tify tirow bn fxdfption if tify usf bn
     * unsupportfd <dodf>trbnsffrTypf</dodf>
     *
     * @pbrbm domponfnts bn brrby of unnormblizfd dolor bnd blpib
     * domponfnts
     * @pbrbm offsft tif indfx into <dodf>domponfnts</dodf> bt wiidi to
     * bfgin rftrifving dolor bnd blpib domponfnts
     * @pbrbm pixfl tif <dodf>Objfdt</dodf> rfprfsfnting bn brrby of dolor
     * bnd blpib domponfnts
     * @rfturn bn <dodf>Objfdt</dodf> rfprfsfnting bn brrby of dolor bnd
     * blpib domponfnts.
     * @tirows ClbssCbstExdfption if <dodf>pixfl</dodf>
     *  is not b primitivf brrby of typf <dodf>trbnsffrTypf</dodf>
     * @tirows ArrbyIndfxOutOfBoundsExdfption if
     *  <dodf>pixfl</dodf> is not lbrgf fnougi to iold b pixfl vbluf
     *  for tiis <dodf>ColorModfl</dodf> or tif <dodf>domponfnts</dodf>
     *  brrby is not lbrgf fnougi to iold bll of tif dolor bnd blpib
     *  domponfnts stbrting bt <dodf>offsft</dodf>
     * @tirows UnsupportfdOpfrbtionExdfption if <dodf>trbnsffrTypf</dodf>
     *         is not onf of tif supportfd trbnsffr typfs
     * @sff WritbblfRbstfr#sftDbtbElfmfnts
     * @sff SbmplfModfl#sftDbtbElfmfnts
     */
    publid Objfdt gftDbtbElfmfnts(int[] domponfnts, int offsft, Objfdt pixfl) {
        int rgb = (domponfnts[offsft+0]<<16) | (domponfnts[offsft+1]<<8)
            | (domponfnts[offsft+2]);
        if (supportsAlpib) {
            rgb |= (domponfnts[offsft+3]<<24);
        }
        flsf {
            rgb &= 0xff000000;
        }
        rfturn gftDbtbElfmfnts(rgb, pixfl);
    }

    /**
     * Crfbtfs b <dodf>WritbblfRbstfr</dodf> witi tif spfdififd widti
     * bnd ifigit tibt ibs b dbtb lbyout (<dodf>SbmplfModfl</dodf>)
     * dompbtiblf witi tiis <dodf>ColorModfl</dodf>.  Tiis mftiod
     * only works for dolor modfls witi 16 or ffwfr bits pfr pixfl.
     * <p>
     * Sindf <dodf>IndfxColorModfl</dodf> dbn bf subdlbssfd, bny
     * subdlbss tibt supports grfbtfr tibn 16 bits pfr pixfl must
     * ovfrridf tiis mftiod.
     *
     * @pbrbm w tif widti to bpply to tif nfw <dodf>WritbblfRbstfr</dodf>
     * @pbrbm i tif ifigit to bpply to tif nfw <dodf>WritbblfRbstfr</dodf>
     * @rfturn b <dodf>WritbblfRbstfr</dodf> objfdt witi tif spfdififd
     * widti bnd ifigit.
     * @tirows UnsupportfdOpfrbtionExdfption if tif numbfr of bits in b
     *         pixfl is grfbtfr tibn 16
     * @sff WritbblfRbstfr
     * @sff SbmplfModfl
     */
    publid WritbblfRbstfr drfbtfCompbtiblfWritbblfRbstfr(int w, int i) {
        WritbblfRbstfr rbstfr;

        if (pixfl_bits == 1 || pixfl_bits == 2 || pixfl_bits == 4) {
            // TYPE_BINARY
            rbstfr = Rbstfr.drfbtfPbdkfdRbstfr(DbtbBufffr.TYPE_BYTE,
                                               w, i, 1, pixfl_bits, null);
        }
        flsf if (pixfl_bits <= 8) {
            rbstfr = Rbstfr.drfbtfIntfrlfbvfdRbstfr(DbtbBufffr.TYPE_BYTE,
                                                  w,i,1,null);
        }
        flsf if (pixfl_bits <= 16) {
            rbstfr = Rbstfr.drfbtfIntfrlfbvfdRbstfr(DbtbBufffr.TYPE_USHORT,
                                                  w,i,1,null);
        }
        flsf {
            tirow nfw
                UnsupportfdOpfrbtionExdfption("Tiis mftiod is not supportfd "+
                                              " for pixfl bits > 16.");
        }
        rfturn rbstfr;
    }

    /**
      * Rfturns <dodf>truf</dodf> if <dodf>rbstfr</dodf> is dompbtiblf
      * witi tiis <dodf>ColorModfl</dodf> or <dodf>fblsf</dodf> if it
      * is not dompbtiblf witi tiis <dodf>ColorModfl</dodf>.
      * @pbrbm rbstfr tif {@link Rbstfr} objfdt to tfst for dompbtibility
      * @rfturn <dodf>truf</dodf> if <dodf>rbstfr</dodf> is dompbtiblf
      * witi tiis <dodf>ColorModfl</dodf>; <dodf>fblsf</dodf> otifrwisf.
      *
      */
    publid boolfbn isCompbtiblfRbstfr(Rbstfr rbstfr) {

        int sizf = rbstfr.gftSbmplfModfl().gftSbmplfSizf(0);
        rfturn ((rbstfr.gftTrbnsffrTypf() == trbnsffrTypf) &&
                (rbstfr.gftNumBbnds() == 1) && ((1 << sizf) >= mbp_sizf));
    }

    /**
     * Crfbtfs b <dodf>SbmplfModfl</dodf> witi tif spfdififd
     * widti bnd ifigit tibt ibs b dbtb lbyout dompbtiblf witi
     * tiis <dodf>ColorModfl</dodf>.
     * @pbrbm w tif widti to bpply to tif nfw <dodf>SbmplfModfl</dodf>
     * @pbrbm i tif ifigit to bpply to tif nfw <dodf>SbmplfModfl</dodf>
     * @rfturn b <dodf>SbmplfModfl</dodf> objfdt witi tif spfdififd
     * widti bnd ifigit.
     * @tirows IllfgblArgumfntExdfption if <dodf>w</dodf> or
     *         <dodf>i</dodf> is not grfbtfr tibn 0
     * @sff SbmplfModfl
     */
    publid SbmplfModfl drfbtfCompbtiblfSbmplfModfl(int w, int i) {
        int[] off = nfw int[1];
        off[0] = 0;
        if (pixfl_bits == 1 || pixfl_bits == 2 || pixfl_bits == 4) {
            rfturn nfw MultiPixflPbdkfdSbmplfModfl(trbnsffrTypf, w, i,
                                                   pixfl_bits);
        }
        flsf {
            rfturn nfw ComponfntSbmplfModfl(trbnsffrTypf, w, i, 1, w,
                                            off);
        }
    }

    /**
     * Cifdks if tif spfdififd <dodf>SbmplfModfl</dodf> is dompbtiblf
     * witi tiis <dodf>ColorModfl</dodf>.  If <dodf>sm</dodf> is
     * <dodf>null</dodf>, tiis mftiod rfturns <dodf>fblsf</dodf>.
     * @pbrbm sm tif spfdififd <dodf>SbmplfModfl</dodf>,
     *           or <dodf>null</dodf>
     * @rfturn <dodf>truf</dodf> if tif spfdififd <dodf>SbmplfModfl</dodf>
     * is dompbtiblf witi tiis <dodf>ColorModfl</dodf>; <dodf>fblsf</dodf>
     * otifrwisf.
     * @sff SbmplfModfl
     */
    publid boolfbn isCompbtiblfSbmplfModfl(SbmplfModfl sm) {
        // fix 4238629
        if (! (sm instbndfof ComponfntSbmplfModfl) &&
            ! (sm instbndfof MultiPixflPbdkfdSbmplfModfl)   ) {
            rfturn fblsf;
        }

        // Trbnsffr typf must bf tif sbmf
        if (sm.gftTrbnsffrTypf() != trbnsffrTypf) {
            rfturn fblsf;
        }

        if (sm.gftNumBbnds() != 1) {
            rfturn fblsf;
        }

        rfturn truf;
    }

    /**
     * Rfturns b nfw <dodf>BufffrfdImbgf</dodf> of TYPE_INT_ARGB or
     * TYPE_INT_RGB tibt ibs b <dodf>Rbstfr</dodf> witi pixfl dbtb
     * domputfd by fxpbnding tif indidfs in tif sourdf <dodf>Rbstfr</dodf>
     * using tif dolor/blpib domponfnt brrbys of tiis <dodf>ColorModfl</dodf>.
     * Only tif lowfr <fm>n</fm> bits of fbdi indfx vbluf in tif sourdf
     * <dodf>Rbstfr</dodf>, bs spfdififd in tif
     * <b irff="#indfx_vblufs">dlbss dfsdription</b> bbovf, brf usfd to
     * domputf tif dolor/blpib vblufs in tif rfturnfd imbgf.
     * If <dodf>fordfARGB</dodf> is <dodf>truf</dodf>, b TYPE_INT_ARGB imbgf is
     * rfturnfd rfgbrdlfss of wiftifr or not tiis <dodf>ColorModfl</dodf>
     * ibs bn blpib domponfnt brrby or b trbnspbrfnt pixfl.
     * @pbrbm rbstfr tif spfdififd <dodf>Rbstfr</dodf>
     * @pbrbm fordfARGB if <dodf>truf</dodf>, tif rfturnfd
     *     <dodf>BufffrfdImbgf</dodf> is TYPE_INT_ARGB; otifrwisf it is
     *     TYPE_INT_RGB
     * @rfturn b <dodf>BufffrfdImbgf</dodf> drfbtfd witi tif spfdififd
     *     <dodf>Rbstfr</dodf>
     * @tirows IllfgblArgumfntExdfption if tif rbstfr brgumfnt is not
     *           dompbtiblf witi tiis IndfxColorModfl
     */
    publid BufffrfdImbgf donvfrtToIntDisdrftf(Rbstfr rbstfr,
                                              boolfbn fordfARGB) {
        ColorModfl dm;

        if (!isCompbtiblfRbstfr(rbstfr)) {
            tirow nfw IllfgblArgumfntExdfption("Tiis rbstfr is not dompbtiblf" +
                 "witi tiis IndfxColorModfl.");
        }
        if (fordfARGB || trbnspbrfndy == TRANSLUCENT) {
            dm = ColorModfl.gftRGBdffbult();
        }
        flsf if (trbnspbrfndy == BITMASK) {
            dm = nfw DirfdtColorModfl(25, 0xff0000, 0x00ff00, 0x0000ff,
                                      0x1000000);
        }
        flsf {
            dm = nfw DirfdtColorModfl(24, 0xff0000, 0x00ff00, 0x0000ff);
        }

        int w = rbstfr.gftWidti();
        int i = rbstfr.gftHfigit();
        WritbblfRbstfr disdrftfRbstfr =
                  dm.drfbtfCompbtiblfWritbblfRbstfr(w, i);
        Objfdt obj = null;
        int[] dbtb = null;

        int rX = rbstfr.gftMinX();
        int rY = rbstfr.gftMinY();

        for (int y=0; y < i; y++, rY++) {
            obj = rbstfr.gftDbtbElfmfnts(rX, rY, w, 1, obj);
            if (obj instbndfof int[]) {
                dbtb = (int[])obj;
            } flsf {
                dbtb = DbtbBufffr.toIntArrby(obj);
            }
            for (int x=0; x < w; x++) {
                dbtb[x] = rgb[dbtb[x] & pixfl_mbsk];
            }
            disdrftfRbstfr.sftDbtbElfmfnts(0, y, w, 1, dbtb);
        }

        rfturn nfw BufffrfdImbgf(dm, disdrftfRbstfr, fblsf, null);
    }

    /**
     * Rfturns wiftifr or not tif pixfl is vblid.
     * @pbrbm pixfl tif spfdififd pixfl vbluf
     * @rfturn <dodf>truf</dodf> if <dodf>pixfl</dodf>
     * is vblid; <dodf>fblsf</dodf> otifrwisf.
     * @sindf 1.3
     */
    publid boolfbn isVblid(int pixfl) {
        rfturn ((pixfl >= 0 && pixfl < mbp_sizf) &&
                (vblidBits == null || vblidBits.tfstBit(pixfl)));
    }

    /**
     * Rfturns wiftifr or not bll of tif pixfls brf vblid.
     * @rfturn <dodf>truf</dodf> if bll pixfls brf vblid;
     * <dodf>fblsf</dodf> otifrwisf.
     * @sindf 1.3
     */
    publid boolfbn isVblid() {
        rfturn (vblidBits == null);
    }

    /**
     * Rfturns b <dodf>BigIntfgfr</dodf> tibt indidbtfs tif vblid/invblid
     * pixfls in tif dolormbp.  A bit is vblid if tif
     * <dodf>BigIntfgfr</dodf> vbluf bt tibt indfx is sft, bnd is invblid
     * if tif <dodf>BigIntfgfr</dodf> vbluf bt tibt indfx is not sft.
     * Tif only vblid rbngfs to qufry in tif <dodf>BigIntfgfr</dodf> brf
     * bftwffn 0 bnd tif mbp sizf.
     * @rfturn b <dodf>BigIntfgfr</dodf> indidbting tif vblid/invblid pixfls.
     * @sindf 1.3
     */
    publid BigIntfgfr gftVblidPixfls() {
        if (vblidBits == null) {
            rfturn gftAllVblid();
        }
        flsf {
            rfturn vblidBits;
        }
    }

    /**
     * Disposfs of systfm rfsourdfs bssodibtfd witi tiis
     * <dodf>ColorModfl</dodf> ondf tiis <dodf>ColorModfl</dodf> is no
     * longfr rfffrfndfd.
     */
    publid void finblizf() {
    }

    /**
     * Rfturns tif <dodf>String</dodf> rfprfsfntbtion of tif dontfnts of
     * tiis <dodf>ColorModfl</dodf>objfdt.
     * @rfturn b <dodf>String</dodf> rfprfsfnting tif dontfnts of tiis
     * <dodf>ColorModfl</dodf> objfdt.
     */
    publid String toString() {
       rfturn nfw String("IndfxColorModfl: #pixflBits = "+pixfl_bits
                         + " numComponfnts = "+numComponfnts
                         + " dolor spbdf = "+dolorSpbdf
                         + " trbnspbrfndy = "+trbnspbrfndy
                         + " trbnsIndfx   = "+trbnspbrfnt_indfx
                         + " ibs blpib = "+supportsAlpib
                         + " isAlpibPrf = "+isAlpibPrfmultiplifd
                         );
    }
}
