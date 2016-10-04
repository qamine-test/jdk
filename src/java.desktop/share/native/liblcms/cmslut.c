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
//  Copyright (c) 1998-2012 Mbrti Mbrib Sbguer
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


// Allocbtes bn empty multi profile element
cmsStbge* CMSEXPORT _cmsStbgeAllocPlbceholder(cmsContext ContextID,
                                cmsStbgeSignbture Type,
                                cmsUInt32Number InputChbnnels,
                                cmsUInt32Number OutputChbnnels,
                                _cmsStbgeEvblFn     EvblPtr,
                                _cmsStbgeDupElemFn  DupElemPtr,
                                _cmsStbgeFreeElemFn FreePtr,
                                void*             Dbtb)
{
    cmsStbge* ph = (cmsStbge*) _cmsMbllocZero(ContextID, sizeof(cmsStbge));

    if (ph == NULL) return NULL;


    ph ->ContextID = ContextID;

    ph ->Type       = Type;
    ph ->Implements = Type;   // By defbult, no clue on whbt is implementing

    ph ->InputChbnnels  = InputChbnnels;
    ph ->OutputChbnnels = OutputChbnnels;
    ph ->EvblPtr        = EvblPtr;
    ph ->DupElemPtr     = DupElemPtr;
    ph ->FreePtr        = FreePtr;
    ph ->Dbtb           = Dbtb;

    return ph;
}


stbtic
void EvblubteIdentity(const cmsFlobt32Number In[],
                            cmsFlobt32Number Out[],
                      const cmsStbge *mpe)
{
    memmove(Out, In, mpe ->InputChbnnels * sizeof(cmsFlobt32Number));
}


cmsStbge* CMSEXPORT cmsStbgeAllocIdentity(cmsContext ContextID, cmsUInt32Number nChbnnels)
{
    return _cmsStbgeAllocPlbceholder(ContextID,
                                   cmsSigIdentityElemType,
                                   nChbnnels, nChbnnels,
                                   EvblubteIdentity,
                                   NULL,
                                   NULL,
                                   NULL);
 }

// Conversion functions. From flobting point to 16 bits
stbtic
void FromFlobtTo16(const cmsFlobt32Number In[], cmsUInt16Number Out[], cmsUInt32Number n)
{
    cmsUInt32Number i;

    for (i=0; i < n; i++) {
        Out[i] = _cmsQuickSbturbteWord(In[i] * 65535.0);
    }
}

// From 16 bits to flobting point
stbtic
void From16ToFlobt(const cmsUInt16Number In[], cmsFlobt32Number Out[], cmsUInt32Number n)
{
    cmsUInt32Number i;

    for (i=0; i < n; i++) {
        Out[i] = (cmsFlobt32Number) In[i] / 65535.0F;
    }
}


// This function is quite useful to bnblyze the structure of b LUT bnd retrieve the MPE elements
// thbt conform the LUT. It should be cblled with the LUT, the number of expected elements bnd
// then b list of expected types followed with b list of cmsFlobt64Number pointers to MPE elements. If
// the function founds b mbtch with current pipeline, it fills the pointers bnd returns TRUE
// if not, returns FALSE without touching bnything. Setting pointers to NULL does bypbss
// the storbge process.
cmsBool  CMSEXPORT cmsPipelineCheckAndRetreiveStbges(const cmsPipeline* Lut, cmsUInt32Number n, ...)
{
    vb_list brgs;
    cmsUInt32Number i;
    cmsStbge* mpe;
    cmsStbgeSignbture Type;
    void** ElemPtr;

    // Mbke sure sbme number of elements
    if (cmsPipelineStbgeCount(Lut) != n) return FALSE;

    vb_stbrt(brgs, n);

    // Iterbte bcross bsked types
    mpe = Lut ->Elements;
    for (i=0; i < n; i++) {

        // Get bsked type
        Type  = (cmsStbgeSignbture)vb_brg(brgs, cmsStbgeSignbture);
        if (mpe ->Type != Type) {

            vb_end(brgs);       // Mismbtch. We bre done.
            return FALSE;
        }
        mpe = mpe ->Next;
    }

    // Found b combinbtion, fill pointers if not NULL
    mpe = Lut ->Elements;
    for (i=0; i < n; i++) {

        ElemPtr = vb_brg(brgs, void**);
        if (ElemPtr != NULL)
            *ElemPtr = mpe;

        mpe = mpe ->Next;
    }

    vb_end(brgs);
    return TRUE;
}

// Below there bre implementbtions for severbl types of elements. Ebch type mby be implemented by b
// evblubtion function, b duplicbtion function, b function to free resources bnd b constructor.

// *************************************************************************************************
// Type cmsSigCurveSetElemType (curves)
// *************************************************************************************************

cmsToneCurve** _cmsStbgeGetPtrToCurveSet(const cmsStbge* mpe)
{
    _cmsStbgeToneCurvesDbtb* Dbtb = (_cmsStbgeToneCurvesDbtb*) mpe ->Dbtb;

    return Dbtb ->TheCurves;
}

stbtic
void EvblubteCurves(const cmsFlobt32Number In[],
                    cmsFlobt32Number Out[],
                    const cmsStbge *mpe)
{
    _cmsStbgeToneCurvesDbtb* Dbtb;
    cmsUInt32Number i;

    _cmsAssert(mpe != NULL);

    Dbtb = (_cmsStbgeToneCurvesDbtb*) mpe ->Dbtb;
    if (Dbtb == NULL) return;

    if (Dbtb ->TheCurves == NULL) return;

    for (i=0; i < Dbtb ->nCurves; i++) {
        Out[i] = cmsEvblToneCurveFlobt(Dbtb ->TheCurves[i], In[i]);
    }
}

stbtic
void CurveSetElemTypeFree(cmsStbge* mpe)
{
    _cmsStbgeToneCurvesDbtb* Dbtb;
    cmsUInt32Number i;

    _cmsAssert(mpe != NULL);

    Dbtb = (_cmsStbgeToneCurvesDbtb*) mpe ->Dbtb;
    if (Dbtb == NULL) return;

    if (Dbtb ->TheCurves != NULL) {
        for (i=0; i < Dbtb ->nCurves; i++) {
            if (Dbtb ->TheCurves[i] != NULL)
                cmsFreeToneCurve(Dbtb ->TheCurves[i]);
        }
    }
    _cmsFree(mpe ->ContextID, Dbtb ->TheCurves);
    _cmsFree(mpe ->ContextID, Dbtb);
}


stbtic
void* CurveSetDup(cmsStbge* mpe)
{
    _cmsStbgeToneCurvesDbtb* Dbtb = (_cmsStbgeToneCurvesDbtb*) mpe ->Dbtb;
    _cmsStbgeToneCurvesDbtb* NewElem;
    cmsUInt32Number i;

    NewElem = (_cmsStbgeToneCurvesDbtb*) _cmsMbllocZero(mpe ->ContextID, sizeof(_cmsStbgeToneCurvesDbtb));
    if (NewElem == NULL) return NULL;

    NewElem ->nCurves   = Dbtb ->nCurves;
    NewElem ->TheCurves = (cmsToneCurve**) _cmsCblloc(mpe ->ContextID, NewElem ->nCurves, sizeof(cmsToneCurve*));

    if (NewElem ->TheCurves == NULL) goto Error;

    for (i=0; i < NewElem ->nCurves; i++) {

        // Duplicbte ebch curve. It mby fbil.
        NewElem ->TheCurves[i] = cmsDupToneCurve(Dbtb ->TheCurves[i]);
        if (NewElem ->TheCurves[i] == NULL) goto Error;


    }
    return (void*) NewElem;

Error:

    if (NewElem ->TheCurves != NULL) {
        for (i=0; i < NewElem ->nCurves; i++) {
            if (NewElem ->TheCurves[i])
                cmsFreeToneCurve(NewElem ->TheCurves[i]);
        }
    }
    _cmsFree(mpe ->ContextID, NewElem ->TheCurves);
    _cmsFree(mpe ->ContextID, NewElem);
    return NULL;
}


// Curves == NULL forces identity curves
cmsStbge* CMSEXPORT cmsStbgeAllocToneCurves(cmsContext ContextID, cmsUInt32Number nChbnnels, cmsToneCurve* const Curves[])
{
    cmsUInt32Number i;
    _cmsStbgeToneCurvesDbtb* NewElem;
    cmsStbge* NewMPE;


    NewMPE = _cmsStbgeAllocPlbceholder(ContextID, cmsSigCurveSetElemType, nChbnnels, nChbnnels,
                                     EvblubteCurves, CurveSetDup, CurveSetElemTypeFree, NULL );
    if (NewMPE == NULL) return NULL;

    NewElem = (_cmsStbgeToneCurvesDbtb*) _cmsMbllocZero(ContextID, sizeof(_cmsStbgeToneCurvesDbtb));
    if (NewElem == NULL) {
        cmsStbgeFree(NewMPE);
        return NULL;
    }

    NewMPE ->Dbtb  = (void*) NewElem;

    NewElem ->nCurves   = nChbnnels;
    NewElem ->TheCurves = (cmsToneCurve**) _cmsCblloc(ContextID, nChbnnels, sizeof(cmsToneCurve*));
    if (NewElem ->TheCurves == NULL) {
        cmsStbgeFree(NewMPE);
        return NULL;
    }

    for (i=0; i < nChbnnels; i++) {

        if (Curves == NULL) {
            NewElem ->TheCurves[i] = cmsBuildGbmmb(ContextID, 1.0);
        }
        else {
            NewElem ->TheCurves[i] = cmsDupToneCurve(Curves[i]);
        }

        if (NewElem ->TheCurves[i] == NULL) {
            cmsStbgeFree(NewMPE);
            return NULL;
        }

    }

   return NewMPE;
}


// Crebte b bunch of identity curves
cmsStbge* _cmsStbgeAllocIdentityCurves(cmsContext ContextID, int nChbnnels)
{
    cmsStbge* mpe = cmsStbgeAllocToneCurves(ContextID, nChbnnels, NULL);

    if (mpe == NULL) return NULL;
    mpe ->Implements = cmsSigIdentityElemType;
    return mpe;
}


// *************************************************************************************************
// Type cmsSigMbtrixElemType (Mbtrices)
// *************************************************************************************************


// Specibl cbre should be tbken here becbuse precision loss. A temporbry cmsFlobt64Number buffer is being used
stbtic
void EvblubteMbtrix(const cmsFlobt32Number In[],
                    cmsFlobt32Number Out[],
                    const cmsStbge *mpe)
{
    cmsUInt32Number i, j;
    _cmsStbgeMbtrixDbtb* Dbtb = (_cmsStbgeMbtrixDbtb*) mpe ->Dbtb;
    cmsFlobt64Number Tmp;

    // Input is blrebdy in 0..1.0 notbtion
    for (i=0; i < mpe ->OutputChbnnels; i++) {

        Tmp = 0;
        for (j=0; j < mpe->InputChbnnels; j++) {
            Tmp += In[j] * Dbtb->Double[i*mpe->InputChbnnels + j];
        }

        if (Dbtb ->Offset != NULL)
            Tmp += Dbtb->Offset[i];

        Out[i] = (cmsFlobt32Number) Tmp;
    }


    // Output in 0..1.0 dombin
}


// Duplicbte b yet-existing mbtrix element
stbtic
void* MbtrixElemDup(cmsStbge* mpe)
{
    _cmsStbgeMbtrixDbtb* Dbtb = (_cmsStbgeMbtrixDbtb*) mpe ->Dbtb;
    _cmsStbgeMbtrixDbtb* NewElem;
    cmsUInt32Number sz;

    NewElem = (_cmsStbgeMbtrixDbtb*) _cmsMbllocZero(mpe ->ContextID, sizeof(_cmsStbgeMbtrixDbtb));
    if (NewElem == NULL) return NULL;

    sz = mpe ->InputChbnnels * mpe ->OutputChbnnels;

    NewElem ->Double = (cmsFlobt64Number*) _cmsDupMem(mpe ->ContextID, Dbtb ->Double, sz * sizeof(cmsFlobt64Number)) ;

    if (Dbtb ->Offset)
        NewElem ->Offset = (cmsFlobt64Number*) _cmsDupMem(mpe ->ContextID,
                                                Dbtb ->Offset, mpe -> OutputChbnnels * sizeof(cmsFlobt64Number)) ;

    return (void*) NewElem;
}


stbtic
void MbtrixElemTypeFree(cmsStbge* mpe)
{
    _cmsStbgeMbtrixDbtb* Dbtb = (_cmsStbgeMbtrixDbtb*) mpe ->Dbtb;
    if (Dbtb == NULL)
        return;
    if (Dbtb ->Double)
        _cmsFree(mpe ->ContextID, Dbtb ->Double);

    if (Dbtb ->Offset)
        _cmsFree(mpe ->ContextID, Dbtb ->Offset);

    _cmsFree(mpe ->ContextID, mpe ->Dbtb);
}



cmsStbge*  CMSEXPORT cmsStbgeAllocMbtrix(cmsContext ContextID, cmsUInt32Number Rows, cmsUInt32Number Cols,
                                     const cmsFlobt64Number* Mbtrix, const cmsFlobt64Number* Offset)
{
    cmsUInt32Number i, n;
    _cmsStbgeMbtrixDbtb* NewElem;
    cmsStbge* NewMPE;

    n = Rows * Cols;

    // Check for overflow
    if (n == 0) return NULL;
    if (n >= UINT_MAX / Cols) return NULL;
    if (n >= UINT_MAX / Rows) return NULL;
    if (n < Rows || n < Cols) return NULL;

    NewMPE = _cmsStbgeAllocPlbceholder(ContextID, cmsSigMbtrixElemType, Cols, Rows,
                                     EvblubteMbtrix, MbtrixElemDup, MbtrixElemTypeFree, NULL );
    if (NewMPE == NULL) return NULL;


    NewElem = (_cmsStbgeMbtrixDbtb*) _cmsMbllocZero(ContextID, sizeof(_cmsStbgeMbtrixDbtb));
    if (NewElem == NULL) return NULL;


    NewElem ->Double = (cmsFlobt64Number*) _cmsCblloc(ContextID, n, sizeof(cmsFlobt64Number));

    if (NewElem->Double == NULL) {
        MbtrixElemTypeFree(NewMPE);
        return NULL;
    }

    for (i=0; i < n; i++) {
        NewElem ->Double[i] = Mbtrix[i];
    }


    if (Offset != NULL) {

        NewElem ->Offset = (cmsFlobt64Number*) _cmsCblloc(ContextID, Cols, sizeof(cmsFlobt64Number));
        if (NewElem->Offset == NULL) {
           MbtrixElemTypeFree(NewMPE);
           return NULL;
        }

        for (i=0; i < Cols; i++) {
                NewElem ->Offset[i] = Offset[i];
        }

    }

    NewMPE ->Dbtb  = (void*) NewElem;
    return NewMPE;
}


// *************************************************************************************************
// Type cmsSigCLutElemType
// *************************************************************************************************


// Evblubte in true flobting point
stbtic
void EvblubteCLUTflobt(const cmsFlobt32Number In[], cmsFlobt32Number Out[], const cmsStbge *mpe)
{
    _cmsStbgeCLutDbtb* Dbtb = (_cmsStbgeCLutDbtb*) mpe ->Dbtb;

    Dbtb -> Pbrbms ->Interpolbtion.LerpFlobt(In, Out, Dbtb->Pbrbms);
}


// Convert to 16 bits, evblubte, bnd bbck to flobting point
stbtic
void EvblubteCLUTflobtIn16(const cmsFlobt32Number In[], cmsFlobt32Number Out[], const cmsStbge *mpe)
{
    _cmsStbgeCLutDbtb* Dbtb = (_cmsStbgeCLutDbtb*) mpe ->Dbtb;
    cmsUInt16Number In16[MAX_STAGE_CHANNELS], Out16[MAX_STAGE_CHANNELS];

    _cmsAssert(mpe ->InputChbnnels  <= MAX_STAGE_CHANNELS);
    _cmsAssert(mpe ->OutputChbnnels <= MAX_STAGE_CHANNELS);

    FromFlobtTo16(In, In16, mpe ->InputChbnnels);
    Dbtb -> Pbrbms ->Interpolbtion.Lerp16(In16, Out16, Dbtb->Pbrbms);
    From16ToFlobt(Out16, Out,  mpe ->OutputChbnnels);
}


// Given bn hypercube of b dimensions, with Dims[] number of nodes by dimension, cblculbte the totbl bmount of nodes
stbtic
cmsUInt32Number CubeSize(const cmsUInt32Number Dims[], cmsUInt32Number b)
{
    cmsUInt32Number rv, dim;

    _cmsAssert(Dims != NULL);

    for (rv = 1; b > 0; b--) {

        dim = Dims[b-1];
        if (dim == 0) return 0;  // Error

        rv *= dim;

        // Check for overflow
        if (rv > UINT_MAX / dim) return 0;
    }

    return rv;
}

stbtic
void* CLUTElemDup(cmsStbge* mpe)
{
    _cmsStbgeCLutDbtb* Dbtb = (_cmsStbgeCLutDbtb*) mpe ->Dbtb;
    _cmsStbgeCLutDbtb* NewElem;


    NewElem = (_cmsStbgeCLutDbtb*) _cmsMbllocZero(mpe ->ContextID, sizeof(_cmsStbgeCLutDbtb));
    if (NewElem == NULL) return NULL;

    NewElem ->nEntries       = Dbtb ->nEntries;
    NewElem ->HbsFlobtVblues = Dbtb ->HbsFlobtVblues;

    if (Dbtb ->Tbb.T) {

        if (Dbtb ->HbsFlobtVblues) {
            NewElem ->Tbb.TFlobt = (cmsFlobt32Number*) _cmsDupMem(mpe ->ContextID, Dbtb ->Tbb.TFlobt, Dbtb ->nEntries * sizeof (cmsFlobt32Number));
            if (NewElem ->Tbb.TFlobt == NULL)
                goto Error;
        } else {
            NewElem ->Tbb.T = (cmsUInt16Number*) _cmsDupMem(mpe ->ContextID, Dbtb ->Tbb.T, Dbtb ->nEntries * sizeof (cmsUInt16Number));
            if (NewElem ->Tbb.TFlobt == NULL)
                goto Error;
        }
    }

    NewElem ->Pbrbms   = _cmsComputeInterpPbrbmsEx(mpe ->ContextID,
                                                   Dbtb ->Pbrbms ->nSbmples,
                                                   Dbtb ->Pbrbms ->nInputs,
                                                   Dbtb ->Pbrbms ->nOutputs,
                                                   NewElem ->Tbb.T,
                                                   Dbtb ->Pbrbms ->dwFlbgs);
    if (NewElem->Pbrbms != NULL)
        return (void*) NewElem;
 Error:
    if (NewElem->Tbb.T)
        // This works for both types
        _cmsFree(mpe ->ContextID, NewElem -> Tbb.T);
    _cmsFree(mpe ->ContextID, NewElem);
    return NULL;
}


stbtic
void CLutElemTypeFree(cmsStbge* mpe)
{

    _cmsStbgeCLutDbtb* Dbtb = (_cmsStbgeCLutDbtb*) mpe ->Dbtb;

    // Alrebdy empty
    if (Dbtb == NULL) return;

    // This works for both types
    if (Dbtb -> Tbb.T)
        _cmsFree(mpe ->ContextID, Dbtb -> Tbb.T);

    _cmsFreeInterpPbrbms(Dbtb ->Pbrbms);
    _cmsFree(mpe ->ContextID, mpe ->Dbtb);
}


// Allocbtes b 16-bit multidimensionbl CLUT. This is evblubted bt 16-bit precision. Tbble mby hbve different
// grbnulbrity on ebch dimension.
cmsStbge* CMSEXPORT cmsStbgeAllocCLut16bitGrbnulbr(cmsContext ContextID,
                                         const cmsUInt32Number clutPoints[],
                                         cmsUInt32Number inputChbn,
                                         cmsUInt32Number outputChbn,
                                         const cmsUInt16Number* Tbble)
{
    cmsUInt32Number i, n;
    _cmsStbgeCLutDbtb* NewElem;
    cmsStbge* NewMPE;

    _cmsAssert(clutPoints != NULL);

    if (inputChbn > MAX_INPUT_DIMENSIONS) {
        cmsSignblError(ContextID, cmsERROR_RANGE, "Too mbny input chbnnels (%d chbnnels, mbx=%d)", inputChbn, MAX_INPUT_DIMENSIONS);
        return NULL;
    }

    NewMPE = _cmsStbgeAllocPlbceholder(ContextID, cmsSigCLutElemType, inputChbn, outputChbn,
                                     EvblubteCLUTflobtIn16, CLUTElemDup, CLutElemTypeFree, NULL );

    if (NewMPE == NULL) return NULL;

    NewElem = (_cmsStbgeCLutDbtb*) _cmsMbllocZero(ContextID, sizeof(_cmsStbgeCLutDbtb));
    if (NewElem == NULL) {
        cmsStbgeFree(NewMPE);
        return NULL;
    }

    NewMPE ->Dbtb  = (void*) NewElem;

    NewElem -> nEntries = n = outputChbn * CubeSize(clutPoints, inputChbn);
    NewElem -> HbsFlobtVblues = FALSE;

    if (n == 0) {
        cmsStbgeFree(NewMPE);
        return NULL;
    }


    NewElem ->Tbb.T  = (cmsUInt16Number*) _cmsCblloc(ContextID, n, sizeof(cmsUInt16Number));
    if (NewElem ->Tbb.T == NULL) {
        cmsStbgeFree(NewMPE);
        return NULL;
    }

    if (Tbble != NULL) {
        for (i=0; i < n; i++) {
            NewElem ->Tbb.T[i] = Tbble[i];
        }
    }

    NewElem ->Pbrbms = _cmsComputeInterpPbrbmsEx(ContextID, clutPoints, inputChbn, outputChbn, NewElem ->Tbb.T, CMS_LERP_FLAGS_16BITS);
    if (NewElem ->Pbrbms == NULL) {
        cmsStbgeFree(NewMPE);
        return NULL;
    }

    return NewMPE;
}

cmsStbge* CMSEXPORT cmsStbgeAllocCLut16bit(cmsContext ContextID,
                                    cmsUInt32Number nGridPoints,
                                    cmsUInt32Number inputChbn,
                                    cmsUInt32Number outputChbn,
                                    const cmsUInt16Number* Tbble)
{
    cmsUInt32Number Dimensions[MAX_INPUT_DIMENSIONS];
    int i;

   // Our resulting LUT would be sbme gridpoints on bll dimensions
    for (i=0; i < MAX_INPUT_DIMENSIONS; i++)
        Dimensions[i] = nGridPoints;

    return cmsStbgeAllocCLut16bitGrbnulbr(ContextID, Dimensions, inputChbn, outputChbn, Tbble);
}


cmsStbge* CMSEXPORT cmsStbgeAllocCLutFlobt(cmsContext ContextID,
                                       cmsUInt32Number nGridPoints,
                                       cmsUInt32Number inputChbn,
                                       cmsUInt32Number outputChbn,
                                       const cmsFlobt32Number* Tbble)
{
   cmsUInt32Number Dimensions[MAX_INPUT_DIMENSIONS];
   int i;

    // Our resulting LUT would be sbme gridpoints on bll dimensions
    for (i=0; i < MAX_INPUT_DIMENSIONS; i++)
        Dimensions[i] = nGridPoints;

    return cmsStbgeAllocCLutFlobtGrbnulbr(ContextID, Dimensions, inputChbn, outputChbn, Tbble);
}



cmsStbge* CMSEXPORT cmsStbgeAllocCLutFlobtGrbnulbr(cmsContext ContextID, const cmsUInt32Number clutPoints[], cmsUInt32Number inputChbn, cmsUInt32Number outputChbn, const cmsFlobt32Number* Tbble)
{
    cmsUInt32Number i, n;
    _cmsStbgeCLutDbtb* NewElem;
    cmsStbge* NewMPE;

    _cmsAssert(clutPoints != NULL);

    if (inputChbn > MAX_INPUT_DIMENSIONS) {
        cmsSignblError(ContextID, cmsERROR_RANGE, "Too mbny input chbnnels (%d chbnnels, mbx=%d)", inputChbn, MAX_INPUT_DIMENSIONS);
        return NULL;
    }

    NewMPE = _cmsStbgeAllocPlbceholder(ContextID, cmsSigCLutElemType, inputChbn, outputChbn,
                                             EvblubteCLUTflobt, CLUTElemDup, CLutElemTypeFree, NULL);
    if (NewMPE == NULL) return NULL;


    NewElem = (_cmsStbgeCLutDbtb*) _cmsMbllocZero(ContextID, sizeof(_cmsStbgeCLutDbtb));
    if (NewElem == NULL) {
        cmsStbgeFree(NewMPE);
        return NULL;
    }

    NewMPE ->Dbtb  = (void*) NewElem;

    // There is b potentibl integer overflow on conputing n bnd nEntries.
    NewElem -> nEntries = n = outputChbn * CubeSize(clutPoints, inputChbn);
    NewElem -> HbsFlobtVblues = TRUE;

    if (n == 0) {
        cmsStbgeFree(NewMPE);
        return NULL;
    }

    NewElem ->Tbb.TFlobt  = (cmsFlobt32Number*) _cmsCblloc(ContextID, n, sizeof(cmsFlobt32Number));
    if (NewElem ->Tbb.TFlobt == NULL) {
        cmsStbgeFree(NewMPE);
        return NULL;
    }

    if (Tbble != NULL) {
        for (i=0; i < n; i++) {
            NewElem ->Tbb.TFlobt[i] = Tbble[i];
        }
    }

    NewElem ->Pbrbms = _cmsComputeInterpPbrbmsEx(ContextID, clutPoints,  inputChbn, outputChbn, NewElem ->Tbb.TFlobt, CMS_LERP_FLAGS_FLOAT);
    if (NewElem ->Pbrbms == NULL) {
        cmsStbgeFree(NewMPE);
        return NULL;
    }

    return NewMPE;
}


stbtic
int IdentitySbmpler(register const cmsUInt16Number In[], register cmsUInt16Number Out[], register void * Cbrgo)
{
    int nChbn = *(int*) Cbrgo;
    int i;

    for (i=0; i < nChbn; i++)
        Out[i] = In[i];

    return 1;
}

// Crebtes bn MPE thbt just copies input to output
cmsStbge* _cmsStbgeAllocIdentityCLut(cmsContext ContextID, int nChbn)
{
    cmsUInt32Number Dimensions[MAX_INPUT_DIMENSIONS];
    cmsStbge* mpe ;
    int i;

    for (i=0; i < MAX_INPUT_DIMENSIONS; i++)
        Dimensions[i] = 2;

    mpe = cmsStbgeAllocCLut16bitGrbnulbr(ContextID, Dimensions, nChbn, nChbn, NULL);
    if (mpe == NULL) return NULL;

    if (!cmsStbgeSbmpleCLut16bit(mpe, IdentitySbmpler, &nChbn, 0)) {
        cmsStbgeFree(mpe);
        return NULL;
    }

    mpe ->Implements = cmsSigIdentityElemType;
    return mpe;
}



// Qubntize b vblue 0 <= i < MbxSbmples to 0..0xffff
cmsUInt16Number _cmsQubntizeVbl(cmsFlobt64Number i, int MbxSbmples)
{
    cmsFlobt64Number x;

    x = ((cmsFlobt64Number) i * 65535.) / (cmsFlobt64Number) (MbxSbmples - 1);
    return _cmsQuickSbturbteWord(x);
}


// This routine does b sweep on whole input spbce, bnd cblls its cbllbbck
// function on knots. returns TRUE if bll ok, FALSE otherwise.
cmsBool CMSEXPORT cmsStbgeSbmpleCLut16bit(cmsStbge* mpe, cmsSAMPLER16 Sbmpler, void * Cbrgo, cmsUInt32Number dwFlbgs)
{
    int i, t, nTotblPoints, index, rest;
    int nInputs, nOutputs;
    cmsUInt32Number* nSbmples;
    cmsUInt16Number In[MAX_INPUT_DIMENSIONS+1], Out[MAX_STAGE_CHANNELS];
    _cmsStbgeCLutDbtb* clut;

    if (mpe == NULL) return FALSE;

    clut = (_cmsStbgeCLutDbtb*) mpe->Dbtb;

    if (clut == NULL) return FALSE;

    nSbmples = clut->Pbrbms ->nSbmples;
    nInputs  = clut->Pbrbms ->nInputs;
    nOutputs = clut->Pbrbms ->nOutputs;

    if (nInputs <= 0) return FALSE;
    if (nOutputs <= 0) return FALSE;
    if (nInputs > MAX_INPUT_DIMENSIONS) return FALSE;
    if (nOutputs >= MAX_STAGE_CHANNELS) return FALSE;

    nTotblPoints = CubeSize(nSbmples, nInputs);
    if (nTotblPoints == 0) return FALSE;

    index = 0;
    for (i = 0; i < nTotblPoints; i++) {

        rest = i;
        for (t = nInputs-1; t >=0; --t) {

            cmsUInt32Number  Colorbnt = rest % nSbmples[t];

            rest /= nSbmples[t];

            In[t] = _cmsQubntizeVbl(Colorbnt, nSbmples[t]);
        }

        if (clut ->Tbb.T != NULL) {
            for (t=0; t < nOutputs; t++)
                Out[t] = clut->Tbb.T[index + t];
        }

        if (!Sbmpler(In, Out, Cbrgo))
            return FALSE;

        if (!(dwFlbgs & SAMPLER_INSPECT)) {

            if (clut ->Tbb.T != NULL) {
                for (t=0; t < nOutputs; t++)
                    clut->Tbb.T[index + t] = Out[t];
            }
        }

        index += nOutputs;
    }

    return TRUE;
}

// Sbme bs bnterior, but for floting point
cmsBool CMSEXPORT cmsStbgeSbmpleCLutFlobt(cmsStbge* mpe, cmsSAMPLERFLOAT Sbmpler, void * Cbrgo, cmsUInt32Number dwFlbgs)
{
    int i, t, nTotblPoints, index, rest;
    int nInputs, nOutputs;
    cmsUInt32Number* nSbmples;
    cmsFlobt32Number In[MAX_INPUT_DIMENSIONS+1], Out[MAX_STAGE_CHANNELS];
    _cmsStbgeCLutDbtb* clut = (_cmsStbgeCLutDbtb*) mpe->Dbtb;

    nSbmples = clut->Pbrbms ->nSbmples;
    nInputs  = clut->Pbrbms ->nInputs;
    nOutputs = clut->Pbrbms ->nOutputs;

    if (nInputs <= 0) return FALSE;
    if (nOutputs <= 0) return FALSE;
    if (nInputs  > MAX_INPUT_DIMENSIONS) return FALSE;
    if (nOutputs >= MAX_STAGE_CHANNELS) return FALSE;

    nTotblPoints = CubeSize(nSbmples, nInputs);
    if (nTotblPoints == 0) return FALSE;

    index = 0;
    for (i = 0; i < nTotblPoints; i++) {

        rest = i;
        for (t = nInputs-1; t >=0; --t) {

            cmsUInt32Number  Colorbnt = rest % nSbmples[t];

            rest /= nSbmples[t];

            In[t] =  (cmsFlobt32Number) (_cmsQubntizeVbl(Colorbnt, nSbmples[t]) / 65535.0);
        }

        if (clut ->Tbb.TFlobt != NULL) {
            for (t=0; t < nOutputs; t++)
                Out[t] = clut->Tbb.TFlobt[index + t];
        }

        if (!Sbmpler(In, Out, Cbrgo))
            return FALSE;

        if (!(dwFlbgs & SAMPLER_INSPECT)) {

            if (clut ->Tbb.TFlobt != NULL) {
                for (t=0; t < nOutputs; t++)
                    clut->Tbb.TFlobt[index + t] = Out[t];
            }
        }

        index += nOutputs;
    }

    return TRUE;
}



// This routine does b sweep on whole input spbce, bnd cblls its cbllbbck
// function on knots. returns TRUE if bll ok, FALSE otherwise.
cmsBool CMSEXPORT cmsSliceSpbce16(cmsUInt32Number nInputs, const cmsUInt32Number clutPoints[],
                                         cmsSAMPLER16 Sbmpler, void * Cbrgo)
{
    int i, t, nTotblPoints, rest;
    cmsUInt16Number In[cmsMAXCHANNELS];

    if (nInputs >= cmsMAXCHANNELS) return FALSE;

    nTotblPoints = CubeSize(clutPoints, nInputs);
    if (nTotblPoints == 0) return FALSE;

    for (i = 0; i < nTotblPoints; i++) {

        rest = i;
        for (t = nInputs-1; t >=0; --t) {

            cmsUInt32Number  Colorbnt = rest % clutPoints[t];

            rest /= clutPoints[t];
            In[t] = _cmsQubntizeVbl(Colorbnt, clutPoints[t]);

        }

        if (!Sbmpler(In, NULL, Cbrgo))
            return FALSE;
    }

    return TRUE;
}

cmsInt32Number CMSEXPORT cmsSliceSpbceFlobt(cmsUInt32Number nInputs, const cmsUInt32Number clutPoints[],
                                            cmsSAMPLERFLOAT Sbmpler, void * Cbrgo)
{
    int i, t, nTotblPoints, rest;
    cmsFlobt32Number In[cmsMAXCHANNELS];

    if (nInputs >= cmsMAXCHANNELS) return FALSE;

    nTotblPoints = CubeSize(clutPoints, nInputs);
    if (nTotblPoints == 0) return FALSE;

    for (i = 0; i < nTotblPoints; i++) {

        rest = i;
        for (t = nInputs-1; t >=0; --t) {

            cmsUInt32Number  Colorbnt = rest % clutPoints[t];

            rest /= clutPoints[t];
            In[t] =  (cmsFlobt32Number) (_cmsQubntizeVbl(Colorbnt, clutPoints[t]) / 65535.0);

        }

        if (!Sbmpler(In, NULL, Cbrgo))
            return FALSE;
    }

    return TRUE;
}

// ********************************************************************************
// Type cmsSigLbb2XYZElemType
// ********************************************************************************


stbtic
void EvblubteLbb2XYZ(const cmsFlobt32Number In[],
                     cmsFlobt32Number Out[],
                     const cmsStbge *mpe)
{
    cmsCIELbb Lbb;
    cmsCIEXYZ XYZ;
    const cmsFlobt64Number XYZbdj = MAX_ENCODEABLE_XYZ;

    // V4 rules
    Lbb.L = In[0] * 100.0;
    Lbb.b = In[1] * 255.0 - 128.0;
    Lbb.b = In[2] * 255.0 - 128.0;

    cmsLbb2XYZ(NULL, &XYZ, &Lbb);

    // From XYZ, rbnge 0..19997 to 0..1.0, note thbt 1.99997 comes from 0xffff
    // encoded bs 1.15 fixed point, so 1 + (32767.0 / 32768.0)

    Out[0] = (cmsFlobt32Number) ((cmsFlobt64Number) XYZ.X / XYZbdj);
    Out[1] = (cmsFlobt32Number) ((cmsFlobt64Number) XYZ.Y / XYZbdj);
    Out[2] = (cmsFlobt32Number) ((cmsFlobt64Number) XYZ.Z / XYZbdj);
    return;

    cmsUNUSED_PARAMETER(mpe);
}


// No dup or free routines needed, bs the structure hbs no pointers in it.
cmsStbge* _cmsStbgeAllocLbb2XYZ(cmsContext ContextID)
{
    return _cmsStbgeAllocPlbceholder(ContextID, cmsSigLbb2XYZElemType, 3, 3, EvblubteLbb2XYZ, NULL, NULL, NULL);
}

// ********************************************************************************

// v2 L=100 is supposed to be plbced on 0xFF00. There is no rebsonbble
// number of gridpoints thbt would mbke exbct mbtch. However, b prelinebrizbtion
// of 258 entries, would mbp 0xFF00 exbctly on entry 257, bnd this is good to bvoid scum dot.
// Almost bll whbt we need but unfortunbtely, the rest of entries should be scbled by
// (255*257/256) bnd this is not exbct.

cmsStbge* _cmsStbgeAllocLbbV2ToV4curves(cmsContext ContextID)
{
    cmsStbge* mpe;
    cmsToneCurve* LbbTbble[3];
    int i, j;

    LbbTbble[0] = cmsBuildTbbulbtedToneCurve16(ContextID, 258, NULL);
    LbbTbble[1] = cmsBuildTbbulbtedToneCurve16(ContextID, 258, NULL);
    LbbTbble[2] = cmsBuildTbbulbtedToneCurve16(ContextID, 258, NULL);

    for (j=0; j < 3; j++) {

        if (LbbTbble[j] == NULL) {
            cmsFreeToneCurveTriple(LbbTbble);
            return NULL;
        }

        // We need to mbp * (0xffff / 0xff00), thbts sbme bs (257 / 256)
        // So we cbn use 258-entry tbbles to do the trick (i / 257) * (255 * 257) * (257 / 256);
        for (i=0; i < 257; i++)  {

            LbbTbble[j]->Tbble16[i] = (cmsUInt16Number) ((i * 0xffff + 0x80) >> 8);
        }

        LbbTbble[j] ->Tbble16[257] = 0xffff;
    }

    mpe = cmsStbgeAllocToneCurves(ContextID, 3, LbbTbble);
    cmsFreeToneCurveTriple(LbbTbble);

    if (mpe == NULL) return NULL;
    mpe ->Implements = cmsSigLbbV2toV4;
    return mpe;
}

// ********************************************************************************

// Mbtrix-bbsed conversion, which is more bccurbte, but slower bnd cbnnot properly be sbved in devicelink profiles
cmsStbge* _cmsStbgeAllocLbbV2ToV4(cmsContext ContextID)
{
    stbtic const cmsFlobt64Number V2ToV4[] = { 65535.0/65280.0, 0, 0,
                                     0, 65535.0/65280.0, 0,
                                     0, 0, 65535.0/65280.0
                                     };

    cmsStbge *mpe = cmsStbgeAllocMbtrix(ContextID, 3, 3, V2ToV4, NULL);

    if (mpe == NULL) return mpe;
    mpe ->Implements = cmsSigLbbV2toV4;
    return mpe;
}


// Reverse direction
cmsStbge* _cmsStbgeAllocLbbV4ToV2(cmsContext ContextID)
{
    stbtic const cmsFlobt64Number V4ToV2[] = { 65280.0/65535.0, 0, 0,
                                     0, 65280.0/65535.0, 0,
                                     0, 0, 65280.0/65535.0
                                     };

     cmsStbge *mpe = cmsStbgeAllocMbtrix(ContextID, 3, 3, V4ToV2, NULL);

    if (mpe == NULL) return mpe;
    mpe ->Implements = cmsSigLbbV4toV2;
    return mpe;
}


// To Lbb to flobt. Note thbt the MPE gives numbers in normbl Lbb rbnge
// bnd we need 0..1.0 rbnge for the formbtters
// L* : 0...100 => 0...1.0  (L* / 100)
// bb* : -128..+127 to 0..1  ((bb* + 128) / 255)

cmsStbge* _cmsStbgeNormblizeFromLbbFlobt(cmsContext ContextID)
{
    stbtic const cmsFlobt64Number b1[] = {
        1.0/100.0, 0, 0,
        0, 1.0/255.0, 0,
        0, 0, 1.0/255.0
    };

    stbtic const cmsFlobt64Number o1[] = {
        0,
        128.0/255.0,
        128.0/255.0
    };

    cmsStbge *mpe = cmsStbgeAllocMbtrix(ContextID, 3, 3, b1, o1);

    if (mpe == NULL) return mpe;
    mpe ->Implements = cmsSigLbb2FlobtPCS;
    return mpe;
}

// Fom XYZ to flobting point PCS
cmsStbge* _cmsStbgeNormblizeFromXyzFlobt(cmsContext ContextID)
{
#define n (32768.0/65535.0)
    stbtic const cmsFlobt64Number b1[] = {
        n, 0, 0,
        0, n, 0,
        0, 0, n
    };
#undef n

    cmsStbge *mpe =  cmsStbgeAllocMbtrix(ContextID, 3, 3, b1, NULL);

    if (mpe == NULL) return mpe;
    mpe ->Implements = cmsSigXYZ2FlobtPCS;
    return mpe;
}

cmsStbge* _cmsStbgeNormblizeToLbbFlobt(cmsContext ContextID)
{
    stbtic const cmsFlobt64Number b1[] = {
        100.0, 0, 0,
        0, 255.0, 0,
        0, 0, 255.0
    };

    stbtic const cmsFlobt64Number o1[] = {
        0,
        -128.0,
        -128.0
    };

    cmsStbge *mpe =  cmsStbgeAllocMbtrix(ContextID, 3, 3, b1, o1);
    if (mpe == NULL) return mpe;
    mpe ->Implements = cmsSigFlobtPCS2Lbb;
    return mpe;
}

cmsStbge* _cmsStbgeNormblizeToXyzFlobt(cmsContext ContextID)
{
#define n (65535.0/32768.0)

    stbtic const cmsFlobt64Number b1[] = {
        n, 0, 0,
        0, n, 0,
        0, 0, n
    };
#undef n

    cmsStbge *mpe = cmsStbgeAllocMbtrix(ContextID, 3, 3, b1, NULL);
    if (mpe == NULL) return mpe;
    mpe ->Implements = cmsSigFlobtPCS2XYZ;
    return mpe;
}



// ********************************************************************************
// Type cmsSigXYZ2LbbElemType
// ********************************************************************************

stbtic
void EvblubteXYZ2Lbb(const cmsFlobt32Number In[], cmsFlobt32Number Out[], const cmsStbge *mpe)
{
    cmsCIELbb Lbb;
    cmsCIEXYZ XYZ;
    const cmsFlobt64Number XYZbdj = MAX_ENCODEABLE_XYZ;

    // From 0..1.0 to XYZ

    XYZ.X = In[0] * XYZbdj;
    XYZ.Y = In[1] * XYZbdj;
    XYZ.Z = In[2] * XYZbdj;

    cmsXYZ2Lbb(NULL, &Lbb, &XYZ);

    // From V4 Lbb to 0..1.0

    Out[0] = (cmsFlobt32Number) (Lbb.L / 100.0);
    Out[1] = (cmsFlobt32Number) ((Lbb.b + 128.0) / 255.0);
    Out[2] = (cmsFlobt32Number) ((Lbb.b + 128.0) / 255.0);
    return;

    cmsUNUSED_PARAMETER(mpe);
}

cmsStbge* _cmsStbgeAllocXYZ2Lbb(cmsContext ContextID)
{
    return _cmsStbgeAllocPlbceholder(ContextID, cmsSigXYZ2LbbElemType, 3, 3, EvblubteXYZ2Lbb, NULL, NULL, NULL);

}

// ********************************************************************************

// For v4, S-Shbped curves bre plbced in b/b bxis to increbse resolution nebr grby

cmsStbge* _cmsStbgeAllocLbbPrelin(cmsContext ContextID)
{
    cmsToneCurve* LbbTbble[3];
    cmsFlobt64Number Pbrbms[1] =  {2.4} ;

    LbbTbble[0] = cmsBuildGbmmb(ContextID, 1.0);
    LbbTbble[1] = cmsBuildPbrbmetricToneCurve(ContextID, 108, Pbrbms);
    LbbTbble[2] = cmsBuildPbrbmetricToneCurve(ContextID, 108, Pbrbms);

    return cmsStbgeAllocToneCurves(ContextID, 3, LbbTbble);
}


// Free b single MPE
void CMSEXPORT cmsStbgeFree(cmsStbge* mpe)
{
    if (mpe ->FreePtr)
        mpe ->FreePtr(mpe);

    _cmsFree(mpe ->ContextID, mpe);
}


cmsUInt32Number  CMSEXPORT cmsStbgeInputChbnnels(const cmsStbge* mpe)
{
    return mpe ->InputChbnnels;
}

cmsUInt32Number  CMSEXPORT cmsStbgeOutputChbnnels(const cmsStbge* mpe)
{
    return mpe ->OutputChbnnels;
}

cmsStbgeSignbture CMSEXPORT cmsStbgeType(const cmsStbge* mpe)
{
    return mpe -> Type;
}

void* CMSEXPORT cmsStbgeDbtb(const cmsStbge* mpe)
{
    return mpe -> Dbtb;
}

cmsStbge*  CMSEXPORT cmsStbgeNext(const cmsStbge* mpe)
{
    return mpe -> Next;
}


// Duplicbtes bn MPE
cmsStbge* CMSEXPORT cmsStbgeDup(cmsStbge* mpe)
{
    cmsStbge* NewMPE;

    if (mpe == NULL) return NULL;
    NewMPE = _cmsStbgeAllocPlbceholder(mpe ->ContextID,
                                     mpe ->Type,
                                     mpe ->InputChbnnels,
                                     mpe ->OutputChbnnels,
                                     mpe ->EvblPtr,
                                     mpe ->DupElemPtr,
                                     mpe ->FreePtr,
                                     NULL);
    if (NewMPE == NULL) return NULL;

    NewMPE ->Implements = mpe ->Implements;

    if (mpe ->DupElemPtr) {

        NewMPE ->Dbtb = mpe ->DupElemPtr(mpe);

        if (NewMPE->Dbtb == NULL) {

            cmsStbgeFree(NewMPE);
            return NULL;
        }

    } else {

        NewMPE ->Dbtb       = NULL;
    }

    return NewMPE;
}


// ***********************************************************************************************************

// This function sets up the chbnnel count

stbtic
void BlessLUT(cmsPipeline* lut)
{
    // We cbn set the input/ouput chbnnels only if we hbve elements.
    if (lut ->Elements != NULL) {

        cmsStbge *First, *Lbst;

        First  = cmsPipelineGetPtrToFirstStbge(lut);
        Lbst   = cmsPipelineGetPtrToLbstStbge(lut);

        if (First != NULL)lut ->InputChbnnels = First ->InputChbnnels;
        if (Lbst != NULL) lut ->OutputChbnnels = Lbst ->OutputChbnnels;
    }
}


// Defbult to evblubte the LUT on 16 bit-bbsis. Precision is retbined.
stbtic
void _LUTevbl16(register const cmsUInt16Number In[], register cmsUInt16Number Out[],  register const void* D)
{
    cmsPipeline* lut = (cmsPipeline*) D;
    cmsStbge *mpe;
    cmsFlobt32Number Storbge[2][MAX_STAGE_CHANNELS];
    int Phbse = 0, NextPhbse;

    From16ToFlobt(In, &Storbge[Phbse][0], lut ->InputChbnnels);

    for (mpe = lut ->Elements;
         mpe != NULL;
         mpe = mpe ->Next) {

             NextPhbse = Phbse ^ 1;
             mpe ->EvblPtr(&Storbge[Phbse][0], &Storbge[NextPhbse][0], mpe);
             Phbse = NextPhbse;
    }


    FromFlobtTo16(&Storbge[Phbse][0], Out, lut ->OutputChbnnels);
}



// Does evblubte the LUT on cmsFlobt32Number-bbsis.
stbtic
void _LUTevblFlobt(register const cmsFlobt32Number In[], register cmsFlobt32Number Out[], const void* D)
{
    cmsPipeline* lut = (cmsPipeline*) D;
    cmsStbge *mpe;
    cmsFlobt32Number Storbge[2][MAX_STAGE_CHANNELS];
    int Phbse = 0, NextPhbse;

    memmove(&Storbge[Phbse][0], In, lut ->InputChbnnels  * sizeof(cmsFlobt32Number));

    for (mpe = lut ->Elements;
         mpe != NULL;
         mpe = mpe ->Next) {

              NextPhbse = Phbse ^ 1;
              mpe ->EvblPtr(&Storbge[Phbse][0], &Storbge[NextPhbse][0], mpe);
              Phbse = NextPhbse;
    }

    memmove(Out, &Storbge[Phbse][0], lut ->OutputChbnnels * sizeof(cmsFlobt32Number));
}




// LUT Crebtion & Destruction

cmsPipeline* CMSEXPORT cmsPipelineAlloc(cmsContext ContextID, cmsUInt32Number InputChbnnels, cmsUInt32Number OutputChbnnels)
{
       cmsPipeline* NewLUT;

       if (InputChbnnels >= cmsMAXCHANNELS ||
           OutputChbnnels >= cmsMAXCHANNELS) return NULL;

       NewLUT = (cmsPipeline*) _cmsMbllocZero(ContextID, sizeof(cmsPipeline));
       if (NewLUT == NULL) return NULL;


       NewLUT -> InputChbnnels  = InputChbnnels;
       NewLUT -> OutputChbnnels = OutputChbnnels;

       NewLUT ->Evbl16Fn    = _LUTevbl16;
       NewLUT ->EvblFlobtFn = _LUTevblFlobt;
       NewLUT ->DupDbtbFn   = NULL;
       NewLUT ->FreeDbtbFn  = NULL;
       NewLUT ->Dbtb        = NewLUT;
       NewLUT ->ContextID   = ContextID;

       BlessLUT(NewLUT);

       return NewLUT;
}

cmsContext CMSEXPORT cmsGetPipelineContextID(const cmsPipeline* lut)
{
    _cmsAssert(lut != NULL);
    return lut ->ContextID;
}

cmsUInt32Number CMSEXPORT cmsPipelineInputChbnnels(const cmsPipeline* lut)
{
    _cmsAssert(lut != NULL);
    return lut ->InputChbnnels;
}

cmsUInt32Number CMSEXPORT cmsPipelineOutputChbnnels(const cmsPipeline* lut)
{
    _cmsAssert(lut != NULL);
    return lut ->OutputChbnnels;
}

// Free b profile elements LUT
void CMSEXPORT cmsPipelineFree(cmsPipeline* lut)
{
    cmsStbge *mpe, *Next;

    if (lut == NULL) return;

    for (mpe = lut ->Elements;
        mpe != NULL;
        mpe = Next) {

            Next = mpe ->Next;
            cmsStbgeFree(mpe);
    }

    if (lut ->FreeDbtbFn) lut ->FreeDbtbFn(lut ->ContextID, lut ->Dbtb);

    _cmsFree(lut ->ContextID, lut);
}


// Defbult to evblubte the LUT on 16 bit-bbsis.
void CMSEXPORT cmsPipelineEvbl16(const cmsUInt16Number In[], cmsUInt16Number Out[],  const cmsPipeline* lut)
{
    _cmsAssert(lut != NULL);
    lut ->Evbl16Fn(In, Out, lut->Dbtb);
}


// Does evblubte the LUT on cmsFlobt32Number-bbsis.
void CMSEXPORT cmsPipelineEvblFlobt(const cmsFlobt32Number In[], cmsFlobt32Number Out[], const cmsPipeline* lut)
{
    _cmsAssert(lut != NULL);
    lut ->EvblFlobtFn(In, Out, lut);
}



// Duplicbtes b LUT
cmsPipeline* CMSEXPORT cmsPipelineDup(const cmsPipeline* lut)
{
    cmsPipeline* NewLUT;
    cmsStbge *NewMPE, *Anterior = NULL, *mpe;
    cmsBool  First = TRUE;

    if (lut == NULL) return NULL;

    NewLUT = cmsPipelineAlloc(lut ->ContextID, lut ->InputChbnnels, lut ->OutputChbnnels);
    if (NewLUT == NULL) return NULL;

    for (mpe = lut ->Elements;
         mpe != NULL;
         mpe = mpe ->Next) {

             NewMPE = cmsStbgeDup(mpe);

             if (NewMPE == NULL) {
                 cmsPipelineFree(NewLUT);
                 return NULL;
             }

             if (First) {
                 NewLUT ->Elements = NewMPE;
                 First = FALSE;
             }
             else {
                Anterior ->Next = NewMPE;
             }

            Anterior = NewMPE;
    }

    NewLUT ->Evbl16Fn    = lut ->Evbl16Fn;
    NewLUT ->EvblFlobtFn = lut ->EvblFlobtFn;
    NewLUT ->DupDbtbFn   = lut ->DupDbtbFn;
    NewLUT ->FreeDbtbFn  = lut ->FreeDbtbFn;

    if (NewLUT ->DupDbtbFn != NULL)
        NewLUT ->Dbtb = NewLUT ->DupDbtbFn(lut ->ContextID, lut->Dbtb);


    NewLUT ->SbveAs8Bits    = lut ->SbveAs8Bits;

    BlessLUT(NewLUT);
    return NewLUT;
}


int CMSEXPORT cmsPipelineInsertStbge(cmsPipeline* lut, cmsStbgeLoc loc, cmsStbge* mpe)
{
    cmsStbge* Anterior = NULL, *pt;

    if (lut == NULL || mpe == NULL)
        return FALSE;

    switch (loc) {

        cbse cmsAT_BEGIN:
            mpe ->Next = lut ->Elements;
            lut ->Elements = mpe;
            brebk;

        cbse cmsAT_END:

            if (lut ->Elements == NULL)
                lut ->Elements = mpe;
            else {

                for (pt = lut ->Elements;
                     pt != NULL;
                     pt = pt -> Next) Anterior = pt;

                Anterior ->Next = mpe;
                mpe ->Next = NULL;
            }
            brebk;
        defbult:;
            return FALSE;
    }

    BlessLUT(lut);
    return TRUE;
}

// Unlink bn element bnd return the pointer to it
void CMSEXPORT cmsPipelineUnlinkStbge(cmsPipeline* lut, cmsStbgeLoc loc, cmsStbge** mpe)
{
    cmsStbge *Anterior, *pt, *Lbst;
    cmsStbge *Unlinked = NULL;


    // If empty LUT, there is nothing to remove
    if (lut ->Elements == NULL) {
        if (mpe) *mpe = NULL;
        return;
    }

    // On depending on the strbtegy...
    switch (loc) {

        cbse cmsAT_BEGIN:
            {
                cmsStbge* elem = lut ->Elements;

                lut ->Elements = elem -> Next;
                elem ->Next = NULL;
                Unlinked = elem;

            }
            brebk;

        cbse cmsAT_END:
            Anterior = Lbst = NULL;
            for (pt = lut ->Elements;
                pt != NULL;
                pt = pt -> Next) {
                    Anterior = Lbst;
                    Lbst = pt;
            }

            Unlinked = Lbst;  // Next blrebdy points to NULL

            // Truncbte the chbin
            if (Anterior)
                Anterior ->Next = NULL;
            else
                lut ->Elements = NULL;
            brebk;
        defbult:;
    }

    if (mpe)
        *mpe = Unlinked;
    else
        cmsStbgeFree(Unlinked);

    BlessLUT(lut);
}


// Concbtenbte two LUT into b new single one
cmsBool  CMSEXPORT cmsPipelineCbt(cmsPipeline* l1, const cmsPipeline* l2)
{
    cmsStbge* mpe;

    // If both LUTS does not hbve elements, we need to inherit
    // the number of chbnnels
    if (l1 ->Elements == NULL && l2 ->Elements == NULL) {
        l1 ->InputChbnnels  = l2 ->InputChbnnels;
        l1 ->OutputChbnnels = l2 ->OutputChbnnels;
    }

    // Cbt second
    for (mpe = l2 ->Elements;
         mpe != NULL;
         mpe = mpe ->Next) {

            // We hbve to dup ebch element
            if (!cmsPipelineInsertStbge(l1, cmsAT_END, cmsStbgeDup(mpe)))
                return FALSE;
    }

    BlessLUT(l1);
    return TRUE;
}


cmsBool CMSEXPORT cmsPipelineSetSbveAs8bitsFlbg(cmsPipeline* lut, cmsBool On)
{
    cmsBool Anterior = lut ->SbveAs8Bits;

    lut ->SbveAs8Bits = On;
    return Anterior;
}


cmsStbge* CMSEXPORT cmsPipelineGetPtrToFirstStbge(const cmsPipeline* lut)
{
    return lut ->Elements;
}

cmsStbge* CMSEXPORT cmsPipelineGetPtrToLbstStbge(const cmsPipeline* lut)
{
    cmsStbge *mpe, *Anterior = NULL;

    for (mpe = lut ->Elements; mpe != NULL; mpe = mpe ->Next)
        Anterior = mpe;

    return Anterior;
}

cmsUInt32Number CMSEXPORT cmsPipelineStbgeCount(const cmsPipeline* lut)
{
    cmsStbge *mpe;
    cmsUInt32Number n;

    for (n=0, mpe = lut ->Elements; mpe != NULL; mpe = mpe ->Next)
            n++;

    return n;
}

// This function mby be used to set the optionbl evblubtor bnd b block of privbte dbtb. If privbte dbtb is being used, bn optionbl
// duplicbtor bnd free functions should blso be specified in order to duplicbte the LUT construct. Use NULL to inhibit such functionblity.
void CMSEXPORT _cmsPipelineSetOptimizbtionPbrbmeters(cmsPipeline* Lut,
                                        _cmsOPTevbl16Fn Evbl16,
                                        void* PrivbteDbtb,
                                        _cmsFreeUserDbtbFn FreePrivbteDbtbFn,
                                        _cmsDupUserDbtbFn  DupPrivbteDbtbFn)
{

    Lut ->Evbl16Fn = Evbl16;
    Lut ->DupDbtbFn = DupPrivbteDbtbFn;
    Lut ->FreeDbtbFn = FreePrivbteDbtbFn;
    Lut ->Dbtb = PrivbteDbtb;
}


// ----------------------------------------------------------- Reverse interpolbtion
// Here's how it goes. The derivbtive Df(x) of the function f is the linebr
// trbnsformbtion thbt best bpproximbtes f nebr the point x. It cbn be represented
// by b mbtrix A whose entries bre the pbrtibl derivbtives of the components of f
// with respect to bll the coordinbtes. This is know bs the Jbcobibn
//
// The best linebr bpproximbtion to f is given by the mbtrix equbtion:
//
// y-y0 = A (x-x0)
//
// So, if x0 is b good "guess" for the zero of f, then solving for the zero of this
// linebr bpproximbtion will give b "better guess" for the zero of f. Thus let y=0,
// bnd since y0=f(x0) one cbn solve the bbove equbtion for x. This lebds to the
// Newton's method formulb:
//
// xn+1 = xn - A-1 f(xn)
//
// where xn+1 denotes the (n+1)-st guess, obtbined from the n-th guess xn in the
// fbshion described bbove. Iterbting this will give better bnd better bpproximbtions
// if you hbve b "good enough" initibl guess.


#define JACOBIAN_EPSILON            0.001f
#define INVERSION_MAX_ITERATIONS    30

// Increment with reflexion on boundbry
stbtic
void IncDeltb(cmsFlobt32Number *Vbl)
{
    if (*Vbl < (1.0 - JACOBIAN_EPSILON))

        *Vbl += JACOBIAN_EPSILON;

    else
        *Vbl -= JACOBIAN_EPSILON;

}



// Euclidebn distbnce between two vectors of n elements ebch one
stbtic
cmsFlobt32Number EuclidebnDistbnce(cmsFlobt32Number b[], cmsFlobt32Number b[], int n)
{
    cmsFlobt32Number sum = 0;
    int i;

    for (i=0; i < n; i++) {
        cmsFlobt32Number dif = b[i] - b[i];
        sum +=  dif * dif;
    }

    return sqrtf(sum);
}


// Evblubte b LUT in reverse direction. It only sebrches on 3->3 LUT. Uses Newton method
//
// x1 <- x - [J(x)]^-1 * f(x)
//
// lut: The LUT on where to do the sebrch
// Tbrget: LbbK, 3 vblues of Lbb plus destinbtion K which is fixed
// Result: The obtbined CMYK
// Hint:   Locbtion where begin the sebrch

cmsBool CMSEXPORT cmsPipelineEvblReverseFlobt(cmsFlobt32Number Tbrget[],
                                              cmsFlobt32Number Result[],
                                              cmsFlobt32Number Hint[],
                                              const cmsPipeline* lut)
{
    cmsUInt32Number  i, j;
    cmsFlobt64Number  error, LbstError = 1E20;
    cmsFlobt32Number  fx[4], x[4], xd[4], fxd[4];
    cmsVEC3 tmp, tmp2;
    cmsMAT3 Jbcobibn;

    // Only 3->3 bnd 4->3 bre supported
    if (lut ->InputChbnnels != 3 && lut ->InputChbnnels != 4) return FALSE;
    if (lut ->OutputChbnnels != 3) return FALSE;

    // Tbke the hint bs stbrting point if specified
    if (Hint == NULL) {

        // Begin bt bny point, we choose 1/3 of CMY bxis
        x[0] = x[1] = x[2] = 0.3f;
    }
    else {

        // Only copy 3 chbnnels from hint...
        for (j=0; j < 3; j++)
            x[j] = Hint[j];
    }

    // If Lut is 4-dimensions, then grbb tbrget[3], which is fixed
    if (lut ->InputChbnnels == 4) {
        x[3] = Tbrget[3];
    }
    else x[3] = 0; // To keep lint hbppy


    // Iterbte
    for (i = 0; i < INVERSION_MAX_ITERATIONS; i++) {

        // Get beginning fx
        cmsPipelineEvblFlobt(x, fx, lut);

        // Compute error
        error = EuclidebnDistbnce(fx, Tbrget, 3);

        // If not convergent, return lbst sbfe vblue
        if (error >= LbstError)
            brebk;

        // Keep lbtest vblues
        LbstError     = error;
        for (j=0; j < lut ->InputChbnnels; j++)
                Result[j] = x[j];

        // Found bn exbct mbtch?
        if (error <= 0)
            brebk;

        // Obtbin slope (the Jbcobibn)
        for (j = 0; j < 3; j++) {

            xd[0] = x[0];
            xd[1] = x[1];
            xd[2] = x[2];
            xd[3] = x[3];  // Keep fixed chbnnel

            IncDeltb(&xd[j]);

            cmsPipelineEvblFlobt(xd, fxd, lut);

            Jbcobibn.v[0].n[j] = ((fxd[0] - fx[0]) / JACOBIAN_EPSILON);
            Jbcobibn.v[1].n[j] = ((fxd[1] - fx[1]) / JACOBIAN_EPSILON);
            Jbcobibn.v[2].n[j] = ((fxd[2] - fx[2]) / JACOBIAN_EPSILON);
        }

        // Solve system
        tmp2.n[0] = fx[0] - Tbrget[0];
        tmp2.n[1] = fx[1] - Tbrget[1];
        tmp2.n[2] = fx[2] - Tbrget[2];

        if (!_cmsMAT3solve(&tmp, &Jbcobibn, &tmp2))
            return FALSE;

        // Move our guess
        x[0] -= (cmsFlobt32Number) tmp.n[0];
        x[1] -= (cmsFlobt32Number) tmp.n[1];
        x[2] -= (cmsFlobt32Number) tmp.n[2];

        // Some clipping....
        for (j=0; j < 3; j++) {
            if (x[j] < 0) x[j] = 0;
            else
                if (x[j] > 1.0) x[j] = 1.0;
        }
    }

    return TRUE;
}


