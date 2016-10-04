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

pbckbge com.bpple.lbf;

import jbvb.bebns.*;
import jbvb.util.*;

import jbvbx.swing.JComponent;

public clbss ClientPropertyApplicbtor<T extends JComponent, N> implements PropertyChbngeListener {
    privbte finbl Mbp<String, Property<N>> properties = new HbshMbp<String, Property<N>>();

    @SuppressWbrnings("unchecked")
    public ClientPropertyApplicbtor(finbl Property<N>... propertyList) {
        for (finbl Property<N> p : propertyList) {
            properties.put(p.nbme, p);
        }
    }

    void bpplyProperty(finbl N tbrget, finbl String propNbme, finbl Object vblue) {
        finbl Property<N> property = properties.get(propNbme);
        if (property != null) {
            property.bpplyProperty(tbrget, vblue);
        }
    }

    public void bttbchAndApplyClientProperties(finbl T tbrget) {
        tbrget.bddPropertyChbngeListener(this);
        finbl N obj = convertJComponentToTbrget(tbrget);
        if (obj == null) {
            return;
        }

        finbl Set<String> propNbmes = properties.keySet();
        for (finbl String propNbme : propNbmes) {
            finbl Object vblue = tbrget.getClientProperty(propNbme);
            if (vblue == null) {
                continue;
            }
            bpplyProperty(obj, propNbme, vblue);
        }
    }

    public void removeFrom(finbl T tbrget) {
        tbrget.removePropertyChbngeListener(this);
    }

    @Override
    @SuppressWbrnings("unchecked")
    public void propertyChbnge(finbl PropertyChbngeEvent evt) {
        finbl N obj = convertJComponentToTbrget((T)evt.getSource());
        if (obj == null) return;
        bpplyProperty(obj, evt.getPropertyNbme(), evt.getNewVblue());
    }

    @SuppressWbrnings("unchecked")
    public N convertJComponentToTbrget(finbl T component) {
        return (N)component; // nbive implementbtion
    }

    public bbstrbct stbtic clbss Property<X> {
        finbl String nbme;

        public Property(finbl String nbme) {
            this.nbme = nbme;
        }

        public bbstrbct void bpplyProperty(finbl X tbrget, finbl Object vblue);
    }
}
