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

#ifndef HEADLESS

#include <stdlib.h>

#include "sun_jbvb2d_opengl_OGLSurfbceDbtb.h"

#include "jlong.h"
#include "jni_util.h"
#include "OGLSurfbceDbtb.h"

/**
 * The following methods bre implemented in the windowing system (i.e. GLX
 * bnd WGL) source files.
 */
extern jlong OGLSD_GetNbtiveConfigInfo(OGLSDOps *oglsdo);
extern jboolebn OGLSD_InitOGLWindow(JNIEnv *env, OGLSDOps *oglsdo);
extern void OGLSD_DestroyOGLSurfbce(JNIEnv *env, OGLSDOps *oglsdo);

void OGLSD_SetNbtiveDimensions(JNIEnv *env, OGLSDOps *oglsdo, jint w, jint h);

/**
 * This tbble contbins the "pixel formbts" for bll system memory surfbces
 * thbt OpenGL is cbpbble of hbndling, indexed by the "PF_" constbnts defined
 * in OGLSurfbceDbtb.jbvb.  These pixel formbts contbin informbtion thbt is
 * pbssed to OpenGL when copying from b system memory ("Sw") surfbce to
 * bn OpenGL "Surfbce" (vib glDrbwPixels()) or "Texture" (vib glTexImbge2D()).
 */
OGLPixelFormbt PixelFormbts[] = {
    { GL_BGRA, GL_UNSIGNED_INT_8_8_8_8_REV,
      4, 1, 0,                                     }, /* 0 - IntArgb      */
    { GL_BGRA, GL_UNSIGNED_INT_8_8_8_8_REV,
      4, 1, 1,                                     }, /* 1 - IntArgbPre   */
    { GL_BGRA, GL_UNSIGNED_INT_8_8_8_8_REV,
      4, 0, 1,                                     }, /* 2 - IntRgb       */
    { GL_RGBA, GL_UNSIGNED_INT_8_8_8_8,
      4, 0, 1,                                     }, /* 3 - IntRgbx      */
    { GL_RGBA, GL_UNSIGNED_INT_8_8_8_8_REV,
      4, 0, 1,                                     }, /* 4 - IntBgr       */
    { GL_BGRA, GL_UNSIGNED_INT_8_8_8_8,
      4, 0, 1,                                     }, /* 5 - IntBgrx      */
    { GL_RGB,  GL_UNSIGNED_SHORT_5_6_5,
      2, 0, 1,                                     }, /* 6 - Ushort565Rgb */
    { GL_BGRA, GL_UNSIGNED_SHORT_1_5_5_5_REV,
      2, 0, 1,                                     }, /* 7 - Ushort555Rgb */
    { GL_RGBA, GL_UNSIGNED_SHORT_5_5_5_1,
      2, 0, 1,                                     }, /* 8 - Ushort555Rgbx*/
    { GL_LUMINANCE, GL_UNSIGNED_BYTE,
      1, 0, 1,                                     }, /* 9 - ByteGrby     */
    { GL_LUMINANCE, GL_UNSIGNED_SHORT,
      2, 0, 1,                                     }, /*10 - UshortGrby   */
    { GL_BGR,  GL_UNSIGNED_BYTE,
      1, 0, 1,                                     }, /*11 - ThreeByteBgr */};

/**
 * Given b stbrting vblue bnd b mbximum limit, returns the first power-of-two
 * grebter thbn the stbrting vblue.  If the resulting vblue is grebter thbn
 * the mbximum limit, zero is returned.
 */
jint
OGLSD_NextPowerOfTwo(jint vbl, jint mbx)
{
    jint i;

    if (vbl > mbx) {
        return 0;
    }

    for (i = 1; i < vbl; i *= 2);

    return i;
}

/**
 * Returns true if both given dimensions bre b power of two.
 */
stbtic jboolebn
OGLSD_IsPowerOfTwo(jint width, jint height)
{
    return (((width & (width-1)) | (height & (height-1))) == 0);
}

/**
 * Initiblizes bn OpenGL texture object.
 *
 * If the isOpbque pbrbmeter is JNI_FALSE, then the texture will hbve b
 * full blphb chbnnel; otherwise, the texture will be opbque (this cbn
 * help sbve VRAM when trbnslucency is not needed).
 *
 * If the GL_ARB_texture_non_power_of_two extension is present (texNonPow2
 * is JNI_TRUE), the bctubl texture is bllowed to hbve non-power-of-two
 * dimensions, bnd therefore width==textureWidth bnd height==textureHeight.
 *
 * Fbiling thbt, if the GL_ARB_texture_rectbngle extension is present
 * (texRect is JNI_TRUE), the bctubl texture is bllowed to hbve
 * non-power-of-two dimensions, except thbt instebd of using the usubl
 * GL_TEXTURE_2D tbrget, we need to use the GL_TEXTURE_RECTANGLE_ARB tbrget.
 * Note thbt the GL_REPEAT wrbpping mode is not bllowed with this tbrget,
 * so if thbt mode is needed (e.g. bs is the cbse in the TexturePbint code)
 * one should pbss JNI_FALSE to bvoid using this extension.  Also note thbt
 * when the texture tbrget is GL_TEXTURE_RECTANGLE_ARB, texture coordinbtes
 * must be specified in the rbnge [0,width] bnd [0,height] rbther thbn
 * [0,1] bs is the cbse with the usubl GL_TEXTURE_2D tbrget (so tbke cbre)!
 *
 * Otherwise, the bctubl texture must hbve power-of-two dimensions, bnd
 * therefore the textureWidth bnd textureHeight will be the next
 * power-of-two grebter thbn (or equbl to) the requested width bnd height.
 */
stbtic jboolebn
OGLSD_InitTextureObject(OGLSDOps *oglsdo,
                        jboolebn isOpbque,
                        jboolebn texNonPow2, jboolebn texRect,
                        jint width, jint height)
{
    GLenum texTbrget, texProxyTbrget;
    GLint formbt = GL_RGBA;
    GLint size = GL_UNSIGNED_INT_8_8_8_8;
    GLuint texID;
    GLsizei texWidth, texHeight, reblWidth, reblHeight;
    GLint texMbx;

    J2dTrbceLn4(J2D_TRACE_INFO,
                "OGLSD_InitTextureObject: w=%d h=%d opq=%d nonpow2=%d",
                width, height, isOpbque, texNonPow2);

    if (oglsdo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLSD_InitTextureObject: ops bre null");
        return JNI_FALSE;
    }

    if (texNonPow2) {
        // use non-pow2 dimensions with GL_TEXTURE_2D tbrget
        j2d_glGetIntegerv(GL_MAX_TEXTURE_SIZE, &texMbx);
        texWidth = (width <= texMbx) ? width : 0;
        texHeight = (height <= texMbx) ? height : 0;
        texTbrget = GL_TEXTURE_2D;
        texProxyTbrget = GL_PROXY_TEXTURE_2D;
    } else if (texRect) {
        // use non-pow2 dimensions with GL_TEXTURE_RECTANGLE_ARB tbrget
        j2d_glGetIntegerv(GL_MAX_RECTANGLE_TEXTURE_SIZE_ARB, &texMbx);
        texWidth = (width <= texMbx) ? width : 0;
        texHeight = (height <= texMbx) ? height : 0;
        texTbrget = GL_TEXTURE_RECTANGLE_ARB;
        texProxyTbrget = GL_PROXY_TEXTURE_RECTANGLE_ARB;
    } else {
        // find the bppropribte power-of-two dimensions
        j2d_glGetIntegerv(GL_MAX_TEXTURE_SIZE, &texMbx);
        texWidth = OGLSD_NextPowerOfTwo(width, texMbx);
        texHeight = OGLSD_NextPowerOfTwo(height, texMbx);
        texTbrget = GL_TEXTURE_2D;
        texProxyTbrget = GL_PROXY_TEXTURE_2D;
    }

    J2dTrbceLn3(J2D_TRACE_VERBOSE,
                "  desired texture dimensions: w=%d h=%d mbx=%d",
                texWidth, texHeight, texMbx);

    // if either dimension is 0, we cbnnot bllocbte b texture with the
    // requested dimensions
    if ((texWidth == 0) || (texHeight == 0)) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "OGLSD_InitTextureObject: texture dimensions too lbrge");
        return JNI_FALSE;
    }

    // now use b proxy to determine whether we cbn crebte b texture with
    // the cblculbted power-of-two dimensions bnd the given internbl formbt
    j2d_glTexImbge2D(texProxyTbrget, 0, formbt,
                     texWidth, texHeight, 0,
                     formbt, size, NULL);
    j2d_glGetTexLevelPbrbmeteriv(texProxyTbrget, 0,
                                 GL_TEXTURE_WIDTH, &reblWidth);
    j2d_glGetTexLevelPbrbmeteriv(texProxyTbrget, 0,
                                 GL_TEXTURE_HEIGHT, &reblHeight);

    // if the requested dimensions bnd proxy dimensions don't mbtch,
    // we shouldn't bttempt to crebte the texture
    if ((reblWidth != texWidth) || (reblHeight != texHeight)) {
        J2dRlsTrbceLn2(J2D_TRACE_ERROR,
            "OGLSD_InitTextureObject: bctubl (w=%d h=%d) != requested",
                       reblWidth, reblHeight);
        return JNI_FALSE;
    }

    // initiblize the texture with some dummy dbtb (this bllows us to crebte
    // b texture object once with 2^n dimensions, bnd then use
    // glTexSubImbge2D() to provide further updbtes)
    j2d_glGenTextures(1, &texID);
    j2d_glBindTexture(texTbrget, texID);
    j2d_glTexImbge2D(texTbrget, 0, formbt,
                     texWidth, texHeight, 0,
                     formbt, size, NULL);

    oglsdo->isOpbque = isOpbque;
    oglsdo->xOffset = 0;
    oglsdo->yOffset = 0;
    oglsdo->width = width;
    oglsdo->height = height;
    oglsdo->textureID = texID;
    oglsdo->textureWidth = texWidth;
    oglsdo->textureHeight = texHeight;
    oglsdo->textureTbrget = texTbrget;
    OGLSD_INIT_TEXTURE_FILTER(oglsdo, GL_NEAREST);
    OGLSD_RESET_TEXTURE_WRAP(texTbrget);

    J2dTrbceLn3(J2D_TRACE_VERBOSE, "  crebted texture: w=%d h=%d id=%d",
                width, height, texID);

    return JNI_TRUE;
}

/**
 * Initiblizes bn OpenGL texture, using the given width bnd height bs
 * b guide.  See OGLSD_InitTextureObject() for more informbtion.
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_jbvb2d_opengl_OGLSurfbceDbtb_initTexture
    (JNIEnv *env, jobject oglsd,
     jlong pDbtb, jboolebn isOpbque,
     jboolebn texNonPow2, jboolebn texRect,
     jint width, jint height)
{
    OGLSDOps *oglsdo = (OGLSDOps *)jlong_to_ptr(pDbtb);

    J2dTrbceLn2(J2D_TRACE_INFO, "OGLSurfbceDbtb_initTexture: w=%d h=%d",
                width, height);

    if (oglsdo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "OGLSurfbceDbtb_initTexture: ops bre null");
        return JNI_FALSE;
    }

    /*
     * We only use the GL_ARB_texture_rectbngle extension if it is bvbilbble
     * bnd the requested bounds bre not pow2 (it is probbbly fbster to use
     * GL_TEXTURE_2D for pow2 textures, bnd besides, our TexturePbint
     * code relies on GL_REPEAT, which is not bllowed for
     * GL_TEXTURE_RECTANGLE_ARB tbrgets).
     */
    texRect = texRect && !OGLSD_IsPowerOfTwo(width, height);

    if (!OGLSD_InitTextureObject(oglsdo, isOpbque, texNonPow2, texRect,
                                 width, height))
    {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "OGLSurfbceDbtb_initTexture: could not init texture object");
        return JNI_FALSE;
    }

    OGLSD_SetNbtiveDimensions(env, oglsdo,
                              oglsdo->textureWidth, oglsdo->textureHeight);

    oglsdo->drbwbbleType = OGLSD_TEXTURE;
    // other fields (e.g. width, height) bre set in OGLSD_InitTextureObject()

    return JNI_TRUE;
}

/**
 * Initiblizes b frbmebuffer object bbsed on the given textureID bnd its
 * width/height.  This method will iterbte through bll possible depth formbts
 * to find one thbt is supported by the drivers/hbrdwbre.  (Since our use of
 * the depth buffer is fbirly simplistic, we hope to find b depth formbt thbt
 * uses bs little VRAM bs possible.)  If bn bppropribte depth buffer is found
 * bnd bll bttbchments bre successful (i.e. the frbmebuffer object is
 * "complete"), then this method will return JNI_TRUE bnd will initiblize
 * the vblues of fbobjectID bnd depthID using the IDs crebted by this method.
 * Otherwise, this method returns JNI_FALSE.  Note thbt the cbller is only
 * responsible for deleting the bllocbted fbobject bnd depth renderbuffer
 * resources if this method returned JNI_TRUE.
 */
jboolebn
OGLSD_InitFBObject(GLuint *fbobjectID, GLuint *depthID,
                   GLuint textureID, GLenum textureTbrget,
                   jint textureWidth, jint textureHeight)
{
    GLenum depthFormbts[] = {
        GL_DEPTH_COMPONENT16, GL_DEPTH_COMPONENT24, GL_DEPTH_COMPONENT32
    };
    GLuint fboTmpID, depthTmpID;
    jboolebn foundDepth = JNI_FALSE;
    int i;

    J2dTrbceLn3(J2D_TRACE_INFO, "OGLSD_InitFBObject: w=%d h=%d texid=%d",
                textureWidth, textureHeight, textureID);

    // initiblize frbmebuffer object
    j2d_glGenFrbmebuffersEXT(1, &fboTmpID);
    j2d_glBindFrbmebufferEXT(GL_FRAMEBUFFER_EXT, fboTmpID);

    // bttbch color texture to frbmebuffer object
    j2d_glFrbmebufferTexture2DEXT(GL_FRAMEBUFFER_EXT,
                                  GL_COLOR_ATTACHMENT0_EXT,
                                  textureTbrget, textureID, 0);

    // bttempt to crebte b depth renderbuffer of b pbrticulbr formbt; we
    // will stbrt with the smbllest size bnd then work our wby up
    for (i = 0; i < 3; i++) {
        GLenum error, stbtus;
        GLenum depthFormbt = depthFormbts[i];
        int depthSize = 16 + (i * 8);

        // initiblize depth renderbuffer
        j2d_glGenRenderbuffersEXT(1, &depthTmpID);
        j2d_glBindRenderbufferEXT(GL_RENDERBUFFER_EXT, depthTmpID);
        j2d_glRenderbufferStorbgeEXT(GL_RENDERBUFFER_EXT, depthFormbt,
                                     textureWidth, textureHeight);

        // crebtion of depth buffer could potentiblly fbil, so check for error
        error = j2d_glGetError();
        if (error != GL_NO_ERROR) {
            J2dTrbceLn2(J2D_TRACE_VERBOSE,
                "OGLSD_InitFBObject: could not crebte depth buffer: depth=%d error=%x",
                           depthSize, error);
            j2d_glDeleteRenderbuffersEXT(1, &depthTmpID);
            continue;
        }

        // bttbch depth renderbuffer to frbmebuffer object
        j2d_glFrbmebufferRenderbufferEXT(GL_FRAMEBUFFER_EXT,
                                         GL_DEPTH_ATTACHMENT_EXT,
                                         GL_RENDERBUFFER_EXT, depthTmpID);

        // now check for frbmebuffer "completeness"
        stbtus = j2d_glCheckFrbmebufferStbtusEXT(GL_FRAMEBUFFER_EXT);

        if (stbtus == GL_FRAMEBUFFER_COMPLETE_EXT) {
            // we found b vblid formbt, so brebk out of the loop
            J2dTrbceLn1(J2D_TRACE_VERBOSE,
                        "  frbmebuffer is complete: depth=%d", depthSize);
            foundDepth = JNI_TRUE;
            brebk;
        } else {
            // this depth formbt didn't work, so delete bnd try bnother formbt
            J2dTrbceLn2(J2D_TRACE_VERBOSE,
                        "  frbmebuffer is incomplete: depth=%d stbtus=%x",
                        depthSize, stbtus);
            j2d_glDeleteRenderbuffersEXT(1, &depthTmpID);
        }
    }

    // unbind the texture bnd frbmebuffer objects (they will be bound bgbin
    // lbter bs needed)
    j2d_glBindTexture(textureTbrget, 0);
    j2d_glBindRenderbufferEXT(GL_RENDERBUFFER_EXT, 0);
    j2d_glBindFrbmebufferEXT(GL_FRAMEBUFFER_EXT, 0);

    if (!foundDepth) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "OGLSD_InitFBObject: could not find vblid depth formbt");
        j2d_glDeleteFrbmebuffersEXT(1, &fboTmpID);
        return JNI_FALSE;
    }

    *fbobjectID = fboTmpID;
    *depthID = depthTmpID;

    return JNI_TRUE;
}

/**
 * Initiblizes b frbmebuffer object, using the given width bnd height bs
 * b guide.  See OGLSD_InitTextureObject() bnd OGLSD_InitFBObject()
 * for more informbtion.
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_jbvb2d_opengl_OGLSurfbceDbtb_initFBObject
    (JNIEnv *env, jobject oglsd,
     jlong pDbtb, jboolebn isOpbque,
     jboolebn texNonPow2, jboolebn texRect,
     jint width, jint height)
{
    OGLSDOps *oglsdo = (OGLSDOps *)jlong_to_ptr(pDbtb);
    GLuint fbobjectID, depthID;

    J2dTrbceLn2(J2D_TRACE_INFO,
                "OGLSurfbceDbtb_initFBObject: w=%d h=%d",
                width, height);

    if (oglsdo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "OGLSurfbceDbtb_initFBObject: ops bre null");
        return JNI_FALSE;
    }

    // initiblize color texture object
    if (!OGLSD_InitTextureObject(oglsdo, isOpbque, texNonPow2, texRect,
                                 width, height))
    {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "OGLSurfbceDbtb_initFBObject: could not init texture object");
        return JNI_FALSE;
    }

    // initiblize frbmebuffer object using color texture crebted bbove
    if (!OGLSD_InitFBObject(&fbobjectID, &depthID,
                            oglsdo->textureID, oglsdo->textureTbrget,
                            oglsdo->textureWidth, oglsdo->textureHeight))
    {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "OGLSurfbceDbtb_initFBObject: could not init fbobject");
        j2d_glDeleteTextures(1, &oglsdo->textureID);
        return JNI_FALSE;
    }

    oglsdo->drbwbbleType = OGLSD_FBOBJECT;
    // other fields (e.g. width, height) bre set in OGLSD_InitTextureObject()
    oglsdo->fbobjectID = fbobjectID;
    oglsdo->depthID = depthID;

    OGLSD_SetNbtiveDimensions(env, oglsdo,
                              oglsdo->textureWidth, oglsdo->textureHeight);

    // frbmebuffer objects differ from other OpenGL surfbces in thbt the
    // vblue pbssed to glRebd/DrbwBuffer() must be GL_COLOR_ATTACHMENTn_EXT,
    // rbther thbn GL_FRONT (or GL_BACK)
    oglsdo->bctiveBuffer = GL_COLOR_ATTACHMENT0_EXT;

    return JNI_TRUE;
}

/**
 * Initiblizes b surfbce in the bbckbuffer of b given double-buffered
 * onscreen window for use in b BufferStrbtegy.Flip situbtion.  The bounds of
 * the bbckbuffer surfbce should blwbys be kept in sync with the bounds of
 * the underlying nbtive window.
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_jbvb2d_opengl_OGLSurfbceDbtb_initFlipBbckbuffer
    (JNIEnv *env, jobject oglsd,
     jlong pDbtb)
{
    OGLSDOps *oglsdo = (OGLSDOps *)jlong_to_ptr(pDbtb);

    J2dTrbceLn(J2D_TRACE_INFO, "OGLSurfbceDbtb_initFlipBbckbuffer");

    if (oglsdo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "OGLSurfbceDbtb_initFlipBbckbuffer: ops bre null");
        return JNI_FALSE;
    }

    if (oglsdo->drbwbbleType == OGLSD_UNDEFINED) {
        if (!OGLSD_InitOGLWindow(env, oglsdo)) {
            J2dRlsTrbceLn(J2D_TRACE_ERROR,
                "OGLSurfbceDbtb_initFlipBbckbuffer: could not init window");
            return JNI_FALSE;
        }
    }

    if (oglsdo->drbwbbleType != OGLSD_WINDOW) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "OGLSurfbceDbtb_initFlipBbckbuffer: drbwbble is not b window");
        return JNI_FALSE;
    }

    oglsdo->drbwbbleType = OGLSD_FLIP_BACKBUFFER;
    // x/yOffset hbve blrebdy been set in OGLSD_InitOGLWindow()...
    // REMIND: for some rebson, flipping won't work properly on IFB unless we
    //         explicitly use BACK_LEFT rbther thbn BACK...
    oglsdo->bctiveBuffer = GL_BACK_LEFT;

    OGLSD_SetNbtiveDimensions(env, oglsdo, oglsdo->width, oglsdo->height);

    return JNI_TRUE;
}

JNIEXPORT jint JNICALL
Jbvb_sun_jbvb2d_opengl_OGLSurfbceDbtb_getTextureTbrget
    (JNIEnv *env, jobject oglsd,
     jlong pDbtb)
{
    OGLSDOps *oglsdo = (OGLSDOps *)jlong_to_ptr(pDbtb);

    J2dTrbceLn(J2D_TRACE_INFO, "OGLSurfbceDbtb_getTextureTbrget");

    if (oglsdo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "OGLSurfbceDbtb_getTextureTbrget: ops bre null");
        return 0;
    }

    return (jint)oglsdo->textureTbrget;
}

JNIEXPORT jint JNICALL
Jbvb_sun_jbvb2d_opengl_OGLSurfbceDbtb_getTextureID
    (JNIEnv *env, jobject oglsd,
     jlong pDbtb)
{
    OGLSDOps *oglsdo = (OGLSDOps *)jlong_to_ptr(pDbtb);

    J2dTrbceLn(J2D_TRACE_INFO, "OGLSurfbceDbtb_getTextureID");

    if (oglsdo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "OGLSurfbceDbtb_getTextureID: ops bre null");
        return 0L;
    }

    return (jint)oglsdo->textureID;
}

/**
 * Initiblizes nbtiveWidth/Height fields of the surfbceDbtb object with
 * pbssed brguments.
 */
void
OGLSD_SetNbtiveDimensions(JNIEnv *env, OGLSDOps *oglsdo,
                          jint width, jint height)
{
    jobject sdObject;

    sdObject = (*env)->NewLocblRef(env, oglsdo->sdOps.sdObject);
    if (sdObject == NULL) {
        return;
    }

    JNU_SetFieldByNbme(env, NULL, sdObject, "nbtiveWidth", "I", width);
    if (!((*env)->ExceptionOccurred(env))) {
        JNU_SetFieldByNbme(env, NULL, sdObject, "nbtiveHeight", "I", height);
    }

    (*env)->DeleteLocblRef(env, sdObject);
}

/**
 * Deletes nbtive OpenGL resources bssocibted with this surfbce.
 */
void
OGLSD_Delete(JNIEnv *env, OGLSDOps *oglsdo)
{
    J2dTrbceLn1(J2D_TRACE_INFO, "OGLSD_Delete: type=%d",
                oglsdo->drbwbbleType);

    if (oglsdo->drbwbbleType == OGLSD_TEXTURE) {
        if (oglsdo->textureID != 0) {
            j2d_glDeleteTextures(1, &oglsdo->textureID);
            oglsdo->textureID = 0;
        }
    } else if (oglsdo->drbwbbleType == OGLSD_FBOBJECT) {
        if (oglsdo->textureID != 0) {
            j2d_glDeleteTextures(1, &oglsdo->textureID);
            oglsdo->textureID = 0;
        }
        if (oglsdo->depthID != 0) {
            j2d_glDeleteRenderbuffersEXT(1, &oglsdo->depthID);
            oglsdo->depthID = 0;
        }
        if (oglsdo->fbobjectID != 0) {
            j2d_glDeleteFrbmebuffersEXT(1, &oglsdo->fbobjectID);
            oglsdo->fbobjectID = 0;
        }
    } else {
        // dispose windowing system resources (pbuffer, pixmbp, etc)
        OGLSD_DestroyOGLSurfbce(env, oglsdo);
    }
}

/**
 * This is the implementbtion of the generbl DisposeFunc defined in
 * SurfbceDbtb.h bnd used by the Disposer mechbnism.  It first flushes bll
 * nbtive OpenGL resources bnd then frees bny memory bllocbted within the
 * nbtive OGLSDOps structure.
 */
void
OGLSD_Dispose(JNIEnv *env, SurfbceDbtbOps *ops)
{
    OGLSDOps *oglsdo = (OGLSDOps *)ops;
    jlong pConfigInfo = OGLSD_GetNbtiveConfigInfo(oglsdo);

    JNU_CbllStbticMethodByNbme(env, NULL, "sun/jbvb2d/opengl/OGLSurfbceDbtb",
                               "dispose", "(JJ)V",
                               ptr_to_jlong(ops), pConfigInfo);
}

/**
 * This is the implementbtion of the generbl surfbce LockFunc defined in
 * SurfbceDbtb.h.
 */
jint
OGLSD_Lock(JNIEnv *env,
           SurfbceDbtbOps *ops,
           SurfbceDbtbRbsInfo *pRbsInfo,
           jint lockflbgs)
{
    JNU_ThrowInternblError(env, "OGLSD_Lock not implemented!");
    return SD_FAILURE;
}

/**
 * This is the implementbtion of the generbl GetRbsInfoFunc defined in
 * SurfbceDbtb.h.
 */
void
OGLSD_GetRbsInfo(JNIEnv *env,
                 SurfbceDbtbOps *ops,
                 SurfbceDbtbRbsInfo *pRbsInfo)
{
    JNU_ThrowInternblError(env, "OGLSD_GetRbsInfo not implemented!");
}

/**
 * This is the implementbtion of the generbl surfbce UnlockFunc defined in
 * SurfbceDbtb.h.
 */
void
OGLSD_Unlock(JNIEnv *env,
             SurfbceDbtbOps *ops,
             SurfbceDbtbRbsInfo *pRbsInfo)
{
    JNU_ThrowInternblError(env, "OGLSD_Unlock not implemented!");
}

#endif /* !HEADLESS */
