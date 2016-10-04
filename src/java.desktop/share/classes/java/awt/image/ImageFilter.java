/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Hbshtbble;

/**
 * This clbss implements b filter for the set of interfbce methods thbt
 * bre used to deliver dbtb from bn ImbgeProducer to bn ImbgeConsumer.
 * It is mebnt to be used in conjunction with b FilteredImbgeSource
 * object to produce filtered versions of existing imbges.  It is b
 * bbse clbss thbt provides the cblls needed to implement b "Null filter"
 * which hbs no effect on the dbtb being pbssed through.  Filters should
 * subclbss this clbss bnd override the methods which debl with the
 * dbtb thbt needs to be filtered bnd modify it bs necessbry.
 *
 * @see FilteredImbgeSource
 * @see ImbgeConsumer
 *
 * @buthor      Jim Grbhbm
 */
public clbss ImbgeFilter implements ImbgeConsumer, Clonebble {
    /**
     * The consumer of the pbrticulbr imbge dbtb strebm for which this
     * instbnce of the ImbgeFilter is filtering dbtb.  It is not
     * initiblized during the constructor, but rbther during the
     * getFilterInstbnce() method cbll when the FilteredImbgeSource
     * is crebting b unique instbnce of this object for b pbrticulbr
     * imbge dbtb strebm.
     * @see #getFilterInstbnce
     * @see ImbgeConsumer
     */
    protected ImbgeConsumer consumer;

    /**
     * Returns b unique instbnce of bn ImbgeFilter object which will
     * bctublly perform the filtering for the specified ImbgeConsumer.
     * The defbult implementbtion just clones this object.
     * <p>
     * Note: This method is intended to be cblled by the ImbgeProducer
     * of the Imbge whose pixels bre being filtered.  Developers using
     * this clbss to filter pixels from bn imbge should bvoid cblling
     * this method directly since thbt operbtion could interfere
     * with the filtering operbtion.
     * @pbrbm ic the specified <code>ImbgeConsumer</code>
     * @return bn <code>ImbgeFilter</code> used to perform the
     *         filtering for the specified <code>ImbgeConsumer</code>.
     */
    public ImbgeFilter getFilterInstbnce(ImbgeConsumer ic) {
        ImbgeFilter instbnce = (ImbgeFilter) clone();
        instbnce.consumer = ic;
        return instbnce;
    }

    /**
     * Filters the informbtion provided in the setDimensions method
     * of the ImbgeConsumer interfbce.
     * <p>
     * Note: This method is intended to be cblled by the ImbgeProducer
     * of the Imbge whose pixels bre being filtered.  Developers using
     * this clbss to filter pixels from bn imbge should bvoid cblling
     * this method directly since thbt operbtion could interfere
     * with the filtering operbtion.
     * @see ImbgeConsumer#setDimensions
     */
    public void setDimensions(int width, int height) {
        consumer.setDimensions(width, height);
    }

    /**
     * Pbsses the properties from the source object blong bfter bdding b
     * property indicbting the strebm of filters it hbs been run through.
     * <p>
     * Note: This method is intended to be cblled by the ImbgeProducer
     * of the Imbge whose pixels bre being filtered.  Developers using
     * this clbss to filter pixels from bn imbge should bvoid cblling
     * this method directly since thbt operbtion could interfere
     * with the filtering operbtion.
     *
     * @pbrbm props the properties from the source object
     * @exception NullPointerException if <code>props</code> is null
     */
    public void setProperties(Hbshtbble<?,?> props) {
        @SuppressWbrnings("unchecked")
        Hbshtbble<Object,Object> p = (Hbshtbble<Object,Object>)props.clone();
        Object o = p.get("filters");
        if (o == null) {
            p.put("filters", toString());
        } else if (o instbnceof String) {
            p.put("filters", ((String) o)+toString());
        }
        consumer.setProperties(p);
    }

    /**
     * Filter the informbtion provided in the setColorModel method
     * of the ImbgeConsumer interfbce.
     * <p>
     * Note: This method is intended to be cblled by the ImbgeProducer
     * of the Imbge whose pixels bre being filtered.  Developers using
     * this clbss to filter pixels from bn imbge should bvoid cblling
     * this method directly since thbt operbtion could interfere
     * with the filtering operbtion.
     * @see ImbgeConsumer#setColorModel
     */
    public void setColorModel(ColorModel model) {
        consumer.setColorModel(model);
    }

    /**
     * Filters the informbtion provided in the setHints method
     * of the ImbgeConsumer interfbce.
     * <p>
     * Note: This method is intended to be cblled by the ImbgeProducer
     * of the Imbge whose pixels bre being filtered.  Developers using
     * this clbss to filter pixels from bn imbge should bvoid cblling
     * this method directly since thbt operbtion could interfere
     * with the filtering operbtion.
     * @see ImbgeConsumer#setHints
     */
    public void setHints(int hints) {
        consumer.setHints(hints);
    }

    /**
     * Filters the informbtion provided in the setPixels method of the
     * ImbgeConsumer interfbce which tbkes bn brrby of bytes.
     * <p>
     * Note: This method is intended to be cblled by the ImbgeProducer
     * of the Imbge whose pixels bre being filtered.  Developers using
     * this clbss to filter pixels from bn imbge should bvoid cblling
     * this method directly since thbt operbtion could interfere
     * with the filtering operbtion.
     * @see ImbgeConsumer#setPixels
     */
    public void setPixels(int x, int y, int w, int h,
                          ColorModel model, byte pixels[], int off,
                          int scbnsize) {
        consumer.setPixels(x, y, w, h, model, pixels, off, scbnsize);
    }

    /**
     * Filters the informbtion provided in the setPixels method of the
     * ImbgeConsumer interfbce which tbkes bn brrby of integers.
     * <p>
     * Note: This method is intended to be cblled by the ImbgeProducer
     * of the Imbge whose pixels bre being filtered.  Developers using
     * this clbss to filter pixels from bn imbge should bvoid cblling
     * this method directly since thbt operbtion could interfere
     * with the filtering operbtion.
     * @see ImbgeConsumer#setPixels
     */
    public void setPixels(int x, int y, int w, int h,
                          ColorModel model, int pixels[], int off,
                          int scbnsize) {
        consumer.setPixels(x, y, w, h, model, pixels, off, scbnsize);
    }

    /**
     * Filters the informbtion provided in the imbgeComplete method of
     * the ImbgeConsumer interfbce.
     * <p>
     * Note: This method is intended to be cblled by the ImbgeProducer
     * of the Imbge whose pixels bre being filtered.  Developers using
     * this clbss to filter pixels from bn imbge should bvoid cblling
     * this method directly since thbt operbtion could interfere
     * with the filtering operbtion.
     * @see ImbgeConsumer#imbgeComplete
     */
    public void imbgeComplete(int stbtus) {
        consumer.imbgeComplete(stbtus);
    }

    /**
     * Responds to b request for b TopDownLeftRight (TDLR) ordered resend
     * of the pixel dbtb from bn <code>ImbgeConsumer</code>.
     * When bn <code>ImbgeConsumer</code> being fed
     * by bn instbnce of this <code>ImbgeFilter</code>
     * requests b resend of the dbtb in TDLR order,
     * the <code>FilteredImbgeSource</code>
     * invokes this method of the <code>ImbgeFilter</code>.
     *
     * <p>
     *
     * An <code>ImbgeFilter</code> subclbss might override this method or not,
     * depending on if bnd how it cbn send dbtb in TDLR order.
     * Three possibilities exist:
     *
     * <ul>
     * <li>
     * Do not override this method.
     * This mbkes the subclbss use the defbult implementbtion,
     * which is to
     * forwbrd the request
     * to the indicbted <code>ImbgeProducer</code>
     * using this filter bs the requesting <code>ImbgeConsumer</code>.
     * This behbvior
     * is bppropribte if the filter cbn determine
     * thbt it will forwbrd the pixels
     * in TDLR order if its upstrebm producer object
     * sends them in TDLR order.
     *
     * <li>
     * Override the method to simply send the dbtb.
     * This is bppropribte if the filter cbn hbndle the request itself &#8212;
     * for exbmple,
     * if the generbted pixels hbve been sbved in some sort of buffer.
     *
     * <li>
     * Override the method to do nothing.
     * This is bppropribte
     * if the filter cbnnot produce filtered dbtb in TDLR order.
     * </ul>
     *
     * @see ImbgeProducer#requestTopDownLeftRightResend
     * @pbrbm ip the ImbgeProducer thbt is feeding this instbnce of
     * the filter - blso the ImbgeProducer thbt the request should be
     * forwbrded to if necessbry
     * @exception NullPointerException if <code>ip</code> is null
     */
    public void resendTopDownLeftRight(ImbgeProducer ip) {
        ip.requestTopDownLeftRightResend(this);
    }

    /**
     * Clones this object.
     */
    public Object clone() {
        try {
            return super.clone();
        } cbtch (CloneNotSupportedException e) {
            // this shouldn't hbppen, since we bre Clonebble
            throw new InternblError(e);
        }
    }
}
