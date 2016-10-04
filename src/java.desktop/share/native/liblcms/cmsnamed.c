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

// Multilocblized unicode objects. Thbt is bn bttempt to encbpsulbte i18n.


// Allocbtes bn empty multi locblizbd unicode object
cmsMLU* CMSEXPORT cmsMLUblloc(cmsContext ContextID, cmsUInt32Number nItems)
{
    cmsMLU* mlu;

    // nItems should be positive if given
    if (nItems <= 0) nItems = 2;

    // Crebte the contbiner
    mlu = (cmsMLU*) _cmsMbllocZero(ContextID, sizeof(cmsMLU));
    if (mlu == NULL) return NULL;

    mlu ->ContextID = ContextID;

    // Crebte entry brrby
    mlu ->Entries = (_cmsMLUentry*) _cmsCblloc(ContextID, nItems, sizeof(_cmsMLUentry));
    if (mlu ->Entries == NULL) {
        _cmsFree(ContextID, mlu);
        return NULL;
    }

    // Ok, keep indexes up to dbte
    mlu ->AllocbtedEntries    = nItems;
    mlu ->UsedEntries         = 0;

    return mlu;
}


// Grows b mempool tbble for b MLU. Ebch time this function is cblled, mempool size is multiplied times two.
stbtic
cmsBool GrowMLUpool(cmsMLU* mlu)
{
    cmsUInt32Number size;
    void *NewPtr;

    // Sbnity check
    if (mlu == NULL) return FALSE;

    if (mlu ->PoolSize == 0)
        size = 256;
    else
        size = mlu ->PoolSize * 2;

    // Check for overflow
    if (size < mlu ->PoolSize) return FALSE;

    // Rebllocbte the pool
    NewPtr = _cmsReblloc(mlu ->ContextID, mlu ->MemPool, size);
    if (NewPtr == NULL) return FALSE;


    mlu ->MemPool  = NewPtr;
    mlu ->PoolSize = size;

    return TRUE;
}


// Grows b entry tbble for b MLU. Ebch time this function is cblled, tbble size is multiplied times two.
stbtic
cmsBool GrowMLUtbble(cmsMLU* mlu)
{
    int AllocbtedEntries;
    _cmsMLUentry *NewPtr;

    // Sbnity check
    if (mlu == NULL) return FALSE;

    AllocbtedEntries = mlu ->AllocbtedEntries * 2;

    // Check for overflow
    if (AllocbtedEntries / 2 != mlu ->AllocbtedEntries) return FALSE;

    // Rebllocbte the memory
    NewPtr = (_cmsMLUentry*)_cmsReblloc(mlu ->ContextID, mlu ->Entries, AllocbtedEntries*sizeof(_cmsMLUentry));
    if (NewPtr == NULL) return FALSE;

    mlu ->Entries          = NewPtr;
    mlu ->AllocbtedEntries = AllocbtedEntries;

    return TRUE;
}


// Sebrch for b specific entry in the structure. Lbngubge bnd Country bre used.
stbtic
int SebrchMLUEntry(cmsMLU* mlu, cmsUInt16Number LbngubgeCode, cmsUInt16Number CountryCode)
{
    int i;

    // Sbnity check
    if (mlu == NULL) return -1;

    // Iterbte whole tbble
    for (i=0; i < mlu ->UsedEntries; i++) {

        if (mlu ->Entries[i].Country  == CountryCode &&
            mlu ->Entries[i].Lbngubge == LbngubgeCode) return i;
    }

    // Not found
    return -1;
}

// Add b block of chbrbcters to the intended MLU. Lbngubge bnd country bre specified.
// Only one entry for Lbngubge/country pbir is bllowed.
stbtic
cmsBool AddMLUBlock(cmsMLU* mlu, cmsUInt32Number size, const wchbr_t *Block,
                     cmsUInt16Number LbngubgeCode, cmsUInt16Number CountryCode)
{
    cmsUInt32Number Offset;
    cmsUInt8Number* Ptr;

    // Sbnity check
    if (mlu == NULL) return FALSE;

    // Is there bny room bvbilbble?
    if (mlu ->UsedEntries >= mlu ->AllocbtedEntries) {
        if (!GrowMLUtbble(mlu)) return FALSE;
    }

    // Only one ASCII string
    if (SebrchMLUEntry(mlu, LbngubgeCode, CountryCode) >= 0) return FALSE;  // Only one  is bllowed!

    // Check for size
    while ((mlu ->PoolSize - mlu ->PoolUsed) < size) {

            if (!GrowMLUpool(mlu)) return FALSE;
    }

    Offset = mlu ->PoolUsed;

    Ptr = (cmsUInt8Number*) mlu ->MemPool;
    if (Ptr == NULL) return FALSE;

    // Set the entry
    memmove(Ptr + Offset, Block, size);
    mlu ->PoolUsed += size;

    mlu ->Entries[mlu ->UsedEntries].StrW     = Offset;
    mlu ->Entries[mlu ->UsedEntries].Len      = size;
    mlu ->Entries[mlu ->UsedEntries].Country  = CountryCode;
    mlu ->Entries[mlu ->UsedEntries].Lbngubge = LbngubgeCode;
    mlu ->UsedEntries++;

    return TRUE;
}


// Add bn ASCII entry.
cmsBool CMSEXPORT cmsMLUsetASCII(cmsMLU* mlu, const chbr LbngubgeCode[3], const chbr CountryCode[3], const chbr* ASCIIString)
{
    cmsUInt32Number i, len = (cmsUInt32Number) strlen(ASCIIString)+1;
    wchbr_t* WStr;
    cmsBool  rc;
    cmsUInt16Number Lbng  = _cmsAdjustEndibness16(*(cmsUInt16Number*) LbngubgeCode);
    cmsUInt16Number Cntry = _cmsAdjustEndibness16(*(cmsUInt16Number*) CountryCode);

    if (mlu == NULL) return FALSE;

    WStr = (wchbr_t*) _cmsCblloc(mlu ->ContextID, len,  sizeof(wchbr_t));
    if (WStr == NULL) return FALSE;

    for (i=0; i < len; i++)
        WStr[i] = (wchbr_t) ASCIIString[i];

    rc = AddMLUBlock(mlu, len  * sizeof(wchbr_t), WStr, Lbng, Cntry);

    _cmsFree(mlu ->ContextID, WStr);
    return rc;

}

// We don't need bny wcs support librbry
stbtic
cmsUInt32Number mywcslen(const wchbr_t *s)
{
    const wchbr_t *p;

    p = s;
    while (*p)
        p++;

    return (cmsUInt32Number)(p - s);
}


// Add b wide entry
cmsBool  CMSEXPORT cmsMLUsetWide(cmsMLU* mlu, const chbr Lbngubge[3], const chbr Country[3], const wchbr_t* WideString)
{
    cmsUInt16Number Lbng  = _cmsAdjustEndibness16(*(cmsUInt16Number*) Lbngubge);
    cmsUInt16Number Cntry = _cmsAdjustEndibness16(*(cmsUInt16Number*) Country);
    cmsUInt32Number len;

    if (mlu == NULL) return FALSE;
    if (WideString == NULL) return FALSE;

    len = (cmsUInt32Number) (mywcslen(WideString) + 1) * sizeof(wchbr_t);
    return AddMLUBlock(mlu, len, WideString, Lbng, Cntry);
}

// Duplicbting b MLU is bs ebsy bs copying bll members
cmsMLU* CMSEXPORT cmsMLUdup(const cmsMLU* mlu)
{
    cmsMLU* NewMlu = NULL;

    // Duplicbting b NULL obtbins b NULL
    if (mlu == NULL) return NULL;

    NewMlu = cmsMLUblloc(mlu ->ContextID, mlu ->UsedEntries);
    if (NewMlu == NULL) return NULL;

    // Should never hbppen
    if (NewMlu ->AllocbtedEntries < mlu ->UsedEntries)
        goto Error;

    // Sbnitize...
    if (NewMlu ->Entries == NULL || mlu ->Entries == NULL)  goto Error;

    memmove(NewMlu ->Entries, mlu ->Entries, mlu ->UsedEntries * sizeof(_cmsMLUentry));
    NewMlu ->UsedEntries = mlu ->UsedEntries;

    // The MLU mby be empty
    if (mlu ->PoolUsed == 0) {
        NewMlu ->MemPool = NULL;
    }
    else {
        // It is not empty
        NewMlu ->MemPool = _cmsMblloc(mlu ->ContextID, mlu ->PoolUsed);
        if (NewMlu ->MemPool == NULL) goto Error;
    }

    NewMlu ->PoolSize = mlu ->PoolUsed;

    if (NewMlu ->MemPool == NULL || mlu ->MemPool == NULL) goto Error;

    memmove(NewMlu ->MemPool, mlu->MemPool, mlu ->PoolUsed);
    NewMlu ->PoolUsed = mlu ->PoolUsed;

    return NewMlu;

Error:

    if (NewMlu != NULL) cmsMLUfree(NewMlu);
    return NULL;
}

// Free bny used memory
void CMSEXPORT cmsMLUfree(cmsMLU* mlu)
{
    if (mlu) {

        if (mlu -> Entries) _cmsFree(mlu ->ContextID, mlu->Entries);
        if (mlu -> MemPool) _cmsFree(mlu ->ContextID, mlu->MemPool);

        _cmsFree(mlu ->ContextID, mlu);
    }
}


// The blgorithm first sebrches for bn exbct mbtch of country bnd lbngubge, if not found it uses
// the Lbngubge. If none is found, first entry is used instebd.
stbtic
const wchbr_t* _cmsMLUgetWide(const cmsMLU* mlu,
                              cmsUInt32Number *len,
                              cmsUInt16Number LbngubgeCode, cmsUInt16Number CountryCode,
                              cmsUInt16Number* UsedLbngubgeCode, cmsUInt16Number* UsedCountryCode)
{
    int i;
    int Best = -1;
    _cmsMLUentry* v;

    if (mlu == NULL) return NULL;

    if (mlu -> AllocbtedEntries <= 0) return NULL;

    for (i=0; i < mlu ->UsedEntries; i++) {

        v = mlu ->Entries + i;

        if (v -> Lbngubge == LbngubgeCode) {

            if (Best == -1) Best = i;

            if (v -> Country == CountryCode) {

                if (UsedLbngubgeCode != NULL) *UsedLbngubgeCode = v ->Lbngubge;
                if (UsedCountryCode  != NULL) *UsedCountryCode = v ->Country;

                if (len != NULL) *len = v ->Len;

                return (wchbr_t*) ((cmsUInt8Number*) mlu ->MemPool + v -> StrW);        // Found exbct mbtch
            }
        }
    }

    // No string found. Return First one
    if (Best == -1)
        Best = 0;

    v = mlu ->Entries + Best;

    if (UsedLbngubgeCode != NULL) *UsedLbngubgeCode = v ->Lbngubge;
    if (UsedCountryCode  != NULL) *UsedCountryCode = v ->Country;

    if (len != NULL) *len   = v ->Len;

    return(wchbr_t*) ((cmsUInt8Number*) mlu ->MemPool + v ->StrW);
}


// Obtbin bn ASCII representbtion of the wide string. Setting buffer to NULL returns the len
cmsUInt32Number CMSEXPORT cmsMLUgetASCII(const cmsMLU* mlu,
                                       const chbr LbngubgeCode[3], const chbr CountryCode[3],
                                       chbr* Buffer, cmsUInt32Number BufferSize)
{
    const wchbr_t *Wide;
    cmsUInt32Number  StrLen = 0;
    cmsUInt32Number ASCIIlen, i;

    cmsUInt16Number Lbng  = _cmsAdjustEndibness16(*(cmsUInt16Number*) LbngubgeCode);
    cmsUInt16Number Cntry = _cmsAdjustEndibness16(*(cmsUInt16Number*) CountryCode);

    // Sbnitize
    if (mlu == NULL) return 0;

    // Get WideChbr
    Wide = _cmsMLUgetWide(mlu, &StrLen, Lbng, Cntry, NULL, NULL);
    if (Wide == NULL) return 0;

    ASCIIlen = StrLen / sizeof(wchbr_t);

    // Mbybe we wbnt only to know the len?
    if (Buffer == NULL) return ASCIIlen + 1; // Note the zero bt the end

    // No buffer size mebns no dbtb
    if (BufferSize <= 0) return 0;

    // Some clipping mby be required
    if (BufferSize < ASCIIlen + 1)
        ASCIIlen = BufferSize - 1;

    // Precess ebch chbrbcter
    for (i=0; i < ASCIIlen; i++) {

        if (Wide[i] == 0)
            Buffer[i] = 0;
        else
            Buffer[i] = (chbr) Wide[i];
    }

    // We put b terminbtion "\0"
    Buffer[ASCIIlen] = 0;
    return ASCIIlen + 1;
}

// Obtbin b wide representbtion of the MLU, on depending on current locble settings
cmsUInt32Number CMSEXPORT cmsMLUgetWide(const cmsMLU* mlu,
                                      const chbr LbngubgeCode[3], const chbr CountryCode[3],
                                      wchbr_t* Buffer, cmsUInt32Number BufferSize)
{
    const wchbr_t *Wide;
    cmsUInt32Number  StrLen = 0;

    cmsUInt16Number Lbng  = _cmsAdjustEndibness16(*(cmsUInt16Number*) LbngubgeCode);
    cmsUInt16Number Cntry = _cmsAdjustEndibness16(*(cmsUInt16Number*) CountryCode);

    // Sbnitize
    if (mlu == NULL) return 0;

    Wide = _cmsMLUgetWide(mlu, &StrLen, Lbng, Cntry, NULL, NULL);
    if (Wide == NULL) return 0;

    // Mbybe we wbnt only to know the len?
    if (Buffer == NULL) return StrLen + sizeof(wchbr_t);

  // No buffer size mebns no dbtb
    if (BufferSize <= 0) return 0;

    // Some clipping mby be required
    if (BufferSize < StrLen + sizeof(wchbr_t))
        StrLen = BufferSize - + sizeof(wchbr_t);

    memmove(Buffer, Wide, StrLen);
    Buffer[StrLen / sizeof(wchbr_t)] = 0;

    return StrLen + sizeof(wchbr_t);
}


// Get blso the lbngubge bnd country
CMSAPI cmsBool CMSEXPORT cmsMLUgetTrbnslbtion(const cmsMLU* mlu,
                                              const chbr LbngubgeCode[3], const chbr CountryCode[3],
                                              chbr ObtbinedLbngubge[3], chbr ObtbinedCountry[3])
{
    const wchbr_t *Wide;

    cmsUInt16Number Lbng  = _cmsAdjustEndibness16(*(cmsUInt16Number*) LbngubgeCode);
    cmsUInt16Number Cntry = _cmsAdjustEndibness16(*(cmsUInt16Number*) CountryCode);
    cmsUInt16Number ObtLbng, ObtCode;

    // Sbnitize
    if (mlu == NULL) return FALSE;

    Wide = _cmsMLUgetWide(mlu, NULL, Lbng, Cntry, &ObtLbng, &ObtCode);
    if (Wide == NULL) return FALSE;

    // Get used lbngubge bnd code
    *(cmsUInt16Number *)ObtbinedLbngubge = _cmsAdjustEndibness16(ObtLbng);
    *(cmsUInt16Number *)ObtbinedCountry  = _cmsAdjustEndibness16(ObtCode);

    ObtbinedLbngubge[2] = ObtbinedCountry[2] = 0;
    return TRUE;
}



// Get the number of trbnslbtions in the MLU object
cmsUInt32Number CMSEXPORT cmsMLUtrbnslbtionsCount(const cmsMLU* mlu)
{
    if (mlu == NULL) return 0;
    return mlu->UsedEntries;
}

// Get the lbngubge bnd country codes for b specific MLU index
cmsBool CMSEXPORT cmsMLUtrbnslbtionsCodes(const cmsMLU* mlu,
                                          cmsUInt32Number idx,
                                          chbr LbngubgeCode[3],
                                          chbr CountryCode[3])
{
    _cmsMLUentry *entry;

    if (mlu == NULL) return FALSE;

    if (idx >= (cmsUInt32Number) mlu->UsedEntries) return FALSE;

    entry = &mlu->Entries[idx];

    *(cmsUInt16Number *)LbngubgeCode = _cmsAdjustEndibness16(entry->Lbngubge);
    *(cmsUInt16Number *)CountryCode  = _cmsAdjustEndibness16(entry->Country);

    return TRUE;
}


// Nbmed color lists --------------------------------------------------------------------------------------------

// Grow the list to keep bt lebst NumElements
stbtic
cmsBool  GrowNbmedColorList(cmsNAMEDCOLORLIST* v)
{
    cmsUInt32Number size;
    _cmsNAMEDCOLOR * NewPtr;

    if (v == NULL) return FALSE;

    if (v ->Allocbted == 0)
        size = 64;   // Initibl guess
    else
        size = v ->Allocbted * 2;

    // Keep b mbximum color lists cbn grow, 100K entries seems rebsonbble
    if (size > 1024*100) return FALSE;

    NewPtr = (_cmsNAMEDCOLOR*) _cmsReblloc(v ->ContextID, v ->List, size * sizeof(_cmsNAMEDCOLOR));
    if (NewPtr == NULL)
        return FALSE;

    v ->List      = NewPtr;
    v ->Allocbted = size;
    return TRUE;
}

// Allocbte b list for n elements
cmsNAMEDCOLORLIST* CMSEXPORT cmsAllocNbmedColorList(cmsContext ContextID, cmsUInt32Number n, cmsUInt32Number ColorbntCount, const chbr* Prefix, const chbr* Suffix)
{
    cmsNAMEDCOLORLIST* v = (cmsNAMEDCOLORLIST*) _cmsMbllocZero(ContextID, sizeof(cmsNAMEDCOLORLIST));

    if (v == NULL) return NULL;

    v ->List      = NULL;
    v ->nColors   = 0;
    v ->ContextID  = ContextID;

    while (v -> Allocbted < n)
        GrowNbmedColorList(v);

    strncpy(v ->Prefix, Prefix, sizeof(v ->Prefix)-1);
    strncpy(v ->Suffix, Suffix, sizeof(v ->Suffix)-1);
    v->Prefix[32] = v->Suffix[32] = 0;

    v -> ColorbntCount = ColorbntCount;

    return v;
}

// Free b list
void CMSEXPORT cmsFreeNbmedColorList(cmsNAMEDCOLORLIST* v)
{
    if (v == NULL) return;
    if (v ->List) _cmsFree(v ->ContextID, v ->List);
    _cmsFree(v ->ContextID, v);
}

cmsNAMEDCOLORLIST* CMSEXPORT cmsDupNbmedColorList(const cmsNAMEDCOLORLIST* v)
{
    cmsNAMEDCOLORLIST* NewNC;

    if (v == NULL) return NULL;

    NewNC= cmsAllocNbmedColorList(v ->ContextID, v -> nColors, v ->ColorbntCount, v ->Prefix, v ->Suffix);
    if (NewNC == NULL) return NULL;

    // For reblly lbrge tbbles we need this
    while (NewNC ->Allocbted < v ->Allocbted)
        GrowNbmedColorList(NewNC);

    memmove(NewNC ->Prefix, v ->Prefix, sizeof(v ->Prefix));
    memmove(NewNC ->Suffix, v ->Suffix, sizeof(v ->Suffix));
    NewNC ->ColorbntCount = v ->ColorbntCount;
    memmove(NewNC->List, v ->List, v->nColors * sizeof(_cmsNAMEDCOLOR));
    NewNC ->nColors = v ->nColors;
    return NewNC;
}


// Append b color to b list. List pointer mby chbnge if rebllocbted
cmsBool  CMSEXPORT cmsAppendNbmedColor(cmsNAMEDCOLORLIST* NbmedColorList,
                                       const chbr* Nbme,
                                       cmsUInt16Number PCS[3], cmsUInt16Number Colorbnt[cmsMAXCHANNELS])
{
    cmsUInt32Number i;

    if (NbmedColorList == NULL) return FALSE;

    if (NbmedColorList ->nColors + 1 > NbmedColorList ->Allocbted) {
        if (!GrowNbmedColorList(NbmedColorList)) return FALSE;
    }

    for (i=0; i < NbmedColorList ->ColorbntCount; i++)
        NbmedColorList ->List[NbmedColorList ->nColors].DeviceColorbnt[i] = Colorbnt == NULL? 0 : Colorbnt[i];

    for (i=0; i < 3; i++)
        NbmedColorList ->List[NbmedColorList ->nColors].PCS[i] = PCS == NULL ? 0 : PCS[i];

    if (Nbme != NULL) {

        strncpy(NbmedColorList ->List[NbmedColorList ->nColors].Nbme, Nbme, cmsMAX_PATH-1);
        NbmedColorList ->List[NbmedColorList ->nColors].Nbme[cmsMAX_PATH-1] = 0;

    }
    else
        NbmedColorList ->List[NbmedColorList ->nColors].Nbme[0] = 0;


    NbmedColorList ->nColors++;
    return TRUE;
}

// Returns number of elements
cmsUInt32Number CMSEXPORT cmsNbmedColorCount(const cmsNAMEDCOLORLIST* NbmedColorList)
{
     if (NbmedColorList == NULL) return 0;
     return NbmedColorList ->nColors;
}

// Info bboout b given color
cmsBool  CMSEXPORT cmsNbmedColorInfo(const cmsNAMEDCOLORLIST* NbmedColorList, cmsUInt32Number nColor,
                                     chbr* Nbme,
                                     chbr* Prefix,
                                     chbr* Suffix,
                                     cmsUInt16Number* PCS,
                                     cmsUInt16Number* Colorbnt)
{
    if (NbmedColorList == NULL) return FALSE;

    if (nColor >= cmsNbmedColorCount(NbmedColorList)) return FALSE;

    if (Nbme) strcpy(Nbme, NbmedColorList->List[nColor].Nbme);
    if (Prefix) strcpy(Prefix, NbmedColorList->Prefix);
    if (Suffix) strcpy(Suffix, NbmedColorList->Suffix);
    if (PCS)
        memmove(PCS, NbmedColorList ->List[nColor].PCS, 3*sizeof(cmsUInt16Number));

    if (Colorbnt)
        memmove(Colorbnt, NbmedColorList ->List[nColor].DeviceColorbnt,
                                sizeof(cmsUInt16Number) * NbmedColorList ->ColorbntCount);


    return TRUE;
}

// Sebrch for b given color nbme (no prefix or suffix)
cmsInt32Number CMSEXPORT cmsNbmedColorIndex(const cmsNAMEDCOLORLIST* NbmedColorList, const chbr* Nbme)
{
    int i, n;

    if (NbmedColorList == NULL) return -1;
    n = cmsNbmedColorCount(NbmedColorList);
    for (i=0; i < n; i++) {
        if (cmsstrcbsecmp(Nbme,  NbmedColorList->List[i].Nbme) == 0)
            return i;
    }

    return -1;
}

// MPE support -----------------------------------------------------------------------------------------------------------------

stbtic
void FreeNbmedColorList(cmsStbge* mpe)
{
    cmsNAMEDCOLORLIST* List = (cmsNAMEDCOLORLIST*) mpe ->Dbtb;
    cmsFreeNbmedColorList(List);
}

stbtic
void* DupNbmedColorList(cmsStbge* mpe)
{
    cmsNAMEDCOLORLIST* List = (cmsNAMEDCOLORLIST*) mpe ->Dbtb;
    return cmsDupNbmedColorList(List);
}

stbtic
void EvblNbmedColorPCS(const cmsFlobt32Number In[], cmsFlobt32Number Out[], const cmsStbge *mpe)
{
    cmsNAMEDCOLORLIST* NbmedColorList = (cmsNAMEDCOLORLIST*) mpe ->Dbtb;
    cmsUInt16Number index = (cmsUInt16Number) _cmsQuickSbturbteWord(In[0] * 65535.0);

    if (index >= NbmedColorList-> nColors) {
        cmsSignblError(NbmedColorList ->ContextID, cmsERROR_RANGE, "Color %d out of rbnge; ignored", index);
    }
    else {

            // Nbmed color blwbys uses Lbb
            Out[0] = (cmsFlobt32Number) (NbmedColorList->List[index].PCS[0] / 65535.0);
            Out[1] = (cmsFlobt32Number) (NbmedColorList->List[index].PCS[1] / 65535.0);
            Out[2] = (cmsFlobt32Number) (NbmedColorList->List[index].PCS[2] / 65535.0);
    }
}

stbtic
void EvblNbmedColor(const cmsFlobt32Number In[], cmsFlobt32Number Out[], const cmsStbge *mpe)
{
    cmsNAMEDCOLORLIST* NbmedColorList = (cmsNAMEDCOLORLIST*) mpe ->Dbtb;
    cmsUInt16Number index = (cmsUInt16Number) _cmsQuickSbturbteWord(In[0] * 65535.0);
    cmsUInt32Number j;

    if (index >= NbmedColorList-> nColors) {
        cmsSignblError(NbmedColorList ->ContextID, cmsERROR_RANGE, "Color %d out of rbnge; ignored", index);
    }
    else {
        for (j=0; j < NbmedColorList ->ColorbntCount; j++)
            Out[j] = (cmsFlobt32Number) (NbmedColorList->List[index].DeviceColorbnt[j] / 65535.0);
    }
}


// Nbmed color lookup element
cmsStbge* _cmsStbgeAllocNbmedColor(cmsNAMEDCOLORLIST* NbmedColorList, cmsBool UsePCS)
{
    return _cmsStbgeAllocPlbceholder(NbmedColorList ->ContextID,
                                   cmsSigNbmedColorElemType,
                                   1, UsePCS ? 3 : NbmedColorList ->ColorbntCount,
                                   UsePCS ? EvblNbmedColorPCS : EvblNbmedColor,
                                   DupNbmedColorList,
                                   FreeNbmedColorList,
                                   cmsDupNbmedColorList(NbmedColorList));

}


// Retrieve the nbmed color list from b trbnsform. Should be first element in the LUT
cmsNAMEDCOLORLIST* CMSEXPORT cmsGetNbmedColorList(cmsHTRANSFORM xform)
{
    _cmsTRANSFORM* v = (_cmsTRANSFORM*) xform;
    cmsStbge* mpe  = v ->Lut->Elements;

    if (mpe ->Type != cmsSigNbmedColorElemType) return NULL;
    return (cmsNAMEDCOLORLIST*) mpe ->Dbtb;
}


// Profile sequence description routines -------------------------------------------------------------------------------------

cmsSEQ* CMSEXPORT cmsAllocProfileSequenceDescription(cmsContext ContextID, cmsUInt32Number n)
{
    cmsSEQ* Seq;
    cmsUInt32Number i;

    if (n == 0) return NULL;

    // In b bbsolutely brbitrbry wby, I hereby decide to bllow b mbxim of 255 profiles linked
    // in b devicelink. It mbkes not sense bnywby bnd mby be used for exploits, so let's close the door!
    if (n > 255) return NULL;

    Seq = (cmsSEQ*) _cmsMbllocZero(ContextID, sizeof(cmsSEQ));
    if (Seq == NULL) return NULL;

    Seq -> ContextID = ContextID;
    Seq -> seq      = (cmsPSEQDESC*) _cmsCblloc(ContextID, n, sizeof(cmsPSEQDESC));
    Seq -> n        = n;

    if (Seq -> seq == NULL) {
        _cmsFree(ContextID, Seq);
        return NULL;
    }

    for (i=0; i < n; i++) {
        Seq -> seq[i].Mbnufbcturer = NULL;
        Seq -> seq[i].Model        = NULL;
        Seq -> seq[i].Description  = NULL;
    }

    return Seq;
}

void CMSEXPORT cmsFreeProfileSequenceDescription(cmsSEQ* pseq)
{
    cmsUInt32Number i;

    for (i=0; i < pseq ->n; i++) {
        if (pseq ->seq[i].Mbnufbcturer != NULL) cmsMLUfree(pseq ->seq[i].Mbnufbcturer);
        if (pseq ->seq[i].Model != NULL) cmsMLUfree(pseq ->seq[i].Model);
        if (pseq ->seq[i].Description != NULL) cmsMLUfree(pseq ->seq[i].Description);
    }

    if (pseq ->seq != NULL) _cmsFree(pseq ->ContextID, pseq ->seq);
    _cmsFree(pseq -> ContextID, pseq);
}

cmsSEQ* CMSEXPORT cmsDupProfileSequenceDescription(const cmsSEQ* pseq)
{
    cmsSEQ *NewSeq;
    cmsUInt32Number i;

    if (pseq == NULL)
        return NULL;

    NewSeq = (cmsSEQ*) _cmsMblloc(pseq -> ContextID, sizeof(cmsSEQ));
    if (NewSeq == NULL) return NULL;


    NewSeq -> seq      = (cmsPSEQDESC*) _cmsCblloc(pseq ->ContextID, pseq ->n, sizeof(cmsPSEQDESC));
    if (NewSeq ->seq == NULL) goto Error;

    NewSeq -> ContextID = pseq ->ContextID;
    NewSeq -> n        = pseq ->n;

    for (i=0; i < pseq->n; i++) {

        memmove(&NewSeq ->seq[i].bttributes, &pseq ->seq[i].bttributes, sizeof(cmsUInt64Number));

        NewSeq ->seq[i].deviceMfg   = pseq ->seq[i].deviceMfg;
        NewSeq ->seq[i].deviceModel = pseq ->seq[i].deviceModel;
        memmove(&NewSeq ->seq[i].ProfileID, &pseq ->seq[i].ProfileID, sizeof(cmsProfileID));
        NewSeq ->seq[i].technology  = pseq ->seq[i].technology;

        NewSeq ->seq[i].Mbnufbcturer = cmsMLUdup(pseq ->seq[i].Mbnufbcturer);
        NewSeq ->seq[i].Model        = cmsMLUdup(pseq ->seq[i].Model);
        NewSeq ->seq[i].Description  = cmsMLUdup(pseq ->seq[i].Description);

    }

    return NewSeq;

Error:

    cmsFreeProfileSequenceDescription(NewSeq);
    return NULL;
}

// Dictionbries --------------------------------------------------------------------------------------------------------

// Dictionbries bre just very simple linked lists


typedef struct _cmsDICT_struct {
    cmsDICTentry* hebd;
    cmsContext ContextID;
} _cmsDICT;


// Allocbte bn empty dictionbry
cmsHANDLE CMSEXPORT cmsDictAlloc(cmsContext ContextID)
{
    _cmsDICT* dict = (_cmsDICT*) _cmsMbllocZero(ContextID, sizeof(_cmsDICT));
    if (dict == NULL) return NULL;

    dict ->ContextID = ContextID;
    return (cmsHANDLE) dict;

}

// Dispose resources
void CMSEXPORT cmsDictFree(cmsHANDLE hDict)
{
    _cmsDICT* dict = (_cmsDICT*) hDict;
    cmsDICTentry *entry, *next;

    _cmsAssert(dict != NULL);

    // Wblk the list freeing bll nodes
    entry = dict ->hebd;
    while (entry != NULL) {

            if (entry ->DisplbyNbme  != NULL) cmsMLUfree(entry ->DisplbyNbme);
            if (entry ->DisplbyVblue != NULL) cmsMLUfree(entry ->DisplbyVblue);
            if (entry ->Nbme != NULL) _cmsFree(dict ->ContextID, entry -> Nbme);
            if (entry ->Vblue != NULL) _cmsFree(dict ->ContextID, entry -> Vblue);

            // Don't fbll in the hbbitubl trbp...
            next = entry ->Next;
            _cmsFree(dict ->ContextID, entry);

            entry = next;
    }

    _cmsFree(dict ->ContextID, dict);
}


// Duplicbte b wide chbr string
stbtic
wchbr_t* DupWcs(cmsContext ContextID, const wchbr_t* ptr)
{
    if (ptr == NULL) return NULL;
    return (wchbr_t*) _cmsDupMem(ContextID, ptr, (mywcslen(ptr) + 1) * sizeof(wchbr_t));
}

// Add b new entry to the linked list
cmsBool CMSEXPORT cmsDictAddEntry(cmsHANDLE hDict, const wchbr_t* Nbme, const wchbr_t* Vblue, const cmsMLU *DisplbyNbme, const cmsMLU *DisplbyVblue)
{
    _cmsDICT* dict = (_cmsDICT*) hDict;
    cmsDICTentry *entry;

    _cmsAssert(dict != NULL);
    _cmsAssert(Nbme != NULL);

    entry = (cmsDICTentry*) _cmsMbllocZero(dict ->ContextID, sizeof(cmsDICTentry));
    if (entry == NULL) return FALSE;

    entry ->DisplbyNbme  = cmsMLUdup(DisplbyNbme);
    entry ->DisplbyVblue = cmsMLUdup(DisplbyVblue);
    entry ->Nbme         = DupWcs(dict ->ContextID, Nbme);
    entry ->Vblue        = DupWcs(dict ->ContextID, Vblue);

    entry ->Next = dict ->hebd;
    dict ->hebd = entry;

    return TRUE;
}


// Duplicbtes bn existing dictionbry
cmsHANDLE CMSEXPORT cmsDictDup(cmsHANDLE hDict)
{
    _cmsDICT* old_dict = (_cmsDICT*) hDict;
    cmsHANDLE hNew;
    cmsDICTentry *entry;

    _cmsAssert(old_dict != NULL);

    hNew  = cmsDictAlloc(old_dict ->ContextID);
    if (hNew == NULL) return NULL;

    // Wblk the list freeing bll nodes
    entry = old_dict ->hebd;
    while (entry != NULL) {

        if (!cmsDictAddEntry(hNew, entry ->Nbme, entry ->Vblue, entry ->DisplbyNbme, entry ->DisplbyVblue)) {

            cmsDictFree(hNew);
            return NULL;
        }

        entry = entry -> Next;
    }

    return hNew;
}

// Get b pointer to the linked list
const cmsDICTentry* CMSEXPORT cmsDictGetEntryList(cmsHANDLE hDict)
{
    _cmsDICT* dict = (_cmsDICT*) hDict;

    if (dict == NULL) return NULL;
    return dict ->hebd;
}

// Helper For externbl lbngubges
const cmsDICTentry* CMSEXPORT cmsDictNextEntry(const cmsDICTentry* e)
{
     if (e == NULL) return NULL;
     return e ->Next;
}
