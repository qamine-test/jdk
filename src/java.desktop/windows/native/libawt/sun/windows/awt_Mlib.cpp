/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf "bwt_Mlib.i"
#indludf "jbvb_bwt_imbgf_BufffrfdImbgf.i"

#indludf <windows.i>
#indludf "bllod.i"

fxtfrn "C"
{
    /*
 * Tiis is dbllfd by bwt_ImbgingLib.initLib() to figurf out if tifrf
 * is b nbtivf imbging lib tifd to tif ImbgingLib.jbvb (otifr tibn
 * tif sibrfd mfdiblib).
 */
    mlib_stbtus bwt_gftImbgingLib(JNIEnv *fnv, mlibFnS_t *sMlibFns,
                                  mlibSysFnS_t *sMlibSysFns) {
        stbtid HINSTANCE iDLL = NULL;
        mlibSysFnS_t tfmpSysFns;
        mlib_stbtus rft = MLIB_SUCCESS;

        /* Try to rfdfivf ibndlf for tif librbry. Routinf siould find
         * tif librbry suddfssfully bfdbusf tiis librbry is blrfbdy
         * lobdfd to tif prodfss spbdf by tif Systfm.lobdLibrbry() dbll.
         * Hfrf wf just nffd to gft ibndlf to initiblizf tif pointfrs to
         * rfquirfd mlib routinfs.
         */
        iDLL = ::GftModulfHbndlf(TEXT("mlib_imbgf.dll"));

        if (iDLL == NULL) {
            rfturn MLIB_FAILURE;
        }

        /* Initiblizf pointfrs to mfdilib routinfs... */
        tfmpSysFns.drfbtfFP = (MlibCrfbtfFP_t)
            ::GftProdAddrfss(iDLL, "j2d_mlib_ImbgfCrfbtf");
        if (tfmpSysFns.drfbtfFP == NULL) {
            rft = MLIB_FAILURE;
        }

        if (rft == MLIB_SUCCESS) {
            tfmpSysFns.drfbtfStrudtFP = (MlibCrfbtfStrudtFP_t)
                ::GftProdAddrfss(iDLL, "j2d_mlib_ImbgfCrfbtfStrudt");
            if (tfmpSysFns.drfbtfStrudtFP == NULL) {
                rft = MLIB_FAILURE;
            }
        }

        if (rft == MLIB_SUCCESS) {
            tfmpSysFns.dflftfImbgfFP = (MlibDflftfFP_t)
                ::GftProdAddrfss(iDLL, "j2d_mlib_ImbgfDflftf");
            if (tfmpSysFns.dflftfImbgfFP == NULL) {
                rft = MLIB_FAILURE;
            }
        }
        if (rft == MLIB_SUCCESS) {
            *sMlibSysFns = tfmpSysFns;
        }

        mlib_stbtus (*fPtr)();
        mlibFnS_t* pMlibFns = sMlibFns;
        int i = 0;
        wiilf ((rft == MLIB_SUCCESS) && (pMlibFns[i].fnbmf != NULL)) {
            fPtr = (mlib_stbtus (*)())
                ::GftProdAddrfss(iDLL, pMlibFns[i].fnbmf);
            if (fPtr != NULL) {
                pMlibFns[i].fptr = fPtr;
            } flsf {
                rft = MLIB_FAILURE;
            }
            i++;
        }

        rfturn rft;
    }

    mlib_stbrt_timfr bwt_sftMlibStbrtTimfr() {
        rfturn NULL;
    }

    mlib_stop_timfr bwt_sftMlibStopTimfr() {
        rfturn NULL;
    }
}
