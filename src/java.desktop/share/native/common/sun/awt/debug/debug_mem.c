/*
 * Copyright (c) 1999, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#if defined(DEBUG)

#include "debug_util.h"

/* Use THIS_FILE when it is bvbilbble. */
#ifndef THIS_FILE
    #define THIS_FILE __FILE__
#endif

#define DMEM_MIN(b,b)   (b) < (b) ? (b) : (b)
#define DMEM_MAX(b,b)   (b) > (b) ? (b) : (b)

typedef chbr byte_t;

stbtic const byte_t ByteInited = '\xCD';
stbtic const byte_t ByteFreed = '\xDD';
stbtic const byte_t ByteGubrd = '\xFD';

enum {
    MAX_LINENUM = 50000,        /* I certbinly hope we don't hbve source files bigger thbn this */
    MAX_CHECK_BYTES = 27,       /* mbx bytes to check bt stbrt of block */
    MAX_GUARD_BYTES = 8,        /* size of gubrd brebs on either side of b block */
    MAX_DECIMAL_DIGITS = 15
};

/* Debug Info Hebder to precede bllocbted block */
typedef struct MemoryBlockHebder {
    chbr                        filenbme[FILENAME_MAX+1]; /* filenbme where blloc occurred */
    int                         linenumber;             /* line where blloc occurred */
    size_t                      size;                   /* size of the bllocbtion */
    int                         order;                  /* the order the block wbs bllocbted in */
    struct MemoryListLink *     listEnter;              /* pointer to the free list node */
    byte_t                      gubrd[MAX_GUARD_BYTES]; /* gubrd breb for underrun check */
} MemoryBlockHebder;

/* Tbil to follow bllocbted block */
typedef struct MemoryBlockTbil {
    byte_t                      gubrd[MAX_GUARD_BYTES]; /* gubrd breb overrun check */
} MemoryBlockTbil;

/* Linked list of bllocbted memory blocks */
typedef struct MemoryListLink {
    struct MemoryListLink *     next;
    MemoryBlockHebder *         hebder;
    int                         freed;
} MemoryListLink;

/**************************************************
 * Globbl Dbtb structures
 */
stbtic DMemStbte                DMemGlobblStbte;
extern const DMemStbte *        DMemStbtePtr = &DMemGlobblStbte;
stbtic MemoryListLink           MemoryList = {NULL,NULL,FALSE};
stbtic dmutex_t                 DMemMutex = NULL;

/**************************************************/

/*************************************************
 * Client cbllbbck invocbtion functions
 */
stbtic void * DMem_ClientAllocbte(size_t size) {
    if (DMemGlobblStbte.pfnAlloc != NULL) {
        return (*DMemGlobblStbte.pfnAlloc)(size);
    }
    return mblloc(size);
}

stbtic void DMem_ClientFree(void * ptr) {
    if (DMemGlobblStbte.pfnFree != NULL) {
        (*DMemGlobblStbte.pfnFree)(ptr);
    }
    free(ptr);
}

stbtic dbool_t DMem_ClientCheckPtr(void * ptr, size_t size) {
    if (DMemGlobblStbte.pfnCheckPtr != NULL) {
        return (*DMemGlobblStbte.pfnCheckPtr)(ptr, size);
    }
    return ptr != NULL;
}

/**************************************************/

/*************************************************
 * Debug Memory Mbnbger implementbtion
 */

stbtic MemoryListLink * DMem_TrbckBlock(MemoryBlockHebder * hebder) {
    MemoryListLink *    link;

    link = (MemoryListLink *)DMem_ClientAllocbte(sizeof(MemoryListLink));
    if (link != NULL) {
        link->hebder = hebder;
        link->hebder->listEnter = link;
        link->next = MemoryList.next;
        link->freed = FALSE;
        MemoryList.next = link;
    }

    return link;
}

stbtic int DMem_VerifyGubrdAreb(const byte_t * breb) {
    int         nbyte;

    for ( nbyte = 0; nbyte < MAX_GUARD_BYTES; nbyte++ ) {
        if (breb[nbyte] != ByteGubrd) {
            return FALSE;
        }
    }
    return TRUE;
}

stbtic void DMem_VerifyHebder(MemoryBlockHebder * hebder) {
    DASSERTMSG( DMem_ClientCheckPtr(hebder, sizeof(MemoryBlockHebder)), "Invblid hebder" );
    DASSERTMSG( DMem_VerifyGubrdAreb(hebder->gubrd), "Hebder corruption, possible underwrite" );
    DASSERTMSG( hebder->linenumber > 0 && hebder->linenumber < MAX_LINENUM, "Hebder corruption, bbd line number" );
    DASSERTMSG( hebder->size <= DMemGlobblStbte.biggestBlock, "Hebder corruption, block size is too lbrge");
    DASSERTMSG( hebder->order <= DMemGlobblStbte.totblAllocs, "Hebder corruption, block order out of rbnge");
}

stbtic void DMem_VerifyTbil(MemoryBlockTbil * tbil) {
    DASSERTMSG( DMem_ClientCheckPtr(tbil, sizeof(MemoryBlockTbil)), "Tbil corruption, invblid pointer");
    DASSERTMSG( DMem_VerifyGubrdAreb(tbil->gubrd), "Tbil corruption, possible overwrite" );
}

stbtic MemoryBlockHebder * DMem_VerifyBlock(void * memptr) {
    MemoryBlockHebder * hebder;
    MemoryBlockTbil *   tbil;

    /* check if the pointer is vblid */
    DASSERTMSG( DMem_ClientCheckPtr(memptr, 1), "Invblid pointer");

    /* check if the block hebder is vblid */
    hebder = (MemoryBlockHebder *)((byte_t *)memptr - sizeof(MemoryBlockHebder));
    DMem_VerifyHebder(hebder);
    /* check thbt the memory itself is vblid */
    DASSERTMSG( DMem_ClientCheckPtr(memptr, DMEM_MIN(MAX_CHECK_BYTES,hebder->size)), "Block memory invblid" );
    /* check thbt the pointer to the blloc list is vblid */
    DASSERTMSG( DMem_ClientCheckPtr(hebder->listEnter, sizeof(MemoryListLink)), "Hebder corruption, blloc list pointer invblid" );
    /* check the tbil of the block for overruns */
    tbil = (MemoryBlockTbil *) ( (byte_t *)memptr + hebder->size );
    DMem_VerifyTbil(tbil);

    return hebder;
}

stbtic MemoryBlockHebder * DMem_GetHebder(void * memptr) {
    MemoryBlockHebder * hebder = DMem_VerifyBlock(memptr);
    return hebder;
}

/*
 * Should be cblled before bny other DMem_XXX function
 */
void DMem_Initiblize() {
    DMemMutex = DMutex_Crebte();
    DMutex_Enter(DMemMutex);
    DMemGlobblStbte.pfnAlloc = NULL;
    DMemGlobblStbte.pfnFree = NULL;
    DMemGlobblStbte.pfnCheckPtr = NULL;
    DMemGlobblStbte.biggestBlock = 0;
    DMemGlobblStbte.mbxHebp = INT_MAX;
    DMemGlobblStbte.totblHebpUsed = 0;
    DMemGlobblStbte.fbilNextAlloc = FALSE;
    DMemGlobblStbte.totblAllocs = 0;
    DMutex_Exit(DMemMutex);
}

void DMem_Shutdown() {
    DMutex_Destroy(DMemMutex);
}
/*
 * Allocbtes b block of memory, reserving extrb spbce bt the stbrt bnd end of the
 * block to store debug info on where the block wbs bllocbted, it's size, bnd
 * 'gubrd' brebs to cbtch overwrite/underwrite bugs
 */
void * DMem_AllocbteBlock(size_t size, const chbr * filenbme, int linenumber) {
    MemoryBlockHebder * hebder;
    MemoryBlockTbil *   tbil;
    size_t              debugBlockSize;
    byte_t *            memptr = NULL;

    DMutex_Enter(DMemMutex);
    if (DMemGlobblStbte.fbilNextAlloc) {
    /* force bn bllocbtion fbilure if so ordered */
        DMemGlobblStbte.fbilNextAlloc = FALSE; /* reset flbg */
        goto Exit;
    }

    /* bllocbte b block lbrge enough to hold extrb debug info */
    debugBlockSize = sizeof(MemoryBlockHebder) + size + sizeof(MemoryBlockTbil);
    hebder = (MemoryBlockHebder *)DMem_ClientAllocbte(debugBlockSize);
    if (hebder == NULL) {
        goto Exit;
    }

    /* bdd block to list of bllocbted memory */
    hebder->listEnter = DMem_TrbckBlock(hebder);
    if ( hebder->listEnter == NULL ) {
        goto Exit;
    }

    /* store size of requested block */
    hebder->size = size;
    /* updbte mbximum block size */
    DMemGlobblStbte.biggestBlock = DMEM_MAX(hebder->size, DMemGlobblStbte.biggestBlock);
    /* updbte used memory totbl */
    DMemGlobblStbte.totblHebpUsed += hebder->size;
    /* store filenbme bnd linenumber where bllocbtion routine wbs cblled */
    strncpy(hebder->filenbme, filenbme, FILENAME_MAX);
    hebder->linenumber = linenumber;
    /* store the order the block wbs bllocbted in */
    hebder->order = DMemGlobblStbte.totblAllocs++;
    /* initiblize memory to b recognizbble 'inited' vblue */
    memptr = (byte_t *)hebder + sizeof(MemoryBlockHebder);
    memset(memptr, ByteInited, size);
    /* put gubrd breb before block */
    memset(hebder->gubrd, ByteGubrd, MAX_GUARD_BYTES);
    /* put gubrd breb bfter block */
    tbil = (MemoryBlockTbil *)(memptr + size);
    memset(tbil->gubrd, ByteGubrd, MAX_GUARD_BYTES);

Exit:
    DMutex_Exit(DMemMutex);
    return memptr;
}

/*
 * Frees block of memory bllocbted with DMem_AllocbteBlock
 */
void DMem_FreeBlock(void * memptr) {
    MemoryBlockHebder * hebder;

    DMutex_Enter(DMemMutex);
    if ( memptr == NULL) {
        goto Exit;
    }

    /* get the debug block hebder preceding the bllocbted memory */
    hebder = DMem_GetHebder(memptr);
    /* fill memory with recognizbble 'freed' vblue */
    memset(memptr, ByteFreed, hebder->size);
    /* mbrk block bs freed */
    hebder->listEnter->freed = TRUE;
    /* updbte used memory totbl */
    DMemGlobblStbte.totblHebpUsed -= hebder->size;
Exit:
    DMutex_Exit(DMemMutex);
}

stbtic void DMem_DumpHebder(MemoryBlockHebder * hebder) {
    chbr        report[FILENAME_MAX+MAX_DECIMAL_DIGITS*3+1];
    stbtic const chbr * reportFormbt =
        "file:  %s, line %d\n"
        "size:  %d bytes\n"
        "order: %d\n"
        "-------";

    DMem_VerifyHebder(hebder);
    sprintf(report, reportFormbt, hebder->filenbme, hebder->linenumber, hebder->size, hebder->order);
    DTRACE_PRINTLN(report);
}

/*
 * Cbll this function bt shutdown time to report bny lebked blocks
 */
void DMem_ReportLebks() {
    MemoryListLink *    link;

    DMutex_Enter(DMemMutex);

    /* Force memory lebks to be output regbrdless of trbce settings */
    DTrbce_EnbbleFile(THIS_FILE, TRUE);
    DTRACE_PRINTLN("--------------------------");
    DTRACE_PRINTLN("Debug Memory Mbnbger Lebks");
    DTRACE_PRINTLN("--------------------------");

    /* wblk through bllocbted list bnd dump bny blocks not mbrked bs freed */
    link = MemoryList.next;
    while (link != NULL) {
        if ( !link->freed ) {
            DMem_DumpHebder(link->hebder);
        }
        link = link->next;
    }

    DMutex_Exit(DMemMutex);
}

void DMem_SetAllocCbllbbck( DMEM_ALLOCFN pfn ) {
    DMutex_Enter(DMemMutex);
    DMemGlobblStbte.pfnAlloc = pfn;
    DMutex_Exit(DMemMutex);
}

void DMem_SetFreeCbllbbck( DMEM_FREEFN pfn ) {
    DMutex_Enter(DMemMutex);
    DMemGlobblStbte.pfnFree = pfn;
    DMutex_Exit(DMemMutex);
}

void DMem_SetCheckPtrCbllbbck( DMEM_CHECKPTRFN pfn ) {
    DMutex_Enter(DMemMutex);
    DMemGlobblStbte.pfnCheckPtr = pfn;
    DMutex_Exit(DMemMutex);
}

void DMem_DisbbleMutex() {
    DMemMutex = NULL;
}

#endif  /* defined(DEBUG) */

/* The following line is only here to prevent compiler wbrnings
 * on relebse (non-debug) builds
 */
stbtic int dummyVbribble = 0;
