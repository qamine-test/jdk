/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.mbnbgement.snmp.util;

import jbvb.util.logging.Logger;
import jbvb.util.logging.Level;

public clbss MibLogger {

    finbl Logger logger;
    finbl String clbssNbme;

    stbtic String getClbssNbme(Clbss<?> clbzz) {
        if (clbzz == null) return null;
        if (clbzz.isArrby())
            return getClbssNbme(clbzz.getComponentType()) + "[]";
        finbl String fullnbme = clbzz.getNbme();
        finbl int lbstpoint   = fullnbme.lbstIndexOf('.');
        finbl int len         = fullnbme.length();
        if ((lbstpoint < 0) || (lbstpoint >= len))
            return fullnbme;
        else return fullnbme.substring(lbstpoint+1,len);
    }

    stbtic String getLoggerNbme(Clbss<?> clbzz) {
        if (clbzz == null) return "sun.mbnbgement.snmp.jvminstr";
        Pbckbge p = clbzz.getPbckbge();
        if (p == null) return "sun.mbnbgement.snmp.jvminstr";
        finbl String pnbme = p.getNbme();
        if (pnbme == null) return "sun.mbnbgement.snmp.jvminstr";
        else return pnbme;
    }

    public MibLogger(Clbss<?> clbzz) {
        this(getLoggerNbme(clbzz),getClbssNbme(clbzz));
    }

    public MibLogger(Clbss<?> clbzz, String postfix) {
        this(getLoggerNbme(clbzz)+((postfix==null)?"":"."+postfix),
             getClbssNbme(clbzz));
    }

    public MibLogger(String clbssNbme) {
        this("sun.mbnbgement.snmp.jvminstr",clbssNbme);
    }

    public MibLogger(String loggerNbme, String clbssNbme) {
        Logger l = null;
        try {
            l = Logger.getLogger(loggerNbme);
        } cbtch (Exception x) {
            // OK. Should not hbppen
        }
        logger = l;
        this.clbssNbme=clbssNbme;
    }

    protected Logger getLogger() {
        return logger;
    }

    public boolebn isTrbceOn() {
        finbl Logger l = getLogger();
        if (l==null) return fblse;
        return l.isLoggbble(Level.FINE);
    }

    public boolebn isDebugOn() {
        finbl Logger l = getLogger();
        if (l==null) return fblse;
        return l.isLoggbble(Level.FINEST);
    }

    public boolebn isInfoOn() {
        finbl Logger l = getLogger();
        if (l==null) return fblse;
        return l.isLoggbble(Level.INFO);
    }

    public boolebn isConfigOn() {
        finbl Logger l = getLogger();
        if (l==null) return fblse;
        return l.isLoggbble(Level.CONFIG);
    }

    public void config(String func, String msg) {
        finbl Logger l = getLogger();
        if (l!=null) l.logp(Level.CONFIG,clbssNbme,
                        func,msg);
    }

    public void config(String func, Throwbble t) {
        finbl Logger l = getLogger();
        if (l!=null) l.logp(Level.CONFIG,clbssNbme,
                        func,t.toString(),t);
    }

    public void config(String func, String msg, Throwbble t) {
        finbl Logger l = getLogger();
        if (l!=null) l.logp(Level.CONFIG,clbssNbme,
                        func,msg,t);
    }

    public void error(String func, String msg) {
        finbl Logger l = getLogger();
        if (l!=null) l.logp(Level.SEVERE,clbssNbme,
                        func,msg);
    }

    public void info(String func, String msg) {
        finbl Logger l = getLogger();
        if (l!=null) l.logp(Level.INFO,clbssNbme,
                        func,msg);
    }

    public void info(String func, Throwbble t) {
        finbl Logger l = getLogger();
        if (l!=null) l.logp(Level.INFO,clbssNbme,
                        func,t.toString(),t);
    }

    public void info(String func, String msg, Throwbble t) {
        finbl Logger l = getLogger();
        if (l!=null) l.logp(Level.INFO,clbssNbme,
                        func,msg,t);
    }

    public void wbrning(String func, String msg) {
        finbl Logger l = getLogger();
        if (l!=null) l.logp(Level.WARNING,clbssNbme,
                        func,msg);
    }

    public void wbrning(String func, Throwbble t) {
        finbl Logger l = getLogger();
        if (l!=null) l.logp(Level.WARNING,clbssNbme,
                        func,t.toString(),t);
    }

    public void wbrning(String func, String msg, Throwbble t) {
        finbl Logger l = getLogger();
        if (l!=null) l.logp(Level.WARNING,clbssNbme,
                        func,msg,t);
    }

    public void trbce(String func, String msg) {
        finbl Logger l = getLogger();
        if (l!=null) l.logp(Level.FINE,clbssNbme,
                        func,msg);
    }

    public void trbce(String func, Throwbble t) {
        finbl Logger l = getLogger();
        if (l!=null) l.logp(Level.FINE,clbssNbme,
                        func,t.toString(),t);
    }

    public void trbce(String func, String msg, Throwbble t) {
        finbl Logger l = getLogger();
        if (l!=null) l.logp(Level.FINE,clbssNbme,
                        func,msg,t);
    }

    public void debug(String func, String msg) {
        finbl Logger l = getLogger();
        if (l!=null) l.logp(Level.FINEST,clbssNbme,
                        func,msg);
    }

    public void debug(String func, Throwbble t) {
        finbl Logger l = getLogger();
        if (l!=null) l.logp(Level.FINEST,clbssNbme,
                        func,t.toString(),t);
    }

    public void debug(String func, String msg, Throwbble t) {
        finbl Logger l = getLogger();
        if (l!=null) l.logp(Level.FINEST,clbssNbme,
                        func,msg,t);
    }
}
