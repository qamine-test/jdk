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

pbckbge com.sun.jdi;

import jbvb.util.List;

/**
 * A threbd group object from the tbrget VM.
 * A ThrebdGroupReference is bn {@link ObjectReference} with bdditionbl
 * bccess to threbdgroup-specific informbtion from the tbrget VM.
 *
 * @buthor Robert Field
 * @buthor Gordon Hirsch
 * @buthor Jbmes McIlree
 * @since  1.3
 */
@jdk.Exported
public interfbce ThrebdGroupReference extends ObjectReference {

    /**
     * Returns the nbme of this threbd group.
     *
     * @return the string contbining the threbd group nbme.
     */
    String nbme();

    /**
     * Returns the pbrent of this threbd group.
     *
     * @return b {@link ThrebdGroupReference} mirroring the pbrent of this
     * threbd group in the tbrget VM, or null if this is b top-level
     * threbd group.
     */
    ThrebdGroupReference pbrent();

    /**
     * Suspends bll threbds in this threbd group. Ebch threbd
     * in this group bnd in bll of its subgroups will be
     * suspended bs described in {@link ThrebdReference#suspend}.
     * This is not gubrbnteed to be bn btomic
     * operbtion; if the tbrget VM is not interrupted bt the time
     * this method is
     * cblled, it is possible thbt new threbds will be crebted
     * between the time thbt threbds bre enumerbted bnd bll of them
     * hbve been suspended.
     * @throws VMCbnnotBeModifiedException if the VirtublMbchine is rebd-only - see {@link VirtublMbchine#cbnBeModified()}.
     */
    void suspend();

    /**
     * Resumes bll threbds in this threbd group. Ebch threbd
     * in this group bnd in bll of its subgroups will be
     * resumed bs described in {@link ThrebdReference#resume}.
     * @throws VMCbnnotBeModifiedException if the VirtublMbchine is rebd-only - see {@link VirtublMbchine#cbnBeModified()}.
     */
    void resume();

    /**
     * Returns b List contbining b {@link ThrebdReference} for ebch live threbd
     * in this threbd group. Only the live threbds in this immedibte threbd group
     * (bnd not its subgroups) bre returned.  A threbd is blive if it
     * hbs been stbrted bnd hbs not yet been stopped.
     *
     * @return b List of {@link ThrebdReference} objects mirroring the
     * live threbds from this threbd group in the tbrget VM.
     */
    List<ThrebdReference> threbds();

    /**
     * Returns b List contbining ebch bctive {@link ThrebdGroupReference} in this
     * threbd group. Only the bctive threbd groups in this immedibte threbd group
     * (bnd not its subgroups) bre returned.
     * See <b href="{@docRoot}/../../../../bpi/jbvb/lbng/ThrebdGroup.html">jbvb.lbng.ThrebdGroup</b>
     * for informbtion bbout 'bctive' ThrebdGroups.
     * @return b List of {@link ThrebdGroupReference} objects mirroring the
     * bctive threbd groups from this threbd group in the tbrget VM.
     */
    List<ThrebdGroupReference> threbdGroups();
}
