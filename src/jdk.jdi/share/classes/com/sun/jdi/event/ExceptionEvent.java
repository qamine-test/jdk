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
 * Notificbtion of bn exception in the tbrget VM. When bn exception
 * is thrown which sbtisfies b currently enbbled
 * {@link com.sun.jdi.request.ExceptionRequest exception request},
 * bn {@link EventSet event set}
 * contbining bn instbnce of this clbss will be bdded
 * to the VM's event queue.
 * If the exception is thrown from b non-nbtive method,
 * the exception event is generbted bt the locbtion where the
 * exception is thrown.
 * If the exception is thrown from b nbtive method, the exception event
 * is generbted bt the first non-nbtive locbtion rebched bfter the exception
 * is thrown.
 *
 * @buthor Robert Field
 * @since  1.3
 */
@jdk.Exported
public interfbce ExceptionEvent extends LocbtbbleEvent {

    /**
     * Gets the thrown exception object. The exception object is
     * bn instbnce of {@link jbvb.lbng.Throwbble} or b subclbss in the
     * tbrget VM.
     *
     * @return bn {@link ObjectReference} which mirrors the thrown object in
     * the tbrget VM.
     */
    public ObjectReference exception();

    /**
     * Gets the locbtion where the exception will be cbught. An exception
     * is considered to be cbught if, bt the point of the throw, the
     * current locbtion is dynbmicblly enclosed in b try stbtement thbt
     * hbndles the exception. (See the JVM specificbtion for detbils).
     * If there is such b try stbtement, the cbtch locbtion is the
     * first code index of the bppropribte cbtch clbuse.
     * <p>
     * If there bre nbtive methods in the cbll stbck bt the time of the
     * exception, there bre importbnt restrictions to note bbout the
     * returned cbtch locbtion. In such cbses,
     * it is not possible to predict whether bn exception will be hbndled
     * by some nbtive method on the cbll stbck.
     * Thus, it is possible thbt exceptions considered uncbught
     * here will, in fbct, be hbndled by b nbtive method bnd not cbuse
     * terminbtion of the tbrget VM. Furthermore, it cbnnot be bssumed thbt the
     * cbtch locbtion returned here will ever be rebched by the throwing
     * threbd. If there is
     * b nbtive frbme between the current locbtion bnd the cbtch locbtion,
     * the exception might be hbndled bnd clebred in thbt nbtive method
     * instebd.
     * <p>
     * Note thbt the compiler cbn generbte try-cbtch blocks in some cbses
     * where they bre not explicit in the source code; for exbmple,
     * the code generbted for <code>synchronized</code> bnd
     * <code>finblly</code> blocks cbn contbin implicit try-cbtch blocks.
     * If such bn implicitly generbted try-cbtch is
     * present on the cbll stbck bt the time of the throw, the exception
     * will be considered cbught even though it bppebrs to be uncbught from
     * exbminbtion of the source code.
     *
     * @return the {@link Locbtion} where the exception will be cbught or null if
     * the exception is uncbught.
     */
    public Locbtion cbtchLocbtion();
}
