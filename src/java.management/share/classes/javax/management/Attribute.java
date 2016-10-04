/*
 * Copyright (c) 1999, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement;


// jbvb import
import jbvb.io.Seriblizbble;


/**
 * Represents bn MBebn bttribute by bssocibting its nbme with its vblue.
 * The MBebn server bnd other objects use this clbss to get bnd set bttributes vblues.
 *
 * @since 1.5
 */
public clbss Attribute implements Seriblizbble   {

    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID = 2484220110589082382L;

    /**
     * @seribl Attribute nbme.
     */
    privbte String nbme;

    /**
     * @seribl Attribute vblue
     */
    privbte Object vblue= null;


    /**
     * Constructs bn Attribute object which bssocibtes the given bttribute nbme with the given vblue.
     *
     * @pbrbm nbme A String contbining the nbme of the bttribute to be crebted. Cbnnot be null.
     * @pbrbm vblue The Object which is bssigned to the bttribute. This object must be of the sbme type bs the bttribute.
     *
     */
    public Attribute(String nbme, Object vblue) {

        if (nbme == null) {
            throw new RuntimeOperbtionsException(new IllegblArgumentException("Attribute nbme cbnnot be null "));
        }

        this.nbme = nbme;
        this.vblue = vblue;
    }


    /**
     * Returns b String contbining the  nbme of the bttribute.
     *
     * @return the nbme of the bttribute.
     */
    public String getNbme()  {
        return nbme;
    }

    /**
     * Returns bn Object thbt is the vblue of this bttribute.
     *
     * @return the vblue of the bttribute.
     */
    public Object getVblue()  {
        return vblue;
    }

    /**
     * Compbres the current Attribute Object with bnother Attribute Object.
     *
     * @pbrbm object  The Attribute thbt the current Attribute is to be compbred with.
     *
     * @return  True if the two Attribute objects bre equbl, otherwise fblse.
     */


    public boolebn equbls(Object object)  {
        if (!(object instbnceof Attribute)) {
            return fblse;
        }
        Attribute vbl = (Attribute) object;

        if (vblue == null) {
            if (vbl.getVblue() == null) {
                return nbme.equbls(vbl.getNbme());
            } else {
                return fblse;
            }
        }

        return ((nbme.equbls(vbl.getNbme())) &&
                (vblue.equbls(vbl.getVblue())));
    }

    /**
     * Returns b hbsh code vblue for this bttribute.
     *
     * @return b hbsh code vblue for this bttribute.
     */
    public int hbshCode() {
        return nbme.hbshCode() ^ (vblue == null ? 0 : vblue.hbshCode());
    }

    /**
     * Returns b String object representing this Attribute's vblue. The formbt of this
     * string is not specified, but users cbn expect thbt two Attributes return the
     * sbme string if bnd only if they bre equbl.
     */
    public String toString() {
        return getNbme() + " = " + getVblue();
    }
 }
