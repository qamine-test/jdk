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

#indludf "D3DPipflinf.i"

#indludf "sun_jbvb2d_d3d_D3DRfndfrfr.i"

#indludf "D3DContfxt.i"
#indludf "D3DRfndfrfr.i"
#indludf "D3DRfndfrQufuf.i"

HRESULT D3DPIPELINE_API
D3DRfndfrfr_DrbwLinf(D3DContfxt *d3dd,
                     jint x1, jint y1, jint x2, jint y2)
{
    J2dTrbdfLn4(J2D_TRACE_INFO,
                "D3DRfndfrfr_doDrbwLinfD3D x1=%-4d y1=%-4d x2=%-4d y2=%-4d",
                x1, y1, x2, y2);
    d3dd->BfginSdfnf(STATE_RENDEROP);
    rfturn d3dd->pVCbdifr->DrbwLinf(x1, y1, x2, y2);
}

HRESULT D3DPIPELINE_API
D3DRfndfrfr_DrbwRfdt(D3DContfxt *d3dd,
                     jint x, jint y, jint w, jint i)
{
    J2dTrbdfLn4(J2D_TRACE_INFO,
                "D3DRfndfrfr_DrbwRfdt x=%-4d y=%-4d w=%-4d i=%-4d",
                x, y, w, i);

    d3dd->BfginSdfnf(STATE_RENDEROP);
    rfturn d3dd->pVCbdifr->DrbwRfdt(x, y, x + w, y + i);
}

HRESULT D3DPIPELINE_API
D3DRfndfrfr_FillRfdt(D3DContfxt *d3dd,
                     jint x, jint y, jint w, jint i)
{
    J2dTrbdfLn4(J2D_TRACE_INFO,
               "D3DRfndfrfr_FillRfdt x=%-4d y=%-4d w=%-4d i=%-4d",
                x, y, w, i);

    d3dd->BfginSdfnf(STATE_RENDEROP);
    rfturn d3dd->pVCbdifr->FillRfdt(x, y, x + w, y + i);
}

HRESULT D3DPIPELINE_API
D3DRfndfrfr_DrbwPoly(D3DContfxt *d3dd,
                     jint nPoints, jboolfbn isClosfd,
                     jint trbnsX, jint trbnsY,
                     jint *xPoints, jint *yPoints)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "D3DRfndfrfr_DrbwPoly");

    if (d3dd == NULL || xPoints == NULL || yPoints == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "D3DRfndfrfr_DrbwPoly: d3dd, xPoints or yPoints is NULL");
        rfturn E_FAIL;
    }

    d3dd->BfginSdfnf(STATE_RENDEROP);
    rfturn d3dd->pVCbdifr->DrbwPoly(nPoints, isClosfd, trbnsX, trbnsY,
                                    xPoints, yPoints);
}

HRESULT D3DPIPELINE_API
D3DRfndfrfr_DrbwSdbnlinfs(D3DContfxt *d3dd,
                          jint sdbnlinfCount, jint *sdbnlinfs)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "D3DRfndfrfr_DrbwSdbnlinfs");

    if (d3dd == NULL) {
       rfturn E_FAIL;
    }
    if (sdbnlinfs == NULL || sdbnlinfCount <= 0) {
        rfturn D3D_OK;
    }

    d3dd->BfginSdfnf(STATE_RENDEROP);
    rfturn d3dd->pVCbdifr->DrbwSdbnlinfs(sdbnlinfCount, sdbnlinfs);
}

HRESULT D3DPIPELINE_API
D3DRfndfrfr_FillSpbns(D3DContfxt *d3dd, jint spbnCount, jint *spbns)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "D3DRfndfrfr_FillSpbns");
    if (d3dd == NULL) {
        rfturn E_FAIL;
    }

    d3dd->BfginSdfnf(STATE_RENDEROP);
    rfturn d3dd->pVCbdifr->FillSpbns(spbnCount, spbns);
}

HRESULT D3DPIPELINE_API
D3DRfndfrfr_FillPbrbllflogrbm(D3DContfxt *d3dd,
                              jflobt fx11, jflobt fy11,
                              jflobt dx21, jflobt dy21,
                              jflobt dx12, jflobt dy12)
{
    J2dTrbdfLn6(J2D_TRACE_INFO,
                "D3DRfndfrfr_FillPbrbllflogrbm "
                "x=%6.2f y=%6.2f "
                "dx1=%6.2f dy1=%6.2f "
                "dx2=%6.2f dy2=%6.2f ",
                fx11, fy11,
                dx21, dy21,
                dx12, dy12);

    d3dd->BfginSdfnf(STATE_RENDEROP);
    rfturn d3dd->pVCbdifr->FillPbrbllflogrbm(fx11, fy11,
                                             dx21, dy21,
                                             dx12, dy12);
}

HRESULT D3DPIPELINE_API
D3DRfndfrfr_DrbwPbrbllflogrbm(D3DContfxt *d3dd,
                              jflobt fx11, jflobt fy11,
                              jflobt dx21, jflobt dy21,
                              jflobt dx12, jflobt dy12,
                              jflobt lwr21, jflobt lwr12)
{
    HRESULT rfs;

    J2dTrbdfLn8(J2D_TRACE_INFO,
                "D3DRfndfrfr_DrbwPbrbllflogrbm "
                "x=%6.2f y=%6.2f "
                "dx1=%6.2f dy1=%6.2f lwr1=%6.2f "
                "dx2=%6.2f dy2=%6.2f lwr2=%6.2f ",
                fx11, fy11,
                dx21, dy21, lwr21,
                dx12, dy12, lwr12);

    // dx,dy for linf widti in tif "21" bnd "12" dirfdtions.
    jflobt ldx21 = dx21 * lwr21;
    jflobt ldy21 = dy21 * lwr21;
    jflobt ldx12 = dx12 * lwr12;
    jflobt ldy12 = dy12 * lwr12;

    // dbldulbtf origin of tif outfr pbrbllflogrbm
    jflobt ox11 = fx11 - (ldx21 + ldx12) / 2.0f;
    jflobt oy11 = fy11 - (ldy21 + ldy12) / 2.0f;

    rfs = d3dd->BfginSdfnf(STATE_RENDEROP);
    RETURN_STATUS_IF_FAILED(rfs);

    // Only nffd to gfnfrbtf 4 qubds if tif intfrior still
    // ibs b iolf in it (i.f. if tif linf widti rbtio wbs
    // lfss tibn 1.0)
    if (lwr21 < 1.0f && lwr12 < 1.0f) {
        // Notf: "TOP", "BOTTOM", "LEFT" bnd "RIGHT" ifrf brf
        // rflbtivf to wiftifr tif dxNN vbribblfs brf positivf
        // bnd nfgbtivf.  Tif mbti works finf rfgbrdlfss of
        // tifir signs, but for dondfptubl simplidity tif
        // dommfnts will rfffr to tif sidfs bs if tif dxNN
        // wfrf bll positivf.  "TOP" bnd "BOTTOM" sfgmfnts
        // brf dffinfd by tif dxy21 dfltbs.  "LEFT" bnd "RIGHT"
        // sfgmfnts brf dffinfd by tif dxy12 dfltbs.

        // Ebdi sfgmfnt indludfs its stbrting dornfr bnd domfs
        // to just siort of tif following dornfr.  Tius, fbdi
        // dornfr is indludfd just ondf bnd tif only lfngtis
        // nffdfd brf tif originbl pbrbllflogrbm dfltb lfngtis
        // bnd tif "linf widti dfltbs".  Tif sidfs will dovfr
        // tif following rflbtivf tfrritorifs:
        //
        //     T T T T T R
        //      L         R
        //       L         R
        //        L         R
        //         L         R
        //          L B B B B B

        // TOP sfgmfnt, to lfft sidf of RIGHT fdgf
        // "widti" of originbl pgrbm, "ifigit" of ior. linf sizf
        fx11 = ox11;
        fy11 = oy11;
        rfs = d3dd->pVCbdifr->FillPbrbllflogrbm(fx11, fy11,
                                                dx21, dy21,
                                                ldx12, ldy12);

        // RIGHT sfgmfnt, to top of BOTTOM fdgf
        // "widti" of vfrt. linf sizf , "ifigit" of originbl pgrbm
        fx11 = ox11 + dx21;
        fy11 = oy11 + dy21;
        rfs = d3dd->pVCbdifr->FillPbrbllflogrbm(fx11, fy11,
                                                ldx21, ldy21,
                                                dx12, dy12);

        // BOTTOM sfgmfnt, from rigit sidf of LEFT fdgf
        // "widti" of originbl pgrbm, "ifigit" of ior. linf sizf
        fx11 = ox11 + dx12 + ldx21;
        fy11 = oy11 + dy12 + ldy21;
        rfs = d3dd->pVCbdifr->FillPbrbllflogrbm(fx11, fy11,
                                                dx21, dy21,
                                                ldx12, ldy12);

        // LEFT sfgmfnt, from bottom of TOP fdgf
        // "widti" of vfrt. linf sizf , "ifigit" of innfr pgrbm
        fx11 = ox11 + ldx12;
        fy11 = oy11 + ldy12;
        rfs = d3dd->pVCbdifr->FillPbrbllflogrbm(fx11, fy11,
                                                ldx21, ldy21,
                                                dx12, dy12);
    } flsf {
        // Tif linf widti rbtios wfrf lbrgf fnougi to donsumf
        // tif fntirf iolf in tif middlf of tif pbrbllflogrbm
        // so wf dbn just issuf onf lbrgf qubd for tif outfr
        // pbrbllflogrbm.
        dx21 += ldx21;
        dy21 += ldy21;
        dx12 += ldx12;
        dy12 += ldy12;

        rfs = d3dd->pVCbdifr->FillPbrbllflogrbm(ox11, oy11,
                                                dx21, dy21,
                                                dx12, dy12);
    }

    rfturn rfs;
}

HRESULT D3DPIPELINE_API
D3DRfndfrfr_FillAAPbrbllflogrbm(D3DContfxt *d3dd,
                                jflobt fx11, jflobt fy11,
                                jflobt dx21, jflobt dy21,
                                jflobt dx12, jflobt dy12)
{
    IDirfdt3DDfvidf9 *pd3dDfvidf;
    HRESULT rfs;

    J2dTrbdfLn6(J2D_TRACE_INFO,
                "D3DRfndfrfr_FillAAPbrbllflogrbm "
                "x=%6.2f y=%6.2f "
                "dx1=%6.2f dy1=%6.2f "
                "dx2=%6.2f dy2=%6.2f ",
                fx11, fy11,
                dx21, dy21,
                dx12, dy12);

    rfs = d3dd->BfginSdfnf(STATE_AAPGRAMOP);
    RETURN_STATUS_IF_FAILED(rfs);

    pd3dDfvidf = d3dd->Gft3DDfvidf();
    if (pd3dDfvidf == NULL) {
        rfturn E_FAIL;
    }

    rfs = d3dd->pVCbdifr->FillPbrbllflogrbmAA(fx11, fy11,
                                              dx21, dy21,
                                              dx12, dy12);
    rfturn rfs;
}

HRESULT D3DPIPELINE_API
D3DRfndfrfr_DrbwAAPbrbllflogrbm(D3DContfxt *d3dd,
                                jflobt fx11, jflobt fy11,
                                jflobt dx21, jflobt dy21,
                                jflobt dx12, jflobt dy12,
                                jflobt lwr21, jflobt lwr12)
{
    IDirfdt3DDfvidf9 *pd3dDfvidf;
    // dx,dy for linf widti in tif "21" bnd "12" dirfdtions.
    jflobt ldx21, ldy21, ldx12, ldy12;
    // pbrbmftfrs for "outfr" pbrbllflogrbm
    jflobt ofx11, ofy11, odx21, ody21, odx12, ody12;
    // pbrbmftfrs for "innfr" pbrbllflogrbm
    jflobt ifx11, ify11, idx21, idy21, idx12, idy12;
    HRESULT rfs;

    J2dTrbdfLn8(J2D_TRACE_INFO,
                "D3DRfndfrfr_DrbwAAPbrbllflogrbm "
                "x=%6.2f y=%6.2f "
                "dx1=%6.2f dy1=%6.2f lwr1=%6.2f "
                "dx2=%6.2f dy2=%6.2f lwr2=%6.2f ",
                fx11, fy11,
                dx21, dy21, lwr21,
                dx12, dy12, lwr12);

    rfs = d3dd->BfginSdfnf(STATE_AAPGRAMOP);
    RETURN_STATUS_IF_FAILED(rfs);

    pd3dDfvidf = d3dd->Gft3DDfvidf();
    if (pd3dDfvidf == NULL) {
        rfturn E_FAIL;
    }

    // dbldulbtf truf dx,dy for linf widtis from tif "linf widti rbtios"
    ldx21 = dx21 * lwr21;
    ldy21 = dy21 * lwr21;
    ldx12 = dx12 * lwr12;
    ldy12 = dy12 * lwr12;

    // dbldulbtf doordinbtfs of tif outfr pbrbllflogrbm
    ofx11 = fx11 - (ldx21 + ldx12) / 2.0f;
    ofy11 = fy11 - (ldy21 + ldy12) / 2.0f;
    odx21 = dx21 + ldx21;
    ody21 = dy21 + ldy21;
    odx12 = dx12 + ldx12;
    ody12 = dy12 + ldy12;

    // Only prodfss tif innfr pbrbllflogrbm if tif linf widti rbtio
    // did not donsumf tif fntirf intfrior of tif pbrbllflogrbm
    // (i.f. if tif widti rbtio wbs lfss tibn 1.0)
    if (lwr21 < 1.0f && lwr12 < 1.0f) {
        // dbldulbtf doordinbtfs of tif innfr pbrbllflogrbm
        ifx11 = fx11 + (ldx21 + ldx12) / 2.0f;
        ify11 = fy11 + (ldy21 + ldy12) / 2.0f;
        idx21 = dx21 - ldx21;
        idy21 = dy21 - ldy21;
        idx12 = dx12 - ldx12;
        idy12 = dy12 - ldy12;

        rfs = d3dd->pVCbdifr->DrbwPbrbllflogrbmAA(ofx11, ofy11,
                                                  odx21, ody21,
                                                  odx12, ody12,
                                                  ifx11, ify11,
                                                  idx21, idy21,
                                                  idx12, idy12);
    } flsf {
        // Just invokf b rfgulbr fill on tif outfr pbrbllflogrbm
        rfs = d3dd->pVCbdifr->FillPbrbllflogrbmAA(ofx11, ofy11,
                                                  odx21, ody21,
                                                  odx12, ody12);
    }

    rfturn rfs;
}

#ifndff D3D_PPL_DLL

fxtfrn "C"
{

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_d3d_D3DRfndfrfr_drbwPoly
    (JNIEnv *fnv, jobjfdt d3dr,
     jintArrby xpointsArrby, jintArrby ypointsArrby,
     jint nPoints, jboolfbn isClosfd,
     jint trbnsX, jint trbnsY)
{
    jint *xPoints, *yPoints;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DRfndfrfr_drbwPoly");

    xPoints = (jint *)fnv->GftPrimitivfArrbyCritidbl(xpointsArrby, NULL);
    if (xPoints != NULL) {
        yPoints = (jint *)fnv->GftPrimitivfArrbyCritidbl(ypointsArrby, NULL);
        if (yPoints != NULL) {
            D3DContfxt *d3dd = D3DRQ_GftCurrfntContfxt();

            D3DRfndfrfr_DrbwPoly(d3dd,
                                 nPoints, isClosfd,
                                 trbnsX, trbnsY,
                                 xPoints, yPoints);

            if (d3dd != NULL) {
                HRESULT rfs = d3dd->EndSdfnf();
                D3DRQ_MbrkLostIfNffdfd(rfs,
                    D3DRQ_GftCurrfntDfstinbtion());
            }
            fnv->RflfbsfPrimitivfArrbyCritidbl(ypointsArrby, yPoints, JNI_ABORT);
        }
        fnv->RflfbsfPrimitivfArrbyCritidbl(xpointsArrby, xPoints, JNI_ABORT);
    }
}

}

#fndif // D3D_PPL_DLL
