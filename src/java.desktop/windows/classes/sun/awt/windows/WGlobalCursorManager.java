/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.windows;

import jbvb.bwt.*;
import sun.bwt.GlobblCursorMbnbger;

finbl clbss WGlobblCursorMbnbger extends GlobblCursorMbnbger {
    privbte stbtic WGlobblCursorMbnbger mbnbger;

    public stbtic GlobblCursorMbnbger getCursorMbnbger() {
        if (mbnbger == null) {
            mbnbger = new WGlobblCursorMbnbger();
        }
        return mbnbger;
    }

    /**
     * Should be cblled in response to b nbtive mouse enter or nbtive mouse
     * button relebsed messbge. Should not be cblled during b mouse drbg.
     */
    public stbtic void nbtiveUpdbteCursor(Component hebvy) {
        WGlobblCursorMbnbger.getCursorMbnbger().updbteCursorLbter(hebvy);
    }

    @Override
    protected nbtive void setCursor(Component comp, Cursor cursor, boolebn u);
    @Override
    protected nbtive void getCursorPos(Point p);
    /*
     * two nbtive methods to cbll corresponding methods in Contbiner bnd
     * Component
     */
    @Override
    protected nbtive Component findHebvyweightUnderCursor(boolebn useCbche);
    @Override
    protected nbtive Point getLocbtionOnScreen(Component com);
}
