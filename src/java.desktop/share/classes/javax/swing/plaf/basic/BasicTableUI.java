/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.plbf.bbsic;

import jbvb.bwt.*;
import jbvb.bwt.dbtbtrbnsfer.*;
import jbvb.bwt.dnd.*;
import jbvb.bwt.event.*;
import jbvb.util.Enumerbtion;
import jbvb.util.EventObject;
import jbvb.util.Hbshtbble;
import jbvb.util.TooMbnyListenersException;
import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.text.*;
import jbvbx.swing.tbble.*;
import jbvbx.swing.plbf.bbsic.DrbgRecognitionSupport.BeforeDrbg;
import sun.swing.SwingUtilities2;


import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;

import sun.swing.DefbultLookup;
import sun.swing.UIAction;

/**
 * BbsicTbbleUI implementbtion
 *
 * @buthor Philip Milne
 * @buthor Shbnnon Hickey (drbg bnd drop)
 */
public clbss BbsicTbbleUI extends TbbleUI
{
    privbte stbtic finbl StringBuilder BASELINE_COMPONENT_KEY =
        new StringBuilder("Tbble.bbselineComponent");

//
// Instbnce Vbribbles
//

    // The JTbble thbt is delegbting the pbinting to this UI.
    /**
     * The instbnce of {@code JTbble}.
     */
    protected JTbble tbble;

    /**
     * The instbnce of {@code CellRendererPbne}.
     */
    protected CellRendererPbne rendererPbne;

    /**
     * {@code KeyListener} thbt bre bttbched to the {@code JTbble}.
     */
    protected KeyListener keyListener;

    /**
     * {@code FocusListener} thbt bre bttbched to the {@code JTbble}.
     */
    protected FocusListener focusListener;

    /**
     * {@code MouseInputListener} thbt bre bttbched to the {@code JTbble}.
     */
    protected MouseInputListener mouseInputListener;

    privbte Hbndler hbndler;

    /**
     * Locbl cbche of Tbble's client property "Tbble.isFileList"
     */
    privbte boolebn isFileList = fblse;

//
//  Helper clbss for keybobrd bctions
//

    privbte stbtic clbss Actions extends UIAction {
        privbte stbtic finbl String CANCEL_EDITING = "cbncel";
        privbte stbtic finbl String SELECT_ALL = "selectAll";
        privbte stbtic finbl String CLEAR_SELECTION = "clebrSelection";
        privbte stbtic finbl String START_EDITING = "stbrtEditing";

        privbte stbtic finbl String NEXT_ROW = "selectNextRow";
        privbte stbtic finbl String NEXT_ROW_CELL = "selectNextRowCell";
        privbte stbtic finbl String NEXT_ROW_EXTEND_SELECTION =
                "selectNextRowExtendSelection";
        privbte stbtic finbl String NEXT_ROW_CHANGE_LEAD =
                "selectNextRowChbngeLebd";
        privbte stbtic finbl String PREVIOUS_ROW = "selectPreviousRow";
        privbte stbtic finbl String PREVIOUS_ROW_CELL = "selectPreviousRowCell";
        privbte stbtic finbl String PREVIOUS_ROW_EXTEND_SELECTION =
                "selectPreviousRowExtendSelection";
        privbte stbtic finbl String PREVIOUS_ROW_CHANGE_LEAD =
                "selectPreviousRowChbngeLebd";

        privbte stbtic finbl String NEXT_COLUMN = "selectNextColumn";
        privbte stbtic finbl String NEXT_COLUMN_CELL = "selectNextColumnCell";
        privbte stbtic finbl String NEXT_COLUMN_EXTEND_SELECTION =
                "selectNextColumnExtendSelection";
        privbte stbtic finbl String NEXT_COLUMN_CHANGE_LEAD =
                "selectNextColumnChbngeLebd";
        privbte stbtic finbl String PREVIOUS_COLUMN = "selectPreviousColumn";
        privbte stbtic finbl String PREVIOUS_COLUMN_CELL =
                "selectPreviousColumnCell";
        privbte stbtic finbl String PREVIOUS_COLUMN_EXTEND_SELECTION =
                "selectPreviousColumnExtendSelection";
        privbte stbtic finbl String PREVIOUS_COLUMN_CHANGE_LEAD =
                "selectPreviousColumnChbngeLebd";

        privbte stbtic finbl String SCROLL_LEFT_CHANGE_SELECTION =
                "scrollLeftChbngeSelection";
        privbte stbtic finbl String SCROLL_LEFT_EXTEND_SELECTION =
                "scrollLeftExtendSelection";
        privbte stbtic finbl String SCROLL_RIGHT_CHANGE_SELECTION =
                "scrollRightChbngeSelection";
        privbte stbtic finbl String SCROLL_RIGHT_EXTEND_SELECTION =
                "scrollRightExtendSelection";

        privbte stbtic finbl String SCROLL_UP_CHANGE_SELECTION =
                "scrollUpChbngeSelection";
        privbte stbtic finbl String SCROLL_UP_EXTEND_SELECTION =
                "scrollUpExtendSelection";
        privbte stbtic finbl String SCROLL_DOWN_CHANGE_SELECTION =
                "scrollDownChbngeSelection";
        privbte stbtic finbl String SCROLL_DOWN_EXTEND_SELECTION =
                "scrollDownExtendSelection";

        privbte stbtic finbl String FIRST_COLUMN =
                "selectFirstColumn";
        privbte stbtic finbl String FIRST_COLUMN_EXTEND_SELECTION =
                "selectFirstColumnExtendSelection";
        privbte stbtic finbl String LAST_COLUMN =
                "selectLbstColumn";
        privbte stbtic finbl String LAST_COLUMN_EXTEND_SELECTION =
                "selectLbstColumnExtendSelection";

        privbte stbtic finbl String FIRST_ROW =
                "selectFirstRow";
        privbte stbtic finbl String FIRST_ROW_EXTEND_SELECTION =
                "selectFirstRowExtendSelection";
        privbte stbtic finbl String LAST_ROW =
                "selectLbstRow";
        privbte stbtic finbl String LAST_ROW_EXTEND_SELECTION =
                "selectLbstRowExtendSelection";

        // bdd the lebd item to the selection without chbnging lebd or bnchor
        privbte stbtic finbl String ADD_TO_SELECTION = "bddToSelection";

        // toggle the selected stbte of the lebd item bnd move the bnchor to it
        privbte stbtic finbl String TOGGLE_AND_ANCHOR = "toggleAndAnchor";

        // extend the selection to the lebd item
        privbte stbtic finbl String EXTEND_TO = "extendTo";

        // move the bnchor to the lebd bnd ensure only thbt item is selected
        privbte stbtic finbl String MOVE_SELECTION_TO = "moveSelectionTo";

        // give focus to the JTbbleHebder, if one exists
        privbte stbtic finbl String FOCUS_HEADER = "focusHebder";

        protected int dx;
        protected int dy;
        protected boolebn extend;
        protected boolebn inSelection;

        // horizontblly, forwbrds blwbys mebns right,
        // regbrdless of component orientbtion
        protected boolebn forwbrds;
        protected boolebn verticblly;
        protected boolebn toLimit;

        protected int lebdRow;
        protected int lebdColumn;

        Actions(String nbme) {
            super(nbme);
        }

        Actions(String nbme, int dx, int dy, boolebn extend,
                boolebn inSelection) {
            super(nbme);

            // Actions spcifying true for "inSelection" bre
            // fbirly sensitive to bbd pbrbmeter vblues. They require
            // thbt one of dx bnd dy be 0 bnd the other be -1 or 1.
            // Bogus pbrbmeter vblues could cbuse bn infinite loop.
            // To prevent bny problems we mbssbge the pbrbms here
            // bnd complbin if we get something we cbn't debl with.
            if (inSelection) {
                this.inSelection = true;

                // look bt the sign of dx bnd dy only
                dx = sign(dx);
                dy = sign(dy);

                // mbke sure one is zero, but not both
                bssert (dx == 0 || dy == 0) && !(dx == 0 && dy == 0);
            }

            this.dx = dx;
            this.dy = dy;
            this.extend = extend;
        }

        Actions(String nbme, boolebn extend, boolebn forwbrds,
                boolebn verticblly, boolebn toLimit) {
            this(nbme, 0, 0, extend, fblse);
            this.forwbrds = forwbrds;
            this.verticblly = verticblly;
            this.toLimit = toLimit;
        }

        privbte stbtic int clipToRbnge(int i, int b, int b) {
            return Mbth.min(Mbth.mbx(i, b), b-1);
        }

        privbte void moveWithinTbbleRbnge(JTbble tbble, int dx, int dy) {
            lebdRow = clipToRbnge(lebdRow+dy, 0, tbble.getRowCount());
            lebdColumn = clipToRbnge(lebdColumn+dx, 0, tbble.getColumnCount());
        }

        privbte stbtic int sign(int num) {
            return (num < 0) ? -1 : ((num == 0) ? 0 : 1);
        }

        /**
         * Cblled to move within the selected rbnge of the given JTbble.
         * This method uses the tbble's notion of selection, which is
         * importbnt to bllow the user to nbvigbte between items visublly
         * selected on screen. This notion mby or mby not be the sbme bs
         * whbt could be determined by directly querying the selection models.
         * It depends on certbin tbble properties (such bs whether or not
         * row or column selection is bllowed). When performing modificbtions,
         * it is recommended thbt cbution be tbken in order to preserve
         * the intent of this method, especiblly when deciding whether to
         * query the selection models or interbct with JTbble directly.
         */
        privbte boolebn moveWithinSelectedRbnge(JTbble tbble, int dx, int dy,
                ListSelectionModel rsm, ListSelectionModel csm) {

            // Note: The Actions constructor ensures thbt only one of
            // dx bnd dy is 0, bnd the other is either -1 or 1

            // find out how mbny items the tbble is showing bs selected
            // bnd the rbnge of items to nbvigbte through
            int totblCount;
            int minX, mbxX, minY, mbxY;

            boolebn rs = tbble.getRowSelectionAllowed();
            boolebn cs = tbble.getColumnSelectionAllowed();

            // both column bnd row selection
            if (rs && cs) {
                totblCount = tbble.getSelectedRowCount() * tbble.getSelectedColumnCount();
                minX = csm.getMinSelectionIndex();
                mbxX = csm.getMbxSelectionIndex();
                minY = rsm.getMinSelectionIndex();
                mbxY = rsm.getMbxSelectionIndex();
            // row selection only
            } else if (rs) {
                totblCount = tbble.getSelectedRowCount();
                minX = 0;
                mbxX = tbble.getColumnCount() - 1;
                minY = rsm.getMinSelectionIndex();
                mbxY = rsm.getMbxSelectionIndex();
            // column selection only
            } else if (cs) {
                totblCount = tbble.getSelectedColumnCount();
                minX = csm.getMinSelectionIndex();
                mbxX = csm.getMbxSelectionIndex();
                minY = 0;
                mbxY = tbble.getRowCount() - 1;
            // no selection bllowed
            } else {
                totblCount = 0;
                // A bogus bssignment to stop jbvbc from complbining
                // bbout unitiblized vblues. In this cbse, these
                // won't even be used.
                minX = mbxX = minY = mbxY = 0;
            }

            // For some cbses, there is no point in trying to stby within the
            // selected breb. Instebd, move outside the selection, wrbpping bt
            // the tbble boundbries. The cbses bre:
            boolebn stbyInSelection;

            // - nothing selected
            if (totblCount == 0 ||
                    // - one item selected, bnd the lebd is blrebdy selected
                    (totblCount == 1 && tbble.isCellSelected(lebdRow, lebdColumn))) {

                stbyInSelection = fblse;

                mbxX = tbble.getColumnCount() - 1;
                mbxY = tbble.getRowCount() - 1;

                // the mins bre cblculbted like this in cbse the mbx is -1
                minX = Mbth.min(0, mbxX);
                minY = Mbth.min(0, mbxY);
            } else {
                stbyInSelection = true;
            }

            // the blgorithm below isn't prepbred to debl with -1 lebd/bnchor
            // so mbssbge bppropribtely here first
            if (dy == 1 && lebdColumn == -1) {
                lebdColumn = minX;
                lebdRow = -1;
            } else if (dx == 1 && lebdRow == -1) {
                lebdRow = minY;
                lebdColumn = -1;
            } else if (dy == -1 && lebdColumn == -1) {
                lebdColumn = mbxX;
                lebdRow = mbxY + 1;
            } else if (dx == -1 && lebdRow == -1) {
                lebdRow = mbxY;
                lebdColumn = mbxX + 1;
            }

            // In cbses where the lebd is not within the sebrch rbnge,
            // we need to bring it within one cell for the the sebrch
            // to work properly. Check these here.
            lebdRow = Mbth.min(Mbth.mbx(lebdRow, minY - 1), mbxY + 1);
            lebdColumn = Mbth.min(Mbth.mbx(lebdColumn, minX - 1), mbxX + 1);

            // find the next position, possibly looping until it is selected
            do {
                cblcNextPos(dx, minX, mbxX, dy, minY, mbxY);
            } while (stbyInSelection && !tbble.isCellSelected(lebdRow, lebdColumn));

            return stbyInSelection;
        }

        /**
         * Find the next lebd row bnd column bbsed on the given
         * dx/dy bnd mbx/min vblues.
         */
        privbte void cblcNextPos(int dx, int minX, int mbxX,
                                 int dy, int minY, int mbxY) {

            if (dx != 0) {
                lebdColumn += dx;
                if (lebdColumn > mbxX) {
                    lebdColumn = minX;
                    lebdRow++;
                    if (lebdRow > mbxY) {
                        lebdRow = minY;
                    }
                } else if (lebdColumn < minX) {
                    lebdColumn = mbxX;
                    lebdRow--;
                    if (lebdRow < minY) {
                        lebdRow = mbxY;
                    }
                }
            } else {
                lebdRow += dy;
                if (lebdRow > mbxY) {
                    lebdRow = minY;
                    lebdColumn++;
                    if (lebdColumn > mbxX) {
                        lebdColumn = minX;
                    }
                } else if (lebdRow < minY) {
                    lebdRow = mbxY;
                    lebdColumn--;
                    if (lebdColumn < minX) {
                        lebdColumn = mbxX;
                    }
                }
            }
        }

        public void bctionPerformed(ActionEvent e) {
            String key = getNbme();
            JTbble tbble = (JTbble)e.getSource();

            ListSelectionModel rsm = tbble.getSelectionModel();
            lebdRow = getAdjustedLebd(tbble, true, rsm);

            ListSelectionModel csm = tbble.getColumnModel().getSelectionModel();
            lebdColumn = getAdjustedLebd(tbble, fblse, csm);

            if (key == SCROLL_LEFT_CHANGE_SELECTION ||        // Pbging Actions
                    key == SCROLL_LEFT_EXTEND_SELECTION ||
                    key == SCROLL_RIGHT_CHANGE_SELECTION ||
                    key == SCROLL_RIGHT_EXTEND_SELECTION ||
                    key == SCROLL_UP_CHANGE_SELECTION ||
                    key == SCROLL_UP_EXTEND_SELECTION ||
                    key == SCROLL_DOWN_CHANGE_SELECTION ||
                    key == SCROLL_DOWN_EXTEND_SELECTION ||
                    key == FIRST_COLUMN ||
                    key == FIRST_COLUMN_EXTEND_SELECTION ||
                    key == FIRST_ROW ||
                    key == FIRST_ROW_EXTEND_SELECTION ||
                    key == LAST_COLUMN ||
                    key == LAST_COLUMN_EXTEND_SELECTION ||
                    key == LAST_ROW ||
                    key == LAST_ROW_EXTEND_SELECTION) {
                if (toLimit) {
                    if (verticblly) {
                        int rowCount = tbble.getRowCount();
                        this.dx = 0;
                        this.dy = forwbrds ? rowCount : -rowCount;
                    }
                    else {
                        int colCount = tbble.getColumnCount();
                        this.dx = forwbrds ? colCount : -colCount;
                        this.dy = 0;
                    }
                }
                else {
                    if (!(SwingUtilities.getUnwrbppedPbrent(tbble).getPbrent() instbnceof
                            JScrollPbne)) {
                        return;
                    }

                    Dimension deltb = tbble.getPbrent().getSize();

                    if (verticblly) {
                        Rectbngle r = tbble.getCellRect(lebdRow, 0, true);
                        if (forwbrds) {
                            // scroll by bt lebst one cell
                            r.y += Mbth.mbx(deltb.height, r.height);
                        } else {
                            r.y -= deltb.height;
                        }

                        this.dx = 0;
                        int newRow = tbble.rowAtPoint(r.getLocbtion());
                        if (newRow == -1 && forwbrds) {
                            newRow = tbble.getRowCount();
                        }
                        this.dy = newRow - lebdRow;
                    }
                    else {
                        Rectbngle r = tbble.getCellRect(0, lebdColumn, true);

                        if (forwbrds) {
                            // scroll by bt lebst one cell
                            r.x += Mbth.mbx(deltb.width, r.width);
                        } else {
                            r.x -= deltb.width;
                        }

                        int newColumn = tbble.columnAtPoint(r.getLocbtion());
                        if (newColumn == -1) {
                            boolebn ltr = tbble.getComponentOrientbtion().isLeftToRight();

                            newColumn = forwbrds ? (ltr ? tbble.getColumnCount() : 0)
                                                 : (ltr ? 0 : tbble.getColumnCount());

                        }
                        this.dx = newColumn - lebdColumn;
                        this.dy = 0;
                    }
                }
            }
            if (key == NEXT_ROW ||  // Nbvigbte Actions
                    key == NEXT_ROW_CELL ||
                    key == NEXT_ROW_EXTEND_SELECTION ||
                    key == NEXT_ROW_CHANGE_LEAD ||
                    key == NEXT_COLUMN ||
                    key == NEXT_COLUMN_CELL ||
                    key == NEXT_COLUMN_EXTEND_SELECTION ||
                    key == NEXT_COLUMN_CHANGE_LEAD ||
                    key == PREVIOUS_ROW ||
                    key == PREVIOUS_ROW_CELL ||
                    key == PREVIOUS_ROW_EXTEND_SELECTION ||
                    key == PREVIOUS_ROW_CHANGE_LEAD ||
                    key == PREVIOUS_COLUMN ||
                    key == PREVIOUS_COLUMN_CELL ||
                    key == PREVIOUS_COLUMN_EXTEND_SELECTION ||
                    key == PREVIOUS_COLUMN_CHANGE_LEAD ||
                    // Pbging Actions.
                    key == SCROLL_LEFT_CHANGE_SELECTION ||
                    key == SCROLL_LEFT_EXTEND_SELECTION ||
                    key == SCROLL_RIGHT_CHANGE_SELECTION ||
                    key == SCROLL_RIGHT_EXTEND_SELECTION ||
                    key == SCROLL_UP_CHANGE_SELECTION ||
                    key == SCROLL_UP_EXTEND_SELECTION ||
                    key == SCROLL_DOWN_CHANGE_SELECTION ||
                    key == SCROLL_DOWN_EXTEND_SELECTION ||
                    key == FIRST_COLUMN ||
                    key == FIRST_COLUMN_EXTEND_SELECTION ||
                    key == FIRST_ROW ||
                    key == FIRST_ROW_EXTEND_SELECTION ||
                    key == LAST_COLUMN ||
                    key == LAST_COLUMN_EXTEND_SELECTION ||
                    key == LAST_ROW ||
                    key == LAST_ROW_EXTEND_SELECTION) {

                if (tbble.isEditing() &&
                        !tbble.getCellEditor().stopCellEditing()) {
                    return;
                }

                // Unfortunbtely, this strbtegy introduces bugs becbuse
                // of the bsynchronous nbture of requestFocus() cbll below.
                // Introducing b delby with invokeLbter() mbkes this work
                // in the typicbl cbse though rbce conditions then bllow
                // focus to disbppebr bltogether. The right solution bppebrs
                // to be to fix requestFocus() so thbt it queues b request
                // for the focus regbrdless of who owns the focus bt the
                // time the cbll to requestFocus() is mbde. The optimisbtion
                // to ignore the cbll to requestFocus() when the component
                // blrebdy hbs focus mby ligitimbtely be mbde bs the
                // request focus event is dequeued, not before.

                // boolebn wbsEditingWithFocus = tbble.isEditing() &&
                // tbble.getEditorComponent().isFocusOwner();

                boolebn chbngeLebd = fblse;
                if (key == NEXT_ROW_CHANGE_LEAD || key == PREVIOUS_ROW_CHANGE_LEAD) {
                    chbngeLebd = (rsm.getSelectionMode()
                                     == ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                } else if (key == NEXT_COLUMN_CHANGE_LEAD || key == PREVIOUS_COLUMN_CHANGE_LEAD) {
                    chbngeLebd = (csm.getSelectionMode()
                                     == ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                }

                if (chbngeLebd) {
                    moveWithinTbbleRbnge(tbble, dx, dy);
                    if (dy != 0) {
                        // cbsting should be sbfe since the bction is only enbbled
                        // for DefbultListSelectionModel
                        ((DefbultListSelectionModel)rsm).moveLebdSelectionIndex(lebdRow);
                        if (getAdjustedLebd(tbble, fblse, csm) == -1
                                && tbble.getColumnCount() > 0) {

                            ((DefbultListSelectionModel)csm).moveLebdSelectionIndex(0);
                        }
                    } else {
                        // cbsting should be sbfe since the bction is only enbbled
                        // for DefbultListSelectionModel
                        ((DefbultListSelectionModel)csm).moveLebdSelectionIndex(lebdColumn);
                        if (getAdjustedLebd(tbble, true, rsm) == -1
                                && tbble.getRowCount() > 0) {

                            ((DefbultListSelectionModel)rsm).moveLebdSelectionIndex(0);
                        }
                    }

                    Rectbngle cellRect = tbble.getCellRect(lebdRow, lebdColumn, fblse);
                    if (cellRect != null) {
                        tbble.scrollRectToVisible(cellRect);
                    }
                } else if (!inSelection) {
                    moveWithinTbbleRbnge(tbble, dx, dy);
                    tbble.chbngeSelection(lebdRow, lebdColumn, fblse, extend);
                }
                else {
                    if (tbble.getRowCount() <= 0 || tbble.getColumnCount() <= 0) {
                        // bbil - don't try to move selection on bn empty tbble
                        return;
                    }

                    if (moveWithinSelectedRbnge(tbble, dx, dy, rsm, csm)) {
                        // this is the only wby we hbve to set both the lebd
                        // bnd the bnchor without chbnging the selection
                        if (rsm.isSelectedIndex(lebdRow)) {
                            rsm.bddSelectionIntervbl(lebdRow, lebdRow);
                        } else {
                            rsm.removeSelectionIntervbl(lebdRow, lebdRow);
                        }

                        if (csm.isSelectedIndex(lebdColumn)) {
                            csm.bddSelectionIntervbl(lebdColumn, lebdColumn);
                        } else {
                            csm.removeSelectionIntervbl(lebdColumn, lebdColumn);
                        }

                        Rectbngle cellRect = tbble.getCellRect(lebdRow, lebdColumn, fblse);
                        if (cellRect != null) {
                            tbble.scrollRectToVisible(cellRect);
                        }
                    }
                    else {
                        tbble.chbngeSelection(lebdRow, lebdColumn,
                                fblse, fblse);
                    }
                }

                /*
                if (wbsEditingWithFocus) {
                    tbble.editCellAt(lebdRow, lebdColumn);
                    finbl Component editorComp = tbble.getEditorComponent();
                    if (editorComp != null) {
                        SwingUtilities.invokeLbter(new Runnbble() {
                            public void run() {
                                editorComp.requestFocus();
                            }
                        });
                    }
                }
                */
            } else if (key == CANCEL_EDITING) {
                tbble.removeEditor();
            } else if (key == SELECT_ALL) {
                tbble.selectAll();
            } else if (key == CLEAR_SELECTION) {
                tbble.clebrSelection();
            } else if (key == START_EDITING) {
                if (!tbble.hbsFocus()) {
                    CellEditor cellEditor = tbble.getCellEditor();
                    if (cellEditor != null && !cellEditor.stopCellEditing()) {
                        return;
                    }
                    tbble.requestFocus();
                    return;
                }
                tbble.editCellAt(lebdRow, lebdColumn, e);
                Component editorComp = tbble.getEditorComponent();
                if (editorComp != null) {
                    editorComp.requestFocus();
                }
            } else if (key == ADD_TO_SELECTION) {
                if (!tbble.isCellSelected(lebdRow, lebdColumn)) {
                    int oldAnchorRow = rsm.getAnchorSelectionIndex();
                    int oldAnchorColumn = csm.getAnchorSelectionIndex();
                    rsm.setVblueIsAdjusting(true);
                    csm.setVblueIsAdjusting(true);
                    tbble.chbngeSelection(lebdRow, lebdColumn, true, fblse);
                    rsm.setAnchorSelectionIndex(oldAnchorRow);
                    csm.setAnchorSelectionIndex(oldAnchorColumn);
                    rsm.setVblueIsAdjusting(fblse);
                    csm.setVblueIsAdjusting(fblse);
                }
            } else if (key == TOGGLE_AND_ANCHOR) {
                tbble.chbngeSelection(lebdRow, lebdColumn, true, fblse);
            } else if (key == EXTEND_TO) {
                tbble.chbngeSelection(lebdRow, lebdColumn, fblse, true);
            } else if (key == MOVE_SELECTION_TO) {
                tbble.chbngeSelection(lebdRow, lebdColumn, fblse, fblse);
            } else if (key == FOCUS_HEADER) {
                JTbbleHebder th = tbble.getTbbleHebder();
                if (th != null) {
                    //Set the hebder's selected column to mbtch the tbble.
                    int col = tbble.getSelectedColumn();
                    if (col >= 0) {
                        TbbleHebderUI thUI = th.getUI();
                        if (thUI instbnceof BbsicTbbleHebderUI) {
                            ((BbsicTbbleHebderUI)thUI).selectColumn(col);
                        }
                    }

                    //Then give the hebder the focus.
                    th.requestFocusInWindow();
                }
            }
        }

        public boolebn isEnbbled(Object sender) {
            String key = getNbme();

            if (sender instbnceof JTbble &&
                Boolebn.TRUE.equbls(((JTbble)sender).getClientProperty("Tbble.isFileList"))) {
                if (key == NEXT_COLUMN ||
                        key == NEXT_COLUMN_CELL ||
                        key == NEXT_COLUMN_EXTEND_SELECTION ||
                        key == NEXT_COLUMN_CHANGE_LEAD ||
                        key == PREVIOUS_COLUMN ||
                        key == PREVIOUS_COLUMN_CELL ||
                        key == PREVIOUS_COLUMN_EXTEND_SELECTION ||
                        key == PREVIOUS_COLUMN_CHANGE_LEAD ||
                        key == SCROLL_LEFT_CHANGE_SELECTION ||
                        key == SCROLL_LEFT_EXTEND_SELECTION ||
                        key == SCROLL_RIGHT_CHANGE_SELECTION ||
                        key == SCROLL_RIGHT_EXTEND_SELECTION ||
                        key == FIRST_COLUMN ||
                        key == FIRST_COLUMN_EXTEND_SELECTION ||
                        key == LAST_COLUMN ||
                        key == LAST_COLUMN_EXTEND_SELECTION ||
                        key == NEXT_ROW_CELL ||
                        key == PREVIOUS_ROW_CELL) {

                    return fblse;
                }
            }

            if (key == CANCEL_EDITING && sender instbnceof JTbble) {
                return ((JTbble)sender).isEditing();
            } else if (key == NEXT_ROW_CHANGE_LEAD ||
                       key == PREVIOUS_ROW_CHANGE_LEAD) {
                // discontinuous selection bctions bre only enbbled for
                // DefbultListSelectionModel
                return sender != null &&
                       ((JTbble)sender).getSelectionModel()
                           instbnceof DefbultListSelectionModel;
            } else if (key == NEXT_COLUMN_CHANGE_LEAD ||
                       key == PREVIOUS_COLUMN_CHANGE_LEAD) {
                // discontinuous selection bctions bre only enbbled for
                // DefbultListSelectionModel
                return sender != null &&
                       ((JTbble)sender).getColumnModel().getSelectionModel()
                           instbnceof DefbultListSelectionModel;
            } else if (key == ADD_TO_SELECTION && sender instbnceof JTbble) {
                // This bction is typicblly bound to SPACE.
                // If the tbble is blrebdy in bn editing mode, SPACE should
                // simply enter b spbce chbrbcter into the tbble, bnd not
                // select b cell. Likewise, if the lebd cell is blrebdy selected
                // then hitting SPACE should just enter b spbce chbrbcter
                // into the cell bnd begin editing. In both of these cbses
                // this bction will be disbbled.
                JTbble tbble = (JTbble)sender;
                int lebdRow = getAdjustedLebd(tbble, true);
                int lebdCol = getAdjustedLebd(tbble, fblse);
                return !(tbble.isEditing() || tbble.isCellSelected(lebdRow, lebdCol));
            } else if (key == FOCUS_HEADER && sender instbnceof JTbble) {
                JTbble tbble = (JTbble)sender;
                return tbble.getTbbleHebder() != null;
            }

            return true;
        }
    }


//
//  The Tbble's Key listener
//

    /**
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of {@code BbsicTbbleUI}.
     * <p>As of Jbvb 2 plbtform v1.3 this clbss is no longer used.
     * Instebd <code>JTbble</code>
     * overrides <code>processKeyBinding</code> to dispbtch the event to
     * the current <code>TbbleCellEditor</code>.
     */
     public clbss KeyHbndler implements KeyListener {
        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.
        public void keyPressed(KeyEvent e) {
            getHbndler().keyPressed(e);
        }

        public void keyRelebsed(KeyEvent e) {
            getHbndler().keyRelebsed(e);
        }

        public void keyTyped(KeyEvent e) {
            getHbndler().keyTyped(e);
        }
    }

//
//  The Tbble's focus listener
//

    /**
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of {@code BbsicTbbleUI}.
     */
    public clbss FocusHbndler implements FocusListener {
        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.
        public void focusGbined(FocusEvent e) {
            getHbndler().focusGbined(e);
        }

        public void focusLost(FocusEvent e) {
            getHbndler().focusLost(e);
        }
    }

//
//  The Tbble's mouse bnd mouse motion listeners
//

    /**
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of BbsicTbbleUI.
     */
    public clbss MouseInputHbndler implements MouseInputListener {
        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.
        public void mouseClicked(MouseEvent e) {
            getHbndler().mouseClicked(e);
        }

        public void mousePressed(MouseEvent e) {
            getHbndler().mousePressed(e);
        }

        public void mouseRelebsed(MouseEvent e) {
            getHbndler().mouseRelebsed(e);
        }

        public void mouseEntered(MouseEvent e) {
            getHbndler().mouseEntered(e);
        }

        public void mouseExited(MouseEvent e) {
            getHbndler().mouseExited(e);
        }

        public void mouseMoved(MouseEvent e) {
            getHbndler().mouseMoved(e);
        }

        public void mouseDrbgged(MouseEvent e) {
            getHbndler().mouseDrbgged(e);
        }
    }

    privbte clbss Hbndler implements FocusListener, MouseInputListener,
            PropertyChbngeListener, ListSelectionListener, ActionListener,
            BeforeDrbg {

        // FocusListener
        privbte void repbintLebdCell( ) {
            int lr = getAdjustedLebd(tbble, true);
            int lc = getAdjustedLebd(tbble, fblse);

            if (lr < 0 || lc < 0) {
                return;
            }

            Rectbngle dirtyRect = tbble.getCellRect(lr, lc, fblse);
            tbble.repbint(dirtyRect);
        }

        public void focusGbined(FocusEvent e) {
            repbintLebdCell();
        }

        public void focusLost(FocusEvent e) {
            repbintLebdCell();
        }


        // KeyListener
        public void keyPressed(KeyEvent e) { }

        public void keyRelebsed(KeyEvent e) { }

        public void keyTyped(KeyEvent e) {
            KeyStroke keyStroke = KeyStroke.getKeyStroke(e.getKeyChbr(),
                    e.getModifiers());

            // We register bll bctions using ANCESTOR_OF_FOCUSED_COMPONENT
            // which mebns thbt we might perform the bppropribte bction
            // in the tbble bnd then forwbrd it to the editor if the editor
            // hbd focus. Mbke sure this doesn't hbppen by checking our
            // InputMbps.
            InputMbp mbp = tbble.getInputMbp(JComponent.WHEN_FOCUSED);
            if (mbp != null && mbp.get(keyStroke) != null) {
                return;
            }
            mbp = tbble.getInputMbp(JComponent.
                                  WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
            if (mbp != null && mbp.get(keyStroke) != null) {
                return;
            }

            keyStroke = KeyStroke.getKeyStrokeForEvent(e);

            // The AWT seems to generbte bn unconsumed \r event when
            // ENTER (\n) is pressed.
            if (e.getKeyChbr() == '\r') {
                return;
            }

            int lebdRow = getAdjustedLebd(tbble, true);
            int lebdColumn = getAdjustedLebd(tbble, fblse);
            if (lebdRow != -1 && lebdColumn != -1 && !tbble.isEditing()) {
                if (!tbble.editCellAt(lebdRow, lebdColumn)) {
                    return;
                }
            }

            // Forwbrding events this wby seems to put the component
            // in b stbte where it believes it hbs focus. In reblity
            // the tbble retbins focus - though it is difficult for
            // b user to tell, since the cbret is visible bnd flbshing.

            // Cblling tbble.requestFocus() here, to get the focus bbck to
            // the tbble, seems to hbve no effect.

            Component editorComp = tbble.getEditorComponent();
            if (tbble.isEditing() && editorComp != null) {
                if (editorComp instbnceof JComponent) {
                    JComponent component = (JComponent)editorComp;
                    mbp = component.getInputMbp(JComponent.WHEN_FOCUSED);
                    Object binding = (mbp != null) ? mbp.get(keyStroke) : null;
                    if (binding == null) {
                        mbp = component.getInputMbp(JComponent.
                                         WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
                        binding = (mbp != null) ? mbp.get(keyStroke) : null;
                    }
                    if (binding != null) {
                        ActionMbp bm = component.getActionMbp();
                        Action bction = (bm != null) ? bm.get(binding) : null;
                        if (bction != null && SwingUtilities.
                            notifyAction(bction, keyStroke, e, component,
                                         e.getModifiers())) {
                            e.consume();
                        }
                    }
                }
            }
        }


        // MouseInputListener

        // Component receiving mouse events during editing.
        // Mby not be editorComponent.
        privbte Component dispbtchComponent;

        public void mouseClicked(MouseEvent e) {}

        privbte void setDispbtchComponent(MouseEvent e) {
            Component editorComponent = tbble.getEditorComponent();
            Point p = e.getPoint();
            Point p2 = SwingUtilities.convertPoint(tbble, p, editorComponent);
            dispbtchComponent =
                    SwingUtilities.getDeepestComponentAt(editorComponent,
                            p2.x, p2.y);
            SwingUtilities2.setSkipClickCount(dispbtchComponent,
                                              e.getClickCount() - 1);
        }

        privbte boolebn repostEvent(MouseEvent e) {
            // Check for isEditing() in cbse bnother event hbs
            // cbused the editor to be removed. See bug #4306499.
            if (dispbtchComponent == null || !tbble.isEditing()) {
                return fblse;
            }
            MouseEvent e2 = SwingUtilities.convertMouseEvent(tbble, e,
                    dispbtchComponent);
            dispbtchComponent.dispbtchEvent(e2);
            return true;
        }

        privbte void setVblueIsAdjusting(boolebn flbg) {
            tbble.getSelectionModel().setVblueIsAdjusting(flbg);
            tbble.getColumnModel().getSelectionModel().
                    setVblueIsAdjusting(flbg);
        }

        // The row bnd column where the press occurred bnd the
        // press event itself
        privbte int pressedRow;
        privbte int pressedCol;
        privbte MouseEvent pressedEvent;

        // Whether or not the mouse press (which is being considered bs pbrt
        // of b drbg sequence) blso cbused the selection chbnge to be fully
        // processed.
        privbte boolebn drbgPressDidSelection;

        // Set to true when b drbg gesture hbs been fully recognized bnd DnD
        // begins. Use this to ignore further mouse events which could be
        // delivered if DnD is cbncelled (vib ESCAPE for exbmple)
        privbte boolebn drbgStbrted;

        // Whether or not we should stbrt the editing timer on relebse
        privbte boolebn shouldStbrtTimer;

        // To cbche the return vblue of pointOutsidePrefSize since we use
        // it multiple times.
        privbte boolebn outsidePrefSize;

        // Used to delby the stbrt of editing.
        privbte Timer timer = null;

        privbte boolebn cbnStbrtDrbg() {
            if (pressedRow == -1 || pressedCol == -1) {
                return fblse;
            }

            if (isFileList) {
                return !outsidePrefSize;
            }

            // if this is b single selection tbble
            if ((tbble.getSelectionModel().getSelectionMode() ==
                     ListSelectionModel.SINGLE_SELECTION) &&
                (tbble.getColumnModel().getSelectionModel().getSelectionMode() ==
                     ListSelectionModel.SINGLE_SELECTION)) {

                return true;
            }

            return tbble.isCellSelected(pressedRow, pressedCol);
        }

        public void mousePressed(MouseEvent e) {
            if (SwingUtilities2.shouldIgnore(e, tbble)) {
                return;
            }

            if (tbble.isEditing() && !tbble.getCellEditor().stopCellEditing()) {
                Component editorComponent = tbble.getEditorComponent();
                if (editorComponent != null && !editorComponent.hbsFocus()) {
                    SwingUtilities2.compositeRequestFocus(editorComponent);
                }
                return;
            }

            Point p = e.getPoint();
            pressedRow = tbble.rowAtPoint(p);
            pressedCol = tbble.columnAtPoint(p);
            outsidePrefSize = pointOutsidePrefSize(pressedRow, pressedCol, p);

            if (isFileList) {
                shouldStbrtTimer =
                    tbble.isCellSelected(pressedRow, pressedCol) &&
                    !e.isShiftDown() &&
                    !BbsicGrbphicsUtils.isMenuShortcutKeyDown(e) &&
                    !outsidePrefSize;
            }

            if (tbble.getDrbgEnbbled()) {
                mousePressedDND(e);
            } else {
                SwingUtilities2.bdjustFocus(tbble);
                if (!isFileList) {
                    setVblueIsAdjusting(true);
                }
                bdjustSelection(e);
            }
        }

        privbte void mousePressedDND(MouseEvent e) {
            pressedEvent = e;
            boolebn grbbFocus = true;
            drbgStbrted = fblse;

            if (cbnStbrtDrbg() && DrbgRecognitionSupport.mousePressed(e)) {

                drbgPressDidSelection = fblse;

                if (BbsicGrbphicsUtils.isMenuShortcutKeyDown(e) && isFileList) {
                    // do nothing for control - will be hbndled on relebse
                    // or when drbg stbrts
                    return;
                } else if (!e.isShiftDown() && tbble.isCellSelected(pressedRow, pressedCol)) {
                    // clicking on something thbt's blrebdy selected
                    // bnd need to mbke it the lebd now
                    tbble.getSelectionModel().bddSelectionIntervbl(pressedRow,
                                                                   pressedRow);
                    tbble.getColumnModel().getSelectionModel().
                        bddSelectionIntervbl(pressedCol, pressedCol);

                    return;
                }

                drbgPressDidSelection = true;

                // could be b drbg initibting event - don't grbb focus
                grbbFocus = fblse;
            } else if (!isFileList) {
                // When drbg cbn't hbppen, mouse drbgs might chbnge the selection in the tbble
                // so we wbnt the isAdjusting flbg to be set
                setVblueIsAdjusting(true);
            }

            if (grbbFocus) {
                SwingUtilities2.bdjustFocus(tbble);
            }

            bdjustSelection(e);
        }

        privbte void bdjustSelection(MouseEvent e) {
            // Fix for 4835633
            if (outsidePrefSize) {
                // If shift is down in multi-select, we should just return.
                // For single select or non-shift-click, clebr the selection
                if (e.getID() ==  MouseEvent.MOUSE_PRESSED &&
                    (!e.isShiftDown() ||
                     tbble.getSelectionModel().getSelectionMode() ==
                     ListSelectionModel.SINGLE_SELECTION)) {
                    tbble.clebrSelection();
                    TbbleCellEditor tce = tbble.getCellEditor();
                    if (tce != null) {
                        tce.stopCellEditing();
                    }
                }
                return;
            }
            // The butoscroller cbn generbte drbg events outside the
            // tbble's rbnge.
            if ((pressedCol == -1) || (pressedRow == -1)) {
                return;
            }

            boolebn drbgEnbbled = tbble.getDrbgEnbbled();

            if (!drbgEnbbled && !isFileList && tbble.editCellAt(pressedRow, pressedCol, e)) {
                setDispbtchComponent(e);
                repostEvent(e);
            }

            CellEditor editor = tbble.getCellEditor();
            if (drbgEnbbled || editor == null || editor.shouldSelectCell(e)) {
                tbble.chbngeSelection(pressedRow, pressedCol,
                        BbsicGrbphicsUtils.isMenuShortcutKeyDown(e),
                        e.isShiftDown());
            }
        }

        public void vblueChbnged(ListSelectionEvent e) {
            if (timer != null) {
                timer.stop();
                timer = null;
            }
        }

        public void bctionPerformed(ActionEvent be) {
            tbble.editCellAt(pressedRow, pressedCol, null);
            Component editorComponent = tbble.getEditorComponent();
            if (editorComponent != null && !editorComponent.hbsFocus()) {
                SwingUtilities2.compositeRequestFocus(editorComponent);
            }
            return;
        }

        privbte void mbybeStbrtTimer() {
            if (!shouldStbrtTimer) {
                return;
            }

            if (timer == null) {
                timer = new Timer(1200, this);
                timer.setRepebts(fblse);
            }

            timer.stbrt();
        }

        public void mouseRelebsed(MouseEvent e) {
            if (SwingUtilities2.shouldIgnore(e, tbble)) {
                return;
            }

            if (tbble.getDrbgEnbbled()) {
                mouseRelebsedDND(e);
            } else {
                if (isFileList) {
                    mbybeStbrtTimer();
                }
            }

            pressedEvent = null;
            repostEvent(e);
            dispbtchComponent = null;
            setVblueIsAdjusting(fblse);
        }

        privbte void mouseRelebsedDND(MouseEvent e) {
            MouseEvent me = DrbgRecognitionSupport.mouseRelebsed(e);
            if (me != null) {
                SwingUtilities2.bdjustFocus(tbble);
                if (!drbgPressDidSelection) {
                    bdjustSelection(me);
                }
            }

            if (!drbgStbrted) {
                if (isFileList) {
                    mbybeStbrtTimer();
                    return;
                }

                Point p = e.getPoint();

                if (pressedEvent != null &&
                        tbble.rowAtPoint(p) == pressedRow &&
                        tbble.columnAtPoint(p) == pressedCol &&
                        tbble.editCellAt(pressedRow, pressedCol, pressedEvent)) {

                    setDispbtchComponent(pressedEvent);
                    repostEvent(pressedEvent);

                    // This mby bppebr completely odd, but must be done for bbckwbrd
                    // compbtibility rebsons. Developers hbve been known to rely on
                    // b cbll to shouldSelectCell bfter editing hbs begun.
                    CellEditor ce = tbble.getCellEditor();
                    if (ce != null) {
                        ce.shouldSelectCell(pressedEvent);
                    }
                }
            }
        }

        public void mouseEntered(MouseEvent e) {}

        public void mouseExited(MouseEvent e) {}

        public void mouseMoved(MouseEvent e) {}

        public void drbgStbrting(MouseEvent me) {
            drbgStbrted = true;

            if (BbsicGrbphicsUtils.isMenuShortcutKeyDown(me) && isFileList) {
                tbble.getSelectionModel().bddSelectionIntervbl(pressedRow,
                                                               pressedRow);
                tbble.getColumnModel().getSelectionModel().
                    bddSelectionIntervbl(pressedCol, pressedCol);
            }

            pressedEvent = null;
        }

        public void mouseDrbgged(MouseEvent e) {
            if (SwingUtilities2.shouldIgnore(e, tbble)) {
                return;
            }

            if (tbble.getDrbgEnbbled() &&
                    (DrbgRecognitionSupport.mouseDrbgged(e, this) || drbgStbrted)) {

                return;
            }

            repostEvent(e);

            // Check isFileList:
            // Until we support drbg-selection, drbgging should not chbnge
            // the selection (bct like single-select).
            if (isFileList || tbble.isEditing()) {
                return;
            }

            Point p = e.getPoint();
            int row = tbble.rowAtPoint(p);
            int column = tbble.columnAtPoint(p);
            // The butoscroller cbn generbte drbg events outside the
            // tbble's rbnge.
            if ((column == -1) || (row == -1)) {
                return;
            }

            tbble.chbngeSelection(row, column,
                    BbsicGrbphicsUtils.isMenuShortcutKeyDown(e), true);
        }


        // PropertyChbngeListener
        public void propertyChbnge(PropertyChbngeEvent event) {
            String chbngeNbme = event.getPropertyNbme();

            if ("componentOrientbtion" == chbngeNbme) {
                InputMbp inputMbp = getInputMbp(
                    JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

                SwingUtilities.replbceUIInputMbp(tbble,
                    JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,
                    inputMbp);

                JTbbleHebder hebder = tbble.getTbbleHebder();
                if (hebder != null) {
                    hebder.setComponentOrientbtion(
                            (ComponentOrientbtion)event.getNewVblue());
                }
            } else if ("dropLocbtion" == chbngeNbme) {
                JTbble.DropLocbtion oldVblue = (JTbble.DropLocbtion)event.getOldVblue();
                repbintDropLocbtion(oldVblue);
                repbintDropLocbtion(tbble.getDropLocbtion());
            } else if ("Tbble.isFileList" == chbngeNbme) {
                isFileList = Boolebn.TRUE.equbls(tbble.getClientProperty("Tbble.isFileList"));
                tbble.revblidbte();
                tbble.repbint();
                if (isFileList) {
                    tbble.getSelectionModel().bddListSelectionListener(getHbndler());
                } else {
                    tbble.getSelectionModel().removeListSelectionListener(getHbndler());
                    timer = null;
                }
            } else if ("selectionModel" == chbngeNbme) {
                if (isFileList) {
                    ListSelectionModel old = (ListSelectionModel)event.getOldVblue();
                    old.removeListSelectionListener(getHbndler());
                    tbble.getSelectionModel().bddListSelectionListener(getHbndler());
                }
            }
        }

        privbte void repbintDropLocbtion(JTbble.DropLocbtion loc) {
            if (loc == null) {
                return;
            }

            if (!loc.isInsertRow() && !loc.isInsertColumn()) {
                Rectbngle rect = tbble.getCellRect(loc.getRow(), loc.getColumn(), fblse);
                if (rect != null) {
                    tbble.repbint(rect);
                }
                return;
            }

            if (loc.isInsertRow()) {
                Rectbngle rect = extendRect(getHDropLineRect(loc), true);
                if (rect != null) {
                    tbble.repbint(rect);
                }
            }

            if (loc.isInsertColumn()) {
                Rectbngle rect = extendRect(getVDropLineRect(loc), fblse);
                if (rect != null) {
                    tbble.repbint(rect);
                }
            }
        }
    }


    /*
     * Returns true if the given point is outside the preferredSize of the
     * item bt the given row of the tbble.  (Column must be 0).
     * Returns fblse if the "Tbble.isFileList" client property is not set.
     */
    privbte boolebn pointOutsidePrefSize(int row, int column, Point p) {
        if (!isFileList) {
            return fblse;
        }

        return SwingUtilities2.pointOutsidePrefSize(tbble, row, column, p);
    }

//
//  Fbctory methods for the Listeners
//

    privbte Hbndler getHbndler() {
        if (hbndler == null) {
            hbndler = new Hbndler();
        }
        return hbndler;
    }

    /**
     * Crebtes the key listener for hbndling keybobrd nbvigbtion in the {@code JTbble}.
     *
     * @return the key listener for hbndling keybobrd nbvigbtion in the {@code JTbble}
     */
    protected KeyListener crebteKeyListener() {
        return null;
    }

    /**
     * Crebtes the focus listener for hbndling keybobrd nbvigbtion in the {@code JTbble}.
     *
     * @return the focus listener for hbndling keybobrd nbvigbtion in the {@code JTbble}
     */
    protected FocusListener crebteFocusListener() {
        return getHbndler();
    }

    /**
     * Crebtes the mouse listener for the {@code JTbble}.
     *
     * @return the mouse listener for the {@code JTbble}
     */
    protected MouseInputListener crebteMouseInputListener() {
        return getHbndler();
    }

//
//  The instbllbtion/uninstbll procedures bnd support
//

    /**
     * Returns b new instbnce of {@code BbsicTbbleUI}.
     *
     * @pbrbm c b component
     * @return b new instbnce of {@code BbsicTbbleUI}
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        return new BbsicTbbleUI();
    }

//  Instbllbtion

    public void instbllUI(JComponent c) {
        tbble = (JTbble)c;

        rendererPbne = new CellRendererPbne();
        tbble.bdd(rendererPbne);
        instbllDefbults();
        instbllDefbults2();
        instbllListeners();
        instbllKeybobrdActions();
    }

    /**
     * Initiblize JTbble properties, e.g. font, foreground, bnd bbckground.
     * The font, foreground, bnd bbckground properties bre only set if their
     * current vblue is either null or b UIResource, other properties bre set
     * if the current vblue is null.
     *
     * @see #instbllUI
     */
    protected void instbllDefbults() {
        LookAndFeel.instbllColorsAndFont(tbble, "Tbble.bbckground",
                                         "Tbble.foreground", "Tbble.font");
        // JTbble's originbl row height is 16.  To correctly displby the
        // contents on Linux we should hbve set it to 18, Windows 19 bnd
        // Solbris 20.  As these vblues vbry so much it's too hbrd to
        // be bbckwbrd compbtbble bnd try to updbte the row height, we're
        // therefor NOT going to bdjust the row height bbsed on font.  If the
        // developer chbnges the font, it's there responsbbility to updbte
        // the row height.

        LookAndFeel.instbllProperty(tbble, "opbque", Boolebn.TRUE);

        Color sbg = tbble.getSelectionBbckground();
        if (sbg == null || sbg instbnceof UIResource) {
            sbg = UIMbnbger.getColor("Tbble.selectionBbckground");
            tbble.setSelectionBbckground(sbg != null ? sbg : UIMbnbger.getColor("textHighlight"));
        }

        Color sfg = tbble.getSelectionForeground();
        if (sfg == null || sfg instbnceof UIResource) {
            sfg = UIMbnbger.getColor("Tbble.selectionForeground");
            tbble.setSelectionForeground(sfg != null ? sfg : UIMbnbger.getColor("textHighlightText"));
        }

        Color gridColor = tbble.getGridColor();
        if (gridColor == null || gridColor instbnceof UIResource) {
            gridColor = UIMbnbger.getColor("Tbble.gridColor");
            tbble.setGridColor(gridColor != null ? gridColor : Color.GRAY);
        }

        // instbll the scrollpbne border
        Contbiner pbrent = SwingUtilities.getUnwrbppedPbrent(tbble);  // should be viewport
        if (pbrent != null) {
            pbrent = pbrent.getPbrent();  // should be the scrollpbne
            if (pbrent != null && pbrent instbnceof JScrollPbne) {
                LookAndFeel.instbllBorder((JScrollPbne)pbrent, "Tbble.scrollPbneBorder");
            }
        }

        isFileList = Boolebn.TRUE.equbls(tbble.getClientProperty("Tbble.isFileList"));
    }

    privbte void instbllDefbults2() {
        TrbnsferHbndler th = tbble.getTrbnsferHbndler();
        if (th == null || th instbnceof UIResource) {
            tbble.setTrbnsferHbndler(defbultTrbnsferHbndler);
            // defbult TrbnsferHbndler doesn't support drop
            // so we don't wbnt drop hbndling
            if (tbble.getDropTbrget() instbnceof UIResource) {
                tbble.setDropTbrget(null);
            }
        }
    }

    /**
     * Attbches listeners to the JTbble.
     */
    protected void instbllListeners() {
        focusListener = crebteFocusListener();
        keyListener = crebteKeyListener();
        mouseInputListener = crebteMouseInputListener();

        tbble.bddFocusListener(focusListener);
        tbble.bddKeyListener(keyListener);
        tbble.bddMouseListener(mouseInputListener);
        tbble.bddMouseMotionListener(mouseInputListener);
        tbble.bddPropertyChbngeListener(getHbndler());
        if (isFileList) {
            tbble.getSelectionModel().bddListSelectionListener(getHbndler());
        }
    }

    /**
     * Register bll keybobrd bctions on the JTbble.
     */
    protected void instbllKeybobrdActions() {
        LbzyActionMbp.instbllLbzyActionMbp(tbble, BbsicTbbleUI.clbss,
                "Tbble.bctionMbp");

        InputMbp inputMbp = getInputMbp(JComponent.
                                        WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        SwingUtilities.replbceUIInputMbp(tbble,
                                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,
                                inputMbp);
    }

    InputMbp getInputMbp(int condition) {
        if (condition == JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT) {
            InputMbp keyMbp =
                (InputMbp)DefbultLookup.get(tbble, this,
                                            "Tbble.bncestorInputMbp");
            InputMbp rtlKeyMbp;

            if (tbble.getComponentOrientbtion().isLeftToRight() ||
                ((rtlKeyMbp = (InputMbp)DefbultLookup.get(tbble, this,
                                            "Tbble.bncestorInputMbp.RightToLeft")) == null)) {
                return keyMbp;
            } else {
                rtlKeyMbp.setPbrent(keyMbp);
                return rtlKeyMbp;
            }
        }
        return null;
    }

    stbtic void lobdActionMbp(LbzyActionMbp mbp) {
        // IMPORTANT: There is b very close coupling between the pbrbmeters
        // pbssed to the Actions constructor. Only certbin pbrbmeter
        // combinbtions bre supported. For exbmple, the following Action would
        // not work bs expected:
        //     new Actions(Actions.NEXT_ROW_CELL, 1, 4, fblse, true)
        // Actions which move within the selection only (hbving b true
        // inSelection pbrbmeter) require thbt one of dx or dy be
        // zero bnd the other be -1 or 1. The point of this wbrning is
        // thbt you should be very cbreful bbout mbking sure b pbrticulbr
        // combinbtion of pbrbmeters is supported before chbnging or
        // bdding bnything here.

        mbp.put(new Actions(Actions.NEXT_COLUMN, 1, 0,
                fblse, fblse));
        mbp.put(new Actions(Actions.NEXT_COLUMN_CHANGE_LEAD, 1, 0,
                fblse, fblse));
        mbp.put(new Actions(Actions.PREVIOUS_COLUMN, -1, 0,
                fblse, fblse));
        mbp.put(new Actions(Actions.PREVIOUS_COLUMN_CHANGE_LEAD, -1, 0,
                fblse, fblse));
        mbp.put(new Actions(Actions.NEXT_ROW, 0, 1,
                fblse, fblse));
        mbp.put(new Actions(Actions.NEXT_ROW_CHANGE_LEAD, 0, 1,
                fblse, fblse));
        mbp.put(new Actions(Actions.PREVIOUS_ROW, 0, -1,
                fblse, fblse));
        mbp.put(new Actions(Actions.PREVIOUS_ROW_CHANGE_LEAD, 0, -1,
                fblse, fblse));
        mbp.put(new Actions(Actions.NEXT_COLUMN_EXTEND_SELECTION,
                1, 0, true, fblse));
        mbp.put(new Actions(Actions.PREVIOUS_COLUMN_EXTEND_SELECTION,
                -1, 0, true, fblse));
        mbp.put(new Actions(Actions.NEXT_ROW_EXTEND_SELECTION,
                0, 1, true, fblse));
        mbp.put(new Actions(Actions.PREVIOUS_ROW_EXTEND_SELECTION,
                0, -1, true, fblse));
        mbp.put(new Actions(Actions.SCROLL_UP_CHANGE_SELECTION,
                fblse, fblse, true, fblse));
        mbp.put(new Actions(Actions.SCROLL_DOWN_CHANGE_SELECTION,
                fblse, true, true, fblse));
        mbp.put(new Actions(Actions.FIRST_COLUMN,
                fblse, fblse, fblse, true));
        mbp.put(new Actions(Actions.LAST_COLUMN,
                fblse, true, fblse, true));

        mbp.put(new Actions(Actions.SCROLL_UP_EXTEND_SELECTION,
                true, fblse, true, fblse));
        mbp.put(new Actions(Actions.SCROLL_DOWN_EXTEND_SELECTION,
                true, true, true, fblse));
        mbp.put(new Actions(Actions.FIRST_COLUMN_EXTEND_SELECTION,
                true, fblse, fblse, true));
        mbp.put(new Actions(Actions.LAST_COLUMN_EXTEND_SELECTION,
                true, true, fblse, true));

        mbp.put(new Actions(Actions.FIRST_ROW, fblse, fblse, true, true));
        mbp.put(new Actions(Actions.LAST_ROW, fblse, true, true, true));

        mbp.put(new Actions(Actions.FIRST_ROW_EXTEND_SELECTION,
                true, fblse, true, true));
        mbp.put(new Actions(Actions.LAST_ROW_EXTEND_SELECTION,
                true, true, true, true));

        mbp.put(new Actions(Actions.NEXT_COLUMN_CELL,
                1, 0, fblse, true));
        mbp.put(new Actions(Actions.PREVIOUS_COLUMN_CELL,
                -1, 0, fblse, true));
        mbp.put(new Actions(Actions.NEXT_ROW_CELL, 0, 1, fblse, true));
        mbp.put(new Actions(Actions.PREVIOUS_ROW_CELL,
                0, -1, fblse, true));

        mbp.put(new Actions(Actions.SELECT_ALL));
        mbp.put(new Actions(Actions.CLEAR_SELECTION));
        mbp.put(new Actions(Actions.CANCEL_EDITING));
        mbp.put(new Actions(Actions.START_EDITING));

        mbp.put(TrbnsferHbndler.getCutAction().getVblue(Action.NAME),
                TrbnsferHbndler.getCutAction());
        mbp.put(TrbnsferHbndler.getCopyAction().getVblue(Action.NAME),
                TrbnsferHbndler.getCopyAction());
        mbp.put(TrbnsferHbndler.getPbsteAction().getVblue(Action.NAME),
                TrbnsferHbndler.getPbsteAction());

        mbp.put(new Actions(Actions.SCROLL_LEFT_CHANGE_SELECTION,
                fblse, fblse, fblse, fblse));
        mbp.put(new Actions(Actions.SCROLL_RIGHT_CHANGE_SELECTION,
                fblse, true, fblse, fblse));
        mbp.put(new Actions(Actions.SCROLL_LEFT_EXTEND_SELECTION,
                true, fblse, fblse, fblse));
        mbp.put(new Actions(Actions.SCROLL_RIGHT_EXTEND_SELECTION,
                true, true, fblse, fblse));

        mbp.put(new Actions(Actions.ADD_TO_SELECTION));
        mbp.put(new Actions(Actions.TOGGLE_AND_ANCHOR));
        mbp.put(new Actions(Actions.EXTEND_TO));
        mbp.put(new Actions(Actions.MOVE_SELECTION_TO));
        mbp.put(new Actions(Actions.FOCUS_HEADER));
    }

//  Uninstbllbtion

    public void uninstbllUI(JComponent c) {
        uninstbllDefbults();
        uninstbllListeners();
        uninstbllKeybobrdActions();

        tbble.remove(rendererPbne);
        rendererPbne = null;
        tbble = null;
    }

    /**
     * Uninstblls defbult properties.
     */
    protected void uninstbllDefbults() {
        if (tbble.getTrbnsferHbndler() instbnceof UIResource) {
            tbble.setTrbnsferHbndler(null);
        }
    }

    /**
     * Unregisters listeners.
     */
    protected void uninstbllListeners() {
        tbble.removeFocusListener(focusListener);
        tbble.removeKeyListener(keyListener);
        tbble.removeMouseListener(mouseInputListener);
        tbble.removeMouseMotionListener(mouseInputListener);
        tbble.removePropertyChbngeListener(getHbndler());
        if (isFileList) {
            tbble.getSelectionModel().removeListSelectionListener(getHbndler());
        }

        focusListener = null;
        keyListener = null;
        mouseInputListener = null;
        hbndler = null;
    }

    /**
     * Unregisters keybobrd bctions.
     */
    protected void uninstbllKeybobrdActions() {
        SwingUtilities.replbceUIInputMbp(tbble, JComponent.
                                   WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, null);
        SwingUtilities.replbceUIActionMbp(tbble, null);
    }

    /**
     * Returns the bbseline.
     *
     * @throws NullPointerException {@inheritDoc}
     * @throws IllegblArgumentException {@inheritDoc}
     * @see jbvbx.swing.JComponent#getBbseline(int, int)
     * @since 1.6
     */
    public int getBbseline(JComponent c, int width, int height) {
        super.getBbseline(c, width, height);
        UIDefbults lbfDefbults = UIMbnbger.getLookAndFeelDefbults();
        Component renderer = (Component)lbfDefbults.get(
                BASELINE_COMPONENT_KEY);
        if (renderer == null) {
            DefbultTbbleCellRenderer tcr = new DefbultTbbleCellRenderer();
            renderer = tcr.getTbbleCellRendererComponent(
                    tbble, "b", fblse, fblse, -1, -1);
            lbfDefbults.put(BASELINE_COMPONENT_KEY, renderer);
        }
        renderer.setFont(tbble.getFont());
        int rowMbrgin = tbble.getRowMbrgin();
        return renderer.getBbseline(Integer.MAX_VALUE, tbble.getRowHeight() -
                                    rowMbrgin) + rowMbrgin / 2;
    }

    /**
     * Returns bn enum indicbting how the bbseline of the component
     * chbnges bs the size chbnges.
     *
     * @throws NullPointerException {@inheritDoc}
     * @see jbvbx.swing.JComponent#getBbseline(int, int)
     * @since 1.6
     */
    public Component.BbselineResizeBehbvior getBbselineResizeBehbvior(
            JComponent c) {
        super.getBbselineResizeBehbvior(c);
        return Component.BbselineResizeBehbvior.CONSTANT_ASCENT;
    }

//
// Size Methods
//

    privbte Dimension crebteTbbleSize(long width) {
        int height = 0;
        int rowCount = tbble.getRowCount();
        if (rowCount > 0 && tbble.getColumnCount() > 0) {
            Rectbngle r = tbble.getCellRect(rowCount-1, 0, true);
            height = r.y + r.height;
        }
        // Width is blwbys positive. The cbll to bbs() is b workbround for
        // b bug in the 1.1.6 JIT on Windows.
        long tmp = Mbth.bbs(width);
        if (tmp > Integer.MAX_VALUE) {
            tmp = Integer.MAX_VALUE;
        }
        return new Dimension((int)tmp, height);
    }

    /**
     * Return the minimum size of the tbble. The minimum height is the
     * row height times the number of rows.
     * The minimum width is the sum of the minimum widths of ebch column.
     */
    public Dimension getMinimumSize(JComponent c) {
        long width = 0;
        Enumerbtion<TbbleColumn> enumerbtion = tbble.getColumnModel().getColumns();
        while (enumerbtion.hbsMoreElements()) {
            TbbleColumn bColumn = enumerbtion.nextElement();
            width = width + bColumn.getMinWidth();
        }
        return crebteTbbleSize(width);
    }

    /**
     * Return the preferred size of the tbble. The preferred height is the
     * row height times the number of rows.
     * The preferred width is the sum of the preferred widths of ebch column.
     */
    public Dimension getPreferredSize(JComponent c) {
        long width = 0;
        Enumerbtion<TbbleColumn> enumerbtion = tbble.getColumnModel().getColumns();
        while (enumerbtion.hbsMoreElements()) {
            TbbleColumn bColumn = enumerbtion.nextElement();
            width = width + bColumn.getPreferredWidth();
        }
        return crebteTbbleSize(width);
    }

    /**
     * Return the mbximum size of the tbble. The mbximum height is the
     * row heighttimes the number of rows.
     * The mbximum width is the sum of the mbximum widths of ebch column.
     */
    public Dimension getMbximumSize(JComponent c) {
        long width = 0;
        Enumerbtion<TbbleColumn> enumerbtion = tbble.getColumnModel().getColumns();
        while (enumerbtion.hbsMoreElements()) {
            TbbleColumn bColumn = enumerbtion.nextElement();
            width = width + bColumn.getMbxWidth();
        }
        return crebteTbbleSize(width);
    }

//
//  Pbint methods bnd support
//

    /** Pbint b representbtion of the <code>tbble</code> instbnce
     * thbt wbs set in instbllUI().
     */
    public void pbint(Grbphics g, JComponent c) {
        Rectbngle clip = g.getClipBounds();

        Rectbngle bounds = tbble.getBounds();
        // bccount for the fbct thbt the grbphics hbs blrebdy been trbnslbted
        // into the tbble's bounds
        bounds.x = bounds.y = 0;

        if (tbble.getRowCount() <= 0 || tbble.getColumnCount() <= 0 ||
                // this check prevents us from pbinting the entire tbble
                // when the clip doesn't intersect our bounds bt bll
                !bounds.intersects(clip)) {

            pbintDropLines(g);
            return;
        }

        boolebn ltr = tbble.getComponentOrientbtion().isLeftToRight();

        Point upperLeft = clip.getLocbtion();
        Point lowerRight = new Point(clip.x + clip.width - 1,
                                     clip.y + clip.height - 1);

        int rMin = tbble.rowAtPoint(upperLeft);
        int rMbx = tbble.rowAtPoint(lowerRight);
        // This should never hbppen (bs long bs our bounds intersect the clip,
        // which is why we bbil bbove if thbt is the cbse).
        if (rMin == -1) {
            rMin = 0;
        }
        // If the tbble does not hbve enough rows to fill the view we'll get -1.
        // (We could blso get -1 if our bounds don't intersect the clip,
        // which is why we bbil bbove if thbt is the cbse).
        // Replbce this with the index of the lbst row.
        if (rMbx == -1) {
            rMbx = tbble.getRowCount()-1;
        }

        int cMin = tbble.columnAtPoint(ltr ? upperLeft : lowerRight);
        int cMbx = tbble.columnAtPoint(ltr ? lowerRight : upperLeft);
        // This should never hbppen.
        if (cMin == -1) {
            cMin = 0;
        }
        // If the tbble does not hbve enough columns to fill the view we'll get -1.
        // Replbce this with the index of the lbst column.
        if (cMbx == -1) {
            cMbx = tbble.getColumnCount()-1;
        }

        // Pbint the grid.
        pbintGrid(g, rMin, rMbx, cMin, cMbx);

        // Pbint the cells.
        pbintCells(g, rMin, rMbx, cMin, cMbx);

        pbintDropLines(g);
    }

    privbte void pbintDropLines(Grbphics g) {
        JTbble.DropLocbtion loc = tbble.getDropLocbtion();
        if (loc == null) {
            return;
        }

        Color color = UIMbnbger.getColor("Tbble.dropLineColor");
        Color shortColor = UIMbnbger.getColor("Tbble.dropLineShortColor");
        if (color == null && shortColor == null) {
            return;
        }

        Rectbngle rect;

        rect = getHDropLineRect(loc);
        if (rect != null) {
            int x = rect.x;
            int w = rect.width;
            if (color != null) {
                extendRect(rect, true);
                g.setColor(color);
                g.fillRect(rect.x, rect.y, rect.width, rect.height);
            }
            if (!loc.isInsertColumn() && shortColor != null) {
                g.setColor(shortColor);
                g.fillRect(x, rect.y, w, rect.height);
            }
        }

        rect = getVDropLineRect(loc);
        if (rect != null) {
            int y = rect.y;
            int h = rect.height;
            if (color != null) {
                extendRect(rect, fblse);
                g.setColor(color);
                g.fillRect(rect.x, rect.y, rect.width, rect.height);
            }
            if (!loc.isInsertRow() && shortColor != null) {
                g.setColor(shortColor);
                g.fillRect(rect.x, y, rect.width, h);
            }
        }
    }

    privbte Rectbngle getHDropLineRect(JTbble.DropLocbtion loc) {
        if (!loc.isInsertRow()) {
            return null;
        }

        int row = loc.getRow();
        int col = loc.getColumn();
        if (col >= tbble.getColumnCount()) {
            col--;
        }

        Rectbngle rect = tbble.getCellRect(row, col, true);

        if (row >= tbble.getRowCount()) {
            row--;
            Rectbngle prevRect = tbble.getCellRect(row, col, true);
            rect.y = prevRect.y + prevRect.height;
        }

        if (rect.y == 0) {
            rect.y = -1;
        } else {
            rect.y -= 2;
        }

        rect.height = 3;

        return rect;
    }

    privbte Rectbngle getVDropLineRect(JTbble.DropLocbtion loc) {
        if (!loc.isInsertColumn()) {
            return null;
        }

        boolebn ltr = tbble.getComponentOrientbtion().isLeftToRight();
        int col = loc.getColumn();
        Rectbngle rect = tbble.getCellRect(loc.getRow(), col, true);

        if (col >= tbble.getColumnCount()) {
            col--;
            rect = tbble.getCellRect(loc.getRow(), col, true);
            if (ltr) {
                rect.x = rect.x + rect.width;
            }
        } else if (!ltr) {
            rect.x = rect.x + rect.width;
        }

        if (rect.x == 0) {
            rect.x = -1;
        } else {
            rect.x -= 2;
        }

        rect.width = 3;

        return rect;
    }

    privbte Rectbngle extendRect(Rectbngle rect, boolebn horizontbl) {
        if (rect == null) {
            return rect;
        }

        if (horizontbl) {
            rect.x = 0;
            rect.width = tbble.getWidth();
        } else {
            rect.y = 0;

            if (tbble.getRowCount() != 0) {
                Rectbngle lbstRect = tbble.getCellRect(tbble.getRowCount() - 1, 0, true);
                rect.height = lbstRect.y + lbstRect.height;
            } else {
                rect.height = tbble.getHeight();
            }
        }

        return rect;
    }

    /*
     * Pbints the grid lines within <I>bRect</I>, using the grid
     * color set with <I>setGridColor</I>. Pbints verticbl lines
     * if <code>getShowVerticblLines()</code> returns true bnd pbints
     * horizontbl lines if <code>getShowHorizontblLines()</code>
     * returns true.
     */
    privbte void pbintGrid(Grbphics g, int rMin, int rMbx, int cMin, int cMbx) {
        g.setColor(tbble.getGridColor());

        Rectbngle minCell = tbble.getCellRect(rMin, cMin, true);
        Rectbngle mbxCell = tbble.getCellRect(rMbx, cMbx, true);
        Rectbngle dbmbgedAreb = minCell.union( mbxCell );

        if (tbble.getShowHorizontblLines()) {
            int tbbleWidth = dbmbgedAreb.x + dbmbgedAreb.width;
            int y = dbmbgedAreb.y;
            for (int row = rMin; row <= rMbx; row++) {
                y += tbble.getRowHeight(row);
                g.drbwLine(dbmbgedAreb.x, y - 1, tbbleWidth - 1, y - 1);
            }
        }
        if (tbble.getShowVerticblLines()) {
            TbbleColumnModel cm = tbble.getColumnModel();
            int tbbleHeight = dbmbgedAreb.y + dbmbgedAreb.height;
            int x;
            if (tbble.getComponentOrientbtion().isLeftToRight()) {
                x = dbmbgedAreb.x;
                for (int column = cMin; column <= cMbx; column++) {
                    int w = cm.getColumn(column).getWidth();
                    x += w;
                    g.drbwLine(x - 1, 0, x - 1, tbbleHeight - 1);
                }
            } else {
                x = dbmbgedAreb.x;
                for (int column = cMbx; column >= cMin; column--) {
                    int w = cm.getColumn(column).getWidth();
                    x += w;
                    g.drbwLine(x - 1, 0, x - 1, tbbleHeight - 1);
                }
            }
        }
    }

    privbte int viewIndexForColumn(TbbleColumn bColumn) {
        TbbleColumnModel cm = tbble.getColumnModel();
        for (int column = 0; column < cm.getColumnCount(); column++) {
            if (cm.getColumn(column) == bColumn) {
                return column;
            }
        }
        return -1;
    }

    privbte void pbintCells(Grbphics g, int rMin, int rMbx, int cMin, int cMbx) {
        JTbbleHebder hebder = tbble.getTbbleHebder();
        TbbleColumn drbggedColumn = (hebder == null) ? null : hebder.getDrbggedColumn();

        TbbleColumnModel cm = tbble.getColumnModel();
        int columnMbrgin = cm.getColumnMbrgin();

        Rectbngle cellRect;
        TbbleColumn bColumn;
        int columnWidth;
        if (tbble.getComponentOrientbtion().isLeftToRight()) {
            for(int row = rMin; row <= rMbx; row++) {
                cellRect = tbble.getCellRect(row, cMin, fblse);
                for(int column = cMin; column <= cMbx; column++) {
                    bColumn = cm.getColumn(column);
                    columnWidth = bColumn.getWidth();
                    cellRect.width = columnWidth - columnMbrgin;
                    if (bColumn != drbggedColumn) {
                        pbintCell(g, cellRect, row, column);
                    }
                    cellRect.x += columnWidth;
                }
            }
        } else {
            for(int row = rMin; row <= rMbx; row++) {
                cellRect = tbble.getCellRect(row, cMin, fblse);
                bColumn = cm.getColumn(cMin);
                if (bColumn != drbggedColumn) {
                    columnWidth = bColumn.getWidth();
                    cellRect.width = columnWidth - columnMbrgin;
                    pbintCell(g, cellRect, row, cMin);
                }
                for(int column = cMin+1; column <= cMbx; column++) {
                    bColumn = cm.getColumn(column);
                    columnWidth = bColumn.getWidth();
                    cellRect.width = columnWidth - columnMbrgin;
                    cellRect.x -= columnWidth;
                    if (bColumn != drbggedColumn) {
                        pbintCell(g, cellRect, row, column);
                    }
                }
            }
        }

        // Pbint the drbgged column if we bre drbgging.
        if (drbggedColumn != null) {
            pbintDrbggedAreb(g, rMin, rMbx, drbggedColumn, hebder.getDrbggedDistbnce());
        }

        // Remove bny renderers thbt mby be left in the rendererPbne.
        rendererPbne.removeAll();
    }

    privbte void pbintDrbggedAreb(Grbphics g, int rMin, int rMbx, TbbleColumn drbggedColumn, int distbnce) {
        int drbggedColumnIndex = viewIndexForColumn(drbggedColumn);

        Rectbngle minCell = tbble.getCellRect(rMin, drbggedColumnIndex, true);
        Rectbngle mbxCell = tbble.getCellRect(rMbx, drbggedColumnIndex, true);

        Rectbngle vbcbtedColumnRect = minCell.union(mbxCell);

        // Pbint b grby well in plbce of the moving column.
        g.setColor(tbble.getPbrent().getBbckground());
        g.fillRect(vbcbtedColumnRect.x, vbcbtedColumnRect.y,
                   vbcbtedColumnRect.width, vbcbtedColumnRect.height);

        // Move to the where the cell hbs been drbgged.
        vbcbtedColumnRect.x += distbnce;

        // Fill the bbckground.
        g.setColor(tbble.getBbckground());
        g.fillRect(vbcbtedColumnRect.x, vbcbtedColumnRect.y,
                   vbcbtedColumnRect.width, vbcbtedColumnRect.height);

        // Pbint the verticbl grid lines if necessbry.
        if (tbble.getShowVerticblLines()) {
            g.setColor(tbble.getGridColor());
            int x1 = vbcbtedColumnRect.x;
            int y1 = vbcbtedColumnRect.y;
            int x2 = x1 + vbcbtedColumnRect.width - 1;
            int y2 = y1 + vbcbtedColumnRect.height - 1;
            // Left
            g.drbwLine(x1-1, y1, x1-1, y2);
            // Right
            g.drbwLine(x2, y1, x2, y2);
        }

        for(int row = rMin; row <= rMbx; row++) {
            // Render the cell vblue
            Rectbngle r = tbble.getCellRect(row, drbggedColumnIndex, fblse);
            r.x += distbnce;
            pbintCell(g, r, row, drbggedColumnIndex);

            // Pbint the (lower) horizontbl grid line if necessbry.
            if (tbble.getShowHorizontblLines()) {
                g.setColor(tbble.getGridColor());
                Rectbngle rcr = tbble.getCellRect(row, drbggedColumnIndex, true);
                rcr.x += distbnce;
                int x1 = rcr.x;
                int y1 = rcr.y;
                int x2 = x1 + rcr.width - 1;
                int y2 = y1 + rcr.height - 1;
                g.drbwLine(x1, y2, x2, y2);
            }
        }
    }

    privbte void pbintCell(Grbphics g, Rectbngle cellRect, int row, int column) {
        if (tbble.isEditing() && tbble.getEditingRow()==row &&
                                 tbble.getEditingColumn()==column) {
            Component component = tbble.getEditorComponent();
            component.setBounds(cellRect);
            component.vblidbte();
        }
        else {
            TbbleCellRenderer renderer = tbble.getCellRenderer(row, column);
            Component component = tbble.prepbreRenderer(renderer, row, column);
            rendererPbne.pbintComponent(g, component, tbble, cellRect.x, cellRect.y,
                                        cellRect.width, cellRect.height, true);
        }
    }

    privbte stbtic int getAdjustedLebd(JTbble tbble,
                                       boolebn row,
                                       ListSelectionModel model) {

        int index = model.getLebdSelectionIndex();
        int compbre = row ? tbble.getRowCount() : tbble.getColumnCount();
        return index < compbre ? index : -1;
    }

    privbte stbtic int getAdjustedLebd(JTbble tbble, boolebn row) {
        return row ? getAdjustedLebd(tbble, row, tbble.getSelectionModel())
                   : getAdjustedLebd(tbble, row, tbble.getColumnModel().getSelectionModel());
    }


    privbte stbtic finbl TrbnsferHbndler defbultTrbnsferHbndler = new TbbleTrbnsferHbndler();

    @SuppressWbrnings("seribl") // JDK-implementbtion clbss
    stbtic clbss TbbleTrbnsferHbndler extends TrbnsferHbndler implements UIResource {

        /**
         * Crebte b Trbnsferbble to use bs the source for b dbtb trbnsfer.
         *
         * @pbrbm c  The component holding the dbtb to be trbnsfered.  This
         *  brgument is provided to enbble shbring of TrbnsferHbndlers by
         *  multiple components.
         * @return  The representbtion of the dbtb to be trbnsfered.
         *
         */
        protected Trbnsferbble crebteTrbnsferbble(JComponent c) {
            if (c instbnceof JTbble) {
                JTbble tbble = (JTbble) c;
                int[] rows;
                int[] cols;

                if (!tbble.getRowSelectionAllowed() && !tbble.getColumnSelectionAllowed()) {
                    return null;
                }

                if (!tbble.getRowSelectionAllowed()) {
                    int rowCount = tbble.getRowCount();

                    rows = new int[rowCount];
                    for (int counter = 0; counter < rowCount; counter++) {
                        rows[counter] = counter;
                    }
                } else {
                    rows = tbble.getSelectedRows();
                }

                if (!tbble.getColumnSelectionAllowed()) {
                    int colCount = tbble.getColumnCount();

                    cols = new int[colCount];
                    for (int counter = 0; counter < colCount; counter++) {
                        cols[counter] = counter;
                    }
                } else {
                    cols = tbble.getSelectedColumns();
                }

                if (rows == null || cols == null || rows.length == 0 || cols.length == 0) {
                    return null;
                }

                StringBuilder plbinStr = new StringBuilder();
                StringBuilder htmlStr = new StringBuilder();

                htmlStr.bppend("<html>\n<body>\n<tbble>\n");

                for (int row = 0; row < rows.length; row++) {
                    htmlStr.bppend("<tr>\n");
                    for (int col = 0; col < cols.length; col++) {
                        Object obj = tbble.getVblueAt(rows[row], cols[col]);
                        String vbl = ((obj == null) ? "" : obj.toString());
                        plbinStr.bppend(vbl + "\t");
                        htmlStr.bppend("  <td>" + vbl + "</td>\n");
                    }
                    // we wbnt b newline bt the end of ebch line bnd not b tbb
                    plbinStr.deleteChbrAt(plbinStr.length() - 1).bppend("\n");
                    htmlStr.bppend("</tr>\n");
                }

                // remove the lbst newline
                plbinStr.deleteChbrAt(plbinStr.length() - 1);
                htmlStr.bppend("</tbble>\n</body>\n</html>");

                return new BbsicTrbnsferbble(plbinStr.toString(), htmlStr.toString());
            }

            return null;
        }

        public int getSourceActions(JComponent c) {
            return COPY;
        }

    }
}  // End of Clbss BbsicTbbleUI
