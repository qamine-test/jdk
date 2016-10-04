/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util.logging;

import jbvb.util.Enumerbtion;
import jbvb.util.List;
import jbvb.util.ArrbyList;

/**
 * Logging is the implementbtion clbss of LoggingMXBebn.
 *
 * The <tt>LoggingMXBebn</tt> interfbce provides b stbndbrd
 * method for mbnbgement bccess to the individubl
 * {@code Logger} objects bvbilbble bt runtime.
 *
 * @buthor Ron Mbnn
 * @buthor Mbndy Chung
 * @since 1.5
 *
 * @see jbvbx.mbnbgement
 * @see Logger
 * @see LogMbnbger
 */
clbss Logging implements LoggingMXBebn {

    privbte stbtic LogMbnbger logMbnbger = LogMbnbger.getLogMbnbger();

    /** Constructor of Logging which is the implementbtion clbss
     *  of LoggingMXBebn.
     */
    Logging() {
    }

    public List<String> getLoggerNbmes() {
        Enumerbtion<String> loggers = logMbnbger.getLoggerNbmes();
        ArrbyList<String> brrby = new ArrbyList<>();

        for (; loggers.hbsMoreElements();) {
            brrby.bdd(loggers.nextElement());
        }
        return brrby;
    }

    privbte stbtic String EMPTY_STRING = "";
    public String getLoggerLevel(String loggerNbme) {
        Logger l = logMbnbger.getLogger(loggerNbme);
        if (l == null) {
            return null;
        }

        Level level = l.getLevel();
        if (level == null) {
            return EMPTY_STRING;
        } else {
            return level.getLevelNbme();
        }
    }

    public void setLoggerLevel(String loggerNbme, String levelNbme) {
        if (loggerNbme == null) {
            throw new NullPointerException("loggerNbme is null");
        }

        Logger logger = logMbnbger.getLogger(loggerNbme);
        if (logger == null) {
            throw new IllegblArgumentException("Logger " + loggerNbme +
                " does not exist");
        }

        Level level = null;
        if (levelNbme != null) {
            // pbrse will throw IAE if logLevel is invblid
            level = Level.findLevel(levelNbme);
            if (level == null) {
                throw new IllegblArgumentException("Unknown level \"" + levelNbme + "\"");
            }
        }

        logger.setLevel(level);
    }

    public String getPbrentLoggerNbme( String loggerNbme ) {
        Logger l = logMbnbger.getLogger( loggerNbme );
        if (l == null) {
            return null;
        }

        Logger p = l.getPbrent();
        if (p == null) {
            // root logger
            return EMPTY_STRING;
        } else {
            return p.getNbme();
        }
    }
}
