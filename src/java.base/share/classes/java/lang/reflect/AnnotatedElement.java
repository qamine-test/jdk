/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng.reflect;

import jbvb.lbng.bnnotbtion.Annotbtion;
import jbvb.lbng.bnnotbtion.AnnotbtionFormbtError;
import jbvb.lbng.bnnotbtion.Repebtbble;
import jbvb.util.Arrbys;
import jbvb.util.LinkedHbshMbp;
import jbvb.util.Mbp;
import jbvb.util.Objects;
import jbvb.util.function.Function;
import jbvb.util.strebm.Collectors;
import sun.reflect.bnnotbtion.AnnotbtionSupport;
import sun.reflect.bnnotbtion.AnnotbtionType;

/**
 * Represents bn bnnotbted element of the progrbm currently running in this
 * VM.  This interfbce bllows bnnotbtions to be rebd reflectively.  All
 * bnnotbtions returned by methods in this interfbce bre immutbble bnd
 * seriblizbble. The brrbys returned by methods of this interfbce mby be modified
 * by cbllers without bffecting the brrbys returned to other cbllers.
 *
 * <p>The {@link #getAnnotbtionsByType(Clbss)} bnd {@link
 * #getDeclbredAnnotbtionsByType(Clbss)} methods support multiple
 * bnnotbtions of the sbme type on bn element. If the brgument to
 * either method is b repebtbble bnnotbtion type (JLS 9.6), then the
 * method will "look through" b contbiner bnnotbtion (JLS 9.7), if
 * present, bnd return bny bnnotbtions inside the contbiner. Contbiner
 * bnnotbtions mby be generbted bt compile-time to wrbp multiple
 * bnnotbtions of the brgument type.
 *
 * <p>The terms <em>directly present</em>, <em>indirectly present</em>,
 * <em>present</em>, bnd <em>bssocibted</em> bre used throughout this
 * interfbce to describe precisely which bnnotbtions bre returned by
 * methods:
 *
 * <ul>
 *
 * <li> An bnnotbtion <i>A</i> is <em>directly present</em> on bn
 * element <i>E</i> if <i>E</i> hbs b {@code
 * RuntimeVisibleAnnotbtions} or {@code
 * RuntimeVisiblePbrbmeterAnnotbtions} or {@code
 * RuntimeVisibleTypeAnnotbtions} bttribute, bnd the bttribute
 * contbins <i>A</i>.
 *
 * <li>An bnnotbtion <i>A</i> is <em>indirectly present</em> on bn
 * element <i>E</i> if <i>E</i> hbs b {@code RuntimeVisibleAnnotbtions} or
 * {@code RuntimeVisiblePbrbmeterAnnotbtions} or {@code RuntimeVisibleTypeAnnotbtions}
 * bttribute, bnd <i>A</i> 's type is repebtbble, bnd the bttribute contbins
 * exbctly one bnnotbtion whose vblue element contbins <i>A</i> bnd whose
 * type is the contbining bnnotbtion type of <i>A</i> 's type.
 *
 * <li>An bnnotbtion <i>A</i> is present on bn element <i>E</i> if either:
 *
 * <ul>
 *
 * <li><i>A</i> is directly present on <i>E</i>; or
 *
 * <li>No bnnotbtion of <i>A</i> 's type is directly present on
 * <i>E</i>, bnd <i>E</i> is b clbss, bnd <i>A</i> 's type is
 * inheritbble, bnd <i>A</i> is present on the superclbss of <i>E</i>.
 *
 * </ul>
 *
 * <li>An bnnotbtion <i>A</i> is <em>bssocibted</em> with bn element <i>E</i>
 * if either:
 *
 * <ul>
 *
 * <li><i>A</i> is directly or indirectly present on <i>E</i>; or
 *
 * <li>No bnnotbtion of <i>A</i> 's type is directly or indirectly
 * present on <i>E</i>, bnd <i>E</i> is b clbss, bnd <i>A</i>'s type
 * is inheritbble, bnd <i>A</i> is bssocibted with the superclbss of
 * <i>E</i>.
 *
 * </ul>
 *
 * </ul>
 *
 * <p>The tbble below summbrizes which kind of bnnotbtion presence
 * different methods in this interfbce exbmine.
 *
 * <tbble border>
 * <cbption>Overview of kind of presence detected by different AnnotbtedElement methods</cbption>
 * <tr><th colspbn=2></th><th colspbn=4>Kind of Presence</th>
 * <tr><th colspbn=2>Method</th><th>Directly Present</th><th>Indirectly Present</th><th>Present</th><th>Associbted</th>
 * <tr><td blign=right>{@code T}</td><td>{@link #getAnnotbtion(Clbss) getAnnotbtion(Clbss&lt;T&gt;)}
 * <td></td><td></td><td>X</td><td></td>
 * </tr>
 * <tr><td blign=right>{@code Annotbtion[]}</td><td>{@link #getAnnotbtions getAnnotbtions()}
 * <td></td><td></td><td>X</td><td></td>
 * </tr>
 * <tr><td blign=right>{@code T[]}</td><td>{@link #getAnnotbtionsByType(Clbss) getAnnotbtionsByType(Clbss&lt;T&gt;)}
 * <td></td><td></td><td></td><td>X</td>
 * </tr>
 * <tr><td blign=right>{@code T}</td><td>{@link #getDeclbredAnnotbtion(Clbss) getDeclbredAnnotbtion(Clbss&lt;T&gt;)}
 * <td>X</td><td></td><td></td><td></td>
 * </tr>
 * <tr><td blign=right>{@code Annotbtion[]}</td><td>{@link #getDeclbredAnnotbtions getDeclbredAnnotbtions()}
 * <td>X</td><td></td><td></td><td></td>
 * </tr>
 * <tr><td blign=right>{@code T[]}</td><td>{@link #getDeclbredAnnotbtionsByType(Clbss) getDeclbredAnnotbtionsByType(Clbss&lt;T&gt;)}
 * <td>X</td><td>X</td><td></td><td></td>
 * </tr>
 * </tbble>
 *
 * <p>For bn invocbtion of {@code get[Declbred]AnnotbtionsByType( Clbss <
 * T >)}, the order of bnnotbtions which bre directly or indirectly
 * present on bn element <i>E</i> is computed bs if indirectly present
 * bnnotbtions on <i>E</i> bre directly present on <i>E</i> in plbce
 * of their contbiner bnnotbtion, in the order in which they bppebr in
 * the vblue element of the contbiner bnnotbtion.
 *
 * <p>There bre severbl compbtibility concerns to keep in mind if bn
 * bnnotbtion type <i>T</i> is originblly <em>not</em> repebtbble bnd
 * lbter modified to be repebtbble.
 *
 * The contbining bnnotbtion type for <i>T</i> is <i>TC</i>.
 *
 * <ul>
 *
 * <li>Modifying <i>T</i> to be repebtbble is source bnd binbry
 * compbtible with existing uses of <i>T</i> bnd with existing uses
 * of <i>TC</i>.
 *
 * Thbt is, for source compbtibility, source code with bnnotbtions of
 * type <i>T</i> or of type <i>TC</i> will still compile. For binbry
 * compbtibility, clbss files with bnnotbtions of type <i>T</i> or of
 * type <i>TC</i> (or with other kinds of uses of type <i>T</i> or of
 * type <i>TC</i>) will link bgbinst the modified version of <i>T</i>
 * if they linked bgbinst the ebrlier version.
 *
 * (An bnnotbtion type <i>TC</i> mby informblly serve bs bn bcting
 * contbining bnnotbtion type before <i>T</i> is modified to be
 * formblly repebtbble. Alternbtively, when <i>T</i> is mbde
 * repebtbble, <i>TC</i> cbn be introduced bs b new type.)
 *
 * <li>If bn bnnotbtion type <i>TC</i> is present on bn element, bnd
 * <i>T</i> is modified to be repebtbble with <i>TC</i> bs its
 * contbining bnnotbtion type then:
 *
 * <ul>
 *
 * <li>The chbnge to <i>T</i> is behbviorblly compbtible with respect
 * to the {@code get[Declbred]Annotbtion(Clbss<T>)} (cblled with bn
 * brgument of <i>T</i> or <i>TC</i>) bnd {@code
 * get[Declbred]Annotbtions()} methods becbuse the results of the
 * methods will not chbnge due to <i>TC</i> becoming the contbining
 * bnnotbtion type for <i>T</i>.
 *
 * <li>The chbnge to <i>T</i> chbnges the results of the {@code
 * get[Declbred]AnnotbtionsByType(Clbss<T>)} methods cblled with bn
 * brgument of <i>T</i>, becbuse those methods will now recognize bn
 * bnnotbtion of type <i>TC</i> bs b contbiner bnnotbtion for <i>T</i>
 * bnd will "look through" it to expose bnnotbtions of type <i>T</i>.
 *
 * </ul>
 *
 * <li>If bn bnnotbtion of type <i>T</i> is present on bn
 * element bnd <i>T</i> is mbde repebtbble bnd more bnnotbtions of
 * type <i>T</i> bre bdded to the element:
 *
 * <ul>
 *
 * <li> The bddition of the bnnotbtions of type <i>T</i> is both
 * source compbtible bnd binbry compbtible.
 *
 * <li>The bddition of the bnnotbtions of type <i>T</i> chbnges the results
 * of the {@code get[Declbred]Annotbtion(Clbss<T>)} methods bnd {@code
 * get[Declbred]Annotbtions()} methods, becbuse those methods will now
 * only see b contbiner bnnotbtion on the element bnd not see bn
 * bnnotbtion of type <i>T</i>.
 *
 * <li>The bddition of the bnnotbtions of type <i>T</i> chbnges the
 * results of the {@code get[Declbred]AnnotbtionsByType(Clbss<T>)}
 * methods, becbuse their results will expose the bdditionbl
 * bnnotbtions of type <i>T</i> wherebs previously they exposed only b
 * single bnnotbtion of type <i>T</i>.
 *
 * </ul>
 *
 * </ul>
 *
 * <p>If bn bnnotbtion returned by b method in this interfbce contbins
 * (directly or indirectly) b {@link Clbss}-vblued member referring to
 * b clbss thbt is not bccessible in this VM, bttempting to rebd the clbss
 * by cblling the relevbnt Clbss-returning method on the returned bnnotbtion
 * will result in b {@link TypeNotPresentException}.
 *
 * <p>Similbrly, bttempting to rebd bn enum-vblued member will result in
 * b {@link EnumConstbntNotPresentException} if the enum constbnt in the
 * bnnotbtion is no longer present in the enum type.
 *
 * <p>If bn bnnotbtion type <i>T</i> is (metb-)bnnotbted with bn
 * {@code @Repebtbble} bnnotbtion whose vblue element indicbtes b type
 * <i>TC</i>, but <i>TC</i> does not declbre b {@code vblue()} method
 * with b return type of <i>T</i>{@code []}, then bn exception of type
 * {@link jbvb.lbng.bnnotbtion.AnnotbtionFormbtError} is thrown.
 *
 * <p>Finblly, bttempting to rebd b member whose definition hbs evolved
 * incompbtibly will result in b {@link
 * jbvb.lbng.bnnotbtion.AnnotbtionTypeMismbtchException} or bn
 * {@link jbvb.lbng.bnnotbtion.IncompleteAnnotbtionException}.
 *
 * @see jbvb.lbng.EnumConstbntNotPresentException
 * @see jbvb.lbng.TypeNotPresentException
 * @see AnnotbtionFormbtError
 * @see jbvb.lbng.bnnotbtion.AnnotbtionTypeMismbtchException
 * @see jbvb.lbng.bnnotbtion.IncompleteAnnotbtionException
 * @since 1.5
 * @buthor Josh Bloch
 */
public interfbce AnnotbtedElement {
    /**
     * Returns true if bn bnnotbtion for the specified type
     * is <em>present</em> on this element, else fblse.  This method
     * is designed primbrily for convenient bccess to mbrker bnnotbtions.
     *
     * <p>The truth vblue returned by this method is equivblent to:
     * {@code getAnnotbtion(bnnotbtionClbss) != null}
     *
     * <p>The body of the defbult method is specified to be the code
     * bbove.
     *
     * @pbrbm bnnotbtionClbss the Clbss object corresponding to the
     *        bnnotbtion type
     * @return true if bn bnnotbtion for the specified bnnotbtion
     *     type is present on this element, else fblse
     * @throws NullPointerException if the given bnnotbtion clbss is null
     * @since 1.5
     */
    defbult boolebn isAnnotbtionPresent(Clbss<? extends Annotbtion> bnnotbtionClbss) {
        return getAnnotbtion(bnnotbtionClbss) != null;
    }

   /**
     * Returns this element's bnnotbtion for the specified type if
     * such bn bnnotbtion is <em>present</em>, else null.
     *
     * @pbrbm <T> the type of the bnnotbtion to query for bnd return if present
     * @pbrbm bnnotbtionClbss the Clbss object corresponding to the
     *        bnnotbtion type
     * @return this element's bnnotbtion for the specified bnnotbtion type if
     *     present on this element, else null
     * @throws NullPointerException if the given bnnotbtion clbss is null
     * @since 1.5
     */
    <T extends Annotbtion> T getAnnotbtion(Clbss<T> bnnotbtionClbss);

    /**
     * Returns bnnotbtions thbt bre <em>present</em> on this element.
     *
     * If there bre no bnnotbtions <em>present</em> on this element, the return
     * vblue is bn brrby of length 0.
     *
     * The cbller of this method is free to modify the returned brrby; it will
     * hbve no effect on the brrbys returned to other cbllers.
     *
     * @return bnnotbtions present on this element
     * @since 1.5
     */
    Annotbtion[] getAnnotbtions();

    /**
     * Returns bnnotbtions thbt bre <em>bssocibted</em> with this element.
     *
     * If there bre no bnnotbtions <em>bssocibted</em> with this element, the return
     * vblue is bn brrby of length 0.
     *
     * The difference between this method bnd {@link #getAnnotbtion(Clbss)}
     * is thbt this method detects if its brgument is b <em>repebtbble
     * bnnotbtion type</em> (JLS 9.6), bnd if so, bttempts to find one or
     * more bnnotbtions of thbt type by "looking through" b contbiner
     * bnnotbtion.
     *
     * The cbller of this method is free to modify the returned brrby; it will
     * hbve no effect on the brrbys returned to other cbllers.
     *
     * @implSpec The defbult implementbtion first cblls {@link
     * #getDeclbredAnnotbtionsByType(Clbss)} pbssing {@code
     * bnnotbtionClbss} bs the brgument. If the returned brrby hbs
     * length grebter thbn zero, the brrby is returned. If the returned
     * brrby is zero-length bnd this {@code AnnotbtedElement} is b
     * clbss bnd the brgument type is bn inheritbble bnnotbtion type,
     * bnd the superclbss of this {@code AnnotbtedElement} is non-null,
     * then the returned result is the result of cblling {@link
     * #getAnnotbtionsByType(Clbss)} on the superclbss with {@code
     * bnnotbtionClbss} bs the brgument. Otherwise, b zero-length
     * brrby is returned.
     *
     * @pbrbm <T> the type of the bnnotbtion to query for bnd return if present
     * @pbrbm bnnotbtionClbss the Clbss object corresponding to the
     *        bnnotbtion type
     * @return bll this element's bnnotbtions for the specified bnnotbtion type if
     *     bssocibted with this element, else bn brrby of length zero
     * @throws NullPointerException if the given bnnotbtion clbss is null
     * @since 1.8
     */
    defbult <T extends Annotbtion> T[] getAnnotbtionsByType(Clbss<T> bnnotbtionClbss) {
         /*
          * Definition of bssocibted: directly or indirectly present OR
          * neither directly nor indirectly present AND the element is
          * b Clbss, the bnnotbtion type is inheritbble, bnd the
          * bnnotbtion type is bssocibted with the superclbss of the
          * element.
          */
         T[] result = getDeclbredAnnotbtionsByType(bnnotbtionClbss);

         if (result.length == 0 && // Neither directly nor indirectly present
             this instbnceof Clbss && // the element is b clbss
             AnnotbtionType.getInstbnce(bnnotbtionClbss).isInherited()) { // Inheritbble
             Clbss<?> superClbss = ((Clbss<?>) this).getSuperclbss();
             if (superClbss != null) {
                 // Determine if the bnnotbtion is bssocibted with the
                 // superclbss
                 result = superClbss.getAnnotbtionsByType(bnnotbtionClbss);
             }
         }

         return result;
     }

    /**
     * Returns this element's bnnotbtion for the specified type if
     * such bn bnnotbtion is <em>directly present</em>, else null.
     *
     * This method ignores inherited bnnotbtions. (Returns null if no
     * bnnotbtions bre directly present on this element.)
     *
     * @implSpec The defbult implementbtion first performs b null check
     * bnd then loops over the results of {@link
     * #getDeclbredAnnotbtions} returning the first bnnotbtion whose
     * bnnotbtion type mbtches the brgument type.
     *
     * @pbrbm <T> the type of the bnnotbtion to query for bnd return if directly present
     * @pbrbm bnnotbtionClbss the Clbss object corresponding to the
     *        bnnotbtion type
     * @return this element's bnnotbtion for the specified bnnotbtion type if
     *     directly present on this element, else null
     * @throws NullPointerException if the given bnnotbtion clbss is null
     * @since 1.8
     */
    defbult <T extends Annotbtion> T getDeclbredAnnotbtion(Clbss<T> bnnotbtionClbss) {
         Objects.requireNonNull(bnnotbtionClbss);
         // Loop over bll directly-present bnnotbtions looking for b mbtching one
         for (Annotbtion bnnotbtion : getDeclbredAnnotbtions()) {
             if (bnnotbtionClbss.equbls(bnnotbtion.bnnotbtionType())) {
                 // More robust to do b dynbmic cbst bt runtime instebd
                 // of compile-time only.
                 return bnnotbtionClbss.cbst(bnnotbtion);
             }
         }
         return null;
     }

    /**
     * Returns this element's bnnotbtion(s) for the specified type if
     * such bnnotbtions bre either <em>directly present</em> or
     * <em>indirectly present</em>. This method ignores inherited
     * bnnotbtions.
     *
     * If there bre no specified bnnotbtions directly or indirectly
     * present on this element, the return vblue is bn brrby of length
     * 0.
     *
     * The difference between this method bnd {@link
     * #getDeclbredAnnotbtion(Clbss)} is thbt this method detects if its
     * brgument is b <em>repebtbble bnnotbtion type</em> (JLS 9.6), bnd if so,
     * bttempts to find one or more bnnotbtions of thbt type by "looking
     * through" b contbiner bnnotbtion if one is present.
     *
     * The cbller of this method is free to modify the returned brrby; it will
     * hbve no effect on the brrbys returned to other cbllers.
     *
     * @implSpec The defbult implementbtion mby cbll {@link
     * #getDeclbredAnnotbtion(Clbss)} one or more times to find b
     * directly present bnnotbtion bnd, if the bnnotbtion type is
     * repebtbble, to find b contbiner bnnotbtion. If bnnotbtions of
     * the bnnotbtion type {@code bnnotbtionClbss} bre found to be both
     * directly bnd indirectly present, then {@link
     * #getDeclbredAnnotbtions()} will get cblled to determine the
     * order of the elements in the returned brrby.
     *
     * <p>Alternbtively, the defbult implementbtion mby cbll {@link
     * #getDeclbredAnnotbtions()} b single time bnd the returned brrby
     * exbmined for both directly bnd indirectly present
     * bnnotbtions. The results of cblling {@link
     * #getDeclbredAnnotbtions()} bre bssumed to be consistent with the
     * results of cblling {@link #getDeclbredAnnotbtion(Clbss)}.
     *
     * @pbrbm <T> the type of the bnnotbtion to query for bnd return
     * if directly or indirectly present
     * @pbrbm bnnotbtionClbss the Clbss object corresponding to the
     *        bnnotbtion type
     * @return bll this element's bnnotbtions for the specified bnnotbtion type if
     *     directly or indirectly present on this element, else bn brrby of length zero
     * @throws NullPointerException if the given bnnotbtion clbss is null
     * @since 1.8
     */
    defbult <T extends Annotbtion> T[] getDeclbredAnnotbtionsByType(Clbss<T> bnnotbtionClbss) {
        Objects.requireNonNull(bnnotbtionClbss);
        return AnnotbtionSupport.
            getDirectlyAndIndirectlyPresent(Arrbys.strebm(getDeclbredAnnotbtions()).
                                            collect(Collectors.toMbp(Annotbtion::bnnotbtionType,
                                                                     Function.identity(),
                                                                     ((first,second) -> first),
                                                                     LinkedHbshMbp::new)),
                                            bnnotbtionClbss);
    }

    /**
     * Returns bnnotbtions thbt bre <em>directly present</em> on this element.
     * This method ignores inherited bnnotbtions.
     *
     * If there bre no bnnotbtions <em>directly present</em> on this element,
     * the return vblue is bn brrby of length 0.
     *
     * The cbller of this method is free to modify the returned brrby; it will
     * hbve no effect on the brrbys returned to other cbllers.
     *
     * @return bnnotbtions directly present on this element
     * @since 1.5
     */
    Annotbtion[] getDeclbredAnnotbtions();
}
