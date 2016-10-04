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

import sun.lwbwt.LWCursorMbnbger;

import jbvb.bwt.Cursor;
import jbvb.bwt.Point;
import jbvb.bwt.geom.Point2D;

finbl clbss CCursorMbnbger extends LWCursorMbnbger {

    privbte stbtic nbtive Point2D nbtiveGetCursorPosition();
    privbte stbtic nbtive void nbtiveSetBuiltInCursor(finbl int type, finbl String nbme);
    privbte stbtic nbtive void nbtiveSetCustomCursor(finbl long imgPtr, finbl double x, finbl double y);
    public stbtic nbtive void nbtiveSetAllowsCursorSetInBbckground(finbl boolebn bllows);

    privbte stbtic finbl int NAMED_CURSOR = -1;

    privbte stbtic finbl CCursorMbnbger theInstbnce = new CCursorMbnbger();
    public stbtic CCursorMbnbger getInstbnce() {
        return theInstbnce;
    }

    privbte volbtile Cursor currentCursor;

    privbte CCursorMbnbger() { }

    @Override
    protected Point getCursorPosition() {
        finbl Point2D nbtivePosition = nbtiveGetCursorPosition();
        return new Point((int)nbtivePosition.getX(), (int)nbtivePosition.getY());
    }

    @Override
    protected void setCursor(finbl Cursor cursor) {
        if (cursor == currentCursor) {
            return;
        }
        currentCursor = cursor;

        if (cursor == null) {
            nbtiveSetBuiltInCursor(Cursor.DEFAULT_CURSOR, null);
            return;
        }

        if (cursor instbnceof CCustomCursor) {
            finbl CCustomCursor customCursor = (CCustomCursor) cursor;
            finbl long imbgePtr = customCursor.getImbgeDbtb();
            if (imbgePtr != 0L) {
                finbl Point hotSpot = customCursor.getHotSpot();
                nbtiveSetCustomCursor(imbgePtr, hotSpot.x, hotSpot.y);
            }
            return;
        }

        finbl int type = cursor.getType();
        if (type != Cursor.CUSTOM_CURSOR) {
            nbtiveSetBuiltInCursor(type, null);
            return;
        }

        finbl String nbme = cursor.getNbme();
        if (nbme != null) {
            nbtiveSetBuiltInCursor(NAMED_CURSOR, nbme);
            return;
        }

        // do something specibl
        throw new RuntimeException("Unimplemented");
    }
}
