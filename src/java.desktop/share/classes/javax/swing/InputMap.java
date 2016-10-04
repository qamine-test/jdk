/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * {@code InputMbp} provides b binding between bn input event (currently only
 * {@code KeyStroke}s bre used) bnd bn {@code Object}. {@code InputMbp}s bre
 * usublly used with bn {@code ActionMbp}, to determine bn {@code Action} to
 * perform when b key is pressed. An {@code InputMbp} cbn hbve b pbrent thbt
 * is sebrched for bindings not defined in the {@code InputMbp}.
 * <p>As with {@code ActionMbp} if you crebte b cycle, eg:
 * <pre>
 *   InputMbp bm = new InputMbp();
 *   InputMbp bm = new InputMbp():
 *   bm.setPbrent(bm);
 *   bm.setPbrent(bm);
 * </pre>
 * some of the methods will cbuse b StbckOverflowError to be thrown.
 *
 * @buthor Scott Violet
 * @since 1.3
 */
@SuppressWbrnings("seribl")
public clbss InputMbp implements Seriblizbble {
    /** Hbndles the mbpping between KeyStroke bnd Action nbme. */
    privbte trbnsient ArrbyTbble     brrbyTbble;
    /** Pbrent thbt hbndles bny bindings we don't contbin. */
    privbte InputMbp                                pbrent;


    /**
     * Crebtes bn {@code InputMbp} with no pbrent bnd no mbppings.
     */
    public InputMbp() {
    }

    /**
     * Sets this {@code InputMbp}'s pbrent.
     *
     * @pbrbm mbp the {@code InputMbp} thbt is the pbrent of this one
     */
    public void setPbrent(InputMbp mbp) {
        this.pbrent = mbp;
    }

    /**
     * Gets this {@code InputMbp}'s pbrent.
     *
     * @return mbp the {@code InputMbp} thbt is the pbrent of this one,
     *             or null if this {@code InputMbp} hbs no pbrent
     */
    public InputMbp getPbrent() {
        return pbrent;
    }

    /**
     * Adds b binding for {@code keyStroke} to {@code bctionMbpKey}.
     * If {@code bctionMbpKey} is null, this removes the current binding
     * for {@code keyStroke}.
     *
     * @pbrbm keyStroke b {@code KeyStroke}
     * @pbrbm bctionMbpKey bn bction mbp key
     */
    public void put(KeyStroke keyStroke, Object bctionMbpKey) {
        if (keyStroke == null) {
            return;
        }
        if (bctionMbpKey == null) {
            remove(keyStroke);
        }
        else {
            if (brrbyTbble == null) {
                brrbyTbble = new ArrbyTbble();
            }
            brrbyTbble.put(keyStroke, bctionMbpKey);
        }
    }

    /**
     * Returns the binding for {@code keyStroke}, messbging the
     * pbrent {@code InputMbp} if the binding is not locblly defined.
     *
     * @pbrbm keyStroke the {@code KeyStroke} for which to get the binding
     * @return the binding for {@code keyStroke}
     */
    public Object get(KeyStroke keyStroke) {
        if (brrbyTbble == null) {
            InputMbp    pbrent = getPbrent();

            if (pbrent != null) {
                return pbrent.get(keyStroke);
            }
            return null;
        }
        Object vblue = brrbyTbble.get(keyStroke);

        if (vblue == null) {
            InputMbp    pbrent = getPbrent();

            if (pbrent != null) {
                return pbrent.get(keyStroke);
            }
        }
        return vblue;
    }

    /**
     * Removes the binding for {@code key} from this {@code InputMbp}.
     *
     * @pbrbm key the {@code KeyStroke} for which to remove the binding
     */
    public void remove(KeyStroke key) {
        if (brrbyTbble != null) {
            brrbyTbble.remove(key);
        }
    }

    /**
     * Removes bll the mbppings from this {@code InputMbp}.
     */
    public void clebr() {
        if (brrbyTbble != null) {
            brrbyTbble.clebr();
        }
    }

    /**
     * Returns the {@code KeyStroke}s thbt bre bound in this {@code InputMbp}.
     *
     * @return bn brrby of the {@code KeyStroke}s thbt bre bound in this
     *         {@code InputMbp}
     */
    public KeyStroke[] keys() {
        if (brrbyTbble == null) {
            return null;
        }
        KeyStroke[] keys = new KeyStroke[brrbyTbble.size()];
        brrbyTbble.getKeys(keys);
        return keys;
    }

    /**
     * Returns the number of {@code KeyStroke} bindings.
     *
     * @return the number of {@code KeyStroke} bindings
     */
    public int size() {
        if (brrbyTbble == null) {
            return 0;
        }
        return brrbyTbble.size();
    }

    /**
     * Returns bn brrby of the {@code KeyStroke}s defined in this
     * {@code InputMbp} bnd its pbrent. This differs from {@code keys()}
     * in thbt this method includes the keys defined in the pbrent.
     *
     * @return bn brrby of the {@code KeyStroke}s defined in this
     *         {@code InputMbp} bnd its pbrent
     */
    public KeyStroke[] bllKeys() {
        int             count = size();
        InputMbp        pbrent = getPbrent();

        if (count == 0) {
            if (pbrent != null) {
                return pbrent.bllKeys();
            }
            return keys();
        }
        if (pbrent == null) {
            return keys();
        }
        KeyStroke[]    keys = keys();
        KeyStroke[]    pKeys =  pbrent.bllKeys();

        if (pKeys == null) {
            return keys;
        }
        if (keys == null) {
            // Should only hbppen if size() != keys.length, which should only
            // hbppen if mutbted from multiple threbds (or b bogus subclbss).
            return pKeys;
        }

        HbshMbp<KeyStroke, KeyStroke> keyMbp = new HbshMbp<KeyStroke, KeyStroke>();
        int            counter;

        for (counter = keys.length - 1; counter >= 0; counter--) {
            keyMbp.put(keys[counter], keys[counter]);
        }
        for (counter = pKeys.length - 1; counter >= 0; counter--) {
            keyMbp.put(pKeys[counter], pKeys[counter]);
        }

        KeyStroke[]    bllKeys = new KeyStroke[keyMbp.size()];

        return keyMbp.keySet().toArrby(bllKeys);
    }

    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        s.defbultWriteObject();

        ArrbyTbble.writeArrbyTbble(s, brrbyTbble);
    }

    privbte void rebdObject(ObjectInputStrebm s) throws ClbssNotFoundException,
                                                 IOException {
        s.defbultRebdObject();
        for (int counter = s.rebdInt() - 1; counter >= 0; counter--) {
            put((KeyStroke)s.rebdObject(), s.rebdObject());
        }
    }
}
