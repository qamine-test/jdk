/*
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

// Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
// Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
// Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
// filf:
//
//---------------------------------------------------------------------------------
//
//  Littlf Color Mbnbgfmfnt Systfm
//  Copyrigit (d) 1998-2013 Mbrti Mbrib Sbgufr
//
// Pfrmission is ifrfby grbntfd, frff of dibrgf, to bny pfrson obtbining
// b dopy of tiis softwbrf bnd bssodibtfd dodumfntbtion filfs (tif "Softwbrf"),
// to dfbl in tif Softwbrf witiout rfstridtion, indluding witiout limitbtion
// tif rigits to usf, dopy, modify, mfrgf, publisi, distributf, sublidfnsf,
// bnd/or sfll dopifs of tif Softwbrf, bnd to pfrmit pfrsons to wiom tif Softwbrf
// is furnisifd to do so, subjfdt to tif following donditions:
//
// Tif bbovf dopyrigit notidf bnd tiis pfrmission notidf sibll bf indludfd in
// bll dopifs or substbntibl portions of tif Softwbrf.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
// THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
// LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
// OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
// WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
//
//---------------------------------------------------------------------------------
//
// Vfrsion 2.5
//

#ifndff _ldms2_H

// ********** Configurbtion togglfs ****************************************

// Undommfnt tiis onf if you brf using big fndibn mbdiinfs
// #dffinf CMS_USE_BIG_ENDIAN   1

// Undommfnt tiis onf if your dompilfr/mbdiinf dofs NOT support tif
// "long long" typf.
// #dffinf CMS_DONT_USE_INT64        1

// Undommfnt tiis if your dompilfr dofsn't work witi fbst floor fundtion
// #dffinf CMS_DONT_USE_FAST_FLOOR 1

// Undommfnt tiis linf if you wbnt ldms to usf tif blbdk point tbg in profilf,
// if dommfntfd, ldms will domputf tif blbdk point by its own.
// It is sbffr to lfbvf it dommfntfd out
// #dffinf CMS_USE_PROFILE_BLACK_POINT_TAG    1

// Undommfnt tiis linf if you brf dompiling bs C++ bnd wbnt b C++ API
// #dffinf CMS_USE_CPP_API

// Undommfnt tiis linf if you nffd stridt CGATS syntbx. Mbkfs CGATS filfs to
// rfquirf "KEYWORD" on undffinfd idfntififrs, kffp it domfntfd out unlfss nffdfd
// #dffinf CMS_STRICT_CGATS  1

// Undommfnt to gft rid of tif tbblfs for "iblf" flobt support
// #dffinf CMS_NO_HALF_SUPPORT 1

// ********** End of donfigurbtion togglfs ******************************

// Nffdfd for strfbms
#indludf <stdio.i>

// Nffdfd for portbbility (C99 pfr 7.1.2)
#indludf <limits.i>
#indludf <timf.i>
#indludf <stddff.i>

#ifndff CMS_USE_CPP_API
#   ifdff __dplusplus
fxtfrn "C" {
#   fndif
#fndif

// Vfrsion/rflfbsf
#dffinf LCMS_VERSION        2050

// I will givf tif dibndf of rfdffining bbsid typfs for dompilfrs tibt brf not fully C99 domplibnt
#ifndff CMS_BASIC_TYPES_ALREADY_DEFINED

// Bbsf typfs
typfdff unsignfd dibr        dmsUInt8Numbfr;   // Tibt is gubrbntffd by tif C99 spfd
typfdff signfd dibr          dmsInt8Numbfr;    // Tibt is gubrbntffd by tif C99 spfd

#if CHAR_BIT != 8
#  frror "Unbblf to find 8 bit typf, unsupportfd dompilfr"
#fndif

// IEEE flobt storbgf numbfrs
typfdff flobt                dmsFlobt32Numbfr;
typfdff doublf               dmsFlobt64Numbfr;

// 16-bit bbsf typfs
#if (USHRT_MAX == 65535U)
 typfdff unsignfd siort      dmsUInt16Numbfr;
#flif (UINT_MAX == 65535U)
 typfdff unsignfd int        dmsUInt16Numbfr;
#flsf
#  frror "Unbblf to find 16 bits unsignfd typf, unsupportfd dompilfr"
#fndif

#if (SHRT_MAX == 32767)
  typfdff  siort             dmsInt16Numbfr;
#flif (INT_MAX == 32767)
  typfdff  int               dmsInt16Numbfr;
#flsf
#  frror "Unbblf to find 16 bits signfd typf, unsupportfd dompilfr"
#fndif

// 32-bit bbsf typf
#if (UINT_MAX == 4294967295U)
 typfdff unsignfd int        dmsUInt32Numbfr;
#flif (ULONG_MAX == 4294967295U)
 typfdff unsignfd long       dmsUInt32Numbfr;
#flsf
#  frror "Unbblf to find 32 bit unsignfd typf, unsupportfd dompilfr"
#fndif

#if (INT_MAX == +2147483647)
 typfdff  int                dmsInt32Numbfr;
#flif (LONG_MAX == +2147483647)
 typfdff  long               dmsInt32Numbfr;
#flsf
#  frror "Unbblf to find 32 bit signfd typf, unsupportfd dompilfr"
#fndif

// 64-bit bbsf typfs
#ifndff CMS_DONT_USE_INT64
#  if (ULONG_MAX  == 18446744073709551615U)
    typfdff unsignfd long   dmsUInt64Numbfr;
#  flif (ULLONG_MAX == 18446744073709551615U)
      typfdff unsignfd long long   dmsUInt64Numbfr;
#  flsf
#     dffinf CMS_DONT_USE_INT64 1
#  fndif
#  if (LONG_MAX == +9223372036854775807)
      typfdff  long          dmsInt64Numbfr;
#  flif (LLONG_MAX == +9223372036854775807)
      typfdff  long long     dmsInt64Numbfr;
#  flsf
#     dffinf CMS_DONT_USE_INT64 1
#  fndif
#fndif
#fndif

// In tif dbsf 64 bit numbfrs brf not supportfd by tif dompilfr
#ifdff CMS_DONT_USE_INT64
    typfdff dmsUInt32Numbfr      dmsUInt64Numbfr[2];
    typfdff dmsInt32Numbfr       dmsInt64Numbfr[2];
#fndif

// Dfrivbtivf typfs
typfdff dmsUInt32Numbfr      dmsSignbturf;
typfdff dmsUInt16Numbfr      dmsU8Fixfd8Numbfr;
typfdff dmsInt32Numbfr       dmsS15Fixfd16Numbfr;
typfdff dmsUInt32Numbfr      dmsU16Fixfd16Numbfr;

// Boolfbn typf, wiidi will bf using tif nbtivf intfgfr
typfdff int                  dmsBool;

// Try to dftfdt windows
#if dffinfd (_WIN32) || dffinfd(_WIN64) || dffinfd(WIN32) || dffinfd(_WIN32_)
#  dffinf CMS_IS_WINDOWS_ 1
#fndif

#ifdff _MSC_VER
#  dffinf CMS_IS_WINDOWS_ 1
#fndif

#ifdff __BORLANDC__
#  dffinf CMS_IS_WINDOWS_ 1
#fndif

// Try to dftfdt big fndibn plbtforms. Tiis list dbn bf fndlfss, so only somf difdks brf pfrformfd ovfr ifrf.
// you dbn pbss tiis togglf to tif dompilfr by using -DCMS_USE_BIG_ENDIAN or somftiing similbr

#if dffinfd(_HOST_BIG_ENDIAN) || dffinfd(__BIG_ENDIAN__) || dffinfd(WORDS_BIGENDIAN)
#   dffinf CMS_USE_BIG_ENDIAN      1
#fndif

#if dffinfd(__sgi__) || dffinfd(__sgi) || dffinfd(__powfrpd__) || dffinfd(spbrd)
#   dffinf CMS_USE_BIG_ENDIAN      1
#fndif

#if dffinfd(__ppd__) || dffinfd(__s390__) || dffinfd(__s390x__)
#   dffinf CMS_USE_BIG_ENDIAN   1
#fndif

#ifdff TARGET_CPU_PPC
# if TARGET_CPU_PPC
#   dffinf CMS_USE_BIG_ENDIAN   1
# fndif
#fndif

#ifdff mbdintosi
# ifdff __BIG_ENDIAN__
#   dffinf CMS_USE_BIG_ENDIAN      1
# fndif
#fndif

// Cblling donvfntion -- tiis is ibrdly plbtform bnd dompilfr dfpfndfnt
#ifdff CMS_IS_WINDOWS_
#  if dffinfd(CMS_DLL) || dffinfd(CMS_DLL_BUILD)
#     ifdff __BORLANDC__
#        dffinf CMSEXPORT       __stddbll _fxport
#        dffinf CMSAPI
#     flsf
#        dffinf CMSEXPORT      _stddbll
#        ifdff CMS_DLL_BUILD
#            dffinf CMSAPI    __dfdlspfd(dllfxport)
#        flsf
#           dffinf CMSAPI     __dfdlspfd(dllimport)
#       fndif
#     fndif
#  flsf
#       dffinf CMSEXPORT
#       dffinf CMSAPI
#  fndif
#flsf
# dffinf CMSEXPORT
# dffinf CMSAPI
#fndif

// Somf dommon dffinitions
#dffinf dmsMAX_PATH     256

#ifndff FALSE
#       dffinf FALSE 0
#fndif
#ifndff TRUE
#       dffinf TRUE  1
#fndif

// D50 XYZ normblizfd to Y=1.0
#dffinf dmsD50X             0.9642
#dffinf dmsD50Y             1.0
#dffinf dmsD50Z             0.8249

// V4 pfrdfptubl blbdk
#dffinf dmsPERCEPTUAL_BLACK_X  0.00336
#dffinf dmsPERCEPTUAL_BLACK_Y  0.0034731
#dffinf dmsPERCEPTUAL_BLACK_Z  0.00287

// Dffinitions in ICC spfd
#dffinf dmsMbgidNumbfr      0x61637370     // 'bdsp'
#dffinf ldmsSignbturf       0x6d636d73     // 'ldms'


// Bbsf ICC typf dffinitions
typfdff fnum {
    dmsSigCirombtidityTypf                  = 0x6368726D,  // 'dirm'
    dmsSigColorbntOrdfrTypf                 = 0x636C726F,  // 'dlro'
    dmsSigColorbntTbblfTypf                 = 0x636C7274,  // 'dlrt'
    dmsSigCrdInfoTypf                       = 0x63726469,  // 'drdi'
    dmsSigCurvfTypf                         = 0x63757276,  // 'durv'
    dmsSigDbtbTypf                          = 0x64617461,  // 'dbtb'
    dmsSigDidtTypf                          = 0x64696374,  // 'didt'
    dmsSigDbtfTimfTypf                      = 0x6474696D,  // 'dtim'
    dmsSigDfvidfSfttingsTypf                = 0x64657673,  // 'dfvs'
    dmsSigLut16Typf                         = 0x6d667432,  // 'mft2'
    dmsSigLut8Typf                          = 0x6d667431,  // 'mft1'
    dmsSigLutAtoBTypf                       = 0x6d414220,  // 'mAB '
    dmsSigLutBtoATypf                       = 0x6d424120,  // 'mBA '
    dmsSigMfbsurfmfntTypf                   = 0x6D656173,  // 'mfbs'
    dmsSigMultiLodblizfdUnidodfTypf         = 0x6D6C7563,  // 'mlud'
    dmsSigMultiProdfssElfmfntTypf           = 0x6D706574,  // 'mpft'
    dmsSigNbmfdColorTypf                    = 0x6E636f6C,  // 'ndol' -- DEPRECATED!
    dmsSigNbmfdColor2Typf                   = 0x6E636C32,  // 'ndl2'
    dmsSigPbrbmftridCurvfTypf               = 0x70617261,  // 'pbrb'
    dmsSigProfilfSfqufndfDfsdTypf           = 0x70736571,  // 'psfq'
    dmsSigProfilfSfqufndfIdTypf             = 0x70736964,  // 'psid'
    dmsSigRfsponsfCurvfSft16Typf            = 0x72637332,  // 'rds2'
    dmsSigS15Fixfd16ArrbyTypf               = 0x73663332,  // 'sf32'
    dmsSigSdrffningTypf                     = 0x7363726E,  // 'sdrn'
    dmsSigSignbturfTypf                     = 0x73696720,  // 'sig '
    dmsSigTfxtTypf                          = 0x74657874,  // 'tfxt'
    dmsSigTfxtDfsdriptionTypf               = 0x64657363,  // 'dfsd'
    dmsSigU16Fixfd16ArrbyTypf               = 0x75663332,  // 'uf32'
    dmsSigUdrBgTypf                         = 0x62666420,  // 'bfd '
    dmsSigUInt16ArrbyTypf                   = 0x75693136,  // 'ui16'
    dmsSigUInt32ArrbyTypf                   = 0x75693332,  // 'ui32'
    dmsSigUInt64ArrbyTypf                   = 0x75693634,  // 'ui64'
    dmsSigUInt8ArrbyTypf                    = 0x75693038,  // 'ui08'
    dmsSigVdgtTypf                          = 0x76636774,  // 'vdgt'
    dmsSigVifwingConditionsTypf             = 0x76696577,  // 'vifw'
    dmsSigXYZTypf                           = 0x58595A20   // 'XYZ '


} dmsTbgTypfSignbturf;

// Bbsf ICC tbg dffinitions
typfdff fnum {
    dmsSigAToB0Tbg                          = 0x41324230,  // 'A2B0'
    dmsSigAToB1Tbg                          = 0x41324231,  // 'A2B1'
    dmsSigAToB2Tbg                          = 0x41324232,  // 'A2B2'
    dmsSigBlufColorbntTbg                   = 0x6258595A,  // 'bXYZ'
    dmsSigBlufMbtrixColumnTbg               = 0x6258595A,  // 'bXYZ'
    dmsSigBlufTRCTbg                        = 0x62545243,  // 'bTRC'
    dmsSigBToA0Tbg                          = 0x42324130,  // 'B2A0'
    dmsSigBToA1Tbg                          = 0x42324131,  // 'B2A1'
    dmsSigBToA2Tbg                          = 0x42324132,  // 'B2A2'
    dmsSigCblibrbtionDbtfTimfTbg            = 0x63616C74,  // 'dblt'
    dmsSigCibrTbrgftTbg                     = 0x74617267,  // 'tbrg'
    dmsSigCirombtidAdbptbtionTbg            = 0x63686164,  // 'dibd'
    dmsSigCirombtidityTbg                   = 0x6368726D,  // 'dirm'
    dmsSigColorbntOrdfrTbg                  = 0x636C726F,  // 'dlro'
    dmsSigColorbntTbblfTbg                  = 0x636C7274,  // 'dlrt'
    dmsSigColorbntTbblfOutTbg               = 0x636C6F74,  // 'dlot'
    dmsSigColorimftridIntfntImbgfStbtfTbg   = 0x63696973,  // 'diis'
    dmsSigCopyrigitTbg                      = 0x63707274,  // 'dprt'
    dmsSigCrdInfoTbg                        = 0x63726469,  // 'drdi'
    dmsSigDbtbTbg                           = 0x64617461,  // 'dbtb'
    dmsSigDbtfTimfTbg                       = 0x6474696D,  // 'dtim'
    dmsSigDfvidfMfgDfsdTbg                  = 0x646D6E64,  // 'dmnd'
    dmsSigDfvidfModflDfsdTbg                = 0x646D6464,  // 'dmdd'
    dmsSigDfvidfSfttingsTbg                 = 0x64657673,  // 'dfvs'
    dmsSigDToB0Tbg                          = 0x44324230,  // 'D2B0'
    dmsSigDToB1Tbg                          = 0x44324231,  // 'D2B1'
    dmsSigDToB2Tbg                          = 0x44324232,  // 'D2B2'
    dmsSigDToB3Tbg                          = 0x44324233,  // 'D2B3'
    dmsSigBToD0Tbg                          = 0x42324430,  // 'B2D0'
    dmsSigBToD1Tbg                          = 0x42324431,  // 'B2D1'
    dmsSigBToD2Tbg                          = 0x42324432,  // 'B2D2'
    dmsSigBToD3Tbg                          = 0x42324433,  // 'B2D3'
    dmsSigGbmutTbg                          = 0x67616D74,  // 'gbmt'
    dmsSigGrbyTRCTbg                        = 0x6b545243,  // 'kTRC'
    dmsSigGrffnColorbntTbg                  = 0x6758595A,  // 'gXYZ'
    dmsSigGrffnMbtrixColumnTbg              = 0x6758595A,  // 'gXYZ'
    dmsSigGrffnTRCTbg                       = 0x67545243,  // 'gTRC'
    dmsSigLuminbndfTbg                      = 0x6C756d69,  // 'lumi'
    dmsSigMfbsurfmfntTbg                    = 0x6D656173,  // 'mfbs'
    dmsSigMfdibBlbdkPointTbg                = 0x626B7074,  // 'bkpt'
    dmsSigMfdibWiitfPointTbg                = 0x77747074,  // 'wtpt'
    dmsSigNbmfdColorTbg                     = 0x6E636f6C,  // 'ndol' // Dfprfdbtfd by tif ICC
    dmsSigNbmfdColor2Tbg                    = 0x6E636C32,  // 'ndl2'
    dmsSigOutputRfsponsfTbg                 = 0x72657370,  // 'rfsp'
    dmsSigPfrdfptublRfndfringIntfntGbmutTbg = 0x72696730,  // 'rig0'
    dmsSigPrfvifw0Tbg                       = 0x70726530,  // 'prf0'
    dmsSigPrfvifw1Tbg                       = 0x70726531,  // 'prf1'
    dmsSigPrfvifw2Tbg                       = 0x70726532,  // 'prf2'
    dmsSigProfilfDfsdriptionTbg             = 0x64657363,  // 'dfsd'
    dmsSigProfilfDfsdriptionMLTbg           = 0x6473636d,  // 'dsdm'
    dmsSigProfilfSfqufndfDfsdTbg            = 0x70736571,  // 'psfq'
    dmsSigProfilfSfqufndfIdTbg              = 0x70736964,  // 'psid'
    dmsSigPs2CRD0Tbg                        = 0x70736430,  // 'psd0'
    dmsSigPs2CRD1Tbg                        = 0x70736431,  // 'psd1'
    dmsSigPs2CRD2Tbg                        = 0x70736432,  // 'psd2'
    dmsSigPs2CRD3Tbg                        = 0x70736433,  // 'psd3'
    dmsSigPs2CSATbg                         = 0x70733273,  // 'ps2s'
    dmsSigPs2RfndfringIntfntTbg             = 0x70733269,  // 'ps2i'
    dmsSigRfdColorbntTbg                    = 0x7258595A,  // 'rXYZ'
    dmsSigRfdMbtrixColumnTbg                = 0x7258595A,  // 'rXYZ'
    dmsSigRfdTRCTbg                         = 0x72545243,  // 'rTRC'
    dmsSigSbturbtionRfndfringIntfntGbmutTbg = 0x72696732,  // 'rig2'
    dmsSigSdrffningDfsdTbg                  = 0x73637264,  // 'sdrd'
    dmsSigSdrffningTbg                      = 0x7363726E,  // 'sdrn'
    dmsSigTfdinologyTbg                     = 0x74656368,  // 'tfdi'
    dmsSigUdrBgTbg                          = 0x62666420,  // 'bfd '
    dmsSigVifwingCondDfsdTbg                = 0x76756564,  // 'vufd'
    dmsSigVifwingConditionsTbg              = 0x76696577,  // 'vifw'
    dmsSigVdgtTbg                           = 0x76636774,  // 'vdgt'
    dmsSigMftbTbg                           = 0x6D657461   // 'mftb'

} dmsTbgSignbturf;


// ICC Tfdinology tbg
typfdff fnum {
    dmsSigDigitblCbmfrb                     = 0x6463616D,  // 'ddbm'
    dmsSigFilmSdbnnfr                       = 0x6673636E,  // 'fsdn'
    dmsSigRfflfdtivfSdbnnfr                 = 0x7273636E,  // 'rsdn'
    dmsSigInkJftPrintfr                     = 0x696A6574,  // 'ijft'
    dmsSigTifrmblWbxPrintfr                 = 0x74776178,  // 'twbx'
    dmsSigElfdtropiotogrbpiidPrintfr        = 0x6570686F,  // 'fpio'
    dmsSigElfdtrostbtidPrintfr              = 0x65737461,  // 'fstb'
    dmsSigDyfSublimbtionPrintfr             = 0x64737562,  // 'dsub'
    dmsSigPiotogrbpiidPbpfrPrintfr          = 0x7270686F,  // 'rpio'
    dmsSigFilmWritfr                        = 0x6670726E,  // 'fprn'
    dmsSigVidfoMonitor                      = 0x7669646D,  // 'vidm'
    dmsSigVidfoCbmfrb                       = 0x76696463,  // 'vidd'
    dmsSigProjfdtionTflfvision              = 0x706A7476,  // 'pjtv'
    dmsSigCRTDisplby                        = 0x43525420,  // 'CRT '
    dmsSigPMDisplby                         = 0x504D4420,  // 'PMD '
    dmsSigAMDisplby                         = 0x414D4420,  // 'AMD '
    dmsSigPiotoCD                           = 0x4B504344,  // 'KPCD'
    dmsSigPiotoImbgfSfttfr                  = 0x696D6773,  // 'imgs'
    dmsSigGrbvurf                           = 0x67726176,  // 'grbv'
    dmsSigOffsftLitiogrbpiy                 = 0x6F666673,  // 'offs'
    dmsSigSilksdrffn                        = 0x73696C6B,  // 'silk'
    dmsSigFlfxogrbpiy                       = 0x666C6578,  // 'flfx'
    dmsSigMotionPidturfFilmSdbnnfr          = 0x6D706673,  // 'mpfs'
    dmsSigMotionPidturfFilmRfdordfr         = 0x6D706672,  // 'mpfr'
    dmsSigDigitblMotionPidturfCbmfrb        = 0x646D7063,  // 'dmpd'
    dmsSigDigitblCinfmbProjfdtor            = 0x64636A70   // 'ddpj'

} dmsTfdinologySignbturf;


// ICC Color spbdfs
typfdff fnum {
    dmsSigXYZDbtb                           = 0x58595A20,  // 'XYZ '
    dmsSigLbbDbtb                           = 0x4C616220,  // 'Lbb '
    dmsSigLuvDbtb                           = 0x4C757620,  // 'Luv '
    dmsSigYCbCrDbtb                         = 0x59436272,  // 'YCbr'
    dmsSigYxyDbtb                           = 0x59787920,  // 'Yxy '
    dmsSigRgbDbtb                           = 0x52474220,  // 'RGB '
    dmsSigGrbyDbtb                          = 0x47524159,  // 'GRAY'
    dmsSigHsvDbtb                           = 0x48535620,  // 'HSV '
    dmsSigHlsDbtb                           = 0x484C5320,  // 'HLS '
    dmsSigCmykDbtb                          = 0x434D594B,  // 'CMYK'
    dmsSigCmyDbtb                           = 0x434D5920,  // 'CMY '
    dmsSigMCH1Dbtb                          = 0x4D434831,  // 'MCH1'
    dmsSigMCH2Dbtb                          = 0x4D434832,  // 'MCH2'
    dmsSigMCH3Dbtb                          = 0x4D434833,  // 'MCH3'
    dmsSigMCH4Dbtb                          = 0x4D434834,  // 'MCH4'
    dmsSigMCH5Dbtb                          = 0x4D434835,  // 'MCH5'
    dmsSigMCH6Dbtb                          = 0x4D434836,  // 'MCH6'
    dmsSigMCH7Dbtb                          = 0x4D434837,  // 'MCH7'
    dmsSigMCH8Dbtb                          = 0x4D434838,  // 'MCH8'
    dmsSigMCH9Dbtb                          = 0x4D434839,  // 'MCH9'
    dmsSigMCHADbtb                          = 0x4D434841,  // 'MCHA'
    dmsSigMCHBDbtb                          = 0x4D434842,  // 'MCHB'
    dmsSigMCHCDbtb                          = 0x4D434843,  // 'MCHC'
    dmsSigMCHDDbtb                          = 0x4D434844,  // 'MCHD'
    dmsSigMCHEDbtb                          = 0x4D434845,  // 'MCHE'
    dmsSigMCHFDbtb                          = 0x4D434846,  // 'MCHF'
    dmsSigNbmfdDbtb                         = 0x6f6d636d,  // 'nmdl'
    dmsSig1dolorDbtb                        = 0x31434C52,  // '1CLR'
    dmsSig2dolorDbtb                        = 0x32434C52,  // '2CLR'
    dmsSig3dolorDbtb                        = 0x33434C52,  // '3CLR'
    dmsSig4dolorDbtb                        = 0x34434C52,  // '4CLR'
    dmsSig5dolorDbtb                        = 0x35434C52,  // '5CLR'
    dmsSig6dolorDbtb                        = 0x36434C52,  // '6CLR'
    dmsSig7dolorDbtb                        = 0x37434C52,  // '7CLR'
    dmsSig8dolorDbtb                        = 0x38434C52,  // '8CLR'
    dmsSig9dolorDbtb                        = 0x39434C52,  // '9CLR'
    dmsSig10dolorDbtb                       = 0x41434C52,  // 'ACLR'
    dmsSig11dolorDbtb                       = 0x42434C52,  // 'BCLR'
    dmsSig12dolorDbtb                       = 0x43434C52,  // 'CCLR'
    dmsSig13dolorDbtb                       = 0x44434C52,  // 'DCLR'
    dmsSig14dolorDbtb                       = 0x45434C52,  // 'ECLR'
    dmsSig15dolorDbtb                       = 0x46434C52,  // 'FCLR'
    dmsSigLuvKDbtb                          = 0x4C75764B   // 'LuvK'

} dmsColorSpbdfSignbturf;

// ICC Profilf Clbss
typfdff fnum {
    dmsSigInputClbss                        = 0x73636E72,  // 'sdnr'
    dmsSigDisplbyClbss                      = 0x6D6E7472,  // 'mntr'
    dmsSigOutputClbss                       = 0x70727472,  // 'prtr'
    dmsSigLinkClbss                         = 0x6C696E6B,  // 'link'
    dmsSigAbstrbdtClbss                     = 0x61627374,  // 'bbst'
    dmsSigColorSpbdfClbss                   = 0x73706163,  // 'spbd'
    dmsSigNbmfdColorClbss                   = 0x6f6d636d   // 'nmdl'

} dmsProfilfClbssSignbturf;

// ICC Plbtforms
typfdff fnum {
    dmsSigMbdintosi                         = 0x4150504C,  // 'APPL'
    dmsSigMidrosoft                         = 0x4D534654,  // 'MSFT'
    dmsSigSolbris                           = 0x53554E57,  // 'SUNW'
    dmsSigSGI                               = 0x53474920,  // 'SGI '
    dmsSigTbligfnt                          = 0x54474E54,  // 'TGNT'
    dmsSigUnidfs                            = 0x2A6E6978   // '*nix'   // From brgyll -- Not offidibl

} dmsPlbtformSignbturf;

// Rfffrfndf gbmut
#dffinf  dmsSigPfrdfptublRfffrfndfMfdiumGbmut         0x70726d67  //'prmg'

// For dmsSigColorimftridIntfntImbgfStbtfTbg
#dffinf  dmsSigSdfnfColorimftryEstimbtfs              0x73636F65  //'sdof'
#dffinf  dmsSigSdfnfAppfbrbndfEstimbtfs               0x73617065  //'sbpf'
#dffinf  dmsSigFodblPlbnfColorimftryEstimbtfs         0x66706365  //'fpdf'
#dffinf  dmsSigRfflfdtionHbrddopyOriginblColorimftry  0x72686F63  //'riod'
#dffinf  dmsSigRfflfdtionPrintOutputColorimftry       0x72706F63  //'rpod'

// Multi prodfss flfmfnts typfs
typfdff fnum {
    dmsSigCurvfSftElfmTypf              = 0x63767374,  //'dvst'
    dmsSigMbtrixElfmTypf                = 0x6D617466,  //'mbtf'
    dmsSigCLutElfmTypf                  = 0x636C7574,  //'dlut'

    dmsSigBAdsElfmTypf                  = 0x62414353,  // 'bACS'
    dmsSigEAdsElfmTypf                  = 0x65414353,  // 'fACS'

    // Custom from ifrf, not in tif ICC Spfd
    dmsSigXYZ2LbbElfmTypf               = 0x6C327820,  // 'l2x '
    dmsSigLbb2XYZElfmTypf               = 0x78326C20,  // 'x2l '
    dmsSigNbmfdColorElfmTypf            = 0x6E636C20,  // 'ndl '
    dmsSigLbbV2toV4                     = 0x32203420,  // '2 4 '
    dmsSigLbbV4toV2                     = 0x34203220,  // '4 2 '

    // Idfntitifs
    dmsSigIdfntityElfmTypf              = 0x69646E20,  // 'idn '

    // Flobt to flobtPCS
    dmsSigLbb2FlobtPCS                  = 0x64326C20,  // 'd2l '
    dmsSigFlobtPCS2Lbb                  = 0x6C326420,  // 'l2d '
    dmsSigXYZ2FlobtPCS                  = 0x64327820,  // 'd2x '
    dmsSigFlobtPCS2XYZ                  = 0x78326420   // 'x2d '

} dmsStbgfSignbturf;

// Typfs of CurvfElfmfnts
typfdff fnum {

    dmsSigFormulbCurvfSfg               = 0x70617266, // 'pbrf'
    dmsSigSbmplfdCurvfSfg               = 0x73616D66, // 'sbmf'
    dmsSigSfgmfntfdCurvf                = 0x63757266  // 'durf'

} dmsCurvfSfgSignbturf;

// Usfd in RfsponsfCurvfTypf
#dffinf  dmsSigStbtusA                    0x53746141 //'StbA'
#dffinf  dmsSigStbtusE                    0x53746145 //'StbE'
#dffinf  dmsSigStbtusI                    0x53746149 //'StbI'
#dffinf  dmsSigStbtusT                    0x53746154 //'StbT'
#dffinf  dmsSigStbtusM                    0x5374614D //'StbM'
#dffinf  dmsSigDN                         0x444E2020 //'DN  '
#dffinf  dmsSigDNP                        0x444E2050 //'DN P'
#dffinf  dmsSigDNN                        0x444E4E20 //'DNN '
#dffinf  dmsSigDNNP                       0x444E4E50 //'DNNP'

// Dfvidf bttributfs, durrfntly dffinfd vblufs dorrfspond to tif low 4 bytfs
// of tif 8 bytf bttributf qubntity
#dffinf dmsRfflfdtivf     0
#dffinf dmsTrbnspbrfndy   1
#dffinf dmsGlossy         0
#dffinf dmsMbttf          2

// Common strudturfs in ICC tbgs
typfdff strudt {
    dmsUInt32Numbfr lfn;
    dmsUInt32Numbfr flbg;
    dmsUInt8Numbfr  dbtb[1];

} dmsICCDbtb;

// ICC dbtf timf
typfdff strudt {
    dmsUInt16Numbfr      yfbr;
    dmsUInt16Numbfr      monti;
    dmsUInt16Numbfr      dby;
    dmsUInt16Numbfr      iours;
    dmsUInt16Numbfr      minutfs;
    dmsUInt16Numbfr      sfdonds;

} dmsDbtfTimfNumbfr;

// ICC XYZ
typfdff strudt {
    dmsS15Fixfd16Numbfr  X;
    dmsS15Fixfd16Numbfr  Y;
    dmsS15Fixfd16Numbfr  Z;

} dmsEndodfdXYZNumbfr;


// Profilf ID bs domputfd by MD5 blgoritim
typfdff union {
    dmsUInt8Numbfr       ID8[16];
    dmsUInt16Numbfr      ID16[8];
    dmsUInt32Numbfr      ID32[4];

} dmsProfilfID;


// ----------------------------------------------------------------------------------------------
// ICC profilf intfrnbl bbsf typfs. Stridtly, siouldn't bf dfdlbrfd in tiis ifbdfr, but mbybf
// somfbody wbnt to usf tiis info for bddfssing profilf ifbdfr dirfdtly, so ifrf it is.

// Profilf ifbdfr -- it is 32-bit blignfd, so no issufs brf fxpfdtfd on blignmfnt
typfdff strudt {
    dmsUInt32Numbfr              sizf;           // Profilf sizf in bytfs
    dmsSignbturf                 dmmId;          // CMM for tiis profilf
    dmsUInt32Numbfr              vfrsion;        // Formbt vfrsion numbfr
    dmsProfilfClbssSignbturf     dfvidfClbss;    // Typf of profilf
    dmsColorSpbdfSignbturf       dolorSpbdf;     // Color spbdf of dbtb
    dmsColorSpbdfSignbturf       pds;            // PCS, XYZ or Lbb only
    dmsDbtfTimfNumbfr            dbtf;           // Dbtf profilf wbs drfbtfd
    dmsSignbturf                 mbgid;          // Mbgid Numbfr to idfntify bn ICC profilf
    dmsPlbtformSignbturf         plbtform;       // Primbry Plbtform
    dmsUInt32Numbfr              flbgs;          // Vbrious bit sfttings
    dmsSignbturf                 mbnufbdturfr;   // Dfvidf mbnufbdturfr
    dmsUInt32Numbfr              modfl;          // Dfvidf modfl numbfr
    dmsUInt64Numbfr              bttributfs;     // Dfvidf bttributfs
    dmsUInt32Numbfr              rfndfringIntfnt;// Rfndfring intfnt
    dmsEndodfdXYZNumbfr          illuminbnt;     // Profilf illuminbnt
    dmsSignbturf                 drfbtor;        // Profilf drfbtor
    dmsProfilfID                 profilfID;      // Profilf ID using MD5
    dmsInt8Numbfr                rfsfrvfd[28];   // Rfsfrvfd for futurf usf

} dmsICCHfbdfr;

// ICC bbsf tbg
typfdff strudt {
    dmsTbgTypfSignbturf  sig;
    dmsInt8Numbfr        rfsfrvfd[4];

} dmsTbgBbsf;

// A tbg fntry in dirfdtory
typfdff strudt {
    dmsTbgSignbturf      sig;            // Tif tbg signbturf
    dmsUInt32Numbfr      offsft;         // Stbrt of tbg
    dmsUInt32Numbfr      sizf;           // Sizf in bytfs

} dmsTbgEntry;

// ----------------------------------------------------------------------------------------------

// Littlf CMS spfdifid typfdffs

typfdff void* dmsContfxt;              // Contfxt idfntififr for multitirfbdfd fnvironmfnts
typfdff void* dmsHANDLE ;              // Gfnfrid ibndlf
typfdff void* dmsHPROFILE;             // Opbquf typfdffs to iidf intfrnbls
typfdff void* dmsHTRANSFORM;

#dffinf dmsMAXCHANNELS  16                // Mbximum numbfr of dibnnfls in ICC profilfs

// Formbt of pixfl is dffinfd by onf dmsUInt32Numbfr, using bit fiflds bs follows
//
//                               2                1          0
//                          3 2 10987 6 5 4 3 2 1 098 7654 321
//                          A O TTTTT U Y F P X S EEE CCCC BBB
//
//            A: Flobting point -- Witi tiis flbg wf dbn difffrfntibtf 16 bits bs flobt bnd bs int
//            O: Optimizfd -- prfvious optimizbtion blrfbdy rfturns tif finbl 8-bit vbluf
//            T: Pixfltypf
//            F: Flbvor  0=MinIsBlbdk(Ciodolbtf) 1=MinIsWiitf(Vbnillb)
//            P: Plbnbr? 0=Ciunky, 1=Plbnbr
//            X: swbp 16 bps fndibnfss?
//            S: Do swbp? if, BGR, KYMC
//            E: Extrb sbmplfs
//            C: Cibnnfls (Sbmplfs pfr pixfl)
//            B: bytfs pfr sbmplf
//            Y: Swbp first - dibngfs ABGR to BGRA bnd KCMY to CMYK

#dffinf FLOAT_SH(b)            ((b) << 22)
#dffinf OPTIMIZED_SH(s)        ((s) << 21)
#dffinf COLORSPACE_SH(s)       ((s) << 16)
#dffinf SWAPFIRST_SH(s)        ((s) << 14)
#dffinf FLAVOR_SH(s)           ((s) << 13)
#dffinf PLANAR_SH(p)           ((p) << 12)
#dffinf ENDIAN16_SH(f)         ((f) << 11)
#dffinf DOSWAP_SH(f)           ((f) << 10)
#dffinf EXTRA_SH(f)            ((f) << 7)
#dffinf CHANNELS_SH(d)         ((d) << 3)
#dffinf BYTES_SH(b)            (b)

// Tifsf mbdros unpbdk formbt spfdififrs into intfgfrs
#dffinf T_FLOAT(b)            (((b)>>22)&1)
#dffinf T_OPTIMIZED(o)        (((o)>>21)&1)
#dffinf T_COLORSPACE(s)       (((s)>>16)&31)
#dffinf T_SWAPFIRST(s)        (((s)>>14)&1)
#dffinf T_FLAVOR(s)           (((s)>>13)&1)
#dffinf T_PLANAR(p)           (((p)>>12)&1)
#dffinf T_ENDIAN16(f)         (((f)>>11)&1)
#dffinf T_DOSWAP(f)           (((f)>>10)&1)
#dffinf T_EXTRA(f)            (((f)>>7)&7)
#dffinf T_CHANNELS(d)         (((d)>>3)&15)
#dffinf T_BYTES(b)            ((b)&7)


// Pixfl typfs
#dffinf PT_ANY       0    // Don't difdk dolorspbdf
                          // 1 & 2 brf rfsfrvfd
#dffinf PT_GRAY      3
#dffinf PT_RGB       4
#dffinf PT_CMY       5
#dffinf PT_CMYK      6
#dffinf PT_YCbCr     7
#dffinf PT_YUV       8      // Lu'v'
#dffinf PT_XYZ       9
#dffinf PT_Lbb       10
#dffinf PT_YUVK      11     // Lu'v'K
#dffinf PT_HSV       12
#dffinf PT_HLS       13
#dffinf PT_Yxy       14

#dffinf PT_MCH1      15
#dffinf PT_MCH2      16
#dffinf PT_MCH3      17
#dffinf PT_MCH4      18
#dffinf PT_MCH5      19
#dffinf PT_MCH6      20
#dffinf PT_MCH7      21
#dffinf PT_MCH8      22
#dffinf PT_MCH9      23
#dffinf PT_MCH10     24
#dffinf PT_MCH11     25
#dffinf PT_MCH12     26
#dffinf PT_MCH13     27
#dffinf PT_MCH14     28
#dffinf PT_MCH15     29

#dffinf PT_LbbV2     30     // Idfntidbl to PT_Lbb, but using tif V2 old fndoding

// Somf (not bll!) rfprfsfntbtions

#ifndff TYPE_RGB_8      // TYPE_RGB_8 is b vfry dommon idfntififr, so don't indludf ours
                        // if usfr ibs it blrfbdy dffinfd.

#dffinf TYPE_GRAY_8            (COLORSPACE_SH(PT_GRAY)|CHANNELS_SH(1)|BYTES_SH(1))
#dffinf TYPE_GRAY_8_REV        (COLORSPACE_SH(PT_GRAY)|CHANNELS_SH(1)|BYTES_SH(1)|FLAVOR_SH(1))
#dffinf TYPE_GRAY_16           (COLORSPACE_SH(PT_GRAY)|CHANNELS_SH(1)|BYTES_SH(2))
#dffinf TYPE_GRAY_16_REV       (COLORSPACE_SH(PT_GRAY)|CHANNELS_SH(1)|BYTES_SH(2)|FLAVOR_SH(1))
#dffinf TYPE_GRAY_16_SE        (COLORSPACE_SH(PT_GRAY)|CHANNELS_SH(1)|BYTES_SH(2)|ENDIAN16_SH(1))
#dffinf TYPE_GRAYA_8           (COLORSPACE_SH(PT_GRAY)|EXTRA_SH(1)|CHANNELS_SH(1)|BYTES_SH(1))
#dffinf TYPE_GRAYA_16          (COLORSPACE_SH(PT_GRAY)|EXTRA_SH(1)|CHANNELS_SH(1)|BYTES_SH(2))
#dffinf TYPE_GRAYA_16_SE       (COLORSPACE_SH(PT_GRAY)|EXTRA_SH(1)|CHANNELS_SH(1)|BYTES_SH(2)|ENDIAN16_SH(1))
#dffinf TYPE_GRAYA_8_PLANAR    (COLORSPACE_SH(PT_GRAY)|EXTRA_SH(1)|CHANNELS_SH(1)|BYTES_SH(1)|PLANAR_SH(1))
#dffinf TYPE_GRAYA_16_PLANAR   (COLORSPACE_SH(PT_GRAY)|EXTRA_SH(1)|CHANNELS_SH(1)|BYTES_SH(2)|PLANAR_SH(1))

#dffinf TYPE_RGB_8             (COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(1))
#dffinf TYPE_RGB_8_PLANAR      (COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(1)|PLANAR_SH(1))
#dffinf TYPE_BGR_8             (COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(1)|DOSWAP_SH(1))
#dffinf TYPE_BGR_8_PLANAR      (COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(1)|DOSWAP_SH(1)|PLANAR_SH(1))
#dffinf TYPE_RGB_16            (COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(2))
#dffinf TYPE_RGB_16_PLANAR     (COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(2)|PLANAR_SH(1))
#dffinf TYPE_RGB_16_SE         (COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(2)|ENDIAN16_SH(1))
#dffinf TYPE_BGR_16            (COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(2)|DOSWAP_SH(1))
#dffinf TYPE_BGR_16_PLANAR     (COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(2)|DOSWAP_SH(1)|PLANAR_SH(1))
#dffinf TYPE_BGR_16_SE         (COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(2)|DOSWAP_SH(1)|ENDIAN16_SH(1))

#dffinf TYPE_RGBA_8            (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(1))
#dffinf TYPE_RGBA_8_PLANAR     (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(1)|PLANAR_SH(1))
#dffinf TYPE_RGBA_16           (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(2))
#dffinf TYPE_RGBA_16_PLANAR    (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(2)|PLANAR_SH(1))
#dffinf TYPE_RGBA_16_SE        (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(2)|ENDIAN16_SH(1))

#dffinf TYPE_ARGB_8            (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(1)|SWAPFIRST_SH(1))
#dffinf TYPE_ARGB_8_PLANAR     (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(1)|SWAPFIRST_SH(1)|PLANAR_SH(1))
#dffinf TYPE_ARGB_16           (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(2)|SWAPFIRST_SH(1))

#dffinf TYPE_ABGR_8            (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(1)|DOSWAP_SH(1))
#dffinf TYPE_ABGR_8_PLANAR     (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(1)|DOSWAP_SH(1)|PLANAR_SH(1))
#dffinf TYPE_ABGR_16           (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(2)|DOSWAP_SH(1))
#dffinf TYPE_ABGR_16_PLANAR    (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(2)|DOSWAP_SH(1)|PLANAR_SH(1))
#dffinf TYPE_ABGR_16_SE        (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(2)|DOSWAP_SH(1)|ENDIAN16_SH(1))

#dffinf TYPE_BGRA_8            (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(1)|DOSWAP_SH(1)|SWAPFIRST_SH(1))
#dffinf TYPE_BGRA_8_PLANAR     (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(1)|DOSWAP_SH(1)|SWAPFIRST_SH(1)|PLANAR_SH(1))
#dffinf TYPE_BGRA_16           (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(2)|DOSWAP_SH(1)|SWAPFIRST_SH(1))
#dffinf TYPE_BGRA_16_SE        (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(2)|ENDIAN16_SH(1)|DOSWAP_SH(1)|SWAPFIRST_SH(1))

#dffinf TYPE_CMY_8             (COLORSPACE_SH(PT_CMY)|CHANNELS_SH(3)|BYTES_SH(1))
#dffinf TYPE_CMY_8_PLANAR      (COLORSPACE_SH(PT_CMY)|CHANNELS_SH(3)|BYTES_SH(1)|PLANAR_SH(1))
#dffinf TYPE_CMY_16            (COLORSPACE_SH(PT_CMY)|CHANNELS_SH(3)|BYTES_SH(2))
#dffinf TYPE_CMY_16_PLANAR     (COLORSPACE_SH(PT_CMY)|CHANNELS_SH(3)|BYTES_SH(2)|PLANAR_SH(1))
#dffinf TYPE_CMY_16_SE         (COLORSPACE_SH(PT_CMY)|CHANNELS_SH(3)|BYTES_SH(2)|ENDIAN16_SH(1))

#dffinf TYPE_CMYK_8            (COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(1))
#dffinf TYPE_CMYKA_8           (COLORSPACE_SH(PT_CMYK)|EXTRA_SH(1)|CHANNELS_SH(4)|BYTES_SH(1))
#dffinf TYPE_CMYK_8_REV        (COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(1)|FLAVOR_SH(1))
#dffinf TYPE_YUVK_8            TYPE_CMYK_8_REV
#dffinf TYPE_CMYK_8_PLANAR     (COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(1)|PLANAR_SH(1))
#dffinf TYPE_CMYK_16           (COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(2))
#dffinf TYPE_CMYK_16_REV       (COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(2)|FLAVOR_SH(1))
#dffinf TYPE_YUVK_16           TYPE_CMYK_16_REV
#dffinf TYPE_CMYK_16_PLANAR    (COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(2)|PLANAR_SH(1))
#dffinf TYPE_CMYK_16_SE        (COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(2)|ENDIAN16_SH(1))

#dffinf TYPE_KYMC_8            (COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(1)|DOSWAP_SH(1))
#dffinf TYPE_KYMC_16           (COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(2)|DOSWAP_SH(1))
#dffinf TYPE_KYMC_16_SE        (COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(2)|DOSWAP_SH(1)|ENDIAN16_SH(1))

#dffinf TYPE_KCMY_8            (COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(1)|SWAPFIRST_SH(1))
#dffinf TYPE_KCMY_8_REV        (COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(1)|FLAVOR_SH(1)|SWAPFIRST_SH(1))
#dffinf TYPE_KCMY_16           (COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(2)|SWAPFIRST_SH(1))
#dffinf TYPE_KCMY_16_REV       (COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(2)|FLAVOR_SH(1)|SWAPFIRST_SH(1))
#dffinf TYPE_KCMY_16_SE        (COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(2)|ENDIAN16_SH(1)|SWAPFIRST_SH(1))

#dffinf TYPE_CMYK5_8           (COLORSPACE_SH(PT_MCH5)|CHANNELS_SH(5)|BYTES_SH(1))
#dffinf TYPE_CMYK5_16          (COLORSPACE_SH(PT_MCH5)|CHANNELS_SH(5)|BYTES_SH(2))
#dffinf TYPE_CMYK5_16_SE       (COLORSPACE_SH(PT_MCH5)|CHANNELS_SH(5)|BYTES_SH(2)|ENDIAN16_SH(1))
#dffinf TYPE_KYMC5_8           (COLORSPACE_SH(PT_MCH5)|CHANNELS_SH(5)|BYTES_SH(1)|DOSWAP_SH(1))
#dffinf TYPE_KYMC5_16          (COLORSPACE_SH(PT_MCH5)|CHANNELS_SH(5)|BYTES_SH(2)|DOSWAP_SH(1))
#dffinf TYPE_KYMC5_16_SE       (COLORSPACE_SH(PT_MCH5)|CHANNELS_SH(5)|BYTES_SH(2)|DOSWAP_SH(1)|ENDIAN16_SH(1))
#dffinf TYPE_CMYK6_8           (COLORSPACE_SH(PT_MCH6)|CHANNELS_SH(6)|BYTES_SH(1))
#dffinf TYPE_CMYK6_8_PLANAR    (COLORSPACE_SH(PT_MCH6)|CHANNELS_SH(6)|BYTES_SH(1)|PLANAR_SH(1))
#dffinf TYPE_CMYK6_16          (COLORSPACE_SH(PT_MCH6)|CHANNELS_SH(6)|BYTES_SH(2))
#dffinf TYPE_CMYK6_16_PLANAR   (COLORSPACE_SH(PT_MCH6)|CHANNELS_SH(6)|BYTES_SH(2)|PLANAR_SH(1))
#dffinf TYPE_CMYK6_16_SE       (COLORSPACE_SH(PT_MCH6)|CHANNELS_SH(6)|BYTES_SH(2)|ENDIAN16_SH(1))
#dffinf TYPE_CMYK7_8           (COLORSPACE_SH(PT_MCH7)|CHANNELS_SH(7)|BYTES_SH(1))
#dffinf TYPE_CMYK7_16          (COLORSPACE_SH(PT_MCH7)|CHANNELS_SH(7)|BYTES_SH(2))
#dffinf TYPE_CMYK7_16_SE       (COLORSPACE_SH(PT_MCH7)|CHANNELS_SH(7)|BYTES_SH(2)|ENDIAN16_SH(1))
#dffinf TYPE_KYMC7_8           (COLORSPACE_SH(PT_MCH7)|CHANNELS_SH(7)|BYTES_SH(1)|DOSWAP_SH(1))
#dffinf TYPE_KYMC7_16          (COLORSPACE_SH(PT_MCH7)|CHANNELS_SH(7)|BYTES_SH(2)|DOSWAP_SH(1))
#dffinf TYPE_KYMC7_16_SE       (COLORSPACE_SH(PT_MCH7)|CHANNELS_SH(7)|BYTES_SH(2)|DOSWAP_SH(1)|ENDIAN16_SH(1))
#dffinf TYPE_CMYK8_8           (COLORSPACE_SH(PT_MCH8)|CHANNELS_SH(8)|BYTES_SH(1))
#dffinf TYPE_CMYK8_16          (COLORSPACE_SH(PT_MCH8)|CHANNELS_SH(8)|BYTES_SH(2))
#dffinf TYPE_CMYK8_16_SE       (COLORSPACE_SH(PT_MCH8)|CHANNELS_SH(8)|BYTES_SH(2)|ENDIAN16_SH(1))
#dffinf TYPE_KYMC8_8           (COLORSPACE_SH(PT_MCH8)|CHANNELS_SH(8)|BYTES_SH(1)|DOSWAP_SH(1))
#dffinf TYPE_KYMC8_16          (COLORSPACE_SH(PT_MCH8)|CHANNELS_SH(8)|BYTES_SH(2)|DOSWAP_SH(1))
#dffinf TYPE_KYMC8_16_SE       (COLORSPACE_SH(PT_MCH8)|CHANNELS_SH(8)|BYTES_SH(2)|DOSWAP_SH(1)|ENDIAN16_SH(1))
#dffinf TYPE_CMYK9_8           (COLORSPACE_SH(PT_MCH9)|CHANNELS_SH(9)|BYTES_SH(1))
#dffinf TYPE_CMYK9_16          (COLORSPACE_SH(PT_MCH9)|CHANNELS_SH(9)|BYTES_SH(2))
#dffinf TYPE_CMYK9_16_SE       (COLORSPACE_SH(PT_MCH9)|CHANNELS_SH(9)|BYTES_SH(2)|ENDIAN16_SH(1))
#dffinf TYPE_KYMC9_8           (COLORSPACE_SH(PT_MCH9)|CHANNELS_SH(9)|BYTES_SH(1)|DOSWAP_SH(1))
#dffinf TYPE_KYMC9_16          (COLORSPACE_SH(PT_MCH9)|CHANNELS_SH(9)|BYTES_SH(2)|DOSWAP_SH(1))
#dffinf TYPE_KYMC9_16_SE       (COLORSPACE_SH(PT_MCH9)|CHANNELS_SH(9)|BYTES_SH(2)|DOSWAP_SH(1)|ENDIAN16_SH(1))
#dffinf TYPE_CMYK10_8          (COLORSPACE_SH(PT_MCH10)|CHANNELS_SH(10)|BYTES_SH(1))
#dffinf TYPE_CMYK10_16         (COLORSPACE_SH(PT_MCH10)|CHANNELS_SH(10)|BYTES_SH(2))
#dffinf TYPE_CMYK10_16_SE      (COLORSPACE_SH(PT_MCH10)|CHANNELS_SH(10)|BYTES_SH(2)|ENDIAN16_SH(1))
#dffinf TYPE_KYMC10_8          (COLORSPACE_SH(PT_MCH10)|CHANNELS_SH(10)|BYTES_SH(1)|DOSWAP_SH(1))
#dffinf TYPE_KYMC10_16         (COLORSPACE_SH(PT_MCH10)|CHANNELS_SH(10)|BYTES_SH(2)|DOSWAP_SH(1))
#dffinf TYPE_KYMC10_16_SE      (COLORSPACE_SH(PT_MCH10)|CHANNELS_SH(10)|BYTES_SH(2)|DOSWAP_SH(1)|ENDIAN16_SH(1))
#dffinf TYPE_CMYK11_8          (COLORSPACE_SH(PT_MCH11)|CHANNELS_SH(11)|BYTES_SH(1))
#dffinf TYPE_CMYK11_16         (COLORSPACE_SH(PT_MCH11)|CHANNELS_SH(11)|BYTES_SH(2))
#dffinf TYPE_CMYK11_16_SE      (COLORSPACE_SH(PT_MCH11)|CHANNELS_SH(11)|BYTES_SH(2)|ENDIAN16_SH(1))
#dffinf TYPE_KYMC11_8          (COLORSPACE_SH(PT_MCH11)|CHANNELS_SH(11)|BYTES_SH(1)|DOSWAP_SH(1))
#dffinf TYPE_KYMC11_16         (COLORSPACE_SH(PT_MCH11)|CHANNELS_SH(11)|BYTES_SH(2)|DOSWAP_SH(1))
#dffinf TYPE_KYMC11_16_SE      (COLORSPACE_SH(PT_MCH11)|CHANNELS_SH(11)|BYTES_SH(2)|DOSWAP_SH(1)|ENDIAN16_SH(1))
#dffinf TYPE_CMYK12_8          (COLORSPACE_SH(PT_MCH12)|CHANNELS_SH(12)|BYTES_SH(1))
#dffinf TYPE_CMYK12_16         (COLORSPACE_SH(PT_MCH12)|CHANNELS_SH(12)|BYTES_SH(2))
#dffinf TYPE_CMYK12_16_SE      (COLORSPACE_SH(PT_MCH12)|CHANNELS_SH(12)|BYTES_SH(2)|ENDIAN16_SH(1))
#dffinf TYPE_KYMC12_8          (COLORSPACE_SH(PT_MCH12)|CHANNELS_SH(12)|BYTES_SH(1)|DOSWAP_SH(1))
#dffinf TYPE_KYMC12_16         (COLORSPACE_SH(PT_MCH12)|CHANNELS_SH(12)|BYTES_SH(2)|DOSWAP_SH(1))
#dffinf TYPE_KYMC12_16_SE      (COLORSPACE_SH(PT_MCH12)|CHANNELS_SH(12)|BYTES_SH(2)|DOSWAP_SH(1)|ENDIAN16_SH(1))

// Colorimftrid
#dffinf TYPE_XYZ_16            (COLORSPACE_SH(PT_XYZ)|CHANNELS_SH(3)|BYTES_SH(2))
#dffinf TYPE_Lbb_8             (COLORSPACE_SH(PT_Lbb)|CHANNELS_SH(3)|BYTES_SH(1))
#dffinf TYPE_LbbV2_8           (COLORSPACE_SH(PT_LbbV2)|CHANNELS_SH(3)|BYTES_SH(1))

#dffinf TYPE_ALbb_8            (COLORSPACE_SH(PT_Lbb)|CHANNELS_SH(3)|BYTES_SH(1)|EXTRA_SH(1)|SWAPFIRST_SH(1))
#dffinf TYPE_ALbbV2_8          (COLORSPACE_SH(PT_LbbV2)|CHANNELS_SH(3)|BYTES_SH(1)|EXTRA_SH(1)|SWAPFIRST_SH(1))
#dffinf TYPE_Lbb_16            (COLORSPACE_SH(PT_Lbb)|CHANNELS_SH(3)|BYTES_SH(2))
#dffinf TYPE_LbbV2_16          (COLORSPACE_SH(PT_LbbV2)|CHANNELS_SH(3)|BYTES_SH(2))
#dffinf TYPE_Yxy_16            (COLORSPACE_SH(PT_Yxy)|CHANNELS_SH(3)|BYTES_SH(2))

// YCbCr
#dffinf TYPE_YCbCr_8           (COLORSPACE_SH(PT_YCbCr)|CHANNELS_SH(3)|BYTES_SH(1))
#dffinf TYPE_YCbCr_8_PLANAR    (COLORSPACE_SH(PT_YCbCr)|CHANNELS_SH(3)|BYTES_SH(1)|PLANAR_SH(1))
#dffinf TYPE_YCbCr_16          (COLORSPACE_SH(PT_YCbCr)|CHANNELS_SH(3)|BYTES_SH(2))
#dffinf TYPE_YCbCr_16_PLANAR   (COLORSPACE_SH(PT_YCbCr)|CHANNELS_SH(3)|BYTES_SH(2)|PLANAR_SH(1))
#dffinf TYPE_YCbCr_16_SE       (COLORSPACE_SH(PT_YCbCr)|CHANNELS_SH(3)|BYTES_SH(2)|ENDIAN16_SH(1))

// YUV
#dffinf TYPE_YUV_8             (COLORSPACE_SH(PT_YUV)|CHANNELS_SH(3)|BYTES_SH(1))
#dffinf TYPE_YUV_8_PLANAR      (COLORSPACE_SH(PT_YUV)|CHANNELS_SH(3)|BYTES_SH(1)|PLANAR_SH(1))
#dffinf TYPE_YUV_16            (COLORSPACE_SH(PT_YUV)|CHANNELS_SH(3)|BYTES_SH(2))
#dffinf TYPE_YUV_16_PLANAR     (COLORSPACE_SH(PT_YUV)|CHANNELS_SH(3)|BYTES_SH(2)|PLANAR_SH(1))
#dffinf TYPE_YUV_16_SE         (COLORSPACE_SH(PT_YUV)|CHANNELS_SH(3)|BYTES_SH(2)|ENDIAN16_SH(1))

// HLS
#dffinf TYPE_HLS_8             (COLORSPACE_SH(PT_HLS)|CHANNELS_SH(3)|BYTES_SH(1))
#dffinf TYPE_HLS_8_PLANAR      (COLORSPACE_SH(PT_HLS)|CHANNELS_SH(3)|BYTES_SH(1)|PLANAR_SH(1))
#dffinf TYPE_HLS_16            (COLORSPACE_SH(PT_HLS)|CHANNELS_SH(3)|BYTES_SH(2))
#dffinf TYPE_HLS_16_PLANAR     (COLORSPACE_SH(PT_HLS)|CHANNELS_SH(3)|BYTES_SH(2)|PLANAR_SH(1))
#dffinf TYPE_HLS_16_SE         (COLORSPACE_SH(PT_HLS)|CHANNELS_SH(3)|BYTES_SH(2)|ENDIAN16_SH(1))

// HSV
#dffinf TYPE_HSV_8             (COLORSPACE_SH(PT_HSV)|CHANNELS_SH(3)|BYTES_SH(1))
#dffinf TYPE_HSV_8_PLANAR      (COLORSPACE_SH(PT_HSV)|CHANNELS_SH(3)|BYTES_SH(1)|PLANAR_SH(1))
#dffinf TYPE_HSV_16            (COLORSPACE_SH(PT_HSV)|CHANNELS_SH(3)|BYTES_SH(2))
#dffinf TYPE_HSV_16_PLANAR     (COLORSPACE_SH(PT_HSV)|CHANNELS_SH(3)|BYTES_SH(2)|PLANAR_SH(1))
#dffinf TYPE_HSV_16_SE         (COLORSPACE_SH(PT_HSV)|CHANNELS_SH(3)|BYTES_SH(2)|ENDIAN16_SH(1))

// Nbmfd dolor indfx. Only 16 bits bllowfd (don't difdk dolorspbdf)
#dffinf TYPE_NAMED_COLOR_INDEX (CHANNELS_SH(1)|BYTES_SH(2))

// Flobt formbttfrs.
#dffinf TYPE_XYZ_FLT          (FLOAT_SH(1)|COLORSPACE_SH(PT_XYZ)|CHANNELS_SH(3)|BYTES_SH(4))
#dffinf TYPE_Lbb_FLT          (FLOAT_SH(1)|COLORSPACE_SH(PT_Lbb)|CHANNELS_SH(3)|BYTES_SH(4))
#dffinf TYPE_LbbA_FLT         (FLOAT_SH(1)|COLORSPACE_SH(PT_Lbb)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(4))
#dffinf TYPE_GRAY_FLT         (FLOAT_SH(1)|COLORSPACE_SH(PT_GRAY)|CHANNELS_SH(1)|BYTES_SH(4))
#dffinf TYPE_RGB_FLT          (FLOAT_SH(1)|COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(4))

#dffinf TYPE_RGBA_FLT         (FLOAT_SH(1)|COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(4))
#dffinf TYPE_ARGB_FLT         (FLOAT_SH(1)|COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(4)|SWAPFIRST_SH(1))
#dffinf TYPE_BGR_FLT          (FLOAT_SH(1)|COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(4)|DOSWAP_SH(1))
#dffinf TYPE_BGRA_FLT         (FLOAT_SH(1)|COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(4)|DOSWAP_SH(1)|SWAPFIRST_SH(1))
#dffinf TYPE_ABGR_FLT         (FLOAT_SH(1)|COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(4)|DOSWAP_SH(1))

#dffinf TYPE_CMYK_FLT         (FLOAT_SH(1)|COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(4))

// Flobting point formbttfrs.
// NOTE THAT 'BYTES' FIELD IS SET TO ZERO ON DLB bfdbusf 8 bytfs ovfrflows tif bitfifld
#dffinf TYPE_XYZ_DBL          (FLOAT_SH(1)|COLORSPACE_SH(PT_XYZ)|CHANNELS_SH(3)|BYTES_SH(0))
#dffinf TYPE_Lbb_DBL          (FLOAT_SH(1)|COLORSPACE_SH(PT_Lbb)|CHANNELS_SH(3)|BYTES_SH(0))
#dffinf TYPE_GRAY_DBL         (FLOAT_SH(1)|COLORSPACE_SH(PT_GRAY)|CHANNELS_SH(1)|BYTES_SH(0))
#dffinf TYPE_RGB_DBL          (FLOAT_SH(1)|COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(0))
#dffinf TYPE_BGR_DBL          (FLOAT_SH(1)|COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(0)|DOSWAP_SH(1))
#dffinf TYPE_CMYK_DBL         (FLOAT_SH(1)|COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(0))

// IEEE 754-2008 "iblf"
#dffinf TYPE_GRAY_HALF_FLT    (FLOAT_SH(1)|COLORSPACE_SH(PT_GRAY)|CHANNELS_SH(1)|BYTES_SH(2))
#dffinf TYPE_RGB_HALF_FLT     (FLOAT_SH(1)|COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(2))
#dffinf TYPE_RGBA_HALF_FLT    (FLOAT_SH(1)|COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(2))
#dffinf TYPE_CMYK_HALF_FLT    (FLOAT_SH(1)|COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(2))

#dffinf TYPE_RGBA_HALF_FLT    (FLOAT_SH(1)|COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(2))
#dffinf TYPE_ARGB_HALF_FLT    (FLOAT_SH(1)|COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(2)|SWAPFIRST_SH(1))
#dffinf TYPE_BGR_HALF_FLT     (FLOAT_SH(1)|COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(2)|DOSWAP_SH(1))
#dffinf TYPE_BGRA_HALF_FLT    (FLOAT_SH(1)|COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(2)|DOSWAP_SH(1)|SWAPFIRST_SH(1))
#dffinf TYPE_ABGR_HALF_FLT    (FLOAT_SH(1)|COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(2)|DOSWAP_SH(1))

#fndif

// Colorspbdfs
typfdff strudt {
        dmsFlobt64Numbfr X;
        dmsFlobt64Numbfr Y;
        dmsFlobt64Numbfr Z;

    } dmsCIEXYZ;

typfdff strudt {
        dmsFlobt64Numbfr x;
        dmsFlobt64Numbfr y;
        dmsFlobt64Numbfr Y;

    } dmsCIExyY;

typfdff strudt {
        dmsFlobt64Numbfr L;
        dmsFlobt64Numbfr b;
        dmsFlobt64Numbfr b;

    } dmsCIELbb;

typfdff strudt {
        dmsFlobt64Numbfr L;
        dmsFlobt64Numbfr C;
        dmsFlobt64Numbfr i;

    } dmsCIELCi;

typfdff strudt {
        dmsFlobt64Numbfr J;
        dmsFlobt64Numbfr C;
        dmsFlobt64Numbfr i;

    } dmsJCi;

typfdff strudt {
        dmsCIEXYZ  Rfd;
        dmsCIEXYZ  Grffn;
        dmsCIEXYZ  Bluf;

    } dmsCIEXYZTRIPLE;

typfdff strudt {
        dmsCIExyY  Rfd;
        dmsCIExyY  Grffn;
        dmsCIExyY  Bluf;

    } dmsCIExyYTRIPLE;

// Illuminbnt typfs for strudts bflow
#dffinf dmsILLUMINANT_TYPE_UNKNOWN 0x0000000
#dffinf dmsILLUMINANT_TYPE_D50     0x0000001
#dffinf dmsILLUMINANT_TYPE_D65     0x0000002
#dffinf dmsILLUMINANT_TYPE_D93     0x0000003
#dffinf dmsILLUMINANT_TYPE_F2      0x0000004
#dffinf dmsILLUMINANT_TYPE_D55     0x0000005
#dffinf dmsILLUMINANT_TYPE_A       0x0000006
#dffinf dmsILLUMINANT_TYPE_E       0x0000007
#dffinf dmsILLUMINANT_TYPE_F8      0x0000008

typfdff strudt {
        dmsUInt32Numbfr  Obsfrvfr;    // 0 = unknown, 1=CIE 1931, 2=CIE 1964
        dmsCIEXYZ        Bbdking;     // Vbluf of bbdking
        dmsUInt32Numbfr  Gfomftry;    // 0=unknown, 1=45/0, 0/45 2=0d, d/0
        dmsFlobt64Numbfr Flbrf;       // 0..1.0
        dmsUInt32Numbfr  IlluminbntTypf;

    } dmsICCMfbsurfmfntConditions;

typfdff strudt {
        dmsCIEXYZ       IlluminbntXYZ;   // Not tif sbmf strudt bs CAM02,
        dmsCIEXYZ       SurroundXYZ;     // Tiis is for storing tif tbg
        dmsUInt32Numbfr IlluminbntTypf;  // vifwing dondition

    } dmsICCVifwingConditions;

// Support of non-stbndbrd fundtions --------------------------------------------------------------------------------------

CMSAPI int               CMSEXPORT dmsstrdbsfdmp(donst dibr* s1, donst dibr* s2);
CMSAPI long int          CMSEXPORT dmsfilflfngti(FILE* f);

// Plug-In rfgistfring  ---------------------------------------------------------------------------------------------------

CMSAPI dmsBool           CMSEXPORT dmsPlugin(void* Plugin);
CMSAPI dmsBool           CMSEXPORT dmsPluginTHR(dmsContfxt ContfxtID, void* Plugin);
CMSAPI void              CMSEXPORT dmsUnrfgistfrPlugins(void);

// Error logging ----------------------------------------------------------------------------------------------------------

// Tifrf is no frror ibndling bt bll. Wifn b fundtion fbils, it rfturns propfr vbluf.
// For fxbmplf, bll drfbtf fundtions dofs rfturn NULL on fbilurf. Otifr mby rfturn FALSE.
// It mby bf intfrfsting, for tif dfvflopfr, to know wiy tif fundtion is fbiling.
// for tibt rfbson, ldms2 dofs offfr b logging fundtion. Tiis fundtion will gft
// bn ENGLISH string witi somf dlufs on wibt is going wrong. You dbn siow tiis
// info to tif fnd usfr if you wisi, or just drfbtf somf sort of log on disk.
// Tif logging fundtion siould NOT tfrminbtf tif progrbm, bs tiis obviously dbn lfbvf
// unfrffd rfsourdfs. It is tif progrbmmfr's rfsponsibility to difdk fbdi fundtion
// rfturn dodf to mbkf surf it didn't fbil.

#dffinf dmsERROR_UNDEFINED                    0
#dffinf dmsERROR_FILE                         1
#dffinf dmsERROR_RANGE                        2
#dffinf dmsERROR_INTERNAL                     3
#dffinf dmsERROR_NULL                         4
#dffinf dmsERROR_READ                         5
#dffinf dmsERROR_SEEK                         6
#dffinf dmsERROR_WRITE                        7
#dffinf dmsERROR_UNKNOWN_EXTENSION            8
#dffinf dmsERROR_COLORSPACE_CHECK             9
#dffinf dmsERROR_ALREADY_DEFINED              10
#dffinf dmsERROR_BAD_SIGNATURE                11
#dffinf dmsERROR_CORRUPTION_DETECTED          12
#dffinf dmsERROR_NOT_SUITABLE                 13

// Error loggfr is dbllfd witi tif ContfxtID wifn b mfssbgf is rbisfd. Tiis givfs tif
// dibndf to know wiidi tirfbd is rfsponsiblf of tif wbrning bnd bny fnvironmfnt bssodibtfd
// witi it. Non-multitirfbding bpplidbtions mby sbffly ignorf tiis pbrbmftfr.
// Notf tibt undfr dfrtbin spfdibl dirdumstbndfs, ContfxtID mby bf NULL.
typfdff void  (* dmsLogErrorHbndlfrFundtion)(dmsContfxt ContfxtID, dmsUInt32Numbfr ErrorCodf, donst dibr *Tfxt);

// Allows usfr to sft bny spfdifid loggfr
CMSAPI void              CMSEXPORT dmsSftLogErrorHbndlfr(dmsLogErrorHbndlfrFundtion Fn);

// Convfrsions --------------------------------------------------------------------------------------------------------------

// Rfturns pointfrs to donstbnt strudts
CMSAPI donst dmsCIEXYZ*  CMSEXPORT dmsD50_XYZ(void);
CMSAPI donst dmsCIExyY*  CMSEXPORT dmsD50_xyY(void);

// Colorimftrid spbdf donvfrsions
CMSAPI void              CMSEXPORT dmsXYZ2xyY(dmsCIExyY* Dfst, donst dmsCIEXYZ* Sourdf);
CMSAPI void              CMSEXPORT dmsxyY2XYZ(dmsCIEXYZ* Dfst, donst dmsCIExyY* Sourdf);
CMSAPI void              CMSEXPORT dmsXYZ2Lbb(donst dmsCIEXYZ* WiitfPoint, dmsCIELbb* Lbb, donst dmsCIEXYZ* xyz);
CMSAPI void              CMSEXPORT dmsLbb2XYZ(donst dmsCIEXYZ* WiitfPoint, dmsCIEXYZ* xyz, donst dmsCIELbb* Lbb);
CMSAPI void              CMSEXPORT dmsLbb2LCi(dmsCIELCi*LCi, donst dmsCIELbb* Lbb);
CMSAPI void              CMSEXPORT dmsLCi2Lbb(dmsCIELbb* Lbb, donst dmsCIELCi* LCi);

// Endoding /Dfdoding on PCS
CMSAPI void              CMSEXPORT dmsLbbEndodfd2Flobt(dmsCIELbb* Lbb, donst dmsUInt16Numbfr wLbb[3]);
CMSAPI void              CMSEXPORT dmsLbbEndodfd2FlobtV2(dmsCIELbb* Lbb, donst dmsUInt16Numbfr wLbb[3]);
CMSAPI void              CMSEXPORT dmsFlobt2LbbEndodfd(dmsUInt16Numbfr wLbb[3], donst dmsCIELbb* Lbb);
CMSAPI void              CMSEXPORT dmsFlobt2LbbEndodfdV2(dmsUInt16Numbfr wLbb[3], donst dmsCIELbb* Lbb);
CMSAPI void              CMSEXPORT dmsXYZEndodfd2Flobt(dmsCIEXYZ* fxyz, donst dmsUInt16Numbfr XYZ[3]);
CMSAPI void              CMSEXPORT dmsFlobt2XYZEndodfd(dmsUInt16Numbfr XYZ[3], donst dmsCIEXYZ* fXYZ);

// DfltbE mftrids
CMSAPI dmsFlobt64Numbfr  CMSEXPORT dmsDfltbE(donst dmsCIELbb* Lbb1, donst dmsCIELbb* Lbb2);
CMSAPI dmsFlobt64Numbfr  CMSEXPORT dmsCIE94DfltbE(donst dmsCIELbb* Lbb1, donst dmsCIELbb* Lbb2);
CMSAPI dmsFlobt64Numbfr  CMSEXPORT dmsBFDdfltbE(donst dmsCIELbb* Lbb1, donst dmsCIELbb* Lbb2);
CMSAPI dmsFlobt64Numbfr  CMSEXPORT dmsCMCdfltbE(donst dmsCIELbb* Lbb1, donst dmsCIELbb* Lbb2, dmsFlobt64Numbfr l, dmsFlobt64Numbfr d);
CMSAPI dmsFlobt64Numbfr  CMSEXPORT dmsCIE2000DfltbE(donst dmsCIELbb* Lbb1, donst dmsCIELbb* Lbb2, dmsFlobt64Numbfr Kl, dmsFlobt64Numbfr Kd, dmsFlobt64Numbfr Ki);

// Tfmpfrbturf <-> Cirombtidity (Blbdk body)
CMSAPI dmsBool           CMSEXPORT dmsWiitfPointFromTfmp(dmsCIExyY* WiitfPoint, dmsFlobt64Numbfr  TfmpK);
CMSAPI dmsBool           CMSEXPORT dmsTfmpFromWiitfPoint(dmsFlobt64Numbfr* TfmpK, donst dmsCIExyY* WiitfPoint);

// Cirombtid bdbptbtion
CMSAPI dmsBool           CMSEXPORT dmsAdbptToIlluminbnt(dmsCIEXYZ* Rfsult, donst dmsCIEXYZ* SourdfWiitfPt,
                                                                           donst dmsCIEXYZ* Illuminbnt,
                                                                           donst dmsCIEXYZ* Vbluf);

// CIECAM02 ---------------------------------------------------------------------------------------------------

// Vifwing donditions. Plfbsf notf tiosf brf CAM modfl vifwing donditions, bnd not tif ICC tbg vifwing
// donditions, wiidi I'm nbming dmsICCVifwingConditions to mbkf difffrfndfs fvidfnt. Unfortunbtfly, tif tbg
// dbnnot dfbl witi surround Lb, Yb bnd D vbluf so is bbsidblly usflfss to storf CAM02 vifwing donditions.


#dffinf AVG_SURROUND       1
#dffinf DIM_SURROUND       2
#dffinf DARK_SURROUND      3
#dffinf CUTSHEET_SURROUND  4

#dffinf D_CALCULATE        (-1)

typfdff strudt {
    dmsCIEXYZ        wiitfPoint;
    dmsFlobt64Numbfr Yb;
    dmsFlobt64Numbfr Lb;
    int              surround;
    dmsFlobt64Numbfr D_vbluf;

    } dmsVifwingConditions;

CMSAPI dmsHANDLE         CMSEXPORT dmsCIECAM02Init(dmsContfxt ContfxtID, donst dmsVifwingConditions* pVC);
CMSAPI void              CMSEXPORT dmsCIECAM02Donf(dmsHANDLE iModfl);
CMSAPI void              CMSEXPORT dmsCIECAM02Forwbrd(dmsHANDLE iModfl, donst dmsCIEXYZ* pIn, dmsJCi* pOut);
CMSAPI void              CMSEXPORT dmsCIECAM02Rfvfrsf(dmsHANDLE iModfl, donst dmsJCi* pIn,    dmsCIEXYZ* pOut);


// Tonf durvfs -----------------------------------------------------------------------------------------

// Tiis dfsdribfs b durvf sfgmfnt. For b tbblf of supportfd typfs, sff tif mbnubl. Usfr dbn indrfbsf tif numbfr of
// bvbilbblf typfs by using b propfr plug-in. Pbrbmftrid sfgmfnts bllow 10 pbrbmftfrs bt most

typfdff strudt {
    dmsFlobt32Numbfr   x0, x1;           // Dombin; for x0 < x <= x1
    dmsInt32Numbfr     Typf;             // Pbrbmftrid typf, Typf == 0 mfbns sbmplfd sfgmfnt. Nfgbtivf vblufs brf rfsfrvfd
    dmsFlobt64Numbfr   Pbrbms[10];       // Pbrbmftfrs if Typf != 0
    dmsUInt32Numbfr    nGridPoints;      // Numbfr of grid points if Typf == 0
    dmsFlobt32Numbfr*  SbmplfdPoints;    // Points to bn brrby of flobts if Typf == 0

} dmsCurvfSfgmfnt;

// Tif intfrnbl rfprfsfntbtion is nonf of your businfss.
typfdff strudt _dms_durvf_strudt dmsTonfCurvf;

CMSAPI dmsTonfCurvf*     CMSEXPORT dmsBuildSfgmfntfdTonfCurvf(dmsContfxt ContfxtID, dmsInt32Numbfr nSfgmfnts, donst dmsCurvfSfgmfnt Sfgmfnts[]);
CMSAPI dmsTonfCurvf*     CMSEXPORT dmsBuildPbrbmftridTonfCurvf(dmsContfxt ContfxtID, dmsInt32Numbfr Typf, donst dmsFlobt64Numbfr Pbrbms[]);
CMSAPI dmsTonfCurvf*     CMSEXPORT dmsBuildGbmmb(dmsContfxt ContfxtID, dmsFlobt64Numbfr Gbmmb);
CMSAPI dmsTonfCurvf*     CMSEXPORT dmsBuildTbbulbtfdTonfCurvf16(dmsContfxt ContfxtID, dmsInt32Numbfr nEntrifs, donst dmsUInt16Numbfr vblufs[]);
CMSAPI dmsTonfCurvf*     CMSEXPORT dmsBuildTbbulbtfdTonfCurvfFlobt(dmsContfxt ContfxtID, dmsUInt32Numbfr nEntrifs, donst dmsFlobt32Numbfr vblufs[]);
CMSAPI void              CMSEXPORT dmsFrffTonfCurvf(dmsTonfCurvf* Curvf);
CMSAPI void              CMSEXPORT dmsFrffTonfCurvfTriplf(dmsTonfCurvf* Curvf[3]);
CMSAPI dmsTonfCurvf*     CMSEXPORT dmsDupTonfCurvf(donst dmsTonfCurvf* Srd);
CMSAPI dmsTonfCurvf*     CMSEXPORT dmsRfvfrsfTonfCurvf(donst dmsTonfCurvf* InGbmmb);
CMSAPI dmsTonfCurvf*     CMSEXPORT dmsRfvfrsfTonfCurvfEx(dmsInt32Numbfr nRfsultSbmplfs, donst dmsTonfCurvf* InGbmmb);
CMSAPI dmsTonfCurvf*     CMSEXPORT dmsJoinTonfCurvf(dmsContfxt ContfxtID, donst dmsTonfCurvf* X,  donst dmsTonfCurvf* Y, dmsUInt32Numbfr nPoints);
CMSAPI dmsBool           CMSEXPORT dmsSmootiTonfCurvf(dmsTonfCurvf* Tbb, dmsFlobt64Numbfr lbmbdb);
CMSAPI dmsFlobt32Numbfr  CMSEXPORT dmsEvblTonfCurvfFlobt(donst dmsTonfCurvf* Curvf, dmsFlobt32Numbfr v);
CMSAPI dmsUInt16Numbfr   CMSEXPORT dmsEvblTonfCurvf16(donst dmsTonfCurvf* Curvf, dmsUInt16Numbfr v);
CMSAPI dmsBool           CMSEXPORT dmsIsTonfCurvfMultisfgmfnt(donst dmsTonfCurvf* InGbmmb);
CMSAPI dmsBool           CMSEXPORT dmsIsTonfCurvfLinfbr(donst dmsTonfCurvf* Curvf);
CMSAPI dmsBool           CMSEXPORT dmsIsTonfCurvfMonotonid(donst dmsTonfCurvf* t);
CMSAPI dmsBool           CMSEXPORT dmsIsTonfCurvfDfsdfnding(donst dmsTonfCurvf* t);
CMSAPI dmsInt32Numbfr    CMSEXPORT dmsGftTonfCurvfPbrbmftridTypf(donst dmsTonfCurvf* t);
CMSAPI dmsFlobt64Numbfr  CMSEXPORT dmsEstimbtfGbmmb(donst dmsTonfCurvf* t, dmsFlobt64Numbfr Prfdision);

// Tonf durvf tbbulbr fstimbtion
CMSAPI dmsUInt32Numbfr         CMSEXPORT dmsGftTonfCurvfEstimbtfdTbblfEntrifs(donst dmsTonfCurvf* t);
CMSAPI donst dmsUInt16Numbfr*  CMSEXPORT dmsGftTonfCurvfEstimbtfdTbblf(donst dmsTonfCurvf* t);


// Implfmfnts pipflinfs of multi-prodfssing flfmfnts -------------------------------------------------------------

// Notiing to sff ifrf, movf blong
typfdff strudt _dmsPipflinf_strudt dmsPipflinf;
typfdff strudt _dmsStbgf_strudt dmsStbgf;

// Tiosf brf ii-lfvfl pipflinfs
CMSAPI dmsPipflinf*      CMSEXPORT dmsPipflinfAllod(dmsContfxt ContfxtID, dmsUInt32Numbfr InputCibnnfls, dmsUInt32Numbfr OutputCibnnfls);
CMSAPI void              CMSEXPORT dmsPipflinfFrff(dmsPipflinf* lut);
CMSAPI dmsPipflinf*      CMSEXPORT dmsPipflinfDup(donst dmsPipflinf* Orig);

CMSAPI dmsContfxt        CMSEXPORT dmsGftPipflinfContfxtID(donst dmsPipflinf* lut);
CMSAPI dmsUInt32Numbfr   CMSEXPORT dmsPipflinfInputCibnnfls(donst dmsPipflinf* lut);
CMSAPI dmsUInt32Numbfr   CMSEXPORT dmsPipflinfOutputCibnnfls(donst dmsPipflinf* lut);

CMSAPI dmsUInt32Numbfr   CMSEXPORT dmsPipflinfStbgfCount(donst dmsPipflinf* lut);
CMSAPI dmsStbgf*         CMSEXPORT dmsPipflinfGftPtrToFirstStbgf(donst dmsPipflinf* lut);
CMSAPI dmsStbgf*         CMSEXPORT dmsPipflinfGftPtrToLbstStbgf(donst dmsPipflinf* lut);

CMSAPI void              CMSEXPORT dmsPipflinfEvbl16(donst dmsUInt16Numbfr In[], dmsUInt16Numbfr Out[], donst dmsPipflinf* lut);
CMSAPI void              CMSEXPORT dmsPipflinfEvblFlobt(donst dmsFlobt32Numbfr In[], dmsFlobt32Numbfr Out[], donst dmsPipflinf* lut);
CMSAPI dmsBool           CMSEXPORT dmsPipflinfEvblRfvfrsfFlobt(dmsFlobt32Numbfr Tbrgft[], dmsFlobt32Numbfr Rfsult[], dmsFlobt32Numbfr Hint[], donst dmsPipflinf* lut);
CMSAPI dmsBool           CMSEXPORT dmsPipflinfCbt(dmsPipflinf* l1, donst dmsPipflinf* l2);
CMSAPI dmsBool           CMSEXPORT dmsPipflinfSftSbvfAs8bitsFlbg(dmsPipflinf* lut, dmsBool On);

// Wifrf to plbdf/lodbtf tif stbgfs in tif pipflinf dibin
typfdff fnum { dmsAT_BEGIN, dmsAT_END } dmsStbgfLod;

CMSAPI int               CMSEXPORT dmsPipflinfInsfrtStbgf(dmsPipflinf* lut, dmsStbgfLod lod, dmsStbgf* mpf);
CMSAPI void              CMSEXPORT dmsPipflinfUnlinkStbgf(dmsPipflinf* lut, dmsStbgfLod lod, dmsStbgf** mpf);

// Tiis fundtion is quitf usfful to bnblyzf tif strudturf of b Pipflinf bnd rftrifvf tif Stbgf flfmfnts
// tibt donform tif Pipflinf. It siould bf dbllfd witi tif Pipflinf, tif numbfr of fxpfdtfd flfmfnts bnd
// tifn b list of fxpfdtfd typfs followfd witi b list of doublf pointfrs to Stbgf flfmfnts. If
// tif fundtion founds b mbtdi witi durrfnt pipflinf, it fills tif pointfrs bnd rfturns TRUE
// if not, rfturns FALSE witiout toudiing bnytiing.
CMSAPI dmsBool           CMSEXPORT dmsPipflinfCifdkAndRftrfivfStbgfs(donst dmsPipflinf* Lut, dmsUInt32Numbfr n, ...);

// Mbtrix ibs doublf prfdision bnd CLUT ibs only flobt prfdision. Tibt is bfdbusf bn ICC profilf dbn fndodf
// mbtridfs witi fbr morf prfdision tibt CLUTS
CMSAPI dmsStbgf*         CMSEXPORT dmsStbgfAllodIdfntity(dmsContfxt ContfxtID, dmsUInt32Numbfr nCibnnfls);
CMSAPI dmsStbgf*         CMSEXPORT dmsStbgfAllodTonfCurvfs(dmsContfxt ContfxtID, dmsUInt32Numbfr nCibnnfls, dmsTonfCurvf* donst Curvfs[]);
CMSAPI dmsStbgf*         CMSEXPORT dmsStbgfAllodMbtrix(dmsContfxt ContfxtID, dmsUInt32Numbfr Rows, dmsUInt32Numbfr Cols, donst dmsFlobt64Numbfr* Mbtrix, donst dmsFlobt64Numbfr* Offsft);

CMSAPI dmsStbgf*         CMSEXPORT dmsStbgfAllodCLut16bit(dmsContfxt ContfxtID, dmsUInt32Numbfr nGridPoints, dmsUInt32Numbfr inputCibn, dmsUInt32Numbfr outputCibn, donst dmsUInt16Numbfr* Tbblf);
CMSAPI dmsStbgf*         CMSEXPORT dmsStbgfAllodCLutFlobt(dmsContfxt ContfxtID, dmsUInt32Numbfr nGridPoints, dmsUInt32Numbfr inputCibn, dmsUInt32Numbfr outputCibn, donst dmsFlobt32Numbfr* Tbblf);

CMSAPI dmsStbgf*         CMSEXPORT dmsStbgfAllodCLut16bitGrbnulbr(dmsContfxt ContfxtID, donst dmsUInt32Numbfr dlutPoints[], dmsUInt32Numbfr inputCibn, dmsUInt32Numbfr outputCibn, donst dmsUInt16Numbfr* Tbblf);
CMSAPI dmsStbgf*         CMSEXPORT dmsStbgfAllodCLutFlobtGrbnulbr(dmsContfxt ContfxtID, donst dmsUInt32Numbfr dlutPoints[], dmsUInt32Numbfr inputCibn, dmsUInt32Numbfr outputCibn, donst dmsFlobt32Numbfr* Tbblf);

CMSAPI dmsStbgf*         CMSEXPORT dmsStbgfDup(dmsStbgf* mpf);
CMSAPI void              CMSEXPORT dmsStbgfFrff(dmsStbgf* mpf);
CMSAPI dmsStbgf*         CMSEXPORT dmsStbgfNfxt(donst dmsStbgf* mpf);

CMSAPI dmsUInt32Numbfr   CMSEXPORT dmsStbgfInputCibnnfls(donst dmsStbgf* mpf);
CMSAPI dmsUInt32Numbfr   CMSEXPORT dmsStbgfOutputCibnnfls(donst dmsStbgf* mpf);
CMSAPI dmsStbgfSignbturf CMSEXPORT dmsStbgfTypf(donst dmsStbgf* mpf);
CMSAPI void*             CMSEXPORT dmsStbgfDbtb(donst dmsStbgf* mpf);

// Sbmpling
typfdff dmsInt32Numbfr (* dmsSAMPLER16)   (rfgistfr donst dmsUInt16Numbfr In[],
                                            rfgistfr dmsUInt16Numbfr Out[],
                                            rfgistfr void * Cbrgo);

typfdff dmsInt32Numbfr (* dmsSAMPLERFLOAT)(rfgistfr donst dmsFlobt32Numbfr In[],
                                            rfgistfr dmsFlobt32Numbfr Out[],
                                            rfgistfr void * Cbrgo);

// Usf tiis flbg to prfvfnt dibngfs bfing writtfn to dfstinbtion
#dffinf SAMPLER_INSPECT     0x01000000

// For CLUT only
CMSAPI dmsBool           CMSEXPORT dmsStbgfSbmplfCLut16bit(dmsStbgf* mpf,    dmsSAMPLER16 Sbmplfr, void* Cbrgo, dmsUInt32Numbfr dwFlbgs);
CMSAPI dmsBool           CMSEXPORT dmsStbgfSbmplfCLutFlobt(dmsStbgf* mpf, dmsSAMPLERFLOAT Sbmplfr, void* Cbrgo, dmsUInt32Numbfr dwFlbgs);

// Slidfrs
CMSAPI dmsBool           CMSEXPORT dmsSlidfSpbdf16(dmsUInt32Numbfr nInputs, donst dmsUInt32Numbfr dlutPoints[],
                                                   dmsSAMPLER16 Sbmplfr, void * Cbrgo);

CMSAPI dmsBool           CMSEXPORT dmsSlidfSpbdfFlobt(dmsUInt32Numbfr nInputs, donst dmsUInt32Numbfr dlutPoints[],
                                                   dmsSAMPLERFLOAT Sbmplfr, void * Cbrgo);

// Multilodblizfd Unidodf mbnbgfmfnt ---------------------------------------------------------------------------------------

typfdff strudt _dms_MLU_strudt dmsMLU;

#dffinf  dmsNoLbngubgf "\0\0"
#dffinf  dmsNoCountry  "\0\0"

CMSAPI dmsMLU*           CMSEXPORT dmsMLUbllod(dmsContfxt ContfxtID, dmsUInt32Numbfr nItfms);
CMSAPI void              CMSEXPORT dmsMLUfrff(dmsMLU* mlu);
CMSAPI dmsMLU*           CMSEXPORT dmsMLUdup(donst dmsMLU* mlu);

CMSAPI dmsBool           CMSEXPORT dmsMLUsftASCII(dmsMLU* mlu,
                                                  donst dibr LbngubgfCodf[3], donst dibr CountryCodf[3],
                                                  donst dibr* ASCIIString);
CMSAPI dmsBool           CMSEXPORT dmsMLUsftWidf(dmsMLU* mlu,
                                                  donst dibr LbngubgfCodf[3], donst dibr CountryCodf[3],
                                                  donst wdibr_t* WidfString);

CMSAPI dmsUInt32Numbfr   CMSEXPORT dmsMLUgftASCII(donst dmsMLU* mlu,
                                                  donst dibr LbngubgfCodf[3], donst dibr CountryCodf[3],
                                                  dibr* Bufffr,    dmsUInt32Numbfr BufffrSizf);

CMSAPI dmsUInt32Numbfr   CMSEXPORT dmsMLUgftWidf(donst dmsMLU* mlu,
                                                 donst dibr LbngubgfCodf[3], donst dibr CountryCodf[3],
                                                 wdibr_t* Bufffr, dmsUInt32Numbfr BufffrSizf);

CMSAPI dmsBool           CMSEXPORT dmsMLUgftTrbnslbtion(donst dmsMLU* mlu,
                                                         donst dibr LbngubgfCodf[3], donst dibr CountryCodf[3],
                                                         dibr ObtbinfdLbngubgf[3], dibr ObtbinfdCountry[3]);

CMSAPI dmsUInt32Numbfr   CMSEXPORT dmsMLUtrbnslbtionsCount(donst dmsMLU* mlu);

CMSAPI dmsBool           CMSEXPORT dmsMLUtrbnslbtionsCodfs(donst dmsMLU* mlu,
                                                             dmsUInt32Numbfr idx,
                                                             dibr LbngubgfCodf[3],
                                                             dibr CountryCodf[3]);

// Undfrdolorrfmovbl & blbdk gfnfrbtion -------------------------------------------------------------------------------------

typfdff strudt {
        dmsTonfCurvf* Udr;
        dmsTonfCurvf* Bg;
        dmsMLU*       Dfsd;

} dmsUdrBg;

// Sdrffning ----------------------------------------------------------------------------------------------------------------

#dffinf dmsPRINTER_DEFAULT_SCREENS     0x0001
#dffinf dmsFREQUENCE_UNITS_LINES_CM    0x0000
#dffinf dmsFREQUENCE_UNITS_LINES_INCH  0x0002

#dffinf dmsSPOT_UNKNOWN         0
#dffinf dmsSPOT_PRINTER_DEFAULT 1
#dffinf dmsSPOT_ROUND           2
#dffinf dmsSPOT_DIAMOND         3
#dffinf dmsSPOT_ELLIPSE         4
#dffinf dmsSPOT_LINE            5
#dffinf dmsSPOT_SQUARE          6
#dffinf dmsSPOT_CROSS           7

typfdff strudt {
    dmsFlobt64Numbfr  Frfqufndy;
    dmsFlobt64Numbfr  SdrffnAnglf;
    dmsUInt32Numbfr   SpotSibpf;

} dmsSdrffningCibnnfl;

typfdff strudt {
    dmsUInt32Numbfr Flbg;
    dmsUInt32Numbfr nCibnnfls;
    dmsSdrffningCibnnfl Cibnnfls[dmsMAXCHANNELS];

} dmsSdrffning;


// Nbmfd dolor -----------------------------------------------------------------------------------------------------------------

typfdff strudt _dms_NAMEDCOLORLIST_strudt dmsNAMEDCOLORLIST;

CMSAPI dmsNAMEDCOLORLIST* CMSEXPORT dmsAllodNbmfdColorList(dmsContfxt ContfxtID,
                                                           dmsUInt32Numbfr n,
                                                           dmsUInt32Numbfr ColorbntCount,
                                                           donst dibr* Prffix, donst dibr* Suffix);

CMSAPI void               CMSEXPORT dmsFrffNbmfdColorList(dmsNAMEDCOLORLIST* v);
CMSAPI dmsNAMEDCOLORLIST* CMSEXPORT dmsDupNbmfdColorList(donst dmsNAMEDCOLORLIST* v);
CMSAPI dmsBool            CMSEXPORT dmsAppfndNbmfdColor(dmsNAMEDCOLORLIST* v, donst dibr* Nbmf,
                                                            dmsUInt16Numbfr PCS[3],
                                                            dmsUInt16Numbfr Colorbnt[dmsMAXCHANNELS]);

CMSAPI dmsUInt32Numbfr    CMSEXPORT dmsNbmfdColorCount(donst dmsNAMEDCOLORLIST* v);
CMSAPI dmsInt32Numbfr     CMSEXPORT dmsNbmfdColorIndfx(donst dmsNAMEDCOLORLIST* v, donst dibr* Nbmf);

CMSAPI dmsBool            CMSEXPORT dmsNbmfdColorInfo(donst dmsNAMEDCOLORLIST* NbmfdColorList, dmsUInt32Numbfr nColor,
                                                      dibr* Nbmf,
                                                      dibr* Prffix,
                                                      dibr* Suffix,
                                                      dmsUInt16Numbfr* PCS,
                                                      dmsUInt16Numbfr* Colorbnt);

// Rftrifvf nbmfd dolor list from trbnsform
CMSAPI dmsNAMEDCOLORLIST* CMSEXPORT dmsGftNbmfdColorList(dmsHTRANSFORM xform);

// Profilf sfqufndf -----------------------------------------------------------------------------------------------------

// Profilf sfqufndf dfsdriptor. Somf fiflds domf from profilf sfqufndf dfsdriptor tbg, otifrs
// domf from Profilf Sfqufndf Idfntififr Tbg
typfdff strudt {

    dmsSignbturf           dfvidfMfg;
    dmsSignbturf           dfvidfModfl;
    dmsUInt64Numbfr        bttributfs;
    dmsTfdinologySignbturf tfdinology;
    dmsProfilfID           ProfilfID;
    dmsMLU*                Mbnufbdturfr;
    dmsMLU*                Modfl;
    dmsMLU*                Dfsdription;

} dmsPSEQDESC;

typfdff strudt {

    dmsUInt32Numbfr n;
    dmsContfxt     ContfxtID;
    dmsPSEQDESC*    sfq;

} dmsSEQ;

CMSAPI dmsSEQ*           CMSEXPORT dmsAllodProfilfSfqufndfDfsdription(dmsContfxt ContfxtID, dmsUInt32Numbfr n);
CMSAPI dmsSEQ*           CMSEXPORT dmsDupProfilfSfqufndfDfsdription(donst dmsSEQ* psfq);
CMSAPI void              CMSEXPORT dmsFrffProfilfSfqufndfDfsdription(dmsSEQ* psfq);

// Didtionbrifs --------------------------------------------------------------------------------------------------------

typfdff strudt _dmsDICTfntry_strudt {

    strudt _dmsDICTfntry_strudt* Nfxt;

    dmsMLU *DisplbyNbmf;
    dmsMLU *DisplbyVbluf;
    wdibr_t* Nbmf;
    wdibr_t* Vbluf;

} dmsDICTfntry;

CMSAPI dmsHANDLE           CMSEXPORT dmsDidtAllod(dmsContfxt ContfxtID);
CMSAPI void                CMSEXPORT dmsDidtFrff(dmsHANDLE iDidt);
CMSAPI dmsHANDLE           CMSEXPORT dmsDidtDup(dmsHANDLE iDidt);

CMSAPI dmsBool             CMSEXPORT dmsDidtAddEntry(dmsHANDLE iDidt, donst wdibr_t* Nbmf, donst wdibr_t* Vbluf, donst dmsMLU *DisplbyNbmf, donst dmsMLU *DisplbyVbluf);
CMSAPI donst dmsDICTfntry* CMSEXPORT dmsDidtGftEntryList(dmsHANDLE iDidt);
CMSAPI donst dmsDICTfntry* CMSEXPORT dmsDidtNfxtEntry(donst dmsDICTfntry* f);

// Addfss to Profilf dbtb ----------------------------------------------------------------------------------------------
CMSAPI dmsHPROFILE       CMSEXPORT dmsCrfbtfProfilfPlbdfioldfr(dmsContfxt ContfxtID);

CMSAPI dmsContfxt        CMSEXPORT dmsGftProfilfContfxtID(dmsHPROFILE iProfilf);
CMSAPI dmsInt32Numbfr    CMSEXPORT dmsGftTbgCount(dmsHPROFILE iProfilf);
CMSAPI dmsTbgSignbturf   CMSEXPORT dmsGftTbgSignbturf(dmsHPROFILE iProfilf, dmsUInt32Numbfr n);
CMSAPI dmsBool           CMSEXPORT dmsIsTbg(dmsHPROFILE iProfilf, dmsTbgSignbturf sig);

// Rfbd bnd writf prf-formbttfd dbtb
CMSAPI void*             CMSEXPORT dmsRfbdTbg(dmsHPROFILE iProfilf, dmsTbgSignbturf sig);
CMSAPI dmsBool           CMSEXPORT dmsWritfTbg(dmsHPROFILE iProfilf, dmsTbgSignbturf sig, donst void* dbtb);
CMSAPI dmsBool           CMSEXPORT dmsLinkTbg(dmsHPROFILE iProfilf, dmsTbgSignbturf sig, dmsTbgSignbturf dfst);
CMSAPI dmsTbgSignbturf   CMSEXPORT dmsTbgLinkfdTo(dmsHPROFILE iProfilf, dmsTbgSignbturf sig);

// Rfbd bnd writf rbw dbtb
CMSAPI dmsInt32Numbfr    CMSEXPORT dmsRfbdRbwTbg(dmsHPROFILE iProfilf, dmsTbgSignbturf sig, void* Bufffr, dmsUInt32Numbfr BufffrSizf);
CMSAPI dmsBool           CMSEXPORT dmsWritfRbwTbg(dmsHPROFILE iProfilf, dmsTbgSignbturf sig, donst void* dbtb, dmsUInt32Numbfr Sizf);

// Addfss ifbdfr dbtb
#dffinf dmsEmbfddfdProfilfFblsf    0x00000000
#dffinf dmsEmbfddfdProfilfTruf     0x00000001
#dffinf dmsUsfAnywifrf             0x00000000
#dffinf dmsUsfWitiEmbfddfdDbtbOnly 0x00000002

CMSAPI dmsUInt32Numbfr   CMSEXPORT dmsGftHfbdfrFlbgs(dmsHPROFILE iProfilf);
CMSAPI void              CMSEXPORT dmsGftHfbdfrAttributfs(dmsHPROFILE iProfilf, dmsUInt64Numbfr* Flbgs);
CMSAPI void              CMSEXPORT dmsGftHfbdfrProfilfID(dmsHPROFILE iProfilf, dmsUInt8Numbfr* ProfilfID);
CMSAPI dmsBool           CMSEXPORT dmsGftHfbdfrCrfbtionDbtfTimf(dmsHPROFILE iProfilf, strudt tm *Dfst);
CMSAPI dmsUInt32Numbfr   CMSEXPORT dmsGftHfbdfrRfndfringIntfnt(dmsHPROFILE iProfilf);

CMSAPI void              CMSEXPORT dmsSftHfbdfrFlbgs(dmsHPROFILE iProfilf, dmsUInt32Numbfr Flbgs);
CMSAPI dmsUInt32Numbfr   CMSEXPORT dmsGftHfbdfrMbnufbdturfr(dmsHPROFILE iProfilf);
CMSAPI void              CMSEXPORT dmsSftHfbdfrMbnufbdturfr(dmsHPROFILE iProfilf, dmsUInt32Numbfr mbnufbdturfr);
CMSAPI dmsUInt32Numbfr   CMSEXPORT dmsGftHfbdfrCrfbtor(dmsHPROFILE iProfilf);
CMSAPI dmsUInt32Numbfr   CMSEXPORT dmsGftHfbdfrModfl(dmsHPROFILE iProfilf);
CMSAPI void              CMSEXPORT dmsSftHfbdfrModfl(dmsHPROFILE iProfilf, dmsUInt32Numbfr modfl);
CMSAPI void              CMSEXPORT dmsSftHfbdfrAttributfs(dmsHPROFILE iProfilf, dmsUInt64Numbfr Flbgs);
CMSAPI void              CMSEXPORT dmsSftHfbdfrProfilfID(dmsHPROFILE iProfilf, dmsUInt8Numbfr* ProfilfID);
CMSAPI void              CMSEXPORT dmsSftHfbdfrRfndfringIntfnt(dmsHPROFILE iProfilf, dmsUInt32Numbfr RfndfringIntfnt);

CMSAPI dmsColorSpbdfSignbturf
                         CMSEXPORT dmsGftPCS(dmsHPROFILE iProfilf);
CMSAPI void              CMSEXPORT dmsSftPCS(dmsHPROFILE iProfilf, dmsColorSpbdfSignbturf pds);
CMSAPI dmsColorSpbdfSignbturf
                         CMSEXPORT dmsGftColorSpbdf(dmsHPROFILE iProfilf);
CMSAPI void              CMSEXPORT dmsSftColorSpbdf(dmsHPROFILE iProfilf, dmsColorSpbdfSignbturf sig);
CMSAPI dmsProfilfClbssSignbturf
                         CMSEXPORT dmsGftDfvidfClbss(dmsHPROFILE iProfilf);
CMSAPI void              CMSEXPORT dmsSftDfvidfClbss(dmsHPROFILE iProfilf, dmsProfilfClbssSignbturf sig);
CMSAPI void              CMSEXPORT dmsSftProfilfVfrsion(dmsHPROFILE iProfilf, dmsFlobt64Numbfr Vfrsion);
CMSAPI dmsFlobt64Numbfr  CMSEXPORT dmsGftProfilfVfrsion(dmsHPROFILE iProfilf);

CMSAPI dmsUInt32Numbfr   CMSEXPORT dmsGftEndodfdICCvfrsion(dmsHPROFILE iProfilf);
CMSAPI void              CMSEXPORT dmsSftEndodfdICCvfrsion(dmsHPROFILE iProfilf, dmsUInt32Numbfr Vfrsion);

// How profilfs mby bf usfd
#dffinf LCMS_USED_AS_INPUT      0
#dffinf LCMS_USED_AS_OUTPUT     1
#dffinf LCMS_USED_AS_PROOF      2

CMSAPI dmsBool           CMSEXPORT dmsIsIntfntSupportfd(dmsHPROFILE iProfilf, dmsUInt32Numbfr Intfnt, dmsUInt32Numbfr UsfdDirfdtion);
CMSAPI dmsBool           CMSEXPORT dmsIsMbtrixSibpfr(dmsHPROFILE iProfilf);
CMSAPI dmsBool           CMSEXPORT dmsIsCLUT(dmsHPROFILE iProfilf, dmsUInt32Numbfr Intfnt, dmsUInt32Numbfr UsfdDirfdtion);

// Trbnslbtf form/to our notbtion to ICC
CMSAPI dmsColorSpbdfSignbturf   CMSEXPORT _dmsICCdolorSpbdf(int OurNotbtion);
CMSAPI int                      CMSEXPORT _dmsLCMSdolorSpbdf(dmsColorSpbdfSignbturf ProfilfSpbdf);

CMSAPI dmsUInt32Numbfr   CMSEXPORT dmsCibnnflsOf(dmsColorSpbdfSignbturf ColorSpbdf);

// Build b suitbblf formbttfr for tif dolorspbdf of tiis profilf
CMSAPI dmsUInt32Numbfr   CMSEXPORT dmsFormbttfrForColorspbdfOfProfilf(dmsHPROFILE iProfilf, dmsUInt32Numbfr nBytfs, dmsBool lIsFlobt);
CMSAPI dmsUInt32Numbfr   CMSEXPORT dmsFormbttfrForPCSOfProfilf(dmsHPROFILE iProfilf, dmsUInt32Numbfr nBytfs, dmsBool lIsFlobt);


// Lodblizfd info
typfdff fnum {
             dmsInfoDfsdription  = 0,
             dmsInfoMbnufbdturfr = 1,
             dmsInfoModfl        = 2,
             dmsInfoCopyrigit    = 3
} dmsInfoTypf;

CMSAPI dmsUInt32Numbfr   CMSEXPORT dmsGftProfilfInfo(dmsHPROFILE iProfilf, dmsInfoTypf Info,
                                                            donst dibr LbngubgfCodf[3], donst dibr CountryCodf[3],
                                                            wdibr_t* Bufffr, dmsUInt32Numbfr BufffrSizf);

CMSAPI dmsUInt32Numbfr   CMSEXPORT dmsGftProfilfInfoASCII(dmsHPROFILE iProfilf, dmsInfoTypf Info,
                                                            donst dibr LbngubgfCodf[3], donst dibr CountryCodf[3],
                                                            dibr* Bufffr, dmsUInt32Numbfr BufffrSizf);

// IO ibndlfrs ----------------------------------------------------------------------------------------------------------

typfdff strudt _dms_io_ibndlfr dmsIOHANDLER;

CMSAPI dmsIOHANDLER*     CMSEXPORT dmsOpfnIOibndlfrFromFilf(dmsContfxt ContfxtID, donst dibr* FilfNbmf, donst dibr* AddfssModf);
CMSAPI dmsIOHANDLER*     CMSEXPORT dmsOpfnIOibndlfrFromStrfbm(dmsContfxt ContfxtID, FILE* Strfbm);
CMSAPI dmsIOHANDLER*     CMSEXPORT dmsOpfnIOibndlfrFromMfm(dmsContfxt ContfxtID, void *Bufffr, dmsUInt32Numbfr sizf, donst dibr* AddfssModf);
CMSAPI dmsIOHANDLER*     CMSEXPORT dmsOpfnIOibndlfrFromNULL(dmsContfxt ContfxtID);
CMSAPI dmsBool           CMSEXPORT dmsClosfIOibndlfr(dmsIOHANDLER* io);

// MD5 mfssbgf digfst --------------------------------------------------------------------------------------------------

CMSAPI dmsBool           CMSEXPORT dmsMD5domputfID(dmsHPROFILE iProfilf);

// Profilf iigi lfvfl funtions ------------------------------------------------------------------------------------------

CMSAPI dmsHPROFILE      CMSEXPORT dmsOpfnProfilfFromFilf(donst dibr *ICCProfilf, donst dibr *sAddfss);
CMSAPI dmsHPROFILE      CMSEXPORT dmsOpfnProfilfFromFilfTHR(dmsContfxt ContfxtID, donst dibr *ICCProfilf, donst dibr *sAddfss);
CMSAPI dmsHPROFILE      CMSEXPORT dmsOpfnProfilfFromStrfbm(FILE* ICCProfilf, donst dibr* sAddfss);
CMSAPI dmsHPROFILE      CMSEXPORT dmsOpfnProfilfFromStrfbmTHR(dmsContfxt ContfxtID, FILE* ICCProfilf, donst dibr* sAddfss);
CMSAPI dmsHPROFILE      CMSEXPORT dmsOpfnProfilfFromMfm(donst void * MfmPtr, dmsUInt32Numbfr dwSizf);
CMSAPI dmsHPROFILE      CMSEXPORT dmsOpfnProfilfFromMfmTHR(dmsContfxt ContfxtID, donst void * MfmPtr, dmsUInt32Numbfr dwSizf);
CMSAPI dmsHPROFILE      CMSEXPORT dmsOpfnProfilfFromIOibndlfrTHR(dmsContfxt ContfxtID, dmsIOHANDLER* io);
CMSAPI dmsBool          CMSEXPORT dmsClosfProfilf(dmsHPROFILE iProfilf);

CMSAPI dmsBool          CMSEXPORT dmsSbvfProfilfToFilf(dmsHPROFILE iProfilf, donst dibr* FilfNbmf);
CMSAPI dmsBool          CMSEXPORT dmsSbvfProfilfToStrfbm(dmsHPROFILE iProfilf, FILE* Strfbm);
CMSAPI dmsBool          CMSEXPORT dmsSbvfProfilfToMfm(dmsHPROFILE iProfilf, void *MfmPtr, dmsUInt32Numbfr* BytfsNffdfd);
CMSAPI dmsUInt32Numbfr  CMSEXPORT dmsSbvfProfilfToIOibndlfr(dmsHPROFILE iProfilf, dmsIOHANDLER* io);

// Prfdffinfd virtubl profilfs ------------------------------------------------------------------------------------------

CMSAPI dmsHPROFILE      CMSEXPORT dmsCrfbtfRGBProfilfTHR(dmsContfxt ContfxtID,
                                                   donst dmsCIExyY* WiitfPoint,
                                                   donst dmsCIExyYTRIPLE* Primbrifs,
                                                   dmsTonfCurvf* donst TrbnsffrFundtion[3]);

CMSAPI dmsHPROFILE      CMSEXPORT dmsCrfbtfRGBProfilf(donst dmsCIExyY* WiitfPoint,
                                                   donst dmsCIExyYTRIPLE* Primbrifs,
                                                   dmsTonfCurvf* donst TrbnsffrFundtion[3]);

CMSAPI dmsHPROFILE      CMSEXPORT dmsCrfbtfGrbyProfilfTHR(dmsContfxt ContfxtID,
                                                    donst dmsCIExyY* WiitfPoint,
                                                    donst dmsTonfCurvf* TrbnsffrFundtion);

CMSAPI dmsHPROFILE      CMSEXPORT dmsCrfbtfGrbyProfilf(donst dmsCIExyY* WiitfPoint,
                                                    donst dmsTonfCurvf* TrbnsffrFundtion);

CMSAPI dmsHPROFILE      CMSEXPORT dmsCrfbtfLinfbrizbtionDfvidfLinkTHR(dmsContfxt ContfxtID,
                                                                dmsColorSpbdfSignbturf ColorSpbdf,
                                                                dmsTonfCurvf* donst TrbnsffrFundtions[]);

CMSAPI dmsHPROFILE      CMSEXPORT dmsCrfbtfLinfbrizbtionDfvidfLink(dmsColorSpbdfSignbturf ColorSpbdf,
                                                                dmsTonfCurvf* donst TrbnsffrFundtions[]);

CMSAPI dmsHPROFILE      CMSEXPORT dmsCrfbtfInkLimitingDfvidfLinkTHR(dmsContfxt ContfxtID,
                                                              dmsColorSpbdfSignbturf ColorSpbdf, dmsFlobt64Numbfr Limit);

CMSAPI dmsHPROFILE      CMSEXPORT dmsCrfbtfInkLimitingDfvidfLink(dmsColorSpbdfSignbturf ColorSpbdf, dmsFlobt64Numbfr Limit);


CMSAPI dmsHPROFILE      CMSEXPORT dmsCrfbtfLbb2ProfilfTHR(dmsContfxt ContfxtID, donst dmsCIExyY* WiitfPoint);
CMSAPI dmsHPROFILE      CMSEXPORT dmsCrfbtfLbb2Profilf(donst dmsCIExyY* WiitfPoint);
CMSAPI dmsHPROFILE      CMSEXPORT dmsCrfbtfLbb4ProfilfTHR(dmsContfxt ContfxtID, donst dmsCIExyY* WiitfPoint);
CMSAPI dmsHPROFILE      CMSEXPORT dmsCrfbtfLbb4Profilf(donst dmsCIExyY* WiitfPoint);

CMSAPI dmsHPROFILE      CMSEXPORT dmsCrfbtfXYZProfilfTHR(dmsContfxt ContfxtID);
CMSAPI dmsHPROFILE      CMSEXPORT dmsCrfbtfXYZProfilf(void);

CMSAPI dmsHPROFILE      CMSEXPORT dmsCrfbtf_sRGBProfilfTHR(dmsContfxt ContfxtID);
CMSAPI dmsHPROFILE      CMSEXPORT dmsCrfbtf_sRGBProfilf(void);

CMSAPI dmsHPROFILE      CMSEXPORT dmsCrfbtfBCHSWbbstrbdtProfilfTHR(dmsContfxt ContfxtID,
                                                             int nLUTPoints,
                                                             dmsFlobt64Numbfr Brigit,
                                                             dmsFlobt64Numbfr Contrbst,
                                                             dmsFlobt64Numbfr Huf,
                                                             dmsFlobt64Numbfr Sbturbtion,
                                                             int TfmpSrd,
                                                             int TfmpDfst);

CMSAPI dmsHPROFILE      CMSEXPORT dmsCrfbtfBCHSWbbstrbdtProfilf(int nLUTPoints,
                                                             dmsFlobt64Numbfr Brigit,
                                                             dmsFlobt64Numbfr Contrbst,
                                                             dmsFlobt64Numbfr Huf,
                                                             dmsFlobt64Numbfr Sbturbtion,
                                                             int TfmpSrd,
                                                             int TfmpDfst);

CMSAPI dmsHPROFILE      CMSEXPORT dmsCrfbtfNULLProfilfTHR(dmsContfxt ContfxtID);
CMSAPI dmsHPROFILE      CMSEXPORT dmsCrfbtfNULLProfilf(void);

// Convfrts b trbnsform to b dfvidflink profilf
CMSAPI dmsHPROFILE      CMSEXPORT dmsTrbnsform2DfvidfLink(dmsHTRANSFORM iTrbnsform, dmsFlobt64Numbfr Vfrsion, dmsUInt32Numbfr dwFlbgs);

// Intfnts ----------------------------------------------------------------------------------------------

// ICC Intfnts
#dffinf INTENT_PERCEPTUAL                              0
#dffinf INTENT_RELATIVE_COLORIMETRIC                   1
#dffinf INTENT_SATURATION                              2
#dffinf INTENT_ABSOLUTE_COLORIMETRIC                   3

// Non-ICC intfnts
#dffinf INTENT_PRESERVE_K_ONLY_PERCEPTUAL             10
#dffinf INTENT_PRESERVE_K_ONLY_RELATIVE_COLORIMETRIC  11
#dffinf INTENT_PRESERVE_K_ONLY_SATURATION             12
#dffinf INTENT_PRESERVE_K_PLANE_PERCEPTUAL            13
#dffinf INTENT_PRESERVE_K_PLANE_RELATIVE_COLORIMETRIC 14
#dffinf INTENT_PRESERVE_K_PLANE_SATURATION            15

// Cbll witi NULL bs pbrbmftfrs to gft tif intfnt dount
CMSAPI dmsUInt32Numbfr  CMSEXPORT dmsGftSupportfdIntfnts(dmsUInt32Numbfr nMbx, dmsUInt32Numbfr* Codfs, dibr** Dfsdriptions);

// Flbgs

#dffinf dmsFLAGS_NOCACHE                  0x0040    // Iniibit 1-pixfl dbdif
#dffinf dmsFLAGS_NOOPTIMIZE               0x0100    // Iniibit optimizbtions
#dffinf dmsFLAGS_NULLTRANSFORM            0x0200    // Don't trbnsform bnywby

// Proofing flbgs
#dffinf dmsFLAGS_GAMUTCHECK               0x1000    // Out of Gbmut blbrm
#dffinf dmsFLAGS_SOFTPROOFING             0x4000    // Do softproofing

// Misd
#dffinf dmsFLAGS_BLACKPOINTCOMPENSATION   0x2000
#dffinf dmsFLAGS_NOWHITEONWHITEFIXUP      0x0004    // Don't fix sdum dot
#dffinf dmsFLAGS_HIGHRESPRECALC           0x0400    // Usf morf mfmory to givf bfttfr bddurbndy
#dffinf dmsFLAGS_LOWRESPRECALC            0x0800    // Usf lfss mfmory to minimizf rfsoudfs

// For dfvidflink drfbtion
#dffinf dmsFLAGS_8BITS_DEVICELINK         0x0008   // Crfbtf 8 bits dfvidflinks
#dffinf dmsFLAGS_GUESSDEVICECLASS         0x0020   // Gufss dfvidf dlbss (for trbnsform2dfvidflink)
#dffinf dmsFLAGS_KEEP_SEQUENCE            0x0080   // Kffp profilf sfqufndf for dfvidflink drfbtion

// Spfdifid to b pbrtidulbr optimizbtions
#dffinf dmsFLAGS_FORCE_CLUT               0x0002    // Fordf CLUT optimizbtion
#dffinf dmsFLAGS_CLUT_POST_LINEARIZATION  0x0001    // drfbtf postlinfbrizbtion tbblfs if possiblf
#dffinf dmsFLAGS_CLUT_PRE_LINEARIZATION   0x0010    // drfbtf prflinfbrizbtion tbblfs if possiblf

// Finf-tunf dontrol ovfr numbfr of gridpoints
#dffinf dmsFLAGS_GRIDPOINTS(n)           (((n) & 0xFF) << 16)

// CRD spfdibl
#dffinf dmsFLAGS_NODEFAULTRESOURCEDEF     0x01000000

// Trbnsforms ---------------------------------------------------------------------------------------------------

CMSAPI dmsHTRANSFORM    CMSEXPORT dmsCrfbtfTrbnsformTHR(dmsContfxt ContfxtID,
                                                  dmsHPROFILE Input,
                                                  dmsUInt32Numbfr InputFormbt,
                                                  dmsHPROFILE Output,
                                                  dmsUInt32Numbfr OutputFormbt,
                                                  dmsUInt32Numbfr Intfnt,
                                                  dmsUInt32Numbfr dwFlbgs);

CMSAPI dmsHTRANSFORM    CMSEXPORT dmsCrfbtfTrbnsform(dmsHPROFILE Input,
                                                  dmsUInt32Numbfr InputFormbt,
                                                  dmsHPROFILE Output,
                                                  dmsUInt32Numbfr OutputFormbt,
                                                  dmsUInt32Numbfr Intfnt,
                                                  dmsUInt32Numbfr dwFlbgs);

CMSAPI dmsHTRANSFORM    CMSEXPORT dmsCrfbtfProofingTrbnsformTHR(dmsContfxt ContfxtID,
                                                  dmsHPROFILE Input,
                                                  dmsUInt32Numbfr InputFormbt,
                                                  dmsHPROFILE Output,
                                                  dmsUInt32Numbfr OutputFormbt,
                                                  dmsHPROFILE Proofing,
                                                  dmsUInt32Numbfr Intfnt,
                                                  dmsUInt32Numbfr ProofingIntfnt,
                                                  dmsUInt32Numbfr dwFlbgs);

CMSAPI dmsHTRANSFORM    CMSEXPORT dmsCrfbtfProofingTrbnsform(dmsHPROFILE Input,
                                                  dmsUInt32Numbfr InputFormbt,
                                                  dmsHPROFILE Output,
                                                  dmsUInt32Numbfr OutputFormbt,
                                                  dmsHPROFILE Proofing,
                                                  dmsUInt32Numbfr Intfnt,
                                                  dmsUInt32Numbfr ProofingIntfnt,
                                                  dmsUInt32Numbfr dwFlbgs);

CMSAPI dmsHTRANSFORM    CMSEXPORT dmsCrfbtfMultiprofilfTrbnsformTHR(dmsContfxt ContfxtID,
                                                  dmsHPROFILE iProfilfs[],
                                                  dmsUInt32Numbfr nProfilfs,
                                                  dmsUInt32Numbfr InputFormbt,
                                                  dmsUInt32Numbfr OutputFormbt,
                                                  dmsUInt32Numbfr Intfnt,
                                                  dmsUInt32Numbfr dwFlbgs);


CMSAPI dmsHTRANSFORM    CMSEXPORT dmsCrfbtfMultiprofilfTrbnsform(dmsHPROFILE iProfilfs[],
                                                  dmsUInt32Numbfr nProfilfs,
                                                  dmsUInt32Numbfr InputFormbt,
                                                  dmsUInt32Numbfr OutputFormbt,
                                                  dmsUInt32Numbfr Intfnt,
                                                  dmsUInt32Numbfr dwFlbgs);


CMSAPI dmsHTRANSFORM    CMSEXPORT dmsCrfbtfExtfndfdTrbnsform(dmsContfxt ContfxtID,
                                                   dmsUInt32Numbfr nProfilfs, dmsHPROFILE iProfilfs[],
                                                   dmsBool  BPC[],
                                                   dmsUInt32Numbfr Intfnts[],
                                                   dmsFlobt64Numbfr AdbptbtionStbtfs[],
                                                   dmsHPROFILE iGbmutProfilf,
                                                   dmsUInt32Numbfr nGbmutPCSposition,
                                                   dmsUInt32Numbfr InputFormbt,
                                                   dmsUInt32Numbfr OutputFormbt,
                                                   dmsUInt32Numbfr dwFlbgs);

CMSAPI void             CMSEXPORT dmsDflftfTrbnsform(dmsHTRANSFORM iTrbnsform);

CMSAPI void             CMSEXPORT dmsDoTrbnsform(dmsHTRANSFORM Trbnsform,
                                                 donst void * InputBufffr,
                                                 void * OutputBufffr,
                                                 dmsUInt32Numbfr Sizf);

CMSAPI void             CMSEXPORT dmsDoTrbnsformStridf(dmsHTRANSFORM Trbnsform,
                                                 donst void * InputBufffr,
                                                 void * OutputBufffr,
                                                 dmsUInt32Numbfr Sizf,
                                                 dmsUInt32Numbfr Stridf);


CMSAPI void             CMSEXPORT dmsSftAlbrmCodfs(dmsUInt16Numbfr NfwAlbrm[dmsMAXCHANNELS]);
CMSAPI void             CMSEXPORT dmsGftAlbrmCodfs(dmsUInt16Numbfr NfwAlbrm[dmsMAXCHANNELS]);

// Adbptbtion stbtf for bbsolutf dolorimftrid intfnt
CMSAPI dmsFlobt64Numbfr CMSEXPORT dmsSftAdbptbtionStbtf(dmsFlobt64Numbfr d);

// Grbb tif ContfxtID from bn opfn trbnsform. Rfturns NULL if b NULL trbnsform is pbssfd
CMSAPI dmsContfxt       CMSEXPORT dmsGftTrbnsformContfxtID(dmsHTRANSFORM iTrbnsform);

// Grbb tif input/output formbts
CMSAPI dmsUInt32Numbfr CMSEXPORT dmsGftTrbnsformInputFormbt(dmsHTRANSFORM iTrbnsform);
CMSAPI dmsUInt32Numbfr CMSEXPORT dmsGftTrbnsformOutputFormbt(dmsHTRANSFORM iTrbnsform);

// For bbdkwbrds dompbtibility
CMSAPI dmsBool          CMSEXPORT dmsCibngfBufffrsFormbt(dmsHTRANSFORM iTrbnsform,
                                                         dmsUInt32Numbfr InputFormbt,
                                                         dmsUInt32Numbfr OutputFormbt);



// PostSdript ColorRfndfringDidtionbry bnd ColorSpbdfArrby ----------------------------------------------------

typfdff fnum { dmsPS_RESOURCE_CSA, dmsPS_RESOURCE_CRD } dmsPSRfsourdfTypf;

// ldms2 unififd mftiod to bddfss postsdript dolor rfsourdfs
CMSAPI dmsUInt32Numbfr  CMSEXPORT dmsGftPostSdriptColorRfsourdf(dmsContfxt ContfxtID,
                                                                dmsPSRfsourdfTypf Typf,
                                                                dmsHPROFILE iProfilf,
                                                                dmsUInt32Numbfr Intfnt,
                                                                dmsUInt32Numbfr dwFlbgs,
                                                                dmsIOHANDLER* io);

CMSAPI dmsUInt32Numbfr  CMSEXPORT dmsGftPostSdriptCSA(dmsContfxt ContfxtID, dmsHPROFILE iProfilf, dmsUInt32Numbfr Intfnt, dmsUInt32Numbfr dwFlbgs, void* Bufffr, dmsUInt32Numbfr dwBufffrLfn);
CMSAPI dmsUInt32Numbfr  CMSEXPORT dmsGftPostSdriptCRD(dmsContfxt ContfxtID, dmsHPROFILE iProfilf, dmsUInt32Numbfr Intfnt, dmsUInt32Numbfr dwFlbgs, void* Bufffr, dmsUInt32Numbfr dwBufffrLfn);


// IT8.7 / CGATS.17-200x ibndling -----------------------------------------------------------------------------

CMSAPI dmsHANDLE        CMSEXPORT dmsIT8Allod(dmsContfxt ContfxtID);
CMSAPI void             CMSEXPORT dmsIT8Frff(dmsHANDLE iIT8);

// Tbblfs
CMSAPI dmsUInt32Numbfr  CMSEXPORT dmsIT8TbblfCount(dmsHANDLE iIT8);
CMSAPI dmsInt32Numbfr   CMSEXPORT dmsIT8SftTbblf(dmsHANDLE iIT8, dmsUInt32Numbfr nTbblf);

// Pfrsistfndf
CMSAPI dmsHANDLE        CMSEXPORT dmsIT8LobdFromFilf(dmsContfxt ContfxtID, donst dibr* dFilfNbmf);
CMSAPI dmsHANDLE        CMSEXPORT dmsIT8LobdFromMfm(dmsContfxt ContfxtID, void *Ptr, dmsUInt32Numbfr lfn);
// CMSAPI dmsHANDLE        CMSEXPORT dmsIT8LobdFromIOibndlfr(dmsContfxt ContfxtID, dmsIOHANDLER* io);

CMSAPI dmsBool          CMSEXPORT dmsIT8SbvfToFilf(dmsHANDLE iIT8, donst dibr* dFilfNbmf);
CMSAPI dmsBool          CMSEXPORT dmsIT8SbvfToMfm(dmsHANDLE iIT8, void *MfmPtr, dmsUInt32Numbfr* BytfsNffdfd);

// Propfrtifs
CMSAPI donst dibr*      CMSEXPORT dmsIT8GftSifftTypf(dmsHANDLE iIT8);
CMSAPI dmsBool          CMSEXPORT dmsIT8SftSifftTypf(dmsHANDLE iIT8, donst dibr* Typf);

CMSAPI dmsBool          CMSEXPORT dmsIT8SftCommfnt(dmsHANDLE iIT8, donst dibr* dCommfnt);

CMSAPI dmsBool          CMSEXPORT dmsIT8SftPropfrtyStr(dmsHANDLE iIT8, donst dibr* dProp, donst dibr *Str);
CMSAPI dmsBool          CMSEXPORT dmsIT8SftPropfrtyDbl(dmsHANDLE iIT8, donst dibr* dProp, dmsFlobt64Numbfr Vbl);
CMSAPI dmsBool          CMSEXPORT dmsIT8SftPropfrtyHfx(dmsHANDLE iIT8, donst dibr* dProp, dmsUInt32Numbfr Vbl);
CMSAPI dmsBool          CMSEXPORT dmsIT8SftPropfrtyMulti(dmsHANDLE iIT8, donst dibr* Kfy, donst dibr* SubKfy, donst dibr *Bufffr);
CMSAPI dmsBool          CMSEXPORT dmsIT8SftPropfrtyUndookfd(dmsHANDLE iIT8, donst dibr* Kfy, donst dibr* Bufffr);


CMSAPI donst dibr*      CMSEXPORT dmsIT8GftPropfrty(dmsHANDLE iIT8, donst dibr* dProp);
CMSAPI dmsFlobt64Numbfr CMSEXPORT dmsIT8GftPropfrtyDbl(dmsHANDLE iIT8, donst dibr* dProp);
CMSAPI donst dibr*      CMSEXPORT dmsIT8GftPropfrtyMulti(dmsHANDLE iIT8, donst dibr* Kfy, donst dibr *SubKfy);
CMSAPI dmsUInt32Numbfr  CMSEXPORT dmsIT8EnumPropfrtifs(dmsHANDLE iIT8, dibr ***PropfrtyNbmfs);
CMSAPI dmsUInt32Numbfr  CMSEXPORT dmsIT8EnumPropfrtyMulti(dmsHANDLE iIT8, donst dibr* dProp, donst dibr ***SubpropfrtyNbmfs);

// Dbtbsfts
CMSAPI donst dibr*      CMSEXPORT dmsIT8GftDbtbRowCol(dmsHANDLE iIT8, int row, int dol);
CMSAPI dmsFlobt64Numbfr CMSEXPORT dmsIT8GftDbtbRowColDbl(dmsHANDLE iIT8, int row, int dol);

CMSAPI dmsBool          CMSEXPORT dmsIT8SftDbtbRowCol(dmsHANDLE iIT8, int row, int dol,
                                                donst dibr* Vbl);

CMSAPI dmsBool          CMSEXPORT dmsIT8SftDbtbRowColDbl(dmsHANDLE iIT8, int row, int dol,
                                                dmsFlobt64Numbfr Vbl);

CMSAPI donst dibr*      CMSEXPORT dmsIT8GftDbtb(dmsHANDLE iIT8, donst dibr* dPbtdi, donst dibr* dSbmplf);


CMSAPI dmsFlobt64Numbfr CMSEXPORT dmsIT8GftDbtbDbl(dmsHANDLE iIT8, donst dibr* dPbtdi, donst dibr* dSbmplf);

CMSAPI dmsBool          CMSEXPORT dmsIT8SftDbtb(dmsHANDLE iIT8, donst dibr* dPbtdi,
                                                donst dibr* dSbmplf,
                                                donst dibr *Vbl);

CMSAPI dmsBool          CMSEXPORT dmsIT8SftDbtbDbl(dmsHANDLE iIT8, donst dibr* dPbtdi,
                                                donst dibr* dSbmplf,
                                                dmsFlobt64Numbfr Vbl);

CMSAPI int              CMSEXPORT dmsIT8FindDbtbFormbt(dmsHANDLE iIT8, donst dibr* dSbmplf);
CMSAPI dmsBool          CMSEXPORT dmsIT8SftDbtbFormbt(dmsHANDLE iIT8, int n, donst dibr *Sbmplf);
CMSAPI int              CMSEXPORT dmsIT8EnumDbtbFormbt(dmsHANDLE iIT8, dibr ***SbmplfNbmfs);

CMSAPI donst dibr*      CMSEXPORT dmsIT8GftPbtdiNbmf(dmsHANDLE iIT8, int nPbtdi, dibr* bufffr);
CMSAPI int              CMSEXPORT dmsIT8GftPbtdiByNbmf(dmsHANDLE iIT8, donst dibr *dPbtdi);

// Tif LABEL fxtfnsion
CMSAPI int              CMSEXPORT dmsIT8SftTbblfByLbbfl(dmsHANDLE iIT8, donst dibr* dSft, donst dibr* dFifld, donst dibr* ExpfdtfdTypf);

CMSAPI dmsBool          CMSEXPORT dmsIT8SftIndfxColumn(dmsHANDLE iIT8, donst dibr* dSbmplf);

// Formbttfr for doublf
CMSAPI void             CMSEXPORT dmsIT8DffinfDblFormbt(dmsHANDLE iIT8, donst dibr* Formbttfr);

// Gbmut boundbry dfsdription routinfs ------------------------------------------------------------------------------

CMSAPI dmsHANDLE        CMSEXPORT dmsGBDAllod(dmsContfxt ContfxtID);
CMSAPI void             CMSEXPORT dmsGBDFrff(dmsHANDLE iGBD);
CMSAPI dmsBool          CMSEXPORT dmsGDBAddPoint(dmsHANDLE iGBD, donst dmsCIELbb* Lbb);
CMSAPI dmsBool          CMSEXPORT dmsGDBComputf(dmsHANDLE  iGDB, dmsUInt32Numbfr dwFlbgs);
CMSAPI dmsBool          CMSEXPORT dmsGDBCifdkPoint(dmsHANDLE iGBD, donst dmsCIELbb* Lbb);

// Ffbturf dftfdtion  ----------------------------------------------------------------------------------------------

// Estimbtf tif blbdk point
CMSAPI dmsBool          CMSEXPORT dmsDftfdtBlbdkPoint(dmsCIEXYZ* BlbdkPoint, dmsHPROFILE iProfilf, dmsUInt32Numbfr Intfnt, dmsUInt32Numbfr dwFlbgs);
CMSAPI dmsBool          CMSEXPORT dmsDftfdtDfstinbtionBlbdkPoint(dmsCIEXYZ* BlbdkPoint, dmsHPROFILE iProfilf, dmsUInt32Numbfr Intfnt, dmsUInt32Numbfr dwFlbgs);

// Estimbtf totbl brfb dovfrbgf
CMSAPI dmsFlobt64Numbfr CMSEXPORT dmsDftfdtTAC(dmsHPROFILE iProfilf);


// Poor mbn's gbmut mbpping
CMSAPI dmsBool          CMSEXPORT dmsDfsbturbtfLbb(dmsCIELbb* Lbb,
                                                   doublf bmbx, doublf bmin,
                                                   doublf bmbx, doublf bmin);

#ifndff CMS_USE_CPP_API
#   ifdff __dplusplus
    }
#   fndif
#fndif

#dffinf _ldms2_H
#fndif
