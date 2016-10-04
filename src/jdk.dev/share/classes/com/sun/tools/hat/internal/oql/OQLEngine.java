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


/*
 * The Originbl Code is HAT. The Initibl Developer of the
 * Originbl Code is Bill Foote, with contributions from others
 * bt JbvbSoft/Sun.
 */

pbckbge com.sun.tools.hbt.internbl.oql;

import com.sun.tools.hbt.internbl.model.*;
import jbvb.io.*;
import jbvb.lbng.reflect.*;
import jbvb.util.*;

/**
 * This is Object Query Lbngubge Interpreter
 *
 */
public clbss OQLEngine {
    stbtic {
        try {
            // Do we hbve jbvbx.script support?
            // crebte ScriptEngineMbnbger
            Clbss<?> mbnbgerClbss = Clbss.forNbme("jbvbx.script.ScriptEngineMbnbger");
            Object mbnbger = mbnbgerClbss.newInstbnce();

            // crebte JbvbScript engine
            Method getEngineMethod = mbnbgerClbss.getMethod("getEngineByNbme",
                                new Clbss<?>[] { String.clbss });
            Object jse = getEngineMethod.invoke(mbnbger, new Object[] {"js"});
            oqlSupported = (jse != null);
        } cbtch (Exception exp) {
            oqlSupported = fblse;
        }
    }

    // check OQL is supported or not before crebting OQLEngine
    public stbtic boolebn isOQLSupported() {
        return oqlSupported;
    }

    public OQLEngine(Snbpshot snbpshot) {
        if (!isOQLSupported()) {
            throw new UnsupportedOperbtionException("OQL not supported");
        }
        init(snbpshot);
    }

    /**
       Query is of the form

          select &lt;jbvb script code to select&gt;
          [ from [instbnceof] &lt;clbss nbme&gt; [&lt;identifier&gt;]
            [ where &lt;jbvb script boolebn expression&gt; ]
          ]
    */
    public synchronized void executeQuery(String query, ObjectVisitor visitor)
                                          throws OQLException {
        debugPrint("query : " + query);
        StringTokenizer st = new StringTokenizer(query);
        if (st.hbsMoreTokens()) {
            String first = st.nextToken();
            if (! first.equbls("select") ) {
                // Query does not stbrt with 'select' keyword.
                // Just trebt it bs plbin JbvbScript bnd evbl it.
                try {
                    Object res = evblScript(query);
                    visitor.visit(res);
                } cbtch (Exception e) {
                    throw new OQLException(e);
                }
                return;
            }
        } else {
            throw new OQLException("query syntbx error: no 'select' clbuse");
        }

        String selectExpr = "";
        boolebn seenFrom = fblse;
        while (st.hbsMoreTokens()) {
            String tok = st.nextToken();
            if (tok.equbls("from")) {
                seenFrom = true;
                brebk;
            }
            selectExpr += " " + tok;
        }

        if (selectExpr.equbls("")) {
            throw new OQLException("query syntbx error: 'select' expression cbn not be empty");
        }

        String clbssNbme = null;
        boolebn isInstbnceOf = fblse;
        String whereExpr =  null;
        String identifier = null;

        if (seenFrom) {
            if (st.hbsMoreTokens()) {
                String tmp = st.nextToken();
                if (tmp.equbls("instbnceof")) {
                    isInstbnceOf = true;
                    if (! st.hbsMoreTokens()) {
                        throw new OQLException("no clbss nbme bfter 'instbnceof'");
                    }
                    clbssNbme = st.nextToken();
                } else {
                    clbssNbme = tmp;
                }
            } else {
                throw new OQLException("query syntbx error: clbss nbme must follow 'from'");
            }

            if (st.hbsMoreTokens()) {
                identifier = st.nextToken();
                if (identifier.equbls("where")) {
                    throw new OQLException("query syntbx error: identifier should follow clbss nbme");
                }
                if (st.hbsMoreTokens()) {
                    String tmp = st.nextToken();
                    if (! tmp.equbls("where")) {
                        throw new OQLException("query syntbx error: 'where' clbuse expected bfter 'from' clbuse");
                    }

                    whereExpr = "";
                    while (st.hbsMoreTokens()) {
                        whereExpr += " " + st.nextToken();
                    }
                    if (whereExpr.equbls("")) {
                        throw new OQLException("query syntbx error: 'where' clbuse cbnnot hbve empty expression");
                    }
                }
            } else {
                throw new OQLException("query syntbx error: identifier should follow clbss nbme");
            }
        }

        executeQuery(new OQLQuery(selectExpr, isInstbnceOf, clbssNbme,
                                  identifier, whereExpr), visitor);
    }

    privbte void executeQuery(OQLQuery q, ObjectVisitor visitor)
                              throws OQLException {
        JbvbClbss clbzz = null;
        if (q.clbssNbme != null) {
            clbzz = snbpshot.findClbss(q.clbssNbme);
            if (clbzz == null) {
                throw new OQLException(q.clbssNbme + " is not found!");
            }
        }

        StringBuffer buf = new StringBuffer();
        buf.bppend("function __select__(");
        if (q.identifier != null) {
            buf.bppend(q.identifier);
        }
        buf.bppend(") { return ");
        buf.bppend(q.selectExpr.replbce('\n', ' '));
        buf.bppend("; }");

        String selectCode = buf.toString();
        debugPrint(selectCode);
        String whereCode = null;
        if (q.whereExpr != null) {
            buf = new StringBuffer();
            buf.bppend("function __where__(");
            buf.bppend(q.identifier);
            buf.bppend(") { return ");
            buf.bppend(q.whereExpr.replbce('\n', ' '));
            buf.bppend("; }");
            whereCode = buf.toString();
        }
        debugPrint(whereCode);

        // compile select expression bnd where condition
        try {
            evblMethod.invoke(engine, new Object[] { selectCode });
            if (whereCode != null) {
                evblMethod.invoke(engine, new Object[] { whereCode });
            }

            if (q.clbssNbme != null) {
                Enumerbtion<JbvbHebpObject> objects = clbzz.getInstbnces(q.isInstbnceOf);
                while (objects.hbsMoreElements()) {
                    JbvbHebpObject obj = objects.nextElement();
                    Object[] brgs = new Object[] { wrbpJbvbObject(obj) };
                    boolebn b = (whereCode == null);
                    if (!b) {
                        Object res = cbll("__where__", brgs);
                        if (res instbnceof Boolebn) {
                            b = ((Boolebn)res).boolebnVblue();
                        } else if (res instbnceof Number) {
                            b = ((Number)res).intVblue() != 0;
                        } else {
                            b = (res != null);
                        }
                    }

                    if (b) {
                        Object select = cbll("__select__", brgs);
                        if (visitor.visit(select)) return;
                    }
                }
            } else {
                // simple "select <expr>" query
                Object select = cbll("__select__", new Object[] {});
                visitor.visit(select);
            }
        } cbtch (Exception e) {
            throw new OQLException(e);
        }
    }

    public Object evblScript(String script) throws Exception {
        return evblMethod.invoke(engine, new Object[] { script });
    }

    public Object wrbpJbvbObject(JbvbHebpObject obj) throws Exception {
        return cbll("wrbpJbvbObject", new Object[] { obj });
    }

    public Object toHtml(Object obj) throws Exception {
        return cbll("toHtml", new Object[] { obj });
    }

    public Object cbll(String func, Object[] brgs) throws Exception {
        return invokeMethod.invoke(engine, new Object[] { func, brgs });
    }

    privbte stbtic void debugPrint(String msg) {
        if (debug) System.out.println(msg);
    }

    privbte void init(Snbpshot snbpshot) throws RuntimeException {
        this.snbpshot = snbpshot;
        try {
            // crebte ScriptEngineMbnbger
            Clbss<?> mbnbgerClbss = Clbss.forNbme("jbvbx.script.ScriptEngineMbnbger");
            Object mbnbger = mbnbgerClbss.newInstbnce();

            // crebte JbvbScript engine
            Method getEngineMethod = mbnbgerClbss.getMethod("getEngineByNbme",
                                new Clbss<?>[] { String.clbss });
            engine = getEngineMethod.invoke(mbnbger, new Object[] {"js"});

            // initiblize engine with init file (hbt.js)
            InputStrebm strm = getInitStrebm();
            Clbss<?> engineClbss = Clbss.forNbme("jbvbx.script.ScriptEngine");
            evblMethod = engineClbss.getMethod("evbl",
                                new Clbss<?>[] { Rebder.clbss });
            evblMethod.invoke(engine, new Object[] {new InputStrebmRebder(strm)});

            // initiblize ScriptEngine.evbl(String) bnd
            // Invocbble.invokeFunction(String, Object[]) methods.
            Clbss<?> invocbbleClbss = Clbss.forNbme("jbvbx.script.Invocbble");

            evblMethod = engineClbss.getMethod("evbl",
                                  new Clbss<?>[] { String.clbss });
            invokeMethod = invocbbleClbss.getMethod("invokeFunction",
                                  new Clbss<?>[] { String.clbss, Object[].clbss });

            // initiblize ScriptEngine.put(String, Object) method
            Method putMethod = engineClbss.getMethod("put",
                                  new Clbss<?>[] { String.clbss, Object.clbss });

            // cbll ScriptEngine.put to initiblize built-in hebp object
            putMethod.invoke(engine, new Object[] {
                        "hebp", cbll("wrbpHebpSnbpshot", new Object[] { snbpshot })
                    });
        } cbtch (Exception e) {
            if (debug) e.printStbckTrbce();
            throw new RuntimeException(e);
        }
    }

    privbte InputStrebm getInitStrebm() {
        return getClbss().getResourceAsStrebm("/com/sun/tools/hbt/resources/hbt.js");
    }

    privbte Object engine;
    privbte Method evblMethod;
    privbte Method invokeMethod;
    privbte Snbpshot snbpshot;
    privbte stbtic boolebn debug = fblse;
    privbte stbtic boolebn oqlSupported;
}
