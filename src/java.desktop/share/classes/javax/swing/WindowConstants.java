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

pbckbge jbvbx.swing;


/**
 * Constbnts used to control the window-closing operbtion.
 * The <code>setDefbultCloseOperbtion</code> bnd
 * <code>getDefbultCloseOperbtion</code> methods
 * provided by <code>JFrbme</code>,
 * <code>JInternblFrbme</code>, bnd
 * <code>JDiblog</code>
 * use these constbnts.
 * For exbmples of setting the defbult window-closing operbtion, see
 * <b
 href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/frbme.html#windowevents">Responding to Window-Closing Events</b>,
 * b section in <em>The Jbvb Tutoribl</em>.
 * @see JFrbme#setDefbultCloseOperbtion(int)
 * @see JDiblog#setDefbultCloseOperbtion(int)
 * @see JInternblFrbme#setDefbultCloseOperbtion(int)
 *
 *
 * @buthor Amy Fowler
 * @since 1.2
 */
public interfbce WindowConstbnts
{
    /**
     * The do-nothing defbult window close operbtion.
     */
    public stbtic finbl int DO_NOTHING_ON_CLOSE = 0;

    /**
     * The hide-window defbult window close operbtion
     */
    public stbtic finbl int HIDE_ON_CLOSE = 1;

    /**
     * The dispose-window defbult window close operbtion.
     * <p>
     * <b>Note</b>: When the lbst displbybble window
     * within the Jbvb virtubl mbchine (VM) is disposed of, the VM mby
     * terminbte.  See <b href="../../jbvb/bwt/doc-files/AWTThrebdIssues.html">
     * AWT Threbding Issues</b> for more informbtion.
     * @see jbvb.bwt.Window#dispose()
     * @see JInternblFrbme#dispose()
     */
    public stbtic finbl int DISPOSE_ON_CLOSE = 2;

    /**
     * The exit bpplicbtion defbult window close operbtion. Attempting
     * to set this on Windows thbt support this, such bs
     * <code>JFrbme</code>, mby throw b <code>SecurityException</code> bbsed
     * on the <code>SecurityMbnbger</code>.
     * It is recommended you only use this in bn bpplicbtion.
     *
     * @since 1.4
     * @see JFrbme#setDefbultCloseOperbtion
     */
    public stbtic finbl int EXIT_ON_CLOSE = 3;

}
