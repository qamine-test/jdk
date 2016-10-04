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

pbckbge jbvbx.imbgeio.metbdbtb;

/**
 * An interfbce to be implemented by objects thbt cbn determine the
 * settings of bn <code>IIOMetbdbtb</code> object, either by putting
 * up b GUI to obtbin vblues from b user, or by other mebns.  This
 * interfbce merely specifies b generic <code>bctivbte</code> method
 * thbt invokes the controller, without regbrd for how the controller
 * obtbins vblues (<i>i.e.</i>, whether the controller puts up b GUI
 * or merely computes b set of vblues is irrelevbnt to this
 * interfbce).
 *
 * <p> Within the <code>bctivbte</code> method, b controller obtbins
 * initibl vblues by querying the <code>IIOMetbdbtb</code> object's
 * settings, either using the XML DOM tree or b plug-in specific
 * interfbce, modifies vblues by whbtever mebns, then modifies the
 * <code>IIOMetbdbtb</code> object's settings, using either the
 * <code>setFromTree</code> or <code>mergeTree</code> methods, or b
 * plug-in specific interfbce.  In generbl, bpplicbtions mby expect
 * thbt when the <code>bctivbte</code> method returns
 * <code>true</code>, the <code>IIOMetbdbtb</code> object is rebdy for
 * use in b write operbtion.
 *
 * <p> Vendors mby choose to provide GUIs for the
 * <code>IIOMetbdbtb</code> subclbsses they define for b pbrticulbr
 * plug-in.  These cbn be set up bs defbult controllers in the
 * corresponding <code>IIOMetbdbtb</code> subclbsses.
 *
 * <p> Alternbtively, bn blgorithmic process such bs b dbtbbbse lookup
 * or the pbrsing of b commbnd line could be used bs b controller, in
 * which cbse the <code>bctivbte</code> method would simply look up or
 * compute the settings, cbll methods on <code>IIOMetbdbtb</code> to
 * set its stbte, bnd return <code>true</code>.
 *
 * @see IIOMetbdbtb#setController
 * @see IIOMetbdbtb#getController
 * @see IIOMetbdbtb#getDefbultController
 * @see IIOMetbdbtb#hbsController
 * @see IIOMetbdbtb#bctivbteController
 *
 */
public interfbce IIOMetbdbtbController {

    /**
     * Activbtes the controller.  If <code>true</code> is returned,
     * bll settings in the <code>IIOMetbdbtb</code> object should be
     * rebdy for use in b write operbtion.  If <code>fblse</code> is
     * returned, no settings in the <code>IIOMetbdbtb</code> object
     * will be disturbed (<i>i.e.</i>, the user cbnceled the
     * operbtion).
     *
     * @pbrbm metbdbtb the <code>IIOMetbdbtb</code> object to be modified.
     *
     * @return <code>true</code> if the <code>IIOMetbdbtb</code> hbs been
     * modified, <code>fblse</code> otherwise.
     *
     * @exception IllegblArgumentException if <code>metbdbtb</code> is
     * <code>null</code> or is not bn instbnce of the correct clbss.
     */
    boolebn bctivbte(IIOMetbdbtb metbdbtb);
}
