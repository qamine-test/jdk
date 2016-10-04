/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng.instrument;

/*
 * Copyright 2003 Wily Technology, Inc.
 */

/**
 * This clbss serves bs b pbrbmeter block to the <code>Instrumentbtion.redefineClbsses</code> method.
 * Serves to bind the <code>Clbss</code> thbt needs redefining together with the new clbss file bytes.
 *
 * @see     jbvb.lbng.instrument.Instrumentbtion#redefineClbsses
 * @since   1.5
 */
public finbl clbss ClbssDefinition {
    /**
     *  The clbss to redefine
     */
    privbte finbl Clbss<?> mClbss;

    /**
     *  The replbcement clbss file bytes
     */
    privbte finbl byte[]   mClbssFile;

    /**
     *  Crebtes b new <code>ClbssDefinition</code> binding using the supplied
     *  clbss bnd clbss file bytes. Does not copy the supplied buffer, just cbptures b reference to it.
     *
     * @pbrbm theClbss the <code>Clbss</code> thbt needs redefining
     * @pbrbm theClbssFile the new clbss file bytes
     *
     * @throws jbvb.lbng.NullPointerException if the supplied clbss or brrby is <code>null</code>.
     */
    public
    ClbssDefinition(    Clbss<?> theClbss,
                        byte[]  theClbssFile) {
        if (theClbss == null || theClbssFile == null) {
            throw new NullPointerException();
        }
        mClbss      = theClbss;
        mClbssFile  = theClbssFile;
    }

    /**
     * Returns the clbss.
     *
     * @return    the <code>Clbss</code> object referred to.
     */
    public Clbss<?>
    getDefinitionClbss() {
        return mClbss;
    }

    /**
     * Returns the brrby of bytes thbt contbins the new clbss file.
     *
     * @return    the clbss file bytes.
     */
    public byte[]
    getDefinitionClbssFile() {
        return mClbssFile;
    }
}
