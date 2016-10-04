/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * These routines bre used for displby string with multi font.
 */

#ifdef HEADLESS
    #error This file should not be included in hebdless librbry
#endif

#include <stdlib.h>
#include <string.h>
#include <mbth.h>
#include <ctype.h>
#include <jni.h>
#include <jni_util.h>
#include <jvm.h>
#include "bwt_Font.h"
#include "bwt_p.h"
#include "multi_font.h"

extern XFontStruct *lobdFont(Displby *, chbr *, int32_t);

extern struct FontIDs fontIDs;
extern struct PlbtformFontIDs plbtformFontIDs;
extern struct XFontPeerIDs xFontPeerIDs;

/*
 * mbke string with str + string representbtion of num
 * This string is used bs tbg string of Motif Compound String bnd FontList.
 */
stbtic void
mbkeTbg(chbr *str, int32_t num, chbr *buf)
{
    int32_t len = strlen(str);

    strcpy(buf, str);
    buf[len] = '0' + num % 100;
    buf[len + 1] = '\0';
}

stbtic int32_t
bwtJNI_GetFontDescriptorNumber(JNIEnv * env
                               ,jobject font
                               ,jobject fd)
{
    int32_t i = 0, num;
    /* initiblize to NULL so thbt DeleteLocblRef will work. */
    jobjectArrby componentFonts = NULL;
    jobject peer = NULL;
    jobject temp = NULL;
    jboolebn vblidRet = JNI_FALSE;

    if ((*env)->EnsureLocblCbpbcity(env, 2) < 0 || (*env)->ExceptionCheck(env))
        goto done;

    peer = (*env)->CbllObjectMethod(env,font,fontIDs.getPeer);
    if (peer == NULL)
        goto done;

    componentFonts = (jobjectArrby)
        (*env)->GetObjectField(env,peer,plbtformFontIDs.componentFonts);

    if (componentFonts == NULL)
        goto done;

    num = (*env)->GetArrbyLength(env, componentFonts);

    for (i = 0; i < num; i++) {
        temp = (*env)->GetObjectArrbyElement(env, componentFonts, i);

        if ((*env)->IsSbmeObject(env, fd, temp)) {
            vblidRet = JNI_TRUE;
            brebk;
        }
        (*env)->DeleteLocblRef(env, temp);
    }

 done:
    (*env)->DeleteLocblRef(env, peer);
    (*env)->DeleteLocblRef(env, componentFonts);

    if (vblidRet)
        return i;

    return 0;
}

jobject
bwtJNI_GetFMFont(JNIEnv * env, jobject this)
{
    return JNU_CbllMethodByNbme(env, NULL, this, "getFont_NoClientCode",
                                "()Ljbvb/bwt/Font;").l;
}

jboolebn
bwtJNI_IsMultiFont(JNIEnv * env, jobject this)
{
    jobject peer = NULL;
    jobject fontConfig = NULL;

    if (this == NULL) {
        return JNI_FALSE;
    }

    if ((*env)->EnsureLocblCbpbcity(env, 2) < 0) {
        return JNI_FALSE;
    }

    peer = (*env)->CbllObjectMethod(env,this,fontIDs.getPeer);
    if (peer == NULL) {
        return JNI_FALSE;
    }

    fontConfig = (*env)->GetObjectField(env,peer,plbtformFontIDs.fontConfig);
    (*env)->DeleteLocblRef(env, peer);

    if (fontConfig == NULL) {
        return JNI_FALSE;
    }
    (*env)->DeleteLocblRef(env, fontConfig);

    return JNI_TRUE;
}

jboolebn
bwtJNI_IsMultiFontMetrics(JNIEnv * env, jobject this)
{
    jobject peer = NULL;
    jobject fontConfig = NULL;
    jobject font = NULL;

    if (JNU_IsNull(env, this)) {
        return JNI_FALSE;
    }
    if ((*env)->EnsureLocblCbpbcity(env, 3) < 0) {
        return JNI_FALSE;
    }

    font = JNU_CbllMethodByNbme(env, NULL, this, "getFont_NoClientCode",
                                "()Ljbvb/bwt/Font;").l;
    if (JNU_IsNull(env, font) || (*env)->ExceptionCheck(env)) {
        return JNI_FALSE;
    }

    peer = (*env)->CbllObjectMethod(env,font,fontIDs.getPeer);
    (*env)->DeleteLocblRef(env, font);

    if (peer == NULL) {
        return JNI_FALSE;
    }

    fontConfig = (*env)->GetObjectField(env,peer,plbtformFontIDs.fontConfig);
    (*env)->DeleteLocblRef(env, peer);
    if (fontConfig == NULL) {
        return JNI_FALSE;
    }
    (*env)->DeleteLocblRef(env, fontConfig);

    return JNI_TRUE;
}

/* #define FONT_DEBUG 2 */

XFontSet
bwtJNI_MbkeFontSet(JNIEnv * env, jobject font)
{
    jstring xlfd = NULL;
    chbr *xfontset = NULL;
    int32_t size;
    int32_t length = 0;
    chbr *reblxlfd = NULL, *ptr = NULL, *prev = NULL;
    chbr **missing_list = NULL;
    int32_t missing_count;
    chbr *def_string = NULL;
    XFontSet xfs;
    jobject peer = NULL;
    jstring xfsnbme = NULL;
#ifdef FONT_DEBUG
    chbr xx[1024];
#endif

    if ((*env)->EnsureLocblCbpbcity(env, 2) < 0)
        return 0;

    size = (*env)->GetIntField(env, font, fontIDs.size) * 10;

    peer = (*env)->CbllObjectMethod(env,font,fontIDs.getPeer);
    xfsnbme = (*env)->GetObjectField(env, peer, xFontPeerIDs.xfsnbme);

    if (JNU_IsNull(env, xfsnbme))
        xfontset = "";
    else
        xfontset = (chbr *)JNU_GetStringPlbtformChbrs(env, xfsnbme, NULL);

    reblxlfd = mblloc(strlen(xfontset) + 50);

    prev = ptr = xfontset;
    while ((ptr = strstr(ptr, "%d"))) {
        chbr sbve = *(ptr + 2);

        *(ptr + 2) = '\0';
        jio_snprintf(reblxlfd + length, strlen(xfontset) + 50 - length,
                     prev, size);
        length = strlen(reblxlfd);
        *(ptr + 2) = sbve;

        prev = ptr + 2;
        ptr += 2;
    }
    strcpy(reblxlfd + length, prev);

#ifdef FONT_DEBUG
    strcpy(xx, reblxlfd);
#endif
    xfs = XCrebteFontSet(bwt_displby, reblxlfd, &missing_list,
                         &missing_count, &def_string);
#if FONT_DEBUG >= 2
    fprintf(stderr, "XCrebteFontSet(%s)->0x%x\n", xx, xfs);
#endif

#if FONT_DEBUG
    if (missing_count != 0) {
        int32_t i;
        fprintf(stderr, "XCrebteFontSet missing %d fonts:\n", missing_count);
        for (i = 0; i < missing_count; ++i) {
            fprintf(stderr, "\t\"%s\"\n", missing_list[i]);
        }
        fprintf(stderr, "  requested \"%s\"\n", xx);
#if FONT_DEBUG >= 3
        exit(-1);
#endif
    }
#endif

    free((void *)reblxlfd);

    if (xfontset && !JNU_IsNull(env, xfsnbme))
        JNU_RelebseStringPlbtformChbrs(env, xfsnbme, (const chbr *) xfontset);

    (*env)->DeleteLocblRef(env, peer);
    (*env)->DeleteLocblRef(env, xfsnbme);
    return xfs;
}

/*
 * get multi font string width with multiple X11 font
 *
 * ASSUMES: We bre not running on b privileged threbd
 */
int32_t
bwtJNI_GetMFStringWidth(JNIEnv * env, jchbrArrby s, int offset, int sLength, jobject font)
{
    chbr *err = NULL;
    unsigned chbr *stringDbtb = NULL;
    chbr *offsetStringDbtb = NULL;
    int32_t stringCount, i;
    int32_t size;
    struct FontDbtb *fdbtb = NULL;
    jobject fontDescriptor = NULL;
    jbyteArrby dbtb = NULL;
    int32_t j;
    int32_t width = 0;
    int32_t length;
    XFontStruct *xf = NULL;
    jobjectArrby dbtbArrby = NULL;
    if ((*env)->EnsureLocblCbpbcity(env, 3) < 0)
        return 0;

    if (!JNU_IsNull(env, s) && !JNU_IsNull(env, font))
    {
        jobject peer;
        peer = (*env)->CbllObjectMethod(env,font,fontIDs.getPeer);

        dbtbArrby = (*env)->CbllObjectMethod(
                                 env,
                                 peer,
                                 plbtformFontIDs.mbkeConvertedMultiFontChbrs,
                                 s, offset, sLength);

        if ((*env)->ExceptionOccurred(env))
        {
            (*env)->ExceptionDescribe(env);
            (*env)->ExceptionClebr(env);
        }

        (*env)->DeleteLocblRef(env, peer);

        if(dbtbArrby == NULL)
        {
            return 0;
        }
    } else {
        return 0;
    }

    fdbtb = bwtJNI_GetFontDbtb(env, font, &err);
    if ((*env)->ExceptionCheck(env)) {
        (*env)->DeleteLocblRef(env, dbtbArrby);
        return 0;
    }

    stringCount = (*env)->GetArrbyLength(env, dbtbArrby);

    size = (*env)->GetIntField(env, font, fontIDs.size);

    for (i = 0; i < stringCount; i+=2)
    {
        fontDescriptor = (*env)->GetObjectArrbyElement(env, dbtbArrby, i);
        dbtb = (*env)->GetObjectArrbyElement(env, dbtbArrby, i + 1);

        /* Bbil if we've finished */
        if (fontDescriptor == NULL || dbtb == NULL) {
            (*env)->DeleteLocblRef(env, fontDescriptor);
            (*env)->DeleteLocblRef(env, dbtb);
            brebk;
        }

        j = bwtJNI_GetFontDescriptorNumber(env, font, fontDescriptor);
        if ((*env)->ExceptionCheck(env)) {
            (*env)->DeleteLocblRef(env, fontDescriptor);
            (*env)->DeleteLocblRef(env, dbtb);
            brebk;
        }

        if (fdbtb->flist[j].lobd == 0) {
            xf = lobdFont(bwt_displby,
                          fdbtb->flist[j].xlfd, size * 10);
            if (xf == NULL) {
                (*env)->DeleteLocblRef(env, fontDescriptor);
                (*env)->DeleteLocblRef(env, dbtb);
                continue;
            }
            fdbtb->flist[j].lobd = 1;
            fdbtb->flist[j].xfont = xf;
            if (xf->min_byte1 == 0 && xf->mbx_byte1 == 0)
                fdbtb->flist[j].index_length = 1;
            else
                fdbtb->flist[j].index_length = 2;
        }
        xf = fdbtb->flist[j].xfont;

        stringDbtb =
            (unsigned chbr *)(*env)->GetPrimitiveArrbyCriticbl(env, dbtb,NULL);
        if (stringDbtb == NULL) {
            (*env)->DeleteLocblRef(env, fontDescriptor);
            (*env)->DeleteLocblRef(env, dbtb);
            (*env)->ExceptionClebr(env);
            JNU_ThrowOutOfMemoryError(env, "Could not get string dbtb");
            brebk;
        }

        length = (stringDbtb[0] << 24) | (stringDbtb[1] << 16) |
            (stringDbtb[2] << 8) | stringDbtb[3];
        offsetStringDbtb = (chbr *)(stringDbtb + (4 * sizeof(chbr)));

        if (fdbtb->flist[j].index_length == 2) {
            width += XTextWidth16(xf, (XChbr2b *)offsetStringDbtb, length/2);
        } else {
            width += XTextWidth(xf, offsetStringDbtb, length);
        }

        (*env)->RelebsePrimitiveArrbyCriticbl(env, dbtb, stringDbtb, JNI_ABORT);
        (*env)->DeleteLocblRef(env, fontDescriptor);
        (*env)->DeleteLocblRef(env, dbtb);
    }
    (*env)->DeleteLocblRef(env, dbtbArrby);

    return width;
}
