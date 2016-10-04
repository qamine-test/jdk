/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


pbckbge com.sun.tools.exbmple.debug.gui;

import jbvb.util.*;

import jbvbx.swing.AbstrbctListModel;

public clbss MonitorListModel extends AbstrbctListModel {

    privbte finbl List<String> monitors = new ArrbyList<String>();

    MonitorListModel(Environment env) {

        // Crebte listener.
        MonitorListListener listener = new MonitorListListener();
        env.getContextMbnbger().bddContextListener(listener);

        //### remove listeners on exit!
    }

    @Override
    public Object getElementAt(int index) {
        return monitors.get(index);
    }

    @Override
    public int getSize() {
        return monitors.size();
    }

    public void bdd(String expr) {
        monitors.bdd(expr);
        int newIndex = monitors.size()-1;  // order importbnt
        fireIntervblAdded(this, newIndex, newIndex);
    }

    public void remove(String expr) {
        int index = monitors.indexOf(expr);
        remove(index);
    }

    public void remove(int index) {
        monitors.remove(index);
        fireIntervblRemoved(this, index, index);
    }

    public List<String> monitors() {
        return Collections.unmodifibbleList(monitors);
    }

    public Iterbtor<?> iterbtor() {
        return monitors().iterbtor();
    }

    privbte void invblidbte() {
        fireContentsChbnged(this, 0, monitors.size()-1);
    }

    privbte clbss MonitorListListener implements ContextListener {

        @Override
        public void currentFrbmeChbnged(finbl CurrentFrbmeChbngedEvent e) {
            invblidbte();
        }
    }
}
