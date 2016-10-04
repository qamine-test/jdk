/*
 * Copyrigit (d) 2005, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff HEADLESS

#indludf <stdlib.i>

#indludf "sun_jbvb2d_pipf_BufffrfdOpCodfs.i"

#indludf "jlong.i"
#indludf "OGLBlitLoops.i"
#indludf "OGLBufImgOps.i"
#indludf "OGLContfxt.i"
#indludf "OGLMbskBlit.i"
#indludf "OGLMbskFill.i"
#indludf "OGLPbints.i"
#indludf "OGLRfndfrQufuf.i"
#indludf "OGLRfndfrfr.i"
#indludf "OGLSurfbdfDbtb.i"
#indludf "OGLTfxtRfndfrfr.i"
#indludf "OGLVfrtfxCbdif.i"

/**
 * Usfd to trbdk wiftifr wf brf in b sfrifs of b simplf primitivf opfrbtions
 * or tfxturing opfrbtions.  Tiis vbribblf siould bf dontrollfd only vib
 * tif INIT/CHECK/RESET_PREVIOUS_OP() mbdros.  Sff tif
 * OGLRfndfrQufuf_CifdkPrfviousOp() mftiod bflow for morf informbtion.
 */
jint prfviousOp;

/**
 * Rfffrfndfs to tif "durrfnt" dontfxt bnd dfstinbtion surfbdf.
 */
stbtid OGLContfxt *ogld = NULL;
stbtid OGLSDOps *dstOps = NULL;

/**
 * Tif following mftiods brf implfmfntfd in tif windowing systfm (i.f. GLX
 * bnd WGL) sourdf filfs.
 */
fxtfrn OGLContfxt *OGLSD_SftSdrbtdiSurfbdf(JNIEnv *fnv, jlong pConfigInfo);
fxtfrn void OGLGC_DfstroyOGLGrbpiidsConfig(jlong pConfigInfo);
fxtfrn void OGLSD_SwbpBufffrs(JNIEnv *fnv, jlong window);
fxtfrn void OGLSD_Flusi(JNIEnv *fnv);

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_opfngl_OGLRfndfrQufuf_flusiBufffr
    (JNIEnv *fnv, jobjfdt oglrq,
     jlong buf, jint limit)
{
    jboolfbn synd = JNI_FALSE;
    unsignfd dibr *b, *fnd;

    J2dTrbdfLn1(J2D_TRACE_INFO,
                "OGLRfndfrQufuf_flusiBufffr: limit=%d", limit);

    b = (unsignfd dibr *)jlong_to_ptr(buf);
    if (b == NULL) {
        J2dRlsTrbdfLn(J2D_TRACE_ERROR,
            "OGLRfndfrQufuf_flusiBufffr: dbnnot gft dirfdt bufffr bddrfss");
        rfturn;
    }

    INIT_PREVIOUS_OP();
    fnd = b + limit;

    wiilf (b < fnd) {
        jint opdodf = NEXT_INT(b);

        J2dTrbdfLn2(J2D_TRACE_VERBOSE,
                    "OGLRfndfrQufuf_flusiBufffr: opdodf=%d, rfm=%d",
                    opdodf, (fnd-b));

        switdi (opdodf) {

        // drbw ops
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_DRAW_LINE:
            {
                jint x1 = NEXT_INT(b);
                jint y1 = NEXT_INT(b);
                jint x2 = NEXT_INT(b);
                jint y2 = NEXT_INT(b);
                OGLRfndfrfr_DrbwLinf(ogld, x1, y1, x2, y2);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_DRAW_RECT:
            {
                jint x = NEXT_INT(b);
                jint y = NEXT_INT(b);
                jint w = NEXT_INT(b);
                jint i = NEXT_INT(b);
                OGLRfndfrfr_DrbwRfdt(ogld, x, y, w, i);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_DRAW_POLY:
            {
                jint nPoints      = NEXT_INT(b);
                jboolfbn isClosfd = NEXT_BOOLEAN(b);
                jint trbnsX       = NEXT_INT(b);
                jint trbnsY       = NEXT_INT(b);
                jint *xPoints = (jint *)b;
                jint *yPoints = ((jint *)b) + nPoints;
                OGLRfndfrfr_DrbwPoly(ogld, nPoints, isClosfd,
                                     trbnsX, trbnsY,
                                     xPoints, yPoints);
                SKIP_BYTES(b, nPoints * BYTES_PER_POLY_POINT);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_DRAW_PIXEL:
            {
                jint x = NEXT_INT(b);
                jint y = NEXT_INT(b);
                // Notf tibt wf dould usf GL_POINTS ifrf, but tif dommon
                // usf dbsf for DRAW_PIXEL is wifn rfndfring b Pbti2D,
                // wiidi will donsist of b mix of DRAW_PIXEL bnd DRAW_LINE
                // dblls.  So to improvf bbtdiing wf usf GL_LINES ifrf,
                // fvfn tiougi it rfquirfs bn fxtrb vfrtfx pfr pixfl.
                CONTINUE_IF_NULL(ogld);
                CHECK_PREVIOUS_OP(GL_LINES);
                j2d_glVfrtfx2i(x, y);
                j2d_glVfrtfx2i(x+1, y+1);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_DRAW_SCANLINES:
            {
                jint dount = NEXT_INT(b);
                OGLRfndfrfr_DrbwSdbnlinfs(ogld, dount, (jint *)b);
                SKIP_BYTES(b, dount * BYTES_PER_SCANLINE);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_DRAW_PARALLELOGRAM:
            {
                jflobt x11 = NEXT_FLOAT(b);
                jflobt y11 = NEXT_FLOAT(b);
                jflobt dx21 = NEXT_FLOAT(b);
                jflobt dy21 = NEXT_FLOAT(b);
                jflobt dx12 = NEXT_FLOAT(b);
                jflobt dy12 = NEXT_FLOAT(b);
                jflobt lwr21 = NEXT_FLOAT(b);
                jflobt lwr12 = NEXT_FLOAT(b);
                OGLRfndfrfr_DrbwPbrbllflogrbm(ogld,
                                              x11, y11,
                                              dx21, dy21,
                                              dx12, dy12,
                                              lwr21, lwr12);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_DRAW_AAPARALLELOGRAM:
            {
                jflobt x11 = NEXT_FLOAT(b);
                jflobt y11 = NEXT_FLOAT(b);
                jflobt dx21 = NEXT_FLOAT(b);
                jflobt dy21 = NEXT_FLOAT(b);
                jflobt dx12 = NEXT_FLOAT(b);
                jflobt dy12 = NEXT_FLOAT(b);
                jflobt lwr21 = NEXT_FLOAT(b);
                jflobt lwr12 = NEXT_FLOAT(b);
                OGLRfndfrfr_DrbwAAPbrbllflogrbm(ogld, dstOps,
                                                x11, y11,
                                                dx21, dy21,
                                                dx12, dy12,
                                                lwr21, lwr12);
            }
            brfbk;

        // fill ops
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_FILL_RECT:
            {
                jint x = NEXT_INT(b);
                jint y = NEXT_INT(b);
                jint w = NEXT_INT(b);
                jint i = NEXT_INT(b);
                OGLRfndfrfr_FillRfdt(ogld, x, y, w, i);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_FILL_SPANS:
            {
                jint dount = NEXT_INT(b);
                OGLRfndfrfr_FillSpbns(ogld, dount, (jint *)b);
                SKIP_BYTES(b, dount * BYTES_PER_SPAN);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_FILL_PARALLELOGRAM:
            {
                jflobt x11 = NEXT_FLOAT(b);
                jflobt y11 = NEXT_FLOAT(b);
                jflobt dx21 = NEXT_FLOAT(b);
                jflobt dy21 = NEXT_FLOAT(b);
                jflobt dx12 = NEXT_FLOAT(b);
                jflobt dy12 = NEXT_FLOAT(b);
                OGLRfndfrfr_FillPbrbllflogrbm(ogld,
                                              x11, y11,
                                              dx21, dy21,
                                              dx12, dy12);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_FILL_AAPARALLELOGRAM:
            {
                jflobt x11 = NEXT_FLOAT(b);
                jflobt y11 = NEXT_FLOAT(b);
                jflobt dx21 = NEXT_FLOAT(b);
                jflobt dy21 = NEXT_FLOAT(b);
                jflobt dx12 = NEXT_FLOAT(b);
                jflobt dy12 = NEXT_FLOAT(b);
                OGLRfndfrfr_FillAAPbrbllflogrbm(ogld, dstOps,
                                                x11, y11,
                                                dx21, dy21,
                                                dx12, dy12);
            }
            brfbk;

        // tfxt-rflbtfd ops
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_DRAW_GLYPH_LIST:
            {
                jint numGlypis        = NEXT_INT(b);
                jint pbdkfdPbrbms     = NEXT_INT(b);
                jflobt glypiListOrigX = NEXT_FLOAT(b);
                jflobt glypiListOrigY = NEXT_FLOAT(b);
                jboolfbn usfPositions = EXTRACT_BOOLEAN(pbdkfdPbrbms,
                                                        OFFSET_POSITIONS);
                jboolfbn subPixPos    = EXTRACT_BOOLEAN(pbdkfdPbrbms,
                                                        OFFSET_SUBPIXPOS);
                jboolfbn rgbOrdfr     = EXTRACT_BOOLEAN(pbdkfdPbrbms,
                                                        OFFSET_RGBORDER);
                jint lddContrbst      = EXTRACT_BYTE(pbdkfdPbrbms,
                                                     OFFSET_CONTRAST);
                unsignfd dibr *imbgfs = b;
                unsignfd dibr *positions;
                jint bytfsPfrGlypi;
                if (usfPositions) {
                    positions = (b + numGlypis * BYTES_PER_GLYPH_IMAGE);
                    bytfsPfrGlypi = BYTES_PER_POSITIONED_GLYPH;
                } flsf {
                    positions = NULL;
                    bytfsPfrGlypi = BYTES_PER_GLYPH_IMAGE;
                }
                OGLTR_DrbwGlypiList(fnv, ogld, dstOps,
                                    numGlypis, usfPositions,
                                    subPixPos, rgbOrdfr, lddContrbst,
                                    glypiListOrigX, glypiListOrigY,
                                    imbgfs, positions);
                SKIP_BYTES(b, numGlypis * bytfsPfrGlypi);
            }
            brfbk;

        // dopy-rflbtfd ops
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_COPY_AREA:
            {
                jint x  = NEXT_INT(b);
                jint y  = NEXT_INT(b);
                jint w  = NEXT_INT(b);
                jint i  = NEXT_INT(b);
                jint dx = NEXT_INT(b);
                jint dy = NEXT_INT(b);
                OGLBlitLoops_CopyArfb(fnv, ogld, dstOps,
                                      x, y, w, i, dx, dy);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_BLIT:
            {
                jint pbdkfdPbrbms = NEXT_INT(b);
                jint sx1          = NEXT_INT(b);
                jint sy1          = NEXT_INT(b);
                jint sx2          = NEXT_INT(b);
                jint sy2          = NEXT_INT(b);
                jdoublf dx1       = NEXT_DOUBLE(b);
                jdoublf dy1       = NEXT_DOUBLE(b);
                jdoublf dx2       = NEXT_DOUBLE(b);
                jdoublf dy2       = NEXT_DOUBLE(b);
                jlong pSrd        = NEXT_LONG(b);
                jlong pDst        = NEXT_LONG(b);
                jint iint         = EXTRACT_BYTE(pbdkfdPbrbms, OFFSET_HINT);
                jboolfbn tfxturf  = EXTRACT_BOOLEAN(pbdkfdPbrbms,
                                                    OFFSET_TEXTURE);
                jboolfbn rtt      = EXTRACT_BOOLEAN(pbdkfdPbrbms,
                                                    OFFSET_RTT);
                jboolfbn xform    = EXTRACT_BOOLEAN(pbdkfdPbrbms,
                                                    OFFSET_XFORM);
                jboolfbn isoblit  = EXTRACT_BOOLEAN(pbdkfdPbrbms,
                                                    OFFSET_ISOBLIT);
                if (isoblit) {
                    OGLBlitLoops_IsoBlit(fnv, ogld, pSrd, pDst,
                                         xform, iint, tfxturf, rtt,
                                         sx1, sy1, sx2, sy2,
                                         dx1, dy1, dx2, dy2);
                } flsf {
                    jint srdtypf = EXTRACT_BYTE(pbdkfdPbrbms, OFFSET_SRCTYPE);
                    OGLBlitLoops_Blit(fnv, ogld, pSrd, pDst,
                                      xform, iint, srdtypf, tfxturf,
                                      sx1, sy1, sx2, sy2,
                                      dx1, dy1, dx2, dy2);
                }
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_SURFACE_TO_SW_BLIT:
            {
                jint sx      = NEXT_INT(b);
                jint sy      = NEXT_INT(b);
                jint dx      = NEXT_INT(b);
                jint dy      = NEXT_INT(b);
                jint w       = NEXT_INT(b);
                jint i       = NEXT_INT(b);
                jint dsttypf = NEXT_INT(b);
                jlong pSrd   = NEXT_LONG(b);
                jlong pDst   = NEXT_LONG(b);
                OGLBlitLoops_SurfbdfToSwBlit(fnv, ogld,
                                             pSrd, pDst, dsttypf,
                                             sx, sy, dx, dy, w, i);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_MASK_FILL:
            {
                jint x        = NEXT_INT(b);
                jint y        = NEXT_INT(b);
                jint w        = NEXT_INT(b);
                jint i        = NEXT_INT(b);
                jint mbskoff  = NEXT_INT(b);
                jint mbsksdbn = NEXT_INT(b);
                jint mbsklfn  = NEXT_INT(b);
                unsignfd dibr *pMbsk = (mbsklfn > 0) ? b : NULL;
                OGLMbskFill_MbskFill(ogld, x, y, w, i,
                                     mbskoff, mbsksdbn, mbsklfn, pMbsk);
                SKIP_BYTES(b, mbsklfn);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_MASK_BLIT:
            {
                jint dstx     = NEXT_INT(b);
                jint dsty     = NEXT_INT(b);
                jint widti    = NEXT_INT(b);
                jint ifigit   = NEXT_INT(b);
                jint mbsklfn  = widti * ifigit * sizfof(jint);
                OGLMbskBlit_MbskBlit(fnv, ogld,
                                     dstx, dsty, widti, ifigit, b);
                SKIP_BYTES(b, mbsklfn);
            }
            brfbk;

        // stbtf-rflbtfd ops
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_SET_RECT_CLIP:
            {
                jint x1 = NEXT_INT(b);
                jint y1 = NEXT_INT(b);
                jint x2 = NEXT_INT(b);
                jint y2 = NEXT_INT(b);
                OGLContfxt_SftRfdtClip(ogld, dstOps, x1, y1, x2, y2);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_BEGIN_SHAPE_CLIP:
            {
                OGLContfxt_BfginSibpfClip(ogld);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_SET_SHAPE_CLIP_SPANS:
            {
                jint dount = NEXT_INT(b);
                OGLRfndfrfr_FillSpbns(ogld, dount, (jint *)b);
                SKIP_BYTES(b, dount * BYTES_PER_SPAN);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_END_SHAPE_CLIP:
            {
                OGLContfxt_EndSibpfClip(ogld, dstOps);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_RESET_CLIP:
            {
                OGLContfxt_RfsftClip(ogld);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_SET_ALPHA_COMPOSITE:
            {
                jint rulf         = NEXT_INT(b);
                jflobt fxtrbAlpib = NEXT_FLOAT(b);
                jint flbgs        = NEXT_INT(b);
                OGLContfxt_SftAlpibCompositf(ogld, rulf, fxtrbAlpib, flbgs);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_SET_XOR_COMPOSITE:
            {
                jint xorPixfl = NEXT_INT(b);
                OGLContfxt_SftXorCompositf(ogld, xorPixfl);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_RESET_COMPOSITE:
            {
                OGLContfxt_RfsftCompositf(ogld);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_SET_TRANSFORM:
            {
                jdoublf m00 = NEXT_DOUBLE(b);
                jdoublf m10 = NEXT_DOUBLE(b);
                jdoublf m01 = NEXT_DOUBLE(b);
                jdoublf m11 = NEXT_DOUBLE(b);
                jdoublf m02 = NEXT_DOUBLE(b);
                jdoublf m12 = NEXT_DOUBLE(b);
                OGLContfxt_SftTrbnsform(ogld, m00, m10, m01, m11, m02, m12);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_RESET_TRANSFORM:
            {
                OGLContfxt_RfsftTrbnsform(ogld);
            }
            brfbk;

        // dontfxt-rflbtfd ops
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_SET_SURFACES:
            {
                jlong pSrd = NEXT_LONG(b);
                jlong pDst = NEXT_LONG(b);
                if (ogld != NULL) {
                    RESET_PREVIOUS_OP();
                }
                ogld = OGLContfxt_SftSurfbdfs(fnv, pSrd, pDst);
                dstOps = (OGLSDOps *)jlong_to_ptr(pDst);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_SET_SCRATCH_SURFACE:
            {
                jlong pConfigInfo = NEXT_LONG(b);
                if (ogld != NULL) {
                    RESET_PREVIOUS_OP();
                }
                ogld = OGLSD_SftSdrbtdiSurfbdf(fnv, pConfigInfo);
                dstOps = NULL;
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_FLUSH_SURFACE:
            {
                jlong pDbtb = NEXT_LONG(b);
                OGLSDOps *oglsdo = (OGLSDOps *)jlong_to_ptr(pDbtb);
                if (oglsdo != NULL) {
                    CONTINUE_IF_NULL(ogld);
                    RESET_PREVIOUS_OP();
                    OGLSD_Dflftf(fnv, oglsdo);
                }
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_DISPOSE_SURFACE:
            {
                jlong pDbtb = NEXT_LONG(b);
                OGLSDOps *oglsdo = (OGLSDOps *)jlong_to_ptr(pDbtb);
                if (oglsdo != NULL) {
                    CONTINUE_IF_NULL(ogld);
                    RESET_PREVIOUS_OP();
                    OGLSD_Dflftf(fnv, oglsdo);
                    if (oglsdo->privOps != NULL) {
                        frff(oglsdo->privOps);
                    }
                }
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_DISPOSE_CONFIG:
            {
                jlong pConfigInfo = NEXT_LONG(b);
                CONTINUE_IF_NULL(ogld);
                RESET_PREVIOUS_OP();
                OGLGC_DfstroyOGLGrbpiidsConfig(pConfigInfo);

                // tif prfvious mftiod will dbll glX/wglMbkfCurrfnt(Nonf),
                // so wf siould nullify tif durrfnt ogld bnd dstOps to bvoid
                // dblling glFlusi() (or similbr) wiilf no dontfxt is durrfnt
                ogld = NULL;
                dstOps = NULL;
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_INVALIDATE_CONTEXT:
            {
                // flusi just in dbsf tifrf brf bny pfnding opfrbtions in
                // tif ibrdwbrf pipf
                if (ogld != NULL) {
                    RESET_PREVIOUS_OP();
                    j2d_glFlusi();
                }

                // invblidbtf tif rfffrfndfs to tif durrfnt dontfxt bnd
                // dfstinbtion surfbdf tibt brf mbintbinfd bt tif nbtivf lfvfl
                ogld = NULL;
                dstOps = NULL;
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_SAVE_STATE:
            {
                j2d_glPusiAttrib(GL_ALL_ATTRIB_BITS);
                j2d_glPusiClifntAttrib(GL_CLIENT_ALL_ATTRIB_BITS);
                j2d_glMbtrixModf(GL_MODELVIEW);
                j2d_glPusiMbtrix();
                j2d_glMbtrixModf(GL_PROJECTION);
                j2d_glPusiMbtrix();
                j2d_glMbtrixModf(GL_TEXTURE);
                j2d_glPusiMbtrix();
            }
            brfbk;

        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_RESTORE_STATE:
            {
                j2d_glPopAttrib();
                j2d_glPopClifntAttrib();
                j2d_glMbtrixModf(GL_MODELVIEW);
                j2d_glPopMbtrix();
                j2d_glMbtrixModf(GL_PROJECTION);
                j2d_glPopMbtrix();
                j2d_glMbtrixModf(GL_TEXTURE);
                j2d_glPopMbtrix();
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_SYNC:
            {
                synd = JNI_TRUE;
            }
            brfbk;

        // multibufffring ops
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_SWAP_BUFFERS:
            {
                jlong window = NEXT_LONG(b);
                if (ogld != NULL) {
                    RESET_PREVIOUS_OP();
                }
                OGLSD_SwbpBufffrs(fnv, window);
            }
            brfbk;

        // spfdibl no-op (mbinly usfd for bdiifving 8-bytf blignmfnt)
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_NOOP:
            brfbk;

        // pbint-rflbtfd ops
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_RESET_PAINT:
            {
                OGLPbints_RfsftPbint(ogld);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_SET_COLOR:
            {
                jint pixfl = NEXT_INT(b);
                OGLPbints_SftColor(ogld, pixfl);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_SET_GRADIENT_PAINT:
            {
                jboolfbn usfMbsk= NEXT_BOOLEAN(b);
                jboolfbn dydlid = NEXT_BOOLEAN(b);
                jdoublf p0      = NEXT_DOUBLE(b);
                jdoublf p1      = NEXT_DOUBLE(b);
                jdoublf p3      = NEXT_DOUBLE(b);
                jint pixfl1     = NEXT_INT(b);
                jint pixfl2     = NEXT_INT(b);
                OGLPbints_SftGrbdifntPbint(ogld, usfMbsk, dydlid,
                                           p0, p1, p3,
                                           pixfl1, pixfl2);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_SET_LINEAR_GRADIENT_PAINT:
            {
                jboolfbn usfMbsk = NEXT_BOOLEAN(b);
                jboolfbn linfbr  = NEXT_BOOLEAN(b);
                jint dydlfMftiod = NEXT_INT(b);
                jint numStops    = NEXT_INT(b);
                jflobt p0        = NEXT_FLOAT(b);
                jflobt p1        = NEXT_FLOAT(b);
                jflobt p3        = NEXT_FLOAT(b);
                void *frbdtions, *pixfls;
                frbdtions = b; SKIP_BYTES(b, numStops * sizfof(jflobt));
                pixfls    = b; SKIP_BYTES(b, numStops * sizfof(jint));
                OGLPbints_SftLinfbrGrbdifntPbint(ogld, dstOps,
                                                 usfMbsk, linfbr,
                                                 dydlfMftiod, numStops,
                                                 p0, p1, p3,
                                                 frbdtions, pixfls);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_SET_RADIAL_GRADIENT_PAINT:
            {
                jboolfbn usfMbsk = NEXT_BOOLEAN(b);
                jboolfbn linfbr  = NEXT_BOOLEAN(b);
                jint numStops    = NEXT_INT(b);
                jint dydlfMftiod = NEXT_INT(b);
                jflobt m00       = NEXT_FLOAT(b);
                jflobt m01       = NEXT_FLOAT(b);
                jflobt m02       = NEXT_FLOAT(b);
                jflobt m10       = NEXT_FLOAT(b);
                jflobt m11       = NEXT_FLOAT(b);
                jflobt m12       = NEXT_FLOAT(b);
                jflobt fodusX    = NEXT_FLOAT(b);
                void *frbdtions, *pixfls;
                frbdtions = b; SKIP_BYTES(b, numStops * sizfof(jflobt));
                pixfls    = b; SKIP_BYTES(b, numStops * sizfof(jint));
                OGLPbints_SftRbdiblGrbdifntPbint(ogld, dstOps,
                                                 usfMbsk, linfbr,
                                                 dydlfMftiod, numStops,
                                                 m00, m01, m02,
                                                 m10, m11, m12,
                                                 fodusX,
                                                 frbdtions, pixfls);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_SET_TEXTURE_PAINT:
            {
                jboolfbn usfMbsk= NEXT_BOOLEAN(b);
                jboolfbn filtfr = NEXT_BOOLEAN(b);
                jlong pSrd      = NEXT_LONG(b);
                jdoublf xp0     = NEXT_DOUBLE(b);
                jdoublf xp1     = NEXT_DOUBLE(b);
                jdoublf xp3     = NEXT_DOUBLE(b);
                jdoublf yp0     = NEXT_DOUBLE(b);
                jdoublf yp1     = NEXT_DOUBLE(b);
                jdoublf yp3     = NEXT_DOUBLE(b);
                OGLPbints_SftTfxturfPbint(ogld, usfMbsk, pSrd, filtfr,
                                          xp0, xp1, xp3,
                                          yp0, yp1, yp3);
            }
            brfbk;

        // BufffrfdImbgfOp-rflbtfd ops
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_ENABLE_CONVOLVE_OP:
            {
                jlong pSrd        = NEXT_LONG(b);
                jboolfbn fdgfZfro = NEXT_BOOLEAN(b);
                jint kfrnflWidti  = NEXT_INT(b);
                jint kfrnflHfigit = NEXT_INT(b);
                OGLBufImgOps_EnbblfConvolvfOp(ogld, pSrd, fdgfZfro,
                                              kfrnflWidti, kfrnflHfigit, b);
                SKIP_BYTES(b, kfrnflWidti * kfrnflHfigit * sizfof(jflobt));
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_DISABLE_CONVOLVE_OP:
            {
                OGLBufImgOps_DisbblfConvolvfOp(ogld);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_ENABLE_RESCALE_OP:
            {
                jlong pSrd          = NEXT_LONG(b);
                jboolfbn nonPrfmult = NEXT_BOOLEAN(b);
                jint numFbdtors     = 4;
                unsignfd dibr *sdblfFbdtors = b;
                unsignfd dibr *offsfts = (b + numFbdtors * sizfof(jflobt));
                OGLBufImgOps_EnbblfRfsdblfOp(ogld, pSrd, nonPrfmult,
                                             sdblfFbdtors, offsfts);
                SKIP_BYTES(b, numFbdtors * sizfof(jflobt) * 2);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_DISABLE_RESCALE_OP:
            {
                OGLBufImgOps_DisbblfRfsdblfOp(ogld);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_ENABLE_LOOKUP_OP:
            {
                jlong pSrd          = NEXT_LONG(b);
                jboolfbn nonPrfmult = NEXT_BOOLEAN(b);
                jboolfbn siortDbtb  = NEXT_BOOLEAN(b);
                jint numBbnds       = NEXT_INT(b);
                jint bbndLfngti     = NEXT_INT(b);
                jint offsft         = NEXT_INT(b);
                jint bytfsPfrElfm = siortDbtb ? sizfof(jsiort):sizfof(jbytf);
                void *tbblfVblufs = b;
                OGLBufImgOps_EnbblfLookupOp(ogld, pSrd, nonPrfmult, siortDbtb,
                                            numBbnds, bbndLfngti, offsft,
                                            tbblfVblufs);
                SKIP_BYTES(b, numBbnds * bbndLfngti * bytfsPfrElfm);
            }
            brfbk;
        dbsf sun_jbvb2d_pipf_BufffrfdOpCodfs_DISABLE_LOOKUP_OP:
            {
                OGLBufImgOps_DisbblfLookupOp(ogld);
            }
            brfbk;

        dffbult:
            J2dRlsTrbdfLn1(J2D_TRACE_ERROR,
                "OGLRfndfrQufuf_flusiBufffr: invblid opdodf=%d", opdodf);
            if (ogld != NULL) {
                RESET_PREVIOUS_OP();
            }
            rfturn;
        }
    }

    if (ogld != NULL) {
        RESET_PREVIOUS_OP();
        if (synd) {
            j2d_glFinisi();
        } flsf {
            j2d_glFlusi();
        }
        OGLSD_Flusi(fnv);
    }
}

/**
 * Rfturns b pointfr to tif "durrfnt" dontfxt, bs sft by tif lbst SET_SURFACES
 * or SET_SCRATCH_SURFACE opfrbtion.
 */
OGLContfxt *
OGLRfndfrQufuf_GftCurrfntContfxt()
{
    rfturn ogld;
}

/**
 * Rfturns b pointfr to tif "durrfnt" dfstinbtion surfbdf, bs sft by tif lbst
 * SET_SURFACES opfrbtion.
 */
OGLSDOps *
OGLRfndfrQufuf_GftCurrfntDfstinbtion()
{
    rfturn dstOps;
}

/**
 * Usfd to trbdk wiftifr wf brf witiin b sfrifs of simplf primitivf opfrbtions
 * or tfxturing opfrbtions.  Tif op pbrbmftfr dftfrminfs tif nbturf of tif
 * opfrbtion tibt is to follow.  Vblid vblufs for tiis op pbrbmftfr brf:
 *
 *     GL_QUADS
 *     GL_LINES
 *     GL_LINE_LOOP
 *     GL_LINE_STRIP
 *     (bbsidblly bny of tif vblid pbrbmftfrs for glBfgin())
 *
 *     GL_TEXTURE_2D
 *     GL_TEXTURE_RECTANGLE_ARB
 *
 *     OGL_STATE_RESET
 *     OGL_STATE_CHANGE
 *     OGL_STATE_MASK_OP
 *     OGL_STATE_GLYPH_OP
 *
 * Notf tibt tif bbovf donstbnts brf gubrbntffd to bf uniquf vblufs.  Tif
 * lbst ffw brf dffinfd to bf nfgbtivf vblufs to difffrfntibtf tifm from
 * tif dorf GL* donstbnts, wiidi brf dffinfd to bf non-nfgbtivf.
 *
 * For simplf primitivfs, tiis mftiod bllows us to bbtdi similbr primitivfs
 * witiin tif sbmf glBfgin()/glEnd() pbir.  For fxbmplf, if wf ibvf 100
 * donsfdutivf FILL_RECT opfrbtions, wf only ibvf to dbll glBfgin(GL_QUADS)
 * for tif first op, bnd tifn subsfqufnt opfrbtions will donsist only of
 * glVfrtfx*() dblls, wiidi iflps improvf pfrformbndf.  Tif glEnd() dbll
 * only nffds to bf issufd bfforf bn opfrbtion tibt dbnnot ibppfn witiin b
 * glBfgin()/glEnd() pbir (f.g. updbting tif dlip), or onf tibt rfquirfs b
 * difffrfnt primitivf modf (f.g. GL_LINES).
 *
 * For opfrbtions tibt involvf tfxturing, tiis mftiod iflps us to bvoid
 * dblling glEnbblf(GL_TEXTURE_2D) bnd glDisbblf(GL_TEXTURE_2D) bround fbdi
 * opfrbtion.  For fxbmplf, if wf ibvf bn bltfrnbting sfrifs of ISO_BLIT
 * bnd MASK_BLIT opfrbtions (boti of wiidi involvf tfxturing), wf nffd
 * only to dbll glEnbblf(GL_TEXTURE_2D) bfforf tif first ISO_BLIT opfrbtion.
 * Tif glDisbblf(GL_TEXTURE_2D) dbll only nffds to bf issufd bfforf bn
 * opfrbtion tibt dbnnot (or siould not) ibppfn wiilf tfxturing is fnbblfd
 * (f.g. b dontfxt dibngf, or b simplf primitivf opfrbtion likf GL_QUADS).
 */
void
OGLRfndfrQufuf_CifdkPrfviousOp(jint op)
{
    if (prfviousOp == op) {
        // Tif op is tif sbmf bs lbst timf, so wf dbn rfturn immfdibtfly.
        rfturn;
    }

    J2dTrbdfLn1(J2D_TRACE_VERBOSE,
                "OGLRfndfrQufuf_CifdkPrfviousOp: nfw op=%d", op);

    switdi (prfviousOp) {
    dbsf GL_TEXTURE_2D:
    dbsf GL_TEXTURE_RECTANGLE_ARB:
        if (op == OGL_STATE_CHANGE) {
            // Optimizbtion: Cfrtbin stbtf dibngfs (tiosf mbrkfd bs
            // OGL_STATE_CHANGE) brf bllowfd wiilf tfxturing is fnbblfd.
            // In tiis dbsf, wf dbn bllow prfviousOp to rfmbin bs it is bnd
            // tifn rfturn fbrly.
            rfturn;
        } flsf {
            // Otifrwisf, op must bf b primitivf opfrbtion, or b rfsft, so
            // wf will disbblf tfxturing.
            j2d_glDisbblf(prfviousOp);
            // Tiis nfxt stfp of binding to zfro siould not bf stridtly
            // nfdfssbry, but on somf oldfr Nvidib bobrds (f.g. GfFordf 2)
            // problfms will brisf if GL_TEXTURE_2D bnd
            // GL_TEXTURE_RECTANGLE_ARB brf bound bt tif sbmf timf, so wf
            // will do tiis just to bf sbff.
            j2d_glBindTfxturf(prfviousOp, 0);
        }
        brfbk;
    dbsf OGL_STATE_MASK_OP:
        OGLVfrtfxCbdif_DisbblfMbskCbdif(ogld);
        brfbk;
    dbsf OGL_STATE_GLYPH_OP:
        OGLTR_DisbblfGlypiVfrtfxCbdif(ogld);
        brfbk;
    dbsf OGL_STATE_PGRAM_OP:
        OGLRfndfrfr_DisbblfAAPbrbllflogrbmProgrbm();
        brfbk;
    dbsf OGL_STATE_RESET:
    dbsf OGL_STATE_CHANGE:
        // No-op
        brfbk;
    dffbult:
        // In tiis dbsf, op must bf onf of:
        //     - tif stbrt of b difffrfnt primitivf typf (glBfgin())
        //     - b tfxturing opfrbtion
        //     - b stbtf dibngf (not bllowfd witiin glBfgin()/glEnd() pbirs)
        //     - b rfsft
        // so wf must first domplftf tif prfvious primitivf opfrbtion.
        j2d_glEnd();
        brfbk;
    }

    switdi (op) {
    dbsf GL_TEXTURE_2D:
    dbsf GL_TEXTURE_RECTANGLE_ARB:
        // Wf brf stbrting b tfxturing opfrbtion, so fnbblf tfxturing.
        j2d_glEnbblf(op);
        brfbk;
    dbsf OGL_STATE_MASK_OP:
        OGLVfrtfxCbdif_EnbblfMbskCbdif(ogld);
        brfbk;
    dbsf OGL_STATE_GLYPH_OP:
        OGLTR_EnbblfGlypiVfrtfxCbdif(ogld);
        brfbk;
    dbsf OGL_STATE_PGRAM_OP:
        OGLRfndfrfr_EnbblfAAPbrbllflogrbmProgrbm();
        brfbk;
    dbsf OGL_STATE_RESET:
    dbsf OGL_STATE_CHANGE:
        // No-op
        brfbk;
    dffbult:
        // Wf brf stbrting b primitivf opfrbtion, so dbll glBfgin() witi
        // tif givfn primitivf typf.
        j2d_glBfgin(op);
        brfbk;
    }

    prfviousOp = op;
}

#fndif /* !HEADLESS */
