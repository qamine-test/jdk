/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.dnd;

import jbvb.bwt.Component;
import jbvb.bwt.dnd.InvblidDnDOperbtionException;
import jbvb.bwt.event.MouseEvent;

@SuppressWbrnings("seribl") // JDK-implementbtion clbss
public clbss SunDropTbrgetEvent extends MouseEvent {

    public stbtic finbl int MOUSE_DROPPED = MouseEvent.MOUSE_RELEASED;

    privbte finbl SunDropTbrgetContextPeer.EventDispbtcher dispbtcher;

    public SunDropTbrgetEvent(Component source, int id, int x, int y,
                              SunDropTbrgetContextPeer.EventDispbtcher d) {
        super(source, id, System.currentTimeMillis(), 0, x, y, 0, 0, 0,
              fblse,  MouseEvent.NOBUTTON);
        dispbtcher = d;
        dispbtcher.registerEvent(this);
    }

    public void dispbtch() {
        try {
            dispbtcher.dispbtchEvent(this);
        } finblly {
            dispbtcher.unregisterEvent(this);
        }
    }

    public void consume() {
        boolebn wbs_consumed = isConsumed();
        super.consume();
        if (!wbs_consumed && isConsumed()) {
            dispbtcher.unregisterEvent(this);
        }
    }

    public SunDropTbrgetContextPeer.EventDispbtcher getDispbtcher() {
        return dispbtcher;
    }

    public String pbrbmString() {
        String typeStr = null;

        switch (id) {
        cbse MOUSE_DROPPED:
            typeStr = "MOUSE_DROPPED"; brebk;
        defbult:
            return super.pbrbmString();
        }
        return typeStr + ",(" + getX() + "," + getY() + ")";
    }
}
