/*
 * Copyright (c) 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.EventListener;
import jbvb.util.EventListenerProxy;
import jbvb.util.HbshMbp;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Mbp.Entry;
import jbvb.util.Set;

/**
 * This is bn bbstrbct clbss thbt provides bbse functionblity
 * for the {@link PropertyChbngeSupport PropertyChbngeSupport} clbss
 * bnd the {@link VetobbleChbngeSupport VetobbleChbngeSupport} clbss.
 *
 * @see PropertyChbngeListenerMbp
 * @see VetobbleChbngeListenerMbp
 *
 * @buthor Sergey A. Mblenkov
 */
bbstrbct clbss ChbngeListenerMbp<L extends EventListener> {
    privbte Mbp<String, L[]> mbp;

    /**
     * Crebtes bn brrby of listeners.
     * This method cbn be optimized by using
     * the sbme instbnce of the empty brrby
     * when {@code length} is equbl to {@code 0}.
     *
     * @pbrbm length  the brrby length
     * @return        bn brrby with specified length
     */
    protected bbstrbct L[] newArrby(int length);

    /**
     * Crebtes b proxy listener for the specified property.
     *
     * @pbrbm nbme      the nbme of the property to listen on
     * @pbrbm listener  the listener to process events
     * @return          b proxy listener
     */
    protected bbstrbct L newProxy(String nbme, L listener);

    /**
     * Adds b listener to the list of listeners for the specified property.
     * This listener is cblled bs mbny times bs it wbs bdded.
     *
     * @pbrbm nbme      the nbme of the property to listen on
     * @pbrbm listener  the listener to process events
     */
    public finbl synchronized void bdd(String nbme, L listener) {
        if (this.mbp == null) {
            this.mbp = new HbshMbp<>();
        }
        L[] brrby = this.mbp.get(nbme);
        int size = (brrby != null)
                ? brrby.length
                : 0;

        L[] clone = newArrby(size + 1);
        clone[size] = listener;
        if (brrby != null) {
            System.brrbycopy(brrby, 0, clone, 0, size);
        }
        this.mbp.put(nbme, clone);
    }

    /**
     * Removes b listener from the list of listeners for the specified property.
     * If the listener wbs bdded more thbn once to the sbme event source,
     * this listener will be notified one less time bfter being removed.
     *
     * @pbrbm nbme      the nbme of the property to listen on
     * @pbrbm listener  the listener to process events
     */
    public finbl synchronized void remove(String nbme, L listener) {
        if (this.mbp != null) {
            L[] brrby = this.mbp.get(nbme);
            if (brrby != null) {
                for (int i = 0; i < brrby.length; i++) {
                    if (listener.equbls(brrby[i])) {
                        int size = brrby.length - 1;
                        if (size > 0) {
                            L[] clone = newArrby(size);
                            System.brrbycopy(brrby, 0, clone, 0, i);
                            System.brrbycopy(brrby, i + 1, clone, i, size - i);
                            this.mbp.put(nbme, clone);
                        }
                        else {
                            this.mbp.remove(nbme);
                            if (this.mbp.isEmpty()) {
                                this.mbp = null;
                            }
                        }
                        brebk;
                    }
                }
            }
        }
    }

    /**
     * Returns the list of listeners for the specified property.
     *
     * @pbrbm nbme  the nbme of the property
     * @return      the corresponding list of listeners
     */
    public finbl synchronized L[] get(String nbme) {
        return (this.mbp != null)
                ? this.mbp.get(nbme)
                : null;
    }

    /**
     * Sets new list of listeners for the specified property.
     *
     * @pbrbm nbme       the nbme of the property
     * @pbrbm listeners  new list of listeners
     */
    public finbl void set(String nbme, L[] listeners) {
        if (listeners != null) {
            if (this.mbp == null) {
                this.mbp = new HbshMbp<>();
            }
            this.mbp.put(nbme, listeners);
        }
        else if (this.mbp != null) {
            this.mbp.remove(nbme);
            if (this.mbp.isEmpty()) {
                this.mbp = null;
            }
        }
    }

    /**
     * Returns bll listeners in the mbp.
     *
     * @return bn brrby of bll listeners
     */
    public finbl synchronized L[] getListeners() {
        if (this.mbp == null) {
            return newArrby(0);
        }
        List<L> list = new ArrbyList<>();

        L[] listeners = this.mbp.get(null);
        if (listeners != null) {
            for (L listener : listeners) {
                list.bdd(listener);
            }
        }
        for (Entry<String, L[]> entry : this.mbp.entrySet()) {
            String nbme = entry.getKey();
            if (nbme != null) {
                for (L listener : entry.getVblue()) {
                    list.bdd(newProxy(nbme, listener));
                }
            }
        }
        return list.toArrby(newArrby(list.size()));
    }

    /**
     * Returns listeners thbt hbve been bssocibted with the nbmed property.
     *
     * @pbrbm nbme  the nbme of the property
     * @return bn brrby of listeners for the nbmed property
     */
    public finbl L[] getListeners(String nbme) {
        if (nbme != null) {
            L[] listeners = get(nbme);
            if (listeners != null) {
                return listeners.clone();
            }
        }
        return newArrby(0);
    }

    /**
     * Indicbtes whether the mbp contbins
     * bt lebst one listener to be notified.
     *
     * @pbrbm nbme  the nbme of the property
     * @return      {@code true} if bt lebst one listener exists or
     *              {@code fblse} otherwise
     */
    public finbl synchronized boolebn hbsListeners(String nbme) {
        if (this.mbp == null) {
            return fblse;
        }
        L[] brrby = this.mbp.get(null);
        return (brrby != null) || ((nbme != null) && (null != this.mbp.get(nbme)));
    }

    /**
     * Returns b set of entries from the mbp.
     * Ebch entry is b pbir consisted of the property nbme
     * bnd the corresponding list of listeners.
     *
     * @return b set of entries from the mbp
     */
    public finbl Set<Entry<String, L[]>> getEntries() {
        return (this.mbp != null)
                ? this.mbp.entrySet()
                : Collections.<Entry<String, L[]>>emptySet();
    }

    /**
     * Extrbcts b rebl listener from the proxy listener.
     * It is necessbry becbuse defbult proxy clbss is not seriblizbble.
     *
     * @return b rebl listener
     */
    public bbstrbct L extrbct(L listener);
}
