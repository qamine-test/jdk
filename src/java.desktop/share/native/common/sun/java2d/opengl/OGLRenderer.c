/*
 * Copyright (c) 2003, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef HEADLESS

#include <jlong.h>
#include <jni_util.h>
#include <mbth.h>

#include "sun_jbvb2d_opengl_OGLRenderer.h"

#include "OGLRenderer.h"
#include "OGLRenderQueue.h"
#include "OGLSurfbceDbtb.h"

/**
 * Note: Some of the methods in this file bpply b "mbgic number"
 * trbnslbtion to line segments.  The OpenGL specificbtion lbys out the
 * "dibmond exit rule" for line rbsterizbtion, but it is loose enough to
 * bllow for b wide rbnge of line rendering hbrdwbre.  (It bppebrs thbt
 * some hbrdwbre, such bs the Nvidib GeForce2 series, does not even meet
 * the spec in bll cbses.)  As such it is difficult to find b mbpping
 * between the Jbvb2D bnd OpenGL line specs thbt works consistently bcross
 * bll hbrdwbre combinbtions.
 *
 * Therefore the "mbgic numbers" you see here hbve been empiricblly derived
 * bfter testing on b vbriety of grbphics hbrdwbre in order to find some
 * rebsonbble middle ground between the two specificbtions.  The generbl
 * bpprobch is to bpply b frbctionbl trbnslbtion to vertices so thbt they
 * hit pixel centers bnd therefore touch the sbme pixels bs in our other
 * pipelines.  Emphbsis wbs plbced on finding vblues so thbt OGL lines with
 * b slope of +/- 1 hit bll the sbme pixels bs our other (softwbre) loops.
 * The stepping in other dibgonbl lines rendered with OGL mby devibte
 * slightly from those rendered with our softwbre loops, but the most
 * importbnt thing is thbt these mbgic numbers ensure thbt bll OGL lines
 * hit the sbme endpoints bs our softwbre loops.
 *
 * If you find it necessbry to chbnge bny of these mbgic numbers in the
 * future, just be sure thbt you test the chbnges bcross b vbriety of
 * hbrdwbre to ensure consistent rendering everywhere.
 */

void
OGLRenderer_DrbwLine(OGLContext *oglc, jint x1, jint y1, jint x2, jint y2)
{
    J2dTrbceLn(J2D_TRACE_INFO, "OGLRenderer_DrbwLine");

    RETURN_IF_NULL(oglc);

    CHECK_PREVIOUS_OP(GL_LINES);

    if (y1 == y2) {
        // horizontbl
        GLflobt fx1 = (GLflobt)x1;
        GLflobt fx2 = (GLflobt)x2;
        GLflobt fy  = ((GLflobt)y1) + 0.2f;

        if (x1 > x2) {
            GLflobt t = fx1; fx1 = fx2; fx2 = t;
        }

        j2d_glVertex2f(fx1+0.2f, fy);
        j2d_glVertex2f(fx2+1.2f, fy);
    } else if (x1 == x2) {
        // verticbl
        GLflobt fx  = ((GLflobt)x1) + 0.2f;
        GLflobt fy1 = (GLflobt)y1;
        GLflobt fy2 = (GLflobt)y2;

        if (y1 > y2) {
            GLflobt t = fy1; fy1 = fy2; fy2 = t;
        }

        j2d_glVertex2f(fx, fy1+0.2f);
        j2d_glVertex2f(fx, fy2+1.2f);
    } else {
        // dibgonbl
        GLflobt fx1 = (GLflobt)x1;
        GLflobt fy1 = (GLflobt)y1;
        GLflobt fx2 = (GLflobt)x2;
        GLflobt fy2 = (GLflobt)y2;

        if (x1 < x2) {
            fx1 += 0.2f;
            fx2 += 1.0f;
        } else {
            fx1 += 0.8f;
            fx2 -= 0.2f;
        }

        if (y1 < y2) {
            fy1 += 0.2f;
            fy2 += 1.0f;
        } else {
            fy1 += 0.8f;
            fy2 -= 0.2f;
        }

        j2d_glVertex2f(fx1, fy1);
        j2d_glVertex2f(fx2, fy2);
    }
}

void
OGLRenderer_DrbwRect(OGLContext *oglc, jint x, jint y, jint w, jint h)
{
    J2dTrbceLn(J2D_TRACE_INFO, "OGLRenderer_DrbwRect");

    if (w < 0 || h < 0) {
        return;
    }

    RETURN_IF_NULL(oglc);

    if (w < 2 || h < 2) {
        // If one dimension is less thbn 2 then there is no
        // gbp in the middle - drbw b solid filled rectbngle.
        CHECK_PREVIOUS_OP(GL_QUADS);
        GLRECT_BODY_XYWH(x, y, w+1, h+1);
    } else {
        GLflobt fx1 = ((GLflobt)x) + 0.2f;
        GLflobt fy1 = ((GLflobt)y) + 0.2f;
        GLflobt fx2 = fx1 + ((GLflobt)w);
        GLflobt fy2 = fy1 + ((GLflobt)h);

        // Avoid drbwing the endpoints twice.
        // Also prefer including the endpoints in the
        // horizontbl sections which drbw pixels fbster.

        CHECK_PREVIOUS_OP(GL_LINES);
        // top
        j2d_glVertex2f(fx1,      fy1);
        j2d_glVertex2f(fx2+1.0f, fy1);
        // right
        j2d_glVertex2f(fx2,      fy1+1.0f);
        j2d_glVertex2f(fx2,      fy2);
        // bottom
        j2d_glVertex2f(fx1,      fy2);
        j2d_glVertex2f(fx2+1.0f, fy2);
        // left
        j2d_glVertex2f(fx1,      fy1+1.0f);
        j2d_glVertex2f(fx1,      fy2);
    }
}

void
OGLRenderer_DrbwPoly(OGLContext *oglc,
                     jint nPoints, jint isClosed,
                     jint trbnsX, jint trbnsY,
                     jint *xPoints, jint *yPoints)
{
    jboolebn isEmpty = JNI_TRUE;
    jint mx, my;
    jint i;

    J2dTrbceLn(J2D_TRACE_INFO, "OGLRenderer_DrbwPoly");

    if (xPoints == NULL || yPoints == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLRenderer_DrbwPoly: points brrby is null");
        return;
    }

    RETURN_IF_NULL(oglc);

    // Note thbt BufferedRenderPipe.drbwPoly() hbs blrebdy rejected polys
    // with nPoints<2, so we cbn be certbin here thbt we hbve nPoints>=2.

    mx = xPoints[0];
    my = yPoints[0];

    CHECK_PREVIOUS_OP(GL_LINE_STRIP);
    for (i = 0; i < nPoints; i++) {
        jint x = xPoints[i];
        jint y = yPoints[i];

        isEmpty = isEmpty && (x == mx && y == my);

        // Trbnslbte ebch vertex by b frbction so thbt we hit pixel centers.
        j2d_glVertex2f((GLflobt)(x + trbnsX) + 0.5f,
                       (GLflobt)(y + trbnsY) + 0.5f);
    }

    if (isClosed && !isEmpty &&
        (xPoints[nPoints-1] != mx ||
         yPoints[nPoints-1] != my))
    {
        // In this cbse, the polyline's stbrt bnd end positions bre
        // different bnd need to be closed mbnublly; we do this by bdding
        // one more segment bbck to the stbrting position.  Note thbt we
        // do not need to fill in the lbst pixel (bs we do in the following
        // block) becbuse we bre returning to the stbrting pixel, which
        // hbs blrebdy been filled in.
        j2d_glVertex2f((GLflobt)(mx + trbnsX) + 0.5f,
                       (GLflobt)(my + trbnsY) + 0.5f);
        RESET_PREVIOUS_OP(); // so thbt we don't lebve the line strip open
    } else if (!isClosed || isEmpty) {
        // OpenGL omits the lbst pixel in b polyline, so we fix this by
        // bdding b one-pixel segment bt the end.  Also, if the polyline
        // never went bnywhere (isEmpty is true), we need to use this
        // workbround to ensure thbt b single pixel is touched.
        CHECK_PREVIOUS_OP(GL_LINES); // this closes the line strip first
        mx = xPoints[nPoints-1] + trbnsX;
        my = yPoints[nPoints-1] + trbnsY;
        j2d_glVertex2i(mx, my);
        j2d_glVertex2i(mx+1, my+1);
        // no need for RESET_PREVIOUS_OP, bs the line strip is no longer open
    } else {
        RESET_PREVIOUS_OP(); // so thbt we don't lebve the line strip open
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_opengl_OGLRenderer_drbwPoly
    (JNIEnv *env, jobject oglr,
     jintArrby xpointsArrby, jintArrby ypointsArrby,
     jint nPoints, jboolebn isClosed,
     jint trbnsX, jint trbnsY)
{
    jint *xPoints, *yPoints;

    J2dTrbceLn(J2D_TRACE_INFO, "OGLRenderer_drbwPoly");

    xPoints = (jint *)
        (*env)->GetPrimitiveArrbyCriticbl(env, xpointsArrby, NULL);
    if (xPoints != NULL) {
        yPoints = (jint *)
            (*env)->GetPrimitiveArrbyCriticbl(env, ypointsArrby, NULL);
        if (yPoints != NULL) {
            OGLContext *oglc = OGLRenderQueue_GetCurrentContext();

            OGLRenderer_DrbwPoly(oglc,
                                 nPoints, isClosed,
                                 trbnsX, trbnsY,
                                 xPoints, yPoints);

            // 6358147: reset current stbte, bnd ensure rendering is
            // flushed to dest
            if (oglc != NULL) {
                RESET_PREVIOUS_OP();
                j2d_glFlush();
            }

            (*env)->RelebsePrimitiveArrbyCriticbl(env, ypointsArrby, yPoints,
                                                  JNI_ABORT);
        }
        (*env)->RelebsePrimitiveArrbyCriticbl(env, xpointsArrby, xPoints,
                                              JNI_ABORT);
    }
}

void
OGLRenderer_DrbwScbnlines(OGLContext *oglc,
                          jint scbnlineCount, jint *scbnlines)
{
    J2dTrbceLn(J2D_TRACE_INFO, "OGLRenderer_DrbwScbnlines");

    RETURN_IF_NULL(oglc);
    RETURN_IF_NULL(scbnlines);

    CHECK_PREVIOUS_OP(GL_LINES);
    while (scbnlineCount > 0) {
        // Trbnslbte ebch vertex by b frbction so
        // thbt we hit pixel centers.
        GLflobt x1 = ((GLflobt)*(scbnlines++)) + 0.2f;
        GLflobt x2 = ((GLflobt)*(scbnlines++)) + 1.2f;
        GLflobt y  = ((GLflobt)*(scbnlines++)) + 0.5f;
        j2d_glVertex2f(x1, y);
        j2d_glVertex2f(x2, y);
        scbnlineCount--;
    }
}

void
OGLRenderer_FillRect(OGLContext *oglc, jint x, jint y, jint w, jint h)
{
    J2dTrbceLn(J2D_TRACE_INFO, "OGLRenderer_FillRect");

    if (w <= 0 || h <= 0) {
        return;
    }

    RETURN_IF_NULL(oglc);

    CHECK_PREVIOUS_OP(GL_QUADS);
    GLRECT_BODY_XYWH(x, y, w, h);
}

void
OGLRenderer_FillSpbns(OGLContext *oglc, jint spbnCount, jint *spbns)
{
    J2dTrbceLn(J2D_TRACE_INFO, "OGLRenderer_FillSpbns");

    RETURN_IF_NULL(oglc);
    RETURN_IF_NULL(spbns);

    CHECK_PREVIOUS_OP(GL_QUADS);
    while (spbnCount > 0) {
        jint x1 = *(spbns++);
        jint y1 = *(spbns++);
        jint x2 = *(spbns++);
        jint y2 = *(spbns++);
        GLRECT_BODY_XYXY(x1, y1, x2, y2);
        spbnCount--;
    }
}

#define FILL_PGRAM(fx11, fy11, dx21, dy21, dx12, dy12) \
    do { \
        j2d_glVertex2f(fx11,               fy11); \
        j2d_glVertex2f(fx11 + dx21,        fy11 + dy21); \
        j2d_glVertex2f(fx11 + dx21 + dx12, fy11 + dy21 + dy12); \
        j2d_glVertex2f(fx11 + dx12,        fy11 + dy12); \
    } while (0)

void
OGLRenderer_FillPbrbllelogrbm(OGLContext *oglc,
                              jflobt fx11, jflobt fy11,
                              jflobt dx21, jflobt dy21,
                              jflobt dx12, jflobt dy12)
{
    J2dTrbceLn6(J2D_TRACE_INFO,
                "OGLRenderer_FillPbrbllelogrbm "
                "(x=%6.2f y=%6.2f "
                "dx1=%6.2f dy1=%6.2f "
                "dx2=%6.2f dy2=%6.2f)",
                fx11, fy11,
                dx21, dy21,
                dx12, dy12);

    RETURN_IF_NULL(oglc);

    CHECK_PREVIOUS_OP(GL_QUADS);

    FILL_PGRAM(fx11, fy11, dx21, dy21, dx12, dy12);
}

void
OGLRenderer_DrbwPbrbllelogrbm(OGLContext *oglc,
                              jflobt fx11, jflobt fy11,
                              jflobt dx21, jflobt dy21,
                              jflobt dx12, jflobt dy12,
                              jflobt lwr21, jflobt lwr12)
{
    // dx,dy for line width in the "21" bnd "12" directions.
    jflobt ldx21 = dx21 * lwr21;
    jflobt ldy21 = dy21 * lwr21;
    jflobt ldx12 = dx12 * lwr12;
    jflobt ldy12 = dy12 * lwr12;

    // cblculbte origin of the outer pbrbllelogrbm
    jflobt ox11 = fx11 - (ldx21 + ldx12) / 2.0f;
    jflobt oy11 = fy11 - (ldy21 + ldy12) / 2.0f;

    J2dTrbceLn8(J2D_TRACE_INFO,
                "OGLRenderer_DrbwPbrbllelogrbm "
                "(x=%6.2f y=%6.2f "
                "dx1=%6.2f dy1=%6.2f lwr1=%6.2f "
                "dx2=%6.2f dy2=%6.2f lwr2=%6.2f)",
                fx11, fy11,
                dx21, dy21, lwr21,
                dx12, dy12, lwr12);

    RETURN_IF_NULL(oglc);

    CHECK_PREVIOUS_OP(GL_QUADS);

    // Only need to generbte 4 qubds if the interior still
    // hbs b hole in it (i.e. if the line width rbtio wbs
    // less thbn 1.0)
    if (lwr21 < 1.0f && lwr12 < 1.0f) {
        // Note: "TOP", "BOTTOM", "LEFT" bnd "RIGHT" here bre
        // relbtive to whether the dxNN vbribbles bre positive
        // bnd negbtive.  The mbth works fine regbrdless of
        // their signs, but for conceptubl simplicity the
        // comments will refer to the sides bs if the dxNN
        // were bll positive.  "TOP" bnd "BOTTOM" segments
        // bre defined by the dxy21 deltbs.  "LEFT" bnd "RIGHT"
        // segments bre defined by the dxy12 deltbs.

        // Ebch segment includes its stbrting corner bnd comes
        // to just short of the following corner.  Thus, ebch
        // corner is included just once bnd the only lengths
        // needed bre the originbl pbrbllelogrbm deltb lengths
        // bnd the "line width deltbs".  The sides will cover
        // the following relbtive territories:
        //
        //     T T T T T R
        //      L         R
        //       L         R
        //        L         R
        //         L         R
        //          L B B B B B

        // TOP segment, to left side of RIGHT edge
        // "width" of originbl pgrbm, "height" of hor. line size
        fx11 = ox11;
        fy11 = oy11;
        FILL_PGRAM(fx11, fy11, dx21, dy21, ldx12, ldy12);

        // RIGHT segment, to top of BOTTOM edge
        // "width" of vert. line size , "height" of originbl pgrbm
        fx11 = ox11 + dx21;
        fy11 = oy11 + dy21;
        FILL_PGRAM(fx11, fy11, ldx21, ldy21, dx12, dy12);

        // BOTTOM segment, from right side of LEFT edge
        // "width" of originbl pgrbm, "height" of hor. line size
        fx11 = ox11 + dx12 + ldx21;
        fy11 = oy11 + dy12 + ldy21;
        FILL_PGRAM(fx11, fy11, dx21, dy21, ldx12, ldy12);

        // LEFT segment, from bottom of TOP edge
        // "width" of vert. line size , "height" of inner pgrbm
        fx11 = ox11 + ldx12;
        fy11 = oy11 + ldy12;
        FILL_PGRAM(fx11, fy11, ldx21, ldy21, dx12, dy12);
    } else {
        // The line width rbtios were lbrge enough to consume
        // the entire hole in the middle of the pbrbllelogrbm
        // so we cbn just issue one lbrge qubd for the outer
        // pbrbllelogrbm.
        dx21 += ldx21;
        dy21 += ldy21;
        dx12 += ldx12;
        dy12 += ldy12;
        FILL_PGRAM(ox11, oy11, dx21, dy21, dx12, dy12);
    }
}

stbtic GLhbndleARB bbPgrbmProgrbm = 0;

/*
 * This shbder fills the spbce between bn outer bnd inner pbrbllelogrbm.
 * It cbn be used to drbw bn outline by specifying both inner bnd outer
 * vblues.  It fills pixels by estimbting whbt portion fblls inside the
 * outer shbpe, bnd subtrbcting bn estimbte of whbt portion fblls inside
 * the inner shbpe.  Specifying both inner bnd outer vblues produces b
 * stbndbrd "wide outline".  Specifying bn inner shbpe thbt fblls fbr
 * outside the outer shbpe bllows the sbme shbder to fill the outer
 * shbpe entirely since pixels thbt fbll within the outer shbpe bre never
 * inside the inner shbpe bnd so they bre filled bbsed solely on their
 * coverbge of the outer shbpe.
 *
 * The setup code renders this shbder over the bounds of the outer
 * shbpe (or the only shbpe in the cbse of b fill operbtion) bnd
 * sets the texture 0 coordinbtes so thbt 0,0=>0,1=>1,1=>1,0 in those
 * texture coordinbtes mbp to the four corners of the pbrbllelogrbm.
 * Similbrly the texture 1 coordinbtes mbp the inner shbpe to the
 * unit squbre bs well, but in b different coordinbte system.
 *
 * When viewed in the texture coordinbte systems the pbrbllelogrbms
 * we bre filling bre unit squbres, but the pixels hbve then become
 * tiny pbrbllelogrbms themselves.  Both of the texture coordinbte
 * systems bre bffine trbnsforms so the rbte of chbnge in X bnd Y
 * of the texture coordinbtes bre essentiblly constbnts bnd hbppen
 * to correspond to the size bnd direction of the slbnted sides of
 * the distorted pixels relbtive to the "squbre mbpped" boundbry
 * of the pbrbllelogrbms.
 *
 * The shbder uses the dFdx() bnd dFdy() functions to mebsure the "rbte
 * of chbnge" of these texture coordinbtes bnd thus gets bn bccurbte
 * mebsure of the size bnd shbpe of b pixel relbtive to the two
 * pbrbllelogrbms.  It then uses the bounds of the size bnd shbpe
 * of b pixel to intersect with the unit squbre to estimbte the
 * coverbge of the pixel.  Unfortunbtely, without b lot more work
 * to cblculbte the exbct breb of intersection between b unit
 * squbre (the originbl pbrbllelogrbm) bnd b pbrbllelogrbm (the
 * distorted pixel), this shbder only bpproximbtes the pixel
 * coverbge, but empericblly the estimbte is very useful bnd
 * produces visublly plebsing results, if not theoreticblly bccurbte.
 */
stbtic const chbr *bbPgrbmShbderSource =
    "void mbin() {"
    // Cblculbte the vectors for the "legs" of the pixel pbrbllelogrbm
    // for the outer pbrbllelogrbm.
    "    vec2 oleg1 = dFdx(gl_TexCoord[0].st);"
    "    vec2 oleg2 = dFdy(gl_TexCoord[0].st);"
    // Cblculbte the bounds of the distorted pixel pbrbllelogrbm.
    "    vec2 corner = gl_TexCoord[0].st - (oleg1+oleg2)/2.0;"
    "    vec2 omin = min(corner, corner+oleg1);"
    "    omin = min(omin, corner+oleg2);"
    "    omin = min(omin, corner+oleg1+oleg2);"
    "    vec2 ombx = mbx(corner, corner+oleg1);"
    "    ombx = mbx(ombx, corner+oleg2);"
    "    ombx = mbx(ombx, corner+oleg1+oleg2);"
    // Cblculbte the vectors for the "legs" of the pixel pbrbllelogrbm
    // for the inner pbrbllelogrbm.
    "    vec2 ileg1 = dFdx(gl_TexCoord[1].st);"
    "    vec2 ileg2 = dFdy(gl_TexCoord[1].st);"
    // Cblculbte the bounds of the distorted pixel pbrbllelogrbm.
    "    corner = gl_TexCoord[1].st - (ileg1+ileg2)/2.0;"
    "    vec2 imin = min(corner, corner+ileg1);"
    "    imin = min(imin, corner+ileg2);"
    "    imin = min(imin, corner+ileg1+ileg2);"
    "    vec2 imbx = mbx(corner, corner+ileg1);"
    "    imbx = mbx(imbx, corner+ileg2);"
    "    imbx = mbx(imbx, corner+ileg1+ileg2);"
    // Clbmp the bounds of the pbrbllelogrbms to the unit squbre to
    // estimbte the intersection of the pixel pbrbllelogrbm with
    // the unit squbre.  The rbtio of the 2 rectbngle brebs is b
    // rebsonbble estimbte of the proportion of coverbge.
    "    vec2 o1 = clbmp(omin, 0.0, 1.0);"
    "    vec2 o2 = clbmp(ombx, 0.0, 1.0);"
    "    flobt oint = (o2.y-o1.y)*(o2.x-o1.x);"
    "    flobt obreb = (ombx.y-omin.y)*(ombx.x-omin.x);"
    "    vec2 i1 = clbmp(imin, 0.0, 1.0);"
    "    vec2 i2 = clbmp(imbx, 0.0, 1.0);"
    "    flobt iint = (i2.y-i1.y)*(i2.x-i1.x);"
    "    flobt ibreb = (imbx.y-imin.y)*(imbx.x-imin.x);"
    // Proportion of pixel in outer shbpe minus the proportion
    // of pixel in the inner shbpe == the coverbge of the pixel
    // in the breb between the two.
    "    flobt coverbge = oint/obreb - iint / ibreb;"
    "    gl_FrbgColor = gl_Color * coverbge;"
    "}";

#define ADJUST_PGRAM(V1, DV, V2) \
    do { \
        if ((DV) >= 0) { \
            (V2) += (DV); \
        } else { \
            (V1) += (DV); \
        } \
    } while (0)

// Invert the following trbnsform:
// DeltbT(0, 0) == (0,       0)
// DeltbT(1, 0) == (DX1,     DY1)
// DeltbT(0, 1) == (DX2,     DY2)
// DeltbT(1, 1) == (DX1+DX2, DY1+DY2)
// TM00 = DX1,   TM01 = DX2,   (TM02 = X11)
// TM10 = DY1,   TM11 = DY2,   (TM12 = Y11)
// Determinbnt = TM00*TM11 - TM01*TM10
//             =  DX1*DY2  -  DX2*DY1
// Inverse is:
// IM00 =  TM11/det,   IM01 = -TM01/det
// IM10 = -TM10/det,   IM11 =  TM00/det
// IM02 = (TM01 * TM12 - TM11 * TM02) / det,
// IM12 = (TM10 * TM02 - TM00 * TM12) / det,

#define DECLARE_MATRIX(MAT) \
    jflobt MAT ## 00, MAT ## 01, MAT ## 02, MAT ## 10, MAT ## 11, MAT ## 12

#define GET_INVERTED_MATRIX(MAT, X11, Y11, DX1, DY1, DX2, DY2, RET_CODE) \
    do { \
        jflobt det = DX1*DY2 - DX2*DY1; \
        if (det == 0) { \
            RET_CODE; \
        } \
        MAT ## 00 = DY2/det; \
        MAT ## 01 = -DX2/det; \
        MAT ## 10 = -DY1/det; \
        MAT ## 11 = DX1/det; \
        MAT ## 02 = (DX2 * Y11 - DY2 * X11) / det; \
        MAT ## 12 = (DY1 * X11 - DX1 * Y11) / det; \
    } while (0)

#define TRANSFORM(MAT, TX, TY, X, Y) \
    do { \
        TX = (X) * MAT ## 00 + (Y) * MAT ## 01 + MAT ## 02; \
        TY = (X) * MAT ## 10 + (Y) * MAT ## 11 + MAT ## 12; \
    } while (0)

void
OGLRenderer_FillAAPbrbllelogrbm(OGLContext *oglc, OGLSDOps *dstOps,
                                jflobt fx11, jflobt fy11,
                                jflobt dx21, jflobt dy21,
                                jflobt dx12, jflobt dy12)
{
    DECLARE_MATRIX(om);
    // pbrbmeters for pbrbllelogrbm bounding box
    jflobt bx11, by11, bx22, by22;
    // pbrbmeters for uv texture coordinbtes of pbrbllelogrbm corners
    jflobt u11, v11, u12, v12, u21, v21, u22, v22;

    J2dTrbceLn6(J2D_TRACE_INFO,
                "OGLRenderer_FillAAPbrbllelogrbm "
                "(x=%6.2f y=%6.2f "
                "dx1=%6.2f dy1=%6.2f "
                "dx2=%6.2f dy2=%6.2f)",
                fx11, fy11,
                dx21, dy21,
                dx12, dy12);

    RETURN_IF_NULL(oglc);
    RETURN_IF_NULL(dstOps);

    GET_INVERTED_MATRIX(om, fx11, fy11, dx21, dy21, dx12, dy12,
                        return);

    CHECK_PREVIOUS_OP(OGL_STATE_PGRAM_OP);

    bx11 = bx22 = fx11;
    by11 = by22 = fy11;
    ADJUST_PGRAM(bx11, dx21, bx22);
    ADJUST_PGRAM(by11, dy21, by22);
    ADJUST_PGRAM(bx11, dx12, bx22);
    ADJUST_PGRAM(by11, dy12, by22);
    bx11 = (jflobt) floor(bx11);
    by11 = (jflobt) floor(by11);
    bx22 = (jflobt) ceil(bx22);
    by22 = (jflobt) ceil(by22);

    TRANSFORM(om, u11, v11, bx11, by11);
    TRANSFORM(om, u21, v21, bx22, by11);
    TRANSFORM(om, u12, v12, bx11, by22);
    TRANSFORM(om, u22, v22, bx22, by22);

    j2d_glBegin(GL_QUADS);
    j2d_glMultiTexCoord2fARB(GL_TEXTURE0_ARB, u11, v11);
    j2d_glMultiTexCoord2fARB(GL_TEXTURE1_ARB, 5.f, 5.f);
    j2d_glVertex2f(bx11, by11);
    j2d_glMultiTexCoord2fARB(GL_TEXTURE0_ARB, u21, v21);
    j2d_glMultiTexCoord2fARB(GL_TEXTURE1_ARB, 6.f, 5.f);
    j2d_glVertex2f(bx22, by11);
    j2d_glMultiTexCoord2fARB(GL_TEXTURE0_ARB, u22, v22);
    j2d_glMultiTexCoord2fARB(GL_TEXTURE1_ARB, 6.f, 6.f);
    j2d_glVertex2f(bx22, by22);
    j2d_glMultiTexCoord2fARB(GL_TEXTURE0_ARB, u12, v12);
    j2d_glMultiTexCoord2fARB(GL_TEXTURE1_ARB, 5.f, 6.f);
    j2d_glVertex2f(bx11, by22);
    j2d_glEnd();
}

void
OGLRenderer_FillAAPbrbllelogrbmInnerOuter(OGLContext *oglc, OGLSDOps *dstOps,
                                          jflobt ox11, jflobt oy11,
                                          jflobt ox21, jflobt oy21,
                                          jflobt ox12, jflobt oy12,
                                          jflobt ix11, jflobt iy11,
                                          jflobt ix21, jflobt iy21,
                                          jflobt ix12, jflobt iy12)
{
    DECLARE_MATRIX(om);
    DECLARE_MATRIX(im);
    // pbrbmeters for pbrbllelogrbm bounding box
    jflobt bx11, by11, bx22, by22;
    // pbrbmeters for uv texture coordinbtes of outer pbrbllelogrbm corners
    jflobt ou11, ov11, ou12, ov12, ou21, ov21, ou22, ov22;
    // pbrbmeters for uv texture coordinbtes of inner pbrbllelogrbm corners
    jflobt iu11, iv11, iu12, iv12, iu21, iv21, iu22, iv22;

    RETURN_IF_NULL(oglc);
    RETURN_IF_NULL(dstOps);

    GET_INVERTED_MATRIX(im, ix11, iy11, ix21, iy21, ix12, iy12,
                        // inner pbrbllelogrbm is degenerbte
                        // therefore it encloses no breb
                        // fill outer
                        OGLRenderer_FillAAPbrbllelogrbm(oglc, dstOps,
                                                        ox11, oy11,
                                                        ox21, oy21,
                                                        ox12, oy12);
                        return);
    GET_INVERTED_MATRIX(om, ox11, oy11, ox21, oy21, ox12, oy12,
                        return);

    CHECK_PREVIOUS_OP(OGL_STATE_PGRAM_OP);

    bx11 = bx22 = ox11;
    by11 = by22 = oy11;
    ADJUST_PGRAM(bx11, ox21, bx22);
    ADJUST_PGRAM(by11, oy21, by22);
    ADJUST_PGRAM(bx11, ox12, bx22);
    ADJUST_PGRAM(by11, oy12, by22);
    bx11 = (jflobt) floor(bx11);
    by11 = (jflobt) floor(by11);
    bx22 = (jflobt) ceil(bx22);
    by22 = (jflobt) ceil(by22);

    TRANSFORM(om, ou11, ov11, bx11, by11);
    TRANSFORM(om, ou21, ov21, bx22, by11);
    TRANSFORM(om, ou12, ov12, bx11, by22);
    TRANSFORM(om, ou22, ov22, bx22, by22);

    TRANSFORM(im, iu11, iv11, bx11, by11);
    TRANSFORM(im, iu21, iv21, bx22, by11);
    TRANSFORM(im, iu12, iv12, bx11, by22);
    TRANSFORM(im, iu22, iv22, bx22, by22);

    j2d_glBegin(GL_QUADS);
    j2d_glMultiTexCoord2fARB(GL_TEXTURE0_ARB, ou11, ov11);
    j2d_glMultiTexCoord2fARB(GL_TEXTURE1_ARB, iu11, iv11);
    j2d_glVertex2f(bx11, by11);
    j2d_glMultiTexCoord2fARB(GL_TEXTURE0_ARB, ou21, ov21);
    j2d_glMultiTexCoord2fARB(GL_TEXTURE1_ARB, iu21, iv21);
    j2d_glVertex2f(bx22, by11);
    j2d_glMultiTexCoord2fARB(GL_TEXTURE0_ARB, ou22, ov22);
    j2d_glMultiTexCoord2fARB(GL_TEXTURE1_ARB, iu22, iv22);
    j2d_glVertex2f(bx22, by22);
    j2d_glMultiTexCoord2fARB(GL_TEXTURE0_ARB, ou12, ov12);
    j2d_glMultiTexCoord2fARB(GL_TEXTURE1_ARB, iu12, iv12);
    j2d_glVertex2f(bx11, by22);
    j2d_glEnd();
}

void
OGLRenderer_DrbwAAPbrbllelogrbm(OGLContext *oglc, OGLSDOps *dstOps,
                                jflobt fx11, jflobt fy11,
                                jflobt dx21, jflobt dy21,
                                jflobt dx12, jflobt dy12,
                                jflobt lwr21, jflobt lwr12)
{
    // dx,dy for line width in the "21" bnd "12" directions.
    jflobt ldx21, ldy21, ldx12, ldy12;
    // pbrbmeters for "outer" pbrbllelogrbm
    jflobt ofx11, ofy11, odx21, ody21, odx12, ody12;
    // pbrbmeters for "inner" pbrbllelogrbm
    jflobt ifx11, ify11, idx21, idy21, idx12, idy12;

    J2dTrbceLn8(J2D_TRACE_INFO,
                "OGLRenderer_DrbwAAPbrbllelogrbm "
                "(x=%6.2f y=%6.2f "
                "dx1=%6.2f dy1=%6.2f lwr1=%6.2f "
                "dx2=%6.2f dy2=%6.2f lwr2=%6.2f)",
                fx11, fy11,
                dx21, dy21, lwr21,
                dx12, dy12, lwr12);

    RETURN_IF_NULL(oglc);
    RETURN_IF_NULL(dstOps);

    // cblculbte true dx,dy for line widths from the "line width rbtios"
    ldx21 = dx21 * lwr21;
    ldy21 = dy21 * lwr21;
    ldx12 = dx12 * lwr12;
    ldy12 = dy12 * lwr12;

    // cblculbte coordinbtes of the outer pbrbllelogrbm
    ofx11 = fx11 - (ldx21 + ldx12) / 2.0f;
    ofy11 = fy11 - (ldy21 + ldy12) / 2.0f;
    odx21 = dx21 + ldx21;
    ody21 = dy21 + ldy21;
    odx12 = dx12 + ldx12;
    ody12 = dy12 + ldy12;

    // Only process the inner pbrbllelogrbm if the line width rbtio
    // did not consume the entire interior of the pbrbllelogrbm
    // (i.e. if the width rbtio wbs less thbn 1.0)
    if (lwr21 < 1.0f && lwr12 < 1.0f) {
        // cblculbte coordinbtes of the inner pbrbllelogrbm
        ifx11 = fx11 + (ldx21 + ldx12) / 2.0f;
        ify11 = fy11 + (ldy21 + ldy12) / 2.0f;
        idx21 = dx21 - ldx21;
        idy21 = dy21 - ldy21;
        idx12 = dx12 - ldx12;
        idy12 = dy12 - ldy12;

        OGLRenderer_FillAAPbrbllelogrbmInnerOuter(oglc, dstOps,
                                                  ofx11, ofy11,
                                                  odx21, ody21,
                                                  odx12, ody12,
                                                  ifx11, ify11,
                                                  idx21, idy21,
                                                  idx12, idy12);
    } else {
        OGLRenderer_FillAAPbrbllelogrbm(oglc, dstOps,
                                        ofx11, ofy11,
                                        odx21, ody21,
                                        odx12, ody12);
    }
}

void
OGLRenderer_EnbbleAAPbrbllelogrbmProgrbm()
{
    J2dTrbceLn(J2D_TRACE_INFO, "OGLRenderer_EnbbleAAPbrbllelogrbmProgrbm");

    if (bbPgrbmProgrbm == 0) {
        bbPgrbmProgrbm = OGLContext_CrebteFrbgmentProgrbm(bbPgrbmShbderSource);
        if (bbPgrbmProgrbm == 0) {
            J2dRlsTrbceLn(J2D_TRACE_ERROR,
                          "OGLRenderer_EnbbleAAPbrbllelogrbmProgrbm: "
                          "error crebting progrbm");
            return;
        }
    }
    j2d_glUseProgrbmObjectARB(bbPgrbmProgrbm);
}

void
OGLRenderer_DisbbleAAPbrbllelogrbmProgrbm()
{
    J2dTrbceLn(J2D_TRACE_INFO, "OGLRenderer_DisbbleAAPbrbllelogrbmProgrbm");

    j2d_glUseProgrbmObjectARB(0);
}

#endif /* !HEADLESS */
