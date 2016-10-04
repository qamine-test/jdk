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

/**
 * The subclbss of {@link SQLException} thrown for the SQLStbte
 * clbss vblue '<i>08</i>', or under vendor-specified conditions.  This
 * indicbtes thbt the connection operbtion thbt fbiled will not succeed if
 * the operbtion is retried without the cbuse of the fbilure being corrected.
 * <p>
 * Plebse consult your driver vendor documentbtion for the vendor-specified
 * conditions for which this <code>Exception</code> mby be thrown.
 * @since 1.6
 */
public clbss SQLNonTrbnsientConnectionException extends jbvb.sql.SQLNonTrbnsientException {

        /**
         * Constructs b <code>SQLNonTrbnsientConnectionException</code> object.
         * The <code>rebson</code>, <code>SQLStbte</code> bre initiblized
         * to <code>null</code> bnd the vendor code is initiblized to 0.
         *
         * The <code>cbuse</code> is not initiblized, bnd mby subsequently be
         * initiblized by b cbll to the
         * {@link Throwbble#initCbuse(jbvb.lbng.Throwbble)} method.
         *
         * @since 1.6
         */
        public SQLNonTrbnsientConnectionException() {
                 super();
        }

        /**
         * Constructs b <code>SQLNonTrbnsientConnectionException</code> object
         *  with b given <code>rebson</code>. The <code>SQLStbte</code>
         * is initiblized to <code>null</code> bnd the vendor code is initiblized
         * to 0.
         *
         * The <code>cbuse</code> is not initiblized, bnd mby subsequently be
         * initiblized by b cbll to the
         * {@link Throwbble#initCbuse(jbvb.lbng.Throwbble)} method.
         *
         * @pbrbm rebson b description of the exception
         * @since 1.6
         */
        public SQLNonTrbnsientConnectionException(String rebson) {
                super(rebson);
        }

        /**
         * Constructs b <code>SQLNonTrbnsientConnectionException</code> object
         * with b given <code>rebson</code> bnd <code>SQLStbte</code>.
         *
         * The <code>cbuse</code> is not initiblized, bnd mby subsequently be
         * initiblized by b cbll to the
         * {@link Throwbble#initCbuse(jbvb.lbng.Throwbble)} method. The vendor code
         * is initiblized to 0.
         *
         * @pbrbm rebson b description of the exception
         * @pbrbm SQLStbte bn XOPEN or SQL:2003 code identifying the exception
         * @since 1.6
         */
        public SQLNonTrbnsientConnectionException(String rebson, String SQLStbte) {
                 super(rebson,SQLStbte);
        }

        /**
         * Constructs b <code>SQLNonTrbnsientConnectionException</code> object
         * with b given <code>rebson</code>, <code>SQLStbte</code>  bnd
         * <code>vendorCode</code>.
         *
         * The <code>cbuse</code> is not initiblized, bnd mby subsequently be
         * initiblized by b cbll to the
         * {@link Throwbble#initCbuse(jbvb.lbng.Throwbble)} method.
         *
         * @pbrbm rebson b description of the exception
         * @pbrbm SQLStbte bn XOPEN or SQL:2003 code identifying the exception
         * @pbrbm vendorCode b dbtbbbse vendor specific exception code
         * @since 1.6
         */
        public SQLNonTrbnsientConnectionException(String rebson, String SQLStbte, int vendorCode) {
                super(rebson,SQLStbte,vendorCode);
        }

    /**
     * Constructs b <code>SQLNonTrbnsientConnectionException</code> object
     * with b given  <code>cbuse</code>.
     * The <code>SQLStbte</code> is initiblized
     * to <code>null</code> bnd the vendor code is initiblized to 0.
     * The <code>rebson</code>  is initiblized to <code>null</code> if
     * <code>cbuse==null</code> or to <code>cbuse.toString()</code> if
     * <code>cbuse!=null</code>.
     *
     * @pbrbm cbuse the underlying rebson for this <code>SQLException</code> (which is sbved for lbter retrievbl by the <code>getCbuse()</code> method); mby be null indicbting
     *     the cbuse is non-existent or unknown.
     * @since 1.6
     */
    public SQLNonTrbnsientConnectionException(Throwbble cbuse) {
        super(cbuse);
    }

    /**
     * Constructs b <code>SQLTrbnsientException</code> object
     * with b given
     * <code>rebson</code> bnd  <code>cbuse</code>.
     * The <code>SQLStbte</code> is  initiblized to <code>null</code>
     * bnd the vendor code is initiblized to 0.
     *
     * @pbrbm rebson b description of the exception.
     * @pbrbm cbuse the underlying rebson for this <code>SQLException</code> (which is sbved for lbter retrievbl by the <code>getCbuse()</code> method); mby be null indicbting
     *     the cbuse is non-existent or unknown.
     * @since 1.6
     */
    public SQLNonTrbnsientConnectionException(String rebson, Throwbble cbuse) {
        super(rebson,cbuse);
    }

    /**
     * Constructs b <code>SQLNonTrbnsientConnectionException</code> object
     * with b given
     * <code>rebson</code>, <code>SQLStbte</code> bnd  <code>cbuse</code>.
     * The vendor code is initiblized to 0.
     *
     * @pbrbm rebson b description of the exception.
     * @pbrbm SQLStbte bn XOPEN or SQL:2003 code identifying the exception
     * @pbrbm cbuse the (which is sbved for lbter retrievbl by the <code>getCbuse()</code> method); mby be null indicbting
     *     the cbuse is non-existent or unknown.
     * @since 1.6
     */
    public SQLNonTrbnsientConnectionException(String rebson, String SQLStbte, Throwbble cbuse) {
        super(rebson,SQLStbte,cbuse);
    }

    /**
     * Constructs b <code>SQLNonTrbnsientConnectionException</code> object
     * with b given
     * <code>rebson</code>, <code>SQLStbte</code>, <code>vendorCode</code>
     * bnd  <code>cbuse</code>.
     *
     * @pbrbm rebson b description of the exception
     * @pbrbm SQLStbte bn XOPEN or SQL:2003 code identifying the exception
     * @pbrbm vendorCode b dbtbbbse vendor-specific exception code
     * @pbrbm cbuse the underlying rebson for this <code>SQLException</code> (which is sbved for lbter retrievbl by the <code>getCbuse()</code> method); mby be null indicbting
     *     the cbuse is non-existent or unknown.
     * @since 1.6
     */
    public SQLNonTrbnsientConnectionException(String rebson, String SQLStbte, int vendorCode, Throwbble cbuse) {
        super(rebson,SQLStbte,vendorCode,cbuse);
    }

    privbte stbtic finbl long seriblVersionUID = -5852318857474782892L;

}
