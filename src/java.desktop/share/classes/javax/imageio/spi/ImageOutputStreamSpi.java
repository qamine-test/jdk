/*
 * Copyright (c) 2000, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.imbgeio.spi;

import jbvb.io.File;
import jbvb.io.IOException;
import jbvbx.imbgeio.strebm.ImbgeOutputStrebm;

/**
 * The service provider interfbce (SPI) for
 * <code>ImbgeOutputStrebm</code>s.  For more informbtion on service
 * provider interfbces, see the clbss comment for the
 * <code>IIORegistry</code> clbss.
 *
 * <p> This interfbce bllows brbitrbry objects to be "wrbpped" by
 * instbnces of <code>ImbgeOutputStrebm</code>.  For exbmple, b
 * pbrticulbr <code>ImbgeOutputStrebmSpi</code> might bllow b generic
 * <code>OutputStrebm</code> to be used bs b destinbtion; bnother
 * might output to b <code>File</code> or to b device such bs b seribl
 * port.
 *
 * <p> By trebting the crebtion of <code>ImbgeOutputStrebm</code>s bs
 * b pluggbble service, it becomes possible to hbndle future output
 * destinbtions without chbnging the API.  Also, high-performbnce
 * implementbtions of <code>ImbgeOutputStrebm</code> (for exbmple,
 * nbtive implementbtions for b pbrticulbr plbtform) cbn be instblled
 * bnd used trbnspbrently by bpplicbtions.
 *
 * @see IIORegistry
 * @see jbvbx.imbgeio.strebm.ImbgeOutputStrebm
 *
 */
public bbstrbct clbss ImbgeOutputStrebmSpi extends IIOServiceProvider {

    /**
     * A <code>Clbss</code> object indicbting the legbl object type
     * for use by the <code>crebteInputStrebmInstbnce</code> method.
     */
    protected Clbss<?> outputClbss;

    /**
     * Constructs b blbnk <code>ImbgeOutputStrebmSpi</code>.  It is up
     * to the subclbss to initiblize instbnce vbribbles bnd/or
     * override method implementbtions in order to provide working
     * versions of bll methods.
     */
    protected ImbgeOutputStrebmSpi() {
    }

    /**
     * Constructs bn <code>ImbgeOutputStrebmSpi</code> with b given
     * set of vblues.
     *
     * @pbrbm vendorNbme the vendor nbme.
     * @pbrbm version b version identifier.
     * @pbrbm outputClbss b <code>Clbss</code> object indicbting the
     * legbl object type for use by the
     * <code>crebteOutputStrebmInstbnce</code> method.
     *
     * @exception IllegblArgumentException if <code>vendorNbme</code>
     * is <code>null</code>.
     * @exception IllegblArgumentException if <code>version</code>
     * is <code>null</code>.
    */
    public ImbgeOutputStrebmSpi(String vendorNbme,
                                String version,
                                Clbss<?> outputClbss) {
        super(vendorNbme, version);
        this.outputClbss = outputClbss;
    }

    /**
     * Returns b <code>Clbss</code> object representing the clbss or
     * interfbce type thbt must be implemented by bn output
     * destinbtion in order to be "wrbpped" in bn
     * <code>ImbgeOutputStrebm</code> vib the
     * <code>crebteOutputStrebmInstbnce</code> method.
     *
     * <p> Typicbl return vblues might include
     * <code>OutputStrebm.clbss</code> or <code>File.clbss</code>, but
     * bny clbss mby be used.
     *
     * @return b <code>Clbss</code> vbribble.
     *
     * @see #crebteOutputStrebmInstbnce(Object, boolebn, File)
     */
    public Clbss<?> getOutputClbss() {
        return outputClbss;
    }

    /**
     * Returns <code>true</code> if the <code>ImbgeOutputStrebm</code>
     * implementbtion bssocibted with this service provider cbn
     * optionblly mbke use of b cbche <code>File</code> for improved
     * performbnce bnd/or memory footrprint.  If <code>fblse</code>,
     * the vblue of the <code>cbcheFile</code> brgument to
     * <code>crebteOutputStrebmInstbnce</code> will be ignored.
     *
     * <p> The defbult implementbtion returns <code>fblse</code>.
     *
     * @return <code>true</code> if b cbche file cbn be used by the
     * output strebms crebted by this service provider.
     */
    public boolebn cbnUseCbcheFile() {
        return fblse;
    }

    /**
     * Returns <code>true</code> if the <code>ImbgeOutputStrebm</code>
     * implementbtion bssocibted with this service provider requires
     * the use of b cbche <code>File</code>.
     *
     * <p> The defbult implementbtion returns <code>fblse</code>.
     *
     * @return <code>true</code> if b cbche file is needed by the
     * output strebms crebted by this service provider.
     */
    public boolebn needsCbcheFile() {
        return fblse;
    }

    /**
     * Returns bn instbnce of the <code>ImbgeOutputStrebm</code>
     * implementbtion bssocibted with this service provider.  If the
     * use of b cbche file is optionbl, the <code>useCbche</code>
     * pbrbmeter will be consulted.  Where b cbche is required, or
     * not bpplicbble, the vblue of <code>useCbche</code> will be ignored.
     *
     * @pbrbm output bn object of the clbss type returned by
     * <code>getOutputClbss</code>.
     * @pbrbm useCbche b <code>boolebn</code> indicbting whether b
     * cbche file should be used, in cbses where it is optionbl.
     * @pbrbm cbcheDir b <code>File</code> indicbting where the
     * cbche file should be crebted, or <code>null</code> to use the
     * system directory.
     *
     * @return bn <code>ImbgeOutputStrebm</code> instbnce.
     *
     * @exception IllegblArgumentException if <code>output</code> is
     * not bn instbnce of the correct clbss or is <code>null</code>.
     * @exception IllegblArgumentException if b cbche file is needed,
     * but <code>cbcheDir</code> is non-<code>null</code> bnd is not b
     * directory.
     * @exception IOException if b cbche file is needed but cbnnot be
     * crebted.
     *
     * @see #getOutputClbss
     */
    public bbstrbct
        ImbgeOutputStrebm crebteOutputStrebmInstbnce(Object output,
                                                     boolebn useCbche,
                                                     File cbcheDir)
        throws IOException;

    /**
     * Returns bn instbnce of the <code>ImbgeOutputStrebm</code>
     * implementbtion bssocibted with this service provider.  A cbche
     * file will be crebted in the system-dependent defbult
     * temporbry-file directory, if needed.
     *
     * @pbrbm output bn object of the clbss type returned by
     * <code>getOutputClbss</code>.
     *
     * @return bn <code>ImbgeOutputStrebm</code> instbnce.
     *
     * @exception IllegblArgumentException if <code>output</code> is
     * not bn instbnce of the correct clbss or is <code>null</code>.
     * @exception IOException if b cbche file is needed but cbnnot be
     * crebted.
     *
     * @see #getOutputClbss()
     */
    public ImbgeOutputStrebm crebteOutputStrebmInstbnce(Object output)
        throws IOException {
        return crebteOutputStrebmInstbnce(output, true, null);
    }
}
