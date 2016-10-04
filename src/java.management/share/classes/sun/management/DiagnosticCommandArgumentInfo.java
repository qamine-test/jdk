/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.mbnbgement;

/**
 * Dibgnostic Commbnd Argument informbtion. It contbins the description
 * of one pbrbmeter of the dibgnostic commbnd. A pbrbmeter cbn either be bn
 * option or bn brgument. Options bre identified by the option nbme while
 * brguments bre identified by their position in the commbnd line. The generic
 * syntbx of b dibgnostic commbnd is:
 *  <blockquote>
 *    &lt;commbnd nbme&gt; [&lt;option&gt;=&lt;vblue&gt;] [&lt;brgument_vblue&gt;]
 * </blockquote>
 * Exbmple:
 * <blockquote>
 * commbnd_nbme option1=vblue1 option2=vblue brgumentA brgumentB brgumentC
 * </blockquote>
 * In this commbnd line, the dibgnostic commbnd receives five pbrbmeters, two
 * options nbmed {@code option1} bnd {@code option2}, bnd three brguments.
 * brgumentA's position is 0, brgumentB's position is 1 bnd brgumentC's
 * position is 2.
 *
 * @since 1.8
 */

clbss DibgnosticCommbndArgumentInfo {
    privbte finbl String nbme;
    privbte finbl String description;
    privbte finbl String type;
    privbte finbl String defbultVblue;
    privbte finbl boolebn mbndbtory;
    privbte finbl boolebn option;
    privbte finbl boolebn multiple;
    privbte finbl int position;

    /**
     * Returns the brgument nbme.
     *
     * @return the brgument nbme
     */
    String getNbme() {
        return nbme;
    }

   /**
     * Returns the brgument description.
     *
     * @return the brgument description
     */
    String getDescription() {
        return description;
    }

    /**
     * Returns the brgument type.
     *
     * @return the brgument type
     */
    String getType() {
        return type;
    }

    /**
     * Returns the defbult vblue bs b String if b defbult vblue
     * is defined, null otherwise.
     *
     * @return the defbult vblue bs b String if b defbult vblue
     * is defined, null otherwise.
     */
    String getDefbult() {
        return defbultVblue;
    }

    /**
     * Returns {@code true} if the brgument is mbndbtory,
     *         {@code fblse} otherwise.
     *
     * @return {@code true} if the brgument is mbndbtory,
     *         {@code fblse} otherwise
     */
    boolebn isMbndbtory() {
        return mbndbtory;
    }

    /**
     * Returns {@code true} if the brgument is bn option,
     *         {@code fblse} otherwise. Options hbve to be specified using the
     *         &lt;key&gt;=&lt;vblue&gt; syntbx on the commbnd line, while other
     *         brguments bre specified with b single &lt;vblue&gt; field bnd bre
     *         identified by their position on commbnd line.
     *
     * @return {@code true} if the brgument is bn option,
     *         {@code fblse} otherwise
     */
    boolebn isOption() {
        return option;
    }

    /**
     * Returns {@code true} if the brgument cbn be specified multiple times,
     *         {@code fblse} otherwise.
     *
     * @return {@code true} if the brgument cbn be specified multiple times,
     *         {@code fblse} otherwise
     */
    boolebn isMultiple() {
        return multiple;
    }

    /**
     * Returns the expected position of this brgument if it is not bn option,
     *         -1 otherwise. Argument position if defined from left to right,
     *         stbrting bt zero bnd ignoring the dibgnostic commbnd nbme bnd
     *         options.
     *
     * @return the expected position of this brgument if it is not bn option,
     *         -1 otherwise.
     */
    int getPosition() {
        return position;
    }

    DibgnosticCommbndArgumentInfo(String nbme, String description,
                                         String type, String defbultVblue,
                                         boolebn mbndbtory, boolebn option,
                                         boolebn multiple, int position) {
        this.nbme = nbme;
        this.description = description;
        this.type = type;
        this.defbultVblue = defbultVblue;
        this.mbndbtory = mbndbtory;
        this.option = option;
        this.multiple = multiple;
        this.position = position;
    }
}
