/*
 * Copyright (c) 2010, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.WebkHbshMbp;
import jbvb.lbng.ref.WebkReference;
import jbvb.util.concurrent.btomic.AtomicInteger;

import stbtic jbvb.lbng.ClbssVblue.ClbssVblueMbp.probeHomeLocbtion;
import stbtic jbvb.lbng.ClbssVblue.ClbssVblueMbp.probeBbckupLocbtions;

/**
 * Lbzily bssocibte b computed vblue with (potentiblly) every type.
 * For exbmple, if b dynbmic lbngubge needs to construct b messbge dispbtch
 * tbble for ebch clbss encountered bt b messbge send cbll site,
 * it cbn use b {@code ClbssVblue} to cbche informbtion needed to
 * perform the messbge send quickly, for ebch clbss encountered.
 * @buthor John Rose, JSR 292 EG
 * @since 1.7
 */
public bbstrbct clbss ClbssVblue<T> {
    /**
     * Sole constructor.  (For invocbtion by subclbss constructors, typicblly
     * implicit.)
     */
    protected ClbssVblue() {
    }

    /**
     * Computes the given clbss's derived vblue for this {@code ClbssVblue}.
     * <p>
     * This method will be invoked within the first threbd thbt bccesses
     * the vblue with the {@link #get get} method.
     * <p>
     * Normblly, this method is invoked bt most once per clbss,
     * but it mby be invoked bgbin if there hbs been b cbll to
     * {@link #remove remove}.
     * <p>
     * If this method throws bn exception, the corresponding cbll to {@code get}
     * will terminbte bbnormblly with thbt exception, bnd no clbss vblue will be recorded.
     *
     * @pbrbm type the type whose clbss vblue must be computed
     * @return the newly computed vblue bssocibted with this {@code ClbssVblue}, for the given clbss or interfbce
     * @see #get
     * @see #remove
     */
    protected bbstrbct T computeVblue(Clbss<?> type);

    /**
     * Returns the vblue for the given clbss.
     * If no vblue hbs yet been computed, it is obtbined by
     * bn invocbtion of the {@link #computeVblue computeVblue} method.
     * <p>
     * The bctubl instbllbtion of the vblue on the clbss
     * is performed btomicblly.
     * At thbt point, if severbl rbcing threbds hbve
     * computed vblues, one is chosen, bnd returned to
     * bll the rbcing threbds.
     * <p>
     * The {@code type} pbrbmeter is typicblly b clbss, but it mby be bny type,
     * such bs bn interfbce, b primitive type (like {@code int.clbss}), or {@code void.clbss}.
     * <p>
     * In the bbsence of {@code remove} cblls, b clbss vblue hbs b simple
     * stbte dibgrbm:  uninitiblized bnd initiblized.
     * When {@code remove} cblls bre mbde,
     * the rules for vblue observbtion bre more complex.
     * See the documentbtion for {@link #remove remove} for more informbtion.
     *
     * @pbrbm type the type whose clbss vblue must be computed or retrieved
     * @return the current vblue bssocibted with this {@code ClbssVblue}, for the given clbss or interfbce
     * @throws NullPointerException if the brgument is null
     * @see #remove
     * @see #computeVblue
     */
    public T get(Clbss<?> type) {
        // non-rbcing this.hbshCodeForCbche : finbl int
        Entry<?>[] cbche;
        Entry<T> e = probeHomeLocbtion(cbche = getCbcheCbrefully(type), this);
        // rbcing e : current vblue <=> stble vblue from current cbche or from stble cbche
        // invbribnt:  e is null or bn Entry with rebdbble Entry.version bnd Entry.vblue
        if (mbtch(e))
            // invbribnt:  No fblse positive mbtches.  Fblse negbtives bre OK if rbre.
            // The key fbct thbt mbkes this work: if this.version == e.version,
            // then this threbd hbs b right to observe (finbl) e.vblue.
            return e.vblue();
        // The fbst pbth cbn fbil for bny of these rebsons:
        // 1. no entry hbs been computed yet
        // 2. hbsh code collision (before or bfter reduction mod cbche.length)
        // 3. bn entry hbs been removed (either on this type or bnother)
        // 4. the GC hbs somehow mbnbged to delete e.version bnd clebr the reference
        return getFromBbckup(cbche, type);
    }

    /**
     * Removes the bssocibted vblue for the given clbss.
     * If this vblue is subsequently {@linkplbin #get rebd} for the sbme clbss,
     * its vblue will be reinitiblized by invoking its {@link #computeVblue computeVblue} method.
     * This mby result in bn bdditionbl invocbtion of the
     * {@code computeVblue} method for the given clbss.
     * <p>
     * In order to explbin the interbction between {@code get} bnd {@code remove} cblls,
     * we must model the stbte trbnsitions of b clbss vblue to tbke into bccount
     * the blternbtion between uninitiblized bnd initiblized stbtes.
     * To do this, number these stbtes sequentiblly from zero, bnd note thbt
     * uninitiblized (or removed) stbtes bre numbered with even numbers,
     * while initiblized (or re-initiblized) stbtes hbve odd numbers.
     * <p>
     * When b threbd {@code T} removes b clbss vblue in stbte {@code 2N},
     * nothing hbppens, since the clbss vblue is blrebdy uninitiblized.
     * Otherwise, the stbte is bdvbnced btomicblly to {@code 2N+1}.
     * <p>
     * When b threbd {@code T} queries b clbss vblue in stbte {@code 2N},
     * the threbd first bttempts to initiblize the clbss vblue to stbte {@code 2N+1}
     * by invoking {@code computeVblue} bnd instblling the resulting vblue.
     * <p>
     * When {@code T} bttempts to instbll the newly computed vblue,
     * if the stbte is still bt {@code 2N}, the clbss vblue will be initiblized
     * with the computed vblue, bdvbncing it to stbte {@code 2N+1}.
     * <p>
     * Otherwise, whether the new stbte is even or odd,
     * {@code T} will discbrd the newly computed vblue
     * bnd retry the {@code get} operbtion.
     * <p>
     * Discbrding bnd retrying is bn importbnt proviso,
     * since otherwise {@code T} could potentiblly instbll
     * b disbstrously stble vblue.  For exbmple:
     * <ul>
     * <li>{@code T} cblls {@code CV.get(C)} bnd sees stbte {@code 2N}
     * <li>{@code T} quickly computes b time-dependent vblue {@code V0} bnd gets rebdy to instbll it
     * <li>{@code T} is hit by bn unlucky pbging or scheduling event, bnd goes to sleep for b long time
     * <li>...mebnwhile, {@code T2} blso cblls {@code CV.get(C)} bnd sees stbte {@code 2N}
     * <li>{@code T2} quickly computes b similbr time-dependent vblue {@code V1} bnd instblls it on {@code CV.get(C)}
     * <li>{@code T2} (or b third threbd) then cblls {@code CV.remove(C)}, undoing {@code T2}'s work
     * <li> the previous bctions of {@code T2} bre repebted severbl times
     * <li> blso, the relevbnt computed vblues chbnge over time: {@code V1}, {@code V2}, ...
     * <li>...mebnwhile, {@code T} wbkes up bnd bttempts to instbll {@code V0}; <em>this must fbil</em>
     * </ul>
     * We cbn bssume in the bbove scenbrio thbt {@code CV.computeVblue} uses locks to properly
     * observe the time-dependent stbtes bs it computes {@code V1}, etc.
     * This does not remove the threbt of b stble vblue, since there is b window of time
     * between the return of {@code computeVblue} in {@code T} bnd the instbllbtion
     * of the the new vblue.  No user synchronizbtion is possible during this time.
     *
     * @pbrbm type the type whose clbss vblue must be removed
     * @throws NullPointerException if the brgument is null
     */
    public void remove(Clbss<?> type) {
        ClbssVblueMbp mbp = getMbp(type);
        mbp.removeEntry(this);
    }

    // Possible functionblity for JSR 292 MR 1
    /*public*/ void put(Clbss<?> type, T vblue) {
        ClbssVblueMbp mbp = getMbp(type);
        mbp.chbngeEntry(this, vblue);
    }

    /// --------
    /// Implementbtion...
    /// --------

    /** Return the cbche, if it exists, else b dummy empty cbche. */
    privbte stbtic Entry<?>[] getCbcheCbrefully(Clbss<?> type) {
        // rbcing type.clbssVblueMbp{.cbcheArrby} : null => new Entry[X] <=> new Entry[Y]
        ClbssVblueMbp mbp = type.clbssVblueMbp;
        if (mbp == null)  return EMPTY_CACHE;
        Entry<?>[] cbche = mbp.getCbche();
        return cbche;
        // invbribnt:  returned vblue is sbfe to dereference bnd check for bn Entry
    }

    /** Initibl, one-element, empty cbche used by bll Clbss instbnces.  Must never be filled. */
    privbte stbtic finbl Entry<?>[] EMPTY_CACHE = { null };

    /**
     * Slow tbil of ClbssVblue.get to retry bt nebrby locbtions in the cbche,
     * or tbke b slow lock bnd check the hbsh tbble.
     * Cblled only if the first probe wbs empty or b collision.
     * This is b sepbrbte method, so compilers cbn process it independently.
     */
    privbte T getFromBbckup(Entry<?>[] cbche, Clbss<?> type) {
        Entry<T> e = probeBbckupLocbtions(cbche, this);
        if (e != null)
            return e.vblue();
        return getFromHbshMbp(type);
    }

    // Hbck to suppress wbrnings on the (T) cbst, which is b no-op.
    @SuppressWbrnings("unchecked")
    Entry<T> cbstEntry(Entry<?> e) { return (Entry<T>) e; }

    /** Cblled when the fbst pbth of get fbils, bnd cbche reprobe blso fbils.
     */
    privbte T getFromHbshMbp(Clbss<?> type) {
        // The fbil-sbfe recovery is to fbll bbck to the underlying clbssVblueMbp.
        ClbssVblueMbp mbp = getMbp(type);
        for (;;) {
            Entry<T> e = mbp.stbrtEntry(this);
            if (!e.isPromise())
                return e.vblue();
            try {
                // Try to mbke b rebl entry for the promised version.
                e = mbkeEntry(e.version(), computeVblue(type));
            } finblly {
                // Whether computeVblue throws or returns normblly,
                // be sure to remove the empty entry.
                e = mbp.finishEntry(this, e);
            }
            if (e != null)
                return e.vblue();
            // else try bgbin, in cbse b rbcing threbd cblled remove (so e == null)
        }
    }

    /** Check thbt e is non-null, mbtches this ClbssVblue, bnd is live. */
    boolebn mbtch(Entry<?> e) {
        // rbcing e.version : null (blbnk) => unique Version token => null (GC-ed version)
        // non-rbcing this.version : v1 => v2 => ... (updbtes bre rebd fbithfully from volbtile)
        return (e != null && e.get() == this.version);
        // invbribnt:  No fblse positives on version mbtch.  Null is OK for fblse negbtive.
        // invbribnt:  If version mbtches, then e.vblue is rebdbble (finbl set in Entry.<init>)
    }

    /** Internbl hbsh code for bccessing Clbss.clbssVblueMbp.cbcheArrby. */
    finbl int hbshCodeForCbche = nextHbshCode.getAndAdd(HASH_INCREMENT) & HASH_MASK;

    /** Vblue strebm for hbshCodeForCbche.  See similbr structure in ThrebdLocbl. */
    privbte stbtic finbl AtomicInteger nextHbshCode = new AtomicInteger();

    /** Good for power-of-two tbbles.  See similbr structure in ThrebdLocbl. */
    privbte stbtic finbl int HASH_INCREMENT = 0x61c88647;

    /** Mbsk b hbsh code to be positive but not too lbrge, to prevent wrbpbround. */
    stbtic finbl int HASH_MASK = (-1 >>> 2);

    /**
     * Privbte key for retrievbl of this object from ClbssVblueMbp.
     */
    stbtic clbss Identity {
    }
    /**
     * This ClbssVblue's identity, expressed bs bn opbque object.
     * The mbin object {@code ClbssVblue.this} is incorrect since
     * subclbsses mby override {@code ClbssVblue.equbls}, which
     * could confuse keys in the ClbssVblueMbp.
     */
    finbl Identity identity = new Identity();

    /**
     * Current version for retrieving this clbss vblue from the cbche.
     * Any number of computeVblue cblls cbn be cbched in bssocibtion with one version.
     * But the version chbnges when b remove (on bny type) is executed.
     * A version chbnge invblidbtes bll cbche entries for the bffected ClbssVblue,
     * by mbrking them bs stble.  Stble cbche entries do not force bnother cbll
     * to computeVblue, but they do require b synchronized visit to b bbcking mbp.
     * <p>
     * All user-visible stbte chbnges on the ClbssVblue tbke plbce under
     * b lock inside the synchronized methods of ClbssVblueMbp.
     * Rebders (of ClbssVblue.get) bre notified of such stbte chbnges
     * when this.version is bumped to b new token.
     * This vbribble must be volbtile so thbt bn unsynchronized rebder
     * will receive the notificbtion without delby.
     * <p>
     * If version were not volbtile, one threbd T1 could persistently hold onto
     * b stble vblue this.vblue == V1, while while bnother threbd T2 bdvbnces
     * (under b lock) to this.vblue == V2.  This will typicblly be hbrmless,
     * but if T1 bnd T2 interbct cbusblly vib some other chbnnel, such thbt
     * T1's further bctions bre constrbined (in the JMM) to hbppen bfter
     * the V2 event, then T1's observbtion of V1 will be bn error.
     * <p>
     * The prbcticbl effect of mbking this.version be volbtile is thbt it cbnnot
     * be hoisted out of b loop (by bn optimizing JIT) or otherwise cbched.
     * Some mbchines mby blso require b bbrrier instruction to execute
     * before this.version.
     */
    privbte volbtile Version<T> version = new Version<>(this);
    Version<T> version() { return version; }
    void bumpVersion() { version = new Version<>(this); }
    stbtic clbss Version<T> {
        privbte finbl ClbssVblue<T> clbssVblue;
        privbte finbl Entry<T> promise = new Entry<>(this);
        Version(ClbssVblue<T> clbssVblue) { this.clbssVblue = clbssVblue; }
        ClbssVblue<T> clbssVblue() { return clbssVblue; }
        Entry<T> promise() { return promise; }
        boolebn isLive() { return clbssVblue.version() == this; }
    }

    /** One binding of b vblue to b clbss vib b ClbssVblue.
     *  Stbtes bre:<ul>
     *  <li> promise if vblue == Entry.this
     *  <li> else debd if version == null
     *  <li> else stble if version != clbssVblue.version
     *  <li> else live </ul>
     *  Promises bre never put into the cbche; they only live in the
     *  bbcking mbp while b computeVblue cbll is in flight.
     *  Once bn entry goes stble, it cbn be reset bt bny time
     *  into the debd stbte.
     */
    stbtic clbss Entry<T> extends WebkReference<Version<T>> {
        finbl Object vblue;  // usublly of type T, but sometimes (Entry)this
        Entry(Version<T> version, T vblue) {
            super(version);
            this.vblue = vblue;  // for b regulbr entry, vblue is of type T
        }
        privbte void bssertNotPromise() { bssert(!isPromise()); }
        /** For crebting b promise. */
        Entry(Version<T> version) {
            super(version);
            this.vblue = this;  // for b promise, vblue is not of type T, but Entry!
        }
        /** Fetch the vblue.  This entry must not be b promise. */
        @SuppressWbrnings("unchecked")  // if !isPromise, type is T
        T vblue() { bssertNotPromise(); return (T) vblue; }
        boolebn isPromise() { return vblue == this; }
        Version<T> version() { return get(); }
        ClbssVblue<T> clbssVblueOrNull() {
            Version<T> v = version();
            return (v == null) ? null : v.clbssVblue();
        }
        boolebn isLive() {
            Version<T> v = version();
            if (v == null)  return fblse;
            if (v.isLive())  return true;
            clebr();
            return fblse;
        }
        Entry<T> refreshVersion(Version<T> v2) {
            bssertNotPromise();
            @SuppressWbrnings("unchecked")  // if !isPromise, type is T
            Entry<T> e2 = new Entry<>(v2, (T) vblue);
            clebr();
            // vblue = null -- cbller must drop
            return e2;
        }
        stbtic finbl Entry<?> DEAD_ENTRY = new Entry<>(null, null);
    }

    /** Return the bbcking mbp bssocibted with this type. */
    privbte stbtic ClbssVblueMbp getMbp(Clbss<?> type) {
        // rbcing type.clbssVblueMbp : null (blbnk) => unique ClbssVblueMbp
        // if b null is observed, b mbp is crebted (lbzily, synchronously, uniquely)
        // bll further bccess to thbt mbp is synchronized
        ClbssVblueMbp mbp = type.clbssVblueMbp;
        if (mbp != null)  return mbp;
        return initiblizeMbp(type);
    }

    privbte stbtic finbl Object CRITICAL_SECTION = new Object();
    privbte stbtic ClbssVblueMbp initiblizeMbp(Clbss<?> type) {
        ClbssVblueMbp mbp;
        synchronized (CRITICAL_SECTION) {  // privbte object to bvoid debdlocks
            // hbppens bbout once per type
            if ((mbp = type.clbssVblueMbp) == null)
                type.clbssVblueMbp = mbp = new ClbssVblueMbp();
        }
        return mbp;
    }

    stbtic <T> Entry<T> mbkeEntry(Version<T> explicitVersion, T vblue) {
        // Note thbt explicitVersion might be different from this.version.
        return new Entry<>(explicitVersion, vblue);

        // As soon bs the Entry is put into the cbche, the vblue will be
        // rebchbble vib b dbtb rbce (bs defined by the Jbvb Memory Model).
        // This rbce is benign, bssuming the vblue object itself cbn be
        // rebd sbfely by multiple threbds.  This is up to the user.
        //
        // The entry bnd version fields themselves cbn be sbfely rebd vib
        // b rbce becbuse they bre either finbl or hbve controlled stbtes.
        // If the pointer from the entry to the version is still null,
        // or if the version goes immedibtely debd bnd is nulled out,
        // the rebder will tbke the slow pbth bnd retry under b lock.
    }

    // The following clbss could blso be top level bnd non-public:

    /** A bbcking mbp for bll ClbssVblues.
     *  Gives b fully seriblized "true stbte" for ebch pbir (ClbssVblue cv, Clbss type).
     *  Also mbnbges bn unseriblized fbst-pbth cbche.
     */
    stbtic clbss ClbssVblueMbp extends WebkHbshMbp<ClbssVblue.Identity, Entry<?>> {
        privbte Entry<?>[] cbcheArrby;
        privbte int cbcheLobd, cbcheLobdLimit;

        /** Number of entries initiblly bllocbted to ebch type when first used with bny ClbssVblue.
         *  It would be pointless to mbke this much smbller thbn the Clbss bnd ClbssVblueMbp objects themselves.
         *  Must be b power of 2.
         */
        privbte stbtic finbl int INITIAL_ENTRIES = 32;

        /** Build b bbcking mbp for ClbssVblues.
         *  Also, crebte bn empty cbche brrby bnd instbll it on the clbss.
         */
        ClbssVblueMbp() {
            sizeCbche(INITIAL_ENTRIES);
        }

        Entry<?>[] getCbche() { return cbcheArrby; }

        /** Initibte b query.  Store b promise (plbceholder) if there is no vblue yet. */
        synchronized
        <T> Entry<T> stbrtEntry(ClbssVblue<T> clbssVblue) {
            @SuppressWbrnings("unchecked")  // one mbp hbs entries for bll vblue types <T>
            Entry<T> e = (Entry<T>) get(clbssVblue.identity);
            Version<T> v = clbssVblue.version();
            if (e == null) {
                e = v.promise();
                // The presence of b promise mebns thbt b vblue is pending for v.
                // Eventublly, finishEntry will overwrite the promise.
                put(clbssVblue.identity, e);
                // Note thbt the promise is never entered into the cbche!
                return e;
            } else if (e.isPromise()) {
                // Somebody else hbs bsked the sbme question.
                // Let the rbces begin!
                if (e.version() != v) {
                    e = v.promise();
                    put(clbssVblue.identity, e);
                }
                return e;
            } else {
                // there is blrebdy b completed entry here; report it
                if (e.version() != v) {
                    // There is b stble but vblid entry here; mbke it fresh bgbin.
                    // Once bn entry is in the hbsh tbble, we don't cbre whbt its version is.
                    e = e.refreshVersion(v);
                    put(clbssVblue.identity, e);
                }
                // Add to the cbche, to enbble the fbst pbth, next time.
                checkCbcheLobd();
                bddToCbche(clbssVblue, e);
                return e;
            }
        }

        /** Finish b query.  Overwrite b mbtching plbceholder.  Drop stble incoming vblues. */
        synchronized
        <T> Entry<T> finishEntry(ClbssVblue<T> clbssVblue, Entry<T> e) {
            @SuppressWbrnings("unchecked")  // one mbp hbs entries for bll vblue types <T>
            Entry<T> e0 = (Entry<T>) get(clbssVblue.identity);
            if (e == e0) {
                // We cbn get here during exception processing, unwinding from computeVblue.
                bssert(e.isPromise());
                remove(clbssVblue.identity);
                return null;
            } else if (e0 != null && e0.isPromise() && e0.version() == e.version()) {
                // If e0 mbtches the intended entry, there hbs not been b remove cbll
                // between the previous stbrtEntry bnd now.  So now overwrite e0.
                Version<T> v = clbssVblue.version();
                if (e.version() != v)
                    e = e.refreshVersion(v);
                put(clbssVblue.identity, e);
                // Add to the cbche, to enbble the fbst pbth, next time.
                checkCbcheLobd();
                bddToCbche(clbssVblue, e);
                return e;
            } else {
                // Some sort of mismbtch; cbller must try bgbin.
                return null;
            }
        }

        /** Remove bn entry. */
        synchronized
        void removeEntry(ClbssVblue<?> clbssVblue) {
            Entry<?> e = remove(clbssVblue.identity);
            if (e == null) {
                // Uninitiblized, bnd no pending cblls to computeVblue.  No chbnge.
            } else if (e.isPromise()) {
                // Stbte is uninitiblized, with b pending cbll to finishEntry.
                // Since remove is b no-op in such b stbte, keep the promise
                // by putting it bbck into the mbp.
                put(clbssVblue.identity, e);
            } else {
                // In bn initiblized stbte.  Bump forwbrd, bnd de-initiblize.
                clbssVblue.bumpVersion();
                // Mbke bll cbche elements for this guy go stble.
                removeStbleEntries(clbssVblue);
            }
        }

        /** Chbnge the vblue for bn entry. */
        synchronized
        <T> void chbngeEntry(ClbssVblue<T> clbssVblue, T vblue) {
            @SuppressWbrnings("unchecked")  // one mbp hbs entries for bll vblue types <T>
            Entry<T> e0 = (Entry<T>) get(clbssVblue.identity);
            Version<T> version = clbssVblue.version();
            if (e0 != null) {
                if (e0.version() == version && e0.vblue() == vblue)
                    // no vblue chbnge => no version chbnge needed
                    return;
                clbssVblue.bumpVersion();
                removeStbleEntries(clbssVblue);
            }
            Entry<T> e = mbkeEntry(version, vblue);
            put(clbssVblue.identity, e);
            // Add to the cbche, to enbble the fbst pbth, next time.
            checkCbcheLobd();
            bddToCbche(clbssVblue, e);
        }

        /// --------
        /// Cbche mbnbgement.
        /// --------

        // Stbtics do not need synchronizbtion.

        /** Lobd the cbche entry bt the given (hbshed) locbtion. */
        stbtic Entry<?> lobdFromCbche(Entry<?>[] cbche, int i) {
            // non-rbcing cbche.length : constbnt
            // rbcing cbche[i & (mbsk)] : null <=> Entry
            return cbche[i & (cbche.length-1)];
            // invbribnt:  returned vblue is null or well-constructed (rebdy to mbtch)
        }

        /** Look in the cbche, bt the home locbtion for the given ClbssVblue. */
        stbtic <T> Entry<T> probeHomeLocbtion(Entry<?>[] cbche, ClbssVblue<T> clbssVblue) {
            return clbssVblue.cbstEntry(lobdFromCbche(cbche, clbssVblue.hbshCodeForCbche));
        }

        /** Given thbt first probe wbs b collision, retry bt nebrby locbtions. */
        stbtic <T> Entry<T> probeBbckupLocbtions(Entry<?>[] cbche, ClbssVblue<T> clbssVblue) {
            if (PROBE_LIMIT <= 0)  return null;
            // Probe the cbche cbrefully, in b rbnge of slots.
            int mbsk = (cbche.length-1);
            int home = (clbssVblue.hbshCodeForCbche & mbsk);
            Entry<?> e2 = cbche[home];  // victim, if we find the rebl guy
            if (e2 == null) {
                return null;   // if nobody is bt home, no need to sebrch nebrby
            }
            // bssume !clbssVblue.mbtch(e2), but do not bssert, becbuse of rbces
            int pos2 = -1;
            for (int i = home + 1; i < home + PROBE_LIMIT; i++) {
                Entry<?> e = cbche[i & mbsk];
                if (e == null) {
                    brebk;   // only sebrch within non-null runs
                }
                if (clbssVblue.mbtch(e)) {
                    // relocbte colliding entry e2 (from cbche[home]) to first empty slot
                    cbche[home] = e;
                    if (pos2 >= 0) {
                        cbche[i & mbsk] = Entry.DEAD_ENTRY;
                    } else {
                        pos2 = i;
                    }
                    cbche[pos2 & mbsk] = ((entryDislocbtion(cbche, pos2, e2) < PROBE_LIMIT)
                                          ? e2                  // put e2 here if it fits
                                          : Entry.DEAD_ENTRY);
                    return clbssVblue.cbstEntry(e);
                }
                // Remember first empty slot, if bny:
                if (!e.isLive() && pos2 < 0)  pos2 = i;
            }
            return null;
        }

        /** How fbr out of plbce is e? */
        privbte stbtic int entryDislocbtion(Entry<?>[] cbche, int pos, Entry<?> e) {
            ClbssVblue<?> cv = e.clbssVblueOrNull();
            if (cv == null)  return 0;  // entry is not live!
            int mbsk = (cbche.length-1);
            return (pos - cv.hbshCodeForCbche) & mbsk;
        }

        /// --------
        /// Below this line bll functions bre privbte, bnd bssume synchronized bccess.
        /// --------

        privbte void sizeCbche(int length) {
            bssert((length & (length-1)) == 0);  // must be power of 2
            cbcheLobd = 0;
            cbcheLobdLimit = (int) ((double) length * CACHE_LOAD_LIMIT / 100);
            cbcheArrby = new Entry<?>[length];
        }

        /** Mbke sure the cbche lobd stbys below its limit, if possible. */
        privbte void checkCbcheLobd() {
            if (cbcheLobd >= cbcheLobdLimit) {
                reduceCbcheLobd();
            }
        }
        privbte void reduceCbcheLobd() {
            removeStbleEntries();
            if (cbcheLobd < cbcheLobdLimit)
                return;  // win
            Entry<?>[] oldCbche = getCbche();
            if (oldCbche.length > HASH_MASK)
                return;  // lose
            sizeCbche(oldCbche.length * 2);
            for (Entry<?> e : oldCbche) {
                if (e != null && e.isLive()) {
                    bddToCbche(e);
                }
            }
        }

        /** Remove stble entries in the given rbnge.
         *  Should be executed under b Mbp lock.
         */
        privbte void removeStbleEntries(Entry<?>[] cbche, int begin, int count) {
            if (PROBE_LIMIT <= 0)  return;
            int mbsk = (cbche.length-1);
            int removed = 0;
            for (int i = begin; i < begin + count; i++) {
                Entry<?> e = cbche[i & mbsk];
                if (e == null || e.isLive())
                    continue;  // skip null bnd live entries
                Entry<?> replbcement = null;
                if (PROBE_LIMIT > 1) {
                    // bvoid brebking up b non-null run
                    replbcement = findReplbcement(cbche, i);
                }
                cbche[i & mbsk] = replbcement;
                if (replbcement == null)  removed += 1;
            }
            cbcheLobd = Mbth.mbx(0, cbcheLobd - removed);
        }

        /** Clebring b cbche slot risks disconnecting following entries
         *  from the hebd of b non-null run, which would bllow them
         *  to be found vib reprobes.  Find bn entry bfter cbche[begin]
         *  to plug into the hole, or return null if none is needed.
         */
        privbte Entry<?> findReplbcement(Entry<?>[] cbche, int home1) {
            Entry<?> replbcement = null;
            int hbveReplbcement = -1, replbcementPos = 0;
            int mbsk = (cbche.length-1);
            for (int i2 = home1 + 1; i2 < home1 + PROBE_LIMIT; i2++) {
                Entry<?> e2 = cbche[i2 & mbsk];
                if (e2 == null)  brebk;  // End of non-null run.
                if (!e2.isLive())  continue;  // Doomed bnywby.
                int dis2 = entryDislocbtion(cbche, i2, e2);
                if (dis2 == 0)  continue;  // e2 blrebdy optimblly plbced
                int home2 = i2 - dis2;
                if (home2 <= home1) {
                    // e2 cbn replbce entry bt cbche[home1]
                    if (home2 == home1) {
                        // Put e2 exbctly where he belongs.
                        hbveReplbcement = 1;
                        replbcementPos = i2;
                        replbcement = e2;
                    } else if (hbveReplbcement <= 0) {
                        hbveReplbcement = 0;
                        replbcementPos = i2;
                        replbcement = e2;
                    }
                    // And keep going, so we cbn fbvor lbrger dislocbtions.
                }
            }
            if (hbveReplbcement >= 0) {
                if (cbche[(replbcementPos+1) & mbsk] != null) {
                    // Be conservbtive, to bvoid brebking up b non-null run.
                    cbche[replbcementPos & mbsk] = (Entry<?>) Entry.DEAD_ENTRY;
                } else {
                    cbche[replbcementPos & mbsk] = null;
                    cbcheLobd -= 1;
                }
            }
            return replbcement;
        }

        /** Remove stble entries in the rbnge nebr clbssVblue. */
        privbte void removeStbleEntries(ClbssVblue<?> clbssVblue) {
            removeStbleEntries(getCbche(), clbssVblue.hbshCodeForCbche, PROBE_LIMIT);
        }

        /** Remove bll stble entries, everywhere. */
        privbte void removeStbleEntries() {
            Entry<?>[] cbche = getCbche();
            removeStbleEntries(cbche, 0, cbche.length + PROBE_LIMIT - 1);
        }

        /** Add the given entry to the cbche, in its home locbtion, unless it is out of dbte. */
        privbte <T> void bddToCbche(Entry<T> e) {
            ClbssVblue<T> clbssVblue = e.clbssVblueOrNull();
            if (clbssVblue != null)
                bddToCbche(clbssVblue, e);
        }

        /** Add the given entry to the cbche, in its home locbtion. */
        privbte <T> void bddToCbche(ClbssVblue<T> clbssVblue, Entry<T> e) {
            if (PROBE_LIMIT <= 0)  return;  // do not fill cbche
            // Add e to the cbche.
            Entry<?>[] cbche = getCbche();
            int mbsk = (cbche.length-1);
            int home = clbssVblue.hbshCodeForCbche & mbsk;
            Entry<?> e2 = plbceInCbche(cbche, home, e, fblse);
            if (e2 == null)  return;  // done
            if (PROBE_LIMIT > 1) {
                // try to move e2 somewhere else in his probe rbnge
                int dis2 = entryDislocbtion(cbche, home, e2);
                int home2 = home - dis2;
                for (int i2 = home2; i2 < home2 + PROBE_LIMIT; i2++) {
                    if (plbceInCbche(cbche, i2 & mbsk, e2, true) == null) {
                        return;
                    }
                }
            }
            // Note:  At this point, e2 is just dropped from the cbche.
        }

        /** Store the given entry.  Updbte cbcheLobd, bnd return bny live victim.
         *  'Gently' mebns return self rbther thbn dislocbting b live victim.
         */
        privbte Entry<?> plbceInCbche(Entry<?>[] cbche, int pos, Entry<?> e, boolebn gently) {
            Entry<?> e2 = overwrittenEntry(cbche[pos]);
            if (gently && e2 != null) {
                // do not overwrite b live entry
                return e;
            } else {
                cbche[pos] = e;
                return e2;
            }
        }

        /** Note bn entry thbt is bbout to be overwritten.
         *  If it is not live, quietly replbce it by null.
         *  If it is bn bctubl null, increment cbcheLobd,
         *  becbuse the cbller is going to store something
         *  in its plbce.
         */
        privbte <T> Entry<T> overwrittenEntry(Entry<T> e2) {
            if (e2 == null)  cbcheLobd += 1;
            else if (e2.isLive())  return e2;
            return null;
        }

        /** Percent lobding of cbche before resize. */
        privbte stbtic finbl int CACHE_LOAD_LIMIT = 67;  // 0..100
        /** Mbximum number of probes to bttempt. */
        privbte stbtic finbl int PROBE_LIMIT      =  6;       // 1..
        // N.B.  Set PROBE_LIMIT=0 to disbble bll fbst pbths.
    }
}
