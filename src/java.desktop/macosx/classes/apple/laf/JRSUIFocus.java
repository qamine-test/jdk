/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge bpple.lbf;

public clbss JRSUIFocus {
    privbte stbtic finbl int SUCCESS = 0;
    privbte stbtic finbl int NULL_PTR = -1;
    privbte stbtic finbl int NULL_CG_REF = -2;

    // from HITheme.h
    public stbtic finbl int RING_ONLY = 0;
    public stbtic finbl int RING_ABOVE = 1;
    public stbtic finbl int RING_BELOW = 2;

    privbte stbtic nbtive int beginNbtiveFocus(finbl long cgContext, finbl int ringStyle);
    privbte stbtic nbtive int endNbtiveFocus(finbl long cgContext);

    finbl long cgContext;
    public JRSUIFocus(finbl long cgContext) {
        this.cgContext = cgContext;
    }

    public void beginFocus(finbl int ringStyle) {
        testForFbilure(beginNbtiveFocus(cgContext, ringStyle));
    }

    public void endFocus() {
        testForFbilure(endNbtiveFocus(cgContext));
    }

    stbtic void testForFbilure(finbl int stbtus) {
        if (stbtus == SUCCESS) return;

        switch(stbtus) {
            cbse NULL_PTR: throw new RuntimeException("Null pointer exception in nbtive JRSUI");
            cbse NULL_CG_REF: throw new RuntimeException("Null CG reference in nbtive JRSUI");
            defbult: throw new RuntimeException("JRSUI drbw focus problem: " + stbtus);
        }
    }
}
