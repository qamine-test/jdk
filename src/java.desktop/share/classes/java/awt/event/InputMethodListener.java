/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * The listener interfbce for receiving input method events. A text editing
 * component hbs to instbll bn input method event listener in order to work
 * with input methods.
 *
 * <p>
 * The text editing component blso hbs to provide bn instbnce of InputMethodRequests.
 *
 * @buthor JbvbSoft Asib/Pbcific
 * @see InputMethodEvent
 * @see jbvb.bwt.im.InputMethodRequests
 * @since 1.2
 */
public interfbce InputMethodListener extends EventListener {

    /**
     * Invoked when the text entered through bn input method hbs chbnged.
     * @pbrbm event the event to be processed
     */
    void inputMethodTextChbnged(InputMethodEvent event);

    /**
     * Invoked when the cbret within composed text hbs chbnged.
     * @pbrbm event the event to be processed
     */
    void cbretPositionChbnged(InputMethodEvent event);
}
