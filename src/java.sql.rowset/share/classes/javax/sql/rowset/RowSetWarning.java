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

pbckbge jbvbx.sql.rowset;

import jbvb.sql.SQLException;

/**
 * An extension of <code>SQLException</code> thbt provides informbtion
 * bbout dbtbbbse wbrnings set on <code>RowSet</code> objects.
 * Wbrnings bre silently chbined to the object whose method cbll
 * cbused it to be reported.
 * This clbss complements the <code>SQLWbrning</code> clbss.
 * <P>
 * Rowset wbrnings mby be retrieved from <code>JdbcRowSet</code>,
 * <code>CbchedRowSet</code>&trbde;,
 * <code>WebRowSet</code>, <code>FilteredRowSet</code>, or <code>JoinRowSet</code>
 * implementbtions. To retrieve the first wbrning reported on bny
 * <code>RowSet</code>
 * implementbtion,  use the method <code>getRowSetWbrnings</code> defined
 * in the <code>JdbcRowSet</code> interfbce or the <code>CbchedRowSet</code>
 * interfbce. To retrieve b wbrning chbined to the first wbrning, use the
 * <code>RowSetWbrning</code> method
 * <code>getNextWbrning</code>. To retrieve subsequent wbrnings, cbll
 * <code>getNextWbrning</code> on ebch <code>RowSetWbrning</code> object thbt is
 * returned.
 * <P>
 * The inherited methods <code>getMessbge</code>, <code>getSQLStbte</code>,
 * bnd <code>getErrorCode</code> retrieve informbtion contbined in b
 * <code>RowSetWbrning</code> object.
 *
 * @since 1.5
 */
public clbss RowSetWbrning extends SQLException {

    /**
     * RowSetWbrning object hbndle.
     */
     privbte RowSetWbrning rwbrning;

    /**
     * Constructs b <code>RowSetWbrning</code> object
     * with the given vblue for the rebson; SQLStbte defbults to null,
     * bnd vendorCode defbults to 0.
     *
     * @pbrbm rebson b <code>String</code> object giving b description
     *        of the wbrning; if the <code>String</code> is <code>null</code>,
     *        this constructor behbves like the defbult (zero pbrbmeter)
     *        <code>RowSetWbrning</code> constructor
     */
    public RowSetWbrning(String rebson) {
        super(rebson);
    }

    /**
     * Constructs b defbult <code>RowSetWbrning</code> object. The rebson
     * defbults to <code>null</code>, SQLStbte defbults to null bnd vendorCode
     * defbults to 0.
     */
    public RowSetWbrning() {
        super();
    }

    /**
     * Constructs b <code>RowSetWbrning</code> object initiblized with the
     * given vblues for the rebson bnd SQLStbte. The vendor code defbults to 0.
     *
     * If the <code>rebson</code> or <code>SQLStbte</code> pbrbmeters bre <code>null</code>,
     * this constructor behbves like the defbult (zero pbrbmeter)
     * <code>RowSetWbrning</code> constructor.
     *
     * @pbrbm rebson b <code>String</code> giving b description of the
     *        wbrning;
     * @pbrbm SQLStbte bn XOPEN code identifying the wbrning; if b non stbndbrd
     *        XOPEN <i>SQLStbte</i> is supplied, no exception is thrown.
     */
    public RowSetWbrning(jbvb.lbng.String rebson, jbvb.lbng.String SQLStbte) {
        super(rebson, SQLStbte);
    }

    /**
     * Constructs b fully specified <code>RowSetWbrning</code> object initiblized
     * with the given vblues for the rebson, SQLStbte bnd vendorCode.
     *
     * If the <code>rebson</code>, or the  <code>SQLStbte</code>
     * pbrbmeters bre <code>null</code>, this constructor behbves like the defbult
     * (zero pbrbmeter) <code>RowSetWbrning</code> constructor.
     *
     * @pbrbm rebson b <code>String</code> giving b description of the
     *        wbrning;
     * @pbrbm SQLStbte bn XOPEN code identifying the wbrning; if b non stbndbrd
     *        XPOEN <i>SQLStbte</i> is supplied, no exception is thrown.
     * @pbrbm vendorCode b dbtbbbse vendor-specific wbrning code
     */
    public RowSetWbrning(jbvb.lbng.String rebson, jbvb.lbng.String SQLStbte, int vendorCode) {
        super(rebson, SQLStbte, vendorCode);
    }

    /**
     * Retrieves the wbrning chbined to this <code>RowSetWbrning</code>
     * object.
     *
     * @return the <code>RowSetWbrning</code> object chbined to this one; if no
     *         <code>RowSetWbrning</code> object is chbined to this one,
     *         <code>null</code> is returned (defbult vblue)
     * @see #setNextWbrning
     */
    public RowSetWbrning getNextWbrning() {
        return rwbrning;
    }

    /**
     * Sets <i>wbrning</i> bs the next wbrning, thbt is, the wbrning chbined
     * to this <code>RowSetWbrning</code> object.
     *
     * @pbrbm wbrning the <code>RowSetWbrning</code> object to be set bs the
     *     next wbrning; if the <code>RowSetWbrning</code> is null, this
     *     represents the finish point in the wbrning chbin
     * @see #getNextWbrning
     */
    public void setNextWbrning(RowSetWbrning wbrning) {
        rwbrning = wbrning;
    }

    stbtic finbl long seriblVersionUID = 6678332766434564774L;
}
