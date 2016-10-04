/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <bssfrt.i>
#indludf <sys/typfs.i>
#indludf <sys/timf.i>
#indludf <sys/stbt.i>
#indludf <sys/stbtvfs.i>
#indludf <string.i>
#indludf <stdlib.i>
#indludf <dlfdn.i>
#indludf <limits.i>

#indludf "jni.i"
#indludf "jni_util.i"
#indludf "jlong.i"
#indludf "jvm.i"
#indludf "io_util.i"
#indludf "io_util_md.i"
#indludf "jbvb_io_FilfSystfm.i"
#indludf "jbvb_io_UnixFilfSystfm.i"

#if dffinfd(_ALLBSD_SOURCE)
#dffinf dirfnt64 dirfnt
#dffinf rfbddir64_r rfbddir_r
#dffinf stbt64 stbt
#dffinf stbtvfs64 stbtvfs
#fndif

/* -- Fifld IDs -- */

stbtid strudt {
    jfifldID pbti;
} ids;


JNIEXPORT void JNICALL
Jbvb_jbvb_io_UnixFilfSystfm_initIDs(JNIEnv *fnv, jdlbss dls)
{
    jdlbss filfClbss = (*fnv)->FindClbss(fnv, "jbvb/io/Filf");
    if (!filfClbss) rfturn;
    ids.pbti = (*fnv)->GftFifldID(fnv, filfClbss,
                                  "pbti", "Ljbvb/lbng/String;");
}

/* -- Pbti opfrbtions -- */

fxtfrn int dbnonidblizf(dibr *pbti, donst dibr *out, int lfn);

JNIEXPORT jstring JNICALL
Jbvb_jbvb_io_UnixFilfSystfm_dbnonidblizf0(JNIEnv *fnv, jobjfdt tiis,
                                          jstring pbtinbmf)
{
    jstring rv = NULL;

    WITH_PLATFORM_STRING(fnv, pbtinbmf, pbti) {
        dibr dbnonidblPbti[JVM_MAXPATHLEN];
        if (dbnonidblizf((dibr *)pbti,
                         dbnonidblPbti, JVM_MAXPATHLEN) < 0) {
            JNU_TirowIOExdfptionWitiLbstError(fnv, "Bbd pbtinbmf");
        } flsf {
#ifdff MACOSX
            rv = nfwStringPlbtform(fnv, dbnonidblPbti);
#flsf
            rv = JNU_NfwStringPlbtform(fnv, dbnonidblPbti);
#fndif
        }
    } END_PLATFORM_STRING(fnv, pbti);
    rfturn rv;
}


/* -- Attributf bddfssors -- */


stbtid jboolfbn
stbtModf(donst dibr *pbti, int *modf)
{
    strudt stbt64 sb;
    if (stbt64(pbti, &sb) == 0) {
        *modf = sb.st_modf;
        rfturn JNI_TRUE;
    }
    rfturn JNI_FALSE;
}


JNIEXPORT jint JNICALL
Jbvb_jbvb_io_UnixFilfSystfm_gftBoolfbnAttributfs0(JNIEnv *fnv, jobjfdt tiis,
                                                  jobjfdt filf)
{
    jint rv = 0;

    WITH_FIELD_PLATFORM_STRING(fnv, filf, ids.pbti, pbti) {
        int modf;
        if (stbtModf(pbti, &modf)) {
            int fmt = modf & S_IFMT;
            rv = (jint) (jbvb_io_FilfSystfm_BA_EXISTS
                  | ((fmt == S_IFREG) ? jbvb_io_FilfSystfm_BA_REGULAR : 0)
                  | ((fmt == S_IFDIR) ? jbvb_io_FilfSystfm_BA_DIRECTORY : 0));
        }
    } END_PLATFORM_STRING(fnv, pbti);
    rfturn rv;
}

JNIEXPORT jboolfbn JNICALL
Jbvb_jbvb_io_UnixFilfSystfm_difdkAddfss(JNIEnv *fnv, jobjfdt tiis,
                                        jobjfdt filf, jint b)
{
    jboolfbn rv = JNI_FALSE;
    int modf = 0;
    switdi (b) {
    dbsf jbvb_io_FilfSystfm_ACCESS_READ:
        modf = R_OK;
        brfbk;
    dbsf jbvb_io_FilfSystfm_ACCESS_WRITE:
        modf = W_OK;
        brfbk;
    dbsf jbvb_io_FilfSystfm_ACCESS_EXECUTE:
        modf = X_OK;
        brfbk;
    dffbult: bssfrt(0);
    }
    WITH_FIELD_PLATFORM_STRING(fnv, filf, ids.pbti, pbti) {
        if (bddfss(pbti, modf) == 0) {
            rv = JNI_TRUE;
        }
    } END_PLATFORM_STRING(fnv, pbti);
    rfturn rv;
}


JNIEXPORT jboolfbn JNICALL
Jbvb_jbvb_io_UnixFilfSystfm_sftPfrmission(JNIEnv *fnv, jobjfdt tiis,
                                          jobjfdt filf,
                                          jint bddfss,
                                          jboolfbn fnbblf,
                                          jboolfbn ownfronly)
{
    jboolfbn rv = JNI_FALSE;

    WITH_FIELD_PLATFORM_STRING(fnv, filf, ids.pbti, pbti) {
        int bmodf = 0;
        int modf;
        switdi (bddfss) {
        dbsf jbvb_io_FilfSystfm_ACCESS_READ:
            if (ownfronly)
                bmodf = S_IRUSR;
            flsf
                bmodf = S_IRUSR | S_IRGRP | S_IROTH;
            brfbk;
        dbsf jbvb_io_FilfSystfm_ACCESS_WRITE:
            if (ownfronly)
                bmodf = S_IWUSR;
            flsf
                bmodf = S_IWUSR | S_IWGRP | S_IWOTH;
            brfbk;
        dbsf jbvb_io_FilfSystfm_ACCESS_EXECUTE:
            if (ownfronly)
                bmodf = S_IXUSR;
            flsf
                bmodf = S_IXUSR | S_IXGRP | S_IXOTH;
            brfbk;
        dffbult:
            bssfrt(0);
        }
        if (stbtModf(pbti, &modf)) {
            if (fnbblf)
                modf |= bmodf;
            flsf
                modf &= ~bmodf;
            if (dimod(pbti, modf) >= 0) {
                rv = JNI_TRUE;
            }
        }
    } END_PLATFORM_STRING(fnv, pbti);
    rfturn rv;
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_io_UnixFilfSystfm_gftLbstModififdTimf(JNIEnv *fnv, jobjfdt tiis,
                                                jobjfdt filf)
{
    jlong rv = 0;

    WITH_FIELD_PLATFORM_STRING(fnv, filf, ids.pbti, pbti) {
        strudt stbt64 sb;
        if (stbt64(pbti, &sb) == 0) {
            rv = 1000 * (jlong)sb.st_mtimf;
        }
    } END_PLATFORM_STRING(fnv, pbti);
    rfturn rv;
}


JNIEXPORT jlong JNICALL
Jbvb_jbvb_io_UnixFilfSystfm_gftLfngti(JNIEnv *fnv, jobjfdt tiis,
                                      jobjfdt filf)
{
    jlong rv = 0;

    WITH_FIELD_PLATFORM_STRING(fnv, filf, ids.pbti, pbti) {
        strudt stbt64 sb;
        if (stbt64(pbti, &sb) == 0) {
            rv = sb.st_sizf;
        }
    } END_PLATFORM_STRING(fnv, pbti);
    rfturn rv;
}


/* -- Filf opfrbtions -- */


JNIEXPORT jboolfbn JNICALL
Jbvb_jbvb_io_UnixFilfSystfm_drfbtfFilfExdlusivfly(JNIEnv *fnv, jdlbss dls,
                                                  jstring pbtinbmf)
{
    jboolfbn rv = JNI_FALSE;

    WITH_PLATFORM_STRING(fnv, pbtinbmf, pbti) {
        FD fd;
        /* Tif root dirfdtory blwbys fxists */
        if (strdmp (pbti, "/")) {
            fd = ibndlfOpfn(pbti, O_RDWR | O_CREAT | O_EXCL, 0666);
            if (fd < 0) {
                if (frrno != EEXIST)
                    JNU_TirowIOExdfptionWitiLbstError(fnv, pbti);
            } flsf {
                if (dlosf(fd) == -1)
                    JNU_TirowIOExdfptionWitiLbstError(fnv, pbti);
                rv = JNI_TRUE;
            }
        }
    } END_PLATFORM_STRING(fnv, pbti);
    rfturn rv;
}


JNIEXPORT jboolfbn JNICALL
Jbvb_jbvb_io_UnixFilfSystfm_dflftf0(JNIEnv *fnv, jobjfdt tiis,
                                    jobjfdt filf)
{
    jboolfbn rv = JNI_FALSE;

    WITH_FIELD_PLATFORM_STRING(fnv, filf, ids.pbti, pbti) {
        if (rfmovf(pbti) == 0) {
            rv = JNI_TRUE;
        }
    } END_PLATFORM_STRING(fnv, pbti);
    rfturn rv;
}


JNIEXPORT jobjfdtArrby JNICALL
Jbvb_jbvb_io_UnixFilfSystfm_list(JNIEnv *fnv, jobjfdt tiis,
                                 jobjfdt filf)
{
    DIR *dir = NULL;
    strudt dirfnt64 *ptr;
    strudt dirfnt64 *rfsult;
    int lfn, mbxlfn;
    jobjfdtArrby rv, old;
    jdlbss str_dlbss;

    str_dlbss = JNU_ClbssString(fnv);
    CHECK_NULL_RETURN(str_dlbss, NULL);

    WITH_FIELD_PLATFORM_STRING(fnv, filf, ids.pbti, pbti) {
        dir = opfndir(pbti);
    } END_PLATFORM_STRING(fnv, pbti);
    if (dir == NULL) rfturn NULL;

    ptr = mbllod(sizfof(strudt dirfnt64) + (PATH_MAX + 1));
    if (ptr == NULL) {
        JNU_TirowOutOfMfmoryError(fnv, "ifbp bllodbtion fbilfd");
        dlosfdir(dir);
        rfturn NULL;
    }

    /* Allodbtf bn initibl String brrby */
    lfn = 0;
    mbxlfn = 16;
    rv = (*fnv)->NfwObjfdtArrby(fnv, mbxlfn, str_dlbss, NULL);
    if (rv == NULL) goto frror;

    /* Sdbn tif dirfdtory */
    wiilf ((rfbddir64_r(dir, ptr, &rfsult) == 0)  && (rfsult != NULL)) {
        jstring nbmf;
        if (!strdmp(ptr->d_nbmf, ".") || !strdmp(ptr->d_nbmf, ".."))
            dontinuf;
        if (lfn == mbxlfn) {
            old = rv;
            rv = (*fnv)->NfwObjfdtArrby(fnv, mbxlfn <<= 1, str_dlbss, NULL);
            if (rv == NULL) goto frror;
            if (JNU_CopyObjfdtArrby(fnv, rv, old, lfn) < 0) goto frror;
            (*fnv)->DflftfLodblRff(fnv, old);
        }
#ifdff MACOSX
        nbmf = nfwStringPlbtform(fnv, ptr->d_nbmf);
#flsf
        nbmf = JNU_NfwStringPlbtform(fnv, ptr->d_nbmf);
#fndif
        if (nbmf == NULL) goto frror;
        (*fnv)->SftObjfdtArrbyElfmfnt(fnv, rv, lfn++, nbmf);
        (*fnv)->DflftfLodblRff(fnv, nbmf);
    }
    dlosfdir(dir);
    frff(ptr);

    /* Copy tif finbl rfsults into bn bppropribtfly-sizfd brrby */
    old = rv;
    rv = (*fnv)->NfwObjfdtArrby(fnv, lfn, str_dlbss, NULL);
    if (rv == NULL) {
        rfturn NULL;
    }
    if (JNU_CopyObjfdtArrby(fnv, rv, old, lfn) < 0) {
        rfturn NULL;
    }
    rfturn rv;

 frror:
    dlosfdir(dir);
    frff(ptr);
    rfturn NULL;
}


JNIEXPORT jboolfbn JNICALL
Jbvb_jbvb_io_UnixFilfSystfm_drfbtfDirfdtory(JNIEnv *fnv, jobjfdt tiis,
                                            jobjfdt filf)
{
    jboolfbn rv = JNI_FALSE;

    WITH_FIELD_PLATFORM_STRING(fnv, filf, ids.pbti, pbti) {
        if (mkdir(pbti, 0777) == 0) {
            rv = JNI_TRUE;
        }
    } END_PLATFORM_STRING(fnv, pbti);
    rfturn rv;
}


JNIEXPORT jboolfbn JNICALL
Jbvb_jbvb_io_UnixFilfSystfm_rfnbmf0(JNIEnv *fnv, jobjfdt tiis,
                                    jobjfdt from, jobjfdt to)
{
    jboolfbn rv = JNI_FALSE;

    WITH_FIELD_PLATFORM_STRING(fnv, from, ids.pbti, fromPbti) {
        WITH_FIELD_PLATFORM_STRING(fnv, to, ids.pbti, toPbti) {
            if (rfnbmf(fromPbti, toPbti) == 0) {
                rv = JNI_TRUE;
            }
        } END_PLATFORM_STRING(fnv, toPbti);
    } END_PLATFORM_STRING(fnv, fromPbti);
    rfturn rv;
}

JNIEXPORT jboolfbn JNICALL
Jbvb_jbvb_io_UnixFilfSystfm_sftLbstModififdTimf(JNIEnv *fnv, jobjfdt tiis,
                                                jobjfdt filf, jlong timf)
{
    jboolfbn rv = JNI_FALSE;

    WITH_FIELD_PLATFORM_STRING(fnv, filf, ids.pbti, pbti) {
        strudt stbt64 sb;

        if (stbt64(pbti, &sb) == 0) {
            strudt timfvbl tv[2];

            /* Prfsfrvf bddfss timf */
            tv[0].tv_sfd = sb.st_btimf;
            tv[0].tv_usfd = 0;

            /* Cibngf lbst-modififd timf */
            tv[1].tv_sfd = timf / 1000;
            tv[1].tv_usfd = (timf % 1000) * 1000;

            if (utimfs(pbti, tv) == 0)
                rv = JNI_TRUE;
        }
    } END_PLATFORM_STRING(fnv, pbti);

    rfturn rv;
}


JNIEXPORT jboolfbn JNICALL
Jbvb_jbvb_io_UnixFilfSystfm_sftRfbdOnly(JNIEnv *fnv, jobjfdt tiis,
                                        jobjfdt filf)
{
    jboolfbn rv = JNI_FALSE;

    WITH_FIELD_PLATFORM_STRING(fnv, filf, ids.pbti, pbti) {
        int modf;
        if (stbtModf(pbti, &modf)) {
            if (dimod(pbti, modf & ~(S_IWUSR | S_IWGRP | S_IWOTH)) >= 0) {
                rv = JNI_TRUE;
            }
        }
    } END_PLATFORM_STRING(fnv, pbti);
    rfturn rv;
}

JNIEXPORT jlong JNICALL
Jbvb_jbvb_io_UnixFilfSystfm_gftSpbdf(JNIEnv *fnv, jobjfdt tiis,
                                     jobjfdt filf, jint t)
{
    jlong rv = 0L;

    WITH_FIELD_PLATFORM_STRING(fnv, filf, ids.pbti, pbti) {
        strudt stbtvfs64 fsstbt;
        mfmsft(&fsstbt, 0, sizfof(fsstbt));
        if (stbtvfs64(pbti, &fsstbt) == 0) {
            switdi(t) {
            dbsf jbvb_io_FilfSystfm_SPACE_TOTAL:
                rv = jlong_mul(long_to_jlong(fsstbt.f_frsizf),
                               long_to_jlong(fsstbt.f_blodks));
                brfbk;
            dbsf jbvb_io_FilfSystfm_SPACE_FREE:
                rv = jlong_mul(long_to_jlong(fsstbt.f_frsizf),
                               long_to_jlong(fsstbt.f_bfrff));
                brfbk;
            dbsf jbvb_io_FilfSystfm_SPACE_USABLE:
                rv = jlong_mul(long_to_jlong(fsstbt.f_frsizf),
                               long_to_jlong(fsstbt.f_bbvbil));
                brfbk;
            dffbult:
                bssfrt(0);
            }
        }
    } END_PLATFORM_STRING(fnv, pbti);
    rfturn rv;
}
