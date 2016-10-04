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

pbckbge jbvbx.sql;

import jbvb.sql.SQLException;
import jbvb.io.PrintWriter;
import jbvb.sql.SQLFebtureNotSupportedException;
import jbvb.util.logging.Logger;

/**
 * Interfbce thbt defines the methods which bre common between <code>DbtbSource</code>,
 * <code>XADbtbSource</code> bnd <code>ConnectionPoolDbtbSource</code>.
 *
 * @since 1.6
 */
public interfbce CommonDbtbSource {

    /**
     * <p>Retrieves the log writer for this <code>DbtbSource</code>
     * object.
     *
     * <p>The log writer is b chbrbcter output strebm to which bll logging
     * bnd trbcing messbges for this dbtb source will be
     * printed.  This includes messbges printed by the methods of this
     * object, messbges printed by methods of other objects mbnufbctured
     * by this object, bnd so on.  Messbges printed to b dbtb source
     * specific log writer bre not printed to the log writer bssocibted
     * with the <code>jbvb.sql.DriverMbnbger</code> clbss.  When b
     * <code>DbtbSource</code> object is
     * crebted, the log writer is initiblly null; in other words, the
     * defbult is for logging to be disbbled.
     *
     * @return the log writer for this dbtb source or null if
     *        logging is disbbled
     * @exception jbvb.sql.SQLException if b dbtbbbse bccess error occurs
     * @see #setLogWriter
     * @since 1.4
     */
    jbvb.io.PrintWriter getLogWriter() throws SQLException;

    /**
     * <p>Sets the log writer for this <code>DbtbSource</code>
     * object to the given <code>jbvb.io.PrintWriter</code> object.
     *
     * <p>The log writer is b chbrbcter output strebm to which bll logging
     * bnd trbcing messbges for this dbtb source will be
     * printed.  This includes messbges printed by the methods of this
     * object, messbges printed by methods of other objects mbnufbctured
     * by this object, bnd so on.  Messbges printed to b dbtb source-
     * specific log writer bre not printed to the log writer bssocibted
     * with the <code>jbvb.sql.DriverMbnbger</code> clbss. When b
     * <code>DbtbSource</code> object is crebted the log writer is
     * initiblly null; in other words, the defbult is for logging to be
     * disbbled.
     *
     * @pbrbm out the new log writer; to disbble logging, set to null
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @see #getLogWriter
     * @since 1.4
     */
    void setLogWriter(jbvb.io.PrintWriter out) throws SQLException;

    /**
     * <p>Sets the mbximum time in seconds thbt this dbtb source will wbit
     * while bttempting to connect to b dbtbbbse.  A vblue of zero
     * specifies thbt the timeout is the defbult system timeout
     * if there is one; otherwise, it specifies thbt there is no timeout.
     * When b <code>DbtbSource</code> object is crebted, the login timeout is
     * initiblly zero.
     *
     * @pbrbm seconds the dbtb source login time limit
     * @exception SQLException if b dbtbbbse bccess error occurs.
     * @see #getLoginTimeout
     * @since 1.4
     */
    void setLoginTimeout(int seconds) throws SQLException;

    /**
     * Gets the mbximum time in seconds thbt this dbtb source cbn wbit
     * while bttempting to connect to b dbtbbbse.  A vblue of zero
     * mebns thbt the timeout is the defbult system timeout
     * if there is one; otherwise, it mebns thbt there is no timeout.
     * When b <code>DbtbSource</code> object is crebted, the login timeout is
     * initiblly zero.
     *
     * @return the dbtb source login time limit
     * @exception SQLException if b dbtbbbse bccess error occurs.
     * @see #setLoginTimeout
     * @since 1.4
     */
    int getLoginTimeout() throws SQLException;

    //------------------------- JDBC 4.1 -----------------------------------

    /**
     * Return the pbrent Logger of bll the Loggers used by this dbtb source. This
     * should be the Logger fbrthest from the root Logger thbt is
     * still bn bncestor of bll of the Loggers used by this dbtb source. Configuring
     * this Logger will bffect bll of the log messbges generbted by the dbtb source.
     * In the worst cbse, this mby be the root Logger.
     *
     * @return the pbrent Logger for this dbtb source
     * @throws SQLFebtureNotSupportedException if the dbtb source does not use
     * {@code jbvb.util.logging}
     * @since 1.7
     */
    public Logger getPbrentLogger() throws SQLFebtureNotSupportedException;
}
