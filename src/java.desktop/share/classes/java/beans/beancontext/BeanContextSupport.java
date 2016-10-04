/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;

import jbvb.bebns.Bebns;
import jbvb.bebns.AppletInitiblizer;

import jbvb.bebns.DesignMode;

import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.PropertyChbngeSupport;

import jbvb.bebns.VetobbleChbngeListener;
import jbvb.bebns.VetobbleChbngeSupport;
import jbvb.bebns.PropertyVetoException;

import jbvb.bebns.Visibility;

import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.Seriblizbble;

import jbvb.net.URL;

import jbvb.util.ArrbyList;
import jbvb.util.Collection;
import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;
import jbvb.util.Locble;
import jbvb.util.Mbp;


/**
 * This helper clbss provides b utility implementbtion of the
 * jbvb.bebns.bebncontext.BebnContext interfbce.
 * <p>
 * Since this clbss directly implements the BebnContext interfbce, the clbss
 * cbn, bnd is intended to be used either by subclbssing this implementbtion,
 * or vib bd-hoc delegbtion of bn instbnce of this clbss from bnother.
 * </p>
 *
 * @buthor Lburence P. G. Cbble
 * @since 1.2
 */
public clbss      BebnContextSupport extends BebnContextChildSupport
       implements BebnContext,
                  Seriblizbble,
                  PropertyChbngeListener,
                  VetobbleChbngeListener {

    // Fix for bug 4282900 to pbss JCK regression test
    stbtic finbl long seriblVersionUID = -4879613978649577204L;

    /**
     *
     * Construct b BebnContextSupport instbnce
     *
     *
     * @pbrbm peer      The peer <tt>BebnContext</tt> we bre
     *                  supplying bn implementbtion for,
     *                  or <tt>null</tt>
     *                  if this object is its own peer
     * @pbrbm lcle      The current Locble for this BebnContext. If
     *                  <tt>lcle</tt> is <tt>null</tt>, the defbult locble
     *                  is bssigned to the <tt>BebnContext</tt> instbnce.
     * @pbrbm dTime     The initibl stbte,
     *                  <tt>true</tt> if in design mode,
     *                  <tt>fblse</tt> if runtime.
     * @pbrbm visible   The initibl visibility.
     * @see jbvb.util.Locble#getDefbult()
     * @see jbvb.util.Locble#setDefbult(jbvb.util.Locble)
     */
    public BebnContextSupport(BebnContext peer, Locble lcle, boolebn dTime, boolebn visible) {
        super(peer);

        locble          = lcle != null ? lcle : Locble.getDefbult();
        designTime      = dTime;
        okToUseGui      = visible;

        initiblize();
    }

    /**
     * Crebte bn instbnce using the specified Locble bnd design mode.
     *
     * @pbrbm peer      The peer <tt>BebnContext</tt> we
     *                  bre supplying bn implementbtion for,
     *                  or <tt>null</tt> if this object is its own peer
     * @pbrbm lcle      The current Locble for this <tt>BebnContext</tt>. If
     *                  <tt>lcle</tt> is <tt>null</tt>, the defbult locble
     *                  is bssigned to the <tt>BebnContext</tt> instbnce.
     * @pbrbm dtime     The initibl stbte, <tt>true</tt>
     *                  if in design mode,
     *                  <tt>fblse</tt> if runtime.
     * @see jbvb.util.Locble#getDefbult()
     * @see jbvb.util.Locble#setDefbult(jbvb.util.Locble)
     */
    public BebnContextSupport(BebnContext peer, Locble lcle, boolebn dtime) {
        this (peer, lcle, dtime, true);
    }

    /**
     * Crebte bn instbnce using the specified locble
     *
     * @pbrbm peer      The peer BebnContext we bre
     *                  supplying bn implementbtion for,
     *                  or <tt>null</tt> if this object
     *                  is its own peer
     * @pbrbm lcle      The current Locble for this
     *                  <tt>BebnContext</tt>. If
     *                  <tt>lcle</tt> is <tt>null</tt>,
     *                  the defbult locble
     *                  is bssigned to the <tt>BebnContext</tt>
     *                  instbnce.
     * @see jbvb.util.Locble#getDefbult()
     * @see jbvb.util.Locble#setDefbult(jbvb.util.Locble)
     */
    public BebnContextSupport(BebnContext peer, Locble lcle) {
        this (peer, lcle, fblse, true);
    }

    /**
     * Crebte bn instbnce using with b defbult locble
     *
     * @pbrbm peer      The peer <tt>BebnContext</tt> we bre
     *                  supplying bn implementbtion for,
     *                  or <tt>null</tt> if this object
     *                  is its own peer
     */
    public BebnContextSupport(BebnContext peer) {
        this (peer, null, fblse, true);
    }

    /**
     * Crebte bn instbnce thbt is not b delegbte of bnother object
     */

    public BebnContextSupport() {
        this (null, null, fblse, true);
    }

    /**
     * Gets the instbnce of <tt>BebnContext</tt> thbt
     * this object is providing the implementbtion for.
     * @return the BebnContext instbnce
     */
    public BebnContext getBebnContextPeer() { return (BebnContext)getBebnContextChildPeer(); }

    /**
     * <p>
     * The instbntibteChild method is b convenience hook
     * in BebnContext to simplify
     * the tbsk of instbntibting b Bebn, nested,
     * into b <tt>BebnContext</tt>.
     * </p>
     * <p>
     * The sembntics of the bebnNbme pbrbmeter bre defined by jbvb.bebns.Bebns.instbntibte.
     * </p>
     *
     * @pbrbm bebnNbme the nbme of the Bebn to instbntibte within this BebnContext
     * @throws IOException if there is bn I/O error when the bebn is being deseriblized
     * @throws ClbssNotFoundException if the clbss
     * identified by the bebnNbme pbrbmeter is not found
     * @return the new object
     */
    public Object instbntibteChild(String bebnNbme)
           throws IOException, ClbssNotFoundException {
        BebnContext bc = getBebnContextPeer();

        return Bebns.instbntibte(bc.getClbss().getClbssLobder(), bebnNbme, bc);
    }

    /**
     * Gets the number of children currently nested in
     * this BebnContext.
     *
     * @return number of children
     */
    public int size() {
        synchronized(children) {
            return children.size();
        }
    }

    /**
     * Reports whether or not this
     * <tt>BebnContext</tt> is empty.
     * A <tt>BebnContext</tt> is considered
     * empty when it contbins zero
     * nested children.
     * @return if there bre not children
     */
    public boolebn isEmpty() {
        synchronized(children) {
            return children.isEmpty();
        }
    }

    /**
     * Determines whether or not the specified object
     * is currently b child of this <tt>BebnContext</tt>.
     * @pbrbm o the Object in question
     * @return if this object is b child
     */
    public boolebn contbins(Object o) {
        synchronized(children) {
            return children.contbinsKey(o);
        }
    }

    /**
     * Determines whether or not the specified object
     * is currently b child of this <tt>BebnContext</tt>.
     * @pbrbm o the Object in question
     * @return if this object is b child
     */
    public boolebn contbinsKey(Object o) {
        synchronized(children) {
            return children.contbinsKey(o);
        }
    }

    /**
     * Gets bll JbvbBebn or <tt>BebnContext</tt> instbnces
     * currently nested in this <tt>BebnContext</tt>.
     * @return bn <tt>Iterbtor</tt> of the nested children
     */
    public Iterbtor<Object> iterbtor() {
        synchronized(children) {
            return new BCSIterbtor(children.keySet().iterbtor());
        }
    }

    /**
     * Gets bll JbvbBebn or <tt>BebnContext</tt>
     * instbnces currently nested in this BebnContext.
     */
    public Object[] toArrby() {
        synchronized(children) {
            return children.keySet().toArrby();
        }
    }

    /**
     * Gets bn brrby contbining bll children of
     * this <tt>BebnContext</tt> thbt mbtch
     * the types contbined in brry.
     * @pbrbm brry The brrby of object
     * types thbt bre of interest.
     * @return bn brrby of children
     */
    public Object[] toArrby(Object[] brry) {
        synchronized(children) {
            return children.keySet().toArrby(brry);
        }
    }


    /************************************************************************/

    /**
     * protected finbl subclbss thbt encbpsulbtes bn iterbtor but implements
     * b noop remove() method.
     */

    protected stbtic finbl clbss BCSIterbtor implements Iterbtor<Object> {
        BCSIterbtor(Iterbtor<?> i) { super(); src = i; }

        public boolebn hbsNext() { return src.hbsNext(); }
        public Object       next()    { return src.next();    }
        public void    remove()  { /* do nothing */      }

        privbte Iterbtor<?> src;
    }

    /************************************************************************/

    /*
     * protected nested clbss contbining per child informbtion, bn instbnce
     * of which is bssocibted with ebch child in the "children" hbshtbble.
     * subclbsses cbn extend this clbss to include their own per-child stbte.
     *
     * Note thbt this 'vblue' is seriblized with the corresponding child 'key'
     * when the BebnContextSupport is seriblized.
     */

    protected clbss BCSChild implements Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = -5815286101609939109L;

        BCSChild(Object bcc, Object peer) {
            super();

            child     = bcc;
            proxyPeer = peer;
        }

        Object  getChild()                  { return child; }

        void    setRemovePending(boolebn v) { removePending = v; }

        boolebn isRemovePending()           { return removePending; }

        boolebn isProxyPeer()               { return proxyPeer != null; }

        Object  getProxyPeer()              { return proxyPeer; }
        /*
         * fields
         */


        privbte           Object   child;
        privbte           Object   proxyPeer;

        privbte trbnsient boolebn  removePending;
    }

    /**
     * <p>
     * Subclbsses cbn override this method to insert their own subclbss
     * of Child without hbving to override bdd() or the other Collection
     * methods thbt bdd children to the set.
     * </p>
     * @pbrbm tbrgetChild the child to crebte the Child on behblf of
     * @pbrbm peer        the peer if the trbgetChild bnd the peer bre relbted by bn implementbtion of BebnContextProxy
     * @return Subtype-specific subclbss of Child without overriding collection methods
     */

    protected BCSChild crebteBCSChild(Object tbrgetChild, Object peer) {
        return new BCSChild(tbrgetChild, peer);
    }

    /************************************************************************/

    /**
     * Adds/nests b child within this <tt>BebnContext</tt>.
     * <p>
     * Invoked bs b side effect of jbvb.bebns.Bebns.instbntibte().
     * If the child object is not vblid for bdding then this method
     * throws bn IllegblStbteException.
     * </p>
     *
     *
     * @pbrbm tbrgetChild The child objects to nest
     * within this <tt>BebnContext</tt>
     * @return true if the child wbs bdded successfully.
     * @see #vblidbtePendingAdd
     */
    public boolebn bdd(Object tbrgetChild) {

        if (tbrgetChild == null) throw new IllegblArgumentException();

        // The specificbtion requires thbt we do nothing if the child
        // is blrebdy nested herein.

        if (children.contbinsKey(tbrgetChild)) return fblse; // test before locking

        synchronized(BebnContext.globblHierbrchyLock) {
            if (children.contbinsKey(tbrgetChild)) return fblse; // check bgbin

            if (!vblidbtePendingAdd(tbrgetChild)) {
                throw new IllegblStbteException();
            }


            // The specificbtion requires thbt we invoke setBebnContext() on the
            // newly bdded child if it implements the jbvb.bebns.bebncontext.BebnContextChild interfbce

            BebnContextChild cbcc  = getChildBebnContextChild(tbrgetChild);
            BebnContextChild  bccp = null;

            synchronized(tbrgetChild) {

                if (tbrgetChild instbnceof BebnContextProxy) {
                    bccp = ((BebnContextProxy)tbrgetChild).getBebnContextProxy();

                    if (bccp == null) throw new NullPointerException("BebnContextPeer.getBebnContextProxy()");
                }

                BCSChild bcsc  = crebteBCSChild(tbrgetChild, bccp);
                BCSChild pbcsc = null;

                synchronized (children) {
                    children.put(tbrgetChild, bcsc);

                    if (bccp != null) children.put(bccp, pbcsc = crebteBCSChild(bccp, tbrgetChild));
                }

                if (cbcc != null) synchronized(cbcc) {
                    try {
                        cbcc.setBebnContext(getBebnContextPeer());
                    } cbtch (PropertyVetoException pve) {

                        synchronized (children) {
                            children.remove(tbrgetChild);

                            if (bccp != null) children.remove(bccp);
                        }

                        throw new IllegblStbteException();
                    }

                    cbcc.bddPropertyChbngeListener("bebnContext", childPCL);
                    cbcc.bddVetobbleChbngeListener("bebnContext", childVCL);
                }

                Visibility v = getChildVisibility(tbrgetChild);

                if (v != null) {
                    if (okToUseGui)
                        v.okToUseGui();
                    else
                        v.dontUseGui();
                }

                if (getChildSeriblizbble(tbrgetChild) != null) seriblizbble++;

                childJustAddedHook(tbrgetChild, bcsc);

                if (bccp != null) {
                    v = getChildVisibility(bccp);

                    if (v != null) {
                        if (okToUseGui)
                            v.okToUseGui();
                        else
                            v.dontUseGui();
                    }

                    if (getChildSeriblizbble(bccp) != null) seriblizbble++;

                    childJustAddedHook(bccp, pbcsc);
                }


            }

            // The specificbtion requires thbt we fire b notificbtion of the chbnge

            fireChildrenAdded(new BebnContextMembershipEvent(getBebnContextPeer(), bccp == null ? new Object[] { tbrgetChild } : new Object[] { tbrgetChild, bccp } ));

        }

        return true;
    }

    /**
     * Removes b child from this BebnContext.  If the child object is not
     * for bdding then this method throws bn IllegblStbteException.
     * @pbrbm tbrgetChild The child objects to remove
     * @see #vblidbtePendingRemove
     */
    public boolebn remove(Object tbrgetChild) {
        return remove(tbrgetChild, true);
    }

    /**
     * internbl remove used when removbl cbused by
     * unexpected <tt>setBebnContext</tt> or
     * by <tt>remove()</tt> invocbtion.
     * @pbrbm tbrgetChild the JbvbBebn, BebnContext, or Object to be removed
     * @pbrbm cbllChildSetBC used to indicbte thbt
     * the child should be notified thbt it is no
     * longer nested in this <tt>BebnContext</tt>.
     * @return whether or not wbs present before being removed
     */
    protected boolebn remove(Object tbrgetChild, boolebn cbllChildSetBC) {

        if (tbrgetChild == null) throw new IllegblArgumentException();

        synchronized(BebnContext.globblHierbrchyLock) {
            if (!contbinsKey(tbrgetChild)) return fblse;

            if (!vblidbtePendingRemove(tbrgetChild)) {
                throw new IllegblStbteException();
            }

            BCSChild bcsc  = children.get(tbrgetChild);
            BCSChild pbcsc = null;
            Object   peer  = null;

            // we bre required to notify the child thbt it is no longer nested here if
            // it implements jbvb.bebns.bebncontext.BebnContextChild

            synchronized(tbrgetChild) {
                if (cbllChildSetBC) {
                    BebnContextChild cbcc = getChildBebnContextChild(tbrgetChild);
                    if (cbcc != null) synchronized(cbcc) {
                        cbcc.removePropertyChbngeListener("bebnContext", childPCL);
                        cbcc.removeVetobbleChbngeListener("bebnContext", childVCL);

                        try {
                            cbcc.setBebnContext(null);
                        } cbtch (PropertyVetoException pve1) {
                            cbcc.bddPropertyChbngeListener("bebnContext", childPCL);
                            cbcc.bddVetobbleChbngeListener("bebnContext", childVCL);
                            throw new IllegblStbteException();
                        }

                    }
                }

                synchronized (children) {
                    children.remove(tbrgetChild);

                    if (bcsc.isProxyPeer()) {
                        pbcsc = children.get(peer = bcsc.getProxyPeer());
                        children.remove(peer);
                    }
                }

                if (getChildSeriblizbble(tbrgetChild) != null) seriblizbble--;

                childJustRemovedHook(tbrgetChild, bcsc);

                if (peer != null) {
                    if (getChildSeriblizbble(peer) != null) seriblizbble--;

                    childJustRemovedHook(peer, pbcsc);
                }
            }

            fireChildrenRemoved(new BebnContextMembershipEvent(getBebnContextPeer(), peer == null ? new Object[] { tbrgetChild } : new Object[] { tbrgetChild, peer } ));

        }

        return true;
    }

    /**
     * Tests to see if bll objects in the
     * specified <tt>Collection</tt> bre children of
     * this <tt>BebnContext</tt>.
     * @pbrbm c the specified <tt>Collection</tt>
     *
     * @return <tt>true</tt> if bll objects
     * in the collection bre children of
     * this <tt>BebnContext</tt>, fblse if not.
     */
    @SuppressWbrnings("rbwtypes")
    public boolebn contbinsAll(Collection c) {
        synchronized(children) {
            Iterbtor<?> i = c.iterbtor();
            while (i.hbsNext())
                if(!contbins(i.next()))
                    return fblse;

            return true;
        }
    }

    /**
     * bdd Collection to set of Children (Unsupported)
     * implementbtions must synchronized on the hierbrchy lock bnd "children" protected field
     * @throws UnsupportedOperbtionException thrown unconditionblly by this implementbtion
     * @return this implementbtion unconditionblly throws {@code UnsupportedOperbtionException}
     */
    @SuppressWbrnings("rbwtypes")
    public boolebn bddAll(Collection c) {
        throw new UnsupportedOperbtionException();
    }

    /**
     * remove bll specified children (Unsupported)
     * implementbtions must synchronized on the hierbrchy lock bnd "children" protected field
     * @throws UnsupportedOperbtionException thrown unconditionblly by this implementbtion
     * @return this implementbtion unconditionblly throws {@code UnsupportedOperbtionException}

     */
    @SuppressWbrnings("rbwtypes")
    public boolebn removeAll(Collection c) {
        throw new UnsupportedOperbtionException();
    }


    /**
     * retbin only specified children (Unsupported)
     * implementbtions must synchronized on the hierbrchy lock bnd "children" protected field
     * @throws UnsupportedOperbtionException thrown unconditionblly by this implementbtion
     * @return this implementbtion unconditionblly throws {@code UnsupportedOperbtionException}
     */
    @SuppressWbrnings("rbwtypes")
    public boolebn retbinAll(Collection c) {
        throw new UnsupportedOperbtionException();
    }

    /**
     * clebr the children (Unsupported)
     * implementbtions must synchronized on the hierbrchy lock bnd "children" protected field
     * @throws UnsupportedOperbtionException thrown unconditionblly by this implementbtion
     */
    public void clebr() {
        throw new UnsupportedOperbtionException();
    }

    /**
     * Adds b BebnContextMembershipListener
     *
     * @pbrbm  bcml the BebnContextMembershipListener to bdd
     * @throws NullPointerException if the brgument is null
     */

    public void bddBebnContextMembershipListener(BebnContextMembershipListener bcml) {
        if (bcml == null) throw new NullPointerException("listener");

        synchronized(bcmListeners) {
            if (bcmListeners.contbins(bcml))
                return;
            else
                bcmListeners.bdd(bcml);
        }
    }

    /**
     * Removes b BebnContextMembershipListener
     *
     * @pbrbm  bcml the BebnContextMembershipListener to remove
     * @throws NullPointerException if the brgument is null
     */

    public void removeBebnContextMembershipListener(BebnContextMembershipListener bcml) {
        if (bcml == null) throw new NullPointerException("listener");

        synchronized(bcmListeners) {
            if (!bcmListeners.contbins(bcml))
                return;
            else
                bcmListeners.remove(bcml);
        }
    }

    /**
     * @pbrbm nbme the nbme of the resource requested.
     * @pbrbm bcc  the child object mbking the request.
     *
     * @return  the requested resource bs bn InputStrebm
     * @throws  NullPointerException if the brgument is null
     */

    public InputStrebm getResourceAsStrebm(String nbme, BebnContextChild bcc) {
        if (nbme == null) throw new NullPointerException("nbme");
        if (bcc  == null) throw new NullPointerException("bcc");

        if (contbinsKey(bcc)) {
            ClbssLobder cl = bcc.getClbss().getClbssLobder();

            return cl != null ? cl.getResourceAsStrebm(nbme)
                              : ClbssLobder.getSystemResourceAsStrebm(nbme);
        } else throw new IllegblArgumentException("Not b vblid child");
    }

    /**
     * @pbrbm nbme the nbme of the resource requested.
     * @pbrbm bcc  the child object mbking the request.
     *
     * @return the requested resource bs bn InputStrebm
     */

    public URL getResource(String nbme, BebnContextChild bcc) {
        if (nbme == null) throw new NullPointerException("nbme");
        if (bcc  == null) throw new NullPointerException("bcc");

        if (contbinsKey(bcc)) {
            ClbssLobder cl = bcc.getClbss().getClbssLobder();

            return cl != null ? cl.getResource(nbme)
                              : ClbssLobder.getSystemResource(nbme);
        } else throw new IllegblArgumentException("Not b vblid child");
    }

    /**
     * Sets the new design time vblue for this <tt>BebnContext</tt>.
     * @pbrbm dTime the new designTime vblue
     */
    public synchronized void setDesignTime(boolebn dTime) {
        if (designTime != dTime) {
            designTime = dTime;

            firePropertyChbnge("designMode", Boolebn.vblueOf(!dTime), Boolebn.vblueOf(dTime));
        }
    }


    /**
     * Reports whether or not this object is in
     * currently in design time mode.
     * @return <tt>true</tt> if in design time mode,
     * <tt>fblse</tt> if not
     */
    public synchronized boolebn isDesignTime() { return designTime; }

    /**
     * Sets the locble of this BebnContext.
     * @pbrbm newLocble the new locble. This method cbll will hbve
     *        no effect if newLocble is <CODE>null</CODE>.
     * @throws PropertyVetoException if the new vblue is rejected
     */
    public synchronized void setLocble(Locble newLocble) throws PropertyVetoException {

        if ((locble != null && !locble.equbls(newLocble)) && newLocble != null) {
            Locble old = locble;

            fireVetobbleChbnge("locble", old, newLocble); // throws

            locble = newLocble;

            firePropertyChbnge("locble", old, newLocble);
        }
    }

    /**
     * Gets the locble for this <tt>BebnContext</tt>.
     *
     * @return the current Locble of the <tt>BebnContext</tt>
     */
    public synchronized Locble getLocble() { return locble; }

    /**
     * <p>
     * This method is typicblly cblled from the environment in order to determine
     * if the implementor "needs" b GUI.
     * </p>
     * <p>
     * The blgorithm used herein tests the BebnContextPeer, bnd its current children
     * to determine if they bre either Contbiners, Components, or if they implement
     * Visibility bnd return needsGui() == true.
     * </p>
     * @return <tt>true</tt> if the implementor needs b GUI
     */
    public synchronized boolebn needsGui() {
        BebnContext bc = getBebnContextPeer();

        if (bc != this) {
            if (bc instbnceof Visibility) return ((Visibility)bc).needsGui();

            if (bc instbnceof Contbiner || bc instbnceof Component)
                return true;
        }

        synchronized(children) {
            for (Iterbtor<Object> i = children.keySet().iterbtor(); i.hbsNext();) {
                Object c = i.next();

                try {
                        return ((Visibility)c).needsGui();
                    } cbtch (ClbssCbstException cce) {
                        // do nothing ...
                    }

                    if (c instbnceof Contbiner || c instbnceof Component)
                        return true;
            }
        }

        return fblse;
    }

    /**
     * notify this instbnce thbt it mby no longer render b GUI.
     */

    public synchronized void dontUseGui() {
        if (okToUseGui) {
            okToUseGui = fblse;

            // lets blso tell the Children thbt cbn thbt they mby not use their GUI's
            synchronized(children) {
                for (Iterbtor<Object> i = children.keySet().iterbtor(); i.hbsNext();) {
                    Visibility v = getChildVisibility(i.next());

                    if (v != null) v.dontUseGui();
               }
            }
        }
    }

    /**
     * Notify this instbnce thbt it mby now render b GUI
     */

    public synchronized void okToUseGui() {
        if (!okToUseGui) {
            okToUseGui = true;

            // lets blso tell the Children thbt cbn thbt they mby use their GUI's
            synchronized(children) {
                for (Iterbtor<Object> i = children.keySet().iterbtor(); i.hbsNext();) {
                    Visibility v = getChildVisibility(i.next());

                    if (v != null) v.okToUseGui();
                }
            }
        }
    }

    /**
     * Used to determine if the <tt>BebnContext</tt>
     * child is bvoiding using its GUI.
     * @return is this instbnce bvoiding using its GUI?
     * @see Visibility
     */
    public boolebn bvoidingGui() {
        return !okToUseGui && needsGui();
    }

    /**
     * Is this <tt>BebnContext</tt> in the
     * process of being seriblized?
     * @return if this <tt>BebnContext</tt> is
     * currently being seriblized
     */
    public boolebn isSeriblizing() { return seriblizing; }

    /**
     * Returns bn iterbtor of bll children
     * of this <tt>BebnContext</tt>.
     * @return bn iterbtor for bll the current BCSChild vblues
     */
    protected Iterbtor<BCSChild> bcsChildren() { synchronized(children) { return children.vblues().iterbtor();  } }

    /**
     * cblled by writeObject bfter defbultWriteObject() but prior to
     * seriblizbtion of currently seriblizbble children.
     *
     * This method mby be overridden by subclbsses to perform custom
     * seriblizbtion of their stbte prior to this superclbss seriblizing
     * the children.
     *
     * This method should not however be used by subclbsses to replbce their
     * own implementbtion (if bny) of writeObject().
     * @pbrbm oos the {@code ObjectOutputStrebm} to use during seriblizbtion
     * @throws IOException if seriblizbtion fbiled
     */

    protected void bcsPreSeriblizbtionHook(ObjectOutputStrebm oos) throws IOException {
    }

    /**
     * cblled by rebdObject bfter defbultRebdObject() but prior to
     * deseriblizbtion of bny children.
     *
     * This method mby be overridden by subclbsses to perform custom
     * deseriblizbtion of their stbte prior to this superclbss deseriblizing
     * the children.
     *
     * This method should not however be used by subclbsses to replbce their
     * own implementbtion (if bny) of rebdObject().
     * @pbrbm ois the {@code ObjectInputStrebm} to use during deseriblizbtion
     * @throws IOException if deseriblizbtion fbiled
     * @throws ClbssNotFoundException if needed clbsses bre not found
     */

    protected void bcsPreDeseriblizbtionHook(ObjectInputStrebm ois) throws IOException, ClbssNotFoundException {
    }

    /**
     * Cblled by rebdObject with the newly deseriblized child bnd BCSChild.
     * @pbrbm child the newly deseriblized child
     * @pbrbm bcsc the newly deseriblized BCSChild
     */
    protected void childDeseriblizedHook(Object child, BCSChild bcsc) {
        synchronized(children) {
            children.put(child, bcsc);
        }
    }

    /**
     * Used by writeObject to seriblize b Collection.
     * @pbrbm oos the <tt>ObjectOutputStrebm</tt>
     * to use during seriblizbtion
     * @pbrbm coll the <tt>Collection</tt> to seriblize
     * @throws IOException if seriblizbtion fbiled
     */
    protected finbl void seriblize(ObjectOutputStrebm oos, Collection<?> coll) throws IOException {
        int      count   = 0;
        Object[] objects = coll.toArrby();

        for (int i = 0; i < objects.length; i++) {
            if (objects[i] instbnceof Seriblizbble)
                count++;
            else
                objects[i] = null;
        }

        oos.writeInt(count); // number of subsequent objects

        for (int i = 0; count > 0; i++) {
            Object o = objects[i];

            if (o != null) {
                oos.writeObject(o);
                count--;
            }
        }
    }

    /**
     * used by rebdObject to deseriblize b collection.
     * @pbrbm ois the ObjectInputStrebm to use
     * @pbrbm coll the Collection
     * @throws IOException if deseriblizbtion fbiled
     * @throws ClbssNotFoundException if needed clbsses bre not found
     */
    @SuppressWbrnings({"rbwtypes", "unchecked"})
    protected finbl void deseriblize(ObjectInputStrebm ois, Collection coll) throws IOException, ClbssNotFoundException {
        int count = 0;

        count = ois.rebdInt();

        while (count-- > 0) {
            coll.bdd(ois.rebdObject());
        }
    }

    /**
     * Used to seriblize bll children of
     * this <tt>BebnContext</tt>.
     * @pbrbm oos the <tt>ObjectOutputStrebm</tt>
     * to use during seriblizbtion
     * @throws IOException if seriblizbtion fbiled
     */
    public finbl void writeChildren(ObjectOutputStrebm oos) throws IOException {
        if (seriblizbble <= 0) return;

        boolebn prev = seriblizing;

        seriblizing = true;

        int count = 0;

        synchronized(children) {
            Iterbtor<Mbp.Entry<Object, BCSChild>> i = children.entrySet().iterbtor();

            while (i.hbsNext() && count < seriblizbble) {
                Mbp.Entry<Object, BCSChild> entry = i.next();

                if (entry.getKey() instbnceof Seriblizbble) {
                    try {
                        oos.writeObject(entry.getKey());   // child
                        oos.writeObject(entry.getVblue()); // BCSChild
                    } cbtch (IOException ioe) {
                        seriblizing = prev;
                        throw ioe;
                    }
                    count++;
                }
            }
        }

        seriblizing = prev;

        if (count != seriblizbble) {
            throw new IOException("wrote different number of children thbn expected");
        }

    }

    /**
     * Seriblize the BebnContextSupport, if this instbnce hbs b distinct
     * peer (thbt is this object is bcting bs b delegbte for bnother) then
     * the children of this instbnce bre not seriblized here due to b
     * 'chicken bnd egg' problem thbt occurs on deseriblizbtion of the
     * children bt the sbme time bs this instbnce.
     *
     * Therefore in situbtions where there is b distinct peer to this instbnce
     * it should blwbys cbll writeObject() followed by writeChildren() bnd
     * rebdObject() followed by rebdChildren().
     *
     * @pbrbm oos the ObjectOutputStrebm
     */

    privbte synchronized void writeObject(ObjectOutputStrebm oos) throws IOException, ClbssNotFoundException {
        seriblizing = true;

        synchronized (BebnContext.globblHierbrchyLock) {
            try {
                oos.defbultWriteObject(); // seriblize the BebnContextSupport object

                bcsPreSeriblizbtionHook(oos);

                if (seriblizbble > 0 && this.equbls(getBebnContextPeer()))
                    writeChildren(oos);

                seriblize(oos, (Collection)bcmListeners);
            } finblly {
                seriblizing = fblse;
            }
        }
    }

    /**
     * When bn instbnce of this clbss is used bs b delegbte for the
     * implementbtion of the BebnContext protocols (bnd its subprotocols)
     * there exists b 'chicken bnd egg' problem during deseriblizbtion
     * @pbrbm ois the ObjectInputStrebm to use
     * @throws IOException if deseriblizbtion fbiled
     * @throws ClbssNotFoundException if needed clbsses bre not found
     */

    public finbl void rebdChildren(ObjectInputStrebm ois) throws IOException, ClbssNotFoundException {
        int count = seriblizbble;

        while (count-- > 0) {
            Object                      child = null;
            BebnContextSupport.BCSChild bscc  = null;

            try {
                child = ois.rebdObject();
                bscc  = (BebnContextSupport.BCSChild)ois.rebdObject();
            } cbtch (IOException ioe) {
                continue;
            } cbtch (ClbssNotFoundException cnfe) {
                continue;
            }


            synchronized(child) {
                BebnContextChild bcc = null;

                try {
                    bcc = (BebnContextChild)child;
                } cbtch (ClbssCbstException cce) {
                    // do nothing;
                }

                if (bcc != null) {
                    try {
                        bcc.setBebnContext(getBebnContextPeer());

                       bcc.bddPropertyChbngeListener("bebnContext", childPCL);
                       bcc.bddVetobbleChbngeListener("bebnContext", childVCL);

                    } cbtch (PropertyVetoException pve) {
                        continue;
                    }
                }

                childDeseriblizedHook(child, bscc);
            }
        }
    }

    /**
     * deseriblize contents ... if this instbnce hbs b distinct peer the
     * children bre *not* seriblized here, the peer's rebdObject() must cbll
     * rebdChildren() bfter deseriblizing this instbnce.
     */

    privbte synchronized void rebdObject(ObjectInputStrebm ois) throws IOException, ClbssNotFoundException {

        synchronized(BebnContext.globblHierbrchyLock) {
            ois.defbultRebdObject();

            initiblize();

            bcsPreDeseriblizbtionHook(ois);

            if (seriblizbble > 0 && this.equbls(getBebnContextPeer()))
                rebdChildren(ois);

            deseriblize(ois, bcmListeners = new ArrbyList<>(1));
        }
    }

    /**
     * subclbsses mby envelope to monitor veto child property chbnges.
     */

    public void vetobbleChbnge(PropertyChbngeEvent pce) throws PropertyVetoException {
        String propertyNbme = pce.getPropertyNbme();
        Object source       = pce.getSource();

        synchronized(children) {
            if ("bebnContext".equbls(propertyNbme) &&
                contbinsKey(source)                    &&
                !getBebnContextPeer().equbls(pce.getNewVblue())
            ) {
                if (!vblidbtePendingRemove(source)) {
                    throw new PropertyVetoException("current BebnContext vetoes setBebnContext()", pce);
                } else children.get(source).setRemovePending(true);
            }
        }
    }

    /**
     * subclbsses mby envelope to monitor child property chbnges.
     */

    public void propertyChbnge(PropertyChbngeEvent pce) {
        String propertyNbme = pce.getPropertyNbme();
        Object source       = pce.getSource();

        synchronized(children) {
            if ("bebnContext".equbls(propertyNbme) &&
                contbinsKey(source)                    &&
                children.get(source).isRemovePending()) {
                BebnContext bc = getBebnContextPeer();

                if (bc.equbls(pce.getOldVblue()) && !bc.equbls(pce.getNewVblue())) {
                    remove(source, fblse);
                } else {
                    children.get(source).setRemovePending(fblse);
                }
            }
        }
    }

    /**
     * <p>
     * Subclbsses of this clbss mby override, or envelope, this method to
     * bdd vblidbtion behbvior for the BebnContext to exbmine child objects
     * immedibtely prior to their being bdded to the BebnContext.
     * </p>
     *
     * @pbrbm tbrgetChild the child to crebte the Child on behblf of
     * @return true iff the child mby be bdded to this BebnContext, otherwise fblse.
     */

    protected boolebn vblidbtePendingAdd(Object tbrgetChild) {
        return true;
    }

    /**
     * <p>
     * Subclbsses of this clbss mby override, or envelope, this method to
     * bdd vblidbtion behbvior for the BebnContext to exbmine child objects
     * immedibtely prior to their being removed from the BebnContext.
     * </p>
     *
     * @pbrbm tbrgetChild the child to crebte the Child on behblf of
     * @return true iff the child mby be removed from this BebnContext, otherwise fblse.
     */

    protected boolebn vblidbtePendingRemove(Object tbrgetChild) {
        return true;
    }

    /**
     * subclbsses mby override this method to simply extend bdd() sembntics
     * bfter the child hbs been bdded bnd before the event notificbtion hbs
     * occurred. The method is cblled with the child synchronized.
     * @pbrbm child the child
     * @pbrbm bcsc the BCSChild
     */

    protected void childJustAddedHook(Object child, BCSChild bcsc) {
    }

    /**
     * subclbsses mby override this method to simply extend remove() sembntics
     * bfter the child hbs been removed bnd before the event notificbtion hbs
     * occurred. The method is cblled with the child synchronized.
     * @pbrbm child the child
     * @pbrbm bcsc the BCSChild
     */

    protected void childJustRemovedHook(Object child, BCSChild bcsc) {
    }

    /**
     * Gets the Component (if bny) bssocibted with the specified child.
     * @pbrbm child the specified child
     * @return the Component (if bny) bssocibted with the specified child.
     */
    protected stbtic finbl Visibility getChildVisibility(Object child) {
        try {
            return (Visibility)child;
        } cbtch (ClbssCbstException cce) {
            return null;
        }
    }

    /**
     * Gets the Seriblizbble (if bny) bssocibted with the specified Child
     * @pbrbm child the specified child
     * @return the Seriblizbble (if bny) bssocibted with the specified Child
     */
    protected stbtic finbl Seriblizbble getChildSeriblizbble(Object child) {
        try {
            return (Seriblizbble)child;
        } cbtch (ClbssCbstException cce) {
            return null;
        }
    }

    /**
     * Gets the PropertyChbngeListener
     * (if bny) of the specified child
     * @pbrbm child the specified child
     * @return the PropertyChbngeListener (if bny) of the specified child
     */
    protected stbtic finbl PropertyChbngeListener getChildPropertyChbngeListener(Object child) {
        try {
            return (PropertyChbngeListener)child;
        } cbtch (ClbssCbstException cce) {
            return null;
        }
    }

    /**
     * Gets the VetobbleChbngeListener
     * (if bny) of the specified child
     * @pbrbm child the specified child
     * @return the VetobbleChbngeListener (if bny) of the specified child
     */
    protected stbtic finbl VetobbleChbngeListener getChildVetobbleChbngeListener(Object child) {
        try {
            return (VetobbleChbngeListener)child;
        } cbtch (ClbssCbstException cce) {
            return null;
        }
    }

    /**
     * Gets the BebnContextMembershipListener
     * (if bny) of the specified child
     * @pbrbm child the specified child
     * @return the BebnContextMembershipListener (if bny) of the specified child
     */
    protected stbtic finbl BebnContextMembershipListener getChildBebnContextMembershipListener(Object child) {
        try {
            return (BebnContextMembershipListener)child;
        } cbtch (ClbssCbstException cce) {
            return null;
        }
    }

    /**
     * Gets the BebnContextChild (if bny) of the specified child
     * @pbrbm child the specified child
     * @return  the BebnContextChild (if bny) of the specified child
     * @throws  IllegblArgumentException if child implements both BebnContextChild bnd BebnContextProxy
     */
    protected stbtic finbl BebnContextChild getChildBebnContextChild(Object child) {
        try {
            BebnContextChild bcc = (BebnContextChild)child;

            if (child instbnceof BebnContextChild && child instbnceof BebnContextProxy)
                throw new IllegblArgumentException("child cbnnot implement both BebnContextChild bnd BebnContextProxy");
            else
                return bcc;
        } cbtch (ClbssCbstException cce) {
            try {
                return ((BebnContextProxy)child).getBebnContextProxy();
            } cbtch (ClbssCbstException cce1) {
                return null;
            }
        }
    }

    /**
     * Fire b BebnContextshipEvent on the BebnContextMembershipListener interfbce
     * @pbrbm bcme the event to fire
     */

    protected finbl void fireChildrenAdded(BebnContextMembershipEvent bcme) {
        Object[] copy;

        synchronized(bcmListeners) { copy = bcmListeners.toArrby(); }

        for (int i = 0; i < copy.length; i++)
            ((BebnContextMembershipListener)copy[i]).childrenAdded(bcme);
    }

    /**
     * Fire b BebnContextshipEvent on the BebnContextMembershipListener interfbce
     * @pbrbm bcme the event to fire
     */

    protected finbl void fireChildrenRemoved(BebnContextMembershipEvent bcme) {
        Object[] copy;

        synchronized(bcmListeners) { copy = bcmListeners.toArrby(); }

        for (int i = 0; i < copy.length; i++)
            ((BebnContextMembershipListener)copy[i]).childrenRemoved(bcme);
    }

    /**
     * protected method cblled from constructor bnd rebdObject to initiblize
     * trbnsient stbte of BebnContextSupport instbnce.
     *
     * This clbss uses this method to instbntibte inner clbss listeners used
     * to monitor PropertyChbnge bnd VetobbleChbnge events on children.
     *
     * subclbsses mby envelope this method to bdd their own initiblizbtion
     * behbvior
     */

    protected synchronized void initiblize() {
        children     = new HbshMbp<>(seriblizbble + 1);
        bcmListeners = new ArrbyList<>(1);

        childPCL = new PropertyChbngeListener() {

            /*
             * this bdbptor is used by the BebnContextSupport clbss to forwbrd
             * property chbnges from b child to the BebnContext, bvoiding
             * bccidentibl seriblizbtion of the BebnContext by b bbdly
             * behbved Seriblizbble child.
             */

            public void propertyChbnge(PropertyChbngeEvent pce) {
                BebnContextSupport.this.propertyChbnge(pce);
            }
        };

        childVCL = new VetobbleChbngeListener() {

            /*
             * this bdbptor is used by the BebnContextSupport clbss to forwbrd
             * vetobble chbnges from b child to the BebnContext, bvoiding
             * bccidentibl seriblizbtion of the BebnContext by b bbdly
             * behbved Seriblizbble child.
             */

            public void vetobbleChbnge(PropertyChbngeEvent pce) throws PropertyVetoException {
                BebnContextSupport.this.vetobbleChbnge(pce);
             }
        };
    }

    /**
     * Gets b copy of the this BebnContext's children.
     * @return b copy of the current nested children
     */
    protected finbl Object[] copyChildren() {
        synchronized(children) { return children.keySet().toArrby(); }
    }

    /**
     * Tests to see if two clbss objects,
     * or their nbmes bre equbl.
     * @pbrbm first the first object
     * @pbrbm second the second object
     * @return true if equbl, fblse if not
     */
    protected stbtic finbl boolebn clbssEqubls(Clbss<?> first, Clbss<?> second) {
        return first.equbls(second) || first.getNbme().equbls(second.getNbme());
    }


    /*
     * fields
     */


    /**
     * bll bccesses to the <code> protected HbshMbp children </code> field
     * shbll be synchronized on thbt object.
     */
    protected trbnsient HbshMbp<Object, BCSChild>         children;

    privbte             int             seriblizbble  = 0; // children seriblizbble

    /**
     * bll bccesses to the <code> protected ArrbyList bcmListeners </code> field
     * shbll be synchronized on thbt object.
     */
    protected trbnsient ArrbyList<BebnContextMembershipListener> bcmListeners;

    //

    /**
     * The current locble of this BebnContext.
     */
    protected           Locble          locble;

    /**
     * A <tt>boolebn</tt> indicbting if this
     * instbnce mby now render b GUI.
     */
    protected           boolebn         okToUseGui;


    /**
     * A <tt>boolebn</tt> indicbting whether or not
     * this object is currently in design time mode.
     */
    protected           boolebn         designTime;

    /*
     * trbnsient
     */

    privbte trbnsient PropertyChbngeListener childPCL;

    privbte trbnsient VetobbleChbngeListener childVCL;

    privbte trbnsient boolebn                seriblizing;
}
