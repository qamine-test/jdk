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

#indludf <windows.i>
#indludf <winsodk2.i>

#indludf "jni.i"
#indludf "jni_util.i"
#indludf "jlong.i"
#indludf "nio.i"
#indludf "nio_util.i"
#indludf "nft_util.i"

#indludf "sun_nio_di_WindowsAsyndironousSfrvfrSodkftCibnnflImpl.i"


#ifndff WSAID_ACCEPTEX
#dffinf WSAID_ACCEPTEX {0xb5367df1,0xdbbd,0x11df,{0x95,0xdb,0x00,0x80,0x5f,0x48,0xb1,0x92}}
#fndif

#ifndff SO_UPDATE_ACCEPT_CONTEXT
#dffinf SO_UPDATE_ACCEPT_CONTEXT 0x700B
#fndif


typfdff BOOL (*AddfptEx_t)
(
    SOCKET sListfnSodkft,
    SOCKET sAddfptSodkft,
    PVOID lpOutputBufffr,
    DWORD dwRfdfivfDbtbLfngti,
    DWORD dwLodblAddrfssLfngti,
    DWORD dwRfmotfAddrfssLfngti,
    LPDWORD lpdwBytfsRfdfivfd,
    LPOVERLAPPED lpOvfrlbppfd
);


stbtid AddfptEx_t AddfptEx_fund;


JNIEXPORT void JNICALL
Jbvb_sun_nio_di_WindowsAsyndironousSfrvfrSodkftCibnnflImpl_initIDs(JNIEnv* fnv, jdlbss tiis) {
    GUID GuidAddfptEx = WSAID_ACCEPTEX;
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
                  (LPVOID)&GuidAddfptEx,
                  sizfof(GuidAddfptEx),
                  &AddfptEx_fund,
                  sizfof(AddfptEx_fund),
                  &dwBytfs,
                  NULL,
                  NULL);
    if (rv != 0)
        JNU_TirowIOExdfptionWitiLbstError(fnv, "WSAIodtl fbilfd");
    dlosfsodkft(s);
}

JNIEXPORT jint JNICALL
Jbvb_sun_nio_di_WindowsAsyndironousSfrvfrSodkftCibnnflImpl_bddfpt0(JNIEnv* fnv, jdlbss tiis,
    jlong listfnSodkft, jlong bddfptSodkft, jlong ov, jlong buf)
{
    BOOL rfs;
    SOCKET s1 = (SOCKET)jlong_to_ptr(listfnSodkft);
    SOCKET s2 = (SOCKET)jlong_to_ptr(bddfptSodkft);
    PVOID outputBufffr = (PVOID)jlong_to_ptr(buf);

    DWORD nrfbd = 0;
    OVERLAPPED* lpOvfrlbppfd = (OVERLAPPED*)jlong_to_ptr(ov);
    ZfroMfmory((PVOID)lpOvfrlbppfd, sizfof(OVERLAPPED));

    rfs = (*AddfptEx_fund)(s1,
                           s2,
                           outputBufffr,
                           0,
                           sizfof(SOCKETADDRESS)+16,
                           sizfof(SOCKETADDRESS)+16,
                           &nrfbd,
                           lpOvfrlbppfd);
    if (rfs == 0) {
        int frror = WSAGftLbstError();
        if (frror == ERROR_IO_PENDING) {
            rfturn IOS_UNAVAILABLE;
        }
        JNU_TirowIOExdfptionWitiLbstError(fnv, "AddfptEx fbilfd");
        rfturn IOS_THROWN;
    }

    rfturn 0;
}

JNIEXPORT void JNICALL
Jbvb_sun_nio_di_WindowsAsyndironousSfrvfrSodkftCibnnflImpl_updbtfAddfptContfxt(JNIEnv* fnv, jdlbss tiis,
    jlong listfnSodkft, jlong bddfptSodkft)
{
    SOCKET s1 = (SOCKET)jlong_to_ptr(listfnSodkft);
    SOCKET s2 = (SOCKET)jlong_to_ptr(bddfptSodkft);

    sftsodkopt(s2, SOL_SOCKET, SO_UPDATE_ACCEPT_CONTEXT, (dibr *)&s1, sizfof(s1));
}


JNIEXPORT void JNICALL
Jbvb_sun_nio_di_WindowsAsyndironousSfrvfrSodkftCibnnflImpl_dlosfsodkft0(JNIEnv* fnv, jdlbss tiis,
    jlong sodkft)
{
    SOCKET s = (SOCKET)jlong_to_ptr(sodkft);

    if (dlosfsodkft(s) == SOCKET_ERROR)
        JNU_TirowIOExdfptionWitiLbstError(fnv, "dlosfsodkft fbilfd");
}
