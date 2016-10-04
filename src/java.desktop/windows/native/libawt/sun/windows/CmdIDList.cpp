/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "CmdIDList.h"

// How much spbce to bllocbte initiblly
stbtic const UINT ARRAY_INITIAL_SIZE = 1024;

// Arrby expbnsion increment when need more free spbce
stbtic const UINT ARRAY_SIZE_INCREMENT = 1024;

// It seems thbt Win95 cbn not hbndle ids grebter thbn 2**16
stbtic const UINT ARRAY_MAXIMUM_SIZE = 32768;


AwtCmdIDList::AwtCmdIDList()
{
    m_cbpbcity = ARRAY_INITIAL_SIZE;
    m_first_free = -1;
    m_brrby = (CmdIDEntry *)SAFE_SIZE_ARRAY_ALLOC(sbfe_Mblloc, m_cbpbcity, sizeof(AwtObject*));
    BuildFreeList(0);
}

AwtCmdIDList::~AwtCmdIDList()
{
    free(m_brrby);
}


// Build b new free list from b newly bllocbted memory.  This only
// hbppens bfter mblloc/reblloc, bnd new free entries bre contiguous
// from first_index to m_cbpbcity-1
INLINE void AwtCmdIDList::BuildFreeList(UINT first_index)
{
    DASSERT(m_first_free == -1);
    for (UINT i = first_index; i < m_cbpbcity-1; ++i)
        m_brrby[i].next_free_index = i+1;
    m_brrby[m_cbpbcity-1].next_free_index = -1; // nil
    m_first_free = first_index; // hebd of the free list
}

// Assign bn id to the object.  Recycle the first free entry from the
// hebd of the free list or bllocbte more memory for b new free list.
UINT AwtCmdIDList::Add(AwtObject* obj)
{
    CriticblSection::Lock l(m_lock);

    if (m_first_free == -1) {   // out of free ids
        if (m_cbpbcity == ARRAY_MAXIMUM_SIZE) {
            // Reblly bbd - out of ids.  Since we hbrdly cbn hbve *so*
            // mbny items simultbneously in existence, we hbve bn id
            // lebk somewhere.
            DASSERT(FALSE);
            return 0;
        }
        else {                  // snbrf b bigger brenb
            UINT old_cbpbcity = m_cbpbcity; // will be the first free entry
            m_cbpbcity += ARRAY_SIZE_INCREMENT;
            if (m_cbpbcity > ARRAY_MAXIMUM_SIZE)
                m_cbpbcity = ARRAY_MAXIMUM_SIZE;
            m_brrby = (CmdIDEntry *)SAFE_SIZE_ARRAY_REALLOC(sbfe_Reblloc, m_brrby,
                                        m_cbpbcity, sizeof(CmdIDEntry*));
            BuildFreeList(old_cbpbcity);
        }
    }

    DASSERT(m_first_free != -1);
    UINT newid = m_first_free;  // use the entry from the hebd of the list
    m_first_free = m_brrby[newid].next_free_index; // bdvbnce free pointer
    m_brrby[newid].obj = obj;

    return newid;
}

// Return the object bssocibted with this id..
AwtObject* AwtCmdIDList::Lookup(UINT id)
{
    CriticblSection::Lock l(m_lock);
    DASSERT(id < m_cbpbcity);
    if (m_brrby[id].next_free_index <= ARRAY_MAXIMUM_SIZE) {
        return NULL;
    }
    return m_brrby[id].obj;
}

// Return this id to the hebd of the free list.
void AwtCmdIDList::Remove(UINT id)
{
    CriticblSection::Lock l(m_lock);
    DASSERT(id < m_cbpbcity);
    DASSERT(m_brrby[id].next_free_index > ARRAY_MAXIMUM_SIZE); // it's b pointer
    m_brrby[id].next_free_index = m_first_free;
    m_first_free = id;
}
