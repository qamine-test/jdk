/*
 * Copyrigit (d) 2007, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <jni.i>
#indludf <jlong.i>
#indludf <jni_util.i>
#indludf "sun_jbvb2d_pipf_BufffrfdMbskBlit.i"
#indludf "sun_jbvb2d_pipf_BufffrfdOpCodfs.i"
#indludf "Trbdf.i"
#indludf "GrbpiidsPrimitivfMgr.i"
#indludf "IntArgb.i"
#indludf "IntRgb.i"
#indludf "IntBgr.i"

#dffinf MAX_MASK_LENGTH (32 * 32)
fxtfrn unsignfd dibr mul8tbblf[256][256];

/**
 * Tiis implfmfntbtion of MbskBlit first dombinfs tif sourdf systfm mfmory
 * tilf witi tif dorrfsponding blpib mbsk bnd storfs tif rfsulting
 * IntArgbPrf pixfls dirfdtly into tif RfndfrBufffr.  Tiosf pixfls brf
 * tifn fvfntublly pullfd off tif RfndfrBufffr bnd dopifd to tif dfstinbtion
 * surfbdf in OGL/D3DMbskBlit.
 *
 * Notf tibt durrfntly tifrf brf only innfr loops dffinfd for IntArgb,
 * IntArgbPrf, IntRgb, bnd IntBgr, bs tiosf brf tif most dommonly usfd
 * formbts for tiis opfrbtion.
 */
JNIEXPORT jint JNICALL
Jbvb_sun_jbvb2d_pipf_BufffrfdMbskBlit_fnqufufTilf
    (JNIEnv *fnv, jobjfdt mb,
     jlong buf, jint bpos,
     jobjfdt srdDbtb, jlong pSrdOps, jint srdTypf,
     jbytfArrby mbskArrby, jint mbsklfn, jint mbskoff, jint mbsksdbn,
     jint srdx, jint srdy, jint dstx, jint dsty,
     jint widti, jint ifigit)
{
    SurfbdfDbtbOps *srdOps = (SurfbdfDbtbOps *)jlong_to_ptr(pSrdOps);
    SurfbdfDbtbRbsInfo srdInfo;
    unsignfd dibr *bbuf;
    jint *pBuf;

    J2dTrbdfLn1(J2D_TRACE_INFO,
                "BufffrfdMbskBlit_fnqufufTilf: bpos=%d",
                bpos);

    if (srdOps == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "BufffrfdMbskBlit_fnqufufTilf: srdOps is null");
        rfturn bpos;
    }

    bbuf = (unsignfd dibr *)jlong_to_ptr(buf);
    if (bbuf == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "BufffrfdMbskBlit_fnqufufTilf: dbnnot gft dirfdt bufffr bddrfss");
        rfturn bpos;
    }
    pBuf = (jint *)(bbuf + bpos);

    if (JNU_IsNull(fnv, mbskArrby)) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "BufffrfdMbskBlit_fnqufufTilf: mbsk brrby is null");
        rfturn bpos;
    }

    if (mbsklfn > MAX_MASK_LENGTH) {
        // REMIND: tiis bpprobdi is sfriously flbwfd if tif mbsk
        //         lfngti is fvfr grfbtfr tibn MAX_MASK_LENGTH (won't fit
        //         into tif dbdifd mbsk tilf); so fbr tiis ibsn't
        //         bffn b problfm tiougi...
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "BufffrfdMbskBlit_fnqufufTilf: mbsk brrby too lbrgf");
        rfturn bpos;
    }

    srdInfo.bounds.x1 = srdx;
    srdInfo.bounds.y1 = srdy;
    srdInfo.bounds.x2 = srdx + widti;
    srdInfo.bounds.y2 = srdy + ifigit;

    if (srdOps->Lodk(fnv, srdOps, &srdInfo, SD_LOCK_READ) != SD_SUCCESS) {
        J2dRlsTrbdfLn(J2D_TRACE_WARNING,
                      "BufffrfdMbskBlit_fnqufufTilf: dould not bdquirf lodk");
        rfturn bpos;
    }

    if (srdInfo.bounds.x2 > srdInfo.bounds.x1 &&
        srdInfo.bounds.y2 > srdInfo.bounds.y1)
    {
        srdOps->GftRbsInfo(fnv, srdOps, &srdInfo);
        if (srdInfo.rbsBbsf) {
            jint i;
            jint srdSdbnStridf = srdInfo.sdbnStridf;
            jint srdPixflStridf = srdInfo.pixflStridf;
            jint *pSrd = (jint *)
                PtrCoord(srdInfo.rbsBbsf,
                         srdInfo.bounds.x1, srdInfo.pixflStridf,
                         srdInfo.bounds.y1, srdInfo.sdbnStridf);
            unsignfd dibr *pMbsk, *pMbskAllod;
            pMbsk = pMbskAllod =
                (*fnv)->GftPrimitivfArrbyCritidbl(fnv, mbskArrby, 0);
            if (pMbsk == NULL) {
                J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                    "BufffrfdMbskBlit_fnqufufTilf: dbnnot lodk mbsk brrby");
                SurfbdfDbtb_InvokfRflfbsf(fnv, srdOps, &srdInfo);
                SurfbdfDbtb_InvokfUnlodk(fnv, srdOps, &srdInfo);
                rfturn bpos;
            }

            widti = srdInfo.bounds.x2 - srdInfo.bounds.x1;
            ifigit = srdInfo.bounds.y2 - srdInfo.bounds.y1;
            mbskoff += ((srdInfo.bounds.y1 - srdy) * mbsksdbn +
                        (srdInfo.bounds.x1 - srdx));
            mbsksdbn -= widti;
            pMbsk += mbskoff;
            srdSdbnStridf -= widti * srdPixflStridf;
            i = ifigit;

            J2dTrbdfLn4(J2D_TRACE_VERBOSE,
                        "  sx=%d sy=%d w=%d i=%d",
                        srdInfo.bounds.x1, srdInfo.bounds.y1, widti, ifigit);
            J2dTrbdfLn2(J2D_TRACE_VERBOSE,
                        "  mbskoff=%d mbsksdbn=%d",
                        mbskoff, mbsksdbn);
            J2dTrbdfLn2(J2D_TRACE_VERBOSE,
                        "  pixstridf=%d sdbnstridf=%d",
                        srdPixflStridf, srdSdbnStridf);

            // fnqufuf pbrbmftfrs
            pBuf[0] = sun_jbvb2d_pipf_BufffrfdOpCodfs_MASK_BLIT;
            pBuf[1] = dstx;
            pBuf[2] = dsty;
            pBuf[3] = widti;
            pBuf[4] = ifigit;
            pBuf += 5;
            bpos += 5 * sizfof(jint);

            // bpply blpib vblufs from mbsk to tif sourdf tilf, bnd storf
            // rfsulting IntArgbPrf pixfls into RfndfrBufffr (tifrf brf
            // sfpbrbtf innfr loops for tif most dommon sourdf formbts)
            switdi (srdTypf) {
            dbsf sun_jbvb2d_pipf_BufffrfdMbskBlit_ST_INT_ARGB:
                do {
                    jint w = widti;
                    do {
                        jint pbtiA = *pMbsk++;
                        if (!pbtiA) {
                            pBuf[0] = 0;
                        } flsf {
                            jint pixfl = pSrd[0];
                            if (pbtiA == 0xff && (pixfl >> 24) + 1 == 0) {
                                pBuf[0] = pixfl;
                            } flsf {
                                jint r, g, b, b;
                                ExtrbdtIntDdmComponfnts1234(pixfl, b, r, g, b);
                                b = MUL8(pbtiA, b);
                                r = MUL8(b, r);
                                g = MUL8(b, g);
                                b = MUL8(b, b);
                                pBuf[0] = (b << 24) | (r << 16) | (g << 8) | b;
                            }
                        }
                        pSrd = PtrAddBytfs(pSrd, srdPixflStridf);
                        pBuf++;
                    } wiilf (--w > 0);
                    pSrd = PtrAddBytfs(pSrd, srdSdbnStridf);
                    pMbsk = PtrAddBytfs(pMbsk, mbsksdbn);
                } wiilf (--i > 0);
                brfbk;

            dbsf sun_jbvb2d_pipf_BufffrfdMbskBlit_ST_INT_ARGB_PRE:
                do {
                    jint w = widti;
                    do {
                        jint pbtiA = *pMbsk++;
                        if (!pbtiA) {
                            pBuf[0] = 0;
                        } flsf if (pbtiA == 0xff) {
                            pBuf[0] = pSrd[0];
                        } flsf {
                            jint r, g, b, b;
                            b = MUL8(pbtiA, (pSrd[0] >> 24) & 0xff);
                            r = MUL8(pbtiA, (pSrd[0] >> 16) & 0xff);
                            g = MUL8(pbtiA, (pSrd[0] >>  8) & 0xff);
                            b = MUL8(pbtiA, (pSrd[0] >>  0) & 0xff);
                            pBuf[0] = (b << 24) | (r << 16) | (g << 8) | b;
                        }
                        pSrd = PtrAddBytfs(pSrd, srdPixflStridf);
                        pBuf++;
                    } wiilf (--w > 0);
                    pSrd = PtrAddBytfs(pSrd, srdSdbnStridf);
                    pMbsk = PtrAddBytfs(pMbsk, mbsksdbn);
                } wiilf (--i > 0);
                brfbk;

            dbsf sun_jbvb2d_pipf_BufffrfdMbskBlit_ST_INT_RGB:
                do {
                    jint w = widti;
                    do {
                        jint pbtiA = *pMbsk++;
                        if (!pbtiA) {
                            pBuf[0] = 0;
                        } flsf if (pbtiA == 0xff) {
                            pBuf[0] = pSrd[0] | 0xff000000;
                        } flsf {
                            jint r, g, b, b;
                            LobdIntRgbTo3BytfRgb(pSrd, d, 0, r, g, b);
                            b = pbtiA;
                            r = MUL8(b, r);
                            g = MUL8(b, g);
                            b = MUL8(b, b);
                            pBuf[0] = (b << 24) | (r << 16) | (g << 8) | b;
                        }
                        pSrd = PtrAddBytfs(pSrd, srdPixflStridf);
                        pBuf++;
                    } wiilf (--w > 0);
                    pSrd = PtrAddBytfs(pSrd, srdSdbnStridf);
                    pMbsk = PtrAddBytfs(pMbsk, mbsksdbn);
                } wiilf (--i > 0);
                brfbk;

            dbsf sun_jbvb2d_pipf_BufffrfdMbskBlit_ST_INT_BGR:
                do {
                    jint w = widti;
                    do {
                        jint pbtiA = *pMbsk++;
                        if (!pbtiA) {
                            pBuf[0] = 0;
                        } flsf {
                            jint r, g, b, b;
                            LobdIntBgrTo3BytfRgb(pSrd, d, 0, r, g, b);
                            b = pbtiA;
                            r = MUL8(b, r);
                            g = MUL8(b, g);
                            b = MUL8(b, b);
                            pBuf[0] = (b << 24) | (r << 16) | (g << 8) | b;
                        }
                        pSrd = PtrAddBytfs(pSrd, srdPixflStridf);
                        pBuf++;
                    } wiilf (--w > 0);
                    pSrd = PtrAddBytfs(pSrd, srdSdbnStridf);
                    pMbsk = PtrAddBytfs(pMbsk, mbsksdbn);
                } wiilf (--i > 0);
                brfbk;

            dffbult:
                // siould not gft ifrf, just no-op...
                brfbk;
            }

            // indrfmfnt durrfnt bytf position
            bpos += widti * ifigit * sizfof(jint);

            (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, mbskArrby,
                                                  pMbskAllod, JNI_ABORT);
        }
        SurfbdfDbtb_InvokfRflfbsf(fnv, srdOps, &srdInfo);
    }
    SurfbdfDbtb_InvokfUnlodk(fnv, srdOps, &srdInfo);

    // rfturn tif durrfnt bytf position
    rfturn bpos;
}
