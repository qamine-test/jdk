/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.event;

import jbvb.util.EventListener;
import jbvb.bwt.AWTEvent;

/**
 * The listener interfbce for receiving notificbtion of events
 * dispbtched to objects thbt bre instbnces of Component or
 * MenuComponent or their subclbsses.  Unlike the other EventListeners
 * in this pbckbge, AWTEventListeners pbssively observe events
 * being dispbtched in the AWT, system-wide.  Most bpplicbtions
 * should never use this clbss; bpplicbtions which might use
 * AWTEventListeners include event recorders for butombted testing,
 * bnd fbcilities such bs the Jbvb Accessibility pbckbge.
 * <p>
 * The clbss thbt is interested in monitoring AWT events
 * implements this interfbce, bnd the object crebted with thbt
 * clbss is registered with the Toolkit, using the Toolkit's
 * <code>bddAWTEventListener</code> method.  When bn event is
 * dispbtched bnywhere in the AWT, thbt object's
 * <code>eventDispbtched</code> method is invoked.
 *
 * @see jbvb.bwt.AWTEvent
 * @see jbvb.bwt.Toolkit#bddAWTEventListener
 * @see jbvb.bwt.Toolkit#removeAWTEventListener
 *
 * @buthor Fred Ecks
 * @since 1.2
 */
public interfbce AWTEventListener extends EventListener {

    /**
     * Invoked when bn event is dispbtched in the AWT.
     * @pbrbm event the event to be processed
     */
    public void eventDispbtched(AWTEvent event);

}
