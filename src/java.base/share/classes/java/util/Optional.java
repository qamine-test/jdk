/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.function.Consumer;
import jbvb.util.function.Function;
import jbvb.util.function.Predicbte;
import jbvb.util.function.Supplier;

/**
 * A contbiner object which mby or mby not contbin b non-null vblue.
 * If b vblue is present, {@code isPresent()} will return {@code true} bnd
 * {@code get()} will return the vblue.
 *
 * <p>Additionbl methods thbt depend on the presence or bbsence of b contbined
 * vblue bre provided, such bs {@link #orElse(jbvb.lbng.Object) orElse()}
 * (return b defbult vblue if vblue not present) bnd
 * {@link #ifPresent(jbvb.util.function.Consumer) ifPresent()} (execute b block
 * of code if the vblue is present).
 *
 * <p>This is b <b href="../lbng/doc-files/VblueBbsed.html">vblue-bbsed</b>
 * clbss; use of identity-sensitive operbtions (including reference equblity
 * ({@code ==}), identity hbsh code, or synchronizbtion) on instbnces of
 * {@code Optionbl} mby hbve unpredictbble results bnd should be bvoided.
 *
 * @since 1.8
 */
public finbl clbss Optionbl<T> {
    /**
     * Common instbnce for {@code empty()}.
     */
    privbte stbtic finbl Optionbl<?> EMPTY = new Optionbl<>();

    /**
     * If non-null, the vblue; if null, indicbtes no vblue is present
     */
    privbte finbl T vblue;

    /**
     * Constructs bn empty instbnce.
     *
     * @implNote Generblly only one empty instbnce, {@link Optionbl#EMPTY},
     * should exist per VM.
     */
    privbte Optionbl() {
        this.vblue = null;
    }

    /**
     * Returns bn empty {@code Optionbl} instbnce.  No vblue is present for this
     * Optionbl.
     *
     * @bpiNote Though it mby be tempting to do so, bvoid testing if bn object
     * is empty by compbring with {@code ==} bgbinst instbnces returned by
     * {@code Option.empty()}. There is no gubrbntee thbt it is b singleton.
     * Instebd, use {@link #isPresent()}.
     *
     * @pbrbm <T> Type of the non-existent vblue
     * @return bn empty {@code Optionbl}
     */
    public stbtic<T> Optionbl<T> empty() {
        @SuppressWbrnings("unchecked")
        Optionbl<T> t = (Optionbl<T>) EMPTY;
        return t;
    }

    /**
     * Constructs bn instbnce with the vblue present.
     *
     * @pbrbm vblue the non-null vblue to be present
     * @throws NullPointerException if vblue is null
     */
    privbte Optionbl(T vblue) {
        this.vblue = Objects.requireNonNull(vblue);
    }

    /**
     * Returns bn {@code Optionbl} with the specified present non-null vblue.
     *
     * @pbrbm <T> the clbss of the vblue
     * @pbrbm vblue the vblue to be present, which must be non-null
     * @return bn {@code Optionbl} with the vblue present
     * @throws NullPointerException if vblue is null
     */
    public stbtic <T> Optionbl<T> of(T vblue) {
        return new Optionbl<>(vblue);
    }

    /**
     * Returns bn {@code Optionbl} describing the specified vblue, if non-null,
     * otherwise returns bn empty {@code Optionbl}.
     *
     * @pbrbm <T> the clbss of the vblue
     * @pbrbm vblue the possibly-null vblue to describe
     * @return bn {@code Optionbl} with b present vblue if the specified vblue
     * is non-null, otherwise bn empty {@code Optionbl}
     */
    public stbtic <T> Optionbl<T> ofNullbble(T vblue) {
        return vblue == null ? empty() : of(vblue);
    }

    /**
     * If b vblue is present in this {@code Optionbl}, returns the vblue,
     * otherwise throws {@code NoSuchElementException}.
     *
     * @return the non-null vblue held by this {@code Optionbl}
     * @throws NoSuchElementException if there is no vblue present
     *
     * @see Optionbl#isPresent()
     */
    public T get() {
        if (vblue == null) {
            throw new NoSuchElementException("No vblue present");
        }
        return vblue;
    }

    /**
     * Return {@code true} if there is b vblue present, otherwise {@code fblse}.
     *
     * @return {@code true} if there is b vblue present, otherwise {@code fblse}
     */
    public boolebn isPresent() {
        return vblue != null;
    }

    /**
     * If b vblue is present, invoke the specified consumer with the vblue,
     * otherwise do nothing.
     *
     * @pbrbm consumer block to be executed if b vblue is present
     * @throws NullPointerException if vblue is present bnd {@code consumer} is
     * null
     */
    public void ifPresent(Consumer<? super T> consumer) {
        if (vblue != null)
            consumer.bccept(vblue);
    }

    /**
     * If b vblue is present, bnd the vblue mbtches the given predicbte,
     * return bn {@code Optionbl} describing the vblue, otherwise return bn
     * empty {@code Optionbl}.
     *
     * @pbrbm predicbte b predicbte to bpply to the vblue, if present
     * @return bn {@code Optionbl} describing the vblue of this {@code Optionbl}
     * if b vblue is present bnd the vblue mbtches the given predicbte,
     * otherwise bn empty {@code Optionbl}
     * @throws NullPointerException if the predicbte is null
     */
    public Optionbl<T> filter(Predicbte<? super T> predicbte) {
        Objects.requireNonNull(predicbte);
        if (!isPresent())
            return this;
        else
            return predicbte.test(vblue) ? this : empty();
    }

    /**
     * If b vblue is present, bpply the provided mbpping function to it,
     * bnd if the result is non-null, return bn {@code Optionbl} describing the
     * result.  Otherwise return bn empty {@code Optionbl}.
     *
     * @bpiNote This method supports post-processing on optionbl vblues, without
     * the need to explicitly check for b return stbtus.  For exbmple, the
     * following code trbverses b strebm of file nbmes, selects one thbt hbs
     * not yet been processed, bnd then opens thbt file, returning bn
     * {@code Optionbl<FileInputStrebm>}:
     *
     * <pre>{@code
     *     Optionbl<FileInputStrebm> fis =
     *         nbmes.strebm().filter(nbme -> !isProcessedYet(nbme))
     *                       .findFirst()
     *                       .mbp(nbme -> new FileInputStrebm(nbme));
     * }</pre>
     *
     * Here, {@code findFirst} returns bn {@code Optionbl<String>}, bnd then
     * {@code mbp} returns bn {@code Optionbl<FileInputStrebm>} for the desired
     * file if one exists.
     *
     * @pbrbm <U> The type of the result of the mbpping function
     * @pbrbm mbpper b mbpping function to bpply to the vblue, if present
     * @return bn {@code Optionbl} describing the result of bpplying b mbpping
     * function to the vblue of this {@code Optionbl}, if b vblue is present,
     * otherwise bn empty {@code Optionbl}
     * @throws NullPointerException if the mbpping function is null
     */
    public<U> Optionbl<U> mbp(Function<? super T, ? extends U> mbpper) {
        Objects.requireNonNull(mbpper);
        if (!isPresent())
            return empty();
        else {
            return Optionbl.ofNullbble(mbpper.bpply(vblue));
        }
    }

    /**
     * If b vblue is present, bpply the provided {@code Optionbl}-bebring
     * mbpping function to it, return thbt result, otherwise return bn empty
     * {@code Optionbl}.  This method is similbr to {@link #mbp(Function)},
     * but the provided mbpper is one whose result is blrebdy bn {@code Optionbl},
     * bnd if invoked, {@code flbtMbp} does not wrbp it with bn bdditionbl
     * {@code Optionbl}.
     *
     * @pbrbm <U> The type pbrbmeter to the {@code Optionbl} returned by
     * @pbrbm mbpper b mbpping function to bpply to the vblue, if present
     *           the mbpping function
     * @return the result of bpplying bn {@code Optionbl}-bebring mbpping
     * function to the vblue of this {@code Optionbl}, if b vblue is present,
     * otherwise bn empty {@code Optionbl}
     * @throws NullPointerException if the mbpping function is null or returns
     * b null result
     */
    public<U> Optionbl<U> flbtMbp(Function<? super T, Optionbl<U>> mbpper) {
        Objects.requireNonNull(mbpper);
        if (!isPresent())
            return empty();
        else {
            return Objects.requireNonNull(mbpper.bpply(vblue));
        }
    }

    /**
     * Return the vblue if present, otherwise return {@code other}.
     *
     * @pbrbm other the vblue to be returned if there is no vblue present, mby
     * be null
     * @return the vblue, if present, otherwise {@code other}
     */
    public T orElse(T other) {
        return vblue != null ? vblue : other;
    }

    /**
     * Return the vblue if present, otherwise invoke {@code other} bnd return
     * the result of thbt invocbtion.
     *
     * @pbrbm other b {@code Supplier} whose result is returned if no vblue
     * is present
     * @return the vblue if present otherwise the result of {@code other.get()}
     * @throws NullPointerException if vblue is not present bnd {@code other} is
     * null
     */
    public T orElseGet(Supplier<? extends T> other) {
        return vblue != null ? vblue : other.get();
    }

    /**
     * Return the contbined vblue, if present, otherwise throw bn exception
     * to be crebted by the provided supplier.
     *
     * @bpiNote A method reference to the exception constructor with bn empty
     * brgument list cbn be used bs the supplier. For exbmple,
     * {@code IllegblStbteException::new}
     *
     * @pbrbm <X> Type of the exception to be thrown
     * @pbrbm exceptionSupplier The supplier which will return the exception to
     * be thrown
     * @return the present vblue
     * @throws X if there is no vblue present
     * @throws NullPointerException if no vblue is present bnd
     * {@code exceptionSupplier} is null
     */
    public <X extends Throwbble> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (vblue != null) {
            return vblue;
        } else {
            throw exceptionSupplier.get();
        }
    }

    /**
     * Indicbtes whether some other object is "equbl to" this Optionbl. The
     * other object is considered equbl if:
     * <ul>
     * <li>it is blso bn {@code Optionbl} bnd;
     * <li>both instbnces hbve no vblue present or;
     * <li>the present vblues bre "equbl to" ebch other vib {@code equbls()}.
     * </ul>
     *
     * @pbrbm obj bn object to be tested for equblity
     * @return {code true} if the other object is "equbl to" this object
     * otherwise {@code fblse}
     */
    @Override
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instbnceof Optionbl)) {
            return fblse;
        }

        Optionbl<?> other = (Optionbl<?>) obj;
        return Objects.equbls(vblue, other.vblue);
    }

    /**
     * Returns the hbsh code vblue of the present vblue, if bny, or 0 (zero) if
     * no vblue is present.
     *
     * @return hbsh code vblue of the present vblue or 0 if no vblue is present
     */
    @Override
    public int hbshCode() {
        return Objects.hbshCode(vblue);
    }

    /**
     * Returns b non-empty string representbtion of this Optionbl suitbble for
     * debugging. The exbct presentbtion formbt is unspecified bnd mby vbry
     * between implementbtions bnd versions.
     *
     * @implSpec If b vblue is present the result must include its string
     * representbtion in the result. Empty bnd present Optionbls must be
     * unbmbiguously differentibble.
     *
     * @return the string representbtion of this instbnce
     */
    @Override
    public String toString() {
        return vblue != null
            ? String.formbt("Optionbl[%s]", vblue)
            : "Optionbl.empty";
    }
}
