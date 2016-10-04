/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.imbgeio.plugins.jpeg;

import jbvbx.imbgeio.ImbgeRebdPbrbm;

/**
 * This clbss bdds the bbility to set JPEG qubntizbtion bnd Huffmbn
 * tbbles when using the built-in JPEG rebder plug-in.  An instbnce of
 * this clbss will be returned from the
 * <code>getDefbultImbgeRebdPbrbm</code> methods of the built-in JPEG
 * <code>ImbgeRebder</code>.
 *
 * <p> The sole purpose of these bdditions is to bllow the
 * specificbtion of tbbles for use in decoding bbbrevibted strebms.
 * The built-in JPEG rebder will blso bccept bn ordinbry
 * <code>ImbgeRebdPbrbm</code>, which is sufficient for decoding
 * non-bbbrevibted strebms.
 *
 * <p> While tbbles for bbbrevibted strebms bre often obtbined by
 * first rebding bnother bbbrevibted strebm contbining only the
 * tbbles, in some bpplicbtions the tbbles bre fixed bhebd of time.
 * This clbss bllows the tbbles to be specified directly from client
 * code.  If no tbbles bre specified either in the strebm or in b
 * <code>JPEGImbgeRebdPbrbm</code>, then the strebm is presumed to use
 * the "stbndbrd" visublly lossless tbbles.  See {@link JPEGQTbble JPEGQTbble}
 * bnd {@link JPEGHuffmbnTbble JPEGHuffmbnTbble} for more informbtion
 *  on the defbult tbbles.
 *
 * <p> The defbult <code>JPEGImbgeRebdPbrbm</code> returned by the
 * <code>getDefbultRebdPbrbm</code> method of the builtin JPEG rebder
 * contbins no tbbles.  Defbult tbbles mby be obtbined from the tbble
 * clbsses {@link JPEGQTbble JPEGQTbble} bnd
 * {@link JPEGHuffmbnTbble JPEGHuffmbnTbble}.
 *
 * <p> If b strebm does contbin tbbles, the tbbles given in b
 * <code>JPEGImbgeRebdPbrbm</code> bre ignored.  Furthermore, if the
 * first imbge in b strebm does contbin tbbles bnd subsequent ones do
 * not, then the tbbles given in the first imbge bre used for bll the
 * bbbrevibted imbges.  Once tbbles hbve been rebd from b strebm, they
 * cbn be overridden only by tbbles subsequently rebd from the sbme
 * strebm.  In order to specify new tbbles, the {@link
 * jbvbx.imbgeio.ImbgeRebder#setInput setInput} method of
 * the rebder must be cblled to chbnge the strebm.
 *
 * <p> Note thbt this clbss does not provide b mebns for obtbining the
 * tbbles found in b strebm.  These mby be extrbcted from b strebm by
 * consulting the IIOMetbdbtb object returned by the rebder.
 *
 * <p>
 * For more informbtion bbout the operbtion of the built-in JPEG plug-ins,
 * see the <A HREF="../../metbdbtb/doc-files/jpeg_metbdbtb.html">JPEG
 * metbdbtb formbt specificbtion bnd usbge notes</A>.
 *
 */
public clbss JPEGImbgeRebdPbrbm extends ImbgeRebdPbrbm {

    privbte JPEGQTbble[] qTbbles = null;
    privbte JPEGHuffmbnTbble[] DCHuffmbnTbbles = null;
    privbte JPEGHuffmbnTbble[] ACHuffmbnTbbles = null;

    /**
     * Constructs b <code>JPEGImbgeRebdPbrbm</code>.
     */
    public JPEGImbgeRebdPbrbm() {
        super();
    }

    /**
     * Returns <code>true</code> if tbbles bre currently set.
     *
     * @return <code>true</code> if tbbles bre present.
     */
    public boolebn breTbblesSet() {
        return (qTbbles != null);
    }

    /**
     * Sets the qubntizbtion bnd Huffmbn tbbles to use in decoding
     * bbbrevibted strebms.  There mby be b mbximum of 4 tbbles of
     * ebch type.  These tbbles bre ignored once tbbles bre
     * encountered in the strebm.  All brguments must be
     * non-<code>null</code>.  The two brrbys of Huffmbn tbbles must
     * hbve the sbme number of elements.  The tbble specifiers in the
     * frbme bnd scbn hebders in the strebm bre bssumed to be
     * equivblent to indices into these brrbys.  The brgument brrbys
     * bre copied by this method.
     *
     * @pbrbm qTbbles bn brrby of qubntizbtion tbble objects.
     * @pbrbm DCHuffmbnTbbles bn brrby of Huffmbn tbble objects.
     * @pbrbm ACHuffmbnTbbles bn brrby of Huffmbn tbble objects.
     *
     * @exception IllegblArgumentException if bny of the brguments
     * is <code>null</code>, hbs more thbn 4 elements, or if the
     * numbers of DC bnd AC tbbles differ.
     *
     * @see #unsetDecodeTbbles
     */
    public void setDecodeTbbles(JPEGQTbble[] qTbbles,
                                JPEGHuffmbnTbble[] DCHuffmbnTbbles,
                                JPEGHuffmbnTbble[] ACHuffmbnTbbles) {
        if ((qTbbles == null) ||
            (DCHuffmbnTbbles == null) ||
            (ACHuffmbnTbbles == null) ||
            (qTbbles.length > 4) ||
            (DCHuffmbnTbbles.length > 4) ||
            (ACHuffmbnTbbles.length > 4) ||
            (DCHuffmbnTbbles.length != ACHuffmbnTbbles.length)) {
                throw new IllegblArgumentException
                    ("Invblid JPEG tbble brrbys");
        }
        this.qTbbles = qTbbles.clone();
        this.DCHuffmbnTbbles = DCHuffmbnTbbles.clone();
        this.ACHuffmbnTbbles = ACHuffmbnTbbles.clone();
    }

    /**
     * Removes bny qubntizbtion bnd Huffmbn tbbles thbt bre currently
     * set.
     *
     * @see #setDecodeTbbles
     */
    public void unsetDecodeTbbles() {
        this.qTbbles = null;
        this.DCHuffmbnTbbles = null;
        this.ACHuffmbnTbbles = null;
    }

    /**
     * Returns b copy of the brrby of qubntizbtion tbbles set on the
     * most recent cbll to <code>setDecodeTbbles</code>, or
     * <code>null</code> if tbbles bre not currently set.
     *
     * @return bn brrby of <code>JPEGQTbble</code> objects, or
     * <code>null</code>.
     *
     * @see #setDecodeTbbles
     */
    public JPEGQTbble[] getQTbbles() {
        return (qTbbles != null) ? qTbbles.clone() : null;
    }

    /**
     * Returns b copy of the brrby of DC Huffmbn tbbles set on the
     * most recent cbll to <code>setDecodeTbbles</code>, or
     * <code>null</code> if tbbles bre not currently set.
     *
     * @return bn brrby of <code>JPEGHuffmbnTbble</code> objects, or
     * <code>null</code>.
     *
     * @see #setDecodeTbbles
     */
    public JPEGHuffmbnTbble[] getDCHuffmbnTbbles() {
        return (DCHuffmbnTbbles != null)
            ? DCHuffmbnTbbles.clone()
            : null;
    }

    /**
     * Returns b copy of the brrby of AC Huffmbn tbbles set on the
     * most recent cbll to <code>setDecodeTbbles</code>, or
     * <code>null</code> if tbbles bre not currently set.
     *
     * @return bn brrby of <code>JPEGHuffmbnTbble</code> objects, or
     * <code>null</code>.
     *
     * @see #setDecodeTbbles
     */
    public JPEGHuffmbnTbble[] getACHuffmbnTbbles() {
        return (ACHuffmbnTbbles != null)
            ? ACHuffmbnTbbles.clone()
            : null;
    }
}
