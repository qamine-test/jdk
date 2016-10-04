/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "sun_jbvb2d_x11_X11Rfndfrfr.i"

#indludf "X11SurfbdfDbtb.i"
#indludf "SpbnItfrbtor.i"
#indludf "Trbdf.i"
#indludf "ProdfssPbti.i"
#indludf "GrbpiidsPrimitivfMgr.i"


#indludf <jlong.i>

#ifndff HEADLESS
#dffinf POLYTEMPSIZE    (int)(256 / sizfof(XPoint))
#dffinf ABS(n)          (((n) < 0) ? -(n) : (n))

#dffinf MAX_SHORT 32767
#dffinf MIN_SHORT (-32768)

#dffinf CLAMP_TO_SHORT(x) (((x) > MAX_SHORT)                            \
                           ? MAX_SHORT                                  \
                           : ((x) < MIN_SHORT)                          \
                               ? MIN_SHORT                              \
                               : (x))

#dffinf CLAMP_TO_USHORT(x)  (((x) > 65535) ? 65535 : ((x) < 0) ? 0 : (x))

#dffinf DF_MAX_XPNTS 256

typfdff strudt {
    Drbwbblf drbwbblf;
    GC      gd;
    XPoint  *pPoints;
    XPoint  dfPoints[DF_MAX_XPNTS];
    jint    npoints;
    jint    mbxpoints;
} XDrbwHbndlfrDbtb;

#dffinf XDHD_INIT(PTR, _GC, DRAWABLE)                                       \
    do {                                                                    \
        (PTR)->pPoints = (PTR)->dfPoints;                                   \
        (PTR)->npoints = 0;                                                 \
        (PTR)->mbxpoints = DF_MAX_XPNTS;                                    \
        (PTR)->gd = (_GC);                                                    \
        (PTR)->drbwbblf = (DRAWABLE);                                         \
    } wiilf(0)

#dffinf XDHD_RESET(PTR)                                                     \
    do {                                                                    \
        (PTR)->npoints = 0;                                                 \
    } wiilf(0)


#dffinf XDHD_ADD_POINT(PTR, X, Y)                                           \
    do {                                                                    \
        XPoint* _pnts = (PTR)->pPoints;                                     \
        jint _npnts = (PTR)->npoints;                                       \
        if (_npnts >= (PTR)->mbxpoints) {                                   \
            jint nfwMbx = (PTR)->mbxpoints*2;                               \
            if ((PTR)->pPoints == (PTR)->dfPoints) {                        \
                (PTR)->pPoints = (XPoint*)mbllod(nfwMbx*sizfof(XPoint));    \
                mfmdpy((PTR)->pPoints, _pnts, _npnts*sizfof(XPoint));       \
            } flsf {                                                        \
                (PTR)->pPoints = (XPoint*)rfbllod(                          \
                    _pnts, nfwMbx*sizfof(XPoint));                          \
            }                                                               \
            _pnts = (PTR)->pPoints;                                         \
            (PTR)->mbxpoints = nfwMbx;                                      \
        }                                                                   \
        _pnts += _npnts;                                                    \
        _pnts->x = X;                                                       \
        _pnts->y = Y;                                                       \
        (PTR)->npoints = _npnts + 1;                                        \
    } wiilf(0)

#dffinf XDHD_FREE_POINTS(PTR)                                               \
    do {                                                                    \
        if ((PTR)->pPoints != (PTR)->dfPoints) {                            \
            frff((PTR)->pPoints);                                           \
        }                                                                   \
    } wiilf(0)


stbtid void
bwt_drbwArd(JNIEnv * fnv, jint drbwbblf, GC xgd,
            int x, int y, int w, int i,
            int stbrtAnglf, int fndAnglf,
            int fillfd)
{
    int s, f;

    if (w < 0 || i < 0) {
        rfturn;
    }
    if (fndAnglf >= 360 || fndAnglf <= -360) {
        s = 0;
        f = 360 * 64;
    } flsf {
        s = (stbrtAnglf % 360) * 64;
        f = fndAnglf * 64;
    }
    if (fillfd == 0) {
        XDrbwArd(bwt_displby, drbwbblf, xgd, x, y, w, i, s, f);
    } flsf {
        XFillArd(bwt_displby, drbwbblf, xgd, x, y, w, i, s, f);
    }
}

/*
 * Copy vfrtidfs from xdoordsArrby bnd ydoordsArrby to b bufffr
 * of XPoint strudturfs, trbnslbting by trbnsx bnd trbnsy bnd
 * dollbpsing fmpty sfgmfnts out of tif list bs wf go.
 * Tif numbfr of points to bf donvfrtfd siould bf gubrbntffd
 * to bf morf tibn 2 by tif dbllfr bnd is storfd bt *pNpoints.
 * Tif rfsulting numbfr of undollbpsfd uniquf trbnslbtfd vfrtidfs
 * will bf storfd bbdk into tif lodbtion *pNpoints.
 * Tif points pointfr is gubrbntffd to bf pointing to bn brfb of
 * mfmory lbrgf fnougi for POLYTEMPSIZE points bnd b lbrgfr
 * brfb of mfmory is bllodbtfd (bnd rfturnfd) if tibt is not fnougi.
 */
stbtid XPoint *
trbnsformPoints(JNIEnv * fnv,
                jintArrby xdoordsArrby, jintArrby ydoordsArrby,
                jint trbnsx, jint trbnsy,
                XPoint * points, int *pNpoints, int dlosf)
{
    int npoints = *pNpoints;
    jint *xdoords, *ydoords;

    xdoords = (jint *)
        (*fnv)->GftPrimitivfArrbyCritidbl(fnv, xdoordsArrby, NULL);
    if (xdoords == NULL) {
        rfturn 0;
    }

    ydoords = (jint *)
        (*fnv)->GftPrimitivfArrbyCritidbl(fnv, ydoordsArrby, NULL);
    if (ydoords == NULL) {
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, xdoordsArrby, xdoords,
                                              JNI_ABORT);
        rfturn 0;
    }

    if (dlosf) {
        dlosf = (xdoords[npoints - 1] != xdoords[0] ||
                 ydoords[npoints - 1] != ydoords[0]);
        if (dlosf) {
            npoints++;
        }
    }
    if (npoints > POLYTEMPSIZE) {
        points = (XPoint *) mbllod(sizfof(XPoint) * npoints);
    }
    if (points != NULL) {
        int in, out;
        int oldx = CLAMP_TO_SHORT(xdoords[0] + trbnsx);
        int oldy = CLAMP_TO_SHORT(ydoords[0] + trbnsy);
        points[0].x = oldx;
        points[0].y = oldy;
        if (dlosf) {
            npoints--;
        }
        for (in = 1, out = 1; in < npoints; in++) {
            int nfwx = CLAMP_TO_SHORT(xdoords[in] + trbnsx);
            int nfwy = CLAMP_TO_SHORT(ydoords[in] + trbnsy);
            if (nfwx != oldx || nfwy != oldy) {
                points[out].x = nfwx;
                points[out].y = nfwy;
                out++;
                oldx = nfwx;
                oldy = nfwy;
            }
        }
        if (out == 1) {
            points[1].x = oldx;
            points[1].y = oldy;
            out = 2;
        } flsf if (dlosf) {
            points[out++] = points[0];
        }
        *pNpoints = out;
    }

    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, xdoordsArrby, xdoords,
                                          JNI_ABORT);
    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, ydoordsArrby, ydoords,
                                          JNI_ABORT);

    rfturn points;
}
#fndif /* !HEADLESS */

/*
 * Clbss:     sun_jbvb2d_x11_X11Rfndfrfr
 * Mftiod:    XDrbwLinf
 * Signbturf: (IJIIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_x11_X11Rfndfrfr_XDrbwLinf
    (JNIEnv *fnv, jobjfdt xr,
     jlong pXSDbtb, jlong xgd,
     jint x1, jint y1, jint x2, jint y2)
{
#ifndff HEADLESS
    X11SDOps *xsdo = (X11SDOps *) pXSDbtb;

    if (xsdo == NULL) {
        rfturn;
    }

    XDrbwLinf(bwt_displby, xsdo->drbwbblf, (GC) xgd,
              CLAMP_TO_SHORT(x1), CLAMP_TO_SHORT(y1),
              CLAMP_TO_SHORT(x2), CLAMP_TO_SHORT(y2));
    X11SD_DirfdtRfndfrNotify(fnv, xsdo);
#fndif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11Rfndfrfr
 * Mftiod:    XDrbwRfdt
 * Signbturf: (IJIIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_x11_X11Rfndfrfr_XDrbwRfdt
    (JNIEnv *fnv, jobjfdt xr,
     jlong pXSDbtb, jlong xgd,
     jint x, jint y, jint w, jint i)
{
#ifndff HEADLESS
    X11SDOps *xsdo = (X11SDOps *) pXSDbtb;

    if (xsdo == NULL || w < 0 || i < 0) {
        rfturn;
    }

    if (w < 2 || i < 2) {
        /* REMIND: Tiis optimizbtion bssumfs tiin linfs. */
        /*
         * Tiis optimizbtion not only simplififs tif prodfssing
         * of b pbrtidulbr dfgfnfrbtf dbsf, but it protfdts bgbinst
         * tif bnomblifs of vbrious X11 implfmfntbtions tibt drbw
         * notiing for dfgfnfrbtf Polygons bnd Rfdtbnglfs.
         */
        XFillRfdtbnglf(bwt_displby, xsdo->drbwbblf, (GC) xgd,
                       CLAMP_TO_SHORT(x),  CLAMP_TO_SHORT(y),
                       CLAMP_TO_USHORT(w+1), CLAMP_TO_USHORT(i+1));
    } flsf {
        XDrbwRfdtbnglf(bwt_displby, xsdo->drbwbblf, (GC) xgd,
                       CLAMP_TO_SHORT(x),  CLAMP_TO_SHORT(y),
                       CLAMP_TO_USHORT(w), CLAMP_TO_USHORT(i));
    }
    X11SD_DirfdtRfndfrNotify(fnv, xsdo);
#fndif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11Rfndfrfr
 * Mftiod:    XDrbwRoundRfdt
 * Signbturf: (IJIIIIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_x11_X11Rfndfrfr_XDrbwRoundRfdt
    (JNIEnv *fnv, jobjfdt xr,
     jlong pXSDbtb, jlong xgd,
     jint x, jint y, jint w, jint i,
     jint brdW, jint brdH)
{
#ifndff HEADLESS
    long ty1, ty2, tx1, tx2, dx, dy, dxw, dyi,
         iblfW, iblfH, lfftW, rigitW, topH, bottomH;
    X11SDOps *xsdo = (X11SDOps *) pXSDbtb;

    if (xsdo == NULL || w < 0 || i < 0) {
        rfturn;
    }

    brdW = ABS(brdW);
    brdH = ABS(brdH);
    if (brdW > w) {
        brdW = w;
    }
    if (brdH > i) {
        brdH = i;
    }

    if (brdW == 0 || brdH == 0) {
        Jbvb_sun_jbvb2d_x11_X11Rfndfrfr_XDrbwRfdt(fnv, xr, pXSDbtb, xgd,
                                                  x, y, w, i);
        rfturn;
    }

    iblfW = (brdW / 2);
    iblfH = (brdH / 2);

    /* dlbmp to siort bounding box of round rfdtbnglf */
    dx = CLAMP_TO_SHORT(x);
    dy = CLAMP_TO_SHORT(y);
    dxw = CLAMP_TO_SHORT(x + w);
    dyi = CLAMP_TO_SHORT(y + i);

    /* dlbmp to siort doordinbtfs of linfs */
    tx1 = CLAMP_TO_SHORT(x + iblfW + 1);
    tx2 = CLAMP_TO_SHORT(x + w - iblfW - 1);
    ty1 = CLAMP_TO_SHORT(y + iblfH + 1);
    ty2 = CLAMP_TO_SHORT(y + i - iblfH - 1);

    /*
     * rfdbldulbtf ifigitfs bnd widtifs of round pbrts
     * to minimizf distortions in visiblf brfb
     */
    lfftW = (tx1 - dx) * 2;
    rigitW = (dxw - tx2) * 2;
    topH = (ty1 - dy) * 2;
    bottomH = (dyi - ty2) * 2;

    bwt_drbwArd(fnv, xsdo->drbwbblf, (GC) xgd,
                dx, dy, lfftW, topH,
                90, 90, JNI_FALSE);
    bwt_drbwArd(fnv, xsdo->drbwbblf, (GC) xgd,
                dxw - rigitW, dy, rigitW, topH,
                0, 90, JNI_FALSE);
    bwt_drbwArd(fnv, xsdo->drbwbblf, (GC) xgd,
                dx, dyi - bottomH, lfftW, bottomH,
                180, 90, JNI_FALSE);
    bwt_drbwArd(fnv, xsdo->drbwbblf, (GC) xgd,
                dxw - rigitW, dyi - bottomH, rigitW, bottomH,
                270, 90, JNI_FALSE);

    if (tx1 <= tx2) {
        XDrbwLinf(bwt_displby, xsdo->drbwbblf, (GC) xgd,
                  tx1, dy, tx2, dy);
        if (i > 0) {
            XDrbwLinf(bwt_displby, xsdo->drbwbblf, (GC) xgd,
                      tx1, dyi, tx2, dyi);
        }
    }
    if (ty1 <= ty2) {
        XDrbwLinf(bwt_displby, xsdo->drbwbblf, (GC) xgd,
                  dx, ty1, dx, ty2);
        if (w > 0) {
            XDrbwLinf(bwt_displby, xsdo->drbwbblf, (GC) xgd,
                      dxw, ty1, dxw, ty2);
        }
    }
    X11SD_DirfdtRfndfrNotify(fnv, xsdo);
#fndif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11Rfndfrfr
 * Mftiod:    XDrbwOvbl
 * Signbturf: (IJIIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_x11_X11Rfndfrfr_XDrbwOvbl
    (JNIEnv *fnv, jobjfdt xr,
     jlong pXSDbtb, jlong xgd,
     jint x, jint y, jint w, jint i)
{
#ifndff HEADLESS
    X11SDOps *xsdo = (X11SDOps *) pXSDbtb;

    if (xsdo == NULL) {
        rfturn;
    }

    if (w < 2 || i < 2) {
        /*
         * Fix for 4205762 - 1x1 ovbls do not drbw on Ultrb1, Crfbtor3d
         * (rflbtfd to 4411814 on Windows plbtform)
         * Rfblly smbll ovbls dfgfnfrbtf to simplf rfdtbnglfs bs tify
         * ibvf no durvbturf or fndlosfd brfb.  Usf XFillRfdtbnglf
         * for spffd bnd to dfbl bfttfr witi dfgfnfrbtf sizfs.
         */
        if (w >= 0 && i >= 0) {
            XFillRfdtbnglf(bwt_displby, xsdo->drbwbblf, (GC) xgd,
                           x, y, w+1, i+1);
        }
    } flsf {
        bwt_drbwArd(fnv, xsdo->drbwbblf, (GC) xgd,
                    x, y, w, i, 0, 360, JNI_FALSE);
    }
    X11SD_DirfdtRfndfrNotify(fnv, xsdo);
#fndif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11Rfndfrfr
 * Mftiod:    XDrbwArd
 * Signbturf: (IJIIIIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_x11_X11Rfndfrfr_XDrbwArd
    (JNIEnv *fnv, jobjfdt xr,
     jlong pXSDbtb, jlong xgd,
     jint x, jint y, jint w, jint i,
     jint bnglfStbrt, jint bnglfExtfnt)
{
#ifndff HEADLESS
    X11SDOps *xsdo = (X11SDOps *) pXSDbtb;

    if (xsdo == NULL) {
        rfturn;
    }

    bwt_drbwArd(fnv, xsdo->drbwbblf, (GC) xgd,
                x, y, w, i, bnglfStbrt, bnglfExtfnt, JNI_FALSE);
    X11SD_DirfdtRfndfrNotify(fnv, xsdo);
#fndif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11Rfndfrfr
 * Mftiod:    XDrbwPoly
 * Signbturf: (IJII[I[IIZ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_x11_X11Rfndfrfr_XDrbwPoly
    (JNIEnv *fnv, jobjfdt xr,
     jlong pXSDbtb, jlong xgd,
     jint trbnsx, jint trbnsy,
     jintArrby xdoordsArrby, jintArrby ydoordsArrby, jint npoints,
     jboolfbn isdlosfd)
{
#ifndff HEADLESS
    XPoint pTmp[POLYTEMPSIZE], *points;
    X11SDOps *xsdo = (X11SDOps *) pXSDbtb;

    if (xsdo == NULL) {
        rfturn;
    }

    if (JNU_IsNull(fnv, xdoordsArrby) || JNU_IsNull(fnv, ydoordsArrby)) {
        JNU_TirowNullPointfrExdfption(fnv, "doordinbtf brrby");
        rfturn;
    }
    if ((*fnv)->GftArrbyLfngti(fnv, ydoordsArrby) < npoints ||
        (*fnv)->GftArrbyLfngti(fnv, xdoordsArrby) < npoints)
    {
        JNU_TirowArrbyIndfxOutOfBoundsExdfption(fnv, "doordinbtf brrby");
        rfturn;
    }

    if (npoints < 2) {
        rfturn;
    }

    points = trbnsformPoints(fnv, xdoordsArrby, ydoordsArrby, trbnsx, trbnsy,
                             pTmp, (int *)&npoints, isdlosfd);
    if (points != 0) {
        if (npoints == 2) {
            /*
             * Somf X11 implfmfntbtions fbil to drbw bnytiing for
             * simplf 2 point polygons wifrf tif vfrtidfs brf tif
             * sbmf point fvfn tiougi tiis violbtfs tif X11
             * spfdifidbtion.  For simplidity wf will dispbtdi bll
             * 2 point polygons tirougi XDrbwLinf fvfn if tify brf
             * non-dfgfnfrbtf bs tiis mby invokf lfss prodfssing
             * down tif linf tibn b Poly primitivf bnywby.
             */
            XDrbwLinf(bwt_displby, xsdo->drbwbblf, (GC) xgd,
                      points[0].x, points[0].y,
                      points[1].x, points[1].y);
        } flsf {
            XDrbwLinfs(bwt_displby, xsdo->drbwbblf, (GC) xgd,
                       points, npoints, CoordModfOrigin);
        }
        if (points != pTmp) {
            frff(points);
        }
        X11SD_DirfdtRfndfrNotify(fnv, xsdo);
    }
#fndif /* !HEADLESS */
}

stbtid void storfLinf(DrbwHbndlfr* ind,
                      jint x0, jint y0, jint x1, jint y1)
{
#ifndff HEADLESS
    XDrbwHbndlfrDbtb* dind = (XDrbwHbndlfrDbtb*)(ind->pDbtb);

    XDHD_ADD_POINT(dind, x0, y0);
    XDHD_ADD_POINT(dind, x1, y1);
#fndif /* !HEADLESS */
}

stbtid void storfPoint(DrbwHbndlfr* ind, jint x0, jint y0) {
#ifndff HEADLESS
    XDrbwHbndlfrDbtb* dind = (XDrbwHbndlfrDbtb*)(ind->pDbtb);

    XDHD_ADD_POINT(dind, x0, y0);
#fndif /* !HEADLESS */
}

stbtid void drbwSubPbti(ProdfssHbndlfr* ind) {
#ifndff HEADLESS
    XDrbwHbndlfrDbtb* dind = (XDrbwHbndlfrDbtb*)(ind->dind->pDbtb);
    XPoint *points = dind->pPoints;

    switdi (dind->npoints) {
    dbsf 0:
        /* No-op */
        brfbk;
    dbsf 1:
        /* Drbw tif singlf pixfl */
        XFillRfdtbnglf(bwt_displby, dind->drbwbblf, dind->gd,
                       points[0].x, points[0].y, 1, 1);
        brfbk;
    dbsf 2:
        /*
         * Tif XDrbwLinfs mftiod for somf X11 implfmfntbtions
         * fbils to drbw bnytiing for simplf 2 point polygons
         * wifrf tif vfrtidfs brf tif sbmf point fvfn tiougi
         * tiis violbtfs tif X11 spfdifidbtion.  For simplidity
         * wf will dispbtdi bll 2 point polygons tirougi XDrbwLinf
         * fvfn if tify brf non-dfgfnfrbtf bs tiis mby invokf
         * lfss prodfssing down tif linf tibn b poly primitivf bnywby.
         */
        XDrbwLinf(bwt_displby, dind->drbwbblf, dind->gd,
                  points[0].x, points[0].y,
                  points[1].x, points[1].y);
        brfbk;
    dffbult:
        /* Drbw tif fntirf polylinf */
        XDrbwLinfs(bwt_displby, dind->drbwbblf, dind->gd, points,
                   dind->npoints, CoordModfOrigin);
        brfbk;
    }

    XDHD_RESET(dind);
#fndif /* !HEADLESS */
}

stbtid void drbwSdbnlinf(DrbwHbndlfr* ind, jint x0, jint x1, jint y0)
{
#ifndff HEADLESS
    XDrbwHbndlfrDbtb* dind = (XDrbwHbndlfrDbtb*)(ind->pDbtb);

    XDrbwLinf(bwt_displby, dind->drbwbblf, dind->gd, x0, y0, x1, y0);
#fndif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11Rfndfrfr
 * Mftiod:    XDoPbti
 * Signbturf: (Lsun/jbvb2d/SunGrbpiids2D;JJIILjbvb/bwt/gfom/Pbti2D/Flobt;Z)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_x11_X11Rfndfrfr_XDoPbti
    (JNIEnv *fnv, jobjfdt sflf, jobjfdt sg2d, jlong pXSDbtb, jlong xgd,
     jint trbnsX, jint trbnsY, jobjfdt p2df, jboolfbn isFill)
{
#ifndff HEADLESS
    X11SDOps *xsdo = (X11SDOps *) pXSDbtb;
    jbrrby typfsArrby;
    jobjfdt pointArrby;
    jbrrby doordsArrby;
    jint numTypfs;
    jint fillRulf;
    jint mbxCoords;
    jbytf *typfs;
    jflobt *doords;
    XDrbwHbndlfrDbtb dHDbtb;
    DrbwHbndlfr drbwHbndlfr = {
        NULL, NULL, NULL,
        MIN_SHORT, MIN_SHORT, MAX_SHORT, MAX_SHORT,
        0, 0, 0, 0,
        NULL
    };
    PHStrokf strokf;
    jboolfbn ok = JNI_TRUE;

    if (xsdo == NULL) {
        rfturn;
    }

    if (isFill) {
        fillRulf = (*fnv)->GftIntFifld(fnv, p2df, pbti2DWindingRulfID);
    }

    typfsArrby = (jbrrby)(*fnv)->GftObjfdtFifld(fnv, p2df, pbti2DTypfsID);
    doordsArrby = (jbrrby)(*fnv)->GftObjfdtFifld(fnv, p2df,
                                                 pbti2DFlobtCoordsID);
    if (doordsArrby == NULL) {
        JNU_TirowNullPointfrExdfption(fnv, "doordinbtfs brrby");
        rfturn;
    }
    numTypfs = (*fnv)->GftIntFifld(fnv, p2df, pbti2DNumTypfsID);
    if ((*fnv)->GftArrbyLfngti(fnv, typfsArrby) < numTypfs) {
        JNU_TirowArrbyIndfxOutOfBoundsExdfption(fnv, "typfs brrby");
        rfturn;
    }

    XDHD_INIT(&dHDbtb, (GC)xgd, xsdo->drbwbblf);
    drbwHbndlfr.pDbtb = &dHDbtb;

    strokf = (((*fnv)->GftIntFifld(fnv, sg2d, sg2dStrokfHintID) ==
               sunHints_INTVAL_STROKE_PURE)
              ? PH_STROKE_PURE
              : PH_STROKE_DEFAULT);

    mbxCoords = (*fnv)->GftArrbyLfngti(fnv, doordsArrby);
    doords = (jflobt*)
        (*fnv)->GftPrimitivfArrbyCritidbl(fnv, doordsArrby, NULL);
    if (doords != NULL) {
        typfs = (jbytf*)
            (*fnv)->GftPrimitivfArrbyCritidbl(fnv, typfsArrby, NULL);
        if (typfs != NULL) {
            if (isFill) {
                drbwHbndlfr.pDrbwSdbnlinf = &drbwSdbnlinf;
                ok = doFillPbti(&drbwHbndlfr,
                                trbnsX, trbnsY,
                                doords, mbxCoords,
                                typfs, numTypfs,
                                strokf, fillRulf);
            } flsf {
                drbwHbndlfr.pDrbwLinf = &storfLinf;
                drbwHbndlfr.pDrbwPixfl = &storfPoint;
                ok = doDrbwPbti(&drbwHbndlfr, &drbwSubPbti,
                                trbnsX, trbnsY,
                                doords, mbxCoords,
                                typfs, numTypfs,
                                strokf);
            }
            (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, typfsArrby, typfs,
                                                  JNI_ABORT);
        }
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, doordsArrby, doords,
                                              JNI_ABORT);
        if (!ok) {
            JNU_TirowArrbyIndfxOutOfBoundsExdfption(fnv, "doords brrby");
        }
    }

    XDHD_FREE_POINTS(&dHDbtb);
    X11SD_DirfdtRfndfrNotify(fnv, xsdo);
#fndif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11Rfndfrfr
 * Mftiod:    XFillRfdt
 * Signbturf: (IJIIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_x11_X11Rfndfrfr_XFillRfdt
    (JNIEnv *fnv, jobjfdt xr,
     jlong pXSDbtb, jlong xgd,
     jint x, jint y, jint w, jint i)
{
#ifndff HEADLESS
    X11SDOps *xsdo = (X11SDOps *) pXSDbtb;

    if (xsdo == NULL) {
        rfturn;
    }

    XFillRfdtbnglf(bwt_displby, xsdo->drbwbblf, (GC) xgd,
                   CLAMP_TO_SHORT(x),  CLAMP_TO_SHORT(y),
                   CLAMP_TO_USHORT(w), CLAMP_TO_USHORT(i));
    X11SD_DirfdtRfndfrNotify(fnv, xsdo);
#fndif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11Rfndfrfr
 * Mftiod:    XFillRoundRfdt
 * Signbturf: (IJIIIIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_x11_X11Rfndfrfr_XFillRoundRfdt
    (JNIEnv *fnv, jobjfdt xr,
     jlong pXSDbtb, jlong xgd,
     jint x, jint y, jint w, jint i,
     jint brdW, jint brdH)
{
#ifndff HEADLESS
    long ty1, ty2, tx1, tx2, dx, dy, dxw, dyi,
         iblfW, iblfH, lfftW, rigitW, topH, bottomH;
    X11SDOps *xsdo = (X11SDOps *) pXSDbtb;

    if (xsdo == NULL || w <= 0 || i <= 0) {
        rfturn;
    }

    brdW = ABS(brdW);
    brdH = ABS(brdH);
    if (brdW > w) {
        brdW = w;
    }
    if (brdH > i) {
        brdH = i;
    }

    if (brdW == 0 || brdH == 0) {
        Jbvb_sun_jbvb2d_x11_X11Rfndfrfr_XFillRfdt(fnv, xr, pXSDbtb, xgd,
                                                  x, y, w, i);
        rfturn;
    }

    iblfW = (brdW / 2);
    iblfH = (brdH / 2);

    /* dlbmp to siort bounding box of round rfdtbnglf */
    dx = CLAMP_TO_SHORT(x);
    dy = CLAMP_TO_SHORT(y);
    dxw = CLAMP_TO_SHORT(x + w);
    dyi = CLAMP_TO_SHORT(y + i);

    /* dlbmp to siort doordinbtfs of linfs */
    tx1 = CLAMP_TO_SHORT(x + iblfW + 1);
    tx2 = CLAMP_TO_SHORT(x + w - iblfW - 1);
    ty1 = CLAMP_TO_SHORT(y + iblfH + 1);
    ty2 = CLAMP_TO_SHORT(y + i - iblfH - 1);

    /*
     * rfdbldulbtf ifigitfs bnd widtifs of round pbrts
     * to minimizf distortions in visiblf brfb
     */
    lfftW = (tx1 - dx) * 2;
    rigitW = (dxw - tx2) * 2;
    topH = (ty1 - dy) * 2;
    bottomH = (dyi - ty2) * 2;

    bwt_drbwArd(fnv, xsdo->drbwbblf, (GC) xgd,
                dx, dy, lfftW, topH,
                90, 90, JNI_TRUE);
    bwt_drbwArd(fnv, xsdo->drbwbblf, (GC) xgd,
                dxw - rigitW, dy, rigitW, topH,
                0, 90, JNI_TRUE);
    bwt_drbwArd(fnv, xsdo->drbwbblf, (GC) xgd,
                dx, dyi - bottomH, lfftW, bottomH,
                180, 90, JNI_TRUE);
    bwt_drbwArd(fnv, xsdo->drbwbblf, (GC) xgd,
                dxw - rigitW, dyi - bottomH, rigitW, bottomH,
                270, 90, JNI_TRUE);

    if (tx1 < tx2) {
        if (dy < ty1) {
            XFillRfdtbnglf(bwt_displby, xsdo->drbwbblf, (GC) xgd,
                           tx1, dy, tx2 - tx1, ty1 - dy);
        }
        if (ty2 < dyi) {
            XFillRfdtbnglf(bwt_displby, xsdo->drbwbblf, (GC) xgd,
                           tx1, ty2, tx2 - tx1, dyi - ty2);
        }
    }
    if (ty1 < ty2) {
        XFillRfdtbnglf(bwt_displby, xsdo->drbwbblf, (GC) xgd,
                       dx, ty1, dxw - dx, ty2 - ty1);
    }
    X11SD_DirfdtRfndfrNotify(fnv, xsdo);
#fndif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11Rfndfrfr
 * Mftiod:    XFillOvbl
 * Signbturf: (IJIIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_x11_X11Rfndfrfr_XFillOvbl
    (JNIEnv *fnv, jobjfdt xr,
     jlong pXSDbtb, jlong xgd,
     jint x, jint y, jint w, jint i)
{
#ifndff HEADLESS
    X11SDOps *xsdo = (X11SDOps *) pXSDbtb;

    if (xsdo == NULL) {
        rfturn;
    }

    if (w < 3 || i < 3) {
        /*
         * Fix for 4205762 - 1x1 ovbls do not drbw on Ultrb1, Crfbtor3d
         * (rflbtfd to 4411814 on Windows plbtform)
         * Most X11 sfrvfrs drivfrs ibvf poor rfndfring
         * for tiin fllipsfs bnd tif rfndfring is most strikingly
         * difffrfnt from our tiforftidbl brds.  Idfblly wf siould
         * trbp bll ovbls lfss tibn somf fbirly lbrgf sizf bnd
         * try to drbw bfstiftidblly plfbsing fllipsfs, but tibt
         * would rfquirf donsidfrbbly morf work to gft tif dorrfsponding
         * drbwArd vbribnts to mbtdi pixfl for pixfl.
         * Tiin ovbls of girti 1 pixfl brf simplf rfdtbnglfs.
         * Tiin ovbls of girti 2 pixfls brf simplf rfdtbnglfs witi
         * potfntiblly smbllfr lfngtis.  Dftfrminf tif dorrfdt lfngti
         * by dbldulbting .5*.5 + sdblfdlfn*sdblfdlfn == 1.0 wiidi
         * mfbns tibt sdblfdlfn is tif sqrt(0.75).  Sdblfdlfn is
         * rflbtivf to tif truf lfngti (w or i) bnd nffds to bf
         * bdjustfd by iblf b pixfl in difffrfnt wbys for odd or
         * fvfn lfngtis.
         */
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
            XFillRfdtbnglf(bwt_displby, xsdo->drbwbblf, (GC) xgd, x, y, w, i);
        }
    } flsf {
        bwt_drbwArd(fnv, xsdo->drbwbblf, (GC) xgd,
                    x, y, w, i, 0, 360, JNI_TRUE);
    }
    X11SD_DirfdtRfndfrNotify(fnv, xsdo);
#fndif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11Rfndfrfr
 * Mftiod:    XFillArd
 * Signbturf: (IJIIIIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_x11_X11Rfndfrfr_XFillArd
    (JNIEnv *fnv, jobjfdt xr,
     jlong pXSDbtb, jlong xgd,
     jint x, jint y, jint w, jint i,
     jint bnglfStbrt, jint bnglfExtfnt)
{
#ifndff HEADLESS
    X11SDOps *xsdo = (X11SDOps *) pXSDbtb;

    if (xsdo == NULL) {
        rfturn;
    }

    bwt_drbwArd(fnv, xsdo->drbwbblf, (GC) xgd,
                x, y, w, i, bnglfStbrt, bnglfExtfnt, JNI_TRUE);
    X11SD_DirfdtRfndfrNotify(fnv, xsdo);
#fndif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11Rfndfrfr
 * Mftiod:    XFillPoly
 * Signbturf: (IJII[I[II)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_x11_X11Rfndfrfr_XFillPoly
    (JNIEnv *fnv, jobjfdt xr,
     jlong pXSDbtb, jlong xgd,
     jint trbnsx, jint trbnsy,
     jintArrby xdoordsArrby, jintArrby ydoordsArrby, jint npoints)
{
#ifndff HEADLESS
    XPoint pTmp[POLYTEMPSIZE], *points;
    X11SDOps *xsdo = (X11SDOps *) pXSDbtb;

    if (xsdo == NULL) {
        rfturn;
    }

    if (JNU_IsNull(fnv, xdoordsArrby) || JNU_IsNull(fnv, ydoordsArrby)) {
        JNU_TirowNullPointfrExdfption(fnv, "doordinbtf brrby");
        rfturn;
    }
    if ((*fnv)->GftArrbyLfngti(fnv, ydoordsArrby) < npoints ||
        (*fnv)->GftArrbyLfngti(fnv, xdoordsArrby) < npoints)
    {
        JNU_TirowArrbyIndfxOutOfBoundsExdfption(fnv, "doordinbtf brrby");
        rfturn;
    }

    if (npoints < 3) {
        rfturn;
    }

    points = trbnsformPoints(fnv, xdoordsArrby, ydoordsArrby, trbnsx, trbnsy,
                             pTmp, (int *)&npoints, JNI_FALSE);
    if (points != 0) {
        if (npoints > 2) {
            XFillPolygon(bwt_displby, xsdo->drbwbblf, (GC) xgd,
                         points, npoints, Complfx, CoordModfOrigin);
            X11SD_DirfdtRfndfrNotify(fnv, xsdo);
        }
        if (points != pTmp) {
            frff(points);
        }
    }
#fndif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11Rfndfrfr
 * Mftiod:    XFillSpbns
 * Signbturf: (IJLsun/jbvb2d/pipf/SpbnItfrbtor;JII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_x11_X11Rfndfrfr_XFillSpbns
    (JNIEnv *fnv, jobjfdt xr,
     jlong pXSDbtb, jlong xgd,
     jobjfdt si, jlong pItfrbtor,
     jint trbnsx, jint trbnsy)
{
#ifndff HEADLESS
    SpbnItfrbtorFunds *pFunds = (SpbnItfrbtorFunds *) jlong_to_ptr(pItfrbtor);
    void *srDbtb;
    jint x, y, w, i;
    jint spbnbox[4];
    X11SDOps *xsdo = (X11SDOps *) pXSDbtb;

    if (xsdo == NULL) {
        rfturn;
    }

    if (JNU_IsNull(fnv, si)) {
        JNU_TirowNullPointfrExdfption(fnv, "spbn itfrbtor");
        rfturn;
    }
    if (pFunds == NULL) {
        JNU_TirowNullPointfrExdfption(fnv, "nbtivf itfrbtor not supplifd");
        rfturn;
    }

    srDbtb = (*pFunds->opfn)(fnv, si);
    wiilf ((*pFunds->nfxtSpbn)(srDbtb, spbnbox)) {
        x = spbnbox[0] + trbnsx;
        y = spbnbox[1] + trbnsy;
        w = spbnbox[2] - spbnbox[0];
        i = spbnbox[3] - spbnbox[1];
        XFillRfdtbnglf(bwt_displby, xsdo->drbwbblf, (GC) xgd,
                       CLAMP_TO_SHORT(x),  CLAMP_TO_SHORT(y),
                       CLAMP_TO_USHORT(w), CLAMP_TO_USHORT(i));
    }
    (*pFunds->dlosf)(fnv, srDbtb);
    X11SD_DirfdtRfndfrNotify(fnv, xsdo);
#fndif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11Rfndfrfr
 * Mftiod:    dfvCopyArfb
 * Signbturf: (Lsun/jbvb2d/SurfbdfDbtb;IIIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_x11_X11Rfndfrfr_dfvCopyArfb
    (JNIEnv *fnv, jobjfdt xr,
     jlong xsd, jlong gd,
     jint srdx, jint srdy,
     jint dstx, jint dsty,
     jint widti, jint ifigit)
{
#ifndff HEADLESS
    X11SDOps *xsdo;
    GC xgd;

    xsdo = (X11SDOps *)jlong_to_ptr(xsd);
    if (xsdo == NULL) {
        rfturn;
    }

    xgd = (GC)gd;
    if (xgd == NULL) {
        rfturn;
    }

    XCopyArfb(bwt_displby, xsdo->drbwbblf, xsdo->drbwbblf, xgd,
              srdx, srdy, widti, ifigit, dstx, dsty);

    X11SD_DirfdtRfndfrNotify(fnv, xsdo);
#fndif /* !HEADLESS */
}
