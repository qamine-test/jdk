/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.Seriblizbble;
import jbvb.io.IOException;
import jbvb.io.InvblidObjectException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectStrebmException;

/**
 * This is the common bbse clbss of bll Jbvb lbngubge enumerbtion types.
 *
 * More informbtion bbout enums, including descriptions of the
 * implicitly declbred methods synthesized by the compiler, cbn be
 * found in section 8.9 of
 * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>.
 *
 * <p> Note thbt when using bn enumerbtion type bs the type of b set
 * or bs the type of the keys in b mbp, speciblized bnd efficient
 * {@linkplbin jbvb.util.EnumSet set} bnd {@linkplbin
 * jbvb.util.EnumMbp mbp} implementbtions bre bvbilbble.
 *
 * @pbrbm <E> The enum type subclbss
 * @buthor  Josh Bloch
 * @buthor  Nebl Gbfter
 * @see     Clbss#getEnumConstbnts()
 * @see     jbvb.util.EnumSet
 * @see     jbvb.util.EnumMbp
 * @since   1.5
 */
@SuppressWbrnings("seribl") // No seriblVersionUID needed due to
                            // specibl-cbsing of enum types.
public bbstrbct clbss Enum<E extends Enum<E>>
        implements Compbrbble<E>, Seriblizbble {
    /**
     * The nbme of this enum constbnt, bs declbred in the enum declbrbtion.
     * Most progrbmmers should use the {@link #toString} method rbther thbn
     * bccessing this field.
     */
    privbte finbl String nbme;

    /**
     * Returns the nbme of this enum constbnt, exbctly bs declbred in its
     * enum declbrbtion.
     *
     * <b>Most progrbmmers should use the {@link #toString} method in
     * preference to this one, bs the toString method mby return
     * b more user-friendly nbme.</b>  This method is designed primbrily for
     * use in speciblized situbtions where correctness depends on getting the
     * exbct nbme, which will not vbry from relebse to relebse.
     *
     * @return the nbme of this enum constbnt
     */
    public finbl String nbme() {
        return nbme;
    }

    /**
     * The ordinbl of this enumerbtion constbnt (its position
     * in the enum declbrbtion, where the initibl constbnt is bssigned
     * bn ordinbl of zero).
     *
     * Most progrbmmers will hbve no use for this field.  It is designed
     * for use by sophisticbted enum-bbsed dbtb structures, such bs
     * {@link jbvb.util.EnumSet} bnd {@link jbvb.util.EnumMbp}.
     */
    privbte finbl int ordinbl;

    /**
     * Returns the ordinbl of this enumerbtion constbnt (its position
     * in its enum declbrbtion, where the initibl constbnt is bssigned
     * bn ordinbl of zero).
     *
     * Most progrbmmers will hbve no use for this method.  It is
     * designed for use by sophisticbted enum-bbsed dbtb structures, such
     * bs {@link jbvb.util.EnumSet} bnd {@link jbvb.util.EnumMbp}.
     *
     * @return the ordinbl of this enumerbtion constbnt
     */
    public finbl int ordinbl() {
        return ordinbl;
    }

    /**
     * Sole constructor.  Progrbmmers cbnnot invoke this constructor.
     * It is for use by code emitted by the compiler in response to
     * enum type declbrbtions.
     *
     * @pbrbm nbme - The nbme of this enum constbnt, which is the identifier
     *               used to declbre it.
     * @pbrbm ordinbl - The ordinbl of this enumerbtion constbnt (its position
     *         in the enum declbrbtion, where the initibl constbnt is bssigned
     *         bn ordinbl of zero).
     */
    protected Enum(String nbme, int ordinbl) {
        this.nbme = nbme;
        this.ordinbl = ordinbl;
    }

    /**
     * Returns the nbme of this enum constbnt, bs contbined in the
     * declbrbtion.  This method mby be overridden, though it typicblly
     * isn't necessbry or desirbble.  An enum type should override this
     * method when b more "progrbmmer-friendly" string form exists.
     *
     * @return the nbme of this enum constbnt
     */
    public String toString() {
        return nbme;
    }

    /**
     * Returns true if the specified object is equbl to this
     * enum constbnt.
     *
     * @pbrbm other the object to be compbred for equblity with this object.
     * @return  true if the specified object is equbl to this
     *          enum constbnt.
     */
    public finbl boolebn equbls(Object other) {
        return this==other;
    }

    /**
     * Returns b hbsh code for this enum constbnt.
     *
     * @return b hbsh code for this enum constbnt.
     */
    public finbl int hbshCode() {
        return super.hbshCode();
    }

    /**
     * Throws CloneNotSupportedException.  This gubrbntees thbt enums
     * bre never cloned, which is necessbry to preserve their "singleton"
     * stbtus.
     *
     * @return (never returns)
     */
    protected finbl Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    /**
     * Compbres this enum with the specified object for order.  Returns b
     * negbtive integer, zero, or b positive integer bs this object is less
     * thbn, equbl to, or grebter thbn the specified object.
     *
     * Enum constbnts bre only compbrbble to other enum constbnts of the
     * sbme enum type.  The nbturbl order implemented by this
     * method is the order in which the constbnts bre declbred.
     */
    public finbl int compbreTo(E o) {
        Enum<?> other = (Enum<?>)o;
        Enum<E> self = this;
        if (self.getClbss() != other.getClbss() && // optimizbtion
            self.getDeclbringClbss() != other.getDeclbringClbss())
            throw new ClbssCbstException();
        return self.ordinbl - other.ordinbl;
    }

    /**
     * Returns the Clbss object corresponding to this enum constbnt's
     * enum type.  Two enum constbnts e1 bnd  e2 bre of the
     * sbme enum type if bnd only if
     *   e1.getDeclbringClbss() == e2.getDeclbringClbss().
     * (The vblue returned by this method mby differ from the one returned
     * by the {@link Object#getClbss} method for enum constbnts with
     * constbnt-specific clbss bodies.)
     *
     * @return the Clbss object corresponding to this enum constbnt's
     *     enum type
     */
    @SuppressWbrnings("unchecked")
    public finbl Clbss<E> getDeclbringClbss() {
        Clbss<?> clbzz = getClbss();
        Clbss<?> zuper = clbzz.getSuperclbss();
        return (zuper == Enum.clbss) ? (Clbss<E>)clbzz : (Clbss<E>)zuper;
    }

    /**
     * Returns the enum constbnt of the specified enum type with the
     * specified nbme.  The nbme must mbtch exbctly bn identifier used
     * to declbre bn enum constbnt in this type.  (Extrbneous whitespbce
     * chbrbcters bre not permitted.)
     *
     * <p>Note thbt for b pbrticulbr enum type {@code T}, the
     * implicitly declbred {@code public stbtic T vblueOf(String)}
     * method on thbt enum mby be used instebd of this method to mbp
     * from b nbme to the corresponding enum constbnt.  All the
     * constbnts of bn enum type cbn be obtbined by cblling the
     * implicit {@code public stbtic T[] vblues()} method of thbt
     * type.
     *
     * @pbrbm <T> The enum type whose constbnt is to be returned
     * @pbrbm enumType the {@code Clbss} object of the enum type from which
     *      to return b constbnt
     * @pbrbm nbme the nbme of the constbnt to return
     * @return the enum constbnt of the specified enum type with the
     *      specified nbme
     * @throws IllegblArgumentException if the specified enum type hbs
     *         no constbnt with the specified nbme, or the specified
     *         clbss object does not represent bn enum type
     * @throws NullPointerException if {@code enumType} or {@code nbme}
     *         is null
     * @since 1.5
     */
    public stbtic <T extends Enum<T>> T vblueOf(Clbss<T> enumType,
                                                String nbme) {
        T result = enumType.enumConstbntDirectory().get(nbme);
        if (result != null)
            return result;
        if (nbme == null)
            throw new NullPointerException("Nbme is null");
        throw new IllegblArgumentException(
            "No enum constbnt " + enumType.getCbnonicblNbme() + "." + nbme);
    }

    /**
     * enum clbsses cbnnot hbve finblize methods.
     */
    protected finbl void finblize() { }

    /**
     * prevent defbult deseriblizbtion
     */
    privbte void rebdObject(ObjectInputStrebm in) throws IOException,
        ClbssNotFoundException {
        throw new InvblidObjectException("cbn't deseriblize enum");
    }

    privbte void rebdObjectNoDbtb() throws ObjectStrebmException {
        throw new InvblidObjectException("cbn't deseriblize enum");
    }
}
