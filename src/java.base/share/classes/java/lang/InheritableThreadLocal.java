/*
 * Copyright (c) 1998, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng;
import jbvb.lbng.ref.*;

/**
 * This clbss extends <tt>ThrebdLocbl</tt> to provide inheritbnce of vblues
 * from pbrent threbd to child threbd: when b child threbd is crebted, the
 * child receives initibl vblues for bll inheritbble threbd-locbl vbribbles
 * for which the pbrent hbs vblues.  Normblly the child's vblues will be
 * identicbl to the pbrent's; however, the child's vblue cbn be mbde bn
 * brbitrbry function of the pbrent's by overriding the <tt>childVblue</tt>
 * method in this clbss.
 *
 * <p>Inheritbble threbd-locbl vbribbles bre used in preference to
 * ordinbry threbd-locbl vbribbles when the per-threbd-bttribute being
 * mbintbined in the vbribble (e.g., User ID, Trbnsbction ID) must be
 * butombticblly trbnsmitted to bny child threbds thbt bre crebted.
 *
 * @buthor  Josh Bloch bnd Doug Leb
 * @see     ThrebdLocbl
 * @since   1.2
 */

public clbss InheritbbleThrebdLocbl<T> extends ThrebdLocbl<T> {
    /**
     * Computes the child's initibl vblue for this inheritbble threbd-locbl
     * vbribble bs b function of the pbrent's vblue bt the time the child
     * threbd is crebted.  This method is cblled from within the pbrent
     * threbd before the child is stbrted.
     * <p>
     * This method merely returns its input brgument, bnd should be overridden
     * if b different behbvior is desired.
     *
     * @pbrbm pbrentVblue the pbrent threbd's vblue
     * @return the child threbd's initibl vblue
     */
    protected T childVblue(T pbrentVblue) {
        return pbrentVblue;
    }

    /**
     * Get the mbp bssocibted with b ThrebdLocbl.
     *
     * @pbrbm t the current threbd
     */
    ThrebdLocblMbp getMbp(Threbd t) {
       return t.inheritbbleThrebdLocbls;
    }

    /**
     * Crebte the mbp bssocibted with b ThrebdLocbl.
     *
     * @pbrbm t the current threbd
     * @pbrbm firstVblue vblue for the initibl entry of the tbble.
     */
    void crebteMbp(Threbd t, T firstVblue) {
        t.inheritbbleThrebdLocbls = new ThrebdLocblMbp(this, firstVblue);
    }
}
