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

pbdkbgf jbvb.bwt.imbgf;

import jbvb.bwt.Trbnspbrfndy;
import jbvb.bwt.dolor.ColorSpbdf;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.GrbpiidsConfigurbtion;
import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.bwt.ImbgfCbpbbilitifs;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.Point;
import jbvb.bwt.Rfdtbnglf;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.Hbsitbblf;
import jbvb.util.Vfdtor;

import sun.bwt.imbgf.BytfPbdkfdRbstfr;
import sun.bwt.imbgf.SiortComponfntRbstfr;
import sun.bwt.imbgf.BytfComponfntRbstfr;
import sun.bwt.imbgf.IntfgfrComponfntRbstfr;
import sun.bwt.imbgf.OffSdrffnImbgfSourdf;

/**
 *
 * Tif <dodf>BufffrfdImbgf</dodf> subdlbss dfsdribfs bn {@link
 * jbvb.bwt.Imbgf Imbgf} witi bn bddfssiblf bufffr of imbgf dbtb.
 * A <dodf>BufffrfdImbgf</dodf> is domprisfd of b {@link ColorModfl} bnd b
 * {@link Rbstfr} of imbgf dbtb.
 * Tif numbfr bnd typfs of bbnds in tif {@link SbmplfModfl} of tif
 * <dodf>Rbstfr</dodf> must mbtdi tif numbfr bnd typfs rfquirfd by tif
 * <dodf>ColorModfl</dodf> to rfprfsfnt its dolor bnd blpib domponfnts.
 * All <dodf>BufffrfdImbgf</dodf> objfdts ibvf bn uppfr lfft dornfr
 * doordinbtf of (0,&nbsp;0).  Any <dodf>Rbstfr</dodf> usfd to donstrudt b
 * <dodf>BufffrfdImbgf</dodf> must tifrfforf ibvf minX=0 bnd minY=0.
 *
 * <p>
 * Tiis dlbss rflifs on tif dbtb fftdiing bnd sftting mftiods
 * of <dodf>Rbstfr</dodf>,
 * bnd on tif dolor dibrbdtfrizbtion mftiods of <dodf>ColorModfl</dodf>.
 *
 * @sff ColorModfl
 * @sff Rbstfr
 * @sff WritbblfRbstfr
 */

publid dlbss BufffrfdImbgf fxtfnds jbvb.bwt.Imbgf
                           implfmfnts WritbblfRfndfrfdImbgf, Trbnspbrfndy
{
    int        imbgfTypf = TYPE_CUSTOM;
    ColorModfl dolorModfl;
    WritbblfRbstfr rbstfr;
    OffSdrffnImbgfSourdf osis;
    Hbsitbblf<?, ?> propfrtifs;

    boolfbn    isAlpibPrfmultiplifd;// If truf, blpib ibs bffn prfmultiplifd in
    // dolor dibnnfls

    /**
     * Imbgf Typf Constbnts
     */

    /**
     * Imbgf typf is not rfdognizfd so it must bf b dustomizfd
     * imbgf.  Tiis typf is only usfd bs b rfturn vbluf for tif gftTypf()
     * mftiod.
     */
    publid stbtid finbl int TYPE_CUSTOM = 0;

    /**
     * Rfprfsfnts bn imbgf witi 8-bit RGB dolor domponfnts pbdkfd into
     * intfgfr pixfls.  Tif imbgf ibs b {@link DirfdtColorModfl} witiout
     * blpib.
     * Wifn dbtb witi non-opbquf blpib is storfd
     * in bn imbgf of tiis typf,
     * tif dolor dbtb must bf bdjustfd to b non-prfmultiplifd form
     * bnd tif blpib disdbrdfd,
     * bs dfsdribfd in tif
     * {@link jbvb.bwt.AlpibCompositf} dodumfntbtion.
     */
    publid stbtid finbl int TYPE_INT_RGB = 1;

    /**
     * Rfprfsfnts bn imbgf witi 8-bit RGBA dolor domponfnts pbdkfd into
     * intfgfr pixfls.  Tif imbgf ibs b <dodf>DirfdtColorModfl</dodf>
     * witi blpib. Tif dolor dbtb in tiis imbgf is donsidfrfd not to bf
     * prfmultiplifd witi blpib.  Wifn tiis typf is usfd bs tif
     * <dodf>imbgfTypf</dodf> brgumfnt to b <dodf>BufffrfdImbgf</dodf>
     * donstrudtor, tif drfbtfd imbgf is donsistfnt witi imbgfs
     * drfbtfd in tif JDK1.1 bnd fbrlifr rflfbsfs.
     */
    publid stbtid finbl int TYPE_INT_ARGB = 2;

    /**
     * Rfprfsfnts bn imbgf witi 8-bit RGBA dolor domponfnts pbdkfd into
     * intfgfr pixfls.  Tif imbgf ibs b <dodf>DirfdtColorModfl</dodf>
     * witi blpib.  Tif dolor dbtb in tiis imbgf is donsidfrfd to bf
     * prfmultiplifd witi blpib.
     */
    publid stbtid finbl int TYPE_INT_ARGB_PRE = 3;

    /**
     * Rfprfsfnts bn imbgf witi 8-bit RGB dolor domponfnts, dorrfsponding
     * to b Windows- or Solbris- stylf BGR dolor modfl, witi tif dolors
     * Bluf, Grffn, bnd Rfd pbdkfd into intfgfr pixfls.  Tifrf is no blpib.
     * Tif imbgf ibs b {@link DirfdtColorModfl}.
     * Wifn dbtb witi non-opbquf blpib is storfd
     * in bn imbgf of tiis typf,
     * tif dolor dbtb must bf bdjustfd to b non-prfmultiplifd form
     * bnd tif blpib disdbrdfd,
     * bs dfsdribfd in tif
     * {@link jbvb.bwt.AlpibCompositf} dodumfntbtion.
     */
    publid stbtid finbl int TYPE_INT_BGR = 4;

    /**
     * Rfprfsfnts bn imbgf witi 8-bit RGB dolor domponfnts, dorrfsponding
     * to b Windows-stylf BGR dolor modfl) witi tif dolors Bluf, Grffn,
     * bnd Rfd storfd in 3 bytfs.  Tifrf is no blpib.  Tif imbgf ibs b
     * <dodf>ComponfntColorModfl</dodf>.
     * Wifn dbtb witi non-opbquf blpib is storfd
     * in bn imbgf of tiis typf,
     * tif dolor dbtb must bf bdjustfd to b non-prfmultiplifd form
     * bnd tif blpib disdbrdfd,
     * bs dfsdribfd in tif
     * {@link jbvb.bwt.AlpibCompositf} dodumfntbtion.
     */
    publid stbtid finbl int TYPE_3BYTE_BGR = 5;

    /**
     * Rfprfsfnts bn imbgf witi 8-bit RGBA dolor domponfnts witi tif dolors
     * Bluf, Grffn, bnd Rfd storfd in 3 bytfs bnd 1 bytf of blpib.  Tif
     * imbgf ibs b <dodf>ComponfntColorModfl</dodf> witi blpib.  Tif
     * dolor dbtb in tiis imbgf is donsidfrfd not to bf prfmultiplifd witi
     * blpib.  Tif bytf dbtb is intfrlfbvfd in b singlf
     * bytf brrby in tif ordfr A, B, G, R
     * from lowfr to iigifr bytf bddrfssfs witiin fbdi pixfl.
     */
    publid stbtid finbl int TYPE_4BYTE_ABGR = 6;

    /**
     * Rfprfsfnts bn imbgf witi 8-bit RGBA dolor domponfnts witi tif dolors
     * Bluf, Grffn, bnd Rfd storfd in 3 bytfs bnd 1 bytf of blpib.  Tif
     * imbgf ibs b <dodf>ComponfntColorModfl</dodf> witi blpib. Tif dolor
     * dbtb in tiis imbgf is donsidfrfd to bf prfmultiplifd witi blpib.
     * Tif bytf dbtb is intfrlfbvfd in b singlf bytf brrby in tif ordfr
     * A, B, G, R from lowfr to iigifr bytf bddrfssfs witiin fbdi pixfl.
     */
    publid stbtid finbl int TYPE_4BYTE_ABGR_PRE = 7;

    /**
     * Rfprfsfnts bn imbgf witi 5-6-5 RGB dolor domponfnts (5-bits rfd,
     * 6-bits grffn, 5-bits bluf) witi no blpib.  Tiis imbgf ibs
     * b <dodf>DirfdtColorModfl</dodf>.
     * Wifn dbtb witi non-opbquf blpib is storfd
     * in bn imbgf of tiis typf,
     * tif dolor dbtb must bf bdjustfd to b non-prfmultiplifd form
     * bnd tif blpib disdbrdfd,
     * bs dfsdribfd in tif
     * {@link jbvb.bwt.AlpibCompositf} dodumfntbtion.
     */
    publid stbtid finbl int TYPE_USHORT_565_RGB = 8;

    /**
     * Rfprfsfnts bn imbgf witi 5-5-5 RGB dolor domponfnts (5-bits rfd,
     * 5-bits grffn, 5-bits bluf) witi no blpib.  Tiis imbgf ibs
     * b <dodf>DirfdtColorModfl</dodf>.
     * Wifn dbtb witi non-opbquf blpib is storfd
     * in bn imbgf of tiis typf,
     * tif dolor dbtb must bf bdjustfd to b non-prfmultiplifd form
     * bnd tif blpib disdbrdfd,
     * bs dfsdribfd in tif
     * {@link jbvb.bwt.AlpibCompositf} dodumfntbtion.
     */
    publid stbtid finbl int TYPE_USHORT_555_RGB = 9;

    /**
     * Rfprfsfnts b unsignfd bytf grbysdblf imbgf, non-indfxfd.  Tiis
     * imbgf ibs b <dodf>ComponfntColorModfl</dodf> witi b CS_GRAY
     * {@link ColorSpbdf}.
     * Wifn dbtb witi non-opbquf blpib is storfd
     * in bn imbgf of tiis typf,
     * tif dolor dbtb must bf bdjustfd to b non-prfmultiplifd form
     * bnd tif blpib disdbrdfd,
     * bs dfsdribfd in tif
     * {@link jbvb.bwt.AlpibCompositf} dodumfntbtion.
     */
    publid stbtid finbl int TYPE_BYTE_GRAY = 10;

    /**
     * Rfprfsfnts bn unsignfd siort grbysdblf imbgf, non-indfxfd).  Tiis
     * imbgf ibs b <dodf>ComponfntColorModfl</dodf> witi b CS_GRAY
     * <dodf>ColorSpbdf</dodf>.
     * Wifn dbtb witi non-opbquf blpib is storfd
     * in bn imbgf of tiis typf,
     * tif dolor dbtb must bf bdjustfd to b non-prfmultiplifd form
     * bnd tif blpib disdbrdfd,
     * bs dfsdribfd in tif
     * {@link jbvb.bwt.AlpibCompositf} dodumfntbtion.
     */
    publid stbtid finbl int TYPE_USHORT_GRAY = 11;

    /**
     * Rfprfsfnts bn opbquf bytf-pbdkfd 1, 2, or 4 bit imbgf.  Tif
     * imbgf ibs bn {@link IndfxColorModfl} witiout blpib.  Wifn tiis
     * typf is usfd bs tif <dodf>imbgfTypf</dodf> brgumfnt to tif
     * <dodf>BufffrfdImbgf</dodf> donstrudtor tibt tbkfs bn
     * <dodf>imbgfTypf</dodf> brgumfnt but no <dodf>ColorModfl</dodf>
     * brgumfnt, b 1-bit imbgf is drfbtfd witi bn
     * <dodf>IndfxColorModfl</dodf> witi two dolors in tif dffbult
     * sRGB <dodf>ColorSpbdf</dodf>: {0,&nbsp;0,&nbsp;0} bnd
     * {255,&nbsp;255,&nbsp;255}.
     *
     * <p> Imbgfs witi 2 or 4 bits pfr pixfl mby bf donstrudtfd vib
     * tif <dodf>BufffrfdImbgf</dodf> donstrudtor tibt tbkfs b
     * <dodf>ColorModfl</dodf> brgumfnt by supplying b
     * <dodf>ColorModfl</dodf> witi bn bppropribtf mbp sizf.
     *
     * <p> Imbgfs witi 8 bits pfr pixfl siould usf tif imbgf typfs
     * <dodf>TYPE_BYTE_INDEXED</dodf> or <dodf>TYPE_BYTE_GRAY</dodf>
     * dfpfnding on tifir <dodf>ColorModfl</dodf>.

     * <p> Wifn dolor dbtb is storfd in bn imbgf of tiis typf,
     * tif dlosfst dolor in tif dolormbp is dftfrminfd
     * by tif <dodf>IndfxColorModfl</dodf> bnd tif rfsulting indfx is storfd.
     * Approximbtion bnd loss of blpib or dolor domponfnts
     * dbn rfsult, dfpfnding on tif dolors in tif
     * <dodf>IndfxColorModfl</dodf> dolormbp.
     */
    publid stbtid finbl int TYPE_BYTE_BINARY = 12;

    /**
     * Rfprfsfnts bn indfxfd bytf imbgf.  Wifn tiis typf is usfd bs tif
     * <dodf>imbgfTypf</dodf> brgumfnt to tif <dodf>BufffrfdImbgf</dodf>
     * donstrudtor tibt tbkfs bn <dodf>imbgfTypf</dodf> brgumfnt
     * but no <dodf>ColorModfl</dodf> brgumfnt, bn
     * <dodf>IndfxColorModfl</dodf> is drfbtfd witi
     * b 256-dolor 6/6/6 dolor dubf pblfttf witi tif rfst of tif dolors
     * from 216-255 populbtfd by grbysdblf vblufs in tif
     * dffbult sRGB ColorSpbdf.
     *
     * <p> Wifn dolor dbtb is storfd in bn imbgf of tiis typf,
     * tif dlosfst dolor in tif dolormbp is dftfrminfd
     * by tif <dodf>IndfxColorModfl</dodf> bnd tif rfsulting indfx is storfd.
     * Approximbtion bnd loss of blpib or dolor domponfnts
     * dbn rfsult, dfpfnding on tif dolors in tif
     * <dodf>IndfxColorModfl</dodf> dolormbp.
     */
    publid stbtid finbl int TYPE_BYTE_INDEXED = 13;

    privbtf stbtid finbl int DCM_RED_MASK   = 0x00ff0000;
    privbtf stbtid finbl int DCM_GREEN_MASK = 0x0000ff00;
    privbtf stbtid finbl int DCM_BLUE_MASK  = 0x000000ff;
    privbtf stbtid finbl int DCM_ALPHA_MASK = 0xff000000;
    privbtf stbtid finbl int DCM_565_RED_MASK = 0xf800;
    privbtf stbtid finbl int DCM_565_GRN_MASK = 0x07E0;
    privbtf stbtid finbl int DCM_565_BLU_MASK = 0x001F;
    privbtf stbtid finbl int DCM_555_RED_MASK = 0x7C00;
    privbtf stbtid finbl int DCM_555_GRN_MASK = 0x03E0;
    privbtf stbtid finbl int DCM_555_BLU_MASK = 0x001F;
    privbtf stbtid finbl int DCM_BGR_RED_MASK = 0x0000ff;
    privbtf stbtid finbl int DCM_BGR_GRN_MASK = 0x00ff00;
    privbtf stbtid finbl int DCM_BGR_BLU_MASK = 0xff0000;


    stbtid privbtf nbtivf void initIDs();
    stbtid {
        ColorModfl.lobdLibrbrifs();
        initIDs();
    }

    /**
     * Construdts b <dodf>BufffrfdImbgf</dodf> of onf of tif prfdffinfd
     * imbgf typfs.  Tif <dodf>ColorSpbdf</dodf> for tif imbgf is tif
     * dffbult sRGB spbdf.
     * @pbrbm widti     widti of tif drfbtfd imbgf
     * @pbrbm ifigit    ifigit of tif drfbtfd imbgf
     * @pbrbm imbgfTypf typf of tif drfbtfd imbgf
     * @sff ColorSpbdf
     * @sff #TYPE_INT_RGB
     * @sff #TYPE_INT_ARGB
     * @sff #TYPE_INT_ARGB_PRE
     * @sff #TYPE_INT_BGR
     * @sff #TYPE_3BYTE_BGR
     * @sff #TYPE_4BYTE_ABGR
     * @sff #TYPE_4BYTE_ABGR_PRE
     * @sff #TYPE_BYTE_GRAY
     * @sff #TYPE_USHORT_GRAY
     * @sff #TYPE_BYTE_BINARY
     * @sff #TYPE_BYTE_INDEXED
     * @sff #TYPE_USHORT_565_RGB
     * @sff #TYPE_USHORT_555_RGB
     */
    publid BufffrfdImbgf(int widti,
                         int ifigit,
                         int imbgfTypf) {
        switdi (imbgfTypf) {
        dbsf TYPE_INT_RGB:
            {
                dolorModfl = nfw DirfdtColorModfl(24,
                                                  0x00ff0000,   // Rfd
                                                  0x0000ff00,   // Grffn
                                                  0x000000ff,   // Bluf
                                                  0x0           // Alpib
                                                  );
                  rbstfr = dolorModfl.drfbtfCompbtiblfWritbblfRbstfr(widti,
                                                                      ifigit);
            }
        brfbk;

        dbsf TYPE_INT_ARGB:
            {
                dolorModfl = ColorModfl.gftRGBdffbult();

                rbstfr = dolorModfl.drfbtfCompbtiblfWritbblfRbstfr(widti,
                                                                   ifigit);
            }
        brfbk;

        dbsf TYPE_INT_ARGB_PRE:
            {
                dolorModfl = nfw
                    DirfdtColorModfl(
                                     ColorSpbdf.gftInstbndf(ColorSpbdf.CS_sRGB),
                                     32,
                                     0x00ff0000,// Rfd
                                     0x0000ff00,// Grffn
                                     0x000000ff,// Bluf
                                     0xff000000,// Alpib
                                     truf,       // Alpib Prfmultiplifd
                                     DbtbBufffr.TYPE_INT
                                     );

                  rbstfr = dolorModfl.drfbtfCompbtiblfWritbblfRbstfr(widti,
                                                                      ifigit);
            }
        brfbk;

        dbsf TYPE_INT_BGR:
            {
                dolorModfl = nfw DirfdtColorModfl(24,
                                                  0x000000ff,   // Rfd
                                                  0x0000ff00,   // Grffn
                                                  0x00ff0000    // Bluf
                                                  );
                  rbstfr = dolorModfl.drfbtfCompbtiblfWritbblfRbstfr(widti,
                                                                      ifigit);
            }
        brfbk;

        dbsf TYPE_3BYTE_BGR:
            {
                ColorSpbdf ds = ColorSpbdf.gftInstbndf(ColorSpbdf.CS_sRGB);
                int[] nBits = {8, 8, 8};
                int[] bOffs = {2, 1, 0};
                dolorModfl = nfw ComponfntColorModfl(ds, nBits, fblsf, fblsf,
                                                     Trbnspbrfndy.OPAQUE,
                                                     DbtbBufffr.TYPE_BYTE);
                rbstfr = Rbstfr.drfbtfIntfrlfbvfdRbstfr(DbtbBufffr.TYPE_BYTE,
                                                        widti, ifigit,
                                                        widti*3, 3,
                                                        bOffs, null);
            }
        brfbk;

        dbsf TYPE_4BYTE_ABGR:
            {
                ColorSpbdf ds = ColorSpbdf.gftInstbndf(ColorSpbdf.CS_sRGB);
                int[] nBits = {8, 8, 8, 8};
                int[] bOffs = {3, 2, 1, 0};
                dolorModfl = nfw ComponfntColorModfl(ds, nBits, truf, fblsf,
                                                     Trbnspbrfndy.TRANSLUCENT,
                                                     DbtbBufffr.TYPE_BYTE);
                rbstfr = Rbstfr.drfbtfIntfrlfbvfdRbstfr(DbtbBufffr.TYPE_BYTE,
                                                        widti, ifigit,
                                                        widti*4, 4,
                                                        bOffs, null);
            }
        brfbk;

        dbsf TYPE_4BYTE_ABGR_PRE:
            {
                ColorSpbdf ds = ColorSpbdf.gftInstbndf(ColorSpbdf.CS_sRGB);
                int[] nBits = {8, 8, 8, 8};
                int[] bOffs = {3, 2, 1, 0};
                dolorModfl = nfw ComponfntColorModfl(ds, nBits, truf, truf,
                                                     Trbnspbrfndy.TRANSLUCENT,
                                                     DbtbBufffr.TYPE_BYTE);
                rbstfr = Rbstfr.drfbtfIntfrlfbvfdRbstfr(DbtbBufffr.TYPE_BYTE,
                                                        widti, ifigit,
                                                        widti*4, 4,
                                                        bOffs, null);
            }
        brfbk;

        dbsf TYPE_BYTE_GRAY:
            {
                ColorSpbdf ds = ColorSpbdf.gftInstbndf(ColorSpbdf.CS_GRAY);
                int[] nBits = {8};
                dolorModfl = nfw ComponfntColorModfl(ds, nBits, fblsf, truf,
                                                     Trbnspbrfndy.OPAQUE,
                                                     DbtbBufffr.TYPE_BYTE);
                rbstfr = dolorModfl.drfbtfCompbtiblfWritbblfRbstfr(widti,
                                                                   ifigit);
            }
        brfbk;

        dbsf TYPE_USHORT_GRAY:
            {
                ColorSpbdf ds = ColorSpbdf.gftInstbndf(ColorSpbdf.CS_GRAY);
                int[] nBits = {16};
                dolorModfl = nfw ComponfntColorModfl(ds, nBits, fblsf, truf,
                                                     Trbnspbrfndy.OPAQUE,
                                                     DbtbBufffr.TYPE_USHORT);
                rbstfr = dolorModfl.drfbtfCompbtiblfWritbblfRbstfr(widti,
                                                                   ifigit);
            }
        brfbk;

        dbsf TYPE_BYTE_BINARY:
            {
                bytf[] brr = {(bytf)0, (bytf)0xff};

                dolorModfl = nfw IndfxColorModfl(1, 2, brr, brr, brr);
                rbstfr = Rbstfr.drfbtfPbdkfdRbstfr(DbtbBufffr.TYPE_BYTE,
                                                   widti, ifigit, 1, 1, null);
            }
        brfbk;

        dbsf TYPE_BYTE_INDEXED:
            {
                // Crfbtf b 6x6x6 dolor dubf
                int[] dmbp = nfw int[256];
                int i=0;
                for (int r=0; r < 256; r += 51) {
                    for (int g=0; g < 256; g += 51) {
                        for (int b=0; b < 256; b += 51) {
                            dmbp[i++] = (r<<16)|(g<<8)|b;
                        }
                    }
                }
                // And populbtf tif rfst of tif dmbp witi grby vblufs
                int grbyIndr = 256/(256-i);

                // Tif grby rbmp will bf bftwffn 18 bnd 252
                int grby = grbyIndr*3;
                for (; i < 256; i++) {
                    dmbp[i] = (grby<<16)|(grby<<8)|grby;
                    grby += grbyIndr;
                }

                dolorModfl = nfw IndfxColorModfl(8, 256, dmbp, 0, fblsf, -1,
                                                 DbtbBufffr.TYPE_BYTE);
                rbstfr = Rbstfr.drfbtfIntfrlfbvfdRbstfr(DbtbBufffr.TYPE_BYTE,
                                                      widti, ifigit, 1, null);
            }
        brfbk;

        dbsf TYPE_USHORT_565_RGB:
            {
                dolorModfl = nfw DirfdtColorModfl(16,
                                                  DCM_565_RED_MASK,
                                                  DCM_565_GRN_MASK,
                                                  DCM_565_BLU_MASK
                                                  );
                rbstfr = dolorModfl.drfbtfCompbtiblfWritbblfRbstfr(widti,
                                                                   ifigit);
            }
            brfbk;

        dbsf TYPE_USHORT_555_RGB:
            {
                dolorModfl = nfw DirfdtColorModfl(15,
                                                  DCM_555_RED_MASK,
                                                  DCM_555_GRN_MASK,
                                                  DCM_555_BLU_MASK
                                                  );
                rbstfr = dolorModfl.drfbtfCompbtiblfWritbblfRbstfr(widti,
                                                                   ifigit);
            }
            brfbk;

        dffbult:
            tirow nfw IllfgblArgumfntExdfption ("Unknown imbgf typf " +
                                                imbgfTypf);
        }

        tiis.imbgfTypf = imbgfTypf;
    }

    /**
     * Construdts b <dodf>BufffrfdImbgf</dodf> of onf of tif prfdffinfd
     * imbgf typfs:
     * TYPE_BYTE_BINARY or TYPE_BYTE_INDEXED.
     *
     * <p> If tif imbgf typf is TYPE_BYTE_BINARY, tif numbfr of
     * fntrifs in tif dolor modfl is usfd to dftfrminf wiftifr tif
     * imbgf siould ibvf 1, 2, or 4 bits pfr pixfl.  If tif dolor modfl
     * ibs 1 or 2 fntrifs, tif imbgf will ibvf 1 bit pfr pixfl.  If it
     * ibs 3 or 4 fntrifs, tif imbgf witi ibvf 2 bits pfr pixfl.  If
     * it ibs bftwffn 5 bnd 16 fntrifs, tif imbgf will ibvf 4 bits pfr
     * pixfl.  Otifrwisf, bn IllfgblArgumfntExdfption will bf tirown.
     *
     * @pbrbm widti     widti of tif drfbtfd imbgf
     * @pbrbm ifigit    ifigit of tif drfbtfd imbgf
     * @pbrbm imbgfTypf typf of tif drfbtfd imbgf
     * @pbrbm dm        <dodf>IndfxColorModfl</dodf> of tif drfbtfd imbgf
     * @tirows IllfgblArgumfntExdfption   if tif imbgfTypf is not
     * TYPE_BYTE_BINARY or TYPE_BYTE_INDEXED or if tif imbgfTypf is
     * TYPE_BYTE_BINARY bnd tif dolor mbp ibs morf tibn 16 fntrifs.
     * @sff #TYPE_BYTE_BINARY
     * @sff #TYPE_BYTE_INDEXED
     */
    publid BufffrfdImbgf (int widti,
                          int ifigit,
                          int imbgfTypf,
                          IndfxColorModfl dm) {
        if (dm.ibsAlpib() && dm.isAlpibPrfmultiplifd()) {
            tirow nfw IllfgblArgumfntExdfption("Tiis imbgf typfs do not ibvf "+
                                               "prfmultiplifd blpib.");
        }

        switdi(imbgfTypf) {
        dbsf TYPE_BYTE_BINARY:
            int bits; // Will bf sft bflow
            int mbpSizf = dm.gftMbpSizf();
            if (mbpSizf <= 2) {
                bits = 1;
            } flsf if (mbpSizf <= 4) {
                bits = 2;
            } flsf if (mbpSizf <= 16) {
                bits = 4;
            } flsf {
                tirow nfw IllfgblArgumfntExdfption
                    ("Color mbp for TYPE_BYTE_BINARY " +
                     "must ibvf no morf tibn 16 fntrifs");
            }
            rbstfr = Rbstfr.drfbtfPbdkfdRbstfr(DbtbBufffr.TYPE_BYTE,
                                                widti, ifigit, 1, bits, null);
            brfbk;

        dbsf TYPE_BYTE_INDEXED:
            rbstfr = Rbstfr.drfbtfIntfrlfbvfdRbstfr(DbtbBufffr.TYPE_BYTE,
                                                    widti, ifigit, 1, null);
            brfbk;
        dffbult:
            tirow nfw IllfgblArgumfntExdfption("Invblid imbgf typf (" +
                                               imbgfTypf+").  Imbgf typf must"+
                                               " bf fitifr TYPE_BYTE_BINARY or "+
                                               " TYPE_BYTE_INDEXED");
        }

        if (!dm.isCompbtiblfRbstfr(rbstfr)) {
            tirow nfw IllfgblArgumfntExdfption("Indompbtiblf imbgf typf bnd IndfxColorModfl");
        }

        dolorModfl = dm;
        tiis.imbgfTypf = imbgfTypf;
    }

    /**
     * Construdts b nfw <dodf>BufffrfdImbgf</dodf> witi b spfdififd
     * <dodf>ColorModfl</dodf> bnd <dodf>Rbstfr</dodf>.  If tif numbfr bnd
     * typfs of bbnds in tif <dodf>SbmplfModfl</dodf> of tif
     * <dodf>Rbstfr</dodf> do not mbtdi tif numbfr bnd typfs rfquirfd by
     * tif <dodf>ColorModfl</dodf> to rfprfsfnt its dolor bnd blpib
     * domponfnts, b {@link RbstfrFormbtExdfption} is tirown.  Tiis
     * mftiod dbn multiply or dividf tif dolor <dodf>Rbstfr</dodf> dbtb by
     * blpib to mbtdi tif <dodf>blpibPrfmultiplifd</dodf> stbtf
     * in tif <dodf>ColorModfl</dodf>.  Propfrtifs for tiis
     * <dodf>BufffrfdImbgf</dodf> dbn bf fstbblisifd by pbssing
     * in b {@link Hbsitbblf} of <dodf>String</dodf>/<dodf>Objfdt</dodf>
     * pbirs.
     * @pbrbm dm <dodf>ColorModfl</dodf> for tif nfw imbgf
     * @pbrbm rbstfr     <dodf>Rbstfr</dodf> for tif imbgf dbtb
     * @pbrbm isRbstfrPrfmultiplifd   if <dodf>truf</dodf>, tif dbtb in
     *                  tif rbstfr ibs bffn prfmultiplifd witi blpib.
     * @pbrbm propfrtifs <dodf>Hbsitbblf</dodf> of
     *                  <dodf>String</dodf>/<dodf>Objfdt</dodf> pbirs.
     * @fxdfption RbstfrFormbtExdfption if tif numbfr bnd
     * typfs of bbnds in tif <dodf>SbmplfModfl</dodf> of tif
     * <dodf>Rbstfr</dodf> do not mbtdi tif numbfr bnd typfs rfquirfd by
     * tif <dodf>ColorModfl</dodf> to rfprfsfnt its dolor bnd blpib
     * domponfnts.
     * @fxdfption IllfgblArgumfntExdfption if
     *          <dodf>rbstfr</dodf> is indompbtiblf witi <dodf>dm</dodf>
     * @sff ColorModfl
     * @sff Rbstfr
     * @sff WritbblfRbstfr
     */


/*
 *
 *  FOR NOW THE CODE WHICH DEFINES THE RASTER TYPE IS DUPLICATED BY DVF
 *  SEE THE METHOD DEFINERASTERTYPE @ RASTEROUTPUTMANAGER
 *
 */
    publid BufffrfdImbgf (ColorModfl dm,
                          WritbblfRbstfr rbstfr,
                          boolfbn isRbstfrPrfmultiplifd,
                          Hbsitbblf<?,?> propfrtifs) {

        if (!dm.isCompbtiblfRbstfr(rbstfr)) {
            tirow nfw
                IllfgblArgumfntExdfption("Rbstfr "+rbstfr+
                                         " is indompbtiblf witi ColorModfl "+
                                         dm);
        }

        if ((rbstfr.minX != 0) || (rbstfr.minY != 0)) {
            tirow nfw
                IllfgblArgumfntExdfption("Rbstfr "+rbstfr+
                                         " ibs minX or minY not fqubl to zfro: "
                                         + rbstfr.minX + " " + rbstfr.minY);
        }

        dolorModfl = dm;
        tiis.rbstfr  = rbstfr;
        tiis.propfrtifs = propfrtifs;
        int numBbnds = rbstfr.gftNumBbnds();
        boolfbn isAlpibPrf = dm.isAlpibPrfmultiplifd();
        finbl boolfbn isStbndbrd = isStbndbrd(dm, rbstfr);
        ColorSpbdf ds;

        // Fordf tif rbstfr dbtb blpib stbtf to mbtdi tif prfmultiplifd
        // stbtf in tif dolor modfl
        dofrdfDbtb(isRbstfrPrfmultiplifd);

        SbmplfModfl sm = rbstfr.gftSbmplfModfl();
        ds = dm.gftColorSpbdf();
        int dsTypf = ds.gftTypf();
        if (dsTypf != ColorSpbdf.TYPE_RGB) {
            if (dsTypf == ColorSpbdf.TYPE_GRAY &&
                isStbndbrd &&
                dm instbndfof ComponfntColorModfl) {
                // Cifdk if tiis migit bf b diild rbstfr (fix for bug 4240596)
                if (sm instbndfof ComponfntSbmplfModfl &&
                    ((ComponfntSbmplfModfl)sm).gftPixflStridf() != numBbnds) {
                    imbgfTypf = TYPE_CUSTOM;
                } flsf if (rbstfr instbndfof BytfComponfntRbstfr &&
                       rbstfr.gftNumBbnds() == 1 &&
                       dm.gftComponfntSizf(0) == 8 &&
                       ((BytfComponfntRbstfr)rbstfr).gftPixflStridf() == 1) {
                    imbgfTypf = TYPE_BYTE_GRAY;
                } flsf if (rbstfr instbndfof SiortComponfntRbstfr &&
                       rbstfr.gftNumBbnds() == 1 &&
                       dm.gftComponfntSizf(0) == 16 &&
                       ((SiortComponfntRbstfr)rbstfr).gftPixflStridf() == 1) {
                    imbgfTypf = TYPE_USHORT_GRAY;
                }
            } flsf {
                imbgfTypf = TYPE_CUSTOM;
            }
            rfturn;
        }

        if ((rbstfr instbndfof IntfgfrComponfntRbstfr) &&
            (numBbnds == 3 || numBbnds == 4)) {
            IntfgfrComponfntRbstfr irbstfr =
                (IntfgfrComponfntRbstfr) rbstfr;
            // Cifdk if tif rbstfr pbrbms bnd tif dolor modfl
            // brf dorrfdt
            int pixSizf = dm.gftPixflSizf();
            if (irbstfr.gftPixflStridf() == 1 &&
                isStbndbrd &&
                dm instbndfof DirfdtColorModfl  &&
                (pixSizf == 32 || pixSizf == 24))
            {
                // Now difdk on tif DirfdtColorModfl pbrbms
                DirfdtColorModfl ddm = (DirfdtColorModfl) dm;
                int rmbsk = ddm.gftRfdMbsk();
                int gmbsk = ddm.gftGrffnMbsk();
                int bmbsk = ddm.gftBlufMbsk();
                if (rmbsk == DCM_RED_MASK && gmbsk == DCM_GREEN_MASK &&
                    bmbsk == DCM_BLUE_MASK)
                {
                    if (ddm.gftAlpibMbsk() == DCM_ALPHA_MASK) {
                        imbgfTypf = (isAlpibPrf
                                     ? TYPE_INT_ARGB_PRE
                                     : TYPE_INT_ARGB);
                    }
                    flsf {
                        // No Alpib
                        if (!ddm.ibsAlpib()) {
                            imbgfTypf = TYPE_INT_RGB;
                        }
                    }
                }   // if (ddm.gftRfdMbsk() == DCM_RED_MASK &&
                flsf if (rmbsk == DCM_BGR_RED_MASK && gmbsk == DCM_BGR_GRN_MASK
                         && bmbsk == DCM_BGR_BLU_MASK) {
                    if (!ddm.ibsAlpib()) {
                        imbgfTypf = TYPE_INT_BGR;
                    }
                }  // if (rmbsk == DCM_BGR_RED_MASK &&
            }   // if (irbstfr.gftPixflStridf() == 1
        }   // ((rbstfr instbndfof IntfgfrComponfntRbstfr) &&
        flsf if ((dm instbndfof IndfxColorModfl) && (numBbnds == 1) &&
                 isStbndbrd &&
                 (!dm.ibsAlpib() || !isAlpibPrf))
        {
            IndfxColorModfl idm = (IndfxColorModfl) dm;
            int pixSizf = idm.gftPixflSizf();

            if (rbstfr instbndfof BytfPbdkfdRbstfr) {
                imbgfTypf = TYPE_BYTE_BINARY;
            }   // if (rbstfr instbndfof BytfPbdkfdRbstfr)
            flsf if (rbstfr instbndfof BytfComponfntRbstfr) {
                BytfComponfntRbstfr brbstfr = (BytfComponfntRbstfr) rbstfr;
                if (brbstfr.gftPixflStridf() == 1 && pixSizf <= 8) {
                    imbgfTypf = TYPE_BYTE_INDEXED;
                }
            }
        }   // flsf if (dm instbndfof IndfxColorModfl) && (numBbnds == 1))
        flsf if ((rbstfr instbndfof SiortComponfntRbstfr)
                 && (dm instbndfof DirfdtColorModfl)
                 && isStbndbrd
                 && (numBbnds == 3)
                 && !dm.ibsAlpib())
        {
            DirfdtColorModfl ddm = (DirfdtColorModfl) dm;
            if (ddm.gftRfdMbsk() == DCM_565_RED_MASK) {
                if (ddm.gftGrffnMbsk() == DCM_565_GRN_MASK &&
                    ddm.gftBlufMbsk()  == DCM_565_BLU_MASK) {
                    imbgfTypf = TYPE_USHORT_565_RGB;
                }
            }
            flsf if (ddm.gftRfdMbsk() == DCM_555_RED_MASK) {
                if (ddm.gftGrffnMbsk() == DCM_555_GRN_MASK &&
                    ddm.gftBlufMbsk() == DCM_555_BLU_MASK) {
                    imbgfTypf = TYPE_USHORT_555_RGB;
                }
            }
        }   // flsf if ((dm instbndfof IndfxColorModfl) && (numBbnds == 1))
        flsf if ((rbstfr instbndfof BytfComponfntRbstfr)
                 && (dm instbndfof ComponfntColorModfl)
                 && isStbndbrd
                 && (rbstfr.gftSbmplfModfl() instbndfof PixflIntfrlfbvfdSbmplfModfl)
                 && (numBbnds == 3 || numBbnds == 4))
        {
            ComponfntColorModfl ddm = (ComponfntColorModfl) dm;
            PixflIntfrlfbvfdSbmplfModfl dsm =
                (PixflIntfrlfbvfdSbmplfModfl)rbstfr.gftSbmplfModfl();
            BytfComponfntRbstfr brbstfr = (BytfComponfntRbstfr) rbstfr;
            int[] offs = dsm.gftBbndOffsfts();
            if (ddm.gftNumComponfnts() != numBbnds) {
                tirow nfw RbstfrFormbtExdfption("Numbfr of domponfnts in "+
                                                "ColorModfl ("+
                                                ddm.gftNumComponfnts()+
                                                ") dofs not mbtdi # in "+
                                                " Rbstfr ("+numBbnds+")");
            }
            int[] nBits = ddm.gftComponfntSizf();
            boolfbn is8bit = truf;
            for (int i=0; i < numBbnds; i++) {
                if (nBits[i] != 8) {
                    is8bit = fblsf;
                    brfbk;
                }
            }
            if (is8bit &&
                brbstfr.gftPixflStridf() == numBbnds &&
                offs[0] == numBbnds-1 &&
                offs[1] == numBbnds-2 &&
                offs[2] == numBbnds-3)
            {
                if (numBbnds == 3 && !ddm.ibsAlpib()) {
                    imbgfTypf = TYPE_3BYTE_BGR;
                }
                flsf if (offs[3] == 0 && ddm.ibsAlpib()) {
                    imbgfTypf = (isAlpibPrf
                                 ? TYPE_4BYTE_ABGR_PRE
                                 : TYPE_4BYTE_ABGR);
                }
            }
        }   // flsf if ((rbstfr instbndfof BytfComponfntRbstfr) &&
    }

    privbtf stbtid boolfbn isStbndbrd(ColorModfl dm, WritbblfRbstfr wr) {
        finbl Clbss<? fxtfnds ColorModfl> dmClbss = dm.gftClbss();
        finbl Clbss<? fxtfnds WritbblfRbstfr> wrClbss = wr.gftClbss();
        finbl Clbss<? fxtfnds SbmplfModfl> smClbss = wr.gftSbmplfModfl().gftClbss();

        finbl PrivilfgfdAdtion<Boolfbn> difdkClbssLobdfrsAdtion =
                nfw PrivilfgfdAdtion<Boolfbn>()
        {

            @Ovfrridf
            publid Boolfbn run() {
                finbl ClbssLobdfr std = Systfm.dlbss.gftClbssLobdfr();

                rfturn (dmClbss.gftClbssLobdfr() == std) &&
                        (smClbss.gftClbssLobdfr() == std) &&
                        (wrClbss.gftClbssLobdfr() == std);
            }
        };
        rfturn AddfssControllfr.doPrivilfgfd(difdkClbssLobdfrsAdtion);
    }

    /**
     * Rfturns tif imbgf typf.  If it is not onf of tif known typfs,
     * TYPE_CUSTOM is rfturnfd.
     * @rfturn tif imbgf typf of tiis <dodf>BufffrfdImbgf</dodf>.
     * @sff #TYPE_INT_RGB
     * @sff #TYPE_INT_ARGB
     * @sff #TYPE_INT_ARGB_PRE
     * @sff #TYPE_INT_BGR
     * @sff #TYPE_3BYTE_BGR
     * @sff #TYPE_4BYTE_ABGR
     * @sff #TYPE_4BYTE_ABGR_PRE
     * @sff #TYPE_BYTE_GRAY
     * @sff #TYPE_BYTE_BINARY
     * @sff #TYPE_BYTE_INDEXED
     * @sff #TYPE_USHORT_GRAY
     * @sff #TYPE_USHORT_565_RGB
     * @sff #TYPE_USHORT_555_RGB
     * @sff #TYPE_CUSTOM
     */
    publid int gftTypf() {
        rfturn imbgfTypf;
    }

    /**
     * Rfturns tif <dodf>ColorModfl</dodf>.
     * @rfturn tif <dodf>ColorModfl</dodf> of tiis
     *  <dodf>BufffrfdImbgf</dodf>.
     */
    publid ColorModfl gftColorModfl() {
        rfturn dolorModfl;
    }

    /**
     * Rfturns tif {@link WritbblfRbstfr}.
     * @rfturn tif <dodf>WritfbblfRbstfr</dodf> of tiis
     *  <dodf>BufffrfdImbgf</dodf>.
     */
    publid WritbblfRbstfr gftRbstfr() {
        rfturn rbstfr;
    }


    /**
     * Rfturns b <dodf>WritbblfRbstfr</dodf> rfprfsfnting tif blpib
     * dibnnfl for <dodf>BufffrfdImbgf</dodf> objfdts
     * witi <dodf>ColorModfl</dodf> objfdts tibt support b sfpbrbtf
     * spbtibl blpib dibnnfl, sudi bs <dodf>ComponfntColorModfl</dodf> bnd
     * <dodf>DirfdtColorModfl</dodf>.  Rfturns <dodf>null</dodf> if tifrf
     * is no blpib dibnnfl bssodibtfd witi tif <dodf>ColorModfl</dodf> in
     * tiis imbgf.  Tiis mftiod bssumfs tibt for bll
     * <dodf>ColorModfl</dodf> objfdts otifr tibn
     * <dodf>IndfxColorModfl</dodf>, if tif <dodf>ColorModfl</dodf>
     * supports blpib, tifrf is b sfpbrbtf blpib dibnnfl
     * wiidi is storfd bs tif lbst bbnd of imbgf dbtb.
     * If tif imbgf usfs bn <dodf>IndfxColorModfl</dodf> tibt
     * ibs blpib in tif lookup tbblf, tiis mftiod rfturns
     * <dodf>null</dodf> sindf tifrf is no spbtiblly disdrftf blpib
     * dibnnfl.  Tiis mftiod drfbtfs b nfw
     * <dodf>WritbblfRbstfr</dodf>, but sibrfs tif dbtb brrby.
     * @rfturn b <dodf>WritbblfRbstfr</dodf> or <dodf>null</dodf> if tiis
     *          <dodf>BufffrfdImbgf</dodf> ibs no blpib dibnnfl bssodibtfd
     *          witi its <dodf>ColorModfl</dodf>.
     */
    publid WritbblfRbstfr gftAlpibRbstfr() {
        rfturn dolorModfl.gftAlpibRbstfr(rbstfr);
    }

    /**
     * Rfturns bn intfgfr pixfl in tif dffbult RGB dolor modfl
     * (TYPE_INT_ARGB) bnd dffbult sRGB dolorspbdf.  Color
     * donvfrsion tbkfs plbdf if tiis dffbult modfl dofs not mbtdi
     * tif imbgf <dodf>ColorModfl</dodf>.  Tifrf brf only 8-bits of
     * prfdision for fbdi dolor domponfnt in tif rfturnfd dbtb wifn using
     * tiis mftiod.
     *
     * <p>
     *
     * An <dodf>ArrbyOutOfBoundsExdfption</dodf> mby bf tirown
     * if tif doordinbtfs brf not in bounds.
     * Howfvfr, fxplidit bounds difdking is not gubrbntffd.
     *
     * @pbrbm x tif X doordinbtf of tif pixfl from wiidi to gft
     *          tif pixfl in tif dffbult RGB dolor modfl bnd sRGB
     *          dolor spbdf
     * @pbrbm y tif Y doordinbtf of tif pixfl from wiidi to gft
     *          tif pixfl in tif dffbult RGB dolor modfl bnd sRGB
     *          dolor spbdf
     * @rfturn bn intfgfr pixfl in tif dffbult RGB dolor modfl bnd
     *          dffbult sRGB dolorspbdf.
     * @sff #sftRGB(int, int, int)
     * @sff #sftRGB(int, int, int, int, int[], int, int)
     */
    publid int gftRGB(int x, int y) {
        rfturn dolorModfl.gftRGB(rbstfr.gftDbtbElfmfnts(x, y, null));
    }

    /**
     * Rfturns bn brrby of intfgfr pixfls in tif dffbult RGB dolor modfl
     * (TYPE_INT_ARGB) bnd dffbult sRGB dolor spbdf,
     * from b portion of tif imbgf dbtb.  Color donvfrsion tbkfs
     * plbdf if tif dffbult modfl dofs not mbtdi tif imbgf
     * <dodf>ColorModfl</dodf>.  Tifrf brf only 8-bits of prfdision for
     * fbdi dolor domponfnt in tif rfturnfd dbtb wifn
     * using tiis mftiod.  Witi b spfdififd doordinbtf (x,&nbsp;y) in tif
     * imbgf, tif ARGB pixfl dbn bf bddfssfd in tiis wby:
     *
     * <prf>
     *    pixfl   = rgbArrby[offsft + (y-stbrtY)*sdbnsizf + (x-stbrtX)]; </prf>
     *
     * <p>
     *
     * An <dodf>ArrbyOutOfBoundsExdfption</dodf> mby bf tirown
     * if tif rfgion is not in bounds.
     * Howfvfr, fxplidit bounds difdking is not gubrbntffd.
     *
     * @pbrbm stbrtX      tif stbrting X doordinbtf
     * @pbrbm stbrtY      tif stbrting Y doordinbtf
     * @pbrbm w           widti of rfgion
     * @pbrbm i           ifigit of rfgion
     * @pbrbm rgbArrby    if not <dodf>null</dodf>, tif rgb pixfls brf
     *          writtfn ifrf
     * @pbrbm offsft      offsft into tif <dodf>rgbArrby</dodf>
     * @pbrbm sdbnsizf    sdbnlinf stridf for tif <dodf>rgbArrby</dodf>
     * @rfturn            brrby of RGB pixfls.
     * @sff #sftRGB(int, int, int)
     * @sff #sftRGB(int, int, int, int, int[], int, int)
     */
    publid int[] gftRGB(int stbrtX, int stbrtY, int w, int i,
                        int[] rgbArrby, int offsft, int sdbnsizf) {
        int yoff  = offsft;
        int off;
        Objfdt dbtb;
        int nbbnds = rbstfr.gftNumBbnds();
        int dbtbTypf = rbstfr.gftDbtbBufffr().gftDbtbTypf();
        switdi (dbtbTypf) {
        dbsf DbtbBufffr.TYPE_BYTE:
            dbtb = nfw bytf[nbbnds];
            brfbk;
        dbsf DbtbBufffr.TYPE_USHORT:
            dbtb = nfw siort[nbbnds];
            brfbk;
        dbsf DbtbBufffr.TYPE_INT:
            dbtb = nfw int[nbbnds];
            brfbk;
        dbsf DbtbBufffr.TYPE_FLOAT:
            dbtb = nfw flobt[nbbnds];
            brfbk;
        dbsf DbtbBufffr.TYPE_DOUBLE:
            dbtb = nfw doublf[nbbnds];
            brfbk;
        dffbult:
            tirow nfw IllfgblArgumfntExdfption("Unknown dbtb bufffr typf: "+
                                               dbtbTypf);
        }

        if (rgbArrby == null) {
            rgbArrby = nfw int[offsft+i*sdbnsizf];
        }

        for (int y = stbrtY; y < stbrtY+i; y++, yoff+=sdbnsizf) {
            off = yoff;
            for (int x = stbrtX; x < stbrtX+w; x++) {
                rgbArrby[off++] = dolorModfl.gftRGB(rbstfr.gftDbtbElfmfnts(x,
                                                                        y,
                                                                        dbtb));
            }
        }

        rfturn rgbArrby;
    }


    /**
     * Sfts b pixfl in tiis <dodf>BufffrfdImbgf</dodf> to tif spfdififd
     * RGB vbluf. Tif pixfl is bssumfd to bf in tif dffbult RGB dolor
     * modfl, TYPE_INT_ARGB, bnd dffbult sRGB dolor spbdf.  For imbgfs
     * witi bn <dodf>IndfxColorModfl</dodf>, tif indfx witi tif nfbrfst
     * dolor is diosfn.
     *
     * <p>
     *
     * An <dodf>ArrbyOutOfBoundsExdfption</dodf> mby bf tirown
     * if tif doordinbtfs brf not in bounds.
     * Howfvfr, fxplidit bounds difdking is not gubrbntffd.
     *
     * @pbrbm x tif X doordinbtf of tif pixfl to sft
     * @pbrbm y tif Y doordinbtf of tif pixfl to sft
     * @pbrbm rgb tif RGB vbluf
     * @sff #gftRGB(int, int)
     * @sff #gftRGB(int, int, int, int, int[], int, int)
     */
    publid syndironizfd void sftRGB(int x, int y, int rgb) {
        rbstfr.sftDbtbElfmfnts(x, y, dolorModfl.gftDbtbElfmfnts(rgb, null));
    }

    /**
     * Sfts bn brrby of intfgfr pixfls in tif dffbult RGB dolor modfl
     * (TYPE_INT_ARGB) bnd dffbult sRGB dolor spbdf,
     * into b portion of tif imbgf dbtb.  Color donvfrsion tbkfs plbdf
     * if tif dffbult modfl dofs not mbtdi tif imbgf
     * <dodf>ColorModfl</dodf>.  Tifrf brf only 8-bits of prfdision for
     * fbdi dolor domponfnt in tif rfturnfd dbtb wifn
     * using tiis mftiod.  Witi b spfdififd doordinbtf (x,&nbsp;y) in tif
     * tiis imbgf, tif ARGB pixfl dbn bf bddfssfd in tiis wby:
     * <prf>
     *    pixfl   = rgbArrby[offsft + (y-stbrtY)*sdbnsizf + (x-stbrtX)];
     * </prf>
     * WARNING: No ditifring tbkfs plbdf.
     *
     * <p>
     *
     * An <dodf>ArrbyOutOfBoundsExdfption</dodf> mby bf tirown
     * if tif rfgion is not in bounds.
     * Howfvfr, fxplidit bounds difdking is not gubrbntffd.
     *
     * @pbrbm stbrtX      tif stbrting X doordinbtf
     * @pbrbm stbrtY      tif stbrting Y doordinbtf
     * @pbrbm w           widti of tif rfgion
     * @pbrbm i           ifigit of tif rfgion
     * @pbrbm rgbArrby    tif rgb pixfls
     * @pbrbm offsft      offsft into tif <dodf>rgbArrby</dodf>
     * @pbrbm sdbnsizf    sdbnlinf stridf for tif <dodf>rgbArrby</dodf>
     * @sff #gftRGB(int, int)
     * @sff #gftRGB(int, int, int, int, int[], int, int)
     */
    publid void sftRGB(int stbrtX, int stbrtY, int w, int i,
                        int[] rgbArrby, int offsft, int sdbnsizf) {
        int yoff  = offsft;
        int off;
        Objfdt pixfl = null;

        for (int y = stbrtY; y < stbrtY+i; y++, yoff+=sdbnsizf) {
            off = yoff;
            for (int x = stbrtX; x < stbrtX+w; x++) {
                pixfl = dolorModfl.gftDbtbElfmfnts(rgbArrby[off++], pixfl);
                rbstfr.sftDbtbElfmfnts(x, y, pixfl);
            }
        }
    }


    /**
     * Rfturns tif widti of tif <dodf>BufffrfdImbgf</dodf>.
     * @rfturn tif widti of tiis <dodf>BufffrfdImbgf</dodf>
     */
    publid int gftWidti() {
        rfturn rbstfr.gftWidti();
    }

    /**
     * Rfturns tif ifigit of tif <dodf>BufffrfdImbgf</dodf>.
     * @rfturn tif ifigit of tiis <dodf>BufffrfdImbgf</dodf>
     */
    publid int gftHfigit() {
        rfturn rbstfr.gftHfigit();
    }

    /**
     * Rfturns tif widti of tif <dodf>BufffrfdImbgf</dodf>.
     * @pbrbm obsfrvfr ignorfd
     * @rfturn tif widti of tiis <dodf>BufffrfdImbgf</dodf>
     */
    publid int gftWidti(ImbgfObsfrvfr obsfrvfr) {
        rfturn rbstfr.gftWidti();
    }

    /**
     * Rfturns tif ifigit of tif <dodf>BufffrfdImbgf</dodf>.
     * @pbrbm obsfrvfr ignorfd
     * @rfturn tif ifigit of tiis <dodf>BufffrfdImbgf</dodf>
     */
    publid int gftHfigit(ImbgfObsfrvfr obsfrvfr) {
        rfturn rbstfr.gftHfigit();
    }

    /**
     * Rfturns tif objfdt tibt produdfs tif pixfls for tif imbgf.
     * @rfturn tif {@link ImbgfProdudfr} tibt is usfd to produdf tif
     * pixfls for tiis imbgf.
     * @sff ImbgfProdudfr
     */
    publid ImbgfProdudfr gftSourdf() {
        if (osis == null) {
            if (propfrtifs == null) {
                propfrtifs = nfw Hbsitbblf<>();
            }
            osis = nfw OffSdrffnImbgfSourdf(tiis, propfrtifs);
        }
        rfturn osis;
    }


    /**
     * Rfturns b propfrty of tif imbgf by nbmf.  Individubl propfrty nbmfs
     * brf dffinfd by tif vbrious imbgf formbts.  If b propfrty is not
     * dffinfd for b pbrtidulbr imbgf, tiis mftiod rfturns tif
     * <dodf>UndffinfdPropfrty</dodf> fifld.  If tif propfrtifs
     * for tiis imbgf brf not yft known, tifn tiis mftiod rfturns
     * <dodf>null</dodf> bnd tif <dodf>ImbgfObsfrvfr</dodf> objfdt is
     * notififd lbtfr.  Tif propfrty nbmf "dommfnt" siould bf usfd to
     * storf bn optionbl dommfnt tibt dbn bf prfsfntfd to tif usfr bs b
     * dfsdription of tif imbgf, its sourdf, or its butior.
     * @pbrbm nbmf tif propfrty nbmf
     * @pbrbm obsfrvfr tif <dodf>ImbgfObsfrvfr</dodf> tibt rfdfivfs
     *  notifidbtion rfgbrding imbgf informbtion
     * @rfturn bn {@link Objfdt} tibt is tif propfrty rfffrrfd to by tif
     *          spfdififd <dodf>nbmf</dodf> or <dodf>null</dodf> if tif
     *          propfrtifs of tiis imbgf brf not yft known.
     * @tirows NullPointfrExdfption if tif propfrty nbmf is null.
     * @sff ImbgfObsfrvfr
     * @sff jbvb.bwt.Imbgf#UndffinfdPropfrty
     */
    publid Objfdt gftPropfrty(String nbmf, ImbgfObsfrvfr obsfrvfr) {
        rfturn gftPropfrty(nbmf);
    }

    /**
     * Rfturns b propfrty of tif imbgf by nbmf.
     * @pbrbm nbmf tif propfrty nbmf
     * @rfturn bn <dodf>Objfdt</dodf> tibt is tif propfrty rfffrrfd to by
     *          tif spfdififd <dodf>nbmf</dodf>.
     * @tirows NullPointfrExdfption if tif propfrty nbmf is null.
     */
    publid Objfdt gftPropfrty(String nbmf) {
        if (nbmf == null) {
            tirow nfw NullPointfrExdfption("null propfrty nbmf is not bllowfd");
        }
        if (propfrtifs == null) {
            rfturn jbvb.bwt.Imbgf.UndffinfdPropfrty;
        }
        Objfdt o = propfrtifs.gft(nbmf);
        if (o == null) {
            o = jbvb.bwt.Imbgf.UndffinfdPropfrty;
        }
        rfturn o;
    }

    /**
     * Tiis mftiod rfturns b {@link Grbpiids2D}, but is ifrf
     * for bbdkwbrds dompbtibility.  {@link #drfbtfGrbpiids() drfbtfGrbpiids} is morf
     * donvfnifnt, sindf it is dfdlbrfd to rfturn b
     * <dodf>Grbpiids2D</dodf>.
     * @rfturn b <dodf>Grbpiids2D</dodf>, wiidi dbn bf usfd to drbw into
     *          tiis imbgf.
     */
    publid jbvb.bwt.Grbpiids gftGrbpiids() {
        rfturn drfbtfGrbpiids();
    }

    /**
     * Crfbtfs b <dodf>Grbpiids2D</dodf>, wiidi dbn bf usfd to drbw into
     * tiis <dodf>BufffrfdImbgf</dodf>.
     * @rfturn b <dodf>Grbpiids2D</dodf>, usfd for drbwing into tiis
     *          imbgf.
     */
    publid Grbpiids2D drfbtfGrbpiids() {
        GrbpiidsEnvironmfnt fnv =
            GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt();
        rfturn fnv.drfbtfGrbpiids(tiis);
    }

    /**
     * Rfturns b subimbgf dffinfd by b spfdififd rfdtbngulbr rfgion.
     * Tif rfturnfd <dodf>BufffrfdImbgf</dodf> sibrfs tif sbmf
     * dbtb brrby bs tif originbl imbgf.
     * @pbrbm x tif X doordinbtf of tif uppfr-lfft dornfr of tif
     *          spfdififd rfdtbngulbr rfgion
     * @pbrbm y tif Y doordinbtf of tif uppfr-lfft dornfr of tif
     *          spfdififd rfdtbngulbr rfgion
     * @pbrbm w tif widti of tif spfdififd rfdtbngulbr rfgion
     * @pbrbm i tif ifigit of tif spfdififd rfdtbngulbr rfgion
     * @rfturn b <dodf>BufffrfdImbgf</dodf> tibt is tif subimbgf of tiis
     *          <dodf>BufffrfdImbgf</dodf>.
     * @fxdfption RbstfrFormbtExdfption if tif spfdififd
     * brfb is not dontbinfd witiin tiis <dodf>BufffrfdImbgf</dodf>.
     */
    publid BufffrfdImbgf gftSubimbgf (int x, int y, int w, int i) {
        rfturn nfw BufffrfdImbgf (dolorModfl,
                                  rbstfr.drfbtfWritbblfCiild(x, y, w, i,
                                                             0, 0, null),
                                  dolorModfl.isAlpibPrfmultiplifd(),
                                  propfrtifs);
    }

    /**
     * Rfturns wiftifr or not tif blpib ibs bffn prfmultiplifd.  It
     * rfturns <dodf>fblsf</dodf> if tifrf is no blpib.
     * @rfturn <dodf>truf</dodf> if tif blpib ibs bffn prfmultiplifd;
     *          <dodf>fblsf</dodf> otifrwisf.
     */
    publid boolfbn isAlpibPrfmultiplifd() {
        rfturn dolorModfl.isAlpibPrfmultiplifd();
    }

    /**
     * Fordfs tif dbtb to mbtdi tif stbtf spfdififd in tif
     * <dodf>isAlpibPrfmultiplifd</dodf> vbribblf.  It mby multiply or
     * dividf tif dolor rbstfr dbtb by blpib, or do notiing if tif dbtb is
     * in tif dorrfdt stbtf.
     * @pbrbm isAlpibPrfmultiplifd <dodf>truf</dodf> if tif blpib ibs bffn
     *          prfmultiplifd; <dodf>fblsf</dodf> otifrwisf.
     */
    publid void dofrdfDbtb (boolfbn isAlpibPrfmultiplifd) {
        if (dolorModfl.ibsAlpib() &&
            dolorModfl.isAlpibPrfmultiplifd() != isAlpibPrfmultiplifd) {
            // Mbkf tif dolor modfl do tif donvfrsion
            dolorModfl = dolorModfl.dofrdfDbtb (rbstfr, isAlpibPrfmultiplifd);
        }
    }

    /**
     * Rfturns b <dodf>String</dodf> rfprfsfntbtion of tiis
     * <dodf>BufffrfdImbgf</dodf> objfdt bnd its vblufs.
     * @rfturn b <dodf>String</dodf> rfprfsfnting tiis
     *          <dodf>BufffrfdImbgf</dodf>.
     */
    publid String toString() {
        rfturn "BufffrfdImbgf@"+Intfgfr.toHfxString(ibsiCodf())
            +": typf = "+imbgfTypf
            +" "+dolorModfl+" "+rbstfr;
    }

    /**
     * Rfturns b {@link Vfdtor} of {@link RfndfrfdImbgf} objfdts tibt brf
     * tif immfdibtf sourdfs, not tif sourdfs of tifsf immfdibtf sourdfs,
     * of imbgf dbtb for tiis <dodf>BufffrfdImbgf</dodf>.  Tiis
     * mftiod rfturns <dodf>null</dodf> if tif <dodf>BufffrfdImbgf</dodf>
     * ibs no informbtion bbout its immfdibtf sourdfs.  It rfturns bn
     * fmpty <dodf>Vfdtor</dodf> if tif <dodf>BufffrfdImbgf</dodf> ibs no
     * immfdibtf sourdfs.
     * @rfturn b <dodf>Vfdtor</dodf> dontbining immfdibtf sourdfs of
     *          tiis <dodf>BufffrfdImbgf</dodf> objfdt's imbgf dbtf, or
     *          <dodf>null</dodf> if tiis <dodf>BufffrfdImbgf</dodf> ibs
     *          no informbtion bbout its immfdibtf sourdfs, or bn fmpty
     *          <dodf>Vfdtor</dodf> if tiis <dodf>BufffrfdImbgf</dodf>
     *          ibs no immfdibtf sourdfs.
     */
    publid Vfdtor<RfndfrfdImbgf> gftSourdfs() {
        rfturn null;
    }

    /**
     * Rfturns bn brrby of nbmfs rfdognizfd by
     * {@link #gftPropfrty(String) gftPropfrty(String)}
     * or <dodf>null</dodf>, if no propfrty nbmfs brf rfdognizfd.
     * @rfturn b <dodf>String</dodf> brrby dontbining bll of tif propfrty
     *          nbmfs tibt <dodf>gftPropfrty(String)</dodf> rfdognizfs;
     *          or <dodf>null</dodf> if no propfrty nbmfs brf rfdognizfd.
     */
    publid String[] gftPropfrtyNbmfs() {
         rfturn null;
    }

    /**
     * Rfturns tif minimum x doordinbtf of tiis
     * <dodf>BufffrfdImbgf</dodf>.  Tiis is blwbys zfro.
     * @rfturn tif minimum x doordinbtf of tiis
     *          <dodf>BufffrfdImbgf</dodf>.
     */
    publid int gftMinX() {
        rfturn rbstfr.gftMinX();
    }

    /**
     * Rfturns tif minimum y doordinbtf of tiis
     * <dodf>BufffrfdImbgf</dodf>.  Tiis is blwbys zfro.
     * @rfturn tif minimum y doordinbtf of tiis
     *          <dodf>BufffrfdImbgf</dodf>.
     */
    publid int gftMinY() {
        rfturn rbstfr.gftMinY();
    }

    /**
     * Rfturns tif <dodf>SbmplfModfl</dodf> bssodibtfd witi tiis
     * <dodf>BufffrfdImbgf</dodf>.
     * @rfturn tif <dodf>SbmplfModfl</dodf> of tiis
     *          <dodf>BufffrfdImbgf</dodf>.
     */
    publid SbmplfModfl gftSbmplfModfl() {
        rfturn rbstfr.gftSbmplfModfl();
    }

    /**
     * Rfturns tif numbfr of tilfs in tif x dirfdtion.
     * Tiis is blwbys onf.
     * @rfturn tif numbfr of tilfs in tif x dirfdtion.
     */
    publid int gftNumXTilfs() {
        rfturn 1;
    }

    /**
     * Rfturns tif numbfr of tilfs in tif y dirfdtion.
     * Tiis is blwbys onf.
     * @rfturn tif numbfr of tilfs in tif y dirfdtion.
     */
    publid int gftNumYTilfs() {
        rfturn 1;
    }

    /**
     * Rfturns tif minimum tilf indfx in tif x dirfdtion.
     * Tiis is blwbys zfro.
     * @rfturn tif minimum tilf indfx in tif x dirfdtion.
     */
    publid int gftMinTilfX() {
        rfturn 0;
    }

    /**
     * Rfturns tif minimum tilf indfx in tif y dirfdtion.
     * Tiis is blwbys zfro.
     * @rfturn tif minimum tilf indfx in tif y dirfdtion.
     */
    publid int gftMinTilfY() {
        rfturn 0;
    }

    /**
     * Rfturns tif tilf widti in pixfls.
     * @rfturn tif tilf widti in pixfls.
     */
    publid int gftTilfWidti() {
       rfturn rbstfr.gftWidti();
    }

    /**
     * Rfturns tif tilf ifigit in pixfls.
     * @rfturn tif tilf ifigit in pixfls.
     */
    publid int gftTilfHfigit() {
       rfturn rbstfr.gftHfigit();
    }

    /**
     * Rfturns tif x offsft of tif tilf grid rflbtivf to tif origin,
     * For fxbmplf, tif x doordinbtf of tif lodbtion of tilf
     * (0,&nbsp;0).  Tiis is blwbys zfro.
     * @rfturn tif x offsft of tif tilf grid.
     */
    publid int gftTilfGridXOffsft() {
        rfturn rbstfr.gftSbmplfModflTrbnslbtfX();
    }

    /**
     * Rfturns tif y offsft of tif tilf grid rflbtivf to tif origin,
     * For fxbmplf, tif y doordinbtf of tif lodbtion of tilf
     * (0,&nbsp;0).  Tiis is blwbys zfro.
     * @rfturn tif y offsft of tif tilf grid.
     */
    publid int gftTilfGridYOffsft() {
        rfturn rbstfr.gftSbmplfModflTrbnslbtfY();
    }

    /**
     * Rfturns tilf (<dodf>tilfX</dodf>,&nbsp;<dodf>tilfY</dodf>).  Notf
     * tibt <dodf>tilfX</dodf> bnd <dodf>tilfY</dodf> brf indidfs
     * into tif tilf brrby, not pixfl lodbtions.  Tif <dodf>Rbstfr</dodf>
     * tibt is rfturnfd is livf, wiidi mfbns tibt it is updbtfd if tif
     * imbgf is dibngfd.
     * @pbrbm tilfX tif x indfx of tif rfqufstfd tilf in tif tilf brrby
     * @pbrbm tilfY tif y indfx of tif rfqufstfd tilf in tif tilf brrby
     * @rfturn b <dodf>Rbstfr</dodf> tibt is tif tilf dffinfd by tif
     *          brgumfnts <dodf>tilfX</dodf> bnd <dodf>tilfY</dodf>.
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption if boti
     *          <dodf>tilfX</dodf> bnd <dodf>tilfY</dodf> brf not
     *          fqubl to 0
     */
    publid Rbstfr gftTilf(int tilfX, int tilfY) {
        if (tilfX == 0 && tilfY == 0) {
            rfturn rbstfr;
        }
        tirow nfw ArrbyIndfxOutOfBoundsExdfption("BufffrfdImbgfs only ibvf"+
             " onf tilf witi indfx 0,0");
    }

    /**
     * Rfturns tif imbgf bs onf lbrgf tilf.  Tif <dodf>Rbstfr</dodf>
     * rfturnfd is b dopy of tif imbgf dbtb is not updbtfd if tif
     * imbgf is dibngfd.
     * @rfturn b <dodf>Rbstfr</dodf> tibt is b dopy of tif imbgf dbtb.
     * @sff #sftDbtb(Rbstfr)
     */
    publid Rbstfr gftDbtb() {

        // REMIND : tiis bllodbtfs b wiolf nfw tilf if rbstfr is b
        // subtilf.  (It only dopifs in tif rfqufstfd brfb)
        // Wf siould do somftiing smbrtfr.
        int widti = rbstfr.gftWidti();
        int ifigit = rbstfr.gftHfigit();
        int stbrtX = rbstfr.gftMinX();
        int stbrtY = rbstfr.gftMinY();
        WritbblfRbstfr wr =
           Rbstfr.drfbtfWritbblfRbstfr(rbstfr.gftSbmplfModfl(),
                         nfw Point(rbstfr.gftSbmplfModflTrbnslbtfX(),
                                   rbstfr.gftSbmplfModflTrbnslbtfY()));

        Objfdt tdbtb = null;

        for (int i = stbrtY; i < stbrtY+ifigit; i++)  {
            tdbtb = rbstfr.gftDbtbElfmfnts(stbrtX,i,widti,1,tdbtb);
            wr.sftDbtbElfmfnts(stbrtX,i,widti,1, tdbtb);
        }
        rfturn wr;
    }

    /**
     * Computfs bnd rfturns bn brbitrbry rfgion of tif
     * <dodf>BufffrfdImbgf</dodf>.  Tif <dodf>Rbstfr</dodf> rfturnfd is b
     * dopy of tif imbgf dbtb bnd is not updbtfd if tif imbgf is
     * dibngfd.
     * @pbrbm rfdt tif rfgion of tif <dodf>BufffrfdImbgf</dodf> to bf
     * rfturnfd.
     * @rfturn b <dodf>Rbstfr</dodf> tibt is b dopy of tif imbgf dbtb of
     *          tif spfdififd rfgion of tif <dodf>BufffrfdImbgf</dodf>
     * @sff #sftDbtb(Rbstfr)
     */
    publid Rbstfr gftDbtb(Rfdtbnglf rfdt) {
        SbmplfModfl sm = rbstfr.gftSbmplfModfl();
        SbmplfModfl nsm = sm.drfbtfCompbtiblfSbmplfModfl(rfdt.widti,
                                                         rfdt.ifigit);
        WritbblfRbstfr wr = Rbstfr.drfbtfWritbblfRbstfr(nsm,
                                                  rfdt.gftLodbtion());
        int widti = rfdt.widti;
        int ifigit = rfdt.ifigit;
        int stbrtX = rfdt.x;
        int stbrtY = rfdt.y;

        Objfdt tdbtb = null;

        for (int i = stbrtY; i < stbrtY+ifigit; i++)  {
            tdbtb = rbstfr.gftDbtbElfmfnts(stbrtX,i,widti,1,tdbtb);
            wr.sftDbtbElfmfnts(stbrtX,i,widti,1, tdbtb);
        }
        rfturn wr;
    }

    /**
     * Computfs bn brbitrbry rfdtbngulbr rfgion of tif
     * <dodf>BufffrfdImbgf</dodf> bnd dopifs it into b spfdififd
     * <dodf>WritbblfRbstfr</dodf>.  Tif rfgion to bf domputfd is
     * dftfrminfd from tif bounds of tif spfdififd
     * <dodf>WritbblfRbstfr</dodf>.  Tif spfdififd
     * <dodf>WritbblfRbstfr</dodf> must ibvf b
     * <dodf>SbmplfModfl</dodf> tibt is dompbtiblf witi tiis imbgf.  If
     * <dodf>outRbstfr</dodf> is <dodf>null</dodf>,
     * bn bppropribtf <dodf>WritbblfRbstfr</dodf> is drfbtfd.
     * @pbrbm outRbstfr b <dodf>WritbblfRbstfr</dodf> to iold tif rfturnfd
     *          pbrt of tif imbgf, or <dodf>null</dodf>
     * @rfturn b rfffrfndf to tif supplifd or drfbtfd
     *          <dodf>WritbblfRbstfr</dodf>.
     */
    publid WritbblfRbstfr dopyDbtb(WritbblfRbstfr outRbstfr) {
        if (outRbstfr == null) {
            rfturn (WritbblfRbstfr) gftDbtb();
        }
        int widti = outRbstfr.gftWidti();
        int ifigit = outRbstfr.gftHfigit();
        int stbrtX = outRbstfr.gftMinX();
        int stbrtY = outRbstfr.gftMinY();

        Objfdt tdbtb = null;

        for (int i = stbrtY; i < stbrtY+ifigit; i++)  {
            tdbtb = rbstfr.gftDbtbElfmfnts(stbrtX,i,widti,1,tdbtb);
            outRbstfr.sftDbtbElfmfnts(stbrtX,i,widti,1, tdbtb);
        }

        rfturn outRbstfr;
    }

  /**
     * Sfts b rfdtbngulbr rfgion of tif imbgf to tif dontfnts of tif
     * spfdififd <dodf>Rbstfr</dodf> <dodf>r</dodf>, wiidi is
     * bssumfd to bf in tif sbmf doordinbtf spbdf bs tif
     * <dodf>BufffrfdImbgf</dodf>. Tif opfrbtion is dlippfd to tif bounds
     * of tif <dodf>BufffrfdImbgf</dodf>.
     * @pbrbm r tif spfdififd <dodf>Rbstfr</dodf>
     * @sff #gftDbtb
     * @sff #gftDbtb(Rfdtbnglf)
    */
    publid void sftDbtb(Rbstfr r) {
        int widti = r.gftWidti();
        int ifigit = r.gftHfigit();
        int stbrtX = r.gftMinX();
        int stbrtY = r.gftMinY();

        int[] tdbtb = null;

        // Clip to tif durrfnt Rbstfr
        Rfdtbnglf rdlip = nfw Rfdtbnglf(stbrtX, stbrtY, widti, ifigit);
        Rfdtbnglf bdlip = nfw Rfdtbnglf(0, 0, rbstfr.widti, rbstfr.ifigit);
        Rfdtbnglf intfrsfdt = rdlip.intfrsfdtion(bdlip);
        if (intfrsfdt.isEmpty()) {
            rfturn;
        }
        widti = intfrsfdt.widti;
        ifigit = intfrsfdt.ifigit;
        stbrtX = intfrsfdt.x;
        stbrtY = intfrsfdt.y;

        // rfmind usf gft/sftDbtbElfmfnts for spffd if Rbstfrs brf
        // dompbtiblf
        for (int i = stbrtY; i < stbrtY+ifigit; i++)  {
            tdbtb = r.gftPixfls(stbrtX,i,widti,1,tdbtb);
            rbstfr.sftPixfls(stbrtX,i,widti,1, tdbtb);
        }
    }


  /**
   * Adds b tilf obsfrvfr.  If tif obsfrvfr is blrfbdy prfsfnt,
   * it rfdfivfs multiplf notifidbtions.
   * @pbrbm to tif spfdififd {@link TilfObsfrvfr}
   */
    publid void bddTilfObsfrvfr (TilfObsfrvfr to) {
    }

  /**
   * Rfmovfs b tilf obsfrvfr.  If tif obsfrvfr wbs not rfgistfrfd,
   * notiing ibppfns.  If tif obsfrvfr wbs rfgistfrfd for multiplf
   * notifidbtions, it is now rfgistfrfd for onf ffwfr notifidbtion.
   * @pbrbm to tif spfdififd <dodf>TilfObsfrvfr</dodf>.
   */
    publid void rfmovfTilfObsfrvfr (TilfObsfrvfr to) {
    }

    /**
     * Rfturns wiftifr or not b tilf is durrfntly difdkfd out for writing.
     * @pbrbm tilfX tif x indfx of tif tilf.
     * @pbrbm tilfY tif y indfx of tif tilf.
     * @rfturn <dodf>truf</dodf> if tif tilf spfdififd by tif spfdififd
     *          indidfs is difdkfd out for writing; <dodf>fblsf</dodf>
     *          otifrwisf.
     * @fxdfption ArrbyIndfxOutOfBoundsExdfption if boti
     *          <dodf>tilfX</dodf> bnd <dodf>tilfY</dodf> brf not fqubl
     *          to 0
     */
    publid boolfbn isTilfWritbblf (int tilfX, int tilfY) {
        if (tilfX == 0 && tilfY == 0) {
            rfturn truf;
        }
        tirow nfw IllfgblArgumfntExdfption("Only 1 tilf in imbgf");
    }

    /**
     * Rfturns bn brrby of {@link Point} objfdts indidbting wiidi tilfs
     * brf difdkfd out for writing.  Rfturns <dodf>null</dodf> if nonf brf
     * difdkfd out.
     * @rfturn b <dodf>Point</dodf> brrby tibt indidbtfs tif tilfs tibt
     *          brf difdkfd out for writing, or <dodf>null</dodf> if no
     *          tilfs brf difdkfd out for writing.
     */
    publid Point[] gftWritbblfTilfIndidfs() {
        Point[] p = nfw Point[1];
        p[0] = nfw Point(0, 0);

        rfturn p;
    }

    /**
     * Rfturns wiftifr or not bny tilf is difdkfd out for writing.
     * Sfmbntidblly fquivblfnt to
     * <prf>
     * (gftWritbblfTilfIndidfs() != null).
     * </prf>
     * @rfturn <dodf>truf</dodf> if bny tilf is difdkfd out for writing;
     *          <dodf>fblsf</dodf> otifrwisf.
     */
    publid boolfbn ibsTilfWritfrs () {
        rfturn truf;
    }

  /**
   * Cifdks out b tilf for writing.  All rfgistfrfd
   * <dodf>TilfObsfrvfrs</dodf> brf notififd wifn b tilf gofs from ibving
   * no writfrs to ibving onf writfr.
   * @pbrbm tilfX tif x indfx of tif tilf
   * @pbrbm tilfY tif y indfx of tif tilf
   * @rfturn b <dodf>WritbblfRbstfr</dodf> tibt is tif tilf, indidbtfd by
   *            tif spfdififd indidfs, to bf difdkfd out for writing.
   */
    publid WritbblfRbstfr gftWritbblfTilf (int tilfX, int tilfY) {
        rfturn rbstfr;
    }

  /**
   * Rflinquisifs pfrmission to writf to b tilf.  If tif dbllfr
   * dontinufs to writf to tif tilf, tif rfsults brf undffinfd.
   * Cblls to tiis mftiod siould only bppfbr in mbtdiing pbirs
   * witi dblls to {@link #gftWritbblfTilf(int, int) gftWritbblfTilf(int, int)}.  Any otifr lfbds
   * to undffinfd rfsults.  All rfgistfrfd <dodf>TilfObsfrvfrs</dodf>
   * brf notififd wifn b tilf gofs from ibving onf writfr to ibving no
   * writfrs.
   * @pbrbm tilfX tif x indfx of tif tilf
   * @pbrbm tilfY tif y indfx of tif tilf
   */
    publid void rflfbsfWritbblfTilf (int tilfX, int tilfY) {
    }

    /**
     * Rfturns tif trbnspbrfndy.  Rfturns fitifr OPAQUE, BITMASK,
     * or TRANSLUCENT.
     * @rfturn tif trbnspbrfndy of tiis <dodf>BufffrfdImbgf</dodf>.
     * @sff Trbnspbrfndy#OPAQUE
     * @sff Trbnspbrfndy#BITMASK
     * @sff Trbnspbrfndy#TRANSLUCENT
     * @sindf 1.5
     */
    publid int gftTrbnspbrfndy() {
        rfturn dolorModfl.gftTrbnspbrfndy();
    }
}
