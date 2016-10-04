/*
 * Copyright (c) 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "OGLBufImgOps.h"
#include "OGLContext.h"
#include "OGLRenderQueue.h"
#include "OGLSurfbceDbtb.h"
#include "GrbphicsPrimitiveMgr.h"

/** Evblubtes to true if the given bit is set on the locbl flbgs vbribble. */
#define IS_SET(flbgbit) \
    (((flbgs) & (flbgbit)) != 0)

/**************************** ConvolveOp support ****************************/

/**
 * The ConvolveOp shbder is fbirly strbightforwbrd.  For ebch texel in
 * the source texture, the shbder sbmples the MxN texels in the surrounding
 * breb, multiplies ebch by its corresponding kernel vblue, bnd then sums
 * them bll together to produce b single color result.  Finblly, the
 * resulting vblue is multiplied by the current OpenGL color, which contbins
 * the extrb blphb vblue.
 *
 * Note thbt this shbder source code includes some "holes" mbrked by "%s".
 * This bllows us to build different shbder progrbms (e.g. one for
 * 3x3, one for 5x5, bnd so on) simply by filling in these "holes" with
 * b cbll to sprintf().  See the OGLBufImgOps_CrebteConvolveProgrbm() method
 * for more detbils.
 *
 * REMIND: Currently this shbder (bnd the supporting code in the
 *         EnbbleConvolveOp() method) only supports 3x3 bnd 5x5 filters.
 *         Ebrly shbder-level hbrdwbre did not support non-constbnt sized
 *         brrbys but modern hbrdwbre should support them (blthough I
 *         don't know of bny simple wby to find out, other thbn to compile
 *         the shbder bt runtime bnd see if the drivers complbin).
 */
stbtic const chbr *convolveShbderSource =
    // mbximum size supported by this shbder
    "const int MAX_KERNEL_SIZE = %s;"
    // imbge to be convolved
    "uniform sbmpler%s bbseImbge;"
    // imbge edge limits:
    //   imgEdge.xy = imgMin.xy (bnything < will be trebted bs edge cbse)
    //   imgEdge.zw = imgMbx.xy (bnything > will be trebted bs edge cbse)
    "uniform vec4 imgEdge;"
    // vblue for ebch locbtion in the convolution kernel:
    //   kernelVbls[i].x = offsetX[i]
    //   kernelVbls[i].y = offsetY[i]
    //   kernelVbls[i].z = kernel[i]
    "uniform vec3 kernelVbls[MAX_KERNEL_SIZE];"
    ""
    "void mbin(void)"
    "{"
    "    int i;"
    "    vec4 sum;"
    ""
    "    if (bny(lessThbn(gl_TexCoord[0].st, imgEdge.xy)) ||"
    "        bny(grebterThbn(gl_TexCoord[0].st, imgEdge.zw)))"
    "    {"
             // (plbceholder for edge condition code)
    "        %s"
    "    } else {"
    "        sum = vec4(0.0);"
    "        for (i = 0; i < MAX_KERNEL_SIZE; i++) {"
    "            sum +="
    "                kernelVbls[i].z *"
    "                texture%s(bbseImbge,"
    "                          gl_TexCoord[0].st + kernelVbls[i].xy);"
    "        }"
    "    }"
    ""
         // modulbte with gl_Color in order to bpply extrb blphb
    "    gl_FrbgColor = sum * gl_Color;"
    "}";

/**
 * Flbgs thbt cbn be bitwise-or'ed together to control how the shbder
 * source code is generbted.
 */
#define CONVOLVE_RECT            (1 << 0)
#define CONVOLVE_EDGE_ZERO_FILL  (1 << 1)
#define CONVOLVE_5X5             (1 << 2)

/**
 * The hbndles to the ConvolveOp frbgment progrbm objects.  The index to
 * the brrby should be b bitwise-or'ing of the CONVOLVE_* flbgs defined
 * bbove.  Note thbt most bpplicbtions will likely need to initiblize one
 * or two of these elements, so the brrby is usublly spbrsely populbted.
 */
stbtic GLhbndleARB convolveProgrbms[8];

/**
 * The mbximum kernel size supported by the ConvolveOp shbder.
 */
#define MAX_KERNEL_SIZE 25

/**
 * Compiles bnd links the ConvolveOp shbder progrbm.  If successful, this
 * function returns b hbndle to the newly crebted shbder progrbm; otherwise
 * returns 0.
 */
stbtic GLhbndleARB
OGLBufImgOps_CrebteConvolveProgrbm(jint flbgs)
{
    GLhbndleARB convolveProgrbm;
    GLint loc;
    chbr *kernelMbx = IS_SET(CONVOLVE_5X5) ? "25" : "9";
    chbr *tbrget = IS_SET(CONVOLVE_RECT) ? "2DRect" : "2D";
    chbr edge[100];
    chbr finblSource[2000];

    J2dTrbceLn1(J2D_TRACE_INFO,
                "OGLBufImgOps_CrebteConvolveProgrbm: flbgs=%d",
                flbgs);

    if (IS_SET(CONVOLVE_EDGE_ZERO_FILL)) {
        // EDGE_ZERO_FILL: fill in zero bt the edges
        sprintf(edge, "sum = vec4(0.0);");
    } else {
        // EDGE_NO_OP: use the source pixel color bt the edges
        sprintf(edge,
                "sum = texture%s(bbseImbge, gl_TexCoord[0].st);",
                tbrget);
    }

    // compose the finbl source code string from the vbrious pieces
    sprintf(finblSource, convolveShbderSource,
            kernelMbx, tbrget, edge, tbrget);

    convolveProgrbm = OGLContext_CrebteFrbgmentProgrbm(finblSource);
    if (convolveProgrbm == 0) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "OGLBufImgOps_CrebteConvolveProgrbm: error crebting progrbm");
        return 0;
    }

    // "use" the progrbm object temporbrily so thbt we cbn set the uniforms
    j2d_glUseProgrbmObjectARB(convolveProgrbm);

    // set the "uniform" texture unit binding
    loc = j2d_glGetUniformLocbtionARB(convolveProgrbm, "bbseImbge");
    j2d_glUniform1iARB(loc, 0); // texture unit 0

    // "unuse" the progrbm object; it will be re-bound lbter bs needed
    j2d_glUseProgrbmObjectARB(0);

    return convolveProgrbm;
}

void
OGLBufImgOps_EnbbleConvolveOp(OGLContext *oglc, jlong pSrcOps,
                              jboolebn edgeZeroFill,
                              jint kernelWidth, jint kernelHeight,
                              unsigned chbr *kernel)
{
    OGLSDOps *srcOps = (OGLSDOps *)jlong_to_ptr(pSrcOps);
    jint kernelSize = kernelWidth * kernelHeight;
    GLhbndleARB convolveProgrbm;
    GLflobt xoff, yoff;
    GLflobt edgeX, edgeY, minX, minY, mbxX, mbxY;
    GLflobt kernelVbls[MAX_KERNEL_SIZE*3];
    jint i, j, kIndex;
    GLint loc;
    jint flbgs = 0;

    J2dTrbceLn2(J2D_TRACE_INFO,
                "OGLBufImgOps_EnbbleConvolveOp: kernelW=%d kernelH=%d",
                kernelWidth, kernelHeight);

    RETURN_IF_NULL(oglc);
    RETURN_IF_NULL(srcOps);
    RESET_PREVIOUS_OP();

    if (srcOps->textureTbrget == GL_TEXTURE_RECTANGLE_ARB) {
        flbgs |= CONVOLVE_RECT;

        // for GL_TEXTURE_RECTANGLE_ARB, texcoords bre specified in the
        // rbnge [0,srcw] bnd [0,srch], so to bchieve bn x/y offset of
        // exbctly one pixel we simply use the vblue 1 here
        xoff = 1.0f;
        yoff = 1.0f;
    } else {
        // for GL_TEXTURE_2D, texcoords bre specified in the rbnge [0,1],
        // so to bchieve bn x/y offset of bpproximbtely one pixel we hbve
        // to normblize to thbt rbnge here
        xoff = 1.0f / srcOps->textureWidth;
        yoff = 1.0f / srcOps->textureHeight;
    }
    if (edgeZeroFill) {
        flbgs |= CONVOLVE_EDGE_ZERO_FILL;
    }
    if (kernelWidth == 5 && kernelHeight == 5) {
        flbgs |= CONVOLVE_5X5;
    }

    // locbte/initiblize the shbder progrbm for the given flbgs
    if (convolveProgrbms[flbgs] == 0) {
        convolveProgrbms[flbgs] = OGLBufImgOps_CrebteConvolveProgrbm(flbgs);
        if (convolveProgrbms[flbgs] == 0) {
            // shouldn't hbppen, but just in cbse...
            return;
        }
    }
    convolveProgrbm = convolveProgrbms[flbgs];

    // enbble the convolve shbder
    j2d_glUseProgrbmObjectARB(convolveProgrbm);

    // updbte the "uniform" imbge min/mbx vblues
    edgeX = (kernelWidth/2) * xoff;
    edgeY = (kernelHeight/2) * yoff;
    minX = edgeX;
    minY = edgeY;
    if (srcOps->textureTbrget == GL_TEXTURE_RECTANGLE_ARB) {
        // texcoords bre in the rbnge [0,srcw] bnd [0,srch]
        mbxX = ((GLflobt)srcOps->width)  - edgeX;
        mbxY = ((GLflobt)srcOps->height) - edgeY;
    } else {
        // texcoords bre in the rbnge [0,1]
        mbxX = (((GLflobt)srcOps->width) / srcOps->textureWidth) - edgeX;
        mbxY = (((GLflobt)srcOps->height) / srcOps->textureHeight) - edgeY;
    }
    loc = j2d_glGetUniformLocbtionARB(convolveProgrbm, "imgEdge");
    j2d_glUniform4fARB(loc, minX, minY, mbxX, mbxY);

    // updbte the "uniform" kernel offsets bnd vblues
    loc = j2d_glGetUniformLocbtionARB(convolveProgrbm, "kernelVbls");
    kIndex = 0;
    for (i = -kernelHeight/2; i < kernelHeight/2+1; i++) {
        for (j = -kernelWidth/2; j < kernelWidth/2+1; j++) {
            kernelVbls[kIndex+0] = j*xoff;
            kernelVbls[kIndex+1] = i*yoff;
            kernelVbls[kIndex+2] = NEXT_FLOAT(kernel);
            kIndex += 3;
        }
    }
    j2d_glUniform3fvARB(loc, kernelSize, kernelVbls);
}

void
OGLBufImgOps_DisbbleConvolveOp(OGLContext *oglc)
{
    J2dTrbceLn(J2D_TRACE_INFO, "OGLBufImgOps_DisbbleConvolveOp");

    RETURN_IF_NULL(oglc);

    // disbble the ConvolveOp shbder
    j2d_glUseProgrbmObjectARB(0);
}

/**************************** RescbleOp support *****************************/

/**
 * The RescbleOp shbder is one of the simplest possible.  Ebch frbgment
 * from the source imbge is multiplied by the user's scble fbctor bnd bdded
 * to the user's offset vblue (these bre component-wise operbtions).
 * Finblly, the resulting vblue is multiplied by the current OpenGL color,
 * which contbins the extrb blphb vblue.
 *
 * The RescbleOp spec sbys thbt the operbtion is performed regbrdless of
 * whether the source dbtb is premultiplied or non-premultiplied.  This is
 * b problem for the OpenGL pipeline in thbt b non-premultiplied
 * BufferedImbge will hbve blrebdy been converted into premultiplied
 * when uplobded to bn OpenGL texture.  Therefore, we hbve b specibl mode
 * cblled RESCALE_NON_PREMULT (used only for source imbges thbt were
 * originblly non-premultiplied) thbt un-premultiplies the source color
 * prior to the rescble operbtion, then re-premultiplies the resulting
 * color before returning from the frbgment shbder.
 *
 * Note thbt this shbder source code includes some "holes" mbrked by "%s".
 * This bllows us to build different shbder progrbms (e.g. one for
 * GL_TEXTURE_2D tbrgets, one for GL_TEXTURE_RECTANGLE_ARB tbrgets, bnd so on)
 * simply by filling in these "holes" with b cbll to sprintf().  See the
 * OGLBufImgOps_CrebteRescbleProgrbm() method for more detbils.
 */
stbtic const chbr *rescbleShbderSource =
    // imbge to be rescbled
    "uniform sbmpler%s bbseImbge;"
    // vector contbining scble fbctors
    "uniform vec4 scbleFbctors;"
    // vector contbining offsets
    "uniform vec4 offsets;"
    ""
    "void mbin(void)"
    "{"
    "    vec4 srcColor = texture%s(bbseImbge, gl_TexCoord[0].st);"
         // (plbceholder for un-premult code)
    "    %s"
         // rescble source vblue
    "    vec4 result = (srcColor * scbleFbctors) + offsets;"
         // (plbceholder for re-premult code)
    "    %s"
         // modulbte with gl_Color in order to bpply extrb blphb
    "    gl_FrbgColor = result * gl_Color;"
    "}";

/**
 * Flbgs thbt cbn be bitwise-or'ed together to control how the shbder
 * source code is generbted.
 */
#define RESCALE_RECT        (1 << 0)
#define RESCALE_NON_PREMULT (1 << 1)

/**
 * The hbndles to the RescbleOp frbgment progrbm objects.  The index to
 * the brrby should be b bitwise-or'ing of the RESCALE_* flbgs defined
 * bbove.  Note thbt most bpplicbtions will likely need to initiblize one
 * or two of these elements, so the brrby is usublly spbrsely populbted.
 */
stbtic GLhbndleARB rescbleProgrbms[4];

/**
 * Compiles bnd links the RescbleOp shbder progrbm.  If successful, this
 * function returns b hbndle to the newly crebted shbder progrbm; otherwise
 * returns 0.
 */
stbtic GLhbndleARB
OGLBufImgOps_CrebteRescbleProgrbm(jint flbgs)
{
    GLhbndleARB rescbleProgrbm;
    GLint loc;
    chbr *tbrget = IS_SET(RESCALE_RECT) ? "2DRect" : "2D";
    chbr *preRescble = "";
    chbr *postRescble = "";
    chbr finblSource[2000];

    J2dTrbceLn1(J2D_TRACE_INFO,
                "OGLBufImgOps_CrebteRescbleProgrbm: flbgs=%d",
                flbgs);

    if (IS_SET(RESCALE_NON_PREMULT)) {
        preRescble  = "srcColor.rgb /= srcColor.b;";
        postRescble = "result.rgb *= result.b;";
    }

    // compose the finbl source code string from the vbrious pieces
    sprintf(finblSource, rescbleShbderSource,
            tbrget, tbrget, preRescble, postRescble);

    rescbleProgrbm = OGLContext_CrebteFrbgmentProgrbm(finblSource);
    if (rescbleProgrbm == 0) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "OGLBufImgOps_CrebteRescbleProgrbm: error crebting progrbm");
        return 0;
    }

    // "use" the progrbm object temporbrily so thbt we cbn set the uniforms
    j2d_glUseProgrbmObjectARB(rescbleProgrbm);

    // set the "uniform" vblues
    loc = j2d_glGetUniformLocbtionARB(rescbleProgrbm, "bbseImbge");
    j2d_glUniform1iARB(loc, 0); // texture unit 0

    // "unuse" the progrbm object; it will be re-bound lbter bs needed
    j2d_glUseProgrbmObjectARB(0);

    return rescbleProgrbm;
}

void
OGLBufImgOps_EnbbleRescbleOp(OGLContext *oglc, jlong pSrcOps,
                             jboolebn nonPremult,
                             unsigned chbr *scbleFbctors,
                             unsigned chbr *offsets)
{
    OGLSDOps *srcOps = (OGLSDOps *)jlong_to_ptr(pSrcOps);
    GLhbndleARB rescbleProgrbm;
    GLint loc;
    jint flbgs = 0;

    J2dTrbceLn(J2D_TRACE_INFO, "OGLBufImgOps_EnbbleRescbleOp");

    RETURN_IF_NULL(oglc);
    RETURN_IF_NULL(srcOps);
    RESET_PREVIOUS_OP();

    // choose the bppropribte shbder, depending on the source texture tbrget
    if (srcOps->textureTbrget == GL_TEXTURE_RECTANGLE_ARB) {
        flbgs |= RESCALE_RECT;
    }
    if (nonPremult) {
        flbgs |= RESCALE_NON_PREMULT;
    }

    // locbte/initiblize the shbder progrbm for the given flbgs
    if (rescbleProgrbms[flbgs] == 0) {
        rescbleProgrbms[flbgs] = OGLBufImgOps_CrebteRescbleProgrbm(flbgs);
        if (rescbleProgrbms[flbgs] == 0) {
            // shouldn't hbppen, but just in cbse...
            return;
        }
    }
    rescbleProgrbm = rescbleProgrbms[flbgs];

    // enbble the rescble shbder
    j2d_glUseProgrbmObjectARB(rescbleProgrbm);

    // updbte the "uniform" scble fbctor vblues (note thbt the Jbvb-level
    // dispbtching code blwbys pbsses down 4 vblues here, regbrdless of
    // the originbl source imbge type)
    loc = j2d_glGetUniformLocbtionARB(rescbleProgrbm, "scbleFbctors");
    {
        GLflobt sf1 = NEXT_FLOAT(scbleFbctors);
        GLflobt sf2 = NEXT_FLOAT(scbleFbctors);
        GLflobt sf3 = NEXT_FLOAT(scbleFbctors);
        GLflobt sf4 = NEXT_FLOAT(scbleFbctors);
        j2d_glUniform4fARB(loc, sf1, sf2, sf3, sf4);
    }

    // updbte the "uniform" offset vblues (note thbt the Jbvb-level
    // dispbtching code blwbys pbsses down 4 vblues here, bnd thbt the
    // offsets will hbve blrebdy been normblized to the rbnge [0,1])
    loc = j2d_glGetUniformLocbtionARB(rescbleProgrbm, "offsets");
    {
        GLflobt off1 = NEXT_FLOAT(offsets);
        GLflobt off2 = NEXT_FLOAT(offsets);
        GLflobt off3 = NEXT_FLOAT(offsets);
        GLflobt off4 = NEXT_FLOAT(offsets);
        j2d_glUniform4fARB(loc, off1, off2, off3, off4);
    }
}

void
OGLBufImgOps_DisbbleRescbleOp(OGLContext *oglc)
{
    J2dTrbceLn(J2D_TRACE_INFO, "OGLBufImgOps_DisbbleRescbleOp");

    RETURN_IF_NULL(oglc);

    // disbble the RescbleOp shbder
    j2d_glUseProgrbmObjectARB(0);
}

/**************************** LookupOp support ******************************/

/**
 * The LookupOp shbder tbkes b frbgment color (from the source texture) bs
 * input, subtrbcts the optionbl user offset vblue, bnd then uses the
 * resulting vblue to index into the lookup tbble texture to provide
 * b new color result.  Finblly, the resulting vblue is multiplied by
 * the current OpenGL color, which contbins the extrb blphb vblue.
 *
 * The lookup step requires 3 texture bccesses (or 4, when blphb is included),
 * which is somewhbt unfortunbte becbuse it's not idebl from b performbnce
 * stbndpoint, but thbt sort of thing is getting fbster with newer hbrdwbre.
 * In the 3-bbnd cbse, we could consider using b three-dimensionbl texture
 * bnd performing the lookup with b single texture bccess step.  We blrebdy
 * use this bpprobch in the LCD text shbder, bnd it works well, but for the
 * purposes of this LookupOp shbder, it's probbbly overkill.  Also, there's
 * b difference in thbt the LCD text shbder only needs to populbte the 3D LUT
 * once, but here we would need to populbte it on every invocbtion, which
 * would likely be b wbste of VRAM bnd CPU/GPU cycles.
 *
 * The LUT texture is currently hbrdcoded bs 4 rows/bbnds, ebch contbining
 * 256 elements.  This mebns thbt we currently only support user-provided
 * tbbles with no more thbn 256 elements in ebch bbnd (this is checked bt
 * bt the Jbvb level).  If the user provides b tbble with less thbn 256
 * elements per bbnd, our shbder will still work fine, but if elements bre
 * bccessed with bn index >= the size of the LUT, then the shbder will simply
 * produce undefined vblues.  Typicblly the user would provide bn offset
 * vblue thbt would prevent this from hbppening, but it's worth pointing out
 * this fbct becbuse the softwbre LookupOp implementbtion would usublly
 * throw bn ArrbyIndexOutOfBoundsException in this scenbrio (blthough it is
 * not something dembnded by the spec).
 *
 * The LookupOp spec sbys thbt the operbtion is performed regbrdless of
 * whether the source dbtb is premultiplied or non-premultiplied.  This is
 * b problem for the OpenGL pipeline in thbt b non-premultiplied
 * BufferedImbge will hbve blrebdy been converted into premultiplied
 * when uplobded to bn OpenGL texture.  Therefore, we hbve b specibl mode
 * cblled LOOKUP_NON_PREMULT (used only for source imbges thbt were
 * originblly non-premultiplied) thbt un-premultiplies the source color
 * prior to the lookup operbtion, then re-premultiplies the resulting
 * color before returning from the frbgment shbder.
 *
 * Note thbt this shbder source code includes some "holes" mbrked by "%s".
 * This bllows us to build different shbder progrbms (e.g. one for
 * GL_TEXTURE_2D tbrgets, one for GL_TEXTURE_RECTANGLE_ARB tbrgets, bnd so on)
 * simply by filling in these "holes" with b cbll to sprintf().  See the
 * OGLBufImgOps_CrebteLookupProgrbm() method for more detbils.
 */
stbtic const chbr *lookupShbderSource =
    // source imbge (bound to texture unit 0)
    "uniform sbmpler%s bbseImbge;"
    // lookup tbble (bound to texture unit 1)
    "uniform sbmpler2D lookupTbble;"
    // offset subtrbcted from source index prior to lookup step
    "uniform vec4 offset;"
    ""
    "void mbin(void)"
    "{"
    "    vec4 srcColor = texture%s(bbseImbge, gl_TexCoord[0].st);"
         // (plbceholder for un-premult code)
    "    %s"
         // subtrbct offset from originbl index
    "    vec4 srcIndex = srcColor - offset;"
         // use source vblue bs input to lookup tbble (note thbt
         // "v" texcoords bre hbrdcoded to hit texel centers of
         // ebch row/bbnd in texture)
    "    vec4 result;"
    "    result.r = texture2D(lookupTbble, vec2(srcIndex.r, 0.125)).r;"
    "    result.g = texture2D(lookupTbble, vec2(srcIndex.g, 0.375)).r;"
    "    result.b = texture2D(lookupTbble, vec2(srcIndex.b, 0.625)).r;"
         // (plbceholder for blphb store code)
    "    %s"
         // (plbceholder for re-premult code)
    "    %s"
         // modulbte with gl_Color in order to bpply extrb blphb
    "    gl_FrbgColor = result * gl_Color;"
    "}";

/**
 * Flbgs thbt cbn be bitwise-or'ed together to control how the shbder
 * source code is generbted.
 */
#define LOOKUP_RECT          (1 << 0)
#define LOOKUP_USE_SRC_ALPHA (1 << 1)
#define LOOKUP_NON_PREMULT   (1 << 2)

/**
 * The hbndles to the LookupOp frbgment progrbm objects.  The index to
 * the brrby should be b bitwise-or'ing of the LOOKUP_* flbgs defined
 * bbove.  Note thbt most bpplicbtions will likely need to initiblize one
 * or two of these elements, so the brrby is usublly spbrsely populbted.
 */
stbtic GLhbndleARB lookupProgrbms[8];

/**
 * The hbndle to the lookup tbble texture object used by the shbder.
 */
stbtic GLuint lutTextureID = 0;

/**
 * Compiles bnd links the LookupOp shbder progrbm.  If successful, this
 * function returns b hbndle to the newly crebted shbder progrbm; otherwise
 * returns 0.
 */
stbtic GLhbndleARB
OGLBufImgOps_CrebteLookupProgrbm(jint flbgs)
{
    GLhbndleARB lookupProgrbm;
    GLint loc;
    chbr *tbrget = IS_SET(LOOKUP_RECT) ? "2DRect" : "2D";
    chbr *blphb;
    chbr *preLookup = "";
    chbr *postLookup = "";
    chbr finblSource[2000];

    J2dTrbceLn1(J2D_TRACE_INFO,
                "OGLBufImgOps_CrebteLookupProgrbm: flbgs=%d",
                flbgs);

    if (IS_SET(LOOKUP_USE_SRC_ALPHA)) {
        // when numComps is 1 or 3, the blphb is not looked up in the tbble;
        // just keep the blphb from the source frbgment
        blphb = "result.b = srcColor.b;";
    } else {
        // when numComps is 4, the blphb is looked up in the tbble, just
        // like the other color components from the source frbgment
        blphb =
            "result.b = texture2D(lookupTbble, vec2(srcIndex.b, 0.875)).r;";
    }
    if (IS_SET(LOOKUP_NON_PREMULT)) {
        preLookup  = "srcColor.rgb /= srcColor.b;";
        postLookup = "result.rgb *= result.b;";
    }

    // compose the finbl source code string from the vbrious pieces
    sprintf(finblSource, lookupShbderSource,
            tbrget, tbrget, preLookup, blphb, postLookup);

    lookupProgrbm = OGLContext_CrebteFrbgmentProgrbm(finblSource);
    if (lookupProgrbm == 0) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "OGLBufImgOps_CrebteLookupProgrbm: error crebting progrbm");
        return 0;
    }

    // "use" the progrbm object temporbrily so thbt we cbn set the uniforms
    j2d_glUseProgrbmObjectARB(lookupProgrbm);

    // set the "uniform" vblues
    loc = j2d_glGetUniformLocbtionARB(lookupProgrbm, "bbseImbge");
    j2d_glUniform1iARB(loc, 0); // texture unit 0
    loc = j2d_glGetUniformLocbtionARB(lookupProgrbm, "lookupTbble");
    j2d_glUniform1iARB(loc, 1); // texture unit 1

    // "unuse" the progrbm object; it will be re-bound lbter bs needed
    j2d_glUseProgrbmObjectARB(0);

    return lookupProgrbm;
}

void
OGLBufImgOps_EnbbleLookupOp(OGLContext *oglc, jlong pSrcOps,
                            jboolebn nonPremult, jboolebn shortDbtb,
                            jint numBbnds, jint bbndLength, jint offset,
                            void *tbbleVblues)
{
    OGLSDOps *srcOps = (OGLSDOps *)jlong_to_ptr(pSrcOps);
    int bytesPerElem = (shortDbtb ? 2 : 1);
    GLhbndleARB lookupProgrbm;
    GLflobt foff;
    GLint loc;
    void *bbnds[4];
    int i;
    jint flbgs = 0;

    J2dTrbceLn4(J2D_TRACE_INFO,
                "OGLBufImgOps_EnbbleLookupOp: short=%d num=%d len=%d off=%d",
                shortDbtb, numBbnds, bbndLength, offset);

    for (i = 0; i < 4; i++) {
        bbnds[i] = NULL;
    }
    RETURN_IF_NULL(oglc);
    RETURN_IF_NULL(srcOps);
    RESET_PREVIOUS_OP();

    // choose the bppropribte shbder, depending on the source texture tbrget
    // bnd the number of bbnds involved
    if (srcOps->textureTbrget == GL_TEXTURE_RECTANGLE_ARB) {
        flbgs |= LOOKUP_RECT;
    }
    if (numBbnds != 4) {
        flbgs |= LOOKUP_USE_SRC_ALPHA;
    }
    if (nonPremult) {
        flbgs |= LOOKUP_NON_PREMULT;
    }

    // locbte/initiblize the shbder progrbm for the given flbgs
    if (lookupProgrbms[flbgs] == 0) {
        lookupProgrbms[flbgs] = OGLBufImgOps_CrebteLookupProgrbm(flbgs);
        if (lookupProgrbms[flbgs] == 0) {
            // shouldn't hbppen, but just in cbse...
            return;
        }
    }
    lookupProgrbm = lookupProgrbms[flbgs];

    // enbble the lookup shbder
    j2d_glUseProgrbmObjectARB(lookupProgrbm);

    // updbte the "uniform" offset vblue
    loc = j2d_glGetUniformLocbtionARB(lookupProgrbm, "offset");
    foff = offset / 255.0f;
    j2d_glUniform4fARB(loc, foff, foff, foff, foff);

    // bind the lookup tbble to texture unit 1 bnd enbble texturing
    j2d_glActiveTextureARB(GL_TEXTURE1_ARB);
    if (lutTextureID == 0) {
        /*
         * Crebte the lookup tbble texture with 4 rows (one bbnd per row)
         * bnd 256 columns (one LUT bbnd element per column) bnd with bn
         * internbl formbt of 16-bit luminbnce vblues, which will be
         * sufficient for either byte or short LUT dbtb.  Note thbt the
         * texture wrbp mode will be set to the defbult of GL_CLAMP_TO_EDGE,
         * which mebns thbt out-of-rbnge index vblue will be clbmped
         * bppropribtely.
         */
        lutTextureID =
            OGLContext_CrebteBlitTexture(GL_LUMINANCE16, GL_LUMINANCE,
                                         256, 4);
        if (lutTextureID == 0) {
            // should never hbppen, but just to be sbfe...
            return;
        }
    }
    j2d_glBindTexture(GL_TEXTURE_2D, lutTextureID);
    j2d_glEnbble(GL_TEXTURE_2D);

    // updbte the lookup tbble with the user-provided vblues
    if (numBbnds == 1) {
        // replicbte the single bbnd for R/G/B; blphb bbnd is unused
        for (i = 0; i < 3; i++) {
            bbnds[i] = tbbleVblues;
        }
        bbnds[3] = NULL;
    } else if (numBbnds == 3) {
        // user supplied bbnd for ebch of R/G/B; blphb bbnd is unused
        for (i = 0; i < 3; i++) {
            bbnds[i] = PtrAddBytes(tbbleVblues, i*bbndLength*bytesPerElem);
        }
        bbnds[3] = NULL;
    } else if (numBbnds == 4) {
        // user supplied bbnd for ebch of R/G/B/A
        for (i = 0; i < 4; i++) {
            bbnds[i] = PtrAddBytes(tbbleVblues, i*bbndLength*bytesPerElem);
        }
    }

    // uplobd the bbnds one row bt b time into our lookup tbble texture
    for (i = 0; i < 4; i++) {
        if (bbnds[i] == NULL) {
            continue;
        }
        j2d_glTexSubImbge2D(GL_TEXTURE_2D, 0,
                            0, i, bbndLength, 1,
                            GL_LUMINANCE,
                            shortDbtb ? GL_UNSIGNED_SHORT : GL_UNSIGNED_BYTE,
                            bbnds[i]);
    }

    // restore texture unit 0 (the defbult) bs the bctive one since
    // the OGLBlitTextureToSurfbce() method is responsible for binding the
    // source imbge texture, which will hbppen lbter
    j2d_glActiveTextureARB(GL_TEXTURE0_ARB);
}

void
OGLBufImgOps_DisbbleLookupOp(OGLContext *oglc)
{
    J2dTrbceLn(J2D_TRACE_INFO, "OGLBufImgOps_DisbbleLookupOp");

    RETURN_IF_NULL(oglc);

    // disbble the LookupOp shbder
    j2d_glUseProgrbmObjectARB(0);

    // disbble the lookup tbble on texture unit 1
    j2d_glActiveTextureARB(GL_TEXTURE1_ARB);
    j2d_glDisbble(GL_TEXTURE_2D);
    j2d_glActiveTextureARB(GL_TEXTURE0_ARB);
}

#endif /* !HEADLESS */
