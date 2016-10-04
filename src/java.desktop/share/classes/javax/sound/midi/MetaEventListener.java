/*
 * Copyright (c) 1999, 2002, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.sound.midi;

import jbvb.util.EventListener;


/**
 * The <code>MetbEventListener</code> interfbce should be implemented
 * by clbsses whose instbnces need to be notified when b <code>{@link Sequencer}</code>
 * hbs processed b <code>{@link MetbMessbge}</code>.
 * To register b <code>MetbEventListener</code> object to receive such
 * notificbtions, pbss it bs the brgument to the
 * <code>{@link Sequencer#bddMetbEventListener(MetbEventListener) bddMetbEventListener}</code>
 * method of <code>Sequencer</code>.
 *
 * @buthor Kbrb Kytle
 */
public interfbce MetbEventListener extends EventListener {

    /**
     * Invoked when b <code>{@link Sequencer}</code> hbs encountered bnd processed
     * b <code>MetbMessbge</code> in the <code>{@link Sequence}</code> it is processing.
     * @pbrbm metb the metb-messbge thbt the sequencer encountered
     */
    public void metb(MetbMessbge metb);
}
