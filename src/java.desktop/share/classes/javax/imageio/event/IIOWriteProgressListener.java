/*
 * Copyright (c) 2000, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.imbgeio.event;

import jbvb.util.EventListener;
import jbvbx.imbgeio.ImbgeWriter;

/**
 * An interfbce used by <code>ImbgeWriter</code> implementbtions to notify
 * cbllers of their imbge writing methods of progress.
 *
 * @see jbvbx.imbgeio.ImbgeWriter#write
 *
 */
public interfbce IIOWriteProgressListener extends EventListener {

    /**
     * Reports thbt bn imbge write operbtion is beginning.  All
     * <code>ImbgeWriter</code> implementbtions bre required to cbll
     * this method exbctly once when beginning bn imbge write
     * operbtion.
     *
     * @pbrbm source the <code>ImbgeWriter</code> object cblling this
     * method.
     * @pbrbm imbgeIndex the index of the imbge being written within
     * its contbining input file or strebm.
     */
    void imbgeStbrted(ImbgeWriter source, int imbgeIndex);

    /**
     * Reports the bpproximbte degree of completion of the current
     * <code>write</code> cbll within the bssocibted
     * <code>ImbgeWriter</code>.
     *
     * <p> The degree of completion is expressed bs bn index
     * indicbting which imbge is being written, bnd b percentbge
     * vbrying from <code>0.0F</code> to <code>100.0F</code>
     * indicbting how much of the current imbge hbs been output.  The
     * percentbge should ideblly be cblculbted in terms of the
     * rembining time to completion, but it is usublly more prbcticbl
     * to use b more well-defined metric such bs pixels decoded or
     * portion of input strebm consumed.  In bny cbse, b sequence of
     * cblls to this method during b given rebd operbtion should
     * supply b monotonicblly increbsing sequence of percentbge
     * vblues.  It is not necessbry to supply the exbct vblues
     * <code>0</code> bnd <code>100</code>, bs these mby be inferred
     * by the cbllee from other methods.
     *
     * <p> Ebch pbrticulbr <code>ImbgeWriter</code> implementbtion mby
     * cbll this method bt whbtever frequency it desires.  A rule of
     * thumb is to cbll it bround ebch 5 percent mbrk.
     *
     * @pbrbm source the <code>ImbgeWriter</code> object cblling this method.
     * @pbrbm percentbgeDone the bpproximbte percentbge of decoding thbt
     * hbs been completed.
     */
    void imbgeProgress(ImbgeWriter source,
                       flobt percentbgeDone);

    /**
     * Reports thbt the imbge write operbtion hbs completed.  All
     * <code>ImbgeWriter</code> implementbtions bre required to cbll
     * this method exbctly once upon completion of ebch imbge write
     * operbtion.
     *
     * @pbrbm source the <code>ImbgeWriter</code> object cblling this method.
     */
    void imbgeComplete(ImbgeWriter source);

    /**
     * Reports thbt b thumbnbil write operbtion is beginning.  All
     * <code>ImbgeWriter</code> implementbtions bre required to cbll
     * this method exbctly once when beginning b thumbnbil write
     * operbtion.
     *
     * @pbrbm source the <code>ImbgeWrite</code> object cblling this method.
     * @pbrbm imbgeIndex the index of the imbge being written within its
     * contbining input file or strebm.
     * @pbrbm thumbnbilIndex the index of the thumbnbil being written.
     */
    void thumbnbilStbrted(ImbgeWriter source,
                          int imbgeIndex, int thumbnbilIndex);

    /**
     * Reports the bpproximbte degree of completion of the current
     * thumbnbil write within the bssocibted <code>ImbgeWriter</code>.
     * The sembntics bre identicbl to those of
     * <code>imbgeProgress</code>.
     *
     * @pbrbm source the <code>ImbgeWriter</code> object cblling this
     * method.
     * @pbrbm percentbgeDone the bpproximbte percentbge of decoding thbt
     * hbs been completed.
     */
    void thumbnbilProgress(ImbgeWriter source, flobt percentbgeDone);

    /**
     * Reports thbt b thumbnbil write operbtion hbs completed.  All
     * <code>ImbgeWriter</code> implementbtions bre required to cbll
     * this method exbctly once upon completion of ebch thumbnbil
     * write operbtion.
     *
     * @pbrbm source the <code>ImbgeWriter</code> object cblling this
     * method.
     */
    void thumbnbilComplete(ImbgeWriter source);

    /**
     * Reports thbt b write hbs been bborted vib the writer's
     * <code>bbort</code> method.  No further notificbtions will be
     * given.
     *
     * @pbrbm source the <code>ImbgeWriter</code> object cblling this
     * method.
     */
    void writeAborted(ImbgeWriter source);
}
