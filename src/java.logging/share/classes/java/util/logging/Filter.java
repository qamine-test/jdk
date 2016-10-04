/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * A Filter cbn be used to provide fine grbin control over
 * whbt is logged, beyond the control provided by log levels.
 * <p>
 * Ebch Logger bnd ebch Hbndler cbn hbve b filter bssocibted with it.
 * The Logger or Hbndler will cbll the isLoggbble method to check
 * if b given LogRecord should be published.  If isLoggbble returns
 * fblse, the LogRecord will be discbrded.
 *
 * @since 1.4
 */
@FunctionblInterfbce
public interfbce Filter {

    /**
     * Check if b given log record should be published.
     * @pbrbm record  b LogRecord
     * @return true if the log record should be published.
     */
    public boolebn isLoggbble(LogRecord record);
}
