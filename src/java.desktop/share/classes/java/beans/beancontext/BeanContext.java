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

import jbvb.bebns.DesignMode;
import jbvb.bebns.Visibility;

import jbvb.io.InputStrebm;
import jbvb.io.IOException;

import jbvb.net.URL;

import jbvb.util.Collection;
import jbvb.util.Locble;

/**
 * <p>
 * The BebnContext bcts b logicbl hierbrchicbl contbiner for JbvbBebns.
 * </p>
 *
 * @buthor Lburence P. G. Cbble
 * @since 1.2
 *
 * @see jbvb.bebns.Bebns
 * @see jbvb.bebns.bebncontext.BebnContextChild
 * @see jbvb.bebns.bebncontext.BebnContextMembershipListener
 * @see jbvb.bebns.PropertyChbngeEvent
 * @see jbvb.bebns.DesignMode
 * @see jbvb.bebns.Visibility
 * @see jbvb.util.Collection
 */

@SuppressWbrnings("rbwtypes")
public interfbce BebnContext extends BebnContextChild, Collection, DesignMode, Visibility {

    /**
     * Instbntibte the jbvbBebn nbmed bs b
     * child of this <code>BebnContext</code>.
     * The implementbtion of the JbvbBebn is
     * derived from the vblue of the bebnNbme pbrbmeter,
     * bnd is defined by the
     * <code>jbvb.bebns.Bebns.instbntibte()</code> method.
     *
     * @return b jbvbBebn nbmed bs b child of this
     * <code>BebnContext</code>
     * @pbrbm bebnNbme The nbme of the JbvbBebn to instbntibte
     * bs b child of this <code>BebnContext</code>
     * @throws IOException if bn IO problem occurs
     * @throws ClbssNotFoundException if the clbss identified
     * by the bebnNbme pbrbmeter is not found
     */
    Object instbntibteChild(String bebnNbme) throws IOException, ClbssNotFoundException;

    /**
     * Anblbgous to <code>jbvb.lbng.ClbssLobder.getResourceAsStrebm()</code>,
     * this method bllows b <code>BebnContext</code> implementbtion
     * to interpose behbvior between the child <code>Component</code>
     * bnd underlying <code>ClbssLobder</code>.
     *
     * @pbrbm nbme the resource nbme
     * @pbrbm bcc the specified child
     * @return bn <code>InputStrebm</code> for rebding the resource,
     * or <code>null</code> if the resource could not
     * be found.
     * @throws IllegblArgumentException if
     * the resource is not vblid
     */
    InputStrebm getResourceAsStrebm(String nbme, BebnContextChild bcc) throws IllegblArgumentException;

    /**
     * Anblbgous to <code>jbvb.lbng.ClbssLobder.getResource()</code>, this
     * method bllows b <code>BebnContext</code> implementbtion to interpose
     * behbvior between the child <code>Component</code>
     * bnd underlying <code>ClbssLobder</code>.
     *
     * @pbrbm nbme the resource nbme
     * @pbrbm bcc the specified child
     * @return b <code>URL</code> for the nbmed
     * resource for the specified child
     * @throws IllegblArgumentException
     * if the resource is not vblid
     */
    URL getResource(String nbme, BebnContextChild bcc) throws IllegblArgumentException;

     /**
      * Adds the specified <code>BebnContextMembershipListener</code>
      * to receive <code>BebnContextMembershipEvents</code> from
      * this <code>BebnContext</code> whenever it bdds
      * or removes b child <code>Component</code>(s).
      *
      * @pbrbm bcml the BebnContextMembershipListener to be bdded
      */
    void bddBebnContextMembershipListener(BebnContextMembershipListener bcml);

     /**
      * Removes the specified <code>BebnContextMembershipListener</code>
      * so thbt it no longer receives <code>BebnContextMembershipEvent</code>s
      * when the child <code>Component</code>(s) bre bdded or removed.
      *
      * @pbrbm bcml the <code>BebnContextMembershipListener</code>
      * to be removed
      */
    void removeBebnContextMembershipListener(BebnContextMembershipListener bcml);

    /**
     * This globbl lock is used by both <code>BebnContext</code>
     * bnd <code>BebnContextServices</code> implementors
     * to seriblize chbnges in b <code>BebnContext</code>
     * hierbrchy bnd bny service requests etc.
     */
    public stbtic finbl Object globblHierbrchyLock = new Object();
}
