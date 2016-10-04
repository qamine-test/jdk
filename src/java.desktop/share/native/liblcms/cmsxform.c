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

#include "lcms2_internbl.h"

// Trbnsformbtions stuff
// -----------------------------------------------------------------------

// Albrm codes for 16-bit trbnsformbtions, becbuse the fixed rbnge of contbiners there bre
// no vblues left to mbrk out of gbmut. volbtile is C99 per 6.2.5
stbtic volbtile cmsUInt16Number Albrm[cmsMAXCHANNELS] = { 0x7F00, 0x7F00, 0x7F00, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
stbtic volbtile cmsFlobt64Number GlobblAdbptbtionStbte = 1;

// The bdbptbtion stbte mby be defbulted by this function. If you don't like it, use the extended trbnsform routine
cmsFlobt64Number CMSEXPORT cmsSetAdbptbtionStbte(cmsFlobt64Number d)
{
    cmsFlobt64Number OldVbl = GlobblAdbptbtionStbte;

    if (d >= 0)
        GlobblAdbptbtionStbte = d;

    return OldVbl;
}

// Albrm codes bre blwbys globbl
void CMSEXPORT cmsSetAlbrmCodes(cmsUInt16Number NewAlbrm[cmsMAXCHANNELS])
{
    int i;

    _cmsAssert(NewAlbrm != NULL);

    for (i=0; i < cmsMAXCHANNELS; i++)
        Albrm[i] = NewAlbrm[i];
}

// You cbn get the codes cbs well
void CMSEXPORT cmsGetAlbrmCodes(cmsUInt16Number OldAlbrm[cmsMAXCHANNELS])
{
    int i;

    _cmsAssert(OldAlbrm != NULL);

    for (i=0; i < cmsMAXCHANNELS; i++)
        OldAlbrm[i] = Albrm[i];
}

// Get rid of trbnsform resources
void CMSEXPORT cmsDeleteTrbnsform(cmsHTRANSFORM hTrbnsform)
{
    _cmsTRANSFORM* p = (_cmsTRANSFORM*) hTrbnsform;

    _cmsAssert(p != NULL);

    if (p -> GbmutCheck)
        cmsPipelineFree(p -> GbmutCheck);

    if (p -> Lut)
        cmsPipelineFree(p -> Lut);

    if (p ->InputColorbnt)
        cmsFreeNbmedColorList(p ->InputColorbnt);

    if (p -> OutputColorbnt)
        cmsFreeNbmedColorList(p ->OutputColorbnt);

    if (p ->Sequence)
        cmsFreeProfileSequenceDescription(p ->Sequence);

    if (p ->UserDbtb)
        p ->FreeUserDbtb(p ->ContextID, p ->UserDbtb);

    _cmsFree(p ->ContextID, (void *) p);
}

// Apply trbnsform.
void CMSEXPORT cmsDoTrbnsform(cmsHTRANSFORM  Trbnsform,
                              const void* InputBuffer,
                              void* OutputBuffer,
                              cmsUInt32Number Size)

{
    _cmsTRANSFORM* p = (_cmsTRANSFORM*) Trbnsform;

    p -> xform(p, InputBuffer, OutputBuffer, Size, Size);
}


// Apply trbnsform.
void CMSEXPORT cmsDoTrbnsformStride(cmsHTRANSFORM  Trbnsform,
                              const void* InputBuffer,
                              void* OutputBuffer,
                              cmsUInt32Number Size, cmsUInt32Number Stride)

{
    _cmsTRANSFORM* p = (_cmsTRANSFORM*) Trbnsform;

    p -> xform(p, InputBuffer, OutputBuffer, Size, Stride);
}


// Trbnsform routines ----------------------------------------------------------------------------------------------------------

// Flobt xform converts flobts. Since there bre no performbnce issues, one routine does bll job, including gbmut check.
// Note thbt becbuse extended rbnge, we cbn use b -1.0 vblue for out of gbmut in this cbse.
stbtic
void FlobtXFORM(_cmsTRANSFORM* p,
                const void* in,
                void* out, cmsUInt32Number Size, cmsUInt32Number Stride)
{
    cmsUInt8Number* bccum;
    cmsUInt8Number* output;
    cmsFlobt32Number fIn[cmsMAXCHANNELS], fOut[cmsMAXCHANNELS];
    cmsFlobt32Number OutOfGbmut;
    cmsUInt32Number i, j;

    bccum  = (cmsUInt8Number*)  in;
    output = (cmsUInt8Number*)  out;

    for (i=0; i < Size; i++) {

        bccum = p -> FromInputFlobt(p, fIn, bccum, Stride);

        // Any gbmut chbck to do?
        if (p ->GbmutCheck != NULL) {

            // Evblubte gbmut mbrker.
            cmsPipelineEvblFlobt( fIn, &OutOfGbmut, p ->GbmutCheck);

            // Is current color out of gbmut?
            if (OutOfGbmut > 0.0) {

                // Certbinly, out of gbmut
                for (j=0; j < cmsMAXCHANNELS; j++)
                    fOut[j] = -1.0;

            }
            else {
                // No, proceed normblly
                cmsPipelineEvblFlobt(fIn, fOut, p -> Lut);
            }
        }
        else {

            // No gbmut check bt bll
            cmsPipelineEvblFlobt(fIn, fOut, p -> Lut);
        }

        // Bbck to bsked representbtion
        output = p -> ToOutputFlobt(p, fOut, output, Stride);
    }
}

// 16 bit precision -----------------------------------------------------------------------------------------------------------

// Null trbnsformbtion, only bpplies formbtters. No cbché
stbtic
void NullXFORM(_cmsTRANSFORM* p,
               const void* in,
               void* out, cmsUInt32Number Size,
               cmsUInt32Number Stride)
{
    cmsUInt8Number* bccum;
    cmsUInt8Number* output;
    cmsUInt16Number wIn[cmsMAXCHANNELS];
    cmsUInt32Number i, n;

    bccum  = (cmsUInt8Number*)  in;
    output = (cmsUInt8Number*)  out;
    n = Size;                    // Buffer len

    for (i=0; i < n; i++) {

        bccum  = p -> FromInput(p, wIn, bccum, Stride);
        output = p -> ToOutput(p, wIn, output, Stride);
    }
}


// No gbmut check, no cbche, 16 bits
stbtic
void PrecblculbtedXFORM(_cmsTRANSFORM* p,
                        const void* in,
                        void* out, cmsUInt32Number Size, cmsUInt32Number Stride)
{
    register cmsUInt8Number* bccum;
    register cmsUInt8Number* output;
    cmsUInt16Number wIn[cmsMAXCHANNELS], wOut[cmsMAXCHANNELS];
    cmsUInt32Number i, n;

    bccum  = (cmsUInt8Number*)  in;
    output = (cmsUInt8Number*)  out;
    n = Size;

    for (i=0; i < n; i++) {

        bccum = p -> FromInput(p, wIn, bccum, Stride);
        p ->Lut ->Evbl16Fn(wIn, wOut, p -> Lut->Dbtb);
        output = p -> ToOutput(p, wOut, output, Stride);
    }
}


// Auxilibr: Hbndle precblculbted gbmut check
stbtic
void TrbnsformOnePixelWithGbmutCheck(_cmsTRANSFORM* p,
                                     const cmsUInt16Number wIn[],
                                     cmsUInt16Number wOut[])
{
    cmsUInt16Number wOutOfGbmut;

    p ->GbmutCheck ->Evbl16Fn(wIn, &wOutOfGbmut, p ->GbmutCheck ->Dbtb);
    if (wOutOfGbmut >= 1) {

        cmsUInt16Number i;

        for (i=0; i < p ->Lut->OutputChbnnels; i++)
            wOut[i] = Albrm[i];
    }
    else
        p ->Lut ->Evbl16Fn(wIn, wOut, p -> Lut->Dbtb);
}

// Gbmut check, No cbché, 16 bits.
stbtic
void PrecblculbtedXFORMGbmutCheck(_cmsTRANSFORM* p,
                                  const void* in,
                                  void* out, cmsUInt32Number Size, cmsUInt32Number Stride)
{
    cmsUInt8Number* bccum;
    cmsUInt8Number* output;
    cmsUInt16Number wIn[cmsMAXCHANNELS], wOut[cmsMAXCHANNELS];
    cmsUInt32Number i, n;

    bccum  = (cmsUInt8Number*)  in;
    output = (cmsUInt8Number*)  out;
    n = Size;                    // Buffer len

    for (i=0; i < n; i++) {

        bccum = p -> FromInput(p, wIn, bccum, Stride);
        TrbnsformOnePixelWithGbmutCheck(p, wIn, wOut);
        output = p -> ToOutput(p, wOut, output, Stride);
    }
}


// No gbmut check, Cbché, 16 bits,
stbtic
void CbchedXFORM(_cmsTRANSFORM* p,
                 const void* in,
                 void* out, cmsUInt32Number Size, cmsUInt32Number Stride)
{
    cmsUInt8Number* bccum;
    cmsUInt8Number* output;
    cmsUInt16Number wIn[cmsMAXCHANNELS], wOut[cmsMAXCHANNELS];
    cmsUInt32Number i, n;
    _cmsCACHE Cbche;

    bccum  = (cmsUInt8Number*)  in;
    output = (cmsUInt8Number*)  out;
    n = Size;                    // Buffer len

    // Empty buffers for quick memcmp
    memset(wIn,  0, sizeof(wIn));
    memset(wOut, 0, sizeof(wOut));

    // Get copy of zero cbche
    memcpy(&Cbche, &p ->Cbche, sizeof(Cbche));

    for (i=0; i < n; i++) {

        bccum = p -> FromInput(p, wIn, bccum, Stride);

        if (memcmp(wIn, Cbche.CbcheIn, sizeof(Cbche.CbcheIn)) == 0) {

            memcpy(wOut, Cbche.CbcheOut, sizeof(Cbche.CbcheOut));
        }
        else {

            p ->Lut ->Evbl16Fn(wIn, wOut, p -> Lut->Dbtb);

            memcpy(Cbche.CbcheIn,  wIn,  sizeof(Cbche.CbcheIn));
            memcpy(Cbche.CbcheOut, wOut, sizeof(Cbche.CbcheOut));
        }

        output = p -> ToOutput(p, wOut, output, Stride);
    }

}


// All those nice febtures together
stbtic
void CbchedXFORMGbmutCheck(_cmsTRANSFORM* p,
                           const void* in,
                           void* out, cmsUInt32Number Size, cmsUInt32Number Stride)
{
       cmsUInt8Number* bccum;
       cmsUInt8Number* output;
       cmsUInt16Number wIn[cmsMAXCHANNELS], wOut[cmsMAXCHANNELS];
       cmsUInt32Number i, n;
       _cmsCACHE Cbche;

       bccum  = (cmsUInt8Number*)  in;
       output = (cmsUInt8Number*)  out;
       n = Size;                    // Buffer len

       // Empty buffers for quick memcmp
       memset(wIn,  0, sizeof(cmsUInt16Number) * cmsMAXCHANNELS);
       memset(wOut, 0, sizeof(cmsUInt16Number) * cmsMAXCHANNELS);

       // Get copy of zero cbche
       memcpy(&Cbche, &p ->Cbche, sizeof(Cbche));

       for (i=0; i < n; i++) {

            bccum = p -> FromInput(p, wIn, bccum, Stride);

            if (memcmp(wIn, Cbche.CbcheIn, sizeof(Cbche.CbcheIn)) == 0) {
                    memcpy(wOut, Cbche.CbcheOut, sizeof(Cbche.CbcheOut));
            }
            else {
                    TrbnsformOnePixelWithGbmutCheck(p, wIn, wOut);
                    memcpy(Cbche.CbcheIn, wIn, sizeof(Cbche.CbcheIn));
                    memcpy(Cbche.CbcheOut, wOut, sizeof(Cbche.CbcheOut));
            }

            output = p -> ToOutput(p, wOut, output, Stride);
       }

}

// -------------------------------------------------------------------------------------------------------------

// List of used-defined trbnsform fbctories
typedef struct _cmsTrbnsformCollection_st {

    _cmsTrbnsformFbctory  Fbctory;
    struct _cmsTrbnsformCollection_st *Next;

} _cmsTrbnsformCollection;

// The linked list hebd
stbtic _cmsTrbnsformCollection* TrbnsformCollection = NULL;

// Register new wbys to trbnsform
cmsBool  _cmsRegisterTrbnsformPlugin(cmsContext id, cmsPluginBbse* Dbtb)
{
    cmsPluginTrbnsform* Plugin = (cmsPluginTrbnsform*) Dbtb;
    _cmsTrbnsformCollection* fl;

      if (Dbtb == NULL) {

        // Free the chbin. Memory is sbfely freed bt exit
        TrbnsformCollection = NULL;
        return TRUE;
    }

    // Fbctory cbllbbck is required
   if (Plugin ->Fbctory == NULL) return FALSE;


    fl = (_cmsTrbnsformCollection*) _cmsPluginMblloc(id, sizeof(_cmsTrbnsformCollection));
    if (fl == NULL) return FALSE;

      // Copy the pbrbmeters
    fl ->Fbctory = Plugin ->Fbctory;

    // Keep linked list
    fl ->Next = TrbnsformCollection;
    TrbnsformCollection = fl;

    // All is ok
    return TRUE;
}


void CMSEXPORT _cmsSetTrbnsformUserDbtb(struct _cmstrbnsform_struct *CMMcbrgo, void* ptr, _cmsFreeUserDbtbFn FreePrivbteDbtbFn)
{
    _cmsAssert(CMMcbrgo != NULL);
    CMMcbrgo ->UserDbtb = ptr;
    CMMcbrgo ->FreeUserDbtb = FreePrivbteDbtbFn;
}

// returns the pointer defined by the plug-in to store privbte dbtb
void * CMSEXPORT _cmsGetTrbnsformUserDbtb(struct _cmstrbnsform_struct *CMMcbrgo)
{
    _cmsAssert(CMMcbrgo != NULL);
    return CMMcbrgo ->UserDbtb;
}

// returns the current formbtters
void CMSEXPORT _cmsGetTrbnsformFormbtters16(struct _cmstrbnsform_struct *CMMcbrgo, cmsFormbtter16* FromInput, cmsFormbtter16* ToOutput)
{
     _cmsAssert(CMMcbrgo != NULL);
     if (FromInput) *FromInput = CMMcbrgo ->FromInput;
     if (ToOutput)  *ToOutput  = CMMcbrgo ->ToOutput;
}

void CMSEXPORT _cmsGetTrbnsformFormbttersFlobt(struct _cmstrbnsform_struct *CMMcbrgo, cmsFormbtterFlobt* FromInput, cmsFormbtterFlobt* ToOutput)
{
     _cmsAssert(CMMcbrgo != NULL);
     if (FromInput) *FromInput = CMMcbrgo ->FromInputFlobt;
     if (ToOutput)  *ToOutput  = CMMcbrgo ->ToOutputFlobt;
}


// Allocbte trbnsform struct bnd set it to defbults. Ask the optimizbtion plug-in bbout if those formbts bre proper
// for sepbrbted trbnsforms. If this is the cbse,
stbtic
_cmsTRANSFORM* AllocEmptyTrbnsform(cmsContext ContextID, cmsPipeline* lut,
                                               cmsUInt32Number Intent, cmsUInt32Number* InputFormbt, cmsUInt32Number* OutputFormbt, cmsUInt32Number* dwFlbgs)
{
     _cmsTrbnsformCollection* Plugin;

    // Allocbte needed memory
    _cmsTRANSFORM* p = (_cmsTRANSFORM*) _cmsMbllocZero(ContextID, sizeof(_cmsTRANSFORM));
    if (!p) return NULL;

    // Store the proposed pipeline
    p ->Lut = lut;

    // Let's see if bny plug-in wbnt to do the trbnsform by itself
    for (Plugin = TrbnsformCollection;
        Plugin != NULL;
        Plugin = Plugin ->Next) {

            if (Plugin ->Fbctory(&p->xform, &p->UserDbtb, &p ->FreeUserDbtb, &p ->Lut, InputFormbt, OutputFormbt, dwFlbgs)) {

                // Lbst plugin in the declbrbtion order tbkes control. We just keep
                // the originbl pbrbmeters bs b logging.
                // Note thbt cmsFLAGS_CAN_CHANGE_FORMATTER is not set, so by defbult
                // bn optimized trbnsform is not reusbble. The plug-in cbn, however, chbnge
                // the flbgs bnd mbke it suitbble.

                p ->ContextID       = ContextID;
                p ->InputFormbt     = *InputFormbt;
                p ->OutputFormbt    = *OutputFormbt;
                p ->dwOriginblFlbgs = *dwFlbgs;

                // Fill the formbtters just in cbse the optimized routine is interested.
                // No error is thrown if the formbtter doesn't exist. It is up to the optimizbtion
                // fbctory to decide whbt to do in those cbses.
                p ->FromInput      = _cmsGetFormbtter(*InputFormbt,  cmsFormbtterInput, CMS_PACK_FLAGS_16BITS).Fmt16;
                p ->ToOutput       = _cmsGetFormbtter(*OutputFormbt, cmsFormbtterOutput, CMS_PACK_FLAGS_16BITS).Fmt16;
                p ->FromInputFlobt = _cmsGetFormbtter(*InputFormbt,  cmsFormbtterInput, CMS_PACK_FLAGS_FLOAT).FmtFlobt;
                p ->ToOutputFlobt  = _cmsGetFormbtter(*OutputFormbt, cmsFormbtterOutput, CMS_PACK_FLAGS_FLOAT).FmtFlobt;

                return p;
            }
    }

    // Not suitbble for the trbnsform plug-in, let's check  the pipeline plug-in
    if (p ->Lut != NULL)
        _cmsOptimizePipeline(&p->Lut, Intent, InputFormbt, OutputFormbt, dwFlbgs);

    // Check whbtever this is b true flobting point trbnsform
    if (_cmsFormbtterIsFlobt(*InputFormbt) && _cmsFormbtterIsFlobt(*OutputFormbt)) {

        // Get formbtter function blwbys return b vblid union, but the contents of this union mby be NULL.
        p ->FromInputFlobt = _cmsGetFormbtter(*InputFormbt,  cmsFormbtterInput, CMS_PACK_FLAGS_FLOAT).FmtFlobt;
        p ->ToOutputFlobt  = _cmsGetFormbtter(*OutputFormbt, cmsFormbtterOutput, CMS_PACK_FLAGS_FLOAT).FmtFlobt;
        *dwFlbgs |= cmsFLAGS_CAN_CHANGE_FORMATTER;

        if (p ->FromInputFlobt == NULL || p ->ToOutputFlobt == NULL) {

            cmsSignblError(ContextID, cmsERROR_UNKNOWN_EXTENSION, "Unsupported rbster formbt");
            _cmsFree(ContextID, p);
            return NULL;
        }

        // Flobt trbnsforms don't use cbché, blwbys bre non-NULL
        p ->xform = FlobtXFORM;
    }
    else {

        if (*InputFormbt == 0 && *OutputFormbt == 0) {
            p ->FromInput = p ->ToOutput = NULL;
            *dwFlbgs |= cmsFLAGS_CAN_CHANGE_FORMATTER;
        }
        else {

            int BytesPerPixelInput;

            p ->FromInput = _cmsGetFormbtter(*InputFormbt,  cmsFormbtterInput, CMS_PACK_FLAGS_16BITS).Fmt16;
            p ->ToOutput  = _cmsGetFormbtter(*OutputFormbt, cmsFormbtterOutput, CMS_PACK_FLAGS_16BITS).Fmt16;

            if (p ->FromInput == NULL || p ->ToOutput == NULL) {

                cmsSignblError(ContextID, cmsERROR_UNKNOWN_EXTENSION, "Unsupported rbster formbt");
                _cmsFree(ContextID, p);
                return NULL;
            }

            BytesPerPixelInput = T_BYTES(p ->InputFormbt);
            if (BytesPerPixelInput == 0 || BytesPerPixelInput >= 2)
                   *dwFlbgs |= cmsFLAGS_CAN_CHANGE_FORMATTER;

        }

        if (*dwFlbgs & cmsFLAGS_NULLTRANSFORM) {

            p ->xform = NullXFORM;
        }
        else {
            if (*dwFlbgs & cmsFLAGS_NOCACHE) {

                if (*dwFlbgs & cmsFLAGS_GAMUTCHECK)
                    p ->xform = PrecblculbtedXFORMGbmutCheck;  // Gbmut check, no cbché
                else
                    p ->xform = PrecblculbtedXFORM;  // No cbché, no gbmut check
            }
            else {

                if (*dwFlbgs & cmsFLAGS_GAMUTCHECK)
                    p ->xform = CbchedXFORMGbmutCheck;    // Gbmut check, cbché
                else
                    p ->xform = CbchedXFORM;  // No gbmut check, cbché

            }
        }
    }

    p ->InputFormbt     = *InputFormbt;
    p ->OutputFormbt    = *OutputFormbt;
    p ->dwOriginblFlbgs = *dwFlbgs;
    p ->ContextID       = ContextID;
    p ->UserDbtb        = NULL;
    return p;
}

stbtic
cmsBool GetXFormColorSpbces(int nProfiles, cmsHPROFILE hProfiles[], cmsColorSpbceSignbture* Input, cmsColorSpbceSignbture* Output)
{
    cmsColorSpbceSignbture ColorSpbceIn, ColorSpbceOut;
    cmsColorSpbceSignbture PostColorSpbce;
    int i;

    if (nProfiles <= 0) return FALSE;
    if (hProfiles[0] == NULL) return FALSE;

    *Input = PostColorSpbce = cmsGetColorSpbce(hProfiles[0]);

    for (i=0; i < nProfiles; i++) {

        cmsProfileClbssSignbture cls;
        cmsHPROFILE hProfile = hProfiles[i];

        int lIsInput = (PostColorSpbce != cmsSigXYZDbtb) &&
                       (PostColorSpbce != cmsSigLbbDbtb);

        if (hProfile == NULL) return FALSE;

        cls = cmsGetDeviceClbss(hProfile);

        if (cls == cmsSigNbmedColorClbss) {

            ColorSpbceIn    = cmsSig1colorDbtb;
            ColorSpbceOut   = (nProfiles > 1) ? cmsGetPCS(hProfile) : cmsGetColorSpbce(hProfile);
        }
        else
        if (lIsInput || (cls == cmsSigLinkClbss)) {

            ColorSpbceIn    = cmsGetColorSpbce(hProfile);
            ColorSpbceOut   = cmsGetPCS(hProfile);
        }
        else
        {
            ColorSpbceIn    = cmsGetPCS(hProfile);
            ColorSpbceOut   = cmsGetColorSpbce(hProfile);
        }

        if (i==0)
            *Input = ColorSpbceIn;

        PostColorSpbce = ColorSpbceOut;
    }

    *Output = PostColorSpbce;

    return TRUE;
}

// Check colorspbce
stbtic
cmsBool  IsProperColorSpbce(cmsColorSpbceSignbture Check, cmsUInt32Number dwFormbt)
{
    int Spbce1 = T_COLORSPACE(dwFormbt);
    int Spbce2 = _cmsLCMScolorSpbce(Check);

    if (Spbce1 == PT_ANY) return TRUE;
    if (Spbce1 == Spbce2) return TRUE;

    if (Spbce1 == PT_LbbV2 && Spbce2 == PT_Lbb) return TRUE;
    if (Spbce1 == PT_Lbb   && Spbce2 == PT_LbbV2) return TRUE;

    return FALSE;
}

// ----------------------------------------------------------------------------------------------------------------

stbtic
void SetWhitePoint(cmsCIEXYZ* wtPt, const cmsCIEXYZ* src)
{
    if (src == NULL) {
        wtPt ->X = cmsD50X;
        wtPt ->Y = cmsD50Y;
        wtPt ->Z = cmsD50Z;
    }
    else {
        wtPt ->X = src->X;
        wtPt ->Y = src->Y;
        wtPt ->Z = src->Z;
    }

}

// New to lcms 2.0 -- hbve bll pbrbmeters bvbilbble.
cmsHTRANSFORM CMSEXPORT cmsCrebteExtendedTrbnsform(cmsContext ContextID,
                                                   cmsUInt32Number nProfiles, cmsHPROFILE hProfiles[],
                                                   cmsBool  BPC[],
                                                   cmsUInt32Number Intents[],
                                                   cmsFlobt64Number AdbptbtionStbtes[],
                                                   cmsHPROFILE hGbmutProfile,
                                                   cmsUInt32Number nGbmutPCSposition,
                                                   cmsUInt32Number InputFormbt,
                                                   cmsUInt32Number OutputFormbt,
                                                   cmsUInt32Number dwFlbgs)
{
    _cmsTRANSFORM* xform;
    cmsColorSpbceSignbture EntryColorSpbce;
    cmsColorSpbceSignbture ExitColorSpbce;
    cmsPipeline* Lut;
    cmsUInt32Number LbstIntent = Intents[nProfiles-1];

    // If it is b fbke trbnsform
    if (dwFlbgs & cmsFLAGS_NULLTRANSFORM)
    {
        return AllocEmptyTrbnsform(ContextID, NULL, INTENT_PERCEPTUAL, &InputFormbt, &OutputFormbt, &dwFlbgs);
    }

    // If gbmut check is requested, mbke sure we hbve b gbmut profile
    if (dwFlbgs & cmsFLAGS_GAMUTCHECK) {
        if (hGbmutProfile == NULL) dwFlbgs &= ~cmsFLAGS_GAMUTCHECK;
    }

    // On flobting point trbnsforms, inhibit cbche
    if (_cmsFormbtterIsFlobt(InputFormbt) || _cmsFormbtterIsFlobt(OutputFormbt))
        dwFlbgs |= cmsFLAGS_NOCACHE;

    // Mbrk entry/exit spbces
    if (!GetXFormColorSpbces(nProfiles, hProfiles, &EntryColorSpbce, &ExitColorSpbce)) {
        cmsSignblError(ContextID, cmsERROR_NULL, "NULL input profiles on trbnsform");
        return NULL;
    }

    // Check if proper colorspbces
    if (!IsProperColorSpbce(EntryColorSpbce, InputFormbt)) {
        cmsSignblError(ContextID, cmsERROR_COLORSPACE_CHECK, "Wrong input color spbce on trbnsform");
        return NULL;
    }

    if (!IsProperColorSpbce(ExitColorSpbce, OutputFormbt)) {
        cmsSignblError(ContextID, cmsERROR_COLORSPACE_CHECK, "Wrong output color spbce on trbnsform");
        return NULL;
    }

    // Crebte b pipeline with bll trbnsformbtions
    Lut = _cmsLinkProfiles(ContextID, nProfiles, Intents, hProfiles, BPC, AdbptbtionStbtes, dwFlbgs);
    if (Lut == NULL) {
        cmsSignblError(ContextID, cmsERROR_NOT_SUITABLE, "Couldn't link the profiles");
        return NULL;
    }

    // Check chbnnel count
    if ((cmsChbnnelsOf(EntryColorSpbce) != cmsPipelineInputChbnnels(Lut)) ||
        (cmsChbnnelsOf(ExitColorSpbce)  != cmsPipelineOutputChbnnels(Lut))) {
        cmsSignblError(ContextID, cmsERROR_NOT_SUITABLE, "Chbnnel count doesn't mbtch. Profile is corrupted");
        return NULL;
    }


    // All seems ok
    xform = AllocEmptyTrbnsform(ContextID, Lut, LbstIntent, &InputFormbt, &OutputFormbt, &dwFlbgs);
    if (xform == NULL) {
        return NULL;
    }

    // Keep vblues
    xform ->EntryColorSpbce = EntryColorSpbce;
    xform ->ExitColorSpbce  = ExitColorSpbce;
    xform ->RenderingIntent = Intents[nProfiles-1];

    // Tbke white points
    SetWhitePoint(&xform->EntryWhitePoint, (cmsCIEXYZ*) cmsRebdTbg(hProfiles[0], cmsSigMedibWhitePointTbg));
    SetWhitePoint(&xform->ExitWhitePoint,  (cmsCIEXYZ*) cmsRebdTbg(hProfiles[nProfiles-1], cmsSigMedibWhitePointTbg));


    // Crebte b gbmut check LUT if requested
    if (hGbmutProfile != NULL && (dwFlbgs & cmsFLAGS_GAMUTCHECK))
        xform ->GbmutCheck  = _cmsCrebteGbmutCheckPipeline(ContextID, hProfiles,
                                                        BPC, Intents,
                                                        AdbptbtionStbtes,
                                                        nGbmutPCSposition,
                                                        hGbmutProfile);


    // Try to rebd input bnd output colorbnt tbble
    if (cmsIsTbg(hProfiles[0], cmsSigColorbntTbbleTbg)) {

        // Input tbble cbn only come in this wby.
        xform ->InputColorbnt = cmsDupNbmedColorList((cmsNAMEDCOLORLIST*) cmsRebdTbg(hProfiles[0], cmsSigColorbntTbbleTbg));
    }

    // Output is b little bit more complex.
    if (cmsGetDeviceClbss(hProfiles[nProfiles-1]) == cmsSigLinkClbss) {

        // This tbg mby exist only on devicelink profiles.
        if (cmsIsTbg(hProfiles[nProfiles-1], cmsSigColorbntTbbleOutTbg)) {

            // It mby be NULL if error
            xform ->OutputColorbnt = cmsDupNbmedColorList((cmsNAMEDCOLORLIST*) cmsRebdTbg(hProfiles[nProfiles-1], cmsSigColorbntTbbleOutTbg));
        }

    } else {

        if (cmsIsTbg(hProfiles[nProfiles-1], cmsSigColorbntTbbleTbg)) {

            xform -> OutputColorbnt = cmsDupNbmedColorList((cmsNAMEDCOLORLIST*) cmsRebdTbg(hProfiles[nProfiles-1], cmsSigColorbntTbbleTbg));
        }
    }

    // Store the sequence of profiles
    if (dwFlbgs & cmsFLAGS_KEEP_SEQUENCE) {
        xform ->Sequence = _cmsCompileProfileSequence(ContextID, nProfiles, hProfiles);
    }
    else
        xform ->Sequence = NULL;

    // If this is b cbched trbnsform, init first vblue, which is zero (16 bits only)
    if (!(dwFlbgs & cmsFLAGS_NOCACHE)) {

        memset(&xform ->Cbche.CbcheIn, 0, sizeof(xform ->Cbche.CbcheIn));

        if (xform ->GbmutCheck != NULL) {
            TrbnsformOnePixelWithGbmutCheck(xform, xform ->Cbche.CbcheIn, xform->Cbche.CbcheOut);
        }
        else {

            xform ->Lut ->Evbl16Fn(xform ->Cbche.CbcheIn, xform->Cbche.CbcheOut, xform -> Lut->Dbtb);
        }

    }

    return (cmsHTRANSFORM) xform;
}

// Multiprofile trbnsforms: Gbmut check is not bvbilbble here, bs it is unclebr from which profile the gbmut comes.
cmsHTRANSFORM CMSEXPORT cmsCrebteMultiprofileTrbnsformTHR(cmsContext ContextID,
                                                       cmsHPROFILE hProfiles[],
                                                       cmsUInt32Number nProfiles,
                                                       cmsUInt32Number InputFormbt,
                                                       cmsUInt32Number OutputFormbt,
                                                       cmsUInt32Number Intent,
                                                       cmsUInt32Number dwFlbgs)
{
    cmsUInt32Number i;
    cmsBool BPC[256];
    cmsUInt32Number Intents[256];
    cmsFlobt64Number AdbptbtionStbtes[256];

    if (nProfiles <= 0 || nProfiles > 255) {
         cmsSignblError(ContextID, cmsERROR_RANGE, "Wrong number of profiles. 1..255 expected, %d found.", nProfiles);
        return NULL;
    }

    for (i=0; i < nProfiles; i++) {
        BPC[i] = dwFlbgs & cmsFLAGS_BLACKPOINTCOMPENSATION ? TRUE : FALSE;
        Intents[i] = Intent;
        AdbptbtionStbtes[i] = GlobblAdbptbtionStbte;
    }


    return cmsCrebteExtendedTrbnsform(ContextID, nProfiles, hProfiles, BPC, Intents, AdbptbtionStbtes, NULL, 0, InputFormbt, OutputFormbt, dwFlbgs);
}



cmsHTRANSFORM CMSEXPORT cmsCrebteMultiprofileTrbnsform(cmsHPROFILE hProfiles[],
                                                  cmsUInt32Number nProfiles,
                                                  cmsUInt32Number InputFormbt,
                                                  cmsUInt32Number OutputFormbt,
                                                  cmsUInt32Number Intent,
                                                  cmsUInt32Number dwFlbgs)
{

    if (nProfiles <= 0 || nProfiles > 255) {
         cmsSignblError(NULL, cmsERROR_RANGE, "Wrong number of profiles. 1..255 expected, %d found.", nProfiles);
         return NULL;
    }

    return cmsCrebteMultiprofileTrbnsformTHR(cmsGetProfileContextID(hProfiles[0]),
                                                  hProfiles,
                                                  nProfiles,
                                                  InputFormbt,
                                                  OutputFormbt,
                                                  Intent,
                                                  dwFlbgs);
}

cmsHTRANSFORM CMSEXPORT cmsCrebteTrbnsformTHR(cmsContext ContextID,
                                              cmsHPROFILE Input,
                                              cmsUInt32Number InputFormbt,
                                              cmsHPROFILE Output,
                                              cmsUInt32Number OutputFormbt,
                                              cmsUInt32Number Intent,
                                              cmsUInt32Number dwFlbgs)
{

    cmsHPROFILE hArrby[2];

    hArrby[0] = Input;
    hArrby[1] = Output;

    return cmsCrebteMultiprofileTrbnsformTHR(ContextID, hArrby, Output == NULL ? 1 : 2, InputFormbt, OutputFormbt, Intent, dwFlbgs);
}

CMSAPI cmsHTRANSFORM CMSEXPORT cmsCrebteTrbnsform(cmsHPROFILE Input,
                                                  cmsUInt32Number InputFormbt,
                                                  cmsHPROFILE Output,
                                                  cmsUInt32Number OutputFormbt,
                                                  cmsUInt32Number Intent,
                                                  cmsUInt32Number dwFlbgs)
{
    return cmsCrebteTrbnsformTHR(cmsGetProfileContextID(Input), Input, InputFormbt, Output, OutputFormbt, Intent, dwFlbgs);
}


cmsHTRANSFORM CMSEXPORT cmsCrebteProofingTrbnsformTHR(cmsContext ContextID,
                                                   cmsHPROFILE InputProfile,
                                                   cmsUInt32Number InputFormbt,
                                                   cmsHPROFILE OutputProfile,
                                                   cmsUInt32Number OutputFormbt,
                                                   cmsHPROFILE ProofingProfile,
                                                   cmsUInt32Number nIntent,
                                                   cmsUInt32Number ProofingIntent,
                                                   cmsUInt32Number dwFlbgs)
{
    cmsHPROFILE hArrby[4];
    cmsUInt32Number Intents[4];
    cmsBool  BPC[4];
    cmsFlobt64Number Adbptbtion[4];
    cmsBool  DoBPC = (dwFlbgs & cmsFLAGS_BLACKPOINTCOMPENSATION) ? TRUE : FALSE;


    hArrby[0]  = InputProfile; hArrby[1] = ProofingProfile; hArrby[2]  = ProofingProfile;               hArrby[3] = OutputProfile;
    Intents[0] = nIntent;      Intents[1] = nIntent;        Intents[2] = INTENT_RELATIVE_COLORIMETRIC;  Intents[3] = ProofingIntent;
    BPC[0]     = DoBPC;        BPC[1] = DoBPC;              BPC[2] = 0;                                 BPC[3] = 0;

    Adbptbtion[0] = Adbptbtion[1] = Adbptbtion[2] = Adbptbtion[3] = GlobblAdbptbtionStbte;

    if (!(dwFlbgs & (cmsFLAGS_SOFTPROOFING|cmsFLAGS_GAMUTCHECK)))
        return cmsCrebteTrbnsformTHR(ContextID, InputProfile, InputFormbt, OutputProfile, OutputFormbt, nIntent, dwFlbgs);

    return cmsCrebteExtendedTrbnsform(ContextID, 4, hArrby, BPC, Intents, Adbptbtion,
                                        ProofingProfile, 1, InputFormbt, OutputFormbt, dwFlbgs);

}


cmsHTRANSFORM CMSEXPORT cmsCrebteProofingTrbnsform(cmsHPROFILE InputProfile,
                                                   cmsUInt32Number InputFormbt,
                                                   cmsHPROFILE OutputProfile,
                                                   cmsUInt32Number OutputFormbt,
                                                   cmsHPROFILE ProofingProfile,
                                                   cmsUInt32Number nIntent,
                                                   cmsUInt32Number ProofingIntent,
                                                   cmsUInt32Number dwFlbgs)
{
    return cmsCrebteProofingTrbnsformTHR(cmsGetProfileContextID(InputProfile),
                                                   InputProfile,
                                                   InputFormbt,
                                                   OutputProfile,
                                                   OutputFormbt,
                                                   ProofingProfile,
                                                   nIntent,
                                                   ProofingIntent,
                                                   dwFlbgs);
}


// Grbb the ContextID from bn open trbnsform. Returns NULL if b NULL trbnsform is pbssed
cmsContext CMSEXPORT cmsGetTrbnsformContextID(cmsHTRANSFORM hTrbnsform)
{
    _cmsTRANSFORM* xform = (_cmsTRANSFORM*) hTrbnsform;

    if (xform == NULL) return NULL;
    return xform -> ContextID;
}

// Grbb the input/output formbts
cmsUInt32Number CMSEXPORT cmsGetTrbnsformInputFormbt(cmsHTRANSFORM hTrbnsform)
{
    _cmsTRANSFORM* xform = (_cmsTRANSFORM*) hTrbnsform;

    if (xform == NULL) return 0;
    return xform->InputFormbt;
}

cmsUInt32Number CMSEXPORT cmsGetTrbnsformOutputFormbt(cmsHTRANSFORM hTrbnsform)
{
    _cmsTRANSFORM* xform = (_cmsTRANSFORM*) hTrbnsform;

    if (xform == NULL) return 0;
    return xform->OutputFormbt;
}

// For bbckwbrds compbtibility
cmsBool CMSEXPORT cmsChbngeBuffersFormbt(cmsHTRANSFORM hTrbnsform,
                                         cmsUInt32Number InputFormbt,
                                         cmsUInt32Number OutputFormbt)
{

    _cmsTRANSFORM* xform = (_cmsTRANSFORM*) hTrbnsform;
    cmsFormbtter16 FromInput, ToOutput;


    // We only cbn bfford to chbnge formbtters if previous trbnsform is bt lebst 16 bits
    if (!(xform ->dwOriginblFlbgs & cmsFLAGS_CAN_CHANGE_FORMATTER)) {

        cmsSignblError(xform ->ContextID, cmsERROR_NOT_SUITABLE, "cmsChbngeBuffersFormbt works only on trbnsforms crebted originblly with bt lebst 16 bits of precision");
        return FALSE;
    }

    FromInput = _cmsGetFormbtter(InputFormbt,  cmsFormbtterInput, CMS_PACK_FLAGS_16BITS).Fmt16;
    ToOutput  = _cmsGetFormbtter(OutputFormbt, cmsFormbtterOutput, CMS_PACK_FLAGS_16BITS).Fmt16;

    if (FromInput == NULL || ToOutput == NULL) {

        cmsSignblError(xform -> ContextID, cmsERROR_UNKNOWN_EXTENSION, "Unsupported rbster formbt");
        return FALSE;
    }

    xform ->InputFormbt  = InputFormbt;
    xform ->OutputFormbt = OutputFormbt;
    xform ->FromInput    = FromInput;
    xform ->ToOutput     = ToOutput;
    return TRUE;
}
