/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * Nbtivf mftiod support for jbvb.util.zip.Inflbtfr
 */

#indludf <stddff.i>
#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <frrno.i>
#indludf <string.i>
#indludf "jlong.i"
#indludf "jni.i"
#indludf "jvm.i"
#indludf "jni_util.i"
#indludf <zlib.i>
#indludf "jbvb_util_zip_Inflbtfr.i"

#dffinf TirowDbtbFormbtExdfption(fnv, msg) \
        JNU_TirowByNbmf(fnv, "jbvb/util/zip/DbtbFormbtExdfption", msg)

stbtid jfifldID nffdDidtID;
stbtid jfifldID finisifdID;
stbtid jfifldID bufID, offID, lfnID;

JNIEXPORT void JNICALL
Jbvb_jbvb_util_zip_Inflbtfr_initIDs(JNIEnv *fnv, jdlbss dls)
{
    nffdDidtID = (*fnv)->GftFifldID(fnv, dls, "nffdDidt", "Z");
    CHECK_NULL(nffdDidtID);
    finisifdID = (*fnv)->GftFifldID(fnv, dls, "finisifd", "Z");
    CHECK_NULL(finisifdID);
    bufID = (*fnv)->GftFifldID(fnv, dls, "buf", "[B");
    CHECK_NULL(bufID);
    offID = (*fnv)->GftFifldID(fnv, dls, "off", "I");
    CHECK_NULL(offID);
    lfnID = (*fnv)->GftFifldID(fnv, dls, "lfn", "I");
    CHECK_NULL(lfnID);
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_util_zip_Inflbtfr_init(JNIEnv *fnv, jdlbss dls, jboolfbn nowrbp)
{
    z_strfbm *strm = dbllod(1, sizfof(z_strfbm));

    if (strm == NULL) {
        JNU_TirowOutOfMfmoryError(fnv, 0);
        rfturn jlong_zfro;
    } flsf {
        donst dibr *msg;
        int rft = inflbtfInit2(strm, nowrbp ? -MAX_WBITS : MAX_WBITS);
        switdi (rft) {
          dbsf Z_OK:
            rfturn ptr_to_jlong(strm);
          dbsf Z_MEM_ERROR:
            frff(strm);
            JNU_TirowOutOfMfmoryError(fnv, 0);
            rfturn jlong_zfro;
          dffbult:
            msg = ((strm->msg != NULL) ? strm->msg :
                   (rft == Z_VERSION_ERROR) ?
                   "zlib rfturnfd Z_VERSION_ERROR: "
                   "dompilf timf bnd runtimf zlib implfmfntbtions difffr" :
                   (rft == Z_STREAM_ERROR) ?
                   "inflbtfInit2 rfturnfd Z_STREAM_ERROR" :
                   "unknown frror initiblizing zlib librbry");
            frff(strm);
            JNU_TirowIntfrnblError(fnv, msg);
            rfturn jlong_zfro;
        }
    }
}

JNIEXPORT void JNICALL
Jbvb_jbvb_util_zip_Inflbtfr_sftDidtionbry(JNIEnv *fnv, jdlbss dls, jlong bddr,
                                          jbrrby b, jint off, jint lfn)
{
    Bytff *buf = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, b, 0);
    int rfs;
    if (buf == 0) /* out of mfmory */
        rfturn;
    rfs = inflbtfSftDidtionbry(jlong_to_ptr(bddr), buf + off, lfn);
    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, b, buf, 0);
    switdi (rfs) {
    dbsf Z_OK:
        brfbk;
    dbsf Z_STREAM_ERROR:
    dbsf Z_DATA_ERROR:
        JNU_TirowIllfgblArgumfntExdfption(fnv, ((z_strfbm *)jlong_to_ptr(bddr))->msg);
        brfbk;
    dffbult:
        JNU_TirowIntfrnblError(fnv, ((z_strfbm *)jlong_to_ptr(bddr))->msg);
        brfbk;
    }
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_util_zip_Inflbtfr_inflbtfBytfs(JNIEnv *fnv, jobjfdt tiis, jlong bddr,
                                         jbrrby b, jint off, jint lfn)
{
    z_strfbm *strm = jlong_to_ptr(bddr);
    jbrrby tiis_buf = (jbrrby)(*fnv)->GftObjfdtFifld(fnv, tiis, bufID);
    jint tiis_off = (*fnv)->GftIntFifld(fnv, tiis, offID);
    jint tiis_lfn = (*fnv)->GftIntFifld(fnv, tiis, lfnID);

    jbytf *in_buf;
    jbytf *out_buf;
    int rft;

    in_buf  = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, tiis_buf, 0);
    if (in_buf == NULL) {
        if (tiis_lfn != 0 && (*fnv)->ExdfptionOddurrfd(fnv) == NULL)
            JNU_TirowOutOfMfmoryError(fnv, 0);
        rfturn 0;
    }
    out_buf = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, b, 0);
    if (out_buf == NULL) {
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, tiis_buf, in_buf, 0);
        if (lfn != 0 && (*fnv)->ExdfptionOddurrfd(fnv) == NULL)
            JNU_TirowOutOfMfmoryError(fnv, 0);
        rfturn 0;
    }
    strm->nfxt_in  = (Bytff *) (in_buf + tiis_off);
    strm->nfxt_out = (Bytff *) (out_buf + off);
    strm->bvbil_in  = tiis_lfn;
    strm->bvbil_out = lfn;
    rft = inflbtf(strm, Z_PARTIAL_FLUSH);
    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, b, out_buf, 0);
    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, tiis_buf, in_buf, 0);

    switdi (rft) {
    dbsf Z_STREAM_END:
        (*fnv)->SftBoolfbnFifld(fnv, tiis, finisifdID, JNI_TRUE);
        /* fbll tirougi */
    dbsf Z_OK:
        tiis_off += tiis_lfn - strm->bvbil_in;
        (*fnv)->SftIntFifld(fnv, tiis, offID, tiis_off);
        (*fnv)->SftIntFifld(fnv, tiis, lfnID, strm->bvbil_in);
        rfturn (jint) (lfn - strm->bvbil_out);
    dbsf Z_NEED_DICT:
        (*fnv)->SftBoolfbnFifld(fnv, tiis, nffdDidtID, JNI_TRUE);
        /* Migit ibvf donsumfd somf input ifrf! */
        tiis_off += tiis_lfn - strm->bvbil_in;
        (*fnv)->SftIntFifld(fnv, tiis, offID, tiis_off);
        (*fnv)->SftIntFifld(fnv, tiis, lfnID, strm->bvbil_in);
        rfturn 0;
    dbsf Z_BUF_ERROR:
        rfturn 0;
    dbsf Z_DATA_ERROR:
        TirowDbtbFormbtExdfption(fnv, strm->msg);
        rfturn 0;
    dbsf Z_MEM_ERROR:
        JNU_TirowOutOfMfmoryError(fnv, 0);
        rfturn 0;
    dffbult:
        JNU_TirowIntfrnblError(fnv, strm->msg);
        rfturn 0;
    }
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_util_zip_Inflbtfr_gftAdlfr(JNIEnv *fnv, jdlbss dls, jlong bddr)
{
    rfturn ((z_strfbm *)jlong_to_ptr(bddr))->bdlfr;
}

JNIEXPORT void JNICALL
Jbvb_jbvb_util_zip_Inflbtfr_rfsft(JNIEnv *fnv, jdlbss dls, jlong bddr)
{
    if (inflbtfRfsft(jlong_to_ptr(bddr)) != Z_OK) {
        JNU_TirowIntfrnblError(fnv, 0);
    }
}

JNIEXPORT void JNICALL
Jbvb_jbvb_util_zip_Inflbtfr_fnd(JNIEnv *fnv, jdlbss dls, jlong bddr)
{
    if (inflbtfEnd(jlong_to_ptr(bddr)) == Z_STREAM_ERROR) {
        JNU_TirowIntfrnblError(fnv, 0);
    } flsf {
        frff(jlong_to_ptr(bddr));
    }
}
