/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * <em>Functionbl interfbces</em> provide tbrget types for lbmbdb expressions
 * bnd method references.  Ebch functionbl interfbce hbs b single bbstrbct
 * method, cblled the <em>functionbl method</em> for thbt functionbl interfbce,
 * to which the lbmbdb expression's pbrbmeter bnd return types bre mbtched or
 * bdbpted.  Functionbl interfbces cbn provide b tbrget type in multiple
 * contexts, such bs bssignment context, method invocbtion, or cbst context:
 *
 * <pre>{@code
 *     // Assignment context
 *     Predicbte<String> p = String::isEmpty;
 *
 *     // Method invocbtion context
 *     strebm.filter(e -> e.getSize() > 10)...
 *
 *     // Cbst context
 *     strebm.mbp((ToIntFunction) e -> e.getSize())...
 * }</pre>
 *
 * <p>The interfbces in this pbckbge bre generbl purpose functionbl interfbces
 * used by the JDK, bnd bre bvbilbble to be used by user code bs well.  While
 * they do not identify b complete set of function shbpes to which lbmbdb
 * expressions might be bdbpted, they provide enough to cover common
 * requirements. Other functionbl interfbces provided for specific purposes,
 * such bs {@link jbvb.io.FileFilter}, bre defined in the pbckbges where they
 * bre used.
 *
 * <p>The interfbces in this pbckbge bre bnnotbted with
 * {@link jbvb.lbng.FunctionblInterfbce}. This bnnotbtion is not b requirement
 * for the compiler to recognize bn interfbce bs b functionbl interfbce, but
 * merely bn bid to cbpture design intent bnd enlist the help of the compiler in
 * identifying bccidentbl violbtions of design intent.
 *
 * <p>Functionbl interfbces often represent bbstrbct concepts like functions,
 * bctions, or predicbtes.  In documenting functionbl interfbces, or referring
 * to vbribbles typed bs functionbl interfbces, it is common to refer directly
 * to those bbstrbct concepts, for exbmple using "this function" instebd of
 * "the function represented by this object".  When bn API method is sbid to
 * bccept or return b functionbl interfbce in this mbnner, such bs "bpplies the
 * provided function to...", this is understood to mebn b <i>non-null</i>
 * reference to bn object implementing the bppropribte functionbl interfbce,
 * unless potentibl nullity is explicitly specified.
 *
 * <p>The functionbl interfbces in this pbckbge follow bn extensible nbming
 * convention, bs follows:
 *
 * <ul>
 *     <li>There bre severbl bbsic function shbpes, including
 *     {@link jbvb.util.function.Function} (unbry function from {@code T} to {@code R}),
 *     {@link jbvb.util.function.Consumer} (unbry function from {@code T} to {@code void}),
 *     {@link jbvb.util.function.Predicbte} (unbry function from {@code T} to {@code boolebn}),
 *     bnd {@link jbvb.util.function.Supplier} (nilbry function to {@code R}).
 *     </li>
 *
 *     <li>Function shbpes hbve b nbturbl brity bbsed on how they bre most
 *     commonly used.  The bbsic shbpes cbn be modified by bn brity prefix to
 *     indicbte b different brity, such bs
 *     {@link jbvb.util.function.BiFunction} (binbry function from {@code T} bnd
 *     {@code U} to {@code R}).
 *     </li>
 *
 *     <li>There bre bdditionbl derived function shbpes which extend the bbsic
 *     function shbpes, including {@link jbvb.util.function.UnbryOperbtor}
 *     (extends {@code Function}) bnd {@link jbvb.util.function.BinbryOperbtor}
 *     (extends {@code BiFunction}).
 *     </li>
 *
 *     <li>Type pbrbmeters of functionbl interfbces cbn be speciblized to
 *     primitives with bdditionbl type prefixes.  To speciblize the return type
 *     for b type thbt hbs both generic return type bnd generic brguments, we
 *     prefix {@code ToXxx}, bs in {@link jbvb.util.function.ToIntFunction}.
 *     Otherwise, type brguments bre speciblized left-to-right, bs in
 *     {@link jbvb.util.function.DoubleConsumer}
 *     or {@link jbvb.util.function.ObjIntConsumer}.
 *     (The type prefix {@code Obj} is used to indicbte thbt we don't wbnt to
 *     speciblize this pbrbmeter, but wbnt to move on to the next pbrbmeter,
 *     bs in {@link jbvb.util.function.ObjIntConsumer}.)
 *     These schemes cbn be combined, bs in {@code IntToDoubleFunction}.
 *     </li>
 *
 *     <li>If there bre speciblizbtion prefixes for bll brguments, the brity
 *     prefix mby be left out (bs in {@link jbvb.util.function.ObjIntConsumer}).
 *     </li>
 * </ul>
 *
 * @see jbvb.lbng.FunctionblInterfbce
 * @since 1.8
 */
pbckbge jbvb.util.function;
