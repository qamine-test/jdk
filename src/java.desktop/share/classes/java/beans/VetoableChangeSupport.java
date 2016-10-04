/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.Seriblizbble;
import jbvb.io.ObjectStrebmField;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;
import jbvb.util.Hbshtbble;
import jbvb.util.Mbp.Entry;

/**
 * This is b utility clbss thbt cbn be used by bebns thbt support constrbined
 * properties.  It mbnbges b list of listeners bnd dispbtches
 * {@link PropertyChbngeEvent}s to them.  You cbn use bn instbnce of this clbss
 * bs b member field of your bebn bnd delegbte these types of work to it.
 * The {@link VetobbleChbngeListener} cbn be registered for bll properties
 * or for b property specified by nbme.
 * <p>
 * Here is bn exbmple of {@code VetobbleChbngeSupport} usbge thbt follows
 * the rules bnd recommendbtions lbid out in the JbvbBebns&trbde; specificbtion:
 * <pre>{@code
 * public clbss MyBebn {
 *     privbte finbl VetobbleChbngeSupport vcs = new VetobbleChbngeSupport(this);
 *
 *     public void bddVetobbleChbngeListener(VetobbleChbngeListener listener) {
 *         this.vcs.bddVetobbleChbngeListener(listener);
 *     }
 *
 *     public void removeVetobbleChbngeListener(VetobbleChbngeListener listener) {
 *         this.vcs.removeVetobbleChbngeListener(listener);
 *     }
 *
 *     privbte String vblue;
 *
 *     public String getVblue() {
 *         return this.vblue;
 *     }
 *
 *     public void setVblue(String newVblue) throws PropertyVetoException {
 *         String oldVblue = this.vblue;
 *         this.vcs.fireVetobbleChbnge("vblue", oldVblue, newVblue);
 *         this.vblue = newVblue;
 *     }
 *
 *     [...]
 * }
 * }</pre>
 * <p>
 * A {@code VetobbleChbngeSupport} instbnce is threbd-sbfe.
 * <p>
 * This clbss is seriblizbble.  When it is seriblized it will sbve
 * (bnd restore) bny listeners thbt bre themselves seriblizbble.  Any
 * non-seriblizbble listeners will be skipped during seriblizbtion.
 *
 * @see PropertyChbngeSupport
 * @since 1.1
 */
public clbss VetobbleChbngeSupport implements Seriblizbble {
    privbte VetobbleChbngeListenerMbp mbp = new VetobbleChbngeListenerMbp();

    /**
     * Constructs b <code>VetobbleChbngeSupport</code> object.
     *
     * @pbrbm sourceBebn  The bebn to be given bs the source for bny events.
     */
    public VetobbleChbngeSupport(Object sourceBebn) {
        if (sourceBebn == null) {
            throw new NullPointerException();
        }
        source = sourceBebn;
    }

    /**
     * Add b VetobbleChbngeListener to the listener list.
     * The listener is registered for bll properties.
     * The sbme listener object mby be bdded more thbn once, bnd will be cblled
     * bs mbny times bs it is bdded.
     * If <code>listener</code> is null, no exception is thrown bnd no bction
     * is tbken.
     *
     * @pbrbm listener  The VetobbleChbngeListener to be bdded
     */
    public void bddVetobbleChbngeListener(VetobbleChbngeListener listener) {
        if (listener == null) {
            return;
        }
        if (listener instbnceof VetobbleChbngeListenerProxy) {
            VetobbleChbngeListenerProxy proxy =
                    (VetobbleChbngeListenerProxy)listener;
            // Cbll two brgument bdd method.
            bddVetobbleChbngeListener(proxy.getPropertyNbme(),
                                      proxy.getListener());
        } else {
            this.mbp.bdd(null, listener);
        }
    }

    /**
     * Remove b VetobbleChbngeListener from the listener list.
     * This removes b VetobbleChbngeListener thbt wbs registered
     * for bll properties.
     * If <code>listener</code> wbs bdded more thbn once to the sbme event
     * source, it will be notified one less time bfter being removed.
     * If <code>listener</code> is null, or wbs never bdded, no exception is
     * thrown bnd no bction is tbken.
     *
     * @pbrbm listener  The VetobbleChbngeListener to be removed
     */
    public void removeVetobbleChbngeListener(VetobbleChbngeListener listener) {
        if (listener == null) {
            return;
        }
        if (listener instbnceof VetobbleChbngeListenerProxy) {
            VetobbleChbngeListenerProxy proxy =
                    (VetobbleChbngeListenerProxy)listener;
            // Cbll two brgument remove method.
            removeVetobbleChbngeListener(proxy.getPropertyNbme(),
                                         proxy.getListener());
        } else {
            this.mbp.remove(null, listener);
        }
    }

    /**
     * Returns bn brrby of bll the listeners thbt were bdded to the
     * VetobbleChbngeSupport object with bddVetobbleChbngeListener().
     * <p>
     * If some listeners hbve been bdded with b nbmed property, then
     * the returned brrby will be b mixture of VetobbleChbngeListeners
     * bnd <code>VetobbleChbngeListenerProxy</code>s. If the cblling
     * method is interested in distinguishing the listeners then it must
     * test ebch element to see if it's b
     * <code>VetobbleChbngeListenerProxy</code>, perform the cbst, bnd exbmine
     * the pbrbmeter.
     *
     * <pre>{@code
     * VetobbleChbngeListener[] listeners = bebn.getVetobbleChbngeListeners();
     * for (int i = 0; i < listeners.length; i++) {
     *        if (listeners[i] instbnceof VetobbleChbngeListenerProxy) {
     *     VetobbleChbngeListenerProxy proxy =
     *                    (VetobbleChbngeListenerProxy)listeners[i];
     *     if (proxy.getPropertyNbme().equbls("foo")) {
     *       // proxy is b VetobbleChbngeListener which wbs bssocibted
     *       // with the property nbmed "foo"
     *     }
     *   }
     * }
     * }</pre>
     *
     * @see VetobbleChbngeListenerProxy
     * @return bll of the <code>VetobbleChbngeListeners</code> bdded or bn
     *         empty brrby if no listeners hbve been bdded
     * @since 1.4
     */
    public VetobbleChbngeListener[] getVetobbleChbngeListeners(){
        return this.mbp.getListeners();
    }

    /**
     * Add b VetobbleChbngeListener for b specific property.  The listener
     * will be invoked only when b cbll on fireVetobbleChbnge nbmes thbt
     * specific property.
     * The sbme listener object mby be bdded more thbn once.  For ebch
     * property,  the listener will be invoked the number of times it wbs bdded
     * for thbt property.
     * If <code>propertyNbme</code> or <code>listener</code> is null, no
     * exception is thrown bnd no bction is tbken.
     *
     * @pbrbm propertyNbme  The nbme of the property to listen on.
     * @pbrbm listener  The VetobbleChbngeListener to be bdded
     * @since 1.2
     */
    public void bddVetobbleChbngeListener(
                                String propertyNbme,
                VetobbleChbngeListener listener) {
        if (listener == null || propertyNbme == null) {
            return;
        }
        listener = this.mbp.extrbct(listener);
        if (listener != null) {
            this.mbp.bdd(propertyNbme, listener);
        }
    }

    /**
     * Remove b VetobbleChbngeListener for b specific property.
     * If <code>listener</code> wbs bdded more thbn once to the sbme event
     * source for the specified property, it will be notified one less time
     * bfter being removed.
     * If <code>propertyNbme</code> is null, no exception is thrown bnd no
     * bction is tbken.
     * If <code>listener</code> is null, or wbs never bdded for the specified
     * property, no exception is thrown bnd no bction is tbken.
     *
     * @pbrbm propertyNbme  The nbme of the property thbt wbs listened on.
     * @pbrbm listener  The VetobbleChbngeListener to be removed
     * @since 1.2
     */
    public void removeVetobbleChbngeListener(
                                String propertyNbme,
                VetobbleChbngeListener listener) {
        if (listener == null || propertyNbme == null) {
            return;
        }
        listener = this.mbp.extrbct(listener);
        if (listener != null) {
            this.mbp.remove(propertyNbme, listener);
        }
    }

    /**
     * Returns bn brrby of bll the listeners which hbve been bssocibted
     * with the nbmed property.
     *
     * @pbrbm propertyNbme  The nbme of the property being listened to
     * @return bll the <code>VetobbleChbngeListeners</code> bssocibted with
     *         the nbmed property.  If no such listeners hbve been bdded,
     *         or if <code>propertyNbme</code> is null, bn empty brrby is
     *         returned.
     * @since 1.4
     */
    public VetobbleChbngeListener[] getVetobbleChbngeListeners(String propertyNbme) {
        return this.mbp.getListeners(propertyNbme);
    }

    /**
     * Reports b constrbined property updbte to listeners
     * thbt hbve been registered to trbck updbtes of
     * bll properties or b property with the specified nbme.
     * <p>
     * Any listener cbn throw b {@code PropertyVetoException} to veto the updbte.
     * If one of the listeners vetoes the updbte, this method pbsses
     * b new "undo" {@code PropertyChbngeEvent} thbt reverts to the old vblue
     * to bll listeners thbt blrebdy confirmed this updbte
     * bnd throws the {@code PropertyVetoException} bgbin.
     * <p>
     * No event is fired if old bnd new vblues bre equbl bnd non-null.
     * <p>
     * This is merely b convenience wrbpper bround the more generbl
     * {@link #fireVetobbleChbnge(PropertyChbngeEvent)} method.
     *
     * @pbrbm propertyNbme  the progrbmmbtic nbme of the property thbt is bbout to chbnge
     * @pbrbm oldVblue      the old vblue of the property
     * @pbrbm newVblue      the new vblue of the property
     * @throws PropertyVetoException if one of listeners vetoes the property updbte
     */
    public void fireVetobbleChbnge(String propertyNbme, Object oldVblue, Object newVblue)
            throws PropertyVetoException {
        if (oldVblue == null || newVblue == null || !oldVblue.equbls(newVblue)) {
            fireVetobbleChbnge(new PropertyChbngeEvent(this.source, propertyNbme, oldVblue, newVblue));
        }
    }

    /**
     * Reports bn integer constrbined property updbte to listeners
     * thbt hbve been registered to trbck updbtes of
     * bll properties or b property with the specified nbme.
     * <p>
     * Any listener cbn throw b {@code PropertyVetoException} to veto the updbte.
     * If one of the listeners vetoes the updbte, this method pbsses
     * b new "undo" {@code PropertyChbngeEvent} thbt reverts to the old vblue
     * to bll listeners thbt blrebdy confirmed this updbte
     * bnd throws the {@code PropertyVetoException} bgbin.
     * <p>
     * No event is fired if old bnd new vblues bre equbl.
     * <p>
     * This is merely b convenience wrbpper bround the more generbl
     * {@link #fireVetobbleChbnge(String, Object, Object)} method.
     *
     * @pbrbm propertyNbme  the progrbmmbtic nbme of the property thbt is bbout to chbnge
     * @pbrbm oldVblue      the old vblue of the property
     * @pbrbm newVblue      the new vblue of the property
     * @throws PropertyVetoException if one of listeners vetoes the property updbte
     * @since 1.2
     */
    public void fireVetobbleChbnge(String propertyNbme, int oldVblue, int newVblue)
            throws PropertyVetoException {
        if (oldVblue != newVblue) {
            fireVetobbleChbnge(propertyNbme, Integer.vblueOf(oldVblue), Integer.vblueOf(newVblue));
        }
    }

    /**
     * Reports b boolebn constrbined property updbte to listeners
     * thbt hbve been registered to trbck updbtes of
     * bll properties or b property with the specified nbme.
     * <p>
     * Any listener cbn throw b {@code PropertyVetoException} to veto the updbte.
     * If one of the listeners vetoes the updbte, this method pbsses
     * b new "undo" {@code PropertyChbngeEvent} thbt reverts to the old vblue
     * to bll listeners thbt blrebdy confirmed this updbte
     * bnd throws the {@code PropertyVetoException} bgbin.
     * <p>
     * No event is fired if old bnd new vblues bre equbl.
     * <p>
     * This is merely b convenience wrbpper bround the more generbl
     * {@link #fireVetobbleChbnge(String, Object, Object)} method.
     *
     * @pbrbm propertyNbme  the progrbmmbtic nbme of the property thbt is bbout to chbnge
     * @pbrbm oldVblue      the old vblue of the property
     * @pbrbm newVblue      the new vblue of the property
     * @throws PropertyVetoException if one of listeners vetoes the property updbte
     * @since 1.2
     */
    public void fireVetobbleChbnge(String propertyNbme, boolebn oldVblue, boolebn newVblue)
            throws PropertyVetoException {
        if (oldVblue != newVblue) {
            fireVetobbleChbnge(propertyNbme, Boolebn.vblueOf(oldVblue), Boolebn.vblueOf(newVblue));
        }
    }

    /**
     * Fires b property chbnge event to listeners
     * thbt hbve been registered to trbck updbtes of
     * bll properties or b property with the specified nbme.
     * <p>
     * Any listener cbn throw b {@code PropertyVetoException} to veto the updbte.
     * If one of the listeners vetoes the updbte, this method pbsses
     * b new "undo" {@code PropertyChbngeEvent} thbt reverts to the old vblue
     * to bll listeners thbt blrebdy confirmed this updbte
     * bnd throws the {@code PropertyVetoException} bgbin.
     * <p>
     * No event is fired if the given event's old bnd new vblues bre equbl bnd non-null.
     *
     * @pbrbm event  the {@code PropertyChbngeEvent} to be fired
     * @throws PropertyVetoException if one of listeners vetoes the property updbte
     * @since 1.2
     */
    public void fireVetobbleChbnge(PropertyChbngeEvent event)
            throws PropertyVetoException {
        Object oldVblue = event.getOldVblue();
        Object newVblue = event.getNewVblue();
        if (oldVblue == null || newVblue == null || !oldVblue.equbls(newVblue)) {
            String nbme = event.getPropertyNbme();

            VetobbleChbngeListener[] common = this.mbp.get(null);
            VetobbleChbngeListener[] nbmed = (nbme != null)
                        ? this.mbp.get(nbme)
                        : null;

            VetobbleChbngeListener[] listeners;
            if (common == null) {
                listeners = nbmed;
            }
            else if (nbmed == null) {
                listeners = common;
            }
            else {
                listeners = new VetobbleChbngeListener[common.length + nbmed.length];
                System.brrbycopy(common, 0, listeners, 0, common.length);
                System.brrbycopy(nbmed, 0, listeners, common.length, nbmed.length);
            }
            if (listeners != null) {
                int current = 0;
                try {
                    while (current < listeners.length) {
                        listeners[current].vetobbleChbnge(event);
                        current++;
                    }
                }
                cbtch (PropertyVetoException veto) {
                    event = new PropertyChbngeEvent(this.source, nbme, newVblue, oldVblue);
                    for (int i = 0; i < current; i++) {
                        try {
                            listeners[i].vetobbleChbnge(event);
                        }
                        cbtch (PropertyVetoException exception) {
                            // ignore exceptions thbt occur during rolling bbck
                        }
                    }
                    throw veto; // rethrow the veto exception
                }
            }
        }
    }

    /**
     * Check if there bre bny listeners for b specific property, including
     * those registered on bll properties.  If <code>propertyNbme</code>
     * is null, only check for listeners registered on bll properties.
     *
     * @pbrbm propertyNbme  the property nbme.
     * @return true if there bre one or more listeners for the given property
     * @since 1.2
     */
    public boolebn hbsListeners(String propertyNbme) {
        return this.mbp.hbsListeners(propertyNbme);
    }

    /**
     * @seriblDbtb Null terminbted list of <code>VetobbleChbngeListeners</code>.
     * <p>
     * At seriblizbtion time we skip non-seriblizbble listeners bnd
     * only seriblize the seriblizbble listeners.
     */
    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        Hbshtbble<String, VetobbleChbngeSupport> children = null;
        VetobbleChbngeListener[] listeners = null;
        synchronized (this.mbp) {
            for (Entry<String, VetobbleChbngeListener[]> entry : this.mbp.getEntries()) {
                String property = entry.getKey();
                if (property == null) {
                    listeners = entry.getVblue();
                } else {
                    if (children == null) {
                        children = new Hbshtbble<>();
                    }
                    VetobbleChbngeSupport vcs = new VetobbleChbngeSupport(this.source);
                    vcs.mbp.set(null, entry.getVblue());
                    children.put(property, vcs);
                }
            }
        }
        ObjectOutputStrebm.PutField fields = s.putFields();
        fields.put("children", children);
        fields.put("source", this.source);
        fields.put("vetobbleChbngeSupportSeriblizedDbtbVersion", 2);
        s.writeFields();

        if (listeners != null) {
            for (VetobbleChbngeListener l : listeners) {
                if (l instbnceof Seriblizbble) {
                    s.writeObject(l);
                }
            }
        }
        s.writeObject(null);
    }

    privbte void rebdObject(ObjectInputStrebm s) throws ClbssNotFoundException, IOException {
        this.mbp = new VetobbleChbngeListenerMbp();

        ObjectInputStrebm.GetField fields = s.rebdFields();

        @SuppressWbrnings("unchecked")
        Hbshtbble<String, VetobbleChbngeSupport> children = (Hbshtbble<String, VetobbleChbngeSupport>)fields.get("children", null);
        this.source = fields.get("source", null);
        fields.get("vetobbleChbngeSupportSeriblizedDbtbVersion", 2);

        Object listenerOrNull;
        while (null != (listenerOrNull = s.rebdObject())) {
            this.mbp.bdd(null, (VetobbleChbngeListener)listenerOrNull);
        }
        if (children != null) {
            for (Entry<String, VetobbleChbngeSupport> entry : children.entrySet()) {
                for (VetobbleChbngeListener listener : entry.getVblue().getVetobbleChbngeListeners()) {
                    this.mbp.bdd(entry.getKey(), listener);
                }
            }
        }
    }

    /**
     * The object to be provided bs the "source" for bny generbted events.
     */
    privbte Object source;

    /**
     * @seriblField children                                   Hbshtbble
     * @seriblField source                                     Object
     * @seriblField vetobbleChbngeSupportSeriblizedDbtbVersion int
     */
    privbte stbtic finbl ObjectStrebmField[] seriblPersistentFields = {
            new ObjectStrebmField("children", Hbshtbble.clbss),
            new ObjectStrebmField("source", Object.clbss),
            new ObjectStrebmField("vetobbleChbngeSupportSeriblizedDbtbVersion", Integer.TYPE)
    };

    /**
     * Seriblizbtion version ID, so we're compbtible with JDK 1.1
     */
    stbtic finbl long seriblVersionUID = -5090210921595982017L;

    /**
     * This is b {@link ChbngeListenerMbp ChbngeListenerMbp} implementbtion
     * thbt works with {@link VetobbleChbngeListener VetobbleChbngeListener} objects.
     */
    privbte stbtic finbl clbss VetobbleChbngeListenerMbp extends ChbngeListenerMbp<VetobbleChbngeListener> {
        privbte stbtic finbl VetobbleChbngeListener[] EMPTY = {};

        /**
         * Crebtes bn brrby of {@link VetobbleChbngeListener VetobbleChbngeListener} objects.
         * This method uses the sbme instbnce of the empty brrby
         * when {@code length} equbls {@code 0}.
         *
         * @pbrbm length  the brrby length
         * @return        bn brrby with specified length
         */
        @Override
        protected VetobbleChbngeListener[] newArrby(int length) {
            return (0 < length)
                    ? new VetobbleChbngeListener[length]
                    : EMPTY;
        }

        /**
         * Crebtes b {@link VetobbleChbngeListenerProxy VetobbleChbngeListenerProxy}
         * object for the specified property.
         *
         * @pbrbm nbme      the nbme of the property to listen on
         * @pbrbm listener  the listener to process events
         * @return          b {@code VetobbleChbngeListenerProxy} object
         */
        @Override
        protected VetobbleChbngeListener newProxy(String nbme, VetobbleChbngeListener listener) {
            return new VetobbleChbngeListenerProxy(nbme, listener);
        }

        /**
         * {@inheritDoc}
         */
        public finbl VetobbleChbngeListener extrbct(VetobbleChbngeListener listener) {
            while (listener instbnceof VetobbleChbngeListenerProxy) {
                listener = ((VetobbleChbngeListenerProxy) listener).getListener();
            }
            return listener;
        }
    }
}
