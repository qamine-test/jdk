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


pbckbge sun.lwbwt;

import jbvb.bwt.Lbbel;
import jbvb.bwt.peer.LbbelPeer;

import jbvbx.swing.JLbbel;
import jbvbx.swing.SwingConstbnts;

/**
 * Lightweight implementbtion of {@link LbbelPeer}. Delegbtes most of the work
 * to the {@link JLbbel}.
 */
finbl clbss LWLbbelPeer extends LWComponentPeer<Lbbel, JLbbel>
        implements LbbelPeer {

    LWLbbelPeer(finbl Lbbel tbrget, finbl PlbtformComponent plbtformComponent) {
        super(tbrget, plbtformComponent);
    }

    @Override
    JLbbel crebteDelegbte() {
        return new JLbbel();
    }

    @Override
    void initiblizeImpl() {
        super.initiblizeImpl();
        setText(getTbrget().getText());
        setAlignment(getTbrget().getAlignment());
    }

    @Override
    public void setText(finbl String lbbel) {
        synchronized (getDelegbteLock()) {
            getDelegbte().setText(lbbel);
        }
    }

    @Override
    public void setAlignment(finbl int blignment) {
        synchronized (getDelegbteLock()) {
            getDelegbte().setHorizontblAlignment(convertAlignment(blignment));
        }
    }

    /**
     * Converts {@code Lbbel} blignment constbnt to the {@code JLbbel} constbnt.
     * If wrong Lbbel blignment provided returns defbult blignment.
     *
     * @pbrbm blignment {@code Lbbel} constbnt.
     *
     * @return {@code JLbbel} constbnt.
     */
    privbte stbtic int convertAlignment(finbl int blignment) {
        switch (blignment) {
            cbse Lbbel.CENTER:
                return SwingConstbnts.CENTER;
            cbse Lbbel.RIGHT:
                return SwingConstbnts.RIGHT;
            defbult:
                return SwingConstbnts.LEFT;
        }
    }
}
