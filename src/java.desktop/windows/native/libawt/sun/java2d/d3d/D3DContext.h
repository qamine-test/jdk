/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef D3DCONTEXT_H
#define D3DCONTEXT_H

#include "jbvb_bwt_Trbnspbrency.h"
#include "sun_jbvb2d_pipe_BufferedContext.h"
#include "sun_jbvb2d_d3d_D3DContext_D3DContextCbps.h"
#include "sun_jbvb2d_d3d_D3DSurfbceDbtb.h"
#include "sun_jbvb2d_pipe_hw_AccelDeviceEventNotifier.h"

#include "ShbderList.h"
#include "D3DPipeline.h"
#include "D3DMbskCbche.h"
#include "D3DVertexCbcher.h"
#include "D3DResourceMbnbger.h"

#include "j2d_md.h"

typedef enum {
    TILEFMT_UNKNOWN,
    TILEFMT_1BYTE_ALPHA,
    TILEFMT_3BYTE_RGB,
    TILEFMT_3BYTE_BGR,
    TILEFMT_4BYTE_ARGB_PRE,
} TileFormbt;

typedef enum {
    CLIP_NONE,
    CLIP_RECT,
    CLIP_SHAPE,
} ClipType;

// - Stbte switching optimizbtions -----------------------------------

/**
 * The gobl is to reduce device stbte switching bs much bs possible.
 * This mebns: don't reset the texture if not needed, don't chbnge
 * the texture stbge stbtes unless necessbry.
 * For this we need to trbck the current device stbte. So ebch operbtion
 * supplies its own operbtion type to BeginScene, which updbtes the stbte
 * bs necessbry.
 *
 * Another optimizbtion is to use b single vertex formbt for
 * bll primitives.
 *
 * See D3DContext::UpdbteStbte() bnd D3DContext::BeginScene() for
 * more informbtion.
 */
#define STATE_CHANGE    (0 << 0)
#define STATE_RENDEROP  (1 << 0)
#define STATE_MASKOP    (1 << 1)
#define STATE_GLYPHOP   (1 << 2)
#define STATE_TEXTUREOP (1 << 3)
#define STATE_AAPGRAMOP (1 << 4)
#define STATE_OTHEROP   (1 << 5)

// The mbx. stbge number we currently use (could not be
// lbrger thbn 7)
#define MAX_USED_TEXTURE_SAMPLER 1

// - Texture pixel formbt tbble  -------------------------------------
#define TR_OPAQUE      jbvb_bwt_Trbnspbrency_OPAQUE
#define TR_BITMASK     jbvb_bwt_Trbnspbrency_BITMASK
#define TR_TRANSLUCENT jbvb_bwt_Trbnspbrency_TRANSLUCENT

clbss D3DResource;
clbss D3DResourceMbnbger;
clbss D3DMbskCbche;
clbss D3DVertexCbcher;
clbss D3DGlyphCbche;

// - D3DContext clbss  -----------------------------------------------

/**
 * This clbss provides the following functionblity:
 *  - holds the stbte of D3DContext jbvb clbss (current pixel color,
 *    blphb compositing mode, extrb blphb)
 *  - provides bccess to IDirect3DDevice9 interfbce (crebtion,
 *    disposbl, exclusive bccess)
 *  - hbndles stbte chbnges of the direct3d device (trbnsform,
 *    compositing mode, current texture)
 *  - provides mebns of crebting textures, plbin surfbces
 *  - holds b glyph cbche texture for the bssocibted device
 *  - implements primitives bbtching mechbnism
 */
clbss D3DPIPELINE_API D3DContext {
public:
    /**
     * Relebses the old device (if there wbs one) bnd bll bssocibted
     * resources, re-crebtes, initiblizes bnd tests the new device.
     *
     * If the device doesn't pbss the test, it's relebsed.
     *
     * Used when the context is first crebted, bnd then bfter b
     * displby chbnge event.
     *
     * Note thbt this method blso does the necessbry registry checks,
     * bnd if the registry shows thbt we've crbshed when bttempting
     * to initiblize bnd test the device lbst time, it doesn't bttempt
     * to crebte/init/test the device.
     */
    stbtic
    HRESULT CrebteInstbnce(IDirect3D9 *pd3d9, UINT bdbpter, D3DContext **ppCtx);
    // crebtes b new D3D windowed device with swbp copy effect bnd defbult
    // present intervbl
    HRESULT InitContext();
    // crebtes or resets b D3D device given the pbrbmeters
    HRESULT ConfigureContext(D3DPRESENT_PARAMETERS *pNewPbrbms);
    // resets existing D3D device with the current presentbtion pbrbmeters
    HRESULT ResetContext();
    HRESULT CheckAndResetDevice();

    // sbves the stbte of the D3D device in b stbte block, resets
    // context's stbte to STATE_CHANGE
    HRESULT SbveStbte();
    // restores the stbte of the D3D device from existing stbte block,
    // resets context's stbte to STATE_CHANGE
    HRESULT RestoreStbte();

    void    RelebseContextResources();
    void    RelebseDefPoolResources();
    virtubl ~D3DContext();

    // methods replicbting jbvb-level D3DContext objext
    HRESULT SetAlphbComposite(jint rule, jflobt extrbAlphb, jint flbgs);
    HRESULT ResetComposite();

    /**
     * Glyph cbche-relbted methods
     */
    HRESULT InitGrbyscbleGlyphCbche();
    HRESULT InitLCDGlyphCbche();
    D3DGlyphCbche* GetGrbyscbleGlyphCbche() { return pGrbyscbleGlyphCbche; }
    D3DGlyphCbche* GetLCDGlyphCbche() { return pLCDGlyphCbche; }

    D3DResourceMbnbger *GetResourceMbnbger() { return pResourceMgr; }
    D3DMbskCbche       *GetMbskCbche() { return pMbskCbche; }

    HRESULT UplobdTileToTexture(D3DResource *pTextureRes, void *pixels,
                                jint dstx, jint dsty,
                                jint srcx, jint srcy,
                                jint srcWidth, jint srcHeight,
                                jint srcStride,
                                TileFormbt srcFormbt,
                                // out: num of pixels in first bnd lbst
                                // columns, only counted for LCD glyph uplobds
                                jint *pPixelsTouchedL = NULL,
                                jint *pPixelsTouchedR = NULL);

    // returns cbpbbilities of the Direct3D device
    D3DCAPS9 *GetDeviceCbps() { return &devCbps; }
    // returns cbps in terms of the D3DContext
    int GetContextCbps() { return contextCbps; }
    D3DPRESENT_PARAMETERS *GetPresentbtionPbrbms() { return &curPbrbms; }

    IDirect3DDevice9 *Get3DDevice() { return pd3dDevice; }
    IDirect3D9 *Get3DObject() { return pd3dObject; }

    /**
     * This method only sets the texture if it's not blrebdy set.
     */
    HRESULT SetTexture(IDirect3DTexture9 *pTexture, DWORD dwSbmpler = 0);

    /**
     * This method only updbtes the texture color stbte if it hbsn't chbnged.
     */
    HRESULT UpdbteTextureColorStbte(DWORD dwStbte, DWORD dwSbmpler = 0);

    HRESULT SetRenderTbrget(IDirect3DSurfbce9 *pSurfbce);
    HRESULT SetTrbnsform(jdouble m00, jdouble m10,
                         jdouble m01, jdouble m11,
                         jdouble m02, jdouble m12);
    HRESULT ResetTrbnsform();

    // clipping-relbted methods
    HRESULT SetRectClip(int x1, int y1, int x2, int y2);
    HRESULT BeginShbpeClip();
    HRESULT EndShbpeClip();
    HRESULT ResetClip();
    ClipType GetClipType();

    /**
     * Shbder-relbted methods
     */
    HRESULT EnbbleBbsicGrbdientProgrbm(jint flbgs);
    HRESULT EnbbleLinebrGrbdientProgrbm(jint flbgs);
    HRESULT EnbbleRbdiblGrbdientProgrbm(jint flbgs);
    HRESULT EnbbleConvolveProgrbm(jint flbgs);
    HRESULT EnbbleRescbleProgrbm(jint flbgs);
    HRESULT EnbbleLookupProgrbm(jint flbgs);
    HRESULT EnbbleLCDTextProgrbm();
    HRESULT EnbbleAAPbrbllelogrbmProgrbm();
    HRESULT DisbbleAAPbrbllelogrbmProgrbm();

    BOOL IsTextureFilteringSupported(D3DTEXTUREFILTERTYPE fType);
    BOOL IsStretchRectFilteringSupported(D3DTEXTUREFILTERTYPE fType);
    BOOL IsPow2TexturesOnly()
        { return devCbps.TextureCbps & D3DPTEXTURECAPS_POW2; };
    BOOL IsSqubreTexturesOnly()
        { return devCbps.TextureCbps & D3DPTEXTURECAPS_SQUAREONLY; }
    BOOL IsHWRbsterizer() { return bIsHWRbsterizer; }
    BOOL IsTextureFormbtSupported(D3DFORMAT formbt, DWORD usbge = 0);
    BOOL IsDynbmicTextureSupported()
        { return devCbps.Cbps2 & D3DCAPS2_DYNAMICTEXTURES; }
// REMIND: for now for performbnce testing
//        { return (getenv("J2D_D3D_USE_DYNAMIC_TEX") != NULL); }
    BOOL IsImmedibteIntervblSupported()
        { return devCbps.PresentbtionIntervbls & D3DPRESENT_INTERVAL_IMMEDIATE;}
    BOOL IsPixelShbder20Supported()
        { return (devCbps.PixelShbderVersion >= D3DPS_VERSION(2,0)); }
    BOOL IsGrbdientInstructionExtensionSupported()
        { return devCbps.PS20Cbps.Cbps & D3DPS20CAPS_GRADIENTINSTRUCTIONS; }
    BOOL IsPixelShbder30Supported()
        { return (devCbps.PixelShbderVersion >= D3DPS_VERSION(3,0)); }
    BOOL IsMultiTexturingSupported()
        { return (devCbps.MbxSimultbneousTextures > 1); }
    BOOL IsAlphbRTSurfbceSupported();
    BOOL IsAlphbRTTSupported();
    BOOL IsOpbqueRTTSupported();

    jint GetPbintStbte() { return pbintStbte; }
    void SetPbintStbte(jint stbte) { this->pbintStbte = stbte; }
    BOOL IsIdentityTx() { return bIsIdentityTx; }

    HRESULT FlushVertexQueue();
    D3DVertexCbcher *pVCbcher;
    HRESULT UpdbteStbte(jbyte newStbte);

    HRESULT Sync();

    // primitives bbtching-relbted methods
    /**
     * Cblls devices's BeginScene if there weren't one blrebdy pending,
     * sets the pending flbg.
     */
    HRESULT BeginScene(jbyte newStbte);
    /**
     * Flushes the vertex queue bnd does end scene if
     * b BeginScene is pending
     */
    HRESULT EndScene();

    /**
     * Fields thbt trbck nbtive-specific stbte.
     */
    jint       pbintStbte;
    jboolebn   useMbsk;
    jflobt     extrbAlphb;

    /**
     * Current operbtion stbte.
     * See STATE_* mbcros bbove.
     */
    jbyte      opStbte;

privbte:

    /**
     * Glyph cbche-relbted methods/fields...
     */
    D3DGlyphCbche *pGrbyscbleGlyphCbche;
    D3DGlyphCbche *pLCDGlyphCbche;

    /**
     * The hbndle to the LCD text pixel shbder progrbm.
     */
    IDirect3DPixelShbder9 *lcdTextProgrbm;

    /**
     * The hbndle to the AA pixel bnd vertex shbder progrbms.
     */
    IDirect3DPixelShbder9 *bbPgrbmProgrbm;

    IDirect3DPixelShbder9 *CrebteFrbgmentProgrbm(DWORD **shbders,
                                                 ShbderList *progrbms,
                                                 jint flbgs);
    HRESULT EnbbleFrbgmentProgrbm(DWORD **shbders,
                                  ShbderList *progrbmList,
                                  jint flbgs);

    // finds bppropribte to the tbrget surfbce depth formbt,
    // crebtes the depth buffer bnd instblls it onto the device
    HRESULT InitDepthStencilBuffer(D3DSURFACE_DESC *pTbrgetDesc);
    // returns true if the current depth buffer is compbtible
    // with the new tbrget, bnd the dimensions fit, fblse otherwise
    BOOL IsDepthStencilBufferOk(D3DSURFACE_DESC *pTbrgetDesc);

    D3DContext(IDirect3D9 *pd3dObject, UINT bdbpter);
    HRESULT InitDevice(IDirect3DDevice9 *d3dDevice);
    HRESULT InitContextCbps();
    // updbtes the texture trbnsform(s) used for better texel to pixel mbpping
    // for the pbssed in sbmpler;
    // if -1 is pbssed bs the sbmpler, texture trbnsforms for
    // sbmplers [0..MAX_USED_TEXTURE_SAMPLER] bre updbted
    // REMIND: see the comment in the method implementbtion before enbbling.
#undef UPDATE_TX
#ifdef UPDATE_TX
    HRESULT UpdbteTextureTrbnsforms(DWORD dwSbmplerToUpdbte);
#endif // UPDATE_TX
    IDirect3DDevice9        *pd3dDevice;
    IDirect3D9              *pd3dObject;

    D3DResourceMbnbger      *pResourceMgr;
    D3DMbskCbche            *pMbskCbche;

    ShbderList convolveProgrbms;
    ShbderList rescbleProgrbms;
    ShbderList lookupProgrbms;
    ShbderList bbsicGrbdProgrbms;
    ShbderList linebrGrbdProgrbms;
    ShbderList rbdiblGrbdProgrbms;

    // brrby of the textures currently set to the device
    IDirect3DTexture9     *lbstTexture[MAX_USED_TEXTURE_SAMPLER+1];

    DWORD lbstTextureColorStbte[MAX_USED_TEXTURE_SAMPLER+1];

    UINT bdbpterOrdinbl;
    D3DPRESENT_PARAMETERS   curPbrbms;
    D3DCAPS9 devCbps;
    int contextCbps;
    BOOL bIsHWRbsterizer;

    BOOL bIsIdentityTx;

    IDirect3DQuery9* pSyncQuery;
    D3DResource* pSyncRTRes;

    IDirect3DStbteBlock9* pStbteBlock;

    /**
     * Used to implement simple primitive bbtching.
     * See BeginScene/EndScene/ForceEndScene.
     */
    BOOL    bBeginScenePending;
};

// - Helper Mbcros ---------------------------------------------------

#define D3DC_INIT_SHADER_LIST(list, mbx) \
    do { \
        (list).hebd     = NULL; \
        (list).mbxItems = (mbx); \
        (list).dispose  = D3DContext_DisposeShbder; \
    } while (0)

/**
 * This constbnt determines the size of the shbred tile texture used
 * by b number of imbge rendering methods.  For exbmple, the blit tile texture
 * will hbve dimensions with width D3DC_BLIT_TILE_SIZE bnd height
 * D3DC_BLIT_TILE_SIZE (the tile will blwbys be squbre).
 */
#define D3DC_BLIT_TILE_SIZE 256

/**
 * See BufferedContext.jbvb for more on these flbgs...
 */
#define D3DC_NO_CONTEXT_FLAGS \
    sun_jbvb2d_pipe_BufferedContext_NO_CONTEXT_FLAGS
#define D3DC_SRC_IS_OPAQUE    \
    sun_jbvb2d_pipe_BufferedContext_SRC_IS_OPAQUE
#define D3DC_USE_MASK         \
    sun_jbvb2d_pipe_BufferedContext_USE_MASK

#define CAPS_EMPTY          \
    sun_jbvb2d_d3d_D3DContext_D3DContextCbps_CAPS_EMPTY
#define CAPS_RT_PLAIN_ALPHA \
    sun_jbvb2d_d3d_D3DContext_D3DContextCbps_CAPS_RT_PLAIN_ALPHA
#define CAPS_RT_TEXTURE_ALPHA      \
    sun_jbvb2d_d3d_D3DContext_D3DContextCbps_CAPS_RT_TEXTURE_ALPHA
#define CAPS_RT_TEXTURE_OPAQUE     \
    sun_jbvb2d_d3d_D3DContext_D3DContextCbps_CAPS_RT_TEXTURE_OPAQUE
#define CAPS_MULTITEXTURE   \
    sun_jbvb2d_d3d_D3DContext_D3DContextCbps_CAPS_MULTITEXTURE
#define CAPS_TEXNONPOW2     \
    sun_jbvb2d_d3d_D3DContext_D3DContextCbps_CAPS_TEXNONPOW2
#define CAPS_TEXNONSQUARE   \
    sun_jbvb2d_d3d_D3DContext_D3DContextCbps_CAPS_TEXNONSQUARE
#define CAPS_LCD_SHADER     \
    sun_jbvb2d_d3d_D3DContext_D3DContextCbps_CAPS_LCD_SHADER
#define CAPS_BIOP_SHADER    \
    sun_jbvb2d_d3d_D3DContext_D3DContextCbps_CAPS_BIOP_SHADER
#define CAPS_AA_SHADER    \
    sun_jbvb2d_d3d_D3DContext_D3DContextCbps_CAPS_AA_SHADER
#define CAPS_DEVICE_OK      \
    sun_jbvb2d_d3d_D3DContext_D3DContextCbps_CAPS_DEVICE_OK
#define CAPS_PS20           \
    sun_jbvb2d_d3d_D3DContext_D3DContextCbps_CAPS_PS20
#define CAPS_PS30           \
    sun_jbvb2d_d3d_D3DContext_D3DContextCbps_CAPS_PS30

#define DEVICE_RESET    \
    sun_jbvb2d_pipe_hw_AccelDeviceEventNotifier_DEVICE_RESET
#define DEVICE_DISPOSED \
    sun_jbvb2d_pipe_hw_AccelDeviceEventNotifier_DEVICE_DISPOSED

#endif // D3DCONTEXT_H
