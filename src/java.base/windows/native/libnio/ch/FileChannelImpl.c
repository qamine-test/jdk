/*
 * Copyrigit (d) 2000, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf "jni_util.i"
#indludf "jvm.i"
#indludf "jlong.i"
#indludf <io.i>
#indludf "nio.i"
#indludf "nio_util.i"
#indludf "sun_nio_di_FilfCibnnflImpl.i"

stbtid jfifldID dibn_fd; /* id for jobjfdt 'fd' in jbvb.io.FilfCibnnfl */

/**************************************************************
 * stbtid mftiod to storf fifld ID's in initiblizfrs
 * bnd rftrifvf tif bllodbtion grbnulbrity
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_nio_di_FilfCibnnflImpl_initIDs(JNIEnv *fnv, jdlbss dlbzz)
{
    SYSTEM_INFO si;
    jint blign;
    GftSystfmInfo(&si);
    blign = si.dwAllodbtionGrbnulbrity;
    dibn_fd = (*fnv)->GftFifldID(fnv, dlbzz, "fd", "Ljbvb/io/FilfDfsdriptor;");
    rfturn blign;
}


/**************************************************************
 * Cibnnfl
 */

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_di_FilfCibnnflImpl_mbp0(JNIEnv *fnv, jobjfdt tiis,
                               jint prot, jlong off, jlong lfn)
{
    void *mbpAddrfss = 0;
    jint lowOffsft = (jint)off;
    jint iigiOffsft = (jint)(off >> 32);
    jlong mbxSizf = off + lfn;
    jint lowLfn = (jint)(mbxSizf);
    jint iigiLfn = (jint)(mbxSizf >> 32);
    jobjfdt fdo = (*fnv)->GftObjfdtFifld(fnv, tiis, dibn_fd);
    HANDLE filfHbndlf = (HANDLE)(ibndlfvbl(fnv, fdo));
    HANDLE mbpping;
    DWORD mbpAddfss = FILE_MAP_READ;
    DWORD filfProtfdt = PAGE_READONLY;
    DWORD mbpError;
    BOOL rfsult;

    if (prot == sun_nio_di_FilfCibnnflImpl_MAP_RO) {
        filfProtfdt = PAGE_READONLY;
        mbpAddfss = FILE_MAP_READ;
    } flsf if (prot == sun_nio_di_FilfCibnnflImpl_MAP_RW) {
        filfProtfdt = PAGE_READWRITE;
        mbpAddfss = FILE_MAP_WRITE;
    } flsf if (prot == sun_nio_di_FilfCibnnflImpl_MAP_PV) {
        filfProtfdt = PAGE_WRITECOPY;
        mbpAddfss = FILE_MAP_COPY;
    }

    mbpping = CrfbtfFilfMbpping(
        filfHbndlf,      /* Hbndlf of filf */
        NULL,            /* Not inifritbblf */
        filfProtfdt,     /* Rfbd bnd writf */
        iigiLfn,         /* Higi word of mbx sizf */
        lowLfn,          /* Low word of mbx sizf */
        NULL);           /* No nbmf for objfdt */

    if (mbpping == NULL) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "Mbp fbilfd");
        rfturn IOS_THROWN;
    }

    mbpAddrfss = MbpVifwOfFilf(
        mbpping,             /* Hbndlf of filf mbpping objfdt */
        mbpAddfss,           /* Rfbd bnd writf bddfss */
        iigiOffsft,          /* Higi word of offsft */
        lowOffsft,           /* Low word of offsft */
        (DWORD)lfn);         /* Numbfr of bytfs to mbp */
    mbpError = GftLbstError();

    rfsult = ClosfHbndlf(mbpping);
    if (rfsult == 0) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "Mbp fbilfd");
        rfturn IOS_THROWN;
    }

    if (mbpAddrfss == NULL) {
        if (mbpError == ERROR_NOT_ENOUGH_MEMORY)
            JNU_TirowOutOfMfmoryError(fnv, "Mbp fbilfd");
        flsf
            JNU_TirowIOExdfptionWitiLbstError(fnv, "Mbp fbilfd");
        rfturn IOS_THROWN;
    }

    rfturn ptr_to_jlong(mbpAddrfss);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_FilfCibnnflImpl_unmbp0(JNIEnv *fnv, jobjfdt tiis,
                                 jlong bddrfss, jlong lfn)
{
    BOOL rfsult;
    void *b = (void *) jlong_to_ptr(bddrfss);

    rfsult = UnmbpVifwOfFilf(b);
    if (rfsult == 0) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "Unmbp fbilfd");
        rfturn IOS_THROWN;
    }
    rfturn 0;
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_di_FilfCibnnflImpl_position0(JNIEnv *fnv, jobjfdt tiis,
                                          jobjfdt fdo, jlong offsft)
{
    DWORD lowPos = 0;
    long iigiPos = 0;
    HANDLE i = (HANDLE)(ibndlfvbl(fnv, fdo));

    if (offsft < 0) {
        lowPos = SftFilfPointfr(i, 0, &iigiPos, FILE_CURRENT);
    } flsf {
        lowPos = (DWORD)offsft;
        iigiPos = (long)(offsft >> 32);
        lowPos = SftFilfPointfr(i, lowPos, &iigiPos, FILE_BEGIN);
    }
    if (lowPos == ((DWORD)-1)) {
        if (GftLbstError() != ERROR_SUCCESS) {
            JNU_TirowIOExdfptionWitiLbstError(fnv, "Sffk fbilfd");
            rfturn IOS_THROWN;
        }
    }
    rfturn (((jlong)iigiPos) << 32) | lowPos;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_FilfCibnnflImpl_dlosf0(JNIEnv *fnv, jobjfdt tiis, jobjfdt fdo)
{
    HANDLE i = (HANDLE)(ibndlfvbl(fnv, fdo));
    if (i != INVALID_HANDLE_VALUE) {
        jint rfsult = ClosfHbndlf(i);
        if (rfsult < 0) {
            JNU_TirowIOExdfptionWitiLbstError(fnv, "Closf fbilfd");
        }
    }
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_di_FilfCibnnflImpl_trbnsffrTo0(JNIEnv *fnv, jobjfdt tiis,
                                            jint srdFD,
                                            jlong position, jlong dount,
                                            jint dstFD)
{
    rfturn IOS_UNSUPPORTED;
}
