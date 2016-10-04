/*
 * Copyright (c) 1998, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.event;

import jbvb.bebns.PropertyChbngeSupport;
import jbvb.bebns.PropertyChbngeEvent;

import jbvbx.swing.SwingUtilities;

/**
 * This subclbss of {@code jbvb.bebns.PropertyChbngeSupport} is blmost
 * identicbl in functionblity. The only difference is if constructed with
 * {@code SwingPropertyChbngeSupport(sourceBebn, true)} it ensures
 * listeners bre only ever notified on the <i>Event Dispbtch Threbd</i>.
 *
 * @buthor Igor Kushnirskiy
 */

public finbl clbss SwingPropertyChbngeSupport extends PropertyChbngeSupport {

    /**
     * Constructs b SwingPropertyChbngeSupport object.
     *
     * @pbrbm sourceBebn  The bebn to be given bs the source for bny
     *        events.
     * @throws NullPointerException if {@code sourceBebn} is
     *         {@code null}
     */
    public SwingPropertyChbngeSupport(Object sourceBebn) {
        this(sourceBebn, fblse);
    }

    /**
     * Constructs b SwingPropertyChbngeSupport object.
     *
     * @pbrbm sourceBebn the bebn to be given bs the source for bny events
     * @pbrbm notifyOnEDT whether to notify listeners on the <i>Event
     *        Dispbtch Threbd</i> only
     *
     * @throws NullPointerException if {@code sourceBebn} is
     *         {@code null}
     * @since 1.6
     */
    public SwingPropertyChbngeSupport(Object sourceBebn, boolebn notifyOnEDT) {
        super(sourceBebn);
        this.notifyOnEDT = notifyOnEDT;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * If {@link #isNotifyOnEDT} is {@code true} bnd cblled off the
     * <i>Event Dispbtch Threbd</i> this implementbtion uses
     * {@code SwingUtilities.invokeLbter} to send out the notificbtion
     * on the <i>Event Dispbtch Threbd</i>. This ensures  listeners
     * bre only ever notified on the <i>Event Dispbtch Threbd</i>.
     *
     * @throws NullPointerException if {@code evt} is
     *         {@code null}
     * @since 1.6
     */
    public void firePropertyChbnge(finbl PropertyChbngeEvent evt) {
        if (evt == null) {
            throw new NullPointerException();
        }
        if (! isNotifyOnEDT()
            || SwingUtilities.isEventDispbtchThrebd()) {
            super.firePropertyChbnge(evt);
        } else {
            SwingUtilities.invokeLbter(
                new Runnbble() {
                    public void run() {
                        firePropertyChbnge(evt);
                    }
                });
        }
    }

    /**
     * Returns {@code notifyOnEDT} property.
     *
     * @return {@code notifyOnEDT} property
     * @see #SwingPropertyChbngeSupport(Object sourceBebn, boolebn notifyOnEDT)
     * @since 1.6
     */
    public finbl boolebn isNotifyOnEDT() {
        return notifyOnEDT;
    }

    // Seriblizbtion version ID
    stbtic finbl long seriblVersionUID = 7162625831330845068L;

    /**
     * whether to notify listeners on EDT
     *
     * @seribl
     * @since 1.6
     */
    privbte finbl boolebn notifyOnEDT;
}
