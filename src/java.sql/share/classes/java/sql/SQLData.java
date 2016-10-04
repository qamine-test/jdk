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
 * The interfbce used for the custom mbpping of bn SQL user-defined type (UDT) to
 * b clbss in the Jbvb progrbmming lbngubge. The clbss object for b clbss
 * implementing the <code>SQLDbtb</code> interfbce will be entered in the
 * bppropribte <code>Connection</code> object's type mbp blong with the SQL
 * nbme of the UDT for which it is b custom mbpping.
 * <P>
 * Typicblly, b <code>SQLDbtb</code> implementbtion
 * will define b field for ebch bttribute of bn SQL structured type or b
 * single field for bn SQL <code>DISTINCT</code> type. When the UDT is
 * retrieved from b dbtb source with the <code>ResultSet.getObject</code>
 * method, it will be mbpped bs bn instbnce of this clbss.  A progrbmmer
 * cbn operbte on this clbss instbnce just bs on bny other object in the
 * Jbvb progrbmming lbngubge bnd then store bny chbnges mbde to it by
 * cblling the <code>PrepbredStbtement.setObject</code> method,
 * which will mbp it bbck to the SQL type.
 * <p>
 * It is expected thbt the implementbtion of the clbss for b custom
 * mbpping will be done by b tool.  In b typicbl implementbtion, the
 * progrbmmer would simply supply the nbme of the SQL UDT, the nbme of
 * the clbss to which it is being mbpped, bnd the nbmes of the fields to
 * which ebch of the bttributes of the UDT is to be mbpped.  The tool will use
 * this informbtion to implement the <code>SQLDbtb.rebdSQL</code> bnd
 * <code>SQLDbtb.writeSQL</code> methods.  The <code>rebdSQL</code> method
 * cblls the bppropribte <code>SQLInput</code> methods to rebd
 * ebch bttribute from bn <code>SQLInput</code> object, bnd the
 * <code>writeSQL</code> method cblls <code>SQLOutput</code> methods
 * to write ebch bttribute bbck to the dbtb source vib bn
 * <code>SQLOutput</code> object.
 * <P>
 * An bpplicbtion progrbmmer will not normblly cbll <code>SQLDbtb</code> methods
 * directly, bnd the <code>SQLInput</code> bnd <code>SQLOutput</code> methods
 * bre cblled internblly by <code>SQLDbtb</code> methods, not by bpplicbtion code.
 *
 * @since 1.2
 */
public interfbce SQLDbtb {

 /**
  * Returns the fully-qublified
  * nbme of the SQL user-defined type thbt this object represents.
  * This method is cblled by the JDBC driver to get the nbme of the
  * UDT instbnce thbt is being mbpped to this instbnce of
  * <code>SQLDbtb</code>.
  *
  * @return the type nbme thbt wbs pbssed to the method <code>rebdSQL</code>
  *            when this object wbs constructed bnd populbted
  * @exception SQLException if there is b dbtbbbse bccess error
  * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
  * this method
  * @since 1.2
  */
  String getSQLTypeNbme() throws SQLException;

 /**
  * Populbtes this object with dbtb rebd from the dbtbbbse.
  * The implementbtion of the method must follow this protocol:
  * <UL>
  * <LI>It must rebd ebch of the bttributes or elements of the SQL
  * type  from the given input strebm.  This is done
  * by cblling b method of the input strebm to rebd ebch
  * item, in the order thbt they bppebr in the SQL definition
  * of the type.
  * <LI>The method <code>rebdSQL</code> then
  * bssigns the dbtb to bppropribte fields or
  * elements (of this or other objects).
  * Specificblly, it must cbll the bppropribte <i>rebder</i> method
  * (<code>SQLInput.rebdString</code>, <code>SQLInput.rebdBigDecimbl</code>,
  * bnd so on) method(s) to do the following:
  * for b distinct type, rebd its single dbtb element;
  * for b structured type, rebd b vblue for ebch bttribute of the SQL type.
  * </UL>
  * The JDBC driver initiblizes the input strebm with b type mbp
  * before cblling this method, which is used by the bppropribte
  * <code>SQLInput</code> rebder method on the strebm.
  *
  * @pbrbm strebm the <code>SQLInput</code> object from which to rebd the dbtb for
  * the vblue thbt is being custom mbpped
  * @pbrbm typeNbme the SQL type nbme of the vblue on the dbtb strebm
  * @exception SQLException if there is b dbtbbbse bccess error
  * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
  * this method
  * @see SQLInput
  * @since 1.2
  */
  void rebdSQL (SQLInput strebm, String typeNbme) throws SQLException;

  /**
  * Writes this object to the given SQL dbtb strebm, converting it bbck to
  * its SQL vblue in the dbtb source.
  * The implementbtion of the method must follow this protocol:<BR>
  * It must write ebch of the bttributes of the SQL type
  * to the given output strebm.  This is done by cblling b
  * method of the output strebm to write ebch item, in the order thbt
  * they bppebr in the SQL definition of the type.
  * Specificblly, it must cbll the bppropribte <code>SQLOutput</code> writer
  * method(s) (<code>writeInt</code>, <code>writeString</code>, bnd so on)
  * to do the following: for b Distinct Type, write its single dbtb element;
  * for b Structured Type, write b vblue for ebch bttribute of the SQL type.
  *
  * @pbrbm strebm the <code>SQLOutput</code> object to which to write the dbtb for
  * the vblue thbt wbs custom mbpped
  * @exception SQLException if there is b dbtbbbse bccess error
  * @exception SQLFebtureNotSupportedException if the JDBC driver does not support
  * this method
  * @see SQLOutput
  * @since 1.2
  */
  void writeSQL (SQLOutput strebm) throws SQLException;
}
