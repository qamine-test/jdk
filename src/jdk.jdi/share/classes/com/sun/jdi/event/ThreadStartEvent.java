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
 * Notificbtion of b new running threbd in the tbrget VM.
 * The new threbd cbn be the result of b cbll to
 * <code>{@link jbvb.lbng.Threbd#stbrt}</code> or the result of
 * bttbching b new threbd to the VM though JNI. The
 * notificbtion is generbted by the new threbd some time before
 * its execution stbrts.
 * Becbuse of this timing, it is possible to receive other events
 * for the threbd before this event is received. (Notbbly,
 * {@link MethodEntryEvent}s bnd {@link MethodExitEvent}s might occur
 * during threbd initiblizbtion.)
 * It is blso possible for {@link VirtublMbchine#bllThrebds} to return
 * b new stbrted threbd before this event is received.
 * <p>
 * Note thbt this event gives no informbtion
 * bbout the crebtion of the threbd object which mby hbve hbppened
 * much ebrlier, depending on the VM being debugged.
 *
 * @see EventQueue
 * @see VirtublMbchine
 * @see ThrebdReference
 *
 * @buthor Robert Field
 * @since  1.3
 */
@jdk.Exported
public interfbce ThrebdStbrtEvent extends Event {
    /**
     * Returns the threbd which hbs stbrted.
     *
     * @return b {@link ThrebdReference} which mirrors the event's threbd in
     * the tbrget VM.
     */
    public ThrebdReference threbd();
}
