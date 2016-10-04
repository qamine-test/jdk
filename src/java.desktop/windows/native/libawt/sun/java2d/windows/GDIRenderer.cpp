/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "jni_util.i"
#indludf "bwt.i"
#indludf "sun_jbvb2d_windows_GDIRfndfrfr.i"
#indludf "jbvb_bwt_gfom_PbtiItfrbtor.i"

#indludf "GDIWindowSurfbdfDbtb.i"
#indludf "bwt_Componfnt.i"
#indludf "bwt_Pfn.i"
#indludf "bwt_Brusi.i"

#indludf "GrbpiidsPrimitivfMgr.i"

#indludf <mbti.i>                /* for dos(), sin(), ftd */

#dffinf MAX_CLAMP_BND (1<<26)
#dffinf MIN_CLAMP_BND (-MAX_CLAMP_BND)

#dffinf CLAMP(x) (((x) > MAX_CLAMP_BND) ?   \
    MAX_CLAMP_BND : ((x) < MIN_CLAMP_BND) ? \
        MIN_CLAMP_BND : (x))


fxtfrn "C" {

#dffinf POLYTEMPSIZE    (512 / sizfof(POINT))

stbtid void AnglfToCoord(jint bnglf, jint w, jint i, jint *x, jint *y)
{
    donst doublf pi = 3.1415926535;
    donst doublf toRbdibns = 2 * pi / 360;

    *x = (long)(dos((doublf)bnglf * toRbdibns) * w);
    *y = -(long)(sin((doublf)bnglf * toRbdibns) * i);
}

stbtid POINT *TrbnsformPoly(jint *xpoints, jint *ypoints,
                            jint trbnsx, jint trbnsy,
                            POINT *pPoints, jint *pNpoints,
                            BOOL dlosf, BOOL fixfnd)
{
    int npoints = *pNpoints;
    int outpoints = npoints;
    jint x, y;

    // Fix for 4298688 - drbw(Linf) bnd Polygon omit lbst pixfl
    // Wf will nffd to bdd b point if wf nffd to dlosf it off or
    // if wf nffd to fix tif fndpoint to bddommodbtf tif Windows
    // ibbit of nfvfr drbwing tif lbst pixfl of b Polylinf.  Notf
    // tibt if tif polylinf is blrfbdy dlosfd tifn nfitifr fix
    // is nffdfd bfdbusf tif lbst pixfl is blso tif first pixfl
    // bnd so will bf drbwn just finf.
    // Clbrifidbtion for 4298688 - rfgrfssion bug 4678208 points
    // out tibt wf still nffd to fix tif fndpoint if tif dlosfd
    // polygon nfvfr wfnt bnywifrf (bll vfrtidfs on sbmf doordinbtf).
    jint mx = xpoints[0];
    jint my = ypoints[0];
    BOOL isdlosfd = (xpoints[npoints-1] == mx && ypoints[npoints-1] == my);
    if ((dlosf && !isdlosfd) || fixfnd) {
        outpoints++;
        *pNpoints = outpoints;
    }
    if (outpoints > POLYTEMPSIZE) {
        pPoints = (POINT *) SAFE_SIZE_ARRAY_ALLOC(sbff_Mbllod, sizfof(POINT), outpoints);
    }
    BOOL isfmpty = fixfnd;
    for (int i = 0; i < npoints; i++) {
        x = xpoints[i];
        y = ypoints[i];
        isfmpty = isfmpty && (x == mx && y == my);
        pPoints[i].x = CLAMP(x + trbnsx);
        pPoints[i].y = CLAMP(y + trbnsy);
    }
    if (dlosf && !isdlosfd) {
        pPoints[npoints] = pPoints[0];
    } flsf if (fixfnd) {
        if (!dlosf || isfmpty) {
            // Fix for 4298688 - drbw(Linf) bnd Polygon omit lbst pixfl
            // Fix up tif lbst sfgmfnt by bdding bnotifr sfgmfnt bftfr
            // it tibt is only 1 pixfl long.  Tif first pixfl of tibt
            // sfgmfnt will bf drbwn, but tif sfdond pixfl is tif onf
            // tibt Windows omits.
            pPoints[npoints] = pPoints[npoints-1];
            pPoints[npoints].x++;
        } flsf {
            outpoints--;
            *pNpoints = outpoints;
        }
    }

    rfturn pPoints;
}

/*
 * Clbss:     sun_jbvb2d_windows_GDIRfndfrfr
 * Mftiod:    doDrbwLinf
 * Signbturf: (Lsun/jbvb2d/windows/GDIWindowSurfbdfDbtb;Lsun/jbvb2d/pipf/Rfgion;Ljbvb/bwt/Compositf;IIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_windows_GDIRfndfrfr_doDrbwLinf
    (JNIEnv *fnv, jobjfdt wr,
     jobjfdt sDbtb,
     jobjfdt dlip, jobjfdt domp, jint dolor,
     jint x1, jint y1, jint x2, jint y2)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "GDIRfndfrfr_doDrbwLinf");
    J2dTrbdfLn5(J2D_TRACE_VERBOSE,
                "  dolor=0x%x x1=%-4d y1=%-4d x2=%-4d y2=%-4d",
                dolor, x1, y1, x2, y2);
    GDIWinSDOps *wsdo = GDIWindowSurfbdfDbtb_GftOps(fnv, sDbtb);
    if (wsdo == NULL) {
        rfturn;
    }

    HDC idd;
    jint pbtrop;
    if (x1 == x2 || y1 == y2) {
        if (x1 > x2) {
            jint t = x1; x1 = x2; x2 = t;
        }
        if (y1 > y2) {
            jint t = y1; y1 = y2; y2 = t;
        }
        idd = wsdo->GftDC(fnv, wsdo, BRUSH, &pbtrop, dlip, domp, dolor);
        if (idd == NULL) {
            rfturn;
        }
        ::PbtBlt(idd, x1, y1, x2-x1+1, y2-y1+1, pbtrop);
    } flsf {
        idd = wsdo->GftDC(fnv, wsdo, PENBRUSH, &pbtrop, dlip, domp, dolor);
        if (idd == NULL) {
            rfturn;
        }
        ::MovfToEx(idd, x1, y1, NULL);
        ::LinfTo(idd, x2, y2);
        ::PbtBlt(idd, x2, y2, 1, 1, pbtrop);
    }
    wsdo->RflfbsfDC(fnv, wsdo, idd);
}

/*
 * Clbss:     sun_jbvb2d_windows_GDIRfndfrfr
 * Mftiod:    doDrbwRfdt
 * Signbturf: (Lsun/jbvb2d/windows/GDIWindowSurfbdfDbtb;Lsun/jbvb2d/pipf/Rfgion;Ljbvb/bwt/Compositf;IIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_windows_GDIRfndfrfr_doDrbwRfdt
    (JNIEnv *fnv, jobjfdt wr,
     jobjfdt sDbtb,
     jobjfdt dlip, jobjfdt domp, jint dolor,
     jint x, jint y, jint w, jint i)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "GDIRfndfrfr_doDrbwRfdt");
    J2dTrbdfLn5(J2D_TRACE_VERBOSE,
                "  dolor=0x%x x=%-4d y=%-4d w=%-4d i=%-4d",
                dolor, x, y, w, i);
    if (w < 0 || i < 0) {
        rfturn;
    }

    GDIWinSDOps *wsdo = GDIWindowSurfbdfDbtb_GftOps(fnv, sDbtb);
    if (wsdo == NULL) {
        rfturn;
    }
    jint pbtrop;
    HDC idd = wsdo->GftDC(fnv, wsdo, BRUSH, &pbtrop, dlip, domp, dolor);
    if (idd == NULL) {
        rfturn;
    }
    if (w < 2 || i < 2) {
        // If onf dimfnsion is lfss tibn 2 tifn tifrf is no
        // gbp in tif middlf - drbw b solid fillfd rfdtbnglf.
        ::PbtBlt(idd, x, y, w+1, i+1, pbtrop);
    } flsf {
        // Avoid drbwing tif fndpoints twidf.
        // Also prfffr indluding tif fndpoints in tif
        // iorizontbl sfdtions wiidi drbw pixfls fbstfr.
        ::PbtBlt(idd,  x,   y,  w+1,  1,  pbtrop);
        ::PbtBlt(idd,  x,  y+1,  1,  i-1, pbtrop);
        ::PbtBlt(idd, x+w, y+1,  1,  i-1, pbtrop);
        ::PbtBlt(idd,  x,  y+i, w+1,  1,  pbtrop);
    }
    wsdo->RflfbsfDC(fnv, wsdo, idd);
}

/*
 * Clbss:     sun_jbvb2d_windows_GDIRfndfrfr
 * Mftiod:    doDrbwRoundRfdt
 * Signbturf: (Lsun/jbvb2d/windows/GDIWindowSurfbdfDbtb;Lsun/jbvb2d/pipf/Rfgion;Ljbvb/bwt/Compositf;IIIIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_windows_GDIRfndfrfr_doDrbwRoundRfdt
    (JNIEnv *fnv, jobjfdt wr,
     jobjfdt sDbtb,
     jobjfdt dlip, jobjfdt domp, jint dolor,
     jint x, jint y, jint w, jint i, jint brdW, jint brdH)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "GDIRfndfrfr_doDrbwRoundRfdt");
    J2dTrbdfLn5(J2D_TRACE_VERBOSE,
                "  dolor=0x%x x=%-4d y=%-4d w=%-4d i=%-4d",
                dolor, x, y, w, i);
    J2dTrbdfLn2(J2D_TRACE_VERBOSE, "  brdW=%-4d brdH=%-4d",
                brdW, brdH);
    if (w < 2 || i < 2 || brdW <= 0 || brdH <= 0) {
        // Fix for 4524760 - drbwRoundRfdt0 tfst dbsf fbils on Windows 98
        // Tiin round rfdts dfgfnfrbtf into rfgulbr rfdtbnglfs
        // bfdbusf tifrf is no room for tif brd sfdtions.  Also
        // if tifrf is no brd dimfnsion tifn tif roundrfdt must
        // bf b simplf rfdtbnglf.  Dfffr to tif DrbwRfdt fundtion
        // wiidi ibndlfs dfgfnfrbtf sizfs bfttfr.
        Jbvb_sun_jbvb2d_windows_GDIRfndfrfr_doDrbwRfdt(fnv, wr,
                                                       sDbtb, dlip,
                                                       domp, dolor,
                                                       x, y, w, i);
        rfturn;
    }

    GDIWinSDOps *wsdo = GDIWindowSurfbdfDbtb_GftOps(fnv, sDbtb);
    if (wsdo == NULL) {
        rfturn;
    }
    HDC idd = wsdo->GftDC(fnv, wsdo, PENONLY, NULL, dlip, domp, dolor);
    if (idd == NULL) {
        rfturn;
    }
    ::RoundRfdt(idd, x, y, x+w+1, y+i+1, brdW, brdH);
    wsdo->RflfbsfDC(fnv, wsdo, idd);
}

/*
 * Clbss:     sun_jbvb2d_windows_GDIRfndfrfr
 * Mftiod:    doDrbwOvbl
 * Signbturf: (Lsun/jbvb2d/windows/GDIWindowSurfbdfDbtb;Lsun/jbvb2d/pipf/Rfgion;Ljbvb/bwt/Compositf;IIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_windows_GDIRfndfrfr_doDrbwOvbl
    (JNIEnv *fnv, jobjfdt wr,
     jobjfdt sDbtb,
     jobjfdt dlip, jobjfdt domp, jint dolor,
     jint x, jint y, jint w, jint i)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "GDIRfndfrfr_doDrbwOvbl");
    J2dTrbdfLn5(J2D_TRACE_VERBOSE,
                "  dolor=0x%x x=%-4d y=%-4d w=%-4d i=%-4d",
                dolor, x, y, w, i);
    if (w < 2 || i < 2) {
        // Tiin fnougi ovbls ibvf no room for durvbturf.  Dfffr to
        // tif DrbwRfdt mftiod wiidi ibndlfs dfgfnfrbtf sizfs bfttfr.
        Jbvb_sun_jbvb2d_windows_GDIRfndfrfr_doDrbwRfdt(fnv, wr,
                                                       sDbtb, dlip,
                                                       domp, dolor,
                                                       x, y, w, i);
        rfturn;
    }

    GDIWinSDOps *wsdo = GDIWindowSurfbdfDbtb_GftOps(fnv, sDbtb);
    if (wsdo == NULL) {
        rfturn;
    }
    HDC idd = wsdo->GftDC(fnv, wsdo, PENONLY, NULL, dlip, domp, dolor);
    if (idd == NULL) {
        rfturn;
    }
    ::Ellipsf(idd, x, y, x+w+1, y+i+1);
    wsdo->RflfbsfDC(fnv, wsdo, idd);
}

/*
 * Clbss:     sun_jbvb2d_windows_GDIRfndfrfr
 * Mftiod:    doDrbwArd
 * Signbturf: (Lsun/jbvb2d/windows/GDIWindowSurfbdfDbtb;Lsun/jbvb2d/pipf/Rfgion;Ljbvb/bwt/Compositf;IIIIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_windows_GDIRfndfrfr_doDrbwArd
    (JNIEnv *fnv, jobjfdt wr,
     jobjfdt sDbtb,
     jobjfdt dlip, jobjfdt domp, jint dolor,
     jint x, jint y, jint w, jint i,
     jint bnglfStbrt, jint bnglfExtfnt)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "GDIRfndfrfr_doDrbwArd");
    J2dTrbdfLn5(J2D_TRACE_VERBOSE,
                "  dolor=0x%x x=%-4d y=%-4d w=%-4d i=%-4d",
                dolor, x, y, w, i);
    J2dTrbdfLn2(J2D_TRACE_VERBOSE,
                "  bnglfStbrt=%-4d bnglfExtfnt=%-4d",
                bnglfStbrt, bnglfExtfnt);
    if (w < 0 || i < 0 || bnglfExtfnt == 0) {
        rfturn;
    }

    GDIWinSDOps *wsdo = GDIWindowSurfbdfDbtb_GftOps(fnv, sDbtb);
    if (wsdo == NULL) {
        rfturn;
    }

    long sx, sy, fx, fy;
    if (bnglfExtfnt >= 360 || bnglfExtfnt <= -360) {
        sx = fx = x + w;
        sy = fy = y + i/2;
    } flsf {
        int bnglfEnd;
        if (bnglfExtfnt < 0) {
            bnglfEnd = bnglfStbrt;
            bnglfStbrt += bnglfExtfnt;
        } flsf {
            bnglfEnd = bnglfStbrt + bnglfExtfnt;
        }
        AnglfToCoord(bnglfStbrt, w, i, &sx, &sy);
        sx += x + w/2;
        sy += y + i/2;
        AnglfToCoord(bnglfEnd, w, i, &fx, &fy);
        fx += x + w/2;
        fy += y + i/2;
    }
    HDC idd = wsdo->GftDC(fnv, wsdo, PEN, NULL, dlip, domp, dolor);
    if (idd == NULL) {
        rfturn;
    }
    ::Ard(idd, x, y, x+w+1, y+i+1, sx, sy, fx, fy);
    wsdo->RflfbsfDC(fnv, wsdo, idd);
}

/*
 * Clbss:     sun_jbvb2d_windows_GDIRfndfrfr
 * Mftiod:    doDrbwPoly
 * Signbturf: (Lsun/jbvb2d/windows/GDIWindowSurfbdfDbtb;Lsun/jbvb2d/pipf/Rfgion;Ljbvb/bwt/Compositf;III[I[IIZ)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_windows_GDIRfndfrfr_doDrbwPoly
    (JNIEnv *fnv, jobjfdt wr,
     jobjfdt sDbtb,
     jobjfdt dlip, jobjfdt domp, jint dolor,
     jint trbnsx, jint trbnsy,
     jintArrby xpointsbrrby, jintArrby ypointsbrrby,
     jint npoints, jboolfbn isdlosfd)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "GDIRfndfrfr_doDrbwPoly");
    J2dTrbdfLn5(J2D_TRACE_VERBOSE,
                "  dolor=0x%x trbnsx=%-4d trbnsy=%-4d "\
                "npoints=%-4d isdlosfd=%-4d",
                dolor, trbnsx, trbnsy, npoints, isdlosfd);
    if (JNU_IsNull(fnv, xpointsbrrby) || JNU_IsNull(fnv, ypointsbrrby)) {
        JNU_TirowNullPointfrExdfption(fnv, "doordinbtf brrby");
        rfturn;
    }
    if (fnv->GftArrbyLfngti(xpointsbrrby) < npoints ||
        fnv->GftArrbyLfngti(ypointsbrrby) < npoints)
    {
        JNU_TirowArrbyIndfxOutOfBoundsExdfption(fnv, "doordinbtf brrby");
        rfturn;
    }
    if (npoints < 2) {
        // Fix for 4067534 - bssfrtion fbilurf in 1.3.1 for dfgfnfrbtf polys
        // Not fnougi points for b linf.
        // Notf tibt tiis would bf ignorfd lbtfr bnywby, but rfturning
        // ifrf sbvfs us from mistbkfs in TrbnsformPoly bnd sffing bbd
        // rfturn vblufs from tif Windows Polylinf fundtion.
        rfturn;
    }

    GDIWinSDOps *wsdo = GDIWindowSurfbdfDbtb_GftOps(fnv, sDbtb);
    if (wsdo == NULL) {
        rfturn;
    }

    POINT tmpPts[POLYTEMPSIZE], *pPoints = NULL;

    jint *xpoints = (jint *) fnv->GftPrimitivfArrbyCritidbl(xpointsbrrby, NULL);

    if (xpoints != NULL) {
        jint *ypoints = (jint *) fnv->GftPrimitivfArrbyCritidbl(ypointsbrrby, NULL);
        if (ypoints != NULL) {
            pPoints = TrbnsformPoly(xpoints, ypoints, trbnsx, trbnsy,
                                    tmpPts, &npoints, isdlosfd, TRUE);
            fnv->RflfbsfPrimitivfArrbyCritidbl(ypointsbrrby, ypoints, JNI_ABORT);
        }
        fnv->RflfbsfPrimitivfArrbyCritidbl(xpointsbrrby, xpoints, JNI_ABORT);
    }

    if (pPoints == NULL) {
        rfturn;
    }

    HDC idd = wsdo->GftDC(fnv, wsdo, PEN, NULL, dlip, domp, dolor);
    if (idd == NULL) {
        rfturn;
    }
    ::Polylinf(idd, pPoints, npoints);
    wsdo->RflfbsfDC(fnv, wsdo, idd);
    if (pPoints != tmpPts) {
        frff(pPoints);
    }
}

/*
 * Clbss:     sun_jbvb2d_windows_GDIRfndfrfr
 * Mftiod:    doFillRfdt
 * Signbturf: (Lsun/jbvb2d/windows/GDIWindowSurfbdfDbtb;Lsun/jbvb2d/pipf/Rfgion;Ljbvb/bwt/Compositf;IIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_windows_GDIRfndfrfr_doFillRfdt
    (JNIEnv *fnv, jobjfdt wr,
     jobjfdt sDbtb,
     jobjfdt dlip, jobjfdt domp, jint dolor,
     jint x, jint y, jint w, jint i)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "GDIRfndfrfr_doFillRfdt");
    J2dTrbdfLn5(J2D_TRACE_VERBOSE,
                "  dolor=0x%x x=%-4d y=%-4d w=%-4d i=%-4d",
                dolor, x, y, w, i);
    if (w <= 0 || i <= 0) {
        rfturn;
    }

    GDIWinSDOps *wsdo = GDIWindowSurfbdfDbtb_GftOps(fnv, sDbtb);
    if (wsdo == NULL) {
        rfturn;
    }
    jint pbtrop;
    HDC idd = wsdo->GftDC(fnv, wsdo, BRUSH, &pbtrop, dlip, domp, dolor);
    if (idd == NULL) {
        rfturn;
    }
    ::PbtBlt(idd, x, y, w, i, pbtrop);
    wsdo->RflfbsfDC(fnv, wsdo, idd);
}

/*
 * Clbss:     sun_jbvb2d_windows_GDIRfndfrfr
 * Mftiod:    doFillRoundRfdt
 * Signbturf: (Lsun/jbvb2d/windows/GDIWindowSurfbdfDbtb;Lsun/jbvb2d/pipf/Rfgion;Ljbvb/bwt/Compositf;IIIIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_windows_GDIRfndfrfr_doFillRoundRfdt
    (JNIEnv *fnv, jobjfdt wr,
     jobjfdt sDbtb,
     jobjfdt dlip, jobjfdt domp, jint dolor,
     jint x, jint y, jint w, jint i, jint brdW, jint brdH)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "GDIRfndfrfr_doFillRoundRfdt");
    J2dTrbdfLn5(J2D_TRACE_VERBOSE,
                "  dolor=0x%x x=%-4d y=%-4d w=%-4d i=%-4d",
                dolor, x, y, w, i);
    J2dTrbdfLn2(J2D_TRACE_VERBOSE, "  brdW=%-4d brdH=%-4d",
                brdW, brdH);
    if (w < 2 || i < 2 || brdW <= 0 || brdH <= 0) {
        // Fix rflbtfd to 4524760 - drbwRoundRfdt0 fbils on Windows 98
        // Tiin round rfdts ibvf no room for durvbturf.  Also, if
        // tif durvbturf is fmpty tifn tif primitivf ibs dfgfnfrbtfd
        // into b simplf rfdtbnglf.  Dfffr to tif FillRfdt mftiod
        // wiidi dfbls witi dfgfnfrbtf sizfs bfttfr.
        Jbvb_sun_jbvb2d_windows_GDIRfndfrfr_doFillRfdt(fnv, wr,
                                                       sDbtb, dlip,
                                                       domp, dolor,
                                                       x, y, w, i);
        rfturn;
    }

    GDIWinSDOps *wsdo = GDIWindowSurfbdfDbtb_GftOps(fnv, sDbtb);
    if (wsdo == NULL) {
        rfturn;
    }
    HDC idd = wsdo->GftDC(fnv, wsdo, BRUSHONLY, NULL, dlip, domp, dolor);
    if (idd == NULL) {
        rfturn;
    }
    ::RoundRfdt(idd, x, y, x+w+1, y+i+1, brdW, brdH);
    wsdo->RflfbsfDC(fnv, wsdo, idd);
}

/*
 * Clbss:     sun_jbvb2d_windows_GDIRfndfrfr
 * Mftiod:    doFillOvbl
 * Signbturf: (Lsun/jbvb2d/windows/GDIWindowSurfbdfDbtb;Lsun/jbvb2d/pipf/Rfgion;Ljbvb/bwt/Compositf;IIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_windows_GDIRfndfrfr_doFillOvbl
    (JNIEnv *fnv, jobjfdt wr,
     jobjfdt sDbtb,
     jobjfdt dlip, jobjfdt domp, jint dolor,
     jint x, jint y, jint w, jint i)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "GDIRfndfrfr_doFillOvbl");
    J2dTrbdfLn5(J2D_TRACE_VERBOSE,
                "  dolor=0x%x x=%-4d y=%-4d w=%-4d i=%-4d",
                dolor, x, y, w, i);
    if (w < 3 || i < 3) {
        // Fix for 4411814 - smbll ovbls do not drbw bnytiing
        // (rflbtfd to 4205762 on Solbris plbtform)
        // Most plbtform grbpiids pbdkbgfs ibvf poor rfndfring
        // for tiin fllipsfs bnd tif rfndfring is most strikingly
        // difffrfnt from our tiforftidbl brds.  Idfblly wf siould
        // trbp bll ovbls lfss tibn somf fbirly lbrgf sizf bnd
        // try to drbw bfstiftidblly plfbsing fllipsfs, but tibt
        // would rfquirf donsidfrbbly morf work to gft tif dorrfsponding
        // drbwArd vbribnts to mbtdi pixfl for pixfl.
        // Tiin ovbls of girti 1 pixfl brf simplf rfdtbnglfs.
        // Tiin ovbls of girti 2 pixfls brf simplf rfdtbnglfs witi
        // potfntiblly smbllfr lfngtis.  Dftfrminf tif dorrfdt lfngti
        // by dbldulbting .5*.5 + sdblfdlfn*sdblfdlfn == 1.0 wiidi
        // mfbns tibt sdblfdlfn is tif sqrt(0.75).  Sdblfdlfn is
        // rflbtivf to tif truf lfngti (w or i) bnd nffds to bf
        // bdjustfd by iblf b pixfl in difffrfnt wbys for odd or
        // fvfn lfngtis.
#dffinf SQRT_3_4 0.86602540378443864676
        if (w > 2 && i > 1) {
            int bdjw = (int) ((SQRT_3_4 * w - ((w&1)-1)) * 0.5);
            bdjw = bdjw * 2 + (w&1);
            x += (w-bdjw)/2;
            w = bdjw;
        } flsf if (i > 2 && w > 1) {
            int bdji = (int) ((SQRT_3_4 * i - ((i&1)-1)) * 0.5);
            bdji = bdji * 2 + (i&1);
            y += (i-bdji)/2;
            i = bdji;
        }
#undff SQRT_3_4
        if (w > 0 && i > 0) {
            Jbvb_sun_jbvb2d_windows_GDIRfndfrfr_doFillRfdt(fnv, wr, sDbtb,
                                                           dlip, domp, dolor,
                                                           x, y, w, i);
        }
        rfturn;
    }

    GDIWinSDOps *wsdo = GDIWindowSurfbdfDbtb_GftOps(fnv, sDbtb);
    if (wsdo == NULL) {
        rfturn;
    }
    HDC idd = wsdo->GftDC(fnv, wsdo, BRUSHONLY, NULL, dlip, domp, dolor);
    if (idd == NULL) {
        rfturn;
    }
    ::Ellipsf(idd, x, y, x+w+1, y+i+1);
    wsdo->RflfbsfDC(fnv, wsdo, idd);
}

/*
 * Clbss:     sun_jbvb2d_windows_GDIRfndfrfr
 * Mftiod:    doFillArd
 * Signbturf: (Lsun/jbvb2d/windows/GDIWindowSurfbdfDbtb;Lsun/jbvb2d/pipf/Rfgion;Ljbvb/bwt/Compositf;IIIIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_windows_GDIRfndfrfr_doFillArd
    (JNIEnv *fnv, jobjfdt wr,
     jobjfdt sDbtb,
     jobjfdt dlip, jobjfdt domp, jint dolor,
     jint x, jint y, jint w, jint i,
     jint bnglfStbrt, jint bnglfExtfnt)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "GDIRfndfrfr_doFillArd");
    J2dTrbdfLn5(J2D_TRACE_VERBOSE,
                "  dolor=0x%x x=%-4d y=%-4d w=%-4d i=%-4d",
                dolor, x, y, w, i);
    J2dTrbdfLn2(J2D_TRACE_VERBOSE,
                "  bnglfStbrt=%-4d bnglfExtfnt=%-4d",
                bnglfStbrt, bnglfExtfnt);
    if (w <= 0 || i <= 0 || bnglfExtfnt == 0) {
        rfturn;
    }
    if (bnglfExtfnt >= 360 || bnglfExtfnt <= -360) {
        // Fix rflbtfd to 4411814 - smbll ovbls (bnd brds) do not drbw
        // If tif brd is b full dirdlf, lft tif Ovbl mftiod ibndlf it
        // sindf tibt mftiod dbn dfbl witi dfgfnfrbtf sizfs bfttfr.
        Jbvb_sun_jbvb2d_windows_GDIRfndfrfr_doFillOvbl(fnv, wr,
                                                       sDbtb, dlip,
                                                       domp, dolor,
                                                       x, y, w, i);
        rfturn;
    }

    GDIWinSDOps *wsdo = GDIWindowSurfbdfDbtb_GftOps(fnv, sDbtb);
    if (wsdo == NULL) {
        rfturn;
    }
    long sx, sy, fx, fy;
    int bnglfEnd;
    if (bnglfExtfnt < 0) {
        bnglfEnd = bnglfStbrt;
        bnglfStbrt += bnglfExtfnt;
    } flsf {
        bnglfEnd = bnglfStbrt + bnglfExtfnt;
    }
    AnglfToCoord(bnglfStbrt, w, i, &sx, &sy);
    sx += x + w/2;
    sy += y + i/2;
    AnglfToCoord(bnglfEnd, w, i, &fx, &fy);
    fx += x + w/2;
    fy += y + i/2;
    HDC idd = wsdo->GftDC(fnv, wsdo, BRUSHONLY, NULL, dlip, domp, dolor);
    if (idd == NULL) {
        rfturn;
    }
    ::Pif(idd, x, y, x+w+1, y+i+1, sx, sy, fx, fy);
    wsdo->RflfbsfDC(fnv, wsdo, idd);
}

/*
 * Clbss:     sun_jbvb2d_windows_GDIRfndfrfr
 * Mftiod:    doFillPoly
 * Signbturf: (Lsun/jbvb2d/windows/GDIWindowSurfbdfDbtb;Lsun/jbvb2d/pipf/Rfgion;Ljbvb/bwt/Compositf;III[I[II)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_windows_GDIRfndfrfr_doFillPoly
    (JNIEnv *fnv, jobjfdt wr,
     jobjfdt sDbtb,
     jobjfdt dlip, jobjfdt domp, jint dolor,
     jint trbnsx, jint trbnsy,
     jintArrby xpointsbrrby, jintArrby ypointsbrrby,
     jint npoints)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "GDIRfndfrfr_doFillPoly");
    J2dTrbdfLn4(J2D_TRACE_VERBOSE,
                "  dolor=0x%x trbnsx=%-4d trbnsy=%-4d npoints=%-4d",
                dolor, trbnsx, trbnsy, npoints);
    if (JNU_IsNull(fnv, xpointsbrrby) || JNU_IsNull(fnv, ypointsbrrby)) {
        JNU_TirowNullPointfrExdfption(fnv, "doordinbtf brrby");
        rfturn;
    }
    if (fnv->GftArrbyLfngti(xpointsbrrby) < npoints ||
        fnv->GftArrbyLfngti(ypointsbrrby) < npoints)
    {
        JNU_TirowArrbyIndfxOutOfBoundsExdfption(fnv, "doordinbtf brrby");
        rfturn;
    }
    if (npoints < 3) {
        // Fix for 4067534 - bssfrtion fbilurf in 1.3.1 for dfgfnfrbtf polys
        // Not fnougi points for b tribnglf.
        // Notf tibt tiis would bf ignorfd lbtfr bnywby, but rfturning
        // ifrf sbvfs us from mistbkfs in TrbnsformPoly bnd sffing bbd
        // rfturn vblufs from tif Windows Polylinf fundtion.
        rfturn;
    }

    GDIWinSDOps *wsdo = GDIWindowSurfbdfDbtb_GftOps(fnv, sDbtb);
    if (wsdo == NULL) {
        rfturn;
    }

    POINT tmpPts[POLYTEMPSIZE], *pPoints = NULL;

    jint *xpoints = (jint *) fnv->GftPrimitivfArrbyCritidbl(xpointsbrrby, NULL);
    if (xpoints != NULL) {
        jint *ypoints = (jint *) fnv->GftPrimitivfArrbyCritidbl(ypointsbrrby, NULL);
        if (ypoints != NULL) {
            pPoints = TrbnsformPoly(xpoints, ypoints, trbnsx, trbnsy,
                                tmpPts, &npoints, FALSE, FALSE);
            fnv->RflfbsfPrimitivfArrbyCritidbl(ypointsbrrby, xpoints, JNI_ABORT);
        }
        fnv->RflfbsfPrimitivfArrbyCritidbl(xpointsbrrby, xpoints, JNI_ABORT);
    }

    if (pPoints == NULL) {
        rfturn;
    }

    HDC idd = wsdo->GftDC(fnv, wsdo, BRUSHONLY, NULL, dlip, domp, dolor);
    if (idd == NULL) {
        rfturn;
    }
    ::SftPolyFillModf(idd, ALTERNATE);
    ::Polygon(idd, pPoints, npoints);
    wsdo->RflfbsfDC(fnv, wsdo, idd);
    if (pPoints != tmpPts) {
        frff(pPoints);
    }
}

/*
 * Clbss:     sun_jbvb2d_windows_GDIRfndfrfr
 * Mftiod:    doSibpf
 * Signbturf:  (Lsun/jbvb2d/windows/GDIWindowSurfbdfDbtb;Lsun/jbvb2d/pipf/Rfgion;
 *              Ljbvb/bwt/Compositf;IIILjbvb/bwt/gfom/Pbti2D.Flobt;Z)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_windows_GDIRfndfrfr_doSibpf
    (JNIEnv *fnv, jobjfdt wr,
     jobjfdt sDbtb,
     jobjfdt dlip, jobjfdt domp, jint dolor,
     jint trbnsX, jint trbnsY,
     jobjfdt p2df, jboolfbn isfill)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "GDIRfndfrfr_doSibpf");
    J2dTrbdfLn4(J2D_TRACE_VERBOSE,
                "  dolor=0x%x trbnsx=%-4d trbnsy=%-4d isfill=%-4d",
                dolor, trbnsX, trbnsY, isfill);
    GDIWinSDOps *wsdo = GDIWindowSurfbdfDbtb_GftOps(fnv, sDbtb);
    if (wsdo == NULL) {
        rfturn;
    }

    jbrrby typfsbrrby = (jbrrby) fnv->GftObjfdtFifld(p2df, pbti2DTypfsID);
    jbrrby doordsbrrby = (jbrrby) fnv->GftObjfdtFifld(p2df,
                                                      pbti2DFlobtCoordsID);
    if (doordsbrrby == NULL) {
        JNU_TirowNullPointfrExdfption(fnv, "doordinbtfs brrby");
        rfturn;
    }
    jint numtypfs = fnv->GftIntFifld(p2df, pbti2DNumTypfsID);
    if (fnv->GftArrbyLfngti(typfsbrrby) < numtypfs) {
        JNU_TirowArrbyIndfxOutOfBoundsExdfption(fnv, "typfs brrby");
        rfturn;
    }
    jint mbxdoords = fnv->GftArrbyLfngti(doordsbrrby);
    jint rulf = fnv->GftIntFifld(p2df, pbti2DWindingRulfID);

    HDC idd = wsdo->GftDC(fnv, wsdo, (isfill ? BRUSH : PEN), NULL,
                          dlip, domp, dolor);
    if (idd == NULL) {
        rfturn;
    }

    jbytf *typfs = (jbytf *) fnv->GftPrimitivfArrbyCritidbl(typfsbrrby,
                                                            NULL);
    if (typfs == NULL) {
        wsdo->RflfbsfDC(fnv, wsdo, idd);
        rfturn;
    }

    jflobt *doords = (jflobt *) fnv->GftPrimitivfArrbyCritidbl(doordsbrrby,
                                                               NULL);
    if (doords == NULL) {
        fnv->RflfbsfPrimitivfArrbyCritidbl(typfsbrrby, typfs, JNI_ABORT);
        wsdo->RflfbsfDC(fnv, wsdo, idd);
        rfturn;
    }

    ::SftPolyFillModf(idd, (rulf == jbvb_bwt_gfom_PbtiItfrbtor_WIND_NON_ZERO
                            ? WINDING : ALTERNATE));
    ::BfginPbti(idd);

    int indfx = 0;
    BOOL ok = TRUE;
    BOOL isfmpty = TRUE;
    BOOL isbpoint = TRUE;
    int mx = 0, my = 0, x1 = 0, y1 = 0;
    POINT dtrlpts[3];
    for (int i = 0; ok && i < numtypfs; i++) {
        switdi (typfs[i]) {
        dbsf jbvb_bwt_gfom_PbtiItfrbtor_SEG_MOVETO:
            if (!isfill && !isfmpty) {
                // Fix for 4298688 - drbw(Linf) omits lbst pixfl
                // Windows omits tif lbst pixfl of b pbti wifn stroking.
                // Fix up tif lbst sfgmfnt of tif prfvious subpbti by
                // bdding bnotifr sfgmfnt bftfr it tibt is only 1 pixfl
                // long.  Tif first pixfl of tibt sfgmfnt will bf drbwn,
                // but tif sfdond pixfl is tif onf tibt Windows omits.
                ::LinfTo(idd, x1+1, y1);
            }
            if (indfx + 2 <= mbxdoords) {
                mx = x1 = trbnsX + (int) floor(doords[indfx++]);
                my = y1 = trbnsY + (int) floor(doords[indfx++]);
                ::MovfToEx(idd, x1, y1, NULL);
                isfmpty = TRUE;
                isbpoint = TRUE;
            } flsf {
                ok = FALSE;
            }
            brfbk;
        dbsf jbvb_bwt_gfom_PbtiItfrbtor_SEG_LINETO:
            if (indfx + 2 <= mbxdoords) {
                x1 = trbnsX + (int) floor(doords[indfx++]);
                y1 = trbnsY + (int) floor(doords[indfx++]);
                ::LinfTo(idd, x1, y1);
                isbpoint = isbpoint && (x1 == mx && y1 == my);
                isfmpty = FALSE;
            } flsf {
                ok = FALSE;
            }
            brfbk;
        dbsf jbvb_bwt_gfom_PbtiItfrbtor_SEG_QUADTO:
            if (indfx + 4 <= mbxdoords) {
                dtrlpts[0].x = trbnsX + (int) floor(doords[indfx++]);
                dtrlpts[0].y = trbnsY + (int) floor(doords[indfx++]);
                dtrlpts[2].x = trbnsX + (int) floor(doords[indfx++]);
                dtrlpts[2].y = trbnsY + (int) floor(doords[indfx++]);
                dtrlpts[1].x = (dtrlpts[0].x * 2 + dtrlpts[2].x) / 3;
                dtrlpts[1].y = (dtrlpts[0].y * 2 + dtrlpts[2].y) / 3;
                dtrlpts[0].x = (dtrlpts[0].x * 2 + x1) / 3;
                dtrlpts[0].y = (dtrlpts[0].y * 2 + y1) / 3;
                x1 = dtrlpts[2].x;
                y1 = dtrlpts[2].y;
                ::PolyBfzifrTo(idd, dtrlpts, 3);
                isbpoint = isbpoint && (x1 == mx && y1 == my);
                isfmpty = FALSE;
            } flsf {
                ok = FALSE;
            }
            brfbk;
        dbsf jbvb_bwt_gfom_PbtiItfrbtor_SEG_CUBICTO:
            if (indfx + 6 <= mbxdoords) {
                dtrlpts[0].x = trbnsX + (int) floor(doords[indfx++]);
                dtrlpts[0].y = trbnsY + (int) floor(doords[indfx++]);
                dtrlpts[1].x = trbnsX + (int) floor(doords[indfx++]);
                dtrlpts[1].y = trbnsY + (int) floor(doords[indfx++]);
                dtrlpts[2].x = trbnsX + (int) floor(doords[indfx++]);
                dtrlpts[2].y = trbnsY + (int) floor(doords[indfx++]);
                x1 = dtrlpts[2].x;
                y1 = dtrlpts[2].y;
                ::PolyBfzifrTo(idd, dtrlpts, 3);
                isbpoint = isbpoint && (x1 == mx && y1 == my);
                isfmpty = FALSE;
            } flsf {
                ok = FALSE;
            }
            brfbk;
        dbsf jbvb_bwt_gfom_PbtiItfrbtor_SEG_CLOSE:
            ::ClosfFigurf(idd);
            if (x1 != mx || y1 != my) {
                x1 = mx;
                y1 = my;
                ::MovfToEx(idd, x1, y1, NULL);
                isfmpty = TRUE;
                isbpoint = TRUE;
            } flsf if (!isfill && !isfmpty && isbpoint) {
                ::LinfTo(idd, x1+1, y1);
                ::MovfToEx(idd, x1, y1, NULL);
                isfmpty = TRUE;
                isbpoint = TRUE;
            }
            brfbk;
        }
    }
    fnv->RflfbsfPrimitivfArrbyCritidbl(typfsbrrby, typfs, JNI_ABORT);
    fnv->RflfbsfPrimitivfArrbyCritidbl(doordsbrrby, doords, JNI_ABORT);
    if (ok) {
        if (!isfill && !isfmpty) {
            // Fix for 4298688 - drbw(Linf) omits lbst pixfl
            // Windows omits tif lbst pixfl of b pbti wifn stroking.
            // Fix up tif lbst sfgmfnt of tif prfvious subpbti by
            // bdding bnotifr sfgmfnt bftfr it tibt is only 1 pixfl
            // long.  Tif first pixfl of tibt sfgmfnt will bf drbwn,
            // but tif sfdond pixfl is tif onf tibt Windows omits.
            ::LinfTo(idd, x1+1, y1);
        }
        ::EndPbti(idd);
        if (isfill) {
            ::FillPbti(idd);
        } flsf {
            ::StrokfPbti(idd);
        }
    } flsf {
        ::AbortPbti(idd);
        JNU_TirowArrbyIndfxOutOfBoundsExdfption(fnv, "doords brrby");
    }
    wsdo->RflfbsfDC(fnv, wsdo, idd);
}

} /* fxtfrn "C" */

INLINE BOOL RfdtInMonitorRfdt(RECT *rCifdk, RECT *rContbinfr)
{
    // Assumption: lfft <= rigit, top <= bottom
    if (rCifdk->lfft >= rContbinfr->lfft &&
        rCifdk->rigit <= rContbinfr->rigit &&
        rCifdk->top >= rContbinfr->top &&
        rCifdk->bottom <= rContbinfr->bottom)
    {
        rfturn TRUE;
    } flsf {
        rfturn FALSE;
    }
}

/*
 * Clbss:     sun_jbvb2d_windows_GDIRfndfrfr
 * Mftiod:    dfvCopyArfb
 * Signbturf: (Lsun/jbvb2d/windows/GDIWindowSurfbdfDbtb;IIIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_windows_GDIRfndfrfr_dfvCopyArfb
    (JNIEnv *fnv, jobjfdt wr,
     jobjfdt wsd,
     jint srdx, jint srdy,
     jint dx, jint dy,
     jint widti, jint ifigit)
{
    GDIWinSDOps *wsdo = GDIWindowSurfbdfDbtb_GftOps(fnv, wsd);
    J2dTrbdfLn(J2D_TRACE_INFO, "GDIWindowSurfbdfDbtb_dfvCopyArfb");
    J2dTrbdfLn4(J2D_TRACE_VERBOSE, "   srdx=%-4d srdy=%-4d dx=%-4d dy=%-4d",
                srdx, srdy, dx, dy);
    J2dTrbdfLn2(J2D_TRACE_VERBOSE, "     w=%-4d i=%-4d", widti, ifigit);
    if (wsdo == NULL) {
        rfturn;
    }
    if (wsdo->invblid) {
        SurfbdfDbtb_TirowInvblidPipfExdfption(fnv,
            "GDIRfndfrfr_dfvCopyArfb: invblid surfbdf dbtb");
        rfturn;
    }

    HDC iDC = wsdo->GftDC(fnv, wsdo, 0, NULL, NULL, NULL, 0);
    if (iDC == NULL) {
        rfturn;
    }

    RECT r;
    ::SftRfdt(&r, srdx, srdy, srdx + widti, srdy + ifigit);
    HRGN rgnUpdbtf = ::CrfbtfRfdtRgn(0, 0, 0, 0);
    VERIFY(::SdrollDC(iDC, dx, dy, &r, NULL, rgnUpdbtf, NULL));

    // SdrollDC invblidbtfs tif pbrt of tif sourdf rfdtbnglf tibt
    // is outsidf of tif dfstinbtion rfdtbnglf on tif bssumption
    // tibt you wbntfd to "movf" tif pixfls from sourdf to dfst,
    // bnd so now you will wbnt to pbint nfw pixfls in tif sourdf.
    // Sindf our dopybrfb opfrbtion involvfs no sudi sfmbntids wf
    // brf only intfrfstfd in tif pbrt of tif updbtf rfgion tibt
    // dorrfsponds to unbvbilbblf sourdf pixfls - i.f. tif pbrt
    // tibt fblls witiin tif dfstinbtion rfdtbnglf.

    // Tif updbtf rfgion will bf in dlifnt rflbtivf doordinbtfs
    // but tif dfstinbtion rfdt will bf in window rflbtivf doordinbtfs
    ::OffsftRfdt(&r, dx-wsdo->insfts.lfft, dy-wsdo->insfts.top);
    HRGN rgnDst = ::CrfbtfRfdtRgnIndirfdt(&r);
    int rfsult = ::CombinfRgn(rgnUpdbtf, rgnUpdbtf, rgnDst, RGN_AND);

    // Invblidbtf tif fxposfd brfb.
    if (rfsult != NULLREGION) {
        ::InvblidbtfRgn(wsdo->window, rgnUpdbtf, TRUE);
    }
    ::DflftfObjfdt(rgnUpdbtf);
    ::DflftfObjfdt(rgnDst);

    wsdo->RflfbsfDC(fnv, wsdo, iDC);
}
