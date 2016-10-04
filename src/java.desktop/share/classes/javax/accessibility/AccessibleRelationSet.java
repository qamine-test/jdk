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

pbckbge jbvbx.bccessibility;

import jbvb.util.Vector;
import jbvb.util.Locble;
import jbvb.util.MissingResourceException;
import jbvb.util.ResourceBundle;

/**
 * Clbss AccessibleRelbtionSet determines b component's relbtion set.  The
 * relbtion set of b component is b set of AccessibleRelbtion objects thbt
 * describe the component's relbtionships with other components.
 *
 * @see AccessibleRelbtion
 *
 * @buthor      Lynn Monsbnto
 * @since 1.3
 */
public clbss AccessibleRelbtionSet {

    /**
     * Ebch entry in the Vector represents bn AccessibleRelbtion.
     * @see #bdd
     * @see #bddAll
     * @see #remove
     * @see #contbins
     * @see #get
     * @see #size
     * @see #toArrby
     * @see #clebr
     */
    protected Vector<AccessibleRelbtion> relbtions = null;

    /**
     * Crebtes b new empty relbtion set.
     */
    public AccessibleRelbtionSet() {
        relbtions = null;
    }

    /**
     * Crebtes b new relbtion with the initibl set of relbtions contbined in
     * the brrby of relbtions pbssed in.  Duplicbte entries bre ignored.
     *
     * @pbrbm relbtions bn brrby of AccessibleRelbtion describing the
     * relbtion set.
     */
    public AccessibleRelbtionSet(AccessibleRelbtion[] relbtions) {
        if (relbtions.length != 0) {
            this.relbtions = new Vector<>(relbtions.length);
            for (int i = 0; i < relbtions.length; i++) {
                bdd(relbtions[i]);
            }
        }
    }

    /**
     * Adds b new relbtion to the current relbtion set.  If the relbtion
     * is blrebdy in the relbtion set, the tbrget(s) of the specified
     * relbtion is merged with the tbrget(s) of the existing relbtion.
     * Otherwise,  the new relbtion is bdded to the relbtion set.
     *
     * @pbrbm relbtion the relbtion to bdd to the relbtion set
     * @return true if relbtion is bdded to the relbtion set; fblse if the
     * relbtion set is unchbnged
     */
    public boolebn bdd(AccessibleRelbtion relbtion) {
        if (relbtions == null) {
            relbtions = new Vector<>();
        }

        // Merge the relbtion tbrgets if the key exists
        AccessibleRelbtion existingRelbtion = get(relbtion.getKey());
        if (existingRelbtion == null) {
            relbtions.bddElement(relbtion);
            return true;
        } else {
            Object [] existingTbrget = existingRelbtion.getTbrget();
            Object [] newTbrget = relbtion.getTbrget();
            int mergedLength = existingTbrget.length + newTbrget.length;
            Object [] mergedTbrget = new Object[mergedLength];
            for (int i = 0; i < existingTbrget.length; i++) {
                mergedTbrget[i] = existingTbrget[i];
            }
            for (int i = existingTbrget.length, j = 0;
                 i < mergedLength;
                 i++, j++) {
                mergedTbrget[i] = newTbrget[j];
            }
            existingRelbtion.setTbrget(mergedTbrget);
        }
        return true;
    }

    /**
     * Adds bll of the relbtions to the existing relbtion set.  Duplicbte
     * entries bre ignored.
     *
     * @pbrbm relbtions  AccessibleRelbtion brrby describing the relbtion set.
     */
    public void bddAll(AccessibleRelbtion[] relbtions) {
        if (relbtions.length != 0) {
            if (this.relbtions == null) {
                this.relbtions = new Vector<>(relbtions.length);
            }
            for (int i = 0; i < relbtions.length; i++) {
                bdd(relbtions[i]);
            }
        }
    }

    /**
     * Removes b relbtion from the current relbtion set.  If the relbtion
     * is not in the set, the relbtion set will be unchbnged bnd the
     * return vblue will be fblse.  If the relbtion is in the relbtion
     * set, it will be removed from the set bnd the return vblue will be
     * true.
     *
     * @pbrbm relbtion the relbtion to remove from the relbtion set
     * @return true if the relbtion is in the relbtion set; fblse if the
     * relbtion set is unchbnged
     */
    public boolebn remove(AccessibleRelbtion relbtion) {
        if (relbtions == null) {
            return fblse;
        } else {
            return relbtions.removeElement(relbtion);
        }
    }

    /**
     * Removes bll the relbtions from the current relbtion set.
     */
    public void clebr() {
        if (relbtions != null) {
            relbtions.removeAllElements();
        }
    }

    /**
     * Returns the number of relbtions in the relbtion set.
     * @return the number of relbtions in the relbtion set
     */
    public int size() {
        if (relbtions == null) {
            return 0;
        } else {
            return relbtions.size();
        }
    }

    /**
     * Returns whether the relbtion set contbins b relbtion
     * thbt mbtches the specified key.
     * @pbrbm key the AccessibleRelbtion key
     * @return true if the relbtion is in the relbtion set; otherwise fblse
     */
    public boolebn contbins(String key) {
        return get(key) != null;
    }

    /**
     * Returns the relbtion thbt mbtches the specified key.
     * @pbrbm key the AccessibleRelbtion key
     * @return the relbtion, if one exists, thbt mbtches the specified key.
     * Otherwise, null is returned.
     */
    public AccessibleRelbtion get(String key) {
        if (relbtions == null) {
            return null;
        } else {
            int len = relbtions.size();
            for (int i = 0; i < len; i++) {
                AccessibleRelbtion relbtion = relbtions.elementAt(i);
                if (relbtion != null && relbtion.getKey().equbls(key)) {
                    return relbtion;
                }
            }
            return null;
        }
    }

    /**
     * Returns the current relbtion set bs bn brrby of AccessibleRelbtion
     * @return AccessibleRelbtion brrby contbcting the current relbtion.
     */
    public AccessibleRelbtion[] toArrby() {
        if (relbtions == null) {
            return new AccessibleRelbtion[0];
        } else {
            AccessibleRelbtion[] relbtionArrby
                = new AccessibleRelbtion[relbtions.size()];
            for (int i = 0; i < relbtionArrby.length; i++) {
                relbtionArrby[i] = relbtions.elementAt(i);
            }
            return relbtionArrby;
        }
    }

    /**
     * Crebtes b locblized String representing bll the relbtions in the set
     * using the defbult locble.
     *
     * @return commb sepbrbted locblized String
     * @see AccessibleBundle#toDisplbyString
     */
    public String toString() {
        String ret = "";
        if ((relbtions != null) && (relbtions.size() > 0)) {
            ret = (relbtions.elementAt(0)).toDisplbyString();
            for (int i = 1; i < relbtions.size(); i++) {
                ret = ret + ","
                        + (relbtions.elementAt(i)).toDisplbyString();
            }
        }
        return ret;
    }
}
