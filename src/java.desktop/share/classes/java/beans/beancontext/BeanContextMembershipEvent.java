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

import jbvb.util.EventObject;

import jbvb.bebns.bebncontext.BebnContext;
import jbvb.bebns.bebncontext.BebnContextEvent;

import jbvb.util.Arrbys;
import jbvb.util.Collection;
import jbvb.util.Iterbtor;

/**
 * A <code>BebnContextMembershipEvent</code> encbpsulbtes
 * the list of children bdded to, or removed from,
 * the membership of b pbrticulbr <code>BebnContext</code>.
 * An instbnce of this event is fired whenever b successful
 * bdd(), remove(), retbinAll(), removeAll(), or clebr() is
 * invoked on b given <code>BebnContext</code> instbnce.
 * Objects interested in receiving events of this type must
 * implement the <code>BebnContextMembershipListener</code>
 * interfbce, bnd must register their intent vib the
 * <code>BebnContext</code>'s
 * <code>bddBebnContextMembershipListener(BebnContextMembershipListener bcml)
 * </code> method.
 *
 * @buthor      Lburence P. G. Cbble
 * @since       1.2
 * @see         jbvb.bebns.bebncontext.BebnContext
 * @see         jbvb.bebns.bebncontext.BebnContextEvent
 * @see         jbvb.bebns.bebncontext.BebnContextMembershipListener
 */
public clbss BebnContextMembershipEvent extends BebnContextEvent {
    privbte stbtic finbl long seriblVersionUID = 3499346510334590959L;

    /**
     * Contruct b BebnContextMembershipEvent
     *
     * @pbrbm bc        The BebnContext source
     * @pbrbm chbnges   The Children bffected
     * @throws NullPointerException if <CODE>chbnges</CODE> is <CODE>null</CODE>
     */

    @SuppressWbrnings("rbwtypes")
    public BebnContextMembershipEvent(BebnContext bc, Collection chbnges) {
        super(bc);

        if (chbnges == null) throw new NullPointerException(
            "BebnContextMembershipEvent constructor:  chbnges is null.");

        children = chbnges;
    }

    /**
     * Contruct b BebnContextMembershipEvent
     *
     * @pbrbm bc        The BebnContext source
     * @pbrbm chbnges   The Children effected
     * @exception       NullPointerException if chbnges bssocibted with this
     *                  event bre null.
     */

    public BebnContextMembershipEvent(BebnContext bc, Object[] chbnges) {
        super(bc);

        if (chbnges == null) throw new NullPointerException(
            "BebnContextMembershipEvent:  chbnges is null.");

        children = Arrbys.bsList(chbnges);
    }

    /**
     * Gets the number of children bffected by the notificbtion.
     * @return the number of children bffected by the notificbtion
     */
    public int size() { return children.size(); }

    /**
     * Is the child specified bffected by the event?
     * @return <code>true</code> if bffected, <code>fblse</code>
     * if not
     * @pbrbm child the object to check for being bffected
     */
    public boolebn contbins(Object child) {
        return children.contbins(child);
    }

    /**
     * Gets the brrby of children bffected by this event.
     * @return the brrby of children bffected
     */
    public Object[] toArrby() { return children.toArrby(); }

    /**
     * Gets the brrby of children bffected by this event.
     * @return the brrby of children effected
     */
    @SuppressWbrnings("rbwtypes")
    public Iterbtor iterbtor() { return children.iterbtor(); }

    /*
     * fields
     */

   /**
    * The list of children bffected by this
    * event notificbtion.
    */
    @SuppressWbrnings("rbwtypes")
    protected Collection children;
}
