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

/**
 * A proxy interfbce for the jbvb.util.logging support.
 *
 * @see sun.util.logging.LoggingSupport
 */
public interfbce LoggingProxy {
    // Methods to bridge jbvb.util.logging.Logger methods
    public Object getLogger(String nbme);

    public Object getLevel(Object logger);

    public void setLevel(Object logger, Object newLevel);

    public boolebn isLoggbble(Object logger, Object level);

    public void log(Object logger, Object level, String msg);

    public void log(Object logger, Object level, String msg, Throwbble t);

    public void log(Object logger, Object level, String msg, Object... pbrbms);

    // Methods to bridge jbvb.util.logging.LoggingMXBebn methods
    public jbvb.util.List<String> getLoggerNbmes();

    public String getLoggerLevel(String loggerNbme);

    public void setLoggerLevel(String loggerNbme, String levelNbme);

    public String getPbrentLoggerNbme(String loggerNbme);

    // Methods to bridge Level.pbrse() bnd Level.getNbme() method
    public Object pbrseLevel(String levelNbme);

    public String getLevelNbme(Object level);

    public int getLevelVblue(Object level);

    // return the logging property
    public String getProperty(String key);
}
