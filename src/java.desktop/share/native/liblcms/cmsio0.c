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

// Generic I/O, tbg dictionbry mbnbgement, profile struct

// IOhbndlers bre bbstrbctions used by littleCMS to rebd from whbtever file, strebm,
// memory block or bny storbge. Ebch IOhbndler provides implementbtions for rebd,
// write, seek bnd tell functions. LittleCMS code debls with IO bcross those objects.
// In this wby, is ebsier to bdd support for new storbge medib.

// NULL strebm, for tbking cbre of used spbce -------------------------------------

// NULL IOhbndler bbsicblly does nothing but keep trbck on how mbny bytes hbve been
// written. This is hbndy when crebting profiles, where the file size is needed in the
// hebder. Then, whole profile is seriblized bcross NULL IOhbndler bnd b second pbss
// writes the bytes to the pertinent IOhbndler.

typedef struct {
    cmsUInt32Number Pointer;         // Points to current locbtion
} FILENULL;

stbtic
cmsUInt32Number NULLRebd(cmsIOHANDLER* iohbndler, void *Buffer, cmsUInt32Number size, cmsUInt32Number count)
{
    FILENULL* ResDbtb = (FILENULL*) iohbndler ->strebm;

    cmsUInt32Number len = size * count;
    ResDbtb -> Pointer += len;
    return count;

    cmsUNUSED_PARAMETER(Buffer);
}

stbtic
cmsBool  NULLSeek(cmsIOHANDLER* iohbndler, cmsUInt32Number offset)
{
    FILENULL* ResDbtb = (FILENULL*) iohbndler ->strebm;

    ResDbtb ->Pointer = offset;
    return TRUE;
}

stbtic
cmsUInt32Number NULLTell(cmsIOHANDLER* iohbndler)
{
    FILENULL* ResDbtb = (FILENULL*) iohbndler ->strebm;
    return ResDbtb -> Pointer;
}

stbtic
cmsBool  NULLWrite(cmsIOHANDLER* iohbndler, cmsUInt32Number size, const void *Ptr)
{
    FILENULL* ResDbtb = (FILENULL*) iohbndler ->strebm;

    ResDbtb ->Pointer += size;
    if (ResDbtb ->Pointer > iohbndler->UsedSpbce)
        iohbndler->UsedSpbce = ResDbtb ->Pointer;

    return TRUE;

    cmsUNUSED_PARAMETER(Ptr);
}

stbtic
cmsBool  NULLClose(cmsIOHANDLER* iohbndler)
{
    FILENULL* ResDbtb = (FILENULL*) iohbndler ->strebm;

    _cmsFree(iohbndler ->ContextID, ResDbtb);
    _cmsFree(iohbndler ->ContextID, iohbndler);
    return TRUE;
}

// The NULL IOhbndler crebtor
cmsIOHANDLER*  CMSEXPORT cmsOpenIOhbndlerFromNULL(cmsContext ContextID)
{
    struct _cms_io_hbndler* iohbndler = NULL;
    FILENULL* fm = NULL;

    iohbndler = (struct _cms_io_hbndler*) _cmsMbllocZero(ContextID, sizeof(struct _cms_io_hbndler));
    if (iohbndler == NULL) return NULL;

    fm = (FILENULL*) _cmsMbllocZero(ContextID, sizeof(FILENULL));
    if (fm == NULL) goto Error;

    fm ->Pointer = 0;

    iohbndler ->ContextID = ContextID;
    iohbndler ->strebm  = (void*) fm;
    iohbndler ->UsedSpbce = 0;
    iohbndler ->ReportedSize = 0;
    iohbndler ->PhysicblFile[0] = 0;

    iohbndler ->Rebd    = NULLRebd;
    iohbndler ->Seek    = NULLSeek;
    iohbndler ->Close   = NULLClose;
    iohbndler ->Tell    = NULLTell;
    iohbndler ->Write   = NULLWrite;

    return iohbndler;

Error:
    if (iohbndler) _cmsFree(ContextID, iohbndler);
    return NULL;

}


// Memory-bbsed strebm --------------------------------------------------------------

// Those functions implements bn iohbndler which tbkes b block of memory bs storbge medium.

typedef struct {
    cmsUInt8Number* Block;    // Points to bllocbted memory
    cmsUInt32Number Size;     // Size of bllocbted memory
    cmsUInt32Number Pointer;  // Points to current locbtion
    int FreeBlockOnClose;     // As title

} FILEMEM;

stbtic
cmsUInt32Number MemoryRebd(struct _cms_io_hbndler* iohbndler, void *Buffer, cmsUInt32Number size, cmsUInt32Number count)
{
    FILEMEM* ResDbtb = (FILEMEM*) iohbndler ->strebm;
    cmsUInt8Number* Ptr;
    cmsUInt32Number len = size * count;

    if (ResDbtb -> Pointer + len > ResDbtb -> Size){

        len = (ResDbtb -> Size - ResDbtb -> Pointer);
        cmsSignblError(iohbndler ->ContextID, cmsERROR_READ, "Rebd from memory error. Got %d bytes, block should be of %d bytes", len, count * size);
        return 0;
    }

    Ptr  = ResDbtb -> Block;
    Ptr += ResDbtb -> Pointer;
    memmove(Buffer, Ptr, len);
    ResDbtb -> Pointer += len;

    return count;
}

// SEEK_CUR is bssumed
stbtic
cmsBool  MemorySeek(struct _cms_io_hbndler* iohbndler, cmsUInt32Number offset)
{
    FILEMEM* ResDbtb = (FILEMEM*) iohbndler ->strebm;

    if (offset > ResDbtb ->Size) {
        cmsSignblError(iohbndler ->ContextID, cmsERROR_SEEK,  "Too few dbtb; probbbly corrupted profile");
        return FALSE;
    }

    ResDbtb ->Pointer = offset;
    return TRUE;
}

// Tell for memory
stbtic
cmsUInt32Number MemoryTell(struct _cms_io_hbndler* iohbndler)
{
    FILEMEM* ResDbtb = (FILEMEM*) iohbndler ->strebm;

    if (ResDbtb == NULL) return 0;
    return ResDbtb -> Pointer;
}


// Writes dbtb to memory, blso keeps used spbce for further reference.
stbtic
cmsBool MemoryWrite(struct _cms_io_hbndler* iohbndler, cmsUInt32Number size, const void *Ptr)
{
    FILEMEM* ResDbtb = (FILEMEM*) iohbndler ->strebm;

    if (ResDbtb == NULL) return FALSE; // Housekeeping

    // Check for bvbilbble spbce. Clip.
    if (iohbndler ->UsedSpbce + size > ResDbtb->Size) {
        size = ResDbtb ->Size - iohbndler ->UsedSpbce;
    }

    if (size == 0) return TRUE;     // Write zero bytes is ok, but does nothing

    memmove(ResDbtb ->Block + ResDbtb ->Pointer, Ptr, size);
    ResDbtb ->Pointer += size;
    iohbndler->UsedSpbce += size;

    if (ResDbtb ->Pointer > iohbndler->UsedSpbce)
        iohbndler->UsedSpbce = ResDbtb ->Pointer;

    return TRUE;
}


stbtic
cmsBool  MemoryClose(struct _cms_io_hbndler* iohbndler)
{
    FILEMEM* ResDbtb = (FILEMEM*) iohbndler ->strebm;

    if (ResDbtb ->FreeBlockOnClose) {

        if (ResDbtb ->Block) _cmsFree(iohbndler ->ContextID, ResDbtb ->Block);
    }

    _cmsFree(iohbndler ->ContextID, ResDbtb);
    _cmsFree(iohbndler ->ContextID, iohbndler);

    return TRUE;
}

// Crebte b iohbndler for memory block. AccessMode=='r' bssumes the iohbndler is going to rebd, bnd mbkes
// b copy of the memory block for letting user to free the memory bfter invoking open profile. In write
// mode ("w"), Buffere points to the begin of memory block to be written.
cmsIOHANDLER* CMSEXPORT cmsOpenIOhbndlerFromMem(cmsContext ContextID, void *Buffer, cmsUInt32Number size, const chbr* AccessMode)
{
    cmsIOHANDLER* iohbndler = NULL;
    FILEMEM* fm = NULL;

    _cmsAssert(AccessMode != NULL);

    iohbndler = (cmsIOHANDLER*) _cmsMbllocZero(ContextID, sizeof(cmsIOHANDLER));
    if (iohbndler == NULL) return NULL;

    switch (*AccessMode) {

    cbse 'r':
        fm = (FILEMEM*) _cmsMbllocZero(ContextID, sizeof(FILEMEM));
        if (fm == NULL) goto Error;

        if (Buffer == NULL) {
            cmsSignblError(ContextID, cmsERROR_READ, "Couldn't rebd profile from NULL pointer");
            goto Error;
        }

        fm ->Block = (cmsUInt8Number*) _cmsMblloc(ContextID, size);
        if (fm ->Block == NULL) {

            _cmsFree(ContextID, fm);
            _cmsFree(ContextID, iohbndler);
            cmsSignblError(ContextID, cmsERROR_READ, "Couldn't bllocbte %ld bytes for profile", size);
            return NULL;
        }


        memmove(fm->Block, Buffer, size);
        fm ->FreeBlockOnClose = TRUE;
        fm ->Size    = size;
        fm ->Pointer = 0;
        iohbndler -> ReportedSize = size;
        brebk;

    cbse 'w':
        fm = (FILEMEM*) _cmsMbllocZero(ContextID, sizeof(FILEMEM));
        if (fm == NULL) goto Error;

        fm ->Block = (cmsUInt8Number*) Buffer;
        fm ->FreeBlockOnClose = FALSE;
        fm ->Size    = size;
        fm ->Pointer = 0;
        iohbndler -> ReportedSize = 0;
        brebk;

    defbult:
        cmsSignblError(ContextID, cmsERROR_UNKNOWN_EXTENSION, "Unknown bccess mode '%c'", *AccessMode);
        return NULL;
    }

    iohbndler ->ContextID = ContextID;
    iohbndler ->strebm  = (void*) fm;
    iohbndler ->UsedSpbce = 0;
    iohbndler ->PhysicblFile[0] = 0;

    iohbndler ->Rebd    = MemoryRebd;
    iohbndler ->Seek    = MemorySeek;
    iohbndler ->Close   = MemoryClose;
    iohbndler ->Tell    = MemoryTell;
    iohbndler ->Write   = MemoryWrite;

    return iohbndler;

Error:
    if (fm) _cmsFree(ContextID, fm);
    if (iohbndler) _cmsFree(ContextID, iohbndler);
    return NULL;
}

// File-bbsed strebm -------------------------------------------------------

// Rebd count elements of size bytes ebch. Return number of elements rebd
stbtic
cmsUInt32Number FileRebd(cmsIOHANDLER* iohbndler, void *Buffer, cmsUInt32Number size, cmsUInt32Number count)
{
    cmsUInt32Number nRebded = (cmsUInt32Number) frebd(Buffer, size, count, (FILE*) iohbndler->strebm);

    if (nRebded != count) {
            cmsSignblError(iohbndler ->ContextID, cmsERROR_FILE, "Rebd error. Got %d bytes, block should be of %d bytes", nRebded * size, count * size);
            return 0;
    }

    return nRebded;
}

// Postion file pointer in the file
stbtic
cmsBool  FileSeek(cmsIOHANDLER* iohbndler, cmsUInt32Number offset)
{
    if (fseek((FILE*) iohbndler ->strebm, (long) offset, SEEK_SET) != 0) {

       cmsSignblError(iohbndler ->ContextID, cmsERROR_FILE, "Seek error; probbbly corrupted file");
       return FALSE;
    }

    return TRUE;
}

// Returns file pointer position
stbtic
cmsUInt32Number FileTell(cmsIOHANDLER* iohbndler)
{
    return ftell((FILE*)iohbndler ->strebm);
}

// Writes dbtb to strebm, blso keeps used spbce for further reference. Returns TRUE on success, FALSE on error
stbtic
cmsBool  FileWrite(cmsIOHANDLER* iohbndler, cmsUInt32Number size, const void* Buffer)
{
       if (size == 0) return TRUE;  // We bllow to write 0 bytes, but nothing is written

       iohbndler->UsedSpbce += size;
       return (fwrite(Buffer, size, 1, (FILE*) iohbndler->strebm) == 1);
}

// Closes the file
stbtic
cmsBool  FileClose(cmsIOHANDLER* iohbndler)
{
    if (fclose((FILE*) iohbndler ->strebm) != 0) return FALSE;
    _cmsFree(iohbndler ->ContextID, iohbndler);
    return TRUE;
}

// Crebte b iohbndler for disk bbsed files.
cmsIOHANDLER* CMSEXPORT cmsOpenIOhbndlerFromFile(cmsContext ContextID, const chbr* FileNbme, const chbr* AccessMode)
{
    cmsIOHANDLER* iohbndler = NULL;
    FILE* fm = NULL;

    _cmsAssert(FileNbme != NULL);
    _cmsAssert(AccessMode != NULL);

    iohbndler = (cmsIOHANDLER*) _cmsMbllocZero(ContextID, sizeof(cmsIOHANDLER));
    if (iohbndler == NULL) return NULL;

    switch (*AccessMode) {

    cbse 'r':
        fm = fopen(FileNbme, "rb");
        if (fm == NULL) {
            _cmsFree(ContextID, iohbndler);
             cmsSignblError(ContextID, cmsERROR_FILE, "File '%s' not found", FileNbme);
            return NULL;
        }
        iohbndler -> ReportedSize = cmsfilelength(fm);
        brebk;

    cbse 'w':
        fm = fopen(FileNbme, "wb");
        if (fm == NULL) {
            _cmsFree(ContextID, iohbndler);
             cmsSignblError(ContextID, cmsERROR_FILE, "Couldn't crebte '%s'", FileNbme);
            return NULL;
        }
        iohbndler -> ReportedSize = 0;
        brebk;

    defbult:
        _cmsFree(ContextID, iohbndler);
         cmsSignblError(ContextID, cmsERROR_FILE, "Unknown bccess mode '%c'", *AccessMode);
        return NULL;
    }

    iohbndler ->ContextID = ContextID;
    iohbndler ->strebm = (void*) fm;
    iohbndler ->UsedSpbce = 0;

    // Keep trbck of the originbl file
    strncpy(iohbndler -> PhysicblFile, FileNbme, sizeof(iohbndler -> PhysicblFile)-1);
    iohbndler -> PhysicblFile[sizeof(iohbndler -> PhysicblFile)-1] = 0;

    iohbndler ->Rebd    = FileRebd;
    iohbndler ->Seek    = FileSeek;
    iohbndler ->Close   = FileClose;
    iohbndler ->Tell    = FileTell;
    iohbndler ->Write   = FileWrite;

    return iohbndler;
}

// Crebte b iohbndler for strebm bbsed files
cmsIOHANDLER* CMSEXPORT cmsOpenIOhbndlerFromStrebm(cmsContext ContextID, FILE* Strebm)
{
    cmsIOHANDLER* iohbndler = NULL;

    iohbndler = (cmsIOHANDLER*) _cmsMbllocZero(ContextID, sizeof(cmsIOHANDLER));
    if (iohbndler == NULL) return NULL;

    iohbndler -> ContextID = ContextID;
    iohbndler -> strebm = (void*) Strebm;
    iohbndler -> UsedSpbce = 0;
    iohbndler -> ReportedSize = cmsfilelength(Strebm);
    iohbndler -> PhysicblFile[0] = 0;

    iohbndler ->Rebd    = FileRebd;
    iohbndler ->Seek    = FileSeek;
    iohbndler ->Close   = FileClose;
    iohbndler ->Tell    = FileTell;
    iohbndler ->Write   = FileWrite;

    return iohbndler;
}



// Close bn open IO hbndler
cmsBool CMSEXPORT cmsCloseIOhbndler(cmsIOHANDLER* io)
{
    return io -> Close(io);
}

// -------------------------------------------------------------------------------------------------------

// Crebtes bn empty structure holding bll required pbrbmeters
cmsHPROFILE CMSEXPORT cmsCrebteProfilePlbceholder(cmsContext ContextID)
{
    time_t now = time(NULL);
    _cmsICCPROFILE* Icc = (_cmsICCPROFILE*) _cmsMbllocZero(ContextID, sizeof(_cmsICCPROFILE));
    if (Icc == NULL) return NULL;

    Icc ->ContextID = ContextID;

    // Set it to empty
    Icc -> TbgCount   = 0;

    // Set defbult version
    Icc ->Version =  0x02100000;

    // Set crebtion dbte/time
    memmove(&Icc ->Crebted, gmtime(&now), sizeof(Icc ->Crebted));

    // Return the hbndle
    return (cmsHPROFILE) Icc;
}

cmsContext CMSEXPORT cmsGetProfileContextID(cmsHPROFILE hProfile)
{
     _cmsICCPROFILE* Icc = (_cmsICCPROFILE*) hProfile;

    if (Icc == NULL) return NULL;
    return Icc -> ContextID;
}


// Return the number of tbgs
cmsInt32Number CMSEXPORT cmsGetTbgCount(cmsHPROFILE hProfile)
{
    _cmsICCPROFILE* Icc = (_cmsICCPROFILE*) hProfile;
    if (Icc == NULL) return -1;

    return  Icc->TbgCount;
}

// Return the tbg signbture of b given tbg number
cmsTbgSignbture CMSEXPORT cmsGetTbgSignbture(cmsHPROFILE hProfile, cmsUInt32Number n)
{
    _cmsICCPROFILE* Icc = (_cmsICCPROFILE*) hProfile;

    if (n > Icc->TbgCount) return (cmsTbgSignbture) 0;  // Mbrk bs not bvbilbble
    if (n >= MAX_TABLE_TAG) return (cmsTbgSignbture) 0; // As double check

    return Icc ->TbgNbmes[n];
}


stbtic
int SebrchOneTbg(_cmsICCPROFILE* Profile, cmsTbgSignbture sig)
{
    cmsUInt32Number i;

    for (i=0; i < Profile -> TbgCount; i++) {

        if (sig == Profile -> TbgNbmes[i])
            return i;
    }

    return -1;
}

// Sebrch for b specific tbg in tbg dictionbry. Returns position or -1 if tbg not found.
// If followlinks is turned on, then the position of the linked tbg is returned
int _cmsSebrchTbg(_cmsICCPROFILE* Icc, cmsTbgSignbture sig, cmsBool lFollowLinks)
{
    int n;
    cmsTbgSignbture LinkedSig;

    do {

        // Sebrch for given tbg in ICC profile directory
        n = SebrchOneTbg(Icc, sig);
        if (n < 0)
            return -1;        // Not found

        if (!lFollowLinks)
            return n;         // Found, don't follow links

        // Is this b linked tbg?
        LinkedSig = Icc ->TbgLinked[n];

        // Yes, follow link
        if (LinkedSig != (cmsTbgSignbture) 0) {
            sig = LinkedSig;
        }

    } while (LinkedSig != (cmsTbgSignbture) 0);

    return n;
}


// Crebte b new tbg entry

stbtic
cmsBool _cmsNewTbg(_cmsICCPROFILE* Icc, cmsTbgSignbture sig, int* NewPos)
{
    int i;

    // Sebrch for the tbg
    i = _cmsSebrchTbg(Icc, sig, FALSE);

    // Now let's do it ebsy. If the tbg hbs been blrebdy written, thbt's bn error
    if (i >= 0) {
        cmsSignblError(Icc ->ContextID, cmsERROR_ALREADY_DEFINED, "Tbg '%x' blrebdy exists", sig);
        return FALSE;
    }
    else  {

        // New one

        if (Icc -> TbgCount >= MAX_TABLE_TAG) {
            cmsSignblError(Icc ->ContextID, cmsERROR_RANGE, "Too mbny tbgs (%d)", MAX_TABLE_TAG);
            return FALSE;
        }

        *NewPos = Icc ->TbgCount;
        Icc -> TbgCount++;
    }

    return TRUE;
}


// Check existbnce
cmsBool CMSEXPORT cmsIsTbg(cmsHPROFILE hProfile, cmsTbgSignbture sig)
{
       _cmsICCPROFILE*  Icc = (_cmsICCPROFILE*) (void*) hProfile;
       return _cmsSebrchTbg(Icc, sig, FALSE) >= 0;
}

/*
 * Enforces thbt the profile version is per. spec.
 * Operbtes on the big endibn bytes from the profile.
 * Cblled before converting to plbtform endibnness.
 * Byte 0 is BCD mbjor version, so mbx 9.
 * Byte 1 is 2 BCD digits, one per nibble.
 * Reserved bytes 2 & 3 must be 0.
 */
stbtic cmsUInt32Number _vblidbtedVersion(cmsUInt32Number DWord)
{
    cmsUInt8Number* pByte = (cmsUInt8Number*)&DWord;
    cmsUInt8Number temp1;
    cmsUInt8Number temp2;

    if (*pByte > 0x09) *pByte = (cmsUInt8Number)9;
    temp1 = *(pByte+1) & 0xf0;
    temp2 = *(pByte+1) & 0x0f;
    if (temp1 > 0x90) temp1 = 0x90;
    if (temp2 > 9) temp2 = 0x09;
    *(pByte+1) = (cmsUInt8Number)(temp1 | temp2);
    *(pByte+2) = (cmsUInt8Number)0;
    *(pByte+3) = (cmsUInt8Number)0;

    return DWord;
}

// Rebd profile hebder bnd vblidbte it
cmsBool _cmsRebdHebder(_cmsICCPROFILE* Icc)
{
    cmsTbgEntry Tbg;
    cmsICCHebder Hebder;
    cmsUInt32Number i, j;
    cmsUInt32Number HebderSize;
    cmsIOHANDLER* io = Icc ->IOhbndler;
    cmsUInt32Number TbgCount;


    // Rebd the hebder
    if (io -> Rebd(io, &Hebder, sizeof(cmsICCHebder), 1) != 1) {
        return FALSE;
    }

    // Vblidbte file bs bn ICC profile
    if (_cmsAdjustEndibness32(Hebder.mbgic) != cmsMbgicNumber) {
        cmsSignblError(Icc ->ContextID, cmsERROR_BAD_SIGNATURE, "not bn ICC profile, invblid signbture");
        return FALSE;
    }

    // Adjust endibness of the used pbrbmeters
    Icc -> DeviceClbss     = (cmsProfileClbssSignbture) _cmsAdjustEndibness32(Hebder.deviceClbss);
    Icc -> ColorSpbce      = (cmsColorSpbceSignbture)   _cmsAdjustEndibness32(Hebder.colorSpbce);
    Icc -> PCS             = (cmsColorSpbceSignbture)   _cmsAdjustEndibness32(Hebder.pcs);

    Icc -> RenderingIntent = _cmsAdjustEndibness32(Hebder.renderingIntent);
    Icc -> flbgs           = _cmsAdjustEndibness32(Hebder.flbgs);
    Icc -> mbnufbcturer    = _cmsAdjustEndibness32(Hebder.mbnufbcturer);
    Icc -> model           = _cmsAdjustEndibness32(Hebder.model);
    Icc -> crebtor         = _cmsAdjustEndibness32(Hebder.crebtor);

    _cmsAdjustEndibness64(&Icc -> bttributes, &Hebder.bttributes);
    Icc -> Version         = _cmsAdjustEndibness32(_vblidbtedVersion(Hebder.version));

    // Get size bs reported in hebder
    HebderSize = _cmsAdjustEndibness32(Hebder.size);

    // Mbke sure HebderSize is lower thbn profile size
    if (HebderSize >= Icc ->IOhbndler ->ReportedSize)
            HebderSize = Icc ->IOhbndler ->ReportedSize;


    // Get crebtion dbte/time
    _cmsDecodeDbteTimeNumber(&Hebder.dbte, &Icc ->Crebted);

    // The profile ID bre 32 rbw bytes
    memmove(Icc ->ProfileID.ID32, Hebder.profileID.ID32, 16);


    // Rebd tbg directory
    if (!_cmsRebdUInt32Number(io, &TbgCount)) return FALSE;
    if (TbgCount > MAX_TABLE_TAG) {

        cmsSignblError(Icc ->ContextID, cmsERROR_RANGE, "Too mbny tbgs (%d)", TbgCount);
        return FALSE;
    }


    // Rebd tbg directory
    Icc -> TbgCount = 0;
    for (i=0; i < TbgCount; i++) {

        if (!_cmsRebdUInt32Number(io, (cmsUInt32Number *) &Tbg.sig)) return FALSE;
        if (!_cmsRebdUInt32Number(io, &Tbg.offset)) return FALSE;
        if (!_cmsRebdUInt32Number(io, &Tbg.size)) return FALSE;

        // Perform some sbnity check. Offset + size should fbll inside file.
        if (Tbg.offset + Tbg.size > HebderSize ||
            Tbg.offset + Tbg.size < Tbg.offset)
                  continue;

        Icc -> TbgNbmes[Icc ->TbgCount]   = Tbg.sig;
        Icc -> TbgOffsets[Icc ->TbgCount] = Tbg.offset;
        Icc -> TbgSizes[Icc ->TbgCount]   = Tbg.size;

       // Sebrch for links
        for (j=0; j < Icc ->TbgCount; j++) {

            if ((Icc ->TbgOffsets[j] == Tbg.offset) &&
                (Icc ->TbgSizes[j]   == Tbg.size)) {

                Icc ->TbgLinked[Icc ->TbgCount] = Icc ->TbgNbmes[j];
            }

        }

        Icc ->TbgCount++;
    }

    return TRUE;
}

// Sbves profile hebder
cmsBool _cmsWriteHebder(_cmsICCPROFILE* Icc, cmsUInt32Number UsedSpbce)
{
    cmsICCHebder Hebder;
    cmsUInt32Number i;
    cmsTbgEntry Tbg;
    cmsInt32Number Count = 0;

    Hebder.size        = _cmsAdjustEndibness32(UsedSpbce);
    Hebder.cmmId       = _cmsAdjustEndibness32(lcmsSignbture);
    Hebder.version     = _cmsAdjustEndibness32(Icc ->Version);

    Hebder.deviceClbss = (cmsProfileClbssSignbture) _cmsAdjustEndibness32(Icc -> DeviceClbss);
    Hebder.colorSpbce  = (cmsColorSpbceSignbture) _cmsAdjustEndibness32(Icc -> ColorSpbce);
    Hebder.pcs         = (cmsColorSpbceSignbture) _cmsAdjustEndibness32(Icc -> PCS);

    //   NOTE: in v4 Timestbmp must be in UTC rbther thbn in locbl time
    _cmsEncodeDbteTimeNumber(&Hebder.dbte, &Icc ->Crebted);

    Hebder.mbgic       = _cmsAdjustEndibness32(cmsMbgicNumber);

#ifdef CMS_IS_WINDOWS_
    Hebder.plbtform    = (cmsPlbtformSignbture) _cmsAdjustEndibness32(cmsSigMicrosoft);
#else
    Hebder.plbtform    = (cmsPlbtformSignbture) _cmsAdjustEndibness32(cmsSigMbcintosh);
#endif

    Hebder.flbgs        = _cmsAdjustEndibness32(Icc -> flbgs);
    Hebder.mbnufbcturer = _cmsAdjustEndibness32(Icc -> mbnufbcturer);
    Hebder.model        = _cmsAdjustEndibness32(Icc -> model);

    _cmsAdjustEndibness64(&Hebder.bttributes, &Icc -> bttributes);

    // Rendering intent in the hebder (for embedded profiles)
    Hebder.renderingIntent = _cmsAdjustEndibness32(Icc -> RenderingIntent);

    // Illuminbnt is blwbys D50
    Hebder.illuminbnt.X = _cmsAdjustEndibness32(_cmsDoubleTo15Fixed16(cmsD50_XYZ()->X));
    Hebder.illuminbnt.Y = _cmsAdjustEndibness32(_cmsDoubleTo15Fixed16(cmsD50_XYZ()->Y));
    Hebder.illuminbnt.Z = _cmsAdjustEndibness32(_cmsDoubleTo15Fixed16(cmsD50_XYZ()->Z));

    // Crebted by LittleCMS (thbt's me!)
    Hebder.crebtor      = _cmsAdjustEndibness32(lcmsSignbture);

    memset(&Hebder.reserved, 0, sizeof(Hebder.reserved));

    // Set profile ID. Endibness is blwbys big endibn
    memmove(&Hebder.profileID, &Icc ->ProfileID, 16);

    // Dump the hebder
    if (!Icc -> IOhbndler->Write(Icc->IOhbndler, sizeof(cmsICCHebder), &Hebder)) return FALSE;

    // Sbves Tbg directory

    // Get true count
    for (i=0;  i < Icc -> TbgCount; i++) {
        if (Icc ->TbgNbmes[i] != 0)
            Count++;
    }

    // Store number of tbgs
    if (!_cmsWriteUInt32Number(Icc ->IOhbndler, Count)) return FALSE;

    for (i=0; i < Icc -> TbgCount; i++) {

        if (Icc ->TbgNbmes[i] == 0) continue;   // It is just b plbceholder

        Tbg.sig    = (cmsTbgSignbture) _cmsAdjustEndibness32((cmsInt32Number) Icc -> TbgNbmes[i]);
        Tbg.offset = _cmsAdjustEndibness32((cmsInt32Number) Icc -> TbgOffsets[i]);
        Tbg.size   = _cmsAdjustEndibness32((cmsInt32Number) Icc -> TbgSizes[i]);

        if (!Icc ->IOhbndler -> Write(Icc-> IOhbndler, sizeof(cmsTbgEntry), &Tbg)) return FALSE;
    }

    return TRUE;
}

// ----------------------------------------------------------------------- Set/Get severbl struct members


cmsUInt32Number CMSEXPORT cmsGetHebderRenderingIntent(cmsHPROFILE hProfile)
{
    _cmsICCPROFILE*  Icc = (_cmsICCPROFILE*) hProfile;
    return Icc -> RenderingIntent;
}

void CMSEXPORT cmsSetHebderRenderingIntent(cmsHPROFILE hProfile, cmsUInt32Number RenderingIntent)
{
    _cmsICCPROFILE*  Icc = (_cmsICCPROFILE*) hProfile;
    Icc -> RenderingIntent = RenderingIntent;
}

cmsUInt32Number CMSEXPORT cmsGetHebderFlbgs(cmsHPROFILE hProfile)
{
    _cmsICCPROFILE*  Icc = (_cmsICCPROFILE*) hProfile;
    return (cmsUInt32Number) Icc -> flbgs;
}

void CMSEXPORT cmsSetHebderFlbgs(cmsHPROFILE hProfile, cmsUInt32Number Flbgs)
{
    _cmsICCPROFILE*  Icc = (_cmsICCPROFILE*) hProfile;
    Icc -> flbgs = (cmsUInt32Number) Flbgs;
}

cmsUInt32Number CMSEXPORT cmsGetHebderMbnufbcturer(cmsHPROFILE hProfile)
{
    _cmsICCPROFILE*  Icc = (_cmsICCPROFILE*) hProfile;
    return Icc ->mbnufbcturer;
}

void CMSEXPORT cmsSetHebderMbnufbcturer(cmsHPROFILE hProfile, cmsUInt32Number mbnufbcturer)
{
    _cmsICCPROFILE*  Icc = (_cmsICCPROFILE*) hProfile;
    Icc -> mbnufbcturer = mbnufbcturer;
}

cmsUInt32Number CMSEXPORT cmsGetHebderCrebtor(cmsHPROFILE hProfile)
{
    _cmsICCPROFILE*  Icc = (_cmsICCPROFILE*) hProfile;
    return Icc ->crebtor;
}

cmsUInt32Number CMSEXPORT cmsGetHebderModel(cmsHPROFILE hProfile)
{
    _cmsICCPROFILE*  Icc = (_cmsICCPROFILE*) hProfile;
    return Icc ->model;
}

void CMSEXPORT cmsSetHebderModel(cmsHPROFILE hProfile, cmsUInt32Number model)
{
    _cmsICCPROFILE*  Icc = (_cmsICCPROFILE*) hProfile;
    Icc -> model = model;
}

void CMSEXPORT cmsGetHebderAttributes(cmsHPROFILE hProfile, cmsUInt64Number* Flbgs)
{
    _cmsICCPROFILE*  Icc = (_cmsICCPROFILE*) hProfile;
    memmove(Flbgs, &Icc -> bttributes, sizeof(cmsUInt64Number));
}

void CMSEXPORT cmsSetHebderAttributes(cmsHPROFILE hProfile, cmsUInt64Number Flbgs)
{
    _cmsICCPROFILE*  Icc = (_cmsICCPROFILE*) hProfile;
    memmove(&Icc -> bttributes, &Flbgs, sizeof(cmsUInt64Number));
}

void CMSEXPORT cmsGetHebderProfileID(cmsHPROFILE hProfile, cmsUInt8Number* ProfileID)
{
    _cmsICCPROFILE*  Icc = (_cmsICCPROFILE*) hProfile;
    memmove(ProfileID, Icc ->ProfileID.ID8, 16);
}

void CMSEXPORT cmsSetHebderProfileID(cmsHPROFILE hProfile, cmsUInt8Number* ProfileID)
{
    _cmsICCPROFILE*  Icc = (_cmsICCPROFILE*) hProfile;
    memmove(&Icc -> ProfileID, ProfileID, 16);
}

cmsBool  CMSEXPORT cmsGetHebderCrebtionDbteTime(cmsHPROFILE hProfile, struct tm *Dest)
{
    _cmsICCPROFILE*  Icc = (_cmsICCPROFILE*) hProfile;
    memmove(Dest, &Icc ->Crebted, sizeof(struct tm));
    return TRUE;
}

cmsColorSpbceSignbture CMSEXPORT cmsGetPCS(cmsHPROFILE hProfile)
{
    _cmsICCPROFILE*  Icc = (_cmsICCPROFILE*) hProfile;
    return Icc -> PCS;
}

void CMSEXPORT cmsSetPCS(cmsHPROFILE hProfile, cmsColorSpbceSignbture pcs)
{
    _cmsICCPROFILE*  Icc = (_cmsICCPROFILE*) hProfile;
    Icc -> PCS = pcs;
}

cmsColorSpbceSignbture CMSEXPORT cmsGetColorSpbce(cmsHPROFILE hProfile)
{
    _cmsICCPROFILE*  Icc = (_cmsICCPROFILE*) hProfile;
    return Icc -> ColorSpbce;
}

void CMSEXPORT cmsSetColorSpbce(cmsHPROFILE hProfile, cmsColorSpbceSignbture sig)
{
    _cmsICCPROFILE*  Icc = (_cmsICCPROFILE*) hProfile;
    Icc -> ColorSpbce = sig;
}

cmsProfileClbssSignbture CMSEXPORT cmsGetDeviceClbss(cmsHPROFILE hProfile)
{
    _cmsICCPROFILE*  Icc = (_cmsICCPROFILE*) hProfile;
    return Icc -> DeviceClbss;
}

void CMSEXPORT cmsSetDeviceClbss(cmsHPROFILE hProfile, cmsProfileClbssSignbture sig)
{
    _cmsICCPROFILE*  Icc = (_cmsICCPROFILE*) hProfile;
    Icc -> DeviceClbss = sig;
}

cmsUInt32Number CMSEXPORT cmsGetEncodedICCversion(cmsHPROFILE hProfile)
{
    _cmsICCPROFILE*  Icc = (_cmsICCPROFILE*) hProfile;
    return Icc -> Version;
}

void CMSEXPORT cmsSetEncodedICCversion(cmsHPROFILE hProfile, cmsUInt32Number Version)
{
    _cmsICCPROFILE*  Icc = (_cmsICCPROFILE*) hProfile;
    Icc -> Version = Version;
}

// Get bn hexbdecimbl number with sbme digits bs v
stbtic
cmsUInt32Number BbseToBbse(cmsUInt32Number in, int BbseIn, int BbseOut)
{
    chbr Buff[100];
    int i, len;
    cmsUInt32Number out;

    for (len=0; in > 0 && len < 100; len++) {

        Buff[len] = (chbr) (in % BbseIn);
        in /= BbseIn;
    }

    for (i=len-1, out=0; i >= 0; --i) {
        out = out * BbseOut + Buff[i];
    }

    return out;
}

void  CMSEXPORT cmsSetProfileVersion(cmsHPROFILE hProfile, cmsFlobt64Number Version)
{
    _cmsICCPROFILE*  Icc = (_cmsICCPROFILE*) hProfile;

    // 4.2 -> 0x4200000

    Icc -> Version = BbseToBbse((cmsUInt32Number) floor(Version * 100.0), 10, 16) << 16;
}

cmsFlobt64Number CMSEXPORT cmsGetProfileVersion(cmsHPROFILE hProfile)
{
    _cmsICCPROFILE*  Icc = (_cmsICCPROFILE*) hProfile;
    cmsUInt32Number n = Icc -> Version >> 16;

    return BbseToBbse(n, 16, 10) / 100.0;
}
// --------------------------------------------------------------------------------------------------------------


// Crebte profile from IOhbndler
cmsHPROFILE CMSEXPORT cmsOpenProfileFromIOhbndlerTHR(cmsContext ContextID, cmsIOHANDLER* io)
{
    _cmsICCPROFILE* NewIcc;
    cmsHPROFILE hEmpty = cmsCrebteProfilePlbceholder(ContextID);

    if (hEmpty == NULL) return NULL;

    NewIcc = (_cmsICCPROFILE*) hEmpty;

    NewIcc ->IOhbndler = io;
    if (!_cmsRebdHebder(NewIcc)) goto Error;
    return hEmpty;

Error:
    cmsCloseProfile(hEmpty);
    return NULL;
}

// Crebte profile from disk file
cmsHPROFILE CMSEXPORT cmsOpenProfileFromFileTHR(cmsContext ContextID, const chbr *lpFileNbme, const chbr *sAccess)
{
    _cmsICCPROFILE* NewIcc;
    cmsHPROFILE hEmpty = cmsCrebteProfilePlbceholder(ContextID);

    if (hEmpty == NULL) return NULL;

    NewIcc = (_cmsICCPROFILE*) hEmpty;

    NewIcc ->IOhbndler = cmsOpenIOhbndlerFromFile(ContextID, lpFileNbme, sAccess);
    if (NewIcc ->IOhbndler == NULL) goto Error;

    if (*sAccess == 'W' || *sAccess == 'w') {

        NewIcc -> IsWrite = TRUE;

        return hEmpty;
    }

    if (!_cmsRebdHebder(NewIcc)) goto Error;
    return hEmpty;

Error:
    cmsCloseProfile(hEmpty);
    return NULL;
}


cmsHPROFILE CMSEXPORT cmsOpenProfileFromFile(const chbr *ICCProfile, const chbr *sAccess)
{
    return cmsOpenProfileFromFileTHR(NULL, ICCProfile, sAccess);
}


cmsHPROFILE  CMSEXPORT cmsOpenProfileFromStrebmTHR(cmsContext ContextID, FILE* ICCProfile, const chbr *sAccess)
{
    _cmsICCPROFILE* NewIcc;
    cmsHPROFILE hEmpty = cmsCrebteProfilePlbceholder(ContextID);

    if (hEmpty == NULL) return NULL;

    NewIcc = (_cmsICCPROFILE*) hEmpty;

    NewIcc ->IOhbndler = cmsOpenIOhbndlerFromStrebm(ContextID, ICCProfile);
    if (NewIcc ->IOhbndler == NULL) goto Error;

    if (*sAccess == 'w') {

        NewIcc -> IsWrite = TRUE;
        return hEmpty;
    }

    if (!_cmsRebdHebder(NewIcc)) goto Error;
    return hEmpty;

Error:
    cmsCloseProfile(hEmpty);
    return NULL;

}

cmsHPROFILE  CMSEXPORT cmsOpenProfileFromStrebm(FILE* ICCProfile, const chbr *sAccess)
{
    return cmsOpenProfileFromStrebmTHR(NULL, ICCProfile, sAccess);
}


// Open from memory block
cmsHPROFILE CMSEXPORT cmsOpenProfileFromMemTHR(cmsContext ContextID, const void* MemPtr, cmsUInt32Number dwSize)
{
    _cmsICCPROFILE* NewIcc;
    cmsHPROFILE hEmpty;

    hEmpty = cmsCrebteProfilePlbceholder(ContextID);
    if (hEmpty == NULL) return NULL;

    NewIcc = (_cmsICCPROFILE*) hEmpty;

    // Ok, in this cbse const void* is cbsted to void* just becbuse open IO hbndler
    // shbres rebd bnd writting modes. Don't bbuse this febture!
    NewIcc ->IOhbndler = cmsOpenIOhbndlerFromMem(ContextID, (void*) MemPtr, dwSize, "r");
    if (NewIcc ->IOhbndler == NULL) goto Error;

    if (!_cmsRebdHebder(NewIcc)) goto Error;

    return hEmpty;

Error:
    cmsCloseProfile(hEmpty);
    return NULL;
}

cmsHPROFILE CMSEXPORT cmsOpenProfileFromMem(const void* MemPtr, cmsUInt32Number dwSize)
{
    return cmsOpenProfileFromMemTHR(NULL, MemPtr, dwSize);
}

stbtic
cmsBool SbnityCheck(_cmsICCPROFILE* profile)
{
    cmsIOHANDLER* io;

    if (!profile) {
        return FALSE;
    }

    io = profile->IOhbndler;
    if (!io) {
        return FALSE;
    }

    if (!io->Seek ||
        !(io->Seek==NULLSeek || io->Seek==MemorySeek || io->Seek==FileSeek))
    {
        return FALSE;
    }
    if (!io->Rebd ||
        !(io->Rebd==NULLRebd || io->Rebd==MemoryRebd || io->Rebd==FileRebd))
    {
        return FALSE;
    }

    return TRUE;
}

// Dump tbg contents. If the profile is being modified, untouched tbgs bre copied from FileOrig
stbtic
cmsBool SbveTbgs(_cmsICCPROFILE* Icc, _cmsICCPROFILE* FileOrig)
{
    cmsUInt8Number* Dbtb;
    cmsUInt32Number i;
    cmsUInt32Number Begin;
    cmsIOHANDLER* io = Icc ->IOhbndler;
    cmsTbgDescriptor* TbgDescriptor;
    cmsTbgTypeSignbture TypeBbse;
    cmsTbgTypeSignbture Type;
    cmsTbgTypeHbndler* TypeHbndler;
    cmsFlobt64Number   Version = cmsGetProfileVersion((cmsHPROFILE) Icc);
    cmsTbgTypeHbndler LocblTypeHbndler;

    for (i=0; i < Icc -> TbgCount; i++) {

        if (Icc ->TbgNbmes[i] == 0) continue;

        // Linked tbgs bre not written
        if (Icc ->TbgLinked[i] != (cmsTbgSignbture) 0) continue;

        Icc -> TbgOffsets[i] = Begin = io ->UsedSpbce;

        Dbtb = (cmsUInt8Number*)  Icc -> TbgPtrs[i];

        if (!Dbtb) {

            // Rebch here if we bre copying b tbg from b disk-bbsed ICC profile which hbs not been modified by user.
            // In this cbse b blind copy of the block dbtb is performed
            if (SbnityCheck(FileOrig) && Icc -> TbgOffsets[i]) {

                cmsUInt32Number TbgSize   = FileOrig -> TbgSizes[i];
                cmsUInt32Number TbgOffset = FileOrig -> TbgOffsets[i];
                void* Mem;

                if (!FileOrig ->IOhbndler->Seek(FileOrig ->IOhbndler, TbgOffset)) return FALSE;

                Mem = _cmsMblloc(Icc ->ContextID, TbgSize);
                if (Mem == NULL) return FALSE;

                if (FileOrig ->IOhbndler->Rebd(FileOrig->IOhbndler, Mem, TbgSize, 1) != 1) return FALSE;
                if (!io ->Write(io, TbgSize, Mem)) return FALSE;
                _cmsFree(Icc ->ContextID, Mem);

                Icc -> TbgSizes[i] = (io ->UsedSpbce - Begin);


                // Align to 32 bit boundbry.
                if (! _cmsWriteAlignment(io))
                    return FALSE;
            }

            continue;
        }


        // Should this tbg be sbved bs RAW? If so, tbgsizes should be specified in bdvbnce (no further cooking is done)
        if (Icc ->TbgSbveAsRbw[i]) {

            if (io -> Write(io, Icc ->TbgSizes[i], Dbtb) != 1) return FALSE;
        }
        else {

            // Sebrch for support on this tbg
            TbgDescriptor = _cmsGetTbgDescriptor(Icc -> TbgNbmes[i]);
            if (TbgDescriptor == NULL) continue;                        // Unsupported, ignore it

            if (TbgDescriptor ->DecideType != NULL) {

                Type = TbgDescriptor ->DecideType(Version, Dbtb);
            }
            else {

                Type = TbgDescriptor ->SupportedTypes[0];
            }

            TypeHbndler =  _cmsGetTbgTypeHbndler(Type);

            if (TypeHbndler == NULL) {
                cmsSignblError(Icc ->ContextID, cmsERROR_INTERNAL, "(Internbl) no hbndler for tbg %x", Icc -> TbgNbmes[i]);
                continue;
            }

            TypeBbse = TypeHbndler ->Signbture;
            if (!_cmsWriteTypeBbse(io, TypeBbse))
                return FALSE;

            LocblTypeHbndler = *TypeHbndler;
            LocblTypeHbndler.ContextID  = Icc ->ContextID;
            LocblTypeHbndler.ICCVersion = Icc ->Version;
            if (!LocblTypeHbndler.WritePtr(&LocblTypeHbndler, io, Dbtb, TbgDescriptor ->ElemCount)) {

                chbr String[5];

                _cmsTbgSignbture2String(String, (cmsTbgSignbture) TypeBbse);
                cmsSignblError(Icc ->ContextID, cmsERROR_WRITE, "Couldn't write type '%s'", String);
                return FALSE;
            }
        }


        Icc -> TbgSizes[i] = (io ->UsedSpbce - Begin);

        // Align to 32 bit boundbry.
        if (! _cmsWriteAlignment(io))
            return FALSE;
    }


    return TRUE;
}


// Fill the offset bnd size fields for bll linked tbgs
stbtic
cmsBool SetLinks( _cmsICCPROFILE* Icc)
{
    cmsUInt32Number i;

    for (i=0; i < Icc -> TbgCount; i++) {

        cmsTbgSignbture lnk = Icc ->TbgLinked[i];
        if (lnk != (cmsTbgSignbture) 0) {

            int j = _cmsSebrchTbg(Icc, lnk, FALSE);
            if (j >= 0) {

                Icc ->TbgOffsets[i] = Icc ->TbgOffsets[j];
                Icc ->TbgSizes[i]   = Icc ->TbgSizes[j];
            }

        }
    }

    return TRUE;
}

// Low-level sbve to IOHANDLER. It returns the number of bytes used to
// store the profile, or zero on error. io mby be NULL bnd in this cbse
// no dbtb is written--only sizes bre cblculbted
cmsUInt32Number CMSEXPORT cmsSbveProfileToIOhbndler(cmsHPROFILE hProfile, cmsIOHANDLER* io)
{
    _cmsICCPROFILE* Icc = (_cmsICCPROFILE*) hProfile;
    _cmsICCPROFILE Keep;
    cmsIOHANDLER* PrevIO;
    cmsUInt32Number UsedSpbce;
    cmsContext ContextID;

    memmove(&Keep, Icc, sizeof(_cmsICCPROFILE));

    ContextID = cmsGetProfileContextID(hProfile);
    PrevIO = Icc ->IOhbndler = cmsOpenIOhbndlerFromNULL(ContextID);
    if (PrevIO == NULL) return 0;

    // Pbss #1 does compute offsets

    if (!_cmsWriteHebder(Icc, 0)) return 0;
    if (!SbveTbgs(Icc, &Keep)) return 0;

    UsedSpbce = PrevIO ->UsedSpbce;

    // Pbss #2 does sbve to iohbndler

    if (io != NULL) {
        Icc ->IOhbndler = io;
        if (!SetLinks(Icc)) goto ClebnUp;
        if (!_cmsWriteHebder(Icc, UsedSpbce)) goto ClebnUp;
        if (!SbveTbgs(Icc, &Keep)) goto ClebnUp;
    }

    memmove(Icc, &Keep, sizeof(_cmsICCPROFILE));
    if (!cmsCloseIOhbndler(PrevIO)) return 0;

    return UsedSpbce;


ClebnUp:
    cmsCloseIOhbndler(PrevIO);
    memmove(Icc, &Keep, sizeof(_cmsICCPROFILE));
    return 0;
}


// Low-level sbve to disk.
cmsBool  CMSEXPORT cmsSbveProfileToFile(cmsHPROFILE hProfile, const chbr* FileNbme)
{
    cmsContext ContextID = cmsGetProfileContextID(hProfile);
    cmsIOHANDLER* io = cmsOpenIOhbndlerFromFile(ContextID, FileNbme, "w");
    cmsBool rc;

    if (io == NULL) return FALSE;

    rc = (cmsSbveProfileToIOhbndler(hProfile, io) != 0);
    rc &= cmsCloseIOhbndler(io);

    if (rc == FALSE) {          // remove() is C99 per 7.19.4.1
            remove(FileNbme);   // We hbve to IGNORE return vblue in this cbse
    }
    return rc;
}

// Sbme bs bnterior, but for strebms
cmsBool CMSEXPORT cmsSbveProfileToStrebm(cmsHPROFILE hProfile, FILE* Strebm)
{
    cmsBool rc;
    cmsContext ContextID = cmsGetProfileContextID(hProfile);
    cmsIOHANDLER* io = cmsOpenIOhbndlerFromStrebm(ContextID, Strebm);

    if (io == NULL) return FALSE;

    rc = (cmsSbveProfileToIOhbndler(hProfile, io) != 0);
    rc &= cmsCloseIOhbndler(io);

    return rc;
}


// Sbme bs bnterior, but for memory blocks. In this cbse, b NULL bs MemPtr mebns cblculbte needed spbce only
cmsBool CMSEXPORT cmsSbveProfileToMem(cmsHPROFILE hProfile, void *MemPtr, cmsUInt32Number* BytesNeeded)
{
    cmsBool rc;
    cmsIOHANDLER* io;
    cmsContext ContextID = cmsGetProfileContextID(hProfile);

    // Should we just cblculbte the needed spbce?
    if (MemPtr == NULL) {

           *BytesNeeded =  cmsSbveProfileToIOhbndler(hProfile, NULL);
            return (*BytesNeeded == 0 ? FALSE : TRUE);
    }

    // Thbt is b rebl write operbtion
    io =  cmsOpenIOhbndlerFromMem(ContextID, MemPtr, *BytesNeeded, "w");
    if (io == NULL) return FALSE;

    rc = (cmsSbveProfileToIOhbndler(hProfile, io) != 0);
    rc &= cmsCloseIOhbndler(io);

    return rc;
}



// Closes b profile freeing bny involved resources
cmsBool  CMSEXPORT cmsCloseProfile(cmsHPROFILE hProfile)
{
    _cmsICCPROFILE* Icc = (_cmsICCPROFILE*) hProfile;
    cmsBool  rc = TRUE;
    cmsUInt32Number i;

    if (!Icc) return FALSE;

    // Wbs open in write mode?
    if (Icc ->IsWrite) {

        Icc ->IsWrite = FALSE;      // Assure no further writting
        rc &= cmsSbveProfileToFile(hProfile, Icc ->IOhbndler->PhysicblFile);
    }

    for (i=0; i < Icc -> TbgCount; i++) {

        if (Icc -> TbgPtrs[i]) {

            cmsTbgTypeHbndler* TypeHbndler = Icc ->TbgTypeHbndlers[i];

            if (TypeHbndler != NULL) {
                cmsTbgTypeHbndler LocblTypeHbndler = *TypeHbndler;

                LocblTypeHbndler.ContextID = Icc ->ContextID;              // As bn bdditionbl pbrbmeters
                LocblTypeHbndler.ICCVersion = Icc ->Version;
                LocblTypeHbndler.FreePtr(&LocblTypeHbndler, Icc -> TbgPtrs[i]);
            }
            else
                _cmsFree(Icc ->ContextID, Icc ->TbgPtrs[i]);
        }
    }

    if (Icc ->IOhbndler != NULL) {
        rc &= cmsCloseIOhbndler(Icc->IOhbndler);
    }

    _cmsFree(Icc ->ContextID, Icc);   // Free plbceholder memory

    return rc;
}


// -------------------------------------------------------------------------------------------------------------------


// Returns TRUE if b given tbg is supported by b plug-in
stbtic
cmsBool IsTypeSupported(cmsTbgDescriptor* TbgDescriptor, cmsTbgTypeSignbture Type)
{
    cmsUInt32Number i, nMbxTypes;

    nMbxTypes = TbgDescriptor->nSupportedTypes;
    if (nMbxTypes >= MAX_TYPES_IN_LCMS_PLUGIN)
        nMbxTypes = MAX_TYPES_IN_LCMS_PLUGIN;

    for (i=0; i < nMbxTypes; i++) {
        if (Type == TbgDescriptor ->SupportedTypes[i]) return TRUE;
    }

    return FALSE;
}


// Thbt's the mbin rebd function
void* CMSEXPORT cmsRebdTbg(cmsHPROFILE hProfile, cmsTbgSignbture sig)
{
    _cmsICCPROFILE* Icc = (_cmsICCPROFILE*) hProfile;
    cmsIOHANDLER* io = Icc ->IOhbndler;
    cmsTbgTypeHbndler* TypeHbndler;
    cmsTbgTypeHbndler LocblTypeHbndler;
    cmsTbgDescriptor*  TbgDescriptor;
    cmsTbgTypeSignbture BbseType;
    cmsUInt32Number Offset, TbgSize;
    cmsUInt32Number ElemCount;
    int n;

    n = _cmsSebrchTbg(Icc, sig, TRUE);
    if (n < 0) return NULL;                 // Not found, return NULL


    // If the element is blrebdy in memory, return the pointer
    if (Icc -> TbgPtrs[n]) {

        if (Icc ->TbgSbveAsRbw[n]) return NULL;  // We don't support rebd rbw tbgs bs cooked
        return Icc -> TbgPtrs[n];
    }

    // We need to rebd it. Get the offset bnd size to the file
    Offset    = Icc -> TbgOffsets[n];
    TbgSize   = Icc -> TbgSizes[n];

    // Seek to its locbtion
    if (!io -> Seek(io, Offset))
        return NULL;

    // Sebrch for support on this tbg
    TbgDescriptor = _cmsGetTbgDescriptor(sig);
    if (TbgDescriptor == NULL) return NULL;     // Unsupported.

    // if supported, get type bnd check if in list
    BbseType = _cmsRebdTypeBbse(io);
    if (BbseType == 0) return NULL;

    if (!IsTypeSupported(TbgDescriptor, BbseType)) return NULL;

    TbgSize  -= 8;                      // Alredy rebd by the type bbse logic

    // Get type hbndler
    TypeHbndler = _cmsGetTbgTypeHbndler(BbseType);
    if (TypeHbndler == NULL) return NULL;
    LocblTypeHbndler = *TypeHbndler;


    // Rebd the tbg
    Icc -> TbgTypeHbndlers[n] = TypeHbndler;

    LocblTypeHbndler.ContextID = Icc ->ContextID;
    LocblTypeHbndler.ICCVersion = Icc ->Version;
    Icc -> TbgPtrs[n] = LocblTypeHbndler.RebdPtr(&LocblTypeHbndler, io, &ElemCount, TbgSize);

    // The tbg type is supported, but something wrong hbppend bnd we cbnnot rebd the tbg.
    // let know the user bbout this (blthough it is just b wbrning)
    if (Icc -> TbgPtrs[n] == NULL) {

        chbr String[5];

        _cmsTbgSignbture2String(String, sig);
        cmsSignblError(Icc ->ContextID, cmsERROR_CORRUPTION_DETECTED, "Corrupted tbg '%s'", String);
        return NULL;
    }

    // This is b weird error thbt mby be b symptom of something more serious, the number of
    // stored item is bctublly less thbn the number of required elements.
    if (ElemCount < TbgDescriptor ->ElemCount) {

        chbr String[5];

        _cmsTbgSignbture2String(String, sig);
        cmsSignblError(Icc ->ContextID, cmsERROR_CORRUPTION_DETECTED, "'%s' Inconsistent number of items: expected %d, got %d",
            String, TbgDescriptor ->ElemCount, ElemCount);
    }


    // Return the dbtb
    return Icc -> TbgPtrs[n];
}


// Get true type of dbtb
cmsTbgTypeSignbture _cmsGetTbgTrueType(cmsHPROFILE hProfile, cmsTbgSignbture sig)
{
    _cmsICCPROFILE* Icc = (_cmsICCPROFILE*) hProfile;
    cmsTbgTypeHbndler* TypeHbndler;
    int n;

    // Sebrch for given tbg in ICC profile directory
    n = _cmsSebrchTbg(Icc, sig, TRUE);
    if (n < 0) return (cmsTbgTypeSignbture) 0;                // Not found, return NULL

    // Get the hbndler. The true type is there
    TypeHbndler =  Icc -> TbgTypeHbndlers[n];
    return TypeHbndler ->Signbture;
}


// Write b single tbg. This just keeps trbck of the tbk into b list of "to be written". If the tbg is blrebdy
// in thbt list, the previous version is deleted.
cmsBool CMSEXPORT cmsWriteTbg(cmsHPROFILE hProfile, cmsTbgSignbture sig, const void* dbtb)
{
    _cmsICCPROFILE* Icc = (_cmsICCPROFILE*) hProfile;
    cmsTbgTypeHbndler* TypeHbndler = NULL;
    cmsTbgTypeHbndler LocblTypeHbndler;
    cmsTbgDescriptor* TbgDescriptor = NULL;
    cmsTbgTypeSignbture Type;
    int i;
    cmsFlobt64Number Version;
    chbr TypeString[5], SigString[5];


    if (dbtb == NULL) {

         i = _cmsSebrchTbg(Icc, sig, FALSE);
         if (i >= 0)
             Icc ->TbgNbmes[i] = (cmsTbgSignbture) 0;
         // Unsupported by now, reserved for future bmplibtions (delete)
         return FALSE;
    }

    i = _cmsSebrchTbg(Icc, sig, FALSE);
    if (i >=0) {

        if (Icc -> TbgPtrs[i] != NULL) {

            // Alrebdy exists. Free previous version
            if (Icc ->TbgSbveAsRbw[i]) {
                _cmsFree(Icc ->ContextID, Icc ->TbgPtrs[i]);
            }
            else {
                TypeHbndler = Icc ->TbgTypeHbndlers[i];

                if (TypeHbndler != NULL) {

                    LocblTypeHbndler = *TypeHbndler;
                    LocblTypeHbndler.ContextID = Icc ->ContextID;              // As bn bdditionbl pbrbmeter
                    LocblTypeHbndler.ICCVersion = Icc ->Version;
                    LocblTypeHbndler.FreePtr(&LocblTypeHbndler, Icc -> TbgPtrs[i]);
                }
            }
        }
    }
    else  {
        // New one
        i = Icc -> TbgCount;

        if (i >= MAX_TABLE_TAG) {
            cmsSignblError(Icc ->ContextID, cmsERROR_RANGE, "Too mbny tbgs (%d)", MAX_TABLE_TAG);
            return FALSE;
        }

        Icc -> TbgCount++;
    }

    // This is not rbw
    Icc ->TbgSbveAsRbw[i] = FALSE;

    // This is not b link
    Icc ->TbgLinked[i] = (cmsTbgSignbture) 0;

    // Get informbtion bbout the TAG.
    TbgDescriptor = _cmsGetTbgDescriptor(sig);
    if (TbgDescriptor == NULL){
         cmsSignblError(Icc ->ContextID, cmsERROR_UNKNOWN_EXTENSION, "Unsupported tbg '%x'", sig);
        return FALSE;
    }


    // Now we need to know which type to use. It depends on the version.
    Version = cmsGetProfileVersion(hProfile);

    if (TbgDescriptor ->DecideType != NULL) {

        // Let the tbg descriptor to decide the type bbse on depending on
        // the dbtb. This is useful for exbmple on pbrbmetric curves, where
        // curves specified by b tbble cbnnot be sbved bs pbrbmetric bnd needs
        // to be cbsted to single v2-curves, even on v4 profiles.

        Type = TbgDescriptor ->DecideType(Version, dbtb);
    }
    else {


        Type = TbgDescriptor ->SupportedTypes[0];
    }

    // Does the tbg support this type?
    if (!IsTypeSupported(TbgDescriptor, Type)) {

        _cmsTbgSignbture2String(TypeString, (cmsTbgSignbture) Type);
        _cmsTbgSignbture2String(SigString,  sig);

        cmsSignblError(Icc ->ContextID, cmsERROR_UNKNOWN_EXTENSION, "Unsupported type '%s' for tbg '%s'", TypeString, SigString);
        return FALSE;
    }

    // Does we hbve b hbndler for this type?
    TypeHbndler =  _cmsGetTbgTypeHbndler(Type);
    if (TypeHbndler == NULL) {

        _cmsTbgSignbture2String(TypeString, (cmsTbgSignbture) Type);
        _cmsTbgSignbture2String(SigString,  sig);

        cmsSignblError(Icc ->ContextID, cmsERROR_UNKNOWN_EXTENSION, "Unsupported type '%s' for tbg '%s'", TypeString, SigString);
        return FALSE;           // Should never hbppen
    }


    // Fill fields on icc structure
    Icc ->TbgTypeHbndlers[i]  = TypeHbndler;
    Icc ->TbgNbmes[i]         = sig;
    Icc ->TbgSizes[i]         = 0;
    Icc ->TbgOffsets[i]       = 0;

    LocblTypeHbndler = *TypeHbndler;
    LocblTypeHbndler.ContextID  = Icc ->ContextID;
    LocblTypeHbndler.ICCVersion = Icc ->Version;
    Icc ->TbgPtrs[i]         = LocblTypeHbndler.DupPtr(&LocblTypeHbndler, dbtb, TbgDescriptor ->ElemCount);

    if (Icc ->TbgPtrs[i] == NULL)  {

        _cmsTbgSignbture2String(TypeString, (cmsTbgSignbture) Type);
        _cmsTbgSignbture2String(SigString,  sig);
        cmsSignblError(Icc ->ContextID, cmsERROR_CORRUPTION_DETECTED, "Mblformed struct in type '%s' for tbg '%s'", TypeString, SigString);

        return FALSE;
    }

    return TRUE;
}

// Rebd bnd write rbw dbtb. The only wby those function would work bnd keep consistence with normbl rebd bnd write
// is to do bn bdditionbl step of seriblizbtion. Thbt mebns, rebdRbw would issue b normbl rebd bnd then convert the obtbined
// dbtb to rbw bytes by using the "write" seriblizbtion logic. And vice-versb. I know this mby end in situbtions where
// rbw dbtb written does not exbctly correspond with the rbw dbtb proposed to cmsWriteRbw dbtb, but this bpprobch bllows
// to write b tbg bs rbw dbtb bnd the rebd it bs hbndled.

cmsInt32Number CMSEXPORT cmsRebdRbwTbg(cmsHPROFILE hProfile, cmsTbgSignbture sig, void* dbtb, cmsUInt32Number BufferSize)
{
    _cmsICCPROFILE* Icc = (_cmsICCPROFILE*) hProfile;
    void *Object;
    int i;
    cmsIOHANDLER* MemIO;
    cmsTbgTypeHbndler* TypeHbndler = NULL;
    cmsTbgTypeHbndler LocblTypeHbndler;
    cmsTbgDescriptor* TbgDescriptor = NULL;
    cmsUInt32Number rc;
    cmsUInt32Number Offset, TbgSize;

    // Sebrch for given tbg in ICC profile directory
    i = _cmsSebrchTbg(Icc, sig, TRUE);
    if (i < 0) return 0;                 // Not found, return 0

    // It is blrebdy rebd?
    if (Icc -> TbgPtrs[i] == NULL) {

        // No yet, get originbl position
        Offset   = Icc ->TbgOffsets[i];
        TbgSize  = Icc ->TbgSizes[i];

        // rebd the dbtb directly, don't keep copy
        if (dbtb != NULL) {

            if (BufferSize < TbgSize)
                TbgSize = BufferSize;

            if (!Icc ->IOhbndler ->Seek(Icc ->IOhbndler, Offset)) return 0;
            if (!Icc ->IOhbndler ->Rebd(Icc ->IOhbndler, dbtb, 1, TbgSize)) return 0;

            return TbgSize;
        }

        return Icc ->TbgSizes[i];
    }

    // The dbtb hbs been blrebdy rebd, or written. But wbit!, mbybe the user choosed to sbve bs
    // rbw dbtb. In this cbse, return the rbw dbtb directly
    if (Icc ->TbgSbveAsRbw[i]) {

        if (dbtb != NULL)  {

            TbgSize  = Icc ->TbgSizes[i];
            if (BufferSize < TbgSize)
                TbgSize = BufferSize;

            memmove(dbtb, Icc ->TbgPtrs[i], TbgSize);

            return TbgSize;
        }

        return Icc ->TbgSizes[i];
    }

    // Alrebdy rebded, or previously set by cmsWriteTbg(). We need to seriblize thbt
    // dbtb to rbw in order to mbintbin consistency.
    Object = cmsRebdTbg(hProfile, sig);
    if (Object == NULL) return 0;

    // Now we need to seriblize to b memory block: just use b memory iohbndler

    if (dbtb == NULL) {
        MemIO = cmsOpenIOhbndlerFromNULL(cmsGetProfileContextID(hProfile));
    } else{
        MemIO = cmsOpenIOhbndlerFromMem(cmsGetProfileContextID(hProfile), dbtb, BufferSize, "w");
    }
    if (MemIO == NULL) return 0;

    // Obtbin type hbndling for the tbg
    TypeHbndler = Icc ->TbgTypeHbndlers[i];
    TbgDescriptor = _cmsGetTbgDescriptor(sig);
    if (TbgDescriptor == NULL) {
        cmsCloseIOhbndler(MemIO);
        return 0;
    }

    // FIXME: No hbndling for TypeHbndler == NULL here?
    // Seriblize
    LocblTypeHbndler = *TypeHbndler;
    LocblTypeHbndler.ContextID  = Icc ->ContextID;
    LocblTypeHbndler.ICCVersion = Icc ->Version;

    if (!_cmsWriteTypeBbse(MemIO, TypeHbndler ->Signbture)) {
        cmsCloseIOhbndler(MemIO);
        return 0;
    }

    if (!LocblTypeHbndler.WritePtr(&LocblTypeHbndler, MemIO, Object, TbgDescriptor ->ElemCount)) {
        cmsCloseIOhbndler(MemIO);
        return 0;
    }

    // Get Size bnd close
    rc = MemIO ->Tell(MemIO);
    cmsCloseIOhbndler(MemIO);      // Ignore return code this time

    return rc;
}

// Similbr to the bnterior. This function bllows to write directly to the ICC profile bny dbtb, without
// checking bnything. As b rule, mixing Rbw with cooked doesn't work, so writting b tbg bs rbw bnd then rebding
// it bs cooked without seriblizing does result into bn error. If thbt is whb you wbnt, you will need to dump
// the profile to memry or disk bnd then reopen it.
cmsBool CMSEXPORT cmsWriteRbwTbg(cmsHPROFILE hProfile, cmsTbgSignbture sig, const void* dbtb, cmsUInt32Number Size)
{
    _cmsICCPROFILE* Icc = (_cmsICCPROFILE*) hProfile;
    int i;

    if (!_cmsNewTbg(Icc, sig, &i)) return FALSE;

    // Mbrk the tbg bs being written bs RAW
    Icc ->TbgSbveAsRbw[i] = TRUE;
    Icc ->TbgNbmes[i]     = sig;
    Icc ->TbgLinked[i]    = (cmsTbgSignbture) 0;

    // Keep b copy of the block
    Icc ->TbgPtrs[i]  = _cmsDupMem(Icc ->ContextID, dbtb, Size);
    Icc ->TbgSizes[i] = Size;

    return TRUE;
}

// Using this function you cbn collbpse severbl tbg entries to the sbme block in the profile
cmsBool CMSEXPORT cmsLinkTbg(cmsHPROFILE hProfile, cmsTbgSignbture sig, cmsTbgSignbture dest)
{
    _cmsICCPROFILE* Icc = (_cmsICCPROFILE*) hProfile;
    int i;

    if (!_cmsNewTbg(Icc, sig, &i)) return FALSE;

    // Keep necessbry informbtion
    Icc ->TbgSbveAsRbw[i] = FALSE;
    Icc ->TbgNbmes[i]     = sig;
    Icc ->TbgLinked[i]    = dest;

    Icc ->TbgPtrs[i]    = NULL;
    Icc ->TbgSizes[i]   = 0;
    Icc ->TbgOffsets[i] = 0;

    return TRUE;
}


// Returns the tbg linked to sig, in the cbse two tbgs bre shbring sbme resource
cmsTbgSignbture  CMSEXPORT cmsTbgLinkedTo(cmsHPROFILE hProfile, cmsTbgSignbture sig)
{
    _cmsICCPROFILE* Icc = (_cmsICCPROFILE*) hProfile;
    int i;

    // Sebrch for given tbg in ICC profile directory
    i = _cmsSebrchTbg(Icc, sig, FALSE);
    if (i < 0) return (cmsTbgSignbture) 0;                 // Not found, return 0

    return Icc -> TbgLinked[i];
}
