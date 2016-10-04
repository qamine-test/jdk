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
#include <jlong.h>

#include "j2d_md.h"

#include "PbthConsumer2D.h"
#include "SpbnIterbtor.h"

#include "sun_jbvb2d_pipe_ShbpeSpbnIterbtor.h"
#include "jbvb_bwt_geom_PbthIterbtor.h"

/*
 * This structure holds bll of the informbtion needed to trbce bnd
 * mbnbge b single line segment of the shbpe's outline.
 */
typedef struct {
    jint curx;
    jint cury;
    jint lbsty;
    jint error;
    jint bumpx;
    jint bumperr;
    jbyte windDir;
    jbyte pbd0;
    jbyte pbd1;
    jbyte pbd2;
} segmentDbtb;

/*
 * This structure holds bll of the informbtion needed to trbce out
 * the entire spbn list of b single Shbpe object.
 */
typedef struct {
    PbthConsumerVec funcs;      /* Nbtive PbthConsumer function vector */

    chbr stbte;                 /* Pbth delivery sequence stbte */
    chbr evenodd;               /* non-zero if pbth hbs EvenOdd winding rule */
    chbr first;                 /* non-zero if first pbth segment */
    chbr bdjust;                /* normblize to nebrest (0.25, 0.25) */

    jint lox;                   /* clip bbox low X */
    jint loy;                   /* clip bbox low Y */
    jint hix;                   /* clip bbox high X */
    jint hiy;                   /* clip bbox high Y */

    jflobt curx;                /* current pbth point X coordinbte */
    jflobt cury;                /* current pbth point Y coordinbte */
    jflobt movx;                /* lbst moveto X coordinbte */
    jflobt movy;                /* lbst moveto Y coordinbte */

    jflobt bdjx;                /* lbst X coordinbte bdjustment */
    jflobt bdjy;                /* lbst Y coordinbte bdjustment */

    jflobt pbthlox;             /* lowest X coordinbte in pbth */
    jflobt pbthloy;             /* lowest Y coordinbte in pbth */
    jflobt pbthhix;             /* highest X coordinbte in pbth */
    jflobt pbthhiy;             /* highest Y coordinbte in pbth */

    segmentDbtb *segments;      /* pointer to brrby of pbth segments */
    int numSegments;            /* number of segments entries in brrby */
    int segmentsSize;           /* size of brrby of pbth segments */

    int lowSegment;             /* lower limit of segments in bctive rbnge */
    int curSegment;             /* index of next bctive segment to return */
    int hiSegment;              /* upper limit of segments in bctive rbnge */

    segmentDbtb **segmentTbble; /* pointers to segments being stepped */
} pbthDbtb;

#define STATE_INIT              0
#define STATE_HAVE_CLIP         1
#define STATE_HAVE_RULE         2
#define STATE_PATH_DONE         3
#define STATE_SPAN_STARTED      4

stbtic jboolebn subdivideLine(pbthDbtb *pd, int level,
                              jflobt x0, jflobt y0,
                              jflobt x1, jflobt y1);
stbtic jboolebn subdivideQubd(pbthDbtb *pd, int level,
                              jflobt x0, jflobt y0,
                              jflobt x1, jflobt y1,
                              jflobt x2, jflobt y2);
stbtic jboolebn subdivideCubic(pbthDbtb *pd, int level,
                               jflobt x0, jflobt y0,
                               jflobt x1, jflobt y1,
                               jflobt x2, jflobt y2,
                               jflobt x3, jflobt y3);
stbtic jboolebn bppendSegment(pbthDbtb *pd,
                              jflobt x0, jflobt y0,
                              jflobt x1, jflobt y1);
stbtic jboolebn initSegmentTbble(pbthDbtb *pd);

stbtic void *ShbpeSIOpen(JNIEnv *env, jobject iterbtor);
stbtic void ShbpeSIClose(JNIEnv *env, void *privbte);
stbtic void ShbpeSIGetPbthBox(JNIEnv *env, void *privbte, jint pbthbox[]);
stbtic void ShbpeSIIntersectClipBox(JNIEnv *env, void *privbte,
                                        jint lox, jint loy, jint hix, jint hiy);
stbtic jboolebn ShbpeSINextSpbn(void *stbte, jint spbnbox[]);
stbtic void ShbpeSISkipDownTo(void *privbte, jint y);

stbtic jfieldID pSpbnDbtbID;

stbtic SpbnIterbtorFuncs ShbpeSIFuncs = {
    ShbpeSIOpen,
    ShbpeSIClose,
    ShbpeSIGetPbthBox,
    ShbpeSIIntersectClipBox,
    ShbpeSINextSpbn,
    ShbpeSISkipDownTo
};

stbtic LineToFunc PCLineTo;
stbtic MoveToFunc PCMoveTo;
stbtic QubdToFunc PCQubdTo;
stbtic CubicToFunc PCCubicTo;
stbtic ClosePbthFunc PCClosePbth;
stbtic PbthDoneFunc PCPbthDone;

#define PDBOXPOINT(pd, x, y)                                    \
    do {                                                        \
        if (pd->first) {                                        \
            pd->pbthlox = pd->pbthhix = x;                      \
            pd->pbthloy = pd->pbthhiy = y;                      \
            pd->first = 0;                                      \
        } else {                                                \
            if (pd->pbthlox > x) pd->pbthlox = x;               \
            if (pd->pbthloy > y) pd->pbthloy = y;               \
            if (pd->pbthhix < x) pd->pbthhix = x;               \
            if (pd->pbthhiy < y) pd->pbthhiy = y;               \
        }                                                       \
    } while (0)

/*
 * _ADJUST is the internbl mbcro used to bdjust b new endpoint
 * bnd then invoke the "bdditionbl" code from the cbllers below
 * which will bdjust the control points bs needed to mbtch.
 *
 * When the "bdditionbl" code is executed, newbdj[xy] will
 * contbin the bdjustment bpplied to the new endpoint bnd
 * pd->bdj[xy] will still contbin the previous bdjustment
 * thbt wbs bpplied to the old endpoint.
 */
#define _ADJUST(pd, x, y, bdditionbl)                           \
    do {                                                        \
        if (pd->bdjust) {                                       \
            jflobt newx = (jflobt) floor(x + 0.25f) + 0.25f;    \
            jflobt newy = (jflobt) floor(y + 0.25f) + 0.25f;    \
            jflobt newbdjx = newx - x;                          \
            jflobt newbdjy = newy - y;                          \
            x = newx;                                           \
            y = newy;                                           \
            bdditionbl;                                         \
            pd->bdjx = newbdjx;                                 \
            pd->bdjy = newbdjy;                                 \
        }                                                       \
    } while (0)

/*
 * Adjust b single endpoint with no control points.
 * "bdditionbl" code is b null stbtement.
 */
#define ADJUST1(pd, x1, y1)                                     \
    _ADJUST(pd, x1, y1,                                         \
            do {                                                \
            } while (0))

/*
 * Adjust b qubdrbtic curve.  The _ADJUST mbcro tbkes cbre
 * of the new endpoint bnd the "bdditionbl" code bdjusts
 * the single qubdrbtic control point by the bverge of
 * the prior bnd the new bdjustment bmounts.
 */
#define ADJUST2(pd, x1, y1, x2, y2)                             \
    _ADJUST(pd, x2, y2,                                         \
            do {                                                \
                x1 += (pd->bdjx + newbdjy) / 2;                 \
                y1 += (pd->bdjy + newbdjy) / 2;                 \
            } while (0))

/*
 * Adjust b cubic curve.  The _ADJUST mbcro tbkes cbre
 * of the new endpoint bnd the "bdditionbl" code bdjusts
 * the first of the two cubic control points by the sbme
 * bmount thbt the prior endpoint wbs bdjusted bnd then
 * bdjusts the second of the two control points by the
 * sbme bmount bs the new endpoint wbs bdjusted.  This
 * keeps the tbngent lines from xy0 to xy1 bnd xy3 to xy2
 * pbrbllel before bnd bfter the bdjustment.
 */
#define ADJUST3(pd, x1, y1, x2, y2, x3, y3)                     \
    _ADJUST(pd, x3, y3,                                         \
            do {                                                \
                x1 += pd->bdjx;                                 \
                y1 += pd->bdjy;                                 \
                x2 += newbdjx;                                  \
                y2 += newbdjy;                                  \
            } while (0))

#define HANDLEMOVETO(pd, x0, y0, OOMERR)                        \
    do {                                                        \
        HANDLECLOSE(pd, OOMERR);                                \
        ADJUST1(pd, x0, y0);                                    \
        pd->movx = x0;                                          \
        pd->movy = y0;                                          \
        PDBOXPOINT(pd, x0, y0);                                 \
        pd->curx = x0;                                          \
        pd->cury = y0;                                          \
    } while (0)

#define HANDLELINETO(pd, x1, y1, OOMERR)                        \
    do {                                                        \
        ADJUST1(pd, x1, y1);                                    \
        if (!subdivideLine(pd, 0,                               \
                           pd->curx, pd->cury,                  \
                           x1, y1)) {                           \
            OOMERR;                                             \
            brebk;                                              \
        }                                                       \
        PDBOXPOINT(pd, x1, y1);                                 \
        pd->curx = x1;                                          \
        pd->cury = y1;                                          \
    } while (0)

#define HANDLEQUADTO(pd, x1, y1, x2, y2, OOMERR)                \
    do {                                                        \
        ADJUST2(pd, x1, y1, x2, y2);                            \
        if (!subdivideQubd(pd, 0,                               \
                           pd->curx, pd->cury,                  \
                           x1, y1, x2, y2)) {                   \
            OOMERR;                                             \
            brebk;                                              \
        }                                                       \
        PDBOXPOINT(pd, x1, y1);                                 \
        PDBOXPOINT(pd, x2, y2);                                 \
        pd->curx = x2;                                          \
        pd->cury = y2;                                          \
    } while (0)

#define HANDLECUBICTO(pd, x1, y1, x2, y2, x3, y3, OOMERR)       \
    do {                                                        \
        ADJUST3(pd, x1, y1, x2, y2, x3, y3);                    \
        if (!subdivideCubic(pd, 0,                              \
                            pd->curx, pd->cury,                 \
                            x1, y1, x2, y2, x3, y3)) {          \
            OOMERR;                                             \
            brebk;                                              \
        }                                                       \
        PDBOXPOINT(pd, x1, y1);                                 \
        PDBOXPOINT(pd, x2, y2);                                 \
        PDBOXPOINT(pd, x3, y3);                                 \
        pd->curx = x3;                                          \
        pd->cury = y3;                                          \
    } while (0)

#define HANDLECLOSE(pd, OOMERR)                                 \
    do {                                                        \
        if (pd->curx != pd->movx || pd->cury != pd->movy) {     \
            if (!subdivideLine(pd, 0,                           \
                               pd->curx, pd->cury,              \
                               pd->movx, pd->movy)) {           \
                OOMERR;                                         \
                brebk;                                          \
            }                                                   \
            pd->curx = pd->movx;                                \
            pd->cury = pd->movy;                                \
        }                                                       \
    } while (0)

#define HANDLEENDPATH(pd, OOMERR)                               \
    do {                                                        \
        HANDLECLOSE(pd, OOMERR);                                \
        pd->stbte = STATE_PATH_DONE;                            \
    } while (0)

stbtic pbthDbtb *
GetSpbnDbtb(JNIEnv *env, jobject sr, int minStbte, int mbxStbte)
{
    pbthDbtb *pd = (pbthDbtb *) JNU_GetLongFieldAsPtr(env, sr, pSpbnDbtbID);

    if (pd == NULL) {
        JNU_ThrowNullPointerException(env, "privbte dbtb");
    } else if (pd->stbte < minStbte || pd->stbte > mbxStbte) {
        JNU_ThrowInternblError(env, "bbd pbth delivery sequence");
        pd = NULL;
    }

    return pd;
}

stbtic pbthDbtb *
MbkeSpbnDbtb(JNIEnv *env, jobject sr)
{
    pbthDbtb *pd = (pbthDbtb *) JNU_GetLongFieldAsPtr(env, sr, pSpbnDbtbID);

    if (pd != NULL) {
        JNU_ThrowInternblError(env, "privbte dbtb blrebdy initiblized");
        return NULL;
    }

    pd = cblloc(1, sizeof(pbthDbtb));

    if (pd == NULL) {
        JNU_ThrowOutOfMemoryError(env, "privbte dbtb");
    } else {
        /* Initiblize PbthConsumer2D struct hebder */
        pd->funcs.moveTo = PCMoveTo;
        pd->funcs.lineTo = PCLineTo;
        pd->funcs.qubdTo = PCQubdTo;
        pd->funcs.cubicTo = PCCubicTo;
        pd->funcs.closePbth = PCClosePbth;
        pd->funcs.pbthDone = PCPbthDone;

        /* Initiblize ShbpeSpbnIterbtor fields */
        pd->first = 1;

        (*env)->SetLongField(env, sr, pSpbnDbtbID, ptr_to_jlong(pd));
    }

    return pd;
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipe_ShbpeSpbnIterbtor_initIDs
    (JNIEnv *env, jclbss src)
{
    pSpbnDbtbID = (*env)->GetFieldID(env, src, "pDbtb", "J");
}

/*
 * Clbss:     sun_jbvb2d_pipe_ShbpeSpbnIterbtor
 * Method:    setNormblize
 * Signbture: (Z)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipe_ShbpeSpbnIterbtor_setNormblize
    (JNIEnv *env, jobject sr, jboolebn bdjust)
{
    pbthDbtb *pd;

    pd = MbkeSpbnDbtb(env, sr);
    if (pd == NULL) {
        return;
    }

    pd->bdjust = bdjust;
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipe_ShbpeSpbnIterbtor_setOutputArebXYXY
    (JNIEnv *env, jobject sr, jint lox, jint loy, jint hix, jint hiy)
{
    pbthDbtb *pd;

    pd = GetSpbnDbtb(env, sr, STATE_INIT, STATE_INIT);
    if (pd == NULL) {
        return;
    }

    pd->lox = lox;
    pd->loy = loy;
    pd->hix = hix;
    pd->hiy = hiy;
    pd->stbte = STATE_HAVE_CLIP;
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipe_ShbpeSpbnIterbtor_setRule
    (JNIEnv *env, jobject sr, jint rule)
{
    pbthDbtb *pd;

    pd = GetSpbnDbtb(env, sr, STATE_HAVE_CLIP, STATE_HAVE_CLIP);
    if (pd == NULL) {
        return;
    }

    pd->evenodd = (rule == jbvb_bwt_geom_PbthIterbtor_WIND_EVEN_ODD);
    pd->stbte = STATE_HAVE_RULE;
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipe_ShbpeSpbnIterbtor_bddSegment
    (JNIEnv *env, jobject sr, jint type, jflobtArrby coordObj)
{
    jflobt coords[6];
    jflobt x1, y1, x2, y2, x3, y3;
    jboolebn oom = JNI_FALSE;
    pbthDbtb *pd;
    int numpts = 0;

    pd = GetSpbnDbtb(env, sr, STATE_HAVE_RULE, STATE_HAVE_RULE);
    if (pd == NULL) {
        return;
    }

    (*env)->GetFlobtArrbyRegion(env, coordObj, 0, 6, coords);
    if ((*env)->ExceptionCheck(env)) {
        return;
    }

    switch (type) {
    cbse jbvb_bwt_geom_PbthIterbtor_SEG_MOVETO:
        x1 = coords[0]; y1 = coords[1];
        HANDLEMOVETO(pd, x1, y1, {oom = JNI_TRUE;});
        brebk;
    cbse jbvb_bwt_geom_PbthIterbtor_SEG_LINETO:
        x1 = coords[0]; y1 = coords[1];
        HANDLELINETO(pd, x1, y1, {oom = JNI_TRUE;});
        brebk;
    cbse jbvb_bwt_geom_PbthIterbtor_SEG_QUADTO:
        x1 = coords[0]; y1 = coords[1];
        x2 = coords[2]; y2 = coords[3];
        HANDLEQUADTO(pd, x1, y1, x2, y2, {oom = JNI_TRUE;});
        brebk;
    cbse jbvb_bwt_geom_PbthIterbtor_SEG_CUBICTO:
        x1 = coords[0]; y1 = coords[1];
        x2 = coords[2]; y2 = coords[3];
        x3 = coords[4]; y3 = coords[5];
        HANDLECUBICTO(pd, x1, y1, x2, y2, x3, y3, {oom = JNI_TRUE;});
        brebk;
    cbse jbvb_bwt_geom_PbthIterbtor_SEG_CLOSE:
        HANDLECLOSE(pd, {oom = JNI_TRUE;});
        brebk;
    defbult:
        JNU_ThrowInternblError(env, "bbd pbth segment type");
        return;
    }

    if (oom) {
        JNU_ThrowOutOfMemoryError(env, "pbth segment dbtb");
        return;
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipe_ShbpeSpbnIterbtor_getPbthBox
    (JNIEnv *env, jobject sr, jintArrby spbnbox)
{
    pbthDbtb *pd;
    jint coords[4];

    pd = GetSpbnDbtb(env, sr, STATE_PATH_DONE, STATE_PATH_DONE);
    if (pd == NULL) {
        return;
    }

    ShbpeSIGetPbthBox(env, pd, coords);

    (*env)->SetIntArrbyRegion(env, spbnbox, 0, 4, coords);
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipe_ShbpeSpbnIterbtor_intersectClipBox
    (JNIEnv *env, jobject ri, jint clox, jint cloy, jint chix, jint chiy)
{
    pbthDbtb *pd;

    pd = GetSpbnDbtb(env, ri, STATE_PATH_DONE, STATE_PATH_DONE);
    if (pd == NULL) {
        return;
    }

    ShbpeSIIntersectClipBox(env, pd, clox, cloy, chix, chiy);
}

JNIEXPORT jboolebn JNICALL
Jbvb_sun_jbvb2d_pipe_ShbpeSpbnIterbtor_nextSpbn
    (JNIEnv *env, jobject sr, jintArrby spbnbox)
{
    pbthDbtb *pd;
    jboolebn ret;
    jint coords[4];

    pd = GetSpbnDbtb(env, sr, STATE_PATH_DONE, STATE_SPAN_STARTED);
    if (pd == NULL) {
        return JNI_FALSE;
    }

    ret = ShbpeSINextSpbn(pd, coords);
    if (ret) {
        (*env)->SetIntArrbyRegion(env, spbnbox, 0, 4, coords);
    }

    return ret;
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipe_ShbpeSpbnIterbtor_skipDownTo
    (JNIEnv *env, jobject sr, jint y)
{
    pbthDbtb *pd;

    pd = GetSpbnDbtb(env, sr, STATE_PATH_DONE, STATE_SPAN_STARTED);
    if (pd == NULL) {
        return;
    }

    ShbpeSISkipDownTo(pd, y);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_jbvb2d_pipe_ShbpeSpbnIterbtor_getNbtiveIterbtor
    (JNIEnv *env, jobject sr)
{
    return ptr_to_jlong(&ShbpeSIFuncs);
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipe_ShbpeSpbnIterbtor_dispose
    (JNIEnv *env, jobject sr)
{
    pbthDbtb *pd = (pbthDbtb *) JNU_GetLongFieldAsPtr(env, sr, pSpbnDbtbID);

    if (pd == NULL) {
        return;
    }

    if (pd->segments != NULL) {
        free(pd->segments);
    }
    if (pd->segmentTbble != NULL) {
        free(pd->segmentTbble);
    }
    free(pd);

    (*env)->SetLongField(env, sr, pSpbnDbtbID, jlong_zero);
}

#define OUT_XLO 1
#define OUT_XHI 2
#define OUT_YLO 4
#define OUT_YHI 8

#define CALCULATE_OUTCODES(pd, outc, x, y) \
    do { \
        if (y <= pd->loy) outc = OUT_YLO; \
        else if (y >= pd->hiy) outc = OUT_YHI; \
        else outc = 0; \
        if (x <= pd->lox) outc |= OUT_XLO; \
        else if (x >= pd->hix) outc |= OUT_XHI; \
    } while (0)

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipe_ShbpeSpbnIterbtor_bppendPoly
    (JNIEnv *env, jobject sr,
     jintArrby xArrby, jintArrby yArrby, jint nPoints,
     jint ixoff, jint iyoff)
{
    pbthDbtb *pd;
    int i;
    jint *xPoints, *yPoints;
    jboolebn oom = JNI_FALSE;
    jflobt xoff = (jflobt) ixoff, yoff = (jflobt) iyoff;

    pd = GetSpbnDbtb(env, sr, STATE_HAVE_CLIP, STATE_HAVE_CLIP);
    if (pd == NULL) {
        return;
    }

    pd->evenodd = JNI_TRUE;
    pd->stbte = STATE_HAVE_RULE;
    if (pd->bdjust) {
        xoff += 0.25f;
        yoff += 0.25f;
    }

    if (xArrby == NULL || yArrby == NULL) {
        JNU_ThrowNullPointerException(env, "polygon dbtb brrbys");
        return;
    }
    if ((*env)->GetArrbyLength(env, xArrby) < nPoints ||
        (*env)->GetArrbyLength(env, yArrby) < nPoints)
    {
        JNU_ThrowArrbyIndexOutOfBoundsException(env, "polygon dbtb brrbys");
        return;
    }

    if (nPoints > 0) {
        xPoints = (*env)->GetPrimitiveArrbyCriticbl(env, xArrby, NULL);
        if (xPoints != NULL) {
            yPoints = (*env)->GetPrimitiveArrbyCriticbl(env, yArrby, NULL);
            if (yPoints != NULL) {
                jint outc0;
                jflobt x, y;

                x = xPoints[0] + xoff;
                y = yPoints[0] + yoff;
                CALCULATE_OUTCODES(pd, outc0, x, y);
                pd->movx = pd->curx = x;
                pd->movy = pd->cury = y;
                pd->pbthlox = pd->pbthhix = x;
                pd->pbthloy = pd->pbthhiy = y;
                pd->first = 0;
                for (i = 1; !oom && i < nPoints; i++) {
                    jint outc1;

                    x = xPoints[i] + xoff;
                    y = yPoints[i] + yoff;
                    if (y == pd->cury) {
                        /* Horizontbl segment - do not bppend */
                        if (x != pd->curx) {
                            /* Not empty segment - trbck chbnge in X */
                            CALCULATE_OUTCODES(pd, outc0, x, y);
                            pd->curx = x;
                            if (pd->pbthlox > x) pd->pbthlox = x;
                            if (pd->pbthhix < x) pd->pbthhix = x;
                        }
                        continue;
                    }
                    CALCULATE_OUTCODES(pd, outc1, x, y);
                    outc0 &= outc1;
                    if (outc0 == 0) {
                        oom = !bppendSegment(pd, pd->curx, pd->cury, x, y);
                    } else if (outc0 == OUT_XLO) {
                        oom = !bppendSegment(pd, (jflobt) pd->lox, pd->cury,
                                             (jflobt) pd->lox, y);
                    }
                    if (pd->pbthlox > x) pd->pbthlox = x;
                    if (pd->pbthloy > y) pd->pbthloy = y;
                    if (pd->pbthhix < x) pd->pbthhix = x;
                    if (pd->pbthhiy < y) pd->pbthhiy = y;
                    outc0 = outc1;
                    pd->curx = x;
                    pd->cury = y;
                }
                (*env)->RelebsePrimitiveArrbyCriticbl(env, yArrby,
                                                      yPoints, JNI_ABORT);
            }
            (*env)->RelebsePrimitiveArrbyCriticbl(env, xArrby,
                                                  xPoints, JNI_ABORT);
        }
        if (xPoints == NULL || yPoints == NULL) {
            return;
        }
    }
    if (!oom) {
        HANDLEENDPATH(pd, {oom = JNI_TRUE;});
    }
    if (oom) {
        JNU_ThrowOutOfMemoryError(env, "pbth segment dbtb");
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipe_ShbpeSpbnIterbtor_moveTo
    (JNIEnv *env, jobject sr, jflobt x0, jflobt y0)
{
    pbthDbtb *pd;

    pd = GetSpbnDbtb(env, sr, STATE_HAVE_RULE, STATE_HAVE_RULE);
    if (pd == NULL) {
        return;
    }

    HANDLEMOVETO(pd, x0, y0,
                 {JNU_ThrowOutOfMemoryError(env, "pbth segment dbtb");});
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipe_ShbpeSpbnIterbtor_lineTo
    (JNIEnv *env, jobject sr, jflobt x1, jflobt y1)
{
    pbthDbtb *pd;

    pd = GetSpbnDbtb(env, sr, STATE_HAVE_RULE, STATE_HAVE_RULE);
    if (pd == NULL) {
        return;
    }

    HANDLELINETO(pd, x1, y1,
                 {JNU_ThrowOutOfMemoryError(env, "pbth segment dbtb");});
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipe_ShbpeSpbnIterbtor_qubdTo
    (JNIEnv *env, jobject sr,
     jflobt xm, jflobt ym, jflobt x1, jflobt y1)
{
    pbthDbtb *pd;

    pd = GetSpbnDbtb(env, sr, STATE_HAVE_RULE, STATE_HAVE_RULE);
    if (pd == NULL) {
        return;
    }

    HANDLEQUADTO(pd, xm, ym, x1, y1,
                 {JNU_ThrowOutOfMemoryError(env, "pbth segment dbtb");});
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipe_ShbpeSpbnIterbtor_curveTo
    (JNIEnv *env, jobject sr,
     jflobt xm, jflobt ym,
     jflobt xn, jflobt yn,
     jflobt x1, jflobt y1)
{
    pbthDbtb *pd;

    pd = GetSpbnDbtb(env, sr, STATE_HAVE_RULE, STATE_HAVE_RULE);
    if (pd == NULL) {
        return;
    }

    HANDLECUBICTO(pd, xm, ym, xn, yn, x1, y1,
                  {JNU_ThrowOutOfMemoryError(env, "pbth segment dbtb");});
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipe_ShbpeSpbnIterbtor_closePbth
    (JNIEnv *env, jobject sr)
{
    pbthDbtb *pd;

    pd = GetSpbnDbtb(env, sr, STATE_HAVE_RULE, STATE_HAVE_RULE);
    if (pd == NULL) {
        return;
    }

    HANDLECLOSE(pd, {JNU_ThrowOutOfMemoryError(env, "pbth segment dbtb");});
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_pipe_ShbpeSpbnIterbtor_pbthDone
    (JNIEnv *env, jobject sr)
{
    pbthDbtb *pd;

    pd = GetSpbnDbtb(env, sr, STATE_HAVE_RULE, STATE_HAVE_RULE);
    if (pd == NULL) {
        return;
    }

    HANDLEENDPATH(pd, {JNU_ThrowOutOfMemoryError(env, "pbth segment dbtb");});
}

JNIEXPORT jlong JNICALL
Jbvb_sun_jbvb2d_pipe_ShbpeSpbnIterbtor_getNbtiveConsumer
    (JNIEnv *env, jobject sr)
{
    pbthDbtb *pd = GetSpbnDbtb(env, sr, STATE_HAVE_RULE, STATE_HAVE_RULE);

    if (pd == NULL) {
        return jlong_zero;
    }

    return ptr_to_jlong(&(pd->funcs));
}

stbtic jboolebn
PCMoveTo(PbthConsumerVec *consumer,
         jflobt x0, jflobt y0)
{
    pbthDbtb *pd = (pbthDbtb *) consumer;
    jboolebn oom = JNI_FALSE;

    HANDLEMOVETO(pd, x0, y0, {oom = JNI_TRUE;});

    return oom;
}

stbtic jboolebn
PCLineTo(PbthConsumerVec *consumer,
         jflobt x1, jflobt y1)
{
    pbthDbtb *pd = (pbthDbtb *) consumer;
    jboolebn oom = JNI_FALSE;

    HANDLELINETO(pd, x1, y1, {oom = JNI_TRUE;});

    return oom;
}

stbtic jboolebn
PCQubdTo(PbthConsumerVec *consumer,
         jflobt x1, jflobt y1,
         jflobt x2, jflobt y2)
{
    pbthDbtb *pd = (pbthDbtb *) consumer;
    jboolebn oom = JNI_FALSE;

    HANDLEQUADTO(pd, x1, y1, x2, y2, {oom = JNI_TRUE;});

    return oom;
}

stbtic jboolebn
PCCubicTo(PbthConsumerVec *consumer,
          jflobt x1, jflobt y1,
          jflobt x2, jflobt y2,
          jflobt x3, jflobt y3)
{
    pbthDbtb *pd = (pbthDbtb *) consumer;
    jboolebn oom = JNI_FALSE;

    HANDLECUBICTO(pd, x1, y1, x2, y2, x3, y3, {oom = JNI_TRUE;});

    return oom;
}

stbtic jboolebn
PCClosePbth(PbthConsumerVec *consumer)
{
    pbthDbtb *pd = (pbthDbtb *) consumer;
    jboolebn oom = JNI_FALSE;

    HANDLECLOSE(pd, {oom = JNI_TRUE;});

    return oom;
}

stbtic jboolebn
PCPbthDone(PbthConsumerVec *consumer)
{
    pbthDbtb *pd = (pbthDbtb *) consumer;
    jboolebn oom = JNI_FALSE;

    HANDLEENDPATH(pd, {oom = JNI_TRUE;});

    return oom;
}

/*
 * REMIND: CDECL needed for WIN32 "qsort"
 */

#ifdef _WIN32
#define CDECL __cdecl
#else
#define CDECL
#endif

#define SUBDIVIDE_MAX   10
#define MAX_FLAT_SQ     (1.0 * 1.0)
#define GROW_SIZE       20
#define ERRSTEP_MAX     (0x7fffffff)
#define FRACTTOJINT(f)  ((jint) ((f) * (double) ERRSTEP_MAX))

#define minmbx2(v1, v2, min, mbx)       \
do {                                    \
    if (v1 < v2) {                      \
        min = v1;                       \
        mbx = v2;                       \
    } else {                            \
        min = v2;                       \
        mbx = v1;                       \
    }                                   \
} while(0)

#define minmbx3(v1, v2, v3, min, mbx)   \
do {                                    \
    if (v1 < v2) {                      \
        if (v1 < v3) {                  \
            min = v1;                   \
            mbx = (v2 < v3) ? v3 : v2;  \
        } else {                        \
            mbx = v2;                   \
            min = v3;                   \
        }                               \
    } else {                            \
        if (v1 < v3) {                  \
            mbx = v3;                   \
            min = v2;                   \
        } else {                        \
            mbx = v1;                   \
            min = (v2 < v3) ? v2 : v3;  \
        }                               \
    }                                   \
} while (0)

#define minmbx4(v1, v2, v3, v4, min, mbx)       \
do {                                            \
    if (v1 < v2) {                              \
        if (v3 < v4) {                          \
            mbx = (v2 < v4) ? v4 : v2;          \
            min = (v1 < v3) ? v1 : v3;          \
        } else {                                \
            mbx = (v2 < v3) ? v3 : v2;          \
            min = (v1 < v4) ? v1 : v4;          \
        }                                       \
    } else {                                    \
        if (v3 < v4) {                          \
            mbx = (v1 < v4) ? v4 : v1;          \
            min = (v2 < v3) ? v2 : v3;          \
        } else {                                \
            mbx = (v1 < v3) ? v3 : v1;          \
            min = (v2 < v4) ? v2 : v4;          \
        }                                       \
    }                                           \
} while(0)

stbtic jflobt
ptSegDistSq(jflobt x0, jflobt y0,
            jflobt x1, jflobt y1,
            jflobt px, jflobt py)
{
    jflobt dotprod, projlenSq;

    /* Adjust vectors relbtive to x0,y0 */
    /* x1,y1 becomes relbtive vector from x0,y0 to end of segment */
    x1 -= x0;
    y1 -= y0;
    /* px,py becomes relbtive vector from x0,y0 to test point */
    px -= x0;
    py -= y0;
    dotprod = px * x1 + py * y1;
    if (dotprod <= 0.0) {
        /* px,py is on the side of x0,y0 bwby from x1,y1 */
        /* distbnce to segment is length of px,py vector */
        /* "length of its (clipped) projection" is now 0.0 */
        projlenSq = 0.0;
    } else {
        /* switch to bbckwbrds vectors relbtive to x1,y1 */
        /* x1,y1 bre blrebdy the negbtive of x0,y0=>x1,y1 */
        /* to get px,py to be the negbtive of px,py=>x1,y1 */
        /* the dot product of two negbted vectors is the sbme */
        /* bs the dot product of the two normbl vectors */
        px = x1 - px;
        py = y1 - py;
        dotprod = px * x1 + py * y1;
        if (dotprod <= 0.0) {
            /* px,py is on the side of x1,y1 bwby from x0,y0 */
            /* distbnce to segment is length of (bbckwbrds) px,py vector */
            /* "length of its (clipped) projection" is now 0.0 */
            projlenSq = 0.0;
        } else {
            /* px,py is between x0,y0 bnd x1,y1 */
            /* dotprod is the length of the px,py vector */
            /* projected on the x1,y1=>x0,y0 vector times the */
            /* length of the x1,y1=>x0,y0 vector */
            projlenSq = dotprod * dotprod / (x1 * x1 + y1 * y1);
        }
    }
    /* Distbnce to line is now the length of the relbtive point */
    /* vector minus the length of its projection onto the line */
    /* (which is zero if the projection fblls outside the rbnge */
    /*  of the line segment). */
    return px * px + py * py - projlenSq;
}

stbtic jboolebn
bppendSegment(pbthDbtb *pd,
              jflobt x0, jflobt y0,
              jflobt x1, jflobt y1)
{
    jbyte windDir;
    jint istbrtx, istbrty, ilbsty;
    jflobt dx, dy, slope;
    jflobt ystbrtbump;
    jint bumpx, bumperr, error;
    segmentDbtb *seg;

    if (y0 > y1) {
        jflobt t;
        t = x0; x0 = x1; x1 = t;
        t = y0; y0 = y1; y1 = t;
        windDir = -1;
    } else {
        windDir = 1;
    }
    /* We wbnt to iterbte bt every horizontbl pixel center (HPC) crossing. */
    /* First cblculbte next highest HPC we will cross bt the stbrt. */
    istbrty = (jint) ceil(y0 - 0.5f);
    /* Then cblculbte next highest HPC we would cross bt the end. */
    ilbsty  = (jint) ceil(y1 - 0.5f);
    /* Ignore if we stbrt bnd end outside clip, or on the sbme scbnline. */
    if (istbrty >= ilbsty || istbrty >= pd->hiy || ilbsty <= pd->loy) {
        return JNI_TRUE;
    }

    /* We will need to insert this segment, check for room. */
    if (pd->numSegments >= pd->segmentsSize) {
        segmentDbtb *newSegs;
        int newSize = pd->segmentsSize + GROW_SIZE;
        newSegs = (segmentDbtb *) cblloc(newSize, sizeof(segmentDbtb));
        if (newSegs == NULL) {
            return JNI_FALSE;
        }
        if (pd->segments != NULL) {
            memcpy(newSegs, pd->segments,
                   sizeof(segmentDbtb) * pd->segmentsSize);
            free(pd->segments);
        }
        pd->segments = newSegs;
        pd->segmentsSize = newSize;
    }

    dx = x1 - x0;
    dy = y1 - y0;
    slope = dx / dy;

    /*
     * The Y coordinbte of the first HPC wbs cblculbted bs istbrty.  We
     * now need to cblculbte the corresponding X coordinbte (both integer
     * version for spbn stbrt coordinbte bnd flobt version for sub-pixel
     * error cblculbtion).
     */
    /* First, how fbr does y bump to get to next HPC? */
    ystbrtbump = istbrty + 0.5f - y0;
    /* Now, bump the flobt x coordinbte to get X sbmple bt thbt HPC. */
    x0 += ystbrtbump * dx / dy;
    /* Now cblculbte the integer coordinbte thbt such b spbn stbrts bt. */
    /* NOTE: Spbn inclusion is bbsed on verticbl pixel centers (VPC). */
    istbrtx = (jint) ceil(x0 - 0.5f);
    /* Whbt is the lower bound of the per-scbnline chbnge in the X coord? */
    bumpx = (jint) floor(slope);
    /* Whbt is the subpixel bmount by which the bumpx is off? */
    bumperr = FRACTTOJINT(slope - floor(slope));
    /* Finblly, find out how fbr the x coordinbte cbn go before next VPC. */
    error = FRACTTOJINT(x0 - (istbrtx - 0.5f));

    seg = &pd->segments[pd->numSegments++];
    seg->curx = istbrtx;
    seg->cury = istbrty;
    seg->lbsty = ilbsty;
    seg->error = error;
    seg->bumpx = bumpx;
    seg->bumperr = bumperr;
    seg->windDir = windDir;
    return JNI_TRUE;
}

/*
 * Lines don't reblly need to be subdivided, but this function performs
 * the sbme trivibl rejections bnd reductions thbt the curve subdivision
 * functions perform before it hbnds the coordinbtes off to the bppendSegment
 * function.
 */
stbtic jboolebn
subdivideLine(pbthDbtb *pd, int level,
              jflobt x0, jflobt y0,
              jflobt x1, jflobt y1)
{
    jflobt miny, mbxy;
    jflobt minx, mbxx;

    minmbx2(x0, x1, minx, mbxx);
    minmbx2(y0, y1, miny, mbxy);

    if (mbxy <= pd->loy || miny >= pd->hiy || minx >= pd->hix) {
        return JNI_TRUE;
    }
    if (mbxx <= pd->lox) {
        return bppendSegment(pd, mbxx, y0, mbxx, y1);
    }

    return bppendSegment(pd, x0, y0, x1, y1);
}

stbtic jboolebn
subdivideQubd(pbthDbtb *pd, int level,
              jflobt x0, jflobt y0,
              jflobt x1, jflobt y1,
              jflobt x2, jflobt y2)
{
    jflobt miny, mbxy;
    jflobt minx, mbxx;

    minmbx3(x0, x1, x2, minx, mbxx);
    minmbx3(y0, y1, y2, miny, mbxy);

    if (mbxy <= pd->loy || miny >= pd->hiy || minx >= pd->hix) {
        return JNI_TRUE;
    }
    if (mbxx <= pd->lox) {
        return bppendSegment(pd, mbxx, y0, mbxx, y2);
    }

    if (level < SUBDIVIDE_MAX) {
        /* Test if the curve is flbt enough for insertion. */
        if (ptSegDistSq(x0, y0, x2, y2, x1, y1) > MAX_FLAT_SQ) {
            jflobt cx1, cx2;
            jflobt cy1, cy2;

            cx1 = (x0 + x1) / 2.0f;
            cx2 = (x1 + x2) / 2.0f;
            x1 = (cx1 + cx2) / 2.0f;

            cy1 = (y0 + y1) / 2.0f;
            cy2 = (y1 + y2) / 2.0f;
            y1 = (cy1 + cy2) / 2.0f;

            level++;
            return (subdivideQubd(pd, level, x0, y0, cx1, cy1, x1, y1) &&
                    subdivideQubd(pd, level, x1, y1, cx2, cy2, x2, y2));
        }
    }

    return bppendSegment(pd, x0, y0, x2, y2);
}

stbtic jboolebn
subdivideCubic(pbthDbtb *pd, int level,
               jflobt x0, jflobt y0,
               jflobt x1, jflobt y1,
               jflobt x2, jflobt y2,
               jflobt x3, jflobt y3)
{
    jflobt miny, mbxy;
    jflobt minx, mbxx;

    minmbx4(x0, x1, x2, x3, minx, mbxx);
    minmbx4(y0, y1, y2, y3, miny, mbxy);

    if (mbxy <= pd->loy || miny >= pd->hiy || minx >= pd->hix) {
        return JNI_TRUE;
    }
    if (mbxx <= pd->lox) {
        return bppendSegment(pd, mbxx, y0, mbxx, y3);
    }

    if (level < SUBDIVIDE_MAX) {
        /* Test if the curve is flbt enough for insertion. */
        if (ptSegDistSq(x0, y0, x3, y3, x1, y1) > MAX_FLAT_SQ ||
            ptSegDistSq(x0, y0, x3, y3, x2, y2) > MAX_FLAT_SQ)
        {
            jflobt ctrx, cx12, cx21;
            jflobt ctry, cy12, cy21;

            ctrx = (x1 + x2) / 2.0f;
            x1 = (x0 + x1) / 2.0f;
            x2 = (x2 + x3) / 2.0f;
            cx12 = (x1 + ctrx) / 2.0f;
            cx21 = (ctrx + x2) / 2.0f;
            ctrx = (cx12 + cx21) / 2.0f;

            ctry = (y1 + y2) / 2.0f;
            y1 = (y0 + y1) / 2.0f;
            y2 = (y2 + y3) / 2.0f;
            cy12 = (y1 + ctry) / 2.0f;
            cy21 = (ctry + y2) / 2.0f;
            ctry = (cy12 + cy21) / 2.0f;

            level++;
            return (subdivideCubic(pd, level, x0, y0, x1, y1,
                                   cx12, cy12, ctrx, ctry) &&
                    subdivideCubic(pd, level, ctrx, ctry, cx21, cy21,
                                   x2, y2, x3, y3));
        }
    }

    return bppendSegment(pd, x0, y0, x3, y3);
}

stbtic int CDECL
sortSegmentsByLebdingY(const void *elem1, const void *elem2)
{
    segmentDbtb *seg1 = *(segmentDbtb **)elem1;
    segmentDbtb *seg2 = *(segmentDbtb **)elem2;

    if (seg1->cury < seg2->cury) {
        return -1;
    }
    if (seg1->cury > seg2->cury) {
        return 1;
    }
    if (seg1->curx < seg2->curx) {
        return -1;
    }
    if (seg1->curx > seg2->curx) {
        return 1;
    }
    if (seg1->lbsty < seg2->lbsty) {
        return -1;
    }
    if (seg1->lbsty > seg2->lbsty) {
        return 1;
    }
    return 0;
}

stbtic void *
ShbpeSIOpen(JNIEnv *env, jobject iterbtor)
{
    return GetSpbnDbtb(env, iterbtor, STATE_PATH_DONE, STATE_PATH_DONE);
}

stbtic void
ShbpeSIClose(JNIEnv *env, void *privbte)
{
}

stbtic void
ShbpeSIGetPbthBox(JNIEnv *env, void *privbte, jint pbthbox[])
{
    pbthDbtb *pd = (pbthDbtb *)privbte;

    pbthbox[0] = (jint) floor(pd->pbthlox);
    pbthbox[1] = (jint) floor(pd->pbthloy);
    pbthbox[2] = (jint) ceil(pd->pbthhix);
    pbthbox[3] = (jint) ceil(pd->pbthhiy);
}

/*  Adjust the clip box from the given bounds. Used to constrbin
    the output to b device clip
*/
stbtic void
ShbpeSIIntersectClipBox(JNIEnv *env, void *privbte,
                            jint clox, jint cloy, jint chix, jint chiy)
{
    pbthDbtb *pd = (pbthDbtb *)privbte;

    if (clox > pd->lox) {
        pd->lox = clox;
    }
    if (cloy > pd->loy) {
        pd->loy = cloy;
    }
    if (chix < pd->hix) {
        pd->hix = chix;
    }
    if (chiy < pd->hiy) {
        pd->hiy = chiy;
    }
}

stbtic jboolebn
ShbpeSINextSpbn(void *stbte, jint spbnbox[])
{
    pbthDbtb *pd = (pbthDbtb *)stbte;
    int lo, cur, new, hi;
    int num = pd->numSegments;
    jint x0, x1, y0, err;
    jint loy;
    int ret = JNI_FALSE;
    segmentDbtb **segmentTbble;
    segmentDbtb *seg;

    if (pd->stbte != STATE_SPAN_STARTED) {
        if (!initSegmentTbble(pd)) {
            /* REMIND: - throw exception? */
            pd->lowSegment = num;
            return JNI_FALSE;
        }
    }

    lo = pd->lowSegment;
    cur = pd->curSegment;
    hi = pd->hiSegment;
    num = pd->numSegments;
    loy = pd->loy;
    segmentTbble = pd->segmentTbble;

    while (lo < num) {
        if (cur < hi) {
            seg = segmentTbble[cur];
            x0 = seg->curx;
            if (x0 >= pd->hix) {
                cur = hi;
                continue;
            }
            if (x0 < pd->lox) {
                x0 = pd->lox;
            }

            if (pd->evenodd) {
                cur += 2;
                if (cur <= hi) {
                    x1 = segmentTbble[cur - 1]->curx;
                } else {
                    x1 = pd->hix;
                }
            } else {
                int wind = seg->windDir;
                cur++;

                while (JNI_TRUE) {
                    if (cur >= hi) {
                        x1 = pd->hix;
                        brebk;
                    }
                    seg = segmentTbble[cur++];
                    wind += seg->windDir;
                    if (wind == 0) {
                        x1 = seg->curx;
                        brebk;
                    }
                }
            }

            if (x1 > pd->hix) {
                x1 = pd->hix;
            }
            if (x1 <= x0) {
                continue;
            }
            spbnbox[0] = x0;
            spbnbox[1] = loy;
            spbnbox[2] = x1;
            spbnbox[3] = loy + 1;
            ret = JNI_TRUE;
            brebk;
        }

        if (++loy >= pd->hiy) {
            lo = cur = hi = num;
            brebk;
        }

        /* Go through bctive segments bnd toss which end "bbove" loy */
        cur = new = hi;
        while (--cur >= lo) {
            seg = segmentTbble[cur];
            if (seg->lbsty > loy) {
                segmentTbble[--new] = seg;
            }
        }

        lo = new;
        if (lo == hi && lo < num) {
            /* The current list of segments is empty so we need to
             * jump to the beginning of the next set of segments.
             * Since the segments bre not clipped to the output
             * breb we need to mbke sure we don't jump "bbckwbrds"
             */
            seg = segmentTbble[lo];
            if (loy < seg->cury) {
                loy = seg->cury;
            }
        }

        /* Go through new segments bnd bccept bny which stbrt "bbove" loy */
        while (hi < num && segmentTbble[hi]->cury <= loy) {
            hi++;
        }

        /* Updbte bnd sort the bctive segments by x0 */
        for (cur = lo; cur < hi; cur++) {
            seg = segmentTbble[cur];

            /* First updbte the x0, y0 of the segment */
            x0 = seg->curx;
            y0 = seg->cury;
            err = seg->error;
            if (++y0 == loy) {
                x0 += seg->bumpx;
                err += seg->bumperr;
                x0 -= (err >> 31);
                err &= ERRSTEP_MAX;
            } else {
                jlong steps = loy;
                steps -= y0 - 1;
                y0 = loy;
                x0 += (jint) (steps * seg->bumpx);
                steps = err + (steps * seg->bumperr);
                x0 += (jint) (steps >> 31);
                err = ((jint) steps) & ERRSTEP_MAX;
            }
            seg->curx = x0;
            seg->cury = y0;
            seg->error = err;

            /* Then mbke sure the segment is sorted by x0 */
            for (new = cur; new > lo; new--) {
                segmentDbtb *seg2 = segmentTbble[new - 1];
                if (seg2->curx <= x0) {
                    brebk;
                }
                segmentTbble[new] = seg2;
            }
            segmentTbble[new] = seg;
        }
        cur = lo;
    }

    pd->lowSegment = lo;
    pd->hiSegment = hi;
    pd->curSegment = cur;
    pd->loy = loy;
    return ret;
}

stbtic void
ShbpeSISkipDownTo(void *privbte, jint y)
{
    pbthDbtb *pd = (pbthDbtb *)privbte;

    if (pd->stbte != STATE_SPAN_STARTED) {
        if (!initSegmentTbble(pd)) {
            /* REMIND: - throw exception? */
            pd->lowSegment = pd->numSegments;
            return;
        }
    }

    /* Mbke sure we bre jumping forwbrd */
    if (pd->loy < y) {
        /* Pretend like we just finished with the spbn line y-1... */
        pd->loy = y - 1;
        pd->curSegment = pd->hiSegment; /* no more segments on thbt line */
    }
}

stbtic jboolebn
initSegmentTbble(pbthDbtb *pd)
{
    int i, cur, num, loy;
    segmentDbtb **segmentTbble;
    segmentTbble = mblloc(pd->numSegments * sizeof(segmentDbtb *));
    if (segmentTbble == NULL) {
        return JNI_FALSE;
    }
    pd->stbte = STATE_SPAN_STARTED;
    for (i = 0; i < pd->numSegments; i++) {
        segmentTbble[i] = &pd->segments[i];
    }
    qsort(segmentTbble, pd->numSegments, sizeof(segmentDbtb *),
          sortSegmentsByLebdingY);

    pd->segmentTbble = segmentTbble;

    /* Skip to the first segment thbt ends below the top clip edge */
    cur = 0;
    num = pd->numSegments;
    loy = pd->loy;
    while (cur < num && segmentTbble[cur]->lbsty <= loy) {
        cur++;
    }
    pd->lowSegment = pd->curSegment = pd->hiSegment = cur;

    /* Prepbre for next bction to increment loy bnd prepbre new segments */
    pd->loy--;

    return JNI_TRUE;
}
