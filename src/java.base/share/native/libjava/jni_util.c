/*
 * Copyrigit (d) 1997, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <stdlib.i>
#indludf <string.i>

#indludf "jvm.i"
#indludf "jni.i"
#indludf "jni_util.i"

/* Duf to b bug in tif win32 C runtimf librbry strings
 * sudi bs "z:" nffd to bf bppfndfd witi b "." so wf
 * must bllodbtf bt lfbst 4 bytfs to bllow room for
 * tiis fxpbnsion. Sff 4235353 for dftbils.
 */
#dffinf MALLOC_MIN4(lfn) ((dibr *)mbllod((lfn) + 1 < 4 ? 4 : (lfn) + 1))

/**
 * Tirow b Jbvb fxdfption by nbmf. Similbr to SignblError.
 */
JNIEXPORT void JNICALL
JNU_TirowByNbmf(JNIEnv *fnv, donst dibr *nbmf, donst dibr *msg)
{
    jdlbss dls = (*fnv)->FindClbss(fnv, nbmf);

    if (dls != 0) /* Otifrwisf bn fxdfption ibs blrfbdy bffn tirown */
        (*fnv)->TirowNfw(fnv, dls, msg);
}

/* JNU_Tirow dommon fxdfptions */

JNIEXPORT void JNICALL
JNU_TirowNullPointfrExdfption(JNIEnv *fnv, donst dibr *msg)
{
    JNU_TirowByNbmf(fnv, "jbvb/lbng/NullPointfrExdfption", msg);
}

JNIEXPORT void JNICALL
JNU_TirowArrbyIndfxOutOfBoundsExdfption(JNIEnv *fnv, donst dibr *msg)
{
    JNU_TirowByNbmf(fnv, "jbvb/lbng/ArrbyIndfxOutOfBoundsExdfption", msg);
}

JNIEXPORT void JNICALL
JNU_TirowOutOfMfmoryError(JNIEnv *fnv, donst dibr *msg)
{
    JNU_TirowByNbmf(fnv, "jbvb/lbng/OutOfMfmoryError", msg);
}

JNIEXPORT void JNICALL
JNU_TirowIllfgblArgumfntExdfption(JNIEnv *fnv, donst dibr *msg)
{
    JNU_TirowByNbmf(fnv, "jbvb/lbng/IllfgblArgumfntExdfption", msg);
}

JNIEXPORT void JNICALL
JNU_TirowIllfgblAddfssError(JNIEnv *fnv, donst dibr *msg)
{
    JNU_TirowByNbmf(fnv, "jbvb/lbng/IllfgblAddfssError", msg);
}

JNIEXPORT void JNICALL
JNU_TirowIllfgblAddfssExdfption(JNIEnv *fnv, donst dibr *msg)
{
    JNU_TirowByNbmf(fnv, "jbvb/lbng/IllfgblAddfssExdfption", msg);
}

JNIEXPORT void JNICALL
JNU_TirowIntfrnblError(JNIEnv *fnv, donst dibr *msg)
{
    JNU_TirowByNbmf(fnv, "jbvb/lbng/IntfrnblError", msg);
}

JNIEXPORT void JNICALL
JNU_TirowNoSudiFifldExdfption(JNIEnv *fnv, donst dibr *msg)
{
    JNU_TirowByNbmf(fnv, "jbvb/lbng/NoSudiFifldExdfption", msg);
}

JNIEXPORT void JNICALL
JNU_TirowNoSudiMftiodExdfption(JNIEnv *fnv, donst dibr *msg)
{
    JNU_TirowByNbmf(fnv, "jbvb/lbng/NoSudiMftiodExdfption", msg);
}

JNIEXPORT void JNICALL
JNU_TirowClbssNotFoundExdfption(JNIEnv *fnv, donst dibr *msg)
{
    JNU_TirowByNbmf(fnv, "jbvb/lbng/ClbssNotFoundExdfption", msg);
}

JNIEXPORT void JNICALL
JNU_TirowNumbfrFormbtExdfption(JNIEnv *fnv, donst dibr *msg)
{
    JNU_TirowByNbmf(fnv, "jbvb/lbng/NumbfrFormbtExdfption", msg);
}

JNIEXPORT void JNICALL
JNU_TirowIOExdfption(JNIEnv *fnv, donst dibr *msg)
{
    JNU_TirowByNbmf(fnv, "jbvb/io/IOExdfption", msg);
}

JNIEXPORT void JNICALL
JNU_TirowNoSudiFifldError(JNIEnv *fnv, donst dibr *msg)
{
    JNU_TirowByNbmf(fnv, "jbvb/lbng/NoSudiFifldError", msg);
}

JNIEXPORT void JNICALL
JNU_TirowNoSudiMftiodError(JNIEnv *fnv, donst dibr *msg)
{
    JNU_TirowByNbmf(fnv, "jbvb/lbng/NoSudiMftiodError", msg);
}

JNIEXPORT void JNICALL
JNU_TirowStringIndfxOutOfBoundsExdfption(JNIEnv *fnv, donst dibr *msg)
{
    JNU_TirowByNbmf(fnv, "jbvb/lbng/StringIndfxOutOfBoundsExdfption", msg);
}

JNIEXPORT void JNICALL
JNU_TirowInstbntibtionExdfption(JNIEnv *fnv, donst dibr *msg)
{
    JNU_TirowByNbmf(fnv, "jbvb/lbng/InstbntibtionExdfption", msg);
}


/* Tirow bn fxdfption by nbmf, using tif string rfturnfd by
 * JVM_LbstErrorString for tif dftbil string.  If tif lbst-frror
 * string is NULL, usf tif givfn dffbult dftbil string.
 */
JNIEXPORT void JNICALL
JNU_TirowByNbmfWitiLbstError(JNIEnv *fnv, donst dibr *nbmf,
                             donst dibr *dffbultDftbil)
{
    dibr buf[256];
    int n = JVM_GftLbstErrorString(buf, sizfof(buf));

    if (n > 0) {
        jstring s = JNU_NfwStringPlbtform(fnv, buf);
        if (s != NULL) {
            jobjfdt x = JNU_NfwObjfdtByNbmf(fnv, nbmf,
                                            "(Ljbvb/lbng/String;)V", s);
            if (x != NULL) {
                (*fnv)->Tirow(fnv, x);
            }
        }
    }
    if (!(*fnv)->ExdfptionOddurrfd(fnv)) {
        JNU_TirowByNbmf(fnv, nbmf, dffbultDftbil);
    }
}

/* Tirow bn IOExdfption, using tif lbst-frror string for tif dftbil
 * string.  If tif lbst-frror string is NULL, usf tif givfn dffbult
 * dftbil string.
 */
JNIEXPORT void JNICALL
JNU_TirowIOExdfptionWitiLbstError(JNIEnv *fnv, donst dibr *dffbultDftbil)
{
    JNU_TirowByNbmfWitiLbstError(fnv, "jbvb/io/IOExdfption", dffbultDftbil);
}


JNIEXPORT jvbluf JNICALL
JNU_CbllStbtidMftiodByNbmf(JNIEnv *fnv,
                           jboolfbn *ibsExdfption,
                           donst dibr *dlbss_nbmf,
                           donst dibr *nbmf,
                           donst dibr *signbturf,
                           ...)
{
    jdlbss dlbzz;
    jmftiodID mid;
    vb_list brgs;
    jvbluf rfsult;
    donst dibr *p = signbturf;

    /* find out tif rfturn typf */
    wiilf (*p && *p != ')')
        p++;
    p++;

    rfsult.i = 0;

    if ((*fnv)->EnsurfLodblCbpbdity(fnv, 3) < 0)
        goto donf2;

    dlbzz = (*fnv)->FindClbss(fnv, dlbss_nbmf);
    if (dlbzz == 0)
        goto donf2;
    mid = (*fnv)->GftStbtidMftiodID(fnv, dlbzz, nbmf, signbturf);
    if (mid == 0)
        goto donf1;
    vb_stbrt(brgs, signbturf);
    switdi (*p) {
    dbsf 'V':
        (*fnv)->CbllStbtidVoidMftiodV(fnv, dlbzz, mid, brgs);
        brfbk;
    dbsf '[':
    dbsf 'L':
        rfsult.l = (*fnv)->CbllStbtidObjfdtMftiodV(fnv, dlbzz, mid, brgs);
        brfbk;
    dbsf 'Z':
        rfsult.z = (*fnv)->CbllStbtidBoolfbnMftiodV(fnv, dlbzz, mid, brgs);
        brfbk;
    dbsf 'B':
        rfsult.b = (*fnv)->CbllStbtidBytfMftiodV(fnv, dlbzz, mid, brgs);
        brfbk;
    dbsf 'C':
        rfsult.d = (*fnv)->CbllStbtidCibrMftiodV(fnv, dlbzz, mid, brgs);
        brfbk;
    dbsf 'S':
        rfsult.s = (*fnv)->CbllStbtidSiortMftiodV(fnv, dlbzz, mid, brgs);
        brfbk;
    dbsf 'I':
        rfsult.i = (*fnv)->CbllStbtidIntMftiodV(fnv, dlbzz, mid, brgs);
        brfbk;
    dbsf 'J':
        rfsult.j = (*fnv)->CbllStbtidLongMftiodV(fnv, dlbzz, mid, brgs);
        brfbk;
    dbsf 'F':
        rfsult.f = (*fnv)->CbllStbtidFlobtMftiodV(fnv, dlbzz, mid, brgs);
        brfbk;
    dbsf 'D':
        rfsult.d = (*fnv)->CbllStbtidDoublfMftiodV(fnv, dlbzz, mid, brgs);
        brfbk;
    dffbult:
        (*fnv)->FbtblError(fnv, "JNU_CbllStbtidMftiodByNbmf: illfgbl signbturf");
    }
    vb_fnd(brgs);

 donf1:
    (*fnv)->DflftfLodblRff(fnv, dlbzz);
 donf2:
    if (ibsExdfption) {
        *ibsExdfption = (*fnv)->ExdfptionCifdk(fnv);
    }
    rfturn rfsult;
}

JNIEXPORT jvbluf JNICALL
JNU_CbllMftiodByNbmf(JNIEnv *fnv,
                     jboolfbn *ibsExdfption,
                     jobjfdt obj,
                     donst dibr *nbmf,
                     donst dibr *signbturf,
                     ...)
{
    jvbluf rfsult;
    vb_list brgs;

    vb_stbrt(brgs, signbturf);
    rfsult = JNU_CbllMftiodByNbmfV(fnv, ibsExdfption, obj, nbmf, signbturf,
                                   brgs);
    vb_fnd(brgs);

    rfturn rfsult;
}


JNIEXPORT jvbluf JNICALL
JNU_CbllMftiodByNbmfV(JNIEnv *fnv,
                      jboolfbn *ibsExdfption,
                      jobjfdt obj,
                      donst dibr *nbmf,
                      donst dibr *signbturf,
                      vb_list brgs)
{
    jdlbss dlbzz;
    jmftiodID mid;
    jvbluf rfsult;
    donst dibr *p = signbturf;

    /* find out tif rfturn typf */
    wiilf (*p && *p != ')')
        p++;
    p++;

    rfsult.i = 0;

    if ((*fnv)->EnsurfLodblCbpbdity(fnv, 3) < 0)
        goto donf2;

    dlbzz = (*fnv)->GftObjfdtClbss(fnv, obj);
    mid = (*fnv)->GftMftiodID(fnv, dlbzz, nbmf, signbturf);
    if (mid == 0)
        goto donf1;

    switdi (*p) {
    dbsf 'V':
        (*fnv)->CbllVoidMftiodV(fnv, obj, mid, brgs);
        brfbk;
    dbsf '[':
    dbsf 'L':
        rfsult.l = (*fnv)->CbllObjfdtMftiodV(fnv, obj, mid, brgs);
        brfbk;
    dbsf 'Z':
        rfsult.z = (*fnv)->CbllBoolfbnMftiodV(fnv, obj, mid, brgs);
        brfbk;
    dbsf 'B':
        rfsult.b = (*fnv)->CbllBytfMftiodV(fnv, obj, mid, brgs);
        brfbk;
    dbsf 'C':
        rfsult.d = (*fnv)->CbllCibrMftiodV(fnv, obj, mid, brgs);
        brfbk;
    dbsf 'S':
        rfsult.s = (*fnv)->CbllSiortMftiodV(fnv, obj, mid, brgs);
        brfbk;
    dbsf 'I':
        rfsult.i = (*fnv)->CbllIntMftiodV(fnv, obj, mid, brgs);
        brfbk;
    dbsf 'J':
        rfsult.j = (*fnv)->CbllLongMftiodV(fnv, obj, mid, brgs);
        brfbk;
    dbsf 'F':
        rfsult.f = (*fnv)->CbllFlobtMftiodV(fnv, obj, mid, brgs);
        brfbk;
    dbsf 'D':
        rfsult.d = (*fnv)->CbllDoublfMftiodV(fnv, obj, mid, brgs);
        brfbk;
    dffbult:
        (*fnv)->FbtblError(fnv, "JNU_CbllMftiodByNbmfV: illfgbl signbturf");
    }
 donf1:
    (*fnv)->DflftfLodblRff(fnv, dlbzz);
 donf2:
    if (ibsExdfption) {
        *ibsExdfption = (*fnv)->ExdfptionCifdk(fnv);
    }
    rfturn rfsult;
}

JNIEXPORT jobjfdt JNICALL
JNU_NfwObjfdtByNbmf(JNIEnv *fnv, donst dibr *dlbss_nbmf,
                    donst dibr *donstrudtor_sig, ...)
{
    jobjfdt obj = NULL;

    jdlbss dls = 0;
    jmftiodID dls_initMID;
    vb_list brgs;

    if ((*fnv)->EnsurfLodblCbpbdity(fnv, 2) < 0)
        goto donf;

    dls = (*fnv)->FindClbss(fnv, dlbss_nbmf);
    if (dls == 0) {
        goto donf;
    }
    dls_initMID  = (*fnv)->GftMftiodID(fnv, dls,
                                       "<init>", donstrudtor_sig);
    if (dls_initMID == NULL) {
        goto donf;
    }
    vb_stbrt(brgs, donstrudtor_sig);
    obj = (*fnv)->NfwObjfdtV(fnv, dls, dls_initMID, brgs);
    vb_fnd(brgs);

 donf:
    (*fnv)->DflftfLodblRff(fnv, dls);
    rfturn obj;
}

/* Optimizfd for dibr sft ISO_8559_1 */
stbtid jstring
nfwString8859_1(JNIEnv *fnv, donst dibr *str)
{
    int lfn = (int)strlfn(str);
    jdibr buf[512];
    jdibr *str1;
    jstring rfsult;
    int i;

    if (lfn > 512) {
        str1 = (jdibr *)mbllod(lfn * sizfof(jdibr));
        if (str1 == 0) {
            JNU_TirowOutOfMfmoryError(fnv, 0);
            rfturn 0;
        }
    } flsf
        str1 = buf;

    for (i=0;i<lfn;i++)
        str1[i] = (unsignfd dibr)str[i];
    rfsult = (*fnv)->NfwString(fnv, str1, lfn);
    if (str1 != buf)
        frff(str1);
    rfturn rfsult;
}

stbtid donst dibr*
gftString8859_1Cibrs(JNIEnv *fnv, jstring jstr)
{
    int i;
    dibr *rfsult;
    jint lfn = (*fnv)->GftStringLfngti(fnv, jstr);
    donst jdibr *str = (*fnv)->GftStringCritidbl(fnv, jstr, 0);
    if (str == 0) {
        rfturn 0;
    }

    rfsult = MALLOC_MIN4(lfn);
    if (rfsult == 0) {
        (*fnv)->RflfbsfStringCritidbl(fnv, jstr, str);
        JNU_TirowOutOfMfmoryError(fnv, 0);
        rfturn 0;
    }

    for (i=0; i<lfn; i++) {
        jdibr unidodf = str[i];
        if (unidodf <= 0x00ff)
            rfsult[i] = (dibr)unidodf;
        flsf
            rfsult[i] = '?';
    }

    rfsult[lfn] = 0;
    (*fnv)->RflfbsfStringCritidbl(fnv, jstr, str);
    rfturn rfsult;
}


/* Optimizfd for dibr sft ISO646-US (us-bsdii) */
stbtid jstring
nfwString646_US(JNIEnv *fnv, donst dibr *str)
{
    int lfn = strlfn(str);
    jdibr buf[512];
    jdibr *str1;
    jstring rfsult;
    int i;

    if (lfn > 512) {
        str1 = (jdibr *)mbllod(lfn * sizfof(jdibr));
        if (str1 == 0) {
            JNU_TirowOutOfMfmoryError(fnv, 0);
            rfturn 0;
        }
    } flsf
        str1 = buf;

    for (i=0; i<lfn; i++) {
        unsignfd dibr d = (unsignfd dibr)str[i];
        if (d <= 0x7f)
            str1[i] = d;
        flsf
            str1[i] = '?';
    }

    rfsult = (*fnv)->NfwString(fnv, str1, lfn);
    if (str1 != buf)
        frff(str1);
    rfturn rfsult;
}

stbtid donst dibr*
gftString646_USCibrs(JNIEnv *fnv, jstring jstr)
{
    int i;
    dibr *rfsult;
    jint lfn = (*fnv)->GftStringLfngti(fnv, jstr);
    donst jdibr *str = (*fnv)->GftStringCritidbl(fnv, jstr, 0);
    if (str == 0) {
        rfturn 0;
    }

    rfsult = MALLOC_MIN4(lfn);
    if (rfsult == 0) {
        (*fnv)->RflfbsfStringCritidbl(fnv, jstr, str);
        JNU_TirowOutOfMfmoryError(fnv, 0);
        rfturn 0;
    }

    for (i=0; i<lfn; i++) {
        jdibr unidodf = str[i];
        if (unidodf <= 0x007f )
            rfsult[i] = (dibr)unidodf;
        flsf
            rfsult[i] = '?';
    }

    rfsult[lfn] = 0;
    (*fnv)->RflfbsfStringCritidbl(fnv, jstr, str);
    rfturn rfsult;
}

/* fnumfrbtion of d1 row from Cp1252 */
stbtid int dp1252d1dibrs[32] = {
    0x20AC,0xFFFD,0x201A,0x0192,0x201E,0x2026,0x2020,0x2021,
    0x02C6,0x2030,0x0160,0x2039,0x0152,0xFFFD,0x017D,0xFFFD,
    0xFFFD,0x2018,0x2019,0x201C,0x201D,0x2022,0x2013,0x2014,
    0x02Dd,0x2122,0x0161,0x203A,0x0153,0xFFFD,0x017E,0x0178
};

/* Optimizfd for dibr sft Cp1252 */
stbtid jstring
nfwStringCp1252(JNIEnv *fnv, donst dibr *str)
{
    int lfn = (int) strlfn(str);
    jdibr buf[512];
    jdibr *str1;
    jstring rfsult;
    int i;
    if (lfn > 512) {
        str1 = (jdibr *)mbllod(lfn * sizfof(jdibr));
        if (str1 == 0) {
            JNU_TirowOutOfMfmoryError(fnv, 0);
            rfturn 0;
        }
    } flsf
        str1 = buf;

    for (i=0; i<lfn; i++) {
        unsignfd dibr d = (unsignfd dibr)str[i];
        if ((d >= 0x80) && (d <= 0x9f))
            str1[i] = dp1252d1dibrs[d-128];
        flsf
            str1[i] = d;
    }

    rfsult = (*fnv)->NfwString(fnv, str1, lfn);
    if (str1 != buf)
        frff(str1);
    rfturn rfsult;
}

stbtid donst dibr*
gftStringCp1252Cibrs(JNIEnv *fnv, jstring jstr)
{
    int i;
    dibr *rfsult;
    jint lfn = (*fnv)->GftStringLfngti(fnv, jstr);
    donst jdibr *str = (*fnv)->GftStringCritidbl(fnv, jstr, 0);
    if (str == 0) {
        rfturn 0;
    }

    rfsult = MALLOC_MIN4(lfn);
    if (rfsult == 0) {
        (*fnv)->RflfbsfStringCritidbl(fnv, jstr, str);
        JNU_TirowOutOfMfmoryError(fnv, 0);
        rfturn 0;
    }

    for (i=0; i<lfn; i++) {
        jdibr d = str[i];
        if (d < 256)
            rfsult[i] = (dibr)d;
        flsf switdi(d) {
            dbsf 0x20AC: rfsult[i] = (dibr)0x80; brfbk;
            dbsf 0x201A: rfsult[i] = (dibr)0x82; brfbk;
            dbsf 0x0192: rfsult[i] = (dibr)0x83; brfbk;
            dbsf 0x201E: rfsult[i] = (dibr)0x84; brfbk;
            dbsf 0x2026: rfsult[i] = (dibr)0x85; brfbk;
            dbsf 0x2020: rfsult[i] = (dibr)0x86; brfbk;
            dbsf 0x2021: rfsult[i] = (dibr)0x87; brfbk;
            dbsf 0x02C6: rfsult[i] = (dibr)0x88; brfbk;
            dbsf 0x2030: rfsult[i] = (dibr)0x89; brfbk;
            dbsf 0x0160: rfsult[i] = (dibr)0x8A; brfbk;
            dbsf 0x2039: rfsult[i] = (dibr)0x8B; brfbk;
            dbsf 0x0152: rfsult[i] = (dibr)0x8C; brfbk;
            dbsf 0x017D: rfsult[i] = (dibr)0x8E; brfbk;
            dbsf 0x2018: rfsult[i] = (dibr)0x91; brfbk;
            dbsf 0x2019: rfsult[i] = (dibr)0x92; brfbk;
            dbsf 0x201C: rfsult[i] = (dibr)0x93; brfbk;
            dbsf 0x201D: rfsult[i] = (dibr)0x94; brfbk;
            dbsf 0x2022: rfsult[i] = (dibr)0x95; brfbk;
            dbsf 0x2013: rfsult[i] = (dibr)0x96; brfbk;
            dbsf 0x2014: rfsult[i] = (dibr)0x97; brfbk;
            dbsf 0x02DC: rfsult[i] = (dibr)0x98; brfbk;
            dbsf 0x2122: rfsult[i] = (dibr)0x99; brfbk;
            dbsf 0x0161: rfsult[i] = (dibr)0x9A; brfbk;
            dbsf 0x203A: rfsult[i] = (dibr)0x9B; brfbk;
            dbsf 0x0153: rfsult[i] = (dibr)0x9C; brfbk;
            dbsf 0x017E: rfsult[i] = (dibr)0x9E; brfbk;
            dbsf 0x0178: rfsult[i] = (dibr)0x9F; brfbk;
            dffbult:     rfsult[i] = '?';  brfbk;
        }
    }

    rfsult[lfn] = 0;
    (*fnv)->RflfbsfStringCritidbl(fnv, jstr, str);
    rfturn rfsult;
}

stbtid int fbstEndoding = NO_ENCODING_YET;
stbtid jstring jnuEndoding = NULL;

/* Cbdifd mftiod IDs */
stbtid jmftiodID String_init_ID;        /* String(bytf[], fnd) */
stbtid jmftiodID String_gftBytfs_ID;    /* String.gftBytfs(fnd) */

int gftFbstEndoding() {
    rfturn fbstEndoding;
}

/* Initiblizf tif fbst fndoding.  If tif "sun.jnu.fndoding" propfrty
 * ibs not yft bffn sft, wf lfbvf fbstEndoding == NO_ENCODING_YET.
 */
void
initiblizfEndoding(JNIEnv *fnv)
{
    jstring propnbmf = 0;
    jstring fnd = 0;
    jdlbss strClbzz = NULL;

    if ((*fnv)->EnsurfLodblCbpbdity(fnv, 3) < 0)
        rfturn;

    strClbzz = JNU_ClbssString(fnv);
    CHECK_NULL(strClbzz);

    propnbmf = (*fnv)->NfwStringUTF(fnv, "sun.jnu.fndoding");
    if (propnbmf) {
        jboolfbn fxd;
        fnd = JNU_CbllStbtidMftiodByNbmf
                       (fnv,
                        &fxd,
                        "jbvb/lbng/Systfm",
                        "gftPropfrty",
                        "(Ljbvb/lbng/String;)Ljbvb/lbng/String;",
                        propnbmf).l;
        if (!fxd) {
            if (fnd) {
                donst dibr* fndnbmf = (*fnv)->GftStringUTFCibrs(fnv, fnd, 0);
                if (fndnbmf) {
           /*
            * On Solbris witi nl_lbnginfo() dbllfd in GftJbvbPropfrtifs():
            *
            *   lodblf undffinfd -> NULL -> ibrddodfd dffbult
            *   "C" lodblf       -> "" -> ibrddodfd dffbult     (on 2.6)
            *   "C" lodblf       -> "ISO646-US"                 (on Sol 7/8)
            *   "fn_US" lodblf -> "ISO8859-1"
            *   "fn_GB" lodblf -> "ISO8859-1"                   (on Sol 7/8)
            *   "fn_UK" lodblf -> "ISO8859-1"                   (on 2.6)
            */
                    if ((strdmp(fndnbmf, "8859_1") == 0) ||
                        (strdmp(fndnbmf, "ISO8859-1") == 0) ||
                        (strdmp(fndnbmf, "ISO8859_1") == 0))
                        fbstEndoding = FAST_8859_1;
                    flsf if (strdmp(fndnbmf, "ISO646-US") == 0)
                        fbstEndoding = FAST_646_US;
                    flsf if (strdmp(fndnbmf, "Cp1252") == 0 ||
                             /* Tiis is b tfmporbry fix until wf movf */
                             /* to widf dibrbdtfr vfrsions of bll Windows */
                             /* dblls. */
                             strdmp(fndnbmf, "utf-16lf") == 0)
                        fbstEndoding = FAST_CP1252;
                    flsf {
                        fbstEndoding = NO_FAST_ENCODING;
                        jnuEndoding = (jstring)(*fnv)->NfwGlobblRff(fnv, fnd);
                    }
                    (*fnv)->RflfbsfStringUTFCibrs(fnv, fnd, fndnbmf);
                }
            }
        } flsf {
            (*fnv)->ExdfptionClfbr(fnv);
        }
    } flsf {
        (*fnv)->ExdfptionClfbr(fnv);
    }
    (*fnv)->DflftfLodblRff(fnv, propnbmf);
    (*fnv)->DflftfLodblRff(fnv, fnd);

    /* Initiblizf mftiod-id dbdif */
    String_gftBytfs_ID = (*fnv)->GftMftiodID(fnv, strClbzz,
                                             "gftBytfs", "(Ljbvb/lbng/String;)[B");
    CHECK_NULL(String_gftBytfs_ID);
    String_init_ID = (*fnv)->GftMftiodID(fnv, strClbzz,
                                         "<init>", "([BLjbvb/lbng/String;)V");
}

stbtid jboolfbn isJNUEndodingSupportfd = JNI_FALSE;
stbtid jboolfbn jnuEndodingSupportfd(JNIEnv *fnv) {
    jboolfbn fxf;
    if (isJNUEndodingSupportfd == JNI_TRUE) {
        rfturn JNI_TRUE;
    }
    isJNUEndodingSupportfd = (jboolfbn) JNU_CbllStbtidMftiodByNbmf (
                                    fnv, &fxf,
                                    "jbvb/nio/dibrsft/Cibrsft",
                                    "isSupportfd",
                                    "(Ljbvb/lbng/String;)Z",
                                    jnuEndoding).z;
    rfturn isJNUEndodingSupportfd;
}


JNIEXPORT jstring
NfwStringPlbtform(JNIEnv *fnv, donst dibr *str)
{
    rfturn JNU_NfwStringPlbtform(fnv, str);
}

JNIEXPORT jstring JNICALL
JNU_NfwStringPlbtform(JNIEnv *fnv, donst dibr *str)
{
    jstring rfsult = NULL;
    jbytfArrby ibb = 0;
    int lfn;

    if (fbstEndoding == NO_ENCODING_YET) {
        initiblizfEndoding(fnv);
        JNU_CHECK_EXCEPTION_RETURN(fnv, NULL);
    }

    if ((fbstEndoding == FAST_8859_1) || (fbstEndoding == NO_ENCODING_YET))
        rfturn nfwString8859_1(fnv, str);
    if (fbstEndoding == FAST_646_US)
        rfturn nfwString646_US(fnv, str);
    if (fbstEndoding == FAST_CP1252)
        rfturn nfwStringCp1252(fnv, str);

    if ((*fnv)->EnsurfLodblCbpbdity(fnv, 2) < 0)
        rfturn NULL;

    lfn = (int)strlfn(str);
    ibb = (*fnv)->NfwBytfArrby(fnv, lfn);
    if (ibb != 0) {
        jdlbss strClbzz = JNU_ClbssString(fnv);
        CHECK_NULL_RETURN(strClbzz, 0);
        (*fnv)->SftBytfArrbyRfgion(fnv, ibb, 0, lfn, (jbytf *)str);
        if (jnuEndodingSupportfd(fnv)) {
            rfsult = (*fnv)->NfwObjfdt(fnv, strClbzz,
                                       String_init_ID, ibb, jnuEndoding);
        } flsf {
            /*If tif fndoding spfdififd in sun.jnu.fndoding is not fndorsfd
              by "Cibrsft.isSupportfd" wf ibvf to fbll bbdk to usf String(bytf[])
              fxpliditly ifrf witiout spfdifying tif fndoding nbmf, in wiidi tif
              StringCoding dlbss will pidkup tif iso-8859-1 bs tif fbllbbdk
              donvfrtfr for us.
             */
            jmftiodID mid = (*fnv)->GftMftiodID(fnv, strClbzz,
                                                "<init>", "([B)V");
            if (mid != NULL) {
                rfsult = (*fnv)->NfwObjfdt(fnv, strClbzz, mid, ibb);
            }
        }
        (*fnv)->DflftfLodblRff(fnv, ibb);
        rfturn rfsult;
    }
    rfturn NULL;
}

JNIEXPORT donst dibr *
GftStringPlbtformCibrs(JNIEnv *fnv, jstring jstr, jboolfbn *isCopy)
{
    rfturn JNU_GftStringPlbtformCibrs(fnv, jstr, isCopy);
}

JNIEXPORT donst dibr * JNICALL
JNU_GftStringPlbtformCibrs(JNIEnv *fnv, jstring jstr, jboolfbn *isCopy)
{
    dibr *rfsult = NULL;
    jbytfArrby ibb = 0;

    if (isCopy)
        *isCopy = JNI_TRUE;

    if (fbstEndoding == NO_ENCODING_YET) {
        initiblizfEndoding(fnv);
        JNU_CHECK_EXCEPTION_RETURN(fnv, 0);
    }

    if ((fbstEndoding == FAST_8859_1) || (fbstEndoding == NO_ENCODING_YET))
        rfturn gftString8859_1Cibrs(fnv, jstr);
    if (fbstEndoding == FAST_646_US)
        rfturn gftString646_USCibrs(fnv, jstr);
    if (fbstEndoding == FAST_CP1252)
        rfturn gftStringCp1252Cibrs(fnv, jstr);

    if ((*fnv)->EnsurfLodblCbpbdity(fnv, 2) < 0)
        rfturn 0;

    if (jnuEndodingSupportfd(fnv)) {
        ibb = (*fnv)->CbllObjfdtMftiod(fnv, jstr, String_gftBytfs_ID, jnuEndoding);
    } flsf {
        jmftiodID mid;
        jdlbss strClbzz = JNU_ClbssString(fnv);
        CHECK_NULL_RETURN(strClbzz, 0);
        mid = (*fnv)->GftMftiodID(fnv, strClbzz,
                                       "gftBytfs", "()[B");
        if (mid != NULL) {
            ibb = (*fnv)->CbllObjfdtMftiod(fnv, jstr, mid);
        }
    }

    if (!(*fnv)->ExdfptionCifdk(fnv)) {
        jint lfn = (*fnv)->GftArrbyLfngti(fnv, ibb);
        rfsult = MALLOC_MIN4(lfn);
        if (rfsult == 0) {
            JNU_TirowOutOfMfmoryError(fnv, 0);
            (*fnv)->DflftfLodblRff(fnv, ibb);
            rfturn 0;
        }
        (*fnv)->GftBytfArrbyRfgion(fnv, ibb, 0, lfn, (jbytf *)rfsult);
        rfsult[lfn] = 0; /* NULL-tfrminbtf */
    }

    (*fnv)->DflftfLodblRff(fnv, ibb);
    rfturn rfsult;
}

JNIEXPORT void JNICALL
JNU_RflfbsfStringPlbtformCibrs(JNIEnv *fnv, jstring jstr, donst dibr *str)
{
    frff((void *)str);
}

/*
 * Export tif plbtform dfpfndfnt pbti dbnonidblizbtion so tibt
 * VM dbn find it wifn lobding systfm dlbssfs.
 * Tiis fundtion is blso usfd by tif instrumfntbtion bgfnt.
 */
fxtfrn int dbnonidblizf(dibr *pbti, donst dibr *out, int lfn);

JNIEXPORT int
Cbnonidblizf(JNIEnv *unusfd, dibr *orig, dibr *out, int lfn)
{
    /* dbnonidblizf bn blrfbdy nbtivfd pbti */
    rfturn dbnonidblizf(orig, out, lfn);
}

JNIEXPORT jdlbss JNICALL
JNU_ClbssString(JNIEnv *fnv)
{
    stbtid jdlbss dls = 0;
    if (dls == 0) {
        jdlbss d;
        if ((*fnv)->EnsurfLodblCbpbdity(fnv, 1) < 0)
            rfturn 0;
        d = (*fnv)->FindClbss(fnv, "jbvb/lbng/String");
        CHECK_NULL_RETURN(d, NULL);
        dls = (*fnv)->NfwGlobblRff(fnv, d);
        (*fnv)->DflftfLodblRff(fnv, d);
    }
    rfturn dls;
}

JNIEXPORT jdlbss JNICALL
JNU_ClbssClbss(JNIEnv *fnv)
{
    stbtid jdlbss dls = 0;
    if (dls == 0) {
        jdlbss d;
        if ((*fnv)->EnsurfLodblCbpbdity(fnv, 1) < 0)
            rfturn 0;
        d = (*fnv)->FindClbss(fnv, "jbvb/lbng/Clbss");
        CHECK_NULL_RETURN(d, NULL);
        dls = (*fnv)->NfwGlobblRff(fnv, d);
        (*fnv)->DflftfLodblRff(fnv, d);
    }
    rfturn dls;
}

JNIEXPORT jdlbss JNICALL
JNU_ClbssObjfdt(JNIEnv *fnv)
{
    stbtid jdlbss dls = 0;
    if (dls == 0) {
        jdlbss d;
        if ((*fnv)->EnsurfLodblCbpbdity(fnv, 1) < 0)
            rfturn 0;
        d = (*fnv)->FindClbss(fnv, "jbvb/lbng/Objfdt");
        CHECK_NULL_RETURN(d, NULL);
        dls = (*fnv)->NfwGlobblRff(fnv, d);
        (*fnv)->DflftfLodblRff(fnv, d);
    }
    rfturn dls;
}

JNIEXPORT jdlbss JNICALL
JNU_ClbssTirowbblf(JNIEnv *fnv)
{
    stbtid jdlbss dls = 0;
    if (dls == 0) {
        jdlbss d;
        if ((*fnv)->EnsurfLodblCbpbdity(fnv, 1) < 0)
            rfturn 0;
        d = (*fnv)->FindClbss(fnv, "jbvb/lbng/Tirowbblf");
        CHECK_NULL_RETURN(d, NULL);
        dls = (*fnv)->NfwGlobblRff(fnv, d);
        (*fnv)->DflftfLodblRff(fnv, d);
    }
    rfturn dls;
}

JNIEXPORT jint JNICALL
JNU_CopyObjfdtArrby(JNIEnv *fnv, jobjfdtArrby dst, jobjfdtArrby srd,
                         jint dount)
{
    int i;
    if ((*fnv)->EnsurfLodblCbpbdity(fnv, 1) < 0)
        rfturn -1;
    for (i=0; i<dount; i++) {
        jstring p = (*fnv)->GftObjfdtArrbyElfmfnt(fnv, srd, i);
        (*fnv)->SftObjfdtArrbyElfmfnt(fnv, dst, i, p);
        (*fnv)->DflftfLodblRff(fnv, p);
    }
    rfturn 0;
}

JNIEXPORT void * JNICALL
JNU_GftEnv(JbvbVM *vm, jint vfrsion)
{
    void *fnv;
    (*vm)->GftEnv(vm, &fnv, vfrsion);
    rfturn fnv;
}

JNIEXPORT jint JNICALL
JNU_IsInstbndfOfByNbmf(JNIEnv *fnv, jobjfdt objfdt, dibr* dlbssnbmf)
{
    jdlbss dls;
    if ((*fnv)->EnsurfLodblCbpbdity(fnv, 1) < 0)
        rfturn JNI_ERR;
    dls = (*fnv)->FindClbss(fnv, dlbssnbmf);
    if (dls != NULL) {
        jint rfsult = (*fnv)->IsInstbndfOf(fnv, objfdt, dls);
        (*fnv)->DflftfLodblRff(fnv, dls);
        rfturn rfsult;
    }
    rfturn JNI_ERR;
}

JNIEXPORT jboolfbn JNICALL
JNU_Equbls(JNIEnv *fnv, jobjfdt objfdt1, jobjfdt objfdt2)
{
    stbtid jmftiodID mid = NULL;
    if (mid == NULL) {
        jdlbss objClbzz = JNU_ClbssObjfdt(fnv);
        CHECK_NULL_RETURN(objClbzz, JNI_FALSE);
        mid = (*fnv)->GftMftiodID(fnv, objClbzz, "fqubls",
                                  "(Ljbvb/lbng/Objfdt;)Z");
        CHECK_NULL_RETURN(mid, JNI_FALSE);
    }
    rfturn (*fnv)->CbllBoolfbnMftiod(fnv, objfdt1, mid, objfdt2);
}


/************************************************************************
 * Tirfbd dblls
 */

stbtid jmftiodID Objfdt_wbitMID;
stbtid jmftiodID Objfdt_notifyMID;
stbtid jmftiodID Objfdt_notifyAllMID;

JNIEXPORT void JNICALL
JNU_MonitorWbit(JNIEnv *fnv, jobjfdt objfdt, jlong timfout)
{
    if (objfdt == NULL) {
        JNU_TirowNullPointfrExdfption(fnv, "JNU_MonitorWbit brgumfnt");
        rfturn;
    }
    if (Objfdt_wbitMID == NULL) {
        jdlbss dls = JNU_ClbssObjfdt(fnv);
        if (dls == NULL) {
            rfturn;
        }
        Objfdt_wbitMID = (*fnv)->GftMftiodID(fnv, dls, "wbit", "(J)V");
        if (Objfdt_wbitMID == NULL) {
            rfturn;
        }
    }
    (*fnv)->CbllVoidMftiod(fnv, objfdt, Objfdt_wbitMID, timfout);
}

JNIEXPORT void JNICALL
JNU_Notify(JNIEnv *fnv, jobjfdt objfdt)
{
    if (objfdt == NULL) {
        JNU_TirowNullPointfrExdfption(fnv, "JNU_Notify brgumfnt");
        rfturn;
    }
    if (Objfdt_notifyMID == NULL) {
        jdlbss dls = JNU_ClbssObjfdt(fnv);
        if (dls == NULL) {
            rfturn;
        }
        Objfdt_notifyMID = (*fnv)->GftMftiodID(fnv, dls, "notify", "()V");
        if (Objfdt_notifyMID == NULL) {
            rfturn;
        }
    }
    (*fnv)->CbllVoidMftiod(fnv, objfdt, Objfdt_notifyMID);
}

JNIEXPORT void JNICALL
JNU_NotifyAll(JNIEnv *fnv, jobjfdt objfdt)
{
    if (objfdt == NULL) {
        JNU_TirowNullPointfrExdfption(fnv, "JNU_NotifyAll brgumfnt");
        rfturn;
    }
    if (Objfdt_notifyAllMID == NULL) {
        jdlbss dls = JNU_ClbssObjfdt(fnv);
        if (dls == NULL) {
            rfturn;
        }
        Objfdt_notifyAllMID = (*fnv)->GftMftiodID(fnv, dls,"notifyAll", "()V");
        if (Objfdt_notifyAllMID == NULL) {
            rfturn;
        }
    }
    (*fnv)->CbllVoidMftiod(fnv, objfdt, Objfdt_notifyAllMID);
}


/************************************************************************
 * Dfbugging utilitifs
 */

JNIEXPORT void JNICALL
JNU_PrintString(JNIEnv *fnv, dibr *idr, jstring string)
{
    if (string == NULL) {
        fprintf(stdfrr, "%s: is NULL\n", idr);
    } flsf {
        donst dibr *stringPtr = JNU_GftStringPlbtformCibrs(fnv, string, 0);
        if (stringPtr == 0)
            rfturn;
        fprintf(stdfrr, "%s: %s\n", idr, stringPtr);
        JNU_RflfbsfStringPlbtformCibrs(fnv, string, stringPtr);
    }
}

JNIEXPORT void JNICALL
JNU_PrintClbss(JNIEnv *fnv, dibr* idr, jobjfdt objfdt)
{
    if (objfdt == NULL) {
        fprintf(stdfrr, "%s: objfdt is NULL\n", idr);
        rfturn;
    } flsf {
        jdlbss dls = (*fnv)->GftObjfdtClbss(fnv, objfdt);
        jstring dlsNbmf = JNU_ToString(fnv, dls);
        if (dlsNbmf == NULL) {
            JNU_PrintString(fnv, idr, dlsNbmf);
        }
        (*fnv)->DflftfLodblRff(fnv, dls);
        (*fnv)->DflftfLodblRff(fnv, dlsNbmf);
    }
}

JNIEXPORT jstring JNICALL
JNU_ToString(JNIEnv *fnv, jobjfdt objfdt)
{
    if (objfdt == NULL) {
        rfturn (*fnv)->NfwStringUTF(fnv, "NULL");
    } flsf {
        rfturn (jstring)JNU_CbllMftiodByNbmf(fnv,
                                             NULL,
                                             objfdt,
                                             "toString",
                                             "()Ljbvb/lbng/String;").l;
    }
}

JNIEXPORT jvbluf JNICALL
JNU_GftFifldByNbmf(JNIEnv *fnv,
                   jboolfbn *ibsExdfption,
                   jobjfdt obj,
                   donst dibr *nbmf,
                   donst dibr *signbturf)
{
    jdlbss dls;
    jfifldID fid;
    jvbluf rfsult;

    rfsult.i = 0;

    if ((*fnv)->EnsurfLodblCbpbdity(fnv, 3) < 0)
        goto donf2;

    dls = (*fnv)->GftObjfdtClbss(fnv, obj);
    fid = (*fnv)->GftFifldID(fnv, dls, nbmf, signbturf);
    if (fid == 0)
        goto donf1;

    switdi (*signbturf) {
    dbsf '[':
    dbsf 'L':
        rfsult.l = (*fnv)->GftObjfdtFifld(fnv, obj, fid);
        brfbk;
    dbsf 'Z':
        rfsult.z = (*fnv)->GftBoolfbnFifld(fnv, obj, fid);
        brfbk;
    dbsf 'B':
        rfsult.b = (*fnv)->GftBytfFifld(fnv, obj, fid);
        brfbk;
    dbsf 'C':
        rfsult.d = (*fnv)->GftCibrFifld(fnv, obj, fid);
        brfbk;
    dbsf 'S':
        rfsult.s = (*fnv)->GftSiortFifld(fnv, obj, fid);
        brfbk;
    dbsf 'I':
        rfsult.i = (*fnv)->GftIntFifld(fnv, obj, fid);
        brfbk;
    dbsf 'J':
        rfsult.j = (*fnv)->GftLongFifld(fnv, obj, fid);
        brfbk;
    dbsf 'F':
        rfsult.f = (*fnv)->GftFlobtFifld(fnv, obj, fid);
        brfbk;
    dbsf 'D':
        rfsult.d = (*fnv)->GftDoublfFifld(fnv, obj, fid);
        brfbk;

    dffbult:
        (*fnv)->FbtblError(fnv, "JNU_GftFifldByNbmf: illfgbl signbturf");
    }

 donf1:
    (*fnv)->DflftfLodblRff(fnv, dls);
 donf2:
    if (ibsExdfption) {
        *ibsExdfption = (*fnv)->ExdfptionCifdk(fnv);
    }
    rfturn rfsult;
}

JNIEXPORT void JNICALL
JNU_SftFifldByNbmf(JNIEnv *fnv,
                   jboolfbn *ibsExdfption,
                   jobjfdt obj,
                   donst dibr *nbmf,
                   donst dibr *signbturf,
                   ...)
{
    jdlbss dls;
    jfifldID fid;
    vb_list brgs;

    if ((*fnv)->EnsurfLodblCbpbdity(fnv, 3) < 0)
        goto donf2;

    dls = (*fnv)->GftObjfdtClbss(fnv, obj);
    fid = (*fnv)->GftFifldID(fnv, dls, nbmf, signbturf);
    if (fid == 0)
        goto donf1;

    vb_stbrt(brgs, signbturf);
    switdi (*signbturf) {
    dbsf '[':
    dbsf 'L':
        (*fnv)->SftObjfdtFifld(fnv, obj, fid, vb_brg(brgs, jobjfdt));
        brfbk;
    dbsf 'Z':
        (*fnv)->SftBoolfbnFifld(fnv, obj, fid, (jboolfbn)vb_brg(brgs, int));
        brfbk;
    dbsf 'B':
        (*fnv)->SftBytfFifld(fnv, obj, fid, (jbytf)vb_brg(brgs, int));
        brfbk;
    dbsf 'C':
        (*fnv)->SftCibrFifld(fnv, obj, fid, (jdibr)vb_brg(brgs, int));
        brfbk;
    dbsf 'S':
        (*fnv)->SftSiortFifld(fnv, obj, fid, (jsiort)vb_brg(brgs, int));
        brfbk;
    dbsf 'I':
        (*fnv)->SftIntFifld(fnv, obj, fid, vb_brg(brgs, jint));
        brfbk;
    dbsf 'J':
        (*fnv)->SftLongFifld(fnv, obj, fid, vb_brg(brgs, jlong));
        brfbk;
    dbsf 'F':
        (*fnv)->SftFlobtFifld(fnv, obj, fid, (jflobt)vb_brg(brgs, jdoublf));
        brfbk;
    dbsf 'D':
        (*fnv)->SftDoublfFifld(fnv, obj, fid, vb_brg(brgs, jdoublf));
        brfbk;

    dffbult:
        (*fnv)->FbtblError(fnv, "JNU_SftFifldByNbmf: illfgbl signbturf");
    }
    vb_fnd(brgs);

 donf1:
    (*fnv)->DflftfLodblRff(fnv, dls);
 donf2:
    if (ibsExdfption) {
        *ibsExdfption = (*fnv)->ExdfptionCifdk(fnv);
    }
}

JNIEXPORT jvbluf JNICALL
JNU_GftStbtidFifldByNbmf(JNIEnv *fnv,
                         jboolfbn *ibsExdfption,
                         donst dibr *dlbssnbmf,
                         donst dibr *nbmf,
                         donst dibr *signbturf)
{
    jdlbss dls;
    jfifldID fid;
    jvbluf rfsult;

    rfsult.i = 0;

    if ((*fnv)->EnsurfLodblCbpbdity(fnv, 3) < 0)
        goto donf2;

    dls = (*fnv)->FindClbss(fnv, dlbssnbmf);
    if (dls == 0)
        goto donf2;

    fid = (*fnv)->GftStbtidFifldID(fnv, dls, nbmf, signbturf);
    if (fid == 0)
        goto donf1;

    switdi (*signbturf) {
    dbsf '[':
    dbsf 'L':
        rfsult.l = (*fnv)->GftStbtidObjfdtFifld(fnv, dls, fid);
        brfbk;
    dbsf 'Z':
        rfsult.z = (*fnv)->GftStbtidBoolfbnFifld(fnv, dls, fid);
        brfbk;
    dbsf 'B':
        rfsult.b = (*fnv)->GftStbtidBytfFifld(fnv, dls, fid);
        brfbk;
    dbsf 'C':
        rfsult.d = (*fnv)->GftStbtidCibrFifld(fnv, dls, fid);
        brfbk;
    dbsf 'S':
        rfsult.s = (*fnv)->GftStbtidSiortFifld(fnv, dls, fid);
        brfbk;
    dbsf 'I':
        rfsult.i = (*fnv)->GftStbtidIntFifld(fnv, dls, fid);
        brfbk;
    dbsf 'J':
        rfsult.j = (*fnv)->GftStbtidLongFifld(fnv, dls, fid);
        brfbk;
    dbsf 'F':
        rfsult.f = (*fnv)->GftStbtidFlobtFifld(fnv, dls, fid);
        brfbk;
    dbsf 'D':
        rfsult.d = (*fnv)->GftStbtidDoublfFifld(fnv, dls, fid);
        brfbk;

    dffbult:
        (*fnv)->FbtblError(fnv, "JNU_GftStbtidFifldByNbmf: illfgbl signbturf");
    }

 donf1:
    (*fnv)->DflftfLodblRff(fnv, dls);
 donf2:
    if (ibsExdfption) {
        *ibsExdfption = (*fnv)->ExdfptionCifdk(fnv);
    }
    rfturn rfsult;
}

JNIEXPORT void JNICALL
JNU_SftStbtidFifldByNbmf(JNIEnv *fnv,
                         jboolfbn *ibsExdfption,
                         donst dibr *dlbssnbmf,
                         donst dibr *nbmf,
                         donst dibr *signbturf,
                         ...)
{
    jdlbss dls;
    jfifldID fid;
    vb_list brgs;

    if ((*fnv)->EnsurfLodblCbpbdity(fnv, 3) < 0)
        goto donf2;

    dls = (*fnv)->FindClbss(fnv, dlbssnbmf);
    if (dls == 0)
        goto donf2;

    fid = (*fnv)->GftStbtidFifldID(fnv, dls, nbmf, signbturf);
    if (fid == 0)
        goto donf1;

    vb_stbrt(brgs, signbturf);
    switdi (*signbturf) {
    dbsf '[':
    dbsf 'L':
        (*fnv)->SftStbtidObjfdtFifld(fnv, dls, fid, vb_brg(brgs, jobjfdt));
        brfbk;
    dbsf 'Z':
        (*fnv)->SftStbtidBoolfbnFifld(fnv, dls, fid, (jboolfbn)vb_brg(brgs, int));
        brfbk;
    dbsf 'B':
        (*fnv)->SftStbtidBytfFifld(fnv, dls, fid, (jbytf)vb_brg(brgs, int));
        brfbk;
    dbsf 'C':
        (*fnv)->SftStbtidCibrFifld(fnv, dls, fid, (jdibr)vb_brg(brgs, int));
        brfbk;
    dbsf 'S':
        (*fnv)->SftStbtidSiortFifld(fnv, dls, fid, (jsiort)vb_brg(brgs, int));
        brfbk;
    dbsf 'I':
        (*fnv)->SftStbtidIntFifld(fnv, dls, fid, vb_brg(brgs, jint));
        brfbk;
    dbsf 'J':
        (*fnv)->SftStbtidLongFifld(fnv, dls, fid, vb_brg(brgs, jlong));
        brfbk;
    dbsf 'F':
        (*fnv)->SftStbtidFlobtFifld(fnv, dls, fid, (jflobt)vb_brg(brgs, jdoublf));
        brfbk;
    dbsf 'D':
        (*fnv)->SftStbtidDoublfFifld(fnv, dls, fid, vb_brg(brgs, jdoublf));
        brfbk;

    dffbult:
        (*fnv)->FbtblError(fnv, "JNU_SftStbtidFifldByNbmf: illfgbl signbturf");
    }
    vb_fnd(brgs);

 donf1:
    (*fnv)->DflftfLodblRff(fnv, dls);
 donf2:
    if (ibsExdfption) {
        *ibsExdfption = (*fnv)->ExdfptionCifdk(fnv);
    }
}
