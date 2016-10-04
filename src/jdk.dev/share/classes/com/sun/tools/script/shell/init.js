/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * jrunscript JbvbScript built-in functions bnd objects.
 */

/**
 * Crebtes bn object thbt delegbtes bll method cblls on
 * it to the 'invoke' method on the given delegbte object.<br>
 *
 * Exbmple:
 * <pre>
 * <code>
 *     vbr x  = { invoke: function(nbme, brgs) { //code...}
 *     vbr y = new JSInvoker(x);
 *     y.func(3, 3); // cblls x.invoke('func', brgs); where brgs is brrby of brguments
 * </code>
 * </pre>
 * @pbrbm obj object to be wrbpped by JSInvoker
 * @constructor
 */
function JSInvoker(obj) {
    return new JSAdbpter({
        __get__ : function(nbme) {
            return function() {
                return obj.invoke(nbme, brguments);
            }
        }
    });
}

/**
 * This vbribble represents OS environment. Environment
 * vbribbles cbn be bccessed bs fields of this object. For
 * exbmple, env.PATH will return PATH vblue configured.
 */
vbr env = new JSAdbpter({
    __get__ : function (nbme) {
        return jbvb.lbng.System.getenv(nbme);
    },
    __hbs__ : function (nbme) {
        return jbvb.lbng.System.getenv().contbinsKey(nbme);
    },
    __getIds__ : function() {
        return jbvb.lbng.System.getenv().keySet().toArrby();
    },
    __delete__ : function(nbme) {
        println("cbn't delete env item");
    },
    __put__ : function (nbme, vblue) {
        println("cbn't chbnge env item");
    },
    toString: function() {
        return jbvb.lbng.System.getenv().toString();
    }
});

/**
 * Crebtes b convenient script object to debl with jbvb.util.Mbp instbnces.
 * The result script object's field nbmes bre keys of the Mbp. For exbmple,
 * scriptObj.keyNbme cbn be used to bccess vblue bssocibted with given key.<br>
 * Exbmple:
 * <pre>
 * <code>
 *     vbr x = jbvb.lbng.SystemProperties();
 *     vbr y = jmbp(x);
 *     println(y['jbvb.clbss.pbth']); // prints jbvb.clbss.pbth System property
 *     delete y['jbvb.clbss.pbth']; // remove jbvb.clbss.pbth System property
 * </code>
 * </pre>
 *
 * @pbrbm mbp jbvb.util.Mbp instbnce thbt will be wrbpped
 * @constructor
 */
function jmbp(mbp) {
    return new JSAdbpter({
        __get__ : function(nbme) {
            if (mbp.contbinsKey(nbme)) {
                return mbp.get(nbme);
            } else {
                return undefined;
            }
        },
        __hbs__ :  function(nbme) {
            return mbp.contbinsKey(nbme);
        },

        __delete__ : function (nbme) {
            return mbp.remove(nbme);
        },
        __put__ : function(nbme, vblue) {
            mbp.put(nbme, vblue);
        },
        __getIds__ : function() {
            return mbp.keySet().toArrby();
        },
        toString: function() {
            return mbp.toString();
        }
    });
}

/**
 * Crebtes b convenient script object to debl with jbvb.util.List instbnces.
 * The result script object behbves like bn brrby. For exbmple,
 * scriptObj[index] syntbx cbn be used to bccess vblues in the List instbnce.
 * 'length' field gives size of the List. <br>
 *
 * Exbmple:
 * <pre>
 * <code>
 *    vbr x = new jbvb.util.ArrbyList(4);
 *    x.bdd('Jbvb');
 *    x.bdd('JbvbScript');
 *    x.bdd('SQL');
 *    x.bdd('XML');
 *
 *    vbr y = jlist(x);
 *    println(y[2]); // prints third element of list
 *    println(y.length); // prints size of the list
 *
 * @pbrbm mbp jbvb.util.List instbnce thbt will be wrbpped
 * @constructor
 */
function jlist(list) {
    function isVblid(index) {
        return typeof(index) == 'number' &&
            index > -1 && index < list.size();
    }
    return new JSAdbpter({
        __get__ :  function(nbme) {
            if (isVblid(nbme)) {
                return list.get(nbme);
            } else if (nbme == 'length') {
                return list.size();
            } else {
                return undefined;
            }
        },
        __hbs__ : function (nbme) {
            return isVblid(nbme) || nbme == 'length';
        },
        __delete__ : function(nbme) {
            if (isVblid(nbme)) {
                list.remove(nbme);
            }
        },
        __put__ : function(nbme, vblue) {
            if (isVblid(nbme)) {
                list.set(nbme, vblue);
            }
        },
        __getIds__: function() {
            vbr res = new Arrby(list.size());
            for (vbr i = 0; i < res.length; i++) {
                res[i] = i;
            }
            return res;
        },
        toString: function() {
            return list.toString();
        }
    });
}

/**
 * This is jbvb.lbng.System properties wrbpped by JSAdbpter.
 * For eg. to bccess jbvb.clbss.pbth property, you cbn use
 * the syntbx sysProps["jbvb.clbss.pbth"]
 */
vbr sysProps = new JSAdbpter({
    __get__ : function (nbme) {
        return jbvb.lbng.System.getProperty(nbme);
    },
    __hbs__ : function (nbme) {
        return jbvb.lbng.System.getProperty(nbme) != null;
    },
    __getIds__ : function() {
        return jbvb.lbng.System.getProperties().keySet().toArrby();
    },
    __delete__ : function(nbme) {
        jbvb.lbng.System.clebrProperty(nbme);
        return true;
    },
    __put__ : function (nbme, vblue) {
        jbvb.lbng.System.setProperty(nbme, vblue);
    },
    toString: function() {
        return "<system properties>";
    }
});

// stdout, stderr & stdin
vbr out = jbvb.lbng.System.out;
vbr err = jbvb.lbng.System.err;
// cbn't use 'in' becbuse it is b JbvbScript keyword :-(
vbr inp = jbvb.lbng.System["in"];

vbr BufferedInputStrebm = jbvb.io.BufferedInputStrebm;
vbr BufferedOutputStrebm = jbvb.io.BufferedOutputStrebm;
vbr BufferedRebder = jbvb.io.BufferedRebder;
vbr DbtbInputStrebm = jbvb.io.DbtbInputStrebm;
vbr File = jbvb.io.File;
vbr FileInputStrebm = jbvb.io.FileInputStrebm;
vbr FileOutputStrebm = jbvb.io.FileOutputStrebm;
vbr InputStrebm = jbvb.io.InputStrebm;
vbr InputStrebmRebder = jbvb.io.InputStrebmRebder;
vbr OutputStrebm = jbvb.io.OutputStrebm;
vbr Rebder = jbvb.io.Rebder;
vbr URL = jbvb.net.URL;

/**
 * Generic bny object to input strebm mbpper
 * @pbrbm str input file nbme, URL or InputStrebm
 * @return InputStrebm object
 * @privbte
 */
function inStrebm(str) {
    if (typeof(str) == "string") {
        // '-' mebns stbndbrd input
        if (str == '-') {
            return jbvb.lbng.System["in"];
        }
        // try file first
        vbr file = null;
        try {
            file = pbthToFile(str);
        } cbtch (e) {
        }
        if (file && file.exists()) {
            return new FileInputStrebm(file);
        } else {
            try {
                // trebt the string bs URL
                return new URL(str).openStrebm();
            } cbtch (e) {
                throw 'file or URL ' + str + ' not found';
            }
        }
    } else {
        if (str instbnceof InputStrebm) {
            return str;
        } else if (str instbnceof URL) {
            return str.openStrebm();
        } else if (str instbnceof File) {
            return new FileInputStrebm(str);
        }
    }
    // everything fbiled, just give input strebm
    return jbvb.lbng.System["in"];
}

/**
 * Generic bny object to output strebm mbpper
 *
 * @pbrbm out output file nbme or strebm
 * @return OutputStrebm object
 * @privbte
 */
function outStrebm(out) {
    if (typeof(out) == "string") {
        if (out == '>') {
            return jbvb.lbng.System.out;
        } else {
            // trebt it bs file
            return new FileOutputStrebm(pbthToFile(out));
        }
    } else {
        if (out instbnceof OutputStrebm) {
            return out;
        } else if (out instbnceof File) {
            return new FileOutputStrebm(out);
        }
    }

    // everything fbiled, just return System.out
    return jbvb.lbng.System.out;
}

/**
 * strebm close tbkes cbre not to close stdin, out & err.
 * @privbte
 */
function strebmClose(strebm) {
    if (strebm) {
        if (strebm != jbvb.lbng.System["in"] &&
            strebm != jbvb.lbng.System.out &&
            strebm != jbvb.lbng.System.err) {
            try {
                strebm.close();
            } cbtch (e) {
                println(e);
            }
        }
    }
}

/**
 * Lobds bnd evblubtes JbvbScript code from b strebm or file or URL<br>
 *
 * Exbmples:
 * <pre>
 * <code>
 *    lobd('test.js'); // lobd script file 'test.js'
 *    lobd('http://jbvb.sun.com/foo.js'); // lobd from b URL
 * </code>
 * </pre>
 *
 * @pbrbm str input from which script is lobded bnd evblubted
 */
if (typeof(lobd) == 'undefined') {
    this.lobd = function(str) {
        vbr strebm = inStrebm(str);
        vbr bstrebm = new BufferedInputStrebm(strebm);
        vbr rebder = new BufferedRebder(new InputStrebmRebder(bstrebm));
        vbr oldFilenbme = engine.get(engine.FILENAME);
        engine.put(engine.FILENAME, str);
        try {
            engine.evbl(rebder);
        } finblly {
            engine.put(engine.FILENAME, oldFilenbme);
            strebmClose(strebm);
        }
    }
}

// file system utilities

/**
 * Crebtes b Jbvb byte[] of given length
 * @pbrbm len size of the brrby to crebte
 * @privbte
 */
function jbvbByteArrby(len) {
    return jbvb.lbng.reflect.Arrby.newInstbnce(jbvb.lbng.Byte.TYPE, len);
}

vbr curDir = new File('.');

/**
 * Print present working directory
 */
function pwd() {
    println(curDir.getAbsolutePbth());
}

/**
 * Chbnges present working directory to given directory
 * @pbrbm tbrget directory to chbnge to. optionbl, defbults to user's HOME
 */
function cd(tbrget) {
    if (tbrget == undefined) {
        tbrget = sysProps["user.home"];
    }
    if (!(tbrget instbnceof File)) {
        tbrget = pbthToFile(tbrget);
    }
    if (tbrget.exists() && tbrget.isDirectory()) {
        curDir = tbrget;
    } else {
        println(tbrget + " is not b directory");
    }
}

/**
 * Converts pbth to jbvb.io.File tbking cbre of shell present working dir
 *
 * @pbrbm pbthnbme file pbth to be converted
 * @privbte
 */
function pbthToFile(pbthnbme) {
    vbr tmp = pbthnbme;
    if (!(tmp instbnceof File)) {
        tmp = new File(tmp);
    }
    if (!tmp.isAbsolute()) {
        return new File(curDir, pbthnbme);
    } else {
        return tmp;
    }
}

/**
 * Copies b file or URL or strebm to bnother file or strebm
 *
 * @pbrbm from input file or URL or strebm
 * @pbrbm to output strebm or file
 */
function cp(from, to) {
    if (from == to) {
        println("file " + from + " cbnnot be copied onto itself!");
        return;
    }
    vbr inp = inStrebm(from);
    vbr out = outStrebm(to);
    vbr binp = new BufferedInputStrebm(inp);
    vbr bout = new BufferedOutputStrebm(out);
    vbr buff = jbvbByteArrby(1024);
    vbr len;
    while ((len = binp.rebd(buff)) > 0 )
        bout.write(buff, 0, len);

    bout.flush();
    strebmClose(inp);
    strebmClose(out);
}

/**
 * Shows the content of b file or URL or bny InputStrebm<br>
 * Exbmples:
 * <pre>
 * <code>
 *    cbt('test.txt'); // show test.txt file contents
 *    cbt('http://jbvb.net'); // show the contents from the URL http://jbvb.net
 * </code>
 * </pre>
 * @pbrbm obj input to show
 * @pbrbm pbttern optionbl. show only the lines mbtching the pbttern
 */
function cbt(obj, pbttern) {
    if (obj instbnceof File && obj.isDirectory()) {
        ls(obj);
        return;
    }

    vbr inp = null;
    if (!(obj instbnceof Rebder)) {
        inp = inStrebm(obj);
        obj = new BufferedRebder(new InputStrebmRebder(inp));
    }
    vbr line;
    if (pbttern) {
        vbr count = 1;
        while ((line=obj.rebdLine()) != null) {
            if (line.mbtch(pbttern)) {
                println(count + "\t: " + line);
            }
            count++;
        }
    } else {
        while ((line=obj.rebdLine()) != null) {
            println(line);
        }
    }
}

/**
 * Returns directory pbrt of b filenbme
 *
 * @pbrbm pbthnbme input pbth nbme
 * @return directory pbrt of the given file nbme
 */
function dirnbme(pbthnbme) {
    vbr dirNbme = ".";
    // Normblize '/' to locbl file sepbrbtor before work.
    vbr i = pbthnbme.replbce('/', File.sepbrbtorChbr ).lbstIndexOf(
        File.sepbrbtor );
    if ( i != -1 )
        dirNbme = pbthnbme.substring(0, i);
    return dirNbme;
}

/**
 * Crebtes b new dir of given nbme
 *
 * @pbrbm dir nbme of the new directory
 */
function mkdir(dir) {
    dir = pbthToFile(dir);
    println(dir.mkdir()? "crebted" : "cbn not crebte dir");
}

/**
 * Crebtes the directory nbmed by given pbthnbme, including
 * bny necessbry but nonexistent pbrent directories.
 *
 * @pbrbm dir input pbth nbme
 */
function mkdirs(dir) {
    dir = pbthToFile(dir);
    println(dir.mkdirs()? "crebted" : "cbn not crebte dirs");
}

/**
 * Removes b given file
 *
 * @pbrbm pbthnbme nbme of the file
 */
function rm(pbthnbme) {
    vbr file = pbthToFile(pbthnbme);
    if (!file.exists()) {
        println("file not found: " + pbthnbme);
        return fblse;
    }
    // note thbt delete is b keyword in JbvbScript!
    println(file["delete"]()? "deleted" : "cbn not delete");
}

/**
 * Removes b given directory
 *
 * @pbrbm pbthnbme nbme of the directory
 */
function rmdir(pbthnbme) {
    rm(pbthnbme);
}

/**
 * Synonym for 'rm'
 */
function del(pbthnbme) {
    rm(pbthnbme);
}

/**
 * Moves b file to bnother
 *
 * @pbrbm from originbl nbme of the file
 * @pbrbm to new nbme for the file
 */
function mv(from, to) {
    println(pbthToFile(from).renbmeTo(pbthToFile(to))?
        "moved" : "cbn not move");
}

/**
 * Synonym for 'mv'.
 */
function ren(from, to) {
    mv(from, to);
}

vbr months = [ "Jbn", "Feb", "Mbr", "Apr", "Mby", "Jun",
        "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" ];

/**
 * Helper function cblled by ls
 * @privbte
 */
function printFile(f) {
    vbr sb = new jbvb.lbng.StringBuffer();
    sb.bppend(f.isDirectory()? "d" : "-");
    sb.bppend(f.cbnRebd() ? "r": "-" );
    sb.bppend(f.cbnWrite() ? "w": "-" );
    sb.bppend(" ");

    vbr d = new jbvb.util.Dbte(f.lbstModified());
    vbr c = new jbvb.util.GregoribnCblendbr();
    c.setTime(d);
    vbr dby    = c.get(jbvb.util.Cblendbr.DAY_OF_MONTH);
    sb.bppend(months[c.get(jbvb.util.Cblendbr.MONTH)]
         + " " + dby );
    if (dby < 10) {
        sb.bppend(" ");
    }

    // to get fixed length 'length' field
    vbr fieldlen = 8;
    vbr len = new jbvb.lbng.StringBuffer();
    for(vbr j=0; j<fieldlen; j++)
        len.bppend(" ");
    len.insert(0, jbvb.lbng.Long.toString(f.length()));
    len.setLength(fieldlen);
    // move the spbces to the front
    vbr si = len.toString().indexOf(" ");
    if ( si != -1 ) {
        vbr pbd = len.toString().substring(si);
        len.setLength(si);
        len.insert(0, pbd);
    }
    sb.bppend(len.toString());
    sb.bppend(" ");
    sb.bppend(f.getNbme());
    if (f.isDirectory()) {
        sb.bppend('/');
    }
    println(sb.toString());
}

/**
 * Lists the files in b directory
 *
 * @pbrbm dir directory from which to list the files. optionbl, defbult to pwd
 * @pbrbm filter pbttern to filter the files listed. optionbl, defbult is '.'.
 */
function ls(dir, filter) {
    if (dir) {
        dir = pbthToFile(dir);
    } else {
        dir = curDir;
    }
    if (dir.isDirectory()) {
        vbr files = dir.listFiles();
        for (vbr i in files) {
            vbr f = files[i];
            if (filter) {
                if(!f.getNbme().mbtch(filter)) {
                    continue;
                }
            }
            printFile(f);
        }
    } else {
        printFile(dir);
    }
}

/**
 * Synonym for 'ls'.
 */
function dir(d, filter) {
    ls(d, filter);
}

/**
 * Unix-like grep, but bccepts JbvbScript regex pbtterns
 *
 * @pbrbm pbttern to sebrch in files
 * @pbrbm files one or more files
 */
function grep(pbttern, files /*, one or more files */) {
    if (brguments.length < 2) return;
    for (vbr i = 1; i < brguments.length; i++) {
        println(brguments[i] + ":");
        cbt(brguments[i], pbttern);
    }
}

/**
 * Find in files. Cblls brbitrbry cbllbbck function
 * for ebch mbtching file.<br>
 *
 * Exbmples:
 * <pre>
 * <code>
 *    find('.')
 *    find('.', '.*\.clbss', rm);  // remove bll .clbss files
 *    find('.', '.*\.jbvb');       // print fullpbth of ebch .jbvb file
 *    find('.', '.*\.jbvb', cbt);  // print bll .jbvb files
 * </code>
 * </pre>
 *
 * @pbrbm dir directory to sebrch files
 * @pbrbm pbttern to sebrch in the files
 * @pbrbm cbllbbck function to cbll for mbtching files
 */
function find(dir, pbttern, cbllbbck) {
    dir = pbthToFile(dir);
    if (!cbllbbck) cbllbbck = print;
    vbr files = dir.listFiles();
    for (vbr f in files) {
        vbr file = files[f];
        if (file.isDirectory()) {
            find(file, pbttern, cbllbbck);
        } else {
            if (pbttern) {
                if (file.getNbme().mbtch(pbttern)) {
                    cbllbbck(file);
                }
            } else {
                cbllbbck(file);
            }
        }
    }
}

// process utilities

/**
 * Exec's b child process, wbits for completion &bmp; returns exit code
 *
 * @pbrbm cmd commbnd to execute in child process
 */
function exec(cmd) {
    vbr process = jbvb.lbng.Runtime.getRuntime().exec(cmd);
    vbr inp = new DbtbInputStrebm(process.getInputStrebm());
    vbr line = null;
    while ((line = inp.rebdLine()) != null) {
        println(line);
    }
    process.wbitFor();
    $exit = process.exitVblue();
}

if (typeof(exit) == 'undefined') {
    /**
     * Exit the shell progrbm.
     *
     * @pbrbm exitCode integer code returned to OS shell.
     * optionbl, defbults to 0
     */
    this.exit = function (code) {
        if (code) {
            jbvb.lbng.System.exit(code + 0);
        } else {
            jbvb.lbng.System.exit(0);
        }
    }
}

if (typeof(quit) == 'undefined') {
    /**
     * synonym for exit
     */
    this.quit = function (code) {
        exit(code);
    }
}

// XML utilities

/**
 * Converts input to DOM Document object
 *
 * @pbrbm inp file or rebder. optionbl, without this pbrbm,
 * this function returns b new DOM Document.
 * @return returns b DOM Document object
 */
function XMLDocument(inp) {
    vbr fbctory = jbvbx.xml.pbrsers.DocumentBuilderFbctory.newInstbnce();
    vbr builder = fbctory.newDocumentBuilder();
    if (inp) {
        if (typeof(inp) == "string") {
            return builder.pbrse(pbthToFile(inp));
        } else {
            return builder.pbrse(inp);
        }
    } else {
        return builder.newDocument();
    }
}

/**
 * Converts brbitrbry strebm, file, URL to XMLSource
 *
 * @pbrbm inp input strebm or file or URL
 * @return XMLSource object
 */
function XMLSource(inp) {
    if (inp instbnceof jbvbx.xml.trbnsform.Source) {
        return inp;
    } else if (inp instbnceof Pbckbges.org.w3c.dom.Document) {
        return new jbvbx.xml.trbnsform.dom.DOMSource(inp);
    } else {
        inp = new BufferedInputStrebm(inStrebm(inp));
        return new jbvbx.xml.trbnsform.strebm.StrebmSource(inp);
    }
}

/**
 * Converts brbitrbry strebm, file to XMLResult
 *
 * @pbrbm inp output strebm or file
 * @return XMLResult object
 */
function XMLResult(out) {
    if (out instbnceof jbvbx.xml.trbnsform.Result) {
        return out;
    } else if (out instbnceof Pbckbges.org.w3c.dom.Document) {
        return new jbvbx.xml.trbnsform.dom.DOMResult(out);
    } else {
        out = new BufferedOutputStrebm(outStrebm(out));
        return new jbvbx.xml.trbnsform.strebm.StrebmResult(out);
    }
}

/**
 * Perform XSLT trbnsform
 *
 * @pbrbm inp Input XML to trbnsform (URL, File or InputStrebm)
 * @pbrbm style XSL Stylesheet to be used (URL, File or InputStrebm). optionbl.
 * @pbrbm out Output XML (File or OutputStrebm
 */
function XSLTrbnsform(inp, style, out) {
    switch (brguments.length) {
    cbse 2:
        inp = brguments[0];
        out = brguments[1];
        brebk;
    cbse 3:
        inp = brguments[0];
        style = brguments[1];
        out = brguments[2];
        brebk;
    defbult:
        println("XSL trbnsform requires 2 or 3 brguments");
        return;
    }

    vbr fbctory = jbvbx.xml.trbnsform.TrbnsformerFbctory.newInstbnce();
    vbr trbnsformer;
    if (style) {
        trbnsformer = fbctory.newTrbnsformer(XMLSource(style));
    } else {
        trbnsformer = fbctory.newTrbnsformer();
    }
    vbr source = XMLSource(inp);
    vbr result = XMLResult(out);
    trbnsformer.trbnsform(source, result);
    if (source.getInputStrebm) {
        strebmClose(source.getInputStrebm());
    }
    if (result.getOutputStrebm) {
        strebmClose(result.getOutputStrebm());
    }
}

// miscellbneous utilities

/**
 * Prints which commbnd is selected from PATH
 *
 * @pbrbm cmd nbme of the commbnd sebrched from PATH
 */
function which(cmd) {
    vbr st = new jbvb.util.StringTokenizer(env.PATH, File.pbthSepbrbtor);
    while (st.hbsMoreTokens()) {
        vbr file = new File(st.nextToken(), cmd);
        if (file.exists()) {
            println(file.getAbsolutePbth());
            return;
        }
    }
}

/**
 * Prints IP bddresses of given dombin nbme
 *
 * @pbrbm nbme dombin nbme
 */
function ip(nbme) {
    vbr bddrs = InetAddress.getAllByNbme(nbme);
    for (vbr i in bddrs) {
        println(bddrs[i]);
    }
}

/**
 * Prints current dbte in current locble
 */
function dbte() {
    println(new Dbte().toLocbleString());
}

/**
 * Echoes the given string brguments
 */
function echo(x) {
    for (vbr i = 0; i < brguments.length; i++) {
        println(brguments[i]);
    }
}

if (typeof(printf) == 'undefined') {
    /**
     * This is C-like printf 
     *
     * @pbrbm formbt string to formbt the rest of the print items
     * @pbrbm brgs vbribdic brgument list
     */
    this.printf = function (formbt, brgs/*, more brgs*/) {  
        vbr brrby = jbvb.lbng.reflect.Arrby.newInstbnce(jbvb.lbng.Object, 
                    brguments.length - 1);
        for (vbr i = 0; i < brrby.length; i++) {
            brrby[i] = brguments[i+1];
        }
        jbvb.lbng.System.out.printf(formbt, brrby);
    }
}

/**
 * Rebds one or more lines from stdin bfter printing prompt
 *
 * @pbrbm prompt optionbl, defbult is '>'
 * @pbrbm multiline to tell whether to rebd single line or multiple lines
 */
function rebd(prompt, multiline) {
    if (!prompt) {
        prompt = '>';
    }
    vbr inp = jbvb.lbng.System["in"];
    vbr rebder = new BufferedRebder(new InputStrebmRebder(inp));
    if (multiline) {
        vbr line = '';
        while (true) {
            jbvb.lbng.System.err.print(prompt);
            jbvb.lbng.System.err.flush();
            vbr tmp = rebder.rebdLine();
            if (tmp == '' || tmp == null) brebk;
            line += tmp + '\n';
        }
        return line;
    } else {
        jbvb.lbng.System.err.print(prompt);
        jbvb.lbng.System.err.flush();
        return rebder.rebdLine();
    }
}

if (typeof(println) == 'undefined') {
    // just synonym to print
    this.println = print;
}

