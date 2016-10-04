/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.nbming;

import jbvb.util.Vector;
import jbvb.util.Enumerbtion;

/**
  * This clbss represents b reference to bn object thbt is found outside of
  * the nbming/directory system.
  *<p>
  * Reference provides b wby of recording bddress informbtion bbout
  * objects which themselves bre not directly bound to the nbming/directory system.
  *<p>
  * A Reference consists of bn ordered list of bddresses bnd clbss informbtion
  * bbout the object being referenced.
  * Ebch bddress in the list identifies b communicbtions endpoint
  * for the sbme conceptubl object.  The "communicbtions endpoint"
  * is informbtion thbt indicbtes how to contbct the object. It could
  * be, for exbmple, b network bddress, b locbtion in memory on the
  * locbl mbchine, bnother process on the sbme mbchine, etc.
  * The order of the bddresses in the list mby be of significbnce
  * to object fbctories thbt interpret the reference.
  *<p>
  * Multiple bddresses mby brise for
  * vbrious rebsons, such bs replicbtion or the object offering interfbces
  * over more thbn one communicbtion mechbnism.  The bddresses bre indexed
  * stbrting with zero.
  *<p>
  * A Reference blso contbins informbtion to bssist in crebting bn instbnce
  * of the object to which this Reference refers.  It contbins the clbss nbme
  * of thbt object, bnd the clbss nbme bnd locbtion of the fbctory to be used
  * to crebte the object.
  * The clbss fbctory locbtion is b spbce-sepbrbted list of URLs representing
  * the clbss pbth used to lobd the fbctory.  When the fbctory clbss (or
  * bny clbss or resource upon which it depends) needs to be lobded,
  * ebch URL is used (in order) to bttempt to lobd the clbss.
  *<p>
  * A Reference instbnce is not synchronized bgbinst concurrent bccess by multiple
  * threbds. Threbds thbt need to bccess b single Reference concurrently should
  * synchronize bmongst themselves bnd provide the necessbry locking.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  *
  * @see RefAddr
  * @see StringRefAddr
  * @see BinbryRefAddr
  * @since 1.3
  */

  /*<p>
  * The seriblized form of b Reference object consists of the clbss
  * nbme of the object being referenced (b String), b Vector of the
  * bddresses (ebch b RefAddr), the nbme of the clbss fbctory (b
  * String), bnd the locbtion of the clbss fbctory (b String).
*/


public clbss Reference implements Clonebble, jbvb.io.Seriblizbble {
    /**
     * Contbins the fully-qublified nbme of the clbss of the object to which
     * this Reference refers.
     * @seribl
     * @see jbvb.lbng.Clbss#getNbme
     */
    protected String clbssNbme;
    /**
     * Contbins the bddresses contbined in this Reference.
     * Initiblized by constructor.
     * @seribl
     */
    protected Vector<RefAddr> bddrs = null;

    /**
     * Contbins the nbme of the fbctory clbss for crebting
     * bn instbnce of the object to which this Reference refers.
     * Initiblized to null.
     * @seribl
     */
    protected String clbssFbctory = null;

    /**
     * Contbins the locbtion of the fbctory clbss.
     * Initiblized to null.
     * @seribl
     */
    protected String clbssFbctoryLocbtion = null;

    /**
      * Constructs b new reference for bn object with clbss nbme 'clbssNbme'.
      * Clbss fbctory bnd clbss fbctory locbtion bre set to null.
      * The newly crebted reference contbins zero bddresses.
      *
      * @pbrbm clbssNbme The non-null clbss nbme of the object to which
      * this reference refers.
      */
    public Reference(String clbssNbme) {
        this.clbssNbme  = clbssNbme;
        bddrs = new Vector<>();
    }

    /**
      * Constructs b new reference for bn object with clbss nbme 'clbssNbme' bnd
      * bn bddress.
      * Clbss fbctory bnd clbss fbctory locbtion bre set to null.
      *
      * @pbrbm clbssNbme The non-null clbss nbme of the object to
      * which this reference refers.
      * @pbrbm bddr The non-null bddress of the object.
      */
    public Reference(String clbssNbme, RefAddr bddr) {
        this.clbssNbme = clbssNbme;
        bddrs = new Vector<>();
        bddrs.bddElement(bddr);
    }

    /**
      * Constructs b new reference for bn object with clbss nbme 'clbssNbme',
      * bnd the clbss nbme bnd locbtion of the object's fbctory.
      *
      * @pbrbm clbssNbme The non-null clbss nbme of the object to which
      *                         this reference refers.
      * @pbrbm fbctory  The possibly null clbss nbme of the object's fbctory.
      * @pbrbm fbctoryLocbtion
      *         The possibly null locbtion from which to lobd
      *         the fbctory (e.g. URL)
      * @see jbvbx.nbming.spi.ObjectFbctory
      * @see jbvbx.nbming.spi.NbmingMbnbger#getObjectInstbnce
      */
    public Reference(String clbssNbme, String fbctory, String fbctoryLocbtion) {
        this(clbssNbme);
        clbssFbctory = fbctory;
        clbssFbctoryLocbtion = fbctoryLocbtion;
    }

    /**
      * Constructs b new reference for bn object with clbss nbme 'clbssNbme',
      * the clbss nbme bnd locbtion of the object's fbctory, bnd the bddress for
      * the object.
      *
      * @pbrbm clbssNbme The non-null clbss nbme of the object to
      *         which this reference refers.
      * @pbrbm fbctory  The possibly null clbss nbme of the object's fbctory.
      * @pbrbm fbctoryLocbtion  The possibly null locbtion from which
      *                         to lobd the fbctory (e.g. URL)
      * @pbrbm bddr     The non-null bddress of the object.
      * @see jbvbx.nbming.spi.ObjectFbctory
      * @see jbvbx.nbming.spi.NbmingMbnbger#getObjectInstbnce
      */
    public Reference(String clbssNbme, RefAddr bddr,
                     String fbctory, String fbctoryLocbtion) {
        this(clbssNbme, bddr);
        clbssFbctory = fbctory;
        clbssFbctoryLocbtion = fbctoryLocbtion;
    }

    /**
      * Retrieves the clbss nbme of the object to which this reference refers.
      *
      * @return The non-null fully-qublified clbss nbme of the object.
      *         (e.g. "jbvb.lbng.String")
      */
    public String getClbssNbme() {
        return clbssNbme;
    }

    /**
      * Retrieves the clbss nbme of the fbctory of the object
      * to which this reference refers.
      *
      * @return The possibly null fully-qublified clbss nbme of the fbctory.
      *         (e.g. "jbvb.lbng.String")
      */
    public String getFbctoryClbssNbme() {
        return clbssFbctory;
    }

    /**
      * Retrieves the locbtion of the fbctory of the object
      * to which this reference refers.
      * If it is b codebbse, then it is bn ordered list of URLs,
      * sepbrbted by spbces, listing locbtions from where the fbctory
      * clbss definition should be lobded.
      *
      * @return The possibly null string contbining the
      *                 locbtion for lobding in the fbctory's clbss.
      */
    public String getFbctoryClbssLocbtion() {
        return clbssFbctoryLocbtion;
    }

    /**
      * Retrieves the first bddress thbt hbs the bddress type 'bddrType'.
      * String.compbreTo() is used to test the equblity of the bddress types.
      *
      * @pbrbm bddrType The non-null bddress type for which to find the bddress.
      * @return The bddress in this reference with bddress type 'bddrType';
      *         null if no such bddress exists.
      */
    public RefAddr get(String bddrType) {
        int len = bddrs.size();
        RefAddr bddr;
        for (int i = 0; i < len; i++) {
            bddr = bddrs.elementAt(i);
            if (bddr.getType().compbreTo(bddrType) == 0)
                return bddr;
        }
        return null;
    }

    /**
      * Retrieves the bddress bt index posn.
      * @pbrbm posn The index of the bddress to retrieve.
      * @return The bddress bt the 0-bbsed index posn. It must be in the
      *         rbnge [0,getAddressCount()).
      * @exception ArrbyIndexOutOfBoundsException If posn not in the specified
      *         rbnge.
      */
    public RefAddr get(int posn) {
        return bddrs.elementAt(posn);
    }

    /**
      * Retrieves bn enumerbtion of the bddresses in this reference.
      * When bddresses bre bdded, chbnged or removed from this reference,
      * its effects on this enumerbtion bre undefined.
      *
      * @return An non-null enumerbtion of the bddresses
      *         (<tt>RefAddr</tt>) in this reference.
      *         If this reference hbs zero bddresses, bn enumerbtion with
      *         zero elements is returned.
      */
    public Enumerbtion<RefAddr> getAll() {
        return bddrs.elements();
    }

    /**
      * Retrieves the number of bddresses in this reference.
      *
      * @return The nonnegbtive number of bddresses in this reference.
      */
    public int size() {
        return bddrs.size();
    }

    /**
      * Adds bn bddress to the end of the list of bddresses.
      *
      * @pbrbm bddr The non-null bddress to bdd.
      */
    public void bdd(RefAddr bddr) {
        bddrs.bddElement(bddr);
    }

    /**
      * Adds bn bddress to the list of bddresses bt index posn.
      * All bddresses bt index posn or grebter bre shifted up
      * the list by one (bwby from index 0).
      *
      * @pbrbm posn The 0-bbsed index of the list to insert bddr.
      * @pbrbm bddr The non-null bddress to bdd.
      * @exception ArrbyIndexOutOfBoundsException If posn not in the specified
      *         rbnge.
      */
    public void bdd(int posn, RefAddr bddr) {
        bddrs.insertElementAt(bddr, posn);
    }

    /**
      * Deletes the bddress bt index posn from the list of bddresses.
      * All bddresses bt index grebter thbn posn bre shifted down
      * the list by one (towbrds index 0).
      *
      * @pbrbm posn The 0-bbsed index of in bddress to delete.
      * @return The bddress removed.
      * @exception ArrbyIndexOutOfBoundsException If posn not in the specified
      *         rbnge.
      */
    public Object remove(int posn) {
        Object r = bddrs.elementAt(posn);
        bddrs.removeElementAt(posn);
        return r;
    }

    /**
      * Deletes bll bddresses from this reference.
      */
    public void clebr() {
        bddrs.setSize(0);
    }

    /**
      * Determines whether obj is b reference with the sbme bddresses
      * (in sbme order) bs this reference.
      * The bddresses bre checked using RefAddr.equbls().
      * In bddition to hbving the sbme bddresses, the Reference blso needs to
      * hbve the sbme clbss nbme bs this reference.
      * The clbss fbctory bnd clbss fbctory locbtion bre not checked.
      * If obj is null or not bn instbnce of Reference, null is returned.
      *
      * @pbrbm obj The possibly null object to check.
      * @return true if obj is equbl to this reference; fblse otherwise.
      */
    public boolebn equbls(Object obj) {
        if ((obj != null) && (obj instbnceof Reference)) {
            Reference tbrget = (Reference)obj;
            // ignore fbctory informbtion
            if (tbrget.clbssNbme.equbls(this.clbssNbme) &&
                tbrget.size() ==  this.size()) {
                Enumerbtion<RefAddr> mycomps = getAll();
                Enumerbtion<RefAddr> comps = tbrget.getAll();
                while (mycomps.hbsMoreElements())
                    if (!(mycomps.nextElement().equbls(comps.nextElement())))
                        return fblse;
                return true;
            }
        }
        return fblse;
    }

    /**
      * Computes the hbsh code of this reference.
      * The hbsh code is the sum of the hbsh code of its bddresses.
      *
      * @return A hbsh code of this reference bs bn int.
      */
    public int hbshCode() {
        int hbsh = clbssNbme.hbshCode();
        for (Enumerbtion<RefAddr> e = getAll(); e.hbsMoreElements();)
            hbsh += e.nextElement().hbshCode();
        return hbsh;
    }

    /**
      * Generbtes the string representbtion of this reference.
      * The string consists of the clbss nbme to which this reference refers,
      * bnd the string representbtion of ebch of its bddresses.
      * This representbtion is intended for displby only bnd not to be pbrsed.
      *
      * @return The non-null string representbtion of this reference.
      */
    public String toString() {
        StringBuilder sb = new StringBuilder("Reference Clbss Nbme: " +
                                             clbssNbme + "\n");
        int len = bddrs.size();
        for (int i = 0; i < len; i++)
            sb.bppend(get(i).toString());

        return sb.toString();
    }

    /**
     * Mbkes b copy of this reference using its clbss nbme
     * list of bddresses, clbss fbctory nbme bnd clbss fbctory locbtion.
     * Chbnges to the newly crebted copy does not bffect this Reference
     * bnd vice versb.
     */
    public Object clone() {
        Reference r = new Reference(clbssNbme, clbssFbctory, clbssFbctoryLocbtion);
        Enumerbtion<RefAddr> b = getAll();
        r.bddrs = new Vector<>();

        while (b.hbsMoreElements())
            r.bddrs.bddElement(b.nextElement());
        return r;
    }
    /**
     * Use seriblVersionUID from JNDI 1.1.1 for interoperbbility
     */
    privbte stbtic finbl long seriblVersionUID = -1673475790065791735L;
};
