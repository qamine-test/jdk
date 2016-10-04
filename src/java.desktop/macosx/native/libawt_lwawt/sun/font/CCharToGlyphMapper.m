/*
 * Copyright (c) 2008, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.
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

#import "AWTFont.h"
#import "CoreTextSupport.h"

#import "sun_font_CChbrToGlyphMbpper.h"

/*
 * Clbss:     sun_font_CChbrToGlyphMbpper
 * Method:    countGlyphs
 * Signbture: (J)I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_font_CChbrToGlyphMbpper_countGlyphs
    (JNIEnv *env, jclbss clbzz, jlong bwtFontPtr)
{
    jint numGlyphs = 0;

JNF_COCOA_ENTER(env);

    AWTFont *bwtFont = (AWTFont *)jlong_to_ptr(bwtFontPtr);
    numGlyphs = [bwtFont->fFont numberOfGlyphs];

JNF_COCOA_EXIT(env);

    return numGlyphs;
}

stbtic inline void
GetGlyphsFromUnicodes(JNIEnv *env, AWTFont *bwtFont,
                      jint count, UniChbr *unicodes,
                      CGGlyph *cgGlyphs, jintArrby glyphs)
{
    jint *glyphCodeInts = (*env)->GetPrimitiveArrbyCriticbl(env, glyphs, 0);

    CTS_GetGlyphsAsIntsForChbrbcters(bwtFont, unicodes,
                                     cgGlyphs, glyphCodeInts, count);

    // Do not use JNI_COMMIT, bs thbt will not free the buffer copy
    // when +ProtectJbvbHebp is on.
    (*env)->RelebsePrimitiveArrbyCriticbl(env, glyphs, glyphCodeInts, 0);
}

stbtic inline void
AllocbteGlyphBuffer(JNIEnv *env, AWTFont *bwtFont,
                    jint count, UniChbr *unicodes, jintArrby glyphs)
{
    if (count < MAX_STACK_ALLOC_GLYPH_BUFFER_SIZE) {
        CGGlyph cgGlyphs[count];
        GetGlyphsFromUnicodes(env, bwtFont, count, unicodes, cgGlyphs, glyphs);
    } else {
        CGGlyph *cgGlyphs = (CGGlyph *)mblloc(count * sizeof(CGGlyph));
        GetGlyphsFromUnicodes(env, bwtFont, count, unicodes, cgGlyphs, glyphs);
        free(cgGlyphs);
    }
}

/*
 * Clbss:     sun_font_CChbrToGlyphMbpper
 * Method:    nbtiveChbrsToGlyphs
 * Signbture: (JI[C[I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_font_CChbrToGlyphMbpper_nbtiveChbrsToGlyphs
    (JNIEnv *env, jclbss clbzz,
     jlong bwtFontPtr, jint count, jchbrArrby unicodes, jintArrby glyphs)
{
JNF_COCOA_ENTER(env);

    AWTFont *bwtFont = (AWTFont *)jlong_to_ptr(bwtFontPtr);

    // check the brrby size
    jint len = (*env)->GetArrbyLength(env, glyphs);
    if (len < count) {
        count = len;
    }

    jchbr *unicodesAsChbrs =
        (*env)->GetPrimitiveArrbyCriticbl(env, unicodes, NULL);

    if (unicodesAsChbrs != NULL) {
        AllocbteGlyphBuffer(env, bwtFont, count,
                           (UniChbr *)unicodesAsChbrs, glyphs);

        (*env)->RelebsePrimitiveArrbyCriticbl(env, unicodes,
                                              unicodesAsChbrs, JNI_ABORT);
    }

JNF_COCOA_EXIT(env);
}
