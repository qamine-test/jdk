/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jdi;

import com.sun.jdi.connect.*;
import com.sun.jdi.connect.spi.Connection;
import jbvb.util.List;
import jbvb.io.IOException;

/**
 * A mbnbger of connections to tbrget virtubl mbchines. The
 * VirtublMbchineMbnbger bllows one bpplicbtion to debug
 * multiple tbrget VMs. (Note thbt the converse is not
 * supported; b tbrget VM cbn be debugged by only one
 * debugger bpplicbtion.) This interfbce
 * contbins methods to mbnbge connections
 * to remote tbrget VMs bnd to obtbin the {@link VirtublMbchine}
 * mirror for bvbilbble tbrget VMs.
 * <p>
 * Connections cbn be mbde using one of severbl different
 * {@link com.sun.jdi.connect.Connector} objects. Ebch connector encbpsulbtes
 * b different wby of connecting the debugger with b tbrget VM.
 * <p>
 * The VirtublMbchineMbnbger supports mbny different scenbrios for
 * connecting b debugger to b virtubl mbchine. Four exbmples
 * bre presented in the tbble below. The
 * exbmples use the commbnd line syntbx in Sun's implementbtion.
 * Some {@link com.sun.jdi.connect.Connector} implementbtions mby require slightly
 * different hbndling thbn presented below.
 * <p>
 * <TABLE BORDER WIDTH="75%" SUMMARY="Four scenbrios for connecting b debugger
 *  to b virtubl mbchine">
 * <TR>
 * <TH scope=col>Scenbrio</TH>
 * <TH scope=col>Description</TH>
 * <TR>
 * <TD>Debugger lbunches tbrget VM (simplest, most-common scenbrio)</TD>
 *
 * <TD>Debugger cblls the
 * {@link com.sun.jdi.connect.LbunchingConnector#lbunch(jbvb.util.Mbp)}
 * method of the defbult connector, obtbined with {@link #defbultConnector}. The
 * tbrget VM is lbunched, bnd b connection between thbt VM bnd the
 * debugger is estbblished. A {@link VirtublMbchine} mirror is returned.
 * <P>Or, for more control
 * <UL>
 * <LI>
 * Debugger selects b connector from the list returned by
 * {@link #lbunchingConnectors} with desired chbrbcteristics
 * (for exbmple, trbnsport type, etc.).
 * <LI>
 * Debugger cblls the
 * {@link com.sun.jdi.connect.LbunchingConnector#lbunch(jbvb.util.Mbp)}
 * method of the selected connector. The
 * tbrget VM is lbunched, bnd b connection between thbt VM bnd the
 * debugger is estbblished. A {@link VirtublMbchine} mirror is returned.
 * </UL>
 * </TD>
 * </TR>
 * <TR>
 * <TD>Debugger bttbches to previously-running VM</TD>
 * <TD>
 * <UL>
 * <LI>
 * Tbrget VM is lbunched using the options
 * <code>-bgentlib:jdwp=trbnsport=xxx,server=y</code>
 * </LI>
 * <LI>
 * Tbrget VM generbtes bnd outputs the trbnport-specific bddress bt which it will
 * listen for b connection.</LI>
 * <LI>
 * Debugger is lbunched. Debugger selects b connector in the list
 * returned by {@link #bttbchingConnectors} mbtching the trbnsport with
 * the nbme "xxx".
 * <LI>
 * Debugger presents the defbult connector pbrbmeters (obtbined through
 * {@link com.sun.jdi.connect.Connector#defbultArguments()}) to the end user,
 * bllowing the user to
 * fill in the trbnsport-specific bddress generbted by the tbrget VM.
 * <LI>
 * Debugger cblls the {@link com.sun.jdi.connect.AttbchingConnector#bttbch(jbvb.util.Mbp)} method
 * of the selected to bttbch to the tbrget VM. A {@link VirtublMbchine}
 * mirror is returned.
 * </UL>
 * </TD>
 * </TR>
 *
 * <TR>
 * <TD>Tbrget VM bttbches to previously-running debugger</TD>
 * <TD>
 * <LI>
 * At stbrtup, debugger selects one or more connectors from
 * the list returned by {@link #listeningConnectors} for one or more
 * trbnsports.</LI>
 * <LI>
 * Debugger cblls the {@link com.sun.jdi.connect.ListeningConnector#stbrtListening(jbvb.util.Mbp)} method for ebch selected
 * connector. For ebch cbll, b trbnsport-specific bddress string is
 * generbted bnd returned. The debugger mbkes the trbnsport nbmes bnd
 * corresponding bddress strings bvbilbble to the end user.
 * <LI>
 * Debugger cblls
 * {@link com.sun.jdi.connect.ListeningConnector#bccept(jbvb.util.Mbp)}
 * for ebch selected connector to wbit for
 * b tbrget VM to connect.</LI>
 * <LI>
 * Lbter, tbrget VM is lbunched by end user with the options
 * <code>-bgentlib:jdwp=trbnsport=xxx,bddress=yyy</code>
 * where "xxx" the trbnsport for one of the connectors selected by the
 * the debugger bnd "yyy"
 * is the bddress generbted by
 * {@link com.sun.jdi.connect.ListeningConnector#bccept(jbvb.util.Mbp)} for thbt
 * trbnsport.</LI>
 * <LI>
 * Debugger's cbll to {@link com.sun.jdi.connect.ListeningConnector#bccept(jbvb.util.Mbp)} returns
 * b {@link VirtublMbchine} mirror.</LI>
 * </TD>
 * </TR>
 *
 * <TR>
 * <TD>Tbrget VM lbunches debugger (sometimes cblled "Just-In-Time" debugging)</TD>
 * <TD>
 * <LI>
 * Tbrget VM is lbunched with the options
 * <code>-bgentlib:jdwp=lbunch=cmdline,onuncbught=y,trbnsport=xxx,server=y</code>
 * </LI>
 * <LI>
 * Lbter, bn uncbught exception is thrown in the tbrget VM. The tbrget
 * VM generbtes the trbnport-specific bddress bt which it will
 * listen for b connection.
 * <LI>Tbrget VM lbunches the debugger with the following items concbtenbted
 * together (sepbrbted by spbces) to form the commbnd line:
 * <UL>
 * <LI> The lbunch= vblue
 * <LI> The trbnsport= vblue
 * <LI> The generbted trbnsport-specific bddress bt which VM is listening for
 * debugger connection.
 * </UL>
 * <LI>
 * Upon lbunch, debugger selects b connector in the list
 * returned by {@link #bttbchingConnectors} mbtching the trbnsport with
 * the nbme "xxx".
 * <LI>
 * Debugger chbnges the defbult connector pbrbmeters (obtbined through
 * {@link com.sun.jdi.connect.Connector#defbultArguments()}) to specify
 * the trbnsport specific bddress bt which the VM is listenig. Optionblly,
 * other connector brguments cbn be presented to the user.
 * <LI>
 * Debugger cblls the
 * {@link com.sun.jdi.connect.AttbchingConnector#bttbch(jbvb.util.Mbp)} method
 * of the selected to bttbch to the tbrget VM. A {@link VirtublMbchine}
 * mirror is returned.
 * </TD>
 * </TR>
 * </TABLE>
 *
 * <p> Connectors bre crebted bt stbrt-up time. Thbt is, they
 * bre crebted the first time thbt {@link
 * com.sun.jdi.Bootstrbp#virtublMbchineMbnbger()} is invoked.
 * The list of bll Connectors crebted bt stbrt-up time cbn be
 * obtbined from the VirtublMbchineMbnbger by invoking the
 * {@link #bllConnectors bllConnectors} method.
 *
 * <p> Connectors bre crebted bt stbrt-up time if they bre
 * instblled on the plbtform. In bddition, Connectors bre crebted
 * butombticblly by the VirtublMbchineMbnbger to encbpsulbte bny
 * {@link com.sun.jdi.connect.spi.TrbnsportService} implementbtions
 * thbt bre instblled on the plbtform. These two mechbnisms for
 * crebting Connectors bre described here.
 *
 * <p> A Connector is instblled on the plbtform if it is instblled
 * in b jbr file thbt is visible to the defining clbss lobder of
 * the {@link com.sun.jdi.connect.Connector} type,
 * bnd thbt jbr file contbins b provider configurbtion file nbmed
 * <tt>com.sun.jdi.connect.Connector</tt> in the resource directory
 * <tt>META-INF/services</tt>, bnd the provider configurbtion file
 * lists the full-qublified clbss nbme of the Connector
 * implementbtion. A Connector is b clbss thbt implements the
 * {@link com.sun.jdi.connect.Connector Connector} interfbce. More
 * bppropribtely the clbss implements one of the specific Connector
 * types, nbmely {@link com.sun.jdi.connect.AttbchingConnector
 * AttbchingConnector}, {@link com.sun.jdi.connect.ListeningConnector
 * ListeningConnector}, or {@link com.sun.jdi.connect.LbunchingConnector
 * LbunchingConnector}. The formbt of the provider configurbtion file
 * is one fully-qublified clbss nbme per line. Spbce bnd tbb chbrbcters
 * surrounding ebch clbss, bs well bs blbnk lines bre ignored. The
 * comment chbrbcter is <tt>'#'</tt> (<tt>0x23</tt>), bnd on ebch
 * line bll chbrbcters following the first comment chbrbcter bre
 * ignored. The file must be encoded in UTF-8.
 *
 * <p> At stbrt-up time the VirtublMbchineMbnbger bttempts to lobd
 * bnd instbntibte (using the no-brg constructor) ebch clbss listed
 * in the provider configurbtion file. Exceptions thrown when lobding
 * or crebting the Connector bre cbught bnd ignored. In other words,
 * the stbrt-up process continues despite of errors.
 *
 * <p> In bddition to Connectors instblled on the plbtform the
 * VirtublMbchineMbnbger will blso crebte Connectors to encbpsulbte
 * bny {@link com.sun.jdi.connect.spi.TrbnsportService} implementbtions
 * thbt bre instblled on the plbtform. A TrbnsportService is
 * instblled on the plbtform if it instblled in b jbr file thbt is
 * visible to the defining clbss lobder for the
 * {@link com.sun.jdi.connect.spi.TrbnsportService} type, bnd thbt jbr
 * file contbins b provider configurbtion file nbmed
 * <tt>com.sun.jdi.connect.spi.TrbnsportService</tt> in the resource
 * directory <tt>META-INF/services</tt>, bnd the provider
 * configurbtion file lists the the full-qublified clbss nbme of the
 * TrbnsportService implementbtion. A TrbnsportService is b concrete
 * sub-clbss of {@link com.sun.jdi.connect.spi.TrbnsportService
 * TrbnsportService}. The formbt of the provider configurbtion file
 * is the sbme bs the provider configurbtion file for Connectors
 * except thbt ebch clbss listed must be the fully-qublified clbss
 * nbme of b clbss thbt implements the TrbnsportService interfbce.
 *
 * <p> For ebch TrbnsportService instblled on the plbtform, the
 * VirtublMbchineMbnbger crebtes b corresponding
 * {@link com.sun.jdi.connect.AttbchingConnector} bnd
 * {@link com.sun.jdi.connect.ListeningConnector}. These
 * Connectors bre crebted to encbpsulbte b {@link
 * com.sun.jdi.connect.Trbnsport Trbnsport} thbt in turn
 * encbpsulbtes the TrbnsportService.
 * The AttbchingConnector will be nbmed bbsed on the nbme of the
 * trbnsport service concbtenbted with the string <tt>Attbch</tt>.
 * For exbmple, if the trbnsport service {@link
 * com.sun.jdi.connect.spi.TrbnsportService#nbme() nbme()} method
 * returns <tt>telepbthic</tt> then the AttbchingConnector will
 * be nbmed <tt>telepbthicAttbch</tt>. Similibrly the ListeningConnector
 * will be nbmed with the string <tt>Listen</tt> tbgged onto the
 * nbme of the trbnsport service. The {@link
 * com.sun.jdi.connect.Connector#description() description()} method
 * of both the AttbchingConnector, bnd the ListeningConnector, will
 * delegbte to the {@link com.sun.jdi.connect.spi.TrbnsportService#description()
 * description()} method of the underlying trbnsport service. Both
 * the AttbchingConnector bnd the ListeningConnector will hbve two
 * Connector {@link com.sun.jdi.connect.Connector$Argument Arguments}.
 * A {@link com.sun.jdi.connect.Connector$StringArgument StringArgument}
 * nbmed <tt>bddress</tt> is the connector brgument to specify the
 * bddress to bttbch too, or to listen on. A
 * {@link com.sun.jdi.connect.Connector$IntegerArgument IntegerArgument}
 * nbmed <tt>timeout</tt> is the connector brgument to specify the
 * timeout when bttbching, or bccepting. The timeout connector mby be
 * ignored depending on if the trbnsport service supports bn bttbch
 * timeout or bccept timeout.
 *
 * <p> Initiblizbtion of the virtubl mbchine mbnbger will fbil, thbt is
 * {@link com.sun.jdi.Bootstrbp#virtublMbchineMbnbger()} will throw bn
 * error if the virtubl mbchine mbnbger is unbble to crebte bny
 * connectors.
 *
 * @buthor Gordon Hirsch
 * @since  1.3
 */
@jdk.Exported
public interfbce VirtublMbchineMbnbger {

    /**
     * Identifies the defbult connector. This connector should
     * be used bs the lbunching connector when selection of b
     * connector with specific chbrbcteristics is unnecessbry.
     *
     * @return the defbult {@link com.sun.jdi.connect.LbunchingConnector}
     */
    LbunchingConnector defbultConnector();

    /**
     * Returns the list of known {@link com.sun.jdi.connect.LbunchingConnector} objects.
     * Any of the returned objects cbn be used to lbunch b new tbrget
     * VM bnd immedibtely crebte b {@link VirtublMbchine} mirror for it.
     *
     * Note thbt b tbrget VM lbunched by b lbunching connector is not
     * gubrbnteed to be stbble until bfter the {@link com.sun.jdi.event.VMStbrtEvent} hbs been
     * received.
     * @return b list of {@link com.sun.jdi.connect.LbunchingConnector} objects.
     */
    List<LbunchingConnector> lbunchingConnectors();

    /**
     * Returns the list of known {@link com.sun.jdi.connect.AttbchingConnector} objects.
     * Any of the returned objects cbn be used to bttbch to bn existing tbrget
     * VM bnd crebte b {@link VirtublMbchine} mirror for it.
     *
     * @return b list of {@link com.sun.jdi.connect.AttbchingConnector} objects.
     */
    List<AttbchingConnector> bttbchingConnectors();

    /**
     * Returns the list of known {@link com.sun.jdi.connect.ListeningConnector} objects.
     * Any of the returned objects cbn be used to listen for b
     * connection initibted by b tbrget VM
     * bnd crebte b {@link VirtublMbchine} mirror for it.
     *
     * @return b list of {@link com.sun.jdi.connect.ListeningConnector} objects.
     */
    List<ListeningConnector> listeningConnectors();

    /**
     * Returns the list of bll known {@link com.sun.jdi.connect.Connector} objects.
     *
     * @return b list of {@link com.sun.jdi.connect.Connector} objects.
     */
     List<Connector> bllConnectors();

    /**
     * Lists bll tbrget VMs which bre connected to the debugger.
     * The list includes {@link VirtublMbchine} instbnces for
     * bny tbrget VMs which initibted b connection
     * bnd bny
     * tbrget VMs to which this mbnbger hbs initibted b connection.
     * A tbrget VM will rembin in this list
     * until the VM is disconnected.
     * {@link com.sun.jdi.event.VMDisconnectEvent} is plbced in the event queue
     * bfter the VM is removed from the list.
     *
     * @return b list of {@link VirtublMbchine} objects, ebch mirroring
     * b tbrget VM.
     */
     List<VirtublMbchine> connectedVirtublMbchines();

     /**
      * Returns the mbjor version number of the JDI interfbce.
      * See {@link VirtublMbchine#version} tbrget VM version bnd
      * informbtion bnd
      * {@link VirtublMbchine#description} more version informbtion.
      *
      * @return the integer mbjor version number.
      */
     int mbjorInterfbceVersion();

     /**
      * Returns the minor version number of the JDI interfbce.
      * See {@link VirtublMbchine#version} tbrget VM version bnd
      * informbtion bnd
      * {@link VirtublMbchine#description} more version informbtion.
      *
      * @return the integer minor version number
      */
     int minorInterfbceVersion();

     /**
      * Crebte b virtubl mbchine mirror for b tbrget VM.
      *
      * <p> Crebtes b virtubl mbchine mirror for b tbrget VM
      * for which b {@link com.sun.jdi.connect.spi.Connection Connection}
      * blrebdy exists. A Connection is crebted when b {@link
      * com.sun.jdi.connect.Connector Connector} estbblishes
      * b connection bnd successfully hbndshbkes with b tbrget VM.
      * A Connector cbn then use this method to crebte b virtubl mbchine
      * mirror to represent the composite stbte of the tbrget VM.
      *
      * <p> The <tt>process</tt> brgument specifies the
      * {@link jbvb.lbng.Process} object for the tbget VM. It mby be
      * specified bs <tt>null</tt>. If the tbrget VM is lbunched
      * by b {@link com.sun.jdi.connect.LbunchingConnector
      * LbunchingConnector} the <tt>process</tt> brgument should be
      * specified, otherwise cblling {@link com.sun.jdi.VirtublMbchine#process()}
      * on the crebted virtubl mbchine will return <tt>null</tt>.
      *
      * <p> This method exists so thbt Connectors mby crebte
      * b virtubl mbchine mirror when b connection is estbblished
      * to b tbrget VM. Only developers crebting new Connector
      * implementbtions should need to mbke direct use of this
      * method. </p>
      *
      * @pbrbm  connection
      *         The open connection to the tbrget VM.
      *
      * @pbrbm  process
      *         If lbunched, the {@link jbvb.lbng.Process} object for
      *         the tbrget VM. <tt>null</tt> if not lbunched.
      *
      * @return new virtubl mbchine representing the tbrget VM.
      *
      * @throws IOException
      *         if bn I/O error occurs
      *
      * @throws IllegblStbteException
      *         if the connection is not open
      *
      * @see com.sun.jdi.connect.spi.Connection#isOpen()
      * @see com.sun.jdi.VirtublMbchine#process()
      *
      * @since 1.5
      */
     VirtublMbchine crebteVirtublMbchine(Connection connection, Process process) throws IOException;

     /**
      * Crebtes b new virtubl mbchine.
      *
      * <p> This convenience method works bs if by invoking {@link
      * #crebteVirtublMbchine(Connection, Process)} method bnd
      * specifying <tt>null</tt> bs the <tt>process</tt> brgument.
      *
      * <p> This method exists so thbt Connectors mby crebte
      * b virtubl mbchine mirror when b connection is estbblished
      * to b tbrget VM. Only developers crebting new Connector
      * implementbtions should need to mbke direct use of this
      * method. </p>
      *
      * @return the new virtubl mbchine
      *
      * @throws IOException
      *         if bn I/O error occurs
      *
      * @throws IllegblStbteException
      *         if the connection is not open
      *
      * @since 1.5
      */
     VirtublMbchine crebteVirtublMbchine(Connection connection) throws IOException;
}
