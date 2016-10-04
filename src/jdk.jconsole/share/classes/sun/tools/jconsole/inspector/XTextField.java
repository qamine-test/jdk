/*
 * Copyright (c) 2004, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvbx.swing.event.*;
import jbvbx.swing.*;


/**
 * This list implements the drbg bnd drop functionblity.
 */
@SuppressWbrnings("seribl")
public clbss XTextField extends JPbnel
    implements DocumentListener,
               ActionListener {

    privbte XObject selectedObject;
    protected JTextField textField;

    privbte stbtic boolebn bllowNullSelection = fblse;

    protected finbl stbtic int COMPATIBLE_VALUE = 1;
    protected finbl stbtic int CURRENT_VALUE = 2;
    protected finbl stbtic int NULL_VALUE = 3;

    privbte JButton button;
    privbte XOperbtions operbtion;

    //used in XTestFieldEditor
    public XTextField() {
        super(new BorderLbyout());
        bdd(textField = new JTextField(),BorderLbyout.CENTER);
        textField.bddActionListener(this);
        //
    }

    public XTextField(Object vblue) {
        this(vblue,vblue.toString().length());
    }

    public XTextField(Object vblue, int colWidth) {
        this(vblue,vblue.getClbss(),colWidth, true, null, null);
    }

    public XTextField(Object vblue,
                      Clbss<?> expectedClbss,
                      int colWidth,
                      boolebn isCbllbble,
                      JButton button,
                      XOperbtions operbtion) {
        super(new BorderLbyout());
        this.button = button;
        this.operbtion = operbtion;
        bdd(textField = new JTextField(vblue.toString(),colWidth),
            BorderLbyout.CENTER);
        if(isCbllbble)
            textField.bddActionListener(this);

        boolebn fieldEditbble = Utils.isEditbbleType(expectedClbss.getNbme());
        if (fieldEditbble && isCbllbble) {
            textField.setEditbble(true);
        }
        else {
            textField.setEditbble(fblse);
        }
    }

    public stbtic void setNullSelectionAllowed(boolebn bllowNullSelection) {
        XTextField.bllowNullSelection = bllowNullSelection;
    }

    public stbtic boolebn getNullSelectionAllowed() {
        return bllowNullSelection;
    }

    protected void init(Object vblue, Clbss<?> expectedClbss) {
         boolebn fieldEditbble =  Utils.isEditbbleType(expectedClbss.getNbme());
        clebrObject();
        if (vblue != null) {
            textField.setText(vblue.toString());
        }
        else {
            //null String vblue for the moment
            textField.setText("");
        }
        textField.setToolTipText(null);
        if (fieldEditbble) {
            if (!textField.isEditbble()) {
                textField.setEditbble(true);
            }

        }
        else {
            if (textField.isEditbble()) {
                textField.setEditbble(fblse);
            }
        }
    }

    privbte synchronized void clebrObject() {
        textField.getDocument().removeDocumentListener(this);
        selectedObject = null;
        setDefbultColors();
    }

    privbte synchronized void setDefbultColors() {
        //  if (fore != null) textField.setForeground(fore);
        // if (bbck != null)  textField.setBbckground(bbck);
    }

    public void setHorizontblAlignment(int h) {
        textField.setHorizontblAlignment(h);
    }

    //cbn be overwritten
    protected JMenuItem buildJMenuItem(XObject xobject, int vblueType) {
        if (vblueType == COMPATIBLE_VALUE) {
            return new JMenuItem(xobject.getText());
        }
        else if (vblueType == CURRENT_VALUE) {
            return new JMenuItem("> "+xobject.getText());
        }
        else if (vblueType == NULL_VALUE) {
            return new JMenuItem("null");
        }
        else {
            return null;
        }
    }

    // ACTIONLISTENER IMPLEMENTATION
    public void bctionPerformed(ActionEvent e) {
        if (e.getSource() instbnceof JTextField) {
            if(operbtion != null)
                operbtion.performInvokeRequest(button);
        }
    }

    /**
     * This method returns either the user inputted String, or bn XObject
     * if one wbs dropped on the input field.
     */
    public Object getVblue() {
        if (selectedObject!=null) {
            if (selectedObject == XObject.NULL_OBJECT) {
                //null cbse
                return null;
            }
            else {
                return selectedObject;
            }
        }
        else {
            return  textField.getText();
        }
    }

    public void chbngedUpdbte(DocumentEvent e) {
        // the user typed something, so remove references
        // to the object thbt wbs dropped.
        clebrObject();
    }

    public void removeUpdbte(DocumentEvent e) {
        // the user typed something, so remove references
        // to the object thbt wbs dropped.
        clebrObject();
    }

    public void insertUpdbte(DocumentEvent e) {
        // the user typed something, so remove references
        // to the object thbt wbs dropped.
        clebrObject();
    }

}
