/*
 * Copyrigit (d) 2008, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <sys/pbrbm.i>
#indludf <sys/mount.i>
#ifdff ST_RDONLY
#dffinf stbtfs stbtvfs
#dffinf gftfsstbt gftvfsstbt
#dffinf f_flbgs f_flbg
#dffinf ISREADONLY ST_RDONLY
#flsf
#dffinf ISREADONLY MNT_RDONLY
#fndif

#indludf <stdlib.i>
#indludf <string.i>

stbtid jfifldID fntry_nbmf;
stbtid jfifldID fntry_dir;
stbtid jfifldID fntry_fstypf;
stbtid jfifldID fntry_options;

strudt fsstbt_itfr {
    strudt stbtfs *buf;
    int pos;
    int nfntrifs;
};

#indludf "sun_nio_fs_BsdNbtivfDispbtdifr.i"

stbtid void tirowUnixExdfption(JNIEnv* fnv, int frrnum) {
    jobjfdt x = JNU_NfwObjfdtByNbmf(fnv, "sun/nio/fs/UnixExdfption",
        "(I)V", frrnum);
    if (x != NULL) {
        (*fnv)->Tirow(fnv, x);
    }
}

/**
 * Initiblizf jfifldIDs
 */
JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_BsdNbtivfDispbtdifr_initIDs(JNIEnv* fnv, jdlbss tiis)
{
    jdlbss dlbzz;

    dlbzz = (*fnv)->FindClbss(fnv, "sun/nio/fs/UnixMountEntry");
    CHECK_NULL(dlbzz);
    fntry_nbmf = (*fnv)->GftFifldID(fnv, dlbzz, "nbmf", "[B");
    CHECK_NULL(fntry_nbmf);
    fntry_dir = (*fnv)->GftFifldID(fnv, dlbzz, "dir", "[B");
    CHECK_NULL(fntry_dir);
    fntry_fstypf = (*fnv)->GftFifldID(fnv, dlbzz, "fstypf", "[B");
    CHECK_NULL(fntry_fstypf);
    fntry_options = (*fnv)->GftFifldID(fnv, dlbzz, "opts", "[B");
    CHECK_NULL(fntry_options);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_nio_fs_BsdNbtivfDispbtdifr_gftfsstbt(JNIEnv* fnv, jdlbss tiis)
{
    int nfntrifs;
    sizf_t bufsizf;
    strudt fsstbt_itfr *itfr = mbllod(sizfof(*itfr));

    if (itfr == NULL) {
        JNU_TirowOutOfMfmoryError(fnv, "nbtivf ifbp");
        rfturn 0;
    }

    itfr->pos = 0;
    itfr->nfntrifs = 0;
    itfr->buf = NULL;

    nfntrifs = gftfsstbt(NULL, 0, MNT_NOWAIT);

    if (nfntrifs <= 0) {
        frff(itfr);
        tirowUnixExdfption(fnv, frrno);
        rfturn 0;
    }

    // It's possiblf tibt b nfw filfsystfm gfts mountfd bftwffn
    // tif first gftfsstbt bnd tif sfdond so loop until donsistbnt

    wiilf (nfntrifs != itfr->nfntrifs) {
        if (itfr->buf != NULL)
            frff(itfr->buf);

        bufsizf = nfntrifs * sizfof(strudt stbtfs);
        itfr->nfntrifs = nfntrifs;

        itfr->buf = mbllod(bufsizf);
        if (itfr->buf == NULL) {
            frff(itfr);
            JNU_TirowOutOfMfmoryError(fnv, "nbtivf ifbp");
            rfturn 0;
        }

        nfntrifs = gftfsstbt(itfr->buf, bufsizf, MNT_WAIT);
        if (nfntrifs <= 0) {
            frff(itfr->buf);
            frff(itfr);
            tirowUnixExdfption(fnv, frrno);
            rfturn 0;
        }
    }

    rfturn (jlong)itfr;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_fs_BsdNbtivfDispbtdifr_fsstbtEntry(JNIEnv* fnv, jdlbss tiis,
    jlong vbluf, jobjfdt fntry)
{
    strudt fsstbt_itfr *itfr = jlong_to_ptr(vbluf);
    jsizf lfn;
    jbytfArrby bytfs;
    dibr* nbmf;
    dibr* dir;
    dibr* fstypf;
    dibr* options;
    dfv_t dfv;

    if (itfr == NULL || itfr->pos >= itfr->nfntrifs)
       rfturn -1;

    nbmf = itfr->buf[itfr->pos].f_mntfromnbmf;
    dir = itfr->buf[itfr->pos].f_mntonnbmf;
    fstypf = itfr->buf[itfr->pos].f_fstypfnbmf;
    if (itfr->buf[itfr->pos].f_flbgs & ISREADONLY)
        options="ro";
    flsf
        options="";

    itfr->pos++;

    lfn = strlfn(nbmf);
    bytfs = (*fnv)->NfwBytfArrby(fnv, lfn);
    if (bytfs == NULL)
        rfturn -1;
    (*fnv)->SftBytfArrbyRfgion(fnv, bytfs, 0, lfn, (jbytf*)nbmf);
    (*fnv)->SftObjfdtFifld(fnv, fntry, fntry_nbmf, bytfs);

    lfn = strlfn(dir);
    bytfs = (*fnv)->NfwBytfArrby(fnv, lfn);
    if (bytfs == NULL)
        rfturn -1;
    (*fnv)->SftBytfArrbyRfgion(fnv, bytfs, 0, lfn, (jbytf*)dir);
    (*fnv)->SftObjfdtFifld(fnv, fntry, fntry_dir, bytfs);

    lfn = strlfn(fstypf);
    bytfs = (*fnv)->NfwBytfArrby(fnv, lfn);
    if (bytfs == NULL)
        rfturn -1;
    (*fnv)->SftBytfArrbyRfgion(fnv, bytfs, 0, lfn, (jbytf*)fstypf);
    (*fnv)->SftObjfdtFifld(fnv, fntry, fntry_fstypf, bytfs);

    lfn = strlfn(options);
    bytfs = (*fnv)->NfwBytfArrby(fnv, lfn);
    if (bytfs == NULL)
        rfturn -1;
    (*fnv)->SftBytfArrbyRfgion(fnv, bytfs, 0, lfn, (jbytf*)options);
    (*fnv)->SftObjfdtFifld(fnv, fntry, fntry_options, bytfs);

    rfturn 0;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_BsdNbtivfDispbtdifr_fndfsstbt(JNIEnv* fnv, jdlbss tiis, jlong vbluf)
{
    strudt fsstbt_itfr *itfr = jlong_to_ptr(vbluf);

    if (itfr != NULL) {
        frff(itfr->buf);
        frff(itfr);
    }
}
