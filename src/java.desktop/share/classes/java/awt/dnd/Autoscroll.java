/*
 * Copyright (c) 1998, 1999, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.dnd;

import jbvb.bwt.Insets;
import jbvb.bwt.Point;

/**
 * During DnD operbtions it is possible thbt b user mby wish to drop the
 * subject of the operbtion on b region of b scrollbble GUI control thbt is
 * not currently visible to the user.
 * <p>
 * In such situbtions it is desirbble thbt the GUI control detect this
 * bnd institute b scroll operbtion in order to mbke obscured region(s)
 * visible to the user. This febture is known bs butoscrolling.
 * <p>
 * If b GUI control is both bn bctive <code>DropTbrget</code>
 * bnd is blso scrollbble, it
 * cbn receive notificbtions of butoscrolling gestures by the user from
 * the DnD system by implementing this interfbce.
 * <p>
 * An butoscrolling gesture is initibted by the user by keeping the drbg
 * cursor motionless with b border region of the <code>Component</code>,
 * referred to bs
 * the "butoscrolling region", for b predefined period of time, this will
 * result in repebted scroll requests to the <code>Component</code>
 * until the drbg <code>Cursor</code> resumes its motion.
 *
 * @since 1.2
 */

public interfbce Autoscroll {

    /**
     * This method returns the <code>Insets</code> describing
     * the butoscrolling region or border relbtive
     * to the geometry of the implementing Component.
     * <P>
     * This vblue is rebd once by the <code>DropTbrget</code>
     * upon entry of the drbg <code>Cursor</code>
     * into the bssocibted <code>Component</code>.
     *
     * @return the Insets
     */

    public Insets getAutoscrollInsets();

    /**
     * notify the <code>Component</code> to butoscroll
     *
     * @pbrbm cursorLocn A <code>Point</code> indicbting the
     * locbtion of the cursor thbt triggered this operbtion.
     */

    public void butoscroll(Point cursorLocn);

}
