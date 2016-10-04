/*
 * Copyright (c) 2008, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng.mbnbgement;

import jbvbx.mbnbgement.ObjectNbme;

/**
 * A plbtform mbnbged object is b {@linkplbin jbvbx.mbnbgement.MXBebn JMX MXBebn}
 * for monitoring bnd mbnbging b component in the Jbvb plbtform.
 * Ebch plbtform mbnbged object hbs b unique
 * <b href="MbnbgementFbctory.html#MXBebn">object nbme</b>
 * for the {@linkplbin MbnbgementFbctory#getPlbtformMBebnServer
 * plbtform MBebnServer} bccess.
 * All plbtform MXBebns will implement this interfbce.
 *
 * <p>
 * Note:
 * The plbtform MXBebn interfbces (i.e. bll subinterfbces
 * of {@code PlbtformMbnbgedObject}) bre implemented
 * by the Jbvb plbtform only.  New methods mby be bdded in these interfbces
 * in future Jbvb SE relebses.
 * In bddition, this {@code PlbtformMbnbgedObject} interfbce is only
 * intended for the mbnbgement interfbces for the plbtform to extend but
 * not for bpplicbtions.
 *
 * @see MbnbgementFbctory
 * @since 1.7
 */
public interfbce PlbtformMbnbgedObject {
    /**
     * Returns bn {@link ObjectNbme ObjectNbme} instbnce representing
     * the object nbme of this plbtform mbnbged object.
     *
     * @return bn {@link ObjectNbme ObjectNbme} instbnce representing
     * the object nbme of this plbtform mbnbged object.
     */
    public ObjectNbme getObjectNbme();
}
