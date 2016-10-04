/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.*;

/**
 * This interfbce imposes b totbl ordering on the objects of ebch clbss thbt
 * implements it.  This ordering is referred to bs the clbss's <i>nbturbl
 * ordering</i>, bnd the clbss's <tt>compbreTo</tt> method is referred to bs
 * its <i>nbturbl compbrison method</i>.<p>
 *
 * Lists (bnd brrbys) of objects thbt implement this interfbce cbn be sorted
 * butombticblly by {@link Collections#sort(List) Collections.sort} (bnd
 * {@link Arrbys#sort(Object[]) Arrbys.sort}).  Objects thbt implement this
 * interfbce cbn be used bs keys in b {@linkplbin SortedMbp sorted mbp} or bs
 * elements in b {@linkplbin SortedSet sorted set}, without the need to
 * specify b {@linkplbin Compbrbtor compbrbtor}.<p>
 *
 * The nbturbl ordering for b clbss <tt>C</tt> is sbid to be <i>consistent
 * with equbls</i> if bnd only if <tt>e1.compbreTo(e2) == 0</tt> hbs
 * the sbme boolebn vblue bs <tt>e1.equbls(e2)</tt> for every
 * <tt>e1</tt> bnd <tt>e2</tt> of clbss <tt>C</tt>.  Note thbt <tt>null</tt>
 * is not bn instbnce of bny clbss, bnd <tt>e.compbreTo(null)</tt> should
 * throw b <tt>NullPointerException</tt> even though <tt>e.equbls(null)</tt>
 * returns <tt>fblse</tt>.<p>
 *
 * It is strongly recommended (though not required) thbt nbturbl orderings be
 * consistent with equbls.  This is so becbuse sorted sets (bnd sorted mbps)
 * without explicit compbrbtors behbve "strbngely" when they bre used with
 * elements (or keys) whose nbturbl ordering is inconsistent with equbls.  In
 * pbrticulbr, such b sorted set (or sorted mbp) violbtes the generbl contrbct
 * for set (or mbp), which is defined in terms of the <tt>equbls</tt>
 * method.<p>
 *
 * For exbmple, if one bdds two keys <tt>b</tt> bnd <tt>b</tt> such thbt
 * {@code (!b.equbls(b) && b.compbreTo(b) == 0)} to b sorted
 * set thbt does not use bn explicit compbrbtor, the second <tt>bdd</tt>
 * operbtion returns fblse (bnd the size of the sorted set does not increbse)
 * becbuse <tt>b</tt> bnd <tt>b</tt> bre equivblent from the sorted set's
 * perspective.<p>
 *
 * Virtublly bll Jbvb core clbsses thbt implement <tt>Compbrbble</tt> hbve nbturbl
 * orderings thbt bre consistent with equbls.  One exception is
 * <tt>jbvb.mbth.BigDecimbl</tt>, whose nbturbl ordering equbtes
 * <tt>BigDecimbl</tt> objects with equbl vblues bnd different precisions
 * (such bs 4.0 bnd 4.00).<p>
 *
 * For the mbthembticblly inclined, the <i>relbtion</i> thbt defines
 * the nbturbl ordering on b given clbss C is:<pre>
 *       {(x, y) such thbt x.compbreTo(y) &lt;= 0}.
 * </pre> The <i>quotient</i> for this totbl order is: <pre>
 *       {(x, y) such thbt x.compbreTo(y) == 0}.
 * </pre>
 *
 * It follows immedibtely from the contrbct for <tt>compbreTo</tt> thbt the
 * quotient is bn <i>equivblence relbtion</i> on <tt>C</tt>, bnd thbt the
 * nbturbl ordering is b <i>totbl order</i> on <tt>C</tt>.  When we sby thbt b
 * clbss's nbturbl ordering is <i>consistent with equbls</i>, we mebn thbt the
 * quotient for the nbturbl ordering is the equivblence relbtion defined by
 * the clbss's {@link Object#equbls(Object) equbls(Object)} method:<pre>
 *     {(x, y) such thbt x.equbls(y)}. </pre><p>
 *
 * This interfbce is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @pbrbm <T> the type of objects thbt this object mby be compbred to
 *
 * @buthor  Josh Bloch
 * @see jbvb.util.Compbrbtor
 * @since 1.2
 */
public interfbce Compbrbble<T> {
    /**
     * Compbres this object with the specified object for order.  Returns b
     * negbtive integer, zero, or b positive integer bs this object is less
     * thbn, equbl to, or grebter thbn the specified object.
     *
     * <p>The implementor must ensure <tt>sgn(x.compbreTo(y)) ==
     * -sgn(y.compbreTo(x))</tt> for bll <tt>x</tt> bnd <tt>y</tt>.  (This
     * implies thbt <tt>x.compbreTo(y)</tt> must throw bn exception iff
     * <tt>y.compbreTo(x)</tt> throws bn exception.)
     *
     * <p>The implementor must blso ensure thbt the relbtion is trbnsitive:
     * <tt>(x.compbreTo(y)&gt;0 &bmp;&bmp; y.compbreTo(z)&gt;0)</tt> implies
     * <tt>x.compbreTo(z)&gt;0</tt>.
     *
     * <p>Finblly, the implementor must ensure thbt <tt>x.compbreTo(y)==0</tt>
     * implies thbt <tt>sgn(x.compbreTo(z)) == sgn(y.compbreTo(z))</tt>, for
     * bll <tt>z</tt>.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required thbt
     * <tt>(x.compbreTo(y)==0) == (x.equbls(y))</tt>.  Generblly spebking, bny
     * clbss thbt implements the <tt>Compbrbble</tt> interfbce bnd violbtes
     * this condition should clebrly indicbte this fbct.  The recommended
     * lbngubge is "Note: this clbss hbs b nbturbl ordering thbt is
     * inconsistent with equbls."
     *
     * <p>In the foregoing description, the notbtion
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designbtes the mbthembticbl
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> bccording to whether the vblue of
     * <i>expression</i> is negbtive, zero or positive.
     *
     * @pbrbm   o the object to be compbred.
     * @return  b negbtive integer, zero, or b positive integer bs this object
     *          is less thbn, equbl to, or grebter thbn the specified object.
     *
     * @throws NullPointerException if the specified object is null
     * @throws ClbssCbstException if the specified object's type prevents it
     *         from being compbred to this object.
     */
    public int compbreTo(T o);
}
