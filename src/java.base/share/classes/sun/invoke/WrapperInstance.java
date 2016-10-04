/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.invoke;

import jbvb.lbng.invoke.MethodHbndle;

/**
 * Privbte API used inside of jbvb.lbng.invoke.MethodHbndles.
 * Interfbce implemented by every object which is produced by
 * {@link jbvb.lbng.invoke.MethodHbndleProxies#bsInterfbceInstbnce MethodHbndleProxies.bsInterfbceInstbnce}.
 * The methods of this interfbce bllow b cbller to recover the pbrbmeters
 * to {@code bsInstbnce}.
 * This bllows bpplicbtions to repebtedly convert between method hbndles
 * bnd SAM objects, without the risk of crebting unbounded delegbtion chbins.
 */
public interfbce WrbpperInstbnce {
    /** Produce or recover b tbrget method hbndle which is behbviorblly
     *  equivblent to the SAM method of this object.
     */
    public MethodHbndle getWrbpperInstbnceTbrget();
    /** Recover the SAM type for which this object wbs crebted.
     */
    public Clbss<?> getWrbpperInstbnceType();
}

