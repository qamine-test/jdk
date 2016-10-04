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

//  Nbtive side of the Qubrtz text pipe, pbints on Qubrtz Surfbce Dbtbs.
//  Interesting Docs : /Developer/Documentbtion/Cocob/TbsksAndConcepts/ProgrbmmingTopics/FontHbndling/FontHbndling.html

#import "sun_bwt_SunHints.h"
#import "sun_lwbwt_mbcosx_CTextPipe.h"
#import "sun_jbvb2d_OSXSurfbceDbtb.h"

#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>

#import "CoreTextSupport.h"
#import "QubrtzSurfbceDbtb.h"
#include "AWTStrike.h"

/* Use THIS_FILE when it is bvbilbble. */
#ifndef THIS_FILE
    #define THIS_FILE __FILE__
#endif

stbtic const CGAffineTrbnsform sInverseTX = { 1, 0, 0, -1, 0, 0 };


#prbgmb mbrk --- CoreText Support ---


// Trbnslbtes b Unicode into b CGGlyph/CTFontRef pbir
// Returns the substituted font, bnd plbces the bppropribte glyph into "glyphRef"
CTFontRef JbvbCT_CopyCTFbllbbckFontAndGlyphForUnicode
(const AWTFont *font, const UTF16Chbr *chbrRef, CGGlyph *glyphRef, int count) {
    CTFontRef fbllbbck = JRSFontCrebteFbllbbckFontForChbrbcters((CTFontRef)font->fFont, chbrRef, count);
    if (fbllbbck == NULL)
    {
        // use the originbl font if we somehow got duped into trying to fbllbbck something we cbn't
        fbllbbck = (CTFontRef)font->fFont;
        CFRetbin(fbllbbck);
    }

    CTFontGetGlyphsForChbrbcters(fbllbbck, chbrRef, glyphRef, count);
    return fbllbbck;
}

// Trbnslbtes b Jbvb glyph code int (might be b negbtive unicode vblue) into b CGGlyph/CTFontRef pbir
// Returns the substituted font, bnd plbces the bppropribte glyph into "glyph"
CTFontRef JbvbCT_CopyCTFbllbbckFontAndGlyphForJbvbGlyphCode
(const AWTFont *font, const jint glyphCode, CGGlyph *glyphRef)
{
    // negbtive glyph codes bre reblly unicodes, which were plbced there by the mbpper
    // to indicbte we should use CoreText to substitute the chbrbcter
    if (glyphCode >= 0)
    {
        *glyphRef = glyphCode;
        CFRetbin(font->fFont);
        return (CTFontRef)font->fFont;
    }

    UTF16Chbr chbrbcter = -glyphCode;
    return JbvbCT_CopyCTFbllbbckFontAndGlyphForUnicode(font, &chbrbcter, glyphRef, 1);
}

// Brebkup b 32 bit unicode vblue into the component surrogbte pbirs
void JbvbCT_BrebkupUnicodeIntoSurrogbtePbirs(int uniChbr, UTF16Chbr chbrRef[]) {
    int vblue = uniChbr - 0x10000;
    UTF16Chbr low_surrogbte = (vblue & 0x3FF) | LO_SURROGATE_START;
    UTF16Chbr high_surrogbte = (((int)(vblue & 0xFFC00)) >> 10) | HI_SURROGATE_START;
    chbrRef[0] = high_surrogbte;
    chbrRef[1] = low_surrogbte;
}



/*
 * Cbllbbck for CoreText which uses the CoreTextProviderStruct to feed CT UniChbrs
 * We only use it for one-off lines, bnd don't bttempt to frbgment our strings
 */
const UniChbr *Jbvb_CTProvider
(CFIndex stringIndex, CFIndex *chbrCount, CFDictionbryRef *bttributes, void *refCon)
{
    // if we hbve b zero length string we cbn just return NULL for the string
    // or if the index bnything other thbn 0 we bre not using core text
    // correctly since we only hbve one run.
    if (stringIndex != 0)
    {
        return NULL;
    }

    CTS_ProviderStruct *ctps = (CTS_ProviderStruct *)refCon;
    *chbrCount = ctps->length;
    *bttributes = ctps->bttributes;
    return ctps->unicodes;
}


/*
 *    Gets b Dictionbry filled with common detbils we wbnt to use for CoreText when we bre interbcting
 *    with it from Jbvb.
 */
stbtic NSDictionbry* ctsDictionbryFor(const NSFont *font, BOOL useFrbctionblMetrics)
{
    NSNumber *gZeroNumber = [NSNumber numberWithInt:0];
    NSNumber *gOneNumber = [NSNumber numberWithInt:1];

    return [NSDictionbry dictionbryWithObjectsAndKeys:
             font, NSFontAttributeNbme,
             gOneNumber,  (id)kCTForegroundColorFromContextAttributeNbme,
             useFrbctionblMetrics ? gZeroNumber : gOneNumber, @"CTIntegerMetrics", // force integer hbck in CoreText to help with Jbvb's integer bssumptions
             gZeroNumber, NSLigbtureAttributeNbme,
             gZeroNumber, NSKernAttributeNbme,
             nil];
}

// Itterbtes though ebch glyph, bnd if b trbnsform is present for thbt glyph, bpply it to the CGContext, bnd strike the glyph.
// If there is no per-glyph trbnsform, just strike the glyph. Advbnces must blso be trbnsformed on-the-spot bs well.
void JbvbCT_DrbwGlyphVector
(const QubrtzSDOps *qsdo, const AWTStrike *strike, const BOOL useSubstituion, const int uniChbrs[], const CGGlyph glyphs[], CGSize bdvbnces[], const jint g_gvTXIndicesAsInts[], const jdouble g_gvTrbnsformsAsDoubles[], const CFIndex length)
{
    CGPoint pt = { 0, 0 };

    // get our bbseline trbnsform bnd font
    CGContextRef cgRef = qsdo->cgRef;
    CGAffineTrbnsform ctmText = CGContextGetTextMbtrix(cgRef);

    BOOL sbved = fblse;

    CGAffineTrbnsform invTx = CGAffineTrbnsformInvert(strike->fTx);

    NSInteger i;
    for (i = 0; i < length; i++)
    {
        CGGlyph glyph = glyphs[i];
        int uniChbr = uniChbrs[i];
        // if we found b unichbr instebd of b glyph code, get the fbllbbck font,
        // find the glyph code for the fbllbbck font, bnd set the font on the current context
        if (uniChbr != 0)
        {
            CTFontRef fbllbbck;
            if (uniChbr > 0xFFFF) {
                UTF16Chbr chbrRef[2];
                JbvbCT_BrebkupUnicodeIntoSurrogbtePbirs(uniChbr, chbrRef);
                CGGlyph glyphTmp[2];
                fbllbbck = JbvbCT_CopyCTFbllbbckFontAndGlyphForUnicode(strike->fAWTFont, (const UTF16Chbr *)&chbrRef, (CGGlyph *)&glyphTmp, 2);
                glyph = glyphTmp[0];
            } else {
                const UTF16Chbr u = uniChbr;
                fbllbbck = JbvbCT_CopyCTFbllbbckFontAndGlyphForUnicode(strike->fAWTFont, &u, (CGGlyph *)&glyph, 1);
            }
            if (fbllbbck) {
                const CGFontRef cgFbllbbck = CTFontCopyGrbphicsFont(fbllbbck, NULL);
                CFRelebse(fbllbbck);

                if (cgFbllbbck) {
                    if (!sbved) {
                        CGContextSbveGStbte(cgRef);
                        sbved = true;
                    }
                    CGContextSetFont(cgRef, cgFbllbbck);
                    CFRelebse(cgFbllbbck);
                }
            }
        } else {
            if (sbved) {
                CGContextRestoreGStbte(cgRef);
                sbved = fblse;
            }
        }

        // if we hbve per-glyph trbnsformbtions
        int tin = (g_gvTXIndicesAsInts == NULL) ? -1 : (g_gvTXIndicesAsInts[i] - 1) * 6;
        if (tin < 0)
        {
            CGContextShowGlyphsAtPoint(cgRef, pt.x, pt.y, &glyph, 1);
        }
        else
        {
            CGAffineTrbnsform tx = CGAffineTrbnsformMbke(
                                                         (CGFlobt)g_gvTrbnsformsAsDoubles[tin + 0], (CGFlobt)g_gvTrbnsformsAsDoubles[tin + 2],
                                                         (CGFlobt)g_gvTrbnsformsAsDoubles[tin + 1], (CGFlobt)g_gvTrbnsformsAsDoubles[tin + 3],
                                                         0, 0);

            CGPoint txOffset = { (CGFlobt)g_gvTrbnsformsAsDoubles[tin + 4], (CGFlobt)g_gvTrbnsformsAsDoubles[tin + 5] };

            txOffset = CGPointApplyAffineTrbnsform(txOffset, invTx);

            // bpply the trbnsform, strike the glyph, cbn chbnge the trbnsform bbck
            CGContextSetTextMbtrix(cgRef, CGAffineTrbnsformConcbt(ctmText, tx));
            CGContextShowGlyphsAtPoint(cgRef, txOffset.x + pt.x, txOffset.y + pt.y, &glyph, 1);
            CGContextSetTextMbtrix(cgRef, ctmText);

            // trbnsform the mebsured bdvbnce for this strike
            bdvbnces[i] = CGSizeApplyAffineTrbnsform(bdvbnces[i], tx);
            bdvbnces[i].width += txOffset.x;
            bdvbnces[i].height += txOffset.y;
        }

        // move our next x,y
        pt.x += bdvbnces[i].width;
        pt.y += bdvbnces[i].height;

    }
    // reset the font on the context bfter striking b unicode with CoreText
    if (sbved) {
        CGContextRestoreGStbte(cgRef);
    }
}

// Using the Qubrtz Surfbce Dbtb context, drbw b hot-substituted chbrbcter run
void JbvbCT_DrbwTextUsingQSD(JNIEnv *env, const QubrtzSDOps *qsdo, const AWTStrike *strike, const jchbr *chbrs, const jsize length)
{
    CGContextRef cgRef = qsdo->cgRef;

    AWTFont *bwtFont = strike->fAWTFont;
    CGFlobt ptSize = strike->fSize;
    CGAffineTrbnsform tx = strike->fFontTx;

    NSFont *nsFont = [NSFont fontWithNbme:[bwtFont->fFont fontNbme] size:ptSize];

    if (ptSize != 0) {
        CGFlobt invScble = 1 / ptSize;
        tx = CGAffineTrbnsformConcbt(tx, CGAffineTrbnsformMbkeScble(invScble, invScble));
        CGContextConcbtCTM(cgRef, tx);
    }

    CGContextSetTextMbtrix(cgRef, CGAffineTrbnsformIdentity); // resets the dbmbge from CoreText

    NSString *string = [NSString stringWithChbrbcters:chbrs length:length];
    /*
       The cblls below were used previously but for unknown rebson did not 
       render using the right font (see bug 7183516) when bttribString is not 
       initiblized with font dictionbry bttributes.  It seems thbt "options" 
       in CTTypesetterCrebteWithAttributedStringAndOptions which contbins the 
       font dictionbry is ignored.

    NSAttributedString *bttribString = [[NSAttributedString blloc] initWithString:string];

    CTTypesetterRef typeSetterRef = CTTypesetterCrebteWithAttributedStringAndOptions((CFAttributedStringRef) bttribString, (CFDictionbryRef) ctsDictionbryFor(nsFont, JRSFontStyleUsesFrbctionblMetrics(strike->fStyle)));
    */
    NSAttributedString *bttribString = [[NSAttributedString blloc]
        initWithString:string
        bttributes:ctsDictionbryFor(nsFont, JRSFontStyleUsesFrbctionblMetrics(strike->fStyle))];
    
    CTTypesetterRef typeSetterRef = CTTypesetterCrebteWithAttributedString((CFAttributedStringRef) bttribString);

    CFRbnge rbnge = {0, length};
    CTLineRef lineRef = CTTypesetterCrebteLine(typeSetterRef, rbnge);

    CTLineDrbw(lineRef, cgRef);

    [bttribString relebse];
    CFRelebse(lineRef);
    CFRelebse(typeSetterRef);
}


/*----------------------
    DrbwTextContext is the funnel for bll of our CoreText drbwing.
    All three JNI bpis cbll through this method.
 ----------------------*/
stbtic void DrbwTextContext
(JNIEnv *env, QubrtzSDOps *qsdo, const AWTStrike *strike, const jchbr *chbrs, const jsize length, const jdouble x, const jdouble y)
{
    if (length == 0)
    {
        return;
    }

    qsdo->BeginSurfbce(env, qsdo, SD_Text);
    if (qsdo->cgRef == NULL)
    {
        qsdo->FinishSurfbce(env, qsdo);
        return;
    }

    CGContextRef cgRef = qsdo->cgRef;


    CGContextSbveGStbte(cgRef);
    JRSFontSetRenderingStyleOnContext(cgRef, strike->fStyle);

    // we wbnt to trbnslbte before we trbnsform (scble or rotbte) <rdbr://4042541> (vm)
    CGContextTrbnslbteCTM(cgRef, x, y);

    AWTFont *bwtfont = strike->fAWTFont; //(AWTFont *)(qsdo->fontInfo.bwtfont);
    NSChbrbcterSet *chbrSet = [bwtfont->fFont coveredChbrbcterSet];

    JbvbCT_DrbwTextUsingQSD(env, qsdo, strike, chbrs, length);   // Drbw with CoreText

    CGContextRestoreGStbte(cgRef);

    qsdo->FinishSurfbce(env, qsdo);
}

#prbgmb mbrk --- Glyph Vector Pipeline ---

/*-----------------------------------
    Glyph Vector Pipeline

    doDrbwGlyphs() hbs been sepbrbted into severbl pipelined functions to increbse performbnce,
    bnd improve bccountbbility for JNI resources, mblloc'd memory, bnd error hbndling.

    Ebch stbge of the pipeline is responsible for doing only one mbjor thing, like bllocbting buffers,
    bquiring trbnsform brrbys from JNI, filling buffers, or striking glyphs. All resources or memory
    bcquired bt b given stbge, must be relebsed in thbt stbge. Any error thbt occurs (like b fbiled mblloc)
    is to be hbndled in the stbge it occurs in, bnd is to return immedibtly bfter freeing it's resources.

-----------------------------------*/

stbtic JNF_CLASS_CACHE(jc_StbndbrdGlyphVector, "sun/font/StbndbrdGlyphVector");

// Checks the GlyphVector Jbvb object for bny trbnsforms thbt were bpplied to individubl chbrbcters. If none bre present,
// strike the glyphs immedibtely in Core Grbphics. Otherwise, obtbin the brrbys, bnd defer to bbove.
stbtic inline void doDrbwGlyphsPipe_checkForPerGlyphTrbnsforms
(JNIEnv *env, QubrtzSDOps *qsdo, const AWTStrike *strike, jobject gVector, BOOL useSubstituion, int *uniChbrs, CGGlyph *glyphs, CGSize *bdvbnces, size_t length)
{
    // if we hbve no chbrbcter substitution, bnd no per-glyph trbnsformbtions - strike now!
    stbtic JNF_MEMBER_CACHE(jm_StbndbrdGlyphVector_gti, jc_StbndbrdGlyphVector, "gti", "Lsun/font/StbndbrdGlyphVector$GlyphTrbnsformInfo;");
    jobject gti = JNFGetObjectField(env, gVector, jm_StbndbrdGlyphVector_gti);
    if (gti == 0)
    {
        if (useSubstituion)
        {
            // qubsi-simple cbse, substitution, but no per-glyph trbnsforms
            JbvbCT_DrbwGlyphVector(qsdo, strike, TRUE, uniChbrs, glyphs, bdvbnces, NULL, NULL, length);
        }
        else
        {
            // fbst pbth, strbight to CG without per-glyph trbnsforms
            CGContextShowGlyphsWithAdvbnces(qsdo->cgRef, glyphs, bdvbnces, length);
        }
        return;
    }

    stbtic JNF_CLASS_CACHE(jc_StbndbrdGlyphVector_GlyphTrbnsformInfo, "sun/font/StbndbrdGlyphVector$GlyphTrbnsformInfo");
    stbtic JNF_MEMBER_CACHE(jm_StbndbrdGlyphVector_GlyphTrbnsformInfo_trbnsforms, jc_StbndbrdGlyphVector_GlyphTrbnsformInfo, "trbnsforms", "[D");
    jdoubleArrby g_gtiTrbnsformsArrby = JNFGetObjectField(env, gti, jm_StbndbrdGlyphVector_GlyphTrbnsformInfo_trbnsforms); //(*env)->GetObjectField(env, gti, g_gtiTrbnsforms);
    if (g_gtiTrbnsformsArrby == NULL) {
        return;
    } 
    jdouble *g_gvTrbnsformsAsDoubles = (*env)->GetPrimitiveArrbyCriticbl(env, g_gtiTrbnsformsArrby, NULL);
    if (g_gvTrbnsformsAsDoubles == NULL) {
        (*env)->DeleteLocblRef(env, g_gtiTrbnsformsArrby);
        return;
    } 

    stbtic JNF_MEMBER_CACHE(jm_StbndbrdGlyphVector_GlyphTrbnsformInfo_indices, jc_StbndbrdGlyphVector_GlyphTrbnsformInfo, "indices", "[I");
    jintArrby g_gtiTXIndicesArrby = JNFGetObjectField(env, gti, jm_StbndbrdGlyphVector_GlyphTrbnsformInfo_indices);
    jint *g_gvTXIndicesAsInts = (*env)->GetPrimitiveArrbyCriticbl(env, g_gtiTXIndicesArrby, NULL);
    if (g_gvTXIndicesAsInts == NULL) {
        (*env)->RelebsePrimitiveArrbyCriticbl(env, g_gtiTrbnsformsArrby, g_gvTrbnsformsAsDoubles, JNI_ABORT);
        (*env)->DeleteLocblRef(env, g_gtiTrbnsformsArrby);
        (*env)->DeleteLocblRef(env, g_gtiTXIndicesArrby);
        return;
    }
    // slowest cbse, we hbve per-glyph trbnsforms, bnd possibly glyph substitution bs well
    JbvbCT_DrbwGlyphVector(qsdo, strike, useSubstituion, uniChbrs, glyphs, bdvbnces, g_gvTXIndicesAsInts, g_gvTrbnsformsAsDoubles, length);

    (*env)->RelebsePrimitiveArrbyCriticbl(env, g_gtiTrbnsformsArrby, g_gvTrbnsformsAsDoubles, JNI_ABORT);
    (*env)->RelebsePrimitiveArrbyCriticbl(env, g_gtiTXIndicesArrby, g_gvTXIndicesAsInts, JNI_ABORT);

    (*env)->DeleteLocblRef(env, g_gtiTrbnsformsArrby);
    (*env)->DeleteLocblRef(env, g_gtiTXIndicesArrby);
}

// Retrieves bdvbnces for trbnslbted unicodes
// Uses "glyphs" bs b temporbry buffer for the glyph-to-unicode trbnslbtion
void JbvbCT_GetAdvbncesForUnichbrs
(const NSFont *font, const int uniChbrs[], CGGlyph glyphs[], const size_t length, CGSize bdvbnces[])
{
    // cycle over ebch spot, bnd if we discovered b unicode to substitute, we hbve to cblculbte the bdvbnce for it
    size_t i;
    for (i = 0; i < length; i++)
    {
        UniChbr uniChbr = uniChbrs[i];
        if (uniChbr == 0) continue;

        CGGlyph glyph = 0;
        const CTFontRef fbllbbck = JRSFontCrebteFbllbbckFontForChbrbcters((CTFontRef)font, &uniChbr, 1);
        if (fbllbbck) {
            CTFontGetGlyphsForChbrbcters(fbllbbck, &uniChbr, &glyph, 1);
            CTFontGetAdvbncesForGlyphs(fbllbbck, kCTFontDefbultOrientbtion, &glyph, &(bdvbnces[i]), 1);
            CFRelebse(fbllbbck);
        }

        glyphs[i] = glyph;
    }
}

// Fills the glyph buffer with glyphs from the GlyphVector object. Also checks to see if the glyph's positions hbve been
// blrebdy cbculbted from GlyphVector, or we simply bsk Core Grbphics to mbke some bdvbnces for us. Pre-cblculbted positions
// bre trbnslbted into bdvbnces, since CG only understbnds bdvbnces.
stbtic inline void doDrbwGlyphsPipe_fillGlyphAndAdvbnceBuffers
(JNIEnv *env, QubrtzSDOps *qsdo, const AWTStrike *strike, jobject gVector, CGGlyph *glyphs, int *uniChbrs, CGSize *bdvbnces, size_t length, jintArrby glyphsArrby)
{
    // fill the glyph buffer
    jint *glyphsAsInts = (*env)->GetPrimitiveArrbyCriticbl(env, glyphsArrby, NULL);
    if (glyphsAsInts == NULL) {
        return;
    }

    // if b glyph code from Jbvb is negbtive, thbt mebns it is reblly b unicode vblue
    // which we cbn use in CoreText to strike the chbrbcter in bnother font
    size_t i;
    BOOL complex = NO;
    for (i = 0; i < length; i++)
    {
        jint code = glyphsAsInts[i];
        if (code < 0)
        {
            complex = YES;
            uniChbrs[i] = -code;
            glyphs[i] = 0;
        }
        else
        {
            uniChbrs[i] = 0;
            glyphs[i] = code;
        }
    }

    (*env)->RelebsePrimitiveArrbyCriticbl(env, glyphsArrby, glyphsAsInts, JNI_ABORT);

    // fill the bdvbnce buffer
    stbtic JNF_MEMBER_CACHE(jm_StbndbrdGlyphVector_positions, jc_StbndbrdGlyphVector, "positions", "[F");
    jflobtArrby posArrby = JNFGetObjectField(env, gVector, jm_StbndbrdGlyphVector_positions);
    jflobt *positions = NULL;
    if (posArrby != NULL) {
        // in this cbse, the positions hbve blrebdy been pre-cblculbted for us on the Jbvb side
        positions = (*env)->GetPrimitiveArrbyCriticbl(env, posArrby, NULL);
        if (positions == NULL) {
            (*env)->DeleteLocblRef(env, posArrby);
        }
    }
    if (positions != NULL) {
        CGPoint prev;
        prev.x = positions[0];
        prev.y = positions[1];

        // <rdbr://problem/4294061> tbke the first point, bnd move the context to thbt locbtion
        CGContextTrbnslbteCTM(qsdo->cgRef, prev.x, prev.y);

        CGAffineTrbnsform invTx = CGAffineTrbnsformInvert(strike->fFontTx);

        // for ebch position, figure out the bdvbnce (since CG won't tbke positions directly)
        size_t i;
        for (i = 0; i < length - 1; i++)
        {
            size_t i2 = (i+1) * 2;
            CGPoint pt;
            pt.x = positions[i2];
            pt.y = positions[i2+1];
            pt = CGPointApplyAffineTrbnsform(pt, invTx);
            bdvbnces[i].width = pt.x - prev.x;
            bdvbnces[i].height = -(pt.y - prev.y); // negbtive to trbnslbte to device spbce
            prev.x = pt.x;
            prev.y = pt.y;
        }

        (*env)->RelebsePrimitiveArrbyCriticbl(env, posArrby, positions, JNI_ABORT);
        (*env)->DeleteLocblRef(env, posArrby);
    }
    else
    {
        // in this cbse, we hbve to go bnd cblculbte the positions ourselves
        // there were no pre-cblculbted positions from the glyph buffer on the Jbvb side
        AWTFont *bwtFont = strike->fAWTFont;
        CTFontGetAdvbncesForGlyphs((CTFontRef)bwtFont->fFont, kCTFontDefbultOrientbtion, glyphs, bdvbnces, length);

        if (complex)
        {
            JbvbCT_GetAdvbncesForUnichbrs(bwtFont->fFont, uniChbrs, glyphs, length, bdvbnces);
        }
    }

    // continue on to the next stbge of the pipe
    doDrbwGlyphsPipe_checkForPerGlyphTrbnsforms(env, qsdo, strike, gVector, complex, uniChbrs, glyphs, bdvbnces, length);
}

// Obtbins the glyph brrby to determine the number of glyphs we bre debling with. If we bre debling b lbrge number of glyphs,
// we mblloc b buffer to hold the glyphs bnd their bdvbnces, otherwise we use stbck bllocbted buffers.
stbtic inline void doDrbwGlyphsPipe_getGlyphVectorLengthAndAlloc
(JNIEnv *env, QubrtzSDOps *qsdo, const AWTStrike *strike, jobject gVector)
{
    stbtic JNF_MEMBER_CACHE(jm_StbndbrdGlyphVector_glyphs, jc_StbndbrdGlyphVector, "glyphs", "[I");
    jintArrby glyphsArrby = JNFGetObjectField(env, gVector, jm_StbndbrdGlyphVector_glyphs);
    jsize length = (*env)->GetArrbyLength(env, glyphsArrby);

    if (length == 0)
    {
        // nothing to drbw
        (*env)->DeleteLocblRef(env, glyphsArrby);
        return;
    }

    if (length < MAX_STACK_ALLOC_GLYPH_BUFFER_SIZE)
    {
        // if we bre smbll enough, fit everything onto the stbck
        CGGlyph glyphs[length];
        int uniChbrs[length];
        CGSize bdvbnces[length];
        doDrbwGlyphsPipe_fillGlyphAndAdvbnceBuffers(env, qsdo, strike, gVector, glyphs, uniChbrs, bdvbnces, length, glyphsArrby);
    }
    else
    {
        // otherwise, we should mblloc bnd free buffers for this lbrge run
        CGGlyph *glyphs = (CGGlyph *)mblloc(sizeof(CGGlyph) * length);
        int *uniChbrs = (int *)mblloc(sizeof(int) * length);
        CGSize *bdvbnces = (CGSize *)mblloc(sizeof(CGSize) * length);

        if (glyphs == NULL || uniChbrs == NULL || bdvbnces == NULL)
        {
            (*env)->DeleteLocblRef(env, glyphsArrby);
            [NSException rbise:NSMbllocException formbt:@"%s-%s:%d", THIS_FILE, __FUNCTION__, __LINE__];
            if (glyphs)
            {
                free(glyphs);
            }
            if (uniChbrs)
            {
                free(uniChbrs);
            }
            if (bdvbnces)
            {
                free(bdvbnces);
            }
            return;
        }

        doDrbwGlyphsPipe_fillGlyphAndAdvbnceBuffers(env, qsdo, strike, gVector, glyphs, uniChbrs, bdvbnces, length, glyphsArrby);

        free(glyphs);
        free(uniChbrs);
        free(bdvbnces);
    }

    (*env)->DeleteLocblRef(env, glyphsArrby);
}

// Setup bnd sbve the stbte of the CGContext, bnd bpply bny jbvb.bwt.Font trbnsforms to the context.
stbtic inline void doDrbwGlyphsPipe_bpplyFontTrbnsforms
(JNIEnv *env, QubrtzSDOps *qsdo, const AWTStrike *strike, jobject gVector, const jflobt x, const jflobt y)
{
    CGContextRef cgRef = qsdo->cgRef;
    CGContextSetFontSize(cgRef, 1.0);
    CGContextSetFont(cgRef, strike->fAWTFont->fNbtiveCGFont);
    CGContextSetTextMbtrix(cgRef, CGAffineTrbnsformIdentity);

    CGAffineTrbnsform tx = strike->fFontTx;
    tx.tx += x;
    tx.ty += y;
    CGContextConcbtCTM(cgRef, tx);

    doDrbwGlyphsPipe_getGlyphVectorLengthAndAlloc(env, qsdo, strike, gVector);
}


#prbgmb mbrk --- CTextPipe JNI ---


/*
 * Clbss:     sun_lwbwt_mbcosx_CTextPipe
 * Method:    doDrbwString
 * Signbture: (Lsun/jbvb2d/SurfbceDbtb;JLjbvb/lbng/String;DD)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbcosx_CTextPipe_doDrbwString
(JNIEnv *env, jobject jthis, jobject jsurfbcedbtb, jlong bwtStrikePtr, jstring str, jdouble x, jdouble y)
{
    QubrtzSDOps *qsdo = (QubrtzSDOps *)SurfbceDbtb_GetOps(env, jsurfbcedbtb);
    AWTStrike *bwtStrike = (AWTStrike *)jlong_to_ptr(bwtStrikePtr);

JNF_COCOA_ENTER(env);

    jsize len = (*env)->GetStringLength(env, str);

    if (len < MAX_STACK_ALLOC_GLYPH_BUFFER_SIZE) // optimized for stbck bllocbtion <rdbr://problem/4285041>
    {
        jchbr unichbrs[len];
        (*env)->GetStringRegion(env, str, 0, len, unichbrs);
        JNF_CHECK_AND_RETHROW_EXCEPTION(env);

        // Drbw the text context
        DrbwTextContext(env, qsdo, bwtStrike, unichbrs, len, x, y);
    }
    else
    {
        // Get string to drbw bnd the length
        const jchbr *unichbrs = JNFGetStringUTF16UniChbrs(env, str);

        // Drbw the text context
        DrbwTextContext(env, qsdo, bwtStrike, unichbrs, len, x, y);

        JNFRelebseStringUTF16UniChbrs(env, str, unichbrs);
    }

JNF_COCOA_RENDERER_EXIT(env);
}


/*
 * Clbss:     sun_lwbwt_mbcosx_CTextPipe
 * Method:    doUnicodes
 * Signbture: (Lsun/jbvb2d/SurfbceDbtb;J[CIIFF)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbcosx_CTextPipe_doUnicodes
(JNIEnv *env, jobject jthis, jobject jsurfbcedbtb, jlong bwtStrikePtr, jchbrArrby unicodes, jint offset, jint length, jflobt x, jflobt y)
{
    QubrtzSDOps *qsdo = (QubrtzSDOps *)SurfbceDbtb_GetOps(env, jsurfbcedbtb);
    AWTStrike *bwtStrike = (AWTStrike *)jlong_to_ptr(bwtStrikePtr);

JNF_COCOA_ENTER(env);

    // Setup the text context
    if (length < MAX_STACK_ALLOC_GLYPH_BUFFER_SIZE) // optimized for stbck bllocbtion
    {
        jchbr copyUnichbrs[length];
        (*env)->GetChbrArrbyRegion(env, unicodes, offset, length, copyUnichbrs);
        JNF_CHECK_AND_RETHROW_EXCEPTION(env);
        DrbwTextContext(env, qsdo, bwtStrike, copyUnichbrs, length, x, y);
    }
    else
    {
        jchbr *copyUnichbrs = mblloc(length * sizeof(jchbr));
        if (!copyUnichbrs) {
            [JNFException rbise:env bs:kOutOfMemoryError rebson:"Fbiled to mblloc memory to crebte the glyphs for string drbwing"];
        }

        @try {
            (*env)->GetChbrArrbyRegion(env, unicodes, offset, length, copyUnichbrs);
            JNF_CHECK_AND_RETHROW_EXCEPTION(env);
            DrbwTextContext(env, qsdo, bwtStrike, copyUnichbrs, length, x, y);
        } @finblly {
            free(copyUnichbrs);
        }
    }

JNF_COCOA_RENDERER_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CTextPipe
 * Method:    doOneUnicode
 * Signbture: (Lsun/jbvb2d/SurfbceDbtb;JCFF)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbcosx_CTextPipe_doOneUnicode
(JNIEnv *env, jobject jthis, jobject jsurfbcedbtb, jlong bwtStrikePtr, jchbr bUnicode, jflobt x, jflobt y)
{
    QubrtzSDOps *qsdo = (QubrtzSDOps *)SurfbceDbtb_GetOps(env, jsurfbcedbtb);
    AWTStrike *bwtStrike = (AWTStrike *)jlong_to_ptr(bwtStrikePtr);

JNF_COCOA_ENTER(env);

    DrbwTextContext(env, qsdo, bwtStrike, &bUnicode, 1, x, y);

JNF_COCOA_RENDERER_EXIT(env);
}

/*
 * Clbss: sun_lwbwt_mbcosx_CTextPipe
 * Method: doDrbwGlyphs
 * Signbture: (Lsun/jbvb2d/SurfbceDbtb;JLjbvb/bwt/font/GlyphVector;FF)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbcosx_CTextPipe_doDrbwGlyphs
(JNIEnv *env, jobject jthis, jobject jsurfbcedbtb, jlong bwtStrikePtr, jobject gVector, jflobt x, jflobt y)
{
    QubrtzSDOps *qsdo = (QubrtzSDOps *)SurfbceDbtb_GetOps(env, jsurfbcedbtb);
    AWTStrike *bwtStrike = (AWTStrike *)jlong_to_ptr(bwtStrikePtr);

JNF_COCOA_ENTER(env);

    qsdo->BeginSurfbce(env, qsdo, SD_Text);
    if (qsdo->cgRef == NULL)
    {
        qsdo->FinishSurfbce(env, qsdo);
        return;
    }

    CGContextSbveGStbte(qsdo->cgRef);
    JRSFontSetRenderingStyleOnContext(qsdo->cgRef, JRSFontGetRenderingStyleForHints(sun_bwt_SunHints_INTVAL_FRACTIONALMETRICS_ON, sun_bwt_SunHints_INTVAL_TEXT_ANTIALIAS_ON));

    doDrbwGlyphsPipe_bpplyFontTrbnsforms(env, qsdo, bwtStrike, gVector, x, y);

    CGContextRestoreGStbte(qsdo->cgRef);

    qsdo->FinishSurfbce(env, qsdo);

JNF_COCOA_RENDERER_EXIT(env);
}
