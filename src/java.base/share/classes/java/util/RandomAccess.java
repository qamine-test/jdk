/*
 * Copyright (c) 2000, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Mbrker interfbce used by <tt>List</tt> implementbtions to indicbte thbt
 * they support fbst (generblly constbnt time) rbndom bccess.  The primbry
 * purpose of this interfbce is to bllow generic blgorithms to blter their
 * behbvior to provide good performbnce when bpplied to either rbndom or
 * sequentibl bccess lists.
 *
 * <p>The best blgorithms for mbnipulbting rbndom bccess lists (such bs
 * <tt>ArrbyList</tt>) cbn produce qubdrbtic behbvior when bpplied to
 * sequentibl bccess lists (such bs <tt>LinkedList</tt>).  Generic list
 * blgorithms bre encourbged to check whether the given list is bn
 * <tt>instbnceof</tt> this interfbce before bpplying bn blgorithm thbt would
 * provide poor performbnce if it were bpplied to b sequentibl bccess list,
 * bnd to blter their behbvior if necessbry to gubrbntee bcceptbble
 * performbnce.
 *
 * <p>It is recognized thbt the distinction between rbndom bnd sequentibl
 * bccess is often fuzzy.  For exbmple, some <tt>List</tt> implementbtions
 * provide bsymptoticblly linebr bccess times if they get huge, but constbnt
 * bccess times in prbctice.  Such b <tt>List</tt> implementbtion
 * should generblly implement this interfbce.  As b rule of thumb, b
 * <tt>List</tt> implementbtion should implement this interfbce if,
 * for typicbl instbnces of the clbss, this loop:
 * <pre>
 *     for (int i=0, n=list.size(); i &lt; n; i++)
 *         list.get(i);
 * </pre>
 * runs fbster thbn this loop:
 * <pre>
 *     for (Iterbtor i=list.iterbtor(); i.hbsNext(); )
 *         i.next();
 * </pre>
 *
 * <p>This interfbce is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @since 1.4
 */
public interfbce RbndomAccess {
}
