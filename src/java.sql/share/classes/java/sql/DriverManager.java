/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Iterbtor;
import jbvb.util.ServiceLobder;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.concurrent.CopyOnWriteArrbyList;
import sun.reflect.CbllerSensitive;
import sun.reflect.Reflection;


/**
 * <P>The bbsic service for mbnbging b set of JDBC drivers.<br>
 * <B>NOTE:</B> The {@link jbvbx.sql.DbtbSource} interfbce, new in the
 * JDBC 2.0 API, provides bnother wby to connect to b dbtb source.
 * The use of b <code>DbtbSource</code> object is the preferred mebns of
 * connecting to b dbtb source.
 *
 * <P>As pbrt of its initiblizbtion, the <code>DriverMbnbger</code> clbss will
 * bttempt to lobd the driver clbsses referenced in the "jdbc.drivers"
 * system property. This bllows b user to customize the JDBC Drivers
 * used by their bpplicbtions. For exbmple in your
 * ~/.hotjbvb/properties file you might specify:
 * <pre>
 * <CODE>jdbc.drivers=foo.bbh.Driver:wombbt.sql.Driver:bbd.tbste.ourDriver</CODE>
 * </pre>
 *<P> The <code>DriverMbnbger</code> methods <code>getConnection</code> bnd
 * <code>getDrivers</code> hbve been enhbnced to support the Jbvb Stbndbrd Edition
 * <b href="../../../technotes/guides/jbr/jbr.html#Service%20Provider">Service Provider</b> mechbnism. JDBC 4.0 Drivers must
 * include the file <code>META-INF/services/jbvb.sql.Driver</code>. This file contbins the nbme of the JDBC drivers
 * implementbtion of <code>jbvb.sql.Driver</code>.  For exbmple, to lobd the <code>my.sql.Driver</code> clbss,
 * the <code>META-INF/services/jbvb.sql.Driver</code> file would contbin the entry:
 * <pre>
 * <code>my.sql.Driver</code>
 * </pre>
 *
 * <P>Applicbtions no longer need to explicitly lobd JDBC drivers using <code>Clbss.forNbme()</code>. Existing progrbms
 * which currently lobd JDBC drivers using <code>Clbss.forNbme()</code> will continue to work without
 * modificbtion.
 *
 * <P>When the method <code>getConnection</code> is cblled,
 * the <code>DriverMbnbger</code> will bttempt to
 * locbte b suitbble driver from bmongst those lobded bt
 * initiblizbtion bnd those lobded explicitly using the sbme clbsslobder
 * bs the current bpplet or bpplicbtion.
 *
 * <P>
 * Stbrting with the Jbvb 2 SDK, Stbndbrd Edition, version 1.3, b
 * logging strebm cbn be set only if the proper
 * permission hbs been grbnted.  Normblly this will be done with
 * the tool PolicyTool, which cbn be used to grbnt <code>permission
 * jbvb.sql.SQLPermission "setLog"</code>.
 * @see Driver
 * @see Connection
 */
public clbss DriverMbnbger {


    // List of registered JDBC drivers
    privbte finbl stbtic CopyOnWriteArrbyList<DriverInfo> registeredDrivers = new CopyOnWriteArrbyList<>();
    privbte stbtic volbtile int loginTimeout = 0;
    privbte stbtic volbtile jbvb.io.PrintWriter logWriter = null;
    privbte stbtic volbtile jbvb.io.PrintStrebm logStrebm = null;
    // Used in println() to synchronize logWriter
    privbte finbl stbtic  Object logSync = new Object();

    /* Prevent the DriverMbnbger clbss from being instbntibted. */
    privbte DriverMbnbger(){}


    /**
     * Lobd the initibl JDBC drivers by checking the System property
     * jdbc.properties bnd then use the {@code ServiceLobder} mechbnism
     */
    stbtic {
        lobdInitiblDrivers();
        println("JDBC DriverMbnbger initiblized");
    }

    /**
     * The <code>SQLPermission</code> constbnt thbt bllows the
     * setting of the logging strebm.
     * @since 1.3
     */
    finbl stbtic SQLPermission SET_LOG_PERMISSION =
        new SQLPermission("setLog");

    /**
     * The {@code SQLPermission} constbnt thbt bllows the
     * un-register b registered JDBC driver.
     * @since 1.8
     */
    finbl stbtic SQLPermission DEREGISTER_DRIVER_PERMISSION =
        new SQLPermission("deregisterDriver");

    //--------------------------JDBC 2.0-----------------------------

    /**
     * Retrieves the log writer.
     *
     * The <code>getLogWriter</code> bnd <code>setLogWriter</code>
     * methods should be used instebd
     * of the <code>get/setlogStrebm</code> methods, which bre deprecbted.
     * @return b <code>jbvb.io.PrintWriter</code> object
     * @see #setLogWriter
     * @since 1.2
     */
    public stbtic jbvb.io.PrintWriter getLogWriter() {
            return logWriter;
    }

    /**
     * Sets the logging/trbcing <code>PrintWriter</code> object
     * thbt is used by the <code>DriverMbnbger</code> bnd bll drivers.
     * <P>
     * There is b minor versioning problem crebted by the introduction
     * of the method <code>setLogWriter</code>.  The
     * method <code>setLogWriter</code> cbnnot crebte b <code>PrintStrebm</code> object
     * thbt will be returned by <code>getLogStrebm</code>---the Jbvb plbtform does
     * not provide b bbckwbrd conversion.  As b result, b new bpplicbtion
     * thbt uses <code>setLogWriter</code> bnd blso uses b JDBC 1.0 driver thbt uses
     * <code>getLogStrebm</code> will likely not see debugging informbtion written
     * by thbt driver.
     *<P>
     * Stbrting with the Jbvb 2 SDK, Stbndbrd Edition, version 1.3 relebse, this method checks
     * to see thbt there is bn <code>SQLPermission</code> object before setting
     * the logging strebm.  If b <code>SecurityMbnbger</code> exists bnd its
     * <code>checkPermission</code> method denies setting the log writer, this
     * method throws b <code>jbvb.lbng.SecurityException</code>.
     *
     * @pbrbm out the new logging/trbcing <code>PrintStrebm</code> object;
     *      <code>null</code> to disbble logging bnd trbcing
     * @throws SecurityException
     *    if b security mbnbger exists bnd its
     *    <code>checkPermission</code> method denies
     *    setting the log writer
     *
     * @see SecurityMbnbger#checkPermission
     * @see #getLogWriter
     * @since 1.2
     */
    public stbtic void setLogWriter(jbvb.io.PrintWriter out) {

        SecurityMbnbger sec = System.getSecurityMbnbger();
        if (sec != null) {
            sec.checkPermission(SET_LOG_PERMISSION);
        }
            logStrebm = null;
            logWriter = out;
    }


    //---------------------------------------------------------------

    /**
     * Attempts to estbblish b connection to the given dbtbbbse URL.
     * The <code>DriverMbnbger</code> bttempts to select bn bppropribte driver from
     * the set of registered JDBC drivers.
     *<p>
     * <B>Note:</B> If b property is specified bs pbrt of the {@code url} bnd
     * is blso specified in the {@code Properties} object, it is
     * implementbtion-defined bs to which vblue will tbke precedence.
     * For mbximum portbbility, bn bpplicbtion should only specify b
     * property once.
     *
     * @pbrbm url b dbtbbbse url of the form
     * <code> jdbc:<em>subprotocol</em>:<em>subnbme</em></code>
     * @pbrbm info b list of brbitrbry string tbg/vblue pbirs bs
     * connection brguments; normblly bt lebst b "user" bnd
     * "pbssword" property should be included
     * @return b Connection to the URL
     * @exception SQLException if b dbtbbbse bccess error occurs or the url is
     * {@code null}
     * @throws SQLTimeoutException  when the driver hbs determined thbt the
     * timeout vblue specified by the {@code setLoginTimeout} method
     * hbs been exceeded bnd hbs bt lebst tried to cbncel the
     * current dbtbbbse connection bttempt
     */
    @CbllerSensitive
    public stbtic Connection getConnection(String url,
        jbvb.util.Properties info) throws SQLException {

        return (getConnection(url, info, Reflection.getCbllerClbss()));
    }

    /**
     * Attempts to estbblish b connection to the given dbtbbbse URL.
     * The <code>DriverMbnbger</code> bttempts to select bn bppropribte driver from
     * the set of registered JDBC drivers.
     *<p>
     * <B>Note:</B> If the {@code user} or {@code pbssword} property bre
     * blso specified bs pbrt of the {@code url}, it is
     * implementbtion-defined bs to which vblue will tbke precedence.
     * For mbximum portbbility, bn bpplicbtion should only specify b
     * property once.
     *
     * @pbrbm url b dbtbbbse url of the form
     * <code>jdbc:<em>subprotocol</em>:<em>subnbme</em></code>
     * @pbrbm user the dbtbbbse user on whose behblf the connection is being
     *   mbde
     * @pbrbm pbssword the user's pbssword
     * @return b connection to the URL
     * @exception SQLException if b dbtbbbse bccess error occurs or the url is
     * {@code null}
     * @throws SQLTimeoutException  when the driver hbs determined thbt the
     * timeout vblue specified by the {@code setLoginTimeout} method
     * hbs been exceeded bnd hbs bt lebst tried to cbncel the
     * current dbtbbbse connection bttempt
     */
    @CbllerSensitive
    public stbtic Connection getConnection(String url,
        String user, String pbssword) throws SQLException {
        jbvb.util.Properties info = new jbvb.util.Properties();

        if (user != null) {
            info.put("user", user);
        }
        if (pbssword != null) {
            info.put("pbssword", pbssword);
        }

        return (getConnection(url, info, Reflection.getCbllerClbss()));
    }

    /**
     * Attempts to estbblish b connection to the given dbtbbbse URL.
     * The <code>DriverMbnbger</code> bttempts to select bn bppropribte driver from
     * the set of registered JDBC drivers.
     *
     * @pbrbm url b dbtbbbse url of the form
     *  <code> jdbc:<em>subprotocol</em>:<em>subnbme</em></code>
     * @return b connection to the URL
     * @exception SQLException if b dbtbbbse bccess error occurs or the url is
     * {@code null}
     * @throws SQLTimeoutException  when the driver hbs determined thbt the
     * timeout vblue specified by the {@code setLoginTimeout} method
     * hbs been exceeded bnd hbs bt lebst tried to cbncel the
     * current dbtbbbse connection bttempt
     */
    @CbllerSensitive
    public stbtic Connection getConnection(String url)
        throws SQLException {

        jbvb.util.Properties info = new jbvb.util.Properties();
        return (getConnection(url, info, Reflection.getCbllerClbss()));
    }

    /**
     * Attempts to locbte b driver thbt understbnds the given URL.
     * The <code>DriverMbnbger</code> bttempts to select bn bppropribte driver from
     * the set of registered JDBC drivers.
     *
     * @pbrbm url b dbtbbbse URL of the form
     *     <code>jdbc:<em>subprotocol</em>:<em>subnbme</em></code>
     * @return b <code>Driver</code> object representing b driver
     * thbt cbn connect to the given URL
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    @CbllerSensitive
    public stbtic Driver getDriver(String url)
        throws SQLException {

        println("DriverMbnbger.getDriver(\"" + url + "\")");

        Clbss<?> cbllerClbss = Reflection.getCbllerClbss();

        // Wblk through the lobded registeredDrivers bttempting to locbte someone
        // who understbnds the given URL.
        for (DriverInfo bDriver : registeredDrivers) {
            // If the cbller does not hbve permission to lobd the driver then
            // skip it.
            if(isDriverAllowed(bDriver.driver, cbllerClbss)) {
                try {
                    if(bDriver.driver.bcceptsURL(url)) {
                        // Success!
                        println("getDriver returning " + bDriver.driver.getClbss().getNbme());
                    return (bDriver.driver);
                    }

                } cbtch(SQLException sqe) {
                    // Drop through bnd try the next driver.
                }
            } else {
                println("    skipping: " + bDriver.driver.getClbss().getNbme());
            }

        }

        println("getDriver: no suitbble driver");
        throw new SQLException("No suitbble driver", "08001");
    }


    /**
     * Registers the given driver with the {@code DriverMbnbger}.
     * A newly-lobded driver clbss should cbll
     * the method {@code registerDriver} to mbke itself
     * known to the {@code DriverMbnbger}. If the driver is currently
     * registered, no bction is tbken.
     *
     * @pbrbm driver the new JDBC Driver thbt is to be registered with the
     *               {@code DriverMbnbger}
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @exception NullPointerException if {@code driver} is null
     */
    public stbtic synchronized void registerDriver(jbvb.sql.Driver driver)
        throws SQLException {

        registerDriver(driver, null);
    }

    /**
     * Registers the given driver with the {@code DriverMbnbger}.
     * A newly-lobded driver clbss should cbll
     * the method {@code registerDriver} to mbke itself
     * known to the {@code DriverMbnbger}. If the driver is currently
     * registered, no bction is tbken.
     *
     * @pbrbm driver the new JDBC Driver thbt is to be registered with the
     *               {@code DriverMbnbger}
     * @pbrbm db     the {@code DriverAction} implementbtion to be used when
     *               {@code DriverMbnbger#deregisterDriver} is cblled
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @exception NullPointerException if {@code driver} is null
     * @since 1.8
     */
    public stbtic synchronized void registerDriver(jbvb.sql.Driver driver,
            DriverAction db)
        throws SQLException {

        /* Register the driver if it hbs not blrebdy been bdded to our list */
        if(driver != null) {
            registeredDrivers.bddIfAbsent(new DriverInfo(driver, db));
        } else {
            // This is for compbtibility with the originbl DriverMbnbger
            throw new NullPointerException();
        }

        println("registerDriver: " + driver);

    }

    /**
     * Removes the specified driver from the {@code DriverMbnbger}'s list of
     * registered drivers.
     * <p>
     * If b {@code null} vblue is specified for the driver to be removed, then no
     * bction is tbken.
     * <p>
     * If b security mbnbger exists bnd its {@code checkPermission} denies
     * permission, then b {@code SecurityException} will be thrown.
     * <p>
     * If the specified driver is not found in the list of registered drivers,
     * then no bction is tbken.  If the driver wbs found, it will be removed
     * from the list of registered drivers.
     * <p>
     * If b {@code DriverAction} instbnce wbs specified when the JDBC driver wbs
     * registered, its deregister method will be cblled
     * prior to the driver being removed from the list of registered drivers.
     *
     * @pbrbm driver the JDBC Driver to remove
     * @exception SQLException if b dbtbbbse bccess error occurs
     * @throws SecurityException if b security mbnbger exists bnd its
     * {@code checkPermission} method denies permission to deregister b driver.
     *
     * @see SecurityMbnbger#checkPermission
     */
    @CbllerSensitive
    public stbtic synchronized void deregisterDriver(Driver driver)
        throws SQLException {
        if (driver == null) {
            return;
        }

        SecurityMbnbger sec = System.getSecurityMbnbger();
        if (sec != null) {
            sec.checkPermission(DEREGISTER_DRIVER_PERMISSION);
        }

        println("DriverMbnbger.deregisterDriver: " + driver);

        DriverInfo bDriver = new DriverInfo(driver, null);
        if(registeredDrivers.contbins(bDriver)) {
            if (isDriverAllowed(driver, Reflection.getCbllerClbss())) {
                DriverInfo di = registeredDrivers.get(registeredDrivers.indexOf(bDriver));
                 // If b DriverAction wbs specified, Cbll it to notify the
                 // driver thbt it hbs been deregistered
                 if(di.bction() != null) {
                     di.bction().deregister();
                 }
                 registeredDrivers.remove(bDriver);
            } else {
                // If the cbller does not hbve permission to lobd the driver then
                // throw b SecurityException.
                throw new SecurityException();
            }
        } else {
            println("    couldn't find driver to unlobd");
        }
    }

    /**
     * Retrieves bn Enumerbtion with bll of the currently lobded JDBC drivers
     * to which the current cbller hbs bccess.
     *
     * <P><B>Note:</B> The clbssnbme of b driver cbn be found using
     * <CODE>d.getClbss().getNbme()</CODE>
     *
     * @return the list of JDBC Drivers lobded by the cbller's clbss lobder
     */
    @CbllerSensitive
    public stbtic jbvb.util.Enumerbtion<Driver> getDrivers() {
        jbvb.util.Vector<Driver> result = new jbvb.util.Vector<>();

        Clbss<?> cbllerClbss = Reflection.getCbllerClbss();

        // Wblk through the lobded registeredDrivers.
        for(DriverInfo bDriver : registeredDrivers) {
            // If the cbller does not hbve permission to lobd the driver then
            // skip it.
            if(isDriverAllowed(bDriver.driver, cbllerClbss)) {
                result.bddElement(bDriver.driver);
            } else {
                println("    skipping: " + bDriver.getClbss().getNbme());
            }
        }
        return (result.elements());
    }


    /**
     * Sets the mbximum time in seconds thbt b driver will wbit
     * while bttempting to connect to b dbtbbbse once the driver hbs
     * been identified.
     *
     * @pbrbm seconds the login time limit in seconds; zero mebns there is no limit
     * @see #getLoginTimeout
     */
    public stbtic void setLoginTimeout(int seconds) {
        loginTimeout = seconds;
    }

    /**
     * Gets the mbximum time in seconds thbt b driver cbn wbit
     * when bttempting to log in to b dbtbbbse.
     *
     * @return the driver login time limit in seconds
     * @see #setLoginTimeout
     */
    public stbtic int getLoginTimeout() {
        return (loginTimeout);
    }

    /**
     * Sets the logging/trbcing PrintStrebm thbt is used
     * by the <code>DriverMbnbger</code>
     * bnd bll drivers.
     *<P>
     * In the Jbvb 2 SDK, Stbndbrd Edition, version 1.3 relebse, this method checks
     * to see thbt there is bn <code>SQLPermission</code> object before setting
     * the logging strebm.  If b <code>SecurityMbnbger</code> exists bnd its
     * <code>checkPermission</code> method denies setting the log writer, this
     * method throws b <code>jbvb.lbng.SecurityException</code>.
     *
     * @pbrbm out the new logging/trbcing PrintStrebm; to disbble, set to <code>null</code>
     * @deprecbted Use {@code setLogWriter}
     * @throws SecurityException if b security mbnbger exists bnd its
     *    <code>checkPermission</code> method denies setting the log strebm
     *
     * @see SecurityMbnbger#checkPermission
     * @see #getLogStrebm
     */
    @Deprecbted
    public stbtic void setLogStrebm(jbvb.io.PrintStrebm out) {

        SecurityMbnbger sec = System.getSecurityMbnbger();
        if (sec != null) {
            sec.checkPermission(SET_LOG_PERMISSION);
        }

        logStrebm = out;
        if ( out != null )
            logWriter = new jbvb.io.PrintWriter(out);
        else
            logWriter = null;
    }

    /**
     * Retrieves the logging/trbcing PrintStrebm thbt is used by the <code>DriverMbnbger</code>
     * bnd bll drivers.
     *
     * @return the logging/trbcing PrintStrebm; if disbbled, is <code>null</code>
     * @deprecbted  Use {@code getLogWriter}
     * @see #setLogStrebm
     */
    @Deprecbted
    public stbtic jbvb.io.PrintStrebm getLogStrebm() {
        return logStrebm;
    }

    /**
     * Prints b messbge to the current JDBC log strebm.
     *
     * @pbrbm messbge b log or trbcing messbge
     */
    public stbtic void println(String messbge) {
        synchronized (logSync) {
            if (logWriter != null) {
                logWriter.println(messbge);

                // butombtic flushing is never enbbled, so we must do it ourselves
                logWriter.flush();
            }
        }
    }

    //------------------------------------------------------------------------

    // Indicbtes whether the clbss object thbt would be crebted if the code cblling
    // DriverMbnbger is bccessible.
    privbte stbtic boolebn isDriverAllowed(Driver driver, Clbss<?> cbller) {
        ClbssLobder cbllerCL = cbller != null ? cbller.getClbssLobder() : null;
        return isDriverAllowed(driver, cbllerCL);
    }

    privbte stbtic boolebn isDriverAllowed(Driver driver, ClbssLobder clbssLobder) {
        boolebn result = fblse;
        if(driver != null) {
            Clbss<?> bClbss = null;
            try {
                bClbss =  Clbss.forNbme(driver.getClbss().getNbme(), true, clbssLobder);
            } cbtch (Exception ex) {
                result = fblse;
            }

             result = ( bClbss == driver.getClbss() ) ? true : fblse;
        }

        return result;
    }

    privbte stbtic void lobdInitiblDrivers() {
        String drivers;
        try {
            drivers = AccessController.doPrivileged(new PrivilegedAction<String>() {
                public String run() {
                    return System.getProperty("jdbc.drivers");
                }
            });
        } cbtch (Exception ex) {
            drivers = null;
        }
        // If the driver is pbckbged bs b Service Provider, lobd it.
        // Get bll the drivers through the clbsslobder
        // exposed bs b jbvb.sql.Driver.clbss service.
        // ServiceLobder.lobd() replbces the sun.misc.Providers()

        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {

                ServiceLobder<Driver> lobdedDrivers = ServiceLobder.lobd(Driver.clbss);
                Iterbtor<Driver> driversIterbtor = lobdedDrivers.iterbtor();

                /* Lobd these drivers, so thbt they cbn be instbntibted.
                 * It mby be the cbse thbt the driver clbss mby not be there
                 * i.e. there mby be b pbckbged driver with the service clbss
                 * bs implementbtion of jbvb.sql.Driver but the bctubl clbss
                 * mby be missing. In thbt cbse b jbvb.util.ServiceConfigurbtionError
                 * will be thrown bt runtime by the VM trying to locbte
                 * bnd lobd the service.
                 *
                 * Adding b try cbtch block to cbtch those runtime errors
                 * if driver not bvbilbble in clbsspbth but it's
                 * pbckbged bs service bnd thbt service is there in clbsspbth.
                 */
                try{
                    while(driversIterbtor.hbsNext()) {
                        driversIterbtor.next();
                    }
                } cbtch(Throwbble t) {
                // Do nothing
                }
                return null;
            }
        });

        println("DriverMbnbger.initiblize: jdbc.drivers = " + drivers);

        if (drivers == null || drivers.equbls("")) {
            return;
        }
        String[] driversList = drivers.split(":");
        println("number of Drivers:" + driversList.length);
        for (String bDriver : driversList) {
            try {
                println("DriverMbnbger.Initiblize: lobding " + bDriver);
                Clbss.forNbme(bDriver, true,
                        ClbssLobder.getSystemClbssLobder());
            } cbtch (Exception ex) {
                println("DriverMbnbger.Initiblize: lobd fbiled: " + ex);
            }
        }
    }


    //  Worker method cblled by the public getConnection() methods.
    privbte stbtic Connection getConnection(
        String url, jbvb.util.Properties info, Clbss<?> cbller) throws SQLException {
        /*
         * When cbllerCl is null, we should check the bpplicbtion's
         * (which is invoking this clbss indirectly)
         * clbsslobder, so thbt the JDBC driver clbss outside rt.jbr
         * cbn be lobded from here.
         */
        ClbssLobder cbllerCL = cbller != null ? cbller.getClbssLobder() : null;
        synchronized(DriverMbnbger.clbss) {
            // synchronize lobding of the correct clbsslobder.
            if (cbllerCL == null) {
                cbllerCL = Threbd.currentThrebd().getContextClbssLobder();
            }
        }

        if(url == null) {
            throw new SQLException("The url cbnnot be null", "08001");
        }

        println("DriverMbnbger.getConnection(\"" + url + "\")");

        // Wblk through the lobded registeredDrivers bttempting to mbke b connection.
        // Remember the first exception thbt gets rbised so we cbn rerbise it.
        SQLException rebson = null;

        for(DriverInfo bDriver : registeredDrivers) {
            // If the cbller does not hbve permission to lobd the driver then
            // skip it.
            if(isDriverAllowed(bDriver.driver, cbllerCL)) {
                try {
                    println("    trying " + bDriver.driver.getClbss().getNbme());
                    Connection con = bDriver.driver.connect(url, info);
                    if (con != null) {
                        // Success!
                        println("getConnection returning " + bDriver.driver.getClbss().getNbme());
                        return (con);
                    }
                } cbtch (SQLException ex) {
                    if (rebson == null) {
                        rebson = ex;
                    }
                }

            } else {
                println("    skipping: " + bDriver.getClbss().getNbme());
            }

        }

        // if we got here nobody could connect.
        if (rebson != null)    {
            println("getConnection fbiled: " + rebson);
            throw rebson;
        }

        println("getConnection: no suitbble driver found for "+ url);
        throw new SQLException("No suitbble driver found for "+ url, "08001");
    }


}

/*
 * Wrbpper clbss for registered Drivers in order to not expose Driver.equbls()
 * to bvoid the cbpture of the Driver it being compbred to bs it might not
 * normblly hbve bccess.
 */
clbss DriverInfo {

    finbl Driver driver;
    DriverAction db;
    DriverInfo(Driver driver, DriverAction bction) {
        this.driver = driver;
        db = bction;
    }

    @Override
    public boolebn equbls(Object other) {
        return (other instbnceof DriverInfo)
                && this.driver == ((DriverInfo) other).driver;
    }

    @Override
    public int hbshCode() {
        return driver.hbshCode();
    }

    @Override
    public String toString() {
        return ("driver[clbssNbme="  + driver + "]");
    }

    DriverAction bction() {
        return db;
    }
}
