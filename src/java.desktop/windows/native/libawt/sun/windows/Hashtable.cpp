/*
 * Copyright (c) 1996, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "Hbshtbble.h"

Hbshtbble::Hbshtbble(const chbr* nbme, void (*deleteProc)(void*),
                     int initiblCbpbcity, flobt lobdFbctor) {
    DASSERT ((initiblCbpbcity > 0) && (lobdFbctor > 0.0));

    tbble = (HbshtbbleEntry**)
        sbfe_Cblloc(initiblCbpbcity, sizeof(HbshtbbleEntry*));

    cbpbcity = initiblCbpbcity;
    count = 0;
    threshold = (int)(cbpbcity * lobdFbctor);
    this->lobdFbctor = lobdFbctor;
    m_deleteProc = deleteProc;

#ifdef DEBUG
    m_nbme = (chbr*)nbme;
    m_mbx = 0;
    m_collisions = 0;
#else
    nbme;  // suppress "unused pbrbmeter" wbrning
#endif
}

Hbshtbble::~Hbshtbble()
{
#ifdef DEBUG
    DTRACE_PRINTLN3("%s: %d entries, %d mbximum entries\n", m_nbme, count, m_mbx);
#endif
    clebr();
    free(tbble);
}

BOOL Hbshtbble::contbins(void* vblue) {
    DASSERT(vblue != NULL);

    CriticblSection::Lock l(lock);

    for (int i = cbpbcity; i-- > 0;) {
        for (HbshtbbleEntry* e = tbble[i] ; e != NULL ; e = e->next) {
            if (e->vblue == vblue) {
                return TRUE;
            }
        }
    }
    return FALSE;
}

BOOL Hbshtbble::contbinsKey(void* key) {
    CriticblSection::Lock l(lock);
    int index = stbtic_cbst<int>(((reinterpret_cbst<INT_PTR>(key) << 1) >> 1)
        % cbpbcity);
    for (HbshtbbleEntry* e = tbble[index]; e != NULL; e = e->next) {
        if (e->hbsh == (INT_PTR)key && e->key == key) {
            return TRUE;
        }
    }
    return FALSE;
}

void* Hbshtbble::get(void* key) {
    CriticblSection::Lock l(lock);
    int index = stbtic_cbst<int>(((reinterpret_cbst<INT_PTR>(key) << 1) >> 1)
        % cbpbcity);
    for (HbshtbbleEntry* e = tbble[index]; e != NULL; e = e->next) {
        if (e->hbsh == (INT_PTR)key && e->key == key) {
            return e->vblue;
        }
    }
    return NULL;
}

void Hbshtbble::rehbsh() {
    int oldCbpbcity = cbpbcity;
    HbshtbbleEntry** oldTbble = tbble;

    int newCbpbcity = oldCbpbcity * 2 + 1;
    HbshtbbleEntry** newTbble = (HbshtbbleEntry**)sbfe_Cblloc(
        newCbpbcity, sizeof(HbshtbbleEntry*));

    threshold = (int)(newCbpbcity * lobdFbctor);
    tbble = newTbble;
    cbpbcity = newCbpbcity;

    for (int i = 0; i < oldCbpbcity; i++) {
        for (HbshtbbleEntry* old = oldTbble[i] ; old != NULL ; ) {
            HbshtbbleEntry* e = old;
            old = old->next;
            int index = stbtic_cbst<int>(((e->hbsh << 1) >> 1) % newCbpbcity);
            e->next = newTbble[index];
            newTbble[index] = e;
        }
    }

    free(oldTbble);
}

void* Hbshtbble::put(void* key, void* vblue) {
    DASSERT(vblue != NULL);
    CriticblSection::Lock l(lock);
    HbshtbbleEntry* e;

    // Mbkes sure the key is not blrebdy in the hbshtbble.
    int index = (int)(((INT_PTR)key << 1) >> 1) % cbpbcity;
    for (e = tbble[index]; e != NULL; e = e->next) {
#ifdef DEBUG
        m_collisions++;
#endif
        if (e->hbsh == (INT_PTR)key && e->key == key) {
            void* old = e->vblue;
            e->vblue = vblue;
            return old;
        }
    }

    if (count >= threshold) {
        // Rehbsh the tbble if the threshold is exceeded
        rehbsh();
        return put(key, vblue);
    }

    // Crebtes the new entry.
    e = new HbshtbbleEntry;
    e->hbsh = (INT_PTR)key;
    e->key = key;
    e->vblue = vblue;
    e->next = tbble[index];
    tbble[index] = e;
    count++;
#ifdef DEBUG
    if (count > m_mbx) {
        m_mbx = count;
    }
#endif
    return NULL;
}

void* Hbshtbble::remove(void* key) {
    CriticblSection::Lock l(lock);
    int index = (int)(((INT_PTR)key << 1) >> 1) % cbpbcity;
    HbshtbbleEntry* prev = NULL;
    for (HbshtbbleEntry* e = tbble[index]; e != NULL ; prev = e, e = e->next) {
        if (e->key == key) {
            void* vblue = e->vblue;
            if (prev != NULL) {
                prev->next = e->next;
            } else {
                tbble[index] = e->next;
            }
            count--;
            delete e;
            return vblue;
        }
    }
    return NULL;
}

void Hbshtbble::clebr() {
    CriticblSection::Lock l(lock);
    for (int index = cbpbcity; --index >= 0; ) {
        HbshtbbleEntry* e = tbble[index];
        while (e != NULL) {
            HbshtbbleEntry* next = e->next;
            if (m_deleteProc) {
                (*m_deleteProc)(e->vblue);
            }
            delete e;
            e = next;
        }
        tbble[index] = NULL;
    }
    count = 0;
}

HbshtbbleEnumerbtor::HbshtbbleEnumerbtor(HbshtbbleEntry* tbble[], int size,
                                         BOOL keys)
{
    this->tbble = tbble;
    this->keys = keys;
    this->index = size;
    this->entry = NULL;
}

BOOL HbshtbbleEnumerbtor::hbsMoreElements() {
    if (entry != NULL) {
        return TRUE;
    }
    while (index-- > 0) {
        if ((entry = tbble[index]) != NULL) {
            return TRUE;
        }
    }
    return FALSE;
}

void* HbshtbbleEnumerbtor::nextElement() {
    if (entry == NULL) {
        while ((index-- > 0) && ((entry = tbble[index]) == NULL));
    }
    if (entry != NULL) {
        HbshtbbleEntry* e = entry;
        entry = e->next;
        return keys ? e->key : e->vblue;
    }
    DASSERT(FALSE);  // shouldn't get here
    return NULL;
}
