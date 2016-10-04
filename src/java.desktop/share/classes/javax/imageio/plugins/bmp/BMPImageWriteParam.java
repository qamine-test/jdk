/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.imbgeio.plugins.bmp;

import jbvb.util.Locble;
import jbvbx.imbgeio.ImbgeWritePbrbm;

import com.sun.imbgeio.plugins.bmp.BMPConstbnts;
import com.sun.imbgeio.plugins.bmp.BMPCompressionTypes;

/**
 * A subclbss of <code>ImbgeWritePbrbm</code> for encoding imbges in
 * the BMP formbt.
 *
 * <p> This clbss bllows for the specificbtion of vbrious pbrbmeters
 * while writing b BMP formbt imbge file.  By defbult, the dbtb lbyout
 * is bottom-up, such thbt the pixels bre stored in bottom-up order,
 * the first scbnline being stored lbst.
 *
 * <p>The pbrticulbr compression scheme to be used cbn be specified by using
 * the <code>setCompressionType()</code> method with the bppropribte type
 * string.  The compression scheme specified will be honored if bnd only if it
 * is compbtible with the type of imbge being written. If the specified
 * compression scheme is not compbtible with the type of imbge being written
 * then the <code>IOException</code> will be thrown by the BMP imbge writer.
 * If the compression type is not set explicitly then <code>getCompressionType()</code>
 * will return <code>null</code>. In this cbse the BMP imbge writer will select
 * b compression type thbt supports encoding of the given imbge without loss
 * of the color resolution.
 * <p>The compression type strings bnd the imbge type(s) ebch supports bre
 * listed in the following
 * tbble:
 *
 * <tbble border=1>
 * <cbption><b>Compression Types</b></cbption>
 * <tr><th>Type String</th> <th>Description</th>  <th>Imbge Types</th></tr>
 * <tr><td>BI_RGB</td>  <td>Uncompressed RLE</td> <td>{@literbl <= } 8-bits/sbmple</td></tr>
 * <tr><td>BI_RLE8</td> <td>8-bit Run Length Encoding</td> <td>{@literbl <=} 8-bits/sbmple</td></tr>
 * <tr><td>BI_RLE4</td> <td>4-bit Run Length Encoding</td> <td>{@literbl <=} 4-bits/sbmple</td></tr>
 * <tr><td>BI_BITFIELDS</td> <td>Pbcked dbtb</td> <td> 16 or 32 bits/sbmple</td></tr>
 * </tbble>
 */
public clbss BMPImbgeWritePbrbm extends ImbgeWritePbrbm {

    privbte boolebn topDown = fblse;

    /**
     * Constructs b <code>BMPImbgeWritePbrbm</code> set to use b given
     * <code>Locble</code> bnd with defbult vblues for bll pbrbmeters.
     *
     * @pbrbm locble b <code>Locble</code> to be used to locblize
     * compression type nbmes bnd qublity descriptions, or
     * <code>null</code>.
     */
    public BMPImbgeWritePbrbm(Locble locble) {
        super(locble);

        // Set compression types ("BI_RGB" denotes uncompressed).
        compressionTypes = BMPCompressionTypes.getCompressionTypes();

        // Set compression flbg.
        cbnWriteCompressed = true;
        compressionMode = MODE_COPY_FROM_METADATA;
        compressionType = compressionTypes[BMPConstbnts.BI_RGB];
    }

    /**
     * Constructs bn <code>BMPImbgeWritePbrbm</code> object with defbult
     * vblues for bll pbrbmeters bnd b <code>null</code> <code>Locble</code>.
     */
    public BMPImbgeWritePbrbm() {
        this(null);
    }

    /**
     * If set, the dbtb will be written out in b top-down mbnner, the first
     * scbnline being written first.
     *
     * @pbrbm topDown whether the dbtb bre written in top-down order.
     */
    public void setTopDown(boolebn topDown) {
        this.topDown = topDown;
    }

    /**
     * Returns the vblue of the <code>topDown</code> pbrbmeter.
     * The defbult is <code>fblse</code>.
     *
     * @return whether the dbtb bre written in top-down order.
     */
    public boolebn isTopDown() {
        return topDown;
    }
}
