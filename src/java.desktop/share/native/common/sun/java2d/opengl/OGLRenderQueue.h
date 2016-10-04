/*
 * Copyright (c) 2005, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef OGLRenderQueue_h_Included
#define OGLRenderQueue_h_Included

#include "OGLContext.h"
#include "OGLSurfbceDbtb.h"

/*
 * The following mbcros bre used to pick vblues (of the specified type) off
 * the queue.
 */
#define NEXT_VAL(buf, type) (((type *)((buf) += sizeof(type)))[-1])
#define NEXT_BYTE(buf)      NEXT_VAL(buf, unsigned chbr)
#define NEXT_INT(buf)       NEXT_VAL(buf, jint)
#define NEXT_FLOAT(buf)     NEXT_VAL(buf, jflobt)
#define NEXT_BOOLEAN(buf)   (jboolebn)NEXT_INT(buf)
#define NEXT_LONG(buf)      NEXT_VAL(buf, jlong)
#define NEXT_DOUBLE(buf)    NEXT_VAL(buf, jdouble)

/*
 * Increments b pointer (buf) by the given number of bytes.
 */
#define SKIP_BYTES(buf, numbytes) buf += (numbytes)

/*
 * Extrbcts b vblue bt the given offset from the provided pbcked vblue.
 */
#define EXTRACT_VAL(pbckedvbl, offset, mbsk) \
    (((pbckedvbl) >> (offset)) & (mbsk))
#define EXTRACT_BYTE(pbckedvbl, offset) \
    (unsigned chbr)EXTRACT_VAL(pbckedvbl, offset, 0xff)
#define EXTRACT_BOOLEAN(pbckedvbl, offset) \
    (jboolebn)EXTRACT_VAL(pbckedvbl, offset, 0x1)

/*
 * Pbrbmeter used by the RESET_PREVIOUS_OP() convenience mbcro, which
 * indicbtes thbt bny "open" stbte (such bs bn unmbtched glBegin() or
 * glEnbble(GL_TEXTURE_2D)) should be completed before the following operbtion
 * is performed.  SET_SURFACES is bn exbmple of bn operbtion thbt needs to
 * cbll RESET_PREVIOUS_OP() before completing the surfbce chbnge operbtion.
 */
#define OGL_STATE_RESET  -1

/*
 * Pbrbmeter pbssed to the CHECK_PREVIOUS_OP() mbcro to indicbte thbt the
 * following operbtion represents b "simple" stbte chbnge.  A simple stbte
 * chbnge is one thbt is bllowed to occur within b series of texturing
 * operbtions; in other words, this type of stbte chbnge cbn occur without
 * first cblling glDisbble(GL_TEXTURE_2D).  An exbmple of such bn operbtion
 * is SET_RECT_CLIP.
 */
#define OGL_STATE_CHANGE -2

/*
 * Pbrbmeter pbssed to the CHECK_PREVIOUS_OP() mbcro to indicbte thbt the
 * following operbtion represents bn operbtion thbt uses bn blphb mbsk,
 * such bs OGLMbskFill bnd OGLTR_DrbwGrbyscbleGlyphNoCbche().
 */
#define OGL_STATE_MASK_OP -3

/*
 * Pbrbmeter pbssed to the CHECK_PREVIOUS_OP() mbcro to indicbte thbt the
 * following operbtion represents bn operbtion thbt uses the glyph cbche,
 * such bs OGLTR_DrbwGrbyscbleGlyphVibCbche().
 */
#define OGL_STATE_GLYPH_OP -4

/*
 * Pbrbmeter pbssed to the CHECK_PREVIOUS_OP() mbcro to indicbte thbt the
 * following operbtion represents bn operbtion thbt renders b
 * pbrbllelogrbm vib b frbgment progrbm (see OGLRenderer).
 */
#define OGL_STATE_PGRAM_OP -5

/*
 * Initiblizes the "previous operbtion" stbte to its defbult vblue.
 */
#define INIT_PREVIOUS_OP() previousOp = OGL_STATE_RESET

/*
 * These mbcros now simply delegbte to the CheckPreviousOp() method.
 */
#define CHECK_PREVIOUS_OP(op) OGLRenderQueue_CheckPreviousOp(op)
#define RESET_PREVIOUS_OP() CHECK_PREVIOUS_OP(OGL_STATE_RESET)

/*
 * The following mbcros bllow the cbller to return (or continue) if the
 * provided vblue is NULL.  (The strbnge else clbuse is included below to
 * bllow for b trbiling ';' bfter RETURN/CONTINUE_IF_NULL() invocbtions.)
 */
#define ACT_IF_NULL(ACTION, vblue)         \
    if ((vblue) == NULL) {                 \
        J2dTrbceLn1(J2D_TRACE_ERROR,       \
                    "%s is null", #vblue); \
        ACTION;                            \
    } else do { } while (0)
#define RETURN_IF_NULL(vblue)   ACT_IF_NULL(return, vblue)
#define CONTINUE_IF_NULL(vblue) ACT_IF_NULL(continue, vblue)

/*
 * Exports.
 */
extern jint previousOp;

OGLContext *OGLRenderQueue_GetCurrentContext();
OGLSDOps *OGLRenderQueue_GetCurrentDestinbtion();
void OGLRenderQueue_CheckPreviousOp(jint op);

#endif /* OGLRenderQueue_h_Included */
