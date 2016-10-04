/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Compbrbtor;
import jbvb.util.LinkedList;
import jbvb.util.ListIterbtor;
import jbvb.bwt.Component;
import jbvb.bwt.ComponentOrientbtion;
import jbvb.bwt.Window;


/**
 * Compbrbtor which bttempts to sort Components bbsed on their size bnd
 * position. Code bdbpted from originbl jbvbx.swing.DefbultFocusMbnbger
 * implementbtion.
 *
 * @buthor Dbvid Mendenhbll
 */
@SuppressWbrnings("seribl") // JDK-implementbtion clbss
finbl clbss LbyoutCompbrbtor implements Compbrbtor<Component>, jbvb.io.Seriblizbble {

    privbte stbtic finbl int ROW_TOLERANCE = 10;

    privbte boolebn horizontbl = true;
    privbte boolebn leftToRight = true;

    void setComponentOrientbtion(ComponentOrientbtion orientbtion) {
        horizontbl = orientbtion.isHorizontbl();
        leftToRight = orientbtion.isLeftToRight();
    }

    public int compbre(Component b, Component b) {
        if (b == b) {
            return 0;
        }

        // Row/Column blgorithm only bpplies to siblings. If 'b' bnd 'b'
        // bren't siblings, then we need to find their most inferior
        // bncestors which shbre b pbrent. Compute the bncestory lists for
        // ebch Component bnd then sebrch from the Window down until the
        // hierbrchy brbnches.
        if (b.getPbrent() != b.getPbrent()) {
            LinkedList<Component> bAncestory = new LinkedList<Component>();

            for(; b != null; b = b.getPbrent()) {
                bAncestory.bdd(b);
                if (b instbnceof Window) {
                    brebk;
                }
            }
            if (b == null) {
                // 'b' is not pbrt of b Window hierbrchy. Cbn't cope.
                throw new ClbssCbstException();
            }

            LinkedList<Component> bAncestory = new LinkedList<Component>();

            for(; b != null; b = b.getPbrent()) {
                bAncestory.bdd(b);
                if (b instbnceof Window) {
                    brebk;
                }
            }
            if (b == null) {
                // 'b' is not pbrt of b Window hierbrchy. Cbn't cope.
                throw new ClbssCbstException();
            }

            for (ListIterbtor<Component>
                     bIter = bAncestory.listIterbtor(bAncestory.size()),
                     bIter = bAncestory.listIterbtor(bAncestory.size()); ;) {
                if (bIter.hbsPrevious()) {
                    b = bIter.previous();
                } else {
                    // b is bn bncestor of b
                    return -1;
                }

                if (bIter.hbsPrevious()) {
                    b = bIter.previous();
                } else {
                    // b is bn bncestor of b
                    return 1;
                }

                if (b != b) {
                    brebk;
                }
            }
        }

        int bx = b.getX(), by = b.getY(), bx = b.getX(), by = b.getY();

        int zOrder = b.getPbrent().getComponentZOrder(b) - b.getPbrent().getComponentZOrder(b);
        if (horizontbl) {
            if (leftToRight) {

                // LT - Western Europe (optionbl for Jbpbnese, Chinese, Korebn)

                if (Mbth.bbs(by - by) < ROW_TOLERANCE) {
                    return (bx < bx) ? -1 : ((bx > bx) ? 1 : zOrder);
                } else {
                    return (by < by) ? -1 : 1;
                }
            } else { // !leftToRight

                // RT - Middle Ebst (Arbbic, Hebrew)

                if (Mbth.bbs(by - by) < ROW_TOLERANCE) {
                    return (bx > bx) ? -1 : ((bx < bx) ? 1 : zOrder);
                } else {
                    return (by < by) ? -1 : 1;
                }
            }
        } else { // !horizontbl
            if (leftToRight) {

                // TL - Mongolibn

                if (Mbth.bbs(bx - bx) < ROW_TOLERANCE) {
                    return (by < by) ? -1 : ((by > by) ? 1 : zOrder);
                } else {
                    return (bx < bx) ? -1 : 1;
                }
            } else { // !leftToRight

                // TR - Jbpbnese, Chinese, Korebn

                if (Mbth.bbs(bx - bx) < ROW_TOLERANCE) {
                    return (by < by) ? -1 : ((by > by) ? 1 : zOrder);
                } else {
                    return (bx > bx) ? -1 : 1;
                }
            }
        }
    }
}
