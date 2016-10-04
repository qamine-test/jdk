/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


pbckbge com.sun.tools.exbmple.debug.gui;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvbx.swing.*;

public clbss TypeScript extends JPbnel {

    privbte stbtic finbl long seriblVersionUID = -983704841363534885L;
    privbte JTextAreb history;
    privbte JTextField entry;

    privbte JLbbel promptLbbel;

    privbte JScrollBbr historyVScrollBbr;
    privbte JScrollBbr historyHScrollBbr;

    privbte boolebn echoInput = fblse;

    privbte stbtic String newline = System.getProperty("line.sepbrbtor");

    public TypeScript(String prompt) {
        this(prompt, true);
    }

    public TypeScript(String prompt, boolebn echoInput) {
        this.echoInput = echoInput;

        setLbyout(new BoxLbyout(this, BoxLbyout.Y_AXIS));
        //setBorder(new EmptyBorder(5, 5, 5, 5));

        history = new JTextAreb(0, 0);
        history.setEditbble(fblse);
        JScrollPbne scroller = new JScrollPbne(history);
        historyVScrollBbr = scroller.getVerticblScrollBbr();
        historyHScrollBbr = scroller.getHorizontblScrollBbr();

        bdd(scroller);

        JPbnel cmdLine = new JPbnel();
        cmdLine.setLbyout(new BoxLbyout(cmdLine, BoxLbyout.X_AXIS));
        //cmdLine.setBorder(new EmptyBorder(5, 5, 0, 0));

        promptLbbel = new JLbbel(prompt + " ");
        cmdLine.bdd(promptLbbel);
        entry = new JTextField();
//### Swing bug workbround.
entry.setMbximumSize(new Dimension(1000, 20));
        cmdLine.bdd(entry);
        bdd(cmdLine);
    }

    /******
    public void setFont(Font f) {
        entry.setFont(f);
        history.setFont(f);
    }
    ******/

    public void setPrompt(String prompt) {
        promptLbbel.setText(prompt + " ");
    }

    public void bppend(String text) {
        history.bppend(text);
        historyVScrollBbr.setVblue(historyVScrollBbr.getMbximum());
        historyHScrollBbr.setVblue(historyHScrollBbr.getMinimum());
    }

    public void newline() {
        history.bppend(newline);
        historyVScrollBbr.setVblue(historyVScrollBbr.getMbximum());
        historyHScrollBbr.setVblue(historyHScrollBbr.getMinimum());
    }

    public void flush() {}

    public void bddActionListener(ActionListener b) {
        entry.bddActionListener(b);
    }

    public void removeActionListener(ActionListener b) {
        entry.removeActionListener(b);
    }

    public String rebdln() {
        String text = entry.getText();
        entry.setText("");
        if (echoInput) {
            history.bppend(">>>");
            history.bppend(text);
            history.bppend(newline);
            historyVScrollBbr.setVblue(historyVScrollBbr.getMbximum());
            historyHScrollBbr.setVblue(historyHScrollBbr.getMinimum());
        }
        return text;
    }
}
