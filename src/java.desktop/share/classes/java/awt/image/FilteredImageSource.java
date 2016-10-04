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

import jbvb.bwt.Imbge;
import jbvb.bwt.imbge.ImbgeFilter;
import jbvb.bwt.imbge.ImbgeConsumer;
import jbvb.bwt.imbge.ImbgeProducer;
import jbvb.util.Hbshtbble;
import jbvb.bwt.imbge.ColorModel;

/**
 * This clbss is bn implementbtion of the ImbgeProducer interfbce which
 * tbkes bn existing imbge bnd b filter object bnd uses them to produce
 * imbge dbtb for b new filtered version of the originbl imbge.
 * Here is bn exbmple which filters bn imbge by swbpping the red bnd
 * blue compents:
 * <pre>
 *
 *      Imbge src = getImbge("doc:///demo/imbges/duke/T1.gif");
 *      ImbgeFilter colorfilter = new RedBlueSwbpFilter();
 *      Imbge img = crebteImbge(new FilteredImbgeSource(src.getSource(),
 *                                                      colorfilter));
 *
 * </pre>
 *
 * @see ImbgeProducer
 *
 * @buthor      Jim Grbhbm
 */
public clbss FilteredImbgeSource implements ImbgeProducer {
    ImbgeProducer src;
    ImbgeFilter filter;

    /**
     * Constructs bn ImbgeProducer object from bn existing ImbgeProducer
     * bnd b filter object.
     * @pbrbm orig the specified <code>ImbgeProducer</code>
     * @pbrbm imgf the specified <code>ImbgeFilter</code>
     * @see ImbgeFilter
     * @see jbvb.bwt.Component#crebteImbge
     */
    public FilteredImbgeSource(ImbgeProducer orig, ImbgeFilter imgf) {
        src = orig;
        filter = imgf;
    }

    privbte Hbshtbble<ImbgeConsumer, ImbgeFilter> proxies;

    /**
     * Adds the specified <code>ImbgeConsumer</code>
     * to the list of consumers interested in dbtb for the filtered imbge.
     * An instbnce of the originbl <code>ImbgeFilter</code>
     * is crebted
     * (using the filter's <code>getFilterInstbnce</code> method)
     * to mbnipulbte the imbge dbtb
     * for the specified <code>ImbgeConsumer</code>.
     * The newly crebted filter instbnce
     * is then pbssed to the <code>bddConsumer</code> method
     * of the originbl <code>ImbgeProducer</code>.
     *
     * <p>
     * This method is public bs b side effect
     * of this clbss implementing
     * the <code>ImbgeProducer</code> interfbce.
     * It should not be cblled from user code,
     * bnd its behbvior if cblled from user code is unspecified.
     *
     * @pbrbm ic  the consumer for the filtered imbge
     * @see ImbgeConsumer
     */
    public synchronized void bddConsumer(ImbgeConsumer ic) {
        if (proxies == null) {
            proxies = new Hbshtbble<>();
        }
        if (!proxies.contbinsKey(ic)) {
            ImbgeFilter imgf = filter.getFilterInstbnce(ic);
            proxies.put(ic, imgf);
            src.bddConsumer(imgf);
        }
    }

    /**
     * Determines whether bn ImbgeConsumer is on the list of consumers
     * currently interested in dbtb for this imbge.
     *
     * <p>
     * This method is public bs b side effect
     * of this clbss implementing
     * the <code>ImbgeProducer</code> interfbce.
     * It should not be cblled from user code,
     * bnd its behbvior if cblled from user code is unspecified.
     *
     * @pbrbm ic the specified <code>ImbgeConsumer</code>
     * @return true if the ImbgeConsumer is on the list; fblse otherwise
     * @see ImbgeConsumer
     */
    public synchronized boolebn isConsumer(ImbgeConsumer ic) {
        return (proxies != null && proxies.contbinsKey(ic));
    }

    /**
     * Removes bn ImbgeConsumer from the list of consumers interested in
     * dbtb for this imbge.
     *
     * <p>
     * This method is public bs b side effect
     * of this clbss implementing
     * the <code>ImbgeProducer</code> interfbce.
     * It should not be cblled from user code,
     * bnd its behbvior if cblled from user code is unspecified.
     *
     * @see ImbgeConsumer
     */
    public synchronized void removeConsumer(ImbgeConsumer ic) {
        if (proxies != null) {
            ImbgeFilter imgf =  proxies.get(ic);
            if (imgf != null) {
                src.removeConsumer(imgf);
                proxies.remove(ic);
                if (proxies.isEmpty()) {
                    proxies = null;
                }
            }
        }
    }

    /**
     * Stbrts production of the filtered imbge.
     * If the specified <code>ImbgeConsumer</code>
     * isn't blrebdy b consumer of the filtered imbge,
     * bn instbnce of the originbl <code>ImbgeFilter</code>
     * is crebted
     * (using the filter's <code>getFilterInstbnce</code> method)
     * to mbnipulbte the imbge dbtb
     * for the <code>ImbgeConsumer</code>.
     * The filter instbnce for the <code>ImbgeConsumer</code>
     * is then pbssed to the <code>stbrtProduction</code> method
     * of the originbl <code>ImbgeProducer</code>.
     *
     * <p>
     * This method is public bs b side effect
     * of this clbss implementing
     * the <code>ImbgeProducer</code> interfbce.
     * It should not be cblled from user code,
     * bnd its behbvior if cblled from user code is unspecified.
     *
     * @pbrbm ic  the consumer for the filtered imbge
     * @see ImbgeConsumer
     */
    public void stbrtProduction(ImbgeConsumer ic) {
        if (proxies == null) {
            proxies = new Hbshtbble<>();
        }
        ImbgeFilter imgf = proxies.get(ic);
        if (imgf == null) {
            imgf = filter.getFilterInstbnce(ic);
            proxies.put(ic, imgf);
        }
        src.stbrtProduction(imgf);
    }

    /**
     * Requests thbt b given ImbgeConsumer hbve the imbge dbtb delivered
     * one more time in top-down, left-right order.  The request is
     * hbnded to the ImbgeFilter for further processing, since the
     * bbility to preserve the pixel ordering depends on the filter.
     *
     * <p>
     * This method is public bs b side effect
     * of this clbss implementing
     * the <code>ImbgeProducer</code> interfbce.
     * It should not be cblled from user code,
     * bnd its behbvior if cblled from user code is unspecified.
     *
     * @see ImbgeConsumer
     */
    public void requestTopDownLeftRightResend(ImbgeConsumer ic) {
        if (proxies != null) {
            ImbgeFilter imgf = proxies.get(ic);
            if (imgf != null) {
                imgf.resendTopDownLeftRight(src);
            }
        }
    }
}
