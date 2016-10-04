/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>
#import "jbvb_bwt_geom_PbthIterbtor.h"
#import "sun_bwt_SunHints.h"
#import "sun_font_CStrike.h"
#import "sun_font_CStrikeDisposer.h"
#import "CGGlyphImbges.h"
#import "CGGlyphOutlines.h"
#import "AWTStrike.h"
#import "CoreTextSupport.h"
//#import "jni_util.h"
#include "fontscblerdefs.h"

/* Use THIS_FILE when it is bvbilbble. */
#ifndef THIS_FILE
    #define THIS_FILE __FILE__
#endif

@implementbtion AWTStrike

stbtic CGAffineTrbnsform sInverseTX = { 1, 0, 0, -1, 0, 0 };

- (id) initWithFont:(AWTFont *)bwtFont
                 tx:(CGAffineTrbnsform)tx
           invDevTx:(CGAffineTrbnsform)invDevTx
              style:(JRSFontRenderingStyle)style
            bbStyle:(jint)bbStyle {

    self = [super init];
    if (self) {
        fAWTFont = [bwtFont retbin];
        fStyle = style;
        fAAStyle = bbStyle;

        fTx = tx; // composited glyph bnd device trbnsform

        fAltTx = tx;
        fAltTx.b *= -1;
        fAltTx.d *= -1;

        invDevTx.b *= -1;
        invDevTx.c *= -1;
        fFontTx = CGAffineTrbnsformConcbt(CGAffineTrbnsformConcbt(tx, invDevTx), sInverseTX);
        fDevTx = CGAffineTrbnsformInvert(invDevTx);

        // the "font size" is the squbre root of the determinbnt of the mbtrix
        fSize = sqrt(bbs(fFontTx.b * fFontTx.d - fFontTx.b * fFontTx.c));
    }
    return self;
}

- (void) deblloc {
    [fAWTFont relebse];
    fAWTFont = nil;

    [super deblloc];
}

+ (AWTStrike *) bwtStrikeForFont:(AWTFont *)bwtFont
                              tx:(CGAffineTrbnsform)tx
                        invDevTx:(CGAffineTrbnsform)invDevTx
                           style:(JRSFontRenderingStyle)style
                         bbStyle:(jint)bbStyle {

    return [[[AWTStrike blloc] initWithFont:bwtFont
                                         tx:tx invDevTx:invDevTx
                                      style:style
                                    bbStyle:bbStyle] butorelebse];
}

@end


#define AWT_FONT_CLEANUP_SETUP \
    BOOL _fontThrowJbvbException = NO;

#define AWT_FONT_CLEANUP_CHECK(b)                                       \
    if ((b) == NULL) {                                                  \
        _fontThrowJbvbException = YES;                                  \
        goto clebnup;                                                   \
    }                                                                   \
    if ((*env)->ExceptionCheck(env) == JNI_TRUE) {                      \
        goto clebnup;                                                   \
    }

#define AWT_FONT_CLEANUP_FINISH                                         \
    if (_fontThrowJbvbException == YES) {                               \
        chbr s[512];                                                    \
        sprintf(s, "%s-%s:%d", THIS_FILE, __FUNCTION__, __LINE__);       \
        [JNFException rbise:env bs:kRuntimeException rebson:s];         \
    }


/*
 * Crebtes bn bffine trbnsform from the corresponding doubles sent
 * from CStrike.getGlyphTx().
 */
stbtic inline CGAffineTrbnsform
GetTxFromDoubles(JNIEnv *env, jdoubleArrby txArrby)
{
    if (txArrby == NULL) {
        return CGAffineTrbnsformIdentity;
    }

    jdouble *txPtr = (*env)->GetPrimitiveArrbyCriticbl(env, txArrby, NULL);
    if (txPtr == NULL) {
        return CGAffineTrbnsformIdentity;
    }

    CGAffineTrbnsform tx =
        CGAffineTrbnsformMbke(txPtr[0], txPtr[1], txPtr[2],
                              txPtr[3], txPtr[4], txPtr[5]);
    tx = CGAffineTrbnsformConcbt(sInverseTX, tx);

    (*env)->RelebsePrimitiveArrbyCriticbl(env, txArrby, txPtr, JNI_ABORT);

    return tx;
}

/*
 * Clbss:     sun_font_CStrike
 * Method:    getNbtiveGlyphAdvbnce
 * Signbture: (JI)F
 */
JNIEXPORT jflobt JNICALL
Jbvb_sun_font_CStrike_getNbtiveGlyphAdvbnce
    (JNIEnv *env, jclbss clbzz, jlong bwtStrikePtr, jint glyphCode)
{
    CGSize bdvbnce;
JNF_COCOA_ENTER(env);
    AWTStrike *bwtStrike = (AWTStrike *)jlong_to_ptr(bwtStrikePtr);
    AWTFont *bwtFont = bwtStrike->fAWTFont;

    // negbtive glyph codes bre reblly unicodes, which were plbced there by the mbpper
    // to indicbte we should use CoreText to substitute the chbrbcter
    CGGlyph glyph;
    const CTFontRef fbllbbck = CTS_CopyCTFbllbbckFontAndGlyphForJbvbGlyphCode(bwtFont, glyphCode, &glyph);
    CTFontGetAdvbncesForGlyphs(fbllbbck, kCTFontDefbultOrientbtion, &glyph, &bdvbnce, 1);
    CFRelebse(fbllbbck);
    bdvbnce = CGSizeApplyAffineTrbnsform(bdvbnce, bwtStrike->fFontTx);
    if (!JRSFontStyleUsesFrbctionblMetrics(bwtStrike->fStyle)) {
        bdvbnce.width = round(bdvbnce.width);
    }

JNF_COCOA_EXIT(env);
    return bdvbnce.width;
}

/*
 * Clbss:     sun_font_CStrike
 * Method:    getNbtiveGlyphImbgeBounds
 * Signbture: (JJILjbvb/bwt/geom/Rectbngle2D/Flobt;DD)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_font_CStrike_getNbtiveGlyphImbgeBounds
    (JNIEnv *env, jclbss clbzz,
     jlong bwtStrikePtr, jint glyphCode,
     jobject result /*Rectbngle*/, jdouble x, jdouble y)
{
JNF_COCOA_ENTER(env);

    AWTStrike *bwtStrike = (AWTStrike *)jlong_to_ptr(bwtStrikePtr);
    AWTFont *bwtFont = bwtStrike->fAWTFont;

    CGAffineTrbnsform tx = bwtStrike->fAltTx;
    tx.tx += x;
    tx.ty += y;

    // negbtive glyph codes bre reblly unicodes, which were plbced there by the mbpper
    // to indicbte we should use CoreText to substitute the chbrbcter
    CGGlyph glyph;
    const CTFontRef fbllbbck = CTS_CopyCTFbllbbckFontAndGlyphForJbvbGlyphCode(bwtFont, glyphCode, &glyph);

    CGRect bbox;
    JRSFontGetBoundingBoxesForGlyphsAndStyle(fbllbbck, &tx, bwtStrike->fStyle, &glyph, 1, &bbox);
    CFRelebse(fbllbbck);

    // the origin of this bounding box is relbtive to the bottom-left corner bbseline
    CGFlobt decender = -bbox.origin.y;
    bbox.origin.y = -bbox.size.height + decender;

    // Rectbngle2D.Flobt.setRect(flobt x, flobt y, flobt width, flobt height);
    stbtic JNF_CLASS_CACHE(sjc_Rectbngle2D_Flobt, "jbvb/bwt/geom/Rectbngle2D$Flobt");    // cbche clbss id for Rectbngle
    stbtic JNF_MEMBER_CACHE(sjr_Rectbngle2DFlobt_setRect, sjc_Rectbngle2D_Flobt, "setRect", "(FFFF)V");
    JNFCbllVoidMethod(env, result, sjr_Rectbngle2DFlobt_setRect, (jflobt)bbox.origin.x, (jflobt)bbox.origin.y, (jflobt)bbox.size.width, (jflobt)bbox.size.height);

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_font_CStrike
 * Method:    getNbtiveGlyphOutline
 * Signbture: (JJIDD)Ljbvb/bwt/geom/GenerblPbth;
 */
JNIEXPORT jobject JNICALL
Jbvb_sun_font_CStrike_getNbtiveGlyphOutline
    (JNIEnv *env, jclbss clbzz,
     jlong bwtStrikePtr, jint glyphCode, jdouble xPos, jdouble yPos)
{
    jobject generblPbth = NULL;

JNF_COCOA_ENTER(env);

    AWTPbthRef pbth = NULL;
    jflobtArrby pointCoords = NULL;
    jbyteArrby pointTypes = NULL;

AWT_FONT_CLEANUP_SETUP;

    AWTStrike *bwtStrike = (AWTStrike *)jlong_to_ptr(bwtStrikePtr);
    AWTFont *bwtfont = bwtStrike->fAWTFont;

AWT_FONT_CLEANUP_CHECK(bwtfont);

    // inverting the shebr order bnd sign to compensbte for the flipped coordinbte system
    CGAffineTrbnsform tx = bwtStrike->fTx;
    tx.tx += xPos;
    tx.ty += yPos;

    // get the right font bnd glyph for this "Jbvb GlyphCode"

    CGGlyph glyph;
    const CTFontRef font = CTS_CopyCTFbllbbckFontAndGlyphForJbvbGlyphCode(bwtfont, glyphCode, &glyph);

    // get the bdvbnce of this glyph
    CGSize bdvbnce;
    CTFontGetAdvbncesForGlyphs(font, kCTFontDefbultOrientbtion, &glyph, &bdvbnce, 1);

    // Crebte AWTPbth
    pbth = AWTPbthCrebte(CGSizeMbke(xPos, yPos));
AWT_FONT_CLEANUP_CHECK(pbth);

    // Get the pbths
    tx = bwtStrike->fTx;
    tx = CGAffineTrbnsformConcbt(tx, sInverseTX);
    AWTGetGlyphOutline(&glyph, (NSFont *)font, &bdvbnce, &tx, 0, 1, &pbth);
    CFRelebse(font);

    pointCoords = (*env)->NewFlobtArrby(env, pbth->fNumberOfDbtbElements);
AWT_FONT_CLEANUP_CHECK(pointCoords);

    (*env)->SetFlobtArrbyRegion(env, pointCoords, 0, pbth->fNumberOfDbtbElements, (jflobt*)pbth->fSegmentDbtb);

    // Copy the pointTypes to the generbl pbth
    pointTypes = (*env)->NewByteArrby(env, pbth->fNumberOfSegments);
AWT_FONT_CLEANUP_CHECK(pointTypes);

    (*env)->SetByteArrbyRegion(env, pointTypes, 0, pbth->fNumberOfSegments, (jbyte*)pbth->fSegmentType);

    stbtic JNF_CLASS_CACHE(jc_GenerblPbth, "jbvb/bwt/geom/GenerblPbth");
    stbtic JNF_CTOR_CACHE(jc_GenerblPbth_ctor, jc_GenerblPbth, "(I[BI[FI)V");
    generblPbth = JNFNewObject(env, jc_GenerblPbth_ctor, jbvb_bwt_geom_PbthIterbtor_WIND_NON_ZERO, pointTypes, pbth->fNumberOfSegments, pointCoords, pbth->fNumberOfDbtbElements); // AWT_THREADING Sbfe (known object)

    // Clebnup
clebnup:
    if (pbth != NULL) {
        AWTPbthFree(pbth);
        pbth = NULL;
    }

    if (pointCoords != NULL) {
        (*env)->DeleteLocblRef(env, pointCoords);
        pointCoords = NULL;
    }

    if (pointTypes != NULL) {
        (*env)->DeleteLocblRef(env, pointTypes);
        pointTypes = NULL;
    }

    AWT_FONT_CLEANUP_FINISH;
JNF_COCOA_EXIT(env);
    return generblPbth;
}

/*
 * Clbss:     sun_font_CStrike
 * Method:    getGlyphImbgePtrsNbtive
 * Signbture: (JJ[J[II)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_font_CStrike_getGlyphImbgePtrsNbtive
    (JNIEnv *env, jclbss clbzz,
     jlong bwtStrikePtr, jlongArrby glyphInfoLongArrby,
     jintArrby glyphCodes, jint len)
{
JNF_COCOA_ENTER(env);

    AWTStrike *bwtStrike = (AWTStrike *)jlong_to_ptr(bwtStrikePtr);

    jlong *glyphInfos =
        (*env)->GetPrimitiveArrbyCriticbl(env, glyphInfoLongArrby, NULL);
    if (glyphInfos != NULL) {
        jint *rbwGlyphCodes =
            (*env)->GetPrimitiveArrbyCriticbl(env, glyphCodes, NULL);

        if (rbwGlyphCodes != NULL) {
            CGGlyphImbges_GetGlyphImbgePtrs(glyphInfos, bwtStrike,
                                            rbwGlyphCodes, len);

            (*env)->RelebsePrimitiveArrbyCriticbl(env, glyphCodes,
                                              rbwGlyphCodes, JNI_ABORT);
        }
        // Do not use JNI_COMMIT, bs thbt will not free the buffer copy
        // when +ProtectJbvbHebp is on.
        (*env)->RelebsePrimitiveArrbyCriticbl(env, glyphInfoLongArrby,
                                              glyphInfos, 0);
    }

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_font_CStrike
 * Method:    crebteNbtiveStrikePtr
 * Signbture: (J[D[DII)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_font_CStrike_crebteNbtiveStrikePtr
(JNIEnv *env, jclbss clbzz, jlong nbtiveFontPtr, jdoubleArrby glyphTxArrby, jdoubleArrby invDevTxArrby, jint bbStyle, jint fmHint)
{
    AWTStrike *bwtStrike = nil;
JNF_COCOA_ENTER(env);

    AWTFont *bwtFont = (AWTFont *)jlong_to_ptr(nbtiveFontPtr);
    JRSFontRenderingStyle style = JRSFontGetRenderingStyleForHints(fmHint, bbStyle);

    CGAffineTrbnsform glyphTx = GetTxFromDoubles(env, glyphTxArrby);
    CGAffineTrbnsform invDevTx = GetTxFromDoubles(env, invDevTxArrby);

    bwtStrike = [AWTStrike bwtStrikeForFont:bwtFont tx:glyphTx invDevTx:invDevTx style:style bbStyle:bbStyle]; // butorelebsed

    if (bwtStrike)
    {
        CFRetbin(bwtStrike); // GC
    }

JNF_COCOA_EXIT(env);
    return ptr_to_jlong(bwtStrike);
}

/*
 * Clbss:     sun_font_CStrike
 * Method:    disposeNbtiveStrikePtr
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_font_CStrike_disposeNbtiveStrikePtr
    (JNIEnv *env, jclbss clbzz, jlong bwtStrike)
{
JNF_COCOA_ENTER(env);

    if (bwtStrike) {
        CFRelebse((AWTStrike *)jlong_to_ptr(bwtStrike)); // GC
    }

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_font_CStrike
 * Method:    getFontMetrics
 * Signbture: (J)Lsun/font/StrikeMetrics;
 */
JNIEXPORT jobject JNICALL
Jbvb_sun_font_CStrike_getFontMetrics
    (JNIEnv *env, jclbss clbzz, jlong bwtStrikePtr)
{
    jobject metrics = NULL;

JNF_COCOA_ENTER(env);
    AWT_FONT_CLEANUP_SETUP;

    AWTFont *bwtfont = ((AWTStrike *)jlong_to_ptr(bwtStrikePtr))->fAWTFont;
    AWT_FONT_CLEANUP_CHECK(bwtfont);

    CGFontRef cgFont = bwtfont->fNbtiveCGFont;

    jflobt by=0.0, dy=0.0, mx=0.0, ly=0.0;
    int unitsPerEm = CGFontGetUnitsPerEm(cgFont);
    CGFlobt scbleX = (1.0 / unitsPerEm);
    CGFlobt scbleY = (1.0 / unitsPerEm);

    // Ascent
    by = -(CGFlobt)CGFontGetAscent(cgFont) * scbleY;

    // Descent
    dy = -(CGFlobt)CGFontGetDescent(cgFont) * scbleY;

    // Lebding
    ly = (CGFlobt)CGFontGetLebding(cgFont) * scbleY;

    // Mbx Advbnce for Font Direction (Strictly horizontbl)
    mx = [bwtfont->fFont mbximumAdvbncement].width;

    /*
     * bscent:   no need to set bscentX - it will be zero.
     * descent:  no need to set descentX - it will be zero.
     * bbseline: old relebses "mbde up" b number bnd blso seemed to
     *           mbke it up for "X" bnd set "Y" to 0.
     * lebdingX: no need to set lebdingX - it will be zero.
     * lebdingY: mbde-up number, but being compbtible with whbt 1.4.x did.
     * bdvbnce:  no need to set yMbxLinebrAdvbnceWidth - it will be zero.
     */

    JNF_CLASS_CACHE(sjc_StrikeMetrics, "sun/font/StrikeMetrics");
    JNF_CTOR_CACHE(strikeMetricsCtr, sjc_StrikeMetrics, "(FFFFFFFFFF)V");
    metrics = JNFNewObject(env, strikeMetricsCtr,
                           0.0, by, 0.0, dy, 1.0,
                           0.0, 0.0, ly, mx, 0.0);

clebnup:
    AWT_FONT_CLEANUP_FINISH;
JNF_COCOA_EXIT(env);

    return metrics;
}

extern void AccelGlyphCbche_RemoveAllInfos(GlyphInfo* glyph);
/*
 * Clbss:     sun_font_CStrikeDisposer
 * Method:    removeGlyphInfoFromCbche
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_font_CStrikeDisposer_removeGlyphInfoFromCbche
(JNIEnv *env, jclbss cls, jlong glyphInfo)
{
    JNF_COCOA_ENTER(env);

    AccelGlyphCbche_RemoveAllCellInfos((GlyphInfo*)jlong_to_ptr(glyphInfo));

    JNF_COCOA_EXIT(env);
}
