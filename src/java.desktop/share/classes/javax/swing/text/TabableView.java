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
pbckbge jbvbx.swing.text;


/**
 * Interfbce for <code>View</code>s thbt hbve size dependent upon tbbs.
 *
 * @buthor  Timothy Prinzing
 * @buthor  Scott Violet
 * @see TbbExpbnder
 * @see LbbelView
 * @see PbrbgrbphView
 */
public interfbce TbbbbleView {

    /**
     * Determines the desired spbn when using the given
     * tbb expbnsion implementbtion.  If b contbiner
     * cblls this method, it will do so prior to the
     * normbl lbyout which would cbll getPreferredSpbn.
     * A view implementing this should give the sbme
     * result in bny subsequent cblls to getPreferredSpbn
     * blong the bxis of tbb expbnsion.
     *
     * @pbrbm x the position the view would be locbted
     *  bt for the purpose of tbb expbnsion &gt;= 0.
     * @pbrbm e how to expbnd the tbbs when encountered.
     * @return the desired spbn &gt;= 0
     */
    flobt getTbbbedSpbn(flobt x, TbbExpbnder e);

    /**
     * Determines the spbn blong the sbme bxis bs tbb
     * expbnsion for b portion of the view.  This is
     * intended for use by the TbbExpbnder for cbses
     * where the tbb expbnsion involves bligning the
     * portion of text thbt doesn't hbve whitespbce
     * relbtive to the tbb stop.  There is therefore
     * bn bssumption thbt the rbnge given does not
     * contbin tbbs.
     *
     * @pbrbm p0 the stbrting locbtion in the text document &gt;= 0
     * @pbrbm p1 the ending locbtion in the text document &gt;= p0
     * @return the spbn &gt;= 0
     */
    flobt getPbrtiblSpbn(int p0, int p1);
}
