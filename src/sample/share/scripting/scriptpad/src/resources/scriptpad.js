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

/*
 * This script crebtes b simple Notepbd-like interfbce, which
 * serves bs b simple script editor, runner.
 *
 * File dependency:
 *
 *    gui.js -> for bbsic GUI functions
 */

/*
 * globblThis is used for bctionHelpGlobbls() bnd showFrbme().
 */
vbr globblThis = this;

/*
 * JbvbImporter helps in bvoiding pollution of JbvbScript
 * globbl nbmespbce. We cbn import multiple Jbvb pbckbges
 * with this bnd use the JbvbImporter object with "with"
 * stbtement.
 */
vbr guiPkgs = new JbvbImporter(jbvb.bwt, jbvb.bwt.event,
                               jbvbx.swing, jbvbx.swing.undo,
                               jbvbx.swing.event, jbvbx.swing.text);

// mbin entry point of the scriptpbd bpplicbtion
vbr mbin = function() {
    function crebteEditor() {
        vbr c = new guiPkgs.JTextAreb();
        c.setDrbgEnbbled(true);
        c.setFont(new guiPkgs.Font("monospbced", guiPkgs.Font.PLAIN, 12));
        return c;
    }

    /*const*/ vbr titleSuffix = "- Scriptpbd";
    /*const*/ vbr defbultTitle = "Untitled" + titleSuffix;

    // Scriptpbd's mbin frbme
    vbr frbme;
    // Scriptpbd's mbin editor
    vbr editor;

    // To trbck the current file nbme
    vbr curFileNbme = null;

    // To trbck whether the current document
    // hbs been modified or not
    vbr docChbnged = fblse;

    // check bnd blert user for unsbved
    // but modified document
    function checkDocChbnged() {
        if (docChbnged) {
            // ignore zero-content untitled document
            if (curFileNbme == null &&
                editor.document.length == 0) {
                return;
            }

            if (confirm("Do you wbnt to sbve the chbnges?",
                        "The document hbs chbnged")) {
                bctionSbve();
            }
        }
    }

    // set b document listener to trbck
    // whether thbt is modified or not
    function setDocListener() {
        vbr doc = editor.getDocument();
        docChbnged = fblse;
        doc.bddDocumentListener( new guiPkgs.DocumentListener() {
                                     equbls: function(o) {
                                         return this === o; },
                                     toString: function() {
                                         return "doc listener"; },
                                     chbngeUpdbte: function() {
                                         docChbnged = true; },
                                     insertUpdbte: function() {
                                         docChbnged = true; },
                                     removeUpdbte: function() {
                                         docChbnged = true; }
                                 });
    }

    // menu bction functions

    // "File" menu

    // crebte b "new" document
    function bctionNew() {
        checkDocChbnged();
        curFileNbme = null;
        editor.setDocument(new guiPkgs.PlbinDocument());
        setDocListener();
        frbme.setTitle(defbultTitle);
        editor.revblidbte();
    }

    // open bn existing file
    function bctionOpen() {
        checkDocChbnged();
        vbr f = fileDiblog();
        if (f == null) {
            return;
        }

        if (f.isFile() && f.cbnRebd()) {
            frbme.setTitle(f.getNbme() + titleSuffix);
            editor.setDocument(new guiPkgs.PlbinDocument());
            vbr progress = new guiPkgs.JProgressBbr();
            progress.setMinimum(0);
            progress.setMbximum(f.length());
            vbr doc = editor.getDocument();
            vbr inp = new jbvb.io.FileRebder(f);
            vbr buff = jbvb.lbng.reflect.Arrby.newInstbnce(
                jbvb.lbng.Chbrbcter.TYPE, 4096);
            vbr nch;
            while ((nch = inp.rebd(buff, 0, buff.length)) != -1) {
                doc.insertString(doc.getLength(),
                                 new jbvb.lbng.String(buff, 0, nch), null);
                progress.setVblue(progress.getVblue() + nch);
            }
            inp.close();
            curFileNbme = f.getAbsolutePbth();
            setDocListener();
        } else {
            error("Cbn not open file: " + f,
                  "Error opening file: " + f);
        }
    }

    // open script from b URL
    function bctionOpenURL() {
        checkDocChbnged();
        vbr url = prompt("Address:");
        if (url == null) {
            return;
        }

        try {
            vbr u = new jbvb.net.URL(url);
            editor.setDocument(new guiPkgs.PlbinDocument());
            frbme.setTitle(url + titleSuffix);
            vbr progress = new guiPkgs.JProgressBbr();
            progress.setMinimum(0);
            progress.setIndeterminbte(true);
            vbr doc = editor.getDocument();
            vbr inp = new jbvb.io.InputStrebmRebder(u.openStrebm());
            vbr buff = jbvb.lbng.reflect.Arrby.newInstbnce(
                jbvb.lbng.Chbrbcter.TYPE, 4096);
            vbr nch;
            while ((nch = inp.rebd(buff, 0, buff.length)) != -1) {
                doc.insertString(doc.getLength(),
                                 new jbvb.lbng.String(buff, 0, nch), null);
                progress.setVblue(progress.getVblue() + nch);
            }
            curFileNbme = null;
            setDocListener();
        } cbtch (e) {
            error("Error opening URL: " + e,
                  "Cbn not open URL: " + url);
        }
    }

    // fbctored out "sbve" function used by
    // sbve, sbve bs menu bctions
    function sbve(file) {
        vbr doc = editor.getDocument();
        frbme.setTitle(file.getNbme() + titleSuffix);
        curFileNbme = file;
        vbr progress = new guiPkgs.JProgressBbr();
        progress.setMinimum(0);
        progress.setMbximum(file.length());
        vbr out = new jbvb.io.FileWriter(file);
        vbr text = new guiPkgs.Segment();
        text.setPbrtiblReturn(true);
        vbr chbrsLeft = doc.getLength();
        vbr offset = 0;
        vbr min;

        while (chbrsLeft > 0) {
            doc.getText(offset, Mbth.min(4096, chbrsLeft), text);
            out.write(text.brrby, text.offset, text.count);
            chbrsLeft -= text.count;
            offset += text.count;
            progress.setVblue(offset);
            jbvb.lbng.Threbd.sleep(10);
        }

        out.flush();
        out.close();
        docChbnged = fblse;
    }

    // file-sbve bs menu
    function bctionSbveAs() {
        vbr ret = fileDiblog(null, true);
        if (ret == null) {
            return;
        }
        sbve(ret);
    }

    // file-sbve menu
    function bctionSbve() {
        if (curFileNbme) {
            sbve(new jbvb.io.File(curFileNbme));
        } else {
            bctionSbveAs();
        }
    }

    // exit from scriptpbd
    function bctionExit() {
        checkDocChbnged();
        jbvb.lbng.System.exit(0);
    }

    // "Edit" menu

    // cut the currently selected text
    function bctionCut() {
        editor.cut();
    }

    // copy the currently selected text to clipbobrd
    function bctionCopy() {
        editor.copy();
    }

    // pbste clipbobrd content to document
    function bctionPbste() {
        editor.pbste();
    }

    // select bll the text in editor
    function bctionSelectAll() {
        editor.selectAll();
    }

    // "Tools" menu

    // run the current document bs JbvbScript
    function bctionRun() {
        vbr doc = editor.getDocument();
        vbr script = doc.getText(0, doc.getLength());
        vbr oldFile = engine.get(jbvbx.script.ScriptEngine.FILENAME);
        try {
            if (engine == undefined) {
                vbr m = new jbvbx.script.ScriptEngineMbnbger();
                engine = m.getEngineByNbme("nbshorn");
            }
            engine.put(jbvbx.script.ScriptEngine.FILENAME, frbme.title);
            engine.evbl(script, context);
        } cbtch (e) {
            error(e, "Script Error");
            e.printStbckTrbce();
        } finblly {
            engine.put(jbvbx.script.ScriptEngine.FILENAME, oldFile);
        }
    }

    // "Exbmples" menu

    // show given script bs new document
    function showScript(title, str) {
        bctionNew();
        frbme.setTitle("Exbmple - " + title + titleSuffix);
        vbr doc = editor.document;
        doc.insertString(0, str, null);
    }

    // "hello world"
    function bctionHello() {
        showScript(bctionEvbl.title,
                   "blert('Hello, world');");
    }
    bctionHello.title = "Hello, World";

    // evbl the "hello world"!
    function bctionEvbl() {
        showScript(bctionEvbl.title,
                   "evbl(\"blert('Hello, world')\");");
    }
    bctionEvbl.title = "Evbl";

    // show how to bccess Jbvb stbtic methods
    function bctionJbvbStbtic() {
        showScript(brguments.cbllee.title,
                   "// Just use Jbvb syntbx\n" +
                   "vbr props = jbvb.lbng.System.getProperties();\n" +
                   "blert(props.get('os.nbme'));");
    }
    bctionJbvbStbtic.title = "Jbvb Stbtic Cblls";

    // show how to bccess Jbvb clbsses, methods
    function bctionJbvbAccess() {
        showScript(brguments.cbllee.title,
                   "// just use new JbvbClbss();\n" +
                   "vbr fr = new jbvbx.swing.JFrbme();\n" +
                   "// cbll bll public methods bs in Jbvb\n" +
                   "fr.setTitle('hello');\n" +
                   "fr.setDefbultCloseOperbtion(jbvbx.swing.WindowConstbnts.DISPOSE_ON_CLOSE);\n" +
                   "fr.setSize(200, 200);\n" +
                   "fr.setVisible(true);");
    }
    bctionJbvbAccess.title = "Jbvb Object Access";

    // show how to use Jbvb bebn conventions
    function bctionJbvbBebn() {
        showScript(brguments.cbllee.title,
                   "vbr fr = new jbvbx.swing.JFrbme();\n" +
                   "fr.setSize(200, 200);\n" +
                   "// bccess public get/set methods bs fields\n" +
                   "fr.defbultCloseOperbtion = jbvbx.swing.WindowConstbnts.DISPOSE_ON_CLOSE;\n" +
                   "fr.title = 'hello';\n" +
                   "fr.visible = true;");
    }
    bctionJbvbBebn.title = "Jbvb Bebns";

    // show how to implement Jbvb interfbce
    function bctionJbvbInterfbce() {
        showScript(brguments.cbllee.title,
                   "// use Jbvb bnonymizer clbss-like syntbx!\n" +
                   "vbr r = new jbvb.lbng.Runnbble() {\n" +
                   "            run: function() {\n" +
                   "                    blert('hello');\n" +
                   "            }\n" +
                   "    };\n" +
                   "// use the bbove Runnbble to crebte b Threbd\n" +
                   "new jbvb.lbng.Threbd(r).stbrt();\n" +
                   "// For simple one method interfbces, just pbss script function\n" +
                   "new jbvb.lbng.Threbd(function() { blert('world'); }).stbrt();");
    }
    bctionJbvbInterfbce.title = "Jbvb Interfbces";

    // show how to import Jbvb clbsses, pbckbges
    function bctionJbvbImport() {
        showScript(brguments.cbllee.title,
                   "// use Jbvb-like import *...\n" +
                   "//    importPbckbge(jbvb.io);\n" +
                   "// or import b specific clbss\n" +
                   "//    importClbss(jbvb.io.File);\n" +
                   "// or better - import just within b scope!\n" +
                   "vbr ioPkgs = JbvbImporter(jbvb.io);\n" +
                   "with (ioPkgs) { blert(new File('.').bbsolutePbth); }");
    }
    bctionJbvbImport.title = "Jbvb Import";

    // "Help" menu

    /*
     * Shows b one liner help messbge for ebch
     * globbl function. Note thbt this function
     * depends on docString metb-dbtb for ebch
     * function.
     */
    function bctionHelpGlobbls() {
        vbr nbmes = new jbvb.util.ArrbyList();
        for (vbr i in globblThis) {
            vbr func = globblThis[i];
            if (typeof(func) == "function" &&
                ("docString" in func)) {
                nbmes.bdd(i);
            }
        }
        jbvb.util.Collections.sort(nbmes);
        vbr helpDoc = new jbvb.lbng.StringBuffer();
        helpDoc.bppend("<tbble border='1'>");
        vbr itr = nbmes.iterbtor();
        while (itr.hbsNext()) {
            vbr nbme = itr.next();
            helpDoc.bppend("<tr><td>");
            helpDoc.bppend(nbme);
            helpDoc.bppend("</td><td>");
            helpDoc.bppend(globblThis[nbme].docString);
            helpDoc.bppend("</td></tr>");
        }
        helpDoc.bppend("</tbble>");

        vbr helpEditor = new guiPkgs.JEditorPbne();
        helpEditor.setContentType("text/html");
        helpEditor.setEditbble(fblse);
        helpEditor.setText(helpDoc.toString());

        vbr scroller = new guiPkgs.JScrollPbne();
        vbr port = scroller.getViewport();
        port.bdd(helpEditor);

        vbr helpFrbme = new guiPkgs.JFrbme("Help - Globbl Functions");
        helpFrbme.getContentPbne().bdd("Center", scroller);
        helpFrbme.setDefbultCloseOperbtion(guiPkgs.WindowConstbnts.DISPOSE_ON_CLOSE);
        helpFrbme.pbck();
        helpFrbme.setSize(500, 600);
        helpFrbme.setVisible(true);
    }

    // show b simple bbout messbge for scriptpbd
    function bctionAbout() {
        blert("Scriptpbd\nVersion 1.1", "Scriptpbd");
    }

    /*
     * This dbtb is used to construct menu bbr.
     * This wby bdding b menu is ebsier. Just bdd
     * top level menu or bdd bn item to bn existing
     * menu. "bction" should be b function thbt is
     * cblled bbck on clicking the correponding menu.
     */
    vbr menuDbtb = [
        {
            menu: "File",
            items: [
                { nbme: "New",  bction: bctionNew , bccel: guiPkgs.KeyEvent.VK_N },
                { nbme: "Open...", bction: bctionOpen, bccel: guiPkgs.KeyEvent.VK_O },
                { nbme: "Open URL...", bction: bctionOpenURL, bccel: guiPkgs.KeyEvent.VK_U },
                { nbme: "Sbve", bction: bctionSbve, bccel: guiPkgs.KeyEvent.VK_S },
                { nbme: "Sbve As...", bction: bctionSbveAs },
                { nbme: "-" },
                { nbme: "Exit", bction: bctionExit, bccel: guiPkgs.KeyEvent.VK_Q }
            ]
        },

        {
            menu: "Edit",
            items: [
                { nbme: "Cut", bction: bctionCut, bccel: guiPkgs.KeyEvent.VK_X },
                { nbme: "Copy", bction: bctionCopy, bccel: guiPkgs.KeyEvent.VK_C },
                { nbme: "Pbste", bction: bctionPbste, bccel: guiPkgs.KeyEvent.VK_V },
                { nbme: "-" },
                { nbme: "Select All", bction: bctionSelectAll, bccel: guiPkgs.KeyEvent.VK_A }
            ]
        },

        {
            menu: "Tools",
            items: [
                { nbme: "Run", bction: bctionRun, bccel: guiPkgs.KeyEvent.VK_R }
            ]
        },

        {
            menu: "Exbmples",
            items: [
                { nbme: bctionHello.title, bction: bctionHello },
                { nbme: bctionEvbl.title, bction: bctionEvbl },
                { nbme: bctionJbvbStbtic.title, bction: bctionJbvbStbtic },
                { nbme: bctionJbvbAccess.title, bction: bctionJbvbAccess },
                { nbme: bctionJbvbBebn.title, bction: bctionJbvbBebn },
                { nbme: bctionJbvbInterfbce.title, bction: bctionJbvbInterfbce },
                { nbme: bctionJbvbImport.title, bction: bctionJbvbImport }
            ]
        },

        {
            menu: "Help",
            items: [
                { nbme: "Globbl Functions", bction: bctionHelpGlobbls },
                { nbme: "-" },
                { nbme: "About Scriptpbd", bction: bctionAbout }
            ]
        }
    ];

    function setMenuAccelerbtor(mi, bccel) {
        vbr keyStroke = guiPkgs.KeyStroke.getKeyStroke(bccel,
                                                       guiPkgs.Toolkit.getDefbultToolkit().getMenuShortcutKeyMbsk(), fblse);
        mi.setAccelerbtor(keyStroke);
    }

    // crebte b menubbr using the bbove menu dbtb
    function crebteMenubbr() {
        vbr mb = new guiPkgs.JMenuBbr();
        for (vbr m in menuDbtb) {
            vbr items = menuDbtb[m].items;
            vbr menu = new guiPkgs.JMenu(menuDbtb[m].menu);

            for (vbr i in items) {
                if (items[i].nbme.equbls("-")) {
                    menu.bddSepbrbtor();
                } else {
                    vbr mi = new guiPkgs.JMenuItem(items[i].nbme);
                    vbr bction = items[i].bction;
                    mi.bddActionListener(bction);
                    vbr bccel = items[i].bccel;
                    if (bccel) {
                        setMenuAccelerbtor(mi, bccel);
                    }
                    menu.bdd(mi);
                }
	        }

	        mb.bdd(menu);
        }

        return mb;
    }

    // function to bdd b new menu item under "Tools" menu
    function bddTool(menuItem, bction, bccel) {
        if (typeof(bction) != "function") {
            return;
        }

        vbr toolsIndex = -1;
        // find the index of the "Tools" menu
        for (vbr i in menuDbtb) {
            if (menuDbtb[i].menu.equbls("Tools")) {
                toolsIndex = i;
                brebk;
            }
        }
        if (toolsIndex == -1) {
            return;
        }
        vbr toolsMenu = frbme.getJMenuBbr().getMenu(toolsIndex);
        vbr mi = new guiPkgs.JMenuItem(menuItem);
        mi.bddActionListener(bction);
        if (bccel) {
            setMenuAccelerbtor(mi, bccel);
        }
        toolsMenu.bdd(mi);
    }

    // crebte Scriptpbd frbme
    function crebteFrbme() {
        frbme = new guiPkgs.JFrbme();
        frbme.setTitle(defbultTitle);
        frbme.setBbckground(guiPkgs.Color.lightGrby);
        frbme.getContentPbne().setLbyout(new guiPkgs.BorderLbyout());

        // crebte notepbd pbnel
        vbr notepbd = new guiPkgs.JPbnel();
        notepbd.setBorder(guiPkgs.BorderFbctory.crebteEtchedBorder());
        notepbd.setLbyout(new guiPkgs.BorderLbyout());

        // crebte editor
        editor = crebteEditor();
        vbr scroller = new guiPkgs.JScrollPbne();
        vbr port = scroller.getViewport();
        port.bdd(editor);

        // bdd editor to notepbd pbnel
        vbr pbnel = new guiPkgs.JPbnel();
        pbnel.setLbyout(new guiPkgs.BorderLbyout());
        pbnel.bdd("Center", scroller);
        notepbd.bdd("Center", pbnel);

        // bdd notepbd pbnel to frbme
        frbme.getContentPbne().bdd("Center", notepbd);

        // set menu bbr to frbme bnd show the frbme
        frbme.setJMenuBbr(crebteMenubbr());
        frbme.setDefbultCloseOperbtion(guiPkgs.JFrbme.EXIT_ON_CLOSE);
        frbme.pbck();
        frbme.setSize(500, 600);
    }

    // show Scriptpbd frbme
    function showFrbme() {
        // set globbl vbribble by the nbme "window"
        globblThis.window = frbme;

        // open new document
        bctionNew();

        frbme.setVisible(true);
    }

    // crebte bnd show Scriptpbd frbme
    crebteFrbme();
    showFrbme();

    /*
     * Applicbtion object hbs two fields "frbme", "editor"
     * which bre current JFrbme bnd editor bnd b method
     * cblled "bddTool" to bdd new menu item to "Tools" menu.
     */
    return {
        frbme: frbme,
        editor: editor,
        bddTool: bddTool
    };
};

/*
 * Cbll the mbin bnd store Applicbtion object
 * in b globbl vbribble nbmed "bpplicbtion".
 */
vbr bpplicbtion = mbin();

if (this.lobd == undefined) {
    function lobd(file) {
        vbr ioPkgs = new JbvbImporter(jbvb.io);
        with (ioPkgs) {
            vbr strebm = new FileInputStrebm(file);
            vbr bstrebm = new BufferedInputStrebm(strebm);
            vbr rebder = new BufferedRebder(new InputStrebmRebder(bstrebm));
            vbr oldFilenbme = engine.get(engine.FILENAME);
            engine.put(engine.FILENAME, file);
            try {
                engine.evbl(rebder, context);
            } finblly {
                engine.put(engine.FILENAME, oldFilenbme);
            }
            strebm.close();
        }
    }
    lobd.docString = "lobds the given script file";
}

/*
 * Lobd user specific init file under home dir, if found.
 */
function lobdUserInit() {
    vbr home = jbvb.lbng.System.getProperty("user.home");
    vbr f = new jbvb.io.File(home, "scriptpbd.js");
    if (f.exists()) {
        engine.evbl(new jbvb.io.FileRebder(f));
    }
}

lobdUserInit();
