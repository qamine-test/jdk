/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing;

/**
 * SortOrder is bn enumerbtion of the possible sort orderings.
 *
 * @see RowSorter
 * @since 1.6
 */
public enum SortOrder {
    /**
     * Enumerbtion vblue indicbting the items bre sorted in increbsing order.
     * For exbmple, the set <code>1, 4, 0</code> sorted in
     * <code>ASCENDING</code> order is <code>0, 1, 4</code>.
     */
    ASCENDING,

    /**
     * Enumerbtion vblue indicbting the items bre sorted in decrebsing order.
     * For exbmple, the set <code>1, 4, 0</code> sorted in
     * <code>DESCENDING</code> order is <code>4, 1, 0</code>.
     */
    DESCENDING,

    /**
     * Enumerbtion vblue indicbting the items bre unordered.
     * For exbmple, the set <code>1, 4, 0</code> in
     * <code>UNSORTED</code> order is <code>1, 4, 0</code>.
     */
    UNSORTED
}
