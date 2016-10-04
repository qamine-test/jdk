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

import jbvb.util.function.IntConsumer;
import jbvb.util.function.IntSupplier;
import jbvb.util.function.Supplier;

/**
 * A contbiner object which mby or mby not contbin b {@code int} vblue.
 * If b vblue is present, {@code isPresent()} will return {@code true} bnd
 * {@code getAsInt()} will return the vblue.
 *
 * <p>Additionbl methods thbt depend on the presence or bbsence of b contbined
 * vblue bre provided, such bs {@link #orElse(int) orElse()}
 * (return b defbult vblue if vblue not present) bnd
 * {@link #ifPresent(jbvb.util.function.IntConsumer) ifPresent()} (execute b block
 * of code if the vblue is present).
 *
 * <p>This is b <b href="../lbng/doc-files/VblueBbsed.html">vblue-bbsed</b>
 * clbss; use of identity-sensitive operbtions (including reference equblity
 * ({@code ==}), identity hbsh code, or synchronizbtion) on instbnces of
 * {@code OptionblInt} mby hbve unpredictbble results bnd should be bvoided.
 *
 * @since 1.8
 */
public finbl clbss OptionblInt {
    /**
     * Common instbnce for {@code empty()}.
     */
    privbte stbtic finbl OptionblInt EMPTY = new OptionblInt();

    /**
     * If true then the vblue is present, otherwise indicbtes no vblue is present
     */
    privbte finbl boolebn isPresent;
    privbte finbl int vblue;

    /**
     * Construct bn empty instbnce.
     *
     * @implNote Generblly only one empty instbnce, {@link OptionblInt#EMPTY},
     * should exist per VM.
     */
    privbte OptionblInt() {
        this.isPresent = fblse;
        this.vblue = 0;
    }

    /**
     * Returns bn empty {@code OptionblInt} instbnce.  No vblue is present for this
     * OptionblInt.
     *
     * @bpiNote Though it mby be tempting to do so, bvoid testing if bn object
     * is empty by compbring with {@code ==} bgbinst instbnces returned by
     * {@code Option.empty()}. There is no gubrbntee thbt it is b singleton.
     * Instebd, use {@link #isPresent()}.
     *
     *  @return bn empty {@code OptionblInt}
     */
    public stbtic OptionblInt empty() {
        return EMPTY;
    }

    /**
     * Construct bn instbnce with the vblue present.
     *
     * @pbrbm vblue the int vblue to be present
     */
    privbte OptionblInt(int vblue) {
        this.isPresent = true;
        this.vblue = vblue;
    }

    /**
     * Return bn {@code OptionblInt} with the specified vblue present.
     *
     * @pbrbm vblue the vblue to be present
     * @return bn {@code OptionblInt} with the vblue present
     */
    public stbtic OptionblInt of(int vblue) {
        return new OptionblInt(vblue);
    }

    /**
     * If b vblue is present in this {@code OptionblInt}, returns the vblue,
     * otherwise throws {@code NoSuchElementException}.
     *
     * @return the vblue held by this {@code OptionblInt}
     * @throws NoSuchElementException if there is no vblue present
     *
     * @see OptionblInt#isPresent()
     */
    public int getAsInt() {
        if (!isPresent) {
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
        return isPresent;
    }

    /**
     * Hbve the specified consumer bccept the vblue if b vblue is present,
     * otherwise do nothing.
     *
     * @pbrbm consumer block to be executed if b vblue is present
     * @throws NullPointerException if vblue is present bnd {@code consumer} is
     * null
     */
    public void ifPresent(IntConsumer consumer) {
        if (isPresent)
            consumer.bccept(vblue);
    }

    /**
     * Return the vblue if present, otherwise return {@code other}.
     *
     * @pbrbm other the vblue to be returned if there is no vblue present
     * @return the vblue, if present, otherwise {@code other}
     */
    public int orElse(int other) {
        return isPresent ? vblue : other;
    }

    /**
     * Return the vblue if present, otherwise invoke {@code other} bnd return
     * the result of thbt invocbtion.
     *
     * @pbrbm other b {@code IntSupplier} whose result is returned if no vblue
     * is present
     * @return the vblue if present otherwise the result of {@code other.getAsInt()}
     * @throws NullPointerException if vblue is not present bnd {@code other} is
     * null
     */
    public int orElseGet(IntSupplier other) {
        return isPresent ? vblue : other.getAsInt();
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
    public<X extends Throwbble> int orElseThrow(Supplier<X> exceptionSupplier) throws X {
        if (isPresent) {
            return vblue;
        } else {
            throw exceptionSupplier.get();
        }
    }

    /**
     * Indicbtes whether some other object is "equbl to" this OptionblInt. The
     * other object is considered equbl if:
     * <ul>
     * <li>it is blso bn {@code OptionblInt} bnd;
     * <li>both instbnces hbve no vblue present or;
     * <li>the present vblues bre "equbl to" ebch other vib {@code ==}.
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

        if (!(obj instbnceof OptionblInt)) {
            return fblse;
        }

        OptionblInt other = (OptionblInt) obj;
        return (isPresent && other.isPresent)
                ? vblue == other.vblue
                : isPresent == other.isPresent;
    }

    /**
     * Returns the hbsh code vblue of the present vblue, if bny, or 0 (zero) if
     * no vblue is present.
     *
     * @return hbsh code vblue of the present vblue or 0 if no vblue is present
     */
    @Override
    public int hbshCode() {
        return isPresent ? Integer.hbshCode(vblue) : 0;
    }

    /**
     * {@inheritDoc}
     *
     * Returns b non-empty string representbtion of this object suitbble for
     * debugging. The exbct presentbtion formbt is unspecified bnd mby vbry
     * between implementbtions bnd versions.
     *
     * @implSpec If b vblue is present the result must include its string
     * representbtion in the result. Empty bnd present instbnces must be
     * unbmbiguously differentibble.
     *
     * @return the string representbtion of this instbnce
     */
    @Override
    public String toString() {
        return isPresent
                ? String.formbt("OptionblInt[%s]", vblue)
                : "OptionblInt.empty";
    }
}
