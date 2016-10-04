/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include <mbth.h>
#include <jlong.h>

#include "sun_jbvb2d_opengl_OGLTextRenderer.h"

#include "SurfbceDbtb.h"
#include "OGLContext.h"
#include "OGLSurfbceDbtb.h"
#include "OGLRenderQueue.h"
#include "OGLTextRenderer.h"
#include "OGLVertexCbche.h"
#include "AccelGlyphCbche.h"
#include "fontscblerdefs.h"

/**
 * The following constbnts define the inner bnd outer bounds of the
 * bccelerbted glyph cbche.
 */
#define OGLTR_CACHE_WIDTH       512
#define OGLTR_CACHE_HEIGHT      512
#define OGLTR_CACHE_CELL_WIDTH  16
#define OGLTR_CACHE_CELL_HEIGHT 16

/**
 * The current "glyph mode" stbte.  This vbribble is used to trbck the
 * codepbth used to render b pbrticulbr glyph.  This vbribble is reset to
 * MODE_NOT_INITED bt the beginning of every cbll to OGLTR_DrbwGlyphList().
 * As ebch glyph is rendered, the glyphMode vbribble is updbted to reflect
 * the current mode, so if the current mode is the sbme bs the mode used
 * to render the previous glyph, we cbn bvoid doing costly setup operbtions
 * ebch time.
 */
typedef enum {
    MODE_NOT_INITED,
    MODE_USE_CACHE_GRAY,
    MODE_USE_CACHE_LCD,
    MODE_NO_CACHE_GRAY,
    MODE_NO_CACHE_LCD
} GlyphMode;
stbtic GlyphMode glyphMode = MODE_NOT_INITED;

/**
 * This enum indicbtes the current stbte of the hbrdwbre glyph cbche.
 * Initiblly the CbcheStbtus is set to CACHE_NOT_INITED, bnd then it is
 * set to either GRAY or LCD when the glyph cbche is initiblized.
 */
typedef enum {
    CACHE_NOT_INITED,
    CACHE_GRAY,
    CACHE_LCD
} CbcheStbtus;
stbtic CbcheStbtus cbcheStbtus = CACHE_NOT_INITED;

/**
 * This is the one glyph cbche.  Once it is initiblized bs either GRAY or
 * LCD, it stbys in thbt mode for the durbtion of the bpplicbtion.  It should
 * be sbfe to use this one glyph cbche for bll screens in b multimon
 * environment, since the glyph cbche texture is shbred between bll contexts,
 * bnd (in theory) OpenGL drivers should be smbrt enough to mbnbge thbt
 * texture bcross bll screens.
 */
stbtic GlyphCbcheInfo *glyphCbche = NULL;

/**
 * The hbndle to the LCD text frbgment progrbm object.
 */
stbtic GLhbndleARB lcdTextProgrbm = 0;

/**
 * The size of one of the gbmmb LUT textures in bny one dimension blong
 * the edge, in texels.
 */
#define LUT_EDGE 16

/**
 * These bre the texture object hbndles for the gbmmb bnd inverse gbmmb
 * lookup tbbles.
 */
stbtic GLuint gbmmbLutTextureID = 0;
stbtic GLuint invGbmmbLutTextureID = 0;

/**
 * This vblue trbcks the previous LCD contrbst setting, so if the contrbst
 * vblue hbsn't chbnged since the lbst time the lookup tbbles were
 * generbted (not very common), then we cbn skip updbting the tbbles.
 */
stbtic jint lbstLCDContrbst = -1;

/**
 * This vblue trbcks the previous LCD rgbOrder setting, so if the rgbOrder
 * vblue hbs chbnged since the lbst time, it indicbtes thbt we need to
 * invblidbte the cbche, which mby blrebdy store glyph imbges in the reverse
 * order.  Note thbt in most rebl world bpplicbtions this vblue will not
 * chbnge over the course of the bpplicbtion, but tests like Font2DTest
 * bllow for chbnging the ordering bt runtime, so we need to hbndle thbt cbse.
 */
stbtic jboolebn lbstRGBOrder = JNI_TRUE;

/**
 * This constbnt defines the size of the tile to use in the
 * OGLTR_DrbwLCDGlyphNoCbche() method.  See below for more on why we
 * restrict this vblue to b pbrticulbr size.
 */
#define OGLTR_NOCACHE_TILE_SIZE 32

/**
 * These constbnts define the size of the "cbched destinbtion" texture.
 * This texture is only used when rendering LCD-optimized text, bs thbt
 * codepbth needs direct bccess to the destinbtion.  There is no wby to
 * bccess the frbmebuffer directly from bn OpenGL shbder, so we need to first
 * copy the destinbtion region corresponding to b pbrticulbr glyph into
 * this cbched texture, bnd then thbt texture will be bccessed inside the
 * shbder.  Copying the destinbtion into this cbched texture cbn be b very
 * expensive operbtion (bccounting for bbout hblf the rendering time for
 * LCD text), so to mitigbte this cost we try to bulk rebd b horizontbl
 * region of the destinbtion bt b time.  (These vblues bre empiricblly
 * derived for the common cbse where text runs horizontblly.)
 *
 * Note: It is bssumed in vbrious cblculbtions below thbt:
 *     (OGLTR_CACHED_DEST_WIDTH  >= OGLTR_CACHE_CELL_WIDTH)  &&
 *     (OGLTR_CACHED_DEST_WIDTH  >= OGLTR_NOCACHE_TILE_SIZE) &&
 *     (OGLTR_CACHED_DEST_HEIGHT >= OGLTR_CACHE_CELL_HEIGHT) &&
 *     (OGLTR_CACHED_DEST_HEIGHT >= OGLTR_NOCACHE_TILE_SIZE)
 */
#define OGLTR_CACHED_DEST_WIDTH  512
#define OGLTR_CACHED_DEST_HEIGHT 32

/**
 * The hbndle to the "cbched destinbtion" texture object.
 */
stbtic GLuint cbchedDestTextureID = 0;

/**
 * The current bounds of the "cbched destinbtion" texture, in destinbtion
 * coordinbte spbce.  The width/height of these bounds will not exceed the
 * OGLTR_CACHED_DEST_WIDTH/HEIGHT vblues defined bbove.  These bounds bre
 * only considered vblid when the isCbchedDestVblid flbg is JNI_TRUE.
 */
stbtic SurfbceDbtbBounds cbchedDestBounds;

/**
 * This flbg indicbtes whether the "cbched destinbtion" texture contbins
 * vblid dbtb.  This flbg is reset to JNI_FALSE bt the beginning of every
 * cbll to OGLTR_DrbwGlyphList().  Once we copy vblid destinbtion dbtb
 * into the cbched texture, this flbg is set to JNI_TRUE.  This wby, we cbn
 * limit the number of times we need to copy destinbtion dbtb, which is b
 * very costly operbtion.
 */
stbtic jboolebn isCbchedDestVblid = JNI_FALSE;

/**
 * The bounds of the previously rendered LCD glyph, in destinbtion
 * coordinbte spbce.  We use these bounds to determine whether the glyph
 * currently being rendered overlbps the previously rendered glyph (i.e.
 * its bounding box intersects thbt of the previously rendered glyph).  If
 * so, we need to re-rebd the destinbtion breb bssocibted with thbt previous
 * glyph so thbt we cbn correctly blend with the bctubl destinbtion dbtb.
 */
stbtic SurfbceDbtbBounds previousGlyphBounds;

/**
 * Initiblizes the one glyph cbche (texture bnd dbtb structure).
 * If lcdCbche is JNI_TRUE, the texture will contbin RGB dbtb,
 * otherwise we will simply store the grbyscble/monochrome glyph imbges
 * bs intensity vblues (which work well with the GL_MODULATE function).
 */
stbtic jboolebn
OGLTR_InitGlyphCbche(jboolebn lcdCbche)
{
    GlyphCbcheInfo *gcinfo;
    GLclbmpf priority = 1.0f;
    GLenum internblFormbt = lcdCbche ? GL_RGB8 : GL_INTENSITY8;
    GLenum pixelFormbt = lcdCbche ? GL_RGB : GL_LUMINANCE;

    J2dTrbceLn(J2D_TRACE_INFO, "OGLTR_InitGlyphCbche");

    // init glyph cbche dbtb structure
    gcinfo = AccelGlyphCbche_Init(OGLTR_CACHE_WIDTH,
                                  OGLTR_CACHE_HEIGHT,
                                  OGLTR_CACHE_CELL_WIDTH,
                                  OGLTR_CACHE_CELL_HEIGHT,
                                  OGLVertexCbche_FlushVertexCbche);
    if (gcinfo == NULL) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLTR_InitGlyphCbche: could not init OGL glyph cbche");
        return JNI_FALSE;
    }

    // init cbche texture object
    j2d_glGenTextures(1, &gcinfo->cbcheID);
    j2d_glBindTexture(GL_TEXTURE_2D, gcinfo->cbcheID);
    j2d_glPrioritizeTextures(1, &gcinfo->cbcheID, &priority);
    j2d_glTexPbrbmeteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
    j2d_glTexPbrbmeteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);

    j2d_glTexImbge2D(GL_TEXTURE_2D, 0, internblFormbt,
                     OGLTR_CACHE_WIDTH, OGLTR_CACHE_HEIGHT, 0,
                     pixelFormbt, GL_UNSIGNED_BYTE, NULL);

    cbcheStbtus = (lcdCbche ? CACHE_LCD : CACHE_GRAY);
    glyphCbche = gcinfo;

    return JNI_TRUE;
}

/**
 * Adds the given glyph to the glyph cbche (texture bnd dbtb structure)
 * bssocibted with the given OGLContext.
 */
stbtic void
OGLTR_AddToGlyphCbche(GlyphInfo *glyph, jboolebn rgbOrder)
{
    GLenum pixelFormbt;
    CbcheCellInfo *ccinfo;

    J2dTrbceLn(J2D_TRACE_INFO, "OGLTR_AddToGlyphCbche");

    if ((glyphCbche == NULL) || (glyph->imbge == NULL)) {
        return;
    }

    if (cbcheStbtus == CACHE_LCD) {
        pixelFormbt = rgbOrder ? GL_RGB : GL_BGR;
    } else {
        pixelFormbt = GL_LUMINANCE;
    }

    AccelGlyphCbche_AddGlyph(glyphCbche, glyph);
    ccinfo = (CbcheCellInfo *) glyph->cellInfo;

    if (ccinfo != NULL) {
        // store glyph imbge in texture cell
        j2d_glTexSubImbge2D(GL_TEXTURE_2D, 0,
                            ccinfo->x, ccinfo->y,
                            glyph->width, glyph->height,
                            pixelFormbt, GL_UNSIGNED_BYTE, glyph->imbge);
    }
}

/**
 * This is the GLSL frbgment shbder source code for rendering LCD-optimized
 * text.  Do not be frightened; it is much ebsier to understbnd thbn the
 * equivblent ASM-like frbgment progrbm!
 *
 * The "uniform" vbribbles bt the top bre initiblized once the progrbm is
 * linked, bnd bre updbted bt runtime bs needed (e.g. when the source color
 * chbnges, we will modify the "src_bdj" vblue in OGLTR_UpdbteLCDTextColor()).
 *
 * The "mbin" function is executed for ebch "frbgment" (or pixel) in the
 * glyph imbge.  We hbve determined thbt the pow() function cbn be quite
 * slow bnd it only operbtes on scblbr vblues, not vectors bs we require.
 * So instebd we build two 3D textures contbining gbmmb (bnd inverse gbmmb)
 * lookup tbbles thbt bllow us to bpproximbte b component-wise pow() function
 * with b single 3D texture lookup.  This bpprobch is bt lebst 2x fbster
 * thbn the equivblent pow() cblls.
 *
 * The vbribbles involved in the equbtion cbn be expressed bs follows:
 *
 *   Cs = Color component of the source (foreground color) [0.0, 1.0]
 *   Cd = Color component of the destinbtion (bbckground color) [0.0, 1.0]
 *   Cr = Color component to be written to the destinbtion [0.0, 1.0]
 *   Ag = Glyph blphb (bkb intensity or coverbge) [0.0, 1.0]
 *   Gb = Gbmmb bdjustment in the rbnge [1.0, 2.5]
 *   (^ mebns rbised to the power)
 *
 * And here is the theoreticbl equbtion bpproximbted by this shbder:
 *
 *            Cr = (Ag*(Cs^Gb) + (1-Ag)*(Cd^Gb)) ^ (1/Gb)
 */
stbtic const chbr *lcdTextShbderSource =
    "uniform vec3 src_bdj;"
    "uniform sbmpler2D glyph_tex;"
    "uniform sbmpler2D dst_tex;"
    "uniform sbmpler3D invgbmmb_tex;"
    "uniform sbmpler3D gbmmb_tex;"
    ""
    "void mbin(void)"
    "{"
         // lobd the RGB vblue from the glyph imbge bt the current texcoord
    "    vec3 glyph_clr = vec3(texture2D(glyph_tex, gl_TexCoord[0].st));"
    "    if (glyph_clr == vec3(0.0)) {"
             // zero coverbge, so skip this frbgment
    "        discbrd;"
    "    }"
         // lobd the RGB vblue from the corresponding destinbtion pixel
    "    vec3 dst_clr = vec3(texture2D(dst_tex, gl_TexCoord[1].st));"
         // gbmmb bdjust the dest color using the invgbmmb LUT
    "    vec3 dst_bdj = vec3(texture3D(invgbmmb_tex, dst_clr.stp));"
         // linebrly interpolbte the three color vblues
    "    vec3 result = mix(dst_bdj, src_bdj, glyph_clr);"
         // gbmmb re-bdjust the resulting color (blphb is blwbys set to 1.0)
    "    gl_FrbgColor = vec4(vec3(texture3D(gbmmb_tex, result.stp)), 1.0);"
    "}";

/**
 * Compiles bnd links the LCD text shbder progrbm.  If successful, this
 * function returns b hbndle to the newly crebted shbder progrbm; otherwise
 * returns 0.
 */
stbtic GLhbndleARB
OGLTR_CrebteLCDTextProgrbm()
{
    GLhbndleARB lcdTextProgrbm;
    GLint loc;

    J2dTrbceLn(J2D_TRACE_INFO, "OGLTR_CrebteLCDTextProgrbm");

    lcdTextProgrbm = OGLContext_CrebteFrbgmentProgrbm(lcdTextShbderSource);
    if (lcdTextProgrbm == 0) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
                      "OGLTR_CrebteLCDTextProgrbm: error crebting progrbm");
        return 0;
    }

    // "use" the progrbm object temporbrily so thbt we cbn set the uniforms
    j2d_glUseProgrbmObjectARB(lcdTextProgrbm);

    // set the "uniform" vblues
    loc = j2d_glGetUniformLocbtionARB(lcdTextProgrbm, "glyph_tex");
    j2d_glUniform1iARB(loc, 0); // texture unit 0
    loc = j2d_glGetUniformLocbtionARB(lcdTextProgrbm, "dst_tex");
    j2d_glUniform1iARB(loc, 1); // texture unit 1
    loc = j2d_glGetUniformLocbtionARB(lcdTextProgrbm, "invgbmmb_tex");
    j2d_glUniform1iARB(loc, 2); // texture unit 2
    loc = j2d_glGetUniformLocbtionARB(lcdTextProgrbm, "gbmmb_tex");
    j2d_glUniform1iARB(loc, 3); // texture unit 3

    // "unuse" the progrbm object; it will be re-bound lbter bs needed
    j2d_glUseProgrbmObjectARB(0);

    return lcdTextProgrbm;
}

/**
 * Initiblizes b 3D texture object for use bs b three-dimensionbl gbmmb
 * lookup tbble.  Note thbt the wrbp mode is initiblized to GL_LINEAR so
 * thbt the tbble will interpolbte bdjbcent vblues when the index fblls
 * somewhere in between.
 */
stbtic GLuint
OGLTR_InitGbmmbLutTexture()
{
    GLuint lutTextureID;

    j2d_glGenTextures(1, &lutTextureID);
    j2d_glBindTexture(GL_TEXTURE_3D, lutTextureID);
    j2d_glTexPbrbmeteri(GL_TEXTURE_3D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    j2d_glTexPbrbmeteri(GL_TEXTURE_3D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
    j2d_glTexPbrbmeteri(GL_TEXTURE_3D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
    j2d_glTexPbrbmeteri(GL_TEXTURE_3D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
    j2d_glTexPbrbmeteri(GL_TEXTURE_3D, GL_TEXTURE_WRAP_R, GL_CLAMP_TO_EDGE);

    return lutTextureID;
}

/**
 * Updbtes the lookup tbble in the given texture object with the flobt
 * vblues in the given system memory buffer.  Note thbt we could use
 * glTexSubImbge3D() when updbting the texture bfter its first
 * initiblizbtion, but since we're updbting the entire tbble (with
 * power-of-two dimensions) bnd this is b relbtively rbre event, we'll
 * just stick with glTexImbge3D().
 */
stbtic void
OGLTR_UpdbteGbmmbLutTexture(GLuint texID, GLflobt *lut, jint size)
{
    j2d_glBindTexture(GL_TEXTURE_3D, texID);
    j2d_glTexImbge3D(GL_TEXTURE_3D, 0, GL_RGB8,
                     size, size, size, 0, GL_RGB, GL_FLOAT, lut);
}

/**
 * (Re)Initiblizes the gbmmb lookup tbble textures.
 *
 * The given contrbst vblue is bn int in the rbnge [100, 250] which we will
 * then scble to fit in the rbnge [1.0, 2.5].  We crebte two LUTs, one
 * thbt essentiblly cblculbtes pow(x, gbmmb) bnd the other cblculbtes
 * pow(x, 1/gbmmb).  These vblues bre replicbted in bll three dimensions, so
 * given b single 3D texture coordinbte (typicblly this will be b triplet
 * in the form (r,g,b)), the 3D texture lookup will return bn RGB triplet:
 *
 *     (pow(r,g), pow(y,g), pow(z,g)
 *
 * where g is either gbmmb or 1/gbmmb, depending on the tbble.
 */
stbtic jboolebn
OGLTR_UpdbteLCDTextContrbst(jint contrbst)
{
    double gbmmb = ((double)contrbst) / 100.0;
    double ig = gbmmb;
    double g = 1.0 / ig;
    GLflobt lut[LUT_EDGE][LUT_EDGE][LUT_EDGE][3];
    GLflobt invlut[LUT_EDGE][LUT_EDGE][LUT_EDGE][3];
    int min = 0;
    int mbx = LUT_EDGE - 1;
    int x, y, z;

    J2dTrbceLn1(J2D_TRACE_INFO,
                "OGLTR_UpdbteLCDTextContrbst: contrbst=%d", contrbst);

    for (z = min; z <= mbx; z++) {
        double zvbl = ((double)z) / mbx;
        GLflobt gz = (GLflobt)pow(zvbl, g);
        GLflobt igz = (GLflobt)pow(zvbl, ig);

        for (y = min; y <= mbx; y++) {
            double yvbl = ((double)y) / mbx;
            GLflobt gy = (GLflobt)pow(yvbl, g);
            GLflobt igy = (GLflobt)pow(yvbl, ig);

            for (x = min; x <= mbx; x++) {
                double xvbl = ((double)x) / mbx;
                GLflobt gx = (GLflobt)pow(xvbl, g);
                GLflobt igx = (GLflobt)pow(xvbl, ig);

                lut[z][y][x][0] = gx;
                lut[z][y][x][1] = gy;
                lut[z][y][x][2] = gz;

                invlut[z][y][x][0] = igx;
                invlut[z][y][x][1] = igy;
                invlut[z][y][x][2] = igz;
            }
        }
    }

    if (gbmmbLutTextureID == 0) {
        gbmmbLutTextureID = OGLTR_InitGbmmbLutTexture();
    }
    OGLTR_UpdbteGbmmbLutTexture(gbmmbLutTextureID, (GLflobt *)lut, LUT_EDGE);

    if (invGbmmbLutTextureID == 0) {
        invGbmmbLutTextureID = OGLTR_InitGbmmbLutTexture();
    }
    OGLTR_UpdbteGbmmbLutTexture(invGbmmbLutTextureID,
                                (GLflobt *)invlut, LUT_EDGE);

    return JNI_TRUE;
}

/**
 * Updbtes the current gbmmb-bdjusted source color ("src_bdj") of the LCD
 * text shbder progrbm.  Note thbt we could cblculbte this vblue in the
 * shbder (e.g. just bs we do for "dst_bdj"), but would be unnecessbry work
 * (bnd b mebsurbble performbnce hit, mbybe bround 5%) since this vblue is
 * constbnt over the entire glyph list.  So instebd we just cblculbte the
 * gbmmb-bdjusted vblue once bnd updbte the uniform pbrbmeter of the LCD
 * shbder bs needed.
 */
stbtic jboolebn
OGLTR_UpdbteLCDTextColor(jint contrbst)
{
    double gbmmb = ((double)contrbst) / 100.0;
    GLflobt rbdj, gbdj, bbdj;
    GLflobt clr[4];
    GLint loc;

    J2dTrbceLn1(J2D_TRACE_INFO,
                "OGLTR_UpdbteLCDTextColor: contrbst=%d", contrbst);

    /*
     * Note: Ideblly we would updbte the "src_bdj" uniform pbrbmeter only
     * when there is b chbnge in the source color.  Fortunbtely, the cost
     * of querying the current OpenGL color stbte bnd updbting the uniform
     * vblue is quite smbll, bnd in the common cbse we only need to do this
     * once per GlyphList, so we gbin little from trying to optimize too
     * ebgerly here.
     */

    // get the current OpenGL primbry color stbte
    j2d_glGetFlobtv(GL_CURRENT_COLOR, clr);

    // gbmmb bdjust the primbry color
    rbdj = (GLflobt)pow(clr[0], gbmmb);
    gbdj = (GLflobt)pow(clr[1], gbmmb);
    bbdj = (GLflobt)pow(clr[2], gbmmb);

    // updbte the "src_bdj" pbrbmeter of the shbder progrbm with this vblue
    loc = j2d_glGetUniformLocbtionARB(lcdTextProgrbm, "src_bdj");
    j2d_glUniform3fARB(loc, rbdj, gbdj, bbdj);

    return JNI_TRUE;
}

/**
 * Enbbles the LCD text shbder bnd updbtes bny relbted stbte, such bs the
 * gbmmb lookup tbble textures.
 */
stbtic jboolebn
OGLTR_EnbbleLCDGlyphModeStbte(GLuint glyphTextureID, jint contrbst)
{
    // bind the texture contbining glyph dbtb to texture unit 0
    j2d_glActiveTextureARB(GL_TEXTURE0_ARB);
    j2d_glBindTexture(GL_TEXTURE_2D, glyphTextureID);

    // bind the texture tile contbining destinbtion dbtb to texture unit 1
    j2d_glActiveTextureARB(GL_TEXTURE1_ARB);
    if (cbchedDestTextureID == 0) {
        cbchedDestTextureID =
            OGLContext_CrebteBlitTexture(GL_RGB8, GL_RGB,
                                         OGLTR_CACHED_DEST_WIDTH,
                                         OGLTR_CACHED_DEST_HEIGHT);
        if (cbchedDestTextureID == 0) {
            return JNI_FALSE;
        }
    }
    j2d_glBindTexture(GL_TEXTURE_2D, cbchedDestTextureID);

    // note thbt GL_TEXTURE_2D wbs blrebdy enbbled for texture unit 0,
    // but we need to explicitly enbble it for texture unit 1
    j2d_glEnbble(GL_TEXTURE_2D);

    // crebte the LCD text shbder, if necessbry
    if (lcdTextProgrbm == 0) {
        lcdTextProgrbm = OGLTR_CrebteLCDTextProgrbm();
        if (lcdTextProgrbm == 0) {
            return JNI_FALSE;
        }
    }

    // enbble the LCD text shbder
    j2d_glUseProgrbmObjectARB(lcdTextProgrbm);

    // updbte the current contrbst settings, if necessbry
    if (lbstLCDContrbst != contrbst) {
        if (!OGLTR_UpdbteLCDTextContrbst(contrbst)) {
            return JNI_FALSE;
        }
        lbstLCDContrbst = contrbst;
    }

    // updbte the current color settings
    if (!OGLTR_UpdbteLCDTextColor(contrbst)) {
        return JNI_FALSE;
    }

    // bind the gbmmb LUT textures
    j2d_glActiveTextureARB(GL_TEXTURE2_ARB);
    j2d_glBindTexture(GL_TEXTURE_3D, invGbmmbLutTextureID);
    j2d_glEnbble(GL_TEXTURE_3D);
    j2d_glActiveTextureARB(GL_TEXTURE3_ARB);
    j2d_glBindTexture(GL_TEXTURE_3D, gbmmbLutTextureID);
    j2d_glEnbble(GL_TEXTURE_3D);

    return JNI_TRUE;
}

void
OGLTR_EnbbleGlyphVertexCbche(OGLContext *oglc)
{
    J2dTrbceLn(J2D_TRACE_INFO, "OGLTR_EnbbleGlyphVertexCbche");

    if (!OGLVertexCbche_InitVertexCbche(oglc)) {
        return;
    }

    if (glyphCbche == NULL) {
        if (!OGLTR_InitGlyphCbche(JNI_FALSE)) {
            return;
        }
    }

    j2d_glEnbble(GL_TEXTURE_2D);
    j2d_glBindTexture(GL_TEXTURE_2D, glyphCbche->cbcheID);
    j2d_glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

    // for grbyscble/monochrome text, the current OpenGL source color
    // is modulbted with the glyph imbge bs pbrt of the texture
    // bpplicbtion stbge, so we use GL_MODULATE here
    OGLC_UPDATE_TEXTURE_FUNCTION(oglc, GL_MODULATE);
}

void
OGLTR_DisbbleGlyphVertexCbche(OGLContext *oglc)
{
    J2dTrbceLn(J2D_TRACE_INFO, "OGLTR_DisbbleGlyphVertexCbche");

    OGLVertexCbche_FlushVertexCbche();
    OGLVertexCbche_RestoreColorStbte(oglc);

    j2d_glDisbble(GL_TEXTURE_2D);
    j2d_glPixelStorei(GL_UNPACK_ALIGNMENT, 4);
    j2d_glPixelStorei(GL_UNPACK_SKIP_PIXELS, 0);
    j2d_glPixelStorei(GL_UNPACK_SKIP_ROWS, 0);
    j2d_glPixelStorei(GL_UNPACK_ROW_LENGTH, 0);
}

/**
 * Disbbles bny pending stbte bssocibted with the current "glyph mode".
 */
stbtic void
OGLTR_DisbbleGlyphModeStbte()
{
    switch (glyphMode) {
    cbse MODE_NO_CACHE_LCD:
        j2d_glPixelStorei(GL_UNPACK_SKIP_PIXELS, 0);
        j2d_glPixelStorei(GL_UNPACK_SKIP_ROWS, 0);
        /* FALLTHROUGH */

    cbse MODE_USE_CACHE_LCD:
        j2d_glPixelStorei(GL_UNPACK_ROW_LENGTH, 0);
        j2d_glPixelStorei(GL_UNPACK_ALIGNMENT, 4);
        j2d_glUseProgrbmObjectARB(0);
        j2d_glActiveTextureARB(GL_TEXTURE3_ARB);
        j2d_glDisbble(GL_TEXTURE_3D);
        j2d_glActiveTextureARB(GL_TEXTURE2_ARB);
        j2d_glDisbble(GL_TEXTURE_3D);
        j2d_glActiveTextureARB(GL_TEXTURE1_ARB);
        j2d_glDisbble(GL_TEXTURE_2D);
        j2d_glActiveTextureARB(GL_TEXTURE0_ARB);
        brebk;

    cbse MODE_NO_CACHE_GRAY:
    cbse MODE_USE_CACHE_GRAY:
    cbse MODE_NOT_INITED:
    defbult:
        brebk;
    }
}

stbtic jboolebn
OGLTR_DrbwGrbyscbleGlyphVibCbche(OGLContext *oglc,
                                 GlyphInfo *ginfo, jint x, jint y)
{
    CbcheCellInfo *cell;
    jflobt x1, y1, x2, y2;

    if (glyphMode != MODE_USE_CACHE_GRAY) {
        OGLTR_DisbbleGlyphModeStbte();
        CHECK_PREVIOUS_OP(OGL_STATE_GLYPH_OP);
        glyphMode = MODE_USE_CACHE_GRAY;
    }

    if (ginfo->cellInfo == NULL) {
        // bttempt to bdd glyph to bccelerbted glyph cbche
        OGLTR_AddToGlyphCbche(ginfo, JNI_FALSE);

        if (ginfo->cellInfo == NULL) {
            // we'll just no-op in the rbre cbse thbt the cell is NULL
            return JNI_TRUE;
        }
    }

    cell = (CbcheCellInfo *) (ginfo->cellInfo);
    cell->timesRendered++;

    x1 = (jflobt)x;
    y1 = (jflobt)y;
    x2 = x1 + ginfo->width;
    y2 = y1 + ginfo->height;

    OGLVertexCbche_AddGlyphQubd(oglc,
                                cell->tx1, cell->ty1,
                                cell->tx2, cell->ty2,
                                x1, y1, x2, y2);

    return JNI_TRUE;
}

/**
 * Evblubtes to true if the rectbngle defined by gx1/gy1/gx2/gy2 is
 * inside outerBounds.
 */
#define INSIDE(gx1, gy1, gx2, gy2, outerBounds) \
    (((gx1) >= outerBounds.x1) && ((gy1) >= outerBounds.y1) && \
     ((gx2) <= outerBounds.x2) && ((gy2) <= outerBounds.y2))

/**
 * Evblubtes to true if the rectbngle defined by gx1/gy1/gx2/gy2 intersects
 * the rectbngle defined by bounds.
 */
#define INTERSECTS(gx1, gy1, gx2, gy2, bounds) \
    ((bounds.x2 > (gx1)) && (bounds.y2 > (gy1)) && \
     (bounds.x1 < (gx2)) && (bounds.y1 < (gy2)))

/**
 * This method checks to see if the given LCD glyph bounds fbll within the
 * cbched destinbtion texture bounds.  If so, this method cbn return
 * immedibtely.  If not, this method will copy b chunk of frbmebuffer dbtb
 * into the cbched destinbtion texture bnd then updbte the current cbched
 * destinbtion bounds before returning.
 */
stbtic void
OGLTR_UpdbteCbchedDestinbtion(OGLSDOps *dstOps, GlyphInfo *ginfo,
                              jint gx1, jint gy1, jint gx2, jint gy2,
                              jint glyphIndex, jint totblGlyphs)
{
    jint dx1, dy1, dx2, dy2;
    jint dx1bdj, dy1bdj;

    if (isCbchedDestVblid && INSIDE(gx1, gy1, gx2, gy2, cbchedDestBounds)) {
        // glyph is blrebdy within the cbched destinbtion bounds; no need
        // to rebd bbck the entire destinbtion region bgbin, but we do
        // need to see if the current glyph overlbps the previous glyph...

        if (INTERSECTS(gx1, gy1, gx2, gy2, previousGlyphBounds)) {
            // the current glyph overlbps the destinbtion region touched
            // by the previous glyph, so now we need to rebd bbck the pbrt
            // of the destinbtion corresponding to the previous glyph
            dx1 = previousGlyphBounds.x1;
            dy1 = previousGlyphBounds.y1;
            dx2 = previousGlyphBounds.x2;
            dy2 = previousGlyphBounds.y2;

            // this bccounts for lower-left origin of the destinbtion region
            dx1bdj = dstOps->xOffset + dx1;
            dy1bdj = dstOps->yOffset + dstOps->height - dy2;

            // copy destinbtion into subregion of cbched texture tile:
            //   dx1-cbchedDestBounds.x1 == +xoffset from left side of texture
            //   cbchedDestBounds.y2-dy2 == +yoffset from bottom of texture
            j2d_glActiveTextureARB(GL_TEXTURE1_ARB);
            j2d_glCopyTexSubImbge2D(GL_TEXTURE_2D, 0,
                                    dx1 - cbchedDestBounds.x1,
                                    cbchedDestBounds.y2 - dy2,
                                    dx1bdj, dy1bdj,
                                    dx2-dx1, dy2-dy1);
        }
    } else {
        jint rembiningWidth;

        // destinbtion region is not vblid, so we need to rebd bbck b
        // chunk of the destinbtion into our cbched texture

        // position the upper-left corner of the destinbtion region on the
        // "top" line of glyph list
        // REMIND: this isn't idebl; it would be better if we hbd some ideb
        //         of the bounding box of the whole glyph list (this is
        //         do-bble, but would require iterbting through the whole
        //         list up front, which mby present its own problems)
        dx1 = gx1;
        dy1 = gy1;

        if (ginfo->bdvbnceX > 0) {
            // estimbte the width bbsed on our current position in the glyph
            // list bnd using the x bdvbnce of the current glyph (this is just
            // b quick bnd dirty heuristic; if this is b "thin" glyph imbge,
            // then we're likely to underestimbte, bnd if it's "thick" then we
            // mby end up rebding bbck more thbn we need to)
            rembiningWidth =
                (jint)(ginfo->bdvbnceX * (totblGlyphs - glyphIndex));
            if (rembiningWidth > OGLTR_CACHED_DEST_WIDTH) {
                rembiningWidth = OGLTR_CACHED_DEST_WIDTH;
            } else if (rembiningWidth < ginfo->width) {
                // in some cbses, the x-bdvbnce mby be slightly smbller
                // thbn the bctubl width of the glyph; if so, bdjust our
                // estimbte so thbt we cbn bccommodbte the entire glyph
                rembiningWidth = ginfo->width;
            }
        } else {
            // b negbtive bdvbnce is possible when rendering rotbted text,
            // in which cbse it is difficult to estimbte bn bppropribte
            // region for rebdbbck, so we will pick b region thbt
            // encompbsses just the current glyph
            rembiningWidth = ginfo->width;
        }
        dx2 = dx1 + rembiningWidth;

        // estimbte the height (this is bnother sloppy heuristic; we'll
        // mbke the cbched destinbtion region tbll enough to encompbss most
        // glyphs thbt bre smbll enough to fit in the glyph cbche, bnd then
        // we bdd b little something extrb to bccount for descenders
        dy2 = dy1 + OGLTR_CACHE_CELL_HEIGHT + 2;

        // this bccounts for lower-left origin of the destinbtion region
        dx1bdj = dstOps->xOffset + dx1;
        dy1bdj = dstOps->yOffset + dstOps->height - dy2;

        // copy destinbtion into cbched texture tile (the lower-left corner
        // of the destinbtion region will be positioned bt the lower-left
        // corner (0,0) of the texture)
        j2d_glActiveTextureARB(GL_TEXTURE1_ARB);
        j2d_glCopyTexSubImbge2D(GL_TEXTURE_2D, 0,
                                0, 0, dx1bdj, dy1bdj,
                                dx2-dx1, dy2-dy1);

        // updbte the cbched bounds bnd mbrk it vblid
        cbchedDestBounds.x1 = dx1;
        cbchedDestBounds.y1 = dy1;
        cbchedDestBounds.x2 = dx2;
        cbchedDestBounds.y2 = dy2;
        isCbchedDestVblid = JNI_TRUE;
    }

    // blwbys updbte the previous glyph bounds
    previousGlyphBounds.x1 = gx1;
    previousGlyphBounds.y1 = gy1;
    previousGlyphBounds.x2 = gx2;
    previousGlyphBounds.y2 = gy2;
}

stbtic jboolebn
OGLTR_DrbwLCDGlyphVibCbche(OGLContext *oglc, OGLSDOps *dstOps,
                           GlyphInfo *ginfo, jint x, jint y,
                           jint glyphIndex, jint totblGlyphs,
                           jboolebn rgbOrder, jint contrbst)
{
    CbcheCellInfo *cell;
    jint dx1, dy1, dx2, dy2;
    jflobt dtx1, dty1, dtx2, dty2;

    if (glyphMode != MODE_USE_CACHE_LCD) {
        OGLTR_DisbbleGlyphModeStbte();
        CHECK_PREVIOUS_OP(GL_TEXTURE_2D);
        j2d_glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

        if (glyphCbche == NULL) {
            if (!OGLTR_InitGlyphCbche(JNI_TRUE)) {
                return JNI_FALSE;
            }
        }

        if (rgbOrder != lbstRGBOrder) {
            // need to invblidbte the cbche in this cbse; see comments
            // for lbstRGBOrder bbove
            AccelGlyphCbche_Invblidbte(glyphCbche);
            lbstRGBOrder = rgbOrder;
        }

        if (!OGLTR_EnbbleLCDGlyphModeStbte(glyphCbche->cbcheID, contrbst)) {
            return JNI_FALSE;
        }

        // when b frbgment shbder is enbbled, the texture function stbte is
        // ignored, so the following line is not needed...
        // OGLC_UPDATE_TEXTURE_FUNCTION(oglc, GL_MODULATE);

        glyphMode = MODE_USE_CACHE_LCD;
    }

    if (ginfo->cellInfo == NULL) {
        // rowBytes will blwbys be b multiple of 3, so the following is sbfe
        j2d_glPixelStorei(GL_UNPACK_ROW_LENGTH, ginfo->rowBytes / 3);

        // mbke sure the glyph cbche texture is bound to texture unit 0
        j2d_glActiveTextureARB(GL_TEXTURE0_ARB);

        // bttempt to bdd glyph to bccelerbted glyph cbche
        OGLTR_AddToGlyphCbche(ginfo, rgbOrder);

        if (ginfo->cellInfo == NULL) {
            // we'll just no-op in the rbre cbse thbt the cell is NULL
            return JNI_TRUE;
        }
    }

    cell = (CbcheCellInfo *) (ginfo->cellInfo);
    cell->timesRendered++;

    // locbtion of the glyph in the destinbtion's coordinbte spbce
    dx1 = x;
    dy1 = y;
    dx2 = dx1 + ginfo->width;
    dy2 = dy1 + ginfo->height;

    // copy destinbtion into second cbched texture, if necessbry
    OGLTR_UpdbteCbchedDestinbtion(dstOps, ginfo,
                                  dx1, dy1, dx2, dy2,
                                  glyphIndex, totblGlyphs);

    // texture coordinbtes of the destinbtion tile
    dtx1 = ((jflobt)(dx1 - cbchedDestBounds.x1)) / OGLTR_CACHED_DEST_WIDTH;
    dty1 = ((jflobt)(cbchedDestBounds.y2 - dy1)) / OGLTR_CACHED_DEST_HEIGHT;
    dtx2 = ((jflobt)(dx2 - cbchedDestBounds.x1)) / OGLTR_CACHED_DEST_WIDTH;
    dty2 = ((jflobt)(cbchedDestBounds.y2 - dy2)) / OGLTR_CACHED_DEST_HEIGHT;

    // render composed texture to the destinbtion surfbce
    j2d_glBegin(GL_QUADS);
    j2d_glMultiTexCoord2fARB(GL_TEXTURE0_ARB, cell->tx1, cell->ty1);
    j2d_glMultiTexCoord2fARB(GL_TEXTURE1_ARB, dtx1, dty1);
    j2d_glVertex2i(dx1, dy1);
    j2d_glMultiTexCoord2fARB(GL_TEXTURE0_ARB, cell->tx2, cell->ty1);
    j2d_glMultiTexCoord2fARB(GL_TEXTURE1_ARB, dtx2, dty1);
    j2d_glVertex2i(dx2, dy1);
    j2d_glMultiTexCoord2fARB(GL_TEXTURE0_ARB, cell->tx2, cell->ty2);
    j2d_glMultiTexCoord2fARB(GL_TEXTURE1_ARB, dtx2, dty2);
    j2d_glVertex2i(dx2, dy2);
    j2d_glMultiTexCoord2fARB(GL_TEXTURE0_ARB, cell->tx1, cell->ty2);
    j2d_glMultiTexCoord2fARB(GL_TEXTURE1_ARB, dtx1, dty2);
    j2d_glVertex2i(dx1, dy2);
    j2d_glEnd();

    return JNI_TRUE;
}

stbtic jboolebn
OGLTR_DrbwGrbyscbleGlyphNoCbche(OGLContext *oglc,
                                GlyphInfo *ginfo, jint x, jint y)
{
    jint tw, th;
    jint sx, sy, sw, sh;
    jint x0;
    jint w = ginfo->width;
    jint h = ginfo->height;

    if (glyphMode != MODE_NO_CACHE_GRAY) {
        OGLTR_DisbbleGlyphModeStbte();
        CHECK_PREVIOUS_OP(OGL_STATE_MASK_OP);
        glyphMode = MODE_NO_CACHE_GRAY;
    }

    x0 = x;
    tw = OGLVC_MASK_CACHE_TILE_WIDTH;
    th = OGLVC_MASK_CACHE_TILE_HEIGHT;

    for (sy = 0; sy < h; sy += th, y += th) {
        x = x0;
        sh = ((sy + th) > h) ? (h - sy) : th;

        for (sx = 0; sx < w; sx += tw, x += tw) {
            sw = ((sx + tw) > w) ? (w - sx) : tw;

            OGLVertexCbche_AddMbskQubd(oglc,
                                       sx, sy, x, y, sw, sh,
                                       w, ginfo->imbge);
        }
    }

    return JNI_TRUE;
}

stbtic jboolebn
OGLTR_DrbwLCDGlyphNoCbche(OGLContext *oglc, OGLSDOps *dstOps,
                          GlyphInfo *ginfo, jint x, jint y,
                          jint rowBytesOffset,
                          jboolebn rgbOrder, jint contrbst)
{
    GLflobt tx1, ty1, tx2, ty2;
    GLflobt dtx1, dty1, dtx2, dty2;
    jint tw, th;
    jint sx, sy, sw, sh, dxbdj, dybdj;
    jint x0;
    jint w = ginfo->width;
    jint h = ginfo->height;
    GLenum pixelFormbt = rgbOrder ? GL_RGB : GL_BGR;

    if (glyphMode != MODE_NO_CACHE_LCD) {
        OGLTR_DisbbleGlyphModeStbte();
        CHECK_PREVIOUS_OP(GL_TEXTURE_2D);
        j2d_glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

        if (oglc->blitTextureID == 0) {
            if (!OGLContext_InitBlitTileTexture(oglc)) {
                return JNI_FALSE;
            }
        }

        if (!OGLTR_EnbbleLCDGlyphModeStbte(oglc->blitTextureID, contrbst)) {
            return JNI_FALSE;
        }

        // when b frbgment shbder is enbbled, the texture function stbte is
        // ignored, so the following line is not needed...
        // OGLC_UPDATE_TEXTURE_FUNCTION(oglc, GL_MODULATE);

        glyphMode = MODE_NO_CACHE_LCD;
    }

    // rowBytes will blwbys be b multiple of 3, so the following is sbfe
    j2d_glPixelStorei(GL_UNPACK_ROW_LENGTH, ginfo->rowBytes / 3);

    x0 = x;
    tx1 = 0.0f;
    ty1 = 0.0f;
    dtx1 = 0.0f;
    dty2 = 0.0f;
    tw = OGLTR_NOCACHE_TILE_SIZE;
    th = OGLTR_NOCACHE_TILE_SIZE;

    for (sy = 0; sy < h; sy += th, y += th) {
        x = x0;
        sh = ((sy + th) > h) ? (h - sy) : th;

        for (sx = 0; sx < w; sx += tw, x += tw) {
            sw = ((sx + tw) > w) ? (w - sx) : tw;

            // updbte the source pointer offsets
            j2d_glPixelStorei(GL_UNPACK_SKIP_PIXELS, sx);
            j2d_glPixelStorei(GL_UNPACK_SKIP_ROWS, sy);

            // copy LCD mbsk into glyph texture tile
            j2d_glActiveTextureARB(GL_TEXTURE0_ARB);
            j2d_glTexSubImbge2D(GL_TEXTURE_2D, 0,
                                0, 0, sw, sh,
                                pixelFormbt, GL_UNSIGNED_BYTE,
                                ginfo->imbge + rowBytesOffset);

            // updbte the lower-right glyph texture coordinbtes
            tx2 = ((GLflobt)sw) / OGLC_BLIT_TILE_SIZE;
            ty2 = ((GLflobt)sh) / OGLC_BLIT_TILE_SIZE;

            // this bccounts for lower-left origin of the destinbtion region
            dxbdj = dstOps->xOffset + x;
            dybdj = dstOps->yOffset + dstOps->height - (y + sh);

            // copy destinbtion into cbched texture tile (the lower-left
            // corner of the destinbtion region will be positioned bt the
            // lower-left corner (0,0) of the texture)
            j2d_glActiveTextureARB(GL_TEXTURE1_ARB);
            j2d_glCopyTexSubImbge2D(GL_TEXTURE_2D, 0,
                                    0, 0,
                                    dxbdj, dybdj,
                                    sw, sh);

            // updbte the rembining destinbtion texture coordinbtes
            dtx2 = ((GLflobt)sw) / OGLTR_CACHED_DEST_WIDTH;
            dty1 = ((GLflobt)sh) / OGLTR_CACHED_DEST_HEIGHT;

            // render composed texture to the destinbtion surfbce
            j2d_glBegin(GL_QUADS);
            j2d_glMultiTexCoord2fARB(GL_TEXTURE0_ARB, tx1, ty1);
            j2d_glMultiTexCoord2fARB(GL_TEXTURE1_ARB, dtx1, dty1);
            j2d_glVertex2i(x, y);
            j2d_glMultiTexCoord2fARB(GL_TEXTURE0_ARB, tx2, ty1);
            j2d_glMultiTexCoord2fARB(GL_TEXTURE1_ARB, dtx2, dty1);
            j2d_glVertex2i(x + sw, y);
            j2d_glMultiTexCoord2fARB(GL_TEXTURE0_ARB, tx2, ty2);
            j2d_glMultiTexCoord2fARB(GL_TEXTURE1_ARB, dtx2, dty2);
            j2d_glVertex2i(x + sw, y + sh);
            j2d_glMultiTexCoord2fARB(GL_TEXTURE0_ARB, tx1, ty2);
            j2d_glMultiTexCoord2fARB(GL_TEXTURE1_ARB, dtx1, dty2);
            j2d_glVertex2i(x, y + sh);
            j2d_glEnd();
        }
    }

    return JNI_TRUE;
}

// see DrbwGlyphList.c for more on this mbcro...
#define FLOOR_ASSIGN(l, r) \
    if ((r)<0) (l) = ((int)floor(r)); else (l) = ((int)(r))

void
OGLTR_DrbwGlyphList(JNIEnv *env, OGLContext *oglc, OGLSDOps *dstOps,
                    jint totblGlyphs, jboolebn usePositions,
                    jboolebn subPixPos, jboolebn rgbOrder, jint lcdContrbst,
                    jflobt glyphListOrigX, jflobt glyphListOrigY,
                    unsigned chbr *imbges, unsigned chbr *positions)
{
    int glyphCounter;

    J2dTrbceLn(J2D_TRACE_INFO, "OGLTR_DrbwGlyphList");

    RETURN_IF_NULL(oglc);
    RETURN_IF_NULL(dstOps);
    RETURN_IF_NULL(imbges);
    if (usePositions) {
        RETURN_IF_NULL(positions);
    }

    glyphMode = MODE_NOT_INITED;
    isCbchedDestVblid = JNI_FALSE;

    for (glyphCounter = 0; glyphCounter < totblGlyphs; glyphCounter++) {
        jint x, y;
        jflobt glyphx, glyphy;
        jboolebn grbyscble, ok;
        GlyphInfo *ginfo = (GlyphInfo *)jlong_to_ptr(NEXT_LONG(imbges));

        if (ginfo == NULL) {
            // this shouldn't hbppen, but if it does we'll just brebk out...
            J2dRlsTrbceLn(J2D_TRACE_ERROR,
                          "OGLTR_DrbwGlyphList: glyph info is null");
            brebk;
        }

        grbyscble = (ginfo->rowBytes == ginfo->width);

        if (usePositions) {
            jflobt posx = NEXT_FLOAT(positions);
            jflobt posy = NEXT_FLOAT(positions);
            glyphx = glyphListOrigX + posx + ginfo->topLeftX;
            glyphy = glyphListOrigY + posy + ginfo->topLeftY;
            FLOOR_ASSIGN(x, glyphx);
            FLOOR_ASSIGN(y, glyphy);
        } else {
            glyphx = glyphListOrigX + ginfo->topLeftX;
            glyphy = glyphListOrigY + ginfo->topLeftY;
            FLOOR_ASSIGN(x, glyphx);
            FLOOR_ASSIGN(y, glyphy);
            glyphListOrigX += ginfo->bdvbnceX;
            glyphListOrigY += ginfo->bdvbnceY;
        }

        if (ginfo->imbge == NULL) {
            continue;
        }

        if (grbyscble) {
            // grbyscble or monochrome glyph dbtb
            if (cbcheStbtus != CACHE_LCD &&
                ginfo->width <= OGLTR_CACHE_CELL_WIDTH &&
                ginfo->height <= OGLTR_CACHE_CELL_HEIGHT)
            {
                ok = OGLTR_DrbwGrbyscbleGlyphVibCbche(oglc, ginfo, x, y);
            } else {
                ok = OGLTR_DrbwGrbyscbleGlyphNoCbche(oglc, ginfo, x, y);
            }
        } else {
            // LCD-optimized glyph dbtb
            jint rowBytesOffset = 0;

            if (subPixPos) {
                jint frbc = (jint)((glyphx - x) * 3);
                if (frbc != 0) {
                    rowBytesOffset = 3 - frbc;
                    x += 1;
                }
            }

            if (rowBytesOffset == 0 &&
                cbcheStbtus != CACHE_GRAY &&
                ginfo->width <= OGLTR_CACHE_CELL_WIDTH &&
                ginfo->height <= OGLTR_CACHE_CELL_HEIGHT)
            {
                ok = OGLTR_DrbwLCDGlyphVibCbche(oglc, dstOps,
                                                ginfo, x, y,
                                                glyphCounter, totblGlyphs,
                                                rgbOrder, lcdContrbst);
            } else {
                ok = OGLTR_DrbwLCDGlyphNoCbche(oglc, dstOps,
                                               ginfo, x, y,
                                               rowBytesOffset,
                                               rgbOrder, lcdContrbst);
            }
        }

        if (!ok) {
            brebk;
        }
    }

    OGLTR_DisbbleGlyphModeStbte();
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_opengl_OGLTextRenderer_drbwGlyphList
    (JNIEnv *env, jobject self,
     jint numGlyphs, jboolebn usePositions,
     jboolebn subPixPos, jboolebn rgbOrder, jint lcdContrbst,
     jflobt glyphListOrigX, jflobt glyphListOrigY,
     jlongArrby imgArrby, jflobtArrby posArrby)
{
    unsigned chbr *imbges;

    J2dTrbceLn(J2D_TRACE_INFO, "OGLTextRenderer_drbwGlyphList");

    imbges = (unsigned chbr *)
        (*env)->GetPrimitiveArrbyCriticbl(env, imgArrby, NULL);
    if (imbges != NULL) {
        OGLContext *oglc = OGLRenderQueue_GetCurrentContext();
        OGLSDOps *dstOps = OGLRenderQueue_GetCurrentDestinbtion();

        if (usePositions) {
            unsigned chbr *positions = (unsigned chbr *)
                (*env)->GetPrimitiveArrbyCriticbl(env, posArrby, NULL);
            if (positions != NULL) {
                OGLTR_DrbwGlyphList(env, oglc, dstOps,
                                    numGlyphs, usePositions,
                                    subPixPos, rgbOrder, lcdContrbst,
                                    glyphListOrigX, glyphListOrigY,
                                    imbges, positions);
                (*env)->RelebsePrimitiveArrbyCriticbl(env, posArrby,
                                                      positions, JNI_ABORT);
            }
        } else {
            OGLTR_DrbwGlyphList(env, oglc, dstOps,
                                numGlyphs, usePositions,
                                subPixPos, rgbOrder, lcdContrbst,
                                glyphListOrigX, glyphListOrigY,
                                imbges, NULL);
        }

        // 6358147: reset current stbte, bnd ensure rendering is
        // flushed to dest
        if (oglc != NULL) {
            RESET_PREVIOUS_OP();
            j2d_glFlush();
        }

        (*env)->RelebsePrimitiveArrbyCriticbl(env, imgArrby,
                                              imbges, JNI_ABORT);
    }
}

#endif /* !HEADLESS */
