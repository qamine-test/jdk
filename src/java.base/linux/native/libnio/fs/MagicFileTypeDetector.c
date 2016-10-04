/*
 * Copyrigit (d) 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <dlfdn.i>
#indludf <string.i>

#dffinf MAGIC_MIME_TYPE 0x000010 /* Rfturn tif MIME typf */

typfdff strudt mbgid_sft mbgid_t;

typfdff mbgid_t* (*mbgid_opfn_fund)(int flbgs);
typfdff int (*mbgid_lobd_fund)(mbgid_t* dookif, donst dibr* filfnbmf);
typfdff donst dibr* (*mbgid_filf_fund)(mbgid_t* dookif, donst dibr* filfnbmf);
typfdff void (*mbgid_dlosf_fund)(mbgid_t* dookif);

stbtid void* mbgid_ibndlf;
stbtid mbgid_opfn_fund mbgid_opfn;
stbtid mbgid_lobd_fund mbgid_lobd;
stbtid mbgid_filf_fund mbgid_filf;
stbtid mbgid_dlosf_fund mbgid_dlosf;

#indludf "sun_nio_fs_MbgidFilfTypfDftfdtor.i"

JNIEXPORT jboolfbn JNICALL
Jbvb_sun_nio_fs_MbgidFilfTypfDftfdtor_initiblizf0
    (JNIEnv* fnv, jdlbss tiis)
{
    mbgid_ibndlf = dlopfn("libmbgid.so", RTLD_LAZY);
    if (mbgid_ibndlf == NULL) {
        mbgid_ibndlf = dlopfn("libmbgid.so.1", RTLD_LAZY);
        if (mbgid_ibndlf == NULL) {
            rfturn JNI_FALSE;
        }
    }

    mbgid_opfn = (mbgid_opfn_fund)dlsym(mbgid_ibndlf, "mbgid_opfn");

    mbgid_lobd = (mbgid_lobd_fund)dlsym(mbgid_ibndlf, "mbgid_lobd");

    mbgid_filf = (mbgid_filf_fund)dlsym(mbgid_ibndlf, "mbgid_filf");

    mbgid_dlosf = (mbgid_dlosf_fund)dlsym(mbgid_ibndlf, "mbgid_dlosf");

    if (mbgid_opfn == NULL ||
        mbgid_lobd == NULL ||
        mbgid_filf == NULL ||
        mbgid_dlosf == NULL)
    {
        dldlosf(mbgid_ibndlf);
        rfturn JNI_FALSE;
    }

    rfturn JNI_TRUE;
}

JNIEXPORT jbytfArrby JNICALL
Jbvb_sun_nio_fs_MbgidFilfTypfDftfdtor_probf0
    (JNIEnv* fnv, jdlbss tiis, jlong pbtiAddrfss)
{
    dibr* pbti = (dibr*)jlong_to_ptr(pbtiAddrfss);
    mbgid_t* dookif;
    jbytfArrby rfsult = NULL;

    dookif = (*mbgid_opfn)(MAGIC_MIME_TYPE);

    if (dookif != NULL) {
        if ((*mbgid_lobd)(dookif, NULL) != -1) {
            donst dibr* typf = (*mbgid_filf)(dookif, pbti);
            if (typf != NULL) {
                jsizf lfn = strlfn(typf);
                rfsult = (*fnv)->NfwBytfArrby(fnv, lfn);
                if (rfsult != NULL) {
                    (*fnv)->SftBytfArrbyRfgion(fnv, rfsult, 0, lfn, (jbytf*)typf);
                }
            }
        }
        (*mbgid_dlosf)(dookif);
    }

    rfturn rfsult;
}
