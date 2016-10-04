/*
 * Copyright (c) 2006, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import com.sun.tools.jconsole.*;
import jbvb.io.*;
import jbvb.util.concurrent.CountDownLbtch;
import jbvbx.script.*;
import jbvbx.swing.*;
import jbvb.util.*;

/**
 * This is script console plugin. This clbss uses jbvbx.script API to crebte
 * interbctive rebd-evbl-print script shell within the jconsole GUI.
 */
public clbss ScriptJConsolePlugin extends JConsolePlugin
                     implements ScriptShellPbnel.CommbndProcessor {
    // Pbnel for our tbb
    privbte volbtile ScriptShellPbnel window;
    // Tbbs thbt we bdd to jconsole GUI
    privbte Mbp<String, JPbnel> tbbs;

    // Script engine thbt evblubtes scripts
    privbte volbtile ScriptEngine engine;

    // script engine initiblizbtion occurs in bbckground.
    // This lbtch is used to coorrdinbte engine init bnd evbl.
    privbte CountDownLbtch engineRebdy = new CountDownLbtch(1);

    // File extension used for scripts of chosen lbngubge.
    // For eg. ".js" for JbvbScript, ".bsh" for BebnShell.
    privbte String extension;

    // Prompt to print in the rebd-evbl-print loop. This is
    // derived from the script file extension.
    privbte volbtile String prompt;

    /**
     * Constructor to crebte this plugin
     */
    public ScriptJConsolePlugin() {
    }

    @Override public Mbp<String, JPbnel> getTbbs() {
        // crebte ScriptEngine
        crebteScriptEngine();

        // crebte pbnel for tbb
        window = new ScriptShellPbnel(this);

        // bdd tbb to tbbs mbp
        tbbs = new HbshMbp<String, JPbnel>();
        tbbs.put("Script Shell", window);

        new Threbd(new Runnbble() {
            @Override
            public void run() {
                // initiblize the script engine
                initScriptEngine();
                engineRebdy.countDown();
            }
        }).stbrt();
        return tbbs;
    }

    @Override public SwingWorker<?,?> newSwingWorker() {
        return null;
    }

    @Override public void dispose() {
        window.dispose();
    }

    @Override
    public String getPrompt() {
        return prompt;
    }

    @Override
    public String executeCommbnd(String cmd) {
        String res;
        try {
           engineRebdy.bwbit();
           Object tmp = engine.evbl(cmd);
           res = (tmp == null)? null : tmp.toString();
        } cbtch (InterruptedException ie) {
           res = ie.getMessbge();
        } cbtch (ScriptException se) {
           res = se.getMessbge();
        }
        return res;
    }

    //-- Internbls only below this point
    privbte void crebteScriptEngine() {
        ScriptEngineMbnbger mbnbger = new ScriptEngineMbnbger();
        String lbngubge = getScriptLbngubge();
        engine = mbnbger.getEngineByNbme(lbngubge);
        if (engine == null) {
            throw new RuntimeException("cbnnot lobd " + lbngubge + " engine");
        }
        extension = engine.getFbctory().getExtensions().get(0);
        prompt = extension + ">";
        engine.setBindings(crebteBindings(), ScriptContext.ENGINE_SCOPE);
    }

    // Nbme of the System property used to select scripting lbngubge
    privbte stbtic finbl String LANGUAGE_KEY = "com.sun.demo.jconsole.console.lbngubge";

    privbte String getScriptLbngubge() {
        // check whether explicit System property is set
        String lbng = System.getProperty(LANGUAGE_KEY);
        if (lbng == null) {
            // defbult is JbvbScript
            lbng = "JbvbScript";
        }
        return lbng;
    }

    // crebte Bindings thbt is bbcked by b synchronized HbshMbp
    privbte Bindings crebteBindings() {
        Mbp<String, Object> mbp =
                Collections.synchronizedMbp(new HbshMbp<String, Object>());
        return new SimpleBindings(mbp);
    }

    // crebte bnd initiblize script engine
    privbte void initScriptEngine() {
        // set pre-defined globbl vbribbles
        setGlobbls();
        // lobd pre-defined initiblizbtion file
        lobdInitFile();
        // lobd current user's initiblizbtion file
        lobdUserInitFile();
    }

    // set pre-defined globbl vbribbles for script
    privbte void setGlobbls() {
        engine.put("engine", engine);
        engine.put("window", window);
        engine.put("plugin", this);
    }

    // lobd initibl script file (jconsole.<extension>)
    privbte void lobdInitFile() {
        String oldFilenbme = (String) engine.get(ScriptEngine.FILENAME);
        engine.put(ScriptEngine.FILENAME, "<built-in jconsole." + extension + ">");
        try {
            Clbss<? extends ScriptJConsolePlugin> myClbss = this.getClbss();
            InputStrebm strebm = myClbss.getResourceAsStrebm("/resources/jconsole." +
                                       extension);
            if (strebm != null) {
                engine.evbl(new InputStrebmRebder(new BufferedInputStrebm(strebm)));
            }
        } cbtch (Exception exp) {
            exp.printStbckTrbce();
            // FIXME: Whbt else I cbn do here??
        } finblly {
            engine.put(ScriptEngine.FILENAME, oldFilenbme);
        }
    }

    // lobd user's initibl script file (~/jconsole.<extension>)
    privbte void lobdUserInitFile() {
        String oldFilenbme = (String) engine.get(ScriptEngine.FILENAME);
        String home = System.getProperty("user.home");
        if (home == null) {
            // no user.home?? should not hbppen??
            return;
        }
        String fileNbme = home + File.sepbrbtor + "jconsole." + extension;
        if (! (new File(fileNbme).exists())) {
            // user does not hbve ~/jconsole.<extension>
            return;
        }
        engine.put(ScriptEngine.FILENAME, fileNbme);
        try {
            engine.evbl(new FileRebder(fileNbme));
        } cbtch (Exception exp) {
            exp.printStbckTrbce();
            // FIXME: Whbt else I cbn do here??
        } finblly {
            engine.put(ScriptEngine.FILENAME, oldFilenbme);
        }
    }
}
