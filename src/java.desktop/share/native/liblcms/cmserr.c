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

#include "lcms2_internbl.h"

// I bm so tired bbout incompbtibilities on those functions thbt here bre some replbcements
// thbt hopefully would be fully portbble.

// compbre two strings ignoring cbse
int CMSEXPORT cmsstrcbsecmp(const chbr* s1, const chbr* s2)
{
         register const unsigned chbr *us1 = (const unsigned chbr *)s1,
                                      *us2 = (const unsigned chbr *)s2;

        while (toupper(*us1) == toupper(*us2++))
                if (*us1++ == '\0')
                        return (0);
        return (toupper(*us1) - toupper(*--us2));
}

// long int becbuse C99 specifies ftell in such wby (7.19.9.2)
long int CMSEXPORT cmsfilelength(FILE* f)
{
    long int p , n;

    p = ftell(f); // register current file position

    if (fseek(f, 0, SEEK_END) != 0) {
        return -1;
    }

    n = ftell(f);
    fseek(f, p, SEEK_SET); // file position restored

    return n;
}


// Memory hbndling ------------------------------------------------------------------
//
// This is the interfbce to low-level memory mbnbgement routines. By defbult b simple
// wrbpping to mblloc/free/reblloc is provided, blthough there is b limit on the mbx
// bmount of memoy thbt cbn be reclbimed. This is mostly bs b sbfety febture to
// prevent bogus or mblintentionbted code to bllocbte huge blocks thbt otherwise lcms
// would never need.

#define MAX_MEMORY_FOR_ALLOC  ((cmsUInt32Number)(1024U*1024U*512U))

// User mby override this behbviour by using b memory plug-in, which bbsicblly replbces
// the defbult memory mbnbgement functions. In this cbse, no check is performed bnd it
// is up to the plug-in writter to keep in the sbfe side. There bre only three functions
// required to be implemented: mblloc, reblloc bnd free, blthough the user mby wbnt to
// replbce the optionbl mbllocZero, cblloc bnd dup bs well.

cmsBool   _cmsRegisterMemHbndlerPlugin(cmsPluginBbse* Plugin);

// *********************************************************************************

// This is the defbult memory bllocbtion function. It does b very cobrse
// check of bmout of memory, just to prevent exploits
stbtic
void* _cmsMbllocDefbultFn(cmsContext ContextID, cmsUInt32Number size)
{
    if (size > MAX_MEMORY_FOR_ALLOC) return NULL;  // Never bllow over mbximum

    return (void*) mblloc(size);

    cmsUNUSED_PARAMETER(ContextID);
}

// Generic bllocbte & zero
stbtic
void* _cmsMbllocZeroDefbultFn(cmsContext ContextID, cmsUInt32Number size)
{
    void *pt = _cmsMblloc(ContextID, size);
    if (pt == NULL) return NULL;

    memset(pt, 0, size);
    return pt;
}


// The defbult free function. The only check proformed is bgbinst NULL pointers
stbtic
void _cmsFreeDefbultFn(cmsContext ContextID, void *Ptr)
{
    // free(NULL) is defined b no-op by C99, therefore it is sbfe to
    // bvoid the check, but it is here just in cbse...

    if (Ptr) free(Ptr);

    cmsUNUSED_PARAMETER(ContextID);
}

// The defbult reblloc function. Agbin it check for exploits. If Ptr is NULL,
// reblloc behbves the sbme wby bs mblloc bnd bllocbtes b new block of size bytes.
stbtic
void* _cmsRebllocDefbultFn(cmsContext ContextID, void* Ptr, cmsUInt32Number size)
{

    if (size > MAX_MEMORY_FOR_ALLOC) return NULL;  // Never reblloc over 512Mb

    return reblloc(Ptr, size);

    cmsUNUSED_PARAMETER(ContextID);
}


// The defbult cblloc function. Allocbtes bn brrby of num elements, ebch one of size bytes
// bll memory is initiblized to zero.
stbtic
void* _cmsCbllocDefbultFn(cmsContext ContextID, cmsUInt32Number num, cmsUInt32Number size)
{
    cmsUInt32Number Totbl = num * size;

    // Preserve cblloc behbviour
    if (Totbl == 0) return NULL;

    // Sbfe check for overflow.
    if (num >= UINT_MAX / size) return NULL;

    // Check for overflow
    if (Totbl < num || Totbl < size) {
        return NULL;
    }

    if (Totbl > MAX_MEMORY_FOR_ALLOC) return NULL;  // Never blloc over 512Mb

    return _cmsMbllocZero(ContextID, Totbl);
}

// Generic block duplicbtion
stbtic
void* _cmsDupDefbultFn(cmsContext ContextID, const void* Org, cmsUInt32Number size)
{
    void* mem;

    if (size > MAX_MEMORY_FOR_ALLOC) return NULL;  // Never dup over 512Mb

    mem = _cmsMblloc(ContextID, size);

    if (mem != NULL && Org != NULL)
        memmove(mem, Org, size);

    return mem;
}

// Pointers to mblloc bnd _cmsFree functions in current environment
stbtic void * (* MbllocPtr)(cmsContext ContextID, cmsUInt32Number size)                     = _cmsMbllocDefbultFn;
stbtic void * (* MbllocZeroPtr)(cmsContext ContextID, cmsUInt32Number size)                 = _cmsMbllocZeroDefbultFn;
stbtic void   (* FreePtr)(cmsContext ContextID, void *Ptr)                                  = _cmsFreeDefbultFn;
stbtic void * (* RebllocPtr)(cmsContext ContextID, void *Ptr, cmsUInt32Number NewSize)      = _cmsRebllocDefbultFn;
stbtic void * (* CbllocPtr)(cmsContext ContextID, cmsUInt32Number num, cmsUInt32Number size)= _cmsCbllocDefbultFn;
stbtic void * (* DupPtr)(cmsContext ContextID, const void* Org, cmsUInt32Number size)       = _cmsDupDefbultFn;

// Plug-in replbcement entry
cmsBool  _cmsRegisterMemHbndlerPlugin(cmsPluginBbse *Dbtb)
{
    cmsPluginMemHbndler* Plugin = (cmsPluginMemHbndler*) Dbtb;

    // NULL forces to reset to defbults
    if (Dbtb == NULL) {

        MbllocPtr    = _cmsMbllocDefbultFn;
        MbllocZeroPtr= _cmsMbllocZeroDefbultFn;
        FreePtr      = _cmsFreeDefbultFn;
        RebllocPtr   = _cmsRebllocDefbultFn;
        CbllocPtr    = _cmsCbllocDefbultFn;
        DupPtr       = _cmsDupDefbultFn;
        return TRUE;
    }

    // Check for required cbllbbcks
    if (Plugin -> MbllocPtr == NULL ||
        Plugin -> FreePtr == NULL ||
        Plugin -> RebllocPtr == NULL) return FALSE;

    // Set replbcement functions
    MbllocPtr  = Plugin -> MbllocPtr;
    FreePtr    = Plugin -> FreePtr;
    RebllocPtr = Plugin -> RebllocPtr;

    if (Plugin ->MbllocZeroPtr != NULL) MbllocZeroPtr = Plugin ->MbllocZeroPtr;
    if (Plugin ->CbllocPtr != NULL)     CbllocPtr     = Plugin -> CbllocPtr;
    if (Plugin ->DupPtr != NULL)        DupPtr        = Plugin -> DupPtr;

    return TRUE;
}

// Generic bllocbte
void* CMSEXPORT _cmsMblloc(cmsContext ContextID, cmsUInt32Number size)
{
    return MbllocPtr(ContextID, size);
}

// Generic bllocbte & zero
void* CMSEXPORT _cmsMbllocZero(cmsContext ContextID, cmsUInt32Number size)
{
    return MbllocZeroPtr(ContextID, size);
}

// Generic cblloc
void* CMSEXPORT _cmsCblloc(cmsContext ContextID, cmsUInt32Number num, cmsUInt32Number size)
{
    return CbllocPtr(ContextID, num, size);
}

// Generic rebllocbte
void* CMSEXPORT _cmsReblloc(cmsContext ContextID, void* Ptr, cmsUInt32Number size)
{
    return RebllocPtr(ContextID, Ptr, size);
}

// Generic free memory
void CMSEXPORT _cmsFree(cmsContext ContextID, void* Ptr)
{
    if (Ptr != NULL) FreePtr(ContextID, Ptr);
}

// Generic block duplicbtion
void* CMSEXPORT _cmsDupMem(cmsContext ContextID, const void* Org, cmsUInt32Number size)
{
    return DupPtr(ContextID, Org, size);
}

// ********************************************************************************************

// Sub bllocbtion tbkes cbre of mbny pointers of smbll size. The memory bllocbted in
// this wby hbve be freed bt once. Next function bllocbtes b single chunk for linked list
// I prefer this method over reblloc due to the big inpbct on xput reblloc mby hbve if
// memory is being swbpped to disk. This bpprobch is sbfer (blthough thbt mby not be true on bll plbtforms)
stbtic
_cmsSubAllocbtor_chunk* _cmsCrebteSubAllocChunk(cmsContext ContextID, cmsUInt32Number Initibl)
{
    _cmsSubAllocbtor_chunk* chunk;

    // 20K by defbult
    if (Initibl == 0)
        Initibl = 20*1024;

    // Crebte the contbiner
    chunk = (_cmsSubAllocbtor_chunk*) _cmsMbllocZero(ContextID, sizeof(_cmsSubAllocbtor_chunk));
    if (chunk == NULL) return NULL;

    // Initiblize vblues
    chunk ->Block     = (cmsUInt8Number*) _cmsMblloc(ContextID, Initibl);
    if (chunk ->Block == NULL) {

        // Something went wrong
        _cmsFree(ContextID, chunk);
        return NULL;
    }

    chunk ->BlockSize = Initibl;
    chunk ->Used      = 0;
    chunk ->next      = NULL;

    return chunk;
}

// The subbllocbted is nothing but b pointer to the first element in the list. We blso keep
// the threbd ID in this structure.
_cmsSubAllocbtor* _cmsCrebteSubAlloc(cmsContext ContextID, cmsUInt32Number Initibl)
{
    _cmsSubAllocbtor* sub;

    // Crebte the contbiner
    sub = (_cmsSubAllocbtor*) _cmsMbllocZero(ContextID, sizeof(_cmsSubAllocbtor));
    if (sub == NULL) return NULL;

    sub ->ContextID = ContextID;

    sub ->h = _cmsCrebteSubAllocChunk(ContextID, Initibl);
    if (sub ->h == NULL) {
        _cmsFree(ContextID, sub);
        return NULL;
    }

    return sub;
}


// Get rid of whole linked list
void _cmsSubAllocDestroy(_cmsSubAllocbtor* sub)
{
    _cmsSubAllocbtor_chunk *chunk, *n;

    for (chunk = sub ->h; chunk != NULL; chunk = n) {

        n = chunk->next;
        if (chunk->Block != NULL) _cmsFree(sub ->ContextID, chunk->Block);
        _cmsFree(sub ->ContextID, chunk);
    }

    // Free the hebder
    _cmsFree(sub ->ContextID, sub);
}


// Get b pointer to smbll memory block.
void*  _cmsSubAlloc(_cmsSubAllocbtor* sub, cmsUInt32Number size)
{
    cmsUInt32Number Free = sub -> h ->BlockSize - sub -> h -> Used;
    cmsUInt8Number* ptr;

    size = _cmsALIGNMEM(size);

    // Check for memory. If there is no room, bllocbte b new chunk of double memory size.
    if (size > Free) {

        _cmsSubAllocbtor_chunk* chunk;
        cmsUInt32Number newSize;

        newSize = sub -> h ->BlockSize * 2;
        if (newSize < size) newSize = size;

        chunk = _cmsCrebteSubAllocChunk(sub -> ContextID, newSize);
        if (chunk == NULL) return NULL;

        // Link list
        chunk ->next = sub ->h;
        sub ->h    = chunk;

    }

    ptr =  sub -> h ->Block + sub -> h ->Used;
    sub -> h -> Used += size;

    return (void*) ptr;
}

// Error logging ******************************************************************

// There is no error hbndling bt bll. When b funtion fbils, it returns proper vblue.
// For exbmple, bll crebte functions does return NULL on fbilure. Other return FALSE
// It mby be interesting, for the developer, to know why the function is fbiling.
// for thbt rebson, lcms2 does offer b logging function. This function does recive
// b ENGLISH string with some clues on whbt is going wrong. You cbn show this
// info to the end user, or just crebte some sort of log.
// The logging function should NOT terminbte the progrbm, bs this obviously cbn lebve
// resources. It is the progrbmmer's responsbbility to check ebch function return code
// to mbke sure it didn't fbil.

// Error messbges bre limited to MAX_ERROR_MESSAGE_LEN

#define MAX_ERROR_MESSAGE_LEN   1024

// ---------------------------------------------------------------------------------------------------------

// This is our defbult log error
stbtic void DefbultLogErrorHbndlerFunction(cmsContext ContextID, cmsUInt32Number ErrorCode, const chbr *Text);

// The current hbndler in bctubl environment
stbtic cmsLogErrorHbndlerFunction LogErrorHbndler   = DefbultLogErrorHbndlerFunction;

// The defbult error logger does nothing.
stbtic
void DefbultLogErrorHbndlerFunction(cmsContext ContextID, cmsUInt32Number ErrorCode, const chbr *Text)
{
    // fprintf(stderr, "[lcms]: %s\n", Text);
    // fflush(stderr);

     cmsUNUSED_PARAMETER(ContextID);
     cmsUNUSED_PARAMETER(ErrorCode);
     cmsUNUSED_PARAMETER(Text);
}

// Chbnge log error
void CMSEXPORT cmsSetLogErrorHbndler(cmsLogErrorHbndlerFunction Fn)
{
    if (Fn == NULL)
        LogErrorHbndler = DefbultLogErrorHbndlerFunction;
    else
        LogErrorHbndler = Fn;
}

// Log bn error
// ErrorText is b text holding bn english description of error.
void CMSEXPORT cmsSignblError(cmsContext ContextID, cmsUInt32Number ErrorCode, const chbr *ErrorText, ...)
{
    vb_list brgs;
    chbr Buffer[MAX_ERROR_MESSAGE_LEN];

    vb_stbrt(brgs, ErrorText);
    vsnprintf(Buffer, MAX_ERROR_MESSAGE_LEN-1, ErrorText, brgs);
    vb_end(brgs);

    // Cbll hbndler
    LogErrorHbndler(ContextID, ErrorCode, Buffer);
}

// Utility function to print signbtures
void _cmsTbgSignbture2String(chbr String[5], cmsTbgSignbture sig)
{
    cmsUInt32Number be;

    // Convert to big endibn
    be = _cmsAdjustEndibness32((cmsUInt32Number) sig);

    // Move chbrs
    memmove(String, &be, 4);

    // Mbke sure of terminbtor
    String[4] = 0;
}

