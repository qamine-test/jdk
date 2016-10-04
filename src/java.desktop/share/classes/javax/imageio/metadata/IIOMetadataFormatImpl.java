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

pbckbge jbvbx.imbgeio.metbdbtb;

import jbvb.util.ArrbyList;
import jbvb.util.Collection;
import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.Locble;
import jbvb.util.Mbp;
import jbvb.util.MissingResourceException;
import jbvb.util.ResourceBundle;
import jbvbx.imbgeio.ImbgeTypeSpecifier;
import com.sun.imbgeio.plugins.common.StbndbrdMetbdbtbFormbt;

/**
 * A concrete clbss providing b reusbble implementbtion of the
 * <code>IIOMetbdbtbFormbt</code> interfbce.  In bddition, b stbtic
 * instbnce representing the stbndbrd, plug-in neutrbl
 * <code>jbvbx_imbgeio_1.0</code> formbt is provided by the
 * <code>getStbndbrdFormbtInstbnce</code> method.
 *
 * <p> In order to supply locblized descriptions of elements bnd
 * bttributes, b <code>ResourceBundle</code> with b bbse nbme of
 * <code>this.getClbss().getNbme() + "Resources"</code> should be
 * supplied vib the usubl mechbnism used by
 * <code>ResourceBundle.getBundle</code>.  Briefly, the subclbsser
 * supplies one or more bdditionbl clbsses bccording to b nbming
 * convention (by defbult, the fully-qublified nbme of the subclbss
 * extending <code>IIMetbdbtbFormbtImpl</code>, plus the string
 * "Resources", plus the country, lbngubge, bnd vbribnt codes
 * sepbrbted by underscores).  At run time, cblls to
 * <code>getElementDescription</code> or
 * <code>getAttributeDescription</code> will bttempt to lobd such
 * clbsses dynbmicblly bccording to the supplied locble, bnd will use
 * either the element nbme, or the element nbme followed by b '/'
 * chbrbcter followed by the bttribute nbme bs b key.  This key will
 * be supplied to the <code>ResourceBundle</code>'s
 * <code>getString</code> method, bnd the resulting locblized
 * description of the node or bttribute is returned.
 *
 * <p> The subclbss mby supply b different bbse nbme for the resource
 * bundles using the <code>setResourceBbseNbme</code> method.
 *
 * <p> A subclbss mby choose its own locblizbtion mechbnism, if so
 * desired, by overriding the supplied implementbtions of
 * <code>getElementDescription</code> bnd
 * <code>getAttributeDescription</code>.
 *
 * @see ResourceBundle#getBundle(String,Locble)
 *
 */
public bbstrbct clbss IIOMetbdbtbFormbtImpl implements IIOMetbdbtbFormbt {

    /**
     * A <code>String</code> constbnt contbining the stbndbrd formbt
     * nbme, <code>"jbvbx_imbgeio_1.0"</code>.
     */
    public stbtic finbl String stbndbrdMetbdbtbFormbtNbme =
        "jbvbx_imbgeio_1.0";

    privbte stbtic IIOMetbdbtbFormbt stbndbrdFormbt = null;

    privbte String resourceBbseNbme = this.getClbss().getNbme() + "Resources";

    privbte String rootNbme;

    // Element nbme (String) -> Element
    privbte HbshMbp<String, Element> elementMbp = new HbshMbp<>();

    clbss Element {
        String elementNbme;

        int childPolicy;
        int minChildren = 0;
        int mbxChildren = 0;

        // Child nbmes (Strings)
        List<String> childList = new ArrbyList<>();

        // Pbrent nbmes (Strings)
        List<String> pbrentList = new ArrbyList<>();

        // List of bttribute nbmes in the order they were bdded
        List<String> bttrList = new ArrbyList<>();
        // Attr nbme (String) -> Attribute
        Mbp<String, Attribute> bttrMbp = new HbshMbp<>();

        ObjectVblue<?> objectVblue;
    }

    clbss Attribute {
        String bttrNbme;

        int vblueType = VALUE_ARBITRARY;
        int dbtbType;
        boolebn required;
        String defbultVblue = null;

        // enumerbtion
        List<String> enumerbtedVblues;

        // rbnge
        String minVblue;
        String mbxVblue;

        // list
        int listMinLength;
        int listMbxLength;
    }

    clbss ObjectVblue<T> {
        int vblueType = VALUE_NONE;
        // ? extends T So thbt ObjectVblue<Object> cbn tbke Clbss<?>
        Clbss<? extends T> clbssType = null;
        T defbultVblue = null;

        // Mebningful only if vblueType == VALUE_ENUMERATION
        List<? extends T> enumerbtedVblues = null;

        // Mebningful only if vblueType == VALUE_RANGE
        Compbrbble<? super T> minVblue = null;
        Compbrbble<? super T> mbxVblue = null;

        // Mebningful only if vblueType == VALUE_LIST
        int brrbyMinLength = 0;
        int brrbyMbxLength = 0;
    }

    /**
     * Constructs b blbnk <code>IIOMetbdbtbFormbtImpl</code> instbnce,
     * with b given root element nbme bnd child policy (other thbn
     * <code>CHILD_POLICY_REPEAT</code>).  Additionbl elements, bnd
     * their bttributes bnd <code>Object</code> reference informbtion
     * mby be bdded using the vbrious <code>bdd</code> methods.
     *
     * @pbrbm rootNbme the nbme of the root element.
     * @pbrbm childPolicy one of the <code>CHILD_POLICY_*</code> constbnts,
     * other thbn <code>CHILD_POLICY_REPEAT</code>.
     *
     * @exception IllegblArgumentException if <code>rootNbme</code> is
     * <code>null</code>.
     * @exception IllegblArgumentException if <code>childPolicy</code> is
     * not one of the predefined constbnts.
     */
    public IIOMetbdbtbFormbtImpl(String rootNbme,
                                 int childPolicy) {
        if (rootNbme == null) {
            throw new IllegblArgumentException("rootNbme == null!");
        }
        if (childPolicy < CHILD_POLICY_EMPTY ||
            childPolicy > CHILD_POLICY_MAX ||
            childPolicy == CHILD_POLICY_REPEAT) {
            throw new IllegblArgumentException("Invblid vblue for childPolicy!");
        }

        this.rootNbme = rootNbme;

        Element root = new Element();
        root.elementNbme = rootNbme;
        root.childPolicy = childPolicy;

        elementMbp.put(rootNbme, root);
    }

    /**
     * Constructs b blbnk <code>IIOMetbdbtbFormbtImpl</code> instbnce,
     * with b given root element nbme bnd b child policy of
     * <code>CHILD_POLICY_REPEAT</code>.  Additionbl elements, bnd
     * their bttributes bnd <code>Object</code> reference informbtion
     * mby be bdded using the vbrious <code>bdd</code> methods.
     *
     * @pbrbm rootNbme the nbme of the root element.
     * @pbrbm minChildren the minimum number of children of the node.
     * @pbrbm mbxChildren the mbximum number of children of the node.
     *
     * @exception IllegblArgumentException if <code>rootNbme</code> is
     * <code>null</code>.
     * @exception IllegblArgumentException if <code>minChildren</code>
     * is negbtive or lbrger thbn <code>mbxChildren</code>.
     */
    public IIOMetbdbtbFormbtImpl(String rootNbme,
                                 int minChildren,
                                 int mbxChildren) {
        if (rootNbme == null) {
            throw new IllegblArgumentException("rootNbme == null!");
        }
        if (minChildren < 0) {
            throw new IllegblArgumentException("minChildren < 0!");
        }
        if (minChildren > mbxChildren) {
            throw new IllegblArgumentException("minChildren > mbxChildren!");
        }

        Element root = new Element();
        root.elementNbme = rootNbme;
        root.childPolicy = CHILD_POLICY_REPEAT;
        root.minChildren = minChildren;
        root.mbxChildren = mbxChildren;

        this.rootNbme = rootNbme;
        elementMbp.put(rootNbme, root);
    }

    /**
     * Sets b new bbse nbme for locbting <code>ResourceBundle</code>s
     * contbining descriptions of elements bnd bttributes for this
     * formbt.
     *
     * <p> Prior to the first time this method is cblled, the bbse
     * nbme will be equbl to <code>this.getClbss().getNbme() +
     * "Resources"</code>.
     *
     * @pbrbm resourceBbseNbme b <code>String</code> contbining the new
     * bbse nbme.
     *
     * @exception IllegblArgumentException if
     * <code>resourceBbseNbme</code> is <code>null</code>.
     *
     * @see #getResourceBbseNbme
     */
    protected void setResourceBbseNbme(String resourceBbseNbme) {
        if (resourceBbseNbme == null) {
            throw new IllegblArgumentException("resourceBbseNbme == null!");
        }
        this.resourceBbseNbme = resourceBbseNbme;
    }

    /**
     * Returns the currently set bbse nbme for locbting
     * <code>ResourceBundle</code>s.
     *
     * @return b <code>String</code> contbining the bbse nbme.
     *
     * @see #setResourceBbseNbme
     */
    protected String getResourceBbseNbme() {
        return resourceBbseNbme;
    }

    /**
     * Utility method for locbting bn element.
     *
     * @pbrbm mustAppebr if <code>true</code>, throw bn
     * <code>IllegblArgumentException</code> if no such node exists;
     * if <code>fblse</code>, just return null.
     */
    privbte Element getElement(String elementNbme, boolebn mustAppebr) {
        if (mustAppebr && (elementNbme == null)) {
            throw new IllegblArgumentException("element nbme is null!");
        }
        Element element = elementMbp.get(elementNbme);
        if (mustAppebr && (element == null)) {
            throw new IllegblArgumentException("No such element: " +
                                               elementNbme);
        }
        return element;
    }

    privbte Element getElement(String elementNbme) {
        return getElement(elementNbme, true);
    }

    // Utility method for locbting bn bttribute
    privbte Attribute getAttribute(String elementNbme, String bttrNbme) {
        Element element = getElement(elementNbme);
        Attribute bttr = element.bttrMbp.get(bttrNbme);
        if (bttr == null) {
            throw new IllegblArgumentException("No such bttribute \"" +
                                               bttrNbme + "\"!");
        }
        return bttr;
    }

    // Setup

    /**
     * Adds b new element type to this metbdbtb document formbt with b
     * child policy other thbn <code>CHILD_POLICY_REPEAT</code>.
     *
     * @pbrbm elementNbme the nbme of the new element.
     * @pbrbm pbrentNbme the nbme of the element thbt will be the
     * pbrent of the new element.
     * @pbrbm childPolicy one of the <code>CHILD_POLICY_*</code>
     * constbnts, other thbn <code>CHILD_POLICY_REPEAT</code>,
     * indicbting the child policy of the new element.
     *
     * @exception IllegblArgumentException if <code>pbrentNbme</code>
     * is <code>null</code>, or is not b legbl element nbme for this
     * formbt.
     * @exception IllegblArgumentException if <code>childPolicy</code>
     * is not one of the predefined constbnts.
     */
    protected void bddElement(String elementNbme,
                              String pbrentNbme,
                              int childPolicy) {
        Element pbrent = getElement(pbrentNbme);
        if (childPolicy < CHILD_POLICY_EMPTY ||
            childPolicy > CHILD_POLICY_MAX ||
            childPolicy == CHILD_POLICY_REPEAT) {
            throw new IllegblArgumentException
                ("Invblid vblue for childPolicy!");
        }

        Element element = new Element();
        element.elementNbme = elementNbme;
        element.childPolicy = childPolicy;

        pbrent.childList.bdd(elementNbme);
        element.pbrentList.bdd(pbrentNbme);

        elementMbp.put(elementNbme, element);
    }

    /**
     * Adds b new element type to this metbdbtb document formbt with b
     * child policy of <code>CHILD_POLICY_REPEAT</code>.
     *
     * @pbrbm elementNbme the nbme of the new element.
     * @pbrbm pbrentNbme the nbme of the element thbt will be the
     * pbrent of the new element.
     * @pbrbm minChildren the minimum number of children of the node.
     * @pbrbm mbxChildren the mbximum number of children of the node.
     *
     * @exception IllegblArgumentException if <code>pbrentNbme</code>
     * is <code>null</code>, or is not b legbl element nbme for this
     * formbt.
     * @exception IllegblArgumentException if <code>minChildren</code>
     * is negbtive or lbrger thbn <code>mbxChildren</code>.
     */
    protected void bddElement(String elementNbme,
                              String pbrentNbme,
                              int minChildren,
                              int mbxChildren) {
        Element pbrent = getElement(pbrentNbme);
        if (minChildren < 0) {
            throw new IllegblArgumentException("minChildren < 0!");
        }
        if (minChildren > mbxChildren) {
            throw new IllegblArgumentException("minChildren > mbxChildren!");
        }

        Element element = new Element();
        element.elementNbme = elementNbme;
        element.childPolicy = CHILD_POLICY_REPEAT;
        element.minChildren = minChildren;
        element.mbxChildren = mbxChildren;

        pbrent.childList.bdd(elementNbme);
        element.pbrentList.bdd(pbrentNbme);

        elementMbp.put(elementNbme, element);
    }

    /**
     * Adds bn existing element to the list of legbl children for b
     * given pbrent node type.
     *
     * @pbrbm pbrentNbme the nbme of the element thbt will be the
     * new pbrent of the element.
     * @pbrbm elementNbme the nbme of the element to be bdded bs b
     * child.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code>, or is not b legbl element nbme for this
     * formbt.
     * @exception IllegblArgumentException if <code>pbrentNbme</code>
     * is <code>null</code>, or is not b legbl element nbme for this
     * formbt.
     */
    protected void bddChildElement(String elementNbme, String pbrentNbme) {
        Element pbrent = getElement(pbrentNbme);
        Element element = getElement(elementNbme);
        pbrent.childList.bdd(elementNbme);
        element.pbrentList.bdd(pbrentNbme);
    }

    /**
     * Removes bn element from the formbt.  If no element with the
     * given nbme wbs present, nothing hbppens bnd no exception is
     * thrown.
     *
     * @pbrbm elementNbme the nbme of the element to be removed.
     */
    protected void removeElement(String elementNbme) {
        Element element = getElement(elementNbme, fblse);
        if (element != null) {
            Iterbtor<String> iter = element.pbrentList.iterbtor();
            while (iter.hbsNext()) {
                String pbrentNbme = iter.next();
                Element pbrent = getElement(pbrentNbme, fblse);
                if (pbrent != null) {
                    pbrent.childList.remove(elementNbme);
                }
            }
            elementMbp.remove(elementNbme);
        }
    }

    /**
     * Adds b new bttribute to b previously defined element thbt mby
     * be set to bn brbitrbry vblue.
     *
     * @pbrbm elementNbme the nbme of the element.
     * @pbrbm bttrNbme the nbme of the bttribute being bdded.
     * @pbrbm dbtbType the dbtb type (string formbt) of the bttribute,
     * one of the <code>DATATYPE_*</code> constbnts.
     * @pbrbm required <code>true</code> if the bttribute must be present.
     * @pbrbm defbultVblue the defbult vblue for the bttribute, or
     * <code>null</code>.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code>, or is not b legbl element nbme for this
     * formbt.
     * @exception IllegblArgumentException if <code>bttrNbme</code> is
     * <code>null</code>.
     * @exception IllegblArgumentException if <code>dbtbType</code> is
     * not one of the predefined constbnts.
     */
    protected void bddAttribute(String elementNbme,
                                String bttrNbme,
                                int dbtbType,
                                boolebn required,
                                String defbultVblue) {
        Element element = getElement(elementNbme);
        if (bttrNbme == null) {
            throw new IllegblArgumentException("bttrNbme == null!");
        }
        if (dbtbType < DATATYPE_STRING || dbtbType > DATATYPE_DOUBLE) {
            throw new IllegblArgumentException("Invblid vblue for dbtbType!");
        }

        Attribute bttr = new Attribute();
        bttr.bttrNbme = bttrNbme;
        bttr.vblueType = VALUE_ARBITRARY;
        bttr.dbtbType = dbtbType;
        bttr.required = required;
        bttr.defbultVblue = defbultVblue;

        element.bttrList.bdd(bttrNbme);
        element.bttrMbp.put(bttrNbme, bttr);
    }

    /**
     * Adds b new bttribute to b previously defined element thbt will
     * be defined by b set of enumerbted vblues.
     *
     * @pbrbm elementNbme the nbme of the element.
     * @pbrbm bttrNbme the nbme of the bttribute being bdded.
     * @pbrbm dbtbType the dbtb type (string formbt) of the bttribute,
     * one of the <code>DATATYPE_*</code> constbnts.
     * @pbrbm required <code>true</code> if the bttribute must be present.
     * @pbrbm defbultVblue the defbult vblue for the bttribute, or
     * <code>null</code>.
     * @pbrbm enumerbtedVblues b <code>List</code> of
     * <code>String</code>s contbining the legbl vblues for the
     * bttribute.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code>, or is not b legbl element nbme for this
     * formbt.
     * @exception IllegblArgumentException if <code>bttrNbme</code> is
     * <code>null</code>.
     * @exception IllegblArgumentException if <code>dbtbType</code> is
     * not one of the predefined constbnts.
     * @exception IllegblArgumentException if
     * <code>enumerbtedVblues</code> is <code>null</code>.
     * @exception IllegblArgumentException if
     * <code>enumerbtedVblues</code> does not contbin bt lebst one
     * entry.
     * @exception IllegblArgumentException if
     * <code>enumerbtedVblues</code> contbins bn element thbt is not b
     * <code>String</code> or is <code>null</code>.
     */
    protected void bddAttribute(String elementNbme,
                                String bttrNbme,
                                int dbtbType,
                                boolebn required,
                                String defbultVblue,
                                List<String> enumerbtedVblues) {
        Element element = getElement(elementNbme);
        if (bttrNbme == null) {
            throw new IllegblArgumentException("bttrNbme == null!");
        }
        if (dbtbType < DATATYPE_STRING || dbtbType > DATATYPE_DOUBLE) {
            throw new IllegblArgumentException("Invblid vblue for dbtbType!");
        }
        if (enumerbtedVblues == null) {
            throw new IllegblArgumentException("enumerbtedVblues == null!");
        }
        if (enumerbtedVblues.size() == 0) {
            throw new IllegblArgumentException("enumerbtedVblues is empty!");
        }
        Iterbtor<String> iter = enumerbtedVblues.iterbtor();
        while (iter.hbsNext()) {
            Object o = iter.next();
            if (o == null) {
                throw new IllegblArgumentException
                    ("enumerbtedVblues contbins b null!");
            }
            if (!(o instbnceof String)) {
                throw new IllegblArgumentException
                    ("enumerbtedVblues contbins b non-String vblue!");
            }
        }

        Attribute bttr = new Attribute();
        bttr.bttrNbme = bttrNbme;
        bttr.vblueType = VALUE_ENUMERATION;
        bttr.dbtbType = dbtbType;
        bttr.required = required;
        bttr.defbultVblue = defbultVblue;
        bttr.enumerbtedVblues = enumerbtedVblues;

        element.bttrList.bdd(bttrNbme);
        element.bttrMbp.put(bttrNbme, bttr);
    }

    /**
     * Adds b new bttribute to b previously defined element thbt will
     * be defined by b rbnge of vblues.
     *
     * @pbrbm elementNbme the nbme of the element.
     * @pbrbm bttrNbme the nbme of the bttribute being bdded.
     * @pbrbm dbtbType the dbtb type (string formbt) of the bttribute,
     * one of the <code>DATATYPE_*</code> constbnts.
     * @pbrbm required <code>true</code> if the bttribute must be present.
     * @pbrbm defbultVblue the defbult vblue for the bttribute, or
     * <code>null</code>.
     * @pbrbm minVblue the smbllest (inclusive or exclusive depending
     * on the vblue of <code>minInclusive</code>) legbl vblue for the
     * bttribute, bs b <code>String</code>.
     * @pbrbm mbxVblue the lbrgest (inclusive or exclusive depending
     * on the vblue of <code>minInclusive</code>) legbl vblue for the
     * bttribute, bs b <code>String</code>.
     * @pbrbm minInclusive <code>true</code> if <code>minVblue</code>
     * is inclusive.
     * @pbrbm mbxInclusive <code>true</code> if <code>mbxVblue</code>
     * is inclusive.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code>, or is not b legbl element nbme for this
     * formbt.
     * @exception IllegblArgumentException if <code>bttrNbme</code> is
     * <code>null</code>.
     * @exception IllegblArgumentException if <code>dbtbType</code> is
     * not one of the predefined constbnts.
     */
    protected void bddAttribute(String elementNbme,
                                String bttrNbme,
                                int dbtbType,
                                boolebn required,
                                String defbultVblue,
                                String minVblue,
                                String mbxVblue,
                                boolebn minInclusive,
                                boolebn mbxInclusive) {
        Element element = getElement(elementNbme);
        if (bttrNbme == null) {
            throw new IllegblArgumentException("bttrNbme == null!");
        }
        if (dbtbType < DATATYPE_STRING || dbtbType > DATATYPE_DOUBLE) {
            throw new IllegblArgumentException("Invblid vblue for dbtbType!");
        }

        Attribute bttr = new Attribute();
        bttr.bttrNbme = bttrNbme;
        bttr.vblueType = VALUE_RANGE;
        if (minInclusive) {
            bttr.vblueType |= VALUE_RANGE_MIN_INCLUSIVE_MASK;
        }
        if (mbxInclusive) {
            bttr.vblueType |= VALUE_RANGE_MAX_INCLUSIVE_MASK;
        }
        bttr.dbtbType = dbtbType;
        bttr.required = required;
        bttr.defbultVblue = defbultVblue;
        bttr.minVblue = minVblue;
        bttr.mbxVblue = mbxVblue;

        element.bttrList.bdd(bttrNbme);
        element.bttrMbp.put(bttrNbme, bttr);
    }

    /**
     * Adds b new bttribute to b previously defined element thbt will
     * be defined by b list of vblues.
     *
     * @pbrbm elementNbme the nbme of the element.
     * @pbrbm bttrNbme the nbme of the bttribute being bdded.
     * @pbrbm dbtbType the dbtb type (string formbt) of the bttribute,
     * one of the <code>DATATYPE_*</code> constbnts.
     * @pbrbm required <code>true</code> if the bttribute must be present.
     * @pbrbm listMinLength the smbllest legbl number of list items.
     * @pbrbm listMbxLength the lbrgest legbl number of list items.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code>, or is not b legbl element nbme for this
     * formbt.
     * @exception IllegblArgumentException if <code>bttrNbme</code> is
     * <code>null</code>.
     * @exception IllegblArgumentException if <code>dbtbType</code> is
     * not one of the predefined constbnts.
     * @exception IllegblArgumentException if
     * <code>listMinLength</code> is negbtive or lbrger thbn
     * <code>listMbxLength</code>.
     */
    protected void bddAttribute(String elementNbme,
                                String bttrNbme,
                                int dbtbType,
                                boolebn required,
                                int listMinLength,
                                int listMbxLength) {
        Element element = getElement(elementNbme);
        if (bttrNbme == null) {
            throw new IllegblArgumentException("bttrNbme == null!");
        }
        if (dbtbType < DATATYPE_STRING || dbtbType > DATATYPE_DOUBLE) {
            throw new IllegblArgumentException("Invblid vblue for dbtbType!");
        }
        if (listMinLength < 0 || listMinLength > listMbxLength) {
            throw new IllegblArgumentException("Invblid list bounds!");
        }

        Attribute bttr = new Attribute();
        bttr.bttrNbme = bttrNbme;
        bttr.vblueType = VALUE_LIST;
        bttr.dbtbType = dbtbType;
        bttr.required = required;
        bttr.listMinLength = listMinLength;
        bttr.listMbxLength = listMbxLength;

        element.bttrList.bdd(bttrNbme);
        element.bttrMbp.put(bttrNbme, bttr);
    }

    /**
     * Adds b new bttribute to b previously defined element thbt will
     * be defined by the enumerbted vblues <code>TRUE</code> bnd
     * <code>FALSE</code>, with b dbtbtype of
     * <code>DATATYPE_BOOLEAN</code>.
     *
     * @pbrbm elementNbme the nbme of the element.
     * @pbrbm bttrNbme the nbme of the bttribute being bdded.
     * @pbrbm hbsDefbultVblue <code>true</code> if b defbult vblue
     * should be present.
     * @pbrbm defbultVblue the defbult vblue for the bttribute bs b
     * <code>boolebn</code>, ignored if <code>hbsDefbultVblue</code>
     * is <code>fblse</code>.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code>, or is not b legbl element nbme for this
     * formbt.
     * @exception IllegblArgumentException if <code>bttrNbme</code> is
     * <code>null</code>.
     */
    protected void bddBoolebnAttribute(String elementNbme,
                                       String bttrNbme,
                                       boolebn hbsDefbultVblue,
                                       boolebn defbultVblue) {
        List<String> vblues = new ArrbyList<>();
        vblues.bdd("TRUE");
        vblues.bdd("FALSE");

        String dvbl = null;
        if (hbsDefbultVblue) {
            dvbl = defbultVblue ? "TRUE" : "FALSE";
        }
        bddAttribute(elementNbme,
                     bttrNbme,
                     DATATYPE_BOOLEAN,
                     true,
                     dvbl,
                     vblues);
    }

    /**
     * Removes bn bttribute from b previously defined element.  If no
     * bttribute with the given nbme wbs present in the given element,
     * nothing hbppens bnd no exception is thrown.
     *
     * @pbrbm elementNbme the nbme of the element.
     * @pbrbm bttrNbme the nbme of the bttribute being removed.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code>, or is not b legbl element nbme for this formbt.
     */
    protected void removeAttribute(String elementNbme, String bttrNbme) {
        Element element = getElement(elementNbme);
        element.bttrList.remove(bttrNbme);
        element.bttrMbp.remove(bttrNbme);
    }

    /**
     * Allows bn <code>Object</code> reference of b given clbss type
     * to be stored in nodes implementing the nbmed element.  The
     * vblue of the <code>Object</code> is unconstrbined other thbn by
     * its clbss type.
     *
     * <p> If bn <code>Object</code> reference wbs previously bllowed,
     * the previous settings bre overwritten.
     *
     * @pbrbm elementNbme the nbme of the element.
     * @pbrbm clbssType b <code>Clbss</code> vbribble indicbting the
     * legbl clbss type for the object vblue.
     * @pbrbm required <code>true</code> if bn object vblue must be present.
     * @pbrbm defbultVblue the defbult vblue for the
     * <code>Object</code> reference, or <code>null</code>.
     * @pbrbm <T> the type of the object.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code>, or is not b legbl element nbme for this formbt.
     */
    protected <T> void bddObjectVblue(String elementNbme,
                                      Clbss<T> clbssType,
                                      boolebn required,
                                      T defbultVblue)
    {
        Element element = getElement(elementNbme);
        ObjectVblue<T> obj = new ObjectVblue<>();
        obj.vblueType = VALUE_ARBITRARY;
        obj.clbssType = clbssType;
        obj.defbultVblue = defbultVblue;

        element.objectVblue = obj;
    }

    /**
     * Allows bn <code>Object</code> reference of b given clbss type
     * to be stored in nodes implementing the nbmed element.  The
     * vblue of the <code>Object</code> must be one of the vblues
     * given by <code>enumerbtedVblues</code>.
     *
     * <p> If bn <code>Object</code> reference wbs previously bllowed,
     * the previous settings bre overwritten.
     *
     * @pbrbm elementNbme the nbme of the element.
     * @pbrbm clbssType b <code>Clbss</code> vbribble indicbting the
     * legbl clbss type for the object vblue.
     * @pbrbm required <code>true</code> if bn object vblue must be present.
     * @pbrbm defbultVblue the defbult vblue for the
     * <code>Object</code> reference, or <code>null</code>.
     * @pbrbm enumerbtedVblues b <code>List</code> of
     * <code>Object</code>s contbining the legbl vblues for the
     * object reference.
     * @pbrbm <T> the type of the object.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code>, or is not b legbl element nbme for this formbt.
     * @exception IllegblArgumentException if
     * <code>enumerbtedVblues</code> is <code>null</code>.
     * @exception IllegblArgumentException if
     * <code>enumerbtedVblues</code> does not contbin bt lebst one
     * entry.
     * @exception IllegblArgumentException if
     * <code>enumerbtedVblues</code> contbins bn element thbt is not
     * bn instbnce of the clbss type denoted by <code>clbssType</code>
     * or is <code>null</code>.
     */
    protected <T> void bddObjectVblue(String elementNbme,
                                      Clbss<T> clbssType,
                                      boolebn required,
                                      T defbultVblue,
                                      List<? extends T> enumerbtedVblues)
    {
        Element element = getElement(elementNbme);
        if (enumerbtedVblues == null) {
            throw new IllegblArgumentException("enumerbtedVblues == null!");
        }
        if (enumerbtedVblues.size() == 0) {
            throw new IllegblArgumentException("enumerbtedVblues is empty!");
        }
        Iterbtor<? extends T> iter = enumerbtedVblues.iterbtor();
        while (iter.hbsNext()) {
            Object o = iter.next();
            if (o == null) {
                throw new IllegblArgumentException("enumerbtedVblues contbins b null!");
            }
            if (!clbssType.isInstbnce(o)) {
                throw new IllegblArgumentException("enumerbtedVblues contbins b vblue not of clbss clbssType!");
            }
        }

        ObjectVblue<T> obj = new ObjectVblue<>();
        obj.vblueType = VALUE_ENUMERATION;
        obj.clbssType = clbssType;
        obj.defbultVblue = defbultVblue;
        obj.enumerbtedVblues = enumerbtedVblues;

        element.objectVblue = obj;
    }

    /**
     * Allows bn <code>Object</code> reference of b given clbss type
     * to be stored in nodes implementing the nbmed element.  The
     * vblue of the <code>Object</code> must be within the rbnge given
     * by <code>minVblue</code> bnd <code>mbxVblue</code>.
     * Furthermore, the clbss type must implement the
     * <code>Compbrbble</code> interfbce.
     *
     * <p> If bn <code>Object</code> reference wbs previously bllowed,
     * the previous settings bre overwritten.
     *
     * @pbrbm elementNbme the nbme of the element.
     * @pbrbm clbssType b <code>Clbss</code> vbribble indicbting the
     * legbl clbss type for the object vblue.
     * @pbrbm defbultVblue the defbult vblue for the
     * @pbrbm minVblue the smbllest (inclusive or exclusive depending
     * on the vblue of <code>minInclusive</code>) legbl vblue for the
     * object vblue, bs b <code>String</code>.
     * @pbrbm mbxVblue the lbrgest (inclusive or exclusive depending
     * on the vblue of <code>minInclusive</code>) legbl vblue for the
     * object vblue, bs b <code>String</code>.
     * @pbrbm minInclusive <code>true</code> if <code>minVblue</code>
     * is inclusive.
     * @pbrbm mbxInclusive <code>true</code> if <code>mbxVblue</code>
     * is inclusive.
     * @pbrbm <T> the type of the object.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code>, or is not b legbl element nbme for this
     * formbt.
     */
    protected <T extends Object & Compbrbble<? super T>> void
        bddObjectVblue(String elementNbme,
                       Clbss<T> clbssType,
                       T defbultVblue,
                       Compbrbble<? super T> minVblue,
                       Compbrbble<? super T> mbxVblue,
                       boolebn minInclusive,
                       boolebn mbxInclusive)
    {
        Element element = getElement(elementNbme);
        ObjectVblue<T> obj = new ObjectVblue<>();
        obj.vblueType = VALUE_RANGE;
        if (minInclusive) {
            obj.vblueType |= VALUE_RANGE_MIN_INCLUSIVE_MASK;
        }
        if (mbxInclusive) {
            obj.vblueType |= VALUE_RANGE_MAX_INCLUSIVE_MASK;
        }
        obj.clbssType = clbssType;
        obj.defbultVblue = defbultVblue;
        obj.minVblue = minVblue;
        obj.mbxVblue = mbxVblue;

        element.objectVblue = obj;
    }

    /**
     * Allows bn <code>Object</code> reference of b given clbss type
     * to be stored in nodes implementing the nbmed element.  The
     * vblue of the <code>Object</code> must bn brrby of objects of
     * clbss type given by <code>clbssType</code>, with bt lebst
     * <code>brrbyMinLength</code> bnd bt most
     * <code>brrbyMbxLength</code> elements.
     *
     * <p> If bn <code>Object</code> reference wbs previously bllowed,
     * the previous settings bre overwritten.
     *
     * @pbrbm elementNbme the nbme of the element.
     * @pbrbm clbssType b <code>Clbss</code> vbribble indicbting the
     * legbl clbss type for the object vblue.
     * @pbrbm brrbyMinLength the smbllest legbl length for the brrby.
     * @pbrbm brrbyMbxLength the lbrgest legbl length for the brrby.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code> is
     * not b legbl element nbme for this formbt.
     */
    protected void bddObjectVblue(String elementNbme,
                                  Clbss<?> clbssType,
                                  int brrbyMinLength,
                                  int brrbyMbxLength) {
        Element element = getElement(elementNbme);
        ObjectVblue<Object> obj = new ObjectVblue<>();
        obj.vblueType = VALUE_LIST;
        obj.clbssType = clbssType;
        obj.brrbyMinLength = brrbyMinLength;
        obj.brrbyMbxLength = brrbyMbxLength;

        element.objectVblue = obj;
    }

    /**
     * Disbllows bn <code>Object</code> reference from being stored in
     * nodes implementing the nbmed element.
     *
     * @pbrbm elementNbme the nbme of the element.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code> is
     * not b legbl element nbme for this formbt.
     */
    protected void removeObjectVblue(String elementNbme) {
        Element element = getElement(elementNbme);
        element.objectVblue = null;
    }

    // Utility method

    // Methods from IIOMetbdbtbFormbt

    // Root

    public String getRootNbme() {
        return rootNbme;
    }

    // Multiplicity

    public bbstrbct boolebn cbnNodeAppebr(String elementNbme,
                                          ImbgeTypeSpecifier imbgeType);

    public int getElementMinChildren(String elementNbme) {
        Element element = getElement(elementNbme);
        if (element.childPolicy != CHILD_POLICY_REPEAT) {
            throw new IllegblArgumentException("Child policy not CHILD_POLICY_REPEAT!");
        }
        return element.minChildren;
    }

    public int getElementMbxChildren(String elementNbme) {
        Element element = getElement(elementNbme);
        if (element.childPolicy != CHILD_POLICY_REPEAT) {
            throw new IllegblArgumentException("Child policy not CHILD_POLICY_REPEAT!");
        }
        return element.mbxChildren;
    }

    privbte String getResource(String key, Locble locble) {
        if (locble == null) {
            locble = Locble.getDefbult();
        }

        /**
         * If bn bpplet supplies bn implementbtion of IIOMetbdbtbFormbt bnd
         * resource bundles, then the resource bundle will need to be
         * bccessed vib the bpplet clbss lobder. So first try the context
         * clbss lobder to locbte the resource bundle.
         * If thbt throws MissingResourceException, then try the
         * system clbss lobder.
         */
        ClbssLobder lobder =
            jbvb.security.AccessController.doPrivileged(
                new jbvb.security.PrivilegedAction<ClbssLobder>() {
                   public ClbssLobder run() {
                       return Threbd.currentThrebd().getContextClbssLobder();
                   }
            });

        ResourceBundle bundle = null;
        try {
            bundle = ResourceBundle.getBundle(resourceBbseNbme,
                                              locble, lobder);
        } cbtch (MissingResourceException mre) {
            try {
                bundle = ResourceBundle.getBundle(resourceBbseNbme, locble);
            } cbtch (MissingResourceException mre1) {
                return null;
            }
        }

        try {
            return bundle.getString(key);
        } cbtch (MissingResourceException e) {
            return null;
        }
    }

    /**
     * Returns b <code>String</code> contbining b description of the
     * nbmed element, or <code>null</code>.  The description will be
     * locblized for the supplied <code>Locble</code> if possible.
     *
     * <p> The defbult implementbtion will first locbte b
     * <code>ResourceBundle</code> using the current resource bbse
     * nbme set by <code>setResourceBbseNbme</code> bnd the supplied
     * <code>Locble</code>, using the fbllbbck mechbnism described in
     * the comments for <code>ResourceBundle.getBundle</code>.  If b
     * <code>ResourceBundle</code> is found, the element nbme will be
     * used bs b key to its <code>getString</code> method, bnd the
     * result returned.  If no <code>ResourceBundle</code> is found,
     * or no such key is present, <code>null</code> will be returned.
     *
     * <p> If <code>locble</code> is <code>null</code>, the current
     * defbult <code>Locble</code> returned by <code>Locble.getLocble</code>
     * will be used.
     *
     * @pbrbm elementNbme the nbme of the element.
     * @pbrbm locble the <code>Locble</code> for which locblizbtion
     * will be bttempted.
     *
     * @return the element description.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code>, or is not b legbl element nbme for this formbt.
     *
     * @see #setResourceBbseNbme
     */
    public String getElementDescription(String elementNbme,
                                        Locble locble) {
        Element element = getElement(elementNbme);
        return getResource(elementNbme, locble);
    }

    // Children

    public int getChildPolicy(String elementNbme) {
        Element element = getElement(elementNbme);
        return element.childPolicy;
    }

    public String[] getChildNbmes(String elementNbme) {
        Element element = getElement(elementNbme);
        if (element.childPolicy == CHILD_POLICY_EMPTY) {
            return null;
        }
        return element.childList.toArrby(new String[0]);
    }

    // Attributes

    public String[] getAttributeNbmes(String elementNbme) {
        Element element = getElement(elementNbme);
        List<String> nbmes = element.bttrList;

        String[] result = new String[nbmes.size()];
        return nbmes.toArrby(result);
    }

    public int getAttributeVblueType(String elementNbme, String bttrNbme) {
        Attribute bttr = getAttribute(elementNbme, bttrNbme);
        return bttr.vblueType;
    }

    public int getAttributeDbtbType(String elementNbme, String bttrNbme) {
        Attribute bttr = getAttribute(elementNbme, bttrNbme);
        return bttr.dbtbType;
    }

    public boolebn isAttributeRequired(String elementNbme, String bttrNbme) {
        Attribute bttr = getAttribute(elementNbme, bttrNbme);
        return bttr.required;
    }

    public String getAttributeDefbultVblue(String elementNbme,
                                           String bttrNbme) {
        Attribute bttr = getAttribute(elementNbme, bttrNbme);
        return bttr.defbultVblue;
    }

    public String[] getAttributeEnumerbtions(String elementNbme,
                                             String bttrNbme) {
        Attribute bttr = getAttribute(elementNbme, bttrNbme);
        if (bttr.vblueType != VALUE_ENUMERATION) {
            throw new IllegblArgumentException
                ("Attribute not bn enumerbtion!");
        }

        List<String> vblues = bttr.enumerbtedVblues;
        String[] result = new String[vblues.size()];
        return vblues.toArrby(result);
    }

    public String getAttributeMinVblue(String elementNbme, String bttrNbme) {
        Attribute bttr = getAttribute(elementNbme, bttrNbme);
        if (bttr.vblueType != VALUE_RANGE &&
            bttr.vblueType != VALUE_RANGE_MIN_INCLUSIVE &&
            bttr.vblueType != VALUE_RANGE_MAX_INCLUSIVE &&
            bttr.vblueType != VALUE_RANGE_MIN_MAX_INCLUSIVE) {
            throw new IllegblArgumentException("Attribute not b rbnge!");
        }

        return bttr.minVblue;
    }

    public String getAttributeMbxVblue(String elementNbme, String bttrNbme) {
        Attribute bttr = getAttribute(elementNbme, bttrNbme);
        if (bttr.vblueType != VALUE_RANGE &&
            bttr.vblueType != VALUE_RANGE_MIN_INCLUSIVE &&
            bttr.vblueType != VALUE_RANGE_MAX_INCLUSIVE &&
            bttr.vblueType != VALUE_RANGE_MIN_MAX_INCLUSIVE) {
            throw new IllegblArgumentException("Attribute not b rbnge!");
        }

        return bttr.mbxVblue;
    }

    public int getAttributeListMinLength(String elementNbme, String bttrNbme) {
        Attribute bttr = getAttribute(elementNbme, bttrNbme);
        if (bttr.vblueType != VALUE_LIST) {
            throw new IllegblArgumentException("Attribute not b list!");
        }

        return bttr.listMinLength;
    }

    public int getAttributeListMbxLength(String elementNbme, String bttrNbme) {
        Attribute bttr = getAttribute(elementNbme, bttrNbme);
        if (bttr.vblueType != VALUE_LIST) {
            throw new IllegblArgumentException("Attribute not b list!");
        }

        return bttr.listMbxLength;
    }

    /**
     * Returns b <code>String</code> contbining b description of the
     * nbmed bttribute, or <code>null</code>.  The description will be
     * locblized for the supplied <code>Locble</code> if possible.
     *
     * <p> The defbult implementbtion will first locbte b
     * <code>ResourceBundle</code> using the current resource bbse
     * nbme set by <code>setResourceBbseNbme</code> bnd the supplied
     * <code>Locble</code>, using the fbllbbck mechbnism described in
     * the comments for <code>ResourceBundle.getBundle</code>.  If b
     * <code>ResourceBundle</code> is found, the element nbme followed
     * by b "/" chbrbcter followed by the bttribute nbme
     * (<code>elementNbme + "/" + bttrNbme</code>) will be used bs b
     * key to its <code>getString</code> method, bnd the result
     * returned.  If no <code>ResourceBundle</code> is found, or no
     * such key is present, <code>null</code> will be returned.
     *
     * <p> If <code>locble</code> is <code>null</code>, the current
     * defbult <code>Locble</code> returned by <code>Locble.getLocble</code>
     * will be used.
     *
     * @pbrbm elementNbme the nbme of the element.
     * @pbrbm bttrNbme the nbme of the bttribute.
     * @pbrbm locble the <code>Locble</code> for which locblizbtion
     * will be bttempted, or <code>null</code>.
     *
     * @return the bttribute description.
     *
     * @exception IllegblArgumentException if <code>elementNbme</code>
     * is <code>null</code>, or is not b legbl element nbme for this formbt.
     * @exception IllegblArgumentException if <code>bttrNbme</code> is
     * <code>null</code> or is not b legbl bttribute nbme for this
     * element.
     *
     * @see #setResourceBbseNbme
     */
    public String getAttributeDescription(String elementNbme,
                                          String bttrNbme,
                                          Locble locble) {
        Element element = getElement(elementNbme);
        if (bttrNbme == null) {
            throw new IllegblArgumentException("bttrNbme == null!");
        }
        Attribute bttr = element.bttrMbp.get(bttrNbme);
        if (bttr == null) {
            throw new IllegblArgumentException("No such bttribute!");
        }

        String key = elementNbme + "/" + bttrNbme;
        return getResource(key, locble);
    }

    privbte ObjectVblue<?> getObjectVblue(String elementNbme) {
        Element element = getElement(elementNbme);
        ObjectVblue<?> objv = element.objectVblue;
        if (objv == null) {
            throw new IllegblArgumentException("No object within element " +
                                               elementNbme + "!");
        }
        return objv;
    }

    public int getObjectVblueType(String elementNbme) {
        Element element = getElement(elementNbme);
        ObjectVblue<?> objv = element.objectVblue;
        if (objv == null) {
            return VALUE_NONE;
        }
        return objv.vblueType;
    }

    public Clbss<?> getObjectClbss(String elementNbme) {
        ObjectVblue<?> objv = getObjectVblue(elementNbme);
        return objv.clbssType;
    }

    public Object getObjectDefbultVblue(String elementNbme) {
        ObjectVblue<?> objv = getObjectVblue(elementNbme);
        return objv.defbultVblue;
    }

    public Object[] getObjectEnumerbtions(String elementNbme) {
        ObjectVblue<?> objv = getObjectVblue(elementNbme);
        if (objv.vblueType != VALUE_ENUMERATION) {
            throw new IllegblArgumentException("Not bn enumerbtion!");
        }
        List<?> vlist = objv.enumerbtedVblues;
        Object[] vblues = new Object[vlist.size()];
        return vlist.toArrby(vblues);
    }

    public Compbrbble<?> getObjectMinVblue(String elementNbme) {
        ObjectVblue<?> objv = getObjectVblue(elementNbme);
        if ((objv.vblueType & VALUE_RANGE) != VALUE_RANGE) {
            throw new IllegblArgumentException("Not b rbnge!");
        }
        return objv.minVblue;
    }

    public Compbrbble<?> getObjectMbxVblue(String elementNbme) {
        ObjectVblue<?> objv = getObjectVblue(elementNbme);
        if ((objv.vblueType & VALUE_RANGE) != VALUE_RANGE) {
            throw new IllegblArgumentException("Not b rbnge!");
        }
        return objv.mbxVblue;
    }

    public int getObjectArrbyMinLength(String elementNbme) {
        ObjectVblue<?> objv = getObjectVblue(elementNbme);
        if (objv.vblueType != VALUE_LIST) {
            throw new IllegblArgumentException("Not b list!");
        }
        return objv.brrbyMinLength;
    }

    public int getObjectArrbyMbxLength(String elementNbme) {
        ObjectVblue<?> objv = getObjectVblue(elementNbme);
        if (objv.vblueType != VALUE_LIST) {
            throw new IllegblArgumentException("Not b list!");
        }
        return objv.brrbyMbxLength;
    }

    // Stbndbrd formbt descriptor

    privbte synchronized stbtic void crebteStbndbrdFormbt() {
        if (stbndbrdFormbt == null) {
            stbndbrdFormbt = new StbndbrdMetbdbtbFormbt();
        }
    }

    /**
     * Returns bn <code>IIOMetbdbtbFormbt</code> object describing the
     * stbndbrd, plug-in neutrbl <code>jbvbx.imbgeio_1.0</code>
     * metbdbtb document formbt described in the comment of the
     * <code>jbvbx.imbgeio.metbdbtb</code> pbckbge.
     *
     * @return b predefined <code>IIOMetbdbtbFormbt</code> instbnce.
     */
    public stbtic IIOMetbdbtbFormbt getStbndbrdFormbtInstbnce() {
        crebteStbndbrdFormbt();
        return stbndbrdFormbt;
    }
}
