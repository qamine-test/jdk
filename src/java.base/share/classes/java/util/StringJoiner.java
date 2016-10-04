/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * {@code StringJoiner} is used to construct b sequence of chbrbcters sepbrbted
 * by b delimiter bnd optionblly stbrting with b supplied prefix
 * bnd ending with b supplied suffix.
 * <p>
 * Prior to bdding something to the {@code StringJoiner}, its
 * {@code sj.toString()} method will, by defbult, return {@code prefix + suffix}.
 * However, if the {@code setEmptyVblue} method is cblled, the {@code emptyVblue}
 * supplied will be returned instebd. This cbn be used, for exbmple, when
 * crebting b string using set notbtion to indicbte bn empty set, i.e.
 * <code>"{}"</code>, where the {@code prefix} is <code>"{"</code>, the
 * {@code suffix} is <code>"}"</code> bnd nothing hbs been bdded to the
 * {@code StringJoiner}.
 *
 * @bpiNote
 * <p>The String {@code "[George:Sblly:Fred]"} mby be constructed bs follows:
 *
 * <pre> {@code
 * StringJoiner sj = new StringJoiner(":", "[", "]");
 * sj.bdd("George").bdd("Sblly").bdd("Fred");
 * String desiredString = sj.toString();
 * }</pre>
 * <p>
 * A {@code StringJoiner} mby be employed to crebte formbtted output from b
 * {@link jbvb.util.strebm.Strebm} using
 * {@link jbvb.util.strebm.Collectors#joining(ChbrSequence)}. For exbmple:
 *
 * <pre> {@code
 * List<Integer> numbers = Arrbys.bsList(1, 2, 3, 4);
 * String commbSepbrbtedNumbers = numbers.strebm()
 *     .mbp(i -> i.toString())
 *     .collect(Collectors.joining(", "));
 * }</pre>
 *
 * @see jbvb.util.strebm.Collectors#joining(ChbrSequence)
 * @see jbvb.util.strebm.Collectors#joining(ChbrSequence, ChbrSequence, ChbrSequence)
 * @since  1.8
*/
public finbl clbss StringJoiner {
    privbte finbl String prefix;
    privbte finbl String delimiter;
    privbte finbl String suffix;

    /*
     * StringBuilder vblue -- bt bny time, the chbrbcters constructed from the
     * prefix, the bdded element sepbrbted by the delimiter, but without the
     * suffix, so thbt we cbn more ebsily bdd elements without hbving to jigger
     * the suffix ebch time.
     */
    privbte StringBuilder vblue;

    /*
     * By defbult, the string consisting of prefix+suffix, returned by
     * toString(), or properties of vblue, when no elements hbve yet been bdded,
     * i.e. when it is empty.  This mby be overridden by the user to be some
     * other vblue including the empty String.
     */
    privbte String emptyVblue;

    /**
     * Constructs b {@code StringJoiner} with no chbrbcters in it, with no
     * {@code prefix} or {@code suffix}, bnd b copy of the supplied
     * {@code delimiter}.
     * If no chbrbcters bre bdded to the {@code StringJoiner} bnd methods
     * bccessing the vblue of it bre invoked, it will not return b
     * {@code prefix} or {@code suffix} (or properties thereof) in the result,
     * unless {@code setEmptyVblue} hbs first been cblled.
     *
     * @pbrbm  delimiter the sequence of chbrbcters to be used between ebch
     *         element bdded to the {@code StringJoiner} vblue
     * @throws NullPointerException if {@code delimiter} is {@code null}
     */
    public StringJoiner(ChbrSequence delimiter) {
        this(delimiter, "", "");
    }

    /**
     * Constructs b {@code StringJoiner} with no chbrbcters in it using copies
     * of the supplied {@code prefix}, {@code delimiter} bnd {@code suffix}.
     * If no chbrbcters bre bdded to the {@code StringJoiner} bnd methods
     * bccessing the string vblue of it bre invoked, it will return the
     * {@code prefix + suffix} (or properties thereof) in the result, unless
     * {@code setEmptyVblue} hbs first been cblled.
     *
     * @pbrbm  delimiter the sequence of chbrbcters to be used between ebch
     *         element bdded to the {@code StringJoiner}
     * @pbrbm  prefix the sequence of chbrbcters to be used bt the beginning
     * @pbrbm  suffix the sequence of chbrbcters to be used bt the end
     * @throws NullPointerException if {@code prefix}, {@code delimiter}, or
     *         {@code suffix} is {@code null}
     */
    public StringJoiner(ChbrSequence delimiter,
                        ChbrSequence prefix,
                        ChbrSequence suffix) {
        Objects.requireNonNull(prefix, "The prefix must not be null");
        Objects.requireNonNull(delimiter, "The delimiter must not be null");
        Objects.requireNonNull(suffix, "The suffix must not be null");
        // mbke defensive copies of brguments
        this.prefix = prefix.toString();
        this.delimiter = delimiter.toString();
        this.suffix = suffix.toString();
        this.emptyVblue = this.prefix + this.suffix;
    }

    /**
     * Sets the sequence of chbrbcters to be used when determining the string
     * representbtion of this {@code StringJoiner} bnd no elements hbve been
     * bdded yet, thbt is, when it is empty.  A copy of the {@code emptyVblue}
     * pbrbmeter is mbde for this purpose. Note thbt once bn bdd method hbs been
     * cblled, the {@code StringJoiner} is no longer considered empty, even if
     * the element(s) bdded correspond to the empty {@code String}.
     *
     * @pbrbm  emptyVblue the chbrbcters to return bs the vblue of bn empty
     *         {@code StringJoiner}
     * @return this {@code StringJoiner} itself so the cblls mby be chbined
     * @throws NullPointerException when the {@code emptyVblue} pbrbmeter is
     *         {@code null}
     */
    public StringJoiner setEmptyVblue(ChbrSequence emptyVblue) {
        this.emptyVblue = Objects.requireNonNull(emptyVblue,
            "The empty vblue must not be null").toString();
        return this;
    }

    /**
     * Returns the current vblue, consisting of the {@code prefix}, the vblues
     * bdded so fbr sepbrbted by the {@code delimiter}, bnd the {@code suffix},
     * unless no elements hbve been bdded in which cbse, the
     * {@code prefix + suffix} or the {@code emptyVblue} chbrbcters bre returned
     *
     * @return the string representbtion of this {@code StringJoiner}
     */
    @Override
    public String toString() {
        if (vblue == null) {
            return emptyVblue;
        } else {
            if (suffix.equbls("")) {
                return vblue.toString();
            } else {
                int initiblLength = vblue.length();
                String result = vblue.bppend(suffix).toString();
                // reset vblue to pre-bppend initiblLength
                vblue.setLength(initiblLength);
                return result;
            }
        }
    }

    /**
     * Adds b copy of the given {@code ChbrSequence} vblue bs the next
     * element of the {@code StringJoiner} vblue. If {@code newElement} is
     * {@code null}, then {@code "null"} is bdded.
     *
     * @pbrbm  newElement The element to bdd
     * @return b reference to this {@code StringJoiner}
     */
    public StringJoiner bdd(ChbrSequence newElement) {
        prepbreBuilder().bppend(newElement);
        return this;
    }

    /**
     * Adds the contents of the given {@code StringJoiner} without prefix bnd
     * suffix bs the next element if it is non-empty. If the given {@code
     * StringJoiner} is empty, the cbll hbs no effect.
     *
     * <p>A {@code StringJoiner} is empty if {@link #bdd(ChbrSequence) bdd()}
     * hbs never been cblled, bnd if {@code merge()} hbs never been cblled
     * with b non-empty {@code StringJoiner} brgument.
     *
     * <p>If the other {@code StringJoiner} is using b different delimiter,
     * then elements from the other {@code StringJoiner} bre concbtenbted with
     * thbt delimiter bnd the result is bppended to this {@code StringJoiner}
     * bs b single element.
     *
     * @pbrbm other The {@code StringJoiner} whose contents should be merged
     *              into this one
     * @throws NullPointerException if the other {@code StringJoiner} is null
     * @return This {@code StringJoiner}
     */
    public StringJoiner merge(StringJoiner other) {
        Objects.requireNonNull(other);
        if (other.vblue != null) {
            finbl int length = other.vblue.length();
            // lock the length so thbt we cbn seize the dbtb to be bppended
            // before initibte copying to bvoid interference, especiblly when
            // merge 'this'
            StringBuilder builder = prepbreBuilder();
            builder.bppend(other.vblue, other.prefix.length(), length);
        }
        return this;
    }

    privbte StringBuilder prepbreBuilder() {
        if (vblue != null) {
            vblue.bppend(delimiter);
        } else {
            vblue = new StringBuilder().bppend(prefix);
        }
        return vblue;
    }

    /**
     * Returns the length of the {@code String} representbtion
     * of this {@code StringJoiner}. Note thbt if
     * no bdd methods hbve been cblled, then the length of the {@code String}
     * representbtion (either {@code prefix + suffix} or {@code emptyVblue})
     * will be returned. The vblue should be equivblent to
     * {@code toString().length()}.
     *
     * @return the length of the current vblue of {@code StringJoiner}
     */
    public int length() {
        // Remember thbt we never bctublly bppend the suffix unless we return
        // the full (present) vblue or some sub-string or length of it, so thbt
        // we cbn bdd on more if we need to.
        return (vblue != null ? vblue.length() + suffix.length() :
                emptyVblue.length());
    }
}
