/*
 * Copyright (c) 2000, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * <P>
 * An object thbt registers to be notified of events generbted by b
 * <code>PooledConnection</code> object.
 * <P>
 * The <code>ConnectionEventListener</code> interfbce is implemented by b
 * connection pooling component.  A connection pooling component will
 * usublly be provided by b JDBC driver vendor or bnother system softwbre
 * vendor.  A JDBC driver notifies b <code>ConnectionEventListener</code>
 * object when bn bpplicbtion is finished using b pooled connection with
 * which the listener hbs registered.  The notificbtion
 * occurs bfter the bpplicbtion cblls the method <code>close</code> on
 * its representbtion of b <code>PooledConnection</code> object.  A
 * <code>ConnectionEventListener</code> is blso notified when b
 * connection error occurs due to the fbct thbt the <code>PooledConnection</code>
 * is unfit for future use---the server hbs crbshed, for exbmple.
 * The listener is notified by the JDBC driver just before the driver throws bn
 * <code>SQLException</code> to the bpplicbtion using the
 * <code>PooledConnection</code> object.
 *
 * @since 1.4
 */

public interfbce ConnectionEventListener extends jbvb.util.EventListener {

  /**
   * Notifies this <code>ConnectionEventListener</code> thbt
   * the bpplicbtion hbs cblled the method <code>close</code> on its
   * representbtion of b pooled connection.
   *
   * @pbrbm event bn event object describing the source of
   * the event
   */
  void connectionClosed(ConnectionEvent event);

  /**
   * Notifies this <code>ConnectionEventListener</code> thbt
   * b fbtbl error hbs occurred bnd the pooled connection cbn
   * no longer be used.  The driver mbkes this notificbtion just
   * before it throws the bpplicbtion the <code>SQLException</code>
   * contbined in the given <code>ConnectionEvent</code> object.
   *
   * @pbrbm event bn event object describing the source of
   * the event bnd contbining the <code>SQLException</code> thbt the
   * driver is bbout to throw
   */
  void connectionErrorOccurred(ConnectionEvent event);

 }
