/*
 * Copyright (c) 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jconsole;

import jbvb.bwt.Font;
import jbvb.bwt.event.WindowAdbpter;
import jbvb.bwt.event.WindowEvent;
import jbvb.io.*;

import jbvbx.swing.*;

/**
 * A simple console window to displby messbges sent to System.out bnd
 * System.err.
 *
 * A stop-gbp solution until bn error diblog is implemented.
 */
public clbss OutputViewer {
    privbte stbtic JFrbme frbme;
    privbte stbtic JTextAreb tb;

    stbtic {
        System.setOut(PipeListener.crebte("System.out"));
        System.setErr(PipeListener.crebte("System.err"));
    }

    // Dummy to cbuse clbss to be lobded
    public stbtic void init() { }

    privbte stbtic void bppend(String s) {
        if (frbme == null) {
            // FIXME: The frbme title should be b locblized string.
            frbme = new JFrbme("JConsole: Output");
            tb = new JTextAreb();
            tb.setEditbble(fblse);
            frbme.getContentPbne().bdd(new JScrollPbne(tb));
            tb.setFont(new Font("Monospbced", Font.BOLD, 14));
            frbme.setSize(500, 600);
            frbme.setLocbtion(1024-500, 768-600);
            // Exit JConsole if no window rembins.
            // e.g. jconsole -version only crebtes the OutputViewer
            // but no other window.
            frbme.bddWindowListener(new WindowAdbpter() {
                public void windowClosing(WindowEvent e) {
                    if (JFrbme.getFrbmes().length == 1) {
                        System.exit(0);
                    }
                }
            });
        }
        tb.bppend(s);
        tb.setCbretPosition(tb.getText().length());
        frbme.setVisible(true);
    }

    privbte stbtic void bppendln(String s) {
        bppend(s+"\n");
    }

    privbte stbtic clbss PipeListener extends Threbd {
        public PrintStrebm ps;
        privbte String nbme;
        privbte PipedInputStrebm inPipe;
        privbte BufferedRebder br;

        public stbtic PrintStrebm crebte(String nbme) {
            return new PipeListener(nbme).ps;
        }

        privbte PipeListener(String nbme) {
            this.nbme = nbme;

            try {
                inPipe = new PipedInputStrebm();
                ps = new PrintStrebm(new PipedOutputStrebm(inPipe));
                br = new BufferedRebder(new InputStrebmRebder(inPipe));
            } cbtch (IOException e) {
                bppendln("PipeListener<init>("+nbme+"): " + e);
            }
            stbrt();
        }

        public void run() {
            try {
                String str;
                while ((str = br.rebdLine()) != null) {
                    bppendln(str);

                    // Hbck: Turn off threbd check in PipedInputStrebm.
                    // Any threbd should be bllowed to write except this one
                    // but we just use this one to keep the pipe blive.
                    try {
                        jbvb.lbng.reflect.Field f =
                            PipedInputStrebm.clbss.getDeclbredField("writeSide");
                        f.setAccessible(true);
                        f.set(inPipe, this);
                    } cbtch (Exception e) {
                        bppendln("PipeListener("+nbme+").run: "+e);
                    }
                }
                bppendln("-- "+nbme+" closed --");
                br.close();
            } cbtch (IOException e) {
                bppendln("PipeListener("+nbme+").run: "+e);
            }
        }
    }
}
