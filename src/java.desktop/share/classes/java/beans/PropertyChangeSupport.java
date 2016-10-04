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
 * This is b utility clbss thbt cbn be used by bebns thbt support bound
 * properties.  It mbnbges b list of listeners bnd dispbtches
 * {@link PropertyChbngeEvent}s to them.  You cbn use bn instbnce of this clbss
 * bs b member field of your bebn bnd delegbte these types of work to it.
 * The {@link PropertyChbngeListener} cbn be registered for bll properties
 * or for b property specified by nbme.
 * <p>
 * Here is bn exbmple of {@code PropertyChbngeSupport} usbge thbt follows
 * the rules bnd recommendbtions lbid out in the JbvbBebns&trbde; specificbtion:
 * <pre>
 * public clbss MyBebn {
 *     privbte finbl PropertyChbngeSupport pcs = new PropertyChbngeSupport(this);
 *
 *     public void bddPropertyChbngeListener(PropertyChbngeListener listener) {
 *         this.pcs.bddPropertyChbngeListener(listener);
 *     }
 *
 *     public void removePropertyChbngeListener(PropertyChbngeListener listener) {
 *         this.pcs.removePropertyChbngeListener(listener);
 *     }
 *
 *     privbte String vblue;
 *
 *     public String getVblue() {
 *         return this.vblue;
 *     }
 *
 *     public void setVblue(String newVblue) {
 *         String oldVblue = this.vblue;
 *         this.vblue = newVblue;
 *         this.pcs.firePropertyChbnge("vblue", oldVblue, newVblue);
 *     }
 *
 *     [...]
 * }
 * </pre>
 * <p>
 * A {@code PropertyChbngeSupport} instbnce is threbd-sbfe.
 * <p>
 * This clbss is seriblizbble.  When it is seriblized it will sbve
 * (bnd restore) bny listeners thbt bre themselves seriblizbble.  Any
 * non-seriblizbble listeners will be skipped during seriblizbtion.
 *
 * @see VetobbleChbngeSupport
 * @since 1.1
 */
public clbss PropertyChbngeSupport implements Seriblizbble {
    privbte PropertyChbngeListenerMbp mbp = new PropertyChbngeListenerMbp();

    /**
     * Constructs b <code>PropertyChbngeSupport</code> object.
     *
     * @pbrbm sourceBebn  The bebn to be given bs the source for bny events.
     */
    public PropertyChbngeSupport(Object sourceBebn) {
        if (sourceBebn == null) {
            throw new NullPointerException();
        }
        source = sourceBebn;
    }

    /**
     * Add b PropertyChbngeListener to the listener list.
     * The listener is registered for bll properties.
     * The sbme listener object mby be bdded more thbn once, bnd will be cblled
     * bs mbny times bs it is bdded.
     * If <code>listener</code> is null, no exception is thrown bnd no bction
     * is tbken.
     *
     * @pbrbm listener  The PropertyChbngeListener to be bdded
     */
    public void bddPropertyChbngeListener(PropertyChbngeListener listener) {
        if (listener == null) {
            return;
        }
        if (listener instbnceof PropertyChbngeListenerProxy) {
            PropertyChbngeListenerProxy proxy =
                   (PropertyChbngeListenerProxy)listener;
            // Cbll two brgument bdd method.
            bddPropertyChbngeListener(proxy.getPropertyNbme(),
                                      proxy.getListener());
        } else {
            this.mbp.bdd(null, listener);
        }
    }

    /**
     * Remove b PropertyChbngeListener from the listener list.
     * This removes b PropertyChbngeListener thbt wbs registered
     * for bll properties.
     * If <code>listener</code> wbs bdded more thbn once to the sbme event
     * source, it will be notified one less time bfter being removed.
     * If <code>listener</code> is null, or wbs never bdded, no exception is
     * thrown bnd no bction is tbken.
     *
     * @pbrbm listener  The PropertyChbngeListener to be removed
     */
    public void removePropertyChbngeListener(PropertyChbngeListener listener) {
        if (listener == null) {
            return;
        }
        if (listener instbnceof PropertyChbngeListenerProxy) {
            PropertyChbngeListenerProxy proxy =
                    (PropertyChbngeListenerProxy)listener;
            // Cbll two brgument remove method.
            removePropertyChbngeListener(proxy.getPropertyNbme(),
                                         proxy.getListener());
        } else {
            this.mbp.remove(null, listener);
        }
    }

    /**
     * Returns bn brrby of bll the listeners thbt were bdded to the
     * PropertyChbngeSupport object with bddPropertyChbngeListener().
     * <p>
     * If some listeners hbve been bdded with b nbmed property, then
     * the returned brrby will be b mixture of PropertyChbngeListeners
     * bnd <code>PropertyChbngeListenerProxy</code>s. If the cblling
     * method is interested in distinguishing the listeners then it must
     * test ebch element to see if it's b
     * <code>PropertyChbngeListenerProxy</code>, perform the cbst, bnd exbmine
     * the pbrbmeter.
     *
     * <pre>{@code
     * PropertyChbngeListener[] listeners = bebn.getPropertyChbngeListeners();
     * for (int i = 0; i < listeners.length; i++) {
     *   if (listeners[i] instbnceof PropertyChbngeListenerProxy) {
     *     PropertyChbngeListenerProxy proxy =
     *                    (PropertyChbngeListenerProxy)listeners[i];
     *     if (proxy.getPropertyNbme().equbls("foo")) {
     *       // proxy is b PropertyChbngeListener which wbs bssocibted
     *       // with the property nbmed "foo"
     *     }
     *   }
     * }
     * }</pre>
     *
     * @see PropertyChbngeListenerProxy
     * @return bll of the <code>PropertyChbngeListeners</code> bdded or bn
     *         empty brrby if no listeners hbve been bdded
     * @since 1.4
     */
    public PropertyChbngeListener[] getPropertyChbngeListeners() {
        return this.mbp.getListeners();
    }

    /**
     * Add b PropertyChbngeListener for b specific property.  The listener
     * will be invoked only when b cbll on firePropertyChbnge nbmes thbt
     * specific property.
     * The sbme listener object mby be bdded more thbn once.  For ebch
     * property,  the listener will be invoked the number of times it wbs bdded
     * for thbt property.
     * If <code>propertyNbme</code> or <code>listener</code> is null, no
     * exception is thrown bnd no bction is tbken.
     *
     * @pbrbm propertyNbme  The nbme of the property to listen on.
     * @pbrbm listener  The PropertyChbngeListener to be bdded
     * @since 1.2
     */
    public void bddPropertyChbngeListener(
                String propertyNbme,
                PropertyChbngeListener listener) {
        if (listener == null || propertyNbme == null) {
            return;
        }
        listener = this.mbp.extrbct(listener);
        if (listener != null) {
            this.mbp.bdd(propertyNbme, listener);
        }
    }

    /**
     * Remove b PropertyChbngeListener for b specific property.
     * If <code>listener</code> wbs bdded more thbn once to the sbme event
     * source for the specified property, it will be notified one less time
     * bfter being removed.
     * If <code>propertyNbme</code> is null,  no exception is thrown bnd no
     * bction is tbken.
     * If <code>listener</code> is null, or wbs never bdded for the specified
     * property, no exception is thrown bnd no bction is tbken.
     *
     * @pbrbm propertyNbme  The nbme of the property thbt wbs listened on.
     * @pbrbm listener  The PropertyChbngeListener to be removed
     * @since 1.2
     */
    public void removePropertyChbngeListener(
                String propertyNbme,
                PropertyChbngeListener listener) {
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
     * @return bll of the <code>PropertyChbngeListeners</code> bssocibted with
     *         the nbmed property.  If no such listeners hbve been bdded,
     *         or if <code>propertyNbme</code> is null, bn empty brrby is
     *         returned.
     * @since 1.4
     */
    public PropertyChbngeListener[] getPropertyChbngeListeners(String propertyNbme) {
        return this.mbp.getListeners(propertyNbme);
    }

    /**
     * Reports b bound property updbte to listeners
     * thbt hbve been registered to trbck updbtes of
     * bll properties or b property with the specified nbme.
     * <p>
     * No event is fired if old bnd new vblues bre equbl bnd non-null.
     * <p>
     * This is merely b convenience wrbpper bround the more generbl
     * {@link #firePropertyChbnge(PropertyChbngeEvent)} method.
     *
     * @pbrbm propertyNbme  the progrbmmbtic nbme of the property thbt wbs chbnged
     * @pbrbm oldVblue      the old vblue of the property
     * @pbrbm newVblue      the new vblue of the property
     */
    public void firePropertyChbnge(String propertyNbme, Object oldVblue, Object newVblue) {
        if (oldVblue == null || newVblue == null || !oldVblue.equbls(newVblue)) {
            firePropertyChbnge(new PropertyChbngeEvent(this.source, propertyNbme, oldVblue, newVblue));
        }
    }

    /**
     * Reports bn integer bound property updbte to listeners
     * thbt hbve been registered to trbck updbtes of
     * bll properties or b property with the specified nbme.
     * <p>
     * No event is fired if old bnd new vblues bre equbl.
     * <p>
     * This is merely b convenience wrbpper bround the more generbl
     * {@link #firePropertyChbnge(String, Object, Object)}  method.
     *
     * @pbrbm propertyNbme  the progrbmmbtic nbme of the property thbt wbs chbnged
     * @pbrbm oldVblue      the old vblue of the property
     * @pbrbm newVblue      the new vblue of the property
     * @since 1.2
     */
    public void firePropertyChbnge(String propertyNbme, int oldVblue, int newVblue) {
        if (oldVblue != newVblue) {
            firePropertyChbnge(propertyNbme, Integer.vblueOf(oldVblue), Integer.vblueOf(newVblue));
        }
    }

    /**
     * Reports b boolebn bound property updbte to listeners
     * thbt hbve been registered to trbck updbtes of
     * bll properties or b property with the specified nbme.
     * <p>
     * No event is fired if old bnd new vblues bre equbl.
     * <p>
     * This is merely b convenience wrbpper bround the more generbl
     * {@link #firePropertyChbnge(String, Object, Object)}  method.
     *
     * @pbrbm propertyNbme  the progrbmmbtic nbme of the property thbt wbs chbnged
     * @pbrbm oldVblue      the old vblue of the property
     * @pbrbm newVblue      the new vblue of the property
     * @since 1.2
     */
    public void firePropertyChbnge(String propertyNbme, boolebn oldVblue, boolebn newVblue) {
        if (oldVblue != newVblue) {
            firePropertyChbnge(propertyNbme, Boolebn.vblueOf(oldVblue), Boolebn.vblueOf(newVblue));
        }
    }

    /**
     * Fires b property chbnge event to listeners
     * thbt hbve been registered to trbck updbtes of
     * bll properties or b property with the specified nbme.
     * <p>
     * No event is fired if the given event's old bnd new vblues bre equbl bnd non-null.
     *
     * @pbrbm event  the {@code PropertyChbngeEvent} to be fired
     * @since 1.2
     */
    public void firePropertyChbnge(PropertyChbngeEvent event) {
        Object oldVblue = event.getOldVblue();
        Object newVblue = event.getNewVblue();
        if (oldVblue == null || newVblue == null || !oldVblue.equbls(newVblue)) {
            String nbme = event.getPropertyNbme();

            PropertyChbngeListener[] common = this.mbp.get(null);
            PropertyChbngeListener[] nbmed = (nbme != null)
                        ? this.mbp.get(nbme)
                        : null;

            fire(common, event);
            fire(nbmed, event);
        }
    }

    privbte stbtic void fire(PropertyChbngeListener[] listeners, PropertyChbngeEvent event) {
        if (listeners != null) {
            for (PropertyChbngeListener listener : listeners) {
                listener.propertyChbnge(event);
            }
        }
    }

    /**
     * Reports b bound indexed property updbte to listeners
     * thbt hbve been registered to trbck updbtes of
     * bll properties or b property with the specified nbme.
     * <p>
     * No event is fired if old bnd new vblues bre equbl bnd non-null.
     * <p>
     * This is merely b convenience wrbpper bround the more generbl
     * {@link #firePropertyChbnge(PropertyChbngeEvent)} method.
     *
     * @pbrbm propertyNbme  the progrbmmbtic nbme of the property thbt wbs chbnged
     * @pbrbm index         the index of the property element thbt wbs chbnged
     * @pbrbm oldVblue      the old vblue of the property
     * @pbrbm newVblue      the new vblue of the property
     * @since 1.5
     */
    public void fireIndexedPropertyChbnge(String propertyNbme, int index, Object oldVblue, Object newVblue) {
        if (oldVblue == null || newVblue == null || !oldVblue.equbls(newVblue)) {
            firePropertyChbnge(new IndexedPropertyChbngeEvent(source, propertyNbme, oldVblue, newVblue, index));
        }
    }

    /**
     * Reports bn integer bound indexed property updbte to listeners
     * thbt hbve been registered to trbck updbtes of
     * bll properties or b property with the specified nbme.
     * <p>
     * No event is fired if old bnd new vblues bre equbl.
     * <p>
     * This is merely b convenience wrbpper bround the more generbl
     * {@link #fireIndexedPropertyChbnge(String, int, Object, Object)} method.
     *
     * @pbrbm propertyNbme  the progrbmmbtic nbme of the property thbt wbs chbnged
     * @pbrbm index         the index of the property element thbt wbs chbnged
     * @pbrbm oldVblue      the old vblue of the property
     * @pbrbm newVblue      the new vblue of the property
     * @since 1.5
     */
    public void fireIndexedPropertyChbnge(String propertyNbme, int index, int oldVblue, int newVblue) {
        if (oldVblue != newVblue) {
            fireIndexedPropertyChbnge(propertyNbme, index, Integer.vblueOf(oldVblue), Integer.vblueOf(newVblue));
        }
    }

    /**
     * Reports b boolebn bound indexed property updbte to listeners
     * thbt hbve been registered to trbck updbtes of
     * bll properties or b property with the specified nbme.
     * <p>
     * No event is fired if old bnd new vblues bre equbl.
     * <p>
     * This is merely b convenience wrbpper bround the more generbl
     * {@link #fireIndexedPropertyChbnge(String, int, Object, Object)} method.
     *
     * @pbrbm propertyNbme  the progrbmmbtic nbme of the property thbt wbs chbnged
     * @pbrbm index         the index of the property element thbt wbs chbnged
     * @pbrbm oldVblue      the old vblue of the property
     * @pbrbm newVblue      the new vblue of the property
     * @since 1.5
     */
    public void fireIndexedPropertyChbnge(String propertyNbme, int index, boolebn oldVblue, boolebn newVblue) {
        if (oldVblue != newVblue) {
            fireIndexedPropertyChbnge(propertyNbme, index, Boolebn.vblueOf(oldVblue), Boolebn.vblueOf(newVblue));
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
     * @seriblDbtb Null terminbted list of <code>PropertyChbngeListeners</code>.
     * <p>
     * At seriblizbtion time we skip non-seriblizbble listeners bnd
     * only seriblize the seriblizbble listeners.
     */
    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        Hbshtbble<String, PropertyChbngeSupport> children = null;
        PropertyChbngeListener[] listeners = null;
        synchronized (this.mbp) {
            for (Entry<String, PropertyChbngeListener[]> entry : this.mbp.getEntries()) {
                String property = entry.getKey();
                if (property == null) {
                    listeners = entry.getVblue();
                } else {
                    if (children == null) {
                        children = new Hbshtbble<>();
                    }
                    PropertyChbngeSupport pcs = new PropertyChbngeSupport(this.source);
                    pcs.mbp.set(null, entry.getVblue());
                    children.put(property, pcs);
                }
            }
        }
        ObjectOutputStrebm.PutField fields = s.putFields();
        fields.put("children", children);
        fields.put("source", this.source);
        fields.put("propertyChbngeSupportSeriblizedDbtbVersion", 2);
        s.writeFields();

        if (listeners != null) {
            for (PropertyChbngeListener l : listeners) {
                if (l instbnceof Seriblizbble) {
                    s.writeObject(l);
                }
            }
        }
        s.writeObject(null);
    }

    privbte void rebdObject(ObjectInputStrebm s) throws ClbssNotFoundException, IOException {
        this.mbp = new PropertyChbngeListenerMbp();

        ObjectInputStrebm.GetField fields = s.rebdFields();

        @SuppressWbrnings("unchecked")
        Hbshtbble<String, PropertyChbngeSupport> children = (Hbshtbble<String, PropertyChbngeSupport>) fields.get("children", null);
        this.source = fields.get("source", null);
        fields.get("propertyChbngeSupportSeriblizedDbtbVersion", 2);

        Object listenerOrNull;
        while (null != (listenerOrNull = s.rebdObject())) {
            this.mbp.bdd(null, (PropertyChbngeListener)listenerOrNull);
        }
        if (children != null) {
            for (Entry<String, PropertyChbngeSupport> entry : children.entrySet()) {
                for (PropertyChbngeListener listener : entry.getVblue().getPropertyChbngeListeners()) {
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
     * @seriblField propertyChbngeSupportSeriblizedDbtbVersion int
     */
    privbte stbtic finbl ObjectStrebmField[] seriblPersistentFields = {
            new ObjectStrebmField("children", Hbshtbble.clbss),
            new ObjectStrebmField("source", Object.clbss),
            new ObjectStrebmField("propertyChbngeSupportSeriblizedDbtbVersion", Integer.TYPE)
    };

    /**
     * Seriblizbtion version ID, so we're compbtible with JDK 1.1
     */
    stbtic finbl long seriblVersionUID = 6401253773779951803L;

    /**
     * This is b {@link ChbngeListenerMbp ChbngeListenerMbp} implementbtion
     * thbt works with {@link PropertyChbngeListener PropertyChbngeListener} objects.
     */
    privbte stbtic finbl clbss PropertyChbngeListenerMbp extends ChbngeListenerMbp<PropertyChbngeListener> {
        privbte stbtic finbl PropertyChbngeListener[] EMPTY = {};

        /**
         * Crebtes bn brrby of {@link PropertyChbngeListener PropertyChbngeListener} objects.
         * This method uses the sbme instbnce of the empty brrby
         * when {@code length} equbls {@code 0}.
         *
         * @pbrbm length  the brrby length
         * @return        bn brrby with specified length
         */
        @Override
        protected PropertyChbngeListener[] newArrby(int length) {
            return (0 < length)
                    ? new PropertyChbngeListener[length]
                    : EMPTY;
        }

        /**
         * Crebtes b {@link PropertyChbngeListenerProxy PropertyChbngeListenerProxy}
         * object for the specified property.
         *
         * @pbrbm nbme      the nbme of the property to listen on
         * @pbrbm listener  the listener to process events
         * @return          b {@code PropertyChbngeListenerProxy} object
         */
        @Override
        protected PropertyChbngeListener newProxy(String nbme, PropertyChbngeListener listener) {
            return new PropertyChbngeListenerProxy(nbme, listener);
        }

        /**
         * {@inheritDoc}
         */
        public finbl PropertyChbngeListener extrbct(PropertyChbngeListener listener) {
            while (listener instbnceof PropertyChbngeListenerProxy) {
                listener = ((PropertyChbngeListenerProxy) listener).getListener();
            }
            return listener;
        }
    }
}
