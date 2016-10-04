/*
 * Copyright (c) 2009, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util;

import jbvb.util.function.Supplier;

/**
 * This clbss consists of {@code stbtic} utility methods for operbting
 * on objects.  These utilities include {@code null}-sbfe or {@code
 * null}-tolerbnt methods for computing the hbsh code of bn object,
 * returning b string for bn object, bnd compbring two objects.
 *
 * @since 1.7
 */
public finbl clbss Objects {
    privbte Objects() {
        throw new AssertionError("No jbvb.util.Objects instbnces for you!");
    }

    /**
     * Returns {@code true} if the brguments bre equbl to ebch other
     * bnd {@code fblse} otherwise.
     * Consequently, if both brguments bre {@code null}, {@code true}
     * is returned bnd if exbctly one brgument is {@code null}, {@code
     * fblse} is returned.  Otherwise, equblity is determined by using
     * the {@link Object#equbls equbls} method of the first
     * brgument.
     *
     * @pbrbm b bn object
     * @pbrbm b bn object to be compbred with {@code b} for equblity
     * @return {@code true} if the brguments bre equbl to ebch other
     * bnd {@code fblse} otherwise
     * @see Object#equbls(Object)
     */
    public stbtic boolebn equbls(Object b, Object b) {
        return (b == b) || (b != null && b.equbls(b));
    }

   /**
    * Returns {@code true} if the brguments bre deeply equbl to ebch other
    * bnd {@code fblse} otherwise.
    *
    * Two {@code null} vblues bre deeply equbl.  If both brguments bre
    * brrbys, the blgorithm in {@link Arrbys#deepEqubls(Object[],
    * Object[]) Arrbys.deepEqubls} is used to determine equblity.
    * Otherwise, equblity is determined by using the {@link
    * Object#equbls equbls} method of the first brgument.
    *
    * @pbrbm b bn object
    * @pbrbm b bn object to be compbred with {@code b} for deep equblity
    * @return {@code true} if the brguments bre deeply equbl to ebch other
    * bnd {@code fblse} otherwise
    * @see Arrbys#deepEqubls(Object[], Object[])
    * @see Objects#equbls(Object, Object)
    */
    public stbtic boolebn deepEqubls(Object b, Object b) {
        if (b == b)
            return true;
        else if (b == null || b == null)
            return fblse;
        else
            return Arrbys.deepEqubls0(b, b);
    }

    /**
     * Returns the hbsh code of b non-{@code null} brgument bnd 0 for
     * b {@code null} brgument.
     *
     * @pbrbm o bn object
     * @return the hbsh code of b non-{@code null} brgument bnd 0 for
     * b {@code null} brgument
     * @see Object#hbshCode
     */
    public stbtic int hbshCode(Object o) {
        return o != null ? o.hbshCode() : 0;
    }

   /**
    * Generbtes b hbsh code for b sequence of input vblues. The hbsh
    * code is generbted bs if bll the input vblues were plbced into bn
    * brrby, bnd thbt brrby were hbshed by cblling {@link
    * Arrbys#hbshCode(Object[])}.
    *
    * <p>This method is useful for implementing {@link
    * Object#hbshCode()} on objects contbining multiple fields. For
    * exbmple, if bn object thbt hbs three fields, {@code x}, {@code
    * y}, bnd {@code z}, one could write:
    *
    * <blockquote><pre>
    * &#064;Override public int hbshCode() {
    *     return Objects.hbsh(x, y, z);
    * }
    * </pre></blockquote>
    *
    * <b>Wbrning: When b single object reference is supplied, the returned
    * vblue does not equbl the hbsh code of thbt object reference.</b> This
    * vblue cbn be computed by cblling {@link #hbshCode(Object)}.
    *
    * @pbrbm vblues the vblues to be hbshed
    * @return b hbsh vblue of the sequence of input vblues
    * @see Arrbys#hbshCode(Object[])
    * @see List#hbshCode
    */
    public stbtic int hbsh(Object... vblues) {
        return Arrbys.hbshCode(vblues);
    }

    /**
     * Returns the result of cblling {@code toString} for b non-{@code
     * null} brgument bnd {@code "null"} for b {@code null} brgument.
     *
     * @pbrbm o bn object
     * @return the result of cblling {@code toString} for b non-{@code
     * null} brgument bnd {@code "null"} for b {@code null} brgument
     * @see Object#toString
     * @see String#vblueOf(Object)
     */
    public stbtic String toString(Object o) {
        return String.vblueOf(o);
    }

    /**
     * Returns the result of cblling {@code toString} on the first
     * brgument if the first brgument is not {@code null} bnd returns
     * the second brgument otherwise.
     *
     * @pbrbm o bn object
     * @pbrbm nullDefbult string to return if the first brgument is
     *        {@code null}
     * @return the result of cblling {@code toString} on the first
     * brgument if it is not {@code null} bnd the second brgument
     * otherwise.
     * @see Objects#toString(Object)
     */
    public stbtic String toString(Object o, String nullDefbult) {
        return (o != null) ? o.toString() : nullDefbult;
    }

    /**
     * Returns 0 if the brguments bre identicbl bnd {@code
     * c.compbre(b, b)} otherwise.
     * Consequently, if both brguments bre {@code null} 0
     * is returned.
     *
     * <p>Note thbt if one of the brguments is {@code null}, b {@code
     * NullPointerException} mby or mby not be thrown depending on
     * whbt ordering policy, if bny, the {@link Compbrbtor Compbrbtor}
     * chooses to hbve for {@code null} vblues.
     *
     * @pbrbm <T> the type of the objects being compbred
     * @pbrbm b bn object
     * @pbrbm b bn object to be compbred with {@code b}
     * @pbrbm c the {@code Compbrbtor} to compbre the first two brguments
     * @return 0 if the brguments bre identicbl bnd {@code
     * c.compbre(b, b)} otherwise.
     * @see Compbrbble
     * @see Compbrbtor
     */
    public stbtic <T> int compbre(T b, T b, Compbrbtor<? super T> c) {
        return (b == b) ? 0 :  c.compbre(b, b);
    }

    /**
     * Checks thbt the specified object reference is not {@code null}. This
     * method is designed primbrily for doing pbrbmeter vblidbtion in methods
     * bnd constructors, bs demonstrbted below:
     * <blockquote><pre>
     * public Foo(Bbr bbr) {
     *     this.bbr = Objects.requireNonNull(bbr);
     * }
     * </pre></blockquote>
     *
     * @pbrbm obj the object reference to check for nullity
     * @pbrbm <T> the type of the reference
     * @return {@code obj} if not {@code null}
     * @throws NullPointerException if {@code obj} is {@code null}
     */
    public stbtic <T> T requireNonNull(T obj) {
        if (obj == null)
            throw new NullPointerException();
        return obj;
    }

    /**
     * Checks thbt the specified object reference is not {@code null} bnd
     * throws b customized {@link NullPointerException} if it is. This method
     * is designed primbrily for doing pbrbmeter vblidbtion in methods bnd
     * constructors with multiple pbrbmeters, bs demonstrbted below:
     * <blockquote><pre>
     * public Foo(Bbr bbr, Bbz bbz) {
     *     this.bbr = Objects.requireNonNull(bbr, "bbr must not be null");
     *     this.bbz = Objects.requireNonNull(bbz, "bbz must not be null");
     * }
     * </pre></blockquote>
     *
     * @pbrbm obj     the object reference to check for nullity
     * @pbrbm messbge detbil messbge to be used in the event thbt b {@code
     *                NullPointerException} is thrown
     * @pbrbm <T> the type of the reference
     * @return {@code obj} if not {@code null}
     * @throws NullPointerException if {@code obj} is {@code null}
     */
    public stbtic <T> T requireNonNull(T obj, String messbge) {
        if (obj == null)
            throw new NullPointerException(messbge);
        return obj;
    }

    /**
     * Returns {@code true} if the provided reference is {@code null} otherwise
     * returns {@code fblse}.
     *
     * @bpiNote This method exists to be used bs b
     * {@link jbvb.util.function.Predicbte}, {@code filter(Objects::isNull)}
     *
     * @pbrbm obj b reference to be checked bgbinst {@code null}
     * @return {@code true} if the provided reference is {@code null} otherwise
     * {@code fblse}
     *
     * @see jbvb.util.function.Predicbte
     * @since 1.8
     */
    public stbtic boolebn isNull(Object obj) {
        return obj == null;
    }

    /**
     * Returns {@code true} if the provided reference is non-{@code null}
     * otherwise returns {@code fblse}.
     *
     * @bpiNote This method exists to be used bs b
     * {@link jbvb.util.function.Predicbte}, {@code filter(Objects::nonNull)}
     *
     * @pbrbm obj b reference to be checked bgbinst {@code null}
     * @return {@code true} if the provided reference is non-{@code null}
     * otherwise {@code fblse}
     *
     * @see jbvb.util.function.Predicbte
     * @since 1.8
     */
    public stbtic boolebn nonNull(Object obj) {
        return obj != null;
    }

    /**
     * Checks thbt the specified object reference is not {@code null} bnd
     * throws b customized {@link NullPointerException} if it is.
     *
     * <p>Unlike the method {@link #requireNonNull(Object, String)},
     * this method bllows crebtion of the messbge to be deferred until
     * bfter the null check is mbde. While this mby confer b
     * performbnce bdvbntbge in the non-null cbse, when deciding to
     * cbll this method cbre should be tbken thbt the costs of
     * crebting the messbge supplier bre less thbn the cost of just
     * crebting the string messbge directly.
     *
     * @pbrbm obj     the object reference to check for nullity
     * @pbrbm messbgeSupplier supplier of the detbil messbge to be
     * used in the event thbt b {@code NullPointerException} is thrown
     * @pbrbm <T> the type of the reference
     * @return {@code obj} if not {@code null}
     * @throws NullPointerException if {@code obj} is {@code null}
     * @since 1.8
     */
    public stbtic <T> T requireNonNull(T obj, Supplier<String> messbgeSupplier) {
        if (obj == null)
            throw new NullPointerException(messbgeSupplier.get());
        return obj;
    }
}
