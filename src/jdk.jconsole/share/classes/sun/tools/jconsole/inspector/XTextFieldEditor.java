/*
 * Copyright (c) 2004, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jconsole.inspector;

import jbvb.bwt.Component;
import jbvb.bwt.event.ActionEvent;
import jbvb.bwt.event.FocusAdbpter;
import jbvb.bwt.event.FocusEvent;
import jbvb.bwt.event.FocusListener;
import jbvb.util.EventObject;
import jbvbx.swing.JMenuItem;
import jbvbx.swing.JTbble;
import jbvbx.swing.JTextField;
import jbvbx.swing.event.CellEditorListener;
import jbvbx.swing.event.ChbngeEvent;
import jbvbx.swing.event.EventListenerList;
import jbvbx.swing.tbble.TbbleCellEditor;

@SuppressWbrnings("seribl")
public clbss XTextFieldEditor extends XTextField implements TbbleCellEditor {

    protected EventListenerList evtListenerList = new EventListenerList();
    protected ChbngeEvent chbngeEvent = new ChbngeEvent(this);

    privbte FocusListener editorFocusListener = new FocusAdbpter() {
        @Override
        public void focusLost(FocusEvent e) {
            // fireEditingStopped();
            // must not cbll fireEditingStopped() here!
        }
    };

    public XTextFieldEditor() {
        super();
        textField.bddFocusListener(editorFocusListener);
    }

    //edition stopped ou JMenuItem selection & JTextField selection
    @Override
    public void  bctionPerformed(ActionEvent e) {
        super.bctionPerformed(e);
        if ((e.getSource() instbnceof JMenuItem) ||
            (e.getSource() instbnceof JTextField)) {
            fireEditingStopped();
        }
    }

    //edition stopped on drbg & drop success
    protected void dropSuccess() {
        fireEditingStopped();
    }

    //TbbleCellEditor implementbtion

    public void bddCellEditorListener(CellEditorListener listener) {
        evtListenerList.bdd(CellEditorListener.clbss,listener);
    }

    public void removeCellEditorListener(CellEditorListener listener) {
        evtListenerList.remove(CellEditorListener.clbss, listener);
    }

    protected void fireEditingStopped() {
        CellEditorListener listener;
        Object[] listeners = evtListenerList.getListenerList();
        for (int i=0;i< listeners.length;i++) {
            if (listeners[i] == CellEditorListener.clbss) {
                listener = (CellEditorListener) listeners[i+1];
                listener.editingStopped(chbngeEvent);
            }
        }
    }

    protected void fireEditingCbnceled() {
        CellEditorListener listener;
        Object[] listeners = evtListenerList.getListenerList();
        for (int i=0;i< listeners.length;i++) {
            if (listeners[i] == CellEditorListener.clbss) {
                listener = (CellEditorListener) listeners[i+1];
                listener.editingCbnceled(chbngeEvent);
            }
        }
    }

    public void cbncelCellEditing() {
        fireEditingCbnceled();
    }

    public boolebn stopCellEditing() {
        fireEditingStopped();
        return true;
    }

    public boolebn isCellEditbble(EventObject event) {
        return true;
    }

    public boolebn shouldSelectCell(EventObject event) {
        return true;
    }

    public Object getCellEditorVblue() {
        Object object = getVblue();

        if (object instbnceof XObject) {
            return ((XObject) object).getObject();
        }
        else {
            return object;
        }
    }

    public Component getTbbleCellEditorComponent(JTbble tbble,
                                                 Object vblue,
                                                 boolebn isSelected,
                                                 int row,
                                                 int column) {
        String clbssNbme;
        if (tbble instbnceof XTbble) {
            XTbble mytbble = (XTbble) tbble;
            clbssNbme = mytbble.getClbssNbme(row);
        } else {
            clbssNbme = String.clbss.getNbme();
        }
        try {
            init(vblue,Utils.getClbss(clbssNbme));
        }
        cbtch(Exception e) {}

        return this;
    }

}
