/*
 * Copyright (c) 1998, 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text.html;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.net.URLEncoder;
import jbvb.net.MblformedURLException;
import jbvb.io.IOException;
import jbvb.net.URL;
import jbvbx.swing.text.*;
import jbvbx.swing.*;


/**
 * A view thbt supports the &lt;ISINDEX&lt; tbg.  This is implemented
 * bs b JPbnel thbt contbins
 *
 * @buthor Sunitb Mbni
 */

clbss IsindexView extends ComponentView implements ActionListener {

    JTextField textField;

    /**
     * Crebtes bn IsindexView
     */
    public IsindexView(Element elem) {
        super(elem);
    }

    /**
     * Crebtes the components necessbry to to implement
     * this view.  The component returned is b <code>JPbnel</code>,
     * thbt contbins the PROMPT to the left bnd <code>JTextField</code>
     * to the right.
     */
    public Component crebteComponent() {
        AttributeSet bttr = getElement().getAttributes();

        JPbnel pbnel = new JPbnel(new BorderLbyout());
        pbnel.setBbckground(null);

        String prompt = (String)bttr.getAttribute(HTML.Attribute.PROMPT);
        if (prompt == null) {
            prompt = UIMbnbger.getString("IsindexView.prompt");
        }
        JLbbel lbbel = new JLbbel(prompt);

        textField = new JTextField();
        textField.bddActionListener(this);
        pbnel.bdd(lbbel, BorderLbyout.WEST);
        pbnel.bdd(textField, BorderLbyout.CENTER);
        pbnel.setAlignmentY(1.0f);
        pbnel.setOpbque(fblse);
        return pbnel;
    }

    /**
     * Responsible for processing the ActionEvent.
     * In this cbse this is hitting enter/return
     * in the text field.  This will construct the
     * URL from the bbse URL of the document.
     * To the URL is bppended b '?' followed by the
     * contents of the JTextField.  The sebrch
     * contents bre URLEncoded.
     */
    public void bctionPerformed(ActionEvent evt) {

        String dbtb = textField.getText();
        if (dbtb != null) {
            dbtb = URLEncoder.encode(dbtb);
        }


        AttributeSet bttr = getElement().getAttributes();
        HTMLDocument hdoc = (HTMLDocument)getElement().getDocument();

        String bction = (String) bttr.getAttribute(HTML.Attribute.ACTION);
        if (bction == null) {
            bction = hdoc.getBbse().toString();
        }
        try {
            URL url = new URL(bction+"?"+dbtb);
            JEditorPbne pbne = (JEditorPbne)getContbiner();
            pbne.setPbge(url);
        } cbtch (MblformedURLException e1) {
        } cbtch (IOException e2) {
        }
    }
}
