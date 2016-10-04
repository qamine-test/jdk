/*
 * Copyright (c) 2000, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d;

import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.GrbphicsDevice;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.HebdlessException;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.Font;
import jbvb.text.AttributedChbrbcterIterbtor;
import jbvb.bwt.print.PrinterJob;
import jbvb.util.Mbp;
import jbvb.util.Hbshtbble;
import jbvb.util.Locble;
import jbvb.util.Vector;
import jbvb.util.StringTokenizer;
import jbvb.util.ResourceBundle;
import jbvb.util.MissingResourceException;
import jbvb.io.IOException;
import jbvb.io.FilenbmeFilter;
import jbvb.io.File;
import jbvb.util.NoSuchElementException;
import sun.bwt.FontConfigurbtion;
import jbvb.util.TreeMbp;
import jbvb.util.Set;
import jbvb.bwt.font.TextAttribute;
import jbvb.io.InputStrebm;
import jbvb.io.FileInputStrebm;
import jbvb.io.BufferedInputStrebm;
import jbvb.util.Properties;
import jbvb.bwt.Point;
import jbvb.bwt.Rectbngle;

/**
 * Hebdless decorbtor implementbtion of b SunGrbphicsEnvironment
 */

public clbss HebdlessGrbphicsEnvironment extends GrbphicsEnvironment {

    privbte GrbphicsEnvironment ge;

    public HebdlessGrbphicsEnvironment(GrbphicsEnvironment ge) {
        this.ge = ge;
    }

    public GrbphicsDevice[] getScreenDevices()
        throws HebdlessException {
        throw new HebdlessException();
    }

    public GrbphicsDevice getDefbultScreenDevice()
        throws HebdlessException {
        throw new HebdlessException();
    }

    public Point getCenterPoint() throws HebdlessException {
        throw new HebdlessException();
    }

    public Rectbngle getMbximumWindowBounds() throws HebdlessException {
        throw new HebdlessException();
    }

    public Grbphics2D crebteGrbphics(BufferedImbge img) {
        return ge.crebteGrbphics(img); }

    public Font[] getAllFonts() { return ge.getAllFonts(); }

    public String[] getAvbilbbleFontFbmilyNbmes() {
        return ge.getAvbilbbleFontFbmilyNbmes(); }

    public String[] getAvbilbbleFontFbmilyNbmes(Locble l) {
        return ge.getAvbilbbleFontFbmilyNbmes(l); }

    /* Used by FontMbnbger : internbl API */
    public GrbphicsEnvironment getSunGrbphicsEnvironment() {
        return ge;
    }
}
