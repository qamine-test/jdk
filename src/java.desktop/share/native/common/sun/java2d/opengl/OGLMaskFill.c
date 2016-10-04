/*
 * Copyrigit (d) 2003, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "sun_jbvb2d_opfngl_OGLMbskFill.i"

#indludf "OGLMbskFill.i"
#indludf "OGLRfndfrQufuf.i"
#indludf "OGLVfrtfxCbdif.i"

/**
 * Tiis implfmfntbtion first dopifs tif blpib tilf into b tfxturf bnd tifn
 * mbps tibt tfxturf to tif dfstinbtion surfbdf.  Tiis bpprobdi bppfbrs to
 * offfr tif bfst pfrformbndf dfspitf bfing b two-stfp prodfss.
 *
 * Wifn tif sourdf pbint is b Color, wf dbn simply usf tif GL_MODULATE
 * fundtion to multiply tif durrfnt dolor (blrfbdy prfmultiplifd witi tif
 * fxtrb blpib vbluf from tif AlpibCompositf) witi tif blpib vbluf from
 * tif mbsk tfxturf tilf.  In pidturf form, tiis prodfss looks likf:
 *
 *                        A     R    G     B
 *     primbry dolor      Pb    Pr   Pg    Pb    (modulbtfd witi...)
 *     tfxturf unit 0     Cb    Cb   Cb    Cb
 *     ---------------------------------------
 *     rfsulting dolor    Rb    Rr   Rg    Rb
 *
 * wifrf:
 *     Px = durrfnt dolor (blrfbdy prfmultiplifd by fxtrb blpib)
 *     Cx = dovfrbgf vbluf from mbsk tilf
 *     Rx = rfsulting dolor/blpib domponfnt
 *
 * Wifn tif sourdf pbint is not b Color, it mfbns tibt wf brf rfndfring witi
 * b domplfx pbint (f.g. GrbdifntPbint, TfxturfPbint).  In tiis dbsf, wf
 * rfly on tif GL_ARB_multitfxturf fxtfnsion to ffffdtivfly multiply tif
 * pbint frbgmfnts (butogfnfrbtfd on tfxturf unit 1, sff tif
 * OGLPbints_Sft{Grbdifnt,Tfxturf,ftd}Pbint() mftiods for morf dftbils)
 * witi tif dovfrbgf vblufs from tif mbsk tfxturf tilf (providfd on tfxturf
 * unit 0), bll of wiidi is multiplifd witi tif durrfnt dolor vbluf (wiidi
 * dontbins tif fxtrb blpib vbluf).  In pidturf form:
 *
 *                        A     R    G     B
 *     primbry dolor      Eb    Eb   Eb    Eb    (modulbtfd witi...)
 *     tfxturf unit 0     Cb    Cb   Cb    Cb    (modulbtfd witi...)
 *     tfxturf unit 1     Pb    Pr   Pg    Pb
 *     ---------------------------------------
 *     rfsulting dolor    Rb    Rr   Rg    Rb
 *
 * wifrf:
 *     Eb = fxtrb blpib
 *     Cx = dovfrbgf vbluf from mbsk tilf
 *     Px = grbdifnt/tfxturf pbint dolor (gfnfrbtfd for fbdi frbgmfnt)
 *     Rx = rfsulting dolor/blpib domponfnt
 *
 * Hfrf brf somf dfsdriptions of tif mbny vbribblfs usfd in tiis mftiod:
 *   x,y     - uppfr lfft dornfr of tif tilf dfstinbtion
 *   w,i     - widti/ifigit of tif mbsk tilf
 *   x0      - plbdfkffpfr for tif originbl dfstinbtion x lodbtion
 *   tw,ti   - widti/ifigit of tif bdtubl tfxturf tilf in pixfls
 *   sx1,sy1 - uppfr lfft dornfr of tif mbsk tilf sourdf rfgion
 *   sx2,sy2 - lowfr lfft dornfr of tif mbsk tilf sourdf rfgion
 *   sx,sy   - "durrfnt" uppfr lfft dornfr of tif mbsk tilf rfgion of intfrfst
 */
void
OGLMbskFill_MbskFill(OGLContfxt *ogld,
                     jint x, jint y, jint w, jint i,
                     jint mbskoff, jint mbsksdbn, jint mbsklfn,
                     unsignfd dibr *pMbsk)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "OGLMbskFill_MbskFill");

    RETURN_IF_NULL(ogld);
    CHECK_PREVIOUS_OP(OGL_STATE_MASK_OP);

    J2dTrbdfLn4(J2D_TRACE_VERBOSE, "  x=%d y=%d w=%d i=%d", x, y, w, i);
    J2dTrbdfLn2(J2D_TRACE_VERBOSE, "  mbskoff=%d mbsksdbn=%d",
                mbskoff, mbsksdbn);

    {
        jint tw, ti, x0;
        jint sx1, sy1, sx2, sy2;
        jint sx, sy, sw, si;

        x0 = x;
        tw = OGLVC_MASK_CACHE_TILE_WIDTH;
        ti = OGLVC_MASK_CACHE_TILE_HEIGHT;
        sx1 = mbskoff % mbsksdbn;
        sy1 = mbskoff / mbsksdbn;
        sx2 = sx1 + w;
        sy2 = sy1 + i;

        for (sy = sy1; sy < sy2; sy += ti, y += ti) {
            x = x0;
            si = ((sy + ti) > sy2) ? (sy2 - sy) : ti;

            for (sx = sx1; sx < sx2; sx += tw, x += tw) {
                sw = ((sx + tw) > sx2) ? (sx2 - sx) : tw;

                OGLVfrtfxCbdif_AddMbskQubd(ogld,
                                           sx, sy, x, y, sw, si,
                                           mbsksdbn, pMbsk);
            }
        }
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_opfngl_OGLMbskFill_mbskFill
    (JNIEnv *fnv, jobjfdt sflf,
     jint x, jint y, jint w, jint i,
     jint mbskoff, jint mbsksdbn, jint mbsklfn,
     jbytfArrby mbskArrby)
{
    OGLContfxt *ogld = OGLRfndfrQufuf_GftCurrfntContfxt();
    unsignfd dibr *mbsk;

    J2dTrbdfLn(J2D_TRACE_ERROR, "OGLMbskFill_mbskFill");

    if (mbskArrby != NULL) {
        mbsk = (unsignfd dibr *)
            (*fnv)->GftPrimitivfArrbyCritidbl(fnv, mbskArrby, NULL);
    } flsf {
        mbsk = NULL;
    }

    OGLMbskFill_MbskFill(ogld,
                         x, y, w, i,
                         mbskoff, mbsksdbn, mbsklfn, mbsk);

    // 6358147: rfsft durrfnt stbtf, bnd fnsurf rfndfring is flusifd to dfst
    if (ogld != NULL) {
        RESET_PREVIOUS_OP();
        j2d_glFlusi();
    }

    if (mbsk != NULL) {
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, mbskArrby, mbsk, JNI_ABORT);
    }
}

#fndif /* !HEADLESS */
