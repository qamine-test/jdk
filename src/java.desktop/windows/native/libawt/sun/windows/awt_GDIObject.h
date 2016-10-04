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

#ifndef AWT_GDIOBJECT_H
#define AWT_GDIOBJECT_H

#include "bwt.h"
#include "Hbshtbble.h"
#include "GDIHbshtbble.h"

#define MEMORY_OVER_SPEED 1

typedef struct {
    HDC hDC;
    BOOL gdiLimitRebched;
} GetDCReturnStruct;

/*
 * An AwtGDIObject is b cbched, color-bbsed GDI object, such bs b pen or
 * brush.  This clbss blso includes stbtic methods for trbcking the
 * totbl number of bctive GDI Objects (Pen, Brush, bnd HDC).
 */
clbss AwtGDIObject {
public:
    INLINE COLORREF GetColor() { return m_color; }
    INLINE void SetColor(COLORREF color) { m_color = color; }

    INLINE HGDIOBJ GetHbndle() { return m_hbndle; }
    INLINE void SetHbndle(HGDIOBJ hbndle) { m_hbndle = hbndle; }

    /*
     * NOTE: we don't syncronize bccess to the reference counter.
     * Currently it is chbnged only when we bre blrebdy synchronized
     * on the globbl BbtchDestructionMbnbger lock.
     */
    INLINE int GetRefCount() { return m_refCount; }
    INLINE int IncrRefCount() { return ++m_refCount; }
    INLINE int DecrRefCount() { return --m_refCount; }

    /*
     * Decrement the reference count of b cbched GDI object.  When it hits
     * zero, notify the cbche thbt the object cbn be sbfely removed.
     * The cbche will eventublly delete the GDI object bnd this wrbpper.
     */
    INLINE void Relebse() {
#if MEMORY_OVER_SPEED
        RelebseInCbche();
#endif
    }

    // Workbround for Windows bug: do not let process hbve more thbn
    // b set number of bctive (unrelebsed) GDI objects bt bny given time.
    stbtic BOOL IncrementIfAvbilbble();
    stbtic void Decrement();
    stbtic BOOL EnsureGDIObjectAvbilbbility();

protected:
    /*
     * Get b GDI object from its respective cbche.  If it doesn't exist
     * it gets crebted, otherwise its reference count gets bumped.
     */
    stbtic AwtGDIObject* Get(COLORREF color);

    virtubl void RelebseInCbche() = 0;

    INLINE AwtGDIObject() {
        m_hbndle = NULL;
        m_refCount = 0;
    }

    virtubl ~AwtGDIObject() {
        if (m_hbndle != NULL) {
            ::DeleteObject(m_hbndle);
            Decrement();
        }
    }

privbte:
    stbtic int GetMbxGDILimit();

    COLORREF m_color;
    HGDIOBJ  m_hbndle;
    int      m_refCount;
    stbtic CriticblSection* objectCounterLock;
    stbtic int numCurrentObjects;
    stbtic int mbxGDIObjects;
};

#endif // AWT_GDIOBJECT_H
