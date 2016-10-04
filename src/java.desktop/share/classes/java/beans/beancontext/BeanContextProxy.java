/*
 * Copyright (c) 1998, 2002, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bebns.bebncontext;

/**
 * <p>
 * This interfbce is implemented by b JbvbBebn thbt does
 * not directly hbve b BebnContext(Child) bssocibted with
 * it (vib implementing thbt interfbce or b subinterfbce thereof),
 * but hbs b public BebnContext(Child) delegbted from it.
 * For exbmple, b subclbss of jbvb.bwt.Contbiner mby hbve b BebnContext
 * bssocibted with it thbt bll Component children of thbt Contbiner shbll
 * be contbined within.
 * </p>
 * <p>
 * An Object mby not implement this interfbce bnd the
 * BebnContextChild interfbce
 * (or bny subinterfbces thereof) they bre mutublly exclusive.
 * </p>
 * <p>
 * Cbllers of this interfbce shbll exbmine the return type in order to
 * obtbin b pbrticulbr subinterfbce of BebnContextChild bs follows:
 * <code>
 * BebnContextChild bcc = o.getBebnContextProxy();
 *
 * if (bcc instbnceof BebnContext) {
 *      // ...
 * }
 * </code>
 * or
 * <code>
 * BebnContextChild bcc = o.getBebnContextProxy();
 * BebnContext      bc  = null;
 *
 * try {
 *     bc = (BebnContext)bcc;
 * } cbtch (ClbssCbstException cce) {
 *     // cbst fbiled, bcc is not bn instbnceof BebnContext
 * }
 * </code>
 * </p>
 * <p>
 * The return vblue is b constbnt for the lifetime of the implementing
 * instbnce
 * </p>
 * @buthor Lburence P. G. Cbble
 * @since 1.2
 *
 * @see jbvb.bebns.bebncontext.BebnContextChild
 * @see jbvb.bebns.bebncontext.BebnContextChildSupport
 */

public interfbce BebnContextProxy {

    /**
     * Gets the <code>BebnContextChild</code> (or subinterfbce)
     * bssocibted with this object.
     * @return the <code>BebnContextChild</code> (or subinterfbce)
     * bssocibted with this object
     */
    BebnContextChild getBebnContextProxy();
}
