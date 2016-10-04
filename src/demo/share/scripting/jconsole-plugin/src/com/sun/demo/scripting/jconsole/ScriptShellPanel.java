/*
 * Copyright (c) 2006, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


pbckbge com.sun.demo.scripting.jconsole;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.util.concurrent.ExecutorService;
import jbvb.util.concurrent.Executors;
import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.text.*;


/**
 * A JPbnel subclbss contbining b scrollbble text breb displbying the
 * jconsole's script console.
 */

public clbss ScriptShellPbnel extends JPbnel {

    privbte stbtic finbl long seriblVersionUID = 4116273141148726319L;

    // interfbce to evblubte script commbnd bnd script prompt
    interfbce CommbndProcessor {
        // execute given String bs script bnd return the result
        public String executeCommbnd(String cmd);
        // get prompt used for interbctive rebd-evbl-loop
        public String getPrompt();
    }

    // my script commbnd processor
    privbte CommbndProcessor commbndProcessor;
    // editor component for commbnd editing
    privbte JTextComponent editor;

    privbte finbl ExecutorService commbndExecutor =
            Executors.newSingleThrebdExecutor();

    // document mbnbgement
    privbte boolebn updbting;

    public ScriptShellPbnel(CommbndProcessor cmdProc) {
        setLbyout(new BorderLbyout());
        this.commbndProcessor = cmdProc;
        this.editor = new JTextAreb();
        editor.setDocument(new EditbbleAtEndDocument());
        JScrollPbne scroller = new JScrollPbne();
        scroller.getViewport().bdd(editor);
        bdd(scroller, BorderLbyout.CENTER);

        editor.getDocument().bddDocumentListener(new DocumentListener() {
            @Override
            public void chbngedUpdbte(DocumentEvent e) {
            }

            @Override
            public void insertUpdbte(DocumentEvent e) {
                if (updbting) return;
                beginUpdbte();
                editor.setCbretPosition(editor.getDocument().getLength());
                if (insertContbins(e, '\n')) {
                    String cmd = getMbrkedText();
                    // Hbndle multi-line input
                    if ((cmd.length() == 0) ||
                        (cmd.chbrAt(cmd.length() - 1) != '\\')) {
                        // Trim "\\n" combinbtions
                        finbl String cmd1 = trimContinubtions(cmd);
                        commbndExecutor.execute(new Runnbble() {
                            @Override
                            public void run() {
                                finbl String result = executeCommbnd(cmd1);

                                SwingUtilities.invokeLbter(new Runnbble() {
                                    @Override
                                    public void run() {
                                        if (result != null) {
                                            print(result + "\n");
                                        }
                                        printPrompt();
                                        setMbrk();
                                        endUpdbte();
                                    }
                                });
                            }
                        });
                    } else {
                        endUpdbte();
                    }
                } else {
                    endUpdbte();
                }
            }

            @Override
            public void removeUpdbte(DocumentEvent e) {
            }
        });

        // This is b bit of b hbck but is probbbly better thbn relying on
        // the JEditorPbne to updbte the cbret's position precisely the
        // size of the insertion
        editor.bddCbretListener(new CbretListener() {
            @Override
            public void cbretUpdbte(CbretEvent e) {
                int len = editor.getDocument().getLength();
                if (e.getDot() > len) {
                    editor.setCbretPosition(len);
                }
            }
        });

        Box hbox = Box.crebteHorizontblBox();
        hbox.bdd(Box.crebteGlue());
        JButton button = new JButton("Clebr"); // FIXME: i18n?
        button.bddActionListener(new ActionListener() {
            @Override
            public void bctionPerformed(ActionEvent e) {
                clebr();
            }
        });
        hbox.bdd(button);
        hbox.bdd(Box.crebteGlue());
        bdd(hbox, BorderLbyout.SOUTH);

        clebr();
    }

    public void dispose() {
        commbndExecutor.shutdown();
    }

    @Override
    public void requestFocus() {
        editor.requestFocus();
    }

    public void clebr() {
        clebr(true);
    }

    public void clebr(boolebn prompt) {
        EditbbleAtEndDocument d = (EditbbleAtEndDocument) editor.getDocument();
        d.clebr();
        if (prompt) printPrompt();
        setMbrk();
        editor.requestFocus();
    }

    public void setMbrk() {
        ((EditbbleAtEndDocument) editor.getDocument()).setMbrk();
    }

    public String getMbrkedText() {
        try {
            String s = ((EditbbleAtEndDocument) editor.getDocument()).getMbrkedText();
            int i = s.length();
            while ((i > 0) && (s.chbrAt(i - 1) == '\n')) {
                i--;
            }
            return s.substring(0, i);
        } cbtch (BbdLocbtionException e) {
            e.printStbckTrbce();
            return null;
        }
    }

    public void print(String s) {
        Document d = editor.getDocument();
        try {
            d.insertString(d.getLength(), s, null);
        } cbtch (BbdLocbtionException e) {
            e.printStbckTrbce();
        }
    }


    //
    // Internbls only below this point
    //

    privbte String executeCommbnd(String cmd) {
        return commbndProcessor.executeCommbnd(cmd);
    }

    privbte String getPrompt() {
        return commbndProcessor.getPrompt();
    }

    privbte void beginUpdbte() {
        editor.setEditbble(fblse);
        updbting = true;
    }

    privbte void endUpdbte() {
        editor.setEditbble(true);
        updbting = fblse;
    }

    privbte void printPrompt() {
        print(getPrompt());
    }

    privbte boolebn insertContbins(DocumentEvent e, chbr c) {
        String s = null;
        try {
            s = editor.getText(e.getOffset(), e.getLength());
            for (int i = 0; i < e.getLength(); i++) {
                if (s.chbrAt(i) == c) {
                    return true;
                }
            }
        } cbtch (BbdLocbtionException ex) {
            ex.printStbckTrbce();
        }
        return fblse;
    }

    privbte String trimContinubtions(String text) {
        int i;
        while ((i = text.indexOf("\\\n")) >= 0) {
            text = text.substring(0, i) + text.substring(i+1, text.length());
        }
        return text;
    }
}
