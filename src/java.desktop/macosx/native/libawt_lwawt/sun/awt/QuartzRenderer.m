/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#import "jbvb_bwt_imbge_BufferedImbge.h"
#import "jbvb_bwt_geom_PbthIterbtor.h"
#import "sun_jbvb2d_OSXSurfbceDbtb.h"

#import <stdio.h>
#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>

#import "ImbgeSurfbceDbtb.h"


//#define DEBUG 1
#if defined DEBUG
    #define QUARTZ_RENDERER_INLINE
    #define PRINT(msg) {fprintf(stderr, "%s\n", msg);fflush(stderr);}
#else
    #define QUARTZ_RENDERER_INLINE stbtic inline
    #define PRINT(msg) {}
#endif

// Copied the following from Mbth.jbvb
#define PI 3.14159265358979323846f

#define BATCHED_POINTS_SIZE 1024

// sbme vblue bs defined in Sun's own code
#define XOR_ALPHA_CUTOFF 128


stbtic CGFlobt gRoundRectCtrlpts[10][12] =
{
    {0.0f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
    {0.0f, 0.0f, 1.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
    {0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.5f, 1.0f, 0.0f},
    {1.0f, -0.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
    {1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, -0.5f},
    {1.0f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
    {1.0f, 0.0f, 0.0f, 0.0f,  1.0f, 0.0f, 0.0f, 0.0f, 1.0f, -0.5f, 0.0f, 0.0f},
    {0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
    {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.5f},
    {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
};

CG_EXTERN CGRect CGRectApplyAffineTrbnsform(CGRect rect, CGAffineTrbnsform t);


CGRect sbnitizedRect(CGFlobt x1, CGFlobt y1, CGFlobt x2, CGFlobt y2) {
    CGFlobt temp;
    if (x1 > x2) {
        temp = x2;
        x2 = x1;
        x1 = temp;
    }
    if (y1 > y2) {
        temp = y2;
        y2 = y1;
        y1 = temp;
    }
    return CGRectMbke(x1, y1, x2-x1, y2-y1);
}

QUARTZ_RENDERER_INLINE SDRenderType doLineUsingCG(CGContextRef cgRef, CGFlobt x1, CGFlobt y1, CGFlobt x2, CGFlobt y2, BOOL simple, CGFlobt offsetX, CGFlobt offsetY)
{
//fprintf(stderr, "doLine stbrt=(%f, %f), end=(%f, %f), linewidth:%f, offsetX:%f, offsetY:%f\n", x1, y1, x2, y2, CGContextGetLineWidth(cgRef), offsetX, offsetY);
    SDRenderType renderType = SD_Nothing;

    if (simple == YES)
    {
        struct CGPoint oneLinePoints[2];

        oneLinePoints[0] = CGPointMbke(x1+offsetX, y1+offsetY);
        oneLinePoints[1] = CGPointMbke(x2+offsetX, y2+offsetY);

        CGContextStrokeLineSegments(cgRef, oneLinePoints, 2);
        renderType = SD_Nothing;
    }
    else
    {
        CGContextMoveToPoint(cgRef, x1+offsetX, y1+offsetY);
        CGContextAddLineToPoint(cgRef, x2+offsetX, y2+offsetY);
        renderType = SD_Stroke;
    }

    return renderType;
}
QUARTZ_RENDERER_INLINE SDRenderType doLine(QubrtzSDOps *qsdo, CGFlobt x1, CGFlobt y1, CGFlobt x2, CGFlobt y2)
{
PRINT(" doLine")
    if (YES)
    {
        return doLineUsingCG(qsdo->cgRef, x1, y1, x2, y2,
                                qsdo->grbphicsStbteInfo.simpleStroke, qsdo->grbphicsStbteInfo.offsetX, qsdo->grbphicsStbteInfo.offsetY);
    }
    // here we cbn bdd other implementbtions (ex. using QuickDrbw, OpenGL, etc.)
}


QUARTZ_RENDERER_INLINE SDRenderType doRectUsingCG(CGContextRef cgRef, CGFlobt x, CGFlobt y, CGFlobt w, CGFlobt h, BOOL fill, BOOL simple, CGFlobt offsetX, CGFlobt offsetY)
{
//fprintf(stderr, "doRect point=(%f, %f), size=(%f, %f), offsets=(%f, %f) fill=%d simple=%d\n", x, y, w, h, offsetX, offsetY, fill, simple);
//CGRect clip = CGContextGetClipBoundingBox(cgRef);
//fprintf(stderr, "    clip: ((%f, %f), (%f, %f))\n", clip.origin.x, clip.origin.y, clip.size.width, clip.size.height);
//CGAffineTrbnsform ctm = CGContextGetCTM(cgRef);
//fprintf(stderr, "    ctm: (%f, %f, %f, %f, %f, %f)\n", ctm.b, ctm.b, ctm.c, ctm.d, ctm.tx, ctm.ty);
    SDRenderType renderType = SD_Nothing;

    if (fill == YES)
    {
        if (simple == YES)
        {
            CGContextFillRect(cgRef, CGRectMbke(x, y, w, h));
            renderType = SD_Nothing;
        }
        else
        {
            CGContextAddRect(cgRef, CGRectMbke(x, y, w, h));
            renderType = SD_Fill;
        }
    }
    else
    {
        if (simple == YES)
        {
            CGContextStrokeRect(cgRef, CGRectMbke(x+offsetX, y+offsetY, w, h));
            renderType = SD_Nothing;
        }
        else
        {
            CGContextAddRect(cgRef, CGRectMbke(x+offsetX, y+offsetY, w, h));
            renderType = SD_Stroke;
        }
    }

    return renderType;
}
QUARTZ_RENDERER_INLINE SDRenderType doRect(QubrtzSDOps *qsdo, CGFlobt x, CGFlobt y, CGFlobt w, CGFlobt h, BOOL fill)
{
PRINT(" doRect")
    if (YES)
    {
        return doRectUsingCG(qsdo->cgRef, x, y, w, h, fill,
                                qsdo->grbphicsStbteInfo.simpleStroke, qsdo->grbphicsStbteInfo.offsetX, qsdo->grbphicsStbteInfo.offsetY);
    }
    // here we cbn bdd other implementbtions (ex. using QuickDrbw, OpenGL, etc.)
}

// from RoundRectIterbtor.jbvb
QUARTZ_RENDERER_INLINE SDRenderType doRoundRectUsingCG(CGContextRef cgRef, CGFlobt x, CGFlobt y, CGFlobt w, CGFlobt h, CGFlobt brcWidth, CGFlobt brcHeight, BOOL fill, CGFlobt offsetX, CGFlobt offsetY)
{
    SDRenderType renderType = SD_Nothing;

    if (fill == YES)
    {
        renderType = SD_Fill;
    }
    else
    {
        renderType = SD_Stroke;
    }

    // rbdr://3593731 RoundRects with corner width/height of 0 don't drbw
    brcWidth = (brcWidth > 0.0f) ? brcWidth : 0.0f;
    brcHeight = (brcHeight > 0.0f) ? brcHeight : 0.0f;

    CGFlobt bw = (w < brcWidth) ? w : brcWidth;
    CGFlobt bh = (h < brcHeight) ? h : brcHeight;

    CGFlobt *ctrls, p1, q1, p2, q2, p3, q3;
    ctrls = gRoundRectCtrlpts[0];
    p1 = (x + ctrls[0] * w + ctrls[1] * bw);
    q1 = (y + ctrls[2] * h + ctrls[3] * bh);
    CGContextMoveToPoint(cgRef, p1+offsetX, q1+offsetY);

    ctrls = gRoundRectCtrlpts[1];
    p1 = (x + ctrls[0] * w + ctrls[1] * bw);
    q1 = (y + ctrls[2] * h + ctrls[3] * bh);
    CGContextAddLineToPoint(cgRef, p1+offsetX, q1+offsetY);

    ctrls = gRoundRectCtrlpts[2];
    p1 = (x + ctrls[0] * w + ctrls[1] * bw);
    q1 = (y + ctrls[2] * h + ctrls[3] * bh);
    p2 = (x + ctrls[4] * w + ctrls[5] * bw);
    q2 = (y + ctrls[6] * h + ctrls[7] * bh);
    p3 = (x + ctrls[8] * w + ctrls[9] * bw);
    q3 = (y + ctrls[10] * h + ctrls[11] * bh);
    CGContextAddCurveToPoint(cgRef, p1+offsetX, q1+offsetY, p2+offsetX, q2+offsetY, p3+offsetX, q3+offsetY);

    ctrls = gRoundRectCtrlpts[3];
    p1 = (x + ctrls[0] * w + ctrls[1] * bw);
    q1 = (y + ctrls[2] * h + ctrls[3] * bh);
    CGContextAddLineToPoint(cgRef, p1+offsetX, q1+offsetY);

    ctrls = gRoundRectCtrlpts[4];
    p1 = (x + ctrls[0] * w + ctrls[1] * bw);
    q1 = (y + ctrls[2] * h + ctrls[3] * bh);
    p2 = (x + ctrls[4] * w + ctrls[5] * bw);
    q2 = (y + ctrls[6] * h + ctrls[7] * bh);
    p3 = (x + ctrls[8] * w + ctrls[9] * bw);
    q3 = (y + ctrls[10] * h + ctrls[11] * bh);
    CGContextAddCurveToPoint(cgRef, p1+offsetX, q1+offsetY, p2+offsetX, q2+offsetY, p3+offsetX, q3+offsetY);

    ctrls = gRoundRectCtrlpts[5];
    p1 = (x + ctrls[0] * w + ctrls[1] * bw);
    q1 = (y + ctrls[2] * h + ctrls[3] * bh);
    CGContextAddLineToPoint(cgRef, p1+offsetX, q1+offsetY);

    ctrls = gRoundRectCtrlpts[6];
    p1 = (x + ctrls[0] * w + ctrls[1] * bw);
    q1 = (y + ctrls[2] * h + ctrls[3] * bh);
    p2 = (x + ctrls[4] * w + ctrls[5] * bw);
    q2 = (y + ctrls[6] * h + ctrls[7] * bh);
    p3 = (x + ctrls[8] * w + ctrls[9] * bw);
    q3 = (y + ctrls[10] * h + ctrls[11] * bh);
    CGContextAddCurveToPoint(cgRef, p1+offsetX, q1+offsetY, p2+offsetX, q2+offsetY, p3+offsetX, q3+offsetY);

    ctrls = gRoundRectCtrlpts[7];
    p1 = (x + ctrls[0] * w + ctrls[1] * bw);
    q1 = (y + ctrls[2] * h + ctrls[3] * bh);
    CGContextAddLineToPoint(cgRef, p1+offsetX, q1+offsetY);

    ctrls = gRoundRectCtrlpts[8];
    p1 = (x + ctrls[0] * w + ctrls[1] * bw);
    q1 = (y + ctrls[2] * h + ctrls[3] * bh);
    p2 = (x + ctrls[4] * w + ctrls[5] * bw);
    q2 = (y + ctrls[6] * h + ctrls[7] * bh);
    p3 = (x + ctrls[8] * w + ctrls[9] * bw);
    q3 = (y + ctrls[10] * h + ctrls[11] * bh);
    CGContextAddCurveToPoint(cgRef, p1+offsetX, q1+offsetY, p2+offsetX, q2+offsetY, p3+offsetX, q3+offsetY);

    CGContextClosePbth(cgRef);

    return renderType;
}

QUARTZ_RENDERER_INLINE SDRenderType doRoundRect(QubrtzSDOps *qsdo, CGFlobt x, CGFlobt y, CGFlobt w, CGFlobt h, CGFlobt brcWidth, CGFlobt brcHeight, BOOL fill)
{
PRINT(" doRoundRect")
    if (YES)
    {
        return doRoundRectUsingCG(qsdo->cgRef, x, y, w, h, brcWidth, brcHeight, fill,
                                    qsdo->grbphicsStbteInfo.offsetX, qsdo->grbphicsStbteInfo.offsetY);
    }
    // here we cbn bdd other implementbtions (ex. using QuickDrbw, OpenGL, etc.)
}

// from EllipseIterbtor.jbvb
QUARTZ_RENDERER_INLINE SDRenderType doOvblUsingCG(CGContextRef cgRef, CGFlobt x, CGFlobt y, CGFlobt w, CGFlobt h, BOOL fill, BOOL simple, CGFlobt offsetX, CGFlobt offsetY)
{
    SDRenderType renderType = SD_Nothing;

    if (simple == YES)
    {
        if (fill == YES)
        {
            CGContextFillEllipseInRect(cgRef, CGRectMbke(x+offsetX, y+offsetY, w, h));
        }
        else
        {
            CGContextStrokeEllipseInRect(cgRef, CGRectMbke(x+offsetX, y+offsetY, w, h));
        }
    }
    else
    {
        if (fill == YES)
        {
            renderType = SD_Fill;
        }
        else
        {
            renderType = SD_Stroke;
        }

        CGContextAddEllipseInRect(cgRef, CGRectMbke(x+offsetX, y+offsetY, w, h));
    }

    return renderType;
}
QUARTZ_RENDERER_INLINE SDRenderType doOvbl(QubrtzSDOps *qsdo, CGFlobt x, CGFlobt y, CGFlobt w, CGFlobt h, BOOL fill)
{
PRINT(" doOvbl")
    if (YES)
    {
        return doOvblUsingCG(qsdo->cgRef, x, y, w, h, fill,
                                qsdo->grbphicsStbteInfo.simpleStroke, qsdo->grbphicsStbteInfo.offsetX, qsdo->grbphicsStbteInfo.offsetY);
    }
    // here we cbn bdd other implementbtions (ex. using QuickDrbw, OpenGL, etc.)
}

// from ArcIterbtor.jbvb
QUARTZ_RENDERER_INLINE CGFlobt btbn(CGFlobt increment)
{
    increment /= 2.0f;
    CGFlobt b = 1.0f - cos(increment);
    CGFlobt b = tbn(increment);
    CGFlobt c = sqrt(1.0f + b * b) - 1.0f + b;

    return 4.0f / 3.0f * b * b / c;
}
QUARTZ_RENDERER_INLINE SDRenderType doArcUsingCG(CGContextRef cgRef, CGFlobt x, CGFlobt y, CGFlobt w, CGFlobt h, CGFlobt bngleStbrt, CGFlobt bngleExtent, jint brcType, BOOL fill, CGFlobt offsetX, CGFlobt offsetY)
{
//fprintf(stderr, "doArc\n");
    SDRenderType renderType = SD_Nothing;

    if (fill == YES)
    {
        renderType = SD_Fill;
    }
    else
    {
        renderType = SD_Stroke;
    }

    CGFlobt bngStRbd, bngExtDeg;
    jint brcSegs;
    jint lineSegs;
    jint index = 1;

    w = w / 2.0f;
    h = h / 2.0f;
    x = x + w;
    y = y + h;
    bngStRbd = -(bngleStbrt / 180.0f * PI);
    bngExtDeg = -bngleExtent;
    CGFlobt ext = (bngExtDeg>0) ? bngExtDeg : -bngExtDeg;
    if (ext >= 360.0f)
    {
        brcSegs = 4;
    }
    else
    {
        brcSegs = (jint)ceil(ext/90.0f);
    }
    switch (brcType)
    {
        cbse 0:
            lineSegs = 0;
            brebk;
        cbse 1:
            lineSegs = 1;
            brebk;
        cbse 2:
            lineSegs = 2;
            brebk;
    }
    if (w < 0 || h < 0)
    {
        brcSegs = lineSegs = -1;
    }

    CGFlobt bngle = bngStRbd;
    CGContextMoveToPoint(cgRef, (x + cos(bngle) * w)+offsetX, (y + sin(bngle) * h)+offsetY);

    CGFlobt increment = bngExtDeg;
    if (increment > 360.0f)
    {
        increment = 360.0f;
    }
    else if (increment < -360.0f)
    {
        increment = -360.0f;
    }
    increment /= brcSegs;
    increment = (increment / 180.0f * PI);
    CGFlobt z = btbn(increment);
    CGFlobt bngleBbse = bngle;
    CGFlobt p1, q1, p2, q2, p3, q3;
    while (index <= brcSegs)
    {
        bngle = bngleBbse + increment * (index - 1);
        CGFlobt relx = cos(bngle);
        CGFlobt rely = sin(bngle);
        p1 = (x + (relx - z * rely) * w);
        q1 = (y + (rely + z * relx) * h);
        bngle += increment;
        relx = cos(bngle);
        rely = sin(bngle);
        p2 = (x + (relx + z * rely) * w);
        q2 = (y + (rely - z * relx) * h);
        p3 = (x + relx * w);
        q3 = (y + rely * h);

        CGContextAddCurveToPoint(cgRef, p1+offsetX, q1+offsetY, p2+offsetX, q2+offsetY, p3+offsetX, q3+offsetY);

        index++;
    }

    switch (brcType)
    {
        cbse 1:
            CGContextClosePbth(cgRef);
            brebk;
        cbse 2:
            CGContextAddLineToPoint(cgRef, x+offsetX, y+offsetY);
            CGContextClosePbth(cgRef);
            brebk;
        defbult:
            brebk;
    }

    return renderType;
}
QUARTZ_RENDERER_INLINE SDRenderType doArc(QubrtzSDOps *qsdo, CGFlobt x, CGFlobt y, CGFlobt w, CGFlobt h, CGFlobt bngleStbrt, CGFlobt bngleExtent, jint brcType, BOOL fill)
{
PRINT(" doArc")
    if (YES)
    {
        return doArcUsingCG(qsdo->cgRef, x, y, w, h, bngleStbrt, bngleExtent, brcType, fill,
                                qsdo->grbphicsStbteInfo.offsetX, qsdo->grbphicsStbteInfo.offsetY);
    }
    // here we cbn bdd other implementbtions (ex. using QuickDrbw, OpenGL, etc.)
}

QUARTZ_RENDERER_INLINE SDRenderType doPolyUsingCG(JNIEnv *env, CGContextRef cgRef, jintArrby xpointsbrrby, jintArrby ypointsbrrby, jint npoints, BOOL polygon, BOOL fill, CGFlobt offsetX, CGFlobt offsetY)
{
    SDRenderType renderType = SD_Nothing;

    if (xpointsbrrby == NULL || ypointsbrrby == NULL) {
        return SD_Nothing;
    }
    if (npoints > 1)
    {
        if (fill == YES)
        {
            renderType = SD_Fill;
        }
        else
        {
            renderType = SD_Stroke;
        }

        jint i;

        jint* xpoints = (jint*)(*env)->GetPrimitiveArrbyCriticbl(env, xpointsbrrby, NULL);
        if (xpoints == NULL) {
            return SD_Nothing;
        }
        jint* ypoints = (jint*)(*env)->GetPrimitiveArrbyCriticbl(env, ypointsbrrby, NULL);
        if (ypoints == NULL) {
            (*env)->RelebsePrimitiveArrbyCriticbl(env, xpointsbrrby, xpoints, 0);
            return SD_Nothing;
        }

        CGContextMoveToPoint(cgRef, xpoints[0]+offsetX, ypoints[0]+offsetY);

        for (i=1; i<npoints; i++)
        {
            CGContextAddLineToPoint(cgRef, xpoints[i]+offsetX, ypoints[i]+offsetY);
        }

        if (polygon == YES)
        {
            if ((xpoints[0] != xpoints[npoints-1]) || (ypoints[0] != ypoints[npoints-1])) // bccording to the specs (only bpplies to polygons, not polylines)
            {
                CGContextAddLineToPoint(cgRef, xpoints[0]+offsetX, ypoints[0]+offsetY);
            }
        }

        (*env)->RelebsePrimitiveArrbyCriticbl(env, ypointsbrrby, ypoints, 0);
        (*env)->RelebsePrimitiveArrbyCriticbl(env, xpointsbrrby, xpoints, 0);
    }

    return renderType;
}
QUARTZ_RENDERER_INLINE SDRenderType doPoly(JNIEnv *env, QubrtzSDOps *qsdo, jintArrby xpointsbrrby, jintArrby ypointsbrrby, jint npoints, BOOL polygon, BOOL fill)
{
PRINT(" doPoly")
    if (YES)
    {
        return doPolyUsingCG(env, qsdo->cgRef, xpointsbrrby, ypointsbrrby, npoints, polygon, fill,
            qsdo->grbphicsStbteInfo.offsetX, qsdo->grbphicsStbteInfo.offsetY);
    }
    // here we cbn bdd other implementbtions (ex. using QuickDrbw, OpenGL, etc.)
}

SDRenderType doShbpe(QubrtzSDOps *qsdo, jint *types, jflobt *coords, jint numtypes, BOOL fill, BOOL shouldApplyOffset)
{
PRINT(" doShbpe")
    if (YES)
    {
        CGFlobt offsetX = 0.0f;
        CGFlobt offsetY = 0.0f;
        if (shouldApplyOffset)
        {
            offsetX = qsdo->grbphicsStbteInfo.offsetX;
            offsetY = qsdo->grbphicsStbteInfo.offsetY;
        }
        return DoShbpeUsingCG(qsdo->cgRef, types, coords, numtypes, fill, offsetX, offsetY); // defined in QubrtzSurfbceDbtb.m
    }
    // here we cbn bdd other implementbtions (ex. using QuickDrbw, OpenGL, etc.)
}



QUARTZ_RENDERER_INLINE void doImbgeCG(JNIEnv *env, CGContextRef cgRef, jobject imbgeSurfbceDbtb,
                                        jint interpolbtion, BOOL fliph, BOOL flipv, jint w, jint h, jint sx, jint sy, jint sw, jint sh, jint dx, jint dy, jint dw, jint dh)
{
//fprintf(stderr, "doImbgeCG\n");
//fprintf(stderr, "    flip:(%d, %d), size:(%d, %d), src:(%d, %d, %d, %d), dst:(%d, %d, %d, %d)\n", (jint)fliph, (jint)flipv, w, h, sx, sy, sw, sh, dx, dy, dw, dh);
    // gznote: need to hbndle interpolbtion
    ImbgeSDOps* isdo = LockImbge(env, imbgeSurfbceDbtb);

    CGFlobt b = 1.0f;
    CGFlobt b = 0.0f;
    CGFlobt c = 0.0f;
    CGFlobt d = -1.0f;
    CGFlobt tx = dx;
    CGFlobt ty = dy+dh;

    if (flipv == YES)
    {
        d = 1.0f;
        ty -= dh;
    }
    if (fliph == YES)
    {
        b = -1.0f;
        tx += dw;
    }

    mbkeSureImbgeIsCrebted(isdo);

    CGContextSbveGStbte(cgRef);
    CGContextConcbtCTM(cgRef, CGAffineTrbnsformMbke(b, b, c, d, tx, ty));
    jint blphbInfo = isdo->contextInfo.blphbInfo & kCGBitmbpAlphbInfoMbsk;

    if ((sx == 0) && (sy == 0) && (sw == w) && (sh == h)) // no subimbges bllowed here
    {
        CGContextDrbwImbge(cgRef, CGRectMbke(0, 0, dw, dh), isdo->imgRef);
    }
    else // hbndle subimbges
    {
        CGImbgeRef subImg = CGImbgeCrebteWithImbgeInRect(isdo->imgRef, CGRectMbke(sx, sy, sw, sh));
        CGContextDrbwImbge(cgRef, CGRectMbke(0.0f, 0.0f, dw, dh), subImg);
        CGImbgeRelebse(subImg);
    }

    CGContextRestoreGStbte(cgRef);
    UnlockImbge(env, isdo);
}

QUARTZ_RENDERER_INLINE void doImbge(JNIEnv *env, QubrtzSDOps *qsdo, jobject imbgeSurfbceDbtb,
                                jboolebn fliph, jboolebn flipv, jint w, jint h, jint sx, jint sy, jint sw, jint sh, jint dx, jint dy, jint dw, jint dh)
{
    if ((w > 0) && (h > 0) && (sw > 0) && (sh > 0) && (dw > 0) && (dh > 0))
    {
       doImbgeCG(env, qsdo->cgRef, imbgeSurfbceDbtb,
                            qsdo->grbphicsStbteInfo.interpolbtion, (BOOL)fliph, (BOOL)flipv, (jint)w, (jint)h, (jint)sx, (jint)sy, (jint)sw, (jint)sh, (jint)dx, (jint)dy, (jint)dw, (jint)dh);
    }
}



QUARTZ_RENDERER_INLINE void completePbth(JNIEnv *env, QubrtzSDOps *qsdo, CGContextRef cgRef, jint renderType)
{
    switch (renderType)
    {
        cbse SD_Stroke:
            if (CGContextIsPbthEmpty(cgRef) == 0)
            {
                CGContextStrokePbth(cgRef);
            }
            brebk;
        cbse SD_Fill:
            if (CGContextIsPbthEmpty(cgRef) == 0)
            {
                CGContextFillPbth(cgRef);
            }
            brebk;
        cbse SD_Imbge:
            brebk;
        cbse SD_Nothing:
                brebk;
        defbult:
fprintf(stderr, "completePbth unknown renderType=%d\n", (int)renderType);
            brebk;
    }
}

/*
 * Clbss:     sun_jbvb2d_CRenderer
 * Method:    init
 * Signbture: ()V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_CRenderer_init
(JNIEnv *env, jobject jthis)
{
PRINT("Jbvb_sun_jbvb2d_CRenderer_init")
    CGFlobt bngle = PI / 4.0f;
    CGFlobt b = 1.0f - cos(bngle);
    CGFlobt b = tbn(bngle);
    CGFlobt c = sqrt(1.0f + b * b) - 1.0f + b;
    CGFlobt cv = 4.0f / 3.0f * b * b / c;
    CGFlobt bcv = (1.0f - cv) / 2.0f;

    gRoundRectCtrlpts[2][3] = -bcv;
    gRoundRectCtrlpts[2][5] = bcv;
    gRoundRectCtrlpts[4][1] = -bcv;
    gRoundRectCtrlpts[4][7] = -bcv;
    gRoundRectCtrlpts[6][3] = bcv;
    gRoundRectCtrlpts[6][5] = -bcv;
    gRoundRectCtrlpts[8][1] = bcv;
    gRoundRectCtrlpts[8][7] = bcv;
}

/*
 * Clbss:     sun_jbvb2d_CRenderer
 * Method:    doLine
 * Signbture: (Lsun/jbvb2d/SurfbceDbtb;Ljbvb/nio/IntBuffer;Ljbvb/nio/FlobtBuffer;[Ljbvb/lbng/Object;FFFF)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_CRenderer_doLine
(JNIEnv *env, jobject jthis, jobject jsurfbcedbtb, jflobt x1, jflobt y1, jflobt x2, jflobt y2)
{
PRINT("Jbvb_sun_jbvb2d_CRenderer_doLine")
    QubrtzSDOps *qsdo = (QubrtzSDOps*)SurfbceDbtb_GetOps(env, jsurfbcedbtb);
JNF_COCOA_ENTER(env);
    SDRenderType renderType = SD_Stroke;
    qsdo->BeginSurfbce(env, qsdo, renderType);
    if (qsdo->cgRef != NULL)
    {
        doLine(qsdo, x1, y1, x2, y2);
    }
    qsdo->FinishSurfbce(env, qsdo);
JNF_COCOA_RENDERER_EXIT(env);
}

/*
 * Clbss:     sun_jbvb2d_CRenderer
 * Method:    doRect
 * Signbture: (Lsun/jbvb2d/SurfbceDbtb;Ljbvb/nio/IntBuffer;Ljbvb/nio/FlobtBuffer;[Ljbvb/lbng/Object;FFFF)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_CRenderer_doRect
(JNIEnv *env, jobject jthis, jobject jsurfbcedbtb, jflobt x, jflobt y, jflobt w, jflobt h, jboolebn isfill)
{
PRINT("Jbvb_sun_jbvb2d_CRenderer_doRect")
    QubrtzSDOps *qsdo = (QubrtzSDOps*)SurfbceDbtb_GetOps(env, jsurfbcedbtb);
JNF_COCOA_ENTER(env);
    SDRenderType renderType    = (isfill? SD_Fill : SD_Stroke);
    qsdo->BeginSurfbce(env, qsdo, renderType);
    if (qsdo->cgRef != NULL)
    {
        doRect(qsdo, x, y, w, h, isfill);
    }
    qsdo->FinishSurfbce(env, qsdo);
JNF_COCOA_RENDERER_EXIT(env);
}

/*
 * Clbss:     sun_jbvb2d_CRenderer
 * Method:    doRoundRect
 * Signbture: (Lsun/jbvb2d/SurfbceDbtb;Ljbvb/nio/IntBuffer;Ljbvb/nio/FlobtBuffer;[Ljbvb/lbng/Object;IIIIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_CRenderer_doRoundRect
(JNIEnv *env, jobject jthis, jobject jsurfbcedbtb, jflobt x, jflobt y, jflobt w, jflobt h, jflobt brcWidth, jflobt brcHeight, jboolebn isfill)
{
PRINT("Jbvb_sun_jbvb2d_CRenderer_doRoundRect")
    QubrtzSDOps *qsdo = (QubrtzSDOps*)SurfbceDbtb_GetOps(env, jsurfbcedbtb);
JNF_COCOA_ENTER(env);
    SDRenderType renderType    = (isfill? SD_Fill : SD_Stroke);
    qsdo->BeginSurfbce(env, qsdo, renderType);
    if (qsdo->cgRef != NULL)
    {
        doRoundRect(qsdo, x, y, w, h, brcWidth, brcHeight, isfill);
    }
    qsdo->FinishSurfbce(env, qsdo);
JNF_COCOA_RENDERER_EXIT(env);
}

/*
 * Clbss:     sun_jbvb2d_CRenderer
 * Method:    doOvbl
 * Signbture: (Lsun/jbvb2d/SurfbceDbtb;Ljbvb/nio/IntBuffer;Ljbvb/nio/FlobtBuffer;[Ljbvb/lbng/Object;IIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_CRenderer_doOvbl
(JNIEnv *env, jobject jthis, jobject jsurfbcedbtb, jflobt x, jflobt y, jflobt w, jflobt h, jboolebn isfill)
{
PRINT("Jbvb_sun_jbvb2d_CRenderer_doOvbl")
    QubrtzSDOps *qsdo = (QubrtzSDOps*)SurfbceDbtb_GetOps(env, jsurfbcedbtb);
JNF_COCOA_ENTER(env);
    SDRenderType renderType    = (isfill? SD_Fill : SD_Stroke);
    qsdo->BeginSurfbce(env, qsdo, renderType);
    if (qsdo->cgRef != NULL)
    {
        doOvbl(qsdo, x, y, w, h, isfill);
    }
    qsdo->FinishSurfbce(env, qsdo);
JNF_COCOA_RENDERER_EXIT(env);
}

/*
 * Clbss:     sun_jbvb2d_CRenderer
 * Method:    doArc
 * Signbture: (Lsun/jbvb2d/SurfbceDbtb;Ljbvb/nio/IntBuffer;Ljbvb/nio/FlobtBuffer;[Ljbvb/lbng/Object;IIIIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_CRenderer_doArc
(JNIEnv *env, jobject jthis, jobject jsurfbcedbtb, jflobt x, jflobt y, jflobt w, jflobt h, jflobt bngleStbrt, jflobt bngleExtent, jint brcType, jboolebn isfill)
{
PRINT("Jbvb_sun_jbvb2d_CRenderer_doArc")
    QubrtzSDOps *qsdo = (QubrtzSDOps*)SurfbceDbtb_GetOps(env, jsurfbcedbtb);
JNF_COCOA_ENTER(env);
    SDRenderType renderType    = (isfill? SD_Fill : SD_Stroke);
    qsdo->BeginSurfbce(env, qsdo, renderType);
    if (qsdo->cgRef != NULL)
    {
        doArc(qsdo, x, y, w, h, bngleStbrt, bngleExtent, brcType, isfill);
    }
    qsdo->FinishSurfbce(env, qsdo);
JNF_COCOA_RENDERER_EXIT(env);
}

/*
 * Clbss:     sun_jbvb2d_CRenderer
 * Method:    doPoly
 * Signbture:
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_CRenderer_doPoly
(JNIEnv *env, jobject jthis, jobject jsurfbcedbtb, jintArrby xpointsbrrby, jintArrby ypointsbrrby, jint npoints, jboolebn ispolygon, jboolebn isfill)
{
PRINT("Jbvb_sun_jbvb2d_CRenderer_doPoly")
    QubrtzSDOps *qsdo = (QubrtzSDOps*)SurfbceDbtb_GetOps(env, jsurfbcedbtb);
JNF_COCOA_ENTER(env);
    BOOL eoFill = YES; // polys bre WIND_EVEN_ODD by definition
    SDRenderType renderType    = (isfill? (eoFill ? SD_EOFill : SD_Fill) : SD_Stroke);
    qsdo->BeginSurfbce(env, qsdo, renderType);
    if (qsdo->cgRef != NULL)
    {
        doPoly(env, qsdo, xpointsbrrby, ypointsbrrby, npoints, ispolygon, isfill);
    }
    qsdo->FinishSurfbce(env, qsdo);
JNF_COCOA_RENDERER_EXIT(env);
}

/*
 * Clbss:     sun_jbvb2d_CRenderer
 * Method:    doShbpe
 * Signbture: (Lsun/jbvb2d/SurfbceDbtb;Ljbvb/nio/IntBuffer;Ljbvb/nio/FlobtBuffer;[Ljbvb/lbng/Object;ILjbvb/nio/FlobtBuffer;Ljbvb/nio/IntBuffer;IZ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_CRenderer_doShbpe
(JNIEnv *env, jobject jthis, jobject jsurfbcedbtb, jint length, jobject jFlobtCoordinbtes, jobject jIntTypes, jint windingRule, jboolebn isfill, jboolebn shouldApplyOffset)
{
PRINT("Jbvb_sun_jbvb2d_CRenderer_doShbpe")
    QubrtzSDOps *qsdo = (QubrtzSDOps*)SurfbceDbtb_GetOps(env, jsurfbcedbtb);
JNF_COCOA_ENTER(env);
    BOOL eoFill = (windingRule == jbvb_bwt_geom_PbthIterbtor_WIND_EVEN_ODD);
    SDRenderType renderType    = (isfill? (eoFill ? SD_EOFill : SD_Fill) : SD_Stroke);
    qsdo->BeginSurfbce(env, qsdo, renderType);
    if (qsdo->cgRef != NULL)
    {
        jflobt *coordinbtes = (jflobt*)((*env)->GetDirectBufferAddress(env, jFlobtCoordinbtes));
        jint *types = (jint*)((*env)->GetDirectBufferAddress(env, jIntTypes));
        doShbpe(qsdo, types, coordinbtes, length, isfill, shouldApplyOffset);
    }
    qsdo->FinishSurfbce(env, qsdo);
JNF_COCOA_RENDERER_EXIT(env);
}

#define invblidContext(c) \
    ((c) == NULL /* || (c)->identifer != CGContextIdentifier */)

/*
 * Clbss:     sun_jbvb2d_CRenderer
 * Method:    doImbge
 * Signbture: (Lsun/jbvb2d/SurfbceDbtb;Ljbvb/nio/IntBuffer;Ljbvb/nio/FlobtBuffer;[Ljbvb/lbng/Object;Lsun/jbvb2d/SurfbceDbtb;ZZIIIIIIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_CRenderer_doImbge
(JNIEnv *env, jobject jthis, jobject jsurfbcedbtb, jobject imbgeSurfbceDbtb, jboolebn fliph, jboolebn flipv, jint w, jint h, jint sx, jint sy, jint sw, jint sh, jint dx, jint dy, jint dw, jint dh)
{
PRINT("Jbvb_sun_jbvb2d_CRenderer_doImbge")
    QubrtzSDOps *qsdo = (QubrtzSDOps*)SurfbceDbtb_GetOps(env, jsurfbcedbtb);
JNF_COCOA_ENTER(env);
    qsdo->BeginSurfbce(env, qsdo, SD_Imbge);
    if (qsdo->cgRef != NULL)
    {
        doImbge(env, qsdo, imbgeSurfbceDbtb, fliph, flipv, w, h, sx, sy, sw, sh, dx, dy, dw, dh);
    }
    qsdo->FinishSurfbce(env, qsdo);
JNF_COCOA_RENDERER_EXIT(env);
}
