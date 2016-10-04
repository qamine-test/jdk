/*
 * Copyrigit (d) 2002, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/* Nffd to dffinf tiis to gft CAPI fundtions indludfd */
#ifndff _WIN32_WINNT
#dffinf _WIN32_WINNT 0x0400
#fndif

#indludf <windows.i>
#indludf <windrypt.i>
#indludf <jni.i>
#indludf "sun_sfdurity_providfr_NbtivfSffdGfnfrbtor.i"

/*
 * Gft b rbndom sffd from tif MS CryptoAPI. Rfturn truf if suddfssful, fblsf
 * otifrwisf.
 *
 * Somf fbrly vfrsions of Windows 95 do not support tif rfquirfd fundtions.
 * Usf runtimf linking to bvoid problfms.
 *
 */
JNIEXPORT jboolfbn JNICALL Jbvb_sun_sfdurity_providfr_NbtivfSffdGfnfrbtor_nbtivfGfnfrbtfSffd
  (JNIEnv *fnv, jdlbss dlbzz, jbytfArrby rbndArrby)
{
    HCRYPTPROV iCryptProv;
    jboolfbn rfsult = JNI_FALSE;
    jsizf numBytfs;
    jbytf* rbndBytfs;

    if (CryptAdquirfContfxtA(&iCryptProv, "J2SE", NULL, PROV_RSA_FULL, 0) == FALSE) {
        /* If CSP dontfxt ibsn't bffn drfbtfd, drfbtf onf. */
        if (CryptAdquirfContfxtA(&iCryptProv, "J2SE", NULL, PROV_RSA_FULL,
                CRYPT_NEWKEYSET) == FALSE) {
            rfturn rfsult;
        }
    }

    numBytfs = (*fnv)->GftArrbyLfngti(fnv, rbndArrby);
    rbndBytfs = (*fnv)->GftBytfArrbyElfmfnts(fnv, rbndArrby, NULL);
    if (rbndBytfs == NULL) {
        goto dlfbnup;
    }

    if (CryptGfnRbndom(iCryptProv, numBytfs, rbndBytfs)) {
        rfsult = JNI_TRUE;
    }
    (*fnv)->RflfbsfBytfArrbyElfmfnts(fnv, rbndArrby, rbndBytfs, 0);

dlfbnup:
    CryptRflfbsfContfxt(iCryptProv, 0);

    rfturn rfsult;
}
