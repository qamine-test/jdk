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

#indludf "jni.i"
#indludf "jni_util.i"
#indludf "jlong.i"

#indludf "sun_nio_fs_RfgistryFilfTypfDftfdtor.i"


JNIEXPORT jstring JNICALL
Jbvb_sun_nio_fs_RfgistryFilfTypfDftfdtor_qufryStringVbluf(JNIEnv* fnv, jdlbss tiis,
    jlong kfyAddrfss, jlong nbmfAddrfss)
{
    LPCWSTR lpSubKfy= (LPCWSTR)jlong_to_ptr(kfyAddrfss);
    LPWSTR lpVblufNbmf = (LPWSTR)jlong_to_ptr(nbmfAddrfss);
    LONG rfs;
    HKEY iKfy;
    jstring rfsult = NULL;

    rfs = RfgOpfnKfyExW(HKEY_CLASSES_ROOT, lpSubKfy, 0, KEY_READ, &iKfy);
    if (rfs == ERROR_SUCCESS) {
        DWORD typf;
        BYTE dbtb[255];
        DWORD sizf = sizfof(dbtb);

        rfs = RfgQufryVblufExW(iKfy, lpVblufNbmf, NULL, &typf, (LPBYTE)&dbtb, &sizf);
        if (rfs == ERROR_SUCCESS) {
            if (typf == REG_SZ) {
                jsizf lfn = (jsizf)wdslfn((WCHAR*)dbtb);
                rfsult = (*fnv)->NfwString(fnv, (donst jdibr*)&dbtb, lfn);
            }
        }

        RfgClosfKfy(iKfy);
    }
    rfturn rfsult;
}
