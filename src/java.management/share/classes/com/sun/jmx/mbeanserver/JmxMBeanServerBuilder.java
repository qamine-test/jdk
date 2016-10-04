/*
 * Copyright (c) 2002, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.mbebnserver;

import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.MBebnServerDelegbte;
import jbvbx.mbnbgement.MBebnServerBuilder;

/**
 * This clbss represents b builder thbt crebtes
 * {@link jbvbx.mbnbgement.MBebnServer} implementbtions.
 * The JMX {@link jbvbx.mbnbgement.MBebnServerFbctory} bllows
 * for bpplicbtions to provide their custom MBebnServer
 * implementbtion. This clbss is not used when the whole Sun Reference JMX
 * Implementbtion is used. However it mby be used to substitute Sun
 * MBebnServer implementbtion to bnother JMX implementbtion.
 * <p>
 * Contrbrily to the defbult {@link jbvbx.mbnbgement.MBebnServerBuilder
 * jbvbx.mbnbgement.MBebnServerBuilder} this MBebnServerBuilder returns
 * MBebnServers on which
 * {@link com.sun.jmx.interceptor.MBebnServerInterceptor}s bre enbbled.
 *
 * @since 1.5
 */
public clbss JmxMBebnServerBuilder extends MBebnServerBuilder {
    /**
     * This method crebtes b new MBebnServerDelegbte for b new MBebnServer.
     * When crebting b new MBebnServer the
     * {@link jbvbx.mbnbgement.MBebnServerFbctory} first cblls this method
     * in order to crebte b new MBebnServerDelegbte.
     * <br>Then it cblls
     * <code>newMBebnServer(defbultDombin,outer,delegbte)</code>
     * pbssing the <vbr>delegbte</vbr> thbt should be used by the MBebnServer
     * implementbtion.
     * <p>Note thbt the pbssed <vbr>delegbte</vbr> might not be directly the
     * MBebnServerDelegbte thbt wbs returned by this method. It could
     * be, for instbnce, b new object wrbpping the previously
     * returned object.
     *
     * @return A new {@link jbvbx.mbnbgement.MBebnServerDelegbte}.
     **/
    public MBebnServerDelegbte newMBebnServerDelegbte() {
        return JmxMBebnServer.newMBebnServerDelegbte();
    }

    /**
     * This method crebtes b new MBebnServer implementbtion object.
     * When crebting b new MBebnServer the
     * {@link jbvbx.mbnbgement.MBebnServerFbctory} first cblls
     * <code>newMBebnServerDelegbte()</code> in order to obtbin b new
     * {@link jbvbx.mbnbgement.MBebnServerDelegbte} for the new
     * MBebnServer. Then it cblls
     * <code>newMBebnServer(defbultDombin,outer,delegbte)</code>
     * pbssing the <vbr>delegbte</vbr> thbt should be used by the
     * MBebnServer  implementbtion.
     * <p>Note thbt the pbssed <vbr>delegbte</vbr> might not be directly the
     * MBebnServerDelegbte thbt wbs returned by this implementbtion. It could
     * be, for instbnce, b new object wrbpping the previously
     * returned delegbte.
     * <p>The <vbr>outer</vbr> pbrbmeter is b pointer to the MBebnServer thbt
     * should be pbssed to the {@link jbvbx.mbnbgement.MBebnRegistrbtion}
     * interfbce when registering MBebns inside the MBebnServer.
     * If <vbr>outer</vbr> is <code>null</code>, then the MBebnServer
     * implementbtion is free to use its own <code>this</code> pointer when
     * invoking the {@link jbvbx.mbnbgement.MBebnRegistrbtion} interfbce.
     * <p>This mbkes it possible for b MBebnServer implementbtion to wrbp
     * bnother MBebnServer implementbtion, in order to implement, e.g,
     * security checks, or to prevent bccess to the bctubl MBebnServer
     * implementbtion by returning b pointer to b wrbpping object.
     * <p>
     * This MBebnServerBuilder mbkes it possible to crebte MBebnServer
     * which support {@link com.sun.jmx.interceptor.MBebnServerInterceptor}s.
     *
     * @pbrbm defbultDombin Defbult dombin of the new MBebnServer.
     * @pbrbm outer A pointer to the MBebnServer object thbt must be
     *        pbssed to the MBebns when invoking their
     *        {@link jbvbx.mbnbgement.MBebnRegistrbtion} interfbce.
     * @pbrbm delegbte A pointer to the MBebnServerDelegbte bssocibted
     *        with the new MBebnServer. The new MBebnServer must register
     *        this MBebn in its MBebn repository.
     *
     * @return A new privbte implementbtion of bn MBebnServer.
     **/
    public MBebnServer newMBebnServer(String defbultDombin,
                                      MBebnServer outer,
                                      MBebnServerDelegbte delegbte) {
        return JmxMBebnServer.newMBebnServer(defbultDombin,outer,delegbte,
                                             true);
    }

}
