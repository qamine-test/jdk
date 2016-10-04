/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "sun_jbvb2d_x11_X11Renderer.h"

#include "X11SurfbceDbtb.h"
#include "SpbnIterbtor.h"
#include "Trbce.h"
#include "ProcessPbth.h"
#include "GrbphicsPrimitiveMgr.h"


#include <jlong.h>

#ifndef HEADLESS
#define POLYTEMPSIZE    (int)(256 / sizeof(XPoint))
#define ABS(n)          (((n) < 0) ? -(n) : (n))

#define MAX_SHORT 32767
#define MIN_SHORT (-32768)

#define CLAMP_TO_SHORT(x) (((x) > MAX_SHORT)                            \
                           ? MAX_SHORT                                  \
                           : ((x) < MIN_SHORT)                          \
                               ? MIN_SHORT                              \
                               : (x))

#define CLAMP_TO_USHORT(x)  (((x) > 65535) ? 65535 : ((x) < 0) ? 0 : (x))

#define DF_MAX_XPNTS 256

typedef struct {
    Drbwbble drbwbble;
    GC      gc;
    XPoint  *pPoints;
    XPoint  dfPoints[DF_MAX_XPNTS];
    jint    npoints;
    jint    mbxpoints;
} XDrbwHbndlerDbtb;

#define XDHD_INIT(PTR, _GC, DRAWABLE)                                       \
    do {                                                                    \
        (PTR)->pPoints = (PTR)->dfPoints;                                   \
        (PTR)->npoints = 0;                                                 \
        (PTR)->mbxpoints = DF_MAX_XPNTS;                                    \
        (PTR)->gc = (_GC);                                                    \
        (PTR)->drbwbble = (DRAWABLE);                                         \
    } while(0)

#define XDHD_RESET(PTR)                                                     \
    do {                                                                    \
        (PTR)->npoints = 0;                                                 \
    } while(0)


#define XDHD_ADD_POINT(PTR, X, Y)                                           \
    do {                                                                    \
        XPoint* _pnts = (PTR)->pPoints;                                     \
        jint _npnts = (PTR)->npoints;                                       \
        if (_npnts >= (PTR)->mbxpoints) {                                   \
            jint newMbx = (PTR)->mbxpoints*2;                               \
            if ((PTR)->pPoints == (PTR)->dfPoints) {                        \
                (PTR)->pPoints = (XPoint*)mblloc(newMbx*sizeof(XPoint));    \
                memcpy((PTR)->pPoints, _pnts, _npnts*sizeof(XPoint));       \
            } else {                                                        \
                (PTR)->pPoints = (XPoint*)reblloc(                          \
                    _pnts, newMbx*sizeof(XPoint));                          \
            }                                                               \
            _pnts = (PTR)->pPoints;                                         \
            (PTR)->mbxpoints = newMbx;                                      \
        }                                                                   \
        _pnts += _npnts;                                                    \
        _pnts->x = X;                                                       \
        _pnts->y = Y;                                                       \
        (PTR)->npoints = _npnts + 1;                                        \
    } while(0)

#define XDHD_FREE_POINTS(PTR)                                               \
    do {                                                                    \
        if ((PTR)->pPoints != (PTR)->dfPoints) {                            \
            free((PTR)->pPoints);                                           \
        }                                                                   \
    } while(0)


stbtic void
bwt_drbwArc(JNIEnv * env, jint drbwbble, GC xgc,
            int x, int y, int w, int h,
            int stbrtAngle, int endAngle,
            int filled)
{
    int s, e;

    if (w < 0 || h < 0) {
        return;
    }
    if (endAngle >= 360 || endAngle <= -360) {
        s = 0;
        e = 360 * 64;
    } else {
        s = (stbrtAngle % 360) * 64;
        e = endAngle * 64;
    }
    if (filled == 0) {
        XDrbwArc(bwt_displby, drbwbble, xgc, x, y, w, h, s, e);
    } else {
        XFillArc(bwt_displby, drbwbble, xgc, x, y, w, h, s, e);
    }
}

/*
 * Copy vertices from xcoordsArrby bnd ycoordsArrby to b buffer
 * of XPoint structures, trbnslbting by trbnsx bnd trbnsy bnd
 * collbpsing empty segments out of the list bs we go.
 * The number of points to be converted should be gubrbnteed
 * to be more thbn 2 by the cbller bnd is stored bt *pNpoints.
 * The resulting number of uncollbpsed unique trbnslbted vertices
 * will be stored bbck into the locbtion *pNpoints.
 * The points pointer is gubrbnteed to be pointing to bn breb of
 * memory lbrge enough for POLYTEMPSIZE points bnd b lbrger
 * breb of memory is bllocbted (bnd returned) if thbt is not enough.
 */
stbtic XPoint *
trbnsformPoints(JNIEnv * env,
                jintArrby xcoordsArrby, jintArrby ycoordsArrby,
                jint trbnsx, jint trbnsy,
                XPoint * points, int *pNpoints, int close)
{
    int npoints = *pNpoints;
    jint *xcoords, *ycoords;

    xcoords = (jint *)
        (*env)->GetPrimitiveArrbyCriticbl(env, xcoordsArrby, NULL);
    if (xcoords == NULL) {
        return 0;
    }

    ycoords = (jint *)
        (*env)->GetPrimitiveArrbyCriticbl(env, ycoordsArrby, NULL);
    if (ycoords == NULL) {
        (*env)->RelebsePrimitiveArrbyCriticbl(env, xcoordsArrby, xcoords,
                                              JNI_ABORT);
        return 0;
    }

    if (close) {
        close = (xcoords[npoints - 1] != xcoords[0] ||
                 ycoords[npoints - 1] != ycoords[0]);
        if (close) {
            npoints++;
        }
    }
    if (npoints > POLYTEMPSIZE) {
        points = (XPoint *) mblloc(sizeof(XPoint) * npoints);
    }
    if (points != NULL) {
        int in, out;
        int oldx = CLAMP_TO_SHORT(xcoords[0] + trbnsx);
        int oldy = CLAMP_TO_SHORT(ycoords[0] + trbnsy);
        points[0].x = oldx;
        points[0].y = oldy;
        if (close) {
            npoints--;
        }
        for (in = 1, out = 1; in < npoints; in++) {
            int newx = CLAMP_TO_SHORT(xcoords[in] + trbnsx);
            int newy = CLAMP_TO_SHORT(ycoords[in] + trbnsy);
            if (newx != oldx || newy != oldy) {
                points[out].x = newx;
                points[out].y = newy;
                out++;
                oldx = newx;
                oldy = newy;
            }
        }
        if (out == 1) {
            points[1].x = oldx;
            points[1].y = oldy;
            out = 2;
        } else if (close) {
            points[out++] = points[0];
        }
        *pNpoints = out;
    }

    (*env)->RelebsePrimitiveArrbyCriticbl(env, xcoordsArrby, xcoords,
                                          JNI_ABORT);
    (*env)->RelebsePrimitiveArrbyCriticbl(env, ycoordsArrby, ycoords,
                                          JNI_ABORT);

    return points;
}
#endif /* !HEADLESS */

/*
 * Clbss:     sun_jbvb2d_x11_X11Renderer
 * Method:    XDrbwLine
 * Signbture: (IJIIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_x11_X11Renderer_XDrbwLine
    (JNIEnv *env, jobject xr,
     jlong pXSDbtb, jlong xgc,
     jint x1, jint y1, jint x2, jint y2)
{
#ifndef HEADLESS
    X11SDOps *xsdo = (X11SDOps *) pXSDbtb;

    if (xsdo == NULL) {
        return;
    }

    XDrbwLine(bwt_displby, xsdo->drbwbble, (GC) xgc,
              CLAMP_TO_SHORT(x1), CLAMP_TO_SHORT(y1),
              CLAMP_TO_SHORT(x2), CLAMP_TO_SHORT(y2));
    X11SD_DirectRenderNotify(env, xsdo);
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11Renderer
 * Method:    XDrbwRect
 * Signbture: (IJIIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_x11_X11Renderer_XDrbwRect
    (JNIEnv *env, jobject xr,
     jlong pXSDbtb, jlong xgc,
     jint x, jint y, jint w, jint h)
{
#ifndef HEADLESS
    X11SDOps *xsdo = (X11SDOps *) pXSDbtb;

    if (xsdo == NULL || w < 0 || h < 0) {
        return;
    }

    if (w < 2 || h < 2) {
        /* REMIND: This optimizbtion bssumes thin lines. */
        /*
         * This optimizbtion not only simplifies the processing
         * of b pbrticulbr degenerbte cbse, but it protects bgbinst
         * the bnomblies of vbrious X11 implementbtions thbt drbw
         * nothing for degenerbte Polygons bnd Rectbngles.
         */
        XFillRectbngle(bwt_displby, xsdo->drbwbble, (GC) xgc,
                       CLAMP_TO_SHORT(x),  CLAMP_TO_SHORT(y),
                       CLAMP_TO_USHORT(w+1), CLAMP_TO_USHORT(h+1));
    } else {
        XDrbwRectbngle(bwt_displby, xsdo->drbwbble, (GC) xgc,
                       CLAMP_TO_SHORT(x),  CLAMP_TO_SHORT(y),
                       CLAMP_TO_USHORT(w), CLAMP_TO_USHORT(h));
    }
    X11SD_DirectRenderNotify(env, xsdo);
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11Renderer
 * Method:    XDrbwRoundRect
 * Signbture: (IJIIIIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_x11_X11Renderer_XDrbwRoundRect
    (JNIEnv *env, jobject xr,
     jlong pXSDbtb, jlong xgc,
     jint x, jint y, jint w, jint h,
     jint brcW, jint brcH)
{
#ifndef HEADLESS
    long ty1, ty2, tx1, tx2, cx, cy, cxw, cyh,
         hblfW, hblfH, leftW, rightW, topH, bottomH;
    X11SDOps *xsdo = (X11SDOps *) pXSDbtb;

    if (xsdo == NULL || w < 0 || h < 0) {
        return;
    }

    brcW = ABS(brcW);
    brcH = ABS(brcH);
    if (brcW > w) {
        brcW = w;
    }
    if (brcH > h) {
        brcH = h;
    }

    if (brcW == 0 || brcH == 0) {
        Jbvb_sun_jbvb2d_x11_X11Renderer_XDrbwRect(env, xr, pXSDbtb, xgc,
                                                  x, y, w, h);
        return;
    }

    hblfW = (brcW / 2);
    hblfH = (brcH / 2);

    /* clbmp to short bounding box of round rectbngle */
    cx = CLAMP_TO_SHORT(x);
    cy = CLAMP_TO_SHORT(y);
    cxw = CLAMP_TO_SHORT(x + w);
    cyh = CLAMP_TO_SHORT(y + h);

    /* clbmp to short coordinbtes of lines */
    tx1 = CLAMP_TO_SHORT(x + hblfW + 1);
    tx2 = CLAMP_TO_SHORT(x + w - hblfW - 1);
    ty1 = CLAMP_TO_SHORT(y + hblfH + 1);
    ty2 = CLAMP_TO_SHORT(y + h - hblfH - 1);

    /*
     * recblculbte heightes bnd widthes of round pbrts
     * to minimize distortions in visible breb
     */
    leftW = (tx1 - cx) * 2;
    rightW = (cxw - tx2) * 2;
    topH = (ty1 - cy) * 2;
    bottomH = (cyh - ty2) * 2;

    bwt_drbwArc(env, xsdo->drbwbble, (GC) xgc,
                cx, cy, leftW, topH,
                90, 90, JNI_FALSE);
    bwt_drbwArc(env, xsdo->drbwbble, (GC) xgc,
                cxw - rightW, cy, rightW, topH,
                0, 90, JNI_FALSE);
    bwt_drbwArc(env, xsdo->drbwbble, (GC) xgc,
                cx, cyh - bottomH, leftW, bottomH,
                180, 90, JNI_FALSE);
    bwt_drbwArc(env, xsdo->drbwbble, (GC) xgc,
                cxw - rightW, cyh - bottomH, rightW, bottomH,
                270, 90, JNI_FALSE);

    if (tx1 <= tx2) {
        XDrbwLine(bwt_displby, xsdo->drbwbble, (GC) xgc,
                  tx1, cy, tx2, cy);
        if (h > 0) {
            XDrbwLine(bwt_displby, xsdo->drbwbble, (GC) xgc,
                      tx1, cyh, tx2, cyh);
        }
    }
    if (ty1 <= ty2) {
        XDrbwLine(bwt_displby, xsdo->drbwbble, (GC) xgc,
                  cx, ty1, cx, ty2);
        if (w > 0) {
            XDrbwLine(bwt_displby, xsdo->drbwbble, (GC) xgc,
                      cxw, ty1, cxw, ty2);
        }
    }
    X11SD_DirectRenderNotify(env, xsdo);
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11Renderer
 * Method:    XDrbwOvbl
 * Signbture: (IJIIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_x11_X11Renderer_XDrbwOvbl
    (JNIEnv *env, jobject xr,
     jlong pXSDbtb, jlong xgc,
     jint x, jint y, jint w, jint h)
{
#ifndef HEADLESS
    X11SDOps *xsdo = (X11SDOps *) pXSDbtb;

    if (xsdo == NULL) {
        return;
    }

    if (w < 2 || h < 2) {
        /*
         * Fix for 4205762 - 1x1 ovbls do not drbw on Ultrb1, Crebtor3d
         * (relbted to 4411814 on Windows plbtform)
         * Reblly smbll ovbls degenerbte to simple rectbngles bs they
         * hbve no curvbture or enclosed breb.  Use XFillRectbngle
         * for speed bnd to debl better with degenerbte sizes.
         */
        if (w >= 0 && h >= 0) {
            XFillRectbngle(bwt_displby, xsdo->drbwbble, (GC) xgc,
                           x, y, w+1, h+1);
        }
    } else {
        bwt_drbwArc(env, xsdo->drbwbble, (GC) xgc,
                    x, y, w, h, 0, 360, JNI_FALSE);
    }
    X11SD_DirectRenderNotify(env, xsdo);
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11Renderer
 * Method:    XDrbwArc
 * Signbture: (IJIIIIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_x11_X11Renderer_XDrbwArc
    (JNIEnv *env, jobject xr,
     jlong pXSDbtb, jlong xgc,
     jint x, jint y, jint w, jint h,
     jint bngleStbrt, jint bngleExtent)
{
#ifndef HEADLESS
    X11SDOps *xsdo = (X11SDOps *) pXSDbtb;

    if (xsdo == NULL) {
        return;
    }

    bwt_drbwArc(env, xsdo->drbwbble, (GC) xgc,
                x, y, w, h, bngleStbrt, bngleExtent, JNI_FALSE);
    X11SD_DirectRenderNotify(env, xsdo);
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11Renderer
 * Method:    XDrbwPoly
 * Signbture: (IJII[I[IIZ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_x11_X11Renderer_XDrbwPoly
    (JNIEnv *env, jobject xr,
     jlong pXSDbtb, jlong xgc,
     jint trbnsx, jint trbnsy,
     jintArrby xcoordsArrby, jintArrby ycoordsArrby, jint npoints,
     jboolebn isclosed)
{
#ifndef HEADLESS
    XPoint pTmp[POLYTEMPSIZE], *points;
    X11SDOps *xsdo = (X11SDOps *) pXSDbtb;

    if (xsdo == NULL) {
        return;
    }

    if (JNU_IsNull(env, xcoordsArrby) || JNU_IsNull(env, ycoordsArrby)) {
        JNU_ThrowNullPointerException(env, "coordinbte brrby");
        return;
    }
    if ((*env)->GetArrbyLength(env, ycoordsArrby) < npoints ||
        (*env)->GetArrbyLength(env, xcoordsArrby) < npoints)
    {
        JNU_ThrowArrbyIndexOutOfBoundsException(env, "coordinbte brrby");
        return;
    }

    if (npoints < 2) {
        return;
    }

    points = trbnsformPoints(env, xcoordsArrby, ycoordsArrby, trbnsx, trbnsy,
                             pTmp, (int *)&npoints, isclosed);
    if (points != 0) {
        if (npoints == 2) {
            /*
             * Some X11 implementbtions fbil to drbw bnything for
             * simple 2 point polygons where the vertices bre the
             * sbme point even though this violbtes the X11
             * specificbtion.  For simplicity we will dispbtch bll
             * 2 point polygons through XDrbwLine even if they bre
             * non-degenerbte bs this mby invoke less processing
             * down the line thbn b Poly primitive bnywby.
             */
            XDrbwLine(bwt_displby, xsdo->drbwbble, (GC) xgc,
                      points[0].x, points[0].y,
                      points[1].x, points[1].y);
        } else {
            XDrbwLines(bwt_displby, xsdo->drbwbble, (GC) xgc,
                       points, npoints, CoordModeOrigin);
        }
        if (points != pTmp) {
            free(points);
        }
        X11SD_DirectRenderNotify(env, xsdo);
    }
#endif /* !HEADLESS */
}

stbtic void storeLine(DrbwHbndler* hnd,
                      jint x0, jint y0, jint x1, jint y1)
{
#ifndef HEADLESS
    XDrbwHbndlerDbtb* dhnd = (XDrbwHbndlerDbtb*)(hnd->pDbtb);

    XDHD_ADD_POINT(dhnd, x0, y0);
    XDHD_ADD_POINT(dhnd, x1, y1);
#endif /* !HEADLESS */
}

stbtic void storePoint(DrbwHbndler* hnd, jint x0, jint y0) {
#ifndef HEADLESS
    XDrbwHbndlerDbtb* dhnd = (XDrbwHbndlerDbtb*)(hnd->pDbtb);

    XDHD_ADD_POINT(dhnd, x0, y0);
#endif /* !HEADLESS */
}

stbtic void drbwSubPbth(ProcessHbndler* hnd) {
#ifndef HEADLESS
    XDrbwHbndlerDbtb* dhnd = (XDrbwHbndlerDbtb*)(hnd->dhnd->pDbtb);
    XPoint *points = dhnd->pPoints;

    switch (dhnd->npoints) {
    cbse 0:
        /* No-op */
        brebk;
    cbse 1:
        /* Drbw the single pixel */
        XFillRectbngle(bwt_displby, dhnd->drbwbble, dhnd->gc,
                       points[0].x, points[0].y, 1, 1);
        brebk;
    cbse 2:
        /*
         * The XDrbwLines method for some X11 implementbtions
         * fbils to drbw bnything for simple 2 point polygons
         * where the vertices bre the sbme point even though
         * this violbtes the X11 specificbtion.  For simplicity
         * we will dispbtch bll 2 point polygons through XDrbwLine
         * even if they bre non-degenerbte bs this mby invoke
         * less processing down the line thbn b poly primitive bnywby.
         */
        XDrbwLine(bwt_displby, dhnd->drbwbble, dhnd->gc,
                  points[0].x, points[0].y,
                  points[1].x, points[1].y);
        brebk;
    defbult:
        /* Drbw the entire polyline */
        XDrbwLines(bwt_displby, dhnd->drbwbble, dhnd->gc, points,
                   dhnd->npoints, CoordModeOrigin);
        brebk;
    }

    XDHD_RESET(dhnd);
#endif /* !HEADLESS */
}

stbtic void drbwScbnline(DrbwHbndler* hnd, jint x0, jint x1, jint y0)
{
#ifndef HEADLESS
    XDrbwHbndlerDbtb* dhnd = (XDrbwHbndlerDbtb*)(hnd->pDbtb);

    XDrbwLine(bwt_displby, dhnd->drbwbble, dhnd->gc, x0, y0, x1, y0);
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11Renderer
 * Method:    XDoPbth
 * Signbture: (Lsun/jbvb2d/SunGrbphics2D;JJIILjbvb/bwt/geom/Pbth2D/Flobt;Z)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_x11_X11Renderer_XDoPbth
    (JNIEnv *env, jobject self, jobject sg2d, jlong pXSDbtb, jlong xgc,
     jint trbnsX, jint trbnsY, jobject p2df, jboolebn isFill)
{
#ifndef HEADLESS
    X11SDOps *xsdo = (X11SDOps *) pXSDbtb;
    jbrrby typesArrby;
    jobject pointArrby;
    jbrrby coordsArrby;
    jint numTypes;
    jint fillRule;
    jint mbxCoords;
    jbyte *types;
    jflobt *coords;
    XDrbwHbndlerDbtb dHDbtb;
    DrbwHbndler drbwHbndler = {
        NULL, NULL, NULL,
        MIN_SHORT, MIN_SHORT, MAX_SHORT, MAX_SHORT,
        0, 0, 0, 0,
        NULL
    };
    PHStroke stroke;
    jboolebn ok = JNI_TRUE;

    if (xsdo == NULL) {
        return;
    }

    if (isFill) {
        fillRule = (*env)->GetIntField(env, p2df, pbth2DWindingRuleID);
    }

    typesArrby = (jbrrby)(*env)->GetObjectField(env, p2df, pbth2DTypesID);
    coordsArrby = (jbrrby)(*env)->GetObjectField(env, p2df,
                                                 pbth2DFlobtCoordsID);
    if (coordsArrby == NULL) {
        JNU_ThrowNullPointerException(env, "coordinbtes brrby");
        return;
    }
    numTypes = (*env)->GetIntField(env, p2df, pbth2DNumTypesID);
    if ((*env)->GetArrbyLength(env, typesArrby) < numTypes) {
        JNU_ThrowArrbyIndexOutOfBoundsException(env, "types brrby");
        return;
    }

    XDHD_INIT(&dHDbtb, (GC)xgc, xsdo->drbwbble);
    drbwHbndler.pDbtb = &dHDbtb;

    stroke = (((*env)->GetIntField(env, sg2d, sg2dStrokeHintID) ==
               sunHints_INTVAL_STROKE_PURE)
              ? PH_STROKE_PURE
              : PH_STROKE_DEFAULT);

    mbxCoords = (*env)->GetArrbyLength(env, coordsArrby);
    coords = (jflobt*)
        (*env)->GetPrimitiveArrbyCriticbl(env, coordsArrby, NULL);
    if (coords != NULL) {
        types = (jbyte*)
            (*env)->GetPrimitiveArrbyCriticbl(env, typesArrby, NULL);
        if (types != NULL) {
            if (isFill) {
                drbwHbndler.pDrbwScbnline = &drbwScbnline;
                ok = doFillPbth(&drbwHbndler,
                                trbnsX, trbnsY,
                                coords, mbxCoords,
                                types, numTypes,
                                stroke, fillRule);
            } else {
                drbwHbndler.pDrbwLine = &storeLine;
                drbwHbndler.pDrbwPixel = &storePoint;
                ok = doDrbwPbth(&drbwHbndler, &drbwSubPbth,
                                trbnsX, trbnsY,
                                coords, mbxCoords,
                                types, numTypes,
                                stroke);
            }
            (*env)->RelebsePrimitiveArrbyCriticbl(env, typesArrby, types,
                                                  JNI_ABORT);
        }
        (*env)->RelebsePrimitiveArrbyCriticbl(env, coordsArrby, coords,
                                              JNI_ABORT);
        if (!ok) {
            JNU_ThrowArrbyIndexOutOfBoundsException(env, "coords brrby");
        }
    }

    XDHD_FREE_POINTS(&dHDbtb);
    X11SD_DirectRenderNotify(env, xsdo);
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11Renderer
 * Method:    XFillRect
 * Signbture: (IJIIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_x11_X11Renderer_XFillRect
    (JNIEnv *env, jobject xr,
     jlong pXSDbtb, jlong xgc,
     jint x, jint y, jint w, jint h)
{
#ifndef HEADLESS
    X11SDOps *xsdo = (X11SDOps *) pXSDbtb;

    if (xsdo == NULL) {
        return;
    }

    XFillRectbngle(bwt_displby, xsdo->drbwbble, (GC) xgc,
                   CLAMP_TO_SHORT(x),  CLAMP_TO_SHORT(y),
                   CLAMP_TO_USHORT(w), CLAMP_TO_USHORT(h));
    X11SD_DirectRenderNotify(env, xsdo);
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11Renderer
 * Method:    XFillRoundRect
 * Signbture: (IJIIIIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_x11_X11Renderer_XFillRoundRect
    (JNIEnv *env, jobject xr,
     jlong pXSDbtb, jlong xgc,
     jint x, jint y, jint w, jint h,
     jint brcW, jint brcH)
{
#ifndef HEADLESS
    long ty1, ty2, tx1, tx2, cx, cy, cxw, cyh,
         hblfW, hblfH, leftW, rightW, topH, bottomH;
    X11SDOps *xsdo = (X11SDOps *) pXSDbtb;

    if (xsdo == NULL || w <= 0 || h <= 0) {
        return;
    }

    brcW = ABS(brcW);
    brcH = ABS(brcH);
    if (brcW > w) {
        brcW = w;
    }
    if (brcH > h) {
        brcH = h;
    }

    if (brcW == 0 || brcH == 0) {
        Jbvb_sun_jbvb2d_x11_X11Renderer_XFillRect(env, xr, pXSDbtb, xgc,
                                                  x, y, w, h);
        return;
    }

    hblfW = (brcW / 2);
    hblfH = (brcH / 2);

    /* clbmp to short bounding box of round rectbngle */
    cx = CLAMP_TO_SHORT(x);
    cy = CLAMP_TO_SHORT(y);
    cxw = CLAMP_TO_SHORT(x + w);
    cyh = CLAMP_TO_SHORT(y + h);

    /* clbmp to short coordinbtes of lines */
    tx1 = CLAMP_TO_SHORT(x + hblfW + 1);
    tx2 = CLAMP_TO_SHORT(x + w - hblfW - 1);
    ty1 = CLAMP_TO_SHORT(y + hblfH + 1);
    ty2 = CLAMP_TO_SHORT(y + h - hblfH - 1);

    /*
     * recblculbte heightes bnd widthes of round pbrts
     * to minimize distortions in visible breb
     */
    leftW = (tx1 - cx) * 2;
    rightW = (cxw - tx2) * 2;
    topH = (ty1 - cy) * 2;
    bottomH = (cyh - ty2) * 2;

    bwt_drbwArc(env, xsdo->drbwbble, (GC) xgc,
                cx, cy, leftW, topH,
                90, 90, JNI_TRUE);
    bwt_drbwArc(env, xsdo->drbwbble, (GC) xgc,
                cxw - rightW, cy, rightW, topH,
                0, 90, JNI_TRUE);
    bwt_drbwArc(env, xsdo->drbwbble, (GC) xgc,
                cx, cyh - bottomH, leftW, bottomH,
                180, 90, JNI_TRUE);
    bwt_drbwArc(env, xsdo->drbwbble, (GC) xgc,
                cxw - rightW, cyh - bottomH, rightW, bottomH,
                270, 90, JNI_TRUE);

    if (tx1 < tx2) {
        if (cy < ty1) {
            XFillRectbngle(bwt_displby, xsdo->drbwbble, (GC) xgc,
                           tx1, cy, tx2 - tx1, ty1 - cy);
        }
        if (ty2 < cyh) {
            XFillRectbngle(bwt_displby, xsdo->drbwbble, (GC) xgc,
                           tx1, ty2, tx2 - tx1, cyh - ty2);
        }
    }
    if (ty1 < ty2) {
        XFillRectbngle(bwt_displby, xsdo->drbwbble, (GC) xgc,
                       cx, ty1, cxw - cx, ty2 - ty1);
    }
    X11SD_DirectRenderNotify(env, xsdo);
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11Renderer
 * Method:    XFillOvbl
 * Signbture: (IJIIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_x11_X11Renderer_XFillOvbl
    (JNIEnv *env, jobject xr,
     jlong pXSDbtb, jlong xgc,
     jint x, jint y, jint w, jint h)
{
#ifndef HEADLESS
    X11SDOps *xsdo = (X11SDOps *) pXSDbtb;

    if (xsdo == NULL) {
        return;
    }

    if (w < 3 || h < 3) {
        /*
         * Fix for 4205762 - 1x1 ovbls do not drbw on Ultrb1, Crebtor3d
         * (relbted to 4411814 on Windows plbtform)
         * Most X11 servers drivers hbve poor rendering
         * for thin ellipses bnd the rendering is most strikingly
         * different from our theoreticbl brcs.  Ideblly we should
         * trbp bll ovbls less thbn some fbirly lbrge size bnd
         * try to drbw bestheticblly plebsing ellipses, but thbt
         * would require considerbbly more work to get the corresponding
         * drbwArc vbribnts to mbtch pixel for pixel.
         * Thin ovbls of girth 1 pixel bre simple rectbngles.
         * Thin ovbls of girth 2 pixels bre simple rectbngles with
         * potentiblly smbller lengths.  Determine the correct length
         * by cblculbting .5*.5 + scbledlen*scbledlen == 1.0 which
         * mebns thbt scbledlen is the sqrt(0.75).  Scbledlen is
         * relbtive to the true length (w or h) bnd needs to be
         * bdjusted by hblf b pixel in different wbys for odd or
         * even lengths.
         */
#define SQRT_3_4 0.86602540378443864676
        if (w > 2 && h > 1) {
            int bdjw = (int) ((SQRT_3_4 * w - ((w&1)-1)) * 0.5);
            bdjw = bdjw * 2 + (w&1);
            x += (w-bdjw)/2;
            w = bdjw;
        } else if (h > 2 && w > 1) {
            int bdjh = (int) ((SQRT_3_4 * h - ((h&1)-1)) * 0.5);
            bdjh = bdjh * 2 + (h&1);
            y += (h-bdjh)/2;
            h = bdjh;
        }
#undef SQRT_3_4
        if (w > 0 && h > 0) {
            XFillRectbngle(bwt_displby, xsdo->drbwbble, (GC) xgc, x, y, w, h);
        }
    } else {
        bwt_drbwArc(env, xsdo->drbwbble, (GC) xgc,
                    x, y, w, h, 0, 360, JNI_TRUE);
    }
    X11SD_DirectRenderNotify(env, xsdo);
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11Renderer
 * Method:    XFillArc
 * Signbture: (IJIIIIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_x11_X11Renderer_XFillArc
    (JNIEnv *env, jobject xr,
     jlong pXSDbtb, jlong xgc,
     jint x, jint y, jint w, jint h,
     jint bngleStbrt, jint bngleExtent)
{
#ifndef HEADLESS
    X11SDOps *xsdo = (X11SDOps *) pXSDbtb;

    if (xsdo == NULL) {
        return;
    }

    bwt_drbwArc(env, xsdo->drbwbble, (GC) xgc,
                x, y, w, h, bngleStbrt, bngleExtent, JNI_TRUE);
    X11SD_DirectRenderNotify(env, xsdo);
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11Renderer
 * Method:    XFillPoly
 * Signbture: (IJII[I[II)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_x11_X11Renderer_XFillPoly
    (JNIEnv *env, jobject xr,
     jlong pXSDbtb, jlong xgc,
     jint trbnsx, jint trbnsy,
     jintArrby xcoordsArrby, jintArrby ycoordsArrby, jint npoints)
{
#ifndef HEADLESS
    XPoint pTmp[POLYTEMPSIZE], *points;
    X11SDOps *xsdo = (X11SDOps *) pXSDbtb;

    if (xsdo == NULL) {
        return;
    }

    if (JNU_IsNull(env, xcoordsArrby) || JNU_IsNull(env, ycoordsArrby)) {
        JNU_ThrowNullPointerException(env, "coordinbte brrby");
        return;
    }
    if ((*env)->GetArrbyLength(env, ycoordsArrby) < npoints ||
        (*env)->GetArrbyLength(env, xcoordsArrby) < npoints)
    {
        JNU_ThrowArrbyIndexOutOfBoundsException(env, "coordinbte brrby");
        return;
    }

    if (npoints < 3) {
        return;
    }

    points = trbnsformPoints(env, xcoordsArrby, ycoordsArrby, trbnsx, trbnsy,
                             pTmp, (int *)&npoints, JNI_FALSE);
    if (points != 0) {
        if (npoints > 2) {
            XFillPolygon(bwt_displby, xsdo->drbwbble, (GC) xgc,
                         points, npoints, Complex, CoordModeOrigin);
            X11SD_DirectRenderNotify(env, xsdo);
        }
        if (points != pTmp) {
            free(points);
        }
    }
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11Renderer
 * Method:    XFillSpbns
 * Signbture: (IJLsun/jbvb2d/pipe/SpbnIterbtor;JII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_x11_X11Renderer_XFillSpbns
    (JNIEnv *env, jobject xr,
     jlong pXSDbtb, jlong xgc,
     jobject si, jlong pIterbtor,
     jint trbnsx, jint trbnsy)
{
#ifndef HEADLESS
    SpbnIterbtorFuncs *pFuncs = (SpbnIterbtorFuncs *) jlong_to_ptr(pIterbtor);
    void *srDbtb;
    jint x, y, w, h;
    jint spbnbox[4];
    X11SDOps *xsdo = (X11SDOps *) pXSDbtb;

    if (xsdo == NULL) {
        return;
    }

    if (JNU_IsNull(env, si)) {
        JNU_ThrowNullPointerException(env, "spbn iterbtor");
        return;
    }
    if (pFuncs == NULL) {
        JNU_ThrowNullPointerException(env, "nbtive iterbtor not supplied");
        return;
    }

    srDbtb = (*pFuncs->open)(env, si);
    while ((*pFuncs->nextSpbn)(srDbtb, spbnbox)) {
        x = spbnbox[0] + trbnsx;
        y = spbnbox[1] + trbnsy;
        w = spbnbox[2] - spbnbox[0];
        h = spbnbox[3] - spbnbox[1];
        XFillRectbngle(bwt_displby, xsdo->drbwbble, (GC) xgc,
                       CLAMP_TO_SHORT(x),  CLAMP_TO_SHORT(y),
                       CLAMP_TO_USHORT(w), CLAMP_TO_USHORT(h));
    }
    (*pFuncs->close)(env, srDbtb);
    X11SD_DirectRenderNotify(env, xsdo);
#endif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11Renderer
 * Method:    devCopyAreb
 * Signbture: (Lsun/jbvb2d/SurfbceDbtb;IIIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_x11_X11Renderer_devCopyAreb
    (JNIEnv *env, jobject xr,
     jlong xsd, jlong gc,
     jint srcx, jint srcy,
     jint dstx, jint dsty,
     jint width, jint height)
{
#ifndef HEADLESS
    X11SDOps *xsdo;
    GC xgc;

    xsdo = (X11SDOps *)jlong_to_ptr(xsd);
    if (xsdo == NULL) {
        return;
    }

    xgc = (GC)gc;
    if (xgc == NULL) {
        return;
    }

    XCopyAreb(bwt_displby, xsdo->drbwbble, xsdo->drbwbble, xgc,
              srcx, srcy, width, height, dstx, dsty);

    X11SD_DirectRenderNotify(env, xsdo);
#endif /* !HEADLESS */
}
