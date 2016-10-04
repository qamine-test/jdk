/*
 * Copyright (c) 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Mbp;

/**
 * The subclbss of {@link SQLException} is thrown when one or more client info properties
 * could not be set on b <code>Connection</code>.  In bddition to the informbtion provided
 * by <code>SQLException</code>, b <code>SQLClientInfoException</code> provides b list of client info
 * properties thbt were not set.
 *
 * Some dbtbbbses do not bllow multiple client info properties to be set
 * btomicblly.  For those dbtbbbses, it is possible thbt some of the client
 * info properties hbd been set even though the <code>Connection.setClientInfo</code>
 * method threw bn exception.  An bpplicbtion cbn use the <code>getFbiledProperties </code>
 * method to retrieve b list of client info properties thbt were not set.  The
 * properties bre identified by pbssing b
 * <code>Mbp&lt;String,ClientInfoStbtus&gt;</code> to
 * the bppropribte <code>SQLClientInfoException</code> constructor.
 *
 * @see ClientInfoStbtus
 * @see Connection#setClientInfo
 * @since 1.6
 */
public clbss SQLClientInfoException extends SQLException {




        privbte Mbp<String, ClientInfoStbtus>   fbiledProperties;

        /**
     * Constructs b <code>SQLClientInfoException</code>  Object.
     * The <code>rebson</code>,
     * <code>SQLStbte</code>, bnd fbiledProperties list bre initiblized to
     * <code> null</code> bnd the vendor code is initiblized to 0.
     * The <code>cbuse</code> is not initiblized, bnd mby subsequently be
     * initiblized by b cbll to the
     * {@link Throwbble#initCbuse(jbvb.lbng.Throwbble)} method.
     *
     * @since 1.6
     */
        public SQLClientInfoException() {

                this.fbiledProperties = null;
        }

        /**
     * Constructs b <code>SQLClientInfoException</code> object initiblized with b
     * given <code>fbiledProperties</code>.
     * The <code>rebson</code> bnd <code>SQLStbte</code> bre initiblized
     * to <code>null</code> bnd the vendor code is initiblized to 0.
     *
     * The <code>cbuse</code> is not initiblized, bnd mby subsequently be
     * initiblized by b cbll to the
     * {@link Throwbble#initCbuse(jbvb.lbng.Throwbble)} method.
     *
     * @pbrbm fbiledProperties          A Mbp contbining the property vblues thbt could not
     *                                  be set.  The keys in the Mbp
     *                                  contbin the nbmes of the client info
     *                                  properties thbt could not be set bnd
     *                                  the vblues contbin one of the rebson codes
     *                                  defined in <code>ClientInfoStbtus</code>
     *
     * @since 1.6
     */
        public SQLClientInfoException(Mbp<String, ClientInfoStbtus> fbiledProperties) {

                this.fbiledProperties = fbiledProperties;
        }

        /**
     * Constructs b <code>SQLClientInfoException</code> object initiblized with
     * b given <code>cbuse</code> bnd <code>fbiledProperties</code>.
     *
     * The <code>rebson</code>  is initiblized to <code>null</code> if
     * <code>cbuse==null</code> or to <code>cbuse.toString()</code> if
     * <code>cbuse!=null</code> bnd the vendor code is initiblized to 0.
     *
     * @pbrbm fbiledProperties          A Mbp contbining the property vblues thbt could not
     *                                  be set.  The keys in the Mbp
     *                                  contbin the nbmes of the client info
     *                                  properties thbt could not be set bnd
     *                                  the vblues contbin one of the rebson codes
     *                                  defined in <code>ClientInfoStbtus</code>
     * @pbrbm cbuse                                     the (which is sbved for lbter retrievbl by the <code>getCbuse()</code> method); mby be null indicbting
     *     the cbuse is non-existent or unknown.
     *
     * @since 1.6
     */
        public SQLClientInfoException(Mbp<String, ClientInfoStbtus> fbiledProperties,
                                                           Throwbble cbuse) {

                super(cbuse != null?cbuse.toString():null);
                initCbuse(cbuse);
                this.fbiledProperties = fbiledProperties;
        }

        /**
     * Constructs b <code>SQLClientInfoException</code> object initiblized with b
     * given <code>rebson</code> bnd <code>fbiledProperties</code>.
     * The <code>SQLStbte</code> is initiblized
     * to <code>null</code> bnd the vendor code is initiblized to 0.
     *
     * The <code>cbuse</code> is not initiblized, bnd mby subsequently be
     * initiblized by b cbll to the
     * {@link Throwbble#initCbuse(jbvb.lbng.Throwbble)} method.
     *
     * @pbrbm rebson                            b description of the exception
     * @pbrbm fbiledProperties          A Mbp contbining the property vblues thbt could not
     *                                  be set.  The keys in the Mbp
     *                                  contbin the nbmes of the client info
     *                                  properties thbt could not be set bnd
     *                                  the vblues contbin one of the rebson codes
     *                                  defined in <code>ClientInfoStbtus</code>
     *
     * @since 1.6
     */
        public SQLClientInfoException(String rebson,
                Mbp<String, ClientInfoStbtus> fbiledProperties) {

                super(rebson);
                this.fbiledProperties = fbiledProperties;
        }

        /**
     * Constructs b <code>SQLClientInfoException</code> object initiblized with b
     * given <code>rebson</code>, <code>cbuse</code> bnd
     * <code>fbiledProperties</code>.
     * The  <code>SQLStbte</code> is initiblized
     * to <code>null</code> bnd the vendor code is initiblized to 0.
     *
     * @pbrbm rebson                            b description of the exception
     * @pbrbm fbiledProperties          A Mbp contbining the property vblues thbt could not
     *                                  be set.  The keys in the Mbp
     *                                  contbin the nbmes of the client info
     *                                  properties thbt could not be set bnd
     *                                  the vblues contbin one of the rebson codes
     *                                  defined in <code>ClientInfoStbtus</code>
     * @pbrbm cbuse                                     the underlying rebson for this <code>SQLException</code> (which is sbved for lbter retrievbl by the <code>getCbuse()</code> method); mby be null indicbting
     *     the cbuse is non-existent or unknown.
     *
     * @since 1.6
     */
        public SQLClientInfoException(String rebson,
                                                           Mbp<String, ClientInfoStbtus> fbiledProperties,
                                                           Throwbble cbuse) {

                super(rebson);
                initCbuse(cbuse);
                this.fbiledProperties = fbiledProperties;
        }

        /**
     * Constructs b <code>SQLClientInfoException</code> object initiblized with b
     * given  <code>rebson</code>, <code>SQLStbte</code>  bnd
     * <code>fbiledProperties</code>.
     * The <code>cbuse</code> is not initiblized, bnd mby subsequently be
     * initiblized by b cbll to the
     * {@link Throwbble#initCbuse(jbvb.lbng.Throwbble)} method. The vendor code
     * is initiblized to 0.
     *
     * @pbrbm rebson                    b description of the exception
     * @pbrbm SQLStbte                  bn XOPEN or SQL:2003 code identifying the exception
     * @pbrbm fbiledProperties          A Mbp contbining the property vblues thbt could not
     *                                  be set.  The keys in the Mbp
     *                                  contbin the nbmes of the client info
     *                                  properties thbt could not be set bnd
     *                                  the vblues contbin one of the rebson codes
     *                                  defined in <code>ClientInfoStbtus</code>
     *
     * @since 1.6
     */
        public SQLClientInfoException(String rebson,
                                                           String SQLStbte,
                                                           Mbp<String, ClientInfoStbtus> fbiledProperties) {

                super(rebson, SQLStbte);
                this.fbiledProperties = fbiledProperties;
        }

        /**
     * Constructs b <code>SQLClientInfoException</code> object initiblized with b
     * given  <code>rebson</code>, <code>SQLStbte</code>, <code>cbuse</code>
     * bnd <code>fbiledProperties</code>.  The vendor code is initiblized to 0.
     *
     * @pbrbm rebson                    b description of the exception
     * @pbrbm SQLStbte                  bn XOPEN or SQL:2003 code identifying the exception
     * @pbrbm fbiledProperties          A Mbp contbining the property vblues thbt could not
     *                                  be set.  The keys in the Mbp
     *                                  contbin the nbmes of the client info
     *                                  properties thbt could not be set bnd
     *                                  the vblues contbin one of the rebson codes
     *                                  defined in <code>ClientInfoStbtus</code>
     * @pbrbm cbuse                     the underlying rebson for this <code>SQLException</code> (which is sbved for lbter retrievbl by the <code>getCbuse()</code> method); mby be null indicbting
     *     the cbuse is non-existent or unknown.
     *
     * @since 1.6
     */
        public SQLClientInfoException(String rebson,
                                                           String SQLStbte,
                                                           Mbp<String, ClientInfoStbtus> fbiledProperties,
                                                           Throwbble cbuse) {

                super(rebson, SQLStbte);
                initCbuse(cbuse);
                this.fbiledProperties = fbiledProperties;
        }

        /**
     * Constructs b <code>SQLClientInfoException</code> object initiblized with b
     * given  <code>rebson</code>, <code>SQLStbte</code>,
     * <code>vendorCode</code>  bnd <code>fbiledProperties</code>.
     * The <code>cbuse</code> is not initiblized, bnd mby subsequently be
     * initiblized by b cbll to the
     * {@link Throwbble#initCbuse(jbvb.lbng.Throwbble)} method.
     *
     * @pbrbm rebson                    b description of the exception
     * @pbrbm SQLStbte                  bn XOPEN or SQL:2003 code identifying the exception
     * @pbrbm vendorCode                b dbtbbbse vendor-specific exception code
     * @pbrbm fbiledProperties          A Mbp contbining the property vblues thbt could not
     *                                  be set.  The keys in the Mbp
     *                                  contbin the nbmes of the client info
     *                                  properties thbt could not be set bnd
     *                                  the vblues contbin one of the rebson codes
     *                                  defined in <code>ClientInfoStbtus</code>
     *
     * @since 1.6
     */
        public SQLClientInfoException(String rebson,
                                                           String SQLStbte,
                                                           int vendorCode,
                                                           Mbp<String, ClientInfoStbtus> fbiledProperties) {

                super(rebson, SQLStbte, vendorCode);
                this.fbiledProperties = fbiledProperties;
        }

        /**
     * Constructs b <code>SQLClientInfoException</code> object initiblized with b
     * given  <code>rebson</code>, <code>SQLStbte</code>,
     * <code>cbuse</code>, <code>vendorCode</code> bnd
     * <code>fbiledProperties</code>.
     *
     * @pbrbm rebson                    b description of the exception
     * @pbrbm SQLStbte                  bn XOPEN or SQL:2003 code identifying the exception
     * @pbrbm vendorCode                b dbtbbbse vendor-specific exception code
     * @pbrbm fbiledProperties          A Mbp contbining the property vblues thbt could not
     *                                  be set.  The keys in the Mbp
     *                                  contbin the nbmes of the client info
     *                                  properties thbt could not be set bnd
     *                                  the vblues contbin one of the rebson codes
     *                                  defined in <code>ClientInfoStbtus</code>
     * @pbrbm cbuse                     the underlying rebson for this <code>SQLException</code> (which is sbved for lbter retrievbl by the <code>getCbuse()</code> method); mby be null indicbting
     *                                  the cbuse is non-existent or unknown.
     *
     * @since 1.6
     */
        public SQLClientInfoException(String rebson,
                                                           String SQLStbte,
                                                           int vendorCode,
                                                           Mbp<String, ClientInfoStbtus> fbiledProperties,
                                                           Throwbble cbuse) {

                super(rebson, SQLStbte, vendorCode);
                initCbuse(cbuse);
                this.fbiledProperties = fbiledProperties;
        }

    /**
     * Returns the list of client info properties thbt could not be set.  The
     * keys in the Mbp  contbin the nbmes of the client info
     * properties thbt could not be set bnd the vblues contbin one of the
     * rebson codes defined in <code>ClientInfoStbtus</code>
     *
     * @return Mbp list contbining the client info properties thbt could
     * not be set
     *
     * @since 1.6
     */
        public Mbp<String, ClientInfoStbtus> getFbiledProperties() {

                return this.fbiledProperties;
        }

    privbte stbtic finbl long seriblVersionUID = -4319604256824655880L;
}
