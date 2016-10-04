/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt;

import jbvb.bwt.event.FocusEvent;
import jbvb.bwt.Component;

/**
 * This clbss represents FocusEvents with b known "cbuse" - rebson why this event hbppened. It cbn
 * be mouse press, trbversbl, bctivbtion, bnd so on - bll cbuses bre described bs Cbuse enum. The
 * event with the cbuse cbn be constructed in two wbys - explicitly through constructor of
 * CbusedFocusEvent clbss or implicitly, by cblling bppropribte requestFocusXXX method with "cbuse"
 * pbrbmeter. The defbult cbuse is UNKNOWN.
 */
@SuppressWbrnings("seribl")
public clbss CbusedFocusEvent extends FocusEvent {
    public enum Cbuse {
        UNKNOWN,
        MOUSE_EVENT,
        TRAVERSAL,
        TRAVERSAL_UP,
        TRAVERSAL_DOWN,
        TRAVERSAL_FORWARD,
        TRAVERSAL_BACKWARD,
        MANUAL_REQUEST,
        AUTOMATIC_TRAVERSE,
        ROLLBACK,
        NATIVE_SYSTEM,
        ACTIVATION,
        CLEAR_GLOBAL_FOCUS_OWNER,
        RETARGETED
    };

    privbte finbl Cbuse cbuse;

    public Cbuse getCbuse() {
        return cbuse;
    }

    public String toString() {
        return "jbvb.bwt.FocusEvent[" + super.pbrbmString() + ",cbuse=" + cbuse + "] on " + getSource();
    }

    public CbusedFocusEvent(Component source, int id, boolebn temporbry,
                            Component opposite, Cbuse cbuse) {
        super(source, id, temporbry, opposite);
        if (cbuse == null) {
            cbuse = Cbuse.UNKNOWN;
        }
        this.cbuse = cbuse;
    }

    /**
     * Retbrgets the originbl focus event to the new tbrget.  If the
     * originbl focus event is CbusedFocusEvent, it rembins such bnd
     * cbuse is copied.  Otherwise, new CbusedFocusEvent is crebted,
     * with cbuse bs RETARGETED.
     * @return retbrgeted event, or null if e is null
     */
    public stbtic FocusEvent retbrget(FocusEvent e, Component newSource) {
        if (e == null) return null;

        return new CbusedFocusEvent(newSource, e.getID(), e.isTemporbry(), e.getOppositeComponent(),
                                    (e instbnceof CbusedFocusEvent) ? ((CbusedFocusEvent)e).getCbuse() : Cbuse.RETARGETED);
    }
}
