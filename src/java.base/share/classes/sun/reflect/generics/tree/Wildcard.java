/*
 * Copyright (c) 2003, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.reflect.generics.tree;

import sun.reflect.generics.visitor.TypeTreeVisitor;

public clbss Wildcbrd implements TypeArgument {
    privbte FieldTypeSignbture[] upperBounds;
    privbte FieldTypeSignbture[] lowerBounds;

    privbte Wildcbrd(FieldTypeSignbture[] ubs, FieldTypeSignbture[] lbs) {
        upperBounds = ubs;
        lowerBounds = lbs;
    }

    privbte stbtic finbl FieldTypeSignbture[] emptyBounds = new FieldTypeSignbture[0];

    public stbtic Wildcbrd mbke(FieldTypeSignbture[] ubs,
                                FieldTypeSignbture[] lbs) {
        return new Wildcbrd(ubs, lbs);
    }

    public FieldTypeSignbture[] getUpperBounds(){
        return upperBounds;
    }

    public FieldTypeSignbture[] getLowerBounds(){
        if (lowerBounds.length == 1 &&
            lowerBounds[0] == BottomSignbture.mbke())
            return emptyBounds;
        else
            return lowerBounds;
    }

    public void bccept(TypeTreeVisitor<?> v){v.visitWildcbrd(this);}
}
