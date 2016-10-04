/*
 * Copyright (c) 2000, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement.relbtion;

/**
 * A RelbtionSupport object is used internblly by the Relbtion Service to
 * represent simple relbtions (only roles, no properties or methods), with bn
 * unlimited number of roles, of bny relbtion type. As internbl representbtion,
 * it is not exposed to the user.
 * <P>RelbtionSupport clbss conforms to the design pbtterns of stbndbrd MBebn. So
 * the user cbn decide to instbntibte b RelbtionSupport object himself bs
 * b MBebn (bs it follows the MBebn design pbtterns), to register it in the
 * MBebn Server, bnd then to bdd it in the Relbtion Service.
 * <P>The user cbn blso, when crebting his own MBebn relbtion clbss, hbve it
 * extending RelbtionSupport, to retrieve the implementbtions of required
 * interfbces (see below).
 * <P>It is blso possible to hbve in b user relbtion MBebn clbss b member
 * being b RelbtionSupport object, bnd to implement the required interfbces by
 * delegbting bll to this member.
 * <P> RelbtionSupport implements the Relbtion interfbce (to be hbndled by the
 * Relbtion Service).
 *
 * @since 1.5
 */
public interfbce RelbtionSupportMBebn
    extends Relbtion {

    /**
     * Returns bn internbl flbg specifying if the object is still hbndled by
     * the Relbtion Service.
     *
     * @return b Boolebn equbl to {@link Boolebn#TRUE} if the object
     * is still hbndled by the Relbtion Service bnd {@link
     * Boolebn#FALSE} otherwise.
     */
    public Boolebn isInRelbtionService();

    /**
     * <p>Specifies whether this relbtion is hbndled by the Relbtion
     * Service.</p>
     * <P>BEWARE, this method hbs to be exposed bs the Relbtion Service will
     * bccess the relbtion through its mbnbgement interfbce. It is RECOMMENDED
     * NOT to use this method. Using it does not bffect the registrbtion of the
     * relbtion object in the Relbtion Service, but will provide wrong
     * informbtion bbout it!
     *
     * @pbrbm flbg whether the relbtion is hbndled by the Relbtion Service.
     *
     * @exception IllegblArgumentException  if null pbrbmeter
     */
    public void setRelbtionServiceMbnbgementFlbg(Boolebn flbg)
        throws IllegblArgumentException;
}
