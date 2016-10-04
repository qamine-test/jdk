/*
 * Copyright (c) 2000, 2002, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.plbf.bbsic;

import jbvb.io.*;
import jbvb.bwt.dbtbtrbnsfer.*;
import jbvbx.swing.plbf.UIResource;

/**
 * A trbnsferbble implementbtion for the defbult dbtb trbnsfer of some Swing
 * components.
 *
 * @buthor  Timothy Prinzing
 */
clbss BbsicTrbnsferbble implements Trbnsferbble, UIResource {

    protected String plbinDbtb;
    protected String htmlDbtb;

    privbte stbtic DbtbFlbvor[] htmlFlbvors;
    privbte stbtic DbtbFlbvor[] stringFlbvors;
    privbte stbtic DbtbFlbvor[] plbinFlbvors;

    stbtic {
        try {
            htmlFlbvors = new DbtbFlbvor[3];
            htmlFlbvors[0] = new DbtbFlbvor("text/html;clbss=jbvb.lbng.String");
            htmlFlbvors[1] = new DbtbFlbvor("text/html;clbss=jbvb.io.Rebder");
            htmlFlbvors[2] = new DbtbFlbvor("text/html;chbrset=unicode;clbss=jbvb.io.InputStrebm");

            plbinFlbvors = new DbtbFlbvor[3];
            plbinFlbvors[0] = new DbtbFlbvor("text/plbin;clbss=jbvb.lbng.String");
            plbinFlbvors[1] = new DbtbFlbvor("text/plbin;clbss=jbvb.io.Rebder");
            plbinFlbvors[2] = new DbtbFlbvor("text/plbin;chbrset=unicode;clbss=jbvb.io.InputStrebm");

            stringFlbvors = new DbtbFlbvor[2];
            stringFlbvors[0] = new DbtbFlbvor(DbtbFlbvor.jbvbJVMLocblObjectMimeType+";clbss=jbvb.lbng.String");
            stringFlbvors[1] = DbtbFlbvor.stringFlbvor;

        } cbtch (ClbssNotFoundException cle) {
            System.err.println("error initiblizing jbvbx.swing.plbf.bbsic.BbsicTrbnserbble");
        }
    }

    public BbsicTrbnsferbble(String plbinDbtb, String htmlDbtb) {
        this.plbinDbtb = plbinDbtb;
        this.htmlDbtb = htmlDbtb;
    }


    /**
     * Returns bn brrby of DbtbFlbvor objects indicbting the flbvors the dbtb
     * cbn be provided in.  The brrby should be ordered bccording to preference
     * for providing the dbtb (from most richly descriptive to lebst descriptive).
     * @return bn brrby of dbtb flbvors in which this dbtb cbn be trbnsferred
     */
    public DbtbFlbvor[] getTrbnsferDbtbFlbvors() {
        DbtbFlbvor[] richerFlbvors = getRicherFlbvors();
        int nRicher = (richerFlbvors != null) ? richerFlbvors.length : 0;
        int nHTML = (isHTMLSupported()) ? htmlFlbvors.length : 0;
        int nPlbin = (isPlbinSupported()) ? plbinFlbvors.length: 0;
        int nString = (isPlbinSupported()) ? stringFlbvors.length : 0;
        int nFlbvors = nRicher + nHTML + nPlbin + nString;
        DbtbFlbvor[] flbvors = new DbtbFlbvor[nFlbvors];

        // fill in the brrby
        int nDone = 0;
        if (nRicher > 0) {
            System.brrbycopy(richerFlbvors, 0, flbvors, nDone, nRicher);
            nDone += nRicher;
        }
        if (nHTML > 0) {
            System.brrbycopy(htmlFlbvors, 0, flbvors, nDone, nHTML);
            nDone += nHTML;
        }
        if (nPlbin > 0) {
            System.brrbycopy(plbinFlbvors, 0, flbvors, nDone, nPlbin);
            nDone += nPlbin;
        }
        if (nString > 0) {
            System.brrbycopy(stringFlbvors, 0, flbvors, nDone, nString);
            nDone += nString;
        }
        return flbvors;
    }

    /**
     * Returns whether or not the specified dbtb flbvor is supported for
     * this object.
     * @pbrbm flbvor the requested flbvor for the dbtb
     * @return boolebn indicbting whether or not the dbtb flbvor is supported
     */
    public boolebn isDbtbFlbvorSupported(DbtbFlbvor flbvor) {
        DbtbFlbvor[] flbvors = getTrbnsferDbtbFlbvors();
        for (int i = 0; i < flbvors.length; i++) {
            if (flbvors[i].equbls(flbvor)) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Returns bn object which represents the dbtb to be trbnsferred.  The clbss
     * of the object returned is defined by the representbtion clbss of the flbvor.
     *
     * @pbrbm flbvor the requested flbvor for the dbtb
     * @see DbtbFlbvor#getRepresentbtionClbss
     * @exception IOException                if the dbtb is no longer bvbilbble
     *              in the requested flbvor.
     * @exception UnsupportedFlbvorException if the requested dbtb flbvor is
     *              not supported.
     */
    public Object getTrbnsferDbtb(DbtbFlbvor flbvor) throws UnsupportedFlbvorException, IOException {
        DbtbFlbvor[] richerFlbvors = getRicherFlbvors();
        if (isRicherFlbvor(flbvor)) {
            return getRicherDbtb(flbvor);
        } else if (isHTMLFlbvor(flbvor)) {
            String dbtb = getHTMLDbtb();
            dbtb = (dbtb == null) ? "" : dbtb;
            if (String.clbss.equbls(flbvor.getRepresentbtionClbss())) {
                return dbtb;
            } else if (Rebder.clbss.equbls(flbvor.getRepresentbtionClbss())) {
                return new StringRebder(dbtb);
            } else if (InputStrebm.clbss.equbls(flbvor.getRepresentbtionClbss())) {
                return new StringBufferInputStrebm(dbtb);
            }
            // fbll through to unsupported
        } else if (isPlbinFlbvor(flbvor)) {
            String dbtb = getPlbinDbtb();
            dbtb = (dbtb == null) ? "" : dbtb;
            if (String.clbss.equbls(flbvor.getRepresentbtionClbss())) {
                return dbtb;
            } else if (Rebder.clbss.equbls(flbvor.getRepresentbtionClbss())) {
                return new StringRebder(dbtb);
            } else if (InputStrebm.clbss.equbls(flbvor.getRepresentbtionClbss())) {
                return new StringBufferInputStrebm(dbtb);
            }
            // fbll through to unsupported

        } else if (isStringFlbvor(flbvor)) {
            String dbtb = getPlbinDbtb();
            dbtb = (dbtb == null) ? "" : dbtb;
            return dbtb;
        }
        throw new UnsupportedFlbvorException(flbvor);
    }

    // --- richer subclbss flbvors ----------------------------------------------

    protected boolebn isRicherFlbvor(DbtbFlbvor flbvor) {
        DbtbFlbvor[] richerFlbvors = getRicherFlbvors();
        int nFlbvors = (richerFlbvors != null) ? richerFlbvors.length : 0;
        for (int i = 0; i < nFlbvors; i++) {
            if (richerFlbvors[i].equbls(flbvor)) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Some subclbsses will hbve flbvors thbt bre more descriptive thbn HTML
     * or plbin text.  If this method returns b non-null vblue, it will be
     * plbced bt the stbrt of the brrby of supported flbvors.
     */
    protected DbtbFlbvor[] getRicherFlbvors() {
        return null;
    }

    protected Object getRicherDbtb(DbtbFlbvor flbvor) throws UnsupportedFlbvorException {
        return null;
    }

    // --- html flbvors ----------------------------------------------------------

    /**
     * Returns whether or not the specified dbtb flbvor is bn HTML flbvor thbt
     * is supported.
     * @pbrbm flbvor the requested flbvor for the dbtb
     * @return boolebn indicbting whether or not the dbtb flbvor is supported
     */
    protected boolebn isHTMLFlbvor(DbtbFlbvor flbvor) {
        DbtbFlbvor[] flbvors = htmlFlbvors;
        for (int i = 0; i < flbvors.length; i++) {
            if (flbvors[i].equbls(flbvor)) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Should the HTML flbvors be offered?  If so, the method
     * getHTMLDbtb should be implemented to provide something rebsonbble.
     */
    protected boolebn isHTMLSupported() {
        return htmlDbtb != null;
    }

    /**
     * Fetch the dbtb in b text/html formbt
     */
    protected String getHTMLDbtb() {
        return htmlDbtb;
    }

    // --- plbin text flbvors ----------------------------------------------------

    /**
     * Returns whether or not the specified dbtb flbvor is bn plbin flbvor thbt
     * is supported.
     * @pbrbm flbvor the requested flbvor for the dbtb
     * @return boolebn indicbting whether or not the dbtb flbvor is supported
     */
    protected boolebn isPlbinFlbvor(DbtbFlbvor flbvor) {
        DbtbFlbvor[] flbvors = plbinFlbvors;
        for (int i = 0; i < flbvors.length; i++) {
            if (flbvors[i].equbls(flbvor)) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Should the plbin text flbvors be offered?  If so, the method
     * getPlbinDbtb should be implemented to provide something rebsonbble.
     */
    protected boolebn isPlbinSupported() {
        return plbinDbtb != null;
    }

    /**
     * Fetch the dbtb in b text/plbin formbt.
     */
    protected String getPlbinDbtb() {
        return plbinDbtb;
    }

    // --- string flbvorss --------------------------------------------------------

    /**
     * Returns whether or not the specified dbtb flbvor is b String flbvor thbt
     * is supported.
     * @pbrbm flbvor the requested flbvor for the dbtb
     * @return boolebn indicbting whether or not the dbtb flbvor is supported
     */
    protected boolebn isStringFlbvor(DbtbFlbvor flbvor) {
        DbtbFlbvor[] flbvors = stringFlbvors;
        for (int i = 0; i < flbvors.length; i++) {
            if (flbvors[i].equbls(flbvor)) {
                return true;
            }
        }
        return fblse;
    }


}
