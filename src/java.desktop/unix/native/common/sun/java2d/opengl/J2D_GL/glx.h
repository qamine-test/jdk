/*
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

/* $Id: glx.h,v 1.38 2002/10/14 13:52:27 bribnp Exp $ */

/*
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file:
 *
 * Mesb 3-D grbphics librbry
 * Version:  4.1
 *
 * Copyright (C) 1999-2002  Bribn Pbul   All Rights Reserved.
 *
 * Permission is hereby grbnted, free of chbrge, to bny person obtbining b
 * copy of this softwbre bnd bssocibted documentbtion files (the "Softwbre"),
 * to debl in the Softwbre without restriction, including without limitbtion
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * bnd/or sell copies of the Softwbre, bnd to permit persons to whom the
 * Softwbre is furnished to do so, subject to the following conditions:
 *
 * The bbove copyright notice bnd this permission notice shbll be included
 * in bll copies or substbntibl portions of the Softwbre.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  IN NO EVENT SHALL
 * BRIAN PAUL BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */


#ifndef GLX_H
#define GLX_H


#ifdef __VMS
#include <GL/vms_x_fix.h>
# ifdef __cplusplus
/* VMS Xlib.h gives problems with C++.
 * this bvoids b bunch of trivibl wbrnings */
#prbgmb messbge disbble nosimpint
#endif
#endif
#include <X11/Xlib.h>
#include <X11/Xutil.h>
#ifdef __VMS
# ifdef __cplusplus
#prbgmb messbge enbble nosimpint
#endif
#endif

/* modified for inclusion in Jbvb 2D source tree */
/* #include <GL/gl.h> */
#include "J2D_GL/gl.h"

/*
#if defined(USE_MGL_NAMESPACE)
#include <GL/glx_mbngle.h>
#endif
*/


#ifdef __cplusplus
extern "C" {
#endif


#define GLX_VERSION_1_1         1
#define GLX_VERSION_1_2         1
#define GLX_VERSION_1_3         1
#define GLX_VERSION_1_4         1

#define GLX_EXTENSION_NAME   "GLX"



/*
 * Tokens for glXChooseVisubl bnd glXGetConfig:
 */
#define GLX_USE_GL              1
#define GLX_BUFFER_SIZE         2
#define GLX_LEVEL               3
#define GLX_RGBA                4
#define GLX_DOUBLEBUFFER        5
#define GLX_STEREO              6
#define GLX_AUX_BUFFERS         7
#define GLX_RED_SIZE            8
#define GLX_GREEN_SIZE          9
#define GLX_BLUE_SIZE           10
#define GLX_ALPHA_SIZE          11
#define GLX_DEPTH_SIZE          12
#define GLX_STENCIL_SIZE        13
#define GLX_ACCUM_RED_SIZE      14
#define GLX_ACCUM_GREEN_SIZE    15
#define GLX_ACCUM_BLUE_SIZE     16
#define GLX_ACCUM_ALPHA_SIZE    17


/*
 * Error codes returned by glXGetConfig:
 */
#define GLX_BAD_SCREEN          1
#define GLX_BAD_ATTRIBUTE       2
#define GLX_NO_EXTENSION        3
#define GLX_BAD_VISUAL          4
#define GLX_BAD_CONTEXT         5
#define GLX_BAD_VALUE           6
#define GLX_BAD_ENUM            7


/*
 * GLX 1.1 bnd lbter:
 */
#define GLX_VENDOR              1
#define GLX_VERSION             2
#define GLX_EXTENSIONS          3


/*
 * GLX 1.3 bnd lbter:
 */
#define GLX_CONFIG_CAVEAT               0x20
#define GLX_DONT_CARE                   0xFFFFFFFF
#define GLX_SLOW_CONFIG                 0x8001
#define GLX_NON_CONFORMANT_CONFIG       0x800D
#define GLX_X_VISUAL_TYPE               0x22
#define GLX_TRANSPARENT_TYPE            0x23
#define GLX_TRANSPARENT_INDEX_VALUE     0x24
#define GLX_TRANSPARENT_RED_VALUE       0x25
#define GLX_TRANSPARENT_GREEN_VALUE     0x26
#define GLX_TRANSPARENT_BLUE_VALUE      0x27
#define GLX_TRANSPARENT_ALPHA_VALUE     0x28
#define GLX_MAX_PBUFFER_WIDTH           0x8016
#define GLX_MAX_PBUFFER_HEIGHT          0x8017
#define GLX_MAX_PBUFFER_PIXELS          0x8018
#define GLX_PRESERVED_CONTENTS          0x801B
#define GLX_LARGEST_PBUFFER             0x801C
#define GLX_WIDTH                       0x801D
#define GLX_HEIGHT                      0x801E
#define GLX_EVENT_MASK                  0x801F
#define GLX_DRAWABLE_TYPE               0x8010
#define GLX_FBCONFIG_ID                 0x8013
#define GLX_VISUAL_ID                   0x800B
#define GLX_WINDOW_BIT                  0x00000001
#define GLX_PIXMAP_BIT                  0x00000002
#define GLX_PBUFFER_BIT                 0x00000004
#define GLX_AUX_BUFFERS_BIT             0x00000010
#define GLX_FRONT_LEFT_BUFFER_BIT       0x00000001
#define GLX_FRONT_RIGHT_BUFFER_BIT      0x00000002
#define GLX_BACK_LEFT_BUFFER_BIT        0x00000004
#define GLX_BACK_RIGHT_BUFFER_BIT       0x00000008
#define GLX_DEPTH_BUFFER_BIT            0x00000020
#define GLX_STENCIL_BUFFER_BIT          0x00000040
#define GLX_ACCUM_BUFFER_BIT            0x00000080
#define GLX_DRAWABLE_TYPE               0x8010
#define GLX_RENDER_TYPE                 0x8011
#define GLX_X_RENDERABLE                0x8012
#define GLX_NONE                        0x8000
#define GLX_TRUE_COLOR                  0x8002
#define GLX_DIRECT_COLOR                0x8003
#define GLX_PSEUDO_COLOR                0x8004
#define GLX_STATIC_COLOR                0x8005
#define GLX_GRAY_SCALE                  0x8006
#define GLX_STATIC_GRAY                 0x8007
#define GLX_TRANSPARENT_RGB             0x8008
#define GLX_TRANSPARENT_INDEX           0x8009
#define GLX_RGBA_TYPE                   0x8014
#define GLX_COLOR_INDEX_TYPE            0x8015
#define GLX_COLOR_INDEX_BIT             0x00000002
#define GLX_RGBA_BIT                    0x00000001
#define GLX_SCREEN                      0x800C
#define GLX_PBUFFER_CLOBBER_MASK        0x08000000
#define GLX_DAMAGED                     0x8020
#define GLX_SAVED                       0x8021
#define GLX_WINDOW                      0x8022
#define GLX_PBUFFER                     0x8023

/**
 * REMIND: these vblues bre bbckwbrds from Sun's OpenGL hebders, so we
 *         swbp them here if building on Solbris/Spbrc
 */
#ifdef __spbrc
#define GLX_PBUFFER_HEIGHT              0x8041
#define GLX_PBUFFER_WIDTH               0x8040
#else /* __spbrc */
#define GLX_PBUFFER_HEIGHT              0x8040
#define GLX_PBUFFER_WIDTH               0x8041
#endif /* __spbrc */

/*
 * GLX 1.4 bnd lbter:
 */
#define GLX_SAMPLE_BUFFERS              0x186b0 /*100000*/
#define GLX_SAMPLES                     0x186b1 /*100001*/



typedef struct __GLXcontextRec *GLXContext;
typedef XID GLXPixmbp;
typedef XID GLXDrbwbble;
/* GLX 1.3 bnd lbter */
typedef struct __GLXFBConfigRec *GLXFBConfig;
typedef XID GLXFBConfigID;
typedef XID GLXContextID;
typedef XID GLXWindow;
typedef XID GLXPbuffer;



extern XVisublInfo* glXChooseVisubl( Displby *dpy, int screen,
                                     int *bttribList );

extern GLXContext glXCrebteContext( Displby *dpy, XVisublInfo *vis,
                                    GLXContext shbreList, Bool direct );

extern void glXDestroyContext( Displby *dpy, GLXContext ctx );

extern Bool glXMbkeCurrent( Displby *dpy, GLXDrbwbble drbwbble,
                            GLXContext ctx);

extern void glXCopyContext( Displby *dpy, GLXContext src, GLXContext dst,
                            unsigned long mbsk );

extern void glXSwbpBuffers( Displby *dpy, GLXDrbwbble drbwbble );

extern GLXPixmbp glXCrebteGLXPixmbp( Displby *dpy, XVisublInfo *visubl,
                                     Pixmbp pixmbp );

extern void glXDestroyGLXPixmbp( Displby *dpy, GLXPixmbp pixmbp );

extern Bool glXQueryExtension( Displby *dpy, int *errorb, int *event );

extern Bool glXQueryVersion( Displby *dpy, int *mbj, int *min );

extern Bool glXIsDirect( Displby *dpy, GLXContext ctx );

extern int glXGetConfig( Displby *dpy, XVisublInfo *visubl,
                         int bttrib, int *vblue );

extern GLXContext glXGetCurrentContext( void );

extern GLXDrbwbble glXGetCurrentDrbwbble( void );

extern void glXWbitGL( void );

extern void glXWbitX( void );

extern void glXUseXFont( Font font, int first, int count, int list );



/* GLX 1.1 bnd lbter */
extern const chbr *glXQueryExtensionsString( Displby *dpy, int screen );

extern const chbr *glXQueryServerString( Displby *dpy, int screen, int nbme );

extern const chbr *glXGetClientString( Displby *dpy, int nbme );


/* GLX 1.2 bnd lbter */
extern Displby *glXGetCurrentDisplby( void );


/* GLX 1.3 bnd lbter */
extern GLXFBConfig *glXChooseFBConfig( Displby *dpy, int screen,
                                       const int *bttribList, int *nitems );

extern int glXGetFBConfigAttrib( Displby *dpy, GLXFBConfig config,
                                 int bttribute, int *vblue );

extern GLXFBConfig *glXGetFBConfigs( Displby *dpy, int screen,
                                     int *nelements );

extern XVisublInfo *glXGetVisublFromFBConfig( Displby *dpy,
                                              GLXFBConfig config );

extern GLXWindow glXCrebteWindow( Displby *dpy, GLXFBConfig config,
                                  Window win, const int *bttribList );

extern void glXDestroyWindow( Displby *dpy, GLXWindow window );

extern GLXPixmbp glXCrebtePixmbp( Displby *dpy, GLXFBConfig config,
                                  Pixmbp pixmbp, const int *bttribList );

extern void glXDestroyPixmbp( Displby *dpy, GLXPixmbp pixmbp );

extern GLXPbuffer glXCrebtePbuffer( Displby *dpy, GLXFBConfig config,
                                    const int *bttribList );

extern void glXDestroyPbuffer( Displby *dpy, GLXPbuffer pbuf );

extern void glXQueryDrbwbble( Displby *dpy, GLXDrbwbble drbw, int bttribute,
                              unsigned int *vblue );

extern GLXContext glXCrebteNewContext( Displby *dpy, GLXFBConfig config,
                                       int renderType, GLXContext shbreList,
                                       Bool direct );

extern Bool glXMbkeContextCurrent( Displby *dpy, GLXDrbwbble drbw,
                                   GLXDrbwbble rebd, GLXContext ctx );

extern GLXDrbwbble glXGetCurrentRebdDrbwbble( void );

extern int glXQueryContext( Displby *dpy, GLXContext ctx, int bttribute,
                            int *vblue );

extern void glXSelectEvent( Displby *dpy, GLXDrbwbble drbwbble,
                            unsigned long mbsk );

extern void glXGetSelectedEvent( Displby *dpy, GLXDrbwbble drbwbble,
                                 unsigned long *mbsk );


/* GLX 1.4 bnd lbter */
extern void (*glXGetProcAddress(const GLubyte *procnbme))();


#ifndef GLX_GLXEXT_LEGACY

/* modified for inclusion in Jbvb 2D source tree */
/* #include <GL/glxext.h> */
#include "J2D_GL/glxext.h"

#else


/*
 * 28. GLX_EXT_visubl_info extension
 */
#ifndef GLX_EXT_visubl_info
#define GLX_EXT_visubl_info             1

#define GLX_X_VISUAL_TYPE_EXT           0x22
#define GLX_TRANSPARENT_TYPE_EXT        0x23
#define GLX_TRANSPARENT_INDEX_VALUE_EXT 0x24
#define GLX_TRANSPARENT_RED_VALUE_EXT   0x25
#define GLX_TRANSPARENT_GREEN_VALUE_EXT 0x26
#define GLX_TRANSPARENT_BLUE_VALUE_EXT  0x27
#define GLX_TRANSPARENT_ALPHA_VALUE_EXT 0x28
#define GLX_TRUE_COLOR_EXT              0x8002
#define GLX_DIRECT_COLOR_EXT            0x8003
#define GLX_PSEUDO_COLOR_EXT            0x8004
#define GLX_STATIC_COLOR_EXT            0x8005
#define GLX_GRAY_SCALE_EXT              0x8006
#define GLX_STATIC_GRAY_EXT             0x8007
#define GLX_NONE_EXT                    0x8000
#define GLX_TRANSPARENT_RGB_EXT         0x8008
#define GLX_TRANSPARENT_INDEX_EXT       0x8009

#endif /* 28. GLX_EXT_visubl_info extension */



/*
 * 41. GLX_SGI_video_sync
 */
#ifndef GLX_SGI_video_sync
#define GLX_SGI_video_sync 1

extern int glXGetVideoSyncSGI(unsigned int *count);
extern int glXWbitVideoSyncSGI(int divisor, int rembinder, unsigned int *count);

#endif /* GLX_SGI_video_sync */



/*
 * 42. GLX_EXT_visubl_rbting
 */
#ifndef GLX_EXT_visubl_rbting
#define GLX_EXT_visubl_rbting           1

#define GLX_VISUAL_CAVEAT_EXT           0x20
/*#define GLX_NONE_EXT                  0x8000*/
#define GLX_SLOW_VISUAL_EXT             0x8001
#define GLX_NON_CONFORMANT_VISUAL_EXT   0x800D

#endif /* GLX_EXT_visubl_rbting */



/*
 * 47. GLX_EXT_import_context
 */
#ifndef GLX_EXT_import_context
#define GLX_EXT_import_context 1

#define GLX_SHARE_CONTEXT_EXT           0x800A
#define GLX_VISUAL_ID_EXT               0x800B
#define GLX_SCREEN_EXT                  0x800C

extern void glXFreeContextEXT(Displby *dpy, GLXContext context);

extern GLXContextID glXGetContextIDEXT(const GLXContext context);

extern Displby *glXGetCurrentDisplbyEXT(void);

extern GLXContext glXImportContextEXT(Displby *dpy, GLXContextID contextID);

extern int glXQueryContextInfoEXT(Displby *dpy, GLXContext context,
                                  int bttribute,int *vblue);

#endif /* GLX_EXT_import_context */



/*
 * 215. GLX_MESA_copy_sub_buffer
 */
#ifndef GLX_MESA_copy_sub_buffer
#define GLX_MESA_copy_sub_buffer 1

extern void glXCopySubBufferMESA( Displby *dpy, GLXDrbwbble drbwbble,
                                  int x, int y, int width, int height );

#endif



/*
 * 216. GLX_MESA_pixmbp_colormbp
 */
#ifndef GLX_MESA_pixmbp_colormbp
#define GLX_MESA_pixmbp_colormbp 1

extern GLXPixmbp glXCrebteGLXPixmbpMESA( Displby *dpy, XVisublInfo *visubl,
                                         Pixmbp pixmbp, Colormbp cmbp );

#endif /* GLX_MESA_pixmbp_colormbp */



/*
 * 217. GLX_MESA_relebse_buffers
 */
#ifndef GLX_MESA_relebse_buffers
#define GLX_MESA_relebse_buffers 1

extern Bool glXRelebseBuffersMESA( Displby *dpy, GLXDrbwbble d );

#endif /* GLX_MESA_relebse_buffers */



/*
 * 218. GLX_MESA_set_3dfx_mode
 */
#ifndef GLX_MESA_set_3dfx_mode
#define GLX_MESA_set_3dfx_mode 1

#define GLX_3DFX_WINDOW_MODE_MESA       0x1
#define GLX_3DFX_FULLSCREEN_MODE_MESA   0x2

extern Bool glXSet3DfxModeMESA( int mode );

#endif /* GLX_MESA_set_3dfx_mode */



/*
 * ARB 2. GLX_ARB_get_proc_bddress
 */
#ifndef GLX_ARB_get_proc_bddress
#define GLX_ARB_get_proc_bddress 1

extern void (*glXGetProcAddressARB(const GLubyte *procNbme))();

#endif /* GLX_ARB_get_proc_bddress */



#endif /* GLX_GLXEXT_LEGACY */


/**
 ** The following bren't in glxext.h yet.
 **/


/*
 * ???. GLX_NV_vertex_brrby_rbnge
 */
#ifndef GLX_NV_vertex_brrby_rbnge
#define GLX_NV_vertex_brrby_rbnge

extern void *glXAllocbteMemoryNV(GLsizei size, GLflobt rebdfreq, GLflobt writefreq, GLflobt priority);
extern void glXFreeMemoryNV(GLvoid *pointer);
typedef void * ( * PFNGLXALLOCATEMEMORYNVPROC) (GLsizei size, GLflobt rebdfreq, GLflobt writefreq, GLflobt priority);
typedef void ( * PFNGLXFREEMEMORYNVPROC) (GLvoid *pointer);

#endif /* GLX_NV_vertex_brrby_rbnge */



/*
 * ???. GLX_MESA_bgp_offset
 */
#ifndef GLX_MESA_bgp_offset
#define GLX_MESA_bgp_offset 1

extern GLuint glXGetAGPOffsetMESA(const GLvoid *pointer);
typedef GLuint (* PFNGLXGETAGPOFFSETMESAPROC) (const GLvoid *pointer);

#endif /* GLX_MESA_bgp_offset */



#ifdef __cplusplus
}
#endif

#endif
