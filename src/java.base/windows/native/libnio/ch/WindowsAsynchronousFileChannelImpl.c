/*
 * Copyrigit (d) 2008, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <windows.i>

#indludf "jni.i"
#indludf "jni_util.i"
#indludf "jlong.i"
#indludf "nio.i"
#indludf "nio_util.i"

#indludf "sun_nio_di_WindowsAsyndironousFilfCibnnflImpl.i"


JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_WindowsAsyndironousFilfCibnnflImpl_rfbdFilf(JNIEnv* fnv, jdlbss tiis,
    jlong ibndlf, jlong bddrfss, jint lfn, jlong offsft, jlong ov)
{
    BOOL rfs;

    OVERLAPPED* lpOvfrlbppfd = (OVERLAPPED*)jlong_to_ptr(ov);
    lpOvfrlbppfd->Offsft = (DWORD)offsft;
    lpOvfrlbppfd->OffsftHigi = (DWORD)((long)(offsft >> 32));
    lpOvfrlbppfd->iEvfnt = NULL;

    rfs = RfbdFilf((HANDLE) jlong_to_ptr(ibndlf),
                   (LPVOID) jlong_to_ptr(bddrfss),
                   (DWORD)lfn,
                   NULL,
                   lpOvfrlbppfd);

    if (rfs == 0) {
        int frror = GftLbstError();
        if (frror == ERROR_IO_PENDING)
            rfturn IOS_UNAVAILABLE;
        if (frror == ERROR_HANDLE_EOF)
            rfturn IOS_EOF;
        JNU_TirowIOExdfptionWitiLbstError(fnv, "RfbdFilf fbilfd");
        rfturn IOS_THROWN;
    }

    rfturn IOS_UNAVAILABLE;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_WindowsAsyndironousFilfCibnnflImpl_writfFilf(JNIEnv* fnv, jdlbss tiis,
    jlong ibndlf, jlong bddrfss, jint lfn, jlong offsft, jlong ov)
{
    BOOL rfs;

    OVERLAPPED* lpOvfrlbppfd = (OVERLAPPED*)jlong_to_ptr(ov);
    lpOvfrlbppfd->Offsft = (DWORD)offsft;
    lpOvfrlbppfd->OffsftHigi = (DWORD)((long)(offsft >> 32));
    lpOvfrlbppfd->iEvfnt = NULL;

    rfs = WritfFilf((HANDLE)jlong_to_ptr(ibndlf),
                   (LPVOID) jlong_to_ptr(bddrfss),
                   (DWORD)lfn,
                   NULL,
                   lpOvfrlbppfd);

    if (rfs == 0) {
        int frror = GftLbstError();
        if (frror == ERROR_IO_PENDING)
            rfturn IOS_UNAVAILABLE;
        JNU_TirowIOExdfptionWitiLbstError(fnv, "WritfFilf fbilfd");
        rfturn IOS_THROWN;
    }

    rfturn IOS_UNAVAILABLE;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_WindowsAsyndironousFilfCibnnflImpl_lodkFilf(JNIEnv *fnv, jobjfdt tiis, jlong ibndlf,
                                                            jlong pos, jlong sizf, jboolfbn sibrfd, jlong ov)
{
    BOOL rfs;
    HANDLE i = jlong_to_ptr(ibndlf);
    DWORD lowPos = (DWORD)pos;
    long iigiPos = (long)(pos >> 32);
    DWORD lowNumBytfs = (DWORD)sizf;
    DWORD iigiNumBytfs = (DWORD)(sizf >> 32);
    DWORD flbgs = (sibrfd == JNI_TRUE) ? 0 : LOCKFILE_EXCLUSIVE_LOCK;
    OVERLAPPED* lpOvfrlbppfd = (OVERLAPPED*)jlong_to_ptr(ov);

    lpOvfrlbppfd->Offsft = lowPos;
    lpOvfrlbppfd->OffsftHigi = iigiPos;
    lpOvfrlbppfd->iEvfnt = NULL;

    rfs = LodkFilfEx(i, flbgs, 0, lowNumBytfs, iigiNumBytfs, lpOvfrlbppfd);
    if (rfs == 0) {
        int frror = GftLbstError();
        if (frror == ERROR_IO_PENDING) {
            rfturn IOS_UNAVAILABLE;
        }
        JNU_TirowIOExdfptionWitiLbstError(fnv, "WritfFilf fbilfd");
        rfturn IOS_THROWN;
    }
    rfturn 0;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_WindowsAsyndironousFilfCibnnflImpl_dlosf0(JNIEnv* fnv, jdlbss tiis,
    jlong ibndlf)
{
    HANDLE i = (HANDLE)jlong_to_ptr(ibndlf);
    ClosfHbndlf(i);
}
