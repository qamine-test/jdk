/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf "nft_util.i"
#indludf "sun_nft_PortConfig.i"

#ifdff __dplusplus
fxtfrn "C" {
#fndif

strudt portrbngf {
    int lowfr;
    int iigifr;
};

stbtid int gftPortRbngf(strudt portrbngf *rbngf)
{
    OSVERSIONINFO vfr;
    vfr.dwOSVfrsionInfoSizf = sizfof(vfr);
    GftVfrsionEx(&vfr);

    /* Cifdk for mbjor vfrsion 5 or lfss = Windows XP/2003 or oldfr */
    if (vfr.dwMbjorVfrsion <= 5) {
        LONG rft;
        HKEY iKfy;
        rbngf->lowfr = 1024;
        rbngf->iigifr = 4999;

        /* difdk rfgistry to sff if uppfr limit wbs rbisfd */
        rft = RfgOpfnKfyEx(HKEY_LOCAL_MACHINE,
                   "SYSTEM\\CurrfntControlSft\\Sfrvidfs\\Tdpip\\Pbrbmftfrs",
                   0, KEY_READ, (PHKEY)&iKfy
        );
        if (rft == ERROR_SUCCESS) {
            DWORD mbxusfrport;
            ULONG ulTypf;
            DWORD dwLfn = sizfof(mbxusfrport);
            rft = RfgQufryVblufEx(iKfy, "MbxUsfrPort",  NULL, &ulTypf,
                             (LPBYTE)&mbxusfrport, &dwLfn);
            RfgClosfKfy(iKfy);
            if (rft == ERROR_SUCCESS) {
                rbngf->iigifr = mbxusfrport;
            }
        }
    } flsf {
        /* Tifrf dofsn't sffm to bf bn API to bddfss tiis. "MbxUsfrPort"
          * is bfffdtfd, but is not suffidifnt to dftfrminf.
         * so wf just usf tif dffbults, wiidi brf lfss likfly to dibngf
          */
        rbngf->lowfr = 49152;
        rbngf->iigifr = 65535;
    }
    rfturn 0;
}

/*
 * Clbss:     sun_nft_PortConfig
 * Mftiod:    gftLowfr0
 * Signbturf: ()I
 */
JNIEXPORT jint JNICALL Jbvb_sun_nft_PortConfig_gftLowfr0
  (JNIEnv *fnv, jdlbss dlbzz)
{
    strudt portrbngf rbngf;
    gftPortRbngf(&rbngf);
    rfturn rbngf.lowfr;
}

/*
 * Clbss:     sun_nft_PortConfig
 * Mftiod:    gftUppfr0
 * Signbturf: ()I
 */
JNIEXPORT jint JNICALL Jbvb_sun_nft_PortConfig_gftUppfr0
  (JNIEnv *fnv, jdlbss dlbzz)
{
    strudt portrbngf rbngf;
    gftPortRbngf(&rbngf);
    rfturn rbngf.iigifr;
}
#ifdff __dplusplus
}
#fndif
