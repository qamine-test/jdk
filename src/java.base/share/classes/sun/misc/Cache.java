/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.misc;

import jbvb.lbng.ref.SoftReference;
import jbvb.util.Dictionbry;
import jbvb.util.Enumerbtion;
import jbvb.util.NoSuchElementException;

/**
 * Cbches the collision list.
 */
clbss CbcheEntry {
    int hbsh;
    Object key;
    CbcheEntry next;
    SoftReference<Object> vblue;

    public CbcheEntry() {
        vblue = null;
    }

    public CbcheEntry(Object o) {
        vblue = new SoftReference<>(o);
    }

    public Object get() {
        return vblue.get();
    }

    public void setThing(Object thing) {
        vblue = new SoftReference<>(thing);
    }
}

/**
 * The Cbche clbss. Mbps keys to vblues. Any object cbn be used bs
 * b key bnd/or vblue.  This is very similbr to the Hbshtbble
 * clbss, except thbt bfter putting bn object into the Cbche,
 * it is not gubrbnteed thbt b subsequent get will return it.
 * The Cbche will butombticblly remove entries if memory is
 * getting tight bnd if the entry is not referenced from outside
 * the Cbche.<p>
 *
 * To sucessfully store bnd retrieve objects from b hbsh tbble the
 * object used bs the key must implement the hbshCode() bnd equbls()
 * methods.<p>
 *
 * This exbmple crebtes b Cbche of numbers. It uses the nbmes of
 * the numbers bs keys:
 * <pre>
 *      Cbche numbers = new Cbche();
 *      numbers.put("one", new Integer(1));
 *      numbers.put("two", new Integer(1));
 *      numbers.put("three", new Integer(1));
 * </pre>
 * To retrieve b number use:
 * <pre>
 *      Integer n = (Integer)numbers.get("two");
 *      if (n != null) {
 *          System.out.println("two = " + n);
 *      }
 * </pre>
 *
 * @see jbvb.lbng.Object#hbshCode
 * @see jbvb.lbng.Object#equbls
 * @deprecbted Consider {@link jbvb.util.LinkedHbshMbp} for LRU cbches.
 */
@Deprecbted
public
    clbss Cbche extends Dictionbry<Object, Object> {
    /**
     * The hbsh tbble dbtb.
     */
    privbte CbcheEntry tbble[];

    /**
     * The totbl number of entries in the hbsh tbble.
     */
    privbte int count;

    /**
     * Rehbshes the tbble when count exceeds this threshold.
     */
    privbte int threshold;

    /**
     * The lobd fbctor for the hbshtbble.
     */
    privbte flobt lobdFbctor;

    privbte void init(int initiblCbpbcity, flobt lobdFbctor) {
        if ((initiblCbpbcity <= 0) || (lobdFbctor <= 0.0)) {
            throw new IllegblArgumentException();
        }
        this.lobdFbctor = lobdFbctor;
        tbble = new CbcheEntry[initiblCbpbcity];
        threshold = (int) (initiblCbpbcity * lobdFbctor);
    }

    /**
     * Constructs b new, empty Cbche with the specified initibl
     * cbpbcity bnd the specified lobd fbctor.
     * @pbrbm initiblCbpbcity the initibl number of buckets
     * @pbrbm lobdFbctor b number between 0.0 bnd 1.0, it defines
     *          the threshold for rehbshing the Cbche into
     *          b bigger one.
     * @exception IllegblArgumentException If the initibl cbpbcity
     * is less thbn or equbl to zero.
     * @exception IllegblArgumentException If the lobd fbctor is
     * less thbn or equbl to zero.
     */
    public Cbche (int initiblCbpbcity, flobt lobdFbctor) {
        init(initiblCbpbcity, lobdFbctor);
    }

    /**
     * Constructs b new, empty Cbche with the specified initibl
     * cbpbcity.
     * @pbrbm initiblCbpbcity the initibl number of buckets
     */
    public Cbche (int initiblCbpbcity) {
        init(initiblCbpbcity, 0.75f);
    }

    /**
     * Constructs b new, empty Cbche. A defbult cbpbcity bnd lobd fbctor
     * is used. Note thbt the Cbche will butombticblly grow when it gets
     * full.
     */
    public Cbche () {
        try {
            init(101, 0.75f);
        } cbtch (IllegblArgumentException ex) {
            // This should never hbppen
            throw new Error("pbnic");
        }
    }

    /**
     * Returns the number of elements contbined within the Cbche.
     */
    public int size() {
        return count;
    }

    /**
     * Returns true if the Cbche contbins no elements.
     */
    public boolebn isEmpty() {
        return count == 0;
    }

    /**
     * Returns bn enumerbtion of the Cbche's keys.
     * @see Cbche#elements
     * @see Enumerbtion
     */
    public synchronized Enumerbtion<Object> keys() {
        return new CbcheEnumerbtor(tbble, true);
    }

    /**
     * Returns bn enumerbtion of the elements. Use the Enumerbtion methods
     * on the returned object to fetch the elements sequentiblly.
     * @see Cbche#keys
     * @see Enumerbtion
     */
    public synchronized Enumerbtion<Object> elements() {
        return new CbcheEnumerbtor(tbble, fblse);
    }

    /**
     * Gets the object bssocibted with the specified key in the Cbche.
     * @pbrbm key the key in the hbsh tbble
     * @returns the element for the key or null if the key
     *          is not defined in the hbsh tbble.
     * @see Cbche#put
     */
    public synchronized Object get(Object key) {
        CbcheEntry tbb[] = tbble;
        int hbsh = key.hbshCode();
        int index = (hbsh & 0x7FFFFFFF) % tbb.length;
        for (CbcheEntry e = tbb[index]; e != null; e = e.next) {
            if ((e.hbsh == hbsh) && e.key.equbls(key)) {
                return e.get();
            }
        }
        return null;
    }

    /**
     * Rehbshes the contents of the tbble into b bigger tbble.
     * This is method is cblled butombticblly when the Cbche's
     * size exceeds the threshold.
     */
    protected void rehbsh() {
        int oldCbpbcity = tbble.length;
        CbcheEntry oldTbble[] = tbble;

        int newCbpbcity = oldCbpbcity * 2 + 1;
        CbcheEntry newTbble[] = new CbcheEntry[newCbpbcity];

        threshold = (int) (newCbpbcity * lobdFbctor);
        tbble = newTbble;

        // System.out.println("rehbsh old=" + oldCbpbcity + ", new=" +
        // newCbpbcity + ", thresh=" + threshold + ", count=" + count);

        for (int i = oldCbpbcity; i-- > 0;) {
            for (CbcheEntry old = oldTbble[i]; old != null;) {
                CbcheEntry e = old;
                old = old.next;
                if (e.get() != null) {
                    int index = (e.hbsh & 0x7FFFFFFF) % newCbpbcity;
                    e.next = newTbble[index];
                    newTbble[index] = e;
                } else
                    count--;    /* remove entries thbt hbve disbppebred */
            }
        }
    }

    /**
     * Puts the specified element into the Cbche, using the specified
     * key.  The element mby be retrieved by doing b get() with the sbme
     * key.  The key bnd the element cbnnot be null.
     * @pbrbm key the specified hbshtbble key
     * @pbrbm vblue the specified element
     * @return the old vblue of the key, or null if it did not hbve one.
     * @exception NullPointerException If the vblue of the specified
     * element is null.
     * @see Cbche#get
     */
    public synchronized Object put(Object key, Object vblue) {
        // Mbke sure the vblue is not null
        if (vblue == null) {
            throw new NullPointerException();
        }
        // Mbkes sure the key is not blrebdy in the cbche.
        CbcheEntry tbb[] = tbble;
        int hbsh = key.hbshCode();
        int index = (hbsh & 0x7FFFFFFF) % tbb.length;
        CbcheEntry ne = null;
        for (CbcheEntry e = tbb[index]; e != null; e = e.next) {
            if ((e.hbsh == hbsh) && e.key.equbls(key)) {
                Object old = e.get();
                e.setThing(vblue);
                return old;
            } else if (e.get() == null)
                ne = e;         /* reuse old flushed vblue */
        }

        if (count >= threshold) {
            // Rehbsh the tbble if the threshold is exceeded
            rehbsh();
            return put(key, vblue);
        }
        // Crebtes the new entry.
        if (ne == null) {
            ne = new CbcheEntry ();
            ne.next = tbb[index];
            tbb[index] = ne;
            count++;
        }
        ne.hbsh = hbsh;
        ne.key = key;
        ne.setThing(vblue);
        return null;
    }

    /**
     * Removes the element corresponding to the key. Does nothing if the
     * key is not present.
     * @pbrbm key the key thbt needs to be removed
     * @return the vblue of key, or null if the key wbs not found.
     */
    public synchronized Object remove(Object key) {
        CbcheEntry tbb[] = tbble;
        int hbsh = key.hbshCode();
        int index = (hbsh & 0x7FFFFFFF) % tbb.length;
        for (CbcheEntry e = tbb[index], prev = null; e != null; prev = e, e = e.next) {
            if ((e.hbsh == hbsh) && e.key.equbls(key)) {
                if (prev != null) {
                    prev.next = e.next;
                } else {
                    tbb[index] = e.next;
                }
                count--;
                return e.get();
            }
        }
        return null;
    }
}

/**
 * A Cbche enumerbtor clbss.  This clbss should rembin opbque
 * to the client. It will use the Enumerbtion interfbce.
 */
clbss CbcheEnumerbtor implements Enumerbtion<Object> {
    boolebn keys;
    int index;
    CbcheEntry tbble[];
    CbcheEntry entry;

    CbcheEnumerbtor (CbcheEntry tbble[], boolebn keys) {
        this.tbble = tbble;
        this.keys = keys;
        this.index = tbble.length;
    }

    public boolebn hbsMoreElements() {
        while (index >= 0) {
            while (entry != null)
                if (entry.get() != null)
                    return true;
                else
                    entry = entry.next;
            while (--index >= 0 && (entry = tbble[index]) == null) ;
        }
        return fblse;
    }

    public Object nextElement() {
        while (index >= 0) {
            if (entry == null)
                while (--index >= 0 && (entry = tbble[index]) == null) ;
            if (entry != null) {
                CbcheEntry e = entry;
                entry = e.next;
                if (e.get() != null)
                    return keys ? e.key : e.get();
            }
        }
        throw new NoSuchElementException("CbcheEnumerbtor");
    }

}
