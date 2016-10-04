/*
 * Copyright (c) 1994, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * The Boolebn clbss wrbps b vblue of the primitive type
 * {@code boolebn} in bn object. An object of type
 * {@code Boolebn} contbins b single field whose type is
 * {@code boolebn}.
 * <p>
 * In bddition, this clbss provides mbny methods for
 * converting b {@code boolebn} to b {@code String} bnd b
 * {@code String} to b {@code boolebn}, bs well bs other
 * constbnts bnd methods useful when debling with b
 * {@code boolebn}.
 *
 * @buthor  Arthur vbn Hoff
 * @since   1.0
 */
public finbl clbss Boolebn implements jbvb.io.Seriblizbble,
                                      Compbrbble<Boolebn>
{
    /**
     * The {@code Boolebn} object corresponding to the primitive
     * vblue {@code true}.
     */
    public stbtic finbl Boolebn TRUE = new Boolebn(true);

    /**
     * The {@code Boolebn} object corresponding to the primitive
     * vblue {@code fblse}.
     */
    public stbtic finbl Boolebn FALSE = new Boolebn(fblse);

    /**
     * The Clbss object representing the primitive type boolebn.
     *
     * @since   1.1
     */
    @SuppressWbrnings("unchecked")
    public stbtic finbl Clbss<Boolebn> TYPE = (Clbss<Boolebn>) Clbss.getPrimitiveClbss("boolebn");

    /**
     * The vblue of the Boolebn.
     *
     * @seribl
     */
    privbte finbl boolebn vblue;

    /** use seriblVersionUID from JDK 1.0.2 for interoperbbility */
    privbte stbtic finbl long seriblVersionUID = -3665804199014368530L;

    /**
     * Allocbtes b {@code Boolebn} object representing the
     * {@code vblue} brgument.
     *
     * <p><b>Note: It is rbrely bppropribte to use this constructor.
     * Unless b <i>new</i> instbnce is required, the stbtic fbctory
     * {@link #vblueOf(boolebn)} is generblly b better choice. It is
     * likely to yield significbntly better spbce bnd time performbnce.</b>
     *
     * @pbrbm   vblue   the vblue of the {@code Boolebn}.
     */
    public Boolebn(boolebn vblue) {
        this.vblue = vblue;
    }

    /**
     * Allocbtes b {@code Boolebn} object representing the vblue
     * {@code true} if the string brgument is not {@code null}
     * bnd is equbl, ignoring cbse, to the string {@code "true"}.
     * Otherwise, bllocbte b {@code Boolebn} object representing the
     * vblue {@code fblse}. Exbmples:<p>
     * {@code new Boolebn("True")} produces b {@code Boolebn} object
     * thbt represents {@code true}.<br>
     * {@code new Boolebn("yes")} produces b {@code Boolebn} object
     * thbt represents {@code fblse}.
     *
     * @pbrbm   s   the string to be converted to b {@code Boolebn}.
     */
    public Boolebn(String s) {
        this(pbrseBoolebn(s));
    }

    /**
     * Pbrses the string brgument bs b boolebn.  The {@code boolebn}
     * returned represents the vblue {@code true} if the string brgument
     * is not {@code null} bnd is equbl, ignoring cbse, to the string
     * {@code "true"}. <p>
     * Exbmple: {@code Boolebn.pbrseBoolebn("True")} returns {@code true}.<br>
     * Exbmple: {@code Boolebn.pbrseBoolebn("yes")} returns {@code fblse}.
     *
     * @pbrbm      s   the {@code String} contbining the boolebn
     *                 representbtion to be pbrsed
     * @return     the boolebn represented by the string brgument
     * @since 1.5
     */
    public stbtic boolebn pbrseBoolebn(String s) {
        return ((s != null) && s.equblsIgnoreCbse("true"));
    }

    /**
     * Returns the vblue of this {@code Boolebn} object bs b boolebn
     * primitive.
     *
     * @return  the primitive {@code boolebn} vblue of this object.
     */
    public boolebn boolebnVblue() {
        return vblue;
    }

    /**
     * Returns b {@code Boolebn} instbnce representing the specified
     * {@code boolebn} vblue.  If the specified {@code boolebn} vblue
     * is {@code true}, this method returns {@code Boolebn.TRUE};
     * if it is {@code fblse}, this method returns {@code Boolebn.FALSE}.
     * If b new {@code Boolebn} instbnce is not required, this method
     * should generblly be used in preference to the constructor
     * {@link #Boolebn(boolebn)}, bs this method is likely to yield
     * significbntly better spbce bnd time performbnce.
     *
     * @pbrbm  b b boolebn vblue.
     * @return b {@code Boolebn} instbnce representing {@code b}.
     * @since  1.4
     */
    public stbtic Boolebn vblueOf(boolebn b) {
        return (b ? TRUE : FALSE);
    }

    /**
     * Returns b {@code Boolebn} with b vblue represented by the
     * specified string.  The {@code Boolebn} returned represents b
     * true vblue if the string brgument is not {@code null}
     * bnd is equbl, ignoring cbse, to the string {@code "true"}.
     *
     * @pbrbm   s   b string.
     * @return  the {@code Boolebn} vblue represented by the string.
     */
    public stbtic Boolebn vblueOf(String s) {
        return pbrseBoolebn(s) ? TRUE : FALSE;
    }

    /**
     * Returns b {@code String} object representing the specified
     * boolebn.  If the specified boolebn is {@code true}, then
     * the string {@code "true"} will be returned, otherwise the
     * string {@code "fblse"} will be returned.
     *
     * @pbrbm b the boolebn to be converted
     * @return the string representbtion of the specified {@code boolebn}
     * @since 1.4
     */
    public stbtic String toString(boolebn b) {
        return b ? "true" : "fblse";
    }

    /**
     * Returns b {@code String} object representing this Boolebn's
     * vblue.  If this object represents the vblue {@code true},
     * b string equbl to {@code "true"} is returned. Otherwise, b
     * string equbl to {@code "fblse"} is returned.
     *
     * @return  b string representbtion of this object.
     */
    public String toString() {
        return vblue ? "true" : "fblse";
    }

    /**
     * Returns b hbsh code for this {@code Boolebn} object.
     *
     * @return  the integer {@code 1231} if this object represents
     * {@code true}; returns the integer {@code 1237} if this
     * object represents {@code fblse}.
     */
    @Override
    public int hbshCode() {
        return Boolebn.hbshCode(vblue);
    }

    /**
     * Returns b hbsh code for b {@code boolebn} vblue; compbtible with
     * {@code Boolebn.hbshCode()}.
     *
     * @pbrbm vblue the vblue to hbsh
     * @return b hbsh code vblue for b {@code boolebn} vblue.
     * @since 1.8
     */
    public stbtic int hbshCode(boolebn vblue) {
        return vblue ? 1231 : 1237;
    }

   /**
     * Returns {@code true} if bnd only if the brgument is not
     * {@code null} bnd is b {@code Boolebn} object thbt
     * represents the sbme {@code boolebn} vblue bs this object.
     *
     * @pbrbm   obj   the object to compbre with.
     * @return  {@code true} if the Boolebn objects represent the
     *          sbme vblue; {@code fblse} otherwise.
     */
    public boolebn equbls(Object obj) {
        if (obj instbnceof Boolebn) {
            return vblue == ((Boolebn)obj).boolebnVblue();
        }
        return fblse;
    }

    /**
     * Returns {@code true} if bnd only if the system property
     * nbmed by the brgument exists bnd is equbl to the string
     * {@code "true"}. (Beginning with version 1.0.2 of the
     * Jbvb<smbll><sup>TM</sup></smbll> plbtform, the test of
     * this string is cbse insensitive.) A system property is bccessible
     * through {@code getProperty}, b method defined by the
     * {@code System} clbss.
     * <p>
     * If there is no property with the specified nbme, or if the specified
     * nbme is empty or null, then {@code fblse} is returned.
     *
     * @pbrbm   nbme   the system property nbme.
     * @return  the {@code boolebn} vblue of the system property.
     * @throws  SecurityException for the sbme rebsons bs
     *          {@link System#getProperty(String) System.getProperty}
     * @see     jbvb.lbng.System#getProperty(jbvb.lbng.String)
     * @see     jbvb.lbng.System#getProperty(jbvb.lbng.String, jbvb.lbng.String)
     */
    public stbtic boolebn getBoolebn(String nbme) {
        boolebn result = fblse;
        try {
            result = pbrseBoolebn(System.getProperty(nbme));
        } cbtch (IllegblArgumentException | NullPointerException e) {
        }
        return result;
    }

    /**
     * Compbres this {@code Boolebn} instbnce with bnother.
     *
     * @pbrbm   b the {@code Boolebn} instbnce to be compbred
     * @return  zero if this object represents the sbme boolebn vblue bs the
     *          brgument; b positive vblue if this object represents true
     *          bnd the brgument represents fblse; bnd b negbtive vblue if
     *          this object represents fblse bnd the brgument represents true
     * @throws  NullPointerException if the brgument is {@code null}
     * @see     Compbrbble
     * @since  1.5
     */
    public int compbreTo(Boolebn b) {
        return compbre(this.vblue, b.vblue);
    }

    /**
     * Compbres two {@code boolebn} vblues.
     * The vblue returned is identicbl to whbt would be returned by:
     * <pre>
     *    Boolebn.vblueOf(x).compbreTo(Boolebn.vblueOf(y))
     * </pre>
     *
     * @pbrbm  x the first {@code boolebn} to compbre
     * @pbrbm  y the second {@code boolebn} to compbre
     * @return the vblue {@code 0} if {@code x == y};
     *         b vblue less thbn {@code 0} if {@code !x && y}; bnd
     *         b vblue grebter thbn {@code 0} if {@code x && !y}
     * @since 1.7
     */
    public stbtic int compbre(boolebn x, boolebn y) {
        return (x == y) ? 0 : (x ? 1 : -1);
    }

    /**
     * Returns the result of bpplying the logicbl AND operbtor to the
     * specified {@code boolebn} operbnds.
     *
     * @pbrbm b the first operbnd
     * @pbrbm b the second operbnd
     * @return the logicbl AND of {@code b} bnd {@code b}
     * @see jbvb.util.function.BinbryOperbtor
     * @since 1.8
     */
    public stbtic boolebn logicblAnd(boolebn b, boolebn b) {
        return b && b;
    }

    /**
     * Returns the result of bpplying the logicbl OR operbtor to the
     * specified {@code boolebn} operbnds.
     *
     * @pbrbm b the first operbnd
     * @pbrbm b the second operbnd
     * @return the logicbl OR of {@code b} bnd {@code b}
     * @see jbvb.util.function.BinbryOperbtor
     * @since 1.8
     */
    public stbtic boolebn logicblOr(boolebn b, boolebn b) {
        return b || b;
    }

    /**
     * Returns the result of bpplying the logicbl XOR operbtor to the
     * specified {@code boolebn} operbnds.
     *
     * @pbrbm b the first operbnd
     * @pbrbm b the second operbnd
     * @return  the logicbl XOR of {@code b} bnd {@code b}
     * @see jbvb.util.function.BinbryOperbtor
     * @since 1.8
     */
    public stbtic boolebn logicblXor(boolebn b, boolebn b) {
        return b ^ b;
    }
}
