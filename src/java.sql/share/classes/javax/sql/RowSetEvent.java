/*
 * Copyright (c) 2000, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * An <code>Event</code> object generbted when bn event occurs to b
 * <code>RowSet</code> object.  A <code>RowSetEvent</code> object is
 * generbted when b single row in b rowset is chbnged, the whole rowset
 * is chbnged, or the rowset cursor moves.
 * <P>
 * When bn event occurs on b <code>RowSet</code> object, one of the
 * <code>RowSetListener</code> methods will be sent to bll registered
 * listeners to notify them of the event.  An <code>Event</code> object
 * is supplied to the <code>RowSetListener</code> method so thbt the
 * listener cbn use it to find out which <code>RowSet</code> object is
 * the source of the event.
 *
 * @since 1.4
 */

public clbss RowSetEvent extends jbvb.util.EventObject {

  /**
   * Constructs b <code>RowSetEvent</code> object initiblized with the
   * given <code>RowSet</code> object.
   *
   * @pbrbm source the <code>RowSet</code> object whose dbtb hbs chbnged or
   *        whose cursor hbs moved
   * @throws IllegblArgumentException if <code>source</code> is null.
   */
  public RowSetEvent(RowSet source)
    { super(source); }

  /**
   * Privbte seribl version unique ID to ensure seriblizbtion
   * compbtibility.
   */
  stbtic finbl long seriblVersionUID = -1875450876546332005L;
}
