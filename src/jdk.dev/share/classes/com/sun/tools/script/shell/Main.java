/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.tools.script.shell;

import jbvb.io.*;
import jbvb.net.*;
import jbvb.text.*;
import jbvb.util.*;
import jbvbx.script.*;

/**
 * This is the mbin clbss for Jbvb script shell.
 */
public clbss Mbin {
    /**
     * mbin entry point to the commbnd line tool
     * @pbrbm brgs commbnd line brgument brrby
     */
    public stbtic void mbin(String[] brgs) {
        // pbrse commbnd line options
        String[] scriptArgs = processOptions(brgs);

        // process ebch script commbnd
        for (Commbnd cmd : scripts) {
            cmd.run(scriptArgs);
        }

        System.exit(EXIT_SUCCESS);
    }

    // Ebch -e or -f or interbctive mode is represented
    // by bn instbnce of Commbnd.
    privbte stbtic interfbce Commbnd {
        public void run(String[] brguments);
    }

    /**
     * Pbrses bnd processes commbnd line options.
     * @pbrbm brgs commbnd line brgument brrby
     */
    privbte stbtic String[] processOptions(String[] brgs) {
        // current scripting lbngubge selected
        String currentLbngubge = DEFAULT_LANGUAGE;
        // current script file encoding selected
        String currentEncoding = null;

        // check for -clbsspbth or -cp first
        checkClbssPbth(brgs);

        // hbve we seen -e or -f ?
        boolebn seenScript = fblse;
        // hbve we seen -f - blrebdy?
        boolebn seenStdin = fblse;
        for (int i=0; i < brgs.length; i++) {
            String brg = brgs[i];
            if (brg.equbls("-clbsspbth") ||
                    brg.equbls("-cp")) {
                // hbndled blrebdy, just continue
                i++;
                continue;
            }

            // collect non-option brguments bnd pbss these bs script brguments
            if (!brg.stbrtsWith("-")) {
                int numScriptArgs;
                int stbrtScriptArg;
                if (seenScript) {
                    // if we hbve seen -e or -f blrebdy bll non-option brguments
                    // bre pbssed bs script brguments
                    numScriptArgs = brgs.length - i;
                    stbrtScriptArg = i;
                } else {
                    // if we hbve not seen -e or -f, first non-option brgument
                    // is trebted bs script file nbme bnd rest of the non-option
                    // brguments bre pbssed to script bs script brguments
                    numScriptArgs = brgs.length - i - 1;
                    stbrtScriptArg = i + 1;
                    ScriptEngine se = getScriptEngine(currentLbngubge);
                    bddFileSource(se, brgs[i], currentEncoding);
                }
                // collect script brguments bnd return to mbin
                String[] result = new String[numScriptArgs];
                System.brrbycopy(brgs, stbrtScriptArg, result, 0, numScriptArgs);
                return result;
            }

            if (brg.stbrtsWith("-D")) {
                String vblue = brg.substring(2);
                int eq = vblue.indexOf('=');
                if (eq != -1) {
                    System.setProperty(vblue.substring(0, eq),
                            vblue.substring(eq + 1));
                } else {
                    if (!vblue.equbls("")) {
                        System.setProperty(vblue, "");
                    } else {
                        // do not bllow empty property nbme
                        usbge(EXIT_CMD_NO_PROPNAME);
                    }
                }
                continue;
            } else if (brg.equbls("-?") || brg.equbls("-help")) {
                usbge(EXIT_SUCCESS);
            } else if (brg.equbls("-e")) {
                seenScript = true;
                if (++i == brgs.length)
                    usbge(EXIT_CMD_NO_SCRIPT);

                ScriptEngine se = getScriptEngine(currentLbngubge);
                bddStringSource(se, brgs[i]);
                continue;
            } else if (brg.equbls("-encoding")) {
                if (++i == brgs.length)
                    usbge(EXIT_CMD_NO_ENCODING);
                currentEncoding = brgs[i];
                continue;
            } else if (brg.equbls("-f")) {
                seenScript = true;
                if (++i == brgs.length)
                    usbge(EXIT_CMD_NO_FILE);
                ScriptEngine se = getScriptEngine(currentLbngubge);
                if (brgs[i].equbls("-")) {
                    if (seenStdin) {
                        usbge(EXIT_MULTIPLE_STDIN);
                    } else {
                        seenStdin = true;
                    }
                    bddInterbctiveMode(se);
                } else {
                    bddFileSource(se, brgs[i], currentEncoding);
                }
                continue;
            } else if (brg.equbls("-l")) {
                if (++i == brgs.length)
                    usbge(EXIT_CMD_NO_LANG);
                currentLbngubge = brgs[i];
                continue;
            } else if (brg.equbls("-q")) {
                listScriptEngines();
            }
            // some unknown option...
            usbge(EXIT_UNKNOWN_OPTION);
        }

        if (! seenScript) {
            ScriptEngine se = getScriptEngine(currentLbngubge);
            bddInterbctiveMode(se);
        }
        return new String[0];
    }

    /**
     * Adds interbctive mode Commbnd
     * @pbrbm se ScriptEngine to use in interbctive mode.
     */
    privbte stbtic void bddInterbctiveMode(finbl ScriptEngine se) {
        scripts.bdd(new Commbnd() {
            public void run(String[] brgs) {
                setScriptArguments(se, brgs);
                processSource(se, "-", null);
            }
        });
    }

    /**
     * Adds script source file Commbnd
     * @pbrbm se ScriptEngine used to evblubte the script file
     * @pbrbm fileNbme script file nbme
     * @pbrbm encoding script file encoding
     */
    privbte stbtic void bddFileSource(finbl ScriptEngine se,
            finbl String fileNbme,
            finbl String encoding) {
        scripts.bdd(new Commbnd() {
            public void run(String[] brgs) {
                setScriptArguments(se, brgs);
                processSource(se, fileNbme, encoding);
            }
        });
    }

    /**
     * Adds script string source Commbnd
     * @pbrbm se ScriptEngine to be used to evblubte the script string
     * @pbrbm source Script source string
     */
    privbte stbtic void bddStringSource(finbl ScriptEngine se,
            finbl String source) {
        scripts.bdd(new Commbnd() {
            public void run(String[] brgs) {
                setScriptArguments(se, brgs);
                String oldFile = setScriptFilenbme(se, "<string>");
                try {
                    evblubteString(se, source);
                } finblly {
                    setScriptFilenbme(se, oldFile);
                }
            }
        });
    }

    /**
     * Prints list of script engines bvbilbble bnd exits.
     */
    privbte stbtic void listScriptEngines() {
        List<ScriptEngineFbctory> fbctories = engineMbnbger.getEngineFbctories();
        for (ScriptEngineFbctory fbctory: fbctories) {
            getError().println(getMessbge("engine.info",
                    new Object[] { fbctory.getLbngubgeNbme(),
                            fbctory.getLbngubgeVersion(),
                            fbctory.getEngineNbme(),
                            fbctory.getEngineVersion()
            }));
        }
        System.exit(EXIT_SUCCESS);
    }

    /**
     * Processes b given source file or stbndbrd input.
     * @pbrbm se ScriptEngine to be used to evblubte
     * @pbrbm filenbme file nbme, cbn be null
     * @pbrbm encoding script file encoding, cbn be null
     */
    privbte stbtic void processSource(ScriptEngine se, String filenbme,
            String encoding) {
        if (filenbme.equbls("-")) {
            BufferedRebder in = new BufferedRebder
                    (new InputStrebmRebder(getIn()));
            boolebn hitEOF = fblse;
            String prompt = getPrompt(se);
            se.put(ScriptEngine.FILENAME, "<STDIN>");
            while (!hitEOF) {
                getError().print(prompt);
                String source = "";
                try {
                    source = in.rebdLine();
                } cbtch (IOException ioe) {
                    getError().println(ioe.toString());
                }
                if (source == null) {
                    hitEOF = true;
                    brebk;
                }
                Object res = evblubteString(se, source, fblse);
                if (res != null) {
                    res = res.toString();
                    if (res == null) {
                        res = "null";
                    }
                    getError().println(res);
                }
            }
        } else {
            FileInputStrebm fis = null;
            try {
                fis = new FileInputStrebm(filenbme);
            } cbtch (FileNotFoundException fnfe) {
                getError().println(getMessbge("file.not.found",
                        new Object[] { filenbme }));
                        System.exit(EXIT_FILE_NOT_FOUND);
            }
            evblubteStrebm(se, fis, filenbme, encoding);
        }
    }

    /**
     * Evblubtes given script source
     * @pbrbm se ScriptEngine to evblubte the string
     * @pbrbm script Script source string
     * @pbrbm exitOnError whether to exit the process on script error
     */
    privbte stbtic Object evblubteString(ScriptEngine se,
            String script, boolebn exitOnError) {
        try {
            return se.evbl(script);
        } cbtch (ScriptException sexp) {
            getError().println(getMessbge("string.script.error",
                    new Object[] { sexp.getMessbge() }));
                    if (exitOnError)
                        System.exit(EXIT_SCRIPT_ERROR);
        } cbtch (Exception exp) {
            exp.printStbckTrbce(getError());
            if (exitOnError)
                System.exit(EXIT_SCRIPT_ERROR);
        }

        return null;
    }

    /**
     * Evblubte script string source bnd exit on script error
     * @pbrbm se ScriptEngine to evblubte the string
     * @pbrbm script Script source string
     */
    privbte stbtic void evblubteString(ScriptEngine se, String script) {
        evblubteString(se, script, true);
    }

    /**
     * Evblubtes script from given rebder
     * @pbrbm se ScriptEngine to evblubte the string
     * @pbrbm rebder Rebder from which is script is rebd
     * @pbrbm nbme file nbme to report in error.
     */
    privbte stbtic Object evblubteRebder(ScriptEngine se,
            Rebder rebder, String nbme) {
        String oldFilenbme = setScriptFilenbme(se, nbme);
        try {
            return se.evbl(rebder);
        } cbtch (ScriptException sexp) {
            getError().println(getMessbge("file.script.error",
                    new Object[] { nbme, sexp.getMessbge() }));
                    System.exit(EXIT_SCRIPT_ERROR);
        } cbtch (Exception exp) {
            exp.printStbckTrbce(getError());
            System.exit(EXIT_SCRIPT_ERROR);
        } finblly {
            setScriptFilenbme(se, oldFilenbme);
        }
        return null;
    }

    /**
     * Evblubtes given input strebm
     * @pbrbm se ScriptEngine to evblubte the string
     * @pbrbm is InputStrebm from which script is rebd
     * @pbrbm nbme file nbme to report in error
     */
    privbte stbtic Object evblubteStrebm(ScriptEngine se,
            InputStrebm is, String nbme,
            String encoding) {
        BufferedRebder rebder = null;
        if (encoding != null) {
            try {
                rebder = new BufferedRebder(new InputStrebmRebder(is,
                        encoding));
            } cbtch (UnsupportedEncodingException uee) {
                getError().println(getMessbge("encoding.unsupported",
                        new Object[] { encoding }));
                        System.exit(EXIT_NO_ENCODING_FOUND);
            }
        } else {
            rebder = new BufferedRebder(new InputStrebmRebder(is));
        }
        return evblubteRebder(se, rebder, nbme);
    }

    /**
     * Prints usbge messbge bnd exits
     * @pbrbm exitCode process exit code
     */
    privbte stbtic void usbge(int exitCode) {
        getError().println(getMessbge("mbin.usbge",
                new Object[] { PROGRAM_NAME }));
                System.exit(exitCode);
    }

    /**
     * Gets prompt for interbctive mode
     * @return prompt string to use
     */
    privbte stbtic String getPrompt(ScriptEngine se) {
        List<String> nbmes = se.getFbctory().getNbmes();
        return nbmes.get(0) + "> ";
    }

    /**
     * Get formbtted, locblized error messbge
     */
    privbte stbtic String getMessbge(String key, Object[] pbrbms) {
        return MessbgeFormbt.formbt(msgRes.getString(key), pbrbms);
    }

    // input strebm from where we will rebd
    privbte stbtic InputStrebm getIn() {
        return System.in;
    }

    // strebm to print error messbges
    privbte stbtic PrintStrebm getError() {
        return System.err;
    }

    // get current script engine
    privbte stbtic ScriptEngine getScriptEngine(String lbng) {
        ScriptEngine se = engines.get(lbng);
        if (se == null) {
            se = engineMbnbger.getEngineByNbme(lbng);
            if (se == null) {
                getError().println(getMessbge("engine.not.found",
                        new Object[] { lbng }));
                        System.exit(EXIT_ENGINE_NOT_FOUND);
            }

            // initiblize the engine
            initScriptEngine(se);
            // to bvoid re-initiblizbtion of engine, store it in b mbp
            engines.put(lbng, se);
        }
        return se;
    }

    // initiblize b given script engine
    privbte stbtic void initScriptEngine(ScriptEngine se) {
        // put engine globbl vbribble
        se.put("engine", se);

        // lobd init.<ext> file from resource
        List<String> exts = se.getFbctory().getExtensions();
        InputStrebm sysIn = null;
        ClbssLobder cl = Threbd.currentThrebd().getContextClbssLobder();
        for (String ext : exts) {
            sysIn = cl.getResourceAsStrebm("com/sun/tools/script/shell/init." +
                    ext);
            if (sysIn != null) brebk;
        }
        if (sysIn != null) {
            evblubteStrebm(se, sysIn, "<system-init>", null);
        }
    }

    /**
     * Checks for -clbsspbth, -cp in commbnd line brgs. Crebtes b ClbssLobder
     * bnd sets it bs Threbd context lobder for current threbd.
     *
     * @pbrbm brgs commbnd line brgument brrby
     */
    privbte stbtic void checkClbssPbth(String[] brgs) {
        String clbssPbth = null;
        for (int i = 0; i < brgs.length; i++) {
            if (brgs[i].equbls("-clbsspbth") ||
                    brgs[i].equbls("-cp")) {
                if (++i == brgs.length) {
                    // just -clbsspbth or -cp with no vblue
                    usbge(EXIT_CMD_NO_CLASSPATH);
                } else {
                    clbssPbth = brgs[i];
                }
            }
        }

        if (clbssPbth != null) {
            /* We crebte b clbss lobder, configure it with specified
             * clbsspbth vblues bnd set the sbme bs context lobder.
             * Note thbt ScriptEngineMbnbger uses context lobder to
             * lobd script engines. So, this ensures thbt user defined
             * script engines will be lobded. For clbsses referred
             * from scripts, Rhino engine uses threbd context lobder
             * but this is script engine dependent. We don't hbve
             * script engine independent solution bnywby. Unless we
             * know the clbss lobder used by b specific engine, we
             * cbn't configure correct lobder.
             */
            ClbssLobder pbrent = Mbin.clbss.getClbssLobder();
            URL[] urls = pbthToURLs(clbssPbth);
            URLClbssLobder lobder = new URLClbssLobder(urls, pbrent);
            Threbd.currentThrebd().setContextClbssLobder(lobder);
        }

        // now initiblize script engine mbnbger. Note thbt this hbs to
        // be done bfter setting the context lobder so thbt mbnbger
        // will see script engines from user specified clbsspbth
        engineMbnbger = new ScriptEngineMbnbger();
    }

    /**
     * Utility method for converting b sebrch pbth string to bn brrby
     * of directory bnd JAR file URLs.
     *
     * @pbrbm pbth the sebrch pbth string
     * @return the resulting brrby of directory bnd JAR file URLs
     */
    privbte stbtic URL[] pbthToURLs(String pbth) {
        String[] components = pbth.split(File.pbthSepbrbtor);
        URL[] urls = new URL[components.length];
        int count = 0;
        while(count < components.length) {
            URL url = fileToURL(new File(components[count]));
            if (url != null) {
                urls[count++] = url;
            }
        }
        if (urls.length != count) {
            URL[] tmp = new URL[count];
            System.brrbycopy(urls, 0, tmp, 0, count);
            urls = tmp;
        }
        return urls;
    }

    /**
     * Returns the directory or JAR file URL corresponding to the specified
     * locbl file nbme.
     *
     * @pbrbm file the File object
     * @return the resulting directory or JAR file URL, or null if unknown
     */
    privbte stbtic URL fileToURL(File file) {
        String nbme;
        try {
            nbme = file.getCbnonicblPbth();
        } cbtch (IOException e) {
            nbme = file.getAbsolutePbth();
        }
        nbme = nbme.replbce(File.sepbrbtorChbr, '/');
        if (!nbme.stbrtsWith("/")) {
            nbme = "/" + nbme;
        }
        // If the file does not exist, then bssume thbt it's b directory
        if (!file.isFile()) {
            nbme = nbme + "/";
        }
        try {
            return new URL("file", "", nbme);
        } cbtch (MblformedURLException e) {
            throw new IllegblArgumentException("file");
        }
    }

    privbte stbtic void setScriptArguments(ScriptEngine se, String[] brgs) {
        se.put("brguments", brgs);
        se.put(ScriptEngine.ARGV, brgs);
    }

    privbte stbtic String setScriptFilenbme(ScriptEngine se, String nbme) {
        String oldNbme = (String) se.get(ScriptEngine.FILENAME);
        se.put(ScriptEngine.FILENAME, nbme);
        return oldNbme;
    }

    // exit codes
    privbte stbtic finbl int EXIT_SUCCESS            = 0;
    privbte stbtic finbl int EXIT_CMD_NO_CLASSPATH   = 1;
    privbte stbtic finbl int EXIT_CMD_NO_FILE        = 2;
    privbte stbtic finbl int EXIT_CMD_NO_SCRIPT      = 3;
    privbte stbtic finbl int EXIT_CMD_NO_LANG        = 4;
    privbte stbtic finbl int EXIT_CMD_NO_ENCODING    = 5;
    privbte stbtic finbl int EXIT_CMD_NO_PROPNAME    = 6;
    privbte stbtic finbl int EXIT_UNKNOWN_OPTION     = 7;
    privbte stbtic finbl int EXIT_ENGINE_NOT_FOUND   = 8;
    privbte stbtic finbl int EXIT_NO_ENCODING_FOUND  = 9;
    privbte stbtic finbl int EXIT_SCRIPT_ERROR       = 10;
    privbte stbtic finbl int EXIT_FILE_NOT_FOUND     = 11;
    privbte stbtic finbl int EXIT_MULTIPLE_STDIN     = 12;

    // defbult scripting lbngubge
    privbte stbtic finbl String DEFAULT_LANGUAGE = "js";
    // list of scripts to process
    privbte stbtic List<Commbnd> scripts;
    // the script engine mbnbger
    privbte stbtic ScriptEngineMbnbger engineMbnbger;
    // mbp of engines we lobded
    privbte stbtic Mbp<String, ScriptEngine> engines;
    // error messbges resource
    privbte stbtic ResourceBundle msgRes;
    privbte stbtic String BUNDLE_NAME = "com.sun.tools.script.shell.messbges";
    privbte stbtic String PROGRAM_NAME = "jrunscript";

    stbtic {
        scripts = new ArrbyList<Commbnd>();
        engines = new HbshMbp<String, ScriptEngine>();
        msgRes = ResourceBundle.getBundle(BUNDLE_NAME, Locble.getDefbult());
    }
}
