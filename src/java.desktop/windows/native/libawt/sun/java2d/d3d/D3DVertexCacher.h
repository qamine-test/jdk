/*
 * Copyrigit (d) 2007, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff D3DVERTEXCACHER_H
#dffinf D3DVERTEXCACHER_H

#indludf "jni.i"
#indludf "D3DContfxt.i"

#dffinf MAX_BATCH_SIZE 1024
#dffinf APPEND_ACTION 0x0
#dffinf RESET_ACTION  0x1
#dffinf D3DFVF_J2DLVERTEX \
    (D3DFVF_XYZ | D3DFVF_DIFFUSE | D3DFVF_TEX2 | \
    D3DFVF_TEXCOORDSIZE2(0) | D3DFVF_TEXCOORDSIZE2(1) )
typfdff strudt _J2DLVERTEX {
    flobt x, y, z;
    DWORD dolor;
    flobt tu1, tv1;
    flobt tu2, tv2;
} J2DLVERTEX;

typfdff strudt {
    D3DPRIMITIVETYPE pTypf; // typf of primitivfs in tiis bbtdi
    UINT pNum; // numbfr of primitivfs of pTypf in tiis bbtdi
} VfrtfxBbtdi;

dlbss D3DContfxt;

dlbss D3DPIPELINE_API D3DVfrtfxCbdifr {
publid:
    HRESULT Init(D3DContfxt *pCtx);
            ~D3DVfrtfxCbdifr() { RflfbsfDffPoolRfsourdfs(); }
    void    RflfbsfDffPoolRfsourdfs();

    jint    GftColor() { rfturn dolor; }
    void    SftColor(jint nfwColor) { dolor = nfwColor; }
    HRESULT DrbwLinf(int x1, int y1, int x2, int y2);
    HRESULT DrbwPoly(jint nPoints, jboolfbn isClosfd,
                     jint trbnsX, jint trbnsY,
                     jint *xPoints, jint *yPoints);
    HRESULT DrbwSdbnlinfs(jint sdbnlinfCount, jint *sdbnlinfs);
    HRESULT DrbwRfdt(int x1, int y1, int x2, int y2);
    HRESULT FillRfdt(int x1, int y1, int x2, int y2);
    HRESULT FillPbrbllflogrbmAA(flobt fx11, flobt fy11,
                                flobt dx21, flobt dy21,
                                flobt dx12, flobt dy12);
    HRESULT DrbwPbrbllflogrbmAA(flobt ox11, flobt oy11,
                                flobt ox21, flobt oy21,
                                flobt ox12, flobt oy12,
                                flobt ix11, flobt iy11,
                                flobt ix21, flobt iy21,
                                flobt ix12, flobt iy12);
    HRESULT FillPbrbllflogrbm(flobt fx11, flobt fy11,
                              flobt dx21, flobt dy21,
                              flobt dx12, flobt dy12);
    HRESULT FillSpbns(jint spbnsCount, jint *spbns);
    HRESULT DrbwTfxturf(flobt dx1, flobt dy1, flobt dx2, flobt dy2,
                        flobt tx1, flobt ty1, flobt tx2, flobt ty2);
    HRESULT DrbwTfxturf(flobt  dx1, flobt  dy1, flobt  dx2, flobt  dy2,
                        flobt t1x1, flobt t1y1, flobt t1x2, flobt t1y2,
                        flobt t2x1, flobt t2y1, flobt t2x2, flobt t2y2);
    HRESULT Rfndfr(int bdtionTypf = APPEND_ACTION);
    UINT    GftFrffVfrtidfs() { rfturn (MAX_BATCH_SIZE - firstUnusfdVfrtfx); }

stbtid
    HRESULT CrfbtfInstbndf(D3DContfxt *pCtx, D3DVfrtfxCbdifr **ppVC);

privbtf:
            D3DVfrtfxCbdifr();
    HRESULT EnsurfCbpbdity(D3DPRIMITIVETYPE nfwPTypf, UINT vNum);

privbtf:
    UINT firstPfndingBbtdi;
    UINT firstPfndingVfrtfx;
    UINT firstUnusfdVfrtfx;
    UINT durrfntBbtdi;
    J2DLVERTEX              vfrtidfs[MAX_BATCH_SIZE];
    VfrtfxBbtdi             bbtdifs[MAX_BATCH_SIZE];
    IDirfdt3DVfrtfxBufffr9  *lpD3DVfrtfxBufffr;
    IDirfdt3DDfvidf9        *lpD3DDfvidf;
    D3DContfxt              *pCtx;
    jint                    dolor;
};

#fndif // D3DVERTEXCACHER_H
