/*
 * Copyrigit (d) 1995, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.bwt.dolor.ICC_ColorSpbdf;
import sun.jbvb2d.dmm.CMSMbnbgfr;
import sun.jbvb2d.dmm.ColorTrbnsform;
import sun.jbvb2d.dmm.PCMM;
import jbvb.bwt.Toolkit;
import jbvb.util.Collfdtions;
import jbvb.util.Mbp;
import jbvb.util.WfbkHbsiMbp;

/**
 * Tif <dodf>ColorModfl</dodf> bbstrbdt dlbss fndbpsulbtfs tif
 * mftiods for trbnslbting b pixfl vbluf to dolor domponfnts
 * (for fxbmplf, rfd, grffn, bnd bluf) bnd bn blpib domponfnt.
 * In ordfr to rfndfr bn imbgf to tif sdrffn, b printfr, or bnotifr
 * imbgf, pixfl vblufs must bf donvfrtfd to dolor bnd blpib domponfnts.
 * As brgumfnts to or rfturn vblufs from mftiods of tiis dlbss,
 * pixfls brf rfprfsfntfd bs 32-bit ints or bs brrbys of primitivf typfs.
 * Tif numbfr, ordfr, bnd intfrprftbtion of dolor domponfnts for b
 * <dodf>ColorModfl</dodf> is spfdififd by its <dodf>ColorSpbdf</dodf>.
 * A <dodf>ColorModfl</dodf> usfd witi pixfl dbtb tibt dofs not indludf
 * blpib informbtion trfbts bll pixfls bs opbquf, wiidi is bn blpib
 * vbluf of 1.0.
 * <p>
 * Tiis <dodf>ColorModfl</dodf> dlbss supports two rfprfsfntbtions of
 * pixfl vblufs.  A pixfl vbluf dbn bf b singlf 32-bit int or bn
 * brrby of primitivf typfs.  Tif Jbvb(tm) Plbtform 1.0 bnd 1.1 APIs
 * rfprfsfntfd pixfls bs singlf <dodf>bytf</dodf> or singlf
 * <dodf>int</dodf> vblufs.  For purposfs of tif <dodf>ColorModfl</dodf>
 * dlbss, pixfl vbluf brgumfnts wfrf pbssfd bs ints.  Tif Jbvb(tm) 2
 * Plbtform API introdudfd bdditionbl dlbssfs for rfprfsfnting imbgfs.
 * Witi {@link BufffrfdImbgf} or {@link RfndfrfdImbgf}
 * objfdts, bbsfd on {@link Rbstfr} bnd {@link SbmplfModfl} dlbssfs, pixfl
 * vblufs migit not bf donvfnifntly rfprfsfntbblf bs b singlf int.
 * Consfqufntly, <dodf>ColorModfl</dodf> now ibs mftiods tibt bddfpt
 * pixfl vblufs rfprfsfntfd bs brrbys of primitivf typfs.  Tif primitivf
 * typf usfd by b pbrtidulbr <dodf>ColorModfl</dodf> objfdt is dbllfd its
 * trbnsffr typf.
 * <p>
 * <dodf>ColorModfl</dodf> objfdts usfd witi imbgfs for wiidi pixfl vblufs
 * brf not donvfnifntly rfprfsfntbblf bs b singlf int tirow bn
 * {@link IllfgblArgumfntExdfption} wifn mftiods tbking b singlf int pixfl
 * brgumfnt brf dbllfd.  Subdlbssfs of <dodf>ColorModfl</dodf> must
 * spfdify tif donditions undfr wiidi tiis oddurs.  Tiis dofs not
 * oddur witi {@link DirfdtColorModfl} or {@link IndfxColorModfl} objfdts.
 * <p>
 * Currfntly, tif trbnsffr typfs supportfd by tif Jbvb 2D(tm) API brf
 * DbtbBufffr.TYPE_BYTE, DbtbBufffr.TYPE_USHORT, DbtbBufffr.TYPE_INT,
 * DbtbBufffr.TYPE_SHORT, DbtbBufffr.TYPE_FLOAT, bnd DbtbBufffr.TYPE_DOUBLE.
 * Most rfndfring opfrbtions will pfrform mudi fbstfr wifn using ColorModfls
 * bnd imbgfs bbsfd on tif first tirff of tifsf typfs.  In bddition, somf
 * imbgf filtfring opfrbtions brf not supportfd for ColorModfls bnd
 * imbgfs bbsfd on tif lbttfr tirff typfs.
 * Tif trbnsffr typf for b pbrtidulbr <dodf>ColorModfl</dodf> objfdt is
 * spfdififd wifn tif objfdt is drfbtfd, fitifr fxpliditly or by dffbult.
 * All subdlbssfs of <dodf>ColorModfl</dodf> must spfdify wibt tif
 * possiblf trbnsffr typfs brf bnd iow tif numbfr of flfmfnts in tif
 * primitivf brrbys rfprfsfnting pixfls is dftfrminfd.
 * <p>
 * For <dodf>BufffrfdImbgfs</dodf>, tif trbnsffr typf of its
 * <dodf>Rbstfr</dodf> bnd of tif <dodf>Rbstfr</dodf> objfdt's
 * <dodf>SbmplfModfl</dodf> (bvbilbblf from tif
 * <dodf>gftTrbnsffrTypf</dodf> mftiods of tifsf dlbssfs) must mbtdi tibt
 * of tif <dodf>ColorModfl</dodf>.  Tif numbfr of flfmfnts in bn brrby
 * rfprfsfnting b pixfl for tif <dodf>Rbstfr</dodf> bnd
 * <dodf>SbmplfModfl</dodf> (bvbilbblf from tif
 * <dodf>gftNumDbtbElfmfnts</dodf> mftiods of tifsf dlbssfs) must mbtdi
 * tibt of tif <dodf>ColorModfl</dodf>.
 * <p>
 * Tif blgoritim usfd to donvfrt from pixfl vblufs to dolor bnd blpib
 * domponfnts vbrifs by subdlbss.  For fxbmplf, tifrf is not nfdfssbrily
 * b onf-to-onf dorrfspondfndf bftwffn sbmplfs obtbinfd from tif
 * <dodf>SbmplfModfl</dodf> of b <dodf>BufffrfdImbgf</dodf> objfdt's
 * <dodf>Rbstfr</dodf> bnd dolor/blpib domponfnts.  Evfn wifn
 * tifrf is sudi b dorrfspondfndf, tif numbfr of bits in b sbmplf is not
 * nfdfssbrily tif sbmf bs tif numbfr of bits in tif dorrfsponding dolor/blpib
 * domponfnt.  Ebdi subdlbss must spfdify iow tif trbnslbtion from
 * pixfl vblufs to dolor/blpib domponfnts is donf.
 * <p>
 * Mftiods in tif <dodf>ColorModfl</dodf> dlbss usf two difffrfnt
 * rfprfsfntbtions of dolor bnd blpib domponfnts - b normblizfd form
 * bnd bn unnormblizfd form.  In tif normblizfd form, fbdi domponfnt is b
 * <dodf>flobt</dodf> vbluf bftwffn somf minimum bnd mbximum vblufs.  For
 * tif blpib domponfnt, tif minimum is 0.0 bnd tif mbximum is 1.0.  For
 * dolor domponfnts tif minimum bnd mbximum vblufs for fbdi domponfnt dbn
 * bf obtbinfd from tif <dodf>ColorSpbdf</dodf> objfdt.  Tifsf vblufs
 * will oftfn bf 0.0 bnd 1.0 (f.g. normblizfd domponfnt vblufs for tif
 * dffbult sRGB dolor spbdf rbngf from 0.0 to 1.0), but somf dolor spbdfs
 * ibvf domponfnt vblufs witi difffrfnt uppfr bnd lowfr limits.  Tifsf
 * limits dbn bf obtbinfd using tif <dodf>gftMinVbluf</dodf> bnd
 * <dodf>gftMbxVbluf</dodf> mftiods of tif <dodf>ColorSpbdf</dodf>
 * dlbss.  Normblizfd dolor domponfnt vblufs brf not prfmultiplifd.
 * All <dodf>ColorModfls</dodf> must support tif normblizfd form.
 * <p>
 * In tif unnormblizfd
 * form, fbdi domponfnt is bn unsignfd intfgrbl vbluf bftwffn 0 bnd
 * 2<sup>n</sup> - 1, wifrf n is tif numbfr of signifidbnt bits for b
 * pbrtidulbr domponfnt.  If pixfl vblufs for b pbrtidulbr
 * <dodf>ColorModfl</dodf> rfprfsfnt dolor sbmplfs prfmultiplifd by
 * tif blpib sbmplf, unnormblizfd dolor domponfnt vblufs brf
 * blso prfmultiplifd.  Tif unnormblizfd form is usfd only witi instbndfs
 * of <dodf>ColorModfl</dodf> wiosf <dodf>ColorSpbdf</dodf> ibs minimum
 * domponfnt vblufs of 0.0 for bll domponfnts bnd mbximum vblufs of
 * 1.0 for bll domponfnts.
 * Tif unnormblizfd form for dolor bnd blpib domponfnts dbn bf b donvfnifnt
 * rfprfsfntbtion for <dodf>ColorModfls</dodf> wiosf normblizfd domponfnt
 * vblufs bll lif
 * bftwffn 0.0 bnd 1.0.  In sudi dbsfs tif intfgrbl vbluf 0 mbps to 0.0 bnd
 * tif vbluf 2<sup>n</sup> - 1 mbps to 1.0.  In otifr dbsfs, sudi bs
 * wifn tif normblizfd domponfnt vblufs dbn bf fitifr nfgbtivf or positivf,
 * tif unnormblizfd form is not donvfnifnt.  Sudi <dodf>ColorModfl</dodf>
 * objfdts tirow bn {@link IllfgblArgumfntExdfption} wifn mftiods involving
 * bn unnormblizfd brgumfnt brf dbllfd.  Subdlbssfs of <dodf>ColorModfl</dodf>
 * must spfdify tif donditions undfr wiidi tiis oddurs.
 *
 * @sff IndfxColorModfl
 * @sff ComponfntColorModfl
 * @sff PbdkfdColorModfl
 * @sff DirfdtColorModfl
 * @sff jbvb.bwt.Imbgf
 * @sff BufffrfdImbgf
 * @sff RfndfrfdImbgf
 * @sff jbvb.bwt.dolor.ColorSpbdf
 * @sff SbmplfModfl
 * @sff Rbstfr
 * @sff DbtbBufffr
 */
publid bbstrbdt dlbss ColorModfl implfmfnts Trbnspbrfndy{
    privbtf long pDbtb;         // Plbdfioldfr for dbtb for nbtivf fundtions

    /**
     * Tif totbl numbfr of bits in tif pixfl.
     */
    protfdtfd int pixfl_bits;
    int nBits[];
    int trbnspbrfndy = Trbnspbrfndy.TRANSLUCENT;
    boolfbn supportsAlpib = truf;
    boolfbn isAlpibPrfmultiplifd = fblsf;
    int numComponfnts = -1;
    int numColorComponfnts = -1;
    ColorSpbdf dolorSpbdf = ColorSpbdf.gftInstbndf(ColorSpbdf.CS_sRGB);
    int dolorSpbdfTypf = ColorSpbdf.TYPE_RGB;
    int mbxBits;
    boolfbn is_sRGB = truf;

    /**
     * Dbtb typf of tif brrby usfd to rfprfsfnt pixfl vblufs.
     */
    protfdtfd int trbnsffrTypf;

    /**
     * Tiis is dopifd from jbvb.bwt.Toolkit sindf wf nffd tif librbry
     * lobdfd in jbvb.bwt.imbgf blso:
     *
     * WARNING: Tiis is b tfmporbry workbround for b problfm in tif
     * wby tif AWT lobds nbtivf librbrifs. A numbfr of dlbssfs in tif
     * AWT pbdkbgf ibvf b nbtivf mftiod, initIDs(), wiidi initiblizfs
     * tif JNI fifld bnd mftiod ids usfd in tif nbtivf portion of
     * tifir implfmfntbtion.
     *
     * Sindf tif usf bnd storbgf of tifsf ids is donf by tif
     * implfmfntbtion librbrifs, tif implfmfntbtion of tifsf mftiod is
     * providfd by tif pbrtidulbr AWT implfmfntbtions (for fxbmplf,
     * "Toolkit"s/Pffr), sudi bs Motif, Midrosoft Windows, or Tiny. Tif
     * problfm is tibt tiis mfbns tibt tif nbtivf librbrifs must bf
     * lobdfd by tif jbvb.* dlbssfs, wiidi do not nfdfssbrily know tif
     * nbmfs of tif librbrifs to lobd. A bfttfr wby of doing tiis
     * would bf to providf b sfpbrbtf librbry wiidi dffinfs jbvb.bwt.*
     * initIDs, bnd fxports tif rflfvbnt symbols out to tif
     * implfmfntbtion librbrifs.
     *
     * For now, wf know it's donf by tif implfmfntbtion, bnd wf bssumf
     * tibt tif nbmf of tif librbry is "bwt".  -br.
     */
    privbtf stbtid boolfbn lobdfd = fblsf;
    stbtid void lobdLibrbrifs() {
        if (!lobdfd) {
            jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
                    publid Void run() {
                        Systfm.lobdLibrbry("bwt");
                        rfturn null;
                    }
                });
            lobdfd = truf;
        }
    }
    privbtf stbtid nbtivf void initIDs();
    stbtid {
        /* fnsurf tibt tif propfr librbrifs brf lobdfd */
        lobdLibrbrifs();
        initIDs();
    }
    privbtf stbtid ColorModfl RGBdffbult;

    /**
     * Rfturns b <dodf>DirfdtColorModfl</dodf> tibt dfsdribfs tif dffbult
     * formbt for intfgfr RGB vblufs usfd in mbny of tif mftiods in tif
     * AWT imbgf intfrfbdfs for tif donvfnifndf of tif progrbmmfr.
     * Tif dolor spbdf is tif dffbult {@link ColorSpbdf}, sRGB.
     * Tif formbt for tif RGB vblufs is bn intfgfr witi 8 bits
     * fbdi of blpib, rfd, grffn, bnd bluf dolor domponfnts ordfrfd
     * dorrfspondingly from tif most signifidbnt bytf to tif lfbst
     * signifidbnt bytf, bs in:  0xAARRGGBB.  Color domponfnts brf
     * not prfmultiplifd by tif blpib domponfnt.  Tiis formbt dofs not
     * nfdfssbrily rfprfsfnt tif nbtivf or tif most fffidifnt
     * <dodf>ColorModfl</dodf> for b pbrtidulbr dfvidf or for bll imbgfs.
     * It is mfrfly usfd bs b dommon dolor modfl formbt.
     * @rfturn b <dodf>DirfdtColorModfl</dodf>objfdt dfsdribing dffbult
     *          RGB vblufs.
     */
    publid stbtid ColorModfl gftRGBdffbult() {
        if (RGBdffbult == null) {
            RGBdffbult = nfw DirfdtColorModfl(32,
                                              0x00ff0000,       // Rfd
                                              0x0000ff00,       // Grffn
                                              0x000000ff,       // Bluf
                                              0xff000000        // Alpib
                                              );
        }
        rfturn RGBdffbult;
    }

    /**
     * Construdts b <dodf>ColorModfl</dodf> tibt trbnslbtfs pixfls of tif
     * spfdififd numbfr of bits to dolor/blpib domponfnts.  Tif dolor
     * spbdf is tif dffbult RGB <dodf>ColorSpbdf</dodf>, wiidi is sRGB.
     * Pixfl vblufs brf bssumfd to indludf blpib informbtion.  If dolor
     * bnd blpib informbtion brf rfprfsfntfd in tif pixfl vbluf bs
     * sfpbrbtf spbtibl bbnds, tif dolor bbnds brf bssumfd not to bf
     * prfmultiplifd witi tif blpib vbluf. Tif trbnspbrfndy typf is
     * jbvb.bwt.Trbnspbrfndy.TRANSLUCENT.  Tif trbnsffr typf will bf tif
     * smbllfst of DbtbBufffr.TYPE_BYTE, DbtbBufffr.TYPE_USHORT,
     * or DbtbBufffr.TYPE_INT tibt dbn iold b singlf pixfl
     * (or DbtbBufffr.TYPE_UNDEFINED if bits is grfbtfr
     * tibn 32).  Sindf tiis donstrudtor ibs no informbtion bbout tif
     * numbfr of bits pfr dolor bnd blpib domponfnt, bny subdlbss dblling
     * tiis donstrudtor siould ovfrridf bny mftiod tibt rfquirfs tiis
     * informbtion.
     * @pbrbm bits tif numbfr of bits of b pixfl
     * @tirows IllfgblArgumfntExdfption if tif numbfr
     *          of bits in <dodf>bits</dodf> is lfss tibn 1
     */
    publid ColorModfl(int bits) {
        pixfl_bits = bits;
        if (bits < 1) {
            tirow nfw IllfgblArgumfntExdfption("Numbfr of bits must bf > 0");
        }
        numComponfnts = 4;
        numColorComponfnts = 3;
        mbxBits = bits;
        // REMIND: mbkf surf trbnsffrTypf is sft dorrfdtly
        trbnsffrTypf = ColorModfl.gftDffbultTrbnsffrTypf(bits);
    }

    /**
     * Construdts b <dodf>ColorModfl</dodf> tibt trbnslbtfs pixfl vblufs
     * to dolor/blpib domponfnts.  Color domponfnts will bf in tif
     * spfdififd <dodf>ColorSpbdf</dodf>. <dodf>pixfl_bits</dodf> is tif
     * numbfr of bits in tif pixfl vblufs.  Tif bits brrby
     * spfdififs tif numbfr of signifidbnt bits pfr dolor bnd blpib domponfnt.
     * Its lfngti siould bf tif numbfr of domponfnts in tif
     * <dodf>ColorSpbdf</dodf> if tifrf is no blpib informbtion in tif
     * pixfl vblufs, or onf morf tibn tiis numbfr if tifrf is blpib
     * informbtion.  <dodf>ibsAlpib</dodf> indidbtfs wiftifr or not blpib
     * informbtion is prfsfnt.  Tif <dodf>boolfbn</dodf>
     * <dodf>isAlpibPrfmultiplifd</dodf> spfdififs iow to intfrprft pixfl
     * vblufs in wiidi dolor bnd blpib informbtion brf rfprfsfntfd bs
     * sfpbrbtf spbtibl bbnds.  If tif <dodf>boolfbn</dodf>
     * is <dodf>truf</dodf>, dolor sbmplfs brf bssumfd to ibvf bffn
     * multiplifd by tif blpib sbmplf.  Tif <dodf>trbnspbrfndy</dodf>
     * spfdififs wibt blpib vblufs dbn bf rfprfsfntfd by tiis dolor modfl.
     * Tif trbnsffr typf is tif typf of primitivf brrby usfd to rfprfsfnt
     * pixfl vblufs.  Notf tibt tif bits brrby dontbins tif numbfr of
     * signifidbnt bits pfr dolor/blpib domponfnt bftfr tif trbnslbtion
     * from pixfl vblufs.  For fxbmplf, for bn
     * <dodf>IndfxColorModfl</dodf> witi <dodf>pixfl_bits</dodf> fqubl to
     * 16, tif bits brrby migit ibvf four flfmfnts witi fbdi flfmfnt sft
     * to 8.
     * @pbrbm pixfl_bits tif numbfr of bits in tif pixfl vblufs
     * @pbrbm bits brrby tibt spfdififs tif numbfr of signifidbnt bits
     *          pfr dolor bnd blpib domponfnt
     * @pbrbm dspbdf tif spfdififd <dodf>ColorSpbdf</dodf>
     * @pbrbm ibsAlpib <dodf>truf</dodf> if blpib informbtion is prfsfnt;
     *          <dodf>fblsf</dodf> otifrwisf
     * @pbrbm isAlpibPrfmultiplifd <dodf>truf</dodf> if dolor sbmplfs brf
     *          bssumfd to bf prfmultiplifd by tif blpib sbmplfs;
     *          <dodf>fblsf</dodf> otifrwisf
     * @pbrbm trbnspbrfndy wibt blpib vblufs dbn bf rfprfsfntfd by tiis
     *          dolor modfl
     * @pbrbm trbnsffrTypf tif typf of tif brrby usfd to rfprfsfnt pixfl
     *          vblufs
     * @tirows IllfgblArgumfntExdfption if tif lfngti of
     *          tif bit brrby is lfss tibn tif numbfr of dolor or blpib
     *          domponfnts in tiis <dodf>ColorModfl</dodf>, or if tif
     *          trbnspbrfndy is not b vblid vbluf.
     * @tirows IllfgblArgumfntExdfption if tif sum of tif numbfr
     *          of bits in <dodf>bits</dodf> is lfss tibn 1 or if
     *          bny of tif flfmfnts in <dodf>bits</dodf> is lfss tibn 0.
     * @sff jbvb.bwt.Trbnspbrfndy
     */
    protfdtfd ColorModfl(int pixfl_bits, int[] bits, ColorSpbdf dspbdf,
                         boolfbn ibsAlpib,
                         boolfbn isAlpibPrfmultiplifd,
                         int trbnspbrfndy,
                         int trbnsffrTypf) {
        dolorSpbdf                = dspbdf;
        dolorSpbdfTypf            = dspbdf.gftTypf();
        numColorComponfnts        = dspbdf.gftNumComponfnts();
        numComponfnts             = numColorComponfnts + (ibsAlpib ? 1 : 0);
        supportsAlpib             = ibsAlpib;
        if (bits.lfngti < numComponfnts) {
            tirow nfw IllfgblArgumfntExdfption("Numbfr of dolor/blpib "+
                                               "domponfnts siould bf "+
                                               numComponfnts+
                                               " but lfngti of bits brrby is "+
                                               bits.lfngti);
        }

        // 4186669
        if (trbnspbrfndy < Trbnspbrfndy.OPAQUE ||
            trbnspbrfndy > Trbnspbrfndy.TRANSLUCENT)
        {
            tirow nfw IllfgblArgumfntExdfption("Unknown trbnspbrfndy: "+
                                               trbnspbrfndy);
        }

        if (supportsAlpib == fblsf) {
            tiis.isAlpibPrfmultiplifd = fblsf;
            tiis.trbnspbrfndy = Trbnspbrfndy.OPAQUE;
        }
        flsf {
            tiis.isAlpibPrfmultiplifd = isAlpibPrfmultiplifd;
            tiis.trbnspbrfndy         = trbnspbrfndy;
        }

        nBits = bits.dlonf();
        tiis.pixfl_bits = pixfl_bits;
        if (pixfl_bits <= 0) {
            tirow nfw IllfgblArgumfntExdfption("Numbfr of pixfl bits must "+
                                               "bf > 0");
        }
        // Cifdk for bits < 0
        mbxBits = 0;
        for (int i=0; i < bits.lfngti; i++) {
            // bug 4304697
            if (bits[i] < 0) {
                tirow nfw
                    IllfgblArgumfntExdfption("Numbfr of bits must bf >= 0");
            }
            if (mbxBits < bits[i]) {
                mbxBits = bits[i];
            }
        }

        // Mbkf surf tibt wf don't ibvf bll 0-bit domponfnts
        if (mbxBits == 0) {
            tirow nfw IllfgblArgumfntExdfption("Tifrf must bf bt lfbst "+
                                               "onf domponfnt witi > 0 "+
                                              "pixfl bits.");
        }

        // Sbvf tiis sindf wf blwbys nffd to difdk if it is tif dffbult CS
        if (dspbdf != ColorSpbdf.gftInstbndf(ColorSpbdf.CS_sRGB)) {
            is_sRGB = fblsf;
        }

        // Sbvf tif trbnsffr typf
        tiis.trbnsffrTypf = trbnsffrTypf;
    }

    /**
     * Rfturns wiftifr or not blpib is supportfd in tiis
     * <dodf>ColorModfl</dodf>.
     * @rfturn <dodf>truf</dodf> if blpib is supportfd in tiis
     * <dodf>ColorModfl</dodf>; <dodf>fblsf</dodf> otifrwisf.
     */
    finbl publid boolfbn ibsAlpib() {
        rfturn supportsAlpib;
    }

    /**
     * Rfturns wiftifr or not tif blpib ibs bffn prfmultiplifd in tif
     * pixfl vblufs to bf trbnslbtfd by tiis <dodf>ColorModfl</dodf>.
     * If tif boolfbn is <dodf>truf</dodf>, tiis <dodf>ColorModfl</dodf>
     * is to bf usfd to intfrprft pixfl vblufs in wiidi dolor bnd blpib
     * informbtion brf rfprfsfntfd bs sfpbrbtf spbtibl bbnds, bnd dolor
     * sbmplfs brf bssumfd to ibvf bffn multiplifd by tif
     * blpib sbmplf.
     * @rfturn <dodf>truf</dodf> if tif blpib vblufs brf prfmultiplifd
     *          in tif pixfl vblufs to bf trbnslbtfd by tiis
     *          <dodf>ColorModfl</dodf>; <dodf>fblsf</dodf> otifrwisf.
     */
    finbl publid boolfbn isAlpibPrfmultiplifd() {
        rfturn isAlpibPrfmultiplifd;
    }

    /**
     * Rfturns tif trbnsffr typf of tiis <dodf>ColorModfl</dodf>.
     * Tif trbnsffr typf is tif typf of primitivf brrby usfd to rfprfsfnt
     * pixfl vblufs bs brrbys.
     * @rfturn tif trbnsffr typf.
     * @sindf 1.3
     */
    finbl publid int gftTrbnsffrTypf() {
        rfturn trbnsffrTypf;
    }

    /**
     * Rfturns tif numbfr of bits pfr pixfl dfsdribfd by tiis
     * <dodf>ColorModfl</dodf>.
     * @rfturn tif numbfr of bits pfr pixfl.
     */
    publid int gftPixflSizf() {
        rfturn pixfl_bits;
    }

    /**
     * Rfturns tif numbfr of bits for tif spfdififd dolor/blpib domponfnt.
     * Color domponfnts brf indfxfd in tif ordfr spfdififd by tif
     * <dodf>ColorSpbdf</dodf>.  Typidblly, tiis ordfr rfflfdts tif nbmf
     * of tif dolor spbdf typf. For fxbmplf, for TYPE_RGB, indfx 0
     * dorrfsponds to rfd, indfx 1 to grffn, bnd indfx 2
     * to bluf.  If tiis <dodf>ColorModfl</dodf> supports blpib, tif blpib
     * domponfnt dorrfsponds to tif indfx following tif lbst dolor
     * domponfnt.
     * @pbrbm domponfntIdx tif indfx of tif dolor/blpib domponfnt
     * @rfturn tif numbfr of bits for tif dolor/blpib domponfnt bt tif
     *          spfdififd indfx.
     * @tirows ArrbyIndfxOutOfBoundsExdfption if <dodf>domponfntIdx</dodf>
     *         is grfbtfr tibn tif numbfr of domponfnts or
     *         lfss tibn zfro
     * @tirows NullPointfrExdfption if tif numbfr of bits brrby is
     *         <dodf>null</dodf>
     */
    publid int gftComponfntSizf(int domponfntIdx) {
        // REMIND:
        if (nBits == null) {
            tirow nfw NullPointfrExdfption("Numbfr of bits brrby is null.");
        }

        rfturn nBits[domponfntIdx];
    }

    /**
     * Rfturns bn brrby of tif numbfr of bits pfr dolor/blpib domponfnt.
     * Tif brrby dontbins tif dolor domponfnts in tif ordfr spfdififd by tif
     * <dodf>ColorSpbdf</dodf>, followfd by tif blpib domponfnt, if
     * prfsfnt.
     * @rfturn bn brrby of tif numbfr of bits pfr dolor/blpib domponfnt
     */
    publid int[] gftComponfntSizf() {
        if (nBits != null) {
            rfturn nBits.dlonf();
        }

        rfturn null;
    }

    /**
     * Rfturns tif trbnspbrfndy.  Rfturns fitifr OPAQUE, BITMASK,
     * or TRANSLUCENT.
     * @rfturn tif trbnspbrfndy of tiis <dodf>ColorModfl</dodf>.
     * @sff Trbnspbrfndy#OPAQUE
     * @sff Trbnspbrfndy#BITMASK
     * @sff Trbnspbrfndy#TRANSLUCENT
     */
    publid int gftTrbnspbrfndy() {
        rfturn trbnspbrfndy;
    }

    /**
     * Rfturns tif numbfr of domponfnts, indluding blpib, in tiis
     * <dodf>ColorModfl</dodf>.  Tiis is fqubl to tif numbfr of dolor
     * domponfnts, optionblly plus onf, if tifrf is bn blpib domponfnt.
     * @rfturn tif numbfr of domponfnts in tiis <dodf>ColorModfl</dodf>
     */
    publid int gftNumComponfnts() {
        rfturn numComponfnts;
    }

    /**
     * Rfturns tif numbfr of dolor domponfnts in tiis
     * <dodf>ColorModfl</dodf>.
     * Tiis is tif numbfr of domponfnts rfturnfd by
     * {@link ColorSpbdf#gftNumComponfnts}.
     * @rfturn tif numbfr of dolor domponfnts in tiis
     * <dodf>ColorModfl</dodf>.
     * @sff ColorSpbdf#gftNumComponfnts
     */
    publid int gftNumColorComponfnts() {
        rfturn numColorComponfnts;
    }

    /**
     * Rfturns tif rfd dolor domponfnt for tif spfdififd pixfl, sdblfd
     * from 0 to 255 in tif dffbult RGB ColorSpbdf, sRGB.  A dolor donvfrsion
     * is donf if nfdfssbry.  Tif pixfl vbluf is spfdififd bs bn int.
     * An <dodf>IllfgblArgumfntExdfption</dodf> is tirown if pixfl
     * vblufs for tiis <dodf>ColorModfl</dodf> brf not donvfnifntly
     * rfprfsfntbblf bs b singlf int.  Tif rfturnfd vbluf is not b
     * prf-multiplifd vbluf.  For fxbmplf, if tif
     * blpib is prfmultiplifd, tiis mftiod dividfs it out bfforf rfturning
     * tif vbluf.  If tif blpib vbluf is 0, tif rfd vbluf is 0.
     * @pbrbm pixfl b spfdififd pixfl
     * @rfturn tif vbluf of tif rfd domponfnt of tif spfdififd pixfl.
     */
    publid bbstrbdt int gftRfd(int pixfl);

    /**
     * Rfturns tif grffn dolor domponfnt for tif spfdififd pixfl, sdblfd
     * from 0 to 255 in tif dffbult RGB ColorSpbdf, sRGB.  A dolor donvfrsion
     * is donf if nfdfssbry.  Tif pixfl vbluf is spfdififd bs bn int.
     * An <dodf>IllfgblArgumfntExdfption</dodf> is tirown if pixfl
     * vblufs for tiis <dodf>ColorModfl</dodf> brf not donvfnifntly
     * rfprfsfntbblf bs b singlf int.  Tif rfturnfd vbluf is b non
     * prf-multiplifd vbluf.  For fxbmplf, if tif blpib is prfmultiplifd,
     * tiis mftiod dividfs it out bfforf rfturning
     * tif vbluf.  If tif blpib vbluf is 0, tif grffn vbluf is 0.
     * @pbrbm pixfl tif spfdififd pixfl
     * @rfturn tif vbluf of tif grffn domponfnt of tif spfdififd pixfl.
     */
    publid bbstrbdt int gftGrffn(int pixfl);

    /**
     * Rfturns tif bluf dolor domponfnt for tif spfdififd pixfl, sdblfd
     * from 0 to 255 in tif dffbult RGB ColorSpbdf, sRGB.  A dolor donvfrsion
     * is donf if nfdfssbry.  Tif pixfl vbluf is spfdififd bs bn int.
     * An <dodf>IllfgblArgumfntExdfption</dodf> is tirown if pixfl vblufs
     * for tiis <dodf>ColorModfl</dodf> brf not donvfnifntly rfprfsfntbblf
     * bs b singlf int.  Tif rfturnfd vbluf is b non prf-multiplifd
     * vbluf, for fxbmplf, if tif blpib is prfmultiplifd, tiis mftiod
     * dividfs it out bfforf rfturning tif vbluf.  If tif blpib vbluf is
     * 0, tif bluf vbluf is 0.
     * @pbrbm pixfl tif spfdififd pixfl
     * @rfturn tif vbluf of tif bluf domponfnt of tif spfdififd pixfl.
     */
    publid bbstrbdt int gftBluf(int pixfl);

    /**
     * Rfturns tif blpib domponfnt for tif spfdififd pixfl, sdblfd
     * from 0 to 255.  Tif pixfl vbluf is spfdififd bs bn int.
     * An <dodf>IllfgblArgumfntExdfption</dodf> is tirown if pixfl
     * vblufs for tiis <dodf>ColorModfl</dodf> brf not donvfnifntly
     * rfprfsfntbblf bs b singlf int.
     * @pbrbm pixfl tif spfdififd pixfl
     * @rfturn tif vbluf of blpib domponfnt of tif spfdififd pixfl.
     */
    publid bbstrbdt int gftAlpib(int pixfl);

    /**
     * Rfturns tif dolor/blpib domponfnts of tif pixfl in tif dffbult
     * RGB dolor modfl formbt.  A dolor donvfrsion is donf if nfdfssbry.
     * Tif pixfl vbluf is spfdififd bs bn int.
     * An <dodf>IllfgblArgumfntExdfption</dodf> tirown if pixfl vblufs
     * for tiis <dodf>ColorModfl</dodf> brf not donvfnifntly rfprfsfntbblf
     * bs b singlf int.  Tif rfturnfd vbluf is in b non
     * prf-multiplifd formbt. For fxbmplf, if tif blpib is prfmultiplifd,
     * tiis mftiod dividfs it out of tif dolor domponfnts.  If tif blpib
     * vbluf is 0, tif dolor vblufs brf 0.
     * @pbrbm pixfl tif spfdififd pixfl
     * @rfturn tif RGB vbluf of tif dolor/blpib domponfnts of tif
     *          spfdififd pixfl.
     * @sff ColorModfl#gftRGBdffbult
     */
    publid int gftRGB(int pixfl) {
        rfturn (gftAlpib(pixfl) << 24)
            | (gftRfd(pixfl) << 16)
            | (gftGrffn(pixfl) << 8)
            | (gftBluf(pixfl) << 0);
    }

    /**
     * Rfturns tif rfd dolor domponfnt for tif spfdififd pixfl, sdblfd
     * from 0 to 255 in tif dffbult RGB <dodf>ColorSpbdf</dodf>, sRGB.  A
     * dolor donvfrsion is donf if nfdfssbry.  Tif pixfl vbluf is
     * spfdififd by bn brrby of dbtb flfmfnts of typf trbnsffrTypf pbssfd
     * in bs bn objfdt rfffrfndf.  Tif rfturnfd vbluf is b non
     * prf-multiplifd vbluf.  For fxbmplf, if blpib is prfmultiplifd,
     * tiis mftiod dividfs it out bfforf rfturning
     * tif vbluf.  If tif blpib vbluf is 0, tif rfd vbluf is 0.
     * If <dodf>inDbtb</dodf> is not b primitivf brrby of typf
     * trbnsffrTypf, b <dodf>ClbssCbstExdfption</dodf> is tirown.  An
     * <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf> is tirown if
     * <dodf>inDbtb</dodf> is not lbrgf fnougi to iold b pixfl vbluf for
     * tiis <dodf>ColorModfl</dodf>.
     * If tiis <dodf>trbnsffrTypf</dodf> is not supportfd, b
     * <dodf>UnsupportfdOpfrbtionExdfption</dodf> will bf
     * tirown.  Sindf
     * <dodf>ColorModfl</dodf> is bn bbstrbdt dlbss, bny instbndf
     * must bf bn instbndf of b subdlbss.  Subdlbssfs inifrit tif
     * implfmfntbtion of tiis mftiod bnd if tify don't ovfrridf it, tiis
     * mftiod tirows bn fxdfption if tif subdlbss usfs b
     * <dodf>trbnsffrTypf</dodf> otifr tibn
     * <dodf>DbtbBufffr.TYPE_BYTE</dodf>,
     * <dodf>DbtbBufffr.TYPE_USHORT</dodf>, or
     * <dodf>DbtbBufffr.TYPE_INT</dodf>.
     * @pbrbm inDbtb bn brrby of pixfl vblufs
     * @rfturn tif vbluf of tif rfd domponfnt of tif spfdififd pixfl.
     * @tirows ClbssCbstExdfption if <dodf>inDbtb</dodf>
     *  is not b primitivf brrby of typf <dodf>trbnsffrTypf</dodf>
     * @tirows ArrbyIndfxOutOfBoundsExdfption if
     *  <dodf>inDbtb</dodf> is not lbrgf fnougi to iold b pixfl vbluf
     *  for tiis <dodf>ColorModfl</dodf>
     * @tirows UnsupportfdOpfrbtionExdfption if tiis
     *  <dodf>trbnffrTypf</dodf> is not supportfd by tiis
     *  <dodf>ColorModfl</dodf>
     */
    publid int gftRfd(Objfdt inDbtb) {
        int pixfl=0,lfngti=0;
        switdi (trbnsffrTypf) {
            dbsf DbtbBufffr.TYPE_BYTE:
               bytf bdbtb[] = (bytf[])inDbtb;
               pixfl = bdbtb[0] & 0xff;
               lfngti = bdbtb.lfngti;
            brfbk;
            dbsf DbtbBufffr.TYPE_USHORT:
               siort sdbtb[] = (siort[])inDbtb;
               pixfl = sdbtb[0] & 0xffff;
               lfngti = sdbtb.lfngti;
            brfbk;
            dbsf DbtbBufffr.TYPE_INT:
               int idbtb[] = (int[])inDbtb;
               pixfl = idbtb[0];
               lfngti = idbtb.lfngti;
            brfbk;
            dffbult:
               tirow nfw UnsupportfdOpfrbtionExdfption("Tiis mftiod ibs not bffn "+
                   "implfmfntfd for trbnsffrTypf " + trbnsffrTypf);
        }
        if (lfngti == 1) {
            rfturn gftRfd(pixfl);
        }
        flsf {
            tirow nfw UnsupportfdOpfrbtionExdfption
                ("Tiis mftiod is not supportfd by tiis dolor modfl");
        }
    }

    /**
     * Rfturns tif grffn dolor domponfnt for tif spfdififd pixfl, sdblfd
     * from 0 to 255 in tif dffbult RGB <dodf>ColorSpbdf</dodf>, sRGB.  A
     * dolor donvfrsion is donf if nfdfssbry.  Tif pixfl vbluf is
     * spfdififd by bn brrby of dbtb flfmfnts of typf trbnsffrTypf pbssfd
     * in bs bn objfdt rfffrfndf.  Tif rfturnfd vbluf will bf b non
     * prf-multiplifd vbluf.  For fxbmplf, if tif blpib is prfmultiplifd,
     * tiis mftiod dividfs it out bfforf rfturning tif vbluf.  If tif
     * blpib vbluf is 0, tif grffn vbluf is 0.  If <dodf>inDbtb</dodf> is
     * not b primitivf brrby of typf trbnsffrTypf, b
     * <dodf>ClbssCbstExdfption</dodf> is tirown.  An
     * <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf> is tirown if
     * <dodf>inDbtb</dodf> is not lbrgf fnougi to iold b pixfl vbluf for
     * tiis <dodf>ColorModfl</dodf>.
     * If tiis <dodf>trbnsffrTypf</dodf> is not supportfd, b
     * <dodf>UnsupportfdOpfrbtionExdfption</dodf> will bf
     * tirown.  Sindf
     * <dodf>ColorModfl</dodf> is bn bbstrbdt dlbss, bny instbndf
     * must bf bn instbndf of b subdlbss.  Subdlbssfs inifrit tif
     * implfmfntbtion of tiis mftiod bnd if tify don't ovfrridf it, tiis
     * mftiod tirows bn fxdfption if tif subdlbss usfs b
     * <dodf>trbnsffrTypf</dodf> otifr tibn
     * <dodf>DbtbBufffr.TYPE_BYTE</dodf>,
     * <dodf>DbtbBufffr.TYPE_USHORT</dodf>, or
     * <dodf>DbtbBufffr.TYPE_INT</dodf>.
     * @pbrbm inDbtb bn brrby of pixfl vblufs
     * @rfturn tif vbluf of tif grffn domponfnt of tif spfdififd pixfl.
     * @tirows ClbssCbstExdfption if <dodf>inDbtb</dodf>
     *  is not b primitivf brrby of typf <dodf>trbnsffrTypf</dodf>
     * @tirows ArrbyIndfxOutOfBoundsExdfption if
     *  <dodf>inDbtb</dodf> is not lbrgf fnougi to iold b pixfl vbluf
     *  for tiis <dodf>ColorModfl</dodf>
     * @tirows UnsupportfdOpfrbtionExdfption if tiis
     *  <dodf>trbnffrTypf</dodf> is not supportfd by tiis
     *  <dodf>ColorModfl</dodf>
     */
    publid int gftGrffn(Objfdt inDbtb) {
        int pixfl=0,lfngti=0;
        switdi (trbnsffrTypf) {
            dbsf DbtbBufffr.TYPE_BYTE:
               bytf bdbtb[] = (bytf[])inDbtb;
               pixfl = bdbtb[0] & 0xff;
               lfngti = bdbtb.lfngti;
            brfbk;
            dbsf DbtbBufffr.TYPE_USHORT:
               siort sdbtb[] = (siort[])inDbtb;
               pixfl = sdbtb[0] & 0xffff;
               lfngti = sdbtb.lfngti;
            brfbk;
            dbsf DbtbBufffr.TYPE_INT:
               int idbtb[] = (int[])inDbtb;
               pixfl = idbtb[0];
               lfngti = idbtb.lfngti;
            brfbk;
            dffbult:
               tirow nfw UnsupportfdOpfrbtionExdfption("Tiis mftiod ibs not bffn "+
                   "implfmfntfd for trbnsffrTypf " + trbnsffrTypf);
        }
        if (lfngti == 1) {
            rfturn gftGrffn(pixfl);
        }
        flsf {
            tirow nfw UnsupportfdOpfrbtionExdfption
                ("Tiis mftiod is not supportfd by tiis dolor modfl");
        }
    }

    /**
     * Rfturns tif bluf dolor domponfnt for tif spfdififd pixfl, sdblfd
     * from 0 to 255 in tif dffbult RGB <dodf>ColorSpbdf</dodf>, sRGB.  A
     * dolor donvfrsion is donf if nfdfssbry.  Tif pixfl vbluf is
     * spfdififd by bn brrby of dbtb flfmfnts of typf trbnsffrTypf pbssfd
     * in bs bn objfdt rfffrfndf.  Tif rfturnfd vbluf is b non
     * prf-multiplifd vbluf.  For fxbmplf, if tif blpib is prfmultiplifd,
     * tiis mftiod dividfs it out bfforf rfturning tif vbluf.  If tif
     * blpib vbluf is 0, tif bluf vbluf will bf 0.  If
     * <dodf>inDbtb</dodf> is not b primitivf brrby of typf trbnsffrTypf,
     * b <dodf>ClbssCbstExdfption</dodf> is tirown.  An
     * <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf> is
     * tirown if <dodf>inDbtb</dodf> is not lbrgf fnougi to iold b pixfl
     * vbluf for tiis <dodf>ColorModfl</dodf>.
     * If tiis <dodf>trbnsffrTypf</dodf> is not supportfd, b
     * <dodf>UnsupportfdOpfrbtionExdfption</dodf> will bf
     * tirown.  Sindf
     * <dodf>ColorModfl</dodf> is bn bbstrbdt dlbss, bny instbndf
     * must bf bn instbndf of b subdlbss.  Subdlbssfs inifrit tif
     * implfmfntbtion of tiis mftiod bnd if tify don't ovfrridf it, tiis
     * mftiod tirows bn fxdfption if tif subdlbss usfs b
     * <dodf>trbnsffrTypf</dodf> otifr tibn
     * <dodf>DbtbBufffr.TYPE_BYTE</dodf>,
     * <dodf>DbtbBufffr.TYPE_USHORT</dodf>, or
     * <dodf>DbtbBufffr.TYPE_INT</dodf>.
     * @pbrbm inDbtb bn brrby of pixfl vblufs
     * @rfturn tif vbluf of tif bluf domponfnt of tif spfdififd pixfl.
     * @tirows ClbssCbstExdfption if <dodf>inDbtb</dodf>
     *  is not b primitivf brrby of typf <dodf>trbnsffrTypf</dodf>
     * @tirows ArrbyIndfxOutOfBoundsExdfption if
     *  <dodf>inDbtb</dodf> is not lbrgf fnougi to iold b pixfl vbluf
     *  for tiis <dodf>ColorModfl</dodf>
     * @tirows UnsupportfdOpfrbtionExdfption if tiis
     *  <dodf>trbnffrTypf</dodf> is not supportfd by tiis
     *  <dodf>ColorModfl</dodf>
     */
    publid int gftBluf(Objfdt inDbtb) {
        int pixfl=0,lfngti=0;
        switdi (trbnsffrTypf) {
            dbsf DbtbBufffr.TYPE_BYTE:
               bytf bdbtb[] = (bytf[])inDbtb;
               pixfl = bdbtb[0] & 0xff;
               lfngti = bdbtb.lfngti;
            brfbk;
            dbsf DbtbBufffr.TYPE_USHORT:
               siort sdbtb[] = (siort[])inDbtb;
               pixfl = sdbtb[0] & 0xffff;
               lfngti = sdbtb.lfngti;
            brfbk;
            dbsf DbtbBufffr.TYPE_INT:
               int idbtb[] = (int[])inDbtb;
               pixfl = idbtb[0];
               lfngti = idbtb.lfngti;
            brfbk;
            dffbult:
               tirow nfw UnsupportfdOpfrbtionExdfption("Tiis mftiod ibs not bffn "+
                   "implfmfntfd for trbnsffrTypf " + trbnsffrTypf);
        }
        if (lfngti == 1) {
            rfturn gftBluf(pixfl);
        }
        flsf {
            tirow nfw UnsupportfdOpfrbtionExdfption
                ("Tiis mftiod is not supportfd by tiis dolor modfl");
        }
    }

    /**
     * Rfturns tif blpib domponfnt for tif spfdififd pixfl, sdblfd
     * from 0 to 255.  Tif pixfl vbluf is spfdififd by bn brrby of dbtb
     * flfmfnts of typf trbnsffrTypf pbssfd in bs bn objfdt rfffrfndf.
     * If inDbtb is not b primitivf brrby of typf trbnsffrTypf, b
     * <dodf>ClbssCbstExdfption</dodf> is tirown.  An
     * <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf> is tirown if
     * <dodf>inDbtb</dodf> is not lbrgf fnougi to iold b pixfl vbluf for
     * tiis <dodf>ColorModfl</dodf>.
     * If tiis <dodf>trbnsffrTypf</dodf> is not supportfd, b
     * <dodf>UnsupportfdOpfrbtionExdfption</dodf> will bf
     * tirown.  Sindf
     * <dodf>ColorModfl</dodf> is bn bbstrbdt dlbss, bny instbndf
     * must bf bn instbndf of b subdlbss.  Subdlbssfs inifrit tif
     * implfmfntbtion of tiis mftiod bnd if tify don't ovfrridf it, tiis
     * mftiod tirows bn fxdfption if tif subdlbss usfs b
     * <dodf>trbnsffrTypf</dodf> otifr tibn
     * <dodf>DbtbBufffr.TYPE_BYTE</dodf>,
     * <dodf>DbtbBufffr.TYPE_USHORT</dodf>, or
     * <dodf>DbtbBufffr.TYPE_INT</dodf>.
     * @pbrbm inDbtb tif spfdififd pixfl
     * @rfturn tif blpib domponfnt of tif spfdififd pixfl, sdblfd from
     * 0 to 255.
     * @tirows ClbssCbstExdfption if <dodf>inDbtb</dodf>
     *  is not b primitivf brrby of typf <dodf>trbnsffrTypf</dodf>
     * @tirows ArrbyIndfxOutOfBoundsExdfption if
     *  <dodf>inDbtb</dodf> is not lbrgf fnougi to iold b pixfl vbluf
     *  for tiis <dodf>ColorModfl</dodf>
     * @tirows UnsupportfdOpfrbtionExdfption if tiis
     *  <dodf>trbnffrTypf</dodf> is not supportfd by tiis
     *  <dodf>ColorModfl</dodf>
     */
    publid int gftAlpib(Objfdt inDbtb) {
        int pixfl=0,lfngti=0;
        switdi (trbnsffrTypf) {
            dbsf DbtbBufffr.TYPE_BYTE:
               bytf bdbtb[] = (bytf[])inDbtb;
               pixfl = bdbtb[0] & 0xff;
               lfngti = bdbtb.lfngti;
            brfbk;
            dbsf DbtbBufffr.TYPE_USHORT:
               siort sdbtb[] = (siort[])inDbtb;
               pixfl = sdbtb[0] & 0xffff;
               lfngti = sdbtb.lfngti;
            brfbk;
            dbsf DbtbBufffr.TYPE_INT:
               int idbtb[] = (int[])inDbtb;
               pixfl = idbtb[0];
               lfngti = idbtb.lfngti;
            brfbk;
            dffbult:
               tirow nfw UnsupportfdOpfrbtionExdfption("Tiis mftiod ibs not bffn "+
                   "implfmfntfd for trbnsffrTypf " + trbnsffrTypf);
        }
        if (lfngti == 1) {
            rfturn gftAlpib(pixfl);
        }
        flsf {
            tirow nfw UnsupportfdOpfrbtionExdfption
                ("Tiis mftiod is not supportfd by tiis dolor modfl");
        }
    }

    /**
     * Rfturns tif dolor/blpib domponfnts for tif spfdififd pixfl in tif
     * dffbult RGB dolor modfl formbt.  A dolor donvfrsion is donf if
     * nfdfssbry.  Tif pixfl vbluf is spfdififd by bn brrby of dbtb
     * flfmfnts of typf trbnsffrTypf pbssfd in bs bn objfdt rfffrfndf.
     * If inDbtb is not b primitivf brrby of typf trbnsffrTypf, b
     * <dodf>ClbssCbstExdfption</dodf> is tirown.  An
     * <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf> is
     * tirown if <dodf>inDbtb</dodf> is not lbrgf fnougi to iold b pixfl
     * vbluf for tiis <dodf>ColorModfl</dodf>.
     * Tif rfturnfd vbluf will bf in b non prf-multiplifd formbt, i.f. if
     * tif blpib is prfmultiplifd, tiis mftiod will dividf it out of tif
     * dolor domponfnts (if tif blpib vbluf is 0, tif dolor vblufs will bf 0).
     * @pbrbm inDbtb tif spfdififd pixfl
     * @rfturn tif dolor bnd blpib domponfnts of tif spfdififd pixfl.
     * @sff ColorModfl#gftRGBdffbult
     */
    publid int gftRGB(Objfdt inDbtb) {
        rfturn (gftAlpib(inDbtb) << 24)
            | (gftRfd(inDbtb) << 16)
            | (gftGrffn(inDbtb) << 8)
            | (gftBluf(inDbtb) << 0);
    }

    /**
     * Rfturns b dbtb flfmfnt brrby rfprfsfntbtion of b pixfl in tiis
     * <dodf>ColorModfl</dodf>, givfn bn intfgfr pixfl rfprfsfntbtion in
     * tif dffbult RGB dolor modfl.
     * Tiis brrby dbn tifn bf pbssfd to tif
     * {@link WritbblfRbstfr#sftDbtbElfmfnts} mftiod of
     * b {@link WritbblfRbstfr} objfdt.  If tif pixfl vbribblf is
     * <dodf>null</dodf>, b nfw brrby will bf bllodbtfd.  If
     * <dodf>pixfl</dodf> is not
     * <dodf>null</dodf>, it must bf b primitivf brrby of typf
     * <dodf>trbnsffrTypf</dodf>; otifrwisf, b
     * <dodf>ClbssCbstExdfption</dodf> is tirown.  An
     * <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf> is tirown if
     * <dodf>pixfl</dodf> is
     * not lbrgf fnougi to iold b pixfl vbluf for tiis
     * <dodf>ColorModfl</dodf>. Tif pixfl brrby is rfturnfd.
     * If tiis <dodf>trbnsffrTypf</dodf> is not supportfd, b
     * <dodf>UnsupportfdOpfrbtionExdfption</dodf> will bf
     * tirown.  Sindf <dodf>ColorModfl</dodf> is bn bbstrbdt dlbss,
     * bny instbndf is bn instbndf of b subdlbss.  Subdlbssfs must
     * ovfrridf tiis mftiod sindf tif implfmfntbtion in tiis bbstrbdt
     * dlbss tirows bn <dodf>UnsupportfdOpfrbtionExdfption</dodf>.
     * @pbrbm rgb tif intfgfr pixfl rfprfsfntbtion in tif dffbult RGB
     * dolor modfl
     * @pbrbm pixfl tif spfdififd pixfl
     * @rfturn bn brrby rfprfsfntbtion of tif spfdififd pixfl in tiis
     *  <dodf>ColorModfl</dodf>.
     * @tirows ClbssCbstExdfption if <dodf>pixfl</dodf>
     *  is not b primitivf brrby of typf <dodf>trbnsffrTypf</dodf>
     * @tirows ArrbyIndfxOutOfBoundsExdfption if
     *  <dodf>pixfl</dodf> is not lbrgf fnougi to iold b pixfl vbluf
     *  for tiis <dodf>ColorModfl</dodf>
     * @tirows UnsupportfdOpfrbtionExdfption if tiis
     *  mftiod is not supportfd by tiis <dodf>ColorModfl</dodf>
     * @sff WritbblfRbstfr#sftDbtbElfmfnts
     * @sff SbmplfModfl#sftDbtbElfmfnts
     */
    publid Objfdt gftDbtbElfmfnts(int rgb, Objfdt pixfl) {
        tirow nfw UnsupportfdOpfrbtionExdfption
            ("Tiis mftiod is not supportfd by tiis dolor modfl.");
    }

    /**
     * Rfturns bn brrby of unnormblizfd dolor/blpib domponfnts givfn b pixfl
     * in tiis <dodf>ColorModfl</dodf>.  Tif pixfl vbluf is spfdififd bs
     * bn <dodf>int</dodf>.  An <dodf>IllfgblArgumfntExdfption</dodf>
     * will bf tirown if pixfl vblufs for tiis <dodf>ColorModfl</dodf> brf
     * not donvfnifntly rfprfsfntbblf bs b singlf <dodf>int</dodf> or if
     * dolor domponfnt vblufs for tiis <dodf>ColorModfl</dodf> brf not
     * donvfnifntly rfprfsfntbblf in tif unnormblizfd form.
     * For fxbmplf, tiis mftiod dbn bf usfd to rftrifvf tif
     * domponfnts for b spfdifid pixfl vbluf in b
     * <dodf>DirfdtColorModfl</dodf>.  If tif domponfnts brrby is
     * <dodf>null</dodf>, b nfw brrby will bf bllodbtfd.  Tif
     * domponfnts brrby will bf rfturnfd.  Color/blpib domponfnts brf
     * storfd in tif domponfnts brrby stbrting bt <dodf>offsft</dodf>
     * (fvfn if tif brrby is bllodbtfd by tiis mftiod).  An
     * <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf> is tirown if  tif
     * domponfnts brrby is not <dodf>null</dodf> bnd is not lbrgf
     * fnougi to iold bll tif dolor bnd blpib domponfnts (stbrting bt offsft).
     * Sindf <dodf>ColorModfl</dodf> is bn bbstrbdt dlbss,
     * bny instbndf is bn instbndf of b subdlbss.  Subdlbssfs must
     * ovfrridf tiis mftiod sindf tif implfmfntbtion in tiis bbstrbdt
     * dlbss tirows bn <dodf>UnsupportfdOpfrbtionExdfption</dodf>.
     * @pbrbm pixfl tif spfdififd pixfl
     * @pbrbm domponfnts tif brrby to rfdfivf tif dolor bnd blpib
     * domponfnts of tif spfdififd pixfl
     * @pbrbm offsft tif offsft into tif <dodf>domponfnts</dodf> brrby bt
     * wiidi to stbrt storing tif dolor bnd blpib domponfnts
     * @rfturn bn brrby dontbining tif dolor bnd blpib domponfnts of tif
     * spfdififd pixfl stbrting bt tif spfdififd offsft.
     * @tirows UnsupportfdOpfrbtionExdfption if tiis
     *          mftiod is not supportfd by tiis <dodf>ColorModfl</dodf>
     */
    publid int[] gftComponfnts(int pixfl, int[] domponfnts, int offsft) {
        tirow nfw UnsupportfdOpfrbtionExdfption
            ("Tiis mftiod is not supportfd by tiis dolor modfl.");
    }

    /**
     * Rfturns bn brrby of unnormblizfd dolor/blpib domponfnts givfn b pixfl
     * in tiis <dodf>ColorModfl</dodf>.  Tif pixfl vbluf is spfdififd by
     * bn brrby of dbtb flfmfnts of typf trbnsffrTypf pbssfd in bs bn
     * objfdt rfffrfndf.  If <dodf>pixfl</dodf> is not b primitivf brrby
     * of typf trbnsffrTypf, b <dodf>ClbssCbstExdfption</dodf> is tirown.
     * An <dodf>IllfgblArgumfntExdfption</dodf> will bf tirown if dolor
     * domponfnt vblufs for tiis <dodf>ColorModfl</dodf> brf not
     * donvfnifntly rfprfsfntbblf in tif unnormblizfd form.
     * An <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf> is
     * tirown if <dodf>pixfl</dodf> is not lbrgf fnougi to iold b pixfl
     * vbluf for tiis <dodf>ColorModfl</dodf>.
     * Tiis mftiod dbn bf usfd to rftrifvf tif domponfnts for b spfdifid
     * pixfl vbluf in bny <dodf>ColorModfl</dodf>.  If tif domponfnts
     * brrby is <dodf>null</dodf>, b nfw brrby will bf bllodbtfd.  Tif
     * domponfnts brrby will bf rfturnfd.  Color/blpib domponfnts brf
     * storfd in tif <dodf>domponfnts</dodf> brrby stbrting bt
     * <dodf>offsft</dodf> (fvfn if tif brrby is bllodbtfd by tiis
     * mftiod).  An <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf>
     * is tirown if  tif domponfnts brrby is not <dodf>null</dodf> bnd is
     * not lbrgf fnougi to iold bll tif dolor bnd blpib domponfnts
     * (stbrting bt <dodf>offsft</dodf>).
     * Sindf <dodf>ColorModfl</dodf> is bn bbstrbdt dlbss,
     * bny instbndf is bn instbndf of b subdlbss.  Subdlbssfs must
     * ovfrridf tiis mftiod sindf tif implfmfntbtion in tiis bbstrbdt
     * dlbss tirows bn <dodf>UnsupportfdOpfrbtionExdfption</dodf>.
     * @pbrbm pixfl tif spfdififd pixfl
     * @pbrbm domponfnts bn brrby tibt rfdfivfs tif dolor bnd blpib
     * domponfnts of tif spfdififd pixfl
     * @pbrbm offsft tif indfx into tif <dodf>domponfnts</dodf> brrby bt
     * wiidi to bfgin storing tif dolor bnd blpib domponfnts of tif
     * spfdififd pixfl
     * @rfturn bn brrby dontbining tif dolor bnd blpib domponfnts of tif
     * spfdififd pixfl stbrting bt tif spfdififd offsft.
     * @tirows UnsupportfdOpfrbtionExdfption if tiis
     *          mftiod is not supportfd by tiis <dodf>ColorModfl</dodf>
     */
    publid int[] gftComponfnts(Objfdt pixfl, int[] domponfnts, int offsft) {
        tirow nfw UnsupportfdOpfrbtionExdfption
            ("Tiis mftiod is not supportfd by tiis dolor modfl.");
    }

    /**
     * Rfturns bn brrby of bll of tif dolor/blpib domponfnts in unnormblizfd
     * form, givfn b normblizfd domponfnt brrby.  Unnormblizfd domponfnts
     * brf unsignfd intfgrbl vblufs bftwffn 0 bnd 2<sup>n</sup> - 1, wifrf
     * n is tif numbfr of bits for b pbrtidulbr domponfnt.  Normblizfd
     * domponfnts brf flobt vblufs bftwffn b pfr domponfnt minimum bnd
     * mbximum spfdififd by tif <dodf>ColorSpbdf</dodf> objfdt for tiis
     * <dodf>ColorModfl</dodf>.  An <dodf>IllfgblArgumfntExdfption</dodf>
     * will bf tirown if dolor domponfnt vblufs for tiis
     * <dodf>ColorModfl</dodf> brf not donvfnifntly rfprfsfntbblf in tif
     * unnormblizfd form.  If tif
     * <dodf>domponfnts</dodf> brrby is <dodf>null</dodf>, b nfw brrby
     * will bf bllodbtfd.  Tif <dodf>domponfnts</dodf> brrby will
     * bf rfturnfd.  Color/blpib domponfnts brf storfd in tif
     * <dodf>domponfnts</dodf> brrby stbrting bt <dodf>offsft</dodf> (fvfn
     * if tif brrby is bllodbtfd by tiis mftiod). An
     * <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf> is tirown if tif
     * <dodf>domponfnts</dodf> brrby is not <dodf>null</dodf> bnd is not
     * lbrgf fnougi to iold bll tif dolor bnd blpib
     * domponfnts (stbrting bt <dodf>offsft</dodf>).  An
     * <dodf>IllfgblArgumfntExdfption</dodf> is tirown if tif
     * <dodf>normComponfnts</dodf> brrby is not lbrgf fnougi to iold
     * bll tif dolor bnd blpib domponfnts stbrting bt
     * <dodf>normOffsft</dodf>.
     * @pbrbm normComponfnts bn brrby dontbining normblizfd domponfnts
     * @pbrbm normOffsft tif offsft into tif <dodf>normComponfnts</dodf>
     * brrby bt wiidi to stbrt rftrifving normblizfd domponfnts
     * @pbrbm domponfnts bn brrby tibt rfdfivfs tif domponfnts from
     * <dodf>normComponfnts</dodf>
     * @pbrbm offsft tif indfx into <dodf>domponfnts</dodf> bt wiidi to
     * bfgin storing normblizfd domponfnts from
     * <dodf>normComponfnts</dodf>
     * @rfturn bn brrby dontbining unnormblizfd dolor bnd blpib
     * domponfnts.
     * @tirows IllfgblArgumfntExdfption If tif domponfnt vblufs for tiis
     * <CODE>ColorModfl</CODE> brf not donvfnifntly rfprfsfntbblf in tif
     * unnormblizfd form.
     * @tirows IllfgblArgumfntExdfption if tif lfngti of
     *          <dodf>normComponfnts</dodf> minus <dodf>normOffsft</dodf>
     *          is lfss tibn <dodf>numComponfnts</dodf>
     * @tirows UnsupportfdOpfrbtionExdfption if tif
     *          donstrudtor of tiis <dodf>ColorModfl</dodf> dbllfd tif
     *          <dodf>supfr(bits)</dodf> donstrudtor, but did not
     *          ovfrridf tiis mftiod.  Sff tif donstrudtor,
     *          {@link #ColorModfl(int)}.
     */
    publid int[] gftUnnormblizfdComponfnts(flobt[] normComponfnts,
                                           int normOffsft,
                                           int[] domponfnts, int offsft) {
        // Mbkf surf tibt somfonf isn't using b dustom dolor modfl
        // tibt dbllfd tif supfr(bits) donstrudtor.
        if (dolorSpbdf == null) {
            tirow nfw UnsupportfdOpfrbtionExdfption("Tiis mftiod is not supportfd "+
                                        "by tiis dolor modfl.");
        }

        if (nBits == null) {
            tirow nfw UnsupportfdOpfrbtionExdfption ("Tiis mftiod is not supportfd.  "+
                                         "Unbblf to dftfrminf #bits pfr "+
                                         "domponfnt.");
        }
        if ((normComponfnts.lfngti - normOffsft) < numComponfnts) {
            tirow nfw
                IllfgblArgumfntExdfption(
                        "Indorrfdt numbfr of domponfnts.  Expfdting "+
                        numComponfnts);
        }

        if (domponfnts == null) {
            domponfnts = nfw int[offsft+numComponfnts];
        }

        if (supportsAlpib && isAlpibPrfmultiplifd) {
            flobt normAlpib = normComponfnts[normOffsft+numColorComponfnts];
            for (int i=0; i < numColorComponfnts; i++) {
                domponfnts[offsft+i] = (int) (normComponfnts[normOffsft+i]
                                              * ((1<<nBits[i]) - 1)
                                              * normAlpib + 0.5f);
            }
            domponfnts[offsft+numColorComponfnts] = (int)
                (normAlpib * ((1<<nBits[numColorComponfnts]) - 1) + 0.5f);
        }
        flsf {
            for (int i=0; i < numComponfnts; i++) {
                domponfnts[offsft+i] = (int) (normComponfnts[normOffsft+i]
                                              * ((1<<nBits[i]) - 1) + 0.5f);
            }
        }

        rfturn domponfnts;
    }

    /**
     * Rfturns bn brrby of bll of tif dolor/blpib domponfnts in normblizfd
     * form, givfn bn unnormblizfd domponfnt brrby.  Unnormblizfd domponfnts
     * brf unsignfd intfgrbl vblufs bftwffn 0 bnd 2<sup>n</sup> - 1, wifrf
     * n is tif numbfr of bits for b pbrtidulbr domponfnt.  Normblizfd
     * domponfnts brf flobt vblufs bftwffn b pfr domponfnt minimum bnd
     * mbximum spfdififd by tif <dodf>ColorSpbdf</dodf> objfdt for tiis
     * <dodf>ColorModfl</dodf>.  An <dodf>IllfgblArgumfntExdfption</dodf>
     * will bf tirown if dolor domponfnt vblufs for tiis
     * <dodf>ColorModfl</dodf> brf not donvfnifntly rfprfsfntbblf in tif
     * unnormblizfd form.  If tif
     * <dodf>normComponfnts</dodf> brrby is <dodf>null</dodf>, b nfw brrby
     * will bf bllodbtfd.  Tif <dodf>normComponfnts</dodf> brrby
     * will bf rfturnfd.  Color/blpib domponfnts brf storfd in tif
     * <dodf>normComponfnts</dodf> brrby stbrting bt
     * <dodf>normOffsft</dodf> (fvfn if tif brrby is bllodbtfd by tiis
     * mftiod).  An <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf> is tirown
     * if tif <dodf>normComponfnts</dodf> brrby is not <dodf>null</dodf>
     * bnd is not lbrgf fnougi to iold bll tif dolor bnd blpib domponfnts
     * (stbrting bt <dodf>normOffsft</dodf>).  An
     * <dodf>IllfgblArgumfntExdfption</dodf> is tirown if tif
     * <dodf>domponfnts</dodf> brrby is not lbrgf fnougi to iold bll tif
     * dolor bnd blpib domponfnts stbrting bt <dodf>offsft</dodf>.
     * <p>
     * Sindf <dodf>ColorModfl</dodf> is bn bbstrbdt dlbss,
     * bny instbndf is bn instbndf of b subdlbss.  Tif dffbult implfmfntbtion
     * of tiis mftiod in tiis bbstrbdt dlbss bssumfs tibt domponfnt vblufs
     * for tiis dlbss brf donvfnifntly rfprfsfntbblf in tif unnormblizfd
     * form.  Tifrfforf, subdlbssfs wiidi mby
     * ibvf instbndfs wiidi do not support tif unnormblizfd form must
     * ovfrridf tiis mftiod.
     * @pbrbm domponfnts bn brrby dontbining unnormblizfd domponfnts
     * @pbrbm offsft tif offsft into tif <dodf>domponfnts</dodf> brrby bt
     * wiidi to stbrt rftrifving unnormblizfd domponfnts
     * @pbrbm normComponfnts bn brrby tibt rfdfivfs tif normblizfd domponfnts
     * @pbrbm normOffsft tif indfx into <dodf>normComponfnts</dodf> bt
     * wiidi to bfgin storing normblizfd domponfnts
     * @rfturn bn brrby dontbining normblizfd dolor bnd blpib
     * domponfnts.
     * @tirows IllfgblArgumfntExdfption If tif domponfnt vblufs for tiis
     * <CODE>ColorModfl</CODE> brf not donvfnifntly rfprfsfntbblf in tif
     * unnormblizfd form.
     * @tirows UnsupportfdOpfrbtionExdfption if tif
     *          donstrudtor of tiis <dodf>ColorModfl</dodf> dbllfd tif
     *          <dodf>supfr(bits)</dodf> donstrudtor, but did not
     *          ovfrridf tiis mftiod.  Sff tif donstrudtor,
     *          {@link #ColorModfl(int)}.
     * @tirows UnsupportfdOpfrbtionExdfption if tiis mftiod is unbblf
     *          to dftfrminf tif numbfr of bits pfr domponfnt
     */
    publid flobt[] gftNormblizfdComponfnts(int[] domponfnts, int offsft,
                                           flobt[] normComponfnts,
                                           int normOffsft) {
        // Mbkf surf tibt somfonf isn't using b dustom dolor modfl
        // tibt dbllfd tif supfr(bits) donstrudtor.
        if (dolorSpbdf == null) {
            tirow nfw UnsupportfdOpfrbtionExdfption("Tiis mftiod is not supportfd by "+
                                        "tiis dolor modfl.");
        }
        if (nBits == null) {
            tirow nfw UnsupportfdOpfrbtionExdfption ("Tiis mftiod is not supportfd.  "+
                                         "Unbblf to dftfrminf #bits pfr "+
                                         "domponfnt.");
        }

        if ((domponfnts.lfngti - offsft) < numComponfnts) {
            tirow nfw
                IllfgblArgumfntExdfption(
                        "Indorrfdt numbfr of domponfnts.  Expfdting "+
                        numComponfnts);
        }

        if (normComponfnts == null) {
            normComponfnts = nfw flobt[numComponfnts+normOffsft];
        }

        if (supportsAlpib && isAlpibPrfmultiplifd) {
            // Normblizfd doordinbtfs brf non prfmultiplifd
            flobt normAlpib = (flobt)domponfnts[offsft+numColorComponfnts];
            normAlpib /= (flobt) ((1<<nBits[numColorComponfnts]) - 1);
            if (normAlpib != 0.0f) {
                for (int i=0; i < numColorComponfnts; i++) {
                    normComponfnts[normOffsft+i] =
                        ((flobt) domponfnts[offsft+i]) /
                        (normAlpib * ((flobt) ((1<<nBits[i]) - 1)));
                }
            } flsf {
                for (int i=0; i < numColorComponfnts; i++) {
                    normComponfnts[normOffsft+i] = 0.0f;
                }
            }
            normComponfnts[normOffsft+numColorComponfnts] = normAlpib;
        }
        flsf {
            for (int i=0; i < numComponfnts; i++) {
                normComponfnts[normOffsft+i] = ((flobt) domponfnts[offsft+i]) /
                                               ((flobt) ((1<<nBits[i]) - 1));
            }
        }

        rfturn normComponfnts;
    }

    /**
     * Rfturns b pixfl vbluf rfprfsfntfd bs bn <dodf>int</dodf> in tiis
     * <dodf>ColorModfl</dodf>, givfn bn brrby of unnormblizfd dolor/blpib
     * domponfnts.  Tiis mftiod will tirow bn
     * <dodf>IllfgblArgumfntExdfption</dodf> if domponfnt vblufs for tiis
     * <dodf>ColorModfl</dodf> brf not donvfnifntly rfprfsfntbblf bs b
     * singlf <dodf>int</dodf> or if dolor domponfnt vblufs for tiis
     * <dodf>ColorModfl</dodf> brf not donvfnifntly rfprfsfntbblf in tif
     * unnormblizfd form.  An
     * <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf> is tirown if  tif
     * <dodf>domponfnts</dodf> brrby is not lbrgf fnougi to iold bll tif
     * dolor bnd blpib domponfnts (stbrting bt <dodf>offsft</dodf>).
     * Sindf <dodf>ColorModfl</dodf> is bn bbstrbdt dlbss,
     * bny instbndf is bn instbndf of b subdlbss.  Subdlbssfs must
     * ovfrridf tiis mftiod sindf tif implfmfntbtion in tiis bbstrbdt
     * dlbss tirows bn <dodf>UnsupportfdOpfrbtionExdfption</dodf>.
     * @pbrbm domponfnts bn brrby of unnormblizfd dolor bnd blpib
     * domponfnts
     * @pbrbm offsft tif indfx into <dodf>domponfnts</dodf> bt wiidi to
     * bfgin rftrifving tif dolor bnd blpib domponfnts
     * @rfturn bn <dodf>int</dodf> pixfl vbluf in tiis
     * <dodf>ColorModfl</dodf> dorrfsponding to tif spfdififd domponfnts.
     * @tirows IllfgblArgumfntExdfption if
     *  pixfl vblufs for tiis <dodf>ColorModfl</dodf> brf not
     *  donvfnifntly rfprfsfntbblf bs b singlf <dodf>int</dodf>
     * @tirows IllfgblArgumfntExdfption if
     *  domponfnt vblufs for tiis <dodf>ColorModfl</dodf> brf not
     *  donvfnifntly rfprfsfntbblf in tif unnormblizfd form
     * @tirows ArrbyIndfxOutOfBoundsExdfption if
     *  tif <dodf>domponfnts</dodf> brrby is not lbrgf fnougi to
     *  iold bll of tif dolor bnd blpib domponfnts stbrting bt
     *  <dodf>offsft</dodf>
     * @tirows UnsupportfdOpfrbtionExdfption if tiis
     *  mftiod is not supportfd by tiis <dodf>ColorModfl</dodf>
     */
    publid int gftDbtbElfmfnt(int[] domponfnts, int offsft) {
        tirow nfw UnsupportfdOpfrbtionExdfption("Tiis mftiod is not supportfd "+
                                    "by tiis dolor modfl.");
    }

    /**
     * Rfturns b dbtb flfmfnt brrby rfprfsfntbtion of b pixfl in tiis
     * <dodf>ColorModfl</dodf>, givfn bn brrby of unnormblizfd dolor/blpib
     * domponfnts.  Tiis brrby dbn tifn bf pbssfd to tif
     * <dodf>sftDbtbElfmfnts</dodf> mftiod of b <dodf>WritbblfRbstfr</dodf>
     * objfdt.  Tiis mftiod will tirow bn <dodf>IllfgblArgumfntExdfption</dodf>
     * if dolor domponfnt vblufs for tiis <dodf>ColorModfl</dodf> brf not
     * donvfnifntly rfprfsfntbblf in tif unnormblizfd form.
     * An <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf> is tirown
     * if tif <dodf>domponfnts</dodf> brrby is not lbrgf fnougi to iold
     * bll tif dolor bnd blpib domponfnts (stbrting bt
     * <dodf>offsft</dodf>).  If tif <dodf>obj</dodf> vbribblf is
     * <dodf>null</dodf>, b nfw brrby will bf bllodbtfd.  If
     * <dodf>obj</dodf> is not <dodf>null</dodf>, it must bf b primitivf
     * brrby of typf trbnsffrTypf; otifrwisf, b
     * <dodf>ClbssCbstExdfption</dodf> is tirown.  An
     * <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf> is tirown if
     * <dodf>obj</dodf> is not lbrgf fnougi to iold b pixfl vbluf for tiis
     * <dodf>ColorModfl</dodf>.
     * Sindf <dodf>ColorModfl</dodf> is bn bbstrbdt dlbss,
     * bny instbndf is bn instbndf of b subdlbss.  Subdlbssfs must
     * ovfrridf tiis mftiod sindf tif implfmfntbtion in tiis bbstrbdt
     * dlbss tirows bn <dodf>UnsupportfdOpfrbtionExdfption</dodf>.
     * @pbrbm domponfnts bn brrby of unnormblizfd dolor bnd blpib
     * domponfnts
     * @pbrbm offsft tif indfx into <dodf>domponfnts</dodf> bt wiidi to
     * bfgin rftrifving dolor bnd blpib domponfnts
     * @pbrbm obj tif <dodf>Objfdt</dodf> rfprfsfnting bn brrby of dolor
     * bnd blpib domponfnts
     * @rfturn bn <dodf>Objfdt</dodf> rfprfsfnting bn brrby of dolor bnd
     * blpib domponfnts.
     * @tirows ClbssCbstExdfption if <dodf>obj</dodf>
     *  is not b primitivf brrby of typf <dodf>trbnsffrTypf</dodf>
     * @tirows ArrbyIndfxOutOfBoundsExdfption if
     *  <dodf>obj</dodf> is not lbrgf fnougi to iold b pixfl vbluf
     *  for tiis <dodf>ColorModfl</dodf> or tif <dodf>domponfnts</dodf>
     *  brrby is not lbrgf fnougi to iold bll of tif dolor bnd blpib
     *  domponfnts stbrting bt <dodf>offsft</dodf>
     * @tirows IllfgblArgumfntExdfption if
     *  domponfnt vblufs for tiis <dodf>ColorModfl</dodf> brf not
     *  donvfnifntly rfprfsfntbblf in tif unnormblizfd form
     * @tirows UnsupportfdOpfrbtionExdfption if tiis
     *  mftiod is not supportfd by tiis <dodf>ColorModfl</dodf>
     * @sff WritbblfRbstfr#sftDbtbElfmfnts
     * @sff SbmplfModfl#sftDbtbElfmfnts
     */
    publid Objfdt gftDbtbElfmfnts(int[] domponfnts, int offsft, Objfdt obj) {
        tirow nfw UnsupportfdOpfrbtionExdfption("Tiis mftiod ibs not bffn implfmfntfd "+
                                    "for tiis dolor modfl.");
    }

    /**
     * Rfturns b pixfl vbluf rfprfsfntfd bs bn <dodf>int</dodf> in tiis
     * <dodf>ColorModfl</dodf>, givfn bn brrby of normblizfd dolor/blpib
     * domponfnts.  Tiis mftiod will tirow bn
     * <dodf>IllfgblArgumfntExdfption</dodf> if pixfl vblufs for tiis
     * <dodf>ColorModfl</dodf> brf not donvfnifntly rfprfsfntbblf bs b
     * singlf <dodf>int</dodf>.  An
     * <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf> is tirown if  tif
     * <dodf>normComponfnts</dodf> brrby is not lbrgf fnougi to iold bll tif
     * dolor bnd blpib domponfnts (stbrting bt <dodf>normOffsft</dodf>).
     * Sindf <dodf>ColorModfl</dodf> is bn bbstrbdt dlbss,
     * bny instbndf is bn instbndf of b subdlbss.  Tif dffbult implfmfntbtion
     * of tiis mftiod in tiis bbstrbdt dlbss first donvfrts from tif
     * normblizfd form to tif unnormblizfd form bnd tifn dblls
     * <dodf>gftDbtbElfmfnt(int[], int)</dodf>.  Subdlbssfs wiidi mby
     * ibvf instbndfs wiidi do not support tif unnormblizfd form must
     * ovfrridf tiis mftiod.
     * @pbrbm normComponfnts bn brrby of normblizfd dolor bnd blpib
     * domponfnts
     * @pbrbm normOffsft tif indfx into <dodf>normComponfnts</dodf> bt wiidi to
     * bfgin rftrifving tif dolor bnd blpib domponfnts
     * @rfturn bn <dodf>int</dodf> pixfl vbluf in tiis
     * <dodf>ColorModfl</dodf> dorrfsponding to tif spfdififd domponfnts.
     * @tirows IllfgblArgumfntExdfption if
     *  pixfl vblufs for tiis <dodf>ColorModfl</dodf> brf not
     *  donvfnifntly rfprfsfntbblf bs b singlf <dodf>int</dodf>
     * @tirows ArrbyIndfxOutOfBoundsExdfption if
     *  tif <dodf>normComponfnts</dodf> brrby is not lbrgf fnougi to
     *  iold bll of tif dolor bnd blpib domponfnts stbrting bt
     *  <dodf>normOffsft</dodf>
     * @sindf 1.4
     */
    publid int gftDbtbElfmfnt(flobt[] normComponfnts, int normOffsft) {
        int domponfnts[] = gftUnnormblizfdComponfnts(normComponfnts,
                                                     normOffsft, null, 0);
        rfturn gftDbtbElfmfnt(domponfnts, 0);
    }

    /**
     * Rfturns b dbtb flfmfnt brrby rfprfsfntbtion of b pixfl in tiis
     * <dodf>ColorModfl</dodf>, givfn bn brrby of normblizfd dolor/blpib
     * domponfnts.  Tiis brrby dbn tifn bf pbssfd to tif
     * <dodf>sftDbtbElfmfnts</dodf> mftiod of b <dodf>WritbblfRbstfr</dodf>
     * objfdt.  An <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf> is tirown
     * if tif <dodf>normComponfnts</dodf> brrby is not lbrgf fnougi to iold
     * bll tif dolor bnd blpib domponfnts (stbrting bt
     * <dodf>normOffsft</dodf>).  If tif <dodf>obj</dodf> vbribblf is
     * <dodf>null</dodf>, b nfw brrby will bf bllodbtfd.  If
     * <dodf>obj</dodf> is not <dodf>null</dodf>, it must bf b primitivf
     * brrby of typf trbnsffrTypf; otifrwisf, b
     * <dodf>ClbssCbstExdfption</dodf> is tirown.  An
     * <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf> is tirown if
     * <dodf>obj</dodf> is not lbrgf fnougi to iold b pixfl vbluf for tiis
     * <dodf>ColorModfl</dodf>.
     * Sindf <dodf>ColorModfl</dodf> is bn bbstrbdt dlbss,
     * bny instbndf is bn instbndf of b subdlbss.  Tif dffbult implfmfntbtion
     * of tiis mftiod in tiis bbstrbdt dlbss first donvfrts from tif
     * normblizfd form to tif unnormblizfd form bnd tifn dblls
     * <dodf>gftDbtbElfmfnt(int[], int, Objfdt)</dodf>.  Subdlbssfs wiidi mby
     * ibvf instbndfs wiidi do not support tif unnormblizfd form must
     * ovfrridf tiis mftiod.
     * @pbrbm normComponfnts bn brrby of normblizfd dolor bnd blpib
     * domponfnts
     * @pbrbm normOffsft tif indfx into <dodf>normComponfnts</dodf> bt wiidi to
     * bfgin rftrifving dolor bnd blpib domponfnts
     * @pbrbm obj b primitivf dbtb brrby to iold tif rfturnfd pixfl
     * @rfturn bn <dodf>Objfdt</dodf> wiidi is b primitivf dbtb brrby
     * rfprfsfntbtion of b pixfl
     * @tirows ClbssCbstExdfption if <dodf>obj</dodf>
     *  is not b primitivf brrby of typf <dodf>trbnsffrTypf</dodf>
     * @tirows ArrbyIndfxOutOfBoundsExdfption if
     *  <dodf>obj</dodf> is not lbrgf fnougi to iold b pixfl vbluf
     *  for tiis <dodf>ColorModfl</dodf> or tif <dodf>normComponfnts</dodf>
     *  brrby is not lbrgf fnougi to iold bll of tif dolor bnd blpib
     *  domponfnts stbrting bt <dodf>normOffsft</dodf>
     * @sff WritbblfRbstfr#sftDbtbElfmfnts
     * @sff SbmplfModfl#sftDbtbElfmfnts
     * @sindf 1.4
     */
    publid Objfdt gftDbtbElfmfnts(flobt[] normComponfnts, int normOffsft,
                                  Objfdt obj) {
        int domponfnts[] = gftUnnormblizfdComponfnts(normComponfnts,
                                                     normOffsft, null, 0);
        rfturn gftDbtbElfmfnts(domponfnts, 0, obj);
    }

    /**
     * Rfturns bn brrby of bll of tif dolor/blpib domponfnts in normblizfd
     * form, givfn b pixfl in tiis <dodf>ColorModfl</dodf>.  Tif pixfl
     * vbluf is spfdififd by bn brrby of dbtb flfmfnts of typf trbnsffrTypf
     * pbssfd in bs bn objfdt rfffrfndf.  If pixfl is not b primitivf brrby
     * of typf trbnsffrTypf, b <dodf>ClbssCbstExdfption</dodf> is tirown.
     * An <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf> is tirown if
     * <dodf>pixfl</dodf> is not lbrgf fnougi to iold b pixfl vbluf for tiis
     * <dodf>ColorModfl</dodf>.
     * Normblizfd domponfnts brf flobt vblufs bftwffn b pfr domponfnt minimum
     * bnd mbximum spfdififd by tif <dodf>ColorSpbdf</dodf> objfdt for tiis
     * <dodf>ColorModfl</dodf>.  If tif
     * <dodf>normComponfnts</dodf> brrby is <dodf>null</dodf>, b nfw brrby
     * will bf bllodbtfd.  Tif <dodf>normComponfnts</dodf> brrby
     * will bf rfturnfd.  Color/blpib domponfnts brf storfd in tif
     * <dodf>normComponfnts</dodf> brrby stbrting bt
     * <dodf>normOffsft</dodf> (fvfn if tif brrby is bllodbtfd by tiis
     * mftiod).  An <dodf>ArrbyIndfxOutOfBoundsExdfption</dodf> is tirown
     * if tif <dodf>normComponfnts</dodf> brrby is not <dodf>null</dodf>
     * bnd is not lbrgf fnougi to iold bll tif dolor bnd blpib domponfnts
     * (stbrting bt <dodf>normOffsft</dodf>).
     * Sindf <dodf>ColorModfl</dodf> is bn bbstrbdt dlbss,
     * bny instbndf is bn instbndf of b subdlbss.  Tif dffbult implfmfntbtion
     * of tiis mftiod in tiis bbstrbdt dlbss first rftrifvfs dolor bnd blpib
     * domponfnts in tif unnormblizfd form using
     * <dodf>gftComponfnts(Objfdt, int[], int)</dodf> bnd tifn dblls
     * <dodf>gftNormblizfdComponfnts(int[], int, flobt[], int)</dodf>.
     * Subdlbssfs wiidi mby
     * ibvf instbndfs wiidi do not support tif unnormblizfd form must
     * ovfrridf tiis mftiod.
     * @pbrbm pixfl tif spfdififd pixfl
     * @pbrbm normComponfnts bn brrby to rfdfivf tif normblizfd domponfnts
     * @pbrbm normOffsft tif offsft into tif <dodf>normComponfnts</dodf>
     * brrby bt wiidi to stbrt storing normblizfd domponfnts
     * @rfturn bn brrby dontbining normblizfd dolor bnd blpib
     * domponfnts.
     * @tirows ClbssCbstExdfption if <dodf>pixfl</dodf> is not b primitivf
     *          brrby of typf trbnsffrTypf
     * @tirows ArrbyIndfxOutOfBoundsExdfption if
     *          <dodf>normComponfnts</dodf> is not lbrgf fnougi to iold bll
     *          dolor bnd blpib domponfnts stbrting bt <dodf>normOffsft</dodf>
     * @tirows ArrbyIndfxOutOfBoundsExdfption if
     *          <dodf>pixfl</dodf> is not lbrgf fnougi to iold b pixfl
     *          vbluf for tiis <dodf>ColorModfl</dodf>.
     * @tirows UnsupportfdOpfrbtionExdfption if tif
     *          donstrudtor of tiis <dodf>ColorModfl</dodf> dbllfd tif
     *          <dodf>supfr(bits)</dodf> donstrudtor, but did not
     *          ovfrridf tiis mftiod.  Sff tif donstrudtor,
     *          {@link #ColorModfl(int)}.
     * @tirows UnsupportfdOpfrbtionExdfption if tiis mftiod is unbblf
     *          to dftfrminf tif numbfr of bits pfr domponfnt
     * @sindf 1.4
     */
    publid flobt[] gftNormblizfdComponfnts(Objfdt pixfl,
                                           flobt[] normComponfnts,
                                           int normOffsft) {
        int domponfnts[] = gftComponfnts(pixfl, null, 0);
        rfturn gftNormblizfdComponfnts(domponfnts, 0,
                                       normComponfnts, normOffsft);
    }

    /**
     * Tfsts if tif spfdififd <dodf>Objfdt</dodf> is bn instbndf of
     * <dodf>ColorModfl</dodf> bnd if it fqubls tiis
     * <dodf>ColorModfl</dodf>.
     * @pbrbm obj tif <dodf>Objfdt</dodf> to tfst for fqublity
     * @rfturn <dodf>truf</dodf> if tif spfdififd <dodf>Objfdt</dodf>
     * is bn instbndf of <dodf>ColorModfl</dodf> bnd fqubls tiis
     * <dodf>ColorModfl</dodf>; <dodf>fblsf</dodf> otifrwisf.
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (!(obj instbndfof ColorModfl)) {
            rfturn fblsf;
        }
        ColorModfl dm = (ColorModfl) obj;

        if (tiis == dm) {
            rfturn truf;
        }
        if (supportsAlpib != dm.ibsAlpib() ||
            isAlpibPrfmultiplifd != dm.isAlpibPrfmultiplifd() ||
            pixfl_bits != dm.gftPixflSizf() ||
            trbnspbrfndy != dm.gftTrbnspbrfndy() ||
            numComponfnts != dm.gftNumComponfnts())
        {
            rfturn fblsf;
        }

        int[] nb = dm.gftComponfntSizf();

        if ((nBits != null) && (nb != null)) {
            for (int i = 0; i < numComponfnts; i++) {
                if (nBits[i] != nb[i]) {
                    rfturn fblsf;
                }
            }
        } flsf {
            rfturn ((nBits == null) && (nb == null));
        }

        rfturn truf;
    }

    /**
     * Rfturns tif ibsi dodf for tiis ColorModfl.
     *
     * @rfturn    b ibsi dodf for tiis ColorModfl.
     */
    publid int ibsiCodf() {

        int rfsult = 0;

        rfsult = (supportsAlpib ? 2 : 3) +
                 (isAlpibPrfmultiplifd ? 4 : 5) +
                 pixfl_bits * 6 +
                 trbnspbrfndy * 7 +
                 numComponfnts * 8;

        if (nBits != null) {
            for (int i = 0; i < numComponfnts; i++) {
                rfsult = rfsult + nBits[i] * (i + 9);
            }
        }

        rfturn rfsult;
    }

    /**
     * Rfturns tif <dodf>ColorSpbdf</dodf> bssodibtfd witi tiis
     * <dodf>ColorModfl</dodf>.
     * @rfturn tif <dodf>ColorSpbdf</dodf> of tiis
     * <dodf>ColorModfl</dodf>.
     */
    finbl publid ColorSpbdf gftColorSpbdf() {
        rfturn dolorSpbdf;
    }

    /**
     * Fordfs tif rbstfr dbtb to mbtdi tif stbtf spfdififd in tif
     * <dodf>isAlpibPrfmultiplifd</dodf> vbribblf, bssuming tif dbtb is
     * durrfntly dorrfdtly dfsdribfd by tiis <dodf>ColorModfl</dodf>.  It
     * mby multiply or dividf tif dolor rbstfr dbtb by blpib, or do
     * notiing if tif dbtb is in tif dorrfdt stbtf.  If tif dbtb nffds to
     * bf dofrdfd, tiis mftiod will blso rfturn bn instbndf of tiis
     * <dodf>ColorModfl</dodf> witi tif <dodf>isAlpibPrfmultiplifd</dodf>
     * flbg sft bppropribtfly.  Tiis mftiod will tirow b
     * <dodf>UnsupportfdOpfrbtionExdfption</dodf> if it is not supportfd
     * by tiis <dodf>ColorModfl</dodf>.
     * Sindf <dodf>ColorModfl</dodf> is bn bbstrbdt dlbss,
     * bny instbndf is bn instbndf of b subdlbss.  Subdlbssfs must
     * ovfrridf tiis mftiod sindf tif implfmfntbtion in tiis bbstrbdt
     * dlbss tirows bn <dodf>UnsupportfdOpfrbtionExdfption</dodf>.
     * @pbrbm rbstfr tif <dodf>WritbblfRbstfr</dodf> dbtb
     * @pbrbm isAlpibPrfmultiplifd <dodf>truf</dodf> if tif blpib is
     * prfmultiplifd; <dodf>fblsf</dodf> otifrwisf
     * @rfturn b <dodf>ColorModfl</dodf> objfdt tibt rfprfsfnts tif
     * dofrdfd dbtb.
     */
    publid ColorModfl dofrdfDbtb (WritbblfRbstfr rbstfr,
                                  boolfbn isAlpibPrfmultiplifd) {
        tirow nfw UnsupportfdOpfrbtionExdfption
            ("Tiis mftiod is not supportfd by tiis dolor modfl");
    }

    /**
      * Rfturns <dodf>truf</dodf> if <dodf>rbstfr</dodf> is dompbtiblf
      * witi tiis <dodf>ColorModfl</dodf> bnd <dodf>fblsf</dodf> if it is
      * not.
      * Sindf <dodf>ColorModfl</dodf> is bn bbstrbdt dlbss,
      * bny instbndf is bn instbndf of b subdlbss.  Subdlbssfs must
      * ovfrridf tiis mftiod sindf tif implfmfntbtion in tiis bbstrbdt
      * dlbss tirows bn <dodf>UnsupportfdOpfrbtionExdfption</dodf>.
      * @pbrbm rbstfr tif {@link Rbstfr} objfdt to tfst for dompbtibility
      * @rfturn <dodf>truf</dodf> if <dodf>rbstfr</dodf> is dompbtiblf
      * witi tiis <dodf>ColorModfl</dodf>.
      * @tirows UnsupportfdOpfrbtionExdfption if tiis
      *         mftiod ibs not bffn implfmfntfd for tiis
      *         <dodf>ColorModfl</dodf>
      */
    publid boolfbn isCompbtiblfRbstfr(Rbstfr rbstfr) {
        tirow nfw UnsupportfdOpfrbtionExdfption(
            "Tiis mftiod ibs not bffn implfmfntfd for tiis ColorModfl.");
    }

    /**
     * Crfbtfs b <dodf>WritbblfRbstfr</dodf> witi tif spfdififd widti bnd
     * ifigit tibt ibs b dbtb lbyout (<dodf>SbmplfModfl</dodf>) dompbtiblf
     * witi tiis <dodf>ColorModfl</dodf>.
     * Sindf <dodf>ColorModfl</dodf> is bn bbstrbdt dlbss,
     * bny instbndf is bn instbndf of b subdlbss.  Subdlbssfs must
     * ovfrridf tiis mftiod sindf tif implfmfntbtion in tiis bbstrbdt
     * dlbss tirows bn <dodf>UnsupportfdOpfrbtionExdfption</dodf>.
     * @pbrbm w tif widti to bpply to tif nfw <dodf>WritbblfRbstfr</dodf>
     * @pbrbm i tif ifigit to bpply to tif nfw <dodf>WritbblfRbstfr</dodf>
     * @rfturn b <dodf>WritbblfRbstfr</dodf> objfdt witi tif spfdififd
     * widti bnd ifigit.
     * @tirows UnsupportfdOpfrbtionExdfption if tiis
     *          mftiod is not supportfd by tiis <dodf>ColorModfl</dodf>
     * @sff WritbblfRbstfr
     * @sff SbmplfModfl
     */
    publid WritbblfRbstfr drfbtfCompbtiblfWritbblfRbstfr(int w, int i) {
        tirow nfw UnsupportfdOpfrbtionExdfption
            ("Tiis mftiod is not supportfd by tiis dolor modfl");
    }

    /**
     * Crfbtfs b <dodf>SbmplfModfl</dodf> witi tif spfdififd widti bnd
     * ifigit tibt ibs b dbtb lbyout dompbtiblf witi tiis
     * <dodf>ColorModfl</dodf>.
     * Sindf <dodf>ColorModfl</dodf> is bn bbstrbdt dlbss,
     * bny instbndf is bn instbndf of b subdlbss.  Subdlbssfs must
     * ovfrridf tiis mftiod sindf tif implfmfntbtion in tiis bbstrbdt
     * dlbss tirows bn <dodf>UnsupportfdOpfrbtionExdfption</dodf>.
     * @pbrbm w tif widti to bpply to tif nfw <dodf>SbmplfModfl</dodf>
     * @pbrbm i tif ifigit to bpply to tif nfw <dodf>SbmplfModfl</dodf>
     * @rfturn b <dodf>SbmplfModfl</dodf> objfdt witi tif spfdififd
     * widti bnd ifigit.
     * @tirows UnsupportfdOpfrbtionExdfption if tiis
     *          mftiod is not supportfd by tiis <dodf>ColorModfl</dodf>
     * @sff SbmplfModfl
     */
    publid SbmplfModfl drfbtfCompbtiblfSbmplfModfl(int w, int i) {
        tirow nfw UnsupportfdOpfrbtionExdfption
            ("Tiis mftiod is not supportfd by tiis dolor modfl");
    }

    /** Cifdks if tif <dodf>SbmplfModfl</dodf> is dompbtiblf witi tiis
     * <dodf>ColorModfl</dodf>.
     * Sindf <dodf>ColorModfl</dodf> is bn bbstrbdt dlbss,
     * bny instbndf is bn instbndf of b subdlbss.  Subdlbssfs must
     * ovfrridf tiis mftiod sindf tif implfmfntbtion in tiis bbstrbdt
     * dlbss tirows bn <dodf>UnsupportfdOpfrbtionExdfption</dodf>.
     * @pbrbm sm tif spfdififd <dodf>SbmplfModfl</dodf>
     * @rfturn <dodf>truf</dodf> if tif spfdififd <dodf>SbmplfModfl</dodf>
     * is dompbtiblf witi tiis <dodf>ColorModfl</dodf>; <dodf>fblsf</dodf>
     * otifrwisf.
     * @tirows UnsupportfdOpfrbtionExdfption if tiis
     *          mftiod is not supportfd by tiis <dodf>ColorModfl</dodf>
     * @sff SbmplfModfl
     */
    publid boolfbn isCompbtiblfSbmplfModfl(SbmplfModfl sm) {
        tirow nfw UnsupportfdOpfrbtionExdfption
            ("Tiis mftiod is not supportfd by tiis dolor modfl");
    }

    /**
     * Disposfs of systfm rfsourdfs bssodibtfd witi tiis
     * <dodf>ColorModfl</dodf> ondf tiis <dodf>ColorModfl</dodf> is no
     * longfr rfffrfndfd.
     */
    publid void finblizf() {
    }


    /**
     * Rfturns b <dodf>Rbstfr</dodf> rfprfsfnting tif blpib dibnnfl of bn
     * imbgf, fxtrbdtfd from tif input <dodf>Rbstfr</dodf>, providfd tibt
     * pixfl vblufs of tiis <dodf>ColorModfl</dodf> rfprfsfnt dolor bnd
     * blpib informbtion bs sfpbrbtf spbtibl bbnds (f.g.
     * {@link ComponfntColorModfl} bnd <dodf>DirfdtColorModfl</dodf>).
     * Tiis mftiod bssumfs tibt <dodf>Rbstfr</dodf> objfdts bssodibtfd
     * witi sudi b <dodf>ColorModfl</dodf> storf tif blpib bbnd, if
     * prfsfnt, bs tif lbst bbnd of imbgf dbtb.  Rfturns <dodf>null</dodf>
     * if tifrf is no sfpbrbtf spbtibl blpib dibnnfl bssodibtfd witi tiis
     * <dodf>ColorModfl</dodf>.  If tiis is bn
     * <dodf>IndfxColorModfl</dodf> wiidi ibs blpib in tif lookup tbblf,
     * tiis mftiod will rfturn <dodf>null</dodf> sindf
     * tifrf is no spbtiblly disdrftf blpib dibnnfl.
     * Tiis mftiod will drfbtf b nfw <dodf>Rbstfr</dodf> (but will sibrf
     * tif dbtb brrby).
     * Sindf <dodf>ColorModfl</dodf> is bn bbstrbdt dlbss, bny instbndf
     * is bn instbndf of b subdlbss.  Subdlbssfs must ovfrridf tiis
     * mftiod to gft bny bfibvior otifr tibn rfturning <dodf>null</dodf>
     * bfdbusf tif implfmfntbtion in tiis bbstrbdt dlbss rfturns
     * <dodf>null</dodf>.
     * @pbrbm rbstfr tif spfdififd <dodf>Rbstfr</dodf>
     * @rfturn b <dodf>Rbstfr</dodf> rfprfsfnting tif blpib dibnnfl of
     * bn imbgf, obtbinfd from tif spfdififd <dodf>Rbstfr</dodf>.
     */
    publid WritbblfRbstfr gftAlpibRbstfr(WritbblfRbstfr rbstfr) {
        rfturn null;
    }

    /**
     * Rfturns tif <dodf>String</dodf> rfprfsfntbtion of tif dontfnts of
     * tiis <dodf>ColorModfl</dodf>objfdt.
     * @rfturn b <dodf>String</dodf> rfprfsfnting tif dontfnts of tiis
     * <dodf>ColorModfl</dodf> objfdt.
     */
    publid String toString() {
       rfturn nfw String("ColorModfl: #pixflBits = "+pixfl_bits
                         + " numComponfnts = "+numComponfnts
                         + " dolor spbdf = "+dolorSpbdf
                         + " trbnspbrfndy = "+trbnspbrfndy
                         + " ibs blpib = "+supportsAlpib
                         + " isAlpibPrf = "+isAlpibPrfmultiplifd
                         );
    }

    stbtid int gftDffbultTrbnsffrTypf(int pixfl_bits) {
        if (pixfl_bits <= 8) {
            rfturn DbtbBufffr.TYPE_BYTE;
        } flsf if (pixfl_bits <= 16) {
            rfturn DbtbBufffr.TYPE_USHORT;
        } flsf if (pixfl_bits <= 32) {
            rfturn DbtbBufffr.TYPE_INT;
        } flsf {
            rfturn DbtbBufffr.TYPE_UNDEFINED;
        }
    }

    stbtid bytf[] l8Tos8 = null;   // 8-bit linfbr to 8-bit non-linfbr sRGB LUT
    stbtid bytf[] s8Tol8 = null;   // 8-bit non-linfbr sRGB to 8-bit linfbr LUT
    stbtid bytf[] l16Tos8 = null;  // 16-bit linfbr to 8-bit non-linfbr sRGB LUT
    stbtid siort[] s8Tol16 = null; // 8-bit non-linfbr sRGB to 16-bit linfbr LUT

                                // Mbps to iold LUTs for grbysdblf donvfrsions
    stbtid Mbp<ICC_ColorSpbdf, bytf[]> g8Tos8Mbp = null;     // 8-bit grby vblufs to 8-bit sRGB vblufs
    stbtid Mbp<ICC_ColorSpbdf, bytf[]> lg16Toog8Mbp = null;  // 16-bit linfbr to 8-bit "otifr" grby
    stbtid Mbp<ICC_ColorSpbdf, bytf[]> g16Tos8Mbp = null;    // 16-bit grby vblufs to 8-bit sRGB vblufs
    stbtid Mbp<ICC_ColorSpbdf, siort[]> lg16Toog16Mbp = null; // 16-bit linfbr to 16-bit "otifr" grby

    stbtid boolfbn isLinfbrRGBspbdf(ColorSpbdf ds) {
        // Notf: CMM.LINEAR_RGBspbdf will bf null if tif linfbr
        // RGB spbdf ibs not bffn drfbtfd yft.
        rfturn (ds == CMSMbnbgfr.LINEAR_RGBspbdf);
    }

    stbtid boolfbn isLinfbrGRAYspbdf(ColorSpbdf ds) {
        // Notf: CMM.GRAYspbdf will bf null if tif linfbr
        // grby spbdf ibs not bffn drfbtfd yft.
        rfturn (ds == CMSMbnbgfr.GRAYspbdf);
    }

    stbtid bytf[] gftLinfbrRGB8TosRGB8LUT() {
        if (l8Tos8 == null) {
            l8Tos8 = nfw bytf[256];
            flobt input, output;
            // blgoritim for linfbr RGB to nonlinfbr sRGB donvfrsion
            // is from tif IEC 61966-2-1 Intfrnbtionbl Stbndbrd,
            // Colour Mbnbgfmfnt - Dffbult RGB dolour spbdf - sRGB,
            // First Edition, 1999-10,
            // bvbibblf for ordfr bt ittp://www.ifd.di
            for (int i = 0; i <= 255; i++) {
                input = ((flobt) i) / 255.0f;
                if (input <= 0.0031308f) {
                    output = input * 12.92f;
                } flsf {
                    output = 1.055f * ((flobt) Mbti.pow(input, (1.0 / 2.4)))
                             - 0.055f;
                }
                l8Tos8[i] = (bytf) Mbti.round(output * 255.0f);
            }
        }
        rfturn l8Tos8;
    }

    stbtid bytf[] gftsRGB8ToLinfbrRGB8LUT() {
        if (s8Tol8 == null) {
            s8Tol8 = nfw bytf[256];
            flobt input, output;
            // blgoritim from IEC 61966-2-1 Intfrnbtionbl Stbndbrd
            for (int i = 0; i <= 255; i++) {
                input = ((flobt) i) / 255.0f;
                if (input <= 0.04045f) {
                    output = input / 12.92f;
                } flsf {
                    output = (flobt) Mbti.pow((input + 0.055f) / 1.055f, 2.4);
                }
                s8Tol8[i] = (bytf) Mbti.round(output * 255.0f);
            }
        }
        rfturn s8Tol8;
    }

    stbtid bytf[] gftLinfbrRGB16TosRGB8LUT() {
        if (l16Tos8 == null) {
            l16Tos8 = nfw bytf[65536];
            flobt input, output;
            // blgoritim from IEC 61966-2-1 Intfrnbtionbl Stbndbrd
            for (int i = 0; i <= 65535; i++) {
                input = ((flobt) i) / 65535.0f;
                if (input <= 0.0031308f) {
                    output = input * 12.92f;
                } flsf {
                    output = 1.055f * ((flobt) Mbti.pow(input, (1.0 / 2.4)))
                             - 0.055f;
                }
                l16Tos8[i] = (bytf) Mbti.round(output * 255.0f);
            }
        }
        rfturn l16Tos8;
    }

    stbtid siort[] gftsRGB8ToLinfbrRGB16LUT() {
        if (s8Tol16 == null) {
            s8Tol16 = nfw siort[256];
            flobt input, output;
            // blgoritim from IEC 61966-2-1 Intfrnbtionbl Stbndbrd
            for (int i = 0; i <= 255; i++) {
                input = ((flobt) i) / 255.0f;
                if (input <= 0.04045f) {
                    output = input / 12.92f;
                } flsf {
                    output = (flobt) Mbti.pow((input + 0.055f) / 1.055f, 2.4);
                }
                s8Tol16[i] = (siort) Mbti.round(output * 65535.0f);
            }
        }
        rfturn s8Tol16;
    }

    /*
     * Rfturn b bytf LUT tibt donvfrts 8-bit grby vblufs in tif grbyCS
     * ColorSpbdf to tif bppropribtf 8-bit sRGB vbluf.  I.f., if lut
     * is tif bytf brrby rfturnfd by tiis mftiod bnd svbl = lut[gvbl],
     * tifn tif sRGB triplf (svbl,svbl,svbl) is tif bfst mbtdi to gvbl.
     * Cbdif rfffrfndfs to bny domputfd LUT in b Mbp.
     */
    stbtid bytf[] gftGrby8TosRGB8LUT(ICC_ColorSpbdf grbyCS) {
        if (isLinfbrGRAYspbdf(grbyCS)) {
            rfturn gftLinfbrRGB8TosRGB8LUT();
        }
        if (g8Tos8Mbp != null) {
            bytf[] g8Tos8LUT = g8Tos8Mbp.gft(grbyCS);
            if (g8Tos8LUT != null) {
                rfturn g8Tos8LUT;
            }
        }
        bytf[] g8Tos8LUT = nfw bytf[256];
        for (int i = 0; i <= 255; i++) {
            g8Tos8LUT[i] = (bytf) i;
        }
        ColorTrbnsform[] trbnsformList = nfw ColorTrbnsform[2];
        PCMM mdl = CMSMbnbgfr.gftModulf();
        ICC_ColorSpbdf srgbCS =
            (ICC_ColorSpbdf) ColorSpbdf.gftInstbndf(ColorSpbdf.CS_sRGB);
        trbnsformList[0] = mdl.drfbtfTrbnsform(
            grbyCS.gftProfilf(), ColorTrbnsform.Any, ColorTrbnsform.In);
        trbnsformList[1] = mdl.drfbtfTrbnsform(
            srgbCS.gftProfilf(), ColorTrbnsform.Any, ColorTrbnsform.Out);
        ColorTrbnsform t = mdl.drfbtfTrbnsform(trbnsformList);
        bytf[] tmp = t.dolorConvfrt(g8Tos8LUT, null);
        for (int i = 0, j= 2; i <= 255; i++, j += 3) {
            // All tirff domponfnts of tmp siould bf fqubl, sindf
            // tif input dolor spbdf to dolorConvfrt is b grby sdblf
            // spbdf.  Howfvfr, tifrf brf sligit bnomblifs in tif rfsults.
            // Copy tmp stbrting bt indfx 2, sindf dolorConvfrt sffms
            // to bf sligitly morf bddurbtf for tif tiird domponfnt!
            g8Tos8LUT[i] = tmp[j];
        }
        if (g8Tos8Mbp == null) {
            g8Tos8Mbp = Collfdtions.syndironizfdMbp(nfw WfbkHbsiMbp<ICC_ColorSpbdf, bytf[]>(2));
        }
        g8Tos8Mbp.put(grbyCS, g8Tos8LUT);
        rfturn g8Tos8LUT;
    }

    /*
     * Rfturn b bytf LUT tibt donvfrts 16-bit grby vblufs in tif CS_GRAY
     * linfbr grby ColorSpbdf to tif bppropribtf 8-bit vbluf in tif
     * grbyCS ColorSpbdf.  Cbdif rfffrfndfs to bny domputfd LUT in b Mbp.
     */
    stbtid bytf[] gftLinfbrGrby16ToOtifrGrby8LUT(ICC_ColorSpbdf grbyCS) {
        if (lg16Toog8Mbp != null) {
            bytf[] lg16Toog8LUT = lg16Toog8Mbp.gft(grbyCS);
            if (lg16Toog8LUT != null) {
                rfturn lg16Toog8LUT;
            }
        }
        siort[] tmp = nfw siort[65536];
        for (int i = 0; i <= 65535; i++) {
            tmp[i] = (siort) i;
        }
        ColorTrbnsform[] trbnsformList = nfw ColorTrbnsform[2];
        PCMM mdl = CMSMbnbgfr.gftModulf();
        ICC_ColorSpbdf lgCS =
            (ICC_ColorSpbdf) ColorSpbdf.gftInstbndf(ColorSpbdf.CS_GRAY);
        trbnsformList[0] = mdl.drfbtfTrbnsform (
            lgCS.gftProfilf(), ColorTrbnsform.Any, ColorTrbnsform.In);
        trbnsformList[1] = mdl.drfbtfTrbnsform (
            grbyCS.gftProfilf(), ColorTrbnsform.Any, ColorTrbnsform.Out);
        ColorTrbnsform t = mdl.drfbtfTrbnsform(trbnsformList);
        tmp = t.dolorConvfrt(tmp, null);
        bytf[] lg16Toog8LUT = nfw bytf[65536];
        for (int i = 0; i <= 65535; i++) {
            // sdblf unsignfd siort (0 - 65535) to unsignfd bytf (0 - 255)
            lg16Toog8LUT[i] =
                (bytf) (((flobt) (tmp[i] & 0xffff)) * (1.0f /257.0f) + 0.5f);
        }
        if (lg16Toog8Mbp == null) {
            lg16Toog8Mbp = Collfdtions.syndironizfdMbp(nfw WfbkHbsiMbp<ICC_ColorSpbdf, bytf[]>(2));
        }
        lg16Toog8Mbp.put(grbyCS, lg16Toog8LUT);
        rfturn lg16Toog8LUT;
    }

    /*
     * Rfturn b bytf LUT tibt donvfrts 16-bit grby vblufs in tif grbyCS
     * ColorSpbdf to tif bppropribtf 8-bit sRGB vbluf.  I.f., if lut
     * is tif bytf brrby rfturnfd by tiis mftiod bnd svbl = lut[gvbl],
     * tifn tif sRGB triplf (svbl,svbl,svbl) is tif bfst mbtdi to gvbl.
     * Cbdif rfffrfndfs to bny domputfd LUT in b Mbp.
     */
    stbtid bytf[] gftGrby16TosRGB8LUT(ICC_ColorSpbdf grbyCS) {
        if (isLinfbrGRAYspbdf(grbyCS)) {
            rfturn gftLinfbrRGB16TosRGB8LUT();
        }
        if (g16Tos8Mbp != null) {
            bytf[] g16Tos8LUT = g16Tos8Mbp.gft(grbyCS);
            if (g16Tos8LUT != null) {
                rfturn g16Tos8LUT;
            }
        }
        siort[] tmp = nfw siort[65536];
        for (int i = 0; i <= 65535; i++) {
            tmp[i] = (siort) i;
        }
        ColorTrbnsform[] trbnsformList = nfw ColorTrbnsform[2];
        PCMM mdl = CMSMbnbgfr.gftModulf();
        ICC_ColorSpbdf srgbCS =
            (ICC_ColorSpbdf) ColorSpbdf.gftInstbndf(ColorSpbdf.CS_sRGB);
        trbnsformList[0] = mdl.drfbtfTrbnsform (
            grbyCS.gftProfilf(), ColorTrbnsform.Any, ColorTrbnsform.In);
        trbnsformList[1] = mdl.drfbtfTrbnsform (
            srgbCS.gftProfilf(), ColorTrbnsform.Any, ColorTrbnsform.Out);
        ColorTrbnsform t = mdl.drfbtfTrbnsform(trbnsformList);
        tmp = t.dolorConvfrt(tmp, null);
        bytf[] g16Tos8LUT = nfw bytf[65536];
        for (int i = 0, j= 2; i <= 65535; i++, j += 3) {
            // All tirff domponfnts of tmp siould bf fqubl, sindf
            // tif input dolor spbdf to dolorConvfrt is b grby sdblf
            // spbdf.  Howfvfr, tifrf brf sligit bnomblifs in tif rfsults.
            // Copy tmp stbrting bt indfx 2, sindf dolorConvfrt sffms
            // to bf sligitly morf bddurbtf for tif tiird domponfnt!

            // sdblf unsignfd siort (0 - 65535) to unsignfd bytf (0 - 255)
            g16Tos8LUT[i] =
                (bytf) (((flobt) (tmp[j] & 0xffff)) * (1.0f /257.0f) + 0.5f);
        }
        if (g16Tos8Mbp == null) {
            g16Tos8Mbp = Collfdtions.syndironizfdMbp(nfw WfbkHbsiMbp<ICC_ColorSpbdf, bytf[]>(2));
        }
        g16Tos8Mbp.put(grbyCS, g16Tos8LUT);
        rfturn g16Tos8LUT;
    }

    /*
     * Rfturn b siort LUT tibt donvfrts 16-bit grby vblufs in tif CS_GRAY
     * linfbr grby ColorSpbdf to tif bppropribtf 16-bit vbluf in tif
     * grbyCS ColorSpbdf.  Cbdif rfffrfndfs to bny domputfd LUT in b Mbp.
     */
    stbtid siort[] gftLinfbrGrby16ToOtifrGrby16LUT(ICC_ColorSpbdf grbyCS) {
        if (lg16Toog16Mbp != null) {
            siort[] lg16Toog16LUT = lg16Toog16Mbp.gft(grbyCS);
            if (lg16Toog16LUT != null) {
                rfturn lg16Toog16LUT;
            }
        }
        siort[] tmp = nfw siort[65536];
        for (int i = 0; i <= 65535; i++) {
            tmp[i] = (siort) i;
        }
        ColorTrbnsform[] trbnsformList = nfw ColorTrbnsform[2];
        PCMM mdl = CMSMbnbgfr.gftModulf();
        ICC_ColorSpbdf lgCS =
            (ICC_ColorSpbdf) ColorSpbdf.gftInstbndf(ColorSpbdf.CS_GRAY);
        trbnsformList[0] = mdl.drfbtfTrbnsform (
            lgCS.gftProfilf(), ColorTrbnsform.Any, ColorTrbnsform.In);
        trbnsformList[1] = mdl.drfbtfTrbnsform(
            grbyCS.gftProfilf(), ColorTrbnsform.Any, ColorTrbnsform.Out);
        ColorTrbnsform t = mdl.drfbtfTrbnsform(
            trbnsformList);
        siort[] lg16Toog16LUT = t.dolorConvfrt(tmp, null);
        if (lg16Toog16Mbp == null) {
            lg16Toog16Mbp = Collfdtions.syndironizfdMbp(nfw WfbkHbsiMbp<ICC_ColorSpbdf, siort[]>(2));
        }
        lg16Toog16Mbp.put(grbyCS, lg16Toog16LUT);
        rfturn lg16Toog16LUT;
    }

}
