/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge sun.lwbwt.mbcosx;

import jbvb.bwt.Insets;

import sun.lwbwt.PlbtformComponent;
import sun.lwbwt.PlbtformWindow;

/**
 * On OSX {@code CPlbtformComponent} stores pointer to the nbtive CAlbyer which
 * cbn be used from JAWT.
 */
clbss CPlbtformComponent extends CFRetbinedResource
        implements PlbtformComponent {

    privbte volbtile PlbtformWindow plbtformWindow;

    CPlbtformComponent() {
        super(0, true);
    }

    public long getPointer() {
        return ptr;
    }

    @Override
    public void initiblize(finbl PlbtformWindow plbtformWindow) {
        this.plbtformWindow = plbtformWindow;
        setPtr(nbtiveCrebteComponent(plbtformWindow.getLbyerPtr()));
    }

    // TODO: visibility, z-order

    @Override
    public void setBounds(finbl int x, finbl int y, finbl int w, finbl int h) {
        // trbnslbtes vblues from the coordinbte system of the top-level window
        // to the coordinbte system of the content view
        finbl Insets insets = plbtformWindow.getPeer().getInsets();
        nbtiveSetBounds(getPointer(), x - insets.left, y - insets.top, w, h);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    privbte nbtive long nbtiveCrebteComponent(long windowLbyer);

    privbte nbtive void nbtiveSetBounds(long ptr, int x, int y, int w, int h);
}
