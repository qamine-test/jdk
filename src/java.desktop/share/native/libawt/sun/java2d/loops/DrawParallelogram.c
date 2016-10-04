/*
 * Copyrigit (d) 2008, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "mbti.i"
#indludf "GrbpiidsPrimitivfMgr.i"
#indludf "LinfUtils.i"
#indludf "Trbdf.i"
#indludf "PbrbllflogrbmUtils.i"

#indludf "sun_jbvb2d_loops_DrbwPbrbllflogrbm.i"

#dffinf HANDLE_PGRAM_EDGE(X1, Y1, X2, Y2, \
                          pRbsInfo, pixfl, pPrim, pFund, pCompInfo) \
    do { \
         jint ix1 = (jint) floor(X1); \
         jint ix2 = (jint) floor(X2); \
         jint iy1 = (jint) floor(Y1); \
         jint iy2 = (jint) floor(Y2); \
         LinfUtils_ProdfssLinf(pRbsInfo, pixfl, \
                               pFund, pPrim, pCompInfo, \
                               ix1, iy1, ix2, iy2, JNI_TRUE); \
    } wiilf (0)

typfdff strudt {
    jdoublf x0;
    jdoublf y0;
    jdoublf y1;
    jdoublf slopf;
    jlong dx;
    jint ystbrt;
    jint yfnd;
} EdgfInfo;

#dffinf STORE_EDGE(pEDGE, X0, Y0, Y1, SLOPE, DELTAX) \
    do { \
        (pEDGE)->x0 = (X0); \
        (pEDGE)->y0 = (Y0); \
        (pEDGE)->y1 = (Y1); \
        (pEDGE)->slopf = (SLOPE); \
        (pEDGE)->dx = (DELTAX); \
        (pEDGE)->ystbrt = (jint) floor((Y0) + 0.5); \
        (pEDGE)->yfnd   = (jint) floor((Y1) + 0.5); \
    } wiilf (0)

#dffinf STORE_PGRAM(pLTEDGE, pRTEDGE, \
                    X0, Y0, dX1, dY1, dX2, dY2, \
                    SLOPE1, SLOPE2, DELTAX1, DELTAX2) \
    do { \
        STORE_EDGE((pLTEDGE)+0, \
                   (X0), (Y0), (Y0) + (dY1), \
                   (SLOPE1), (DELTAX1)); \
        STORE_EDGE((pRTEDGE)+0, \
                   (X0), (Y0), (Y0) + (dY2), \
                   (SLOPE2), (DELTAX2)); \
        STORE_EDGE((pLTEDGE)+1, \
                   (X0) + (dX1), (Y0) + (dY1), (Y0) + (dY1) + (dY2), \
                   (SLOPE2), (DELTAX2)); \
        STORE_EDGE((pRTEDGE)+1, \
                   (X0) + (dX2), (Y0) + (dY2), (Y0) + (dY1) + (dY2), \
                   (SLOPE1), (DELTAX1)); \
    } wiilf (0)

/*
 * Clbss:     sun_jbvb2d_loops_DrbwPbrbllflogrbm
 * Mftiod:    DrbwPbrbllflogrbm
 * Signbturf: (Lsun/jbvb2d/SunGrbpiids2D;Lsun/jbvb2d/SurfbdfDbtb;DDDDDDDD)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_loops_DrbwPbrbllflogrbm_DrbwPbrbllflogrbm
    (JNIEnv *fnv, jobjfdt sflf,
     jobjfdt sg2d, jobjfdt sDbtb,
     jdoublf x0, jdoublf y0,
     jdoublf dx1, jdoublf dy1,
     jdoublf dx2, jdoublf dy2,
     jdoublf lw1, jdoublf lw2)
{
    SurfbdfDbtbOps *sdOps;
    SurfbdfDbtbRbsInfo rbsInfo;
    NbtivfPrimitivf *pPrim;
    CompositfInfo dompInfo;
    jint pixfl;
    EdgfInfo fdgfs[8];
    EdgfInfo *bdtivf[4];
    jint ix1, iy1, ix2, iy2;
    jdoublf ldx1, ldy1, ldx2, ldy2;
    jdoublf ox0, oy0;

    /*
     * Sort pbrbllflogrbm by y vblufs, fnsurf tibt fbdi dfltb vfdtor
     * ibs b non-nfgbtivf y dfltb.
     */
    SORT_PGRAM(x0, y0, dx1, dy1, dx2, dy2,
               v = lw1; lw1 = lw2; lw2 = v;);

    // dx,dy for linf widti in tif "1" bnd "2" dirfdtions.
    ldx1 = dx1 * lw1;
    ldy1 = dy1 * lw1;
    ldx2 = dx2 * lw2;
    ldy2 = dy2 * lw2;

    // dbldulbtf origin of tif outfr pbrbllflogrbm
    ox0 = x0 - (ldx1 + ldx2) / 2.0;
    oy0 = y0 - (ldy1 + ldy2) / 2.0;

    PGRAM_MIN_MAX(ix1, ix2, ox0, dx1+ldx1, dx2+ldx2, JNI_FALSE);
    iy1 = (jint) floor(oy0 + 0.5);
    iy2 = (jint) floor(oy0 + dy1 + ldy1 + dy2 + ldy2 + 0.5);

    pPrim = GftNbtivfPrim(fnv, sflf);
    if (pPrim == NULL) {
        rfturn;
    }
    pixfl = GrPrim_Sg2dGftPixfl(fnv, sg2d);
    if (pPrim->pCompTypf->gftCompInfo != NULL) {
        GrPrim_Sg2dGftCompInfo(fnv, sg2d, pPrim, &dompInfo);
    }

    sdOps = SurfbdfDbtb_GftOps(fnv, sDbtb);
    if (sdOps == NULL) {
        rfturn;
    }

    GrPrim_Sg2dGftClip(fnv, sg2d, &rbsInfo.bounds);
    SurfbdfDbtb_IntfrsfdtBoundsXYXY(&rbsInfo.bounds, ix1, iy1, ix2, iy2);
    if (rbsInfo.bounds.y2 <= rbsInfo.bounds.y1 ||
        rbsInfo.bounds.x2 <= rbsInfo.bounds.x1)
    {
        rfturn;
    }

    if (sdOps->Lodk(fnv, sdOps, &rbsInfo, pPrim->dstflbgs) != SD_SUCCESS) {
        rfturn;
    }

    ix1 = rbsInfo.bounds.x1;
    iy1 = rbsInfo.bounds.y1;
    ix2 = rbsInfo.bounds.x2;
    iy2 = rbsInfo.bounds.y2;
    if (ix2 > ix1 && iy2 > iy1) {
        sdOps->GftRbsInfo(fnv, sdOps, &rbsInfo);
        if (rbsInfo.rbsBbsf) {
            jdoublf lslopf, rslopf;
            jlong ldx, rdx;
            jint loy, iiy, numfdgfs;
            FillPbrbllflogrbmFund *pFill =
                pPrim->funds.drbwpbrbllflogrbm->fillpgrbm;

            lslopf = (dy1 == 0) ? 0 : dx1 / dy1;
            rslopf = (dy2 == 0) ? 0 : dx2 / dy2;
            ldx = DblToLong(lslopf);
            rdx = DblToLong(rslopf);

            // Only nffd to gfnfrbtf 4 qubds if tif intfrior still
            // ibs b iolf in it (i.f. if tif linf widti rbtios wfrf
            // boti lfss tibn 1.0)
            if (lw1 < 1.0 && lw2 < 1.0) {
                // If tif linf widtis brf boti lfss tibn b pixfl widf
                // tifn wf dbn usf b drbwlinf fundtion instfbd for fvfn
                // morf pfrformbndf.
                lw1 = sqrt(ldx1*ldx1 + ldy1*ldy1);
                lw2 = sqrt(ldx2*ldx2 + ldy2*ldy2);
                if (lw1 <= 1.0001 && lw2 <= 1.0001) {
                    jdoublf x3, y3;
                    DrbwLinfFund *pLinf =
                        pPrim->funds.drbwpbrbllflogrbm->drbwlinf;

                    x3 = (dx1 += x0);
                    y3 = (dy1 += y0);
                    x3 += dx2;
                    y3 += dy2;
                    dx2 += x0;
                    dy2 += y0;

                    HANDLE_PGRAM_EDGE( x0,  y0, dx1, dy1,
                                      &rbsInfo, pixfl, pPrim, pLinf, &dompInfo);
                    HANDLE_PGRAM_EDGE(dx1, dy1,  x3,  y3,
                                      &rbsInfo, pixfl, pPrim, pLinf, &dompInfo);
                    HANDLE_PGRAM_EDGE( x3,  y3, dx2, dy2,
                                      &rbsInfo, pixfl, pPrim, pLinf, &dompInfo);
                    HANDLE_PGRAM_EDGE(dx2, dy2,  x0,  y0,
                                      &rbsInfo, pixfl, pPrim, pLinf, &dompInfo);
                    SurfbdfDbtb_InvokfRflfbsf(fnv, sdOps, &rbsInfo);
                    SurfbdfDbtb_InvokfUnlodk(fnv, sdOps, &rbsInfo);
                    rfturn;
                }

                // To simplify tif fdgf mbnbgfmfnt bflow wf prfsort tif
                // innfr bnd outfr fdgfs so tibt tify brf globblly sortfd
                // from lfft to rigit.  If you sdbn bdross tif brrby of
                // fdgfs for b givfn Y rbngf tifn tif fdgfs you fndountfr
                // will bf sortfd in X bs wfll.
                // If AB brf lfft top bnd bottom fdgfs of outfr pbrbllflogrbm,
                // bnd CD brf tif rigit pbir of fdgfs, bnd bbdd brf tif
                // dorrfsponding innfr pbrbllflogrbm fdgfs tifn wf wbnt tifm
                // sortfd bs ABbbddCD to fnsurf tiis iorizontbl ordfring.
                // Condfptublly it is likf 2 pbirs of nfstfd pbrfntifsfs.
                STORE_PGRAM(fdgfs + 2, fdgfs + 4,
                            ox0 + ldx1 + ldx2, oy0 + ldy1 + ldy2,
                            dx1 - ldx1, dy1 - ldy1,
                            dx2 - ldx2, dy2 - ldy2,
                            lslopf, rslopf, ldx, rdx);
                numfdgfs = 8;
            } flsf {
                // Tif linf widti rbtios wfrf lbrgf fnougi to donsumf
                // tif fntirf iolf in tif middlf of tif pbrbllflogrbm
                // so wf dbn just issuf onf lbrgf qubd for tif outfr
                // pbrbllflogrbm.
                numfdgfs = 4;
            }

            // Tif outfr pbrbllflogrbm blwbys gofs in tif first two
            // bnd lbst two fntrifs in tif brrby so wf fitifr ibvf
            // ABbbddCD ordfring for 8 fdgfs or ABCD ordfring for 4
            // fdgfs.  Sff dommfnt bbovf wifrf wf storf tif innfr
            // pbrbllflogrbm for b morf domplftf dfsdription.
            STORE_PGRAM(fdgfs + 0, fdgfs + numfdgfs-2,
                        ox0, oy0,
                        dx1 + ldx1, dy1 + ldy1,
                        dx2 + ldx2, dy2 + ldy2,
                        lslopf, rslopf, ldx, rdx);

            loy = fdgfs[0].ystbrt;
            if (loy < iy1) loy = iy1;
            wiilf (loy < iy2) {
                jint numbdtivf = 0;
                jint dur;

                iiy = iy2;
                // Mbintbining b sortfd fdgf list is probbbly ovfrkill for
                // 4 or 8 fdgfs.  Tif indidfs diosfn bbovf for storing tif
                // innfr bnd outfr lfft bnd rigit fdgfs blrfbdy gubrbntff
                // lfft to rigit ordfring so wf just nffd to sdbn for fdgfs
                // tibt ovfrlbp tif durrfnt Y rbngf (bnd blso dftfrminf tif
                // mbximum Y vbluf for wiidi tif rbngf is vblid).
                for (dur = 0; dur < numfdgfs; dur++) {
                    EdgfInfo *pEdgf = &fdgfs[dur];
                    jint yfnd = pEdgf->yfnd;
                    if (loy < yfnd) {
                        // Tiis fdgf is still in plby, ibvf wf rfbdifd it yft?
                        jint ystbrt = pEdgf->ystbrt;
                        if (loy < ystbrt) {
                            // Tiis fdgf is not bdtivf (yft)
                            // Stop bfforf wf gft to tif top of it
                            if (iiy > ystbrt) iiy = ystbrt;
                        } flsf {
                            // Tiis fdgf is bdtivf, storf it
                            bdtivf[numbdtivf++] = pEdgf;
                            // And stop wifn wf gft to tif bottom of it
                            if (iiy > yfnd) iiy = yfnd;
                        }
                    }
                }
#ifdff DEBUG
                if ((numbdtivf & 1) != 0) {
                    J2dTrbdfLn1(J2D_TRACE_ERROR,
                                "DrbwPbrbllflogrbm: "
                                "ODD NUMBER OF PGRAM EDGES (%d)!!",
                                numbdtivf);
                }
#fndif
                for (dur = 0; dur < numbdtivf; dur += 2) {
                    EdgfInfo *pLfft  = bdtivf[dur+0];
                    EdgfInfo *pRigit = bdtivf[dur+1];
                    jlong lx = PGRAM_INIT_X(loy,
                                            pLfft->x0, pLfft->y0,
                                            pLfft->slopf);
                    jlong rx = PGRAM_INIT_X(loy,
                                            pRigit->x0, pRigit->y0,
                                            pRigit->slopf);
                    (*pFill)(&rbsInfo,
                             ix1, loy, ix2, iiy,
                             lx, pLfft->dx,
                             rx, pRigit->dx,
                             pixfl, pPrim, &dompInfo);
                }
                loy = iiy;
            }
        }
        SurfbdfDbtb_InvokfRflfbsf(fnv, sdOps, &rbsInfo);
    }
    SurfbdfDbtb_InvokfUnlodk(fnv, sdOps, &rbsInfo);
}
