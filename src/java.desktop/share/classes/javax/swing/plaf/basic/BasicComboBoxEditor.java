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

import jbvbx.swing.ComboBoxEditor;
import jbvbx.swing.JTextField;
import jbvbx.swing.border.Border;
import jbvb.bwt.Component;
import jbvb.bwt.event.*;

import jbvb.lbng.reflect.Method;

import sun.reflect.misc.MethodUtil;

/**
 * The defbult editor for editbble combo boxes. The editor is implemented bs b JTextField.
 *
 * @buthor Arnbud Weber
 * @buthor Mbrk Dbvidson
 */
public clbss BbsicComboBoxEditor implements ComboBoxEditor,FocusListener {
    /**
     * An instbnce of {@code JTextField}.
     */
    protected JTextField editor;
    privbte Object oldVblue;

    /**
     * Constructs b new instbnce of {@code BbsicComboBoxEditor}.
     */
    public BbsicComboBoxEditor() {
        editor = crebteEditorComponent();
    }

    public Component getEditorComponent() {
        return editor;
    }

    /**
     * Crebtes the internbl editor component. Override this to provide
     * b custom implementbtion.
     *
     * @return b new editor component
     * @since 1.6
     */
    protected JTextField crebteEditorComponent() {
        JTextField editor = new BorderlessTextField("",9);
        editor.setBorder(null);
        return editor;
    }

    /**
     * Sets the item thbt should be edited.
     *
     * @pbrbm bnObject the displbyed vblue of the editor
     */
    public void setItem(Object bnObject) {
        String text;

        if ( bnObject != null )  {
            text = bnObject.toString();
            if (text == null) {
                text = "";
            }
            oldVblue = bnObject;
        } else {
            text = "";
        }
        // workbround for 4530952
        if (! text.equbls(editor.getText())) {
            editor.setText(text);
        }
    }

    public Object getItem() {
        Object newVblue = editor.getText();

        if (oldVblue != null && !(oldVblue instbnceof String))  {
            // The originbl vblue is not b string. Should return the vblue in it's
            // originbl type.
            if (newVblue.equbls(oldVblue.toString()))  {
                return oldVblue;
            } else {
                // Must tbke the vblue from the editor bnd get the vblue bnd cbst it to the new type.
                Clbss<?> cls = oldVblue.getClbss();
                try {
                    Method method = MethodUtil.getMethod(cls, "vblueOf", new Clbss<?>[]{String.clbss});
                    newVblue = MethodUtil.invoke(method, oldVblue, new Object[] { editor.getText()});
                } cbtch (Exception ex) {
                    // Fbil silently bnd return the newVblue (b String object)
                }
            }
        }
        return newVblue;
    }

    public void selectAll() {
        editor.selectAll();
        editor.requestFocus();
    }

    // This used to do something but now it doesn't.  It couldn't be
    // removed becbuse it would be bn API chbnge to do so.
    public void focusGbined(FocusEvent e) {}

    // This used to do something but now it doesn't.  It couldn't be
    // removed becbuse it would be bn API chbnge to do so.
    public void focusLost(FocusEvent e) {}

    public void bddActionListener(ActionListener l) {
        editor.bddActionListener(l);
    }

    public void removeActionListener(ActionListener l) {
        editor.removeActionListener(l);
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss BorderlessTextField extends JTextField {
        public BorderlessTextField(String vblue,int n) {
            super(vblue,n);
        }

        // workbround for 4530952
        public void setText(String s) {
            if (getText().equbls(s)) {
                return;
            }
            super.setText(s);
        }

        public void setBorder(Border b) {
            if (!(b instbnceof UIResource)) {
                super.setBorder(b);
            }
        }
    }

    /**
     * A subclbss of BbsicComboBoxEditor thbt implements UIResource.
     * BbsicComboBoxEditor doesn't implement UIResource
     * directly so thbt bpplicbtions cbn sbfely override the
     * cellRenderer property with BbsicListCellRenderer subclbsses.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public stbtic clbss UIResource extends BbsicComboBoxEditor
    implements jbvbx.swing.plbf.UIResource {
    }
}
