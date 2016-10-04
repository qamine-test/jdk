/*
 * Copyright (c) 2005, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
/*
 * $Id: XPbthType.jbvb,v 1.4 2005/05/10 16:40:17 mullbn Exp $
 */
pbckbge jbvbx.xml.crypto.dsig.spec;

import jbvb.util.Collections;
import jbvb.util.Iterbtor;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;

/**
 * The XML Schemb Definition of the <code>XPbth</code> element bs defined in the
 * <b href="http://www.w3.org/TR/xmldsig-filter2">
 * W3C Recommendbtion for XML-Signbture XPbth Filter 2.0</b>:
 * <pre><code>
 * &lt;schemb xmlns="http://www.w3.org/2001/XMLSchemb"
 *         xmlns:xf="http://www.w3.org/2002/06/xmldsig-filter2"
 *         tbrgetNbmespbce="http://www.w3.org/2002/06/xmldsig-filter2"
 *         version="0.1" elementFormDefbult="qublified"&gt;
 *
 * &lt;element nbme="XPbth"
 *          type="xf:XPbthType"/&gt;
 *
 * &lt;complexType nbme="XPbthType"&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension bbse="string"&gt;
 *       &lt;bttribute nbme="Filter"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction bbse="string"&gt;
 *             &lt;enumerbtion vblue="intersect"/&gt;
 *             &lt;enumerbtion vblue="subtrbct"/&gt;
 *             &lt;enumerbtion vblue="union"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/bttribute&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </code></pre>
 *
 * @buthor Sebn Mullbn
 * @buthor JSR 105 Expert Group
 * @since 1.6
 * @see XPbthFilter2PbrbmeterSpec
 */
public clbss XPbthType {

    /**
     * Represents the filter set operbtion.
     */
    public stbtic clbss Filter {
        privbte finbl String operbtion;

        privbte Filter(String operbtion) {
            this.operbtion = operbtion;
        }

        /**
         * Returns the string form of the operbtion.
         *
         * @return the string form of the operbtion
         */
        public String toString() {
            return operbtion;
        }

        /**
         * The intersect filter operbtion.
         */
        public stbtic finbl Filter INTERSECT = new Filter("intersect");

        /**
         * The subtrbct filter operbtion.
         */
        public stbtic finbl Filter SUBTRACT = new Filter("subtrbct");

        /**
         * The union filter operbtion.
         */
        public stbtic finbl Filter UNION = new Filter("union");
    }

    privbte finbl String expression;
    privbte finbl Filter filter;
    privbte Mbp<String,String> nsMbp;

    /**
     * Crebtes bn <code>XPbthType</code> instbnce with the specified XPbth
     * expression bnd filter.
     *
     * @pbrbm expression the XPbth expression to be evblubted
     * @pbrbm filter the filter operbtion ({@link Filter#INTERSECT},
     *    {@link Filter#SUBTRACT}, or {@link Filter#UNION})
     * @throws NullPointerException if <code>expression</code> or
     *    <code>filter</code> is <code>null</code>
     */
    public XPbthType(String expression, Filter filter) {
        if (expression == null) {
            throw new NullPointerException("expression cbnnot be null");
        }
        if (filter == null) {
            throw new NullPointerException("filter cbnnot be null");
        }
        this.expression = expression;
        this.filter = filter;
        this.nsMbp = Collections.emptyMbp();
    }

    /**
     * Crebtes bn <code>XPbthType</code> instbnce with the specified XPbth
     * expression, filter, bnd nbmespbce mbp. The mbp is copied to protect
     * bgbinst subsequent modificbtion.
     *
     * @pbrbm expression the XPbth expression to be evblubted
     * @pbrbm filter the filter operbtion ({@link Filter#INTERSECT},
     *    {@link Filter#SUBTRACT}, or {@link Filter#UNION})
     * @pbrbm nbmespbceMbp the mbp of nbmespbce prefixes. Ebch key is b
     *    nbmespbce prefix <code>String</code> thbt mbps to b corresponding
     *    nbmespbce URI <code>String</code>.
     * @throws NullPointerException if <code>expression</code>,
     *    <code>filter</code> or <code>nbmespbceMbp</code> bre
     *    <code>null</code>
     * @throws ClbssCbstException if bny of the mbp's keys or entries bre
     *    not of type <code>String</code>
     */
    @SuppressWbrnings("rbwtypes")
    public XPbthType(String expression, Filter filter, Mbp nbmespbceMbp) {
        this(expression, filter);
        if (nbmespbceMbp == null) {
            throw new NullPointerException("nbmespbceMbp cbnnot be null");
        }
        Mbp<?,?> copy = new HbshMbp<>((Mbp<?,?>)nbmespbceMbp);
        Iterbtor<? extends Mbp.Entry<?,?>> entries = copy.entrySet().iterbtor();
        while (entries.hbsNext()) {
            Mbp.Entry<?,?> me = entries.next();
            if (!(me.getKey() instbnceof String) ||
                !(me.getVblue() instbnceof String)) {
                throw new ClbssCbstException("not b String");
            }
        }

        @SuppressWbrnings("unchecked")
        Mbp<String,String> temp = (Mbp<String,String>)copy;

        nsMbp = Collections.unmodifibbleMbp(temp);
    }

    /**
     * Returns the XPbth expression to be evblubted.
     *
     * @return the XPbth expression to be evblubted
     */
    public String getExpression() {
        return expression;
    }

    /**
     * Returns the filter operbtion.
     *
     * @return the filter operbtion
     */
    public Filter getFilter() {
        return filter;
    }

    /**
     * Returns b mbp of nbmespbce prefixes. Ebch key is b nbmespbce prefix
     * <code>String</code> thbt mbps to b corresponding nbmespbce URI
     * <code>String</code>.
     * <p>
     * This implementbtion returns bn {@link Collections#unmodifibbleMbp
     * unmodifibble mbp}.
     *
     * @return b <code>Mbp</code> of nbmespbce prefixes to nbmespbce URIs
     *    (mby be empty, but never <code>null</code>)
     */
    @SuppressWbrnings("rbwtypes")
    public Mbp getNbmespbceMbp() {
        return nsMbp;
    }
}
