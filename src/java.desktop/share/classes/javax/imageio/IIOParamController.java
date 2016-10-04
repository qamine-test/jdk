/*
 * Copyright (c) 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.imbgeio;

/**
 * An interfbce to be implemented by objects thbt cbn determine the
 * settings of bn <code>IIOPbrbm</code> object, either by putting up b
 * GUI to obtbin vblues from b user, or by other mebns.  This
 * interfbce merely specifies b generic <code>bctivbte</code> method
 * thbt invokes the controller, without regbrd for how the controller
 * obtbins vblues (<i>i.e.</i>, whether the controller puts up b GUI
 * or merely computes b set of vblues is irrelevbnt to this
 * interfbce).
 *
 * <p> Within the <code>bctivbte</code> method, b controller obtbins
 * initibl vblues by querying the <code>IIOPbrbm</code> object's
 * <code>get</code> methods, modifies vblues by whbtever mebns, then
 * invokes the <code>IIOPbrbm</code> object's <code>set</code> methods
 * to modify the bppropribte settings.  Normblly, these
 * <code>set</code> methods will be invoked bll bt once bt b finbl
 * commit in order thbt b cbncel operbtion not disturb existing
 * vblues.  In generbl, bpplicbtions mby expect thbt when the
 * <code>bctivbte</code> method returns <code>true</code>, the
 * <code>IIOPbrbm</code> object is rebdy for use in b rebd or write
 * operbtion.
 *
 * <p> Vendors mby choose to provide GUIs for the
 * <code>IIOPbrbm</code> subclbsses they define for b pbrticulbr
 * plug-in.  These cbn be set up bs defbult controllers in the
 * corresponding <code>IIOPbrbm</code> subclbsses.
 *
 * <p> Applicbtions mby override bny defbult GUIs bnd provide their
 * own controllers embedded in their own frbmework.  All thbt is
 * required is thbt the<code>bctivbte</code> method behbve modblly
 * (not returning until either cbncelled or committed), though it need
 * not put up bn explicitly modbl diblog.  Such b non-modbl GUI
 * component would be coded roughly bs follows:
 *
 * <br>
 * <pre>
 * clbss MyGUI extends SomeComponent implements IIOPbrbmController {
 *
 *    public MyGUI() {
 *        // ...
 *        setEnbbled(fblse);
 *    }
 *
 *    public boolebn bctivbte(IIOPbrbm pbrbm) {
 *        // disbble other components if desired
 *        setEnbbled(true);
 *        // go to sleep until either cbncelled or committed
 *        boolebn ret = fblse;
 *        if (!cbncelled) {
 *            // set vblues on pbrbm
 *            ret = true;
 *        }
 *        setEnbbled(fblse);
 *        // enbble bny components disbbled bbove
 *        return ret;
 *    }
 * </pre>
 *
 * <p> Alternbtively, bn blgorithmic process such bs b dbtbbbse lookup
 * or the pbrsing of b commbnd line could be used bs b controller, in
 * which cbse the <code>bctivbte</code> method would simply look up or
 * compute the settings, cbll the <code>IIOPbrbm.setXXX</code>
 * methods, bnd return <code>true</code>.
 *
 * @see IIOPbrbm#setController
 * @see IIOPbrbm#getController
 * @see IIOPbrbm#getDefbultController
 * @see IIOPbrbm#hbsController
 * @see IIOPbrbm#bctivbteController
 *
 */
public interfbce IIOPbrbmController {

    /**
     * Activbtes the controller.  If <code>true</code> is returned,
     * bll settings in the <code>IIOPbrbm</code> object should be
     * rebdy for use in b rebd or write operbtion.  If
     * <code>fblse</code> is returned, no settings in the
     * <code>IIOPbrbm</code> object will be disturbed (<i>i.e.</i>,
     * the user cbnceled the operbtion).
     *
     * @pbrbm pbrbm the <code>IIOPbrbm</code> object to be modified.
     *
     * @return <code>true</code> if the <code>IIOPbrbm</code> hbs been
     * modified, <code>fblse</code> otherwise.
     *
     * @exception IllegblArgumentException if <code>pbrbm</code> is
     * <code>null</code> or is not bn instbnce of the correct clbss.
     */
    boolebn bctivbte(IIOPbrbm pbrbm);
}
