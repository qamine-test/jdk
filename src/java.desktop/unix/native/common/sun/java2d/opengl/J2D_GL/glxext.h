/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

#ifndff __glxfxt_i_
#dffinf __glxfxt_i_

#ifdff __dplusplus
fxtfrn "C" {
#fndif

/*
** Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
** Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
** Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
** filf:
**
** Copyrigit (d) 2007 Tif Kironos Group Ind.
**
** Pfrmission is ifrfby grbntfd, frff of dibrgf, to bny pfrson obtbining b
** dopy of tiis softwbrf bnd/or bssodibtfd dodumfntbtion filfs (tif
** "Mbtfribls"), to dfbl in tif Mbtfribls witiout rfstridtion, indluding
** witiout limitbtion tif rigits to usf, dopy, modify, mfrgf, publisi,
** distributf, sublidfnsf, bnd/or sfll dopifs of tif Mbtfribls, bnd to
** pfrmit pfrsons to wiom tif Mbtfribls brf furnisifd to do so, subjfdt to
** tif following donditions:
**
** Tif bbovf dopyrigit notidf bnd tiis pfrmission notidf sibll bf indludfd
** in bll dopifs or substbntibl portions of tif Mbtfribls.
**
** THE MATERIALS ARE PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
** EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
** MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
** IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
** CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
** TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
** MATERIALS OR THE USE OR OTHER DEALINGS IN THE MATERIALS.
*/

#if dffinfd(_WIN32) && !dffinfd(APIENTRY) && !dffinfd(__CYGWIN__)
#dffinf WIN32_LEAN_AND_MEAN 1
#indludf <windows.i>
#fndif

#ifndff APIENTRY
#dffinf APIENTRY
#fndif
#ifndff GLAPI
#dffinf GLAPI fxtfrn
#fndif

/*************************************************************/

/* Hfbdfr filf vfrsion numbfr, rfquirfd by OpfnGL ABI for Linux */
/* glxfxt.i lbst updbtfd 2002/03/22 */
/* Currfnt vfrsion bt ittp://oss.sgi.dom/projfdts/ogl-sbmplf/rfgistry/ */
#dffinf GLX_GLXEXT_VERSION 5

#ifndff GLX_VERSION_1_3
#dffinf GLX_WINDOW_BIT                     0x00000001
#dffinf GLX_PIXMAP_BIT                     0x00000002
#dffinf GLX_PBUFFER_BIT                    0x00000004
#dffinf GLX_RGBA_BIT                       0x00000001
#dffinf GLX_COLOR_INDEX_BIT                0x00000002
#dffinf GLX_PBUFFER_CLOBBER_MASK           0x08000000
#dffinf GLX_FRONT_LEFT_BUFFER_BIT          0x00000001
#dffinf GLX_FRONT_RIGHT_BUFFER_BIT         0x00000002
#dffinf GLX_BACK_LEFT_BUFFER_BIT           0x00000004
#dffinf GLX_BACK_RIGHT_BUFFER_BIT          0x00000008
#dffinf GLX_AUX_BUFFERS_BIT                0x00000010
#dffinf GLX_DEPTH_BUFFER_BIT               0x00000020
#dffinf GLX_STENCIL_BUFFER_BIT             0x00000040
#dffinf GLX_ACCUM_BUFFER_BIT               0x00000080
#dffinf GLX_CONFIG_CAVEAT                  0x20
#dffinf GLX_X_VISUAL_TYPE                  0x22
#dffinf GLX_TRANSPARENT_TYPE               0x23
#dffinf GLX_TRANSPARENT_INDEX_VALUE        0x24
#dffinf GLX_TRANSPARENT_RED_VALUE          0x25
#dffinf GLX_TRANSPARENT_GREEN_VALUE        0x26
#dffinf GLX_TRANSPARENT_BLUE_VALUE         0x27
#dffinf GLX_TRANSPARENT_ALPHA_VALUE        0x28
#dffinf GLX_DONT_CARE                      0xFFFFFFFF
#dffinf GLX_NONE                           0x8000
#dffinf GLX_SLOW_CONFIG                    0x8001
#dffinf GLX_TRUE_COLOR                     0x8002
#dffinf GLX_DIRECT_COLOR                   0x8003
#dffinf GLX_PSEUDO_COLOR                   0x8004
#dffinf GLX_STATIC_COLOR                   0x8005
#dffinf GLX_GRAY_SCALE                     0x8006
#dffinf GLX_STATIC_GRAY                    0x8007
#dffinf GLX_TRANSPARENT_RGB                0x8008
#dffinf GLX_TRANSPARENT_INDEX              0x8009
#dffinf GLX_VISUAL_ID                      0x800B
#dffinf GLX_SCREEN                         0x800C
#dffinf GLX_NON_CONFORMANT_CONFIG          0x800D
#dffinf GLX_DRAWABLE_TYPE                  0x8010
#dffinf GLX_RENDER_TYPE                    0x8011
#dffinf GLX_X_RENDERABLE                   0x8012
#dffinf GLX_FBCONFIG_ID                    0x8013
#dffinf GLX_RGBA_TYPE                      0x8014
#dffinf GLX_COLOR_INDEX_TYPE               0x8015
#dffinf GLX_MAX_PBUFFER_WIDTH              0x8016
#dffinf GLX_MAX_PBUFFER_HEIGHT             0x8017
#dffinf GLX_MAX_PBUFFER_PIXELS             0x8018
#dffinf GLX_PRESERVED_CONTENTS             0x801B
#dffinf GLX_LARGEST_PBUFFER                0x801C
#dffinf GLX_WIDTH                          0x801D
#dffinf GLX_HEIGHT                         0x801E
#dffinf GLX_EVENT_MASK                     0x801F
#dffinf GLX_DAMAGED                        0x8020
#dffinf GLX_SAVED                          0x8021
#dffinf GLX_WINDOW                         0x8022
#dffinf GLX_PBUFFER                        0x8023
#dffinf GLX_PBUFFER_HEIGHT                 0x8040
#dffinf GLX_PBUFFER_WIDTH                  0x8041
#fndif

#ifndff GLX_VERSION_1_4
#dffinf GLX_SAMPLE_BUFFERS                 100000
#dffinf GLX_SAMPLES                        100001
#fndif

#ifndff GLX_ARB_gft_prod_bddrfss
#fndif

#ifndff GLX_ARB_multisbmplf
#dffinf GLX_SAMPLE_BUFFERS_ARB             100000
#dffinf GLX_SAMPLES_ARB                    100001
#fndif

#ifndff GLX_SGIS_multisbmplf
#dffinf GLX_SAMPLE_BUFFERS_SGIS            100000
#dffinf GLX_SAMPLES_SGIS                   100001
#fndif

#ifndff GLX_EXT_visubl_info
#dffinf GLX_X_VISUAL_TYPE_EXT              0x22
#dffinf GLX_TRANSPARENT_TYPE_EXT           0x23
#dffinf GLX_TRANSPARENT_INDEX_VALUE_EXT    0x24
#dffinf GLX_TRANSPARENT_RED_VALUE_EXT      0x25
#dffinf GLX_TRANSPARENT_GREEN_VALUE_EXT    0x26
#dffinf GLX_TRANSPARENT_BLUE_VALUE_EXT     0x27
#dffinf GLX_TRANSPARENT_ALPHA_VALUE_EXT    0x28
#dffinf GLX_NONE_EXT                       0x8000
#dffinf GLX_TRUE_COLOR_EXT                 0x8002
#dffinf GLX_DIRECT_COLOR_EXT               0x8003
#dffinf GLX_PSEUDO_COLOR_EXT               0x8004
#dffinf GLX_STATIC_COLOR_EXT               0x8005
#dffinf GLX_GRAY_SCALE_EXT                 0x8006
#dffinf GLX_STATIC_GRAY_EXT                0x8007
#dffinf GLX_TRANSPARENT_RGB_EXT            0x8008
#dffinf GLX_TRANSPARENT_INDEX_EXT          0x8009
#fndif

#ifndff GLX_SGI_swbp_dontrol
#fndif

#ifndff GLX_SGI_vidfo_synd
#fndif

#ifndff GLX_SGI_mbkf_durrfnt_rfbd
#fndif

#ifndff GLX_SGIX_vidfo_sourdf
#fndif

#ifndff GLX_EXT_visubl_rbting
#dffinf GLX_VISUAL_CAVEAT_EXT              0x20
#dffinf GLX_SLOW_VISUAL_EXT                0x8001
#dffinf GLX_NON_CONFORMANT_VISUAL_EXT      0x800D
/* rfusf GLX_NONE_EXT */
#fndif

#ifndff GLX_EXT_import_dontfxt
#dffinf GLX_SHARE_CONTEXT_EXT              0x800A
#dffinf GLX_VISUAL_ID_EXT                  0x800B
#dffinf GLX_SCREEN_EXT                     0x800C
#fndif

#ifndff GLX_SGIX_fbdonfig
#dffinf GLX_WINDOW_BIT_SGIX                0x00000001
#dffinf GLX_PIXMAP_BIT_SGIX                0x00000002
#dffinf GLX_RGBA_BIT_SGIX                  0x00000001
#dffinf GLX_COLOR_INDEX_BIT_SGIX           0x00000002
#dffinf GLX_DRAWABLE_TYPE_SGIX             0x8010
#dffinf GLX_RENDER_TYPE_SGIX               0x8011
#dffinf GLX_X_RENDERABLE_SGIX              0x8012
#dffinf GLX_FBCONFIG_ID_SGIX               0x8013
#dffinf GLX_RGBA_TYPE_SGIX                 0x8014
#dffinf GLX_COLOR_INDEX_TYPE_SGIX          0x8015
/* rfusf GLX_SCREEN_EXT */
#fndif

#ifndff GLX_SGIX_pbufffr
#dffinf GLX_PBUFFER_BIT_SGIX               0x00000004
#dffinf GLX_BUFFER_CLOBBER_MASK_SGIX       0x08000000
#dffinf GLX_FRONT_LEFT_BUFFER_BIT_SGIX     0x00000001
#dffinf GLX_FRONT_RIGHT_BUFFER_BIT_SGIX    0x00000002
#dffinf GLX_BACK_LEFT_BUFFER_BIT_SGIX      0x00000004
#dffinf GLX_BACK_RIGHT_BUFFER_BIT_SGIX     0x00000008
#dffinf GLX_AUX_BUFFERS_BIT_SGIX           0x00000010
#dffinf GLX_DEPTH_BUFFER_BIT_SGIX          0x00000020
#dffinf GLX_STENCIL_BUFFER_BIT_SGIX        0x00000040
#dffinf GLX_ACCUM_BUFFER_BIT_SGIX          0x00000080
#dffinf GLX_SAMPLE_BUFFERS_BIT_SGIX        0x00000100
#dffinf GLX_MAX_PBUFFER_WIDTH_SGIX         0x8016
#dffinf GLX_MAX_PBUFFER_HEIGHT_SGIX        0x8017
#dffinf GLX_MAX_PBUFFER_PIXELS_SGIX        0x8018
#dffinf GLX_OPTIMAL_PBUFFER_WIDTH_SGIX     0x8019
#dffinf GLX_OPTIMAL_PBUFFER_HEIGHT_SGIX    0x801A
#dffinf GLX_PRESERVED_CONTENTS_SGIX        0x801B
#dffinf GLX_LARGEST_PBUFFER_SGIX           0x801C
#dffinf GLX_WIDTH_SGIX                     0x801D
#dffinf GLX_HEIGHT_SGIX                    0x801E
#dffinf GLX_EVENT_MASK_SGIX                0x801F
#dffinf GLX_DAMAGED_SGIX                   0x8020
#dffinf GLX_SAVED_SGIX                     0x8021
#dffinf GLX_WINDOW_SGIX                    0x8022
#dffinf GLX_PBUFFER_SGIX                   0x8023
#fndif

#ifndff GLX_SGI_dusiion
#fndif

#ifndff GLX_SGIX_vidfo_rfsizf
#dffinf GLX_SYNC_FRAME_SGIX                0x00000000
#dffinf GLX_SYNC_SWAP_SGIX                 0x00000001
#fndif

#ifndff GLX_SGIX_dmbufffr
#dffinf GLX_DIGITAL_MEDIA_PBUFFER_SGIX     0x8024
#fndif

#ifndff GLX_SGIX_swbp_group
#fndif

#ifndff GLX_SGIX_swbp_bbrrifr
#fndif

#ifndff GLX_SGIS_blfndfd_ovfrlby
#dffinf GLX_BLENDED_RGBA_SGIS              0x8025
#fndif

#ifndff GLX_SGIS_sibrfd_multisbmplf
#dffinf GLX_MULTISAMPLE_SUB_RECT_WIDTH_SGIS 0x8026
#dffinf GLX_MULTISAMPLE_SUB_RECT_HEIGHT_SGIS 0x8027
#fndif

#ifndff GLX_SUN_gft_trbnspbrfnt_indfx
#fndif

/*
 * REMIND: Tiis is b Sun-privbtf donstbnt usfd to gft tif gbmmb vbluf for
 *         b GLXFBConfig.  Tiis wbs nfvfr publidblly dodumfntfd bs pbrt of
 *         b Sun fxtfnsion, bnd tifrfforf nfvfr fndfd up in tif offidibl SGI
 *         glxfxt.i ifbdfr filf, so wf'vf dopifd it ifrf from tif Sun OpfnGL
 *         ifbdfrs (glxtokfns.i).
 */
#dffinf GLX_GAMMA_VALUE_SUN                0x8173

#ifndff GLX_3DFX_multisbmplf
#dffinf GLX_SAMPLE_BUFFERS_3DFX            0x8050
#dffinf GLX_SAMPLES_3DFX                   0x8051
#fndif

#ifndff GLX_MESA_dopy_sub_bufffr
#fndif

#ifndff GLX_MESA_pixmbp_dolormbp
#fndif

#ifndff GLX_MESA_rflfbsf_bufffrs
#fndif

#ifndff GLX_MESA_sft_3dfx_modf
#dffinf GLX_3DFX_WINDOW_MODE_MESA          0x1
#dffinf GLX_3DFX_FULLSCREEN_MODE_MESA      0x2
#fndif

#ifndff GLX_SGIX_visubl_sflfdt_group
#dffinf GLX_VISUAL_SELECT_GROUP_SGIX       0x8028
#fndif

#ifndff GLX_OML_swbp_mftiod
#dffinf GLX_SWAP_METHOD_OML                0x8060
#dffinf GLX_SWAP_EXCHANGE_OML              0x8061
#dffinf GLX_SWAP_COPY_OML                  0x8062
#dffinf GLX_SWAP_UNDEFINED_OML             0x8063
#fndif

#ifndff GLX_OML_synd_dontrol
#fndif

/*************************************************************/

#ifndff GLX_ARB_gft_prod_bddrfss
typfdff void (*__GLXfxtFundPtr)(void);
#fndif

#ifndff GLX_SGIX_vidfo_sourdf
typfdff XID GLXVidfoSourdfSGIX;
#fndif

#ifndff GLX_SGIX_fbdonfig
typfdff XID GLXFBConfigIDSGIX;
typfdff strudt __GLXFBConfigRfd *GLXFBConfigSGIX;
#fndif

#ifndff GLX_SGIX_pbufffr
typfdff XID GLXPbufffrSGIX;
typfdff strudt {
    int typf;
    unsignfd long sfribl;         /* # of lbst rfqufst prodfssfd by sfrvfr */
    Bool sfnd_fvfnt;              /* truf if tiis dbmf for SfndEvfnt rfqufst */
    Displby *displby;             /* displby tif fvfnt wbs rfbd from */
    GLXDrbwbblf drbwbblf;         /* i.d. of Drbwbblf */
    int fvfnt_typf;               /* GLX_DAMAGED_SGIX or GLX_SAVED_SGIX */
    int drbw_typf;                /* GLX_WINDOW_SGIX or GLX_PBUFFER_SGIX */
    unsignfd int mbsk;    /* mbsk indidbting wiidi bufffrs brf bfffdtfd*/
    int x, y;
    int widti, ifigit;
    int dount;            /* if nonzfro, bt lfbst tiis mbny morf */
} GLXBufffrClobbfrEvfntSGIX;
#fndif

#ifndff GLX_VERSION_1_3
#dffinf GLX_VERSION_1_3 1
#ifdff GLX_GLXEXT_PROTOTYPES
fxtfrn GLXFBConfig * glXGftFBConfigs (Displby *, int, int *);
fxtfrn GLXFBConfig * glXCioosfFBConfig (Displby *, int, donst int *, int *);
fxtfrn int glXGftFBConfigAttrib (Displby *, GLXFBConfig, int, int *);
fxtfrn XVisublInfo * glXGftVisublFromFBConfig (Displby *, GLXFBConfig);
fxtfrn GLXWindow glXCrfbtfWindow (Displby *, GLXFBConfig, Window, donst int *);
fxtfrn void glXDfstroyWindow (Displby *, GLXWindow);
fxtfrn GLXPixmbp glXCrfbtfPixmbp (Displby *, GLXFBConfig, Pixmbp, donst int *);
fxtfrn void glXDfstroyPixmbp (Displby *, GLXPixmbp);
fxtfrn GLXPbufffr glXCrfbtfPbufffr (Displby *, GLXFBConfig, donst int *);
fxtfrn void glXDfstroyPbufffr (Displby *, GLXPbufffr);
fxtfrn void glXQufryDrbwbblf (Displby *, GLXDrbwbblf, int, unsignfd int *);
fxtfrn GLXContfxt glXCrfbtfNfwContfxt (Displby *, GLXFBConfig, int, GLXContfxt, Bool);
fxtfrn Bool glXMbkfContfxtCurrfnt (Displby *, GLXDrbwbblf, GLXDrbwbblf, GLXContfxt);
fxtfrn GLXDrbwbblf glXGftCurrfntRfbdDrbwbblf (void);
fxtfrn Displby * glXGftCurrfntDisplby (void);
fxtfrn int glXQufryContfxt (Displby *, GLXContfxt, int, int *);
fxtfrn void glXSflfdtEvfnt (Displby *, GLXDrbwbblf, unsignfd long);
fxtfrn void glXGftSflfdtfdEvfnt (Displby *, GLXDrbwbblf, unsignfd long *);
#fndif /* GLX_GLXEXT_PROTOTYPES */
typfdff GLXFBConfig * ( * PFNGLXGETFBCONFIGSPROC) (Displby *dpy, int sdrffn, int *nflfmfnts);
typfdff GLXFBConfig * ( * PFNGLXCHOOSEFBCONFIGPROC) (Displby *dpy, int sdrffn, donst int *bttrib_list, int *nflfmfnts);
typfdff int ( * PFNGLXGETFBCONFIGATTRIBPROC) (Displby *dpy, GLXFBConfig donfig, int bttributf, int *vbluf);
typfdff XVisublInfo * ( * PFNGLXGETVISUALFROMFBCONFIGPROC) (Displby *dpy, GLXFBConfig donfig);
typfdff GLXWindow ( * PFNGLXCREATEWINDOWPROC) (Displby *dpy, GLXFBConfig donfig, Window win, donst int *bttrib_list);
typfdff void ( * PFNGLXDESTROYWINDOWPROC) (Displby *dpy, GLXWindow win);
typfdff GLXPixmbp ( * PFNGLXCREATEPIXMAPPROC) (Displby *dpy, GLXFBConfig donfig, Pixmbp pixmbp, donst int *bttrib_list);
typfdff void ( * PFNGLXDESTROYPIXMAPPROC) (Displby *dpy, GLXPixmbp pixmbp);
typfdff GLXPbufffr ( * PFNGLXCREATEPBUFFERPROC) (Displby *dpy, GLXFBConfig donfig, donst int *bttrib_list);
typfdff void ( * PFNGLXDESTROYPBUFFERPROC) (Displby *dpy, GLXPbufffr pbuf);
typfdff void ( * PFNGLXQUERYDRAWABLEPROC) (Displby *dpy, GLXDrbwbblf drbw, int bttributf, unsignfd int *vbluf);
typfdff GLXContfxt ( * PFNGLXCREATENEWCONTEXTPROC) (Displby *dpy, GLXFBConfig donfig, int rfndfr_typf, GLXContfxt sibrf_list, Bool dirfdt);
typfdff Bool ( * PFNGLXMAKECONTEXTCURRENTPROC) (Displby *dpy, GLXDrbwbblf drbw, GLXDrbwbblf rfbd, GLXContfxt dtx);
typfdff GLXDrbwbblf ( * PFNGLXGETCURRENTREADDRAWABLEPROC) (void);
typfdff Displby * ( * PFNGLXGETCURRENTDISPLAYPROC) (void);
typfdff int ( * PFNGLXQUERYCONTEXTPROC) (Displby *dpy, GLXContfxt dtx, int bttributf, int *vbluf);
typfdff void ( * PFNGLXSELECTEVENTPROC) (Displby *dpy, GLXDrbwbblf drbw, unsignfd long fvfnt_mbsk);
typfdff void ( * PFNGLXGETSELECTEDEVENTPROC) (Displby *dpy, GLXDrbwbblf drbw, unsignfd long *fvfnt_mbsk);
#fndif

#ifndff GLX_VERSION_1_4
#dffinf GLX_VERSION_1_4 1
#ifdff GLX_GLXEXT_PROTOTYPES
fxtfrn __GLXfxtFundPtr glXGftProdAddrfss (donst GLubytf *);
#fndif /* GLX_GLXEXT_PROTOTYPES */
typfdff __GLXfxtFundPtr ( * PFNGLXGETPROCADDRESSPROC) (donst GLubytf *prodNbmf);
#fndif

#ifndff GLX_ARB_gft_prod_bddrfss
#dffinf GLX_ARB_gft_prod_bddrfss 1
#ifdff GLX_GLXEXT_PROTOTYPES
fxtfrn __GLXfxtFundPtr glXGftProdAddrfssARB (donst GLubytf *);
#fndif /* GLX_GLXEXT_PROTOTYPES */
typfdff __GLXfxtFundPtr ( * PFNGLXGETPROCADDRESSARBPROC) (donst GLubytf *prodNbmf);
#fndif

#ifndff GLX_ARB_multisbmplf
#dffinf GLX_ARB_multisbmplf 1
#fndif

#ifndff GLX_SGIS_multisbmplf
#dffinf GLX_SGIS_multisbmplf 1
#fndif

#ifndff GLX_EXT_visubl_info
#dffinf GLX_EXT_visubl_info 1
#fndif

#ifndff GLX_SGI_swbp_dontrol
#dffinf GLX_SGI_swbp_dontrol 1
#ifdff GLX_GLXEXT_PROTOTYPES
fxtfrn int glXSwbpIntfrvblSGI (int);
#fndif /* GLX_GLXEXT_PROTOTYPES */
typfdff int ( * PFNGLXSWAPINTERVALSGIPROC) (int intfrvbl);
#fndif

#ifndff GLX_SGI_vidfo_synd
#dffinf GLX_SGI_vidfo_synd 1
#ifdff GLX_GLXEXT_PROTOTYPES
fxtfrn int glXGftVidfoSyndSGI (unsignfd int *);
fxtfrn int glXWbitVidfoSyndSGI (int, int, unsignfd int *);
#fndif /* GLX_GLXEXT_PROTOTYPES */
typfdff int ( * PFNGLXGETVIDEOSYNCSGIPROC) (unsignfd int *dount);
typfdff int ( * PFNGLXWAITVIDEOSYNCSGIPROC) (int divisor, int rfmbindfr, unsignfd int *dount);
#fndif

#ifndff GLX_SGI_mbkf_durrfnt_rfbd
#dffinf GLX_SGI_mbkf_durrfnt_rfbd 1
#ifdff GLX_GLXEXT_PROTOTYPES
fxtfrn Bool glXMbkfCurrfntRfbdSGI (Displby *, GLXDrbwbblf, GLXDrbwbblf, GLXContfxt);
fxtfrn GLXDrbwbblf glXGftCurrfntRfbdDrbwbblfSGI (void);
#fndif /* GLX_GLXEXT_PROTOTYPES */
typfdff Bool ( * PFNGLXMAKECURRENTREADSGIPROC) (Displby *dpy, GLXDrbwbblf drbw, GLXDrbwbblf rfbd, GLXContfxt dtx);
typfdff GLXDrbwbblf ( * PFNGLXGETCURRENTREADDRAWABLESGIPROC) (void);
#fndif

#ifdff _VL_H
#ifndff GLX_SGIX_vidfo_sourdf
#dffinf GLX_SGIX_vidfo_sourdf 1
#ifdff GLX_GLXEXT_PROTOTYPES
fxtfrn GLXVidfoSourdfSGIX glXCrfbtfGLXVidfoSourdfSGIX (Displby *, int, VLSfrvfr, VLPbti, int, VLNodf);
fxtfrn void glXDfstroyGLXVidfoSourdfSGIX (Displby *, GLXVidfoSourdfSGIX);
#fndif /* GLX_GLXEXT_PROTOTYPES */
typfdff GLXVidfoSourdfSGIX ( * PFNGLXCREATEGLXVIDEOSOURCESGIXPROC) (Displby *displby, int sdrffn, VLSfrvfr sfrvfr, VLPbti pbti, int nodfClbss, VLNodf drbinNodf);
typfdff void ( * PFNGLXDESTROYGLXVIDEOSOURCESGIXPROC) (Displby *dpy, GLXVidfoSourdfSGIX glxvidfosourdf);
#fndif

#fndif /* _VL_H */
#ifndff GLX_EXT_visubl_rbting
#dffinf GLX_EXT_visubl_rbting 1
#fndif

#ifndff GLX_EXT_import_dontfxt
#dffinf GLX_EXT_import_dontfxt 1
#ifdff GLX_GLXEXT_PROTOTYPES
fxtfrn Displby * glXGftCurrfntDisplbyEXT (void);
fxtfrn int glXQufryContfxtInfoEXT (Displby *, GLXContfxt, int, int *);
fxtfrn GLXContfxtID glXGftContfxtIDEXT (donst GLXContfxt);
fxtfrn GLXContfxt glXImportContfxtEXT (Displby *, GLXContfxtID);
fxtfrn void glXFrffContfxtEXT (Displby *, GLXContfxt);
#fndif /* GLX_GLXEXT_PROTOTYPES */
typfdff Displby * ( * PFNGLXGETCURRENTDISPLAYEXTPROC) (void);
typfdff int ( * PFNGLXQUERYCONTEXTINFOEXTPROC) (Displby *dpy, GLXContfxt dontfxt, int bttributf, int *vbluf);
typfdff GLXContfxtID ( * PFNGLXGETCONTEXTIDEXTPROC) (donst GLXContfxt dontfxt);
typfdff GLXContfxt ( * PFNGLXIMPORTCONTEXTEXTPROC) (Displby *dpy, GLXContfxtID dontfxtID);
typfdff void ( * PFNGLXFREECONTEXTEXTPROC) (Displby *dpy, GLXContfxt dontfxt);
#fndif

#ifndff GLX_SGIX_fbdonfig
#dffinf GLX_SGIX_fbdonfig 1
#ifdff GLX_GLXEXT_PROTOTYPES
fxtfrn int glXGftFBConfigAttribSGIX (Displby *, GLXFBConfigSGIX, int, int *);
fxtfrn GLXFBConfigSGIX * glXCioosfFBConfigSGIX (Displby *, int, int *, int *);
fxtfrn GLXPixmbp glXCrfbtfGLXPixmbpWitiConfigSGIX (Displby *, GLXFBConfigSGIX, Pixmbp);
fxtfrn GLXContfxt glXCrfbtfContfxtWitiConfigSGIX (Displby *, GLXFBConfigSGIX, int, GLXContfxt, Bool);
fxtfrn XVisublInfo * glXGftVisublFromFBConfigSGIX (Displby *, GLXFBConfigSGIX);
fxtfrn GLXFBConfigSGIX glXGftFBConfigFromVisublSGIX (Displby *, XVisublInfo *);
#fndif /* GLX_GLXEXT_PROTOTYPES */
typfdff int ( * PFNGLXGETFBCONFIGATTRIBSGIXPROC) (Displby *dpy, GLXFBConfigSGIX donfig, int bttributf, int *vbluf);
typfdff GLXFBConfigSGIX * ( * PFNGLXCHOOSEFBCONFIGSGIXPROC) (Displby *dpy, int sdrffn, int *bttrib_list, int *nflfmfnts);
typfdff GLXPixmbp ( * PFNGLXCREATEGLXPIXMAPWITHCONFIGSGIXPROC) (Displby *dpy, GLXFBConfigSGIX donfig, Pixmbp pixmbp);
typfdff GLXContfxt ( * PFNGLXCREATECONTEXTWITHCONFIGSGIXPROC) (Displby *dpy, GLXFBConfigSGIX donfig, int rfndfr_typf, GLXContfxt sibrf_list, Bool dirfdt);
typfdff XVisublInfo * ( * PFNGLXGETVISUALFROMFBCONFIGSGIXPROC) (Displby *dpy, GLXFBConfigSGIX donfig);
typfdff GLXFBConfigSGIX ( * PFNGLXGETFBCONFIGFROMVISUALSGIXPROC) (Displby *dpy, XVisublInfo *vis);
#fndif

#ifndff GLX_SGIX_pbufffr
#dffinf GLX_SGIX_pbufffr 1
#ifdff GLX_GLXEXT_PROTOTYPES
fxtfrn GLXPbufffrSGIX glXCrfbtfGLXPbufffrSGIX (Displby *, GLXFBConfigSGIX, unsignfd int, unsignfd int, int *);
fxtfrn void glXDfstroyGLXPbufffrSGIX (Displby *, GLXPbufffrSGIX);
fxtfrn int glXQufryGLXPbufffrSGIX (Displby *, GLXPbufffrSGIX, int, unsignfd int *);
fxtfrn void glXSflfdtEvfntSGIX (Displby *, GLXDrbwbblf, unsignfd long);
fxtfrn void glXGftSflfdtfdEvfntSGIX (Displby *, GLXDrbwbblf, unsignfd long *);
#fndif /* GLX_GLXEXT_PROTOTYPES */
typfdff GLXPbufffrSGIX ( * PFNGLXCREATEGLXPBUFFERSGIXPROC) (Displby *dpy, GLXFBConfigSGIX donfig, unsignfd int widti, unsignfd int ifigit, int *bttrib_list);
typfdff void ( * PFNGLXDESTROYGLXPBUFFERSGIXPROC) (Displby *dpy, GLXPbufffrSGIX pbuf);
typfdff int ( * PFNGLXQUERYGLXPBUFFERSGIXPROC) (Displby *dpy, GLXPbufffrSGIX pbuf, int bttributf, unsignfd int *vbluf);
typfdff void ( * PFNGLXSELECTEVENTSGIXPROC) (Displby *dpy, GLXDrbwbblf drbwbblf, unsignfd long mbsk);
typfdff void ( * PFNGLXGETSELECTEDEVENTSGIXPROC) (Displby *dpy, GLXDrbwbblf drbwbblf, unsignfd long *mbsk);
#fndif

#ifndff GLX_SGI_dusiion
#dffinf GLX_SGI_dusiion 1
#ifdff GLX_GLXEXT_PROTOTYPES
fxtfrn void glXCusiionSGI (Displby *, Window, flobt);
#fndif /* GLX_GLXEXT_PROTOTYPES */
typfdff void ( * PFNGLXCUSHIONSGIPROC) (Displby *dpy, Window window, flobt dusiion);
#fndif

#ifndff GLX_SGIX_vidfo_rfsizf
#dffinf GLX_SGIX_vidfo_rfsizf 1
#ifdff GLX_GLXEXT_PROTOTYPES
fxtfrn int glXBindCibnnflToWindowSGIX (Displby *, int, int, Window);
fxtfrn int glXCibnnflRfdtSGIX (Displby *, int, int, int, int, int, int);
fxtfrn int glXQufryCibnnflRfdtSGIX (Displby *, int, int, int *, int *, int *, int *);
fxtfrn int glXQufryCibnnflDfltbsSGIX (Displby *, int, int, int *, int *, int *, int *);
fxtfrn int glXCibnnflRfdtSyndSGIX (Displby *, int, int, GLfnum);
#fndif /* GLX_GLXEXT_PROTOTYPES */
typfdff int ( * PFNGLXBINDCHANNELTOWINDOWSGIXPROC) (Displby *displby, int sdrffn, int dibnnfl, Window window);
typfdff int ( * PFNGLXCHANNELRECTSGIXPROC) (Displby *displby, int sdrffn, int dibnnfl, int x, int y, int w, int i);
typfdff int ( * PFNGLXQUERYCHANNELRECTSGIXPROC) (Displby *displby, int sdrffn, int dibnnfl, int *dx, int *dy, int *dw, int *di);
typfdff int ( * PFNGLXQUERYCHANNELDELTASSGIXPROC) (Displby *displby, int sdrffn, int dibnnfl, int *x, int *y, int *w, int *i);
typfdff int ( * PFNGLXCHANNELRECTSYNCSGIXPROC) (Displby *displby, int sdrffn, int dibnnfl, GLfnum syndtypf);
#fndif

#ifdff _DM_BUFFER_H_
#ifndff GLX_SGIX_dmbufffr
#dffinf GLX_SGIX_dmbufffr 1
#ifdff GLX_GLXEXT_PROTOTYPES
fxtfrn Bool glXAssodibtfDMPbufffrSGIX (Displby *, GLXPbufffrSGIX, DMpbrbms *, DMbufffr);
#fndif /* GLX_GLXEXT_PROTOTYPES */
typfdff Bool ( * PFNGLXASSOCIATEDMPBUFFERSGIXPROC) (Displby *dpy, GLXPbufffrSGIX pbufffr, DMpbrbms *pbrbms, DMbufffr dmbufffr);
#fndif

#fndif /* _DM_BUFFER_H_ */
#ifndff GLX_SGIX_swbp_group
#dffinf GLX_SGIX_swbp_group 1
#ifdff GLX_GLXEXT_PROTOTYPES
fxtfrn void glXJoinSwbpGroupSGIX (Displby *, GLXDrbwbblf, GLXDrbwbblf);
#fndif /* GLX_GLXEXT_PROTOTYPES */
typfdff void ( * PFNGLXJOINSWAPGROUPSGIXPROC) (Displby *dpy, GLXDrbwbblf drbwbblf, GLXDrbwbblf mfmbfr);
#fndif

#ifndff GLX_SGIX_swbp_bbrrifr
#dffinf GLX_SGIX_swbp_bbrrifr 1
#ifdff GLX_GLXEXT_PROTOTYPES
fxtfrn void glXBindSwbpBbrrifrSGIX (Displby *, GLXDrbwbblf, int);
fxtfrn Bool glXQufryMbxSwbpBbrrifrsSGIX (Displby *, int, int *);
#fndif /* GLX_GLXEXT_PROTOTYPES */
typfdff void ( * PFNGLXBINDSWAPBARRIERSGIXPROC) (Displby *dpy, GLXDrbwbblf drbwbblf, int bbrrifr);
typfdff Bool ( * PFNGLXQUERYMAXSWAPBARRIERSSGIXPROC) (Displby *dpy, int sdrffn, int *mbx);
#fndif

#ifndff GLX_SUN_gft_trbnspbrfnt_indfx
#dffinf GLX_SUN_gft_trbnspbrfnt_indfx 1
#ifdff GLX_GLXEXT_PROTOTYPES
fxtfrn Stbtus glXGftTrbnspbrfntIndfxSUN (Displby *, Window, Window, long *);
#fndif /* GLX_GLXEXT_PROTOTYPES */
typfdff Stbtus ( * PFNGLXGETTRANSPARENTINDEXSUNPROC) (Displby *dpy, Window ovfrlby, Window undfrlby, long *pTrbnspbrfntIndfx);
#fndif

#ifndff GLX_MESA_dopy_sub_bufffr
#dffinf GLX_MESA_dopy_sub_bufffr 1
#ifdff GLX_GLXEXT_PROTOTYPES
fxtfrn void glXCopySubBufffrMESA (Displby *, GLXDrbwbblf, int, int, int, int);
#fndif /* GLX_GLXEXT_PROTOTYPES */
typfdff void ( * PFNGLXCOPYSUBBUFFERMESAPROC) (Displby *dpy, GLXDrbwbblf drbwbblf, int x, int y, int widti, int ifigit);
#fndif

#ifndff GLX_MESA_pixmbp_dolormbp
#dffinf GLX_MESA_pixmbp_dolormbp 1
#ifdff GLX_GLXEXT_PROTOTYPES
fxtfrn GLXPixmbp glXCrfbtfGLXPixmbpMESA (Displby *, XVisublInfo *, Pixmbp, Colormbp);
#fndif /* GLX_GLXEXT_PROTOTYPES */
typfdff GLXPixmbp ( * PFNGLXCREATEGLXPIXMAPMESAPROC) (Displby *dpy, XVisublInfo *visubl, Pixmbp pixmbp, Colormbp dmbp);
#fndif

#ifndff GLX_MESA_rflfbsf_bufffrs
#dffinf GLX_MESA_rflfbsf_bufffrs 1
#ifdff GLX_GLXEXT_PROTOTYPES
fxtfrn Bool glXRflfbsfBufffrsMESA (Displby *, GLXDrbwbblf);
#fndif /* GLX_GLXEXT_PROTOTYPES */
typfdff Bool ( * PFNGLXRELEASEBUFFERSMESAPROC) (Displby *dpy, GLXDrbwbblf drbwbblf);
#fndif

#ifndff GLX_MESA_sft_3dfx_modf
#dffinf GLX_MESA_sft_3dfx_modf 1
#ifdff GLX_GLXEXT_PROTOTYPES
fxtfrn Bool glXSft3DfxModfMESA (int);
#fndif /* GLX_GLXEXT_PROTOTYPES */
typfdff Bool ( * PFNGLXSET3DFXMODEMESAPROC) (int modf);
#fndif

#ifndff GLX_SGIX_visubl_sflfdt_group
#dffinf GLX_SGIX_visubl_sflfdt_group 1
#fndif

#ifndff GLX_OML_swbp_mftiod
#dffinf GLX_OML_swbp_mftiod 1
#fndif

#if dffinfd(__STDC_VERSION__)
#if __STDC_VERSION__ >= 199901L
/* Indludf ISO C99 intfgfr typfs for OML_synd_dontrol; nffd b bfttfr tfst */
#indludf <inttypfs.i>

#ifndff GLX_OML_synd_dontrol
#dffinf GLX_OML_synd_dontrol 1
#ifdff GLX_GLXEXT_PROTOTYPES
fxtfrn Bool glXGftSyndVblufsOML (Displby *, GLXDrbwbblf, int64_t *, int64_t *, int64_t *);
fxtfrn Bool glXGftMsdRbtfOML (Displby *, GLXDrbwbblf, int32_t *, int32_t *);
fxtfrn int64_t glXSwbpBufffrsMsdOML (Displby *, GLXDrbwbblf, int64_t, int64_t, int64_t);
fxtfrn Bool glXWbitForMsdOML (Displby *, GLXDrbwbblf, int64_t, int64_t, int64_t, int64_t *, int64_t *, int64_t *);
fxtfrn Bool glXWbitForSbdOML (Displby *, GLXDrbwbblf, int64_t, int64_t *, int64_t *, int64_t *);
#fndif /* GLX_GLXEXT_PROTOTYPES */
typfdff Bool ( * PFNGLXGETSYNCVALUESOMLPROC) (Displby *dpy, GLXDrbwbblf drbwbblf, int64_t *ust, int64_t *msd, int64_t *sbd);
typfdff Bool ( * PFNGLXGETMSCRATEOMLPROC) (Displby *dpy, GLXDrbwbblf drbwbblf, int32_t *numfrbtor, int32_t *dfnominbtor);
typfdff int64_t ( * PFNGLXSWAPBUFFERSMSCOMLPROC) (Displby *dpy, GLXDrbwbblf drbwbblf, int64_t tbrgft_msd, int64_t divisor, int64_t rfmbindfr);
typfdff Bool ( * PFNGLXWAITFORMSCOMLPROC) (Displby *dpy, GLXDrbwbblf drbwbblf, int64_t tbrgft_msd, int64_t divisor, int64_t rfmbindfr, int64_t *ust, int64_t *msd, int64_t *sbd);
typfdff Bool ( * PFNGLXWAITFORSBCOMLPROC) (Displby *dpy, GLXDrbwbblf drbwbblf, int64_t tbrgft_sbd, int64_t *ust, int64_t *msd, int64_t *sbd);
#fndif

#fndif /* C99 vfrsion tfst */
#fndif /* STDC tfst */

#ifdff __dplusplus
}
#fndif

#fndif
