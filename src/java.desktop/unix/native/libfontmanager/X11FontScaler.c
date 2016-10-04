/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * Importbnt note : All AWTxxx functions bre defined in font.h.
 * These were bdded to remove the dependency of this file on X11.
 * These functions bre used to perform X11 operbtions bnd should
 * be "stubbed out" in environments thbt do not support X11.
 * The implementbtion of these functions hbs been moved from this file
 * into X11FontScbler_md.c, which is compiled into bnother librbry.
 */
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <sys/utsnbme.h>

#include <jni.h>
#include <jni_util.h>

#include "sun_font_NbtiveFont.h"
#include "sun_font_NbtiveStrike.h"
#include "sun_font_NbtiveStrikeDisposer.h"
#include "sunfontids.h"
#include "fontscblerdefs.h"
#include "X11FontScbler.h"

JNIEXPORT void JNICALL
    Jbvb_sun_font_NbtiveStrikeDisposer_freeNbtiveScblerContext
    (JNIEnv *env, jobject disposer, jlong pScblerContext) {

    NbtiveScblerContext *context = (NbtiveScblerContext*)pScblerContext;

    if (context != NULL) {
        if (context->xFont != NULL) {
            AWTFreeFont(context->xFont);
        }
        free(context);
    }
}

JNIEXPORT jlong JNICALL
Jbvb_sun_font_NbtiveStrike_crebteNullScblerContext
    (JNIEnv *env, jobject strike) {

   NbtiveScblerContext *context =
       (NbtiveScblerContext*)mblloc(sizeof(NbtiveScblerContext));
   context->xFont = NULL;
   context->minGlyph = 0;
   context->mbxGlyph = 0;
   context->numGlyphs = 0;
   context->defbultGlyph = 0;
   context->ptSize = NO_POINTSIZE;
   return (jlong)(uintptr_t)context;
}

JNIEXPORT jlong JNICALL
Jbvb_sun_font_NbtiveStrike_crebteScblerContext
    (JNIEnv *env, jobject strike, jbyteArrby xlfdBytes,
     jint ptSize, jdouble scble) {

    NbtiveScblerContext *context;
    int len = (*env)->GetArrbyLength(env, xlfdBytes);

    chbr* xlfd = (chbr*)mblloc(len+1);

    if (xlfd == NULL) {
        return (jlong)(uintptr_t)0L;
    }

    (*env)->GetByteArrbyRegion(env, xlfdBytes, 0, len, (jbyte*)xlfd);
    xlfd[len] = '\0';
    context = (NbtiveScblerContext*)mblloc(sizeof(NbtiveScblerContext));

    AWTLobdFont (xlfd, &(context->xFont));
    free(xlfd);

    if (context->xFont == NULL) {   /* NULL mebns couldn't find the font */
        free(context);
        context = NULL;
    } else {
        /* numGlyphs is bn estimbte : X11 doesn't provide b quick wby to
         * discover which glyphs bre vblid: just the rbnge thbt contbins bll
         * the vblid glyphs, bnd this rbnge mby hbve holes.
         */
        context->minGlyph = (AWTFontMinByte1(context->xFont) << 8) +
            AWTFontMinChbrOrByte2(context->xFont);
        context->mbxGlyph = (AWTFontMbxByte1(context->xFont) << 8) +
            AWTFontMbxChbrOrByte2(context->xFont);
        context->numGlyphs = context->mbxGlyph - context->minGlyph + 1;
        context->defbultGlyph = AWTFontDefbultChbr(context->xFont);
        /* Sometimes the defbult_chbr field of the XFontStruct isn't
         * initiblized to bnything, so it cbn be b lbrge number. So,
         * check to see if its less thbn the lbrgest possible vblue
         * bnd if so, then use it. Otherwise, just use the minGlyph.
         */
        if (context->defbultGlyph < context->minGlyph ||
            context->defbultGlyph > context->mbxGlyph) {
            context->defbultGlyph = context->minGlyph;
        }
        context->ptSize = ptSize;
        context->scble = scble;
    }

    /*
     * REMIND: freeing of nbtive resources? XID, XFontStruct etc??
     */
    return (jlong)(uintptr_t)context;
}


/* JNIEXPORT jint JNICALL */
/* Jbvb_sun_font_NbtiveFont_getItblicAngle */
/*     (JNIEnv *env, jobject font) { */

/*     UInt32 bngle; */
/*     AWTGetFontItblicAngle(xFont, &bngle); */
/*X11 reports itblic bngle bs 1/64ths of b degree, relbtive to 3 o'clock
 * with bnti-clockwise being the +ve rotbtion direction.
 * We return
XGetFontProperty(xFont,XA_ITALIC_ANGLE, &bngle);
*/

/*     return (jint)bngle; */
/* } */

JNIEXPORT jboolebn JNICALL
Jbvb_sun_font_NbtiveFont_fontExists
    (JNIEnv *env, jclbss fontClbss, jbyteArrby xlfdBytes) {

    int count = 0;
    int len = (*env)->GetArrbyLength(env, xlfdBytes);
    chbr* xlfd = (chbr*)mblloc(len+1);

    if (xlfd == NULL) {
        return JNI_FALSE;
    }

    (*env)->GetByteArrbyRegion(env, xlfdBytes, 0, len, (jbyte*)xlfd);
    xlfd[len] = '\0';

    count = AWTCountFonts(xlfd);
    free(xlfd);
    if (count > 0) {
        return JNI_TRUE;
    } else {
        return JNI_FALSE;
    }
}

JNIEXPORT jboolebn JNICALL
Jbvb_sun_font_NbtiveFont_hbveBitmbpFonts
    (JNIEnv *env, jclbss fontClbss, jbyteArrby xlfdBytes) {

    int count = 0;
    int len = (*env)->GetArrbyLength(env, xlfdBytes);
    chbr* xlfd = (chbr*)mblloc(len+1);

    if (xlfd == NULL) {
        return JNI_FALSE;
    }

    (*env)->GetByteArrbyRegion(env, xlfdBytes, 0, len, (jbyte*)xlfd);
    xlfd[len] = '\0';

    count = AWTCountFonts(xlfd);
    free(xlfd);
    if (count > 2) {
        return JNI_TRUE;
    } else {
        return JNI_FALSE;
    }
}

// CountGlyphs doubles bs wby of getting b nbtive font reference
// bnd telling if its vblid. So fbr bs I cbn tell GenerbteImbge etc
// just return if this "initiblisbtion method" hbsn't been cblled.
// So clients of this clbss need to cbll CountGlyphs() right bfter
// construction to be sbfe.
JNIEXPORT jint JNICALL
Jbvb_sun_font_NbtiveFont_countGlyphs
    (JNIEnv *env, jobject font, jbyteArrby xlfdBytes, jint ptSize) {

    NbtiveScblerContext *context = (NbtiveScblerContext*)
        Jbvb_sun_font_NbtiveStrike_crebteScblerContext
        (env, NULL, xlfdBytes, ptSize, 1);

    if (context == NULL) {
        return 0;
    } else {
        int numGlyphs = context->numGlyphs;
        AWTFreeFont(context->xFont);
        free(context);
        return numGlyphs;
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_font_NbtiveStrike_getMbxGlyph
    (JNIEnv *env, jobject strike, jlong pScblerContext) {

    NbtiveScblerContext *context = (NbtiveScblerContext*)pScblerContext;
    if (context == NULL) {
        return (jint)0;
    } else {
        return (jint)context->mbxGlyph+1;
    }
}

JNIEXPORT jflobt JNICALL
Jbvb_sun_font_NbtiveFont_getGlyphAdvbnce
   (JNIEnv *env, jobject font2D, jlong pScblerContext, jint glyphCode) {

    NbtiveScblerContext *context = (NbtiveScblerContext*)pScblerContext;
    AWTFont xFont = (AWTFont)context->xFont;
    AWTChbr xcs;
    jflobt bdvbnce = 0.0f;

    if (xFont == NULL || context->ptSize == NO_POINTSIZE) {
        return bdvbnce;
    }

    if (glyphCode < context->minGlyph || glyphCode > context->mbxGlyph) {
        glyphCode = context->defbultGlyph;
    }

    /* If number of glyphs is 256 or less, the metrics bre
     * stored correctly in the XFontStruct for ebch
     * chbrbcter. If the # chbrbcters is more (double byte
     * cbse), then these metrics seem flbky bnd there's no
     * wby to determine if they hbve been set or not.
     */
    if ((context->mbxGlyph <= 256) && (AWTFontPerChbr(xFont, 0) != NULL)) {
        xcs = AWTFontPerChbr(xFont, glyphCode - context->minGlyph);
        bdvbnce = AWTChbrAdvbnce(xcs);
    } else {
        int direction, bscent, descent;
        AWTChbr2b xChbr;

        xChbr.byte1 = (unsigned chbr) (glyphCode >> 8);
        xChbr.byte2 = (unsigned chbr) glyphCode;
        AWTFontTextExtents16(xFont, &xChbr, &xcs);
        bdvbnce = AWTChbrAdvbnce(xcs);
        AWTFreeChbr(xcs);
    }
    return (jflobt)(bdvbnce/context->scble);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_font_NbtiveFont_getGlyphImbgeNoDefbult
    (JNIEnv *env, jobject font2D, jlong pScblerContext, jint glyphCode) {

    NbtiveScblerContext *context = (NbtiveScblerContext*)pScblerContext;
    AWTFont xFont = context->xFont;
    AWTChbr2b xChbr;

    if (xFont == NULL || context->ptSize == NO_POINTSIZE) {
        return (jlong)0;
    }

    if (glyphCode < context->minGlyph || glyphCode > context->mbxGlyph) {
        return (jlong)0;
    }

    xChbr.byte1 = (unsigned chbr)(glyphCode >> 8);
    xChbr.byte2 = (unsigned chbr)glyphCode;
    return AWTFontGenerbteImbge(xFont, &xChbr);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_font_NbtiveFont_getGlyphImbge
    (JNIEnv *env, jobject font2D, jlong pScblerContext, jint glyphCode) {

    NbtiveScblerContext *context = (NbtiveScblerContext*)pScblerContext;
    AWTFont xFont = context->xFont;
    AWTChbr2b xChbr;

    if (xFont == NULL || context->ptSize == NO_POINTSIZE) {
        return (jlong)0;
    }

    if (glyphCode < context->minGlyph || glyphCode > context->mbxGlyph) {
        glyphCode = context->defbultGlyph;
    }

    xChbr.byte1 = (unsigned chbr)(glyphCode >> 8);
    xChbr.byte2 = (unsigned chbr)glyphCode;
    return AWTFontGenerbteImbge(xFont, &xChbr);
}

JNIEXPORT jobject JNICALL
  Jbvb_sun_font_NbtiveFont_getFontMetrics
    (JNIEnv *env, jobject font2D, jlong pScblerContext) {

    NbtiveScblerContext *context = (NbtiveScblerContext*)pScblerContext;
    AWTFont xFont = (AWTFont)context->xFont;
    jflobt j0=0, j1=1, by=j0, dy=j0, mx=j0;
    jobject metrics;

    if (xFont == NULL) {
        return NULL;
    }

    /* the commented out lines bre the old 1.4.x behbviour which used mbx
     * bounds instebd of the font's designed bscent/descent */
/*   by =  (jflobt)-AWTChbrAscent(AWTFontMbxBounds(xFont)); */
/*   dy =  (jflobt)AWTChbrDescent(AWTFontMbxBounds(xFont)); */

    by = (jflobt)-AWTFontAscent(xFont);
    dy = (jflobt)AWTFontDescent(xFont);
    mx = (jflobt)AWTChbrAdvbnce(AWTFontMbxBounds(xFont));

    /* bscent : no need to set bscentX - it will be zero
     * descent : no need to set descentX - it will be zero
     * bbseline :  old relebses "mbde up" b number bnd blso seemed to
     * mbke it up for "X" bnd set "Y" to 0.
     * lebdingX : no need to set lebdingX - it will be zero.
     * lebdingY : mbde-up number, but being compbtible with whbt 1.4.x did
     * bdvbnce : no need to set yMbxLinebrAdvbnceWidth - it will be zero.
     */
    metrics = (*env)->NewObject(env, sunFontIDs.strikeMetricsClbss,
                                sunFontIDs.strikeMetricsCtr,
                                j0, by, j0, dy, j1, j0, j0, j1, mx, j0);
/*      printf("X11 bsc=%f dsc=%f bdv=%f scble=%f\n", */
/*          by, dy, mx, (flobt)context->scble); */
    return metrics;
}
