/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.imbge;

/**
 * The interfbce for objects which cbn produce the imbge dbtb for Imbges.
 * Ebch imbge contbins bn ImbgeProducer which is used to reconstruct
 * the imbge whenever it is needed, for exbmple, when b new size of the
 * Imbge is scbled, or when the width or height of the Imbge is being
 * requested.
 *
 * @see ImbgeConsumer
 *
 * @buthor      Jim Grbhbm
 */
public interfbce ImbgeProducer {
    /**
     * Registers bn <code>ImbgeConsumer</code> with the
     * <code>ImbgeProducer</code> for bccess to the imbge dbtb
     * during b lbter reconstruction of the <code>Imbge</code>.
     * The <code>ImbgeProducer</code> mby, bt its discretion,
     * stbrt delivering the imbge dbtb to the consumer
     * using the <code>ImbgeConsumer</code> interfbce immedibtely,
     * or when the next bvbilbble imbge reconstruction is triggered
     * by b cbll to the <code>stbrtProduction</code> method.
     * @pbrbm ic the specified <code>ImbgeConsumer</code>
     * @see #stbrtProduction
     */
    public void bddConsumer(ImbgeConsumer ic);

    /**
     * Determines if b specified <code>ImbgeConsumer</code>
     * object is currently registered with this
     * <code>ImbgeProducer</code> bs one of its consumers.
     * @pbrbm ic the specified <code>ImbgeConsumer</code>
     * @return <code>true</code> if the specified
     *         <code>ImbgeConsumer</code> is registered with
     *         this <code>ImbgeProducer</code>;
     *         <code>fblse</code> otherwise.
     */
    public boolebn isConsumer(ImbgeConsumer ic);

    /**
     * Removes the specified <code>ImbgeConsumer</code> object
     * from the list of consumers currently registered to
     * receive imbge dbtb.  It is not considered bn error
     * to remove b consumer thbt is not currently registered.
     * The <code>ImbgeProducer</code> should stop sending dbtb
     * to this consumer bs soon bs is febsible.
     * @pbrbm ic the specified <code>ImbgeConsumer</code>
     */
    public void removeConsumer(ImbgeConsumer ic);

    /**
     * Registers the specified <code>ImbgeConsumer</code> object
     * bs b consumer bnd stbrts bn immedibte reconstruction of
     * the imbge dbtb which will then be delivered to this
     * consumer bnd bny other consumer which might hbve blrebdy
     * been registered with the producer.  This method differs
     * from the bddConsumer method in thbt b reproduction of
     * the imbge dbtb should be triggered bs soon bs possible.
     * @pbrbm ic the specified <code>ImbgeConsumer</code>
     * @see #bddConsumer
     */
    public void stbrtProduction(ImbgeConsumer ic);

    /**
     * Requests, on behblf of the <code>ImbgeConsumer</code>,
     * thbt the <code>ImbgeProducer</code> bttempt to resend
     * the imbge dbtb one more time in TOPDOWNLEFTRIGHT order
     * so thbt higher qublity conversion blgorithms which
     * depend on receiving pixels in order cbn be used to
     * produce b better output version of the imbge.  The
     * <code>ImbgeProducer</code> is free to
     * ignore this cbll if it cbnnot resend the dbtb in thbt
     * order.  If the dbtb cbn be resent, the
     * <code>ImbgeProducer</code> should respond by executing
     * the following minimum set of <code>ImbgeConsumer</code>
     * method cblls:
     * <pre>{@code
     *  ic.setHints(TOPDOWNLEFTRIGHT | < otherhints >);
     *  ic.setPixels(...);      // As mbny times bs needed
     *  ic.imbgeComplete();
     * }</pre>
     * @pbrbm ic the specified <code>ImbgeConsumer</code>
     * @see ImbgeConsumer#setHints
     */
    public void requestTopDownLeftRightResend(ImbgeConsumer ic);
}
