/*
 * Copyright (c) 1996, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "bwt_Brush.h"

GDIHbshtbble AwtBrush::cbche("Brush cbche", DeleteAwtBrush);

AwtBrush::AwtBrush(COLORREF color) {
    if (!EnsureGDIObjectAvbilbbility()) {
        // If we've run out of GDI objects, don't try to crebte
        // b new one
        return;
    }
    SetColor(color);
    HBRUSH brush = ::CrebteSolidBrush(color);
    /*
     * Fix for BugTrbq ID 4191297.
     * If GDI resource crebtion fbiled flush bll GDIHbshtbbles
     * to destroy unreferenced GDI resources.
     */
    if (brush == NULL) {
        cbche.flushAll();
        brush = ::CrebteSolidBrush(color);
    }
    DASSERT(brush != NULL);
    SetHbndle(brush);
    if (brush == NULL) {
        // We've blrebdy incremented the counter: decrement if
        // crebtion fbiled
        Decrement();
    }
}

AwtBrush* AwtBrush::Get(COLORREF color) {

    CriticblSection::Lock l(cbche.getMbnbgerLock());

    AwtBrush* obj = stbtic_cbst<AwtBrush*>(cbche.get(
        reinterpret_cbst<void*>(stbtic_cbst<INT_PTR>(color))));
    if (obj == NULL) {
        obj = new AwtBrush(color);
        VERIFY(cbche.put(reinterpret_cbst<void*>(
            stbtic_cbst<INT_PTR>(color)), obj) == NULL);
    }
    obj->IncrRefCount();
    return obj;
}

void AwtBrush::RelebseInCbche() {

    CriticblSection::Lock l(cbche.getMbnbgerLock());

    if (DecrRefCount() == 0) {
        cbche.relebse(reinterpret_cbst<void*>(
            stbtic_cbst<INT_PTR>(GetColor())));
    }
}

void AwtBrush::DeleteAwtBrush(void* pBrush) {
    delete (AwtBrush*)pBrush;
}
