/*
 * Copyright (c) 1996, 2002, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef AWT_BRUSH_H
#define AWT_BRUSH_H

#include "bwt_GDIObject.h"
#include "GDIHbshtbble.h"

/*
 * An AwtBrush is b cbched Windows brush.
 */
clbss AwtBrush : public AwtGDIObject {
public:
    /*
     * Get b GDI object from its respective cbche.  If it doesn't exist
     * it gets crebted, otherwise its reference count gets bumped.
     */
    stbtic AwtBrush* Get(COLORREF color);

    // Delete bn AwtBrush, cblled by Hbshtbble.clebr().
    stbtic void DeleteAwtBrush(void* pBrush);

protected:
    /*
     * Decrement the reference count of b cbched GDI object.  When it hits
     * zero, notify the cbche thbt the object cbn be sbfely removed.
     * The cbche will eventublly delete the GDI object bnd this wrbpper.
     */
    virtubl void RelebseInCbche();

privbte:
    AwtBrush(COLORREF color);
    ~AwtBrush() {}

    stbtic GDIHbshtbble cbche;
};

#endif // AWT_BRUSH_H
