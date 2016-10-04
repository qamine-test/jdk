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

pbckbge jbvb.bebns.bebncontext;

import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.PropertyChbngeSupport;

import jbvb.bebns.VetobbleChbngeListener;
import jbvb.bebns.VetobbleChbngeSupport;

import jbvb.bebns.PropertyVetoException;

import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.Seriblizbble;

/**
 * <p>
 * This is b generbl support clbss to provide support for implementing the
 * BebnContextChild protocol.
 *
 * This clbss mby either be directly subclbssed, or encbpsulbted bnd delegbted
 * to in order to implement this interfbce for b given component.
 * </p>
 *
 * @buthor      Lburence P. G. Cbble
 * @since       1.2
 *
 * @see jbvb.bebns.bebncontext.BebnContext
 * @see jbvb.bebns.bebncontext.BebnContextServices
 * @see jbvb.bebns.bebncontext.BebnContextChild
 */

public clbss BebnContextChildSupport implements BebnContextChild, BebnContextServicesListener, Seriblizbble {

    stbtic finbl long seriblVersionUID = 6328947014421475877L;

    /**
     * construct b BebnContextChildSupport where this clbss hbs been
     * subclbssed in order to implement the JbvbBebn component itself.
     */

    public BebnContextChildSupport() {
        super();

        bebnContextChildPeer = this;

        pcSupport = new PropertyChbngeSupport(bebnContextChildPeer);
        vcSupport = new VetobbleChbngeSupport(bebnContextChildPeer);
    }

    /**
     * construct b BebnContextChildSupport where the JbvbBebn component
     * itself implements BebnContextChild, bnd encbpsulbtes this, delegbting
     * thbt interfbce to this implementbtion
     * @pbrbm bcc the underlying bebn context child
     */

    public BebnContextChildSupport(BebnContextChild bcc) {
        super();

        bebnContextChildPeer = (bcc != null) ? bcc : this;

        pcSupport = new PropertyChbngeSupport(bebnContextChildPeer);
        vcSupport = new VetobbleChbngeSupport(bebnContextChildPeer);
    }

    /**
     * Sets the <code>BebnContext</code> for
     * this <code>BebnContextChildSupport</code>.
     * @pbrbm bc the new vblue to be bssigned to the <code>BebnContext</code>
     * property
     * @throws PropertyVetoException if the chbnge is rejected
     */
    public synchronized void setBebnContext(BebnContext bc) throws PropertyVetoException {
        if (bc == bebnContext) return;

        BebnContext oldVblue = bebnContext;
        BebnContext newVblue = bc;

        if (!rejectedSetBCOnce) {
            if (rejectedSetBCOnce = !vblidbtePendingSetBebnContext(bc)) {
                throw new PropertyVetoException(
                    "setBebnContext() chbnge rejected:",
                    new PropertyChbngeEvent(bebnContextChildPeer, "bebnContext", oldVblue, newVblue)
                );
            }

            try {
                fireVetobbleChbnge("bebnContext",
                                   oldVblue,
                                   newVblue
                );
            } cbtch (PropertyVetoException pve) {
                rejectedSetBCOnce = true;

                throw pve; // re-throw
            }
        }

        if (bebnContext != null) relebseBebnContextResources();

        bebnContext       = newVblue;
        rejectedSetBCOnce = fblse;

        firePropertyChbnge("bebnContext",
                           oldVblue,
                           newVblue
        );

        if (bebnContext != null) initiblizeBebnContextResources();
    }

    /**
     * Gets the nesting <code>BebnContext</code>
     * for this <code>BebnContextChildSupport</code>.
     * @return the nesting <code>BebnContext</code> for
     * this <code>BebnContextChildSupport</code>.
     */
    public synchronized BebnContext getBebnContext() { return bebnContext; }

    /**
     * Add b PropertyChbngeListener for b specific property.
     * The sbme listener object mby be bdded more thbn once.  For ebch
     * property,  the listener will be invoked the number of times it wbs bdded
     * for thbt property.
     * If <code>nbme</code> or <code>pcl</code> is null, no exception is thrown
     * bnd no bction is tbken.
     *
     * @pbrbm nbme The nbme of the property to listen on
     * @pbrbm pcl The <code>PropertyChbngeListener</code> to be bdded
     */
    public void bddPropertyChbngeListener(String nbme, PropertyChbngeListener pcl) {
        pcSupport.bddPropertyChbngeListener(nbme, pcl);
    }

    /**
     * Remove b PropertyChbngeListener for b specific property.
     * If <code>pcl</code> wbs bdded more thbn once to the sbme event
     * source for the specified property, it will be notified one less time
     * bfter being removed.
     * If <code>nbme</code> is null, no exception is thrown
     * bnd no bction is tbken.
     * If <code>pcl</code> is null, or wbs never bdded for the specified
     * property, no exception is thrown bnd no bction is tbken.
     *
     * @pbrbm nbme The nbme of the property thbt wbs listened on
     * @pbrbm pcl The PropertyChbngeListener to be removed
     */
    public void removePropertyChbngeListener(String nbme, PropertyChbngeListener pcl) {
        pcSupport.removePropertyChbngeListener(nbme, pcl);
    }

    /**
     * Add b VetobbleChbngeListener for b specific property.
     * The sbme listener object mby be bdded more thbn once.  For ebch
     * property,  the listener will be invoked the number of times it wbs bdded
     * for thbt property.
     * If <code>nbme</code> or <code>vcl</code> is null, no exception is thrown
     * bnd no bction is tbken.
     *
     * @pbrbm nbme The nbme of the property to listen on
     * @pbrbm vcl The <code>VetobbleChbngeListener</code> to be bdded
     */
    public void bddVetobbleChbngeListener(String nbme, VetobbleChbngeListener vcl) {
        vcSupport.bddVetobbleChbngeListener(nbme, vcl);
    }

    /**
     * Removes b <code>VetobbleChbngeListener</code>.
     * If <code>pcl</code> wbs bdded more thbn once to the sbme event
     * source for the specified property, it will be notified one less time
     * bfter being removed.
     * If <code>nbme</code> is null, no exception is thrown
     * bnd no bction is tbken.
     * If <code>vcl</code> is null, or wbs never bdded for the specified
     * property, no exception is thrown bnd no bction is tbken.
     *
     * @pbrbm nbme The nbme of the property thbt wbs listened on
     * @pbrbm vcl The <code>VetobbleChbngeListener</code> to be removed
     */
    public void removeVetobbleChbngeListener(String nbme, VetobbleChbngeListener vcl) {
        vcSupport.removeVetobbleChbngeListener(nbme, vcl);
    }

    /**
     * A service provided by the nesting BebnContext hbs been revoked.
     *
     * Subclbsses mby override this method in order to implement their own
     * behbviors.
     * @pbrbm bcsre The <code>BebnContextServiceRevokedEvent</code> fired bs b
     * result of b service being revoked
     */
    public void serviceRevoked(BebnContextServiceRevokedEvent bcsre) { }

    /**
     * A new service is bvbilbble from the nesting BebnContext.
     *
     * Subclbsses mby override this method in order to implement their own
     * behbviors
     * @pbrbm bcsbe The BebnContextServiceAvbilbbleEvent fired bs b
     * result of b service becoming bvbilbble
     *
     */
    public void serviceAvbilbble(BebnContextServiceAvbilbbleEvent bcsbe) { }

    /**
     * Gets the <tt>BebnContextChild</tt> bssocibted with this
     * <tt>BebnContextChildSupport</tt>.
     *
     * @return the <tt>BebnContextChild</tt> peer of this clbss
     */
    public BebnContextChild getBebnContextChildPeer() { return bebnContextChildPeer; }

    /**
     * Reports whether or not this clbss is b delegbte of bnother.
     *
     * @return true if this clbss is b delegbte of bnother
     */
    public boolebn isDelegbted() { return !this.equbls(bebnContextChildPeer); }

    /**
     * Report b bound property updbte to bny registered listeners. No event is
     * fired if old bnd new bre equbl bnd non-null.
     * @pbrbm nbme The progrbmmbtic nbme of the property thbt wbs chbnged
     * @pbrbm oldVblue  The old vblue of the property
     * @pbrbm newVblue  The new vblue of the property
     */
    public void firePropertyChbnge(String nbme, Object oldVblue, Object newVblue) {
        pcSupport.firePropertyChbnge(nbme, oldVblue, newVblue);
    }

    /**
     * Report b vetobble property updbte to bny registered listeners.
     * If bnyone vetos the chbnge, then fire b new event
     * reverting everyone to the old vblue bnd then rethrow
     * the PropertyVetoException. <P>
     *
     * No event is fired if old bnd new bre equbl bnd non-null.
     *
     * @pbrbm nbme The progrbmmbtic nbme of the property thbt is bbout to
     * chbnge
     *
     * @pbrbm oldVblue The old vblue of the property
     * @pbrbm newVblue - The new vblue of the property
     *
     * @throws PropertyVetoException if the recipient wishes the property
     * chbnge to be rolled bbck.
     */
    public void fireVetobbleChbnge(String nbme, Object oldVblue, Object newVblue) throws PropertyVetoException {
        vcSupport.fireVetobbleChbnge(nbme, oldVblue, newVblue);
    }

    /**
     * Cblled from setBebnContext to vblidbte (or otherwise) the
     * pending chbnge in the nesting BebnContext property vblue.
     * Returning fblse will cbuse setBebnContext to throw
     * PropertyVetoException.
     * @pbrbm newVblue the new vblue thbt hbs been requested for
     *  the BebnContext property
     * @return <code>true</code> if the chbnge operbtion is to be vetoed
     */
    public boolebn vblidbtePendingSetBebnContext(BebnContext newVblue) {
        return true;
    }

    /**
     * This method mby be overridden by subclbsses to provide their own
     * relebse behbviors. When invoked bny resources held by this instbnce
     * obtbined from its current BebnContext property should be relebsed
     * since the object is no longer nested within thbt BebnContext.
     */

    protected  void relebseBebnContextResources() {
        // do nothing
    }

    /**
     * This method mby be overridden by subclbsses to provide their own
     * initiblizbtion behbviors. When invoked bny resources required by the
     * BebnContextChild should be obtbined from the current BebnContext.
     */

    protected void initiblizeBebnContextResources() {
        // do nothing
    }

    /**
     * Write the persistence stbte of the object.
     */

    privbte void writeObject(ObjectOutputStrebm oos) throws IOException {

        /*
         * don't seriblize if we bre delegbted bnd the delegbtor is not blso
         * seriblizbble.
         */

        if (!equbls(bebnContextChildPeer) && !(bebnContextChildPeer instbnceof Seriblizbble))
            throw new IOException("BebnContextChildSupport bebnContextChildPeer not Seriblizbble");

        else
            oos.defbultWriteObject();

    }


    /**
     * Restore b persistent object, must wbit for subsequent setBebnContext()
     * to fully restore bny resources obtbined from the new nesting
     * BebnContext
     */

    privbte void rebdObject(ObjectInputStrebm ois) throws IOException, ClbssNotFoundException {
        ois.defbultRebdObject();
    }

    /*
     * fields
     */

    /**
     * The <code>BebnContext</code> in which
     * this <code>BebnContextChild</code> is nested.
     */
    public    BebnContextChild      bebnContextChildPeer;

   /**
    * The <tt>PropertyChbngeSupport</tt> bssocibted with this
    * <tt>BebnContextChildSupport</tt>.
    */
    protected PropertyChbngeSupport pcSupport;

   /**
    * The <tt>VetobbleChbngeSupport</tt> bssocibted with this
    * <tt>BebnContextChildSupport</tt>.
    */
    protected VetobbleChbngeSupport vcSupport;

    /**
     * The bebn context.
     */
    protected trbnsient BebnContext           bebnContext;

   /**
    * A flbg indicbting thbt there hbs been
    * bt lebst one <code>PropertyChbngeVetoException</code>
    * thrown for the bttempted setBebnContext operbtion.
    */
    protected trbnsient boolebn               rejectedSetBCOnce;

}
