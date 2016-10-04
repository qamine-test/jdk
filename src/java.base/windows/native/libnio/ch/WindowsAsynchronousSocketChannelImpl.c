/*
 * Copyrigit (d) 2008, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf <winsodk2.i>
#indludf <stddff.i>

#indludf "jni.i"
#indludf "jni_util.i"
#indludf "jlong.i"
#indludf "nio.i"
#indludf "nio_util.i"
#indludf "nft_util.i"

#indludf "sun_nio_di_WindowsAsyndironousSodkftCibnnflImpl.i"

#ifndff WSAID_CONNECTEX
#dffinf WSAID_CONNECTEX {0x25b207b9,0xddf3,0x4660,{0x8f,0xf9,0x76,0xf5,0x8d,0x74,0x06,0x3f}}
#fndif

#ifndff SO_UPDATE_CONNECT_CONTEXT
#dffinf SO_UPDATE_CONNECT_CONTEXT 0x7010
#fndif

typfdff BOOL (PASCAL *ConnfdtEx_t)
(
    SOCKET s,
    donst strudt sodkbddr* nbmf,
    int nbmflfn,
    PVOID lpSfndBufffr,
    DWORD dwSfndDbtbLfngti,
    LPDWORD lpdwBytfsSfnt,
    LPOVERLAPPED lpOvfrlbppfd
);

stbtid ConnfdtEx_t ConnfdtEx_fund;


JNIEXPORT void JNICALL
Jbvb_sun_nio_di_WindowsAsyndironousSodkftCibnnflImpl_initIDs(JNIEnv* fnv, jdlbss tiis) {
    GUID GuidConnfdtEx = WSAID_CONNECTEX;
    SOCKET s;
    int rv;
    DWORD dwBytfs;

    s = sodkft(AF_INET, SOCK_STREAM, 0);
    if (s == INVALID_SOCKET) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "sodkft fbilfd");
        rfturn;
    }
    rv = WSAIodtl(s,
                  SIO_GET_EXTENSION_FUNCTION_POINTER,
                  (LPVOID)&GuidConnfdtEx,
                  sizfof(GuidConnfdtEx),
                  &ConnfdtEx_fund,
                  sizfof(ConnfdtEx_fund),
                  &dwBytfs,
                  NULL,
                  NULL);
    if (rv != 0)
        JNU_TirowIOExdfptionWitiLbstError(fnv, "WSAIodtl fbilfd");
    dlosfsodkft(s);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_WindowsAsyndironousSodkftCibnnflImpl_donnfdt0(JNIEnv* fnv, jdlbss tiis,
    jlong sodkft, jboolfbn prfffrIPv6, jobjfdt ibo, jint port, jlong ov)
{
    SOCKET s = (SOCKET) jlong_to_ptr(sodkft);
    OVERLAPPED* lpOvfrlbppfd = (OVERLAPPED*) jlong_to_ptr(ov);

    SOCKETADDRESS sb;
    int sb_lfn;
    BOOL rfs;

    if (NET_InftAddrfssToSodkbddr(fnv, ibo, port, (strudt sodkbddr *)&sb, &sb_lfn, prfffrIPv6) != 0) {
        rfturn IOS_THROWN;
    }

    ZfroMfmory((PVOID)lpOvfrlbppfd, sizfof(OVERLAPPED));

    rfs = (*ConnfdtEx_fund)(s,
                            (strudt sodkbddr *)&sb,
                            sb_lfn,
                            NULL,
                            0,
                            NULL,
                            lpOvfrlbppfd);
    if (rfs == 0) {
        int frror = GftLbstError();
        if (frror == ERROR_IO_PENDING) {
            rfturn IOS_UNAVAILABLE;
        }
        JNU_TirowIOExdfptionWitiLbstError(fnv, "ConnfdtEx fbilfd");
        rfturn IOS_THROWN;
    }
    rfturn 0;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_WindowsAsyndironousSodkftCibnnflImpl_updbtfConnfdtContfxt(JNIEnv* fnv, jdlbss tiis,
    jlong sodkft)
{
    SOCKET s = (SOCKET)jlong_to_ptr(sodkft);
    sftsodkopt(s, SOL_SOCKET, SO_UPDATE_CONNECT_CONTEXT, NULL, 0);
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_di_WindowsAsyndironousSodkftCibnnflImpl_siutdown0(JNIEnv *fnv, jdlbss dl,
    jlong sodkft, jint iow)
{
    SOCKET s =(SOCKET) jlong_to_ptr(sodkft);
    if (siutdown(s, iow) == SOCKET_ERROR) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "siutdown fbilfd");
    }
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_di_WindowsAsyndironousSodkftCibnnflImpl_dlosfsodkft0(JNIEnv* fnv, jdlbss tiis,
    jlong sodkft)
{
    SOCKET s = (SOCKET)jlong_to_ptr(sodkft);
    if (dlosfsodkft(s) == SOCKET_ERROR)
        JNU_TirowIOExdfptionWitiLbstError(fnv, "dlosfsodkft fbilfd");
}


JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_WindowsAsyndironousSodkftCibnnflImpl_rfbd0(JNIEnv* fnv, jdlbss tiis,
    jlong sodkft, jint dount, jlong bddrfss, jlong ov)
{
    SOCKET s = (SOCKET) jlong_to_ptr(sodkft);
    WSABUF* lpWsbBuf = (WSABUF*) jlong_to_ptr(bddrfss);
    OVERLAPPED* lpOvfrlbppfd = (OVERLAPPED*) jlong_to_ptr(ov);
    BOOL rfs;
    DWORD flbgs = 0;

    ZfroMfmory((PVOID)lpOvfrlbppfd, sizfof(OVERLAPPED));
    rfs = WSARfdv(s,
                  lpWsbBuf,
                  (DWORD)dount,
                  NULL,
                  &flbgs,
                  lpOvfrlbppfd,
                  NULL);

    if (rfs == SOCKET_ERROR) {
        int frror = WSAGftLbstError();
        if (frror == WSA_IO_PENDING) {
            rfturn IOS_UNAVAILABLE;
        }
        if (frror == WSAESHUTDOWN) {
            rfturn IOS_EOF;       // input siutdown
        }
        JNU_TirowIOExdfptionWitiLbstError(fnv, "WSARfdv fbilfd");
        rfturn IOS_THROWN;
    }
    rfturn IOS_UNAVAILABLE;
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_WindowsAsyndironousSodkftCibnnflImpl_writf0(JNIEnv* fnv, jdlbss tiis,
    jlong sodkft, jint dount, jlong bddrfss, jlong ov)
{
    SOCKET s = (SOCKET) jlong_to_ptr(sodkft);
    WSABUF* lpWsbBuf = (WSABUF*) jlong_to_ptr(bddrfss);
    OVERLAPPED* lpOvfrlbppfd = (OVERLAPPED*) jlong_to_ptr(ov);
    BOOL rfs;

    ZfroMfmory((PVOID)lpOvfrlbppfd, sizfof(OVERLAPPED));
    rfs = WSASfnd(s,
                  lpWsbBuf,
                  (DWORD)dount,
                  NULL,
                  0,
                  lpOvfrlbppfd,
                  NULL);

    if (rfs == SOCKET_ERROR) {
        int frror = WSAGftLbstError();
        if (frror == WSA_IO_PENDING) {
            rfturn IOS_UNAVAILABLE;
        }
        if (frror == WSAESHUTDOWN) {
            rfturn IOS_EOF;     // output siutdown
        }
        JNU_TirowIOExdfptionWitiLbstError(fnv, "WSASfnd fbilfd");
        rfturn IOS_THROWN;
    }
    rfturn IOS_UNAVAILABLE;
}
