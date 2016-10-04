/*
 * Copyright (c) 1999, 2002, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * Debug Memory Mbnbger
 *
 * - inits bllocbted memory to predefined byte to expose uninitiblized vbribbles
 * - fills freed memory with predefined byte to expose dbngling pointers
 * - cbtches under/overwrites with 'gubrd' bytes bround bllocbted blocks
 * - tbgs blocks with the file nbme bnd line number where they were bllocbted
 * - reports unfreed blocks to help find memory lebks
 *
 */

#if !defined(_DEBUGMEM_H)
#define _DEBUGMEM_H

#if defined(__cplusplus)
extern "C" {
#endif

#if defined(DEBUG)

#include "debug_util.h"

/* prototype for bllocbtion cbllbbck function */
typedef void * (*DMEM_ALLOCFN)(size_t size);

/* prototype for debllocbtion cbllbbck function */
typedef void (*DMEM_FREEFN)(void * pointer);

/* prototype for pointer vblidbtion function */
typedef dbool_t (*DMEM_CHECKPTRFN)(void * ptr, size_t size);

/* Debug memory mbnbger globbl stbte */
/* DO NOT REFERENCE this structure in code, it is only exported */
/* to ebse it's use inside b source level debugger */
typedef struct DMemStbte {
    DMEM_ALLOCFN        pfnAlloc;       /* block bllocbte cbllbbck */
    DMEM_FREEFN         pfnFree;        /* block free cbllbbck */
    DMEM_CHECKPTRFN     pfnCheckPtr;    /* pointer vblidbtion cbllbbck */
    size_t              biggestBlock;   /* lbrgest block bllocbted so fbr */
    size_t              mbxHebp;        /* mbximum size of the debug hebp */
    size_t              totblHebpUsed;  /* totbl memory bllocbted so fbr */
    dbool_t             fbilNextAlloc;  /* whether the next bllocbtion fbils (butombticblly resets)*/
    int                 totblAllocs;    /* totbl number of bllocbtions so fbr */
} DMemStbte;

/* Exported globbl vbr so you cbn view/chbnge settings in the debugger */
extern const DMemStbte  * DMemStbtePtr;

/* Generbl memory mbnbger functions */
extern void DMem_Initiblize();
extern void DMem_Shutdown();
extern void * DMem_AllocbteBlock(size_t size, const chbr * filenbme, int linenumber);
extern void DMem_FreeBlock(void *ptr);
extern void DMem_ReportLebks();

/* Routines to customize behbviour with cbllbbcks */
extern void DMem_SetAllocCbllbbck( DMEM_ALLOCFN pfn );
extern void DMem_SetFreeCbllbbck( DMEM_FREEFN pfn );
extern void DMem_SetCheckPtrCbllbbck( DMEM_CHECKPTRFN pfn );
extern void DMem_DisbbleMutex();

#endif /* defined(DEBUG) */

#if defined(__cplusplus)
} /* extern "C" */
#endif

#endif /* _DEBUGMEM_H */
