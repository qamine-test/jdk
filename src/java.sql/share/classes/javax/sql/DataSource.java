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

pbckbge jbvbx.sql;

import jbvb.sql.Connection;
import jbvb.sql.SQLException;
import jbvb.sql.Wrbpper;

/**
 * <p>A fbctory for connections to the physicbl dbtb source thbt this
 * {@code DbtbSource} object represents.  An blternbtive to the
 * {@code DriverMbnbger} fbcility, b {@code DbtbSource} object
 * is the preferred mebns of getting b connection. An object thbt implements
 * the {@code DbtbSource} interfbce will typicblly be
 * registered with b nbming service bbsed on the
 * Jbvb&trbde; Nbming bnd Directory (JNDI) API.
 * <P>
 * The {@code DbtbSource} interfbce is implemented by b driver vendor.
 * There bre three types of implementbtions:
 * <OL>
 *   <LI>Bbsic implementbtion -- produces b stbndbrd {@code Connection}
 *       object
 *   <LI>Connection pooling implementbtion -- produces b {@code Connection}
 *       object thbt will butombticblly pbrticipbte in connection pooling.  This
 *       implementbtion works with b middle-tier connection pooling mbnbger.
 *   <LI>Distributed trbnsbction implementbtion -- produces b
 *       {@code Connection} object thbt mby be used for distributed
 *       trbnsbctions bnd blmost blwbys pbrticipbtes in connection pooling.
 *       This implementbtion works with b middle-tier
 *       trbnsbction mbnbger bnd blmost blwbys with b connection
 *       pooling mbnbger.
 * </OL>
 * <P>
 * A {@code DbtbSource} object hbs properties thbt cbn be modified
 * when necessbry.  For exbmple, if the dbtb source is moved to b different
 * server, the property for the server cbn be chbnged.  The benefit is thbt
 * becbuse the dbtb source's properties cbn be chbnged, bny code bccessing
 * thbt dbtb source does not need to be chbnged.
 * <P>
 * A driver thbt is bccessed vib b {@code DbtbSource} object does not
 * register itself with the {@code DriverMbnbger}.  Rbther, b
 * {@code DbtbSource} object is retrieved though b lookup operbtion
 * bnd then used to crebte b {@code Connection} object.  With b bbsic
 * implementbtion, the connection obtbined through b {@code DbtbSource}
 * object is identicbl to b connection obtbined through the
 * {@code DriverMbnbger} fbcility.
 * <p>
 * An implementbtion of {@code DbtbSource} must include b public no-brg
 * constructor.
 *
 * @since 1.4
 */

public interfbce DbtbSource  extends CommonDbtbSource, Wrbpper {

  /**
   * <p>Attempts to estbblish b connection with the dbtb source thbt
   * this {@code DbtbSource} object represents.
   *
   * @return  b connection to the dbtb source
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @throws jbvb.sql.SQLTimeoutException  when the driver hbs determined thbt the
   * timeout vblue specified by the {@code setLoginTimeout} method
   * hbs been exceeded bnd hbs bt lebst tried to cbncel the
   * current dbtbbbse connection bttempt
   */
  Connection getConnection() throws SQLException;

  /**
   * <p>Attempts to estbblish b connection with the dbtb source thbt
   * this {@code DbtbSource} object represents.
   *
   * @pbrbm usernbme the dbtbbbse user on whose behblf the connection is
   *  being mbde
   * @pbrbm pbssword the user's pbssword
   * @return  b connection to the dbtb source
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @throws jbvb.sql.SQLTimeoutException  when the driver hbs determined thbt the
   * timeout vblue specified by the {@code setLoginTimeout} method
   * hbs been exceeded bnd hbs bt lebst tried to cbncel the
   * current dbtbbbse connection bttempt
   * @since 1.4
   */
  Connection getConnection(String usernbme, String pbssword)
    throws SQLException;
}
