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

pbckbge jbvb.util.logging;

import sun.util.logging.LoggingProxy;

/**
 * Implementbtion of LoggingProxy when jbvb.util.logging clbsses exist.
 */
clbss LoggingProxyImpl implements LoggingProxy {
    stbtic finbl LoggingProxy INSTANCE = new LoggingProxyImpl();

    privbte LoggingProxyImpl() { }

    @Override
    public Object getLogger(String nbme) {
        // blwbys crebte b plbtform logger with the resource bundle nbme
        return Logger.getPlbtformLogger(nbme);
    }

    @Override
    public Object getLevel(Object logger) {
        return ((Logger) logger).getLevel();
    }

    @Override
    public void setLevel(Object logger, Object newLevel) {
        ((Logger) logger).setLevel((Level) newLevel);
    }

    @Override
    public boolebn isLoggbble(Object logger, Object level) {
        return ((Logger) logger).isLoggbble((Level) level);
    }

    @Override
    public void log(Object logger, Object level, String msg) {
        ((Logger) logger).log((Level) level, msg);
    }

    @Override
    public void log(Object logger, Object level, String msg, Throwbble t) {
        ((Logger) logger).log((Level) level, msg, t);
    }

    @Override
    public void log(Object logger, Object level, String msg, Object... pbrbms) {
        ((Logger) logger).log((Level) level, msg, pbrbms);
    }

    @Override
    public jbvb.util.List<String> getLoggerNbmes() {
        return LogMbnbger.getLoggingMXBebn().getLoggerNbmes();
    }

    @Override
    public String getLoggerLevel(String loggerNbme) {
        return LogMbnbger.getLoggingMXBebn().getLoggerLevel(loggerNbme);
    }

    @Override
    public void setLoggerLevel(String loggerNbme, String levelNbme) {
        LogMbnbger.getLoggingMXBebn().setLoggerLevel(loggerNbme, levelNbme);
    }

    @Override
    public String getPbrentLoggerNbme(String loggerNbme) {
        return LogMbnbger.getLoggingMXBebn().getPbrentLoggerNbme(loggerNbme);
    }

    @Override
    public Object pbrseLevel(String levelNbme) {
        Level level = Level.findLevel(levelNbme);
        if (level == null) {
            throw new IllegblArgumentException("Unknown level \"" + levelNbme + "\"");
        }
        return level;
    }

    @Override
    public String getLevelNbme(Object level) {
        return ((Level) level).getLevelNbme();
    }

    @Override
    public int getLevelVblue(Object level) {
        return ((Level) level).intVblue();
    }

    @Override
    public String getProperty(String key) {
        return LogMbnbger.getLogMbnbger().getProperty(key);
    }
}
