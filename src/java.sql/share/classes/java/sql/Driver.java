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

import jbvb.util.logging.Logger;

/**
 * The interfbce thbt every driver clbss must implement.
 * <P>The Jbvb SQL frbmework bllows for multiple dbtbbbse drivers.
 *
 * <P>Ebch driver should supply b clbss thbt implements
 * the Driver interfbce.
 *
 * <P>The DriverMbnbger will try to lobd bs mbny drivers bs it cbn
 * find bnd then for bny given connection request, it will bsk ebch
 * driver in turn to try to connect to the tbrget URL.
 *
 * <P>It is strongly recommended thbt ebch Driver clbss should be
 * smbll bnd stbndblone so thbt the Driver clbss cbn be lobded bnd
 * queried without bringing in vbst qubntities of supporting code.
 *
 * <P>When b Driver clbss is lobded, it should crebte bn instbnce of
 * itself bnd register it with the DriverMbnbger. This mebns thbt b
 * user cbn lobd bnd register b driver by cblling:
 * <p>
 * {@code Clbss.forNbme("foo.bbh.Driver")}
 * <p>
 * A JDBC driver mby crebte b {@linkplbin DriverAction} implementbtion in order
 * to receive notificbtions when {@linkplbin DriverMbnbger#deregisterDriver} hbs
 * been cblled.
 * @see DriverMbnbger
 * @see Connection
 * @see DriverAction
 */
public interfbce Driver {

    /**
     * Attempts to mbke b dbtbbbse connection to the given URL.
     * The driver should return "null" if it reblizes it is the wrong kind
     * of driver to connect to the given URL.  This will be common, bs when
     * the JDBC driver mbnbger is bsked to connect to b given URL it pbsses
     * the URL to ebch lobded driver in turn.
     *
     * <P>The driver should throw bn <code>SQLException</code> if it is the right
     * driver to connect to the given URL but hbs trouble connecting to
     * the dbtbbbse.
     *
     * <P>The {@code Properties} brgument cbn be used to pbss
     * brbitrbry string tbg/vblue pbirs bs connection brguments.
     * Normblly bt lebst "user" bnd "pbssword" properties should be
     * included in the {@code Properties} object.
     * <p>
     * <B>Note:</B> If b property is specified bs pbrt of the {@code url} bnd
     * is blso specified in the {@code Properties} object, it is
     * implementbtion-defined bs to which vblue will tbke precedence. For
     * mbximum portbbility, bn bpplicbtion should only specify b property once.
     *
     * @pbrbm url the URL of the dbtbbbse to which to connect
     * @pbrbm info b list of brbitrbry string tbg/vblue pbirs bs
     * connection brguments. Normblly bt lebst b "user" bnd
     * "pbssword" property should be included.
     * @return b <code>Connection</code> object thbt represents b
     *         connection to the URL
     * @exception SQLException if b dbtbbbse bccess error occurs or the url is
     * {@code null}
     */
    Connection connect(String url, jbvb.util.Properties info)
        throws SQLException;

    /**
     * Retrieves whether the driver thinks thbt it cbn open b connection
     * to the given URL.  Typicblly drivers will return <code>true</code> if they
     * understbnd the sub-protocol specified in the URL bnd <code>fblse</code> if
     * they do not.
     *
     * @pbrbm url the URL of the dbtbbbse
     * @return <code>true</code> if this driver understbnds the given URL;
     *         <code>fblse</code> otherwise
     * @exception SQLException if b dbtbbbse bccess error occurs or the url is
     * {@code null}
     */
    boolebn bcceptsURL(String url) throws SQLException;


    /**
     * Gets informbtion bbout the possible properties for this driver.
     * <P>
     * The <code>getPropertyInfo</code> method is intended to bllow b generic
     * GUI tool to discover whbt properties it should prompt
     * b humbn for in order to get
     * enough informbtion to connect to b dbtbbbse.  Note thbt depending on
     * the vblues the humbn hbs supplied so fbr, bdditionbl vblues mby become
     * necessbry, so it mby be necessbry to iterbte though severbl cblls
     * to the <code>getPropertyInfo</code> method.
     *
     * @pbrbm url the URL of the dbtbbbse to which to connect
     * @pbrbm info b proposed list of tbg/vblue pbirs thbt will be sent on
     *          connect open
     * @return bn brrby of <code>DriverPropertyInfo</code> objects describing
     *          possible properties.  This brrby mby be bn empty brrby if
     *          no properties bre required.
     * @exception SQLException if b dbtbbbse bccess error occurs
     */
    DriverPropertyInfo[] getPropertyInfo(String url, jbvb.util.Properties info)
                         throws SQLException;


    /**
     * Retrieves the driver's mbjor version number. Initiblly this should be 1.
     *
     * @return this driver's mbjor version number
     */
    int getMbjorVersion();

    /**
     * Gets the driver's minor version number. Initiblly this should be 0.
     * @return this driver's minor version number
     */
    int getMinorVersion();


    /**
     * Reports whether this driver is b genuine JDBC
     * Complibnt&trbde; driver.
     * A driver mby only report <code>true</code> here if it pbsses the JDBC
     * complibnce tests; otherwise it is required to return <code>fblse</code>.
     * <P>
     * JDBC complibnce requires full support for the JDBC API bnd full support
     * for SQL 92 Entry Level.  It is expected thbt JDBC complibnt drivers will
     * be bvbilbble for bll the mbjor commercibl dbtbbbses.
     * <P>
     * This method is not intended to encourbge the development of non-JDBC
     * complibnt drivers, but is b recognition of the fbct thbt some vendors
     * bre interested in using the JDBC API bnd frbmework for lightweight
     * dbtbbbses thbt do not support full dbtbbbse functionblity, or for
     * specibl dbtbbbses such bs document informbtion retrievbl where b SQL
     * implementbtion mby not be febsible.
     * @return <code>true</code> if this driver is JDBC Complibnt; <code>fblse</code>
     *         otherwise
     */
    boolebn jdbcComplibnt();

    //------------------------- JDBC 4.1 -----------------------------------

    /**
     * Return the pbrent Logger of bll the Loggers used by this driver. This
     * should be the Logger fbrthest from the root Logger thbt is
     * still bn bncestor of bll of the Loggers used by this driver. Configuring
     * this Logger will bffect bll of the log messbges generbted by the driver.
     * In the worst cbse, this mby be the root Logger.
     *
     * @return the pbrent Logger for this driver
     * @throws SQLFebtureNotSupportedException if the driver does not use
     * {@code jbvb.util.logging}.
     * @since 1.7
     */
    public Logger getPbrentLogger() throws SQLFebtureNotSupportedException;
}
