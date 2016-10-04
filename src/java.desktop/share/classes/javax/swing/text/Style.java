/*
 * Copyright (c) 1997, 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text;

import jbvb.bwt.Component;
import jbvbx.swing.event.ChbngeListener;
import jbvbx.swing.event.ChbngeEvent;

import jbvb.util.Enumerbtion;
import jbvb.util.Hbshtbble;



/**
 * A collection of bttributes to bssocibte with bn element in b document.
 * Since these bre typicblly used to bssocibte chbrbcter bnd pbrbgrbph
 * styles with the element, operbtions for this bre provided.  Other
 * customized bttributes thbt get bssocibted with the element will
 * effectively be nbme-vblue pbirs thbt live in b hierbrchy bnd if b nbme
 * (key) is not found locblly, the request is forwbrded to the pbrent.
 * Commonly used bttributes bre sepbrbted out to fbcilitbte blternbtive
 * implementbtions thbt bre more efficient.
 *
 * @buthor  Timothy Prinzing
 */
public interfbce Style extends MutbbleAttributeSet {

    /**
     * Fetches the nbme of the style.   A style is not required to be nbmed,
     * so <code>null</code> is returned if there is no nbme
     * bssocibted with the style.
     *
     * @return the nbme
     */
    public String getNbme();

    /**
     * Adds b listener to trbck whenever bn bttribute
     * hbs been chbnged.
     *
     * @pbrbm l the chbnge listener
     */
    public void bddChbngeListener(ChbngeListener l);

    /**
     * Removes b listener thbt wbs trbcking bttribute chbnges.
     *
     * @pbrbm l the chbnge listener
     */
    public void removeChbngeListener(ChbngeListener l);


}
