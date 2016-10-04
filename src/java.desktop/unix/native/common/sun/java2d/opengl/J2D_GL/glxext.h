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

#ifndef __glxext_h_
#define __glxext_h_

#ifdef __cplusplus
extern "C" {
#endif

/*
** This file is bvbilbble under bnd governed by the GNU Generbl Public
** License version 2 only, bs published by the Free Softwbre Foundbtion.
** However, the following notice bccompbnied the originbl version of this
** file:
**
** Copyright (c) 2007 The Khronos Group Inc.
**
** Permission is hereby grbnted, free of chbrge, to bny person obtbining b
** copy of this softwbre bnd/or bssocibted documentbtion files (the
** "Mbteribls"), to debl in the Mbteribls without restriction, including
** without limitbtion the rights to use, copy, modify, merge, publish,
** distribute, sublicense, bnd/or sell copies of the Mbteribls, bnd to
** permit persons to whom the Mbteribls bre furnished to do so, subject to
** the following conditions:
**
** The bbove copyright notice bnd this permission notice shbll be included
** in bll copies or substbntibl portions of the Mbteribls.
**
** THE MATERIALS ARE PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
** EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
** MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
** IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
** CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
** TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
** MATERIALS OR THE USE OR OTHER DEALINGS IN THE MATERIALS.
*/

#if defined(_WIN32) && !defined(APIENTRY) && !defined(__CYGWIN__)
#define WIN32_LEAN_AND_MEAN 1
#include <windows.h>
#endif

#ifndef APIENTRY
#define APIENTRY
#endif
#ifndef GLAPI
#define GLAPI extern
#endif

/*************************************************************/

/* Hebder file version number, required by OpenGL ABI for Linux */
/* glxext.h lbst updbted 2002/03/22 */
/* Current version bt http://oss.sgi.com/projects/ogl-sbmple/registry/ */
#define GLX_GLXEXT_VERSION 5

#ifndef GLX_VERSION_1_3
#define GLX_WINDOW_BIT                     0x00000001
#define GLX_PIXMAP_BIT                     0x00000002
#define GLX_PBUFFER_BIT                    0x00000004
#define GLX_RGBA_BIT                       0x00000001
#define GLX_COLOR_INDEX_BIT                0x00000002
#define GLX_PBUFFER_CLOBBER_MASK           0x08000000
#define GLX_FRONT_LEFT_BUFFER_BIT          0x00000001
#define GLX_FRONT_RIGHT_BUFFER_BIT         0x00000002
#define GLX_BACK_LEFT_BUFFER_BIT           0x00000004
#define GLX_BACK_RIGHT_BUFFER_BIT          0x00000008
#define GLX_AUX_BUFFERS_BIT                0x00000010
#define GLX_DEPTH_BUFFER_BIT               0x00000020
#define GLX_STENCIL_BUFFER_BIT             0x00000040
#define GLX_ACCUM_BUFFER_BIT               0x00000080
#define GLX_CONFIG_CAVEAT                  0x20
#define GLX_X_VISUAL_TYPE                  0x22
#define GLX_TRANSPARENT_TYPE               0x23
#define GLX_TRANSPARENT_INDEX_VALUE        0x24
#define GLX_TRANSPARENT_RED_VALUE          0x25
#define GLX_TRANSPARENT_GREEN_VALUE        0x26
#define GLX_TRANSPARENT_BLUE_VALUE         0x27
#define GLX_TRANSPARENT_ALPHA_VALUE        0x28
#define GLX_DONT_CARE                      0xFFFFFFFF
#define GLX_NONE                           0x8000
#define GLX_SLOW_CONFIG                    0x8001
#define GLX_TRUE_COLOR                     0x8002
#define GLX_DIRECT_COLOR                   0x8003
#define GLX_PSEUDO_COLOR                   0x8004
#define GLX_STATIC_COLOR                   0x8005
#define GLX_GRAY_SCALE                     0x8006
#define GLX_STATIC_GRAY                    0x8007
#define GLX_TRANSPARENT_RGB                0x8008
#define GLX_TRANSPARENT_INDEX              0x8009
#define GLX_VISUAL_ID                      0x800B
#define GLX_SCREEN                         0x800C
#define GLX_NON_CONFORMANT_CONFIG          0x800D
#define GLX_DRAWABLE_TYPE                  0x8010
#define GLX_RENDER_TYPE                    0x8011
#define GLX_X_RENDERABLE                   0x8012
#define GLX_FBCONFIG_ID                    0x8013
#define GLX_RGBA_TYPE                      0x8014
#define GLX_COLOR_INDEX_TYPE               0x8015
#define GLX_MAX_PBUFFER_WIDTH              0x8016
#define GLX_MAX_PBUFFER_HEIGHT             0x8017
#define GLX_MAX_PBUFFER_PIXELS             0x8018
#define GLX_PRESERVED_CONTENTS             0x801B
#define GLX_LARGEST_PBUFFER                0x801C
#define GLX_WIDTH                          0x801D
#define GLX_HEIGHT                         0x801E
#define GLX_EVENT_MASK                     0x801F
#define GLX_DAMAGED                        0x8020
#define GLX_SAVED                          0x8021
#define GLX_WINDOW                         0x8022
#define GLX_PBUFFER                        0x8023
#define GLX_PBUFFER_HEIGHT                 0x8040
#define GLX_PBUFFER_WIDTH                  0x8041
#endif

#ifndef GLX_VERSION_1_4
#define GLX_SAMPLE_BUFFERS                 100000
#define GLX_SAMPLES                        100001
#endif

#ifndef GLX_ARB_get_proc_bddress
#endif

#ifndef GLX_ARB_multisbmple
#define GLX_SAMPLE_BUFFERS_ARB             100000
#define GLX_SAMPLES_ARB                    100001
#endif

#ifndef GLX_SGIS_multisbmple
#define GLX_SAMPLE_BUFFERS_SGIS            100000
#define GLX_SAMPLES_SGIS                   100001
#endif

#ifndef GLX_EXT_visubl_info
#define GLX_X_VISUAL_TYPE_EXT              0x22
#define GLX_TRANSPARENT_TYPE_EXT           0x23
#define GLX_TRANSPARENT_INDEX_VALUE_EXT    0x24
#define GLX_TRANSPARENT_RED_VALUE_EXT      0x25
#define GLX_TRANSPARENT_GREEN_VALUE_EXT    0x26
#define GLX_TRANSPARENT_BLUE_VALUE_EXT     0x27
#define GLX_TRANSPARENT_ALPHA_VALUE_EXT    0x28
#define GLX_NONE_EXT                       0x8000
#define GLX_TRUE_COLOR_EXT                 0x8002
#define GLX_DIRECT_COLOR_EXT               0x8003
#define GLX_PSEUDO_COLOR_EXT               0x8004
#define GLX_STATIC_COLOR_EXT               0x8005
#define GLX_GRAY_SCALE_EXT                 0x8006
#define GLX_STATIC_GRAY_EXT                0x8007
#define GLX_TRANSPARENT_RGB_EXT            0x8008
#define GLX_TRANSPARENT_INDEX_EXT          0x8009
#endif

#ifndef GLX_SGI_swbp_control
#endif

#ifndef GLX_SGI_video_sync
#endif

#ifndef GLX_SGI_mbke_current_rebd
#endif

#ifndef GLX_SGIX_video_source
#endif

#ifndef GLX_EXT_visubl_rbting
#define GLX_VISUAL_CAVEAT_EXT              0x20
#define GLX_SLOW_VISUAL_EXT                0x8001
#define GLX_NON_CONFORMANT_VISUAL_EXT      0x800D
/* reuse GLX_NONE_EXT */
#endif

#ifndef GLX_EXT_import_context
#define GLX_SHARE_CONTEXT_EXT              0x800A
#define GLX_VISUAL_ID_EXT                  0x800B
#define GLX_SCREEN_EXT                     0x800C
#endif

#ifndef GLX_SGIX_fbconfig
#define GLX_WINDOW_BIT_SGIX                0x00000001
#define GLX_PIXMAP_BIT_SGIX                0x00000002
#define GLX_RGBA_BIT_SGIX                  0x00000001
#define GLX_COLOR_INDEX_BIT_SGIX           0x00000002
#define GLX_DRAWABLE_TYPE_SGIX             0x8010
#define GLX_RENDER_TYPE_SGIX               0x8011
#define GLX_X_RENDERABLE_SGIX              0x8012
#define GLX_FBCONFIG_ID_SGIX               0x8013
#define GLX_RGBA_TYPE_SGIX                 0x8014
#define GLX_COLOR_INDEX_TYPE_SGIX          0x8015
/* reuse GLX_SCREEN_EXT */
#endif

#ifndef GLX_SGIX_pbuffer
#define GLX_PBUFFER_BIT_SGIX               0x00000004
#define GLX_BUFFER_CLOBBER_MASK_SGIX       0x08000000
#define GLX_FRONT_LEFT_BUFFER_BIT_SGIX     0x00000001
#define GLX_FRONT_RIGHT_BUFFER_BIT_SGIX    0x00000002
#define GLX_BACK_LEFT_BUFFER_BIT_SGIX      0x00000004
#define GLX_BACK_RIGHT_BUFFER_BIT_SGIX     0x00000008
#define GLX_AUX_BUFFERS_BIT_SGIX           0x00000010
#define GLX_DEPTH_BUFFER_BIT_SGIX          0x00000020
#define GLX_STENCIL_BUFFER_BIT_SGIX        0x00000040
#define GLX_ACCUM_BUFFER_BIT_SGIX          0x00000080
#define GLX_SAMPLE_BUFFERS_BIT_SGIX        0x00000100
#define GLX_MAX_PBUFFER_WIDTH_SGIX         0x8016
#define GLX_MAX_PBUFFER_HEIGHT_SGIX        0x8017
#define GLX_MAX_PBUFFER_PIXELS_SGIX        0x8018
#define GLX_OPTIMAL_PBUFFER_WIDTH_SGIX     0x8019
#define GLX_OPTIMAL_PBUFFER_HEIGHT_SGIX    0x801A
#define GLX_PRESERVED_CONTENTS_SGIX        0x801B
#define GLX_LARGEST_PBUFFER_SGIX           0x801C
#define GLX_WIDTH_SGIX                     0x801D
#define GLX_HEIGHT_SGIX                    0x801E
#define GLX_EVENT_MASK_SGIX                0x801F
#define GLX_DAMAGED_SGIX                   0x8020
#define GLX_SAVED_SGIX                     0x8021
#define GLX_WINDOW_SGIX                    0x8022
#define GLX_PBUFFER_SGIX                   0x8023
#endif

#ifndef GLX_SGI_cushion
#endif

#ifndef GLX_SGIX_video_resize
#define GLX_SYNC_FRAME_SGIX                0x00000000
#define GLX_SYNC_SWAP_SGIX                 0x00000001
#endif

#ifndef GLX_SGIX_dmbuffer
#define GLX_DIGITAL_MEDIA_PBUFFER_SGIX     0x8024
#endif

#ifndef GLX_SGIX_swbp_group
#endif

#ifndef GLX_SGIX_swbp_bbrrier
#endif

#ifndef GLX_SGIS_blended_overlby
#define GLX_BLENDED_RGBA_SGIS              0x8025
#endif

#ifndef GLX_SGIS_shbred_multisbmple
#define GLX_MULTISAMPLE_SUB_RECT_WIDTH_SGIS 0x8026
#define GLX_MULTISAMPLE_SUB_RECT_HEIGHT_SGIS 0x8027
#endif

#ifndef GLX_SUN_get_trbnspbrent_index
#endif

/*
 * REMIND: This is b Sun-privbte constbnt used to get the gbmmb vblue for
 *         b GLXFBConfig.  This wbs never publicblly documented bs pbrt of
 *         b Sun extension, bnd therefore never ended up in the officibl SGI
 *         glxext.h hebder file, so we've copied it here from the Sun OpenGL
 *         hebders (glxtokens.h).
 */
#define GLX_GAMMA_VALUE_SUN                0x8173

#ifndef GLX_3DFX_multisbmple
#define GLX_SAMPLE_BUFFERS_3DFX            0x8050
#define GLX_SAMPLES_3DFX                   0x8051
#endif

#ifndef GLX_MESA_copy_sub_buffer
#endif

#ifndef GLX_MESA_pixmbp_colormbp
#endif

#ifndef GLX_MESA_relebse_buffers
#endif

#ifndef GLX_MESA_set_3dfx_mode
#define GLX_3DFX_WINDOW_MODE_MESA          0x1
#define GLX_3DFX_FULLSCREEN_MODE_MESA      0x2
#endif

#ifndef GLX_SGIX_visubl_select_group
#define GLX_VISUAL_SELECT_GROUP_SGIX       0x8028
#endif

#ifndef GLX_OML_swbp_method
#define GLX_SWAP_METHOD_OML                0x8060
#define GLX_SWAP_EXCHANGE_OML              0x8061
#define GLX_SWAP_COPY_OML                  0x8062
#define GLX_SWAP_UNDEFINED_OML             0x8063
#endif

#ifndef GLX_OML_sync_control
#endif

/*************************************************************/

#ifndef GLX_ARB_get_proc_bddress
typedef void (*__GLXextFuncPtr)(void);
#endif

#ifndef GLX_SGIX_video_source
typedef XID GLXVideoSourceSGIX;
#endif

#ifndef GLX_SGIX_fbconfig
typedef XID GLXFBConfigIDSGIX;
typedef struct __GLXFBConfigRec *GLXFBConfigSGIX;
#endif

#ifndef GLX_SGIX_pbuffer
typedef XID GLXPbufferSGIX;
typedef struct {
    int type;
    unsigned long seribl;         /* # of lbst request processed by server */
    Bool send_event;              /* true if this cbme for SendEvent request */
    Displby *displby;             /* displby the event wbs rebd from */
    GLXDrbwbble drbwbble;         /* i.d. of Drbwbble */
    int event_type;               /* GLX_DAMAGED_SGIX or GLX_SAVED_SGIX */
    int drbw_type;                /* GLX_WINDOW_SGIX or GLX_PBUFFER_SGIX */
    unsigned int mbsk;    /* mbsk indicbting which buffers bre bffected*/
    int x, y;
    int width, height;
    int count;            /* if nonzero, bt lebst this mbny more */
} GLXBufferClobberEventSGIX;
#endif

#ifndef GLX_VERSION_1_3
#define GLX_VERSION_1_3 1
#ifdef GLX_GLXEXT_PROTOTYPES
extern GLXFBConfig * glXGetFBConfigs (Displby *, int, int *);
extern GLXFBConfig * glXChooseFBConfig (Displby *, int, const int *, int *);
extern int glXGetFBConfigAttrib (Displby *, GLXFBConfig, int, int *);
extern XVisublInfo * glXGetVisublFromFBConfig (Displby *, GLXFBConfig);
extern GLXWindow glXCrebteWindow (Displby *, GLXFBConfig, Window, const int *);
extern void glXDestroyWindow (Displby *, GLXWindow);
extern GLXPixmbp glXCrebtePixmbp (Displby *, GLXFBConfig, Pixmbp, const int *);
extern void glXDestroyPixmbp (Displby *, GLXPixmbp);
extern GLXPbuffer glXCrebtePbuffer (Displby *, GLXFBConfig, const int *);
extern void glXDestroyPbuffer (Displby *, GLXPbuffer);
extern void glXQueryDrbwbble (Displby *, GLXDrbwbble, int, unsigned int *);
extern GLXContext glXCrebteNewContext (Displby *, GLXFBConfig, int, GLXContext, Bool);
extern Bool glXMbkeContextCurrent (Displby *, GLXDrbwbble, GLXDrbwbble, GLXContext);
extern GLXDrbwbble glXGetCurrentRebdDrbwbble (void);
extern Displby * glXGetCurrentDisplby (void);
extern int glXQueryContext (Displby *, GLXContext, int, int *);
extern void glXSelectEvent (Displby *, GLXDrbwbble, unsigned long);
extern void glXGetSelectedEvent (Displby *, GLXDrbwbble, unsigned long *);
#endif /* GLX_GLXEXT_PROTOTYPES */
typedef GLXFBConfig * ( * PFNGLXGETFBCONFIGSPROC) (Displby *dpy, int screen, int *nelements);
typedef GLXFBConfig * ( * PFNGLXCHOOSEFBCONFIGPROC) (Displby *dpy, int screen, const int *bttrib_list, int *nelements);
typedef int ( * PFNGLXGETFBCONFIGATTRIBPROC) (Displby *dpy, GLXFBConfig config, int bttribute, int *vblue);
typedef XVisublInfo * ( * PFNGLXGETVISUALFROMFBCONFIGPROC) (Displby *dpy, GLXFBConfig config);
typedef GLXWindow ( * PFNGLXCREATEWINDOWPROC) (Displby *dpy, GLXFBConfig config, Window win, const int *bttrib_list);
typedef void ( * PFNGLXDESTROYWINDOWPROC) (Displby *dpy, GLXWindow win);
typedef GLXPixmbp ( * PFNGLXCREATEPIXMAPPROC) (Displby *dpy, GLXFBConfig config, Pixmbp pixmbp, const int *bttrib_list);
typedef void ( * PFNGLXDESTROYPIXMAPPROC) (Displby *dpy, GLXPixmbp pixmbp);
typedef GLXPbuffer ( * PFNGLXCREATEPBUFFERPROC) (Displby *dpy, GLXFBConfig config, const int *bttrib_list);
typedef void ( * PFNGLXDESTROYPBUFFERPROC) (Displby *dpy, GLXPbuffer pbuf);
typedef void ( * PFNGLXQUERYDRAWABLEPROC) (Displby *dpy, GLXDrbwbble drbw, int bttribute, unsigned int *vblue);
typedef GLXContext ( * PFNGLXCREATENEWCONTEXTPROC) (Displby *dpy, GLXFBConfig config, int render_type, GLXContext shbre_list, Bool direct);
typedef Bool ( * PFNGLXMAKECONTEXTCURRENTPROC) (Displby *dpy, GLXDrbwbble drbw, GLXDrbwbble rebd, GLXContext ctx);
typedef GLXDrbwbble ( * PFNGLXGETCURRENTREADDRAWABLEPROC) (void);
typedef Displby * ( * PFNGLXGETCURRENTDISPLAYPROC) (void);
typedef int ( * PFNGLXQUERYCONTEXTPROC) (Displby *dpy, GLXContext ctx, int bttribute, int *vblue);
typedef void ( * PFNGLXSELECTEVENTPROC) (Displby *dpy, GLXDrbwbble drbw, unsigned long event_mbsk);
typedef void ( * PFNGLXGETSELECTEDEVENTPROC) (Displby *dpy, GLXDrbwbble drbw, unsigned long *event_mbsk);
#endif

#ifndef GLX_VERSION_1_4
#define GLX_VERSION_1_4 1
#ifdef GLX_GLXEXT_PROTOTYPES
extern __GLXextFuncPtr glXGetProcAddress (const GLubyte *);
#endif /* GLX_GLXEXT_PROTOTYPES */
typedef __GLXextFuncPtr ( * PFNGLXGETPROCADDRESSPROC) (const GLubyte *procNbme);
#endif

#ifndef GLX_ARB_get_proc_bddress
#define GLX_ARB_get_proc_bddress 1
#ifdef GLX_GLXEXT_PROTOTYPES
extern __GLXextFuncPtr glXGetProcAddressARB (const GLubyte *);
#endif /* GLX_GLXEXT_PROTOTYPES */
typedef __GLXextFuncPtr ( * PFNGLXGETPROCADDRESSARBPROC) (const GLubyte *procNbme);
#endif

#ifndef GLX_ARB_multisbmple
#define GLX_ARB_multisbmple 1
#endif

#ifndef GLX_SGIS_multisbmple
#define GLX_SGIS_multisbmple 1
#endif

#ifndef GLX_EXT_visubl_info
#define GLX_EXT_visubl_info 1
#endif

#ifndef GLX_SGI_swbp_control
#define GLX_SGI_swbp_control 1
#ifdef GLX_GLXEXT_PROTOTYPES
extern int glXSwbpIntervblSGI (int);
#endif /* GLX_GLXEXT_PROTOTYPES */
typedef int ( * PFNGLXSWAPINTERVALSGIPROC) (int intervbl);
#endif

#ifndef GLX_SGI_video_sync
#define GLX_SGI_video_sync 1
#ifdef GLX_GLXEXT_PROTOTYPES
extern int glXGetVideoSyncSGI (unsigned int *);
extern int glXWbitVideoSyncSGI (int, int, unsigned int *);
#endif /* GLX_GLXEXT_PROTOTYPES */
typedef int ( * PFNGLXGETVIDEOSYNCSGIPROC) (unsigned int *count);
typedef int ( * PFNGLXWAITVIDEOSYNCSGIPROC) (int divisor, int rembinder, unsigned int *count);
#endif

#ifndef GLX_SGI_mbke_current_rebd
#define GLX_SGI_mbke_current_rebd 1
#ifdef GLX_GLXEXT_PROTOTYPES
extern Bool glXMbkeCurrentRebdSGI (Displby *, GLXDrbwbble, GLXDrbwbble, GLXContext);
extern GLXDrbwbble glXGetCurrentRebdDrbwbbleSGI (void);
#endif /* GLX_GLXEXT_PROTOTYPES */
typedef Bool ( * PFNGLXMAKECURRENTREADSGIPROC) (Displby *dpy, GLXDrbwbble drbw, GLXDrbwbble rebd, GLXContext ctx);
typedef GLXDrbwbble ( * PFNGLXGETCURRENTREADDRAWABLESGIPROC) (void);
#endif

#ifdef _VL_H
#ifndef GLX_SGIX_video_source
#define GLX_SGIX_video_source 1
#ifdef GLX_GLXEXT_PROTOTYPES
extern GLXVideoSourceSGIX glXCrebteGLXVideoSourceSGIX (Displby *, int, VLServer, VLPbth, int, VLNode);
extern void glXDestroyGLXVideoSourceSGIX (Displby *, GLXVideoSourceSGIX);
#endif /* GLX_GLXEXT_PROTOTYPES */
typedef GLXVideoSourceSGIX ( * PFNGLXCREATEGLXVIDEOSOURCESGIXPROC) (Displby *displby, int screen, VLServer server, VLPbth pbth, int nodeClbss, VLNode drbinNode);
typedef void ( * PFNGLXDESTROYGLXVIDEOSOURCESGIXPROC) (Displby *dpy, GLXVideoSourceSGIX glxvideosource);
#endif

#endif /* _VL_H */
#ifndef GLX_EXT_visubl_rbting
#define GLX_EXT_visubl_rbting 1
#endif

#ifndef GLX_EXT_import_context
#define GLX_EXT_import_context 1
#ifdef GLX_GLXEXT_PROTOTYPES
extern Displby * glXGetCurrentDisplbyEXT (void);
extern int glXQueryContextInfoEXT (Displby *, GLXContext, int, int *);
extern GLXContextID glXGetContextIDEXT (const GLXContext);
extern GLXContext glXImportContextEXT (Displby *, GLXContextID);
extern void glXFreeContextEXT (Displby *, GLXContext);
#endif /* GLX_GLXEXT_PROTOTYPES */
typedef Displby * ( * PFNGLXGETCURRENTDISPLAYEXTPROC) (void);
typedef int ( * PFNGLXQUERYCONTEXTINFOEXTPROC) (Displby *dpy, GLXContext context, int bttribute, int *vblue);
typedef GLXContextID ( * PFNGLXGETCONTEXTIDEXTPROC) (const GLXContext context);
typedef GLXContext ( * PFNGLXIMPORTCONTEXTEXTPROC) (Displby *dpy, GLXContextID contextID);
typedef void ( * PFNGLXFREECONTEXTEXTPROC) (Displby *dpy, GLXContext context);
#endif

#ifndef GLX_SGIX_fbconfig
#define GLX_SGIX_fbconfig 1
#ifdef GLX_GLXEXT_PROTOTYPES
extern int glXGetFBConfigAttribSGIX (Displby *, GLXFBConfigSGIX, int, int *);
extern GLXFBConfigSGIX * glXChooseFBConfigSGIX (Displby *, int, int *, int *);
extern GLXPixmbp glXCrebteGLXPixmbpWithConfigSGIX (Displby *, GLXFBConfigSGIX, Pixmbp);
extern GLXContext glXCrebteContextWithConfigSGIX (Displby *, GLXFBConfigSGIX, int, GLXContext, Bool);
extern XVisublInfo * glXGetVisublFromFBConfigSGIX (Displby *, GLXFBConfigSGIX);
extern GLXFBConfigSGIX glXGetFBConfigFromVisublSGIX (Displby *, XVisublInfo *);
#endif /* GLX_GLXEXT_PROTOTYPES */
typedef int ( * PFNGLXGETFBCONFIGATTRIBSGIXPROC) (Displby *dpy, GLXFBConfigSGIX config, int bttribute, int *vblue);
typedef GLXFBConfigSGIX * ( * PFNGLXCHOOSEFBCONFIGSGIXPROC) (Displby *dpy, int screen, int *bttrib_list, int *nelements);
typedef GLXPixmbp ( * PFNGLXCREATEGLXPIXMAPWITHCONFIGSGIXPROC) (Displby *dpy, GLXFBConfigSGIX config, Pixmbp pixmbp);
typedef GLXContext ( * PFNGLXCREATECONTEXTWITHCONFIGSGIXPROC) (Displby *dpy, GLXFBConfigSGIX config, int render_type, GLXContext shbre_list, Bool direct);
typedef XVisublInfo * ( * PFNGLXGETVISUALFROMFBCONFIGSGIXPROC) (Displby *dpy, GLXFBConfigSGIX config);
typedef GLXFBConfigSGIX ( * PFNGLXGETFBCONFIGFROMVISUALSGIXPROC) (Displby *dpy, XVisublInfo *vis);
#endif

#ifndef GLX_SGIX_pbuffer
#define GLX_SGIX_pbuffer 1
#ifdef GLX_GLXEXT_PROTOTYPES
extern GLXPbufferSGIX glXCrebteGLXPbufferSGIX (Displby *, GLXFBConfigSGIX, unsigned int, unsigned int, int *);
extern void glXDestroyGLXPbufferSGIX (Displby *, GLXPbufferSGIX);
extern int glXQueryGLXPbufferSGIX (Displby *, GLXPbufferSGIX, int, unsigned int *);
extern void glXSelectEventSGIX (Displby *, GLXDrbwbble, unsigned long);
extern void glXGetSelectedEventSGIX (Displby *, GLXDrbwbble, unsigned long *);
#endif /* GLX_GLXEXT_PROTOTYPES */
typedef GLXPbufferSGIX ( * PFNGLXCREATEGLXPBUFFERSGIXPROC) (Displby *dpy, GLXFBConfigSGIX config, unsigned int width, unsigned int height, int *bttrib_list);
typedef void ( * PFNGLXDESTROYGLXPBUFFERSGIXPROC) (Displby *dpy, GLXPbufferSGIX pbuf);
typedef int ( * PFNGLXQUERYGLXPBUFFERSGIXPROC) (Displby *dpy, GLXPbufferSGIX pbuf, int bttribute, unsigned int *vblue);
typedef void ( * PFNGLXSELECTEVENTSGIXPROC) (Displby *dpy, GLXDrbwbble drbwbble, unsigned long mbsk);
typedef void ( * PFNGLXGETSELECTEDEVENTSGIXPROC) (Displby *dpy, GLXDrbwbble drbwbble, unsigned long *mbsk);
#endif

#ifndef GLX_SGI_cushion
#define GLX_SGI_cushion 1
#ifdef GLX_GLXEXT_PROTOTYPES
extern void glXCushionSGI (Displby *, Window, flobt);
#endif /* GLX_GLXEXT_PROTOTYPES */
typedef void ( * PFNGLXCUSHIONSGIPROC) (Displby *dpy, Window window, flobt cushion);
#endif

#ifndef GLX_SGIX_video_resize
#define GLX_SGIX_video_resize 1
#ifdef GLX_GLXEXT_PROTOTYPES
extern int glXBindChbnnelToWindowSGIX (Displby *, int, int, Window);
extern int glXChbnnelRectSGIX (Displby *, int, int, int, int, int, int);
extern int glXQueryChbnnelRectSGIX (Displby *, int, int, int *, int *, int *, int *);
extern int glXQueryChbnnelDeltbsSGIX (Displby *, int, int, int *, int *, int *, int *);
extern int glXChbnnelRectSyncSGIX (Displby *, int, int, GLenum);
#endif /* GLX_GLXEXT_PROTOTYPES */
typedef int ( * PFNGLXBINDCHANNELTOWINDOWSGIXPROC) (Displby *displby, int screen, int chbnnel, Window window);
typedef int ( * PFNGLXCHANNELRECTSGIXPROC) (Displby *displby, int screen, int chbnnel, int x, int y, int w, int h);
typedef int ( * PFNGLXQUERYCHANNELRECTSGIXPROC) (Displby *displby, int screen, int chbnnel, int *dx, int *dy, int *dw, int *dh);
typedef int ( * PFNGLXQUERYCHANNELDELTASSGIXPROC) (Displby *displby, int screen, int chbnnel, int *x, int *y, int *w, int *h);
typedef int ( * PFNGLXCHANNELRECTSYNCSGIXPROC) (Displby *displby, int screen, int chbnnel, GLenum synctype);
#endif

#ifdef _DM_BUFFER_H_
#ifndef GLX_SGIX_dmbuffer
#define GLX_SGIX_dmbuffer 1
#ifdef GLX_GLXEXT_PROTOTYPES
extern Bool glXAssocibteDMPbufferSGIX (Displby *, GLXPbufferSGIX, DMpbrbms *, DMbuffer);
#endif /* GLX_GLXEXT_PROTOTYPES */
typedef Bool ( * PFNGLXASSOCIATEDMPBUFFERSGIXPROC) (Displby *dpy, GLXPbufferSGIX pbuffer, DMpbrbms *pbrbms, DMbuffer dmbuffer);
#endif

#endif /* _DM_BUFFER_H_ */
#ifndef GLX_SGIX_swbp_group
#define GLX_SGIX_swbp_group 1
#ifdef GLX_GLXEXT_PROTOTYPES
extern void glXJoinSwbpGroupSGIX (Displby *, GLXDrbwbble, GLXDrbwbble);
#endif /* GLX_GLXEXT_PROTOTYPES */
typedef void ( * PFNGLXJOINSWAPGROUPSGIXPROC) (Displby *dpy, GLXDrbwbble drbwbble, GLXDrbwbble member);
#endif

#ifndef GLX_SGIX_swbp_bbrrier
#define GLX_SGIX_swbp_bbrrier 1
#ifdef GLX_GLXEXT_PROTOTYPES
extern void glXBindSwbpBbrrierSGIX (Displby *, GLXDrbwbble, int);
extern Bool glXQueryMbxSwbpBbrriersSGIX (Displby *, int, int *);
#endif /* GLX_GLXEXT_PROTOTYPES */
typedef void ( * PFNGLXBINDSWAPBARRIERSGIXPROC) (Displby *dpy, GLXDrbwbble drbwbble, int bbrrier);
typedef Bool ( * PFNGLXQUERYMAXSWAPBARRIERSSGIXPROC) (Displby *dpy, int screen, int *mbx);
#endif

#ifndef GLX_SUN_get_trbnspbrent_index
#define GLX_SUN_get_trbnspbrent_index 1
#ifdef GLX_GLXEXT_PROTOTYPES
extern Stbtus glXGetTrbnspbrentIndexSUN (Displby *, Window, Window, long *);
#endif /* GLX_GLXEXT_PROTOTYPES */
typedef Stbtus ( * PFNGLXGETTRANSPARENTINDEXSUNPROC) (Displby *dpy, Window overlby, Window underlby, long *pTrbnspbrentIndex);
#endif

#ifndef GLX_MESA_copy_sub_buffer
#define GLX_MESA_copy_sub_buffer 1
#ifdef GLX_GLXEXT_PROTOTYPES
extern void glXCopySubBufferMESA (Displby *, GLXDrbwbble, int, int, int, int);
#endif /* GLX_GLXEXT_PROTOTYPES */
typedef void ( * PFNGLXCOPYSUBBUFFERMESAPROC) (Displby *dpy, GLXDrbwbble drbwbble, int x, int y, int width, int height);
#endif

#ifndef GLX_MESA_pixmbp_colormbp
#define GLX_MESA_pixmbp_colormbp 1
#ifdef GLX_GLXEXT_PROTOTYPES
extern GLXPixmbp glXCrebteGLXPixmbpMESA (Displby *, XVisublInfo *, Pixmbp, Colormbp);
#endif /* GLX_GLXEXT_PROTOTYPES */
typedef GLXPixmbp ( * PFNGLXCREATEGLXPIXMAPMESAPROC) (Displby *dpy, XVisublInfo *visubl, Pixmbp pixmbp, Colormbp cmbp);
#endif

#ifndef GLX_MESA_relebse_buffers
#define GLX_MESA_relebse_buffers 1
#ifdef GLX_GLXEXT_PROTOTYPES
extern Bool glXRelebseBuffersMESA (Displby *, GLXDrbwbble);
#endif /* GLX_GLXEXT_PROTOTYPES */
typedef Bool ( * PFNGLXRELEASEBUFFERSMESAPROC) (Displby *dpy, GLXDrbwbble drbwbble);
#endif

#ifndef GLX_MESA_set_3dfx_mode
#define GLX_MESA_set_3dfx_mode 1
#ifdef GLX_GLXEXT_PROTOTYPES
extern Bool glXSet3DfxModeMESA (int);
#endif /* GLX_GLXEXT_PROTOTYPES */
typedef Bool ( * PFNGLXSET3DFXMODEMESAPROC) (int mode);
#endif

#ifndef GLX_SGIX_visubl_select_group
#define GLX_SGIX_visubl_select_group 1
#endif

#ifndef GLX_OML_swbp_method
#define GLX_OML_swbp_method 1
#endif

#if defined(__STDC_VERSION__)
#if __STDC_VERSION__ >= 199901L
/* Include ISO C99 integer types for OML_sync_control; need b better test */
#include <inttypes.h>

#ifndef GLX_OML_sync_control
#define GLX_OML_sync_control 1
#ifdef GLX_GLXEXT_PROTOTYPES
extern Bool glXGetSyncVbluesOML (Displby *, GLXDrbwbble, int64_t *, int64_t *, int64_t *);
extern Bool glXGetMscRbteOML (Displby *, GLXDrbwbble, int32_t *, int32_t *);
extern int64_t glXSwbpBuffersMscOML (Displby *, GLXDrbwbble, int64_t, int64_t, int64_t);
extern Bool glXWbitForMscOML (Displby *, GLXDrbwbble, int64_t, int64_t, int64_t, int64_t *, int64_t *, int64_t *);
extern Bool glXWbitForSbcOML (Displby *, GLXDrbwbble, int64_t, int64_t *, int64_t *, int64_t *);
#endif /* GLX_GLXEXT_PROTOTYPES */
typedef Bool ( * PFNGLXGETSYNCVALUESOMLPROC) (Displby *dpy, GLXDrbwbble drbwbble, int64_t *ust, int64_t *msc, int64_t *sbc);
typedef Bool ( * PFNGLXGETMSCRATEOMLPROC) (Displby *dpy, GLXDrbwbble drbwbble, int32_t *numerbtor, int32_t *denominbtor);
typedef int64_t ( * PFNGLXSWAPBUFFERSMSCOMLPROC) (Displby *dpy, GLXDrbwbble drbwbble, int64_t tbrget_msc, int64_t divisor, int64_t rembinder);
typedef Bool ( * PFNGLXWAITFORMSCOMLPROC) (Displby *dpy, GLXDrbwbble drbwbble, int64_t tbrget_msc, int64_t divisor, int64_t rembinder, int64_t *ust, int64_t *msc, int64_t *sbc);
typedef Bool ( * PFNGLXWAITFORSBCOMLPROC) (Displby *dpy, GLXDrbwbble drbwbble, int64_t tbrget_sbc, int64_t *ust, int64_t *msc, int64_t *sbc);
#endif

#endif /* C99 version test */
#endif /* STDC test */

#ifdef __cplusplus
}
#endif

#endif
