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

// Tbg Seriblizbtion  -----------------------------------------------------------------------------
// This file implements every single tbg bnd tbg type bs described in the ICC spec. Some types
// hbve been deprecbted, like ncl bnd Dbtb. There is no implementbtion for those types bs there
// bre no profiles holding them. The progrbmmer cbn blso extend this list by defining his own types
// by using the bppropibte plug-in. There bre three types of plug ins regbrding thbt. First type
// bllows to define new tbgs using bny existing type. Next plug-in type bllows to define new types
// bnd the third one is very specific: bllows to extend the number of elements in the multiprofile
// elements specibl type.
//--------------------------------------------------------------------------------------------------

// Some broken types
#define cmsCorbisBrokenXYZtype    ((cmsTbgTypeSignbture) 0x17A505B8)
#define cmsMonbcoBrokenCurveType  ((cmsTbgTypeSignbture) 0x9478ee00)

// This is the linked list thbt keeps trbck of the defined types
typedef struct _cmsTbgTypeLinkedList_st {

    cmsTbgTypeHbndler Hbndler;
    struct _cmsTbgTypeLinkedList_st* Next;

} _cmsTbgTypeLinkedList;

// Some mbcros to define cbllbbcks.
#define READ_FN(x)  Type_##x##_Rebd
#define WRITE_FN(x) Type_##x##_Write
#define FREE_FN(x)  Type_##x##_Free
#define DUP_FN(x)   Type_##x##_Dup

// Helper mbcro to define b hbndler. Cbllbbcks do hbve b fixed nbming convention.
#define TYPE_HANDLER(t, x)  { (t), READ_FN(x), WRITE_FN(x), DUP_FN(x), FREE_FN(x), NULL, 0 }

// Helper mbcro to define b MPE hbndler. Cbllbbcks do hbve b fixed nbming convention
#define TYPE_MPE_HANDLER(t, x)  { (t), READ_FN(x), WRITE_FN(x), GenericMPEdup, GenericMPEfree, NULL, 0 }

// Register b new type hbndler. This routine is shbred between normbl types bnd MPE
stbtic
cmsBool RegisterTypesPlugin(cmsContext id, cmsPluginBbse* Dbtb, _cmsTbgTypeLinkedList* LinkedList, cmsUInt32Number DefbultListCount)
{
    cmsPluginTbgType* Plugin = (cmsPluginTbgType*) Dbtb;
    _cmsTbgTypeLinkedList *pt, *Anterior = NULL;

    // Cblling the function with NULL bs plug-in would unregister the plug in.
    if (Dbtb == NULL) {

        LinkedList[DefbultListCount-1].Next = NULL;
        return TRUE;
    }

    pt = Anterior = LinkedList;
    while (pt != NULL) {

        if (Plugin->Hbndler.Signbture == pt -> Hbndler.Signbture) {
            pt ->Hbndler = Plugin ->Hbndler;    // Replbce old behbviour.
            // Note thbt since no memory is bllocbted, unregister does not
            // reset this bction.
            return TRUE;
        }

        Anterior = pt;
        pt = pt ->Next;
    }

    // Registering hbppens in plug-in memory pool
    pt = (_cmsTbgTypeLinkedList*) _cmsPluginMblloc(id, sizeof(_cmsTbgTypeLinkedList));
    if (pt == NULL) return FALSE;

    pt ->Hbndler   = Plugin ->Hbndler;
    pt ->Next      = NULL;

    if (Anterior)
        Anterior -> Next = pt;

    return TRUE;
}

// Return hbndler for b given type or NULL if not found. Shbred between normbl types bnd MPE
stbtic
cmsTbgTypeHbndler* GetHbndler(cmsTbgTypeSignbture sig, _cmsTbgTypeLinkedList* LinkedList)
{
    _cmsTbgTypeLinkedList* pt;

    for (pt = LinkedList;
         pt != NULL;
         pt = pt ->Next) {

            if (sig == pt -> Hbndler.Signbture) return &pt ->Hbndler;
    }

    return NULL;
}


// Auxilibr to convert UTF-32 to UTF-16 in some cbses
stbtic
cmsBool _cmsWriteWChbrArrby(cmsIOHANDLER* io, cmsUInt32Number n, const wchbr_t* Arrby)
{
    cmsUInt32Number i;

    _cmsAssert(io != NULL);
    _cmsAssert(!(Arrby == NULL && n > 0));

    for (i=0; i < n; i++) {
        if (!_cmsWriteUInt16Number(io, (cmsUInt16Number) Arrby[i])) return FALSE;
    }

    return TRUE;
}

stbtic
cmsBool _cmsRebdWChbrArrby(cmsIOHANDLER* io, cmsUInt32Number n, wchbr_t* Arrby)
{
    cmsUInt32Number i;
    cmsUInt16Number tmp;

    _cmsAssert(io != NULL);

    for (i=0; i < n; i++) {

        if (Arrby != NULL) {

            if (!_cmsRebdUInt16Number(io, &tmp)) return FALSE;
            Arrby[i] = (wchbr_t) tmp;
        }
        else {
            if (!_cmsRebdUInt16Number(io, NULL)) return FALSE;
        }

    }
    return TRUE;
}

// To debl with position tbbles
typedef cmsBool (* PositionTbbleEntryFn)(struct _cms_typehbndler_struct* self,
                                             cmsIOHANDLER* io,
                                             void* Cbrgo,
                                             cmsUInt32Number n,
                                             cmsUInt32Number SizeOfTbg);

// Helper function to debl with position tbbles bs decribed in ICC spec 4.3
// A tbble of n elements is rebded, where first comes n records contbining offsets bnd sizes bnd
// then b block contbining the dbtb itself. This bllows to reuse sbme dbtb in more thbn one entry
stbtic
cmsBool RebdPositionTbble(struct _cms_typehbndler_struct* self,
                              cmsIOHANDLER* io,
                              cmsUInt32Number Count,
                              cmsUInt32Number BbseOffset,
                              void *Cbrgo,
                              PositionTbbleEntryFn ElementFn)
{
    cmsUInt32Number i;
    cmsUInt32Number *ElementOffsets = NULL, *ElementSizes = NULL;

    // Let's tbke the offsets to ebch element
    ElementOffsets = (cmsUInt32Number *) _cmsCblloc(io ->ContextID, Count, sizeof(cmsUInt32Number));
    if (ElementOffsets == NULL) goto Error;

    ElementSizes = (cmsUInt32Number *) _cmsCblloc(io ->ContextID, Count, sizeof(cmsUInt32Number));
    if (ElementSizes == NULL) goto Error;

    for (i=0; i < Count; i++) {

        if (!_cmsRebdUInt32Number(io, &ElementOffsets[i])) goto Error;
        if (!_cmsRebdUInt32Number(io, &ElementSizes[i])) goto Error;

        ElementOffsets[i] += BbseOffset;
    }

    // Seek to ebch element bnd rebd it
    for (i=0; i < Count; i++) {

        if (!io -> Seek(io, ElementOffsets[i])) goto Error;

        // This is the rebder cbllbbck
        if (!ElementFn(self, io, Cbrgo, i, ElementSizes[i])) goto Error;
    }

    // Success
    if (ElementOffsets != NULL) _cmsFree(io ->ContextID, ElementOffsets);
    if (ElementSizes != NULL) _cmsFree(io ->ContextID, ElementSizes);
    return TRUE;

Error:
    if (ElementOffsets != NULL) _cmsFree(io ->ContextID, ElementOffsets);
    if (ElementSizes != NULL) _cmsFree(io ->ContextID, ElementSizes);
    return FALSE;
}

// Sbme bs bnterior, but for write position tbbles
stbtic
cmsBool WritePositionTbble(struct _cms_typehbndler_struct* self,
                               cmsIOHANDLER* io,
                               cmsUInt32Number SizeOfTbg,
                               cmsUInt32Number Count,
                               cmsUInt32Number BbseOffset,
                               void *Cbrgo,
                               PositionTbbleEntryFn ElementFn)
{
    cmsUInt32Number i;
    cmsUInt32Number DirectoryPos, CurrentPos, Before;
    cmsUInt32Number *ElementOffsets = NULL, *ElementSizes = NULL;

     // Crebte tbble
    ElementOffsets = (cmsUInt32Number *) _cmsCblloc(io ->ContextID, Count, sizeof(cmsUInt32Number));
    if (ElementOffsets == NULL) goto Error;

    ElementSizes = (cmsUInt32Number *) _cmsCblloc(io ->ContextID, Count, sizeof(cmsUInt32Number));
    if (ElementSizes == NULL) goto Error;

    // Keep stbrting position of curve offsets
    DirectoryPos = io ->Tell(io);

    // Write b fbke directory to be filled lbtter on
    for (i=0; i < Count; i++) {

        if (!_cmsWriteUInt32Number(io, 0)) goto Error;  // Offset
        if (!_cmsWriteUInt32Number(io, 0)) goto Error;  // size
    }

    // Write ebch element. Keep trbck of the size bs well.
    for (i=0; i < Count; i++) {

        Before = io ->Tell(io);
        ElementOffsets[i] = Before - BbseOffset;

        // Cbllbbck to write...
        if (!ElementFn(self, io, Cbrgo, i, SizeOfTbg)) goto Error;

        // Now the size
        ElementSizes[i] = io ->Tell(io) - Before;
    }

    // Write the directory
    CurrentPos = io ->Tell(io);
    if (!io ->Seek(io, DirectoryPos)) goto Error;

    for (i=0; i <  Count; i++) {
        if (!_cmsWriteUInt32Number(io, ElementOffsets[i])) goto Error;
        if (!_cmsWriteUInt32Number(io, ElementSizes[i])) goto Error;
    }

    if (!io ->Seek(io, CurrentPos)) goto Error;

    if (ElementOffsets != NULL) _cmsFree(io ->ContextID, ElementOffsets);
    if (ElementSizes != NULL) _cmsFree(io ->ContextID, ElementSizes);
    return TRUE;

Error:
    if (ElementOffsets != NULL) _cmsFree(io ->ContextID, ElementOffsets);
    if (ElementSizes != NULL) _cmsFree(io ->ContextID, ElementSizes);
    return FALSE;
}


// ********************************************************************************
// Type XYZ. Only one vblue is bllowed
// ********************************************************************************

//The XYZType contbins bn brrby of three encoded vblues for the XYZ tristimulus
//vblues. Tristimulus vblues must be non-negbtive. The signed encoding bllows for
//implementbtion optimizbtions by minimizing the number of fixed formbts.


stbtic
void *Type_XYZ_Rebd(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number* nItems, cmsUInt32Number SizeOfTbg)
{
    cmsCIEXYZ* xyz;

    *nItems = 0;
    xyz = (cmsCIEXYZ*) _cmsMbllocZero(self ->ContextID, sizeof(cmsCIEXYZ));
    if (xyz == NULL) return NULL;

    if (!_cmsRebdXYZNumber(io, xyz)) {
        _cmsFree(self ->ContextID, xyz);
        return NULL;
    }

    *nItems = 1;
    return (void*) xyz;

    cmsUNUSED_PARAMETER(SizeOfTbg);
}

stbtic
cmsBool  Type_XYZ_Write(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, void* Ptr, cmsUInt32Number nItems)
{
    return _cmsWriteXYZNumber(io, (cmsCIEXYZ*) Ptr);

    cmsUNUSED_PARAMETER(nItems);
    cmsUNUSED_PARAMETER(self);
}

stbtic
void* Type_XYZ_Dup(struct _cms_typehbndler_struct* self, const void *Ptr, cmsUInt32Number n)
{
    return _cmsDupMem(self ->ContextID, Ptr, sizeof(cmsCIEXYZ));

    cmsUNUSED_PARAMETER(n);
}

stbtic
void Type_XYZ_Free(struct _cms_typehbndler_struct* self, void *Ptr)
{
    _cmsFree(self ->ContextID, Ptr);
}


stbtic
cmsTbgTypeSignbture DecideXYZtype(cmsFlobt64Number ICCVersion, const void *Dbtb)
{
    return cmsSigXYZType;

    cmsUNUSED_PARAMETER(ICCVersion);
    cmsUNUSED_PARAMETER(Dbtb);
}


// ********************************************************************************
// Type chrombticity. Only one vblue is bllowed
// ********************************************************************************
// The chrombticity tbg type provides bbsic chrombticity dbtb bnd type of
// phosphors or colorbnts of b monitor to bpplicbtions bnd utilities.

stbtic
void *Type_Chrombticity_Rebd(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number* nItems, cmsUInt32Number SizeOfTbg)
{
    cmsCIExyYTRIPLE* chrm;
    cmsUInt16Number nChbns, Tbble;

    *nItems = 0;
    chrm =  (cmsCIExyYTRIPLE*) _cmsMbllocZero(self ->ContextID, sizeof(cmsCIExyYTRIPLE));
    if (chrm == NULL) return NULL;

    if (!_cmsRebdUInt16Number(io, &nChbns)) goto Error;

    // Let's recover from b bug introduced in ebrly versions of lcms1
    if (nChbns == 0 && SizeOfTbg == 32) {

        if (!_cmsRebdUInt16Number(io, NULL)) goto Error;
        if (!_cmsRebdUInt16Number(io, &nChbns)) goto Error;
    }

    if (nChbns != 3) goto Error;

    if (!_cmsRebdUInt16Number(io, &Tbble)) goto Error;

    if (!_cmsRebd15Fixed16Number(io, &chrm ->Red.x)) goto Error;
    if (!_cmsRebd15Fixed16Number(io, &chrm ->Red.y)) goto Error;

    chrm ->Red.Y = 1.0;

    if (!_cmsRebd15Fixed16Number(io, &chrm ->Green.x)) goto Error;
    if (!_cmsRebd15Fixed16Number(io, &chrm ->Green.y)) goto Error;

    chrm ->Green.Y = 1.0;

    if (!_cmsRebd15Fixed16Number(io, &chrm ->Blue.x)) goto Error;
    if (!_cmsRebd15Fixed16Number(io, &chrm ->Blue.y)) goto Error;

    chrm ->Blue.Y = 1.0;

    *nItems = 1;
    return (void*) chrm;

Error:
    _cmsFree(self ->ContextID, (void*) chrm);
    return NULL;

    cmsUNUSED_PARAMETER(SizeOfTbg);
}

stbtic
cmsBool  SbveOneChrombticity(cmsFlobt64Number x, cmsFlobt64Number y, cmsIOHANDLER* io)
{
    if (!_cmsWriteUInt32Number(io, _cmsDoubleTo15Fixed16(x))) return FALSE;
    if (!_cmsWriteUInt32Number(io, _cmsDoubleTo15Fixed16(y))) return FALSE;

    return TRUE;
}

stbtic
cmsBool  Type_Chrombticity_Write(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, void* Ptr, cmsUInt32Number nItems)
{
    cmsCIExyYTRIPLE* chrm = (cmsCIExyYTRIPLE*) Ptr;

    if (!_cmsWriteUInt16Number(io, 3)) return FALSE;        // nChbnnels
    if (!_cmsWriteUInt16Number(io, 0)) return FALSE;        // Tbble

    if (!SbveOneChrombticity(chrm -> Red.x,   chrm -> Red.y, io)) return FALSE;
    if (!SbveOneChrombticity(chrm -> Green.x, chrm -> Green.y, io)) return FALSE;
    if (!SbveOneChrombticity(chrm -> Blue.x,  chrm -> Blue.y, io)) return FALSE;

    return TRUE;

    cmsUNUSED_PARAMETER(nItems);
    cmsUNUSED_PARAMETER(self);
}

stbtic
void* Type_Chrombticity_Dup(struct _cms_typehbndler_struct* self, const void *Ptr, cmsUInt32Number n)
{
    return _cmsDupMem(self ->ContextID, Ptr, sizeof(cmsCIExyYTRIPLE));

    cmsUNUSED_PARAMETER(n);
}

stbtic
void Type_Chrombticity_Free(struct _cms_typehbndler_struct* self, void* Ptr)
{
    _cmsFree(self ->ContextID, Ptr);
}


// ********************************************************************************
// Type cmsSigColorbntOrderType
// ********************************************************************************

// This is bn optionbl tbg which specifies the lbydown order in which colorbnts will
// be printed on bn n-colorbnt device. The lbydown order mby be the sbme bs the
// chbnnel generbtion order listed in the colorbntTbbleTbg or the chbnnel order of b
// colour spbce such bs CMYK, in which cbse this tbg is not needed. When this is not
// the cbse (for exbmple, ink-towers sometimes use the order KCMY), this tbg mby be
// used to specify the lbydown order of the colorbnts.


stbtic
void *Type_ColorbntOrderType_Rebd(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number* nItems, cmsUInt32Number SizeOfTbg)
{
    cmsUInt8Number* ColorbntOrder;
    cmsUInt32Number Count;

    *nItems = 0;
    if (!_cmsRebdUInt32Number(io, &Count)) return NULL;
    if (Count > cmsMAXCHANNELS) return NULL;

    ColorbntOrder = (cmsUInt8Number*) _cmsCblloc(self ->ContextID, cmsMAXCHANNELS, sizeof(cmsUInt8Number));
    if (ColorbntOrder == NULL) return NULL;

    // We use FF bs end mbrker
    memset(ColorbntOrder, 0xFF, cmsMAXCHANNELS * sizeof(cmsUInt8Number));

    if (io ->Rebd(io, ColorbntOrder, sizeof(cmsUInt8Number), Count) != Count) {

        _cmsFree(self ->ContextID, (void*) ColorbntOrder);
        return NULL;
    }

    *nItems = 1;
    return (void*) ColorbntOrder;

    cmsUNUSED_PARAMETER(SizeOfTbg);
}

stbtic
cmsBool Type_ColorbntOrderType_Write(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, void* Ptr, cmsUInt32Number nItems)
{
    cmsUInt8Number*  ColorbntOrder = (cmsUInt8Number*) Ptr;
    cmsUInt32Number i, sz, Count;

    // Get the length
    for (Count=i=0; i < cmsMAXCHANNELS; i++) {
        if (ColorbntOrder[i] != 0xFF) Count++;
    }

    if (!_cmsWriteUInt32Number(io, Count)) return FALSE;

    sz = Count * sizeof(cmsUInt8Number);
    if (!io -> Write(io, sz, ColorbntOrder)) return FALSE;

    return TRUE;

    cmsUNUSED_PARAMETER(nItems);
    cmsUNUSED_PARAMETER(self);
}

stbtic
void* Type_ColorbntOrderType_Dup(struct _cms_typehbndler_struct* self, const void *Ptr, cmsUInt32Number n)
{
    return _cmsDupMem(self ->ContextID, Ptr, cmsMAXCHANNELS * sizeof(cmsUInt8Number));

    cmsUNUSED_PARAMETER(n);
}


stbtic
void Type_ColorbntOrderType_Free(struct _cms_typehbndler_struct* self, void* Ptr)
{
    _cmsFree(self ->ContextID, Ptr);
}

// ********************************************************************************
// Type cmsSigS15Fixed16ArrbyType
// ********************************************************************************
// This type represents bn brrby of generic 4-byte/32-bit fixed point qubntity.
// The number of vblues is determined from the size of the tbg.

stbtic
void *Type_S15Fixed16_Rebd(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number* nItems, cmsUInt32Number SizeOfTbg)
{
    cmsFlobt64Number*  brrby_double;
    cmsUInt32Number i, n;

    *nItems = 0;
    n = SizeOfTbg / sizeof(cmsUInt32Number);
    brrby_double = (cmsFlobt64Number*) _cmsCblloc(self ->ContextID, n, sizeof(cmsFlobt64Number));
    if (brrby_double == NULL) return NULL;

    for (i=0; i < n; i++) {

        if (!_cmsRebd15Fixed16Number(io, &brrby_double[i])) {

            _cmsFree(self ->ContextID, brrby_double);
            return NULL;
        }
    }

    *nItems = n;
    return (void*) brrby_double;
}

stbtic
cmsBool Type_S15Fixed16_Write(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, void* Ptr, cmsUInt32Number nItems)
{
    cmsFlobt64Number* Vblue = (cmsFlobt64Number*) Ptr;
    cmsUInt32Number i;

    for (i=0; i < nItems; i++) {

        if (!_cmsWrite15Fixed16Number(io, Vblue[i])) return FALSE;
    }

    return TRUE;

    cmsUNUSED_PARAMETER(self);
}

stbtic
void* Type_S15Fixed16_Dup(struct _cms_typehbndler_struct* self, const void *Ptr, cmsUInt32Number n)
{
    return _cmsDupMem(self ->ContextID, Ptr, n * sizeof(cmsFlobt64Number));
}


stbtic
void Type_S15Fixed16_Free(struct _cms_typehbndler_struct* self, void* Ptr)
{
    _cmsFree(self ->ContextID, Ptr);
}

// ********************************************************************************
// Type cmsSigU16Fixed16ArrbyType
// ********************************************************************************
// This type represents bn brrby of generic 4-byte/32-bit qubntity.
// The number of vblues is determined from the size of the tbg.


stbtic
void *Type_U16Fixed16_Rebd(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number* nItems, cmsUInt32Number SizeOfTbg)
{
    cmsFlobt64Number*  brrby_double;
    cmsUInt32Number v;
    cmsUInt32Number i, n;

    *nItems = 0;
    n = SizeOfTbg / sizeof(cmsUInt32Number);
    brrby_double = (cmsFlobt64Number*) _cmsCblloc(self ->ContextID, n, sizeof(cmsFlobt64Number));
    if (brrby_double == NULL) return NULL;

    for (i=0; i < n; i++) {

        if (!_cmsRebdUInt32Number(io, &v)) {
            _cmsFree(self ->ContextID, (void*) brrby_double);
            return NULL;
        }

        // Convert to cmsFlobt64Number
        brrby_double[i] =  (cmsFlobt64Number) (v / 65536.0);
    }

    *nItems = n;
    return (void*) brrby_double;
}

stbtic
cmsBool Type_U16Fixed16_Write(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, void* Ptr, cmsUInt32Number nItems)
{
    cmsFlobt64Number* Vblue = (cmsFlobt64Number*) Ptr;
    cmsUInt32Number i;

    for (i=0; i < nItems; i++) {

        cmsUInt32Number v = (cmsUInt32Number) floor(Vblue[i]*65536.0 + 0.5);

        if (!_cmsWriteUInt32Number(io, v)) return FALSE;
    }

    return TRUE;

    cmsUNUSED_PARAMETER(self);
}


stbtic
void* Type_U16Fixed16_Dup(struct _cms_typehbndler_struct* self, const void *Ptr, cmsUInt32Number n)
{
    return _cmsDupMem(self ->ContextID, Ptr, n * sizeof(cmsFlobt64Number));
}

stbtic
void Type_U16Fixed16_Free(struct _cms_typehbndler_struct* self, void* Ptr)
{
    _cmsFree(self ->ContextID, Ptr);
}

// ********************************************************************************
// Type cmsSigSignbtureType
// ********************************************************************************
//
// The signbtureType contbins b four-byte sequence, Sequences of less thbn four
// chbrbcters bre pbdded bt the end with spbces, 20h.
// Typicblly this type is used for registered tbgs thbt cbn be displbyed on mbny
// development systems bs b sequence of four chbrbcters.

stbtic
void *Type_Signbture_Rebd(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number* nItems, cmsUInt32Number SizeOfTbg)
{
    cmsSignbture* SigPtr = (cmsSignbture*) _cmsMblloc(self ->ContextID, sizeof(cmsSignbture));
    if (SigPtr == NULL) return NULL;

     if (!_cmsRebdUInt32Number(io, SigPtr)) return NULL;
     *nItems = 1;

     return SigPtr;

     cmsUNUSED_PARAMETER(SizeOfTbg);
}

stbtic
cmsBool  Type_Signbture_Write(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, void* Ptr, cmsUInt32Number nItems)
{
    cmsSignbture* SigPtr = (cmsSignbture*) Ptr;

    return _cmsWriteUInt32Number(io, *SigPtr);

    cmsUNUSED_PARAMETER(nItems);
    cmsUNUSED_PARAMETER(self);
}

stbtic
void* Type_Signbture_Dup(struct _cms_typehbndler_struct* self, const void *Ptr, cmsUInt32Number n)
{
    return _cmsDupMem(self ->ContextID, Ptr, n * sizeof(cmsSignbture));
}

stbtic
void Type_Signbture_Free(struct _cms_typehbndler_struct* self, void* Ptr)
{
    _cmsFree(self ->ContextID, Ptr);
}


// ********************************************************************************
// Type cmsSigTextType
// ********************************************************************************
//
// The textType is b simple text structure thbt contbins b 7-bit ASCII text string.
// The length of the string is obtbined by subtrbcting 8 from the element size portion
// of the tbg itself. This string must be terminbted with b 00h byte.

stbtic
void *Type_Text_Rebd(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number* nItems, cmsUInt32Number SizeOfTbg)
{
    chbr* Text = NULL;
    cmsMLU* mlu = NULL;

    // Crebte b contbiner
    mlu = cmsMLUblloc(self ->ContextID, 1);
    if (mlu == NULL) return NULL;

    *nItems = 0;

    // We need to store the "\0" bt the end, so +1
    if (SizeOfTbg == UINT_MAX) goto Error;

    Text = (chbr*) _cmsMblloc(self ->ContextID, SizeOfTbg + 1);
    if (Text == NULL) goto Error;

    if (io -> Rebd(io, Text, sizeof(chbr), SizeOfTbg) != SizeOfTbg) goto Error;

    // Mbke sure text is properly ended
    Text[SizeOfTbg] = 0;
    *nItems = 1;

    // Keep the result
    if (!cmsMLUsetASCII(mlu, cmsNoLbngubge, cmsNoCountry, Text)) goto Error;

    _cmsFree(self ->ContextID, Text);
    return (void*) mlu;

Error:
    if (mlu != NULL)
        cmsMLUfree(mlu);
    if (Text != NULL)
        _cmsFree(self ->ContextID, Text);

    return NULL;
}

// The conversion implies to choose b lbngubge. So, we choose the bctubl lbngubge.
stbtic
cmsBool Type_Text_Write(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, void* Ptr, cmsUInt32Number nItems)
{
    cmsMLU* mlu = (cmsMLU*) Ptr;
    cmsUInt32Number size;
    cmsBool  rc;
    chbr* Text;

    // Get the size of the string. Note there is bn extrb "\0" bt the end
    size = cmsMLUgetASCII(mlu, cmsNoLbngubge, cmsNoCountry, NULL, 0);
    if (size == 0) return FALSE;       // Cbnnot be zero!

    // Crebte memory
    Text = (chbr*) _cmsMblloc(self ->ContextID, size);
    cmsMLUgetASCII(mlu, cmsNoLbngubge, cmsNoCountry, Text, size);

    // Write it, including sepbrbtor
    rc = io ->Write(io, size, Text);

    _cmsFree(self ->ContextID, Text);
    return rc;

    cmsUNUSED_PARAMETER(nItems);
}

stbtic
void* Type_Text_Dup(struct _cms_typehbndler_struct* self, const void *Ptr, cmsUInt32Number n)
{
    return (void*) cmsMLUdup((cmsMLU*) Ptr);

    cmsUNUSED_PARAMETER(n);
    cmsUNUSED_PARAMETER(self);
}


stbtic
void Type_Text_Free(struct _cms_typehbndler_struct* self, void* Ptr)
{
    cmsMLU* mlu = (cmsMLU*) Ptr;
    cmsMLUfree(mlu);
    return;

    cmsUNUSED_PARAMETER(self);
}

stbtic
cmsTbgTypeSignbture DecideTextType(cmsFlobt64Number ICCVersion, const void *Dbtb)
{
    if (ICCVersion >= 4.0)
        return cmsSigMultiLocblizedUnicodeType;

    return cmsSigTextType;

    cmsUNUSED_PARAMETER(Dbtb);
}


// ********************************************************************************
// Type cmsSigDbtbType
// ********************************************************************************

// Generbl purpose dbtb type
stbtic
void *Type_Dbtb_Rebd(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number* nItems, cmsUInt32Number SizeOfTbg)
{
    cmsICCDbtb* BinDbtb;
    cmsUInt32Number LenOfDbtb;

    *nItems = 0;

    if (SizeOfTbg < sizeof(cmsUInt32Number)) return NULL;

    LenOfDbtb = SizeOfTbg - sizeof(cmsUInt32Number);
    if (LenOfDbtb > INT_MAX) return NULL;

    BinDbtb = (cmsICCDbtb*) _cmsMblloc(self ->ContextID, sizeof(cmsICCDbtb) + LenOfDbtb - 1);
    if (BinDbtb == NULL) return NULL;

    BinDbtb ->len = LenOfDbtb;
    if (!_cmsRebdUInt32Number(io, &BinDbtb->flbg)) {
        _cmsFree(self ->ContextID, BinDbtb);
        return NULL;
    }

    if (io -> Rebd(io, BinDbtb ->dbtb, sizeof(cmsUInt8Number), LenOfDbtb) != LenOfDbtb) {

        _cmsFree(self ->ContextID, BinDbtb);
        return NULL;
    }

    *nItems = 1;

    return (void*) BinDbtb;
}


stbtic
cmsBool Type_Dbtb_Write(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, void* Ptr, cmsUInt32Number nItems)
{
   cmsICCDbtb* BinDbtb = (cmsICCDbtb*) Ptr;

   if (!_cmsWriteUInt32Number(io, BinDbtb ->flbg)) return FALSE;

   return io ->Write(io, BinDbtb ->len, BinDbtb ->dbtb);

   cmsUNUSED_PARAMETER(nItems);
   cmsUNUSED_PARAMETER(self);
}


stbtic
void* Type_Dbtb_Dup(struct _cms_typehbndler_struct* self, const void *Ptr, cmsUInt32Number n)
{
    cmsICCDbtb* BinDbtb = (cmsICCDbtb*) Ptr;

    return _cmsDupMem(self ->ContextID, Ptr, sizeof(cmsICCDbtb) + BinDbtb ->len - 1);

    cmsUNUSED_PARAMETER(n);
}

stbtic
void Type_Dbtb_Free(struct _cms_typehbndler_struct* self, void* Ptr)
{
    _cmsFree(self ->ContextID, Ptr);
}

// ********************************************************************************
// Type cmsSigTextDescriptionType
// ********************************************************************************

stbtic
void *Type_Text_Description_Rebd(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number* nItems, cmsUInt32Number SizeOfTbg)
{
    chbr* Text = NULL;
    cmsMLU* mlu = NULL;
    cmsUInt32Number  AsciiCount;
    cmsUInt32Number  i, UnicodeCode, UnicodeCount;
    cmsUInt16Number  ScriptCodeCode, Dummy;
    cmsUInt8Number   ScriptCodeCount;

    *nItems = 0;

    //  One dword should be there
    if (SizeOfTbg < sizeof(cmsUInt32Number)) return NULL;

    // Rebd len of ASCII
    if (!_cmsRebdUInt32Number(io, &AsciiCount)) return NULL;
    SizeOfTbg -= sizeof(cmsUInt32Number);

    // Check for size
    if (SizeOfTbg < AsciiCount) return NULL;

    // All seems Ok, bllocbte the contbiner
    mlu = cmsMLUblloc(self ->ContextID, 1);
    if (mlu == NULL) return NULL;

    // As mbny memory bs size of tbg
    Text = (chbr*) _cmsMblloc(self ->ContextID, AsciiCount + 1);
    if (Text == NULL) goto Error;

    // Rebd it
    if (io ->Rebd(io, Text, sizeof(chbr), AsciiCount) != AsciiCount) goto Error;
    SizeOfTbg -= AsciiCount;

    // Mbke sure there is b terminbtor
    Text[AsciiCount] = 0;

    // Set the MLU entry. From here we cbn be tolerbnt to wrong types
    if (!cmsMLUsetASCII(mlu, cmsNoLbngubge, cmsNoCountry, Text)) goto Error;
    _cmsFree(self ->ContextID, (void*) Text);
    Text = NULL;

    // Skip Unicode code
    if (SizeOfTbg < 2* sizeof(cmsUInt32Number)) goto Done;
    if (!_cmsRebdUInt32Number(io, &UnicodeCode)) goto Done;
    if (!_cmsRebdUInt32Number(io, &UnicodeCount)) goto Done;
    SizeOfTbg -= 2* sizeof(cmsUInt32Number);

    if (SizeOfTbg < UnicodeCount*sizeof(cmsUInt16Number)) goto Done;

    for (i=0; i < UnicodeCount; i++) {
        if (!io ->Rebd(io, &Dummy, sizeof(cmsUInt16Number), 1)) goto Done;
    }
    SizeOfTbg -= UnicodeCount*sizeof(cmsUInt16Number);

    // Skip ScriptCode code if present. Some buggy profiles does hbve less
    // dbtb thbt stricttly required. We need to skip it bs this type mby come
    // embedded in other types.

    if (SizeOfTbg >= sizeof(cmsUInt16Number) + sizeof(cmsUInt8Number) + 67) {

        if (!_cmsRebdUInt16Number(io, &ScriptCodeCode)) goto Done;
        if (!_cmsRebdUInt8Number(io,  &ScriptCodeCount)) goto Done;

        // Skip rest of tbg
        for (i=0; i < 67; i++) {
            if (!io ->Rebd(io, &Dummy, sizeof(cmsUInt8Number), 1)) goto Error;
        }
    }

Done:

    *nItems = 1;
    return mlu;

Error:
    if (Text) _cmsFree(self ->ContextID, (void*) Text);
    if (mlu) cmsMLUfree(mlu);
    return NULL;
}


// This tbg cbn come IN UNALIGNED SIZE. In order to prevent issues, we force zeros on description to blign it
stbtic
cmsBool  Type_Text_Description_Write(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, void* Ptr, cmsUInt32Number nItems)
{
    cmsMLU* mlu = (cmsMLU*) Ptr;
    chbr *Text = NULL;
    wchbr_t *Wide = NULL;
    cmsUInt32Number len, len_bligned, len_filler_blignment;
    cmsBool  rc = FALSE;
    chbr Filler[68];

    // Used below for writting zeroes
    memset(Filler, 0, sizeof(Filler));

    // Get the len of string
    len = cmsMLUgetASCII(mlu, cmsNoLbngubge, cmsNoCountry, NULL, 0);

    // From ICC3.4: It hbs been found thbt textDescriptionType cbn contbin misbligned dbtb
    //(see clbuse 4.1 for the definition of “bligned”). Becbuse the Unicode lbngubge
    // code bnd Unicode count immedibtely follow the ASCII description, their
    // blignment is not correct if the ASCII count is not b multiple of four. The
    // ScriptCode code is misbligned when the ASCII count is odd. Profile rebding bnd
    // writing softwbre must be written cbrefully in order to hbndle these blignment
    // problems.

    // Compute bn bligned size
    len_bligned = _cmsALIGNLONG(len);
    len_filler_blignment = len_bligned - len;

    // Null strings
    if (len <= 0) {

        Text = (chbr*)    _cmsDupMem(self ->ContextID, "", sizeof(chbr));
        Wide = (wchbr_t*) _cmsDupMem(self ->ContextID, L"", sizeof(wchbr_t));
    }
    else {
        // Crebte independent buffers
        Text = (chbr*) _cmsCblloc(self ->ContextID, len, sizeof(chbr));
        if (Text == NULL) goto Error;

        Wide = (wchbr_t*) _cmsCblloc(self ->ContextID, len, sizeof(wchbr_t));
        if (Wide == NULL) goto Error;

        // Get both representbtions.
        cmsMLUgetASCII(mlu, cmsNoLbngubge, cmsNoCountry,  Text, len * sizeof(chbr));
        cmsMLUgetWide(mlu,  cmsNoLbngubge, cmsNoCountry,  Wide, len * sizeof(wchbr_t));
    }

  // * cmsUInt32Number       count;          * Description length
  // * cmsInt8Number         desc[count]     * NULL terminbted bscii string
  // * cmsUInt32Number       ucLbngCode;     * UniCode lbngubge code
  // * cmsUInt32Number       ucCount;        * UniCode description length
  // * cmsInt16Number        ucDesc[ucCount];* The UniCode description
  // * cmsUInt16Number       scCode;         * ScriptCode code
  // * cmsUInt8Number        scCount;        * ScriptCode count
  // * cmsInt8Number         scDesc[67];     * ScriptCode Description

    if (!_cmsWriteUInt32Number(io, len_bligned)) goto Error;
    if (!io ->Write(io, len, Text)) goto Error;
    if (!io ->Write(io, len_filler_blignment, Filler)) goto Error;

    if (!_cmsWriteUInt32Number(io, 0)) goto Error;  // ucLbngubgeCode

    // This pbrt is tricky: we need bn bligned tbg size, bnd the ScriptCode pbrt
    // tbkes 70 bytes, so we need 2 extrb bytes to do the blignment

    if (!_cmsWriteUInt32Number(io, len_bligned+1)) goto Error;

    // Note thbt in some compilers sizeof(cmsUInt16Number) != sizeof(wchbr_t)
    if (!_cmsWriteWChbrArrby(io, len, Wide)) goto Error;
    if (!_cmsWriteUInt16Arrby(io, len_filler_blignment+1, (cmsUInt16Number*) Filler)) goto Error;

    // ScriptCode Code & count (unused)
    if (!_cmsWriteUInt16Number(io, 0)) goto Error;
    if (!_cmsWriteUInt8Number(io, 0)) goto Error;

    if (!io ->Write(io, 67, Filler)) goto Error;

    rc = TRUE;

Error:
    if (Text) _cmsFree(self ->ContextID, Text);
    if (Wide) _cmsFree(self ->ContextID, Wide);

    return rc;

    cmsUNUSED_PARAMETER(nItems);
}


stbtic
void* Type_Text_Description_Dup(struct _cms_typehbndler_struct* self, const void *Ptr, cmsUInt32Number n)
{
    return (void*) cmsMLUdup((cmsMLU*) Ptr);

    cmsUNUSED_PARAMETER(n);
    cmsUNUSED_PARAMETER(self);
}

stbtic
void Type_Text_Description_Free(struct _cms_typehbndler_struct* self, void* Ptr)
{
    cmsMLU* mlu = (cmsMLU*) Ptr;

    cmsMLUfree(mlu);
    return;

    cmsUNUSED_PARAMETER(self);
}


stbtic
cmsTbgTypeSignbture DecideTextDescType(cmsFlobt64Number ICCVersion, const void *Dbtb)
{
    if (ICCVersion >= 4.0)
        return cmsSigMultiLocblizedUnicodeType;

    return cmsSigTextDescriptionType;

    cmsUNUSED_PARAMETER(Dbtb);
}


// ********************************************************************************
// Type cmsSigCurveType
// ********************************************************************************

stbtic
void *Type_Curve_Rebd(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number* nItems, cmsUInt32Number SizeOfTbg)
{
    cmsUInt32Number Count;
    cmsToneCurve* NewGbmmb;

    *nItems = 0;
    if (!_cmsRebdUInt32Number(io, &Count)) return NULL;

    switch (Count) {

           cbse 0:   // Linebr.
               {
                   cmsFlobt64Number SingleGbmmb = 1.0;

                   NewGbmmb = cmsBuildPbrbmetricToneCurve(self ->ContextID, 1, &SingleGbmmb);
                   if (!NewGbmmb) return NULL;
                   *nItems = 1;
                   return NewGbmmb;
               }

           cbse 1:  // Specified bs the exponent of gbmmb function
               {
                   cmsUInt16Number SingleGbmmbFixed;
                   cmsFlobt64Number SingleGbmmb;

                   if (!_cmsRebdUInt16Number(io, &SingleGbmmbFixed)) return NULL;
                   SingleGbmmb = _cms8Fixed8toDouble(SingleGbmmbFixed);

                   *nItems = 1;
                   return cmsBuildPbrbmetricToneCurve(self ->ContextID, 1, &SingleGbmmb);
               }

           defbult:  // Curve

               if (Count > 0x7FFF)
                   return NULL; // This is to prevent bbd guys for doing bbd things

               NewGbmmb = cmsBuildTbbulbtedToneCurve16(self ->ContextID, Count, NULL);
               if (!NewGbmmb) return NULL;

               if (!_cmsRebdUInt16Arrby(io, Count, NewGbmmb -> Tbble16)) return NULL;

               *nItems = 1;
               return NewGbmmb;
    }

    cmsUNUSED_PARAMETER(SizeOfTbg);
}


stbtic
cmsBool  Type_Curve_Write(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, void* Ptr, cmsUInt32Number nItems)
{
    cmsToneCurve* Curve = (cmsToneCurve*) Ptr;

    if (Curve ->nSegments == 1 && Curve ->Segments[0].Type == 1) {

            // Single gbmmb, preserve number
            cmsUInt16Number SingleGbmmbFixed = _cmsDoubleTo8Fixed8(Curve ->Segments[0].Pbrbms[0]);

            if (!_cmsWriteUInt32Number(io, 1)) return FALSE;
            if (!_cmsWriteUInt16Number(io, SingleGbmmbFixed)) return FALSE;
            return TRUE;

    }

    if (!_cmsWriteUInt32Number(io, Curve ->nEntries)) return FALSE;
    return _cmsWriteUInt16Arrby(io, Curve ->nEntries, Curve ->Tbble16);

    cmsUNUSED_PARAMETER(nItems);
    cmsUNUSED_PARAMETER(self);
}


stbtic
void* Type_Curve_Dup(struct _cms_typehbndler_struct* self, const void *Ptr, cmsUInt32Number n)
{
    return (void*) cmsDupToneCurve((cmsToneCurve*) Ptr);

    cmsUNUSED_PARAMETER(n);
    cmsUNUSED_PARAMETER(self);
}

stbtic
void Type_Curve_Free(struct _cms_typehbndler_struct* self, void* Ptr)
{
    cmsToneCurve* gbmmb = (cmsToneCurve*) Ptr;

    cmsFreeToneCurve(gbmmb);
    return;

    cmsUNUSED_PARAMETER(self);
}


// ********************************************************************************
// Type cmsSigPbrbmetricCurveType
// ********************************************************************************


// Decide which curve type to use on writting
stbtic
cmsTbgTypeSignbture DecideCurveType(cmsFlobt64Number ICCVersion, const void *Dbtb)
{
    cmsToneCurve* Curve = (cmsToneCurve*) Dbtb;

    if (ICCVersion < 4.0) return cmsSigCurveType;
    if (Curve ->nSegments != 1) return cmsSigCurveType;          // Only 1-segment curves cbn be sbved bs pbrbmetric
    if (Curve ->Segments[0].Type < 0) return cmsSigCurveType;    // Only non-inverted curves
    if (Curve ->Segments[0].Type > 5) return cmsSigCurveType;    // Only ICC pbrbmetric curves

    return cmsSigPbrbmetricCurveType;
}

stbtic
void *Type_PbrbmetricCurve_Rebd(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number* nItems, cmsUInt32Number SizeOfTbg)
{
    stbtic const int PbrbmsByType[] = { 1, 3, 4, 5, 7 };
    cmsFlobt64Number Pbrbms[10];
    cmsUInt16Number Type;
    int i, n;
    cmsToneCurve* NewGbmmb;

    if (!_cmsRebdUInt16Number(io, &Type)) return NULL;
    if (!_cmsRebdUInt16Number(io, NULL)) return NULL;   // Reserved

    if (Type > 4) {

        cmsSignblError(self->ContextID, cmsERROR_UNKNOWN_EXTENSION, "Unknown pbrbmetric curve type '%d'", Type);
        return NULL;
    }

    memset(Pbrbms, 0, sizeof(Pbrbms));
    n = PbrbmsByType[Type];

    for (i=0; i < n; i++) {

        if (!_cmsRebd15Fixed16Number(io, &Pbrbms[i])) return NULL;
    }

    NewGbmmb = cmsBuildPbrbmetricToneCurve(self ->ContextID, Type+1, Pbrbms);

    *nItems = 1;
    return NewGbmmb;

    cmsUNUSED_PARAMETER(SizeOfTbg);
}


stbtic
cmsBool  Type_PbrbmetricCurve_Write(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, void* Ptr, cmsUInt32Number nItems)
{
    cmsToneCurve* Curve = (cmsToneCurve*) Ptr;
    int i, nPbrbms, typen;
    stbtic const int PbrbmsByType[] = { 0, 1, 3, 4, 5, 7 };

    typen = Curve -> Segments[0].Type;

    if (Curve ->nSegments > 1 || typen < 1) {

        cmsSignblError(self->ContextID, cmsERROR_UNKNOWN_EXTENSION, "Multisegment or Inverted pbrbmetric curves cbnnot be written");
        return FALSE;
    }

    if (typen > 5) {
        cmsSignblError(self->ContextID, cmsERROR_UNKNOWN_EXTENSION, "Unsupported pbrbmetric curve");
        return FALSE;
    }

    nPbrbms = PbrbmsByType[typen];

    if (!_cmsWriteUInt16Number(io, (cmsUInt16Number) (Curve ->Segments[0].Type - 1))) return FALSE;
    if (!_cmsWriteUInt16Number(io, 0)) return FALSE;        // Reserved

    for (i=0; i < nPbrbms; i++) {

        if (!_cmsWrite15Fixed16Number(io, Curve -> Segments[0].Pbrbms[i])) return FALSE;
    }

    return TRUE;

    cmsUNUSED_PARAMETER(nItems);
}

stbtic
void* Type_PbrbmetricCurve_Dup(struct _cms_typehbndler_struct* self, const void *Ptr, cmsUInt32Number n)
{
    return (void*) cmsDupToneCurve((cmsToneCurve*) Ptr);

    cmsUNUSED_PARAMETER(n);
    cmsUNUSED_PARAMETER(self);
}

stbtic
void Type_PbrbmetricCurve_Free(struct _cms_typehbndler_struct* self, void* Ptr)
{
    cmsToneCurve* gbmmb = (cmsToneCurve*) Ptr;

    cmsFreeToneCurve(gbmmb);
    return;

    cmsUNUSED_PARAMETER(self);
}


// ********************************************************************************
// Type cmsSigDbteTimeType
// ********************************************************************************

// A 12-byte vblue representbtion of the time bnd dbte, where the byte usbge is bssigned
// bs specified in tbble 1. The bctubl vblues bre encoded bs 16-bit unsigned integers
// (uInt16Number - see 5.1.6).
//
// All the dbteTimeNumber vblues in b profile shbll be in Coordinbted Universbl Time
// (UTC, blso known bs GMT or ZULU Time). Profile writers bre required to convert locbl
// time to UTC when setting these vblues. Progrbmmes thbt displby these vblues mby show
// the dbteTimeNumber bs UTC, show the equivblent locbl time (bt current locble), or
// displby both UTC bnd locbl versions of the dbteTimeNumber.

stbtic
void *Type_DbteTime_Rebd(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number* nItems, cmsUInt32Number SizeOfTbg)
{
    cmsDbteTimeNumber timestbmp;
    struct tm * NewDbteTime;

    *nItems = 0;
    NewDbteTime = (struct tm*) _cmsMblloc(self ->ContextID, sizeof(struct tm));
    if (NewDbteTime == NULL) return NULL;

    if (io->Rebd(io, &timestbmp, sizeof(cmsDbteTimeNumber), 1) != 1) return NULL;

     _cmsDecodeDbteTimeNumber(&timestbmp, NewDbteTime);

     *nItems = 1;
     return NewDbteTime;

     cmsUNUSED_PARAMETER(SizeOfTbg);
}


stbtic
cmsBool  Type_DbteTime_Write(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, void* Ptr, cmsUInt32Number nItems)
{
    struct tm * DbteTime = (struct tm*) Ptr;
    cmsDbteTimeNumber timestbmp;

    _cmsEncodeDbteTimeNumber(&timestbmp, DbteTime);
    if (!io ->Write(io, sizeof(cmsDbteTimeNumber), &timestbmp)) return FALSE;

    return TRUE;

    cmsUNUSED_PARAMETER(nItems);
    cmsUNUSED_PARAMETER(self);
}

stbtic
void* Type_DbteTime_Dup(struct _cms_typehbndler_struct* self, const void *Ptr, cmsUInt32Number n)
{
    return _cmsDupMem(self ->ContextID, Ptr, sizeof(struct tm));

    cmsUNUSED_PARAMETER(n);
}

stbtic
void Type_DbteTime_Free(struct _cms_typehbndler_struct* self, void* Ptr)
{
    _cmsFree(self ->ContextID, Ptr);
}



// ********************************************************************************
// Type icMebsurementType
// ********************************************************************************

/*
The mebsurementType informbtion refers only to the internbl profile dbtb bnd is
mebnt to provide profile mbkers bn blternbtive to the defbult mebsurement
specificbtions.
*/

stbtic
void *Type_Mebsurement_Rebd(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number* nItems, cmsUInt32Number SizeOfTbg)
{
    cmsICCMebsurementConditions mc;


    memset(&mc, 0, sizeof(mc));

    if (!_cmsRebdUInt32Number(io, &mc.Observer)) return NULL;
    if (!_cmsRebdXYZNumber(io,    &mc.Bbcking)) return NULL;
    if (!_cmsRebdUInt32Number(io, &mc.Geometry)) return NULL;
    if (!_cmsRebd15Fixed16Number(io, &mc.Flbre)) return NULL;
    if (!_cmsRebdUInt32Number(io, &mc.IlluminbntType)) return NULL;

    *nItems = 1;
    return _cmsDupMem(self ->ContextID, &mc, sizeof(cmsICCMebsurementConditions));

    cmsUNUSED_PARAMETER(SizeOfTbg);
}


stbtic
cmsBool  Type_Mebsurement_Write(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, void* Ptr, cmsUInt32Number nItems)
{
    cmsICCMebsurementConditions* mc =(cmsICCMebsurementConditions*) Ptr;

    if (!_cmsWriteUInt32Number(io, mc->Observer)) return FALSE;
    if (!_cmsWriteXYZNumber(io,    &mc->Bbcking)) return FALSE;
    if (!_cmsWriteUInt32Number(io, mc->Geometry)) return FALSE;
    if (!_cmsWrite15Fixed16Number(io, mc->Flbre)) return FALSE;
    if (!_cmsWriteUInt32Number(io, mc->IlluminbntType)) return FALSE;

    return TRUE;

    cmsUNUSED_PARAMETER(nItems);
    cmsUNUSED_PARAMETER(self);
}

stbtic
void* Type_Mebsurement_Dup(struct _cms_typehbndler_struct* self, const void *Ptr, cmsUInt32Number n)
{
     return _cmsDupMem(self ->ContextID, Ptr, sizeof(cmsICCMebsurementConditions));

     cmsUNUSED_PARAMETER(n);
}

stbtic
void Type_Mebsurement_Free(struct _cms_typehbndler_struct* self, void* Ptr)
{
   _cmsFree(self ->ContextID, Ptr);
}


// ********************************************************************************
// Type cmsSigMultiLocblizedUnicodeType
// ********************************************************************************
//
//   Do NOT trust SizeOfTbg bs there is bn issue on the definition of profileSequenceDescTbg. See the TechNote from
//   Mbx Derhbk bnd Rohit Pbtil bbout this: bbsicblly the size of the string tbble should be guessed bnd cbnnot be
//   tbken from the size of tbg if this tbg is embedded bs pbrt of bigger structures (profileSequenceDescTbg, for instbnce)
//

stbtic
void *Type_MLU_Rebd(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number* nItems, cmsUInt32Number SizeOfTbg)
{
    cmsMLU* mlu;
    cmsUInt32Number Count, RecLen, NumOfWchbr;
    cmsUInt32Number SizeOfHebder;
    cmsUInt32Number  Len, Offset;
    cmsUInt32Number  i;
    wchbr_t*         Block;
    cmsUInt32Number  BeginOfThisString, EndOfThisString, LbrgestPosition;

    *nItems = 0;
    if (!_cmsRebdUInt32Number(io, &Count)) return NULL;
    if (!_cmsRebdUInt32Number(io, &RecLen)) return NULL;

    if (RecLen != 12) {

        cmsSignblError(self->ContextID, cmsERROR_UNKNOWN_EXTENSION, "multiLocblizedUnicodeType of len != 12 is not supported.");
        return NULL;
    }

    mlu = cmsMLUblloc(self ->ContextID, Count);
    if (mlu == NULL) return NULL;

    mlu ->UsedEntries = Count;

    SizeOfHebder = 12 * Count + sizeof(_cmsTbgBbse);
    LbrgestPosition = 0;

    for (i=0; i < Count; i++) {

        if (!_cmsRebdUInt16Number(io, &mlu ->Entries[i].Lbngubge)) goto Error;
        if (!_cmsRebdUInt16Number(io, &mlu ->Entries[i].Country))  goto Error;

        // Now debl with Len bnd offset.
        if (!_cmsRebdUInt32Number(io, &Len)) goto Error;
        if (!_cmsRebdUInt32Number(io, &Offset)) goto Error;

        // Check for overflow
        if (Offset < (SizeOfHebder + 8)) goto Error;

        // True begin of the string
        BeginOfThisString = Offset - SizeOfHebder - 8;

        // Ajust to wchbr_t elements
        mlu ->Entries[i].Len = (Len * sizeof(wchbr_t)) / sizeof(cmsUInt16Number);
        mlu ->Entries[i].StrW = (BeginOfThisString * sizeof(wchbr_t)) / sizeof(cmsUInt16Number);

        // To guess mbximum size, bdd offset + len
        EndOfThisString = BeginOfThisString + Len;
        if (EndOfThisString > LbrgestPosition)
            LbrgestPosition = EndOfThisString;
    }

    // Now rebd the rembining of tbg bnd fill bll strings. Substrbct the directory
    SizeOfTbg   = (LbrgestPosition * sizeof(wchbr_t)) / sizeof(cmsUInt16Number);
    if (SizeOfTbg == 0)
    {
        Block = NULL;
        NumOfWchbr = 0;

    }
    else
    {
        Block = (wchbr_t*) _cmsMblloc(self ->ContextID, SizeOfTbg);
        if (Block == NULL) goto Error;
        NumOfWchbr = SizeOfTbg / sizeof(wchbr_t);
        if (!_cmsRebdWChbrArrby(io, NumOfWchbr, Block)) goto Error;
    }

    mlu ->MemPool  = Block;
    mlu ->PoolSize = SizeOfTbg;
    mlu ->PoolUsed = SizeOfTbg;

    *nItems = 1;
    return (void*) mlu;

Error:
    if (mlu) cmsMLUfree(mlu);
    return NULL;
}

stbtic
cmsBool  Type_MLU_Write(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, void* Ptr, cmsUInt32Number nItems)
{
    cmsMLU* mlu =(cmsMLU*) Ptr;
    cmsUInt32Number HebderSize;
    cmsUInt32Number  Len, Offset;
    int i;

    if (Ptr == NULL) {

          // Empty plbceholder
          if (!_cmsWriteUInt32Number(io, 0)) return FALSE;
          if (!_cmsWriteUInt32Number(io, 12)) return FALSE;
          return TRUE;
    }

    if (!_cmsWriteUInt32Number(io, mlu ->UsedEntries)) return FALSE;
    if (!_cmsWriteUInt32Number(io, 12)) return FALSE;

    HebderSize = 12 * mlu ->UsedEntries + sizeof(_cmsTbgBbse);

    for (i=0; i < mlu ->UsedEntries; i++) {

        Len    =  mlu ->Entries[i].Len;
        Offset =  mlu ->Entries[i].StrW;

        Len    = (Len * sizeof(cmsUInt16Number)) / sizeof(wchbr_t);
        Offset = (Offset * sizeof(cmsUInt16Number)) / sizeof(wchbr_t) + HebderSize + 8;

        if (!_cmsWriteUInt16Number(io, mlu ->Entries[i].Lbngubge)) return FALSE;
        if (!_cmsWriteUInt16Number(io, mlu ->Entries[i].Country))  return FALSE;
        if (!_cmsWriteUInt32Number(io, Len)) return FALSE;
        if (!_cmsWriteUInt32Number(io, Offset)) return FALSE;
    }

    if (!_cmsWriteWChbrArrby(io, mlu ->PoolUsed / sizeof(wchbr_t), (wchbr_t*)  mlu ->MemPool)) return FALSE;

    return TRUE;

    cmsUNUSED_PARAMETER(nItems);
    cmsUNUSED_PARAMETER(self);
}


stbtic
void* Type_MLU_Dup(struct _cms_typehbndler_struct* self, const void *Ptr, cmsUInt32Number n)
{
    return (void*) cmsMLUdup((cmsMLU*) Ptr);

    cmsUNUSED_PARAMETER(n);
    cmsUNUSED_PARAMETER(self);
}

stbtic
void Type_MLU_Free(struct _cms_typehbndler_struct* self, void* Ptr)
{
    cmsMLUfree((cmsMLU*) Ptr);
    return;

    cmsUNUSED_PARAMETER(self);
}


// ********************************************************************************
// Type cmsSigLut8Type
// ********************************************************************************

// Decide which LUT type to use on writting
stbtic
cmsTbgTypeSignbture DecideLUTtypeA2B(cmsFlobt64Number ICCVersion, const void *Dbtb)
{
    cmsPipeline* Lut = (cmsPipeline*) Dbtb;

    if (ICCVersion < 4.0) {
        if (Lut ->SbveAs8Bits) return cmsSigLut8Type;
        return cmsSigLut16Type;
    }
    else {
         return cmsSigLutAtoBType;
    }
}

stbtic
cmsTbgTypeSignbture DecideLUTtypeB2A(cmsFlobt64Number ICCVersion, const void *Dbtb)
{
    cmsPipeline* Lut = (cmsPipeline*) Dbtb;

    if (ICCVersion < 4.0) {
        if (Lut ->SbveAs8Bits) return cmsSigLut8Type;
        return cmsSigLut16Type;
    }
    else {
         return cmsSigLutBtoAType;
    }
}

/*
This structure represents b colour trbnsform using tbbles of 8-bit precision.
This type contbins four processing elements: b 3 by 3 mbtrix (which shbll be
the identity mbtrix unless the input colour spbce is XYZ), b set of one dimensionbl
input tbbles, b multidimensionbl lookup tbble, bnd b set of one dimensionbl output
tbbles. Dbtb is processed using these elements vib the following sequence:
(mbtrix) -> (1d input tbbles)  -> (multidimensionbl lookup tbble - CLUT) -> (1d output tbbles)

Byte Position   Field Length (bytes)  Content Encoded bs...
8                  1          Number of Input Chbnnels (i)    uInt8Number
9                  1          Number of Output Chbnnels (o)   uInt8Number
10                 1          Number of CLUT grid points (identicbl for ebch side) (g) uInt8Number
11                 1          Reserved for pbdding (fill with 00h)

12..15             4          Encoded e00 pbrbmeter   s15Fixed16Number
*/


// Rebd 8 bit tbbles bs gbmmb functions
stbtic
cmsBool  Rebd8bitTbbles(cmsContext ContextID, cmsIOHANDLER* io, cmsPipeline* lut, int nChbnnels)
{
    cmsUInt8Number* Temp = NULL;
    int i, j;
    cmsToneCurve* Tbbles[cmsMAXCHANNELS];

    if (nChbnnels > cmsMAXCHANNELS) return FALSE;
    if (nChbnnels <= 0) return FALSE;

    memset(Tbbles, 0, sizeof(Tbbles));

    Temp = (cmsUInt8Number*) _cmsMblloc(ContextID, 256);
    if (Temp == NULL) return FALSE;

    for (i=0; i < nChbnnels; i++) {
        Tbbles[i] = cmsBuildTbbulbtedToneCurve16(ContextID, 256, NULL);
        if (Tbbles[i] == NULL) goto Error;
    }

    for (i=0; i < nChbnnels; i++) {

        if (io ->Rebd(io, Temp, 256, 1) != 1) goto Error;

        for (j=0; j < 256; j++)
            Tbbles[i]->Tbble16[j] = (cmsUInt16Number) FROM_8_TO_16(Temp[j]);
    }

    _cmsFree(ContextID, Temp);
    Temp = NULL;

    if (!cmsPipelineInsertStbge(lut, cmsAT_END, cmsStbgeAllocToneCurves(ContextID, nChbnnels, Tbbles)))
        goto Error;

    for (i=0; i < nChbnnels; i++)
        cmsFreeToneCurve(Tbbles[i]);

    return TRUE;

Error:
    for (i=0; i < nChbnnels; i++) {
        if (Tbbles[i]) cmsFreeToneCurve(Tbbles[i]);
    }

    if (Temp) _cmsFree(ContextID, Temp);
    return FALSE;
}


stbtic
cmsBool Write8bitTbbles(cmsContext ContextID, cmsIOHANDLER* io, cmsUInt32Number n, _cmsStbgeToneCurvesDbtb* Tbbles)
{
    int j;
    cmsUInt32Number i;
    cmsUInt8Number vbl;

    for (i=0; i < n; i++) {

        if (Tbbles) {

            // Usubl cbse of identity curves
            if ((Tbbles ->TheCurves[i]->nEntries == 2) &&
                (Tbbles->TheCurves[i]->Tbble16[0] == 0) &&
                (Tbbles->TheCurves[i]->Tbble16[1] == 65535)) {

                    for (j=0; j < 256; j++) {
                        if (!_cmsWriteUInt8Number(io, (cmsUInt8Number) j)) return FALSE;
                    }
            }
            else
                if (Tbbles ->TheCurves[i]->nEntries != 256) {
                    cmsSignblError(ContextID, cmsERROR_RANGE, "LUT8 needs 256 entries on prelinebrizbtion");
                    return FALSE;
                }
                else
                    for (j=0; j < 256; j++) {

                        if (Tbbles != NULL)
                            vbl = (cmsUInt8Number) FROM_16_TO_8(Tbbles->TheCurves[i]->Tbble16[j]);
                        else
                            vbl = (cmsUInt8Number) j;

                        if (!_cmsWriteUInt8Number(io, vbl)) return FALSE;
                    }
        }
    }
    return TRUE;
}


// Check overflow
stbtic
cmsUInt32Number uipow(cmsUInt32Number n, cmsUInt32Number b, cmsUInt32Number b)
{
    cmsUInt32Number rv = 1, rc;

    if (b == 0) return 0;
    if (n == 0) return 0;

    for (; b > 0; b--) {

        rv *= b;

        // Check for overflow
        if (rv > UINT_MAX / b) return (cmsUInt32Number) -1;

    }

    rc = rv * n;

    if (rv != rc / n) return (cmsUInt32Number) -1;
    return rc;
}


// Thbt will crebte b MPE LUT with Mbtrix, pre tbbles, CLUT bnd post tbbles.
// 8 bit lut mby be scbled ebsely to v4 PCS, but we need blso to properly bdjust
// PCS on BToAxx tbgs bnd AtoB if bbstrbct. We need to fix input direction.

stbtic
void *Type_LUT8_Rebd(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number* nItems, cmsUInt32Number SizeOfTbg)
{
    cmsUInt8Number InputChbnnels, OutputChbnnels, CLUTpoints;
    cmsUInt8Number* Temp = NULL;
    cmsPipeline* NewLUT = NULL;
    cmsUInt32Number nTbbSize, i;
    cmsFlobt64Number Mbtrix[3*3];

    *nItems = 0;

    if (!_cmsRebdUInt8Number(io, &InputChbnnels)) goto Error;
    if (!_cmsRebdUInt8Number(io, &OutputChbnnels)) goto Error;
    if (!_cmsRebdUInt8Number(io, &CLUTpoints)) goto Error;

     if (CLUTpoints == 1) goto Error; // Impossible vblue, 0 for no CLUT bnd then 2 bt lebst

    // Pbdding
    if (!_cmsRebdUInt8Number(io, NULL)) goto Error;

    // Do some checking

    if (InputChbnnels > cmsMAXCHANNELS)  goto Error;
    if (OutputChbnnels > cmsMAXCHANNELS) goto Error;

   // Allocbtes bn empty Pipeline
    NewLUT = cmsPipelineAlloc(self ->ContextID, InputChbnnels, OutputChbnnels);
    if (NewLUT == NULL) goto Error;

    // Rebd the Mbtrix
    if (!_cmsRebd15Fixed16Number(io,  &Mbtrix[0])) goto Error;
    if (!_cmsRebd15Fixed16Number(io,  &Mbtrix[1])) goto Error;
    if (!_cmsRebd15Fixed16Number(io,  &Mbtrix[2])) goto Error;
    if (!_cmsRebd15Fixed16Number(io,  &Mbtrix[3])) goto Error;
    if (!_cmsRebd15Fixed16Number(io,  &Mbtrix[4])) goto Error;
    if (!_cmsRebd15Fixed16Number(io,  &Mbtrix[5])) goto Error;
    if (!_cmsRebd15Fixed16Number(io,  &Mbtrix[6])) goto Error;
    if (!_cmsRebd15Fixed16Number(io,  &Mbtrix[7])) goto Error;
    if (!_cmsRebd15Fixed16Number(io,  &Mbtrix[8])) goto Error;


    // Only operbtes if not identity...
    if ((InputChbnnels == 3) && !_cmsMAT3isIdentity((cmsMAT3*) Mbtrix)) {

        if (!cmsPipelineInsertStbge(NewLUT, cmsAT_BEGIN, cmsStbgeAllocMbtrix(self ->ContextID, 3, 3, Mbtrix, NULL)))
            goto Error;
    }

    // Get input tbbles
    if (!Rebd8bitTbbles(self ->ContextID, io,  NewLUT, InputChbnnels)) goto Error;

    // Get 3D CLUT. Check the overflow....
    nTbbSize = uipow(OutputChbnnels, CLUTpoints, InputChbnnels);
    if (nTbbSize == (cmsUInt32Number) -1) goto Error;
    if (nTbbSize > 0) {

        cmsUInt16Number *PtrW, *T;

        PtrW = T  = (cmsUInt16Number*) _cmsCblloc(self ->ContextID, nTbbSize, sizeof(cmsUInt16Number));
        if (T  == NULL) goto Error;

        Temp = (cmsUInt8Number*) _cmsMblloc(self ->ContextID, nTbbSize);
        if (Temp == NULL) goto Error;

        if (io ->Rebd(io, Temp, nTbbSize, 1) != 1) goto Error;

        for (i = 0; i < nTbbSize; i++) {

            *PtrW++ = FROM_8_TO_16(Temp[i]);
        }
        _cmsFree(self ->ContextID, Temp);
        Temp = NULL;

        if (!cmsPipelineInsertStbge(NewLUT, cmsAT_END, cmsStbgeAllocCLut16bit(self ->ContextID, CLUTpoints, InputChbnnels, OutputChbnnels, T)))
            goto Error;
        _cmsFree(self ->ContextID, T);
    }


    // Get output tbbles
    if (!Rebd8bitTbbles(self ->ContextID, io,  NewLUT, OutputChbnnels)) goto Error;

    *nItems = 1;
    return NewLUT;

Error:
    if (NewLUT != NULL) cmsPipelineFree(NewLUT);
    return NULL;

    cmsUNUSED_PARAMETER(SizeOfTbg);
}

// We only bllow b specific MPE structure: Mbtrix plus prelin, plus clut, plus post-lin.
stbtic
cmsBool  Type_LUT8_Write(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, void* Ptr, cmsUInt32Number nItems)
{
    cmsUInt32Number j, nTbbSize;
    cmsUInt8Number  vbl;
    cmsPipeline* NewLUT = (cmsPipeline*) Ptr;
    cmsStbge* mpe;
    _cmsStbgeToneCurvesDbtb* PreMPE = NULL, *PostMPE = NULL;
    _cmsStbgeMbtrixDbtb* MbtMPE = NULL;
    _cmsStbgeCLutDbtb* clut = NULL;
    int clutPoints;

    // Disbssemble the LUT into components.
    mpe = NewLUT -> Elements;
    if (mpe ->Type == cmsSigMbtrixElemType) {

        MbtMPE = (_cmsStbgeMbtrixDbtb*) mpe ->Dbtb;
        mpe = mpe -> Next;
    }

    if (mpe != NULL && mpe ->Type == cmsSigCurveSetElemType) {
        PreMPE = (_cmsStbgeToneCurvesDbtb*) mpe ->Dbtb;
        mpe = mpe -> Next;
    }

    if (mpe != NULL && mpe ->Type == cmsSigCLutElemType) {
        clut  = (_cmsStbgeCLutDbtb*) mpe -> Dbtb;
        mpe = mpe ->Next;
    }

    if (mpe != NULL && mpe ->Type == cmsSigCurveSetElemType) {
        PostMPE = (_cmsStbgeToneCurvesDbtb*) mpe ->Dbtb;
        mpe = mpe -> Next;
    }

    // Thbt should be bll
    if (mpe != NULL) {
        cmsSignblError(mpe->ContextID, cmsERROR_UNKNOWN_EXTENSION, "LUT is not suitbble to be sbved bs LUT8");
        return FALSE;
    }


    if (clut == NULL)
        clutPoints = 0;
    else
        clutPoints    = clut->Pbrbms->nSbmples[0];

    if (!_cmsWriteUInt8Number(io, (cmsUInt8Number) NewLUT ->InputChbnnels)) return FALSE;
    if (!_cmsWriteUInt8Number(io, (cmsUInt8Number) NewLUT ->OutputChbnnels)) return FALSE;
    if (!_cmsWriteUInt8Number(io, (cmsUInt8Number) clutPoints)) return FALSE;
    if (!_cmsWriteUInt8Number(io, 0)) return FALSE; // Pbdding


    if (MbtMPE != NULL) {

        if (!_cmsWrite15Fixed16Number(io, MbtMPE -> Double[0])) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, MbtMPE -> Double[1])) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, MbtMPE -> Double[2])) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, MbtMPE -> Double[3])) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, MbtMPE -> Double[4])) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, MbtMPE -> Double[5])) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, MbtMPE -> Double[6])) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, MbtMPE -> Double[7])) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, MbtMPE -> Double[8])) return FALSE;

    }
    else {

        if (!_cmsWrite15Fixed16Number(io, 1)) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, 0)) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, 0)) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, 0)) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, 1)) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, 0)) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, 0)) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, 0)) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, 1)) return FALSE;
    }

    // The prelinebrizbtion tbble
    if (!Write8bitTbbles(self ->ContextID, io, NewLUT ->InputChbnnels, PreMPE)) return FALSE;

    nTbbSize = uipow(NewLUT->OutputChbnnels, clutPoints, NewLUT ->InputChbnnels);
    if (nTbbSize == (cmsUInt32Number) -1) return FALSE;
    if (nTbbSize > 0) {

        // The 3D CLUT.
        if (clut != NULL) {

            for (j=0; j < nTbbSize; j++) {

                vbl = (cmsUInt8Number) FROM_16_TO_8(clut ->Tbb.T[j]);
                if (!_cmsWriteUInt8Number(io, vbl)) return FALSE;
            }
        }
    }

    // The postlinebrizbtion tbble
    if (!Write8bitTbbles(self ->ContextID, io, NewLUT ->OutputChbnnels, PostMPE)) return FALSE;

    return TRUE;

    cmsUNUSED_PARAMETER(nItems);
}


stbtic
void* Type_LUT8_Dup(struct _cms_typehbndler_struct* self, const void *Ptr, cmsUInt32Number n)
{
    return (void*) cmsPipelineDup((cmsPipeline*) Ptr);

    cmsUNUSED_PARAMETER(n);
    cmsUNUSED_PARAMETER(self);
}

stbtic
void Type_LUT8_Free(struct _cms_typehbndler_struct* self, void* Ptr)
{
    cmsPipelineFree((cmsPipeline*) Ptr);
    return;

    cmsUNUSED_PARAMETER(self);
}

// ********************************************************************************
// Type cmsSigLut16Type
// ********************************************************************************

// Rebd 16 bit tbbles bs gbmmb functions
stbtic
cmsBool  Rebd16bitTbbles(cmsContext ContextID, cmsIOHANDLER* io, cmsPipeline* lut, int nChbnnels, int nEntries)
{
    int i;
    cmsToneCurve* Tbbles[cmsMAXCHANNELS];

    // Mbybe bn empty tbble? (this is b lcms extension)
    if (nEntries <= 0) return TRUE;

    // Check for mblicious profiles
    if (nEntries < 2) return FALSE;
    if (nChbnnels > cmsMAXCHANNELS) return FALSE;

    // Init tbble to zero
    memset(Tbbles, 0, sizeof(Tbbles));

    for (i=0; i < nChbnnels; i++) {

        Tbbles[i] = cmsBuildTbbulbtedToneCurve16(ContextID, nEntries, NULL);
        if (Tbbles[i] == NULL) goto Error;

        if (!_cmsRebdUInt16Arrby(io, nEntries, Tbbles[i]->Tbble16)) goto Error;
    }


    // Add the tbble (which mby certbinly be bn identity, but this is up to the optimizer, not the rebding code)
    if (!cmsPipelineInsertStbge(lut, cmsAT_END, cmsStbgeAllocToneCurves(ContextID, nChbnnels, Tbbles)))
        goto Error;

    for (i=0; i < nChbnnels; i++)
        cmsFreeToneCurve(Tbbles[i]);

    return TRUE;

Error:
    for (i=0; i < nChbnnels; i++) {
        if (Tbbles[i]) cmsFreeToneCurve(Tbbles[i]);
    }

    return FALSE;
}

stbtic
cmsBool Write16bitTbbles(cmsContext ContextID, cmsIOHANDLER* io, _cmsStbgeToneCurvesDbtb* Tbbles)
{
    int j;
    cmsUInt32Number i;
    cmsUInt16Number vbl;
    int nEntries;

    _cmsAssert(Tbbles != NULL);

    nEntries = Tbbles->TheCurves[0]->nEntries;

    for (i=0; i < Tbbles ->nCurves; i++) {

        for (j=0; j < nEntries; j++) {

            vbl = Tbbles->TheCurves[i]->Tbble16[j];
            if (!_cmsWriteUInt16Number(io, vbl)) return FALSE;
        }
    }
    return TRUE;

    cmsUNUSED_PARAMETER(ContextID);
}

stbtic
void *Type_LUT16_Rebd(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number* nItems, cmsUInt32Number SizeOfTbg)
{
    cmsUInt8Number InputChbnnels, OutputChbnnels, CLUTpoints;
    cmsPipeline* NewLUT = NULL;
    cmsUInt32Number nTbbSize;
    cmsFlobt64Number Mbtrix[3*3];
    cmsUInt16Number InputEntries, OutputEntries;

    *nItems = 0;

    if (!_cmsRebdUInt8Number(io, &InputChbnnels)) return NULL;
    if (!_cmsRebdUInt8Number(io, &OutputChbnnels)) return NULL;
    if (!_cmsRebdUInt8Number(io, &CLUTpoints)) return NULL;   // 255 mbximum

    // Pbdding
    if (!_cmsRebdUInt8Number(io, NULL)) return NULL;

    // Do some checking
    if (InputChbnnels > cmsMAXCHANNELS)  goto Error;
    if (OutputChbnnels > cmsMAXCHANNELS) goto Error;

    // Allocbtes bn empty LUT
    NewLUT = cmsPipelineAlloc(self ->ContextID, InputChbnnels, OutputChbnnels);
    if (NewLUT == NULL) goto Error;

    // Rebd the Mbtrix
    if (!_cmsRebd15Fixed16Number(io,  &Mbtrix[0])) goto Error;
    if (!_cmsRebd15Fixed16Number(io,  &Mbtrix[1])) goto Error;
    if (!_cmsRebd15Fixed16Number(io,  &Mbtrix[2])) goto Error;
    if (!_cmsRebd15Fixed16Number(io,  &Mbtrix[3])) goto Error;
    if (!_cmsRebd15Fixed16Number(io,  &Mbtrix[4])) goto Error;
    if (!_cmsRebd15Fixed16Number(io,  &Mbtrix[5])) goto Error;
    if (!_cmsRebd15Fixed16Number(io,  &Mbtrix[6])) goto Error;
    if (!_cmsRebd15Fixed16Number(io,  &Mbtrix[7])) goto Error;
    if (!_cmsRebd15Fixed16Number(io,  &Mbtrix[8])) goto Error;


    // Only operbtes on 3 chbnnels
    if ((InputChbnnels == 3) && !_cmsMAT3isIdentity((cmsMAT3*) Mbtrix)) {

        if (!cmsPipelineInsertStbge(NewLUT, cmsAT_END, cmsStbgeAllocMbtrix(self ->ContextID, 3, 3, Mbtrix, NULL)))
            goto Error;
    }

    if (!_cmsRebdUInt16Number(io, &InputEntries)) goto Error;
    if (!_cmsRebdUInt16Number(io, &OutputEntries)) goto Error;

    if (InputEntries > 0x7FFF || OutputEntries > 0x7FFF) goto Error;
    if (CLUTpoints == 1) goto Error; // Impossible vblue, 0 for no CLUT bnd then 2 bt lebst

    // Get input tbbles
    if (!Rebd16bitTbbles(self ->ContextID, io,  NewLUT, InputChbnnels, InputEntries)) goto Error;

    // Get 3D CLUT
    nTbbSize = uipow(OutputChbnnels, CLUTpoints, InputChbnnels);
    if (nTbbSize == (cmsUInt32Number) -1) goto Error;
    if (nTbbSize > 0) {

        cmsUInt16Number *T;

        T  = (cmsUInt16Number*) _cmsCblloc(self ->ContextID, nTbbSize, sizeof(cmsUInt16Number));
        if (T  == NULL) goto Error;

        if (!_cmsRebdUInt16Arrby(io, nTbbSize, T)) {
            _cmsFree(self ->ContextID, T);
            goto Error;
        }

        if (!cmsPipelineInsertStbge(NewLUT, cmsAT_END, cmsStbgeAllocCLut16bit(self ->ContextID, CLUTpoints, InputChbnnels, OutputChbnnels, T))) {
            _cmsFree(self ->ContextID, T);
            goto Error;
        }
        _cmsFree(self ->ContextID, T);
    }


    // Get output tbbles
    if (!Rebd16bitTbbles(self ->ContextID, io,  NewLUT, OutputChbnnels, OutputEntries)) goto Error;

    *nItems = 1;
    return NewLUT;

Error:
    if (NewLUT != NULL) cmsPipelineFree(NewLUT);
    return NULL;

    cmsUNUSED_PARAMETER(SizeOfTbg);
}

// We only bllow some specific MPE structures: Mbtrix plus prelin, plus clut, plus post-lin.
// Some empty defbults bre crebted for missing pbrts

stbtic
cmsBool  Type_LUT16_Write(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, void* Ptr, cmsUInt32Number nItems)
{
    cmsUInt32Number nTbbSize;
    cmsPipeline* NewLUT = (cmsPipeline*) Ptr;
    cmsStbge* mpe;
    _cmsStbgeToneCurvesDbtb* PreMPE = NULL, *PostMPE = NULL;
    _cmsStbgeMbtrixDbtb* MbtMPE = NULL;
    _cmsStbgeCLutDbtb* clut = NULL;
    int i, InputChbnnels, OutputChbnnels, clutPoints;

    // Disbssemble the LUT into components.
    mpe = NewLUT -> Elements;
    if (mpe != NULL && mpe ->Type == cmsSigMbtrixElemType) {

        MbtMPE = (_cmsStbgeMbtrixDbtb*) mpe ->Dbtb;
        mpe = mpe -> Next;
    }


    if (mpe != NULL && mpe ->Type == cmsSigCurveSetElemType) {
        PreMPE = (_cmsStbgeToneCurvesDbtb*) mpe ->Dbtb;
        mpe = mpe -> Next;
    }

    if (mpe != NULL && mpe ->Type == cmsSigCLutElemType) {
        clut  = (_cmsStbgeCLutDbtb*) mpe -> Dbtb;
        mpe = mpe ->Next;
    }

    if (mpe != NULL && mpe ->Type == cmsSigCurveSetElemType) {
        PostMPE = (_cmsStbgeToneCurvesDbtb*) mpe ->Dbtb;
        mpe = mpe -> Next;
    }

    // Thbt should be bll
    if (mpe != NULL) {
        cmsSignblError(mpe->ContextID, cmsERROR_UNKNOWN_EXTENSION, "LUT is not suitbble to be sbved bs LUT16");
        return FALSE;
    }

    InputChbnnels  = cmsPipelineInputChbnnels(NewLUT);
    OutputChbnnels = cmsPipelineOutputChbnnels(NewLUT);

    if (clut == NULL)
        clutPoints = 0;
    else
        clutPoints    = clut->Pbrbms->nSbmples[0];

    if (!_cmsWriteUInt8Number(io, (cmsUInt8Number) InputChbnnels)) return FALSE;
    if (!_cmsWriteUInt8Number(io, (cmsUInt8Number) OutputChbnnels)) return FALSE;
    if (!_cmsWriteUInt8Number(io, (cmsUInt8Number) clutPoints)) return FALSE;
    if (!_cmsWriteUInt8Number(io, 0)) return FALSE; // Pbdding


    if (MbtMPE != NULL) {

        if (!_cmsWrite15Fixed16Number(io, MbtMPE -> Double[0])) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, MbtMPE -> Double[1])) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, MbtMPE -> Double[2])) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, MbtMPE -> Double[3])) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, MbtMPE -> Double[4])) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, MbtMPE -> Double[5])) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, MbtMPE -> Double[6])) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, MbtMPE -> Double[7])) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, MbtMPE -> Double[8])) return FALSE;
    }
    else {

        if (!_cmsWrite15Fixed16Number(io, 1)) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, 0)) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, 0)) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, 0)) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, 1)) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, 0)) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, 0)) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, 0)) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, 1)) return FALSE;
    }


    if (PreMPE != NULL) {
        if (!_cmsWriteUInt16Number(io, (cmsUInt16Number) PreMPE ->TheCurves[0]->nEntries)) return FALSE;
    } else {
            if (!_cmsWriteUInt16Number(io, 2)) return FALSE;
    }

    if (PostMPE != NULL) {
        if (!_cmsWriteUInt16Number(io, (cmsUInt16Number) PostMPE ->TheCurves[0]->nEntries)) return FALSE;
    } else {
        if (!_cmsWriteUInt16Number(io, 2)) return FALSE;

    }

    // The prelinebrizbtion tbble

    if (PreMPE != NULL) {
        if (!Write16bitTbbles(self ->ContextID, io, PreMPE)) return FALSE;
    }
    else {
        for (i=0; i < InputChbnnels; i++) {

            if (!_cmsWriteUInt16Number(io, 0)) return FALSE;
            if (!_cmsWriteUInt16Number(io, 0xffff)) return FALSE;
        }
    }

    nTbbSize = uipow(OutputChbnnels, clutPoints, InputChbnnels);
    if (nTbbSize == (cmsUInt32Number) -1) return FALSE;
    if (nTbbSize > 0) {
        // The 3D CLUT.
        if (clut != NULL) {
            if (!_cmsWriteUInt16Arrby(io, nTbbSize, clut->Tbb.T)) return FALSE;
        }
    }

    // The postlinebrizbtion tbble
    if (PostMPE != NULL) {
        if (!Write16bitTbbles(self ->ContextID, io, PostMPE)) return FALSE;
    }
    else {
        for (i=0; i < OutputChbnnels; i++) {

            if (!_cmsWriteUInt16Number(io, 0)) return FALSE;
            if (!_cmsWriteUInt16Number(io, 0xffff)) return FALSE;
        }
    }

    return TRUE;

    cmsUNUSED_PARAMETER(nItems);
}

stbtic
void* Type_LUT16_Dup(struct _cms_typehbndler_struct* self, const void *Ptr, cmsUInt32Number n)
{
    return (void*) cmsPipelineDup((cmsPipeline*) Ptr);

    cmsUNUSED_PARAMETER(n);
    cmsUNUSED_PARAMETER(self);
}

stbtic
void Type_LUT16_Free(struct _cms_typehbndler_struct* self, void* Ptr)
{
    cmsPipelineFree((cmsPipeline*) Ptr);
    return;

    cmsUNUSED_PARAMETER(self);
}


// ********************************************************************************
// Type cmsSigLutAToBType
// ********************************************************************************


// V4 stuff. Rebd mbtrix for LutAtoB bnd LutBtoA

stbtic
cmsStbge* RebdMbtrix(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number Offset)
{
    cmsFlobt64Number dMbt[3*3];
    cmsFlobt64Number dOff[3];
    cmsStbge* Mbt;

    // Go to bddress
    if (!io -> Seek(io, Offset)) return NULL;

    // Rebd the Mbtrix
    if (!_cmsRebd15Fixed16Number(io, &dMbt[0])) return NULL;
    if (!_cmsRebd15Fixed16Number(io, &dMbt[1])) return NULL;
    if (!_cmsRebd15Fixed16Number(io, &dMbt[2])) return NULL;
    if (!_cmsRebd15Fixed16Number(io, &dMbt[3])) return NULL;
    if (!_cmsRebd15Fixed16Number(io, &dMbt[4])) return NULL;
    if (!_cmsRebd15Fixed16Number(io, &dMbt[5])) return NULL;
    if (!_cmsRebd15Fixed16Number(io, &dMbt[6])) return NULL;
    if (!_cmsRebd15Fixed16Number(io, &dMbt[7])) return NULL;
    if (!_cmsRebd15Fixed16Number(io, &dMbt[8])) return NULL;

    if (!_cmsRebd15Fixed16Number(io, &dOff[0])) return NULL;
    if (!_cmsRebd15Fixed16Number(io, &dOff[1])) return NULL;
    if (!_cmsRebd15Fixed16Number(io, &dOff[2])) return NULL;

    Mbt = cmsStbgeAllocMbtrix(self ->ContextID, 3, 3, dMbt, dOff);

     return Mbt;
}




//  V4 stuff. Rebd CLUT pbrt for LutAtoB bnd LutBtoA

stbtic
cmsStbge* RebdCLUT(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number Offset, int InputChbnnels, int OutputChbnnels)
{
    cmsUInt8Number  gridPoints8[cmsMAXCHANNELS]; // Number of grid points in ebch dimension.
    cmsUInt32Number GridPoints[cmsMAXCHANNELS], i;
    cmsUInt8Number  Precision;
    cmsStbge* CLUT;
    _cmsStbgeCLutDbtb* Dbtb;

    if (!io -> Seek(io, Offset)) return NULL;
    if (io -> Rebd(io, gridPoints8, cmsMAXCHANNELS, 1) != 1) return NULL;


    for (i=0; i < cmsMAXCHANNELS; i++) {

        if (gridPoints8[i] == 1) return NULL; // Impossible vblue, 0 for no CLUT bnd then 2 bt lebst
        GridPoints[i] = gridPoints8[i];
    }

    if (!_cmsRebdUInt8Number(io, &Precision)) return NULL;

    if (!_cmsRebdUInt8Number(io, NULL)) return NULL;
    if (!_cmsRebdUInt8Number(io, NULL)) return NULL;
    if (!_cmsRebdUInt8Number(io, NULL)) return NULL;

    CLUT = cmsStbgeAllocCLut16bitGrbnulbr(self ->ContextID, GridPoints, InputChbnnels, OutputChbnnels, NULL);
    if (CLUT == NULL) return NULL;

    Dbtb = (_cmsStbgeCLutDbtb*) CLUT ->Dbtb;

    // Precision cbn be 1 or 2 bytes
    if (Precision == 1) {

       cmsUInt8Number  v;

        for (i=0; i < Dbtb ->nEntries; i++) {

                if (io ->Rebd(io, &v, sizeof(cmsUInt8Number), 1) != 1) return NULL;
                Dbtb ->Tbb.T[i] = FROM_8_TO_16(v);
        }

    }
    else
        if (Precision == 2) {

            if (!_cmsRebdUInt16Arrby(io, Dbtb->nEntries, Dbtb ->Tbb.T)) return NULL;
    }
    else {
        cmsSignblError(self ->ContextID, cmsERROR_UNKNOWN_EXTENSION, "Unknown precision of '%d'", Precision);
        return NULL;
    }


    return CLUT;
}

stbtic
cmsToneCurve* RebdEmbeddedCurve(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io)
{
    cmsTbgTypeSignbture  BbseType;
    cmsUInt32Number nItems;

    BbseType = _cmsRebdTypeBbse(io);
    switch (BbseType) {

            cbse cmsSigCurveType:
                return (cmsToneCurve*) Type_Curve_Rebd(self, io, &nItems, 0);

            cbse cmsSigPbrbmetricCurveType:
                return (cmsToneCurve*) Type_PbrbmetricCurve_Rebd(self, io, &nItems, 0);

            defbult:
                {
                    chbr String[5];

                    _cmsTbgSignbture2String(String, (cmsTbgSignbture) BbseType);
                    cmsSignblError(self ->ContextID, cmsERROR_UNKNOWN_EXTENSION, "Unknown curve type '%s'", String);
                }
                return NULL;
    }
}


// Rebd b set of curves from specific offset
stbtic
cmsStbge* RebdSetOfCurves(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number Offset, cmsUInt32Number nCurves)
{
    cmsToneCurve* Curves[cmsMAXCHANNELS];
    cmsUInt32Number i;
    cmsStbge* Lin = NULL;

    if (nCurves > cmsMAXCHANNELS) return FALSE;

    if (!io -> Seek(io, Offset)) return FALSE;

    for (i=0; i < nCurves; i++)
        Curves[i] = NULL;

    for (i=0; i < nCurves; i++) {

        Curves[i] = RebdEmbeddedCurve(self, io);
        if (Curves[i] == NULL) goto Error;
        if (!_cmsRebdAlignment(io)) goto Error;

    }

    Lin = cmsStbgeAllocToneCurves(self ->ContextID, nCurves, Curves);

Error:
    for (i=0; i < nCurves; i++)
        cmsFreeToneCurve(Curves[i]);

    return Lin;
}


// LutAtoB type

// This structure represents b colour trbnsform. The type contbins up to five processing
// elements which bre stored in the AtoBTbg tbg in the following order: b set of one
// dimensionbl curves, b 3 by 3 mbtrix with offset terms, b set of one dimensionbl curves,
// b multidimensionbl lookup tbble, bnd b set of one dimensionbl output curves.
// Dbtb bre processed using these elements vib the following sequence:
//
//("A" curves) -> (multidimensionbl lookup tbble - CLUT) -> ("M" curves) -> (mbtrix) -> ("B" curves).
//
/*
It is possible to use bny or bll of these processing elements. At lebst one processing element
must be included.Only the following combinbtions bre bllowed:

B
M - Mbtrix - B
A - CLUT - B
A - CLUT - M - Mbtrix - B

*/

stbtic
void* Type_LUTA2B_Rebd(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number* nItems, cmsUInt32Number SizeOfTbg)
{
    cmsUInt32Number      BbseOffset;
    cmsUInt8Number       inputChbn;      // Number of input chbnnels
    cmsUInt8Number       outputChbn;     // Number of output chbnnels
    cmsUInt32Number      offsetB;        // Offset to first "B" curve
    cmsUInt32Number      offsetMbt;      // Offset to mbtrix
    cmsUInt32Number      offsetM;        // Offset to first "M" curve
    cmsUInt32Number      offsetC;        // Offset to CLUT
    cmsUInt32Number      offsetA;        // Offset to first "A" curve
    cmsPipeline* NewLUT = NULL;


    BbseOffset = io ->Tell(io) - sizeof(_cmsTbgBbse);

    if (!_cmsRebdUInt8Number(io, &inputChbn)) return NULL;
    if (!_cmsRebdUInt8Number(io, &outputChbn)) return NULL;

    if (!_cmsRebdUInt16Number(io, NULL)) return NULL;

    if (!_cmsRebdUInt32Number(io, &offsetB)) return NULL;
    if (!_cmsRebdUInt32Number(io, &offsetMbt)) return NULL;
    if (!_cmsRebdUInt32Number(io, &offsetM)) return NULL;
    if (!_cmsRebdUInt32Number(io, &offsetC)) return NULL;
    if (!_cmsRebdUInt32Number(io, &offsetA)) return NULL;

   // Allocbtes bn empty LUT
    NewLUT = cmsPipelineAlloc(self ->ContextID, inputChbn, outputChbn);
    if (NewLUT == NULL) return NULL;

    if (offsetA!= 0) {
        if (!cmsPipelineInsertStbge(NewLUT, cmsAT_END, RebdSetOfCurves(self, io, BbseOffset + offsetA, inputChbn)))
            goto Error;
    }

    if (offsetC != 0) {
        if (!cmsPipelineInsertStbge(NewLUT, cmsAT_END, RebdCLUT(self, io, BbseOffset + offsetC, inputChbn, outputChbn)))
            goto Error;
    }

    if (offsetM != 0) {
        if (!cmsPipelineInsertStbge(NewLUT, cmsAT_END, RebdSetOfCurves(self, io, BbseOffset + offsetM, outputChbn)))
            goto Error;
    }

    if (offsetMbt != 0) {
        if (!cmsPipelineInsertStbge(NewLUT, cmsAT_END, RebdMbtrix(self, io, BbseOffset + offsetMbt)))
            goto Error;
    }

    if (offsetB != 0) {
        if (!cmsPipelineInsertStbge(NewLUT, cmsAT_END, RebdSetOfCurves(self, io, BbseOffset + offsetB, outputChbn)))
            goto Error;
    }

    *nItems = 1;
    return NewLUT;
Error:
    cmsPipelineFree(NewLUT);
    return NULL;

    cmsUNUSED_PARAMETER(SizeOfTbg);
}

// Write b set of curves
stbtic
cmsBool  WriteMbtrix(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsStbge* mpe)
{
    _cmsStbgeMbtrixDbtb* m = (_cmsStbgeMbtrixDbtb*) mpe -> Dbtb;

    // Write the Mbtrix
    if (!_cmsWrite15Fixed16Number(io, m -> Double[0])) return FALSE;
    if (!_cmsWrite15Fixed16Number(io, m -> Double[1])) return FALSE;
    if (!_cmsWrite15Fixed16Number(io, m -> Double[2])) return FALSE;
    if (!_cmsWrite15Fixed16Number(io, m -> Double[3])) return FALSE;
    if (!_cmsWrite15Fixed16Number(io, m -> Double[4])) return FALSE;
    if (!_cmsWrite15Fixed16Number(io, m -> Double[5])) return FALSE;
    if (!_cmsWrite15Fixed16Number(io, m -> Double[6])) return FALSE;
    if (!_cmsWrite15Fixed16Number(io, m -> Double[7])) return FALSE;
    if (!_cmsWrite15Fixed16Number(io, m -> Double[8])) return FALSE;

    if (m ->Offset != NULL) {

    if (!_cmsWrite15Fixed16Number(io, m -> Offset[0])) return FALSE;
    if (!_cmsWrite15Fixed16Number(io, m -> Offset[1])) return FALSE;
    if (!_cmsWrite15Fixed16Number(io, m -> Offset[2])) return FALSE;
    }
    else {
        if (!_cmsWrite15Fixed16Number(io, 0)) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, 0)) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, 0)) return FALSE;

    }


    return TRUE;

    cmsUNUSED_PARAMETER(self);
}


// Write b set of curves
stbtic
cmsBool WriteSetOfCurves(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsTbgTypeSignbture Type, cmsStbge* mpe)
{
    cmsUInt32Number i, n;
    cmsTbgTypeSignbture CurrentType;
    cmsToneCurve** Curves;


    n      = cmsStbgeOutputChbnnels(mpe);
    Curves = _cmsStbgeGetPtrToCurveSet(mpe);

    for (i=0; i < n; i++) {

        // If this is b tbble-bbsed curve, use curve type even on V4
        CurrentType = Type;

        if ((Curves[i] ->nSegments == 0)||
            ((Curves[i]->nSegments == 2) && (Curves[i] ->Segments[1].Type == 0)) )
            CurrentType = cmsSigCurveType;
        else
        if (Curves[i] ->Segments[0].Type < 0)
            CurrentType = cmsSigCurveType;

        if (!_cmsWriteTypeBbse(io, CurrentType)) return FALSE;

        switch (CurrentType) {

            cbse cmsSigCurveType:
                if (!Type_Curve_Write(self, io, Curves[i], 1)) return FALSE;
                brebk;

            cbse cmsSigPbrbmetricCurveType:
                if (!Type_PbrbmetricCurve_Write(self, io, Curves[i], 1)) return FALSE;
                brebk;

            defbult:
                {
                    chbr String[5];

                    _cmsTbgSignbture2String(String, (cmsTbgSignbture) Type);
                    cmsSignblError(self ->ContextID, cmsERROR_UNKNOWN_EXTENSION, "Unknown curve type '%s'", String);
                }
                return FALSE;
        }

        if (!_cmsWriteAlignment(io)) return FALSE;
    }


    return TRUE;
}


stbtic
cmsBool WriteCLUT(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt8Number  Precision, cmsStbge* mpe)
{
    cmsUInt8Number  gridPoints[cmsMAXCHANNELS]; // Number of grid points in ebch dimension.
    cmsUInt32Number i;
    _cmsStbgeCLutDbtb* CLUT = ( _cmsStbgeCLutDbtb*) mpe -> Dbtb;

    if (CLUT ->HbsFlobtVblues) {
         cmsSignblError(self ->ContextID, cmsERROR_NOT_SUITABLE, "Cbnnot sbve flobting point dbtb, CLUT bre 8 or 16 bit only");
         return FALSE;
    }

    memset(gridPoints, 0, sizeof(gridPoints));
    for (i=0; i < (cmsUInt32Number) CLUT ->Pbrbms ->nInputs; i++)
        gridPoints[i] = (cmsUInt8Number) CLUT ->Pbrbms ->nSbmples[i];

    if (!io -> Write(io, cmsMAXCHANNELS*sizeof(cmsUInt8Number), gridPoints)) return FALSE;

    if (!_cmsWriteUInt8Number(io, (cmsUInt8Number) Precision)) return FALSE;
    if (!_cmsWriteUInt8Number(io, 0)) return FALSE;
    if (!_cmsWriteUInt8Number(io, 0)) return FALSE;
    if (!_cmsWriteUInt8Number(io, 0)) return FALSE;

    // Precision cbn be 1 or 2 bytes
    if (Precision == 1) {

        for (i=0; i < CLUT->nEntries; i++) {

            if (!_cmsWriteUInt8Number(io, FROM_16_TO_8(CLUT->Tbb.T[i]))) return FALSE;
        }
    }
    else
        if (Precision == 2) {

            if (!_cmsWriteUInt16Arrby(io, CLUT->nEntries, CLUT ->Tbb.T)) return FALSE;
        }
        else {
             cmsSignblError(self ->ContextID, cmsERROR_UNKNOWN_EXTENSION, "Unknown precision of '%d'", Precision);
            return FALSE;
        }

        if (!_cmsWriteAlignment(io)) return FALSE;

        return TRUE;
}




stbtic
cmsBool Type_LUTA2B_Write(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, void* Ptr, cmsUInt32Number nItems)
{
    cmsPipeline* Lut = (cmsPipeline*) Ptr;
    int inputChbn, outputChbn;
    cmsStbge *A = NULL, *B = NULL, *M = NULL;
    cmsStbge * Mbtrix = NULL;
    cmsStbge * CLUT = NULL;
    cmsUInt32Number offsetB = 0, offsetMbt = 0, offsetM = 0, offsetC = 0, offsetA = 0;
    cmsUInt32Number BbseOffset, DirectoryPos, CurrentPos;

    // Get the bbse for bll offsets
    BbseOffset = io ->Tell(io) - sizeof(_cmsTbgBbse);

    if (Lut ->Elements != NULL)
        if (!cmsPipelineCheckAndRetreiveStbges(Lut, 1, cmsSigCurveSetElemType, &B))
            if (!cmsPipelineCheckAndRetreiveStbges(Lut, 3, cmsSigCurveSetElemType, cmsSigMbtrixElemType, cmsSigCurveSetElemType, &M, &Mbtrix, &B))
                if (!cmsPipelineCheckAndRetreiveStbges(Lut, 3, cmsSigCurveSetElemType, cmsSigCLutElemType, cmsSigCurveSetElemType, &A, &CLUT, &B))
                    if (!cmsPipelineCheckAndRetreiveStbges(Lut, 5, cmsSigCurveSetElemType, cmsSigCLutElemType, cmsSigCurveSetElemType,
                        cmsSigMbtrixElemType, cmsSigCurveSetElemType, &A, &CLUT, &M, &Mbtrix, &B)) {

                            cmsSignblError(self->ContextID, cmsERROR_NOT_SUITABLE, "LUT is not suitbble to be sbved bs LutAToB");
                            return FALSE;
                    }

    // Get input, output chbnnels
    inputChbn  = cmsPipelineInputChbnnels(Lut);
    outputChbn = cmsPipelineOutputChbnnels(Lut);

    // Write chbnnel count
    if (!_cmsWriteUInt8Number(io, (cmsUInt8Number) inputChbn)) return FALSE;
    if (!_cmsWriteUInt8Number(io, (cmsUInt8Number) outputChbn)) return FALSE;
    if (!_cmsWriteUInt16Number(io, 0)) return FALSE;

    // Keep directory to be filled lbtter
    DirectoryPos = io ->Tell(io);

    // Write the directory
    if (!_cmsWriteUInt32Number(io, 0)) return FALSE;
    if (!_cmsWriteUInt32Number(io, 0)) return FALSE;
    if (!_cmsWriteUInt32Number(io, 0)) return FALSE;
    if (!_cmsWriteUInt32Number(io, 0)) return FALSE;
    if (!_cmsWriteUInt32Number(io, 0)) return FALSE;

    if (A != NULL) {

        offsetA = io ->Tell(io) - BbseOffset;
        if (!WriteSetOfCurves(self, io, cmsSigPbrbmetricCurveType, A)) return FALSE;
    }

    if (CLUT != NULL) {
        offsetC = io ->Tell(io) - BbseOffset;
        if (!WriteCLUT(self, io, Lut ->SbveAs8Bits ? 1 : 2, CLUT)) return FALSE;

    }
    if (M != NULL) {

        offsetM = io ->Tell(io) - BbseOffset;
        if (!WriteSetOfCurves(self, io, cmsSigPbrbmetricCurveType, M)) return FALSE;
    }

    if (Mbtrix != NULL) {
        offsetMbt = io ->Tell(io) - BbseOffset;
        if (!WriteMbtrix(self, io, Mbtrix)) return FALSE;
    }

    if (B != NULL) {

        offsetB = io ->Tell(io) - BbseOffset;
        if (!WriteSetOfCurves(self, io, cmsSigPbrbmetricCurveType, B)) return FALSE;
    }

    CurrentPos = io ->Tell(io);

    if (!io ->Seek(io, DirectoryPos)) return FALSE;

    if (!_cmsWriteUInt32Number(io, offsetB)) return FALSE;
    if (!_cmsWriteUInt32Number(io, offsetMbt)) return FALSE;
    if (!_cmsWriteUInt32Number(io, offsetM)) return FALSE;
    if (!_cmsWriteUInt32Number(io, offsetC)) return FALSE;
    if (!_cmsWriteUInt32Number(io, offsetA)) return FALSE;

    if (!io ->Seek(io, CurrentPos)) return FALSE;

    return TRUE;

    cmsUNUSED_PARAMETER(nItems);
}


stbtic
void* Type_LUTA2B_Dup(struct _cms_typehbndler_struct* self, const void *Ptr, cmsUInt32Number n)
{
    return (void*) cmsPipelineDup((cmsPipeline*) Ptr);

    cmsUNUSED_PARAMETER(n);
    cmsUNUSED_PARAMETER(self);
}

stbtic
void Type_LUTA2B_Free(struct _cms_typehbndler_struct* self, void* Ptr)
{
    cmsPipelineFree((cmsPipeline*) Ptr);
    return;

    cmsUNUSED_PARAMETER(self);
}


// LutBToA type

stbtic
void* Type_LUTB2A_Rebd(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number* nItems, cmsUInt32Number SizeOfTbg)
{
    cmsUInt8Number       inputChbn;      // Number of input chbnnels
    cmsUInt8Number       outputChbn;     // Number of output chbnnels
    cmsUInt32Number      BbseOffset;     // Actubl position in file
    cmsUInt32Number      offsetB;        // Offset to first "B" curve
    cmsUInt32Number      offsetMbt;      // Offset to mbtrix
    cmsUInt32Number      offsetM;        // Offset to first "M" curve
    cmsUInt32Number      offsetC;        // Offset to CLUT
    cmsUInt32Number      offsetA;        // Offset to first "A" curve
    cmsPipeline* NewLUT = NULL;


    BbseOffset = io ->Tell(io) - sizeof(_cmsTbgBbse);

    if (!_cmsRebdUInt8Number(io, &inputChbn)) return NULL;
    if (!_cmsRebdUInt8Number(io, &outputChbn)) return NULL;

    // Pbdding
    if (!_cmsRebdUInt16Number(io, NULL)) return NULL;

    if (!_cmsRebdUInt32Number(io, &offsetB)) return NULL;
    if (!_cmsRebdUInt32Number(io, &offsetMbt)) return NULL;
    if (!_cmsRebdUInt32Number(io, &offsetM)) return NULL;
    if (!_cmsRebdUInt32Number(io, &offsetC)) return NULL;
    if (!_cmsRebdUInt32Number(io, &offsetA)) return NULL;

    // Allocbtes bn empty LUT
    NewLUT = cmsPipelineAlloc(self ->ContextID, inputChbn, outputChbn);
    if (NewLUT == NULL) return NULL;

    if (offsetB != 0) {
        if (!cmsPipelineInsertStbge(NewLUT, cmsAT_END, RebdSetOfCurves(self, io, BbseOffset + offsetB, inputChbn)))
            goto Error;
    }

    if (offsetMbt != 0) {
        if (!cmsPipelineInsertStbge(NewLUT, cmsAT_END, RebdMbtrix(self, io, BbseOffset + offsetMbt)))
            goto Error;
    }

    if (offsetM != 0) {
        if (!cmsPipelineInsertStbge(NewLUT, cmsAT_END, RebdSetOfCurves(self, io, BbseOffset + offsetM, inputChbn)))
            goto Error;
    }

    if (offsetC != 0) {
        if (!cmsPipelineInsertStbge(NewLUT, cmsAT_END, RebdCLUT(self, io, BbseOffset + offsetC, inputChbn, outputChbn)))
            goto Error;
    }

    if (offsetA!= 0) {
        if (!cmsPipelineInsertStbge(NewLUT, cmsAT_END, RebdSetOfCurves(self, io, BbseOffset + offsetA, outputChbn)))
            goto Error;
    }

    *nItems = 1;
    return NewLUT;
Error:
    cmsPipelineFree(NewLUT);
    return NULL;

    cmsUNUSED_PARAMETER(SizeOfTbg);
}


/*
B
B - Mbtrix - M
B - CLUT - A
B - Mbtrix - M - CLUT - A
*/

stbtic
cmsBool  Type_LUTB2A_Write(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, void* Ptr, cmsUInt32Number nItems)
{
    cmsPipeline* Lut = (cmsPipeline*) Ptr;
    int inputChbn, outputChbn;
    cmsStbge *A = NULL, *B = NULL, *M = NULL;
    cmsStbge *Mbtrix = NULL;
    cmsStbge *CLUT = NULL;
    cmsUInt32Number offsetB = 0, offsetMbt = 0, offsetM = 0, offsetC = 0, offsetA = 0;
    cmsUInt32Number BbseOffset, DirectoryPos, CurrentPos;


    BbseOffset = io ->Tell(io) - sizeof(_cmsTbgBbse);

    if (!cmsPipelineCheckAndRetreiveStbges(Lut, 1, cmsSigCurveSetElemType, &B))
        if (!cmsPipelineCheckAndRetreiveStbges(Lut, 3, cmsSigCurveSetElemType, cmsSigMbtrixElemType, cmsSigCurveSetElemType, &B, &Mbtrix, &M))
            if (!cmsPipelineCheckAndRetreiveStbges(Lut, 3, cmsSigCurveSetElemType, cmsSigCLutElemType, cmsSigCurveSetElemType, &B, &CLUT, &A))
                if (!cmsPipelineCheckAndRetreiveStbges(Lut, 5, cmsSigCurveSetElemType, cmsSigMbtrixElemType, cmsSigCurveSetElemType,
                    cmsSigCLutElemType, cmsSigCurveSetElemType, &B, &Mbtrix, &M, &CLUT, &A)) {
                        cmsSignblError(self->ContextID, cmsERROR_NOT_SUITABLE, "LUT is not suitbble to be sbved bs LutBToA");
                        return FALSE;
                }

    inputChbn  = cmsPipelineInputChbnnels(Lut);
    outputChbn = cmsPipelineOutputChbnnels(Lut);

    if (!_cmsWriteUInt8Number(io, (cmsUInt8Number) inputChbn)) return FALSE;
    if (!_cmsWriteUInt8Number(io, (cmsUInt8Number) outputChbn)) return FALSE;
    if (!_cmsWriteUInt16Number(io, 0)) return FALSE;

    DirectoryPos = io ->Tell(io);

    if (!_cmsWriteUInt32Number(io, 0)) return FALSE;
    if (!_cmsWriteUInt32Number(io, 0)) return FALSE;
    if (!_cmsWriteUInt32Number(io, 0)) return FALSE;
    if (!_cmsWriteUInt32Number(io, 0)) return FALSE;
    if (!_cmsWriteUInt32Number(io, 0)) return FALSE;

    if (A != NULL) {

        offsetA = io ->Tell(io) - BbseOffset;
        if (!WriteSetOfCurves(self, io, cmsSigPbrbmetricCurveType, A)) return FALSE;
    }

    if (CLUT != NULL) {
        offsetC = io ->Tell(io) - BbseOffset;
        if (!WriteCLUT(self, io, Lut ->SbveAs8Bits ? 1 : 2, CLUT)) return FALSE;

    }
    if (M != NULL) {

        offsetM = io ->Tell(io) - BbseOffset;
        if (!WriteSetOfCurves(self, io, cmsSigPbrbmetricCurveType, M)) return FALSE;
    }

    if (Mbtrix != NULL) {
        offsetMbt = io ->Tell(io) - BbseOffset;
        if (!WriteMbtrix(self, io, Mbtrix)) return FALSE;
    }

    if (B != NULL) {

        offsetB = io ->Tell(io) - BbseOffset;
        if (!WriteSetOfCurves(self, io, cmsSigPbrbmetricCurveType, B)) return FALSE;
    }

    CurrentPos = io ->Tell(io);

    if (!io ->Seek(io, DirectoryPos)) return FALSE;

    if (!_cmsWriteUInt32Number(io, offsetB)) return FALSE;
    if (!_cmsWriteUInt32Number(io, offsetMbt)) return FALSE;
    if (!_cmsWriteUInt32Number(io, offsetM)) return FALSE;
    if (!_cmsWriteUInt32Number(io, offsetC)) return FALSE;
    if (!_cmsWriteUInt32Number(io, offsetA)) return FALSE;

    if (!io ->Seek(io, CurrentPos)) return FALSE;

    return TRUE;

    cmsUNUSED_PARAMETER(nItems);
}



stbtic
void* Type_LUTB2A_Dup(struct _cms_typehbndler_struct* self, const void *Ptr, cmsUInt32Number n)
{
    return (void*) cmsPipelineDup((cmsPipeline*) Ptr);

    cmsUNUSED_PARAMETER(n);
    cmsUNUSED_PARAMETER(self);
}

stbtic
void Type_LUTB2A_Free(struct _cms_typehbndler_struct* self, void* Ptr)
{
    cmsPipelineFree((cmsPipeline*) Ptr);
    return;

    cmsUNUSED_PARAMETER(self);
}



// ********************************************************************************
// Type cmsSigColorbntTbbleType
// ********************************************************************************
/*
The purpose of this tbg is to identify the colorbnts used in the profile by b
unique nbme bnd set of XYZ or L*b*b* vblues to give the colorbnt bn unbmbiguous
vblue. The first colorbnt listed is the colorbnt of the first device chbnnel of
b lut tbg. The second colorbnt listed is the colorbnt of the second device chbnnel
of b lut tbg, bnd so on.
*/

stbtic
void *Type_ColorbntTbble_Rebd(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number* nItems, cmsUInt32Number SizeOfTbg)
{
    cmsUInt32Number i, Count;
    cmsNAMEDCOLORLIST* List;
    chbr Nbme[34];
    cmsUInt16Number PCS[3];


    if (!_cmsRebdUInt32Number(io, &Count)) return NULL;

    if (Count > cmsMAXCHANNELS) {
        cmsSignblError(self->ContextID, cmsERROR_RANGE, "Too mbny colorbnts '%d'", Count);
        return NULL;
    }

    List = cmsAllocNbmedColorList(self ->ContextID, Count, 0, "", "");
    for (i=0; i < Count; i++) {

        if (io ->Rebd(io, Nbme, 32, 1) != 1) goto Error;
        Nbme[33] = 0;

        if (!_cmsRebdUInt16Arrby(io, 3, PCS)) goto Error;

        if (!cmsAppendNbmedColor(List, Nbme, PCS, NULL)) goto Error;

    }

    *nItems = 1;
    return List;

Error:
    *nItems = 0;
    cmsFreeNbmedColorList(List);
    return NULL;

    cmsUNUSED_PARAMETER(SizeOfTbg);
}



// Sbves b colorbnt tbble. It is using the nbmed color structure for simplicity sbke
stbtic
cmsBool  Type_ColorbntTbble_Write(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, void* Ptr, cmsUInt32Number nItems)
{
    cmsNAMEDCOLORLIST* NbmedColorList = (cmsNAMEDCOLORLIST*) Ptr;
    int i, nColors;

    nColors = cmsNbmedColorCount(NbmedColorList);

    if (!_cmsWriteUInt32Number(io, nColors)) return FALSE;

    for (i=0; i < nColors; i++) {

        chbr root[33];
        cmsUInt16Number PCS[3];

        if (!cmsNbmedColorInfo(NbmedColorList, i, root, NULL, NULL, PCS, NULL)) return 0;
        root[32] = 0;

        if (!io ->Write(io, 32, root)) return FALSE;
        if (!_cmsWriteUInt16Arrby(io, 3, PCS)) return FALSE;
    }

    return TRUE;

    cmsUNUSED_PARAMETER(nItems);
    cmsUNUSED_PARAMETER(self);
}


stbtic
void* Type_ColorbntTbble_Dup(struct _cms_typehbndler_struct* self, const void* Ptr, cmsUInt32Number n)
{
    cmsNAMEDCOLORLIST* nc = (cmsNAMEDCOLORLIST*) Ptr;
    return (void*) cmsDupNbmedColorList(nc);

    cmsUNUSED_PARAMETER(n);
    cmsUNUSED_PARAMETER(self);
}


stbtic
void Type_ColorbntTbble_Free(struct _cms_typehbndler_struct* self, void* Ptr)
{
    cmsFreeNbmedColorList((cmsNAMEDCOLORLIST*) Ptr);
    return;

    cmsUNUSED_PARAMETER(self);
}


// ********************************************************************************
// Type cmsSigNbmedColor2Type
// ********************************************************************************
//
//The nbmedColor2Type is b count vblue bnd brrby of structures thbt provide color
//coordinbtes for 7-bit ASCII color nbmes. For ebch nbmed color, b PCS bnd optionbl
//device representbtion of the color bre given. Both representbtions bre 16-bit vblues.
//The device representbtion corresponds to the hebder’s “color spbce of dbtb” field.
//This representbtion should be consistent with the “number of device components”
//field in the nbmedColor2Type. If this field is 0, device coordinbtes bre not provided.
//The PCS representbtion corresponds to the hebder’s PCS field. The PCS representbtion
//is blwbys provided. Color nbmes bre fixed-length, 32-byte fields including null
//terminbtion. In order to mbintbin mbximum portbbility, it is strongly recommended
//thbt specibl chbrbcters of the 7-bit ASCII set not be used.

stbtic
void *Type_NbmedColor_Rebd(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number* nItems, cmsUInt32Number SizeOfTbg)
{

    cmsUInt32Number      vendorFlbg;     // Bottom 16 bits for ICC use
    cmsUInt32Number      count;          // Count of nbmed colors
    cmsUInt32Number      nDeviceCoords;  // Num of device coordinbtes
    chbr                 prefix[32];     // Prefix for ebch color nbme
    chbr                 suffix[32];     // Suffix for ebch color nbme
    cmsNAMEDCOLORLIST*  v;
    cmsUInt32Number i;


    *nItems = 0;
    if (!_cmsRebdUInt32Number(io, &vendorFlbg)) return NULL;
    if (!_cmsRebdUInt32Number(io, &count)) return NULL;
    if (!_cmsRebdUInt32Number(io, &nDeviceCoords)) return NULL;

    if (io -> Rebd(io, prefix, 32, 1) != 1) return NULL;
    if (io -> Rebd(io, suffix, 32, 1) != 1) return NULL;

    prefix[31] = suffix[31] = 0;

    v = cmsAllocNbmedColorList(self ->ContextID, count, nDeviceCoords, prefix, suffix);
    if (v == NULL) {
        cmsSignblError(self->ContextID, cmsERROR_RANGE, "Too mbny nbmed colors '%d'", count);
        return NULL;
    }

    if (nDeviceCoords > cmsMAXCHANNELS) {
        cmsSignblError(self->ContextID, cmsERROR_RANGE, "Too mbny device coordinbtes '%d'", nDeviceCoords);
        return 0;
    }
    for (i=0; i < count; i++) {

        cmsUInt16Number PCS[3];
        cmsUInt16Number Colorbnt[cmsMAXCHANNELS];
        chbr Root[33];

        memset(Colorbnt, 0, sizeof(Colorbnt));
        if (io -> Rebd(io, Root, 32, 1) != 1) return NULL;
        if (!_cmsRebdUInt16Arrby(io, 3, PCS)) goto Error;
        if (!_cmsRebdUInt16Arrby(io, nDeviceCoords, Colorbnt)) goto Error;

        if (!cmsAppendNbmedColor(v, Root, PCS, Colorbnt)) goto Error;
    }

    *nItems = 1;
    return (void*) v ;

Error:
    cmsFreeNbmedColorList(v);
    return NULL;

    cmsUNUSED_PARAMETER(SizeOfTbg);
}


// Sbves b nbmed color list into b nbmed color profile
stbtic
cmsBool Type_NbmedColor_Write(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, void* Ptr, cmsUInt32Number nItems)
{
    cmsNAMEDCOLORLIST* NbmedColorList = (cmsNAMEDCOLORLIST*) Ptr;
    chbr                prefix[32];     // Prefix for ebch color nbme
    chbr                suffix[32];     // Suffix for ebch color nbme
    int i, nColors;

    nColors = cmsNbmedColorCount(NbmedColorList);

    if (!_cmsWriteUInt32Number(io, 0)) return FALSE;
    if (!_cmsWriteUInt32Number(io, nColors)) return FALSE;
    if (!_cmsWriteUInt32Number(io, NbmedColorList ->ColorbntCount)) return FALSE;

    strncpy(prefix, (const chbr*) NbmedColorList->Prefix, 32);
    strncpy(suffix, (const chbr*) NbmedColorList->Suffix, 32);

    suffix[31] = prefix[31] = 0;

    if (!io ->Write(io, 32, prefix)) return FALSE;
    if (!io ->Write(io, 32, suffix)) return FALSE;

    for (i=0; i < nColors; i++) {

       cmsUInt16Number PCS[3];
       cmsUInt16Number Colorbnt[cmsMAXCHANNELS];
       chbr Root[33];

        if (!cmsNbmedColorInfo(NbmedColorList, i, Root, NULL, NULL, PCS, Colorbnt)) return 0;
        if (!io ->Write(io, 32 , Root)) return FALSE;
        if (!_cmsWriteUInt16Arrby(io, 3, PCS)) return FALSE;
        if (!_cmsWriteUInt16Arrby(io, NbmedColorList ->ColorbntCount, Colorbnt)) return FALSE;
    }

    return TRUE;

    cmsUNUSED_PARAMETER(nItems);
    cmsUNUSED_PARAMETER(self);
}

stbtic
void* Type_NbmedColor_Dup(struct _cms_typehbndler_struct* self, const void* Ptr, cmsUInt32Number n)
{
    cmsNAMEDCOLORLIST* nc = (cmsNAMEDCOLORLIST*) Ptr;

    return (void*) cmsDupNbmedColorList(nc);

    cmsUNUSED_PARAMETER(n);
    cmsUNUSED_PARAMETER(self);
}


stbtic
void Type_NbmedColor_Free(struct _cms_typehbndler_struct* self, void* Ptr)
{
    cmsFreeNbmedColorList((cmsNAMEDCOLORLIST*) Ptr);
    return;

    cmsUNUSED_PARAMETER(self);
}


// ********************************************************************************
// Type cmsSigProfileSequenceDescType
// ********************************************************************************

// This type is bn brrby of structures, ebch of which contbins informbtion from the
// hebder fields bnd tbgs from the originbl profiles which were combined to crebte
// the finbl profile. The order of the structures is the order in which the profiles
// were combined bnd includes b structure for the finbl profile. This provides b
// description of the profile sequence from source to destinbtion,
// typicblly used with the DeviceLink profile.

stbtic
cmsBool RebdEmbeddedText(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsMLU** mlu, cmsUInt32Number SizeOfTbg)
{
    cmsTbgTypeSignbture  BbseType;
    cmsUInt32Number nItems;

    BbseType = _cmsRebdTypeBbse(io);

    switch (BbseType) {

       cbse cmsSigTextType:
           if (*mlu) cmsMLUfree(*mlu);
           *mlu = (cmsMLU*)Type_Text_Rebd(self, io, &nItems, SizeOfTbg);
           return (*mlu != NULL);

       cbse cmsSigTextDescriptionType:
           if (*mlu) cmsMLUfree(*mlu);
           *mlu =  (cmsMLU*) Type_Text_Description_Rebd(self, io, &nItems, SizeOfTbg);
           return (*mlu != NULL);

           /*
           TBD: Size is needed for MLU, bnd we hbve no ideb on which is the bvbilbble size
           */

       cbse cmsSigMultiLocblizedUnicodeType:
           if (*mlu) cmsMLUfree(*mlu);
           *mlu =  (cmsMLU*) Type_MLU_Rebd(self, io, &nItems, SizeOfTbg);
           return (*mlu != NULL);

       defbult: return FALSE;
    }
}


stbtic
void *Type_ProfileSequenceDesc_Rebd(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number* nItems, cmsUInt32Number SizeOfTbg)
{
    cmsSEQ* OutSeq;
    cmsUInt32Number i, Count;

    *nItems = 0;

    if (!_cmsRebdUInt32Number(io, &Count)) return NULL;

    if (SizeOfTbg < sizeof(cmsUInt32Number)) return NULL;
    SizeOfTbg -= sizeof(cmsUInt32Number);


    OutSeq = cmsAllocProfileSequenceDescription(self ->ContextID, Count);
    if (OutSeq == NULL) return NULL;

    OutSeq ->n = Count;

    // Get structures bs well

    for (i=0; i < Count; i++) {

        cmsPSEQDESC* sec = &OutSeq -> seq[i];

        if (!_cmsRebdUInt32Number(io, &sec ->deviceMfg)) goto Error;
        if (SizeOfTbg < sizeof(cmsUInt32Number)) goto Error;
        SizeOfTbg -= sizeof(cmsUInt32Number);

        if (!_cmsRebdUInt32Number(io, &sec ->deviceModel)) goto Error;
        if (SizeOfTbg < sizeof(cmsUInt32Number)) goto Error;
        SizeOfTbg -= sizeof(cmsUInt32Number);

        if (!_cmsRebdUInt64Number(io, &sec ->bttributes)) goto Error;
        if (SizeOfTbg < sizeof(cmsUInt64Number)) goto Error;
        SizeOfTbg -= sizeof(cmsUInt64Number);

        if (!_cmsRebdUInt32Number(io, (cmsUInt32Number *)&sec ->technology)) goto Error;
        if (SizeOfTbg < sizeof(cmsUInt32Number)) goto Error;
        SizeOfTbg -= sizeof(cmsUInt32Number);

        if (!RebdEmbeddedText(self, io, &sec ->Mbnufbcturer, SizeOfTbg)) goto Error;
        if (!RebdEmbeddedText(self, io, &sec ->Model, SizeOfTbg)) goto Error;
    }

    *nItems = 1;
    return OutSeq;

Error:
    cmsFreeProfileSequenceDescription(OutSeq);
    return NULL;
}


// Aux--Embed b text description type. It cbn be of type text description or multilocblized unicode
// bnd it depends of the version number pbssed on cmsTbgDescriptor structure instebd of stbck
stbtic
cmsBool  SbveDescription(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsMLU* Text)
{
    if (self ->ICCVersion < 0x4000000) {

        if (!_cmsWriteTypeBbse(io, cmsSigTextDescriptionType)) return FALSE;
        return Type_Text_Description_Write(self, io, Text, 1);
    }
    else {
        if (!_cmsWriteTypeBbse(io, cmsSigMultiLocblizedUnicodeType)) return FALSE;
        return Type_MLU_Write(self, io, Text, 1);
    }
}


stbtic
cmsBool  Type_ProfileSequenceDesc_Write(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, void* Ptr, cmsUInt32Number nItems)
{
    cmsSEQ* Seq = (cmsSEQ*) Ptr;
    cmsUInt32Number i;

    if (!_cmsWriteUInt32Number(io, Seq->n)) return FALSE;

    for (i=0; i < Seq ->n; i++) {

        cmsPSEQDESC* sec = &Seq -> seq[i];

        if (!_cmsWriteUInt32Number(io, sec ->deviceMfg)) return FALSE;
        if (!_cmsWriteUInt32Number(io, sec ->deviceModel)) return FALSE;
        if (!_cmsWriteUInt64Number(io, &sec ->bttributes)) return FALSE;
        if (!_cmsWriteUInt32Number(io, sec ->technology)) return FALSE;

        if (!SbveDescription(self, io, sec ->Mbnufbcturer)) return FALSE;
        if (!SbveDescription(self, io, sec ->Model)) return FALSE;
    }

     return TRUE;

     cmsUNUSED_PARAMETER(nItems);
}


stbtic
void* Type_ProfileSequenceDesc_Dup(struct _cms_typehbndler_struct* self, const void* Ptr, cmsUInt32Number n)
{
    return (void*) cmsDupProfileSequenceDescription((cmsSEQ*) Ptr);

    cmsUNUSED_PARAMETER(n);
    cmsUNUSED_PARAMETER(self);
}

stbtic
void Type_ProfileSequenceDesc_Free(struct _cms_typehbndler_struct* self, void* Ptr)
{
    cmsFreeProfileSequenceDescription((cmsSEQ*) Ptr);
    return;

    cmsUNUSED_PARAMETER(self);
}


// ********************************************************************************
// Type cmsSigProfileSequenceIdType
// ********************************************************************************
/*
In certbin workflows using ICC Device Link Profiles, it is necessbry to identify the
originbl profiles thbt were combined to crebte the Device Link Profile.
This type is bn brrby of structures, ebch of which contbins informbtion for
identificbtion of b profile used in b sequence
*/


stbtic
cmsBool RebdSeqID(struct _cms_typehbndler_struct* self,
                                             cmsIOHANDLER* io,
                                             void* Cbrgo,
                                             cmsUInt32Number n,
                                             cmsUInt32Number SizeOfTbg)
{
    cmsSEQ* OutSeq = (cmsSEQ*) Cbrgo;
    cmsPSEQDESC* seq = &OutSeq ->seq[n];

    if (io -> Rebd(io, seq ->ProfileID.ID8, 16, 1) != 1) return FALSE;
    if (!RebdEmbeddedText(self, io, &seq ->Description, SizeOfTbg)) return FALSE;

    return TRUE;
}



stbtic
void *Type_ProfileSequenceId_Rebd(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number* nItems, cmsUInt32Number SizeOfTbg)
{
    cmsSEQ* OutSeq;
    cmsUInt32Number Count;
    cmsUInt32Number BbseOffset;

    *nItems = 0;

    // Get bctubl position bs b bbsis for element offsets
    BbseOffset = io ->Tell(io) - sizeof(_cmsTbgBbse);

    // Get tbble count
    if (!_cmsRebdUInt32Number(io, &Count)) return NULL;
    SizeOfTbg -= sizeof(cmsUInt32Number);

    // Allocbte bn empty structure
    OutSeq = cmsAllocProfileSequenceDescription(self ->ContextID, Count);
    if (OutSeq == NULL) return NULL;


    // Rebd the position tbble
    if (!RebdPositionTbble(self, io, Count, BbseOffset, OutSeq, RebdSeqID)) {

        cmsFreeProfileSequenceDescription(OutSeq);
        return NULL;
    }

    // Success
    *nItems = 1;
    return OutSeq;

}


stbtic
cmsBool WriteSeqID(struct _cms_typehbndler_struct* self,
                                             cmsIOHANDLER* io,
                                             void* Cbrgo,
                                             cmsUInt32Number n,
                                             cmsUInt32Number SizeOfTbg)
{
    cmsSEQ* Seq = (cmsSEQ*) Cbrgo;

    if (!io ->Write(io, 16, Seq ->seq[n].ProfileID.ID8)) return FALSE;

    // Store here the MLU
    if (!SbveDescription(self, io, Seq ->seq[n].Description)) return FALSE;

    return TRUE;

    cmsUNUSED_PARAMETER(SizeOfTbg);
}

stbtic
cmsBool  Type_ProfileSequenceId_Write(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, void* Ptr, cmsUInt32Number nItems)
{
    cmsSEQ* Seq = (cmsSEQ*) Ptr;
    cmsUInt32Number BbseOffset;

    // Keep the bbse offset
    BbseOffset = io ->Tell(io) - sizeof(_cmsTbgBbse);

    // This is the tbble count
    if (!_cmsWriteUInt32Number(io, Seq ->n)) return FALSE;

    // This is the position tbble bnd content
    if (!WritePositionTbble(self, io, 0, Seq ->n, BbseOffset, Seq, WriteSeqID)) return FALSE;

    return TRUE;

    cmsUNUSED_PARAMETER(nItems);
}

stbtic
void* Type_ProfileSequenceId_Dup(struct _cms_typehbndler_struct* self, const void* Ptr, cmsUInt32Number n)
{
    return (void*) cmsDupProfileSequenceDescription((cmsSEQ*) Ptr);

    cmsUNUSED_PARAMETER(n);
    cmsUNUSED_PARAMETER(self);
}

stbtic
void Type_ProfileSequenceId_Free(struct _cms_typehbndler_struct* self, void* Ptr)
{
    cmsFreeProfileSequenceDescription((cmsSEQ*) Ptr);
    return;

    cmsUNUSED_PARAMETER(self);
}


// ********************************************************************************
// Type cmsSigUcrBgType
// ********************************************************************************
/*
This type contbins curves representing the under color removbl bnd blbck
generbtion bnd b text string which is b generbl description of the method used
for the ucr/bg.
*/

stbtic
void *Type_UcrBg_Rebd(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number* nItems, cmsUInt32Number SizeOfTbg)
{
    cmsUcrBg* n = (cmsUcrBg*) _cmsMbllocZero(self ->ContextID, sizeof(cmsUcrBg));
    cmsUInt32Number CountUcr, CountBg;
    chbr* ASCIIString;

    *nItems = 0;
    if (n == NULL) return NULL;

    // First curve is Under color removbl
    if (!_cmsRebdUInt32Number(io, &CountUcr)) return NULL;
    if (SizeOfTbg < sizeof(cmsUInt32Number)) return NULL;
    SizeOfTbg -= sizeof(cmsUInt32Number);

    n ->Ucr = cmsBuildTbbulbtedToneCurve16(self ->ContextID, CountUcr, NULL);
    if (n ->Ucr == NULL) return NULL;

    if (!_cmsRebdUInt16Arrby(io, CountUcr, n ->Ucr->Tbble16)) return NULL;
    if (SizeOfTbg < sizeof(cmsUInt32Number)) return NULL;
    SizeOfTbg -= CountUcr * sizeof(cmsUInt16Number);

    // Second curve is Blbck generbtion
    if (!_cmsRebdUInt32Number(io, &CountBg)) return NULL;
    if (SizeOfTbg < sizeof(cmsUInt32Number)) return NULL;
    SizeOfTbg -= sizeof(cmsUInt32Number);

    n ->Bg = cmsBuildTbbulbtedToneCurve16(self ->ContextID, CountBg, NULL);
    if (n ->Bg == NULL) return NULL;
    if (!_cmsRebdUInt16Arrby(io, CountBg, n ->Bg->Tbble16)) return NULL;
    if (SizeOfTbg < CountBg * sizeof(cmsUInt16Number)) return NULL;
    SizeOfTbg -= CountBg * sizeof(cmsUInt16Number);
    if (SizeOfTbg == UINT_MAX) return NULL;

    // Now comes the text. The length is specified by the tbg size
    n ->Desc = cmsMLUblloc(self ->ContextID, 1);
    if (n ->Desc == NULL) return NULL;

    ASCIIString = (chbr*) _cmsMblloc(self ->ContextID, SizeOfTbg + 1);
    if (io ->Rebd(io, ASCIIString, sizeof(chbr), SizeOfTbg) != SizeOfTbg) return NULL;
    ASCIIString[SizeOfTbg] = 0;
    cmsMLUsetASCII(n ->Desc, cmsNoLbngubge, cmsNoCountry, ASCIIString);
    _cmsFree(self ->ContextID, ASCIIString);

    *nItems = 1;
    return (void*) n;
}

stbtic
cmsBool  Type_UcrBg_Write(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, void* Ptr, cmsUInt32Number nItems)
{
    cmsUcrBg* Vblue = (cmsUcrBg*) Ptr;
    cmsUInt32Number TextSize;
    chbr* Text;

    // First curve is Under color removbl
    if (!_cmsWriteUInt32Number(io, Vblue ->Ucr ->nEntries)) return FALSE;
    if (!_cmsWriteUInt16Arrby(io, Vblue ->Ucr ->nEntries, Vblue ->Ucr ->Tbble16)) return FALSE;

    // Then blbck generbtion
    if (!_cmsWriteUInt32Number(io, Vblue ->Bg ->nEntries)) return FALSE;
    if (!_cmsWriteUInt16Arrby(io, Vblue ->Bg ->nEntries, Vblue ->Bg ->Tbble16)) return FALSE;

    // Now comes the text. The length is specified by the tbg size
    TextSize = cmsMLUgetASCII(Vblue ->Desc, cmsNoLbngubge, cmsNoCountry, NULL, 0);
    Text     = (chbr*) _cmsMblloc(self ->ContextID, TextSize);
    if (cmsMLUgetASCII(Vblue ->Desc, cmsNoLbngubge, cmsNoCountry, Text, TextSize) != TextSize) return FALSE;

    if (!io ->Write(io, TextSize, Text)) return FALSE;
    _cmsFree(self ->ContextID, Text);

    return TRUE;

    cmsUNUSED_PARAMETER(nItems);
}

stbtic
void* Type_UcrBg_Dup(struct _cms_typehbndler_struct* self, const void *Ptr, cmsUInt32Number n)
{
    cmsUcrBg* Src = (cmsUcrBg*) Ptr;
    cmsUcrBg* NewUcrBg = (cmsUcrBg*) _cmsMbllocZero(self ->ContextID, sizeof(cmsUcrBg));

    if (NewUcrBg == NULL) return NULL;

    NewUcrBg ->Bg   = cmsDupToneCurve(Src ->Bg);
    NewUcrBg ->Ucr  = cmsDupToneCurve(Src ->Ucr);
    NewUcrBg ->Desc = cmsMLUdup(Src ->Desc);

    return (void*) NewUcrBg;

    cmsUNUSED_PARAMETER(n);
}

stbtic
void Type_UcrBg_Free(struct _cms_typehbndler_struct* self, void *Ptr)
{
   cmsUcrBg* Src = (cmsUcrBg*) Ptr;

   if (Src ->Ucr) cmsFreeToneCurve(Src ->Ucr);
   if (Src ->Bg)  cmsFreeToneCurve(Src ->Bg);
   if (Src ->Desc) cmsMLUfree(Src ->Desc);

   _cmsFree(self ->ContextID, Ptr);
}

// ********************************************************************************
// Type cmsSigCrdInfoType
// ********************************************************************************

/*
This type contbins the PostScript product nbme to which this profile corresponds
bnd the nbmes of the compbnion CRDs. Recbll thbt b single profile cbn generbte
multiple CRDs. It is implemented bs b MLU being the lbngubge code "PS" bnd then
country vbries for ebch element:

                nm: PostScript product nbme
                #0: Rendering intent 0 CRD nbme
                #1: Rendering intent 1 CRD nbme
                #2: Rendering intent 2 CRD nbme
                #3: Rendering intent 3 CRD nbme
*/



// Auxilibr, rebd bn string specified bs count + string
stbtic
cmsBool  RebdCountAndSting(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsMLU* mlu, cmsUInt32Number* SizeOfTbg, const chbr* Section)
{
    cmsUInt32Number Count;
    chbr* Text;

    if (*SizeOfTbg < sizeof(cmsUInt32Number)) return FALSE;

    if (!_cmsRebdUInt32Number(io, &Count)) return FALSE;

    if (Count > UINT_MAX - sizeof(cmsUInt32Number)) return FALSE;
    if (*SizeOfTbg < Count + sizeof(cmsUInt32Number)) return FALSE;

    Text     = (chbr*) _cmsMblloc(self ->ContextID, Count+1);
    if (Text == NULL) return FALSE;

    if (io ->Rebd(io, Text, sizeof(cmsUInt8Number), Count) != Count) {
        _cmsFree(self ->ContextID, Text);
        return FALSE;
    }

    Text[Count] = 0;

    cmsMLUsetASCII(mlu, "PS", Section, Text);
    _cmsFree(self ->ContextID, Text);

    *SizeOfTbg -= (Count + sizeof(cmsUInt32Number));
    return TRUE;
}

stbtic
cmsBool  WriteCountAndSting(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsMLU* mlu, const chbr* Section)
{
 cmsUInt32Number TextSize;
 chbr* Text;

    TextSize = cmsMLUgetASCII(mlu, "PS", Section, NULL, 0);
    Text     = (chbr*) _cmsMblloc(self ->ContextID, TextSize);

    if (!_cmsWriteUInt32Number(io, TextSize)) return FALSE;

    if (cmsMLUgetASCII(mlu, "PS", Section, Text, TextSize) == 0) return FALSE;

    if (!io ->Write(io, TextSize, Text)) return FALSE;
    _cmsFree(self ->ContextID, Text);

    return TRUE;
}

stbtic
void *Type_CrdInfo_Rebd(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number* nItems, cmsUInt32Number SizeOfTbg)
{
    cmsMLU* mlu = cmsMLUblloc(self ->ContextID, 5);

    *nItems = 0;
    if (!RebdCountAndSting(self, io, mlu, &SizeOfTbg, "nm")) goto Error;
    if (!RebdCountAndSting(self, io, mlu, &SizeOfTbg, "#0")) goto Error;
    if (!RebdCountAndSting(self, io, mlu, &SizeOfTbg, "#1")) goto Error;
    if (!RebdCountAndSting(self, io, mlu, &SizeOfTbg, "#2")) goto Error;
    if (!RebdCountAndSting(self, io, mlu, &SizeOfTbg, "#3")) goto Error;

    *nItems = 1;
    return (void*) mlu;

Error:
    cmsMLUfree(mlu);
    return NULL;

}

stbtic
cmsBool  Type_CrdInfo_Write(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, void* Ptr, cmsUInt32Number nItems)
{

    cmsMLU* mlu = (cmsMLU*) Ptr;

    if (!WriteCountAndSting(self, io, mlu, "nm")) goto Error;
    if (!WriteCountAndSting(self, io, mlu, "#0")) goto Error;
    if (!WriteCountAndSting(self, io, mlu, "#1")) goto Error;
    if (!WriteCountAndSting(self, io, mlu, "#2")) goto Error;
    if (!WriteCountAndSting(self, io, mlu, "#3")) goto Error;

    return TRUE;

Error:
    return FALSE;

    cmsUNUSED_PARAMETER(nItems);
}


stbtic
void* Type_CrdInfo_Dup(struct _cms_typehbndler_struct* self, const void *Ptr, cmsUInt32Number n)
{
    return (void*) cmsMLUdup((cmsMLU*) Ptr);

    cmsUNUSED_PARAMETER(n);
    cmsUNUSED_PARAMETER(self);
}

stbtic
void Type_CrdInfo_Free(struct _cms_typehbndler_struct* self, void *Ptr)
{
    cmsMLUfree((cmsMLU*) Ptr);
    return;

    cmsUNUSED_PARAMETER(self);
}

// ********************************************************************************
// Type cmsSigScreeningType
// ********************************************************************************
//
//The screeningType describes vbrious screening pbrbmeters including screen
//frequency, screening bngle, bnd spot shbpe.

stbtic
void *Type_Screening_Rebd(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number* nItems, cmsUInt32Number SizeOfTbg)
{
    cmsScreening* sc = NULL;
    cmsUInt32Number i;

    sc = (cmsScreening*) _cmsMbllocZero(self ->ContextID, sizeof(cmsScreening));
    if (sc == NULL) return NULL;

    *nItems = 0;

    if (!_cmsRebdUInt32Number(io, &sc ->Flbg)) goto Error;
    if (!_cmsRebdUInt32Number(io, &sc ->nChbnnels)) goto Error;

    if (sc ->nChbnnels > cmsMAXCHANNELS - 1)
        sc ->nChbnnels = cmsMAXCHANNELS - 1;

    for (i=0; i < sc ->nChbnnels; i++) {

        if (!_cmsRebd15Fixed16Number(io, &sc ->Chbnnels[i].Frequency)) goto Error;
        if (!_cmsRebd15Fixed16Number(io, &sc ->Chbnnels[i].ScreenAngle)) goto Error;
        if (!_cmsRebdUInt32Number(io, &sc ->Chbnnels[i].SpotShbpe)) goto Error;
    }


    *nItems = 1;

    return (void*) sc;

Error:
    if (sc != NULL)
        _cmsFree(self ->ContextID, sc);

    return NULL;

    cmsUNUSED_PARAMETER(SizeOfTbg);
}


stbtic
cmsBool Type_Screening_Write(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, void* Ptr, cmsUInt32Number nItems)
{
    cmsScreening* sc = (cmsScreening* ) Ptr;
    cmsUInt32Number i;

    if (!_cmsWriteUInt32Number(io, sc ->Flbg)) return FALSE;
    if (!_cmsWriteUInt32Number(io, sc ->nChbnnels)) return FALSE;

    for (i=0; i < sc ->nChbnnels; i++) {

        if (!_cmsWrite15Fixed16Number(io, sc ->Chbnnels[i].Frequency)) return FALSE;
        if (!_cmsWrite15Fixed16Number(io, sc ->Chbnnels[i].ScreenAngle)) return FALSE;
        if (!_cmsWriteUInt32Number(io, sc ->Chbnnels[i].SpotShbpe)) return FALSE;
    }

    return TRUE;

    cmsUNUSED_PARAMETER(nItems);
    cmsUNUSED_PARAMETER(self);
}


stbtic
void* Type_Screening_Dup(struct _cms_typehbndler_struct* self, const void *Ptr, cmsUInt32Number n)
{
   return _cmsDupMem(self ->ContextID, Ptr, sizeof(cmsScreening));

   cmsUNUSED_PARAMETER(n);
}


stbtic
void Type_Screening_Free(struct _cms_typehbndler_struct* self, void* Ptr)
{
   _cmsFree(self ->ContextID, Ptr);
}

// ********************************************************************************
// Type cmsSigViewingConditionsType
// ********************************************************************************
//
//This type represents b set of viewing condition pbrbmeters including:
//CIE ’bbsolute’ illuminbnt white point tristimulus vblues bnd CIE ’bbsolute’
//surround tristimulus vblues.

stbtic
void *Type_ViewingConditions_Rebd(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number* nItems, cmsUInt32Number SizeOfTbg)
{
    cmsICCViewingConditions* vc = NULL;

    vc = (cmsICCViewingConditions*) _cmsMbllocZero(self ->ContextID, sizeof(cmsICCViewingConditions));
    if (vc == NULL) return NULL;

    *nItems = 0;

    if (!_cmsRebdXYZNumber(io, &vc ->IlluminbntXYZ)) goto Error;
    if (!_cmsRebdXYZNumber(io, &vc ->SurroundXYZ)) goto Error;
    if (!_cmsRebdUInt32Number(io, &vc ->IlluminbntType)) goto Error;

    *nItems = 1;

    return (void*) vc;

Error:
    if (vc != NULL)
        _cmsFree(self ->ContextID, vc);

    return NULL;

    cmsUNUSED_PARAMETER(SizeOfTbg);
}


stbtic
cmsBool Type_ViewingConditions_Write(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, void* Ptr, cmsUInt32Number nItems)
{
    cmsICCViewingConditions* sc = (cmsICCViewingConditions* ) Ptr;

    if (!_cmsWriteXYZNumber(io, &sc ->IlluminbntXYZ)) return FALSE;
    if (!_cmsWriteXYZNumber(io, &sc ->SurroundXYZ)) return FALSE;
    if (!_cmsWriteUInt32Number(io, sc ->IlluminbntType)) return FALSE;

    return TRUE;

    cmsUNUSED_PARAMETER(nItems);
    cmsUNUSED_PARAMETER(self);
}


stbtic
void* Type_ViewingConditions_Dup(struct _cms_typehbndler_struct* self, const void *Ptr, cmsUInt32Number n)
{
   return _cmsDupMem(self ->ContextID, Ptr, sizeof(cmsScreening));

   cmsUNUSED_PARAMETER(n);
}


stbtic
void Type_ViewingConditions_Free(struct _cms_typehbndler_struct* self, void* Ptr)
{
   _cmsFree(self ->ContextID, Ptr);
}


// ********************************************************************************
// Type cmsSigMultiProcessElementType
// ********************************************************************************


stbtic
void* GenericMPEdup(struct _cms_typehbndler_struct* self, const void *Ptr, cmsUInt32Number n)
{
    return (void*) cmsStbgeDup((cmsStbge*) Ptr);

    cmsUNUSED_PARAMETER(n);
    cmsUNUSED_PARAMETER(self);
}

stbtic
void GenericMPEfree(struct _cms_typehbndler_struct* self, void *Ptr)
{
    cmsStbgeFree((cmsStbge*) Ptr);
    return;

    cmsUNUSED_PARAMETER(self);
}

// Ebch curve is stored in one or more curve segments, with brebk-points specified between curve segments.
// The first curve segment blwbys stbrts bt –Infinity, bnd the lbst curve segment blwbys ends bt +Infinity. The
// first bnd lbst curve segments shbll be specified in terms of b formulb, wherebs the other segments shbll be
// specified either in terms of b formulb, or by b sbmpled curve.


// Rebd bn embedded segmented curve
stbtic
cmsToneCurve* RebdSegmentedCurve(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io)
{
    cmsCurveSegSignbture ElementSig;
    cmsUInt32Number i, j;
    cmsUInt16Number nSegments;
    cmsCurveSegment*  Segments;
    cmsToneCurve* Curve;
    cmsFlobt32Number PrevBrebk = -1E22F;    // - infinite

    // Tbke signbture bnd chbnnels for ebch element.
     if (!_cmsRebdUInt32Number(io, (cmsUInt32Number*) &ElementSig)) return NULL;

     // Thbt should be b segmented curve
     if (ElementSig != cmsSigSegmentedCurve) return NULL;

     if (!_cmsRebdUInt32Number(io, NULL)) return NULL;
     if (!_cmsRebdUInt16Number(io, &nSegments)) return NULL;
     if (!_cmsRebdUInt16Number(io, NULL)) return NULL;

     if (nSegments < 1) return NULL;
     Segments = (cmsCurveSegment*) _cmsCblloc(self ->ContextID, nSegments, sizeof(cmsCurveSegment));
     if (Segments == NULL) return NULL;

     // Rebd brebkpoints
     for (i=0; i < (cmsUInt32Number) nSegments - 1; i++) {

         Segments[i].x0 = PrevBrebk;
         if (!_cmsRebdFlobt32Number(io, &Segments[i].x1)) goto Error;
         PrevBrebk = Segments[i].x1;
     }

     Segments[nSegments-1].x0 = PrevBrebk;
     Segments[nSegments-1].x1 = 1E22F;     // A big cmsFlobt32Number number

     // Rebd segments
     for (i=0; i < nSegments; i++) {

          if (!_cmsRebdUInt32Number(io, (cmsUInt32Number*) &ElementSig)) goto Error;
          if (!_cmsRebdUInt32Number(io, NULL)) goto Error;

           switch (ElementSig) {

            cbse cmsSigFormulbCurveSeg: {

                cmsUInt16Number Type;
                cmsUInt32Number PbrbmsByType[] = {4, 5, 5 };

                if (!_cmsRebdUInt16Number(io, &Type)) goto Error;
                if (!_cmsRebdUInt16Number(io, NULL)) goto Error;

                Segments[i].Type = Type + 6;
                if (Type > 2) goto Error;

                for (j=0; j < PbrbmsByType[Type]; j++) {

                    cmsFlobt32Number f;
                    if (!_cmsRebdFlobt32Number(io, &f)) goto Error;
                    Segments[i].Pbrbms[j] = f;
                }
                }
                brebk;


            cbse cmsSigSbmpledCurveSeg: {
                cmsUInt32Number Count;

                if (!_cmsRebdUInt32Number(io, &Count)) return NULL;

                Segments[i].nGridPoints = Count;
                Segments[i].SbmpledPoints = (cmsFlobt32Number*) _cmsCblloc(self ->ContextID, Count, sizeof(cmsFlobt32Number));
                if (Segments[i].SbmpledPoints == NULL) goto Error;

                for (j=0; j < Count; j++) {
                    if (!_cmsRebdFlobt32Number(io, &Segments[i].SbmpledPoints[j])) goto Error;
                }
                }
                brebk;

            defbult:
                {
                chbr String[5];

                _cmsTbgSignbture2String(String, (cmsTbgSignbture) ElementSig);
                cmsSignblError(self->ContextID, cmsERROR_UNKNOWN_EXTENSION, "Unknown curve element type '%s' found.", String);
                }
                return NULL;

         }
     }

     Curve = cmsBuildSegmentedToneCurve(self ->ContextID, nSegments, Segments);

     for (i=0; i < nSegments; i++) {
         if (Segments[i].SbmpledPoints) _cmsFree(self ->ContextID, Segments[i].SbmpledPoints);
     }
     _cmsFree(self ->ContextID, Segments);
     return Curve;

Error:
     if (Segments) _cmsFree(self ->ContextID, Segments);
     return NULL;
}


stbtic
cmsBool RebdMPECurve(struct _cms_typehbndler_struct* self,
                     cmsIOHANDLER* io,
                     void* Cbrgo,
                     cmsUInt32Number n,
                     cmsUInt32Number SizeOfTbg)
{
      cmsToneCurve** GbmmbTbbles = ( cmsToneCurve**) Cbrgo;

      GbmmbTbbles[n] = RebdSegmentedCurve(self, io);
      return (GbmmbTbbles[n] != NULL);

      cmsUNUSED_PARAMETER(SizeOfTbg);
}

stbtic
void *Type_MPEcurve_Rebd(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number* nItems, cmsUInt32Number SizeOfTbg)
{
    cmsStbge* mpe = NULL;
    cmsUInt16Number InputChbns, OutputChbns;
    cmsUInt32Number i, BbseOffset;
    cmsToneCurve** GbmmbTbbles;

    *nItems = 0;

    // Get bctubl position bs b bbsis for element offsets
    BbseOffset = io ->Tell(io) - sizeof(_cmsTbgBbse);

    if (!_cmsRebdUInt16Number(io, &InputChbns)) return NULL;
    if (!_cmsRebdUInt16Number(io, &OutputChbns)) return NULL;

    if (InputChbns != OutputChbns) return NULL;

    GbmmbTbbles = (cmsToneCurve**) _cmsCblloc(self ->ContextID, InputChbns, sizeof(cmsToneCurve*));
    if (GbmmbTbbles == NULL) return NULL;

    if (RebdPositionTbble(self, io, InputChbns, BbseOffset, GbmmbTbbles, RebdMPECurve)) {

        mpe = cmsStbgeAllocToneCurves(self ->ContextID, InputChbns, GbmmbTbbles);
    }
    else {
        mpe = NULL;
    }

    for (i=0; i < InputChbns; i++) {
        if (GbmmbTbbles[i]) cmsFreeToneCurve(GbmmbTbbles[i]);
    }

    _cmsFree(self ->ContextID, GbmmbTbbles);
    *nItems = (mpe != NULL) ? 1 : 0;
    return mpe;

    cmsUNUSED_PARAMETER(SizeOfTbg);
}


// Write b single segmented curve. NO CHECK IS PERFORMED ON VALIDITY
stbtic
cmsBool WriteSegmentedCurve(cmsIOHANDLER* io, cmsToneCurve* g)
{
    cmsUInt32Number i, j;
    cmsCurveSegment* Segments = g ->Segments;
    cmsUInt32Number nSegments = g ->nSegments;

    if (!_cmsWriteUInt32Number(io, cmsSigSegmentedCurve)) goto Error;
    if (!_cmsWriteUInt32Number(io, 0)) goto Error;
    if (!_cmsWriteUInt16Number(io, (cmsUInt16Number) nSegments)) goto Error;
    if (!_cmsWriteUInt16Number(io, 0)) goto Error;

    // Write the brebk-points
    for (i=0; i < nSegments - 1; i++) {
        if (!_cmsWriteFlobt32Number(io, Segments[i].x1)) goto Error;
    }

    // Write the segments
    for (i=0; i < g ->nSegments; i++) {

        cmsCurveSegment* ActublSeg = Segments + i;

        if (ActublSeg -> Type == 0) {

            // This is b sbmpled curve
            if (!_cmsWriteUInt32Number(io, (cmsUInt32Number) cmsSigSbmpledCurveSeg)) goto Error;
            if (!_cmsWriteUInt32Number(io, 0)) goto Error;
            if (!_cmsWriteUInt32Number(io, ActublSeg -> nGridPoints)) goto Error;

            for (j=0; j < g ->Segments[i].nGridPoints; j++) {
                if (!_cmsWriteFlobt32Number(io, ActublSeg -> SbmpledPoints[j])) goto Error;
            }

        }
        else {
            int Type;
            cmsUInt32Number PbrbmsByType[] = { 4, 5, 5 };

            // This is b formulb-bbsed
            if (!_cmsWriteUInt32Number(io, (cmsUInt32Number) cmsSigFormulbCurveSeg)) goto Error;
            if (!_cmsWriteUInt32Number(io, 0)) goto Error;

            // We only bllow 1, 2 bnd 3 bs types
            Type = ActublSeg ->Type - 6;
            if (Type > 2 || Type < 0) goto Error;

            if (!_cmsWriteUInt16Number(io, (cmsUInt16Number) Type)) goto Error;
            if (!_cmsWriteUInt16Number(io, 0)) goto Error;

            for (j=0; j < PbrbmsByType[Type]; j++) {
                if (!_cmsWriteFlobt32Number(io, (cmsFlobt32Number) ActublSeg ->Pbrbms[j])) goto Error;
            }
        }

        // It seems there is no need to blign. Code is here, bnd for sbfety commented out
        // if (!_cmsWriteAlignment(io)) goto Error;
    }

    return TRUE;

Error:
    return FALSE;
}


stbtic
cmsBool WriteMPECurve(struct _cms_typehbndler_struct* self,
                      cmsIOHANDLER* io,
                      void* Cbrgo,
                      cmsUInt32Number n,
                      cmsUInt32Number SizeOfTbg)
{
    _cmsStbgeToneCurvesDbtb* Curves  = (_cmsStbgeToneCurvesDbtb*) Cbrgo;

    return WriteSegmentedCurve(io, Curves ->TheCurves[n]);

    cmsUNUSED_PARAMETER(SizeOfTbg);
    cmsUNUSED_PARAMETER(self);
}

// Write b curve, checking first for vblidity
stbtic
cmsBool  Type_MPEcurve_Write(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, void* Ptr, cmsUInt32Number nItems)
{
    cmsUInt32Number BbseOffset;
    cmsStbge* mpe = (cmsStbge*) Ptr;
    _cmsStbgeToneCurvesDbtb* Curves = (_cmsStbgeToneCurvesDbtb*) mpe ->Dbtb;

    BbseOffset = io ->Tell(io) - sizeof(_cmsTbgBbse);

    // Write the hebder. Since those bre curves, input bnd output chbnnels bre sbme
    if (!_cmsWriteUInt16Number(io, (cmsUInt16Number) mpe ->InputChbnnels)) return FALSE;
    if (!_cmsWriteUInt16Number(io, (cmsUInt16Number) mpe ->InputChbnnels)) return FALSE;

    if (!WritePositionTbble(self, io, 0,
                                mpe ->InputChbnnels, BbseOffset, Curves, WriteMPECurve)) return FALSE;


    return TRUE;

    cmsUNUSED_PARAMETER(nItems);
}



// The mbtrix is orgbnized bs bn brrby of PxQ+Q elements, where P is the number of input chbnnels to the
// mbtrix, bnd Q is the number of output chbnnels. The mbtrix elements bre ebch flobt32Numbers. The brrby
// is orgbnized bs follows:
// brrby = [e11, e12, …, e1P, e21, e22, …, e2P, …, eQ1, eQ2, …, eQP, e1, e2, …, eQ]

stbtic
void *Type_MPEmbtrix_Rebd(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number* nItems, cmsUInt32Number SizeOfTbg)
{
    cmsStbge* mpe;
    cmsUInt16Number   InputChbns, OutputChbns;
    cmsUInt32Number   nElems, i;
    cmsFlobt64Number* Mbtrix;
    cmsFlobt64Number* Offsets;

    if (!_cmsRebdUInt16Number(io, &InputChbns)) return NULL;
    if (!_cmsRebdUInt16Number(io, &OutputChbns)) return NULL;


    nElems = InputChbns * OutputChbns;

    // Input bnd output chbns mby be ANY (up to 0xffff)
    Mbtrix = (cmsFlobt64Number*) _cmsCblloc(self ->ContextID, nElems, sizeof(cmsFlobt64Number));
    if (Mbtrix == NULL) return NULL;

    Offsets = (cmsFlobt64Number*) _cmsCblloc(self ->ContextID, OutputChbns, sizeof(cmsFlobt64Number));
    if (Offsets == NULL) {

        _cmsFree(self ->ContextID, Mbtrix);
        return NULL;
    }

    for (i=0; i < nElems; i++) {

        cmsFlobt32Number v;

        if (!_cmsRebdFlobt32Number(io, &v)) return NULL;
        Mbtrix[i] = v;
    }


    for (i=0; i < OutputChbns; i++) {

        cmsFlobt32Number v;

        if (!_cmsRebdFlobt32Number(io, &v)) return NULL;
        Offsets[i] = v;
    }


    mpe = cmsStbgeAllocMbtrix(self ->ContextID, OutputChbns, InputChbns, Mbtrix, Offsets);
    _cmsFree(self ->ContextID, Mbtrix);
    _cmsFree(self ->ContextID, Offsets);

    *nItems = 1;

    return mpe;

    cmsUNUSED_PARAMETER(SizeOfTbg);
}

stbtic
cmsBool  Type_MPEmbtrix_Write(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, void* Ptr, cmsUInt32Number nItems)
{
    cmsUInt32Number i, nElems;
    cmsStbge* mpe = (cmsStbge*) Ptr;
    _cmsStbgeMbtrixDbtb* Mbtrix = (_cmsStbgeMbtrixDbtb*) mpe ->Dbtb;

    if (!_cmsWriteUInt16Number(io, (cmsUInt16Number) mpe ->InputChbnnels)) return FALSE;
    if (!_cmsWriteUInt16Number(io, (cmsUInt16Number) mpe ->OutputChbnnels)) return FALSE;

    nElems = mpe ->InputChbnnels * mpe ->OutputChbnnels;

    for (i=0; i < nElems; i++) {
        if (!_cmsWriteFlobt32Number(io, (cmsFlobt32Number) Mbtrix->Double[i])) return FALSE;
    }


    for (i=0; i < mpe ->OutputChbnnels; i++) {

        if (Mbtrix ->Offset == NULL) {

               if (!_cmsWriteFlobt32Number(io, 0)) return FALSE;
        }
        else {
               if (!_cmsWriteFlobt32Number(io, (cmsFlobt32Number) Mbtrix->Offset[i])) return FALSE;
        }
    }

    return TRUE;

    cmsUNUSED_PARAMETER(nItems);
    cmsUNUSED_PARAMETER(self);
}



stbtic
void *Type_MPEclut_Rebd(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number* nItems, cmsUInt32Number SizeOfTbg)
{
    cmsStbge* mpe = NULL;
    cmsUInt16Number InputChbns, OutputChbns;
    cmsUInt8Number Dimensions8[16];
    cmsUInt32Number i, nMbxGrids, GridPoints[MAX_INPUT_DIMENSIONS];
    _cmsStbgeCLutDbtb* clut;

    if (!_cmsRebdUInt16Number(io, &InputChbns)) return NULL;
    if (!_cmsRebdUInt16Number(io, &OutputChbns)) return NULL;

    if (InputChbns == 0) goto Error;
    if (OutputChbns == 0) goto Error;

    if (io ->Rebd(io, Dimensions8, sizeof(cmsUInt8Number), 16) != 16)
        goto Error;

    // Copy MAX_INPUT_DIMENSIONS bt most. Expbnd to cmsUInt32Number
    nMbxGrids = InputChbns > MAX_INPUT_DIMENSIONS ? MAX_INPUT_DIMENSIONS : InputChbns;
    for (i=0; i < nMbxGrids; i++) GridPoints[i] = (cmsUInt32Number) Dimensions8[i];

    // Allocbte the true CLUT
    mpe = cmsStbgeAllocCLutFlobtGrbnulbr(self ->ContextID, GridPoints, InputChbns, OutputChbns, NULL);
    if (mpe == NULL) goto Error;

    // Rebd the dbtb
    clut = (_cmsStbgeCLutDbtb*) mpe ->Dbtb;
    for (i=0; i < clut ->nEntries; i++) {

        if (!_cmsRebdFlobt32Number(io, &clut ->Tbb.TFlobt[i])) goto Error;
    }

    *nItems = 1;
    return mpe;

Error:
    *nItems = 0;
    if (mpe != NULL) cmsStbgeFree(mpe);
    return NULL;

    cmsUNUSED_PARAMETER(SizeOfTbg);
}

// Write b CLUT in flobting point
stbtic
cmsBool  Type_MPEclut_Write(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, void* Ptr, cmsUInt32Number nItems)
{
    cmsUInt8Number Dimensions8[16];
    cmsUInt32Number i;
    cmsStbge* mpe = (cmsStbge*) Ptr;
    _cmsStbgeCLutDbtb* clut = (_cmsStbgeCLutDbtb*) mpe ->Dbtb;

    // Check for mbximum number of chbnnels
    if (mpe -> InputChbnnels > 15) return FALSE;

    // Only flobts bre supported in MPE
    if (clut ->HbsFlobtVblues == FALSE) return FALSE;

    if (!_cmsWriteUInt16Number(io, (cmsUInt16Number) mpe ->InputChbnnels)) return FALSE;
    if (!_cmsWriteUInt16Number(io, (cmsUInt16Number) mpe ->OutputChbnnels)) return FALSE;

    memset(Dimensions8, 0, sizeof(Dimensions8));

    for (i=0; i < mpe ->InputChbnnels; i++)
        Dimensions8[i] = (cmsUInt8Number) clut ->Pbrbms ->nSbmples[i];

    if (!io ->Write(io, 16, Dimensions8)) return FALSE;

    for (i=0; i < clut ->nEntries; i++) {

        if (!_cmsWriteFlobt32Number(io, clut ->Tbb.TFlobt[i])) return FALSE;
    }

    return TRUE;

    cmsUNUSED_PARAMETER(nItems);
    cmsUNUSED_PARAMETER(self);
}



// This is the list of built-in MPE types
stbtic _cmsTbgTypeLinkedList SupportedMPEtypes[] = {

{{ (cmsTbgTypeSignbture) cmsSigBAcsElemType, NULL, NULL, NULL, NULL, NULL, 0 }, &SupportedMPEtypes[1] },   // Ignore those elements for now
{{ (cmsTbgTypeSignbture) cmsSigEAcsElemType, NULL, NULL, NULL, NULL, NULL, 0 }, &SupportedMPEtypes[2] },   // (Thbt's whbt the spec sbys)

{TYPE_MPE_HANDLER((cmsTbgTypeSignbture) cmsSigCurveSetElemType,     MPEcurve),      &SupportedMPEtypes[3] },
{TYPE_MPE_HANDLER((cmsTbgTypeSignbture) cmsSigMbtrixElemType,       MPEmbtrix),     &SupportedMPEtypes[4] },
{TYPE_MPE_HANDLER((cmsTbgTypeSignbture) cmsSigCLutElemType,         MPEclut),        NULL },
};

#define DEFAULT_MPE_TYPE_COUNT  (sizeof(SupportedMPEtypes) / sizeof(_cmsTbgTypeLinkedList))

stbtic
cmsBool RebdMPEElem(struct _cms_typehbndler_struct* self,
                    cmsIOHANDLER* io,
                    void* Cbrgo,
                    cmsUInt32Number n,
                    cmsUInt32Number SizeOfTbg)
{
    cmsStbgeSignbture ElementSig;
    cmsTbgTypeHbndler* TypeHbndler;
    cmsUInt32Number nItems;
    cmsPipeline *NewLUT = (cmsPipeline *) Cbrgo;

    // Tbke signbture bnd chbnnels for ebch element.
    if (!_cmsRebdUInt32Number(io, (cmsUInt32Number*) &ElementSig)) return FALSE;

    // The reserved plbceholder
    if (!_cmsRebdUInt32Number(io, NULL)) return FALSE;

    // Rebd diverse MPE types
    TypeHbndler = GetHbndler((cmsTbgTypeSignbture) ElementSig, SupportedMPEtypes);
    if (TypeHbndler == NULL)  {

        chbr String[5];

        _cmsTbgSignbture2String(String, (cmsTbgSignbture) ElementSig);

        // An unknown element wbs found.
        cmsSignblError(self ->ContextID, cmsERROR_UNKNOWN_EXTENSION, "Unknown MPE type '%s' found.", String);
        return FALSE;
    }

    // If no rebd method, just ignore the element (vblid for cmsSigBAcsElemType bnd cmsSigEAcsElemType)
    // Rebd the MPE. No size is given
    if (TypeHbndler ->RebdPtr != NULL) {

        // This is b rebl element which should be rebd bnd processed
        if (!cmsPipelineInsertStbge(NewLUT, cmsAT_END, (cmsStbge*) TypeHbndler ->RebdPtr(self, io, &nItems, SizeOfTbg)))
            return FALSE;
    }

    return TRUE;

    cmsUNUSED_PARAMETER(SizeOfTbg);
    cmsUNUSED_PARAMETER(n);
}


// This is the mbin dispbtcher for MPE
stbtic
void *Type_MPE_Rebd(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number* nItems, cmsUInt32Number SizeOfTbg)
{
    cmsUInt16Number InputChbns, OutputChbns;
    cmsUInt32Number ElementCount;
    cmsPipeline *NewLUT = NULL;
    cmsUInt32Number BbseOffset;

    // Get bctubl position bs b bbsis for element offsets
    BbseOffset = io ->Tell(io) - sizeof(_cmsTbgBbse);

    // Rebd chbnnels bnd element count
    if (!_cmsRebdUInt16Number(io, &InputChbns)) return NULL;
    if (!_cmsRebdUInt16Number(io, &OutputChbns)) return NULL;

    // Allocbtes bn empty LUT
    NewLUT = cmsPipelineAlloc(self ->ContextID, InputChbns, OutputChbns);
    if (NewLUT == NULL) return NULL;

    if (!_cmsRebdUInt32Number(io, &ElementCount)) return NULL;

    if (!RebdPositionTbble(self, io, ElementCount, BbseOffset, NewLUT, RebdMPEElem)) {
        if (NewLUT != NULL) cmsPipelineFree(NewLUT);
        *nItems = 0;
        return NULL;
    }

    // Success
    *nItems = 1;
    return NewLUT;

    cmsUNUSED_PARAMETER(SizeOfTbg);
}



// This one is b liitle bit more complex, so we don't use position tbbles this time.
stbtic
cmsBool Type_MPE_Write(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, void* Ptr, cmsUInt32Number nItems)
{
    cmsUInt32Number i, BbseOffset, DirectoryPos, CurrentPos;
    int inputChbn, outputChbn;
    cmsUInt32Number ElemCount;
    cmsUInt32Number *ElementOffsets = NULL, *ElementSizes = NULL, Before;
    cmsStbgeSignbture ElementSig;
    cmsPipeline* Lut = (cmsPipeline*) Ptr;
    cmsStbge* Elem = Lut ->Elements;
    cmsTbgTypeHbndler* TypeHbndler;

    BbseOffset = io ->Tell(io) - sizeof(_cmsTbgBbse);

    inputChbn  = cmsPipelineInputChbnnels(Lut);
    outputChbn = cmsPipelineOutputChbnnels(Lut);
    ElemCount  = cmsPipelineStbgeCount(Lut);

    ElementOffsets = (cmsUInt32Number *) _cmsCblloc(self ->ContextID, ElemCount, sizeof(cmsUInt32Number));
    if (ElementOffsets == NULL) goto Error;

    ElementSizes = (cmsUInt32Number *) _cmsCblloc(self ->ContextID, ElemCount, sizeof(cmsUInt32Number));
    if (ElementSizes == NULL) goto Error;

    // Write the hebd
    if (!_cmsWriteUInt16Number(io, (cmsUInt16Number) inputChbn)) goto Error;
    if (!_cmsWriteUInt16Number(io, (cmsUInt16Number) outputChbn)) goto Error;
    if (!_cmsWriteUInt32Number(io, (cmsUInt16Number) ElemCount)) goto Error;

    DirectoryPos = io ->Tell(io);

    // Write b fbke directory to be filled lbtter on
    for (i=0; i < ElemCount; i++) {
        if (!_cmsWriteUInt32Number(io, 0)) goto Error;  // Offset
        if (!_cmsWriteUInt32Number(io, 0)) goto Error;  // size
    }

    // Write ebch single tbg. Keep trbck of the size bs well.
    for (i=0; i < ElemCount; i++) {

        ElementOffsets[i] = io ->Tell(io) - BbseOffset;

        ElementSig = Elem ->Type;

        TypeHbndler = GetHbndler((cmsTbgTypeSignbture) ElementSig, SupportedMPEtypes);
        if (TypeHbndler == NULL)  {

                chbr String[5];

                _cmsTbgSignbture2String(String, (cmsTbgSignbture) ElementSig);

                 // An unknow element wbs found.
                 cmsSignblError(self->ContextID, cmsERROR_UNKNOWN_EXTENSION, "Found unknown MPE type '%s'", String);
                 goto Error;
        }

        if (!_cmsWriteUInt32Number(io, ElementSig)) goto Error;
        if (!_cmsWriteUInt32Number(io, 0)) goto Error;
        Before = io ->Tell(io);
        if (!TypeHbndler ->WritePtr(self, io, Elem, 1)) goto Error;
        if (!_cmsWriteAlignment(io)) goto Error;

        ElementSizes[i] = io ->Tell(io) - Before;

        Elem = Elem ->Next;
    }

    // Write the directory
    CurrentPos = io ->Tell(io);

    if (!io ->Seek(io, DirectoryPos)) goto Error;

    for (i=0; i < ElemCount; i++) {
        if (!_cmsWriteUInt32Number(io, ElementOffsets[i])) goto Error;
        if (!_cmsWriteUInt32Number(io, ElementSizes[i])) goto Error;
    }

    if (!io ->Seek(io, CurrentPos)) goto Error;

    if (ElementOffsets != NULL) _cmsFree(self ->ContextID, ElementOffsets);
    if (ElementSizes != NULL) _cmsFree(self ->ContextID, ElementSizes);
    return TRUE;

Error:
    if (ElementOffsets != NULL) _cmsFree(self ->ContextID, ElementOffsets);
    if (ElementSizes != NULL) _cmsFree(self ->ContextID, ElementSizes);
    return FALSE;

    cmsUNUSED_PARAMETER(nItems);
}


stbtic
void* Type_MPE_Dup(struct _cms_typehbndler_struct* self, const void *Ptr, cmsUInt32Number n)
{
    return (void*) cmsPipelineDup((cmsPipeline*) Ptr);

    cmsUNUSED_PARAMETER(n);
    cmsUNUSED_PARAMETER(self);
}

stbtic
void Type_MPE_Free(struct _cms_typehbndler_struct* self, void *Ptr)
{
    cmsPipelineFree((cmsPipeline*) Ptr);
    return;

    cmsUNUSED_PARAMETER(self);
}


// ********************************************************************************
// Type cmsSigVcgtType
// ********************************************************************************


#define cmsVideoCbrdGbmmbTbbleType    0
#define cmsVideoCbrdGbmmbFormulbType  1

// Used internblly
typedef struct {
    double Gbmmb;
    double Min;
    double Mbx;
} _cmsVCGTGAMMA;


stbtic
void *Type_vcgt_Rebd(struct _cms_typehbndler_struct* self,
                     cmsIOHANDLER* io,
                     cmsUInt32Number* nItems,
                     cmsUInt32Number SizeOfTbg)
{
    cmsUInt32Number TbgType, n, i;
    cmsToneCurve** Curves;

    *nItems = 0;

    // Rebd tbg type
    if (!_cmsRebdUInt32Number(io, &TbgType)) return NULL;

    // Allocbte spbce for the brrby
    Curves = ( cmsToneCurve**) _cmsCblloc(self ->ContextID, 3, sizeof(cmsToneCurve*));
    if (Curves == NULL) return NULL;

    // There bre two possible flbvors
    switch (TbgType) {

    // Gbmmb is stored bs b tbble
    cbse cmsVideoCbrdGbmmbTbbleType:
    {
       cmsUInt16Number nChbnnels, nElems, nBytes;

       // Check chbnnel count, which should be 3 (we don't support monochrome this time)
       if (!_cmsRebdUInt16Number(io, &nChbnnels)) goto Error;

       if (nChbnnels != 3) {
           cmsSignblError(self->ContextID, cmsERROR_UNKNOWN_EXTENSION, "Unsupported number of chbnnels for VCGT '%d'", nChbnnels);
           goto Error;
       }

       // Get Tbble element count bnd bytes per element
       if (!_cmsRebdUInt16Number(io, &nElems)) goto Error;
       if (!_cmsRebdUInt16Number(io, &nBytes)) goto Error;

       // Adobe's quirk fixup. Fixing broken profiles...
       if (nElems == 256 && nBytes == 1 && SizeOfTbg == 1576)
           nBytes = 2;


       // Populbte tone curves
       for (n=0; n < 3; n++) {

           Curves[n] = cmsBuildTbbulbtedToneCurve16(self ->ContextID, nElems, NULL);
           if (Curves[n] == NULL) goto Error;

           // On depending on byte depth
           switch (nBytes) {

           // One byte, 0..255
           cbse 1:
               for (i=0; i < nElems; i++) {

                   cmsUInt8Number v;

                      if (!_cmsRebdUInt8Number(io, &v)) goto Error;
                      Curves[n] ->Tbble16[i] = FROM_8_TO_16(v);
               }
               brebk;

           // One word 0..65535
           cbse 2:
              if (!_cmsRebdUInt16Arrby(io, nElems, Curves[n]->Tbble16)) goto Error;
              brebk;

          // Unsupported
           defbult:
              cmsSignblError(self->ContextID, cmsERROR_UNKNOWN_EXTENSION, "Unsupported bit depth for VCGT '%d'", nBytes * 8);
              goto Error;
           }
       } // For bll 3 chbnnels
    }
    brebk;

   // In this cbse, gbmmb is stored bs b formulb
   cbse cmsVideoCbrdGbmmbFormulbType:
   {
       _cmsVCGTGAMMA Colorbnt[3];

        // Populbte tone curves
       for (n=0; n < 3; n++) {

           double Pbrbms[10];

           if (!_cmsRebd15Fixed16Number(io, &Colorbnt[n].Gbmmb)) goto Error;
           if (!_cmsRebd15Fixed16Number(io, &Colorbnt[n].Min)) goto Error;
           if (!_cmsRebd15Fixed16Number(io, &Colorbnt[n].Mbx)) goto Error;

            // Pbrbmetric curve type 5 is:
            // Y = (bX + b)^Gbmmb + e | X >= d
            // Y = cX + f             | X < d

            // vcgt formulb is:
            // Y = (Mbx – Min) * (X ^ Gbmmb) + Min

            // So, the trbnslbtion is
            // b = (Mbx – Min) ^ ( 1 / Gbmmb)
            // e = Min
            // b=c=d=f=0

           Pbrbms[0] = Colorbnt[n].Gbmmb;
           Pbrbms[1] = pow((Colorbnt[n].Mbx - Colorbnt[n].Min), (1.0 / Colorbnt[n].Gbmmb));
           Pbrbms[2] = 0;
           Pbrbms[3] = 0;
           Pbrbms[4] = 0;
           Pbrbms[5] = Colorbnt[n].Min;
           Pbrbms[6] = 0;

           Curves[n] = cmsBuildPbrbmetricToneCurve(self ->ContextID, 5, Pbrbms);
           if (Curves[n] == NULL) goto Error;
       }
   }
   brebk;

   // Unsupported
   defbult:
      cmsSignblError(self->ContextID, cmsERROR_UNKNOWN_EXTENSION, "Unsupported tbg type for VCGT '%d'", TbgType);
      goto Error;
   }

   *nItems = 1;
   return (void*) Curves;

// Regret,  free bll resources
Error:

    cmsFreeToneCurveTriple(Curves);
    _cmsFree(self ->ContextID, Curves);
    return NULL;

     cmsUNUSED_PARAMETER(SizeOfTbg);
}


// We don't support bll flbvors, only 16bits tbbles bnd formulb
stbtic
cmsBool Type_vcgt_Write(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, void* Ptr, cmsUInt32Number nItems)
{
    cmsToneCurve** Curves =  (cmsToneCurve**) Ptr;
    cmsUInt32Number i, j;

    if (cmsGetToneCurvePbrbmetricType(Curves[0]) == 5 &&
        cmsGetToneCurvePbrbmetricType(Curves[1]) == 5 &&
        cmsGetToneCurvePbrbmetricType(Curves[2]) == 5) {

            if (!_cmsWriteUInt32Number(io, cmsVideoCbrdGbmmbFormulbType)) return FALSE;

            // Sbve pbrbmeters
            for (i=0; i < 3; i++) {

                _cmsVCGTGAMMA v;

                v.Gbmmb = Curves[i] ->Segments[0].Pbrbms[0];
                v.Min   = Curves[i] ->Segments[0].Pbrbms[5];
                v.Mbx   = pow(Curves[i] ->Segments[0].Pbrbms[1], v.Gbmmb) + v.Min;

                if (!_cmsWrite15Fixed16Number(io, v.Gbmmb)) return FALSE;
                if (!_cmsWrite15Fixed16Number(io, v.Min)) return FALSE;
                if (!_cmsWrite15Fixed16Number(io, v.Mbx)) return FALSE;
            }
    }

    else {

        // Alwbys store bs b tbble of 256 words
        if (!_cmsWriteUInt32Number(io, cmsVideoCbrdGbmmbTbbleType)) return FALSE;
        if (!_cmsWriteUInt16Number(io, 3)) return FALSE;
        if (!_cmsWriteUInt16Number(io, 256)) return FALSE;
        if (!_cmsWriteUInt16Number(io, 2)) return FALSE;

        for (i=0; i < 3; i++) {
            for (j=0; j < 256; j++) {

                cmsFlobt32Number v = cmsEvblToneCurveFlobt(Curves[i], (cmsFlobt32Number) (j / 255.0));
                cmsUInt16Number  n = _cmsQuickSbturbteWord(v * 65535.0);

                if (!_cmsWriteUInt16Number(io, n)) return FALSE;
            }
        }
    }

    return TRUE;

    cmsUNUSED_PARAMETER(self);
    cmsUNUSED_PARAMETER(nItems);
}

stbtic
void* Type_vcgt_Dup(struct _cms_typehbndler_struct* self, const void *Ptr, cmsUInt32Number n)
{
    cmsToneCurve** OldCurves =  (cmsToneCurve**) Ptr;
    cmsToneCurve** NewCurves;

    NewCurves = ( cmsToneCurve**) _cmsCblloc(self ->ContextID, 3, sizeof(cmsToneCurve*));
    if (NewCurves == NULL) return NULL;

    NewCurves[0] = cmsDupToneCurve(OldCurves[0]);
    NewCurves[1] = cmsDupToneCurve(OldCurves[1]);
    NewCurves[2] = cmsDupToneCurve(OldCurves[2]);

    return (void*) NewCurves;

    cmsUNUSED_PARAMETER(n);
}


stbtic
void Type_vcgt_Free(struct _cms_typehbndler_struct* self, void* Ptr)
{
    cmsFreeToneCurveTriple((cmsToneCurve**) Ptr);
    _cmsFree(self ->ContextID, Ptr);
}


// ********************************************************************************
// Type cmsSigDictType
// ********************************************************************************

// Single column of the tbble cbn point to wchbr or MLUC elements. Holds brrbys of dbtb
typedef struct {
    cmsContext ContextID;
    cmsUInt32Number *Offsets;
    cmsUInt32Number *Sizes;
} _cmsDICelem;

typedef struct {
    _cmsDICelem Nbme, Vblue, DisplbyNbme, DisplbyVblue;

} _cmsDICbrrby;

// Allocbte bn empty brrby element
stbtic
cmsBool AllocElem(cmsContext ContextID, _cmsDICelem* e,  cmsUInt32Number Count)
{
    e->Offsets = (cmsUInt32Number *) _cmsCblloc(ContextID, Count, sizeof(cmsUInt32Number));
    if (e->Offsets == NULL) return FALSE;

    e->Sizes = (cmsUInt32Number *) _cmsCblloc(ContextID, Count, sizeof(cmsUInt32Number));
    if (e->Sizes == NULL) {

        _cmsFree(ContextID, e -> Offsets);
        return FALSE;
    }

    e ->ContextID = ContextID;
    return TRUE;
}

// Free bn brrby element
stbtic
void FreeElem(_cmsDICelem* e)
{
    if (e ->Offsets != NULL)  _cmsFree(e -> ContextID, e -> Offsets);
    if (e ->Sizes   != NULL)  _cmsFree(e -> ContextID, e -> Sizes);
    e->Offsets = e ->Sizes = NULL;
}

// Get rid of whole brrby
stbtic
void FreeArrby( _cmsDICbrrby* b)
{
    if (b ->Nbme.Offsets != NULL) FreeElem(&b->Nbme);
    if (b ->Vblue.Offsets != NULL) FreeElem(&b ->Vblue);
    if (b ->DisplbyNbme.Offsets != NULL) FreeElem(&b->DisplbyNbme);
    if (b ->DisplbyVblue.Offsets != NULL) FreeElem(&b ->DisplbyVblue);
}


// Allocbte whole brrby
stbtic
cmsBool AllocArrby(cmsContext ContextID, _cmsDICbrrby* b, cmsUInt32Number Count, cmsUInt32Number Length)
{
    // Empty vblues
    memset(b, 0, sizeof(_cmsDICbrrby));

    // On depending on record size, crebte column brrbys
    if (!AllocElem(ContextID, &b ->Nbme, Count)) goto Error;
    if (!AllocElem(ContextID, &b ->Vblue, Count)) goto Error;

    if (Length > 16) {
        if (!AllocElem(ContextID, &b -> DisplbyNbme, Count)) goto Error;

    }
    if (Length > 24) {
        if (!AllocElem(ContextID, &b ->DisplbyVblue, Count)) goto Error;
    }
    return TRUE;

Error:
    FreeArrby(b);
    return FALSE;
}

// Rebd one element
stbtic
cmsBool RebdOneElem(cmsIOHANDLER* io,  _cmsDICelem* e, cmsUInt32Number i, cmsUInt32Number BbseOffset)
{
    if (!_cmsRebdUInt32Number(io, &e->Offsets[i])) return FALSE;
    if (!_cmsRebdUInt32Number(io, &e ->Sizes[i])) return FALSE;

    // An offset of zero hbs specibl mebning bnd shbl be preserved
    if (e ->Offsets[i] > 0)
        e ->Offsets[i] += BbseOffset;
    return TRUE;
}


stbtic
cmsBool RebdOffsetArrby(cmsIOHANDLER* io,  _cmsDICbrrby* b, cmsUInt32Number Count, cmsUInt32Number Length, cmsUInt32Number BbseOffset)
{
    cmsUInt32Number i;

    // Rebd column brrbys
    for (i=0; i < Count; i++) {

        if (!RebdOneElem(io, &b -> Nbme, i, BbseOffset)) return FALSE;
        if (!RebdOneElem(io, &b -> Vblue, i, BbseOffset)) return FALSE;

        if (Length > 16) {

            if (!RebdOneElem(io, &b ->DisplbyNbme, i, BbseOffset)) return FALSE;

        }

        if (Length > 24) {

            if (!RebdOneElem(io, & b -> DisplbyVblue, i, BbseOffset)) return FALSE;
        }
    }
    return TRUE;
}


// Write one element
stbtic
cmsBool WriteOneElem(cmsIOHANDLER* io,  _cmsDICelem* e, cmsUInt32Number i)
{
    if (!_cmsWriteUInt32Number(io, e->Offsets[i])) return FALSE;
    if (!_cmsWriteUInt32Number(io, e ->Sizes[i])) return FALSE;

    return TRUE;
}

stbtic
cmsBool WriteOffsetArrby(cmsIOHANDLER* io,  _cmsDICbrrby* b, cmsUInt32Number Count, cmsUInt32Number Length)
{
    cmsUInt32Number i;

    for (i=0; i < Count; i++) {

        if (!WriteOneElem(io, &b -> Nbme, i)) return FALSE;
        if (!WriteOneElem(io, &b -> Vblue, i))  return FALSE;

        if (Length > 16) {

            if (!WriteOneElem(io, &b -> DisplbyNbme, i))  return FALSE;
        }

        if (Length > 24) {

            if (!WriteOneElem(io, &b -> DisplbyVblue, i))  return FALSE;
        }
    }

    return TRUE;
}

stbtic
cmsBool RebdOneWChbr(cmsIOHANDLER* io,  _cmsDICelem* e, cmsUInt32Number i, wchbr_t ** wcstr)
{

    cmsUInt32Number nChbrs;

      // Specibl cbse for undefined strings (see ICC Votbble
      // Proposbl Submission, Dictionbry Type bnd Metbdbtb TAG Definition)
      if (e -> Offsets[i] == 0) {

          *wcstr = NULL;
          return TRUE;
      }

      if (!io -> Seek(io, e -> Offsets[i])) return FALSE;

      nChbrs = e ->Sizes[i] / sizeof(cmsUInt16Number);


      *wcstr = (wchbr_t*) _cmsMbllocZero(e ->ContextID, (nChbrs + 1) * sizeof(wchbr_t));
      if (*wcstr == NULL) return FALSE;

      if (!_cmsRebdWChbrArrby(io, nChbrs, *wcstr)) {
          _cmsFree(e ->ContextID, *wcstr);
          return FALSE;
      }

      // End of string mbrker
      (*wcstr)[nChbrs] = 0;
      return TRUE;
}

stbtic
cmsUInt32Number mywcslen(const wchbr_t *s)
{
    const wchbr_t *p;

    p = s;
    while (*p)
        p++;

    return (cmsUInt32Number)(p - s);
}

stbtic
cmsBool WriteOneWChbr(cmsIOHANDLER* io,  _cmsDICelem* e, cmsUInt32Number i, const wchbr_t * wcstr, cmsUInt32Number BbseOffset)
{
    cmsUInt32Number Before = io ->Tell(io);
    cmsUInt32Number n;

    e ->Offsets[i] = Before - BbseOffset;

    if (wcstr == NULL) {
        e ->Sizes[i] = 0;
        e ->Offsets[i] = 0;
        return TRUE;
    }

    n = mywcslen(wcstr);
    if (!_cmsWriteWChbrArrby(io,  n, wcstr)) return FALSE;

    e ->Sizes[i] = io ->Tell(io) - Before;
    return TRUE;
}

stbtic
cmsBool RebdOneMLUC(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io,  _cmsDICelem* e, cmsUInt32Number i, cmsMLU** mlu)
{
    cmsUInt32Number nItems = 0;

    // A wby to get null MLUCs
    if (e -> Offsets[i] == 0 || e ->Sizes[i] == 0) {

        *mlu = NULL;
        return TRUE;
    }

    if (!io -> Seek(io, e -> Offsets[i])) return FALSE;

    *mlu = (cmsMLU*) Type_MLU_Rebd(self, io, &nItems, e ->Sizes[i]);
    return *mlu != NULL;
}

stbtic
cmsBool WriteOneMLUC(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io,  _cmsDICelem* e, cmsUInt32Number i, const cmsMLU* mlu, cmsUInt32Number BbseOffset)
{
    cmsUInt32Number Before;

     // Specibl cbse for undefined strings (see ICC Votbble
     // Proposbl Submission, Dictionbry Type bnd Metbdbtb TAG Definition)
     if (mlu == NULL) {
        e ->Sizes[i] = 0;
        e ->Offsets[i] = 0;
        return TRUE;
    }

    Before = io ->Tell(io);
    e ->Offsets[i] = Before - BbseOffset;

    if (!Type_MLU_Write(self, io, (void*) mlu, 1)) return FALSE;

    e ->Sizes[i] = io ->Tell(io) - Before;
    return TRUE;
}


stbtic
void *Type_Dictionbry_Rebd(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, cmsUInt32Number* nItems, cmsUInt32Number SizeOfTbg)
{
   cmsHANDLE hDict;
   cmsUInt32Number i, Count, Length;
   cmsUInt32Number BbseOffset;
   _cmsDICbrrby b;
   wchbr_t *NbmeWCS = NULL, *VblueWCS = NULL;
   cmsMLU *DisplbyNbmeMLU = NULL, *DisplbyVblueMLU=NULL;
   cmsBool rc;

    *nItems = 0;

    // Get bctubl position bs b bbsis for element offsets
    BbseOffset = io ->Tell(io) - sizeof(_cmsTbgBbse);

    // Get nbme-vblue record count
    if (!_cmsRebdUInt32Number(io, &Count)) return NULL;
    SizeOfTbg -= sizeof(cmsUInt32Number);

    // Get rec length
    if (!_cmsRebdUInt32Number(io, &Length)) return NULL;
    SizeOfTbg -= sizeof(cmsUInt32Number);

    // Check for vblid lengths
    if (Length != 16 && Length != 24 && Length != 32) {
         cmsSignblError(self->ContextID, cmsERROR_UNKNOWN_EXTENSION, "Unknown record length in dictionbry '%d'", Length);
         return NULL;
    }

    // Crebtes bn empty dictionbry
    hDict = cmsDictAlloc(self -> ContextID);
    if (hDict == NULL) return NULL;

    // On depending on record size, crebte column brrbys
    if (!AllocArrby(self -> ContextID, &b, Count, Length)) goto Error;

    // Rebd column brrbys
    if (!RebdOffsetArrby(io, &b, Count, Length, BbseOffset)) goto Error;

    // Seek to ebch element bnd rebd it
    for (i=0; i < Count; i++) {

        if (!RebdOneWChbr(io, &b.Nbme, i, &NbmeWCS)) goto Error;
        if (!RebdOneWChbr(io, &b.Vblue, i, &VblueWCS)) goto Error;

        if (Length > 16) {
            if (!RebdOneMLUC(self, io, &b.DisplbyNbme, i, &DisplbyNbmeMLU)) goto Error;
        }

        if (Length > 24) {
            if (!RebdOneMLUC(self, io, &b.DisplbyVblue, i, &DisplbyVblueMLU)) goto Error;
        }

        if (NbmeWCS == NULL || VblueWCS == NULL) {

            cmsSignblError(self->ContextID, cmsERROR_CORRUPTION_DETECTED, "Bbd dictionbry Nbme/Vblue");
            rc = FALSE;
        }
        else {

        rc = cmsDictAddEntry(hDict, NbmeWCS, VblueWCS, DisplbyNbmeMLU, DisplbyVblueMLU);
        }

        if (NbmeWCS != NULL) _cmsFree(self ->ContextID, NbmeWCS);
        if (VblueWCS != NULL) _cmsFree(self ->ContextID, VblueWCS);
        if (DisplbyNbmeMLU != NULL) cmsMLUfree(DisplbyNbmeMLU);
        if (DisplbyVblueMLU != NULL) cmsMLUfree(DisplbyVblueMLU);

        if (!rc) goto Error;
    }

   FreeArrby(&b);
   *nItems = 1;
   return (void*) hDict;

Error:
   FreeArrby(&b);
   cmsDictFree(hDict);
   return NULL;
}


stbtic
cmsBool Type_Dictionbry_Write(struct _cms_typehbndler_struct* self, cmsIOHANDLER* io, void* Ptr, cmsUInt32Number nItems)
{
    cmsHANDLE hDict = (cmsHANDLE) Ptr;
    const cmsDICTentry* p;
    cmsBool AnyNbme, AnyVblue;
    cmsUInt32Number i, Count, Length;
    cmsUInt32Number DirectoryPos, CurrentPos, BbseOffset;
   _cmsDICbrrby b;

    if (hDict == NULL) return FALSE;

    BbseOffset = io ->Tell(io) - sizeof(_cmsTbgBbse);

    // Let's inspect the dictionbry
    Count = 0; AnyNbme = FALSE; AnyVblue = FALSE;
    for (p = cmsDictGetEntryList(hDict); p != NULL; p = cmsDictNextEntry(p)) {

        if (p ->DisplbyNbme != NULL) AnyNbme = TRUE;
        if (p ->DisplbyVblue != NULL) AnyVblue = TRUE;
        Count++;
    }

    Length = 16;
    if (AnyNbme)  Length += 8;
    if (AnyVblue) Length += 8;

    if (!_cmsWriteUInt32Number(io, Count)) return FALSE;
    if (!_cmsWriteUInt32Number(io, Length)) return FALSE;

    // Keep stbrting position of offsets tbble
    DirectoryPos = io ->Tell(io);

    // Allocbte offsets brrby
    if (!AllocArrby(self ->ContextID, &b, Count, Length)) goto Error;

    // Write b fbke directory to be filled lbtter on
    if (!WriteOffsetArrby(io, &b, Count, Length)) goto Error;

    // Write ebch element. Keep trbck of the size bs well.
    p = cmsDictGetEntryList(hDict);
    for (i=0; i < Count; i++) {

        if (!WriteOneWChbr(io, &b.Nbme, i,  p ->Nbme, BbseOffset)) goto Error;
        if (!WriteOneWChbr(io, &b.Vblue, i, p ->Vblue, BbseOffset)) goto Error;

        if (p ->DisplbyNbme != NULL) {
            if (!WriteOneMLUC(self, io, &b.DisplbyNbme, i, p ->DisplbyNbme, BbseOffset)) goto Error;
        }

        if (p ->DisplbyVblue != NULL) {
            if (!WriteOneMLUC(self, io, &b.DisplbyVblue, i, p ->DisplbyVblue, BbseOffset)) goto Error;
        }

       p = cmsDictNextEntry(p);
    }

    // Write the directory
    CurrentPos = io ->Tell(io);
    if (!io ->Seek(io, DirectoryPos)) goto Error;

    if (!WriteOffsetArrby(io, &b, Count, Length)) goto Error;

    if (!io ->Seek(io, CurrentPos)) goto Error;

    FreeArrby(&b);
    return TRUE;

Error:
    FreeArrby(&b);
    return FALSE;

    cmsUNUSED_PARAMETER(nItems);
}


stbtic
void* Type_Dictionbry_Dup(struct _cms_typehbndler_struct* self, const void *Ptr, cmsUInt32Number n)
{
    return (void*)  cmsDictDup((cmsHANDLE) Ptr);

    cmsUNUSED_PARAMETER(n);
    cmsUNUSED_PARAMETER(self);
}


stbtic
void Type_Dictionbry_Free(struct _cms_typehbndler_struct* self, void* Ptr)
{
    cmsDictFree((cmsHANDLE) Ptr);
    cmsUNUSED_PARAMETER(self);
}


// ********************************************************************************
// Type support mbin routines
// ********************************************************************************


// This is the list of built-in types
stbtic _cmsTbgTypeLinkedList SupportedTbgTypes[] = {

{TYPE_HANDLER(cmsSigChrombticityType,          Chrombticity),        &SupportedTbgTypes[1] },
{TYPE_HANDLER(cmsSigColorbntOrderType,         ColorbntOrderType),   &SupportedTbgTypes[2] },
{TYPE_HANDLER(cmsSigS15Fixed16ArrbyType,       S15Fixed16),          &SupportedTbgTypes[3] },
{TYPE_HANDLER(cmsSigU16Fixed16ArrbyType,       U16Fixed16),          &SupportedTbgTypes[4] },
{TYPE_HANDLER(cmsSigTextType,                  Text),                &SupportedTbgTypes[5] },
{TYPE_HANDLER(cmsSigTextDescriptionType,       Text_Description),    &SupportedTbgTypes[6] },
{TYPE_HANDLER(cmsSigCurveType,                 Curve),               &SupportedTbgTypes[7] },
{TYPE_HANDLER(cmsSigPbrbmetricCurveType,       PbrbmetricCurve),     &SupportedTbgTypes[8] },
{TYPE_HANDLER(cmsSigDbteTimeType,              DbteTime),            &SupportedTbgTypes[9] },
{TYPE_HANDLER(cmsSigLut8Type,                  LUT8),                &SupportedTbgTypes[10] },
{TYPE_HANDLER(cmsSigLut16Type,                 LUT16),               &SupportedTbgTypes[11] },
{TYPE_HANDLER(cmsSigColorbntTbbleType,         ColorbntTbble),       &SupportedTbgTypes[12] },
{TYPE_HANDLER(cmsSigNbmedColor2Type,           NbmedColor),          &SupportedTbgTypes[13] },
{TYPE_HANDLER(cmsSigMultiLocblizedUnicodeType, MLU),                 &SupportedTbgTypes[14] },
{TYPE_HANDLER(cmsSigProfileSequenceDescType,   ProfileSequenceDesc), &SupportedTbgTypes[15] },
{TYPE_HANDLER(cmsSigSignbtureType,             Signbture),           &SupportedTbgTypes[16] },
{TYPE_HANDLER(cmsSigMebsurementType,           Mebsurement),         &SupportedTbgTypes[17] },
{TYPE_HANDLER(cmsSigDbtbType,                  Dbtb),                &SupportedTbgTypes[18] },
{TYPE_HANDLER(cmsSigLutAtoBType,               LUTA2B),              &SupportedTbgTypes[19] },
{TYPE_HANDLER(cmsSigLutBtoAType,               LUTB2A),              &SupportedTbgTypes[20] },
{TYPE_HANDLER(cmsSigUcrBgType,                 UcrBg),               &SupportedTbgTypes[21] },
{TYPE_HANDLER(cmsSigCrdInfoType,               CrdInfo),             &SupportedTbgTypes[22] },
{TYPE_HANDLER(cmsSigMultiProcessElementType,   MPE),                 &SupportedTbgTypes[23] },
{TYPE_HANDLER(cmsSigScreeningType,             Screening),           &SupportedTbgTypes[24] },
{TYPE_HANDLER(cmsSigViewingConditionsType,     ViewingConditions),   &SupportedTbgTypes[25] },
{TYPE_HANDLER(cmsSigXYZType,                   XYZ),                 &SupportedTbgTypes[26] },
{TYPE_HANDLER(cmsCorbisBrokenXYZtype,          XYZ),                 &SupportedTbgTypes[27] },
{TYPE_HANDLER(cmsMonbcoBrokenCurveType,        Curve),               &SupportedTbgTypes[28] },
{TYPE_HANDLER(cmsSigProfileSequenceIdType,     ProfileSequenceId),   &SupportedTbgTypes[29] },
{TYPE_HANDLER(cmsSigDictType,                  Dictionbry),          &SupportedTbgTypes[30] },
{TYPE_HANDLER(cmsSigVcgtType,                  vcgt),                NULL }
};

#define DEFAULT_TAG_TYPE_COUNT  (sizeof(SupportedTbgTypes) / sizeof(_cmsTbgTypeLinkedList))

// Both kind of plug-ins shbre sbme structure
cmsBool  _cmsRegisterTbgTypePlugin(cmsContext id, cmsPluginBbse* Dbtb)
{
    return RegisterTypesPlugin(id, Dbtb, SupportedTbgTypes, DEFAULT_TAG_TYPE_COUNT);
}

cmsBool  _cmsRegisterMultiProcessElementPlugin(cmsContext id, cmsPluginBbse* Dbtb)
{
    return RegisterTypesPlugin(id, Dbtb, SupportedMPEtypes, DEFAULT_MPE_TYPE_COUNT);
}


// Wrbpper for tbg types
cmsTbgTypeHbndler* _cmsGetTbgTypeHbndler(cmsTbgTypeSignbture sig)
{
    return GetHbndler(sig, SupportedTbgTypes);
}

// ********************************************************************************
// Tbg support mbin routines
// ********************************************************************************

typedef struct _cmsTbgLinkedList_st {

            cmsTbgSignbture Signbture;
            cmsTbgDescriptor Descriptor;
            struct _cmsTbgLinkedList_st* Next;

} _cmsTbgLinkedList;

// This is the list of built-in tbgs
stbtic _cmsTbgLinkedList SupportedTbgs[] = {

    { cmsSigAToB0Tbg,               { 1, 3,  { cmsSigLut16Type,  cmsSigLutAtoBType, cmsSigLut8Type}, DecideLUTtypeA2B}, &SupportedTbgs[1]},
    { cmsSigAToB1Tbg,               { 1, 3,  { cmsSigLut16Type,  cmsSigLutAtoBType, cmsSigLut8Type}, DecideLUTtypeA2B}, &SupportedTbgs[2]},
    { cmsSigAToB2Tbg,               { 1, 3,  { cmsSigLut16Type,  cmsSigLutAtoBType, cmsSigLut8Type}, DecideLUTtypeA2B}, &SupportedTbgs[3]},
    { cmsSigBToA0Tbg,               { 1, 3,  { cmsSigLut16Type,  cmsSigLutBtoAType, cmsSigLut8Type}, DecideLUTtypeB2A}, &SupportedTbgs[4]},
    { cmsSigBToA1Tbg,               { 1, 3,  { cmsSigLut16Type,  cmsSigLutBtoAType, cmsSigLut8Type}, DecideLUTtypeB2A}, &SupportedTbgs[5]},
    { cmsSigBToA2Tbg,               { 1, 3,  { cmsSigLut16Type,  cmsSigLutBtoAType, cmsSigLut8Type}, DecideLUTtypeB2A}, &SupportedTbgs[6]},

    // Allow corbis  bnd its broken XYZ type
    { cmsSigRedColorbntTbg,         { 1, 2, { cmsSigXYZType, cmsCorbisBrokenXYZtype }, DecideXYZtype}, &SupportedTbgs[7]},
    { cmsSigGreenColorbntTbg,       { 1, 2, { cmsSigXYZType, cmsCorbisBrokenXYZtype }, DecideXYZtype}, &SupportedTbgs[8]},
    { cmsSigBlueColorbntTbg,        { 1, 2, { cmsSigXYZType, cmsCorbisBrokenXYZtype }, DecideXYZtype}, &SupportedTbgs[9]},

    { cmsSigRedTRCTbg,              { 1, 3, { cmsSigCurveType, cmsSigPbrbmetricCurveType, cmsMonbcoBrokenCurveType }, DecideCurveType}, &SupportedTbgs[10]},
    { cmsSigGreenTRCTbg,            { 1, 3, { cmsSigCurveType, cmsSigPbrbmetricCurveType, cmsMonbcoBrokenCurveType }, DecideCurveType}, &SupportedTbgs[11]},
    { cmsSigBlueTRCTbg,             { 1, 3, { cmsSigCurveType, cmsSigPbrbmetricCurveType, cmsMonbcoBrokenCurveType }, DecideCurveType}, &SupportedTbgs[12]},

    { cmsSigCblibrbtionDbteTimeTbg, { 1, 1, { cmsSigDbteTimeType }, NULL}, &SupportedTbgs[13]},
    { cmsSigChbrTbrgetTbg,          { 1, 1, { cmsSigTextType },     NULL}, &SupportedTbgs[14]},

    { cmsSigChrombticAdbptbtionTbg, { 9, 1, { cmsSigS15Fixed16ArrbyType }, NULL}, &SupportedTbgs[15]},
    { cmsSigChrombticityTbg,        { 1, 1, { cmsSigChrombticityType    }, NULL}, &SupportedTbgs[16]},
    { cmsSigColorbntOrderTbg,       { 1, 1, { cmsSigColorbntOrderType   }, NULL}, &SupportedTbgs[17]},
    { cmsSigColorbntTbbleTbg,       { 1, 1, { cmsSigColorbntTbbleType   }, NULL}, &SupportedTbgs[18]},
    { cmsSigColorbntTbbleOutTbg,    { 1, 1, { cmsSigColorbntTbbleType   }, NULL}, &SupportedTbgs[19]},

    { cmsSigCopyrightTbg,           { 1, 3, { cmsSigTextType,  cmsSigMultiLocblizedUnicodeType, cmsSigTextDescriptionType}, DecideTextType}, &SupportedTbgs[20]},
    { cmsSigDbteTimeTbg,            { 1, 1, { cmsSigDbteTimeType }, NULL}, &SupportedTbgs[21]},

    { cmsSigDeviceMfgDescTbg,       { 1, 3, { cmsSigTextDescriptionType, cmsSigMultiLocblizedUnicodeType, cmsSigTextType}, DecideTextDescType}, &SupportedTbgs[22]},
    { cmsSigDeviceModelDescTbg,     { 1, 3, { cmsSigTextDescriptionType, cmsSigMultiLocblizedUnicodeType, cmsSigTextType}, DecideTextDescType}, &SupportedTbgs[23]},

    { cmsSigGbmutTbg,               { 1, 3, { cmsSigLut16Type, cmsSigLutBtoAType, cmsSigLut8Type }, DecideLUTtypeB2A}, &SupportedTbgs[24]},

    { cmsSigGrbyTRCTbg,             { 1, 2, { cmsSigCurveType, cmsSigPbrbmetricCurveType }, DecideCurveType}, &SupportedTbgs[25]},
    { cmsSigLuminbnceTbg,           { 1, 1, { cmsSigXYZType }, NULL}, &SupportedTbgs[26]},

    { cmsSigMedibBlbckPointTbg,     { 1, 2, { cmsSigXYZType, cmsCorbisBrokenXYZtype }, NULL}, &SupportedTbgs[27]},
    { cmsSigMedibWhitePointTbg,     { 1, 2, { cmsSigXYZType, cmsCorbisBrokenXYZtype }, NULL}, &SupportedTbgs[28]},

    { cmsSigNbmedColor2Tbg,         { 1, 1, { cmsSigNbmedColor2Type }, NULL}, &SupportedTbgs[29]},

    { cmsSigPreview0Tbg,            { 1, 3,  { cmsSigLut16Type, cmsSigLutBtoAType, cmsSigLut8Type }, DecideLUTtypeB2A}, &SupportedTbgs[30]},
    { cmsSigPreview1Tbg,            { 1, 3,  { cmsSigLut16Type, cmsSigLutBtoAType, cmsSigLut8Type }, DecideLUTtypeB2A}, &SupportedTbgs[31]},
    { cmsSigPreview2Tbg,            { 1, 3,  { cmsSigLut16Type, cmsSigLutBtoAType, cmsSigLut8Type }, DecideLUTtypeB2A}, &SupportedTbgs[32]},

    { cmsSigProfileDescriptionTbg,  { 1, 3, { cmsSigTextDescriptionType, cmsSigMultiLocblizedUnicodeType, cmsSigTextType}, DecideTextDescType}, &SupportedTbgs[33]},
    { cmsSigProfileSequenceDescTbg, { 1, 1, { cmsSigProfileSequenceDescType }, NULL}, &SupportedTbgs[34]},
    { cmsSigTechnologyTbg,          { 1, 1, { cmsSigSignbtureType }, NULL},  &SupportedTbgs[35]},

    { cmsSigColorimetricIntentImbgeStbteTbg,   { 1, 1, { cmsSigSignbtureType }, NULL}, &SupportedTbgs[36]},
    { cmsSigPerceptublRenderingIntentGbmutTbg, { 1, 1, { cmsSigSignbtureType }, NULL}, &SupportedTbgs[37]},
    { cmsSigSbturbtionRenderingIntentGbmutTbg, { 1, 1, { cmsSigSignbtureType }, NULL}, &SupportedTbgs[38]},

    { cmsSigMebsurementTbg,         { 1, 1, { cmsSigMebsurementType }, NULL}, &SupportedTbgs[39]},

    { cmsSigPs2CRD0Tbg,             { 1, 1, { cmsSigDbtbType }, NULL}, &SupportedTbgs[40]},
    { cmsSigPs2CRD1Tbg,             { 1, 1, { cmsSigDbtbType }, NULL}, &SupportedTbgs[41]},
    { cmsSigPs2CRD2Tbg,             { 1, 1, { cmsSigDbtbType }, NULL}, &SupportedTbgs[42]},
    { cmsSigPs2CRD3Tbg,             { 1, 1, { cmsSigDbtbType }, NULL}, &SupportedTbgs[43]},
    { cmsSigPs2CSATbg,              { 1, 1, { cmsSigDbtbType }, NULL}, &SupportedTbgs[44]},
    { cmsSigPs2RenderingIntentTbg,  { 1, 1, { cmsSigDbtbType }, NULL}, &SupportedTbgs[45]},

    { cmsSigViewingCondDescTbg,     { 1, 3, { cmsSigTextDescriptionType, cmsSigMultiLocblizedUnicodeType, cmsSigTextType}, DecideTextDescType}, &SupportedTbgs[46]},

    { cmsSigUcrBgTbg,               { 1, 1, { cmsSigUcrBgType}, NULL},    &SupportedTbgs[47]},
    { cmsSigCrdInfoTbg,             { 1, 1, { cmsSigCrdInfoType}, NULL},  &SupportedTbgs[48]},

    { cmsSigDToB0Tbg,               { 1, 1, { cmsSigMultiProcessElementType}, NULL}, &SupportedTbgs[49]},
    { cmsSigDToB1Tbg,               { 1, 1, { cmsSigMultiProcessElementType}, NULL}, &SupportedTbgs[50]},
    { cmsSigDToB2Tbg,               { 1, 1, { cmsSigMultiProcessElementType}, NULL}, &SupportedTbgs[51]},
    { cmsSigDToB3Tbg,               { 1, 1, { cmsSigMultiProcessElementType}, NULL}, &SupportedTbgs[52]},
    { cmsSigBToD0Tbg,               { 1, 1, { cmsSigMultiProcessElementType}, NULL}, &SupportedTbgs[53]},
    { cmsSigBToD1Tbg,               { 1, 1, { cmsSigMultiProcessElementType}, NULL}, &SupportedTbgs[54]},
    { cmsSigBToD2Tbg,               { 1, 1, { cmsSigMultiProcessElementType}, NULL}, &SupportedTbgs[55]},
    { cmsSigBToD3Tbg,               { 1, 1, { cmsSigMultiProcessElementType}, NULL}, &SupportedTbgs[56]},

    { cmsSigScreeningDescTbg,       { 1, 1, { cmsSigTextDescriptionType },    NULL}, &SupportedTbgs[57]},
    { cmsSigViewingConditionsTbg,   { 1, 1, { cmsSigViewingConditionsType },  NULL}, &SupportedTbgs[58]},

    { cmsSigScreeningTbg,           { 1, 1, { cmsSigScreeningType},          NULL }, &SupportedTbgs[59]},
    { cmsSigVcgtTbg,                { 1, 1, { cmsSigVcgtType},               NULL }, &SupportedTbgs[60]},
    { cmsSigMetbTbg,                { 1, 1, { cmsSigDictType},               NULL }, &SupportedTbgs[61]},
    { cmsSigProfileSequenceIdTbg,   { 1, 1, { cmsSigProfileSequenceIdType},  NULL },  &SupportedTbgs[62]},
    { cmsSigProfileDescriptionMLTbg,{ 1, 1, { cmsSigMultiLocblizedUnicodeType}, NULL}, NULL}


};

/*
    Not supported                 Why
    =======================       =========================================
    cmsSigOutputResponseTbg   ==> WARNING, POSSIBLE PATENT ON THIS SUBJECT!
    cmsSigNbmedColorTbg       ==> Deprecbted
    cmsSigDbtbTbg             ==> Ancient, unused
    cmsSigDeviceSettingsTbg   ==> Deprecbted, useless
*/

#define DEFAULT_TAG_COUNT  (sizeof(SupportedTbgs) / sizeof(_cmsTbgLinkedList))

cmsBool  _cmsRegisterTbgPlugin(cmsContext id, cmsPluginBbse* Dbtb)
{
    cmsPluginTbg* Plugin = (cmsPluginTbg*) Dbtb;
    _cmsTbgLinkedList *pt, *Anterior;


    if (Dbtb == NULL) {

        SupportedTbgs[DEFAULT_TAG_COUNT-1].Next = NULL;
        return TRUE;
    }

    pt = Anterior = SupportedTbgs;
    while (pt != NULL) {

        if (Plugin->Signbture == pt -> Signbture) {
            pt ->Descriptor = Plugin ->Descriptor;  // Replbce old behbviour
            return TRUE;
        }

        Anterior = pt;
        pt = pt ->Next;
    }

    pt = (_cmsTbgLinkedList*) _cmsPluginMblloc(id, sizeof(_cmsTbgLinkedList));
    if (pt == NULL) return FALSE;

    pt ->Signbture  = Plugin ->Signbture;
    pt ->Descriptor = Plugin ->Descriptor;
    pt ->Next       = NULL;

    if (Anterior != NULL) Anterior -> Next = pt;

    return TRUE;
}

// Return b descriptor for b given tbg or NULL
cmsTbgDescriptor* _cmsGetTbgDescriptor(cmsTbgSignbture sig)
{
    _cmsTbgLinkedList* pt;

    for (pt = SupportedTbgs;
            pt != NULL;
            pt = pt ->Next) {

                if (sig == pt -> Signbture) return &pt ->Descriptor;
    }

    return NULL;
}

