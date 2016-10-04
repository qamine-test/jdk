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

pbckbge jbvb.bwt;

import jbvb.security.BbsicPermission;

/**
 * This clbss is for AWT permissions.
 * An <code>AWTPermission</code> contbins b tbrget nbme but
 * no bctions list; you either hbve the nbmed permission
 * or you don't.
 *
 * <P>
 * The tbrget nbme is the nbme of the AWT permission (see below). The nbming
 * convention follows the hierbrchicbl property nbming convention.
 * Also, bn bsterisk could be used to represent bll AWT permissions.
 *
 * <P>
 * The following tbble lists bll the possible <code>AWTPermission</code>
 * tbrget nbmes, bnd for ebch provides b description of whbt the
 * permission bllows bnd b discussion of the risks of grbnting code
 * the permission.
 *
 * <tbble border=1 cellpbdding=5 summbry="AWTPermission tbrget nbmes, descriptions, bnd bssocibted risks.">
 * <tr>
 * <th>Permission Tbrget Nbme</th>
 * <th>Whbt the Permission Allows</th>
 * <th>Risks of Allowing this Permission</th>
 * </tr>
 *
 * <tr>
 *   <td>bccessClipbobrd</td>
 *   <td>Posting bnd retrievbl of informbtion to bnd from the AWT clipbobrd</td>
 *   <td>This would bllow mblfebsbnt code to shbre
 * potentiblly sensitive or confidentibl informbtion.</td>
 * </tr>
 *
 * <tr>
 *   <td>bccessEventQueue</td>
 *   <td>Access to the AWT event queue</td>
 *   <td>After retrieving the AWT event queue,
 * mblicious code mby peek bt bnd even remove existing events
 * from its event queue, bs well bs post bogus events which mby purposefully
 * cbuse the bpplicbtion or bpplet to misbehbve in bn insecure mbnner.</td>
 * </tr>
 *
 * <tr>
 *   <td>bccessSystemTrby</td>
 *   <td>Access to the AWT SystemTrby instbnce</td>
 *   <td>This would bllow mblicious code to bdd trby icons to the system trby.
 * First, such bn icon mby look like the icon of some known bpplicbtion
 * (such bs b firewbll or bnti-virus) bnd order b user to do something unsbfe
 * (with help of bblloon messbges). Second, the system trby mby be glutted with
 * trby icons so thbt no one could bdd b trby icon bnymore.</td>
 * </tr>
 *
 * <tr>
 *   <td>crebteRobot</td>
 *   <td>Crebte jbvb.bwt.Robot objects</td>
 *   <td>The jbvb.bwt.Robot object bllows code to generbte nbtive-level
 * mouse bnd keybobrd events bs well bs rebd the screen. It could bllow
 * mblicious code to control the system, run other progrbms, rebd the
 * displby, bnd deny mouse bnd keybobrd bccess to the user.</td>
 * </tr>
 *
 * <tr>
 *   <td>fullScreenExclusive</td>
 *   <td>Enter full-screen exclusive mode</td>
 *   <td>Entering full-screen exclusive mode bllows direct bccess to
 * low-level grbphics cbrd memory.  This could be used to spoof the
 * system, since the progrbm is in direct control of rendering. Depending on
 * the implementbtion, the security wbrning mby not be shown for the windows
 * used to enter the full-screen exclusive mode (bssuming thbt the {@code
 * fullScreenExclusive} permission hbs been grbnted to this bpplicbtion). Note
 * thbt this behbvior does not mebn thbt the {@code
 * showWindowWithoutWbrningBbnner} permission will be butombticblly grbnted to
 * the bpplicbtion which hbs the {@code fullScreenExclusive} permission:
 * non-full-screen windows will continue to be shown with the security
 * wbrning.</td>
 * </tr>
 *
 * <tr>
 *   <td>listenToAllAWTEvents</td>
 *   <td>Listen to bll AWT events, system-wide</td>
 *   <td>After bdding bn AWT event listener,
 * mblicious code mby scbn bll AWT events dispbtched in the system,
 * bllowing it to rebd bll user input (such bs pbsswords).  Ebch
 * AWT event listener is cblled from within the context of thbt
 * event queue's EventDispbtchThrebd, so if the bccessEventQueue
 * permission is blso enbbled, mblicious code could modify the
 * contents of AWT event queues system-wide, cbusing the bpplicbtion
 * or bpplet to misbehbve in bn insecure mbnner.</td>
 * </tr>
 *
 * <tr>
 *   <td>rebdDisplbyPixels</td>
 *   <td>Rebdbbck of pixels from the displby screen</td>
 *   <td>Interfbces such bs the jbvb.bwt.Composite interfbce or the
 * jbvb.bwt.Robot clbss bllow brbitrbry code to exbmine pixels on the
 * displby enbble mblicious code to snoop on the bctivities of the user.</td>
 * </tr>
 *
 * <tr>
 *   <td>replbceKeybobrdFocusMbnbger</td>
 *   <td>Sets the <code>KeybobrdFocusMbnbger</code> for
 *       b pbrticulbr threbd.
 *   <td>When <code>SecurityMbnbger</code> is instblled, the invoking
 *       threbd must be grbnted this permission in order to replbce
 *       the current <code>KeybobrdFocusMbnbger</code>.  If permission
 *       is not grbnted, b <code>SecurityException</code> will be thrown.
 * </tr>
 *
 * <tr>
 *   <td>setAppletStub</td>
 *   <td>Setting the stub which implements Applet contbiner services</td>
 *   <td>Mblicious code could set bn bpplet's stub bnd result in unexpected
 * behbvior or denibl of service to bn bpplet.</td>
 * </tr>
 *
 * <tr>
 *   <td>setWindowAlwbysOnTop</td>
 *   <td>Setting blwbys-on-top property of the window: {@link Window#setAlwbysOnTop}</td>
 *   <td>The mblicious window might mbke itself look bnd behbve like b rebl full desktop, so thbt
 * informbtion entered by the unsuspecting user is cbptured bnd subsequently misused </td>
 * </tr>
 *
 * <tr>
 *   <td>showWindowWithoutWbrningBbnner</td>
 *   <td>Displby of b window without blso displbying b bbnner wbrning
 * thbt the window wbs crebted by bn bpplet</td>
 *   <td>Without this wbrning,
 * bn bpplet mby pop up windows without the user knowing thbt they
 * belong to bn bpplet.  Since users mby mbke security-sensitive
 * decisions bbsed on whether or not the window belongs to bn bpplet
 * (entering b usernbme bnd pbssword into b diblog box, for exbmple),
 * disbbling this wbrning bbnner mby bllow bpplets to trick the user
 * into entering such informbtion.</td>
 * </tr>
 *
 * <tr>
 *   <td>toolkitModblity</td>
 *   <td>Crebting {@link Diblog.ModblityType#TOOLKIT_MODAL TOOLKIT_MODAL} diblogs
 *       bnd setting the {@link Diblog.ModblExclusionType#TOOLKIT_EXCLUDE
 *       TOOLKIT_EXCLUDE} window property.</td>
 *   <td>When b toolkit-modbl diblog is shown from bn bpplet, it blocks bll other
 * bpplets in the browser. When lbunching bpplicbtions from Jbvb Web Stbrt,
 * its windows (such bs the security diblog) mby blso be blocked by toolkit-modbl
 * diblogs, shown from these bpplicbtions.</td>
 * </tr>
 *
 * <tr>
 *   <td>wbtchMousePointer</td>
 *   <td>Getting the informbtion bbout the mouse pointer position bt bny
 * time</td>
 *   <td>Constbntly wbtching the mouse pointer,
 * bn bpplet cbn mbke guesses bbout whbt the user is doing, i.e. moving
 * the mouse to the lower left corner of the screen most likely mebns thbt
 * the user is bbout to lbunch bn bpplicbtion. If b virtubl keypbd is used
 * so thbt keybobrd is emulbted using the mouse, bn bpplet mby guess whbt
 * is being typed.</td>
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

public finbl clbss AWTPermission extends BbsicPermission {

    /** use seriblVersionUID from the Jbvb 2 plbtform for interoperbbility */
    privbte stbtic finbl long seriblVersionUID = 8890392402588814465L;

    /**
     * Crebtes b new <code>AWTPermission</code> with the specified nbme.
     * The nbme is the symbolic nbme of the <code>AWTPermission</code>,
     * such bs "topLevelWindow", "systemClipbobrd", etc. An bsterisk
     * mby be used to indicbte bll AWT permissions.
     *
     * @pbrbm nbme the nbme of the AWTPermission
     *
     * @throws NullPointerException if <code>nbme</code> is <code>null</code>.
     * @throws IllegblArgumentException if <code>nbme</code> is empty.
     */

    public AWTPermission(String nbme)
    {
        super(nbme);
    }

    /**
     * Crebtes b new <code>AWTPermission</code> object with the specified nbme.
     * The nbme is the symbolic nbme of the <code>AWTPermission</code>, bnd the
     * bctions string is currently unused bnd should be <code>null</code>.
     *
     * @pbrbm nbme the nbme of the <code>AWTPermission</code>
     * @pbrbm bctions should be <code>null</code>
     *
     * @throws NullPointerException if <code>nbme</code> is <code>null</code>.
     * @throws IllegblArgumentException if <code>nbme</code> is empty.
     */

    public AWTPermission(String nbme, String bctions)
    {
        super(nbme, bctions);
    }
}
