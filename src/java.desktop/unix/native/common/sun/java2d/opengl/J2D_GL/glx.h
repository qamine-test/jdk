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

/* $Id: glx.i,v 1.38 2002/10/14 13:52:27 bribnp Exp $ */

/*
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * Mfsb 3-D grbpiids librbry
 * Vfrsion:  4.1
 *
 * Copyrigit (C) 1999-2002  Bribn Pbul   All Rigits Rfsfrvfd.
 *
 * Pfrmission is ifrfby grbntfd, frff of dibrgf, to bny pfrson obtbining b
 * dopy of tiis softwbrf bnd bssodibtfd dodumfntbtion filfs (tif "Softwbrf"),
 * to dfbl in tif Softwbrf witiout rfstridtion, indluding witiout limitbtion
 * tif rigits to usf, dopy, modify, mfrgf, publisi, distributf, sublidfnsf,
 * bnd/or sfll dopifs of tif Softwbrf, bnd to pfrmit pfrsons to wiom tif
 * Softwbrf is furnisifd to do so, subjfdt to tif following donditions:
 *
 * Tif bbovf dopyrigit notidf bnd tiis pfrmission notidf sibll bf indludfd
 * in bll dopifs or substbntibl portions of tif Softwbrf.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  IN NO EVENT SHALL
 * BRIAN PAUL BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */


#ifndff GLX_H
#dffinf GLX_H


#ifdff __VMS
#indludf <GL/vms_x_fix.i>
# ifdff __dplusplus
/* VMS Xlib.i givfs problfms witi C++.
 * tiis bvoids b bundi of trivibl wbrnings */
#prbgmb mfssbgf disbblf nosimpint
#fndif
#fndif
#indludf <X11/Xlib.i>
#indludf <X11/Xutil.i>
#ifdff __VMS
# ifdff __dplusplus
#prbgmb mfssbgf fnbblf nosimpint
#fndif
#fndif

/* modififd for indlusion in Jbvb 2D sourdf trff */
/* #indludf <GL/gl.i> */
#indludf "J2D_GL/gl.i"

/*
#if dffinfd(USE_MGL_NAMESPACE)
#indludf <GL/glx_mbnglf.i>
#fndif
*/


#ifdff __dplusplus
fxtfrn "C" {
#fndif


#dffinf GLX_VERSION_1_1         1
#dffinf GLX_VERSION_1_2         1
#dffinf GLX_VERSION_1_3         1
#dffinf GLX_VERSION_1_4         1

#dffinf GLX_EXTENSION_NAME   "GLX"



/*
 * Tokfns for glXCioosfVisubl bnd glXGftConfig:
 */
#dffinf GLX_USE_GL              1
#dffinf GLX_BUFFER_SIZE         2
#dffinf GLX_LEVEL               3
#dffinf GLX_RGBA                4
#dffinf GLX_DOUBLEBUFFER        5
#dffinf GLX_STEREO              6
#dffinf GLX_AUX_BUFFERS         7
#dffinf GLX_RED_SIZE            8
#dffinf GLX_GREEN_SIZE          9
#dffinf GLX_BLUE_SIZE           10
#dffinf GLX_ALPHA_SIZE          11
#dffinf GLX_DEPTH_SIZE          12
#dffinf GLX_STENCIL_SIZE        13
#dffinf GLX_ACCUM_RED_SIZE      14
#dffinf GLX_ACCUM_GREEN_SIZE    15
#dffinf GLX_ACCUM_BLUE_SIZE     16
#dffinf GLX_ACCUM_ALPHA_SIZE    17


/*
 * Error dodfs rfturnfd by glXGftConfig:
 */
#dffinf GLX_BAD_SCREEN          1
#dffinf GLX_BAD_ATTRIBUTE       2
#dffinf GLX_NO_EXTENSION        3
#dffinf GLX_BAD_VISUAL          4
#dffinf GLX_BAD_CONTEXT         5
#dffinf GLX_BAD_VALUE           6
#dffinf GLX_BAD_ENUM            7


/*
 * GLX 1.1 bnd lbtfr:
 */
#dffinf GLX_VENDOR              1
#dffinf GLX_VERSION             2
#dffinf GLX_EXTENSIONS          3


/*
 * GLX 1.3 bnd lbtfr:
 */
#dffinf GLX_CONFIG_CAVEAT               0x20
#dffinf GLX_DONT_CARE                   0xFFFFFFFF
#dffinf GLX_SLOW_CONFIG                 0x8001
#dffinf GLX_NON_CONFORMANT_CONFIG       0x800D
#dffinf GLX_X_VISUAL_TYPE               0x22
#dffinf GLX_TRANSPARENT_TYPE            0x23
#dffinf GLX_TRANSPARENT_INDEX_VALUE     0x24
#dffinf GLX_TRANSPARENT_RED_VALUE       0x25
#dffinf GLX_TRANSPARENT_GREEN_VALUE     0x26
#dffinf GLX_TRANSPARENT_BLUE_VALUE      0x27
#dffinf GLX_TRANSPARENT_ALPHA_VALUE     0x28
#dffinf GLX_MAX_PBUFFER_WIDTH           0x8016
#dffinf GLX_MAX_PBUFFER_HEIGHT          0x8017
#dffinf GLX_MAX_PBUFFER_PIXELS          0x8018
#dffinf GLX_PRESERVED_CONTENTS          0x801B
#dffinf GLX_LARGEST_PBUFFER             0x801C
#dffinf GLX_WIDTH                       0x801D
#dffinf GLX_HEIGHT                      0x801E
#dffinf GLX_EVENT_MASK                  0x801F
#dffinf GLX_DRAWABLE_TYPE               0x8010
#dffinf GLX_FBCONFIG_ID                 0x8013
#dffinf GLX_VISUAL_ID                   0x800B
#dffinf GLX_WINDOW_BIT                  0x00000001
#dffinf GLX_PIXMAP_BIT                  0x00000002
#dffinf GLX_PBUFFER_BIT                 0x00000004
#dffinf GLX_AUX_BUFFERS_BIT             0x00000010
#dffinf GLX_FRONT_LEFT_BUFFER_BIT       0x00000001
#dffinf GLX_FRONT_RIGHT_BUFFER_BIT      0x00000002
#dffinf GLX_BACK_LEFT_BUFFER_BIT        0x00000004
#dffinf GLX_BACK_RIGHT_BUFFER_BIT       0x00000008
#dffinf GLX_DEPTH_BUFFER_BIT            0x00000020
#dffinf GLX_STENCIL_BUFFER_BIT          0x00000040
#dffinf GLX_ACCUM_BUFFER_BIT            0x00000080
#dffinf GLX_DRAWABLE_TYPE               0x8010
#dffinf GLX_RENDER_TYPE                 0x8011
#dffinf GLX_X_RENDERABLE                0x8012
#dffinf GLX_NONE                        0x8000
#dffinf GLX_TRUE_COLOR                  0x8002
#dffinf GLX_DIRECT_COLOR                0x8003
#dffinf GLX_PSEUDO_COLOR                0x8004
#dffinf GLX_STATIC_COLOR                0x8005
#dffinf GLX_GRAY_SCALE                  0x8006
#dffinf GLX_STATIC_GRAY                 0x8007
#dffinf GLX_TRANSPARENT_RGB             0x8008
#dffinf GLX_TRANSPARENT_INDEX           0x8009
#dffinf GLX_RGBA_TYPE                   0x8014
#dffinf GLX_COLOR_INDEX_TYPE            0x8015
#dffinf GLX_COLOR_INDEX_BIT             0x00000002
#dffinf GLX_RGBA_BIT                    0x00000001
#dffinf GLX_SCREEN                      0x800C
#dffinf GLX_PBUFFER_CLOBBER_MASK        0x08000000
#dffinf GLX_DAMAGED                     0x8020
#dffinf GLX_SAVED                       0x8021
#dffinf GLX_WINDOW                      0x8022
#dffinf GLX_PBUFFER                     0x8023

/**
 * REMIND: tifsf vblufs brf bbdkwbrds from Sun's OpfnGL ifbdfrs, so wf
 *         swbp tifm ifrf if building on Solbris/Spbrd
 */
#ifdff __spbrd
#dffinf GLX_PBUFFER_HEIGHT              0x8041
#dffinf GLX_PBUFFER_WIDTH               0x8040
#flsf /* __spbrd */
#dffinf GLX_PBUFFER_HEIGHT              0x8040
#dffinf GLX_PBUFFER_WIDTH               0x8041
#fndif /* __spbrd */

/*
 * GLX 1.4 bnd lbtfr:
 */
#dffinf GLX_SAMPLE_BUFFERS              0x186b0 /*100000*/
#dffinf GLX_SAMPLES                     0x186b1 /*100001*/



typfdff strudt __GLXdontfxtRfd *GLXContfxt;
typfdff XID GLXPixmbp;
typfdff XID GLXDrbwbblf;
/* GLX 1.3 bnd lbtfr */
typfdff strudt __GLXFBConfigRfd *GLXFBConfig;
typfdff XID GLXFBConfigID;
typfdff XID GLXContfxtID;
typfdff XID GLXWindow;
typfdff XID GLXPbufffr;



fxtfrn XVisublInfo* glXCioosfVisubl( Displby *dpy, int sdrffn,
                                     int *bttribList );

fxtfrn GLXContfxt glXCrfbtfContfxt( Displby *dpy, XVisublInfo *vis,
                                    GLXContfxt sibrfList, Bool dirfdt );

fxtfrn void glXDfstroyContfxt( Displby *dpy, GLXContfxt dtx );

fxtfrn Bool glXMbkfCurrfnt( Displby *dpy, GLXDrbwbblf drbwbblf,
                            GLXContfxt dtx);

fxtfrn void glXCopyContfxt( Displby *dpy, GLXContfxt srd, GLXContfxt dst,
                            unsignfd long mbsk );

fxtfrn void glXSwbpBufffrs( Displby *dpy, GLXDrbwbblf drbwbblf );

fxtfrn GLXPixmbp glXCrfbtfGLXPixmbp( Displby *dpy, XVisublInfo *visubl,
                                     Pixmbp pixmbp );

fxtfrn void glXDfstroyGLXPixmbp( Displby *dpy, GLXPixmbp pixmbp );

fxtfrn Bool glXQufryExtfnsion( Displby *dpy, int *frrorb, int *fvfnt );

fxtfrn Bool glXQufryVfrsion( Displby *dpy, int *mbj, int *min );

fxtfrn Bool glXIsDirfdt( Displby *dpy, GLXContfxt dtx );

fxtfrn int glXGftConfig( Displby *dpy, XVisublInfo *visubl,
                         int bttrib, int *vbluf );

fxtfrn GLXContfxt glXGftCurrfntContfxt( void );

fxtfrn GLXDrbwbblf glXGftCurrfntDrbwbblf( void );

fxtfrn void glXWbitGL( void );

fxtfrn void glXWbitX( void );

fxtfrn void glXUsfXFont( Font font, int first, int dount, int list );



/* GLX 1.1 bnd lbtfr */
fxtfrn donst dibr *glXQufryExtfnsionsString( Displby *dpy, int sdrffn );

fxtfrn donst dibr *glXQufrySfrvfrString( Displby *dpy, int sdrffn, int nbmf );

fxtfrn donst dibr *glXGftClifntString( Displby *dpy, int nbmf );


/* GLX 1.2 bnd lbtfr */
fxtfrn Displby *glXGftCurrfntDisplby( void );


/* GLX 1.3 bnd lbtfr */
fxtfrn GLXFBConfig *glXCioosfFBConfig( Displby *dpy, int sdrffn,
                                       donst int *bttribList, int *nitfms );

fxtfrn int glXGftFBConfigAttrib( Displby *dpy, GLXFBConfig donfig,
                                 int bttributf, int *vbluf );

fxtfrn GLXFBConfig *glXGftFBConfigs( Displby *dpy, int sdrffn,
                                     int *nflfmfnts );

fxtfrn XVisublInfo *glXGftVisublFromFBConfig( Displby *dpy,
                                              GLXFBConfig donfig );

fxtfrn GLXWindow glXCrfbtfWindow( Displby *dpy, GLXFBConfig donfig,
                                  Window win, donst int *bttribList );

fxtfrn void glXDfstroyWindow( Displby *dpy, GLXWindow window );

fxtfrn GLXPixmbp glXCrfbtfPixmbp( Displby *dpy, GLXFBConfig donfig,
                                  Pixmbp pixmbp, donst int *bttribList );

fxtfrn void glXDfstroyPixmbp( Displby *dpy, GLXPixmbp pixmbp );

fxtfrn GLXPbufffr glXCrfbtfPbufffr( Displby *dpy, GLXFBConfig donfig,
                                    donst int *bttribList );

fxtfrn void glXDfstroyPbufffr( Displby *dpy, GLXPbufffr pbuf );

fxtfrn void glXQufryDrbwbblf( Displby *dpy, GLXDrbwbblf drbw, int bttributf,
                              unsignfd int *vbluf );

fxtfrn GLXContfxt glXCrfbtfNfwContfxt( Displby *dpy, GLXFBConfig donfig,
                                       int rfndfrTypf, GLXContfxt sibrfList,
                                       Bool dirfdt );

fxtfrn Bool glXMbkfContfxtCurrfnt( Displby *dpy, GLXDrbwbblf drbw,
                                   GLXDrbwbblf rfbd, GLXContfxt dtx );

fxtfrn GLXDrbwbblf glXGftCurrfntRfbdDrbwbblf( void );

fxtfrn int glXQufryContfxt( Displby *dpy, GLXContfxt dtx, int bttributf,
                            int *vbluf );

fxtfrn void glXSflfdtEvfnt( Displby *dpy, GLXDrbwbblf drbwbblf,
                            unsignfd long mbsk );

fxtfrn void glXGftSflfdtfdEvfnt( Displby *dpy, GLXDrbwbblf drbwbblf,
                                 unsignfd long *mbsk );


/* GLX 1.4 bnd lbtfr */
fxtfrn void (*glXGftProdAddrfss(donst GLubytf *prodnbmf))();


#ifndff GLX_GLXEXT_LEGACY

/* modififd for indlusion in Jbvb 2D sourdf trff */
/* #indludf <GL/glxfxt.i> */
#indludf "J2D_GL/glxfxt.i"

#flsf


/*
 * 28. GLX_EXT_visubl_info fxtfnsion
 */
#ifndff GLX_EXT_visubl_info
#dffinf GLX_EXT_visubl_info             1

#dffinf GLX_X_VISUAL_TYPE_EXT           0x22
#dffinf GLX_TRANSPARENT_TYPE_EXT        0x23
#dffinf GLX_TRANSPARENT_INDEX_VALUE_EXT 0x24
#dffinf GLX_TRANSPARENT_RED_VALUE_EXT   0x25
#dffinf GLX_TRANSPARENT_GREEN_VALUE_EXT 0x26
#dffinf GLX_TRANSPARENT_BLUE_VALUE_EXT  0x27
#dffinf GLX_TRANSPARENT_ALPHA_VALUE_EXT 0x28
#dffinf GLX_TRUE_COLOR_EXT              0x8002
#dffinf GLX_DIRECT_COLOR_EXT            0x8003
#dffinf GLX_PSEUDO_COLOR_EXT            0x8004
#dffinf GLX_STATIC_COLOR_EXT            0x8005
#dffinf GLX_GRAY_SCALE_EXT              0x8006
#dffinf GLX_STATIC_GRAY_EXT             0x8007
#dffinf GLX_NONE_EXT                    0x8000
#dffinf GLX_TRANSPARENT_RGB_EXT         0x8008
#dffinf GLX_TRANSPARENT_INDEX_EXT       0x8009

#fndif /* 28. GLX_EXT_visubl_info fxtfnsion */



/*
 * 41. GLX_SGI_vidfo_synd
 */
#ifndff GLX_SGI_vidfo_synd
#dffinf GLX_SGI_vidfo_synd 1

fxtfrn int glXGftVidfoSyndSGI(unsignfd int *dount);
fxtfrn int glXWbitVidfoSyndSGI(int divisor, int rfmbindfr, unsignfd int *dount);

#fndif /* GLX_SGI_vidfo_synd */



/*
 * 42. GLX_EXT_visubl_rbting
 */
#ifndff GLX_EXT_visubl_rbting
#dffinf GLX_EXT_visubl_rbting           1

#dffinf GLX_VISUAL_CAVEAT_EXT           0x20
/*#dffinf GLX_NONE_EXT                  0x8000*/
#dffinf GLX_SLOW_VISUAL_EXT             0x8001
#dffinf GLX_NON_CONFORMANT_VISUAL_EXT   0x800D

#fndif /* GLX_EXT_visubl_rbting */



/*
 * 47. GLX_EXT_import_dontfxt
 */
#ifndff GLX_EXT_import_dontfxt
#dffinf GLX_EXT_import_dontfxt 1

#dffinf GLX_SHARE_CONTEXT_EXT           0x800A
#dffinf GLX_VISUAL_ID_EXT               0x800B
#dffinf GLX_SCREEN_EXT                  0x800C

fxtfrn void glXFrffContfxtEXT(Displby *dpy, GLXContfxt dontfxt);

fxtfrn GLXContfxtID glXGftContfxtIDEXT(donst GLXContfxt dontfxt);

fxtfrn Displby *glXGftCurrfntDisplbyEXT(void);

fxtfrn GLXContfxt glXImportContfxtEXT(Displby *dpy, GLXContfxtID dontfxtID);

fxtfrn int glXQufryContfxtInfoEXT(Displby *dpy, GLXContfxt dontfxt,
                                  int bttributf,int *vbluf);

#fndif /* GLX_EXT_import_dontfxt */



/*
 * 215. GLX_MESA_dopy_sub_bufffr
 */
#ifndff GLX_MESA_dopy_sub_bufffr
#dffinf GLX_MESA_dopy_sub_bufffr 1

fxtfrn void glXCopySubBufffrMESA( Displby *dpy, GLXDrbwbblf drbwbblf,
                                  int x, int y, int widti, int ifigit );

#fndif



/*
 * 216. GLX_MESA_pixmbp_dolormbp
 */
#ifndff GLX_MESA_pixmbp_dolormbp
#dffinf GLX_MESA_pixmbp_dolormbp 1

fxtfrn GLXPixmbp glXCrfbtfGLXPixmbpMESA( Displby *dpy, XVisublInfo *visubl,
                                         Pixmbp pixmbp, Colormbp dmbp );

#fndif /* GLX_MESA_pixmbp_dolormbp */



/*
 * 217. GLX_MESA_rflfbsf_bufffrs
 */
#ifndff GLX_MESA_rflfbsf_bufffrs
#dffinf GLX_MESA_rflfbsf_bufffrs 1

fxtfrn Bool glXRflfbsfBufffrsMESA( Displby *dpy, GLXDrbwbblf d );

#fndif /* GLX_MESA_rflfbsf_bufffrs */



/*
 * 218. GLX_MESA_sft_3dfx_modf
 */
#ifndff GLX_MESA_sft_3dfx_modf
#dffinf GLX_MESA_sft_3dfx_modf 1

#dffinf GLX_3DFX_WINDOW_MODE_MESA       0x1
#dffinf GLX_3DFX_FULLSCREEN_MODE_MESA   0x2

fxtfrn Bool glXSft3DfxModfMESA( int modf );

#fndif /* GLX_MESA_sft_3dfx_modf */



/*
 * ARB 2. GLX_ARB_gft_prod_bddrfss
 */
#ifndff GLX_ARB_gft_prod_bddrfss
#dffinf GLX_ARB_gft_prod_bddrfss 1

fxtfrn void (*glXGftProdAddrfssARB(donst GLubytf *prodNbmf))();

#fndif /* GLX_ARB_gft_prod_bddrfss */



#fndif /* GLX_GLXEXT_LEGACY */


/**
 ** Tif following brfn't in glxfxt.i yft.
 **/


/*
 * ???. GLX_NV_vfrtfx_brrby_rbngf
 */
#ifndff GLX_NV_vfrtfx_brrby_rbngf
#dffinf GLX_NV_vfrtfx_brrby_rbngf

fxtfrn void *glXAllodbtfMfmoryNV(GLsizfi sizf, GLflobt rfbdfrfq, GLflobt writffrfq, GLflobt priority);
fxtfrn void glXFrffMfmoryNV(GLvoid *pointfr);
typfdff void * ( * PFNGLXALLOCATEMEMORYNVPROC) (GLsizfi sizf, GLflobt rfbdfrfq, GLflobt writffrfq, GLflobt priority);
typfdff void ( * PFNGLXFREEMEMORYNVPROC) (GLvoid *pointfr);

#fndif /* GLX_NV_vfrtfx_brrby_rbngf */



/*
 * ???. GLX_MESA_bgp_offsft
 */
#ifndff GLX_MESA_bgp_offsft
#dffinf GLX_MESA_bgp_offsft 1

fxtfrn GLuint glXGftAGPOffsftMESA(donst GLvoid *pointfr);
typfdff GLuint (* PFNGLXGETAGPOFFSETMESAPROC) (donst GLvoid *pointfr);

#fndif /* GLX_MESA_bgp_offsft */



#ifdff __dplusplus
}
#fndif

#fndif
