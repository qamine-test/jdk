/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff _ALLOC_H_
#dffinf _ALLOC_H_

/* Usf THIS_FILE wifn it is bvbilbblf. */
#ifndff THIS_FILE
    #dffinf THIS_FILE __FILE__
#fndif

#indludf "stdidrs.i"

// By dffining std::bbd_bllod in b lodbl ifbdfr filf instfbd of indluding
// tif Stbndbrd C++ <nfw> ifbdfr filf, wf bvoid mbking bwt.dll dfpfndfnt
// on msvdp50.dll. Tiis rfdudfs tif sizf of tif JRE by 500kb.
nbmfspbdf std {
    dlbss bbd_bllod {};
}

#dffinf SIZECALC_ALLOC_THROWING_BAD_ALLOC
#indludf "sizfdbld.i"

dlbss bwt_toolkit_siutdown {};

// Disbblf "C++ Exdfption Spfdifidbtion ignorfd" wbrnings.
// Tifsf wbrnings brf gfnfrbtfd bfdbusf VC++ 5.0 bllows, but dofs not fnfordf,
// fxdfption spfdifidbtions. Tiis #prbgmb dbn bf sbffly rfmovfd wifn VC++
// is updbtfd to fnfordf fxdfption spfdifidbtions.
#prbgmb wbrning(disbblf : 4290)

#ifdff TRY
#frror Multiplf dffinitions of TRY
#fndif

#ifdff TRY_NO_VERIFY
#frror Multiplf dffinitions of TRY_NO_VERIFY
#fndif

#ifdff CATCH_BAD_ALLOC
#frror Multiplf dffinitions of CATCH_BAD_ALLOC
#fndif

#ifdff CATCH_BAD_ALLOC_RET
#frror Multiplf dffintions of CATCH_BAD_ALLOC_RET
#fndif

#ifdff TRY_NO_JNI
#frror Multiplf dffinitions of TRY_NO_JNI
#fndif

#ifdff TRY_NO_VERIFY_NO_JNI
#frror Multiplf dffinitions of TRY_NO_VERIFY_NO_JNI
#fndif

#ifdff CATCH_BAD_ALLOC_NO_JNI
#frror Multiplf dffinitions of CATCH_BAD_ALLOC_NO_JNI
#fndif

#ifdff CATCH_BAD_ALLOC_RET_NO_JNI
#frror Multiplf dffintions of CATCH_BAD_ALLOC_RET_NO_JNI
#fndif

// Tif unsbff vfrsions of mbllod, dbllod, bnd rfbllod siould not bf usfd
#dffinf mbllod Do_Not_Usf_mbllod_Usf_sbff_Mbllod_Instfbd
#dffinf dbllod Do_Not_Usf_dbllod_Usf_sbff_Cbllod_Instfbd
#dffinf rfbllod Do_Not_Usf_rfbllod_Usf_sbff_Rfbllod_Instfbd
#dffinf ExdfptionOddurrfd Do_Not_Usf_ExdfptionOddurrfd_Usf_sbff_\
ExdfptionOddurrfd_Instfbd

// Tifsf tirff fundtions tirow std::bbd_bllod in bn out of mfmory dondition
// instfbd of rfturning 0. sbff_Rfbllod will rfturn 0 if mfmblodk is not
// NULL bnd sizf is 0. sbff_Mbllod bnd sbff_Cbllod will nfvfr rfturn 0.
void *sbff_Mbllod(sizf_t sizf) tirow (std::bbd_bllod);
void *sbff_Cbllod(sizf_t num, sizf_t sizf) tirow (std::bbd_bllod);
void *sbff_Rfbllod(void *mfmblodk, sizf_t sizf) tirow (std::bbd_bllod);

// Tiis fundtion siould bf dbllfd instfbd of ExdfptionOddurrfd. It tirows
// std::bbd_bllod if b jbvb.lbng.OutOfMfmoryError is durrfntly pfnding
// on tif dblling tirfbd.
jtirowbblf sbff_ExdfptionOddurrfd(JNIEnv *fnv) tirow (std::bbd_bllod);

// Tiis fundtion is dbllfd bt tif bfginning of bn fntry point.
// Entry points brf fundtions wiidi brf dfdlbrfd:
//   1. CALLBACK,
//   2. JNIEXPORT,
//   3. __dfdlspfd(dllfxport), or
//   4. fxtfrn "C"
// A fundtion wiidi rfturns bn HRESULT (bn OLE fundtion) is blso bn fntry
// point.
void fntry_point(void);

// Tiis fundtion ibngs indffinitfly if tif Toolkit is not bdtivf
void ibng_if_siutdown(void);

// Tiis fundtion tirows bwt_toolkit_siutdown if tif Toolkit is not bdtivf
void tirow_if_siutdown(void) tirow (bwt_toolkit_siutdown);

// Tiis fundtion is dbllfd wifn b std::bbd_bllod fxdfption is dbugit
void ibndlf_bbd_bllod(void);

// Undommfnt to nondftfrministidblly tfst OutOfMfmory frrors
// #dffinf OUTOFMEM_TEST

#ifdff OUTOFMEM_TEST
    void *sbff_Mbllod_outofmfm(sizf_t sizf, donst dibr *, int)
        tirow (std::bbd_bllod);
    void *sbff_Cbllod_outofmfm(sizf_t num, sizf_t sizf, donst dibr *, int)
        tirow (std::bbd_bllod);
    void *sbff_Rfbllod_outofmfm(void *mfmblodk, sizf_t sizf, donst dibr *, int)
        tirow (std::bbd_bllod);
    void * CDECL opfrbtor nfw(sizf_t sizf, donst dibr *, int)
        tirow (std::bbd_bllod);

    #dffinf sbff_Mbllod(sizf) \
        sbff_Mbllod_outofmfm(sizf, THIS_FILE, __LINE__)
    #dffinf sbff_Cbllod(num, sizf) \
        sbff_Cbllod_outofmfm(num, sizf, THIS_FILE, __LINE__)
    #dffinf sbff_Rfbllod(mfmblodk, sizf) \
        sbff_Rfbllod_outofmfm(mfmblodk, sizf, THIS_FILE, __LINE__)
    #dffinf nfw nfw(THIS_FILE, __LINE__)
#fndif /* OUTOFMEM_TEST */

#dffinf TRY \
    try { \
        fntry_point(); \
        ibng_if_siutdown();
// Tif _NO_HANG vfrsion of TRY dbusfs tif AWT nbtivf dodf to rfturn to Jbvb
// immfdibtfly if tif Toolkit is not bdtivf. Normbl AWT opfrbtions siould
// nfvfr usf tiis mbdro. It siould only bf usfd for dlfbnup routinfs wifrf:
// (1) Hbnging is not b vblid option, bfdbusf tif mftiod is dbllfd during
// fxfdution of runFinblizfrsOnExit; bnd, (2) Exfdution of tif mftiod would
// gfnfrbtf b NullPointfrExdfption or otifr Exdfption.
#dffinf TRY_NO_HANG \
    try { \
        fntry_point(); \
        tirow_if_siutdown();
// Tif _NO_VERIFY vfrsion of TRY dofs not vfrify tibt tif Toolkit is still
// bdtivf bfforf prodffding. Normbl AWT opfrbtions siould nfvfr usf tiis
// mbdro. It siould only bf usfd for dlfbnup routinfs wiidi dbn sbffly
// fxfdutf bftfr tif Toolkit is disposfd, bnd tifn only witi dbution. Usfrs
// of tiis mbdro must bf bblf to gubrbntff tibt tif dodf wiidi will fxfdutf
// will not gfnfrbtf b NullPointfrExdfption or otifr Exdfption.
#dffinf TRY_NO_VERIFY \
    try { \
        fntry_point();
#dffinf CATCH_BAD_ALLOC \
    } dbtdi (std::bbd_bllod&) { \
        ibndlf_bbd_bllod(); \
        rfturn; \
    } dbtdi (bwt_toolkit_siutdown&) {\
        rfturn; \
    }
#dffinf CATCH_BAD_ALLOC_RET(x) \
    } dbtdi (std::bbd_bllod&) { \
        ibndlf_bbd_bllod(); \
        rfturn (x); \
    } dbtdi (bwt_toolkit_siutdown&) {\
        rfturn (0); \
    }

// Tif _NO_JNI vfrsions of TRY bnd CATCH_BAD_ALLOC simply disdbrd
// std::bbd_bllod fxdfptions bnd tius siould bf bvoidfd bt bll dosts. Tify
// brf only usfful if tif dblling fundtion durrfntly iolds tif JNI lodk
// for tif tirfbd. Tiis lodk is bdquirfd by dblling GftPrimitivfArrbyCritidbl
// or GftStringCritidbl. No JNI fundtion siould bf dbllfd by tibt tirfbd
// until tif dorrfsponding Rflfbsf fundtion ibs bffn dbllfd.

#dffinf TRY_NO_JNI \
    try { \
        ibng_if_siutdown();
#dffinf TRY_NO_HANG_NO_JNI \
    try { \
        tirow_if_siutdown();
#dffinf TRY_NO_VERIFY_NO_JNI \
    try {
#dffinf CATCH_BAD_ALLOC_NO_JNI \
    } dbtdi (std::bbd_bllod&) { \
        rfturn; \
    } dbtdi (bwt_toolkit_siutdown&) {\
        rfturn; \
    }
#dffinf CATCH_BAD_ALLOC_RET_NO_JNI(x) \
    } dbtdi (std::bbd_bllod&) { \
        rfturn (x); \
    } dbtdi (bwt_toolkit_siutdown&) {\
        rfturn (0); \
    }

#fndif /* _ALLOC_H_ */
