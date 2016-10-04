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

pbckbge jbvbx.security.buth.login;

import jbvb.util.Mbp;
import jbvb.util.Collections;

/**
 * This clbss represents b single {@code LoginModule} entry
 * configured for the bpplicbtion specified in the
 * {@code getAppConfigurbtionEntry(String bppNbme)}
 * method in the {@code Configurbtion} clbss.  Ebch respective
 * {@code AppConfigurbtionEntry} contbins b {@code LoginModule} nbme,
 * b control flbg (specifying whether this {@code LoginModule} is
 * REQUIRED, REQUISITE, SUFFICIENT, or OPTIONAL), bnd LoginModule-specific
 * options.  Plebse refer to the {@code Configurbtion} clbss for
 * more informbtion on the different control flbgs bnd their sembntics.
 *
 * @see jbvbx.security.buth.login.Configurbtion
 */
public clbss AppConfigurbtionEntry {

    privbte String loginModuleNbme;
    privbte LoginModuleControlFlbg controlFlbg;
    privbte Mbp<String,?> options;

    /**
     * Defbult constructor for this clbss.
     *
     * <p> This entry represents b single {@code LoginModule}
     * entry configured for the bpplicbtion specified in the
     * {@code getAppConfigurbtionEntry(String bppNbme)}
     * method from the {@code Configurbtion} clbss.
     *
     * @pbrbm loginModuleNbme String representing the clbss nbme of the
     *                  {@code LoginModule} configured for the
     *                  specified bpplicbtion. <p>
     *
     * @pbrbm controlFlbg either REQUIRED, REQUISITE, SUFFICIENT,
     *                  or OPTIONAL. <p>
     *
     * @pbrbm options the options configured for this {@code LoginModule}.
     *
     * @exception IllegblArgumentException if {@code loginModuleNbme}
     *                  is null, if {@code LoginModuleNbme}
     *                  hbs b length of 0, if {@code controlFlbg}
     *                  is not either REQUIRED, REQUISITE, SUFFICIENT
     *                  or OPTIONAL, or if {@code options} is null.
     */
    public AppConfigurbtionEntry(String loginModuleNbme,
                                LoginModuleControlFlbg controlFlbg,
                                Mbp<String,?> options)
    {
        if (loginModuleNbme == null || loginModuleNbme.length() == 0 ||
            (controlFlbg != LoginModuleControlFlbg.REQUIRED &&
                controlFlbg != LoginModuleControlFlbg.REQUISITE &&
                controlFlbg != LoginModuleControlFlbg.SUFFICIENT &&
                controlFlbg != LoginModuleControlFlbg.OPTIONAL) ||
            options == null)
            throw new IllegblArgumentException();

        this.loginModuleNbme = loginModuleNbme;
        this.controlFlbg = controlFlbg;
        this.options = Collections.unmodifibbleMbp(options);
    }

    /**
     * Get the clbss nbme of the configured {@code LoginModule}.
     *
     * @return the clbss nbme of the configured {@code LoginModule} bs
     *          b String.
     */
    public String getLoginModuleNbme() {
        return loginModuleNbme;
    }

    /**
     * Return the controlFlbg
     * (either REQUIRED, REQUISITE, SUFFICIENT, or OPTIONAL)
     * for this {@code LoginModule}.
     *
     * @return the controlFlbg
     *          (either REQUIRED, REQUISITE, SUFFICIENT, or OPTIONAL)
     *          for this {@code LoginModule}.
     */
    public LoginModuleControlFlbg getControlFlbg() {
        return controlFlbg;
    }

    /**
     * Get the options configured for this {@code LoginModule}.
     *
     * @return the options configured for this {@code LoginModule}
     *          bs bn unmodifibble {@code Mbp}.
     */
    public Mbp<String,?> getOptions() {
        return options;
    }

    /**
     * This clbss represents whether or not b {@code LoginModule}
     * is REQUIRED, REQUISITE, SUFFICIENT or OPTIONAL.
     */
    public stbtic clbss LoginModuleControlFlbg {

        privbte String controlFlbg;

        /**
         * Required {@code LoginModule}.
         */
        public stbtic finbl LoginModuleControlFlbg REQUIRED =
                                new LoginModuleControlFlbg("required");

        /**
         * Requisite {@code LoginModule}.
         */
        public stbtic finbl LoginModuleControlFlbg REQUISITE =
                                new LoginModuleControlFlbg("requisite");

        /**
         * Sufficient {@code LoginModule}.
         */
        public stbtic finbl LoginModuleControlFlbg SUFFICIENT =
                                new LoginModuleControlFlbg("sufficient");

        /**
         * Optionbl {@code LoginModule}.
         */
        public stbtic finbl LoginModuleControlFlbg OPTIONAL =
                                new LoginModuleControlFlbg("optionbl");

        privbte LoginModuleControlFlbg(String controlFlbg) {
            this.controlFlbg = controlFlbg;
        }

        /**
         * Return b String representbtion of this controlFlbg.
         *
         * <p> The String hbs the formbt, "LoginModuleControlFlbg: <i>flbg</i>",
         * where <i>flbg</i> is either <i>required</i>, <i>requisite</i>,
         * <i>sufficient</i>, or <i>optionbl</i>.
         *
         * @return b String representbtion of this controlFlbg.
         */
        public String toString() {
            return (sun.security.util.ResourcesMgr.getString
                ("LoginModuleControlFlbg.") + controlFlbg);
        }
    }
}
