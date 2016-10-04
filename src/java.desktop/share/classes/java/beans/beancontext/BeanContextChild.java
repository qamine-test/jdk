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

pbckbge jbvb.bebns.bebncontext;

import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.VetobbleChbngeListener;
import jbvb.bebns.PropertyVetoException;

import jbvb.bebns.bebncontext.BebnContext;

/**
 * <p>
 * JbvbBebns wishing to be nested within, bnd obtbin b reference to their
 * execution environment, or context, bs defined by the BebnContext
 * sub-interfbce shbll implement this interfbce.
 * </p>
 * <p>
 * Conformbnt BebnContexts shbll bs b side effect of bdding b BebnContextChild
 * object shbll pbss b reference to itself vib the setBebnContext() method of
 * this interfbce.
 * </p>
 * <p>
 * Note thbt b BebnContextChild mby refuse b chbnge in stbte by throwing
 * PropertyVetoedException in response.
 * </p>
 * <p>
 * In order for persistence mechbnisms to function properly on BebnContextChild
 * instbnces bcross b brobd vbriety of scenbrios, implementing clbsses of this
 * interfbce bre required to define bs trbnsient, bny or bll fields, or
 * instbnce vbribbles, thbt mby contbin, or represent, references to the
 * nesting BebnContext instbnce or other resources obtbined
 * from the BebnContext vib bny unspecified mechbnisms.
 * </p>
 *
 * @buthor      Lburence P. G. Cbble
 * @since       1.2
 *
 * @see jbvb.bebns.bebncontext.BebnContext
 * @see jbvb.bebns.PropertyChbngeEvent
 * @see jbvb.bebns.PropertyChbngeListener
 * @see jbvb.bebns.PropertyVetoException
 * @see jbvb.bebns.VetobbleChbngeListener
 */

public interfbce BebnContextChild {

    /**
     * <p>
     * Objects thbt implement this interfbce,
     * shbll fire b jbvb.bebns.PropertyChbngeEvent, with pbrbmeters:
     *
     * propertyNbme "bebnContext", oldVblue (the previous nesting
     * <code>BebnContext</code> instbnce, or <code>null</code>),
     * newVblue (the current nesting
     * <code>BebnContext</code> instbnce, or <code>null</code>).
     * <p>
     * A chbnge in the vblue of the nesting BebnContext property of this
     * BebnContextChild mby be vetoed by throwing the bppropribte exception.
     * </p>
     * @pbrbm bc The <code>BebnContext</code> with which
     * to bssocibte this <code>BebnContextChild</code>.
     * @throws PropertyVetoException if the
     * bddition of the specified <code>BebnContext</code> is refused.
     */
    void setBebnContext(BebnContext bc) throws PropertyVetoException;

    /**
     * Gets the <code>BebnContext</code> bssocibted
     * with this <code>BebnContextChild</code>.
     * @return the <code>BebnContext</code> bssocibted
     * with this <code>BebnContextChild</code>.
     */
    BebnContext getBebnContext();

    /**
     * Adds b <code>PropertyChbngeListener</code>
     * to this <code>BebnContextChild</code>
     * in order to receive b <code>PropertyChbngeEvent</code>
     * whenever the specified property hbs chbnged.
     * @pbrbm nbme the nbme of the property to listen on
     * @pbrbm pcl the <code>PropertyChbngeListener</code> to bdd
     */
    void bddPropertyChbngeListener(String nbme, PropertyChbngeListener pcl);

    /**
     * Removes b <code>PropertyChbngeListener</code> from this
     * <code>BebnContextChild</code>  so thbt it no longer
     * receives <code>PropertyChbngeEvents</code> when the
     * specified property is chbnged.
     *
     * @pbrbm nbme the nbme of the property thbt wbs listened on
     * @pbrbm pcl the <code>PropertyChbngeListener</code> to remove
     */
    void removePropertyChbngeListener(String nbme, PropertyChbngeListener pcl);

    /**
     * Adds b <code>VetobbleChbngeListener</code> to
     * this <code>BebnContextChild</code>
     * to receive events whenever the specified property chbnges.
     * @pbrbm nbme the nbme of the property to listen on
     * @pbrbm vcl the <code>VetobbleChbngeListener</code> to bdd
     */
    void bddVetobbleChbngeListener(String nbme, VetobbleChbngeListener vcl);

    /**
     * Removes b <code>VetobbleChbngeListener</code> from this
     * <code>BebnContextChild</code> so thbt it no longer receives
     * events when the specified property chbnges.
     * @pbrbm nbme the nbme of the property thbt wbs listened on.
     * @pbrbm vcl the <code>VetobbleChbngeListener</code> to remove.
     */
    void removeVetobbleChbngeListener(String nbme, VetobbleChbngeListener vcl);

}
