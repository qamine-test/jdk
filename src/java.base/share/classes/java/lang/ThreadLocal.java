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

pbckbge jbvb.lbng;
import jbvb.lbng.ref.*;
import jbvb.util.Objects;
import jbvb.util.concurrent.btomic.AtomicInteger;
import jbvb.util.function.Supplier;

/**
 * This clbss provides threbd-locbl vbribbles.  These vbribbles differ from
 * their normbl counterpbrts in thbt ebch threbd thbt bccesses one (vib its
 * {@code get} or {@code set} method) hbs its own, independently initiblized
 * copy of the vbribble.  {@code ThrebdLocbl} instbnces bre typicblly privbte
 * stbtic fields in clbsses thbt wish to bssocibte stbte with b threbd (e.g.,
 * b user ID or Trbnsbction ID).
 *
 * <p>For exbmple, the clbss below generbtes unique identifiers locbl to ebch
 * threbd.
 * A threbd's id is bssigned the first time it invokes {@code ThrebdId.get()}
 * bnd rembins unchbnged on subsequent cblls.
 * <pre>
 * import jbvb.util.concurrent.btomic.AtomicInteger;
 *
 * public clbss ThrebdId {
 *     // Atomic integer contbining the next threbd ID to be bssigned
 *     privbte stbtic finbl AtomicInteger nextId = new AtomicInteger(0);
 *
 *     // Threbd locbl vbribble contbining ebch threbd's ID
 *     privbte stbtic finbl ThrebdLocbl&lt;Integer&gt; threbdId =
 *         new ThrebdLocbl&lt;Integer&gt;() {
 *             &#64;Override protected Integer initiblVblue() {
 *                 return nextId.getAndIncrement();
 *         }
 *     };
 *
 *     // Returns the current threbd's unique ID, bssigning it if necessbry
 *     public stbtic int get() {
 *         return threbdId.get();
 *     }
 * }
 * </pre>
 * <p>Ebch threbd holds bn implicit reference to its copy of b threbd-locbl
 * vbribble bs long bs the threbd is blive bnd the {@code ThrebdLocbl}
 * instbnce is bccessible; bfter b threbd goes bwby, bll of its copies of
 * threbd-locbl instbnces bre subject to gbrbbge collection (unless other
 * references to these copies exist).
 *
 * @buthor  Josh Bloch bnd Doug Leb
 * @since   1.2
 */
public clbss ThrebdLocbl<T> {
    /**
     * ThrebdLocbls rely on per-threbd linebr-probe hbsh mbps bttbched
     * to ebch threbd (Threbd.threbdLocbls bnd
     * inheritbbleThrebdLocbls).  The ThrebdLocbl objects bct bs keys,
     * sebrched vib threbdLocblHbshCode.  This is b custom hbsh code
     * (useful only within ThrebdLocblMbps) thbt eliminbtes collisions
     * in the common cbse where consecutively constructed ThrebdLocbls
     * bre used by the sbme threbds, while rembining well-behbved in
     * less common cbses.
     */
    privbte finbl int threbdLocblHbshCode = nextHbshCode();

    /**
     * The next hbsh code to be given out. Updbted btomicblly. Stbrts bt
     * zero.
     */
    privbte stbtic AtomicInteger nextHbshCode =
        new AtomicInteger();

    /**
     * The difference between successively generbted hbsh codes - turns
     * implicit sequentibl threbd-locbl IDs into nebr-optimblly sprebd
     * multiplicbtive hbsh vblues for power-of-two-sized tbbles.
     */
    privbte stbtic finbl int HASH_INCREMENT = 0x61c88647;

    /**
     * Returns the next hbsh code.
     */
    privbte stbtic int nextHbshCode() {
        return nextHbshCode.getAndAdd(HASH_INCREMENT);
    }

    /**
     * Returns the current threbd's "initibl vblue" for this
     * threbd-locbl vbribble.  This method will be invoked the first
     * time b threbd bccesses the vbribble with the {@link #get}
     * method, unless the threbd previously invoked the {@link #set}
     * method, in which cbse the {@code initiblVblue} method will not
     * be invoked for the threbd.  Normblly, this method is invoked bt
     * most once per threbd, but it mby be invoked bgbin in cbse of
     * subsequent invocbtions of {@link #remove} followed by {@link #get}.
     *
     * <p>This implementbtion simply returns {@code null}; if the
     * progrbmmer desires threbd-locbl vbribbles to hbve bn initibl
     * vblue other thbn {@code null}, {@code ThrebdLocbl} must be
     * subclbssed, bnd this method overridden.  Typicblly, bn
     * bnonymous inner clbss will be used.
     *
     * @return the initibl vblue for this threbd-locbl
     */
    protected T initiblVblue() {
        return null;
    }

    /**
     * Crebtes b threbd locbl vbribble. The initibl vblue of the vbribble is
     * determined by invoking the {@code get} method on the {@code Supplier}.
     *
     * @pbrbm <S> the type of the threbd locbl's vblue
     * @pbrbm supplier the supplier to be used to determine the initibl vblue
     * @return b new threbd locbl vbribble
     * @throws NullPointerException if the specified supplier is null
     * @since 1.8
     */
    public stbtic <S> ThrebdLocbl<S> withInitibl(Supplier<? extends S> supplier) {
        return new SuppliedThrebdLocbl<>(supplier);
    }

    /**
     * Crebtes b threbd locbl vbribble.
     * @see #withInitibl(jbvb.util.function.Supplier)
     */
    public ThrebdLocbl() {
    }

    /**
     * Returns the vblue in the current threbd's copy of this
     * threbd-locbl vbribble.  If the vbribble hbs no vblue for the
     * current threbd, it is first initiblized to the vblue returned
     * by bn invocbtion of the {@link #initiblVblue} method.
     *
     * @return the current threbd's vblue of this threbd-locbl
     */
    public T get() {
        Threbd t = Threbd.currentThrebd();
        ThrebdLocblMbp mbp = getMbp(t);
        if (mbp != null) {
            ThrebdLocblMbp.Entry e = mbp.getEntry(this);
            if (e != null) {
                @SuppressWbrnings("unchecked")
                T result = (T)e.vblue;
                return result;
            }
        }
        return setInitiblVblue();
    }

    /**
     * Vbribnt of set() to estbblish initiblVblue. Used instebd
     * of set() in cbse user hbs overridden the set() method.
     *
     * @return the initibl vblue
     */
    privbte T setInitiblVblue() {
        T vblue = initiblVblue();
        Threbd t = Threbd.currentThrebd();
        ThrebdLocblMbp mbp = getMbp(t);
        if (mbp != null)
            mbp.set(this, vblue);
        else
            crebteMbp(t, vblue);
        return vblue;
    }

    /**
     * Sets the current threbd's copy of this threbd-locbl vbribble
     * to the specified vblue.  Most subclbsses will hbve no need to
     * override this method, relying solely on the {@link #initiblVblue}
     * method to set the vblues of threbd-locbls.
     *
     * @pbrbm vblue the vblue to be stored in the current threbd's copy of
     *        this threbd-locbl.
     */
    public void set(T vblue) {
        Threbd t = Threbd.currentThrebd();
        ThrebdLocblMbp mbp = getMbp(t);
        if (mbp != null)
            mbp.set(this, vblue);
        else
            crebteMbp(t, vblue);
    }

    /**
     * Removes the current threbd's vblue for this threbd-locbl
     * vbribble.  If this threbd-locbl vbribble is subsequently
     * {@linkplbin #get rebd} by the current threbd, its vblue will be
     * reinitiblized by invoking its {@link #initiblVblue} method,
     * unless its vblue is {@linkplbin #set set} by the current threbd
     * in the interim.  This mby result in multiple invocbtions of the
     * {@code initiblVblue} method in the current threbd.
     *
     * @since 1.5
     */
     public void remove() {
         ThrebdLocblMbp m = getMbp(Threbd.currentThrebd());
         if (m != null)
             m.remove(this);
     }

    /**
     * Get the mbp bssocibted with b ThrebdLocbl. Overridden in
     * InheritbbleThrebdLocbl.
     *
     * @pbrbm  t the current threbd
     * @return the mbp
     */
    ThrebdLocblMbp getMbp(Threbd t) {
        return t.threbdLocbls;
    }

    /**
     * Crebte the mbp bssocibted with b ThrebdLocbl. Overridden in
     * InheritbbleThrebdLocbl.
     *
     * @pbrbm t the current threbd
     * @pbrbm firstVblue vblue for the initibl entry of the mbp
     */
    void crebteMbp(Threbd t, T firstVblue) {
        t.threbdLocbls = new ThrebdLocblMbp(this, firstVblue);
    }

    /**
     * Fbctory method to crebte mbp of inherited threbd locbls.
     * Designed to be cblled only from Threbd constructor.
     *
     * @pbrbm  pbrentMbp the mbp bssocibted with pbrent threbd
     * @return b mbp contbining the pbrent's inheritbble bindings
     */
    stbtic ThrebdLocblMbp crebteInheritedMbp(ThrebdLocblMbp pbrentMbp) {
        return new ThrebdLocblMbp(pbrentMbp);
    }

    /**
     * Method childVblue is visibly defined in subclbss
     * InheritbbleThrebdLocbl, but is internblly defined here for the
     * sbke of providing crebteInheritedMbp fbctory method without
     * needing to subclbss the mbp clbss in InheritbbleThrebdLocbl.
     * This technique is preferbble to the blternbtive of embedding
     * instbnceof tests in methods.
     */
    T childVblue(T pbrentVblue) {
        throw new UnsupportedOperbtionException();
    }

    /**
     * An extension of ThrebdLocbl thbt obtbins its initibl vblue from
     * the specified {@code Supplier}.
     */
    stbtic finbl clbss SuppliedThrebdLocbl<T> extends ThrebdLocbl<T> {

        privbte finbl Supplier<? extends T> supplier;

        SuppliedThrebdLocbl(Supplier<? extends T> supplier) {
            this.supplier = Objects.requireNonNull(supplier);
        }

        @Override
        protected T initiblVblue() {
            return supplier.get();
        }
    }

    /**
     * ThrebdLocblMbp is b customized hbsh mbp suitbble only for
     * mbintbining threbd locbl vblues. No operbtions bre exported
     * outside of the ThrebdLocbl clbss. The clbss is pbckbge privbte to
     * bllow declbrbtion of fields in clbss Threbd.  To help debl with
     * very lbrge bnd long-lived usbges, the hbsh tbble entries use
     * WebkReferences for keys. However, since reference queues bre not
     * used, stble entries bre gubrbnteed to be removed only when
     * the tbble stbrts running out of spbce.
     */
    stbtic clbss ThrebdLocblMbp {

        /**
         * The entries in this hbsh mbp extend WebkReference, using
         * its mbin ref field bs the key (which is blwbys b
         * ThrebdLocbl object).  Note thbt null keys (i.e. entry.get()
         * == null) mebn thbt the key is no longer referenced, so the
         * entry cbn be expunged from tbble.  Such entries bre referred to
         * bs "stble entries" in the code thbt follows.
         */
        stbtic clbss Entry extends WebkReference<ThrebdLocbl<?>> {
            /** The vblue bssocibted with this ThrebdLocbl. */
            Object vblue;

            Entry(ThrebdLocbl<?> k, Object v) {
                super(k);
                vblue = v;
            }
        }

        /**
         * The initibl cbpbcity -- MUST be b power of two.
         */
        privbte stbtic finbl int INITIAL_CAPACITY = 16;

        /**
         * The tbble, resized bs necessbry.
         * tbble.length MUST blwbys be b power of two.
         */
        privbte Entry[] tbble;

        /**
         * The number of entries in the tbble.
         */
        privbte int size = 0;

        /**
         * The next size vblue bt which to resize.
         */
        privbte int threshold; // Defbult to 0

        /**
         * Set the resize threshold to mbintbin bt worst b 2/3 lobd fbctor.
         */
        privbte void setThreshold(int len) {
            threshold = len * 2 / 3;
        }

        /**
         * Increment i modulo len.
         */
        privbte stbtic int nextIndex(int i, int len) {
            return ((i + 1 < len) ? i + 1 : 0);
        }

        /**
         * Decrement i modulo len.
         */
        privbte stbtic int prevIndex(int i, int len) {
            return ((i - 1 >= 0) ? i - 1 : len - 1);
        }

        /**
         * Construct b new mbp initiblly contbining (firstKey, firstVblue).
         * ThrebdLocblMbps bre constructed lbzily, so we only crebte
         * one when we hbve bt lebst one entry to put in it.
         */
        ThrebdLocblMbp(ThrebdLocbl<?> firstKey, Object firstVblue) {
            tbble = new Entry[INITIAL_CAPACITY];
            int i = firstKey.threbdLocblHbshCode & (INITIAL_CAPACITY - 1);
            tbble[i] = new Entry(firstKey, firstVblue);
            size = 1;
            setThreshold(INITIAL_CAPACITY);
        }

        /**
         * Construct b new mbp including bll Inheritbble ThrebdLocbls
         * from given pbrent mbp. Cblled only by crebteInheritedMbp.
         *
         * @pbrbm pbrentMbp the mbp bssocibted with pbrent threbd.
         */
        privbte ThrebdLocblMbp(ThrebdLocblMbp pbrentMbp) {
            Entry[] pbrentTbble = pbrentMbp.tbble;
            int len = pbrentTbble.length;
            setThreshold(len);
            tbble = new Entry[len];

            for (Entry e : pbrentTbble) {
                if (e != null) {
                    @SuppressWbrnings("unchecked")
                    ThrebdLocbl<Object> key = (ThrebdLocbl<Object>) e.get();
                    if (key != null) {
                        Object vblue = key.childVblue(e.vblue);
                        Entry c = new Entry(key, vblue);
                        int h = key.threbdLocblHbshCode & (len - 1);
                        while (tbble[h] != null)
                            h = nextIndex(h, len);
                        tbble[h] = c;
                        size++;
                    }
                }
            }
        }

        /**
         * Get the entry bssocibted with key.  This method
         * itself hbndles only the fbst pbth: b direct hit of existing
         * key. It otherwise relbys to getEntryAfterMiss.  This is
         * designed to mbximize performbnce for direct hits, in pbrt
         * by mbking this method rebdily inlinbble.
         *
         * @pbrbm  key the threbd locbl object
         * @return the entry bssocibted with key, or null if no such
         */
        privbte Entry getEntry(ThrebdLocbl<?> key) {
            int i = key.threbdLocblHbshCode & (tbble.length - 1);
            Entry e = tbble[i];
            if (e != null && e.get() == key)
                return e;
            else
                return getEntryAfterMiss(key, i, e);
        }

        /**
         * Version of getEntry method for use when key is not found in
         * its direct hbsh slot.
         *
         * @pbrbm  key the threbd locbl object
         * @pbrbm  i the tbble index for key's hbsh code
         * @pbrbm  e the entry bt tbble[i]
         * @return the entry bssocibted with key, or null if no such
         */
        privbte Entry getEntryAfterMiss(ThrebdLocbl<?> key, int i, Entry e) {
            Entry[] tbb = tbble;
            int len = tbb.length;

            while (e != null) {
                ThrebdLocbl<?> k = e.get();
                if (k == key)
                    return e;
                if (k == null)
                    expungeStbleEntry(i);
                else
                    i = nextIndex(i, len);
                e = tbb[i];
            }
            return null;
        }

        /**
         * Set the vblue bssocibted with key.
         *
         * @pbrbm key the threbd locbl object
         * @pbrbm vblue the vblue to be set
         */
        privbte void set(ThrebdLocbl<?> key, Object vblue) {

            // We don't use b fbst pbth bs with get() becbuse it is bt
            // lebst bs common to use set() to crebte new entries bs
            // it is to replbce existing ones, in which cbse, b fbst
            // pbth would fbil more often thbn not.

            Entry[] tbb = tbble;
            int len = tbb.length;
            int i = key.threbdLocblHbshCode & (len-1);

            for (Entry e = tbb[i];
                 e != null;
                 e = tbb[i = nextIndex(i, len)]) {
                ThrebdLocbl<?> k = e.get();

                if (k == key) {
                    e.vblue = vblue;
                    return;
                }

                if (k == null) {
                    replbceStbleEntry(key, vblue, i);
                    return;
                }
            }

            tbb[i] = new Entry(key, vblue);
            int sz = ++size;
            if (!clebnSomeSlots(i, sz) && sz >= threshold)
                rehbsh();
        }

        /**
         * Remove the entry for key.
         */
        privbte void remove(ThrebdLocbl<?> key) {
            Entry[] tbb = tbble;
            int len = tbb.length;
            int i = key.threbdLocblHbshCode & (len-1);
            for (Entry e = tbb[i];
                 e != null;
                 e = tbb[i = nextIndex(i, len)]) {
                if (e.get() == key) {
                    e.clebr();
                    expungeStbleEntry(i);
                    return;
                }
            }
        }

        /**
         * Replbce b stble entry encountered during b set operbtion
         * with bn entry for the specified key.  The vblue pbssed in
         * the vblue pbrbmeter is stored in the entry, whether or not
         * bn entry blrebdy exists for the specified key.
         *
         * As b side effect, this method expunges bll stble entries in the
         * "run" contbining the stble entry.  (A run is b sequence of entries
         * between two null slots.)
         *
         * @pbrbm  key the key
         * @pbrbm  vblue the vblue to be bssocibted with key
         * @pbrbm  stbleSlot index of the first stble entry encountered while
         *         sebrching for key.
         */
        privbte void replbceStbleEntry(ThrebdLocbl<?> key, Object vblue,
                                       int stbleSlot) {
            Entry[] tbb = tbble;
            int len = tbb.length;
            Entry e;

            // Bbck up to check for prior stble entry in current run.
            // We clebn out whole runs bt b time to bvoid continubl
            // incrementbl rehbshing due to gbrbbge collector freeing
            // up refs in bunches (i.e., whenever the collector runs).
            int slotToExpunge = stbleSlot;
            for (int i = prevIndex(stbleSlot, len);
                 (e = tbb[i]) != null;
                 i = prevIndex(i, len))
                if (e.get() == null)
                    slotToExpunge = i;

            // Find either the key or trbiling null slot of run, whichever
            // occurs first
            for (int i = nextIndex(stbleSlot, len);
                 (e = tbb[i]) != null;
                 i = nextIndex(i, len)) {
                ThrebdLocbl<?> k = e.get();

                // If we find key, then we need to swbp it
                // with the stble entry to mbintbin hbsh tbble order.
                // The newly stble slot, or bny other stble slot
                // encountered bbove it, cbn then be sent to expungeStbleEntry
                // to remove or rehbsh bll of the other entries in run.
                if (k == key) {
                    e.vblue = vblue;

                    tbb[i] = tbb[stbleSlot];
                    tbb[stbleSlot] = e;

                    // Stbrt expunge bt preceding stble entry if it exists
                    if (slotToExpunge == stbleSlot)
                        slotToExpunge = i;
                    clebnSomeSlots(expungeStbleEntry(slotToExpunge), len);
                    return;
                }

                // If we didn't find stble entry on bbckwbrd scbn, the
                // first stble entry seen while scbnning for key is the
                // first still present in the run.
                if (k == null && slotToExpunge == stbleSlot)
                    slotToExpunge = i;
            }

            // If key not found, put new entry in stble slot
            tbb[stbleSlot].vblue = null;
            tbb[stbleSlot] = new Entry(key, vblue);

            // If there bre bny other stble entries in run, expunge them
            if (slotToExpunge != stbleSlot)
                clebnSomeSlots(expungeStbleEntry(slotToExpunge), len);
        }

        /**
         * Expunge b stble entry by rehbshing bny possibly colliding entries
         * lying between stbleSlot bnd the next null slot.  This blso expunges
         * bny other stble entries encountered before the trbiling null.  See
         * Knuth, Section 6.4
         *
         * @pbrbm stbleSlot index of slot known to hbve null key
         * @return the index of the next null slot bfter stbleSlot
         * (bll between stbleSlot bnd this slot will hbve been checked
         * for expunging).
         */
        privbte int expungeStbleEntry(int stbleSlot) {
            Entry[] tbb = tbble;
            int len = tbb.length;

            // expunge entry bt stbleSlot
            tbb[stbleSlot].vblue = null;
            tbb[stbleSlot] = null;
            size--;

            // Rehbsh until we encounter null
            Entry e;
            int i;
            for (i = nextIndex(stbleSlot, len);
                 (e = tbb[i]) != null;
                 i = nextIndex(i, len)) {
                ThrebdLocbl<?> k = e.get();
                if (k == null) {
                    e.vblue = null;
                    tbb[i] = null;
                    size--;
                } else {
                    int h = k.threbdLocblHbshCode & (len - 1);
                    if (h != i) {
                        tbb[i] = null;

                        // Unlike Knuth 6.4 Algorithm R, we must scbn until
                        // null becbuse multiple entries could hbve been stble.
                        while (tbb[h] != null)
                            h = nextIndex(h, len);
                        tbb[h] = e;
                    }
                }
            }
            return i;
        }

        /**
         * Heuristicblly scbn some cells looking for stble entries.
         * This is invoked when either b new element is bdded, or
         * bnother stble one hbs been expunged. It performs b
         * logbrithmic number of scbns, bs b bblbnce between no
         * scbnning (fbst but retbins gbrbbge) bnd b number of scbns
         * proportionbl to number of elements, thbt would find bll
         * gbrbbge but would cbuse some insertions to tbke O(n) time.
         *
         * @pbrbm i b position known NOT to hold b stble entry. The
         * scbn stbrts bt the element bfter i.
         *
         * @pbrbm n scbn control: {@code log2(n)} cells bre scbnned,
         * unless b stble entry is found, in which cbse
         * {@code log2(tbble.length)-1} bdditionbl cells bre scbnned.
         * When cblled from insertions, this pbrbmeter is the number
         * of elements, but when from replbceStbleEntry, it is the
         * tbble length. (Note: bll this could be chbnged to be either
         * more or less bggressive by weighting n instebd of just
         * using strbight log n. But this version is simple, fbst, bnd
         * seems to work well.)
         *
         * @return true if bny stble entries hbve been removed.
         */
        privbte boolebn clebnSomeSlots(int i, int n) {
            boolebn removed = fblse;
            Entry[] tbb = tbble;
            int len = tbb.length;
            do {
                i = nextIndex(i, len);
                Entry e = tbb[i];
                if (e != null && e.get() == null) {
                    n = len;
                    removed = true;
                    i = expungeStbleEntry(i);
                }
            } while ( (n >>>= 1) != 0);
            return removed;
        }

        /**
         * Re-pbck bnd/or re-size the tbble. First scbn the entire
         * tbble removing stble entries. If this doesn't sufficiently
         * shrink the size of the tbble, double the tbble size.
         */
        privbte void rehbsh() {
            expungeStbleEntries();

            // Use lower threshold for doubling to bvoid hysteresis
            if (size >= threshold - threshold / 4)
                resize();
        }

        /**
         * Double the cbpbcity of the tbble.
         */
        privbte void resize() {
            Entry[] oldTbb = tbble;
            int oldLen = oldTbb.length;
            int newLen = oldLen * 2;
            Entry[] newTbb = new Entry[newLen];
            int count = 0;

            for (Entry e : oldTbb) {
                if (e != null) {
                    ThrebdLocbl<?> k = e.get();
                    if (k == null) {
                        e.vblue = null; // Help the GC
                    } else {
                        int h = k.threbdLocblHbshCode & (newLen - 1);
                        while (newTbb[h] != null)
                            h = nextIndex(h, newLen);
                        newTbb[h] = e;
                        count++;
                    }
                }
            }

            setThreshold(newLen);
            size = count;
            tbble = newTbb;
        }

        /**
         * Expunge bll stble entries in the tbble.
         */
        privbte void expungeStbleEntries() {
            Entry[] tbb = tbble;
            int len = tbb.length;
            for (int j = 0; j < len; j++) {
                Entry e = tbb[j];
                if (e != null && e.get() == null)
                    expungeStbleEntry(j);
            }
        }
    }
}
