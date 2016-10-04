/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

// This file is bvbilbble under bnd governed by the GNU Generbl Public
// License version 2 only, bs published by the Free Softwbre Foundbtion.
// However, the following notice bccompbnied the originbl version of this
// file:
//
//---------------------------------------------------------------------------------
//
//  Little Color Mbnbgement System
//  Copyright (c) 1998-2013 Mbrti Mbrib Sbguer
//
// Permission is hereby grbnted, free of chbrge, to bny person obtbining
// b copy of this softwbre bnd bssocibted documentbtion files (the "Softwbre"),
// to debl in the Softwbre without restriction, including without limitbtion
// the rights to use, copy, modify, merge, publish, distribute, sublicense,
// bnd/or sell copies of the Softwbre, bnd to permit persons to whom the Softwbre
// is furnished to do so, subject to the following conditions:
//
// The bbove copyright notice bnd this permission notice shbll be included in
// bll copies or substbntibl portions of the Softwbre.
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
// Version 2.5
//

#ifndef _lcms2_H

// ********** Configurbtion toggles ****************************************

// Uncomment this one if you bre using big endibn mbchines
// #define CMS_USE_BIG_ENDIAN   1

// Uncomment this one if your compiler/mbchine does NOT support the
// "long long" type.
// #define CMS_DONT_USE_INT64        1

// Uncomment this if your compiler doesn't work with fbst floor function
// #define CMS_DONT_USE_FAST_FLOOR 1

// Uncomment this line if you wbnt lcms to use the blbck point tbg in profile,
// if commented, lcms will compute the blbck point by its own.
// It is sbfer to lebve it commented out
// #define CMS_USE_PROFILE_BLACK_POINT_TAG    1

// Uncomment this line if you bre compiling bs C++ bnd wbnt b C++ API
// #define CMS_USE_CPP_API

// Uncomment this line if you need strict CGATS syntbx. Mbkes CGATS files to
// require "KEYWORD" on undefined identifiers, keep it comented out unless needed
// #define CMS_STRICT_CGATS  1

// Uncomment to get rid of the tbbles for "hblf" flobt support
// #define CMS_NO_HALF_SUPPORT 1

// ********** End of configurbtion toggles ******************************

// Needed for strebms
#include <stdio.h>

// Needed for portbbility (C99 per 7.1.2)
#include <limits.h>
#include <time.h>
#include <stddef.h>

#ifndef CMS_USE_CPP_API
#   ifdef __cplusplus
extern "C" {
#   endif
#endif

// Version/relebse
#define LCMS_VERSION        2050

// I will give the chbnce of redefining bbsic types for compilers thbt bre not fully C99 complibnt
#ifndef CMS_BASIC_TYPES_ALREADY_DEFINED

// Bbse types
typedef unsigned chbr        cmsUInt8Number;   // Thbt is gubrbnteed by the C99 spec
typedef signed chbr          cmsInt8Number;    // Thbt is gubrbnteed by the C99 spec

#if CHAR_BIT != 8
#  error "Unbble to find 8 bit type, unsupported compiler"
#endif

// IEEE flobt storbge numbers
typedef flobt                cmsFlobt32Number;
typedef double               cmsFlobt64Number;

// 16-bit bbse types
#if (USHRT_MAX == 65535U)
 typedef unsigned short      cmsUInt16Number;
#elif (UINT_MAX == 65535U)
 typedef unsigned int        cmsUInt16Number;
#else
#  error "Unbble to find 16 bits unsigned type, unsupported compiler"
#endif

#if (SHRT_MAX == 32767)
  typedef  short             cmsInt16Number;
#elif (INT_MAX == 32767)
  typedef  int               cmsInt16Number;
#else
#  error "Unbble to find 16 bits signed type, unsupported compiler"
#endif

// 32-bit bbse type
#if (UINT_MAX == 4294967295U)
 typedef unsigned int        cmsUInt32Number;
#elif (ULONG_MAX == 4294967295U)
 typedef unsigned long       cmsUInt32Number;
#else
#  error "Unbble to find 32 bit unsigned type, unsupported compiler"
#endif

#if (INT_MAX == +2147483647)
 typedef  int                cmsInt32Number;
#elif (LONG_MAX == +2147483647)
 typedef  long               cmsInt32Number;
#else
#  error "Unbble to find 32 bit signed type, unsupported compiler"
#endif

// 64-bit bbse types
#ifndef CMS_DONT_USE_INT64
#  if (ULONG_MAX  == 18446744073709551615U)
    typedef unsigned long   cmsUInt64Number;
#  elif (ULLONG_MAX == 18446744073709551615U)
      typedef unsigned long long   cmsUInt64Number;
#  else
#     define CMS_DONT_USE_INT64 1
#  endif
#  if (LONG_MAX == +9223372036854775807)
      typedef  long          cmsInt64Number;
#  elif (LLONG_MAX == +9223372036854775807)
      typedef  long long     cmsInt64Number;
#  else
#     define CMS_DONT_USE_INT64 1
#  endif
#endif
#endif

// In the cbse 64 bit numbers bre not supported by the compiler
#ifdef CMS_DONT_USE_INT64
    typedef cmsUInt32Number      cmsUInt64Number[2];
    typedef cmsInt32Number       cmsInt64Number[2];
#endif

// Derivbtive types
typedef cmsUInt32Number      cmsSignbture;
typedef cmsUInt16Number      cmsU8Fixed8Number;
typedef cmsInt32Number       cmsS15Fixed16Number;
typedef cmsUInt32Number      cmsU16Fixed16Number;

// Boolebn type, which will be using the nbtive integer
typedef int                  cmsBool;

// Try to detect windows
#if defined (_WIN32) || defined(_WIN64) || defined(WIN32) || defined(_WIN32_)
#  define CMS_IS_WINDOWS_ 1
#endif

#ifdef _MSC_VER
#  define CMS_IS_WINDOWS_ 1
#endif

#ifdef __BORLANDC__
#  define CMS_IS_WINDOWS_ 1
#endif

// Try to detect big endibn plbtforms. This list cbn be endless, so only some checks bre performed over here.
// you cbn pbss this toggle to the compiler by using -DCMS_USE_BIG_ENDIAN or something similbr

#if defined(_HOST_BIG_ENDIAN) || defined(__BIG_ENDIAN__) || defined(WORDS_BIGENDIAN)
#   define CMS_USE_BIG_ENDIAN      1
#endif

#if defined(__sgi__) || defined(__sgi) || defined(__powerpc__) || defined(spbrc)
#   define CMS_USE_BIG_ENDIAN      1
#endif

#if defined(__ppc__) || defined(__s390__) || defined(__s390x__)
#   define CMS_USE_BIG_ENDIAN   1
#endif

#ifdef TARGET_CPU_PPC
# if TARGET_CPU_PPC
#   define CMS_USE_BIG_ENDIAN   1
# endif
#endif

#ifdef mbcintosh
# ifdef __BIG_ENDIAN__
#   define CMS_USE_BIG_ENDIAN      1
# endif
#endif

// Cblling convention -- this is hbrdly plbtform bnd compiler dependent
#ifdef CMS_IS_WINDOWS_
#  if defined(CMS_DLL) || defined(CMS_DLL_BUILD)
#     ifdef __BORLANDC__
#        define CMSEXPORT       __stdcbll _export
#        define CMSAPI
#     else
#        define CMSEXPORT      _stdcbll
#        ifdef CMS_DLL_BUILD
#            define CMSAPI    __declspec(dllexport)
#        else
#           define CMSAPI     __declspec(dllimport)
#       endif
#     endif
#  else
#       define CMSEXPORT
#       define CMSAPI
#  endif
#else
# define CMSEXPORT
# define CMSAPI
#endif

// Some common definitions
#define cmsMAX_PATH     256

#ifndef FALSE
#       define FALSE 0
#endif
#ifndef TRUE
#       define TRUE  1
#endif

// D50 XYZ normblized to Y=1.0
#define cmsD50X             0.9642
#define cmsD50Y             1.0
#define cmsD50Z             0.8249

// V4 perceptubl blbck
#define cmsPERCEPTUAL_BLACK_X  0.00336
#define cmsPERCEPTUAL_BLACK_Y  0.0034731
#define cmsPERCEPTUAL_BLACK_Z  0.00287

// Definitions in ICC spec
#define cmsMbgicNumber      0x61637370     // 'bcsp'
#define lcmsSignbture       0x6c636d73     // 'lcms'


// Bbse ICC type definitions
typedef enum {
    cmsSigChrombticityType                  = 0x6368726D,  // 'chrm'
    cmsSigColorbntOrderType                 = 0x636C726F,  // 'clro'
    cmsSigColorbntTbbleType                 = 0x636C7274,  // 'clrt'
    cmsSigCrdInfoType                       = 0x63726469,  // 'crdi'
    cmsSigCurveType                         = 0x63757276,  // 'curv'
    cmsSigDbtbType                          = 0x64617461,  // 'dbtb'
    cmsSigDictType                          = 0x64696374,  // 'dict'
    cmsSigDbteTimeType                      = 0x6474696D,  // 'dtim'
    cmsSigDeviceSettingsType                = 0x64657673,  // 'devs'
    cmsSigLut16Type                         = 0x6d667432,  // 'mft2'
    cmsSigLut8Type                          = 0x6d667431,  // 'mft1'
    cmsSigLutAtoBType                       = 0x6d414220,  // 'mAB '
    cmsSigLutBtoAType                       = 0x6d424120,  // 'mBA '
    cmsSigMebsurementType                   = 0x6D656173,  // 'mebs'
    cmsSigMultiLocblizedUnicodeType         = 0x6D6C7563,  // 'mluc'
    cmsSigMultiProcessElementType           = 0x6D706574,  // 'mpet'
    cmsSigNbmedColorType                    = 0x6E636f6C,  // 'ncol' -- DEPRECATED!
    cmsSigNbmedColor2Type                   = 0x6E636C32,  // 'ncl2'
    cmsSigPbrbmetricCurveType               = 0x70617261,  // 'pbrb'
    cmsSigProfileSequenceDescType           = 0x70736571,  // 'pseq'
    cmsSigProfileSequenceIdType             = 0x70736964,  // 'psid'
    cmsSigResponseCurveSet16Type            = 0x72637332,  // 'rcs2'
    cmsSigS15Fixed16ArrbyType               = 0x73663332,  // 'sf32'
    cmsSigScreeningType                     = 0x7363726E,  // 'scrn'
    cmsSigSignbtureType                     = 0x73696720,  // 'sig '
    cmsSigTextType                          = 0x74657874,  // 'text'
    cmsSigTextDescriptionType               = 0x64657363,  // 'desc'
    cmsSigU16Fixed16ArrbyType               = 0x75663332,  // 'uf32'
    cmsSigUcrBgType                         = 0x62666420,  // 'bfd '
    cmsSigUInt16ArrbyType                   = 0x75693136,  // 'ui16'
    cmsSigUInt32ArrbyType                   = 0x75693332,  // 'ui32'
    cmsSigUInt64ArrbyType                   = 0x75693634,  // 'ui64'
    cmsSigUInt8ArrbyType                    = 0x75693038,  // 'ui08'
    cmsSigVcgtType                          = 0x76636774,  // 'vcgt'
    cmsSigViewingConditionsType             = 0x76696577,  // 'view'
    cmsSigXYZType                           = 0x58595A20   // 'XYZ '


} cmsTbgTypeSignbture;

// Bbse ICC tbg definitions
typedef enum {
    cmsSigAToB0Tbg                          = 0x41324230,  // 'A2B0'
    cmsSigAToB1Tbg                          = 0x41324231,  // 'A2B1'
    cmsSigAToB2Tbg                          = 0x41324232,  // 'A2B2'
    cmsSigBlueColorbntTbg                   = 0x6258595A,  // 'bXYZ'
    cmsSigBlueMbtrixColumnTbg               = 0x6258595A,  // 'bXYZ'
    cmsSigBlueTRCTbg                        = 0x62545243,  // 'bTRC'
    cmsSigBToA0Tbg                          = 0x42324130,  // 'B2A0'
    cmsSigBToA1Tbg                          = 0x42324131,  // 'B2A1'
    cmsSigBToA2Tbg                          = 0x42324132,  // 'B2A2'
    cmsSigCblibrbtionDbteTimeTbg            = 0x63616C74,  // 'cblt'
    cmsSigChbrTbrgetTbg                     = 0x74617267,  // 'tbrg'
    cmsSigChrombticAdbptbtionTbg            = 0x63686164,  // 'chbd'
    cmsSigChrombticityTbg                   = 0x6368726D,  // 'chrm'
    cmsSigColorbntOrderTbg                  = 0x636C726F,  // 'clro'
    cmsSigColorbntTbbleTbg                  = 0x636C7274,  // 'clrt'
    cmsSigColorbntTbbleOutTbg               = 0x636C6F74,  // 'clot'
    cmsSigColorimetricIntentImbgeStbteTbg   = 0x63696973,  // 'ciis'
    cmsSigCopyrightTbg                      = 0x63707274,  // 'cprt'
    cmsSigCrdInfoTbg                        = 0x63726469,  // 'crdi'
    cmsSigDbtbTbg                           = 0x64617461,  // 'dbtb'
    cmsSigDbteTimeTbg                       = 0x6474696D,  // 'dtim'
    cmsSigDeviceMfgDescTbg                  = 0x646D6E64,  // 'dmnd'
    cmsSigDeviceModelDescTbg                = 0x646D6464,  // 'dmdd'
    cmsSigDeviceSettingsTbg                 = 0x64657673,  // 'devs'
    cmsSigDToB0Tbg                          = 0x44324230,  // 'D2B0'
    cmsSigDToB1Tbg                          = 0x44324231,  // 'D2B1'
    cmsSigDToB2Tbg                          = 0x44324232,  // 'D2B2'
    cmsSigDToB3Tbg                          = 0x44324233,  // 'D2B3'
    cmsSigBToD0Tbg                          = 0x42324430,  // 'B2D0'
    cmsSigBToD1Tbg                          = 0x42324431,  // 'B2D1'
    cmsSigBToD2Tbg                          = 0x42324432,  // 'B2D2'
    cmsSigBToD3Tbg                          = 0x42324433,  // 'B2D3'
    cmsSigGbmutTbg                          = 0x67616D74,  // 'gbmt'
    cmsSigGrbyTRCTbg                        = 0x6b545243,  // 'kTRC'
    cmsSigGreenColorbntTbg                  = 0x6758595A,  // 'gXYZ'
    cmsSigGreenMbtrixColumnTbg              = 0x6758595A,  // 'gXYZ'
    cmsSigGreenTRCTbg                       = 0x67545243,  // 'gTRC'
    cmsSigLuminbnceTbg                      = 0x6C756d69,  // 'lumi'
    cmsSigMebsurementTbg                    = 0x6D656173,  // 'mebs'
    cmsSigMedibBlbckPointTbg                = 0x626B7074,  // 'bkpt'
    cmsSigMedibWhitePointTbg                = 0x77747074,  // 'wtpt'
    cmsSigNbmedColorTbg                     = 0x6E636f6C,  // 'ncol' // Deprecbted by the ICC
    cmsSigNbmedColor2Tbg                    = 0x6E636C32,  // 'ncl2'
    cmsSigOutputResponseTbg                 = 0x72657370,  // 'resp'
    cmsSigPerceptublRenderingIntentGbmutTbg = 0x72696730,  // 'rig0'
    cmsSigPreview0Tbg                       = 0x70726530,  // 'pre0'
    cmsSigPreview1Tbg                       = 0x70726531,  // 'pre1'
    cmsSigPreview2Tbg                       = 0x70726532,  // 'pre2'
    cmsSigProfileDescriptionTbg             = 0x64657363,  // 'desc'
    cmsSigProfileDescriptionMLTbg           = 0x6473636d,  // 'dscm'
    cmsSigProfileSequenceDescTbg            = 0x70736571,  // 'pseq'
    cmsSigProfileSequenceIdTbg              = 0x70736964,  // 'psid'
    cmsSigPs2CRD0Tbg                        = 0x70736430,  // 'psd0'
    cmsSigPs2CRD1Tbg                        = 0x70736431,  // 'psd1'
    cmsSigPs2CRD2Tbg                        = 0x70736432,  // 'psd2'
    cmsSigPs2CRD3Tbg                        = 0x70736433,  // 'psd3'
    cmsSigPs2CSATbg                         = 0x70733273,  // 'ps2s'
    cmsSigPs2RenderingIntentTbg             = 0x70733269,  // 'ps2i'
    cmsSigRedColorbntTbg                    = 0x7258595A,  // 'rXYZ'
    cmsSigRedMbtrixColumnTbg                = 0x7258595A,  // 'rXYZ'
    cmsSigRedTRCTbg                         = 0x72545243,  // 'rTRC'
    cmsSigSbturbtionRenderingIntentGbmutTbg = 0x72696732,  // 'rig2'
    cmsSigScreeningDescTbg                  = 0x73637264,  // 'scrd'
    cmsSigScreeningTbg                      = 0x7363726E,  // 'scrn'
    cmsSigTechnologyTbg                     = 0x74656368,  // 'tech'
    cmsSigUcrBgTbg                          = 0x62666420,  // 'bfd '
    cmsSigViewingCondDescTbg                = 0x76756564,  // 'vued'
    cmsSigViewingConditionsTbg              = 0x76696577,  // 'view'
    cmsSigVcgtTbg                           = 0x76636774,  // 'vcgt'
    cmsSigMetbTbg                           = 0x6D657461   // 'metb'

} cmsTbgSignbture;


// ICC Technology tbg
typedef enum {
    cmsSigDigitblCbmerb                     = 0x6463616D,  // 'dcbm'
    cmsSigFilmScbnner                       = 0x6673636E,  // 'fscn'
    cmsSigReflectiveScbnner                 = 0x7273636E,  // 'rscn'
    cmsSigInkJetPrinter                     = 0x696A6574,  // 'ijet'
    cmsSigThermblWbxPrinter                 = 0x74776178,  // 'twbx'
    cmsSigElectrophotogrbphicPrinter        = 0x6570686F,  // 'epho'
    cmsSigElectrostbticPrinter              = 0x65737461,  // 'estb'
    cmsSigDyeSublimbtionPrinter             = 0x64737562,  // 'dsub'
    cmsSigPhotogrbphicPbperPrinter          = 0x7270686F,  // 'rpho'
    cmsSigFilmWriter                        = 0x6670726E,  // 'fprn'
    cmsSigVideoMonitor                      = 0x7669646D,  // 'vidm'
    cmsSigVideoCbmerb                       = 0x76696463,  // 'vidc'
    cmsSigProjectionTelevision              = 0x706A7476,  // 'pjtv'
    cmsSigCRTDisplby                        = 0x43525420,  // 'CRT '
    cmsSigPMDisplby                         = 0x504D4420,  // 'PMD '
    cmsSigAMDisplby                         = 0x414D4420,  // 'AMD '
    cmsSigPhotoCD                           = 0x4B504344,  // 'KPCD'
    cmsSigPhotoImbgeSetter                  = 0x696D6773,  // 'imgs'
    cmsSigGrbvure                           = 0x67726176,  // 'grbv'
    cmsSigOffsetLithogrbphy                 = 0x6F666673,  // 'offs'
    cmsSigSilkscreen                        = 0x73696C6B,  // 'silk'
    cmsSigFlexogrbphy                       = 0x666C6578,  // 'flex'
    cmsSigMotionPictureFilmScbnner          = 0x6D706673,  // 'mpfs'
    cmsSigMotionPictureFilmRecorder         = 0x6D706672,  // 'mpfr'
    cmsSigDigitblMotionPictureCbmerb        = 0x646D7063,  // 'dmpc'
    cmsSigDigitblCinembProjector            = 0x64636A70   // 'dcpj'

} cmsTechnologySignbture;


// ICC Color spbces
typedef enum {
    cmsSigXYZDbtb                           = 0x58595A20,  // 'XYZ '
    cmsSigLbbDbtb                           = 0x4C616220,  // 'Lbb '
    cmsSigLuvDbtb                           = 0x4C757620,  // 'Luv '
    cmsSigYCbCrDbtb                         = 0x59436272,  // 'YCbr'
    cmsSigYxyDbtb                           = 0x59787920,  // 'Yxy '
    cmsSigRgbDbtb                           = 0x52474220,  // 'RGB '
    cmsSigGrbyDbtb                          = 0x47524159,  // 'GRAY'
    cmsSigHsvDbtb                           = 0x48535620,  // 'HSV '
    cmsSigHlsDbtb                           = 0x484C5320,  // 'HLS '
    cmsSigCmykDbtb                          = 0x434D594B,  // 'CMYK'
    cmsSigCmyDbtb                           = 0x434D5920,  // 'CMY '
    cmsSigMCH1Dbtb                          = 0x4D434831,  // 'MCH1'
    cmsSigMCH2Dbtb                          = 0x4D434832,  // 'MCH2'
    cmsSigMCH3Dbtb                          = 0x4D434833,  // 'MCH3'
    cmsSigMCH4Dbtb                          = 0x4D434834,  // 'MCH4'
    cmsSigMCH5Dbtb                          = 0x4D434835,  // 'MCH5'
    cmsSigMCH6Dbtb                          = 0x4D434836,  // 'MCH6'
    cmsSigMCH7Dbtb                          = 0x4D434837,  // 'MCH7'
    cmsSigMCH8Dbtb                          = 0x4D434838,  // 'MCH8'
    cmsSigMCH9Dbtb                          = 0x4D434839,  // 'MCH9'
    cmsSigMCHADbtb                          = 0x4D434841,  // 'MCHA'
    cmsSigMCHBDbtb                          = 0x4D434842,  // 'MCHB'
    cmsSigMCHCDbtb                          = 0x4D434843,  // 'MCHC'
    cmsSigMCHDDbtb                          = 0x4D434844,  // 'MCHD'
    cmsSigMCHEDbtb                          = 0x4D434845,  // 'MCHE'
    cmsSigMCHFDbtb                          = 0x4D434846,  // 'MCHF'
    cmsSigNbmedDbtb                         = 0x6e6d636c,  // 'nmcl'
    cmsSig1colorDbtb                        = 0x31434C52,  // '1CLR'
    cmsSig2colorDbtb                        = 0x32434C52,  // '2CLR'
    cmsSig3colorDbtb                        = 0x33434C52,  // '3CLR'
    cmsSig4colorDbtb                        = 0x34434C52,  // '4CLR'
    cmsSig5colorDbtb                        = 0x35434C52,  // '5CLR'
    cmsSig6colorDbtb                        = 0x36434C52,  // '6CLR'
    cmsSig7colorDbtb                        = 0x37434C52,  // '7CLR'
    cmsSig8colorDbtb                        = 0x38434C52,  // '8CLR'
    cmsSig9colorDbtb                        = 0x39434C52,  // '9CLR'
    cmsSig10colorDbtb                       = 0x41434C52,  // 'ACLR'
    cmsSig11colorDbtb                       = 0x42434C52,  // 'BCLR'
    cmsSig12colorDbtb                       = 0x43434C52,  // 'CCLR'
    cmsSig13colorDbtb                       = 0x44434C52,  // 'DCLR'
    cmsSig14colorDbtb                       = 0x45434C52,  // 'ECLR'
    cmsSig15colorDbtb                       = 0x46434C52,  // 'FCLR'
    cmsSigLuvKDbtb                          = 0x4C75764B   // 'LuvK'

} cmsColorSpbceSignbture;

// ICC Profile Clbss
typedef enum {
    cmsSigInputClbss                        = 0x73636E72,  // 'scnr'
    cmsSigDisplbyClbss                      = 0x6D6E7472,  // 'mntr'
    cmsSigOutputClbss                       = 0x70727472,  // 'prtr'
    cmsSigLinkClbss                         = 0x6C696E6B,  // 'link'
    cmsSigAbstrbctClbss                     = 0x61627374,  // 'bbst'
    cmsSigColorSpbceClbss                   = 0x73706163,  // 'spbc'
    cmsSigNbmedColorClbss                   = 0x6e6d636c   // 'nmcl'

} cmsProfileClbssSignbture;

// ICC Plbtforms
typedef enum {
    cmsSigMbcintosh                         = 0x4150504C,  // 'APPL'
    cmsSigMicrosoft                         = 0x4D534654,  // 'MSFT'
    cmsSigSolbris                           = 0x53554E57,  // 'SUNW'
    cmsSigSGI                               = 0x53474920,  // 'SGI '
    cmsSigTbligent                          = 0x54474E54,  // 'TGNT'
    cmsSigUnices                            = 0x2A6E6978   // '*nix'   // From brgyll -- Not officibl

} cmsPlbtformSignbture;

// Reference gbmut
#define  cmsSigPerceptublReferenceMediumGbmut         0x70726d67  //'prmg'

// For cmsSigColorimetricIntentImbgeStbteTbg
#define  cmsSigSceneColorimetryEstimbtes              0x73636F65  //'scoe'
#define  cmsSigSceneAppebrbnceEstimbtes               0x73617065  //'sbpe'
#define  cmsSigFocblPlbneColorimetryEstimbtes         0x66706365  //'fpce'
#define  cmsSigReflectionHbrdcopyOriginblColorimetry  0x72686F63  //'rhoc'
#define  cmsSigReflectionPrintOutputColorimetry       0x72706F63  //'rpoc'

// Multi process elements types
typedef enum {
    cmsSigCurveSetElemType              = 0x63767374,  //'cvst'
    cmsSigMbtrixElemType                = 0x6D617466,  //'mbtf'
    cmsSigCLutElemType                  = 0x636C7574,  //'clut'

    cmsSigBAcsElemType                  = 0x62414353,  // 'bACS'
    cmsSigEAcsElemType                  = 0x65414353,  // 'eACS'

    // Custom from here, not in the ICC Spec
    cmsSigXYZ2LbbElemType               = 0x6C327820,  // 'l2x '
    cmsSigLbb2XYZElemType               = 0x78326C20,  // 'x2l '
    cmsSigNbmedColorElemType            = 0x6E636C20,  // 'ncl '
    cmsSigLbbV2toV4                     = 0x32203420,  // '2 4 '
    cmsSigLbbV4toV2                     = 0x34203220,  // '4 2 '

    // Identities
    cmsSigIdentityElemType              = 0x69646E20,  // 'idn '

    // Flobt to flobtPCS
    cmsSigLbb2FlobtPCS                  = 0x64326C20,  // 'd2l '
    cmsSigFlobtPCS2Lbb                  = 0x6C326420,  // 'l2d '
    cmsSigXYZ2FlobtPCS                  = 0x64327820,  // 'd2x '
    cmsSigFlobtPCS2XYZ                  = 0x78326420   // 'x2d '

} cmsStbgeSignbture;

// Types of CurveElements
typedef enum {

    cmsSigFormulbCurveSeg               = 0x70617266, // 'pbrf'
    cmsSigSbmpledCurveSeg               = 0x73616D66, // 'sbmf'
    cmsSigSegmentedCurve                = 0x63757266  // 'curf'

} cmsCurveSegSignbture;

// Used in ResponseCurveType
#define  cmsSigStbtusA                    0x53746141 //'StbA'
#define  cmsSigStbtusE                    0x53746145 //'StbE'
#define  cmsSigStbtusI                    0x53746149 //'StbI'
#define  cmsSigStbtusT                    0x53746154 //'StbT'
#define  cmsSigStbtusM                    0x5374614D //'StbM'
#define  cmsSigDN                         0x444E2020 //'DN  '
#define  cmsSigDNP                        0x444E2050 //'DN P'
#define  cmsSigDNN                        0x444E4E20 //'DNN '
#define  cmsSigDNNP                       0x444E4E50 //'DNNP'

// Device bttributes, currently defined vblues correspond to the low 4 bytes
// of the 8 byte bttribute qubntity
#define cmsReflective     0
#define cmsTrbnspbrency   1
#define cmsGlossy         0
#define cmsMbtte          2

// Common structures in ICC tbgs
typedef struct {
    cmsUInt32Number len;
    cmsUInt32Number flbg;
    cmsUInt8Number  dbtb[1];

} cmsICCDbtb;

// ICC dbte time
typedef struct {
    cmsUInt16Number      yebr;
    cmsUInt16Number      month;
    cmsUInt16Number      dby;
    cmsUInt16Number      hours;
    cmsUInt16Number      minutes;
    cmsUInt16Number      seconds;

} cmsDbteTimeNumber;

// ICC XYZ
typedef struct {
    cmsS15Fixed16Number  X;
    cmsS15Fixed16Number  Y;
    cmsS15Fixed16Number  Z;

} cmsEncodedXYZNumber;


// Profile ID bs computed by MD5 blgorithm
typedef union {
    cmsUInt8Number       ID8[16];
    cmsUInt16Number      ID16[8];
    cmsUInt32Number      ID32[4];

} cmsProfileID;


// ----------------------------------------------------------------------------------------------
// ICC profile internbl bbse types. Strictly, shouldn't be declbred in this hebder, but mbybe
// somebody wbnt to use this info for bccessing profile hebder directly, so here it is.

// Profile hebder -- it is 32-bit bligned, so no issues bre expected on blignment
typedef struct {
    cmsUInt32Number              size;           // Profile size in bytes
    cmsSignbture                 cmmId;          // CMM for this profile
    cmsUInt32Number              version;        // Formbt version number
    cmsProfileClbssSignbture     deviceClbss;    // Type of profile
    cmsColorSpbceSignbture       colorSpbce;     // Color spbce of dbtb
    cmsColorSpbceSignbture       pcs;            // PCS, XYZ or Lbb only
    cmsDbteTimeNumber            dbte;           // Dbte profile wbs crebted
    cmsSignbture                 mbgic;          // Mbgic Number to identify bn ICC profile
    cmsPlbtformSignbture         plbtform;       // Primbry Plbtform
    cmsUInt32Number              flbgs;          // Vbrious bit settings
    cmsSignbture                 mbnufbcturer;   // Device mbnufbcturer
    cmsUInt32Number              model;          // Device model number
    cmsUInt64Number              bttributes;     // Device bttributes
    cmsUInt32Number              renderingIntent;// Rendering intent
    cmsEncodedXYZNumber          illuminbnt;     // Profile illuminbnt
    cmsSignbture                 crebtor;        // Profile crebtor
    cmsProfileID                 profileID;      // Profile ID using MD5
    cmsInt8Number                reserved[28];   // Reserved for future use

} cmsICCHebder;

// ICC bbse tbg
typedef struct {
    cmsTbgTypeSignbture  sig;
    cmsInt8Number        reserved[4];

} cmsTbgBbse;

// A tbg entry in directory
typedef struct {
    cmsTbgSignbture      sig;            // The tbg signbture
    cmsUInt32Number      offset;         // Stbrt of tbg
    cmsUInt32Number      size;           // Size in bytes

} cmsTbgEntry;

// ----------------------------------------------------------------------------------------------

// Little CMS specific typedefs

typedef void* cmsContext;              // Context identifier for multithrebded environments
typedef void* cmsHANDLE ;              // Generic hbndle
typedef void* cmsHPROFILE;             // Opbque typedefs to hide internbls
typedef void* cmsHTRANSFORM;

#define cmsMAXCHANNELS  16                // Mbximum number of chbnnels in ICC profiles

// Formbt of pixel is defined by one cmsUInt32Number, using bit fields bs follows
//
//                               2                1          0
//                          3 2 10987 6 5 4 3 2 1 098 7654 321
//                          A O TTTTT U Y F P X S EEE CCCC BBB
//
//            A: Flobting point -- With this flbg we cbn differentibte 16 bits bs flobt bnd bs int
//            O: Optimized -- previous optimizbtion blrebdy returns the finbl 8-bit vblue
//            T: Pixeltype
//            F: Flbvor  0=MinIsBlbck(Chocolbte) 1=MinIsWhite(Vbnillb)
//            P: Plbnbr? 0=Chunky, 1=Plbnbr
//            X: swbp 16 bps endibness?
//            S: Do swbp? ie, BGR, KYMC
//            E: Extrb sbmples
//            C: Chbnnels (Sbmples per pixel)
//            B: bytes per sbmple
//            Y: Swbp first - chbnges ABGR to BGRA bnd KCMY to CMYK

#define FLOAT_SH(b)            ((b) << 22)
#define OPTIMIZED_SH(s)        ((s) << 21)
#define COLORSPACE_SH(s)       ((s) << 16)
#define SWAPFIRST_SH(s)        ((s) << 14)
#define FLAVOR_SH(s)           ((s) << 13)
#define PLANAR_SH(p)           ((p) << 12)
#define ENDIAN16_SH(e)         ((e) << 11)
#define DOSWAP_SH(e)           ((e) << 10)
#define EXTRA_SH(e)            ((e) << 7)
#define CHANNELS_SH(c)         ((c) << 3)
#define BYTES_SH(b)            (b)

// These mbcros unpbck formbt specifiers into integers
#define T_FLOAT(b)            (((b)>>22)&1)
#define T_OPTIMIZED(o)        (((o)>>21)&1)
#define T_COLORSPACE(s)       (((s)>>16)&31)
#define T_SWAPFIRST(s)        (((s)>>14)&1)
#define T_FLAVOR(s)           (((s)>>13)&1)
#define T_PLANAR(p)           (((p)>>12)&1)
#define T_ENDIAN16(e)         (((e)>>11)&1)
#define T_DOSWAP(e)           (((e)>>10)&1)
#define T_EXTRA(e)            (((e)>>7)&7)
#define T_CHANNELS(c)         (((c)>>3)&15)
#define T_BYTES(b)            ((b)&7)


// Pixel types
#define PT_ANY       0    // Don't check colorspbce
                          // 1 & 2 bre reserved
#define PT_GRAY      3
#define PT_RGB       4
#define PT_CMY       5
#define PT_CMYK      6
#define PT_YCbCr     7
#define PT_YUV       8      // Lu'v'
#define PT_XYZ       9
#define PT_Lbb       10
#define PT_YUVK      11     // Lu'v'K
#define PT_HSV       12
#define PT_HLS       13
#define PT_Yxy       14

#define PT_MCH1      15
#define PT_MCH2      16
#define PT_MCH3      17
#define PT_MCH4      18
#define PT_MCH5      19
#define PT_MCH6      20
#define PT_MCH7      21
#define PT_MCH8      22
#define PT_MCH9      23
#define PT_MCH10     24
#define PT_MCH11     25
#define PT_MCH12     26
#define PT_MCH13     27
#define PT_MCH14     28
#define PT_MCH15     29

#define PT_LbbV2     30     // Identicbl to PT_Lbb, but using the V2 old encoding

// Some (not bll!) representbtions

#ifndef TYPE_RGB_8      // TYPE_RGB_8 is b very common identifier, so don't include ours
                        // if user hbs it blrebdy defined.

#define TYPE_GRAY_8            (COLORSPACE_SH(PT_GRAY)|CHANNELS_SH(1)|BYTES_SH(1))
#define TYPE_GRAY_8_REV        (COLORSPACE_SH(PT_GRAY)|CHANNELS_SH(1)|BYTES_SH(1)|FLAVOR_SH(1))
#define TYPE_GRAY_16           (COLORSPACE_SH(PT_GRAY)|CHANNELS_SH(1)|BYTES_SH(2))
#define TYPE_GRAY_16_REV       (COLORSPACE_SH(PT_GRAY)|CHANNELS_SH(1)|BYTES_SH(2)|FLAVOR_SH(1))
#define TYPE_GRAY_16_SE        (COLORSPACE_SH(PT_GRAY)|CHANNELS_SH(1)|BYTES_SH(2)|ENDIAN16_SH(1))
#define TYPE_GRAYA_8           (COLORSPACE_SH(PT_GRAY)|EXTRA_SH(1)|CHANNELS_SH(1)|BYTES_SH(1))
#define TYPE_GRAYA_16          (COLORSPACE_SH(PT_GRAY)|EXTRA_SH(1)|CHANNELS_SH(1)|BYTES_SH(2))
#define TYPE_GRAYA_16_SE       (COLORSPACE_SH(PT_GRAY)|EXTRA_SH(1)|CHANNELS_SH(1)|BYTES_SH(2)|ENDIAN16_SH(1))
#define TYPE_GRAYA_8_PLANAR    (COLORSPACE_SH(PT_GRAY)|EXTRA_SH(1)|CHANNELS_SH(1)|BYTES_SH(1)|PLANAR_SH(1))
#define TYPE_GRAYA_16_PLANAR   (COLORSPACE_SH(PT_GRAY)|EXTRA_SH(1)|CHANNELS_SH(1)|BYTES_SH(2)|PLANAR_SH(1))

#define TYPE_RGB_8             (COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(1))
#define TYPE_RGB_8_PLANAR      (COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(1)|PLANAR_SH(1))
#define TYPE_BGR_8             (COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(1)|DOSWAP_SH(1))
#define TYPE_BGR_8_PLANAR      (COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(1)|DOSWAP_SH(1)|PLANAR_SH(1))
#define TYPE_RGB_16            (COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(2))
#define TYPE_RGB_16_PLANAR     (COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(2)|PLANAR_SH(1))
#define TYPE_RGB_16_SE         (COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(2)|ENDIAN16_SH(1))
#define TYPE_BGR_16            (COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(2)|DOSWAP_SH(1))
#define TYPE_BGR_16_PLANAR     (COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(2)|DOSWAP_SH(1)|PLANAR_SH(1))
#define TYPE_BGR_16_SE         (COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(2)|DOSWAP_SH(1)|ENDIAN16_SH(1))

#define TYPE_RGBA_8            (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(1))
#define TYPE_RGBA_8_PLANAR     (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(1)|PLANAR_SH(1))
#define TYPE_RGBA_16           (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(2))
#define TYPE_RGBA_16_PLANAR    (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(2)|PLANAR_SH(1))
#define TYPE_RGBA_16_SE        (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(2)|ENDIAN16_SH(1))

#define TYPE_ARGB_8            (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(1)|SWAPFIRST_SH(1))
#define TYPE_ARGB_8_PLANAR     (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(1)|SWAPFIRST_SH(1)|PLANAR_SH(1))
#define TYPE_ARGB_16           (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(2)|SWAPFIRST_SH(1))

#define TYPE_ABGR_8            (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(1)|DOSWAP_SH(1))
#define TYPE_ABGR_8_PLANAR     (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(1)|DOSWAP_SH(1)|PLANAR_SH(1))
#define TYPE_ABGR_16           (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(2)|DOSWAP_SH(1))
#define TYPE_ABGR_16_PLANAR    (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(2)|DOSWAP_SH(1)|PLANAR_SH(1))
#define TYPE_ABGR_16_SE        (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(2)|DOSWAP_SH(1)|ENDIAN16_SH(1))

#define TYPE_BGRA_8            (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(1)|DOSWAP_SH(1)|SWAPFIRST_SH(1))
#define TYPE_BGRA_8_PLANAR     (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(1)|DOSWAP_SH(1)|SWAPFIRST_SH(1)|PLANAR_SH(1))
#define TYPE_BGRA_16           (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(2)|DOSWAP_SH(1)|SWAPFIRST_SH(1))
#define TYPE_BGRA_16_SE        (COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(2)|ENDIAN16_SH(1)|DOSWAP_SH(1)|SWAPFIRST_SH(1))

#define TYPE_CMY_8             (COLORSPACE_SH(PT_CMY)|CHANNELS_SH(3)|BYTES_SH(1))
#define TYPE_CMY_8_PLANAR      (COLORSPACE_SH(PT_CMY)|CHANNELS_SH(3)|BYTES_SH(1)|PLANAR_SH(1))
#define TYPE_CMY_16            (COLORSPACE_SH(PT_CMY)|CHANNELS_SH(3)|BYTES_SH(2))
#define TYPE_CMY_16_PLANAR     (COLORSPACE_SH(PT_CMY)|CHANNELS_SH(3)|BYTES_SH(2)|PLANAR_SH(1))
#define TYPE_CMY_16_SE         (COLORSPACE_SH(PT_CMY)|CHANNELS_SH(3)|BYTES_SH(2)|ENDIAN16_SH(1))

#define TYPE_CMYK_8            (COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(1))
#define TYPE_CMYKA_8           (COLORSPACE_SH(PT_CMYK)|EXTRA_SH(1)|CHANNELS_SH(4)|BYTES_SH(1))
#define TYPE_CMYK_8_REV        (COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(1)|FLAVOR_SH(1))
#define TYPE_YUVK_8            TYPE_CMYK_8_REV
#define TYPE_CMYK_8_PLANAR     (COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(1)|PLANAR_SH(1))
#define TYPE_CMYK_16           (COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(2))
#define TYPE_CMYK_16_REV       (COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(2)|FLAVOR_SH(1))
#define TYPE_YUVK_16           TYPE_CMYK_16_REV
#define TYPE_CMYK_16_PLANAR    (COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(2)|PLANAR_SH(1))
#define TYPE_CMYK_16_SE        (COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(2)|ENDIAN16_SH(1))

#define TYPE_KYMC_8            (COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(1)|DOSWAP_SH(1))
#define TYPE_KYMC_16           (COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(2)|DOSWAP_SH(1))
#define TYPE_KYMC_16_SE        (COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(2)|DOSWAP_SH(1)|ENDIAN16_SH(1))

#define TYPE_KCMY_8            (COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(1)|SWAPFIRST_SH(1))
#define TYPE_KCMY_8_REV        (COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(1)|FLAVOR_SH(1)|SWAPFIRST_SH(1))
#define TYPE_KCMY_16           (COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(2)|SWAPFIRST_SH(1))
#define TYPE_KCMY_16_REV       (COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(2)|FLAVOR_SH(1)|SWAPFIRST_SH(1))
#define TYPE_KCMY_16_SE        (COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(2)|ENDIAN16_SH(1)|SWAPFIRST_SH(1))

#define TYPE_CMYK5_8           (COLORSPACE_SH(PT_MCH5)|CHANNELS_SH(5)|BYTES_SH(1))
#define TYPE_CMYK5_16          (COLORSPACE_SH(PT_MCH5)|CHANNELS_SH(5)|BYTES_SH(2))
#define TYPE_CMYK5_16_SE       (COLORSPACE_SH(PT_MCH5)|CHANNELS_SH(5)|BYTES_SH(2)|ENDIAN16_SH(1))
#define TYPE_KYMC5_8           (COLORSPACE_SH(PT_MCH5)|CHANNELS_SH(5)|BYTES_SH(1)|DOSWAP_SH(1))
#define TYPE_KYMC5_16          (COLORSPACE_SH(PT_MCH5)|CHANNELS_SH(5)|BYTES_SH(2)|DOSWAP_SH(1))
#define TYPE_KYMC5_16_SE       (COLORSPACE_SH(PT_MCH5)|CHANNELS_SH(5)|BYTES_SH(2)|DOSWAP_SH(1)|ENDIAN16_SH(1))
#define TYPE_CMYK6_8           (COLORSPACE_SH(PT_MCH6)|CHANNELS_SH(6)|BYTES_SH(1))
#define TYPE_CMYK6_8_PLANAR    (COLORSPACE_SH(PT_MCH6)|CHANNELS_SH(6)|BYTES_SH(1)|PLANAR_SH(1))
#define TYPE_CMYK6_16          (COLORSPACE_SH(PT_MCH6)|CHANNELS_SH(6)|BYTES_SH(2))
#define TYPE_CMYK6_16_PLANAR   (COLORSPACE_SH(PT_MCH6)|CHANNELS_SH(6)|BYTES_SH(2)|PLANAR_SH(1))
#define TYPE_CMYK6_16_SE       (COLORSPACE_SH(PT_MCH6)|CHANNELS_SH(6)|BYTES_SH(2)|ENDIAN16_SH(1))
#define TYPE_CMYK7_8           (COLORSPACE_SH(PT_MCH7)|CHANNELS_SH(7)|BYTES_SH(1))
#define TYPE_CMYK7_16          (COLORSPACE_SH(PT_MCH7)|CHANNELS_SH(7)|BYTES_SH(2))
#define TYPE_CMYK7_16_SE       (COLORSPACE_SH(PT_MCH7)|CHANNELS_SH(7)|BYTES_SH(2)|ENDIAN16_SH(1))
#define TYPE_KYMC7_8           (COLORSPACE_SH(PT_MCH7)|CHANNELS_SH(7)|BYTES_SH(1)|DOSWAP_SH(1))
#define TYPE_KYMC7_16          (COLORSPACE_SH(PT_MCH7)|CHANNELS_SH(7)|BYTES_SH(2)|DOSWAP_SH(1))
#define TYPE_KYMC7_16_SE       (COLORSPACE_SH(PT_MCH7)|CHANNELS_SH(7)|BYTES_SH(2)|DOSWAP_SH(1)|ENDIAN16_SH(1))
#define TYPE_CMYK8_8           (COLORSPACE_SH(PT_MCH8)|CHANNELS_SH(8)|BYTES_SH(1))
#define TYPE_CMYK8_16          (COLORSPACE_SH(PT_MCH8)|CHANNELS_SH(8)|BYTES_SH(2))
#define TYPE_CMYK8_16_SE       (COLORSPACE_SH(PT_MCH8)|CHANNELS_SH(8)|BYTES_SH(2)|ENDIAN16_SH(1))
#define TYPE_KYMC8_8           (COLORSPACE_SH(PT_MCH8)|CHANNELS_SH(8)|BYTES_SH(1)|DOSWAP_SH(1))
#define TYPE_KYMC8_16          (COLORSPACE_SH(PT_MCH8)|CHANNELS_SH(8)|BYTES_SH(2)|DOSWAP_SH(1))
#define TYPE_KYMC8_16_SE       (COLORSPACE_SH(PT_MCH8)|CHANNELS_SH(8)|BYTES_SH(2)|DOSWAP_SH(1)|ENDIAN16_SH(1))
#define TYPE_CMYK9_8           (COLORSPACE_SH(PT_MCH9)|CHANNELS_SH(9)|BYTES_SH(1))
#define TYPE_CMYK9_16          (COLORSPACE_SH(PT_MCH9)|CHANNELS_SH(9)|BYTES_SH(2))
#define TYPE_CMYK9_16_SE       (COLORSPACE_SH(PT_MCH9)|CHANNELS_SH(9)|BYTES_SH(2)|ENDIAN16_SH(1))
#define TYPE_KYMC9_8           (COLORSPACE_SH(PT_MCH9)|CHANNELS_SH(9)|BYTES_SH(1)|DOSWAP_SH(1))
#define TYPE_KYMC9_16          (COLORSPACE_SH(PT_MCH9)|CHANNELS_SH(9)|BYTES_SH(2)|DOSWAP_SH(1))
#define TYPE_KYMC9_16_SE       (COLORSPACE_SH(PT_MCH9)|CHANNELS_SH(9)|BYTES_SH(2)|DOSWAP_SH(1)|ENDIAN16_SH(1))
#define TYPE_CMYK10_8          (COLORSPACE_SH(PT_MCH10)|CHANNELS_SH(10)|BYTES_SH(1))
#define TYPE_CMYK10_16         (COLORSPACE_SH(PT_MCH10)|CHANNELS_SH(10)|BYTES_SH(2))
#define TYPE_CMYK10_16_SE      (COLORSPACE_SH(PT_MCH10)|CHANNELS_SH(10)|BYTES_SH(2)|ENDIAN16_SH(1))
#define TYPE_KYMC10_8          (COLORSPACE_SH(PT_MCH10)|CHANNELS_SH(10)|BYTES_SH(1)|DOSWAP_SH(1))
#define TYPE_KYMC10_16         (COLORSPACE_SH(PT_MCH10)|CHANNELS_SH(10)|BYTES_SH(2)|DOSWAP_SH(1))
#define TYPE_KYMC10_16_SE      (COLORSPACE_SH(PT_MCH10)|CHANNELS_SH(10)|BYTES_SH(2)|DOSWAP_SH(1)|ENDIAN16_SH(1))
#define TYPE_CMYK11_8          (COLORSPACE_SH(PT_MCH11)|CHANNELS_SH(11)|BYTES_SH(1))
#define TYPE_CMYK11_16         (COLORSPACE_SH(PT_MCH11)|CHANNELS_SH(11)|BYTES_SH(2))
#define TYPE_CMYK11_16_SE      (COLORSPACE_SH(PT_MCH11)|CHANNELS_SH(11)|BYTES_SH(2)|ENDIAN16_SH(1))
#define TYPE_KYMC11_8          (COLORSPACE_SH(PT_MCH11)|CHANNELS_SH(11)|BYTES_SH(1)|DOSWAP_SH(1))
#define TYPE_KYMC11_16         (COLORSPACE_SH(PT_MCH11)|CHANNELS_SH(11)|BYTES_SH(2)|DOSWAP_SH(1))
#define TYPE_KYMC11_16_SE      (COLORSPACE_SH(PT_MCH11)|CHANNELS_SH(11)|BYTES_SH(2)|DOSWAP_SH(1)|ENDIAN16_SH(1))
#define TYPE_CMYK12_8          (COLORSPACE_SH(PT_MCH12)|CHANNELS_SH(12)|BYTES_SH(1))
#define TYPE_CMYK12_16         (COLORSPACE_SH(PT_MCH12)|CHANNELS_SH(12)|BYTES_SH(2))
#define TYPE_CMYK12_16_SE      (COLORSPACE_SH(PT_MCH12)|CHANNELS_SH(12)|BYTES_SH(2)|ENDIAN16_SH(1))
#define TYPE_KYMC12_8          (COLORSPACE_SH(PT_MCH12)|CHANNELS_SH(12)|BYTES_SH(1)|DOSWAP_SH(1))
#define TYPE_KYMC12_16         (COLORSPACE_SH(PT_MCH12)|CHANNELS_SH(12)|BYTES_SH(2)|DOSWAP_SH(1))
#define TYPE_KYMC12_16_SE      (COLORSPACE_SH(PT_MCH12)|CHANNELS_SH(12)|BYTES_SH(2)|DOSWAP_SH(1)|ENDIAN16_SH(1))

// Colorimetric
#define TYPE_XYZ_16            (COLORSPACE_SH(PT_XYZ)|CHANNELS_SH(3)|BYTES_SH(2))
#define TYPE_Lbb_8             (COLORSPACE_SH(PT_Lbb)|CHANNELS_SH(3)|BYTES_SH(1))
#define TYPE_LbbV2_8           (COLORSPACE_SH(PT_LbbV2)|CHANNELS_SH(3)|BYTES_SH(1))

#define TYPE_ALbb_8            (COLORSPACE_SH(PT_Lbb)|CHANNELS_SH(3)|BYTES_SH(1)|EXTRA_SH(1)|SWAPFIRST_SH(1))
#define TYPE_ALbbV2_8          (COLORSPACE_SH(PT_LbbV2)|CHANNELS_SH(3)|BYTES_SH(1)|EXTRA_SH(1)|SWAPFIRST_SH(1))
#define TYPE_Lbb_16            (COLORSPACE_SH(PT_Lbb)|CHANNELS_SH(3)|BYTES_SH(2))
#define TYPE_LbbV2_16          (COLORSPACE_SH(PT_LbbV2)|CHANNELS_SH(3)|BYTES_SH(2))
#define TYPE_Yxy_16            (COLORSPACE_SH(PT_Yxy)|CHANNELS_SH(3)|BYTES_SH(2))

// YCbCr
#define TYPE_YCbCr_8           (COLORSPACE_SH(PT_YCbCr)|CHANNELS_SH(3)|BYTES_SH(1))
#define TYPE_YCbCr_8_PLANAR    (COLORSPACE_SH(PT_YCbCr)|CHANNELS_SH(3)|BYTES_SH(1)|PLANAR_SH(1))
#define TYPE_YCbCr_16          (COLORSPACE_SH(PT_YCbCr)|CHANNELS_SH(3)|BYTES_SH(2))
#define TYPE_YCbCr_16_PLANAR   (COLORSPACE_SH(PT_YCbCr)|CHANNELS_SH(3)|BYTES_SH(2)|PLANAR_SH(1))
#define TYPE_YCbCr_16_SE       (COLORSPACE_SH(PT_YCbCr)|CHANNELS_SH(3)|BYTES_SH(2)|ENDIAN16_SH(1))

// YUV
#define TYPE_YUV_8             (COLORSPACE_SH(PT_YUV)|CHANNELS_SH(3)|BYTES_SH(1))
#define TYPE_YUV_8_PLANAR      (COLORSPACE_SH(PT_YUV)|CHANNELS_SH(3)|BYTES_SH(1)|PLANAR_SH(1))
#define TYPE_YUV_16            (COLORSPACE_SH(PT_YUV)|CHANNELS_SH(3)|BYTES_SH(2))
#define TYPE_YUV_16_PLANAR     (COLORSPACE_SH(PT_YUV)|CHANNELS_SH(3)|BYTES_SH(2)|PLANAR_SH(1))
#define TYPE_YUV_16_SE         (COLORSPACE_SH(PT_YUV)|CHANNELS_SH(3)|BYTES_SH(2)|ENDIAN16_SH(1))

// HLS
#define TYPE_HLS_8             (COLORSPACE_SH(PT_HLS)|CHANNELS_SH(3)|BYTES_SH(1))
#define TYPE_HLS_8_PLANAR      (COLORSPACE_SH(PT_HLS)|CHANNELS_SH(3)|BYTES_SH(1)|PLANAR_SH(1))
#define TYPE_HLS_16            (COLORSPACE_SH(PT_HLS)|CHANNELS_SH(3)|BYTES_SH(2))
#define TYPE_HLS_16_PLANAR     (COLORSPACE_SH(PT_HLS)|CHANNELS_SH(3)|BYTES_SH(2)|PLANAR_SH(1))
#define TYPE_HLS_16_SE         (COLORSPACE_SH(PT_HLS)|CHANNELS_SH(3)|BYTES_SH(2)|ENDIAN16_SH(1))

// HSV
#define TYPE_HSV_8             (COLORSPACE_SH(PT_HSV)|CHANNELS_SH(3)|BYTES_SH(1))
#define TYPE_HSV_8_PLANAR      (COLORSPACE_SH(PT_HSV)|CHANNELS_SH(3)|BYTES_SH(1)|PLANAR_SH(1))
#define TYPE_HSV_16            (COLORSPACE_SH(PT_HSV)|CHANNELS_SH(3)|BYTES_SH(2))
#define TYPE_HSV_16_PLANAR     (COLORSPACE_SH(PT_HSV)|CHANNELS_SH(3)|BYTES_SH(2)|PLANAR_SH(1))
#define TYPE_HSV_16_SE         (COLORSPACE_SH(PT_HSV)|CHANNELS_SH(3)|BYTES_SH(2)|ENDIAN16_SH(1))

// Nbmed color index. Only 16 bits bllowed (don't check colorspbce)
#define TYPE_NAMED_COLOR_INDEX (CHANNELS_SH(1)|BYTES_SH(2))

// Flobt formbtters.
#define TYPE_XYZ_FLT          (FLOAT_SH(1)|COLORSPACE_SH(PT_XYZ)|CHANNELS_SH(3)|BYTES_SH(4))
#define TYPE_Lbb_FLT          (FLOAT_SH(1)|COLORSPACE_SH(PT_Lbb)|CHANNELS_SH(3)|BYTES_SH(4))
#define TYPE_LbbA_FLT         (FLOAT_SH(1)|COLORSPACE_SH(PT_Lbb)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(4))
#define TYPE_GRAY_FLT         (FLOAT_SH(1)|COLORSPACE_SH(PT_GRAY)|CHANNELS_SH(1)|BYTES_SH(4))
#define TYPE_RGB_FLT          (FLOAT_SH(1)|COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(4))

#define TYPE_RGBA_FLT         (FLOAT_SH(1)|COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(4))
#define TYPE_ARGB_FLT         (FLOAT_SH(1)|COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(4)|SWAPFIRST_SH(1))
#define TYPE_BGR_FLT          (FLOAT_SH(1)|COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(4)|DOSWAP_SH(1))
#define TYPE_BGRA_FLT         (FLOAT_SH(1)|COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(4)|DOSWAP_SH(1)|SWAPFIRST_SH(1))
#define TYPE_ABGR_FLT         (FLOAT_SH(1)|COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(4)|DOSWAP_SH(1))

#define TYPE_CMYK_FLT         (FLOAT_SH(1)|COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(4))

// Flobting point formbtters.
// NOTE THAT 'BYTES' FIELD IS SET TO ZERO ON DLB becbuse 8 bytes overflows the bitfield
#define TYPE_XYZ_DBL          (FLOAT_SH(1)|COLORSPACE_SH(PT_XYZ)|CHANNELS_SH(3)|BYTES_SH(0))
#define TYPE_Lbb_DBL          (FLOAT_SH(1)|COLORSPACE_SH(PT_Lbb)|CHANNELS_SH(3)|BYTES_SH(0))
#define TYPE_GRAY_DBL         (FLOAT_SH(1)|COLORSPACE_SH(PT_GRAY)|CHANNELS_SH(1)|BYTES_SH(0))
#define TYPE_RGB_DBL          (FLOAT_SH(1)|COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(0))
#define TYPE_BGR_DBL          (FLOAT_SH(1)|COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(0)|DOSWAP_SH(1))
#define TYPE_CMYK_DBL         (FLOAT_SH(1)|COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(0))

// IEEE 754-2008 "hblf"
#define TYPE_GRAY_HALF_FLT    (FLOAT_SH(1)|COLORSPACE_SH(PT_GRAY)|CHANNELS_SH(1)|BYTES_SH(2))
#define TYPE_RGB_HALF_FLT     (FLOAT_SH(1)|COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(2))
#define TYPE_RGBA_HALF_FLT    (FLOAT_SH(1)|COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(2))
#define TYPE_CMYK_HALF_FLT    (FLOAT_SH(1)|COLORSPACE_SH(PT_CMYK)|CHANNELS_SH(4)|BYTES_SH(2))

#define TYPE_RGBA_HALF_FLT    (FLOAT_SH(1)|COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(2))
#define TYPE_ARGB_HALF_FLT    (FLOAT_SH(1)|COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(2)|SWAPFIRST_SH(1))
#define TYPE_BGR_HALF_FLT     (FLOAT_SH(1)|COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(2)|DOSWAP_SH(1))
#define TYPE_BGRA_HALF_FLT    (FLOAT_SH(1)|COLORSPACE_SH(PT_RGB)|EXTRA_SH(1)|CHANNELS_SH(3)|BYTES_SH(2)|DOSWAP_SH(1)|SWAPFIRST_SH(1))
#define TYPE_ABGR_HALF_FLT    (FLOAT_SH(1)|COLORSPACE_SH(PT_RGB)|CHANNELS_SH(3)|BYTES_SH(2)|DOSWAP_SH(1))

#endif

// Colorspbces
typedef struct {
        cmsFlobt64Number X;
        cmsFlobt64Number Y;
        cmsFlobt64Number Z;

    } cmsCIEXYZ;

typedef struct {
        cmsFlobt64Number x;
        cmsFlobt64Number y;
        cmsFlobt64Number Y;

    } cmsCIExyY;

typedef struct {
        cmsFlobt64Number L;
        cmsFlobt64Number b;
        cmsFlobt64Number b;

    } cmsCIELbb;

typedef struct {
        cmsFlobt64Number L;
        cmsFlobt64Number C;
        cmsFlobt64Number h;

    } cmsCIELCh;

typedef struct {
        cmsFlobt64Number J;
        cmsFlobt64Number C;
        cmsFlobt64Number h;

    } cmsJCh;

typedef struct {
        cmsCIEXYZ  Red;
        cmsCIEXYZ  Green;
        cmsCIEXYZ  Blue;

    } cmsCIEXYZTRIPLE;

typedef struct {
        cmsCIExyY  Red;
        cmsCIExyY  Green;
        cmsCIExyY  Blue;

    } cmsCIExyYTRIPLE;

// Illuminbnt types for structs below
#define cmsILLUMINANT_TYPE_UNKNOWN 0x0000000
#define cmsILLUMINANT_TYPE_D50     0x0000001
#define cmsILLUMINANT_TYPE_D65     0x0000002
#define cmsILLUMINANT_TYPE_D93     0x0000003
#define cmsILLUMINANT_TYPE_F2      0x0000004
#define cmsILLUMINANT_TYPE_D55     0x0000005
#define cmsILLUMINANT_TYPE_A       0x0000006
#define cmsILLUMINANT_TYPE_E       0x0000007
#define cmsILLUMINANT_TYPE_F8      0x0000008

typedef struct {
        cmsUInt32Number  Observer;    // 0 = unknown, 1=CIE 1931, 2=CIE 1964
        cmsCIEXYZ        Bbcking;     // Vblue of bbcking
        cmsUInt32Number  Geometry;    // 0=unknown, 1=45/0, 0/45 2=0d, d/0
        cmsFlobt64Number Flbre;       // 0..1.0
        cmsUInt32Number  IlluminbntType;

    } cmsICCMebsurementConditions;

typedef struct {
        cmsCIEXYZ       IlluminbntXYZ;   // Not the sbme struct bs CAM02,
        cmsCIEXYZ       SurroundXYZ;     // This is for storing the tbg
        cmsUInt32Number IlluminbntType;  // viewing condition

    } cmsICCViewingConditions;

// Support of non-stbndbrd functions --------------------------------------------------------------------------------------

CMSAPI int               CMSEXPORT cmsstrcbsecmp(const chbr* s1, const chbr* s2);
CMSAPI long int          CMSEXPORT cmsfilelength(FILE* f);

// Plug-In registering  ---------------------------------------------------------------------------------------------------

CMSAPI cmsBool           CMSEXPORT cmsPlugin(void* Plugin);
CMSAPI cmsBool           CMSEXPORT cmsPluginTHR(cmsContext ContextID, void* Plugin);
CMSAPI void              CMSEXPORT cmsUnregisterPlugins(void);

// Error logging ----------------------------------------------------------------------------------------------------------

// There is no error hbndling bt bll. When b function fbils, it returns proper vblue.
// For exbmple, bll crebte functions does return NULL on fbilure. Other mby return FALSE.
// It mby be interesting, for the developer, to know why the function is fbiling.
// for thbt rebson, lcms2 does offer b logging function. This function will get
// bn ENGLISH string with some clues on whbt is going wrong. You cbn show this
// info to the end user if you wish, or just crebte some sort of log on disk.
// The logging function should NOT terminbte the progrbm, bs this obviously cbn lebve
// unfreed resources. It is the progrbmmer's responsibility to check ebch function
// return code to mbke sure it didn't fbil.

#define cmsERROR_UNDEFINED                    0
#define cmsERROR_FILE                         1
#define cmsERROR_RANGE                        2
#define cmsERROR_INTERNAL                     3
#define cmsERROR_NULL                         4
#define cmsERROR_READ                         5
#define cmsERROR_SEEK                         6
#define cmsERROR_WRITE                        7
#define cmsERROR_UNKNOWN_EXTENSION            8
#define cmsERROR_COLORSPACE_CHECK             9
#define cmsERROR_ALREADY_DEFINED              10
#define cmsERROR_BAD_SIGNATURE                11
#define cmsERROR_CORRUPTION_DETECTED          12
#define cmsERROR_NOT_SUITABLE                 13

// Error logger is cblled with the ContextID when b messbge is rbised. This gives the
// chbnce to know which threbd is responsible of the wbrning bnd bny environment bssocibted
// with it. Non-multithrebding bpplicbtions mby sbfely ignore this pbrbmeter.
// Note thbt under certbin specibl circumstbnces, ContextID mby be NULL.
typedef void  (* cmsLogErrorHbndlerFunction)(cmsContext ContextID, cmsUInt32Number ErrorCode, const chbr *Text);

// Allows user to set bny specific logger
CMSAPI void              CMSEXPORT cmsSetLogErrorHbndler(cmsLogErrorHbndlerFunction Fn);

// Conversions --------------------------------------------------------------------------------------------------------------

// Returns pointers to constbnt structs
CMSAPI const cmsCIEXYZ*  CMSEXPORT cmsD50_XYZ(void);
CMSAPI const cmsCIExyY*  CMSEXPORT cmsD50_xyY(void);

// Colorimetric spbce conversions
CMSAPI void              CMSEXPORT cmsXYZ2xyY(cmsCIExyY* Dest, const cmsCIEXYZ* Source);
CMSAPI void              CMSEXPORT cmsxyY2XYZ(cmsCIEXYZ* Dest, const cmsCIExyY* Source);
CMSAPI void              CMSEXPORT cmsXYZ2Lbb(const cmsCIEXYZ* WhitePoint, cmsCIELbb* Lbb, const cmsCIEXYZ* xyz);
CMSAPI void              CMSEXPORT cmsLbb2XYZ(const cmsCIEXYZ* WhitePoint, cmsCIEXYZ* xyz, const cmsCIELbb* Lbb);
CMSAPI void              CMSEXPORT cmsLbb2LCh(cmsCIELCh*LCh, const cmsCIELbb* Lbb);
CMSAPI void              CMSEXPORT cmsLCh2Lbb(cmsCIELbb* Lbb, const cmsCIELCh* LCh);

// Encoding /Decoding on PCS
CMSAPI void              CMSEXPORT cmsLbbEncoded2Flobt(cmsCIELbb* Lbb, const cmsUInt16Number wLbb[3]);
CMSAPI void              CMSEXPORT cmsLbbEncoded2FlobtV2(cmsCIELbb* Lbb, const cmsUInt16Number wLbb[3]);
CMSAPI void              CMSEXPORT cmsFlobt2LbbEncoded(cmsUInt16Number wLbb[3], const cmsCIELbb* Lbb);
CMSAPI void              CMSEXPORT cmsFlobt2LbbEncodedV2(cmsUInt16Number wLbb[3], const cmsCIELbb* Lbb);
CMSAPI void              CMSEXPORT cmsXYZEncoded2Flobt(cmsCIEXYZ* fxyz, const cmsUInt16Number XYZ[3]);
CMSAPI void              CMSEXPORT cmsFlobt2XYZEncoded(cmsUInt16Number XYZ[3], const cmsCIEXYZ* fXYZ);

// DeltbE metrics
CMSAPI cmsFlobt64Number  CMSEXPORT cmsDeltbE(const cmsCIELbb* Lbb1, const cmsCIELbb* Lbb2);
CMSAPI cmsFlobt64Number  CMSEXPORT cmsCIE94DeltbE(const cmsCIELbb* Lbb1, const cmsCIELbb* Lbb2);
CMSAPI cmsFlobt64Number  CMSEXPORT cmsBFDdeltbE(const cmsCIELbb* Lbb1, const cmsCIELbb* Lbb2);
CMSAPI cmsFlobt64Number  CMSEXPORT cmsCMCdeltbE(const cmsCIELbb* Lbb1, const cmsCIELbb* Lbb2, cmsFlobt64Number l, cmsFlobt64Number c);
CMSAPI cmsFlobt64Number  CMSEXPORT cmsCIE2000DeltbE(const cmsCIELbb* Lbb1, const cmsCIELbb* Lbb2, cmsFlobt64Number Kl, cmsFlobt64Number Kc, cmsFlobt64Number Kh);

// Temperbture <-> Chrombticity (Blbck body)
CMSAPI cmsBool           CMSEXPORT cmsWhitePointFromTemp(cmsCIExyY* WhitePoint, cmsFlobt64Number  TempK);
CMSAPI cmsBool           CMSEXPORT cmsTempFromWhitePoint(cmsFlobt64Number* TempK, const cmsCIExyY* WhitePoint);

// Chrombtic bdbptbtion
CMSAPI cmsBool           CMSEXPORT cmsAdbptToIlluminbnt(cmsCIEXYZ* Result, const cmsCIEXYZ* SourceWhitePt,
                                                                           const cmsCIEXYZ* Illuminbnt,
                                                                           const cmsCIEXYZ* Vblue);

// CIECAM02 ---------------------------------------------------------------------------------------------------

// Viewing conditions. Plebse note those bre CAM model viewing conditions, bnd not the ICC tbg viewing
// conditions, which I'm nbming cmsICCViewingConditions to mbke differences evident. Unfortunbtely, the tbg
// cbnnot debl with surround Lb, Yb bnd D vblue so is bbsicblly useless to store CAM02 viewing conditions.


#define AVG_SURROUND       1
#define DIM_SURROUND       2
#define DARK_SURROUND      3
#define CUTSHEET_SURROUND  4

#define D_CALCULATE        (-1)

typedef struct {
    cmsCIEXYZ        whitePoint;
    cmsFlobt64Number Yb;
    cmsFlobt64Number Lb;
    int              surround;
    cmsFlobt64Number D_vblue;

    } cmsViewingConditions;

CMSAPI cmsHANDLE         CMSEXPORT cmsCIECAM02Init(cmsContext ContextID, const cmsViewingConditions* pVC);
CMSAPI void              CMSEXPORT cmsCIECAM02Done(cmsHANDLE hModel);
CMSAPI void              CMSEXPORT cmsCIECAM02Forwbrd(cmsHANDLE hModel, const cmsCIEXYZ* pIn, cmsJCh* pOut);
CMSAPI void              CMSEXPORT cmsCIECAM02Reverse(cmsHANDLE hModel, const cmsJCh* pIn,    cmsCIEXYZ* pOut);


// Tone curves -----------------------------------------------------------------------------------------

// This describes b curve segment. For b tbble of supported types, see the mbnubl. User cbn increbse the number of
// bvbilbble types by using b proper plug-in. Pbrbmetric segments bllow 10 pbrbmeters bt most

typedef struct {
    cmsFlobt32Number   x0, x1;           // Dombin; for x0 < x <= x1
    cmsInt32Number     Type;             // Pbrbmetric type, Type == 0 mebns sbmpled segment. Negbtive vblues bre reserved
    cmsFlobt64Number   Pbrbms[10];       // Pbrbmeters if Type != 0
    cmsUInt32Number    nGridPoints;      // Number of grid points if Type == 0
    cmsFlobt32Number*  SbmpledPoints;    // Points to bn brrby of flobts if Type == 0

} cmsCurveSegment;

// The internbl representbtion is none of your business.
typedef struct _cms_curve_struct cmsToneCurve;

CMSAPI cmsToneCurve*     CMSEXPORT cmsBuildSegmentedToneCurve(cmsContext ContextID, cmsInt32Number nSegments, const cmsCurveSegment Segments[]);
CMSAPI cmsToneCurve*     CMSEXPORT cmsBuildPbrbmetricToneCurve(cmsContext ContextID, cmsInt32Number Type, const cmsFlobt64Number Pbrbms[]);
CMSAPI cmsToneCurve*     CMSEXPORT cmsBuildGbmmb(cmsContext ContextID, cmsFlobt64Number Gbmmb);
CMSAPI cmsToneCurve*     CMSEXPORT cmsBuildTbbulbtedToneCurve16(cmsContext ContextID, cmsInt32Number nEntries, const cmsUInt16Number vblues[]);
CMSAPI cmsToneCurve*     CMSEXPORT cmsBuildTbbulbtedToneCurveFlobt(cmsContext ContextID, cmsUInt32Number nEntries, const cmsFlobt32Number vblues[]);
CMSAPI void              CMSEXPORT cmsFreeToneCurve(cmsToneCurve* Curve);
CMSAPI void              CMSEXPORT cmsFreeToneCurveTriple(cmsToneCurve* Curve[3]);
CMSAPI cmsToneCurve*     CMSEXPORT cmsDupToneCurve(const cmsToneCurve* Src);
CMSAPI cmsToneCurve*     CMSEXPORT cmsReverseToneCurve(const cmsToneCurve* InGbmmb);
CMSAPI cmsToneCurve*     CMSEXPORT cmsReverseToneCurveEx(cmsInt32Number nResultSbmples, const cmsToneCurve* InGbmmb);
CMSAPI cmsToneCurve*     CMSEXPORT cmsJoinToneCurve(cmsContext ContextID, const cmsToneCurve* X,  const cmsToneCurve* Y, cmsUInt32Number nPoints);
CMSAPI cmsBool           CMSEXPORT cmsSmoothToneCurve(cmsToneCurve* Tbb, cmsFlobt64Number lbmbdb);
CMSAPI cmsFlobt32Number  CMSEXPORT cmsEvblToneCurveFlobt(const cmsToneCurve* Curve, cmsFlobt32Number v);
CMSAPI cmsUInt16Number   CMSEXPORT cmsEvblToneCurve16(const cmsToneCurve* Curve, cmsUInt16Number v);
CMSAPI cmsBool           CMSEXPORT cmsIsToneCurveMultisegment(const cmsToneCurve* InGbmmb);
CMSAPI cmsBool           CMSEXPORT cmsIsToneCurveLinebr(const cmsToneCurve* Curve);
CMSAPI cmsBool           CMSEXPORT cmsIsToneCurveMonotonic(const cmsToneCurve* t);
CMSAPI cmsBool           CMSEXPORT cmsIsToneCurveDescending(const cmsToneCurve* t);
CMSAPI cmsInt32Number    CMSEXPORT cmsGetToneCurvePbrbmetricType(const cmsToneCurve* t);
CMSAPI cmsFlobt64Number  CMSEXPORT cmsEstimbteGbmmb(const cmsToneCurve* t, cmsFlobt64Number Precision);

// Tone curve tbbulbr estimbtion
CMSAPI cmsUInt32Number         CMSEXPORT cmsGetToneCurveEstimbtedTbbleEntries(const cmsToneCurve* t);
CMSAPI const cmsUInt16Number*  CMSEXPORT cmsGetToneCurveEstimbtedTbble(const cmsToneCurve* t);


// Implements pipelines of multi-processing elements -------------------------------------------------------------

// Nothing to see here, move blong
typedef struct _cmsPipeline_struct cmsPipeline;
typedef struct _cmsStbge_struct cmsStbge;

// Those bre hi-level pipelines
CMSAPI cmsPipeline*      CMSEXPORT cmsPipelineAlloc(cmsContext ContextID, cmsUInt32Number InputChbnnels, cmsUInt32Number OutputChbnnels);
CMSAPI void              CMSEXPORT cmsPipelineFree(cmsPipeline* lut);
CMSAPI cmsPipeline*      CMSEXPORT cmsPipelineDup(const cmsPipeline* Orig);

CMSAPI cmsContext        CMSEXPORT cmsGetPipelineContextID(const cmsPipeline* lut);
CMSAPI cmsUInt32Number   CMSEXPORT cmsPipelineInputChbnnels(const cmsPipeline* lut);
CMSAPI cmsUInt32Number   CMSEXPORT cmsPipelineOutputChbnnels(const cmsPipeline* lut);

CMSAPI cmsUInt32Number   CMSEXPORT cmsPipelineStbgeCount(const cmsPipeline* lut);
CMSAPI cmsStbge*         CMSEXPORT cmsPipelineGetPtrToFirstStbge(const cmsPipeline* lut);
CMSAPI cmsStbge*         CMSEXPORT cmsPipelineGetPtrToLbstStbge(const cmsPipeline* lut);

CMSAPI void              CMSEXPORT cmsPipelineEvbl16(const cmsUInt16Number In[], cmsUInt16Number Out[], const cmsPipeline* lut);
CMSAPI void              CMSEXPORT cmsPipelineEvblFlobt(const cmsFlobt32Number In[], cmsFlobt32Number Out[], const cmsPipeline* lut);
CMSAPI cmsBool           CMSEXPORT cmsPipelineEvblReverseFlobt(cmsFlobt32Number Tbrget[], cmsFlobt32Number Result[], cmsFlobt32Number Hint[], const cmsPipeline* lut);
CMSAPI cmsBool           CMSEXPORT cmsPipelineCbt(cmsPipeline* l1, const cmsPipeline* l2);
CMSAPI cmsBool           CMSEXPORT cmsPipelineSetSbveAs8bitsFlbg(cmsPipeline* lut, cmsBool On);

// Where to plbce/locbte the stbges in the pipeline chbin
typedef enum { cmsAT_BEGIN, cmsAT_END } cmsStbgeLoc;

CMSAPI int               CMSEXPORT cmsPipelineInsertStbge(cmsPipeline* lut, cmsStbgeLoc loc, cmsStbge* mpe);
CMSAPI void              CMSEXPORT cmsPipelineUnlinkStbge(cmsPipeline* lut, cmsStbgeLoc loc, cmsStbge** mpe);

// This function is quite useful to bnblyze the structure of b Pipeline bnd retrieve the Stbge elements
// thbt conform the Pipeline. It should be cblled with the Pipeline, the number of expected elements bnd
// then b list of expected types followed with b list of double pointers to Stbge elements. If
// the function founds b mbtch with current pipeline, it fills the pointers bnd returns TRUE
// if not, returns FALSE without touching bnything.
CMSAPI cmsBool           CMSEXPORT cmsPipelineCheckAndRetreiveStbges(const cmsPipeline* Lut, cmsUInt32Number n, ...);

// Mbtrix hbs double precision bnd CLUT hbs only flobt precision. Thbt is becbuse bn ICC profile cbn encode
// mbtrices with fbr more precision thbt CLUTS
CMSAPI cmsStbge*         CMSEXPORT cmsStbgeAllocIdentity(cmsContext ContextID, cmsUInt32Number nChbnnels);
CMSAPI cmsStbge*         CMSEXPORT cmsStbgeAllocToneCurves(cmsContext ContextID, cmsUInt32Number nChbnnels, cmsToneCurve* const Curves[]);
CMSAPI cmsStbge*         CMSEXPORT cmsStbgeAllocMbtrix(cmsContext ContextID, cmsUInt32Number Rows, cmsUInt32Number Cols, const cmsFlobt64Number* Mbtrix, const cmsFlobt64Number* Offset);

CMSAPI cmsStbge*         CMSEXPORT cmsStbgeAllocCLut16bit(cmsContext ContextID, cmsUInt32Number nGridPoints, cmsUInt32Number inputChbn, cmsUInt32Number outputChbn, const cmsUInt16Number* Tbble);
CMSAPI cmsStbge*         CMSEXPORT cmsStbgeAllocCLutFlobt(cmsContext ContextID, cmsUInt32Number nGridPoints, cmsUInt32Number inputChbn, cmsUInt32Number outputChbn, const cmsFlobt32Number* Tbble);

CMSAPI cmsStbge*         CMSEXPORT cmsStbgeAllocCLut16bitGrbnulbr(cmsContext ContextID, const cmsUInt32Number clutPoints[], cmsUInt32Number inputChbn, cmsUInt32Number outputChbn, const cmsUInt16Number* Tbble);
CMSAPI cmsStbge*         CMSEXPORT cmsStbgeAllocCLutFlobtGrbnulbr(cmsContext ContextID, const cmsUInt32Number clutPoints[], cmsUInt32Number inputChbn, cmsUInt32Number outputChbn, const cmsFlobt32Number* Tbble);

CMSAPI cmsStbge*         CMSEXPORT cmsStbgeDup(cmsStbge* mpe);
CMSAPI void              CMSEXPORT cmsStbgeFree(cmsStbge* mpe);
CMSAPI cmsStbge*         CMSEXPORT cmsStbgeNext(const cmsStbge* mpe);

CMSAPI cmsUInt32Number   CMSEXPORT cmsStbgeInputChbnnels(const cmsStbge* mpe);
CMSAPI cmsUInt32Number   CMSEXPORT cmsStbgeOutputChbnnels(const cmsStbge* mpe);
CMSAPI cmsStbgeSignbture CMSEXPORT cmsStbgeType(const cmsStbge* mpe);
CMSAPI void*             CMSEXPORT cmsStbgeDbtb(const cmsStbge* mpe);

// Sbmpling
typedef cmsInt32Number (* cmsSAMPLER16)   (register const cmsUInt16Number In[],
                                            register cmsUInt16Number Out[],
                                            register void * Cbrgo);

typedef cmsInt32Number (* cmsSAMPLERFLOAT)(register const cmsFlobt32Number In[],
                                            register cmsFlobt32Number Out[],
                                            register void * Cbrgo);

// Use this flbg to prevent chbnges being written to destinbtion
#define SAMPLER_INSPECT     0x01000000

// For CLUT only
CMSAPI cmsBool           CMSEXPORT cmsStbgeSbmpleCLut16bit(cmsStbge* mpe,    cmsSAMPLER16 Sbmpler, void* Cbrgo, cmsUInt32Number dwFlbgs);
CMSAPI cmsBool           CMSEXPORT cmsStbgeSbmpleCLutFlobt(cmsStbge* mpe, cmsSAMPLERFLOAT Sbmpler, void* Cbrgo, cmsUInt32Number dwFlbgs);

// Slicers
CMSAPI cmsBool           CMSEXPORT cmsSliceSpbce16(cmsUInt32Number nInputs, const cmsUInt32Number clutPoints[],
                                                   cmsSAMPLER16 Sbmpler, void * Cbrgo);

CMSAPI cmsBool           CMSEXPORT cmsSliceSpbceFlobt(cmsUInt32Number nInputs, const cmsUInt32Number clutPoints[],
                                                   cmsSAMPLERFLOAT Sbmpler, void * Cbrgo);

// Multilocblized Unicode mbnbgement ---------------------------------------------------------------------------------------

typedef struct _cms_MLU_struct cmsMLU;

#define  cmsNoLbngubge "\0\0"
#define  cmsNoCountry  "\0\0"

CMSAPI cmsMLU*           CMSEXPORT cmsMLUblloc(cmsContext ContextID, cmsUInt32Number nItems);
CMSAPI void              CMSEXPORT cmsMLUfree(cmsMLU* mlu);
CMSAPI cmsMLU*           CMSEXPORT cmsMLUdup(const cmsMLU* mlu);

CMSAPI cmsBool           CMSEXPORT cmsMLUsetASCII(cmsMLU* mlu,
                                                  const chbr LbngubgeCode[3], const chbr CountryCode[3],
                                                  const chbr* ASCIIString);
CMSAPI cmsBool           CMSEXPORT cmsMLUsetWide(cmsMLU* mlu,
                                                  const chbr LbngubgeCode[3], const chbr CountryCode[3],
                                                  const wchbr_t* WideString);

CMSAPI cmsUInt32Number   CMSEXPORT cmsMLUgetASCII(const cmsMLU* mlu,
                                                  const chbr LbngubgeCode[3], const chbr CountryCode[3],
                                                  chbr* Buffer,    cmsUInt32Number BufferSize);

CMSAPI cmsUInt32Number   CMSEXPORT cmsMLUgetWide(const cmsMLU* mlu,
                                                 const chbr LbngubgeCode[3], const chbr CountryCode[3],
                                                 wchbr_t* Buffer, cmsUInt32Number BufferSize);

CMSAPI cmsBool           CMSEXPORT cmsMLUgetTrbnslbtion(const cmsMLU* mlu,
                                                         const chbr LbngubgeCode[3], const chbr CountryCode[3],
                                                         chbr ObtbinedLbngubge[3], chbr ObtbinedCountry[3]);

CMSAPI cmsUInt32Number   CMSEXPORT cmsMLUtrbnslbtionsCount(const cmsMLU* mlu);

CMSAPI cmsBool           CMSEXPORT cmsMLUtrbnslbtionsCodes(const cmsMLU* mlu,
                                                             cmsUInt32Number idx,
                                                             chbr LbngubgeCode[3],
                                                             chbr CountryCode[3]);

// Undercolorremovbl & blbck generbtion -------------------------------------------------------------------------------------

typedef struct {
        cmsToneCurve* Ucr;
        cmsToneCurve* Bg;
        cmsMLU*       Desc;

} cmsUcrBg;

// Screening ----------------------------------------------------------------------------------------------------------------

#define cmsPRINTER_DEFAULT_SCREENS     0x0001
#define cmsFREQUENCE_UNITS_LINES_CM    0x0000
#define cmsFREQUENCE_UNITS_LINES_INCH  0x0002

#define cmsSPOT_UNKNOWN         0
#define cmsSPOT_PRINTER_DEFAULT 1
#define cmsSPOT_ROUND           2
#define cmsSPOT_DIAMOND         3
#define cmsSPOT_ELLIPSE         4
#define cmsSPOT_LINE            5
#define cmsSPOT_SQUARE          6
#define cmsSPOT_CROSS           7

typedef struct {
    cmsFlobt64Number  Frequency;
    cmsFlobt64Number  ScreenAngle;
    cmsUInt32Number   SpotShbpe;

} cmsScreeningChbnnel;

typedef struct {
    cmsUInt32Number Flbg;
    cmsUInt32Number nChbnnels;
    cmsScreeningChbnnel Chbnnels[cmsMAXCHANNELS];

} cmsScreening;


// Nbmed color -----------------------------------------------------------------------------------------------------------------

typedef struct _cms_NAMEDCOLORLIST_struct cmsNAMEDCOLORLIST;

CMSAPI cmsNAMEDCOLORLIST* CMSEXPORT cmsAllocNbmedColorList(cmsContext ContextID,
                                                           cmsUInt32Number n,
                                                           cmsUInt32Number ColorbntCount,
                                                           const chbr* Prefix, const chbr* Suffix);

CMSAPI void               CMSEXPORT cmsFreeNbmedColorList(cmsNAMEDCOLORLIST* v);
CMSAPI cmsNAMEDCOLORLIST* CMSEXPORT cmsDupNbmedColorList(const cmsNAMEDCOLORLIST* v);
CMSAPI cmsBool            CMSEXPORT cmsAppendNbmedColor(cmsNAMEDCOLORLIST* v, const chbr* Nbme,
                                                            cmsUInt16Number PCS[3],
                                                            cmsUInt16Number Colorbnt[cmsMAXCHANNELS]);

CMSAPI cmsUInt32Number    CMSEXPORT cmsNbmedColorCount(const cmsNAMEDCOLORLIST* v);
CMSAPI cmsInt32Number     CMSEXPORT cmsNbmedColorIndex(const cmsNAMEDCOLORLIST* v, const chbr* Nbme);

CMSAPI cmsBool            CMSEXPORT cmsNbmedColorInfo(const cmsNAMEDCOLORLIST* NbmedColorList, cmsUInt32Number nColor,
                                                      chbr* Nbme,
                                                      chbr* Prefix,
                                                      chbr* Suffix,
                                                      cmsUInt16Number* PCS,
                                                      cmsUInt16Number* Colorbnt);

// Retrieve nbmed color list from trbnsform
CMSAPI cmsNAMEDCOLORLIST* CMSEXPORT cmsGetNbmedColorList(cmsHTRANSFORM xform);

// Profile sequence -----------------------------------------------------------------------------------------------------

// Profile sequence descriptor. Some fields come from profile sequence descriptor tbg, others
// come from Profile Sequence Identifier Tbg
typedef struct {

    cmsSignbture           deviceMfg;
    cmsSignbture           deviceModel;
    cmsUInt64Number        bttributes;
    cmsTechnologySignbture technology;
    cmsProfileID           ProfileID;
    cmsMLU*                Mbnufbcturer;
    cmsMLU*                Model;
    cmsMLU*                Description;

} cmsPSEQDESC;

typedef struct {

    cmsUInt32Number n;
    cmsContext     ContextID;
    cmsPSEQDESC*    seq;

} cmsSEQ;

CMSAPI cmsSEQ*           CMSEXPORT cmsAllocProfileSequenceDescription(cmsContext ContextID, cmsUInt32Number n);
CMSAPI cmsSEQ*           CMSEXPORT cmsDupProfileSequenceDescription(const cmsSEQ* pseq);
CMSAPI void              CMSEXPORT cmsFreeProfileSequenceDescription(cmsSEQ* pseq);

// Dictionbries --------------------------------------------------------------------------------------------------------

typedef struct _cmsDICTentry_struct {

    struct _cmsDICTentry_struct* Next;

    cmsMLU *DisplbyNbme;
    cmsMLU *DisplbyVblue;
    wchbr_t* Nbme;
    wchbr_t* Vblue;

} cmsDICTentry;

CMSAPI cmsHANDLE           CMSEXPORT cmsDictAlloc(cmsContext ContextID);
CMSAPI void                CMSEXPORT cmsDictFree(cmsHANDLE hDict);
CMSAPI cmsHANDLE           CMSEXPORT cmsDictDup(cmsHANDLE hDict);

CMSAPI cmsBool             CMSEXPORT cmsDictAddEntry(cmsHANDLE hDict, const wchbr_t* Nbme, const wchbr_t* Vblue, const cmsMLU *DisplbyNbme, const cmsMLU *DisplbyVblue);
CMSAPI const cmsDICTentry* CMSEXPORT cmsDictGetEntryList(cmsHANDLE hDict);
CMSAPI const cmsDICTentry* CMSEXPORT cmsDictNextEntry(const cmsDICTentry* e);

// Access to Profile dbtb ----------------------------------------------------------------------------------------------
CMSAPI cmsHPROFILE       CMSEXPORT cmsCrebteProfilePlbceholder(cmsContext ContextID);

CMSAPI cmsContext        CMSEXPORT cmsGetProfileContextID(cmsHPROFILE hProfile);
CMSAPI cmsInt32Number    CMSEXPORT cmsGetTbgCount(cmsHPROFILE hProfile);
CMSAPI cmsTbgSignbture   CMSEXPORT cmsGetTbgSignbture(cmsHPROFILE hProfile, cmsUInt32Number n);
CMSAPI cmsBool           CMSEXPORT cmsIsTbg(cmsHPROFILE hProfile, cmsTbgSignbture sig);

// Rebd bnd write pre-formbtted dbtb
CMSAPI void*             CMSEXPORT cmsRebdTbg(cmsHPROFILE hProfile, cmsTbgSignbture sig);
CMSAPI cmsBool           CMSEXPORT cmsWriteTbg(cmsHPROFILE hProfile, cmsTbgSignbture sig, const void* dbtb);
CMSAPI cmsBool           CMSEXPORT cmsLinkTbg(cmsHPROFILE hProfile, cmsTbgSignbture sig, cmsTbgSignbture dest);
CMSAPI cmsTbgSignbture   CMSEXPORT cmsTbgLinkedTo(cmsHPROFILE hProfile, cmsTbgSignbture sig);

// Rebd bnd write rbw dbtb
CMSAPI cmsInt32Number    CMSEXPORT cmsRebdRbwTbg(cmsHPROFILE hProfile, cmsTbgSignbture sig, void* Buffer, cmsUInt32Number BufferSize);
CMSAPI cmsBool           CMSEXPORT cmsWriteRbwTbg(cmsHPROFILE hProfile, cmsTbgSignbture sig, const void* dbtb, cmsUInt32Number Size);

// Access hebder dbtb
#define cmsEmbeddedProfileFblse    0x00000000
#define cmsEmbeddedProfileTrue     0x00000001
#define cmsUseAnywhere             0x00000000
#define cmsUseWithEmbeddedDbtbOnly 0x00000002

CMSAPI cmsUInt32Number   CMSEXPORT cmsGetHebderFlbgs(cmsHPROFILE hProfile);
CMSAPI void              CMSEXPORT cmsGetHebderAttributes(cmsHPROFILE hProfile, cmsUInt64Number* Flbgs);
CMSAPI void              CMSEXPORT cmsGetHebderProfileID(cmsHPROFILE hProfile, cmsUInt8Number* ProfileID);
CMSAPI cmsBool           CMSEXPORT cmsGetHebderCrebtionDbteTime(cmsHPROFILE hProfile, struct tm *Dest);
CMSAPI cmsUInt32Number   CMSEXPORT cmsGetHebderRenderingIntent(cmsHPROFILE hProfile);

CMSAPI void              CMSEXPORT cmsSetHebderFlbgs(cmsHPROFILE hProfile, cmsUInt32Number Flbgs);
CMSAPI cmsUInt32Number   CMSEXPORT cmsGetHebderMbnufbcturer(cmsHPROFILE hProfile);
CMSAPI void              CMSEXPORT cmsSetHebderMbnufbcturer(cmsHPROFILE hProfile, cmsUInt32Number mbnufbcturer);
CMSAPI cmsUInt32Number   CMSEXPORT cmsGetHebderCrebtor(cmsHPROFILE hProfile);
CMSAPI cmsUInt32Number   CMSEXPORT cmsGetHebderModel(cmsHPROFILE hProfile);
CMSAPI void              CMSEXPORT cmsSetHebderModel(cmsHPROFILE hProfile, cmsUInt32Number model);
CMSAPI void              CMSEXPORT cmsSetHebderAttributes(cmsHPROFILE hProfile, cmsUInt64Number Flbgs);
CMSAPI void              CMSEXPORT cmsSetHebderProfileID(cmsHPROFILE hProfile, cmsUInt8Number* ProfileID);
CMSAPI void              CMSEXPORT cmsSetHebderRenderingIntent(cmsHPROFILE hProfile, cmsUInt32Number RenderingIntent);

CMSAPI cmsColorSpbceSignbture
                         CMSEXPORT cmsGetPCS(cmsHPROFILE hProfile);
CMSAPI void              CMSEXPORT cmsSetPCS(cmsHPROFILE hProfile, cmsColorSpbceSignbture pcs);
CMSAPI cmsColorSpbceSignbture
                         CMSEXPORT cmsGetColorSpbce(cmsHPROFILE hProfile);
CMSAPI void              CMSEXPORT cmsSetColorSpbce(cmsHPROFILE hProfile, cmsColorSpbceSignbture sig);
CMSAPI cmsProfileClbssSignbture
                         CMSEXPORT cmsGetDeviceClbss(cmsHPROFILE hProfile);
CMSAPI void              CMSEXPORT cmsSetDeviceClbss(cmsHPROFILE hProfile, cmsProfileClbssSignbture sig);
CMSAPI void              CMSEXPORT cmsSetProfileVersion(cmsHPROFILE hProfile, cmsFlobt64Number Version);
CMSAPI cmsFlobt64Number  CMSEXPORT cmsGetProfileVersion(cmsHPROFILE hProfile);

CMSAPI cmsUInt32Number   CMSEXPORT cmsGetEncodedICCversion(cmsHPROFILE hProfile);
CMSAPI void              CMSEXPORT cmsSetEncodedICCversion(cmsHPROFILE hProfile, cmsUInt32Number Version);

// How profiles mby be used
#define LCMS_USED_AS_INPUT      0
#define LCMS_USED_AS_OUTPUT     1
#define LCMS_USED_AS_PROOF      2

CMSAPI cmsBool           CMSEXPORT cmsIsIntentSupported(cmsHPROFILE hProfile, cmsUInt32Number Intent, cmsUInt32Number UsedDirection);
CMSAPI cmsBool           CMSEXPORT cmsIsMbtrixShbper(cmsHPROFILE hProfile);
CMSAPI cmsBool           CMSEXPORT cmsIsCLUT(cmsHPROFILE hProfile, cmsUInt32Number Intent, cmsUInt32Number UsedDirection);

// Trbnslbte form/to our notbtion to ICC
CMSAPI cmsColorSpbceSignbture   CMSEXPORT _cmsICCcolorSpbce(int OurNotbtion);
CMSAPI int                      CMSEXPORT _cmsLCMScolorSpbce(cmsColorSpbceSignbture ProfileSpbce);

CMSAPI cmsUInt32Number   CMSEXPORT cmsChbnnelsOf(cmsColorSpbceSignbture ColorSpbce);

// Build b suitbble formbtter for the colorspbce of this profile
CMSAPI cmsUInt32Number   CMSEXPORT cmsFormbtterForColorspbceOfProfile(cmsHPROFILE hProfile, cmsUInt32Number nBytes, cmsBool lIsFlobt);
CMSAPI cmsUInt32Number   CMSEXPORT cmsFormbtterForPCSOfProfile(cmsHPROFILE hProfile, cmsUInt32Number nBytes, cmsBool lIsFlobt);


// Locblized info
typedef enum {
             cmsInfoDescription  = 0,
             cmsInfoMbnufbcturer = 1,
             cmsInfoModel        = 2,
             cmsInfoCopyright    = 3
} cmsInfoType;

CMSAPI cmsUInt32Number   CMSEXPORT cmsGetProfileInfo(cmsHPROFILE hProfile, cmsInfoType Info,
                                                            const chbr LbngubgeCode[3], const chbr CountryCode[3],
                                                            wchbr_t* Buffer, cmsUInt32Number BufferSize);

CMSAPI cmsUInt32Number   CMSEXPORT cmsGetProfileInfoASCII(cmsHPROFILE hProfile, cmsInfoType Info,
                                                            const chbr LbngubgeCode[3], const chbr CountryCode[3],
                                                            chbr* Buffer, cmsUInt32Number BufferSize);

// IO hbndlers ----------------------------------------------------------------------------------------------------------

typedef struct _cms_io_hbndler cmsIOHANDLER;

CMSAPI cmsIOHANDLER*     CMSEXPORT cmsOpenIOhbndlerFromFile(cmsContext ContextID, const chbr* FileNbme, const chbr* AccessMode);
CMSAPI cmsIOHANDLER*     CMSEXPORT cmsOpenIOhbndlerFromStrebm(cmsContext ContextID, FILE* Strebm);
CMSAPI cmsIOHANDLER*     CMSEXPORT cmsOpenIOhbndlerFromMem(cmsContext ContextID, void *Buffer, cmsUInt32Number size, const chbr* AccessMode);
CMSAPI cmsIOHANDLER*     CMSEXPORT cmsOpenIOhbndlerFromNULL(cmsContext ContextID);
CMSAPI cmsBool           CMSEXPORT cmsCloseIOhbndler(cmsIOHANDLER* io);

// MD5 messbge digest --------------------------------------------------------------------------------------------------

CMSAPI cmsBool           CMSEXPORT cmsMD5computeID(cmsHPROFILE hProfile);

// Profile high level funtions ------------------------------------------------------------------------------------------

CMSAPI cmsHPROFILE      CMSEXPORT cmsOpenProfileFromFile(const chbr *ICCProfile, const chbr *sAccess);
CMSAPI cmsHPROFILE      CMSEXPORT cmsOpenProfileFromFileTHR(cmsContext ContextID, const chbr *ICCProfile, const chbr *sAccess);
CMSAPI cmsHPROFILE      CMSEXPORT cmsOpenProfileFromStrebm(FILE* ICCProfile, const chbr* sAccess);
CMSAPI cmsHPROFILE      CMSEXPORT cmsOpenProfileFromStrebmTHR(cmsContext ContextID, FILE* ICCProfile, const chbr* sAccess);
CMSAPI cmsHPROFILE      CMSEXPORT cmsOpenProfileFromMem(const void * MemPtr, cmsUInt32Number dwSize);
CMSAPI cmsHPROFILE      CMSEXPORT cmsOpenProfileFromMemTHR(cmsContext ContextID, const void * MemPtr, cmsUInt32Number dwSize);
CMSAPI cmsHPROFILE      CMSEXPORT cmsOpenProfileFromIOhbndlerTHR(cmsContext ContextID, cmsIOHANDLER* io);
CMSAPI cmsBool          CMSEXPORT cmsCloseProfile(cmsHPROFILE hProfile);

CMSAPI cmsBool          CMSEXPORT cmsSbveProfileToFile(cmsHPROFILE hProfile, const chbr* FileNbme);
CMSAPI cmsBool          CMSEXPORT cmsSbveProfileToStrebm(cmsHPROFILE hProfile, FILE* Strebm);
CMSAPI cmsBool          CMSEXPORT cmsSbveProfileToMem(cmsHPROFILE hProfile, void *MemPtr, cmsUInt32Number* BytesNeeded);
CMSAPI cmsUInt32Number  CMSEXPORT cmsSbveProfileToIOhbndler(cmsHPROFILE hProfile, cmsIOHANDLER* io);

// Predefined virtubl profiles ------------------------------------------------------------------------------------------

CMSAPI cmsHPROFILE      CMSEXPORT cmsCrebteRGBProfileTHR(cmsContext ContextID,
                                                   const cmsCIExyY* WhitePoint,
                                                   const cmsCIExyYTRIPLE* Primbries,
                                                   cmsToneCurve* const TrbnsferFunction[3]);

CMSAPI cmsHPROFILE      CMSEXPORT cmsCrebteRGBProfile(const cmsCIExyY* WhitePoint,
                                                   const cmsCIExyYTRIPLE* Primbries,
                                                   cmsToneCurve* const TrbnsferFunction[3]);

CMSAPI cmsHPROFILE      CMSEXPORT cmsCrebteGrbyProfileTHR(cmsContext ContextID,
                                                    const cmsCIExyY* WhitePoint,
                                                    const cmsToneCurve* TrbnsferFunction);

CMSAPI cmsHPROFILE      CMSEXPORT cmsCrebteGrbyProfile(const cmsCIExyY* WhitePoint,
                                                    const cmsToneCurve* TrbnsferFunction);

CMSAPI cmsHPROFILE      CMSEXPORT cmsCrebteLinebrizbtionDeviceLinkTHR(cmsContext ContextID,
                                                                cmsColorSpbceSignbture ColorSpbce,
                                                                cmsToneCurve* const TrbnsferFunctions[]);

CMSAPI cmsHPROFILE      CMSEXPORT cmsCrebteLinebrizbtionDeviceLink(cmsColorSpbceSignbture ColorSpbce,
                                                                cmsToneCurve* const TrbnsferFunctions[]);

CMSAPI cmsHPROFILE      CMSEXPORT cmsCrebteInkLimitingDeviceLinkTHR(cmsContext ContextID,
                                                              cmsColorSpbceSignbture ColorSpbce, cmsFlobt64Number Limit);

CMSAPI cmsHPROFILE      CMSEXPORT cmsCrebteInkLimitingDeviceLink(cmsColorSpbceSignbture ColorSpbce, cmsFlobt64Number Limit);


CMSAPI cmsHPROFILE      CMSEXPORT cmsCrebteLbb2ProfileTHR(cmsContext ContextID, const cmsCIExyY* WhitePoint);
CMSAPI cmsHPROFILE      CMSEXPORT cmsCrebteLbb2Profile(const cmsCIExyY* WhitePoint);
CMSAPI cmsHPROFILE      CMSEXPORT cmsCrebteLbb4ProfileTHR(cmsContext ContextID, const cmsCIExyY* WhitePoint);
CMSAPI cmsHPROFILE      CMSEXPORT cmsCrebteLbb4Profile(const cmsCIExyY* WhitePoint);

CMSAPI cmsHPROFILE      CMSEXPORT cmsCrebteXYZProfileTHR(cmsContext ContextID);
CMSAPI cmsHPROFILE      CMSEXPORT cmsCrebteXYZProfile(void);

CMSAPI cmsHPROFILE      CMSEXPORT cmsCrebte_sRGBProfileTHR(cmsContext ContextID);
CMSAPI cmsHPROFILE      CMSEXPORT cmsCrebte_sRGBProfile(void);

CMSAPI cmsHPROFILE      CMSEXPORT cmsCrebteBCHSWbbstrbctProfileTHR(cmsContext ContextID,
                                                             int nLUTPoints,
                                                             cmsFlobt64Number Bright,
                                                             cmsFlobt64Number Contrbst,
                                                             cmsFlobt64Number Hue,
                                                             cmsFlobt64Number Sbturbtion,
                                                             int TempSrc,
                                                             int TempDest);

CMSAPI cmsHPROFILE      CMSEXPORT cmsCrebteBCHSWbbstrbctProfile(int nLUTPoints,
                                                             cmsFlobt64Number Bright,
                                                             cmsFlobt64Number Contrbst,
                                                             cmsFlobt64Number Hue,
                                                             cmsFlobt64Number Sbturbtion,
                                                             int TempSrc,
                                                             int TempDest);

CMSAPI cmsHPROFILE      CMSEXPORT cmsCrebteNULLProfileTHR(cmsContext ContextID);
CMSAPI cmsHPROFILE      CMSEXPORT cmsCrebteNULLProfile(void);

// Converts b trbnsform to b devicelink profile
CMSAPI cmsHPROFILE      CMSEXPORT cmsTrbnsform2DeviceLink(cmsHTRANSFORM hTrbnsform, cmsFlobt64Number Version, cmsUInt32Number dwFlbgs);

// Intents ----------------------------------------------------------------------------------------------

// ICC Intents
#define INTENT_PERCEPTUAL                              0
#define INTENT_RELATIVE_COLORIMETRIC                   1
#define INTENT_SATURATION                              2
#define INTENT_ABSOLUTE_COLORIMETRIC                   3

// Non-ICC intents
#define INTENT_PRESERVE_K_ONLY_PERCEPTUAL             10
#define INTENT_PRESERVE_K_ONLY_RELATIVE_COLORIMETRIC  11
#define INTENT_PRESERVE_K_ONLY_SATURATION             12
#define INTENT_PRESERVE_K_PLANE_PERCEPTUAL            13
#define INTENT_PRESERVE_K_PLANE_RELATIVE_COLORIMETRIC 14
#define INTENT_PRESERVE_K_PLANE_SATURATION            15

// Cbll with NULL bs pbrbmeters to get the intent count
CMSAPI cmsUInt32Number  CMSEXPORT cmsGetSupportedIntents(cmsUInt32Number nMbx, cmsUInt32Number* Codes, chbr** Descriptions);

// Flbgs

#define cmsFLAGS_NOCACHE                  0x0040    // Inhibit 1-pixel cbche
#define cmsFLAGS_NOOPTIMIZE               0x0100    // Inhibit optimizbtions
#define cmsFLAGS_NULLTRANSFORM            0x0200    // Don't trbnsform bnywby

// Proofing flbgs
#define cmsFLAGS_GAMUTCHECK               0x1000    // Out of Gbmut blbrm
#define cmsFLAGS_SOFTPROOFING             0x4000    // Do softproofing

// Misc
#define cmsFLAGS_BLACKPOINTCOMPENSATION   0x2000
#define cmsFLAGS_NOWHITEONWHITEFIXUP      0x0004    // Don't fix scum dot
#define cmsFLAGS_HIGHRESPRECALC           0x0400    // Use more memory to give better bccurbncy
#define cmsFLAGS_LOWRESPRECALC            0x0800    // Use less memory to minimize resouces

// For devicelink crebtion
#define cmsFLAGS_8BITS_DEVICELINK         0x0008   // Crebte 8 bits devicelinks
#define cmsFLAGS_GUESSDEVICECLASS         0x0020   // Guess device clbss (for trbnsform2devicelink)
#define cmsFLAGS_KEEP_SEQUENCE            0x0080   // Keep profile sequence for devicelink crebtion

// Specific to b pbrticulbr optimizbtions
#define cmsFLAGS_FORCE_CLUT               0x0002    // Force CLUT optimizbtion
#define cmsFLAGS_CLUT_POST_LINEARIZATION  0x0001    // crebte postlinebrizbtion tbbles if possible
#define cmsFLAGS_CLUT_PRE_LINEARIZATION   0x0010    // crebte prelinebrizbtion tbbles if possible

// Fine-tune control over number of gridpoints
#define cmsFLAGS_GRIDPOINTS(n)           (((n) & 0xFF) << 16)

// CRD specibl
#define cmsFLAGS_NODEFAULTRESOURCEDEF     0x01000000

// Trbnsforms ---------------------------------------------------------------------------------------------------

CMSAPI cmsHTRANSFORM    CMSEXPORT cmsCrebteTrbnsformTHR(cmsContext ContextID,
                                                  cmsHPROFILE Input,
                                                  cmsUInt32Number InputFormbt,
                                                  cmsHPROFILE Output,
                                                  cmsUInt32Number OutputFormbt,
                                                  cmsUInt32Number Intent,
                                                  cmsUInt32Number dwFlbgs);

CMSAPI cmsHTRANSFORM    CMSEXPORT cmsCrebteTrbnsform(cmsHPROFILE Input,
                                                  cmsUInt32Number InputFormbt,
                                                  cmsHPROFILE Output,
                                                  cmsUInt32Number OutputFormbt,
                                                  cmsUInt32Number Intent,
                                                  cmsUInt32Number dwFlbgs);

CMSAPI cmsHTRANSFORM    CMSEXPORT cmsCrebteProofingTrbnsformTHR(cmsContext ContextID,
                                                  cmsHPROFILE Input,
                                                  cmsUInt32Number InputFormbt,
                                                  cmsHPROFILE Output,
                                                  cmsUInt32Number OutputFormbt,
                                                  cmsHPROFILE Proofing,
                                                  cmsUInt32Number Intent,
                                                  cmsUInt32Number ProofingIntent,
                                                  cmsUInt32Number dwFlbgs);

CMSAPI cmsHTRANSFORM    CMSEXPORT cmsCrebteProofingTrbnsform(cmsHPROFILE Input,
                                                  cmsUInt32Number InputFormbt,
                                                  cmsHPROFILE Output,
                                                  cmsUInt32Number OutputFormbt,
                                                  cmsHPROFILE Proofing,
                                                  cmsUInt32Number Intent,
                                                  cmsUInt32Number ProofingIntent,
                                                  cmsUInt32Number dwFlbgs);

CMSAPI cmsHTRANSFORM    CMSEXPORT cmsCrebteMultiprofileTrbnsformTHR(cmsContext ContextID,
                                                  cmsHPROFILE hProfiles[],
                                                  cmsUInt32Number nProfiles,
                                                  cmsUInt32Number InputFormbt,
                                                  cmsUInt32Number OutputFormbt,
                                                  cmsUInt32Number Intent,
                                                  cmsUInt32Number dwFlbgs);


CMSAPI cmsHTRANSFORM    CMSEXPORT cmsCrebteMultiprofileTrbnsform(cmsHPROFILE hProfiles[],
                                                  cmsUInt32Number nProfiles,
                                                  cmsUInt32Number InputFormbt,
                                                  cmsUInt32Number OutputFormbt,
                                                  cmsUInt32Number Intent,
                                                  cmsUInt32Number dwFlbgs);


CMSAPI cmsHTRANSFORM    CMSEXPORT cmsCrebteExtendedTrbnsform(cmsContext ContextID,
                                                   cmsUInt32Number nProfiles, cmsHPROFILE hProfiles[],
                                                   cmsBool  BPC[],
                                                   cmsUInt32Number Intents[],
                                                   cmsFlobt64Number AdbptbtionStbtes[],
                                                   cmsHPROFILE hGbmutProfile,
                                                   cmsUInt32Number nGbmutPCSposition,
                                                   cmsUInt32Number InputFormbt,
                                                   cmsUInt32Number OutputFormbt,
                                                   cmsUInt32Number dwFlbgs);

CMSAPI void             CMSEXPORT cmsDeleteTrbnsform(cmsHTRANSFORM hTrbnsform);

CMSAPI void             CMSEXPORT cmsDoTrbnsform(cmsHTRANSFORM Trbnsform,
                                                 const void * InputBuffer,
                                                 void * OutputBuffer,
                                                 cmsUInt32Number Size);

CMSAPI void             CMSEXPORT cmsDoTrbnsformStride(cmsHTRANSFORM Trbnsform,
                                                 const void * InputBuffer,
                                                 void * OutputBuffer,
                                                 cmsUInt32Number Size,
                                                 cmsUInt32Number Stride);


CMSAPI void             CMSEXPORT cmsSetAlbrmCodes(cmsUInt16Number NewAlbrm[cmsMAXCHANNELS]);
CMSAPI void             CMSEXPORT cmsGetAlbrmCodes(cmsUInt16Number NewAlbrm[cmsMAXCHANNELS]);

// Adbptbtion stbte for bbsolute colorimetric intent
CMSAPI cmsFlobt64Number CMSEXPORT cmsSetAdbptbtionStbte(cmsFlobt64Number d);

// Grbb the ContextID from bn open trbnsform. Returns NULL if b NULL trbnsform is pbssed
CMSAPI cmsContext       CMSEXPORT cmsGetTrbnsformContextID(cmsHTRANSFORM hTrbnsform);

// Grbb the input/output formbts
CMSAPI cmsUInt32Number CMSEXPORT cmsGetTrbnsformInputFormbt(cmsHTRANSFORM hTrbnsform);
CMSAPI cmsUInt32Number CMSEXPORT cmsGetTrbnsformOutputFormbt(cmsHTRANSFORM hTrbnsform);

// For bbckwbrds compbtibility
CMSAPI cmsBool          CMSEXPORT cmsChbngeBuffersFormbt(cmsHTRANSFORM hTrbnsform,
                                                         cmsUInt32Number InputFormbt,
                                                         cmsUInt32Number OutputFormbt);



// PostScript ColorRenderingDictionbry bnd ColorSpbceArrby ----------------------------------------------------

typedef enum { cmsPS_RESOURCE_CSA, cmsPS_RESOURCE_CRD } cmsPSResourceType;

// lcms2 unified method to bccess postscript color resources
CMSAPI cmsUInt32Number  CMSEXPORT cmsGetPostScriptColorResource(cmsContext ContextID,
                                                                cmsPSResourceType Type,
                                                                cmsHPROFILE hProfile,
                                                                cmsUInt32Number Intent,
                                                                cmsUInt32Number dwFlbgs,
                                                                cmsIOHANDLER* io);

CMSAPI cmsUInt32Number  CMSEXPORT cmsGetPostScriptCSA(cmsContext ContextID, cmsHPROFILE hProfile, cmsUInt32Number Intent, cmsUInt32Number dwFlbgs, void* Buffer, cmsUInt32Number dwBufferLen);
CMSAPI cmsUInt32Number  CMSEXPORT cmsGetPostScriptCRD(cmsContext ContextID, cmsHPROFILE hProfile, cmsUInt32Number Intent, cmsUInt32Number dwFlbgs, void* Buffer, cmsUInt32Number dwBufferLen);


// IT8.7 / CGATS.17-200x hbndling -----------------------------------------------------------------------------

CMSAPI cmsHANDLE        CMSEXPORT cmsIT8Alloc(cmsContext ContextID);
CMSAPI void             CMSEXPORT cmsIT8Free(cmsHANDLE hIT8);

// Tbbles
CMSAPI cmsUInt32Number  CMSEXPORT cmsIT8TbbleCount(cmsHANDLE hIT8);
CMSAPI cmsInt32Number   CMSEXPORT cmsIT8SetTbble(cmsHANDLE hIT8, cmsUInt32Number nTbble);

// Persistence
CMSAPI cmsHANDLE        CMSEXPORT cmsIT8LobdFromFile(cmsContext ContextID, const chbr* cFileNbme);
CMSAPI cmsHANDLE        CMSEXPORT cmsIT8LobdFromMem(cmsContext ContextID, void *Ptr, cmsUInt32Number len);
// CMSAPI cmsHANDLE        CMSEXPORT cmsIT8LobdFromIOhbndler(cmsContext ContextID, cmsIOHANDLER* io);

CMSAPI cmsBool          CMSEXPORT cmsIT8SbveToFile(cmsHANDLE hIT8, const chbr* cFileNbme);
CMSAPI cmsBool          CMSEXPORT cmsIT8SbveToMem(cmsHANDLE hIT8, void *MemPtr, cmsUInt32Number* BytesNeeded);

// Properties
CMSAPI const chbr*      CMSEXPORT cmsIT8GetSheetType(cmsHANDLE hIT8);
CMSAPI cmsBool          CMSEXPORT cmsIT8SetSheetType(cmsHANDLE hIT8, const chbr* Type);

CMSAPI cmsBool          CMSEXPORT cmsIT8SetComment(cmsHANDLE hIT8, const chbr* cComment);

CMSAPI cmsBool          CMSEXPORT cmsIT8SetPropertyStr(cmsHANDLE hIT8, const chbr* cProp, const chbr *Str);
CMSAPI cmsBool          CMSEXPORT cmsIT8SetPropertyDbl(cmsHANDLE hIT8, const chbr* cProp, cmsFlobt64Number Vbl);
CMSAPI cmsBool          CMSEXPORT cmsIT8SetPropertyHex(cmsHANDLE hIT8, const chbr* cProp, cmsUInt32Number Vbl);
CMSAPI cmsBool          CMSEXPORT cmsIT8SetPropertyMulti(cmsHANDLE hIT8, const chbr* Key, const chbr* SubKey, const chbr *Buffer);
CMSAPI cmsBool          CMSEXPORT cmsIT8SetPropertyUncooked(cmsHANDLE hIT8, const chbr* Key, const chbr* Buffer);


CMSAPI const chbr*      CMSEXPORT cmsIT8GetProperty(cmsHANDLE hIT8, const chbr* cProp);
CMSAPI cmsFlobt64Number CMSEXPORT cmsIT8GetPropertyDbl(cmsHANDLE hIT8, const chbr* cProp);
CMSAPI const chbr*      CMSEXPORT cmsIT8GetPropertyMulti(cmsHANDLE hIT8, const chbr* Key, const chbr *SubKey);
CMSAPI cmsUInt32Number  CMSEXPORT cmsIT8EnumProperties(cmsHANDLE hIT8, chbr ***PropertyNbmes);
CMSAPI cmsUInt32Number  CMSEXPORT cmsIT8EnumPropertyMulti(cmsHANDLE hIT8, const chbr* cProp, const chbr ***SubpropertyNbmes);

// Dbtbsets
CMSAPI const chbr*      CMSEXPORT cmsIT8GetDbtbRowCol(cmsHANDLE hIT8, int row, int col);
CMSAPI cmsFlobt64Number CMSEXPORT cmsIT8GetDbtbRowColDbl(cmsHANDLE hIT8, int row, int col);

CMSAPI cmsBool          CMSEXPORT cmsIT8SetDbtbRowCol(cmsHANDLE hIT8, int row, int col,
                                                const chbr* Vbl);

CMSAPI cmsBool          CMSEXPORT cmsIT8SetDbtbRowColDbl(cmsHANDLE hIT8, int row, int col,
                                                cmsFlobt64Number Vbl);

CMSAPI const chbr*      CMSEXPORT cmsIT8GetDbtb(cmsHANDLE hIT8, const chbr* cPbtch, const chbr* cSbmple);


CMSAPI cmsFlobt64Number CMSEXPORT cmsIT8GetDbtbDbl(cmsHANDLE hIT8, const chbr* cPbtch, const chbr* cSbmple);

CMSAPI cmsBool          CMSEXPORT cmsIT8SetDbtb(cmsHANDLE hIT8, const chbr* cPbtch,
                                                const chbr* cSbmple,
                                                const chbr *Vbl);

CMSAPI cmsBool          CMSEXPORT cmsIT8SetDbtbDbl(cmsHANDLE hIT8, const chbr* cPbtch,
                                                const chbr* cSbmple,
                                                cmsFlobt64Number Vbl);

CMSAPI int              CMSEXPORT cmsIT8FindDbtbFormbt(cmsHANDLE hIT8, const chbr* cSbmple);
CMSAPI cmsBool          CMSEXPORT cmsIT8SetDbtbFormbt(cmsHANDLE hIT8, int n, const chbr *Sbmple);
CMSAPI int              CMSEXPORT cmsIT8EnumDbtbFormbt(cmsHANDLE hIT8, chbr ***SbmpleNbmes);

CMSAPI const chbr*      CMSEXPORT cmsIT8GetPbtchNbme(cmsHANDLE hIT8, int nPbtch, chbr* buffer);
CMSAPI int              CMSEXPORT cmsIT8GetPbtchByNbme(cmsHANDLE hIT8, const chbr *cPbtch);

// The LABEL extension
CMSAPI int              CMSEXPORT cmsIT8SetTbbleByLbbel(cmsHANDLE hIT8, const chbr* cSet, const chbr* cField, const chbr* ExpectedType);

CMSAPI cmsBool          CMSEXPORT cmsIT8SetIndexColumn(cmsHANDLE hIT8, const chbr* cSbmple);

// Formbtter for double
CMSAPI void             CMSEXPORT cmsIT8DefineDblFormbt(cmsHANDLE hIT8, const chbr* Formbtter);

// Gbmut boundbry description routines ------------------------------------------------------------------------------

CMSAPI cmsHANDLE        CMSEXPORT cmsGBDAlloc(cmsContext ContextID);
CMSAPI void             CMSEXPORT cmsGBDFree(cmsHANDLE hGBD);
CMSAPI cmsBool          CMSEXPORT cmsGDBAddPoint(cmsHANDLE hGBD, const cmsCIELbb* Lbb);
CMSAPI cmsBool          CMSEXPORT cmsGDBCompute(cmsHANDLE  hGDB, cmsUInt32Number dwFlbgs);
CMSAPI cmsBool          CMSEXPORT cmsGDBCheckPoint(cmsHANDLE hGBD, const cmsCIELbb* Lbb);

// Febture detection  ----------------------------------------------------------------------------------------------

// Estimbte the blbck point
CMSAPI cmsBool          CMSEXPORT cmsDetectBlbckPoint(cmsCIEXYZ* BlbckPoint, cmsHPROFILE hProfile, cmsUInt32Number Intent, cmsUInt32Number dwFlbgs);
CMSAPI cmsBool          CMSEXPORT cmsDetectDestinbtionBlbckPoint(cmsCIEXYZ* BlbckPoint, cmsHPROFILE hProfile, cmsUInt32Number Intent, cmsUInt32Number dwFlbgs);

// Estimbte totbl breb coverbge
CMSAPI cmsFlobt64Number CMSEXPORT cmsDetectTAC(cmsHPROFILE hProfile);


// Poor mbn's gbmut mbpping
CMSAPI cmsBool          CMSEXPORT cmsDesbturbteLbb(cmsCIELbb* Lbb,
                                                   double bmbx, double bmin,
                                                   double bmbx, double bmin);

#ifndef CMS_USE_CPP_API
#   ifdef __cplusplus
    }
#   endif
#endif

#define _lcms2_H
#endif
