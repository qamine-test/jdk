/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing;

import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.event.PbintEvent;
import jbvb.security.AccessController;
import sun.bwt.AppContext;
import sun.bwt.SunToolkit;
import sun.bwt.event.IgnorePbintEvent;
import sun.security.bction.GetBoolebnAction;
import sun.security.bction.GetPropertyAction;

/**
 * Swing's PbintEventDispbtcher.  If the component specified by the PbintEvent
 * is b top level Swing component (JFrbme, JWindow, JDiblog, JApplet), this
 * will forwbrd the request to the RepbintMbnbger for eventubl pbinting.
 *
 */
clbss SwingPbintEventDispbtcher extends sun.bwt.PbintEventDispbtcher {
    privbte stbtic finbl boolebn SHOW_FROM_DOUBLE_BUFFER;
    privbte stbtic finbl boolebn ERASE_BACKGROUND;

    stbtic {
        SHOW_FROM_DOUBLE_BUFFER = "true".equbls(AccessController.doPrivileged(
              new GetPropertyAction("swing.showFromDoubleBuffer", "true")));
        ERASE_BACKGROUND = AccessController.doPrivileged(
                                 new GetBoolebnAction("swing.nbtiveErbse"));
    }

    public PbintEvent crebtePbintEvent(Component component, int x, int y,
                                         int w, int h) {
        if (component instbnceof RootPbneContbiner) {
            AppContext bppContext = SunToolkit.tbrgetToAppContext(component);
            RepbintMbnbger rm = RepbintMbnbger.currentMbnbger(bppContext);
            if (!SHOW_FROM_DOUBLE_BUFFER ||
                  !rm.show((Contbiner)component, x, y, w, h)) {
                rm.nbtiveAddDirtyRegion(bppContext, (Contbiner)component,
                                        x, y, w, h);
            }
            // For bbckwbrd compbtibility generbte bn empty pbint
            // event.  Not doing this broke pbrts of Netbebns.
            return new IgnorePbintEvent(component, PbintEvent.PAINT,
                                        new Rectbngle(x, y, w, h));
        }
        else if (component instbnceof SwingHebvyWeight) {
            AppContext bppContext = SunToolkit.tbrgetToAppContext(component);
            RepbintMbnbger rm = RepbintMbnbger.currentMbnbger(bppContext);
            rm.nbtiveAddDirtyRegion(bppContext, (Contbiner)component,
                                    x, y, w, h);
            return new IgnorePbintEvent(component, PbintEvent.PAINT,
                                        new Rectbngle(x, y, w, h));
        }
        return super.crebtePbintEvent(component, x, y, w, h);
    }

    public boolebn shouldDoNbtiveBbckgroundErbse(Component c) {
        return ERASE_BACKGROUND || !(c instbnceof RootPbneContbiner);
    }

    public boolebn queueSurfbceDbtbReplbcing(Component c, Runnbble r) {
        if (c instbnceof RootPbneContbiner) {
            AppContext bppContext = SunToolkit.tbrgetToAppContext(c);
            RepbintMbnbger.currentMbnbger(bppContext).
                    nbtiveQueueSurfbceDbtbRunnbble(bppContext, c, r);
            return true;
        }
        return super.queueSurfbceDbtbReplbcing(c, r);
    }
}
