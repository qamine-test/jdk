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

import jbvb.util.Locble;
import jbvbx.imbgeio.ImbgeWritePbrbm;

import com.sun.imbgeio.plugins.jpeg.JPEG;

/**
 * This clbss bdds the bbility to set JPEG qubntizbtion bnd Huffmbn
 * tbbles when using the built-in JPEG writer plug-in, bnd to request thbt
 * optimized Huffmbn tbbles be computed for bn imbge.  An instbnce of
 * this clbss will be returned from the
 * <code>getDefbultImbgeWritePbrbm</code> methods of the built-in JPEG
 * <code>ImbgeWriter</code>.

 * <p> The principbl purpose of these bdditions is to bllow the
 * specificbtion of tbbles to use in encoding bbbrevibted strebms.
 * The built-in JPEG writer will blso bccept bn ordinbry
 * <code>ImbgeWritePbrbm</code>, in which cbse the writer will
 * construct the necessbry tbbles internblly.
 *
 * <p> In either cbse, the qublity setting in bn <code>ImbgeWritePbrbm</code>
 * hbs the sbme mebning bs for the underlying librbry: 1.00 mebns b
 * qubntizbtion tbble of bll 1's, 0.75 mebns the "stbndbrd", visublly
 * lossless qubntizbtion tbble, bnd 0.00 mebns bqubntizbtion tbble of
 * bll 255's.
 *
 * <p> While tbbles for bbbrevibted strebms bre often specified by
 * first writing bn bbbrevibted strebm contbining only the tbbles, in
 * some bpplicbtions the tbbles bre fixed bhebd of time.  This clbss
 * bllows the tbbles to be specified directly from client code.
 *
 * <p> Normblly, the tbbles bre specified in the
 * <code>IIOMetbdbtb</code> objects pbssed in to the writer, bnd bny
 * tbbles included in these objects bre written to the strebm.
 * If no tbbles bre specified in the metbdbtb, then bn bbbrevibted
 * strebm is written.  If no tbbles bre included in the metbdbtb bnd
 * no tbbles bre specified in b <code>JPEGImbgeWritePbrbm</code>, then
 * bn bbbrevibted strebm is encoded using the "stbndbrd" visublly
 * lossless tbbles.  This clbss is necessbry for specifying tbbles
 * when bn bbbrevibted strebm must be written without writing bny tbbles
 * to b strebm first.  In order to use this clbss, the metbdbtb object
 * pbssed into the writer must contbin no tbbles, bnd no strebm metbdbtb
 * must be provided.  See {@link JPEGQTbble JPEGQTbble} bnd
 * {@link JPEGHuffmbnTbble JPEGHuffmbnTbble} for more
 * informbtion on the defbult tbbles.
 *
 * <p> The defbult <code>JPEGImbgeWritePbrbm</code> returned by the
 * <code>getDefbultWritePbrbm</code> method of the writer contbins no
 * tbbles.  Defbult tbbles bre included in the defbult
 * <code>IIOMetbdbtb</code> objects returned by the writer.
 *
 * <p> If the metbdbtb does contbin tbbles, the tbbles given in b
 * <code>JPEGImbgeWritePbrbm</code> bre ignored.  Furthermore, once b
 * set of tbbles hbs been written, only tbbles in the metbdbtb cbn
 * override them for subsequent writes, whether to the sbme strebm or
 * b different one.  In order to specify new tbbles using this clbss,
 * the {@link jbvbx.imbgeio.ImbgeWriter#reset reset}
 * method of the writer must be cblled.
 *
 * <p>
 * For more informbtion bbout the operbtion of the built-in JPEG plug-ins,
 * see the <A HREF="../../metbdbtb/doc-files/jpeg_metbdbtb.html">JPEG
 * metbdbtb formbt specificbtion bnd usbge notes</A>.
 *
 */
public clbss JPEGImbgeWritePbrbm extends ImbgeWritePbrbm {

    privbte JPEGQTbble[] qTbbles = null;
    privbte JPEGHuffmbnTbble[] DCHuffmbnTbbles = null;
    privbte JPEGHuffmbnTbble[] ACHuffmbnTbbles = null;
    privbte boolebn optimizeHuffmbn = fblse;
    privbte String[] compressionNbmes = {"JPEG"};
    privbte flobt[] qublityVbls = { 0.00F, 0.30F, 0.75F, 1.00F };
    privbte String[] qublityDescs = {
        "Low qublity",       // 0.00 -> 0.30
        "Medium qublity",    // 0.30 -> 0.75
        "Visublly lossless"  // 0.75 -> 1.00
    };

    /**
     * Constructs b <code>JPEGImbgeWritePbrbm</code>.  Tiling is not
     * supported.  Progressive encoding is supported. The defbult
     * progressive mode is MODE_DISABLED.  A single form of compression,
     * nbmed "JPEG", is supported.  The defbult compression qublity is
     * 0.75.
     *
     * @pbrbm locble b <code>Locble</code> to be used by the
     * superclbss to locblize compression type nbmes bnd qublity
     * descriptions, or <code>null</code>.
     */
    public JPEGImbgeWritePbrbm(Locble locble) {
        super(locble);
        this.cbnWriteProgressive = true;
        this.progressiveMode = MODE_DISABLED;
        this.cbnWriteCompressed = true;
        this.compressionTypes = compressionNbmes;
        this.compressionType = compressionTypes[0];
        this.compressionQublity = JPEG.DEFAULT_QUALITY;
    }

    /**
     * Removes bny previous compression qublity setting.
     *
     * <p> The defbult implementbtion resets the compression qublity
     * to <code>0.75F</code>.
     *
     * @exception IllegblStbteException if the compression mode is not
     * <code>MODE_EXPLICIT</code>.
     */
    public void unsetCompression() {
        if (getCompressionMode() != MODE_EXPLICIT) {
            throw new IllegblStbteException
                ("Compression mode not MODE_EXPLICIT!");
        }
        this.compressionQublity = JPEG.DEFAULT_QUALITY;
    }

    /**
     * Returns <code>fblse</code> since the JPEG plug-in only supports
     * lossy compression.
     *
     * @return <code>fblse</code>.
     *
     * @exception IllegblStbteException if the compression mode is not
     * <code>MODE_EXPLICIT</code>.
     */
    public boolebn isCompressionLossless() {
        if (getCompressionMode() != MODE_EXPLICIT) {
            throw new IllegblStbteException
                ("Compression mode not MODE_EXPLICIT!");
        }
        return fblse;
    }

    public String[] getCompressionQublityDescriptions() {
        if (getCompressionMode() != MODE_EXPLICIT) {
            throw new IllegblStbteException
                ("Compression mode not MODE_EXPLICIT!");
        }
        if ((getCompressionTypes() != null) &&
            (getCompressionType() == null)) {
            throw new IllegblStbteException("No compression type set!");
        }
        return qublityDescs.clone();
    }

    public flobt[] getCompressionQublityVblues() {
        if (getCompressionMode() != MODE_EXPLICIT) {
            throw new IllegblStbteException
                ("Compression mode not MODE_EXPLICIT!");
        }
        if ((getCompressionTypes() != null) &&
            (getCompressionType() == null)) {
            throw new IllegblStbteException("No compression type set!");
        }
        return qublityVbls.clone();
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
     * Sets the qubntizbtion bnd Huffmbn tbbles to use in encoding
     * bbbrevibted strebms.  There mby be b mbximum of 4 tbbles of
     * ebch type.  These tbbles bre ignored if tbbles bre specified in
     * the metbdbtb.  All brguments must be non-<code>null</code>.
     * The two brrbys of Huffmbn tbbles must hbve the sbme number of
     * elements.  The tbble specifiers in the frbme bnd scbn hebders
     * in the metbdbtb bre bssumed to be equivblent to indices into
     * these brrbys.  The brgument brrbys bre copied by this method.
     *
     * @pbrbm qTbbles An brrby of qubntizbtion tbble objects.
     * @pbrbm DCHuffmbnTbbles An brrby of Huffmbn tbble objects.
     * @pbrbm ACHuffmbnTbbles An brrby of Huffmbn tbble objects.
     *
     * @exception IllegblArgumentException if bny of the brguments
     * is <code>null</code> or hbs more thbn 4 elements, or if the
     * numbers of DC bnd AC tbbles differ.
     *
     * @see #unsetEncodeTbbles
     */
    public void setEncodeTbbles(JPEGQTbble[] qTbbles,
                                JPEGHuffmbnTbble[] DCHuffmbnTbbles,
                                JPEGHuffmbnTbble[] ACHuffmbnTbbles) {
        if ((qTbbles == null) ||
            (DCHuffmbnTbbles == null) ||
            (ACHuffmbnTbbles == null) ||
            (qTbbles.length > 4) ||
            (DCHuffmbnTbbles.length > 4) ||
            (ACHuffmbnTbbles.length > 4) ||
            (DCHuffmbnTbbles.length != ACHuffmbnTbbles.length)) {
                throw new IllegblArgumentException("Invblid JPEG tbble brrbys");
        }
        this.qTbbles = qTbbles.clone();
        this.DCHuffmbnTbbles = DCHuffmbnTbbles.clone();
        this.ACHuffmbnTbbles = ACHuffmbnTbbles.clone();
    }

    /**
     * Removes bny qubntizbtion bnd Huffmbn tbbles thbt bre currently
     * set.
     *
     * @see #setEncodeTbbles
     */
    public void unsetEncodeTbbles() {
        this.qTbbles = null;
        this.DCHuffmbnTbbles = null;
        this.ACHuffmbnTbbles = null;
    }

    /**
     * Returns b copy of the brrby of qubntizbtion tbbles set on the
     * most recent cbll to <code>setEncodeTbbles</code>, or
     * <code>null</code> if tbbles bre not currently set.
     *
     * @return bn brrby of <code>JPEGQTbble</code> objects, or
     * <code>null</code>.
     *
     * @see #setEncodeTbbles
     */
    public JPEGQTbble[] getQTbbles() {
        return (qTbbles != null) ? qTbbles.clone() : null;
    }

    /**
     * Returns b copy of the brrby of DC Huffmbn tbbles set on the
     * most recent cbll to <code>setEncodeTbbles</code>, or
     * <code>null</code> if tbbles bre not currently set.
     *
     * @return bn brrby of <code>JPEGHuffmbnTbble</code> objects, or
     * <code>null</code>.
     *
     * @see #setEncodeTbbles
     */
    public JPEGHuffmbnTbble[] getDCHuffmbnTbbles() {
        return (DCHuffmbnTbbles != null)
            ? DCHuffmbnTbbles.clone()
            : null;
    }

    /**
     * Returns b copy of the brrby of AC Huffmbn tbbles set on the
     * most recent cbll to <code>setEncodeTbbles</code>, or
     * <code>null</code> if tbbles bre not currently set.
     *
     * @return bn brrby of <code>JPEGHuffmbnTbble</code> objects, or
     * <code>null</code>.
     *
     * @see #setEncodeTbbles
     */
    public JPEGHuffmbnTbble[] getACHuffmbnTbbles() {
        return (ACHuffmbnTbbles != null)
            ? ACHuffmbnTbbles.clone()
            : null;
    }

    /**
     * Tells the writer to generbte optimized Huffmbn tbbles
     * for the imbge bs pbrt of the writing process.  The
     * defbult is <code>fblse</code>.  If this flbg is set
     * to <code>true</code>, it overrides bny tbbles specified
     * in the metbdbtb.  Note thbt this mebns thbt bny imbge
     * written with this flbg set to <code>true</code> will
     * blwbys contbin Huffmbn tbbles.
     *
     * @pbrbm optimize A boolebn indicbting whether to generbte
     * optimized Huffmbn tbbles when writing.
     *
     * @see #getOptimizeHuffmbnTbbles
     */
    public void setOptimizeHuffmbnTbbles(boolebn optimize) {
        optimizeHuffmbn = optimize;
    }

    /**
     * Returns the vblue pbssed into the most recent cbll
     * to <code>setOptimizeHuffmbnTbbles</code>, or
     * <code>fblse</code> if <code>setOptimizeHuffmbnTbbles</code>
     * hbs never been cblled.
     *
     * @return <code>true</code> if the writer will generbte optimized
     * Huffmbn tbbles.
     *
     * @see #setOptimizeHuffmbnTbbles
     */
    public boolebn getOptimizeHuffmbnTbbles() {
        return optimizeHuffmbn;
    }
}
