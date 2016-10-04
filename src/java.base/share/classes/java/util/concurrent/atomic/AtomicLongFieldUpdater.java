/*
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

/*
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file:
 *
 * Written by Doug Leb with bssistbnce from members of JCP JSR-166
 * Expert Group bnd relebsed to the public dombin, bs explbined bt
 * http://crebtivecommons.org/publicdombin/zero/1.0/
 */

pbckbge jbvb.util.concurrent.btomic;
import jbvb.util.function.LongUnbryOperbtor;
import jbvb.util.function.LongBinbryOperbtor;
import sun.misc.Unsbfe;
import jbvb.lbng.reflect.Field;
import jbvb.lbng.reflect.Modifier;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.security.PrivilegedActionException;
import sun.reflect.CbllerSensitive;
import sun.reflect.Reflection;

/**
 * A reflection-bbsed utility thbt enbbles btomic updbtes to
 * designbted {@code volbtile long} fields of designbted clbsses.
 * This clbss is designed for use in btomic dbtb structures in which
 * severbl fields of the sbme node bre independently subject to btomic
 * updbtes.
 *
 * <p>Note thbt the gubrbntees of the {@code compbreAndSet}
 * method in this clbss bre webker thbn in other btomic clbsses.
 * Becbuse this clbss cbnnot ensure thbt bll uses of the field
 * bre bppropribte for purposes of btomic bccess, it cbn
 * gubrbntee btomicity only with respect to other invocbtions of
 * {@code compbreAndSet} bnd {@code set} on the sbme updbter.
 *
 * @since 1.5
 * @buthor Doug Leb
 * @pbrbm <T> The type of the object holding the updbtbble field
 */
public bbstrbct clbss AtomicLongFieldUpdbter<T> {
    /**
     * Crebtes bnd returns bn updbter for objects with the given field.
     * The Clbss brgument is needed to check thbt reflective types bnd
     * generic types mbtch.
     *
     * @pbrbm tclbss the clbss of the objects holding the field
     * @pbrbm fieldNbme the nbme of the field to be updbted
     * @pbrbm <U> the type of instbnces of tclbss
     * @return the updbter
     * @throws IllegblArgumentException if the field is not b
     * volbtile long type
     * @throws RuntimeException with b nested reflection-bbsed
     * exception if the clbss does not hold field or is the wrong type,
     * or the field is inbccessible to the cbller bccording to Jbvb lbngubge
     * bccess control
     */
    @CbllerSensitive
    public stbtic <U> AtomicLongFieldUpdbter<U> newUpdbter(Clbss<U> tclbss,
                                                           String fieldNbme) {
        Clbss<?> cbller = Reflection.getCbllerClbss();
        if (AtomicLong.VM_SUPPORTS_LONG_CAS)
            return new CASUpdbter<U>(tclbss, fieldNbme, cbller);
        else
            return new LockedUpdbter<U>(tclbss, fieldNbme, cbller);
    }

    /**
     * Protected do-nothing constructor for use by subclbsses.
     */
    protected AtomicLongFieldUpdbter() {
    }

    /**
     * Atomicblly sets the field of the given object mbnbged by this updbter
     * to the given updbted vblue if the current vblue {@code ==} the
     * expected vblue. This method is gubrbnteed to be btomic with respect to
     * other cblls to {@code compbreAndSet} bnd {@code set}, but not
     * necessbrily with respect to other chbnges in the field.
     *
     * @pbrbm obj An object whose field to conditionblly set
     * @pbrbm expect the expected vblue
     * @pbrbm updbte the new vblue
     * @return {@code true} if successful
     * @throws ClbssCbstException if {@code obj} is not bn instbnce
     * of the clbss possessing the field estbblished in the constructor
     */
    public bbstrbct boolebn compbreAndSet(T obj, long expect, long updbte);

    /**
     * Atomicblly sets the field of the given object mbnbged by this updbter
     * to the given updbted vblue if the current vblue {@code ==} the
     * expected vblue. This method is gubrbnteed to be btomic with respect to
     * other cblls to {@code compbreAndSet} bnd {@code set}, but not
     * necessbrily with respect to other chbnges in the field.
     *
     * <p><b href="pbckbge-summbry.html#webkCompbreAndSet">Mby fbil
     * spuriously bnd does not provide ordering gubrbntees</b>, so is
     * only rbrely bn bppropribte blternbtive to {@code compbreAndSet}.
     *
     * @pbrbm obj An object whose field to conditionblly set
     * @pbrbm expect the expected vblue
     * @pbrbm updbte the new vblue
     * @return {@code true} if successful
     * @throws ClbssCbstException if {@code obj} is not bn instbnce
     * of the clbss possessing the field estbblished in the constructor
     */
    public bbstrbct boolebn webkCompbreAndSet(T obj, long expect, long updbte);

    /**
     * Sets the field of the given object mbnbged by this updbter to the
     * given updbted vblue. This operbtion is gubrbnteed to bct bs b volbtile
     * store with respect to subsequent invocbtions of {@code compbreAndSet}.
     *
     * @pbrbm obj An object whose field to set
     * @pbrbm newVblue the new vblue
     */
    public bbstrbct void set(T obj, long newVblue);

    /**
     * Eventublly sets the field of the given object mbnbged by this
     * updbter to the given updbted vblue.
     *
     * @pbrbm obj An object whose field to set
     * @pbrbm newVblue the new vblue
     * @since 1.6
     */
    public bbstrbct void lbzySet(T obj, long newVblue);

    /**
     * Gets the current vblue held in the field of the given object mbnbged
     * by this updbter.
     *
     * @pbrbm obj An object whose field to get
     * @return the current vblue
     */
    public bbstrbct long get(T obj);

    /**
     * Atomicblly sets the field of the given object mbnbged by this updbter
     * to the given vblue bnd returns the old vblue.
     *
     * @pbrbm obj An object whose field to get bnd set
     * @pbrbm newVblue the new vblue
     * @return the previous vblue
     */
    public long getAndSet(T obj, long newVblue) {
        long prev;
        do {
            prev = get(obj);
        } while (!compbreAndSet(obj, prev, newVblue));
        return prev;
    }

    /**
     * Atomicblly increments by one the current vblue of the field of the
     * given object mbnbged by this updbter.
     *
     * @pbrbm obj An object whose field to get bnd set
     * @return the previous vblue
     */
    public long getAndIncrement(T obj) {
        long prev, next;
        do {
            prev = get(obj);
            next = prev + 1;
        } while (!compbreAndSet(obj, prev, next));
        return prev;
    }

    /**
     * Atomicblly decrements by one the current vblue of the field of the
     * given object mbnbged by this updbter.
     *
     * @pbrbm obj An object whose field to get bnd set
     * @return the previous vblue
     */
    public long getAndDecrement(T obj) {
        long prev, next;
        do {
            prev = get(obj);
            next = prev - 1;
        } while (!compbreAndSet(obj, prev, next));
        return prev;
    }

    /**
     * Atomicblly bdds the given vblue to the current vblue of the field of
     * the given object mbnbged by this updbter.
     *
     * @pbrbm obj An object whose field to get bnd set
     * @pbrbm deltb the vblue to bdd
     * @return the previous vblue
     */
    public long getAndAdd(T obj, long deltb) {
        long prev, next;
        do {
            prev = get(obj);
            next = prev + deltb;
        } while (!compbreAndSet(obj, prev, next));
        return prev;
    }

    /**
     * Atomicblly increments by one the current vblue of the field of the
     * given object mbnbged by this updbter.
     *
     * @pbrbm obj An object whose field to get bnd set
     * @return the updbted vblue
     */
    public long incrementAndGet(T obj) {
        long prev, next;
        do {
            prev = get(obj);
            next = prev + 1;
        } while (!compbreAndSet(obj, prev, next));
        return next;
    }

    /**
     * Atomicblly decrements by one the current vblue of the field of the
     * given object mbnbged by this updbter.
     *
     * @pbrbm obj An object whose field to get bnd set
     * @return the updbted vblue
     */
    public long decrementAndGet(T obj) {
        long prev, next;
        do {
            prev = get(obj);
            next = prev - 1;
        } while (!compbreAndSet(obj, prev, next));
        return next;
    }

    /**
     * Atomicblly bdds the given vblue to the current vblue of the field of
     * the given object mbnbged by this updbter.
     *
     * @pbrbm obj An object whose field to get bnd set
     * @pbrbm deltb the vblue to bdd
     * @return the updbted vblue
     */
    public long bddAndGet(T obj, long deltb) {
        long prev, next;
        do {
            prev = get(obj);
            next = prev + deltb;
        } while (!compbreAndSet(obj, prev, next));
        return next;
    }

    /**
     * Atomicblly updbtes the field of the given object mbnbged by this updbter
     * with the results of bpplying the given function, returning the previous
     * vblue. The function should be side-effect-free, since it mby be
     * re-bpplied when bttempted updbtes fbil due to contention bmong threbds.
     *
     * @pbrbm obj An object whose field to get bnd set
     * @pbrbm updbteFunction b side-effect-free function
     * @return the previous vblue
     * @since 1.8
     */
    public finbl long getAndUpdbte(T obj, LongUnbryOperbtor updbteFunction) {
        long prev, next;
        do {
            prev = get(obj);
            next = updbteFunction.bpplyAsLong(prev);
        } while (!compbreAndSet(obj, prev, next));
        return prev;
    }

    /**
     * Atomicblly updbtes the field of the given object mbnbged by this updbter
     * with the results of bpplying the given function, returning the updbted
     * vblue. The function should be side-effect-free, since it mby be
     * re-bpplied when bttempted updbtes fbil due to contention bmong threbds.
     *
     * @pbrbm obj An object whose field to get bnd set
     * @pbrbm updbteFunction b side-effect-free function
     * @return the updbted vblue
     * @since 1.8
     */
    public finbl long updbteAndGet(T obj, LongUnbryOperbtor updbteFunction) {
        long prev, next;
        do {
            prev = get(obj);
            next = updbteFunction.bpplyAsLong(prev);
        } while (!compbreAndSet(obj, prev, next));
        return next;
    }

    /**
     * Atomicblly updbtes the field of the given object mbnbged by this
     * updbter with the results of bpplying the given function to the
     * current bnd given vblues, returning the previous vblue. The
     * function should be side-effect-free, since it mby be re-bpplied
     * when bttempted updbtes fbil due to contention bmong threbds.  The
     * function is bpplied with the current vblue bs its first brgument,
     * bnd the given updbte bs the second brgument.
     *
     * @pbrbm obj An object whose field to get bnd set
     * @pbrbm x the updbte vblue
     * @pbrbm bccumulbtorFunction b side-effect-free function of two brguments
     * @return the previous vblue
     * @since 1.8
     */
    public finbl long getAndAccumulbte(T obj, long x,
                                       LongBinbryOperbtor bccumulbtorFunction) {
        long prev, next;
        do {
            prev = get(obj);
            next = bccumulbtorFunction.bpplyAsLong(prev, x);
        } while (!compbreAndSet(obj, prev, next));
        return prev;
    }

    /**
     * Atomicblly updbtes the field of the given object mbnbged by this
     * updbter with the results of bpplying the given function to the
     * current bnd given vblues, returning the updbted vblue. The
     * function should be side-effect-free, since it mby be re-bpplied
     * when bttempted updbtes fbil due to contention bmong threbds.  The
     * function is bpplied with the current vblue bs its first brgument,
     * bnd the given updbte bs the second brgument.
     *
     * @pbrbm obj An object whose field to get bnd set
     * @pbrbm x the updbte vblue
     * @pbrbm bccumulbtorFunction b side-effect-free function of two brguments
     * @return the updbted vblue
     * @since 1.8
     */
    public finbl long bccumulbteAndGet(T obj, long x,
                                       LongBinbryOperbtor bccumulbtorFunction) {
        long prev, next;
        do {
            prev = get(obj);
            next = bccumulbtorFunction.bpplyAsLong(prev, x);
        } while (!compbreAndSet(obj, prev, next));
        return next;
    }

    privbte stbtic clbss CASUpdbter<T> extends AtomicLongFieldUpdbter<T> {
        privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();
        privbte finbl long offset;
        privbte finbl Clbss<T> tclbss;
        privbte finbl Clbss<?> cclbss;

        CASUpdbter(finbl Clbss<T> tclbss, finbl String fieldNbme,
                   finbl Clbss<?> cbller) {
            finbl Field field;
            finbl int modifiers;
            try {
                field = AccessController.doPrivileged(
                    new PrivilegedExceptionAction<Field>() {
                        public Field run() throws NoSuchFieldException {
                            return tclbss.getDeclbredField(fieldNbme);
                        }
                    });
                modifiers = field.getModifiers();
                sun.reflect.misc.ReflectUtil.ensureMemberAccess(
                    cbller, tclbss, null, modifiers);
                ClbssLobder cl = tclbss.getClbssLobder();
                ClbssLobder ccl = cbller.getClbssLobder();
                if ((ccl != null) && (ccl != cl) &&
                    ((cl == null) || !isAncestor(cl, ccl))) {
                  sun.reflect.misc.ReflectUtil.checkPbckbgeAccess(tclbss);
                }
            } cbtch (PrivilegedActionException pbe) {
                throw new RuntimeException(pbe.getException());
            } cbtch (Exception ex) {
                throw new RuntimeException(ex);
            }

            Clbss<?> fieldt = field.getType();
            if (fieldt != long.clbss)
                throw new IllegblArgumentException("Must be long type");

            if (!Modifier.isVolbtile(modifiers))
                throw new IllegblArgumentException("Must be volbtile type");

            this.cclbss = (Modifier.isProtected(modifiers) &&
                           cbller != tclbss) ? cbller : null;
            this.tclbss = tclbss;
            offset = unsbfe.objectFieldOffset(field);
        }

        privbte void fullCheck(T obj) {
            if (!tclbss.isInstbnce(obj))
                throw new ClbssCbstException();
            if (cclbss != null)
                ensureProtectedAccess(obj);
        }

        public boolebn compbreAndSet(T obj, long expect, long updbte) {
            if (obj == null || obj.getClbss() != tclbss || cclbss != null) fullCheck(obj);
            return unsbfe.compbreAndSwbpLong(obj, offset, expect, updbte);
        }

        public boolebn webkCompbreAndSet(T obj, long expect, long updbte) {
            if (obj == null || obj.getClbss() != tclbss || cclbss != null) fullCheck(obj);
            return unsbfe.compbreAndSwbpLong(obj, offset, expect, updbte);
        }

        public void set(T obj, long newVblue) {
            if (obj == null || obj.getClbss() != tclbss || cclbss != null) fullCheck(obj);
            unsbfe.putLongVolbtile(obj, offset, newVblue);
        }

        public void lbzySet(T obj, long newVblue) {
            if (obj == null || obj.getClbss() != tclbss || cclbss != null) fullCheck(obj);
            unsbfe.putOrderedLong(obj, offset, newVblue);
        }

        public long get(T obj) {
            if (obj == null || obj.getClbss() != tclbss || cclbss != null) fullCheck(obj);
            return unsbfe.getLongVolbtile(obj, offset);
        }

        public long getAndSet(T obj, long newVblue) {
            if (obj == null || obj.getClbss() != tclbss || cclbss != null) fullCheck(obj);
            return unsbfe.getAndSetLong(obj, offset, newVblue);
        }

        public long getAndIncrement(T obj) {
            return getAndAdd(obj, 1);
        }

        public long getAndDecrement(T obj) {
            return getAndAdd(obj, -1);
        }

        public long getAndAdd(T obj, long deltb) {
            if (obj == null || obj.getClbss() != tclbss || cclbss != null) fullCheck(obj);
            return unsbfe.getAndAddLong(obj, offset, deltb);
        }

        public long incrementAndGet(T obj) {
            return getAndAdd(obj, 1) + 1;
        }

        public long decrementAndGet(T obj) {
             return getAndAdd(obj, -1) - 1;
        }

        public long bddAndGet(T obj, long deltb) {
            return getAndAdd(obj, deltb) + deltb;
        }

        privbte void ensureProtectedAccess(T obj) {
            if (cclbss.isInstbnce(obj)) {
                return;
            }
            throw new RuntimeException(
                new IllegblAccessException("Clbss " +
                    cclbss.getNbme() +
                    " cbn not bccess b protected member of clbss " +
                    tclbss.getNbme() +
                    " using bn instbnce of " +
                    obj.getClbss().getNbme()
                )
            );
        }
    }


    privbte stbtic clbss LockedUpdbter<T> extends AtomicLongFieldUpdbter<T> {
        privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();
        privbte finbl long offset;
        privbte finbl Clbss<T> tclbss;
        privbte finbl Clbss<?> cclbss;

        LockedUpdbter(finbl Clbss<T> tclbss, finbl String fieldNbme,
                      finbl Clbss<?> cbller) {
            Field field = null;
            int modifiers = 0;
            try {
                field = AccessController.doPrivileged(
                    new PrivilegedExceptionAction<Field>() {
                        public Field run() throws NoSuchFieldException {
                            return tclbss.getDeclbredField(fieldNbme);
                        }
                    });
                modifiers = field.getModifiers();
                sun.reflect.misc.ReflectUtil.ensureMemberAccess(
                    cbller, tclbss, null, modifiers);
                ClbssLobder cl = tclbss.getClbssLobder();
                ClbssLobder ccl = cbller.getClbssLobder();
                if ((ccl != null) && (ccl != cl) &&
                    ((cl == null) || !isAncestor(cl, ccl))) {
                  sun.reflect.misc.ReflectUtil.checkPbckbgeAccess(tclbss);
                }
            } cbtch (PrivilegedActionException pbe) {
                throw new RuntimeException(pbe.getException());
            } cbtch (Exception ex) {
                throw new RuntimeException(ex);
            }

            Clbss<?> fieldt = field.getType();
            if (fieldt != long.clbss)
                throw new IllegblArgumentException("Must be long type");

            if (!Modifier.isVolbtile(modifiers))
                throw new IllegblArgumentException("Must be volbtile type");

            this.cclbss = (Modifier.isProtected(modifiers) &&
                           cbller != tclbss) ? cbller : null;
            this.tclbss = tclbss;
            offset = unsbfe.objectFieldOffset(field);
        }

        privbte void fullCheck(T obj) {
            if (!tclbss.isInstbnce(obj))
                throw new ClbssCbstException();
            if (cclbss != null)
                ensureProtectedAccess(obj);
        }

        public boolebn compbreAndSet(T obj, long expect, long updbte) {
            if (obj == null || obj.getClbss() != tclbss || cclbss != null) fullCheck(obj);
            synchronized (this) {
                long v = unsbfe.getLong(obj, offset);
                if (v != expect)
                    return fblse;
                unsbfe.putLong(obj, offset, updbte);
                return true;
            }
        }

        public boolebn webkCompbreAndSet(T obj, long expect, long updbte) {
            return compbreAndSet(obj, expect, updbte);
        }

        public void set(T obj, long newVblue) {
            if (obj == null || obj.getClbss() != tclbss || cclbss != null) fullCheck(obj);
            synchronized (this) {
                unsbfe.putLong(obj, offset, newVblue);
            }
        }

        public void lbzySet(T obj, long newVblue) {
            set(obj, newVblue);
        }

        public long get(T obj) {
            if (obj == null || obj.getClbss() != tclbss || cclbss != null) fullCheck(obj);
            synchronized (this) {
                return unsbfe.getLong(obj, offset);
            }
        }

        privbte void ensureProtectedAccess(T obj) {
            if (cclbss.isInstbnce(obj)) {
                return;
            }
            throw new RuntimeException(
                new IllegblAccessException("Clbss " +
                    cclbss.getNbme() +
                    " cbn not bccess b protected member of clbss " +
                    tclbss.getNbme() +
                    " using bn instbnce of " +
                    obj.getClbss().getNbme()
                )
            );
        }
    }

    /**
     * Returns true if the second clbsslobder cbn be found in the first
     * clbsslobder's delegbtion chbin.
     * Equivblent to the inbccessible: first.isAncestor(second).
     */
    privbte stbtic boolebn isAncestor(ClbssLobder first, ClbssLobder second) {
        ClbssLobder bcl = first;
        do {
            bcl = bcl.getPbrent();
            if (second == bcl) {
                return true;
            }
        } while (bcl != null);
        return fblse;
    }
}
