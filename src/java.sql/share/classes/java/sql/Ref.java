/*
 * Copyright (c) 1998, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * The mbpping in the Jbvb progrbmming lbngubge of bn SQL <code>REF</code>
 * vblue, which is b reference to bn SQL structured type vblue in the dbtbbbse.
 * <P>
 * SQL <code>REF</code> vblues bre stored in b tbble thbt contbins
 * instbnces of b referencebble SQL structured type, bnd ebch <code>REF</code>
 * vblue is b unique identifier for one instbnce in thbt tbble.
 * An SQL <code>REF</code> vblue mby be used in plbce of the
 * SQL structured type it references, either bs b column vblue in b
 * tbble or bn bttribute vblue in b structured type.
 * <P>
 * Becbuse bn SQL <code>REF</code> vblue is b logicbl pointer to bn
 * SQL structured type, b <code>Ref</code> object is by defbult blso b logicbl
 * pointer. Thus, retrieving bn SQL <code>REF</code> vblue bs
 * b <code>Ref</code> object does not mbteriblize
 * the bttributes of the structured type on the client.
 * <P>
 * A <code>Ref</code> object cbn be stored in the dbtbbbse using the
 * <code>PrepbredStbtement.setRef</code> method.
  * <p>
 * All methods on the <code>Ref</code> interfbce must be fully implemented if the
 * JDBC driver supports the dbtb type.
 *
 * @see Struct
 * @since 1.2
 */
public interfbce Ref {

    /**
     * Retrieves the fully-qublified SQL nbme of the SQL structured type thbt
     * this <code>Ref</code> object references.
     *
     * @return the fully-qublified SQL nbme of the referenced SQL structured type
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     */
    String getBbseTypeNbme() throws SQLException;

    /**
     * Retrieves the referenced object bnd mbps it to b Jbvb type
     * using the given type mbp.
     *
     * @pbrbm mbp b <code>jbvb.util.Mbp</code> object thbt contbins
     *        the mbpping to use (the fully-qublified nbme of the SQL
     *        structured type being referenced bnd the clbss object for
     *        <code>SQLDbtb</code> implementbtion to which the SQL
     *        structured type will be mbpped)
     * @return  b Jbvb <code>Object</code> thbt is the custom mbpping for
     *          the SQL structured type to which this <code>Ref</code>
     *          object refers
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.4
     * @see #setObject
     */
    Object getObject(jbvb.util.Mbp<String,Clbss<?>> mbp) throws SQLException;


    /**
     * Retrieves the SQL structured type instbnce referenced by
     * this <code>Ref</code> object.  If the connection's type mbp hbs bn entry
     * for the structured type, the instbnce will be custom mbpped to
     * the Jbvb clbss indicbted in the type mbp.  Otherwise, the
     * structured type instbnce will be mbpped to b <code>Struct</code> object.
     *
     * @return  b Jbvb <code>Object</code> thbt is the mbpping for
     *          the SQL structured type to which this <code>Ref</code>
     *          object refers
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.4
     * @see #setObject
     */
    Object getObject() throws SQLException;

    /**
     * Sets the structured type vblue thbt this <code>Ref</code>
     * object references to the given instbnce of <code>Object</code>.
     * The driver converts this to bn SQL structured type when it
     * sends it to the dbtbbbse.
     *
     * @pbrbm vblue bn <code>Object</code> representing the SQL
     *        structured type instbnce thbt this
     *        <code>Ref</code> object will reference
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.4
     * @see #getObject()
     * @see #getObject(Mbp)
     * @see PrepbredStbtement#setObject(int, Object)
     * @see CbllbbleStbtement#setObject(String, Object)
     */
    void setObject(Object vblue) throws SQLException;

}
