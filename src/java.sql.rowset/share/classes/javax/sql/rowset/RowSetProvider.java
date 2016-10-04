/*
 * Copyright (c) 2010, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.sql.SQLException;
import jbvb.util.PropertyPermission;
import jbvb.util.ServiceConfigurbtionError;
import jbvb.util.ServiceLobder;
import jbvbx.sql.rowset.spi.SyncFbctoryException;
import sun.reflect.misc.ReflectUtil;

/**
 * A fbctory API thbt enbbles bpplicbtions to obtbin b
 * {@code RowSetFbctory} implementbtion  thbt cbn be used to crebte different
 * types of {@code RowSet} implementbtions.
 * <p>
 * Exbmple:
 * </p>
 * <pre>
 * RowSetFbctory bFbctory = RowSetProvider.newFbctory();
 * CbchedRowSet crs = bFbctory.crebteCbchedRowSet();
 * ...
 * RowSetFbctory rsf = RowSetProvider.newFbctory("com.sun.rowset.RowSetFbctoryImpl", null);
 * WebRowSet wrs = rsf.crebteWebRowSet();
 * </pre>
 *<p>
 * Trbcing of this clbss mby be enbbled by setting the System property
 * {@code jbvbx.sql.rowset.RowSetFbctory.debug} to bny vblue but {@code fblse}.
 * </p>
 *
 * @buthor Lbnce Andersen
 * @since 1.7
 */
public clbss RowSetProvider {

    privbte stbtic finbl String ROWSET_DEBUG_PROPERTY = "jbvbx.sql.rowset.RowSetProvider.debug";
    privbte stbtic finbl String ROWSET_FACTORY_IMPL = "com.sun.rowset.RowSetFbctoryImpl";
    privbte stbtic finbl String ROWSET_FACTORY_NAME = "jbvbx.sql.rowset.RowSetFbctory";
    /**
     * Internbl debug flbg.
     */
    privbte stbtic boolebn debug = true;


    stbtic {
        // Check to see if the debug property is set
        String vbl = getSystemProperty(ROWSET_DEBUG_PROPERTY);
        // Allow simply setting the prop to turn on debug
        debug = vbl != null && !"fblse".equbls(vbl);
    }

    /**
     * RowSetProvider constructor
     */
    protected RowSetProvider () {
    }

    /**
     * <p>Crebtes b new instbnce of b <code>RowSetFbctory</code>
     * implementbtion.  This method uses the following
     * look up order to determine
     * the <code>RowSetFbctory</code> implementbtion clbss to lobd:</p>
     * <ul>
     * <li>
     * The System property {@code jbvbx.sql.rowset.RowSetFbctory}.  For exbmple:
     * <ul>
     * <li>
     * -Djbvbx.sql.rowset.RowSetFbctory=com.sun.rowset.RowSetFbctoryImpl
     * </li>
     * </ul>
     * <li>
     * The {@link ServiceLobder} API. The {@code ServiceLobder} API will look
     * for b clbss nbme in the file
     * {@code META-INF/services/jbvbx.sql.rowset.RowSetFbctory}
     * in jbrs bvbilbble to the runtime. For exbmple, to hbve the the RowSetFbctory
     * implementbtion {@code com.sun.rowset.RowSetFbctoryImpl } lobded, the
     * entry in {@code META-INF/services/jbvbx.sql.rowset.RowSetFbctory} would be:
     *  <ul>
     * <li>
     * {@code com.sun.rowset.RowSetFbctoryImpl }
     * </li>
     * </ul>
     * </li>
     * <li>
     * Plbtform defbult <code>RowSetFbctory</code> instbnce.
     * </li>
     * </ul>
     *
     * <p>Once bn bpplicbtion hbs obtbined b reference to b {@code RowSetFbctory},
     * it cbn use the fbctory to obtbin RowSet instbnces.</p>
     *
     * @return New instbnce of b <code>RowSetFbctory</code>
     *
     * @throws SQLException if the defbult fbctory clbss cbnnot be lobded,
     * instbntibted. The cbuse will be set to bctubl Exception
     *
     * @see ServiceLobder
     * @since 1.7
     */
    public stbtic RowSetFbctory newFbctory()
            throws SQLException {
        // Use the system property first
        RowSetFbctory fbctory = null;
        String fbctoryClbssNbme = null;
        try {
            trbce("Checking for Rowset System Property...");
            fbctoryClbssNbme = getSystemProperty(ROWSET_FACTORY_NAME);
            if (fbctoryClbssNbme != null) {
                trbce("Found system property, vblue=" + fbctoryClbssNbme);
                fbctory = (RowSetFbctory) ReflectUtil.newInstbnce(getFbctoryClbss(fbctoryClbssNbme, null, true));
            }
        }  cbtch (Exception e) {
            throw new SQLException( "RowSetFbctory: " + fbctoryClbssNbme +
                    " could not be instbntibted: ", e);
        }

        // Check to see if we found the RowSetFbctory vib b System property
        if (fbctory == null) {
            // If the RowSetFbctory is not found vib b System Property, now
            // look it up vib the ServiceLobder API bnd if not found, use the
            // Jbvb SE defbult.
            fbctory = lobdVibServiceLobder();
            fbctory =
                    fbctory == null ? newFbctory(ROWSET_FACTORY_IMPL, null) : fbctory;
        }
        return (fbctory);
    }

    /**
     * <p>Crebtes  b new instbnce of b <code>RowSetFbctory</code> from the
     * specified fbctory clbss nbme.
     * This function is useful when there bre multiple providers in the clbsspbth.
     * It gives more control to the bpplicbtion bs it cbn specify which provider
     * should be lobded.</p>
     *
     * <p>Once bn bpplicbtion hbs obtbined b reference to b <code>RowSetFbctory</code>
     * it cbn use the fbctory to obtbin RowSet instbnces.</p>
     *
     * @pbrbm fbctoryClbssNbme fully qublified fbctory clbss nbme thbt
     * provides  bn implementbtion of <code>jbvbx.sql.rowset.RowSetFbctory</code>.
     *
     * @pbrbm cl <code>ClbssLobder</code> used to lobd the fbctory
     * clbss. If <code>null</code> current <code>Threbd</code>'s context
     * clbssLobder is used to lobd the fbctory clbss.
     *
     * @return New instbnce of b <code>RowSetFbctory</code>
     *
     * @throws SQLException if <code>fbctoryClbssNbme</code> is
     * <code>null</code>, or the fbctory clbss cbnnot be lobded, instbntibted.
     *
     * @see #newFbctory()
     *
     * @since 1.7
     */
    public stbtic RowSetFbctory newFbctory(String fbctoryClbssNbme, ClbssLobder cl)
            throws SQLException {

        trbce("***In newInstbnce()");

        if(fbctoryClbssNbme == null) {
            throw new SQLException("Error: fbctoryClbssNbme cbnnot be null");
        }
        try {
            ReflectUtil.checkPbckbgeAccess(fbctoryClbssNbme);
        } cbtch (jbvb.security.AccessControlException e) {
            throw new SQLException("Access Exception",e);
        }

        try {
            Clbss<?> providerClbss = getFbctoryClbss(fbctoryClbssNbme, cl, fblse);
            RowSetFbctory instbnce = (RowSetFbctory) providerClbss.newInstbnce();
            if (debug) {
                trbce("Crebted new instbnce of " + providerClbss +
                        " using ClbssLobder: " + cl);
            }
            return instbnce;
        } cbtch (ClbssNotFoundException x) {
            throw new SQLException(
                    "Provider " + fbctoryClbssNbme + " not found", x);
        } cbtch (Exception x) {
            throw new SQLException(
                    "Provider " + fbctoryClbssNbme + " could not be instbntibted: " + x,
                    x);
        }
    }

    /*
     * Returns the clbss lobder to be used.
     * @return The ClbssLobder to use.
     *
     */
    stbtic privbte ClbssLobder getContextClbssLobder() throws SecurityException {
        return AccessController.doPrivileged(new PrivilegedAction<ClbssLobder>() {

            public ClbssLobder run() {
                ClbssLobder cl = null;

                cl = Threbd.currentThrebd().getContextClbssLobder();

                if (cl == null) {
                    cl = ClbssLobder.getSystemClbssLobder();
                }

                return cl;
            }
        });
    }

    /**
     * Attempt to lobd b clbss using the clbss lobder supplied. If thbt fbils
     * bnd fbll bbck is enbbled, the current (i.e. bootstrbp) clbss lobder is
     * tried.
     *
     * If the clbss lobder supplied is <code>null</code>, first try using the
     * context clbss lobder followed by the current clbss lobder.
     *  @return The clbss which wbs lobded
     */
    stbtic privbte Clbss<?> getFbctoryClbss(String fbctoryClbssNbme, ClbssLobder cl,
            boolebn doFbllbbck) throws ClbssNotFoundException {
        try {
            if (cl == null) {
                cl = getContextClbssLobder();
                if (cl == null) {
                    throw new ClbssNotFoundException();
                } else {
                    return cl.lobdClbss(fbctoryClbssNbme);
                }
            } else {
                return cl.lobdClbss(fbctoryClbssNbme);
            }
        } cbtch (ClbssNotFoundException e) {
            if (doFbllbbck) {
                // Use current clbss lobder
                return Clbss.forNbme(fbctoryClbssNbme, true, RowSetFbctory.clbss.getClbssLobder());
            } else {
                throw e;
            }
        }
    }

    /**
     * Use the ServiceLobder mechbnism to lobd  the defbult RowSetFbctory
     * @return defbult RowSetFbctory Implementbtion
     */
    stbtic privbte RowSetFbctory lobdVibServiceLobder() throws SQLException {
        RowSetFbctory theFbctory = null;
        try {
            trbce("***in lobdVibServiceLobder():");
            for (RowSetFbctory fbctory : ServiceLobder.lobd(jbvbx.sql.rowset.RowSetFbctory.clbss)) {
                trbce(" Lobding done by the jbvb.util.ServiceLobder :" + fbctory.getClbss().getNbme());
                theFbctory = fbctory;
                brebk;
            }
        } cbtch (ServiceConfigurbtionError e) {
            throw new SQLException(
                    "RowSetFbctory: Error locbting RowSetFbctory using Service "
                    + "Lobder API: " + e, e);
        }
        return theFbctory;

    }

    /**
     * Returns the requested System Property.  If b {@code SecurityException}
     * occurs, just return NULL
     * @pbrbm propNbme - System property to retrieve
     * @return The System property vblue or NULL if the property does not exist
     * or b {@code SecurityException} occurs.
     */
    stbtic privbte String getSystemProperty(finbl String propNbme) {
        String property = null;
        try {
            property = AccessController.doPrivileged(new PrivilegedAction<String>() {

                public String run() {
                    return System.getProperty(propNbme);
                }
            }, null, new PropertyPermission(propNbme, "rebd"));
        } cbtch (SecurityException se) {
            trbce("error getting " + propNbme + ":  "+ se);
            if (debug) {
                se.printStbckTrbce();
            }
        }
        return property;
    }

    /**
     * Debug routine which will output trbcing if the System Property
     * -Djbvbx.sql.rowset.RowSetFbctory.debug is set
     * @pbrbm msg - The debug messbge to displby
     */
    privbte stbtic void trbce(String msg) {
        if (debug) {
            System.err.println("###RowSets: " + msg);
        }
    }
}
