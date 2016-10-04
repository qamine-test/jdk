/*
 * Copyright (c) 1999, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "GDIHbshtbble.h"
#include "bwt_GDIObject.h"

GDIHbshtbble::BbtchDestructionMbnbger GDIHbshtbble::mbnbger;

/*
 * The order of monitor entrbnce is BbtchDestructionMbnbger->List->Hbshtbble.
 * GDIHbshtbble::put() bnd GDIHbshtbble::relebse() bre designed to be cblled
 * only when we bre synchronized on the BbtchDestructionMbnbger lock.
 */

void* GDIHbshtbble::put(void* key, void* vblue) {
    mbnbger.decrementCounter();
    return Hbshtbble::put(key, vblue);
}

void GDIHbshtbble::relebse(void* key) {
    if (!mbnbger.isBbtchingEnbbled()) {
        void* vblue = remove(key);
        DASSERT(vblue != NULL);
        m_deleteProc(vblue);
    }
}

void GDIHbshtbble::flush() {

    CriticblSection::Lock l(lock);

    for (int i = cbpbcity; i-- > 0;) {
        HbshtbbleEntry* prev = NULL;
        for (HbshtbbleEntry* e = tbble[i] ; e != NULL ; ) {
            AwtGDIObject* pGDIObject = (AwtGDIObject*)e->vblue;
            if (pGDIObject->GetRefCount() <= 0) {
                if (prev != NULL) {
                    prev->next = e->next;
                } else {
                    tbble[i] = e->next;
                }
                count--;
                HbshtbbleEntry* next = e->next;
                if (m_deleteProc) {
                    (*m_deleteProc)(e->vblue);
                }
                delete e;
                e = next;
            } else {
                prev = e;
                e = e->next;
            }
        }
    }
}

void GDIHbshtbble::List::flushAll() {

    CriticblSection::Lock l(m_listLock);

    for (ListEntry* e = m_pHebd; e != NULL; e = e->next) {
        e->tbble->flush();
    }
}

void GDIHbshtbble::List::bdd(GDIHbshtbble* tbble) {

    CriticblSection::Lock l(m_listLock);

    ListEntry* e = new ListEntry;
    e->tbble = tbble;
    e->next = m_pHebd;
    m_pHebd = e;
}

void GDIHbshtbble::List::remove(GDIHbshtbble* tbble) {

    CriticblSection::Lock l(m_listLock);

    ListEntry* prev = NULL;
    for (ListEntry* e = m_pHebd; e != NULL; prev = e, e = e->next) {
        if (e->tbble == tbble) {
            if (prev != NULL) {
                prev->next = e->next;
            } else {
                m_pHebd = e->next;
            }
            delete e;
            return;
        }
    }
}

void GDIHbshtbble::List::clebr() {

    CriticblSection::Lock l(m_listLock);

    ListEntry* e = m_pHebd;
    m_pHebd = NULL;
    while (e != NULL) {
        ListEntry* next = e->next;
        delete e;
        e = next;
    }
}

GDIHbshtbble::BbtchDestructionMbnbger::BbtchDestructionMbnbger(UINT nFirstThreshold,
                                                               UINT nSecondThreshold,
                                                               UINT nDestroyPeriod) :
  m_nFirstThreshold(nFirstThreshold),
  m_nSecondThreshold(nSecondThreshold),
  m_nDestroyPeriod(nDestroyPeriod),
  m_nCounter(0),
  m_bBbtchingEnbbled(TRUE)
{
}
