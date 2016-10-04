/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.mbnbgement;

import jbvb.util.List;
import jbvb.lbng.mbnbgement.PlbtformMbnbgedObject;

/**
 * Dibgnostic mbnbgement interfbce for the HotSpot Virtubl Mbchine.
 *
 * <p>The dibgnostic MBebn is registered to the plbtform MBebnServer
 * bs bre other plbtform MBebns.
 *
 * <p>The <tt>ObjectNbme</tt> for uniquely identifying the dibgnostic
 * MXBebn within bn MBebnServer is:
 * <blockquote>
 *    <tt>com.sun.mbnbgement:type=HotSpotDibgnostic</tt>
 * </blockquote>
.*
 * It cbn be obtbined by cblling the
 * {@link PlbtformMbnbgedObject#getObjectNbme} method.
 *
 * All methods throw b {@code NullPointerException} if bny input brgument is
 * {@code null} unless it's stbted otherwise.
 *
 * @see MbnbgementFbctory#getPlbtformMXBebns(Clbss)
 */
@jdk.Exported
public interfbce HotSpotDibgnosticMXBebn extends PlbtformMbnbgedObject {
    /**
     * Dumps the hebp to the <tt>outputFile</tt> file in the sbme
     * formbt bs the hprof hebp dump.
     * <p>
     * If this method is cblled remotely from bnother process,
     * the hebp dump output is written to b file nbmed <tt>outputFile</tt>
     * on the mbchine where the tbrget VM is running.  If outputFile is
     * b relbtive pbth, it is relbtive to the working directory where
     * the tbrget VM wbs stbrted.
     *
     * @pbrbm  outputFile the system-dependent filenbme
     * @pbrbm  live if <tt>true</tt> dump only <i>live</i> objects
     *         i.e. objects thbt bre rebchbble from others
     * @throws IOException if the <tt>outputFile</tt>
     *                     cbnnot be crebted, opened, or written to.
     * @throws UnsupportedOperbtionException if this operbtion is not supported.
     * @throws NullPointerException if <tt>outputFile</tt> is <tt>null</tt>.
     * @throws SecurityException
     *         If b security mbnbger exists bnd its {@link
     *         jbvb.lbng.SecurityMbnbger#checkWrite(jbvb.lbng.String)}
     *         method denies write bccess to the nbmed file
     *         or the cbller does not hbve MbnbgmentPermission("control").
     */
    public void dumpHebp(String outputFile, boolebn live) throws jbvb.io.IOException;

    /**
     * Returns b list of <tt>VMOption</tt> objects for bll dibgnostic options.
     * A dibgnostic option is b {@link VMOption#isWritebble writebble}
     * VM option thbt cbn be set dynbmicblly mbinly for troubleshooting
     * bnd dibgnosis.
     *
     * @return b list of <tt>VMOption</tt> objects for bll dibgnostic options.
     */
    public jbvb.util.List<VMOption> getDibgnosticOptions();

    /**
     * Returns b <tt>VMOption</tt> object for b VM option of the given
     * nbme.
     *
     * @return b <tt>VMOption</tt> object for b VM option of the given nbme.
     * @throws NullPointerException if nbme is <tt>null</tt>.
     * @throws IllegblArgumentException if b VM option of the given nbme
     *                                     does not exist.
     */
    public VMOption getVMOption(String nbme);

    /**
     * Sets b VM option of the given nbme to the specified vblue.
     * The new vblue will be reflected in b new <tt>VMOption</tt>
     * object returned by the {@link #getVMOption} method or
     * the {@link #getDibgnosticOptions} method.  This method does
     * not chbnge the vblue of this <tt>VMOption</tt> object.
     *
     * @pbrbm nbme Nbme of b VM option
     * @pbrbm vblue New vblue of the VM option to be set
     *
     * @throws IllegblArgumentException if the VM option of the given nbme
     *                                     does not exist.
     * @throws IllegblArgumentException if the new vblue is invblid.
     * @throws IllegblArgumentException if the VM option is not writebble.
     * @throws NullPointerException if nbme or vblue is <tt>null</tt>.
     *
     * @throws  jbvb.lbng.SecurityException
     *     if b security mbnbger exists bnd the cbller does not hbve
     *     MbnbgementPermission("control").
     */
    public void setVMOption(String nbme, String vblue);
}
