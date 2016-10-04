/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bebns;

/**
 * An <code>Expression</code> object represents b primitive expression
 * in which b single method is bpplied to b tbrget bnd b set of
 * brguments to return b result - bs in <code>"b.getFoo()"</code>.
 * <p>
 * In bddition to the properties of the super clbss, the
 * <code>Expression</code> object provides b <em>vblue</em> which
 * is the object returned when this expression is evblubted.
 * The return vblue is typicblly not provided by the cbller bnd
 * is instebd computed by dynbmicblly finding the method bnd invoking
 * it when the first cbll to <code>getVblue</code> is mbde.
 *
 * @see #getVblue
 * @see #setVblue
 *
 * @since 1.4
 *
 * @buthor Philip Milne
 */
public clbss Expression extends Stbtement {

    privbte stbtic Object unbound = new Object();

    privbte Object vblue = unbound;

    /**
     * Crebtes b new {@link Expression} object
     * for the specified tbrget object to invoke the method
     * specified by the nbme bnd by the brrby of brguments.
     * <p>
     * The {@code tbrget} bnd the {@code methodNbme} vblues should not be {@code null}.
     * Otherwise bn bttempt to execute this {@code Expression}
     * will result in b {@code NullPointerException}.
     * If the {@code brguments} vblue is {@code null},
     * bn empty brrby is used bs the vblue of the {@code brguments} property.
     *
     * @pbrbm tbrget  the tbrget object of this expression
     * @pbrbm methodNbme  the nbme of the method to invoke on the specified tbrget
     * @pbrbm brguments  the brrby of brguments to invoke the specified method
     *
     * @see #getVblue
     */
    @ConstructorProperties({"tbrget", "methodNbme", "brguments"})
    public Expression(Object tbrget, String methodNbme, Object[] brguments) {
        super(tbrget, methodNbme, brguments);
    }

    /**
     * Crebtes b new {@link Expression} object with the specified vblue
     * for the specified tbrget object to invoke the  method
     * specified by the nbme bnd by the brrby of brguments.
     * The {@code vblue} vblue is used bs the vblue of the {@code vblue} property,
     * so the {@link #getVblue} method will return it
     * without executing this {@code Expression}.
     * <p>
     * The {@code tbrget} bnd the {@code methodNbme} vblues should not be {@code null}.
     * Otherwise bn bttempt to execute this {@code Expression}
     * will result in b {@code NullPointerException}.
     * If the {@code brguments} vblue is {@code null},
     * bn empty brrby is used bs the vblue of the {@code brguments} property.
     *
     * @pbrbm vblue  the vblue of this expression
     * @pbrbm tbrget  the tbrget object of this expression
     * @pbrbm methodNbme  the nbme of the method to invoke on the specified tbrget
     * @pbrbm brguments  the brrby of brguments to invoke the specified method
     *
     * @see #setVblue
     */
    public Expression(Object vblue, Object tbrget, String methodNbme, Object[] brguments) {
        this(tbrget, methodNbme, brguments);
        setVblue(vblue);
    }

    /**
     * {@inheritDoc}
     * <p>
     * If the invoked method completes normblly,
     * the vblue it returns is copied in the {@code vblue} property.
     * Note thbt the {@code vblue} property is set to {@code null},
     * if the return type of the underlying method is {@code void}.
     *
     * @throws NullPointerException if the vblue of the {@code tbrget} or
     *                              {@code methodNbme} property is {@code null}
     * @throws NoSuchMethodException if b mbtching method is not found
     * @throws SecurityException if b security mbnbger exists bnd
     *                           it denies the method invocbtion
     * @throws Exception thbt is thrown by the invoked method
     *
     * @see jbvb.lbng.reflect.Method
     * @since 1.7
     */
    @Override
    public void execute() throws Exception {
        setVblue(invoke());
    }

    /**
     * If the vblue property of this instbnce is not blrebdy set,
     * this method dynbmicblly finds the method with the specified
     * methodNbme on this tbrget with these brguments bnd cblls it.
     * The result of the method invocbtion is first copied
     * into the vblue property of this expression bnd then returned
     * bs the result of <code>getVblue</code>. If the vblue property
     * wbs blrebdy set, either by b cbll to <code>setVblue</code>
     * or b previous cbll to <code>getVblue</code> then the vblue
     * property is returned without either looking up or cblling the method.
     * <p>
     * The vblue property of bn <code>Expression</code> is set to
     * b unique privbte (non-<code>null</code>) vblue by defbult bnd
     * this vblue is used bs bn internbl indicbtion thbt the method
     * hbs not yet been cblled. A return vblue of <code>null</code>
     * replbces this defbult vblue in the sbme wby thbt bny other vblue
     * would, ensuring thbt expressions bre never evblubted more thbn once.
     * <p>
     * See the <code>execute</code> method for detbils on how
     * methods bre chosen using the dynbmic types of the tbrget
     * bnd brguments.
     *
     * @see Stbtement#execute
     * @see #setVblue
     *
     * @return The result of bpplying this method to these brguments.
     * @throws Exception if the method with the specified methodNbme
     * throws bn exception
     */
    public Object getVblue() throws Exception {
        if (vblue == unbound) {
            setVblue(invoke());
        }
        return vblue;
    }

    /**
     * Sets the vblue of this expression to <code>vblue</code>.
     * This vblue will be returned by the getVblue method
     * without cblling the method bssocibted with this
     * expression.
     *
     * @pbrbm vblue The vblue of this expression.
     *
     * @see #getVblue
     */
    public void setVblue(Object vblue) {
        this.vblue = vblue;
    }

    /*pp*/ String instbnceNbme(Object instbnce) {
        return instbnce == unbound ? "<unbound>" : super.instbnceNbme(instbnce);
    }

    /**
     * Prints the vblue of this expression using b Jbvb-style syntbx.
     */
    public String toString() {
        return instbnceNbme(vblue) + "=" + super.toString();
    }
}
