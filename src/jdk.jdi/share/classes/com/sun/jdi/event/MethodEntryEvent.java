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

pbckbge com.sun.jdi.event;

import com.sun.jdi.*;

/**
 * Notificbtion of b method invocbtion in the tbrget VM. This event
 * occurs bfter entry into the invoked method bnd before bny
 * code hbs executed.
 * Method entry events bre generbted for both nbtive bnd non-nbtive
 * methods.
 * <P>
 * In some VMs method entry events cbn occur for b pbrticulbr threbd
 * before its {@link ThrebdStbrtEvent} occurs if methods bre cblled
 * bs pbrt of the threbd's initiblizbtion.
 *
 * @see EventQueue
 *
 * @buthor Robert Field
 * @since  1.3
 */
@jdk.Exported
public interfbce MethodEntryEvent extends LocbtbbleEvent {

    /**
     * Returns the method thbt wbs entered.
     *
     * @return b {@link Method} which mirrors the method thbt wbs entered.
     */
    public Method method();
}
