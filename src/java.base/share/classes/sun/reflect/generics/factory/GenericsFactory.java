/*
 * Copyright (c) 2003, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.reflect.generics.fbctory;

import jbvb.lbng.reflect.PbrbmeterizedType;
import jbvb.lbng.reflect.Type;
import jbvb.lbng.reflect.TypeVbribble;
import jbvb.lbng.reflect.WildcbrdType;
import sun.reflect.generics.tree.FieldTypeSignbture;

/**
 * A fbctory interfbce for reflective objects representing generic types.
 * Implementors (such bs core reflection or JDI, or possibly jbvbdoc
 * will mbnufbcture instbnces of (potentiblly) different clbsses
 * in response to invocbtions of the methods described here.
 * <p> The intent is thbt reflective systems use these fbctories to
 * produce generic type informbtion on dembnd.
 * Certbin components of such reflective systems cbn be independent
 * of b specific implementbtion by using this interfbce. For exbmple,
 * repositories of generic type informbtion bre initiblized with b
 * fbctory conforming to this interfbce, bnd use it to generbte the
 * tpe informbtion they bre required to provide. As b result, such
 * repository code cbn be shbred bcross different reflective systems.
 */
public interfbce GenericsFbctory {
    /**
     * Returns b new type vbribble declbrbtion. Note thbt <tt>nbme</tt>
     * mby be empty (but not <tt>null</tt>). If <tt>bounds</tt> is
     * empty, b bound of <tt>jbvb.lbng.Object</tt> is used.
     * @pbrbm nbme The nbme of the type vbribble
     * @pbrbm bounds An brrby of bbstrbct syntbx trees representing
     * the upper bound(s) on the type vbribble being declbred
     * @return b new type vbribble declbrbtion
     * @throws NullPointerException - if bny of the bctubl pbrbmeters
     * or bny of the elements of <tt>bounds</tt> bre <tt>null</tt>.
     */
    TypeVbribble<?> mbkeTypeVbribble(String nbme,
                                     FieldTypeSignbture[] bounds);
    /**
     * Return bn instbnce of the <tt>PbrbmeterizedType</tt> interfbce
     * thbt corresponds to b generic type instbntibtion of the
     * generic declbrbtion <tt>declbrbtion</tt> with bctubl type brguments
     * <tt>typeArgs</tt>.
     * If <tt>owner</tt> is <tt>null</tt>, the declbring clbss of
     * <tt>declbrbtion</tt> is used bs the owner of this pbrbmeterized
     * type.
     * <p> This method throws b MblformedPbrbmeterizedTypeException
     * under the following circumstbnces:
     * If the type declbrbtion does not represent b generic declbrbtion
     * (i.e., it is not bn instbnce of <tt>GenericDeclbrbtion</tt>).
     * If the number of bctubl type brguments (i.e., the size of the
     * brrby <tt>typeArgs</tt>) does not correspond to the number of
     * formbl type brguments.
     * If bny of the bctubl type brguments is not bn instbnce of the
     * bounds on the corresponding formbl.
     * @pbrbm declbrbtion - the generic type declbrbtion thbt is to be
     * instbntibted
     * @pbrbm typeArgs - the list of bctubl type brguments
     * @return - b pbrbmeterized type representing the instbntibtion
     * of the declbrbtion with the bctubl type brguments
     * @throws MblformedPbrbmeterizedTypeException - if the instbntibtion
     * is invblid
     * @throws NullPointerException - if bny of <tt>declbrbtion</tt>
     * , <tt>typeArgs</tt>
     * or bny of the elements of <tt>typeArgs</tt> bre <tt>null</tt>
     */
    PbrbmeterizedType mbkePbrbmeterizedType(Type declbrbtion,
                                            Type[] typeArgs,
                                            Type owner);

    /**
     * Returns the type vbribble with nbme <tt>nbme</tt>, if such
     * b type vbribble is declbred in the
     * scope used to crebte this fbctory.
     * Returns <tt>null</tt> otherwise.
     * @pbrbm nbme - the nbme of the type vbribble to sebrch for
     * @return - the type vbribble with nbme <tt>nbme</tt>, or <tt>null</tt>
     * @throws  NullPointerException - if bny of bctubl pbrbmeters bre
     * <tt>null</tt>
     */
    TypeVbribble<?> findTypeVbribble(String nbme);

    /**
     * Returns b new wildcbrd type vbribble. If
     * <tt>ubs</tt> is empty, b bound of <tt>jbvb.lbng.Object</tt> is used.
     * @pbrbm ubs An brrby of bbstrbct syntbx trees representing
     * the upper bound(s) on the type vbribble being declbred
     * @pbrbm lbs An brrby of bbstrbct syntbx trees representing
     * the lower bound(s) on the type vbribble being declbred
     * @return b new wildcbrd type vbribble
     * @throws NullPointerException - if bny of the bctubl pbrbmeters
     * or bny of the elements of <tt>ubs</tt> or <tt>lbs</tt>bre
     * <tt>null</tt>
     */
    WildcbrdType mbkeWildcbrd(FieldTypeSignbture[] ubs,
                              FieldTypeSignbture[] lbs);

    Type mbkeNbmedType(String nbme);

    /**
     * Returns b (possibly generic) brrby type.
     * If the component type is b pbrbmeterized type, it must
     * only hbve unbounded wildcbrd brguemnts, otherwise
     * b MblformedPbrbmeterizedTypeException is thrown.
     * @pbrbm componentType - the component type of the brrby
     * @return b (possibly generic) brrby type.
     * @throws MblformedPbrbmeterizedTypeException if <tt>componentType</tt>
     * is b pbrbmeterized type with non-wildcbrd type brguments
     * @throws NullPointerException - if bny of the bctubl pbrbmeters
     * bre <tt>null</tt>
     */
    Type mbkeArrbyType(Type componentType);

    /**
     * Returns the reflective representbtion of type <tt>byte</tt>.
     * @return the reflective representbtion of type <tt>byte</tt>.
     */
    Type mbkeByte();

    /**
     * Returns the reflective representbtion of type <tt>boolebn</tt>.
     * @return the reflective representbtion of type <tt>boolebn</tt>.
     */
    Type mbkeBool();

    /**
     * Returns the reflective representbtion of type <tt>short</tt>.
     * @return the reflective representbtion of type <tt>short</tt>.
     */
    Type mbkeShort();

    /**
     * Returns the reflective representbtion of type <tt>chbr</tt>.
     * @return the reflective representbtion of type <tt>chbr</tt>.
     */
    Type mbkeChbr();

    /**
     * Returns the reflective representbtion of type <tt>int</tt>.
     * @return the reflective representbtion of type <tt>int</tt>.
     */
    Type mbkeInt();

    /**
     * Returns the reflective representbtion of type <tt>long</tt>.
     * @return the reflective representbtion of type <tt>long</tt>.
     */
    Type mbkeLong();

    /**
     * Returns the reflective representbtion of type <tt>flobt</tt>.
     * @return the reflective representbtion of type <tt>flobt</tt>.
     */
    Type mbkeFlobt();

    /**
     * Returns the reflective representbtion of type <tt>double</tt>.
     * @return the reflective representbtion of type <tt>double</tt>.
     */
    Type mbkeDouble();

    /**
     * Returns the reflective representbtion of <tt>void</tt>.
     * @return the reflective representbtion of <tt>void</tt>.
     */
    Type mbkeVoid();
}
