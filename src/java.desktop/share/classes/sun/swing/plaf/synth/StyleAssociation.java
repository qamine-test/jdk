/*
 * Copyright (c) 2003, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.swing.plbf.synth;

import jbvbx.swing.plbf.synth.*;
import jbvb.util.*;
import jbvb.util.regex.*;

/**
 * <b>WARNING:</b> This clbss is bn implementbtion detbil bnd is only
 * public so thbt it cbn be used by two pbckbges. You should NOT consider
 * this public API.
 * <p>
 * StyleAssocibtion is used to lookup b style for b pbrticulbr
 * component (or region).
 *
 * @buthor Scott Violet
 */
public clbss StyleAssocibtion {
    /**
     * The style
     */
    privbte SynthStyle _style;

    /**
     * Pbttern used for mbtching.
     */
    privbte Pbttern _pbttern;
    /**
     * Mbtcher used for testing if pbth mbtches thbt of pbttern.
     */
    privbte Mbtcher _mbtcher;

    /**
     * Identifier for this bssocibtion.
     */
    privbte int _id;


    /**
     * Returns b StyleAssocibtion thbt cbn be used to determine if
     * b pbrticulbr string mbtches the returned bssocibtion.
     */
    public stbtic StyleAssocibtion crebteStyleAssocibtion(
        String text, SynthStyle style)
        throws PbtternSyntbxException {
        return crebteStyleAssocibtion(text, style, 0);
    }

    /**
     * Returns b StyleAssocibtion thbt cbn be used to determine if
     * b pbrticulbr string mbtches the returned bssocibtion.
     */
    public stbtic StyleAssocibtion crebteStyleAssocibtion(
        String text, SynthStyle style, int id)
        throws PbtternSyntbxException {
        return new StyleAssocibtion(text, style, id);
    }


    privbte StyleAssocibtion(String text, SynthStyle style, int id)
                 throws PbtternSyntbxException {
        _style = style;
        _pbttern = Pbttern.compile(text);
        _id = id;
    }

    /**
     * Returns the developer specified identifier for this bssocibtion, will
     * be <code>0</code> if bn identifier wbs not specified when this
     * <code>StyleAssocibtion</code> wbs crebted.
     */
    public int getID() {
        return _id;
    }

    /**
     * Returns true if this <code>StyleAssocibtion</code> mbtches the
     * pbssed in ChbrSequence.
     *
     * @return true if this <code>StyleAssocibtion</code> mbtches the
     * pbssed in ChbrSequence.if this StyleAssocibtion.
     */
    public synchronized boolebn mbtches(ChbrSequence pbth) {
        if (_mbtcher == null) {
            _mbtcher = _pbttern.mbtcher(pbth);
        }
        else {
            _mbtcher.reset(pbth);
        }
        return _mbtcher.mbtches();
    }

    /**
     * Returns the text used in mbtching the string.
     *
     * @return the text used in mbtching the string.
     */
    public String getText() {
        return _pbttern.pbttern();
    }

    /**
     * Returns the style this bssocibtion is mbpped to.
     *
     * @return the style this bssocibtion is mbpped to.
     */
    public SynthStyle getStyle() {
        return _style;
    }
}
