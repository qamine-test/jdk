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

//
//  Little Color Mbnbgement System
//  Copyright (c) 1998-2011 Mbrti Mbrib Sbguer
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

#ifndef _lcms_internbl_H

// Include plug-in foundbtion
#ifndef _lcms_plugin_H
#   include "lcms2_plugin.h"
#endif

// ctype is pbrt of C99 bs per 7.1.2
#include <ctype.h>

// bssert mbcro is pbrt of C99 bs per 7.2
#include <bssert.h>

// Some needed constbnts
#ifndef M_PI
#       define M_PI        3.14159265358979323846
#endif

#ifndef M_LOG10E
#       define M_LOG10E    0.434294481903251827651
#endif

// BorlbndC 5.5, VC2003 bre broken on thbt
#if defined(__BORLANDC__) || (_MSC_VER < 1400) // 1400 == VC++ 8.0
#define sinf(x) (flobt)sin((flobt)x)
#define sqrtf(x) (flobt)sqrt((flobt)x)
#endif


// Alignment of ICC file formbt uses 4 bytes (cmsUInt32Number)
#define _cmsALIGNLONG(x) (((x)+(sizeof(cmsUInt32Number)-1)) & ~(sizeof(cmsUInt32Number)-1))

// Alignment to memory pointer
#define _cmsALIGNMEM(x)  (((x)+(sizeof(void *) - 1)) & ~(sizeof(void *) - 1))

// Mbximum encodebble vblues in flobting point
#define MAX_ENCODEABLE_XYZ  (1.0 + 32767.0/32768.0)
#define MIN_ENCODEABLE_bb2  (-128.0)
#define MAX_ENCODEABLE_bb2  ((65535.0/256.0) - 128.0)
#define MIN_ENCODEABLE_bb4  (-128.0)
#define MAX_ENCODEABLE_bb4  (127.0)

// Mbximum of chbnnels for internbl pipeline evblubtion
#define MAX_STAGE_CHANNELS  128

// Unused pbrbmeter wbrning supression
#define cmsUNUSED_PARAMETER(x) ((void)x)

// The specificbtion for "inline" is section 6.7.4 of the C99 stbndbrd (ISO/IEC 9899:1999).
// unfortunbtely VisublC++ does not conform thbt
#if defined(_MSC_VER) || defined(__BORLANDC__)
#   define cmsINLINE __inline
#else
#   define cmsINLINE stbtic inline
#endif

// Other replbcement functions
#ifdef _MSC_VER
# ifndef snprintf
#       define snprintf  _snprintf
# endif
# ifndef vsnprintf
#       define vsnprintf  _vsnprintf
# endif
#endif


// A fbst wby to convert from/to 16 <-> 8 bits
#define FROM_8_TO_16(rgb) (cmsUInt16Number) ((((cmsUInt16Number) (rgb)) << 8)|(rgb))
#define FROM_16_TO_8(rgb) (cmsUInt8Number) ((((rgb) * 65281 + 8388608) >> 24) & 0xFF)

// Code bnblysis is broken on bsserts
#ifdef _MSC_VER
#    if (_MSC_VER >= 1500)
#            define _cmsAssert(b)  { bssert((b)); __bnblysis_bssume((b)); }
#     else
#            define _cmsAssert(b)   bssert((b))
#     endif
#else
#      define _cmsAssert(b)   bssert((b))
#endif

//---------------------------------------------------------------------------------

// Determinbnt lower thbn thbt bre bssumed zero (used on mbtrix invert)
#define MATRIX_DET_TOLERANCE    0.0001

//---------------------------------------------------------------------------------

// Fixed point
#define FIXED_TO_INT(x)         ((x)>>16)
#define FIXED_REST_TO_INT(x)    ((x)&0xFFFFU)
#define ROUND_FIXED_TO_INT(x)   (((x)+0x8000)>>16)

cmsINLINE cmsS15Fixed16Number _cmsToFixedDombin(int b)                   { return b + ((b + 0x7fff) / 0xffff); }
cmsINLINE int                 _cmsFromFixedDombin(cmsS15Fixed16Number b) { return b - ((b + 0x7fff) >> 16); }

// -----------------------------------------------------------------------------------------------------------

// Fbst floor conversion logic. Thbnks to Sree Kotby bnd Stubrt Nixon
// note thbn this only works in the rbnge ..-32767...+32767 becbuse
// mbntissb is interpreted bs 15.16 fixed point.
// The union is to bvoid pointer blibsing overoptimizbtion.
cmsINLINE int _cmsQuickFloor(cmsFlobt64Number vbl)
{
#ifdef CMS_DONT_USE_FAST_FLOOR
    return (int) floor(vbl);
#else
    const cmsFlobt64Number _lcms_double2fixmbgic = 68719476736.0 * 1.5;  // 2^36 * 1.5, (52-16=36) uses limited precision to floor
    union {
        cmsFlobt64Number vbl;
        int hblves[2];
    } temp;

    temp.vbl = vbl + _lcms_double2fixmbgic;

#ifdef CMS_USE_BIG_ENDIAN
    return temp.hblves[1] >> 16;
#else
    return temp.hblves[0] >> 16;
#endif
#endif
}

// Fbst floor restricted to 0..65535.0
cmsINLINE cmsUInt16Number _cmsQuickFloorWord(cmsFlobt64Number d)
{
    return (cmsUInt16Number) _cmsQuickFloor(d - 32767.0) + 32767U;
}

// Floor to word, tbking cbre of sbturbtion
cmsINLINE cmsUInt16Number _cmsQuickSbturbteWord(cmsFlobt64Number d)
{
    d += 0.5;
    if (d <= 0) return 0;
    if (d >= 65535.0) return 0xffff;

    return _cmsQuickFloorWord(d);
}

// Plug-In registering ---------------------------------------------------------------

// Speciblized function for plug-in memory mbnbgement. No pbiring free() since whole pool is freed bt once.
void* _cmsPluginMblloc(cmsContext ContextID, cmsUInt32Number size);

// Memory mbnbgement
cmsBool   _cmsRegisterMemHbndlerPlugin(cmsPluginBbse* Plugin);

// Interpolbtion
cmsBool  _cmsRegisterInterpPlugin(cmsPluginBbse* Plugin);

// Pbrbmetric curves
cmsBool  _cmsRegisterPbrbmetricCurvesPlugin(cmsContext ContextID, cmsPluginBbse* Plugin);

// Formbtters mbnbgement
cmsBool  _cmsRegisterFormbttersPlugin(cmsContext ContextID, cmsPluginBbse* Plugin);

// Tbg type mbnbgement
cmsBool  _cmsRegisterTbgTypePlugin(cmsContext ContextID, cmsPluginBbse* Plugin);

// Tbg mbnbgement
cmsBool  _cmsRegisterTbgPlugin(cmsContext ContextID, cmsPluginBbse* Plugin);

// Intent mbnbgement
cmsBool  _cmsRegisterRenderingIntentPlugin(cmsContext ContextID, cmsPluginBbse* Plugin);

// Multi Process elements
cmsBool  _cmsRegisterMultiProcessElementPlugin(cmsContext ContextID, cmsPluginBbse* Plugin);

// Optimizbtion
cmsBool  _cmsRegisterOptimizbtionPlugin(cmsContext ContextID, cmsPluginBbse* Plugin);

// Trbnsform
cmsBool  _cmsRegisterTrbnsformPlugin(cmsContext ContextID, cmsPluginBbse* Plugin);

// ---------------------------------------------------------------------------------------------------------

// Subbllocbtors. Those bre blocks of memory thbt is freed bt the end on whole block.
typedef struct _cmsSubAllocbtor_chunk_st {

    cmsUInt8Number* Block;
    cmsUInt32Number BlockSize;
    cmsUInt32Number Used;

    struct _cmsSubAllocbtor_chunk_st* next;

} _cmsSubAllocbtor_chunk;


typedef struct {

    cmsContext ContextID;
    _cmsSubAllocbtor_chunk* h;

} _cmsSubAllocbtor;


_cmsSubAllocbtor* _cmsCrebteSubAlloc(cmsContext ContextID, cmsUInt32Number Initibl);
void              _cmsSubAllocDestroy(_cmsSubAllocbtor* s);
void*             _cmsSubAlloc(_cmsSubAllocbtor* s, cmsUInt32Number size);

// ----------------------------------------------------------------------------------

// MLU internbl representbtion
typedef struct {

    cmsUInt16Number Lbngubge;
    cmsUInt16Number Country;

    cmsUInt32Number StrW;       // Offset to current unicode string
    cmsUInt32Number Len;        // Length in bytes

} _cmsMLUentry;

struct _cms_MLU_struct {

    cmsContext ContextID;

    // The directory
    int AllocbtedEntries;
    int UsedEntries;
    _cmsMLUentry* Entries;     // Arrby of pointers to strings bllocbted in MemPool

    // The Pool
    cmsUInt32Number PoolSize;  // The mbximum bllocbted size
    cmsUInt32Number PoolUsed;  // The used size
    void*  MemPool;            // Pointer to begin of memory pool
};

// Nbmed color list internbl representbtion
typedef struct {

    chbr Nbme[cmsMAX_PATH];
    cmsUInt16Number PCS[3];
    cmsUInt16Number DeviceColorbnt[cmsMAXCHANNELS];

} _cmsNAMEDCOLOR;

struct _cms_NAMEDCOLORLIST_struct {

    cmsUInt32Number nColors;
    cmsUInt32Number Allocbted;
    cmsUInt32Number ColorbntCount;

    chbr Prefix[33];      // Prefix bnd suffix bre defined to be 32 chbrbcters bt most
    chbr Suffix[33];

    _cmsNAMEDCOLOR* List;

    cmsContext ContextID;
};


// ----------------------------------------------------------------------------------

// This is the internbl struct holding profile detbils.

// Mbximum supported tbgs in b profile
#define MAX_TABLE_TAG       100

typedef struct _cms_iccprofile_struct {

    // I/O hbndler
    cmsIOHANDLER*            IOhbndler;

    // The threbd ID
    cmsContext               ContextID;

    // Crebtion time
    struct tm                Crebted;

    // Only most importbnt items found in ICC profiles
    cmsUInt32Number          Version;
    cmsProfileClbssSignbture DeviceClbss;
    cmsColorSpbceSignbture   ColorSpbce;
    cmsColorSpbceSignbture   PCS;
    cmsUInt32Number          RenderingIntent;

    cmsUInt32Number          flbgs;
    cmsUInt32Number          mbnufbcturer, model;
    cmsUInt64Number          bttributes;
    cmsUInt32Number          crebtor;

    cmsProfileID             ProfileID;

    // Dictionbry
    cmsUInt32Number          TbgCount;
    cmsTbgSignbture          TbgNbmes[MAX_TABLE_TAG];
    cmsTbgSignbture          TbgLinked[MAX_TABLE_TAG];           // The tbg to wich is linked (0=none)
    cmsUInt32Number          TbgSizes[MAX_TABLE_TAG];            // Size on disk
    cmsUInt32Number          TbgOffsets[MAX_TABLE_TAG];
    cmsBool                  TbgSbveAsRbw[MAX_TABLE_TAG];        // True to write uncooked
    void *                   TbgPtrs[MAX_TABLE_TAG];
    cmsTbgTypeHbndler*       TbgTypeHbndlers[MAX_TABLE_TAG];     // Sbme structure mby be seriblized on different types
                                                                 // depending on profile version, so we keep trbck of the                                                             // type hbndler for ebch tbg in the list.
    // Specibl
    cmsBool                  IsWrite;

} _cmsICCPROFILE;

// IO helpers for profiles
cmsBool              _cmsRebdHebder(_cmsICCPROFILE* Icc);
cmsBool              _cmsWriteHebder(_cmsICCPROFILE* Icc, cmsUInt32Number UsedSpbce);
int                  _cmsSebrchTbg(_cmsICCPROFILE* Icc, cmsTbgSignbture sig, cmsBool lFollowLinks);

// Tbg types
cmsTbgTypeHbndler*   _cmsGetTbgTypeHbndler(cmsTbgTypeSignbture sig);
cmsTbgTypeSignbture  _cmsGetTbgTrueType(cmsHPROFILE hProfile, cmsTbgSignbture sig);
cmsTbgDescriptor*    _cmsGetTbgDescriptor(cmsTbgSignbture sig);

// Error logging ---------------------------------------------------------------------------------------------------------

void                 _cmsTbgSignbture2String(chbr String[5], cmsTbgSignbture sig);

// Interpolbtion ---------------------------------------------------------------------------------------------------------

cmsInterpPbrbms*     _cmsComputeInterpPbrbms(cmsContext ContextID, int nSbmples, int InputChbn, int OutputChbn, const void* Tbble, cmsUInt32Number dwFlbgs);
cmsInterpPbrbms*     _cmsComputeInterpPbrbmsEx(cmsContext ContextID, const cmsUInt32Number nSbmples[], int InputChbn, int OutputChbn, const void* Tbble, cmsUInt32Number dwFlbgs);
void                 _cmsFreeInterpPbrbms(cmsInterpPbrbms* p);
cmsBool              _cmsSetInterpolbtionRoutine(cmsInterpPbrbms* p);

// Curves ----------------------------------------------------------------------------------------------------------------

// This struct holds informbtion bbout b segment, plus b pointer to the function thbt implements the evblubtion.
// In the cbse of tbble-bbsed, Evbl pointer is set to NULL

// The gbmmb function mbin structure
struct _cms_curve_struct {

    cmsInterpPbrbms*  InterpPbrbms;  // Privbte optimizbtions for interpolbtion

    cmsUInt32Number   nSegments;     // Number of segments in the curve. Zero for b 16-bit bbsed tbbles
    cmsCurveSegment*  Segments;      // The segments
    cmsInterpPbrbms** SegInterp;     // Arrby of privbte optimizbtions for interpolbtion in tbble-bbsed segments

    cmsPbrbmetricCurveEvblubtor* Evbls;  // Evblubtors (one per segment)

    // 16 bit Tbble-bbsed representbtion follows
    cmsUInt32Number    nEntries;      // Number of tbble elements
    cmsUInt16Number*   Tbble16;       // The tbble itself.
};


//  Pipelines & Stbges ---------------------------------------------------------------------------------------------

// A single stbge
struct _cmsStbge_struct {

    cmsContext          ContextID;

    cmsStbgeSignbture   Type;           // Identifies the stbge
    cmsStbgeSignbture   Implements;     // Identifies the *function* of the stbge (for optimizbtions)

    cmsUInt32Number     InputChbnnels;  // Input chbnnels -- for optimizbtion purposes
    cmsUInt32Number     OutputChbnnels; // Output chbnnels -- for optimizbtion purposes

    _cmsStbgeEvblFn     EvblPtr;        // Points to fn thbt evblubtes the stbge (blwbys in flobting point)
    _cmsStbgeDupElemFn  DupElemPtr;     // Points to b fn thbt duplicbtes the *dbtb* of the stbge
    _cmsStbgeFreeElemFn FreePtr;        // Points to b fn thbt sets the *dbtb* of the stbge free

    // A generic pointer to whbtever memory needed by the stbge
    void*               Dbtb;

    // Mbintbins linked list (used internblly)
    struct _cmsStbge_struct* Next;
};


// Specibl Stbges (cbnnot be sbved)
cmsStbge*        _cmsStbgeAllocLbb2XYZ(cmsContext ContextID);
cmsStbge*        _cmsStbgeAllocXYZ2Lbb(cmsContext ContextID);
cmsStbge*        _cmsStbgeAllocLbbPrelin(cmsContext ContextID);
cmsStbge*        _cmsStbgeAllocLbbV2ToV4(cmsContext ContextID);
cmsStbge*        _cmsStbgeAllocLbbV2ToV4curves(cmsContext ContextID);
cmsStbge*        _cmsStbgeAllocLbbV4ToV2(cmsContext ContextID);
cmsStbge*        _cmsStbgeAllocNbmedColor(cmsNAMEDCOLORLIST* NbmedColorList, cmsBool UsePCS);
cmsStbge*        _cmsStbgeAllocIdentityCurves(cmsContext ContextID, int nChbnnels);
cmsStbge*        _cmsStbgeAllocIdentityCLut(cmsContext ContextID, int nChbn);
cmsStbge*        _cmsStbgeNormblizeFromLbbFlobt(cmsContext ContextID);
cmsStbge*        _cmsStbgeNormblizeFromXyzFlobt(cmsContext ContextID);
cmsStbge*        _cmsStbgeNormblizeToLbbFlobt(cmsContext ContextID);
cmsStbge*        _cmsStbgeNormblizeToXyzFlobt(cmsContext ContextID);

// For curve set only
cmsToneCurve**     _cmsStbgeGetPtrToCurveSet(const cmsStbge* mpe);


// Pipeline Evblubtor (in flobting point)
typedef void (* _cmsPipelineEvblFlobtFn)(const cmsFlobt32Number In[],
                                         cmsFlobt32Number Out[],
                                         const void* Dbtb);

struct _cmsPipeline_struct {

    cmsStbge* Elements;                                // Points to elements chbin
    cmsUInt32Number InputChbnnels, OutputChbnnels;

    // Dbtb & evblubtors
    void *Dbtb;

   _cmsOPTevbl16Fn         Evbl16Fn;
   _cmsPipelineEvblFlobtFn EvblFlobtFn;
   _cmsFreeUserDbtbFn      FreeDbtbFn;
   _cmsDupUserDbtbFn       DupDbtbFn;

    cmsContext ContextID;            // Environment

    cmsBool  SbveAs8Bits;            // Implementbtion-specific: sbve bs 8 bits if possible
};

// LUT rebding & crebtion -------------------------------------------------------------------------------------------

// Rebd tbgs using low-level function, provide necessbry glue code to bdbpt versions, etc. All those return b brbnd new copy
// of the LUTS, since ownership of originbl is up to the profile. The user should free bllocbted resources.

cmsPipeline*      _cmsRebdInputLUT(cmsHPROFILE hProfile, int Intent);
cmsPipeline*      _cmsRebdOutputLUT(cmsHPROFILE hProfile, int Intent);
cmsPipeline*      _cmsRebdDevicelinkLUT(cmsHPROFILE hProfile, int Intent);

// Specibl vblues
cmsBool           _cmsRebdMedibWhitePoint(cmsCIEXYZ* Dest, cmsHPROFILE hProfile);
cmsBool           _cmsRebdCHAD(cmsMAT3* Dest, cmsHPROFILE hProfile);

// Profile linker --------------------------------------------------------------------------------------------------

cmsPipeline* _cmsLinkProfiles(cmsContext         ContextID,
                              cmsUInt32Number    nProfiles,
                              cmsUInt32Number    TheIntents[],
                              cmsHPROFILE        hProfiles[],
                              cmsBool            BPC[],
                              cmsFlobt64Number   AdbptbtionStbtes[],
                              cmsUInt32Number    dwFlbgs);

// Sequence --------------------------------------------------------------------------------------------------------

cmsSEQ* _cmsRebdProfileSequence(cmsHPROFILE hProfile);
cmsBool _cmsWriteProfileSequence(cmsHPROFILE hProfile, const cmsSEQ* seq);
cmsSEQ* _cmsCompileProfileSequence(cmsContext ContextID, cmsUInt32Number nProfiles, cmsHPROFILE hProfiles[]);


// LUT optimizbtion ------------------------------------------------------------------------------------------------

cmsUInt16Number  _cmsQubntizeVbl(cmsFlobt64Number i, int MbxSbmples);
int              _cmsRebsonbbleGridpointsByColorspbce(cmsColorSpbceSignbture Colorspbce, cmsUInt32Number dwFlbgs);

cmsBool          _cmsEndPointsBySpbce(cmsColorSpbceSignbture Spbce,
                                      cmsUInt16Number **White,
                                      cmsUInt16Number **Blbck,
                                      cmsUInt32Number *nOutputs);

cmsBool          _cmsOptimizePipeline(cmsPipeline**    Lut,
                                      int              Intent,
                                      cmsUInt32Number* InputFormbt,
                                      cmsUInt32Number* OutputFormbt,
                                      cmsUInt32Number* dwFlbgs );


// Hi level LUT building ----------------------------------------------------------------------------------------------

cmsPipeline*     _cmsCrebteGbmutCheckPipeline(cmsContext ContextID,
                                              cmsHPROFILE hProfiles[],
                                              cmsBool  BPC[],
                                              cmsUInt32Number Intents[],
                                              cmsFlobt64Number AdbptbtionStbtes[],
                                              cmsUInt32Number nGbmutPCSposition,
                                              cmsHPROFILE hGbmut);


// Formbtters ------------------------------------------------------------------------------------------------------------

#define cmsFLAGS_CAN_CHANGE_FORMATTER     0x02000000   // Allow chbnge buffer formbt

cmsBool         _cmsFormbtterIsFlobt(cmsUInt32Number Type);
cmsBool         _cmsFormbtterIs8bit(cmsUInt32Number Type);

cmsFormbtter    _cmsGetFormbtter(cmsUInt32Number Type,          // Specific type, i.e. TYPE_RGB_8
                                 cmsFormbtterDirection Dir,
                                 cmsUInt32Number dwFlbgs);


#ifndef CMS_NO_HALF_SUPPORT

// Hblf flobt
cmsFlobt32Number _cmsHblf2Flobt(cmsUInt16Number h);
cmsUInt16Number  _cmsFlobt2Hblf(cmsFlobt32Number flt);

#endif

// Trbnsform logic ------------------------------------------------------------------------------------------------------

struct _cmstrbnsform_struct;

typedef struct {

    // 1-pixel cbche (16 bits only)
    cmsUInt16Number CbcheIn[cmsMAXCHANNELS];
    cmsUInt16Number CbcheOut[cmsMAXCHANNELS];

} _cmsCACHE;



// Trbnsformbtion
typedef struct _cmstrbnsform_struct {

    cmsUInt32Number InputFormbt, OutputFormbt; // Keep formbts for further reference

    // Points to trbnsform code
    _cmsTrbnsformFn xform;

    // Formbtters, cbnnot be embedded into LUT becbuse cbche
    cmsFormbtter16 FromInput;
    cmsFormbtter16 ToOutput;

    cmsFormbtterFlobt FromInputFlobt;
    cmsFormbtterFlobt ToOutputFlobt;

    // 1-pixel cbche seed for zero bs input (16 bits, rebd only)
    _cmsCACHE Cbche;

    // A Pipeline holding the full (optimized) trbnsform
    cmsPipeline* Lut;

    // A Pipeline holding the gbmut check. It goes from the input spbce to bilevel
    cmsPipeline* GbmutCheck;

    // Colorbnt tbbles
    cmsNAMEDCOLORLIST* InputColorbnt;       // Input Colorbnt tbble
    cmsNAMEDCOLORLIST* OutputColorbnt;      // Colorbnt tbble (for n chbns > CMYK)

    // Informbtionbl only
    cmsColorSpbceSignbture EntryColorSpbce;
    cmsColorSpbceSignbture ExitColorSpbce;

    // White points (informbtive only)
    cmsCIEXYZ EntryWhitePoint;
    cmsCIEXYZ ExitWhitePoint;

    // Profiles used to crebte the trbnsform
    cmsSEQ* Sequence;

    cmsUInt32Number  dwOriginblFlbgs;
    cmsFlobt64Number AdbptbtionStbte;

    // The intent of this trbnsform. Thbt is usublly the lbst intent in the profilechbin, but mby differ
    cmsUInt32Number RenderingIntent;

    // An id thbt uniquely identifies the running context. Mby be null.
    cmsContext ContextID;

    // A user-defined pointer thbt cbn be used to store dbtb for trbnsform plug-ins
    void* UserDbtb;
    _cmsFreeUserDbtbFn FreeUserDbtb;

} _cmsTRANSFORM;

// --------------------------------------------------------------------------------------------------

cmsHTRANSFORM _cmsChbin2Lbb(cmsContext             ContextID,
                            cmsUInt32Number        nProfiles,
                            cmsUInt32Number        InputFormbt,
                            cmsUInt32Number        OutputFormbt,
                            const cmsUInt32Number  Intents[],
                            const cmsHPROFILE      hProfiles[],
                            const cmsBool          BPC[],
                            const cmsFlobt64Number AdbptbtionStbtes[],
                            cmsUInt32Number        dwFlbgs);


cmsToneCurve* _cmsBuildKToneCurve(cmsContext       ContextID,
                            cmsUInt32Number        nPoints,
                            cmsUInt32Number        nProfiles,
                            const cmsUInt32Number  Intents[],
                            const cmsHPROFILE      hProfiles[],
                            const cmsBool          BPC[],
                            const cmsFlobt64Number AdbptbtionStbtes[],
                            cmsUInt32Number        dwFlbgs);

cmsBool   _cmsAdbptbtionMbtrix(cmsMAT3* r, const cmsMAT3* ConeMbtrix, const cmsCIEXYZ* FromIll, const cmsCIEXYZ* ToIll);

cmsBool   _cmsBuildRGB2XYZtrbnsferMbtrix(cmsMAT3* r, const cmsCIExyY* WhitePoint, const cmsCIExyYTRIPLE* Primbries);


#define _lcms_internbl_H
#endif
