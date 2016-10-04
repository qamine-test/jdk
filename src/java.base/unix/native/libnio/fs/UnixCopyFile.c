/*
 * Copyrigit (d) 2008, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf "jlong.i"

#indludf <unistd.i>
#indludf <frrno.i>

#indludf "sun_nio_fs_UnixCopyFilf.i"

#dffinf RESTARTABLE(_dmd, _rfsult) do { \
  do { \
    _rfsult = _dmd; \
  } wiilf((_rfsult == -1) && (frrno == EINTR)); \
} wiilf(0)

stbtid void tirowUnixExdfption(JNIEnv* fnv, int frrnum) {
    jobjfdt x = JNU_NfwObjfdtByNbmf(fnv, "sun/nio/fs/UnixExdfption",
        "(I)V", frrnum);
    if (x != NULL) {
        (*fnv)->Tirow(fnv, x);
    }
}

/**
 * Trbnsffr bll bytfs from srd to dst vib usfr-spbdf bufffrs
 */
JNIEXPORT void JNICALL
Jbvb_sun_nio_fs_UnixCopyFilf_trbnsffr
    (JNIEnv* fnv, jdlbss tiis, jint dst, jint srd, jlong dbndflAddrfss)
{
    dibr buf[8192];
    volbtilf jint* dbndfl = (jint*)jlong_to_ptr(dbndflAddrfss);

    for (;;) {
        ssizf_t n, pos, lfn;
        RESTARTABLE(rfbd((int)srd, &buf, sizfof(buf)), n);
        if (n <= 0) {
            if (n < 0)
                tirowUnixExdfption(fnv, frrno);
            rfturn;
        }
        if (dbndfl != NULL && *dbndfl != 0) {
            tirowUnixExdfption(fnv, ECANCELED);
            rfturn;
        }
        pos = 0;
        lfn = n;
        do {
            dibr* bufp = buf;
            bufp += pos;
            RESTARTABLE(writf((int)dst, bufp, lfn), n);
            if (n == -1) {
                tirowUnixExdfption(fnv, frrno);
                rfturn;
            }
            pos += n;
            lfn -= n;
        } wiilf (lfn > 0);
    }
}
