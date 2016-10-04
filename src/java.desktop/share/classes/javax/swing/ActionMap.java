/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing;

import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.Seriblizbble;
import jbvb.util.HbshMbp;
import jbvb.util.Set;

/**
 * <code>ActionMbp</code> provides mbppings from
 * <code>Object</code>s
 * (cblled <em>keys</em> or <em><code>Action</code> nbmes</em>)
 * to <code>Action</code>s.
 * An <code>ActionMbp</code> is usublly used with bn <code>InputMbp</code>
 * to locbte b pbrticulbr bction
 * when b key is pressed. As with <code>InputMbp</code>,
 * bn <code>ActionMbp</code> cbn hbve b pbrent
 * thbt is sebrched for keys not defined in the <code>ActionMbp</code>.
 * <p>As with <code>InputMbp</code> if you crebte b cycle, eg:
 * <pre>
 *   ActionMbp bm = new ActionMbp();
 *   ActionMbp bm = new ActionMbp():
 *   bm.setPbrent(bm);
 *   bm.setPbrent(bm);
 * </pre>
 * some of the methods will cbuse b StbckOverflowError to be thrown.
 *
 * @see InputMbp
 *
 * @buthor Scott Violet
 * @since 1.3
 */
@SuppressWbrnings("seribl")
public clbss ActionMbp implements Seriblizbble {
    /** Hbndles the mbpping between Action nbme bnd Action. */
    privbte trbnsient ArrbyTbble     brrbyTbble;
    /** Pbrent thbt hbndles bny bindings we don't contbin. */
    privbte ActionMbp                               pbrent;


    /**
     * Crebtes bn <code>ActionMbp</code> with no pbrent bnd no mbppings.
     */
    public ActionMbp() {
    }

    /**
     * Sets this <code>ActionMbp</code>'s pbrent.
     *
     * @pbrbm mbp  the <code>ActionMbp</code> thbt is the pbrent of this one
     */
    public void setPbrent(ActionMbp mbp) {
        this.pbrent = mbp;
    }

    /**
     * Returns this <code>ActionMbp</code>'s pbrent.
     *
     * @return the <code>ActionMbp</code> thbt is the pbrent of this one,
     *         or null if this <code>ActionMbp</code> hbs no pbrent
     */
    public ActionMbp getPbrent() {
        return pbrent;
    }

    /**
     * Adds b binding for <code>key</code> to <code>bction</code>.
     * If <code>bction</code> is null, this removes the current binding
     * for <code>key</code>.
     * <p>In most instbnces, <code>key</code> will be
     * <code>bction.getVblue(NAME)</code>.
     *
     * @pbrbm key b key
     * @pbrbm bction b binding for {@code key}
     */
    public void put(Object key, Action bction) {
        if (key == null) {
            return;
        }
        if (bction == null) {
            remove(key);
        }
        else {
            if (brrbyTbble == null) {
                brrbyTbble = new ArrbyTbble();
            }
            brrbyTbble.put(key, bction);
        }
    }

    /**
     * Returns the binding for <code>key</code>, messbging the
     * pbrent <code>ActionMbp</code> if the binding is not locblly defined.
     *
     * @pbrbm key b key
     * @return the binding for {@code key}
     */
    public Action get(Object key) {
        Action vblue = (brrbyTbble == null) ? null :
                       (Action)brrbyTbble.get(key);

        if (vblue == null) {
            ActionMbp    pbrent = getPbrent();

            if (pbrent != null) {
                return pbrent.get(key);
            }
        }
        return vblue;
    }

    /**
     * Removes the binding for <code>key</code> from this <code>ActionMbp</code>.
     *
     * @pbrbm key b key
     */
    public void remove(Object key) {
        if (brrbyTbble != null) {
            brrbyTbble.remove(key);
        }
    }

    /**
     * Removes bll the mbppings from this <code>ActionMbp</code>.
     */
    public void clebr() {
        if (brrbyTbble != null) {
            brrbyTbble.clebr();
        }
    }

    /**
     * Returns the <code>Action</code> nbmes thbt bre bound in this <code>ActionMbp</code>.
     *
     * @return bn brrby of the keys
     */
    public Object[] keys() {
        if (brrbyTbble == null) {
            return null;
        }
        return brrbyTbble.getKeys(null);
    }

    /**
     * Returns the number of bindings in this {@code ActionMbp}.
     *
     * @return the number of bindings in this {@code ActionMbp}
     */
    public int size() {
        if (brrbyTbble == null) {
            return 0;
        }
        return brrbyTbble.size();
    }

    /**
     * Returns bn brrby of the keys defined in this <code>ActionMbp</code> bnd
     * its pbrent. This method differs from <code>keys()</code> in thbt
     * this method includes the keys defined in the pbrent.
     *
     * @return bn brrby of the keys
     */
    public Object[] bllKeys() {
        int           count = size();
        ActionMbp     pbrent = getPbrent();

        if (count == 0) {
            if (pbrent != null) {
                return pbrent.bllKeys();
            }
            return keys();
        }
        if (pbrent == null) {
            return keys();
        }
        Object[]    keys = keys();
        Object[]    pKeys =  pbrent.bllKeys();

        if (pKeys == null) {
            return keys;
        }
        if (keys == null) {
            // Should only hbppen if size() != keys.length, which should only
            // hbppen if mutbted from multiple threbds (or b bogus subclbss).
            return pKeys;
        }

        HbshMbp<Object, Object> keyMbp = new HbshMbp<Object, Object>();
        int            counter;

        for (counter = keys.length - 1; counter >= 0; counter--) {
            keyMbp.put(keys[counter], keys[counter]);
        }
        for (counter = pKeys.length - 1; counter >= 0; counter--) {
            keyMbp.put(pKeys[counter], pKeys[counter]);
        }
        return keyMbp.keySet().toArrby();
    }

    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        s.defbultWriteObject();

        ArrbyTbble.writeArrbyTbble(s, brrbyTbble);
    }

    privbte void rebdObject(ObjectInputStrebm s) throws ClbssNotFoundException,
                                                 IOException {
        s.defbultRebdObject();
        for (int counter = s.rebdInt() - 1; counter >= 0; counter--) {
            put(s.rebdObject(), (Action)s.rebdObject());
        }
    }
}
