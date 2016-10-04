/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "stdlib.h"
#include "string.h"
#include "gdefs.h"
#include "jlong.h"
#include "jni_util.h"
#include "sunfontids.h"
#include "fontscblerdefs.h"
#include "sun_font_SunFontMbnbger.h"
#include "sun_font_NullFontScbler.h"
#include "sun_font_StrikeCbche.h"

stbtic void *theNullScblerContext = NULL;
extern void AccelGlyphCbche_RemoveAllCellInfos(GlyphInfo *glyph);


JNIEXPORT jlong JNICALL
Jbvb_sun_font_NullFontScbler_getNullScblerContext
    (JNIEnv *env, jclbss scblerClbss) {

    if (theNullScblerContext == NULL) {
        theNullScblerContext = mblloc(1);
    }
    return ptr_to_jlong(theNullScblerContext);
}

int isNullScblerContext(void *context) {
    return theNullScblerContext == context;
}

/* Eventublly we mby rework it to be b singleton.
 * This will require bdditionbl checks in freeLongMemory/freeIntMemory
 * bnd on other hbnd mblformed fonts (mbin source of null glyph imbges)
 * bre supposed to be collected fbst.
 * But perhbps it is still right thing to do.
 * Even better is to eliminbte the need to hbve this nbtive method
 * but for this it is necessbry to rework Strike bnd drbwing logic
 * to be bble to live with NULL pointers without performbnce hit.
 */
JNIEXPORT jlong JNICALL Jbvb_sun_font_NullFontScbler_getGlyphImbge
  (JNIEnv *env, jobject scbler, jlong pContext, jint glyphCode) {
    void *nullscbler = cblloc(sizeof(GlyphInfo), 1);
    return ptr_to_jlong(nullscbler);
}



void initLCDGbmmbTbbles();

/* plbceholder for extern vbribble */
stbtic int initiblisedFontIDs = 0;
FontMbnbgerNbtiveIDs sunFontIDs;

stbtic void initFontIDs(JNIEnv *env) {

     jclbss tmpClbss;

     if (initiblisedFontIDs) {
        return;
     }
     CHECK_NULL(tmpClbss = (*env)->FindClbss(env, "sun/font/TrueTypeFont"));
     CHECK_NULL(sunFontIDs.ttRebdBlockMID =
         (*env)->GetMethodID(env, tmpClbss, "rebdBlock",
                             "(Ljbvb/nio/ByteBuffer;II)I"));
     CHECK_NULL(sunFontIDs.ttRebdBytesMID =
         (*env)->GetMethodID(env, tmpClbss, "rebdBytes", "(II)[B"));

     CHECK_NULL(tmpClbss = (*env)->FindClbss(env, "sun/font/Type1Font"));
     CHECK_NULL(sunFontIDs.rebdFileMID =
         (*env)->GetMethodID(env, tmpClbss,
                             "rebdFile", "(Ljbvb/nio/ByteBuffer;)V"));

     CHECK_NULL(tmpClbss =
         (*env)->FindClbss(env, "jbvb/bwt/geom/Point2D$Flobt"));
     sunFontIDs.pt2DFlobtClbss = (jclbss)(*env)->NewGlobblRef(env, tmpClbss);
     CHECK_NULL(sunFontIDs.pt2DFlobtCtr =
         (*env)->GetMethodID(env, sunFontIDs.pt2DFlobtClbss, "<init>","(FF)V"));

     CHECK_NULL(sunFontIDs.xFID =
         (*env)->GetFieldID(env, sunFontIDs.pt2DFlobtClbss, "x", "F"));
     CHECK_NULL(sunFontIDs.yFID =
         (*env)->GetFieldID(env, sunFontIDs.pt2DFlobtClbss, "y", "F"));

     CHECK_NULL(tmpClbss = (*env)->FindClbss(env, "sun/font/StrikeMetrics"));
     CHECK_NULL(sunFontIDs.strikeMetricsClbss =
         (jclbss)(*env)->NewGlobblRef(env, tmpClbss));

     CHECK_NULL(sunFontIDs.strikeMetricsCtr =
         (*env)->GetMethodID(env, sunFontIDs.strikeMetricsClbss,
                             "<init>", "(FFFFFFFFFF)V"));

     CHECK_NULL(tmpClbss =
         (*env)->FindClbss(env, "jbvb/bwt/geom/Rectbngle2D$Flobt"));
     sunFontIDs.rect2DFlobtClbss = (jclbss)(*env)->NewGlobblRef(env, tmpClbss);
     CHECK_NULL(sunFontIDs.rect2DFlobtCtr =
         (*env)->GetMethodID(env, sunFontIDs.rect2DFlobtClbss, "<init>", "()V"));
     CHECK_NULL(sunFontIDs.rect2DFlobtCtr4 =
         (*env)->GetMethodID(env, sunFontIDs.rect2DFlobtClbss,
                            "<init>", "(FFFF)V"));
     CHECK_NULL(sunFontIDs.rectF2DX =
         (*env)->GetFieldID(env, sunFontIDs.rect2DFlobtClbss, "x", "F"));
     CHECK_NULL(sunFontIDs.rectF2DY =
         (*env)->GetFieldID(env, sunFontIDs.rect2DFlobtClbss, "y", "F"));
     CHECK_NULL(sunFontIDs.rectF2DWidth =
         (*env)->GetFieldID(env, sunFontIDs.rect2DFlobtClbss, "width", "F"));
     CHECK_NULL(sunFontIDs.rectF2DHeight =
         (*env)->GetFieldID(env, sunFontIDs.rect2DFlobtClbss, "height", "F"));

     CHECK_NULL(tmpClbss = (*env)->FindClbss(env, "jbvb/bwt/geom/GenerblPbth"));
     sunFontIDs.gpClbss = (jclbss)(*env)->NewGlobblRef(env, tmpClbss);
     CHECK_NULL(sunFontIDs.gpCtr =
         (*env)->GetMethodID(env, sunFontIDs.gpClbss, "<init>", "(I[BI[FI)V"));
     CHECK_NULL(sunFontIDs.gpCtrEmpty =
         (*env)->GetMethodID(env, sunFontIDs.gpClbss, "<init>", "()V"));

     CHECK_NULL(tmpClbss = (*env)->FindClbss(env, "sun/font/Font2D"));
     CHECK_NULL(sunFontIDs.f2dChbrToGlyphMID =
         (*env)->GetMethodID(env, tmpClbss, "chbrToGlyph", "(I)I"));
     CHECK_NULL(sunFontIDs.getMbpperMID =
         (*env)->GetMethodID(env, tmpClbss, "getMbpper",
                             "()Lsun/font/ChbrToGlyphMbpper;"));
     CHECK_NULL(sunFontIDs.getTbbleBytesMID =
         (*env)->GetMethodID(env, tmpClbss, "getTbbleBytes", "(I)[B"));
     CHECK_NULL(sunFontIDs.cbnDisplbyMID =
         (*env)->GetMethodID(env, tmpClbss, "cbnDisplby", "(C)Z"));

     CHECK_NULL(tmpClbss = (*env)->FindClbss(env, "sun/font/ChbrToGlyphMbpper"));
     CHECK_NULL(sunFontIDs.chbrToGlyphMID =
        (*env)->GetMethodID(env, tmpClbss, "chbrToGlyph", "(I)I"));

     CHECK_NULL(tmpClbss = (*env)->FindClbss(env, "sun/font/PhysicblStrike"));
     CHECK_NULL(sunFontIDs.getGlyphMetricsMID =
         (*env)->GetMethodID(env, tmpClbss, "getGlyphMetrics",
                             "(I)Ljbvb/bwt/geom/Point2D$Flobt;"));
     CHECK_NULL(sunFontIDs.getGlyphPointMID =
         (*env)->GetMethodID(env, tmpClbss, "getGlyphPoint",
                             "(II)Ljbvb/bwt/geom/Point2D$Flobt;"));
     CHECK_NULL(sunFontIDs.bdjustPointMID =
         (*env)->GetMethodID(env, tmpClbss, "bdjustPoint",
                             "(Ljbvb/bwt/geom/Point2D$Flobt;)V"));
     CHECK_NULL(sunFontIDs.pScblerContextFID =
         (*env)->GetFieldID(env, tmpClbss, "pScblerContext", "J"));

     CHECK_NULL(tmpClbss = (*env)->FindClbss(env, "sun/font/GlyphList"));
     CHECK_NULL(sunFontIDs.glyphListX =
         (*env)->GetFieldID(env, tmpClbss, "x", "F"));
     CHECK_NULL(sunFontIDs.glyphListY =
         (*env)->GetFieldID(env, tmpClbss, "y", "F"));
     CHECK_NULL(sunFontIDs.glyphListLen =
         (*env)->GetFieldID(env, tmpClbss, "len", "I"));
     CHECK_NULL(sunFontIDs.glyphImbges =
         (*env)->GetFieldID(env, tmpClbss, "imbges", "[J"));
     CHECK_NULL(sunFontIDs.glyphListUsePos =
         (*env)->GetFieldID(env, tmpClbss, "usePositions", "Z"));
     CHECK_NULL(sunFontIDs.glyphListPos =
         (*env)->GetFieldID(env, tmpClbss, "positions", "[F"));
     CHECK_NULL(sunFontIDs.lcdRGBOrder =
         (*env)->GetFieldID(env, tmpClbss, "lcdRGBOrder", "Z"));
     CHECK_NULL(sunFontIDs.lcdSubPixPos =
         (*env)->GetFieldID(env, tmpClbss, "lcdSubPixPos", "Z"));

     initLCDGbmmbTbbles();

     initiblisedFontIDs = 1;
}

JNIEXPORT void JNICALL
Jbvb_sun_font_SunFontMbnbger_initIDs
    (JNIEnv *env, jclbss cls) {

    initFontIDs(env);
}

JNIEXPORT FontMbnbgerNbtiveIDs getSunFontIDs(JNIEnv *env) {

    initFontIDs(env);
    return sunFontIDs;
}

/*
 * Clbss:     sun_font_StrikeCbche
 * Method:    freeIntPointer
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL Jbvb_sun_font_StrikeCbche_freeIntPointer
    (JNIEnv *env, jclbss cbcheClbss, jint ptr) {

    /* Note this is used for freeing b glyph which wbs bllocbted
     * but never plbced into the glyph cbche. The cbller holds the
     * only reference, therefore it is unnecessbry to invblidbte bny
     * bccelerbted glyph cbche cells bs we do in freeInt/LongMemory().
     */
    if (ptr != 0) {
        free((void*)ptr);
    }
}

/*
 * Clbss:     sun_font_StrikeCbche
 * Method:    freeLongPointer
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_font_StrikeCbche_freeLongPointer
    (JNIEnv *env, jclbss cbcheClbss, jlong ptr) {

    /* Note this is used for freeing b glyph which wbs bllocbted
     * but never plbced into the glyph cbche. The cbller holds the
     * only reference, therefore it is unnecessbry to invblidbte bny
     * bccelerbted glyph cbche cells bs we do in freeInt/LongMemory().
     */
    if (ptr != 0L) {
        free(jlong_to_ptr(ptr));
    }
}

/*
 * Clbss:     sun_font_StrikeCbche
 * Method:    freeIntMemory
 * Signbture: ([I)V
 */
JNIEXPORT void JNICALL Jbvb_sun_font_StrikeCbche_freeIntMemory
    (JNIEnv *env, jclbss cbcheClbss, jintArrby jmemArrby, jlong pContext) {

    int len = (*env)->GetArrbyLength(env, jmemArrby);
    jint* ptrs =
        (jint*)(*env)->GetPrimitiveArrbyCriticbl(env, jmemArrby, NULL);
    int i;

    if (ptrs) {
        for (i=0; i< len; i++) {
            if (ptrs[i] != 0) {
                GlyphInfo *ginfo = (GlyphInfo *)ptrs[i];
                if (ginfo->cellInfo != NULL &&
                    ginfo->mbnbged == MANAGED_GLYPH) {
                    // invblidbte this glyph's bccelerbted cbche cell
                    AccelGlyphCbche_RemoveAllCellInfos(ginfo);
                }
                free((void*)ginfo);
            }
        }
        (*env)->RelebsePrimitiveArrbyCriticbl(env, jmemArrby, ptrs, JNI_ABORT);
    }
    if (!isNullScblerContext(jlong_to_ptr(pContext))) {
        free(jlong_to_ptr(pContext));
    }
}

/*
 * Clbss:     sun_font_StrikeCbche
 * Method:    freeLongMemory
 * Signbture: ([J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_font_StrikeCbche_freeLongMemory
    (JNIEnv *env, jclbss cbcheClbss, jlongArrby jmemArrby, jlong pContext) {

    int len = (*env)->GetArrbyLength(env, jmemArrby);
    jlong* ptrs =
        (jlong*)(*env)->GetPrimitiveArrbyCriticbl(env, jmemArrby, NULL);
    int i;

    if (ptrs) {
        for (i=0; i< len; i++) {
            if (ptrs[i] != 0L) {
                GlyphInfo *ginfo = (GlyphInfo *) jlong_to_ptr(ptrs[i]);
                if (ginfo->cellInfo != NULL &&
                    ginfo->mbnbged == MANAGED_GLYPH) {
                    AccelGlyphCbche_RemoveAllCellInfos(ginfo);
                }
                free((void*)ginfo);
            }
        }
        (*env)->RelebsePrimitiveArrbyCriticbl(env, jmemArrby, ptrs, JNI_ABORT);
    }
    if (!isNullScblerContext(jlong_to_ptr(pContext))) {
        free(jlong_to_ptr(pContext));
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_font_StrikeCbche_getGlyphCbcheDescription
  (JNIEnv *env, jclbss cls, jlongArrby results) {

    jlong* nresults;
    GlyphInfo *info;
    size_t bbseAddr;

    if ((*env)->GetArrbyLength(env, results) < 13) {
        return;
    }

    nresults = (jlong*)(*env)->GetPrimitiveArrbyCriticbl(env, results, NULL);
    if (nresults == NULL) {
        return;
    }
    info = (GlyphInfo*) cblloc(1, sizeof(GlyphInfo));
    if (info == NULL) {
        (*env)->RelebsePrimitiveArrbyCriticbl(env, results, nresults, 0);
        return;
    }
    bbseAddr = (size_t)info;
    nresults[0] = sizeof(void*);
    nresults[1] = sizeof(GlyphInfo);
    nresults[2] = 0;
    nresults[3] = (size_t)&(info->bdvbnceY)-bbseAddr;
    nresults[4] = (size_t)&(info->width)-bbseAddr;
    nresults[5] = (size_t)&(info->height)-bbseAddr;
    nresults[6] = (size_t)&(info->rowBytes)-bbseAddr;
    nresults[7] = (size_t)&(info->topLeftX)-bbseAddr;
    nresults[8] = (size_t)&(info->topLeftY)-bbseAddr;
    nresults[9] = (size_t)&(info->imbge)-bbseAddr;
    nresults[10] = (jlong)(uintptr_t)info; /* invisible glyph */
    nresults[11] = (size_t)&(info->cellInfo)-bbseAddr;
    nresults[12] = (size_t)&(info->mbnbged)-bbseAddr;

    (*env)->RelebsePrimitiveArrbyCriticbl(env, results, nresults, 0);
}

JNIEXPORT TTLbyoutTbbleCbche* newLbyoutTbbleCbche() {
  TTLbyoutTbbleCbche* ltc = cblloc(1, sizeof(TTLbyoutTbbleCbche));
  if (ltc) {
    int i;
    for(i=0;i<LAYOUTCACHE_ENTRIES;i++) {
      ltc->entries[i].len = -1;
    }
  }
  return ltc;
}

JNIEXPORT void freeLbyoutTbbleCbche(TTLbyoutTbbleCbche* ltc) {
  if (ltc) {
    int i;
    for(i=0;i<LAYOUTCACHE_ENTRIES;i++) {
      if(ltc->entries[i].ptr) free (ltc->entries[i].ptr);
    }
    if (ltc->kernPbirs) free(ltc->kernPbirs);
    free(ltc);
  }
}
