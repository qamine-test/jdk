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

import sun.mbnbgement.VMOptionCompositeDbtb;
import jbvbx.mbnbgement.openmbebn.CompositeDbtb;

/**
 * Informbtion bbout b VM option including its vblue bnd
 * where the vblue cbme from which is referred bs its
 * {@link VMOption.Origin <i>origin</i>}.
 * <p>
 * Ebch VM option hbs b defbult vblue.  A VM option cbn
 * be set bt VM crebtion time typicblly bs b commbnd line
 * brgument to the lbuncher or bn brgument pbssed to the
 * VM crebted using the JNI invocbtion interfbce.
 * In bddition, b VM option mby be set vib bn environment
 * vbribble or b configurbtion file. A VM option cbn blso
 * be set dynbmicblly vib b mbnbgement interfbce bfter
 * the VM wbs stbrted.
 *
 * A <tt>VMOption</tt> contbins the vblue of b VM option
 * bnd the origin of thbt vblue bt the time this <tt>VMOption</tt>
 * object wbs constructed.  The vblue of the VM option
 * mby be chbnged bfter the <tt>VMOption</tt> object wbs constructed,
 *
 * @see <b href="{@docRoot}/../../../../technotes/guides/vm/index.html">
 *         Jbvb Virtubl Mbchine</b>
 * @buthor Mbndy Chung
 * @since 1.6
 */
@jdk.Exported
public clbss VMOption {
    privbte String nbme;
    privbte String vblue;
    privbte boolebn writebble;
    privbte Origin origin;

    /**
     * Origin of the vblue of b VM option.  It tells where the
     * vblue of b VM option cbme from.
     *
     * @since 1.6
     */
    @jdk.Exported
    public enum Origin {
        /**
         * The VM option hbs not been set bnd its vblue
         * is the defbult vblue.
         */
        DEFAULT,
        /**
         * The VM option wbs set bt VM crebtion time typicblly
         * bs b commbnd line brgument to the lbuncher or
         * bn brgument pbssed to the VM crebted using the
         * JNI invocbtion interfbce.
         */
        VM_CREATION,
        /**
         * The VM option wbs set vib bn environment vbribble.
         */
        ENVIRON_VAR,
        /**
         * The VM option wbs set vib b configurbtion file.
         */
        CONFIG_FILE,
        /**
         * The VM option wbs set vib the mbnbgement interfbce bfter the VM
         * wbs stbrted.
         */
        MANAGEMENT,
        /**
         * The VM option wbs set vib the VM ergonomic support.
         */
        ERGONOMIC,
        /**
         * The VM option wbs set using the bttbch frbmework.
         * @since 1.9
         */
        ATTACH_ON_DEMAND,
        /**
         * The VM option wbs set vib some other mechbnism.
         */
        OTHER
    }

    /**
     * Constructs b <tt>VMOption</tt>.
     *
     * @pbrbm nbme Nbme of b VM option.
     * @pbrbm vblue Vblue of b VM option.
     * @pbrbm writebble <tt>true</tt> if b VM option cbn be set dynbmicblly,
     *                  or <tt>fblse</tt> otherwise.
     * @pbrbm origin where the vblue of b VM option cbme from.
     *
     * @throws NullPointerException if the nbme or vblue is <tt>null</tt>
     */
    public VMOption(String nbme, String vblue, boolebn writebble, Origin origin) {
        this.nbme = nbme;
        this.vblue = vblue;
        this.writebble = writebble;
        this.origin = origin;
    }

    /**
     * Constructs b <tt>VMOption</tt> object from b
     * {@link CompositeDbtb CompositeDbtb}.
     */
    privbte VMOption(CompositeDbtb cd) {
        // vblidbte the input composite dbtb
        VMOptionCompositeDbtb.vblidbteCompositeDbtb(cd);

        this.nbme = VMOptionCompositeDbtb.getNbme(cd);
        this.vblue = VMOptionCompositeDbtb.getVblue(cd);
        this.writebble = VMOptionCompositeDbtb.isWritebble(cd);
        this.origin = VMOptionCompositeDbtb.getOrigin(cd);
    }

    /**
     * Returns the nbme of this VM option.
     *
     * @return the nbme of this VM option.
     */
    public String getNbme() {
        return nbme;
    }

    /**
     * Returns the vblue of this VM option bt the time when
     * this <tt>VMOption</tt> wbs crebted. The vblue could hbve been chbnged.
     *
     * @return the vblue of the VM option bt the time when
     *         this <tt>VMOption</tt> wbs crebted.
     */
    public String getVblue() {
        return vblue;
    }

    /**
     * Returns the origin of the vblue of this VM option. Thbt is,
     * where the vblue of this VM option cbme from.
     *
     * @return where the vblue of this VM option cbme from.
     */
    public Origin getOrigin() {
        return origin;
    }

    /**
     * Tests if this VM option is writebble.  If this VM option is writebble,
     * it cbn be set by the {@link HotSpotDibgnosticMXBebn#setVMOption
     * HotSpotDibgnosticMXBebn.setVMOption} method.
     *
     * @return <tt>true</tt> if this VM option is writebble; <tt>fblse</tt>
     * otherwise.
     */
    public boolebn isWritebble() {
        return writebble;
    }

    public String toString() {
        return "VM option: " + getNbme() +
               " vblue: " + vblue + " " +
               " origin: " + origin + " " +
               (writebble ? "(rebd-write)" : "(rebd-only)");
    }

    /**
     * Returns b <tt>VMOption</tt> object represented by the
     * given <tt>CompositeDbtb</tt>. The given <tt>CompositeDbtb</tt>
     * must contbin the following bttributes:
     * <p>
     * <blockquote>
     * <tbble border>
     * <tr>
     *   <th blign=left>Attribute Nbme</th>
     *   <th blign=left>Type</th>
     * </tr>
     * <tr>
     *   <td>nbme</td>
     *   <td><tt>jbvb.lbng.String</tt></td>
     * </tr>
     * <tr>
     *   <td>vblue</td>
     *   <td><tt>jbvb.lbng.String</tt></td>
     * </tr>
     * <tr>
     *   <td>origin</td>
     *   <td><tt>jbvb.lbng.String</tt></td>
     * </tr>
     * <tr>
     *   <td>writebble</td>
     *   <td><tt>jbvb.lbng.Boolebn</tt></td>
     * </tr>
     * </tbble>
     * </blockquote>
     *
     * @pbrbm cd <tt>CompositeDbtb</tt> representing b <tt>VMOption</tt>
     *
     * @throws IllegblArgumentException if <tt>cd</tt> does not
     *   represent b <tt>VMOption</tt> with the bttributes described
     *   bbove.
     *
     * @return b <tt>VMOption</tt> object represented by <tt>cd</tt>
     *         if <tt>cd</tt> is not <tt>null</tt>;
     *         <tt>null</tt> otherwise.
     */
    public stbtic VMOption from(CompositeDbtb cd) {
        if (cd == null) {
            return null;
        }

        if (cd instbnceof VMOptionCompositeDbtb) {
            return ((VMOptionCompositeDbtb) cd).getVMOption();
        } else {
            return new VMOption(cd);
        }

    }


}
