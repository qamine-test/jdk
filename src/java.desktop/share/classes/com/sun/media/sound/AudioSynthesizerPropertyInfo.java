/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.medib.sound;

/**
 * Informbtion bbout property used in  opening <code>AudioSynthesizer</code>.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss AudioSynthesizerPropertyInfo {

    /**
     * Constructs b <code>AudioSynthesizerPropertyInfo</code> object with b given
     * nbme bnd vblue. The <code>description</code> bnd <code>choices</code>
     * bre initiblized by <code>null</code> vblues.
     *
     * @pbrbm nbme the nbme of the property
     * @pbrbm vblue the current vblue or clbss used for vblues.
     *
     */
    public AudioSynthesizerPropertyInfo(String nbme, Object vblue) {
        this.nbme = nbme;
        if (vblue instbnceof Clbss)
            vblueClbss = (Clbss)vblue;
        else
        {
            this.vblue = vblue;
            if (vblue != null)
                vblueClbss = vblue.getClbss();
        }
    }
    /**
     * The nbme of the property.
     */
    public String nbme;
    /**
     * A brief description of the property, which mby be null.
     */
    public String description = null;
    /**
     * The <code>vblue</code> field specifies the current vblue of
     * the property.
     */
    public Object vblue = null;
    /**
     * The <code>vblueClbss</code> field specifies clbss
     * used in <code>vblue</code> field.
     */
    public Clbss<?> vblueClbss = null;
    /**
     * An brrby of possible vblues if the vblue for the field
     * <code>AudioSynthesizerPropertyInfo.vblue</code> mby be selected
     * from b pbrticulbr set of vblues; otherwise null.
     */
    public Object[] choices = null;

}
