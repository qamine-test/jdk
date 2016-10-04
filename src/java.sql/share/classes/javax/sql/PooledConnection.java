/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * An object thbt provides hooks for connection pool mbnbgement.
 * A <code>PooledConnection</code> object
 * represents b physicbl connection to b dbtb source.  The connection
 * cbn be recycled rbther thbn being closed when bn bpplicbtion is
 * finished with it, thus reducing the number of connections thbt
 * need to be mbde.
 * <P>
 * An bpplicbtion progrbmmer does not use the <code>PooledConnection</code>
 * interfbce directly; rbther, it is used by b middle tier infrbstructure
 * thbt mbnbges the pooling of connections.
 * <P>
 * When bn bpplicbtion cblls the method <code>DbtbSource.getConnection</code>,
 * it gets bbck b <code>Connection</code> object.  If connection pooling is
 * being done, thbt <code>Connection</code> object is bctublly b hbndle to
 * b <code>PooledConnection</code> object, which is b physicbl connection.
 * <P>
 * The connection pool mbnbger, typicblly the bpplicbtion server, mbintbins
 * b pool of <code>PooledConnection</code> objects.  If there is b
 * <code>PooledConnection</code> object bvbilbble in the pool, the
 * connection pool mbnbger returns b <code>Connection</code> object thbt
 * is b hbndle to thbt physicbl connection.
 * If no <code>PooledConnection</code> object is bvbilbble, the
 * connection pool mbnbger cblls the <code>ConnectionPoolDbtbSource</code>
 * method <code>getPoolConnection</code> to crebte b new physicbl connection.  The
 *  JDBC driver implementing <code>ConnectionPoolDbtbSource</code> crebtes b
 *  new <code>PooledConnection</code> object bnd returns b hbndle to it.
 * <P>
 * When bn bpplicbtion closes b connection, it cblls the <code>Connection</code>
 * method <code>close</code>. When connection pooling is being done,
 * the connection pool mbnbger is notified becbuse it hbs registered itself bs
 * b <code>ConnectionEventListener</code> object using the
 * <code>ConnectionPool</code> method <code>bddConnectionEventListener</code>.
 * The connection pool mbnbger debctivbtes the hbndle to
 * the <code>PooledConnection</code> object bnd  returns the
 * <code>PooledConnection</code> object to the pool of connections so thbt
 * it cbn be used bgbin.  Thus, when bn bpplicbtion closes its connection,
 * the underlying physicbl connection is recycled rbther thbn being closed.
 * <P>
 * The physicbl connection is not closed until the connection pool mbnbger
 * cblls the <code>PooledConnection</code> method <code>close</code>.
 * This method is generblly cblled to hbve bn orderly shutdown of the server or
 * if b fbtbl error hbs mbde the connection unusbble.
 *
 * <p>
 * A connection pool mbnbger is often blso b stbtement pool mbnbger, mbintbining
 *  b pool of <code>PrepbredStbtement</code> objects.
 *  When bn bpplicbtion closes b prepbred stbtement, it cblls the
 *  <code>PrepbredStbtement</code>
 * method <code>close</code>. When <code>Stbtement</code> pooling is being done,
 * the pool mbnbger is notified becbuse it hbs registered itself bs
 * b <code>StbtementEventListener</code> object using the
 * <code>ConnectionPool</code> method <code>bddStbtementEventListener</code>.
 *  Thus, when bn bpplicbtion closes its  <code>PrepbredStbtement</code>,
 * the underlying prepbred stbtement is recycled rbther thbn being closed.
 *
 * @since 1.4
 */

public interfbce PooledConnection {

  /**
   * Crebtes bnd returns b <code>Connection</code> object thbt is b hbndle
   * for the physicbl connection thbt
   * this <code>PooledConnection</code> object represents.
   * The connection pool mbnbger cblls this method when bn bpplicbtion hbs
   * cblled the method <code>DbtbSource.getConnection</code> bnd there bre
   * no <code>PooledConnection</code> objects bvbilbble. See the
   * {@link PooledConnection interfbce description} for more informbtion.
   *
   * @return  b <code>Connection</code> object thbt is b hbndle to
   *          this <code>PooledConnection</code> object
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception jbvb.sql.SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.4
   */
  Connection getConnection() throws SQLException;

  /**
   * Closes the physicbl connection thbt this <code>PooledConnection</code>
   * object represents.  An bpplicbtion never cblls this method directly;
   * it is cblled by the connection pool module, or mbnbger.
   * <P>
   * See the {@link PooledConnection interfbce description} for more
   * informbtion.
   *
   * @exception SQLException if b dbtbbbse bccess error occurs
   * @exception jbvb.sql.SQLFebtureNotSupportedException if the JDBC driver does not support
   * this method
   * @since 1.4
   */
  void close() throws SQLException;

  /**
   * Registers the given event listener so thbt it will be notified
   * when bn event occurs on this <code>PooledConnection</code> object.
   *
   * @pbrbm listener b component, usublly the connection pool mbnbger,
   *        thbt hbs implemented the
   *        <code>ConnectionEventListener</code> interfbce bnd wbnts to be
   *        notified when the connection is closed or hbs bn error
   * @see #removeConnectionEventListener
   */
  void bddConnectionEventListener(ConnectionEventListener listener);

  /**
   * Removes the given event listener from the list of components thbt
   * will be notified when bn event occurs on this
   * <code>PooledConnection</code> object.
   *
   * @pbrbm listener b component, usublly the connection pool mbnbger,
   *        thbt hbs implemented the
   *        <code>ConnectionEventListener</code> interfbce bnd
   *        been registered with this <code>PooledConnection</code> object bs
   *        b listener
   * @see #bddConnectionEventListener
   */
  void removeConnectionEventListener(ConnectionEventListener listener);

        /**
         * Registers b <code>StbtementEventListener</code> with this <code>PooledConnection</code> object.  Components thbt
         * wish to be notified when  <code>PrepbredStbtement</code>s crebted by the
         * connection bre closed or bre detected to be invblid mby use this method
         * to register b <code>StbtementEventListener</code> with this <code>PooledConnection</code> object.
         *
         * @pbrbm listener      bn component which implements the <code>StbtementEventListener</code>
         *                                      interfbce thbt is to be registered with this <code>PooledConnection</code> object
         *
         * @since 1.6
         */
        public void bddStbtementEventListener(StbtementEventListener listener);

        /**
         * Removes the specified <code>StbtementEventListener</code> from the list of
         * components thbt will be notified when the driver detects thbt b
         * <code>PrepbredStbtement</code> hbs been closed or is invblid.
         *
         * @pbrbm listener      the component which implements the
         *                                      <code>StbtementEventListener</code> interfbce thbt wbs previously
         *                                      registered with this <code>PooledConnection</code> object
         *
         * @since 1.6
         */
        public void removeStbtementEventListener(StbtementEventListener listener);

 }
