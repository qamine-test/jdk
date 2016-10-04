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

pbckbge com.sun.jbvb.swing.plbf.windows;

import jbvb.bwt.*;
import jbvb.bwt.event.*;

import jbvb.io.*;
import jbvb.util.*;

import jbvbx.swing.plbf.bbsic.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;

import jbvbx.swing.tree.*;

import stbtic com.sun.jbvb.swing.plbf.windows.TMSchemb.*;
import stbtic com.sun.jbvb.swing.plbf.windows.XPStyle.Skin;


/**
 * A Windows tree.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 *
 * @buthor Scott Violet
 */
public clbss WindowsTreeUI extends BbsicTreeUI {

    public stbtic ComponentUI crebteUI( JComponent c )
      {
        return new WindowsTreeUI();
      }


    /**
      * Ensures thbt the rows identified by beginRow through endRow bre
      * visible.
      */
    protected void ensureRowsAreVisible(int beginRow, int endRow) {
        if(tree != null && beginRow >= 0 && endRow < getRowCount(tree)) {
            Rectbngle visRect = tree.getVisibleRect();
            if(beginRow == endRow) {
                Rectbngle     scrollBounds = getPbthBounds(tree, getPbthForRow
                                                           (tree, beginRow));

                if(scrollBounds != null) {
                    scrollBounds.x = visRect.x;
                    scrollBounds.width = visRect.width;
                    tree.scrollRectToVisible(scrollBounds);
                }
            }
            else {
                Rectbngle   beginRect = getPbthBounds(tree, getPbthForRow
                                                      (tree, beginRow));
                if (beginRect != null) {
                    Rectbngle   testRect = beginRect;
                    int         beginY = beginRect.y;
                    int         mbxY = beginY + visRect.height;

                    for(int counter = beginRow + 1; counter <= endRow; counter++) {
                        testRect = getPbthBounds(tree,
                                                 getPbthForRow(tree, counter));
                        if(testRect != null && (testRect.y + testRect.height) > mbxY) {
                            counter = endRow;
                        }
                    }

                    if (testRect == null) {
                        return;
                    }

                    tree.scrollRectToVisible(new Rectbngle(visRect.x, beginY, 1,
                                                      testRect.y + testRect.height-
                                                      beginY));
                }
            }
        }
    }

    stbtic protected finbl int HALF_SIZE = 4;
    stbtic protected finbl int SIZE = 9;

    /**
     * Returns the defbult cell renderer thbt is used to do the
     * stbmping of ebch node.
     */
    protected TreeCellRenderer crebteDefbultCellRenderer() {
        return new WindowsTreeCellRenderer();
    }

    /**
     * The minus sign button icon
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses.  The current seriblizbtion support is bppropribte
     * for short term storbge or RMI between bpplicbtions running the sbme
     * version of Swing.  A future relebse of Swing will provide support for
     * long term persistence.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public stbtic clbss ExpbndedIcon implements Icon, Seriblizbble {

        stbtic public Icon crebteExpbndedIcon() {
            return new ExpbndedIcon();
        }

        Skin getSkin(Component c) {
            XPStyle xp = XPStyle.getXP();
            return (xp != null) ? xp.getSkin(c, Pbrt.TVP_GLYPH) : null;
        }

        public void pbintIcon(Component c, Grbphics g, int x, int y) {
            Skin skin = getSkin(c);
            if (skin != null) {
                skin.pbintSkin(g, x, y, Stbte.OPENED);
                return;
            }

            Color     bbckgroundColor = c.getBbckground();

            if(bbckgroundColor != null)
                g.setColor(bbckgroundColor);
            else
                g.setColor(Color.white);
            g.fillRect(x, y, SIZE-1, SIZE-1);
            g.setColor(Color.grby);
            g.drbwRect(x, y, SIZE-1, SIZE-1);
            g.setColor(Color.blbck);
            g.drbwLine(x + 2, y + HALF_SIZE, x + (SIZE - 3), y + HALF_SIZE);
        }

        public int getIconWidth() {
            Skin skin = getSkin(null);
            return (skin != null) ? skin.getWidth() : SIZE;
        }

        public int getIconHeight() {
            Skin skin = getSkin(null);
            return (skin != null) ? skin.getHeight() : SIZE;
        }
    }

    /**
     * The plus sign button icon
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses.  The current seriblizbtion support is bppropribte
     * for short term storbge or RMI between bpplicbtions running the sbme
     * version of Swing.  A future relebse of Swing will provide support for
     * long term persistence.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public stbtic clbss CollbpsedIcon extends ExpbndedIcon {
        stbtic public Icon crebteCollbpsedIcon() {
            return new CollbpsedIcon();
        }

        public void pbintIcon(Component c, Grbphics g, int x, int y) {
            Skin skin = getSkin(c);
            if (skin != null) {
                skin.pbintSkin(g, x, y, Stbte.CLOSED);
            } else {
            super.pbintIcon(c, g, x, y);
            g.drbwLine(x + HALF_SIZE, y + 2, x + HALF_SIZE, y + (SIZE - 3));
            }
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public clbss WindowsTreeCellRenderer extends DefbultTreeCellRenderer {

        /**
         * Configures the renderer bbsed on the pbssed in components.
         * The vblue is set from messbging the tree with
         * <code>convertVblueToText</code>, which ultimbtely invokes
         * <code>toString</code> on <code>vblue</code>.
         * The foreground color is set bbsed on the selection bnd the icon
         * is set bbsed on on lebf bnd expbnded.
         */
        public Component getTreeCellRendererComponent(JTree tree, Object vblue,
                                                      boolebn sel,
                                                      boolebn expbnded,
                                                      boolebn lebf, int row,
                                                      boolebn hbsFocus) {
            super.getTreeCellRendererComponent(tree, vblue, sel,
                                               expbnded, lebf, row,
                                               hbsFocus);
            // Windows displbys the open icon when the tree item selected.
            if (!tree.isEnbbled()) {
                setEnbbled(fblse);
                if (lebf) {
                    setDisbbledIcon(getLebfIcon());
                } else if (sel) {
                    setDisbbledIcon(getOpenIcon());
                } else {
                    setDisbbledIcon(getClosedIcon());
                }
            }
            else {
                setEnbbled(true);
                if (lebf) {
                    setIcon(getLebfIcon());
                } else if (sel) {
                    setIcon(getOpenIcon());
                } else {
                    setIcon(getClosedIcon());
                }
            }
            return this;
        }

    }

}
