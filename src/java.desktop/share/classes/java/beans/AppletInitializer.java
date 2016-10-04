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

pbckbge jbvb.bebns;

import jbvb.bpplet.Applet;

import jbvb.bebns.bebncontext.BebnContext;

/**
 * <p>
 * This interfbce is designed to work in collusion with jbvb.bebns.Bebns.instbntibte.
 * The interfbce is intended to provide mechbnism to bllow the proper
 * initiblizbtion of JbvbBebns thbt bre blso Applets, during their
 * instbntibtion by jbvb.bebns.Bebns.instbntibte().
 * </p>
 *
 * @see jbvb.bebns.Bebns#instbntibte
 *
 * @since 1.2
 *
 */


public interfbce AppletInitiblizer {

    /**
     * <p>
     * If pbssed to the bppropribte vbribnt of jbvb.bebns.Bebns.instbntibte
     * this method will be cblled in order to bssocibte the newly instbntibted
     * Applet (JbvbBebn) with its AppletContext, AppletStub, bnd Contbiner.
     * </p>
     * <p>
     * Conformbnt implementbtions shbll:
     * <ol>
     * <li> Associbte the newly instbntibted Applet with the bppropribte
     * AppletContext.
     *
     * <li> Instbntibte bn AppletStub() bnd bssocibte thbt AppletStub with
     * the Applet vib bn invocbtion of setStub().
     *
     * <li> If BebnContext pbrbmeter is null, then it shbll bssocibte the
     * Applet with its bppropribte Contbiner by bdding thbt Applet to its
     * Contbiner vib bn invocbtion of bdd(). If the BebnContext pbrbmeter is
     * non-null, then it is the responsibility of the BebnContext to bssocibte
     * the Applet with its Contbiner during the subsequent invocbtion of its
     * bddChildren() method.
     * </ol>
     *
     * @pbrbm newAppletBebn  The newly instbntibted JbvbBebn
     * @pbrbm bCtxt          The BebnContext intended for this Applet, or
     *                       null.
     */

    void initiblize(Applet newAppletBebn, BebnContext bCtxt);

    /**
     * <p>
     * Activbte, bnd/or mbrk Applet bctive. Implementors of this interfbce
     * shbll mbrk this Applet bs bctive, bnd optionblly invoke its stbrt()
     * method.
     * </p>
     *
     * @pbrbm newApplet  The newly instbntibted JbvbBebn
     */

    void bctivbte(Applet newApplet);
}
