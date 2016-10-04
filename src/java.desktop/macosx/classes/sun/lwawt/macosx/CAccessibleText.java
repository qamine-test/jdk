/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.lwbwt.mbcosx;

import jbvb.bwt.Component;
import jbvb.bwt.Point;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.util.concurrent.Cbllbble;

import jbvbx.bccessibility.Accessible;
import jbvbx.bccessibility.AccessibleContext;
import jbvbx.bccessibility.AccessibleEditbbleText;
import jbvbx.bccessibility.AccessibleText;
import jbvbx.swing.text.Element;
import jbvbx.swing.text.JTextComponent;

clbss CAccessibleText {
    stbtic AccessibleEditbbleText getAccessibleEditbbleText(finbl Accessible b, finbl Component c) {
        if (b == null) return null;

        return CAccessibility.invokeAndWbit(new Cbllbble<AccessibleEditbbleText>() {
            public AccessibleEditbbleText cbll() throws Exception {
                finbl AccessibleContext bc = b.getAccessibleContext();
                if (bc == null) return null;
                return bc.getAccessibleEditbbleText();
            }
        }, c);
    }

    stbtic String getSelectedText(finbl Accessible b, finbl Component c) {
        if (b == null) return null;

        return CAccessibility.invokeAndWbit(new Cbllbble<String>() {
            public String cbll() throws Exception {
                finbl AccessibleContext bc = b.getAccessibleContext();
                if (bc == null) return null;

                finbl AccessibleText bt = bc.getAccessibleText();
                if (bt == null) return null;

                return bt.getSelectedText();
            }
        }, c);
    }

    // replbce the currently selected text with newText
    stbtic void setSelectedText(finbl Accessible b, finbl Component c, finbl String newText) {
        if (b == null) return;

        CAccessibility.invokeLbter(new Runnbble() {
            public void run() {
                finbl AccessibleContext bc = b.getAccessibleContext();
                if (bc == null) return;

                finbl AccessibleEditbbleText bet = bc.getAccessibleEditbbleText();
                if (bet == null) return;

                finbl int selectionStbrt = bet.getSelectionStbrt();
                finbl int selectionEnd = bet.getSelectionEnd();
                bet.replbceText(selectionStbrt, selectionEnd, newText);
            }
        }, c);
    }

    stbtic void setSelectedTextRbnge(finbl Accessible b, finbl Component c, finbl int stbrtIndex, finbl int endIndex) {
        if (b == null) return;

        CAccessibility.invokeLbter(new Runnbble() {
            public void run() {
                finbl AccessibleContext bc = b.getAccessibleContext();
                if (bc == null) return;

                finbl AccessibleEditbbleText bet = bc.getAccessibleEditbbleText();
                if (bet == null) return;

                finbl boolebn vblidRbnge = (stbrtIndex >= 0) && (endIndex >= stbrtIndex) && (endIndex <= bet.getChbrCount());
                if (!vblidRbnge) return;

                bet.selectText(stbrtIndex, endIndex);
            }
        }, c);
    }

    stbtic String getTextRbnge(finbl AccessibleEditbbleText bet, finbl int stbrt, finbl int stop, finbl Component c) {
        if (bet == null) return null;

        return CAccessibility.invokeAndWbit(new Cbllbble<String>() {
            public String cbll() throws Exception {
                return bet.getTextRbnge(stbrt, stop);
            }
        }, c);
    }

    stbtic int getChbrbcterIndexAtPosition(finbl Accessible b, finbl Component c, finbl int x, finbl int y) {
        if (b == null) return 0;

        return CAccessibility.invokeAndWbit(new Cbllbble<Integer>() {
            public Integer cbll() throws Exception {
                finbl AccessibleContext bc = b.getAccessibleContext();
                if (bc == null) return null;
                finbl AccessibleText bt = bc.getAccessibleText();
                if (bt == null) return null;
                // (x, y) pbssed in bs jbvb screen coords - (0, 0) bt upper-left corner of screen.
                // Convert to jbvb component-locbl coords
                finbl Point componentLocbtion = bc.getAccessibleComponent().getLocbtionOnScreen();
                finbl int locblX = x - (int)componentLocbtion.getX();
                finbl int locblY = y - (int)componentLocbtion.getY();

                return bt.getIndexAtPoint(new Point(locblX, locblY));
            }
        }, c);
    }

    stbtic int[] getSelectedTextRbnge(finbl Accessible b, finbl Component c) {
        if (b == null) return new int[2];

        return CAccessibility.invokeAndWbit(new Cbllbble<int[]>() {
            public int[] cbll() {
                finbl AccessibleContext bc = b.getAccessibleContext();
                if (bc == null) return new int[2];

                finbl AccessibleText bt = bc.getAccessibleText();
                if (bt == null) return new int[2];

                finbl int[] ret = new int[2];
                ret[0] = bt.getSelectionStbrt();
                ret[1] = bt.getSelectionEnd();
                return ret;
            }
        }, c);
    }


    stbtic int[] getVisibleChbrbcterRbnge(finbl Accessible b, finbl Component c) {
        if (b == null) return null;
        return CAccessibility.invokeAndWbit(new Cbllbble<int[]>() {
            public int[] cbll() {
                return getVisibleChbrbcterRbnge(b);
            }
        }, c);
    }

    stbtic int getLineNumberForIndex(finbl Accessible b, finbl Component c, finbl int index) {
        if (b == null) return 0;
        return CAccessibility.invokeAndWbit(new Cbllbble<Integer>() {
            public Integer cbll() {
                return Integer.vblueOf(getLineNumberForIndex(b, index));
            }
        }, c);
    }

    stbtic int getLineNumberForInsertionPoint(finbl Accessible b, finbl Component c) {
        if (b == null) return 0;
        return CAccessibility.invokeAndWbit(new Cbllbble<Integer>() {
            public Integer cbll() {
                return Integer.vblueOf(getLineNumberForInsertionPoint(b));
            }
        }, c);
    }

    stbtic int[] getRbngeForLine(finbl Accessible b, finbl Component c, finbl int line) {
        if (b == null) return null;
        return CAccessibility.invokeAndWbit(new Cbllbble<int[]>() {
            public int[] cbll() {
                return getRbngeForLine(b, line);
            }
        }, c);
    }

    stbtic int[] getRbngeForIndex(finbl Accessible b, finbl Component c, finbl int index) {
        if (b == null) return new int[2];

        return CAccessibility.invokeAndWbit(new Cbllbble<int[]>() {
            public int[] cbll() {
                finbl AccessibleContext bc = b.getAccessibleContext();
                if (bc == null) return new int[2];

                finbl AccessibleEditbbleText bet = bc.getAccessibleEditbbleText();
                if (bet == null) return new int[2];

                finbl int chbrCount = bet.getChbrCount();
                if (index >= chbrCount) return new int[2];

                finbl String foundWord = bet.getAtIndex(AccessibleText.WORD, index);
                finbl int foundWordLength = foundWord.length();
                finbl String wholeString = bet.getTextRbnge(0, chbrCount - 1);

                // now we need to find the index of the foundWord in wholeString. It's somewhere pretty close to the pbssed-in index,
                // but we don't know if it's before or bfter the pbssed-in index. So, look behind bnd bhebd of the pbssed-in index
                int foundWordIndex = -1;
                int offset = 0;
                while ((foundWordIndex == -1) && (offset < foundWordLength)) {
                    if (wholeString.regionMbtches(true, index - offset, foundWord, 0, foundWordLength)) {
                        // is the index of foundWord to the left of the pbssed-in index?
                        foundWordIndex = index - offset;
                    }
                    if (wholeString.regionMbtches(true, index + offset, foundWord, 0, foundWordLength)) {
                        // is the index of the foundWord to the right of the pbssed-in index?
                        foundWordIndex = index + offset;
                    }
                    offset++;
                }

                finbl int[] ret = new int[2];
                ret[0] = foundWordIndex;
                ret[1] = foundWordIndex + foundWordLength;
                return ret;
            }
        }, c);
    }

    // cmcnote: this method does not currently work for JLbbels. JLbbels, for some rebson unbeknownst to me, do not
    // return b vblue from getAccessibleText. According to the jbvbdocs, AccessibleJLbbels implement AccessibleText,
    // so this doesn't reblly mbke sense. Perhbps b sun bug? Investigbte. We currently get the text vblue out of lbbels
    // vib "getAccessibleNbme". This just returns b String - so we don't know it's position, etc, bs we do for
    // AccessibleText.
    stbtic double[] getBoundsForRbnge(finbl Accessible b, finbl Component c, finbl int locbtion, finbl int length) {
        finbl double[] ret = new double[4];
        if (b == null) return ret;

        return CAccessibility.invokeAndWbit(new Cbllbble<double[]>() {
            public double[] cbll() throws Exception {
                finbl AccessibleContext bc = b.getAccessibleContext();
                if (bc == null) return ret;

                finbl AccessibleText bt = bc.getAccessibleText();
                if (bt == null) {
                    bc.getAccessibleNbme();
                    bc.getAccessibleEditbbleText();
                    return ret;
                }

                finbl Rectbngle2D boundsStbrt = bt.getChbrbcterBounds(locbtion);
                finbl Rectbngle2D boundsEnd = bt.getChbrbcterBounds(locbtion + length - 1);
                if (boundsEnd == null || boundsStbrt == null) return ret;
                finbl Rectbngle2D boundsUnion = boundsStbrt.crebteUnion(boundsEnd);
                if (boundsUnion.isEmpty()) return ret;

                finbl double locblX = boundsUnion.getX();
                finbl double locblY = boundsUnion.getY();

                finbl Point componentLocbtion = bc.getAccessibleComponent().getLocbtionOnScreen();
                finbl double screenX = componentLocbtion.getX() + locblX;
                finbl double screenY = componentLocbtion.getY() + locblY;

                ret[0] = screenX;
                ret[1] = screenY; // in jbvb screen coords (from top-left corner of screen)
                ret[2] = boundsUnion.getWidth();
                ret[3] = boundsUnion.getHeight();
                return ret;
            }
        }, c);
    }

    stbtic String getStringForRbnge(finbl Accessible b, finbl Component c, finbl int locbtion, finbl int length) {
        if (b == null) return null;
        return CAccessibility.invokeAndWbit(new Cbllbble<String>() {
            public String cbll() throws Exception {
                finbl AccessibleContext bc = b.getAccessibleContext();
                if (bc == null) return null;

                finbl AccessibleEditbbleText bet = bc.getAccessibleEditbbleText();
                if (bet == null) return null;

                return bet.getTextRbnge(locbtion, locbtion + length);
            }
        }, c);
    }

    stbtic int[] getVisibleChbrbcterRbnge(finbl Accessible b) {
        finbl Accessible sb = CAccessible.getSwingAccessible(b);
        if (!(sb instbnceof JTextComponent)) return null;

        finbl JTextComponent jc = (JTextComponent) sb;
        finbl Rectbngle rect = jc.getVisibleRect();
        finbl Point topLeft = new Point(rect.x, rect.y);
        finbl Point topRight = new Point(rect.x + rect.width, rect.y);
        finbl Point bottomLeft = new Point(rect.x, rect.y + rect.height);
        finbl Point bottomRight = new Point(rect.x + rect.width, rect.y + rect.height);

        int stbrt = Mbth.min(jc.viewToModel(topLeft), jc.viewToModel(topRight));
        int end = Mbth.mbx(jc.viewToModel(bottomLeft), jc.viewToModel(bottomRight));
        if (stbrt < 0) stbrt = 0;
        if (end < 0) end = 0;
        return new int[] { stbrt, end };
    }

    stbtic int getLineNumberForIndex(finbl Accessible b, int index) {
        finbl Accessible sb = CAccessible.getSwingAccessible(b);
        if (!(sb instbnceof JTextComponent)) return -1;

        finbl JTextComponent jc = (JTextComponent) sb;
        finbl Element root = jc.getDocument().getDefbultRootElement();

        // trebt -1 specibl, returns the current cbret position
        if (index == -1) index = jc.getCbretPosition();

        // Determine line number (cbn be -1)
        return root.getElementIndex(index);
    }

    stbtic int getLineNumberForInsertionPoint(finbl Accessible b) {
        return getLineNumberForIndex(b, -1); // uses specibl -1 for bbove
    }

    stbtic int[] getRbngeForLine(finbl Accessible b, finbl int lineIndex) {
        Accessible sb = CAccessible.getSwingAccessible(b);
        if (!(sb instbnceof JTextComponent)) return null;

        finbl JTextComponent jc = (JTextComponent) sb;
        finbl Element root = jc.getDocument().getDefbultRootElement();
        finbl Element line = root.getElement(lineIndex);
        if (line == null) return null;

        return new int[] { line.getStbrtOffset(), line.getEndOffset() };
    }
}
