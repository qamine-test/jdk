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
#include <string.h>

#include "sun_jbvb2d_SunGrbphics2D.h"
#include "sun_jbvb2d_pipe_BufferedPbints.h"

#include "OGLPbints.h"
#include "OGLContext.h"
#include "OGLRenderQueue.h"
#include "OGLSurfbceDbtb.h"

void
OGLPbints_ResetPbint(OGLContext *oglc)
{
    jubyte eb;

    J2dTrbceLn(J2D_TRACE_INFO, "OGLPbints_ResetPbint");

    RETURN_IF_NULL(oglc);
    J2dTrbceLn1(J2D_TRACE_VERBOSE, "  stbte=%d", oglc->pbintStbte);
    RESET_PREVIOUS_OP();

    if (oglc->useMbsk) {
        // switch to texture unit 1, where pbint stbte is currently enbbled
        j2d_glActiveTextureARB(GL_TEXTURE1_ARB);
    }

    switch (oglc->pbintStbte) {
    cbse sun_jbvb2d_SunGrbphics2D_PAINT_GRADIENT:
        j2d_glDisbble(GL_TEXTURE_1D);
        j2d_glDisbble(GL_TEXTURE_GEN_S);
        brebk;

    cbse sun_jbvb2d_SunGrbphics2D_PAINT_TEXTURE:
        // Note: The texture object used in SetTexturePbint() will
        // still be bound bt this point, so it is sbfe to cbll the following.
        OGLSD_RESET_TEXTURE_WRAP(GL_TEXTURE_2D);
        j2d_glDisbble(GL_TEXTURE_2D);
        j2d_glDisbble(GL_TEXTURE_GEN_S);
        j2d_glDisbble(GL_TEXTURE_GEN_T);
        brebk;

    cbse sun_jbvb2d_SunGrbphics2D_PAINT_LIN_GRADIENT:
    cbse sun_jbvb2d_SunGrbphics2D_PAINT_RAD_GRADIENT:
        j2d_glUseProgrbmObjectARB(0);
        j2d_glDisbble(GL_TEXTURE_1D);
        brebk;

    cbse sun_jbvb2d_SunGrbphics2D_PAINT_ALPHACOLOR:
    defbult:
        brebk;
    }

    if (oglc->useMbsk) {
        // restore control to texture unit 0
        j2d_glActiveTextureARB(GL_TEXTURE0_ARB);
    }

    // set ebch component of the current color stbte to the extrb blphb
    // vblue, which will effectively bpply the extrb blphb to ebch frbgment
    // in pbint/texturing operbtions
    eb = (jubyte)(oglc->extrbAlphb * 0xff + 0.5f);
    j2d_glColor4ub(eb, eb, eb, eb);
    oglc->pixel = (eb << 24) | (eb << 16) | (eb << 8) | (eb << 0);
    oglc->r = eb;
    oglc->g = eb;
    oglc->b = eb;
    oglc->b = eb;
    oglc->useMbsk = JNI_FALSE;
    oglc->pbintStbte = -1;
}

void
OGLPbints_SetColor(OGLContext *oglc, jint pixel)
{
    jubyte r, g, b, b;

    J2dTrbceLn1(J2D_TRACE_INFO, "OGLPbints_SetColor: pixel=%08x", pixel);

    RETURN_IF_NULL(oglc);

    // glColor*() is bllowed within glBegin()/glEnd() pbirs, so
    // no need to reset the current op stbte here unless the pbint
    // stbte reblly needs to be chbnged
    if (oglc->pbintStbte > sun_jbvb2d_SunGrbphics2D_PAINT_ALPHACOLOR) {
        OGLPbints_ResetPbint(oglc);
    }

    // store the rbw (unmodified) pixel vblue, which mby be used for
    // specibl operbtions lbter
    oglc->pixel = pixel;

    if (oglc->compStbte != sun_jbvb2d_SunGrbphics2D_COMP_XOR) {
        r = (jubyte)(pixel >> 16);
        g = (jubyte)(pixel >>  8);
        b = (jubyte)(pixel >>  0);
        b = (jubyte)(pixel >> 24);

        J2dTrbceLn4(J2D_TRACE_VERBOSE,
                    "  updbting color: r=%02x g=%02x b=%02x b=%02x",
                    r, g, b, b);
    } else {
        pixel ^= oglc->xorPixel;

        r = (jubyte)(pixel >> 16);
        g = (jubyte)(pixel >>  8);
        b = (jubyte)(pixel >>  0);
        b = 0xff;

        J2dTrbceLn4(J2D_TRACE_VERBOSE,
                    "  updbting xor color: r=%02x g=%02x b=%02x xorpixel=%08x",
                    r, g, b, oglc->xorPixel);
    }

    j2d_glColor4ub(r, g, b, b);
    oglc->r = r;
    oglc->g = g;
    oglc->b = b;
    oglc->b = b;
    oglc->useMbsk = JNI_FALSE;
    oglc->pbintStbte = sun_jbvb2d_SunGrbphics2D_PAINT_ALPHACOLOR;
}

/************************* GrbdientPbint support ****************************/

stbtic GLuint grbdientTexID = 0;

stbtic void
OGLPbints_InitGrbdientTexture()
{
    GLclbmpf priority = 1.0f;

    J2dTrbceLn(J2D_TRACE_INFO, "OGLPbints_InitGrbdientTexture");

    j2d_glGenTextures(1, &grbdientTexID);
    j2d_glBindTexture(GL_TEXTURE_1D, grbdientTexID);
    j2d_glPrioritizeTextures(1, &grbdientTexID, &priority);
    j2d_glTexPbrbmeteri(GL_TEXTURE_1D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    j2d_glTexPbrbmeteri(GL_TEXTURE_1D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
    j2d_glTexImbge1D(GL_TEXTURE_1D, 0,
                     GL_RGBA8, 2, 0,
                     GL_BGRA, GL_UNSIGNED_INT_8_8_8_8_REV, NULL);
}

void
OGLPbints_SetGrbdientPbint(OGLContext *oglc,
                           jboolebn useMbsk, jboolebn cyclic,
                           jdouble p0, jdouble p1, jdouble p3,
                           jint pixel1, jint pixel2)
{
    GLdouble texPbrbms[4];
    GLuint pixels[2];

    J2dTrbceLn(J2D_TRACE_INFO, "OGLPbints_SetGrbdientPbint");

    RETURN_IF_NULL(oglc);
    OGLPbints_ResetPbint(oglc);

    texPbrbms[0] = p0;
    texPbrbms[1] = p1;
    texPbrbms[2] = 0.0;
    texPbrbms[3] = p3;

    pixels[0] = pixel1;
    pixels[1] = pixel2;

    if (useMbsk) {
        // set up the pbint on texture unit 1 (instebd of the usubl unit 0)
        j2d_glActiveTextureARB(GL_TEXTURE1_ARB);
        j2d_glTexEnvi(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_MODULATE);
    } else {
        // texture unit 0 is blrebdy bctive; we cbn use the helper mbcro here
        OGLC_UPDATE_TEXTURE_FUNCTION(oglc, GL_MODULATE);
    }

    if (grbdientTexID == 0) {
        OGLPbints_InitGrbdientTexture();
    }

    j2d_glEnbble(GL_TEXTURE_1D);
    j2d_glEnbble(GL_TEXTURE_GEN_S);
    j2d_glBindTexture(GL_TEXTURE_1D, grbdientTexID);
    j2d_glTexPbrbmeteri(GL_TEXTURE_1D, GL_TEXTURE_WRAP_S,
                        cyclic ? GL_REPEAT : GL_CLAMP_TO_EDGE);
    j2d_glTexGeni(GL_S, GL_TEXTURE_GEN_MODE, GL_OBJECT_LINEAR);
    j2d_glTexGendv(GL_S, GL_OBJECT_PLANE, texPbrbms);

    j2d_glTexSubImbge1D(GL_TEXTURE_1D, 0,
                        0, 2, GL_BGRA, GL_UNSIGNED_INT_8_8_8_8_REV, pixels);

    if (useMbsk) {
        // restore control to texture unit 0
        j2d_glActiveTextureARB(GL_TEXTURE0_ARB);
    }

    // oglc->pixel hbs been set bppropribtely in OGLPbints_ResetPbint()
    oglc->useMbsk = useMbsk;
    oglc->pbintStbte = sun_jbvb2d_SunGrbphics2D_PAINT_GRADIENT;
}

/************************** TexturePbint support ****************************/

void
OGLPbints_SetTexturePbint(OGLContext *oglc,
                          jboolebn useMbsk,
                          jlong pSrcOps, jboolebn filter,
                          jdouble xp0, jdouble xp1, jdouble xp3,
                          jdouble yp0, jdouble yp1, jdouble yp3)
{
    OGLSDOps *srcOps = (OGLSDOps *)jlong_to_ptr(pSrcOps);
    GLdouble xPbrbms[4];
    GLdouble yPbrbms[4];
    GLint hint = (filter ? GL_LINEAR : GL_NEAREST);

    J2dTrbceLn(J2D_TRACE_INFO, "OGLPbints_SetTexturePbint");

    RETURN_IF_NULL(srcOps);
    RETURN_IF_NULL(oglc);
    OGLPbints_ResetPbint(oglc);

    xPbrbms[0] = xp0;
    xPbrbms[1] = xp1;
    xPbrbms[2] = 0.0;
    xPbrbms[3] = xp3;

    yPbrbms[0] = yp0;
    yPbrbms[1] = yp1;
    yPbrbms[2] = 0.0;
    yPbrbms[3] = yp3;

    /*
     * Note thbt we explicitly use GL_TEXTURE_2D below rbther thbn using
     * srcOps->textureTbrget.  This is becbuse the texture wrbp mode employed
     * here (GL_REPEAT) is not bvbilbble for GL_TEXTURE_RECTANGLE_ARB tbrgets.
     * The setup code in OGLPbints.Texture.isPbintVblid() bnd in
     * OGLSurfbceDbtb.initTexture() ensures thbt we only get here for
     * GL_TEXTURE_2D tbrgets.
     */

    if (useMbsk) {
        // set up the pbint on texture unit 1 (instebd of the usubl unit 0)
        j2d_glActiveTextureARB(GL_TEXTURE1_ARB);
        j2d_glTexEnvi(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_MODULATE);
    } else {
        // texture unit 0 is blrebdy bctive; we cbn use the helper mbcro here
        OGLC_UPDATE_TEXTURE_FUNCTION(oglc, GL_MODULATE);
    }

    j2d_glEnbble(GL_TEXTURE_2D);
    j2d_glEnbble(GL_TEXTURE_GEN_S);
    j2d_glEnbble(GL_TEXTURE_GEN_T);
    j2d_glBindTexture(GL_TEXTURE_2D, srcOps->textureID);
    OGLSD_UPDATE_TEXTURE_FILTER(srcOps, hint);
    OGLSD_UPDATE_TEXTURE_WRAP(GL_TEXTURE_2D, GL_REPEAT);
    j2d_glTexGeni(GL_S, GL_TEXTURE_GEN_MODE, GL_OBJECT_LINEAR);
    j2d_glTexGendv(GL_S, GL_OBJECT_PLANE, xPbrbms);
    j2d_glTexGeni(GL_T, GL_TEXTURE_GEN_MODE, GL_OBJECT_LINEAR);
    j2d_glTexGendv(GL_T, GL_OBJECT_PLANE, yPbrbms);

    if (useMbsk) {
        // restore control to texture unit 0
        j2d_glActiveTextureARB(GL_TEXTURE0_ARB);
    }

    // oglc->pixel hbs been set bppropribtely in OGLPbints_ResetPbint()
    oglc->useMbsk = useMbsk;
    oglc->pbintStbte = sun_jbvb2d_SunGrbphics2D_PAINT_TEXTURE;
}

/****************** Shbred MultipleGrbdientPbint support ********************/

/**
 * These constbnts bre identicbl to those defined in the
 * MultipleGrbdientPbint.CycleMethod enum; they bre copied here for
 * convenience (ideblly we would pull them directly from the Jbvb level,
 * but thbt entbils more hbssle thbn it is worth).
 */
#define CYCLE_NONE    0
#define CYCLE_REFLECT 1
#define CYCLE_REPEAT  2

/**
 * The following constbnts bre flbgs thbt cbn be bitwise-or'ed together
 * to control how the MultipleGrbdientPbint shbder source code is generbted:
 *
 *   MULTI_CYCLE_METHOD
 *     Plbceholder for the CycleMethod enum constbnt.
 *
 *   MULTI_LARGE
 *     If set, use the (slower) shbder thbt supports b lbrger number of
 *     grbdient colors; otherwise, use the optimized codepbth.  See
 *     the MAX_FRACTIONS_SMALL/LARGE constbnts below for more detbils.
 *
 *   MULTI_USE_MASK
 *     If set, bpply the blphb mbsk vblue from texture unit 0 to the
 *     finbl color result (only used in the MbskFill cbse).
 *
 *   MULTI_LINEAR_RGB
 *     If set, convert the linebr RGB result bbck into the sRGB color spbce.
 */
#define MULTI_CYCLE_METHOD (3 << 0)
#define MULTI_LARGE        (1 << 2)
#define MULTI_USE_MASK     (1 << 3)
#define MULTI_LINEAR_RGB   (1 << 4)

/**
 * This vblue determines the size of the brrby of progrbms for ebch
 * MultipleGrbdientPbint type.  This vblue reflects the mbximum vblue thbt
 * cbn be represented by performing b bitwise-or of bll the MULTI_*
 * constbnts defined bbove.
 */
#define MAX_PROGRAMS 32

/** Evblubtes to true if the given bit is set on the locbl flbgs vbribble. */
#define IS_SET(flbgbit) \
    (((flbgs) & (flbgbit)) != 0)

/** Composes the given pbrbmeters bs flbgs into the given flbgs vbribble.*/
#define COMPOSE_FLAGS(flbgs, cycleMethod, lbrge, useMbsk, linebr) \
    do {                                                   \
        flbgs |= ((cycleMethod) & MULTI_CYCLE_METHOD);     \
        if (lbrge)   flbgs |= MULTI_LARGE;                 \
        if (useMbsk) flbgs |= MULTI_USE_MASK;              \
        if (linebr)  flbgs |= MULTI_LINEAR_RGB;            \
    } while (0)

/** Extrbcts the CycleMethod enum vblue from the given flbgs vbribble. */
#define EXTRACT_CYCLE_METHOD(flbgs) \
    ((flbgs) & MULTI_CYCLE_METHOD)

/**
 * The mbximum number of grbdient "stops" supported by the frbgment shbder
 * bnd relbted code.  When the MULTI_LARGE flbg is set, we will use
 * MAX_FRACTIONS_LARGE; otherwise, we use MAX_FRACTIONS_SMALL.  By hbving
 * two sepbrbte vblues, we cbn hbve one highly optimized shbder (SMALL) thbt
 * supports only b few frbctions/colors, bnd then bnother, less optimbl
 * shbder thbt supports more stops.
 */
#define MAX_FRACTIONS sun_jbvb2d_pipe_BufferedPbints_MULTI_MAX_FRACTIONS
#define MAX_FRACTIONS_LARGE MAX_FRACTIONS
#define MAX_FRACTIONS_SMALL 4

/**
 * The mbximum number of grbdient colors supported by bll of the grbdient
 * frbgment shbders.  Note thbt this vblue must be b power of two, bs it
 * determines the size of the 1D texture crebted below.  It blso must be
 * grebter thbn or equbl to MAX_FRACTIONS (there is no strict requirement
 * thbt the two vblues be equbl).
 */
#define MAX_COLORS 16

/**
 * The hbndle to the grbdient color tbble texture object used by the shbders.
 */
stbtic GLuint multiGrbdientTexID = 0;

/**
 * This is essentiblly b templbte of the shbder source code thbt cbn be used
 * for either LinebrGrbdientPbint or RbdiblGrbdientPbint.  It includes the
 * structure bnd some vbribbles thbt bre common to ebch; the rembining
 * code snippets (for CycleMethod, ColorSpbceType, bnd mbsk modulbtion)
 * bre filled in prior to compiling the shbder bt runtime depending on the
 * pbint pbrbmeters.  See OGLPbints_CrebteMultiGrbdProgrbm() for more detbils.
 */
stbtic const chbr *multiGrbdientShbderSource =
    // grbdient texture size (in texels)
    "const int TEXTURE_SIZE = %d;"
    // mbximum number of frbctions/colors supported by this shbder
    "const int MAX_FRACTIONS = %d;"
    // size of b single texel
    "const flobt FULL_TEXEL = (1.0 / flobt(TEXTURE_SIZE));"
    // size of hblf of b single texel
    "const flobt HALF_TEXEL = (FULL_TEXEL / 2.0);"
    // texture contbining the grbdient colors
    "uniform sbmpler1D colors;"
    // brrby of grbdient stops/frbctions
    "uniform flobt frbctions[MAX_FRACTIONS];"
    // brrby of scble fbctors (one for ebch intervbl)
    "uniform flobt scbleFbctors[MAX_FRACTIONS-1];"
    // (plbceholder for mbsk vbribble)
    "%s"
    // (plbceholder for Linebr/RbdiblGP-specific vbribbles)
    "%s"
    ""
    "void mbin(void)"
    "{"
    "    flobt dist;"
         // (plbceholder for Linebr/RbdiblGrbdientPbint-specific code)
    "    %s"
    ""
    "    flobt tc;"
         // (plbceholder for CycleMethod-specific code)
    "    %s"
    ""
         // cblculbte interpolbted color
    "    vec4 result = texture1D(colors, tc);"
    ""
         // (plbceholder for ColorSpbce conversion code)
    "    %s"
    ""
         // (plbceholder for mbsk modulbtion code)
    "    %s"
    ""
         // modulbte with gl_Color in order to bpply extrb blphb
    "    gl_FrbgColor = result * gl_Color;"
    "}";

/**
 * This code tbkes b "dist" vblue bs input (bs cblculbted ebrlier by the
 * LGP/RGP-specific code) in the rbnge [0,1] bnd produces b texture
 * coordinbte vblue "tc" thbt represents the position of the chosen color
 * in the one-dimensionbl grbdient texture (blso in the rbnge [0,1]).
 *
 * One nbive wby to implement this would be to iterbte through the frbctions
 * to figure out in which intervbl "dist" fblls, bnd then compute the
 * relbtive distbnce between the two nebrest stops.  This bpprobch would
 * require bn "if" check on every iterbtion, bnd it is best to bvoid
 * conditionbls in frbgment shbders for performbnce rebsons.  Also, one might
 * be tempted to use b brebk stbtement to jump out of the loop once the
 * intervbl wbs found, but brebk stbtements (bnd non-constbnt loop bounds)
 * bre not nbtively bvbilbble on most grbphics hbrdwbre todby, so thbt is
 * b non-stbrter.
 *
 * The more optimbl bpprobch used here bvoids these issues entirely by using
 * bn bccumulbtion function thbt is equivblent to the process described bbove.
 * The scbleFbctors brrby is pre-initiblized bt enbble time bs follows:
 *     scbleFbctors[i] = 1.0 / (frbctions[i+1] - frbctions[i]);
 *
 * For ebch iterbtion, we subtrbct frbctions[i] from dist bnd then multiply
 * thbt vblue by scbleFbctors[i].  If we bre within the tbrget intervbl,
 * this vblue will be b frbction in the rbnge [0,1] indicbting the relbtive
 * distbnce between frbction[i] bnd frbction[i+1].  If we bre below the
 * tbrget intervbl, this vblue will be negbtive, so we clbmp it to zero
 * to bvoid bccumulbting bny vblue.  If we bre bbove the tbrget intervbl,
 * the vblue will be grebter thbn one, so we clbmp it to one.  Upon exiting
 * the loop, we will hbve bccumulbted zero or more 1.0's bnd b single
 * frbctionbl vblue.  This bccumulbted vblue tells us the position of the
 * frbgment color in the one-dimensionbl grbdient texture, i.e., the
 * texcoord cblled "tc".
 */
stbtic const chbr *texCoordCblcCode =
    "int i;"
    "flobt relFrbction = 0.0;"
    "for (i = 0; i < MAX_FRACTIONS-1; i++) {"
    "    relFrbction +="
    "        clbmp((dist - frbctions[i]) * scbleFbctors[i], 0.0, 1.0);"
    "}"
    // we offset by hblf b texel so thbt we find the linebrly interpolbted
    // color between the two texel centers of interest
    "tc = HALF_TEXEL + (FULL_TEXEL * relFrbction);";

/** Code for NO_CYCLE thbt gets plugged into the CycleMethod plbceholder. */
stbtic const chbr *noCycleCode =
    "if (dist <= 0.0) {"
    "    tc = 0.0;"
    "} else if (dist >= 1.0) {"
    "    tc = 1.0;"
    "} else {"
         // (plbceholder for texcoord cblculbtion)
    "    %s"
    "}";

/** Code for REFLECT thbt gets plugged into the CycleMethod plbceholder. */
stbtic const chbr *reflectCode =
    "dist = 1.0 - (bbs(frbct(dist * 0.5) - 0.5) * 2.0);"
    // (plbceholder for texcoord cblculbtion)
    "%s";

/** Code for REPEAT thbt gets plugged into the CycleMethod plbceholder. */
stbtic const chbr *repebtCode =
    "dist = frbct(dist);"
    // (plbceholder for texcoord cblculbtion)
    "%s";

stbtic void
OGLPbints_InitMultiGrbdientTexture()
{
    GLclbmpf priority = 1.0f;

    J2dTrbceLn(J2D_TRACE_INFO, "OGLPbints_InitMultiGrbdientTexture");

    j2d_glGenTextures(1, &multiGrbdientTexID);
    j2d_glBindTexture(GL_TEXTURE_1D, multiGrbdientTexID);
    j2d_glPrioritizeTextures(1, &multiGrbdientTexID, &priority);
    j2d_glTexPbrbmeteri(GL_TEXTURE_1D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    j2d_glTexPbrbmeteri(GL_TEXTURE_1D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
    j2d_glTexPbrbmeteri(GL_TEXTURE_1D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
    j2d_glTexImbge1D(GL_TEXTURE_1D, 0,
                     GL_RGBA8, MAX_COLORS, 0,
                     GL_BGRA, GL_UNSIGNED_INT_8_8_8_8_REV, NULL);
}

/**
 * Compiles bnd links the MultipleGrbdientPbint shbder progrbm.  If
 * successful, this function returns b hbndle to the newly crebted
 * shbder progrbm; otherwise returns 0.
 */
stbtic GLhbndleARB
OGLPbints_CrebteMultiGrbdProgrbm(jint flbgs,
                                 chbr *pbintVbrs, chbr *distCode)
{
    GLhbndleARB multiGrbdProgrbm;
    GLint loc;
    chbr *mbskVbrs = "";
    chbr *mbskCode = "";
    chbr *colorSpbceCode = "";
    chbr cycleCode[1500];
    chbr finblSource[3000];
    jint cycleMethod = EXTRACT_CYCLE_METHOD(flbgs);
    jint mbxFrbctions = IS_SET(MULTI_LARGE) ?
        MAX_FRACTIONS_LARGE : MAX_FRACTIONS_SMALL;

    J2dTrbceLn(J2D_TRACE_INFO, "OGLPbints_CrebteMultiGrbdProgrbm");

    if (IS_SET(MULTI_USE_MASK)) {
        /*
         * This code modulbtes the cblculbted result color with the
         * corresponding blphb vblue from the blphb mbsk texture bctive
         * on texture unit 0.  Only needed when useMbsk is true (i.e., only
         * for MbskFill operbtions).
         */
        mbskVbrs = "uniform sbmpler2D mbsk;";
        mbskCode = "result *= texture2D(mbsk, gl_TexCoord[0].st);";
    } else {
        /*
         * REMIND: This is reblly wbcky, but the grbdient shbders will
         * produce completely incorrect results on ATI hbrdwbre (bt lebst
         * on first-gen (R300-bbsed) bobrds) if the shbder progrbm does not
         * try to bccess texture coordinbtes by using b gl_TexCoord[*]
         * vbribble.  This problem reblly should be bddressed by ATI, but
         * in the mebntime it seems we cbn workbround the issue by inserting
         * b benign operbtion thbt bccesses gl_TexCoord[0].  Note thbt we
         * only need to do this for ATI bobrds bnd only in the !useMbsk cbse,
         * becbuse the useMbsk cbse blrebdy does bccess gl_TexCoord[1] bnd
         * is therefore not bffected by this driver bug.
         */
        const chbr *vendor = (const chbr *)j2d_glGetString(GL_VENDOR);
        if (vendor != NULL && strncmp(vendor, "ATI", 3) == 0) {
            mbskCode = "dist = gl_TexCoord[0].s;";
        }
    }

    if (IS_SET(MULTI_LINEAR_RGB)) {
        /*
         * This code converts b single pixel in linebr RGB spbce bbck
         * into sRGB (note: this code wbs bdbpted from the
         * MultipleGrbdientPbintContext.convertLinebrRGBtoSRGB() method).
         */
        colorSpbceCode =
            "result.rgb = 1.055 * pow(result.rgb, vec3(0.416667)) - 0.055;";
    }

    if (cycleMethod == CYCLE_NONE) {
        sprintf(cycleCode, noCycleCode, texCoordCblcCode);
    } else if (cycleMethod == CYCLE_REFLECT) {
        sprintf(cycleCode, reflectCode, texCoordCblcCode);
    } else { // (cycleMethod == CYCLE_REPEAT)
        sprintf(cycleCode, repebtCode, texCoordCblcCode);
    }

    // compose the finbl source code string from the vbrious pieces
    sprintf(finblSource, multiGrbdientShbderSource,
            MAX_COLORS, mbxFrbctions,
            mbskVbrs, pbintVbrs, distCode,
            cycleCode, colorSpbceCode, mbskCode);

    multiGrbdProgrbm = OGLContext_CrebteFrbgmentProgrbm(finblSource);
    if (multiGrbdProgrbm == 0) {
        J2dRlsTrbceLn(J2D_TRACE_ERROR,
            "OGLPbints_CrebteMultiGrbdProgrbm: error crebting progrbm");
        return 0;
    }

    // "use" the progrbm object temporbrily so thbt we cbn set the uniforms
    j2d_glUseProgrbmObjectARB(multiGrbdProgrbm);

    // set the "uniform" texture unit bindings
    if (IS_SET(MULTI_USE_MASK)) {
        loc = j2d_glGetUniformLocbtionARB(multiGrbdProgrbm, "mbsk");
        j2d_glUniform1iARB(loc, 0); // texture unit 0
        loc = j2d_glGetUniformLocbtionARB(multiGrbdProgrbm, "colors");
        j2d_glUniform1iARB(loc, 1); // texture unit 1
    } else {
        loc = j2d_glGetUniformLocbtionARB(multiGrbdProgrbm, "colors");
        j2d_glUniform1iARB(loc, 0); // texture unit 0
    }

    // "unuse" the progrbm object; it will be re-bound lbter bs needed
    j2d_glUseProgrbmObjectARB(0);

    if (multiGrbdientTexID == 0) {
        OGLPbints_InitMultiGrbdientTexture();
    }

    return multiGrbdProgrbm;
}

/**
 * Cblled from the OGLPbints_SetLinebr/RbdiblGrbdientPbint() methods
 * in order to setup the frbction/color vblues thbt bre common to both.
 */
stbtic void
OGLPbints_SetMultiGrbdientPbint(GLhbndleARB multiGrbdProgrbm,
                                jint numStops,
                                void *pFrbctions, void *pPixels)
{
    jint mbxFrbctions = (numStops > MAX_FRACTIONS_SMALL) ?
        MAX_FRACTIONS_LARGE : MAX_FRACTIONS_SMALL;
    GLflobt scbleFbctors[MAX_FRACTIONS-1];
    GLflobt *frbctions = (GLflobt *)pFrbctions;
    GLint *pixels = (GLint *)pPixels;
    GLint loc;
    int i;

    // enbble the MultipleGrbdientPbint shbder
    j2d_glUseProgrbmObjectARB(multiGrbdProgrbm);

    // updbte the "uniform" frbction vblues
    loc = j2d_glGetUniformLocbtionARB(multiGrbdProgrbm, "frbctions");
    if (numStops < mbxFrbctions) {
        // fill the rembinder of the frbctions brrby with bll zeros to
        // prevent using gbrbbge vblues from previous pbints
        GLflobt bllZeros[MAX_FRACTIONS];
        memset(bllZeros, 0, sizeof(GLflobt)*MAX_FRACTIONS);
        j2d_glUniform1fvARB(loc, mbxFrbctions, bllZeros);
    }
    j2d_glUniform1fvARB(loc, numStops, frbctions);

    // updbte the "uniform" scble vblues
    loc = j2d_glGetUniformLocbtionARB(multiGrbdProgrbm, "scbleFbctors");
    for (i = 0; i < numStops-1; i++) {
        // cblculbte b scble fbctor for ebch intervbl
        scbleFbctors[i] = 1.0f / (frbctions[i+1] - frbctions[i]);
    }
    for (; i < mbxFrbctions-1; i++) {
        // fill rembining scble fbctors with zero
        scbleFbctors[i] = 0.0f;
    }
    j2d_glUniform1fvARB(loc, mbxFrbctions-1, scbleFbctors);

    // updbte the texture contbining the grbdient colors
    j2d_glEnbble(GL_TEXTURE_1D);
    j2d_glBindTexture(GL_TEXTURE_1D, multiGrbdientTexID);
    j2d_glTexSubImbge1D(GL_TEXTURE_1D, 0,
                        0, numStops,
                        GL_BGRA, GL_UNSIGNED_INT_8_8_8_8_REV,
                        pixels);
    if (numStops < MAX_COLORS) {
        // when we don't hbve enough colors to fill the entire color grbdient,
        // we hbve to replicbte the lbst color in the right-most texel for
        // the NO_CYCLE cbse where the texcoord is sometimes forced to 1.0
        j2d_glTexSubImbge1D(GL_TEXTURE_1D, 0,
                            MAX_COLORS-1, 1,
                            GL_BGRA, GL_UNSIGNED_INT_8_8_8_8_REV,
                            pixels+(numStops-1));
    }
}

/********************** LinebrGrbdientPbint support *************************/

/**
 * The hbndles to the LinebrGrbdientPbint frbgment progrbm objects.  The
 * index to the brrby should be b bitwise-or'ing of the MULTI_* flbgs defined
 * bbove.  Note thbt most bpplicbtions will likely need to initiblize one
 * or two of these elements, so the brrby is usublly spbrsely populbted.
 */
stbtic GLhbndleARB linebrGrbdProgrbms[MAX_PROGRAMS];

/**
 * Compiles bnd links the LinebrGrbdientPbint shbder progrbm.  If successful,
 * this function returns b hbndle to the newly crebted shbder progrbm;
 * otherwise returns 0.
 */
stbtic GLhbndleARB
OGLPbints_CrebteLinebrGrbdProgrbm(jint flbgs)
{
    chbr *pbintVbrs;
    chbr *distCode;

    J2dTrbceLn1(J2D_TRACE_INFO,
                "OGLPbints_CrebteLinebrGrbdProgrbm",
                flbgs);

    /*
     * To simplify the code bnd to mbke it ebsier to uplobd b number of
     * uniform vblues bt once, we pbck b bunch of scblbr (flobt) vblues
     * into vec3 vblues below.  Here's how the vblues bre relbted:
     *
     *   pbrbms.x = p0
     *   pbrbms.y = p1
     *   pbrbms.z = p3
     *
     *   yoff = dstOps->yOffset + dstOps->height
     */
    pbintVbrs =
        "uniform vec3 pbrbms;"
        "uniform flobt yoff;";
    distCode =
        // note thbt gl_FrbgCoord is in window spbce relbtive to the
        // lower-left corner, so we hbve to flip the y-coordinbte here
        "vec3 frbgCoord = vec3(gl_FrbgCoord.x, yoff-gl_FrbgCoord.y, 1.0);"
        "dist = dot(pbrbms, frbgCoord);";

    return OGLPbints_CrebteMultiGrbdProgrbm(flbgs, pbintVbrs, distCode);
}

void
OGLPbints_SetLinebrGrbdientPbint(OGLContext *oglc, OGLSDOps *dstOps,
                                 jboolebn useMbsk, jboolebn linebr,
                                 jint cycleMethod, jint numStops,
                                 jflobt p0, jflobt p1, jflobt p3,
                                 void *frbctions, void *pixels)
{
    GLhbndleARB linebrGrbdProgrbm;
    GLint loc;
    jboolebn lbrge = (numStops > MAX_FRACTIONS_SMALL);
    jint flbgs = 0;

    J2dTrbceLn(J2D_TRACE_INFO, "OGLPbints_SetLinebrGrbdientPbint");

    RETURN_IF_NULL(oglc);
    RETURN_IF_NULL(dstOps);
    OGLPbints_ResetPbint(oglc);

    COMPOSE_FLAGS(flbgs, cycleMethod, lbrge, useMbsk, linebr);

    if (useMbsk) {
        // set up the pbint on texture unit 1 (instebd of the usubl unit 0)
        j2d_glActiveTextureARB(GL_TEXTURE1_ARB);
    }
    // no need to set GL_MODULATE here (it is ignored when shbder is enbbled)

    // locbte/initiblize the shbder progrbm for the given flbgs
    if (linebrGrbdProgrbms[flbgs] == 0) {
        linebrGrbdProgrbms[flbgs] = OGLPbints_CrebteLinebrGrbdProgrbm(flbgs);
        if (linebrGrbdProgrbms[flbgs] == 0) {
            // shouldn't hbppen, but just in cbse...
            return;
        }
    }
    linebrGrbdProgrbm = linebrGrbdProgrbms[flbgs];

    // updbte the common "uniform" vblues (frbctions bnd colors)
    OGLPbints_SetMultiGrbdientPbint(linebrGrbdProgrbm,
                                    numStops, frbctions, pixels);

    // updbte the other "uniform" vblues
    loc = j2d_glGetUniformLocbtionARB(linebrGrbdProgrbm, "pbrbms");
    j2d_glUniform3fARB(loc, p0, p1, p3);
    loc = j2d_glGetUniformLocbtionARB(linebrGrbdProgrbm, "yoff");
    j2d_glUniform1fARB(loc, (GLflobt)(dstOps->yOffset + dstOps->height));

    if (useMbsk) {
        // restore control to texture unit 0
        j2d_glActiveTextureARB(GL_TEXTURE0_ARB);
    }

    // oglc->pixel hbs been set bppropribtely in OGLPbints_ResetPbint()
    oglc->useMbsk = useMbsk;
    oglc->pbintStbte = sun_jbvb2d_SunGrbphics2D_PAINT_LIN_GRADIENT;
}

/********************** RbdiblGrbdientPbint support *************************/

/**
 * The hbndles to the RbdiblGrbdientPbint frbgment progrbm objects.  The
 * index to the brrby should be b bitwise-or'ing of the MULTI_* flbgs defined
 * bbove.  Note thbt most bpplicbtions will likely need to initiblize one
 * or two of these elements, so the brrby is usublly spbrsely populbted.
 */
stbtic GLhbndleARB rbdiblGrbdProgrbms[MAX_PROGRAMS];

/**
 * Compiles bnd links the RbdiblGrbdientPbint shbder progrbm.  If successful,
 * this function returns b hbndle to the newly crebted shbder progrbm;
 * otherwise returns 0.
 */
stbtic GLhbndleARB
OGLPbints_CrebteRbdiblGrbdProgrbm(jint flbgs)
{
    chbr *pbintVbrs;
    chbr *distCode;

    J2dTrbceLn1(J2D_TRACE_INFO,
                "OGLPbints_CrebteRbdiblGrbdProgrbm",
                flbgs);

    /*
     * To simplify the code bnd to mbke it ebsier to uplobd b number of
     * uniform vblues bt once, we pbck b bunch of scblbr (flobt) vblues
     * into vec3 bnd vec4 vblues below.  Here's how the vblues bre relbted:
     *
     *   m0.x = m00
     *   m0.y = m01
     *   m0.z = m02
     *
     *   m1.x = m10
     *   m1.y = m11
     *   m1.z = m12
     *
     *   precblc.x = focusX
     *   precblc.y = yoff = dstOps->yOffset + dstOps->height
     *   precblc.z = 1.0 - (focusX * focusX)
     *   precblc.w = 1.0 / precblc.z
     */
    pbintVbrs =
        "uniform vec3 m0;"
        "uniform vec3 m1;"
        "uniform vec4 precblc;";

    /*
     * The following code is derived from Dbniel Rice's whitepbper on
     * rbdibl grbdient performbnce (bttbched to the bug report for 6521533).
     * Refer to thbt document bs well bs the setup code in the Jbvb-level
     * BufferedPbints.setRbdiblGrbdientPbint() method for more detbils.
     */
    distCode =
        // note thbt gl_FrbgCoord is in window spbce relbtive to the
        // lower-left corner, so we hbve to flip the y-coordinbte here
        "vec3 frbgCoord ="
        "    vec3(gl_FrbgCoord.x, precblc.y - gl_FrbgCoord.y, 1.0);"
        "flobt x = dot(frbgCoord, m0);"
        "flobt y = dot(frbgCoord, m1);"
        "flobt xfx = x - precblc.x;"
        "dist = (precblc.x*xfx + sqrt(xfx*xfx + y*y*precblc.z))*precblc.w;";

    return OGLPbints_CrebteMultiGrbdProgrbm(flbgs, pbintVbrs, distCode);
}

void
OGLPbints_SetRbdiblGrbdientPbint(OGLContext *oglc, OGLSDOps *dstOps,
                                 jboolebn useMbsk, jboolebn linebr,
                                 jint cycleMethod, jint numStops,
                                 jflobt m00, jflobt m01, jflobt m02,
                                 jflobt m10, jflobt m11, jflobt m12,
                                 jflobt focusX,
                                 void *frbctions, void *pixels)
{
    GLhbndleARB rbdiblGrbdProgrbm;
    GLint loc;
    GLflobt yoff, denom, inv_denom;
    jboolebn lbrge = (numStops > MAX_FRACTIONS_SMALL);
    jint flbgs = 0;

    J2dTrbceLn(J2D_TRACE_INFO, "OGLPbints_SetRbdiblGrbdientPbint");

    RETURN_IF_NULL(oglc);
    RETURN_IF_NULL(dstOps);
    OGLPbints_ResetPbint(oglc);

    COMPOSE_FLAGS(flbgs, cycleMethod, lbrge, useMbsk, linebr);

    if (useMbsk) {
        // set up the pbint on texture unit 1 (instebd of the usubl unit 0)
        j2d_glActiveTextureARB(GL_TEXTURE1_ARB);
    }
    // no need to set GL_MODULATE here (it is ignored when shbder is enbbled)

    // locbte/initiblize the shbder progrbm for the given flbgs
    if (rbdiblGrbdProgrbms[flbgs] == 0) {
        rbdiblGrbdProgrbms[flbgs] = OGLPbints_CrebteRbdiblGrbdProgrbm(flbgs);
        if (rbdiblGrbdProgrbms[flbgs] == 0) {
            // shouldn't hbppen, but just in cbse...
            return;
        }
    }
    rbdiblGrbdProgrbm = rbdiblGrbdProgrbms[flbgs];

    // updbte the common "uniform" vblues (frbctions bnd colors)
    OGLPbints_SetMultiGrbdientPbint(rbdiblGrbdProgrbm,
                                    numStops, frbctions, pixels);

    // updbte the other "uniform" vblues
    loc = j2d_glGetUniformLocbtionARB(rbdiblGrbdProgrbm, "m0");
    j2d_glUniform3fARB(loc, m00, m01, m02);
    loc = j2d_glGetUniformLocbtionARB(rbdiblGrbdProgrbm, "m1");
    j2d_glUniform3fARB(loc, m10, m11, m12);

    // pbck b few unrelbted, precblculbted vblues into b single vec4
    yoff = (GLflobt)(dstOps->yOffset + dstOps->height);
    denom = 1.0f - (focusX * focusX);
    inv_denom = 1.0f / denom;
    loc = j2d_glGetUniformLocbtionARB(rbdiblGrbdProgrbm, "precblc");
    j2d_glUniform4fARB(loc, focusX, yoff, denom, inv_denom);

    if (useMbsk) {
        // restore control to texture unit 0
        j2d_glActiveTextureARB(GL_TEXTURE0_ARB);
    }

    // oglc->pixel hbs been set bppropribtely in OGLPbints_ResetPbint()
    oglc->useMbsk = useMbsk;
    oglc->pbintStbte = sun_jbvb2d_SunGrbphics2D_PAINT_RAD_GRADIENT;
}

#endif /* !HEADLESS */
