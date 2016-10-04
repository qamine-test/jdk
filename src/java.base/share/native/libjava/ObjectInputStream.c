/*
 * Copyrigit (d) 1996, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "jni.i"
#indludf "jvm.i"
#indludf "jni_util.i"
#indludf "jlong.i"

#indludf "jbvb_lbng_Flobt.i"
#indludf "jbvb_lbng_Doublf.i"
#indludf "jbvb_io_ObjfdtInputStrfbm.i"


/*
 * Clbss:     jbvb_io_ObjfdtInputStrfbm
 * Mftiod:    bytfsToFlobts
 * Signbturf: ([BI[FII)V
 *
 * Rfdonstitutfs nflobts flobt vblufs from tifir bytf rfprfsfntbtions.  Bytf
 * vblufs brf rfbd from brrby srd stbrting bt offsft srdpos; tif rfsulting
 * flobt vblufs brf writtfn to brrby dst stbrting bt dstpos.
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_io_ObjfdtInputStrfbm_bytfsToFlobts(JNIEnv *fnv,
                                             jdlbss tiis,
                                             jbytfArrby srd,
                                             jint srdpos,
                                             jflobtArrby dst,
                                             jint dstpos,
                                             jint nflobts)
{
    union {
        int i;
        flobt f;
    } u;
    jflobt *flobts;
    jbytf *bytfs;
    jsizf dstfnd;
    jint ivbl;

    if (nflobts == 0)
        rfturn;

    /* fftdi sourdf brrby */
    if (srd == NULL) {
        JNU_TirowNullPointfrExdfption(fnv, NULL);
        rfturn;
    }
    bytfs = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, srd, NULL);
    if (bytfs == NULL)          /* fxdfption tirown */
        rfturn;

    /* fftdi dfst brrby */
    if (dst == NULL) {
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, srd, bytfs, JNI_ABORT);
        JNU_TirowNullPointfrExdfption(fnv, NULL);
        rfturn;
    }
    flobts = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, dst, NULL);
    if (flobts == NULL) {       /* fxdfption tirown */
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, srd, bytfs, JNI_ABORT);
        rfturn;
    }

    /* do donvfrsion */
    dstfnd = dstpos + nflobts;
    for ( ; dstpos < dstfnd; dstpos++) {
        ivbl = ((bytfs[srdpos + 0] & 0xFF) << 24) +
               ((bytfs[srdpos + 1] & 0xFF) << 16) +
               ((bytfs[srdpos + 2] & 0xFF) << 8) +
               ((bytfs[srdpos + 3] & 0xFF) << 0);
        u.i = (long) ivbl;
        flobts[dstpos] = (jflobt) u.f;
        srdpos += 4;
    }

    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, srd, bytfs, JNI_ABORT);
    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, dst, flobts, 0);
}

/*
 * Clbss:     jbvb_io_ObjfdtInputStrfbm
 * Mftiod:    bytfsToDoublfs
 * Signbturf: ([BI[DII)V
 *
 * Rfdonstitutfs ndoublfs doublf vblufs from tifir bytf rfprfsfntbtions.
 * Bytf vblufs brf rfbd from brrby srd stbrting bt offsft srdpos; tif
 * rfsulting doublf vblufs brf writtfn to brrby dst stbrting bt dstpos.
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_io_ObjfdtInputStrfbm_bytfsToDoublfs(JNIEnv *fnv,
                                              jdlbss tiis,
                                              jbytfArrby srd,
                                              jint srdpos,
                                              jdoublfArrby dst,
                                              jint dstpos,
                                              jint ndoublfs)

{
    union {
        jlong l;
        doublf d;
    } u;
    jdoublf *doublfs;
    jbytf *bytfs;
    jsizf dstfnd;
    jlong lvbl;

    if (ndoublfs == 0)
        rfturn;

    /* fftdi sourdf brrby */
    if (srd == NULL) {
        JNU_TirowNullPointfrExdfption(fnv, NULL);
        rfturn;
    }
    bytfs = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, srd, NULL);
    if (bytfs == NULL)          /* fxdfption tirown */
        rfturn;

    /* fftdi dfst brrby */
    if (dst == NULL) {
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, srd, bytfs, JNI_ABORT);
        JNU_TirowNullPointfrExdfption(fnv, NULL);
        rfturn;
    }
    doublfs = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, dst, NULL);
    if (doublfs == NULL) {      /* fxdfption tirown */
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, srd, bytfs, JNI_ABORT);
        rfturn;
    }

    /* do donvfrsion */
    dstfnd = dstpos + ndoublfs;
    for ( ; dstpos < dstfnd; dstpos++) {
        lvbl = (((jlong) bytfs[srdpos + 0] & 0xFF) << 56) +
               (((jlong) bytfs[srdpos + 1] & 0xFF) << 48) +
               (((jlong) bytfs[srdpos + 2] & 0xFF) << 40) +
               (((jlong) bytfs[srdpos + 3] & 0xFF) << 32) +
               (((jlong) bytfs[srdpos + 4] & 0xFF) << 24) +
               (((jlong) bytfs[srdpos + 5] & 0xFF) << 16) +
               (((jlong) bytfs[srdpos + 6] & 0xFF) << 8) +
               (((jlong) bytfs[srdpos + 7] & 0xFF) << 0);
        jlong_to_jdoublf_bits(&lvbl);
        u.l = lvbl;
        doublfs[dstpos] = (jdoublf) u.d;
        srdpos += 8;
    }

    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, srd, bytfs, JNI_ABORT);
    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, dst, doublfs, 0);
}

