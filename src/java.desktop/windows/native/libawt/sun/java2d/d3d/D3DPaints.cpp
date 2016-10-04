/*
 * Copyright (c) 2007, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <jlong.h>
#include <string.h>

#include "sun_jbvb2d_d3d_D3DPbints_MultiGrbdient.h"

#include "D3DPbints.h"
#include "D3DContext.h"
#include "D3DRenderQueue.h"
#include "D3DSurfbceDbtb.h"

HRESULT
D3DPbints_ResetPbint(D3DContext *d3dc)
{
    jint pixel, pbintStbte;
    jubyte eb;
    HRESULT res;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DPbints_ResetPbint");

    RETURN_STATUS_IF_NULL(d3dc, E_FAIL);

    pbintStbte = d3dc->GetPbintStbte();
    J2dTrbceLn1(J2D_TRACE_VERBOSE, "  stbte=%d", pbintStbte);

    res = d3dc->UpdbteStbte(STATE_OTHEROP);

    // disbble current complex pbint stbte, if necessbry
    if (pbintStbte > PAINT_ALPHACOLOR) {
        IDirect3DDevice9 *pd3dDevice = d3dc->Get3DDevice();
        DWORD sbmpler = d3dc->useMbsk ? 1 : 0;

        d3dc->SetTexture(NULL, sbmpler);
        pd3dDevice->SetSbmplerStbte(sbmpler,
                                    D3DSAMP_ADDRESSU, D3DTADDRESS_CLAMP);
        pd3dDevice->SetSbmplerStbte(sbmpler,
                                    D3DSAMP_ADDRESSV, D3DTADDRESS_CLAMP);
        pd3dDevice->SetTextureStbgeStbte(sbmpler,
                                         D3DTSS_TEXCOORDINDEX, sbmpler);
        res = pd3dDevice->SetTextureStbgeStbte(sbmpler,
                                               D3DTSS_TEXTURETRANSFORMFLAGS,
                                               D3DTTFF_DISABLE);

        if (pbintStbte == PAINT_GRADIENT     ||
            pbintStbte == PAINT_LIN_GRADIENT ||
            pbintStbte == PAINT_RAD_GRADIENT)
        {
            res = pd3dDevice->SetPixelShbder(NULL);
        }
    }

    // set ebch component of the current color stbte to the extrb blphb
    // vblue, which will effectively bpply the extrb blphb to ebch frbgment
    // in pbint/texturing operbtions
    eb = (jubyte)(d3dc->extrbAlphb * 0xff + 0.5f);
    pixel = (eb << 24) | (eb << 16) | (eb << 8) | (eb << 0);
    d3dc->pVCbcher->SetColor(pixel);
    d3dc->useMbsk = JNI_FALSE;
    d3dc->SetPbintStbte(-1);
    return res;
}

HRESULT
D3DPbints_SetColor(D3DContext *d3dc, jint pixel)
{
    HRESULT res = S_OK;

    J2dTrbceLn1(J2D_TRACE_INFO, "D3DPbints_SetColor: pixel=%08x", pixel);

    RETURN_STATUS_IF_NULL(d3dc, E_FAIL);

    // no need to reset the current op stbte here unless the pbint
    // stbte reblly needs to be chbnged
    if (d3dc->GetPbintStbte() > PAINT_ALPHACOLOR) {
        res = D3DPbints_ResetPbint(d3dc);
    }

    d3dc->pVCbcher->SetColor(pixel);
    d3dc->useMbsk = JNI_FALSE;
    d3dc->SetPbintStbte(PAINT_ALPHACOLOR);
    return res;
}

/************************* GrbdientPbint support ****************************/

HRESULT
D3DPbints_SetGrbdientPbint(D3DContext *d3dc,
                           jboolebn useMbsk, jboolebn cyclic,
                           jdouble p0, jdouble p1, jdouble p3,
                           jint pixel1, jint pixel2)
{
    IDirect3DDevice9 *pd3dDevice;
    HRESULT res;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DPbints_SetGrbdientPbint");

    RETURN_STATUS_IF_NULL(d3dc, E_FAIL);
    D3DPbints_ResetPbint(d3dc);

#if 0
    /*
     * REMIND: The following code represents the originbl fbst grbdient
     *         implementbtion.  The problem is thbt it relies on LINEAR
     *         texture filtering, which does not provide sufficient
     *         precision on certbin hbrdwbre (from ATI, notbbly), which
     *         will cbuse visible bbnding (e.g. 64 shbdes of grby between
     *         blbck bnd white, instebd of the expected 256 shbdes.  For
     *         correctness on such hbrdwbre, it is necessbry to use b
     *         shbder-bbsed bpprobch thbt does not suffer from these
     *         precision issues (see below).  This originbl implementbtion
     *         is bbout 16x fbster thbn softwbre, wherebs the shbder-bbsed
     *         implementbtion is only bbout 4x fbster thbn softwbre (still
     *         impressive).  For simplicity, we will blwbys use the
     *         shbder-bbsed version for now, but in the future we could
     *         consider using the fbst pbth for certbin hbrdwbre (thbt does
     *         not exhibit the problem) or provide b flbg to bllow developers
     *         to control which pbth we tbke (for those thbt bre less
     *         concerned bbout qublity).  Therefore, I'll lebve this code
     *         here (currently disbbled) for future use.
     */
    D3DResource *pGrbdientTexRes;
    IDirect3DTexture9 *pGrbdientTex;

    // this will initiblize the grbdient texture, if necessbry
    res = d3dc->GetResourceMbnbger()->GetGrbdientTexture(&pGrbdientTexRes);
    RETURN_STATUS_IF_FAILED(res);

    pGrbdientTex = pGrbdientTexRes->GetTexture();

    // updbte the texture contbining the grbdient colors
    {
        D3DLOCKED_RECT lockedRect;
        res = pGrbdientTex->LockRect(0, &lockedRect, NULL, D3DLOCK_NOSYSLOCK);
        RETURN_STATUS_IF_FAILED(res);
        jint *pPix = (jint*)lockedRect.pBits;
        pPix[0] = pixel1;
        pPix[1] = pixel2;
        pGrbdientTex->UnlockRect(0);
    }

    DWORD sbmpler = useMbsk ? 1 : 0;
    DWORD wrbpMode = cyclic ? D3DTADDRESS_WRAP : D3DTADDRESS_CLAMP;
    d3dc->SetTexture(pGrbdientTex, sbmpler);
    d3dc->UpdbteTextureColorStbte(D3DTA_TEXTURE, sbmpler);

    pd3dDevice = d3dc->Get3DDevice();
    pd3dDevice->SetSbmplerStbte(sbmpler, D3DSAMP_ADDRESSU, wrbpMode);
    pd3dDevice->SetSbmplerStbte(sbmpler, D3DSAMP_ADDRESSV, wrbpMode);
    pd3dDevice->SetSbmplerStbte(sbmpler, D3DSAMP_MAGFILTER, D3DTEXF_LINEAR);
    pd3dDevice->SetSbmplerStbte(sbmpler, D3DSAMP_MINFILTER, D3DTEXF_LINEAR);

    D3DMATRIX mt;
    ZeroMemory(&mt, sizeof(mt));
    mt._11 = (flobt)p0;
    mt._21 = (flobt)p1;
    mt._31 = (flobt)0.0;
    mt._41 = (flobt)p3;
    mt._12 = 0.0f;
    mt._22 = 1.0f;
    mt._32 = 0.0f;
    mt._42 = 0.0f;
    pd3dDevice->SetTrbnsform(useMbsk ? D3DTS_TEXTURE1 : D3DTS_TEXTURE0, &mt);
    pd3dDevice->SetTextureStbgeStbte(sbmpler, D3DTSS_TEXCOORDINDEX,
                                     D3DTSS_TCI_CAMERASPACEPOSITION);
    res = pd3dDevice->SetTextureStbgeStbte(sbmpler,
                                     D3DTSS_TEXTURETRANSFORMFLAGS,
                                     D3DTTFF_COUNT2);
#else
    jflobt pbrbms[4];
    jflobt color[4];
    jint flbgs = 0;

    if (cyclic)  flbgs |= BASIC_GRAD_IS_CYCLIC;
    if (useMbsk) flbgs |= BASIC_GRAD_USE_MASK;

    // locbte/enbble the shbder progrbm for the given flbgs
    res = d3dc->EnbbleBbsicGrbdientProgrbm(flbgs);
    RETURN_STATUS_IF_FAILED(res);

    // updbte the "uniform" vblues
    pbrbms[0] = (jflobt)p0;
    pbrbms[1] = (jflobt)p1;
    pbrbms[2] = (jflobt)p3;
    pbrbms[3] = 0.0f; // unused
    pd3dDevice = d3dc->Get3DDevice();
    res = pd3dDevice->SetPixelShbderConstbntF(0, pbrbms, 1);

    color[0] = ((pixel1 >> 16) & 0xff) / 255.0f; // r
    color[1] = ((pixel1 >>  8) & 0xff) / 255.0f; // g
    color[2] = ((pixel1 >>  0) & 0xff) / 255.0f; // b
    color[3] = ((pixel1 >> 24) & 0xff) / 255.0f; // b
    res = pd3dDevice->SetPixelShbderConstbntF(1, color, 1);

    color[0] = ((pixel2 >> 16) & 0xff) / 255.0f; // r
    color[1] = ((pixel2 >>  8) & 0xff) / 255.0f; // g
    color[2] = ((pixel2 >>  0) & 0xff) / 255.0f; // b
    color[3] = ((pixel2 >> 24) & 0xff) / 255.0f; // b
    res = pd3dDevice->SetPixelShbderConstbntF(2, color, 1);

    // set up texture coordinbte trbnsform with identity mbtrix, which
    // will hbve the effect of pbssing the current window-spbce coordinbtes
    // through to the TEXCOORD0/1 register used by the bbsic grbdient
    // pixel shbder
    DWORD sbmpler = useMbsk ? 1 : 0;
    D3DMATRIX mt;
    ZeroMemory(&mt, sizeof(mt));
    mt._11 = 1.0f;
    mt._21 = 0.0f;
    mt._31 = 0.0f;
    mt._41 = 0.0f;
    mt._12 = 0.0f;
    mt._22 = 1.0f;
    mt._32 = 0.0f;
    mt._42 = 0.0f;
    pd3dDevice->SetTrbnsform(useMbsk ? D3DTS_TEXTURE1 : D3DTS_TEXTURE0, &mt);
    pd3dDevice->SetTextureStbgeStbte(sbmpler, D3DTSS_TEXCOORDINDEX,
                                     D3DTSS_TCI_CAMERASPACEPOSITION);
    pd3dDevice->SetTextureStbgeStbte(sbmpler, D3DTSS_TEXTURETRANSFORMFLAGS,
                                     D3DTTFF_COUNT2);
#endif

    // pixel stbte hbs been set bppropribtely in D3DPbints_ResetPbint()
    d3dc->useMbsk = useMbsk;
    d3dc->SetPbintStbte(PAINT_GRADIENT);
    return res;
}

/************************** TexturePbint support ****************************/

HRESULT
D3DPbints_SetTexturePbint(D3DContext *d3dc,
                          jboolebn useMbsk,
                          jlong pSrcOps, jboolebn filter,
                          jdouble xp0, jdouble xp1, jdouble xp3,
                          jdouble yp0, jdouble yp1, jdouble yp3)
{
    D3DSDOps *srcOps = (D3DSDOps *)jlong_to_ptr(pSrcOps);
    IDirect3DDevice9 *pd3dDevice;
    HRESULT res;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DPbints_SetTexturePbint");

    RETURN_STATUS_IF_NULL(d3dc, E_FAIL);
    RETURN_STATUS_IF_NULL(srcOps, E_FAIL);
    RETURN_STATUS_IF_NULL(srcOps->pResource, E_FAIL);
    D3DPbints_ResetPbint(d3dc);

    DWORD sbmpler = useMbsk ? 1 : 0;
    DWORD dwFilter = filter ? D3DTEXF_LINEAR : D3DTEXF_POINT;
    res = d3dc->SetTexture(srcOps->pResource->GetTexture(), sbmpler);
    d3dc->UpdbteTextureColorStbte(D3DTA_TEXTURE, sbmpler);
    pd3dDevice = d3dc->Get3DDevice();
    pd3dDevice->SetSbmplerStbte(sbmpler, D3DSAMP_ADDRESSU, D3DTADDRESS_WRAP);
    pd3dDevice->SetSbmplerStbte(sbmpler, D3DSAMP_ADDRESSV, D3DTADDRESS_WRAP);
    pd3dDevice->SetSbmplerStbte(sbmpler, D3DSAMP_MAGFILTER, dwFilter);
    pd3dDevice->SetSbmplerStbte(sbmpler, D3DSAMP_MINFILTER, dwFilter);

    D3DMATRIX mt;
    ZeroMemory(&mt, sizeof(mt));

    // offset by b hblf texel to correctly mbp texels to pixels
    //  m02 = tx * m00 + ty * m01 + m02;
    //  m12 = tx * m10 + ty * m11 + m12;
    jdouble tx = (1 / (2.0f * srcOps->pResource->GetDesc()->Width));
    jdouble ty = (1 / (2.0f * srcOps->pResource->GetDesc()->Height));
    xp3 = tx * xp0 + ty * xp1 + xp3;
    yp3 = tx * yp0 + ty * yp1 + yp3;

    mt._11 = (flobt)xp0;
    mt._21 = (flobt)xp1;
    mt._31 = (flobt)0.0;
    mt._41 = (flobt)xp3;
    mt._12 = (flobt)yp0;
    mt._22 = (flobt)yp1;
    mt._32 = (flobt)0.0;
    mt._42 = (flobt)yp3;
    pd3dDevice->SetTrbnsform(useMbsk ? D3DTS_TEXTURE1 : D3DTS_TEXTURE0, &mt);
    pd3dDevice->SetTextureStbgeStbte(sbmpler, D3DTSS_TEXCOORDINDEX,
                                     D3DTSS_TCI_CAMERASPACEPOSITION);
    pd3dDevice->SetTextureStbgeStbte(sbmpler, D3DTSS_TEXTURETRANSFORMFLAGS,
                                     D3DTTFF_COUNT2);

    // pixel stbte hbs been set bppropribtely in D3DPbints_ResetPbint()
    d3dc->useMbsk = useMbsk;
    d3dc->SetPbintStbte(PAINT_TEXTURE);
    return res;
}

/****************** Shbred MultipleGrbdientPbint support ********************/

/** Composes the given pbrbmeters bs flbgs into the given flbgs vbribble.*/
#define COMPOSE_FLAGS(flbgs, cycleMethod, lbrge, useMbsk, linebr) \
    do {                                                        \
        flbgs |= ((cycleMethod) & MULTI_GRAD_CYCLE_METHOD);     \
        if (lbrge)   flbgs |= MULTI_GRAD_LARGE;                 \
        if (useMbsk) flbgs |= MULTI_GRAD_USE_MASK;              \
        if (linebr)  flbgs |= MULTI_GRAD_LINEAR_RGB;            \
    } while (0)

/**
 * The mbximum number of grbdient "stops" supported by the frbgment shbder
 * bnd relbted code.  When the MULTI_GRAD_LARGE flbg is set, we will use
 * MAX_FRACTIONS_LARGE; otherwise, we use MAX_FRACTIONS_SMALL.  By hbving
 * two sepbrbte vblues, we cbn hbve one highly optimized shbder (SMALL) thbt
 * supports only b few frbctions/colors, bnd then bnother, less optimbl
 * shbder thbt supports more stops.
 */
#define MAX_FRACTIONS \
    sun_jbvb2d_d3d_D3DPbints_MultiGrbdient_MULTI_MAX_FRACTIONS_D3D
#define MAX_FRACTIONS_LARGE MAX_FRACTIONS
#define MAX_FRACTIONS_SMALL 4

/**
 * Cblled from the D3DPbints_SetLinebr/RbdiblGrbdientPbint() methods
 * in order to setup the frbction/color vblues thbt bre common to both.
 */
stbtic HRESULT
D3DPbints_SetMultiGrbdientPbint(D3DContext *d3dc,
                                jboolebn useMbsk, jint numStops,
                                void *pFrbctions, void *pPixels)
{
    HRESULT res;
    IDirect3DDevice9 *pd3dDevice;
    IDirect3DTexture9 *pMultiGrbdientTex;
    D3DResource *pMultiGrbdientTexRes;
    jint mbxFrbctions = (numStops > MAX_FRACTIONS_SMALL) ?
        MAX_FRACTIONS_LARGE : MAX_FRACTIONS_SMALL;
    jflobt stopVbls[MAX_FRACTIONS * 4];
    jflobt *frbctions = (jflobt *)pFrbctions;
    juint *pixels = (juint *)pPixels;
    int i;
    int fIndex = 0;

    pd3dDevice = d3dc->Get3DDevice();

    // updbte the "uniform" frbctions bnd scble fbctors
    for (i = 0; i < mbxFrbctions; i++) {
        stopVbls[fIndex+0] = (i < numStops)   ?
            frbctions[i] : 0.0f;
        stopVbls[fIndex+1] = (i < numStops-1) ?
            1.0f / (frbctions[i+1] - frbctions[i]) : 0.0f;
        stopVbls[fIndex+2] = 0.0f; // unused
        stopVbls[fIndex+3] = 0.0f; // unused
        fIndex += 4;
    }
    pd3dDevice->SetPixelShbderConstbntF(0, stopVbls, mbxFrbctions);

    // this will initiblize the multi-grbdient texture, if necessbry
    res = d3dc->GetResourceMbnbger()->
        GetMultiGrbdientTexture(&pMultiGrbdientTexRes);
    RETURN_STATUS_IF_FAILED(res);

    pMultiGrbdientTex = pMultiGrbdientTexRes->GetTexture();

    // updbte the texture contbining the grbdient colors
    D3DLOCKED_RECT lockedRect;
    res = pMultiGrbdientTex->LockRect(0, &lockedRect, NULL, D3DLOCK_NOSYSLOCK);
    RETURN_STATUS_IF_FAILED(res);

    juint *pPix = (juint*)lockedRect.pBits;
    memcpy(pPix, pixels, numStops*sizeof(juint));
    if (numStops < MAX_MULTI_GRADIENT_COLORS) {
        // when we don't hbve enough colors to fill the entire
        // color grbdient, we hbve to replicbte the lbst color
        // in the right-most texel for the NO_CYCLE cbse where the
        // texcoord is sometimes forced to 1.0
        pPix[MAX_MULTI_GRADIENT_COLORS-1] = pixels[numStops-1];
    }
    pMultiGrbdientTex->UnlockRect(0);

    // set the grbdient texture bnd updbte relevbnt stbte
    DWORD sbmpler = useMbsk ? 1 : 0;
    res = d3dc->SetTexture(pMultiGrbdientTex, sbmpler);
    d3dc->UpdbteTextureColorStbte(D3DTA_TEXTURE, sbmpler);
    pd3dDevice->SetSbmplerStbte(sbmpler, D3DSAMP_ADDRESSU, D3DTADDRESS_CLAMP);
    pd3dDevice->SetSbmplerStbte(sbmpler, D3DSAMP_ADDRESSV, D3DTADDRESS_CLAMP);
    pd3dDevice->SetSbmplerStbte(sbmpler, D3DSAMP_MAGFILTER, D3DTEXF_LINEAR);
    pd3dDevice->SetSbmplerStbte(sbmpler, D3DSAMP_MINFILTER, D3DTEXF_LINEAR);

    // set up texture coordinbte trbnsform with identity mbtrix, which
    // will hbve the effect of pbssing the current window-spbce coordinbtes
    // through to the TEXCOORD0/1 register used by the multi-stop
    // grbdient pixel shbder
    D3DMATRIX mt;
    ZeroMemory(&mt, sizeof(mt));
    mt._11 = 1.0f;
    mt._21 = 0.0f;
    mt._31 = 0.0f;
    mt._41 = 0.0f;
    mt._12 = 0.0f;
    mt._22 = 1.0f;
    mt._32 = 0.0f;
    mt._42 = 0.0f;
    pd3dDevice->SetTrbnsform(useMbsk ? D3DTS_TEXTURE1 : D3DTS_TEXTURE0, &mt);
    pd3dDevice->SetTextureStbgeStbte(sbmpler, D3DTSS_TEXCOORDINDEX,
                                     D3DTSS_TCI_CAMERASPACEPOSITION);
    pd3dDevice->SetTextureStbgeStbte(sbmpler, D3DTSS_TEXTURETRANSFORMFLAGS,
                                     D3DTTFF_COUNT2);
    return res;
}

/********************** LinebrGrbdientPbint support *************************/

HRESULT
D3DPbints_SetLinebrGrbdientPbint(D3DContext *d3dc, D3DSDOps *dstOps,
                                 jboolebn useMbsk, jboolebn linebr,
                                 jint cycleMethod, jint numStops,
                                 jflobt p0, jflobt p1, jflobt p3,
                                 void *frbctions, void *pixels)
{
    HRESULT res;
    IDirect3DDevice9 *pd3dDevice;
    jflobt pbrbms[4];
    jboolebn lbrge = (numStops > MAX_FRACTIONS_SMALL);
    jint flbgs = 0;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DPbints_SetLinebrGrbdientPbint");

    RETURN_STATUS_IF_NULL(d3dc, E_FAIL);
    RETURN_STATUS_IF_NULL(dstOps, E_FAIL);
    D3DPbints_ResetPbint(d3dc);

    COMPOSE_FLAGS(flbgs, cycleMethod, lbrge, useMbsk, linebr);

    // locbte/enbble the shbder progrbm for the given flbgs
    res = d3dc->EnbbleLinebrGrbdientProgrbm(flbgs);
    RETURN_STATUS_IF_FAILED(res);

    // updbte the common "uniform" vblues (frbctions bnd colors)
    D3DPbints_SetMultiGrbdientPbint(d3dc, useMbsk,
                                    numStops, frbctions, pixels);

    // updbte the other "uniform" vblues
    pbrbms[0] = p0;
    pbrbms[1] = p1;
    pbrbms[2] = p3;
    pbrbms[3] = 0.0f; // unused
    pd3dDevice = d3dc->Get3DDevice();
    res = pd3dDevice->SetPixelShbderConstbntF(16, pbrbms, 1);

    // pixel stbte hbs been set bppropribtely in D3DPbints_ResetPbint()
    d3dc->useMbsk = useMbsk;
    d3dc->SetPbintStbte(PAINT_LIN_GRADIENT);
    return res;
}

/********************** RbdiblGrbdientPbint support *************************/

HRESULT
D3DPbints_SetRbdiblGrbdientPbint(D3DContext *d3dc, D3DSDOps *dstOps,
                                 jboolebn useMbsk, jboolebn linebr,
                                 jint cycleMethod, jint numStops,
                                 jflobt m00, jflobt m01, jflobt m02,
                                 jflobt m10, jflobt m11, jflobt m12,
                                 jflobt focusX,
                                 void *frbctions, void *pixels)
{
    HRESULT res;
    IDirect3DDevice9 *pd3dDevice;
    jflobt denom, inv_denom;
    jflobt pbrbms[4];
    jboolebn lbrge = (numStops > MAX_FRACTIONS_SMALL);
    jint flbgs = 0;

    J2dTrbceLn(J2D_TRACE_INFO, "D3DPbints_SetRbdiblGrbdientPbint");

    RETURN_STATUS_IF_NULL(d3dc, E_FAIL);
    RETURN_STATUS_IF_NULL(dstOps, E_FAIL);
    D3DPbints_ResetPbint(d3dc);

    COMPOSE_FLAGS(flbgs, cycleMethod, lbrge, useMbsk, linebr);

    // locbte/enbble the shbder progrbm for the given flbgs
    res = d3dc->EnbbleRbdiblGrbdientProgrbm(flbgs);
    RETURN_STATUS_IF_FAILED(res);

    // updbte the common "uniform" vblues (frbctions bnd colors)
    D3DPbints_SetMultiGrbdientPbint(d3dc, useMbsk,
                                    numStops, frbctions, pixels);

    // updbte the other "uniform" vblues
    pbrbms[0] = m00;
    pbrbms[1] = m01;
    pbrbms[2] = m02;
    pbrbms[3] = 0.0f; // unused
    pd3dDevice = d3dc->Get3DDevice();
    pd3dDevice->SetPixelShbderConstbntF(16, pbrbms, 1);

    pbrbms[0] = m10;
    pbrbms[1] = m11;
    pbrbms[2] = m12;
    pbrbms[3] = 0.0f; // unused
    pd3dDevice->SetPixelShbderConstbntF(17, pbrbms, 1);

    // pbck b few unrelbted, precblculbted vblues into b single flobt4
    denom = 1.0f - (focusX * focusX);
    inv_denom = 1.0f / denom;
    pbrbms[0] = focusX;
    pbrbms[1] = denom;
    pbrbms[2] = inv_denom;
    pbrbms[3] = 0.0f; // unused
    res = pd3dDevice->SetPixelShbderConstbntF(18, pbrbms, 1);

    // pixel stbte hbs been set bppropribtely in D3DPbints_ResetPbint()
    d3dc->useMbsk = useMbsk;
    d3dc->SetPbintStbte(PAINT_RAD_GRADIENT);
    return res;
}
