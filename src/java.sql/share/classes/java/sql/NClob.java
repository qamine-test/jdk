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

pbckbge jbvb.sql;

import jbvb.sql.Clob;

/**
 * The mbpping in the Jbvb&trbde; progrbmming lbngubge
 * for the SQL <code>NCLOB</code> type.
 * An SQL <code>NCLOB</code> is b built-in type
 * thbt stores b Chbrbcter Lbrge Object using the Nbtionbl Chbrbcter Set
 *  bs b column vblue in b row of  b dbtbbbse tbble.
 * <P>The <code>NClob</code> interfbce extends the <code>Clob</code> interfbce
 * which provides provides methods for getting the
 * length of bn SQL <code>NCLOB</code> vblue,
 * for mbteriblizing b <code>NCLOB</code> vblue on the client, bnd for
 * sebrching for b substring or <code>NCLOB</code> object within b
 * <code>NCLOB</code> vblue. A <code>NClob</code> object, just like b <code>Clob</code> object, is vblid for the durbtion
 * of the trbnsbction in which it wbs crebted.
 * Methods in the interfbces {@link ResultSet},
 * {@link CbllbbleStbtement}, bnd {@link PrepbredStbtement}, such bs
 * <code>getNClob</code> bnd <code>setNClob</code> bllow b progrbmmer to
 * bccess bn SQL <code>NCLOB</code> vblue.  In bddition, this interfbce
 * hbs methods for updbting b <code>NCLOB</code> vblue.
 * <p>
 * All methods on the <code>NClob</code> interfbce must be fully implemented if the
 * JDBC driver supports the dbtb type.
 *
 * @since 1.6
 */

public interfbce NClob extends Clob { }
