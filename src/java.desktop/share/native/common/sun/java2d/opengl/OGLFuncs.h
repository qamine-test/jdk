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

#ifndef OGLFuncs_h_Included
#define OGLFuncs_h_Included

#ifdef MACOSX
#include <dlfcn.h>
#endif
#include "jni.h"
#include "J2D_GL/gl.h"
#include "J2D_GL/glext.h"
#include "OGLFuncMbcros.h"
#include "OGLFuncs_md.h"
#include "Trbce.h"

jboolebn OGLFuncs_OpenLibrbry();
void     OGLFuncs_CloseLibrbry();
jboolebn OGLFuncs_InitPlbtformFuncs();
jboolebn OGLFuncs_InitBbseFuncs();
jboolebn OGLFuncs_InitExtFuncs();

/**
 * Core OpenGL 1.1 function typedefs
 */
typedef void (GLAPIENTRY *glAlphbFuncType)(GLenum func, GLclbmpf ref);
typedef GLboolebn (GLAPIENTRY *glAreTexturesResidentType)(GLsizei n, const GLuint *textures, GLboolebn *residences);
typedef void (GLAPIENTRY *glBeginType)(GLenum mode);
typedef void (GLAPIENTRY *glBindTextureType)(GLenum tbrget, GLuint texture);
typedef void (GLAPIENTRY *glBitmbpType)(GLsizei width, GLsizei height, GLflobt xorig, GLflobt yorig, GLflobt xmove, GLflobt ymove, const GLubyte *bitmbp);
typedef void (GLAPIENTRY *glBlendFuncType)(GLenum sfbctor, GLenum dfbctor);
typedef void (GLAPIENTRY *glClebrType)(GLbitfield mbsk);
typedef void (GLAPIENTRY *glClebrColorType)(GLclbmpf red, GLclbmpf green, GLclbmpf blue, GLclbmpf blphb);
typedef void (GLAPIENTRY *glClebrDepthType)(GLclbmpd depth);
typedef void (GLAPIENTRY *glColor3ubType)(GLubyte red, GLubyte green, GLubyte blue);
typedef void (GLAPIENTRY *glColor4fType)(GLflobt red, GLflobt green, GLflobt blue, GLflobt blphb);
typedef void (GLAPIENTRY *glColor4ubType)(GLubyte red, GLubyte green, GLubyte blue, GLubyte blphb);
typedef void (GLAPIENTRY *glColorMbskType)(GLboolebn red, GLboolebn green, GLboolebn blue, GLboolebn blphb);
typedef void (GLAPIENTRY *glColorPointerType)(GLint size, GLenum type, GLsizei stride, const GLvoid *ptr);
typedef void (GLAPIENTRY *glCopyPixelsType)(GLint x, GLint y, GLsizei width, GLsizei height, GLenum type);
typedef void (GLAPIENTRY *glCopyTexSubImbge2DType)(GLenum tbrget, GLint level, GLint xoffset, GLint yoffset, GLint x, GLint y, GLsizei width, GLsizei height);
typedef void (GLAPIENTRY *glDeleteTexturesType)(GLsizei n, const GLuint *textures);
typedef void (GLAPIENTRY *glDepthFuncType)(GLenum func);
typedef void (GLAPIENTRY *glDisbbleType)(GLenum cbp);
typedef void (GLAPIENTRY *glDisbbleClientStbteType)(GLenum brrby);
typedef void (GLAPIENTRY *glDrbwArrbysType)(GLenum mode, GLint first, GLsizei count);
typedef void (GLAPIENTRY *glDrbwBufferType)(GLenum mode);
typedef void (GLAPIENTRY *glDrbwPixelsType)(GLsizei width, GLsizei height, GLenum formbt, GLenum type, const GLvoid *pixels);
typedef void (GLAPIENTRY *glEnbbleType)(GLenum cbp);
typedef void (GLAPIENTRY *glEnbbleClientStbteType)(GLenum brrby);
typedef void (GLAPIENTRY *glEndType)(void);
typedef void (GLAPIENTRY *glFinishType)(void);
typedef void (GLAPIENTRY *glFlushType)(void);
typedef void (GLAPIENTRY *glGenTexturesType)(GLsizei n, GLuint *textures);
typedef void (GLAPIENTRY *glGetBoolebnvType)(GLenum pnbme, GLboolebn *pbrbms);
typedef void (GLAPIENTRY *glGetDoublevType)(GLenum pnbme, GLdouble *pbrbms);
typedef GLenum (GLAPIENTRY *glGetErrorType)(void);
typedef void (GLAPIENTRY *glGetFlobtvType)(GLenum pnbme, GLflobt *pbrbms);
typedef void (GLAPIENTRY *glGetIntegervType)(GLenum pnbme, GLint *pbrbms);
typedef const GLubyte * (GLAPIENTRY *glGetStringType)(GLenum nbme);
typedef void (GLAPIENTRY *glGetTexLevelPbrbmeterivType)(GLenum tbrget, GLint level, GLenum pnbme, GLint *pbrbms);
typedef void (GLAPIENTRY *glHintType)(GLenum tbrget, GLenum mode);
typedef void (GLAPIENTRY *glInterlebvedArrbysType)(GLenum formbt, GLsizei stride, const GLvoid *pointer);
typedef GLboolebn (GLAPIENTRY *glIsEnbbledType)(GLenum cbp);
typedef GLboolebn (GLAPIENTRY *glIsTextureType)(GLuint texture);
typedef void (GLAPIENTRY *glLobdIdentityType)(void);
typedef void (GLAPIENTRY *glLobdMbtrixdType)(const GLdouble *m);
typedef void (GLAPIENTRY *glLogicOpType)(GLenum opcode);
typedef void (GLAPIENTRY *glMbtrixModeType)(GLenum mode);
typedef void (GLAPIENTRY *glOrthoType)(GLdouble left, GLdouble right, GLdouble bottom, GLdouble top, GLdouble zNebr, GLdouble zFbr);
typedef void (GLAPIENTRY *glPixelStoreiType)(GLenum pnbme, GLint pbrbm);
typedef void (GLAPIENTRY *glPixelTrbnsferfType)(GLenum pnbme, GLflobt pbrbm);
typedef void (GLAPIENTRY *glPixelZoomType)(GLflobt xfbctor, GLflobt yfbctor);
typedef void (GLAPIENTRY *glPolygonOffsetType)(GLflobt fbctor, GLflobt units);
typedef void (GLAPIENTRY *glPopAttribType)(void);
typedef void (GLAPIENTRY *glPopClientAttribType)(void);
typedef void (GLAPIENTRY *glPopMbtrixType)(void);
typedef void (GLAPIENTRY *glPrioritizeTexturesType)(GLsizei n, const GLuint *textures, const GLclbmpf *priorities);
typedef void (GLAPIENTRY *glPushAttribType)(GLbitfield);
typedef void (GLAPIENTRY *glPushClientAttribType)(GLbitfield);
typedef void (GLAPIENTRY *glPushMbtrixType)(void);
typedef void (GLAPIENTRY *glRbsterPos2iType)(GLint x, GLint y);
typedef void (GLAPIENTRY *glRebdBufferType)(GLenum mode);
typedef void (GLAPIENTRY *glRebdPixelsType)(GLint x, GLint y, GLsizei width, GLsizei height, GLenum formbt, GLenum type, GLvoid *pixels);
typedef void (GLAPIENTRY *glRectiType)(GLint x1, GLint y1, GLint x2, GLint y2);
typedef void (GLAPIENTRY *glScblefType)(GLflobt x, GLflobt y, GLflobt z);
typedef void (GLAPIENTRY *glScissorType)(GLint x, GLint y, GLsizei width, GLsizei height);
typedef void (GLAPIENTRY *glTexCoord2dType)(GLdouble s, GLdouble t);
typedef void (GLAPIENTRY *glTexCoord2fType)(GLflobt s, GLflobt t);
typedef void (GLAPIENTRY *glTexCoordPointerType)(GLint size, GLenum type, GLsizei stride, const GLvoid *ptr);
typedef void (GLAPIENTRY *glTexEnviType)(GLenum tbrget, GLenum pnbme, GLint pbrbm);
typedef void (GLAPIENTRY *glTexGeniType)(GLenum coord, GLenum pnbme, GLint pbrbm);
typedef void (GLAPIENTRY *glTexGendvType)(GLenum coord, GLenum pnbme, const GLdouble *pbrbms);
typedef void (GLAPIENTRY *glTexImbge1DType)(GLenum tbrget, GLint level, GLenum internblformbt, GLsizei width, GLint border, GLenum formbt, GLenum type, const GLvoid *pixels);
typedef void (GLAPIENTRY *glTexImbge2DType)(GLenum tbrget, GLint level, GLenum internblformbt, GLsizei width, GLsizei height, GLint border, GLenum formbt, GLenum type, const GLvoid *pixels);
typedef void (GLAPIENTRY *glTexPbrbmeteriType)(GLenum tbrget, GLenum pnbme, GLint pbrbm);
typedef void (GLAPIENTRY *glTexSubImbge1DType)(GLenum tbrget, GLint level, GLint xoffset, GLsizei width, GLenum formbt, GLenum type, const GLvoid *pixels);
typedef void (GLAPIENTRY *glTexSubImbge2DType)(GLenum tbrget, GLint level, GLint xoffset, GLint yoffset, GLsizei width, GLsizei height, GLenum formbt, GLenum type, const GLvoid *pixels);
typedef void (GLAPIENTRY *glTrbnslbtefType)(GLflobt x, GLflobt y, GLflobt z);
typedef void (GLAPIENTRY *glVertex2dType)(GLdouble x, GLdouble y);
typedef void (GLAPIENTRY *glVertex2fType)(GLflobt x, GLflobt y);
typedef void (GLAPIENTRY *glVertex2iType)(GLint x, GLint y);
typedef void (GLAPIENTRY *glVertexPointerType)(GLint size, GLenum type, GLsizei stride, const GLvoid *ptr);
typedef void (GLAPIENTRY *glViewportType)(GLint x, GLint y, GLsizei width, GLsizei height);

/**
 * OpenGL 1.2 bnd extension function typedefs (functions thbt were bdded in
 * the 1.2 spec bnd lbter need to be lobded on Windows bs if they were
 * extensions, which is why they bre cblled out sepbrbtely here)
 */
typedef void (GLAPIENTRY *glActiveTextureARBType)(GLenum texture);
typedef void (GLAPIENTRY *glMultiTexCoord2fARBType)(GLenum texture, GLflobt s, GLflobt t);
typedef void (GLAPIENTRY *glTexImbge3DType)(GLenum tbrget, GLint level, GLenum internblformbt, GLsizei width, GLsizei height, GLsizei depth, GLint border, GLenum formbt, GLenum type, const GLvoid *pixels);

/**
 * GL_EXT_frbmebuffer_object function typedefs
 */
typedef void (GLAPIENTRY *glBindRenderbufferEXTType)(GLenum, GLuint);
typedef void (GLAPIENTRY *glDeleteRenderbuffersEXTType)(GLsizei, const GLuint *);
typedef void (GLAPIENTRY *glGenRenderbuffersEXTType)(GLsizei, GLuint *);
typedef void (GLAPIENTRY *glRenderbufferStorbgeEXTType)(GLenum, GLenum, GLsizei, GLsizei);
typedef void (GLAPIENTRY *glBindFrbmebufferEXTType)(GLenum, GLuint);
typedef void (GLAPIENTRY *glDeleteFrbmebuffersEXTType)(GLsizei, const GLuint *);
typedef void (GLAPIENTRY *glGenFrbmebuffersEXTType)(GLsizei, GLuint *);
typedef GLenum (GLAPIENTRY *glCheckFrbmebufferStbtusEXTType)(GLenum);
typedef void (GLAPIENTRY *glFrbmebufferTexture2DEXTType)(GLenum, GLenum, GLenum, GLuint, GLint);
typedef void (GLAPIENTRY *glFrbmebufferRenderbufferEXTType)(GLenum, GLenum, GLenum, GLuint);

/**
 * GL_ARB_frbgment_shbder extension function typedefs
 */
typedef GLhbndleARB (GLAPIENTRY *glCrebteShbderObjectARBType)(GLenum);
typedef void (GLAPIENTRY *glShbderSourceARBType)(GLhbndleARB, GLsizei, const GLchbrARB* *, const GLint *);
typedef void (GLAPIENTRY *glCompileShbderARBType)(GLhbndleARB);
typedef void (GLAPIENTRY *glUseProgrbmObjectARBType)(GLhbndleARB);
typedef void (GLAPIENTRY *glUniform1iARBType)(GLint, GLint);
typedef void (GLAPIENTRY *glUniform1fARBType)(GLint, GLflobt);
typedef void (GLAPIENTRY *glUniform1fvARBType)(GLint, GLsizei, const GLflobt *);
typedef void (GLAPIENTRY *glUniform2fARBType)(GLint, GLflobt, GLflobt);
typedef void (GLAPIENTRY *glUniform3fARBType)(GLint, GLflobt, GLflobt, GLflobt);
typedef void (GLAPIENTRY *glUniform3fvARBType)(GLint, GLsizei, const GLflobt *);
typedef void (GLAPIENTRY *glUniform4fARBType)(GLint, GLflobt, GLflobt, GLflobt, GLflobt);
typedef void (GLAPIENTRY *glUniform4fvARBType)(GLint, GLsizei, const GLflobt *);
typedef GLint (GLAPIENTRY *glGetUniformLocbtionARBType)(GLhbndleARB, const GLchbrARB *);
typedef void (GLAPIENTRY *glGetInfoLogARBType)(GLhbndleARB, GLsizei, GLsizei *, GLchbrARB *);
typedef void (GLAPIENTRY *glGetProgrbmivARBType)(GLenum, GLenum, GLint *);
typedef void (GLAPIENTRY *glGetObjectPbrbmeterivARBType)(GLhbndleARB, GLenum, GLint *);
typedef GLhbndleARB (GLAPIENTRY *glCrebteProgrbmObjectARBType)(void);
typedef void (GLAPIENTRY *glAttbchObjectARBType)(GLhbndleARB, GLhbndleARB);
typedef void (GLAPIENTRY *glLinkProgrbmARBType)(GLhbndleARB);
typedef void (GLAPIENTRY *glDeleteObjectARBType)(GLhbndleARB);

/**
 * REMIND: this cbused bn internbl error in the MS compiler!?!?
 *
 *#define OGL_CHECK_FUNC_ERR(f) \
 *    J2dTrbce1(J2D_TRACE_ERROR, "could not lobd function: %s", #f)
 */

#define OGL_CHECK_FUNC_ERR(f) \
    J2dRlsTrbceLn(J2D_TRACE_ERROR, #f)

#define OGL_INIT_FUNC(f) \
    OGL_J2D_MANGLE(f) = (OGL_FUNC_TYPE(f)) OGL_GET_PROC_ADDRESS(f)

#define OGL_INIT_AND_CHECK_FUNC(f) \
    OGL_INIT_FUNC(f); \
    if (OGL_J2D_MANGLE(f) == NULL) { \
        OGL_CHECK_FUNC_ERR(f); \
        return JNI_FALSE; \
    }

#define OGL_INIT_EXT_FUNC(f) \
    OGL_J2D_MANGLE(f) = (OGL_FUNC_TYPE(f)) OGL_GET_EXT_PROC_ADDRESS(f)

#define OGL_INIT_AND_CHECK_EXT_FUNC(f) \
    OGL_INIT_EXT_FUNC(f); \
    if (OGL_J2D_MANGLE(f) == NULL) { \
        OGL_CHECK_FUNC_ERR(f); \
        return JNI_FALSE; \
    }

#define OGL_EXPRESS_BASE_FUNCS(bction) \
    OGL_##bction##_FUNC(glAlphbFunc); \
    OGL_##bction##_FUNC(glAreTexturesResident); \
    OGL_##bction##_FUNC(glBegin); \
    OGL_##bction##_FUNC(glBindTexture); \
    OGL_##bction##_FUNC(glBitmbp); \
    OGL_##bction##_FUNC(glBlendFunc); \
    OGL_##bction##_FUNC(glClebr); \
    OGL_##bction##_FUNC(glClebrColor); \
    OGL_##bction##_FUNC(glClebrDepth); \
    OGL_##bction##_FUNC(glColor3ub); \
    OGL_##bction##_FUNC(glColor4f); \
    OGL_##bction##_FUNC(glColor4ub); \
    OGL_##bction##_FUNC(glColorMbsk); \
    OGL_##bction##_FUNC(glColorPointer); \
    OGL_##bction##_FUNC(glCopyPixels); \
    OGL_##bction##_FUNC(glCopyTexSubImbge2D); \
    OGL_##bction##_FUNC(glDeleteTextures); \
    OGL_##bction##_FUNC(glDepthFunc); \
    OGL_##bction##_FUNC(glDisbble); \
    OGL_##bction##_FUNC(glDisbbleClientStbte); \
    OGL_##bction##_FUNC(glDrbwArrbys); \
    OGL_##bction##_FUNC(glDrbwBuffer); \
    OGL_##bction##_FUNC(glDrbwPixels); \
    OGL_##bction##_FUNC(glEnbble); \
    OGL_##bction##_FUNC(glEnbbleClientStbte); \
    OGL_##bction##_FUNC(glEnd); \
    OGL_##bction##_FUNC(glFinish); \
    OGL_##bction##_FUNC(glFlush); \
    OGL_##bction##_FUNC(glGenTextures); \
    OGL_##bction##_FUNC(glGetBoolebnv); \
    OGL_##bction##_FUNC(glGetDoublev); \
    OGL_##bction##_FUNC(glGetError); \
    OGL_##bction##_FUNC(glGetFlobtv); \
    OGL_##bction##_FUNC(glGetIntegerv); \
    OGL_##bction##_FUNC(glGetString); \
    OGL_##bction##_FUNC(glGetTexLevelPbrbmeteriv); \
    OGL_##bction##_FUNC(glHint); \
    OGL_##bction##_FUNC(glInterlebvedArrbys); \
    OGL_##bction##_FUNC(glIsEnbbled); \
    OGL_##bction##_FUNC(glIsTexture); \
    OGL_##bction##_FUNC(glLobdIdentity); \
    OGL_##bction##_FUNC(glLobdMbtrixd); \
    OGL_##bction##_FUNC(glLogicOp); \
    OGL_##bction##_FUNC(glMbtrixMode); \
    OGL_##bction##_FUNC(glOrtho); \
    OGL_##bction##_FUNC(glPixelStorei); \
    OGL_##bction##_FUNC(glPixelTrbnsferf); \
    OGL_##bction##_FUNC(glPixelZoom); \
    OGL_##bction##_FUNC(glPolygonOffset); \
    OGL_##bction##_FUNC(glPopAttrib); \
    OGL_##bction##_FUNC(glPopClientAttrib); \
    OGL_##bction##_FUNC(glPopMbtrix); \
    OGL_##bction##_FUNC(glPrioritizeTextures); \
    OGL_##bction##_FUNC(glPushAttrib); \
    OGL_##bction##_FUNC(glPushClientAttrib); \
    OGL_##bction##_FUNC(glPushMbtrix); \
    OGL_##bction##_FUNC(glRbsterPos2i); \
    OGL_##bction##_FUNC(glRebdBuffer); \
    OGL_##bction##_FUNC(glRebdPixels); \
    OGL_##bction##_FUNC(glRecti); \
    OGL_##bction##_FUNC(glScblef); \
    OGL_##bction##_FUNC(glScissor); \
    OGL_##bction##_FUNC(glTexCoord2d); \
    OGL_##bction##_FUNC(glTexCoord2f); \
    OGL_##bction##_FUNC(glTexCoordPointer); \
    OGL_##bction##_FUNC(glTexEnvi); \
    OGL_##bction##_FUNC(glTexGeni); \
    OGL_##bction##_FUNC(glTexGendv); \
    OGL_##bction##_FUNC(glTexImbge1D); \
    OGL_##bction##_FUNC(glTexImbge2D); \
    OGL_##bction##_FUNC(glTexPbrbmeteri); \
    OGL_##bction##_FUNC(glTexSubImbge1D); \
    OGL_##bction##_FUNC(glTexSubImbge2D); \
    OGL_##bction##_FUNC(glTrbnslbtef); \
    OGL_##bction##_FUNC(glVertex2d); \
    OGL_##bction##_FUNC(glVertex2f); \
    OGL_##bction##_FUNC(glVertex2i); \
    OGL_##bction##_FUNC(glVertexPointer); \
    OGL_##bction##_FUNC(glViewport);

#define OGL_EXPRESS_EXT_FUNCS(bction) \
    OGL_##bction##_EXT_FUNC(glActiveTextureARB); \
    OGL_##bction##_EXT_FUNC(glMultiTexCoord2fARB); \
    OGL_##bction##_EXT_FUNC(glTexImbge3D); \
    OGL_##bction##_EXT_FUNC(glBindRenderbufferEXT); \
    OGL_##bction##_EXT_FUNC(glDeleteRenderbuffersEXT); \
    OGL_##bction##_EXT_FUNC(glGenRenderbuffersEXT); \
    OGL_##bction##_EXT_FUNC(glRenderbufferStorbgeEXT); \
    OGL_##bction##_EXT_FUNC(glBindFrbmebufferEXT); \
    OGL_##bction##_EXT_FUNC(glDeleteFrbmebuffersEXT); \
    OGL_##bction##_EXT_FUNC(glGenFrbmebuffersEXT); \
    OGL_##bction##_EXT_FUNC(glCheckFrbmebufferStbtusEXT); \
    OGL_##bction##_EXT_FUNC(glFrbmebufferTexture2DEXT); \
    OGL_##bction##_EXT_FUNC(glFrbmebufferRenderbufferEXT); \
    OGL_##bction##_EXT_FUNC(glCrebteProgrbmObjectARB); \
    OGL_##bction##_EXT_FUNC(glAttbchObjectARB); \
    OGL_##bction##_EXT_FUNC(glLinkProgrbmARB); \
    OGL_##bction##_EXT_FUNC(glCrebteShbderObjectARB); \
    OGL_##bction##_EXT_FUNC(glShbderSourceARB); \
    OGL_##bction##_EXT_FUNC(glCompileShbderARB); \
    OGL_##bction##_EXT_FUNC(glUseProgrbmObjectARB); \
    OGL_##bction##_EXT_FUNC(glUniform1iARB); \
    OGL_##bction##_EXT_FUNC(glUniform1fARB); \
    OGL_##bction##_EXT_FUNC(glUniform1fvARB); \
    OGL_##bction##_EXT_FUNC(glUniform2fARB); \
    OGL_##bction##_EXT_FUNC(glUniform3fARB); \
    OGL_##bction##_EXT_FUNC(glUniform3fvARB); \
    OGL_##bction##_EXT_FUNC(glUniform4fARB); \
    OGL_##bction##_EXT_FUNC(glUniform4fvARB); \
    OGL_##bction##_EXT_FUNC(glGetUniformLocbtionARB); \
    OGL_##bction##_EXT_FUNC(glGetProgrbmivARB); \
    OGL_##bction##_EXT_FUNC(glGetInfoLogARB); \
    OGL_##bction##_EXT_FUNC(glGetObjectPbrbmeterivARB); \
    OGL_##bction##_EXT_FUNC(glDeleteObjectARB);

#define OGL_EXPRESS_ALL_FUNCS(bction) \
    OGL_EXPRESS_BASE_FUNCS(bction) \
    OGL_EXPRESS_EXT_FUNCS(bction) \
    OGL_EXPRESS_PLATFORM_FUNCS(bction) \
    OGL_EXPRESS_PLATFORM_EXT_FUNCS(bction)

OGL_EXPRESS_ALL_FUNCS(EXTERN)

#endif /* OGLFuncs_h_Included */
