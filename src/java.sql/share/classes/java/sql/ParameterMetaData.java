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

pbckbge jbvb.sql;

/**
 * An object thbt cbn be used to get informbtion bbout the types
 * bnd properties for ebch pbrbmeter mbrker in b
 * <code>PrepbredStbtement</code> object. For some queries bnd driver
 * implementbtions, the dbtb thbt would be returned by b <code>PbrbmeterMetbDbtb</code>
 * object mby not be bvbilbble until the <code>PrepbredStbtement</code> hbs
 * been executed.
 *<p>
 *Some driver implementbtions mby not be bble to provide informbtion bbout the
 *types bnd properties for ebch pbrbmeter mbrker in b <code>CbllbbleStbtement</code>
 *object.
 *
 * @since 1.4
 */

public interfbce PbrbmeterMetbDbtb extends Wrbpper {

    /**
     * Retrieves the number of pbrbmeters in the <code>PrepbredStbtement</code>
     * object for which this <code>PbrbmeterMetbDbtb</code> object contbins
     * informbtion.
     *
     * @return the number of pbrbmeters
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.4
     */
    int getPbrbmeterCount() throws SQLException;

    /**
     * Retrieves whether null vblues bre bllowed in the designbted pbrbmeter.
     *
     * @pbrbm pbrbm the first pbrbmeter is 1, the second is 2, ...
     * @return the nullbbility stbtus of the given pbrbmeter; one of
     *        <code>PbrbmeterMetbDbtb.pbrbmeterNoNulls</code>,
     *        <code>PbrbmeterMetbDbtb.pbrbmeterNullbble</code>, or
     *        <code>PbrbmeterMetbDbtb.pbrbmeterNullbbleUnknown</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.4
     */
    int isNullbble(int pbrbm) throws SQLException;

    /**
     * The constbnt indicbting thbt b
     * pbrbmeter will not bllow <code>NULL</code> vblues.
     */
    int pbrbmeterNoNulls = 0;

    /**
     * The constbnt indicbting thbt b
     * pbrbmeter will bllow <code>NULL</code> vblues.
     */
    int pbrbmeterNullbble = 1;

    /**
     * The constbnt indicbting thbt the
     * nullbbility of b pbrbmeter is unknown.
     */
    int pbrbmeterNullbbleUnknown = 2;

    /**
     * Retrieves whether vblues for the designbted pbrbmeter cbn be signed numbers.
     *
     * @pbrbm pbrbm the first pbrbmeter is 1, the second is 2, ...
     * @return <code>true</code> if so; <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.4
     */
    boolebn isSigned(int pbrbm) throws SQLException;

    /**
     * Retrieves the designbted pbrbmeter's specified column size.
     *
     * <P>The returned vblue represents the mbximum column size for the given pbrbmeter.
     * For numeric dbtb, this is the mbximum precision.  For chbrbcter dbtb, this is the length in chbrbcters.
     * For dbtetime dbtbtypes, this is the length in chbrbcters of the String representbtion (bssuming the
     * mbximum bllowed precision of the frbctionbl seconds component). For binbry dbtb, this is the length in bytes.  For the ROWID dbtbtype,
     * this is the length in bytes. 0 is returned for dbtb types where the
     * column size is not bpplicbble.
     *
     * @pbrbm pbrbm the first pbrbmeter is 1, the second is 2, ...
     * @return precision
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.4
     */
    int getPrecision(int pbrbm) throws SQLException;

    /**
     * Retrieves the designbted pbrbmeter's number of digits to right of the decimbl point.
     * 0 is returned for dbtb types where the scble is not bpplicbble.
     *
     * @pbrbm pbrbm the first pbrbmeter is 1, the second is 2, ...
     * @return scble
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.4
     */
    int getScble(int pbrbm) throws SQLException;

    /**
     * Retrieves the designbted pbrbmeter's SQL type.
     *
     * @pbrbm pbrbm the first pbrbmeter is 1, the second is 2, ...
     * @return SQL type from <code>jbvb.sql.Types</code>
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.4
     * @see Types
     */
    int getPbrbmeterType(int pbrbm) throws SQLException;

    /**
     * Retrieves the designbted pbrbmeter's dbtbbbse-specific type nbme.
     *
     * @pbrbm pbrbm the first pbrbmeter is 1, the second is 2, ...
     * @return type the nbme used by the dbtbbbse. If the pbrbmeter type is
     * b user-defined type, then b fully-qublified type nbme is returned.
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.4
     */
    String getPbrbmeterTypeNbme(int pbrbm) throws SQLException;


    /**
     * Retrieves the fully-qublified nbme of the Jbvb clbss whose instbnces
     * should be pbssed to the method <code>PrepbredStbtement.setObject</code>.
     *
     * @pbrbm pbrbm the first pbrbmeter is 1, the second is 2, ...
     * @return the fully-qublified nbme of the clbss in the Jbvb progrbmming
     *         lbngubge thbt would be used by the method
     *         <code>PrepbredStbtement.setObject</code> to set the vblue
     *         in the specified pbrbmeter. This is the clbss nbme used
     *         for custom mbpping.
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.4
     */
    String getPbrbmeterClbssNbme(int pbrbm) throws SQLException;

    /**
     * The constbnt indicbting thbt the mode of the pbrbmeter is unknown.
     */
    int pbrbmeterModeUnknown = 0;

    /**
     * The constbnt indicbting thbt the pbrbmeter's mode is IN.
     */
    int pbrbmeterModeIn = 1;

    /**
     * The constbnt indicbting thbt the pbrbmeter's mode is INOUT.
     */
    int pbrbmeterModeInOut = 2;

    /**
     * The constbnt indicbting thbt the pbrbmeter's mode is  OUT.
     */
    int pbrbmeterModeOut = 4;

    /**
     * Retrieves the designbted pbrbmeter's mode.
     *
     * @pbrbm pbrbm the first pbrbmeter is 1, the second is 2, ...
     * @return mode of the pbrbmeter; one of
     *        <code>PbrbmeterMetbDbtb.pbrbmeterModeIn</code>,
     *        <code>PbrbmeterMetbDbtb.pbrbmeterModeOut</code>, or
     *        <code>PbrbmeterMetbDbtb.pbrbmeterModeInOut</code>
     *        <code>PbrbmeterMetbDbtb.pbrbmeterModeUnknown</code>.
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @since 1.4
     */
    int getPbrbmeterMode(int pbrbm) throws SQLException;
}
