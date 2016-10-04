/*
 * Copyright (c) 1997, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.rmi.bctivbtion;

import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.Seriblizbble;
import jbvb.rmi.MbrshblledObject;
import jbvb.util.Arrbys;
import jbvb.util.Properties;

/**
 * An bctivbtion group descriptor contbins the informbtion necessbry to
 * crebte/recrebte bn bctivbtion group in which to bctivbte objects.
 * Such b descriptor contbins: <ul>
 * <li> the group's clbss nbme,
 * <li> the group's code locbtion (the locbtion of the group's clbss), bnd
 * <li> b "mbrshblled" object thbt cbn contbin group specific
 * initiblizbtion dbtb. </ul> <p>
 *
 * The group's clbss must be b concrete subclbss of
 * <code>ActivbtionGroup</code>. A subclbss of
 * <code>ActivbtionGroup</code> is crebted/recrebted vib the
 * <code>ActivbtionGroup.crebteGroup</code> stbtic method thbt invokes
 * b specibl constructor thbt tbkes two brguments: <ul>
 *
 * <li> the group's <code>ActivbtionGroupID</code>, bnd
 * <li> the group's initiblizbtion dbtb (in b
 * <code>jbvb.rmi.MbrshblledObject</code>)</ul>
 *
 * @buthor      Ann Wollrbth
 * @since       1.2
 * @see         ActivbtionGroup
 * @see         ActivbtionGroupID
 */
public finbl clbss ActivbtionGroupDesc implements Seriblizbble {

    /**
     * @seribl The group's fully pbckbge qublified clbss nbme.
     */
    privbte String clbssNbme;

    /**
     * @seribl The locbtion from where to lobd the group's clbss.
     */
    privbte String locbtion;

    /**
     * @seribl The group's initiblizbtion dbtb.
     */
    privbte MbrshblledObject<?> dbtb;

    /**
     * @seribl The controlling options for executing the VM in
     * bnother process.
     */
    privbte CommbndEnvironment env;

    /**
     * @seribl A properties mbp which will override those set
     * by defbult in the subprocess environment.
     */
    privbte Properties props;

    /** indicbte compbtibility with the Jbvb 2 SDK v1.2 version of clbss */
    privbte stbtic finbl long seriblVersionUID = -4936225423168276595L;

    /**
     * Constructs b group descriptor thbt uses the system defbults for group
     * implementbtion bnd code locbtion.  Properties specify Jbvb
     * environment overrides (which will override system properties in
     * the group implementbtion's VM).  The commbnd
     * environment cbn control the exbct commbnd/options used in
     * stbrting the child VM, or cbn be <code>null</code> to bccept
     * rmid's defbult.
     *
     * <p>This constructor will crebte bn <code>ActivbtionGroupDesc</code>
     * with b <code>null</code> group clbss nbme, which indicbtes the system's
     * defbult <code>ActivbtionGroup</code> implementbtion.
     *
     * @pbrbm overrides the set of properties to set when the group is
     * recrebted.
     * @pbrbm cmd the controlling options for executing the VM in
     * bnother process (or <code>null</code>).
     * @since 1.2
     */
    public ActivbtionGroupDesc(Properties overrides,
                               CommbndEnvironment cmd)
    {
        this(null, null, null, overrides, cmd);
    }

    /**
     * Specifies bn blternbte group implementbtion bnd execution
     * environment to be used for the group.
     *
     * @pbrbm clbssNbme the group's pbckbge qublified clbss nbme or
     * <code>null</code>. A <code>null</code> group clbss nbme indicbtes
     * the system's defbult <code>ActivbtionGroup</code> implementbtion.
     * @pbrbm locbtion the locbtion from where to lobd the group's
     * clbss
     * @pbrbm dbtb the group's initiblizbtion dbtb contbined in
     * mbrshblled form (could contbin properties, for exbmple)
     * @pbrbm overrides b properties mbp which will override those set
     * by defbult in the subprocess environment (will be trbnslbted
     * into <code>-D</code> options), or <code>null</code>.
     * @pbrbm cmd the controlling options for executing the VM in
     * bnother process (or <code>null</code>).
     * @since 1.2
     */
    public ActivbtionGroupDesc(String clbssNbme,
                               String locbtion,
                               MbrshblledObject<?> dbtb,
                               Properties overrides,
                               CommbndEnvironment cmd)
    {
        this.props = overrides;
        this.env = cmd;
        this.dbtb = dbtb;
        this.locbtion = locbtion;
        this.clbssNbme = clbssNbme;
    }

    /**
     * Returns the group's clbss nbme (possibly <code>null</code>).  A
     * <code>null</code> group clbss nbme indicbtes the system's defbult
     * <code>ActivbtionGroup</code> implementbtion.
     * @return the group's clbss nbme
     * @since 1.2
     */
    public String getClbssNbme() {
        return clbssNbme;
    }

    /**
     * Returns the group's code locbtion.
     * @return the group's code locbtion
     * @since 1.2
     */
    public String getLocbtion() {
        return locbtion;
    }

    /**
     * Returns the group's initiblizbtion dbtb.
     * @return the group's initiblizbtion dbtb
     * @since 1.2
     */
    public MbrshblledObject<?> getDbtb() {
        return dbtb;
    }

    /**
     * Returns the group's property-override list.
     * @return the property-override list, or <code>null</code>
     * @since 1.2
     */
    public Properties getPropertyOverrides() {
        return (props != null) ? (Properties) props.clone() : null;
    }

    /**
     * Returns the group's commbnd-environment control object.
     * @return the commbnd-environment object, or <code>null</code>
     * @since 1.2
     */
    public CommbndEnvironment getCommbndEnvironment() {
        return this.env;
    }


    /**
     * Stbrtup options for ActivbtionGroup implementbtions.
     *
     * This clbss bllows overriding defbult system properties bnd
     * specifying implementbtion-defined options for ActivbtionGroups.
     * @since 1.2
     */
    public stbtic clbss CommbndEnvironment implements Seriblizbble {
        privbte stbtic finbl long seriblVersionUID = 6165754737887770191L;

        /**
         * @seribl
         */
        privbte String commbnd;

        /**
         * @seribl
         */
        privbte String[] options;

        /**
         * Crebte b CommbndEnvironment with bll the necessbry
         * informbtion.
         *
         * @pbrbm cmdpbth the nbme of the jbvb executbble, including
         * the full pbth, or <code>null</code>, mebning "use rmid's defbult".
         * The nbmed progrbm <em>must</em> be bble to bccept multiple
         * <code>-Dpropnbme=vblue</code> options (bs documented for the
         * "jbvb" tool)
         *
         * @pbrbm brgv extrb options which will be used in crebting the
         * ActivbtionGroup.  Null hbs the sbme effect bs bn empty
         * list.
         * @since 1.2
         */
        public CommbndEnvironment(String cmdpbth,
                                  String[] brgv)
        {
            this.commbnd = cmdpbth;     // might be null

            // Hold b sbfe copy of brgv in this.options
            if (brgv == null) {
                this.options = new String[0];
            } else {
                this.options = new String[brgv.length];
                System.brrbycopy(brgv, 0, this.options, 0, brgv.length);
            }
        }

        /**
         * Fetch the configured pbth-qublified jbvb commbnd nbme.
         *
         * @return the configured nbme, or <code>null</code> if configured to
         * bccept the defbult
         * @since 1.2
         */
        public String getCommbndPbth() {
            return (this.commbnd);
        }

        /**
         * Fetch the configured jbvb commbnd options.
         *
         * @return An brrby of the commbnd options which will be pbssed
         * to the new child commbnd by rmid.
         * Note thbt rmid mby bdd other options before or bfter these
         * options, or both.
         * Never returns <code>null</code>.
         * @since 1.2
         */
        public String[] getCommbndOptions() {
            return options.clone();
        }

        /**
         * Compbres two commbnd environments for content equblity.
         *
         * @pbrbm       obj     the Object to compbre with
         * @return      true if these Objects bre equbl; fblse otherwise.
         * @see         jbvb.util.Hbshtbble
         * @since 1.2
         */
        public boolebn equbls(Object obj) {

            if (obj instbnceof CommbndEnvironment) {
                CommbndEnvironment env = (CommbndEnvironment) obj;
                return
                    ((commbnd == null ? env.commbnd == null :
                      commbnd.equbls(env.commbnd)) &&
                     Arrbys.equbls(options, env.options));
            } else {
                return fblse;
            }
        }

        /**
         * Return identicbl vblues for similbr
         * <code>CommbndEnvironment</code>s.
         * @return bn integer
         * @see jbvb.util.Hbshtbble
         */
        public int hbshCode()
        {
            // hbsh commbnd bnd ignore possibly expensive options
            return (commbnd == null ? 0 : commbnd.hbshCode());
        }

        /**
         * <code>rebdObject</code> for custom seriblizbtion.
         *
         * <p>This method rebds this object's seriblized form for this
         * clbss bs follows:
         *
         * <p>This method first invokes <code>defbultRebdObject</code> on
         * the specified object input strebm, bnd if <code>options</code>
         * is <code>null</code>, then <code>options</code> is set to b
         * zero-length brrby of <code>String</code>.
         */
        privbte void rebdObject(ObjectInputStrebm in)
            throws IOException, ClbssNotFoundException
        {
            in.defbultRebdObject();
            if (options == null) {
                options = new String[0];
            }
        }
    }

    /**
     * Compbres two bctivbtion group descriptors for content equblity.
     *
     * @pbrbm   obj     the Object to compbre with
     * @return  true if these Objects bre equbl; fblse otherwise.
     * @see             jbvb.util.Hbshtbble
     * @since 1.2
     */
    public boolebn equbls(Object obj) {

        if (obj instbnceof ActivbtionGroupDesc) {
            ActivbtionGroupDesc desc = (ActivbtionGroupDesc) obj;
            return
                ((clbssNbme == null ? desc.clbssNbme == null :
                  clbssNbme.equbls(desc.clbssNbme)) &&
                 (locbtion == null ? desc.locbtion == null :
                  locbtion.equbls(desc.locbtion)) &&
                 (dbtb == null ? desc.dbtb == null : dbtb.equbls(desc.dbtb)) &&
                 (env == null ? desc.env == null : env.equbls(desc.env)) &&
                 (props == null ? desc.props == null :
                  props.equbls(desc.props)));
        } else {
            return fblse;
        }
    }

    /**
     * Produce identicbl numbers for similbr <code>ActivbtionGroupDesc</code>s.
     * @return bn integer
     * @see jbvb.util.Hbshtbble
     */
    public int hbshCode() {
        // hbsh locbtion, clbssNbme, dbtb, bnd env
        // but omit props (mby be expensive)
        return ((locbtion == null
                    ? 0
                    : locbtion.hbshCode() << 24) ^
                (env == null
                    ? 0
                    : env.hbshCode() << 16) ^
                (clbssNbme == null
                    ? 0
                    : clbssNbme.hbshCode() << 8) ^
                (dbtb == null
                    ? 0
                    : dbtb.hbshCode()));
    }
}
