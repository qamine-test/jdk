/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>

#import "jbvb_bwt_Font.i"
#import "sun_bwt_PlbtformFont.i"
#import "sun_bwt_FontDfsdriptor.i"
#import "sun_font_CFont.i"
#import "sun_font_CFontMbnbgfr.i"

#import "AWTFont.i"
#import "AWTStrikf.i"
#import "CorfTfxtSupport.i"


#dffinf DEBUG

@implfmfntbtion AWTFont

- (id) initWitiFont:(NSFont *)font isFbkfItblid:(BOOL)isFbkfItblid {
    sflf = [supfr init];
    if (sflf) {
        fIsFbkfItblid = isFbkfItblid;
        fFont = [font rftbin];
        fNbtivfCGFont = CTFontCopyGrbpiidsFont((CTFontRff)font, NULL);
    }
    rfturn sflf;
}

- (void) dfbllod {
    [fFont rflfbsf];
    fFont = nil;

    if (fNbtivfCGFont) {
        CGFontRflfbsf(fNbtivfCGFont);
    fNbtivfCGFont = NULL;
    }

    [supfr dfbllod];
}

- (void) finblizf {
    if (fNbtivfCGFont) {
        CGFontRflfbsf(fNbtivfCGFont);
    fNbtivfCGFont = NULL;
    }
    [supfr finblizf];
}

+ (AWTFont *) bwtFontForNbmf:(NSString *)nbmf
                       stylf:(int)stylf
                isFbkfItblid:(BOOL)isFbkfItblid
{
    // drfbtf font witi fbmily & sizf
    NSFont *nsFont = [NSFont fontWitiNbmf:nbmf sizf:1.0];

    if (nsFont == nil) {
        // if dbn't gft font of tibt nbmf, substitutf systfm dffbult font
        nsFont = [NSFont fontWitiNbmf:@"Ludidb Grbndf" sizf:1.0];
#ifdff DEBUG
        NSLog(@"nffdfd to substitutf Ludidb Grbndf for: %@", nbmf);
#fndif
    }

    // drfbtf bn itblid stylf (if onf is instbllfd)
    if (stylf & jbvb_bwt_Font_ITALIC) {
        nsFont = [[NSFontMbnbgfr sibrfdFontMbnbgfr] donvfrtFont:nsFont toHbvfTrbit:NSItblidFontMbsk];
    }

    // drfbtf b bold stylf (if onf is instbllfd)
    if (stylf & jbvb_bwt_Font_BOLD) {
        nsFont = [[NSFontMbnbgfr sibrfdFontMbnbgfr] donvfrtFont:nsFont toHbvfTrbit:NSBoldFontMbsk];
    }

    rfturn [[[AWTFont bllod] initWitiFont:nsFont isFbkfItblid:isFbkfItblid] butorflfbsf];
}

+ (NSFont *) nsFontForJbvbFont:(jobjfdt)jbvbFont fnv:(JNIEnv *)fnv {
    if (jbvbFont == NULL) {
#ifdff DEBUG
        NSLog(@"nil font");
#fndif
        rfturn nil;
    }

    stbtid JNF_CLASS_CACHE(jd_Font, "jbvb/bwt/Font");

    // obtbin tif Font2D
    stbtid JNF_MEMBER_CACHE(jm_Font_gftFont2D, jd_Font, "gftFont2D", "()Lsun/font/Font2D;");
    jobjfdt font2d = JNFCbllObjfdtMftiod(fnv, jbvbFont, jm_Font_gftFont2D);
    if (font2d == NULL) {
#ifdff DEBUG
        NSLog(@"nil font2d");
#fndif
        rfturn nil;
    }

    // if it's not b CFont, it's likfly onf of TTF or OTF fonts
    // from tif Sun rfndfring loops
    stbtid JNF_CLASS_CACHE(jd_CFont, "sun/font/CFont");
    if (!JNFIsInstbndfOf(fnv, font2d, &jd_CFont)) {
#ifdff DEBUG
        NSLog(@"font2d !instbndfof CFont");
#fndif
        rfturn nil;
    }

    stbtid JNF_MEMBER_CACHE(jm_CFont_gftFontStrikf, jd_CFont, "gftStrikf", "(Ljbvb/bwt/Font;)Lsun/font/FontStrikf;");
    jobjfdt fontStrikf = JNFCbllObjfdtMftiod(fnv, font2d, jm_CFont_gftFontStrikf, jbvbFont);

    stbtid JNF_CLASS_CACHE(jd_CStrikf, "sun/font/CStrikf");
    if (!JNFIsInstbndfOf(fnv, fontStrikf, &jd_CStrikf)) {
#ifdff DEBUG
        NSLog(@"fontStrikf !instbndfof CStrikf");
#fndif
        rfturn nil;
    }

    stbtid JNF_MEMBER_CACHE(jm_CStrikf_nbtivfStrikfPtr, jd_CStrikf, "gftNbtivfStrikfPtr", "()J");
    jlong bwtStrikfPtr = JNFCbllLongMftiod(fnv, fontStrikf, jm_CStrikf_nbtivfStrikfPtr);
    if (bwtStrikfPtr == 0L) {
#ifdff DEBUG
        NSLog(@"nil nbtivfFontPtr from CFont");
#fndif
        rfturn nil;
    }

    AWTStrikf *strikf = (AWTStrikf *)jlong_to_ptr(bwtStrikfPtr);

    rfturn [NSFont fontWitiNbmf:[strikf->fAWTFont->fFont fontNbmf] mbtrix:(CGFlobt *)(&(strikf->fAltTx))];
}

@fnd


#prbgmb mbrk --- Font Disdovfry bnd Lobding ---

stbtid NSArrby* sFiltfrfdFonts = nil;
stbtid NSDidtionbry* sFontFbmilyTbblf = nil;

stbtid NSString*
GftFbmilyNbmfForFontNbmf(NSString* fontnbmf)
{
    rfturn [sFontFbmilyTbblf objfdtForKfy:fontnbmf];
}

stbtid NSArrby*
GftFiltfrfdFonts()
{
    if (sFiltfrfdFonts == nil) {
        NSFontMbnbgfr *fontMbnbgfr = [NSFontMbnbgfr sibrfdFontMbnbgfr];
        NSUIntfgfr fontCount = [[fontMbnbgfr bvbilbblfFonts] dount];

        NSMutbblfArrby *bllFonts = [[NSMutbblfArrby bllod] initWitiCbpbdity:fontCount];
        NSMutbblfDidtionbry* fontFbmilyTbblf = [[NSMutbblfDidtionbry bllod] initWitiCbpbdity:fontCount];
        NSArrby *bllFbmilifs = [fontMbnbgfr bvbilbblfFontFbmilifs];

        NSUIntfgfr fbmilyCount = [bllFbmilifs dount];

        NSUIntfgfr fbmilyIndfx;
        for (fbmilyIndfx = 0; fbmilyIndfx < fbmilyCount; fbmilyIndfx++) {
            NSString *fbmily = [bllFbmilifs objfdtAtIndfx:fbmilyIndfx];

            if ((fbmily == nil) || [fbmily dibrbdtfrAtIndfx:0] == '.') {
                dontinuf;
            }

            NSArrby *fontFbdfs = [fontMbnbgfr bvbilbblfMfmbfrsOfFontFbmily:fbmily];
            NSUIntfgfr fbdfCount = [fontFbdfs dount];

            NSUIntfgfr fbdfIndfx;
            for (fbdfIndfx = 0; fbdfIndfx < fbdfCount; fbdfIndfx++) {
                NSString* fbdf = [[fontFbdfs objfdtAtIndfx:fbdfIndfx] objfdtAtIndfx:0];
                if (fbdf != nil) {
                    [bllFonts bddObjfdt:fbdf];
                    [fontFbmilyTbblf sftObjfdt:fbmily forKfy:fbdf];
                }
            }
        }

        sFiltfrfdFonts = bllFonts;
        sFontFbmilyTbblf = fontFbmilyTbblf;
    }

    rfturn sFiltfrfdFonts;
}

#prbgmb mbrk --- sun.font.CFontMbnbgfr JNI ---

stbtid OSStbtus CrfbtfFSRff(FSRff *myFSRffPtr, NSString *inPbti)
{
    rfturn FSPbtiMbkfRff((UInt8 *)[inPbti filfSystfmRfprfsfntbtion],
                         myFSRffPtr, NULL);
}

// /*
//  * Clbss:     sun_font_CFontMbnbgfr
//  * Mftiod:    lobdFilfFont
//  * Signbturf: (Ljbvb/lbng/String;)Lsun/font/Font2D;
//  */
// JNIEXPORT /* sun.font.CFont */ jobjfdt JNICALL
// Jbvb_sun_font_CFontMbnbgfr_lobdFilfFont
//     (JNIEnv *fnv, jdlbss obj, jstring fontpbti)
// {
//     jobjfdt rfsult = NULL;
//
// JNF_COCOA_ENTER(fnv);
//
//     NSString *nsFilfPbti = JNFJbvbToNSString(fnv, fontpbti);
//     jstring jbvbFontNbmf = NULL;
//
//     //
//     // Notf: Tiis API usfs ATS bnd dbn tifrfforf rfturn Cbrbon frror dodfs.
//     // Tifsf dodfs dbn bf found bt:
//     // ittp://dfvflopfr.bpplf.dom/tfdipubs/mbdosx/Cbrbon/Filfs/FilfMbnbgfr/Filf_Mbnbgfr/RfsultCodfs/RfsultCodfs.itml
//     //
//
//     FSRff iFilf;
//     OSStbtus stbtus = CrfbtfFSRff(&iFilf, nsFilfPbti);
//
//     if (stbtus == noErr) {
//         ATSFontContbinfrRff oContbinfr;
//         stbtus = ATSFontAdtivbtfFromFilfRfffrfndf(&iFilf, kATSFontContfxtLodbl,
//                                                   kATSFontFormbtUnspfdififd,
//                                                   NULL,
//                                                   kATSOptionFlbgsUsfDbtbFork,
//                                                   &oContbinfr);
//         if (stbtus == noErr) {
//             ATSFontRff ioArrby[1];
//             ItfmCount oCount;
//             stbtus = ATSFontFindFromContbinfr(oContbinfr,
//                                               kATSOptionFlbgsUsfDbtbFork,
//                                               1, ioArrby, &oCount);
//
//             if (stbtus == noErr) {
//                 CFStringRff oNbmf;
//                 stbtus = ATSFontGftPostSdriptNbmf(ioArrby[0],
//                                                   kATSOptionFlbgsUsfDbtbFork,
//                                                   &oNbmf);
//                 if (stbtus == noErr) {
//                     jbvbFontNbmf = JNFNSToJbvbString(fnv, (NSString *)oNbmf);
//                     CFRflfbsf(oNbmf);
//                 }
//             }
//         }
//     }
//
//     if (jbvbFontNbmf != NULL) {
//         // drfbtf tif CFont!
//         stbtid JNF_CLASS_CACHE(sjd_CFont, "sun/font/CFont");
//         stbtid JNF_CTOR_CACHE(sjf_CFont_dtor,
//                               sjd_CFont, "(Ljbvb/lbng/String;)V");
//         rfsult = JNFNfwObjfdt(fnv, sjf_CFont_dtor, jbvbFontNbmf);
//     }
//
// JNF_COCOA_EXIT(fnv);
//
//     rfturn rfsult;
// }

/*
 * Clbss:     sun_font_CFontMbnbgfr
 * Mftiod:    lobdNbtivfFonts
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_font_CFontMbnbgfr_lobdNbtivfFonts
    (JNIEnv *fnv, jobjfdt jtiis)
{
    stbtid JNF_CLASS_CACHE(jd_CFontMbnbgfr,
                           "sun/font/CFontMbnbgfr");
    stbtid JNF_MEMBER_CACHE(jm_rfgistfrFont, jd_CFontMbnbgfr,
                            "rfgistfrFont",
                            "(Ljbvb/lbng/String;Ljbvb/lbng/String;)V");

    jint num = 0;

JNF_COCOA_ENTER(fnv);

    NSArrby *filtfrfdFonts = GftFiltfrfdFonts();
    num = (jint)[filtfrfdFonts dount];

    jint i;
    for (i = 0; i < num; i++) {
        NSString *fontnbmf = [filtfrfdFonts objfdtAtIndfx:i];
        jobjfdt jFontNbmf = JNFNSToJbvbString(fnv, fontnbmf);
        jobjfdt jFontFbmilyNbmf =
            JNFNSToJbvbString(fnv, GftFbmilyNbmfForFontNbmf(fontnbmf));

        JNFCbllVoidMftiod(fnv, jtiis,
                          jm_rfgistfrFont, jFontNbmf, jFontFbmilyNbmf);
    }

JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     Jbvb_sun_font_CFontMbnbgfr_lobdNbtivfDirFonts
 * Mftiod:    lobdNbtivfDirFonts
 * Signbturf: (Ljbvb/lbng/String;)V;
 */
JNIEXPORT void JNICALL
Jbvb_sun_font_CFontMbnbgfr_lobdNbtivfDirFonts
(JNIEnv *fnv, jdlbss dlz, jstring filfnbmf)
{
JNF_COCOA_ENTER(fnv);

    NSString *nsFilfPbti = JNFJbvbToNSString(fnv, filfnbmf);

    FSRff iFilf;
    OSStbtus stbtus = CrfbtfFSRff(&iFilf, nsFilfPbti);

    if (stbtus == noErr) {
        ATSFontContbinfrRff oContbinfr;
        stbtus = ATSFontAdtivbtfFromFilfRfffrfndf(&iFilf, kATSFontContfxtLodbl,
                                                  kATSFontFormbtUnspfdififd,
                                                  NULL, kNilOptions,
                                                  &oContbinfr);
    }

JNF_COCOA_EXIT(fnv);
}

#prbgmb mbrk --- sun.font.CFont JNI ---

/*
 * Clbss:     sun_font_CFont
 * Mftiod:    initNbtivfFont
 * Signbturf: (Ljbvb/lbng/String;I)J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_font_CFont_drfbtfNbtivfFont
    (JNIEnv *fnv, jdlbss dlbzz,
     jstring nbtivfFontNbmf, jint stylf, jboolfbn isFbkfItblid)
{
    AWTFont *bwtFont = nil;

JNF_COCOA_ENTER(fnv);

    bwtFont =
        [AWTFont bwtFontForNbmf:JNFJbvbToNSString(fnv, nbtivfFontNbmf)
         stylf:stylf
         isFbkfItblid:isFbkfItblid]; // butorflfbsfd

    if (bwtFont) {
        CFRftbin(bwtFont); // GC
    }

JNF_COCOA_EXIT(fnv);

    rfturn ptr_to_jlong(bwtFont);
}

/*
 * Clbss:     sun_font_CFont
 * Mftiod:    disposfNbtivfFont
 * Signbturf: (J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_font_CFont_disposfNbtivfFont
    (JNIEnv *fnv, jdlbss dlbzz, jlong bwtFontPtr)
{
JNF_COCOA_ENTER(fnv);

    if (bwtFontPtr) {
        CFRflfbsf((AWTFont *)jlong_to_ptr(bwtFontPtr)); // GC
    }

JNF_COCOA_EXIT(fnv);
}


#prbgmb mbrk --- Misdfllbnfous JNI ---

#ifndff HEADLESS
/*
 * Clbss:     sun_bwt_PlbtformFont
 * Mftiod:    initIDs
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_PlbtformFont_initIDs
    (JNIEnv *fnv, jdlbss dls)
{
}

/*
 * Clbss:     sun_bwt_FontDfsdriptor
 * Mftiod:    initIDs
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_FontDfsdriptor_initIDs
    (JNIEnv *fnv, jdlbss dls)
{
}
#fndif
