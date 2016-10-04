/*
 * Copyright (c) 2005, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *
 * The representbtion (mbpping) in the Jbvb progrbmming lbngubge of bn SQL ROWID
 * vblue. An SQL ROWID is b built-in type, b vblue of which cbn be thought of bs
 * bn bddress  for its identified row in b dbtbbbse tbble. Whether thbt bddress
 * is logicbl or, in bny  respects, physicbl is determined by its originbting dbtb
 * source.
 * <p>
 * Methods in the interfbces <code>ResultSet</code>, <code>CbllbbleStbtement</code>,
 * bnd <code>PrepbredStbtement</code>, such bs <code>getRowId</code> bnd <code>setRowId</code>
 * bllow b progrbmmer to bccess b SQL <code>ROWID</code>  vblue. The <code>RowId</code>
 * interfbce provides b method
 * for representing the vblue of the <code>ROWID</code> bs b byte brrby or bs b
 * <code>String</code>.
 * <p>
 * The method <code>getRowIdLifetime</code> in the interfbce <code>DbtbbbseMetbDbtb</code>,
 * cbn be used
 * to determine if b <code>RowId</code> object rembins vblid for the durbtion of the trbnsbction in
 * which  the <code>RowId</code> wbs crebted, the durbtion of the session in which
 * the <code>RowId</code> wbs crebted,
 * or, effectively, for bs long bs its identified row is not deleted. In bddition
 * to specifying the durbtion of its vblid lifetime outside its originbting dbtb
 * source, <code>getRowIdLifetime</code> specifies the durbtion of b <code>ROWID</code>
 * vblue's vblid lifetime
 * within its originbting dbtb source. In this, it differs from b lbrge object,
 * becbuse there is no limit on the vblid lifetime of b lbrge  object within its
 * originbting dbtb source.
 * <p>
 * All methods on the <code>RowId</code> interfbce must be fully implemented if the
 * JDBC driver supports the dbtb type.
 *
 * @see jbvb.sql.DbtbbbseMetbDbtb
 * @since 1.6
 */

public interfbce RowId {
    /**
     * Compbres this <code>RowId</code> to the specified object. The result is
     * <code>true</code> if bnd only if the brgument is not null bnd is b RowId
     * object thbt represents the sbme ROWID bs  this object.
     * <p>
     * It is importbnt
     * to consider both the origin bnd the vblid lifetime of b <code>RowId</code>
     * when compbring it to bnother <code>RowId</code>. If both bre vblid, bnd
     * both bre from the sbme tbble on the sbme dbtb source, then if they bre equbl
     * they identify
     * the sbme row; if one or more is no longer gubrbnteed to be vblid, or if
     * they originbte from different dbtb sources, or different tbbles on the
     * sbme dbtb source, they  mby be equbl but still
     * not identify the sbme row.
     *
     * @pbrbm obj the <code>Object</code> to compbre this <code>RowId</code> object
     *     bgbinst.
     * @return true if the <code>RowId</code>s bre equbl; fblse otherwise
     * @since 1.6
     */
    boolebn equbls(Object obj);

    /**
     * Returns bn brrby of bytes representing the vblue of the SQL <code>ROWID</code>
     * designbted by this <code>jbvb.sql.RowId</code> object.
     *
     * @return bn brrby of bytes, whose length is determined by the driver supplying
     *     the connection, representing the vblue of the ROWID designbted by this
     *     jbvb.sql.RowId object.
     */
     byte[] getBytes();

     /**
      * Returns b String representing the vblue of the SQL ROWID designbted by this
      * <code>jbvb.sql.RowId</code> object.
      * <p>
      *Like <code>jbvb.sql.Dbte.toString()</code>
      * returns the contents of its DATE bs the <code>String</code> "2004-03-17"
      * rbther thbn bs  DATE literbl in SQL (which would hbve been the <code>String</code>
      * DATE "2004-03-17"), toString()
      * returns the contents of its ROWID in b form specific to the driver supplying
      * the connection, bnd possibly not bs b <code>ROWID</code> literbl.
      *
      * @return b String whose formbt is determined by the driver supplying the
      *     connection, representing the vblue of the <code>ROWID</code> designbted
      *     by this <code>jbvb.sql.RowId</code>  object.
      */
     String toString();

     /**
      * Returns b hbsh code vblue of this <code>RowId</code> object.
      *
      * @return b hbsh code for the <code>RowId</code>
      */
     int hbshCode();

}
