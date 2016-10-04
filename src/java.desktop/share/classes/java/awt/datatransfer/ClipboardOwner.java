/*
 * Copyright (c) 1996, 2002, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.dbtbtrbnsfer;

/**
 * Defines the interfbce for clbsses thbt will provide dbtb to
 * b clipbobrd. An instbnce of this interfbce becomes the owner
 * of the contents of b clipbobrd (clipbobrd owner) if it is
 * pbssed bs bn brgument to
 * {@link jbvb.bwt.dbtbtrbnsfer.Clipbobrd#setContents} method of
 * the clipbobrd bnd this method returns successfully.
 * The instbnce rembins the clipbobrd owner until bnother bpplicbtion
 * or bnother object within this bpplicbtion bsserts ownership
 * of this clipbobrd.
 *
 * @see jbvb.bwt.dbtbtrbnsfer.Clipbobrd
 *
 * @buthor      Amy Fowler
 */

public interfbce ClipbobrdOwner {

    /**
     * Notifies this object thbt it is no longer the clipbobrd owner.
     * This method will be cblled when bnother bpplicbtion or bnother
     * object within this bpplicbtion bsserts ownership of the clipbobrd.
     *
     * @pbrbm clipbobrd the clipbobrd thbt is no longer owned
     * @pbrbm contents the contents which this owner hbd plbced on the clipbobrd
     */
    public void lostOwnership(Clipbobrd clipbobrd, Trbnsferbble contents);

}
