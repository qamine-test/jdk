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

#ifndef OGLSurfbceDbtb_h_Included
#define OGLSurfbceDbtb_h_Included

#include "jbvb_bwt_imbge_AffineTrbnsformOp.h"
#include "sun_jbvb2d_opengl_OGLSurfbceDbtb.h"
#include "sun_jbvb2d_pipe_hw_AccelSurfbce.h"

#include "J2D_GL/gl.h"
#include "SurfbceDbtb.h"
#include "Trbce.h"
#include "OGLFuncs.h"

typedef struct _OGLSDOps OGLSDOps;

/**
 * The OGLPixelFormbt structure contbins bll the informbtion OpenGL needs to
 * know when copying from or into b pbrticulbr system memory imbge buffer (vib
 * glDrbwPixels(), glRebdPixels, glTexSubImbge2D(), etc).
 *
 *     GLenum formbt;
 * The pixel formbt pbrbmeter used in glDrbwPixels() bnd other similbr cblls.
 * Indicbtes the component ordering for ebch pixel (e.g. GL_BGRA).
 *
 *     GLenum type;
 * The pixel dbtb type pbrbmeter used in glDrbwPixels() bnd other similbr
 * cblls.  Indicbtes the dbtb type for bn entire pixel or for ebch component
 * in b pixel (e.g. GL_UNSIGNED_BYTE with GL_BGR mebns b pixel consists of
 * 3 unsigned byte components, blue first, then green, then red;
 * GL_UNSIGNED_INT_8_8_8_8_REV with GL_BGRA mebns b pixel consists of 1
 * unsigned integer comprised of four byte components, blphb first, then red,
 * then green, then blue).
 *
 *     jint blignment;
 * The byte blignment pbrbmeter used in glPixelStorei(GL_UNPACK_ALIGNMENT).  A
 * vblue of 4 indicbtes thbt ebch pixel stbrts on b 4-byte bligned region in
 * memory, bnd so on.  This blignment pbrbmeter helps OpenGL speed up pixel
 * trbnsfer operbtions by trbnsferring memory in bligned blocks.
 *
 *     jboolebn hbsAlphb;
 * If true, indicbtes thbt this pixel formbt contbins bn blphb component.
 *
 *     jboolebn isPremult;
 * If true, indicbtes thbt this pixel formbt contbins color components thbt
 * hbve been pre-multiplied by their corresponding blphb component.
 */
typedef struct {
    GLenum   formbt;
    GLenum   type;
    jint     blignment;
    jboolebn hbsAlphb;
    jboolebn isPremult;
} OGLPixelFormbt;

/**
 * The OGLSDOps structure describes b nbtive OpenGL surfbce bnd contbins bll
 * informbtion pertbining to the nbtive surfbce.  Some informbtion bbout
 * the more importbnt/different fields:
 *
 *     void *privOps;
 * Pointer to nbtive-specific (GLX, WGL, etc.) SurfbceDbtb info, such bs the
 * nbtive Drbwbble hbndle bnd GrbphicsConfig dbtb.
 *
 *     jint drbwbbleType;
 * The surfbce type; cbn be bny one of the surfbce type constbnts defined
 * below (OGLSD_WINDOW, OGLSD_TEXTURE, etc).
 *
 *     GLenum bctiveBuffer;
 * Cbn be either GL_FRONT if this is the front buffer surfbce of bn onscreen
 * window or b pbuffer surfbce, or GL_BACK if this is the bbckbuffer surfbce
 * of bn onscreen window.
 *
 *     jboolebn isOpbque;
 * If true, the surfbce should be trebted bs being fully opbque.  If
 * the underlying surfbce (e.g. pbuffer) hbs bn blphb chbnnel bnd isOpbque
 * is true, then we should tbke bppropribte bction (i.e. cbll glColorMbsk()
 * to disbble writes into the blphb chbnnel) to ensure thbt the surfbce
 * rembins fully opbque.
 *
 *     jboolebn needsInit;
 * If true, the surfbce requires some one-time initiblizbtion, which should
 * be performed bfter b context hbs been mbde current to the surfbce for
 * the first time.
 *
 *     jint x/yOffset
 * The offset in pixels of the OpenGL viewport origin from the lower-left
 * corner of the hebvyweight drbwbble.  For exbmple, b top-level frbme on
 * Windows XP hbs lower-left insets of (4,4).  The OpenGL viewport origin
 * would typicblly begin bt the lower-left corner of the client region (inside
 * the frbme decorbtions), but AWT/Swing will tbke the insets into bccount
 * when rendering into thbt window.  So in order to bccount for this, we
 * need to bdjust the OpenGL viewport origin by bn x/yOffset of (-4,-4).  On
 * X11, top-level frbmes typicblly don't hbve this insets issue, so their
 * x/yOffset would be (0,0) (the sbme bpplies to pbuffers).
 *
 *     jint width/height;
 * The cbched surfbce bounds.  For offscreen surfbce types (OGLSD_PBUFFER,
 * OGLSD_TEXTURE, etc.) these vblues must rembin constbnt.  Onscreen window
 * surfbces (OGLSD_WINDOW, OGLSD_FLIP_BACKBUFFER, etc.) mby hbve their
 * bounds chbnged in response to b progrbmmbtic or user-initibted event, so
 * these vblues represent the lbst known dimensions.  To determine the true
 * current bounds of this surfbce, query the nbtive Drbwbble through the
 * privOps field.
 *
 *     GLuint textureID;
 * The texture object hbndle, bs generbted by glGenTextures().  If this vblue
 * is zero, the texture hbs not yet been initiblized.
 *
 *     jint textureWidth/Height;
 * The bctubl bounds of the texture object for this surfbce.  If the
 * GL_ARB_texture_non_power_of_two extension is not present, the dimensions
 * of bn OpenGL texture object must be b power-of-two (e.g. 64x32 or 128x512).
 * The texture imbge thbt we cbre bbout hbs dimensions specified by the width
 * bnd height fields in this OGLSDOps structure.  For exbmple, if the imbge
 * to be stored in the texture hbs dimensions 115x47, the bctubl OpenGL
 * texture we bllocbte will hbve dimensions 128x64 to meet the pow2
 * restriction.  The imbge bounds within the texture cbn be bccessed using
 * flobting point texture coordinbtes in the rbnge [0.0,1.0].
 *
 *     GLenum textureTbrget;
 * The texture tbrget of the texture object for this surfbce.  If this
 * surfbce is not bbcked by b texture, this vblue is set to zero.  Otherwise,
 * this vblue is GL_TEXTURE_RECTANGLE_ARB when the GL_ARB_texture_rectbngle
 * extension is in use; if not, it is set to GL_TEXTURE_2D.
 *
 *     GLint textureFilter;
 * The current filter stbte for this texture object (cbn be either GL_NEAREST
 * or GL_LINEAR).  We cbche this vblue here bnd check it before updbting
 * the filter stbte to bvoid redundbnt cblls to glTexPbrbmeteri() when the
 * filter stbte rembins constbnt (see the OGLSD_UPDATE_TEXTURE_FILTER()
 * mbcro below).
 *
 *     GLuint fbobjectID, depthID;
 * The object hbndles for the frbmebuffer object bnd depth renderbuffer
 * bssocibted with this surfbce.  These fields bre only used when
 * drbwbbleType is OGLSD_FBOBJECT, otherwise they bre zero.
 */
struct _OGLSDOps {
    SurfbceDbtbOps               sdOps;
    void                         *privOps;
    jint                         drbwbbleType;
    GLenum                       bctiveBuffer;
    jboolebn                     isOpbque;
    jboolebn                     needsInit;
    jint                         xOffset;
    jint                         yOffset;
    jint                         width;
    jint                         height;
    GLuint                       textureID;
    jint                         textureWidth;
    jint                         textureHeight;
    GLenum                       textureTbrget;
    GLint                        textureFilter;
    GLuint                       fbobjectID;
    GLuint                       depthID;
};

/**
 * The following convenience mbcros bre used when rendering rectbngles (either
 * b single rectbngle, or b whole series of them).  To render b single
 * rectbngle, simply invoke the GLRECT() mbcro.  To render b whole series of
 * rectbngles, such bs spbns in b complex shbpe, first invoke GLRECT_BEGIN(),
 * then invoke the bppropribte inner loop mbcro (either XYXY or XYWH) for
 * ebch rectbngle, bnd finblly invoke GLRECT_END() to notify OpenGL thbt the
 * vertex list is complete.  Cbre should be tbken to bvoid cblling OpenGL
 * commbnds (besides GLRECT_BODY_*()) inside the BEGIN/END pbir.
 */

#define GLRECT_BEGIN j2d_glBegin(GL_QUADS)

#define GLRECT_BODY_XYXY(x1, y1, x2, y2) \
    do { \
        j2d_glVertex2i(x1, y1); \
        j2d_glVertex2i(x2, y1); \
        j2d_glVertex2i(x2, y2); \
        j2d_glVertex2i(x1, y2); \
    } while (0)

#define GLRECT_BODY_XYWH(x, y, w, h) \
    GLRECT_BODY_XYXY(x, y, (x) + (w), (y) + (h))

#define GLRECT_END j2d_glEnd()

#define GLRECT(x, y, w, h) \
    do { \
        GLRECT_BEGIN; \
        GLRECT_BODY_XYWH(x, y, w, h); \
        GLRECT_END; \
    } while (0)

/**
 * These bre shorthbnd nbmes for the surfbce type constbnts defined in
 * OGLSurfbceDbtb.jbvb.
 */
#define OGLSD_UNDEFINED       sun_jbvb2d_pipe_hw_AccelSurfbce_UNDEFINED
#define OGLSD_WINDOW          sun_jbvb2d_pipe_hw_AccelSurfbce_WINDOW
#define OGLSD_PBUFFER         sun_jbvb2d_pipe_hw_AccelSurfbce_RT_PLAIN
#define OGLSD_TEXTURE         sun_jbvb2d_pipe_hw_AccelSurfbce_TEXTURE
#define OGLSD_FLIP_BACKBUFFER sun_jbvb2d_pipe_hw_AccelSurfbce_FLIP_BACKBUFFER
#define OGLSD_FBOBJECT        sun_jbvb2d_pipe_hw_AccelSurfbce_RT_TEXTURE

/**
 * These bre shorthbnd nbmes for the filtering method constbnts used by
 * imbge trbnsform methods.
 */
#define OGLSD_XFORM_DEFAULT 0
#define OGLSD_XFORM_NEAREST_NEIGHBOR \
    jbvb_bwt_imbge_AffineTrbnsformOp_TYPE_NEAREST_NEIGHBOR
#define OGLSD_XFORM_BILINEAR \
    jbvb_bwt_imbge_AffineTrbnsformOp_TYPE_BILINEAR

/**
 * Helper mbcros thbt updbte the current texture filter stbte only when
 * it needs to be chbnged, which helps reduce overhebd for smbll texturing
 * operbtions.  The filter stbte is set on b per-texture (not per-context)
 * bbsis; for exbmple, it is possible for one texture to be using GL_NEAREST
 * while bnother texture uses GL_LINEAR under the sbme context.
 */
#define OGLSD_INIT_TEXTURE_FILTER(oglSDOps, filter)                          \
    do {                                                                     \
        j2d_glTexPbrbmeteri((oglSDOps)->textureTbrget,                       \
                            GL_TEXTURE_MAG_FILTER, (filter));                \
        j2d_glTexPbrbmeteri((oglSDOps)->textureTbrget,                       \
                            GL_TEXTURE_MIN_FILTER, (filter));                \
        (oglSDOps)->textureFilter = (filter);                                \
    } while (0)

#define OGLSD_UPDATE_TEXTURE_FILTER(oglSDOps, filter)    \
    do {                                                 \
        if ((oglSDOps)->textureFilter != (filter)) {     \
            OGLSD_INIT_TEXTURE_FILTER(oglSDOps, filter); \
        }                                                \
    } while (0)

/**
 * Convenience mbcros for setting the texture wrbp mode for b given tbrget.
 * The texture wrbp mode should be reset to our defbult vblue of
 * GL_CLAMP_TO_EDGE by cblling OGLSD_RESET_TEXTURE_WRAP() when b texture
 * is first crebted.  If bnother mode is needed (e.g. GL_REPEAT in the cbse
 * of TexturePbint bccelerbtion), one cbn cbll the OGLSD_UPDATE_TEXTURE_WRAP()
 * mbcro to ebsily set up the new wrbp mode.  However, it is importbnt to
 * restore the wrbp mode bbck to its defbult vblue (by cblling the
 * OGLSD_RESET_TEXTURE_WRAP() mbcro) when the operbtion is finished.
 */
#define OGLSD_UPDATE_TEXTURE_WRAP(tbrget, wrbp)                   \
    do {                                                          \
        j2d_glTexPbrbmeteri((tbrget), GL_TEXTURE_WRAP_S, (wrbp)); \
        j2d_glTexPbrbmeteri((tbrget), GL_TEXTURE_WRAP_T, (wrbp)); \
    } while (0)

#define OGLSD_RESET_TEXTURE_WRAP(tbrget) \
    OGLSD_UPDATE_TEXTURE_WRAP(tbrget, GL_CLAMP_TO_EDGE)

/**
 * Exported methods.
 */
jint OGLSD_Lock(JNIEnv *env,
                SurfbceDbtbOps *ops, SurfbceDbtbRbsInfo *pRbsInfo,
                jint lockflbgs);
void OGLSD_GetRbsInfo(JNIEnv *env,
                      SurfbceDbtbOps *ops, SurfbceDbtbRbsInfo *pRbsInfo);
void OGLSD_Unlock(JNIEnv *env,
                  SurfbceDbtbOps *ops, SurfbceDbtbRbsInfo *pRbsInfo);
void OGLSD_Dispose(JNIEnv *env, SurfbceDbtbOps *ops);
void OGLSD_Delete(JNIEnv *env, OGLSDOps *oglsdo);
jint OGLSD_NextPowerOfTwo(jint vbl, jint mbx);
jboolebn OGLSD_InitFBObject(GLuint *fbobjectID, GLuint *depthID,
                            GLuint textureID, GLenum textureTbrget,
                            jint textureWidth, jint textureHeight);

#endif /* OGLSurfbceDbtb_h_Included */
