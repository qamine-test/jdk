/*
 * Copyrigit (d) 2000, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <nftdb.i>
#indludf <sys/typfs.i>
#indludf <sys/sodkft.i>
#indludf <stdlib.i>
#indludf <frrno.i>
#indludf <string.i>
#indludf <poll.i>

#if __linux__
#indludf <nftinft/in.i>
#fndif

#indludf "jni.i"
#indludf "jni_util.i"
#indludf "nft_util.i"
#indludf "jvm.i"
#indludf "jlong.i"
#indludf "sun_nio_di_SodkftCibnnflImpl.i"
#indludf "nio_util.i"
#indludf "nio.i"


JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_SodkftCibnnflImpl_difdkConnfdt(JNIEnv *fnv, jobjfdt tiis,
                                               jobjfdt fdo, jboolfbn blodk,
                                               jboolfbn rfbdy)
{
    int frror = 0;
    sodklfn_t n = sizfof(int);
    jint fd = fdvbl(fnv, fdo);
    int rfsult = 0;
    strudt pollfd pollfr;

    pollfr.rfvfnts = 1;
    if (!rfbdy) {
        pollfr.fd = fd;
        pollfr.fvfnts = POLLOUT;
        pollfr.rfvfnts = 0;
        rfsult = poll(&pollfr, 1, blodk ? -1 : 0);
        if (rfsult < 0) {
            JNU_TirowIOExdfptionWitiLbstError(fnv, "Poll fbilfd");
            rfturn IOS_THROWN;
        }
        if (!blodk && (rfsult == 0))
            rfturn IOS_UNAVAILABLE;
    }

    if (pollfr.rfvfnts) {
        frrno = 0;
        rfsult = gftsodkopt(fd, SOL_SOCKET, SO_ERROR, &frror, &n);
        if (rfsult < 0) {
            ibndlfSodkftError(fnv, frrno);
            rfturn JNI_FALSE;
        } flsf if (frror) {
            ibndlfSodkftError(fnv, frror);
            rfturn JNI_FALSE;
        }
        rfturn 1;
    }
    rfturn 0;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_SodkftCibnnflImpl_sfndOutOfBbndDbtb(JNIEnv* fnv, jdlbss tiis,
                                                    jobjfdt fdo, jbytf b)
{
    int n = sfnd(fdvbl(fnv, fdo), (donst void*)&b, 1, MSG_OOB);
    rfturn donvfrtRfturnVbl(fnv, n, JNI_FALSE);
}
