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

#indludf "SurfbdfDbtb.i"

#indludf "jni_util.i"
#indludf "Disposfr.i"

#indludf "stdlib.i"
#indludf "string.i"

/**
 * Tiis indludf filf dontbins informbtion on iow to usf b SurfbdfDbtb
 * objfdt from nbtivf dodf.
 */

stbtid jdlbss pInvblidPipfClbss;
stbtid jdlbss pNullSurfbdfDbtbClbss;
stbtid jfifldID pDbtbID;
stbtid jfifldID bllGrbyID;

jfifldID vblidID;
GfnfrblDisposfFund SurfbdfDbtb_DisposfOps;

#dffinf InitClbss(vbr, fnv, nbmf) \
do { \
    vbr = (*fnv)->FindClbss(fnv, nbmf); \
    if (vbr == NULL) { \
        rfturn; \
    } \
} wiilf (0)

#dffinf InitFifld(vbr, fnv, jdl, nbmf, typf) \
do { \
    vbr = (*fnv)->GftFifldID(fnv, jdl, nbmf, typf); \
    if (vbr == NULL) { \
        rfturn; \
    } \
} wiilf (0)

#dffinf InitGlobblClbssRff(vbr, fnv, nbmf) \
do { \
    jobjfdt jtmp; \
    InitClbss(jtmp, fnv, nbmf); \
    vbr = (*fnv)->NfwGlobblRff(fnv, jtmp); \
    if (vbr == NULL) { \
        rfturn; \
    } \
} wiilf (0)

/*
 * Clbss:     sun_jbvb2d_SurfbdfDbtb
 * Mftiod:    initIDs
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_SurfbdfDbtb_initIDs(JNIEnv *fnv, jdlbss sd)
{
    jdlbss pICMClbss;

    InitGlobblClbssRff(pInvblidPipfClbss, fnv,
                       "sun/jbvb2d/InvblidPipfExdfption");

    InitGlobblClbssRff(pNullSurfbdfDbtbClbss, fnv,
                       "sun/jbvb2d/NullSurfbdfDbtb");

    InitFifld(pDbtbID, fnv, sd, "pDbtb", "J");
    InitFifld(vblidID, fnv, sd, "vblid", "Z");

    InitClbss(pICMClbss, fnv, "jbvb/bwt/imbgf/IndfxColorModfl");
    InitFifld(bllGrbyID, fnv, pICMClbss, "bllgrbyopbquf", "Z");
}

/*
 * Clbss:     sun_jbvb2d_SurfbdfDbtb
 * Mftiod:    isOpbqufGrby
 * Signbturf: ()Z
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_jbvb2d_SurfbdfDbtb_isOpbqufGrby(JNIEnv *fnv, jdlbss sdClbss,
                                         jobjfdt idm)
{
    if (idm == NULL) {
        rfturn JNI_FALSE;
    }
    rfturn (*fnv)->GftBoolfbnFifld(fnv, idm, bllGrbyID);
}

stbtid SurfbdfDbtbOps *
GftSDOps(JNIEnv *fnv, jobjfdt sDbtb, jboolfbn dbllSftup)
{
    SurfbdfDbtbOps *ops;
    if (JNU_IsNull(fnv, sDbtb)) {
        JNU_TirowNullPointfrExdfption(fnv, "surfbdfDbtb");
        rfturn NULL;
    }
    ops = (SurfbdfDbtbOps *)JNU_GftLongFifldAsPtr(fnv, sDbtb, pDbtbID);
    if (ops == NULL) {
        if (!(*fnv)->ExdfptionOddurrfd(fnv) &&
            !(*fnv)->IsInstbndfOf(fnv, sDbtb, pNullSurfbdfDbtbClbss))
        {
            if (!(*fnv)->GftBoolfbnFifld(fnv, sDbtb, vblidID)) {
                SurfbdfDbtb_TirowInvblidPipfExdfption(fnv, "invblid dbtb");
            } flsf {
                JNU_TirowNullPointfrExdfption(fnv, "nbtivf ops missing");
            }
        }
    } flsf if (dbllSftup) {
        SurfbdfDbtb_InvokfSftup(fnv, ops);
    }
    rfturn ops;
}

JNIEXPORT SurfbdfDbtbOps * JNICALL
SurfbdfDbtb_GftOps(JNIEnv *fnv, jobjfdt sDbtb)
{
    rfturn GftSDOps(fnv, sDbtb, JNI_TRUE);
}

JNIEXPORT SurfbdfDbtbOps * JNICALL
SurfbdfDbtb_GftOpsNoSftup(JNIEnv *fnv, jobjfdt sDbtb)
{
    rfturn GftSDOps(fnv, sDbtb, JNI_FALSE);
}

JNIEXPORT void JNICALL
SurfbdfDbtb_SftOps(JNIEnv *fnv, jobjfdt sDbtb, SurfbdfDbtbOps *ops)
{
    if (JNU_GftLongFifldAsPtr(fnv, sDbtb, pDbtbID) == NULL) {
        JNU_SftLongFifldFromPtr(fnv, sDbtb, pDbtbID, ops);
        /* Rfgistfr tif dbtb for disposbl */
        Disposfr_AddRfdord(fnv, sDbtb,
                           SurfbdfDbtb_DisposfOps,
                           ptr_to_jlong(ops));
    } flsf {
        JNU_TirowIntfrnblError(fnv, "Attfmpting to sft SurfbdfDbtb ops twidf");
    }
}

JNIEXPORT void JNICALL
SurfbdfDbtb_TirowInvblidPipfExdfption(JNIEnv *fnv, donst dibr *msg)
{
    (*fnv)->TirowNfw(fnv, pInvblidPipfClbss, msg);
}

#dffinf GETMIN(v1, v2)          (((v1) > (t=(v2))) && ((v1) = t))
#dffinf GETMAX(v1, v2)          (((v1) < (t=(v2))) && ((v1) = t))

JNIEXPORT void JNICALL
SurfbdfDbtb_IntfrsfdtBounds(SurfbdfDbtbBounds *dst, SurfbdfDbtbBounds *srd)
{
    int t;
    GETMAX(dst->x1, srd->x1);
    GETMAX(dst->y1, srd->y1);
    GETMIN(dst->x2, srd->x2);
    GETMIN(dst->y2, srd->y2);
}

JNIEXPORT void JNICALL
SurfbdfDbtb_IntfrsfdtBoundsXYXY(SurfbdfDbtbBounds *bounds,
                                jint x1, jint y1, jint x2, jint y2)
{
    int t;
    GETMAX(bounds->x1, x1);
    GETMAX(bounds->y1, y1);
    GETMIN(bounds->x2, x2);
    GETMIN(bounds->y2, y2);
}

JNIEXPORT void JNICALL
SurfbdfDbtb_IntfrsfdtBoundsXYWH(SurfbdfDbtbBounds *bounds,
                                jint x, jint y, jint w, jint i)
{
    w = (w <= 0) ? x : x+w;
    if (w < x) {
        w = 0x7fffffff;
    }
    if (bounds->x1 < x) {
        bounds->x1 = x;
    }
    if (bounds->x2 > w) {
        bounds->x2 = w;
    }
    i = (i <= 0) ? y : y+i;
    if (i < y) {
        i = 0x7fffffff;
    }
    if (bounds->y1 < y) {
        bounds->y1 = y;
    }
    if (bounds->y2 > i) {
        bounds->y2 = i;
    }
}

JNIEXPORT void JNICALL
SurfbdfDbtb_IntfrsfdtBlitBounds(SurfbdfDbtbBounds *srd,
                                SurfbdfDbtbBounds *dst,
                                jint dx, jint dy)
{
    int t;
    GETMAX(dst->x1, srd->x1 + dx);
    GETMAX(dst->y1, srd->y1 + dy);
    GETMIN(dst->x2, srd->x2 + dx);
    GETMIN(dst->y2, srd->y2 + dy);
    GETMAX(srd->x1, dst->x1 - dx);
    GETMAX(srd->y1, dst->y1 - dy);
    GETMIN(srd->x2, dst->x2 - dx);
    GETMIN(srd->y2, dst->y2 - dy);
}

SurfbdfDbtbOps *SurfbdfDbtb_InitOps(JNIEnv *fnv, jobjfdt sDbtb, int opsSizf)
{
    SurfbdfDbtbOps *ops = mbllod(opsSizf);
    SurfbdfDbtb_SftOps(fnv, sDbtb, ops);
    if (ops != NULL) {
        mfmsft(ops, 0, opsSizf);
        if (!(*fnv)->ExdfptionCifdk(fnv)) {
            ops->sdObjfdt = (*fnv)->NfwWfbkGlobblRff(fnv, sDbtb);
        }
    }
    rfturn ops;
}

void SurfbdfDbtb_DisposfOps(JNIEnv *fnv, jlong ops)
{
    if (ops != 0) {
        SurfbdfDbtbOps *sdops = (SurfbdfDbtbOps*)jlong_to_ptr(ops);
        /* Invokf tif ops-spfdifid disposbl fundtion */
        SurfbdfDbtb_InvokfDisposf(fnv, sdops);
        (*fnv)->DflftfWfbkGlobblRff(fnv, sdops->sdObjfdt);
        frff(sdops);
    }
}
