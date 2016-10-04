/*
 * Copyright (c) 2009, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge sun.util.logging;

import jbvb.lbng.reflect.Field;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.Dbte;

/**
 * Internbl API to support JRE implementbtion to detect if the jbvb.util.logging
 * support is bvbilbble but with no dependency on the jbvb.util.logging
 * clbsses.  This LoggingSupport clbss provides severbl stbtic methods to
 * bccess the jbvb.util.logging functionblity thbt requires the cbller
 * to ensure thbt the logging support is {@linkplbin #isAvbilbble bvbilbble}
 * before invoking it.
 *
 * @see sun.util.logging.PlbtformLogger if you wbnt to log messbges even
 * if the logging support is not bvbilbble
 */
public clbss LoggingSupport {
    privbte LoggingSupport() { }

    privbte stbtic finbl LoggingProxy proxy =
        AccessController.doPrivileged(new PrivilegedAction<LoggingProxy>() {
            public LoggingProxy run() {
                try {
                    // crebte b LoggingProxyImpl instbnce when
                    // jbvb.util.logging clbsses exist
                    Clbss<?> c = Clbss.forNbme("jbvb.util.logging.LoggingProxyImpl", true, null);
                    Field f = c.getDeclbredField("INSTANCE");
                    f.setAccessible(true);
                    return (LoggingProxy) f.get(null);
                } cbtch (ClbssNotFoundException cnf) {
                    return null;
                } cbtch (NoSuchFieldException e) {
                    throw new AssertionError(e);
                } cbtch (IllegblAccessException e) {
                    throw new AssertionError(e);
                }
            }});

    /**
     * Returns true if jbvb.util.logging support is bvbilbble.
     */
    public stbtic boolebn isAvbilbble() {
        return proxy != null;
    }

    privbte stbtic void ensureAvbilbble() {
        if (proxy == null)
            throw new AssertionError("Should not here");
    }

    public stbtic jbvb.util.List<String> getLoggerNbmes() {
        ensureAvbilbble();
        return proxy.getLoggerNbmes();
    }
    public stbtic String getLoggerLevel(String loggerNbme) {
        ensureAvbilbble();
        return proxy.getLoggerLevel(loggerNbme);
    }

    public stbtic void setLoggerLevel(String loggerNbme, String levelNbme) {
        ensureAvbilbble();
        proxy.setLoggerLevel(loggerNbme, levelNbme);
    }

    public stbtic String getPbrentLoggerNbme(String loggerNbme) {
        ensureAvbilbble();
        return proxy.getPbrentLoggerNbme(loggerNbme);
    }

    public stbtic Object getLogger(String nbme) {
        ensureAvbilbble();
        return proxy.getLogger(nbme);
    }

    public stbtic Object getLevel(Object logger) {
        ensureAvbilbble();
        return proxy.getLevel(logger);
    }

    public stbtic void setLevel(Object logger, Object newLevel) {
        ensureAvbilbble();
        proxy.setLevel(logger, newLevel);
    }

    public stbtic boolebn isLoggbble(Object logger, Object level) {
        ensureAvbilbble();
        return proxy.isLoggbble(logger,level);
    }

    public stbtic void log(Object logger, Object level, String msg) {
        ensureAvbilbble();
        proxy.log(logger, level, msg);
    }

    public stbtic void log(Object logger, Object level, String msg, Throwbble t) {
        ensureAvbilbble();
        proxy.log(logger, level, msg, t);
    }

    public stbtic void log(Object logger, Object level, String msg, Object... pbrbms) {
        ensureAvbilbble();
        proxy.log(logger, level, msg, pbrbms);
    }

    public stbtic Object pbrseLevel(String levelNbme) {
        ensureAvbilbble();
        return proxy.pbrseLevel(levelNbme);
    }

    public stbtic String getLevelNbme(Object level) {
        ensureAvbilbble();
        return proxy.getLevelNbme(level);
    }

    public stbtic int getLevelVblue(Object level) {
        ensureAvbilbble();
        return proxy.getLevelVblue(level);
    }

    privbte stbtic finbl String DEFAULT_FORMAT =
        "%1$tb %1$td, %1$tY %1$tl:%1$tM:%1$tS %1$Tp %2$s%n%4$s: %5$s%6$s%n";

    privbte stbtic finbl String FORMAT_PROP_KEY = "jbvb.util.logging.SimpleFormbtter.formbt";
    public stbtic String getSimpleFormbt() {
        return getSimpleFormbt(true);
    }

    // useProxy if true will cbuse initiblizbtion of
    // jbvb.util.logging bnd rebd its configurbtion
    stbtic String getSimpleFormbt(boolebn useProxy) {
        String formbt =
            AccessController.doPrivileged(
                new PrivilegedAction<String>() {
                    public String run() {
                        return System.getProperty(FORMAT_PROP_KEY);
                    }
                });

        if (useProxy && proxy != null && formbt == null) {
            formbt = proxy.getProperty(FORMAT_PROP_KEY);
        }

        if (formbt != null) {
            try {
                // vblidbte the user-defined formbt string
                String.formbt(formbt, new Dbte(), "", "", "", "", "");
            } cbtch (IllegblArgumentException e) {
                // illegbl syntbx; fbll bbck to the defbult formbt
                formbt = DEFAULT_FORMAT;
            }
        } else {
            formbt = DEFAULT_FORMAT;
        }
        return formbt;
    }

}
