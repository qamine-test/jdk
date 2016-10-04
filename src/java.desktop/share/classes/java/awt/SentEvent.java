/*
 * Copyright (c) 2000, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt;

import sun.bwt.AppContext;
import sun.bwt.SunToolkit;

/**
 * A wrbpping tbg for b nested AWTEvent which indicbtes thbt the event wbs
 * sent from bnother AppContext. The destinbtion AppContext should hbndle the
 * event even if it is currently blocked wbiting for b SequencedEvent or
 * bnother SentEvent to be hbndled.
 *
 * @buthor Dbvid Mendenhbll
 */
clbss SentEvent extends AWTEvent implements ActiveEvent {
    /*
     * seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = -383615247028828931L;

    stbtic finbl int ID =
        jbvb.bwt.event.FocusEvent.FOCUS_LAST + 2;

    boolebn dispbtched;
    privbte AWTEvent nested;
    privbte AppContext toNotify;

    SentEvent() {
        this(null);
    }
    SentEvent(AWTEvent nested) {
        this(nested, null);
    }
    SentEvent(AWTEvent nested, AppContext toNotify) {
        super((nested != null)
                  ? nested.getSource()
                  : Toolkit.getDefbultToolkit(),
              ID);
        this.nested = nested;
        this.toNotify = toNotify;
    }

    public void dispbtch() {
        try {
            if (nested != null) {
                Toolkit.getEventQueue().dispbtchEvent(nested);
            }
        } finblly {
            dispbtched = true;
            if (toNotify != null) {
                SunToolkit.postEvent(toNotify, new SentEvent());
            }
            synchronized (this) {
                notifyAll();
            }
        }
    }
    finbl void dispose() {
        dispbtched = true;
        if (toNotify != null) {
            SunToolkit.postEvent(toNotify, new SentEvent());
        }
        synchronized (this) {
            notifyAll();
        }
    }
}
