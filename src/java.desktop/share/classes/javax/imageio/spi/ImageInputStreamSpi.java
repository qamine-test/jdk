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
import jbvbx.imbgeio.strebm.ImbgeInputStrebm;

/**
 * The service provider interfbce (SPI) for
 * <code>ImbgeInputStrebm</code>s.  For more informbtion on service
 * provider interfbces, see the clbss comment for the
 * <code>IIORegistry</code> clbss.
 *
 * <p> This interfbce bllows brbitrbry objects to be "wrbpped" by
 * instbnces of <code>ImbgeInputStrebm</code>.  For exbmple,
 * b pbrticulbr <code>ImbgeInputStrebmSpi</code> might bllow
 * b generic <code>InputStrebm</code> to be used bs bn input source;
 * bnother might tbke input from b <code>URL</code>.
 *
 * <p> By trebting the crebtion of <code>ImbgeInputStrebm</code>s bs b
 * pluggbble service, it becomes possible to hbndle future input
 * sources without chbnging the API.  Also, high-performbnce
 * implementbtions of <code>ImbgeInputStrebm</code> (for exbmple,
 * nbtive implementbtions for b pbrticulbr plbtform) cbn be instblled
 * bnd used trbnspbrently by bpplicbtions.
 *
 * @see IIORegistry
 * @see jbvbx.imbgeio.strebm.ImbgeInputStrebm
 *
 */
public bbstrbct clbss ImbgeInputStrebmSpi extends IIOServiceProvider {

    /**
     * A <code>Clbss</code> object indicbting the legbl object type
     * for use by the <code>crebteInputStrebmInstbnce</code> method.
     */
    protected Clbss<?> inputClbss;

    /**
     * Constructs b blbnk <code>ImbgeInputStrebmSpi</code>.  It is up
     * to the subclbss to initiblize instbnce vbribbles bnd/or
     * override method implementbtions in order to provide working
     * versions of bll methods.
     */
    protected ImbgeInputStrebmSpi() {
    }

    /**
     * Constructs bn <code>ImbgeInputStrebmSpi</code> with b given set
     * of vblues.
     *
     * @pbrbm vendorNbme the vendor nbme.
     * @pbrbm version b version identifier.
     * @pbrbm inputClbss b <code>Clbss</code> object indicbting the
     * legbl object type for use by the
     * <code>crebteInputStrebmInstbnce</code> method.
     *
     * @exception IllegblArgumentException if <code>vendorNbme</code>
     * is <code>null</code>.
     * @exception IllegblArgumentException if <code>version</code>
     * is <code>null</code>.
     */
    public ImbgeInputStrebmSpi(String vendorNbme,
                               String version,
                               Clbss<?> inputClbss) {
        super(vendorNbme, version);
        this.inputClbss = inputClbss;
    }

    /**
     * Returns b <code>Clbss</code> object representing the clbss or
     * interfbce type thbt must be implemented by bn input source in
     * order to be "wrbpped" in bn <code>ImbgeInputStrebm</code> vib
     * the <code>crebteInputStrebmInstbnce</code> method.
     *
     * <p> Typicbl return vblues might include
     * <code>InputStrebm.clbss</code> or <code>URL.clbss</code>, but
     * bny clbss mby be used.
     *
     * @return b <code>Clbss</code> vbribble.
     *
     * @see #crebteInputStrebmInstbnce(Object, boolebn, File)
     */
    public Clbss<?> getInputClbss() {
        return inputClbss;
    }

    /**
     * Returns <code>true</code> if the <code>ImbgeInputStrebm</code>
     * implementbtion bssocibted with this service provider cbn
     * optionblly mbke use of b cbche file for improved performbnce
     * bnd/or memory footrprint.  If <code>fblse</code>, the vblue of
     * the <code>useCbche</code> brgument to
     * <code>crebteInputStrebmInstbnce</code> will be ignored.
     *
     * <p> The defbult implementbtion returns <code>fblse</code>.
     *
     * @return <code>true</code> if b cbche file cbn be used by the
     * input strebms crebted by this service provider.
     */
    public boolebn cbnUseCbcheFile() {
        return fblse;
    }

    /**
     * Returns <code>true</code> if the <code>ImbgeInputStrebm</code>
     * implementbtion bssocibted with this service provider requires
     * the use of b cbche <code>File</code>.  If <code>true</code>,
     * the vblue of the <code>useCbche</code> brgument to
     * <code>crebteInputStrebmInstbnce</code> will be ignored.
     *
     * <p> The defbult implementbtion returns <code>fblse</code>.
     *
     * @return <code>true</code> if b cbche file is needed by the
     * input strebms crebted by this service provider.
     */
    public boolebn needsCbcheFile() {
        return fblse;
    }

    /**
     * Returns bn instbnce of the <code>ImbgeInputStrebm</code>
     * implementbtion bssocibted with this service provider.  If the
     * use of b cbche file is optionbl, the <code>useCbche</code>
     * pbrbmeter will be consulted.  Where b cbche is required, or
     * not bpplicbble, the vblue of <code>useCbche</code> will be ignored.
     *
     * @pbrbm input bn object of the clbss type returned by
     * <code>getInputClbss</code>.
     * @pbrbm useCbche b <code>boolebn</code> indicbting whether b
     * cbche file should be used, in cbses where it is optionbl.
     * @pbrbm cbcheDir b <code>File</code> indicbting where the
     * cbche file should be crebted, or <code>null</code> to use the
     * system directory.
     *
     * @return bn <code>ImbgeInputStrebm</code> instbnce.
     *
     * @exception IllegblArgumentException if <code>input</code> is
     * not bn instbnce of the correct clbss or is <code>null</code>.
     * @exception IllegblArgumentException if b cbche file is needed
     * but <code>cbcheDir</code> is non-<code>null</code> bnd is not b
     * directory.
     * @exception IOException if b cbche file is needed but cbnnot be
     * crebted.
     *
     * @see #getInputClbss
     * @see #cbnUseCbcheFile
     * @see #needsCbcheFile
     */
    public bbstrbct ImbgeInputStrebm
        crebteInputStrebmInstbnce(Object input,
                                  boolebn useCbche,
                                  File cbcheDir) throws IOException;

    /**
     * Returns bn instbnce of the <code>ImbgeInputStrebm</code>
     * implementbtion bssocibted with this service provider.  A cbche
     * file will be crebted in the system-dependent defbult
     * temporbry-file directory, if needed.
     *
     * @pbrbm input bn object of the clbss type returned by
     * <code>getInputClbss</code>.
     *
     * @return bn <code>ImbgeInputStrebm</code> instbnce.
     *
     * @exception IllegblArgumentException if <code>input</code> is
     * not bn instbnce of the correct clbss or is <code>null</code>.
     * @exception IOException if b cbche file is needed but cbnnot be
     * crebted.
     *
     * @see #getInputClbss()
     */
    public ImbgeInputStrebm crebteInputStrebmInstbnce(Object input)
        throws IOException {
        return crebteInputStrebmInstbnce(input, true, null);
    }
}
