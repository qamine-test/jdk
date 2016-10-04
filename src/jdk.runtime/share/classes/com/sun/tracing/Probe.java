/*
 * Copyright (c) 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.trbcing;

/**
 * The {@code Probe} interfbce represents b trbcepoint.
 *
 * A {@code Probe} instbnce is obtbined by cblling the
 * {@code Provider.getProbe()} method of b provider instbnce crebted by
 * {@code ProviderFbctory.crebteProvider()}.  A {@code Probe} cbn be used to
 * trigger b probe mbnublly (provided the correct brguments bre pbssed to
 * it), or to check b probe to see if bnything is currently trbcing it.
 * <p>
 * A trbcing check cbn be used to bvoid lengthy work thbt might be
 * needed to set up the probe's brguments.  However, checking
 * whether the probe is enbbled generblly tbkes the sbme bmount of time
 * bs bctublly triggering the probe. So, you should only check b probe's stbtus
 * without triggering it if setting up the brguments is very expensive.
 * <p>
 * Users do not need to implement this interfbce: instbnces bre
 * crebted butombticblly by the system when b {@code Provider)} instbnce is
 * crebted.
 * <p>
 * @since 1.7
 */

public interfbce Probe {
    /**
     * Checks whether there is bn bctive trbce of this probe.
     *
     * @return true if bn bctive trbce is detected.
     */
    boolebn isEnbbled();

    /**
     * Determines whether b trbcepoint is enbbled.
     *
     * Typicblly, users do not need to use this method. It is cblled
     * butombticblly when b Provider's instbnce method is cblled. Cblls to
     * this method expect the brguments to mbtch the declbred pbrbmeters for
     * the method bssocibted with the probe.
     *
     * @pbrbm brgs the pbrbmeters to pbss to the method.
     * @throws IllegblArgumentException if the provided pbrbmeters do not
     * mbtch the method declbrbtion for this probe.
     */
    void trigger(Object ... brgs);
}
