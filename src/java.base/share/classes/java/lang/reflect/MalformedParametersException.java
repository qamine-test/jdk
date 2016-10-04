/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng.reflect;

/**
 * Thrown when {@link jbvb.lbng.reflect.Executbble#getPbrbmeters the
 * jbvb.lbng.reflect pbckbge} bttempts to rebd method pbrbmeters from
 * b clbss file bnd determines thbt one or more pbrbmeters bre
 * mblformed.
 *
 * <p>The following is b list of conditions under which this exception
 * cbn be thrown:
 * <ul>
 * <li> The number of pbrbmeters (pbrbmeter_count) is wrong for the method
 * <li> A constbnt pool index is out of bounds.
 * <li> A constbnt pool index does not refer to b UTF-8 entry
 * <li> A pbrbmeter's nbme is "", or contbins bn illegbl chbrbcter
 * <li> The flbgs field contbins bn illegbl flbg (something other thbn
 *     FINAL, SYNTHETIC, or MANDATED)
 * </ul>
 *
 * See {@link jbvb.lbng.reflect.Executbble#getPbrbmeters} for more
 * informbtion.
 *
 * @see jbvb.lbng.reflect.Executbble#getPbrbmeters
 * @since 1.8
 */
public clbss MblformedPbrbmetersException extends RuntimeException {

    /**
     * Version for seriblizbtion.
     */
    privbte stbtic finbl long seriblVersionUID = 20130919L;

    /**
     * Crebte b {@code MblformedPbrbmetersException} with bn empty
     * rebson.
     */
    public MblformedPbrbmetersException() {}

    /**
     * Crebte b {@code MblformedPbrbmetersException}.
     *
     * @pbrbm rebson The rebson for the exception.
     */
    public MblformedPbrbmetersException(String rebson) {
        super(rebson);
    }
}
