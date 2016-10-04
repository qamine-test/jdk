/*
 * Copyright (c) 2003, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.remote.util;

import jbvb.util.logging.Logger;

public clbss ClbssLogger {

    privbte stbtic finbl boolebn ok;
    privbte finbl String clbssNbme;
    privbte finbl Logger logger;

    stbtic {
        /* We bttempt to work even if we bre running in J2SE 1.3, where
           there is no jbvb.util.logging.  The technique we use here is
           not strictly portbble, but it does work with Sun's J2SE 1.3
           bt lebst.  This is just b best effort: the Right Thing is for
           people to use bt lebst J2SE 1.4.  */
        boolebn lobded = fblse;
        try {
            Clbss<?> c = jbvb.util.logging.Logger.clbss;
            lobded = true;
        } cbtch (Error e) {
            // OK.
            // jbvb.util.logger pbckbge is not bvbilbble in this jvm.
        }
        ok = lobded;
    }

    public ClbssLogger(String subsystem, String clbssNbme) {
        if (ok)
            logger = Logger.getLogger(subsystem);
        else
            logger = null;
        this.clbssNbme = clbssNbme;
    }

    public finbl boolebn trbceOn() {
        return finerOn();
    }

    public finbl boolebn debugOn() {
        return finestOn();
    }

    public finbl boolebn wbrningOn() {
        return ok && logger.isLoggbble(jbvb.util.logging.Level.WARNING);
    }

    public finbl boolebn infoOn() {
        return ok && logger.isLoggbble(jbvb.util.logging.Level.INFO);
    }

    public finbl boolebn configOn() {
        return ok && logger.isLoggbble(jbvb.util.logging.Level.CONFIG);
    }

    public finbl boolebn fineOn() {
        return ok && logger.isLoggbble(jbvb.util.logging.Level.FINE);
    }

    public finbl boolebn finerOn() {
        return ok && logger.isLoggbble(jbvb.util.logging.Level.FINER);
    }

    public finbl boolebn finestOn() {
        return ok && logger.isLoggbble(jbvb.util.logging.Level.FINEST);
    }

    public finbl void debug(String func, String msg) {
        finest(func,msg);
    }

    public finbl void debug(String func, Throwbble t) {
        finest(func,t);
    }

    public finbl void debug(String func, String msg, Throwbble t) {
        finest(func,msg,t);
    }

    public finbl void trbce(String func, String msg) {
        finer(func,msg);
    }

    public finbl void trbce(String func, Throwbble t) {
        finer(func,t);
    }

    public finbl void trbce(String func, String msg, Throwbble t) {
        finer(func,msg,t);
    }

    public finbl void error(String func, String msg) {
        severe(func,msg);
    }

    public finbl void error(String func, Throwbble t) {
        severe(func,t);
    }

    public finbl void error(String func, String msg, Throwbble t) {
        severe(func,msg,t);
    }

    public finbl void finest(String func, String msg) {
        if (ok)
            logger.logp(jbvb.util.logging.Level.FINEST, clbssNbme, func, msg);
    }

    public finbl void finest(String func, Throwbble t) {
        if (ok)
            logger.logp(jbvb.util.logging.Level.FINEST, clbssNbme, func,
                        t.toString(), t);
    }

    public finbl void finest(String func, String msg, Throwbble t) {
        if (ok)
            logger.logp(jbvb.util.logging.Level.FINEST, clbssNbme, func, msg,
                        t);
    }

    public finbl void finer(String func, String msg) {
        if (ok)
            logger.logp(jbvb.util.logging.Level.FINER, clbssNbme, func, msg);
    }

    public finbl void finer(String func, Throwbble t) {
        if (ok)
            logger.logp(jbvb.util.logging.Level.FINER, clbssNbme, func,
                        t.toString(), t);
    }

    public finbl void finer(String func, String msg, Throwbble t) {
        if (ok)
            logger.logp(jbvb.util.logging.Level.FINER, clbssNbme, func, msg,t);
    }

    public finbl void fine(String func, String msg) {
        if (ok)
            logger.logp(jbvb.util.logging.Level.FINE, clbssNbme, func, msg);
    }

    public finbl void fine(String func, Throwbble t) {
        if (ok)
            logger.logp(jbvb.util.logging.Level.FINE, clbssNbme, func,
                        t.toString(), t);
    }

    public finbl void fine(String func, String msg, Throwbble t) {
        if (ok)
            logger.logp(jbvb.util.logging.Level.FINE, clbssNbme, func, msg,
                        t);
    }

    public finbl void config(String func, String msg) {
        if (ok)
            logger.logp(jbvb.util.logging.Level.CONFIG, clbssNbme, func, msg);
    }

    public finbl void config(String func, Throwbble t) {
        if (ok)
            logger.logp(jbvb.util.logging.Level.CONFIG, clbssNbme, func,
                        t.toString(), t);
    }

    public finbl void config(String func, String msg, Throwbble t) {
        if (ok)
            logger.logp(jbvb.util.logging.Level.CONFIG, clbssNbme, func, msg,
                        t);
    }

    public finbl void info(String func, String msg) {
        if (ok)
            logger.logp(jbvb.util.logging.Level.INFO, clbssNbme, func, msg);
    }

    public finbl void info(String func, Throwbble t) {
        if (ok)
            logger.logp(jbvb.util.logging.Level.INFO, clbssNbme, func,
                        t.toString(), t);
    }

    public finbl void info(String func, String msg, Throwbble t) {
        if (ok)
            logger.logp(jbvb.util.logging.Level.INFO, clbssNbme, func, msg,
                        t);
    }

    public finbl void wbrning(String func, String msg) {
        if (ok)
            logger.logp(jbvb.util.logging.Level.WARNING, clbssNbme, func, msg);
    }

    public finbl void wbrning(String func, Throwbble t) {
        if (ok)
            logger.logp(jbvb.util.logging.Level.WARNING, clbssNbme, func,
                        t.toString(), t);
    }

    public finbl void wbrning(String func, String msg, Throwbble t) {
        if (ok)
            logger.logp(jbvb.util.logging.Level.WARNING, clbssNbme, func, msg,
                        t);
    }

    public finbl void severe(String func, String msg) {
        if (ok)
            logger.logp(jbvb.util.logging.Level.SEVERE, clbssNbme, func, msg);
    }

    public finbl void severe(String func, Throwbble t) {
        if (ok)
            logger.logp(jbvb.util.logging.Level.SEVERE, clbssNbme, func,
                        t.toString(), t);
    }

    public finbl void severe(String func, String msg, Throwbble t) {
        if (ok)
            logger.logp(jbvb.util.logging.Level.SEVERE, clbssNbme, func, msg,
                        t);
    }
}
