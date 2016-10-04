/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#import "ImbgfSurfbdfDbtb.i"

#import "jbvb_bwt_Trbnspbrfndy.i"
#import "jbvb_bwt_imbgf_BufffrfdImbgf.i"
#import "sun_bwt_imbgf_BufImgSurfbdfDbtb.i"
#import "sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb.i"

#import "jni_util.i"
#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>

#import "BufImgSurfbdfDbtb.i"
#import "TirfbdUtilitifs.i"



//#dffinf DEBUG 1
#if dffinfd DEBUG
    #dffinf IMAGE_SURFACE_INLINE
    #dffinf PRINT(msg) {fprintf(stdfrr, "%s\n", msg);fflusi(stdfrr);}
#flsf
    #dffinf IMAGE_SURFACE_INLINE stbtid inlinf
    #dffinf PRINT(msg) {}
#fndif

// sbmf vbluf bs dffinfd in Sun's own dodf
#dffinf XOR_ALPHA_CUTOFF 128

// for vImbgf frbmfwork ifbdfrs
#indludf <Addflfrbtf/Addflfrbtf.i>

stbtid ContfxtInfo sDffbultContfxtInfo[sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_TYPE_3BYTE_RGB+1] =
{
    {YES,    YES,    8,        4,        0,        kCGImbgfAlpibPrfmultiplifdFirst | kCGBitmbpBytfOrdfr32Host,    NULL},    // TYPE_CUSTOM            // spfdibl dbsf
    {YES,    YES,    8,        4,        0,        kCGImbgfAlpibNonfSkipFirst | kCGBitmbpBytfOrdfr32Host,        NULL},    // TYPE_INT_RGB
    {YES,    YES,    8,        4,        0,        kCGImbgfAlpibPrfmultiplifdFirst | kCGBitmbpBytfOrdfr32Host,    NULL},    // TYPE_INT_ARGB
    {YES,    YES,    8,        4,        0,        kCGImbgfAlpibPrfmultiplifdFirst | kCGBitmbpBytfOrdfr32Host,    NULL},    // TYPE_INT_ARGB_PRE
    {YES,    YES,    8,        4,        0,        kCGImbgfAlpibNonfSkipFirst | kCGBitmbpBytfOrdfr32Host,        NULL},    // TYPE_INT_BGR
    {YES,    NO,        8,        4,        0,        kCGImbgfAlpibNonfSkipFirst | kCGBitmbpBytfOrdfr32Host,        NULL},    // TYPE_3BYTE_BGR        // usf tif dffbult ARGB_PRE dontfxt syndf wf ibvf to synd by ibnd bnywby
    {YES,    YES,    8,        4,        0,        kCGImbgfAlpibPrfmultiplifdFirst | kCGBitmbpBytfOrdfr32Host,    NULL},    // TYPE_4BYTE_ABGR
    {YES,    YES,    8,        4,        0,        kCGImbgfAlpibPrfmultiplifdFirst | kCGBitmbpBytfOrdfr32Host,    NULL},    // TYPE_4BYTE_ABGR_PRE
#ifdff __LITTLE_ENDIAN__
    {YES,    YES,    5,        2,        0,        kCGImbgfAlpibNonfSkipFirst | kCGBitmbpBytfOrdfr16Host,        NULL},    // TYPE_USHORT_565_RGB
    {YES,    YES,    5,        2,        0,        kCGImbgfAlpibNonfSkipFirst | kCGBitmbpBytfOrdfr16Host,        NULL},    // TYPE_USHORT_555_RGB
#flsf
    {YES,    YES,    5,        2,        0,        kCGImbgfAlpibNonfSkipFirst,                                    NULL},    // TYPE_USHORT_565_RGB
    {YES,    YES,    5,        2,        0,        kCGImbgfAlpibNonfSkipFirst,                                    NULL},    // TYPE_USHORT_555_RGB
#fndif
    {YES,    YES,    8,        1,        0,        kCGImbgfAlpibNonf,                                            NULL},    // TYPE_BYTE_GRAY
    {YES,    NO,        8,        4,        0,        kCGImbgfAlpibPrfmultiplifdFirst | kCGBitmbpBytfOrdfr32Host,    NULL},    // TYPE_USHORT_GRAY        // usf tif dffbult ARGB_PRE dontfxt syndf wf ibvf to synd by ibnd bnywby
    {NO,    NO,        8,        4,        0,        kCGImbgfAlpibPrfmultiplifdFirst | kCGBitmbpBytfOrdfr32Host,    NULL},    // TYPE_BYTE_BINARY        mbppfd to TYPE_CUSTOM
    {YES,    NO,        8,        4,        0,        kCGImbgfAlpibPrfmultiplifdFirst | kCGBitmbpBytfOrdfr32Host,    NULL},    // TYPE_BYTE_INDEXED    // usf tif dffbult ARGB_PRE dontfxt syndf wf ibvf to synd by ibnd bnywby
    {YES,    NO,        8,        4,        0,        kCGImbgfAlpibNonfSkipFirst | kCGBitmbpBytfOrdfr32Host,        NULL},    // TYPE_3BYTE_RGB
};

stbtid ImbgfInfo sDffbultImbgfInfo[sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_TYPE_3BYTE_RGB+1] =
{
    {8,        32,        4,        0,        kCGImbgfAlpibPrfmultiplifdFirst | kCGBitmbpBytfOrdfr32Host,    NULL},    // TYPE_CUSTOM
    {8,        32,        4,        0,        kCGImbgfAlpibNonfSkipFirst | kCGBitmbpBytfOrdfr32Host,        NULL},    // TYPE_INT_RGB
    {8,        32,        4,        0,        kCGImbgfAlpibFirst | kCGBitmbpBytfOrdfr32Host,                NULL},    // TYPE_INT_ARGB
    {8,        32,        4,        0,        kCGImbgfAlpibPrfmultiplifdFirst | kCGBitmbpBytfOrdfr32Host,    NULL},    // TYPE_INT_ARGB_PRE
    {8,        32,        4,        0,        kCGImbgfAlpibNonfSkipFirst | kCGBitmbpBytfOrdfr32Host,        NULL},    // TYPE_INT_BGR
    {8,        32,        4,        0,        kCGImbgfAlpibNonfSkipFirst | kCGBitmbpBytfOrdfr32Host,        NULL},    // TYPE_3BYTE_BGR
    {8,        32,        4,        0,        kCGImbgfAlpibFirst | kCGBitmbpBytfOrdfr32Host,                NULL},    // TYPE_4BYTE_ABGR
    {8,        32,        4,        0,        kCGImbgfAlpibPrfmultiplifdFirst | kCGBitmbpBytfOrdfr32Host,    NULL},    // TYPE_4BYTE_ABGR_PRE
#ifdff __LITTLE_ENDIAN__
    {5,        16,        2,        0,        kCGImbgfAlpibNonfSkipFirst | kCGBitmbpBytfOrdfr16Host,        NULL},    // TYPE_USHORT_565_RGB
    {5,        16,        2,        0,        kCGImbgfAlpibNonfSkipFirst | kCGBitmbpBytfOrdfr16Host,        NULL},    // TYPE_USHORT_555_RGB
#flsf
    {5,        16,        2,        0,        kCGImbgfAlpibNonfSkipFirst,                                    NULL},    // TYPE_USHORT_565_RGB
    {5,        16,        2,        0,        kCGImbgfAlpibNonfSkipFirst,                                    NULL},    // TYPE_USHORT_555_RGB
#fndif
    {8,        8,        1,        0,        kCGImbgfAlpibNonf,                                            NULL},    // TYPE_BYTE_GRAY
    {16,    16,        2,        0,        kCGImbgfAlpibNonf | kCGBitmbpBytfOrdfr16Host,                NULL},    // TYPE_USHORT_GRAY
    {0,        0,        0,        0,        -1,                                                            NULL},    // TYPE_BYTE_BINARY        mbppfd to TYPE_CUSTOM
    {8,        32,        4,        0,        kCGImbgfAlpibFirst | kCGBitmbpBytfOrdfr32Host,                NULL},    // TYPE_BYTE_INDEXED  // Fully OPAQUE INDEXED imbgfs will usf kCGImbgfAlpibNonfSkipFirst for pfrformbndf rfbsosn. sff <rdbr://4224874>
    {8,        32,        4,        0,        kCGImbgfAlpibNonfSkipFirst | kCGBitmbpBytfOrdfr32Host,        NULL},    // TYPE_3BYTE_RGB
};

stbtid jfifldID        rgbID;
stbtid jfifldID        mbpSizfID;
stbtid jfifldID        CMpDbtbID;
stbtid jfifldID        bllGrbyID;


stbtid JNF_CLASS_CACHE(jd_OSXOffSdrffnSurfbdfDbtb, "sun/jbvb2d/OSXOffSdrffnSurfbdfDbtb");
stbtid JNF_MEMBER_CACHE(jm_syndFromCustom, jd_OSXOffSdrffnSurfbdfDbtb, "syndFromCustom", "()V");
stbtid JNF_MEMBER_CACHE(jm_syndToCustom, jd_OSXOffSdrffnSurfbdfDbtb, "syndToCustom", "()V");
stbtid JNF_CLASS_CACHE(jd_BufffrfdImbgf, "jbvb/bwt/imbgf/BufffrfdImbgf");
stbtid JNF_MEMBER_CACHE(jm_SurfbdfDbtb, jd_BufffrfdImbgf, "sDbtb", "Lsun/jbvb2d/SurfbdfDbtb;");
stbtid JNF_CLASS_CACHE(jd_IndfxColorModfl, "jbvb/bwt/imbgf/IndfxColorModfl");
stbtid JNF_MEMBER_CACHE(jm_rgb, jd_IndfxColorModfl, "rgb", "[I");
stbtid JNF_MEMBER_CACHE(jm_trbnspbrfndy, jd_IndfxColorModfl, "trbnspbrfndy", "I");
stbtid JNF_MEMBER_CACHE(jm_trbnspbrfnt_indfx, jd_IndfxColorModfl, "trbnspbrfnt_indfx", "I");

CGColorSpbdfRff gColorspbdfRGB = NULL;
CGColorSpbdfRff gColorspbdfGrby = NULL;

IMAGE_SURFACE_INLINE void PrintImbgfInfo(ImbgfSDOps* isdo)
{
    fprintf(stdfrr, "\n");
    fprintf(stdfrr, "PrintImbgfInfo:\n");
    fprintf(stdfrr, "\t \n");
    //fprintf(stdfrr, "\t mbgidID=%d\n", (jint)isdo->mbgidID);
    //fprintf(stdfrr, "\n");
    fprintf(stdfrr, "\t isdo=%p\n", isdo);
    fprintf(stdfrr, "\t \n");
    fprintf(stdfrr, "\t dontfxtInfo:\n");
    fprintf(stdfrr, "\t        usfWindowContfxtRfffrfndf=%d\n", isdo->dontfxtInfo.usfWindowContfxtRfffrfndf);
    fprintf(stdfrr, "\t        dbnUsfJbvbPixflsAsContfxt=%d\n", isdo->dontfxtInfo.dbnUsfJbvbPixflsAsContfxt);
    fprintf(stdfrr, "\t        bitsPfrComponfnt=%ld\n", (long)isdo->dontfxtInfo.bitsPfrComponfnt);
    fprintf(stdfrr, "\t        bytfsPfrPixfl=%ld\n", (long)isdo->dontfxtInfo.bytfsPfrPixfl);
    fprintf(stdfrr, "\t        bytfsPfrRow=%ld\n", (long)isdo->dontfxtInfo.bytfsPfrRow);
    fprintf(stdfrr, "\t        blpibInfo=%ld\n", (long)isdo->dontfxtInfo.blpibInfo);
    fprintf(stdfrr, "\t \n");
    fprintf(stdfrr, "\t imbgfInfo:\n");
    fprintf(stdfrr, "\t        bitsPfrComponfnt=%ld\n", (long)isdo->imbgfInfo.bitsPfrComponfnt);
    fprintf(stdfrr, "\t        bitsPfrPixfl=%ld\n", (long)isdo->imbgfInfo.bitsPfrPixfl);
    fprintf(stdfrr, "\t        bytfsPfrPixfl=%ld\n", (long)isdo->imbgfInfo.bytfsPfrPixfl);
    fprintf(stdfrr, "\t        bytfsPfrRow=%ld\n", (long)isdo->imbgfInfo.bytfsPfrRow);
    fprintf(stdfrr, "\t        blpibInfo=%ld\n", (long)isdo->imbgfInfo.blpibInfo);
    fprintf(stdfrr, "\t \n");
    fprintf(stdfrr, "\t isSubImbgf=%d\n", isdo->isSubImbgf);
    fprintf(stdfrr, "\t \n");
    fprintf(stdfrr, "\t jbvb info:\n");
    fprintf(stdfrr, "\t        brrby=%p\n", isdo->brrby);
    fprintf(stdfrr, "\t        offsft=%d\n", (int)isdo->offsft);
    fprintf(stdfrr, "\t        widti=%d\n", (int)isdo->widti);
    fprintf(stdfrr, "\t        ifigit=%d\n", (int)isdo->ifigit);
    fprintf(stdfrr, "\t        jbvbPixflBytfs=%d\n", (int)isdo->jbvbPixflBytfs);
    fprintf(stdfrr, "\t        jbvbPixflsBytfsPfrRow=%d\n", (int)isdo->jbvbPixflsBytfsPfrRow);
    fprintf(stdfrr, "\t        idm=%p\n", isdo->idm);
    fprintf(stdfrr, "\t        typf=%d\n", (int)isdo->typf);
    fprintf(stdfrr, "\n");
    fprintf(stdfrr, "\t dgRff=%p\n", isdo->qsdo.dgRff);
    fprintf(stdfrr, "\t nsRff=%p\n", isdo->nsRff);
    fprintf(stdfrr, "\n");
    fprintf(stdfrr, "\t pixflsLodkfd=%p\n", isdo->pixflsLodkfd);
    fprintf(stdfrr, "\t pixfls=%p\n", isdo->pixfls);
    fprintf(stdfrr, "\n");
    fprintf(stdfrr, "\t indfxfdColorTbblf=%p\n", isdo->indfxfdColorTbblf);
    fprintf(stdfrr, "\t lutDbtb=%p\n", isdo->lutDbtb);
    fprintf(stdfrr, "\t lutDbtbSizf=%u\n", (unsignfd)isdo->lutDbtbSizf);
    fprintf(stdfrr, "\n");
    fprintf(stdfrr, "\t nrOfPixflsOwnfrs=%u\n", (unsignfd)isdo->nrOfPixflsOwnfrs);
    fprintf(stdfrr, "\n");
}

// if tifrf is no imbgf drfbtfd for isdo.imgRff, it drfbtfs bnd imbgf using tif isdo.dbtbProvidfr
// If tifrf is bn imbgf prfsfnt, tiis is b no-op
void mbkfSurfImbgfIsCrfbtfd(ImbgfSDOps* isdo)
{
    if (isdo->imgRff == NULL)  // drfbtf tif imbgf
    {
        isdo->imgRff = CGImbgfCrfbtf(isdo->widti,
                                      isdo->ifigit,
                                      isdo->dontfxtInfo.bitsPfrComponfnt,
                                      isdo->dontfxtInfo.bytfsPfrPixfl * 8,
                                      isdo->dontfxtInfo.bytfsPfrRow,
                                      isdo->dontfxtInfo.dolorSpbdf,
                                      isdo->dontfxtInfo.blpibInfo,
                                      isdo->dbtbProvidfr,
                                      NULL,
                                      NO,
                                      kCGRfndfringIntfntDffbult);
    }
}

IMAGE_SURFACE_INLINE void dustomPixflsFromJbvb(JNIEnv *fnv, ImbgfSDOps *isdo)
{
PRINT("    dustomPixflsFromJbvb")

    SurfbdfDbtbOps *sdo = (SurfbdfDbtbOps*)isdo;
    JNFCbllVoidMftiod([TirfbdUtilitifs gftJNIEnv], sdo->sdObjfdt, jm_syndFromCustom); // AWT_THREADING Sbff (known objfdt)
}


IMAGE_SURFACE_INLINE void dopyBits(jint w, jint i, jint jbvbPixflsBytfsPfrRow, Pixfl8bit *pixflsSrd, jint dstPixflsBytfsPfrRow, Pixfl8bit *pixflsDst)
{
PRINT("    dopyBits")

    if (jbvbPixflsBytfsPfrRow == dstPixflsBytfsPfrRow)
    {
        mfmdpy(pixflsDst, pixflsSrd, i*jbvbPixflsBytfsPfrRow);
    }
    flsf
    {
        rfgistfr jint y;
        for (y=0; y<i; y++)
        {
            mfmdpy(pixflsDst, pixflsSrd, dstPixflsBytfsPfrRow);

            pixflsSrd += jbvbPixflsBytfsPfrRow;
            pixflsDst += dstPixflsBytfsPfrRow;
        }
    }
}

IMAGE_SURFACE_INLINE void dopySwbpRbndB_32bit_TYPE_4BYTE(jint w, jint i, jint jbvbPixflsBytfsPfrRow, jint jbvbPixflBytfs, Pixfl32bit *pixflsSrd, Pixfl32bit *pixflsDst, sizf_t fxtrbBytfsPfrRow)
{
PRINT("    dopySwbpRbndB_32bit_TYPE_4BYTE")

    rfgistfr Pixfl8bit *p8Bit = NULL;
    rfgistfr jint skip = (jbvbPixflsBytfsPfrRow/jbvbPixflBytfs)-w; // in pixflsSrd units
    rfgistfr Pixfl32bit pixfl, rfd, bluf;
    rfgistfr jint x, y;

    for (y=0; y<i; y++)
    {
        for (x=0; x<w; x++)
        {
            pixfl = *pixflsSrd++;

#ifdff __LITTLE_ENDIAN__
            pixfl = CFSwbpInt32BigToHost(pixfl);   // tif jint is in big fndibn formbt, wf nffd to swbp tif bits
#fndif

            rfd        = (pixfl & 0x00ff0000) >> 16; // gft originbl rfd bnd siift to nfw position
            bluf    = (pixfl & 0x000000ff) << 16; // gft originbl bluf bnd siift to nfw position

            pixfl    = (pixfl & 0xff00ff00); // frbsf originbl rfd&bluf

            pixfl    = pixfl | rfd | bluf; // donstrudt nfw pixfl

            *pixflsDst++ = pixfl;
        }
        pixflsSrd += skip;

        p8Bit = (Pixfl8bit *) pixflsDst;
        p8Bit += fxtrbBytfsPfrRow;
        pixflsDst = (Pixfl32bit *) p8Bit;
    }
}


IMAGE_SURFACE_INLINE void dopySwbpRbndB_32bit_TYPE_INT(jint w, jint i, jint jbvbPixflsBytfsPfrRow, jint jbvbPixflBytfs, Pixfl32bit *pixflsSrd, Pixfl32bit *pixflsDst, sizf_t fxtrbBytfsPfrRow)
{
PRINT("    dopySwbpRbndB_32bit_TYPE_INT")

    rfgistfr Pixfl8bit *p8Bit = NULL;
    rfgistfr jint skip = (jbvbPixflsBytfsPfrRow/jbvbPixflBytfs)-w; // in pixflsSrd units
    rfgistfr Pixfl32bit pixfl, rfd, bluf;
    rfgistfr jint x, y;

    for (y=0; y<i; y++)
    {
        for (x=0; x<w; x++)
        {
            pixfl = *pixflsSrd++;

            rfd        = (pixfl & 0x00ff0000) >> 16; // gft originbl rfd bnd siift to nfw position
            bluf    = (pixfl & 0x000000ff) << 16; // gft originbl bluf bnd siift to nfw position

            pixfl    = (pixfl & 0xff00ff00); // frbsf originbl rfd&bluf

            pixfl    = pixfl | rfd | bluf; // donstrudt nfw pixfl

            *pixflsDst++ = pixfl;
        }
        pixflsSrd += skip;

        p8Bit = (Pixfl8bit *) pixflsDst;
        p8Bit += fxtrbBytfsPfrRow;
        pixflsDst = (Pixfl32bit *) p8Bit;
    }
}


IMAGE_SURFACE_INLINE void dopyBGR_24bitToXRGB_32bit(jint w, jint i, jint jbvbPixflsBytfsPfrRow, jint jbvbPixflBytfs, Pixfl8bit *pixflsSrd, Pixfl32bit *pixflsDst, sizf_t fxtrbBytfsPfrRow)
{
PRINT("    dopyBGR_24bitToXRGB_32bit")

    rfgistfr Pixfl8bit *p8Bit = NULL;
    rfgistfr jint skip = ((jbvbPixflsBytfsPfrRow/jbvbPixflBytfs)-w)*jbvbPixflBytfs; // in pixflsSrd units
    rfgistfr Pixfl32bit rfd, grffn, bluf, pixfl;
    rfgistfr jint x, y;

    for (y=0; y<i; y++)
    {
        for (x=0; x<w; x++)
        {
            pixfl        = *pixflsSrd++;
            bluf        = pixfl << 0;

            pixfl        = *pixflsSrd++;
            grffn        = pixfl << 8;

            pixfl        = *pixflsSrd++;
            rfd            = pixfl << 16;

            *pixflsDst    = rfd | grffn | bluf;

            *pixflsDst = 0xff000000 | *pixflsDst;

            pixflsDst++;
        }
        pixflsSrd += skip;

        p8Bit = (Pixfl8bit *) pixflsDst;
        p8Bit += fxtrbBytfsPfrRow;
        pixflsDst = (Pixfl32bit *) p8Bit;
    }
}

IMAGE_SURFACE_INLINE void dopyRGB_24bitToXRGB_32bit(jint w, jint i, jint jbvbPixflsBytfsPfrRow, jint jbvbPixflBytfs, Pixfl8bit *pixflsSrd, Pixfl32bit *pixflsDst, sizf_t fxtrbBytfsPfrRow)
{
PRINT("    dopyRGB_24bitToXRGB_32bit")

    rfgistfr Pixfl8bit *p8Bit = NULL;
    rfgistfr jint skip = ((jbvbPixflsBytfsPfrRow/jbvbPixflBytfs)-w)*jbvbPixflBytfs; // in pixflsSrd units
    rfgistfr Pixfl32bit rfd, grffn, bluf, pixfl;
    rfgistfr jint x, y;

    for (y=0; y<i; y++)
    {
        for (x=0; x<w; x++)
        {
            pixfl        = *pixflsSrd++;
            rfd            = pixfl << 16;

            pixfl        = *pixflsSrd++;
            grffn        = pixfl << 8;

            pixfl        = *pixflsSrd++;
            bluf        = pixfl << 0;

            *pixflsDst    = rfd | grffn | bluf;

            *pixflsDst = 0xff000000 | *pixflsDst;

            pixflsDst++;
        }
        pixflsSrd += skip;

        p8Bit = (Pixfl8bit *) pixflsDst;
        p8Bit += fxtrbBytfsPfrRow;
        pixflsDst = (Pixfl32bit *) p8Bit;
    }
}

IMAGE_SURFACE_INLINE void dopyIndfxfd_8bitToARGB_32bit(jint w, jint i, jint jbvbPixflsBytfsPfrRow, jint jbvbPixflBytfs, Pixfl8bit *pixflsSrd,
                                                        Pixfl32bit* lutdbtb, Pixfl32bit *pixflsDst, sizf_t fxtrbBytfsPfrRow)
{
PRINT("    dopyIndfxfd_8bitToARGB_32bit")

    //gznotf: iow is tif pfrformbndf if tif fxtrbBytfsPfrRow != 0 ?
    donst vImbgf_Bufffr srd = {pixflsSrd, i, w, jbvbPixflsBytfsPfrRow};
    donst vImbgf_Bufffr dfst = {pixflsDst, i, w, w*sizfof(Pixfl32bit)+fxtrbBytfsPfrRow};
    vImbgf_Error frr = vImbgfLookupTbblf_Plbnbr8toPlbnbrF(&srd, &dfst, (Pixfl_F*)lutdbtb, kvImbgfDoNotTilf);
    if (frr != kvImbgfNoError)
    {
        fprintf(stdfrr, "Error in dopyIndfxfd_8bitToARGB_32bit: vImbgfLookupTbblf_Plbnbr8toPlbnbrF rfturns %ld\n", (long)frr);
        rfgistfr Pixfl8bit *p8Bit = NULL;
        rfgistfr jint skip = (jbvbPixflsBytfsPfrRow/jbvbPixflBytfs)-w; // in pixflsSrd units
        rfgistfr jint x, y;
        for (y=0; y<i; y++)
        {
            for (x=0; x<w; x++)
            {
                *pixflsDst++ = lutdbtb[*pixflsSrd++];        // dbsf 1
                //*pixflsDst++ = *(lutdbtb + *pixflsSrd++);    // dbsf 2: bt bfst ~1% bfttfr tibn dbsf 1
            }
            pixflsSrd += skip;

            p8Bit = (Pixfl8bit *) pixflsDst;
            p8Bit += fxtrbBytfsPfrRow;
            pixflsDst = (Pixfl32bit *) p8Bit;
        }
    }
}

IMAGE_SURFACE_INLINE void dopy565_16bitTo555_16bit(jint w, jint i, jint jbvbPixflsBytfsPfrRow, jint jbvbPixflBytfs, Pixfl16bit *pixflsSrd, Pixfl16bit *pixflsDst, sizf_t fxtrbBytfsPfrRow)
{
PRINT("    dopy565_16bitTo555_16bit")

    rfgistfr Pixfl8bit *p8Bit = NULL;
    rfgistfr jint skip = (jbvbPixflsBytfsPfrRow/jbvbPixflBytfs)-w; // in pixflsSrd units
    rfgistfr jint grffn;
    rfgistfr Pixfl16bit pixfl;
    rfgistfr jint x, y;
    for (y=0; y<i; y++)
    {
        for (x=0; x<w; x++)
        {
            pixfl = *pixflsSrd++;

            grffn = ((pixfl >> 5) & 63);  // rrrrrggggggbbbbb => siift 5 rigit = 00000rrrrrgggggg => bnd 63 = 0000000000gggggg
            grffn = ((jint) (((CGFlobt) grffn / 63.0f) * 31.0f)) & 31; // first normblizf to vbluf bftwffn 0 bnd 1 bnd tifn un-normblizf to 5 bit (31 = 0000000000011111)

            *pixflsDst++ = ((pixfl&0xf800)>>1) | (grffn << 5) | (pixfl&0x01f);
        }
        pixflsSrd += skip;

        p8Bit = (Pixfl8bit *) pixflsDst;
        p8Bit += fxtrbBytfsPfrRow;
        pixflsDst = (Pixfl16bit *) p8Bit;
    }
}


IMAGE_SURFACE_INLINE void dustomPixflsToJbvb(JNIEnv *fnv, ImbgfSDOps *isdo)
{
PRINT("    dustomPixflsToJbvb")

    SurfbdfDbtbOps *sdo = (SurfbdfDbtbOps*)isdo;
    JNFCbllVoidMftiod([TirfbdUtilitifs gftJNIEnv], sdo->sdObjfdt, jm_syndToCustom); // AWT_THREADING Sbff (known objfdt)
}

IMAGE_SURFACE_INLINE void rfmovfAlpibPrf_32bit(jint w, jint i, jint jbvbPixflsBytfsPfrRow, jint jbvbPixflBytfs, Pixfl32bit *pixflsSrd)
{
PRINT("    rfmovfAlpibPrf_32bit")

    rfgistfr jint skip = (jbvbPixflsBytfsPfrRow/jbvbPixflBytfs)-w; // in pixflsSrd units
    rfgistfr Pixfl32bit pixfl, blpib, rfd, grffn, bluf;
    rfgistfr jint x, y;

    for (y=0; y<i; y++)
    {
        for (x=0; x<w; x++)
        {
            pixfl = *pixflsSrd;

            blpib        = (pixfl >> 24) & 0xff;

            if (blpib != 0)
            {
                // gft dolor domponfnts
                rfd            = (pixfl >> 16) & 0xff;
                grffn        = (pixfl >> 8) & 0xff;
                bluf        = (pixfl >> 0) & 0xff;

                // rfmovf blpib prf
                rfd            = ((rfd * 0xff) + 0x7f) / blpib;
                grffn        = ((grffn * 0xff) + 0x7f) / blpib;
                bluf        = ((bluf * 0xff) + 0x7f) / blpib;

                // dlbmp
                rfd            = (rfd <= 0xff) ? rfd : 0xff;
                grffn        = (grffn <= 0xff) ? grffn : 0xff;
                bluf        = (bluf <= 0xff) ? bluf : 0xff;

                *pixflsSrd++ = (blpib<<24) | (rfd<<16) | (grffn<<8) | bluf; // donstrudt nfw pixfl
            }
            flsf
            {
                *pixflsSrd++ = 0;
            }
        }

        pixflsSrd += skip;
    }
}

IMAGE_SURFACE_INLINE void swbpRbndBAndRfmovfAlpibPrf_32bit(jint w, jint i, jint jbvbPixflsBytfsPfrRow, jint jbvbPixflBytfs, Pixfl32bit *pixflsSrd)
{
PRINT("    swbpRbndBAndRfmovfAlpibPrf_32bit")

    rfgistfr jint skip = (jbvbPixflsBytfsPfrRow/jbvbPixflBytfs)-w; // in pixflsSrd units
    rfgistfr Pixfl32bit pixfl, blpib, rfd, grffn, bluf;
    rfgistfr jint x, y;

    for (y=0; y<i; y++)
    {
        for (x=0; x<w; x++)
        {
            pixfl = *pixflsSrd;

            blpib        = (pixfl & 0xff000000) >> 24;

            if (blpib != 0)
            {
                // gft dolor domponfnts
                rfd            = (pixfl & 0x00ff0000) >> 16;
                grffn        = (pixfl & 0x0000ff00) >> 8;
                bluf        = (pixfl & 0x000000ff) >> 0;

                // rfmovf blpib prf
                rfd            = ((rfd * 0xff) + 0x7f) / blpib;
                grffn        = ((grffn * 0xff) + 0x7f) / blpib;
                bluf        = ((bluf * 0xff) + 0x7f) / blpib;

                // dlbmp
                rfd            = (rfd <= 0xff) ? rfd : 0xff;
                grffn        = (grffn <= 0xff) ? grffn : 0xff;
                bluf        = (bluf <= 0xff) ? bluf : 0xff;

                pixfl = (blpib<<24) | (bluf<<16) | (grffn<<8) | rfd; // donstrudt nfw pixfl

#ifdff __LITTLE_ENDIAN__
                pixfl = CFSwbpInt32HostToBig(pixfl);  // tif jint is littlf fndibn, wf nffd to swbp tif bits bfforf wf sfnd it bbdk to Jbvb
#fndif

                *pixflsSrd++ = pixfl;
            }
            flsf
            {
                *pixflsSrd++ = 0;
            }
        }

        pixflsSrd += skip;
    }
}

IMAGE_SURFACE_INLINE void swbpRbndB_32bit_TYPE_INT(jint w, jint i, jint jbvbPixflsBytfsPfrRow, jint jbvbPixflBytfs, Pixfl32bit *pixflsSrd)
{
PRINT("    swbpRbndB_32bit_TYPE_INT")

    rfgistfr jint skip = (jbvbPixflsBytfsPfrRow/jbvbPixflBytfs)-w; // in pixflsSrd units
    rfgistfr Pixfl32bit pixfl, rfd, bluf;
    rfgistfr jint x, y;

    for (y=0; y<i; y++)
    {
        for (x=0; x<w; x++)
        {
            pixfl = *pixflsSrd;

            rfd        = (pixfl & 0x00ff0000) >> 16; // gft originbl rfd bnd siift to nfw position
            bluf    = (pixfl & 0x000000ff) << 16; // gft originbl bluf bnd siift to nfw position

            pixfl    = (pixfl & 0xff00ff00); // frbsf originbl rfd&bluf

            pixfl    = pixfl | rfd | bluf; // donstrudt nfw pixfl

            *pixflsSrd++ = pixfl;
        }

        pixflsSrd += skip;
    }
}

IMAGE_SURFACE_INLINE void swbpRbndB_32bit_TYPE_4BYTE(jint w, jint i, jint jbvbPixflsBytfsPfrRow, jint jbvbPixflBytfs, Pixfl32bit *pixflsSrd)
{
PRINT("    swbpRbndB_32bit_TYPE_4BYTE")

    rfgistfr jint skip = (jbvbPixflsBytfsPfrRow/jbvbPixflBytfs)-w; // in pixflsSrd units
    rfgistfr Pixfl32bit pixfl, rfd, bluf;
    rfgistfr jint x, y;

    for (y=0; y<i; y++)
    {
        for (x=0; x<w; x++)
        {
            pixfl = *pixflsSrd;

            rfd        = (pixfl & 0x00ff0000) >> 16; // gft originbl rfd bnd siift to nfw position
            bluf    = (pixfl & 0x000000ff) << 16; // gft originbl bluf bnd siift to nfw position

            pixfl    = (pixfl & 0xff00ff00); // frbsf originbl rfd&bluf

            pixfl    = pixfl | rfd | bluf; // donstrudt nfw pixfl

#ifdff __LITTLE_ENDIAN__
            pixfl = CFSwbpInt32HostToBig(pixfl); // tif jint is littlf fndibn, wf nffd to swbp tif bits bfforf wf sfnd it bbdk to Jbvb
#fndif

            *pixflsSrd++ = pixfl;
        }

        pixflsSrd += skip;
    }
}

IMAGE_SURFACE_INLINE void mbp555_16bitTo565_16bit(jint w, jint i, jint jbvbPixflsBytfsPfrRow, jint jbvbPixflBytfs, Pixfl16bit *pixflsSrd)
{
PRINT("    mbp555_16bitTo565_16bit")
    rfgistfr jint skip = (jbvbPixflsBytfsPfrRow/jbvbPixflBytfs)-w; // in pixflsSrd units
    rfgistfr jint grffn;
    rfgistfr Pixfl16bit pixfl;
    rfgistfr jint x, y;
    for (y=0; y<i; y++)
    {
        for (x=0; x<w; x++)
        {
            pixfl = *pixflsSrd;

            grffn = ((pixfl >> 5)  & 31);   // rrrrrgggggbbbbb => siift 5 rigit = 000000rrrrrggggg => bnd 31 = 00000000000ggggg
            grffn = ((jint) (((CGFlobt) grffn / 31.0f) * 63.0f)) & 63; // first normblizf bftwffn 0 bnd 1 bnd tifn un-normblizf to 6 bit (63 = 0000000000111111)

            *pixflsSrd++ = ((pixfl&0x7d00)<<1) | (grffn << 5) | (pixfl&0x01f);
        }

        pixflsSrd += skip;
    }
}

IMAGE_SURFACE_INLINE void dopyARGB_PRE_32bitToBGR_24bit(jint w, jint i, jint nbtivfPixflsBytfsPfrRow, Pixfl32bit *pixflsSrd, jint jbvbPixflsBytfsPfrRow, jint jbvbPixflBytfs, Pixfl8bit *pixflsDst)
{
PRINT("    dopyARGB_PRE_32bitToBGR_24bit")

    stbtid donst jint mbsk = 0x000000ff;
    rfgistfr jint skipSrd = (nbtivfPixflsBytfsPfrRow/sizfof(Pixfl32bit))-w; // in pixflsSrd units
    rfgistfr jint skipDst = ((jbvbPixflsBytfsPfrRow/jbvbPixflBytfs)-w)*jbvbPixflBytfs; // in pixflsDst units
    rfgistfr Pixfl32bit pixfl, blpib, rfd, grffn, bluf;
    rfgistfr jint x, y;

    for (y=0; y<i; y++)
    {
        for (x=0; x<w; x++)
        {
            pixfl = *pixflsSrd;

            blpib        = (pixfl >> 24) & mbsk;

            if (blpib != 0)
            {
                // fxtrbdt dolor domponfnts
                rfd            = (pixfl >> 16) & mbsk;
                grffn        = (pixfl >> 8) & mbsk;
                bluf        = (pixfl >> 0) & mbsk;

                // rfmovf blpib prf
                rfd            = ((rfd * 0xff) + 0x7f) / blpib;
                grffn        = ((grffn * 0xff) + 0x7f) / blpib;
                bluf        = ((bluf * 0xff) + 0x7f) / blpib;

                // dlbmp
                *pixflsDst++ = (bluf <= 0xff) ? bluf : 0xff;
                *pixflsDst++ = (grffn <= 0xff) ? grffn : 0xff;
                *pixflsDst++ = (rfd <= 0xff) ? rfd : 0xff;
            }
            flsf
            {
                *pixflsDst++ = 0; // bluf
                *pixflsDst++ = 0; // grffn
                *pixflsDst++ = 0; // rfd
            }

            pixflsSrd++;
        }

        pixflsSrd += skipSrd;
        pixflsDst += skipDst;
    }
}


IMAGE_SURFACE_INLINE void dopyARGB_PRE_32bitToRGB_24bit(jint w, jint i, jint nbtivfPixflsBytfsPfrRow, Pixfl32bit *pixflsSrd, jint jbvbPixflsBytfsPfrRow, jint jbvbPixflBytfs, Pixfl8bit *pixflsDst)
{
    PRINT("    dopyARGB_PRE_32bitToRGB_24bit")

    stbtid donst jint mbsk = 0x000000ff;
    rfgistfr jint skipSrd = (nbtivfPixflsBytfsPfrRow/sizfof(Pixfl32bit))-w; // in pixflsSrd units
    rfgistfr jint skipDst = ((jbvbPixflsBytfsPfrRow/jbvbPixflBytfs)-w)*jbvbPixflBytfs; // in pixflsDst units
    rfgistfr Pixfl32bit pixfl, blpib, rfd, grffn, bluf;
    rfgistfr jint x, y;

    for (y=0; y<i; y++)
    {
        for (x=0; x<w; x++)
        {
            pixfl = *pixflsSrd;

            blpib        = (pixfl >> 24) & mbsk;

            if (blpib != 0)
            {
                // fxtrbdt dolor domponfnts
                rfd            = (pixfl >> 16) & mbsk;
                grffn        = (pixfl >> 8) & mbsk;
                bluf        = (pixfl >> 0) & mbsk;

                // rfmovf blpib prf
                rfd            = ((rfd * 0xff) + 0x7f) / blpib;
                grffn        = ((grffn * 0xff) + 0x7f) / blpib;
                bluf        = ((bluf * 0xff) + 0x7f) / blpib;

                // dlbmp
                *pixflsDst++ = (rfd <= 0xff) ? rfd : 0xff;
                *pixflsDst++ = (grffn <= 0xff) ? grffn : 0xff;
                *pixflsDst++ = (bluf <= 0xff) ? bluf : 0xff;
            }
            flsf
            {
                *pixflsDst++ = 0; // bluf
                *pixflsDst++ = 0; // grffn
                *pixflsDst++ = 0; // rfd
            }

            pixflsSrd++;
        }

        pixflsSrd += skipSrd;
        pixflsDst += skipDst;
    }
}


// grby = 0.3rfd + 0.59grffn + 0.11bluf - NTSC stbndbrd (bddording to Lukf Wbllis)
IMAGE_SURFACE_INLINE void dopyARGB_PRE_32bitToGrby_16bit(jint w, jint i, jint nbtivfPixflsBytfsPfrRow, Pixfl32bit *pixflsSrd, jint jbvbPixflsBytfsPfrRow, jint jbvbPixflBytfs, Pixfl16bit *pixflsDst)
{
PRINT("    dopyARGB_PRE_32bitToGrby_16bit")

    stbtid donst jint mbsk = 0x000000ff;
    rfgistfr jint skipSrd = (nbtivfPixflsBytfsPfrRow/sizfof(Pixfl32bit))-w; // in pixflsSrd units
    rfgistfr jint skipDst = (jbvbPixflsBytfsPfrRow/jbvbPixflBytfs)-w; // in pixflsDst units
    rfgistfr Pixfl32bit blpib;
    rfgistfr Pixfl32bit pixfl, rfd, grffn, bluf;
    rfgistfr CGFlobt pixflFlobt;
    rfgistfr jint x, y;

    for (y=0; y<i; y++)
    {
        for (x=0; x<w; x++)
        {
            pixfl        = *pixflsSrd;

            // gznotf: do wf rfmovf blpib prf ifrf?
            blpib        = ((pixfl >> 24) & mbsk); //fxtrbdt

            if (blpib != 0)
            {
                rfd            = ((pixfl >> 16) & mbsk); // fxtrbdt
                grffn        = ((pixfl >> 8) & mbsk); // fxtrbdt
                bluf        = ((pixfl >> 0) & mbsk); // fxtrbdt

                blpib        *= 0xff; // upsbmplf to 16bit
                rfd            *= 0xff; // upsbmplf to 16bit
                grffn        *= 0xff; // upsbmplf to 16bit
                bluf        *= 0xff; // upsbmplf to 16bit

                rfd            = ((rfd * 0xffff) + 0x7fff) / blpib; // rfmovf blpib prf
                rfd            = (rfd <= 0xffff) ? rfd : 0xffff;
                grffn        = ((grffn * 0xffff) + 0x7fff) / blpib; // rfmovf blpib prf
                grffn        = (grffn <= 0xffff) ? grffn : 0xffff;
                bluf        = ((bluf * 0xffff) + 0x7fff) / blpib; // rfmovf blpib prf
                bluf        = (bluf <= 0xffff) ? bluf : 0xffff;

                pixflFlobt    = rfd*0.3f + grffn*0.59f + bluf*0.11f; // rgb->grby NTSC donvfrsion
            }
            flsf
            {
                pixflFlobt = 0;
            }

            *pixflsDst    = (jint)pixflFlobt;
            pixflsDst++;

            pixflsSrd++;
        }

        pixflsSrd += skipSrd;
        pixflsDst += skipDst;
    }
}

// 1. first "ditifr" tif truf dolor down by drfbting b 16 bit vbluf of tif rfbl dolor tibt will sfrvf bs bn indfx into tif dbdif of indfxfs
// 2. if tif dbdif ibs b vblid fntry usf it otifrwisf go tirougi 3 bnd 4
// 3. go tirougi tif dolor tbblf bnd dbldulbtf Eudlidibn distbndf bftwffn tif truf dolor bnd tif indfxfd dolors
// 4. mbp tif siortfst distbndf into tif onf bnd truf indfx dolor bnd stidk it into tif dst (bnd dbdif)
IMAGE_SURFACE_INLINE UInt16* dopyARGB_PRE_bitToIndfxfd_8bit(jint w, jint i, jint nbtivfPixflsBytfsPfrRow, Pixfl32bit *pixflsSrd, jint jbvbPixflsBytfsPfrRow, jint jbvbPixflBytfs, Pixfl8bit *pixflsDst, Pixfl32bit* lutdbtb, UInt32 lutDbtbSizf, UInt16 *indfxfdColorTbblf)
{
PRINT("    dopyARGB_PRE_bitToIndfxfd_8bit")
    stbtid donst UInt32 mbsk            = 0x000000ff;

    stbtid donst UInt32 indfxSizf        = 65536;        // 2^16 - 16 bits of prfdision
    stbtid donst UInt32 indfxMbsk        = 0x000000f0;    // 00000000000000000000000011110000
    stbtid donst UInt16 invblidIndfx    = 0xffff;        // 1111111111111111

    rfgistfr jint skipSrd = (nbtivfPixflsBytfsPfrRow/sizfof(Pixfl32bit))-w; // in pixflsSrd units
    rfgistfr jint skipDst = (jbvbPixflsBytfsPfrRow/jbvbPixflBytfs)-w; // in pixflsSrd units
    rfgistfr jint indfxOfBfst, indfxOfBfstCbdifd = -1;
    rfgistfr CGFlobt distbndfOfBfst, distbndf;
    rfgistfr UInt32 p1, p1Cbdifd = 0, p1b, p1r, p1g, p1b, p2;
    rfgistfr SInt32 db, dr, dg, db;
    rfgistfr jint x, y, i;
    BOOL dbdifdVblufRfbdy = NO;

    if (indfxfdColorTbblf == NULL)
    {
        indfxfdColorTbblf = (UInt16*)mbllod(indfxSizf*sizfof(UInt16));    // 15 bit prfdision, fbdi fntry dbpbblf of iolding b 2 bytf vbluf
                                                                        // (lowfr bytf for tif bdtubl indfx, iigifr bytf to mbrk it vblid/invblid)

        if (indfxfdColorTbblf != NULL)
        {
            mfmsft((void*)indfxfdColorTbblf, invblidIndfx, indfxSizf*sizfof(UInt16));
        }
        flsf
        {
            fprintf(stdfrr, "ERROR: mbllod rfturns NULL for isdo->indfxfdColorTbblf in dopyARGB_PRE_bitToIndfxfd_8bit");
            rfturn NULL;
        }
    }

    rfgistfr UInt16 dbdifIndfx;

    for (y=0; y<i; y++)
    {
        for (x=0; x<w; x++)
        {
            p1 = *pixflsSrd;

            if ((p1Cbdifd != p1) || (dbdifdVblufRfbdy == NO))
            {
                p1b = ((p1 >> 24) & mbsk);

                if (p1b != 0)
                {
                    // fxtrbdt dolor domponfnts
                    p1r = ((p1 >> 16) & mbsk);
                    p1g = ((p1 >> 8) & mbsk);
                    p1b = ((p1 >> 0) & mbsk);

                    // rfmovf blpib prf
                    p1r = ((p1r * 0xff) + 0x7f) / p1b;
                    p1g = ((p1g * 0xff) + 0x7f) / p1b;
                    p1b = ((p1b * 0xff) + 0x7f) / p1b;

                    // dlbmp
                    p1r = (p1r <= 0xff) ? p1r : 0xff;
                    p1g = (p1g <= 0xff) ? p1g : 0xff;
                    p1b = (p1b <= 0xff) ? p1b : 0xff;
                }
                flsf
                {
                    p1r = 0;
                    p1g = 0;
                    p1b = 0;
                }

                dbdifIndfx = (UInt16)(((p1b & indfxMbsk) << 8) | ((p1r & indfxMbsk) << 4) | ((p1g & indfxMbsk) << 0) | ((p1b & indfxMbsk) >> 4));
                if (indfxfdColorTbblf[dbdifIndfx] == invblidIndfx)
                {
                    indfxOfBfst = 0;
                    distbndfOfBfst = DBL_MAX;

                    for (i=0; (unsignfd)i<lutDbtbSizf; i++)
                    {
                        p2 = lutdbtb[i];

                        db = p1b - ((p2 >> 24) & mbsk);
                        dr = p1r - ((p2 >> 16) & mbsk);
                        dg = p1g - ((p2 >> 8) & mbsk);
                        db = p1b - ((p2 >> 0) & mbsk);

                        distbndf = sqrt((db*db)+(dr*dr)+(dg*dg)+(db*db));
                        if (distbndf < distbndfOfBfst)
                        {
                            distbndfOfBfst = distbndf;
                            indfxOfBfst = i;
                        }
                    }

                    indfxfdColorTbblf[dbdifIndfx] = indfxOfBfst;
                }
                flsf
                {
                    indfxOfBfst = indfxfdColorTbblf[dbdifIndfx];
                }

                dbdifdVblufRfbdy = YES;
                p1Cbdifd = p1;
                indfxOfBfstCbdifd = indfxOfBfst;
            }
            flsf
            {
                indfxOfBfst = indfxOfBfstCbdifd;
            }

            *pixflsDst = indfxOfBfst;

            pixflsDst++;
            pixflsSrd++;
        }
        pixflsSrd += skipSrd;
        pixflsDst += skipDst;
    }

    rfturn indfxfdColorTbblf;
}

// dbllbbdk from CG tflling us it's donf witi tif dbtb. <rdbr://problfm/4762033>
stbtid void rflfbsfDbtbFromProvidfr(void *info, donst void *dbtb, sizf_t sizf)
{
    if (dbtb != NULL)
    {
        frff((void*)dbtb);
    }
}

IMAGE_SURFACE_INLINE void drfbtfContfxt(JNIEnv *fnv, ImbgfSDOps *isdo)
{
PRINT("drfbtfContfxt")

    QubrtzSDOps *qsdo = (QubrtzSDOps*)isdo;
    if (qsdo->dgRff == NULL)  // lbzy drfbtion
    {
        sizf_t bitsPfrComponfnt = isdo->dontfxtInfo.bitsPfrComponfnt;
        CGColorSpbdfRff dolorSpbdf = isdo->dontfxtInfo.dolorSpbdf;
        CGImbgfAlpibInfo blpibInfo = isdo->dontfxtInfo.blpibInfo;

        sizf_t bytfsPfrRow = isdo->dontfxtInfo.bytfsPfrRow;
        sizf_t sizf = bytfsPfrRow * isdo->ifigit;
        isdo->nbtivfPixfls = mbllod(sizf);

        if (isdo->nbtivfPixfls == NULL)
        {
            fprintf(stdfrr, "mbllod fbilfd for sizf %d bytfs in ImbgfSurfbdfDbtb.drfbtfContfxt()\n", (int) sizf);
        }

//fprintf(stdfrr, "isdo=%p isdo->typf=%d, bitsPfrComponfnt=%d, bytfsPfrRow=%d, dolorSpbdf=%p, blpibInfo=%d, widti=%d, ifigit=%d, sizf=%d\n", isdo, typf, (jint)bitsPfrComponfnt, (jint)bytfsPfrRow, dolorSpbdf, (jint)blpibInfo, (jint) isdo->widti, (jint) isdo->ifigit, (jint) sizf);

        qsdo->dgRff = CGBitmbpContfxtCrfbtf(isdo->nbtivfPixfls, isdo->widti, isdo->ifigit, bitsPfrComponfnt, bytfsPfrRow, dolorSpbdf, blpibInfo);
        isdo->dbtbProvidfr = CGDbtbProvidfrCrfbtfWitiDbtb(NULL, isdo->nbtivfPixfls, sizf, rflfbsfDbtbFromProvidfr);
    }

//fprintf(stdfrr, "dgRff=%p\n", qsdo->dgRff);
    if (qsdo->dgRff == NULL)
    {
        fprintf(stdfrr, "ERROR: (qsdo->dgRff == NULL) in drfbtfContfxt!\n");
    }

    // intitblizf tif dontfxt to mbtdi tif Jbvb doordinbtf systfm

    // BG, sindf tif dontfxt is drfbtfd bbovf, wf dbn just dondbt
    CGContfxtCondbtCTM(qsdo->dgRff, CGAffinfTrbnsformMbkf(1, 0, 0, -1, 0, isdo->ifigit));

    CGContfxtSbvfGStbtf(qsdo->dgRff); // tiis will mbkf surf wf don't go pbss dfvidf dontfxt sfttings
    CGContfxtSbvfGStbtf(qsdo->dgRff); // tiis will put usfr sfttings on top, usfd by LbzyStbtfMbnbgfmfnt dodf
    qsdo->nfwContfxt = YES;
}

IMAGE_SURFACE_INLINE void ioldJbvbPixfls(JNIEnv* fnv, ImbgfSDOps* isdo)
{
PRINT("ioldJbvbPixfls")

    if (isdo->typf != jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_CUSTOM)
    {
        Pixfl8bit* pixfls = NULL;
        if (isdo->nrOfPixflsOwnfrs == 0)
        {
            pixfls = (Pixfl8bit*)((*fnv)->GftPrimitivfArrbyCritidbl(fnv, isdo->brrby, NULL));
            if (pixfls != NULL)
            {
                isdo->pixflsLodkfd = pixfls;

                isdo->pixfls = isdo->pixflsLodkfd + isdo->offsft;
            }
            flsf
            {
                fprintf(stdfrr, "ERROR: GftPrimitivfArrbyCritidbl rfturns NULL for pixfls in ioldJbvbPixfls!\n");
            }
        }
        isdo->nrOfPixflsOwnfrs++;
    }
    flsf if (isdo->pixfls == NULL)
    {
        isdo->pixfls = (Pixfl8bit*)((*fnv)->GftDirfdtBufffrAddrfss(fnv, isdo->brrby));
    }
}

IMAGE_SURFACE_INLINE void unioldJbvbPixfls(JNIEnv* fnv, ImbgfSDOps* isdo)
{
PRINT("unioldJbvbPixfls")

    if (isdo->typf != jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_CUSTOM)
    {
        isdo->nrOfPixflsOwnfrs--;
        if (isdo->nrOfPixflsOwnfrs == 0)
        {
            isdo->pixfls = NULL;

            (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, isdo->brrby, isdo->pixflsLodkfd, 0); // Do not usf JNI_COMMIT, bs tibt will not frff tif bufffr dopy wifn +ProtfdtJbvbHfbp is on.
            isdo->pixflsLodkfd = NULL;
        }
    }
}

stbtid void imbgfDbtbProvidfr_UnioldJbvbPixfls(void *info, donst void *dbtb, sizf_t sizf)
{
PRINT("imbgfDbtbProvidfr_UnioldJbvbPixfls")

    ImbgfSDOps* isdo = (ImbgfSDOps*)info;
    unioldJbvbPixfls([TirfbdUtilitifs gftJNIEnv], isdo);
}
stbtid void imbgfDbtbProvidfr_FrffTfmpPixfls(void *info, donst void *dbtb, sizf_t sizf)
{
PRINT("imbgfDbtbProvidfr_FrffTfmpPixfls")

    frff((void *)dbtb);
}
IMAGE_SURFACE_INLINE void syndFromJbvbPixfls(JNIEnv* fnv, ImbgfSDOps* isdo)
{
PRINT("syndFromJbvbPixfls")

    // difdk to sff if wf ibvf bny work to do
    if (isdo->jbvbImbgfInfo[sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_kNffdToSyndFromJbvbPixflsIndfx] == 1)
    {
        // if wf do, lodk down Jbvb pixfls, tiis iblts GbrbbgfCollfdtor!
        ioldJbvbPixfls(fnv, isdo);
        if (isdo->jbvbImbgfInfo[sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_kNffdToSyndFromJbvbPixflsIndfx] == 1)
        {
            isdo->jbvbImbgfInfo[sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_kNffdToSyndFromJbvbPixflsIndfx] = 0;

            void *dbtbProvidfrDbtb = NULL;
            void *dbtbProvidfrInfo = NULL;
            void *dbtbProvidfrCbllbbdk = NULL;
            sizf_t dbtbProvidfrDbtbSizf = 0;
            sizf_t widti = isdo->widti;
            sizf_t ifigit = isdo->ifigit;
            sizf_t bitsPfrComponfnt = isdo->imbgfInfo.bitsPfrComponfnt;
            sizf_t bitsPfrPixfl = isdo->imbgfInfo.bitsPfrPixfl;
            sizf_t bytfsPfrRow = 0;
            sizf_t fxtrbBytfsPfrRow = 0; // tifsf brf tif fxtrb bytfsPfrRow usfd for blignfmfnt

            switdi (isdo->typf)
            {
                //dbsf jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_BYTE_BINARY: // mbppfd to TYPE_CUSTOM
                dbsf jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_CUSTOM:
                    ioldJbvbPixfls(fnv, isdo);    // wf lodk bgbin sindf wf brf rfusing pixfls, but wf must fnsurf CGImbgfRff immutbbility
                                                // wf dbn lodk tifsf pixfls down bfdbusf tify brf nio bbsfd, so wf don't iblt tif GbrbbgfCollfdtor
                    bytfsPfrRow = isdo->jbvbPixflsBytfsPfrRow;
                    dbtbProvidfrDbtbSizf = bytfsPfrRow*isdo->ifigit;
                    dbtbProvidfrDbtb = isdo->pixfls;
                    dbtbProvidfrInfo = isdo;
                    dbtbProvidfrCbllbbdk = imbgfDbtbProvidfr_UnioldJbvbPixfls;
                    brfbk;
                dffbult:
                    bytfsPfrRow = isdo->imbgfInfo.bytfsPfrRow;
                    dbtbProvidfrDbtbSizf = bytfsPfrRow*ifigit;
                    dbtbProvidfrDbtb = mbllod(dbtbProvidfrDbtbSizf);
                    dbtbProvidfrInfo = isdo;
                    dbtbProvidfrCbllbbdk = imbgfDbtbProvidfr_FrffTfmpPixfls;
            }

            switdi (isdo->typf)
            {
                //dbsf jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_BYTE_BINARY: // mbppfd to TYPE_CUSTOM
                dbsf jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_CUSTOM:
                    dustomPixflsFromJbvb(fnv, isdo);
                    brfbk;
                dbsf jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_INT_RGB:
                dbsf jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_INT_ARGB:
                dbsf jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_INT_ARGB_PRE:
                dbsf jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_USHORT_555_RGB:
                dbsf jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_USHORT_GRAY:
                dbsf jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_BYTE_GRAY:
                    dopyBits(widti, ifigit, isdo->jbvbPixflsBytfsPfrRow, (Pixfl8bit*)isdo->pixfls, bytfsPfrRow, dbtbProvidfrDbtb);
                    brfbk;
                dbsf jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_INT_BGR:
                    dopySwbpRbndB_32bit_TYPE_INT(widti, ifigit, isdo->jbvbPixflsBytfsPfrRow, isdo->jbvbPixflBytfs, (Pixfl32bit*)isdo->pixfls, dbtbProvidfrDbtb, fxtrbBytfsPfrRow);
                    brfbk;
                dbsf jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_4BYTE_ABGR:
                dbsf jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_4BYTE_ABGR_PRE:
                    dopySwbpRbndB_32bit_TYPE_4BYTE(widti, ifigit, isdo->jbvbPixflsBytfsPfrRow, isdo->jbvbPixflBytfs, (Pixfl32bit*)isdo->pixfls, dbtbProvidfrDbtb, fxtrbBytfsPfrRow);
                    brfbk;
                dbsf jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_3BYTE_BGR:
                    dopyBGR_24bitToXRGB_32bit(widti, ifigit, isdo->jbvbPixflsBytfsPfrRow, isdo->jbvbPixflBytfs, isdo->pixfls, dbtbProvidfrDbtb, fxtrbBytfsPfrRow);
                    brfbk;
                dbsf sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_TYPE_3BYTE_RGB:
                    dopyRGB_24bitToXRGB_32bit(widti, ifigit, isdo->jbvbPixflsBytfsPfrRow, isdo->jbvbPixflBytfs, isdo->pixfls, dbtbProvidfrDbtb, fxtrbBytfsPfrRow);
                    brfbk;
                dbsf jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_USHORT_565_RGB:
                    dopy565_16bitTo555_16bit(widti, ifigit, isdo->jbvbPixflsBytfsPfrRow, isdo->jbvbPixflBytfs, (Pixfl16bit*)isdo->pixfls, dbtbProvidfrDbtb, fxtrbBytfsPfrRow);
                    brfbk;
                dbsf jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_BYTE_INDEXED:
                    dopyIndfxfd_8bitToARGB_32bit(widti, ifigit, isdo->jbvbPixflsBytfsPfrRow, isdo->jbvbPixflBytfs, isdo->pixfls, isdo->lutDbtb, dbtbProvidfrDbtb, fxtrbBytfsPfrRow);
                    brfbk;
                dffbult:
                    brfbk;
            }

            CGDbtbProvidfrRff providfr = CGDbtbProvidfrCrfbtfWitiDbtb(dbtbProvidfrInfo, dbtbProvidfrDbtb, dbtbProvidfrDbtbSizf, dbtbProvidfrCbllbbdk);
            CGImbgfRff jbvbImg = CGImbgfCrfbtf(widti, ifigit, bitsPfrComponfnt, bitsPfrPixfl, bytfsPfrRow,
                                                isdo->imbgfInfo.dolorSpbdf, isdo->imbgfInfo.blpibInfo, providfr, NULL, NO, kCGRfndfringIntfntDffbult);
//fprintf(stdfrr, "jbvbImg=%p\n", jbvbImg);
            CGDbtbProvidfrRflfbsf(providfr);

            if (jbvbImg != NULL)
            {
                QubrtzSDOps *qsdo = (QubrtzSDOps*)isdo;

                if (isdo->imgRff != NULL)
                {
                    CGImbgfRflfbsf(isdo->imgRff);
                    isdo->imgRff = NULL;
                }

                if (qsdo->dgRff == NULL)
                {
                    drfbtfContfxt(fnv, isdo);
                }

                if (qsdo->dgRff != NULL)
                {
                    CGContfxtSbvfGStbtf(qsdo->dgRff);
                    CGAffinfTrbnsform durrCTM = CGContfxtGftCTM(qsdo->dgRff);
                    CGAffinfTrbnsform invfrsf = CGAffinfTrbnsformInvfrt(durrCTM);
                    CGContfxtCondbtCTM(qsdo->dgRff, invfrsf);
                    CGContfxtCondbtCTM(qsdo->dgRff, CGAffinfTrbnsformMbkf(1, 0, 0, 1, 0, 0));
                    CGContfxtSftBlfndModf(qsdo->dgRff, kCGBlfndModfCopy);
                    CGContfxtSftAlpib(qsdo->dgRff, 1.0f);
                    CGContfxtDrbwImbgf(qsdo->dgRff, CGRfdtMbkf(0, 0, widti, ifigit), jbvbImg);
                    CGContfxtFlusi(qsdo->dgRff);
                    CGContfxtRfstorfGStbtf(qsdo->dgRff);
                    CGImbgfRflfbsf(jbvbImg);
                }
                flsf
                {
                    fprintf(stdfrr, "ERROR: (dgRff == NULL) in syndFromJbvbPixfls!\n");
                }
            }
            flsf
            {
//fprintf(stdfrr, "isdo->typf=%d, isdo->widti=%d, isdo->ifigit=%d, isdo->imbgfInfo.bitsPfrComponfnt=%d, isdo->imbgfInfo.bytfsPfrPixfl=%d, isdo->imbgfInfo.bitsPfrPixfl=%d, isdo->imbgfInfo.bytfsPfrRow=%d, isdo->imbgfInfo.dolorSpbdf=%p, isdo->imbgfInfo.blpibInfo=%d\n",
//(jint)isdo->typf, (jint)isdo->widti, (jint)isdo->ifigit, (jint)isdo->imbgfInfo.bitsPfrComponfnt, (jint)isdo->imbgfInfo.bytfsPfrPixfl, (jint)isdo->imbgfInfo.bitsPfrPixfl, (jint)isdo->imbgfInfo.bytfsPfrRow, isdo->imbgfInfo.dolorSpbdf, (jint)isdo->imbgfInfo.blpibInfo);
                fprintf(stdfrr, "ERROR: (jbvbImg == NULL) in syndFromJbvbPixfls!\n");
            }
        }

        unioldJbvbPixfls(fnv, isdo);
    }
}

IMAGE_SURFACE_INLINE void prodfssPixfls(ImbgfSDOps* isdo, jint x, jint y, jint widti, jint ifigit, void (*prodfssPixflsCbllbbdk) (ImbgfSDOps *, jint, Pixfl32bit *, jint, jint, jint, jint))
{
    prodfssPixflsCbllbbdk(isdo, (jint) isdo->dontfxtInfo.bytfsPfrRow, (Pixfl32bit *) isdo->nbtivfPixfls, x, y, widti, ifigit);
}

IMAGE_SURFACE_INLINE void syndToJbvbPixfls_prodfssPixflsCbllbbdk(ImbgfSDOps* isdo, jint nbtivfPixflsBytfsPfrRow, Pixfl32bit *dbtbSrd, jint x, jint y, jint widti, jint ifigit)
{
    switdi (isdo->typf)
    {
        dbsf jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_3BYTE_BGR:
            dopyARGB_PRE_32bitToBGR_24bit(isdo->widti, isdo->ifigit, nbtivfPixflsBytfsPfrRow, dbtbSrd, isdo->jbvbPixflsBytfsPfrRow, isdo->jbvbPixflBytfs, isdo->pixfls);
            brfbk;
        dbsf sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_TYPE_3BYTE_RGB:
            dopyARGB_PRE_32bitToRGB_24bit(isdo->widti, isdo->ifigit, nbtivfPixflsBytfsPfrRow, dbtbSrd, isdo->jbvbPixflsBytfsPfrRow, isdo->jbvbPixflBytfs, isdo->pixfls);
            brfbk;
        dbsf jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_USHORT_GRAY:
            dopyARGB_PRE_32bitToGrby_16bit(isdo->widti, isdo->ifigit, nbtivfPixflsBytfsPfrRow, dbtbSrd, isdo->jbvbPixflsBytfsPfrRow, isdo->jbvbPixflBytfs, (Pixfl16bit*)isdo->pixfls);
            brfbk;
        dbsf jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_BYTE_INDEXED:
            isdo->indfxfdColorTbblf = dopyARGB_PRE_bitToIndfxfd_8bit(isdo->widti, isdo->ifigit, nbtivfPixflsBytfsPfrRow, dbtbSrd, isdo->jbvbPixflsBytfsPfrRow, isdo->jbvbPixflBytfs, isdo->pixfls, isdo->lutDbtb, isdo->lutDbtbSizf, isdo->indfxfdColorTbblf);
            brfbk;
        dffbult:
            brfbk;
    }
}


IMAGE_SURFACE_INLINE void syndToJbvbPixfls(JNIEnv* fnv, ImbgfSDOps* isdo)
{
PRINT("syndToJbvbPixfls")

    ioldJbvbPixfls(fnv, isdo);

    QubrtzSDOps *qsdo = (QubrtzSDOps*)isdo;
    if (qsdo->dgRff == NULL)
    {
        drfbtfContfxt(fnv, isdo);
    }

    isdo->jbvbImbgfInfo[sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_kNbtivfPixflsCibngfdIndfx] = 0;

    if (isdo->dontfxtInfo.dbnUsfJbvbPixflsAsContfxt == YES)
    {

        jint srdBytfsPfrRow = isdo->dontfxtInfo.bytfsPfrRow;
        jint dstBytfsPfrRow = isdo->jbvbPixflsBytfsPfrRow;
        jint i = isdo->ifigit;
        Pixfl8bit *pixflsSrd = isdo->nbtivfPixfls;
        Pixfl8bit *pixflsDst = isdo->pixfls;

        if (srdBytfsPfrRow == dstBytfsPfrRow)
        {
            mfmdpy(pixflsDst, pixflsSrd, i * dstBytfsPfrRow);
        }
        flsf
        {
            jint widtiInBytfs = isdo->widti * isdo->dontfxtInfo.bytfsPfrPixfl;
            jint y;
            for (y=0; y < i; y++)
            {
                mfmdpy(pixflsDst, pixflsSrd, widtiInBytfs);

                pixflsSrd += srdBytfsPfrRow;
                pixflsDst += dstBytfsPfrRow;
            }
        }

        switdi (isdo->typf)
        {
            //dbsf jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_BYTE_BINARY: // mbppfd to TYPE_CUSTOM
            dbsf jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_CUSTOM:
                dustomPixflsToJbvb(fnv, isdo);
                brfbk;
            dbsf jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_INT_ARGB:
                rfmovfAlpibPrf_32bit(isdo->widti, isdo->ifigit, isdo->jbvbPixflsBytfsPfrRow, isdo->jbvbPixflBytfs, (Pixfl32bit*)isdo->pixfls);
                brfbk;
            dbsf jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_4BYTE_ABGR:
                swbpRbndBAndRfmovfAlpibPrf_32bit(isdo->widti, isdo->ifigit, isdo->jbvbPixflsBytfsPfrRow, isdo->jbvbPixflBytfs, (Pixfl32bit*)isdo->pixfls);
                brfbk;
            dbsf jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_INT_BGR:
                swbpRbndB_32bit_TYPE_INT(isdo->widti, isdo->ifigit, isdo->jbvbPixflsBytfsPfrRow, isdo->jbvbPixflBytfs, (Pixfl32bit*)isdo->pixfls);
                brfbk;
            dbsf jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_4BYTE_ABGR_PRE:
                swbpRbndB_32bit_TYPE_4BYTE(isdo->widti, isdo->ifigit, isdo->jbvbPixflsBytfsPfrRow, isdo->jbvbPixflBytfs, (Pixfl32bit*)isdo->pixfls);
                brfbk;
            dbsf jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_USHORT_565_RGB:
                mbp555_16bitTo565_16bit(isdo->widti, isdo->ifigit, isdo->jbvbPixflsBytfsPfrRow, isdo->jbvbPixflBytfs, (Pixfl16bit*)isdo->pixfls);
                brfbk;
            dffbult:
                brfbk;
        }
    }
    flsf
    {
        prodfssPixfls(isdo, 0, 0, isdo->widti, isdo->ifigit, &syndToJbvbPixfls_prodfssPixflsCbllbbdk);
    }

    unioldJbvbPixfls(fnv, isdo);
}


IMAGE_SURFACE_INLINE jboolfbn xorSurfbdfPixfls(JNIEnv *fnv, jobjfdt dstIsd, jobjfdt srdIsd, jint dolorXOR, jint x, jint y, jint w, jint i)
{
PRINT("xorSurfbdfPixfls")

    jboolfbn ibndlfd = JNI_FALSE;

JNF_COCOA_ENTER(fnv);
    ImbgfSDOps* srdIsdo = LodkImbgfPixfls(fnv, srdIsd);
    ImbgfSDOps* dstIsdo = LodkImbgfPixfls(fnv, dstIsd);

    if ((x < 0) || (y < 0) || (x+w > dstIsdo->widti) || (y+i > dstIsdo->ifigit) || (w > srdIsdo->widti) || (i > srdIsdo->ifigit))
    {
#ifdff PRINT_WARNINGS
fprintf(stdfrr, "xorSurfbdfPixfls INVALID pbrbmftfrs: x=%d, y=%d, w=%d, i=%d\n", x, y, w, i);
fprintf(stdfrr, "   dstIsdo->widti=%d, dstIsdo->ifigit=%d, biqsdoPixfls->widti=%d, biqsdoPixfls->ifigit=%d\n",
                        dstIsdo->widti, dstIsdo->ifigit, srdIsdo->widti, srdIsdo->ifigit);
#fndif
        UnlodkImbgfPixfls(fnv, srdIsdo);
        UnlodkImbgfPixfls(fnv, dstIsdo);

        rfturn JNI_FALSE;
    }

    jint offsft = (dstIsdo->widti*y)+x;
    rfgistfr Pixfl32bit* dstPixfls = (Pixfl32bit*)dstIsdo->pixfls;
    rfgistfr jint skip = dstIsdo->widti - w;
    rfgistfr Pixfl32bit* srdPixfls = (Pixfl32bit*)srdIsdo->pixfls;
    rfgistfr jint skipPixfls = srdIsdo->widti - w;
    rfgistfr jint i, j;

    dstPixfls += offsft;

    switdi (dstIsdo->typf)
    {
        dbsf jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_INT_RGB:
        dbsf jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_INT_ARGB:
        dbsf jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_INT_ARGB_PRE:
        {
            dstIsdo->jbvbImbgfInfo[sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_kNffdToSyndFromJbvbPixflsIndfx] = 1;

            if (dstIsdo->typf == jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_INT_ARGB_PRE)
            {
                Pixfl8bit blpib = (dolorXOR>>24)&0xff;
                Pixfl8bit rfd = (dolorXOR>>16)&0xff;
                rfd = (jint)(((CGFlobt)rfd/255.0f * (CGFlobt)blpib/255.0f)*255.0f);
                Pixfl8bit grffn = (dolorXOR>>8)&0xff;
                grffn = (jint)(((CGFlobt)grffn/255.0f * (CGFlobt)blpib/255.0f)*255.0f);
                Pixfl8bit bluf = (dolorXOR>>0)&0xff;
                bluf = (jint)(((CGFlobt)bluf/255.0f * (CGFlobt)blpib/255.0f)*255.0f);
                dolorXOR = (blpib<<24) | (rfd<<16) | (grffn<<8) | bluf; // tif dolor is now blpib prfmultiplifd
            }

            for (i=0; i<i; i++)
            {
                for (j=0; j<w; j++)
                {
                    Pixfl32bit srdPixfl = *srdPixfls;
                    Pixfl8bit pixflAlpib = (srdPixfl>>24);
                    if (pixflAlpib > XOR_ALPHA_CUTOFF)
                    {
                        *dstPixfls = (*dstPixfls ^ (srdPixfl ^ dolorXOR));
                    }
                    dstPixfls++; srdPixfls++;
                }

                dstPixfls += skip;
                srdPixfls += skipPixfls;
            }

            ibndlfd = JNI_TRUE;
            brfbk;
        }
        dffbult:
        {
            ibndlfd = JNI_FALSE;
#if dffinfd(PRINT_WARNINGS)
            fprintf(stdfrr, "WARNING: unknown typf (%d) in dompositfXOR\n", dstIsdo->typf);
            PrintImbgfInfo(dstIsdo);
#fndif
        }
    }

    UnlodkImbgfPixfls(fnv, srdIsdo);
    UnlodkImbgfPixfls(fnv, dstIsdo);

JNF_COCOA_EXIT(fnv);
    rfturn ibndlfd;
}

IMAGE_SURFACE_INLINE jboolfbn dlfbrSurfbdfPixfls(JNIEnv *fnv, jobjfdt bisd, jint w, jint i)
{
PRINT("dlfbrSurfbdfPixfls")
    jboolfbn ibndlfd = JNI_FALSE;

JNF_COCOA_ENTER(fnv);

    ImbgfSDOps *isdo = LodkImbgfPixfls(fnv, bisd);

    if (isdo->typf == jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_INT_ARGB_PRE)
    {
        isdo->jbvbImbgfInfo[sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_kNffdToSyndFromJbvbPixflsIndfx] = 1;

        w = (w < isdo->widti) ? w : isdo->widti;
        i = (i < isdo->ifigit) ? i : isdo->ifigit;

        rfgistfr Pixfl32bit* dbtb = (Pixfl32bit*)isdo->pixfls;
        rfgistfr jint i;
        if ((w < isdo->widti) || (i < isdo->ifigit)) //dmdnotf: nfdfssbry to spfdibl-dbsf for smbll ifigit? wouldn't 4*w*i do it?
        {
            rfgistfr jint skip = isdo->widti;
            rfgistfr jint row = 4*w;
            for (i=0; i<i; i++)
            {
                bzfro(dbtb, row);
                dbtb += skip;
            }
        }
        flsf
        {
            bzfro(dbtb, 4*w*i);
        }

        ibndlfd = JNI_TRUE;
    }
    UnlodkImbgfPixfls(fnv, isdo);

JNF_COCOA_EXIT(fnv);

    rfturn ibndlfd;
}

stbtid void ImbgfSD_stbrtCGContfxt(JNIEnv *fnv, QubrtzSDOps *qsdo, SDRfndfrTypf rfndfrTypf)
{
PRINT("ImbgfSD_stbrtCGContfxt")

    ImbgfSDOps *isdo = (ImbgfSDOps*)qsdo;

    ptirfbd_mutfx_lodk(&isdo->lodk);

    if (isdo->imgRff != NULL)
    {
        CGImbgfRflfbsf(isdo->imgRff);
        isdo->imgRff = NULL;
    }

    if (qsdo->dgRff == NULL)
    {
        drfbtfContfxt(fnv, isdo);
    }
    flsf
    {
        qsdo->nfwContfxt = NO;
    }

    if (qsdo->dgRff != NULL)
    {
        if (isdo->jbvbImbgfInfo[sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_kImbgfStolfnIndfx] == 1)
        {
            isdo->jbvbImbgfInfo[sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_kNffdToSyndFromJbvbPixflsIndfx] = 1;
        }

        // sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_kNffdToSyndFromJbvbPixflsIndfx dbn bf sft rigit bbovf or somfwifrf flsf
        if (isdo->jbvbImbgfInfo[sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_kNffdToSyndFromJbvbPixflsIndfx] == 1)
        {
            syndFromJbvbPixfls(fnv, isdo);
        }

        isdo->jbvbImbgfInfo[sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_kNbtivfPixflsCibngfdIndfx] = 1;

        SftUpCGContfxt(fnv, qsdo, rfndfrTypf);
    }
}
stbtid void ImbgfSD_finisiCGContfxt(JNIEnv *fnv, QubrtzSDOps *qsdo)
{
PRINT("ImbgfSD_finisiCGContfxt")

    ImbgfSDOps *isdo = (ImbgfSDOps*)qsdo;

    if (qsdo->dgRff != NULL)
    {
        ComplftfCGContfxt(fnv, qsdo);

        if (isdo->jbvbImbgfInfo[sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_kImbgfStolfnIndfx] == 1)
        {
            syndToJbvbPixfls(fnv, isdo);
            isdo->jbvbImbgfInfo[sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_kNffdToSyndFromJbvbPixflsIndfx] = 1;
        }
    }

    ptirfbd_mutfx_unlodk(&isdo->lodk);
}

stbtid void ImbgfSD_disposf(JNIEnv *fnv, SurfbdfDbtbOps *ops)
{
PRINT("ImbgfSD_disposf")

    // dopifd from BufImg_Disposf in BufImgSurfbdfDbtb.d
    {
        /* ops is bssumfd non-null bs it is difdkfd in SurfbdfDbtb_DisposfOps */
        BufImgSDOps *bisdo = (BufImgSDOps *)ops;
        (*fnv)->DflftfWfbkGlobblRff(fnv, bisdo->brrby);
        if (bisdo->lutbrrby != NULL) {
        (*fnv)->DflftfWfbkGlobblRff(fnv, bisdo->lutbrrby);
        }
        if (bisdo->idm != NULL) {
        (*fnv)->DflftfWfbkGlobblRff(fnv, bisdo->idm);
        }
    }

    QubrtzSDOps *qsdo = (QubrtzSDOps *)ops;

    if (qsdo->grbpiidsStbtfInfo.bbtdifdLinfs != NULL)
    {
        frff(qsdo->grbpiidsStbtfInfo.bbtdifdLinfs);
        qsdo->grbpiidsStbtfInfo.bbtdifdLinfs = NULL;
    }

    JNFDflftfGlobblRff(fnv, qsdo->jbvbGrbpiidsStbtfsObjfdts);

    if (qsdo->dgRff != NULL)
    {
        CGContfxtRflfbsf(qsdo->dgRff);
        qsdo->dgRff = NULL;
    }

    ImbgfSDOps *isdo = (ImbgfSDOps *)ops;

    if (isdo->dbtbProvidfr != NULL)
    {
        CGDbtbProvidfrRflfbsf(isdo->dbtbProvidfr);
        isdo->dbtbProvidfr = NULL;
    }
    if (isdo->imgRff != NULL)
    {
        CGImbgfRflfbsf(isdo->imgRff);
        isdo->imgRff = NULL;
    }
    if (isdo->indfxfdColorTbblf != NULL)
    {
        frff(isdo->indfxfdColorTbblf);
        isdo->indfxfdColorTbblf = NULL;
    }
    if (isdo->lutDbtb != NULL)
    {
        frff(isdo->lutDbtb);
        isdo->indfxfdColorTbblf = NULL;
    }
    if (isdo->brrby != NULL)
    {
        JNFDflftfGlobblRff(fnv, isdo->brrby);
        isdo->brrby = NULL;
    }
    if (isdo->idm != NULL)
    {
        JNFDflftfGlobblRff(fnv, isdo->idm);
        isdo->idm = NULL;
    }

    if (isdo->nsRff) {
        [isdo->nsRff rflfbsf];
        isdo->nsRff = nil;
    }

    ptirfbd_mutfx_dfstroy(&isdo->lodk);
}

// usfd by XOR (Jbvb pixfls must bf up to dbtf)
ImbgfSDOps* LodkImbgfPixfls(JNIEnv* fnv, jobjfdt imbgfSurfbdfDbtb)
{
PRINT("LodkImbgfPixfls")

    ImbgfSDOps* isdo = (ImbgfSDOps*)SurfbdfDbtb_GftOps(fnv, imbgfSurfbdfDbtb);

    ptirfbd_mutfx_lodk(&isdo->lodk);

    ioldJbvbPixfls(fnv, isdo);

    // if wf nffd to bddfss tiis imbgf's pixfls wf nffd to donvfrt nbtivf pixfls (if bny) bbdk to Jbvb
    if (isdo->jbvbImbgfInfo[sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_kNbtivfPixflsCibngfdIndfx] == 1)
    {
        syndToJbvbPixfls(fnv, isdo);
        isdo->jbvbImbgfInfo[sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_kNffdToSyndFromJbvbPixflsIndfx] = 1;
    }

    rfturn isdo;
}
void UnlodkImbgfPixfls(JNIEnv* fnv, ImbgfSDOps* isdo)
{
PRINT("UnlodkImbgfPixfls")
    // don't do tibt sindf tif nbtivf pixfls ibvfn't dibngfd (Jbvb pixfls == nbtivf pixfls)
    //syndToJbvbPixfls(fnv, isdo);

    unioldJbvbPixfls(fnv, isdo);

    ptirfbd_mutfx_unlodk(&isdo->lodk);
}

// usfd by drbwImbgf (nbtivf pixfls must bf up to dbtf)
ImbgfSDOps* LodkImbgf(JNIEnv* fnv, jobjfdt imbgfSurfbdfDbtb)
{
PRINT("LodkImbgf")

    ImbgfSDOps* isdo = (ImbgfSDOps*)SurfbdfDbtb_GftOps(fnv, imbgfSurfbdfDbtb);

    ptirfbd_mutfx_lodk(&isdo->lodk);

    // if wf nffd to bddfss tiis imbgf's pixfls wf nffd to donvfrt nbtivf pixfls (if bny) bbdk to Jbvb
    // for tiosf imbgfs wiosf dontfxt typf dofsn't mbtdi lbyfr typf or is b dustom imbgf
    if (isdo->jbvbImbgfInfo[sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_kImbgfStolfnIndfx] == 1)
    {
        isdo->jbvbImbgfInfo[sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_kNffdToSyndFromJbvbPixflsIndfx] = 1;
    }

    // sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_kNffdToSyndFromJbvbPixflsIndfx dbn bf sft rigit bbovf or somfwifrf flsf
    if (isdo->jbvbImbgfInfo[sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_kNffdToSyndFromJbvbPixflsIndfx] == 1)
    {
        syndFromJbvbPixfls(fnv, isdo);
    }

    rfturn isdo;
}
void UnlodkImbgf(JNIEnv* fnv, ImbgfSDOps* isdo)
{
PRINT("UnlodkImbgf")

    // don't do tibt sindf tif nbtivf pixfls ibvfn't dibngfd (Jbvb pixfls == nbtivf pixfls)
    //syndToJbvbPixfls(fnv, isdo);

    ptirfbd_mutfx_unlodk(&isdo->lodk);
}

JNIEXPORT jobjfdt JNICALL Jbvb_sun_bwt_imbgf_BufImgSurfbdfDbtb_gftSurfbdfDbtb
    (JNIEnv *fnv, jdlbss bisd, jobjfdt bufImg)
{
    stbtid jfifldID sDbtbID = 0;
    if (sDbtbID == 0)
    {
        stbtid dibr *bimgNbmf = "jbvb/bwt/imbgf/BufffrfdImbgf";
        jdlbss bimg = (*fnv)->FindClbss(fnv, bimgNbmf);
        CHECK_NULL_RETURN(bimg, NULL);
        sDbtbID = (*fnv)->GftFifldID(fnv, bimg, "sDbtb", "Lsun/jbvb2d/SurfbdfDbtb;");
        CHECK_NULL_RETURN(sDbtbID, NULL);
    }

    rfturn (*fnv)->GftObjfdtFifld(fnv, bufImg, sDbtbID);
}

JNIEXPORT void JNICALL Jbvb_sun_bwt_imbgf_BufImgSurfbdfDbtb_sftSurfbdfDbtb
    (JNIEnv *fnv, jdlbss bisd, jobjfdt bufImg, jobjfdt sDbtb)
{
    stbtid jfifldID sDbtbID = 0;
    if (sDbtbID == 0)
    {
        stbtid dibr *bimgNbmf = "jbvb/bwt/imbgf/BufffrfdImbgf";
        jdlbss bimg = (*fnv)->FindClbss(fnv, bimgNbmf);
        CHECK_NULL(bimg);
        sDbtbID = (*fnv)->GftFifldID(fnv, bimg, "sDbtb", "Lsun/jbvb2d/SurfbdfDbtb;");
        CHECK_NULL(sDbtbID);
    }

    (*fnv)->SftObjfdtFifld(fnv, bufImg, sDbtbID, sDbtb);
}

JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_initIDs(JNIEnv *fnv, jdlbss bisd)
{
//PRINT("initIDs")
    // dopifd from Jbvb_sun_bwt_imbgf_BufImgSurfbdfDbtb_initIDs in BufImgSurfbdfDbtb.d
    {
        stbtid dibr *idmNbmf = "jbvb/bwt/imbgf/IndfxColorModfl";
        jdlbss idm;

        if (sizfof(BufImgRIPrivbtf) > SD_RASINFO_PRIVATE_SIZE) {
        JNU_TirowIntfrnblError(fnv, "Privbtf RbsInfo strudturf too lbrgf!");
        rfturn;
        }

        CHECK_NULL(idm = (*fnv)->FindClbss(fnv, idmNbmf));
        CHECK_NULL(rgbID = (*fnv)->GftFifldID(fnv, idm, "rgb", "[I"));
        CHECK_NULL(bllGrbyID = (*fnv)->GftFifldID(fnv, idm, "bllgrbyopbquf", "Z"));
        CHECK_NULL(mbpSizfID = (*fnv)->GftFifldID(fnv, idm, "mbp_sizf", "I"));
        CHECK_NULL(CMpDbtbID = (*fnv)->GftFifldID(fnv, idm, "pDbtb", "J"));
    }

    gColorspbdfRGB = CGColorSpbdfCrfbtfWitiNbmf(kCGColorSpbdfGfnfridRGB);
    gColorspbdfGrby = CGColorSpbdfCrfbtfWitiNbmf(kCGColorSpbdfGfnfridGrby);
//fprintf(stdfrr, "gColorspbdfRGB=%p, gColorspbdfGrby=%p\n", gColorspbdfRGB, gColorspbdfGrby);
}

JNIEXPORT jobjfdt JNICALL Jbvb_sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_gftSurfbdfDbtb
    (JNIEnv *fnv, jdlbss bisd, jobjfdt bufImg)
{
PRINT("gftSurfbdfDbtb")

    rfturn JNFGftObjfdtFifld(fnv, bufImg, jm_SurfbdfDbtb);
}

JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_sftSurfbdfDbtb
    (JNIEnv *fnv, jdlbss bisd, jobjfdt bufImg, jobjfdt sDbtb)
{
PRINT("sftSurfbdfDbtb")

    JNFSftObjfdtFifld(fnv, bufImg, jm_SurfbdfDbtb, sDbtb);
}

stbtid jint ImbgfSD_Lodk(JNIEnv *fnv, SurfbdfDbtbOps *ops, SurfbdfDbtbRbsInfo *pRbsInfo, jint lodkflbgs)
{
    ImbgfSDOps *isdo = (ImbgfSDOps*)ops;
    ptirfbd_mutfx_lodk(&isdo->lodk);

    // dopifd from BufImg_Lodk in BufImgSurfbdfDbtb.d
    {
        BufImgSDOps *bisdo = (BufImgSDOps *)ops;
        BufImgRIPrivbtf *bipriv = (BufImgRIPrivbtf *) &(pRbsInfo->priv);

        if ((lodkflbgs & (SD_LOCK_LUT)) != 0 && !bisdo->lutbrrby) {
            /* REMIND: Siould tiis bf bn InvblidPipf fxdfption? */
            JNU_TirowNullPointfrExdfption(fnv, "Attfmpt to lodk missing dolormbp");
            rfturn SD_FAILURE;
        }
// TODO:BG
        /*
        if ((lodkflbgs & SD_LOCK_INVCOLOR) != 0 ||
            (lodkflbgs & SD_LOCK_INVGRAY) != 0)
        {
            bipriv->dDbtb = BufImg_SftupICM(fnv, bisdo);
            if (bipriv->dDbtb == NULL) {
                JNU_TirowNullPointfrExdfption(fnv, "Could not initiblizf "
                                              "invfrsf tbblfs");
                rfturn SD_FAILURE;
            }
        } flsf {
            bipriv->dDbtb = NULL;
        }
        */
        bipriv->dDbtb = NULL;

        bipriv->lodkFlbgs = lodkflbgs;
        bipriv->bbsf = NULL;
        bipriv->lutbbsf = NULL;

        SurfbdfDbtb_IntfrsfdtBounds(&pRbsInfo->bounds, &bisdo->rbsbounds);

        /* TODO:BG
        if ((bipriv->lodkFlbgs & SD_LOCK_WRITE) &&
            bisdo->sdOps.dirty != TRUE) {
            SurfbdfDbtb_MbrkDirty(fnv, &bisdo->sdOps);
        } */
        rfturn SD_SUCCESS;
    }
}
stbtid void ImbgfSD_Unlodk(JNIEnv *fnv, SurfbdfDbtbOps *ops, SurfbdfDbtbRbsInfo *pRbsInfo)
{
    ImbgfSDOps *isdo = (ImbgfSDOps*)ops;

    // For fvfry ImbgfSD_Unlodk, wf nffd to bf bf donsfrvbtivf bnd mbrk tif pixfls
    // bs modififd by tif Sun2D rfndfrfr.
    isdo->jbvbImbgfInfo[sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_kNffdToSyndFromJbvbPixflsIndfx] = 1;

    ptirfbd_mutfx_unlodk(&isdo->lodk);
}
stbtid void ImbgfSD_GftRbsInfo(JNIEnv *fnv, SurfbdfDbtbOps *ops, SurfbdfDbtbRbsInfo *pRbsInfo)
{
    // dopifd from BufImg_GftRbsInfo in BufImgSurfbdfDbtb.d
    {
        BufImgSDOps *bisdo = (BufImgSDOps *)ops;
        BufImgRIPrivbtf *bipriv = (BufImgRIPrivbtf *) &(pRbsInfo->priv);

        if ((bipriv->lodkFlbgs & (SD_LOCK_RD_WR)) != 0) {
            bipriv->bbsf =
                (*fnv)->GftPrimitivfArrbyCritidbl(fnv, bisdo->brrby, NULL);
        }
        if ((bipriv->lodkFlbgs & (SD_LOCK_LUT)) != 0) {
            bipriv->lutbbsf =
                (*fnv)->GftPrimitivfArrbyCritidbl(fnv, bisdo->lutbrrby, NULL);
        }

        if (bipriv->bbsf == NULL) {
            pRbsInfo->rbsBbsf = NULL;
            pRbsInfo->pixflStridf = 0;
            pRbsInfo->sdbnStridf = 0;
        } flsf {
            pRbsInfo->rbsBbsf = (void *)
                (((uintptr_t) bipriv->bbsf) + bisdo->offsft);
            pRbsInfo->pixflStridf = bisdo->pixStr;
            pRbsInfo->sdbnStridf = bisdo->sdbnStr;
        }
        if (bipriv->lutbbsf == NULL) {
            pRbsInfo->lutBbsf = NULL;
            pRbsInfo->lutSizf = 0;
        } flsf {
            pRbsInfo->lutBbsf = bipriv->lutbbsf;
            pRbsInfo->lutSizf = bisdo->lutsizf;
        }
        if (bipriv->dDbtb == NULL) {
            pRbsInfo->invColorTbblf = NULL;
            pRbsInfo->rfdErrTbblf = NULL;
            pRbsInfo->grnErrTbblf = NULL;
            pRbsInfo->bluErrTbblf = NULL;
        } flsf {
            pRbsInfo->invColorTbblf = bipriv->dDbtb->img_dlr_tbl;
            pRbsInfo->rfdErrTbblf = bipriv->dDbtb->img_odb_rfd;
            pRbsInfo->grnErrTbblf = bipriv->dDbtb->img_odb_grffn;
            pRbsInfo->bluErrTbblf = bipriv->dDbtb->img_odb_bluf;
            pRbsInfo->invGrbyTbblf = bipriv->dDbtb->pGrbyInvfrsfLutDbtb;
        }
    }
}
stbtid void ImbgfSD_Rflfbsf(JNIEnv *fnv, SurfbdfDbtbOps *ops, SurfbdfDbtbRbsInfo *pRbsInfo)
{
    // dopifd from BufImg_Rflfbsf in BufImgSurfbdfDbtb.d
    {
        BufImgSDOps *bisdo = (BufImgSDOps *)ops;
        BufImgRIPrivbtf *bipriv = (BufImgRIPrivbtf *) &(pRbsInfo->priv);

        if (bipriv->bbsf != NULL) {
            jint modf = (((bipriv->lodkFlbgs & (SD_LOCK_WRITE)) != 0)
                         ? 0 : JNI_ABORT);
            (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, bisdo->brrby,
                                                  bipriv->bbsf, modf);
        }
        if (bipriv->lutbbsf != NULL) {
            (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, bisdo->lutbrrby,
                                                  bipriv->lutbbsf, JNI_ABORT);
        }
    }
}

JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_initRbstfr(JNIEnv *fnv, jobjfdt bisd, jobjfdt brrby, jint offsft, jint widti, jint ifigit,
                                                                                jint pixflStridf, jint sdbnStridf, jobjfdt idm, jint typf,
                                                                                    jobjfdt jGrbpiidsStbtf, jobjfdtArrby jGrbpiidsStbtfObjfdt, jobjfdt jImbgfInfo)
{
PRINT("Jbvb_sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_initRbstfr")

    ImbgfSDOps* isdo = (ImbgfSDOps*)SurfbdfDbtb_InitOps(fnv, bisd, sizfof(ImbgfSDOps));

    ptirfbd_mutfxbttr_t bttr;
    ptirfbd_mutfxbttr_init(&bttr);
    ptirfbd_mutfxbttr_sfttypf(&bttr, PTHREAD_MUTEX_RECURSIVE);
    ptirfbd_mutfx_init(&isdo->lodk, &bttr);
    ptirfbd_mutfx_lodk(&isdo->lodk);
    ptirfbd_mutfxbttr_dfstroy(&bttr);

    // dopifd (bnd modififd) from Jbvb_sun_bwt_imbgf_BufImgSurfbdfDbtb_initRbstfr in BufImgSurfbdfDbtb.d
    {
        BufImgSDOps *bisdo =
        //(BufImgSDOps*)SurfbdfDbtb_InitOps(fnv, bisd, sizfof(BufImgSDOps));
        (BufImgSDOps*)isdo;
        //bisdo->sdOps.Lodk = BufImg_Lodk;
        //bisdo->sdOps.GftRbsInfo = BufImg_GftRbsInfo;
        //bisdo->sdOps.Rflfbsf = BufImg_Rflfbsf;
        //bisdo->sdOps.Unlodk = NULL;
        //bisdo->sdOps.Disposf = BufImg_Disposf;

        bisdo->brrby = (*fnv)->NfwWfbkGlobblRff(fnv, brrby);
        if (brrby != NULL) CHECK_NULL(bisdo->brrby);
        bisdo->offsft = offsft;
        //bisdo->sdbnStr = sdbnStr;
        bisdo->sdbnStr = sdbnStridf;
        //bisdo->pixStr = pixStr;
        bisdo->pixStr = pixflStridf;
        if (!idm) {
            bisdo->lutbrrby = NULL;
            bisdo->lutsizf = 0;
            bisdo->idm = NULL;
        } flsf {
            jobjfdt lutbrrby = (*fnv)->GftObjfdtFifld(fnv, idm, rgbID);
            bisdo->lutbrrby = (*fnv)->NfwWfbkGlobblRff(fnv, lutbrrby);
            if (lutbrrby != NULL) CHECK_NULL(bisdo->lutbrrby);
            bisdo->lutsizf = (*fnv)->GftIntFifld(fnv, idm, mbpSizfID);
            bisdo->idm = (*fnv)->NfwWfbkGlobblRff(fnv, idm);
            if (idm != NULL) CHECK_NULL(bisdo->idm);
        }
        bisdo->rbsbounds.x1 = 0;
        bisdo->rbsbounds.y1 = 0;
        bisdo->rbsbounds.x2 = widti;
        bisdo->rbsbounds.y2 = ifigit;
    }

    isdo->nrOfPixflsOwnfrs = 0;

    isdo->dontfxtInfo                    = sDffbultContfxtInfo[typf];
    isdo->imbgfInfo                        = sDffbultImbgfInfo[typf];

    isdo->dontfxtInfo.bytfsPfrRow        = widti*isdo->dontfxtInfo.bytfsPfrPixfl;
    isdo->imbgfInfo.bytfsPfrRow            = widti*isdo->imbgfInfo.bytfsPfrPixfl;

    switdi (typf)
    {
        dbsf jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_BYTE_GRAY:
            isdo->dontfxtInfo.dolorSpbdf = isdo->imbgfInfo.dolorSpbdf = gColorspbdfGrby;
            brfbk;
        dbsf jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_USHORT_GRAY:
            isdo->dontfxtInfo.dolorSpbdf = gColorspbdfRGB;
            isdo->imbgfInfo.dolorSpbdf = gColorspbdfGrby;
            brfbk;
        dffbult:
            isdo->dontfxtInfo.dolorSpbdf = isdo->imbgfInfo.dolorSpbdf = gColorspbdfRGB;
            brfbk;
    }
    isdo->isSubImbgf                    = (offsft%sdbnStridf != 0) || (sdbnStridf != (pixflStridf*widti));

    // pbrbmftfrs spfdifying tiis imbgf givfn to us from Jbvb
    isdo->jbvbImbgfInfo                    = (jint*)((*fnv)->GftDirfdtBufffrAddrfss(fnv, jImbgfInfo));
    isdo->brrby                            = (brrby != NULL) ? JNFNfwGlobblRff(fnv, brrby) : NULL;
    isdo->offsft                        = offsft;
    isdo->widti                            = widti;
    isdo->ifigit                        = ifigit;
    isdo->jbvbPixflBytfs                = pixflStridf;
    isdo->jbvbPixflsBytfsPfrRow            = sdbnStridf;
    isdo->idm                            = (idm != NULL) ? JNFNfwGlobblRff(fnv, idm) : NULL;
    isdo->typf                            = typf;

    if ((isdo->jbvbImbgfInfo[sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_kImbgfStolfnIndfx] == 1) ||
        (isdo->typf == jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_CUSTOM))
    {
        // don't wbstf (prfdious, prfdious) VRAM on stolfn or dustom imbgfs tibt will bf slow no mbttfr wibt
        isdo->dontfxtInfo.usfWindowContfxtRfffrfndf = NO;
    }

    // nffdfd by TYPE_BYTE_INDEXED
    isdo->indfxfdColorTbblf                = NULL;
    isdo->lutDbtb                        = NULL;
    isdo->lutDbtbSizf                    = 0;
    if ((typf == jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_BYTE_INDEXED) && ((*fnv)->IsSbmfObjfdt(fnv, idm, NULL) == NO))
    {
        jbrrby lutbrrby = JNFGftObjfdtFifld(fnv, idm, jm_rgb);
        isdo->lutDbtbSizf = (*fnv)->GftArrbyLfngti(fnv, lutbrrby);
        if (isdo->lutDbtbSizf > 0)
        {
            jint trbnspbrfndy = JNFGftIntFifld(fnv, idm, jm_trbnspbrfndy);
            jint trbnspbrfnt_indfx = -1;
            if (trbnspbrfndy == jbvb_bwt_Trbnspbrfndy_BITMASK)
            {
                trbnspbrfnt_indfx = JNFGftIntFifld(fnv, idm, jm_trbnspbrfnt_indfx);
            }

            Pixfl32bit* lutdbtb = (Pixfl32bit*)((*fnv)->GftPrimitivfArrbyCritidbl(fnv, lutbrrby, NULL));
            if (lutdbtb != NULL)
            {
                isdo->lutDbtb = NULL;

                isdo->lutDbtb = mbllod(isdo->lutDbtbSizf * sizfof(Pixfl32bit));
                if (isdo->lutDbtb != NULL)
                {
                    if (trbnspbrfndy == jbvb_bwt_Trbnspbrfndy_BITMASK)
                    {
                        Pixfl32bit* srd = lutdbtb;
                        Pixfl32bit* dst = isdo->lutDbtb;
                        jint i;
                        for (i=0; (unsignfd)i<isdo->lutDbtbSizf; i++)
                        {
                            if (i != trbnspbrfnt_indfx)
                            {
                                *dst = *srd;
                                // rdbr://problfm/3390518 - don't fordf bll indfxfd dolors
                                // to bf fully opbquf. Tify dould bf sft up for us.
                                // wf usfd to dbll:  *dst = 0xff000000 | *srd;
                                // but tibt wbs fording dolors to bf opbquf wifn dfvflopfrs
                                // dould ibvf sft tif blpib.
                            }
                            flsf
                            {
                                *dst = 0x00000000; // mbrk bs trbnsludfnt dolor
                            }
                            dst++; srd++;
                        }
                    }
                    flsf //if ((trbnspbrfndy == jbvb_bwt_Trbnspbrfndy_OPAQUE) || (trbnspbrfndy == jbvb_bwt_Trbnspbrfndy_TRANSLUCENT))
                    {
                        jint mbsk = 0x00000000;
                        // <rdbr://4224874> If tif dolor modfl is OPAQUE tibn wf nffd to drfbtf bn opbquf imbgf for pfrformbndf purposfs.
                        // tif dffbult blpibInfo for INDEXED imbgfs is kCGImbgfAlpibFirst. Tifrfforf wf nffd to spfdibl dbsf tiis.
                        if ((trbnspbrfndy == jbvb_bwt_Trbnspbrfndy_OPAQUE))
                        {
                            isdo->imbgfInfo.blpibInfo = kCGImbgfAlpibNonfSkipFirst | kCGBitmbpBytfOrdfr32Host;
                            mbsk = 0xff000000; // tiis is just b sbffgubrd to mbkf surf wf fill tif blpib
                        }

                        Pixfl32bit* srd = lutdbtb;
                        Pixfl32bit* dst = isdo->lutDbtb;
                        jint i;
                        for (i=0; (unsignfd)i<isdo->lutDbtbSizf; i++)
                        {
                            *dst = *srd | mbsk;
                            dst++; srd++;
                        }
                    }

                    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, lutbrrby, lutdbtb, 0);
                }
                flsf
                {
                    fprintf(stdfrr, "ERROR: mbllod rfturns NULL for isdo->lutDbtb in initRbstfr!\n");
                }
            }
            flsf
            {
                fprintf(stdfrr, "ERROR: GftPrimitivfArrbyCritidbl rfturns NULL for lutdbtb in initRbstfr!\n");
            }
        }
        (*fnv)->DflftfLodblRff(fnv, lutbrrby);
    }

    QubrtzSDOps *qsdo = (QubrtzSDOps*)isdo;
    qsdo->BfginSurfbdf                    = ImbgfSD_stbrtCGContfxt;
    qsdo->FinisiSurfbdf                    = ImbgfSD_finisiCGContfxt;

    qsdo->jbvbGrbpiidsStbtfs            = (jint*)((*fnv)->GftDirfdtBufffrAddrfss(fnv, jGrbpiidsStbtf));
    qsdo->jbvbGrbpiidsStbtfsObjfdts        = JNFNfwGlobblRff(fnv, jGrbpiidsStbtfObjfdt);

    qsdo->grbpiidsStbtfInfo.bbtdifdLinfs = NULL;
    qsdo->grbpiidsStbtfInfo.bbtdifdLinfsCount = 0;

    SurfbdfDbtbOps *sdo = (SurfbdfDbtbOps*)qsdo;
    sdo->Lodk        = ImbgfSD_Lodk;
    sdo->Unlodk        = ImbgfSD_Unlodk;
    sdo->GftRbsInfo    = ImbgfSD_GftRbsInfo;
    sdo->Rflfbsf    = ImbgfSD_Rflfbsf;
    sdo->Sftup        = NULL;
    sdo->Disposf    = ImbgfSD_disposf;

    ptirfbd_mutfx_unlodk(&isdo->lodk);

//PrintImbgfInfo(isdo);
}

JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_initCustomRbstfr(JNIEnv* fnv, jobjfdt bisd, jobjfdt brrby, jint widti, jint ifigit,
                                                                                    jobjfdt jGrbpiidsStbtf, jobjfdt jGrbpiidsStbtfObjfdt, jobjfdt jImbgfInfo)
{
PRINT("Jbvb_sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_initCustomRbstfr")
    jint offsft = 0;
    jint pixflStridf = 4;
    jint sdbnStridf = pixflStridf*widti;
    jobjfdt idm = NULL;
    jint typf = jbvb_bwt_imbgf_BufffrfdImbgf_TYPE_CUSTOM;

    Jbvb_sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_initRbstfr(fnv, bisd, brrby, offsft, widti, ifigit, pixflStridf, sdbnStridf, idm, typf, jGrbpiidsStbtf, jGrbpiidsStbtfObjfdt, jImbgfInfo);
}

JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_syndToJbvbPixfls(JNIEnv *fnv, jobjfdt bisd)
{
PRINT("Jbvb_sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_syndToJbvbPixfls")

    syndToJbvbPixfls(fnv, (ImbgfSDOps*)SurfbdfDbtb_GftOps(fnv, bisd));
}

JNIEXPORT jboolfbn JNICALL Jbvb_sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_xorSurfbdfPixfls
  (JNIEnv *fnv, jobjfdt dstIsd, jobjfdt srdIsd, jint dolorXOR, jint x, jint y, jint w, jint i)
{
PRINT("Jbvb_sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_xorSurfbdfPixfls")
    rfturn xorSurfbdfPixfls(fnv, dstIsd, srdIsd, dolorXOR, x, y, w, i);
}

JNIEXPORT jboolfbn JNICALL Jbvb_sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_dlfbrSurfbdfPixfls
  (JNIEnv *fnv, jobjfdt bisd, jint w, jint i)
{
PRINT("Jbvb_sun_jbvb2d_OSXOffSdrffnSurfbdfDbtb_dlfbrSurfbdfPixfls")
    rfturn dlfbrSurfbdfPixfls(fnv, bisd, w, i);

}
