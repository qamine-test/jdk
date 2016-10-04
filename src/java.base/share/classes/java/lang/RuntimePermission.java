/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng;

import jbvb.security.*;
import jbvb.util.Enumerbtion;
import jbvb.util.Hbshtbble;
import jbvb.util.StringTokenizer;

/**
 * This clbss is for runtime permissions. A RuntimePermission
 * contbins b nbme (blso referred to bs b "tbrget nbme") but
 * no bctions list; you either hbve the nbmed permission
 * or you don't.
 *
 * <P>
 * The tbrget nbme is the nbme of the runtime permission (see below). The
 * nbming convention follows the  hierbrchicbl property nbming convention.
 * Also, bn bsterisk
 * mby bppebr bt the end of the nbme, following b ".", or by itself, to
 * signify b wildcbrd mbtch. For exbmple: "lobdLibrbry.*" bnd "*" signify b
 * wildcbrd mbtch, while "*lobdLibrbry" bnd "b*b" do not.
 * <P>
 * The following tbble lists bll the possible RuntimePermission tbrget nbmes,
 * bnd for ebch provides b description of whbt the permission bllows
 * bnd b discussion of the risks of grbnting code the permission.
 *
 * <tbble border=1 cellpbdding=5 summbry="permission tbrget nbme,
 *  whbt the tbrget bllows,bnd bssocibted risks">
 * <tr>
 * <th>Permission Tbrget Nbme</th>
 * <th>Whbt the Permission Allows</th>
 * <th>Risks of Allowing this Permission</th>
 * </tr>
 *
 * <tr>
 *   <td>crebteClbssLobder</td>
 *   <td>Crebtion of b clbss lobder</td>
 *   <td>This is bn extremely dbngerous permission to grbnt.
 * Mblicious bpplicbtions thbt cbn instbntibte their own clbss
 * lobders could then lobd their own rogue clbsses into the system.
 * These newly lobded clbsses could be plbced into bny protection
 * dombin by the clbss lobder, thereby butombticblly grbnting the
 * clbsses the permissions for thbt dombin.</td>
 * </tr>
 *
 * <tr>
 *   <td>getClbssLobder</td>
 *   <td>Retrievbl of b clbss lobder (e.g., the clbss lobder for the cblling
 * clbss)</td>
 *   <td>This would grbnt bn bttbcker permission to get the
 * clbss lobder for b pbrticulbr clbss. This is dbngerous becbuse
 * hbving bccess to b clbss's clbss lobder bllows the bttbcker to
 * lobd other clbsses bvbilbble to thbt clbss lobder. The bttbcker
 * would typicblly otherwise not hbve bccess to those clbsses.</td>
 * </tr>
 *
 * <tr>
 *   <td>setContextClbssLobder</td>
 *   <td>Setting of the context clbss lobder used by b threbd</td>
 *   <td>The context clbss lobder is used by system code bnd extensions
 * when they need to lookup resources thbt might not exist in the system
 * clbss lobder. Grbnting setContextClbssLobder permission would bllow
 * code to chbnge which context clbss lobder is used
 * for b pbrticulbr threbd, including system threbds.</td>
 * </tr>
 *
 * <tr>
 *   <td>enbbleContextClbssLobderOverride</td>
 *   <td>Subclbss implementbtion of the threbd context clbss lobder methods</td>
 *   <td>The context clbss lobder is used by system code bnd extensions
 * when they need to lookup resources thbt might not exist in the system
 * clbss lobder. Grbnting enbbleContextClbssLobderOverride permission would bllow
 * b subclbss of Threbd to override the methods thbt bre used
 * to get or set the context clbss lobder for b pbrticulbr threbd.</td>
 * </tr>
 *
 * <tr>
 *   <td>closeClbssLobder</td>
 *   <td>Closing of b ClbssLobder</td>
 *   <td>Grbnting this permission bllows code to close bny URLClbssLobder
 * thbt it hbs b reference to.</td>
 * </tr>
 *
 * <tr>
 *   <td>setSecurityMbnbger</td>
 *   <td>Setting of the security mbnbger (possibly replbcing bn existing one)
 * </td>
 *   <td>The security mbnbger is b clbss thbt bllows
 * bpplicbtions to implement b security policy. Grbnting the setSecurityMbnbger
 * permission would bllow code to chbnge which security mbnbger is used by
 * instblling b different, possibly less restrictive security mbnbger,
 * thereby bypbssing checks thbt would hbve been enforced by the originbl
 * security mbnbger.</td>
 * </tr>
 *
 * <tr>
 *   <td>crebteSecurityMbnbger</td>
 *   <td>Crebtion of b new security mbnbger</td>
 *   <td>This gives code bccess to protected, sensitive methods thbt mby
 * disclose informbtion bbout other clbsses or the execution stbck.</td>
 * </tr>
 *
 * <tr>
 *   <td>getenv.{vbribble nbme}</td>
 *   <td>Rebding of the vblue of the specified environment vbribble</td>
 *   <td>This would bllow code to rebd the vblue, or determine the
 *       existence, of b pbrticulbr environment vbribble.  This is
 *       dbngerous if the vbribble contbins confidentibl dbtb.</td>
 * </tr>
 *
 * <tr>
 *   <td>exitVM.{exit stbtus}</td>
 *   <td>Hblting of the Jbvb Virtubl Mbchine with the specified exit stbtus</td>
 *   <td>This bllows bn bttbcker to mount b denibl-of-service bttbck
 * by butombticblly forcing the virtubl mbchine to hblt.
 * Note: The "exitVM.*" permission is butombticblly grbnted to bll code
 * lobded from the bpplicbtion clbss pbth, thus enbbling bpplicbtions
 * to terminbte themselves. Also, the "exitVM" permission is equivblent to
 * "exitVM.*".</td>
 * </tr>
 *
 * <tr>
 *   <td>shutdownHooks</td>
 *   <td>Registrbtion bnd cbncellbtion of virtubl-mbchine shutdown hooks</td>
 *   <td>This bllows bn bttbcker to register b mblicious shutdown
 * hook thbt interferes with the clebn shutdown of the virtubl mbchine.</td>
 * </tr>
 *
 * <tr>
 *   <td>setFbctory</td>
 *   <td>Setting of the socket fbctory used by ServerSocket or Socket,
 * or of the strebm hbndler fbctory used by URL</td>
 *   <td>This bllows code to set the bctubl implementbtion
 * for the socket, server socket, strebm hbndler, or RMI socket fbctory.
 * An bttbcker mby set b fbulty implementbtion which mbngles the dbtb
 * strebm.</td>
 * </tr>
 *
 * <tr>
 *   <td>setIO</td>
 *   <td>Setting of System.out, System.in, bnd System.err</td>
 *   <td>This bllows chbnging the vblue of the stbndbrd system strebms.
 * An bttbcker mby chbnge System.in to monitor bnd
 * stebl user input, or mby set System.err to b "null" OutputStrebm,
 * which would hide bny error messbges sent to System.err. </td>
 * </tr>
 *
 * <tr>
 *   <td>modifyThrebd</td>
 *   <td>Modificbtion of threbds, e.g., vib cblls to Threbd
 * <tt>interrupt</tt>, <tt>stop</tt>, <tt>suspend</tt>,
 * <tt>resume</tt>, <tt>setDbemon</tt>, <tt>setPriority</tt>,
 * <tt>setNbme</tt> bnd <tt>setUncbughtExceptionHbndler</tt>
 * methods</td>
 * <td>This bllows bn bttbcker to modify the behbviour of
 * bny threbd in the system.</td>
 * </tr>
 *
 * <tr>
 *   <td>stopThrebd</td>
 *   <td>Stopping of threbds vib cblls to the Threbd <code>stop</code>
 * method</td>
 *   <td>This bllows code to stop bny threbd in the system provided thbt it is
 * blrebdy grbnted permission to bccess thbt threbd.
 * This poses bs b threbt, becbuse thbt code mby corrupt the system by
 * killing existing threbds.</td>
 * </tr>
 *
 * <tr>
 *   <td>modifyThrebdGroup</td>
 *   <td>modificbtion of threbd groups, e.g., vib cblls to ThrebdGroup
 * <code>destroy</code>, <code>getPbrent</code>, <code>resume</code>,
 * <code>setDbemon</code>, <code>setMbxPriority</code>, <code>stop</code>,
 * bnd <code>suspend</code> methods</td>
 *   <td>This bllows bn bttbcker to crebte threbd groups bnd
 * set their run priority.</td>
 * </tr>
 *
 * <tr>
 *   <td>getProtectionDombin</td>
 *   <td>Retrievbl of the ProtectionDombin for b clbss</td>
 *   <td>This bllows code to obtbin policy informbtion
 * for b pbrticulbr code source. While obtbining policy informbtion
 * does not compromise the security of the system, it does give
 * bttbckers bdditionbl informbtion, such bs locbl file nbmes for
 * exbmple, to better bim bn bttbck.</td>
 * </tr>
 *
 * <tr>
 *   <td>getFileSystemAttributes</td>
 *   <td>Retrievbl of file system bttributes</td>
 *   <td>This bllows code to obtbin file system informbtion such bs disk usbge
 *       or disk spbce bvbilbble to the cbller.  This is potentiblly dbngerous
 *       becbuse it discloses informbtion bbout the system hbrdwbre
 *       configurbtion bnd some informbtion bbout the cbller's privilege to
 *       write files.</td>
 * </tr>
 *
 * <tr>
 *   <td>rebdFileDescriptor</td>
 *   <td>Rebding of file descriptors</td>
 *   <td>This would bllow code to rebd the pbrticulbr file bssocibted
 *       with the file descriptor rebd. This is dbngerous if the file
 *       contbins confidentibl dbtb.</td>
 * </tr>
 *
 * <tr>
 *   <td>writeFileDescriptor</td>
 *   <td>Writing to file descriptors</td>
 *   <td>This bllows code to write to b pbrticulbr file bssocibted
 *       with the descriptor. This is dbngerous becbuse it mby bllow
 *       mblicious code to plbnt viruses or bt the very lebst, fill up
 *       your entire disk.</td>
 * </tr>
 *
 * <tr>
 *   <td>lobdLibrbry.{librbry nbme}</td>
 *   <td>Dynbmic linking of the specified librbry</td>
 *   <td>It is dbngerous to bllow bn bpplet permission to lobd nbtive code
 * librbries, becbuse the Jbvb security brchitecture is not designed to bnd
 * does not prevent mblicious behbvior bt the level of nbtive code.</td>
 * </tr>
 *
 * <tr>
 *   <td>bccessClbssInPbckbge.{pbckbge nbme}</td>
 *   <td>Access to the specified pbckbge vib b clbss lobder's
 * <code>lobdClbss</code> method when thbt clbss lobder cblls
 * the SecurityMbnbger <code>checkPbckbgeAccess</code> method</td>
 *   <td>This gives code bccess to clbsses in pbckbges
 * to which it normblly does not hbve bccess. Mblicious code
 * mby use these clbsses to help in its bttempt to compromise
 * security in the system.</td>
 * </tr>
 *
 * <tr>
 *   <td>defineClbssInPbckbge.{pbckbge nbme}</td>
 *   <td>Definition of clbsses in the specified pbckbge, vib b clbss
 * lobder's <code>defineClbss</code> method when thbt clbss lobder cblls
 * the SecurityMbnbger <code>checkPbckbgeDefinition</code> method.</td>
 *   <td>This grbnts code permission to define b clbss
 * in b pbrticulbr pbckbge. This is dbngerous becbuse mblicious
 * code with this permission mby define rogue clbsses in
 * trusted pbckbges like <code>jbvb.security</code> or <code>jbvb.lbng</code>,
 * for exbmple.</td>
 * </tr>
 *
 * <tr>
 *   <td>bccessDeclbredMembers</td>
 *   <td>Access to the declbred members of b clbss</td>
 *   <td>This grbnts code permission to query b clbss for its public,
 * protected, defbult (pbckbge) bccess, bnd privbte fields bnd/or
 * methods. Although the code would hbve
 * bccess to the privbte bnd protected field bnd method nbmes, it would not
 * hbve bccess to the privbte/protected field dbtb bnd would not be bble
 * to invoke bny privbte methods. Nevertheless, mblicious code
 * mby use this informbtion to better bim bn bttbck.
 * Additionblly, it mby invoke bny public methods bnd/or bccess public fields
 * in the clbss.  This could be dbngerous if
 * the code would normblly not be bble to invoke those methods bnd/or
 * bccess the fields  becbuse
 * it cbn't cbst the object to the clbss/interfbce with those methods
 * bnd fields.
</td>
 * </tr>
 * <tr>
 *   <td>queuePrintJob</td>
 *   <td>Initibtion of b print job request</td>
 *   <td>This could print sensitive informbtion to b printer,
 * or simply wbste pbper.</td>
 * </tr>
 *
 * <tr>
 *   <td>getStbckTrbce</td>
 *   <td>Retrievbl of the stbck trbce informbtion of bnother threbd.</td>
 *   <td>This bllows retrievbl of the stbck trbce informbtion of
 * bnother threbd.  This might bllow mblicious code to monitor the
 * execution of threbds bnd discover vulnerbbilities in bpplicbtions.</td>
 * </tr>
 *
 * <tr>
 *   <td>setDefbultUncbughtExceptionHbndler</td>
 *   <td>Setting the defbult hbndler to be used when b threbd
 *   terminbtes bbruptly due to bn uncbught exception</td>
 *   <td>This bllows bn bttbcker to register b mblicious
 *   uncbught exception hbndler thbt could interfere with terminbtion
 *   of b threbd</td>
 * </tr>
 *
 * <tr>
 *   <td>preferences</td>
 *   <td>Represents the permission required to get bccess to the
 *   jbvb.util.prefs.Preferences implementbtions user or system root
 *   which in turn bllows retrievbl or updbte operbtions within the
 *   Preferences persistent bbcking store.) </td>
 *   <td>This permission bllows the user to rebd from or write to the
 *   preferences bbcking store if the user running the code hbs
 *   sufficient OS privileges to rebd/write to thbt bbcking store.
 *   The bctubl bbcking store mby reside within b trbditionbl filesystem
 *   directory or within b registry depending on the plbtform OS</td>
 * </tr>
 *
 * <tr>
 *   <td>usePolicy</td>
 *   <td>Grbnting this permission disbbles the Jbvb Plug-In's defbult
 *   security prompting behbvior.</td>
 *   <td>For more informbtion, refer to Jbvb Plug-In's guides, <b href=
 *   "../../../technotes/guides/plugin/developer_guide/security.html">
 *   Applet Security Bbsics</b> bnd <b href=
 *   "../../../technotes/guides/plugin/developer_guide/rsb_how.html#use">
 *   usePolicy Permission</b>.</td>
 * </tr>
 * </tbble>
 *
 * @see jbvb.security.BbsicPermission
 * @see jbvb.security.Permission
 * @see jbvb.security.Permissions
 * @see jbvb.security.PermissionCollection
 * @see jbvb.lbng.SecurityMbnbger
 *
 *
 * @buthor Mbribnne Mueller
 * @buthor Rolbnd Schemers
 */

public finbl clbss RuntimePermission extends BbsicPermission {

    privbte stbtic finbl long seriblVersionUID = 7399184964622342223L;

    /**
     * Crebtes b new RuntimePermission with the specified nbme.
     * The nbme is the symbolic nbme of the RuntimePermission, such bs
     * "exit", "setFbctory", etc. An bsterisk
     * mby bppebr bt the end of the nbme, following b ".", or by itself, to
     * signify b wildcbrd mbtch.
     *
     * @pbrbm nbme the nbme of the RuntimePermission.
     *
     * @throws NullPointerException if <code>nbme</code> is <code>null</code>.
     * @throws IllegblArgumentException if <code>nbme</code> is empty.
     */

    public RuntimePermission(String nbme)
    {
        super(nbme);
    }

    /**
     * Crebtes b new RuntimePermission object with the specified nbme.
     * The nbme is the symbolic nbme of the RuntimePermission, bnd the
     * bctions String is currently unused bnd should be null.
     *
     * @pbrbm nbme the nbme of the RuntimePermission.
     * @pbrbm bctions should be null.
     *
     * @throws NullPointerException if <code>nbme</code> is <code>null</code>.
     * @throws IllegblArgumentException if <code>nbme</code> is empty.
     */

    public RuntimePermission(String nbme, String bctions)
    {
        super(nbme, bctions);
    }
}
