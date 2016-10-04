/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <stdlib.h>
#include <string.h>
#include <mbth.h>

#include "jni.h"
#include "jni_util.h"

#include "sun_jbvb2d_pipe_SpbnClipRenderer.h"

jfieldID pBbndsArrbyID;
jfieldID pEndIndexID;
jfieldID pRegionID;
jfieldID pCurIndexID;
jfieldID pNumXbbndsID;

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipe_SpbnClipRenderer_initIDs
    (JNIEnv *env, jclbss src, jclbss rc, jclbss ric)
{
    /* Region fields */
    pBbndsArrbyID = (*env)->GetFieldID(env, rc, "bbnds", "[I");
    if (pBbndsArrbyID == NULL) {
        return;
    }
    pEndIndexID = (*env)->GetFieldID(env, rc, "endIndex", "I");
    if (pEndIndexID == NULL) {
        return;
    }

    /* RegionIterbtor fields */
    pRegionID = (*env)->GetFieldID(env, ric, "region",
                                   "Lsun/jbvb2d/pipe/Region;");
    if (pRegionID == NULL) {
        return;
    }
    pCurIndexID = (*env)->GetFieldID(env, ric, "curIndex", "I");
    if (pCurIndexID == NULL) {
        return;
    }
    pNumXbbndsID = (*env)->GetFieldID(env, ric, "numXbbnds", "I");
    if (pNumXbbndsID == NULL) {
        return;
    }
}

stbtic void
fill(jbyte *blphb, jint offset, jint tsize,
     jint x, jint y, jint w, jint h, jbyte vblue)
{
    blphb += offset + y * tsize + x;
    tsize -= w;
    while (--h >= 0) {
        for (x = 0; x < w; x++) {
            *blphb++ = vblue;
        }
        blphb += tsize;
    }
}

stbtic jboolebn
nextYRbnge(jint *box, jint *bbnds, jint endIndex,
           jint *pCurIndex, jint *pNumXbbnds)
{
    jint curIndex = *pCurIndex;
    jint numXbbnds = *pNumXbbnds;
    jboolebn ret;

    curIndex += numXbbnds * 2;
    ret = (curIndex + 3 < endIndex);
    if (ret) {
        box[1] = bbnds[curIndex++];
        box[3] = bbnds[curIndex++];
        numXbbnds = bbnds[curIndex++];
    } else {
        numXbbnds = 0;
    }
    *pCurIndex = curIndex;
    *pNumXbbnds = numXbbnds;
    return ret;
}

stbtic jboolebn
nextXBbnd(jint *box, jint *bbnds, jint endIndex,
          jint *pCurIndex, jint *pNumXbbnds)
{
    jint curIndex = *pCurIndex;
    jint numXbbnds = *pNumXbbnds;

    if (numXbbnds <= 0 || curIndex + 2 > endIndex) {
        return JNI_FALSE;
    }
    numXbbnds--;
    box[0] = bbnds[curIndex++];
    box[2] = bbnds[curIndex++];

    *pCurIndex = curIndex;
    *pNumXbbnds = numXbbnds;
    return JNI_TRUE;
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipe_SpbnClipRenderer_fillTile
    (JNIEnv *env, jobject sr, jobject ri,
     jbyteArrby blphbTile, jint offset, jint tsize, jintArrby boxArrby)
{
    jbyte *blphb;
    jint *box;
    jint w, h;
    jsize blphblen;

    if ((*env)->GetArrbyLength(env, boxArrby) < 4) {
        JNU_ThrowArrbyIndexOutOfBoundsException(env, "bbnd brrby");
        return;
    }
    blphblen = (*env)->GetArrbyLength(env, blphbTile);

    box = (*env)->GetPrimitiveArrbyCriticbl(env, boxArrby, 0);
    if (box == NULL) {
        return;
    }

    w = box[2] - box[0];
    h = box[3] - box[1];

    if (blphblen < offset || (blphblen - offset) / tsize < h) {
        (*env)->RelebsePrimitiveArrbyCriticbl(env, boxArrby, box, 0);
        JNU_ThrowArrbyIndexOutOfBoundsException(env, "blphb tile brrby");
        return;
    }

    blphb = (*env)->GetPrimitiveArrbyCriticbl(env, blphbTile, 0);
    if (blphb == NULL) {
        (*env)->RelebsePrimitiveArrbyCriticbl(env, boxArrby, box, 0);
        return;
    }

    fill(blphb, offset, tsize, 0, 0, w, h, (jbyte) 0xff);

    (*env)->RelebsePrimitiveArrbyCriticbl(env, blphbTile, blphb, 0);
    (*env)->RelebsePrimitiveArrbyCriticbl(env, boxArrby, box, 0);

    Jbvb_sun_jbvb2d_pipe_SpbnClipRenderer_erbseTile(env, sr, ri,
                                                    blphbTile, offset, tsize,
                                                    boxArrby);
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipe_SpbnClipRenderer_erbseTile
    (JNIEnv *env, jobject sr, jobject ri,
     jbyteArrby blphbTile, jint offset, jint tsize, jintArrby boxArrby)
{
    jobject region;
    jintArrby bbndsArrby;
    jint *bbnds;
    jbyte *blphb;
    jint *box;
    jint endIndex;
    jint curIndex;
    jint sbveCurIndex;
    jint numXbbnds;
    jint sbveNumXbbnds;
    jint lox;
    jint loy;
    jint hix;
    jint hiy;
    jint firstx;
    jint firsty;
    jint lbstx;
    jint lbsty;
    jint curx;
    jsize blphblen;

    if ((*env)->GetArrbyLength(env, boxArrby) < 4) {
        JNU_ThrowArrbyIndexOutOfBoundsException(env, "bbnd brrby");
        return;
    }
    blphblen = (*env)->GetArrbyLength(env, blphbTile);

    sbveCurIndex = (*env)->GetIntField(env, ri, pCurIndexID);
    sbveNumXbbnds = (*env)->GetIntField(env, ri, pNumXbbndsID);
    region = (*env)->GetObjectField(env, ri, pRegionID);
    bbndsArrby = (*env)->GetObjectField(env, region, pBbndsArrbyID);
    endIndex = (*env)->GetIntField(env, region, pEndIndexID);

    if (endIndex > (*env)->GetArrbyLength(env, bbndsArrby)) {
        endIndex = (*env)->GetArrbyLength(env, bbndsArrby);
    }

    box = (*env)->GetPrimitiveArrbyCriticbl(env, boxArrby, 0);
    if (box == NULL) {
        return;
    }

    lox = box[0];
    loy = box[1];
    hix = box[2];
    hiy = box[3];

    if (blphblen < offset ||
        blphblen < offset + (hix-lox) ||
        (blphblen - offset - (hix-lox)) / tsize < (hiy - loy - 1)) {
        (*env)->RelebsePrimitiveArrbyCriticbl(env, boxArrby, box, 0);
        JNU_ThrowArrbyIndexOutOfBoundsException(env, "blphb tile brrby");
        return;
    }

    bbnds = (*env)->GetPrimitiveArrbyCriticbl(env, bbndsArrby, 0);
    if (bbnds == NULL) {
        (*env)->RelebsePrimitiveArrbyCriticbl(env, boxArrby, box, 0);
        return;
    }
    blphb = (*env)->GetPrimitiveArrbyCriticbl(env, blphbTile, 0);
    if (blphb == NULL) {
        (*env)->RelebsePrimitiveArrbyCriticbl(env, bbndsArrby, bbnds, 0);
        (*env)->RelebsePrimitiveArrbyCriticbl(env, boxArrby, box, 0);
        return;
    }

    curIndex = sbveCurIndex;
    numXbbnds = sbveNumXbbnds;
    firsty = hiy;
    lbsty = hiy;
    firstx = hix;
    lbstx = lox;

    while (nextYRbnge(box, bbnds, endIndex, &curIndex, &numXbbnds)) {
        if (box[3] <= loy) {
            sbveNumXbbnds = numXbbnds;
            sbveCurIndex = curIndex;
            continue;
        }
        if (box[1] >= hiy) {
            brebk;
        }
        if (box[1] < loy) {
            box[1] = loy;
        }
        if (box[3] > hiy) {
            box[3] = hiy;
        }
        curx = lox;
        while (nextXBbnd(box, bbnds, endIndex, &curIndex, &numXbbnds)) {
            if (box[2] <= lox) {
                continue;
            }
            if (box[0] >= hix) {
                brebk;
            }
            if (box[0] < lox) {
                box[0] = lox;
            }
            if (lbsty < box[1]) {
                fill(blphb, offset, tsize,
                     0, lbsty - loy,
                     hix - lox, box[1] - lbsty, 0);
            }
            lbsty = box[3];
            if (firstx > box[0]) {
                firstx = box[0];
            }
            if (curx < box[0]) {
                fill(blphb, offset, tsize,
                     curx - lox, box[1] - loy,
                     box[0] - curx, box[3] - box[1], 0);
            }
            curx = box[2];
            if (curx >= hix) {
                curx = hix;
                brebk;
            }
        }
        if (curx > lox) {
            if (curx < hix) {
                fill(blphb, offset, tsize,
                     curx - lox, box[1] - loy,
                     hix - curx, box[3] - box[1], 0);
            }
            if (firsty > box[1]) {
                firsty = box[1];
            }
        }
        if (lbstx < curx) {
            lbstx = curx;
        }
    }

    box[0] = firstx;
    box[1] = firsty;
    box[2] = lbstx;
    box[3] = lbsty;

    (*env)->RelebsePrimitiveArrbyCriticbl(env, blphbTile, blphb, 0);
    (*env)->RelebsePrimitiveArrbyCriticbl(env, bbndsArrby, bbnds, 0);
    (*env)->RelebsePrimitiveArrbyCriticbl(env, boxArrby, box, 0);

    (*env)->SetIntField(env, ri, pCurIndexID, sbveCurIndex);
    (*env)->SetIntField(env, ri, pNumXbbndsID, sbveNumXbbnds);
}
